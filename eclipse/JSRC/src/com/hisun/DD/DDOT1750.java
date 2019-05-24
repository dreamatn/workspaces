package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT1750 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDCSCSDX DDCSCSDX = new DDCSCSDX();
    SCCGWA SCCGWA;
    DDB8840_AWA_8840 DDB8840_AWA_8840;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT1750 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB8840_AWA_8840>");
        DDB8840_AWA_8840 = (DDB8840_AWA_8840) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B030_TRANS_DATA_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB8840_AWA_8840.AC);
        CEP.TRC(SCCGWA, DDB8840_AWA_8840.CCY);
        CEP.TRC(SCCGWA, DDB8840_AWA_8840.CCY_TYPE);
        CEP.TRC(SCCGWA, DDB8840_AWA_8840.BV_TYP);
        CEP.TRC(SCCGWA, DDB8840_AWA_8840.CASH_AMT);
        CEP.TRC(SCCGWA, DDB8840_AWA_8840.OTH_AC);
        CEP.TRC(SCCGWA, DDB8840_AWA_8840.OTH_CCY);
        CEP.TRC(SCCGWA, DDB8840_AWA_8840.CCY_TYP1);
        CEP.TRC(SCCGWA, DDB8840_AWA_8840.DEP_AMT);
        if (DDB8840_AWA_8840.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB8840_AWA_8840.BV_TYP == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BV_TYP_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB8840_AWA_8840.CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB8840_AWA_8840.CCY.trim().length() > 0 
            && DDB8840_AWA_8840.CCY_TYPE == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_TYPE_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB8840_AWA_8840.CCY.trim().length() == 0 
            && DDB8840_AWA_8840.CCY_TYPE != ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB8840_AWA_8840.CASH_AMT <= 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AMT_MST_IPT;
            S000_ERR_MSG_PROC();
        }
        if (DDB8840_AWA_8840.OTH_AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB8840_AWA_8840.OTH_CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB8840_AWA_8840.OTH_CCY.trim().length() > 0 
            && DDB8840_AWA_8840.CCY_TYP1 == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_TYPE_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB8840_AWA_8840.OTH_CCY.trim().length() == 0 
            && DDB8840_AWA_8840.CCY_TYP1 != ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDB8840_AWA_8840.DEP_AMT <= 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AMT_MST_IPT;
            S000_ERR_MSG_PROC();
        }
        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B030_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSCSDX);
        DDCSCSDX.CASH_NO = DDB8840_AWA_8840.CASH_NO;
        DDCSCSDX.BV_TYP = DDB8840_AWA_8840.BV_TYP;
        DDCSCSDX.AC = DDB8840_AWA_8840.AC;
        DDCSCSDX.PSBK_NO = DDB8840_AWA_8840.PSBK_NO;
        DDCSCSDX.CCY = DDB8840_AWA_8840.CCY;
        DDCSCSDX.CCY_TYPE = DDB8840_AWA_8840.CCY_TYPE;
        DDCSCSDX.CASH_AMT = DDB8840_AWA_8840.CASH_AMT;
        DDCSCSDX.PAY_TYPE = DDB8840_AWA_8840.PAY_TYPE;
        DDCSCSDX.CHQ_TYPE = DDB8840_AWA_8840.CHQ_TYPE;
        DDCSCSDX.CHQ_NO = DDB8840_AWA_8840.CHQ_NO;
        DDCSCSDX.CHQ_ISS_DATE = DDB8840_AWA_8840.CHQ_DT;
        DDCSCSDX.PSWD = DDB8840_AWA_8840.PSWD;
        DDCSCSDX.TX_MMO = "A026";
        DDCSCSDX.TX_RMK = DDB8840_AWA_8840.TX_RMK;
        DDCSCSDX.REMARKS = DDB8840_AWA_8840.REMARKS;
        DDCSCSDX.OTH_AC = DDB8840_AWA_8840.OTH_AC;
        DDCSCSDX.OTH_PSBK_NO = DDB8840_AWA_8840.OTH_PSBK;
        DDCSCSDX.OTH_CCY = DDB8840_AWA_8840.OTH_CCY;
        DDCSCSDX.OTH_CCY_TYPE = DDB8840_AWA_8840.CCY_TYP1;
        DDCSCSDX.BV_CD = DDB8840_AWA_8840.BV_CD;
        DDCSCSDX.DEP_AMT = DDB8840_AWA_8840.DEP_AMT;
        DDCSCSDX.OTH_TX_MMO = DDB8840_AWA_8840.TX_MMO1;
        DDCSCSDX.OTH_TX_RMK = DDB8840_AWA_8840.TX_RMK1;
        DDCSCSDX.OTH_REMARKS = DDB8840_AWA_8840.REMARKS1;
        S000_CALL_DDZSCSDX();
    }
    public void S000_CALL_DDZSCSDX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-DDZSCSDX", DDCSCSDX);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
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
