package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT5553 {
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
    CIB5553_AWA_5553 CIB5553_AWA_5553;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT5553 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB5553_AWA_5553>");
        CIB5553_AWA_5553 = (CIB5553_AWA_5553) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSMRC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_OPEN_PER_CUST();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (CIB5553_AWA_5553.RELT_NO == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "名单编号必须输入");
        }
        if (CIB5553_AWA_5553.RELT_CNM.trim().length() == 0 
            && CIB5553_AWA_5553.RELT_ENM.trim().length() == 0 
            && (CIB5553_AWA_5553.L_ID_TYP.trim().length() == 0 
            || CIB5553_AWA_5553.L_ID_NO.trim().length() == 0)) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "证件与名称必输其�?");
        }
        if (CIB5553_AWA_5553.RELT_TYP.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "关联方类型必�?");
        }
    }
    public void B020_OPEN_PER_CUST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSMRC);
        CICSMRC.DATA.LIST_NO = CIB5553_AWA_5553.RELT_NO;
        CICSMRC.DATA.RELT_CNM = CIB5553_AWA_5553.RELT_CNM;
        CICSMRC.DATA.RELT_ENM = CIB5553_AWA_5553.RELT_ENM;
        CICSMRC.DATA.RELT_ID_TYPE = CIB5553_AWA_5553.L_ID_TYP;
        CICSMRC.DATA.RELT_ID_NO = CIB5553_AWA_5553.L_ID_NO;
        CICSMRC.DATA.REL_CNM = CIB5553_AWA_5553.REL_CNM;
        CICSMRC.DATA.REL_ENM = CIB5553_AWA_5553.REL_ENM;
        CICSMRC.DATA.REL_ID_TYPE = CIB5553_AWA_5553.R_ID_TYP;
        CICSMRC.DATA.REL_ID_NO = CIB5553_AWA_5553.R_ID_NO;
        CICSMRC.DATA.RELT_TYPE = CIB5553_AWA_5553.RELT_TYP;
        CICSMRC.DATA.RELT_CORP_NM = CIB5553_AWA_5553.L_COR_NM;
        CICSMRC.DATA.TITLE = CIB5553_AWA_5553.TITLE;
        CICSMRC.DATA.EMP_NO = CIB5553_AWA_5553.EMP_NO;
        CICSMRC.DATA.EFF_DT = CIB5553_AWA_5553.EFF_DT;
        CICSMRC.DATA.EXP_DT = CIB5553_AWA_5553.EXP_DT;
        CICSMRC.DATA.IDENT_CD = CIB5553_AWA_5553.IDENT_CD;
        CICSMRC.DATA.RELT_CD = CIB5553_AWA_5553.RELT_CD;
        CICSMRC.DATA.RELT_REMARK = CIB5553_AWA_5553.RELT_MRK;
        CICSMRC.DATA.REL_TYPE = CIB5553_AWA_5553.REL_TYPE;
        CICSMRC.DATA.RULE = CIB5553_AWA_5553.RULE;
        CICSMRC.FUNC = 'M';
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
