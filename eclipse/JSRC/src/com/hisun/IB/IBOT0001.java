package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class IBOT0001 {
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    String K_OUTPUT_FMT = "IBA01";
    IBOT0001_WS_IB_INF WS_IB_INF = new IBOT0001_WS_IB_INF();
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    IBCPOSTA IBCPOSTA = new IBCPOSTA();
    IBCCASH IBCCASH = new IBCCASH();
    IBCFATRU IBCFATRU = new IBCFATRU();
    IBCQRAC IBCQRAC = new IBCQRAC();
    IBCTBP01 IBCTBP01 = new IBCTBP01();
    IBCLINT IBCLINT = new IBCLINT();
    IBCQINFS IBCQINFS = new IBCQINFS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBB0001_AWA_0001 IBB0001_AWA_0001;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "IBOT0001 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "IBB0001_AWA_0001>");
        IBB0001_AWA_0001 = (IBB0001_AWA_0001) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_TRANS_DATA_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCTBP01);
        WS_IB_INF.WS_AC_NO = "71040000000034";
        WS_IB_INF.WS_PAY_AC = "71080000000030";
        WS_IB_INF.WS_PAY_AMT = 15000;
        WS_IB_INF.WS_CCY = "156";
        WS_IB_INF.WS_AC_DATE = 20170121;
        IBCTBP01.TR_DATA = IBS.CLS2CPY(SCCGWA, WS_IB_INF);
        S000_CALL_IBZLPAY();
    }
    public void S000_CALL_IBZLPAY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-L-AUTO-PAY", IBCTBP01);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
