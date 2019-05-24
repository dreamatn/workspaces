package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT8290 {
    String WS_MSGID = " ";
    String WS_ERR_INFO = " ";
    short WS_FLD_NO = 0;
    int WS_CLS_DT = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCBINF SCCBINF = new SCCBINF();
    CICSACOC CICSACOC = new CICSACOC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB8290_AWA_8290 CIB8290_AWA_8290;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT8290 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB8290_AWA_8290>");
        CIB8290_AWA_8290 = (CIB8290_AWA_8290) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSACOC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_CALL_CIZSACOC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB8290_AWA_8290.FUNC);
        CEP.TRC(SCCGWA, CIB8290_AWA_8290.AC_NO);
        CEP.TRC(SCCGWA, CIB8290_AWA_8290.CI_NO);
        if (CIB8290_AWA_8290.FUNC == 'O') {
            CICSACOC.DATA.FUNC = 'O';
        } else if (CIB8290_AWA_8290.FUNC == 'C') {
            CICSACOC.DATA.FUNC = 'C';
        } else {
            WS_MSGID = CICMSG_ERROR_MSG.CI_FUNC_ERR;
            WS_ERR_INFO = "I TABLE FOR 8290 FUNC(" + CIB8290_AWA_8290.FUNC + ")";
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (CIB8290_AWA_8290.AC_NO.trim().length() == 0 
            && CIB8290_AWA_8290.CI_NO.trim().length() == 0) {
            WS_MSGID = CICMSG_ERROR_MSG.CI_MUST_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (CIB8290_AWA_8290.AC_NO.trim().length() > 0 
            && CIB8290_AWA_8290.CI_NO.trim().length() > 0) {
            WS_MSGID = CICMSG_ERROR_MSG.CI_INPUT_DATA_UNMATH;
            WS_ERR_INFO = "INPUT INF ONLY ONE";
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (CIB8290_AWA_8290.CI_NO.trim().length() > 0) {
            CICSACOC.DATA.OPT = 'K';
        } else {
            if (CIB8290_AWA_8290.AC_NO.trim().length() > 0) {
                CICSACOC.DATA.OPT = 'A';
            } else {
                WS_MSGID = CICMSG_ERROR_MSG.CI_MUST_INPUT;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        if (CIB8290_AWA_8290.CLS_DT == 0) {
            if ("99991231".trim().length() == 0) WS_CLS_DT = 0;
            else WS_CLS_DT = Integer.parseInt("99991231");
        } else {
            WS_CLS_DT = CIB8290_AWA_8290.CLS_DT;
        }
        S000_ERR_MSG_PROC_LAST();
    }
    public void B020_CALL_CIZSACOC() throws IOException,SQLException,Exception {
        CICSACOC.DATA.FUNC = CIB8290_AWA_8290.FUNC;
        CICSACOC.DATA.AC_NO = CIB8290_AWA_8290.AC_NO;
        CICSACOC.DATA.CI_NO = CIB8290_AWA_8290.CI_NO;
        CICSACOC.DATA.OPEN_DT = CIB8290_AWA_8290.OPEN_DT;
        CICSACOC.DATA.CLS_DT = WS_CLS_DT;
        CICSACOC.DATA.FRM_APP = CIB8290_AWA_8290.FRM_APP;
        CICSACOC.DATA.PAGE_ROW = CIB8290_AWA_8290.PAGE_ROW;
        CICSACOC.DATA.PAGE_NUM = CIB8290_AWA_8290.PAGE_NUM;
        S000_CALL_CIZSACOC();
    }
    public void S000_CALL_CIZSACOC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZSACOC", CICSACOC);
        if (CICSACOC.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, CICSACOC.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_MSGID, WS_ERR_INFO, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, WS_ERR_INFO, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
