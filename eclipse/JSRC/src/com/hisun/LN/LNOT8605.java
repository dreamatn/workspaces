package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.DD.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT8605 {
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "LND17";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    double WS_REPAY_AMT = 0;
    String WS_DEP_AC = " ";
    String WS_CCY = " ";
    int WS_I = 0;
    LNOT8605_WS_OUT_VARS WS_OUT_VARS = new LNOT8605_WS_OUT_VARS();
    char WS_END_FLAG = ' ';
    String TRC_CTA_NO = " ";
    int TRC_CTA_SEQ = 0;
    String TRC_AMT_FMT = " ";
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    LNCSRPC LNCSRPC = new LNCSRPC();
    DDCSIBAL DDCSIBAL = new DDCSIBAL();
    SCCGWA SCCGWA;
    LNB0025_AWA_0025 LNB0025_AWA_0025;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNOT8605 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB0025_AWA_0025>");
        LNB0025_AWA_0025 = (LNB0025_AWA_0025) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_REPAY_CALC_PROC();
        B300_OUTPUT_PROC();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNB0025_AWA_0025.CTA_NO);
        CEP.TRC(SCCGWA, LNB0025_AWA_0025.CCY);
        CEP.TRC(SCCGWA, LNB0025_AWA_0025.TOT_AMT);
        CEP.TRC(SCCGWA, LNB0025_AWA_0025.WHD_RUL);
        CEP.TRC(SCCGWA, LNB0025_AWA_0025.SETL_MTH);
        CEP.TRC(SCCGWA, LNB0025_AWA_0025.DEP_AC);
        CEP.TRC(SCCGWA, LNB0025_AWA_0025.AC_ARRAY[1-1].STL_MTH2);
        CEP.TRC(SCCGWA, LNB0025_AWA_0025.AC_ARRAY[1-1].DEP_AC2);
        CEP.TRC(SCCGWA, LNB0025_AWA_0025.AC_ARRAY[2-1].STL_MTH2);
        CEP.TRC(SCCGWA, LNB0025_AWA_0025.AC_ARRAY[2-1].DEP_AC2);
        CEP.TRC(SCCGWA, LNB0025_AWA_0025.AC_ARRAY[3-1].STL_MTH2);
        CEP.TRC(SCCGWA, LNB0025_AWA_0025.AC_ARRAY[3-1].DEP_AC2);
        CEP.TRC(SCCGWA, LNB0025_AWA_0025.AC_ARRAY[4-1].STL_MTH2);
        CEP.TRC(SCCGWA, LNB0025_AWA_0025.AC_ARRAY[4-1].DEP_AC2);
        if (LNB0025_AWA_0025.CTA_NO.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            WS_FLD_NO = LNB0025_AWA_0025.CTA_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (LNB0025_AWA_0025.TOT_AMT == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            WS_FLD_NO = LNB0025_AWA_0025.TOT_AMT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_INPUT;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_REPAY_CALC_PROC() throws IOException,SQLException,Exception {
        if (LNB0025_AWA_0025.WHD_RUL == ' ') {
            WS_OUT_VARS.WS_AMT1 = LNB0025_AWA_0025.TOT_AMT;
        } else if (LNB0025_AWA_0025.WHD_RUL == '1') {
            B210_PROC_RUL_1();
        } else if (LNB0025_AWA_0025.WHD_RUL == '2') {
            B210_PROC_RUL_2();
        }
    }
    public void B210_PROC_RUL_1() throws IOException,SQLException,Exception {
        WS_DEP_AC = LNB0025_AWA_0025.DEP_AC;
        S000_CALL_DDZSIBAL();
        if (DDCSIBAL.AVL_BAL >= LNB0025_AWA_0025.TOT_AMT) {
            WS_OUT_VARS.WS_AMT1 = LNB0025_AWA_0025.TOT_AMT;
        } else {
            for (WS_I = 1; WS_I <= 4 
                && LNB0025_AWA_0025.AC_ARRAY[WS_I-1].DEP_AC2.trim().length() != 0; WS_I += 1) {
                WS_DEP_AC = LNB0025_AWA_0025.AC_ARRAY[WS_I-1].DEP_AC2;
                S000_CALL_DDZSIBAL();
                if (DDCSIBAL.AVL_BAL >= LNB0025_AWA_0025.TOT_AMT) {
                    WS_OUT_VARS.WS_AMT[WS_I-1].WS_AMT2 = LNB0025_AWA_0025.TOT_AMT;
                    WS_I += 4;
                }
            }
        }
    }
    public void B210_PROC_RUL_2() throws IOException,SQLException,Exception {
        WS_DEP_AC = LNB0025_AWA_0025.DEP_AC;
        S000_CALL_DDZSIBAL();
        if (DDCSIBAL.AVL_BAL >= LNB0025_AWA_0025.TOT_AMT) {
            WS_OUT_VARS.WS_AMT1 = LNB0025_AWA_0025.TOT_AMT;
        } else {
            WS_OUT_VARS.WS_AMT1 = DDCSIBAL.AVL_BAL;
            WS_REPAY_AMT = LNB0025_AWA_0025.TOT_AMT - DDCSIBAL.AVL_BAL;
            for (WS_I = 1; WS_I <= 4 
                && LNB0025_AWA_0025.AC_ARRAY[WS_I-1].DEP_AC2.trim().length() != 0 
                && WS_REPAY_AMT > 0; WS_I += 1) {
                WS_DEP_AC = LNB0025_AWA_0025.AC_ARRAY[WS_I-1].DEP_AC2;
                S000_CALL_DDZSIBAL();
                if (DDCSIBAL.AVL_BAL >= WS_REPAY_AMT) {
                    WS_OUT_VARS.WS_AMT[WS_I-1].WS_AMT2 = WS_REPAY_AMT;
                    WS_I += 4;
                    WS_REPAY_AMT = 0;
                } else {
                    WS_OUT_VARS.WS_AMT[WS_I-1].WS_AMT2 = DDCSIBAL.AVL_BAL;
                    WS_REPAY_AMT = WS_REPAY_AMT - DDCSIBAL.AVL_BAL;
                }
            }
        }
    }
    public void B300_OUTPUT_PROC() throws IOException,SQLException,Exception {
        WS_OUT_VARS.WS_CTA_NO = LNB0025_AWA_0025.CTA_NO;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUT_VARS;
        SCCFMT.DATA_LEN = 105;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_DDZSIBAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSIBAL);
        DDCSIBAL.FUNC = 'C';
        DDCSIBAL.AC_NO = WS_DEP_AC;
        DDCSIBAL.CCY = LNB0025_AWA_0025.CCY;
        IBS.CALLCPN(SCCGWA, "DD-SVR-INQ-CCY-BAL", DDCSIBAL, true);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
