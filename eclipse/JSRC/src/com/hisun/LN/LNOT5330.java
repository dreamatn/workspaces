package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT5330 {
    char K_FUNC_QUERY = 'I';
    char K_FUNC_ADD = 'A';
    char K_FUNC_UPD = 'M';
    char K_FUNC_DEL = 'D';
    char K_USE_CPND = 'Y';
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CONT_BOOK_BR = 0;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    LNCCONTM LNCCONTM = new LNCCONTM();
    LNCSRATE LNCSRATE = new LNCSRATE();
    SCCGWA SCCGWA;
    LNCI5330 LNCI5330;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNOT5330 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        LNCI5330 = new LNCI5330();
        IBS.init(SCCGWA, LNCI5330);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, LNCI5330);
        LNCSRATE.RC.RC_APP = "LN";
        LNCSRATE.RC.RC_RTNCODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK_INPUT();
        B100_FUNC_MAIN();
    }
    public void B000_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNCI5330.CONT_NO.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_CONT_NO_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        B001_CHK_FTA_TYP();
    }
    public void B100_FUNC_MAIN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSRATE);
        LNCSRATE.DATA.CONTRACT_NO = LNCI5330.CONT_NO;
        LNCSRATE.DATA.SUB_CTA_NO = 0;
        LNCSRATE.DATA.RAT_CHG_DT = LNCI5330.VAL_DT;
        LNCSRATE.DATA.CPND_USE = LNCI5330.CPND_USE;
        LNCSRATE.DATA.RATE_KIND_N = LNCI5330.INT_RATT;
        LNCSRATE.DATA.INT_RAT_N = LNCI5330.INT_IRA;
        LNCSRATE.DATA.RATE_KIND_O = LNCI5330.PEN_RATT;
        LNCSRATE.DATA.INT_RAT_O = LNCI5330.PEN_IRA;
        LNCSRATE.DATA.RATE_KIND_L = LNCI5330.CPNDRATT;
        LNCSRATE.DATA.INT_RAT_L = LNCI5330.CPND_IRA;
        LNCSRATE.DATA.RATE_KIND_P = LNCI5330.ABUSRATT;
        LNCSRATE.DATA.INT_RAT_P = LNCI5330.ABUS_IRA;
        LNCSRATE.FUNC_TYP = LNCI5330.FUNC_TYP;
        S000_CALL_LNZSRATE();
        CEP.TRC(SCCGWA, "TRANS FINISHED");
    }
    public void B001_CHK_FTA_TYP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCONTM);
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCI5330.CONT_NO;
        LNCCONTM.FUNC = '3';
        S000_CALL_LNZCONTM();
        WS_CONT_BOOK_BR = LNCCONTM.REC_DATA.BOOK_BR;
    }
    public void S000_CALL_LNZCONTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-CONT-MAINT", LNCCONTM);
        if (LNCCONTM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCCONTM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_LNZSRATE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SEV-RATE-MAINT", LNCSRATE);
        if (LNCSRATE.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSRATE.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_ERR_MSG);
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
