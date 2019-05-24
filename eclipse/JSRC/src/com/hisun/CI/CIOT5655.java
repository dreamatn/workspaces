package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT5655 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_END_FLAG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CIRSTC CIRSTC = new CIRSTC();
    CIRSCS CIRSCS = new CIRSCS();
    CIRSCA CIRSCA = new CIRSCA();
    CICSSTC CICSSTC = new CICSSTC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB5650_AWA_5650 CIB5650_AWA_5650;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT5655 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB5650_AWA_5650>");
        CIB5650_AWA_5650 = (CIB5650_AWA_5650) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        IBS.init(SCCGWA, CICSSTC);
        CICSSTC.STC_SEQ = CIB5650_AWA_5650.STC_SEQ;
        CICSSTC.CI_NO = CIB5650_AWA_5650.CI_NO;
        CICSSTC.CI_NM = CIB5650_AWA_5650.CI_NM;
        CICSSTC.CI_ENM = CIB5650_AWA_5650.CI_ENM;
        CICSSTC.STC_DATA.TYPE = CIB5650_AWA_5650.STC_TYPE;
        CICSSTC.STC_DATA.FIN_FLG = CIB5650_AWA_5650.FIN_FLG;
        CICSSTC.STC_DATA.PRT_DAY = CIB5650_AWA_5650.PRT_DAY;
        CICSSTC.STC_DATA.PRT_PERM = CIB5650_AWA_5650.PRT_PERM;
        CICSSTC.SCS_DATA.SEND_TYP = CIB5650_AWA_5650.SEND_TYP;
        CICSSTC.SCS_DATA.ADR_SRC = CIB5650_AWA_5650.ADR_SEQ;
        CICSSTC.SCS_DATA.ADR_NM = CIB5650_AWA_5650.ADR_NM;
        CICSSTC.SCS_DATA.CNT_SRC = CIB5650_AWA_5650.CNT_SEQ;
        CICSSTC.SCS_DATA.CNT_INF = CIB5650_AWA_5650.CNT_INF;
        CICSSTC.SCA_DATA.AC = CIB5650_AWA_5650.STC_AC;
        CICSSTC.FUNC = 'D';
        S000_LINK_CIZSSTC();
        if (CICSSTC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSSTC.RC);
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (CIB5650_AWA_5650.STC_SEQ == 0) {
            WS_ERR_MSG = CICMSG_ERROR_MSG.CI_MUST_INPUT;
            WS_FLD_NO = CIB5650_AWA_5650.STC_SEQ_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (CIB5650_AWA_5650.CI_NO.trim().length() == 0) {
            WS_ERR_MSG = CICMSG_ERROR_MSG.CI_NO_MUST_INPUT;
            WS_FLD_NO = CIB5650_AWA_5650.CI_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (CIB5650_AWA_5650.STC_TYPE == ' ') {
            WS_ERR_MSG = CICMSG_ERROR_MSG.CI_MUST_INPUT;
            WS_FLD_NO = CIB5650_AWA_5650.STC_TYPE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (CIB5650_AWA_5650.STC_TYPE == 'A') {
            if (CIB5650_AWA_5650.STC_AC.trim().length() == 0) {
                WS_ERR_MSG = CICMSG_ERROR_MSG.CI_MUST_INPUT;
                WS_FLD_NO = CIB5650_AWA_5650.STC_AC_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        CEP.TRC(SCCGWA, CIB5650_AWA_5650.SEND_TYP);
        if (CIB5650_AWA_5650.SEND_TYP == 'M' 
            && CIB5650_AWA_5650.ADR_SEQ.trim().length() == 0) {
            WS_ERR_MSG = CICMSG_ERROR_MSG.CI_MUST_INPUT;
            WS_FLD_NO = CIB5650_AWA_5650.ADR_SEQ_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (CIB5650_AWA_5650.SEND_TYP == 'E' 
            && CIB5650_AWA_5650.CNT_SEQ.trim().length() == 0) {
            WS_ERR_MSG = CICMSG_ERROR_MSG.CI_MUST_INPUT;
            WS_FLD_NO = CIB5650_AWA_5650.CNT_SEQ_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        S000_ERR_MSG_PROC_LAST();
    }
    public void S000_LINK_CIZSSTC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SERVICE-STC", CICSSTC);
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
