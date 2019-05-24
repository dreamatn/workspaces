package com.hisun.AI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPNHIS;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class AIZSIRNG {
    boolean pgmRtn = false;
    String CPN_REC_NHIS = "BP-REC-NHIS ";
    String K_HIS_REMARKS = "ITEM RANGE INFORMATION MAINTAIN";
    String K_CPY_BPRFBAS = "AICHIRNG";
    String WS_ERR_MSG = " ";
    AIZSIRNG_WS_IRNG_INFO WS_IRNG_INFO = new AIZSIRNG_WS_IRNG_INFO();
    AIZSIRNG_WS_OUT_DATA WS_OUT_DATA = new AIZSIRNG_WS_OUT_DATA();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AIRCOAP AIRCOAP = new AIRCOAP();
    AICHIRNG AICNIRNG = new AICHIRNG();
    AICHIRNG AICOIRNG = new AICHIRNG();
    AICPQITM AICPQITM = new AICPQITM();
    AICRIRNG AICRIRNG = new AICRIRNG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    AICSIRNG AICSIRNG;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, AICSIRNG AICSIRNG) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICSIRNG = AICSIRNG;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZSIRNG return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, AICNIRNG);
        IBS.init(SCCGWA, AICOIRNG);
        IBS.init(SCCGWA, AIRCOAP);
        IBS.init(SCCGWA, BPCPNHIS);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (AICSIRNG.I_FUNC == 'I') {
            B020_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (AICSIRNG.I_FUNC == 'A') {
            B030_CREATE_PROCESS();
            if (pgmRtn) return;
        } else if (AICSIRNG.I_FUNC == 'U') {
            B040_MODIFY_PROCESS();
            if (pgmRtn) return;
        } else if (AICSIRNG.I_FUNC == 'D') {
            B050_DELETE_PROCESS();
            if (pgmRtn) return;
        } else if (AICSIRNG.I_FUNC == 'B') {
            B060_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (AICSIRNG.I_FUNC != 'B') {
            B070_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (AICSIRNG.I_FUNC == 'A' 
            || AICSIRNG.I_FUNC == 'U') {
            if (AICSIRNG.ITM_CNRT.trim().length() == 0) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_ITM_MST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (AICSIRNG.COA_FR.trim().length() == 0) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_ITM_MST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (AICSIRNG.COA_TO.trim().length() == 0) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_ITM_MST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (AICSIRNG.CCY_FLG == ' ') {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_ITM_MST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (AICSIRNG.COA_FR.compareTo(AICSIRNG.COA_TO) > 0) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_COA_FR_GT_COA_TO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (AICSIRNG.I_FUNC == 'I' 
            || AICSIRNG.I_FUNC == 'D') {
            if (AICSIRNG.ITM_CNRT.trim().length() == 0) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_ITM_MST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRCOAP);
        IBS.init(SCCGWA, AICRIRNG);
        AICRIRNG.INFO.FUNC = 'I';
        AIRCOAP.KEY.GL_ITEM = AICSIRNG.ITM_CNRT;
        AIRCOAP.KEY.GL_SEQ = AICSIRNG.SEQ_NO;
        CEP.TRC(SCCGWA, AICSIRNG.SEQ_NO);
        CEP.TRC(SCCGWA, AIRCOAP.KEY.GL_SEQ);
        AICRIRNG.INFO.POINTER = AIRCOAP;
        AICRIRNG.LEN = 82;
        S000_CALL_AIZRIRNG();
        if (pgmRtn) return;
        if (AICRIRNG.RETURN_INFO == 'N') {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_IRNG_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        AICSIRNG.ITM_CNRT = AIRCOAP.KEY.GL_ITEM;
        AICSIRNG.SEQ_NO = AIRCOAP.KEY.GL_SEQ;
        AICSIRNG.COA_FR = AIRCOAP.KEY.COA_FR;
        AICSIRNG.COA_TO = AIRCOAP.KEY.COA_TO;
        AICSIRNG.AC_CTR = AIRCOAP.ACCT_CTR;
        AICSIRNG.MLTI = AIRCOAP.MULTIPLR;
        if (AIRCOAP.CCY.equalsIgnoreCase("*")) {
            AICSIRNG.CCY_FLG = 'Y';
            AICSIRNG.CCY = " ";
        } else {
            AICSIRNG.CCY_FLG = 'N';
            AICSIRNG.CCY = AIRCOAP.CCY;
        }
        AICSIRNG.COA_LVL = AIRCOAP.COL_IND;
        AICSIRNG.DR_CR = AIRCOAP.DRCR_IND;
        CEP.TRC(SCCGWA, AICSIRNG.ITM_CNRT);
        CEP.TRC(SCCGWA, AICSIRNG.SEQ_NO);
        CEP.TRC(SCCGWA, AICSIRNG.COA_FR);
        CEP.TRC(SCCGWA, AICSIRNG.COA_TO);
        CEP.TRC(SCCGWA, AICSIRNG.AC_CTR);
        CEP.TRC(SCCGWA, AICSIRNG.MLTI);
        CEP.TRC(SCCGWA, AICSIRNG.CCY_FLG);
        CEP.TRC(SCCGWA, AICSIRNG.CCY);
        CEP.TRC(SCCGWA, AICSIRNG.CCY_FLG);
        CEP.TRC(SCCGWA, AICSIRNG.CCY);
        CEP.TRC(SCCGWA, AICSIRNG.CCY);
        CEP.TRC(SCCGWA, AICSIRNG.COA_LVL);
        CEP.TRC(SCCGWA, AICSIRNG.DR_CR);
    }
    public void B030_CREATE_PROCESS() throws IOException,SQLException,Exception {
        B031_CHECK_INPUT_AGAIN();
        if (pgmRtn) return;
        IBS.init(SCCGWA, AIRCOAP);
        IBS.init(SCCGWA, AICRIRNG);
        AICRIRNG.INFO.FUNC = 'A';
        R000_TRANS_DATA();
        if (pgmRtn) return;
        AICRIRNG.LEN = 82;
        AICRIRNG.INFO.POINTER = AIRCOAP;
        S000_CALL_AIZRIRNG();
        if (pgmRtn) return;
        B020_01_HISTORY_RECORD();
        if (pgmRtn) return;
    }
    public void B031_CHECK_INPUT_AGAIN() throws IOException,SQLException,Exception {
        if (AICSIRNG.ITM_CNRT.trim().length() == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_ITM_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (AICSIRNG.COA_FR.trim().length() == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_ITM_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (AICSIRNG.COA_TO.trim().length() == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_ITM_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (AICSIRNG.CCY_FLG == ' ') {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_ITM_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (AICSIRNG.COA_FR.compareTo(AICSIRNG.COA_TO) > 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_ITM_MST_INPUT;
        }
    }
    public void B040_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        B031_CHECK_INPUT_AGAIN();
        if (pgmRtn) return;
        IBS.init(SCCGWA, AIRCOAP);
        IBS.init(SCCGWA, AIRCOAP);
        IBS.init(SCCGWA, AICOIRNG);
        IBS.init(SCCGWA, AICNIRNG);
        IBS.init(SCCGWA, AICRIRNG);
        AICRIRNG.INFO.FUNC = 'R';
        AIRCOAP.KEY.GL_ITEM = AICSIRNG.ITM_CNRT;
        AIRCOAP.KEY.GL_SEQ = AICSIRNG.SEQ_NO;
        AICRIRNG.LEN = 82;
        AICRIRNG.INFO.POINTER = AIRCOAP;
        S000_CALL_AIZRIRNG();
        if (pgmRtn) return;
        AICOIRNG.ITM_CNRT = AIRCOAP.KEY.GL_ITEM;
        AICOIRNG.GL_SEQ = AIRCOAP.KEY.GL_SEQ;
        AICOIRNG.COA_FR = AIRCOAP.KEY.COA_FR;
        AICOIRNG.COA_TO = AIRCOAP.KEY.COA_TO;
        AICOIRNG.ACCT_CTR = AIRCOAP.ACCT_CTR;
        AICOIRNG.MULTIPLR = AIRCOAP.MULTIPLR;
        if (AIRCOAP.CCY.equalsIgnoreCase("*")) {
            AICOIRNG.CCY = " ";
        } else {
            AICOIRNG.CCY = AIRCOAP.CCY;
        }
        AICOIRNG.COL_IND = AIRCOAP.COL_IND;
        AICOIRNG.DRCR_IND = AIRCOAP.DRCR_IND;
        AICOIRNG.UPDTBL_DATE = AIRCOAP.UPDTBL_DATE;
        R000_TRANS_DATA();
        if (pgmRtn) return;
        B030_00_GET_INFO();
        if (pgmRtn) return;
