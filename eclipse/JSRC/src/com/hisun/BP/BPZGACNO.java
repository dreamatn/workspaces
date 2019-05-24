package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.CI.*;
import java.util.Date;
import java.text.SimpleDateFormat;

import java.io.IOException;
import java.sql.SQLException;

public class BPZGACNO {
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTACOBL_RD;
    int JIBS_tmp_int;
    SimpleDateFormat JIBS_sdf;
    Date JIBS_date;
    String JIBS_NumStr;
    String JIBS_f0;
    boolean pgmRtn = false;
    String K_MMO = "BP";
    String K_PGM_NAME = "BPZGACNO";
    String K_CMP_CALL_BPZPQPRD = "BP-P-QUERY-PRDT-INFO";
    String K_CMP_CALL_BPZSGSEQ = "BP-S-GET-SEQ   ";
    String K_CMP_CALL_BPZSGOBL = "BP-S-GET-OBL   ";
    String K_CMP_CALL_BPZKDGAC = "BP-AC-DIGIT    ";
    String K_CMP_CALL_CIZCUST = "CI-INQ-CUST    ";
    String K_CMP_CALL_BPZUASEQ = "BP-U-ADD-SEQ   ";
    String K_CI_BASE_SEQ = "ACSEQ";
    String K_EQ_BASE_SEQ = "EQ";
    String K_CU_CI_SEQ_T = "CUCI";
    String K_LN_SUB_SEQ = "000";
    String K_LN_AC_DIG = "00";
    String K_PRDT_INF_MAINT = "BP-S-MAINT-PRDT-INFO";
    String WS_ERR_MSG = " ";
    short WS_CNT = 0;
    String WS_CI_AC = " ";
    BPZGACNO_WS_CI_AC_CN WS_CI_AC_CN = new BPZGACNO_WS_CI_AC_CN();
    BPZGACNO_WS_CI_AC_NCB WS_CI_AC_NCB = new BPZGACNO_WS_CI_AC_NCB();
    BPZGACNO_WS_CI_AC_SEG WS_CI_AC_SEG = new BPZGACNO_WS_CI_AC_SEG();
    String WS_ACO_AC = " ";
    BPZGACNO_WS_ACO_AC_CN WS_ACO_AC_CN = new BPZGACNO_WS_ACO_AC_CN();
    BPZGACNO_WS_ACO_AC_SEG WS_ACO_AC_SEG = new BPZGACNO_WS_ACO_AC_SEG();
    String WS_EQ_AC = " ";
    BPZGACNO_WS_EQ_AC_CN WS_EQ_AC_CN = new BPZGACNO_WS_EQ_AC_CN();
    BPZGACNO_WS_EQ_AC_SEG WS_EQ_AC_SEG = new BPZGACNO_WS_EQ_AC_SEG();
    String WS_AC_NO = " ";
    BPZGACNO_REDEFINES46 REDEFINES46 = new BPZGACNO_REDEFINES46();
    BPZGACNO_WS_AC_NO_CN WS_AC_NO_CN = new BPZGACNO_WS_AC_NO_CN();
    long WS_OBL_KEY = 0;
    String WS_CU_CI = " ";
    BPZGACNO_REDEFINES64 REDEFINES64 = new BPZGACNO_REDEFINES64();
    short WS_CU_CI1 = 0;
    short WS_CU_CI2 = 0;
    short WS_CU_CI3 = 0;
    short WS_CU_CI4 = 0;
    short WS_CU_CI5 = 0;
    BPZGACNO_WS_TYPE_DISTART WS_TYPE_DISTART = new BPZGACNO_WS_TYPE_DISTART();
    long WK_SEED = 0;
    long WK_VALUE = 0;
    String WK_DATE_TIME = " ";
    int WK_TIME = 0;
    long RAND_VALUE = 0;
    short WS_DGT = 0;
    short WS_RET = 0;
    short WS_RMDR = 0;
    short WS_RMDR1 = 0;
    BPZGACNO_WS_AC WS_AC = new BPZGACNO_WS_AC();
    int WS_COUNT = 0;
    int WS_RET2 = 0;
    char WS_FIRST_CREATE_FLG = ' ';
    char WS_OVER_SUB_SEQ_FLG = ' ';
    char WS_AC_OBL_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCCTLM_MSG SCCCTLM_MSG = new SCCCTLM_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    BPCSGOBL BPCSGOBL = new BPCSGOBL();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCUQSEQ BPCUQSEQ = new BPCUQSEQ();
    BPCUASEQ BPCUASEQ = new BPCUASEQ();
    CICCUST CICCUST = new CICCUST();
    BPRACOBL BPRACOBL = new BPRACOBL();
    BPCSMPRD BPCSMPRD = new BPCSMPRD();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCRMSEQ BPCRMSEQ = new BPCRMSEQ();
    BPRSEQ BPRSEQ = new BPRSEQ();
    BPCKDGAC BPCKDGAC = new BPCKDGAC();
    SCCGWA SCCGWA;
    BPCCGAC BPCCGAC;
    public void MP(SCCGWA SCCGWA, BPCCGAC BPCCGAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCCGAC = BPCCGAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZGACNO return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSGSEQ);
        IBS.init(SCCGWA, BPCSGOBL);
        IBS.init(SCCGWA, BPCPQPRD);
        IBS.init(SCCGWA, BPCKDGAC);
        WS_AC_NO = BPCCGAC.DATA.AC_NO;
        IBS.CPY2CLS(SCCGWA, WS_AC_NO, REDEFINES46);
        IBS.CPY2CLS(SCCGWA, WS_AC_NO, WS_AC_NO_CN);
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCCGAC.RC);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_CHECK_INPUT_CN();
            if (pgmRtn) return;
            if (BPCCGAC.DATA.AC_KIND == '1') {
                WS_AC_NO_CN.WS_AC_SEG2.WS_AC_TIT = "95508";
                WS_TYPE_DISTART.WS_SEQ_TYPE = "ACNO";
                WS_TYPE_DISTART.WS_SEQ_MAX = 999;
                B030_GET_SAVE_CI_AC_NCB();
                if (pgmRtn) return;
            } else if (BPCCGAC.DATA.AC_KIND == '2') {
                WS_AC_NO_CN.WS_AC_SEG2.WS_AC_TIT = "85508";
                WS_TYPE_DISTART.WS_SEQ_TYPE = "TDSUB";
                WS_TYPE_DISTART.WS_SEQ_MAX = 99999;
                B050_GET_SAVE_ACO_AC_CN();
                if (pgmRtn) return;
            } else if (BPCCGAC.DATA.AC_KIND == '3') {
                WS_AC_NO_CN.WS_AC_SEG2.WS_AC_TIT = "95508";
                WS_TYPE_DISTART.WS_SEQ_TYPE = "ACNO";
                WS_TYPE_DISTART.WS_SEQ_MAX = 999;
                B070_GET_SAVE_EQ_AC_CN();
                if (pgmRtn) return;
                WS_AC_NO_CN.WS_AC_SEG2.WS_SUB_SEQ = K_LN_SUB_SEQ;
                WS_AC_NO = IBS.CLS2CPY(SCCGWA, WS_AC_NO_CN);
                WS_AC_NO_CN.WS_AC_DIG2 = K_LN_AC_DIG;
                WS_AC_NO = IBS.CLS2CPY(SCCGWA, WS_AC_NO_CN);
            } else if (BPCCGAC.DATA.AC_KIND == '4') {
                B080_GET_SAVE_CU_CI();
                if (pgmRtn) return;
            } else {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_AC_TYPE_UNRIGHT, BPCCGAC.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
            B010_CHECK_INPUT();
            if (pgmRtn) return;
            B020_GET_PRD_NO();
            if (pgmRtn) return;
            B030_GET_SEQ_NO();
            if (pgmRtn) return;
            B040_GET_VERIFY_CODE();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCCGAC.DATA.PRD_CODE);
        if (BPCCGAC.DATA.PRD_CODE.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCCGAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_CN() throws IOException,SQLException,Exception {
        if (BPCCGAC.DATA.AC_KIND == '1') {
            B011_CHECK_INPUT_CI_AC();
            if (pgmRtn) return;
        } else if (BPCCGAC.DATA.AC_KIND == '2') {
            B012_CHECK_INPUT_ACO_AC();
            if (pgmRtn) return;
        } else if (BPCCGAC.DATA.AC_KIND == '3') {
            B013_CHECK_INPUT_EQ_AC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_AC_TYPE_UNRIGHT, BPCCGAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B011_CHECK_INPUT_CI_AC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCCGAC.DATA.CI_AC_FLG);
        if (BPCCGAC.DATA.CI_AC_FLG == ' ') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_INPUT_AC_FLG, BPCCGAC.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            if (BPCCGAC.DATA.CI_AC_FLG != '5' 
                && BPCCGAC.DATA.CI_AC_FLG != '6' 
                && BPCCGAC.DATA.CI_AC_FLG != '7') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_AC_FLG_NOTRIGHT, BPCCGAC.RC);
            }
        }
        CEP.TRC(SCCGWA, BPCCGAC.DATA.CI_AC_TYPE);
        if (BPCCGAC.DATA.CI_AC_TYPE == ' ') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCCGAC.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            if (BPCCGAC.DATA.CI_AC_TYPE == '1' 
                || BPCCGAC.DATA.CI_AC_TYPE == '2') {
            } else {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_AC_TYPE_NOTRIGHT, BPCCGAC.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B012_CHECK_INPUT_ACO_AC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCCGAC.DATA.ACO_AC_MMO);
        if (BPCCGAC.DATA.ACO_AC_MMO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_E_ERR_MMO, BPCCGAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCCGAC.DATA.ACO_AC_DEF);
        if (BPCCGAC.DATA.ACO_AC_DEF == ' ') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCCGAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B013_CHECK_INPUT_EQ_AC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCCGAC.DATA.BR);
        if (BPCCGAC.DATA.BR == ' ') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_INPUT_BR, BPCCGAC.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, BPCCGAC.DATA.BR);
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
            BPCPQORG.BR = BPCCGAC.DATA.BR;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, BPCCGAC.DATA.EQ_CCY);
        if (BPCCGAC.DATA.EQ_CCY.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_IN_CCY, BPCCGAC.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            if (!BPCCGAC.DATA.EQ_CCY.equalsIgnoreCase("156")) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_HIS_CCY_ERR, BPCCGAC.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B030_GET_SAVE_CI_AC_CN() throws IOException,SQLException,Exception {
        B030_GET_CI_AC_FLG();
        if (pgmRtn) return;
        B030_GET_CI_AC_TYPE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCCGAC.DATA.CI_RANDOM);
        if (BPCCGAC.DATA.CI_RANDOM.trim().length() == 0) {
            CEP.TRC(SCCGWA, "AAA1");
            B030_GET_CI_AC_RAND();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "AAA2");
            if (BPCCGAC.DATA.CI_RANDOM.trim().length() == 0) WS_CI_AC_CN.WS_CI_AC_RAND = 0;
            else WS_CI_AC_CN.WS_CI_AC_RAND = Short.parseShort(BPCCGAC.DATA.CI_RANDOM);
            WS_CI_AC = IBS.CLS2CPY(SCCGWA, WS_CI_AC_CN);
            if (BPCCGAC.DATA.CI_RANDOM.trim().length() == 0) WS_CI_AC_SEG.WS_CI_AC_RAND1 = 0;
            else WS_CI_AC_SEG.WS_CI_AC_RAND1 = Short.parseShort(BPCCGAC.DATA.CI_RANDOM);
        }
        CEP.TRC(SCCGWA, BPCCGAC.DATA.CI_SEQ);
        if (BPCCGAC.DATA.CI_SEQ == 0) {
            CEP.TRC(SCCGWA, "AAA3");
            B030_GET_CI_AC_SEQ();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "AAA4");
            WS_CI_AC_CN.WS_CI_AC_SEQ = BPCCGAC.DATA.CI_SEQ;
            WS_CI_AC = IBS.CLS2CPY(SCCGWA, WS_CI_AC_CN);
        }
        B030_GET_CI_AC_DIG();
        if (pgmRtn) return;
        BPCCGAC.DATA.CI_AC = WS_CI_AC;
    }
    public void B030_GET_SAVE_CI_AC_NCB() throws IOException,SQLException,Exception {
        WS_CI_AC = " ";
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        WS_CI_AC_NCB.WS_CI_AC_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        WS_CI_AC = IBS.CLS2CPY(SCCGWA, WS_CI_AC_NCB);
        if (BPCCGAC.DATA.CI_SEQ == 0) {
            CEP.TRC(SCCGWA, "GET NEW SEQ");
            IBS.init(SCCGWA, BPCSGSEQ);
            BPCSGSEQ.TYPE = K_CI_BASE_SEQ;
            BPCSGSEQ.CODE = "NCBAC";
            BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCSGSEQ.RUN_MODE = 'O';
            S000_CALL_BPZSGSEQ();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCSGSEQ.SEQ);
            WS_CI_AC_NCB.WS_CI_AC_SEQ_9 = (int) BPCSGSEQ.SEQ;
            WS_CI_AC = IBS.CLS2CPY(SCCGWA, WS_CI_AC_NCB);
            IBS.init(SCCGWA, BPRACOBL);
            BPRACOBL.KEY.AC_NO_SEQ = WS_CI_AC_NCB.WS_CI_AC_SEQ_9;
            BPTACOBL_RD = new DBParm();
            BPTACOBL_RD.TableName = "BPTACOBL";
            BPTACOBL_RD.eqWhere = "AC_NO_SEQ";
            BPTACOBL_RD.fst = true;
            IBS.READ(SCCGWA, BPRACOBL, BPTACOBL_RD);
            while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
                IBS.init(SCCGWA, BPCSGSEQ);
                BPCSGSEQ.TYPE = K_CI_BASE_SEQ;
                BPCSGSEQ.CODE = "NCBAC";
                BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
                BPCSGSEQ.RUN_MODE = 'O';
                S000_CALL_BPZSGSEQ();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCSGSEQ.SEQ);
                WS_CI_AC_NCB.WS_CI_AC_SEQ_9 = (int) BPCSGSEQ.SEQ;
                WS_CI_AC = IBS.CLS2CPY(SCCGWA, WS_CI_AC_NCB);
                IBS.init(SCCGWA, BPRACOBL);
                BPRACOBL.KEY.AC_NO_SEQ = WS_CI_AC_NCB.WS_CI_AC_SEQ_9;
                BPTACOBL_RD = new DBParm();
                BPTACOBL_RD.TableName = "BPTACOBL";
                BPTACOBL_RD.eqWhere = "AC_NO_SEQ";
                BPTACOBL_RD.fst = true;
                IBS.READ(SCCGWA, BPRACOBL, BPTACOBL_RD);
            }
        } else {
            CEP.TRC(SCCGWA, BPCCGAC.DATA.CI_SEQ);
            WS_CI_AC_NCB.WS_CI_AC_SEQ_9 = BPCCGAC.DATA.CI_SEQ;
            WS_CI_AC = IBS.CLS2CPY(SCCGWA, WS_CI_AC_NCB);
        }
        CEP.TRC(SCCGWA, WS_CI_AC);
        IBS.init(SCCGWA, WS_AC);
        if (WS_CI_AC == null) WS_CI_AC = "";
        JIBS_tmp_int = WS_CI_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) WS_CI_AC += " ";
        IBS.CPY2CLS(SCCGWA, WS_CI_AC.substring(0, 15), WS_AC);
        WS_COUNT = WS_AC.WS_AC_1 * 2 + WS_AC.WS_AC_2 * 3 + WS_AC.WS_AC_3 * 5 + WS_AC.WS_AC_4 * 7 + WS_AC.WS_AC_5 * 11 + WS_AC.WS_AC_6 * 13 + WS_AC.WS_AC_7 * 17 + WS_AC.WS_AC_8 * 19 + WS_AC.WS_AC_9 * 23 + WS_AC.WS_AC_10 * 29 + WS_AC.WS_AC_11 * 31 + WS_AC.WS_AC_12 * 37 + WS_AC.WS_AC_13 * 41 + WS_AC.WS_AC_14 * 43 + WS_AC.WS_AC_15 * 47;
        CEP.TRC(SCCGWA, WS_COUNT);
        WS_CI_AC_NCB.WS_CI_AC_CHK = (short) (WS_COUNT % 10);
        WS_RET2 = (int) ((WS_COUNT - WS_CI_AC_NCB.WS_CI_AC_CHK) / 10);
        CEP.TRC(SCCGWA, WS_CI_AC_NCB.WS_CI_AC_CHK);
        CEP.TRC(SCCGWA, WS_CI_AC);
        BPCCGAC.DATA.CI_AC = WS_CI_AC;
    }
    public void B030_GET_CI_AC_FLG() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCCGAC.DATA.CI_AC_FLG);
        WS_CI_AC_CN.WS_CI_AC_FLG = BPCCGAC.DATA.CI_AC_FLG;
        WS_CI_AC = IBS.CLS2CPY(SCCGWA, WS_CI_AC_CN);
        WS_CI_AC_SEG.WS_CI_AC_FLG1 = BPCCGAC.DATA.CI_AC_FLG;
        CEP.TRC(SCCGWA, WS_CI_AC_CN.WS_CI_AC_FLG);
    }
    public void B030_GET_CI_AC_TYPE() throws IOException,SQLException,Exception {
        WS_CI_AC_CN.WS_CI_AC_TYPE = BPCCGAC.DATA.CI_AC_TYPE;
        WS_CI_AC = IBS.CLS2CPY(SCCGWA, WS_CI_AC_CN);
        WS_CI_AC_SEG.WS_CI_AC_TYPE1 = BPCCGAC.DATA.CI_AC_TYPE;
        CEP.TRC(SCCGWA, WS_CI_AC_CN.WS_CI_AC_TYPE);
    }
    public void B030_GET_CI_AC_RANDOM() throws IOException,SQLException,Exception {
        JIBS_sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        JIBS_date = new Date();
        WK_DATE_TIME = JIBS_sdf.format(JIBS_date);
        CEP.TRC(SCCGWA, WK_DATE_TIME);
        if (WK_DATE_TIME == null) WK_DATE_TIME = "";
        JIBS_tmp_int = WK_DATE_TIME.length();
        for (int i=0;i<21-JIBS_tmp_int;i++) WK_DATE_TIME += " ";
        if (WK_DATE_TIME.substring(0, 16).trim().length() == 0) WK_SEED = 0;
        else WK_SEED = Long.parseLong(WK_DATE_TIME.substring(0, 16));
        CEP.TRC(SCCGWA, WK_SEED);
        JIBS_tmp_str[0] = "" + WK_SEED;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<16-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(9 - 1, 9 + 8 - 1).trim().length() == 0) WK_TIME = 0;
        else WK_TIME = Integer.parseInt(JIBS_tmp_str[0].substring(9 - 1, 9 + 8 - 1));
        CEP.TRC(SCCGWA, WK_TIME);
        CEP.TRC(SCCGWA, "WK-SEED1");
        CEP.TRC(SCCGWA, WK_SEED);
        WK_VALUE = (long) (Math.random() * 1000000000 * 1000000);
        CEP.TRC(SCCGWA, WK_VALUE);
        JIBS_tmp_str[0] = "" + RAND_VALUE;
        JIBS_f0 = "";
        for (int i=0;i<30-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + RAND_VALUE;
        JIBS_tmp_str[1] = "" + WK_VALUE;
        JIBS_tmp_int = JIBS_tmp_str[1].length();
        for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
        JIBS_NumStr = JIBS_tmp_str[1] + JIBS_NumStr.substring(15);
        RAND_VALUE = Long.parseLong(JIBS_NumStr);
        CEP.TRC(SCCGWA, "RAND-VALUE1");
        CEP.TRC(SCCGWA, RAND_VALUE);
        WK_TIME = WK_TIME + 10000;
        JIBS_tmp_str[0] = "" + WK_SEED;
        JIBS_f0 = "";
        for (int i=0;i<16-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + WK_SEED;
        JIBS_tmp_str[1] = "" + WK_TIME;
        JIBS_tmp_int = JIBS_tmp_str[1].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
        JIBS_NumStr = JIBS_NumStr.substring(0, 9 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(9 + 8 - 1);
        WK_SEED = Long.parseLong(JIBS_NumStr);
        CEP.TRC(SCCGWA, WK_SEED);
        WK_VALUE = (long) (Math.random() * 1000000000 * 1000000);
        CEP.TRC(SCCGWA, WK_VALUE);
        JIBS_tmp_str[0] = "" + RAND_VALUE;
        JIBS_f0 = "";
        for (int i=0;i<30-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + RAND_VALUE;
        JIBS_tmp_str[1] = "" + WK_VALUE;
        JIBS_tmp_int = JIBS_tmp_str[1].length();
        for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
        JIBS_NumStr = JIBS_NumStr.substring(0, 16 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(16 + 15 - 1);
        RAND_VALUE = Long.parseLong(JIBS_NumStr);
        CEP.TRC(SCCGWA, RAND_VALUE);
        JIBS_tmp_str[0] = "" + RAND_VALUE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<30-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(30 - 1, 30 + 1 - 1).trim().length() == 0) WS_CI_AC_CN.WS_CI_AC_RAND = 0;
        else WS_CI_AC_CN.WS_CI_AC_RAND = Short.parseShort(JIBS_tmp_str[0].substring(30 - 1, 30 + 1 - 1));
        WS_CI_AC = IBS.CLS2CPY(SCCGWA, WS_CI_AC_CN);
        JIBS_tmp_str[0] = "" + RAND_VALUE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<30-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(30 - 1, 30 + 1 - 1).trim().length() == 0) WS_CI_AC_SEG.WS_CI_AC_RAND1 = 0;
        else WS_CI_AC_SEG.WS_CI_AC_RAND1 = Short.parseShort(JIBS_tmp_str[0].substring(30 - 1, 30 + 1 - 1));
        CEP.TRC(SCCGWA, WS_CI_AC_SEG.WS_CI_AC_RAND1);
        CEP.TRC(SCCGWA, WS_CI_AC_CN.WS_CI_AC_RAND);
    }
    public void B030_GET_CI_AC_RAND() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.JRN_NO;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<12-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(11 - 1, 11 + 2 - 1).trim().length() == 0) WS_CI_AC_CN.WS_CI_AC_RAND = 0;
        else WS_CI_AC_CN.WS_CI_AC_RAND = Short.parseShort(JIBS_tmp_str[0].substring(11 - 1, 11 + 2 - 1));
        WS_CI_AC = IBS.CLS2CPY(SCCGWA, WS_CI_AC_CN);
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.JRN_NO;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<12-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(11 - 1, 11 + 2 - 1).trim().length() == 0) WS_CI_AC_SEG.WS_CI_AC_RAND1 = 0;
        else WS_CI_AC_SEG.WS_CI_AC_RAND1 = Short.parseShort(JIBS_tmp_str[0].substring(11 - 1, 11 + 2 - 1));
        CEP.TRC(SCCGWA, WS_CI_AC_SEG.WS_CI_AC_RAND1);
        CEP.TRC(SCCGWA, WS_CI_AC_CN.WS_CI_AC_RAND);
    }
    public void B030_GET_CI_AC_SEQ() throws IOException,SQLException,Exception {
        B031_CREATE_BASE_SEQ_CN();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSGSEQ.SEQ);
        JIBS_tmp_str[0] = "" + BPCSGSEQ.SEQ;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(8 - 1, 8 + 8 - 1).trim().length() == 0) WS_CI_AC_CN.WS_CI_AC_SEQ = 0;
        else WS_CI_AC_CN.WS_CI_AC_SEQ = Integer.parseInt(JIBS_tmp_str[0].substring(8 - 1, 8 + 8 - 1));
        WS_CI_AC = IBS.CLS2CPY(SCCGWA, WS_CI_AC_CN);
        CEP.TRC(SCCGWA, WS_CI_AC_CN.WS_CI_AC_SEQ);
    }
    public void B030_GET_CI_AC_DIG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCKDGAC);
        WS_CI_AC_SEG.WS_CI_AC_SEQ1 = WS_CI_AC_CN.WS_CI_AC_SEQ;
        CEP.TRC(SCCGWA, WS_CI_AC_SEG);
        if (BPCKDGAC.NO == null) BPCKDGAC.NO = "";
        JIBS_tmp_int = BPCKDGAC.NO.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) BPCKDGAC.NO += " ";
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_CI_AC_SEG);
        BPCKDGAC.NO = JIBS_tmp_str[0] + BPCKDGAC.NO.substring(12);
        CEP.TRC(SCCGWA, BPCKDGAC.NO);
        BPCKDGAC.LEN = 12;
        BPCKDGAC.FUNC = 'G';
        BPCKDGAC.TYPE_FLG = '2';
        S000_CALL_BPZKDGAC();
        if (pgmRtn) return;
        S000_CHECK_RETURN_CODE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_CI_AC);
        CEP.TRC(SCCGWA, BPCKDGAC.DIG);
        CEP.TRC(SCCGWA, BPCPQPRD.AC_TYPE);
        WS_CI_AC_CN.WS_CI_AC_DIG = "" + BPCKDGAC.2DIG;
        JIBS_tmp_int = WS_CI_AC_CN.WS_CI_AC_DIG.length();
        for (int i=0;i<2-JIBS_tmp_int;i++) WS_CI_AC_CN.WS_CI_AC_DIG = "0" + WS_CI_AC_CN.WS_CI_AC_DIG;
        WS_CI_AC = IBS.CLS2CPY(SCCGWA, WS_CI_AC_CN);
        CEP.TRC(SCCGWA, WS_CI_AC);
    }
    public void B050_GET_SAVE_ACO_AC_CN() throws IOException,SQLException,Exception {
        WS_ACO_AC_CN.WS_ACO_AC_FLG = BPCCGAC.DATA.ACO_AC_FLG;
        WS_ACO_AC = IBS.CLS2CPY(SCCGWA, WS_ACO_AC_CN);
        WS_ACO_AC_CN.WS_ACO_AC_MMO = BPCCGAC.DATA.ACO_AC_MMO;
        WS_ACO_AC = IBS.CLS2CPY(SCCGWA, WS_ACO_AC_CN);
        WS_ACO_AC_CN.WS_ACO_AC_DEF = BPCCGAC.DATA.ACO_AC_DEF;
        WS_ACO_AC = IBS.CLS2CPY(SCCGWA, WS_ACO_AC_CN);
        B050_GET_ACO_AC_RANDOM();
        if (pgmRtn) return;
        B050_GET_ACO_AC_SEQ();
        if (pgmRtn) return;
        B050_GET_ACO_AC_DIG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_ACO_AC);
        BPCCGAC.DATA.ACO_AC = WS_ACO_AC;
        CEP.TRC(SCCGWA, "QQQ");
        CEP.TRC(SCCGWA, BPCCGAC.DATA.ACO_AC);
    }
    public void B050_GET_ACO_AC_RANDOM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.JRN_NO;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<12-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(11 - 1, 11 + 2 - 1).trim().length() == 0) WS_ACO_AC_CN.WS_ACO_AC_RAND = 0;
        else WS_ACO_AC_CN.WS_ACO_AC_RAND = Short.parseShort(JIBS_tmp_str[0].substring(11 - 1, 11 + 2 - 1));
        WS_ACO_AC = IBS.CLS2CPY(SCCGWA, WS_ACO_AC_CN);
        CEP.TRC(SCCGWA, WS_ACO_AC_CN.WS_ACO_AC_RAND);
    }
    public void B050_GET_ACO_AC_SEQ() throws IOException,SQLException,Exception {
        B033_CREATE_BASE_ACO_SEQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSGSEQ.SEQ);
        JIBS_tmp_str[0] = "" + BPCSGSEQ.SEQ;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(5 - 1, 5 + 11 - 1).trim().length() == 0) WS_ACO_AC_CN.WS_ACO_AC_SEQ = 0;
        else WS_ACO_AC_CN.WS_ACO_AC_SEQ = Long.parseLong(JIBS_tmp_str[0].substring(5 - 1, 5 + 11 - 1));
        WS_ACO_AC = IBS.CLS2CPY(SCCGWA, WS_ACO_AC_CN);
        CEP.TRC(SCCGWA, WS_ACO_AC_CN.WS_ACO_AC_SEQ);
    }
    public void B050_GET_ACO_AC_DIG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCKDGAC);
        WS_ACO_AC_SEG.WS_ACO_AC_FLG1 = WS_ACO_AC_CN.WS_ACO_AC_FLG;
        WS_ACO_AC_SEG.WS_ACO_AC_MMO1 = WS_ACO_AC_CN.WS_ACO_AC_MMO;
        WS_ACO_AC_SEG.WS_ACO_AC_DEF1 = WS_ACO_AC_CN.WS_ACO_AC_DEF;
        WS_ACO_AC_SEG.WS_ACO_AC_RAND1 = WS_ACO_AC_CN.WS_ACO_AC_RAND;
        WS_ACO_AC_SEG.WS_ACO_AC_SEQ1 = WS_ACO_AC_CN.WS_ACO_AC_SEQ;
        CEP.TRC(SCCGWA, WS_ACO_AC_SEG);
        BPCKDGAC.NO = IBS.CLS2CPY(SCCGWA, WS_ACO_AC_SEG);
        CEP.TRC(SCCGWA, BPCKDGAC.NO);
        BPCKDGAC.LEN = 19;
        BPCKDGAC.FUNC = 'G';
        BPCKDGAC.TYPE_FLG = '2';
        S000_CALL_BPZKDGAC();
        if (pgmRtn) return;
        S000_CHECK_RETURN_CODE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_ACO_AC);
        CEP.TRC(SCCGWA, BPCKDGAC.DIG);
        WS_ACO_AC_CN.WS_ACO_AC_DIG = BPCKDGAC.2DIG;
        WS_ACO_AC = IBS.CLS2CPY(SCCGWA, WS_ACO_AC_CN);
        CEP.TRC(SCCGWA, WS_ACO_AC);
    }
    public void B070_GET_SAVE_EQ_AC_CN() throws IOException,SQLException,Exception {
        WS_EQ_AC_CN.WS_EQ_AC_BR = BPCCGAC.DATA.BR;
        WS_EQ_AC = IBS.CLS2CPY(SCCGWA, WS_EQ_AC_CN);
        WS_EQ_AC_CN.WS_EQ_AC_CCY = BPCCGAC.DATA.EQ_CCY;
        WS_EQ_AC = IBS.CLS2CPY(SCCGWA, WS_EQ_AC_CN);
        WS_EQ_AC_CN.WS_EQ_AC_BIZ_TYPE = BPCCGAC.DATA.EQ_BIZ_TYPE;
        WS_EQ_AC = IBS.CLS2CPY(SCCGWA, WS_EQ_AC_CN);
        B070_GET_EQ_AC_SEQ();
        if (pgmRtn) return;
        B070_GET_EQ_AC_DIG();
        if (pgmRtn) return;
        BPCCGAC.DATA.EQ_AC = WS_EQ_AC;
    }
    public void B070_GET_EQ_AC_SEQ() throws IOException,SQLException,Exception {
        B071_CREATE_BASE_SEQ_EQ();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = "" + BPCSGSEQ.SEQ;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(10 - 1, 10 + 6 - 1).trim().length() == 0) WS_EQ_AC_CN.WS_EQ_AC_SEQ = 0;
        else WS_EQ_AC_CN.WS_EQ_AC_SEQ = Integer.parseInt(JIBS_tmp_str[0].substring(10 - 1, 10 + 6 - 1));
        WS_EQ_AC = IBS.CLS2CPY(SCCGWA, WS_EQ_AC_CN);
        CEP.TRC(SCCGWA, WS_EQ_AC_CN.WS_EQ_AC_SEQ);
    }
    public void B071_CREATE_BASE_SEQ_EQ() throws IOException,SQLException,Exception {
        WS_AC_NO_CN.WS_AC_SEG2.WS_AC_BASE_SEQ.WS_CI_BASE_SEQ = "" + 0;
        JIBS_tmp_int = WS_AC_NO_CN.WS_AC_SEG2.WS_AC_BASE_SEQ.WS_CI_BASE_SEQ.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) WS_AC_NO_CN.WS_AC_SEG2.WS_AC_BASE_SEQ.WS_CI_BASE_SEQ = "0" + WS_AC_NO_CN.WS_AC_SEG2.WS_AC_BASE_SEQ.WS_CI_BASE_SEQ;
        WS_AC_NO = IBS.CLS2CPY(SCCGWA, WS_AC_NO_CN);
        WS_FIRST_CREATE_FLG = 'Y';
        IBS.init(SCCGWA, BPCSGSEQ);
        BPCSGSEQ.TYPE = K_CI_BASE_SEQ;
        BPCSGSEQ.CODE = K_EQ_BASE_SEQ;
        CEP.TRC(SCCGWA, BPCSGSEQ.TYPE);
        CEP.TRC(SCCGWA, BPCSGSEQ.CODE);
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSGSEQ.RUN_MODE = 'O';
        S000_CALL_BPZSGSEQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSGSEQ.SEQ);
        CEP.TRC(SCCGWA, BPCSGSEQ.RC.RC_CODE);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B070_GET_EQ_AC_DIG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCKDGAC);
        WS_EQ_AC_SEG.WS_EQ_AC_BR1 = WS_EQ_AC_CN.WS_EQ_AC_BR;
        WS_EQ_AC_SEG.WS_EQ_AC_CCY1 = WS_EQ_AC_CN.WS_EQ_AC_CCY;
        WS_EQ_AC_SEG.WS_EQ_AC_BIZ_TYPE1 = WS_EQ_AC_CN.WS_EQ_AC_BIZ_TYPE;
        WS_EQ_AC_SEG.WS_EQ_AC_SEQ1 = WS_EQ_AC_CN.WS_EQ_AC_SEQ;
        CEP.TRC(SCCGWA, WS_EQ_AC_SEG);
        BPCKDGAC.NO = IBS.CLS2CPY(SCCGWA, WS_EQ_AC_SEG);
        CEP.TRC(SCCGWA, BPCKDGAC.NO);
        BPCKDGAC.LEN = 18;
        BPCKDGAC.FUNC = 'G';
        BPCKDGAC.TYPE_FLG = '2';
        S000_CALL_BPZKDGAC();
        if (pgmRtn) return;
        S000_CHECK_RETURN_CODE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_EQ_AC);
        CEP.TRC(SCCGWA, BPCKDGAC.DIG);
        CEP.TRC(SCCGWA, BPCPQPRD.AC_TYPE);
        WS_EQ_AC_CN.WS_EQ_AC_DIG = "" + BPCKDGAC.2DIG;
        JIBS_tmp_int = WS_EQ_AC_CN.WS_EQ_AC_DIG.length();
        for (int i=0;i<2-JIBS_tmp_int;i++) WS_EQ_AC_CN.WS_EQ_AC_DIG = "0" + WS_EQ_AC_CN.WS_EQ_AC_DIG;
        WS_EQ_AC = IBS.CLS2CPY(SCCGWA, WS_EQ_AC_CN);
        CEP.TRC(SCCGWA, WS_EQ_AC);
    }
    public void B070_READ_OBL_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRACOBL);
        BPRACOBL.KEY.AC_TYPE = WS_AC_NO_CN.WS_AC_SEG2.WS_AC_BASE_SEQ.WS_AC_TYPE;
        BPRACOBL.KEY.AC_NO_SEQ = WS_OBL_KEY;
        CEP.TRC(SCCGWA, BPRACOBL.KEY.AC_TYPE);
        CEP.TRC(SCCGWA, BPRACOBL.KEY.AC_NO_SEQ);
        BPTACOBL_RD = new DBParm();
        BPTACOBL_RD.TableName = "BPTACOBL";
        IBS.READ(SCCGWA, BPRACOBL, BPTACOBL_RD);
        CEP.TRC(SCCGWA, BPRACOBL.AC_NO);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_AC_OBL_FLG = 'Y';
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_AC_OBL_FLG = 'N';
        }
        WS_FIRST_CREATE_FLG = 'N';
    }
    public void B080_GET_SAVE_CU_CI() throws IOException,SQLException,Exception {
        REDEFINES64.WS_CU_CI_BASE.WS_CU_CI_FLG = 0;
        WS_CU_CI = IBS.CLS2CPY(SCCGWA, REDEFINES64);
        B080_GET_CU_CI_SEQ();
        if (pgmRtn) return;
        B080_GET_CU_CI_DIG();
        if (pgmRtn) return;
        BPCCGAC.DATA.CU_CI = WS_CU_CI;
    }
    public void B080_GET_CU_CI_SEQ() throws IOException,SQLException,Exception {
        B081_CREATE_CU_CI_SEQ();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = "" + BPCSGSEQ.SEQ;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(7 - 1, 7 + 9 - 1).trim().length() == 0) REDEFINES64.WS_CU_CI_BASE.WS_CU_CI_SEQ = 0;
        else REDEFINES64.WS_CU_CI_BASE.WS_CU_CI_SEQ = Integer.parseInt(JIBS_tmp_str[0].substring(7 - 1, 7 + 9 - 1));
        WS_CU_CI = IBS.CLS2CPY(SCCGWA, REDEFINES64);
        CEP.TRC(SCCGWA, REDEFINES64.WS_CU_CI_BASE.WS_CU_CI_SEQ);
    }
    public void B081_CREATE_CU_CI_SEQ() throws IOException,SQLException,Exception {
        WS_AC_NO_CN.WS_AC_SEG2.WS_AC_BASE_SEQ.WS_CI_BASE_SEQ = "" + 0;
        JIBS_tmp_int = WS_AC_NO_CN.WS_AC_SEG2.WS_AC_BASE_SEQ.WS_CI_BASE_SEQ.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) WS_AC_NO_CN.WS_AC_SEG2.WS_AC_BASE_SEQ.WS_CI_BASE_SEQ = "0" + WS_AC_NO_CN.WS_AC_SEG2.WS_AC_BASE_SEQ.WS_CI_BASE_SEQ;
        WS_AC_NO = IBS.CLS2CPY(SCCGWA, WS_AC_NO_CN);
        WS_FIRST_CREATE_FLG = 'Y';
        IBS.init(SCCGWA, BPCSGSEQ);
        CEP.TRC(SCCGWA, WS_CI_AC_CN.WS_CI_AC_SEQ);
        BPCSGSEQ.TYPE = K_CU_CI_SEQ_T;
        BPCSGSEQ.CODE = "SEQ";
        CEP.TRC(SCCGWA, BPCSGSEQ.TYPE);
        CEP.TRC(SCCGWA, BPCSGSEQ.CODE);
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSGSEQ.RUN_MODE = 'O';
        S000_CALL_BPZSGSEQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSGSEQ.SEQ);
        CEP.TRC(SCCGWA, BPCSGSEQ.RC.RC_CODE);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B080_GET_CU_CI_DIG() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES64.WS_CU_CI_BASE);
        if (JIBS_tmp_str[0].trim().length() == 0) WS_CU_CI1 = 0;
        else WS_CU_CI1 = Short.parseShort(JIBS_tmp_str[0]);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES64.WS_CU_CI_BASE);
        if (JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1).trim().length() == 0) WS_CU_CI2 = 0;
        else WS_CU_CI2 = Short.parseShort(JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1));
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES64.WS_CU_CI_BASE);
        if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_CU_CI3 = 0;
        else WS_CU_CI3 = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES64.WS_CU_CI_BASE);
        if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) WS_CU_CI4 = 0;
        else WS_CU_CI4 = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES64.WS_CU_CI_BASE);
        if (JIBS_tmp_str[0].substring(9 - 1, 9 + 2 - 1).trim().length() == 0) WS_CU_CI5 = 0;
        else WS_CU_CI5 = Short.parseShort(JIBS_tmp_str[0].substring(9 - 1, 9 + 2 - 1));
        WS_DGT = (short) (( WS_CU_CI1 * 3 + WS_CU_CI2 * 5 + WS_CU_CI3 * 7 + WS_CU_CI4 * 11 + WS_CU_CI5 * 13 ));
        WS_RMDR1 = (short) (WS_DGT % 10);
        WS_RET = (short) ((WS_DGT - WS_RMDR1) / 10);
        REDEFINES64.WS_CU_CI_DIG = WS_RMDR1;
        WS_CU_CI = IBS.CLS2CPY(SCCGWA, REDEFINES64);
    }
    public void B031_CREATE_BASE_SEQ_CN() throws IOException,SQLException,Exception {
        WS_AC_NO_CN.WS_AC_SEG2.WS_AC_BASE_SEQ.WS_CI_BASE_SEQ = "" + 0;
        JIBS_tmp_int = WS_AC_NO_CN.WS_AC_SEG2.WS_AC_BASE_SEQ.WS_CI_BASE_SEQ.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) WS_AC_NO_CN.WS_AC_SEG2.WS_AC_BASE_SEQ.WS_CI_BASE_SEQ = "0" + WS_AC_NO_CN.WS_AC_SEG2.WS_AC_BASE_SEQ.WS_CI_BASE_SEQ;
        WS_AC_NO = IBS.CLS2CPY(SCCGWA, WS_AC_NO_CN);
        WS_FIRST_CREATE_FLG = 'Y';
        IBS.init(SCCGWA, BPCSGSEQ);
        CEP.TRC(SCCGWA, WS_CI_AC_CN.WS_CI_AC_SEQ);
        BPCSGSEQ.TYPE = K_CI_BASE_SEQ;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_CI_AC_SEG);
        BPCSGSEQ.CODE = JIBS_tmp_str[0];
        CEP.TRC(SCCGWA, BPCSGSEQ.TYPE);
        CEP.TRC(SCCGWA, BPCSGSEQ.CODE);
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSGSEQ.RUN_MODE = 'O';
        S000_CALL_BPZSGSEQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSGSEQ.SEQ);
        CEP.TRC(SCCGWA, BPCSGSEQ.RC.RC_CODE);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
                IBS.init(SCCGWA, BPRSEQ);
                CEP.TRC(SCCGWA, "AAA");
                CEP.TRC(SCCGWA, WS_CI_AC_CN.WS_CI_AC_SEQ);
                BPRSEQ.KEY.TYPE = K_CI_BASE_SEQ;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_CI_AC_SEG);
                BPRSEQ.KEY.NAME = JIBS_tmp_str[0];
                BPCRMSEQ.FUNC = 'Q';
                BPCRMSEQ.PTR = BPRSEQ;
                BPCRMSEQ.LEN = 289;
                S000_CALL_BPZRMSEQ();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCRMSEQ.RC.RC_CODE);
                if (BPCRMSEQ.RC.RC_CODE != 0) {
                    WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRMSEQ.RC);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, "111");
                } else {
                    CEP.TRC(SCCGWA, "222");
                    B032_CREATE_BASE_SEQ_NEW();
                    if (pgmRtn) return;
                }
            } else {
                CEP.TRC(SCCGWA, "BBB");
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRMSEQ.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, BPCSGSEQ.SEQ);
    }
    public void B032_CREATE_BASE_SEQ_NEW() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_CI_AC_SEG);
        BPRSEQ.KEY.NAME = JIBS_tmp_str[0];
        BPRSEQ.LAST_UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRSEQ.LAST_UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRSEQ.FIRST_NUM = 1;
        CEP.TRC(SCCGWA, BPRSEQ.FIRST_NUM);
        CEP.TRC(SCCGWA, BPRSEQ.STEP_NUM);
        IBS.init(SCCGWA, BPCRMSEQ);
        BPCRMSEQ.FUNC = 'C';
        BPCRMSEQ.PTR = BPRSEQ;
        BPCRMSEQ.LEN = 289;
        S000_CALL_BPZRMSEQ();
        if (pgmRtn) return;
        if (BPCRMSEQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRMSEQ.RC);
            CEP.TRC(SCCGWA, "ABC");
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPCSGSEQ.SEQ = BPRSEQ.SEQ_NO;
        CEP.TRC(SCCGWA, BPRSEQ.SEQ_NO);
        CEP.TRC(SCCGWA, BPCSGSEQ.SEQ);
    }
    public void B033_CREATE_BASE_ACO_SEQ() throws IOException,SQLException,Exception {
        WS_AC_NO_CN.WS_AC_SEG2.WS_AC_BASE_SEQ.WS_CI_BASE_SEQ = "" + 0;
        JIBS_tmp_int = WS_AC_NO_CN.WS_AC_SEG2.WS_AC_BASE_SEQ.WS_CI_BASE_SEQ.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) WS_AC_NO_CN.WS_AC_SEG2.WS_AC_BASE_SEQ.WS_CI_BASE_SEQ = "0" + WS_AC_NO_CN.WS_AC_SEG2.WS_AC_BASE_SEQ.WS_CI_BASE_SEQ;
        WS_AC_NO = IBS.CLS2CPY(SCCGWA, WS_AC_NO_CN);
        WS_FIRST_CREATE_FLG = 'Y';
        IBS.init(SCCGWA, BPCSGSEQ);
        CEP.TRC(SCCGWA, WS_CI_AC_CN.WS_CI_AC_SEQ);
        BPCSGSEQ.TYPE = K_CI_BASE_SEQ;
        if (WS_ACO_AC == null) WS_ACO_AC = "";
        JIBS_tmp_int = WS_ACO_AC.length();
        for (int i=0;i<21-JIBS_tmp_int;i++) WS_ACO_AC += " ";
        BPCSGSEQ.CODE = WS_ACO_AC.substring(0, 8);
        CEP.TRC(SCCGWA, BPCSGSEQ.TYPE);
        CEP.TRC(SCCGWA, BPCSGSEQ.CODE);
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSGSEQ.RUN_MODE = 'O';
        S000_CALL_BPZSGSEQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSGSEQ.SEQ);
        CEP.TRC(SCCGWA, BPCSGSEQ.RC.RC_CODE);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
                IBS.init(SCCGWA, BPRSEQ);
                CEP.TRC(SCCGWA, "AAA");
                CEP.TRC(SCCGWA, WS_CI_AC_CN.WS_CI_AC_SEQ);
                BPRSEQ.KEY.TYPE = K_CI_BASE_SEQ;
                BPRSEQ.KEY.NAME = "" + WS_ACO_AC_CN.WS_ACO_AC_FLG;
                JIBS_tmp_int = BPRSEQ.KEY.NAME.length();
                for (int i=0;i<1-JIBS_tmp_int;i++) BPRSEQ.KEY.NAME = "0" + BPRSEQ.KEY.NAME;
                BPCRMSEQ.FUNC = 'Q';
                BPCRMSEQ.PTR = BPRSEQ;
                BPCRMSEQ.LEN = 289;
                S000_CALL_BPZRMSEQ();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCRMSEQ.RC.RC_CODE);
                if (BPCRMSEQ.RC.RC_CODE != 0) {
                    WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRMSEQ.RC);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, "111");
                } else {
                    CEP.TRC(SCCGWA, "222");
                    B034_CREATE_BASE_ACO_SEQ_NEW();
                    if (pgmRtn) return;
                }
            } else {
                CEP.TRC(SCCGWA, "BBB");
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRMSEQ.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, BPCSGSEQ.SEQ);
    }
    public void B034_CREATE_BASE_ACO_SEQ_NEW() throws IOException,SQLException,Exception {
        if (WS_ACO_AC == null) WS_ACO_AC = "";
        JIBS_tmp_int = WS_ACO_AC.length();
        for (int i=0;i<21-JIBS_tmp_int;i++) WS_ACO_AC += " ";
        BPRSEQ.KEY.NAME = WS_ACO_AC.substring(0, 8);
        BPRSEQ.LAST_UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRSEQ.LAST_UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRSEQ.FIRST_NUM = 1;
        CEP.TRC(SCCGWA, BPRSEQ.FIRST_NUM);
        CEP.TRC(SCCGWA, BPRSEQ.STEP_NUM);
        IBS.init(SCCGWA, BPCRMSEQ);
        BPCRMSEQ.FUNC = 'C';
        BPCRMSEQ.PTR = BPRSEQ;
        BPCRMSEQ.LEN = 289;
        S000_CALL_BPZRMSEQ();
        if (pgmRtn) return;
        BPCCGAC.RC.RC_CODE = BPCRMSEQ.RC.RC_CODE;
        CEP.TRC(SCCGWA, "XXXX");
        if (BPCRMSEQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRMSEQ.RC);
            CEP.TRC(SCCGWA, "ABC");
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPCSGSEQ.SEQ = BPRSEQ.SEQ_NO;
        CEP.TRC(SCCGWA, BPRSEQ.SEQ_NO);
        CEP.TRC(SCCGWA, BPCSGSEQ.SEQ);
    }
    public void B050_CREATE_SUB_SEQ_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_AC_NO_CN.WS_AC_SEG2.WS_AC_BASE_SEQ);
        CEP.TRC(SCCGWA, WS_FIRST_CREATE_FLG);
        if (WS_FIRST_CREATE_FLG == 'Y') {
            B090_CREATE_NEW_SUB_SEQ();
            if (pgmRtn) return;
        } else {
            B093_QUERY_SUB_SEQ();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCCGAC.RC);
            CEP.TRC(SCCGWA, BPCSGSEQ.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCCGAC.RC);
            JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
            JIBS_tmp_str[3] = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
            if ((!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL) 
                && !JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_SEQ_OVER_WARN)) 
                || JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND) 
                || JIBS_tmp_str[3].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_SEQ_OVER_MAX)) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCCGAC.RC);
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
                if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
                    CEP.TRC(SCCGWA, "RECORD NOT FND!!");
                    B090_CREATE_NEW_SUB_SEQ();
                    if (pgmRtn) return;
                } else {
                    CEP.TRC(SCCGWA, "RECORD-ERR!!");
                    B031_CREATE_BASE_SEQ_CN();
                    if (pgmRtn) return;
                    B090_CREATE_NEW_SUB_SEQ();
                    if (pgmRtn) return;
                }
            }
        }
        CEP.TRC(SCCGWA, WS_TYPE_DISTART.WS_SEQ_MAX);
        CEP.TRC(SCCGWA, BPCSGSEQ.SEQ);
        if (BPCSGSEQ.SEQ > WS_TYPE_DISTART.WS_SEQ_MAX) {
            CEP.TRC(SCCGWA, "OVER MAX SHOULD CUT THE BASE !");
            WS_OVER_SUB_SEQ_FLG = 'Y';
        } else {
            WS_OVER_SUB_SEQ_FLG = 'N';
        }
        CEP.TRC(SCCGWA, "END OF B050-CREATE-SUB-SEQ-CN");
    }
    public void B090_CREATE_NEW_SUB_SEQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUASEQ);
        BPCUASEQ.SEQ_TYPE = WS_TYPE_DISTART.WS_SEQ_TYPE;
        CEP.TRC(SCCGWA, BPCUASEQ.SEQ_TYPE);
        BPCUASEQ.SEQ_CODE = IBS.CLS2CPY(SCCGWA, WS_AC_NO_CN.WS_AC_SEG2.WS_AC_BASE_SEQ);
        CEP.TRC(SCCGWA, BPCUASEQ.SEQ_CODE);
        R000_TRANS_DATA_CN();
        if (pgmRtn) return;
        S000_CALL_BPZUASEQ();
        if (pgmRtn) return;
        S000_CHECK_RETURN_CODE();
        if (pgmRtn) return;
        B093_QUERY_SUB_SEQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_AC_NO_CN.WS_AC_SEG2.WS_SUB_SEQ);
        CEP.TRC(SCCGWA, BPCSGSEQ.SEQ);
    }
    public void B093_QUERY_SUB_SEQ() throws IOException,SQLException,Exception {
    }
    public void B020_GET_PRD_NO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCCGAC.DATA.PRD_CODE);
        BPCPQPRD.PRDT_CODE = BPCCGAC.DATA.PRD_CODE;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(3 - 1, 3 + 4 - 1).trim().length() == 0) REDEFINES46.WS_AC_SEG1.WS_BR = 0;
        else REDEFINES46.WS_AC_SEG1.WS_BR = Short.parseShort(JIBS_tmp_str[0].substring(3 - 1, 3 + 4 - 1));
        WS_AC_NO = IBS.CLS2CPY(SCCGWA, REDEFINES46);
        REDEFINES46.WS_AC_SEG1.WS_PROD_CODE = BPCPQPRD.AC_TYPE;
        WS_AC_NO = IBS.CLS2CPY(SCCGWA, REDEFINES46);
    }
    public void B030_GET_SEQ_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSGSEQ);
        CEP.TRC(SCCGWA, BPCCGAC.DATA.SEQ_NO);
        if (BPCCGAC.DATA.SEQ_NO == 0) {
            BPCSGSEQ.TYPE = "ACNO";
            BPCSGSEQ.CODE = BPCPQPRD.AC_TYPE;
            BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCSGSEQ.RUN_MODE = 'O';
            S000_CALL_BPZSGSEQ();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCSGSEQ.SEQ);
            S000_CHECK_RETURN_CODE();
            if (pgmRtn) return;
            JIBS_tmp_str[0] = "" + BPCSGSEQ.SEQ;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (JIBS_tmp_str[0].substring(10 - 1, 10 + 6 - 1).trim().length() == 0) REDEFINES46.WS_AC_SEG1.WS_AC_SEQ = 0;
            else REDEFINES46.WS_AC_SEG1.WS_AC_SEQ = Integer.parseInt(JIBS_tmp_str[0].substring(10 - 1, 10 + 6 - 1));
            WS_AC_NO = IBS.CLS2CPY(SCCGWA, REDEFINES46);
            CEP.TRC(SCCGWA, BPCSGSEQ.SEQ);
            REDEFINES46.WS_AC_DIG = 0;
            WS_AC_NO = IBS.CLS2CPY(SCCGWA, REDEFINES46);
        } else {
            BPCSGOBL.TYPE = "ACNO";
            BPCSGOBL.CODE = BPCPQPRD.AC_TYPE;
            BPCSGOBL.MIN_NUM = BPCCGAC.DATA.SEQ_NO;
            BPCSGOBL.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
            S000_CALL_BPZSGOBL();
            if (pgmRtn) return;
            S000_CHECK_RETURN_CODE();
            if (pgmRtn) return;
            REDEFINES46.WS_AC_SEG1.WS_AC_SEQ = (int) BPCSGOBL.SEQ;
            WS_AC_NO = IBS.CLS2CPY(SCCGWA, REDEFINES46);
            REDEFINES46.WS_AC_DIG = 0;
            WS_AC_NO = IBS.CLS2CPY(SCCGWA, REDEFINES46);
        }
        BPCCGAC.DATA.GET_SEQ_NO = REDEFINES46.WS_AC_SEG1.WS_AC_SEQ;
    }
    public void B040_GET_VERIFY_CODE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCKDGAC);
        CEP.TRC(SCCGWA, REDEFINES46.WS_AC_SEG1);
        BPCKDGAC.NO = IBS.CLS2CPY(SCCGWA, REDEFINES46.WS_AC_SEG1);
        BPCKDGAC.LEN = 32;
        BPCKDGAC.FUNC = 'G';
        S000_CALL_BPZKDGAC();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCCGAC.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(SCCCTLM_MSG.SC_ERR_GEN_DIG) 
            && WS_CNT < 10) {
            WS_CNT += 1;
            B030_GET_SEQ_NO();
            if (pgmRtn) return;
            B040_GET_VERIFY_CODE();
            if (pgmRtn) return;
        }
        S000_CHECK_RETURN_CODE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_AC_NO);
        CEP.TRC(SCCGWA, BPCKDGAC.DIG);
        CEP.TRC(SCCGWA, BPCPQPRD.AC_TYPE);
        REDEFINES46.WS_AC_DIG = BPCKDGAC.DIG;
        WS_AC_NO = IBS.CLS2CPY(SCCGWA, REDEFINES46);
        CEP.TRC(SCCGWA, WS_AC_NO);
    }
    public void R000_TRANS_DATA() throws IOException,SQLException,Exception {
        BPCUASEQ.SEQ_NO = 0;
        BPCUASEQ.OLD_SEQ_NO = 0;
        BPCUASEQ.SEQ_DESC = IBS.CLS2CPY(SCCGWA, WS_AC_NO_CN.WS_AC_SEG2.WS_AC_BASE_SEQ);
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
    }
    public void R000_TRANS_DATA_CN() throws IOException,SQLException,Exception {
        BPCUASEQ.SEQ_NO = 0;
        BPCUASEQ.OLD_SEQ_NO = 0;
        BPCUASEQ.SEQ_DESC = IBS.CLS2CPY(SCCGWA, WS_AC_NO_CN.WS_AC_SEG2.WS_AC_BASE_SEQ);
        BPCUASEQ.FIRST_NUM = 1;
        BPCUASEQ.INIT_ZERO = 'N';
        BPCUASEQ.SKIP_FLG = 'N';
        BPCUASEQ.OBL_FLG = 'N';
        BPCUASEQ.VIP_FLG = 'N';
        BPCUASEQ.MAX_SEQ_NO = WS_TYPE_DISTART.WS_SEQ_MAX + 1;
        BPCUASEQ.MAX_FLG = 'E';
        BPCUASEQ.WARN_SEQ_NO = WS_TYPE_DISTART.WS_SEQ_MAX - 10;
        BPCUASEQ.STEP_NUM = 1;
        BPCUASEQ.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCUASEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCUASEQ.BR = (short) SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCUASEQ.DP = SCCGWA.COMM_AREA.BR_DP.TR_DEP;
        BPCUASEQ.TLR = SCCGWA.COMM_AREA.TL_ID;
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CMP_CALL_BPZPQPRD, BPCPQPRD);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCCGAC.RC);
    }
    public void S000_CALL_BPZSMPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_PRDT_INF_MAINT, BPCSMPRD);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CMP_CALL_BPZSGSEQ, BPCSGSEQ);
        CEP.TRC(SCCGWA, BPCSGSEQ.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCCGAC.RC);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CMP_CALL_CIZCUST, CICCUST);
        CEP.TRC(SCCGWA, CICCUST.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCCGAC.RC);
    }
    public void S000_CALL_BPZSGOBL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CMP_CALL_BPZSGOBL, BPCSGOBL);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSGOBL.RC);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCCGAC.RC);
    }
    public void S000_CALL_BPZKDGAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CMP_CALL_BPZKDGAC, BPCKDGAC);
        CEP.TRC(SCCGWA, BPCKDGAC.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCKDGAC.RC);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCCGAC.RC);
    }
    public void S000_CALL_BPZUQSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-INQ-SEQ", BPCUQSEQ);
        if (BPCUQSEQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUQSEQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCCGAC.RC);
        }
    }
    public void S000_CALL_BPZUASEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CMP_CALL_BPZUASEQ, BPCUASEQ);
        CEP.TRC(SCCGWA, BPCUASEQ.RC);
        if (BPCUASEQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUASEQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCCGAC.RC);
        }
    }
    public void S000_CALL_BPZRMSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MAINT-SEQ", BPCRMSEQ);
        CEP.TRC(SCCGWA, "NORMTL OR NOT");
        CEP.TRC(SCCGWA, BPCRMSEQ.RC);
        if (BPCRMSEQ.RETURN_INFO == 'D') {
            CEP.TRC(SCCGWA, "ZHENGJIE");
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_EXIST, BPCCGAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CHECK_RETURN_CODE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCCGAC.RC);
        if (BPCCGAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCCGAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        if (BPCCGAC.RC.RC_CODE == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCCGAC.RC);
        }
        CEP.TRC(SCCGWA, BPCCGAC.RC);
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCCGAC.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCCGAC = ");
            CEP.TRC(SCCGWA, BPCCGAC);
            CEP.TRC(SCCGWA, " BPZGACNO : CI-NO = ");
            CEP.TRC(SCCGWA, BPCCGAC.DATA.CI_NO);
            CEP.TRC(SCCGWA, " WS-FIRST-CREATE-FLG = ");
            CEP.TRC(SCCGWA, WS_FIRST_CREATE_FLG);
            CEP.TRC(SCCGWA, " CGAC-AC-KIND = ");
            CEP.TRC(SCCGWA, BPCCGAC.DATA.AC_KIND);
            CEP.TRC(SCCGWA, " CGAC-RC = ");
            CEP.TRC(SCCGWA, BPCCGAC.RC);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
