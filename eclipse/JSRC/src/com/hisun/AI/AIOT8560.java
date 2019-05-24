package com.hisun.AI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class AIOT8560 {
    String CPN_S_INQ_ITM_DC_TAMT = "AI-S-INQ-ITM-DC-TAMT";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AICSITDC AICSITDC = new AICSITDC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AIB8560_AWA_8560 AIB8560_AWA_8560;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "AIOT8560 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB8560_AWA_8560>");
        AIB8560_AWA_8560 = (AIB8560_AWA_8560) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_INQ_ITM_TAMT_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (AIB8560_AWA_8560.STR_DTE == 0 
            && AIB8560_AWA_8560.END_DTE == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.INPUT_DATE_ERROR;
            WS_FLD_NO = AIB8560_AWA_8560.STR_DTE_NO;
            S000_ERR_MSG_PROC();
        }
        if (AIB8560_AWA_8560.STR_BR == 0 
            && AIB8560_AWA_8560.END_BR == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_BR_MUST_INPUT;
            WS_FLD_NO = AIB8560_AWA_8560.STR_DTE_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_INQ_ITM_TAMT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICSITDC);
        AICSITDC.BOOK_FLG = AIB8560_AWA_8560.BOOK_FLG;
        AICSITDC.ITM_NO = AIB8560_AWA_8560.ITM_NO;
        if (AIB8560_AWA_8560.CCY.equalsIgnoreCase("999")) {
            AICSITDC.CCY = " ";
        } else {
            AICSITDC.CCY = AIB8560_AWA_8560.CCY;
        }
        AICSITDC.STR_BR = AIB8560_AWA_8560.STR_BR;
        AICSITDC.END_BR = AIB8560_AWA_8560.END_BR;
        AICSITDC.STR_DTE = AIB8560_AWA_8560.STR_DTE;
        AICSITDC.END_DTE = AIB8560_AWA_8560.END_DTE;
        S000_CALL_AIZSITDC();
    }
    public void S000_CALL_AIZSITDC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_INQ_ITM_DC_TAMT, AICSITDC);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
