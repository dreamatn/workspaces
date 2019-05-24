package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1220 {
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFEPAY BPCFEPAY = new BPCFEPAY();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB1220_AWA_1220 BPB1220_AWA_1220;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1220 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1220_AWA_1220>");
        BPB1220_AWA_1220 = (BPB1220_AWA_1220) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_FEE_PAY();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_FEE_PAY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFEPAY);
        BPCFEPAY.INFO.FEE_CODE = BPB1220_AWA_1220.FEE_CODE;
        BPCFEPAY.INFO.FEE_CCY = BPB1220_AWA_1220.FEE_CCY;
        BPCFEPAY.INFO.FEE_AMT = BPB1220_AWA_1220.FEE_AMT;
        BPCFEPAY.INFO.VAT_AMT = BPB1220_AWA_1220.VAT_AMT;
        BPCFEPAY.INFO.AC_TYP = BPB1220_AWA_1220.AC_TYP;
        BPCFEPAY.INFO.AC_NO = BPB1220_AWA_1220.AC_NO;
        BPCFEPAY.INFO.CREV_NO = BPB1220_AWA_1220.CREV_NO;
        BPCFEPAY.INFO.BR = BPB1220_AWA_1220.BR;
        BPCFEPAY.INFO.CI_NO = BPB1220_AWA_1220.CI_NO;
        BPCFEPAY.INFO.TX_PROD = BPB1220_AWA_1220.TX_PROD;
        BPCFEPAY.INFO.REF_NO = BPB1220_AWA_1220.REF_NO;
        BPCFEPAY.INFO.INV_NO = BPB1220_AWA_1220.INV_NO;
        BPCFEPAY.INFO.INV_CD = BPB1220_AWA_1220.INV_CD;
        S000_CALL_BPZFEPAY();
    }
    public void S000_CALL_BPZFEPAY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-FEE-PAYMENT", BPCFEPAY);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
