package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSQCRT {
    String K_OUTPUT_FMT = "BP145";
    String CPN_P_MPCY = "BP-TRAN-AMT-BY-CCY";
    String CPN_EXCHANGE = "BP-EX         ";
    int K_MAX_COL = 99;
    int K_MAX_ROW = 99;
    String WS_ERR_MSG = " ";
    BPZSQCRT_WS_ORGS_DETAIL WS_ORGS_DETAIL = new BPZSQCRT_WS_ORGS_DETAIL();
    int WS_TEMP_BR = 0;
    int WS_CNT = 0;
    char WS_SUB_ORG_FLAG = ' ';
    char WS_ORG_HEAD_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOQCRT BPCOQCRT = new BPCOQCRT();
    BPCMPCY BPCMPCY = new BPCMPCY();
    BPCFX BPCFX = new BPCFX();
    SCCGWA SCCGWA;
    BPCSQCRT BPCSQCRT;
    BPCPQBNK_DATA_INFO BPCRBANK;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSQCRT BPCSQCRT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSQCRT = BPCSQCRT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSQCRT return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        BPCOQCRT.LOCAL_CCY = BPCSQCRT.LOC_CCY;
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_QUERY_EX_RATE_FOR_CN();
        } else {
            B010_QUERY_EX_RATE();
        }
        B020_CAL_PROFIT();
        B030_OUTPUT_PROC();
    }
    public void B010_QUERY_EX_RATE_FOR_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSQCRT.CCY);
        CEP.TRC(SCCGWA, BPCSQCRT.AMT);
        CEP.TRC(SCCGWA, BPCSQCRT.EX_RATE);
        CEP.TRC(SCCGWA, BPCSQCRT.LOC_CCY);
        IBS.init(SCCGWA, BPCFX);
        BPCFX.FUNC = '3';
        BPCFX.EXR_TYPE = BPCRBANK.EX_RA;
        BPCFX.BUY_CCY = BPCSQCRT.CCY;
        BPCFX.BUY_AMT = BPCSQCRT.AMT;
        BPCFX.SELL_CCY = BPCSQCRT.LOC_CCY;
        S000_CALL_BPZSFX();
        BPCOQCRT.EX_TIME = BPCFX.EFF_TIME;
        BPCOQCRT.EX_RATE = BPCFX.TRN_RATE;
    }
    public void B010_QUERY_EX_RATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFX);
        BPCFX.FUNC = '3';
        BPCFX.EXR_TYPE = "MDR";
        BPCFX.BUY_CCY = BPCSQCRT.CCY;
        BPCFX.BUY_AMT = BPCSQCRT.AMT;
        BPCFX.TRN_RATE = BPCSQCRT.EX_RATE;
        BPCFX.SELL_CCY = BPCSQCRT.LOC_CCY;
        S000_CALL_BPZSFX();
        BPCOQCRT.EX_TIME = BPCFX.EFF_TIME;
        BPCOQCRT.EX_RATE = BPCFX.TRN_RATE;
    }
    public void B020_CAL_PROFIT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCMPCY);
        CEP.TRC(SCCGWA, BPCSQCRT.LOC_CCY);
        CEP.TRC(SCCGWA, BPCFX.SELL_AMT);
        BPCMPCY.I_CCY = BPCSQCRT.LOC_CCY;
        BPCMPCY.I_AMT = BPCFX.SELL_AMT;
        BPCMPCY.I_FLG = '0';
        S000_CALL_BPZCMPCY();
        BPCOQCRT.PL_AMT = BPCFX.SELL_AMT - BPCMPCY.O_AMT;
        CEP.TRC(SCCGWA, BPCOQCRT.PL_AMT);
        CEP.TRC(SCCGWA, BPCMPCY.O_AMT);
        BPCOQCRT.LOCAL_AMT = BPCMPCY.O_AMT;
    }
    public void B030_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOQCRT;
        SCCFMT.DATA_LEN = 58;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_BPZSFX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-FX", BPCFX);
        if (BPCFX.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFX.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZCMPCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_MPCY, BPCMPCY);
        if (BPCMPCY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCMPCY.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
