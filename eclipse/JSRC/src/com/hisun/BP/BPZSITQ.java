package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class BPZSITQ {
    boolean pgmRtn = false;
    String CPN_R_TQP = "BP-R-TQP-M        ";
    String CPN_R_TQPH = "BP-R-TQPH-B       ";
    String CPN_R_TQPS = "BP-R-TQP-B        ";
    String CPN_R_TEXRM = "BP-R-EXRD-M       ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG      ";
    String CPN_INQ_EXR_CODE = "BP-INQ-EXR-CODE   ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_INQ_PUB_PARM = "BP-PARM-READ      ";
    String CPN_INQ_PUB_CODE = "BP-P-INQ-PC       ";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String K_PARM_EXR_TYP = "EXRT";
    String K_PUB_CODE_FWDT = "FWDT";
    int K_MAX_DATE = 99991231;
    int K_MAX_TIME = 235959;
    char K_RND = '2';
    short K_PNT = 4;
    String WS_ERR_MSG = " ";
    short WS_I = 0;
    short WS_J = 0;
    short WS_READ_CNT = 0;
    int WS_BR = 0;
    String WS_CCY = " ";
    String WS_CORR_CCY = " ";
    String WS_BASE_CCY_BUY = " ";
    String WS_BASE_CCY_SELL = " ";
    String WS_BASE_CCY = " ";
    String WS_EXR_TYP = " ";
    String WS_FWD_TENOR = " ";
    int WS_DATE = 0;
    char WS_EXR_RND = ' ';
    short WS_EXR_PNT = 0;
    String WS_BUY_CCY = " ";
    double WS_CS_BUY1 = 0;
    double WS_CS_SELL1 = 0;
    double WS_FX_BUY1 = 0;
    double WS_FX_SELL1 = 0;
    double WS_CS_BUY2 = 0;
    double WS_CS_SELL2 = 0;
    double WS_FX_BUY2 = 0;
    double WS_FX_SELL2 = 0;
    double WS_LOC_MID1 = 0;
    double WS_LOC_MID2 = 0;
    BPZSITQ_WS_CALC_PRICE WS_CALC_PRICE = new BPZSITQ_WS_CALC_PRICE();
    char WS_TQPH1_FLG = ' ';
    char WS_TQPH2_FLG = ' ';
    char WS_TQP1_FLG = ' ';
    char WS_TQP2_FLG = ' ';
    BPZSITQ_WS_CALC_PRICE_AREA2 WS_CALC_PRICE_AREA2 = new BPZSITQ_WS_CALC_PRICE_AREA2();
    BPZSITQ_WS_FL_AREA WS_FL_AREA = new BPZSITQ_WS_FL_AREA();
    BPZSITQ_WS_OUT_AREA WS_OUT_AREA = new BPZSITQ_WS_OUT_AREA();
    BPZSITQ_WS_TRC_TXT WS_TRC_TXT = new BPZSITQ_WS_TRC_TXT();
    double WS_TMP_RATE = 0;
    char WS_B_CASH_FLG = ' ';
    char WS_S_CASH_FLG = ' ';
    BPZSITQ_WS_DEC_POINT WS_DEC_POINT = new BPZSITQ_WS_DEC_POINT();
    char WS_BUY_SELL_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRFWDT BPRFWDT = new BPRFWDT();
    BPREXRT BPREXRT = new BPREXRT();
    BPREXRT BPREXRT1 = new BPREXRT();
    BPREXRT BPREXRT2 = new BPREXRT();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPCOIEC BPCOIEC = new BPCOIEC();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCRTQPS BPCRTQPS = new BPCRTQPS();
    BPCRTQPH BPCRTQPH = new BPCRTQPH();
    BPCTTQPM BPCTTQPM = new BPCTTQPM();
    BPCTEXRM BPCTEXRM = new BPCTEXRM();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPRTQP BPRTQP = new BPRTQP();
    BPRTQP BPRTQP1 = new BPRTQP();
    BPRTQP BPRTQP2 = new BPRTQP();
    BPRTQPH BPRTQPH = new BPRTQPH();
    BPRTQPH BPRTQPH1 = new BPRTQPH();
    BPRTQPH BPRTQPH2 = new BPRTQPH();
    BPREXRD BPREXRD = new BPREXRD();
    BPREXRD BPREXRD1 = new BPREXRD();
    BPREXRD BPREXRD2 = new BPREXRD();
    SCCGWA SCCGWA;
    BPCITQ BPCITQ;
    public void MP(SCCGWA SCCGWA, BPCITQ BPCITQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCITQ = BPCITQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSITQ return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCITQ.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCITQ.DATA.BUY_INFO.BUY_CCY);
        CEP.TRC(SCCGWA, BPCITQ.DATA.BUY_INFO.BUY_TENOR);
        CEP.TRC(SCCGWA, BPCITQ.DATA.BUY_INFO.BUY_EXR_TYPE);
        CEP.TRC(SCCGWA, BPCITQ.DATA.SELL_INFO.SELL_EXR_TYPE);
        CEP.TRC(SCCGWA, BPCITQ.DATA.SELL_INFO.SELL_CCY);
        CEP.TRC(SCCGWA, BPCITQ.DATA.SELL_INFO.SELL_TENOR);
        CEP.TRC(SCCGWA, BPCITQ.DATA.EX_CODE);
        CEP.TRC(SCCGWA, BPCITQ.DATA.CONT_FLAG);
        CEP.TRC(SCCGWA, BPCITQ.DATA.CMPL_FLAG);
        CEP.TRC(SCCGWA, BPCITQ.DATA.EXR_FLG);
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_ITQ_PROC();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if ((BPCITQ.DATA.FUNC != 'B' 
            && BPCITQ.DATA.FUNC != 'T' 
            && BPCITQ.DATA.FUNC != 'L' 
            && BPCITQ.DATA.FUNC != 'H')) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCITQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCITQ.DATA.BR);
        if (BPCITQ.DATA.BR != 0) {
            WS_BR = BPCITQ.DATA.BR;
            R000_CHECK_BRANCH();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCITQ.DATA.STR_DT);
        if (BPCITQ.DATA.STR_DT == SCCGWA.COMM_AREA.AC_DATE) {
            BPCITQ.DATA.FUNC = 'T';
        }
        if (BPCITQ.DATA.B_CASH_FLG == ' ') {
            BPCITQ.DATA.B_CASH_FLG = 'N';
        }
        if (BPCITQ.DATA.S_CASH_FLG == ' ') {
            BPCITQ.DATA.S_CASH_FLG = 'N';
        }
        if ((BPCITQ.DATA.FUNC == 'T' 
            || BPCITQ.DATA.FUNC == 'L' 
            || BPCITQ.DATA.FUNC == 'H')) {
            B100_CHECK_INQ_INPUT();
            if (pgmRtn) return;
        } else {
            B100_CHECK_BRW_INPUT();
            if (pgmRtn) return;
        }
    }
    public void B100_CHECK_INQ_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCITQ.DATA.BUY_INFO.BUY_CCY);
        if (BPCITQ.DATA.BUY_INFO.BUY_CCY.trim().length() > 0) {
            WS_CCY = BPCITQ.DATA.BUY_INFO.BUY_CCY;
            R000_CHECK_CCY();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_IN_BUY_SELL_CCY, BPCITQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCITQ.DATA.SELL_INFO.SELL_CCY);
        if (BPCITQ.DATA.SELL_INFO.SELL_CCY.trim().length() > 0) {
            WS_CCY = BPCITQ.DATA.SELL_INFO.SELL_CCY;
            R000_CHECK_CCY();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_IN_BUY_SELL_CCY, BPCITQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCITQ.DATA.BUY_INFO.BUY_CCY.equalsIgnoreCase(BPCITQ.DATA.SELL_INFO.SELL_CCY)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SAME_CCY;
            Z_RET();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "EXCHANGE");
            CEP.TRC(SCCGWA, BPCITQ.DATA.BUY_INFO.BUY_CCY);
            CEP.TRC(SCCGWA, BPCITQ.DATA.SELL_INFO.SELL_CCY);
            IBS.init(SCCGWA, BPCOIEC);
            BPCOIEC.CCY1 = BPCITQ.DATA.BUY_INFO.BUY_CCY;
            BPCOIEC.CCY2 = BPCITQ.DATA.SELL_INFO.SELL_CCY;
            CEP.TRC(SCCGWA, BPCOIEC.CCY1);
            CEP.TRC(SCCGWA, BPCOIEC.CCY2);
            BPCOIEC.EXR_FLG = BPCITQ.DATA.EXR_FLG;
            BPCOIEC.EXR_TYP = BPCITQ.DATA.BUY_INFO.BUY_EXR_TYPE;
            S000_CALL_BPZSIEC();
            if (pgmRtn) return;
            BPCITQ.DATA.EX_CODE = BPCOIEC.EXR_CODE;
            CEP.TRC(SCCGWA, BPCOIEC.EXR_CODE);
        }
        CEP.TRC(SCCGWA, BPCITQ.DATA.BUY_INFO.BUY_EXR_TYPE);
        if (BPCITQ.DATA.BUY_INFO.BUY_EXR_TYPE.trim().length() > 0) {
            WS_EXR_TYP = BPCITQ.DATA.BUY_INFO.BUY_EXR_TYPE;
            R000_CHECK_EXR_TYPE();
            if (pgmRtn) return;
            WS_BASE_CCY_BUY = BPREXRT.DAT_TXT.BASE_CCY;
            if (BPREXRT.DAT_TXT.FWD_IND == '1' 
                && BPCITQ.DATA.BUY_INFO.BUY_TENOR.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_IN_FWD_TENOR, BPCITQ.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (BPREXRT.DAT_TXT.FWD_IND == '0' 
                && BPCITQ.DATA.BUY_INFO.BUY_TENOR.trim().length() > 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NONEED_FWD_TENOR, BPCITQ.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_IN_EXR_TYPE, BPCITQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCITQ.DATA.BUY_INFO.BUY_TENOR);
        if (BPCITQ.DATA.BUY_INFO.BUY_TENOR.trim().length() > 0) {
            WS_FWD_TENOR = BPCITQ.DATA.BUY_INFO.BUY_TENOR;
            R000_CHECK_FWD_TENOR();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCITQ.DATA.SELL_INFO.SELL_TENOR);
        if (BPCITQ.DATA.SELL_INFO.SELL_TENOR.trim().length() > 0) {
            WS_FWD_TENOR = BPCITQ.DATA.SELL_INFO.SELL_TENOR;
            R000_CHECK_FWD_TENOR();
            if (pgmRtn) return;
        } else {
            BPCITQ.DATA.SELL_INFO.SELL_TENOR = BPCITQ.DATA.BUY_INFO.BUY_TENOR;
        }
        if (!BPCITQ.DATA.SELL_INFO.SELL_TENOR.equalsIgnoreCase(BPCITQ.DATA.BUY_INFO.BUY_TENOR)) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_SAME_TENOR, BPCITQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCITQ.DATA.SELL_INFO.SELL_EXR_TYPE);
        if (BPCITQ.DATA.SELL_INFO.SELL_EXR_TYPE.trim().length() > 0 
            && !BPCITQ.DATA.SELL_INFO.SELL_EXR_TYPE.equalsIgnoreCase(BPCITQ.DATA.BUY_INFO.BUY_EXR_TYPE)) {
            WS_EXR_TYP = BPCITQ.DATA.SELL_INFO.SELL_EXR_TYPE;
            R000_CHECK_EXR_TYPE();
            if (pgmRtn) return;
            WS_BASE_CCY_SELL = BPREXRT.DAT_TXT.BASE_CCY;
            if (BPREXRT.DAT_TXT.FWD_IND == '1' 
                && BPCITQ.DATA.SELL_INFO.SELL_TENOR.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_IN_FWD_TENOR, BPCITQ.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (BPREXRT.DAT_TXT.FWD_IND == '0' 
                && BPCITQ.DATA.SELL_INFO.SELL_TENOR.trim().length() > 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NONEED_FWD_TENOR, BPCITQ.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
            BPCITQ.DATA.SELL_INFO.SELL_EXR_TYPE = BPCITQ.DATA.BUY_INFO.BUY_EXR_TYPE;
            WS_BASE_CCY_SELL = BPREXRT.DAT_TXT.BASE_CCY;
        }
        if (BPCITQ.DATA.BUY_INFO.BUY_CCY.equalsIgnoreCase(WS_BASE_CCY_SELL)) {
            WS_BASE_CCY = WS_BASE_CCY_SELL;
        }
        if (BPCITQ.DATA.SELL_INFO.SELL_CCY.equalsIgnoreCase(WS_BASE_CCY_BUY)) {
            WS_BASE_CCY = WS_BASE_CCY_BUY;
        }
        if (!WS_BASE_CCY_BUY.equalsIgnoreCase(WS_BASE_CCY_SELL) 
            && !BPCITQ.DATA.BUY_INFO.BUY_CCY.equalsIgnoreCase(WS_BASE_CCY_SELL) 
            && !BPCITQ.DATA.SELL_INFO.SELL_CCY.equalsIgnoreCase(WS_BASE_CCY_BUY)) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_SAME_BASE_CCY, BPCITQ.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            WS_BASE_CCY = WS_BASE_CCY_BUY;
        }
        if (!BPCITQ.DATA.BUY_INFO.BUY_CCY.equalsIgnoreCase(WS_BASE_CCY_BUY)) {
            IBS.init(SCCGWA, BPCTEXRM);
            IBS.init(SCCGWA, BPREXRD);
            BPREXRD.KEY.CCY = BPCITQ.DATA.BUY_INFO.BUY_CCY;
            BPREXRD.KEY.EXR_TYP = BPCITQ.DATA.BUY_INFO.BUY_EXR_TYPE;
            BPREXRD.KEY.FWD_TENOR = BPCITQ.DATA.BUY_INFO.BUY_TENOR;
            BPCTEXRM.INFO.FUNC = 'Q';
            S000_CALL_BPZTEXRM();
            if (pgmRtn) return;
            if (BPCITQ.DATA.EX_CODE == null) BPCITQ.DATA.EX_CODE = "";
            JIBS_tmp_int = BPCITQ.DATA.EX_CODE.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPCITQ.DATA.EX_CODE += " ";
            if (BPCITQ.DATA.BUY_INFO.BUY_CCY.equalsIgnoreCase(BPCITQ.DATA.EX_CODE.substring(0, 3))) {
                IBS.CLONE(SCCGWA, BPREXRD, BPREXRD1);
            } else {
                IBS.CLONE(SCCGWA, BPREXRD, BPREXRD2);
            }
        }
        if (!BPCITQ.DATA.SELL_INFO.SELL_CCY.equalsIgnoreCase(WS_BASE_CCY_SELL)) {
            IBS.init(SCCGWA, BPCTEXRM);
            IBS.init(SCCGWA, BPREXRD);
            BPREXRD.KEY.CCY = BPCITQ.DATA.SELL_INFO.SELL_CCY;
            BPREXRD.KEY.EXR_TYP = BPCITQ.DATA.SELL_INFO.SELL_EXR_TYPE;
            BPREXRD.KEY.FWD_TENOR = BPCITQ.DATA.SELL_INFO.SELL_TENOR;
            BPCTEXRM.INFO.FUNC = 'Q';
            S000_CALL_BPZTEXRM();
            if (pgmRtn) return;
            if (BPCITQ.DATA.EX_CODE == null) BPCITQ.DATA.EX_CODE = "";
            JIBS_tmp_int = BPCITQ.DATA.EX_CODE.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPCITQ.DATA.EX_CODE += " ";
            if (BPCITQ.DATA.SELL_INFO.SELL_CCY.equalsIgnoreCase(BPCITQ.DATA.EX_CODE.substring(0, 3))) {
                IBS.CLONE(SCCGWA, BPREXRD, BPREXRD1);
            } else {
                IBS.CLONE(SCCGWA, BPREXRD, BPREXRD2);
            }
        }
        CEP.TRC(SCCGWA, "BPREXRDJIJ");
        CEP.TRC(SCCGWA, BPREXRD1);
        CEP.TRC(SCCGWA, BPREXRD2);
        CEP.TRC(SCCGWA, BPCITQ.DATA.STR_DT);
        if (BPCITQ.DATA.STR_DT == 0) {
            if (BPCITQ.DATA.FUNC == 'T' 
                || BPCITQ.DATA.FUNC == 'L') {
                BPCITQ.DATA.STR_DT = SCCGWA.COMM_AREA.AC_DATE;
            }
            if (BPCITQ.DATA.FUNC == 'H') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_STR_DATE_ERR, BPCITQ.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
            WS_DATE = BPCITQ.DATA.STR_DT;
            R000_CHECK_DATE();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCITQ.DATA.STR_TM);
        if (BPCITQ.DATA.STR_TM == 0) {
            CEP.TRC(SCCGWA, "NULL STR TM");
            if (BPCITQ.DATA.FUNC == 'T') {
                BPCITQ.DATA.STR_TM = SCCGWA.COMM_AREA.TR_TIME;
            } else if (BPCITQ.DATA.FUNC == 'L') {
                BPCITQ.DATA.STR_TM = 0;
            } else if (BPCITQ.DATA.FUNC == 'H') {
                BPCITQ.DATA.STR_TM = K_MAX_TIME;
            } else {
            }
        }
        CEP.TRC(SCCGWA, BPCITQ.DATA.STR_DT);
        CEP.TRC(SCCGWA, BPCITQ.DATA.END_DT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, BPCITQ.DATA.STR_TM);
        if (BPCITQ.DATA.STR_DT > SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_DATE_GT_ACDATE, BPCITQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B100_CHECK_BRW_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCITQ.DATA.CCY_EXR_INFOR[1-1].CCY);
        if (BPCITQ.DATA.CCY_EXR_INFOR[1-1].CCY.trim().length() > 0) {
            WS_CCY = BPCITQ.DATA.CCY_EXR_INFOR[1-1].CCY;
            R000_CHECK_CCY();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCITQ.DATA.CCY_EXR_INFOR[1-1].EXR_TYPE);
        if (BPCITQ.DATA.CCY_EXR_INFOR[1-1].EXR_TYPE.trim().length() > 0) {
            WS_EXR_TYP = BPCITQ.DATA.CCY_EXR_INFOR[1-1].EXR_TYPE;
            R000_CHECK_EXR_TYPE();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, BPREXRT, BPREXRT1);
        }
        CEP.TRC(SCCGWA, BPCITQ.DATA.CCY_EXR_INFOR[1-1].FWD_TENOR);
        if (BPCITQ.DATA.CCY_EXR_INFOR[1-1].FWD_TENOR.trim().length() > 0) {
            WS_FWD_TENOR = BPCITQ.DATA.CCY_EXR_INFOR[1-1].FWD_TENOR;
            R000_CHECK_FWD_TENOR();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCITQ.DATA.CONT_FLAG);
        CEP.TRC(SCCGWA, BPCITQ.DATA.CMPL_CNT);
        if (BPCITQ.DATA.CONT_FLAG != ' ' 
            && BPCITQ.DATA.CONT_FLAG != 'Y') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CONT_FLG_BE_Y, BPCITQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCITQ.DATA.CONT_FLAG == 'Y' 
            && BPCITQ.DATA.CMPL_CNT == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_IN_REC_CNT, BPCITQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCITQ.DATA.STR_DT);
        if (BPCITQ.DATA.STR_DT != 0) {
            WS_DATE = BPCITQ.DATA.STR_DT;
            R000_CHECK_DATE();
            if (pgmRtn) return;
        } else {
            BPCITQ.DATA.STR_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        CEP.TRC(SCCGWA, BPCITQ.DATA.END_DT);
        if (BPCITQ.DATA.END_DT != 0) {
            WS_DATE = BPCITQ.DATA.STR_DT;
            R000_CHECK_DATE();
            if (pgmRtn) return;
        } else {
            BPCITQ.DATA.END_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        CEP.TRC(SCCGWA, BPCITQ.DATA.STR_DT);
        CEP.TRC(SCCGWA, BPCITQ.DATA.END_DT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (BPCITQ.DATA.STR_DT > SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_DATE_GT_ACDATE, BPCITQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCITQ.DATA.STR_DT > BPCITQ.DATA.END_DT) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_STR_DT_GT_END_DT, BPCITQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B200_ITQ_PROC() throws IOException,SQLException,Exception {
        if (BPCITQ.DATA.FUNC == 'B') {
            if (BPCITQ.DATA.CCY_EXR_INFOR[1-1].CCY.trim().length() > 0
                && BPCITQ.DATA.CCY_EXR_INFOR[1-1].EXR_TYPE.trim().length() > 0) {
                B210_BRW_PROC1();
                if (pgmRtn) return;
            } else if (BPCITQ.DATA.CCY_EXR_INFOR[1-1].CCY.trim().length() > 0
                && BPCITQ.DATA.CCY_EXR_INFOR[1-1].EXR_TYPE.trim().length() == 0) {
                B220_BRW_PROC2();
                if (pgmRtn) return;
            } else if (BPCITQ.DATA.CCY_EXR_INFOR[1-1].CCY.trim().length() == 0
                && BPCITQ.DATA.CCY_EXR_INFOR[1-1].EXR_TYPE.trim().length() > 0) {
                B230_BRW_PROC3();
                if (pgmRtn) return;
            } else if (BPCITQ.DATA.CCY_EXR_INFOR[1-1].CCY.trim().length() == 0
                && BPCITQ.DATA.CCY_EXR_INFOR[1-1].EXR_TYPE.trim().length() == 0) {
                B240_BRW_PROC4();
                if (pgmRtn) return;
            } else {
            }
        } else if (BPCITQ.DATA.FUNC == 'T') {
            B250_INQ_CURRENT_RATE();
            if (pgmRtn) return;
        } else if (BPCITQ.DATA.FUNC == 'L'
            || BPCITQ.DATA.FUNC == 'H') {
            B260_INQ_HISTROY_RATE();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B210_BRW_PROC1() throws IOException,SQLException,Exception {
        if (BPCITQ.DATA.STR_DT == SCCGWA.COMM_AREA.AC_DATE 
            && BPCITQ.DATA.END_DT == SCCGWA.COMM_AREA.AC_DATE 
            && BPCITQ.DATA.STR_TM == 0 
            && BPCITQ.DATA.END_TM == 0) {
            if (BPCITQ.DATA.CCY_EXR_INFOR[1-1].FWD_TENOR.trim().length() == 0) {
                B210_01_BRW_PROC1();
                if (pgmRtn) return;
            } else {
                B210_03_BRW_PROC1();
                if (pgmRtn) return;
            }
        } else {
            if (BPCITQ.DATA.BR != 0) {
                B210_02_BRW_PROC1();
                if (pgmRtn) return;
            } else {
                B210_04_BRW_PROC1();
                if (pgmRtn) return;
            }
        }
    }
    public void B210_01_BRW_PROC1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRTQPS);
        IBS.init(SCCGWA, BPRTQP);
        WS_I = 0;
        WS_READ_CNT = 0;
        CEP.TRC(SCCGWA, "START");
        CEP.TRC(SCCGWA, BPREXRT1.DAT_TXT.FWD_IND);
        CEP.TRC(SCCGWA, BPCITQ.DATA.CCY_EXR_INFOR[1-1].CCY);
        CEP.TRC(SCCGWA, BPCITQ.DATA.CCY_EXR_INFOR[1-1].EXR_TYPE);
        CEP.TRC(SCCGWA, BPCITQ.DATA.CCY_EXR_INFOR[1-1].FWD_TENOR);
        BPRTQP.KEY.CCY = BPCITQ.DATA.CCY_EXR_INFOR[1-1].CCY;
        BPRTQP.KEY.EXR_TYP = BPCITQ.DATA.CCY_EXR_INFOR[1-1].EXR_TYPE;
        CEP.TRC(SCCGWA, BPRTQP.KEY.CCY);
        CEP.TRC(SCCGWA, BPRTQP.KEY.EXR_TYP);
        CEP.TRC(SCCGWA, BPRTQP.KEY.FWD_TENOR);
        BPCRTQPS.INFO.FUNC = '1';
        S000_CALL_BPZTTQPS();
        if (pgmRtn) return;
        BPCRTQPS.INFO.FUNC = 'R';
        CEP.TRC(SCCGWA, "READ NEXT");
        CEP.TRC(SCCGWA, BPRTQP.KEY.CCY);
        CEP.TRC(SCCGWA, BPRTQP.KEY.EXR_TYP);
        CEP.TRC(SCCGWA, BPRTQP.KEY.FWD_TENOR);
        S000_CALL_BPZTTQPS();
        if (pgmRtn) return;
        while (BPCRTQPS.INFO.RTN_INFO != 'N' 
            && WS_I <= 10) {
            WS_READ_CNT += 1;
            if (BPCITQ.DATA.CONT_FLAG == 'Y' 
                && WS_READ_CNT <= BPCITQ.DATA.CMPL_CNT) {
            } else {
                WS_I += 1;
                if (WS_I <= 10) {
                    BPCITQ.DATA.OUT_REC_CNT += 1;
                    R000_OUTPUT_TQP();
                    if (pgmRtn) return;
                }
            }
            if (BPREXRT1.DAT_TXT.FWD_IND != '0') {
                BPCRTQPS.INFO.FUNC = 'R';
                S000_CALL_BPZTTQPS();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, "READ NEXT IN PERFORM");
                CEP.TRC(SCCGWA, BPRTQP.KEY.CCY);
                CEP.TRC(SCCGWA, BPRTQP.KEY.EXR_TYP);
                CEP.TRC(SCCGWA, BPRTQP.KEY.FWD_TENOR);
            } else {
                BPCRTQPS.INFO.RTN_INFO = 'N';
            }
        }
        BPCRTQPS.INFO.FUNC = 'E';
        S000_CALL_BPZTTQPS();
        if (pgmRtn) return;
        if (WS_I <= 10) {
            BPCITQ.DATA.CMPL_FLAG = 'Y';
            BPCITQ.DATA.CMPL_CNT += WS_I;
        } else {
            BPCITQ.DATA.CMPL_FLAG = 'N';
            WS_I -= 1;
            BPCITQ.DATA.CMPL_CNT += WS_I;
        }
        if (BPCITQ.DATA.OUT_REC_CNT == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOT_EXIST, BPCITQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B210_02_BRW_PROC1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRTQPH);
        IBS.init(SCCGWA, BPRTQPH);
        WS_I = 0;
        WS_READ_CNT = 0;
        BPRTQPH.KEY.BR = BPCITQ.DATA.BR;
        BPRTQPH.KEY.CCY = BPCITQ.DATA.CCY_EXR_INFOR[1-1].CCY;
        BPRTQPH.KEY.EXR_TYP = BPCITQ.DATA.CCY_EXR_INFOR[1-1].EXR_TYPE;
        BPRTQPH.KEY.FWD_TENOR = BPCITQ.DATA.CCY_EXR_INFOR[1-1].FWD_TENOR;
        BPCRTQPH.STR_DATE = BPCITQ.DATA.STR_DT;
        BPCRTQPH.END_DATE = BPCITQ.DATA.END_DT;
        BPCRTQPH.INFO.FUNC = '1';
        S000_CALL_BPZTTQPH();
        if (pgmRtn) return;
        BPCRTQPH.INFO.FUNC = 'R';
        S000_CALL_BPZTTQPH();
        if (pgmRtn) return;
        while (BPCRTQPH.INFO.RTN_INFO != 'N' 
            && WS_I <= 10) {
            WS_READ_CNT += 1;
            if (BPCITQ.DATA.CONT_FLAG == 'Y' 
                && WS_READ_CNT <= BPCITQ.DATA.CMPL_CNT) {
            } else {
                WS_I += 1;
                if (WS_I <= 10) {
                    BPCITQ.DATA.OUT_REC_CNT += 1;
                    R000_OUTPUT_TQPH();
                    if (pgmRtn) return;
                }
            }
            BPCRTQPH.INFO.FUNC = 'R';
            S000_CALL_BPZTTQPH();
            if (pgmRtn) return;
        }
        BPCRTQPH.INFO.FUNC = 'E';
        S000_CALL_BPZTTQPH();
        if (pgmRtn) return;
        if (WS_I <= 10) {
            BPCITQ.DATA.CMPL_FLAG = 'Y';
            BPCITQ.DATA.CMPL_CNT += WS_I;
        } else {
            BPCITQ.DATA.CMPL_FLAG = 'N';
            WS_I -= 1;
            BPCITQ.DATA.CMPL_CNT += WS_I;
        }
        if (BPCITQ.DATA.OUT_REC_CNT == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOT_EXIST, BPCITQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B210_03_BRW_PROC1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRTQPS);
        IBS.init(SCCGWA, BPRTQP);
        WS_I = 0;
        WS_READ_CNT = 0;
        BPRTQP.KEY.CCY = BPCITQ.DATA.CCY_EXR_INFOR[1-1].CCY;
        BPRTQP.KEY.EXR_TYP = BPCITQ.DATA.CCY_EXR_INFOR[1-1].EXR_TYPE;
        BPRTQP.KEY.FWD_TENOR = BPCITQ.DATA.CCY_EXR_INFOR[1-1].FWD_TENOR;
        BPCRTQPS.INFO.FUNC = '1';
        S000_CALL_BPZTTQPS();
        if (pgmRtn) return;
        BPCRTQPS.INFO.FUNC = 'R';
        S000_CALL_BPZTTQPS();
        if (pgmRtn) return;
        while (BPCRTQPS.INFO.RTN_INFO != 'N' 
            && WS_I <= 10) {
            WS_READ_CNT += 1;
            if (BPCITQ.DATA.CONT_FLAG == 'Y' 
                && WS_READ_CNT <= BPCITQ.DATA.CMPL_CNT) {
            } else {
                WS_I += 1;
                if (WS_I <= 10) {
                    BPCITQ.DATA.OUT_REC_CNT += 1;
                    R000_OUTPUT_TQP();
                    if (pgmRtn) return;
                }
            }
            BPCRTQPS.INFO.FUNC = 'R';
            S000_CALL_BPZTTQPS();
            if (pgmRtn) return;
        }
        BPCRTQPS.INFO.FUNC = 'E';
        S000_CALL_BPZTTQPS();
        if (pgmRtn) return;
        if (WS_I <= 10) {
            BPCITQ.DATA.CMPL_FLAG = 'Y';
            BPCITQ.DATA.CMPL_CNT += WS_I;
        } else {
            BPCITQ.DATA.CMPL_FLAG = 'N';
            WS_I -= 1;
            BPCITQ.DATA.CMPL_CNT += WS_I;
        }
        if (BPCITQ.DATA.OUT_REC_CNT == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOT_EXIST, BPCITQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B210_04_BRW_PROC1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRTQPH);
        IBS.init(SCCGWA, BPRTQPH);
        WS_I = 0;
        WS_READ_CNT = 0;
        BPRTQPH.KEY.CCY = BPCITQ.DATA.CCY_EXR_INFOR[1-1].CCY;
        BPRTQPH.KEY.EXR_TYP = BPCITQ.DATA.CCY_EXR_INFOR[1-1].EXR_TYPE;
        BPRTQPH.KEY.FWD_TENOR = BPCITQ.DATA.CCY_EXR_INFOR[1-1].FWD_TENOR;
        BPCRTQPH.STR_DATE = BPCITQ.DATA.STR_DT;
        BPCRTQPH.END_DATE = BPCITQ.DATA.END_DT;
        BPCRTQPH.INFO.FUNC = '1';
        S000_CALL_BPZTTQPH();
        if (pgmRtn) return;
        BPCRTQPH.INFO.FUNC = 'R';
        S000_CALL_BPZTTQPH();
        if (pgmRtn) return;
        while (BPCRTQPH.INFO.RTN_INFO != 'N' 
            && WS_I <= 10) {
            WS_READ_CNT += 1;
            if (BPCITQ.DATA.CONT_FLAG == 'Y' 
                && WS_READ_CNT <= BPCITQ.DATA.CMPL_CNT) {
            } else {
                WS_I += 1;
                if (WS_I <= 10) {
                    BPCITQ.DATA.OUT_REC_CNT += 1;
                    R000_OUTPUT_TQPH();
                    if (pgmRtn) return;
                }
            }
            BPCRTQPH.INFO.FUNC = 'R';
            S000_CALL_BPZTTQPH();
            if (pgmRtn) return;
        }
        BPCRTQPH.INFO.FUNC = 'E';
        S000_CALL_BPZTTQPH();
        if (pgmRtn) return;
        if (WS_I <= 10) {
            BPCITQ.DATA.CMPL_FLAG = 'Y';
            BPCITQ.DATA.CMPL_CNT += WS_I;
        } else {
            BPCITQ.DATA.CMPL_FLAG = 'N';
            WS_I -= 1;
            BPCITQ.DATA.CMPL_CNT += WS_I;
        }
        if (BPCITQ.DATA.OUT_REC_CNT == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOT_EXIST, BPCITQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B220_BRW_PROC2() throws IOException,SQLException,Exception {
        if (BPCITQ.DATA.STR_DT == SCCGWA.COMM_AREA.AC_DATE 
            && BPCITQ.DATA.END_DT == SCCGWA.COMM_AREA.AC_DATE 
            && BPCITQ.DATA.STR_TM == 0 
            && BPCITQ.DATA.END_TM == 0) {
            if (BPCITQ.DATA.CCY_EXR_INFOR[1-1].FWD_TENOR.trim().length() == 0) {
                B220_01_BRW_PROC2();
                if (pgmRtn) return;
            } else {
                B220_03_BRW_PROC2();
                if (pgmRtn) return;
            }
        } else {
            if (BPCITQ.DATA.BR != 0) {
                B220_02_BRW_PROC2();
                if (pgmRtn) return;
            } else {
                B220_04_BRW_PROC2();
                if (pgmRtn) return;
            }
        }
    }
    public void B220_01_BRW_PROC2() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRTQPS);
        IBS.init(SCCGWA, BPRTQP);
        WS_I = 0;
        WS_READ_CNT = 0;
        BPRTQP.KEY.CCY = BPCITQ.DATA.CCY_EXR_INFOR[1-1].CCY;
        BPCRTQPS.INFO.FUNC = '2';
        S000_CALL_BPZTTQPS();
        if (pgmRtn) return;
        BPCRTQPS.INFO.FUNC = 'R';
        S000_CALL_BPZTTQPS();
        if (pgmRtn) return;
        while (BPCRTQPS.INFO.RTN_INFO != 'N' 
            && WS_I <= 10) {
            WS_READ_CNT += 1;
            if (BPCITQ.DATA.CONT_FLAG == 'Y' 
                && WS_READ_CNT <= BPCITQ.DATA.CMPL_CNT) {
            } else {
                WS_I += 1;
                if (WS_I <= 10) {
                    BPCITQ.DATA.OUT_REC_CNT += 1;
                    R000_OUTPUT_TQP();
                    if (pgmRtn) return;
                }
            }
            BPCRTQPS.INFO.FUNC = 'R';
            S000_CALL_BPZTTQPS();
            if (pgmRtn) return;
        }
        BPCRTQPS.INFO.FUNC = 'E';
        S000_CALL_BPZTTQPS();
        if (pgmRtn) return;
        if (WS_I <= 10) {
            BPCITQ.DATA.CMPL_FLAG = 'Y';
            BPCITQ.DATA.CMPL_CNT += WS_I;
        } else {
            BPCITQ.DATA.CMPL_FLAG = 'N';
            WS_I -= 1;
            BPCITQ.DATA.CMPL_CNT += WS_I;
        }
        if (BPCITQ.DATA.OUT_REC_CNT == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOT_EXIST, BPCITQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B220_02_BRW_PROC2() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRTQPH);
        IBS.init(SCCGWA, BPRTQPH);
        WS_I = 0;
        WS_READ_CNT = 0;
        BPRTQPH.KEY.BR = BPCITQ.DATA.BR;
        BPRTQPH.KEY.CCY = BPCITQ.DATA.CCY_EXR_INFOR[1-1].CCY;
        BPCRTQPH.STR_DATE = BPCITQ.DATA.STR_DT;
        BPCRTQPH.END_DATE = BPCITQ.DATA.END_DT;
        BPCRTQPH.INFO.FUNC = '2';
        S000_CALL_BPZTTQPH();
        if (pgmRtn) return;
        BPCRTQPH.INFO.FUNC = 'R';
        S000_CALL_BPZTTQPH();
        if (pgmRtn) return;
        while (BPCRTQPH.INFO.RTN_INFO != 'N' 
            && WS_I <= 10) {
            WS_READ_CNT += 1;
            if (BPCITQ.DATA.CONT_FLAG == 'Y' 
                && WS_READ_CNT <= BPCITQ.DATA.CMPL_CNT) {
            } else {
                WS_I += 1;
                if (WS_I <= 10) {
                    BPCITQ.DATA.OUT_REC_CNT += 1;
                    R000_OUTPUT_TQPH();
                    if (pgmRtn) return;
                }
            }
            BPCRTQPH.INFO.FUNC = 'R';
            S000_CALL_BPZTTQPH();
            if (pgmRtn) return;
        }
        BPCRTQPH.INFO.FUNC = 'E';
        S000_CALL_BPZTTQPH();
        if (pgmRtn) return;
        if (WS_I <= 10) {
            BPCITQ.DATA.CMPL_FLAG = 'Y';
            BPCITQ.DATA.CMPL_CNT += WS_I;
        } else {
            BPCITQ.DATA.CMPL_FLAG = 'N';
            WS_I -= 1;
            BPCITQ.DATA.CMPL_CNT += WS_I;
        }
        if (BPCITQ.DATA.OUT_REC_CNT == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOT_EXIST, BPCITQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B220_03_BRW_PROC2() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRTQPS);
        IBS.init(SCCGWA, BPRTQP);
        WS_I = 0;
        WS_READ_CNT = 0;
        BPRTQP.KEY.CCY = BPCITQ.DATA.CCY_EXR_INFOR[1-1].CCY;
        BPRTQP.KEY.EXR_TYP = "%%%";
        BPRTQP.KEY.FWD_TENOR = BPCITQ.DATA.CCY_EXR_INFOR[1-1].FWD_TENOR;
        BPCRTQPS.INFO.FUNC = '1';
        S000_CALL_BPZTTQPS();
        if (pgmRtn) return;
        BPCRTQPS.INFO.FUNC = 'R';
        S000_CALL_BPZTTQPS();
        if (pgmRtn) return;
        while (BPCRTQPS.INFO.RTN_INFO != 'N' 
            && WS_I <= 10) {
            WS_READ_CNT += 1;
            if (BPCITQ.DATA.CONT_FLAG == 'Y' 
                && WS_READ_CNT <= BPCITQ.DATA.CMPL_CNT) {
            } else {
                WS_I += 1;
                if (WS_I <= 10) {
                    BPCITQ.DATA.OUT_REC_CNT += 1;
                    R000_OUTPUT_TQP();
                    if (pgmRtn) return;
                }
            }
            BPCRTQPS.INFO.FUNC = 'R';
            S000_CALL_BPZTTQPS();
            if (pgmRtn) return;
        }
        BPCRTQPS.INFO.FUNC = 'E';
        S000_CALL_BPZTTQPS();
        if (pgmRtn) return;
        if (WS_I <= 10) {
            BPCITQ.DATA.CMPL_FLAG = 'Y';
            BPCITQ.DATA.CMPL_CNT += WS_I;
        } else {
            BPCITQ.DATA.CMPL_FLAG = 'N';
            WS_I -= 1;
            BPCITQ.DATA.CMPL_CNT += WS_I;
        }
        if (BPCITQ.DATA.OUT_REC_CNT == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOT_EXIST, BPCITQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B220_04_BRW_PROC2() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRTQPH);
        IBS.init(SCCGWA, BPRTQPH);
        WS_I = 0;
        WS_READ_CNT = 0;
        BPRTQPH.KEY.CCY = BPCITQ.DATA.CCY_EXR_INFOR[1-1].CCY;
        BPRTQPH.KEY.EXR_TYP = "%%%";
        BPRTQPH.KEY.FWD_TENOR = BPCITQ.DATA.CCY_EXR_INFOR[1-1].FWD_TENOR;
        BPCRTQPH.STR_DATE = BPCITQ.DATA.STR_DT;
        BPCRTQPH.END_DATE = BPCITQ.DATA.END_DT;
        BPCRTQPH.INFO.FUNC = '1';
        S000_CALL_BPZTTQPH();
        if (pgmRtn) return;
        BPCRTQPH.INFO.FUNC = 'R';
        S000_CALL_BPZTTQPH();
        if (pgmRtn) return;
        while (BPCRTQPH.INFO.RTN_INFO != 'N' 
            && WS_I <= 10) {
            WS_READ_CNT += 1;
            if (BPCITQ.DATA.CONT_FLAG == 'Y' 
                && WS_READ_CNT <= BPCITQ.DATA.CMPL_CNT) {
            } else {
                WS_I += 1;
                if (WS_I <= 10) {
                    BPCITQ.DATA.OUT_REC_CNT += 1;
                    R000_OUTPUT_TQPH();
                    if (pgmRtn) return;
                }
            }
            BPCRTQPH.INFO.FUNC = 'R';
            S000_CALL_BPZTTQPH();
            if (pgmRtn) return;
        }
        BPCRTQPH.INFO.FUNC = 'E';
        S000_CALL_BPZTTQPH();
        if (pgmRtn) return;
        if (WS_I <= 10) {
            BPCITQ.DATA.CMPL_FLAG = 'Y';
            BPCITQ.DATA.CMPL_CNT += WS_I;
        } else {
            BPCITQ.DATA.CMPL_FLAG = 'N';
            WS_I -= 1;
            BPCITQ.DATA.CMPL_CNT += WS_I;
        }
        if (BPCITQ.DATA.OUT_REC_CNT == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOT_EXIST, BPCITQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B230_BRW_PROC3() throws IOException,SQLException,Exception {
        if (BPCITQ.DATA.BUY_INFO.BUY_CCY.trim().length() > 0 
            || BPCITQ.DATA.SELL_INFO.SELL_CCY.trim().length() > 0 
            || (BPCITQ.DATA.BUY_INFO.BUY_CCY.trim().length() > 0 
            && BPCITQ.DATA.SELL_INFO.SELL_CCY.trim().length() > 0)) {
            IBS.init(SCCGWA, BPCTEXRM);
            IBS.init(SCCGWA, BPREXRD);
            BPREXRD.KEY.EXR_TYP = BPCITQ.DATA.CCY_EXR_INFOR[1-1].EXR_TYPE;
            BPREXRD.KEY.FWD_TENOR = BPCITQ.DATA.CCY_EXR_INFOR[1-1].FWD_TENOR;
            if ((!BPCITQ.DATA.BUY_INFO.BUY_CCY.equalsIgnoreCase(BPREXRT1.DAT_TXT.BASE_CCY) 
                && BPCITQ.DATA.SELL_INFO.SELL_CCY.equalsIgnoreCase(BPREXRT1.DAT_TXT.BASE_CCY)) 
                || (BPCITQ.DATA.BUY_INFO.BUY_CCY.equalsIgnoreCase(BPREXRT1.DAT_TXT.BASE_CCY) 
                && !BPCITQ.DATA.SELL_INFO.SELL_CCY.equalsIgnoreCase(BPREXRT1.DAT_TXT.BASE_CCY))) {
                if (!BPCITQ.DATA.BUY_INFO.BUY_CCY.equalsIgnoreCase(BPREXRT1.DAT_TXT.BASE_CCY)) {
                    BPREXRD.KEY.CCY = BPCITQ.DATA.BUY_INFO.BUY_CCY;
                } else {
                    BPREXRD.KEY.CCY = BPCITQ.DATA.SELL_INFO.SELL_CCY;
                }
                BPCTEXRM.INFO.FUNC = 'Q';
                S000_CALL_BPZTEXRM();
                if (pgmRtn) return;
                if (BPCTEXRM.RETURN_INFO == 'N') {
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_EX_RATE_UNDEFINE, BPCITQ.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            } else {
                BPREXRD.KEY.CCY = BPCITQ.DATA.BUY_INFO.BUY_CCY;
                BPCTEXRM.INFO.FUNC = 'Q';
                S000_CALL_BPZTEXRM();
                if (pgmRtn) return;
                if (BPCTEXRM.RETURN_INFO == 'N') {
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_EX_RATE_UNDEFINE, BPCITQ.RC);
                    Z_RET();
                    if (pgmRtn) return;
                } else {
                    WS_EXR_RND = BPREXRD.EXR_RND;
                    if (BPREXRD.EXR_PNT == ' ') WS_EXR_PNT = 0;
                    else WS_EXR_PNT = Short.parseShort(""+BPREXRD.EXR_PNT);
                }
                if (BPREXRD.METH == '0') {
                    WS_TQP1_FLG = 'D';
                } else {
                    WS_TQP1_FLG = 'I';
                }
                BPREXRD.KEY.CCY = BPCITQ.DATA.SELL_INFO.SELL_CCY;
                BPCTEXRM.INFO.FUNC = 'Q';
                S000_CALL_BPZTEXRM();
                if (pgmRtn) return;
                if (BPCTEXRM.RETURN_INFO == 'N') {
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_EX_RATE_UNDEFINE, BPCITQ.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                if (BPREXRD.METH == '0') {
                    WS_TQP2_FLG = 'D';
                } else {
                    WS_TQP2_FLG = 'I';
                }
            }
        }
        if (BPCITQ.DATA.STR_DT == SCCGWA.COMM_AREA.AC_DATE 
            && BPCITQ.DATA.END_DT == SCCGWA.COMM_AREA.AC_DATE 
            && BPCITQ.DATA.BUY_INFO.BUY_CCY.trim().length() > 0 
            && BPCITQ.DATA.SELL_INFO.SELL_CCY.trim().length() > 0 
            && BPCITQ.DATA.CCY_EXR_INFOR[1-1].FWD_TENOR.trim().length() > 0 
            && BPCITQ.DATA.STR_TM == 0 
            && BPCITQ.DATA.END_TM == 0) {
            B230_01_BRW_PROC3();
            if (pgmRtn) return;
        } else {
            if (BPCITQ.DATA.CCY_EXR_INFOR[1-1].FWD_TENOR.trim().length() == 0) {
                if (BPCITQ.DATA.STR_DT == SCCGWA.COMM_AREA.AC_DATE 
                    && BPCITQ.DATA.END_DT == SCCGWA.COMM_AREA.AC_DATE 
                    && BPCITQ.DATA.STR_TM == 0 
                    && BPCITQ.DATA.END_TM == 0) {
                    B230_02_BRW_PROC3();
                    if (pgmRtn) return;
                } else {
                    B230_03_BRW_PROC3();
                    if (pgmRtn) return;
                }
            } else {
                if (BPREXRT1.DAT_TXT.FWD_IND == '1') {
                    if (BPCITQ.DATA.STR_DT == SCCGWA.COMM_AREA.AC_DATE 
                        && BPCITQ.DATA.END_DT == SCCGWA.COMM_AREA.AC_DATE 
                        && BPCITQ.DATA.STR_TM == 0 
                        && BPCITQ.DATA.END_TM == 0) {
                        B230_04_BRW_PROC3();
                        if (pgmRtn) return;
                    } else {
                        B230_05_BRW_PROC3();
                        if (pgmRtn) return;
                    }
                }
            }
        }
    }
    public void B230_01_BRW_PROC3() throws IOException,SQLException,Exception {
        if ((!BPCITQ.DATA.BUY_INFO.BUY_CCY.equalsIgnoreCase(BPREXRT1.DAT_TXT.BASE_CCY) 
            && BPCITQ.DATA.SELL_INFO.SELL_CCY.equalsIgnoreCase(BPREXRT1.DAT_TXT.BASE_CCY)) 
            || (BPCITQ.DATA.BUY_INFO.BUY_CCY.equalsIgnoreCase(BPREXRT1.DAT_TXT.BASE_CCY) 
            && !BPCITQ.DATA.SELL_INFO.SELL_CCY.equalsIgnoreCase(BPREXRT1.DAT_TXT.BASE_CCY))) {
            IBS.init(SCCGWA, BPCRTQPS);
            IBS.init(SCCGWA, BPRTQP);
            WS_I = 0;
            WS_READ_CNT = 0;
            BPRTQP.KEY.CCY = BPREXRD.KEY.CCY;
            BPRTQP.KEY.EXR_TYP = BPCITQ.DATA.CCY_EXR_INFOR[1-1].EXR_TYPE;
            BPRTQP.KEY.FWD_TENOR = BPCITQ.DATA.CCY_EXR_INFOR[1-1].FWD_TENOR;
            BPCRTQPS.INFO.FUNC = '3';
            S000_CALL_BPZTTQPS();
            if (pgmRtn) return;
            BPCRTQPS.INFO.FUNC = 'R';
            S000_CALL_BPZTTQPS();
            if (pgmRtn) return;
            while (BPCRTQPS.INFO.RTN_INFO != 'N' 
                && WS_I <= 10) {
                WS_READ_CNT += 1;
                if (BPCITQ.DATA.CONT_FLAG == 'Y' 
                    && WS_READ_CNT <= BPCITQ.DATA.CMPL_CNT) {
                } else {
                    WS_I += 1;
                    if (WS_I <= 10) {
                        BPCITQ.DATA.OUT_REC_CNT += 1;
                        R000_OUTPUT_TQP();
                        if (pgmRtn) return;
                    }
                }
                BPCRTQPS.INFO.FUNC = 'R';
                S000_CALL_BPZTTQPS();
                if (pgmRtn) return;
            }
            BPCRTQPS.INFO.FUNC = 'E';
            S000_CALL_BPZTTQPS();
            if (pgmRtn) return;
            if (WS_I <= 10) {
                BPCITQ.DATA.CMPL_FLAG = 'Y';
                BPCITQ.DATA.CMPL_CNT += WS_I;
            } else {
                BPCITQ.DATA.CMPL_FLAG = 'N';
                WS_I -= 1;
                BPCITQ.DATA.CMPL_CNT += WS_I;
            }
            if (BPCITQ.DATA.OUT_REC_CNT == 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOT_EXIST, BPCITQ.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
            IBS.init(SCCGWA, BPCRTQPS);
            IBS.init(SCCGWA, BPRTQP);
            WS_I = 0;
            WS_READ_CNT = 0;
            if (BPCOIEC.EXR_CODE == null) BPCOIEC.EXR_CODE = "";
            JIBS_tmp_int = BPCOIEC.EXR_CODE.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPCOIEC.EXR_CODE += " ";
            BPRTQP.KEY.CCY = BPCOIEC.EXR_CODE.substring(0, 3);
            BPRTQP.KEY.EXR_TYP = BPCITQ.DATA.CCY_EXR_INFOR[1-1].EXR_TYPE;
            BPRTQP.KEY.FWD_TENOR = BPCITQ.DATA.CCY_EXR_INFOR[1-1].FWD_TENOR;
            BPCRTQPS.STR_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCRTQPS.STR_TIME = SCCGWA.COMM_AREA.TR_TIME;
            BPCRTQPS.INFO.FUNC = '8';
            S000_CALL_BPZTTQPS();
            if (pgmRtn) return;
            BPCRTQPS.INFO.FUNC = 'R';
            S000_CALL_BPZTTQPS();
            if (pgmRtn) return;
            while (BPCRTQPS.INFO.RTN_INFO != 'N' 
                && WS_I <= 10) {
                WS_READ_CNT += 1;
                if (BPCITQ.DATA.CONT_FLAG == 'Y' 
                    && WS_READ_CNT <= BPCITQ.DATA.CMPL_CNT) {
                } else {
                    WS_I += 1;
                    if (WS_I <= 10) {
                        BPCITQ.DATA.OUT_REC_CNT += 1;
                        IBS.CLONE(SCCGWA, BPRTQP, BPRTQP1);
                    }
                }
                BPCRTQPS.INFO.FUNC = 'R';
                S000_CALL_BPZTTQPS();
                if (pgmRtn) return;
            }
            BPCRTQPS.INFO.FUNC = 'E';
            S000_CALL_BPZTTQPS();
            if (pgmRtn) return;
            IBS.init(SCCGWA, BPCRTQPS);
            IBS.init(SCCGWA, BPRTQP);
            WS_I = 0;
            WS_READ_CNT = 0;
            if (BPCOIEC.EXR_CODE == null) BPCOIEC.EXR_CODE = "";
            JIBS_tmp_int = BPCOIEC.EXR_CODE.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPCOIEC.EXR_CODE += " ";
            BPRTQP.KEY.CCY = BPCOIEC.EXR_CODE.substring(4 - 1, 4 + 3 - 1);
            BPRTQP.KEY.EXR_TYP = BPCITQ.DATA.CCY_EXR_INFOR[1-1].EXR_TYPE;
            BPRTQP.KEY.FWD_TENOR = BPCITQ.DATA.CCY_EXR_INFOR[1-1].FWD_TENOR;
            BPCRTQPS.STR_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCRTQPS.STR_TIME = SCCGWA.COMM_AREA.TR_TIME;
            BPCRTQPS.INFO.FUNC = '8';
            S000_CALL_BPZTTQPS();
            if (pgmRtn) return;
            BPCRTQPS.INFO.FUNC = 'R';
            S000_CALL_BPZTTQPS();
            if (pgmRtn) return;
            while (BPCRTQPS.INFO.RTN_INFO != 'N' 
                && WS_I <= 10) {
                WS_READ_CNT += 1;
                if (BPCITQ.DATA.CONT_FLAG == 'Y' 
                    && WS_READ_CNT <= BPCITQ.DATA.CMPL_CNT) {
                } else {
                    WS_I += 1;
                    if (WS_I <= 10) {
                        BPCITQ.DATA.OUT_REC_CNT += 1;
                        IBS.CLONE(SCCGWA, BPRTQP, BPRTQP2);
                    }
                }
                BPCRTQPS.INFO.FUNC = 'R';
                S000_CALL_BPZTTQPS();
                if (pgmRtn) return;
            }
            BPCRTQPS.INFO.FUNC = 'E';
            S000_CALL_BPZTTQPS();
            if (pgmRtn) return;
            if (WS_I <= 10) {
                BPCITQ.DATA.CMPL_FLAG = 'Y';
                BPCITQ.DATA.CMPL_CNT += WS_I;
            } else {
                BPCITQ.DATA.CMPL_FLAG = 'N';
                WS_I -= 1;
                BPCITQ.DATA.CMPL_CNT += WS_I;
            }
            if (BPCITQ.DATA.OUT_REC_CNT == 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOT_EXIST, BPCITQ.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            BPCITQ.DATA.CCY_EXR_INFOR[1-1].DT = SCCGWA.COMM_AREA.AC_DATE;
            BPCITQ.DATA.CCY_EXR_INFOR[1-1].TM = SCCGWA.COMM_AREA.TR_TIME;
            BPCITQ.DATA.CCY_EXR_INFOR[1-1].CCY = BPCITQ.DATA.BUY_INFO.BUY_CCY;
            BPCITQ.DATA.CCY_EXR_INFOR[1-1].EXR_TYPE = BPRTQP.KEY.EXR_TYP;
            BPCITQ.DATA.CCY_EXR_INFOR[1-1].FWD_TENOR = BPRTQP.KEY.FWD_TENOR;
            IBS.init(SCCGWA, WS_CALC_PRICE_AREA2);
            WS_CALC_PRICE_AREA2.WS_P1_B = BPRTQP1.CS_BUY;
            WS_CALC_PRICE_AREA2.WS_P1_S = BPRTQP1.CS_SELL;
            WS_CALC_PRICE_AREA2.WS_P2_B = BPRTQP2.CS_BUY;
            WS_CALC_PRICE_AREA2.WS_P2_S = BPRTQP2.CS_SELL;
            WS_CALC_PRICE_AREA2.WS_METH_1 = WS_TQP1_FLG;
            WS_CALC_PRICE_AREA2.WS_METH_2 = WS_TQP2_FLG;
            WS_CALC_PRICE_AREA2.WS_RND2 = WS_EXR_RND;
            WS_CALC_PRICE_AREA2.WS_PNT2 = WS_EXR_PNT;
            R000_CALC_INDIR_PRICE();
            if (pgmRtn) return;
            if (BPCOIEC.EXR_CODE == null) BPCOIEC.EXR_CODE = "";
            JIBS_tmp_int = BPCOIEC.EXR_CODE.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPCOIEC.EXR_CODE += " ";
            if (BPCOIEC.EXR_CODE == null) BPCOIEC.EXR_CODE = "";
            JIBS_tmp_int = BPCOIEC.EXR_CODE.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPCOIEC.EXR_CODE += " ";
            if (BPCOIEC.EXR_CODE.substring(0, 3).equalsIgnoreCase(BPCITQ.DATA.BUY_INFO.BUY_CCY) 
                && BPCOIEC.EXR_CODE.substring(4 - 1, 4 + 3 - 1).equalsIgnoreCase(BPCITQ.DATA.SELL_INFO.SELL_CCY)) {
                BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_BUY = WS_CALC_PRICE_AREA2.WS_NEW_BUY;
                BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_SELL = WS_CALC_PRICE_AREA2.WS_NEW_SELL;
            } else {
                BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_BUY = WS_CALC_PRICE_AREA2.WS_NEW_SELL;
                BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_SELL = WS_CALC_PRICE_AREA2.WS_NEW_BUY;
            }
            CEP.TRC(SCCGWA, BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_BUY);
            CEP.TRC(SCCGWA, BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_SELL);
            IBS.init(SCCGWA, WS_CALC_PRICE_AREA2);
            WS_CALC_PRICE_AREA2.WS_P1_B = BPRTQP1.FX_BUY;
            WS_CALC_PRICE_AREA2.WS_P1_S = BPRTQP1.FX_SELL;
            WS_CALC_PRICE_AREA2.WS_P2_B = BPRTQP2.FX_BUY;
            WS_CALC_PRICE_AREA2.WS_P2_S = BPRTQP2.FX_SELL;
            WS_CALC_PRICE_AREA2.WS_METH_1 = WS_TQP1_FLG;
            WS_CALC_PRICE_AREA2.WS_METH_2 = WS_TQP2_FLG;
            WS_CALC_PRICE_AREA2.WS_RND2 = WS_EXR_RND;
            WS_CALC_PRICE_AREA2.WS_PNT2 = WS_EXR_PNT;
            R000_CALC_INDIR_PRICE();
            if (pgmRtn) return;
            if (BPCOIEC.EXR_CODE == null) BPCOIEC.EXR_CODE = "";
            JIBS_tmp_int = BPCOIEC.EXR_CODE.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPCOIEC.EXR_CODE += " ";
            if (BPCOIEC.EXR_CODE == null) BPCOIEC.EXR_CODE = "";
            JIBS_tmp_int = BPCOIEC.EXR_CODE.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPCOIEC.EXR_CODE += " ";
            if (BPCOIEC.EXR_CODE.substring(0, 3).equalsIgnoreCase(BPCITQ.DATA.BUY_INFO.BUY_CCY) 
                && BPCOIEC.EXR_CODE.substring(4 - 1, 4 + 3 - 1).equalsIgnoreCase(BPCITQ.DATA.SELL_INFO.SELL_CCY)) {
                BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_BUY = WS_CALC_PRICE_AREA2.WS_NEW_BUY;
                BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_SELL = WS_CALC_PRICE_AREA2.WS_NEW_SELL;
            } else {
                BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_BUY = WS_CALC_PRICE_AREA2.WS_NEW_SELL;
                BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_SELL = WS_CALC_PRICE_AREA2.WS_NEW_BUY;
            }
            CEP.TRC(SCCGWA, BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_BUY);
            CEP.TRC(SCCGWA, BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_SELL);
            BPCITQ.DATA.CCY_EXR_INFOR[1-1].LOC_MID = ( BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_BUY + BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_SELL ) / 2;
        }
    }
    public void B230_02_BRW_PROC3() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRTQPS);
        IBS.init(SCCGWA, BPRTQP);
        WS_I = 0;
        WS_READ_CNT = 0;
        BPRTQP.KEY.EXR_TYP = BPCITQ.DATA.CCY_EXR_INFOR[1-1].EXR_TYPE;
        BPRTQP.KEY.CCY = "%%%";
        BPRTQP.KEY.FWD_TENOR = "%%%";
        BPCRTQPS.INFO.FUNC = '1';
        S000_CALL_BPZTTQPS();
        if (pgmRtn) return;
        BPCRTQPS.INFO.FUNC = 'R';
        S000_CALL_BPZTTQPS();
        if (pgmRtn) return;
        while (BPCRTQPS.INFO.RTN_INFO != 'N' 
            && WS_I <= 10) {
            WS_READ_CNT += 1;
            if (BPCITQ.DATA.CONT_FLAG == 'Y' 
                && WS_READ_CNT <= BPCITQ.DATA.CMPL_CNT) {
            } else {
                WS_I += 1;
                if (WS_I <= 10) {
                    BPCITQ.DATA.OUT_REC_CNT += 1;
                    R000_OUTPUT_TQP();
                    if (pgmRtn) return;
                }
            }
            BPCRTQPS.INFO.FUNC = 'R';
            S000_CALL_BPZTTQPS();
            if (pgmRtn) return;
        }
        BPCRTQPS.INFO.FUNC = 'E';
        S000_CALL_BPZTTQPS();
        if (pgmRtn) return;
        if (WS_I <= 10) {
            BPCITQ.DATA.CMPL_FLAG = 'Y';
            BPCITQ.DATA.CMPL_CNT += WS_I;
        } else {
            BPCITQ.DATA.CMPL_FLAG = 'N';
            WS_I -= 1;
            BPCITQ.DATA.CMPL_CNT += WS_I;
        }
        if (BPCITQ.DATA.OUT_REC_CNT == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOT_EXIST, BPCITQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B230_03_BRW_PROC3() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRTQPH);
        IBS.init(SCCGWA, BPRTQPH);
        WS_I = 0;
        WS_READ_CNT = 0;
        BPRTQPH.KEY.EXR_TYP = BPCITQ.DATA.CCY_EXR_INFOR[1-1].EXR_TYPE;
        BPRTQPH.KEY.CCY = "%%%";
        BPRTQPH.KEY.FWD_TENOR = "%%%";
        BPCRTQPH.STR_DATE = BPCITQ.DATA.STR_DT;
        BPCRTQPH.END_DATE = BPCITQ.DATA.END_DT;
        BPCRTQPH.INFO.FUNC = '1';
        S000_CALL_BPZTTQPH();
        if (pgmRtn) return;
        BPCRTQPH.INFO.FUNC = 'R';
        S000_CALL_BPZTTQPH();
        if (pgmRtn) return;
        while (BPCRTQPH.INFO.RTN_INFO != 'N' 
            && WS_I <= 10) {
            WS_READ_CNT += 1;
            if (BPCITQ.DATA.CONT_FLAG == 'Y' 
                && WS_READ_CNT <= BPCITQ.DATA.CMPL_CNT) {
            } else {
                WS_I += 1;
                if (WS_I <= 10) {
                    BPCITQ.DATA.OUT_REC_CNT += 1;
                    R000_OUTPUT_TQPH();
                    if (pgmRtn) return;
                }
            }
            BPCRTQPH.INFO.FUNC = 'R';
            S000_CALL_BPZTTQPH();
            if (pgmRtn) return;
        }
        BPCRTQPH.INFO.FUNC = 'E';
        S000_CALL_BPZTTQPH();
        if (pgmRtn) return;
        if (WS_I <= 10) {
            BPCITQ.DATA.CMPL_FLAG = 'Y';
            BPCITQ.DATA.CMPL_CNT += WS_I;
        } else {
            BPCITQ.DATA.CMPL_FLAG = 'N';
            WS_I -= 1;
            BPCITQ.DATA.CMPL_CNT += WS_I;
        }
        if (BPCITQ.DATA.OUT_REC_CNT == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOT_EXIST, BPCITQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B230_04_BRW_PROC3() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRTQPS);
        IBS.init(SCCGWA, BPRTQP);
        WS_I = 0;
        WS_READ_CNT = 0;
        BPRTQP.KEY.EXR_TYP = BPCITQ.DATA.CCY_EXR_INFOR[1-1].EXR_TYPE;
        BPRTQP.KEY.FWD_TENOR = BPCITQ.DATA.CCY_EXR_INFOR[1-1].FWD_TENOR;
        BPRTQP.KEY.CCY = "%%%";
        BPCRTQPS.INFO.FUNC = '1';
        S000_CALL_BPZTTQPS();
        if (pgmRtn) return;
        BPCRTQPS.INFO.FUNC = 'R';
        S000_CALL_BPZTTQPS();
        if (pgmRtn) return;
        while (BPCRTQPS.INFO.RTN_INFO != 'N' 
            && WS_I <= 10) {
            WS_READ_CNT += 1;
            if (BPCITQ.DATA.CONT_FLAG == 'Y' 
                && WS_READ_CNT <= BPCITQ.DATA.CMPL_CNT) {
            } else {
                WS_I += 1;
                if (WS_I <= 10) {
                    BPCITQ.DATA.OUT_REC_CNT += 1;
                    R000_OUTPUT_TQP();
                    if (pgmRtn) return;
                }
            }
            BPCRTQPS.INFO.FUNC = 'R';
            S000_CALL_BPZTTQPS();
            if (pgmRtn) return;
        }
        BPCRTQPS.INFO.FUNC = 'E';
        S000_CALL_BPZTTQPS();
        if (pgmRtn) return;
        if (WS_I <= 10) {
            BPCITQ.DATA.CMPL_FLAG = 'Y';
            BPCITQ.DATA.CMPL_CNT += WS_I;
        } else {
            BPCITQ.DATA.CMPL_FLAG = 'N';
            WS_I -= 1;
            BPCITQ.DATA.CMPL_CNT += WS_I;
        }
        if (BPCITQ.DATA.OUT_REC_CNT == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOT_EXIST, BPCITQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B230_05_BRW_PROC3() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRTQPH);
        IBS.init(SCCGWA, BPRTQPH);
        WS_I = 0;
        WS_READ_CNT = 0;
        BPRTQPH.KEY.EXR_TYP = BPCITQ.DATA.CCY_EXR_INFOR[1-1].EXR_TYPE;
        BPRTQPH.KEY.CCY = "%%%";
        BPRTQPH.KEY.FWD_TENOR = BPCITQ.DATA.CCY_EXR_INFOR[1-1].FWD_TENOR;
        BPCRTQPH.STR_DATE = BPCITQ.DATA.STR_DT;
        BPCRTQPH.END_DATE = BPCITQ.DATA.END_DT;
        BPCRTQPH.INFO.FUNC = '1';
        S000_CALL_BPZTTQPH();
        if (pgmRtn) return;
        BPCRTQPH.INFO.FUNC = 'R';
        S000_CALL_BPZTTQPH();
        if (pgmRtn) return;
        while (BPCRTQPH.INFO.RTN_INFO != 'N' 
            && WS_I <= 10) {
            WS_READ_CNT += 1;
            if (BPCITQ.DATA.CONT_FLAG == 'Y' 
                && WS_READ_CNT <= BPCITQ.DATA.CMPL_CNT) {
            } else {
                WS_I += 1;
                if (WS_I <= 10) {
                    BPCITQ.DATA.OUT_REC_CNT += 1;
                    R000_OUTPUT_TQPH();
                    if (pgmRtn) return;
                }
            }
            BPCRTQPH.INFO.FUNC = 'R';
            S000_CALL_BPZTTQPH();
            if (pgmRtn) return;
        }
        BPCRTQPH.INFO.FUNC = 'E';
        S000_CALL_BPZTTQPH();
        if (pgmRtn) return;
        if (WS_I <= 10) {
            BPCITQ.DATA.CMPL_FLAG = 'Y';
            BPCITQ.DATA.CMPL_CNT += WS_I;
        } else {
            BPCITQ.DATA.CMPL_FLAG = 'N';
            WS_I -= 1;
            BPCITQ.DATA.CMPL_CNT += WS_I;
        }
        if (BPCITQ.DATA.OUT_REC_CNT == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOT_EXIST, BPCITQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B240_BRW_PROC4() throws IOException,SQLException,Exception {
        if (BPCITQ.DATA.STR_DT == SCCGWA.COMM_AREA.AC_DATE 
            && BPCITQ.DATA.END_DT == SCCGWA.COMM_AREA.AC_DATE 
            && BPCITQ.DATA.STR_TM == 0 
            && BPCITQ.DATA.END_TM == 0) {
            B240_01_BRW_PROC4();
            if (pgmRtn) return;
        } else {
            B240_02_BRW_PROC4();
            if (pgmRtn) return;
        }
    }
    public void B240_01_BRW_PROC4() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRTQPS);
        IBS.init(SCCGWA, BPRTQP);
        WS_I = 0;
        WS_READ_CNT = 0;
        BPRTQP.KEY.CCY = "%%%";
        BPRTQP.KEY.EXR_TYP = "%%%";
        BPRTQP.KEY.FWD_TENOR = BPCITQ.DATA.CCY_EXR_INFOR[1-1].FWD_TENOR;
        BPCRTQPS.INFO.FUNC = '1';
        S000_CALL_BPZTTQPS();
        if (pgmRtn) return;
        BPCRTQPS.INFO.FUNC = 'R';
        S000_CALL_BPZTTQPS();
        if (pgmRtn) return;
        while (BPCRTQPS.INFO.RTN_INFO != 'N' 
            && WS_I <= 10) {
            WS_READ_CNT += 1;
            if (BPCITQ.DATA.CONT_FLAG == 'Y' 
                && WS_READ_CNT <= BPCITQ.DATA.CMPL_CNT) {
            } else {
                WS_I += 1;
                if (WS_I <= 10) {
                    BPCITQ.DATA.OUT_REC_CNT += 1;
                    R000_OUTPUT_TQP();
                    if (pgmRtn) return;
                }
            }
            BPCRTQPS.INFO.FUNC = 'R';
            S000_CALL_BPZTTQPS();
            if (pgmRtn) return;
        }
        BPCRTQPS.INFO.FUNC = 'E';
        S000_CALL_BPZTTQPS();
        if (pgmRtn) return;
        if (WS_I <= 10) {
            BPCITQ.DATA.CMPL_FLAG = 'Y';
            BPCITQ.DATA.CMPL_CNT += WS_I;
        } else {
            BPCITQ.DATA.CMPL_FLAG = 'N';
            WS_I -= 1;
            BPCITQ.DATA.CMPL_CNT += WS_I;
        }
        if (BPCITQ.DATA.OUT_REC_CNT == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOT_EXIST, BPCITQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B240_02_BRW_PROC4() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRTQPH);
        IBS.init(SCCGWA, BPRTQPH);
        WS_I = 0;
        WS_READ_CNT = 0;
        BPRTQPH.KEY.CCY = "%%%";
        BPRTQPH.KEY.EXR_TYP = "%%%";
        BPRTQPH.KEY.FWD_TENOR = BPCITQ.DATA.CCY_EXR_INFOR[1-1].FWD_TENOR;
        BPCRTQPH.STR_DATE = BPCITQ.DATA.STR_DT;
        BPCRTQPH.END_DATE = BPCITQ.DATA.END_DT;
        BPCRTQPH.INFO.FUNC = '1';
        S000_CALL_BPZTTQPH();
        if (pgmRtn) return;
        BPCRTQPH.INFO.FUNC = 'R';
        S000_CALL_BPZTTQPH();
        if (pgmRtn) return;
        while (BPCRTQPH.INFO.RTN_INFO != 'N' 
            && WS_I <= 10) {
            WS_READ_CNT += 1;
            if (BPCITQ.DATA.CONT_FLAG == 'Y' 
                && WS_READ_CNT <= BPCITQ.DATA.CMPL_CNT) {
            } else {
                WS_I += 1;
                if (WS_I <= 10) {
                    BPCITQ.DATA.OUT_REC_CNT += 1;
                    R000_OUTPUT_TQPH();
                    if (pgmRtn) return;
                }
            }
            BPCRTQPH.INFO.FUNC = 'R';
            S000_CALL_BPZTTQPH();
            if (pgmRtn) return;
        }
        BPCRTQPH.INFO.FUNC = 'E';
        S000_CALL_BPZTTQPH();
        if (pgmRtn) return;
        if (WS_I <= 10) {
            BPCITQ.DATA.CMPL_FLAG = 'Y';
            BPCITQ.DATA.CMPL_CNT += WS_I;
        } else {
            BPCITQ.DATA.CMPL_FLAG = 'N';
            WS_I -= 1;
            BPCITQ.DATA.CMPL_CNT += WS_I;
        }
        if (BPCITQ.DATA.OUT_REC_CNT == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOT_EXIST, BPCITQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B250_INQ_CURRENT_RATE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_BASE_CCY);
        CEP.TRC(SCCGWA, BPCITQ.DATA.BUY_INFO.BUY_CCY);
        CEP.TRC(SCCGWA, BPCITQ.DATA.SELL_INFO.SELL_CCY);
        if (BPCITQ.DATA.BUY_INFO.BUY_CCY.equalsIgnoreCase(WS_BASE_CCY) 
            || BPCITQ.DATA.SELL_INFO.SELL_CCY.equalsIgnoreCase(WS_BASE_CCY)) {
            IBS.init(SCCGWA, WS_FL_AREA);
            WS_FL_AREA.WS_FL_INP_DT = BPCITQ.DATA.STR_DT;
            WS_FL_AREA.WS_FL_INP_TM = BPCITQ.DATA.STR_TM;
            if (BPCITQ.DATA.BUY_INFO.BUY_CCY.equalsIgnoreCase(WS_BASE_CCY)) {
                WS_FL_AREA.WS_FL_INP_CCY = BPCITQ.DATA.SELL_INFO.SELL_CCY;
                WS_FL_AREA.WS_FL_INP_TYP = BPCITQ.DATA.SELL_INFO.SELL_EXR_TYPE;
                WS_FL_AREA.WS_FL_INP_TNR = BPCITQ.DATA.SELL_INFO.SELL_TENOR;
            } else {
                WS_FL_AREA.WS_FL_INP_CCY = BPCITQ.DATA.BUY_INFO.BUY_CCY;
                WS_FL_AREA.WS_FL_INP_TYP = BPCITQ.DATA.BUY_INFO.BUY_EXR_TYPE;
                WS_FL_AREA.WS_FL_INP_TNR = BPCITQ.DATA.BUY_INFO.BUY_TENOR;
            }
            R000_GET_FCY_LCY_RATE();
            if (pgmRtn) return;
            BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_BUY = WS_FL_AREA.WS_FL_OUT_CBUY;
            BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_SELL = WS_FL_AREA.WS_FL_OUT_CSELL;
            BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_BUY = WS_FL_AREA.WS_FL_OUT_BUY;
            BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_SELL = WS_FL_AREA.WS_FL_OUT_SELL;
            BPCITQ.DATA.CCY_EXR_INFOR[1-1].LOC_MID = WS_FL_AREA.WS_FL_OUT_MID;
            BPCITQ.DATA.CCY_EXR_INFOR[1-1].DT = WS_FL_AREA.WS_FL_OUT_EF_DT;
            BPCITQ.DATA.CCY_EXR_INFOR[1-1].TM = WS_FL_AREA.WS_FL_OUT_EF_TM;
            BPCITQ.DATA.CCY_EXR_INFOR[1-1].CCY = WS_FL_AREA.WS_FL_INP_CCY;
            BPCITQ.DATA.CCY_EXR_INFOR[1-1].EXR_TYPE = WS_FL_AREA.WS_FL_INP_TYP;
            BPCITQ.DATA.CCY_EXR_INFOR[1-1].FWD_TENOR = WS_FL_AREA.WS_FL_INP_TNR;
            if ((BPCITQ.DATA.BUY_INFO.BUY_CCY.equalsIgnoreCase("446") 
                || BPCITQ.DATA.SELL_INFO.SELL_CCY.equalsIgnoreCase("446")) 
                && BPCITQ.DATA.BUY_INFO.BUY_EXR_TYPE.equalsIgnoreCase("TRE") 
                && BPCITQ.DATA.SELL_INFO.SELL_EXR_TYPE.equalsIgnoreCase("TRE")) {
                if (BPCITQ.DATA.BUY_INFO.BUY_CCY.equalsIgnoreCase(WS_BASE_CCY) 
                    && BPCITQ.DATA.SELL_INFO.SELL_CCY.equalsIgnoreCase("446")) {
                    if (BPCITQ.DATA.B_CASH_FLG == 'N' 
                        && BPCITQ.DATA.S_CASH_FLG == 'N') {
                        BPCITQ.DATA.EX_RATE = WS_FL_AREA.WS_FL_OUT_BUY;
                    } else {
                        BPCITQ.DATA.EX_RATE = WS_FL_AREA.WS_FL_OUT_CBUY;
                    }
                }
                if (BPCITQ.DATA.BUY_INFO.BUY_CCY.equalsIgnoreCase("446") 
                    && BPCITQ.DATA.SELL_INFO.SELL_CCY.equalsIgnoreCase(WS_BASE_CCY)) {
                    if (BPCITQ.DATA.B_CASH_FLG == 'N' 
                        && BPCITQ.DATA.S_CASH_FLG == 'N') {
                        BPCITQ.DATA.EX_RATE = WS_FL_AREA.WS_FL_OUT_SELL;
                    } else {
                        BPCITQ.DATA.EX_RATE = WS_FL_AREA.WS_FL_OUT_CSELL;
                    }
                }
            } else {
                if (BPCITQ.DATA.BUY_INFO.BUY_CCY.equalsIgnoreCase(WS_BASE_CCY)) {
                    if (BPCITQ.DATA.B_CASH_FLG == 'N' 
                        && BPCITQ.DATA.S_CASH_FLG == 'N') {
                        BPCITQ.DATA.EX_RATE = WS_FL_AREA.WS_FL_OUT_SELL;
                    } else {
                        BPCITQ.DATA.EX_RATE = WS_FL_AREA.WS_FL_OUT_CSELL;
                    }
                } else {
                    if (BPCITQ.DATA.B_CASH_FLG == 'N' 
                        && BPCITQ.DATA.S_CASH_FLG == 'N') {
                        BPCITQ.DATA.EX_RATE = WS_FL_AREA.WS_FL_OUT_BUY;
                    } else {
                        BPCITQ.DATA.EX_RATE = WS_FL_AREA.WS_FL_OUT_CBUY;
                    }
                }
            }
            if (BPCITQ.DATA.EX_CODE == null) BPCITQ.DATA.EX_CODE = "";
            JIBS_tmp_int = BPCITQ.DATA.EX_CODE.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPCITQ.DATA.EX_CODE += " ";
            if (WS_BASE_CCY.equalsIgnoreCase(BPCITQ.DATA.EX_CODE.substring(0, 3))) {
                BPCITQ.DATA.CCY_EXR_INFOR[1-1].UNIT = BPREXRD2.UNT;
            } else {
                BPCITQ.DATA.CCY_EXR_INFOR[1-1].UNIT = BPREXRD1.UNT;
            }
        } else {
            B250_INQ_RATE_CHANGE();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "GET 1ST RATE");
            IBS.init(SCCGWA, WS_FL_AREA);
            WS_FL_AREA.WS_FL_INP_TYP = BPREXRD1.KEY.EXR_TYP;
            WS_FL_AREA.WS_FL_INP_TNR = BPREXRD1.KEY.FWD_TENOR;
            CEP.TRC(SCCGWA, BPCOIEC.EXR_CODE);
            if (BPCOIEC.EXR_CODE == null) BPCOIEC.EXR_CODE = "";
            JIBS_tmp_int = BPCOIEC.EXR_CODE.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPCOIEC.EXR_CODE += " ";
            WS_FL_AREA.WS_FL_INP_CCY = BPCOIEC.EXR_CODE.substring(0, 3);
            CEP.TRC(SCCGWA, WS_FL_AREA.WS_FL_INP_CCY);
            CEP.TRC(SCCGWA, BPCOIEC.EXR_CODE);
            WS_FL_AREA.WS_FL_INP_DT = BPCITQ.DATA.STR_DT;
            WS_FL_AREA.WS_FL_INP_TM = BPCITQ.DATA.STR_TM;
            CEP.TRC(SCCGWA, "ZZZZZZZZZZZZZ");
            CEP.TRC(SCCGWA, WS_FL_AREA.WS_FL_INP_TM);
            R000_GET_FCY_LCY_RATE();
            if (pgmRtn) return;
            BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_BUY = WS_FL_AREA.WS_FL_OUT_CBUY;
            BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_SELL = WS_FL_AREA.WS_FL_OUT_CSELL;
            BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_BUY = WS_FL_AREA.WS_FL_OUT_BUY;
            BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_SELL = WS_FL_AREA.WS_FL_OUT_SELL;
            BPCITQ.DATA.CCY_EXR_INFOR[1-1].LOC_MID = WS_FL_AREA.WS_FL_OUT_MID;
            BPCITQ.DATA.CCY_EXR_INFOR[1-1].DT = WS_FL_AREA.WS_FL_OUT_EF_DT;
            BPCITQ.DATA.CCY_EXR_INFOR[1-1].TM = WS_FL_AREA.WS_FL_OUT_EF_TM;
            CEP.TRC(SCCGWA, WS_FL_AREA.WS_FL_INP_CCY);
            CEP.TRC(SCCGWA, BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_BUY);
            CEP.TRC(SCCGWA, BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_SELL);
            CEP.TRC(SCCGWA, BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_BUY);
            CEP.TRC(SCCGWA, BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_SELL);
            CEP.TRC(SCCGWA, BPCITQ.DATA.CCY_EXR_INFOR[1-1].LOC_MID);
            CEP.TRC(SCCGWA, BPCITQ.DATA.CCY_EXR_INFOR[1-1].DT);
            CEP.TRC(SCCGWA, BPCITQ.DATA.CCY_EXR_INFOR[1-1].TM);
            CEP.TRC(SCCGWA, WS_FL_AREA.WS_FL_OUT_BUY);
            CEP.TRC(SCCGWA, WS_FL_AREA.WS_FL_OUT_SELL);
            if (BPREXRD1.METH == '0') {
                WS_TQP1_FLG = 'D';
            } else {
                WS_TQP1_FLG = 'I';
            }
            CEP.TRC(SCCGWA, "GET 2ND RATE");
            IBS.init(SCCGWA, WS_FL_AREA);
            WS_FL_AREA.WS_FL_INP_TYP = BPREXRD2.KEY.EXR_TYP;
            WS_FL_AREA.WS_FL_INP_TNR = BPREXRD2.KEY.FWD_TENOR;
            if (BPCOIEC.EXR_CODE == null) BPCOIEC.EXR_CODE = "";
            JIBS_tmp_int = BPCOIEC.EXR_CODE.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPCOIEC.EXR_CODE += " ";
            WS_FL_AREA.WS_FL_INP_CCY = BPCOIEC.EXR_CODE.substring(4 - 1, 4 + 3 - 1);
            WS_FL_AREA.WS_FL_INP_DT = BPCITQ.DATA.STR_DT;
            WS_FL_AREA.WS_FL_INP_TM = BPCITQ.DATA.STR_TM;
            CEP.TRC(SCCGWA, "ZZZZZZZZZZZ");
            CEP.TRC(SCCGWA, WS_FL_AREA.WS_FL_INP_TM);
            R000_GET_FCY_LCY_RATE();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_FL_AREA.WS_FL_INP_DT);
            CEP.TRC(SCCGWA, WS_FL_AREA.WS_FL_INP_TM);
            if (BPREXRD2.METH == '0') {
                WS_TQP2_FLG = 'D';
            } else {
                WS_TQP2_FLG = 'I';
            }
            BPCITQ.DATA.CCY_EXR_INFOR[2-1].CS_BUY = WS_FL_AREA.WS_FL_OUT_CBUY;
            BPCITQ.DATA.CCY_EXR_INFOR[2-1].CS_SELL = WS_FL_AREA.WS_FL_OUT_CSELL;
            BPCITQ.DATA.CCY_EXR_INFOR[2-1].FX_BUY = WS_FL_AREA.WS_FL_OUT_BUY;
            BPCITQ.DATA.CCY_EXR_INFOR[2-1].FX_SELL = WS_FL_AREA.WS_FL_OUT_SELL;
            BPCITQ.DATA.CCY_EXR_INFOR[2-1].LOC_MID = WS_FL_AREA.WS_FL_OUT_MID;
            if (WS_FL_AREA.WS_FL_OUT_EF_DT > BPCITQ.DATA.CCY_EXR_INFOR[1-1].DT 
                || (WS_FL_AREA.WS_FL_OUT_EF_DT == BPCITQ.DATA.CCY_EXR_INFOR[1-1].DT 
                && WS_FL_AREA.WS_FL_OUT_EF_TM > BPCITQ.DATA.CCY_EXR_INFOR[1-1].TM)) {
                BPCITQ.DATA.CCY_EXR_INFOR[1-1].DT = WS_FL_AREA.WS_FL_OUT_EF_DT;
                BPCITQ.DATA.CCY_EXR_INFOR[1-1].TM = WS_FL_AREA.WS_FL_OUT_EF_TM;
            }
            CEP.TRC(SCCGWA, WS_FL_AREA.WS_FL_INP_CCY);
            CEP.TRC(SCCGWA, WS_FL_AREA.WS_FL_INP_DT);
            CEP.TRC(SCCGWA, WS_FL_AREA.WS_FL_INP_TM);
            CEP.TRC(SCCGWA, WS_FL_AREA.WS_FL_OUT_BUY);
            CEP.TRC(SCCGWA, WS_FL_AREA.WS_FL_OUT_SELL);
            BPCITQ.DATA.CCY_EXR_INFOR[1-1].EXR_TYPE = BPREXRD1.KEY.EXR_TYP;
            BPCITQ.DATA.CCY_EXR_INFOR[1-1].FWD_TENOR = BPREXRD1.KEY.FWD_TENOR;
            if (BPCOIEC.EXR_CODE == null) BPCOIEC.EXR_CODE = "";
            JIBS_tmp_int = BPCOIEC.EXR_CODE.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPCOIEC.EXR_CODE += " ";
            BPCITQ.DATA.CCY_EXR_INFOR[1-1].CCY = BPCOIEC.EXR_CODE.substring(0, 3);
            if ((WS_B_CASH_FLG == 'N' 
                && WS_S_CASH_FLG == 'N') 
                || (WS_B_CASH_FLG == 'Y' 
                && WS_S_CASH_FLG == 'Y')) {
                IBS.init(SCCGWA, WS_CALC_PRICE_AREA2);
                WS_CALC_PRICE_AREA2.WS_P1_B = BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_BUY;
                WS_CALC_PRICE_AREA2.WS_P1_S = BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_SELL;
                WS_CALC_PRICE_AREA2.WS_P2_B = BPCITQ.DATA.CCY_EXR_INFOR[2-1].CS_BUY;
                WS_CALC_PRICE_AREA2.WS_P2_S = BPCITQ.DATA.CCY_EXR_INFOR[2-1].CS_SELL;
                WS_CALC_PRICE_AREA2.WS_METH_1 = WS_TQP1_FLG;
                WS_CALC_PRICE_AREA2.WS_METH_2 = WS_TQP2_FLG;
                WS_CALC_PRICE_AREA2.WS_RND2 = BPREXRD1.EXR_RND;
                if (BPREXRD1.EXR_PNT == ' ') WS_CALC_PRICE_AREA2.WS_PNT2 = 0;
                else WS_CALC_PRICE_AREA2.WS_PNT2 = Short.parseShort(""+BPREXRD1.EXR_PNT);
                WS_CALC_PRICE_AREA2.WS_P1_UNIT = BPREXRD1.UNT;
                WS_CALC_PRICE_AREA2.WS_P2_UNIT = BPREXRD2.UNT;
                R000_CALC_INDIR_PRICE();
                if (pgmRtn) return;
                BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_BUY = WS_CALC_PRICE_AREA2.WS_NEW_BUY;
                BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_SELL = WS_CALC_PRICE_AREA2.WS_NEW_SELL;
                CEP.TRC(SCCGWA, BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_BUY);
                CEP.TRC(SCCGWA, BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_SELL);
                IBS.init(SCCGWA, WS_CALC_PRICE_AREA2);
                WS_CALC_PRICE_AREA2.WS_P1_B = BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_BUY;
                WS_CALC_PRICE_AREA2.WS_P1_S = BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_SELL;
                WS_CALC_PRICE_AREA2.WS_P2_B = BPCITQ.DATA.CCY_EXR_INFOR[2-1].FX_BUY;
                WS_CALC_PRICE_AREA2.WS_P2_S = BPCITQ.DATA.CCY_EXR_INFOR[2-1].FX_SELL;
                WS_CALC_PRICE_AREA2.WS_METH_1 = WS_TQP1_FLG;
                WS_CALC_PRICE_AREA2.WS_METH_2 = WS_TQP2_FLG;
                WS_CALC_PRICE_AREA2.WS_RND2 = BPREXRD1.EXR_RND;
                if (BPREXRD1.EXR_PNT == ' ') WS_CALC_PRICE_AREA2.WS_PNT2 = 0;
                else WS_CALC_PRICE_AREA2.WS_PNT2 = Short.parseShort(""+BPREXRD1.EXR_PNT);
                WS_CALC_PRICE_AREA2.WS_P1_UNIT = BPREXRD1.UNT;
                WS_CALC_PRICE_AREA2.WS_P2_UNIT = BPREXRD2.UNT;
                R000_CALC_INDIR_PRICE();
                if (pgmRtn) return;
                BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_BUY = WS_CALC_PRICE_AREA2.WS_NEW_BUY;
                BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_SELL = WS_CALC_PRICE_AREA2.WS_NEW_SELL;
                BPCITQ.DATA.CCY_EXR_INFOR[1-1].UNIT = BPREXRD1.UNT;
                CEP.TRC(SCCGWA, BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_BUY);
                CEP.TRC(SCCGWA, BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_SELL);
                BPCITQ.DATA.CCY_EXR_INFOR[1-1].LOC_MID = ( BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_BUY + BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_SELL ) / 2;
                if (BPCITQ.DATA.EX_CODE == null) BPCITQ.DATA.EX_CODE = "";
                JIBS_tmp_int = BPCITQ.DATA.EX_CODE.length();
                for (int i=0;i<6-JIBS_tmp_int;i++) BPCITQ.DATA.EX_CODE += " ";
                if (BPCITQ.DATA.BUY_INFO.BUY_CCY.equalsIgnoreCase(BPCITQ.DATA.EX_CODE.substring(0, 3))) {
                    if (WS_B_CASH_FLG == 'N') {
                        BPCITQ.DATA.EX_RATE = BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_BUY;
                    } else {
                        BPCITQ.DATA.EX_RATE = BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_BUY;
                    }
                } else {
                    if (WS_S_CASH_FLG == 'N') {
                        BPCITQ.DATA.EX_RATE = BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_SELL;
                    } else {
                        BPCITQ.DATA.EX_RATE = BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_SELL;
                    }
                }
            } else {
                if ((WS_B_CASH_FLG == 'Y' 
                    && WS_S_CASH_FLG == 'N')) {
                    IBS.init(SCCGWA, WS_CALC_PRICE_AREA2);
                    WS_CALC_PRICE_AREA2.WS_P1_B = BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_BUY;
                    WS_CALC_PRICE_AREA2.WS_P1_S = BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_SELL;
                    WS_CALC_PRICE_AREA2.WS_P2_B = BPCITQ.DATA.CCY_EXR_INFOR[2-1].FX_BUY;
                    WS_CALC_PRICE_AREA2.WS_P2_S = BPCITQ.DATA.CCY_EXR_INFOR[2-1].FX_SELL;
                    WS_CALC_PRICE_AREA2.WS_METH_1 = WS_TQP1_FLG;
                    WS_CALC_PRICE_AREA2.WS_METH_2 = WS_TQP2_FLG;
                    WS_CALC_PRICE_AREA2.WS_RND2 = BPREXRD1.EXR_RND;
                    if (BPREXRD1.EXR_PNT == ' ') WS_CALC_PRICE_AREA2.WS_PNT2 = 0;
                    else WS_CALC_PRICE_AREA2.WS_PNT2 = Short.parseShort(""+BPREXRD1.EXR_PNT);
                    WS_CALC_PRICE_AREA2.WS_P1_UNIT = BPREXRD1.UNT;
                    WS_CALC_PRICE_AREA2.WS_P2_UNIT = BPREXRD2.UNT;
                    R000_CALC_INDIR_PRICE();
                    if (pgmRtn) return;
                    BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_BUY = WS_CALC_PRICE_AREA2.WS_NEW_BUY;
                    BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_SELL = WS_CALC_PRICE_AREA2.WS_NEW_SELL;
                    CEP.TRC(SCCGWA, BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_BUY);
                    CEP.TRC(SCCGWA, BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_SELL);
                    IBS.init(SCCGWA, WS_CALC_PRICE_AREA2);
                    WS_CALC_PRICE_AREA2.WS_P1_B = BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_BUY;
                    WS_CALC_PRICE_AREA2.WS_P1_S = BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_SELL;
                    WS_CALC_PRICE_AREA2.WS_P2_B = BPCITQ.DATA.CCY_EXR_INFOR[2-1].FX_BUY;
                    WS_CALC_PRICE_AREA2.WS_P2_S = BPCITQ.DATA.CCY_EXR_INFOR[2-1].FX_SELL;
                    WS_CALC_PRICE_AREA2.WS_METH_1 = WS_TQP1_FLG;
                    WS_CALC_PRICE_AREA2.WS_METH_2 = WS_TQP2_FLG;
                    WS_CALC_PRICE_AREA2.WS_RND2 = BPREXRD1.EXR_RND;
                    if (BPREXRD1.EXR_PNT == ' ') WS_CALC_PRICE_AREA2.WS_PNT2 = 0;
                    else WS_CALC_PRICE_AREA2.WS_PNT2 = Short.parseShort(""+BPREXRD1.EXR_PNT);
                    WS_CALC_PRICE_AREA2.WS_P1_UNIT = BPREXRD1.UNT;
                    WS_CALC_PRICE_AREA2.WS_P2_UNIT = BPREXRD2.UNT;
                    R000_CALC_INDIR_PRICE();
                    if (pgmRtn) return;
                    BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_BUY = WS_CALC_PRICE_AREA2.WS_NEW_BUY;
                    BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_SELL = WS_CALC_PRICE_AREA2.WS_NEW_SELL;
                    BPCITQ.DATA.CCY_EXR_INFOR[1-1].UNIT = BPREXRD1.UNT;
                    CEP.TRC(SCCGWA, BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_BUY);
                    CEP.TRC(SCCGWA, BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_SELL);
                    BPCITQ.DATA.CCY_EXR_INFOR[1-1].LOC_MID = ( BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_BUY + BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_SELL ) / 2;
                    if (BPCITQ.DATA.EX_CODE == null) BPCITQ.DATA.EX_CODE = "";
                    JIBS_tmp_int = BPCITQ.DATA.EX_CODE.length();
                    for (int i=0;i<6-JIBS_tmp_int;i++) BPCITQ.DATA.EX_CODE += " ";
                    if (BPCITQ.DATA.BUY_INFO.BUY_CCY.equalsIgnoreCase(BPCITQ.DATA.EX_CODE.substring(0, 3))) {
                        if (WS_B_CASH_FLG == 'Y') {
                            BPCITQ.DATA.EX_RATE = BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_BUY;
                        }
                    } else {
                        if (WS_S_CASH_FLG == 'N') {
                            BPCITQ.DATA.EX_RATE = BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_SELL;
                        }
                    }
                }
                if ((WS_B_CASH_FLG == 'N' 
                    && WS_S_CASH_FLG == 'Y')) {
                    IBS.init(SCCGWA, WS_CALC_PRICE_AREA2);
                    WS_CALC_PRICE_AREA2.WS_P1_B = BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_BUY;
                    WS_CALC_PRICE_AREA2.WS_P1_S = BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_SELL;
                    WS_CALC_PRICE_AREA2.WS_P2_B = BPCITQ.DATA.CCY_EXR_INFOR[2-1].CS_BUY;
                    WS_CALC_PRICE_AREA2.WS_P2_S = BPCITQ.DATA.CCY_EXR_INFOR[2-1].CS_SELL;
                    WS_CALC_PRICE_AREA2.WS_METH_1 = WS_TQP1_FLG;
                    WS_CALC_PRICE_AREA2.WS_METH_2 = WS_TQP2_FLG;
                    WS_CALC_PRICE_AREA2.WS_RND2 = BPREXRD1.EXR_RND;
                    if (BPREXRD1.EXR_PNT == ' ') WS_CALC_PRICE_AREA2.WS_PNT2 = 0;
                    else WS_CALC_PRICE_AREA2.WS_PNT2 = Short.parseShort(""+BPREXRD1.EXR_PNT);
                    WS_CALC_PRICE_AREA2.WS_P1_UNIT = BPREXRD1.UNT;
                    WS_CALC_PRICE_AREA2.WS_P2_UNIT = BPREXRD2.UNT;
                    R000_CALC_INDIR_PRICE();
                    if (pgmRtn) return;
                    BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_BUY = WS_CALC_PRICE_AREA2.WS_NEW_BUY;
                    BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_SELL = WS_CALC_PRICE_AREA2.WS_NEW_SELL;
                    CEP.TRC(SCCGWA, BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_BUY);
                    CEP.TRC(SCCGWA, BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_SELL);
                    IBS.init(SCCGWA, WS_CALC_PRICE_AREA2);
                    WS_CALC_PRICE_AREA2.WS_P1_B = BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_BUY;
                    WS_CALC_PRICE_AREA2.WS_P1_S = BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_SELL;
                    WS_CALC_PRICE_AREA2.WS_P2_B = BPCITQ.DATA.CCY_EXR_INFOR[2-1].FX_BUY;
                    WS_CALC_PRICE_AREA2.WS_P2_S = BPCITQ.DATA.CCY_EXR_INFOR[2-1].FX_SELL;
                    WS_CALC_PRICE_AREA2.WS_METH_1 = WS_TQP1_FLG;
                    WS_CALC_PRICE_AREA2.WS_METH_2 = WS_TQP2_FLG;
                    WS_CALC_PRICE_AREA2.WS_RND2 = BPREXRD1.EXR_RND;
                    if (BPREXRD1.EXR_PNT == ' ') WS_CALC_PRICE_AREA2.WS_PNT2 = 0;
                    else WS_CALC_PRICE_AREA2.WS_PNT2 = Short.parseShort(""+BPREXRD1.EXR_PNT);
                    WS_CALC_PRICE_AREA2.WS_P1_UNIT = BPREXRD1.UNT;
                    WS_CALC_PRICE_AREA2.WS_P2_UNIT = BPREXRD2.UNT;
                    R000_CALC_INDIR_PRICE();
                    if (pgmRtn) return;
                    BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_BUY = WS_CALC_PRICE_AREA2.WS_NEW_BUY;
                    BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_SELL = WS_CALC_PRICE_AREA2.WS_NEW_SELL;
                    BPCITQ.DATA.CCY_EXR_INFOR[1-1].UNIT = BPREXRD1.UNT;
                    CEP.TRC(SCCGWA, BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_BUY);
                    CEP.TRC(SCCGWA, BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_SELL);
                    BPCITQ.DATA.CCY_EXR_INFOR[1-1].LOC_MID = ( BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_BUY + BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_SELL ) / 2;
                    if (BPCITQ.DATA.EX_CODE == null) BPCITQ.DATA.EX_CODE = "";
                    JIBS_tmp_int = BPCITQ.DATA.EX_CODE.length();
                    for (int i=0;i<6-JIBS_tmp_int;i++) BPCITQ.DATA.EX_CODE += " ";
                    if (BPCITQ.DATA.BUY_INFO.BUY_CCY.equalsIgnoreCase(BPCITQ.DATA.EX_CODE.substring(0, 3))) {
                        if (WS_B_CASH_FLG == 'N') {
                            BPCITQ.DATA.EX_RATE = BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_BUY;
                        }
                    } else {
                        if (WS_S_CASH_FLG == 'Y') {
                            BPCITQ.DATA.EX_RATE = BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_SELL;
                        }
                    }
                }
            }
        }
        CEP.TRC(SCCGWA, BPCITQ.DATA.EX_RATE);
        CEP.TRC(SCCGWA, BPCITQ.DATA.CCY_EXR_INFOR[1-1].LOC_MID);
    }
    public void B250_INQ_RATE_CHANGE() throws IOException,SQLException,Exception {
        WS_B_CASH_FLG = ' ';
        WS_S_CASH_FLG = ' ';
        if (BPCOIEC.EXR_CODE == null) BPCOIEC.EXR_CODE = "";
        JIBS_tmp_int = BPCOIEC.EXR_CODE.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCOIEC.EXR_CODE += " ";
        if (BPCOIEC.EXR_CODE.substring(0, 3).equalsIgnoreCase(BPCITQ.DATA.BUY_INFO.BUY_CCY)) {
            WS_B_CASH_FLG = BPCITQ.DATA.B_CASH_FLG;
            WS_S_CASH_FLG = BPCITQ.DATA.S_CASH_FLG;
        } else {
            WS_S_CASH_FLG = BPCITQ.DATA.B_CASH_FLG;
            WS_B_CASH_FLG = BPCITQ.DATA.S_CASH_FLG;
        }
    }
    public void B260_INQ_HISTROY_RATE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCITQ.DATA.BUY_INFO.BUY_CCY);
        CEP.TRC(SCCGWA, BPCITQ.DATA.SELL_INFO.SELL_CCY);
        CEP.TRC(SCCGWA, WS_BASE_CCY);
        CEP.TRC(SCCGWA, BPCITQ.DATA.SELL_INFO.SELL_EXR_TYPE);
        CEP.TRC(SCCGWA, BPCITQ.DATA.BUY_INFO.BUY_EXR_TYPE);
        CEP.TRC(SCCGWA, BPCITQ.DATA.BUY_INFO.BUY_CCY);
        CEP.TRC(SCCGWA, BPCITQ.DATA.SELL_INFO.SELL_CCY);
        if (BPCITQ.DATA.BUY_INFO.BUY_CCY.equalsIgnoreCase(WS_BASE_CCY) 
            || BPCITQ.DATA.SELL_INFO.SELL_CCY.equalsIgnoreCase(WS_BASE_CCY)) {
            IBS.init(SCCGWA, WS_FL_AREA);
            WS_FL_AREA.WS_FL_INP_DT = BPCITQ.DATA.STR_DT;
            WS_FL_AREA.WS_FL_INP_TM = BPCITQ.DATA.STR_TM;
            if (BPCITQ.DATA.BUY_INFO.BUY_CCY.equalsIgnoreCase(WS_BASE_CCY)) {
                WS_FL_AREA.WS_FL_INP_CCY = BPCITQ.DATA.SELL_INFO.SELL_CCY;
                WS_FL_AREA.WS_FL_INP_TYP = BPCITQ.DATA.SELL_INFO.SELL_EXR_TYPE;
                WS_FL_AREA.WS_FL_INP_TNR = BPCITQ.DATA.SELL_INFO.SELL_TENOR;
                CEP.TRC(SCCGWA, BPCITQ.DATA.SELL_INFO.SELL_CCY);
                CEP.TRC(SCCGWA, WS_FL_AREA.WS_FL_INP_CCY);
                CEP.TRC(SCCGWA, BPCITQ.DATA.SELL_INFO.SELL_EXR_TYPE);
                CEP.TRC(SCCGWA, WS_FL_AREA.WS_FL_INP_TYP);
            } else {
                WS_FL_AREA.WS_FL_INP_CCY = BPCITQ.DATA.BUY_INFO.BUY_CCY;
                WS_FL_AREA.WS_FL_INP_TYP = BPCITQ.DATA.BUY_INFO.BUY_EXR_TYPE;
                WS_FL_AREA.WS_FL_INP_TNR = BPCITQ.DATA.BUY_INFO.BUY_TENOR;
                CEP.TRC(SCCGWA, BPCITQ.DATA.BUY_INFO.BUY_CCY);
                CEP.TRC(SCCGWA, WS_FL_AREA.WS_FL_INP_CCY);
                CEP.TRC(SCCGWA, BPCITQ.DATA.BUY_INFO.BUY_EXR_TYPE);
                CEP.TRC(SCCGWA, WS_FL_AREA.WS_FL_INP_TYP);
            }
            R000_GET_FCY_LCY_RATE_HIST();
            if (pgmRtn) return;
            BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_BUY = WS_FL_AREA.WS_FL_OUT_CBUY;
            BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_SELL = WS_FL_AREA.WS_FL_OUT_CSELL;
            BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_BUY = WS_FL_AREA.WS_FL_OUT_BUY;
            BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_SELL = WS_FL_AREA.WS_FL_OUT_SELL;
            BPCITQ.DATA.CCY_EXR_INFOR[1-1].LOC_MID = WS_FL_AREA.WS_FL_OUT_MID;
            BPCITQ.DATA.CCY_EXR_INFOR[1-1].DT = WS_FL_AREA.WS_FL_OUT_EF_DT;
            BPCITQ.DATA.CCY_EXR_INFOR[1-1].TM = WS_FL_AREA.WS_FL_OUT_EF_TM;
            BPCITQ.DATA.CCY_EXR_INFOR[1-1].CCY = WS_FL_AREA.WS_FL_INP_CCY;
            BPCITQ.DATA.CCY_EXR_INFOR[1-1].EXR_TYPE = WS_FL_AREA.WS_FL_INP_TYP;
            BPCITQ.DATA.CCY_EXR_INFOR[1-1].FWD_TENOR = WS_FL_AREA.WS_FL_INP_TNR;
            if ((BPCITQ.DATA.BUY_INFO.BUY_CCY.equalsIgnoreCase("446") 
                || BPCITQ.DATA.SELL_INFO.SELL_CCY.equalsIgnoreCase("446")) 
                && BPCITQ.DATA.BUY_INFO.BUY_EXR_TYPE.equalsIgnoreCase("TRE") 
                && BPCITQ.DATA.SELL_INFO.SELL_EXR_TYPE.equalsIgnoreCase("TRE")) {
                if (BPCITQ.DATA.BUY_INFO.BUY_CCY.equalsIgnoreCase(WS_BASE_CCY) 
                    && BPCITQ.DATA.SELL_INFO.SELL_CCY.equalsIgnoreCase("446")) {
                    if (BPCITQ.DATA.B_CASH_FLG == 'N' 
                        && BPCITQ.DATA.S_CASH_FLG == 'N') {
                        BPCITQ.DATA.EX_RATE = BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_BUY;
                    } else {
                        BPCITQ.DATA.EX_RATE = BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_BUY;
                    }
                    if (BPCITQ.DATA.BUY_INFO.BUY_CCY.equalsIgnoreCase("446") 
                        && BPCITQ.DATA.SELL_INFO.SELL_CCY.equalsIgnoreCase(WS_BASE_CCY)) {
                        BPCITQ.DATA.EX_RATE = BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_SELL;
                    } else {
                        BPCITQ.DATA.EX_RATE = BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_SELL;
                    }
                }
            } else {
                if (BPCITQ.DATA.BUY_INFO.BUY_CCY.equalsIgnoreCase(WS_BASE_CCY)) {
                    if (BPCITQ.DATA.B_CASH_FLG == 'N' 
                        && BPCITQ.DATA.S_CASH_FLG == 'N') {
                        BPCITQ.DATA.EX_RATE = BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_SELL;
                    } else {
                        BPCITQ.DATA.EX_RATE = BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_SELL;
                    }
                } else {
                    if (BPCITQ.DATA.B_CASH_FLG == 'N' 
                        && BPCITQ.DATA.S_CASH_FLG == 'N') {
                        BPCITQ.DATA.EX_RATE = BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_BUY;
                    } else {
                        BPCITQ.DATA.EX_RATE = BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_BUY;
                    }
                }
            }
            if (BPCITQ.DATA.EX_CODE == null) BPCITQ.DATA.EX_CODE = "";
            JIBS_tmp_int = BPCITQ.DATA.EX_CODE.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPCITQ.DATA.EX_CODE += " ";
            if (WS_BASE_CCY.equalsIgnoreCase(BPCITQ.DATA.EX_CODE.substring(0, 3))) {
                BPCITQ.DATA.CCY_EXR_INFOR[1-1].UNIT = BPREXRD2.UNT;
            } else {
                BPCITQ.DATA.CCY_EXR_INFOR[1-1].UNIT = BPREXRD1.UNT;
            }
        } else {
            B250_INQ_RATE_CHANGE();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPREXRD1.KEY.EXR_TYP);
            CEP.TRC(SCCGWA, BPCOIEC.EXR_CODE);
            IBS.init(SCCGWA, WS_FL_AREA);
            WS_FL_AREA.WS_FL_INP_TYP = BPREXRD1.KEY.EXR_TYP;
            WS_FL_AREA.WS_FL_INP_TNR = BPREXRD1.KEY.FWD_TENOR;
            if (BPCOIEC.EXR_CODE == null) BPCOIEC.EXR_CODE = "";
            JIBS_tmp_int = BPCOIEC.EXR_CODE.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPCOIEC.EXR_CODE += " ";
            WS_FL_AREA.WS_FL_INP_CCY = BPCOIEC.EXR_CODE.substring(0, 3);
            WS_FL_AREA.WS_FL_INP_DT = BPCITQ.DATA.STR_DT;
            WS_FL_AREA.WS_FL_INP_TM = BPCITQ.DATA.STR_TM;
            R000_GET_FCY_LCY_RATE_HIST();
            if (pgmRtn) return;
            BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_BUY = WS_FL_AREA.WS_FL_OUT_CBUY;
            BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_SELL = WS_FL_AREA.WS_FL_OUT_CSELL;
            BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_BUY = WS_FL_AREA.WS_FL_OUT_BUY;
            BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_SELL = WS_FL_AREA.WS_FL_OUT_SELL;
            BPCITQ.DATA.CCY_EXR_INFOR[1-1].LOC_MID = WS_FL_AREA.WS_FL_OUT_MID;
            BPCITQ.DATA.CCY_EXR_INFOR[1-1].DT = WS_FL_AREA.WS_FL_OUT_EF_DT;
            BPCITQ.DATA.CCY_EXR_INFOR[1-1].TM = WS_FL_AREA.WS_FL_OUT_EF_TM;
            if (BPREXRD1.METH == '0') {
                WS_TQP1_FLG = 'D';
            } else {
                WS_TQP1_FLG = 'I';
            }
            IBS.init(SCCGWA, WS_FL_AREA);
            WS_FL_AREA.WS_FL_INP_TYP = BPREXRD2.KEY.EXR_TYP;
            WS_FL_AREA.WS_FL_INP_TNR = BPREXRD2.KEY.FWD_TENOR;
            if (BPCOIEC.EXR_CODE == null) BPCOIEC.EXR_CODE = "";
            JIBS_tmp_int = BPCOIEC.EXR_CODE.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPCOIEC.EXR_CODE += " ";
            WS_FL_AREA.WS_FL_INP_CCY = BPCOIEC.EXR_CODE.substring(4 - 1, 4 + 3 - 1);
            WS_FL_AREA.WS_FL_INP_DT = BPCITQ.DATA.STR_DT;
            WS_FL_AREA.WS_FL_INP_TM = BPCITQ.DATA.STR_TM;
            R000_GET_FCY_LCY_RATE_HIST();
            if (pgmRtn) return;
            if (BPREXRD2.METH == '0') {
                WS_TQP2_FLG = 'D';
            } else {
                WS_TQP2_FLG = 'I';
            }
            BPCITQ.DATA.CCY_EXR_INFOR[2-1].CS_BUY = WS_FL_AREA.WS_FL_OUT_CBUY;
            BPCITQ.DATA.CCY_EXR_INFOR[2-1].CS_SELL = WS_FL_AREA.WS_FL_OUT_CSELL;
            BPCITQ.DATA.CCY_EXR_INFOR[2-1].FX_BUY = WS_FL_AREA.WS_FL_OUT_BUY;
            BPCITQ.DATA.CCY_EXR_INFOR[2-1].FX_SELL = WS_FL_AREA.WS_FL_OUT_SELL;
            BPCITQ.DATA.CCY_EXR_INFOR[2-1].LOC_MID = WS_FL_AREA.WS_FL_OUT_MID;
            if (WS_FL_AREA.WS_FL_OUT_EF_DT > BPCITQ.DATA.CCY_EXR_INFOR[1-1].DT 
                || (WS_FL_AREA.WS_FL_OUT_EF_DT == BPCITQ.DATA.CCY_EXR_INFOR[1-1].DT 
                && WS_FL_AREA.WS_FL_OUT_EF_TM > BPCITQ.DATA.CCY_EXR_INFOR[1-1].TM)) {
                BPCITQ.DATA.CCY_EXR_INFOR[1-1].DT = WS_FL_AREA.WS_FL_OUT_EF_DT;
                BPCITQ.DATA.CCY_EXR_INFOR[1-1].TM = WS_FL_AREA.WS_FL_OUT_EF_TM;
            }
            CEP.TRC(SCCGWA, BPCITQ.DATA.CCY_EXR_INFOR[2-1].FX_BUY);
            CEP.TRC(SCCGWA, BPCITQ.DATA.CCY_EXR_INFOR[2-1].FX_SELL);
            BPCITQ.DATA.CCY_EXR_INFOR[1-1].EXR_TYPE = BPREXRD1.KEY.EXR_TYP;
            BPCITQ.DATA.CCY_EXR_INFOR[1-1].FWD_TENOR = BPREXRD1.KEY.FWD_TENOR;
            if (BPCOIEC.EXR_CODE == null) BPCOIEC.EXR_CODE = "";
            JIBS_tmp_int = BPCOIEC.EXR_CODE.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPCOIEC.EXR_CODE += " ";
            BPCITQ.DATA.CCY_EXR_INFOR[1-1].CCY = BPCOIEC.EXR_CODE.substring(0, 3);
            if ((WS_B_CASH_FLG == 'N' 
                && WS_S_CASH_FLG == 'N') 
                || (WS_B_CASH_FLG == 'Y' 
                && WS_S_CASH_FLG == 'Y')) {
                IBS.init(SCCGWA, WS_CALC_PRICE_AREA2);
                WS_CALC_PRICE_AREA2.WS_P1_B = BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_BUY;
                WS_CALC_PRICE_AREA2.WS_P1_S = BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_SELL;
                WS_CALC_PRICE_AREA2.WS_P2_B = BPCITQ.DATA.CCY_EXR_INFOR[2-1].CS_BUY;
                WS_CALC_PRICE_AREA2.WS_P2_S = BPCITQ.DATA.CCY_EXR_INFOR[2-1].CS_SELL;
                WS_CALC_PRICE_AREA2.WS_METH_1 = WS_TQP1_FLG;
                WS_CALC_PRICE_AREA2.WS_METH_2 = WS_TQP2_FLG;
                WS_CALC_PRICE_AREA2.WS_RND2 = BPREXRD1.EXR_RND;
                if (BPREXRD1.EXR_PNT == ' ') WS_CALC_PRICE_AREA2.WS_PNT2 = 0;
                else WS_CALC_PRICE_AREA2.WS_PNT2 = Short.parseShort(""+BPREXRD1.EXR_PNT);
                WS_CALC_PRICE_AREA2.WS_P1_UNIT = BPREXRD1.UNT;
                WS_CALC_PRICE_AREA2.WS_P2_UNIT = BPREXRD2.UNT;
                R000_CALC_INDIR_PRICE();
                if (pgmRtn) return;
                BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_BUY = WS_CALC_PRICE_AREA2.WS_NEW_BUY;
                BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_SELL = WS_CALC_PRICE_AREA2.WS_NEW_SELL;
                CEP.TRC(SCCGWA, BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_BUY);
                CEP.TRC(SCCGWA, BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_SELL);
                IBS.init(SCCGWA, WS_CALC_PRICE_AREA2);
                WS_CALC_PRICE_AREA2.WS_P1_B = BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_BUY;
                WS_CALC_PRICE_AREA2.WS_P1_S = BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_SELL;
                WS_CALC_PRICE_AREA2.WS_P2_B = BPCITQ.DATA.CCY_EXR_INFOR[2-1].FX_BUY;
                WS_CALC_PRICE_AREA2.WS_P2_S = BPCITQ.DATA.CCY_EXR_INFOR[2-1].FX_SELL;
                WS_CALC_PRICE_AREA2.WS_METH_1 = WS_TQP1_FLG;
                WS_CALC_PRICE_AREA2.WS_METH_2 = WS_TQP2_FLG;
                WS_CALC_PRICE_AREA2.WS_RND2 = BPREXRD1.EXR_RND;
                if (BPREXRD1.EXR_PNT == ' ') WS_CALC_PRICE_AREA2.WS_PNT2 = 0;
                else WS_CALC_PRICE_AREA2.WS_PNT2 = Short.parseShort(""+BPREXRD1.EXR_PNT);
                WS_CALC_PRICE_AREA2.WS_P1_UNIT = BPREXRD1.UNT;
                WS_CALC_PRICE_AREA2.WS_P2_UNIT = BPREXRD2.UNT;
                R000_CALC_INDIR_PRICE();
                if (pgmRtn) return;
                BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_BUY = WS_CALC_PRICE_AREA2.WS_NEW_BUY;
                BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_SELL = WS_CALC_PRICE_AREA2.WS_NEW_SELL;
                BPCITQ.DATA.CCY_EXR_INFOR[1-1].UNIT = BPREXRD1.UNT;
                CEP.TRC(SCCGWA, BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_BUY);
                CEP.TRC(SCCGWA, BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_SELL);
                BPCITQ.DATA.CCY_EXR_INFOR[1-1].LOC_MID = ( BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_BUY + BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_SELL ) / 2;
                if (BPCITQ.DATA.EX_CODE == null) BPCITQ.DATA.EX_CODE = "";
                JIBS_tmp_int = BPCITQ.DATA.EX_CODE.length();
                for (int i=0;i<6-JIBS_tmp_int;i++) BPCITQ.DATA.EX_CODE += " ";
                if (BPCITQ.DATA.BUY_INFO.BUY_CCY.equalsIgnoreCase(BPCITQ.DATA.EX_CODE.substring(0, 3))) {
                    if (WS_B_CASH_FLG == 'N') {
                        BPCITQ.DATA.EX_RATE = BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_BUY;
                    } else {
                        BPCITQ.DATA.EX_RATE = BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_BUY;
                    }
                } else {
                    if (WS_S_CASH_FLG == 'N') {
                        BPCITQ.DATA.EX_RATE = BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_SELL;
                    } else {
                        BPCITQ.DATA.EX_RATE = BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_SELL;
                    }
                }
            } else {
                if (WS_B_CASH_FLG == 'Y' 
                    && WS_S_CASH_FLG == 'N') {
                    IBS.init(SCCGWA, WS_CALC_PRICE_AREA2);
                    WS_CALC_PRICE_AREA2.WS_P1_B = BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_BUY;
                    WS_CALC_PRICE_AREA2.WS_P1_S = BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_SELL;
                    WS_CALC_PRICE_AREA2.WS_P2_B = BPCITQ.DATA.CCY_EXR_INFOR[2-1].FX_BUY;
                    WS_CALC_PRICE_AREA2.WS_P2_S = BPCITQ.DATA.CCY_EXR_INFOR[2-1].FX_SELL;
                    WS_CALC_PRICE_AREA2.WS_METH_1 = WS_TQP1_FLG;
                    WS_CALC_PRICE_AREA2.WS_METH_2 = WS_TQP2_FLG;
                    WS_CALC_PRICE_AREA2.WS_RND2 = BPREXRD1.EXR_RND;
                    if (BPREXRD1.EXR_PNT == ' ') WS_CALC_PRICE_AREA2.WS_PNT2 = 0;
                    else WS_CALC_PRICE_AREA2.WS_PNT2 = Short.parseShort(""+BPREXRD1.EXR_PNT);
                    WS_CALC_PRICE_AREA2.WS_P1_UNIT = BPREXRD1.UNT;
                    WS_CALC_PRICE_AREA2.WS_P2_UNIT = BPREXRD2.UNT;
                    R000_CALC_INDIR_PRICE();
                    if (pgmRtn) return;
                    BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_BUY = WS_CALC_PRICE_AREA2.WS_NEW_BUY;
                    BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_SELL = WS_CALC_PRICE_AREA2.WS_NEW_SELL;
                    CEP.TRC(SCCGWA, BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_BUY);
                    CEP.TRC(SCCGWA, BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_SELL);
                    IBS.init(SCCGWA, WS_CALC_PRICE_AREA2);
                    WS_CALC_PRICE_AREA2.WS_P1_B = BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_BUY;
                    WS_CALC_PRICE_AREA2.WS_P1_S = BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_SELL;
                    WS_CALC_PRICE_AREA2.WS_P2_B = BPCITQ.DATA.CCY_EXR_INFOR[2-1].FX_BUY;
                    WS_CALC_PRICE_AREA2.WS_P2_S = BPCITQ.DATA.CCY_EXR_INFOR[2-1].FX_SELL;
                    WS_CALC_PRICE_AREA2.WS_METH_1 = WS_TQP1_FLG;
                    WS_CALC_PRICE_AREA2.WS_METH_2 = WS_TQP2_FLG;
                    WS_CALC_PRICE_AREA2.WS_RND2 = BPREXRD1.EXR_RND;
                    if (BPREXRD1.EXR_PNT == ' ') WS_CALC_PRICE_AREA2.WS_PNT2 = 0;
                    else WS_CALC_PRICE_AREA2.WS_PNT2 = Short.parseShort(""+BPREXRD1.EXR_PNT);
                    WS_CALC_PRICE_AREA2.WS_P1_UNIT = BPREXRD1.UNT;
                    WS_CALC_PRICE_AREA2.WS_P2_UNIT = BPREXRD2.UNT;
                    R000_CALC_INDIR_PRICE();
                    if (pgmRtn) return;
                    BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_BUY = WS_CALC_PRICE_AREA2.WS_NEW_BUY;
                    BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_SELL = WS_CALC_PRICE_AREA2.WS_NEW_SELL;
                    BPCITQ.DATA.CCY_EXR_INFOR[1-1].UNIT = BPREXRD1.UNT;
                    CEP.TRC(SCCGWA, BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_BUY);
                    CEP.TRC(SCCGWA, BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_SELL);
                    BPCITQ.DATA.CCY_EXR_INFOR[1-1].LOC_MID = ( BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_BUY + BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_SELL ) / 2;
                    if (BPCITQ.DATA.EX_CODE == null) BPCITQ.DATA.EX_CODE = "";
                    JIBS_tmp_int = BPCITQ.DATA.EX_CODE.length();
                    for (int i=0;i<6-JIBS_tmp_int;i++) BPCITQ.DATA.EX_CODE += " ";
                    if (BPCITQ.DATA.BUY_INFO.BUY_CCY.equalsIgnoreCase(BPCITQ.DATA.EX_CODE.substring(0, 3))) {
                        if (WS_B_CASH_FLG == 'Y') {
                            BPCITQ.DATA.EX_RATE = BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_BUY;
                        }
                    } else {
                        if (WS_S_CASH_FLG == 'N') {
                            BPCITQ.DATA.EX_RATE = BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_SELL;
                        }
                    }
                }
                if (WS_B_CASH_FLG == 'N' 
                    && WS_S_CASH_FLG == 'Y') {
                    IBS.init(SCCGWA, WS_CALC_PRICE_AREA2);
                    WS_CALC_PRICE_AREA2.WS_P1_B = BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_BUY;
                    WS_CALC_PRICE_AREA2.WS_P1_S = BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_SELL;
                    WS_CALC_PRICE_AREA2.WS_P2_B = BPCITQ.DATA.CCY_EXR_INFOR[2-1].CS_BUY;
                    WS_CALC_PRICE_AREA2.WS_P2_S = BPCITQ.DATA.CCY_EXR_INFOR[2-1].CS_SELL;
                    WS_CALC_PRICE_AREA2.WS_METH_1 = WS_TQP1_FLG;
                    WS_CALC_PRICE_AREA2.WS_METH_2 = WS_TQP2_FLG;
                    WS_CALC_PRICE_AREA2.WS_RND2 = BPREXRD1.EXR_RND;
                    if (BPREXRD1.EXR_PNT == ' ') WS_CALC_PRICE_AREA2.WS_PNT2 = 0;
                    else WS_CALC_PRICE_AREA2.WS_PNT2 = Short.parseShort(""+BPREXRD1.EXR_PNT);
                    WS_CALC_PRICE_AREA2.WS_P1_UNIT = BPREXRD1.UNT;
                    WS_CALC_PRICE_AREA2.WS_P2_UNIT = BPREXRD2.UNT;
                    R000_CALC_INDIR_PRICE();
                    if (pgmRtn) return;
                    BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_BUY = WS_CALC_PRICE_AREA2.WS_NEW_BUY;
                    BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_SELL = WS_CALC_PRICE_AREA2.WS_NEW_SELL;
                    CEP.TRC(SCCGWA, BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_BUY);
                    CEP.TRC(SCCGWA, BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_SELL);
                    IBS.init(SCCGWA, WS_CALC_PRICE_AREA2);
                    WS_CALC_PRICE_AREA2.WS_P1_B = BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_BUY;
                    WS_CALC_PRICE_AREA2.WS_P1_S = BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_SELL;
                    WS_CALC_PRICE_AREA2.WS_P2_B = BPCITQ.DATA.CCY_EXR_INFOR[2-1].FX_BUY;
                    WS_CALC_PRICE_AREA2.WS_P2_S = BPCITQ.DATA.CCY_EXR_INFOR[2-1].FX_SELL;
                    WS_CALC_PRICE_AREA2.WS_METH_1 = WS_TQP1_FLG;
                    WS_CALC_PRICE_AREA2.WS_METH_2 = WS_TQP2_FLG;
                    WS_CALC_PRICE_AREA2.WS_RND2 = BPREXRD1.EXR_RND;
                    if (BPREXRD1.EXR_PNT == ' ') WS_CALC_PRICE_AREA2.WS_PNT2 = 0;
                    else WS_CALC_PRICE_AREA2.WS_PNT2 = Short.parseShort(""+BPREXRD1.EXR_PNT);
                    WS_CALC_PRICE_AREA2.WS_P1_UNIT = BPREXRD1.UNT;
                    WS_CALC_PRICE_AREA2.WS_P2_UNIT = BPREXRD2.UNT;
                    R000_CALC_INDIR_PRICE();
                    if (pgmRtn) return;
                    BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_BUY = WS_CALC_PRICE_AREA2.WS_NEW_BUY;
                    BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_SELL = WS_CALC_PRICE_AREA2.WS_NEW_SELL;
                    BPCITQ.DATA.CCY_EXR_INFOR[1-1].UNIT = BPREXRD1.UNT;
                    CEP.TRC(SCCGWA, BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_BUY);
                    CEP.TRC(SCCGWA, BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_SELL);
                    BPCITQ.DATA.CCY_EXR_INFOR[1-1].LOC_MID = ( BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_BUY + BPCITQ.DATA.CCY_EXR_INFOR[1-1].FX_SELL ) / 2;
                    if (BPCITQ.DATA.EX_CODE == null) BPCITQ.DATA.EX_CODE = "";
                    JIBS_tmp_int = BPCITQ.DATA.EX_CODE.length();
                    for (int i=0;i<6-JIBS_tmp_int;i++) BPCITQ.DATA.EX_CODE += " ";
                    if (BPCITQ.DATA.BUY_INFO.BUY_CCY.equalsIgnoreCase(BPCITQ.DATA.EX_CODE.substring(0, 3))) {
                        if (WS_B_CASH_FLG == 'N') {
                            BPCITQ.DATA.EX_RATE = BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_BUY;
                        }
                    } else {
                        if (WS_S_CASH_FLG == 'Y') {
                            BPCITQ.DATA.EX_RATE = BPCITQ.DATA.CCY_EXR_INFOR[1-1].CS_SELL;
                        }
                    }
                }
            }
        }
    }
    public void R000_CALC_INDIR_PRICE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CALC_PRICE_AREA2.WS_P1_B);
        CEP.TRC(SCCGWA, WS_CALC_PRICE_AREA2.WS_P1_S);
        CEP.TRC(SCCGWA, WS_CALC_PRICE_AREA2.WS_P2_B);
        CEP.TRC(SCCGWA, WS_CALC_PRICE_AREA2.WS_P2_S);
        CEP.TRC(SCCGWA, WS_CALC_PRICE_AREA2.WS_METH_1);
        CEP.TRC(SCCGWA, WS_CALC_PRICE_AREA2.WS_METH_2);
        if (WS_CALC_PRICE_AREA2.WS_P1_B == 0 
            || WS_CALC_PRICE_AREA2.WS_P1_S == 0 
            || WS_CALC_PRICE_AREA2.WS_P2_B == 0 
            || WS_CALC_PRICE_AREA2.WS_P2_S == 0) {
            WS_CALC_PRICE_AREA2.WS_TMP_BUY = 0;
            WS_CALC_PRICE_AREA2.WS_TMP_SELL = 0;
        } else {
            if (WS_CALC_PRICE_AREA2.WS_METH_1 == 'D'
                && WS_CALC_PRICE_AREA2.WS_METH_2 == 'D') {
                WS_CALC_PRICE_AREA2.WS_TMP_BUY = WS_CALC_PRICE_AREA2.WS_P1_B * WS_CALC_PRICE_AREA2.WS_P2_UNIT / WS_CALC_PRICE_AREA2.WS_P2_S;
                WS_CALC_PRICE_AREA2.WS_TMP_SELL = WS_CALC_PRICE_AREA2.WS_P1_S * WS_CALC_PRICE_AREA2.WS_P2_UNIT / WS_CALC_PRICE_AREA2.WS_P2_B;
            } else if (WS_CALC_PRICE_AREA2.WS_METH_1 == 'I'
                && WS_CALC_PRICE_AREA2.WS_METH_2 == 'I') {
                WS_CALC_PRICE_AREA2.WS_TMP_BUY = WS_CALC_PRICE_AREA2.WS_P2_B * WS_CALC_PRICE_AREA2.WS_P1_UNIT / WS_CALC_PRICE_AREA2.WS_P1_S;
                WS_CALC_PRICE_AREA2.WS_TMP_SELL = WS_CALC_PRICE_AREA2.WS_P2_S * WS_CALC_PRICE_AREA2.WS_P1_UNIT / WS_CALC_PRICE_AREA2.WS_P1_B;
            } else if (WS_CALC_PRICE_AREA2.WS_METH_1 == 'D'
                && WS_CALC_PRICE_AREA2.WS_METH_2 == 'I') {
                WS_CALC_PRICE_AREA2.WS_TMP_BUY = WS_CALC_PRICE_AREA2.WS_P1_B * WS_CALC_PRICE_AREA2.WS_P2_B / WS_CALC_PRICE_AREA2.WS_P2_UNIT;
                WS_CALC_PRICE_AREA2.WS_TMP_SELL = WS_CALC_PRICE_AREA2.WS_P1_S * WS_CALC_PRICE_AREA2.WS_P2_S / WS_CALC_PRICE_AREA2.WS_P2_UNIT;
            } else if (WS_CALC_PRICE_AREA2.WS_METH_1 == 'I'
                && WS_CALC_PRICE_AREA2.WS_METH_2 == 'D') {
                WS_CALC_PRICE_AREA2.WS_TMP_BUY = 1 * WS_CALC_PRICE_AREA2.WS_P1_UNIT * WS_CALC_PRICE_AREA2.WS_P2_UNIT / ( WS_CALC_PRICE_AREA2.WS_P1_S * WS_CALC_PRICE_AREA2.WS_P2_S );
                WS_CALC_PRICE_AREA2.WS_TMP_SELL = 1 * WS_CALC_PRICE_AREA2.WS_P1_UNIT * WS_CALC_PRICE_AREA2.WS_P2_UNIT / ( WS_CALC_PRICE_AREA2.WS_P1_B * WS_CALC_PRICE_AREA2.WS_P2_B );
            } else {
            }
        }
        CEP.TRC(SCCGWA, WS_CALC_PRICE_AREA2.WS_TMP_BUY);
        CEP.TRC(SCCGWA, WS_CALC_PRICE_AREA2.WS_TMP_SELL);
        CEP.TRC(SCCGWA, WS_CALC_PRICE_AREA2.WS_PNT2);
        CEP.TRC(SCCGWA, WS_CALC_PRICE_AREA2.WS_RND2);
        WS_CALC_PRICE.WS_K = (short) (8 - WS_CALC_PRICE_AREA2.WS_PNT2);
        if (WS_CALC_PRICE_AREA2.WS_RND2 == 0) {
            WS_CALC_PRICE_AREA2.WS_NEW_BUY = Math.pow(WS_CALC_PRICE_AREA2.WS_TMP_BUY / ( 10, WS_CALC_PRICE.WS_K ));
            WS_CALC_PRICE_AREA2.WS_NEW_BUY = Math.pow(WS_CALC_PRICE_AREA2.WS_NEW_BUY * ( 10, WS_CALC_PRICE.WS_K ));
            WS_CALC_PRICE_AREA2.WS_NEW_SELL = Math.pow(WS_CALC_PRICE_AREA2.WS_TMP_SELL / ( 10, WS_CALC_PRICE.WS_K ));
            WS_CALC_PRICE_AREA2.WS_NEW_SELL = Math.pow(WS_CALC_PRICE_AREA2.WS_NEW_SELL * ( 10, WS_CALC_PRICE.WS_K ));
        } else if (WS_CALC_PRICE_AREA2.WS_RND2 == 1) {
            WS_CALC_PRICE_AREA2.WS_NEW_BUY = Math.pow(WS_CALC_PRICE_AREA2.WS_TMP_BUY / ( 10, WS_CALC_PRICE.WS_K ));
