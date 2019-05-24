package com.hisun.AI;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class AIOT5101 {
    int JIBS_tmp_int;
    DBParm AITITM_RD;
    char WK_FUNC = ' ';
    String PGM_SCSSCKDT = "SCSSCKDT";
    String AI_HIS_REMARKS = "GL A/C NO. MAINTAIN HISTORY";
    String K_CPY_AIRITM = "AIRITM";
    char WK_MEMO_FLG = '8';
    String WS_ERR_MSG = " ";
    char WS_ITM_AWA = ' ';
    String WS_COA_NO = " ";
    int WS_ITM_LEN = 0;
    AIOT5101_WS_ITM_46_NO WS_ITM_46_NO = new AIOT5101_WS_ITM_46_NO();
    AIOT5101_WS_ITM_64_NO WS_ITM_64_NO = new AIOT5101_WS_ITM_64_NO();
    AIOT5101_WS_ITM_424_NO WS_ITM_424_NO = new AIOT5101_WS_ITM_424_NO();
    AIOT5101_WS_ITM_19_NO WS_ITM_19_NO = new AIOT5101_WS_ITM_19_NO();
    String WS_FST8_NO = " ";
    int WS_INT_X = 0;
    int WS_INT_Y = 0;
    int WS_INT_Z = 0;
    AIOT5101_WS_ITM_L WS_ITM_L = new AIOT5101_WS_ITM_L();
    char WS_AIRITM_FLG = ' ';
    char WS_END_FLG = ' ';
    char WS_GET_LEN_FLG = ' ';
    char WS_AITCMIB_FLG = ' ';
    char WS_MST_BAL_FLG = ' ';
    char WS_READ_ITM_CTL = ' ';
    BPCQBKPM BPCQBKPM = new BPCQBKPM();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AICSITAD AICSITAD = new AICSITAD();
    AIRITM AIROITM = new AIRITM();
    AIRITM AIRNITM = new AIRITM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCCKDT SCCCKDT = new SCCCKDT();
    AIRITM AIRITM = new AIRITM();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AIB0005_AWA_0005 AIB0005_AWA_0005;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "AIOT5101 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB0005_AWA_0005>");
        AIB0005_AWA_0005 = (AIB0005_AWA_0005) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_MAINTAIN_ADD_ITM_PROC();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (AIB0005_AWA_0005.FUNC == ' ' 
            || AIB0005_AWA_0005.COA_FLG.trim().length() == 0 
            || AIB0005_AWA_0005.COA_NO.trim().length() == 0 
            || AIB0005_AWA_0005.CNAME.trim().length() == 0 
            || AIB0005_AWA_0005.COA_TYP.trim().length() == 0 
            || AIB0005_AWA_0005.POSTTYP == ' ' 
            || AIB0005_AWA_0005.ITM_LVL == ' ' 
            || AIB0005_AWA_0005.LOW_IND == ' ' 
            || AIB0005_AWA_0005.ODE_FLG == ' ' 
            || AIB0005_AWA_0005.DR_CR == ' ' 
            || AIB0005_AWA_0005.RED_FLG == ' ' 
            || AIB0005_AWA_0005.BAL_IND == ' ') {
            CEP.TRC(SCCGWA, AIB0005_AWA_0005.FUNC);
            CEP.TRC(SCCGWA, AIB0005_AWA_0005.COA_FLG);
            CEP.TRC(SCCGWA, AIB0005_AWA_0005.COA_NO);
            CEP.TRC(SCCGWA, AIB0005_AWA_0005.CNAME);
            CEP.TRC(SCCGWA, AIB0005_AWA_0005.COA_TYP);
            CEP.TRC(SCCGWA, AIB0005_AWA_0005.POSTTYP);
            CEP.TRC(SCCGWA, AIB0005_AWA_0005.ITM_LVL);
            CEP.TRC(SCCGWA, AIB0005_AWA_0005.LOW_IND);
            CEP.TRC(SCCGWA, AIB0005_AWA_0005.MIB_FLG);
            CEP.TRC(SCCGWA, AIB0005_AWA_0005.ODE_FLG);
            CEP.TRC(SCCGWA, AIB0005_AWA_0005.DR_CR);
            CEP.TRC(SCCGWA, AIB0005_AWA_0005.RED_FLG);
            CEP.TRC(SCCGWA, AIB0005_AWA_0005.BAL_IND);
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.MUST_INPUT);
        }
        CEP.TRC(SCCGWA, AIB0005_AWA_0005.FUNC);
        WK_FUNC = AIB0005_AWA_0005.FUNC;
        if (WK_FUNC != 'C') {
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_IPT_FUNC_ERR);
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
        CEP.TRC(SCCGWA, AIB0005_AWA_0005.COA_TYP);
        if (AIB0005_AWA_0005.COA_TYP.equalsIgnoreCase("8") 
            || AIB0005_AWA_0005.COA_TYP.equalsIgnoreCase("9")) {
            if (AIB0005_AWA_0005.POSTTYP != 'M') {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_POST_TYPE_ERR);
            }
        } else {
            if (AIB0005_AWA_0005.POSTTYP != 'R') {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_POST_TYPE_ERR);
            }
        }
        CEP.TRC(SCCGWA, AIB0005_AWA_0005.ITM_LVL);
        if (AIB0005_AWA_0005.ITM_LVL == '1' 
            || AIB0005_AWA_0005.ITM_LVL == '2') {
            if (AIB0005_AWA_0005.LOW_IND != 'N') {
                CEP.TRC(SCCGWA, AIB0005_AWA_0005.LOW_IND);
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_9000);
            }
            AIB0005_AWA_0005.MIB_FLG = ' ';
            if (AIB0005_AWA_0005.ODE_FLG != 'N') {
                CEP.TRC(SCCGWA, AIB0005_AWA_0005.ODE_FLG);
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_CANT_USE_ODE);
            }
        } else {
            if (AIB0005_AWA_0005.LOW_IND != 'Y') {
                CEP.TRC(SCCGWA, AIB0005_AWA_0005.LOW_IND);
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_9000);
            }
        }
        if ((AIB0005_AWA_0005.LOW_IND == 'Y' 
            && (AIB0005_AWA_0005.MIB_FLG == 'Y' 
            || AIB0005_AWA_0005.MIB_FLG == 'N' 
            || AIB0005_AWA_0005.MIB_FLG == 'G')) 
            || (AIB0005_AWA_0005.LOW_IND == 'N' 
            && AIB0005_AWA_0005.MIB_FLG == ' ')) {
        } else {
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.MIB_NOT_MATCH_ODE);
        }
        if (AIB0005_AWA_0005.MIB_FLG == 'Y' 
            && AIB0005_AWA_0005.BAL_IND != 'N') {
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.MIB_NOT_MATCH_BAL_IND);
        }
        R100_GET_COA_LEN();
        CEP.TRC(SCCGWA, WS_INT_Z);
        if (AIB0005_AWA_0005.COA_TYP.equalsIgnoreCase("8") 
            || AIB0005_AWA_0005.COA_TYP.equalsIgnoreCase("9")) {
            if (WS_INT_Z == 3) {
                WS_ITM_19_NO.WS_FST1_NO = AIB0005_AWA_0005.COA_TYP.charAt(0);
                WS_ITM_19_NO.WS_TMP9_NO = AIB0005_AWA_0005.COA_NO;
                AIB0005_AWA_0005.COA_NO = IBS.CLS2CPY(SCCGWA, WS_ITM_19_NO);
            } else if (WS_INT_Z == 7
                || WS_INT_Z == 9) {
                WS_ITM_19_NO.WS_FST1_NO = AIB0005_AWA_0005.COA_TYP.charAt(0);
                WS_ITM_19_NO.WS_TMP9_NO = AIB0005_AWA_0005.COA_NO;
                AIB0005_AWA_0005.COA_NO = IBS.CLS2CPY(SCCGWA, WS_ITM_19_NO);
                WS_ITM_19_NO.WS_TMP9_NO = AIB0005_AWA_0005.UP_ITM;
                AIB0005_AWA_0005.UP_ITM = IBS.CLS2CPY(SCCGWA, WS_ITM_19_NO);
            }
        }
        CEP.TRC(SCCGWA, AIB0005_AWA_0005.ITM_LVL);
        CEP.TRC(SCCGWA, AIB0005_AWA_0005.COA_NO);
        CEP.TRC(SCCGWA, AIB0005_AWA_0005.UP_ITM);
        IBS.CPY2CLS(SCCGWA, AIB0005_AWA_0005.COA_NO, WS_ITM_46_NO);
        IBS.CPY2CLS(SCCGWA, AIB0005_AWA_0005.COA_NO, WS_ITM_64_NO);
        IBS.CPY2CLS(SCCGWA, AIB0005_AWA_0005.COA_NO, WS_ITM_424_NO);
        IBS.CPY2CLS(SCCGWA, AIB0005_AWA_0005.COA_NO, WS_ITM_19_NO);
        R100_GET_COA_LEN();
        CEP.TRC(SCCGWA, WS_INT_Z);
        if (AIB0005_AWA_0005.ITM_LVL == '1') {
            if (WS_INT_Z != 4) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_COA_NO_LEN_ERR);
            }
        } else if (AIB0005_AWA_0005.ITM_LVL == '2') {
            if (WS_INT_Z != 8) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_COA_NO_LEN_ERR);
            }
        } else if (AIB0005_AWA_0005.ITM_LVL == '3') {
            if (WS_INT_Z != 10) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_COA_NO_LEN_ERR);
            }
        }
        if (AIB0005_AWA_0005.ITM_LVL == '1') {
            AIB0005_AWA_0005.UP_ITM = " ";
            if (WS_ITM_19_NO.WS_FST1_NO == ' ' 
                || WS_ITM_46_NO.WS_LST6_NO.trim().length() > 0) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_9003);
            }
        } else if (AIB0005_AWA_0005.ITM_LVL == '2') {
            if (WS_ITM_424_NO.WS_TMP4_NO.trim().length() > 0 
                || WS_ITM_424_NO.WS_MID2_NO.trim().length() == 0 
                || WS_ITM_19_NO.WS_FST1_NO == ' ') {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_9003);
            }
            IBS.CPY2CLS(SCCGWA, AIB0005_AWA_0005.UP_ITM, WS_ITM_46_NO);
            IBS.CPY2CLS(SCCGWA, AIB0005_AWA_0005.COA_NO, WS_ITM_424_NO);
            if (!WS_ITM_46_NO.WS_PER4_NO.equalsIgnoreCase(WS_ITM_424_NO.WS_FST4_NO) 
                || WS_ITM_46_NO.WS_LST6_NO.trim().length() > 0) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_9004);
            }
        } else if (AIB0005_AWA_0005.ITM_LVL == '3') {
            if (WS_ITM_424_NO.WS_TMP4_NO.trim().length() == 0 
                || WS_ITM_424_NO.WS_MID2_NO.trim().length() == 0 
                || WS_ITM_19_NO.WS_FST1_NO == ' ') {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_9003);
            }
            IBS.CPY2CLS(SCCGWA, AIB0005_AWA_0005.UP_ITM, WS_ITM_424_NO);
            if (AIB0005_AWA_0005.COA_NO == null) AIB0005_AWA_0005.COA_NO = "";
            JIBS_tmp_int = AIB0005_AWA_0005.COA_NO.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) AIB0005_AWA_0005.COA_NO += " ";
            WS_FST8_NO = AIB0005_AWA_0005.COA_NO.substring(0, 8);
            if (WS_ITM_424_NO.WS_TMP4_NO.trim().length() > 0 
                || !WS_FST8_NO.equalsIgnoreCase(AIB0005_AWA_0005.UP_ITM)) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_9004);
            }
        } else {
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_9002);
        }
        CEP.TRC(SCCGWA, AIB0005_AWA_0005.EFF_DAT);
        if (AIB0005_AWA_0005.EFF_DAT == 0) {
            AIB0005_AWA_0005.EFF_DAT = SCCGWA.COMM_AREA.AC_DATE;
        } else {
            B110_CHECK_EFF_DATE();
        }
        CEP.TRC(SCCGWA, AIB0005_AWA_0005.EXP_DAT);
        if (AIB0005_AWA_0005.EXP_DAT == 0) {
            AIB0005_AWA_0005.EXP_DAT = 99991231;
        } else {
            B120_CHECK_EXP_DATE();
        }
    }
    public void B110_CHECK_EFF_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = AIB0005_AWA_0005.EFF_DAT;
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            CEP.TRC(SCCGWA, AIB0005_AWA_0005.EFF_DAT);
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.INPUT_DATE_ERROR);
        }
    }
    public void B120_CHECK_EXP_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = AIB0005_AWA_0005.EXP_DAT;
        SCSSCKDT SCSSCKDT2 = new SCSSCKDT();
        SCSSCKDT2.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            CEP.TRC(SCCGWA, AIB0005_AWA_0005.EXP_DAT);
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.INPUT_DATE_ERROR);
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (AIB0005_AWA_0005.EXP_DAT <= SCCGWA.COMM_AREA.AC_DATE) {
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_EXP_MUST_GT_ACDT);
        }
        if (AIB0005_AWA_0005.EXP_DAT <= AIB0005_AWA_0005.EFF_DAT) {
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_EXPDT_MUST_GT_EFFDT);
        }
        if (AIB0005_AWA_0005.EXP_DAT < SCCGWA.COMM_AREA.AC_DATE) {
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.EXP_MUST_GT_AC_DT);
        }
    }
    public void B200_MAINTAIN_ADD_ITM_PROC() throws IOException,SQLException,Exception {
        B210_CHK_ITM_ATTR();
        B220_ADD_ITM();
    }
    public void B210_CHK_ITM_ATTR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_READ_ITM_CTL);
        if (AIB0005_AWA_0005.ITM_LVL != '1') {
            WS_READ_ITM_CTL = 'Y';
            T00_SELECT_AITITM();
            WS_READ_ITM_CTL = ' ';
            if (WS_AIRITM_FLG == 'N') {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_9005);
            }
            if (AIB0005_AWA_0005.EFF_DAT < AIRITM.EFF_DATE) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_GL_AC_EFFDA_EARLIER);
            }
            if (AIB0005_AWA_0005.EXP_DAT > AIRITM.EXP_DATE) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_GL_AC_EXPDA_LASTER);
            }
            if (AIB0005_AWA_0005.POSTTYP != AIRITM.POST_TYPE) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_POST_TYPE_ERR);
            }
        }
        CEP.TRC(SCCGWA, "NOT READ UP ITM");
        CEP.TRC(SCCGWA, WS_READ_ITM_CTL);
        T00_SELECT_AITITM();
        if (WS_AIRITM_FLG == 'Y') {
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_COA_NO_EXIST);
        }
    }
    public void B220_ADD_ITM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRITM);
        AIRITM.KEY.COA_FLG = AIB0005_AWA_0005.COA_FLG;
        AIRITM.KEY.NO = AIB0005_AWA_0005.COA_NO;
        IBS.CPY2CLS(SCCGWA, AIB0005_AWA_0005.COA_NO, WS_ITM_19_NO);
        AIRITM.CHS_NM = AIB0005_AWA_0005.CNAME;
        if (!AIB0005_AWA_0005.COA_TYP.equalsIgnoreCase(WS_ITM_19_NO.WS_FST1_NO+"")) {
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_ITM_TYPE_MUST_FIRST);
        }
        if (AIB0005_AWA_0005.COA_FLG.equalsIgnoreCase("1") 
            || AIB0005_AWA_0005.COA_FLG.equalsIgnoreCase("01")) {
            if (WS_ITM_19_NO.WS_FST1_NO == '1'
                || WS_ITM_19_NO.WS_FST1_NO == '2'
                || WS_ITM_19_NO.WS_FST1_NO == '3'
                || WS_ITM_19_NO.WS_FST1_NO == '4'
                || WS_ITM_19_NO.WS_FST1_NO == '5'
                || WS_ITM_19_NO.WS_FST1_NO == '8'
                || WS_ITM_19_NO.WS_FST1_NO == '9') {
                AIRITM.TYPE = AIB0005_AWA_0005.COA_TYP;
            } else {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_ITM_NOT_VAL);
            }
        } else {
            AIRITM.TYPE = AIB0005_AWA_0005.COA_TYP;
        }
        CEP.TRC(SCCGWA, AIRITM.TYPE);
        AIRITM.POST_TYPE = AIB0005_AWA_0005.POSTTYP;
        AIRITM.DTL_IND = AIB0005_AWA_0005.LOW_IND;
        AIRITM.LOOKUP_CD = AIB0005_AWA_0005.LKUP_CD;
        AIRITM.BAL_ZERO_FLG = AIB0005_AWA_0005.BAL_IND;
        AIRITM.BAL_SIGN_FLG = AIB0005_AWA_0005.DR_CR;
        AIRITM.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AIRITM.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AIRITM.EFF_DATE = AIB0005_AWA_0005.EFF_DAT;
        AIRITM.EXP_DATE = AIB0005_AWA_0005.EXP_DAT;
        AIRITM.LAST_UPDATE_TLR = SCCGWA.COMM_AREA.TL_ID;
        if (AIB0005_AWA_0005.EFF_DAT <= SCCGWA.COMM_AREA.AC_DATE) {
            AIRITM.STS = 'A';
        }
        if (AIB0005_AWA_0005.EFF_DAT > SCCGWA.COMM_AREA.AC_DATE) {
            AIRITM.STS = 'P';
        }
        AIRITM.UP_ITM = AIB0005_AWA_0005.UP_ITM;
        AIRITM.RED_FLG = AIB0005_AWA_0005.RED_FLG;
        AIRITM.MIB_FLG = AIB0005_AWA_0005.MIB_FLG;
        AIRITM.ITM_LVL = AIB0005_AWA_0005.ITM_LVL;
        AIRITM.ODE_FLG = AIB0005_AWA_0005.ODE_FLG;
        T00_INSERT_AITITM();
        IBS.init(SCCGWA, BPCPNHIS);
        IBS.init(SCCGWA, AIROITM);
        BPCPNHIS.INFO.TX_TYP = 'A';
        IBS.CLONE(SCCGWA, AIRITM, AIRNITM);
        S000_CALL_BPZPNHIS();
        R000_TRANS_ITAD();
    }
    public void R100_GET_COA_LEN() throws IOException,SQLException,Exception {
        WS_INT_Z = 0;
        WS_GET_LEN_FLG = ' ';
        CEP.TRC(SCCGWA, WS_GET_LEN_FLG);
        for (WS_INT_X = 1; WS_INT_X <= 10 
            && WS_GET_LEN_FLG != 'Y'; WS_INT_X += 1) {
            if (AIB0005_AWA_0005.COA_NO == null) AIB0005_AWA_0005.COA_NO = "";
            JIBS_tmp_int = AIB0005_AWA_0005.COA_NO.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) AIB0005_AWA_0005.COA_NO += " ";
            CEP.TRC(SCCGWA, AIB0005_AWA_0005.COA_NO.substring(WS_INT_X - 1, WS_INT_X + 1 - 1));
            if (AIB0005_AWA_0005.COA_NO == null) AIB0005_AWA_0005.COA_NO = "";
            JIBS_tmp_int = AIB0005_AWA_0005.COA_NO.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) AIB0005_AWA_0005.COA_NO += " ";
            if (!IBS.isNumeric(AIB0005_AWA_0005.COA_NO.substring(WS_INT_X - 1, WS_INT_X + 1 - 1))) {
                WS_GET_LEN_FLG = 'Y';
            } else {
                WS_INT_Z += 1;
            }
        }
    }
    public void R000_TRANS_ITAD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICSITAD);
        AICSITAD.COMM_DATA.ADJ_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICSITAD.COMM_DATA.COA_TYP = AIB0005_AWA_0005.COA_FLG;
        AICSITAD.COMM_DATA.ITM_OLD = AIB0005_AWA_0005.COA_NO;
        AICSITAD.COMM_DATA.ITM_NEW = AIB0005_AWA_0005.COA_NO;
        AICSITAD.COMM_DATA.FUNC_FLG = AIB0005_AWA_0005.FUNC;
        AICSITAD.COMM_DATA.ITMNEW_D = AIB0005_AWA_0005.CNAME;
        AICSITAD.COMM_DATA.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        AICSITAD.FUNC = 'A';
        S000_CALL_AIZSITAD();
    }
    public void S000_CALL_BPZQBKPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-Q-MAINT-AMBKP", BPCQBKPM);
        CEP.TRC(SCCGWA, BPCQBKPM.RC);
        if (BPCQBKPM.RC.RC_RTNCODE != 0) {
            CEP.ERR(SCCGWA, BPCQBKPM.RC);
        }
    }
    public void S000_CALL_AIZSITAD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-SVR-AIZSITAD", AICSITAD);
        if (AICSITAD.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, AICSITAD.RC);
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        BPCPNHIS.INFO.TX_RMK = AI_HIS_REMARKS;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.FMT_ID = K_CPY_AIRITM;
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID_LEN = 648;
        BPCPNHIS.INFO.OLD_DAT_PT = AIROITM;
        BPCPNHIS.INFO.NEW_DAT_PT = AIRNITM;
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPNHIS.RC);
        }
    }
    public void T00_SELECT_AITITM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRITM);
        AIRITM.KEY.COA_FLG = AIB0005_AWA_0005.COA_FLG;
        AIRITM.KEY.NO = AIB0005_AWA_0005.COA_NO;
        if (WS_READ_ITM_CTL == 'Y') {
            AIRITM.KEY.NO = AIB0005_AWA_0005.UP_ITM;
        }
        AITITM_RD = new DBParm();
        AITITM_RD.TableName = "AITITM";
        IBS.READ(SCCGWA, AIRITM, AITITM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_AIRITM_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_AIRITM_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "OPEN AITITM ERROR!" + AIRITM.KEY.COA_FLG + AIRITM.KEY.NO;
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITITM";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T00_INSERT_AITITM() throws IOException,SQLException,Exception {
        AITITM_RD = new DBParm();
        AITITM_RD.TableName = "AITITM";
        AITITM_RD.errhdl = true;
        IBS.WRITE(SCCGWA, AIRITM, AITITM_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_COA_NO_EXIST);
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "OPEN AITITM ERROR!" + AIRITM.KEY.COA_FLG + AIRITM.KEY.NO;
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITITM";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
