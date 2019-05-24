package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT8662 {
    DBParm CITRELT_RD;
    short WS_I = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CIRRELT CIRRELT = new CIRRELT();
    CICSMRC CICSMRC = new CICSMRC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB8662_AWA_8662 CIB8662_AWA_8662;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT8662 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB8662_AWA_8662>");
        CIB8662_AWA_8662 = (CIB8662_AWA_8662) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSMRC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_OPEN_PER_CUST();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (CIB8662_AWA_8662.REL_CNM.trim().length() == 0 
            && CIB8662_AWA_8662.REL_ENM.trim().length() == 0 
            && CIB8662_AWA_8662.R_ID_TYP.trim().length() == 0 
            && CIB8662_AWA_8662.R_ID_NO.trim().length() == 0 
            && CIB8662_AWA_8662.REL_TYPE.trim().length() == 0 
            && CIB8662_AWA_8662.RULE.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
    }
    public void B020_OPEN_PER_CUST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSMRC);
        IBS.init(SCCGWA, CIRRELT);
        CIRRELT.RELT_CNM = CIB8662_AWA_8662.RELT_CNM;
        CIRRELT.RELT_ENM = CIB8662_AWA_8662.RELT_ENM;
        CIRRELT.RELT_ID_TYPE = CIB8662_AWA_8662.L_ID_TYP;
        CIRRELT.RELT_ID_NO = CIB8662_AWA_8662.L_ID_NO;
        CIRRELT.RELT_TYPE = CIB8662_AWA_8662.RELT_TYP;
        CEP.TRC(SCCGWA, CIB8662_AWA_8662.RELT_CNM);
        CEP.TRC(SCCGWA, CIB8662_AWA_8662.RELT_ENM);
        CEP.TRC(SCCGWA, CIB8662_AWA_8662.L_ID_TYP);
        CEP.TRC(SCCGWA, CIB8662_AWA_8662.L_ID_NO);
        CEP.TRC(SCCGWA, CIB8662_AWA_8662.RELT_TYP);
        CEP.TRC(SCCGWA, CIRRELT.RELT_CNM);
        CEP.TRC(SCCGWA, CIRRELT.RELT_ID_TYPE);
        CEP.TRC(SCCGWA, CIRRELT.RELT_ID_NO);
        T000_READ_CITRELT_BY_IDNM();
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_RELT_NOT_FND);
        }
        CICSMRC.DATA.RELT_NO = CIRRELT.KEY.RELT_NO;
        CICSMRC.DATA.REL_CNM = CIB8662_AWA_8662.REL_CNM;
        CICSMRC.DATA.REL_ENM = CIB8662_AWA_8662.REL_ENM;
        CICSMRC.DATA.REL_ID_TYPE = CIB8662_AWA_8662.R_ID_TYP;
        CICSMRC.DATA.REL_ID_NO = CIB8662_AWA_8662.R_ID_NO;
        CICSMRC.DATA.REL_TYPE = CIB8662_AWA_8662.REL_TYPE;
        CICSMRC.DATA.RULE = CIB8662_AWA_8662.RULE;
        CICSMRC.FUNC = 'R';
        S000_CALL_CIZSMRC();
    }
    public void S000_CALL_CIZSMRC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-RELT-CI", CICSMRC);
        if (CICSMRC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSMRC.RC);
        }
    }
    public void T000_READ_CITRELT_BY_IDNM() throws IOException,SQLException,Exception {
        CITRELT_RD = new DBParm();
        CITRELT_RD.TableName = "CITRELT";
        CITRELT_RD.eqWhere = "RELT_CNM,RELT_ENM,RELT_ID_TYPE,RELT_ID_NO, RELT_TYPE";
        IBS.READ(SCCGWA, CIRRELT, CITRELT_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
