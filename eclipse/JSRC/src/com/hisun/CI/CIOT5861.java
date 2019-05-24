package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT5861 {
    String CPN_REC_MGFX = "CI-REC-MGFX         ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_END_FLAG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICMGFX CICMGFX = new CICMGFX();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB5860_AWA_5860 CIB5860_AWA_5860;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT5861 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB5860_AWA_5860>");
        CIB5860_AWA_5860 = (CIB5860_AWA_5860) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        IBS.init(SCCGWA, CICMGFX);
        CICMGFX.FUNC = 'A';
        CICMGFX.CI_NO = CIB5860_AWA_5860.CI_NO;
        CEP.TRC(SCCGWA, CICMGFX.CI_NO);
        CICMGFX.CI_NM = CIB5860_AWA_5860.CI_NM;
        CICMGFX.ID_TYP = CIB5860_AWA_5860.ID_TYP;
        CEP.TRC(SCCGWA, CICMGFX.ID_TYP);
        CICMGFX.ID_NO = CIB5860_AWA_5860.ID_NO;
        CEP.TRC(SCCGWA, CICMGFX.ID_NO);
        CICMGFX.PACK_NO = CIB5860_AWA_5860.PACK_NO;
        CICMGFX.MON_AMT = CIB5860_AWA_5860.MON_AMT;
        CICMGFX.MON_P_NO = CIB5860_AWA_5860.MON_P_NO;
        CICMGFX.EFF_DATE = CIB5860_AWA_5860.EFF_DATE;
        CICMGFX.EXP_DATE = CIB5860_AWA_5860.EXP_DATE;
        CICMGFX.SPAP_FLG = CIB5860_AWA_5860.SPAP_FLG;
        CICMGFX.SIGN_AC1 = CIB5860_AWA_5860.SIGN_AC1;
        CICMGFX.SIGN_AC2 = CIB5860_AWA_5860.SIGN_AC2;
        CICMGFX.SIGN_AC3 = CIB5860_AWA_5860.SIGN_AC3;
        CICMGFX.SIGN_AC4 = CIB5860_AWA_5860.SIGN_AC4;
        CICMGFX.SIGN_AC5 = CIB5860_AWA_5860.SIGN_AC5;
        CICMGFX.PRE_SER1 = CIB5860_AWA_5860.PRE_SER1;
        CICMGFX.PRE_SER2 = CIB5860_AWA_5860.PRE_SER2;
        CICMGFX.PRE_SER3 = CIB5860_AWA_5860.PRE_SER3;
        CICMGFX.PRE_SER4 = CIB5860_AWA_5860.PRE_SER4;
        CICMGFX.PRE_SER5 = CIB5860_AWA_5860.PRE_SER5;
        CICMGFX.PRE_SER6 = CIB5860_AWA_5860.PRE_SER6;
        CICMGFX.PRE_SER7 = CIB5860_AWA_5860.PRE_SER7;
        CICMGFX.PRE_SER8 = CIB5860_AWA_5860.PRE_SER8;
        CICMGFX.FLDK = CIB5860_AWA_5860.FLDK;
        CICMGFX.REL_NM = CIB5860_AWA_5860.REL_NM;
        CICMGFX.OCCUP = CIB5860_AWA_5860.OCCUP;
        CICMGFX.ADR_NM = CIB5860_AWA_5860.ADR_NM;
        CICMGFX.POS_CODE = CIB5860_AWA_5860.POS_CODE;
        CICMGFX.FAX = CIB5860_AWA_5860.FAX;
        CICMGFX.TEL = CIB5860_AWA_5860.TEL;
        S000_CALL_CIZMGFX();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB5860_AWA_5860.CI_NO);
        if (CIB5860_AWA_5860.CI_NO.trim().length() == 0) {
            WS_ERR_MSG = CICMSG_ERROR_MSG.CI_NO_MUST_INPUT;
            WS_FLD_NO = CIB5860_AWA_5860.CI_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        CEP.TRC(SCCGWA, CIB5860_AWA_5860.PACK_NO);
        if (CIB5860_AWA_5860.PACK_NO == ' ') {
            WS_ERR_MSG = CICMSG_ERROR_MSG.CI_PACK_NO_MUST_IPT;
            WS_FLD_NO = CIB5860_AWA_5860.PACK_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        CEP.TRC(SCCGWA, CIB5860_AWA_5860.EFF_DATE);
        if (CIB5860_AWA_5860.EFF_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = CICMSG_ERROR_MSG.EFF_DT_INPUT_ERROR;
            WS_FLD_NO = CIB5860_AWA_5860.EFF_DATE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (CIB5860_AWA_5860.EXP_DATE < SCCGWA.COMM_AREA.AC_DATE 
            || CIB5860_AWA_5860.EXP_DATE < CIB5860_AWA_5860.EFF_DATE) {
            WS_ERR_MSG = CICMSG_ERROR_MSG.CI_EXP_DT_INPUT_ERR;
            WS_FLD_NO = CIB5860_AWA_5860.EXP_DATE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_CIZMGFX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-GFX", CICMGFX);
        if (CICMGFX.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICMGFX.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}