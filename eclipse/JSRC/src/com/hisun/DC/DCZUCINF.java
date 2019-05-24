package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZUCINF {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm DCTCDDAT_RD;
    DBParm DCTCDORD_RD;
    boolean pgmRtn = false;
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_ERR_MSG = " ";
    char WS_CARD_FLG = ' ';
    char WS_TBL_FLAG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCBINF SCCBINF = new SCCBINF();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCRCDORD DCRCDORD = new DCRCDORD();
    CICCUST CICCUST = new CICCUST();
    BPCPRMR BPCPRMR = new BPCPRMR();
    DCRPRDPR DCRPRDPR = new DCRPRDPR();
    BPCPRMM BPCPRMM = new BPCPRMM();
    SCCSTAR SCCSTAR = new SCCSTAR();
    DCCSCSTS DCCSCSTS = new DCCSCSTS();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    DCRCPARM DCRCPARM = new DCRCPARM();
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCPLOSS BPCPLOSS = new BPCPLOSS();
    BPRLOSS BPRLOSS = new BPRLOSS();
    BPCSLOSS BPCSLOSS = new BPCSLOSS();
    BPCO1821_OPT_1821 BPCO1821_OPT_1821 = new BPCO1821_OPT_1821();
    DCCUPRCD DCCUPRCD = new DCCUPRCD();
    int WS_TR_DT = 0;
    int WS_CNT2 = 0;
    int WS_CNT_SCAD = 0;
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCUCINF DCCUCINF;
    public void MP(SCCGWA SCCGWA, DCCUCINF DCCUCINF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCUCINF = DCCUCINF;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZUCINF return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (WS_CARD_FLG == 'Y') {
            B011_CHECK_STSW();
            if (pgmRtn) return;
        }
        B020_GET_CARD_INFO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DCCUCINF.CARD_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NO_MISSING, DCCUCINF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCUCINF.CARD_NO;
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCRCDDAT.JOIN_CUS_FLG);
    }
    public void B011_CHECK_STSW() throws IOException,SQLException,Exception {
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(0, 1).equalsIgnoreCase("1")) {
            IBS.init(SCCGWA, DCRCPARM);
            IBS.init(SCCGWA, BPCPRMR);
            DCRCPARM.KEY.TYP = "DCPRM";
            BPCPRMR.FUNC = ' ';
            DCRCPARM.KEY.CD = SCCGWA.COMM_AREA.TR_BANK;
            IBS.CPY2CLS(SCCGWA, DCRCPARM.KEY.CD, DCRCPARM.KEY.KEY1);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_BANK);
            BPCPRMR.DAT_PTR = DCRCPARM;
            S000_CALL_BPZPRMR();
            if (pgmRtn) return;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(DCCMSG_ERROR_MSG.NOT_FOUND)) {
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.NOT_FOUND, DCCSCSTS.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, BPCPLOSS);
            BPCPLOSS.INFO.FUNC = 'I';
            BPCPLOSS.INFO.INDEX_FLG = "2";
            CEP.TRC(SCCGWA, DCCUCINF.CARD_NO);
            BPCPLOSS.DATA_INFO.AC = DCCUCINF.CARD_NO;
            BPCPLOSS.DATA_INFO.STS = '2';
            S000_CALL_BPZPLOSS();
            if (pgmRtn) return;
            if (WS_TBL_FLAG == 'N') {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
                IBS.init(SCCGWA, DCRCDDAT);
                DCRCDDAT.KEY.CARD_NO = DCCUCINF.CARD_NO;
                T000_READUPD_DCTCDDAT();
                if (pgmRtn) return;
                if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
                JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
                for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
                DCRCDDAT.CARD_STSW = "0" + DCRCDDAT.CARD_STSW.substring(1);
                DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                T000_REWRITE_DCTCDDAT();
                if (pgmRtn) return;
                IBS.init(SCCGWA, DCCSCSTS);
                DCCSCSTS.CARD_NO = DCCUCINF.CARD_NO;
                R000_UPDATE_STS_SPCDEAL();
                if (pgmRtn) return;
    } //FROM #ENDIF
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
    } //FROM #ENDIF
            } else {
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = BPCPLOSS.DATA_INFO.LOS_DT;
                SCCCLDT.DATE2 = SCCGWA.COMM_AREA.AC_DATE;
                CEP.TRC(SCCGWA, BPCPLOSS.DATA_INFO.LOS_DT);
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
                SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
                SCSSCLDT1.MP(SCCGWA, SCCCLDT);
                if (SCCCLDT.RC != 0) {
                    IBS.init(SCCGWA, SCCBINF);
                    SCCBINF.ERR_TYPE = 'P';
                    SCCBINF.ERR_ACTION = 'E';
                    SCCBINF.ERR_NAME = "SCSSCLDT";
                    WS_ERR_MSG = "" + SCCCLDT.RC;
                    JIBS_tmp_int = WS_ERR_MSG.length();
                    for (int i=0;i<4-JIBS_tmp_int;i++) WS_ERR_MSG = "0" + WS_ERR_MSG;
                    SCCBINF.OTHER_INFO = "CALL SCSSCLDT ERROR!";
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, SCCCLDT.DAYS);
                CEP.TRC(SCCGWA, DCRCPARM.DATA_TXT.LOS_TMP_DAYS);
                if (SCCCLDT.DAYS >= DCRCPARM.DATA_TXT.LOS_TMP_DAYS) {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
                    IBS.init(SCCGWA, DCCSCSTS);
                    DCCSCSTS.CARD_NO = DCCUCINF.CARD_NO;
                    R000_UPDATE_STS_SPCDEAL();
                    if (pgmRtn) return;
    } //FROM #ENDIF
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
    } //FROM #ENDIF
                }
            }
        }
    }
    public void B020_GET_CARD_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCUCINF.CARD_NO);
        IBS.init(SCCGWA, DCRCDDAT);
        IBS.init(SCCGWA, DCCUCINF.RC);
        DCRCDDAT.KEY.CARD_NO = DCCUCINF.CARD_NO;
        CEP.TRC(SCCGWA, DCRCDDAT.KEY.CARD_NO);
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCRCDDAT.JOIN_CUS_FLG);
        CEP.TRC(SCCGWA, DCRCDDAT.CARD_OWN_CINO);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST, DCCUCINF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        DCCUCINF.AC_TYP = DCRCDDAT.ACNO_TYPE;
        DCCUCINF.EXC_CARD_TMS = DCRCDDAT.EXC_CARD_TMS;
        R000_GET_NEW_CARD_STSW();
        if (pgmRtn) return;
        DCCUCINF.CARD_LNK_TYP = DCRCDDAT.CARD_LNK_TYP;
        DCCUCINF.PROD_CD = DCRCDDAT.PROD_CD;
        DCCUCINF.CLS_PROD_CD = DCRCDDAT.CARD_CLS_PROD;
        DCCUCINF.BV_CD_NO = DCRCDDAT.BV_CD_NO;
        DCCUCINF.CARD_OWN_CINO = DCRCDDAT.CARD_OWN_CINO;
        DCCUCINF.CARD_HLDR_CINO = DCRCDDAT.CARD_HLDR_CINO;
        DCCUCINF.CARD_MEDI = DCRCDDAT.CARD_MEDI;
        DCCUCINF.CARD_STS = DCRCDDAT.CARD_STS;
        DCCUCINF.CARD_STSW = DCRCDDAT.CARD_STSW;
        DCCUCINF.CARD_TYP = DCRCDDAT.CARD_TYP;
        DCCUCINF.DOUBLE_FREE_FLG = DCRCDDAT.DOUBLE_FREE_FLG;
        DCCUCINF.ANNUAL_FEE_FREE = DCRCDDAT.ANNUAL_FEE_FREE;
        DCCUCINF.TRAN_WITH_CARD = DCRCDDAT.TRAN_WITH_CARD;
        DCCUCINF.TRAN_NO_CARD = DCRCDDAT.TRAN_NO_CARD;
        DCCUCINF.TRAN_CRS_BOR = DCRCDDAT.TRAN_CRS_BOR;
        DCCUCINF.TRAN_ATM_FLG = DCRCDDAT.TRAN_ATM_FLG;
        if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
        JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
        if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
        JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
        if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
        JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
        if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
        JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
        if (DCRCDDAT.CARD_TYP.substring(0, 1).equalsIgnoreCase("1") 
            || DCRCDDAT.CARD_TYP.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
            || DCRCDDAT.CARD_TYP.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
            || DCRCDDAT.CARD_TYP.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("1")) {
            DCCUCINF.ANNUAL_FEE_FREE = 'Y';
        }
        DCCUCINF.PVK_TYP = DCRCDDAT.PVK_TYP;
        DCCUCINF.CVV_FLG = DCRCDDAT.CVV_FLG;
        DCCUCINF.ISSU_BR = DCRCDDAT.ISSU_BR;
        DCCUCINF.CLT_BR = DCRCDDAT.CLT_BR;
        DCCUCINF.EMBS_DT = DCRCDDAT.EMBS_DT;
        DCCUCINF.ISSU_DT = DCRCDDAT.ISSU_DT;
        DCCUCINF.EXP_DT = DCRCDDAT.EXP_DT;
        DCCUCINF.CLO_DT = DCRCDDAT.CLO_DT;
        DCCUCINF.ANU_FEE_NXT = DCRCDDAT.ANU_FEE_NXT;
        DCCUCINF.ANU_FEE_PCT = DCRCDDAT.ANU_FEE_PCT;
        DCCUCINF.ANU_FEE_FREE = DCRCDDAT.ANU_FEE_FREE;
        if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
        JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
        if (DCRCDDAT.CARD_TYP.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("1")) {
            DCCUCINF.ANU_FEE_FREE = 1;
        }
        DCCUCINF.ANU_FEE_ARG = DCRCDDAT.ANU_FEE_ARG;
        DCCUCINF.PIN_LCK_DT = DCRCDDAT.PIN_LCK_DT;
        DCCUCINF.CVV_LCK_DT = DCRCDDAT.CVV_LCK_DT;
        DCCUCINF.LAST_TXN_DT = DCRCDDAT.LAST_TXN_DT;
        DCCUCINF.UPD_DT = DCRCDDAT.UPDTBL_DATE;
        DCCUCINF.UPD_TLR = DCRCDDAT.UPDTBL_TLR;
        DCCUCINF.SAME_NAME_TFR_FLG = DCRCDDAT.SAME_NAME_TFR_FLG;
        DCCUCINF.DIFF_NAME_TFR_FLG = DCRCDDAT.DIFF_NAME_TFR_FLG;
        DCCUCINF.DRAW_OVER_FLG = DCRCDDAT.DRAW_OVER_FLG;
        DCCUCINF.MOVE_FLG = DCRCDDAT.MOVE_FLG;
        DCCUCINF.ACT_DT = DCRCDDAT.ACT_DT;
        DCCUCINF.OPEN_CHNL = DCRCDDAT.OPEN_CHNL;
        DCCUCINF.E_CARD_NO = DCRCDDAT.E_CARD_NO;
        CEP.TRC(SCCGWA, DCRCDDAT.OPEN_CHNL);
        CEP.TRC(SCCGWA, DCCUCINF.OPEN_CHNL);
        if (DCRCDDAT.CARD_OWN_CINO.trim().length() > 0) {
            IBS.init(SCCGWA, CICCUST);
            CICCUST.FUNC = 'C';
            CICCUST.DATA.CI_NO = DCRCDDAT.CARD_OWN_CINO;
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
        }
        DCCUCINF.CARD_OWN_IDTYP = CICCUST.O_DATA.O_ID_TYPE;
        DCCUCINF.CARD_OWN_IDNO = CICCUST.O_DATA.O_ID_NO;
        DCCUCINF.CARD_OWN_ENM = CICCUST.O_DATA.O_CI_ENM;
        DCCUCINF.CARD_OWN_CNM = CICCUST.O_DATA.O_CI_NM;
        CEP.TRC(SCCGWA, DCCUCINF.CARD_OWN_ENM);
        CEP.TRC(SCCGWA, DCCUCINF.CARD_OWN_CNM);
        if (DCRCDDAT.CARD_HLDR_CINO.trim().length() > 0) {
            if (!DCRCDDAT.CARD_HLDR_CINO.equalsIgnoreCase(DCRCDDAT.CARD_OWN_CINO)) {
                IBS.init(SCCGWA, CICCUST);
                CICCUST.FUNC = 'C';
                CICCUST.DATA.CI_NO = DCRCDDAT.CARD_HLDR_CINO;
                S000_CALL_CIZCUST();
                if (pgmRtn) return;
            }
            DCCUCINF.CARD_HLDR_IDTYP = CICCUST.O_DATA.O_ID_TYPE;
            DCCUCINF.CARD_HLDR_IDNO = CICCUST.O_DATA.O_ID_NO;
            DCCUCINF.CARD_HLDR_ENM = CICCUST.O_DATA.O_CI_ENM;
            DCCUCINF.CARD_HLDR_CNM = CICCUST.O_DATA.O_CI_NM;
            DCCUCINF.CARD_TEL_NO = CICCUST.O_DATA.O_TEL_NO;
            CEP.TRC(SCCGWA, DCCUCINF.CARD_HLDR_ENM);
            CEP.TRC(SCCGWA, DCCUCINF.CARD_HLDR_CNM);
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(0, 1).equalsIgnoreCase("1") 
            || DCRCDDAT.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
            || DCRCDDAT.CARD_STSW.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1")) {
            DCCUCINF.CRD_LST_SW_FLG = 'Y';
        } else {
            DCCUCINF.CRD_LST_SW_FLG = 'N';
        }
        DCCUCINF.PRIM_CARD_NO = DCRCDDAT.PRIM_CARD_NO;
        if (DCRCDDAT.HOLD_AC_FLG == 'Y') {
            DCCUCINF.HOLD_AC_FLG = DCRCDDAT.HOLD_AC_FLG;
        } else {
            DCCUCINF.HOLD_AC_FLG = 'N';
        }
        DCCUCINF.PROD_LMT_FLG = DCRCDDAT.PROD_LMT_FLG;
        DCCUCINF.SAME_NAME_TFR_FLG = DCRCDDAT.SAME_NAME_TFR_FLG;
        DCCUCINF.DIFF_NAME_TFR_FLG = DCRCDDAT.DIFF_NAME_TFR_FLG;
        DCCUCINF.DRAW_OVER_FLG = DCRCDDAT.DRAW_OVER_FLG;
        DCCUCINF.ISSU_NAME = " ";
        IBS.init(SCCGWA, BPCPQPRD);
        if (WS_CARD_FLG == 'Y') {
            BPCPQPRD.PRDT_CODE = DCRCDDAT.PROD_CD;
        } else {
            BPCPQPRD.PRDT_CODE = DCRCDORD.CARD_PROD;
        }
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
        if (BPCPQPRD.PARM_CODE.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PQPRD_PARM_CD_SPC;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_PQPRD_PARM_CD_SPC, DCCUCINF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCRPRDPR);
        IBS.init(SCCGWA, DCCUPRCD);
        DCCUPRCD.TX_TYPE = 'I';
        DCCUPRCD.DATA.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        DCCUPRCD.DATA.VAL.PRDMO_CD = "CARD";
        DCCUPRCD.DATA.KEY.CD.PARM_CODE = BPCPQPRD.PARM_CODE;
        DCCUPRCD.DATE.EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
        S000_CALL_DCZUPRCD();
        if (pgmRtn) return;
        if (DCCUPRCD.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DCCUPRCD.RC);
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUPRCD.DATA);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCRPRDPR);
        }
        CEP.TRC(SCCGWA, DCRPRDPR);
        DCCUCINF.DFT_EXP = DCRPRDPR.DATA_TXT.DFT_EXP;
        DCCUCINF.ADSC_TYPE = DCRPRDPR.DATA_TXT.ADSC_TYP;
        DCCUCINF.SVR_RSC_CD = DCRPRDPR.DATA_TXT.SR_RC_CD;
        DCCUCINF.DD_PROD_CD = DCRPRDPR.DATA_TXT.DD_PROD;
        DCCUCINF.CARD_PHY_TYP = DCRPRDPR.DATA_TXT.PHY_TYP;
        if (DCCUCINF.ADSC_TYPE == 'P') {
            DCCUCINF.AC_TYPE = 'W';
        } else {
            DCCUCINF.AC_TYPE = 'V';
        }
        CEP.TRC(SCCGWA, DCRCDDAT.JOIN_CUS_FLG);
        CEP.TRC(SCCGWA, DCRPRDPR.DATA_TXT.JOIN_CUS);
        if (WS_CARD_FLG == 'Y') {
            if (DCRCDDAT.JOIN_CUS_FLG == 'Y') {
                DCCUCINF.JOIN_CUS_FLG = 'Y';
            } else {
                DCCUCINF.JOIN_CUS_FLG = 'N';
            }
        } else {
            DCCUCINF.JOIN_CUS_FLG = DCRPRDPR.DATA_TXT.JOIN_CUS;
        }
        DCCUCINF.AC_HANG_FLG = DCRPRDPR.DATA_TXT.AC_HANG;
        DCCUCINF.MOBL_PAY_FLG = DCRPRDPR.DATA_TXT.MOBL_PAY;
        DCCUCINF.SUP_CARD_FLG = DCRPRDPR.DATA_TXT.SUP_CARD;
        DCCUCINF.PSW_FLG = DCRPRDPR.DATA_TXT.PSW_FLG;
        DCCUCINF.FACE_FLG = DCRPRDPR.DATA_TXT.FACE_FLG;
        CEP.TRC(SCCGWA, DCRPRDPR.KEY.CD.KEY1.PROD_CD);
        if (DCRPRDPR.KEY.CD.KEY1.PROD_CD.equalsIgnoreCase("CARD000013")) {
            DCCUCINF.CARD_PROD_FLG = 'Y';
        } else {
            if (DCRPRDPR.KEY.CD.KEY1.PROD_CD.equalsIgnoreCase("CARD000015")) {
                DCCUCINF.CARD_PROD_FLG = 'S';
            } else {
                DCCUCINF.CARD_PROD_FLG = 'P';
            }
        }
    }
    public void R000_GET_NEW_CARD_STSW() throws IOException,SQLException,Exception {
    }
    public void R000_UPDATE_STS_SPCDEAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-STS", DCCSCSTS);
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        WS_CARD_FLG = 'N';
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CARD_FLG = 'Y';
        }
        if (WS_CARD_FLG == 'N') {
            IBS.init(SCCGWA, DCRCDORD);
            DCRCDORD.KEY.CARD_NO = DCCUCINF.CARD_NO;
            CEP.TRC(SCCGWA, DCRCDORD.KEY.CARD_NO);
            DCTCDORD_RD = new DBParm();
            DCTCDORD_RD.TableName = "DCTCDORD";
            DCTCDORD_RD.where = "CARD_NO = :DCRCDORD.KEY.CARD_NO "
                + "AND EXC_CARD_TMS < 99";
            DCTCDORD_RD.fst = true;
            DCTCDORD_RD.order = "EXC_CARD_TMS DESC";
            IBS.READ(SCCGWA, DCRCDORD, this, DCTCDORD_RD);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST, DCCUCINF.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            DCRCDDAT.PROD_CD = DCRCDORD.CARD_PROD;
            DCRCDDAT.CARD_CLS_PROD = DCRCDORD.CARD_CLS_CD;
            DCRCDDAT.BV_CD_NO = DCRCDORD.BV_CD_NO;
            DCRCDDAT.EXC_CARD_TMS = DCRCDORD.KEY.EXC_CARD_TMS;
            DCRCDDAT.ISSU_BR = DCRCDORD.APP_BR;
            DCRCDDAT.CARD_STS = DCRCDORD.CRT_STS;
        }
    }
    public void T000_READUPD_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.upd = true;
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.REWRITE(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUCINF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CISOCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUCINF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ        ", BPCPRMR);
        CEP.TRC(SCCGWA, BPCPRMR.RC);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUCINF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUPRCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-MPRD", DCCUPRCD);
        CEP.TRC(SCCGWA, DCCUPRCD.RC);
    }
    public void S000_CALL_BPZPLOSS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-LOSS-INFO", BPCPLOSS, true);
        CEP.TRC(SCCGWA, BPCPLOSS.RC);
        if (BPCPLOSS.RC.RC_CODE == 0) {
            WS_TBL_FLAG = 'Y';
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPLOSS.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase("BP1993")) {
            WS_TBL_FLAG = 'N';
        }
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCPLOSS.RC);
        if (BPCPLOSS.RC.RC_CODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase("BP1993")) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPLOSS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, SCCBINF);
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
