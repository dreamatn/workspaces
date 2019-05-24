package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSTLSF {
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZSTLSF";
    String CPN_R_TLR_MAINTAIN = "BP-R-TLR-MAINTAIN   ";
    String CPN_R_PEN_MAINTAIN = "BP-R-PEND-MAINTAIN  ";
    String CPN_F_ACCU_QUERY = "BP-F-ACCU-QUERY     ";
    String CPN_R_GET_TERM_INFO = "SC-GET-TERM-INFO    ";
    String CPN_ENCRYPT_PASSWORD = "SC-ENCRYPT-PASSWORD ";
    String CPN_PARM_READ = "BP-PARM-READ        ";
    String CPN_F_TLR_PEND_MGM = "BP-F-TLR-PEND-MGM   ";
    String CPN_BP_P_CHK_CBOX = "BP-P-CHK-CBOX       ";
    String K_OUTPUT_FMT = "BP551";
    String CPN_F_TLR_BV_QUERY = "BP-F-TLR-BV-QUERY   ";
    String CPN_QUERY_BANK = "BP-P-QUERY-BANK     ";
    String CPN_FUNC_CHECK_PEND = "LN-FUNC-CHECK-PEND  ";
    String K_HIS_REMARKS = "TELLER SIGN OFF INFO    ";
    String CPN_P_QUERY_ORG = "BP-P-INQ-ORG        ";
    String CPN_R_STARTBR_TLT = "BP-R-STARTBR-TLT    ";
    int WS_O_CNT = 0;
    int WS_T_CNT = 0;
    int WS_O_T_CNT = 0;
    char WS_TBL_FLAG = ' ';
    BPZSTLSF_WS_PEND_INFO WS_PEND_INFO = new BPZSTLSF_WS_PEND_INFO();
    int WS_TEMP_CNT = 0;
    int WS_PEND_CNT = 0;
    short WS_PSW_LEN = 0;
    String WS_ERR_MSG = " ";
    String WS_INFO = " ";
    BPZSTLSF_WS_ERR_INFO WS_ERR_INFO = new BPZSTLSF_WS_ERR_INFO();
    char WS_MSG_TYPE = ' ';
    BPZSTLSF_WS_LVL WS_LVL = new BPZSTLSF_WS_LVL();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCRTLTM BPCRTLTM = new BPCRTLTM();
    BPCRPENM BPCRPENM = new BPCRPENM();
    BPRTLT BPRTLT = new BPRTLT();
    BPRPEND BPRPEND = new BPRPEND();
    SCCENPSW SCCENPSW = new SCCENPSW();
    BPCOTLSF BPCOTLSF = new BPCOTLSF();
    BPCFBTAQ BPCFBTAQ = new BPCFBTAQ();
    SCCTERM SCCTERM = new SCCTERM();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCPEND BPCPEND = new BPCPEND();
    BPCFTLPM BPCFTLPM = new BPCFTLPM();
    BPCPCBOX BPCPCBOX = new BPCPCBOX();
    BPCPQBNK BPCPQBNK = new BPCPQBNK();
    BPCFBVTL BPCFBVTL = new BPCFBVTL();
    BPRPBANK BPRPBANK = new BPRPBANK();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPQBCH BPCPQBCH = new BPCPQBCH();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCRTLTB BPCRTLTB = new BPCRTLTB();
    SCCGWA SCCGWA;
    BPCSTLSF BPCSTLSF;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSTLSF BPCSTLSF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSTLSF = BPCSTLSF;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSTLSF return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRTLT);
        IBS.init(SCCGWA, BPRPEND);
        IBS.init(SCCGWA, BPCRTLTM);
        IBS.init(SCCGWA, BPCRPENM);
        IBS.init(SCCGWA, BPCOTLSF);
        IBS.init(SCCGWA, BPCFTLPM);
        IBS.init(SCCGWA, BPCPNHIS);
        WS_PEND_CNT = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        WS_PSW_LEN = 6;
        CEP.TRC(SCCGWA, "INPUT DATA:");
        CEP.TRC(SCCGWA, WS_PSW_LEN);
        CEP.TRC(SCCGWA, BPCSTLSF.OPT);
        CEP.TRC(SCCGWA, BPCSTLSF.TLR);
        CEP.TRC(SCCGWA, BPCSTLSF.PSW);
        B010_COMMON_CHECK();
        if (pgmRtn) return;
        if (BPCSTLSF.OPT == '0') {
            B020_SIGNOFF_SPEC_CHECK();
            if (pgmRtn) return;
        } else if (BPCSTLSF.OPT == '1') {
            B030_AUTH_SIGNOFF_SPEC_CHECK();
            if (pgmRtn) return;
        } else if (BPCSTLSF.OPT == '2') {
            B040_TEMP_SIGNOFF_SPEC_CHECK();
            if (pgmRtn) return;
        } else if (BPCSTLSF.OPT == '3') {
            B050_SUB_SIGNOFF_SPEC_CHECK();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B060_HISTORY_PROCESS();
        if (pgmRtn) return;
        B080_UPDATE_PROCESS();
        if (pgmRtn) return;
        if (BPCSTLSF.OPT == '0') {
            B090_COUNT_PROCESS();
            if (pgmRtn) return;
            B090_OUTPUT_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void B010_COMMON_CHECK() throws IOException,SQLException,Exception {
        if (BPCSTLSF.OPT == '0'
            || BPCSTLSF.OPT == '1'
            || BPCSTLSF.OPT == '2'
            || BPCSTLSF.OPT == '3') {
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "AAAAA");
        CEP.TRC(SCCGWA, BPCSTLSF.OPT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
        CEP.TRC(SCCGWA, BPCSTLSF.TLR);
        CEP.TRC(SCCGWA, BPCSTLSF.STLR_FLG);
        if (BPCSTLSF.OPT == '0' 
            || BPCSTLSF.OPT == '1' 
            || BPCSTLSF.OPT == '2') {
            if (BPCSTLSF.STLR_FLG != 'Y') {
                if (!SCCGWA.COMM_AREA.TL_ID.equalsIgnoreCase(BPCSTLSF.TLR)) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SIGN_OFF_TLR_ERROR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        } else {
            if (BPCSTLSF.TLR.trim().length() == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TELLER_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (SCCGWA.COMM_AREA.TL_ID.equalsIgnoreCase(BPCSTLSF.TLR)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SIGN_OFF_TLR_ERROR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        B010_01_TLR_QUERY_PROC();
        if (pgmRtn) return;
        if (BPRTLT.EFF_DT > SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EFF_DATE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPRTLT.EXP_DT <= SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EXP_DATE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPRTLT.SIGN_STS != 'O') {
            if (BPRTLT.SIGN_STS == 'T' 
                && BPCSTLSF.OPT == '3') {
            } else {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TELLER_NOT_SIGN_ON;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, BPRTLT.IDEN_DEV_ID);
        CEP.TRC(SCCGWA, BPCSTLSF.DEV_ID);
        CEP.TRC(SCCGWA, BPCSTLSF.STLR_FLG);
        if (BPCSTLSF.OPT == '2') {
            if (BPCSTLSF.STLR_FLG == 'Y') {
            } else {
                if (!BPCSTLSF.DEV_ID.equalsIgnoreCase(BPRTLT.IDEN_DEV_ID)) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_DEV_ERR);
                }
            }
        }
    }
    public void B010_01_TLR_QUERY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLT);
        BPRTLT.KEY.TLR = BPCSTLSF.TLR;
        BPCRTLTM.INFO.FUNC = 'Q';
        CEP.TRC(SCCGWA, BPRTLT.KEY.TLR);
        S000_CALL_BPZRTLRM();
        if (pgmRtn) return;
    }
    public void B020_SIGNOFF_SPEC_CHECK() throws IOException,SQLException,Exception {
        if (BPRTLT.TLR_TYP != 'C') {
            B021_CHECK_PENDING_TRANS();
            if (pgmRtn) return;
        }
        B028_CASH_CHECK();
        if (pgmRtn) return;
        B030_BV_CHECK();
        if (pgmRtn) return;
        B029_VOUCHER_CHECK();
        if (pgmRtn) return;
    }
    public void B021_CHECK_PENDING_TRANS() throws IOException,SQLException,Exception {
        WS_TBL_FLAG = 'Y';
        B022_STARTBR_PROCESS();
        if (pgmRtn) return;
        WS_PEND_CNT = 0;
        IBS.init(SCCGWA, WS_PEND_INFO);
        while (WS_TBL_FLAG != 'N') {
            B023_READNEXT_PROCESS();
            if (pgmRtn) return;
            if (WS_TBL_FLAG == 'Y' 
                && (BPCSTLSF.TLR.equalsIgnoreCase(BPRPEND.KEY.TLR)) 
                && (BPRPEND.TX_COUNT > 0)) {
                WS_PEND_CNT = WS_PEND_CNT + 1;
                WS_PEND_INFO.WS_PEND_DATA[WS_PEND_CNT-1].WS_BUSS_TYPE = BPRPEND.KEY.BUSS_TYP;
            }
        }
        B024_ENDBR_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_PEND_CNT);
        for (WS_TEMP_CNT = 1; WS_TEMP_CNT <= WS_PEND_CNT; WS_TEMP_CNT += 1) {
            IBS.init(SCCGWA, BPCPEND);
            IBS.init(SCCGWA, BPCPRMR);
            BPCPEND.KEY.TYP = "PEND ";
            BPCPEND.KEY.CD = WS_PEND_INFO.WS_PEND_DATA[WS_TEMP_CNT-1].WS_BUSS_TYPE;
            S000_CALL_BPZPRMR();
            if (pgmRtn) return;
            WS_PEND_INFO.WS_PEND_DATA[WS_TEMP_CNT-1].WS_BUSS_MSG_CODE = BPCPEND.DATA_TXT.AUTH_CODE;
            CEP.TRC(SCCGWA, BPCPEND.DATA_TXT.AUTH_CODE);
        }
        for (WS_TEMP_CNT = 1; WS_TEMP_CNT <= WS_PEND_CNT; WS_TEMP_CNT += 1) {
            CEP.TRC(SCCGWA, WS_TEMP_CNT);
            CEP.TRC(SCCGWA, WS_PEND_INFO.WS_PEND_DATA[WS_TEMP_CNT-1].WS_BUSS_MSG_CODE);
            WS_ERR_MSG = WS_PEND_INFO.WS_PEND_DATA[WS_TEMP_CNT-1].WS_BUSS_MSG_CODE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B031_CHECK_PENDING_TRANS() throws IOException,SQLException,Exception {
        WS_TBL_FLAG = 'Y';
        B022_STARTBR_PROCESS();
        if (pgmRtn) return;
        WS_PEND_CNT = 0;
        IBS.init(SCCGWA, WS_PEND_INFO);
        while (WS_TBL_FLAG != 'N') {
            B023_READNEXT_PROCESS();
            if (pgmRtn) return;
            if (WS_TBL_FLAG == 'Y' 
                && (BPCSTLSF.TLR.equalsIgnoreCase(BPRPEND.KEY.TLR)) 
                && (BPRPEND.TX_COUNT > 0)) {
                WS_PEND_CNT = WS_PEND_CNT + 1;
                WS_PEND_INFO.WS_PEND_DATA[WS_PEND_CNT-1].WS_BUSS_TYPE = BPRPEND.KEY.BUSS_TYP;
            }
        }
        B024_ENDBR_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_PEND_CNT);
        for (WS_TEMP_CNT = 1; WS_TEMP_CNT <= WS_PEND_CNT; WS_TEMP_CNT += 1) {
            IBS.init(SCCGWA, BPCPEND);
            IBS.init(SCCGWA, BPCPRMR);
            BPCPEND.KEY.TYP = "PEND ";
            BPCPEND.KEY.CD = WS_PEND_INFO.WS_PEND_DATA[WS_TEMP_CNT-1].WS_BUSS_TYPE;
            S000_CALL_BPZPRMR();
            if (pgmRtn) return;
            WS_PEND_INFO.WS_PEND_DATA[WS_TEMP_CNT-1].WS_BUSS_MSG_CODE = BPCPEND.DATA_TXT.AUTH_CODE;
            CEP.TRC(SCCGWA, BPCPEND.DATA_TXT.AUTH_CODE);
        }
        for (WS_TEMP_CNT = 1; WS_TEMP_CNT <= WS_PEND_CNT; WS_TEMP_CNT += 1) {
            CEP.TRC(SCCGWA, WS_TEMP_CNT);
            CEP.TRC(SCCGWA, WS_PEND_INFO.WS_PEND_DATA[WS_TEMP_CNT-1].WS_BUSS_MSG_CODE);
            WS_MSG_TYPE = 'W';
            WS_ERR_MSG = WS_PEND_INFO.WS_PEND_DATA[WS_TEMP_CNT-1].WS_BUSS_MSG_CODE;
            WS_INFO = "用户有待处理业务";
            S000_ERR_MSG_PROC_INFO();
            if (pgmRtn) return;
        }
    }
    public void B022_STARTBR_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPEND);
        BPRPEND.KEY.TLR = BPCSTLSF.TLR;
        BPCRPENM.INFO.FUNC = 'S';
        S000_CALL_BPZRPENM();
        if (pgmRtn) return;
    }
    public void B023_READNEXT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPEND);
        BPCRPENM.INFO.FUNC = 'N';
        BPRPEND.KEY.TLR = BPCSTLSF.TLR;
        S000_CALL_BPZRPENM();
        if (pgmRtn) return;
        if (BPCRPENM.RETURN_INFO == 'F') {
            WS_TBL_FLAG = 'Y';
        } else if (BPCRPENM.RETURN_INFO == 'N') {
            WS_TBL_FLAG = 'N';
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B024_ENDBR_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPEND);
        BPCRPENM.INFO.FUNC = 'E';
        BPRPEND.KEY.TLR = BPCSTLSF.TLR;
        S000_CALL_BPZRPENM();
        if (pgmRtn) return;
        if (BPCRPENM.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B028_CASH_CHECK() throws IOException,SQLException,Exception {
        if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
        JIBS_tmp_int = BPRTLT.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
        if (BPRTLT.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.init(SCCGWA, BPCPCBOX);
            BPCPCBOX.FUNC = 'Q';
            BPCPCBOX.TLR = BPCSTLSF.TLR;
            BPCPCBOX.BR = BPRTLT.NEW_BR;
            BPCPCBOX.VB_FLG = '0';
            S000_CALL_BPZPCBOX();
            if (pgmRtn) return;
            if (BPCPCBOX.RC.RC_CODE != 0) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CASH_BCHK_FLG_NO);
            }
        }
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
            JIBS_tmp_int = BPRTLT.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
            if (BPRTLT.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                IBS.init(SCCGWA, BPCPCBOX);
                BPCPCBOX.FUNC = 'Q';
                BPCPCBOX.TLR = BPCSTLSF.TLR;
                BPCPCBOX.BR = BPRTLT.NEW_BR;
                BPCPCBOX.VB_FLG = '1';
                S000_CALL_BPZPCBOX();
                if (pgmRtn) return;
                if (BPCPCBOX.RC.RC_CODE != 0) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CASH_VCHK_FLG_NO);
                }
            }
        }
    }
    public void B030_BV_CHECK() throws IOException,SQLException,Exception {
        if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
        JIBS_tmp_int = BPRTLT.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
        if (BPRTLT.TLR_STSW.substring(0, 1).equalsIgnoreCase("1")) {
            IBS.init(SCCGWA, BPCFBVTL);
            BPCFBVTL.TLR = BPCSTLSF.TLR;
            S000_CALL_BPZFBVTL();
            if (pgmRtn) return;
            if (BPCFBVTL.BV_BCHK_FLG == 'N') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BV_BCHK_FLG_NO);
            }
        }
    }
    public void B038_CASH_CHECK() throws IOException,SQLException,Exception {
        if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
        JIBS_tmp_int = BPRTLT.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
        if (BPRTLT.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.init(SCCGWA, BPCPCBOX);
            BPCPCBOX.FUNC = 'Q';
            BPCPCBOX.TLR = BPCSTLSF.TLR;
            BPCPCBOX.BR = BPRTLT.TLR_BR;
            BPCPCBOX.BR = BPRTLT.NEW_BR;
            BPCPCBOX.VB_FLG = '0';
            S000_CALL_BPZPCBOX();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPCBOX.RC.RC_CODE);
            CEP.TRC(SCCGWA, "NCB0622001");
            CEP.TRC(SCCGWA, BPCPCBOX.RC.RC_CODE);
            if (BPCPCBOX.RC.RC_CODE != 0) {
                WS_MSG_TYPE = 'W';
                WS_LVL.WS_LVL1 = 0;
                WS_LVL.WS_LVL2 = 0;
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CASH_BCHK_FLG_W;
                WS_INFO = "用户现金箱未轧账";
                S000_ERR_MSG_PROC_INFO_01();
                if (pgmRtn) return;
            }
        }
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
            JIBS_tmp_int = BPRTLT.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
            if (BPRTLT.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                IBS.init(SCCGWA, BPCPCBOX);
                BPCPCBOX.FUNC = 'Q';
                BPCPCBOX.TLR = BPCSTLSF.TLR;
                BPCPCBOX.BR = BPRTLT.NEW_BR;
                BPCPCBOX.VB_FLG = '1';
                S000_CALL_BPZPCBOX();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, "NCB0622002");
                CEP.TRC(SCCGWA, BPCPCBOX.RC.RC_CODE);
                if (BPCPCBOX.RC.RC_CODE != 0) {
                    WS_MSG_TYPE = 'W';
                    WS_LVL.WS_LVL1 = 0;
                    WS_LVL.WS_LVL2 = 0;
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CASH_VCHK_FLG_W;
                    WS_INFO = "用户现金库未轧账";
                    S000_ERR_MSG_PROC_INFO_01();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B029_VOUCHER_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "111111111111111111111111");
        CEP.TRC(SCCGWA, BPCPQORG.BNK);
        CEP.TRC(SCCGWA, BPCPQORG.BR);
        CEP.TRC(SCCGWA, BPCPQORG.VOU_CHECK_FLG);
        if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
        JIBS_tmp_int = BPRTLT.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
        CEP.TRC(SCCGWA, BPRTLT.TLR_STSW.substring(0, 1));
        if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
        JIBS_tmp_int = BPRTLT.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
        CEP.TRC(SCCGWA, BPRTLT.TLR_STSW.substring(2 - 1, 2 + 1 - 1));
        if (BPCPQORG.VOU_CHECK_FLG == 'Y') {
            IBS.init(SCCGWA, BPCFBVTL);
            CEP.TRC(SCCGWA, "222222222222222222222222");
            BPCFBVTL.TLR = BPCSTLSF.TLR;
            S000_CALL_BPZFBVTL();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCFBVTL.BV_BCHK_FLG);
            CEP.TRC(SCCGWA, BPCFBVTL.BV_VCHK_FLG);
            if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
            JIBS_tmp_int = BPRTLT.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
            if (BPRTLT.TLR_STSW.substring(0, 1).equalsIgnoreCase("1") 
                && BPCFBVTL.BV_BCHK_FLG == 'N') {
                CEP.TRC(SCCGWA, "333333333333333333333333");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BV_BCHK_FLG_NO;
                WS_ERR_INFO.WS_ERR_BR = BPCFBVTL.BOX_BR;
                S000_ERR_MSG_PROC_CHKOUT();
                if (pgmRtn) return;
            }
        }
    }
    public void B039_VOUCHER_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "111111111111111111111111");
        CEP.TRC(SCCGWA, BPCPQORG.BNK);
        CEP.TRC(SCCGWA, BPCPQORG.BR);
        CEP.TRC(SCCGWA, BPCPQORG.VOU_CHECK_FLG);
        if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
        JIBS_tmp_int = BPRTLT.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
        CEP.TRC(SCCGWA, BPRTLT.TLR_STSW.substring(0, 1));
        if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
        JIBS_tmp_int = BPRTLT.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
        CEP.TRC(SCCGWA, BPRTLT.TLR_STSW.substring(2 - 1, 2 + 1 - 1));
        IBS.init(SCCGWA, BPCFBVTL);
        CEP.TRC(SCCGWA, "222222222222222222222222");
        BPCFBVTL.TLR = BPCSTLSF.TLR;
        S000_CALL_BPZFBVTL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCFBVTL.BV_BCHK_FLG);
        CEP.TRC(SCCGWA, BPCFBVTL.BV_VCHK_FLG);
        CEP.TRC(SCCGWA, "NCB0622003");
        CEP.TRC(SCCGWA, BPCFBVTL.BV_BCHK_FLG);
        if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
        JIBS_tmp_int = BPRTLT.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
        if (BPRTLT.TLR_STSW.substring(0, 1).equalsIgnoreCase("1") 
            && BPCFBVTL.BV_BCHK_FLG == 'N') {
            CEP.TRC(SCCGWA, "333333333333333333333333");
            WS_MSG_TYPE = 'W';
            WS_LVL.WS_LVL1 = 0;
            WS_LVL.WS_LVL2 = 0;
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BV_BCHK_FLG_W;
            WS_INFO = "用户凭证箱未轧账";
            S000_ERR_MSG_PROC_INFO_01();
            if (pgmRtn) return;
        }
    }
    public void B030_AUTH_SIGNOFF_SPEC_CHECK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRTLT.SIGN_TRM);
    }
    public void B040_TEMP_SIGNOFF_SPEC_CHECK() throws IOException,SQLException,Exception {
    }
    public void B050_SUB_SIGNOFF_SPEC_CHECK() throws IOException,SQLException,Exception {
        if (BPRTLT.NEW_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_THE_SAME_BRANCH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPRTLT.TLR_TYP != 'C') {
            B031_CHECK_PENDING_TRANS();
            if (pgmRtn) return;
        }
        B038_CASH_CHECK();
        if (pgmRtn) return;
        B039_VOUCHER_CHECK();
        if (pgmRtn) return;
    }
    public void B060_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.FMT_ID = "BPVCNTY";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.JRNNO);
        BPCPNHIS.INFO.REF_NO = BPCSTLSF.TLR;
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_TYP_CD);
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B080_UPDATE_PROCESS() throws IOException,SQLException,Exception {
        for (WS_TEMP_CNT = 1; WS_TEMP_CNT <= WS_PEND_CNT; WS_TEMP_CNT += 1) {
            if (WS_PEND_INFO.WS_PEND_DATA[WS_TEMP_CNT-1].WS_BUSS_ACTION == 'C') {
                IBS.init(SCCGWA, BPCFTLPM);
                BPCFTLPM.OP_CODE = 'C';
                BPCFTLPM.BUSS_TYP = WS_PEND_INFO.WS_PEND_DATA[WS_TEMP_CNT-1].WS_BUSS_TYPE;
                BPCFTLPM.TLR = BPCSTLSF.TLR;
                S000_CALL_BPZFTLPM();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, BPRTLT);
        BPCRTLTM.INFO.FUNC = 'R';
        BPRTLT.KEY.TLR = BPCSTLSF.TLR;
        S000_CALL_BPZRTLRM();
        if (pgmRtn) return;
        R000_TRANS_DATA_PARAMETER();
        if (pgmRtn) return;
        BPCRTLTM.INFO.FUNC = 'M';
        S000_CALL_BPZRTLRM();
        if (pgmRtn) return;
    }
    public void B080_01_UPDATE_TERM_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCTERM);
        SCCTERM.FUNC = 'F';
        SCCTERM.NO = SCCGWA.COMM_AREA.TERM_ID;
        S000_CALL_SCCRTERM();
        if (pgmRtn) return;
    }
    public void B090_COUNT_PROCESS() throws IOException,SQLException,Exception {
        BPRTLT.SIGN_STS = 'O';
        BPCRTLTB.INFO.FUNC = 'G';
        BPCRTLTB.INFO.LEN = 1404;
        BPCRTLTB.INFO.POINTER = BPRTLT;
        CEP.TRC(SCCGWA, BPRTLT.SIGN_STS);
        CEP.TRC(SCCGWA, BPCRTLTB.INFO.FUNC);
        CEP.TRC(SCCGWA, BPRTLT.NEW_BR);
        S000_CALL_BPZRTLTB();
        if (pgmRtn) return;
        WS_O_CNT = BPCRTLTB.INFO.CNT;
        BPRTLT.SIGN_STS = 'T';
        BPCRTLTB.INFO.FUNC = 'G';
        BPCRTLTB.INFO.LEN = 1404;
        BPCRTLTB.INFO.POINTER = BPRTLT;
        CEP.TRC(SCCGWA, BPRTLT.SIGN_STS);
        CEP.TRC(SCCGWA, BPCRTLTB.INFO.FUNC);
        CEP.TRC(SCCGWA, BPRTLT.NEW_BR);
        S000_CALL_BPZRTLTB();
        if (pgmRtn) return;
        WS_T_CNT = BPCRTLTB.INFO.CNT;
        WS_O_T_CNT = WS_O_CNT + WS_T_CNT;
        CEP.TRC(SCCGWA, WS_O_T_CNT);
    }
    public void B090_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOTLSF);
        BPCOTLSF.O_T_CNT = WS_O_T_CNT;
        BPCOTLSF.TLR = BPRTLT.KEY.TLR;
        BPCOTLSF.TLR_BR = BPRTLT.NEW_BR;
        BPCOTLSF.TLR_TKS = BPRTLT.TLR_TKS;
        BPCOTLSF.STAF_NO = BPRTLT.STAF_NO;
        BPCOTLSF.TLR_CN_NM = BPRTLT.TLR_CN_NM;
        BPCOTLSF.TLR_EN_NM = BPRTLT.TLR_EN_NM;
        BPCOTLSF.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCOTLSF.SIGN_TIMES = BPRTLT.SIGN_TIMES;
        if (BPRTLT.TLR_TYP != 'C') {
            B090_01_ACCU_QUERY();
            if (pgmRtn) return;
            for (WS_TEMP_CNT = 1; (WS_TEMP_CNT <= 30) 
                && (BPCFBTAQ.ARRAY[WS_TEMP_CNT-1].CCY.trim().length() != 0); WS_TEMP_CNT += 1) {
                BPCOTLSF.ARRAY[WS_TEMP_CNT-1].ACCU_TYP = BPCFBTAQ.ARRAY[WS_TEMP_CNT-1].ACCU_TYP;
                BPCOTLSF.ARRAY[WS_TEMP_CNT-1].NORMAL_TX_COUNT = BPCFBTAQ.ARRAY[WS_TEMP_CNT-1].NORMAL_TX_COUNT;
                BPCOTLSF.ARRAY[WS_TEMP_CNT-1].NORMAL_AMT = BPCFBTAQ.ARRAY[WS_TEMP_CNT-1].NORMAL_AMT;
                BPCOTLSF.ARRAY[WS_TEMP_CNT-1].REVERSAL_COUNT = BPCFBTAQ.ARRAY[WS_TEMP_CNT-1].REVERSAL_COUNT;
                BPCOTLSF.ARRAY[WS_TEMP_CNT-1].REVERSAL_AMT = BPCFBTAQ.ARRAY[WS_TEMP_CNT-1].REVERSAL_AMT;
                BPCOTLSF.ARRAY[WS_TEMP_CNT-1].CANCEL_COUNT = BPCFBTAQ.ARRAY[WS_TEMP_CNT-1].CANCEL_COUNT;
                BPCOTLSF.ARRAY[WS_TEMP_CNT-1].CANCEL_AMT = BPCFBTAQ.ARRAY[WS_TEMP_CNT-1].CANCEL_AMT;
                BPCOTLSF.ARRAY[WS_TEMP_CNT-1].CCY = BPCFBTAQ.ARRAY[WS_TEMP_CNT-1].CCY;
                BPCOTLSF.PEND_CNT = WS_TEMP_CNT;
                if (BPCFBTAQ.ARRAY[WS_TEMP_CNT-1].ACCU_TYP.trim().length() > 0) {
                    IBS.init(SCCGWA, BPCOQPCD);
                    BPCOQPCD.INPUT_DATA.TYPE = "ACCU";
                    BPCOQPCD.INPUT_DATA.CODE = BPCFBTAQ.ARRAY[WS_TEMP_CNT-1].ACCU_TYP;
                    S000_CALL_BPZPQPCD();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, WS_TEMP_CNT);
                CEP.TRC(SCCGWA, BPCOTLSF.ARRAY[WS_TEMP_CNT-1].ACCU_TYP);
                CEP.TRC(SCCGWA, BPCOTLSF.ARRAY[WS_TEMP_CNT-1].CCY);
                CEP.TRC(SCCGWA, BPCOTLSF.ARRAY[WS_TEMP_CNT-1].NORMAL_TX_COUNT);
                CEP.TRC(SCCGWA, BPCOTLSF.ARRAY[WS_TEMP_CNT-1].NORMAL_AMT);
                CEP.TRC(SCCGWA, BPCOTLSF.ARRAY[WS_TEMP_CNT-1].REVERSAL_COUNT);
                CEP.TRC(SCCGWA, BPCOTLSF.ARRAY[WS_TEMP_CNT-1].REVERSAL_AMT);
                CEP.TRC(SCCGWA, BPCOTLSF.ARRAY[WS_TEMP_CNT-1].CANCEL_COUNT);
                CEP.TRC(SCCGWA, BPCOTLSF.ARRAY[WS_TEMP_CNT-1].CANCEL_AMT);
            }
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOTLSF;
        SCCFMT.DATA_LEN = 2285;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B090_01_ACCU_QUERY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFBTAQ);
        BPCFBTAQ.OP_CODE = 'T';
        BPCFBTAQ.TLR = BPCSTLSF.TLR;
        S000_CALL_BPCFBTAQ();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZRTLRM() throws IOException,SQLException,Exception {
        BPCRTLTM.INFO.POINTER = BPRTLT;
        BPCRTLTM.INFO.LEN = 1404;
        IBS.CALLCPN(SCCGWA, CPN_R_TLR_MAINTAIN, BPCRTLTM);
        if (BPCRTLTM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTLTM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRPENM() throws IOException,SQLException,Exception {
        BPCRPENM.INFO.POINTER = BPRPEND;
        BPCRPENM.INFO.LEN = 22;
        IBS.CALLCPN(SCCGWA, CPN_R_PEN_MAINTAIN, BPCRPENM);
        if (BPCRTLTM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTLTM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPCFBTAQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_ACCU_QUERY, BPCFBTAQ);
        CEP.TRC(SCCGWA, BPCFBTAQ.RC);
        if (BPCFBTAQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFBTAQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCCRTERM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_GET_TERM_INFO, SCCTERM);
        CEP.TRC(SCCGWA, SCCTERM.RC);
        if (SCCTERM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, SCCTERM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCCENPSW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_ENCRYPT_PASSWORD, SCCENPSW);
        CEP.TRC(SCCGWA, SCCENPSW.RC.RC_CODE);
        if (SCCENPSW.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, SCCENPSW.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        BPCPRMR.DAT_PTR = BPCPEND;
        IBS.CALLCPN(SCCGWA, CPN_PARM_READ, BPCPRMR);
        CEP.TRC(SCCGWA, BPCPRMR.RC.RC_RTNCODE);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFTLPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_PEND_MGM, BPCFTLPM);
        if (BPCFTLPM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLPM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPCBOX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BP_P_CHK_CBOX, BPCPCBOX);
        CEP.TRC(SCCGWA, BPCPCBOX.RC.RC_CODE);
    }
    public void S000_CALL_BPZFBVTL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_BV_QUERY, BPCFBVTL);
        if (BPCFBVTL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFBVTL.RC);
            S000_ERR_MSG_PROC_INFO();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPBANK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_QUERY_BANK, BPCPQBNK);
        if (BPCPQBNK.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQBNK.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQBCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-BCH", BPCPQBCH);
        CEP.TRC(SCCGWA, BPCPQBCH.RC);
        if (BPCPQBCH.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQBCH.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQPCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-PC         ", BPCOQPCD);
        if (BPCOQPCD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_QUERY_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC_INFO();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRTLTB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_STARTBR_TLT, BPCRTLTB);
        if (BPCRTLTB.RC.RC_CODE != 0) {
            BPCSTLSF.RC.RC_CODE = BPCRTLTB.RC.RC_CODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        if (BPCSTLSF.OPT == '0') {
            BPRTLT.SIGN_STS = 'F';
            BPRTLT.SIGN_TIMES = BPRTLT.SIGN_TIMES + 1;
            BPRTLT.SIGN_TRM = " ";
        } else if (BPCSTLSF.OPT == '1') {
            BPRTLT.SIGN_STS = 'F';
            BPRTLT.SIGN_TRM = " ";
        } else if (BPCSTLSF.OPT == '2') {
            BPRTLT.SIGN_STS = 'T';
            BPRTLT.SIGN_TIMES = BPRTLT.SIGN_TIMES + 1;
            BPRTLT.SIGN_TRM = " ";
        } else if (BPCSTLSF.OPT == '3') {
            BPRTLT.SIGN_STS = 'C';
            BPRTLT.SIGN_TIMES = BPRTLT.SIGN_TIMES + 1;
            BPRTLT.SIGN_TRM = " ";
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_INFO() throws IOException,SQLException,Exception {
        SCCMSG SCCMSG = new SCCMSG();
        IBS.init(SCCGWA, SCCMSG);
        SCCMSG.MSGID = WS_ERR_MSG;
        SCCMSG.TYPE = WS_MSG_TYPE;
        SCCMSG.INFO = WS_INFO;
        CEP.ERR(SCCGWA, SCCMSG);
    }
    public void S000_ERR_MSG_PROC_INFO_01() throws IOException,SQLException,Exception {
        SCCMSG SCCMSG = new SCCMSG();
        IBS.init(SCCGWA, SCCMSG);
        SCCMSG.MSGID = WS_ERR_MSG;
        SCCMSG.TYPE = WS_MSG_TYPE;
        SCCMSG.LVL.LVL1 = (char) WS_LVL.WS_LVL1;
        SCCMSG.LVL.LVL2 = (char) WS_LVL.WS_LVL2;
        SCCMSG.INFO = WS_INFO;
        CEP.ERR(SCCGWA, SCCMSG);
    }
    public void S000_ERR_MSG_PROC_CHKOUT() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INFO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ATH_MSG_PROC() throws IOException,SQLException,Exception {
        SCCMSG SCCMSG = new SCCMSG();
        IBS.init(SCCGWA, SCCMSG);
        SCCMSG.MSGID = WS_ERR_MSG;
        SCCMSG.TYPE = WS_MSG_TYPE;
        SCCMSG.LVL.LVL1 = (char) WS_LVL.WS_LVL1;
        SCCMSG.LVL.LVL2 = (char) WS_LVL.WS_LVL2;
        CEP.ERR(SCCGWA, SCCMSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "11111");
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
