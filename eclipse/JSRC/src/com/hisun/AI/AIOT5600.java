package com.hisun.AI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class AIOT5600 {
    String CPN_S_MAIN_ERR_VCH = "AI-S-MAIN-ERR-VCH";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AICSQSUS AICSQSUS = new AICSQSUS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AIB5600_AWA_5600 AIB5600_AWA_5600;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "AIOT5600 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB5600_AWA_5600>");
        AIB5600_AWA_5600 = (AIB5600_AWA_5600) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        CEP.TRC(SCCGWA, AIB5600_AWA_5600.TR_DATE);
        CEP.TRC(SCCGWA, AIB5600_AWA_5600.RVS_NO);
        CEP.TRC(SCCGWA, AIB5600_AWA_5600.BR);
        CEP.TRC(SCCGWA, AIB5600_AWA_5600.STS);
        CEP.TRC(SCCGWA, AIB5600_AWA_5600.SET_NO);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_BRW_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (AIB5600_AWA_5600.TR_DATE > SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.INPUT_DATE_ERROR;
            WS_FLD_NO = AIB5600_AWA_5600.TR_DATE_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_BRW_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICSQSUS);
        AICSQSUS.FUNC = '1';
        CEP.TRC(SCCGWA, AIB5600_AWA_5600.TR_DATE);
        CEP.TRC(SCCGWA, AIB5600_AWA_5600.RVS_NO);
        CEP.TRC(SCCGWA, AIB5600_AWA_5600.BR);
        CEP.TRC(SCCGWA, AIB5600_AWA_5600.STS);
        CEP.TRC(SCCGWA, AIB5600_AWA_5600.SET_NO);
        AICSQSUS.TR_DATE = AIB5600_AWA_5600.TR_DATE;
        AICSQSUS.SET_NO = AIB5600_AWA_5600.SET_NO;
        AICSQSUS.RVS_NO = AIB5600_AWA_5600.RVS_NO;
        AICSQSUS.BR = AIB5600_AWA_5600.BR;
        AICSQSUS.STS = AIB5600_AWA_5600.STS;
        S00_CALL_AIZSQSUS();
        CEP.TRC(SCCGWA, AICSQSUS.TR_DATE);
    }
    public void S00_CALL_AIZSQSUS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_MAIN_ERR_VCH, AICSQSUS);
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
