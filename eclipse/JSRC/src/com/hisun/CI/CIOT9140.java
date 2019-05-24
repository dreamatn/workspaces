package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT9140 {
    short WS_I = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICBWRT CICBWRT = new CICBWRT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB9140_AWA_9140 CIB9140_AWA_9140;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT9140 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB9140_AWA_9140>");
        CIB9140_AWA_9140 = (CIB9140_AWA_9140) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICBWRT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_OPEN_PER_CUST();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (CIB9140_AWA_9140.RELT_CNM.trim().length() == 0 
            && CIB9140_AWA_9140.RELT_ENM.trim().length() == 0 
            && CIB9140_AWA_9140.L_ID_NO.trim().length() == 0 
            && CIB9140_AWA_9140.L_ID_TYP.trim().length() == 0 
            && CIB9140_AWA_9140.RREL_CNM.trim().length() == 0 
            && CIB9140_AWA_9140.RREL_ENM.trim().length() == 0 
            && CIB9140_AWA_9140.R_ID_NO.trim().length() == 0 
            && CIB9140_AWA_9140.R_ID_TYP.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
        if (CIB9140_AWA_9140.L_ID_TYP.trim().length() > 0) {
            if (CIB9140_AWA_9140.L_ID_NO.trim().length() == 0) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_ID_NO_MUST_INPUT);
            }
        }
        if (CIB9140_AWA_9140.R_ID_TYP.trim().length() > 0) {
            if (CIB9140_AWA_9140.R_ID_NO.trim().length() == 0) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_ID_NO_MUST_INPUT);
            }
        }
        if (CIB9140_AWA_9140.PAGE_NUM == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "当前页数必须输入");
        }
    }
    public void B020_OPEN_PER_CUST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICBWRT);
        CICBWRT.DATA.RELT_CNM = CIB9140_AWA_9140.RELT_CNM;
        CICBWRT.DATA.RELT_ENM = CIB9140_AWA_9140.RELT_ENM;
        CICBWRT.DATA.RELT_IDTYP = CIB9140_AWA_9140.L_ID_TYP;
        CICBWRT.DATA.RELT_IDNO = CIB9140_AWA_9140.L_ID_NO;
        CICBWRT.DATA.RREL_CNM = CIB9140_AWA_9140.RREL_CNM;
        CICBWRT.DATA.RREL_ENM = CIB9140_AWA_9140.RREL_ENM;
        CICBWRT.DATA.RREL_IDTYP = CIB9140_AWA_9140.R_ID_TYP;
        CICBWRT.DATA.RREL_IDNO = CIB9140_AWA_9140.R_ID_NO;
        CICBWRT.DATA.PAGE_NUM = CIB9140_AWA_9140.PAGE_NUM;
        CICBWRT.DATA.PAGE_ROW = CIB9140_AWA_9140.PAGE_ROW;
        S000_CALL_CIZBWRT();
    }
    public void S000_CALL_CIZBWRT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-BROW-RELT-CI", CICBWRT);
        if (CICBWRT.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICBWRT.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
