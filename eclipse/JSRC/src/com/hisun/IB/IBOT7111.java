package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class IBOT7111 {
    String JIBS_tmp_str[] = new String[10];
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    IBCACINS IBCACINS = new IBCACINS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBB7110_AWA_7110 IBB7110_AWA_7110;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "IBOT7111 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "IBB7110_AWA_7110>");
        IBB7110_AWA_7110 = (IBB7110_AWA_7110) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_INQ_AC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBB7110_AWA_7110.PRI_ACNO);
        CEP.TRC(SCCGWA, IBB7110_AWA_7110.NOS_CD);
        CEP.TRC(SCCGWA, IBB7110_AWA_7110.CCY);
        if (IBB7110_AWA_7110.PRI_ACNO.trim().length() == 0 
            && (IBB7110_AWA_7110.NOS_CD.trim().length() == 0 
            || IBB7110_AWA_7110.CCY.trim().length() == 0)) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT_ONE);
        }
        CEP.TRC(SCCGWA, IBB7110_AWA_7110.SEQ_NO);
        if (IBB7110_AWA_7110.SEQ_NO == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.SEQ_MUST_INPUT);
        }
    }
    public void B020_INQ_AC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCACINS);
        IBCACINS.FUNC = IBB7110_AWA_7110.FCN_C;
        IBCACINS.NOSTR_CD = IBB7110_AWA_7110.NOS_CD;
        IBCACINS.PRIM_AC_NO = IBB7110_AWA_7110.PRI_ACNO;
        IBCACINS.SEQ_NO = IBB7110_AWA_7110.SEQ_NO;
        IBCACINS.CCY = IBB7110_AWA_7110.CCY;
        S000_CALL_IBZACINS();
    }
    public void S000_CALL_IBZACINS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-AC-INQ-SUB", IBCACINS);
        if (IBCACINS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCACINS.RC);
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
