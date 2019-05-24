package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5602 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    char WS_FUNC_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    DDCSFBID DDCSFBID = new DDCSFBID();
    DDRMST DDRMST = new DDRMST();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    DDB5602_AWA_5602 DDB5602_AWA_5602;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT5602 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5602_AWA_5602>");
        DDB5602_AWA_5602 = (DDB5602_AWA_5602) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B030_TRANS_DATA_OUT();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5602_AWA_5602.AC_NO);
        CEP.TRC(SCCGWA, DDB5602_AWA_5602.TYPE);
        CEP.TRC(SCCGWA, DDB5602_AWA_5602.ORG_TYP);
        CEP.TRC(SCCGWA, DDB5602_AWA_5602.ORG_NAME);
        CEP.TRC(SCCGWA, DDB5602_AWA_5602.EFF_DATE);
        CEP.TRC(SCCGWA, DDB5602_AWA_5602.EXP_DATE);
        if (DDB5602_AWA_5602.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5602_AWA_5602.TYPE == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_STS_TYP_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5602_AWA_5602.ORG_TYP == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_ORG_TYP_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5602_AWA_5602.ORG_TYP == '2' 
            && DDB5602_AWA_5602.BOOK_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_BOOK_NO_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5602_AWA_5602.EFF_DATE == 0) {
            DDB5602_AWA_5602.EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (DDB5602_AWA_5602.EFF_DATE != SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_EFF_DT_MNOT_LT_AC_DT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5602_AWA_5602.EXP_DATE == 0) {
            DDB5602_AWA_5602.EXP_DATE = 99991231;
        }
        CEP.TRC(SCCGWA, DDB5602_AWA_5602.EXP_DATE);
        if (DDB5602_AWA_5602.EXP_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_EXP_DT_MNOT_LT_AC_DT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5602_AWA_5602.ORG_TYP == '2' 
            && DDB5602_AWA_5602.TYPE == '1' 
            && DDB5602_AWA_5602.SLAW_NM1.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_LAW_NM_M_INPUT;
            WS_FLD_NO = DDB5602_AWA_5602.SLAW_NM1_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5602_AWA_5602.ORG_TYP == '2' 
            && DDB5602_AWA_5602.TYPE == '1' 
            && DDB5602_AWA_5602.SLAW_NO1.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_LAW_NO_M_INPUT;
            WS_FLD_NO = DDB5602_AWA_5602.SLAW_NO1_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5602_AWA_5602.TYPE != '1' 
            && DDB5602_AWA_5602.TYPE != '2') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_STS_TYP_INVALID;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B030_TRANS_DATA_OUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSFBID);
        DDCSFBID.KEY.AC_NO = DDB5602_AWA_5602.AC_NO;
        DDCSFBID.KEY.REF_NO = DDB5602_AWA_5602.REF_NO;
        DDCSFBID.KEY.TYPE = DDB5602_AWA_5602.TYPE;
        DDCSFBID.STS = DDB5602_AWA_5602.STS;
        DDCSFBID.AC_NAME = DDB5602_AWA_5602.AC_NAME;
        DDCSFBID.ORG_TYP = DDB5602_AWA_5602.ORG_TYP;
        DDCSFBID.BOOK_NO = DDB5602_AWA_5602.BOOK_NO;
        DDCSFBID.ORG_NAME = DDB5602_AWA_5602.ORG_NAME;
        DDCSFBID.EFF_DATE = DDB5602_AWA_5602.EFF_DATE;
        DDCSFBID.EXP_DATE = DDB5602_AWA_5602.EXP_DATE;
        DDCSFBID.REASON = DDB5602_AWA_5602.REASON;
        DDCSFBID.CRT_TL = SCCGWA.COMM_AREA.TL_ID;
        DDCSFBID.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDCSFBID.CRT_AUTH_TL = SCCGWA.COMM_AREA.SUP1_ID;
        DDCSFBID.SLAW_NM1 = DDB5602_AWA_5602.SLAW_NM1;
        DDCSFBID.SLAW_NO1 = DDB5602_AWA_5602.SLAW_NO1;
        DDCSFBID.SLAW_NM2 = DDB5602_AWA_5602.SLAW_NM2;
        DDCSFBID.SLAW_NO2 = DDB5602_AWA_5602.SLAW_NO2;
        DDCSFBID.ORG_TYPE = DDB5602_AWA_5602.ORG_TYPE;
        DDCSFBID.CCY = DDB5602_AWA_5602.CCY;
        DDCSFBID.CCY_TYP = DDB5602_AWA_5602.CCY_TYP;
        DDCSFBID.AC_SEQ = DDB5602_AWA_5602.AC_SEQ;
        CEP.TRC(SCCGWA, DDB5602_AWA_5602.AC_SEQ);
        CEP.TRC(SCCGWA, DDB5602_AWA_5602.ORG_TYPE);
        CEP.TRC(SCCGWA, DDB5602_AWA_5602.TYPE);
        if (DDB5602_AWA_5602.TYPE == '1') {
            DDCSFBID.FUNC = 'F';
            S000_CALL_DDZSFBID();
        } else {
            if (DDB5602_AWA_5602.TYPE == '2' 
                && DDB5602_AWA_5602.ORG_TYP == '1') {
                DDCSFBID.FUNC = 'C';
                S000_CALL_DDZSFBID();
            } else {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ORGAN_NOT_DEBIT;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
    }
    public void S000_CALL_DDZSFBID() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-DDZSFBID", DDCSFBID);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
