package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT5643 {
    DBParm CITCGRP_RD;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CIRCGRP CIRCGRP = new CIRCGRP();
    CICMGRPM CICMGRPM = new CICMGRPM();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB5640_AWA_5640 CIB5640_AWA_5640;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT5643 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB5640_AWA_5640>");
        CIB5640_AWA_5640 = (CIB5640_AWA_5640) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICMGRPM);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        IBS.init(SCCGWA, CICMGRPM);
        CICMGRPM.INPUT_DATA.GRPS_NO = CIB5640_AWA_5640.GRPS_NO;
        CICMGRPM.INPUT_DATA.CI_NO = CIB5640_AWA_5640.CI_NO;
        CICMGRPM.INPUT_DATA.EFF_DATE = CIB5640_AWA_5640.EFF_DT;
        CICMGRPM.INPUT_DATA.EXP_DATE = CIB5640_AWA_5640.EXP_DT;
        CICMGRPM.FUNC = 'M';
        S000_CALL_CIZMGRPM();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB5640_AWA_5640.GRPS_NO);
        CEP.TRC(SCCGWA, CIB5640_AWA_5640.CI_NO);
        if (CIB5640_AWA_5640.GRPS_NO.trim().length() == 0) {
            CEP.ERRC(SCCGWA, CICMSG_ERROR_MSG.CI_GRPS_NO_MUST_INPUT);
        }
        if (CIB5640_AWA_5640.CI_NO.trim().length() == 0) {
            CEP.ERRC(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
        CEP.TRC(SCCGWA, CIB5640_AWA_5640.EFF_DT);
        CEP.TRC(SCCGWA, CIB5640_AWA_5640.EXP_DT);
        IBS.init(SCCGWA, CIRCGRP);
        CIRCGRP.KEY.GRPS_NO = CIB5640_AWA_5640.GRPS_NO;
        T000_READ_CITCGRP();
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, CIRCGRP.EFF_DATE);
        CEP.TRC(SCCGWA, CIRCGRP.EXP_DATE);
        if (CIB5640_AWA_5640.EFF_DT < CIRCGRP.EFF_DATE 
            || CIB5640_AWA_5640.EFF_DT > CIRCGRP.EXP_DATE 
            || CIB5640_AWA_5640.EFF_DT > CIB5640_AWA_5640.EXP_DT) {
            CEP.ERRC(SCCGWA, CICMSG_ERROR_MSG.EFF_DT_INPUT_ERROR);
        }
        if (CIB5640_AWA_5640.EXP_DT < CIRCGRP.EFF_DATE 
            || CIB5640_AWA_5640.EXP_DT > CIRCGRP.EXP_DATE) {
            CEP.ERRC(SCCGWA, CICMSG_ERROR_MSG.CI_EXP_DT_INPUT_ERR);
        }
        if (CIB5640_AWA_5640.EXP_DT < SCCGWA.COMM_AREA.AC_DATE) {
            CEP.ERRC(SCCGWA, CICMSG_ERROR_MSG.CI_EXP_DT_INPUT_ERR);
        }
        CEP.ERR(SCCGWA);
    }
    public void S000_CALL_CIZMGRPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SVR-CIZMGRPM", CICMGRPM);
        if (CICMGRPM.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICMGRPM.RC);
        }
    }
    public void T000_READ_CITCGRP() throws IOException,SQLException,Exception {
        CITCGRP_RD = new DBParm();
        CITCGRP_RD.TableName = "CITCGRP";
        CITCGRP_RD.eqWhere = "GRPS_NO";
        IBS.READ(SCCGWA, CIRCGRP, CITCGRP_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
