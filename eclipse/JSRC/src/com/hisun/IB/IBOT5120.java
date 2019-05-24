package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class IBOT5120 {
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    short WS_CNT = 0;
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    IBCSEINT IBCSEINT = new IBCSEINT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    IBB5110_AWA_5110 IBB5110_AWA_5110;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "IBOT5120 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "IBB5110_AWA_5110>");
        IBB5110_AWA_5110 = (IBB5110_AWA_5110) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_SETTL_INT_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (IBB5110_AWA_5110.AC_NO.trim().length() == 0 
            && (IBB5110_AWA_5110.NOST_CD.trim().length() == 0 
            || IBB5110_AWA_5110.CCY.trim().length() == 0)) {
            WS_CNT = (short) (WS_CNT + 1);
            CEP.ERRC(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT_ONE, IBB5110_AWA_5110.AC_NO_NO);
        }
        if (IBB5110_AWA_5110.SETT_DT == 0) {
            WS_CNT = (short) (WS_CNT + 1);
            CEP.ERRC(SCCGWA, IBCMSG_ERROR_MSG.SETT_DATE_M, IBB5110_AWA_5110.SETT_DT_NO);
        }
        if (IBB5110_AWA_5110.SETT_DT1 == 0) {
            WS_CNT = (short) (WS_CNT + 1);
            CEP.ERRC(SCCGWA, IBCMSG_ERROR_MSG.SETT_DATE1_M, IBB5110_AWA_5110.SETT_DT1_NO);
        }
        if (IBB5110_AWA_5110.ASET_AMT == 0) {
            WS_CNT = (short) (WS_CNT + 1);
            CEP.ERRC(SCCGWA, IBCMSG_ERROR_MSG.ASET_AMT_M, IBB5110_AWA_5110.ASET_AMT_NO);
        }
        CEP.TRC(SCCGWA, WS_CNT);
        if (WS_CNT > 0) {
            CEP.ERR(SCCGWA);
        }
    }
    public void B020_SETTL_INT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCSEINT);
        IBCSEINT.AC_NO = IBB5110_AWA_5110.AC_NO;
        IBCSEINT.NOST_CD = IBB5110_AWA_5110.NOST_CD;
        IBCSEINT.CCY = IBB5110_AWA_5110.CCY;
        IBCSEINT.SETT_DT = IBB5110_AWA_5110.SETT_DT;
        IBCSEINT.ESET_AMT = IBB5110_AWA_5110.ESET_AMT;
        IBCSEINT.ASET_AMT = IBB5110_AWA_5110.ASET_AMT;
        IBCSEINT.SETT_DT1 = IBB5110_AWA_5110.SETT_DT1;
        S000_CALL_IBZSEINT();
    }
    public void S000_CALL_IBZSEINT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZSEINT", IBCSEINT);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
