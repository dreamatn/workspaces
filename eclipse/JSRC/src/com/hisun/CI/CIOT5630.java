package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT5630 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICMCGRP CICMCGRP = new CICMCGRP();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB5630_AWA_5630 CIB5630_AWA_5630;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT5630 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB5630_AWA_5630>");
        CIB5630_AWA_5630 = (CIB5630_AWA_5630) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        CICMCGRP.INPUT_DATA.GRPS_NO = CIB5630_AWA_5630.GRPS_NO;
        CICMCGRP.INPUT_DATA.NAME = CIB5630_AWA_5630.NAME;
        CICMCGRP.FUNC = 'B';
        S000_LINK_CIZMCGRP();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB5630_AWA_5630.GRPS_NO);
        CEP.TRC(SCCGWA, CIB5630_AWA_5630.TYPE);
        CEP.TRC(SCCGWA, CIB5630_AWA_5630.NAME);
        if (CIB5630_AWA_5630.GRPS_NO.trim().length() > 0) {
            CICMCGRP.CHECK_DATA = 'N';
        } else if (CIB5630_AWA_5630.NAME.trim().length() > 0) {
            CICMCGRP.CHECK_DATA = 'M';
        } else {
            CICMCGRP.CHECK_DATA = 'A';
        }
    }
    public void S000_LINK_CIZMCGRP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SVR-CIZMCGRP", CICMCGRP);
        if (CICMCGRP.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICMCGRP.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
