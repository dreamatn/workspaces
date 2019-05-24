package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZGCINO {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    char K_ERROR = 'E';
    String CPN_S_GET_SEQ = "BP-S-GET-SEQ";
    String CPN_SC_CI_DIGIT = "SC-CI-DIGIT";
    short WS_J = 0;
    String WS_ERR_MSG = " ";
    BPZGCINO_WS_MSGID WS_MSGID = new BPZGCINO_WS_MSGID();
    BPZGCINO_WS_CINO_DATA_TXT WS_CINO_DATA_TXT = new BPZGCINO_WS_CINO_DATA_TXT();
    short WS_CUT_LOC = 0;
    short WS_SEQ_LOC = 0;
    short WS_CTL_LOC = 0;
    short WS_INT = 0;
    int WS_I = 0;
    String WS_CODE = " ";
    long WS_CI_NO = 0;
    BPZGCINO_REDEFINES20 REDEFINES20 = new BPZGCINO_REDEFINES20();
    char WS_END_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRCINO BPRCINO = new BPRCINO();
    BPCPRMB BPCPRMB = new BPCPRMB();
    BPRHSEQ BPRHSEQ = new BPRHSEQ();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    SCCKDGCI SCCKDGCI = new SCCKDGCI();
    SCCCTLM_MSG SCCCTLM_MSG = new SCCCTLM_MSG();
    BPCQHSEQ BPCQHSEQ = new BPCQHSEQ();
    BPCRHSEQ BPCRHSEQ = new BPCRHSEQ();
    BPCUQSEQ BPCUQSEQ = new BPCUQSEQ();
    BPCUUSEQ BPCUUSEQ = new BPCUUSEQ();
    BPCUASEQ BPCUASEQ = new BPCUASEQ();
    BPCRMSEQ BPCRMSEQ = new BPCRMSEQ();
    BPRSEQ BPRSEQ = new BPRSEQ();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCCGCI BPCCGCI;
    public void MP(SCCGWA SCCGWA, BPCCGCI BPCCGCI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCCGCI = BPCCGCI;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZGCINO return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSGSEQ);
        IBS.init(SCCGWA, SCCKDGCI);
        WS_J = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (BPCCGCI.FUN_CODE == 'A') {
            B020_GEN_CINO_PROCESS();
            if (pgmRtn) return;
            B030_GET_CI_DIGIT_PROCESS();
            if (pgmRtn) return;
            B060_CHECK_CINO_RESERVE();
            if (pgmRtn) return;
        } else if (BPCCGCI.FUN_CODE == 'U') {
            B040_USE_RES_CINO();
            if (pgmRtn) return;
        } else if (BPCCGCI.FUN_CODE == 'C') {
            B050_CAN_USE_RES_CINO();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FUNC_CD_INPUT_ERR, BPCCGCI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "INPUT:");
        CEP.TRC(SCCGWA, BPCCGCI.FUN_CODE);
        CEP.TRC(SCCGWA, BPCCGCI.BR);
        CEP.TRC(SCCGWA, BPCCGCI.CINO);
        if (BPCCGCI.FUN_CODE == ' ') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FUNC_CD_INPUT_ERR, BPCCGCI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if ((BPCCGCI.FUN_CODE == 'U' 
            || BPCCGCI.FUN_CODE == 'C') 
            && BPCCGCI.CINO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CI_MUST_INPUT, BPCCGCI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCCGCI.FUN_CODE == 'A' 
            && BPCCGCI.BR.equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BR_MUST_INPUT, BPCCGCI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_GEN_CINO_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSGSEQ);
        BPCSGSEQ.TYPE = "CINO";
        if (BPCCGCI.TYP == 'P') {
            BPCSGSEQ.CODE = "91";
        } else {
            BPCSGSEQ.CODE = "96";
        }
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSGSEQ.RUN_MODE = 'O';
        CEP.TRC(SCCGWA, BPCSGSEQ.TYPE);
        CEP.TRC(SCCGWA, BPCSGSEQ.CODE);
        CEP.TRC(SCCGWA, BPCSGSEQ.AC_DATE);
        CEP.TRC(SCCGWA, BPCSGSEQ.RUN_MODE);
        S010_CALL_BPZSGSEQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSGSEQ.RC);
        CEP.TRC(SCCGWA, BPCSGSEQ.SEQ);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BEGIN");
            CEP.TRC(SCCGWA, BPCSGSEQ.RC.RC_CODE);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
                IBS.init(SCCGWA, BPRSEQ);
                BPRSEQ.SEQ_NO = 1;
                BPRSEQ.OLD_SEQ_NO = 1;
                BPRSEQ.KEY.TYPE = "CINO";
                if (BPCCGCI.TYP == 'P') {
                    BPRSEQ.KEY.NAME = "91";
                } else {
                    BPRSEQ.KEY.NAME = "96";
                }
                BPRSEQ.DESC = " ";
                BPRSEQ.FIRST_NUM = 1;
                BPRSEQ.INIT_ZERO = 'N';
                BPRSEQ.SKIP_FLG = 'Y';
                BPRSEQ.OBL_FLG = 'Y';
                BPRSEQ.VIP_FLG = ' ';
                BPRSEQ.VAL_DATE = 19900101;
                BPRSEQ.MAX_SEQ_NO = 1000000;
                BPRSEQ.MAX_FLG = ' ';
                BPRSEQ.WARN_SEQ_NO = 1000000;
                BPRSEQ.BR = 0;
                BPRSEQ.DP = 0;
                BPRSEQ.LAST_UPD_TLR = " ";
                BPRSEQ.LAST_UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
                BPRSEQ.STEP_NUM = 1000000;
                IBS.init(SCCGWA, BPCRMSEQ);
                BPCRMSEQ.FUNC = 'C';
                BPCRMSEQ.PTR = BPRSEQ;
                BPCRMSEQ.LEN = 289;
                S000_CALL_BPZRMSEQ();
                if (pgmRtn) return;
                BPCSGSEQ.RC.RC_CODE = 0;
                IBS.init(SCCGWA, BPCSGSEQ);
                BPCSGSEQ.TYPE = "CINO";
                if (BPCCGCI.TYP == 'P') {
                    BPCSGSEQ.CODE = "91";
                } else {
                    BPCSGSEQ.CODE = "96";
                }
                BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
                BPCSGSEQ.RUN_MODE = 'O';
                S010_CALL_BPZSGSEQ();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, "LINK BPZSGSEQ");
                CEP.TRC(SCCGWA, BPCSGSEQ.RC.RC_CODE);
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
                IBS.init(SCCGWA, BPCUQSEQ);
                BPCUQSEQ.SEQ_TYPE = "CINO";
                if (BPCCGCI.TYP == 'P') {
                    BPCUQSEQ.SEQ_CODE = "91";
                } else {
                    BPCUQSEQ.SEQ_CODE = "96";
                }
                S000_CALL_BPZUQSEQ();
                if (pgmRtn) return;
                IBS.init(SCCGWA, BPCUASEQ);
                BPCUUSEQ.SEQ_TYPE = BPCSGSEQ.TYPE;
                BPCUUSEQ.SEQ_CODE = BPCSGSEQ.CODE;
                R010_TRANS_DATA();
                if (pgmRtn) return;
                S000_CALL_BPZUUSEQ();
                if (pgmRtn) return;
            }
        }
        if (BPCCGCI.TYP == 'F') {
            REDEFINES20.WS_CI_SEG1.WS_CI_TYPE = 96;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES20);
            WS_CI_NO = Long.parseLong(JIBS_tmp_str[0]);
            REDEFINES20.WS_CI_SEG1.WS_CI_SEQ = (int) BPCSGSEQ.SEQ;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES20);
            WS_CI_NO = Long.parseLong(JIBS_tmp_str[0]);
        } else if (BPCCGCI.TYP == 'C') {
            REDEFINES20.WS_CI_SEG1.WS_CI_TYPE = 96;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES20);
            WS_CI_NO = Long.parseLong(JIBS_tmp_str[0]);
            REDEFINES20.WS_CI_SEG1.WS_CI_SEQ = (int) BPCSGSEQ.SEQ;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES20);
            WS_CI_NO = Long.parseLong(JIBS_tmp_str[0]);
        } else {
            REDEFINES20.WS_CI_SEG1.WS_CI_TYPE = 91;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES20);
            WS_CI_NO = Long.parseLong(JIBS_tmp_str[0]);
            REDEFINES20.WS_CI_SEG1.WS_CI_SEQ = (int) BPCSGSEQ.SEQ;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES20);
            WS_CI_NO = Long.parseLong(JIBS_tmp_str[0]);
        }
        CEP.TRC(SCCGWA, "THE WHOLE CINO:");
        CEP.TRC(SCCGWA, BPCCGCI.CINO);
    }
    public void S000_CALL_BPZRMSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MAINT-SEQ", BPCRMSEQ);
        CEP.TRC(SCCGWA, "NORMTL OR NOT");
        CEP.TRC(SCCGWA, BPCRMSEQ.RC.RC_CODE);
        if (BPCRMSEQ.RETURN_INFO == 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_EXIST, BPCUASEQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_GET_CI_DIGIT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCKDGCI);
        SCCKDGCI.NO = "" + WS_CI_NO;
        JIBS_tmp_int = SCCKDGCI.NO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) SCCKDGCI.NO = "0" + SCCKDGCI.NO;
        SCCKDGCI.FUNC = 'G';
        CEP.TRC(SCCGWA, SCCKDGCI.FUNC);
        CEP.TRC(SCCGWA, SCCKDGCI.NO);
        CEP.TRC(SCCGWA, SCCKDGCI);
        S020_CALL_SCZKDGCI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCKDGCI.DIG);
        CEP.TRC(SCCGWA, SCCKDGCI.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCKDGCI.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(SCCCTLM_MSG.SC_ERR_GEN_DIG) 
            && WS_J < 100) {
            B020_GEN_CINO_PROCESS();
            if (pgmRtn) return;
            B030_GET_CI_DIGIT_PROCESS();
            if (pgmRtn) return;
            WS_J += WS_J;
        }
        if (SCCKDGCI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCKDGCI.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCCGCI.RC);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, SCCKDGCI.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            REDEFINES20.WS_CI_DIG = SCCKDGCI.DIG;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES20);
            WS_CI_NO = Long.parseLong(JIBS_tmp_str[0]);
        }
        BPCCGCI.CINO = "" + WS_CI_NO;
        JIBS_tmp_int = BPCCGCI.CINO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) BPCCGCI.CINO = "0" + BPCCGCI.CINO;
        CEP.TRC(SCCGWA, BPCCGCI.CINO);
    }
    public void R000_TRANS_DATA() throws IOException,SQLException,Exception {
        BPCUASEQ.SEQ_NO = 0;
        BPCUASEQ.OLD_SEQ_NO = 0;
        JIBS_tmp_str[0] = "" + WS_CI_NO;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<12-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCUASEQ.SEQ_DESC = JIBS_tmp_str[0].substring(0, 5);
        BPCUASEQ.FIRST_NUM = BPCUQSEQ.FIRST_NUM;
        BPCUASEQ.INIT_ZERO = BPCUQSEQ.INIT_ZERO;
        BPCUASEQ.SKIP_FLG = BPCUQSEQ.SKIP_FLG;
        BPCUASEQ.OBL_FLG = BPCUQSEQ.OBL_FLG;
        BPCUASEQ.MAX_SEQ_NO = BPCUQSEQ.MAX_SEQ_NO;
        BPCUASEQ.MAX_FLG = BPCUQSEQ.MAX_FLG;
        BPCUASEQ.WARN_SEQ_NO = BPCUQSEQ.WARN_SEQ_NO;
        BPCUASEQ.STEP_NUM = (short) BPCUQSEQ.STEP_NUM;
        BPCUASEQ.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCUASEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 3).trim().length() == 0) BPCUASEQ.BR = 0;
        else BPCUASEQ.BR = Short.parseShort(JIBS_tmp_str[0].substring(0, 3));
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(4 - 1, 4 + 3 - 1).trim().length() == 0) BPCUASEQ.DP = 0;
        else BPCUASEQ.DP = Short.parseShort(JIBS_tmp_str[0].substring(4 - 1, 4 + 3 - 1));
        BPCUASEQ.TLR = SCCGWA.COMM_AREA.TL_ID;
        if ((BPCCGCI.TYP != 'F') 
            && (BPCCGCI.TYP != 'C')) {
            BPCUASEQ.FIRST_NUM = BPCUASEQ.FIRST_NUM + 1000000;
            BPCUASEQ.MAX_SEQ_NO = BPCUASEQ.MAX_SEQ_NO + 1000000;
            BPCUASEQ.WARN_SEQ_NO = BPCUASEQ.WARN_SEQ_NO + 1000000;
        }
    }
    public void R010_TRANS_DATA() throws IOException,SQLException,Exception {
        BPCUUSEQ.SEQ_NO = 0;
        BPCUUSEQ.OLD_SEQ_NO = 0;
        JIBS_tmp_str[0] = "" + WS_CI_NO;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<12-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCUUSEQ.SEQ_DESC = JIBS_tmp_str[0].substring(0, 5);
        BPCUUSEQ.FIRST_NUM = BPCUQSEQ.FIRST_NUM;
        BPCUUSEQ.INIT_ZERO = BPCUQSEQ.INIT_ZERO;
        BPCUUSEQ.SKIP_FLG = BPCUQSEQ.SKIP_FLG;
        BPCUUSEQ.OBL_FLG = BPCUQSEQ.OBL_FLG;
        BPCUUSEQ.MAX_SEQ_NO = BPCUQSEQ.MAX_SEQ_NO;
        BPCUUSEQ.MAX_FLG = BPCUQSEQ.MAX_FLG;
        BPCUUSEQ.WARN_SEQ_NO = BPCUQSEQ.WARN_SEQ_NO;
        BPCUUSEQ.STEP_NUM = (short) BPCUQSEQ.STEP_NUM;
        BPCUUSEQ.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 3).trim().length() == 0) BPCUUSEQ.BR = 0;
        else BPCUUSEQ.BR = Short.parseShort(JIBS_tmp_str[0].substring(0, 3));
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(4 - 1, 4 + 3 - 1).trim().length() == 0) BPCUUSEQ.DP = 0;
        else BPCUUSEQ.DP = Short.parseShort(JIBS_tmp_str[0].substring(4 - 1, 4 + 3 - 1));
        if ((BPCCGCI.TYP != 'F') 
            && (BPCCGCI.TYP != 'C')) {
            BPCUUSEQ.FIRST_NUM = BPCUUSEQ.FIRST_NUM + 100000;
            BPCUUSEQ.MAX_SEQ_NO = BPCUUSEQ.MAX_SEQ_NO + 100000;
            BPCUUSEQ.WARN_SEQ_NO = BPCUUSEQ.WARN_SEQ_NO + 100000;
        }
    }
    public void S000_CALL_BPZUQSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-INQ-SEQ", BPCUQSEQ);
        if (BPCUQSEQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUQSEQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCCGCI.RC);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUQSEQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZUUSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-UPD-SEQ", BPCUUSEQ);
        if (BPCUQSEQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUQSEQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCCGCI.RC);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUQSEQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZUASEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-ADD-SEQ", BPCUASEQ);
        if (BPCUASEQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUASEQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCCGCI.RC);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUASEQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B040_USE_RES_CINO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRHSEQ);
        IBS.init(SCCGWA, BPCRHSEQ);
        BPCRHSEQ.INFO.FUNC = 'R';
        BPRHSEQ.KEY.TYPE = "CINO";
        if (BPCCGCI.TYP == 'P') {
            BPRHSEQ.KEY.CODE = "91";
        } else {
            BPRHSEQ.KEY.CODE = "96";
        }
        if (BPCCGCI.CINO == null) BPCCGCI.CINO = "";
        JIBS_tmp_int = BPCCGCI.CINO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) BPCCGCI.CINO += " ";
        if (BPCCGCI.CINO.substring(3 - 1, 3 + 8 - 1).trim().length() == 0) BPRHSEQ.KEY.SEQ_NO = 0;
        else BPRHSEQ.KEY.SEQ_NO = Long.parseLong(BPCCGCI.CINO.substring(3 - 1, 3 + 8 - 1));
        CEP.TRC(SCCGWA, BPRHSEQ.KEY.TYPE);
        CEP.TRC(SCCGWA, BPRHSEQ.KEY.CODE);
        CEP.TRC(SCCGWA, BPRHSEQ.KEY.SEQ_NO);
        BPCRHSEQ.LEN = 558;
        BPCRHSEQ.INFO.POINTER = BPRHSEQ;
        S000_CALL_BPZRHSEQ();
        if (pgmRtn) return;
        if (BPRHSEQ.USED_FLAG == 'Y') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_OBL_CINO_IS_USED, BPCCGCI.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, BPCRHSEQ);
            BPRHSEQ.USED_FLAG = 'Y';
            BPRHSEQ.USED_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCRHSEQ.INFO.FUNC = 'U';
            BPCRHSEQ.LEN = 558;
            BPCRHSEQ.INFO.POINTER = BPRHSEQ;
            S000_CALL_BPZRHSEQ();
            if (pgmRtn) return;
        }
    }
    public void B050_CAN_USE_RES_CINO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRHSEQ);
        IBS.init(SCCGWA, BPCRHSEQ);
        BPCRHSEQ.INFO.FUNC = 'R';
        BPRHSEQ.KEY.TYPE = "CINO";
        if (BPCCGCI.TYP == 'P') {
            BPRHSEQ.KEY.CODE = "91";
        } else {
            BPRHSEQ.KEY.CODE = "96";
        }
        if (BPCCGCI.CINO == null) BPCCGCI.CINO = "";
        JIBS_tmp_int = BPCCGCI.CINO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) BPCCGCI.CINO += " ";
        if (BPCCGCI.CINO.substring(3 - 1, 3 + 8 - 1).trim().length() == 0) BPRHSEQ.KEY.SEQ_NO = 0;
        else BPRHSEQ.KEY.SEQ_NO = Long.parseLong(BPCCGCI.CINO.substring(3 - 1, 3 + 8 - 1));
        CEP.TRC(SCCGWA, BPRHSEQ.KEY.TYPE);
        CEP.TRC(SCCGWA, BPRHSEQ.KEY.CODE);
        CEP.TRC(SCCGWA, BPRHSEQ.KEY.SEQ_NO);
        BPCRHSEQ.LEN = 558;
        BPCRHSEQ.INFO.POINTER = BPRHSEQ;
        S000_CALL_BPZRHSEQ();
        if (pgmRtn) return;
        if (BPRHSEQ.USED_FLAG != 'Y') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_OBL_CINO_IS_NOT_USED, BPCCGCI.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, BPCRHSEQ);
            BPRHSEQ.USED_FLAG = 'N';
            BPCRHSEQ.INFO.FUNC = 'U';
            BPCRHSEQ.LEN = 558;
            BPCRHSEQ.INFO.POINTER = BPRHSEQ;
            S000_CALL_BPZRHSEQ();
            if (pgmRtn) return;
        }
    }
    public void B060_CHECK_CINO_RESERVE() throws IOException,SQLException,Exception {
        WS_END_FLG = 'N';
        B060_10_CHECK_CINO_PROC();
        if (pgmRtn) return;
        for (WS_I = 1; WS_I <= 5000 
            && WS_END_FLG != 'Y'; WS_I += 1) {
            B020_GEN_CINO_PROCESS();
            if (pgmRtn) return;
            B060_10_CHECK_CINO_PROC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "HOW MANY TIMES GET CINO:");
            CEP.TRC(SCCGWA, WS_I);
        }
        CEP.TRC(SCCGWA, "THE FINAL CINO:");
        CEP.TRC(SCCGWA, BPCCGCI.CINO);
    }
    public void B060_10_CHECK_CINO_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQHSEQ);
        IBS.init(SCCGWA, BPRHSEQ);
        BPRHSEQ.CI_NO = BPCCGCI.CINO;
        BPCQHSEQ.INFO.FUNC = 'C';
        BPCQHSEQ.INFO.POINTER = BPRHSEQ;
        BPCQHSEQ.LEN = 558;
        CEP.TRC(SCCGWA, BPCCGCI.CINO);
        S000_CALL_BPZQHSEQ();
        if (pgmRtn) return;
        if (BPCQHSEQ.RETURN_INFO != 'N') {
            CEP.TRC(SCCGWA, "THE CINO HAD BEEN RESERVED:");
            CEP.TRC(SCCGWA, BPCCGCI.CINO);
            WS_END_FLG = 'N';
        } else {
            CEP.TRC(SCCGWA, "THE CINO IS NOT RESERVED:");
            CEP.TRC(SCCGWA, BPCCGCI.CINO);
            WS_END_FLG = 'Y';
        }
    }
    public void S010_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_GET_SEQ, BPCSGSEQ);
    }
    public void S000_CALL_BPZRHSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-HSEQ-MAINT", BPCRHSEQ);
        if (BPCRHSEQ.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PRE_HLD_CI_NOT_EXIST, BPCCGCI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S020_CALL_SCZKDGCI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_SC_CI_DIGIT, SCCKDGCI);
    }
    public void S000_CALL_BPZQHSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-HSEQ-INQ", BPCQHSEQ);
    }
    public void S000_CALL_BPZPRMB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-BROWSE", BPCPRMB);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCCGCI.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCCGCI = ");
            CEP.TRC(SCCGWA, BPCCGCI);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
