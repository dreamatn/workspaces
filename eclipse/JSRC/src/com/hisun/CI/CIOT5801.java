package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT5801 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_END_FLAG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICMLS2 CICMLS2 = new CICMLS2();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB5800_AWA_5800 CIB5800_AWA_5800;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT5801 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB5800_AWA_5800>");
        CIB5800_AWA_5800 = (CIB5800_AWA_5800) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        IBS.init(SCCGWA, CICMLS2);
        CICMLS2.DATA.LST_CD = CIB5800_AWA_5800.LST_CD;
        CICMLS2.DATA.ID_TYPE = CIB5800_AWA_5800.ID_TYPE;
        CICMLS2.DATA.ID_NO = CIB5800_AWA_5800.ID_NO;
        CICMLS2.DATA.CI_ENM = CIB5800_AWA_5800.CI_ENM;
        CICMLS2.DATA.CI_CNM = CIB5800_AWA_5800.CI_CNM;
        CICMLS2.DATA.SRC_SEQ = CIB5800_AWA_5800.SRC_SEQ;
        CICMLS2.DATA.LST_DT = CIB5800_AWA_5800.LST_DT;
        CICMLS2.DATA.CI_BBR = CIB5800_AWA_5800.CI_BBR;
        CICMLS2.DATA.LEG_NAME = CIB5800_AWA_5800.LEG_NAME;
        CICMLS2.DATA.LEG_IDTY = CIB5800_AWA_5800.LEG_IDTY;
        CICMLS2.DATA.LEG_IDNO = CIB5800_AWA_5800.LEG_IDNO;
        CICMLS2.DATA.DFA_CNT = CIB5800_AWA_5800.DFA_CNT;
        CICMLS2.DATA.DFA_AMT = CIB5800_AWA_5800.DFA_AMT;
        CICMLS2.DATA.RSN = CIB5800_AWA_5800.RSN;
        CICMLS2.DATA.DESC = CIB5800_AWA_5800.DESC;
        CICMLS2.FUNC = 'A';
        S000_LINK_CIZMLS2();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (CIB5800_AWA_5800.LST_CD.trim().length() == 0) {
            WS_ERR_MSG = CICMSG_ERROR_MSG.CI_MUST_INPUT;
            WS_FLD_NO = CIB5800_AWA_5800.LST_CD_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (CIB5800_AWA_5800.CI_ENM.trim().length() == 0 
            && CIB5800_AWA_5800.CI_CNM.trim().length() == 0) {
            WS_ERR_MSG = CICMSG_ERROR_MSG.CI_MUST_INPUT;
            WS_FLD_NO = CIB5800_AWA_5800.CI_ENM_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (CIB5800_AWA_5800.ID_TYPE.trim().length() == 0 
            || CIB5800_AWA_5800.ID_NO.trim().length() == 0) {
            WS_ERR_MSG = CICMSG_ERROR_MSG.CI_MUST_INPUT;
            WS_FLD_NO = CIB5800_AWA_5800.ID_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (CIB5800_AWA_5800.LST_DT > SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = CICMSG_ERROR_MSG.CI_LST_DT_GT;
            WS_FLD_NO = CIB5800_AWA_5800.LST_DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void S000_LINK_CIZMLS2() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-LST2", CICMLS2);
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