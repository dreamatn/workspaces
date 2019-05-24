package com.hisun.AI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCQBKPM;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class AIOT5102 {
    boolean pgmRtn = false;
    char WK_FUNC = ' ';
    String PGM_SCSSCKDT = "SCSSCKDT";
    String AI_HIS_REMARKS = "GL A/C NO. MAINTAIN HISTORY";
    String K_CPY_AIRITM = "AIRITM";
    char WK_MEMO_FLG = '8';
    String WS_ERR_MSG = " ";
    char WS_ITM_AWA = ' ';
    String WS_COA_NO = " ";
    int WS_ITM_LEN = 0;
    AIOT5102_WS_ITM_46_NO WS_ITM_46_NO = new AIOT5102_WS_ITM_46_NO();
    AIOT5102_WS_ITM_82_NO WS_ITM_82_NO = new AIOT5102_WS_ITM_82_NO();
    AIOT5102_WS_ITM_442_NO WS_ITM_442_NO = new AIOT5102_WS_ITM_442_NO();
    AIOT5102_WS_ITM_19_NO WS_ITM_19_NO = new AIOT5102_WS_ITM_19_NO();
    String WS_FST8_NO = " ";
    int WS_INT_X = 0;
    int WS_INT_Y = 0;
    int WS_INT_Z = 0;
    AIOT5102_WS_ITM_L WS_ITM_L = new AIOT5102_WS_ITM_L();
    char WS_AIRITM_FLG = ' ';
    char WS_END_FLG = ' ';
    char WS_GET_LEN_FLG = ' ';
    char WS_AITCMIB_FLG = ' ';
    char WS_MST_BAL_FLG = ' ';
    char WS_READ_ITM_CTL = ' ';
    char WS_AIRCMIB_FLG = ' ';
    char WS_AIRMIB_FLG = ' ';
    char WS_AIRGINF_FLG = ' ';
    BPCQBKPM BPCQBKPM = new BPCQBKPM();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AICSITAD AICSITAD = new AICSITAD();
    AIRITM AIROITM = new AIRITM();
    AIRITM AIRNITM = new AIRITM();
    AIRMIB AIRMIB = new AIRMIB();
    AIRCMIB AIRMCMIB = new AIRCMIB();
    AIRGINF AIRGINF = new AIRGINF();
    AICSCMIB AICSCMIB = new AICSCMIB();
    AICRCMIB AICRCMIB = new AICRCMIB();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCCKDT SCCCKDT = new SCCCKDT();
    AIRITM AIRITM = new AIRITM();
    AIRCMIB AIRCMIB = new AIRCMIB();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AIB0005_AWA_0005 AIB0005_AWA_0005;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIOT5102 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB0005_AWA_0005>");
        AIB0005_AWA_0005 = (AIB0005_AWA_0005) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_MAINTAIN_MODIFY_ITM_PROC();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (AIB0005_AWA_0005.FUNC == ' ' 
            || AIB0005_AWA_0005.COA_FLG.trim().length() == 0 
            || AIB0005_AWA_0005.COA_NO.trim().length() == 0) {
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
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.MUST_INPUT);
        }
        CEP.TRC(SCCGWA, AIB0005_AWA_0005.FUNC);
        WK_FUNC = AIB0005_AWA_0005.FUNC;
        if (WK_FUNC != 'M') {
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_IPT_FUNC_ERR);
        }
        CEP.TRC(SCCGWA, AIB0005_AWA_0005.COA_FLG);
        if (AIB0005_AWA_0005.COA_FLG.trim().length() > 0) {
            IBS.init(SCCGWA, BPCQBKPM);
            BPCQBKPM.FUNC = 'B';
            BPCQBKPM.COA_TYP = AIB0005_AWA_0005.COA_FLG;
            S000_CALL_BPZQBKPM();
            if (pgmRtn) return;
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
        if (pgmRtn) return;
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
        IBS.CPY2CLS(SCCGWA, AIB0005_AWA_0005.COA_NO, WS_ITM_82_NO);
        IBS.CPY2CLS(SCCGWA, AIB0005_AWA_0005.COA_NO, WS_ITM_442_NO);
        IBS.CPY2CLS(SCCGWA, AIB0005_AWA_0005.COA_NO, WS_ITM_19_NO);
        R100_GET_COA_LEN();
        if (pgmRtn) return;
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
            if (WS_ITM_82_NO.WS_LST2_NO.trim().length() > 0 
                || WS_ITM_46_NO.WS_LST6_NO.trim().length() == 0 
                || WS_ITM_19_NO.WS_FST1_NO == ' ') {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_9003);
            }
            IBS.CPY2CLS(SCCGWA, AIB0005_AWA_0005.UP_ITM, WS_ITM_46_NO);
            IBS.CPY2CLS(SCCGWA, AIB0005_AWA_0005.COA_NO, WS_ITM_442_NO);
            if (!WS_ITM_46_NO.WS_PER4_NO.equalsIgnoreCase(WS_ITM_442_NO.WS_FST4_NO) 
                || WS_ITM_46_NO.WS_LST6_NO.trim().length() > 0) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_9004);
            }
        } else if (AIB0005_AWA_0005.ITM_LVL == '3') {
            if (WS_ITM_442_NO.WS_TMP2_NO.trim().length() == 0 
                || WS_ITM_442_NO.WS_MID4_NO.trim().length() == 0 
                || WS_ITM_442_NO.WS_FST4_NO.trim().length() == 0) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_9003);
            }
            IBS.CPY2CLS(SCCGWA, AIB0005_AWA_0005.UP_ITM, WS_ITM_82_NO);
            if (AIB0005_AWA_0005.COA_NO == null) AIB0005_AWA_0005.COA_NO = "";
            JIBS_tmp_int = AIB0005_AWA_0005.COA_NO.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) AIB0005_AWA_0005.COA_NO += " ";
            WS_FST8_NO = AIB0005_AWA_0005.COA_NO.substring(0, 8);
            if (WS_ITM_82_NO.WS_LST2_NO.trim().length() > 0 
                || !AIB0005_AWA_0005.UP_ITM.equalsIgnoreCase(WS_FST8_NO)) {
                CEP.TRC(SCCGWA, WS_ITM_82_NO.WS_LST2_NO);
                CEP.TRC(SCCGWA, AIB0005_AWA_0005.UP_ITM);
                CEP.TRC(SCCGWA, WS_FST8_NO);
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_9004);
            }
        } else {
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_9002);
        }
        CEP.TRC(SCCGWA, AIB0005_AWA_0005.EFF_DAT);
        AIB0005_AWA_0005.EFF_DAT = 0;
        CEP.TRC(SCCGWA, AIB0005_AWA_0005.EXP_DAT);
        if (AIB0005_AWA_0005.EXP_DAT == 0) {
            AIB0005_AWA_0005.EXP_DAT = 99991231;
        } else {
            B120_CHECK_EXP_DATE();
            if (pgmRtn) return;
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
    public void B200_MAINTAIN_MODIFY_ITM_PROC() throws IOException,SQLException,Exception {
        B210_CHK_ITM_ATTR();
        if (pgmRtn) return;
        B220_MODIFY_ITM();
        if (pgmRtn) return;
    }
    public void B210_CHK_ITM_ATTR() throws IOException,SQLException,Exception {
        if (AIB0005_AWA_0005.ITM_LVL != '1') {
            WS_READ_ITM_CTL = 'Y';
            T000_SELECT_AITITM();
            if (pgmRtn) return;
            WS_READ_ITM_CTL = ' ';
            if (WS_AIRITM_FLG == 'N') {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_9005);
            }
            if (AIB0005_AWA_0005.EXP_DAT > AIRITM.EXP_DATE) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_GL_AC_EXPDA_LASTER);
            }
            if (AIB0005_AWA_0005.POSTTYP != AIRITM.POST_TYPE) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_POST_TYPE_ERR);
            }
        }
        T000_SELECT_AITITM();
        if (pgmRtn) return;
        if (WS_AIRITM_FLG == 'N') {
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_COA_NO_INFO_NOTFND);
        }
        if (AIB0005_AWA_0005.EFF_DAT != AIRITM.EFF_DATE) {
            if (AIB0005_AWA_0005.EFF_DAT > SCCGWA.COMM_AREA.AC_DATE 
                && AIRITM.STS == 'A') {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_EFF_DAYS_ERR);
            }
        }
        if (AIB0005_AWA_0005.EFF_DAT >= AIB0005_AWA_0005.EXP_DAT) {
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_EXPDT_MUST_GT_EFFDT);
        }
        if (AIB0005_AWA_0005.EXP_DAT < SCCGWA.COMM_AREA.AC_DATE) {
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.EXP_MUST_GT_AC_DT);
        }
        if (((AIB0005_AWA_0005.RED_FLG == 'N' 
            && AIRITM.RED_FLG == 'Y') 
            || (AIB0005_AWA_0005.ODE_FLG == 'N' 
            && AIRITM.ODE_FLG == 'Y') 
            || AIB0005_AWA_0005.EXP_DAT != AIRITM.EXP_DATE) 
            && AIRITM.ITM_LVL == '3') {
            AIRCMIB.KEY.ITM = AIRITM.KEY.NO;
            T000_OPEN_AITCMIB();
            if (pgmRtn) return;
            T000_FETCH_AITCMIB();
            if (pgmRtn) return;
            while (WS_AIRCMIB_FLG != 'N') {
                if (AIRCMIB.AC_EXP > AIB0005_AWA_0005.EXP_DAT) {
                    AIRCMIB.AC_EXP = AIB0005_AWA_0005.EXP_DAT;
                }
                if (AIRCMIB.EXP_DATE > AIB0005_AWA_0005.EXP_DAT) {
                    AIRCMIB.EXP_DATE = AIB0005_AWA_0005.EXP_DAT;
                }
                if (AIB0005_AWA_0005.RED_FLG == 'N' 
                    && AIRITM.RED_FLG == 'Y') {
                    if (AIRCMIB.ONL_FLG == 'N' 
                        && AIRCMIB.BAL_DIR != 'B') {
                        CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.NOT_ONL_UPD_MUST_B);
                    } else {
                        AIRCMIB.BAL_RFLG = 'N';
                    }
                }
                if (AIB0005_AWA_0005.ODE_FLG == 'N' 
                    && AIRITM.ODE_FLG == 'Y') {
                    AIRCMIB.MANUAL_FLG = 'N';
                }
                T000_REWRITE_AITCMIB();
                if (pgmRtn) return;
                T000_FETCH_AITCMIB();
                if (pgmRtn) return;
            }
            T000_CLOSE_AITCMIB();
            if (pgmRtn) return;
            AIRMIB.KEY.ITM_NO = AIRITM.KEY.NO;
            T000_OPEN_AITMIB();
            if (pgmRtn) return;
            T000_FETCH_AITMIB();
            if (pgmRtn) return;
            while (WS_AIRMIB_FLG != 'N') {
                if (AIRMIB.AC_EXP > AIB0005_AWA_0005.EXP_DAT) {
                    AIRMIB.AC_EXP = AIB0005_AWA_0005.EXP_DAT;
                }
                if (AIB0005_AWA_0005.RED_FLG == 'N' 
                    && AIRITM.RED_FLG == 'Y') {
                    AIRMIB.BAL_RFLG = 'N';
                }
                if (AIB0005_AWA_0005.ODE_FLG == 'N' 
                    && AIRITM.ODE_FLG == 'Y') {
                    AIRMIB.MANUAL_FLG = 'N';
                }
                T000_REWRITE_AITMIB();
                if (pgmRtn) return;
                T000_FETCH_AITMIB();
                if (pgmRtn) return;
            }
            T000_CLOSE_AITMIB();
            if (pgmRtn) return;
        }
        if (AIB0005_AWA_0005.EXP_DAT != AIRITM.EXP_DATE 
            && AIRITM.ITM_LVL == '3') {
            AIRGINF.ITM = AIRITM.KEY.NO;
            T000_OPEN_AITGINF();
            if (pgmRtn) return;
            T000_FETCH_AITGINF();
            if (pgmRtn) return;
            while (WS_AIRGINF_FLG != 'N') {
                if (AIRGINF.RVS_EXP > AIB0005_AWA_0005.EXP_DAT) {
                    AIRGINF.RVS_EXP = AIB0005_AWA_0005.EXP_DAT;
                }
                T000_REWRITE_AITGINF();
                if (pgmRtn) return;
                T000_FETCH_AITGINF();
                if (pgmRtn) return;
            }
            T000_CLOSE_AITGINF();
            if (pgmRtn) return;
        }
        if (AIB0005_AWA_0005.ODE_FLG == 'N' 
            && AIRITM.ITM_LVL == '3' 
            && AIRITM.ODE_FLG == 'Y') {
            IBS.init(SCCGWA, AIRMCMIB);
            IBS.init(SCCGWA, AICRCMIB);
            AIRMCMIB.KEY.ITM = AIRITM.KEY.NO;
            T000_STARTBR_CMIB();
            if (pgmRtn) return;
            T000_READNEXT_CMIB();
            if (pgmRtn) return;
            while (WS_AITCMIB_FLG != 'N') {
                if (AIRMCMIB.MANUAL_FLG == 'Y') {
                    S000_MOD_WITH_CMIB();
                    if (pgmRtn) return;
                }
                T000_READNEXT_CMIB();
                if (pgmRtn) return;
            }
            T000_ENDBR_CMIB();
            if (pgmRtn) return;
        }
        if (AIB0005_AWA_0005.ODE_FLG == 'Y' 
            && AIRITM.ITM_LVL == '3' 
            && AIRITM.ODE_FLG == 'N') {
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_MOD_CMIB_MANUAL);
        }
        if (AIRITM.MIB_FLG != 'Y' 
            && AIRITM.STS == 'A' 
            && AIB0005_AWA_0005.MIB_FLG != AIRITM.MIB_FLG) {
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.MIB_NOT_MATCH_ITM_STS);
        }
        if ((AIB0005_AWA_0005.MIB_FLG == 'N' 
            || AIB0005_AWA_0005.MIB_FLG == 'G') 
            && AIRITM.ITM_LVL == '3' 
            && AIRITM.MIB_FLG == 'Y') {
            AIRMIB.KEY.ITM_NO = AIRITM.KEY.NO;
            T000_OPEN_AITMIB();
            if (pgmRtn) return;
            T000_FETCH_AITMIB();
            if (pgmRtn) return;
            while (WS_AIRMIB_FLG != 'N') {
                if (AIRMIB.STS != 'C') {
                    CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_MIB_NOT_ALL_CANCEL);
                }
                T000_FETCH_AITMIB();
                if (pgmRtn) return;
            }
            T000_CLOSE_AITMIB();
            if (pgmRtn) return;
            IBS.init(SCCGWA, AIRCMIB);
            AIRCMIB.KEY.ITM = AIRITM.KEY.NO;
            T000_OPEN_AITCMIB();
            if (pgmRtn) return;
            T000_FETCH_AITCMIB();
            if (pgmRtn) return;
            while (WS_AIRCMIB_FLG != 'N') {
                AIRCMIB.STS = 'S';
                T000_UPDATE_CMIB_STS();
                if (pgmRtn) return;
                T000_FETCH_AITCMIB();
                if (pgmRtn) return;
            }
            T000_CLOSE_AITCMIB();
            if (pgmRtn) return;
        }
        if (AIRITM.ITM_LVL == '3' 
            && AIRITM.STS != 'A' 
            && AIRITM.STS != 'P') {
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_ITM_STS_ERROR);
        }
        IBS.CLONE(SCCGWA, AIRITM, AIRNITM);
