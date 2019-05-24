package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT1900 {
    String CPN_CCY_QUERY = "BP-INQUIRE-CCY";
    String PGM_SCSSCKDT = "SCSSCKDT";
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDCSICCD DDCSICCD = new DDCSICCD();
    SCCGWA SCCGWA;
    DDB1900_AWA_1900 DDB1900_AWA_1900;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DDOT1900 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB1900_AWA_1900>");
        DDB1900_AWA_1900 = (DDB1900_AWA_1900) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B030_TRANS_DATA_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB1900_AWA_1900.CARD_NO);
        CEP.TRC(SCCGWA, DDB1900_AWA_1900.VAL_DT);
        CEP.TRC(SCCGWA, DDB1900_AWA_1900.BAL1);
        CEP.TRC(SCCGWA, DDB1900_AWA_1900.BAL2);
        CEP.TRC(SCCGWA, DDB1900_AWA_1900.TOT1);
        CEP.TRC(SCCGWA, DDB1900_AWA_1900.TOT2);
        if (DDB1900_AWA_1900.CARD_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            if (DDB1900_AWA_1900.CARD_NO.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(DDB1900_AWA_1900.CARD_NO);
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB1900_AWA_1900.VAL_DT == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BAK_DT_INVALID;
            WS_FLD_NO = (short) DDB1900_AWA_1900.VAL_DT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B030_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSICCD);
        DDCSICCD.CARD_NO = DDB1900_AWA_1900.CARD_NO;
        DDCSICCD.VAL_DT = DDB1900_AWA_1900.VAL_DT;
        DDCSICCD.BAL1 = DDB1900_AWA_1900.BAL1;
        DDCSICCD.BAL2 = DDB1900_AWA_1900.BAL2;
        DDCSICCD.TOT1 = DDB1900_AWA_1900.TOT1;
        DDCSICCD.TOT2 = DDB1900_AWA_1900.TOT2;
        S000_CALL_DDZSICCD();
    }
    public void S000_CALL_DDZSICCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-DDZSICCD", DDCSICCD);
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
