package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPCBOX {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_BRE_CLIB = "BP-R-BRE-CLIB    ";
    String CPN_R_CLIB = "BP-R-CLIB";
    String CPN_R_ADW_TLVB = "BP-R-ADW-TLVB";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String CPN_R_STARTBR_TLVB = "BP-R-STARTBR-TLVB";
    int WS_BALFLG_NUM = 0;
    int WS_BAL_NUM = 0;
    int WS_BR = 0;
    String WS_TLR = " ";
    int WS_CNT = 0;
    String WS_ERR_MSG = " ";
    BPZPCBOX_WS_ERR_INFO WS_ERR_INFO = new BPZPCBOX_WS_ERR_INFO();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    BPRCLIB BPRCLIB = new BPRCLIB();
    SCCMSG SCCMSG = new SCCMSG();
    BPCTLIBB BPCTLIBB = new BPCTLIBB();
    BPCRCLIB BPCRCLIB = new BPCRCLIB();
    BPCTLVBF BPCTLVBF = new BPCTLVBF();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPCRTLVB BPCRTLVB = new BPCRTLVB();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPCBOX BPCPCBOX;
    public void MP(SCCGWA SCCGWA, BPCPCBOX BPCPCBOX) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPCBOX = BPCPCBOX;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPCBOX return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRCLIB);
        IBS.init(SCCGWA, BPCTLIBB);
        IBS.init(SCCGWA, BPCPCBOX.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B001_CHK_INPUT();
        if (pgmRtn) return;
        if (BPCPCBOX.FUNC == 'Q') {
            B100_CHKOUT_PROCESS();
            if (pgmRtn) return;
        } else if (BPCPCBOX.FUNC == 'A') {
            B200_CHKBAL_PROCESS();
            if (pgmRtn) return;
        } else if (BPCPCBOX.FUNC == 'R') {
            B300_CHKRLT_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCPCBOX.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B001_CHK_INPUT() throws IOException,SQLException,Exception {
        WS_BR = BPCPCBOX.BR;
        CEP.TRC(SCCGWA, WS_BR);
        CEP.TRC(SCCGWA, BPCPCBOX.TLR);
        CEP.TRC(SCCGWA, BPCPCBOX.VB_FLG);
        WS_TLR = BPCPCBOX.TLR;
        if (BPCPCBOX.VB_FLG != '1' 
            && BPCPCBOX.VB_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_VBFLG_INPUT, BPCPCBOX.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCPCBOX.VB_FLG == '0' 
            && BPCPCBOX.TLR.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_VBTLR_INPUT, BPCPCBOX.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCPCBOX.VB_FLG == '1' 
            && BPCPCBOX.BR == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_VBBR_INPUT, BPCPCBOX.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B100_CHKOUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCLIB);
        IBS.init(SCCGWA, BPCFTLRQ);
        if (BPCPCBOX.VB_FLG == '0') {
            BPCFTLRQ.INFO.TLR = BPCPCBOX.TLR;
            S000_CALL_BPZFTLRQ();
            if (pgmRtn) return;
            BPRTLVB.KEY.BR = BPCFTLRQ.INFO.NEW_BR;
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
                || BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("2") 
                || BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("4")) {
                BPRTLVB.PLBOX_TP = '4';
            } else {
                BPRTLVB.PLBOX_TP = '3';
            }
            BPRTLVB.CRNT_TLR = BPCPCBOX.TLR;
            BPCTLVBF.INFO.FUNC = 'I';
            BPCTLVBF.POINTER = BPRTLVB;
            BPCTLVBF.LEN = 187;
            S000_CALL_BPZTLVBF();
            if (pgmRtn) return;
            if (BPCTLVBF.RETURN_INFO == 'F') {
                BPRCLIB.KEY.BR = BPRTLVB.KEY.BR;
                BPRCLIB.KEY.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
                R100_QUERY_PROC();
                if (pgmRtn) return;
            }
        } else {
            BPRTLVB.CRNT_TLR = BPCPCBOX.TLR;
            BPCRTLVB.INFO.FUNC = 'L';
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
                        if (BPRTLVB.CRNT_TLR.equalsIgnoreCase(BPCPCBOX.TLR)) {
                            BPRCLIB.KEY.BR = BPRTLVB.KEY.BR;
                            BPRCLIB.KEY.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
                            R100_QUERY_PROC();
                            if (pgmRtn) return;
                        }
                        WS_CNT = 1001;
                    }
                } else {
                    if (BPRTLVB.PLBOX_TP == '1' 
                        || BPRTLVB.PLBOX_TP == '2') {
                        if (BPRTLVB.CRNT_TLR.equalsIgnoreCase(BPCPCBOX.TLR)) {
                            BPRCLIB.KEY.BR = BPRTLVB.KEY.BR;
                            BPRCLIB.KEY.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
                            R100_QUERY_PROC();
                            if (pgmRtn) return;
                        }
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
        }
    }
    public void R100_QUERY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRCLIB);
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
            CEP.TRC(SCCGWA, BPRCLIB.BAL_FLG);
            CEP.TRC(SCCGWA, BPRCLIB.PLBOX_TP);
            if (BPRCLIB.BAL_FLG == 'N') {
                WS_BALFLG_NUM += 1;
                WS_ERR_INFO.WS_ERR_BR = BPRCLIB.KEY.BR;
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
        if (WS_BALFLG_NUM > 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BAL_FLG, BPCPCBOX.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B200_CHKBAL_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCLIB);
        IBS.init(SCCGWA, BPCFTLRQ);
        if (BPCPCBOX.VB_FLG == '0') {
            BPCFTLRQ.INFO.TLR = BPCPCBOX.TLR;
            S000_CALL_BPZFTLRQ();
            if (pgmRtn) return;
            BPRTLVB.KEY.BR = BPCFTLRQ.INFO.NEW_BR;
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
                || BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("2") 
                || BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("4")) {
                BPRTLVB.PLBOX_TP = '4';
            } else {
                BPRTLVB.PLBOX_TP = '3';
            }
            BPRTLVB.CRNT_TLR = BPCPCBOX.TLR;
            BPCTLVBF.INFO.FUNC = 'I';
            BPCTLVBF.POINTER = BPRTLVB;
            BPCTLVBF.LEN = 187;
            S000_CALL_BPZTLVBF();
            if (pgmRtn) return;
            if (BPCTLVBF.RETURN_INFO == 'F') {
                BPRCLIB.KEY.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
                BPRCLIB.KEY.BR = BPCFTLRQ.INFO.NEW_BR;
                R200_QUERY_BAL_PROC();
                if (pgmRtn) return;
            }
        } else {
            BPRTLVB.CRNT_TLR = BPCPCBOX.TLR;
            BPCRTLVB.INFO.FUNC = 'L';
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
                        if (BPRTLVB.CRNT_TLR.equalsIgnoreCase(BPCPCBOX.TLR)) {
                            BPRCLIB.KEY.BR = BPRTLVB.KEY.BR;
                            BPRCLIB.KEY.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
                            R200_QUERY_BAL_PROC();
                            if (pgmRtn) return;
                        }
                        WS_CNT = 1001;
                    }
                } else {
                    if (BPRTLVB.PLBOX_TP == '1' 
                        || BPRTLVB.PLBOX_TP == '2') {
                        if (BPRTLVB.CRNT_TLR.equalsIgnoreCase(BPCPCBOX.TLR)) {
                            BPRCLIB.KEY.BR = BPRTLVB.KEY.BR;
                            BPRCLIB.KEY.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
                            R200_QUERY_BAL_PROC();
                            if (pgmRtn) return;
                        }
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
        }
    }
    public void R200_QUERY_BAL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRCLIB);
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
            if (BPRCLIB.BAL > 0) {
                WS_BALFLG_NUM += 1;
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
        CEP.TRC(SCCGWA, WS_BALFLG_NUM);
        if (WS_BALFLG_NUM > 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BAL_ZERO, BPCPCBOX.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B300_CHKRLT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLVB);
        IBS.init(SCCGWA, BPRCLIB);
        IBS.init(SCCGWA, BPCTLVBF);
        IBS.init(SCCGWA, BPCRTLVB);
        if (BPCPCBOX.VB_FLG == '0') {
            BPCFTLRQ.INFO.TLR = BPCPCBOX.TLR;
            S000_CALL_BPZFTLRQ();
            if (pgmRtn) return;
            BPRTLVB.KEY.BR = BPCFTLRQ.INFO.NEW_BR;
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
                || BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("2") 
                || BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("4")) {
                BPRTLVB.PLBOX_TP = '4';
            } else {
                BPRTLVB.PLBOX_TP = '3';
            }
            BPRTLVB.CRNT_TLR = BPCPCBOX.TLR;
            BPCTLVBF.INFO.FUNC = 'I';
            BPCTLVBF.POINTER = BPRTLVB;
            BPCTLVBF.LEN = 187;
            S000_CALL_BPZTLVBF();
            if (pgmRtn) return;
            if (BPCTLVBF.RETURN_INFO == 'F') {
                BPCPCBOX.CHK_RLT_FLG = 'Y';
            }
        } else {
            BPRTLVB.CRNT_TLR = BPCPCBOX.TLR;
            BPCRTLVB.INFO.FUNC = 'L';
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
                        if (BPRTLVB.CRNT_TLR.equalsIgnoreCase(BPCPCBOX.TLR)) {
                            BPCPCBOX.CHK_RLT_FLG = 'Y';
                        }
                        WS_CNT = 1001;
                    }
                } else {
                    if (BPRTLVB.PLBOX_TP == '1' 
                        || BPRTLVB.PLBOX_TP == '2') {
                        if (BPRTLVB.CRNT_TLR.equalsIgnoreCase(BPCPCBOX.TLR)) {
                            BPCPCBOX.CHK_RLT_FLG = 'Y';
                        }
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
        }
    }
    public void S000_CALL_BPZTLIBB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_BRE_CLIB, BPCTLIBB);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTLIBB.RC);
        if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CLIB_NOTFND) 
            && BPCTLIBB.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTLIBB.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPCBOX.RC);
            Z_RET();
            if (pgmRtn) return;
        }
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
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_INF_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRTLVB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_STARTBR_TLVB, BPCRTLVB);
    }
    public void S000_CALL_BPZTLVBF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_TLVB, BPCTLVBF);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CHKOUT() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INFO);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPCBOX.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPCBOX = ");
            CEP.TRC(SCCGWA, BPCPCBOX);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
