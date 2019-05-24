package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class BPZSIPDM {
    boolean pgmRtn = false;
    String CPN_R_BK_MT = "BP-R-IRPD-MAINT";
    String BP_QPCD_MAIN = "BP-P-INQ-PC";
    String K_OUTPUT_FMT = "BP330";
    String K_BASE_TYPE = "RBASE";
    String K_TENOR = "RTENO";
    String K_CMP_TXN_HIS = "BP-REC-NHIS";
    String K_HIS_REMARKS = "PARMETER TYPE MAINTENANCE";
    String K_HIS_COPYBOOK_NAME = "BPCHPDM";
    BPZSIPDM_WS_OUTPUT_DATA WS_OUTPUT_DATA = new BPZSIPDM_WS_OUTPUT_DATA();
    char WS_OLD_EFF_FLG = ' ';
    char WS_OLD_DEL_FLG = ' ';
    BPZSIPDM_WS_HIS_REF WS_HIS_REF = new BPZSIPDM_WS_HIS_REF();
    BPZSIPDM_WS_UPDATE_DATA WS_UPDATE_DATA = new BPZSIPDM_WS_UPDATE_DATA();
    String WS_ERR_MSG = " ";
    char WS_DAY_TYP_FLG = ' ';
    char WS_OUTPUT_FLG = ' ';
    char WS_STOP_FLG = ' ';
    char WS_FIT_COND_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCRIPDM BPCRIPDM = new BPCRIPDM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCHPDM BPCOHPDM = new BPCHPDM();
    BPCHPDM BPCNHPDM = new BPCHPDM();
    BPRIRPD BPRIRPD = new BPRIRPD();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPCIPDMO BPCIPDMO = new BPCIPDMO();
    SCCGWA SCCGWA;
    BPCSIPDM BPCSIPDM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSIPDM BPCSIPDM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSIPDM = BPCSIPDM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSIPDM return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        if (BPCSIPDM.FUNC == 'Q'
            || BPCSIPDM.FUNC == 'A'
            || BPCSIPDM.FUNC == 'U'
            || BPCSIPDM.FUNC == 'C'
            || BPCSIPDM.FUNC == 'D') {
            B210_KEY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSIPDM.FUNC == 'B') {
            B220_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSIPDM.FUNC);
        CEP.TRC(SCCGWA, BPCSIPDM.CCY);
        CEP.TRC(SCCGWA, BPCSIPDM.B_TYPE);
        CEP.TRC(SCCGWA, BPCSIPDM.TENOR);
        if (!(BPCSIPDM.OUTPUT_FLG == 'Y' 
            || BPCSIPDM.OUTPUT_FLG == 'N')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSIPDM.FUNC == 'A' 
            || BPCSIPDM.FUNC == 'U' 
            || BPCSIPDM.FUNC == 'D' 
            || BPCSIPDM.FUNC == 'Q' 
            || BPCSIPDM.FUNC == 'C') {
            if (BPCSIPDM.TENOR.trim().length() == 0 
                || BPCSIPDM.CCY.trim().length() == 0 
                || BPCSIPDM.B_TYPE.trim().length() == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_INPUT_ERROR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCSIPDM.FUNC == 'U') {
            WS_UPDATE_DATA.WS_SIPDM_CCY = BPCSIPDM.CCY;
            WS_UPDATE_DATA.WS_SIPDM_B_TYPE = BPCSIPDM.B_TYPE;
            WS_UPDATE_DATA.WS_SIPDM_TENOR = BPCSIPDM.TENOR;
            WS_UPDATE_DATA.WS_SIPDM_EFF_FLG = BPCSIPDM.EFF_FLG;
            WS_UPDATE_DATA.WS_SIPDM_DEL_FLG = BPCSIPDM.DEL_FLG;
            CEP.TRC(SCCGWA, WS_UPDATE_DATA.WS_SIPDM_CCY);
            CEP.TRC(SCCGWA, WS_UPDATE_DATA.WS_SIPDM_B_TYPE);
            CEP.TRC(SCCGWA, WS_UPDATE_DATA.WS_SIPDM_TENOR);
            CEP.TRC(SCCGWA, WS_UPDATE_DATA.WS_SIPDM_EFF_FLG);
            CEP.TRC(SCCGWA, WS_UPDATE_DATA.WS_SIPDM_DEL_FLG);
            IBS.init(SCCGWA, BPRIRPD);
            IBS.init(SCCGWA, BPCRIPDM);
            BPRIRPD.KEY.CCY = BPCSIPDM.CCY;
            BPRIRPD.KEY.BASE_TYP = BPCSIPDM.B_TYPE;
            BPRIRPD.KEY.TENOR = BPCSIPDM.TENOR;
            BPCRIPDM.INFO.FUNC = 'Q';
            BPCRIPDM.INFO.OPT_2 = 'O';
            S000_CALL_BPZRIPDM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPRIRPD.KEY.CCY);
            CEP.TRC(SCCGWA, BPRIRPD.KEY.BASE_TYP);
            CEP.TRC(SCCGWA, BPRIRPD.KEY.TENOR);
            CEP.TRC(SCCGWA, BPRIRPD.CONTRL);
            CEP.TRC(SCCGWA, BPRIRPD.DEL_FLG);
            CEP.TRC(SCCGWA, WS_UPDATE_DATA.WS_SIPDM_EFF_FLG);
            CEP.TRC(SCCGWA, BPRIRPD.CONTRL);
            if (WS_UPDATE_DATA.WS_SIPDM_CCY.equalsIgnoreCase(BPRIRPD.KEY.CCY) 
                && WS_UPDATE_DATA.WS_SIPDM_B_TYPE.equalsIgnoreCase(BPRIRPD.KEY.BASE_TYP) 
                && WS_UPDATE_DATA.WS_SIPDM_TENOR.equalsIgnoreCase(BPRIRPD.KEY.TENOR) 
                && WS_UPDATE_DATA.WS_SIPDM_EFF_FLG == BPRIRPD.CONTRL 
                && WS_UPDATE_DATA.WS_SIPDM_DEL_FLG == BPRIRPD.DEL_FLG) {
                CEP.TRC(SCCGWA, "=========");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UPD_DATA_NOT_CHG;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B210_KEY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIRPD);
        BPRIRPD.KEY.CCY = BPCSIPDM.CCY;
        BPRIRPD.KEY.BASE_TYP = BPCSIPDM.B_TYPE;
        BPRIRPD.KEY.TENOR = BPCSIPDM.TENOR;
        BPRIRPD.CONTRL = BPCSIPDM.EFF_FLG;
        BPRIRPD.DEL_FLG = BPCSIPDM.DEL_FLG;
        BPRIRPD.TELLER = SCCGWA.COMM_AREA.TL_ID;
        IBS.init(SCCGWA, BPCRIPDM);
        if (BPCSIPDM.FUNC == 'Q'
            || BPCSIPDM.FUNC == 'C') {
            BPCRIPDM.INFO.FUNC = 'Q';
            BPCRIPDM.INFO.OPT_2 = 'O';
        } else if (BPCSIPDM.FUNC == 'A') {
            BPCRIPDM.INFO.FUNC = 'A';
        } else if (BPCSIPDM.FUNC == 'U'
            || BPCSIPDM.FUNC == 'D') {
            BPCRIPDM.INFO.FUNC = 'R';
        }
        S000_CALL_BPZRIPDM();
        if (pgmRtn) return;
        R000_CHECK_RETURN();
        if (pgmRtn) return;
        if (BPCSIPDM.FUNC == 'A') {
            R000_TXN_HIS_PROCESS();
            if (pgmRtn) return;
        }
        if (BPCSIPDM.FUNC == 'U' 
            || BPCSIPDM.FUNC == 'D') {
            if (BPCSIPDM.FUNC == 'D') {
                BPCRIPDM.INFO.FUNC = 'D';
                S000_CALL_BPZRIPDM();
                if (pgmRtn) return;
                BPCSIPDM.EFF_FLG = BPRIRPD.CONTRL;
                BPCSIPDM.DEL_FLG = BPRIRPD.DEL_FLG;
                R000_TXN_HIS_PROCESS();
                if (pgmRtn) return;
            }
            if (BPCSIPDM.FUNC == 'U') {
                WS_OLD_EFF_FLG = BPRIRPD.CONTRL;
                WS_OLD_DEL_FLG = BPRIRPD.DEL_FLG;
                BPCRIPDM.INFO.FUNC = 'U';
                BPRIRPD.CONTRL = BPCSIPDM.EFF_FLG;
                BPRIRPD.DEL_FLG = BPCSIPDM.DEL_FLG;
                S000_CALL_BPZRIPDM();
                if (pgmRtn) return;
                BPCSIPDM.EFF_FLG = BPRIRPD.CONTRL;
                BPCSIPDM.DEL_FLG = BPRIRPD.DEL_FLG;
                R000_TXN_HIS_PROCESS();
                if (pgmRtn) return;
            }
        }
        BPCSIPDM.EFF_FLG = BPRIRPD.CONTRL;
        BPCSIPDM.DEL_FLG = BPRIRPD.DEL_FLG;
        if (BPCSIPDM.OUTPUT_FLG == 'Y') {
            R000_FMT_OUTPUT_DATA();
            if (pgmRtn) return;
        }
    }
    public void B220_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIRPD);
        BPRIRPD.KEY.CCY = BPCSIPDM.CCY;
        BPRIRPD.KEY.BASE_TYP = BPCSIPDM.B_TYPE;
        BPRIRPD.KEY.TENOR = BPCSIPDM.TENOR;
        IBS.init(SCCGWA, BPCRIPDM);
        BPCRIPDM.INFO.FUNC = 'B';
        BPCRIPDM.INFO.OPT_1 = 'S';
        S000_CALL_BPZRIPDM();
        if (pgmRtn) return;
        if (BPCSIPDM.OUTPUT_FLG == 'Y') {
            WS_OUTPUT_FLG = 'T';
            B221_FMT_OUTPUT_DATA();
            if (pgmRtn) return;
        }
        while (WS_STOP_FLG != 'Y' 
            && SCCMPAG.FUNC != 'E') {
            IBS.init(SCCGWA, BPCRIPDM);
            BPCRIPDM.INFO.FUNC = 'B';
            BPCRIPDM.INFO.OPT_1 = 'N';
            S000_CALL_BPZRIPDM();
            if (pgmRtn) return;
            if (BPCRIPDM.RETURN_INFO == 'N') {
                WS_STOP_FLG = 'Y';
            } else {
                R000_CHECK_CONDITION();
                if (pgmRtn) return;
                if (BPCSIPDM.OUTPUT_FLG == 'Y') {
                    WS_OUTPUT_FLG = 'D';
                    B221_FMT_OUTPUT_DATA();
                    if (pgmRtn) return;
                }
            }
        }
        IBS.init(SCCGWA, BPCRIPDM);
        BPCRIPDM.INFO.FUNC = 'B';
        BPCRIPDM.INFO.OPT_1 = 'E';
        S000_CALL_BPZRIPDM();
        if (pgmRtn) return;
    }
    public void R000_CHECK_RETURN() throws IOException,SQLException,Exception {
        if (BPCRIPDM.RETURN_INFO == 'N') {
            if (BPCSIPDM.FUNC == 'Q' 
                || BPCSIPDM.FUNC == 'U' 
                || BPCSIPDM.FUNC == 'D') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_RECORD_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCSIPDM.FUNC == 'C') {
                BPCSIPDM.CHECK_RESULT = 'N';
            }
        }
        if (BPCRIPDM.RETURN_INFO == 'F') {
            if (BPCSIPDM.FUNC == 'C') {
                BPCSIPDM.CHECK_RESULT = 'E';
            }
        }
        if (BPCRIPDM.RETURN_INFO == 'D') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_AUTOGEN_EXIST;
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
            SCCMPAG.MAX_COL_NO = 134;
            SCCMPAG.SCR_ROW_CNT = 57;
            SCCMPAG.SCR_COL_CNT = 20;
            B_MPAG();
            if (pgmRtn) return;
        }
        if (WS_OUTPUT_FLG == 'D') {
            IBS.init(SCCGWA, WS_OUTPUT_DATA);
            WS_OUTPUT_DATA.WS_OT_CCY = BPRIRPD.KEY.CCY;
            WS_OUTPUT_DATA.WS_OT_B_TYPE = BPRIRPD.KEY.BASE_TYP;
            IBS.init(SCCGWA, BPCOQPCD);
            BPCOQPCD.INPUT_DATA.TYPE = K_BASE_TYPE;
            BPCOQPCD.INPUT_DATA.CODE = WS_OUTPUT_DATA.WS_OT_B_TYPE;
            S000_CALL_BPZPQPCD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCOQPCD.OUTPUT_DATA.CODE_INFO.NAME);
            CEP.TRC(SCCGWA, "BASE-TYPE-NAME");
            WS_OUTPUT_DATA.WS_OT_BASE_NAME = BPCOQPCD.OUTPUT_DATA.CODE_INFO.NAME;
            WS_OUTPUT_DATA.WS_OT_TENOR = BPRIRPD.KEY.TENOR;
            IBS.init(SCCGWA, BPCOQPCD);
            BPCOQPCD.INPUT_DATA.TYPE = K_TENOR;
            BPCOQPCD.INPUT_DATA.CODE = WS_OUTPUT_DATA.WS_OT_TENOR;
            S000_CALL_BPZPQPCD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCOQPCD.OUTPUT_DATA.CODE_INFO.NAME);
            CEP.TRC(SCCGWA, "TENOR-NAEM");
            WS_OUTPUT_DATA.WS_OT_TENOR_NAME = BPCOQPCD.OUTPUT_DATA.CODE_INFO.NAME;
            WS_OUTPUT_DATA.WS_OT_EFF_FLG = BPRIRPD.CONTRL;
            CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_OT_EFF_FLG);
            WS_OUTPUT_DATA.WS_OT_DEL_FLG = BPRIRPD.DEL_FLG;
            IBS.init(SCCGWA, SCCMPAG);
            SCCMPAG.FUNC = 'D';
            SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_DATA);
            SCCMPAG.DATA_LEN = 134;
            B_MPAG();
            if (pgmRtn) return;
        }
    }
    public void R000_FMT_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCIPDMO);
        R000_OUTPUT_BASIC_DATA();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCIPDMO;
        SCCFMT.DATA_LEN = 142;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_OUTPUT_BASIC_DATA() throws IOException,SQLException,Exception {
        BPCIPDMO.CCY = BPCSIPDM.CCY;
        BPCIPDMO.B_TYPE = BPCSIPDM.B_TYPE;
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = K_BASE_TYPE;
        BPCOQPCD.INPUT_DATA.CODE = BPCSIPDM.B_TYPE;
        S000_CALL_BPZPQPCD();
        if (pgmRtn) return;
        BPCIPDMO.BASE_NAME = BPCOQPCD.OUTPUT_DATA.CODE_INFO.NAME;
        BPCIPDMO.TENOR = BPCSIPDM.TENOR;
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = K_TENOR;
        BPCOQPCD.INPUT_DATA.CODE = BPCSIPDM.TENOR;
        S000_CALL_BPZPQPCD();
        if (pgmRtn) return;
        BPCIPDMO.TENOR_NAME = BPCOQPCD.OUTPUT_DATA.CODE_INFO.NAME;
        BPCIPDMO.EFF_FLG = BPCSIPDM.EFF_FLG;
        BPCIPDMO.DEL_FLG = BPCSIPDM.DEL_FLG;
        BPCIPDMO.TELLER = SCCGWA.COMM_AREA.TL_ID;
    }
    public void R000_CHECK_CONDITION() throws IOException,SQLException,Exception {
        WS_FIT_COND_FLG = 'Y';
        if (BPCSIPDM.CCY.trim().length() > 0 
            && !BPCSIPDM.CCY.equalsIgnoreCase(BPRIRPD.KEY.CCY)) {
            WS_FIT_COND_FLG = 'N';
        }
        if (BPCSIPDM.B_TYPE.trim().length() > 0 
            && !BPCSIPDM.B_TYPE.equalsIgnoreCase(BPRIRPD.KEY.BASE_TYP)) {
            WS_FIT_COND_FLG = 'N';
        }
        if (BPCSIPDM.TENOR.trim().length() > 0 
            && !BPCSIPDM.TENOR.equalsIgnoreCase(BPRIRPD.KEY.TENOR)) {
            WS_FIT_COND_FLG = 'N';
        }
        if (BPCSIPDM.EFF_FLG != ' ' 
            && BPCSIPDM.EFF_FLG != BPRIRPD.CONTRL) {
            WS_FIT_COND_FLG = 'N';
        }
        if (BPCSIPDM.DEL_FLG != ' ' 
            && BPCSIPDM.DEL_FLG != BPRIRPD.DEL_FLG) {
            WS_FIT_COND_FLG = 'N';
        }
    }
    public void R000_TXN_HIS_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        if (BPCSIPDM.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
        }
        if (BPCSIPDM.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
        }
        if (BPCSIPDM.FUNC == 'U') {
            BPCPNHIS.INFO.TX_TYP = 'M';
        }
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK_NAME;
        WS_HIS_REF.WS_HIS_CCY = BPCSIPDM.CCY;
        WS_HIS_REF.WS_HIS_B_TYPE = BPCSIPDM.B_TYPE;
        WS_HIS_REF.WS_HIS_TENOR = BPCSIPDM.TENOR;
        BPCPNHIS.INFO.REF_NO = IBS.CLS2CPY(SCCGWA, WS_HIS_REF);
        if (BPCSIPDM.FUNC == 'U') {
            IBS.init(SCCGWA, BPCOHPDM);
            BPCOHPDM.EFF_FLG = WS_OLD_EFF_FLG;
            BPCOHPDM.DEL_FLG = WS_OLD_DEL_FLG;
            BPCPNHIS.INFO.OLD_DAT_PT = BPCOHPDM;
            IBS.init(SCCGWA, BPCNHPDM);
            BPCNHPDM.EFF_FLG = BPCSIPDM.EFF_FLG;
            BPCNHPDM.DEL_FLG = BPCSIPDM.DEL_FLG;
            BPCPNHIS.INFO.NEW_DAT_PT = BPCNHPDM;
        }
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZRIPDM() throws IOException,SQLException,Exception {
        BPCRIPDM.INFO.POINTER = BPRIRPD;
        IBS.CALLCPN(SCCGWA, CPN_R_BK_MT, BPCRIPDM);
        CEP.TRC(SCCGWA, BPCRIPDM.RC.RC_CODE);
        CEP.TRC(SCCGWA, BPCRIPDM.RETURN_INFO);
        if (BPCRIPDM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRIPDM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQPCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, BP_QPCD_MAIN, BPCOQPCD);
        CEP.TRC(SCCGWA, BPCOQPCD.RC);
        if (BPCOQPCD.RC.RC_CODE != 0) {
