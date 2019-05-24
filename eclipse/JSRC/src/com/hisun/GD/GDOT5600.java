package com.hisun.GD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.DC.*;

import java.io.IOException;
import java.sql.SQLException;

public class GDOT5600 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    char WS_TXFUNC = ' ';
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    GDCSADHD GDCSADHD = new GDCSADHD();
    DCCUHLD DCCUHLD = new DCCUHLD();
    SCCGWA SCCGWA;
    GDB5600_AWA_5600 GDB5600_AWA_5600;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "GDOT5600 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "GDB5600_AWA_5600>");
        GDB5600_AWA_5600 = (GDB5600_AWA_5600) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDB5600_AWA_5600.TXFUNC);
        CEP.TRC(SCCGWA, GDB5600_AWA_5600.TXDD_AC);
        CEP.TRC(SCCGWA, GDB5600_AWA_5600.TXSEQ);
        CEP.TRC(SCCGWA, GDB5600_AWA_5600.TXACTYP);
        CEP.TRC(SCCGWA, GDB5600_AWA_5600.TXCI_NM);
        CEP.TRC(SCCGWA, GDB5600_AWA_5600.TXCCY);
        CEP.TRC(SCCGWA, GDB5600_AWA_5600.TXTYP);
        CEP.TRC(SCCGWA, GDB5600_AWA_5600.TXRMK);
        CEP.TRC(SCCGWA, GDB5600_AWA_5600.TXHAMT);
        CEP.TRC(SCCGWA, GDB5600_AWA_5600.TXHLD_NO);
        B100_CHECK_INPUT_DATA();
        B200_TRANS_DATA_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDB5600_AWA_5600.TXFUNC);
        CEP.TRC(SCCGWA, GDB5600_AWA_5600.TXDD_AC);
        CEP.TRC(SCCGWA, GDB5600_AWA_5600.TXSEQ);
        CEP.TRC(SCCGWA, GDB5600_AWA_5600.TXRMK);
        WS_TXFUNC = GDB5600_AWA_5600.TXFUNC;
        if (GDB5600_AWA_5600.TXFUNC == ' ') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_FUNC_TYPE_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (GDB5600_AWA_5600.TXDD_AC.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CR_AC_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (GDB5600_AWA_5600.TXACTYP == ' ') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_TYPE_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (GDB5600_AWA_5600.TXHAMT == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AMT_MST_IPT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (WS_TXFUNC == '2' 
            && GDB5600_AWA_5600.TXHLD_NO.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_HOLD_NO_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (GDB5600_AWA_5600.CNTR_NO.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AMT_MST_IPT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCSADHD);
        GDCSADHD.TXFUNC = GDB5600_AWA_5600.TXFUNC;
        GDCSADHD.TXDD_AC = GDB5600_AWA_5600.TXDD_AC;
        GDCSADHD.TXSEQ = GDB5600_AWA_5600.TXSEQ;
        GDCSADHD.TXCI_NM = GDB5600_AWA_5600.TXCI_NM;
        GDCSADHD.TXCCY = GDB5600_AWA_5600.TXCCY;
        GDCSADHD.CCY_TYP = GDB5600_AWA_5600.TXTYP;
        GDCSADHD.TXHAMT = GDB5600_AWA_5600.TXHAMT;
        GDCSADHD.TXHLD_NO = GDB5600_AWA_5600.TXHLD_NO;
        GDCSADHD.CNTR_NO = GDB5600_AWA_5600.CNTR_NO;
        GDCSADHD.REMARK = GDB5600_AWA_5600.TXRMK;
        S000_CALL_GDZSADHD();
    }
    public void S000_CALL_GDZSADHD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SVR-GDZSADHD", GDCSADHD);
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
