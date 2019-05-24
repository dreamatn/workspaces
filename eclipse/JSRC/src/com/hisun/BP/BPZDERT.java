package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;

public class BPZDERT {
    boolean pgmRtn = false;
    String CPN_INQ_PUB_PARM = "BP-PARM-READ      ";
    String CPN_INQ_PUB_CODE = "BP-P-INQ-PC       ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_R_EXRS = "BP-R-EXRD-B       ";
    String CPN_R_EXRM = "BP-R-EXRD-M       ";
    String CPN_R_TQPS = "BP-R-TQP-B        ";
    String CPN_R_SQTPH = "BP-R-SQTPH-B      ";
    String CPN_REC_NHIS = "BP-REC-NHIS       ";
    String CPY_BPREXRD = "BPREXRD ";
    String K_OUTPUT_FMT = "BP270";
    String K_OUTPUT_FMT_X = "BPX01";
    String K_FWD_TENOR = "FWDT ";
    int K_MAX_ROW = 50;
    int K_MAX_COL = 99;
    int K_COL_CNT = 13;
    int K_MAX_DATE = 99991231;
    String WS_CCY = " ";
    String WS_FWD_TENOR = " ";
    short WS_MPAG_CNT = 0;
    BPZDERT_WS_MPAG_DATA WS_MPAG_DATA = new BPZDERT_WS_MPAG_DATA();
    char WS_EX_RATE_USE_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCO270 BPCO270 = new BPCO270();
    BPREXRT BPREXRT = new BPREXRT();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPCRQTPH BPCRQTPH = new BPCRQTPH();
    BPCRTQPS BPCRTQPS = new BPCRTQPS();
    BPCTEXRM BPCTEXRM = new BPCTEXRM();
    BPCREXRS BPCREXRS = new BPCREXRS();
    BPCRERGR BPCRERGR = new BPCRERGR();
    BPCRSPCA BPCRSPCA = new BPCRSPCA();
    BPREXRD BPREXRD = new BPREXRD();
    BPREXRD BPREXRDO = new BPREXRD();
    BPREXRD BPREXRDN = new BPREXRD();
    BPRSQTPH BPRSQTPH = new BPRSQTPH();
    BPRTQP BPRTQP = new BPRTQP();
    BPRERGR BPRERGR = new BPRERGR();
    BPRSPCA BPRSPCA = new BPRSPCA();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCDERT BPCDERT;
    public void MP(SCCGWA SCCGWA, BPCDERT BPCDERT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCDERT = BPCDERT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZDERT return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCDERT.RC);
        IBS.init(SCCGWA, BPREXRDN);
        IBS.init(SCCGWA, BPREXRDO);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCDERT);
        B001_CHECK_INPUT();
        if (pgmRtn) return;
        if (BPCDERT.FUNC == 'I') {
            B010_INQUIRE_PROC();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCDERT.FUNC == 'A') {
            B020_CREATE_PROC();
            if (pgmRtn) return;
            B080_HISTORY_PROC();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCDERT.FUNC == 'M') {
            B030_MODIFY_PROC();
            if (pgmRtn) return;
            B080_HISTORY_PROC();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCDERT.FUNC == 'B') {
            B040_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (BPCDERT.FUNC == 'D') {
            B050_DELETE_PROC();
            if (pgmRtn) return;
            B080_HISTORY_PROC();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCDERT.FUNC == 'N') {
            B010_INQUIRE_PROC();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCDERT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B001_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCDERT.FUNC == 'A' 
            || BPCDERT.FUNC == 'M') {
            CEP.TRC(SCCGWA, BPCDERT.EXR_TYP);
            CEP.TRC(SCCGWA, BPCDERT.CCY);
            CEP.TRC(SCCGWA, BPCDERT.UNT);
            CEP.TRC(SCCGWA, BPCDERT.METH);
            CEP.TRC(SCCGWA, BPCDERT.EXR_PNT);
            CEP.TRC(SCCGWA, BPCDERT.EXR_RND);
            if (BPCDERT.EXR_TYP.trim().length() == 0 
                || BPCDERT.CCY.trim().length() == 0 
                || BPCDERT.UNT == ' ' 
                || BPCDERT.METH == ' ' 
                || BPCDERT.EXR_PNT == ' ' 
                || BPCDERT.EXR_RND == ' ') {
                CEP.TRC(SCCGWA, "111");
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCDERT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (BPCDERT.FUNC == 'I' 
            || BPCDERT.FUNC == 'D' 
            || BPCDERT.FUNC == 'N') {
            if (BPCDERT.EXR_TYP.trim().length() == 0 
                || BPCDERT.CCY.trim().length() == 0) {
                CEP.TRC(SCCGWA, "222");
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCDERT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (BPCDERT.CCY.trim().length() > 0) {
            WS_CCY = BPCDERT.CCY;
            R000_CHECK_CCY();
            if (pgmRtn) return;
        }
        if (BPCDERT.EXR_TYP.trim().length() > 0) {
            IBS.init(SCCGWA, BPREXRT);
            IBS.init(SCCGWA, BPCPRMR);
            BPREXRT.KEY.TYP = "EXRT";
            BPREXRT.KEY.CD = BPCDERT.EXR_TYP;
            BPCPRMR.DAT_PTR = BPREXRT;
            S000_CALL_BPZPRMR();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPREXRT.DAT_TXT.FWD_IND);
            if (BPCPRMR.RC.RC_RTNCODE != 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INVALID_EXR_TYP, BPCDERT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (BPREXRT.DAT_TXT.FWD_IND == '1' 
                && BPCDERT.FWD_TENOR.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_IN_FWD_TENOR, BPCDERT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (BPCDERT.FWD_TENOR.trim().length() > 0) {
            WS_FWD_TENOR = BPCDERT.FWD_TENOR;
            R000_CHECK_FWD_TENOR();
            if (pgmRtn) return;
        }
    }
    public void B010_INQUIRE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPREXRD);
        IBS.init(SCCGWA, BPCTEXRM);
        BPREXRD.KEY.EXR_TYP = BPCDERT.EXR_TYP;
        BPREXRD.KEY.FWD_TENOR = BPCDERT.FWD_TENOR;
        BPREXRD.KEY.CCY = BPCDERT.CCY;
        BPREXRD.KEY.CORR_CCY = BPCDERT.CORR_CCY;
        BPCTEXRM.INFO.FUNC = 'Q';
        S000_CALL_BPZTEXRM();
        if (pgmRtn) return;
        if (BPCTEXRM.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOT_EXIST, BPCDERT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_CREATE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPREXRD);
        IBS.init(SCCGWA, BPCREXRS);
        BPREXRD.KEY.EXR_TYP = BPCDERT.EXR_TYP;
        BPCREXRS.INFO.FUNC = '6';
        S000_CALL_BPZTEXRS();
        if (pgmRtn) return;
        BPCREXRS.INFO.FUNC = 'R';
        S000_CALL_BPZTEXRS();
        if (pgmRtn) return;
        if (BPCREXRS.INFO.RTN_INFO == 'N') {
            BPCDERT.PNL_SQN = 1;
        } else {
            BPCDERT.PNL_SQN = (short) (BPREXRD.PNL_SQN + 1);
        }
        BPCREXRS.INFO.FUNC = 'E';
        S000_CALL_BPZTEXRS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCDERT.PNL_SQN);
        IBS.init(SCCGWA, BPCTEXRM);
        IBS.init(SCCGWA, BPREXRD);
        R000_TRANS_DATA();
        if (pgmRtn) return;
        BPREXRD.CRE_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPREXRD.CRE_TM = SCCGWA.COMM_AREA.TR_TIME;
        BPREXRD.CRE_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPREXRD.CRE_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCTEXRM.INFO.FUNC = 'C';
        S000_CALL_BPZTEXRM();
        if (pgmRtn) return;
        if (BPCTEXRM.RETURN_INFO == 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_EX_RATE_TYPE_EXIST, BPCDERT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_MODIFY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPREXRD);
        IBS.init(SCCGWA, BPCREXRS);
        BPREXRD.KEY.EXR_TYP = BPCDERT.EXR_TYP;
        BPREXRD.PNL_SQN = BPCDERT.PNL_SQN;
        CEP.TRC(SCCGWA, BPCDERT.EXR_TYP);
        CEP.TRC(SCCGWA, BPCDERT.PNL_SQN);
        BPCREXRS.INFO.FUNC = '6';
        S000_CALL_BPZTEXRS();
        if (pgmRtn) return;
        BPCREXRS.INFO.FUNC = 'R';
        S000_CALL_BPZTEXRS();
        if (pgmRtn) return;
        while (BPCREXRS.INFO.RTN_INFO != 'N') {
            if ((!BPCDERT.FWD_TENOR.equalsIgnoreCase(BPREXRD.KEY.FWD_TENOR)) 
                || (!BPCDERT.CCY.equalsIgnoreCase(BPREXRD.KEY.CCY))) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_SAME_SEQ, BPCDERT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            BPCREXRS.INFO.FUNC = 'R';
            S000_CALL_BPZTEXRS();
            if (pgmRtn) return;
        }
        BPCREXRS.INFO.FUNC = 'E';
        S000_CALL_BPZTEXRS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPREXRD);
        IBS.init(SCCGWA, BPCTEXRM);
        BPREXRD.KEY.EXR_TYP = BPCDERT.EXR_TYP;
        BPREXRD.KEY.FWD_TENOR = BPCDERT.FWD_TENOR;
        BPREXRD.KEY.CCY = BPCDERT.CCY;
        BPREXRD.KEY.CORR_CCY = BPCDERT.CORR_CCY;
        BPCTEXRM.INFO.FUNC = 'R';
        S000_CALL_BPZTEXRM();
        if (pgmRtn) return;
        if (BPCTEXRM.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOT_EXIST, BPCDERT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPREXRD, BPREXRDO);
        R000_TRANS_DATA();
        if (pgmRtn) return;
        BPREXRD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPREXRD.UPD_TM = SCCGWA.COMM_AREA.TR_TIME;
        BPREXRD.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPREXRD.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        IBS.CLONE(SCCGWA, BPREXRD, BPREXRDN);
        BPCTEXRM.INFO.FUNC = 'U';
        S000_CALL_BPZTEXRM();
        if (pgmRtn) return;
    }
    public void B050_DELETE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPREXRD);
        IBS.init(SCCGWA, BPCTEXRM);
        BPREXRD.KEY.EXR_TYP = BPCDERT.EXR_TYP;
        BPREXRD.KEY.FWD_TENOR = BPCDERT.FWD_TENOR;
        BPREXRD.KEY.CCY = BPCDERT.CCY;
        BPREXRD.KEY.CORR_CCY = BPCDERT.CORR_CCY;
        BPCTEXRM.INFO.FUNC = 'R';
        S000_CALL_BPZTEXRM();
        if (pgmRtn) return;
        if (BPCTEXRM.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOT_EXIST, BPCDERT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPREXRT);
        IBS.init(SCCGWA, BPCPRMR);
        WS_EX_RATE_USE_FLG = ' ';
        BPREXRT.KEY.TYP = "EXRT";
        BPREXRT.KEY.CD = BPCDERT.EXR_TYP;
        BPCPRMR.DAT_PTR = BPREXRT;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INVALID_EXR_TYP, BPCDERT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "DEVHZ527");
        if (BPREXRT.DAT_TXT.SUM_IND == '1') {
            IBS.init(SCCGWA, BPCRTQPS);
            IBS.init(SCCGWA, BPRTQP);
            BPRTQP.KEY.EXR_TYP = BPCDERT.EXR_TYP;
            BPRTQP.KEY.FWD_TENOR = BPCDERT.FWD_TENOR;
            BPRTQP.KEY.CCY = BPCDERT.CCY;
            BPRTQP.KEY.CORR_CCY = BPCDERT.CORR_CCY;
            BPCRTQPS.INFO.FUNC = '3';
            S000_CALL_BPZTTQPS();
            if (pgmRtn) return;
            BPCRTQPS.INFO.FUNC = 'R';
            S000_CALL_BPZTTQPS();
            if (pgmRtn) return;
            if (BPCRTQPS.INFO.RTN_INFO == 'Y') {
                WS_EX_RATE_USE_FLG = 'Y';
            }
            BPCRTQPS.INFO.FUNC = 'E';
            S000_CALL_BPZTTQPS();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, BPRSQTPH);
            IBS.init(SCCGWA, BPCRQTPH);
            BPRSQTPH.KEY.EXR_TYP = BPCDERT.EXR_TYP;
            BPRSQTPH.KEY.CCY = BPCDERT.CCY;
            BPCRQTPH.INFO.FUNC = 'S';
            S000_CALL_BPZTQTPH();
            if (pgmRtn) return;
            BPCRQTPH.INFO.FUNC = 'R';
            S000_CALL_BPZTQTPH();
            if (pgmRtn) return;
            if (BPCRQTPH.INFO.RTN_INFO == 'Y') {
                WS_EX_RATE_USE_FLG = 'Y';
            }
            BPCRQTPH.INFO.FUNC = 'E';
            S000_CALL_BPZTQTPH();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "CHECK IF IN GEN RULES");
        IBS.init(SCCGWA, BPRERGR);
        IBS.init(SCCGWA, BPCRERGR);
        BPRERGR.EXR_TYP = BPCDERT.EXR_TYP;
        BPRERGR.KEY.CCY = BPCDERT.CCY;
        BPCRERGR.INFO.FUNC = '8';
        S000_CALL_BPZTERGR();
        if (pgmRtn) return;
        BPCRERGR.INFO.FUNC = 'R';
        S000_CALL_BPZTERGR();
        if (pgmRtn) return;
        while (BPCRERGR.INFO.RTN_INFO != 'N' 
            && WS_EX_RATE_USE_FLG != 'Y') {
            if (BPRERGR.FWD_TENOR.equalsIgnoreCase(BPCDERT.FWD_TENOR)) {
                WS_EX_RATE_USE_FLG = 'Y';
            }
            BPCRERGR.INFO.FUNC = 'R';
            S000_CALL_BPZTERGR();
            if (pgmRtn) return;
        }
        BPCRERGR.INFO.FUNC = 'E';
        S000_CALL_BPZTERGR();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRERGR);
        IBS.init(SCCGWA, BPCRERGR);
        BPRERGR.BASE_EXR_TYP = BPCDERT.EXR_TYP;
        BPRERGR.BASE_CCY = BPCDERT.CCY;
        BPRERGR.BASE_FWD_TENOR = BPCDERT.FWD_TENOR;
        BPCRERGR.INFO.FUNC = '9';
        S000_CALL_BPZTERGR();
        if (pgmRtn) return;
        BPCRERGR.INFO.FUNC = 'R';
        S000_CALL_BPZTERGR();
        if (pgmRtn) return;
        if (BPCRERGR.INFO.RTN_INFO == 'Y') {
            WS_EX_RATE_USE_FLG = 'Y';
        }
        BPCRERGR.INFO.FUNC = 'E';
        S000_CALL_BPZTERGR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CHECK IF IN BPTSPCA");
        IBS.init(SCCGWA, BPCRSPCA);
        IBS.init(SCCGWA, BPRSPCA);
        BPRSPCA.KEY.CHK_TYP = '%';
        BPRSPCA.KEY.EXR_TYP = BPCDERT.EXR_TYP;
        BPRSPCA.KEY.FWD_TENOR = BPCDERT.FWD_TENOR;
        BPRSPCA.KEY.CCY = BPCDERT.CCY;
        BPCRSPCA.INFO.FUNC = 'S';
        S000_CALL_BPZTSPCA();
        if (pgmRtn) return;
        BPCRSPCA.INFO.FUNC = 'R';
        S000_CALL_BPZTSPCA();
        if (pgmRtn) return;
        if (BPCRSPCA.INFO.RTN_INFO == 'Y') {
            WS_EX_RATE_USE_FLG = 'Y';
        }
        BPCRSPCA.INFO.FUNC = 'E';
        S000_CALL_BPZTSPCA();
        if (pgmRtn) return;
        if (WS_EX_RATE_USE_FLG == 'Y') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_EX_RATE_TYPE_IN_USE, BPCDERT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        BPCTEXRM.INFO.FUNC = 'D';
        S000_CALL_BPZTEXRM();
        if (pgmRtn) return;
        if (BPCTEXRM.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOT_EXIST, BPCDERT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B040_BROWSE_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCDERT.EXR_TYP);
        CEP.TRC(SCCGWA, BPCDERT.CCY);
        IBS.init(SCCGWA, BPREXRD);
        IBS.init(SCCGWA, BPCREXRS);
        CEP.TRC(SCCGWA, BPCDERT.EXR_TYP);
        CEP.TRC(SCCGWA, BPCDERT.CCY);
        if (BPCDERT.EXR_TYP.trim().length() == 0) {
            BPREXRD.KEY.EXR_TYP = "%%%";
        } else {
            BPREXRD.KEY.EXR_TYP = BPCDERT.EXR_TYP;
        }
        if (BPCDERT.CCY.trim().length() == 0) {
            BPREXRD.KEY.CCY = "%%%";
        } else {
            BPREXRD.KEY.CCY = BPCDERT.CCY;
        }
        BPCREXRS.INFO.FUNC = '1';
        S000_CALL_BPZTEXRS();
        if (pgmRtn) return;
        BPCREXRS.INFO.FUNC = 'R';
        S000_CALL_BPZTEXRS();
        if (pgmRtn) return;
        if (BPCREXRS.INFO.RTN_INFO == 'Y') {
            B040_01_01_OUT_TITLE();
            if (pgmRtn) return;
        }
        while (BPCREXRS.INFO.RTN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E') {
            WS_MPAG_CNT += 1;
            B040_01_02_OUT_BRW_DATA();
            if (pgmRtn) return;
            BPCREXRS.INFO.FUNC = 'R';
            S000_CALL_BPZTEXRS();
            if (pgmRtn) return;
        }
        BPCREXRS.INFO.FUNC = 'E';
        S000_CALL_BPZTEXRS();
        if (pgmRtn) return;
    }
    public void B040_01_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 144;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B040_01_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_MPAG_DATA);
        WS_MPAG_DATA.WS_L_EXR_TYP = BPREXRD.KEY.EXR_TYP;
        WS_MPAG_DATA.WS_L_FWD_TENOR = BPREXRD.KEY.FWD_TENOR;
        WS_MPAG_DATA.WS_L_CCY = BPREXRD.KEY.CCY;
        WS_MPAG_DATA.WS_L_UNT = BPREXRD.UNT;
        WS_MPAG_DATA.WS_L_METH = BPREXRD.METH;
        WS_MPAG_DATA.WS_L_EXR_PNT = BPREXRD.EXR_PNT;
        WS_MPAG_DATA.WS_L_EXR_RND = BPREXRD.EXR_RND;
        WS_MPAG_DATA.WS_L_PNL_SQN = BPREXRD.PNL_SQN;
        WS_MPAG_DATA.WS_L_RMK = BPREXRD.RMK;
        CEP.TRC(SCCGWA, WS_MPAG_CNT);
        CEP.TRC(SCCGWA, BPREXRD.KEY.EXR_TYP);
        CEP.TRC(SCCGWA, BPREXRD.KEY.FWD_TENOR);
        CEP.TRC(SCCGWA, BPREXRD.KEY.CCY);
        CEP.TRC(SCCGWA, BPREXRD.KEY.CORR_CCY);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_MPAG_DATA);
        SCCMPAG.DATA_LEN = 144;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B080_HISTORY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        if (BPCDERT.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
        }
        if (BPCDERT.FUNC == 'M') {
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.OLD_DAT_PT = BPREXRDO;
            BPCPNHIS.INFO.NEW_DAT_PT = BPREXRDN;
        }
        if (BPCDERT.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
        }
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        CEP.TRC(SCCGWA, BPREXRDO.KEY);
