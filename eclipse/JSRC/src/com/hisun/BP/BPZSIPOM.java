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

public class BPZSIPOM {
    boolean pgmRtn = false;
    String CPN_R_BK_MT = "BP-R-IRPD-MAINT";
    String BP_QPCD_MAIN = "BP-P-INQ-PC";
    String K_OUTPUT_FMT = "BP330";
    String K_OUTPUT_FMT_ADD = "BP722";
    String K_BASE_TYPE = "RBASE";
    String K_TENOR = "RTENO";
    String K_RATEID = "RRTID";
    String K_CMP_TXN_HIS = "BP-REC-NHIS";
    String K_HIS_REMARKS = "PARMETER TYPE MAINTENANCE";
    String K_HIS_COPYBOOK_NAME = "BPRIRPD";
    String K_HIS_COPYBOOK_RTID = "BPRRTID";
    BPZSIPOM_WS_OUTPUT_DATA WS_OUTPUT_DATA = new BPZSIPOM_WS_OUTPUT_DATA();
    char WS_OLD_EFF_FLG = ' ';
    char WS_OLD_DEL_FLG = ' ';
    int WS_I = 0;
    BPZSIPOM_WS_HIS_REF WS_HIS_REF = new BPZSIPOM_WS_HIS_REF();
    BPZSIPOM_WS_HIS_REF_RTID WS_HIS_REF_RTID = new BPZSIPOM_WS_HIS_REF_RTID();
    BPZSIPOM_WS_UPDATE_DATA WS_UPDATE_DATA = new BPZSIPOM_WS_UPDATE_DATA();
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
    BPRRTID BPRRTID = new BPRRTID();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPCIPDMO BPCIPDMO = new BPCIPDMO();
    BPCRMRTD BPCRMRTD = new BPCRMRTD();
    BPCOQRTD BPCOQRTD = new BPCOQRTD();
    BPCORTID BPCORTID = new BPCORTID();
    SCCGWA SCCGWA;
    BPCSIPOM BPCSIPOM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSIPOM BPCSIPOM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSIPOM = BPCSIPOM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSIPOM return!");
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
        if (BPCSIPOM.FUNC == 'Q'
            || BPCSIPOM.FUNC == 'A'
            || BPCSIPOM.FUNC == 'U'
            || BPCSIPOM.FUNC == 'C'
            || BPCSIPOM.FUNC == 'D') {
            B210_KEY_PROCESS_ADD();
            if (pgmRtn) return;
        } else if (BPCSIPOM.FUNC == 'B') {
            B230_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSIPOM.B_TYPE);
        if (!(BPCSIPOM.OUTPUT_FLG == 'Y' 
            || BPCSIPOM.OUTPUT_FLG == 'N')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSIPOM.FUNC == 'A') {
            IBS.init(SCCGWA, BPCOQPCD);
            BPCOQPCD.INPUT_DATA.TYPE = K_BASE_TYPE;
            BPCOQPCD.INPUT_DATA.CODE = BPCSIPOM.B_TYPE;
            S000_CALL_BPZPQPCD();
            if (pgmRtn) return;
            for (WS_I = 1; WS_I <= 20; WS_I += 1) {
                if (BPCSIPOM.TEN_TBL[WS_I-1].CCY_TBL.trim().length() > 0 
                    && BPCSIPOM.TEN_TBL[WS_I-1].TENOR_TBL.trim().length() > 0 
                    && BPCSIPOM.TEN_TBL[WS_I-1].RATEID_TBL.trim().length() > 0) {
                    IBS.init(SCCGWA, BPCOQPCD);
                    BPCOQPCD.INPUT_DATA.TYPE = K_TENOR;
                    BPCOQPCD.INPUT_DATA.CODE = BPCSIPOM.TEN_TBL[WS_I-1].TENOR_TBL;
                    S000_CALL_BPZPQPCD();
                    if (pgmRtn) return;
                    IBS.init(SCCGWA, BPCOQPCD);
                    BPCOQPCD.INPUT_DATA.TYPE = K_RATEID;
                    BPCOQPCD.INPUT_DATA.CODE = BPCSIPOM.TEN_TBL[WS_I-1].RATEID_TBL;
                    S000_CALL_BPZPQPCD();
                    if (pgmRtn) return;
                    IBS.init(SCCGWA, BPRRTID);
                    BPRRTID.CCY = BPCSIPOM.TEN_TBL[WS_I-1].CCY_TBL;
                    BPRRTID.BASE_TYP = BPCSIPOM.B_TYPE;
                    BPRRTID.TENOR = BPCSIPOM.TEN_TBL[WS_I-1].TENOR_TBL;
                    BPRRTID.KEY.ID = BPCSIPOM.TEN_TBL[WS_I-1].RATEID_TBL;
                    IBS.init(SCCGWA, BPCRMRTD);
                    BPCRMRTD.INFO.FUNC = 'Q';
                    BPCRMRTD.INFO.OPT = 'I';
                    BPCRMRTD.INFO.POINTER = BPRRTID;
                    BPCRMRTD.INFO.LEN = 44;
                    S000_CALL_BPZRMRTD();
                    if (pgmRtn) return;
                    if (BPCRMRTD.RETURN_INFO == 'F') {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_EXIST;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    IBS.init(SCCGWA, BPRRTID);
                    BPRRTID.KEY.ID = BPCSIPOM.TEN_TBL[WS_I-1].RATEID_TBL;
                    IBS.init(SCCGWA, BPCRMRTD);
                    BPCRMRTD.INFO.FUNC = 'Q';
                    BPCRMRTD.INFO.POINTER = BPRRTID;
                    BPCRMRTD.INFO.LEN = 44;
                    S000_CALL_BPZRMRTD();
                    if (pgmRtn) return;
                    if (BPCRMRTD.RETURN_INFO == 'F') {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_EXIST;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
        }
        if (BPCSIPOM.FUNC == 'U') {
            WS_UPDATE_DATA.WS_SIPOM_CCY = BPCSIPOM.CCY;
            WS_UPDATE_DATA.WS_SIPOM_B_TYPE = BPCSIPOM.B_TYPE;
            WS_UPDATE_DATA.WS_SIPOM_TENOR = BPCSIPOM.TENOR;
            WS_UPDATE_DATA.WS_SIPOM_EFF_FLG = BPCSIPOM.EFF_FLG;
            WS_UPDATE_DATA.WS_SIPOM_DEL_FLG = BPCSIPOM.DEL_FLG;
            CEP.TRC(SCCGWA, WS_UPDATE_DATA.WS_SIPOM_CCY);
            CEP.TRC(SCCGWA, WS_UPDATE_DATA.WS_SIPOM_B_TYPE);
            CEP.TRC(SCCGWA, WS_UPDATE_DATA.WS_SIPOM_TENOR);
            CEP.TRC(SCCGWA, WS_UPDATE_DATA.WS_SIPOM_EFF_FLG);
            CEP.TRC(SCCGWA, WS_UPDATE_DATA.WS_SIPOM_DEL_FLG);
            IBS.init(SCCGWA, BPRIRPD);
            IBS.init(SCCGWA, BPCRIPDM);
            BPRIRPD.KEY.CCY = BPCSIPOM.CCY;
            BPRIRPD.KEY.BASE_TYP = BPCSIPOM.B_TYPE;
            BPRIRPD.KEY.TENOR = BPCSIPOM.TENOR;
            BPCRIPDM.INFO.FUNC = 'Q';
            BPCRIPDM.INFO.OPT_2 = 'O';
            S000_CALL_BPZRIPDM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPRIRPD.KEY.CCY);
            CEP.TRC(SCCGWA, BPRIRPD.KEY.BASE_TYP);
            CEP.TRC(SCCGWA, BPRIRPD.KEY.TENOR);
            CEP.TRC(SCCGWA, BPRIRPD.CONTRL);
            CEP.TRC(SCCGWA, BPRIRPD.DEL_FLG);
            if (WS_UPDATE_DATA.WS_SIPOM_CCY.equalsIgnoreCase(BPRIRPD.KEY.CCY) 
                && WS_UPDATE_DATA.WS_SIPOM_B_TYPE.equalsIgnoreCase(BPRIRPD.KEY.BASE_TYP) 
                && WS_UPDATE_DATA.WS_SIPOM_TENOR.equalsIgnoreCase(BPRIRPD.KEY.TENOR) 
                && WS_UPDATE_DATA.WS_SIPOM_EFF_FLG == BPRIRPD.CONTRL 
                && WS_UPDATE_DATA.WS_SIPOM_DEL_FLG == BPRIRPD.DEL_FLG) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UPD_DATA_NOT_CHG;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B210_KEY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIRPD);
        BPRIRPD.KEY.CCY = BPCSIPOM.CCY;
        BPRIRPD.KEY.BASE_TYP = BPCSIPOM.B_TYPE;
        BPRIRPD.KEY.TENOR = BPCSIPOM.TENOR;
        BPRIRPD.CONTRL = BPCSIPOM.EFF_FLG;
        BPRIRPD.DEL_FLG = BPCSIPOM.DEL_FLG;
        BPRIRPD.TELLER = SCCGWA.COMM_AREA.TL_ID;
        IBS.init(SCCGWA, BPCRIPDM);
        if (BPCSIPOM.FUNC == 'Q'
            || BPCSIPOM.FUNC == 'C') {
            BPCRIPDM.INFO.FUNC = 'Q';
            BPCRIPDM.INFO.OPT_2 = 'O';
        } else if (BPCSIPOM.FUNC == 'A') {
            BPCRIPDM.INFO.FUNC = 'A';
        } else if (BPCSIPOM.FUNC == 'U'
            || BPCSIPOM.FUNC == 'D') {
            BPCRIPDM.INFO.FUNC = 'R';
        } else {
        }
        S000_CALL_BPZRIPDM();
        if (pgmRtn) return;
        B300_CHECK_RETURN();
        if (pgmRtn) return;
        if (BPCSIPOM.FUNC == 'A') {
            B300_TXN_HIS_PROCESS();
            if (pgmRtn) return;
        }
        if (BPCSIPOM.FUNC == 'U' 
            || BPCSIPOM.FUNC == 'D') {
            if (BPCSIPOM.FUNC == 'D') {
                BPCRIPDM.INFO.FUNC = 'D';
                S000_CALL_BPZRIPDM();
                if (pgmRtn) return;
                BPCSIPOM.EFF_FLG = BPRIRPD.CONTRL;
                BPCSIPOM.DEL_FLG = BPRIRPD.DEL_FLG;
                B300_TXN_HIS_PROCESS();
                if (pgmRtn) return;
            }
            if (BPCSIPOM.FUNC == 'U') {
                WS_OLD_EFF_FLG = BPRIRPD.CONTRL;
                WS_OLD_DEL_FLG = BPRIRPD.DEL_FLG;
                BPCRIPDM.INFO.FUNC = 'U';
                BPRIRPD.CONTRL = BPCSIPOM.EFF_FLG;
                BPRIRPD.DEL_FLG = BPCSIPOM.DEL_FLG;
                S000_CALL_BPZRIPDM();
                if (pgmRtn) return;
                BPCSIPOM.EFF_FLG = BPRIRPD.CONTRL;
                BPCSIPOM.DEL_FLG = BPRIRPD.DEL_FLG;
                B300_TXN_HIS_PROCESS();
                if (pgmRtn) return;
            }
        }
        BPCSIPOM.EFF_FLG = BPRIRPD.CONTRL;
        BPCSIPOM.DEL_FLG = BPRIRPD.DEL_FLG;
        if (BPCSIPOM.OUTPUT_FLG == 'Y') {
            B300_FMT_OUTPUT_DATA();
            if (pgmRtn) return;
        }
    }
    public void B210_KEY_PROCESS_ADD() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= 20; WS_I += 1) {
            if (BPCSIPOM.TEN_TBL[WS_I-1].CCY_TBL.trim().length() > 0 
                && BPCSIPOM.TEN_TBL[WS_I-1].TENOR_TBL.trim().length() > 0 
                && BPCSIPOM.TEN_TBL[WS_I-1].RATEID_TBL.trim().length() > 0) {
                CEP.TRC(SCCGWA, WS_I);
                CEP.TRC(SCCGWA, BPCSIPOM.TEN_TBL[WS_I-1].CCY_TBL);
                CEP.TRC(SCCGWA, BPCSIPOM.TEN_TBL[WS_I-1].TENOR_TBL);
                CEP.TRC(SCCGWA, BPCSIPOM.TEN_TBL[WS_I-1].RATEID_TBL);
                IBS.init(SCCGWA, BPRIRPD);
                BPRIRPD.KEY.CCY = BPCSIPOM.TEN_TBL[WS_I-1].CCY_TBL;
                BPRIRPD.KEY.BASE_TYP = BPCSIPOM.B_TYPE;
                BPRIRPD.KEY.TENOR = BPCSIPOM.TEN_TBL[WS_I-1].TENOR_TBL;
                BPRIRPD.TELLER = SCCGWA.COMM_AREA.TL_ID;
                IBS.init(SCCGWA, BPCRIPDM);
                BPCRIPDM.INFO.FUNC = 'A';
                S000_CALL_BPZRIPDM();
                if (pgmRtn) return;
                B300_CHECK_RETURN();
                if (pgmRtn) return;
                B300_TXN_HIS_PROCESS();
                if (pgmRtn) return;
                IBS.init(SCCGWA, BPRRTID);
                BPRRTID.CCY = BPCSIPOM.TEN_TBL[WS_I-1].CCY_TBL;
                BPRRTID.BASE_TYP = BPCSIPOM.B_TYPE;
                BPRRTID.TENOR = BPCSIPOM.TEN_TBL[WS_I-1].TENOR_TBL;
                BPRRTID.KEY.ID = BPCSIPOM.TEN_TBL[WS_I-1].RATEID_TBL;
                BPRRTID.INPUT_DT = SCCGWA.COMM_AREA.AC_DATE;
                BPRRTID.LAST_UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                BPRRTID.LAST_TELLER = SCCGWA.COMM_AREA.TL_ID;
                IBS.init(SCCGWA, BPCRMRTD);
                BPCRMRTD.INFO.FUNC = 'C';
                BPCRMRTD.INFO.POINTER = BPRRTID;
                BPCRMRTD.INFO.LEN = 44;
                S000_CALL_BPZRMRTD();
                if (pgmRtn) return;
                if (BPCRMRTD.RC.RC_CODE != 0) {
                    WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRMRTD.RC);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                B300_TXN_HIS_RTID_PROC();
                if (pgmRtn) return;
            }
        }
        B300_FMT_OUTPUT_DATA_ADD();
        if (pgmRtn) return;
    }
    public void B220_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIRPD);
        BPRIRPD.KEY.CCY = BPCSIPOM.CCY;
        BPRIRPD.KEY.BASE_TYP = BPCSIPOM.B_TYPE;
        IBS.init(SCCGWA, BPCRIPDM);
        BPCRIPDM.INFO.FUNC = 'B';
        BPCRIPDM.INFO.OPT_1 = 'S';
        S000_CALL_BPZRIPDM();
        if (pgmRtn) return;
        if (BPCSIPOM.OUTPUT_FLG == 'Y') {
            WS_OUTPUT_FLG = 'T';
            B221_FMT_OUTPUT_DATA();
            if (pgmRtn) return;
        }
        while (WS_STOP_FLG != 'Y') {
            IBS.init(SCCGWA, BPCRIPDM);
            BPCRIPDM.INFO.FUNC = 'B';
            BPCRIPDM.INFO.OPT_1 = 'N';
            S000_CALL_BPZRIPDM();
            if (pgmRtn) return;
            if (BPCRIPDM.RETURN_INFO == 'N') {
                WS_STOP_FLG = 'Y';
            } else {
                B300_CHECK_CONDITION();
                if (pgmRtn) return;
                if (BPCSIPOM.OUTPUT_FLG == 'Y') {
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
    public void B230_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRRTID);
        BPRRTID.CCY = BPCSIPOM.CCY;
        BPRRTID.BASE_TYP = BPCSIPOM.B_TYPE;
        IBS.init(SCCGWA, BPCRMRTD);
        BPCRMRTD.INFO.FUNC = 'B';
        BPCRMRTD.INFO.OPT = 'S';
        BPCRMRTD.INFO.POINTER = BPRRTID;
        BPCRMRTD.INFO.LEN = 44;
        S000_CALL_BPZRMRTD();
        if (pgmRtn) return;
        if (BPCSIPOM.OUTPUT_FLG == 'Y') {
            WS_OUTPUT_FLG = 'T';
            B221_FMT_OUTPUT_DATA();
            if (pgmRtn) return;
        }
        while (WS_STOP_FLG != 'Y' 
            && SCCMPAG.FUNC != 'E') {
            BPCRMRTD.INFO.FUNC = 'B';
            BPCRMRTD.INFO.OPT = 'N';
            S000_CALL_BPZRMRTD();
            if (pgmRtn) return;
            if (BPCRMRTD.RETURN_INFO == 'N') {
                WS_STOP_FLG = 'Y';
            } else {
                B300_CHECK_CONDITION();
                if (pgmRtn) return;
                if (BPCSIPOM.OUTPUT_FLG == 'Y' 
                    && WS_FIT_COND_FLG == 'Y') {
                    WS_OUTPUT_FLG = 'D';
                    B221_FMT_OUTPUT_DATA();
                    if (pgmRtn) return;
                }
            }
        }
        BPCRMRTD.INFO.FUNC = 'B';
        BPCRMRTD.INFO.OPT = 'E';
        CEP.TRC(SCCGWA, BPCRMRTD.INFO.POINTER);
        S000_CALL_BPZRMRTD();
        if (pgmRtn) return;
    }
    public void B300_CHECK_RETURN() throws IOException,SQLException,Exception {
        if (BPCRIPDM.RETURN_INFO == 'N') {
            if (BPCSIPOM.FUNC == 'Q' 
                || BPCSIPOM.FUNC == 'U' 
                || BPCSIPOM.FUNC == 'D') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_RECORD_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCSIPOM.FUNC == 'C') {
                BPCSIPOM.CHECK_RESULT = 'N';
            }
        }
        if (BPCRIPDM.RETURN_INFO == 'F') {
            if (BPCSIPOM.FUNC == 'C') {
                BPCSIPOM.CHECK_RESULT = 'E';
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
            SCCMPAG.MAX_COL_NO = 168;
            SCCMPAG.SCR_ROW_CNT = 57;
            SCCMPAG.SCR_COL_CNT = 99;
            B_MPAG();
            if (pgmRtn) return;
        }
        if (WS_OUTPUT_FLG == 'D') {
            IBS.init(SCCGWA, WS_OUTPUT_DATA);
            WS_OUTPUT_DATA.WS_OT_CCY = BPRRTID.CCY;
            WS_OUTPUT_DATA.WS_OT_B_TYPE = BPRRTID.BASE_TYP;
            CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_OT_CCY);
            CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_OT_B_TYPE);
            IBS.init(SCCGWA, BPCOQPCD);
            BPCOQPCD.INPUT_DATA.TYPE = K_BASE_TYPE;
            BPCOQPCD.INPUT_DATA.CODE = WS_OUTPUT_DATA.WS_OT_B_TYPE;
            S000_CALL_BPZPQPCD();
            if (pgmRtn) return;
            WS_OUTPUT_DATA.WS_OT_BASE_NAME = BPCOQPCD.OUTPUT_DATA.CODE_INFO.NAME;
            CEP.TRC(SCCGWA, BPCOQPCD.OUTPUT_DATA.CODE_INFO.NAME);
            WS_OUTPUT_DATA.WS_OT_TENOR = BPRRTID.TENOR;
            CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_OT_TENOR);
            IBS.init(SCCGWA, BPCOQPCD);
            BPCOQPCD.INPUT_DATA.TYPE = K_TENOR;
            BPCOQPCD.INPUT_DATA.CODE = WS_OUTPUT_DATA.WS_OT_TENOR;
            S000_CALL_BPZPQPCD();
            if (pgmRtn) return;
            WS_OUTPUT_DATA.WS_OT_TENOR_NAME = BPCOQPCD.OUTPUT_DATA.CODE_INFO.NAME;
            WS_OUTPUT_DATA.WS_RATE_ID = BPRRTID.KEY.ID;
            WS_OUTPUT_DATA.WS_INPUT_DATE = BPRRTID.INPUT_DT;
            WS_OUTPUT_DATA.WS_LAST_DATE = BPRRTID.LAST_UPD_DT;
            WS_OUTPUT_DATA.WS_LAST_TLR = BPRRTID.LAST_TELLER;
            IBS.init(SCCGWA, SCCMPAG);
            SCCMPAG.FUNC = 'D';
            SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_DATA);
            SCCMPAG.DATA_LEN = 168;
            B_MPAG();
            if (pgmRtn) return;
        }
    }
    public void B221_01_GET_RATE_ID() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQRTD);
        BPCOQRTD.DATA.CCY = BPRIRPD.KEY.CCY;
        BPCOQRTD.DATA.BASE_TYP = BPRIRPD.KEY.BASE_TYP;
        BPCOQRTD.DATA.TENOR = BPRIRPD.KEY.TENOR;
        BPCOQRTD.INQ_FLG = 'C';
        CEP.TRC(SCCGWA, BPCOQRTD.DATA.CCY);
        CEP.TRC(SCCGWA, BPCOQRTD.DATA.BASE_TYP);
        CEP.TRC(SCCGWA, BPCOQRTD.DATA.TENOR);
        S000_CALL_BPZPQRTD();
        if (pgmRtn) return;
    }
    public void B300_FMT_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCIPDMO);
        B300_OUTPUT_BASIC_DATA();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCIPDMO;
        SCCFMT.DATA_LEN = 142;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B300_FMT_OUTPUT_DATA_ADD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCORTID);
        B300_OUTPUT_ADD_DATA();
        if (pgmRtn) return;
        for (WS_I = 1; WS_I <= 20; WS_I += 1) {
            if (BPCORTID.TENOR_DATA[WS_I-1].TENOR.equalsIgnoreCase("0")) {
                BPCORTID.TENOR_DATA[WS_I-1].TENOR_STR = " ";
            }
        }
        CEP.TRC(SCCGWA, BPCORTID.DATA_CNT);
        CEP.TRC(SCCGWA, BPCORTID);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_ADD;
        SCCFMT.DATA_PTR = BPCORTID;
        SCCFMT.DATA_LEN = 328;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B300_OUTPUT_ADD_DATA() throws IOException,SQLException,Exception {
        BPCORTID.B_TYPE = BPCSIPOM.B_TYPE;
        BPCORTID.DATA_CNT = 0;
        for (WS_I = 1; WS_I <= 20; WS_I += 1) {
            BPCORTID.TENOR_DATA[WS_I-1].CCY = BPCSIPOM.TEN_TBL[WS_I-1].CCY_TBL;
            BPCORTID.TENOR_DATA[WS_I-1].TENOR = BPCSIPOM.TEN_TBL[WS_I-1].TENOR_TBL;
