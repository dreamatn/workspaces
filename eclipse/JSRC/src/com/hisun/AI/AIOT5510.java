package com.hisun.AI;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class AIOT5510 {
    boolean pgmRtn = false;
    String CPN_S_BRW_HMIB = "AI-S-BRW-HMIB  ";
    String CPN_P_INQ_ORG_LOW = "BP-P-INQ-ORG-LOW";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_LAST_BR = 0;
    int WS_CNT = 0;
    int WS_CNT1 = 0;
    char WS_BR_FLG = ' ';
    char WS_DTL_FLG = ' ';
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    AICSHMIB AICSHMIB = new AICSHMIB();
    AICUIANO AICUIANO = new AICUIANO();
    AIRHMIB AIRHMIB = new AIRHMIB();
    BPCPORLO BPCPORLO = new BPCPORLO();
    AIRMIB AIRMIB = new AIRMIB();
    AICRMIB AICRMIB = new AICRMIB();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AIB5510_AWA_5510 AIB5510_AWA_5510;
    BPCPORUP_DATA_INFO BPCPORUP;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, "PROGRAM AIOT5510 PROCESS START!");
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "PROGRAM AIOT5510 END SUCCESSFULLY!");
        CEP.TRC(SCCGWA, "AIOT5510 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB5510_AWA_5510>");
        AIB5510_AWA_5510 = (AIB5510_AWA_5510) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B015_CHECK_MIB();
        if (pgmRtn) return;
        B020_BRW_REC_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIB5510_AWA_5510.GL_BOOK);
        CEP.TRC(SCCGWA, AIB5510_AWA_5510.AC);
        CEP.TRC(SCCGWA, AIB5510_AWA_5510.STR_DT);
        CEP.TRC(SCCGWA, AIB5510_AWA_5510.END_DT);
        CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.BR);
        CEP.TRC(SCCGWA, AIB5510_AWA_5510.BR);
        if (AIB5510_AWA_5510.GL_BOOK.trim().length() == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, AIB5510_AWA_5510.END_DT);
        if (AIB5510_AWA_5510.END_DT != 0 
            && AIB5510_AWA_5510.END_DT < AIB5510_AWA_5510.STR_DT) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.END_DT_LESS_STRDT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
    }
    public void B015_CHECK_MIB() throws IOException,SQLException,Exception {
        WS_DTL_FLG = ' ';
        IBS.init(SCCGWA, AIRMIB);
        IBS.init(SCCGWA, AICRMIB);
        AICRMIB.FUNC = 'Q';
        AIRMIB.KEY.ITM_NO = AIB5510_AWA_5510.ITM;
        AIRMIB.KEY.BR = AIB5510_AWA_5510.BR;
        AIRMIB.KEY.SEQ = AIB5510_AWA_5510.SEQ;
        AIRMIB.KEY.CCY = AIB5510_AWA_5510.CCY;
        CEP.TRC(SCCGWA, AIB5510_AWA_5510.ITM);
        CEP.TRC(SCCGWA, AIB5510_AWA_5510.BR);
        CEP.TRC(SCCGWA, AIB5510_AWA_5510.SEQ);
        CEP.TRC(SCCGWA, AIB5510_AWA_5510.CCY);
        AIRMIB.KEY.GL_BOOK = AIB5510_AWA_5510.GL_BOOK;
        AICRMIB.POINTER = AIRMIB;
        AICRMIB.REC_LEN = 634;
        S000_CALL_AIZRMIB();
        if (pgmRtn) return;
        if (AICRMIB.RETURN_INFO == 'F') {
            if (AIRMIB.DTL_FLG == 'N') {
                WS_DTL_FLG = 'N';
            }
        } else {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_AITMIB_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_BRW_REC_PROC() throws IOException,SQLException,Exception {
        if (AIRMIB.DTL_FLG == 'N') {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_AITHMIB_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, AICSHMIB);
            AICSHMIB.FUNC = 'B';
            AICSHMIB.GL_BOOK = AIB5510_AWA_5510.GL_BOOK;
            AICSHMIB.BR = AIB5510_AWA_5510.BR;
            AICSHMIB.CCY = AIB5510_AWA_5510.CCY;
            AICSHMIB.ITM = AIB5510_AWA_5510.ITM;
            AICSHMIB.SEQ = AIB5510_AWA_5510.SEQ;
            IBS.init(SCCGWA, AICUIANO);
            AICUIANO.INPUT_DATA.GL_BOOK = AIB5510_AWA_5510.GL_BOOK;
            AICUIANO.INPUT_DATA.BR = AIB5510_AWA_5510.BR;
            AICUIANO.INPUT_DATA.CCY = AIB5510_AWA_5510.CCY;
            AICUIANO.INPUT_DATA.ITM = AIB5510_AWA_5510.ITM;
            AICUIANO.INPUT_DATA.SEQ = AIB5510_AWA_5510.SEQ;
            S000_CALL_AIZUIANO();
            if (pgmRtn) return;
            AICSHMIB.CCY = AICUIANO.INPUT_DATA.CCY;
            CEP.TRC(SCCGWA, AICSHMIB.CCY);
            CEP.TRC(SCCGWA, AIB5510_AWA_5510.AC);
            AICSHMIB.STR_DT = AIB5510_AWA_5510.STR_DT;
            AICSHMIB.END_DT = AIB5510_AWA_5510.END_DT;
            AICSHMIB.SET_NO = AIB5510_AWA_5510.SET_NO;
            AICSHMIB.SET_SEQ = AIB5510_AWA_5510.SET_SEQ;
            CEP.TRC(SCCGWA, AIB5510_AWA_5510.SIGN);
            CEP.TRC(SCCGWA, AIB5510_AWA_5510.STR_AMT);
            CEP.TRC(SCCGWA, AIB5510_AWA_5510.END_AMT);
            AICSHMIB.SIGN = AIB5510_AWA_5510.SIGN;
            AICSHMIB.STR_AMT = AIB5510_AWA_5510.STR_AMT;
            AICSHMIB.END_AMT = AIB5510_AWA_5510.END_AMT;
            S000_CALL_AIZSHMIB();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZSHMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BRW_HMIB, AICSHMIB);
        if (AICSHMIB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICSHMIB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZRMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-R-MAIN-MIB", AICRMIB);
        if (AICRMIB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICRMIB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_CALL_AIZUIANO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-GEN-IANO", AICUIANO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
