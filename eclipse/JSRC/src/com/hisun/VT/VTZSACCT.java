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

public class VTZSACCT {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_GL_BOOK = "BK001";
    int K_BR = 999999;
    String WS_ERR_MSG = " ";
    VTZSACCT_WS_OUTPUT WS_OUTPUT = new VTZSACCT_WS_OUTPUT();
    VTZSACCT_WS_BROWSE_OUTPUT WS_BROWSE_OUTPUT = new VTZSACCT_WS_BROWSE_OUTPUT();
    char WS_FND_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    VTCMSG_ERROR_MSG VTCMSG_ERROR_MSG = new VTCMSG_ERROR_MSG();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCMPAG SCCMPAG = new SCCMPAG();
    VTCRACCT VTCRACCT = new VTCRACCT();
    VTRACCT VTRACCT = new VTRACCT();
    AICPQITM AICPQITM = new AICPQITM();
    AIRCMIB AIRCMIB = new AIRCMIB();
    AICPCMIB AICPCMIB = new AICPCMIB();
    VTRVTCD VTRVTCD = new VTRVTCD();
    VTCRVTCD VTCRVTCD = new VTCRVTCD();
    VTRACCT VTROACCT = new VTRACCT();
    VTRACCT VTRNACCT = new VTRACCT();
    VTRITMR VTRITMR = new VTRITMR();
    VTCRITMR VTCRITMR = new VTCRITMR();
    VTRPRDR VTRPRDR = new VTRPRDR();
    VTCRPRDR VTCRPRDR = new VTCRPRDR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    VTCSACCT VTCSACCT;
    public void MP(SCCGWA SCCGWA, VTCSACCT VTCSACCT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.VTCSACCT = VTCSACCT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "VTZSACCT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (VTCSACCT.FUNC == 'B') {
            B020_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else if (VTCSACCT.FUNC == 'A') {
            B040_ADD_PROCESS();
            if (pgmRtn) return;
        } else if (VTCSACCT.FUNC == 'Q') {
            B030_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (VTCSACCT.FUNC == 'M') {
            B050_MODIFY_RECORD();
            if (pgmRtn) return;
        } else if (VTCSACCT.FUNC == 'D') {
            B070_DELETE_RECORD();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_FUNC_CODE_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (VTCSACCT.FUNC != 'B') {
            R000_ACCT_INFO_OUTPUT();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (VTCSACCT.FUNC == 'A') {
            if (VTCSACCT.COA_FLG.trim().length() == 0 
                || VTCSACCT.BILL_FLG == ' ' 
                || VTCSACCT.CODE.trim().length() == 0 
                || VTCSACCT.ITM.trim().length() == 0 
                || VTCSACCT.VAT_TYPE == ' ') {
                WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, VTRVTCD);
            IBS.init(SCCGWA, VTCRVTCD);
            VTRVTCD.KEY.CODE = VTCSACCT.CODE;
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
            if (SCCGWA.COMM_AREA.AC_DATE < VTRVTCD.EFF_DATE 
                || SCCGWA.COMM_AREA.AC_DATE > VTRVTCD.EXP_DATE) {
                WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_CODE_IS_EXP;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, VTRACCT);
            IBS.init(SCCGWA, VTCRACCT);
            VTRACCT.KEY.CODE = VTCSACCT.CODE;
            VTRACCT.KEY.BILL_FLG = VTCSACCT.BILL_FLG;
            VTRACCT.KEY.VAT_TYPE = VTCSACCT.VAT_TYPE;
            VTCRACCT.FUNC = 'Q';
            VTCRACCT.POINTER = VTRACCT;
            VTCRACCT.REC_LEN = 133;
            S000_CALL_VTZRACCT();
            if (pgmRtn) return;
            if (VTCRACCT.RETURN_INFO == 'F') {
                WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_ACCT_REC_FOUND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (VTCSACCT.FUNC == 'A' 
            || VTCSACCT.FUNC == 'M') {
            IBS.init(SCCGWA, AICPQITM);
            AICPQITM.INPUT_DATA.COA_FLG = VTCSACCT.COA_FLG;
            AICPQITM.INPUT_DATA.NO = VTCSACCT.ITM;
            S000_CALL_AIZPQITM();
            if (pgmRtn) return;
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
            if (AICPQITM.OUTPUT_DATA.MIB_FLG == 'Y' 
                && VTCSACCT.AC_SEQ == 0) {
                WS_ERR_MSG = VTCMSG_ERROR_MSG.AI_SEQ_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (VTCSACCT.AC_SEQ != 0) {
                IBS.init(SCCGWA, AICPCMIB);
                IBS.init(SCCGWA, AIRCMIB);
                AIRCMIB.KEY.GL_BOOK = K_GL_BOOK;
                AIRCMIB.KEY.BR = K_BR;
                AIRCMIB.KEY.ITM = VTCSACCT.ITM;
                AIRCMIB.KEY.SEQ = VTCSACCT.AC_SEQ;
                S000_CALL_AIZPCMIB();
                if (pgmRtn) return;
                if (AIRCMIB.STS != 'N') {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_CMIB_STS_WRONG;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        if (VTCSACCT.FUNC == 'D') {
            IBS.init(SCCGWA, VTCRPRDR);
            IBS.init(SCCGWA, VTRPRDR);
            VTRPRDR.BILL_FLG = VTCSACCT.BILL_FLG;
            VTCRPRDR.FUNC = 'B';
            VTCRPRDR.OPT = 'S';
            VTCRPRDR.POINTER = VTRPRDR;
            VTCRPRDR.REC_LEN = 179;
            if (VTCRPRDR.RETURN_INFO == 'F' 
                && VTRPRDR.STS == 'N') {
                WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_BILL_FLG_NORMAL;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            S000_CALL_VTZRPRDR();
            if (pgmRtn) return;
            VTCRPRDR.OPT = 'N';
            S000_CALL_VTZRPRDR();
            if (pgmRtn) return;
            while (VTCRPRDR.RETURN_INFO != 'N') {
                if (VTCRPRDR.RETURN_INFO == 'F' 
                    && VTRPRDR.STS == 'N') {
                    WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_BILL_FLG_NORMAL;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                VTCRPRDR.OPT = 'N';
                S000_CALL_VTZRPRDR();
                if (pgmRtn) return;
            }
            VTCRPRDR.OPT = 'E';
            S000_CALL_VTZRPRDR();
            if (pgmRtn) return;
        }
    }
    public void B020_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 250;
        SCCMPAG.SCR_ROW_CNT = 20;
        SCCMPAG.SCR_COL_CNT = 5;
        B_MPAG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, VTRACCT);
        VTCRACCT.FUNC = 'B';
        VTCRACCT.OPT = 'S';
        VTRACCT.TAX_FLG = VTCSACCT.TAX_FLG;
        VTRACCT.TAX_TYPE = VTCSACCT.TAX_TYPE;
        VTRACCT.COA_FLG = VTCSACCT.COA_FLG;
        VTRACCT.KEY.BILL_FLG = VTCSACCT.BILL_FLG;
        VTRACCT.KEY.CODE = VTCSACCT.CODE;
        VTRACCT.KEY.VAT_TYPE = VTCSACCT.VAT_TYPE;
        CEP.TRC(SCCGWA, VTCSACCT.TAX_FLG);
        CEP.TRC(SCCGWA, VTCSACCT.TAX_TYPE);
        CEP.TRC(SCCGWA, VTRACCT.TAX_FLG);
        CEP.TRC(SCCGWA, VTRACCT.TAX_TYPE);
        VTCRACCT.POINTER = VTRACCT;
        VTCRACCT.REC_LEN = 133;
        S000_CALL_VTZRACCT();
        if (pgmRtn) return;
        VTCRACCT.OPT = 'N';
        S000_CALL_VTZRACCT();
        if (pgmRtn) return;
        while (VTCRACCT.RETURN_INFO != 'N') {
            R00_BRW_OUTPUT();
            if (pgmRtn) return;
            VTCRACCT.OPT = 'N';
            S000_CALL_VTZRACCT();
            if (pgmRtn) return;
        }
        VTCRACCT.OPT = 'E';
        S000_CALL_VTZRACCT();
        if (pgmRtn) return;
    }
    public void B040_ADD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, VTCRACCT);
        IBS.init(SCCGWA, VTRACCT);
        IBS.init(SCCGWA, VTROACCT);
        IBS.init(SCCGWA, VTRNACCT);
        VTRACCT.KEY.CODE = VTCSACCT.CODE;
        VTRACCT.KEY.BILL_FLG = VTCSACCT.BILL_FLG;
        VTRACCT.COA_FLG = VTCSACCT.COA_FLG;
        VTRACCT.ITM = VTCSACCT.ITM;
        VTRACCT.AC_SEQ = VTCSACCT.AC_SEQ;
        VTRACCT.RMK = VTCSACCT.REMARK;
        VTRACCT.TAX_FLG = VTCSACCT.TAX_FLG;
        VTRACCT.TAX_TYPE = VTCSACCT.TAX_TYPE;
        VTRACCT.KEY.VAT_TYPE = VTCSACCT.VAT_TYPE;
        VTRACCT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        VTRACCT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        VTCRACCT.FUNC = 'C';
        VTCRACCT.POINTER = VTRACCT;
        VTCRACCT.REC_LEN = 133;
        S000_CALL_VTZRACCT();
        if (pgmRtn) return;
        S000_SAVE_NEW_DATA();
        if (pgmRtn) return;
        H000_HISTORY_PROCESS();
        if (pgmRtn) return;
    }
    public void B050_MODIFY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, VTCRACCT);
        IBS.init(SCCGWA, VTRACCT);
        IBS.init(SCCGWA, VTROACCT);
        IBS.init(SCCGWA, VTRNACCT);
        VTRACCT.KEY.CODE = VTCSACCT.CODE;
        VTRACCT.KEY.BILL_FLG = VTCSACCT.BILL_FLG;
        VTRACCT.KEY.VAT_TYPE = VTCSACCT.VAT_TYPE;
        VTCRACCT.FUNC = 'R';
        VTCRACCT.POINTER = VTRACCT;
        VTCRACCT.REC_LEN = 133;
        S000_CALL_VTZRACCT();
        if (pgmRtn) return;
        S000_SAVE_OLD_DATA();
        if (pgmRtn) return;
        VTCRACCT.FUNC = 'U';
        VTRACCT.COA_FLG = VTCSACCT.COA_FLG;
        VTRACCT.ITM = VTCSACCT.ITM;
        VTRACCT.AC_SEQ = VTCSACCT.AC_SEQ;
        VTRACCT.RMK = VTCSACCT.REMARK;
        VTRACCT.TAX_FLG = VTCSACCT.TAX_FLG;
        VTRACCT.TAX_TYPE = VTCSACCT.TAX_TYPE;
        VTRACCT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        VTRACCT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_VTZRACCT();
        if (pgmRtn) return;
        S000_SAVE_NEW_DATA();
        if (pgmRtn) return;
        H000_HISTORY_PROCESS();
        if (pgmRtn) return;
    }
    public void B070_DELETE_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, VTCRACCT);
        IBS.init(SCCGWA, VTRACCT);
        IBS.init(SCCGWA, VTROACCT);
        IBS.init(SCCGWA, VTRNACCT);
        VTRACCT.KEY.BILL_FLG = VTCSACCT.BILL_FLG;
        VTRACCT.KEY.CODE = VTCSACCT.CODE;
        VTRACCT.COA_FLG = VTCSACCT.COA_FLG;
        VTRACCT.ITM = VTCSACCT.ITM;
        VTRACCT.AC_SEQ = VTCSACCT.AC_SEQ;
        VTRACCT.RMK = VTCSACCT.REMARK;
        VTRACCT.TAX_FLG = VTCSACCT.TAX_FLG;
        VTRACCT.TAX_TYPE = VTCSACCT.TAX_TYPE;
        VTRACCT.KEY.VAT_TYPE = VTCSACCT.VAT_TYPE;
        VTCRACCT.FUNC = 'R';
        VTCRACCT.POINTER = VTRACCT;
        VTCRACCT.REC_LEN = 133;
        S000_CALL_VTZRACCT();
        if (pgmRtn) return;
        S000_SAVE_OLD_DATA();
        if (pgmRtn) return;
        VTCRACCT.FUNC = 'D';
        S000_CALL_VTZRACCT();
        if (pgmRtn) return;
        H000_HISTORY_PROCESS();
        if (pgmRtn) return;
    }
    public void B030_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, VTRACCT);
        IBS.init(SCCGWA, VTCRACCT);
        VTRACCT.KEY.CODE = VTCSACCT.CODE;
        VTRACCT.KEY.BILL_FLG = VTCSACCT.BILL_FLG;
        VTRACCT.KEY.VAT_TYPE = VTCSACCT.VAT_TYPE;
        VTCRACCT.FUNC = 'Q';
        VTCRACCT.POINTER = VTRACCT;
        VTCRACCT.REC_LEN = 133;
        S000_CALL_VTZRACCT();
        if (pgmRtn) return;
        if (VTCRACCT.RETURN_INFO == 'N') {
            WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_ACCT_REC_NOT_FOUND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_ACCT_INFO_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        IBS.init(SCCGWA, WS_OUTPUT);
        WS_OUTPUT.WS_CODE = VTRACCT.KEY.CODE;
        WS_OUTPUT.WS_BILL_FLG = VTRACCT.KEY.BILL_FLG;
        WS_OUTPUT.WS_COA_FLG = VTRACCT.COA_FLG;
        WS_OUTPUT.WS_ITM = VTRACCT.ITM;
        WS_OUTPUT.WS_AC_SEQ = VTRACCT.AC_SEQ;
        WS_OUTPUT.WS_REMARK = VTRACCT.RMK;
        if (VTRACCT.AC_SEQ != 0) {
            IBS.init(SCCGWA, AICPCMIB);
            IBS.init(SCCGWA, AIRCMIB);
            AIRCMIB.KEY.GL_BOOK = K_GL_BOOK;
            AIRCMIB.KEY.BR = K_BR;
            AIRCMIB.KEY.ITM = VTRACCT.ITM;
            AIRCMIB.KEY.SEQ = VTRACCT.AC_SEQ;
            S000_CALL_AIZPCMIB();
            if (pgmRtn) return;
            WS_OUTPUT.WS_ITM_NM = AIRCMIB.CHS_NM;
        } else {
            IBS.init(SCCGWA, AICPQITM);
            AICPQITM.INPUT_DATA.COA_FLG = VTRACCT.COA_FLG;
            AICPQITM.INPUT_DATA.NO = VTRACCT.ITM;
            S000_CALL_AIZPQITM();
            if (pgmRtn) return;
            WS_OUTPUT.WS_ITM_NM = AICPQITM.OUTPUT_DATA.CHS_NM;
        }
        WS_OUTPUT.WS_TAX_FLG = VTRACCT.TAX_FLG;
        WS_OUTPUT.WS_TAX_TYPE = VTRACCT.TAX_TYPE;
        SCCFMT.FMTID = "VT040";
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 250;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R00_BRW_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        IBS.init(SCCGWA, WS_BROWSE_OUTPUT);
        WS_BROWSE_OUTPUT.WS_BRW_CODE = VTRACCT.KEY.CODE;
        WS_BROWSE_OUTPUT.WS_BRW_BILL_FLG = VTRACCT.KEY.BILL_FLG;
        WS_BROWSE_OUTPUT.WS_BRW_COA_FLG = VTRACCT.COA_FLG;
        WS_BROWSE_OUTPUT.WS_BRW_ITM = VTRACCT.ITM;
        WS_BROWSE_OUTPUT.WS_BRW_AC_SEQ = VTRACCT.AC_SEQ;
        WS_BROWSE_OUTPUT.WS_BRW_REMARK = VTRACCT.RMK;
        WS_BROWSE_OUTPUT.WS_BRW_TAX_FLG = VTRACCT.TAX_FLG;
        WS_BROWSE_OUTPUT.WS_BRW_TAX_TYPE = VTRACCT.TAX_TYPE;
        if (VTRACCT.AC_SEQ != 0) {
            IBS.init(SCCGWA, AICPCMIB);
            IBS.init(SCCGWA, AIRCMIB);
            AIRCMIB.KEY.GL_BOOK = K_GL_BOOK;
            AIRCMIB.KEY.BR = K_BR;
            AIRCMIB.KEY.ITM = VTRACCT.ITM;
            AIRCMIB.KEY.SEQ = VTRACCT.AC_SEQ;
            S000_CALL_AIZPCMIB();
            if (pgmRtn) return;
            WS_BROWSE_OUTPUT.WS_BRW_ITM_NM = AIRCMIB.CHS_NM;
        } else {
            IBS.init(SCCGWA, AICPQITM);
            AICPQITM.INPUT_DATA.COA_FLG = VTRACCT.COA_FLG;
            AICPQITM.INPUT_DATA.NO = VTRACCT.ITM;
            S000_CALL_AIZPQITM();
            if (pgmRtn) return;
            WS_BROWSE_OUTPUT.WS_BRW_ITM_NM = AICPQITM.OUTPUT_DATA.CHS_NM;
        }
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_BROWSE_OUTPUT);
        SCCMPAG.DATA_LEN = 250;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_SAVE_NEW_DATA() throws IOException,SQLException,Exception {
        VTRNACCT.KEY.CODE = VTCSACCT.CODE;
        VTRNACCT.KEY.BILL_FLG = VTCSACCT.BILL_FLG;
        VTRNACCT.COA_FLG = VTCSACCT.COA_FLG;
        VTRNACCT.ITM = VTCSACCT.ITM;
        VTRNACCT.AC_SEQ = VTCSACCT.AC_SEQ;
        VTRNACCT.RMK = VTCSACCT.REMARK;
        VTRNACCT.TAX_FLG = VTCSACCT.TAX_FLG;
        VTRNACCT.TAX_TYPE = VTCSACCT.TAX_TYPE;
        VTRNACCT.KEY.VAT_TYPE = VTCSACCT.VAT_TYPE;
        CEP.TRC(SCCGWA, VTCSACCT.CODE);
        CEP.TRC(SCCGWA, VTCSACCT.BILL_FLG);
        CEP.TRC(SCCGWA, VTCSACCT.TAX_FLG);
        CEP.TRC(SCCGWA, VTCSACCT.TAX_TYPE);
        VTRNACCT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        VTRNACCT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
    }
    public void S000_SAVE_OLD_DATA() throws IOException,SQLException,Exception {
        VTROACCT.KEY.CODE = VTRACCT.KEY.CODE;
        VTROACCT.KEY.BILL_FLG = VTRACCT.KEY.BILL_FLG;
        VTROACCT.COA_FLG = VTRACCT.COA_FLG;
        VTROACCT.ITM = VTRACCT.ITM;
        VTROACCT.AC_SEQ = VTRACCT.AC_SEQ;
        VTROACCT.RMK = VTRACCT.RMK;
        VTROACCT.TAX_FLG = VTRACCT.TAX_FLG;
        VTROACCT.TAX_TYPE = VTRACCT.TAX_TYPE;
        VTROACCT.UPD_DATE = VTRACCT.UPD_DATE;
        VTROACCT.UPD_TLR = VTRACCT.UPD_TLR;
        VTROACCT.KEY.VAT_TYPE = VTRACCT.KEY.VAT_TYPE;
    }
    public void H000_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        if (VTCSACCT.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
            BPCPNHIS.INFO.TX_RMK = "ADD VTTACCT INFO";
        } else if (VTCSACCT.FUNC == 'M') {
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.TX_RMK = "CHANGE VTTACCT INFO";
        } else if (VTCSACCT.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
            BPCPNHIS.INFO.TX_RMK = "DELETE VTTACCT INFO";
        } else {
            WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_FUNC_CODE_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID = "VT040";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.FMT_ID_LEN = 250;
        BPCPNHIS.INFO.OLD_DAT_PT = VTROACCT;
        BPCPNHIS.INFO.NEW_DAT_PT = VTRNACCT;
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
    public void S000_CALL_AIZPQITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-ITM", AICPQITM);
        CEP.TRC(SCCGWA, AICPQITM.RC.RTNCODE);
        if (AICPQITM.RC.RTNCODE != 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_AITITM_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
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
    public void S000_CALL_VTZRPRDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "VT-R-PRDR-MAINTAIN", VTCRPRDR);
    }
    public void S000_CALL_VTZRACCT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "VT-R-ACCT-MAINTAIN", VTCRACCT);
    }
    public void S000_CALL_VTZRVTCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "VT-R-TAX-MAINTAIN", VTCRVTCD);
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
