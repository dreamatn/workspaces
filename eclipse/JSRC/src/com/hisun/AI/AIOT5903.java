package com.hisun.AI;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class AIOT5903 {
    String CPN_S_CPRD_MAIN = "AI-S-MAIN-CPRD  ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AICSCPRD AICSCPRD = new AICSCPRD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AIB5900_AWA_5900 AIB5900_AWA_5900;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "AIOT5903 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB5900_AWA_5900>");
        AIB5900_AWA_5900 = (AIB5900_AWA_5900) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_DEL_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_DEL_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICSCPRD);
        AICSCPRD.FUNC = 'D';
        AICSCPRD.DEAL_FLG = AIB5900_AWA_5900.DEAL_FLG;
        AICSCPRD.AC_DATE = AIB5900_AWA_5900.AC_DATE;
        AICSCPRD.AC_NO = AIB5900_AWA_5900.AC_NO;
        AICSCPRD.CI_NO = AIB5900_AWA_5900.CI_NO;
        AICSCPRD.NEW_PROD_CD = AIB5900_AWA_5900.N_PROD;
        AICSCPRD.NEW_CI_TYP = AIB5900_AWA_5900.N_CI_TYP;
        AICSCPRD.NEW_FIN_TYP = AIB5900_AWA_5900.N_FIN;
        AICSCPRD.NEW_AC_TYP = AIB5900_AWA_5900.N_AC_TYP;
        AICSCPRD.NEW_PROP_TYP = AIB5900_AWA_5900.N_PROP;
        AICSCPRD.NEW_TERM_CD = AIB5900_AWA_5900.N_TERM;
        AICSCPRD.NEW_LOAN_TYPE = AIB5900_AWA_5900.N_LOAN;
        AICSCPRD.NEW_WE_FLG = AIB5900_AWA_5900.N_WE_FLG;
        AICSCPRD.NEW_DUTY_FREE = AIB5900_AWA_5900.N_DUTY;
        AICSCPRD.NEW_BUS_MOD = AIB5900_AWA_5900.N_BUS_MD;
        S000_CALL_AIZSCPRD();
    }
    public void S000_CALL_AIZSCPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_CPRD_MAIN, AICSCPRD);
        if (AICSCPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICSCPRD.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}