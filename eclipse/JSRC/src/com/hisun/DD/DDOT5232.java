package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5232 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSBFJM DDCSBFJM = new DDCSBFJM();
    SCCGWA SCCGWA;
    DDB5232_AWA_5232 DDB5232_AWA_5232;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DDOT5232 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5232_AWA_5232>");
        DDB5232_AWA_5232 = (DDB5232_AWA_5232) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "11111");
        B100_CHECK_INPUT_DATA();
        CEP.TRC(SCCGWA, "2222");
        B200_TRANS_DATA_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "SFSAFASF");
        CEP.TRC(SCCGWA, DDB5232_AWA_5232.FUNC);
        if (DDB5232_AWA_5232.FUNC == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_4011;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSBFJM);
        DDCSBFJM.FUNC = DDB5232_AWA_5232.FUNC;
        DDCSBFJM.TYP = DDB5232_AWA_5232.TYP;
        DDCSBFJM.CTL_TYPE = DDB5232_AWA_5232.CTL_TYPE;
        DDCSBFJM.AC_TYPE = DDB5232_AWA_5232.AC_TYPE;
        DDCSBFJM.BANK_NO = DDB5232_AWA_5232.BANK_NO;
        DDCSBFJM.CI_NO = DDB5232_AWA_5232.CI_NO;
        DDCSBFJM.OTH_AC = DDB5232_AWA_5232.OTH_AC;
        DDCSBFJM.AC_KND = DDB5232_AWA_5232.AC_KND;
        DDCSBFJM.S_LMT = DDB5232_AWA_5232.S_LMT;
        DDCSBFJM.DAMT_LMT = DDB5232_AWA_5232.DAMT_LMT;
        DDCSBFJM.DCNT_LMT = DDB5232_AWA_5232.DCNT_LMT;
        DDCSBFJM.MAMT_LMT = DDB5232_AWA_5232.MAMT_LMT;
        DDCSBFJM.MCNT_LMT = DDB5232_AWA_5232.MCNT_LMT;
        DDCSBFJM.BASE_LMT = DDB5232_AWA_5232.BASE_LMT;
        S000_CALL_DDZSBFJM();
    }
    public void S000_CALL_DDZSBFJM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-BFJM", DDCSBFJM);
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
