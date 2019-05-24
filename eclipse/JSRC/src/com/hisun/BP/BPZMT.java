package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZMT {
    int JIBS_tmp_int;
    brParm BPTTQP_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTTQP_RD;
    DBParm BPTTQPH_RD;
    String CPN_INQ_PUB_PARM = "BP-PARM-READ      ";
    String CPN_INQ_PUB_CODE = "BP-P-INQ-PC       ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_CHK_UPD_AUTH = "BP-CHK-EXR-UPD-AUTH";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG      ";
    String CPN_R_TQP = "BP-R-TQP-M       ";
    String CPN_R_TQP_B = "BP-R-TQP-B        ";
    String CPN_R_TQPH_B = "BP-R-TQPH-B      ";
    String CPN_R_TQPH = "BP-R-TQPH-M      ";
    String CPN_R_EXRD = "BP-R-EXRD-B       ";
    String CPN_BP_GEN_TQP = "BP-GEN-TQP        ";
    String CPN_R_EXRM = "BP-R-EXRD-M       ";
    String CPN_R_ERGR_B = "BP-R-ERGR-B       ";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String K_FWD_TENOR = "FWDT ";
    int K_MAX_CNT = 20;
    int K_MAX_DATE = 99991231;
    int K_MAX_TIME = 235959;
    int K_BR = 999999;
    short WS_I = 0;
    int WS_BR = 0;
    String WK_CCY = " ";
    String WS_CCY = " ";
    String WS_LOCAL_CCY = " ";
    String WS_FWD_TENOR = " ";
    double WS_QTP_NEW = 0;
    double WS_QTP_OLD = 0;
    BPZMT_WS_DEC_POINT WS_DEC_POINT = new BPZMT_WS_DEC_POINT();
    BPZMT_WS_DATE_AND_TIME WS_DATE_AND_TIME = new BPZMT_WS_DATE_AND_TIME();
    char WS_FIND_LAST_FLAG = ' ';
    BPZMT_WS_EX_RATES WS_EX_RATES = new BPZMT_WS_EX_RATES();
    short WS_READ_CNT = 0;
    String WS_ERR_MSG = " ";
    BPZMT_WS_AUTH_LVL WS_AUTH_LVL = new BPZMT_WS_AUTH_LVL();
    BPZMT_WS_TEMP_LVL WS_TEMP_LVL = new BPZMT_WS_TEMP_LVL();
    char WS_NOTFND_FLAG = ' ';
    BPZMT_WS_OUT_DATA WS_OUT_DATA = new BPZMT_WS_OUT_DATA();
    int WS_STR_DATE = 0;
    short WS_K = 0;
    short WS_T = 0;
    String WS_MT_CCY = " ";
    double WS_MT_CS_BUY = 0;
    double WS_MT_FX_BUY = 0;
    double WS_MT_FX_SELL = 0;
    double WS_MT_CS_SELL = 0;
    double WS_MT_MID_RAT = 0;
    char WS_BPTTQP_FLG = ' ';
    char WS_LOOP_FLG = ' ';
    String WS_OLD_DATA = " ";
    String WS_NEW_DATA = " ";
    int WS_LEN = 0;
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPREXRT BPREXRT = new BPREXRT();
    BPCQCCY BPCQCCY = new BPCQCCY();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPCREXRS BPCREXRS = new BPCREXRS();
    BPCTTQPM BPCTTQPM = new BPCTTQPM();
    BPCRTQPH BPCRTQPH = new BPCRTQPH();
    BPCRTQPS BPCRTQPS = new BPCRTQPS();
    BPCGTQP BPCGTQP = new BPCGTQP();
    BPCTQPHM BPCTQPHM = new BPCTQPHM();
    BPCCEUA BPCCEUA = new BPCCEUA();
    BPCTEXRM BPCTEXRM = new BPCTEXRM();
    BPCRERGR BPCRERGR = new BPCRERGR();
    BPCOIEC BPCOIEC = new BPCOIEC();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPRTQP BPRTQP = new BPRTQP();
    BPRTQPH BPRTQPH = new BPRTQPH();
    BPRTQPH BPRTQPHO = new BPRTQPH();
    BPREXRD BPREXRD = new BPREXRD();
    BPRERGR BPRERGR = new BPRERGR();
    int WS_LAST_MONTH = 0;
    int WS_OLD_EXP_DT = 0;
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCMT BPCMT;
    public void MP(SCCGWA SCCGWA, BPCMT BPCMT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCMT = BPCMT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZMT return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCPNHIS);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "OK");
        B001_CHECK_INPUT();
        if (BPCMT.DATA.FUNC == 'A') {
            B003_ADD_PROC();
        } else if (BPCMT.DATA.FUNC == 'B') {
            B006_BRW_PROC();
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_FUNC;
            S000_ERR_MSG_PROC();
        }
    }
    public void B001_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCMT.DATA.FUNC);
        CEP.TRC(SCCGWA, BPREXRT.DAT_TXT.FWD_IND);
        if ((BPCMT.DATA.FUNC != 'A' 
            && BPCMT.DATA.FUNC != 'B')) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_ERR_FUNC);
        }
        if (BPCMT.DATA.BR != 0 
            && BPCMT.DATA.BR != K_BR) {
            WS_BR = BPCMT.DATA.BR;
            R000_CHECK_BRANCH();
        }
        CEP.TRC(SCCGWA, BPCMT.DATA.EXR_TYPE);
        IBS.init(SCCGWA, BPREXRT);
        IBS.init(SCCGWA, BPCPRMR);
        BPREXRT.KEY.TYP = "EXRT";
        BPREXRT.KEY.CD = BPCMT.DATA.EXR_TYPE;
        BPCPRMR.DAT_PTR = BPREXRT;
        S000_CALL_BPZPRMR();
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INVALID_EXR_TYP);
        }
        WS_LOCAL_CCY = BPREXRT.DAT_TXT.BASE_CCY;
        if (BPCMT.DATA.CCY_INFO[1-1].CCY.trim().length() > 0) {
            WS_CCY = BPCMT.DATA.CCY_INFO[1-1].CCY;
            CEP.TRC(SCCGWA, WS_CCY);
            R000_CHECK_CCY();
        }
        if (BPREXRT.DAT_TXT.FWD_IND == '1' 
            && BPCMT.DATA.CCY_INFO[1-1].FWD_TENOR.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_IN_FWD_TENOR;
            S000_ERR_MSG_PROC();
        }
        if (BPREXRT.DAT_TXT.FWD_IND == '0' 
            && BPCMT.DATA.CCY_INFO[1-1].FWD_TENOR.trim().length() > 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NONEED_FWD_TENOR;
            S000_ERR_MSG_PROC();
        }
        if (BPCMT.DATA.CCY_INFO[1-1].FWD_TENOR.trim().length() > 0) {
            CEP.TRC(SCCGWA, WS_I);
            WS_FWD_TENOR = BPCMT.DATA.CCY_INFO[1-1].FWD_TENOR;
            CEP.TRC(SCCGWA, WS_FWD_TENOR);
            R000_CHECK_FWD_TENOR();
        }
        if (BPCMT.DATA.FUNC == 'A') {
            B001_CHECK_ADD();
        } else if (BPCMT.DATA.FUNC == 'B') {
            B001_CHECK_BRW();
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_FUNC;
            S000_ERR_MSG_PROC();
        }
    }
    public void B001_CHECK_BRW() throws IOException,SQLException,Exception {
        if (BPCMT.DATA.CONT_FLAG != ' ' 
            && BPCMT.DATA.CONT_FLAG != 'Y') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CONT_FLG_BE_Y;
            S000_ERR_MSG_PROC();
        }
    }
    public void B001_CHECK_ADD() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= K_MAX_CNT; WS_I += 1) {
            if (BPCMT.DATA.CCY_INFO[WS_I-1].CCY.trim().length() > 0) {
                WS_CCY = BPCMT.DATA.CCY_INFO[WS_I-1].CCY;
                R000_CHECK_CCY();
                if (BPREXRT.DAT_TXT.FWD_IND == '1' 
                    && BPCMT.DATA.CCY_INFO[WS_I-1].FWD_TENOR.trim().length() == 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_IN_FWD_TENOR;
                    S000_ERR_MSG_PROC();
                }
                if (BPREXRT.DAT_TXT.FWD_IND == '0' 
                    && BPCMT.DATA.CCY_INFO[WS_I-1].FWD_TENOR.trim().length() > 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NONEED_FWD_TENOR;
                    S000_ERR_MSG_PROC();
                }
                if (BPCMT.DATA.CCY_INFO[WS_I-1].FWD_TENOR.trim().length() > 0) {
                    CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[WS_I-1].FWD_TENOR);
                    WS_FWD_TENOR = BPCMT.DATA.CCY_INFO[WS_I-1].FWD_TENOR;
                    R000_CHECK_FWD_TENOR();
                }
                CEP.TRC(SCCGWA, BPCMT.DATA.EXR_TYPE);
                CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[WS_I-1].CCY);
                CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[WS_I-1].FX_BUY);
                CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[WS_I-1].FX_SELL);
                CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[WS_I-1].MID_RAT);
                if (BPCMT.DATA.CCY_INFO[WS_I-1].FX_BUY == 0 
                    && BPCMT.DATA.CCY_INFO[WS_I-1].FX_SELL == 0 
                    && BPCMT.DATA.CCY_INFO[WS_I-1].MID_RAT == 0) {
                    CEP.TRC(SCCGWA, "================");
                    CEP.TRC(SCCGWA, BPCMT.DATA.EXR_TYPE);
                    CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[WS_I-1].CCY);
                    CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[WS_I-1].CORR_CCY);
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INP_RATE;
                    S000_ERR_MSG_PROC();
                }
                if (BPCMT.DATA.CCY_INFO[WS_I-1].CS_BUY == 0) {
                    BPCMT.DATA.CCY_INFO[WS_I-1].CS_BUY = BPCMT.DATA.CCY_INFO[WS_I-1].FX_BUY;
                }
                if (BPCMT.DATA.CCY_INFO[WS_I-1].CS_SELL == 0) {
                    BPCMT.DATA.CCY_INFO[WS_I-1].CS_SELL = BPCMT.DATA.CCY_INFO[WS_I-1].FX_SELL;
                }
                if (BPCMT.DATA.CCY_INFO[WS_I-1].CS_BUY > BPCMT.DATA.CCY_INFO[WS_I-1].FX_BUY) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CS_GT_FX_RATE;
                    S000_ERR_MSG_PROC();
                }
            }
        }
        if (BPCMT.DATA.EFF_DT == 0) {
            BPCMT.DATA.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (BPCMT.DATA.EFF_TM == 0) {
            BPCMT.DATA.EFF_TM = SCCGWA.COMM_AREA.TR_TIME;
        }
        if (BPCMT.DATA.IPT_DT == 0) {
            BPCMT.DATA.IPT_DT = SCCGWA.COMM_AREA.TR_DATE;
        }
        if (BPCMT.DATA.IPT_TM == 0) {
            BPCMT.DATA.IPT_TM = SCCGWA.COMM_AREA.TR_TIME;
        }
        if (BPCMT.DATA.EFF_DT < SCCGWA.COMM_AREA.AC_DATE 
            || (BPCMT.DATA.EFF_DT == SCCGWA.COMM_AREA.AC_DATE 
            && BPCMT.DATA.EFF_TM < SCCGWA.COMM_AREA.TR_TIME)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EFFDT_LT_ACDT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B003_ADD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCMT.DATA.EFF_DT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, BPCMT.DATA.EFF_TM);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_TIME);
        if (BPCMT.DATA.EFF_DT > SCCGWA.COMM_AREA.AC_DATE 
            || (BPCMT.DATA.EFF_DT == SCCGWA.COMM_AREA.AC_DATE 
            && BPCMT.DATA.EFF_TM > SCCGWA.COMM_AREA.TR_TIME)) {
            B003_01_ADD_PROC();
        } else {
            B003_01_ADD_PROC();
            B003_02_DEL_PROC();
        }
        B008_WRITE_BPTPNHIS();
    }
    public void B003_01_ADD_PROC() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= K_MAX_CNT; WS_I += 1) {
            CEP.TRC(SCCGWA, "=====================");
            if (BPCMT.DATA.CCY_INFO[WS_I-1].CCY.trim().length() > 0) {
                CEP.TRC(SCCGWA, "*************************");
                CEP.TRC(SCCGWA, WS_I);
                CEP.TRC(SCCGWA, BPCMT.DATA.EFF_DT);
                CEP.TRC(SCCGWA, BPCMT.DATA.EFF_TM);
                CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[WS_I-1].CCY);
                R000_GET_POINT_PROCESS();
                IBS.init(SCCGWA, BPRTQP);
                WS_DATE_AND_TIME.WS_LAST_EXP_DT = 0;
                WS_DATE_AND_TIME.WS_LAST_EXP_TM = 0;
                WS_OLD_EXP_DT = 0;
                BPRTQP.KEY.EXR_TYP = BPREXRD.KEY.EXR_TYP;
                BPRTQP.KEY.EFF_TM = K_MAX_TIME;
                BPRTQP.KEY.CCY = BPCMT.DATA.CCY_INFO[WS_I-1].CCY;
                BPRTQP.KEY.BR = BPCMT.DATA.BR;
                T000_READFIRST_BPTTQP();
                WS_DATE_AND_TIME.WS_LAST_EXP_DT = BPRTQP.KEY.EFF_DT;
                WS_DATE_AND_TIME.WS_LAST_EXP_TM = BPRTQP.KEY.EFF_TM;
                WS_OLD_EXP_DT = K_MAX_DATE;
                CEP.TRC(SCCGWA, "READ FIRST RESULT");
                if (WS_DATE_AND_TIME.WS_LAST_EXP_DT == 0) {
                    WS_DATE_AND_TIME.WS_LAST_EXP_DT = K_MAX_DATE;
                    WS_DATE_AND_TIME.WS_LAST_EXP_TM = K_MAX_TIME;
                }
                CEP.TRC(SCCGWA, WS_DATE_AND_TIME.WS_LAST_EXP_DT);
                CEP.TRC(SCCGWA, WS_DATE_AND_TIME.WS_LAST_EXP_TM);
                CEP.TRC(SCCGWA, WS_OLD_EXP_DT);
                IBS.init(SCCGWA, BPRTQP);
                IBS.init(SCCGWA, BPCTTQPM);
                BPRTQP.KEY.EXR_TYP = BPCMT.DATA.EXR_TYPE;
                BPRTQP.KEY.BR = BPCMT.DATA.BR;
                BPRTQP.KEY.CCY = BPCMT.DATA.CCY_INFO[WS_I-1].CCY;
                BPRTQP.KEY.CORR_CCY = BPCMT.DATA.CCY_INFO[WS_I-1].CORR_CCY;
                BPRTQP.KEY.EFF_DT = BPCMT.DATA.EFF_DT;
                BPRTQP.KEY.EFF_TM = BPCMT.DATA.EFF_TM;
                BPRTQP.LOC_MID = BPCMT.DATA.CCY_INFO[WS_I-1].MID_RAT;
                BPRTQP.FOR_MID = BPCMT.DATA.CCY_INFO[WS_I-1].RECENT_MID_RAT;
                BPRTQP.CS_BUY = BPCMT.DATA.CCY_INFO[WS_I-1].CS_BUY;
                BPRTQP.CS_SELL = BPCMT.DATA.CCY_INFO[WS_I-1].CS_SELL;
                BPRTQP.FX_BUY = BPCMT.DATA.CCY_INFO[WS_I-1].FX_BUY;
                BPRTQP.FX_SELL = BPCMT.DATA.CCY_INFO[WS_I-1].FX_SELL;
                BPRTQP.CCS_BUY = BPCMT.DATA.CCY_INFO[WS_I-1].CCS_BUY;
                BPRTQP.CCS_SELL = BPCMT.DATA.CCY_INFO[WS_I-1].CCS_SELL;
                BPRTQP.CFX_BUY = BPCMT.DATA.CCY_INFO[WS_I-1].CFX_BUY;
                BPRTQP.CFX_SELL = BPCMT.DATA.CCY_INFO[WS_I-1].CFX_SELL;
                CEP.TRC(SCCGWA, "DEVHZ643");
                CEP.TRC(SCCGWA, BPRTQP.CCS_BUY);
                CEP.TRC(SCCGWA, BPRTQP.CCS_SELL);
                CEP.TRC(SCCGWA, BPRTQP.CFX_BUY);
                CEP.TRC(SCCGWA, BPRTQP.CFX_SELL);
                BPRTQP.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                BPRTQP.UPD_TM = SCCGWA.COMM_AREA.TR_TIME;
                BPRTQP.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPRTQP.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                BPCPNHIS.INFO.TX_TYP = 'A';
                WS_LEN = 401;
                BPCPNHIS.INFO.FMT_ID_LEN = WS_LEN;
                if (WS_NEW_DATA == null) WS_NEW_DATA = "";
                JIBS_tmp_int = WS_NEW_DATA.length();
                for (int i=0;i<5000-JIBS_tmp_int;i++) WS_NEW_DATA += " ";
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRTQP);
                WS_NEW_DATA = JIBS_tmp_str[0] + WS_NEW_DATA.substring(WS_LEN);
                BPCTTQPM.INFO.FUNC = 'C';
                S000_CALL_BPZTTQPM();
                if (BPCTTQPM.RETURN_INFO == 'D') {
                    IBS.init(SCCGWA, BPRTQP);
                    IBS.init(SCCGWA, BPCTTQPM);
                    BPRTQP.KEY.EXR_TYP = BPCMT.DATA.EXR_TYPE;
                    BPRTQP.KEY.BR = BPCMT.DATA.BR;
                    BPRTQP.KEY.CCY = BPCMT.DATA.CCY_INFO[WS_I-1].CCY;
                    BPRTQP.KEY.CORR_CCY = BPCMT.DATA.CCY_INFO[WS_I-1].CORR_CCY;
                    BPRTQP.KEY.EFF_DT = BPCMT.DATA.EFF_DT;
                    BPRTQP.KEY.EFF_TM = BPCMT.DATA.EFF_TM;
                    BPCTTQPM.INFO.FUNC = 'R';
                    BPCTTQPM.QUERY_OPTION.DBL_CHK_FLG = '1';
                    S000_CALL_BPZTTQPM();
                    R000_GET_POINT_PROCESS();
                    BPRTQP.LOC_MID = BPCMT.DATA.CCY_INFO[WS_I-1].MID_RAT;
                    BPRTQP.FOR_MID = BPCMT.DATA.CCY_INFO[WS_I-1].RECENT_MID_RAT;
                    BPRTQP.CS_BUY = BPCMT.DATA.CCY_INFO[WS_I-1].CS_BUY;
                    BPRTQP.CS_SELL = BPCMT.DATA.CCY_INFO[WS_I-1].CS_SELL;
                    BPRTQP.FX_BUY = BPCMT.DATA.CCY_INFO[WS_I-1].FX_BUY;
                    BPRTQP.FX_SELL = BPCMT.DATA.CCY_INFO[WS_I-1].FX_SELL;
                    BPRTQP.CCS_BUY = BPCMT.DATA.CCY_INFO[WS_I-1].CCS_BUY;
                    BPRTQP.CCS_SELL = BPCMT.DATA.CCY_INFO[WS_I-1].CCS_SELL;
                    BPRTQP.CFX_BUY = BPCMT.DATA.CCY_INFO[WS_I-1].CFX_BUY;
                    BPRTQP.CFX_SELL = BPCMT.DATA.CCY_INFO[WS_I-1].CFX_SELL;
                    BPRTQP.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                    BPRTQP.UPD_TM = SCCGWA.COMM_AREA.TR_TIME;
                    BPRTQP.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                    BPRTQP.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                    BPCTTQPM.INFO.FUNC = 'U';
                    S000_CALL_BPZTTQPM();
                }
                B003_CRE_HIS();
                if (BPCTQPHM.RETURN_INFO == 'D') {
                    BPCTQPHM.INFO.FUNC = 'R';
                    S000_CALL_BPZTQPHM();
                    if (BPCTQPHM.RETURN_INFO == 'F') {
                        CEP.TRC(SCCGWA, BPRTQPH.LOC_MID);
                        IBS.CLONE(SCCGWA, BPRTQPH, BPRTQPHO);
                        CEP.TRC(SCCGWA, BPRTQPH.LOC_MID);
                        BPCTQPHM.INFO.FUNC = 'D';
                        S000_CALL_BPZTQPHM();
                        IBS.CLONE(SCCGWA, BPRTQPHO, BPRTQPH);
                        BPRTQPH.KEY.EXP_DT = BPCMT.DATA.EFF_DT;
                        BPRTQPH.KEY.EXP_TM = BPCMT.DATA.EFF_TM;
                        BPCTQPHM.INFO.FUNC = 'C';
                        S000_CALL_BPZTQPHM();
                        B003_CRE_HIS();
                    }
                }
            }
        }
    }
    public void B003_CRE_HIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTQPH);
        IBS.init(SCCGWA, BPCTQPHM);
        BPRTQPH.KEY.EXP_DT = K_MAX_DATE;
        BPRTQPH.KEY.EXP_TM = K_MAX_TIME;
        CEP.TRC(SCCGWA, BPRTQPH.KEY.EXP_DT);
        CEP.TRC(SCCGWA, BPRTQPH.KEY.EXP_TM);
        CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[WS_I-1].MID_RAT);
        BPRTQPH.KEY.EXR_TYP = BPCMT.DATA.EXR_TYPE;
        BPRTQPH.KEY.BR = BPCMT.DATA.BR;
        BPRTQPH.KEY.CCY = BPCMT.DATA.CCY_INFO[WS_I-1].CCY;
        BPRTQPH.KEY.CORR_CCY = BPCMT.DATA.CCY_INFO[WS_I-1].CORR_CCY;
        BPRTQPH.EFF_DT = BPCMT.DATA.EFF_DT;
        BPRTQPH.EFF_TM = BPCMT.DATA.EFF_TM;
        BPRTQPH.LOC_MID = BPCMT.DATA.CCY_INFO[WS_I-1].MID_RAT;
        BPRTQPH.FOR_MID = BPCMT.DATA.CCY_INFO[WS_I-1].RECENT_MID_RAT;
        BPRTQPH.CS_BUY = BPCMT.DATA.CCY_INFO[WS_I-1].CS_BUY;
        BPRTQPH.CS_SELL = BPCMT.DATA.CCY_INFO[WS_I-1].CS_SELL;
        BPRTQPH.FX_BUY = BPCMT.DATA.CCY_INFO[WS_I-1].FX_BUY;
        BPRTQPH.FX_SELL = BPCMT.DATA.CCY_INFO[WS_I-1].FX_SELL;
        CEP.TRC(SCCGWA, BPRTQPH.LOC_MID);
        BPRTQPH.CCS_BUY = BPCMT.DATA.CCY_INFO[WS_I-1].CCS_BUY;
        BPRTQPH.CCS_SELL = BPCMT.DATA.CCY_INFO[WS_I-1].CCS_SELL;
        BPRTQPH.CFX_BUY = BPCMT.DATA.CCY_INFO[WS_I-1].CFX_BUY;
        BPRTQPH.CFX_SELL = BPCMT.DATA.CCY_INFO[WS_I-1].CFX_SELL;
        BPRTQPH.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRTQPH.UPD_TM = SCCGWA.COMM_AREA.TR_TIME;
        BPRTQPH.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRTQPH.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCTQPHM.INFO.FUNC = 'C';
        S000_CALL_BPZTQPHM();
    }
    public void B003_02_DEL_PROC() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= K_MAX_CNT; WS_I += 1) {
            if (BPCMT.DATA.CCY_INFO[WS_I-1].CCY.trim().length() > 0) {
                IBS.init(SCCGWA, BPCRTQPS);
                IBS.init(SCCGWA, BPRTQP);
                BPRTQP.KEY.BR = BPCMT.DATA.BR;
                BPRTQP.KEY.EXR_TYP = BPCMT.DATA.EXR_TYPE;
                BPRTQP.KEY.FWD_TENOR = BPCMT.DATA.CCY_INFO[WS_I-1].FWD_TENOR;
                BPRTQP.KEY.CCY = BPCMT.DATA.CCY_INFO[WS_I-1].CCY;
                if (WS_OLD_DATA == null) WS_OLD_DATA = "";
                JIBS_tmp_int = WS_OLD_DATA.length();
                for (int i=0;i<5000-JIBS_tmp_int;i++) WS_OLD_DATA += " ";
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRTQP);
                WS_OLD_DATA = JIBS_tmp_str[0] + WS_OLD_DATA.substring(WS_LEN);
                BPCTTQPM.INFO.FUNC = 'E';
                S000_CALL_BPZTTQPM();
            }
        }
    }
    public void B003_05_GEN_SEL_REF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRERGR);
        IBS.init(SCCGWA, BPCRERGR);
        BPRERGR.BASE_CCY = BPCMT.DATA.CCY_INFO[WS_I-1].CCY;
        BPRERGR.BASE_EXR_TYP = BPCMT.DATA.EXR_TYPE;
        BPRERGR.BASE_FWD_TENOR = BPCMT.DATA.CCY_INFO[WS_I-1].FWD_TENOR;
        BPCRERGR.INFO.FUNC = '6';
        S000_CALL_BPZTERGR();
        BPCRERGR.INFO.FUNC = 'R';
        S000_CALL_BPZTERGR();
        while (BPCRERGR.INFO.RTN_INFO != 'N') {
            if ((BPRERGR.CMP_BASE.equalsIgnoreCase("01") 
                && BPCMT.DATA.CCY_INFO[WS_I-1].MID_RAT == 0) 
                || (BPRERGR.CMP_BASE.equalsIgnoreCase("02") 
                && BPCMT.DATA.CCY_INFO[WS_I-1].FX_BUY == 0) 
                || (BPRERGR.CMP_BASE.equalsIgnoreCase("03") 
                && BPCMT.DATA.CCY_INFO[WS_I-1].FX_SELL == 0)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INP_RATE;
                S000_ERR_MSG_PROC();
            }
            if (BPRERGR.CMP_BASE.equalsIgnoreCase("01")) {
                WS_EX_RATES.WS_EX_RATE_ORG = BPCMT.DATA.CCY_INFO[WS_I-1].MID_RAT;
            } else if (BPRERGR.CMP_BASE.equalsIgnoreCase("02")) {
                WS_EX_RATES.WS_EX_RATE_ORG = BPCMT.DATA.CCY_INFO[WS_I-1].FX_BUY;
            } else if (BPRERGR.CMP_BASE.equalsIgnoreCase("03")) {
                WS_EX_RATES.WS_EX_RATE_ORG = BPCMT.DATA.CCY_INFO[WS_I-1].FX_SELL;
            } else {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
                S000_ERR_MSG_PROC();
            }
            if (BPRERGR.CMP_FLG == '0') {
                WS_EX_RATES.WS_EX_RATE_ORG = WS_EX_RATES.WS_EX_RATE_ORG * ( 1 + BPRERGR.AMEND_SP / 100 );
            } else if (BPRERGR.CMP_FLG == '1') {
                WS_EX_RATES.WS_EX_RATE_ORG = WS_EX_RATES.WS_EX_RATE_ORG + BPRERGR.AMEND_SP;
            } else {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
                S000_ERR_MSG_PROC();
            }
            if (BPRERGR.SPRD_METH == '1' 
                && BPCMT.DATA.CCY_INFO[WS_I-1].FX_BUY == 0) {
                BPCMT.DATA.CCY_INFO[WS_I-1].FX_BUY = WS_EX_RATES.WS_EX_RATE_ORG;
            }
            if (BPRERGR.SPRD_METH == '2' 
                && BPCMT.DATA.CCY_INFO[WS_I-1].FX_SELL == 0) {
                BPCMT.DATA.CCY_INFO[WS_I-1].FX_SELL = WS_EX_RATES.WS_EX_RATE_ORG;
            }
            if (BPRERGR.SPRD_METH == '3' 
                && BPCMT.DATA.CCY_INFO[WS_I-1].MID_RAT == 0) {
                BPCMT.DATA.CCY_INFO[WS_I-1].MID_RAT = WS_EX_RATES.WS_EX_RATE_ORG;
            }
        }
        BPCRERGR.INFO.FUNC = 'E';
        S000_CALL_BPZTERGR();
        if (BPCMT.DATA.CCY_INFO[WS_I-1].MID_RAT == 0) {
            BPCMT.DATA.CCY_INFO[WS_I-1].MID_RAT = ( BPCMT.DATA.CCY_INFO[WS_I-1].FX_BUY + BPCMT.DATA.CCY_INFO[WS_I-1].FX_SELL ) / 2;
        }
    }
    public void B003_01_CHK_AUTH() throws IOException,SQLException,Exception {
        if (BPRTQP.CS_BUY != BPCMT.DATA.CCY_INFO[WS_I-1].CS_BUY) {
            WS_BR = BPCMT.DATA.BR;
            WS_CCY = BPCMT.DATA.CCY_INFO[WS_I-1].CCY;
            WS_QTP_NEW = BPCMT.DATA.CCY_INFO[WS_I-1].CS_BUY;
            WS_QTP_OLD = BPRTQP.CS_BUY;
            R000_CHK_AUTH();
        }
        if (BPRTQP.CS_SELL != BPCMT.DATA.CCY_INFO[WS_I-1].CS_SELL) {
            WS_BR = BPCMT.DATA.BR;
            WS_CCY = BPCMT.DATA.CCY_INFO[WS_I-1].CCY;
            WS_QTP_NEW = BPCMT.DATA.CCY_INFO[WS_I-1].CS_SELL;
            WS_QTP_OLD = BPRTQP.CS_SELL;
            R000_CHK_AUTH();
        }
        if (BPRTQP.FX_BUY != BPCMT.DATA.CCY_INFO[WS_I-1].FX_BUY) {
            WS_BR = BPCMT.DATA.BR;
            WS_CCY = BPCMT.DATA.CCY_INFO[WS_I-1].CCY;
            WS_QTP_NEW = BPCMT.DATA.CCY_INFO[WS_I-1].FX_BUY;
            WS_QTP_OLD = BPRTQP.FX_BUY;
            R000_CHK_AUTH();
        }
        if (BPRTQP.FX_SELL != BPCMT.DATA.CCY_INFO[WS_I-1].FX_SELL) {
            WS_BR = BPCMT.DATA.BR;
            WS_CCY = BPCMT.DATA.CCY_INFO[WS_I-1].CCY;
            WS_QTP_NEW = BPCMT.DATA.CCY_INFO[WS_I-1].FX_SELL;
            WS_QTP_OLD = BPRTQP.FX_SELL;
            R000_CHK_AUTH();
        }
    }
    public void R000_GET_POINT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPREXRD);
        IBS.init(SCCGWA, BPCTEXRM);
        if ((BPCMT.DATA.EXR_TYPE.equalsIgnoreCase("TRE") 
            || BPCMT.DATA.EXR_TYPE.equalsIgnoreCase("MDR")) 
            && BPCMT.DATA.CCY_INFO[WS_I-1].CCY.equalsIgnoreCase("156")) {
            BPREXRD.KEY.CCY = BPCMT.DATA.CCY_INFO[WS_I-1].CORR_CCY;
        } else {
            BPREXRD.KEY.CCY = BPCMT.DATA.CCY_INFO[WS_I-1].CCY;
        }
        BPREXRD.KEY.EXR_TYP = BPCMT.DATA.EXR_TYPE;
        BPCTEXRM.INFO.FUNC = 'Q';
        S000_CALL_BPZTEXRM();
        if (BPCTEXRM.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EX_RATE_UNDEFINE;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPREXRD.EXR_PNT);
        CEP.TRC(SCCGWA, BPREXRD.EXR_RND);
        if (BPREXRD.EXR_PNT == '0') {
            WS_DEC_POINT.WS_DEC_MREM = BPCMT.DATA.CCY_INFO[WS_I-1].MID_RAT % 1;
            WS_DEC_POINT.WS_DEC_MP0 = (int) ((BPCMT.DATA.CCY_INFO[WS_I-1].MID_RAT - WS_DEC_POINT.WS_DEC_MREM) / 1);
            WS_DEC_POINT.WS_DEC_BREM = BPCMT.DATA.CCY_INFO[WS_I-1].FX_BUY % 1;
            WS_DEC_POINT.WS_DEC_BP0 = (int) ((BPCMT.DATA.CCY_INFO[WS_I-1].FX_BUY - WS_DEC_POINT.WS_DEC_BREM) / 1);
            WS_DEC_POINT.WS_DEC_SREM = BPCMT.DATA.CCY_INFO[WS_I-1].FX_SELL % 1;
            WS_DEC_POINT.WS_DEC_SP0 = (int) ((BPCMT.DATA.CCY_INFO[WS_I-1].FX_SELL - WS_DEC_POINT.WS_DEC_SREM) / 1);
            WS_DEC_POINT.WS_DEC_CBREM = BPCMT.DATA.CCY_INFO[WS_I-1].CS_BUY % 1;
            WS_DEC_POINT.WS_DEC_CBP0 = (int) ((BPCMT.DATA.CCY_INFO[WS_I-1].CS_BUY - WS_DEC_POINT.WS_DEC_CBREM) / 1);
            WS_DEC_POINT.WS_DEC_CSREM = BPCMT.DATA.CCY_INFO[WS_I-1].CS_SELL % 1;
            WS_DEC_POINT.WS_DEC_CSP0 = (int) ((BPCMT.DATA.CCY_INFO[WS_I-1].CS_SELL - WS_DEC_POINT.WS_DEC_CSREM) / 1);
            if (BPREXRD.EXR_RND == '1') {
                WS_DEC_POINT.WS_DEC_MP0 += 1;
                WS_DEC_POINT.WS_DEC_BP0 += 1;
                WS_DEC_POINT.WS_DEC_SP0 += 1;
                WS_DEC_POINT.WS_DEC_CBP0 += 1;
                WS_DEC_POINT.WS_DEC_CSP0 += 1;
            }
            if (BPREXRD.EXR_RND == '2') {
                if (WS_DEC_POINT.WS_DEC_MREM >= .5) {
                    WS_DEC_POINT.WS_DEC_MP0 += 1;
                }
                if (WS_DEC_POINT.WS_DEC_BREM >= .5) {
                    WS_DEC_POINT.WS_DEC_BP0 += 1;
                }
                if (WS_DEC_POINT.WS_DEC_SREM >= .5) {
                    WS_DEC_POINT.WS_DEC_SP0 += 1;
                }
                if (WS_DEC_POINT.WS_DEC_CBREM >= .5) {
                    WS_DEC_POINT.WS_DEC_CBP0 += 1;
                }
                if (WS_DEC_POINT.WS_DEC_CSREM >= .5) {
                    WS_DEC_POINT.WS_DEC_CSP0 += 1;
                }
            }
            BPCMT.DATA.CCY_INFO[WS_I-1].MID_RAT = (double)WS_DEC_POINT.WS_DEC_MP0;
            BPCMT.DATA.CCY_INFO[WS_I-1].FX_BUY = (double)WS_DEC_POINT.WS_DEC_BP0;
            BPCMT.DATA.CCY_INFO[WS_I-1].FX_SELL = (double)WS_DEC_POINT.WS_DEC_SP0;
            BPCMT.DATA.CCY_INFO[WS_I-1].CS_BUY = (double)WS_DEC_POINT.WS_DEC_CBP0;
            BPCMT.DATA.CCY_INFO[WS_I-1].CS_SELL = (double)WS_DEC_POINT.WS_DEC_CSP0;
        } else if (BPREXRD.EXR_PNT == '1') {
            WS_DEC_POINT.WS_DEC_MREM = BPCMT.DATA.CCY_INFO[WS_I-1].MID_RAT % 1;
            WS_DEC_POINT.WS_DEC_MP1 = (BPCMT.DATA.CCY_INFO[WS_I-1].MID_RAT - WS_DEC_POINT.WS_DEC_MREM) / 1;
            WS_DEC_POINT.WS_DEC_BREM = BPCMT.DATA.CCY_INFO[WS_I-1].FX_BUY % 1;
            WS_DEC_POINT.WS_DEC_BP1 = (BPCMT.DATA.CCY_INFO[WS_I-1].FX_BUY - WS_DEC_POINT.WS_DEC_BREM) / 1;
            WS_DEC_POINT.WS_DEC_SREM = BPCMT.DATA.CCY_INFO[WS_I-1].FX_SELL % 1;
            WS_DEC_POINT.WS_DEC_SP1 = (BPCMT.DATA.CCY_INFO[WS_I-1].FX_SELL - WS_DEC_POINT.WS_DEC_SREM) / 1;
            WS_DEC_POINT.WS_DEC_CBREM = BPCMT.DATA.CCY_INFO[WS_I-1].CS_BUY % 1;
            WS_DEC_POINT.WS_DEC_CBP1 = (BPCMT.DATA.CCY_INFO[WS_I-1].CS_BUY - WS_DEC_POINT.WS_DEC_CBREM) / 1;
            WS_DEC_POINT.WS_DEC_CSREM = BPCMT.DATA.CCY_INFO[WS_I-1].CS_SELL % 1;
            WS_DEC_POINT.WS_DEC_CSP1 = (BPCMT.DATA.CCY_INFO[WS_I-1].CS_SELL - WS_DEC_POINT.WS_DEC_CSREM) / 1;
            if (BPREXRD.EXR_RND == '1') {
                WS_DEC_POINT.WS_DEC_MP1 += .1;
                WS_DEC_POINT.WS_DEC_BP1 += .1;
                WS_DEC_POINT.WS_DEC_SP1 += .1;
                WS_DEC_POINT.WS_DEC_CBP1 += .1;
                WS_DEC_POINT.WS_DEC_CSP1 += .1;
            }
            if (BPREXRD.EXR_RND == '2') {
                if (WS_DEC_POINT.WS_DEC_MREM >= .05) {
                    WS_DEC_POINT.WS_DEC_MP1 += .1;
                }
                if (WS_DEC_POINT.WS_DEC_BREM >= .05) {
                    WS_DEC_POINT.WS_DEC_BP1 += .1;
                }
                if (WS_DEC_POINT.WS_DEC_SREM >= .05) {
                    WS_DEC_POINT.WS_DEC_SP1 += .1;
                }
                if (WS_DEC_POINT.WS_DEC_CBREM >= .05) {
                    WS_DEC_POINT.WS_DEC_CBP1 += .1;
                }
                if (WS_DEC_POINT.WS_DEC_CSREM >= .05) {
                    WS_DEC_POINT.WS_DEC_CSP1 += .1;
                }
            }
            BPCMT.DATA.CCY_INFO[WS_I-1].MID_RAT = WS_DEC_POINT.WS_DEC_MP1;
            BPCMT.DATA.CCY_INFO[WS_I-1].FX_BUY = WS_DEC_POINT.WS_DEC_BP1;
            BPCMT.DATA.CCY_INFO[WS_I-1].FX_SELL = WS_DEC_POINT.WS_DEC_SP1;
            BPCMT.DATA.CCY_INFO[WS_I-1].CS_BUY = WS_DEC_POINT.WS_DEC_CBP1;
            BPCMT.DATA.CCY_INFO[WS_I-1].CS_SELL = WS_DEC_POINT.WS_DEC_CSP1;
        } else if (BPREXRD.EXR_PNT == '2') {
            WS_DEC_POINT.WS_DEC_MREM = BPCMT.DATA.CCY_INFO[WS_I-1].MID_RAT % 1;
            WS_DEC_POINT.WS_DEC_MP2 = (BPCMT.DATA.CCY_INFO[WS_I-1].MID_RAT - WS_DEC_POINT.WS_DEC_MREM) / 1;
            WS_DEC_POINT.WS_DEC_BREM = BPCMT.DATA.CCY_INFO[WS_I-1].FX_BUY % 1;
            WS_DEC_POINT.WS_DEC_BP2 = (BPCMT.DATA.CCY_INFO[WS_I-1].FX_BUY - WS_DEC_POINT.WS_DEC_BREM) / 1;
            WS_DEC_POINT.WS_DEC_SREM = BPCMT.DATA.CCY_INFO[WS_I-1].FX_SELL % 1;
            WS_DEC_POINT.WS_DEC_SP2 = (BPCMT.DATA.CCY_INFO[WS_I-1].FX_SELL - WS_DEC_POINT.WS_DEC_SREM) / 1;
            WS_DEC_POINT.WS_DEC_CBREM = BPCMT.DATA.CCY_INFO[WS_I-1].CS_BUY % 1;
            WS_DEC_POINT.WS_DEC_CBP2 = (BPCMT.DATA.CCY_INFO[WS_I-1].CS_BUY - WS_DEC_POINT.WS_DEC_CBREM) / 1;
            WS_DEC_POINT.WS_DEC_CSREM = BPCMT.DATA.CCY_INFO[WS_I-1].CS_SELL % 1;
            WS_DEC_POINT.WS_DEC_CSP2 = (BPCMT.DATA.CCY_INFO[WS_I-1].CS_SELL - WS_DEC_POINT.WS_DEC_CSREM) / 1;
            CEP.TRC(SCCGWA, "DEVHZ985");
            CEP.TRC(SCCGWA, WS_DEC_POINT.WS_DEC_MP2);
            CEP.TRC(SCCGWA, WS_DEC_POINT.WS_DEC_MREM);
            CEP.TRC(SCCGWA, WS_DEC_POINT.WS_DEC_BP2);
            CEP.TRC(SCCGWA, WS_DEC_POINT.WS_DEC_BREM);
            CEP.TRC(SCCGWA, WS_DEC_POINT.WS_DEC_SP2);
            CEP.TRC(SCCGWA, WS_DEC_POINT.WS_DEC_SREM);
            if (BPREXRD.EXR_RND == '1') {
                WS_DEC_POINT.WS_DEC_MP2 += .01;
                WS_DEC_POINT.WS_DEC_BP2 += .01;
                WS_DEC_POINT.WS_DEC_SP2 += .01;
                WS_DEC_POINT.WS_DEC_CBP2 += .01;
                WS_DEC_POINT.WS_DEC_CSP2 += .01;
            }
            if (BPREXRD.EXR_RND == '2') {
                if (WS_DEC_POINT.WS_DEC_MREM >= .005) {
                    WS_DEC_POINT.WS_DEC_MP2 += .01;
                }
                if (WS_DEC_POINT.WS_DEC_BREM >= .005) {
                    WS_DEC_POINT.WS_DEC_BP2 += .01;
                }
                if (WS_DEC_POINT.WS_DEC_SREM >= .005) {
                    WS_DEC_POINT.WS_DEC_SP2 += .01;
                }
                if (WS_DEC_POINT.WS_DEC_CBREM >= .005) {
                    WS_DEC_POINT.WS_DEC_CBP2 += .01;
                }
                if (WS_DEC_POINT.WS_DEC_CSREM >= .005) {
                    WS_DEC_POINT.WS_DEC_CSP2 += .01;
                }
            }
            BPCMT.DATA.CCY_INFO[WS_I-1].MID_RAT = WS_DEC_POINT.WS_DEC_MP2;
            BPCMT.DATA.CCY_INFO[WS_I-1].FX_BUY = WS_DEC_POINT.WS_DEC_BP2;
            BPCMT.DATA.CCY_INFO[WS_I-1].FX_SELL = WS_DEC_POINT.WS_DEC_SP2;
            BPCMT.DATA.CCY_INFO[WS_I-1].CS_BUY = WS_DEC_POINT.WS_DEC_CBP2;
            BPCMT.DATA.CCY_INFO[WS_I-1].CS_SELL = WS_DEC_POINT.WS_DEC_CSP2;
        } else if (BPREXRD.EXR_PNT == '3') {
            WS_DEC_POINT.WS_DEC_MREM = BPCMT.DATA.CCY_INFO[WS_I-1].MID_RAT % 1;
            WS_DEC_POINT.WS_DEC_MP3 = (BPCMT.DATA.CCY_INFO[WS_I-1].MID_RAT - WS_DEC_POINT.WS_DEC_MREM) / 1;
            WS_DEC_POINT.WS_DEC_BREM = BPCMT.DATA.CCY_INFO[WS_I-1].FX_BUY % 1;
            WS_DEC_POINT.WS_DEC_BP3 = (BPCMT.DATA.CCY_INFO[WS_I-1].FX_BUY - WS_DEC_POINT.WS_DEC_BREM) / 1;
            WS_DEC_POINT.WS_DEC_SREM = BPCMT.DATA.CCY_INFO[WS_I-1].FX_SELL % 1;
            WS_DEC_POINT.WS_DEC_SP3 = (BPCMT.DATA.CCY_INFO[WS_I-1].FX_SELL - WS_DEC_POINT.WS_DEC_SREM) / 1;
            WS_DEC_POINT.WS_DEC_CBREM = BPCMT.DATA.CCY_INFO[WS_I-1].CS_BUY % 1;
            WS_DEC_POINT.WS_DEC_CBP3 = (BPCMT.DATA.CCY_INFO[WS_I-1].CS_BUY - WS_DEC_POINT.WS_DEC_CBREM) / 1;
            WS_DEC_POINT.WS_DEC_CSREM = BPCMT.DATA.CCY_INFO[WS_I-1].CS_SELL % 1;
            WS_DEC_POINT.WS_DEC_CSP3 = (BPCMT.DATA.CCY_INFO[WS_I-1].CS_SELL - WS_DEC_POINT.WS_DEC_CSREM) / 1;
            if (BPREXRD.EXR_RND == '1') {
                WS_DEC_POINT.WS_DEC_MP3 += .001;
                WS_DEC_POINT.WS_DEC_BP3 += .001;
                WS_DEC_POINT.WS_DEC_SP3 += .001;
                WS_DEC_POINT.WS_DEC_CBP3 += .001;
                WS_DEC_POINT.WS_DEC_CSP3 += .001;
            }
            if (BPREXRD.EXR_RND == '2') {
                if (WS_DEC_POINT.WS_DEC_MREM >= .0005) {
                    WS_DEC_POINT.WS_DEC_MP3 += .001;
                }
                if (WS_DEC_POINT.WS_DEC_BREM >= .0005) {
                    WS_DEC_POINT.WS_DEC_BP3 += .001;
                }
                if (WS_DEC_POINT.WS_DEC_SREM >= .0005) {
                    WS_DEC_POINT.WS_DEC_SP3 += .001;
                }
                if (WS_DEC_POINT.WS_DEC_CBREM >= .0005) {
                    WS_DEC_POINT.WS_DEC_CBP3 += .001;
                }
                if (WS_DEC_POINT.WS_DEC_CSREM >= .0005) {
                    WS_DEC_POINT.WS_DEC_CSP3 += .001;
                }
            }
            BPCMT.DATA.CCY_INFO[WS_I-1].MID_RAT = WS_DEC_POINT.WS_DEC_MP3;
            BPCMT.DATA.CCY_INFO[WS_I-1].FX_BUY = WS_DEC_POINT.WS_DEC_BP3;
            BPCMT.DATA.CCY_INFO[WS_I-1].FX_SELL = WS_DEC_POINT.WS_DEC_SP3;
            BPCMT.DATA.CCY_INFO[WS_I-1].CS_BUY = WS_DEC_POINT.WS_DEC_CBP3;
            BPCMT.DATA.CCY_INFO[WS_I-1].CS_SELL = WS_DEC_POINT.WS_DEC_CSP3;
        } else if (BPREXRD.EXR_PNT == '4') {
            WS_DEC_POINT.WS_DEC_MREM = BPCMT.DATA.CCY_INFO[WS_I-1].MID_RAT % 1;
            WS_DEC_POINT.WS_DEC_MP4 = (BPCMT.DATA.CCY_INFO[WS_I-1].MID_RAT - WS_DEC_POINT.WS_DEC_MREM) / 1;
            WS_DEC_POINT.WS_DEC_BREM = BPCMT.DATA.CCY_INFO[WS_I-1].FX_BUY % 1;
            WS_DEC_POINT.WS_DEC_BP4 = (BPCMT.DATA.CCY_INFO[WS_I-1].FX_BUY - WS_DEC_POINT.WS_DEC_BREM) / 1;
            WS_DEC_POINT.WS_DEC_SREM = BPCMT.DATA.CCY_INFO[WS_I-1].FX_SELL % 1;
            WS_DEC_POINT.WS_DEC_SP4 = (BPCMT.DATA.CCY_INFO[WS_I-1].FX_SELL - WS_DEC_POINT.WS_DEC_SREM) / 1;
            WS_DEC_POINT.WS_DEC_CBREM = BPCMT.DATA.CCY_INFO[WS_I-1].CS_BUY % 1;
            WS_DEC_POINT.WS_DEC_CBP4 = (BPCMT.DATA.CCY_INFO[WS_I-1].CS_BUY - WS_DEC_POINT.WS_DEC_CBREM) / 1;
            WS_DEC_POINT.WS_DEC_CSREM = BPCMT.DATA.CCY_INFO[WS_I-1].CS_SELL % 1;
            WS_DEC_POINT.WS_DEC_CSP4 = (BPCMT.DATA.CCY_INFO[WS_I-1].CS_SELL - WS_DEC_POINT.WS_DEC_CSREM) / 1;
            if (BPREXRD.EXR_RND == '1') {
                WS_DEC_POINT.WS_DEC_MP4 += .0001;
                WS_DEC_POINT.WS_DEC_BP4 += .0001;
                WS_DEC_POINT.WS_DEC_SP4 += .0001;
                WS_DEC_POINT.WS_DEC_CBP4 += .0001;
                WS_DEC_POINT.WS_DEC_CSP4 += .0001;
            }
            if (BPREXRD.EXR_RND == '2') {
                if (WS_DEC_POINT.WS_DEC_MREM >= .00005) {
                    WS_DEC_POINT.WS_DEC_MP4 += .0001;
                }
                if (WS_DEC_POINT.WS_DEC_BREM >= .00005) {
                    WS_DEC_POINT.WS_DEC_BP4 += .0001;
                }
                if (WS_DEC_POINT.WS_DEC_SREM >= .00005) {
                    WS_DEC_POINT.WS_DEC_SP4 += .0001;
                }
                if (WS_DEC_POINT.WS_DEC_CBREM >= .00005) {
                    WS_DEC_POINT.WS_DEC_CBP4 += .0001;
                }
                if (WS_DEC_POINT.WS_DEC_CSREM >= .00005) {
                    WS_DEC_POINT.WS_DEC_CSP4 += .0001;
                }
            }
            BPCMT.DATA.CCY_INFO[WS_I-1].MID_RAT = WS_DEC_POINT.WS_DEC_MP4;
            BPCMT.DATA.CCY_INFO[WS_I-1].FX_BUY = WS_DEC_POINT.WS_DEC_BP4;
            BPCMT.DATA.CCY_INFO[WS_I-1].FX_SELL = WS_DEC_POINT.WS_DEC_SP4;
            BPCMT.DATA.CCY_INFO[WS_I-1].CS_BUY = WS_DEC_POINT.WS_DEC_CBP4;
            BPCMT.DATA.CCY_INFO[WS_I-1].CS_SELL = WS_DEC_POINT.WS_DEC_CSP4;
        } else if (BPREXRD.EXR_PNT == '5') {
            WS_DEC_POINT.WS_DEC_MREM = BPCMT.DATA.CCY_INFO[WS_I-1].MID_RAT % 1;
            WS_DEC_POINT.WS_DEC_MP5 = (BPCMT.DATA.CCY_INFO[WS_I-1].MID_RAT - WS_DEC_POINT.WS_DEC_MREM) / 1;
            WS_DEC_POINT.WS_DEC_BREM = BPCMT.DATA.CCY_INFO[WS_I-1].FX_BUY % 1;
            WS_DEC_POINT.WS_DEC_BP5 = (BPCMT.DATA.CCY_INFO[WS_I-1].FX_BUY - WS_DEC_POINT.WS_DEC_BREM) / 1;
            WS_DEC_POINT.WS_DEC_SREM = BPCMT.DATA.CCY_INFO[WS_I-1].FX_SELL % 1;
            WS_DEC_POINT.WS_DEC_SP5 = (BPCMT.DATA.CCY_INFO[WS_I-1].FX_SELL - WS_DEC_POINT.WS_DEC_SREM) / 1;
            WS_DEC_POINT.WS_DEC_CBREM = BPCMT.DATA.CCY_INFO[WS_I-1].CS_BUY % 1;
            WS_DEC_POINT.WS_DEC_CBP5 = (BPCMT.DATA.CCY_INFO[WS_I-1].CS_BUY - WS_DEC_POINT.WS_DEC_CBREM) / 1;
            WS_DEC_POINT.WS_DEC_CSREM = BPCMT.DATA.CCY_INFO[WS_I-1].CS_SELL % 1;
            WS_DEC_POINT.WS_DEC_CSP5 = (BPCMT.DATA.CCY_INFO[WS_I-1].CS_SELL - WS_DEC_POINT.WS_DEC_CSREM) / 1;
            if (BPREXRD.EXR_RND == '1') {
                WS_DEC_POINT.WS_DEC_MP5 += .00001;
                WS_DEC_POINT.WS_DEC_BP5 += .00001;
                WS_DEC_POINT.WS_DEC_SP5 += .00001;
                WS_DEC_POINT.WS_DEC_CBP5 += .00001;
                WS_DEC_POINT.WS_DEC_CSP5 += .00001;
            }
            if (BPREXRD.EXR_RND == '2') {
                if (WS_DEC_POINT.WS_DEC_MREM >= .000005) {
                    WS_DEC_POINT.WS_DEC_MP5 += .00001;
                }
                if (WS_DEC_POINT.WS_DEC_BREM >= .000005) {
                    WS_DEC_POINT.WS_DEC_BP5 += .00001;
                }
                if (WS_DEC_POINT.WS_DEC_SREM >= .000005) {
                    WS_DEC_POINT.WS_DEC_SP5 += .00001;
                }
                if (WS_DEC_POINT.WS_DEC_CBREM >= .000005) {
                    WS_DEC_POINT.WS_DEC_CBP5 += .00001;
                }
                if (WS_DEC_POINT.WS_DEC_CSREM >= .000005) {
                    WS_DEC_POINT.WS_DEC_CSP5 += .00001;
                }
            }
            BPCMT.DATA.CCY_INFO[WS_I-1].MID_RAT = WS_DEC_POINT.WS_DEC_MP5;
            BPCMT.DATA.CCY_INFO[WS_I-1].FX_BUY = WS_DEC_POINT.WS_DEC_BP5;
            BPCMT.DATA.CCY_INFO[WS_I-1].FX_SELL = WS_DEC_POINT.WS_DEC_SP5;
            BPCMT.DATA.CCY_INFO[WS_I-1].CS_BUY = WS_DEC_POINT.WS_DEC_CBP5;
            BPCMT.DATA.CCY_INFO[WS_I-1].CS_SELL = WS_DEC_POINT.WS_DEC_CSP5;
        } else if (BPREXRD.EXR_PNT == '6') {
            WS_DEC_POINT.WS_DEC_MREM = BPCMT.DATA.CCY_INFO[WS_I-1].MID_RAT % 1;
            WS_DEC_POINT.WS_DEC_MP6 = (BPCMT.DATA.CCY_INFO[WS_I-1].MID_RAT - WS_DEC_POINT.WS_DEC_MREM) / 1;
            WS_DEC_POINT.WS_DEC_BREM = BPCMT.DATA.CCY_INFO[WS_I-1].FX_BUY % 1;
            WS_DEC_POINT.WS_DEC_BP6 = (BPCMT.DATA.CCY_INFO[WS_I-1].FX_BUY - WS_DEC_POINT.WS_DEC_BREM) / 1;
            WS_DEC_POINT.WS_DEC_SREM = BPCMT.DATA.CCY_INFO[WS_I-1].FX_SELL % 1;
            WS_DEC_POINT.WS_DEC_SP6 = (BPCMT.DATA.CCY_INFO[WS_I-1].FX_SELL - WS_DEC_POINT.WS_DEC_SREM) / 1;
            WS_DEC_POINT.WS_DEC_CBREM = BPCMT.DATA.CCY_INFO[WS_I-1].CS_BUY % 1;
            WS_DEC_POINT.WS_DEC_CBP6 = (BPCMT.DATA.CCY_INFO[WS_I-1].CS_BUY - WS_DEC_POINT.WS_DEC_CBREM) / 1;
            WS_DEC_POINT.WS_DEC_CSREM = BPCMT.DATA.CCY_INFO[WS_I-1].CS_SELL % 1;
            WS_DEC_POINT.WS_DEC_CSP6 = (BPCMT.DATA.CCY_INFO[WS_I-1].CS_SELL - WS_DEC_POINT.WS_DEC_CSREM) / 1;
            if (BPREXRD.EXR_RND == '1') {
                WS_DEC_POINT.WS_DEC_MP6 += .000001;
                WS_DEC_POINT.WS_DEC_BP6 += .000001;
                WS_DEC_POINT.WS_DEC_SP6 += .000001;
                WS_DEC_POINT.WS_DEC_CBP6 += .000001;
                WS_DEC_POINT.WS_DEC_CSP6 += .000001;
            }
            if (BPREXRD.EXR_RND == '2') {
                if (WS_DEC_POINT.WS_DEC_MREM >= .0000005) {
                    WS_DEC_POINT.WS_DEC_MP6 += .000001;
                }
                if (WS_DEC_POINT.WS_DEC_BREM >= .0000005) {
                    WS_DEC_POINT.WS_DEC_BP6 += .000001;
                }
                if (WS_DEC_POINT.WS_DEC_SREM >= .0000005) {
                    WS_DEC_POINT.WS_DEC_SP6 += .000001;
                }
                if (WS_DEC_POINT.WS_DEC_CBREM >= .0000005) {
                    WS_DEC_POINT.WS_DEC_CBP6 += .000001;
                }
                if (WS_DEC_POINT.WS_DEC_CSREM >= .0000005) {
                    WS_DEC_POINT.WS_DEC_CSP6 += .000001;
                }
            }
            BPCMT.DATA.CCY_INFO[WS_I-1].MID_RAT = WS_DEC_POINT.WS_DEC_MP6;
            BPCMT.DATA.CCY_INFO[WS_I-1].FX_BUY = WS_DEC_POINT.WS_DEC_BP6;
            BPCMT.DATA.CCY_INFO[WS_I-1].FX_SELL = WS_DEC_POINT.WS_DEC_SP6;
            BPCMT.DATA.CCY_INFO[WS_I-1].CS_BUY = WS_DEC_POINT.WS_DEC_CBP6;
            BPCMT.DATA.CCY_INFO[WS_I-1].CS_SELL = WS_DEC_POINT.WS_DEC_CSP6;
        } else if (BPREXRD.EXR_PNT == '7') {
            WS_DEC_POINT.WS_DEC_MREM = BPCMT.DATA.CCY_INFO[WS_I-1].MID_RAT % 1;
            WS_DEC_POINT.WS_DEC_MP7 = (BPCMT.DATA.CCY_INFO[WS_I-1].MID_RAT - WS_DEC_POINT.WS_DEC_MREM) / 1;
            WS_DEC_POINT.WS_DEC_BREM = BPCMT.DATA.CCY_INFO[WS_I-1].FX_BUY % 1;
            WS_DEC_POINT.WS_DEC_BP7 = (BPCMT.DATA.CCY_INFO[WS_I-1].FX_BUY - WS_DEC_POINT.WS_DEC_BREM) / 1;
            WS_DEC_POINT.WS_DEC_SREM = BPCMT.DATA.CCY_INFO[WS_I-1].FX_SELL % 1;
            WS_DEC_POINT.WS_DEC_SP7 = (BPCMT.DATA.CCY_INFO[WS_I-1].FX_SELL - WS_DEC_POINT.WS_DEC_SREM) / 1;
            WS_DEC_POINT.WS_DEC_CBREM = BPCMT.DATA.CCY_INFO[WS_I-1].CS_BUY % 1;
            WS_DEC_POINT.WS_DEC_CBP7 = (BPCMT.DATA.CCY_INFO[WS_I-1].CS_BUY - WS_DEC_POINT.WS_DEC_CBREM) / 1;
            WS_DEC_POINT.WS_DEC_CSREM = BPCMT.DATA.CCY_INFO[WS_I-1].CS_SELL % 1;
            WS_DEC_POINT.WS_DEC_CSP7 = (BPCMT.DATA.CCY_INFO[WS_I-1].CS_SELL - WS_DEC_POINT.WS_DEC_CSREM) / 1;
            if (BPREXRD.EXR_RND == '1') {
                WS_DEC_POINT.WS_DEC_MP7 += .0000001;
                WS_DEC_POINT.WS_DEC_BP7 += .0000001;
                WS_DEC_POINT.WS_DEC_SP7 += .0000001;
                WS_DEC_POINT.WS_DEC_CBP7 += .0000001;
                WS_DEC_POINT.WS_DEC_CSP7 += .0000001;
            }
            if (BPREXRD.EXR_RND == '2') {
                if (WS_DEC_POINT.WS_DEC_MREM >= .00000005) {
                    WS_DEC_POINT.WS_DEC_MP7 += .0000001;
                }
                if (WS_DEC_POINT.WS_DEC_BREM >= .00000005) {
                    WS_DEC_POINT.WS_DEC_BP7 += .0000001;
                }
                if (WS_DEC_POINT.WS_DEC_SREM >= .00000005) {
                    WS_DEC_POINT.WS_DEC_SP7 += .0000001;
                }
                if (WS_DEC_POINT.WS_DEC_CBREM >= .00000005) {
                    WS_DEC_POINT.WS_DEC_CBP7 += .0000001;
                }
                if (WS_DEC_POINT.WS_DEC_CSREM >= .00000005) {
                    WS_DEC_POINT.WS_DEC_CSP7 += .0000001;
                }
            }
            BPCMT.DATA.CCY_INFO[WS_I-1].MID_RAT = WS_DEC_POINT.WS_DEC_MP7;
            BPCMT.DATA.CCY_INFO[WS_I-1].FX_BUY = WS_DEC_POINT.WS_DEC_BP7;
            BPCMT.DATA.CCY_INFO[WS_I-1].FX_SELL = WS_DEC_POINT.WS_DEC_SP7;
            BPCMT.DATA.CCY_INFO[WS_I-1].CS_BUY = WS_DEC_POINT.WS_DEC_CBP7;
            BPCMT.DATA.CCY_INFO[WS_I-1].CS_SELL = WS_DEC_POINT.WS_DEC_CSP7;
        } else if (BPREXRD.EXR_PNT == '8'
            || BPREXRD.EXR_PNT == '9') {
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_DATA_ERR;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[WS_I-1].MID_RAT);
        CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[WS_I-1].FX_BUY);
        CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[WS_I-1].FX_SELL);
        CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[WS_I-1].CS_BUY);
        CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[WS_I-1].CS_SELL);
    }
    public void R000_CHK_AUTH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCCEUA);
        BPCCEUA.FUNC = 'C';
        BPCCEUA.INP_DATA.BR = WS_BR;
        BPCCEUA.INP_DATA.CCY = WS_CCY;
        BPCCEUA.INP_DATA.QTP_NEW = WS_QTP_NEW;
        BPCCEUA.INP_DATA.QTP_OLD = WS_QTP_OLD;
        S000_CALL_BPZCEUA();
        if (BPCCEUA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCCEUA.RC);
            S000_ERR_MSG_PROC();
        } else {
            if (BPCCEUA.INP_DATA.AUTH_LVL.trim().length() > 0 
                || !BPCCEUA.INP_DATA.AUTH_LVL.equalsIgnoreCase("00")) {
                IBS.CPY2CLS(SCCGWA, BPCCEUA.INP_DATA.AUTH_LVL, WS_TEMP_LVL);
                if (WS_TEMP_LVL.WS_TEMP_L1 < WS_TEMP_LVL.WS_TEMP_L2) {
                    WS_TEMP_LVL.WS_TEMP_L1 = WS_TEMP_LVL.WS_TEMP_L2;
                    if (BPCCEUA.INP_DATA.AUTH_LVL == null) BPCCEUA.INP_DATA.AUTH_LVL = "";
                    JIBS_tmp_int = BPCCEUA.INP_DATA.AUTH_LVL.length();
                    for (int i=0;i<2-JIBS_tmp_int;i++) BPCCEUA.INP_DATA.AUTH_LVL += " ";
                    WS_TEMP_LVL.WS_TEMP_L2 = BPCCEUA.INP_DATA.AUTH_LVL.substring(0, 1).charAt(0);
                }
                if (WS_TEMP_LVL.WS_TEMP_L1 > WS_AUTH_LVL.WS_AUTH_L1) {
                    WS_AUTH_LVL.WS_AUTH_L1 = WS_TEMP_LVL.WS_TEMP_L1;
                }
                if (WS_TEMP_LVL.WS_TEMP_L2 > WS_AUTH_LVL.WS_AUTH_L2) {
                    WS_AUTH_LVL.WS_AUTH_L2 = WS_TEMP_LVL.WS_TEMP_L2;
                }
            }
        }
    }
    public void B006_BRW_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTTQP();
        T000_READNEXT_BPTTQP();
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "DEVHZ1315");
            IBS.init(SCCGWA, WS_OUT_DATA);
            WS_NOTFND_FLAG = ' ';
            WS_OUT_DATA.WS_X_EXR_TYPE = BPCMT.DATA.EXR_TYPE;
            WS_OUT_DATA.WS_X_BR = BPCMT.DATA.BR;
            WS_I = 0;
            WS_NOTFND_FLAG = 'Y';
        }
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            WS_I += 1;
            WS_OUT_DATA.WS_X_CCY_INFO[WS_I-1].WS_X_CCY = BPRTQP.KEY.CCY;
            IBS.init(SCCGWA, BPCTEXRM);
            IBS.init(SCCGWA, BPREXRD);
            BPCTEXRM.INFO.FUNC = 'Q';
            BPREXRD.KEY.EXR_TYP = WS_OUT_DATA.WS_X_EXR_TYPE;
            BPREXRD.KEY.CCY = WS_OUT_DATA.WS_X_CCY_INFO[WS_I-1].WS_X_CCY;
            S000_CALL_BPZTEXRM();
            WS_OUT_DATA.WS_X_CCY_INFO[WS_I-1].WS_X_UNIT = BPREXRD.UNT;
            CEP.TRC(SCCGWA, WS_OUT_DATA.WS_X_CCY_INFO[WS_I-1].WS_X_UNIT);
            WS_OUT_DATA.WS_X_CCY_INFO[WS_I-1].WS_X_RECENT_MID_RAT = BPRTQP.FOR_MID;
            WS_OUT_DATA.WS_X_CCY_INFO[WS_I-1].WS_X_MID_RAT = BPRTQP.LOC_MID;
            WS_OUT_DATA.WS_X_CCY_INFO[WS_I-1].WS_X_CS_BUY = BPRTQP.CS_BUY;
            WS_OUT_DATA.WS_X_CCY_INFO[WS_I-1].WS_X_CS_SELL = BPRTQP.CS_SELL;
            WS_OUT_DATA.WS_X_CCY_INFO[WS_I-1].WS_X_FX_BUY = BPRTQP.FX_BUY;
            WS_OUT_DATA.WS_X_CCY_INFO[WS_I-1].WS_X_FX_SELL = BPRTQP.FX_SELL;
            WS_OUT_DATA.WS_X_CCY_INFO[WS_I-1].WS_X_CCS_BUY = BPRTQP.CCS_BUY;
            WS_OUT_DATA.WS_X_CCY_INFO[WS_I-1].WS_X_CCS_SELL = BPRTQP.CCS_SELL;
            WS_OUT_DATA.WS_X_CCY_INFO[WS_I-1].WS_X_CFX_BUY = BPRTQP.CFX_BUY;
            WS_OUT_DATA.WS_X_CCY_INFO[WS_I-1].WS_X_CFX_SELL = BPRTQP.CFX_SELL;
            WS_OUT_DATA.WS_X_CCY_INFO[WS_I-1].WS_X_EFF_DT = BPRTQP.KEY.EFF_DT;
            WS_OUT_DATA.WS_X_CCY_INFO[WS_I-1].WS_X_EFF_TM = BPRTQP.KEY.EFF_TM;
            T000_READNEXT_BPTTQP();
            CEP.TRC(SCCGWA, WS_I);
        }
        T000_ENDBR_BPTTQP();
        if (WS_NOTFND_FLAG == 'Y') {
            IBS.init(SCCGWA, SCCFMT);
            SCCFMT.FMTID = "BPX01";
            SCCFMT.DATA_PTR = WS_OUT_DATA;
            SCCFMT.DATA_LEN = 3489;
            IBS.FMT(SCCGWA, SCCFMT);
        }
    }
    public void T000_ENDBR_BPTTQP() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTTQP_BR);
    }
    public void T000_READNEXT_BPTTQP() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRTQP, this, BPTTQP_BR);
    }
    public void T000_STARTBR_BPTTQP() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "DEVHZ1301");
        CEP.TRC(SCCGWA, BPCMT.DATA.BR);
        CEP.TRC(SCCGWA, BPCMT.DATA.EXR_TYPE);
        IBS.init(SCCGWA, BPRTQP);
        BPRTQP.KEY.EXR_TYP = BPCMT.DATA.EXR_TYPE;
        BPRTQP.KEY.BR = BPCMT.DATA.BR;
        CEP.TRC(SCCGWA, BPRTQP.KEY.EXR_TYP);
        CEP.TRC(SCCGWA, BPRTQP.KEY.BR);
        BPTTQP_BR.rp = new DBParm();
        BPTTQP_BR.rp.TableName = "BPTTQP";
        BPTTQP_BR.rp.where = "EXR_TYP = :BPRTQP.KEY.EXR_TYP "
            + "AND BR = :BPRTQP.KEY.BR";
        IBS.STARTBR(SCCGWA, BPRTQP, this, BPTTQP_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void B005_BRW_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPREXRD);
        IBS.init(SCCGWA, BPCREXRS);
        BPCMT.DATA.OUT_REC_CNT = 0;
        WS_I = 0;
        IBS.init(SCCGWA, BPRTQP);
        BPREXRD.KEY.EXR_TYP = BPCMT.DATA.EXR_TYPE;
        if (BPCMT.DATA.CCY_INFO[1-1].CCY.trim().length() == 0) {
            BPREXRD.KEY.CCY = "%%%";
        } else {
            BPREXRD.KEY.CCY = BPCMT.DATA.CCY_INFO[1-1].CCY;
            WK_CCY = BPCMT.DATA.CCY_INFO[1-1].CCY;
        }
        if (BPCMT.DATA.CCY_INFO[1-1].FWD_TENOR.trim().length() == 0) {
            BPREXRD.KEY.FWD_TENOR = "%%%";
            CEP.TRC(SCCGWA, "DEVHZ08042");
        } else {
            BPREXRD.KEY.FWD_TENOR = BPCMT.DATA.CCY_INFO[1-1].FWD_TENOR;
            CEP.TRC(SCCGWA, "DEVHZ08043");
        }
        CEP.TRC(SCCGWA, BPREXRD.KEY.EXR_TYP);
        CEP.TRC(SCCGWA, BPREXRD.KEY.CCY);
        BPCREXRS.INFO.FUNC = '1';
        S000_CALL_BPZTEXRS();
        BPCREXRS.INFO.FUNC = 'R';
        S000_CALL_BPZTEXRS();
        if (BPREXRD.KEY.CCY.equalsIgnoreCase(WS_LOCAL_CCY)) {
            BPCREXRS.INFO.FUNC = 'R';
            S000_CALL_BPZTEXRS();
        }
        WS_I = 0;
        WS_READ_CNT = 0;
        while (BPCREXRS.INFO.RTN_INFO != 'N' 
            && WS_I <= K_MAX_CNT) {
            WS_READ_CNT += 1;
            CEP.TRC(SCCGWA, "DEVSOS");
            if (BPCMT.DATA.CONT_FLAG == 'Y' 
                && WS_READ_CNT <= BPCMT.DATA.CMPL_CNT) {
                CEP.TRC(SCCGWA, "DEVSOS2");
            } else {
                IBS.init(SCCGWA, BPCOIEC);
                BPCOIEC.CCY1 = BPREXRD.KEY.CCY;
                BPCOIEC.CCY2 = WS_LOCAL_CCY;
                BPCOIEC.EXR_TYP = BPREXRD.KEY.EXR_TYP;
                CEP.TRC(SCCGWA, BPCOIEC.CCY1);
                CEP.TRC(SCCGWA, BPCOIEC.CCY2);
                CEP.TRC(SCCGWA, BPCOIEC.EXR_TYP);
                S000_CALL_BPZSIEC();
                if (BPCOIEC.EXR_CODE == null) BPCOIEC.EXR_CODE = "";
                JIBS_tmp_int = BPCOIEC.EXR_CODE.length();
                for (int i=0;i<6-JIBS_tmp_int;i++) BPCOIEC.EXR_CODE += " ";
                CEP.TRC(SCCGWA, BPCOIEC.EXR_CODE.substring(0, 3));
                if (BPCOIEC.EXR_CODE == null) BPCOIEC.EXR_CODE = "";
                JIBS_tmp_int = BPCOIEC.EXR_CODE.length();
                for (int i=0;i<6-JIBS_tmp_int;i++) BPCOIEC.EXR_CODE += " ";
                CEP.TRC(SCCGWA, BPCOIEC.EXR_CODE.substring(4 - 1, 4 + 3 - 1));
                if (BPCOIEC.EXR_CODE == null) BPCOIEC.EXR_CODE = "";
                JIBS_tmp_int = BPCOIEC.EXR_CODE.length();
                for (int i=0;i<6-JIBS_tmp_int;i++) BPCOIEC.EXR_CODE += " ";
                if (BPCOIEC.EXR_CODE == null) BPCOIEC.EXR_CODE = "";
                JIBS_tmp_int = BPCOIEC.EXR_CODE.length();
                for (int i=0;i<6-JIBS_tmp_int;i++) BPCOIEC.EXR_CODE += " ";
                if (BPCOIEC.EXR_CODE.substring(0, 3).trim().length() > 0 
                    && BPCOIEC.EXR_CODE.substring(4 - 1, 4 + 3 - 1).trim().length() > 0) {
                    WS_I += 1;
                    if (WS_I <= K_MAX_CNT) {
                        BPCMT.DATA.OUT_REC_CNT += 1;
                        BPCMT.DATA.CCY_INFO[WS_I-1].FWD_TENOR = BPREXRD.KEY.FWD_TENOR;
                        BPCMT.DATA.CCY_INFO[WS_I-1].CCY = BPREXRD.KEY.CCY;
                        BPCMT.DATA.CCY_INFO[WS_I-1].CORR_CCY = BPREXRD.KEY.CORR_CCY;
                        BPCMT.DATA.CCY_INFO[WS_I-1].UNIT = BPREXRD.UNT;
                        IBS.init(SCCGWA, BPRTQPH);
                        IBS.init(SCCGWA, BPCRTQPH);
                        BPRTQPH.KEY.EXR_TYP = BPREXRD.KEY.EXR_TYP;
                        BPRTQPH.KEY.FWD_TENOR = BPREXRD.KEY.FWD_TENOR;
                        BPRTQPH.KEY.CCY = BPREXRD.KEY.CCY;
                        BPRTQPH.KEY.CORR_CCY = BPREXRD.KEY.CORR_CCY;
                        BPCRTQPH.STR_DATE = SCCGWA.COMM_AREA.AC_DATE;
                        BPCRTQPH.STR_TIME = K_MAX_TIME;
                        WS_MT_CCY = BPRTQPH.KEY.CCY;
                        WS_BPTTQP_FLG = 'N';
                        T000_READBYCCY_BPTTQP();
                        if (WS_BPTTQP_FLG == 'Y') {
                            BPCMT.DATA.CCY_INFO[WS_I-1].MID_RAT = WS_MT_MID_RAT;
                            BPCMT.DATA.CCY_INFO[WS_I-1].FX_BUY = WS_MT_FX_BUY;
                            BPCMT.DATA.CCY_INFO[WS_I-1].FX_SELL = WS_MT_FX_SELL;
                            BPCMT.DATA.CCY_INFO[WS_I-1].CS_BUY = WS_MT_CS_BUY;
                            BPCMT.DATA.CCY_INFO[WS_I-1].CS_SELL = WS_MT_CS_SELL;
                        }
                    }
                }
            }
            BPCREXRS.INFO.FUNC = 'R';
            S000_CALL_BPZTEXRS();
            if (BPREXRD.KEY.CCY.equalsIgnoreCase(WS_LOCAL_CCY)) {
                BPCREXRS.INFO.FUNC = 'R';
                S000_CALL_BPZTEXRS();
            }
        }
        BPCREXRS.INFO.FUNC = 'E';
        S000_CALL_BPZTEXRS();
        if (WS_I <= K_MAX_CNT) {
            BPCMT.DATA.CMPL_FLAG = 'Y';
        } else {
            BPCMT.DATA.CMPL_FLAG = 'N';
        }
        BPCMT.DATA.CMPL_CNT += BPCMT.DATA.OUT_REC_CNT;
        if (BPCMT.DATA.OUT_REC_CNT == 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_RATE_NOT_INP_GEN);
        } else {
            IBS.init(SCCGWA, WS_OUT_DATA);
            WS_OUT_DATA.WS_X_BR = BPCMT.DATA.BR;
            WS_OUT_DATA.WS_X_EXR_TYPE = BPCMT.DATA.EXR_TYPE;
            for (WS_I = 1; WS_I <= BPCMT.DATA.OUT_REC_CNT; WS_I += 1) {
                WS_OUT_DATA.WS_X_CCY_INFO[WS_I-1].WS_X_CCY = BPCMT.DATA.CCY_INFO[WS_I-1].CCY;
                WS_OUT_DATA.WS_X_CCY_INFO[WS_I-1].WS_X_UNIT = BPCMT.DATA.CCY_INFO[WS_I-1].UNIT;
                WS_OUT_DATA.WS_X_CCY_INFO[WS_I-1].WS_X_RECENT_MID_RAT = BPCMT.DATA.CCY_INFO[WS_I-1].RECENT_MID_RAT;
                WS_OUT_DATA.WS_X_CCY_INFO[WS_I-1].WS_X_MID_RAT = BPCMT.DATA.CCY_INFO[WS_I-1].MID_RAT;
                WS_OUT_DATA.WS_X_CCY_INFO[WS_I-1].WS_X_FX_BUY = BPCMT.DATA.CCY_INFO[WS_I-1].FX_BUY;
                WS_OUT_DATA.WS_X_CCY_INFO[WS_I-1].WS_X_FX_SELL = BPCMT.DATA.CCY_INFO[WS_I-1].FX_SELL;
                WS_OUT_DATA.WS_X_CCY_INFO[WS_I-1].WS_X_CS_BUY = BPCMT.DATA.CCY_INFO[WS_I-1].CS_BUY;
                WS_OUT_DATA.WS_X_CCY_INFO[WS_I-1].WS_X_CS_SELL = BPCMT.DATA.CCY_INFO[WS_I-1].CS_SELL;
                WS_OUT_DATA.WS_X_CCY_INFO[WS_I-1].WS_X_CCS_BUY = BPCMT.DATA.CCY_INFO[WS_I-1].CCS_BUY;
                WS_OUT_DATA.WS_X_CCY_INFO[WS_I-1].WS_X_CCS_SELL = BPCMT.DATA.CCY_INFO[WS_I-1].CCS_SELL;
                WS_OUT_DATA.WS_X_CCY_INFO[WS_I-1].WS_X_CFX_BUY = BPCMT.DATA.CCY_INFO[WS_I-1].CFX_BUY;
                WS_OUT_DATA.WS_X_CCY_INFO[WS_I-1].WS_X_CFX_SELL = BPCMT.DATA.CCY_INFO[WS_I-1].CFX_SELL;
                WS_OUT_DATA.WS_X_CCY_INFO[WS_I-1].WS_X_EFF_DT = BPCMT.DATA.EFF_DT;
                WS_OUT_DATA.WS_X_CCY_INFO[WS_I-1].WS_X_EFF_TM = BPCMT.DATA.EFF_TM;
            }
            IBS.init(SCCGWA, SCCFMT);
            SCCFMT.FMTID = "BPX01";
            SCCFMT.DATA_PTR = WS_OUT_DATA;
            SCCFMT.DATA_LEN = 3489;
            IBS.FMT(SCCGWA, SCCFMT);
        }
    }
    public void R000_CHECK_BRANCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = WS_BR;
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void B007_GEN_OTH_RATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCGTQP);
        BPCGTQP.FUNC = 'A';
        BPCGTQP.EFF_DATE = BPCMT.DATA.EFF_DT;
        BPCGTQP.EFF_TIME = BPCMT.DATA.EFF_TM;
        for (WS_I = 1; WS_I <= K_MAX_CNT; WS_I += 1) {
            if (BPCMT.DATA.CCY_INFO[WS_I-1].CCY.trim().length() > 0) {
                BPCGTQP.MKT_RAT_INFO[WS_I-1].EXR_TYP = BPCMT.DATA.EXR_TYPE;
                BPCGTQP.MKT_RAT_INFO[WS_I-1].TENOR = BPCMT.DATA.CCY_INFO[WS_I-1].FWD_TENOR;
                BPCGTQP.MKT_RAT_INFO[WS_I-1].CCY = BPCMT.DATA.CCY_INFO[WS_I-1].CCY;
                BPCGTQP.MKT_RAT_INFO[WS_I-1].CORR_CCY = BPCMT.DATA.CCY_INFO[WS_I-1].CORR_CCY;
                BPCGTQP.MKT_RAT_INFO[WS_I-1].BUY_RAT = BPCMT.DATA.CCY_INFO[WS_I-1].FX_BUY;
                BPCGTQP.MKT_RAT_INFO[WS_I-1].SELL_RAT = BPCMT.DATA.CCY_INFO[WS_I-1].FX_SELL;
                BPCGTQP.MKT_RAT_INFO[WS_I-1].MID_RAT = BPCMT.DATA.CCY_INFO[WS_I-1].MID_RAT;
                BPCGTQP.MKT_RAT_INFO[WS_I-1].UNIT = BPCMT.DATA.CCY_INFO[WS_I-1].UNIT;
                CEP.TRC(SCCGWA, WS_I);
            }
        }
        CEP.TRC(SCCGWA, "CALL BPZGTQP START ");
        S000_CALL_BPZGTQP();
        CEP.TRC(SCCGWA, "CALL BPZGTQP END   ");
        CEP.TRC(SCCGWA, BPCGTQP.RC_CODE);
        if (BPCGTQP.RC_CODE != 0) {
            WS_ERR_MSG = "" + BPCGTQP.RC_CODE;
            JIBS_tmp_int = WS_ERR_MSG.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_ERR_MSG = "0" + WS_ERR_MSG;
            S000_ERR_MSG_PROC();
        }
    }
    public void R000_CHECK_CCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = WS_CCY;
        IBS.CALLCPN(SCCGWA, CPN_INQUIRE_CCY, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void R000_CHECK_FWD_TENOR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = K_FWD_TENOR;
        BPCOQPCD.INPUT_DATA.CODE = WS_FWD_TENOR;
        CEP.TRC(SCCGWA, BPCOQPCD.INPUT_DATA.TYPE);
        CEP.TRC(SCCGWA, BPCOQPCD.INPUT_DATA.CODE);
        IBS.CALLCPN(SCCGWA, CPN_INQ_PUB_CODE, BPCOQPCD);
        if (BPCOQPCD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZSIEC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQ-EXR-CODE", BPCOIEC);
        CEP.TRC(SCCGWA, BPCOIEC);
    }
    public void B008_WRITE_BPTPNHIS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "WRITE-BPTPNHIS");
        BPCPNHIS.INFO.REF_NO = "TBL-BPTTQP";
        BPCPNHIS.INFO.FMT_ID = "BPCMT";
        S000_CALL_BPZPNHIS();
        CEP.TRC(SCCGWA, "LINK-BPZPNHIS-OK");
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZTTQPM() throws IOException,SQLException,Exception {
        BPCTTQPM.POINTER = BPRTQP;
        BPCTTQPM.LEN = 401;
        IBS.CALLCPN(SCCGWA, CPN_R_TQP, BPCTTQPM);
        CEP.TRC(SCCGWA, BPCTTQPM.RETURN_INFO);
        CEP.TRC(SCCGWA, BPCTTQPM.RC);
    }
    public void S000_CALL_BPZTTQPS() throws IOException,SQLException,Exception {
        BPCRTQPS.INFO.POINTER = BPRTQP;
        BPCRTQPS.INFO.LEN = 401;
        IBS.CALLCPN(SCCGWA, CPN_R_TQP_B, BPCRTQPS);
    }
    public void S000_CALL_BPZTTQPH() throws IOException,SQLException,Exception {
        BPCRTQPH.INFO.POINTER = BPRTQPH;
        BPCRTQPH.INFO.LEN = 412;
        IBS.CALLCPN(SCCGWA, CPN_R_TQPH_B, BPCRTQPH);
        CEP.TRC(SCCGWA, BPCRTQPH.RC);
        CEP.TRC(SCCGWA, BPCRTQPH.INFO.RTN_INFO);
    }
    public void S000_CALL_BPZGTQP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BP_GEN_TQP, BPCGTQP);
    }
    public void S000_CALL_BPZTQPHM() throws IOException,SQLException,Exception {
        BPCTQPHM.INFO.POINTER = BPRTQPH;
        BPCTQPHM.INFO.LEN = 412;
        IBS.CALLCPN(SCCGWA, CPN_R_TQPH, BPCTQPHM);
        CEP.TRC(SCCGWA, BPCTQPHM.RC);
        CEP.TRC(SCCGWA, BPCTQPHM.RETURN_INFO);
        CEP.TRC(SCCGWA, BPCTQPHM.INFO.FUNC);
    }
    public void S000_CALL_BPZTEXRS() throws IOException,SQLException,Exception {
        BPCREXRS.INFO.POINTER = BPREXRD;
        BPCREXRS.INFO.LEN = 259;
        IBS.CALLCPN(SCCGWA, CPN_R_EXRD, BPCREXRS);
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_INQ_PUB_PARM, BPCPRMR);
    }
    public void S000_CALL_BPZCEUA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_CHK_UPD_AUTH, BPCCEUA);
    }
    public void S000_CALL_BPZTEXRM() throws IOException,SQLException,Exception {
        BPCTEXRM.POINTER = BPREXRD;
        BPCTEXRM.LEN = 259;
        IBS.CALLCPN(SCCGWA, CPN_R_EXRM, BPCTEXRM);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, BPCTEXRM.RC);
        CEP.TRC(SCCGWA, BPCTEXRM.RETURN_INFO);
    }
    public void S000_CALL_BPZTERGR() throws IOException,SQLException,Exception {
        BPCRERGR.INFO.POINTER = BPRERGR;
        BPCRERGR.INFO.LEN = 122;
        IBS.CALLCPN(SCCGWA, CPN_R_ERGR_B, BPCRERGR);
        if (BPCRERGR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRERGR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READFIRST_BPTTQP() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "READ FIRST");
        CEP.TRC(SCCGWA, BPRTQP.KEY.EXR_TYP);
        CEP.TRC(SCCGWA, BPRTQP.KEY.CCY);
        CEP.TRC(SCCGWA, BPRTQP.KEY.BR);
        CEP.TRC(SCCGWA, BPRTQP.KEY.EFF_DT);
        CEP.TRC(SCCGWA, BPRTQP.KEY.EFF_TM);
        BPTTQP_RD = new DBParm();
        BPTTQP_RD.TableName = "BPTTQP";
        BPTTQP_RD.where = "EXR_TYP = :BPRTQP.KEY.EXR_TYP "
            + "AND CCY = :BPRTQP.KEY.CCY "
            + "AND BR = :BPRTQP.KEY.BR "
            + "AND EFF_TM <= :BPRTQP.KEY.EFF_TM";
        BPTTQP_RD.fst = true;
        BPTTQP_RD.order = "EFF_TM DESC";
        IBS.READ(SCCGWA, BPRTQP, this, BPTTQP_RD);
    }
    public void T000_READBYCCY_BPTTQP() throws IOException,SQLException,Exception {
        BPRTQP.KEY.EXR_TYP = BPREXRD.KEY.EXR_TYP;
        BPRTQP.KEY.CCY = WS_MT_CCY;
        CEP.TRC(SCCGWA, "READ FIRST BEGIN");
        CEP.TRC(SCCGWA, BPRTQP.KEY.CCY);
        CEP.TRC(SCCGWA, BPRTQP.KEY.EXR_TYP);
        null.col = "CCY,LOC_MID,CS_BUY,FX_BUY,FX_SELL,CS_SELL";
        BPTTQP_RD = new DBParm();
        BPTTQP_RD.TableName = "BPTTQP";
        BPTTQP_RD.where = "EXR_TYP = :BPRTQP.KEY.EXR_TYP "
            + "AND CCY = :BPRTQP.KEY.CCY";
        BPTTQP_RD.fst = true;
        BPTTQP_RD.order = "EFF_TM DESC";
        IBS.READ(SCCGWA, BPRTQP, this, BPTTQP_RD);
        CEP.TRC(SCCGWA, "READ END");
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "ZHANYONG FOUND");
            CEP.TRC(SCCGWA, BPRTQP.LOC_MID);
            CEP.TRC(SCCGWA, BPRTQP.CS_BUY);
            CEP.TRC(SCCGWA, BPRTQP.FX_BUY);
            CEP.TRC(SCCGWA, BPRTQP.FX_SELL);
            CEP.TRC(SCCGWA, BPRTQP.CS_SELL);
            WS_MT_MID_RAT = BPRTQP.LOC_MID;
            WS_MT_CS_BUY = BPRTQP.CS_BUY;
            WS_MT_FX_BUY = BPRTQP.FX_BUY;
            WS_MT_FX_SELL = BPRTQP.FX_SELL;
            WS_MT_CS_SELL = BPRTQP.CS_SELL;
            WS_BPTTQP_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "ZHANYONG NOT FOUND");
        } else {
        }
    }
    public void T000_REUPDATE_BPTTQPH() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "REUPDATE");
        CEP.TRC(SCCGWA, BPRTQPH.KEY.EXR_TYP);
        CEP.TRC(SCCGWA, BPRTQPH.KEY.CCY);
        CEP.TRC(SCCGWA, WS_OLD_EXP_DT);
        CEP.TRC(SCCGWA, BPRTQPH.EFF_DT);
        CEP.TRC(SCCGWA, BPRTQPH.EFF_TM);
        BPTTQPH_RD = new DBParm();
        BPTTQPH_RD.TableName = "BPTTQPH";
        BPTTQPH_RD.col = "EXP_DT,EXP_TM";
        BPTTQPH_RD.where = "EXR_TYP = :BPRTQPH.KEY.EXR_TYP "
            + "AND CCY = :BPRTQPH.KEY.CCY "
            + "AND EXP_DT = :WS_OLD_EXP_DT "
            + "AND EFF_DT = :BPRTQPH.EFF_DT "
            + "AND EFF_TM = :BPRTQPH.EFF_TM";
        BPTTQPH_RD.upd = true;
        IBS.READ(SCCGWA, BPRTQPH, this, BPTTQPH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTQPHM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTQPHM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BPTTQPH() throws IOException,SQLException,Exception {
        BPTTQPH_RD = new DBParm();
        BPTTQPH_RD.TableName = "BPTTQPH";
        IBS.REWRITE(SCCGWA, BPRTQPH, BPTTQPH_RD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_LVL() throws IOException,SQLException,Exception {
        SCCMSG SCCMSG = new SCCMSG();
        IBS.init(SCCGWA, SCCMSG);
        SCCMSG.MSGID = WS_ERR_MSG;
        SCCMSG.TYPE = (char) 'A';
        SCCMSG.LVL.LVL1 = WS_AUTH_LVL.WS_AUTH_L1;
        SCCMSG.LVL.LVL2 = WS_AUTH_LVL.WS_AUTH_L2;
        CEP.ERR(SCCGWA, SCCMSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCMT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCMT = ");
            CEP.TRC(SCCGWA, BPCMT);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
