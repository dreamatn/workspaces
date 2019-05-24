package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class IBOT5110 {
    String JIBS_tmp_str[] = new String[10];
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    IBCRATBR IBCRATBR = new IBCRATBR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBB5110_AWA_5110 IBB5110_AWA_5110;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "IBOT5110 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "IBB5110_AWA_5110>");
        IBB5110_AWA_5110 = (IBB5110_AWA_5110) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_TRANS_DATA_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBB5110_AWA_5110.AC_NO);
        CEP.TRC(SCCGWA, IBB5110_AWA_5110.NOST_CD);
        CEP.TRC(SCCGWA, IBB5110_AWA_5110.CCY);
        if (IBB5110_AWA_5110.AC_NO.trim().length() == 0 
            && (IBB5110_AWA_5110.NOST_CD.trim().length() == 0 
            || IBB5110_AWA_5110.CCY.trim().length() == 0)) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT_ONE, IBB5110_AWA_5110.AC_NO_NO);
        }
    }
    public void B020_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCRATBR);
        IBCRATBR.AC_NO = IBB5110_AWA_5110.AC_NO;
        IBCRATBR.NOS_CD = IBB5110_AWA_5110.NOST_CD;
        IBCRATBR.CCY = IBB5110_AWA_5110.CCY;
        S000_CALL_IBZRATBR();
    }
    public void S000_CALL_IBZRATBR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-BROWSE-RATE-INFO", IBCRATBR);
        if (IBCRATBR.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCRATBR.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}