package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT5213 {
    char K_PRINCIPAL = 'P';
    char K_PRINCIINT = 'I';
    String WS_ERR_MSG = " ";
    LNOT5213_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new LNOT5213_WS_TEMP_VARIABLE();
    LNOT5213_WS_MSG_INFO WS_MSG_INFO = new LNOT5213_WS_MSG_INFO();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCSUBS SCCSUBS = new SCCSUBS();
    LNCMTSCH LNCMTSCH = new LNCMTSCH();
    SCCGWA SCCGWA;
    LNB5210_AWA_5210 LNB5210_AWA_5210;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "LNOT5213 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB5210_AWA_5210>");
        LNB5210_AWA_5210 = (LNB5210_AWA_5210) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_CALL_SVR_PROCESS();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNB5210_AWA_5210.CTA_NO);
        CEP.TRC(SCCGWA, LNB5210_AWA_5210.TRAN_SEQ);
        CEP.TRC(SCCGWA, LNB5210_AWA_5210.REPY_TYP);
        CEP.TRC(SCCGWA, LNB5210_AWA_5210.LIST_TYP);
        CEP.TRC(SCCGWA, LNB5210_AWA_5210.TERM_NO);
        CEP.TRC(SCCGWA, LNB5210_AWA_5210.VAL_DAT);
        CEP.TRC(SCCGWA, LNB5210_AWA_5210.DUE_DAT);
        CEP.TRC(SCCGWA, LNB5210_AWA_5210.RATE);
        CEP.TRC(SCCGWA, LNB5210_AWA_5210.REPY_AMT);
        CEP.TRC(SCCGWA, LNB5210_AWA_5210.RMK);
        if (LNB5210_AWA_5210.CTA_NO.compareTo(SPACE) <= 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_CONT_NO_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if ((LNB5210_AWA_5210.REPY_TYP > SPACE) 
            && (LNB5210_AWA_5210.REPY_TYP != LNB5210_AWA_5210.LIST_TYP)) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN5732;
            S000_ERR_MSG_PROC();
        }
        if (LNB5210_AWA_5210.LIST_TYP == K_PRINCIPAL 
            && LNB5210_AWA_5210.REPY_AMT == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_PAY_P_AMT_ZERO;
            S000_ERR_MSG_PROC();
        }
        if (LNB5210_AWA_5210.LIST_TYP == K_PRINCIINT 
            && LNB5210_AWA_5210.REPY_AMT != 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_PAY_I_AMT_NOT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_CALL_SVR_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCMTSCH);
        LNCMTSCH.COMM_DATA.FUNC = 'A';
        LNCMTSCH.COMM_DATA.CTA_NO = LNB5210_AWA_5210.CTA_NO;
        LNCMTSCH.COMM_DATA.TRAN_SEQ = LNB5210_AWA_5210.TRAN_SEQ;
        LNCMTSCH.COMM_DATA.REPY_TYP = LNB5210_AWA_5210.REPY_TYP;
        LNCMTSCH.COMM_DATA.LIST_TYP = LNB5210_AWA_5210.LIST_TYP;
        LNCMTSCH.COMM_DATA.TERM = LNB5210_AWA_5210.TERM_NO;
        LNCMTSCH.COMM_DATA.VAL_DT = LNB5210_AWA_5210.VAL_DAT;
        LNCMTSCH.COMM_DATA.DUE_DT = LNB5210_AWA_5210.DUE_DAT;
        LNCMTSCH.COMM_DATA.RATE = LNB5210_AWA_5210.RATE;
        LNCMTSCH.COMM_DATA.AMOUNT = LNB5210_AWA_5210.REPY_AMT;
        LNCMTSCH.COMM_DATA.REMARK = LNB5210_AWA_5210.RMK;
        S000_CALL_LNZMTSCH();
    }
    public void S000_CALL_LNZMTSCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-MAINTIAN-SCHT", LNCMTSCH);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_MSG_INFO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
