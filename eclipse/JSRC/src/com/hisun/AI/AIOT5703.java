package com.hisun.AI;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class AIOT5703 {
    String CPN_S_MIB_MAIN = "AI-S-MAIN-MIB  ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AICSCMIB AICSCMIB = new AICSCMIB();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AIB5700_AWA_5700 AIB5700_AWA_5700;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "AIOT5703 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB5700_AWA_5700>");
        AIB5700_AWA_5700 = (AIB5700_AWA_5700) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_STP_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (AIB5700_AWA_5700.GL_BOOK.trim().length() == 0 
            || AIB5700_AWA_5700.BR == 0 
            || AIB5700_AWA_5700.ITM.trim().length() == 0 
            || AIB5700_AWA_5700.SEQ == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_STP_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICSCMIB);
        AICSCMIB.FUNC = 'S';
        AICSCMIB.GL_BOOK = AIB5700_AWA_5700.GL_BOOK;
        AICSCMIB.BR = AIB5700_AWA_5700.BR;
        AICSCMIB.ITM = AIB5700_AWA_5700.ITM;
        AICSCMIB.SEQ = AIB5700_AWA_5700.SEQ;
        CEP.TRC(SCCGWA, AICSCMIB.BR);
        CEP.TRC(SCCGWA, AICSCMIB.ITM);
        CEP.TRC(SCCGWA, AICSCMIB.SEQ);
        S000_CALL_AIZSCMIB();
    }
    public void S000_CALL_AIZSCMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-S-MAIN-CMIB", AICSCMIB);
        if (AICSCMIB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICSCMIB.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
