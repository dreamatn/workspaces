package com.hisun.AI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class AIZPQIA {
    boolean pgmRtn = false;
    char K_ACTIVE = 'A';
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    int WS_BOOK_CNT = 0;
    String WS_GLBOOK = " ";
    char WS_GLBOOK_FLG = ' ';
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AICUIANO AICUIANO = new AICUIANO();
    AIRPAI7 AIRPAI7 = new AIRPAI7();
    AICPQMIB AICPQMIB = new AICPQMIB();
    BPCQBKPM BPCQBKPM = new BPCQBKPM();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AICPQIA AICPQIA;
    public void MP(SCCGWA SCCGWA, AICPQIA AICPQIA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICPQIA = AICPQIA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZPQIA return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, AICPQIA.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHK_IPT();
        if (pgmRtn) return;
        B020_GET_IANO();
        if (pgmRtn) return;
    }
    public void B010_CHK_IPT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AICPQIA.AC);
        CEP.TRC(SCCGWA, AICPQIA.CCY);
        CEP.TRC(SCCGWA, AICPQIA.CD.AC_TYP);
        CEP.TRC(SCCGWA, AICPQIA.CD.BUSI_KND);
        CEP.TRC(SCCGWA, AICPQIA.CD.JY_TYP);
        CEP.TRC(SCCGWA, AICPQIA.BR);
        if (AICPQIA.CD.AC_TYP.trim().length() == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_ITMP_GL_ITEM_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (AICPQIA.CD.BUSI_KND.trim().length() == 0) {
            CEP.TRC(SCCGWA, "121111");
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_PAI7_CODE_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (AICPQIA.BR == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_BR_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = AICPQIA.BR;
            S00_CALL_BPZPQORG();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, AICPQIA.BR);
        }
        if (AICPQIA.CCY.trim().length() == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_CUR_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, BPCQCCY);
            BPCQCCY.DATA.CCY = AICPQIA.CCY;
            S000_CALL_BPZQCCY();
            if (pgmRtn) return;
        }
        if ((AICPQIA.SIGN != 'D' 
            && AICPQIA.SIGN != 'C')) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_DCFLG_NOT_VAL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_IANO() throws IOException,SQLException,Exception {
        R000_GET_ALL_BOOK();
        if (pgmRtn) return;
        IBS.init(SCCGWA, AIRPAI7);
        IBS.init(SCCGWA, BPCPRMR);
        AIRPAI7.KEY.TYP = "PAI07";
        AIRPAI7.KEY.REDEFINES6.AC_TYP = AICPQIA.CD.AC_TYP;
        AIRPAI7.KEY.CD = IBS.CLS2CPY(SCCGWA, AIRPAI7.KEY.REDEFINES6);
        AIRPAI7.KEY.REDEFINES6.BUSI_KND = AICPQIA.CD.BUSI_KND;
        AIRPAI7.KEY.CD = IBS.CLS2CPY(SCCGWA, AIRPAI7.KEY.REDEFINES6);
        AIRPAI7.KEY.REDEFINES6.TR_TYP = AICPQIA.CD.JY_TYP;
        AIRPAI7.KEY.CD = IBS.CLS2CPY(SCCGWA, AIRPAI7.KEY.REDEFINES6);
        AIRPAI7.KEY.REDEFINES6.GL_BOOK = WS_GLBOOK;
        AIRPAI7.KEY.CD = IBS.CLS2CPY(SCCGWA, AIRPAI7.KEY.REDEFINES6);
        CEP.TRC(SCCGWA, AICPQIA.CD.AC_TYP);
        CEP.TRC(SCCGWA, AICPQIA.CD.BUSI_KND);
        CEP.TRC(SCCGWA, AICPQIA.CD.JY_TYP);
        CEP.TRC(SCCGWA, WS_GLBOOK);
        BPCPRMR.DAT_PTR = AIRPAI7;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        IBS.init(SCCGWA, AICUIANO);
        AICUIANO.INPUT_DATA.GL_BOOK = AIRPAI7.KEY.REDEFINES6.GL_BOOK;
        AICUIANO.INPUT_DATA.BR = AICPQIA.BR;
        CEP.TRC(SCCGWA, "DDDTEST1");
        CEP.TRC(SCCGWA, AICPQIA.BR);
        if (AICPQIA.SIGN == 'C') {
            AICUIANO.INPUT_DATA.ITM = AIRPAI7.DATA_TXT.DATA_INF.ITM_R_C;
            AICUIANO.INPUT_DATA.SEQ = AIRPAI7.DATA_TXT.DATA_INF.SEQ_R_C;
        } else {
            AICUIANO.INPUT_DATA.ITM = AIRPAI7.DATA_TXT.DATA_INF.ITM_R_D;
            AICUIANO.INPUT_DATA.SEQ = AIRPAI7.DATA_TXT.DATA_INF.SEQ_R_D;
        }
        AICUIANO.INPUT_DATA.CCY = AICPQIA.CCY;
        CEP.TRC(SCCGWA, "DDDTEST");
        CEP.TRC(SCCGWA, AICUIANO.INPUT_DATA.BR);
        S000_CALL_AIZUIANO();
        if (pgmRtn) return;
        IBS.init(SCCGWA, AICPQMIB);
        AICPQMIB.INPUT_DATA.GL_BOOK = AICUIANO.INPUT_DATA.GL_BOOK;
        AICPQMIB.INPUT_DATA.BR = AICUIANO.INPUT_DATA.BR;
        AICPQMIB.INPUT_DATA.ITM_NO = AICUIANO.INPUT_DATA.ITM;
        AICPQMIB.INPUT_DATA.SEQ = AICUIANO.INPUT_DATA.SEQ;
        AICPQMIB.INPUT_DATA.AC = AICPQIA.AC;
        AICPQMIB.INPUT_DATA.CCY = AICPQIA.CCY;
        S000_CALL_AIZPQMIB();
        if (pgmRtn) return;
        AICPQIA.AC = AICUIANO.INPUT_DATA.AC;
        AICPQIA.CHS_NM = AICPQMIB.OUTPUT_DATA.CHS_NM;
        CEP.TRC(SCCGWA, AICPQIA.CHS_NM);
        CEP.TRC(SCCGWA, AICUIANO.INPUT_DATA.AC);
    }
    public void R000_GET_ALL_BOOK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQBKPM);
        WS_GLBOOK_FLG = ' ';
        BPCQBKPM.FUNC = 'B';
        S000_CALL_BPZQBKPM();
        if (pgmRtn) return;
        for (WS_BOOK_CNT = 1; WS_BOOK_CNT <= 5 
            && WS_GLBOOK_FLG != 'Y'; WS_BOOK_CNT += 1) {
            if (BPCQBKPM.DATA[WS_BOOK_CNT-1].STS == K_ACTIVE) {
                WS_GLBOOK = BPCQBKPM.DATA[WS_BOOK_CNT-1].BOOK_FLG;
                CEP.TRC(SCCGWA, WS_GLBOOK);
                WS_GLBOOK_FLG = 'Y';
            }
        }
    }
    public void S000_CALL_AIZUIANO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-GEN-IANO", AICUIANO);
        if (AICUIANO.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICUIANO.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB);
        if (AICPQMIB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPQMIB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S00_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZQBKPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-Q-MAINT-AMBKP", BPCQBKPM);
        CEP.TRC(SCCGWA, BPCQBKPM.RC.RC_RTNCODE);
        if (BPCQBKPM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQBKPM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, WS_ERR_MSG, AICPQIA.RC);
        Z_RET();
        if (pgmRtn) return;
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (AICPQIA.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "AICPQIA = ");
            CEP.TRC(SCCGWA, AICPQIA);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
