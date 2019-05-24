package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT8160 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICMID CICMID = new CICMID();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB5400_AWA_5400 CIB5400_AWA_5400;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT8160 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB5400_AWA_5400>");
        CIB5400_AWA_5400 = (CIB5400_AWA_5400) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        IBS.init(SCCGWA, CICMID);
        CICMID.DATA.CI_NO = CIB5400_AWA_5400.CI_NO;
        CICMID.PAGE_DATA.PAGE_ROW = CIB5400_AWA_5400.PAGE_ROW;
        CICMID.PAGE_DATA.PAGE_NUM = CIB5400_AWA_5400.PAGE_NUM;
        CICMID.FUNC = 'E';
        CEP.TRC(SCCGWA, CIB5400_AWA_5400.CI_NO);
        CEP.TRC(SCCGWA, CIB5400_AWA_5400.PAGE_ROW);
        CEP.TRC(SCCGWA, CIB5400_AWA_5400.PAGE_NUM);
        S000_LINK_CIZMID();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (CIB5400_AWA_5400.PAGE_ROW == 0 
            || CIB5400_AWA_5400.PAGE_ROW > 24) {
            CIB5400_AWA_5400.PAGE_ROW = 24;
        }
        if (CIB5400_AWA_5400.PAGE_NUM == 0) {
            CIB5400_AWA_5400.PAGE_NUM = 1;
        }
    }
    public void S000_LINK_CIZMID() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-ID", CICMID);
        if (CICMID.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICMID.RC);
        }
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
