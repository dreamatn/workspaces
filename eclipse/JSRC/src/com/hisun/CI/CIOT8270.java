package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT8270 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICSDDMT CICSDDMT = new CICSDDMT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB8270_AWA_8270 CIB8270_AWA_8270;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT8270 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB8270_AWA_8270>");
        CIB8270_AWA_8270 = (CIB8270_AWA_8270) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_CALL_CIZSDDMT();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (CIB8270_AWA_8270.CI_NO.trim().length() == 0 
            && CIB8270_AWA_8270.AGR_NO.trim().length() == 0 
            && CIB8270_AWA_8270.ID_TYPE.trim().length() == 0 
            && CIB8270_AWA_8270.ID_NO.trim().length() == 0 
            && CIB8270_AWA_8270.CI_NM.trim().length() == 0) {
            WS_ERR_MSG = CICMSG_ERROR_MSG.CI_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_CALL_CIZSDDMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSDDMT);
        if (CIB8270_AWA_8270.CI_NO.trim().length() > 0) {
            CICSDDMT.DATA.FUNC = 'C';
        } else {
            if (CIB8270_AWA_8270.AGR_NO.trim().length() > 0) {
                CICSDDMT.DATA.FUNC = 'A';
            } else {
                if ((CIB8270_AWA_8270.ID_TYPE.trim().length() > 0 
                    && CIB8270_AWA_8270.ID_NO.trim().length() > 0 
                    && CIB8270_AWA_8270.CI_NM.trim().length() > 0)) {
                    CICSDDMT.DATA.FUNC = 'I';
                } else {
                    WS_ERR_MSG = CICMSG_ERROR_MSG.CI_MUST_INPUT;
                    S000_ERR_MSG_PROC();
                }
            }
        }
        CICSDDMT.DATA.CI_NO = CIB8270_AWA_8270.CI_NO;
        CICSDDMT.DATA.AGR_NO = CIB8270_AWA_8270.AGR_NO;
        CICSDDMT.DATA.ID_TYPE = CIB8270_AWA_8270.ID_TYPE;
        CICSDDMT.DATA.ID_NO = CIB8270_AWA_8270.ID_NO;
        CICSDDMT.DATA.CI_NM = CIB8270_AWA_8270.CI_NM;
        CICSDDMT.DATA.FRM_APP = CIB8270_AWA_8270.FRM_APP;
        CICSDDMT.DATA.PAGE_ROW = CIB8270_AWA_8270.PAGE_ROW;
        CICSDDMT.DATA.PAGE_NUM = CIB8270_AWA_8270.PAGE_NUM;
        S000_CALL_CIZSDDMT();
    }
    public void S000_CALL_CIZSDDMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZSDDMT", CICSDDMT);
        if (CICSDDMT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICSDDMT.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
