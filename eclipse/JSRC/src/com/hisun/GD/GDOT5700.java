package com.hisun.GD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class GDOT5700 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    GDCSPCMA GDCSPCMA = new GDCSPCMA();
    SCCGWA SCCGWA;
    GDB5700_AWA_5700 GDB5700_AWA_5700;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "GDOT5700 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "GDB5700_AWA_5700>");
        GDB5700_AWA_5700 = (GDB5700_AWA_5700) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDB5700_AWA_5700.TXFUNC);
        CEP.TRC(SCCGWA, GDB5700_AWA_5700.DD_AC);
        CEP.TRC(SCCGWA, GDB5700_AWA_5700.AC_TYP);
        CEP.TRC(SCCGWA, GDB5700_AWA_5700.CI_NM);
        CEP.TRC(SCCGWA, GDB5700_AWA_5700.SEQ);
        CEP.TRC(SCCGWA, GDB5700_AWA_5700.CCY);
        CEP.TRC(SCCGWA, GDB5700_AWA_5700.CCY_TYP);
        CEP.TRC(SCCGWA, GDB5700_AWA_5700.CNTR_NO);
        CEP.TRC(SCCGWA, GDB5700_AWA_5700.OLD_PCT);
        CEP.TRC(SCCGWA, GDB5700_AWA_5700.NEW_PCT);
        CEP.TRC(SCCGWA, GDB5700_AWA_5700.RMK);
        CEP.TRC(SCCGWA, GDB5700_AWA_5700.TX_SAMT);
        B100_CHECK_INPUT_DATA();
        B200_TRANS_DATA_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDB5700_AWA_5700);
        CEP.TRC(SCCGWA, GDB5700_AWA_5700.TXFUNC);
        CEP.TRC(SCCGWA, GDB5700_AWA_5700.DD_AC);
        CEP.TRC(SCCGWA, GDB5700_AWA_5700.AC_TYP);
        CEP.TRC(SCCGWA, GDB5700_AWA_5700.TX_SAMT);
        if (GDB5700_AWA_5700.TXFUNC == ' ') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_FUNC_TYPE_M_INPUT;
            CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
        }
        if (GDB5700_AWA_5700.DD_AC.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (GDB5700_AWA_5700.AC_TYP == ' ') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_TYPE_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (GDB5700_AWA_5700.TX_SAMT == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_ADD_AMT_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (GDB5700_AWA_5700.CNTR_NO.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CTA_NO_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (GDB5700_AWA_5700.NEW_PCT == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_NEW_PCT_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCSPCMA);
        GDCSPCMA.VAL.AC = GDB5700_AWA_5700.DD_AC;
        GDCSPCMA.VAL.AC_TYP = GDB5700_AWA_5700.AC_TYP;
        GDCSPCMA.VAL.AC_NM = GDB5700_AWA_5700.CI_NM;
        GDCSPCMA.VAL.AC_SEQ = GDB5700_AWA_5700.SEQ;
        GDCSPCMA.VAL.CCY = GDB5700_AWA_5700.CCY;
        GDCSPCMA.VAL.CCY_TYP = GDB5700_AWA_5700.CCY_TYP;
        GDCSPCMA.VAL.CNTR_NO = GDB5700_AWA_5700.CNTR_NO;
        GDCSPCMA.VAL.OLD_PCT = GDB5700_AWA_5700.OLD_PCT;
        GDCSPCMA.VAL.NEW_PCT = GDB5700_AWA_5700.NEW_PCT;
        GDCSPCMA.VAL.TX_SAMT = GDB5700_AWA_5700.TX_SAMT;
        GDCSPCMA.VAL.RMK = GDB5700_AWA_5700.RMK;
        S000_CALL_GDZSPCMA();
    }
    public void S000_CALL_GDZSPCMA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SVR-GDZSPCMA", GDCSPCMA);
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
