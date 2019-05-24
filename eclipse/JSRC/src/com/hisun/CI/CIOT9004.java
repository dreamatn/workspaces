package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT9004 {
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICBCVOU CICBCVOU = new CICBCVOU();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB9004_AWA_9004 CIB9004_AWA_9004;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT9004 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB9004_AWA_9004>");
        CIB9004_AWA_9004 = (CIB9004_AWA_9004) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICBCVOU);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_INQUIRE_AC_INF();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (CIB9004_AWA_9004.CI_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "CI-NO AC ID MUST INPUT ONE ");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
        if (CIB9004_AWA_9004.PAGE_NUM == 0 
            || CIB9004_AWA_9004.PAGE_ROW == 0) {
            CEP.TRC(SCCGWA, "PAGE INF MUST INPUT ");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
    }
    public void B020_INQUIRE_AC_INF() throws IOException,SQLException,Exception {
        CICBCVOU.DATA.CI_NO = CIB9004_AWA_9004.CI_NO;
        CICBCVOU.DATA.PAGE_NUM = CIB9004_AWA_9004.PAGE_NUM;
        CICBCVOU.DATA.PAGE_ROW = CIB9004_AWA_9004.PAGE_ROW;
        CEP.TRC(SCCGWA, CICBCVOU.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICBCVOU.DATA.PAGE_NUM);
        CEP.TRC(SCCGWA, CICBCVOU.DATA.PAGE_ROW);
        S000_CALL_CIZBCVOU();
        if (CICBCVOU.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICBCVOU.RC);
        }
    }
    public void S000_CALL_CIZBCVOU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-BR-CUST-VOUCHER", CICBCVOU);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
