package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class TDOT5880 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CNT = 0;
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    TDCAACE TDCAACE = new TDCAACE();
    SCCGWA SCCGWA;
    TDB5880_AWA_5880 TDB5880_AWA_5880;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDOT5880 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "TDB5880_AWA_5880>");
        TDB5880_AWA_5880 = (TDB5880_AWA_5880) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_QURY_SETT_INT_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (TDB5880_AWA_5880.CARD_NO.trim().length() == 0) {
            WS_CNT = WS_CNT + 1;
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_CARD_NO_MUST_INPUT;
            if (TDB5880_AWA_5880.CARD_NO.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(TDB5880_AWA_5880.CARD_NO);
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (TDB5880_AWA_5880.AC_SEQ == 0) {
            WS_CNT = WS_CNT + 1;
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_SEQ_MUST_INP;
            WS_FLD_NO = (short) TDB5880_AWA_5880.AC_SEQ;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (WS_CNT > 0) {
            S000_ERR_MSG_PROC_LAST();
        }
    }
    public void B020_QURY_SETT_INT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDB5880_AWA_5880.CARD_NO);
        CEP.TRC(SCCGWA, TDB5880_AWA_5880.AC_SEQ);
        CEP.TRC(SCCGWA, TDB5880_AWA_5880.PAGE_NUM);
        CEP.TRC(SCCGWA, TDB5880_AWA_5880.PAGE_ROW);
        IBS.init(SCCGWA, TDCAACE);
        TDCAACE.CARD_NO = TDB5880_AWA_5880.CARD_NO;
        TDCAACE.AC_SEQ = TDB5880_AWA_5880.AC_SEQ;
        TDCAACE.PAGE_NUM = TDB5880_AWA_5880.PAGE_NUM;
        TDCAACE.PAGE_ROW = TDB5880_AWA_5880.PAGE_ROW;
        S000_CALL_TDZSQINT();
    }
    public void S000_CALL_TDZSQINT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-TDZSQINT", TDCAACE);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
