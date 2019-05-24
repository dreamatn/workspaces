package com.hisun.PN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class PNOT9110 {
    String CPN_U_PNZSMBCC = "PN-S-INF-NOTE";
    char K_ERROR = 'E';
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    PNCMSG_ERROR_MSG PNCMSG_ERROR_MSG = new PNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    PNCSMBCC PNCSMBCC = new PNCSMBCC();
    PNCOCISS PNCOCISS = new PNCOCISS();
    SCCGWA SCCGWA;
    PNB9100_AWA_9100 PNB9100_AWA_9100;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "PNOT9110 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "PNB9100_AWA_9100>");
        PNB9100_AWA_9100 = (PNB9100_AWA_9100) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, PNB9100_AWA_9100.BILL_KND);
        B100_INPUT_CHK_PROC();
        B200_INF_NOTE_PROC();
    }
    public void B100_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        if (PNB9100_AWA_9100.BILL_KND.trim().length() == 0) {
            WS_ERR_MSG = PNCMSG_ERROR_MSG.PN_KND_MUST_IPT;
            CEP.ERRC(SCCGWA, WS_ERR_MSG, PNB9100_AWA_9100.BILL_KND_NO);
        }
        if (PNB9100_AWA_9100.BILL_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "1111111111111111111");
            WS_ERR_MSG = PNCMSG_ERROR_MSG.PN_CCNO_MUST_INPT;
            CEP.ERRC(SCCGWA, WS_ERR_MSG, PNB9100_AWA_9100.BILL_NO_NO);
        }
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == K_ERROR 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE != 0) {
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void B200_INF_NOTE_PROC() throws IOException,SQLException,Exception {
        B220_INF_PNT_PROC();
    }
    public void B220_INF_PNT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNCSMBCC);
        PNCSMBCC.FUNC = 'I';
        PNCSMBCC.DATA.KEY.CC_NO = PNB9100_AWA_9100.BILL_NO;
        if (PNB9100_AWA_9100.BILL_KND.equalsIgnoreCase("02")) {
            PNCSMBCC.DATA.KEY.KND = "C00001";
        } else {
            PNCSMBCC.DATA.KEY.KND = PNB9100_AWA_9100.BILL_KND;
        }
        S000_CALL_PNZSMBCC();
    }
    public void S000_CALL_PNZSMBCC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_PNZSMBCC, PNCSMBCC);
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
