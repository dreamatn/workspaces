package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT5401 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_END_FLAG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    CICMID CICMID = new CICMID();
    CICMSTS CICMSTS = new CICMSTS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB5400_AWA_5400 CIB5400_AWA_5400;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT5401 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB5400_AWA_5400>");
        CIB5400_AWA_5400 = (CIB5400_AWA_5400) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.PGM_NO);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        CICMID.DATA.CI_NO = CIB5400_AWA_5400.CI_NO;
        CICMID.DATA.SINGLE_DATA.S_ID_TYPE = CIB5400_AWA_5400.ID_TYP;
        CICMID.DATA.SINGLE_DATA.S_ID_NO = CIB5400_AWA_5400.ID_NO;
        CICMID.DATA.SINGLE_DATA.S_EFF_DT = CIB5400_AWA_5400.EFF_DT;
        CICMID.DATA.SINGLE_DATA.S_EXP_DT = CIB5400_AWA_5400.EXP_DT;
        CICMID.DATA.SINGLE_DATA.S_ID_CNTY = CIB5400_AWA_5400.ID_CNTY;
        CICMID.DATA.SINGLE_DATA.S_ID_RGN = CIB5400_AWA_5400.ID_RGN;
        CICMID.DATA.SINGLE_DATA.S_ID_ORG = CIB5400_AWA_5400.ID_ORG;
        CICMID.DATA.SINGLE_DATA.S_ID_LVL = CIB5400_AWA_5400.ID_LVL;
        CICMID.DATA.SINGLE_DATA.S_DESC = CIB5400_AWA_5400.DESC;
        CICMID.DATA.SINGLE_DATA.S_OPEN = CIB5400_AWA_5400.OPEN;
        CICMID.FUNC = 'A';
        CICMID.CTLW_1 = 'O';
        S000_LINK_CIZMID();
        if (CIB5400_AWA_5400.OPEN == 'Y') {
            IBS.init(SCCGWA, CICMSTS);
            CICMSTS.FUNC = 'C';
            CICMSTS.DATA.CI_NO = CIB5400_AWA_5400.CI_NO;
            S000_LINK_CIZMSTS();
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (CIB5400_AWA_5400.CI_NO.trim().length() == 0) {
            WS_ERR_MSG = CICMSG_ERROR_MSG.CI_NO_MUST_INPUT;
            WS_FLD_NO = CIB5400_AWA_5400.CI_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (CIB5400_AWA_5400.ID_TYP.trim().length() == 0) {
            WS_ERR_MSG = CICMSG_ERROR_MSG.CI_MUST_INPUT;
            WS_FLD_NO = CIB5400_AWA_5400.ID_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (CIB5400_AWA_5400.ID_NO.trim().length() == 0) {
            WS_ERR_MSG = CICMSG_ERROR_MSG.CI_MUST_INPUT;
            WS_FLD_NO = CIB5400_AWA_5400.ID_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (CIB5400_AWA_5400.EXP_DT < CIB5400_AWA_5400.EFF_DT 
            || CIB5400_AWA_5400.EXP_DT < SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = CICMSG_ERROR_MSG.CI_ID_EXP_ERR;
            WS_FLD_NO = CIB5400_AWA_5400.EXP_DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        S000_ERR_MSG_PROC_LAST();
    }
    public void S000_LINK_CIZMID() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-ID", CICMID);
        if (CICMID.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICMID.RC);
        }
    }
    public void S000_LINK_CIZMSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SVR-REC-CIZMSTS", CICMSTS, true);
        if (CICMSTS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICMSTS.RC);
        }
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