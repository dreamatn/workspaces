package com.hisun.AI;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class AIOT5716 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    AIOT5716_WS_OUTPUT WS_OUTPUT = new AIOT5716_WS_OUTPUT();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    AICPQIA AICPQIA = new AICPQIA();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AIB9080_AWA_9080 AIB9080_AWA_9080;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "AIOT5716 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB9080_AWA_9080>");
        AIB9080_AWA_9080 = (AIB9080_AWA_9080) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_QUERY_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIB9080_AWA_9080.AC_TYP);
        CEP.TRC(SCCGWA, AIB9080_AWA_9080.BUSI_KND);
        CEP.TRC(SCCGWA, AIB9080_AWA_9080.JY_TYP);
        CEP.TRC(SCCGWA, AIB9080_AWA_9080.BR);
        CEP.TRC(SCCGWA, AIB9080_AWA_9080.CCY);
        if (AIB9080_AWA_9080.AC_TYP.trim().length() == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_ITMP_GL_ITEM_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (AIB9080_AWA_9080.BUSI_KND.trim().length() == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_PAI7_CODE_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (AIB9080_AWA_9080.BR == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_BR_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (AIB9080_AWA_9080.CCY.trim().length() == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_CUR_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (AIB9080_AWA_9080.JY_TYP.trim().length() == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_QUERY_REC_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIB9080_AWA_9080.AC_TYP);
        if (AIB9080_AWA_9080.AC_TYP.equalsIgnoreCase("5")) {
            IBS.init(SCCGWA, AICPQIA);
            AICPQIA.CD.AC_TYP = AIB9080_AWA_9080.AC_TYP;
            AICPQIA.CD.BUSI_KND = AIB9080_AWA_9080.BUSI_KND;
            AICPQIA.CD.JY_TYP = AIB9080_AWA_9080.JY_TYP;
            AICPQIA.BR = AIB9080_AWA_9080.BR;
            AICPQIA.CCY = AIB9080_AWA_9080.CCY;
            AICPQIA.SIGN = 'C';
            S000_CALL_AIZPQIA();
            R000_DATA_OUTPUT();
        } else {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AC_TYPE_MUST_BE5;
        }
    }
    public void S000_CALL_AIZPQIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-IA", AICPQIA);
        CEP.TRC(SCCGWA, AICPQIA.RC);
        if (AICPQIA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPQIA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void R000_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT);
        WS_OUTPUT.WS_AC = AICPQIA.AC;
        WS_OUTPUT.WS_OUT_CHS_NM = AICPQIA.CHS_NM;
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_AC);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_CHS_NM);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "AIX01";
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 193;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
