package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT8661 {
    short WS_I = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICSMRC CICSMRC = new CICSMRC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB8661_AWA_8661 CIB8661_AWA_8661;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT8661 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB8661_AWA_8661>");
        CIB8661_AWA_8661 = (CIB8661_AWA_8661) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSMRC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_OPEN_PER_CUST();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (CIB8661_AWA_8661.RELT_CNM.trim().length() == 0 
            && CIB8661_AWA_8661.RELT_ENM.trim().length() == 0 
            && CIB8661_AWA_8661.L_ID_TYP.trim().length() == 0 
            && CIB8661_AWA_8661.L_ID_NO.trim().length() == 0 
            && CIB8661_AWA_8661.RELT_TYP.trim().length() == 0 
            && CIB8661_AWA_8661.L_COR_NM.trim().length() == 0 
            && CIB8661_AWA_8661.TITLE.trim().length() == 0 
            && CIB8661_AWA_8661.EMP_NO.trim().length() == 0 
            && CIB8661_AWA_8661.EFF_DT == 0 
            && CIB8661_AWA_8661.EXP_DT == 0 
            && CIB8661_AWA_8661.IDENT_CD.trim().length() == 0 
            && CIB8661_AWA_8661.RELT_CD.trim().length() == 0 
            && CIB8661_AWA_8661.RELT_MRK.trim().length() == 0 
            && CIB8661_AWA_8661.RULE.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
    }
    public void B020_OPEN_PER_CUST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSMRC);
        CICSMRC.DATA.RELT_CNM = CIB8661_AWA_8661.RELT_CNM;
        CICSMRC.DATA.RELT_ENM = CIB8661_AWA_8661.RELT_ENM;
        CICSMRC.DATA.RELT_ID_TYPE = CIB8661_AWA_8661.L_ID_TYP;
        CICSMRC.DATA.RELT_ID_NO = CIB8661_AWA_8661.L_ID_NO;
        CICSMRC.DATA.RELT_TYPE = CIB8661_AWA_8661.RELT_TYP;
        CICSMRC.DATA.RELT_CORP_NM = CIB8661_AWA_8661.L_COR_NM;
        CICSMRC.DATA.TITLE = CIB8661_AWA_8661.TITLE;
        CICSMRC.DATA.EMP_NO = CIB8661_AWA_8661.EMP_NO;
        CICSMRC.DATA.EFF_DT = CIB8661_AWA_8661.EFF_DT;
        CICSMRC.DATA.EXP_DT = CIB8661_AWA_8661.EXP_DT;
        CICSMRC.DATA.IDENT_CD = CIB8661_AWA_8661.IDENT_CD;
        CICSMRC.DATA.RELT_CD = CIB8661_AWA_8661.RELT_CD;
        CICSMRC.DATA.RELT_REMARK = CIB8661_AWA_8661.RELT_MRK;
        CICSMRC.DATA.RULE = CIB8661_AWA_8661.RULE;
        CICSMRC.FUNC = 'A';
        S000_CALL_CIZSMRC();
    }
    public void S000_CALL_CIZSMRC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-RELT-CI", CICSMRC);
        if (CICSMRC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSMRC.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
