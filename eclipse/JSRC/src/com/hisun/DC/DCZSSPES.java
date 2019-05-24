package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;
import com.hisun.AI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSSPES {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String WS_SMSG_MSG_TEXT = " ";
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    String PGM_SCSSCKDT = "SCSSCKDT";
    String CPN_BP_P_INQ_PC = "BP-P-INQ-PC   ";
    String CPN_PBSOCCAC = "PB-PBSOCCAC";
    int K_HEAD_ITM_NO = 999999;
    int K_BR_ITM_NO = 99999999;
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    int WS_ITM_LEN = 0;
    int WS_A_ITM_LEN = 0;
    int WS_OVER_LEN = 0;
    String WS_COA_NO = " ";
    String WS_MIN_ITM_NO = " ";
    String WS_MAX_ITM_NO = " ";
    short WS_CNT = 0;
    short WS_IDX = 0;
    short WS_FLD_NO = 0;
    DCZSSPES_WS_T_CTL WS_T_CTL = new DCZSSPES_WS_T_CTL();
    DCZSSPES_WS_ITM_46_NO WS_ITM_46_NO = new DCZSSPES_WS_ITM_46_NO();
    DCZSSPES_WS_ITM_64_NO WS_ITM_64_NO = new DCZSSPES_WS_ITM_64_NO();
    DCZSSPES_WS_ITM_424_NO WS_ITM_424_NO = new DCZSSPES_WS_ITM_424_NO();
    char WS_ITM_FST_NO = ' ';
    char WS_REC_FLG = ' ';
    char WS_END_FLG = ' ';
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPCQBKPM BPCQBKPM = new BPCQBKPM();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    AICPQBAL AICPQBAL = new AICPQBAL();
    BPCPQGLM BPCPQGLM = new BPCPQGLM();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    SCCGCPT SCCGCPT;
    SCCGMSG SCCGMSG;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPRTRT BPRTRTT;
    BPRTLT BPRTLT;
    AIB0005_AWA_0005 AIB0005_AWA_0005;
    SCCAWAC SCCAWAC;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        B00_CHECK_PROC();
        CEP.TRC(SCCGWA, "DCZSSPES return!");
        Z_RET();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGCPT = (SCCGCPT) SCCGSCA_SC_AREA.CMPT_AREA_PTR;
        SCCGMSG = (SCCGMSG) SCCGSCA_SC_AREA.MSG_AREA_PTR;
        BPRTRTT = (BPRTRT) SCCGSCA_SC_AREA.TR_PARM_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        BPRTLT = (BPRTLT) SCCGWA.COMM_AREA.TLT_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB0005_AWA_0005>");
        AIB0005_AWA_0005 = (AIB0005_AWA_0005) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        SCCAWAC = new SCCAWAC();
        IBS.init(SCCGWA, SCCAWAC);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.AWAC_AREA_PTR, SCCAWAC);
        IBS.init(SCCGWA, SCCMSG);
        IBS.CPY2CLS(SCCGWA, BPRTRTT.DATA_TXT.PGM[SCCGWA.COMM_AREA.PGM_NO-1].PGM_CTL, WS_T_CTL);
    }
    public void B00_CHECK_PROC() throws IOException,SQLException,Exception {
        if (AIB0005_AWA_0005.COA_FLG.trim().length() == 0 
            && AIB0005_AWA_0005.COA_NO.trim().length() == 0 
            && AIB0005_AWA_0005.ENAME.trim().length() == 0 
            && AIB0005_AWA_0005.CNAME.trim().length() == 0 
            && AIB0005_AWA_0005.COA_TYP.trim().length() == 0 
            && AIB0005_AWA_0005.LKUP_CD.trim().length() == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.MUST_INPUT;
            WS_FLD_NO = AIB0005_AWA_0005.LKUP_CD_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        CEP.TRC(SCCGWA, AIB0005_AWA_0005.COA_TYP);
        if (AIB0005_AWA_0005.COA_FLG.trim().length() == 0 
                && AIB0005_AWA_0005.COA_FLG.charAt(0) == 0X00) {
            AIB0005_AWA_0005.COA_FLG = " ";
        } else if (AIB0005_AWA_0005.COA_NO.equalsIgnoreCase("0") 
                && AIB0005_AWA_0005.COA_NO.charAt(0) == 0X00) {
            AIB0005_AWA_0005.COA_NO = " ";
        } else if (AIB0005_AWA_0005.ENAME.trim().length() == 0 
                && AIB0005_AWA_0005.ENAME.charAt(0) == 0X00) {
            AIB0005_AWA_0005.ENAME = " ";
        } else if (AIB0005_AWA_0005.CNAME.trim().length() == 0 
                && AIB0005_AWA_0005.CNAME.charAt(0) == 0X00) {
            AIB0005_AWA_0005.CNAME = " ";
        } else if (AIB0005_AWA_0005.COA_TYP.trim().length() == 0 
                && AIB0005_AWA_0005.COA_TYP.charAt(0) == 0X00) {
            AIB0005_AWA_0005.COA_TYP = " ";
        } else if (AIB0005_AWA_0005.LKUP_CD.trim().length() == 0 
                && AIB0005_AWA_0005.LKUP_CD.charAt(0) == 0X00) {
            AIB0005_AWA_0005.LKUP_CD = " ";
        } else {
        }
        CEP.TRC(SCCGWA, AIB0005_AWA_0005.COA_FLG);
        if (AIB0005_AWA_0005.COA_FLG.trim().length() > 0) {
            IBS.init(SCCGWA, BPCQBKPM);
            BPCQBKPM.FUNC = 'B';
            BPCQBKPM.COA_TYP = AIB0005_AWA_0005.COA_FLG;
            S000_CALL_BPZQBKPM();
            WS_ITM_LEN = BPCQBKPM.DATA[1-1].ITM_LEN;
        }
        CEP.TRC(SCCGWA, WS_ITM_LEN);
        if (WS_MAX_ITM_NO == null) WS_MAX_ITM_NO = "";
        JIBS_tmp_int = WS_MAX_ITM_NO.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) WS_MAX_ITM_NO += " ";
        WS_MAX_ITM_NO = "9999999999" + WS_MAX_ITM_NO.substring(10);
        CEP.TRC(SCCGWA, WS_MAX_ITM_NO);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_ID.TR_CODE);
        if (SCCGWA.COMM_AREA.TR_ID.TR_CODE == 5100) {
            if (AIB0005_AWA_0005.COA_NO.compareTo(WS_MAX_ITM_NO) >= 0) {
                if (AIB0005_AWA_0005.COA_FLG.equalsIgnoreCase("1")) {
                    WS_ERR_INFO = " ";
                    WS_ERR_INFO = "T" + ",LENGTH OF BRANCH GL A/C NO." + " >= 99999999";
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_COA_NO_LEN_ERR;
                    WS_FLD_NO = AIB0005_AWA_0005.COA_NO_NO;
                    S000_ERR_MSG_PROC();
                }
                if (AIB0005_AWA_0005.COA_FLG.equalsIgnoreCase("2")) {
                    WS_ERR_INFO = " ";
                    WS_ERR_INFO = "T" + ",LENGTH OF HEAD OFFICE " + "GL A/C NO. IS SIX";
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_COA_NO_LEN_ERR;
                    WS_FLD_NO = AIB0005_AWA_0005.COA_NO_NO;
                    S000_ERR_MSG_PROC();
                }
            }
        }
        if (SCCGWA.COMM_AREA.TR_ID.TR_CODE == 5101 
            || SCCGWA.COMM_AREA.TR_ID.TR_CODE == 5102) {
            if (AIB0005_AWA_0005.SEGMENT_NO == 0) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.MUST_INPUT;
                WS_FLD_NO = AIB0005_AWA_0005.SEGMENT_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            CEP.TRC(SCCGWA, AIB0005_AWA_0005.POSTTYP);
            if (AIB0005_AWA_0005.ITM_LVL == '1' 
                || AIB0005_AWA_0005.ITM_LVL == '2') {
                if (AIB0005_AWA_0005.LOW_IND != 'N') {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_9000;
                    WS_FLD_NO = AIB0005_AWA_0005.LOW_IND_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
                if (AIB0005_AWA_0005.MIB_FLG != 'N') {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_9001;
                    WS_FLD_NO = AIB0005_AWA_0005.LOW_IND_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            } else {
                if (AIB0005_AWA_0005.LOW_IND != 'Y') {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_9000;
                    WS_FLD_NO = AIB0005_AWA_0005.LOW_IND_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            }
            if (AIB0005_AWA_0005.COA_NO == null) AIB0005_AWA_0005.COA_NO = "";
            JIBS_tmp_int = AIB0005_AWA_0005.COA_NO.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) AIB0005_AWA_0005.COA_NO += " ";
            WS_ITM_FST_NO = AIB0005_AWA_0005.COA_NO.substring(0, 1).charAt(0);
            CEP.TRC(SCCGWA, AIB0005_AWA_0005.ITM_LVL);
            if (AIB0005_AWA_0005.ITM_LVL == '1') {
                CEP.TRC(SCCGWA, WS_ITM_FST_NO);
                CEP.TRC(SCCGWA, WS_ITM_46_NO.WS_LST6_NO);
                IBS.CPY2CLS(SCCGWA, AIB0005_AWA_0005.COA_NO, WS_ITM_46_NO);
                if (AIB0005_AWA_0005.UP_ITM.trim().length() > 0) {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_9004;
                    WS_FLD_NO = AIB0005_AWA_0005.UP_ITM_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
                if (WS_ITM_FST_NO == ' ' 
                    || WS_ITM_46_NO.WS_LST6_NO.trim().length() > 0) {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_9003;
                    WS_FLD_NO = AIB0005_AWA_0005.COA_NO_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            } else if (AIB0005_AWA_0005.ITM_LVL == '2') {
                IBS.CPY2CLS(SCCGWA, AIB0005_AWA_0005.COA_NO, WS_ITM_46_NO);
                IBS.CPY2CLS(SCCGWA, AIB0005_AWA_0005.COA_NO, WS_ITM_64_NO);
                if (WS_ITM_64_NO.WS_LST4_NO.trim().length() > 0 
                    || WS_ITM_46_NO.WS_LST6_NO.trim().length() == 0 
                    || WS_ITM_FST_NO == ' ') {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_9003;
                    WS_FLD_NO = AIB0005_AWA_0005.COA_NO_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
                IBS.CPY2CLS(SCCGWA, AIB0005_AWA_0005.UP_ITM, WS_ITM_46_NO);
                if (AIB0005_AWA_0005.COA_NO == null) AIB0005_AWA_0005.COA_NO = "";
                JIBS_tmp_int = AIB0005_AWA_0005.COA_NO.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) AIB0005_AWA_0005.COA_NO += " ";
                WS_ITM_64_NO.WS_LST4_NO = AIB0005_AWA_0005.COA_NO.substring(0, 4);
                if (!WS_ITM_46_NO.WS_PER4_NO.equalsIgnoreCase(WS_ITM_64_NO.WS_LST4_NO) 
                    || WS_ITM_46_NO.WS_LST6_NO.trim().length() > 0) {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_9004;
                    WS_FLD_NO = AIB0005_AWA_0005.UP_ITM_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            } else if (AIB0005_AWA_0005.ITM_LVL == '3') {
                IBS.CPY2CLS(SCCGWA, AIB0005_AWA_0005.COA_NO, WS_ITM_424_NO);
                if (WS_ITM_424_NO.WS_TMP4_NO.trim().length() == 0 
                    || WS_ITM_424_NO.WS_MID2_NO.trim().length() == 0 
                    || WS_ITM_FST_NO == ' ') {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_9003;
                    WS_FLD_NO = AIB0005_AWA_0005.COA_NO_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
                IBS.CPY2CLS(SCCGWA, AIB0005_AWA_0005.UP_ITM, WS_ITM_64_NO);
                if (AIB0005_AWA_0005.COA_NO == null) AIB0005_AWA_0005.COA_NO = "";
                JIBS_tmp_int = AIB0005_AWA_0005.COA_NO.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) AIB0005_AWA_0005.COA_NO += " ";
                WS_ITM_46_NO.WS_LST6_NO = AIB0005_AWA_0005.COA_NO.substring(0, 6);
                if (WS_ITM_64_NO.WS_LST4_NO.trim().length() > 0 
                    || !WS_ITM_64_NO.WS_PER6_NO.equalsIgnoreCase(WS_ITM_46_NO.WS_LST6_NO)) {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_9004;
                    WS_FLD_NO = AIB0005_AWA_0005.UP_ITM_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            } else {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_9002;
                WS_FLD_NO = AIB0005_AWA_0005.ITM_LVL_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        WS_A_ITM_LEN = 2;
        CEP.TRC(SCCGWA, WS_A_ITM_LEN);
        CEP.TRC(SCCGWA, WS_ITM_LEN);
        WS_OVER_LEN = WS_A_ITM_LEN - WS_ITM_LEN + 1;
        CEP.TRC(SCCGWA, WS_OVER_LEN);
        CEP.TRC(SCCGWA, AIB0005_AWA_0005.COA_NO);
        WS_COA_NO = AIB0005_AWA_0005.COA_NO;
        for (WS_CNT = WS_OVER_LEN; WS_CNT <= WS_A_ITM_LEN 
            && WS_CNT <= WS_END_FLG; WS_CNT += 1) {
            if (WS_COA_NO == null) WS_COA_NO = "";
            JIBS_tmp_int = WS_COA_NO.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) WS_COA_NO += " ";
            if (WS_COA_NO.substring(WS_CNT - 1, WS_CNT + 1 - 1).equalsIgnoreCase("0")) {
                WS_IDX += 1;
            }
        }
        CEP.TRC(SCCGWA, AIB0005_AWA_0005.RESAMT_NO);
        if (AIB0005_AWA_0005.RESAMT_NO != 0) {
            B07_CHECK_RESAMT();
        }
        CEP.TRC(SCCGWA, AIB0005_AWA_0005.COA_TYP_NO);
        CEP.TRC(SCCGWA, AIB0005_AWA_0005.LOW_IND_NO);
        CEP.TRC(SCCGWA, AIB0005_AWA_0005.EFF_DAT);
        CEP.TRC(SCCGWA, AIB0005_AWA_0005.EXP_DAT);
        CEP.TRC(SCCGWA, AIB0005_AWA_0005.DEL_DATE_NO);
        if (AIB0005_AWA_0005.DEL_DATE_NO != 0) {
            B13_CHECK_DELDAT();
        }
        CEP.TRC(SCCGWA, AIB0005_AWA_0005.EFF_DAT);
        if (AIB0005_AWA_0005.EFF_DAT == 0) {
            AIB0005_AWA_0005.EFF_DAT = SCCGWA.COMM_AREA.AC_DATE;
        }
        CEP.TRC(SCCGWA, AIB0005_AWA_0005.EXP_DAT);
        if (AIB0005_AWA_0005.EXP_DAT == 0) {
            AIB0005_AWA_0005.EXP_DAT = 99991231;
        }
        CEP.TRC(SCCGWA, AIB0005_AWA_0005.EFF_DAT_NO);
        CEP.TRC(SCCGWA, AIB0005_AWA_0005.EXP_DAT_NO);
        CEP.TRC(SCCGWA, AIB0005_AWA_0005.HOL_DT_NO);
        if (AIB0005_AWA_0005.HOL_DT_NO != 0) {
            CEP.TRC(SCCGWA, AIB0005_AWA_0005.HOL_DT);
            B18_CHECK_HODDAT();
        }
        if (AIB0005_AWA_0005.MST_COA_NO != 0) {
            B19_CHECK_MSTCOA();
        }
    }
    public void B07_CHECK_RESAMT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIB0005_AWA_0005.RESAMT);
        if (AIB0005_AWA_0005.RESAMT == ' ') {
            AIB0005_AWA_0005.RESAMT = 'N';
        }
    }
    public void B09_CHECK_COATYP() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIB0005_AWA_0005.POSTTYP);
        if (AIB0005_AWA_0005.POSTTYP == 'M') {
            CEP.TRC(SCCGWA, AIB0005_AWA_0005.COA_TYP);
            if (!AIB0005_AWA_0005.COA_TYP.equalsIgnoreCase("O")) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_COA_TYP_ERR;
                WS_FLD_NO = AIB0005_AWA_0005.COA_TYP_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        } else {
            if (AIB0005_AWA_0005.POSTTYP != 0X00) {
                CEP.TRC(SCCGWA, AIB0005_AWA_0005.COA_TYP);
                if (AIB0005_AWA_0005.COA_TYP.equalsIgnoreCase("O")) {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_COA_TYP_ERR;
                    WS_FLD_NO = AIB0005_AWA_0005.COA_TYP_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            }
        }
    }
    public void B11_CHECK_LOWIND() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIB0005_AWA_0005.AUTO_GEN);
        CEP.TRC(SCCGWA, AIB0005_AWA_0005.LOW_IND);
        if (AIB0005_AWA_0005.AUTO_GEN != 'N') {
            if (AIB0005_AWA_0005.LOW_IND == 'N') {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_LOW_IND_ERR;
                WS_FLD_NO = AIB0005_AWA_0005.LOW_IND_NO;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B13_CHECK_DELDAT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIB0005_AWA_0005.DEL_DATE);
        if (AIB0005_AWA_0005.DEL_DATE != 0) {
        }
    }
    public void B15_CHECK_EFFDAT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = AIB0005_AWA_0005.EFF_DAT;
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.INPUT_DATE_ERROR;
            WS_FLD_NO = AIB0005_AWA_0005.EFF_DAT_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B17_CHECK_EXPDAT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = AIB0005_AWA_0005.EXP_DAT;
        SCSSCKDT SCSSCKDT2 = new SCSSCKDT();
        SCSSCKDT2.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.INPUT_DATE_ERROR;
            WS_FLD_NO = AIB0005_AWA_0005.EXP_DAT_NO;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (AIB0005_AWA_0005.EXP_DAT <= SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_EXP_MUST_GT_ACDT;
            WS_FLD_NO = AIB0005_AWA_0005.EXP_DAT_NO;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, AIB0005_AWA_0005.EFF_DAT);
        CEP.TRC(SCCGWA, AIB0005_AWA_0005.EXP_DAT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (AIB0005_AWA_0005.EXP_DAT <= AIB0005_AWA_0005.EFF_DAT) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_EXPDT_MUST_GT_EFFDT;
            WS_FLD_NO = AIB0005_AWA_0005.EXP_DAT_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B18_CHECK_HODDAT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = AIB0005_AWA_0005.HOL_DT;
        SCSSCKDT SCSSCKDT3 = new SCSSCKDT();
        SCSSCKDT3.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.INPUT_DATE_ERROR;
            WS_FLD_NO = AIB0005_AWA_0005.HOL_DT_NO;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (AIB0005_AWA_0005.HOL_DT < SCCGWA.COMM_AREA.AC_DATE) {
            CEP.TRC(SCCGWA, "TEST AI8304");
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_HOLD_DAT_GT_AC_DATE;
            WS_FLD_NO = AIB0005_AWA_0005.HOL_DT_NO;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, AIB0005_AWA_0005.EFF_DAT);
        CEP.TRC(SCCGWA, AIB0005_AWA_0005.HOL_DT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (AIB0005_AWA_0005.HOL_DT < AIB0005_AWA_0005.EFF_DAT) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_HOLDDT_MUST_GT_EFFDT;
            WS_FLD_NO = AIB0005_AWA_0005.HOL_DT_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B19_CHECK_MSTCOA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIB0005_AWA_0005.MST_COA);
        if (AIB0005_AWA_0005.MST_COA != 0 
            && AIB0005_AWA_0005.MST_COA != 0X00) {
            IBS.init(SCCGWA, BPCPQGLM);
            BPCPQGLM.DAT.INPUT.MSTNO = AIB0005_AWA_0005.MST_COA;
            S000_CALL_BPZPQGLM();
        }
    }
    public void S000_CALL_BPZPQGLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-GLMT", BPCPQGLM);
        CEP.TRC(SCCGWA, BPCPQGLM.RC);
        if (BPCPQGLM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQGLM.RC);
            WS_FLD_NO = AIB0005_AWA_0005.MST_COA_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZQBKPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-Q-MAINT-AMBKP", BPCQBKPM);
        CEP.TRC(SCCGWA, BPCQBKPM.RC);
        if (BPCQBKPM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQBKPM.RC);
            WS_FLD_NO = AIB0005_AWA_0005.COA_FLG_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INFO, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_ERR_INFO, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S00_CALL_AIZPQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-BRW-COA-BAL", AICPQBAL);
        if (AICPQBAL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQBAL.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
