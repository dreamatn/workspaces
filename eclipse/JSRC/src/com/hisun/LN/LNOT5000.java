package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT5000 {
    String WS_ERR_MSG = " ";
    short WS_I = 0;
    short WS_ADJ_NUM = 0;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    LNCSPART LNCSPART = new LNCSPART();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    LNB5000_AWA_5000 LNB5000_AWA_5000;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNOT5000 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB5000_AWA_5000>");
        LNB5000_AWA_5000 = (LNB5000_AWA_5000) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_MAINTENANCE_PARTBK_INF();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            if (LNB5000_AWA_5000.PART_INF[WS_I-1].ADJ_BANK == 'Y') {
                WS_ADJ_NUM += 1;
            }
        }
        if (WS_ADJ_NUM != 1) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ADJ_NUM_MUST_ONE;
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_MAINTENANCE_PARTBK_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSPART);
        LNCSPART.FUNC = LNB5000_AWA_5000.FUN_CD;
        LNCSPART.DATA.LN_CTANO = LNB5000_AWA_5000.LN_CTANO;
        LNCSPART.DATA.LN_AC = LNB5000_AWA_5000.LN_AC;
        LNCSPART.DATA.PAPER_NM = LNB5000_AWA_5000.PAPER_NM;
        LNCSPART.DATA.CCY = LNB5000_AWA_5000.CCY;
        LNCSPART.DATA.AMT = LNB5000_AWA_5000.AMT;
        LNCSPART.DATA.MAIN_BR = LNB5000_AWA_5000.MAIN_BR;
        LNCSPART.DATA.SYN_TYP = LNB5000_AWA_5000.SYN_TYP;
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            LNCSPART.DATA.PART_INF[WS_I-1].PART_NO = LNB5000_AWA_5000.PART_INF[WS_I-1].PART_NO;
            LNCSPART.DATA.PART_INF[WS_I-1].PART_BR = LNB5000_AWA_5000.PART_INF[WS_I-1].PART_BR;
            LNCSPART.DATA.PART_INF[WS_I-1].PART_NM = LNB5000_AWA_5000.PART_INF[WS_I-1].PART_NM;
            LNCSPART.DATA.PART_INF[WS_I-1].REL_TYPE = LNB5000_AWA_5000.PART_INF[WS_I-1].REL_TYPE;
            LNCSPART.DATA.PART_INF[WS_I-1].LOC_BANK = LNB5000_AWA_5000.PART_INF[WS_I-1].LOC_BANK;
            LNCSPART.DATA.PART_INF[WS_I-1].ADJ_BANK = LNB5000_AWA_5000.PART_INF[WS_I-1].ADJ_BANK;
            LNCSPART.DATA.PART_INF[WS_I-1].PART_PCT = LNB5000_AWA_5000.PART_INF[WS_I-1].PART_PCT;
            LNCSPART.DATA.PART_INF[WS_I-1].PART_AMT = LNB5000_AWA_5000.PART_INF[WS_I-1].PART_AMT;
            LNCSPART.DATA.PART_INF[WS_I-1].AC_TYP = LNB5000_AWA_5000.PART_INF[WS_I-1].AC_TYP;
            LNCSPART.DATA.PART_INF[WS_I-1].PART_AC = LNB5000_AWA_5000.PART_INF[WS_I-1].PART_AC;
        }
        S000_CALL_LNZSPART();
    }
    public void S000_CALL_LNZSPART() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-PART-BANK", LNCSPART);
        if (LNCSPART.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSPART.RC);
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
