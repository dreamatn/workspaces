package com.hisun.AI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class AIOT5602 {
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
        CEP.TRC(SCCGWA, "AIOT5602 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB5600_AWA_5600>");
        AIB5600_AWA_5600 = (AIB5600_AWA_5600) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_INQ_VCH_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (AIB5600_AWA_5600.TR_DATE == 0 
            || AIB5600_AWA_5600.TR_DATE == 0X00) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.INPUT_DATE_ERROR;
            S000_ERR_MSG_PROC();
        }
        if (AIB5600_AWA_5600.SET_NO.trim().length() == 0 
            || AIB5600_AWA_5600.SET_NO.charAt(0) == 0X00) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.INPUT_DATE_ERROR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_INQ_VCH_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICSQSUS);
        AICSQSUS.FUNC = '2';
        AICSQSUS.TR_DATE = AIB5600_AWA_5600.TR_DATE;
        AICSQSUS.SET_NO = AIB5600_AWA_5600.SET_NO;
        S00_CALL_AIZSQSUS();
    }
    public void S00_CALL_AIZSQSUS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-S-MAIN-ERR-VCH", AICSQSUS);
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
