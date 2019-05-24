package com.hisun.PN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class PNOT1900 {
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "PN190";
    String WS_ERR_MSG = " ";
    PNCMSG_ERROR_MSG PNCMSG_ERROR_MSG = new PNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    PNCSQUE PNCSQUE = new PNCSQUE();
    PNCOQUE PNCOQUE = new PNCOQUE();
    SCCGWA SCCGWA;
    PNB1900_AWA_1900 PNB1900_AWA_1900;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "PNOT1900 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "PNB1900_AWA_1900>");
        PNB1900_AWA_1900 = (PNB1900_AWA_1900) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_INPUT_CHK_PROC();
        B200_INF_NOTE_PROC();
    }
    public void B100_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        if (PNB1900_AWA_1900.BILL_KND.trim().length() == 0) {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_KND_MUST_IPT);
        }
        if (PNB1900_AWA_1900.BILL_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_NO_MUST_IPT);
        }
        if (PNB1900_AWA_1900.ISS_DATE == 0) {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_ISS_DT_MUST_IN);
        }
        if (PNB1900_AWA_1900.ENCRY_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_ENCRY_NOT_IPT);
        }
        if (PNB1900_AWA_1900.AMT == 0) {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_AMT_MUST_IPT);
        }
    }
    public void B200_INF_NOTE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNCSQUE);
        PNCSQUE.KND = PNB1900_AWA_1900.BILL_KND;
        PNCSQUE.CC_NO = PNB1900_AWA_1900.BILL_NO;
        PNCSQUE.ISS_DATE = PNB1900_AWA_1900.ISS_DATE;
        PNCSQUE.ENCRY_NO = PNB1900_AWA_1900.ENCRY_NO;
        PNCSQUE.AMT = PNB1900_AWA_1900.AMT;
        CEP.TRC(SCCGWA, PNCSQUE.KND);
        CEP.TRC(SCCGWA, PNCSQUE.CC_NO);
        CEP.TRC(SCCGWA, PNCSQUE.ISS_DATE);
        CEP.TRC(SCCGWA, PNCSQUE.ENCRY_NO);
        CEP.TRC(SCCGWA, PNCSQUE.AMT);
        S000_CALL_PNZSQUE();
    }
    public void S000_CALL_PNZSQUE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "PN-PNZSQUE", PNCSQUE);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
