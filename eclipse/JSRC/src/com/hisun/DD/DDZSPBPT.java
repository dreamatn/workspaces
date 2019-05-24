package com.hisun.DD;

import com.hisun.DC.*;
import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSPBPT {
    int JIBS_tmp_int;
    DBParm DDTVCH_RD;
    DBParm DDTPBBL_RD;
    DBParm DDTUPT_RD;
    DBParm DDTCCY_RD;
    brParm DDTUPT_BR = new brParm();
    DBParm DDTMST_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_STS_TABLE_APP = "DD";
    String K_STS_TBL = "5500";
    String K_PRT_UPT_FMT = "DD501";
    String CPN_SCSSCKDT = "SCSSCKDT";
    String CPN_UNI_CIZACCU = "CI-INQ-ACCU";
    String CPN_REC_NHIS = "BP-REC-NHIS  ";
    String K_HIS_RMKS = "CHANGE PASSBOOK PRT UPT";
    String CPN_I_INQ_DDPRD = "DD-I-INQ-DDPRD";
    String CPN_I_PSBK_PROC = "DD-I-PSBK-PROC";
    String K_HIS_COPYBOOK_NAME = "DDCSPUPT";
    String K_HIS_REMARKS = "ACCOUNT PRINT PASSBOOK";
    String WS_ERR_MSG = " ";
    short WS_PAGE = 0;
    short WS_LINE = 0;
    short WS_PSB_LEFT_LINE = 0;
    short WS_PSB_AVA_PRT_LINE = 0;
    short WS_PRT_IDX = 0;
    DDZSPBPT_WS_PRT_DATA WS_PRT_DATA = new DDZSPBPT_WS_PRT_DATA();
    char WS_TABLE_FLG = ' ';
    char WS_PBBL_FLAG = ' ';
    char WS_UPT_FLAG = ' ';
    char WS_CCY_FLAG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    BPCPRMR BPCPRMR = new BPCPRMR();
    DDCPBKS DDCPBKS = new DDCPBKS();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    CICACCU CICACCU = new CICACCU();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CICQACRL CICQACRL = new CICQACRL();
    DCCUCINF DCCUCINF = new DCCUCINF();
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    DDRPBBL DDRPBBL = new DDRPBBL();
    DDRUPT DDRUPT = new DDRUPT();
    DDRVCH DDRVCH = new DDRVCH();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    DDCSPBPT DDCSPBPT;
    public void MP(SCCGWA SCCGWA, DDCSPBPT DDCSPBPT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSPBPT = DDCSPBPT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSPBPT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B030_GET_AC_INF_PROC();
        if (pgmRtn) return;
        B040_CI_INF_PROC();
        if (pgmRtn) return;
        B050_CHECK_STS_TBL_PROC();
        if (pgmRtn) return;
        B080_PRT_UPT_PROC();
        if (pgmRtn) return;
        B098_UPD_MST_INF_PROC();
        if (pgmRtn) return;
        B170_NFIN_TXN_HIS_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSPBPT.AC);
    }
    public void B030_GET_AC_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.DATA.REL_AC_NO = DDCSPBPT.AC;
        CICQACRL.DATA.AC_REL = "12";
        CICQACRL.FUNC = '4';
        CICQACRL.FUNC = 'I';
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_AC_NO);
        if (CICQACRL.RC.RC_CODE == 0) {
            DDCSPBPT.AC = CICQACRL.O_DATA.O_AC_NO;
            B010_CHK_CARD_NO_PROC();
            if (pgmRtn) return;
        } else {
        }
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCSPBPT.AC;
        T000_READ_UPDATE_DDTMST();
        if (pgmRtn) return;
    }
    public void B010_CHK_CARD_NO_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUCINF);
        DCCUCINF.CARD_NO = CICQACRL.DATA.REL_AC_NO;
        S000_CALL_DCZUCINF();
        if (pgmRtn) return;
        if (DCCUCINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUCINF.CARD_STS != 'N') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_NORMAL_STS);
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(0, 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_VCH_ORAL_LOSS_CNOT);
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.VCH_FORMAL_LOSS_CNOT_TR);
        }
    }
    public void B040_CI_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDCSPBPT.AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
    }
    public void B050_CHECK_STS_TBL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFCSTS);
        BPCFCSTS.AP_MMO = K_STS_TABLE_APP;
        BPCFCSTS.TBL_NO = K_STS_TBL;
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        if (CICACCU.DATA.CI_STSW == null) CICACCU.DATA.CI_STSW = "";
        JIBS_tmp_int = CICACCU.DATA.CI_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICACCU.DATA.CI_STSW += " ";
        BPCFCSTS.STATUS_WORD = CICACCU.DATA.CI_STSW + BPCFCSTS.STATUS_WORD.substring(80);
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 100 - 1) + DDRMST.AC_STS_WORD + BPCFCSTS.STATUS_WORD.substring(100 + 99 - 1);
        CEP.TRC(SCCGWA, BPCFCSTS.AP_MMO);
        CEP.TRC(SCCGWA, BPCFCSTS.TBL_NO);
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD);
        S000_CALL_BPZFCSTS();
        if (pgmRtn) return;
    }
    public void B080_PRT_UPT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_PRT_DATA);
        T000_READ_DDTVCH();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDRUPT);
        DDRUPT.KEY.CUS_AC = DDCSPBPT.AC;
        T000_READ_DDTUPT();
        if (pgmRtn) return;
        if (WS_TABLE_FLG == 'N') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_UPT_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_PRT_DATA.WS_AC = DDCSPBPT.AC;
        R000_GET_PASSBOOK_PARM();
        if (pgmRtn) return;
        WS_PRT_DATA.WS_TOT_PAGE = DDCPBKS.DATA_TXT.MAX_PAGE;
        WS_PRT_DATA.WS_CURR_ROW = DDCPBKS.DATA_TXT.PAGE_LINE;
        WS_PRT_DATA.WS_TOT_UPT_ROW = DDRVCH.UPT_CNT;
        WS_PSB_LEFT_LINE = (short) (DDCPBKS.DATA_TXT.MAX_LINE - DDRVCH.PRT_LINE);
        CEP.TRC(SCCGWA, WS_PSB_LEFT_LINE);
        if (WS_PSB_LEFT_LINE <= 0 
            || WS_PSB_LEFT_LINE < DDCSPBPT.PAGE_ROW) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_NET_PRT_ITM_ZERO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSPBPT.PAGE_ROW >= WS_PSB_LEFT_LINE) {
            WS_PSB_AVA_PRT_LINE = WS_PSB_LEFT_LINE;
        } else {
            WS_PSB_AVA_PRT_LINE = (short) DDCSPBPT.PAGE_ROW;
        }
        CEP.TRC(SCCGWA, WS_PSB_AVA_PRT_LINE);
        CEP.TRC(SCCGWA, WS_PRT_IDX);
        CEP.TRC(SCCGWA, DDRVCH.UPT_CNT);
        T000_STARTBR_DDTUPT();
        if (pgmRtn) return;
        T000_READNEXT_DDTUPT();
        if (pgmRtn) return;
        while (WS_UPT_FLAG != 'N' 
            && WS_PRT_IDX < WS_PSB_AVA_PRT_LINE) {
            WS_PRT_IDX += 1;
            DDRPBBL.KEY.CUS_AC = DDRUPT.KEY.CUS_AC;
            DDRPBBL.KEY.CCY = DDRUPT.CCY;
            DDRPBBL.KEY.CCY_TYPE = DDRUPT.CCY_TYPE;
            T000_READ_UPD_DDTPBBL();
            if (pgmRtn) return;
            if (WS_PBBL_FLAG == 'N') {
                if (DDRVCH.LAST_PB_BAL != 0) {
                    if (DDRUPT.CCY.equalsIgnoreCase(DDRVCH.LAST_PB_CCY)) {
                        if (DDRUPT.TXN_TYPE == 'D') {
                            DDRPBBL.PSBK_BAL = DDRVCH.LAST_PB_BAL - DDRUPT.TXN_AMT;
                        } else {
                            DDRPBBL.PSBK_BAL = DDRVCH.LAST_PB_BAL + DDRUPT.TXN_AMT;
                        }
                    } else {
                        if (DDRUPT.TXN_TYPE == 'D') {
                            DDRPBBL.PSBK_BAL -= DDRUPT.TXN_AMT;
                        } else {
                            DDRPBBL.PSBK_BAL += DDRUPT.TXN_AMT;
                        }
                    }
                } else {
                    if (DDRUPT.TXN_TYPE == 'D') {
                        DDRPBBL.PSBK_BAL -= DDRUPT.TXN_AMT;
                    } else {
                        DDRPBBL.PSBK_BAL += DDRUPT.TXN_AMT;
                    }
                }
                T000_WRITE_DDTPBBL();
                if (pgmRtn) return;
            } else {
                if (DDRUPT.TXN_TYPE == 'D') {
                    DDRPBBL.PSBK_BAL -= DDRUPT.TXN_AMT;
                } else {
                    DDRPBBL.PSBK_BAL += DDRUPT.TXN_AMT;
                }
                T000_REWRITE_DDTPBBL();
                if (pgmRtn) return;
            }
            DDRVCH.UPT_CNT -= 1;
            DDRVCH.PRT_LINE += 1;
            CEP.TRC(SCCGWA, DDRVCH.PRT_LINE);
            WS_PRT_DATA.WS_ARRY[WS_PRT_IDX-1].WS_DATE = DDRUPT.TXN_DATE;
            WS_PRT_DATA.WS_ARRY[WS_PRT_IDX-1].WS_CCY = DDRUPT.CCY;
            WS_PRT_DATA.WS_ARRY[WS_PRT_IDX-1].WS_CCY_TYPE = DDRUPT.CCY_TYPE;
            if (DDRUPT.TXN_TYPE == 'D') {
                WS_PRT_DATA.WS_ARRY[WS_PRT_IDX-1].WS_AMT = DDRUPT.TXN_AMT;
                WS_PRT_DATA.WS_ARRY[WS_PRT_IDX-1].WS_DRCR_FLG = 'D';
            } else {
                WS_PRT_DATA.WS_ARRY[WS_PRT_IDX-1].WS_AMT = DDRUPT.TXN_AMT;
                WS_PRT_DATA.WS_ARRY[WS_PRT_IDX-1].WS_DRCR_FLG = 'C';
            }
            WS_PRT_DATA.WS_ARRY[WS_PRT_IDX-1].WS_BALANCE = DDRPBBL.PSBK_BAL;
            WS_PRT_DATA.WS_ARRY[WS_PRT_IDX-1].WS_REF = DDRUPT.TXN_MMO;
            WS_PRT_DATA.WS_ARRY[WS_PRT_IDX-1].WS_TL_ID = DDRUPT.TELLER;
            DDRUPT.PSBK_SEQ = DDRVCH.PSBK_SEQ;
            DDRUPT.PRT_FLAG = '1';
            DDRUPT.PRT_LINE = DDRVCH.PRT_LINE;
            DDRUPT.PB_BAL = DDRPBBL.PSBK_BAL;
            DDRUPT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRUPT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            CEP.TRC(SCCGWA, WS_PRT_DATA.WS_ARRY[WS_PRT_IDX-1]);
            T000_REWRITE_DDTUPT();
            if (pgmRtn) return;
            T000_READNEXT_DDTUPT();
            if (pgmRtn) return;
        }
        T000_ENDBR_DDTUPT();
        if (pgmRtn) return;
        WS_PRT_DATA.WS_CNT = WS_PRT_IDX;
        if (WS_PRT_IDX > 0) {
            DDRVCH.LAST_PB_CCY = DDRUPT.CCY;
            DDRVCH.LAST_PB_BAL = DDRPBBL.PSBK_BAL;
        }
        T000_REWRITE_DDTVCH();
        if (pgmRtn) return;
        WS_LINE = (short) (DDRVCH.PRT_LINE % DDCPBKS.DATA_TXT.PAGE_LINE);
        WS_PAGE = (short) ((DDRVCH.PRT_LINE - WS_LINE) / DDCPBKS.DATA_TXT.PAGE_LINE);
        if (WS_LINE == 0) {
            if (WS_PAGE > 0) {
                WS_PRT_DATA.WS_PSBK_ROW = DDCPBKS.DATA_TXT.PAGE_LINE;
            } else {
                WS_PRT_DATA.WS_PSBK_ROW = WS_LINE;
            }
            WS_PRT_DATA.WS_PSBK_PAGE = WS_PAGE;
        } else {
            WS_PAGE = (short) (WS_PAGE + 1);
            WS_PRT_DATA.WS_PSBK_ROW = WS_LINE;
            WS_PRT_DATA.WS_PSBK_PAGE = WS_PAGE;
        }
        CEP.TRC(SCCGWA, WS_PAGE);
        CEP.TRC(SCCGWA, WS_LINE);
        CEP.TRC(SCCGWA, WS_PRT_DATA.WS_PSBK_ROW);
        CEP.TRC(SCCGWA, WS_PRT_DATA.WS_PSBK_PAGE);
        if (WS_UPT_FLAG == 'N' 
            || WS_PRT_IDX >= WS_PSB_AVA_PRT_LINE) {
            WS_PRT_DATA.WS_END_FLG = 'Y';
        } else {
            WS_PRT_DATA.WS_END_FLG = 'N';
        }
        CEP.TRC(SCCGWA, WS_PRT_DATA);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_PRT_UPT_FMT;
        SCCFMT.DATA_PTR = WS_PRT_DATA;
        SCCFMT.DATA_LEN = 1513;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B098_UPD_MST_INF_PROC() throws IOException,SQLException,Exception {
        DDRMST.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.LAST_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRMST.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DDTMST();
        if (pgmRtn) return;
    }
    public void B170_NFIN_TXN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        if (CICQACRL.RC.RC_CODE == 0) {
            BPCPNHIS.INFO.AC = CICQACRL.DATA.REL_AC_NO;
            BPCPNHIS.INFO.TX_TOOL = CICQACRL.DATA.REL_AC_NO;
        } else {
            BPCPNHIS.INFO.AC = DDCSPBPT.AC;
            BPCPNHIS.INFO.TX_TOOL = DDCSPBPT.AC;
        }
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK_NAME;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.TX_TYP_CD = "P103";
        CEP.TRC(SCCGWA, BPCPNHIS);
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void T000_READ_DDTVCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRVCH);
        DDRVCH.KEY.CUS_AC = DDCSPBPT.AC;
        DDTVCH_RD = new DBParm();
        DDTVCH_RD.TableName = "DDTVCH";
        DDTVCH_RD.where = "CUS_AC = :DDRVCH.KEY.CUS_AC";
        DDTVCH_RD.upd = true;
        IBS.READ(SCCGWA, DDRVCH, this, DDTVCH_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, DDRVCH.UPT_CNT);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ACPB_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRVCH.UPT_CNT == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_UPT_CNT_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRVCH.PSBK_STS == 'U') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ULIST_PSBK);
        }
        if (DDRVCH.PSBK_STS == 'W') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_WLST_PSBK);
        }
    }
    public void R000_GET_PASSBOOK_PARM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, DDCPBKS);
        DDCPBKS.KEY.TYP = "PDD01";
        DDCPBKS.KEY.CD = "PASSBOOK";
        BPCPRMR.DAT_PTR = DDCPBKS;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCPBKS.DATA_TXT.MAX_PAGE);
        CEP.TRC(SCCGWA, DDCPBKS.DATA_TXT.PAGE_LINE);
        CEP.TRC(SCCGWA, DDCPBKS.DATA_TXT.MAX_LINE);
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_DDTPBBL() throws IOException,SQLException,Exception {
        DDTPBBL_RD = new DBParm();
        DDTPBBL_RD.TableName = "DDTPBBL";
        DDTPBBL_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DDRPBBL, DDTPBBL_RD);
    }
    public void T000_REWRITE_DDTPBBL() throws IOException,SQLException,Exception {
        DDRPBBL.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRPBBL.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTPBBL_RD = new DBParm();
        DDTPBBL_RD.TableName = "DDTPBBL";
        IBS.REWRITE(SCCGWA, DDRPBBL, DDTPBBL_RD);
    }
    public void T000_REWRITE_DDTUPT() throws IOException,SQLException,Exception {
        DDTUPT_RD = new DBParm();
        DDTUPT_RD.TableName = "DDTUPT";
        IBS.REWRITE(SCCGWA, DDRUPT, DDTUPT_RD);
    }
    public void T000_ENDBR_DDTUPT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTUPT_BR);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_UNI_CIZACCU, CICACCU);
    }
    public void S000_CALL_BPZFCSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-STS-TBL-AUTH", BPCFCSTS);
        if (BPCFCSTS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFCSTS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPD_DDTPBBL() throws IOException,SQLException,Exception {
        DDTPBBL_RD = new DBParm();
        DDTPBBL_RD.TableName = "DDTPBBL";
        DDTPBBL_RD.col = "PSBK_BAL";
        DDTPBBL_RD.upd = true;
        IBS.READ(SCCGWA, DDRPBBL, DDTPBBL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_PBBL_FLAG = 'Y';
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_PBBL_FLAG = 'N';
        }
    }
    public void T000_READ_DDTCCY_1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = DDRUPT.KEY.CUS_AC;
        CEP.TRC(SCCGWA, DDRVCH.VCH_TYPE);
        if (DDRVCH.VCH_TYPE == '2') {
            IBS.init(SCCGWA, CICQACRL);
            CICQACRL.DATA.AC_NO = DDRUPT.KEY.CUS_AC;
            CICQACRL.DATA.AC_REL = "12";
            CICQACRL.FUNC = '3';
            CICQACRL.FUNC = 'I';
            IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
            CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
            if (CICQACRL.RC.RC_CODE == 0) {
                DDRCCY.CUS_AC = CICQACRL.O_DATA.O_REL_AC_NO;
            } else {
            }
        }
        DDRCCY.CCY = DDRUPT.CCY;
        DDRCCY.CCY_TYPE = DDRUPT.CCY_TYPE;
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "CUS_AC = :DDRCCY.CUS_AC "
            + "AND CCY = :DDRCCY.CCY "
            + "AND CCY_TYPE = :DDRCCY.CCY_TYPE";
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND);
        }
    }
    public void T000_STARTBR_DDTUPT() throws IOException,SQLException,Exception {
        DDRUPT.KEY.CUS_AC = DDCSPBPT.AC;
        DDRUPT.PRT_FLAG = '0';
        DDTUPT_BR.rp = new DBParm();
        DDTUPT_BR.rp.TableName = "DDTUPT";
        DDTUPT_BR.rp.where = "CUS_AC = :DDRUPT.KEY.CUS_AC "
            + "AND PRT_FLAG = :DDRUPT.PRT_FLAG";
        DDTUPT_BR.rp.upd = true;
        DDTUPT_BR.rp.order = "UPT_NO";
        IBS.STARTBR(SCCGWA, DDRUPT, this, DDTUPT_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READ_DDTUPT() throws IOException,SQLException,Exception {
        DDTUPT_RD = new DBParm();
        DDTUPT_RD.TableName = "DDTUPT";
        DDTUPT_RD.where = "CUS_AC = :DDRUPT.KEY.CUS_AC";
        DDTUPT_RD.fst = true;
        DDTUPT_RD.order = "UPT_NO";
        IBS.READ(SCCGWA, DDRUPT, this, DDTUPT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_FLG = 'N';
        } else {
        }
    }
    public void T000_READNEXT_DDTUPT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRUPT, this, DDTUPT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_UPT_FLAG = 'Y';
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_UPT_FLAG = 'N';
        }
    }
    public void T000_READ_UPDATE_DDTMST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSPBPT.AC);
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.upd = true;
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRMST.AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.REWRITE(SCCGWA, DDRMST, DDTMST_RD);
    }
    public void T000_REWRITE_DDTVCH() throws IOException,SQLException,Exception {
        DDRVCH.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRVCH.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDTVCH_RD = new DBParm();
        DDTVCH_RD.TableName = "DDTVCH";
        IBS.REWRITE(SCCGWA, DDRVCH, DDTVCH_RD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
