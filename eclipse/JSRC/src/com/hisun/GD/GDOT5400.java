package com.hisun.GD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class GDOT5400 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    GDCSRLSR GDCSRLSR = new GDCSRLSR();
    SCCGWA SCCGWA;
    GDB5400_AWA_5400 GDB5400_AWA_5400;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "GDOT5400 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "GDB5400_AWA_5400>");
        GDB5400_AWA_5400 = (GDB5400_AWA_5400) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDB5400_AWA_5400.TXRSEQ);
        CEP.TRC(SCCGWA, GDB5400_AWA_5400.TXDD_AC);
        CEP.TRC(SCCGWA, GDB5400_AWA_5400.TXSEQ);
        CEP.TRC(SCCGWA, GDB5400_AWA_5400.TXRMK);
        CEP.TRC(SCCGWA, GDB5400_AWA_5400.CI_NM);
        CEP.TRC(SCCGWA, GDB5400_AWA_5400.CCY);
        CEP.TRC(SCCGWA, GDB5400_AWA_5400.TYP);
        CEP.TRC(SCCGWA, GDB5400_AWA_5400.BAL);
        CEP.TRC(SCCGWA, GDB5400_AWA_5400.AC_TYP);
        CEP.TRC(SCCGWA, GDB5400_AWA_5400.CTA_NO);
        CEP.TRC(SCCGWA, GDB5400_AWA_5400.REF_NO);
        B100_CHECK_INPUT_DATA();
        B200_TRANS_DATA_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDB5400_AWA_5400.TXRSEQ);
        CEP.TRC(SCCGWA, GDB5400_AWA_5400.TXDD_AC);
        CEP.TRC(SCCGWA, GDB5400_AWA_5400.TXSEQ);
        CEP.TRC(SCCGWA, GDB5400_AWA_5400.TXRMK);
        if (GDB5400_AWA_5400.TXRSEQ.trim().length() == 0 
            && GDB5400_AWA_5400.CTA_NO.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_RSCL_BOTH_SPACE;
            CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
        }
        WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCSRLSR);
        GDCSRLSR.VAL.RL_SEQ = GDB5400_AWA_5400.TXRSEQ;
        GDCSRLSR.VAL.AC = GDB5400_AWA_5400.TXDD_AC;
        GDCSRLSR.VAL.AC_SEQ = GDB5400_AWA_5400.TXSEQ;
        GDCSRLSR.VAL.RMK = GDB5400_AWA_5400.TXRMK;
        GDCSRLSR.VAL.CI_NM = GDB5400_AWA_5400.CI_NM;
        GDCSRLSR.VAL.CCY = GDB5400_AWA_5400.CCY;
        GDCSRLSR.VAL.CCY_TYP = GDB5400_AWA_5400.TYP;
        GDCSRLSR.VAL.BAL = GDB5400_AWA_5400.BAL;
        GDCSRLSR.VAL.AC_TYP = GDB5400_AWA_5400.AC_TYP;
        GDCSRLSR.VAL.CTA_NO = GDB5400_AWA_5400.CTA_NO;
        GDCSRLSR.VAL.REF_NO = GDB5400_AWA_5400.REF_NO;
        GDCSRLSR.VAL.SYS_NO = GDB5400_AWA_5400.TXSYS_NO;
        S000_CALL_GDZSRLSR();
    }
    public void S000_CALL_GDZSRLSR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SVR-GDZSRLSR", GDCSRLSR);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
