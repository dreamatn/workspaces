package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.DD.*;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSTWIQ {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm DCTCDDAT_RD;
    DBParm DCTTXTOT_RD;
    DBParm DCTCRDLT_RD;
    DBParm DCTPRDLT_RD;
    boolean pgmRtn = false;
    String CPN_DCZCDDAT = "DC-U-CARD-INF";
    String K_OUTPUT_FMT = "DC100";
    String WS_ERR_MSG = " ";
    String WS_CARD_NO = " ";
    String WS_TXN_TYP = " ";
    double WS_TEM_DLY_LMT_AMT = 0;
    int WS_TEM_DLY_LMT_VOL = 0;
    int WS_PIN_ERR_CNT = 0;
    char WS_TBL_FLAG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCWRMSG SCCWRMSG = new SCCWRMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCRTXTOT DCRTXTOT = new DCRTXTOT();
    DCRPRDLT DCRPRDLT = new DCRPRDLT();
    DCRCRDLT DCRCRDLT = new DCRCRDLT();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    DCCUPSWM DCCUPSWM = new DCCUPSWM();
    DCCUCINF DCCUCINF = new DCCUCINF();
    DCCUPWER DCCUPWER = new DCCUPWER();
    CICCUST CICCUST = new CICCUST();
    CICQACRL CICQACRL = new CICQACRL();
    SCCSTAR SCCSTAR = new SCCSTAR();
    DCRCPARM DCRCPARM = new DCRCPARM();
    DCRPRDPR DCRPRDPR = new DCRPRDPR();
    BPCPRMR BPCPRMR = new BPCPRMR();
    DCCF910 DCCF910 = new DCCF910();
    DCCPCDCK DCCPCDCK = new DCCPCDCK();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCS9100 DCCS9100;
    public void MP(SCCGWA SCCGWA, DCCS9100 DCCS9100) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCS9100 = DCCS9100;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSTWIQ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, DCCF910);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCS9100.CARD_NO);
        CEP.TRC(SCCGWA, DCCS9100.REGN_TYP);
        CEP.TRC(SCCGWA, DCCS9100.CHNL_NO);
        CEP.TRC(SCCGWA, DCCS9100.TR_CCY);
        CEP.TRC(SCCGWA, DCCS9100.TXN_PSW);
        B010_QUERY_PROC();
        if (pgmRtn) return;
        B020_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B010_QUERY_PROC() throws IOException,SQLException,Exception {
        if (DCCS9100.CARD_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NO_MISSING);
        }
        IBS.init(SCCGWA, DCCUCINF);
        DCCUCINF.CARD_NO = DCCS9100.CARD_NO;
        S000_CALL_DCZUCINF();
        if (pgmRtn) return;
        if (DCCUCINF.ADSC_TYPE == 'C') {
            IBS.init(SCCGWA, CICQACRL);
            CICQACRL.FUNC = 'I';
            CICQACRL.DATA.AC_NO = DCCS9100.CARD_NO;
            CICQACRL.DATA.AC_REL = "04";
            S000_CALL_CIZQACRL();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
            WS_CARD_NO = CICQACRL.O_DATA.O_REL_AC_NO;
        } else {
            WS_CARD_NO = DCCS9100.CARD_NO;
        }
        CEP.TRC(SCCGWA, WS_CARD_NO);
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCS9100.CARD_NO;
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        if (DCRCDDAT.CARD_STS == 'C') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_BE_CLOSED);
        }
        if (DCRCDDAT.CARD_STS != 'N') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_NORMAL_STS);
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(0, 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ALR_CARD_LOSS_STS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ALR_CARD_LOSS_STS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCS9100.TXN_PSW.trim().length() > 0) {
            B100_CHECK_PSW();
            if (pgmRtn) return;
        }
        DCCF910.CHNL_NO = DCCS9100.CHNL_NO;
        DCCF910.CI_NO = DCRCDDAT.CARD_HLDR_CINO;
        DCCF910.CARD_STS = DCRCDDAT.CARD_STS;
        DCCF910.OPEN_BR = DCRCDDAT.ISSU_BR;
        WS_TXN_TYP = "01";
        B010_COMPUTE_PRCO();
        if (pgmRtn) return;
        DCCF910.DRAW_AMT = WS_TEM_DLY_LMT_AMT;
        DCCF910.DRAW_TMS = (short) WS_TEM_DLY_LMT_VOL;
        WS_TXN_TYP = "02";
        B010_COMPUTE_PRCO();
        if (pgmRtn) return;
        DCCF910.DEPOS_AMT = WS_TEM_DLY_LMT_AMT;
        DCCF910.DEPOS_TMS = (short) WS_TEM_DLY_LMT_VOL;
        WS_TXN_TYP = "03";
        B010_COMPUTE_PRCO();
        if (pgmRtn) return;
        DCCF910.COST_AMT = WS_TEM_DLY_LMT_AMT;
        DCCF910.COST_TMS = (short) WS_TEM_DLY_LMT_VOL;
        WS_TXN_TYP = "04";
        B010_COMPUTE_PRCO();
        if (pgmRtn) return;
        DCCF910.TRANS_AMT = WS_TEM_DLY_LMT_AMT;
        DCCF910.TRANS_TMS = (short) WS_TEM_DLY_LMT_VOL;
        IBS.init(SCCGWA, DDCIQBAL);
        DDCIQBAL.DATA.AC = WS_CARD_NO;
        DDCIQBAL.DATA.CCY = DCCS9100.TR_CCY;
        S000_CALL_DDZIQBAL();
        if (pgmRtn) return;
        DCCF910.AVAL_AMT = DDCIQBAL.DATA.AVL_BAL;
        DCCF910.BALANCE = DDCIQBAL.DATA.CURR_BAL;
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.CURR_BAL);
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.AVL_BAL);
        IBS.init(SCCGWA, CICCUST);
        CICCUST.DATA.CI_NO = DCRCDDAT.CARD_HLDR_CINO;
        CICCUST.FUNC = 'C';
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        DCCF910.ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
        DCCF910.ID_NO = CICCUST.O_DATA.O_ID_NO;
        DCCF910.CI_NM = CICCUST.O_DATA.O_CI_NM;
        DCCF910.TELPHONE = CICCUST.O_DATA.O_TEL_NO;
    }
    public void B010_COMPUTE_PRCO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRTXTOT);
        DCRTXTOT.KEY.CARD_NO = DCCS9100.CARD_NO;
        DCRTXTOT.KEY.REGN_TYP = DCCS9100.REGN_TYP;
        DCRTXTOT.KEY.CHNL_NO = DCCS9100.CHNL_NO;
        DCRTXTOT.KEY.LMT_CCY = DCCS9100.TR_CCY;
        DCRTXTOT.KEY.TXN_TYPE = WS_TXN_TYP;
        CEP.TRC(SCCGWA, DCRTXTOT.KEY.CARD_NO);
        CEP.TRC(SCCGWA, DCRTXTOT.KEY.REGN_TYP);
        CEP.TRC(SCCGWA, DCRTXTOT.KEY.CHNL_NO);
        CEP.TRC(SCCGWA, DCRTXTOT.KEY.LMT_CCY);
        CEP.TRC(SCCGWA, DCRTXTOT.KEY.TXN_TYPE);
        T000_READ_DCTTXTOT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (WS_TBL_FLAG == 'N') {
            DCRTXTOT.DLY_TOT_AMT = 0;
            DCRTXTOT.DLY_TOT_VOL = 0;
        }
        CEP.TRC(SCCGWA, DCRTXTOT.DLY_TOT_AMT);
        CEP.TRC(SCCGWA, DCRTXTOT.DLY_TOT_VOL);
        IBS.init(SCCGWA, DCRCRDLT);
        DCRCRDLT.KEY.CARD_NO = DCCS9100.CARD_NO;
        DCRCRDLT.KEY.REGN_TYP = DCCS9100.REGN_TYP;
        DCRCRDLT.KEY.CHNL_NO = DCCS9100.CHNL_NO;
        DCRCRDLT.KEY.TXN_TYPE = WS_TXN_TYP;
        DCRCRDLT.KEY.LMT_CCY = DCCS9100.TR_CCY;
        T000_READ_DCTCRDLT();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'Y') {
            CEP.TRC(SCCGWA, DCRCRDLT.DLY_LMT_AMT);
            CEP.TRC(SCCGWA, DCRCRDLT.DLY_LMT_VOL);
            WS_TEM_DLY_LMT_AMT = DCRCRDLT.DLY_LMT_AMT - DCRTXTOT.DLY_TOT_AMT;
            WS_TEM_DLY_LMT_VOL = DCRCRDLT.DLY_LMT_VOL - DCRTXTOT.DLY_TOT_VOL;
            CEP.TRC(SCCGWA, WS_TXN_TYP);
            CEP.TRC(SCCGWA, "CRDLT-WS");
            CEP.TRC(SCCGWA, WS_TEM_DLY_LMT_AMT);
            CEP.TRC(SCCGWA, WS_TEM_DLY_LMT_VOL);
            if (WS_TEM_DLY_LMT_AMT < 0) {
                WS_TEM_DLY_LMT_AMT = 0;
            }
            if (WS_TEM_DLY_LMT_VOL < 0) {
                WS_TEM_DLY_LMT_VOL = 0;
            }
        } else {
            IBS.init(SCCGWA, DCRPRDLT);
            DCRPRDLT.KEY.PROD_CD = DCRCDDAT.PROD_CD;
            DCRPRDLT.KEY.REGN_TYP = DCCS9100.REGN_TYP;
            DCRPRDLT.KEY.CHNL_NO = DCCS9100.CHNL_NO;
            DCRPRDLT.KEY.TXN_TYPE = WS_TXN_TYP;
            DCRPRDLT.KEY.LMT_CCY = DCCS9100.TR_CCY;
            T000_READ_DCTPRDLT();
            if (pgmRtn) return;
            WS_TEM_DLY_LMT_AMT = DCRPRDLT.DLY_LMT_AMT - DCRTXTOT.DLY_TOT_AMT;
            WS_TEM_DLY_LMT_VOL = DCRPRDLT.DLY_LMT_VOL - DCRTXTOT.DLY_TOT_VOL;
            CEP.TRC(SCCGWA, WS_TXN_TYP);
            CEP.TRC(SCCGWA, "PRDLT-WS");
            CEP.TRC(SCCGWA, WS_TEM_DLY_LMT_AMT);
            CEP.TRC(SCCGWA, WS_TEM_DLY_LMT_VOL);
            if (WS_TEM_DLY_LMT_AMT < 0) {
                WS_TEM_DLY_LMT_AMT = 0;
            }
            if (WS_TEM_DLY_LMT_VOL < 0) {
                WS_TEM_DLY_LMT_VOL = 0;
            }
        }
    }
    public void B020_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DCCF910;
        SCCFMT.DATA_LEN = 487;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B099_CHECK_PSW() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "CHECK PASSWORD");
        IBS.init(SCCGWA, DCCUPSWM);
        DCCUPSWM.FUNC = 'C';
        DCCUPSWM.PSW_TYP = 'T';
        DCCUPSWM.OLD_AGR_NO = DCCS9100.CARD_NO;
        DCCUPSWM.PSW_FLG = DCRCDDAT.PSW_TYP;
        DCCUPSWM.CARD_PSW_OLD = DCCS9100.TXN_PSW;
        S000_CALL_DCZUPSWM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCRCDDAT.PSW_TYP);
        CEP.TRC(SCCGWA, DCRCDDAT.TRAN_PIN_DAT);
        CEP.TRC(SCCGWA, DCCUPSWM.O_OLD_PSW);
        CEP.TRC(SCCGWA, DCCUPSWM.O_NEW_PSW);
        if (DCRCDDAT.PSW_TYP == 'O') {
            if (DCCUPSWM.O_OLD_PSW.equalsIgnoreCase(DCRCDDAT.TRAN_PIN_DAT)) {
                IBS.init(SCCGWA, DCCUPWER);
                DCCUPWER.CARD_NO = DCCS9100.CARD_NO;
                DCCUPWER.PWPASS_FLG = 'Y';
                S000_PASSWORD_SPCDEAL();
                if (pgmRtn) return;
            } else {
                IBS.init(SCCGWA, DCCUPWER);
                DCCUPWER.CARD_NO = DCCS9100.CARD_NO;
                DCCUPWER.TOR_SYSID = SCCGWA.COMM_AREA.TOR_SYSID;
                DCCUPWER.PWPASS_FLG = 'N';
                S000_PASSWORD_SPCDEAL();
                if (pgmRtn) return;
                IBS.init(SCCGWA, DCRCPARM);
                IBS.init(SCCGWA, BPCPRMR);
                DCRCPARM.KEY.TYP = "DCPRM";
                BPCPRMR.FUNC = ' ';
                DCRCPARM.KEY.CD = SCCGWA.COMM_AREA.TR_BANK;
                IBS.CPY2CLS(SCCGWA, DCRCPARM.KEY.CD, DCRCPARM.KEY.KEY1);
                BPCPRMR.DAT_PTR = DCRCPARM;
                S000_CALL_BPZPRMR();
                if (pgmRtn) return;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
                if (JIBS_tmp_str[0].equalsIgnoreCase(DCCMSG_ERROR_MSG.NOT_FOUND)) {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.NOT_FOUND;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, DCRCPARM.DATA_TXT.PSW_LOCK_NUM);
                WS_PIN_ERR_CNT = DCRCDDAT.PIN_ERR_CNT + 1;
                if (WS_PIN_ERR_CNT == DCRCPARM.DATA_TXT.PSW_LOCK_NUM) {
                    CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_PIN_LOCK);
                } else {
                    CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_PSWD_NOT_MATCH);
                }
            }
        }
        if (DCRCDDAT.PSW_TYP == 'N') {
            if (DCCUPSWM.O_NEW_PSW.equalsIgnoreCase(DCRCDDAT.TRAN_PIN_DAT)) {
                IBS.init(SCCGWA, DCCUPWER);
                DCCUPWER.CARD_NO = DCCS9100.CARD_NO;
                DCCUPWER.PWPASS_FLG = 'Y';
                S000_PASSWORD_SPCDEAL();
                if (pgmRtn) return;
            } else {
                IBS.init(SCCGWA, DCCUPWER);
                DCCUPWER.CARD_NO = DCCS9100.CARD_NO;
                DCCUPWER.TOR_SYSID = SCCGWA.COMM_AREA.TOR_SYSID;
                DCCUPWER.PWPASS_FLG = 'N';
                S000_PASSWORD_SPCDEAL();
                if (pgmRtn) return;
                IBS.init(SCCGWA, DCRCPARM);
                IBS.init(SCCGWA, BPCPRMR);
                DCRCPARM.KEY.TYP = "DCPRM";
                BPCPRMR.FUNC = ' ';
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_BANK);
                DCRCPARM.KEY.CD = SCCGWA.COMM_AREA.TR_BANK;
                IBS.CPY2CLS(SCCGWA, DCRCPARM.KEY.CD, DCRCPARM.KEY.KEY1);
                BPCPRMR.DAT_PTR = DCRCPARM;
                S000_CALL_BPZPRMR();
                if (pgmRtn) return;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
                if (JIBS_tmp_str[0].equalsIgnoreCase(DCCMSG_ERROR_MSG.NOT_FOUND)) {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.NOT_FOUND;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, DCRCPARM.DATA_TXT.PSW_LOCK_NUM);
                WS_PIN_ERR_CNT = DCRCDDAT.PIN_ERR_CNT + 1;
                if (WS_PIN_ERR_CNT == DCRCPARM.DATA_TXT.PSW_LOCK_NUM) {
                    CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_PIN_LOCK);
                } else {
                    CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_PSWD_NOT_MATCH);
                }
            }
        }
    }
    public void B100_CHECK_PSW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCPCDCK);
        DCCPCDCK.FUNC_CODE = 'P';
        DCCPCDCK.CARD_NO = DCCS9100.CARD_NO;
        DCCPCDCK.CARD_PSW = DCCS9100.TXN_PSW;
        S000_CALL_DCZPCDCK();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ        ", BPCPRMR);
        CEP.TRC(SCCGWA, BPCPRMR.RC);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF);
        if (DCCUCINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZPCDCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-P-CARD-TRKDAT-CHK", DCCPCDCK);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, CICCUST.RC);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
        if (CICQACRL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACRL.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_PASSWORD_SPCDEAL() throws IOException,SQLException,Exception {
        DCZUPWER DCZUPWER = new DCZUPWER();
        DCZUPWER.MP(SCCGWA, DCCUPWER);
        CEP.TRC(SCCGWA, DCCUPWER.PSWLOCK_FLG);
        if (DCCUPWER.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.NOT_FOUND);
        }
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "NNN");
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDDAT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-CCY-BAL", DDCIQBAL);
        if (DDCIQBAL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, DDCIQBAL.RC);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQBAL.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUPSWM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-PSW-MAINTAIN", DCCUPSWM);
        if (DCCUPSWM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUPSWM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void T000_READ_DCTTXTOT() throws IOException,SQLException,Exception {
        DCTTXTOT_RD = new DBParm();
        DCTTXTOT_RD.TableName = "DCTTXTOT";
        IBS.READ(SCCGWA, DCRTXTOT, DCTTXTOT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        }
    }
    public void T000_READ_DCTCRDLT() throws IOException,SQLException,Exception {
        DCTCRDLT_RD = new DBParm();
        DCTCRDLT_RD.TableName = "DCTCRDLT";
        IBS.READ(SCCGWA, DCRCRDLT, DCTCRDLT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        }
    }
    public void T000_READ_DCTPRDLT() throws IOException,SQLException,Exception {
        DCTPRDLT_RD = new DBParm();
        DCTPRDLT_RD.TableName = "DCTPRDLT";
        IBS.READ(SCCGWA, DCRPRDLT, DCTPRDLT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        }
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
