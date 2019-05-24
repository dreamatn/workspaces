package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT5551 {
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
    CIB5551_AWA_5551 CIB5551_AWA_5551;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT5551 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB5551_AWA_5551>");
        CIB5551_AWA_5551 = (CIB5551_AWA_5551) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSMRC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_OPEN_PER_CUST();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (CIB5551_AWA_5551.RELT_CNM.trim().length() == 0 
            && CIB5551_AWA_5551.RELT_ENM.trim().length() == 0 
            && (CIB5551_AWA_5551.L_ID_TYP.trim().length() == 0 
            || CIB5551_AWA_5551.L_ID_NO.trim().length() == 0)) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "证件与名称必输其�?");
        }
        if (CIB5551_AWA_5551.RELT_TYP.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "关联方类型必�?");
        }
    }
    public void B020_OPEN_PER_CUST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSMRC);
        CICSMRC.DATA.RELT_CNM = CIB5551_AWA_5551.RELT_CNM;
        CICSMRC.DATA.RELT_ENM = CIB5551_AWA_5551.RELT_ENM;
        CICSMRC.DATA.RELT_ID_TYPE = CIB5551_AWA_5551.L_ID_TYP;
        CICSMRC.DATA.RELT_ID_NO = CIB5551_AWA_5551.L_ID_NO;
        CICSMRC.DATA.RELT_TYPE = CIB5551_AWA_5551.RELT_TYP;
        CICSMRC.DATA.RELT_CORP_NM = CIB5551_AWA_5551.L_COR_NM;
        CICSMRC.DATA.TITLE = CIB5551_AWA_5551.TITLE;
        CICSMRC.DATA.EMP_NO = CIB5551_AWA_5551.EMP_NO;
        CICSMRC.DATA.EFF_DT = CIB5551_AWA_5551.EFF_DT;
        CICSMRC.DATA.EXP_DT = CIB5551_AWA_5551.EXP_DT;
        CICSMRC.DATA.IDENT_CD = CIB5551_AWA_5551.IDENT_CD;
        CICSMRC.DATA.RELT_CD = CIB5551_AWA_5551.RELT_CD;
        CICSMRC.DATA.RELT_REMARK = CIB5551_AWA_5551.RELT_MRK;
        CICSMRC.DATA.RULE = CIB5551_AWA_5551.RULE;
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
