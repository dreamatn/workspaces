package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSIATM {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG      ";
    String CPN_R_BK_MT = "BP-R-IRAT-MAINT";
    String BP_IRPD_MIAN = "BP-S-IRPD-MAINT";
    String BP_QPCD_MAIN = "BP-P-INQ-PC";
    String K_OUTPUT_FMT = "BP331";
    String K_BASE_TYPE = "RBASE";
    String K_TENOR = "RTENO";
    String K_CMP_TXN_HIS = "BP-REC-NHIS";
    String K_HIS_REMARKS = "PARMETER TYPE MAINTENANCE";
    String K_HIS_COPYBOOK_NAME = "BPCHATM";
    BPZSIATM_WS_OUTPUT_DATA WS_OUTPUT_DATA = new BPZSIATM_WS_OUTPUT_DATA();
    char WS_OLD_FMT = ' ';
    double WS_OLD_HIGH = 0;
    double WS_OLD_LOW = 0;
    BPZSIATM_WS_HIS_REF WS_HIS_REF = new BPZSIATM_WS_HIS_REF();
    BPZSIATM_WS_UPDATE_DATA WS_UPDATE_DATA = new BPZSIATM_WS_UPDATE_DATA();
    String WS_ERR_MSG = " ";
    char WS_DAY_TYP_FLG = ' ';
    char WS_OUTPUT_FLG = ' ';
    char WS_STOP_FLG = ' ';
    char WS_FIT_COND_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCRIATM BPCRIATM = new BPCRIATM();
    BPCSIPDM BPCSIPDM = new BPCSIPDM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCHATM BPCOHATM = new BPCHATM();
    BPCHATM BPCNHATM = new BPCHATM();
    BPRIRAT BPRIRAT = new BPRIRAT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPCIATMO BPCIATMO = new BPCIATMO();
    SCCGWA SCCGWA;
    BPCSIATM BPCSIATM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSIATM BPCSIATM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSIATM = BPCSIATM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSIATM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        if (BPCSIATM.FUNC == 'Q'
            || BPCSIATM.FUNC == 'A'
            || BPCSIATM.FUNC == 'U'
            || BPCSIATM.FUNC == 'C'
            || BPCSIATM.FUNC == 'D') {
            B210_KEY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSIATM.FUNC == 'B') {
            B220_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSIATM.BR);
        CEP.TRC(SCCGWA, BPCSIATM.CCY);
        CEP.TRC(SCCGWA, BPCSIATM.B_TYPE);
        CEP.TRC(SCCGWA, BPCSIATM.TENOR);
        CEP.TRC(SCCGWA, BPCSIATM.FLG);
        if (BPCSIATM.FUNC == 'A' 
            || BPCSIATM.FUNC == 'U') {
            if (BPCSIATM.BR == ' ' 
                || BPCSIATM.BR == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_BR_NO_VALID;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                R000_CHECK_BRANCH();
                if (pgmRtn) return;
            }
        }
        if (BPCSIATM.FLG == 'Y') {
            if (BPCSIATM.CCY.trim().length() == 0 
                && BPCSIATM.B_TYPE.trim().length() == 0 
                && BPCSIATM.TENOR.trim().length() == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_INPUT_ERROR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            if (BPCSIATM.CCY.trim().length() == 0 
                && BPCSIATM.B_TYPE.trim().length() == 0 
                && BPCSIATM.TENOR.trim().length() == 0 
                && BPCSIATM.BR == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_INPUT_ERROR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (!(BPCSIATM.OUTPUT_FLG == 'Y' 
            || BPCSIATM.OUTPUT_FLG == 'N')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSIATM.FUNC == 'A' 
            || BPCSIATM.FUNC == 'U' 
            || BPCSIATM.FUNC == 'D' 
            || BPCSIATM.FUNC == 'Q' 
            || BPCSIATM.FUNC == 'C') {
            if (BPCSIATM.FLG == 'Y') {
                if (BPCSIATM.TENOR.equalsIgnoreCase("0") 
                    || BPCSIATM.CCY.trim().length() == 0 
                    || BPCSIATM.B_TYPE.trim().length() == 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_INPUT_ERROR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            } else {
                if (BPCSIATM.TENOR.equalsIgnoreCase("0") 
                    || BPCSIATM.CCY.trim().length() == 0 
                    || BPCSIATM.B_TYPE.trim().length() == 0 
                    || BPCSIATM.BR == 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_INPUT_ERROR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        if (BPCSIATM.FUNC == 'B') {
            if (BPCSIATM.CCY.trim().length() == 0 
                && BPCSIATM.B_TYPE.trim().length() == 0 
                && BPCSIATM.TENOR.trim().length() == 0 
                && BPCSIATM.BR == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_INPUT_ERROR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCSIATM.FUNC == 'U') {
            WS_UPDATE_DATA.WS_SIATM_FLG = BPCSIATM.FLG;
            WS_UPDATE_DATA.WS_SIATM_BR = BPCSIATM.BR;
            WS_UPDATE_DATA.WS_SIATM_CCY = BPCSIATM.CCY;
            WS_UPDATE_DATA.WS_SIATM_B_TYPE = BPCSIATM.B_TYPE;
            WS_UPDATE_DATA.WS_SIATM_TENOR = BPCSIATM.TENOR;
            WS_UPDATE_DATA.WS_SIATM_FMT = BPCSIATM.FMT;
            WS_UPDATE_DATA.WS_SIATM_HIGH = BPCSIATM.HIGH;
            WS_UPDATE_DATA.WS_SIATM_LOW = BPCSIATM.LOW;
            IBS.init(SCCGWA, BPRIRAT);
            IBS.init(SCCGWA, BPCRIATM);
            BPRIRAT.KEY.BR = BPCSIATM.BR;
            BPRIRAT.KEY.CCY = BPCSIATM.CCY;
            BPRIRAT.KEY.BASE_TYP = BPCSIATM.B_TYPE;
            BPRIRAT.KEY.TENOR = BPCSIATM.TENOR;
            BPCRIATM.INFO.FUNC = 'Q';
            BPCRIATM.INFO.OPT_2 = 'O';
            S000_CALL_BPZRIATM();
            if (pgmRtn) return;
            if (WS_UPDATE_DATA.WS_SIATM_BR == BPRIRAT.KEY.BR 
                && WS_UPDATE_DATA.WS_SIATM_CCY.equalsIgnoreCase(BPRIRAT.KEY.CCY) 
                && WS_UPDATE_DATA.WS_SIATM_B_TYPE.equalsIgnoreCase(BPRIRAT.KEY.BASE_TYP) 
                && WS_UPDATE_DATA.WS_SIATM_TENOR.equalsIgnoreCase(BPRIRAT.KEY.TENOR) 
                && WS_UPDATE_DATA.WS_SIATM_FMT == BPRIRAT.FORMAT 
                && WS_UPDATE_DATA.WS_SIATM_HIGH == BPRIRAT.HIGH 
                && WS_UPDATE_DATA.WS_SIATM_LOW == BPRIRAT.LOW) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UPD_DATA_NOT_CHG;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B210_KEY_PROCESS() throws IOException,SQLException,Exception {
        if (BPCSIATM.FUNC == 'A') {
            IBS.init(SCCGWA, BPCSIPDM);
            BPCSIPDM.CCY = BPCSIATM.CCY;
            BPCSIPDM.B_TYPE = BPCSIATM.B_TYPE;
            BPCSIPDM.TENOR = BPCSIATM.TENOR;
            BPCSIPDM.FUNC = 'C';
            BPCSIPDM.OUTPUT_FLG = 'N';
            S000_CALL_BPZSIPDM();
            if (pgmRtn) return;
            if (BPCSIPDM.CHECK_RESULT != 'E') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_RECORD_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, BPRIRAT);
        BPRIRAT.KEY.BR = BPCSIATM.BR;
        BPRIRAT.KEY.CCY = BPCSIATM.CCY;
        BPRIRAT.KEY.BASE_TYP = BPCSIATM.B_TYPE;
        BPRIRAT.KEY.TENOR = BPCSIATM.TENOR;
        BPRIRAT.FORMAT = BPCSIATM.FMT;
        BPRIRAT.HIGH = BPCSIATM.HIGH;
        BPRIRAT.LOW = BPCSIATM.LOW;
        BPRIRAT.TELLER = SCCGWA.COMM_AREA.TL_ID;
        IBS.init(SCCGWA, BPCRIATM);
        if (BPCSIATM.FUNC == 'Q'
            || BPCSIATM.FUNC == 'C') {
            BPCRIATM.INFO.FUNC = 'Q';
            BPCRIATM.INFO.OPT_2 = 'O';
        } else if (BPCSIATM.FUNC == 'A') {
            BPCRIATM.INFO.FUNC = 'A';
        } else if (BPCSIATM.FUNC == 'U'
            || BPCSIATM.FUNC == 'D') {
            BPCRIATM.INFO.FUNC = 'R';
        }
        S000_CALL_BPZRIATM();
        if (pgmRtn) return;
        R000_CHECK_RETURN();
        if (pgmRtn) return;
        if (BPCSIATM.FUNC == 'A') {
            R000_TXN_HIS_PROCESS();
            if (pgmRtn) return;
        }
        if (BPCSIATM.FUNC == 'U' 
            || BPCSIATM.FUNC == 'D') {
            if (BPCSIATM.FUNC == 'D') {
                BPCRIATM.INFO.FUNC = 'D';
                S000_CALL_BPZRIATM();
                if (pgmRtn) return;
                BPCSIATM.FMT = BPRIRAT.FORMAT;
                BPCSIATM.HIGH = BPRIRAT.HIGH;
                BPCSIATM.LOW = BPRIRAT.LOW;
                R000_TXN_HIS_PROCESS();
                if (pgmRtn) return;
            }
            if (BPCSIATM.FUNC == 'U') {
                WS_OLD_FMT = BPRIRAT.FORMAT;
                WS_OLD_HIGH = BPRIRAT.HIGH;
                WS_OLD_LOW = BPRIRAT.LOW;
                BPCRIATM.INFO.FUNC = 'U';
                BPRIRAT.FORMAT = BPCSIATM.FMT;
                BPRIRAT.HIGH = BPCSIATM.HIGH;
                BPRIRAT.LOW = BPCSIATM.LOW;
                S000_CALL_BPZRIATM();
                if (pgmRtn) return;
                BPCSIATM.FMT = BPRIRAT.FORMAT;
                BPCSIATM.HIGH = BPRIRAT.HIGH;
                BPCSIATM.LOW = BPRIRAT.LOW;
                R000_TXN_HIS_PROCESS();
                if (pgmRtn) return;
            }
        }
        BPCSIATM.FMT = BPRIRAT.FORMAT;
        BPCSIATM.HIGH = BPRIRAT.HIGH;
        BPCSIATM.LOW = BPRIRAT.LOW;
        if (BPCSIATM.OUTPUT_FLG == 'Y') {
            R000_FMT_OUTPUT_DATA();
            if (pgmRtn) return;
        }
    }
    public void B220_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIRAT);
        BPRIRAT.KEY.BR = BPCSIATM.BR;
        if (BPCSIATM.CCY.trim().length() == 0) {
            BPRIRAT.KEY.CCY = "%%%";
        } else {
            BPRIRAT.KEY.CCY = BPCSIATM.CCY;
        }
        if (BPCSIATM.B_TYPE.trim().length() == 0) {
            BPRIRAT.KEY.BASE_TYP = "%%%";
        } else {
            BPRIRAT.KEY.BASE_TYP = BPCSIATM.B_TYPE;
        }
        if (BPCSIATM.TENOR.trim().length() == 0) {
            BPRIRAT.KEY.TENOR = "%%%%";
        } else {
            BPRIRAT.KEY.TENOR = BPCSIATM.TENOR;
        }
        CEP.TRC(SCCGWA, BPRIRAT.KEY.CCY);
        CEP.TRC(SCCGWA, BPRIRAT.KEY.BASE_TYP);
        CEP.TRC(SCCGWA, BPRIRAT.KEY.TENOR);
        IBS.init(SCCGWA, BPCRIATM);
        BPCRIATM.INFO.FUNC = 'B';
        BPCRIATM.INFO.OPT_1 = 'S';
        S000_CALL_BPZRIATM();
        if (pgmRtn) return;
        if (BPCSIATM.OUTPUT_FLG == 'Y') {
            WS_OUTPUT_FLG = 'T';
            B221_FMT_OUTPUT_DATA();
            if (pgmRtn) return;
        }
        while (WS_STOP_FLG != 'Y' 
            && SCCMPAG.FUNC != 'E') {
            IBS.init(SCCGWA, BPCRIATM);
            BPCRIATM.INFO.FUNC = 'B';
            BPCRIATM.INFO.OPT_1 = 'N';
            S000_CALL_BPZRIATM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "aaaaa");
            if (BPCRIATM.RETURN_INFO == 'N') {
                CEP.TRC(SCCGWA, "CCCC");
                WS_STOP_FLG = 'Y';
            } else {
                CEP.TRC(SCCGWA, "BBBB");
                if (BPCSIATM.OUTPUT_FLG == 'Y') {
                    WS_OUTPUT_FLG = 'D';
                    CEP.TRC(SCCGWA, "ABABAB");
                    B221_FMT_OUTPUT_DATA();
                    if (pgmRtn) return;
                }
            }
        }
        IBS.init(SCCGWA, BPCRIATM);
        BPCRIATM.INFO.FUNC = 'B';
        BPCRIATM.INFO.OPT_1 = 'E';
        S000_CALL_BPZRIATM();
        if (pgmRtn) return;
    }
    public void R000_CHECK_RETURN() throws IOException,SQLException,Exception {
        if (BPCRIATM.RETURN_INFO == 'N') {
            if (BPCSIATM.FUNC == 'Q' 
                || BPCSIATM.FUNC == 'U' 
                || BPCSIATM.FUNC == 'D') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_RECORD_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCSIATM.FUNC == 'C') {
                BPCSIATM.CHECK_RESULT = 'N';
            }
        }
        if (BPCRIATM.RETURN_INFO == 'F') {
            if (BPCSIATM.FUNC == 'C') {
                BPCSIATM.CHECK_RESULT = 'E';
            }
        }
        if (BPCRIATM.RETURN_INFO == 'D') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_AUTOGEN_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_BRANCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = BPCSIATM.BR;
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B221_FMT_OUTPUT_DATA() throws IOException,SQLException,Exception {
        if (WS_OUTPUT_FLG == 'T') {
            IBS.init(SCCGWA, SCCMPAG);
            SCCMPAG.FUNC = 'S';
            SCCMPAG.TITL = " ";
            SCCMPAG.SUBT_ROW_CNT = 0;
            SCCMPAG.MAX_COL_NO = 166;
            SCCMPAG.SCR_ROW_CNT = 57;
            SCCMPAG.SCR_COL_CNT = 99;
            B_MPAG();
            if (pgmRtn) return;
        }
        if (WS_OUTPUT_FLG == 'D') {
            IBS.init(SCCGWA, WS_OUTPUT_DATA);
            if (BPRIRAT.KEY.BR == 0) {
                WS_OUTPUT_DATA.WS_OT_FLG = 'Y';
            } else {
                WS_OUTPUT_DATA.WS_OT_FLG = 'N';
            }
            WS_OUTPUT_DATA.WS_OT_BR = BPRIRAT.KEY.BR;
            WS_OUTPUT_DATA.WS_OT_CCY = BPRIRAT.KEY.CCY;
            WS_OUTPUT_DATA.WS_OT_B_TYPE = BPRIRAT.KEY.BASE_TYP;
            IBS.init(SCCGWA, BPCOQPCD);
            BPCOQPCD.INPUT_DATA.TYPE = K_BASE_TYPE;
            BPCOQPCD.INPUT_DATA.CODE = WS_OUTPUT_DATA.WS_OT_B_TYPE;
            S000_CALL_BPZPQPCD();
            if (pgmRtn) return;
            WS_OUTPUT_DATA.WS_OT_BASE_NAME = BPCOQPCD.OUTPUT_DATA.CODE_INFO.NAME;
            WS_OUTPUT_DATA.WS_OT_TENOR = BPRIRAT.KEY.TENOR;
            IBS.init(SCCGWA, BPCOQPCD);
            BPCOQPCD.INPUT_DATA.TYPE = K_TENOR;
            BPCOQPCD.INPUT_DATA.CODE = WS_OUTPUT_DATA.WS_OT_TENOR;
            S000_CALL_BPZPQPCD();
            if (pgmRtn) return;
            WS_OUTPUT_DATA.WS_OT_TENOR_NAME = BPCOQPCD.OUTPUT_DATA.CODE_INFO.NAME;
            WS_OUTPUT_DATA.WS_OT_FMT = BPRIRAT.FORMAT;
            WS_OUTPUT_DATA.WS_OT_HIGH = BPRIRAT.HIGH;
            WS_OUTPUT_DATA.WS_OT_LOW = BPRIRAT.LOW;
            IBS.init(SCCGWA, SCCMPAG);
            SCCMPAG.FUNC = 'D';
            SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_DATA);
            SCCMPAG.DATA_LEN = 166;
            B_MPAG();
            if (pgmRtn) return;
        }
    }
    public void R000_FMT_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCIATMO);
        R000_OUTPUT_BASIC_DATA();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCIATMO;
        SCCFMT.DATA_LEN = 174;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_OUTPUT_BASIC_DATA() throws IOException,SQLException,Exception {
        BPCIATMO.FLG = BPCSIATM.FLG;
        BPCIATMO.BR = BPCSIATM.BR;
        BPCIATMO.CCY = BPCSIATM.CCY;
        BPCIATMO.B_TYPE = BPCSIATM.B_TYPE;
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = K_BASE_TYPE;
        BPCOQPCD.INPUT_DATA.CODE = BPCSIATM.B_TYPE;
        S000_CALL_BPZPQPCD();
        if (pgmRtn) return;
        BPCIATMO.BASE_NAME = BPCOQPCD.OUTPUT_DATA.CODE_INFO.NAME;
        BPCIATMO.TENOR = BPCSIATM.TENOR;
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = K_TENOR;
        BPCOQPCD.INPUT_DATA.CODE = BPCSIATM.TENOR;
        S000_CALL_BPZPQPCD();
        if (pgmRtn) return;
        BPCIATMO.TENOR_NAME = BPCOQPCD.OUTPUT_DATA.CODE_INFO.NAME;
        BPCIATMO.FMT = BPCSIATM.FMT;
        BPCIATMO.HIGH = BPCSIATM.HIGH;
        BPCIATMO.LOW = BPCSIATM.LOW;
        BPCIATMO.TELLER = SCCGWA.COMM_AREA.TL_ID;
    }
    public void R000_CHECK_CONDITION() throws IOException,SQLException,Exception {
        WS_FIT_COND_FLG = 'Y';
        if (BPCSIATM.BR != 0 
            && BPCSIATM.BR != BPRIRAT.KEY.BR) {
            WS_FIT_COND_FLG = 'N';
        }
        if (BPCSIATM.CCY.trim().length() > 0 
            && !BPCSIATM.CCY.equalsIgnoreCase(BPRIRAT.KEY.CCY)) {
            WS_FIT_COND_FLG = 'N';
        }
        if (BPCSIATM.B_TYPE.trim().length() > 0 
            && !BPCSIATM.B_TYPE.equalsIgnoreCase(BPRIRAT.KEY.BASE_TYP)) {
            WS_FIT_COND_FLG = 'N';
        }
        if (!BPCSIATM.TENOR.equalsIgnoreCase("0") 
            && !BPCSIATM.TENOR.equalsIgnoreCase(BPRIRAT.KEY.TENOR)) {
            WS_FIT_COND_FLG = 'N';
        }
        if (BPCSIATM.FMT != ' ' 
            && BPCSIATM.FMT != BPRIRAT.FORMAT) {
            WS_FIT_COND_FLG = 'N';
        }
        if (BPCSIATM.HIGH != 0 
            && BPCSIATM.HIGH != BPRIRAT.HIGH) {
            WS_FIT_COND_FLG = 'N';
        }
        if (BPCSIATM.LOW != 0 
            && BPCSIATM.LOW != BPRIRAT.LOW) {
            WS_FIT_COND_FLG = 'N';
        }
    }
    public void R000_TXN_HIS_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        if (BPCSIATM.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
        }
        if (BPCSIATM.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
        }
        if (BPCSIATM.FUNC == 'U') {
            BPCPNHIS.INFO.TX_TYP = 'M';
        }
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK_NAME;
        WS_HIS_REF.WS_HIS_FLG = BPCSIATM.FLG;
        WS_HIS_REF.WS_HIS_BR = BPCSIATM.BR;
        WS_HIS_REF.WS_HIS_CCY = BPCSIATM.CCY;
        WS_HIS_REF.WS_HIS_B_TYPE = BPCSIATM.B_TYPE;
        WS_HIS_REF.WS_HIS_TENOR = BPCSIATM.TENOR;
        BPCPNHIS.INFO.REF_NO = IBS.CLS2CPY(SCCGWA, WS_HIS_REF);
        if (BPCSIATM.FUNC == 'U') {
            IBS.init(SCCGWA, BPCOHATM);
            BPCOHATM.FMT = WS_OLD_FMT;
            BPCOHATM.HIGH = WS_OLD_HIGH;
            BPCOHATM.LOW = WS_OLD_LOW;
            BPCPNHIS.INFO.OLD_DAT_PT = BPCOHATM;
            IBS.init(SCCGWA, BPCNHATM);
            BPCNHATM.FMT = BPCSIATM.FMT;
            BPCNHATM.HIGH = BPCSIATM.HIGH;
            BPCNHATM.LOW = BPCSIATM.LOW;
            BPCPNHIS.INFO.NEW_DAT_PT = BPCNHATM;
        }
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZRIATM() throws IOException,SQLException,Exception {
        BPCRIATM.INFO.POINTER = BPRIRAT;
        IBS.CALLCPN(SCCGWA, CPN_R_BK_MT, BPCRIATM);
        if (BPCRIATM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRIATM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSIPDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, BP_IRPD_MIAN, BPCSIPDM);
    }
    public void S000_CALL_BPZPQPCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, BP_QPCD_MAIN, BPCOQPCD);
        if (BPCOQPCD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CMP_TXN_HIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
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
