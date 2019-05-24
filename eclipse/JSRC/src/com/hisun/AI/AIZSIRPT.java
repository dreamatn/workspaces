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

public class AIZSIRPT {
    boolean pgmRtn = false;
    String CPN_S_IRPT_MAINT = "AI-S-IRPT-MAINT";
    String WS_ERR_MSG = " ";
    AIZSIRPT_WS_IRPT_INFO WS_IRPT_INFO = new AIZSIRPT_WS_IRPT_INFO();
    AIZSIRPT_WS_OUT_DATA WS_OUT_DATA = new AIZSIRPT_WS_OUT_DATA();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AICHIRPT AICNIRPT = new AICHIRPT();
    AICHIRPT AICOIRPT = new AICHIRPT();
    AIRITMP AIRITMP = new AIRITMP();
    AICRIRPT AICRIRPT = new AICRIRPT();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AICSIRPT AICSIRPT;
    public void MP(SCCGWA SCCGWA, AICSIRPT AICSIRPT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICSIRPT = AICSIRPT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZSIRPT return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICNIRPT);
        IBS.init(SCCGWA, AICOIRPT);
        IBS.init(SCCGWA, AIRITMP);
        IBS.init(SCCGWA, BPCPNHIS);
        CEP.TRC(SCCGWA, AICSIRPT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (AICSIRPT.I_FUNC == 'I') {
            B020_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (AICSIRPT.I_FUNC == 'A') {
            B030_CREATE_PROCESS();
            if (pgmRtn) return;
        } else if (AICSIRPT.I_FUNC == 'U') {
            B040_MODIFY_PROCESS();
            if (pgmRtn) return;
        } else if (AICSIRPT.I_FUNC == 'D') {
            B050_DELETE_PROCESS();
            if (pgmRtn) return;
        } else if (AICSIRPT.I_FUNC == 'B') {
            B060_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = AICMSG_ERROR_MSG.MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (AICSIRPT.I_FUNC != 'B') {
            B070_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (AICSIRPT.I_FUNC != 'B' 
            && AICSIRPT.ITM_CNRT.trim().length() == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRITMP);
        IBS.init(SCCGWA, AICRIRPT);
        AICRIRPT.INFO.FUNC = 'I';
        AIRITMP.KEY.GL_ITEM = AICSIRPT.ITM_CNRT;
        AICRIRPT.INFO.POINTER = AIRITMP;
        AICRIRPT.LEN = 369;
        S000_CALL_AIZRIRPT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AIRITMP);
        if (AICRIRPT.RETURN_INFO == 'N') {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_IRPT_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_CREATE_PROCESS() throws IOException,SQLException,Exception {
        B031_CHECK_INPUT_AGAIN();
        if (pgmRtn) return;
        IBS.init(SCCGWA, AIRITMP);
        IBS.init(SCCGWA, AICRIRPT);
        AICRIRPT.INFO.FUNC = 'A';
        R000_TRANS_DATA();
        if (pgmRtn) return;
        AICRIRPT.LEN = 369;
        AICRIRPT.INFO.POINTER = AIRITMP;
        S000_CALL_AIZRIRPT();
        if (pgmRtn) return;
        B000_01_HISTORY_RECORD();
        if (pgmRtn) return;
    }
    public void B000_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        IBS.init(SCCGWA, AICOIRPT);
        IBS.init(SCCGWA, AICNIRPT);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = "ITEM REPORT PARM";
        BPCPNHIS.INFO.FMT_ID = "AICHIRPT";
        AICNIRPT.ITM_CNRT = AIRITMP.KEY.GL_ITEM;
        AICNIRPT.DESC1 = AIRITMP.DESC1;
        AICNIRPT.DESC2 = AIRITMP.DESC2;
        AICNIRPT.ITM_LEVEL = AIRITMP.CLSFCTION;
        AICNIRPT.DESC3 = AIRITMP.DESC3;
        CEP.TRC(SCCGWA, AICNIRPT.DESC3);
        AICNIRPT.UNIT_TYPE = AIRITMP.UNIT_TYP;
        AICNIRPT.UPDTBL_DATE = AIRITMP.UPDTBL_DATE;
        BPCPNHIS.INFO.FMT_ID_LEN = 163;
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.OLD_DAT_PT = AICOIRPT;
        BPCPNHIS.INFO.NEW_DAT_PT = AICNIRPT;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B031_CHECK_INPUT_AGAIN() throws IOException,SQLException,Exception {
        if (AICSIRPT.ITM_CNRT.trim().length() == 0) {
            CEP.TRC(SCCGWA, "ITEM CONTRACT MUST INPUT");
            WS_ERR_MSG = AICMSG_ERROR_MSG.MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B040_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        B031_CHECK_INPUT_AGAIN();
        if (pgmRtn) return;
        IBS.init(SCCGWA, AIRITMP);
        IBS.init(SCCGWA, AICOIRPT);
        IBS.init(SCCGWA, AICNIRPT);
        IBS.init(SCCGWA, AICRIRPT);
        AICRIRPT.INFO.FUNC = 'R';
        AIRITMP.KEY.GL_ITEM = AICSIRPT.ITM_CNRT;
        AICRIRPT.LEN = 369;
        AICRIRPT.INFO.POINTER = AIRITMP;
        S000_CALL_AIZRIRPT();
        if (pgmRtn) return;
        AICOIRPT.ITM_CNRT = AIRITMP.KEY.GL_ITEM;
        AICOIRPT.DESC1 = AIRITMP.DESC1;
        AICOIRPT.DESC2 = AIRITMP.DESC2;
        AICOIRPT.ITM_LEVEL = AIRITMP.CLSFCTION;
        AICOIRPT.DESC3 = AIRITMP.DESC3;
        AICOIRPT.UNIT_TYPE = AIRITMP.UNIT_TYP;
        AICOIRPT.UPDTBL_DATE = AIRITMP.UPDTBL_DATE;
        CEP.TRC(SCCGWA, AIRITMP.DESC3);
        R000_TRANS_DATA();
        if (pgmRtn) return;
        B030_00_GET_INFO();
        if (pgmRtn) return;
