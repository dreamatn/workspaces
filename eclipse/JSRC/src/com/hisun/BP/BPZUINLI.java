package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZUINLI {
    String JIBS_tmp_str[] = new String[10];
    brParm BPTCLIB_BR = new brParm();
    boolean pgmRtn = false;
    String CPN_R_ADW_BRIS = "BP-R-ADW-BRIS";
    String CPN_R_CLIB = "BP-R-CLIB";
    String CPN_R_STARTBR_TLVB = "BP-R-STARTBR-TLVB";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    double WS_ACCR_AMT = 0;
    char WS_END = ' ';
    double WS_LIB_AMT = 0;
    double WS_BOX_AMT = 0;
    double WS_TAMT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    BPRCLIB BPRCLIB = new BPRCLIB();
    BPRBRIS BPRBRIS = new BPRBRIS();
    BPCRCLIB BPCRCLIB = new BPCRCLIB();
    BPCTBRIS BPCTBRIS = new BPCTBRIS();
    BPCRTLVB BPCRTLVB = new BPCRTLVB();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPCFX BPCFX = new BPCFX();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCSINLI BPCSINLI;
    public void MP(SCCGWA SCCGWA, BPCSINLI BPCSINLI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSINLI = BPCSINLI;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZUINLI return!");
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
        B020_BRANCH_INSR_ADDUP();
        if (pgmRtn) return;
        B030_BRANCH_INSR_CHK();
        if (pgmRtn) return;
    }
    public void B010_CHK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSINLI.BR);
        if (BPCSINLI.BR == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_INPUT;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_BRANCH_INSR_ADDUP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCLIB);
        IBS.init(SCCGWA, BPRBRIS);
        IBS.init(SCCGWA, BPCRCLIB);
        BPRBRIS.KEY.BR = BPCSINLI.BR;
        BPCTBRIS.POINTER = BPRBRIS;
        BPCTBRIS.LEN = 134;
        BPCTBRIS.INFO.FUNC = 'Q';
        S000_CALL_BPZTBRIS();
        if (pgmRtn) return;
        if (BPCTBRIS.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BRIS_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPRCLIB.KEY.BR = BPCSINLI.BR;
        T000_STARTBR_BY_GROUP();
        if (pgmRtn) return;
        T000_READNEXT_PROC();
        if (pgmRtn) return;
        if (WS_END == 'Y') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CLIB_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPRCLIB.KEY.CCY.equalsIgnoreCase(BPRBRIS.INSR_CCY)) {
            WS_ACCR_AMT = WS_ACCR_AMT + WS_TAMT;
        } else {
            if (WS_TAMT != 0) {
                R010_BRANCH_INSR_CHANGE();
                if (pgmRtn) return;
                WS_ACCR_AMT = WS_ACCR_AMT + BPCFX.SELL_AMT;
            }
        }
        WS_END = 'N';
        while (WS_END != 'Y') {
            T000_READNEXT_PROC();
            if (pgmRtn) return;
            if (BPRCLIB.KEY.CCY.equalsIgnoreCase(BPRBRIS.INSR_CCY)) {
                WS_ACCR_AMT = WS_ACCR_AMT + WS_TAMT;
            } else {
                if (WS_TAMT != 0) {
                    R010_BRANCH_INSR_CHANGE();
                    if (pgmRtn) return;
                    WS_ACCR_AMT = WS_ACCR_AMT + BPCFX.SELL_AMT;
                }
            }
        }
        T000_ENDBR_PROC();
        if (pgmRtn) return;
        BPRTLVB.KEY.BR = BPCSINLI.BR;
        BPRTLVB.KEY.PLBOX_NO = "%%%%%%";
        BPCRTLVB.INFO.FUNC = 'Q';
        BPCRTLVB.INFO.LEN = 187;
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        S000_CALL_BPZRTLVB();
        if (pgmRtn) return;
        BPCRTLVB.INFO.FUNC = 'N';
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        BPCRTLVB.INFO.LEN = 187;
        S000_CALL_BPZRTLVB();
        if (pgmRtn) return;
        WS_CNT = 0;
        while (BPCRTLVB.RETURN_INFO != 'N' 
            && WS_CNT <= 1000) {
            WS_CNT += 1;
            if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
                if (BPRTLVB.PLBOX_TP == '1' 
                    || BPRTLVB.PLBOX_TP == '2' 
                    || BPRTLVB.PLBOX_TP == '5') {
                    BPRCLIB.KEY.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
                    WS_CNT = 1001;
                }
            } else {
                if (BPRTLVB.PLBOX_TP == '1' 
                    || BPRTLVB.PLBOX_TP == '2') {
                    CEP.TRC(SCCGWA, "123123");
                    CEP.TRC(SCCGWA, BPRTLVB.CRNT_TLR);
                    CEP.TRC(SCCGWA, BPRTLVB.LAST_TLR);
                    BPRCLIB.KEY.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
                    WS_CNT = 1001;
                }
            }
            BPCRTLVB.INFO.FUNC = 'N';
            BPCRTLVB.INFO.POINTER = BPRTLVB;
            BPCRTLVB.INFO.LEN = 187;
            S000_CALL_BPZRTLVB();
            if (pgmRtn) return;
        }
        BPCRTLVB.INFO.FUNC = 'E';
        BPCRTLVB.INFO.LEN = 187;
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        S000_CALL_BPZRTLVB();
        if (pgmRtn) return;
        if (BPCRTLVB.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPRCLIB.KEY.BR = BPCSINLI.BR;
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
            if (BPRCLIB.KEY.CCY.equalsIgnoreCase(BPRBRIS.INSR_CCY)) {
                WS_LIB_AMT = WS_LIB_AMT + BPRCLIB.BAL;
            } else {
                if (BPRCLIB.BAL != 0) {
                    R020_LIB_INSR_CHANGE();
                    if (pgmRtn) return;
                    WS_LIB_AMT = WS_LIB_AMT + BPCFX.SELL_AMT;
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
        WS_BOX_AMT = WS_ACCR_AMT - WS_LIB_AMT;
    }
    public void B030_BRANCH_INSR_CHK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_ACCR_AMT);
        CEP.TRC(SCCGWA, BPRBRIS.PLIR_AMT);
        CEP.TRC(SCCGWA, BPRBRIS.LMT_U);
        CEP.TRC(SCCGWA, BPRBRIS.LMT_L);
        if (WS_ACCR_AMT > BPRBRIS.PLIR_AMT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_OVER_INSUAMT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_BOX_AMT > BPRBRIS.BXIR_AMT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_OVER_BOXAMT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_ACCR_AMT > BPRBRIS.LMT_U) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_OVER_BRMAX_LIMIT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_ACCR_AMT < BPRBRIS.LMT_L) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UNDER_BRMIN_LIMIT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R010_BRANCH_INSR_CHANGE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFX);
        BPCFX.FUNC = '3';
        BPCFX.BUY_CCY = BPRCLIB.KEY.CCY;
        BPCFX.BUY_AMT = WS_TAMT;
        BPCFX.SELL_CCY = BPRBRIS.INSR_CCY;
        BPCFX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCFX.EXR_TYPE = "MDR";
        S000_CALL_BPZSFX();
        if (pgmRtn) return;
    }
    public void R020_LIB_INSR_CHANGE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFX);
        BPCFX.FUNC = '3';
        BPCFX.BUY_CCY = BPRCLIB.KEY.CCY;
        BPCFX.BUY_AMT = BPRCLIB.BAL;
        BPCFX.SELL_CCY = BPRBRIS.INSR_CCY;
        BPCFX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCFX.EXR_TYPE = "MDR";
        S000_CALL_BPZSFX();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZTBRIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_BRIS, BPCTBRIS);
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
    public void T000_STARTBR_BY_GROUP() throws IOException,SQLException,Exception {
        BPTCLIB_BR.rp = new DBParm();
        BPTCLIB_BR.rp.TableName = "BPTCLIB";
        BPTCLIB_BR.rp.set = "WS-TAMT=SUM(BAL)";
        BPTCLIB_BR.rp.where = "BR = :BPRCLIB.KEY.BR";
        BPTCLIB_BR.rp.grp = "CCY";
        BPTCLIB_BR.rp.order = "CCY";
        IBS.STARTBR(SCCGWA, BPRCLIB, this, BPTCLIB_BR);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        WS_TAMT = 0;
        WS_END = ' ';
        IBS.READNEXT(SCCGWA, BPRCLIB, this, BPTCLIB_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_END = 'Y';
        }
        CEP.TRC(SCCGWA, WS_END);
        CEP.TRC(SCCGWA, BPRCLIB.KEY.CCY);
        CEP.TRC(SCCGWA, WS_TAMT);
    }
    public void S000_CALL_BPZRTLVB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_STARTBR_TLVB, BPCRTLVB);
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTCLIB_BR);
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
