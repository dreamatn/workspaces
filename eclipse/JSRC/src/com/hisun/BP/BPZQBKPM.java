package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;
import com.hisun.SC.*;

public class BPZQBKPM {
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    int WS_J = 0;
    BPZQBKPM_WS_COA WS_COA = new BPZQBKPM_WS_COA();
    BPZQBKPM_WS_KEY WS_KEY = new BPZQBKPM_WS_KEY();
    BPZQBKPM_WS_DATA WS_DATA = new BPZQBKPM_WS_DATA();
    BPZQBKPM_WS_DATA_TMP WS_DATA_TMP = new BPZQBKPM_WS_DATA_TMP();
    char WS_DATA_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPRPARM BPRPARM = new BPRPARM();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCPRMB BPCPRMB = new BPCPRMB();
    BPRBKPM BPRBKPM = new BPRBKPM();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    BPCQBKPM BPCQBKPM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCQBKPM BPCQBKPM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCQBKPM = BPCQBKPM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZQBKPM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCQBKPM.RC.RC_APP = "BP";
        BPCQBKPM.RC.RC_RTNCODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        if (BPCQBKPM.FUNC == 'Q') {
            B010_QUERY_AMBKP_PROCESS();
            if (pgmRtn) return;
        } else if (BPCQBKPM.FUNC == 'B') {
            B050_BROWSE_AMBKP_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCQBKPM.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCQBKPM.FUNC != 'Q' 
            && BPCQBKPM.FUNC != 'B') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FUNC_ERROR, BPCQBKPM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCQBKPM.FUNC == 'Q') {
            if (BPCQBKPM.KEY.BK_FLG.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_GL_BOOK_MUST_INPUT, BPCQBKPM.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_QUERY_AMBKP_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBKPM);
        IBS.init(SCCGWA, BPCPRMR);
        BPRBKPM.KEY.TYPE = "AMBKP";
        BPRBKPM.KEY.REDEFINES6.CNT = 10;
        BPRBKPM.KEY.CD = IBS.CLS2CPY(SCCGWA, BPRBKPM.KEY.REDEFINES6);
        BPCPRMR.FUNC = ' ';
        CEP.TRC(SCCGWA, BPCPRMR);
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        for (WS_CNT = 1; WS_CNT <= 10; WS_CNT += 1) {
            if (BPRBKPM.DAT_TXT.DATA[WS_CNT-1].BOOK_FLG.equalsIgnoreCase(BPCQBKPM.KEY.BK_FLG)) {
                if (BPRBKPM.DAT_TXT.DATA[WS_CNT-1].STS != 'A') {
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BOOK_FLAG_NOT_ACTIVE, BPCQBKPM.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                BPCQBKPM.CNT = 1;
                BPCQBKPM.DATA[BPCQBKPM.CNT-1].BOOK_FLG = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].BOOK_FLG;
                CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].BOOK_FLG);
                BPCQBKPM.DATA[BPCQBKPM.CNT-1].BOOK_TYP = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].BOOK_TYP;
                CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].BOOK_TYP);
                BPCQBKPM.DATA[BPCQBKPM.CNT-1].COA_FLG = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].COA_FLG;
                CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].COA_FLG);
                BPCQBKPM.DATA[BPCQBKPM.CNT-1].ITM_LEN = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].ITM_LEN;
                CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].ITM_LEN);
                BPCQBKPM.DATA[BPCQBKPM.CNT-1].STS = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].STS;
                CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].STS);
                BPCQBKPM.DATA[BPCQBKPM.CNT-1].REAL_SUS_ITM = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].REAL_SUS_ITM;
                CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].REAL_SUS_ITM);
                BPCQBKPM.DATA[BPCQBKPM.CNT-1].MEMO_SUS_ITM = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].MEMO_SUS_ITM;
                CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].MEMO_SUS_ITM);
                BPCQBKPM.DATA[BPCQBKPM.CNT-1].PL_ITM = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].PL_ITM;
                CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].PL_ITM);
                BPCQBKPM.DATA[BPCQBKPM.CNT-1].CRS_BR_ITM = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].CRS_BR_ITM;
                CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].CRS_BR_ITM);
                BPCQBKPM.DATA[BPCQBKPM.CNT-1].CRS_BR_ITM_M = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].CRS_BR_ITM_M;
                CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].CRS_BR_ITM_M);
                BPCQBKPM.DATA[BPCQBKPM.CNT-1].SEQ1 = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].SEQ1;
                CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].SEQ1);
                BPCQBKPM.DATA[BPCQBKPM.CNT-1].SEQ2 = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].SEQ2;
                CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].SEQ2);
                BPCQBKPM.DATA[BPCQBKPM.CNT-1].SEQ3 = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].SEQ3;
                CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].SEQ3);
                BPCQBKPM.DATA[BPCQBKPM.CNT-1].SEQ4 = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].SEQ4;
                CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].SEQ4);
                BPCQBKPM.DATA[BPCQBKPM.CNT-1].SEQ5 = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].SEQ5;
                CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].SEQ5);
                BPCQBKPM.DATA[BPCQBKPM.CNT-1].LR_ITM = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].LR_ITM;
                CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].LR_ITM);
                BPCQBKPM.DATA[BPCQBKPM.CNT-1].JS_ITM = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].JS_ITM;
                CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].JS_ITM);
                BPCQBKPM.DATA[BPCQBKPM.CNT-1].DH_ITM = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].DH_ITM;
                CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].DH_ITM);
                BPCQBKPM.DATA[BPCQBKPM.CNT-1].WH_ITM = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].WH_ITM;
                CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].WH_ITM);
                BPCQBKPM.DATA[BPCQBKPM.CNT-1].FZ_DATE = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].FZ_DATE;
                CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].FZ_DATE);
                BPCQBKPM.DATA[BPCQBKPM.CNT-1].SEQ6 = (short) BPRBKPM.DAT_TXT.DATA[WS_CNT-1].SEQ6;
                CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].SEQ6);
                BPCQBKPM.DATA[BPCQBKPM.CNT-1].SEQ7 = (short) BPRBKPM.DAT_TXT.DATA[WS_CNT-1].SEQ7;
                CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].SEQ7);
                BPCQBKPM.DATA[BPCQBKPM.CNT-1].SEQ8 = (short) BPRBKPM.DAT_TXT.DATA[WS_CNT-1].SEQ8;
                CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].SEQ8);
                BPCQBKPM.DATA[BPCQBKPM.CNT-1].SEQ9 = (short) BPRBKPM.DAT_TXT.DATA[WS_CNT-1].SEQ9;
                CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].SEQ9);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (WS_CNT > 10) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_GL_BOOK_NOT_EXIST, BPCQBKPM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B050_BROWSE_AMBKP_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBKPM);
        IBS.init(SCCGWA, BPCPRMR);
        WS_J = 0;
        WS_J = 0;
        BPRBKPM.KEY.TYPE = "AMBKP";
        BPRBKPM.KEY.REDEFINES6.CNT = 10;
        BPRBKPM.KEY.CD = IBS.CLS2CPY(SCCGWA, BPRBKPM.KEY.REDEFINES6);
        BPCPRMR.FUNC = ' ';
        CEP.TRC(SCCGWA, BPCPRMR);
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRBKPM);
        CEP.TRC(SCCGWA, BPCQBKPM.COA_TYP);
        if (BPCQBKPM.COA_TYP.trim().length() == 0) {
            CEP.TRC(SCCGWA, "AAA");
            IBS.CPY2CLS(SCCGWA, BPCQBKPM.COA_TYP, WS_COA);
        } else {
            CEP.TRC(SCCGWA, "BBB");
            IBS.CPY2CLS(SCCGWA, BPCQBKPM.COA_TYP, WS_COA);
        }
        CEP.TRC(SCCGWA, WS_COA.WS_COA_1);
        CEP.TRC(SCCGWA, WS_COA.WS_COA_2);
        if (BPCQBKPM.COA_TYP.trim().length() == 0) {
            for (WS_CNT = 1; WS_CNT <= 10; WS_CNT += 1) {
                CEP.TRC(SCCGWA, BPRBKPM.DAT_TXT.DATA[WS_CNT-1].BOOK_FLG);
                CEP.TRC(SCCGWA, BPRBKPM.DAT_TXT.DATA[WS_CNT-1].STS);
                if (BPRBKPM.DAT_TXT.DATA[WS_CNT-1].BOOK_FLG.trim().length() > 0 
                    && BPRBKPM.DAT_TXT.DATA[WS_CNT-1].STS == 'A') {
                    CEP.TRC(SCCGWA, BPRBKPM.DAT_TXT.DATA[WS_CNT-1].COA_FLG);
                    CEP.TRC(SCCGWA, BPCQBKPM.COA_TYP);
                    WS_J = WS_J + 1;
                    BPCQBKPM.CNT = WS_J;
                    CEP.TRC(SCCGWA, BPCQBKPM.CNT);
                    BPCQBKPM.DATA[BPCQBKPM.CNT-1].BOOK_FLG = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].BOOK_FLG;
                    BPCQBKPM.DATA[BPCQBKPM.CNT-1].BOOK_TYP = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].BOOK_TYP;
                    BPCQBKPM.DATA[BPCQBKPM.CNT-1].COA_FLG = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].COA_FLG;
                    BPCQBKPM.DATA[BPCQBKPM.CNT-1].ITM_LEN = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].ITM_LEN;
                    BPCQBKPM.DATA[BPCQBKPM.CNT-1].STS = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].STS;
                    BPCQBKPM.DATA[BPCQBKPM.CNT-1].REAL_SUS_ITM = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].REAL_SUS_ITM;
                    BPCQBKPM.DATA[BPCQBKPM.CNT-1].MEMO_SUS_ITM = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].MEMO_SUS_ITM;
                    BPCQBKPM.DATA[BPCQBKPM.CNT-1].PL_ITM = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].PL_ITM;
                    BPCQBKPM.DATA[BPCQBKPM.CNT-1].CRS_BR_ITM = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].CRS_BR_ITM;
                    BPCQBKPM.DATA[BPCQBKPM.CNT-1].CRS_BR_ITM_M = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].CRS_BR_ITM_M;
                    BPCQBKPM.DATA[BPCQBKPM.CNT-1].SEQ1 = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].SEQ1;
                    BPCQBKPM.DATA[BPCQBKPM.CNT-1].SEQ2 = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].SEQ2;
                    BPCQBKPM.DATA[BPCQBKPM.CNT-1].SEQ3 = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].SEQ3;
                    BPCQBKPM.DATA[BPCQBKPM.CNT-1].SEQ4 = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].SEQ4;
                    BPCQBKPM.DATA[BPCQBKPM.CNT-1].SEQ5 = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].SEQ5;
                    BPCQBKPM.DATA[BPCQBKPM.CNT-1].LR_ITM = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].LR_ITM;
                    CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].LR_ITM);
                    BPCQBKPM.DATA[BPCQBKPM.CNT-1].JS_ITM = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].JS_ITM;
                    CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].JS_ITM);
                    BPCQBKPM.DATA[BPCQBKPM.CNT-1].DH_ITM = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].DH_ITM;
                    CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].DH_ITM);
                    BPCQBKPM.DATA[BPCQBKPM.CNT-1].WH_ITM = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].WH_ITM;
                    CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].WH_ITM);
                    BPCQBKPM.DATA[BPCQBKPM.CNT-1].FZ_DATE = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].FZ_DATE;
                    CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].FZ_DATE);
                    BPCQBKPM.DATA[BPCQBKPM.CNT-1].SEQ6 = (short) BPRBKPM.DAT_TXT.DATA[WS_CNT-1].SEQ6;
                    CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].SEQ6);
                    BPCQBKPM.DATA[BPCQBKPM.CNT-1].SEQ7 = (short) BPRBKPM.DAT_TXT.DATA[WS_CNT-1].SEQ7;
                    CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].SEQ7);
                    BPCQBKPM.DATA[BPCQBKPM.CNT-1].SEQ8 = (short) BPRBKPM.DAT_TXT.DATA[WS_CNT-1].SEQ8;
                    CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].SEQ8);
                    BPCQBKPM.DATA[BPCQBKPM.CNT-1].SEQ9 = (short) BPRBKPM.DAT_TXT.DATA[WS_CNT-1].SEQ9;
                    CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].SEQ9);
                }
            }
        } else {
            for (WS_CNT = 1; WS_CNT <= 10; WS_CNT += 1) {
                CEP.TRC(SCCGWA, BPRBKPM.DAT_TXT.DATA[WS_CNT-1].BOOK_FLG);
                if (BPRBKPM.DAT_TXT.DATA[WS_CNT-1].BOOK_FLG.trim().length() > 0) {
                    CEP.TRC(SCCGWA, BPRBKPM.DAT_TXT.DATA[WS_CNT-1].COA_FLG);
                    CEP.TRC(SCCGWA, BPCQBKPM.COA_TYP);
                    CEP.TRC(SCCGWA, BPRBKPM.DAT_TXT.DATA[WS_CNT-1].STS);
                    if (BPCQBKPM.COA_TYP.equalsIgnoreCase(BPRBKPM.DAT_TXT.DATA[WS_CNT-1].COA_FLG) 
                        && BPRBKPM.DAT_TXT.DATA[WS_CNT-1].STS == 'A') {
                        WS_J = WS_J + 1;
                        BPCQBKPM.CNT = WS_J;
                        CEP.TRC(SCCGWA, BPCQBKPM.CNT);
                        BPCQBKPM.DATA[BPCQBKPM.CNT-1].BOOK_FLG = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].BOOK_FLG;
                        BPCQBKPM.DATA[BPCQBKPM.CNT-1].BOOK_TYP = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].BOOK_TYP;
                        BPCQBKPM.DATA[BPCQBKPM.CNT-1].COA_FLG = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].COA_FLG;
                        BPCQBKPM.DATA[BPCQBKPM.CNT-1].ITM_LEN = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].ITM_LEN;
                        BPCQBKPM.DATA[BPCQBKPM.CNT-1].STS = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].STS;
                        BPCQBKPM.DATA[BPCQBKPM.CNT-1].REAL_SUS_ITM = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].REAL_SUS_ITM;
                        BPCQBKPM.DATA[BPCQBKPM.CNT-1].MEMO_SUS_ITM = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].MEMO_SUS_ITM;
                        BPCQBKPM.DATA[BPCQBKPM.CNT-1].PL_ITM = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].PL_ITM;
                        BPCQBKPM.DATA[BPCQBKPM.CNT-1].CRS_BR_ITM = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].CRS_BR_ITM;
                        BPCQBKPM.DATA[BPCQBKPM.CNT-1].CRS_BR_ITM_M = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].CRS_BR_ITM_M;
                        BPCQBKPM.DATA[BPCQBKPM.CNT-1].SEQ1 = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].SEQ1;
                        BPCQBKPM.DATA[BPCQBKPM.CNT-1].SEQ2 = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].SEQ2;
                        BPCQBKPM.DATA[BPCQBKPM.CNT-1].SEQ3 = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].SEQ3;
                        BPCQBKPM.DATA[BPCQBKPM.CNT-1].SEQ4 = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].SEQ4;
                        BPCQBKPM.DATA[BPCQBKPM.CNT-1].SEQ5 = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].SEQ5;
                        BPCQBKPM.DATA[BPCQBKPM.CNT-1].LR_ITM = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].LR_ITM;
                        CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].LR_ITM);
                        BPCQBKPM.DATA[BPCQBKPM.CNT-1].JS_ITM = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].JS_ITM;
                        CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].JS_ITM);
                        BPCQBKPM.DATA[BPCQBKPM.CNT-1].DH_ITM = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].DH_ITM;
                        CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].DH_ITM);
                        BPCQBKPM.DATA[BPCQBKPM.CNT-1].WH_ITM = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].WH_ITM;
                        CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].WH_ITM);
                        BPCQBKPM.DATA[BPCQBKPM.CNT-1].FZ_DATE = BPRBKPM.DAT_TXT.DATA[WS_CNT-1].FZ_DATE;
                        CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].FZ_DATE);
                        BPCQBKPM.DATA[BPCQBKPM.CNT-1].SEQ6 = (short) BPRBKPM.DAT_TXT.DATA[WS_CNT-1].SEQ6;
                        CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].SEQ6);
                        BPCQBKPM.DATA[BPCQBKPM.CNT-1].SEQ7 = (short) BPRBKPM.DAT_TXT.DATA[WS_CNT-1].SEQ7;
                        CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].SEQ7);
                        BPCQBKPM.DATA[BPCQBKPM.CNT-1].SEQ8 = (short) BPRBKPM.DAT_TXT.DATA[WS_CNT-1].SEQ8;
                        CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].SEQ8);
                        BPCQBKPM.DATA[BPCQBKPM.CNT-1].SEQ9 = (short) BPRBKPM.DAT_TXT.DATA[WS_CNT-1].SEQ9;
                        CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].SEQ9);
                    }
                }
            }
        }
        CEP.TRC(SCCGWA, BPCQBKPM.CNT);
        CEP.TRC(SCCGWA, BPCQBKPM.COA_TYP);
        if (BPCQBKPM.CNT == 0) {
            if (BPCQBKPM.COA_TYP.trim().length() == 0) {
                CEP.TRC(SCCGWA, "QBKPM-COA-TYP INPUT");
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.M_COA_TYPE_NO_EXIST, BPCQBKPM.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                CEP.TRC(SCCGWA, "QBKPM-COA-TYP NOT INPUT");
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_GL_BOOK_NOT_EXIST, BPCQBKPM.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_OUTPUT_DATA() throws IOException,SQLException,Exception {
        BPCQBKPM.DATA[BPCQBKPM.CNT-1].BOOK_FLG = WS_KEY.WS_BOOK_FLG;
        CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].BOOK_FLG);
        BPCQBKPM.DATA[BPCQBKPM.CNT-1].BOOK_TYP = WS_DATA.WS_BOOK_TYP;
        CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].BOOK_TYP);
        BPCQBKPM.DATA[BPCQBKPM.CNT-1].COA_FLG = WS_DATA.WS_COA_FLG;
        CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].COA_FLG);
        BPCQBKPM.DATA[BPCQBKPM.CNT-1].ITM_LEN = WS_DATA.WS_ITM_LEN;
        CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].ITM_LEN);
        BPCQBKPM.DATA[BPCQBKPM.CNT-1].STS = WS_DATA.WS_STS;
        CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].STS);
        BPCQBKPM.DATA[BPCQBKPM.CNT-1].REAL_SUS_ITM = WS_DATA.WS_REAL_SUS_ITM;
        CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].REAL_SUS_ITM);
        BPCQBKPM.DATA[BPCQBKPM.CNT-1].MEMO_SUS_ITM = WS_DATA.WS_MEMO_SUS_ITM;
        CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].MEMO_SUS_ITM);
        BPCQBKPM.DATA[BPCQBKPM.CNT-1].PL_ITM = WS_DATA.WS_PL_ITM;
        CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].PL_ITM);
        BPCQBKPM.DATA[BPCQBKPM.CNT-1].CRS_BR_ITM = WS_DATA.WS_CRS_BR_ITM;
        CEP.TRC(SCCGWA, BPCQBKPM.DATA[BPCQBKPM.CNT-1].CRS_BR_ITM);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        //BPRBKPM.DAT_LEN = 2000;
        //CEP.TRC(SCCGWA, BPRBKPM.DAT_LEN);
        BPCPRMR.DAT_PTR = BPRBKPM;
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        CEP.TRC(SCCGWA, BPCPRMR.RC);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_GL_BOOK_NOT_EXIST, BPCQBKPM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMB() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRPARM.BLOB_VAL.trim().length());
        IBS.CALLCPN(SCCGWA, "BP-PARM-BROWSE", BPCPRMB);
        CEP.TRC(SCCGWA, BPCPRMB.RC);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCQBKPM.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, " BPCQBKPM = ");
            CEP.TRC(SCCGWA, BPCQBKPM);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}