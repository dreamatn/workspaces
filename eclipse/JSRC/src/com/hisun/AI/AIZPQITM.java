package com.hisun.AI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class AIZPQITM {
    String JIBS_tmp_str[] = new String[10];
    DBParm AITITM_RD;
    boolean pgmRtn = false;
    String DD_U_CLEAR_CCY = "DD-U-CLEAR-CCY      ";
    short WS_INDEX = 0;
    short WS_CNT = 0;
    String WS_ERR_MSG = " ";
    String WS_ITM_COA_FLG = " ";
    short WS_ITM_LEN = 0;
    short WS_COA_LEN = 0;
    short WS_IDX = 0;
    short WS_INT_X = 0;
    String WS_ITM_NO = " ";
    char WS_SL_FLG = ' ';
    AIZPQITM_WS_COA_NO WS_COA_NO = new AIZPQITM_WS_COA_NO();
    char WS_AIRITM_FLG = ' ';
    char WS_END_FLG = ' ';
    char WS_CONTROL_BKPM = ' ';
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AIRITM AIRITM = new AIRITM();
    BPCQBKPM BPCQBKPM = new BPCQBKPM();
    SCCGWA SCCGWA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AICPQITM AICQITM;
    public void MP(SCCGWA SCCGWA, AICPQITM AICQITM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICQITM = AICQITM;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, WS_CONTROL_BKPM);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZPQITM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_INQ_COA_INFO_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AICQITM.INPUT_DATA.BOOK_FLG);
        CEP.TRC(SCCGWA, AICQITM.INPUT_DATA.COA_FLG);
        CEP.TRC(SCCGWA, AICQITM.INPUT_DATA.NO);
        if (AICQITM.INPUT_DATA.BOOK_FLG.trim().length() == 0 
            && AICQITM.INPUT_DATA.COA_FLG.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_BF_OR_CF_M_INP_ONE, AICQITM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (AICQITM.INPUT_DATA.NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_COA_NO_MUST_INPUT, AICQITM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        AICQITM.INPUT_DATA.DATE = SCCGWA.COMM_AREA.AC_DATE;
    }
    public void B020_INQ_COA_INFO_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICQITM.OUTPUT_DATA);
        CEP.TRC(SCCGWA, AICQITM.INPUT_DATA.COA_FLG);
        if (AICQITM.INPUT_DATA.COA_FLG.trim().length() == 0) {
            CEP.TRC(SCCGWA, "RUN");
            if (WS_CONTROL_BKPM != 'Y') {
                IBS.init(SCCGWA, BPCQBKPM);
                BPCQBKPM.FUNC = 'Q';
                BPCQBKPM.KEY.BK_FLG = AICQITM.INPUT_DATA.BOOK_FLG;
                CEP.TRC(SCCGWA, AICQITM.INPUT_DATA.BOOK_FLG);
                S000_CALL_BPZQBKPM();
                if (pgmRtn) return;
            }
            AICQITM.INPUT_DATA.COA_FLG = BPCQBKPM.DATA[1-1].COA_FLG;
            CEP.TRC(SCCGWA, BPCQBKPM.DATA[1-1].ITM_LEN);
            WS_ITM_LEN = (short) BPCQBKPM.DATA[1-1].ITM_LEN;
        } else {
            CEP.TRC(SCCGWA, "RUNED");
            if (WS_CONTROL_BKPM != 'Y') {
                IBS.init(SCCGWA, BPCQBKPM);
                BPCQBKPM.FUNC = 'B';
                BPCQBKPM.COA_TYP = AICQITM.INPUT_DATA.COA_FLG;
                S000_CALL_BPZQBKPM();
                if (pgmRtn) return;
            }
            WS_END_FLG = 'Y';
            for (WS_INT_X = 1; WS_INT_X <= 10 
                && WS_END_FLG != 'N'; WS_INT_X += 1) {
                CEP.TRC(SCCGWA, BPCQBKPM.DATA[WS_INT_X-1].COA_FLG);
                CEP.TRC(SCCGWA, AICQITM.INPUT_DATA.COA_FLG);
                if (BPCQBKPM.DATA[WS_INT_X-1].COA_FLG.equalsIgnoreCase(AICQITM.INPUT_DATA.COA_FLG)) {
                    WS_ITM_LEN = (short) BPCQBKPM.DATA[WS_INT_X-1].ITM_LEN;
                    CEP.TRC(SCCGWA, BPCQBKPM.DATA[WS_INT_X-1].ITM_LEN);
                    WS_END_FLG = 'N';
                }
            }
        }
        CEP.TRC(SCCGWA, WS_ITM_LEN);
        WS_ITM_NO = AICQITM.INPUT_DATA.NO;
        WS_COA_LEN = 10;
        IBS.CPY2CLS(SCCGWA, WS_ITM_NO, WS_COA_NO);
        CEP.TRC(SCCGWA, WS_COA_NO);
        WS_IDX = (short) (WS_COA_LEN - WS_ITM_LEN + 1);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_COA_NO);
        CEP.TRC(SCCGWA, JIBS_tmp_str[0].substring(WS_IDX - 1, WS_IDX + 1 - 1));
        CEP.TRC(SCCGWA, AICQITM.OUTPUT_DATA.COMP_ZERO_FLG);
        T00_SELECT_AITITM();
        if (pgmRtn) return;
        if (WS_AIRITM_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_READ_AITITM_NOTFND, AICQITM.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            R00_RETURN_DATA();
            if (pgmRtn) return;
        }
    }
    public void R00_RETURN_DATA() throws IOException,SQLException,Exception {
        AICQITM.INPUT_DATA.COA_FLG = AIRITM.KEY.COA_FLG;
        AICQITM.INPUT_DATA.NO = AIRITM.KEY.NO;
        CEP.TRC(SCCGWA, AICQITM.INPUT_DATA.COA_FLG);
        CEP.TRC(SCCGWA, AICQITM.INPUT_DATA.NO);
        CEP.TRC(SCCGWA, AIRITM.ENG_NM);
        CEP.TRC(SCCGWA, AIRITM.CHS_NM);
        AICQITM.OUTPUT_DATA.ENG_NM = AIRITM.ENG_NM;
        AICQITM.OUTPUT_DATA.CHS_NM = AIRITM.CHS_NM;
        CEP.TRC(SCCGWA, AICQITM.OUTPUT_DATA.ENG_NM);
        CEP.TRC(SCCGWA, AICQITM.OUTPUT_DATA.CHS_NM);
        AICQITM.OUTPUT_DATA.TYPE = AIRITM.TYPE;
        AICQITM.OUTPUT_DATA.CATE = AIRITM.CATE;
        AICQITM.OUTPUT_DATA.POST_TYPE = AIRITM.POST_TYPE;
        AICQITM.OUTPUT_DATA.AUTO_GEN = AIRITM.AUTO_GEN;
        AICQITM.OUTPUT_DATA.LOW_LVL_FLG = AIRITM.DTL_IND;
        AICQITM.OUTPUT_DATA.FX_REVAL_FLG = AIRITM.FX_REVAL_FLG;
        AICQITM.OUTPUT_DATA.GLMST = AIRITM.GLMST;
        AICQITM.OUTPUT_DATA.ANS_CD1 = AIRITM.ANS_CD1;
        AICQITM.OUTPUT_DATA.ANS_CD2 = AIRITM.ANS_CD2;
        AICQITM.OUTPUT_DATA.ANS_CD3 = AIRITM.ANS_CD3;
        AICQITM.OUTPUT_DATA.ANS_CD4 = AIRITM.ANS_CD4;
        AICQITM.OUTPUT_DATA.ANS_CD5 = AIRITM.ANS_CD5;
        AICQITM.OUTPUT_DATA.ANS_CD6 = AIRITM.ANS_CD6;
        AICQITM.OUTPUT_DATA.ANS_CD7 = AIRITM.ANS_CD7;
        AICQITM.OUTPUT_DATA.ANS_CD8 = AIRITM.ANS_CD8;
        AICQITM.OUTPUT_DATA.ANS_CD9 = AIRITM.ANS_CD9;
        AICQITM.OUTPUT_DATA.ANS_CD10 = AIRITM.ANS_CD10;
        AICQITM.OUTPUT_DATA.LOOKUP_CD = AIRITM.LOOKUP_CD;
        AICQITM.OUTPUT_DATA.SENS_LVL = AIRITM.SENS_LVL;
        AICQITM.OUTPUT_DATA.BAL_ZERO_FLG = AIRITM.BAL_ZERO_FLG;
        AICQITM.OUTPUT_DATA.BAL_SIGN_FLG = AIRITM.BAL_SIGN_FLG;
        AICQITM.OUTPUT_DATA.AUTO_MATCH_FLG = AIRITM.AUTO_MATCH_FLG;
        AICQITM.OUTPUT_DATA.AUTH_LVL = AIRITM.AUTH_LVL;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, AIRITM.STS);
        CEP.TRC(SCCGWA, AIRITM.EFF_DATE);
        CEP.TRC(SCCGWA, AIRITM.EXP_DATE);
        CEP.TRC(SCCGWA, AIRITM.BAL_SIGN_FLG);
        CEP.TRC(SCCGWA, AICQITM.INPUT_DATA.DATE);
        if (AIRITM.STS == 'P' 
            && AIRITM.EFF_DATE <= AICQITM.INPUT_DATA.DATE) {
            AICQITM.OUTPUT_DATA.STS = 'A';
            WS_CNT += 1;
        } else {
            if (AIRITM.STS != 'A') {
                AICQITM.OUTPUT_DATA.STS = AIRITM.STS;
                WS_CNT += 1;
            }
        }
        if (WS_CNT == 0) {
            if (AIRITM.STS == 'A' 
                && AIRITM.EXP_DATE > AICQITM.INPUT_DATA.DATE) {
                AICQITM.OUTPUT_DATA.STS = 'A';
            } else {
                AICQITM.OUTPUT_DATA.STS = 'S';
            }
        }
        CEP.TRC(SCCGWA, AICQITM.OUTPUT_DATA.STS);
        AICQITM.OUTPUT_DATA.OPEN_DATE = AIRITM.OPEN_DATE;
        AICQITM.OUTPUT_DATA.EFF_DATE = AIRITM.EFF_DATE;
        AICQITM.OUTPUT_DATA.REOPEN_DATE = AIRITM.REOPEN_DATE;
        AICQITM.OUTPUT_DATA.UPD_DATE = AIRITM.UPDTBL_DATE;
        AICQITM.OUTPUT_DATA.EXP_DATE = AIRITM.EXP_DATE;
        AICQITM.OUTPUT_DATA.MIB_FLG = AIRITM.MIB_FLG;
        AICQITM.OUTPUT_DATA.ITM_LVL = AIRITM.ITM_LVL;
        AICQITM.OUTPUT_DATA.RED_FLG = AIRITM.RED_FLG;
        AICQITM.OUTPUT_DATA.UP_ITM = AIRITM.UP_ITM;
        AICQITM.OUTPUT_DATA.EXP_DATE = AIRITM.EXP_DATE;
        AICQITM.OUTPUT_DATA.RESET_FLG = AIRITM.RESET_FLG;
        AICQITM.OUTPUT_DATA.SL_FLG = AIRITM.SL_FLG;
        AICQITM.OUTPUT_DATA.ODE_FLG = AIRITM.ODE_FLG;
        CEP.TRC(SCCGWA, AIRITM.ODE_FLG);
        CEP.TRC(SCCGWA, AIRITM.SL_FLG);
        CEP.TRC(SCCGWA, AICQITM.OUTPUT_DATA.SL_FLG);
    }
    public void S000_CALL_BPZQBKPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-Q-MAINT-AMBKP", BPCQBKPM);
        if (BPCQBKPM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQBKPM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], AICQITM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_CONTROL_BKPM = 'Y';
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void T00_SELECT_AITITM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRITM);
        AIRITM.KEY.COA_FLG = AICQITM.INPUT_DATA.COA_FLG;
        AIRITM.KEY.NO = AICQITM.INPUT_DATA.NO;
        CEP.TRC(SCCGWA, AIRITM.KEY.COA_FLG);
        CEP.TRC(SCCGWA, AIRITM.KEY.NO);
        AITITM_RD = new DBParm();
        AITITM_RD.TableName = "AITITM";
        IBS.READ(SCCGWA, AIRITM, AITITM_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_AIRITM_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_AIRITM_FLG = 'N';
        } else {
            WS_ITM_COA_FLG = AIRITM.KEY.COA_FLG;
            WS_ITM_NO = AIRITM.KEY.NO;
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "OPEN AITITM ERROR!" + WS_ITM_COA_FLG + WS_ITM_NO;
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITITM";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (AICQITM.RC.RTNCODE != 0) {
            CEP.TRC(SCCGWA, "AICPQITM = ");
            CEP.TRC(SCCGWA, AICQITM);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
