package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT1620 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSFDOR DDCSFDOR = new DDCSFDOR();
    SCCGWA SCCGWA;
    DDB1620_AWA_1620 DDB1620_AWA_1620;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DDOT1620 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB1620_AWA_1620>");
        DDB1620_AWA_1620 = (DDB1620_AWA_1620) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_TRANS_DATA_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB1620_AWA_1620.AC);
        CEP.TRC(SCCGWA, DDB1620_AWA_1620.CCY);
        CEP.TRC(SCCGWA, DDB1620_AWA_1620.CCY_TYPE);
        if (DDB1620_AWA_1620.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            WS_FLD_NO = DDB1620_AWA_1620.AC_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB1620_AWA_1620.CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            WS_FLD_NO = DDB1620_AWA_1620.CCY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB1620_AWA_1620.CCY_TYPE != '1' 
            && DDB1620_AWA_1620.CCY_TYPE != '2') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_TYP_INVALID;
            WS_FLD_NO = DDB1620_AWA_1620.CCY_TYPE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSFDOR);
        DDCSFDOR.DATA.AC = DDB1620_AWA_1620.AC;
        DDCSFDOR.DATA.CCY = DDB1620_AWA_1620.CCY;
        DDCSFDOR.DATA.CCY_TYPE = DDB1620_AWA_1620.CCY_TYPE;
        DDCSFDOR.DATA.CI_NO = DDB1620_AWA_1620.CI_NO;
        DDCSFDOR.DATA.CUS_NM = DDB1620_AWA_1620.CUS_NM;
        DDCSFDOR.DATA.CUS_BAL = DDB1620_AWA_1620.CUS_BAL;
        DDCSFDOR.DATA.INT = DDB1620_AWA_1620.INT;
        DDCSFDOR.DATA.OPEN_DT = DDB1620_AWA_1620.OPEN_DT;
        DDCSFDOR.DATA.AC_OIC_NO = DDB1620_AWA_1620.AC_OIC_N;
        DDCSFDOR.DATA.REMARKS = DDB1620_AWA_1620.REMARKS;
        S000_CALL_DDZSFDOR();
    }
    public void S000_CALL_DDZSFDOR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-DDZSFDOR", DDCSFDOR);
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
