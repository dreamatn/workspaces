package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.DC.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSCOMI {
    String K_OUTPUT_FMT = "DD666";
    String PGM_SCSSCLDT = "SCSSCLDT";
    int K_HEAD_BR = 101000;
    short K_360_DAYS = 360;
    short K_365_DAYS = 365;
    short K_366_DAYS = 366;
    short K_30_DAYS = 30;
    short K_30E_DAYS = 31;
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    double WS_DEP_INT = 0;
    double WS_INT = 0;
    double WS_TOT_AMT = 0;
    double WS_TAX = 0;
    double WS_INT_RATE = 0;
    double WS_TAX_RATE = 0;
    short WS_CALR_STD = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCITAXG BPCITAXG = new BPCITAXG();
    DDCOCOMI DDCOCOMI = new DDCOCOMI();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDVMRAT DDVMRAT = new DDVMRAT();
    BPCCINTI BPCCINTI = new BPCCINTI();
    SCCCLDT SCCCLDT = new SCCCLDT();
    DDRMST DDRMST = new DDRMST();
    SCCGWA SCCGWA;
    DDCSCOMI DDCSCOMI;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, DDCSCOMI DDCSCOMI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSCOMI = DDCSCOMI;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZSCOMI return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_DATA_VALIDITY();
        B050_GET_PROD_RATE_PROC();
        B070_GET_RATE_PROC();
        B090_TEST_COMP_INT_PROC();
        B110_GET_EXP_DATE_PROC();
        B130_TRANS_DATA_OUTPUT();
    }
    public void B010_CHECK_DATA_VALIDITY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSCOMI.PRD_CODE);
        CEP.TRC(SCCGWA, DDCSCOMI.AMT);
        CEP.TRC(SCCGWA, DDCSCOMI.DAYS);
        CEP.TRC(SCCGWA, DDCSCOMI.CCY);
        CEP.TRC(SCCGWA, DDCSCOMI.TR_DT);
        CEP.TRC(SCCGWA, "INPUT CHECK");
        if (DDCSCOMI.AMT == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AMT_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDCSCOMI.DAYS == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_DAYS_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDCSCOMI.CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDCSCOMI.TR_DT == 0) {
            DDCSCOMI.TR_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
    }
    public void B050_GET_PROD_RATE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIQPRD);
        IBS.init(SCCGWA, DDVMPRD);
        DDCIQPRD.INPUT_DATA.PROD_CODE = DDCSCOMI.PRD_CODE;
        DDCIQPRD.INPUT_DATA.CCY = DDCSCOMI.CCY;
        CEP.TRC(SCCGWA, DDCSCOMI.PRD_CODE);
        CEP.TRC(SCCGWA, DDCIQPRD.INPUT_DATA.PROD_CODE);
        CEP.TRC(SCCGWA, DDCIQPRD.INPUT_DATA.CCY);
        DDCIQPRD.DDVMPRD_PTR = DDVMPRD;
        DDCIQPRD.DDVMRAT_PTR = DDVMRAT;
        S000_CALL_DDZIQPRD();
    }
    public void B070_GET_RATE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = DDCSCOMI.CCY;
        S000_CALL_BPZQCCY();
        CEP.TRC(SCCGWA, BPCQCCY.DATA.CALR_STD);
        if (BPCQCCY.DATA.CALR_STD.equalsIgnoreCase("01")) {
            WS_CALR_STD = K_360_DAYS;
        } else if (BPCQCCY.DATA.CALR_STD.equalsIgnoreCase("02")) {
            WS_CALR_STD = K_365_DAYS;
        } else if (BPCQCCY.DATA.CALR_STD.equalsIgnoreCase("03")) {
            WS_CALR_STD = K_366_DAYS;
        } else if (BPCQCCY.DATA.CALR_STD.equalsIgnoreCase("04")) {
            WS_CALR_STD = K_30_DAYS;
        } else if (BPCQCCY.DATA.CALR_STD.equalsIgnoreCase("05")) {
            WS_CALR_STD = K_30E_DAYS;
        }
        CEP.TRC(SCCGWA, WS_CALR_STD);
        IBS.init(SCCGWA, BPCCINTI);
        BPCCINTI.FUNC = 'I';
        BPCCINTI.BASE_INFO.BR = SCCGWA.COMM_AREA.HQT_BANK;
        BPCCINTI.BASE_INFO.CCY = DDCSCOMI.CCY;
        BPCCINTI.BASE_INFO.BASE_TYP = DDVMRAT.VAL.TIER[1-1].TIER_IR[1-1].INT_RBAS;
        BPCCINTI.BASE_INFO.TENOR = DDVMRAT.VAL.TIER[1-1].TIER_IR[1-1].INT_RCD;
        BPCCINTI.BASE_INFO.BASE_TYP = "A02";
        BPCCINTI.BASE_INFO.TENOR = "M003";
        BPCCINTI.BASE_INFO.DT = DDCSCOMI.TR_DT;
        CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.BASE_TYP);
        CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.TENOR);
        S000_CALL_BPZCINTI();
        WS_INT_RATE = BPCCINTI.BASE_INFO.RATE;
        IBS.init(SCCGWA, BPCITAXG);
        BPCITAXG.TAX_TYP = DDVMPRD.VAL.TAX_RATE_BASE;
        BPCITAXG.VAL_TYP = '0';
        BPCITAXG.CCY = DDCSCOMI.CCY;
        BPCITAXG.BR = K_HEAD_BR;
        BPCITAXG.ST_DT = DDCSCOMI.TR_DT;
        BPCITAXG.EN_DT = DDCSCOMI.TR_DT;
        S000_CALL_BPZITAXG();
        WS_TAX_RATE = BPCITAXG.OUTPUT.TAXR_GROUP[1-1].TAX_VAL;
        CEP.TRC(SCCGWA, BPCITAXG.OUTPUT.TAXR_GROUP[1-1].TAX_VAL);
    }
    public void B090_TEST_COMP_INT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSCOMI.AMT);
        CEP.TRC(SCCGWA, DDCSCOMI.DAYS);
        WS_DEP_INT = DDCSCOMI.AMT * DDCSCOMI.DAYS * WS_INT_RATE / 100 / WS_CALR_STD;
        WS_TAX = WS_DEP_INT * WS_TAX_RATE;
        CEP.TRC(SCCGWA, WS_DEP_INT);
        CEP.TRC(SCCGWA, WS_TAX);
        WS_TOT_AMT = DDCSCOMI.AMT + WS_DEP_INT - WS_TAX;
        WS_INT = WS_DEP_INT - WS_TAX;
    }
    public void B110_GET_EXP_DATE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = DDCSCOMI.TR_DT;
        SCCCLDT.DAYS = DDCSCOMI.DAYS;
        S000_CALL_SCSSCLDT();
        CEP.TRC(SCCGWA, SCCCLDT.DATE2);
    }
    public void B130_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOCOMI);
        DDCOCOMI.AMT = DDCSCOMI.AMT;
        DDCOCOMI.DEP_INT = WS_DEP_INT;
        CEP.TRC(SCCGWA, WS_DEP_INT);
        DDCOCOMI.INT = WS_INT;
        CEP.TRC(SCCGWA, WS_INT);
        DDCOCOMI.TOT_AMT = WS_TOT_AMT;
        DDCOCOMI.DEP_RATE = WS_INT_RATE;
        DDCOCOMI.TAX = WS_TAX;
        CEP.TRC(SCCGWA, DDCOCOMI.DEP_RATE);
        DDCOCOMI.MT_DT = SCCCLDT.DATE2;
        DDCOCOMI.TAX_RATE = WS_TAX_RATE;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DDCOCOMI;
        SCCFMT.DATA_LEN = 118;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_TAX_RATE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-C-INTR-INQ", BPCCINTI);
        if (BPCCINTI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCCINTI.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        CEP.TRC(SCCGWA, SCCCLDT.RC);
        if (SCCCLDT.RC != 0) {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "LINK SCSSCLDT,CLDT-RC=" + SCCCLDT.RC;
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void S000_CALL_BPZCINTI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-C-INTR-INQ", BPCCINTI);
        if (BPCCINTI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCCINTI.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZITAXG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-TAXR-GROUP-INQ", BPCITAXG);
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZIQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-DDPRD", DDCIQPRD);
        if (DDCIQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQPRD.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_INF() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INFO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
