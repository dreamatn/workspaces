package com.hisun.AI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class AIOT5503 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AICSRVS AICSRVS = new AICSRVS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AIB5500_AWA_5500 AIB5500_AWA_5500;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "AIOT5503 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB5500_AWA_5500>");
        AIB5500_AWA_5500 = (AIB5500_AWA_5500) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_BRW_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_BRW_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICSRVS);
        AICSRVS.FUNC = 'B';
        AICSRVS.FLG = 'C';
        AICSRVS.BR = AIB5500_AWA_5500.BR;
        AICSRVS.ITM = AIB5500_AWA_5500.ITM;
        AICSRVS.SEQ = AIB5500_AWA_5500.SEQ;
        AICSRVS.CCY = AIB5500_AWA_5500.CCY;
        AICSRVS.RVS_NO = AIB5500_AWA_5500.RVS_NO;
        AICSRVS.STR_AMT = AIB5500_AWA_5500.STR_AMT;
        AICSRVS.END_AMT = AIB5500_AWA_5500.END_AMT;
        AICSRVS.STR_DATE = AIB5500_AWA_5500.STR_DATE;
        AICSRVS.END_DATE = AIB5500_AWA_5500.END_DATE;
        S00_CALL_AIZSRVS();
    }
    public void S00_CALL_AIZSRVS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-S-MIAN-RVS", AICSRVS);
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
