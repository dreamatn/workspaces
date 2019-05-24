package com.hisun.AI;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class AIOT5902 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String CPN_S_CPRD_MAIN = "AI-S-MAIN-CPRD  ";
    String PGM_SCSSCLDT = "SCSSCLDT";
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
        CEP.TRC(SCCGWA, "AIOT5902 return!");
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
        B020_UPD_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_UPD_REC_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIB5900_AWA_5900.AC_DATE);
        CEP.TRC(SCCGWA, AIB5900_AWA_5900.AC_NO);
        CEP.TRC(SCCGWA, AIB5900_AWA_5900.CI_NO);
        CEP.TRC(SCCGWA, AIB5900_AWA_5900.N_PROD);
        CEP.TRC(SCCGWA, AIB5900_AWA_5900.N_CI_TYP);
        CEP.TRC(SCCGWA, AIB5900_AWA_5900.N_FIN);
        CEP.TRC(SCCGWA, AIB5900_AWA_5900.N_AC_TYP);
        CEP.TRC(SCCGWA, AIB5900_AWA_5900.N_PROP);
        CEP.TRC(SCCGWA, AIB5900_AWA_5900.N_TERM);
        CEP.TRC(SCCGWA, AIB5900_AWA_5900.N_LOAN);
        CEP.TRC(SCCGWA, AIB5900_AWA_5900.N_WE_FLG);
        CEP.TRC(SCCGWA, AIB5900_AWA_5900.N_DUTY);
        CEP.TRC(SCCGWA, AIB5900_AWA_5900.N_BUS_MD);
        IBS.init(SCCGWA, AICSCPRD);
        AICSCPRD.FUNC = 'M';
        AICSCPRD.DEAL_FLG = AIB5900_AWA_5900.DEAL_FLG;
        AICSCPRD.AC_DATE = AIB5900_AWA_5900.AC_DATE;
        AICSCPRD.AC_NO = AIB5900_AWA_5900.AC_NO;
        CEP.TRC(SCCGWA, AIB5900_AWA_5900.ACAC_SEQ);
        if (AICSCPRD.OTH_AC == null) AICSCPRD.OTH_AC = "";
        JIBS_tmp_int = AICSCPRD.OTH_AC.length();
        for (int i=0;i<25-JIBS_tmp_int;i++) AICSCPRD.OTH_AC += " ";
        JIBS_tmp_str[0] = "" + AIB5900_AWA_5900.ACAC_SEQ;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        AICSCPRD.OTH_AC = JIBS_tmp_str[0] + AICSCPRD.OTH_AC.substring(8);
        CEP.TRC(SCCGWA, AICSCPRD.OTH_AC);
        AICSCPRD.BILL_NO = AIB5900_AWA_5900.BILL_NO;
        AICSCPRD.AP_MMO = AIB5900_AWA_5900.AP_MMO;
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
        AICSCPRD.OLD_PROD_CD = AIB5900_AWA_5900.O_PROD;
        AICSCPRD.OLD_CI_TYP = AIB5900_AWA_5900.O_CI_TYP;
        AICSCPRD.OLD_FIN_TYP = AIB5900_AWA_5900.O_FIN;
        AICSCPRD.OLD_AC_TYP = AIB5900_AWA_5900.O_AC_TYP;
        AICSCPRD.OLD_PROP_TYP = AIB5900_AWA_5900.O_PROP;
        AICSCPRD.OLD_TERM_CD = AIB5900_AWA_5900.O_TERM;
        AICSCPRD.OLD_LOAN_TYPE = AIB5900_AWA_5900.O_LOAN;
        AICSCPRD.OLD_WE_FLG = AIB5900_AWA_5900.O_WE_FLG;
        AICSCPRD.OLD_DUTY_FREE = AIB5900_AWA_5900.O_DUTY;
        AICSCPRD.OLD_BUS_MOD = AIB5900_AWA_5900.O_BUS_MD;
        AICSCPRD.OLD_GL_MSTNO = AIB5900_AWA_5900.O_GLMST;
        S000_CALL_AIZSCPRD();
    }
    public void S000_CALL_AIZSCPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_CPRD_MAIN, AICSCPRD);
        if (AICSCPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = "" + AICSCPRD.RC.RC_CODE;
            JIBS_tmp_int = WS_ERR_MSG.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_ERR_MSG = "0" + WS_ERR_MSG;
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
