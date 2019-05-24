package com.hisun.VT;

import com.hisun.BP.*;
import com.hisun.AI.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCSUBS;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class VTZSITMR {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_P_INQ_ORG_LOW = "BP-P-INQ-ORG-LOW";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY";
    String K_GL_BOOK = "BK001";
    String WS_ERR_MSG = " ";
    short WS_CNT = 0;
    short WS_LEN = 0;
    VTZSITMR_WS_BR_RANGE WS_BR_RANGE = new VTZSITMR_WS_BR_RANGE();
    VTZSITMR_WS_OUTPUT WS_OUTPUT = new VTZSITMR_WS_OUTPUT();
    VTZSITMR_WS_BROWSE_OUTPUT WS_BROWSE_OUTPUT = new VTZSITMR_WS_BROWSE_OUTPUT();
    char WS_FND_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    VTCMSG_ERROR_MSG VTCMSG_ERROR_MSG = new VTCMSG_ERROR_MSG();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    VTRITMR VTRITMR = new VTRITMR();
    SCCFMT SCCFMT = new SCCFMT();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCMPAG SCCMPAG = new SCCMPAG();
    VTCRITMR VTCRITMR = new VTCRITMR();
    VTRVTCD VTRVTCD = new VTRVTCD();
    VTCRVTCD VTCRVTCD = new VTCRVTCD();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCPQORG BPCPQORG = new BPCPQORG();
    AICPQITM AICPQITM = new AICPQITM();
    AIRCMIB AIRCMIB = new AIRCMIB();
    AICPCMIB AICPCMIB = new AICPCMIB();
    BPCPQRGC BPCPQRGC = new BPCPQRGC();
    VTRITMR VTROITMR = new VTRITMR();
    VTRITMR VTRNITMR = new VTRITMR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    VTCSITMR VTCSITMR;
    public void MP(SCCGWA SCCGWA, VTCSITMR VTCSITMR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.VTCSITMR = VTCSITMR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "VTZSITMR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (VTCSITMR.FUNC == 'B') {
            B020_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else if (VTCSITMR.FUNC == 'A') {
            B040_ADD_PROCESS();
            if (pgmRtn) return;
        } else if (VTCSITMR.FUNC == 'Q') {
            B030_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (VTCSITMR.FUNC == 'M') {
            B050_MODIFY_RECORD();
            if (pgmRtn) return;
        } else if (VTCSITMR.FUNC == 'D') {
            B070_DELETE_RECORD();
            if (pgmRtn) return;
        } else if (VTCSITMR.FUNC == 'R') {
            B060_REACTIVE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_FUNC_CODE_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (VTCSITMR.FUNC != 'B') {
            R000_ITMR_INFO_OUTPUT();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (VTCSITMR.FUNC == 'A') {
            IBS.init(SCCGWA, VTRITMR);
            IBS.init(SCCGWA, VTCRITMR);
            VTRITMR.KEY.COA_FLG = VTCSITMR.COA_FLG;
            VTRITMR.KEY.ITM = VTCSITMR.ITM;
            VTRITMR.KEY.AC_SEQ = VTCSITMR.AC_SEQ;
            VTRITMR.KEY.BR = VTCSITMR.BR;
            VTRITMR.KEY.CCY = VTCSITMR.CCY;
            VTRITMR.KEY.CODE = VTCSITMR.CODE;
            VTCRITMR.FUNC = 'Q';
            VTCRITMR.POINTER = VTRITMR;
            VTCRITMR.REC_LEN = 180;
            S000_CALL_VTZRITMR();
            if (pgmRtn) return;
            if (VTCRITMR.RETURN_INFO == 'F') {
                WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_ITMR_CODE_FOUND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (VTCSITMR.CODE.trim().length() > 0) {
                IBS.init(SCCGWA, VTRVTCD);
                IBS.init(SCCGWA, VTCRVTCD);
                VTRVTCD.KEY.CODE = VTCSITMR.CODE;
                VTCRVTCD.FUNC = 'Q';
                VTCRVTCD.POINTER = VTRVTCD;
                VTCRVTCD.REC_LEN = 206;
                S000_CALL_VTZRVTCD();
                if (pgmRtn) return;
                if (VTCRVTCD.RETURN_INFO == 'N') {
                    WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_TAX_CODE_NOT_FOUND;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (VTCSITMR.BR != 99999999) {
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BR = VTCSITMR.BR;
                S000_CALL_BPZPQORG();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, AICPQITM);
            AICPQITM.INPUT_DATA.COA_FLG = VTCSITMR.COA_FLG;
            AICPQITM.INPUT_DATA.NO = VTCSITMR.ITM;
            S000_CALL_AIZPQITM();
            if (pgmRtn) return;
            if (AICPQITM.RC.RTNCODE != 0) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_AITITM_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (AICPQITM.OUTPUT_DATA.LOW_LVL_FLG == 'N') {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_COA_NO_LOW_LVL;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (AICPQITM.OUTPUT_DATA.STS == 'P' 
                || AICPQITM.OUTPUT_DATA.STS == 'C') {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_ITM_STS_ERROR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (VTCSITMR.AC_SEQ != 0) {
                IBS.init(SCCGWA, AICPCMIB);
                IBS.init(SCCGWA, AIRCMIB);
                AIRCMIB.KEY.GL_BOOK = K_GL_BOOK;
                AIRCMIB.KEY.BR = VTCSITMR.BR;
                AIRCMIB.KEY.ITM = VTCSITMR.ITM;
                AIRCMIB.KEY.SEQ = VTCSITMR.AC_SEQ;
                S000_CALL_AIZPCMIB();
                if (pgmRtn) return;
                if (AIRCMIB.STS != 'N') {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_CMIB_STS_WRONG;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (!VTCSITMR.CCY.equalsIgnoreCase("999")) {
                IBS.init(SCCGWA, BPCQCCY);
                BPCQCCY.DATA.CCY = VTCSITMR.CCY;
                S000_CALL_BPZQCCY();
                if (pgmRtn) return;
            }
        }
        if (VTCSITMR.FUNC == 'A' 
            || VTCSITMR.FUNC == 'M') {
            if (VTCSITMR.BR_RGN.trim().length() > 0) {
                IBS.init(SCCGWA, BPCPQRGC);
                IBS.CPY2CLS(SCCGWA, VTCSITMR.BR_RGN, WS_BR_RANGE);
                BPCPQRGC.RGN_NO.RGN_TYPE = WS_BR_RANGE.WS_BR_TYPE;
                BPCPQRGC.RGN_NO.RGN_SEQ = WS_BR_RANGE.WS_BR_SEQ;
                BPCPQRGC.BNK = SCCGWA.COMM_AREA.TR_BANK;
                S000_CALL_BPZPQRGC();
                if (pgmRtn) return;
            }
        }
        if (VTCSITMR.FUNC == 'A' 
            || VTCSITMR.FUNC == 'M') {
            IBS.init(SCCGWA, VTRVTCD);
            IBS.init(SCCGWA, VTCRVTCD);
            VTRVTCD.KEY.CODE = VTCSITMR.CODE;
            VTCRVTCD.FUNC = 'Q';
            VTCRVTCD.POINTER = VTRVTCD;
            VTCRVTCD.REC_LEN = 206;
            S000_CALL_VTZRVTCD();
            if (pgmRtn) return;
            if (VTRVTCD.EFF_DATE > SCCGWA.COMM_AREA.AC_DATE 
                || VTRVTCD.EXP_DATE < SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_CODE_IS_EXP;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, VTRVTCD.RT);
            CEP.TRC(SCCGWA, VTCSITMR.TAX_TYPE);
            if (VTRVTCD.RT == 0 
                && VTCSITMR.TAX_TYPE != '0') {
                WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_RT_TYPE_ZERO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, VTRVTCD.RT);
            CEP.TRC(SCCGWA, VTCSITMR.TAX_TYPE);
            if (VTRVTCD.RT != 0 
                && VTCSITMR.TAX_TYPE == '0') {
                WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_RT_TAX_TYPE_ZERO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, VTCSITMR.TAX_TYPE);
            CEP.TRC(SCCGWA, VTCSITMR.FREE_TYPE);
            if (VTCSITMR.TAX_TYPE == '0' 
                && VTCSITMR.FREE_TYPE == ' ') {
                WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_FREE_TAX_TYPE_NULL;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (VTCSITMR.FUNC == 'D' 
            && VTCSITMR.STS == 'D') {
            WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_ITM_HAD_DELETE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 259;
        SCCMPAG.SCR_ROW_CNT = 20;
        SCCMPAG.SCR_COL_CNT = 5;
        B_MPAG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, VTRITMR);
        WS_LEN = 10;
        for (WS_CNT = 1; WS_CNT <= WS_LEN; WS_CNT += 1) {
            if (VTCSITMR.ITM == null) VTCSITMR.ITM = "";
            JIBS_tmp_int = VTCSITMR.ITM.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) VTCSITMR.ITM += " ";
            if (VTCSITMR.ITM.substring(WS_CNT - 1, WS_CNT + 1 - 1).trim().length() > 0) {
                if (VTCSITMR.ITM == null) VTCSITMR.ITM = "";
                JIBS_tmp_int = VTCSITMR.ITM.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) VTCSITMR.ITM += " ";
                if (VTRITMR.KEY.ITM == null) VTRITMR.KEY.ITM = "";
                JIBS_tmp_int = VTRITMR.KEY.ITM.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) VTRITMR.KEY.ITM += " ";
                VTRITMR.KEY.ITM = VTRITMR.KEY.ITM.substring(0, WS_CNT - 1) + VTCSITMR.ITM.substring(WS_CNT - 1, WS_CNT + 1 - 1) + VTRITMR.KEY.ITM.substring(WS_CNT + 1 - 1);
            } else {
                if (VTRITMR.KEY.ITM == null) VTRITMR.KEY.ITM = "";
                JIBS_tmp_int = VTRITMR.KEY.ITM.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) VTRITMR.KEY.ITM += " ";
                VTRITMR.KEY.ITM = VTRITMR.KEY.ITM.substring(0, WS_CNT - 1) + "%" + VTRITMR.KEY.ITM.substring(WS_CNT + 1 - 1);
            }
        }
        VTRITMR.KEY.AC_SEQ = VTCSITMR.AC_SEQ;
        VTRITMR.KEY.CODE = VTCSITMR.CODE;
        VTRITMR.BILL_FLG = VTCSITMR.BILL_FLG;
        VTRITMR.KEY.COA_FLG = VTCSITMR.COA_FLG;
        CEP.TRC(SCCGWA, VTCSITMR.AC_SEQ);
        CEP.TRC(SCCGWA, VTCSITMR.CODE);
        CEP.TRC(SCCGWA, VTCSITMR.BILL_FLG);
        CEP.TRC(SCCGWA, VTCSITMR.COA_FLG);
        VTCRITMR.FUNC = 'B';
        VTCRITMR.OPT = 'S';
        VTCRITMR.POINTER = VTRITMR;
        VTCRITMR.REC_LEN = 180;
        S000_CALL_VTZRITMR();
        if (pgmRtn) return;
        VTCRITMR.OPT = 'N';
        S000_CALL_VTZRITMR();
        if (pgmRtn) return;
        while (VTCRITMR.RETURN_INFO != 'N') {
            R00_BRW_OUTPUT();
            if (pgmRtn) return;
            VTCRITMR.OPT = 'N';
            S000_CALL_VTZRITMR();
            if (pgmRtn) return;
        }
        VTCRITMR.OPT = 'E';
        S000_CALL_VTZRITMR();
        if (pgmRtn) return;
    }
    public void B040_ADD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, VTCRITMR);
        IBS.init(SCCGWA, VTRITMR);
        IBS.init(SCCGWA, VTROITMR);
        IBS.init(SCCGWA, VTRNITMR);
        VTRITMR.KEY.COA_FLG = VTCSITMR.COA_FLG;
        VTRITMR.KEY.ITM = VTCSITMR.ITM;
        VTRITMR.KEY.AC_SEQ = VTCSITMR.AC_SEQ;
        VTRITMR.KEY.BR = VTCSITMR.BR;
        VTRITMR.KEY.CCY = VTCSITMR.CCY;
        VTRITMR.KEY.CODE = VTCSITMR.CODE;
        VTRITMR.BR_RGN = VTCSITMR.BR_RGN;
        VTRITMR.BILL_FLG = VTCSITMR.BILL_FLG;
        VTRITMR.TAX_FLG = VTCSITMR.TAX_FLG;
        VTRITMR.TAX_TYPE = VTCSITMR.TAX_TYPE;
        VTRITMR.FREE_TYPE = VTCSITMR.FREE_TYPE;
        VTRITMR.BILL_LIM = VTCSITMR.BILL_LIM;
        VTRITMR.RMK = VTCSITMR.DESC;
        VTRITMR.OPEN_TLR = SCCGWA.COMM_AREA.TL_ID;
        VTRITMR.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        VTRITMR.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        VTRITMR.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        VTRITMR.STS = 'N';
        VTCRITMR.FUNC = 'C';
        VTCRITMR.POINTER = VTRITMR;
        VTCRITMR.REC_LEN = 180;
        S000_CALL_VTZRITMR();
        if (pgmRtn) return;
        S000_SAVE_NEW_DATA();
        if (pgmRtn) return;
        H000_HISTORY_PROCESS();
        if (pgmRtn) return;
    }
    public void B050_MODIFY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, VTCRITMR);
        IBS.init(SCCGWA, VTRITMR);
        IBS.init(SCCGWA, VTROITMR);
        IBS.init(SCCGWA, VTRNITMR);
        VTRITMR.KEY.COA_FLG = VTCSITMR.COA_FLG;
        VTRITMR.KEY.ITM = VTCSITMR.ITM;
        VTRITMR.KEY.AC_SEQ = VTCSITMR.AC_SEQ;
        VTRITMR.KEY.BR = VTCSITMR.BR;
        VTRITMR.KEY.CCY = VTCSITMR.CCY;
        VTRITMR.KEY.CODE = VTCSITMR.CODE;
        VTCRITMR.FUNC = 'Q';
        VTCRITMR.POINTER = VTRITMR;
        VTCRITMR.REC_LEN = 180;
        S000_CALL_VTZRITMR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TEST...");
        if (VTRITMR.STS == 'D') {
            CEP.TRC(SCCGWA, "ITMR-STS-D");
            WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_ITM_HAD_DELETE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        VTCRITMR.FUNC = 'R';
        VTCRITMR.POINTER = VTRITMR;
        VTCRITMR.REC_LEN = 180;
        S000_CALL_VTZRITMR();
        if (pgmRtn) return;
        S000_SAVE_OLD_DATA();
        if (pgmRtn) return;
        VTCRITMR.FUNC = 'U';
        VTRITMR.BR_RGN = VTCSITMR.BR_RGN;
        CEP.TRC(SCCGWA, VTRITMR.BR_RGN);
        VTRITMR.BILL_FLG = VTCSITMR.BILL_FLG;
        VTRITMR.TAX_FLG = VTCSITMR.TAX_FLG;
        VTRITMR.TAX_TYPE = VTCSITMR.TAX_TYPE;
        VTRITMR.FREE_TYPE = VTCSITMR.FREE_TYPE;
        VTRITMR.BILL_LIM = VTCSITMR.BILL_LIM;
        VTRITMR.RMK = VTCSITMR.DESC;
        VTRITMR.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        VTRITMR.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_VTZRITMR();
        if (pgmRtn) return;
        S000_SAVE_NEW_DATA();
        if (pgmRtn) return;
        H000_HISTORY_PROCESS();
        if (pgmRtn) return;
    }
    public void B060_REACTIVE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, VTCRITMR);
        IBS.init(SCCGWA, VTRITMR);
        IBS.init(SCCGWA, VTROITMR);
        IBS.init(SCCGWA, VTRNITMR);
        VTRITMR.KEY.COA_FLG = VTCSITMR.COA_FLG;
        VTRITMR.KEY.ITM = VTCSITMR.ITM;
        VTRITMR.KEY.AC_SEQ = VTCSITMR.AC_SEQ;
        VTRITMR.KEY.BR = VTCSITMR.BR;
        VTRITMR.KEY.CCY = VTCSITMR.CCY;
        VTRITMR.KEY.CODE = VTCSITMR.CODE;
        VTCRITMR.FUNC = 'Q';
        VTCRITMR.POINTER = VTRITMR;
        VTCRITMR.REC_LEN = 180;
        S000_CALL_VTZRITMR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TEST...");
        if (VTRITMR.STS == 'D') {
            VTCRITMR.FUNC = 'R';
            VTCRITMR.POINTER = VTRITMR;
            VTCRITMR.REC_LEN = 180;
            S000_CALL_VTZRITMR();
            if (pgmRtn) return;
            S000_SAVE_OLD_DATA();
            if (pgmRtn) return;
            VTCRITMR.FUNC = 'U';
            VTRITMR.STS = 'N';
            VTRITMR.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            VTRITMR.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            S000_CALL_VTZRITMR();
            if (pgmRtn) return;
            S000_SAVE_NEW_DATA();
            if (pgmRtn) return;
            H000_HISTORY_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void B070_DELETE_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, VTCRITMR);
        IBS.init(SCCGWA, VTRITMR);
        IBS.init(SCCGWA, VTROITMR);
        IBS.init(SCCGWA, VTRNITMR);
        VTRITMR.KEY.COA_FLG = VTCSITMR.COA_FLG;
        VTRITMR.KEY.ITM = VTCSITMR.ITM;
        VTRITMR.KEY.AC_SEQ = VTCSITMR.AC_SEQ;
        VTRITMR.KEY.BR = VTCSITMR.BR;
        VTRITMR.KEY.CCY = VTCSITMR.CCY;
        VTRITMR.KEY.CODE = VTCSITMR.CODE;
        VTCRITMR.FUNC = 'Q';
        VTCRITMR.POINTER = VTRITMR;
        VTCRITMR.REC_LEN = 180;
        S000_CALL_VTZRITMR();
        if (pgmRtn) return;
        if (VTRITMR.STS == 'D') {
            WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_ITM_HAD_DELETE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        VTCRITMR.FUNC = 'R';
        VTCRITMR.POINTER = VTRITMR;
        VTCRITMR.REC_LEN = 180;
        S000_CALL_VTZRITMR();
        if (pgmRtn) return;
        S000_SAVE_OLD_DATA();
        if (pgmRtn) return;
        VTCRITMR.FUNC = 'U';
        VTRITMR.STS = 'D';
        S000_CALL_VTZRITMR();
        if (pgmRtn) return;
        S000_SAVE_NEW_DATA();
        if (pgmRtn) return;
        H000_HISTORY_PROCESS();
        if (pgmRtn) return;
    }
    public void B030_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, VTRITMR);
        IBS.init(SCCGWA, VTCRITMR);
        VTRITMR.KEY.COA_FLG = VTCSITMR.COA_FLG;
        VTRITMR.KEY.ITM = VTCSITMR.ITM;
        VTRITMR.KEY.AC_SEQ = VTCSITMR.AC_SEQ;
        VTRITMR.KEY.BR = VTCSITMR.BR;
        VTRITMR.KEY.CCY = VTCSITMR.CCY;
        VTRITMR.KEY.CODE = VTCSITMR.CODE;
        VTCRITMR.FUNC = 'Q';
        VTCRITMR.POINTER = VTRITMR;
        VTCRITMR.REC_LEN = 180;
        S000_CALL_VTZRITMR();
        if (pgmRtn) return;
        if (VTCRITMR.RETURN_INFO == 'N') {
            WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_ITMR_CODE_NOT_FOUND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_ITMR_INFO_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        IBS.init(SCCGWA, WS_OUTPUT);
        WS_OUTPUT.WS_COA_FLG = VTRITMR.KEY.COA_FLG;
        WS_OUTPUT.WS_ITM = VTRITMR.KEY.ITM;
        WS_OUTPUT.WS_AC_SEQ = VTRITMR.KEY.AC_SEQ;
        WS_OUTPUT.WS_BR = VTRITMR.KEY.BR;
        WS_OUTPUT.WS_CCY = VTRITMR.KEY.CCY;
        WS_OUTPUT.WS_CODE = VTRITMR.KEY.CODE;
        WS_OUTPUT.WS_BR_RGN = VTRITMR.BR_RGN;
        WS_OUTPUT.WS_BILL_FLG = VTRITMR.BILL_FLG;
        WS_OUTPUT.WS_TAX_FLG = VTRITMR.TAX_FLG;
        WS_OUTPUT.WS_TAX_TYPE = VTRITMR.TAX_TYPE;
        WS_OUTPUT.WS_FREE_TYPE = VTRITMR.FREE_TYPE;
        WS_OUTPUT.WS_BILL_LIM = VTRITMR.BILL_LIM;
        WS_OUTPUT.WS_DESC = VTRITMR.RMK;
        WS_OUTPUT.WS_STS = VTRITMR.STS;
        CEP.TRC(SCCGWA, VTRITMR.STS);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_STS);
        IBS.init(SCCGWA, VTRVTCD);
        IBS.init(SCCGWA, VTCRVTCD);
        VTRVTCD.KEY.CODE = VTRITMR.KEY.CODE;
        VTCRVTCD.FUNC = 'Q';
        VTCRVTCD.POINTER = VTRVTCD;
        VTCRVTCD.REC_LEN = 206;
        S000_CALL_VTZRVTCD();
        if (pgmRtn) return;
        WS_OUTPUT.WS_RT = VTRVTCD.RT;
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_RT);
        CEP.TRC(SCCGWA, VTRITMR.KEY.CODE);
        SCCFMT.FMTID = "VT030";
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 108;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R00_BRW_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        IBS.init(SCCGWA, WS_BROWSE_OUTPUT);
        WS_BROWSE_OUTPUT.WS_BRW_STS = VTRITMR.STS;
        WS_BROWSE_OUTPUT.WS_BRW_ITM = VTRITMR.KEY.ITM;
        WS_BROWSE_OUTPUT.WS_BRW_AC_SEQ = VTRITMR.KEY.AC_SEQ;
        WS_BROWSE_OUTPUT.WS_BRW_BR = VTRITMR.KEY.BR;
        WS_BROWSE_OUTPUT.WS_BRW_CCY = VTRITMR.KEY.CCY;
        WS_BROWSE_OUTPUT.WS_BRW_CODE = VTRITMR.KEY.CODE;
        WS_BROWSE_OUTPUT.WS_BRW_BILL_FLG = VTRITMR.BILL_FLG;
        WS_BROWSE_OUTPUT.WS_BRW_TAX_FLG = VTRITMR.TAX_FLG;
        WS_BROWSE_OUTPUT.WS_BRW_TAX_TYPE = VTRITMR.TAX_TYPE;
        WS_BROWSE_OUTPUT.WS_BRW_FREE_TYPE = VTRITMR.FREE_TYPE;
        WS_BROWSE_OUTPUT.WS_BRW_BILL_LIM = VTRITMR.BILL_LIM;
        WS_BROWSE_OUTPUT.WS_BRW_DESC = VTRITMR.RMK;
        if (VTRITMR.KEY.AC_SEQ == 0) {
            IBS.init(SCCGWA, AICPQITM);
            AICPQITM.INPUT_DATA.COA_FLG = VTRITMR.KEY.COA_FLG;
            AICPQITM.INPUT_DATA.NO = VTRITMR.KEY.ITM;
            CEP.TRC(SCCGWA, AICPQITM.INPUT_DATA.NO);
            S000_CALL_AIZPQITM();
            if (pgmRtn) return;
            WS_BROWSE_OUTPUT.WS_BRW_ITM_NM = AICPQITM.OUTPUT_DATA.CHS_NM;
        } else {
            IBS.init(SCCGWA, AICPCMIB);
            IBS.init(SCCGWA, AIRCMIB);
            AIRCMIB.KEY.GL_BOOK = K_GL_BOOK;
            AIRCMIB.KEY.BR = VTRITMR.KEY.BR;
            AIRCMIB.KEY.ITM = VTRITMR.KEY.ITM;
            AIRCMIB.KEY.SEQ = VTRITMR.KEY.AC_SEQ;
            CEP.TRC(SCCGWA, VTRITMR.KEY.ITM);
            CEP.TRC(SCCGWA, VTRITMR.KEY.AC_SEQ);
            S000_CALL_AIZPCMIB();
            if (pgmRtn) return;
            WS_BROWSE_OUTPUT.WS_BRW_ITM_NM = AIRCMIB.CHS_NM;
        }
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_BROWSE_OUTPUT);
        SCCMPAG.DATA_LEN = 259;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_SAVE_NEW_DATA() throws IOException,SQLException,Exception {
        VTRNITMR.KEY.COA_FLG = VTCSITMR.COA_FLG;
        VTRNITMR.KEY.ITM = VTCSITMR.ITM;
        VTRNITMR.KEY.AC_SEQ = VTCSITMR.AC_SEQ;
        VTRNITMR.KEY.BR = VTCSITMR.BR;
        VTRNITMR.KEY.CCY = VTCSITMR.CCY;
        VTRNITMR.KEY.CODE = VTCSITMR.CODE;
        VTRNITMR.BR_RGN = VTCSITMR.BR_RGN;
        VTRNITMR.BILL_FLG = VTCSITMR.BILL_FLG;
        VTRNITMR.TAX_FLG = VTCSITMR.TAX_FLG;
        VTRNITMR.TAX_TYPE = VTCSITMR.TAX_TYPE;
        VTRNITMR.FREE_TYPE = VTCSITMR.FREE_TYPE;
        VTRNITMR.BILL_LIM = VTCSITMR.BILL_LIM;
        VTRNITMR.RMK = VTCSITMR.DESC;
        VTRNITMR.OPEN_TLR = SCCGWA.COMM_AREA.TL_ID;
        VTRNITMR.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        VTRNITMR.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        VTRNITMR.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        if (VTCSITMR.FUNC == 'A'
            || VTCSITMR.FUNC == 'M'
            || VTCSITMR.FUNC == 'R') {
            VTRNITMR.STS = 'N';
        } else if (VTCSITMR.FUNC == 'D') {
            VTRNITMR.STS = 'D';
        } else {
            WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_FUNC_CODE_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_SAVE_OLD_DATA() throws IOException,SQLException,Exception {
        VTROITMR.KEY.COA_FLG = VTRITMR.KEY.COA_FLG;
        VTROITMR.KEY.ITM = VTRITMR.KEY.ITM;
        VTROITMR.KEY.AC_SEQ = VTRITMR.KEY.AC_SEQ;
        VTROITMR.KEY.BR = VTRITMR.KEY.BR;
        VTROITMR.KEY.CCY = VTRITMR.KEY.CCY;
        VTROITMR.KEY.CODE = VTRITMR.KEY.CODE;
        VTROITMR.BR_RGN = VTRITMR.BR_RGN;
        VTROITMR.BILL_FLG = VTRITMR.BILL_FLG;
        VTROITMR.TAX_FLG = VTRITMR.TAX_FLG;
        VTROITMR.TAX_TYPE = VTRITMR.TAX_TYPE;
        VTROITMR.FREE_TYPE = VTRITMR.FREE_TYPE;
        VTROITMR.BILL_LIM = VTRITMR.BILL_LIM;
        VTROITMR.RMK = VTRITMR.RMK;
        VTROITMR.UPD_DATE = VTRITMR.UPD_DATE;
        VTROITMR.UPD_TLR = VTRITMR.UPD_TLR;
        VTROITMR.STS = VTRITMR.STS;
    }
    public void H000_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        if (VTCSITMR.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
            BPCPNHIS.INFO.TX_RMK = "ADD VTTPRDR INFO";
        } else if (VTCSITMR.FUNC == 'M'
            || VTCSITMR.FUNC == 'R') {
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.TX_RMK = "CHANGE VTTPRDR INFO";
        } else if (VTCSITMR.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
            BPCPNHIS.INFO.TX_RMK = "DELETE VTTPRDR INFO";
        } else {
            WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_FUNC_CODE_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID = "VT030";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.FMT_ID_LEN = 108;
        BPCPNHIS.INFO.OLD_DAT_PT = VTROITMR;
        BPCPNHIS.INFO.NEW_DAT_PT = VTRNITMR;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_VTZRITMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "VT-R-ITMR-MAINTAIN", VTCRITMR);
    }
    public void S000_CALL_VTZRVTCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "VT-R-VTCD-MAINTAIN", VTCRVTCD);
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_INQUIRE_CCY, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZPQITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-ITM", AICPQITM);
    }
    public void S000_CALL_AIZPCMIB() throws IOException,SQLException,Exception {
        AICPCMIB.POINTER = AIRCMIB;
        AICPCMIB.REC_LEN = 407;
        IBS.CALLCPN(SCCGWA, "AI-P-GET-CMIB", AICPCMIB);
        CEP.TRC(SCCGWA, AICPCMIB.RC.RC_CODE);
        CEP.TRC(SCCGWA, AICPCMIB.RETURN_INFO);
        if (AICPCMIB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPCMIB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQRGC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-RGNC", BPCPQRGC);
        if (BPCPQRGC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQRGC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG);
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
