package com.hisun.GD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class GDOT8500 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    GDCSQTPL GDCSQTPL = new GDCSQTPL();
    SCCGWA SCCGWA;
    GDB8500_AWA_8500 GDB8500_AWA_8500;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "GDOT8500 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "GDB8500_AWA_8500>");
        GDB8500_AWA_8500 = (GDB8500_AWA_8500) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_QUERY_BY_RSEQ_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDB8500_AWA_8500.RL_CNT[1-1].RSEQ);
        CEP.TRC(SCCGWA, GDB8500_AWA_8500.RL_CNT[2-1].RSEQ);
        if (GDB8500_AWA_8500.RL_CNT[1-1].RSEQ.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_RSEQ_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_QUERY_BY_RSEQ_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCSQTPL);
        for (WS_I = 1; WS_I <= 25 
            && GDB8500_AWA_8500.RL_CNT[WS_I-1].RSEQ.trim().length() != 0; WS_I += 1) {
            GDCSQTPL.VAL.RSEQ_INF[WS_I-1].RSEQ = GDB8500_AWA_8500.RL_CNT[WS_I-1].RSEQ;
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, GDB8500_AWA_8500.RL_CNT[WS_I-1].RSEQ);
            CEP.TRC(SCCGWA, GDCSQTPL.VAL.RSEQ_INF[WS_I-1].RSEQ);
        }
        S000_CALL_GDZSQTPL();
    }
    public void S000_CALL_GDZSQTPL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SVR-GDZSQTPL", GDCSQTPL);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
