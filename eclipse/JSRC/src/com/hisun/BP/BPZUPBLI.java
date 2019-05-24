package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZUPBLI {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_ADW_TLVB = "BP-R-ADW-TLVB";
    String CPN_R_CLIB = "BP-R-CLIB";
    char K_POOL_BOX_NUM_1 = '1';
    char K_POOL_BOX_NUM_2 = '2';
    char K_CASH_BOX_NUM_3 = '3';
    char K_CASH_BOX_NUM_4 = '4';
    char K_POOL_BOX_NUM_5 = '5';
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    double WS_ACCR_AMT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    BPRCLIB BPRCLIB = new BPRCLIB();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPCRCLIB BPCRCLIB = new BPCRCLIB();
    BPCTLVBF BPCTLVBF = new BPCTLVBF();
    BPCFX BPCFX = new BPCFX();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCSPBLI BPCSPBLI;
    public void MP(SCCGWA SCCGWA, BPCSPBLI BPCSPBLI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSPBLI = BPCSPBLI;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZUPBLI return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRCLIB);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHK_INPUT_DATA();
        if (pgmRtn) return;
        B020_PLBOX_LIMIT_ADDUP();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B030_PLBOX_LIMIT_CHK_FOR_CN();
            if (pgmRtn) return;
        } else {
            B030_PLBOX_LIMIT_CHK();
            if (pgmRtn) return;
        }
    }
    public void B010_CHK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSPBLI.BR);
        if (BPCSPBLI.BR == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_INPUT;
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCSPBLI.PLBOX_NO.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_INPUT;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_PLBOX_LIMIT_ADDUP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCLIB);
        IBS.init(SCCGWA, BPRTLVB);
        IBS.init(SCCGWA, BPCRCLIB);
        BPRTLVB.KEY.BR = BPCSPBLI.BR;
        BPRTLVB.KEY.PLBOX_NO = BPCSPBLI.PLBOX_NO;
        BPCTLVBF.POINTER = BPRTLVB;
        BPCTLVBF.LEN = 187;
        BPCTLVBF.INFO.FUNC = 'Q';
        CEP.TRC(SCCGWA, BPRTLVB.KEY.BR);
        CEP.TRC(SCCGWA, BPRTLVB.KEY.PLBOX_NO);
        S000_CALL_BPZTLVBF();
        if (pgmRtn) return;
        if (BPCTLVBF.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CLIB_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPRCLIB.KEY.BR = BPCSPBLI.BR;
        BPRCLIB.KEY.PLBOX_NO = BPCSPBLI.PLBOX_NO;
        CEP.TRC(SCCGWA, BPRCLIB.KEY.BR);
        BPCRCLIB.INFO.FUNC = '1';
        BPCRCLIB.INFO.POINTER = BPRCLIB;
        BPCRCLIB.INFO.LEN = 352;
        S000_CALL_BPZRCLIB();
        if (pgmRtn) return;
        if (BPCRCLIB.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CLIB_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPCRCLIB.INFO.FUNC = 'N';
        BPCRCLIB.INFO.POINTER = BPRCLIB;
        BPCRCLIB.INFO.LEN = 352;
        S000_CALL_BPZRCLIB();
        if (pgmRtn) return;
        if (BPCRCLIB.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CLIB_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_CNT = 0;
        while (BPCRCLIB.RETURN_INFO != 'N' 
            && WS_CNT <= 1000) {
            WS_CNT += 1;
            if (BPRCLIB.KEY.CCY.equalsIgnoreCase(BPRTLVB.BLMT_CCY)) {
                WS_ACCR_AMT = WS_ACCR_AMT + BPRCLIB.BAL;
            } else {
                if (BPRCLIB.BAL != 0) {
                    R010_BRANCH_INSR_CHANGE();
                    if (pgmRtn) return;
                    WS_ACCR_AMT = WS_ACCR_AMT + BPCFX.SELL_AMT;
                }
            }
            BPCRCLIB.INFO.FUNC = 'N';
            BPCRCLIB.INFO.POINTER = BPRCLIB;
            BPCRCLIB.INFO.LEN = 352;
            S000_CALL_BPZRCLIB();
            if (pgmRtn) return;
        }
        BPCRCLIB.INFO.FUNC = 'E';
        BPCRCLIB.INFO.POINTER = BPRCLIB;
        BPCRCLIB.INFO.LEN = 352;
        S000_CALL_BPZRCLIB();
        if (pgmRtn) return;
    }
    public void B030_PLBOX_LIMIT_CHK_FOR_CN() throws IOException,SQLException,Exception {
        if (WS_ACCR_AMT > BPRTLVB.BLMT_U) {
            if (BPRTLVB.PLBOX_TP == K_POOL_BOX_NUM_1 
                || BPRTLVB.PLBOX_TP == K_POOL_BOX_NUM_2 
                || BPRTLVB.PLBOX_TP == K_POOL_BOX_NUM_5) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_OVER_PBMAX_LIMIT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPRTLVB.PLBOX_TP == K_CASH_BOX_NUM_3 
                || BPRTLVB.PLBOX_TP == K_CASH_BOX_NUM_4) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_OVER_CBMAX_LIMIT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (WS_ACCR_AMT < BPRTLVB.BLMT_L) {
            if (BPRTLVB.PLBOX_TP == K_POOL_BOX_NUM_1 
                || BPRTLVB.PLBOX_TP == K_POOL_BOX_NUM_2 
                || BPRTLVB.PLBOX_TP == K_POOL_BOX_NUM_5) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UNDER_PBMIN_LIMIT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPRTLVB.PLBOX_TP == K_CASH_BOX_NUM_3 
                || BPRTLVB.PLBOX_TP == K_CASH_BOX_NUM_4) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_OVER_CBMIN_LIMIT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B030_PLBOX_LIMIT_CHK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_ACCR_AMT);
        CEP.TRC(SCCGWA, BPRTLVB.BLMT_U);
        CEP.TRC(SCCGWA, BPRTLVB.BLMT_L);
        if (WS_ACCR_AMT > BPRTLVB.BLMT_U) {
            if (BPRTLVB.PLBOX_TP == K_POOL_BOX_NUM_1 
                || BPRTLVB.PLBOX_TP == K_POOL_BOX_NUM_2) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_OVER_PBMAX_LIMIT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPRTLVB.PLBOX_TP == K_CASH_BOX_NUM_3 
                || BPRTLVB.PLBOX_TP == K_CASH_BOX_NUM_4) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_OVER_CBMAX_LIMIT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (WS_ACCR_AMT < BPRTLVB.BLMT_L) {
            if (BPRTLVB.PLBOX_TP == K_POOL_BOX_NUM_1 
                || BPRTLVB.PLBOX_TP == K_POOL_BOX_NUM_2) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UNDER_PBMIN_LIMIT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPRTLVB.PLBOX_TP == K_CASH_BOX_NUM_3 
                || BPRTLVB.PLBOX_TP == K_CASH_BOX_NUM_4) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_OVER_CBMIN_LIMIT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void R010_BRANCH_INSR_CHANGE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFX);
        BPCFX.FUNC = '3';
        BPCFX.BUY_CCY = BPRCLIB.KEY.CCY;
        BPCFX.BUY_AMT = BPRCLIB.BAL;
        BPCFX.SELL_CCY = BPRTLVB.BLMT_CCY;
        BPCFX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCFX.EXR_TYPE = "MDR";
        S000_CALL_BPZSFX();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZTLVBF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_TLVB, BPCTLVBF);
    }
    public void S000_CALL_BPZRCLIB() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BPCRCLIB");
        IBS.CALLCPN(SCCGWA, CPN_R_CLIB, BPCRCLIB);
        CEP.TRC(SCCGWA, BPCRCLIB.RC.RC_CODE);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRCLIB.RC);
        if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CLIB_NOTFND) 
            && BPCRCLIB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRCLIB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSFX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-FX", BPCFX);
        if (BPCFX.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFX.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
