package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT5210 {
    int K_ZERO = 0;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_ERR_INFO = " ";
    LNOT5210_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new LNOT5210_WS_TEMP_VARIABLE();
    LNOT5210_WS_MSG_INFO WS_MSG_INFO = new LNOT5210_WS_MSG_INFO();
    LNOT5210_WS_OUTPUT_LIST WS_OUTPUT_LIST = new LNOT5210_WS_OUTPUT_LIST();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    LNCENSCH LNCENSCH = new LNCENSCH();
    SCCGWA SCCGWA;
    LNB5210_AWA_5210 LNB5210_AWA_5210;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "LNOT5210 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB5210_AWA_5210>");
        LNB5210_AWA_5210 = (LNB5210_AWA_5210) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        LNCENSCH.RC.RC_APP = "LN";
        LNCENSCH.RC.RC_RTNCODE = 0;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_CALL_SVR_PROCESS();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNB5210_AWA_5210.CTA_NO);
        CEP.TRC(SCCGWA, LNB5210_AWA_5210.CI_NO);
        CEP.TRC(SCCGWA, LNB5210_AWA_5210.CI_S_NAM);
        CEP.TRC(SCCGWA, LNB5210_AWA_5210.PRD_TYP);
        CEP.TRC(SCCGWA, LNB5210_AWA_5210.CCY);
        CEP.TRC(SCCGWA, LNB5210_AWA_5210.PRIN_AMT);
        CEP.TRC(SCCGWA, LNB5210_AWA_5210.OS_BAL);
        CEP.TRC(SCCGWA, LNB5210_AWA_5210.REPY_TYP);
        CEP.TRC(SCCGWA, LNB5210_AWA_5210.PFN_DAT);
        CEP.TRC(SCCGWA, LNB5210_AWA_5210.PFR_TERM);
        CEP.TRC(SCCGWA, LNB5210_AWA_5210.PFR_TYP);
        CEP.TRC(SCCGWA, LNB5210_AWA_5210.REPY_AMT);
        CEP.TRC(SCCGWA, LNB5210_AWA_5210.IFN_DAT);
        CEP.TRC(SCCGWA, LNB5210_AWA_5210.IFR_TERM);
        CEP.TRC(SCCGWA, LNB5210_AWA_5210.IFR_TYP);
        CEP.TRC(SCCGWA, LNB5210_AWA_5210.TRAN_SEQ);
        if (LNB5210_AWA_5210.REPY_TYP == 'C') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_REPAY_TYPE;
            WS_FLD_NO = LNB5210_AWA_5210.REPY_TYP_NO;
            S000_ERR_MSG_PROC();
        }
        if ((LNB5210_AWA_5210.PFN_DAT > 0) 
            || (LNB5210_AWA_5210.PFR_TERM > 0) 
            || (LNB5210_AWA_5210.PFR_TYP > SPACE)) {
            if (LNB5210_AWA_5210.PFN_DAT <= 0) {
                CEP.TRC(SCCGWA, "111111111111");
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN5701;
                WS_FLD_NO = LNB5210_AWA_5210.PFN_DAT_NO;
                S000_ERR_MSG_PROC();
            }
            if (LNB5210_AWA_5210.PFR_TERM <= 0) {
                CEP.TRC(SCCGWA, "222222222222");
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN5701;
                WS_FLD_NO = LNB5210_AWA_5210.PFR_TERM_NO;
                S000_ERR_MSG_PROC();
            }
            if (LNB5210_AWA_5210.PFR_TYP <= SPACE) {
                CEP.TRC(SCCGWA, "333333333333");
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN5701;
                WS_FLD_NO = LNB5210_AWA_5210.PFR_TYP_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if ((LNB5210_AWA_5210.IFN_DAT > 0) 
            || (LNB5210_AWA_5210.IFR_TERM > 0) 
            || (LNB5210_AWA_5210.IFR_TYP > SPACE)) {
            if (LNB5210_AWA_5210.IFN_DAT <= 0) {
                CEP.TRC(SCCGWA, "444444444444");
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN5701;
                WS_FLD_NO = LNB5210_AWA_5210.PFN_DAT_NO;
                S000_ERR_MSG_PROC();
            }
            if (LNB5210_AWA_5210.IFR_TERM <= 0) {
                CEP.TRC(SCCGWA, "555555555555");
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN5701;
                WS_FLD_NO = LNB5210_AWA_5210.PFR_TERM_NO;
                S000_ERR_MSG_PROC();
            }
            if (LNB5210_AWA_5210.IFR_TYP <= SPACE) {
                CEP.TRC(SCCGWA, "666666666666");
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN5701;
                WS_FLD_NO = LNB5210_AWA_5210.PFR_TYP_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if ((LNB5210_AWA_5210.REPY_TYP == 'P') 
            && (LNB5210_AWA_5210.IFN_DAT > 0)) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_NOT_ALLOW_INPUT;
            WS_FLD_NO = LNB5210_AWA_5210.IFN_DAT_NO;
            S000_ERR_MSG_PROC();
        }
        if ((LNB5210_AWA_5210.REPY_TYP == 'I') 
            && (LNB5210_AWA_5210.PFN_DAT > 0)) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_NOT_ALLOW_INPUT;
            WS_FLD_NO = LNB5210_AWA_5210.PFN_DAT_NO;
            S000_ERR_MSG_PROC();
        }
        if ((LNB5210_AWA_5210.REPY_TYP == ' ') 
            && (LNB5210_AWA_5210.IFN_DAT > 0) 
            && (LNB5210_AWA_5210.IFR_TERM > 0) 
            && (LNB5210_AWA_5210.IFR_TYP > SPACE) 
            && (LNB5210_AWA_5210.PFR_TERM == 0) 
            && (LNB5210_AWA_5210.PFR_TYP == ' ') 
            && (LNB5210_AWA_5210.PFN_DAT == 0) 
            && (LNB5210_AWA_5210.REPY_AMT == 0)) {
            CEP.TRC(SCCGWA, "777777777777");
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN5701;
            WS_FLD_NO = LNB5210_AWA_5210.REPY_TYP_NO;
            S000_ERR_MSG_PROC();
        }
        if ((LNB5210_AWA_5210.REPY_TYP == ' ') 
            && (LNB5210_AWA_5210.IFN_DAT == 0) 
            && (LNB5210_AWA_5210.IFR_TERM == 0) 
            && (LNB5210_AWA_5210.IFR_TYP == ' ') 
            && (LNB5210_AWA_5210.PFR_TERM > 0) 
            && (LNB5210_AWA_5210.PFR_TYP > SPACE) 
            && (LNB5210_AWA_5210.PFN_DAT > 0)) {
            CEP.TRC(SCCGWA, "999999999999");
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN5701;
            WS_FLD_NO = LNB5210_AWA_5210.REPY_TYP_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_CALL_SVR_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCENSCH);
        LNCENSCH.COMM_DATA.CTA_NO = LNB5210_AWA_5210.CTA_NO;
        LNCENSCH.COMM_DATA.SUB_CTA_NO = K_ZERO;
        LNCENSCH.COMM_DATA.CI_S_NAM = LNB5210_AWA_5210.CI_S_NAM;
        LNCENSCH.COMM_DATA.PRD_TYP = LNB5210_AWA_5210.PRD_TYP;
        LNCENSCH.COMM_DATA.CCY = LNB5210_AWA_5210.CCY;
        LNCENSCH.COMM_DATA.PRIN_AMT = LNB5210_AWA_5210.PRIN_AMT;
        LNCENSCH.COMM_DATA.OS_BAL = LNB5210_AWA_5210.OS_BAL;
        LNCENSCH.COMM_DATA.REPY_TYP = LNB5210_AWA_5210.REPY_TYP;
        LNCENSCH.COMM_DATA.REPY_AMT = LNB5210_AWA_5210.REPY_AMT;
        LNCENSCH.COMM_DATA.PFNP_DAT = LNB5210_AWA_5210.PFN_DAT;
        LNCENSCH.COMM_DATA.PFRE_TERM = LNB5210_AWA_5210.PFR_TERM;
        LNCENSCH.COMM_DATA.PFRE_TYP = LNB5210_AWA_5210.PFR_TYP;
        LNCENSCH.COMM_DATA.IFNP_DAT = LNB5210_AWA_5210.IFN_DAT;
        LNCENSCH.COMM_DATA.IFRE_TERM = LNB5210_AWA_5210.IFR_TERM;
        LNCENSCH.COMM_DATA.IFRE_TYP = LNB5210_AWA_5210.IFR_TYP;
        LNCENSCH.COMM_DATA.PAGE_ROW = LNB5210_AWA_5210.PAGE_ROW;
        LNCENSCH.COMM_DATA.PAGE_NUM = LNB5210_AWA_5210.PAGE_NUM;
        S000_CALL_LNZENSCH();
    }
    public void S000_CALL_LNZENSCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-ENTRY-SCH", LNCENSCH);
        CEP.TRC(SCCGWA, LNCENSCH.RC);
        if (LNCENSCH.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCENSCH.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
