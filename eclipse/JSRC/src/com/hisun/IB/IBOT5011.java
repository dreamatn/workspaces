package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class IBOT5011 {
    String JIBS_tmp_str[] = new String[10];
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    IBCACIN IBCACIN = new IBCACIN();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBB5010_AWA_5010 IBB5010_AWA_5010;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "IBOT5011 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "IBB5010_AWA_5010>");
        IBB5010_AWA_5010 = (IBB5010_AWA_5010) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_TRANS_DATA_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBB5010_AWA_5010.NOS_CD);
        CEP.TRC(SCCGWA, IBB5010_AWA_5010.CCY);
        CEP.TRC(SCCGWA, IBB5010_AWA_5010.ACNO);
        if (IBB5010_AWA_5010.ACNO.trim().length() == 0 
            && (IBB5010_AWA_5010.NOS_CD.trim().length() == 0 
            || IBB5010_AWA_5010.CCY.trim().length() == 0)) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT_ONE, IBB5010_AWA_5010.ACNO_NO);
        }
        CEP.TRC(SCCGWA, IBB5010_AWA_5010.FCN);
        if (IBB5010_AWA_5010.FCN == ' ') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.FCN_MUST_INPUT, IBB5010_AWA_5010.FCN_NO);
        }
    }
    public void B020_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCACIN);
        IBCACIN.FUNC = IBB5010_AWA_5010.FCN;
        IBCACIN.AC_NO = IBB5010_AWA_5010.ACNO;
        IBCACIN.NOS_CD = IBB5010_AWA_5010.NOS_CD;
        IBCACIN.CCY = IBB5010_AWA_5010.CCY;
        S000_CALL_IBZACIN();
    }
    public void S000_CALL_IBZACIN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-AC-INQ", IBCACIN);
        if (IBCACIN.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCACIN.RC);
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
