package com.hisun.AI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class AIOT4514 {
    String CPN_S_IRNG_MAINT = "AI-R-IRNG-MAINT ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AICSIRNG AICSIRNG = new AICSIRNG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AIB4511_AWA_4511 AIB4511_AWA_4511;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "AIOT4514 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB4511_AWA_4511>");
        AIB4511_AWA_4511 = (AIB4511_AWA_4511) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_INQ_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (AIB4511_AWA_4511.ITM_CNRT.trim().length() == 0) {
            CEP.TRC(SCCGWA, "ITEM CONTRACT MUST INPUT");
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_ITM_MST_INPUT;
            WS_FLD_NO = AIB4511_AWA_4511.ITM_CNRT_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_INQ_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICSIRNG);
        AICSIRNG.I_FUNC = 'I';
        AICSIRNG.ITM_CNRT = AIB4511_AWA_4511.ITM_CNRT;
        AICSIRNG.SEQ_NO = AIB4511_AWA_4511.SEQ_NO;
        CEP.TRC(SCCGWA, AIB4511_AWA_4511.ITM_CNRT);
        CEP.TRC(SCCGWA, AIB4511_AWA_4511.SEQ_NO);
        S00_CALL_AIZSIRNG();
    }
    public void S00_CALL_AIZSIRNG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-S-IRNG-MAINT", AICSIRNG);
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
