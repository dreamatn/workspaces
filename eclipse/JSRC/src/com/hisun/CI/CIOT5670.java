package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT5670 {
    int K_MAX_DT = 99991231;
    String WS_MSGID = " ";
    short WS_FLD_NO = 0;
    String WS_ERR_INFO = " ";
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCBINF SCCBINF = new SCCBINF();
    CICSAGEN CICSAGEN = new CICSAGEN();
    SCCGWA SCCGWA;
    CIB5670_AWA_5670 CIB5670_AWA_5670;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT5670 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB5670_AWA_5670>");
        CIB5670_AWA_5670 = (CIB5670_AWA_5670) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSAGEN);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_MAIN_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB5670_AWA_5670.JRN_NO);
        CEP.TRC(SCCGWA, CIB5670_AWA_5670.START_DT);
        CEP.TRC(SCCGWA, CIB5670_AWA_5670.ID_TYP);
        CEP.TRC(SCCGWA, CIB5670_AWA_5670.ID_NO);
        CEP.TRC(SCCGWA, CIB5670_AWA_5670.CI_NAME);
        CEP.TRC(SCCGWA, CIB5670_AWA_5670.CI_NO);
        CEP.TRC(SCCGWA, CIB5670_AWA_5670.OUT_AC);
        if (CIB5670_AWA_5670.JRN_NO != 0 
                && CIB5670_AWA_5670.START_DT != 0) {
            CICSAGEN.OPT = 'J';
        } else if (CIB5670_AWA_5670.ID_TYP.trim().length() > 0 
                && CIB5670_AWA_5670.ID_NO.trim().length() > 0 
                && CIB5670_AWA_5670.CI_NAME.trim().length() > 0) {
            CICSAGEN.OPT = 'I';
        } else if (CIB5670_AWA_5670.CI_NO.trim().length() > 0) {
            CICSAGEN.OPT = 'C';
        } else if (CIB5670_AWA_5670.OUT_AC.trim().length() > 0) {
            CICSAGEN.OPT = 'A';
        } else {
            CEP.TRC(SCCGWA, "LACK OF NORMAL INFORMATION");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        CICSAGEN.FUNC = 'B';
        CICSAGEN.JRN_NO = CIB5670_AWA_5670.JRN_NO;
        CICSAGEN.AC_DT = CIB5670_AWA_5670.START_DT;
        CICSAGEN.ID_TYP = CIB5670_AWA_5670.ID_TYP;
        CICSAGEN.ID_NO = CIB5670_AWA_5670.ID_NO;
        CICSAGEN.CI_NAME = CIB5670_AWA_5670.CI_NAME;
        CICSAGEN.CI_NO = CIB5670_AWA_5670.CI_NO;
        CICSAGEN.OUT_AC = CIB5670_AWA_5670.OUT_AC;
        S000_CALL_CIZSAGEN();
    }
    public void S000_CALL_CIZSAGEN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SVR-CIZSAGEN", CICSAGEN);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_MSGID, WS_ERR_INFO, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
