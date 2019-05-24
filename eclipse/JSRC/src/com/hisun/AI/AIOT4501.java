package com.hisun.AI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class AIOT4501 {
    String CPN_S_IRPT_MAINT = "AI-S-IRPT-MAINT  ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AICSIRPT AICSIRPT = new AICSIRPT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AIB4500_AWA_4500 AIB4500_AWA_4500;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "AIOT4501 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB4500_AWA_4500>");
        AIB4500_AWA_4500 = (AIB4500_AWA_4500) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_ADD_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "IIIIIIIIII");
        CEP.TRC(SCCGWA, AIB4500_AWA_4500.ITM_CNRT);
        CEP.TRC(SCCGWA, AIB4500_AWA_4500.DESC1);
        CEP.TRC(SCCGWA, AIB4500_AWA_4500.DESC2);
        CEP.TRC(SCCGWA, AIB4500_AWA_4500.ITM_LEV);
        CEP.TRC(SCCGWA, AIB4500_AWA_4500.DESC3);
        CEP.TRC(SCCGWA, AIB4500_AWA_4500.UNIT_TYP);
        if (AIB4500_AWA_4500.ITM_CNRT.trim().length() == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.MUST_INPUT;
            WS_FLD_NO = AIB4500_AWA_4500.ITM_CNRT_NO;
            S000_ERR_MSG_PROC();
        }
        if (AIB4500_AWA_4500.DESC1.trim().length() == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.MUST_INPUT;
            WS_FLD_NO = AIB4500_AWA_4500.DESC1_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_ADD_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICSIRPT);
        AICSIRPT.I_FUNC = 'A';
        AICSIRPT.ITM_CNRT = AIB4500_AWA_4500.ITM_CNRT;
        AICSIRPT.DESC1 = AIB4500_AWA_4500.DESC1;
        AICSIRPT.DESC2 = AIB4500_AWA_4500.DESC2;
        AICSIRPT.ITM_LEVEL = AIB4500_AWA_4500.ITM_LEV;
        AICSIRPT.DESC3 = AIB4500_AWA_4500.DESC3;
        AICSIRPT.UNIT_TYPE = AIB4500_AWA_4500.UNIT_TYP;
        CEP.TRC(SCCGWA, AICSIRPT.ITM_CNRT);
        CEP.TRC(SCCGWA, AICSIRPT.DESC1);
        CEP.TRC(SCCGWA, AICSIRPT.DESC2);
        CEP.TRC(SCCGWA, AICSIRPT.ITM_LEVEL);
        CEP.TRC(SCCGWA, AICSIRPT.DESC3);
        CEP.TRC(SCCGWA, AICSIRPT.UNIT_TYPE);
        S00_CALL_AIZSIRPT();
    }
    public void S00_CALL_AIZSIRPT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_IRPT_MAINT, AICSIRPT);
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
