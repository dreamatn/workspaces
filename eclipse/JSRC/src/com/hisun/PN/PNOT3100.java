package com.hisun.PN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class PNOT3100 {
    char K_ERROR = 'E';
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_TABLE_FLG = ' ';
    PNCMSG_ERROR_MSG PNCMSG_ERROR_MSG = new PNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    PNCGHPAY PNCGHPAY = new PNCGHPAY();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    PNB3100_AWA_3100 PNB3100_AWA_3100;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "PNOT3100 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "PNB3100_AWA_3100>");
        PNB3100_AWA_3100 = (PNB3100_AWA_3100) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_INPUT_CHK_PROC();
        B200_DRAFT_PAY();
    }
    public void B100_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        if (PNB3100_AWA_3100.STL_AMT == 0) {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_DPAY_PAY_MONEY_NULL, PNB3100_AWA_3100.STL_AMT_NO);
        }
        if (PNB3100_AWA_3100.DRFT_NO.trim().length() == 0) {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_DRFT_NO_NULL, PNB3100_AWA_3100.DRFT_NO_NO);
        }
        if (PNB3100_AWA_3100.KND != '1') {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_KND_MUST_IPT, PNB3100_AWA_3100.KND_NO);
        }
        if (PNB3100_AWA_3100.PAY_BRTY != '2') {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_INPUT_DATA_ERR, PNB3100_AWA_3100.PAY_BRTY_NO);
        }
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == K_ERROR 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE != 0) {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_INPUT_DATA_ERR);
        }
    }
    public void B200_DRAFT_PAY() throws IOException,SQLException,Exception {
        B210_PUSH_DATA_FINANCE();
    }
    public void B210_PUSH_DATA_FINANCE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNCGHPAY);
        PNCGHPAY.KND = PNB3100_AWA_3100.KND;
        PNCGHPAY.DRFT_NO = PNB3100_AWA_3100.DRFT_NO;
        PNCGHPAY.PAY_BRTY = PNB3100_AWA_3100.PAY_BRTY;
        PNCGHPAY.BAL_AMT = PNB3100_AWA_3100.BAL_AMT;
        PNCGHPAY.STL_AMT = PNB3100_AWA_3100.STL_AMT;
        S000_CALL_PNZGHPAY();
    }
    public void S000_CALL_PNZGHPAY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "PN-U-PAY-ICBC-DRFT", PNCGHPAY);
        if (PNCGHPAY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, PNCGHPAY.RC);
            S000_ERR_MSG_PROC();
        }
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
