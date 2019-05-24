package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPTFGL {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_BP_MMO = "BP";
    String K_MOV_SEQ_TYPE = "SEQ";
    String K_MOV_SEQ_NAME = "FEEGLMST";
    int K_EXP_DATE = 99991231;
    short K_BOOK_TYPE_1 = 1;
    short K_BOOK_TYPE_2 = 2;
    short K_BOOK_TYPE_3 = 3;
    short K_BOOK_TYPE_4 = 4;
    short K_BOOK_TYPE_5 = 5;
    String K_FEE_REMARK = "FEE ITEM";
    String WS_ERR_MSG = " ";
    String WS_COA_FLG = " ";
    String WS_ITEM_CODE = " ";
    int WS_GL_MASTER = 0;
    String[] WS_BOOK_COA = new String[5];
    short WS_IDX = 0;
    short WS_IDX2 = 0;
    char WS_GLM_FLG = ' ';
    char[] WS_BOOK_TYPE_FLG = new char[5];
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    BPCPQPDM BPCPQPDM = new BPCPQPDM();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCPRMB BPCPRMB = new BPCPRMB();
    BPCQBKPM BPCQBKPM = new BPCQBKPM();
    BPRGLM BPRGLM = new BPRGLM();
    BPCPITM BPCPITM = new BPCPITM();
    SCCGWA SCCGWA;
    BPCPTFGL BPCPTFGL;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCPTFGL BPCPTFGL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPTFGL = BPCPTFGL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPTFGL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
        BPCPTFGL.RC.RC_MMO = K_BP_MMO;
        BPCPTFGL.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPTFGL);
        B010_CHECK_PROC();
        if (pgmRtn) return;
        B020_TRANS_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_PROC() throws IOException,SQLException,Exception {
        for (WS_IDX = 1; WS_IDX <= 4; WS_IDX += 1) {
            if ((!BPCPTFGL.INPUT_DATA.ITEM_NO[WS_IDX-1].equalsIgnoreCase("0")) 
                && (BPCPTFGL.INPUT_DATA.MST_NO[WS_IDX-1] != 0)) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_ITEM_GLMST_INPUT, BPCPTFGL.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (BPCPTFGL.INPUT_DATA.CNTR_TYPE1.trim().length() > 0 
            && BPCPTFGL.INPUT_DATA.CNTR_TYPE1.charAt(0) != 0X00) {
            IBS.init(SCCGWA, BPCPQPDM);
            BPCPQPDM.PRDT_MODEL = BPCPTFGL.INPUT_DATA.CNTR_TYPE1;
            S000_CALL_BPZPQPDM();
            if (pgmRtn) return;
        }
        if (BPCPTFGL.INPUT_DATA.CNTR_TYPE2.trim().length() > 0 
            && BPCPTFGL.INPUT_DATA.CNTR_TYPE2.charAt(0) != 0X00) {
            IBS.init(SCCGWA, BPCPQPDM);
            BPCPQPDM.PRDT_MODEL = BPCPTFGL.INPUT_DATA.CNTR_TYPE2;
            S000_CALL_BPZPQPDM();
            if (pgmRtn) return;
        }
        if (BPCPTFGL.INPUT_DATA.CNTR_TYPE3.trim().length() > 0 
            && BPCPTFGL.INPUT_DATA.CNTR_TYPE3.charAt(0) != 0X00) {
            IBS.init(SCCGWA, BPCPQPDM);
            BPCPQPDM.PRDT_MODEL = BPCPTFGL.INPUT_DATA.CNTR_TYPE3;
            S000_CALL_BPZPQPDM();
            if (pgmRtn) return;
        }
    }
    public void B020_TRANS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQBKPM);
        for (WS_IDX = 1; WS_IDX <= 5; WS_IDX += 1) {
            WS_BOOK_TYPE_FLG[WS_IDX-1] = 'N';
        }
        BPCQBKPM.FUNC = 'B';
        S000_CALL_BPZQBKPM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCQBKPM);
        for (WS_IDX = 1; WS_IDX <= 10; WS_IDX += 1) {
            if (BPCQBKPM.DATA[WS_IDX-1].BOOK_TYP == K_BOOK_TYPE_1 
                && WS_BOOK_TYPE_FLG[1-1] == 'N') {
                WS_BOOK_COA[1-1] = BPCQBKPM.DATA[WS_IDX-1].COA_FLG;
                WS_BOOK_TYPE_FLG[1-1] = 'Y';
            }
            if (BPCQBKPM.DATA[WS_IDX-1].BOOK_TYP == K_BOOK_TYPE_2 
                && WS_BOOK_TYPE_FLG[2-1] == 'N') {
                WS_BOOK_COA[2-1] = BPCQBKPM.DATA[WS_IDX-1].COA_FLG;
                WS_BOOK_TYPE_FLG[2-1] = 'Y';
            }
            if (BPCQBKPM.DATA[WS_IDX-1].BOOK_TYP == K_BOOK_TYPE_3 
                && WS_BOOK_TYPE_FLG[3-1] == 'N') {
                WS_BOOK_COA[3-1] = BPCQBKPM.DATA[WS_IDX-1].COA_FLG;
                WS_BOOK_TYPE_FLG[3-1] = 'Y';
            }
            if (BPCQBKPM.DATA[WS_IDX-1].BOOK_TYP == K_BOOK_TYPE_4 
                && WS_BOOK_TYPE_FLG[4-1] == 'N') {
                WS_BOOK_COA[4-1] = BPCQBKPM.DATA[WS_IDX-1].COA_FLG;
                WS_BOOK_TYPE_FLG[4-1] = 'Y';
            }
            if (BPCQBKPM.DATA[WS_IDX-1].BOOK_TYP == K_BOOK_TYPE_5 
                && WS_BOOK_TYPE_FLG[5-1] == 'N') {
                WS_BOOK_COA[5-1] = BPCQBKPM.DATA[WS_IDX-1].COA_FLG;
                WS_BOOK_TYPE_FLG[5-1] = 'Y';
            }
        }
        CEP.TRC(SCCGWA, WS_BOOK_COA[1-1]);
        CEP.TRC(SCCGWA, WS_BOOK_COA[2-1]);
        CEP.TRC(SCCGWA, WS_BOOK_COA[3-1]);
        CEP.TRC(SCCGWA, WS_BOOK_COA[4-1]);
        CEP.TRC(SCCGWA, WS_BOOK_COA[5-1]);
        for (WS_IDX = 1; WS_IDX <= 4; WS_IDX += 1) {
            if (!BPCPTFGL.INPUT_DATA.ITEM_NO[WS_IDX-1].equalsIgnoreCase("0")) {
                WS_COA_FLG = WS_BOOK_COA[WS_IDX-1];
                WS_ITEM_CODE = BPCPTFGL.INPUT_DATA.ITEM_NO[WS_IDX-1];
                B021_BROWSE_GL_MASTER();
                if (pgmRtn) return;
                BPCPTFGL.INPUT_DATA.MST_NO[WS_IDX-1] = WS_GL_MASTER;
            }
            if (BPCPTFGL.INPUT_DATA.ITEM_NO[WS_IDX-1].equalsIgnoreCase("0") 
                && BPCPTFGL.INPUT_DATA.MST_NO[WS_IDX-1] != 0) {
                WS_GL_MASTER = BPCPTFGL.INPUT_DATA.MST_NO[WS_IDX-1];
                B022_INQ_ITEM_CODE();
                if (pgmRtn) return;
                BPCPTFGL.INPUT_DATA.ITEM_NO[WS_IDX-1] = WS_ITEM_CODE;
            }
        }
    }
    public void B021_BROWSE_GL_MASTER() throws IOException,SQLException,Exception {
        R000_CHECK_ITEM_CODE();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPRMB);
        IBS.init(SCCGWA, BPRGLM);
        BPRGLM.KEY.TYP = "AMGLM";
        BPCPRMB.FUNC = '0';
        BPCPRMB.DAT_PTR = BPRGLM;
        S000_CALL_BPZPRMB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_COA_FLG);
        WS_GLM_FLG = 'N';
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMB.RC);
        while (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND) 
            && WS_GLM_FLG != 'Y') {
            BPCPRMB.FUNC = '1';
            S000_CALL_BPZPRMB();
            if (pgmRtn) return;
            if (BPRGLM.DATA_TXT.OPT_FLG == 'N') {
                for (WS_IDX2 = 1; WS_IDX2 <= 32 
                    && WS_GLM_FLG != 'Y'; WS_IDX2 += 1) {
                    if (WS_ITEM_CODE.equalsIgnoreCase(BPRGLM.DATA_TXT.REL_ITMS[WS_IDX2-1].ITM_NO)) {
                        WS_GLM_FLG = 'Y';
                        WS_GL_MASTER = BPRGLM.KEY.REDEFINES6.MSTNO;
                    }
                }
            }
        }
        BPCPRMB.FUNC = '2';
        S000_CALL_BPZPRMB();
        if (pgmRtn) return;
        if (WS_GLM_FLG == 'N') {
            R000_GEN_GL_MASTER();
            if (pgmRtn) return;
            R000_WRITE_GL_MASTER();
            if (pgmRtn) return;
        }
    }
    public void B022_INQ_ITEM_CODE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRGLM);
        BPRGLM.KEY.TYP = "AMGLM";
        BPRGLM.KEY.REDEFINES6.MSTNO = WS_GL_MASTER;
        BPRGLM.KEY.CD = IBS.CLS2CPY(SCCGWA, BPRGLM.KEY.REDEFINES6);
        IBS.init(SCCGWA, BPCPRMM);
        BPCPRMM.FUNC = '3';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        if (BPRGLM.DATA_TXT.OPT_FLG != 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_GLM_NO_ITEM, BPCPTFGL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_ITEM_CODE = BPRGLM.DATA_TXT.REL_ITMS[1-1].ITM_NO;
    }
    public void R000_GEN_GL_MASTER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSGSEQ);
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSGSEQ.RUN_MODE = 'O';
        BPCSGSEQ.TYPE = K_MOV_SEQ_TYPE;
        BPCSGSEQ.CODE = K_MOV_SEQ_NAME;
        S000_CALL_BPZSGSEQ();
        if (pgmRtn) return;
        WS_GL_MASTER = (int) BPCSGSEQ.SEQ;
    }
    public void R000_WRITE_GL_MASTER() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_COA_FLG);
        IBS.init(SCCGWA, BPRGLM);
        BPRGLM.KEY.TYP = "AMGLM";
        BPRGLM.KEY.REDEFINES6.MSTNO = WS_GL_MASTER;
        BPRGLM.KEY.CD = IBS.CLS2CPY(SCCGWA, BPRGLM.KEY.REDEFINES6);
        BPRGLM.DATA_TXT.SNAME = " ";
        BPRGLM.DATA_TXT.LNAME = " ";
        BPRGLM.DATA_TXT.EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRGLM.DATA_TXT.EXP_DATE = K_EXP_DATE;
        BPRGLM.DATA_TXT.OPT_FLG = 'N';
        BPRGLM.DATA_TXT.CNTY1 = BPCPTFGL.INPUT_DATA.CNTR_TYPE1;
        BPRGLM.DATA_TXT.CNTY2 = BPCPTFGL.INPUT_DATA.CNTR_TYPE2;
        BPRGLM.DATA_TXT.CNTY3 = BPCPTFGL.INPUT_DATA.CNTR_TYPE3;
        BPRGLM.DATA_TXT.CKFLG = 'N';
        BPRGLM.DATA_TXT.REL_ITMS[1-1].ITM_NO = WS_ITEM_CODE;
        BPRGLM.DATA_TXT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRGLM.DATA_TXT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRGLM.DATA_TXT.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        BPRGLM.DATA_TXT.SUP_TEL1 = SCCGWA.COMM_AREA.SUP1_ID;
        BPRGLM.DATA_TXT.SUP_TEL2 = SCCGWA.COMM_AREA.SUP2_ID;
        BPRGLM.DATA_LEN = 4805;
        IBS.init(SCCGWA, BPCPRMM);
        BPCPRMM.FUNC = '0';
        BPCPRMM.EXP_DT = K_EXP_DATE;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void R000_CHECK_ITEM_CODE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPITM);
        BPCPITM.INPUT_DATA.COA_FLG = WS_COA_FLG;
        BPCPITM.INPUT_DATA.NO = WS_ITEM_CODE;
        IBS.CALLCPN(SCCGWA, "BP-P-CHK-ITM", BPCPITM);
        if (BPCPITM.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPITM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPTFGL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZQBKPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-Q-MAINT-AMBKP", BPCQBKPM);
        if (BPCQBKPM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQBKPM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPTFGL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-GET-SEQ    ", BPCSGSEQ);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPTFGL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQPDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-PRD-MODEL", BPCPQPDM);
        CEP.TRC(SCCGWA, "PPPPPPPPPPPPPPPPPPPPPP:");
        CEP.TRC(SCCGWA, BPCPQPDM.RC);
        if (BPCPQPDM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPDM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPTFGL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-BROWSE", BPCPRMB);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCPRMB.RC);
        if (BPCPRMB.RC.RC_RTNCODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMB.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPTFGL.RC);
        }
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        BPCPRMM.DAT_PTR = BPRGLM;
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPTFGL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPTFGL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPTFGL = ");
            CEP.TRC(SCCGWA, BPCPTFGL);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
