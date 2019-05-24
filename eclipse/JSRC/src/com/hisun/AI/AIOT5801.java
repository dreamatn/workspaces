package com.hisun.AI;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class AIOT5801 {
    String CPN_R_CMIB_MAIN = "AI-R-MAIN-CMIB  ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    AIOT5801_WS_OUTPUT WS_OUTPUT = new AIOT5801_WS_OUTPUT();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AICPQITM AICPQITM = new AICPQITM();
    AICPCMIB AICPCMIB = new AICPCMIB();
    BPCPQORG BPCPQORG = new BPCPQORG();
    AIRCMIB AIRCMIB = new AIRCMIB();
    AICRCMIB AICRCMIB = new AICRCMIB();
    AIRMIB AIRMIB = new AIRMIB();
    AICRMIB AICRMIB = new AICRMIB();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AIB5800_AWA_5800 AIB5800_AWA_5800;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "AIOT5801 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB5800_AWA_5800>");
        AIB5800_AWA_5800 = (AIB5800_AWA_5800) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_QUE_REC_PROC();
        R000_DATA_OUTPUT();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (AIB5800_AWA_5800.SEQ == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_SEQ_NOT_ZERO;
            S000_ERR_MSG_PROC();
        }
        if (AIB5800_AWA_5800.GL_BOOK.trim().length() == 0 
            || AIB5800_AWA_5800.BR == 0 
            || AIB5800_AWA_5800.ITM.equalsIgnoreCase("0") 
            || AIB5800_AWA_5800.SEQ == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, AICPQITM);
        AICPQITM.INPUT_DATA.NO = AIB5800_AWA_5800.ITM;
        AICPQITM.INPUT_DATA.BOOK_FLG = AIB5800_AWA_5800.GL_BOOK;
        S000_CALL_AIZPQITM();
        if (AIB5800_AWA_5800.FUNC_N != 'C' 
            && AIB5800_AWA_5800.FUNC_N != 'R') {
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_IPT_FUNC_ERR);
        }
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.GL_BOOK);
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.BR);
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.ITM);
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.SEQ);
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.CCY);
    }
    public void B020_QUE_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRCMIB);
        IBS.init(SCCGWA, AICPCMIB);
        AIRCMIB.KEY.GL_BOOK = AIB5800_AWA_5800.GL_BOOK;
        AIRCMIB.KEY.BR = AIB5800_AWA_5800.BR;
        AIRCMIB.KEY.ITM = AIB5800_AWA_5800.ITM;
        AIRCMIB.KEY.SEQ = AIB5800_AWA_5800.SEQ;
        AICRCMIB.FUNC = 'Q';
        AICPCMIB.POINTER = AIRCMIB;
        AICPCMIB.REC_LEN = 407;
        S000_CALL_AIZPCMIB();
        CEP.TRC(SCCGWA, AIRCMIB.STS);
        if (AICPCMIB.RETURN_INFO == 'N' 
            && AIB5800_AWA_5800.FUNC_N == 'C') {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPCMIB.RC);
            S000_ERR_MSG_PROC();
        } else {
            if (AICPCMIB.RETURN_INFO == 'N' 
                && AIB5800_AWA_5800.FUNC_N == 'R') {
                CEP.TRC(SCCGWA, "QUERY CMIB WITH SEQ = 999999");
                IBS.init(SCCGWA, AIRCMIB);
                IBS.init(SCCGWA, AICPCMIB);
                AIRCMIB.KEY.GL_BOOK = AIB5800_AWA_5800.GL_BOOK;
                AIRCMIB.KEY.BR = AIB5800_AWA_5800.BR;
                AIRCMIB.KEY.ITM = AIB5800_AWA_5800.ITM;
                AIRCMIB.KEY.SEQ = 999999;
                AICRCMIB.FUNC = 'Q';
                AICPCMIB.POINTER = AIRCMIB;
                AICPCMIB.REC_LEN = 407;
                S000_CALL_AIZPCMIB();
                if (AICPCMIB.RETURN_INFO == 'N') {
                    WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPCMIB.RC);
                    S000_ERR_MSG_PROC();
                }
                CEP.TRC(SCCGWA, AIRCMIB.STS);
                IBS.init(SCCGWA, AIRMIB);
                IBS.init(SCCGWA, AICRMIB);
                AICRMIB.FUNC = 'Q';
                AIRMIB.KEY.GL_BOOK = AIB5800_AWA_5800.GL_BOOK;
                AIRMIB.KEY.ITM_NO = AIB5800_AWA_5800.ITM;
                AIRMIB.KEY.BR = AIB5800_AWA_5800.BR;
                AIRMIB.KEY.SEQ = AIB5800_AWA_5800.SEQ;
                AIRMIB.KEY.CCY = AIB5800_AWA_5800.CCY;
                AICRMIB.POINTER = AIRMIB;
                AICRMIB.REC_LEN = 634;
                S000_CALL_AIZRMIB();
                if (AICRMIB.RETURN_INFO == 'N') {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_AITMIB_NOTFND;
                    S000_ERR_MSG_PROC();
                }
            }
            if (AIB5800_AWA_5800.FUNC_N == 'R') {
                IBS.init(SCCGWA, AIRMIB);
                IBS.init(SCCGWA, AICRMIB);
                AICRMIB.FUNC = 'Q';
                AIRMIB.KEY.GL_BOOK = AIB5800_AWA_5800.GL_BOOK;
                AIRMIB.KEY.ITM_NO = AIB5800_AWA_5800.ITM;
                AIRMIB.KEY.BR = AIB5800_AWA_5800.BR;
                AIRMIB.KEY.SEQ = AIB5800_AWA_5800.SEQ;
                AIRMIB.KEY.CCY = AIB5800_AWA_5800.CCY;
                AICRMIB.POINTER = AIRMIB;
                AICRMIB.REC_LEN = 634;
                S000_CALL_AIZRMIB();
                if (AICRMIB.RETURN_INFO == 'N') {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_AITMIB_NOTFND;
                    S000_ERR_MSG_PROC();
                }
            }
        }
    }
    public void R000_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT);
        WS_OUTPUT.WS_OUT_GLBOOK = AIRCMIB.KEY.GL_BOOK;
        WS_OUTPUT.WS_OUT_BR = AIB5800_AWA_5800.BR;
        WS_OUTPUT.WS_OUT_ITM = AIRCMIB.KEY.ITM;
        WS_OUTPUT.WS_OUT_SEQ = AIRCMIB.KEY.SEQ;
        WS_OUTPUT.WS_OUT_ITMNM = AIRCMIB.CHS_NM;
        if (AICRMIB.RETURN_INFO == 'F') {
            WS_OUTPUT.WS_OUT_ITMNM = AIRMIB.CHS_NM;
        }
        WS_OUTPUT.WS_OUT_ACTYP = AIRCMIB.AC_TYP;
        WS_OUTPUT.WS_OUT_CCYLMT = AIRCMIB.CCY_LMT;
        WS_OUTPUT.WS_OUT_BALDIR = AIRCMIB.BAL_DIR;
        WS_OUTPUT.WS_OUT_BALRFLG = AIRCMIB.BAL_RFLG;
        WS_OUTPUT.WS_OUT_DTLFLG = AIRCMIB.DTL_FLG;
        WS_OUTPUT.WS_OUT_RVSTYP = AIRCMIB.RVS_TYP;
        WS_OUTPUT.WS_OUT_RVSKND = AIRCMIB.RVS_KND;
        WS_OUTPUT.WS_OUT_RVSEXP = AIRCMIB.RVS_EXP;
        WS_OUTPUT.WS_OUT_RVSUNIT = AIRCMIB.RVS_UNIT;
        WS_OUTPUT.WS_OUT_ACEXP = AIRCMIB.AC_EXP;
        WS_OUTPUT.WS_OUT_MANUAL = AIRCMIB.MANUAL_FLG;
        WS_OUTPUT.WS_OUT_AMTDIR = AIRCMIB.AMT_DIR;
        WS_OUTPUT.WS_OUT_ONLFLG = AIRCMIB.ONL_FLG;
        WS_OUTPUT.WS_OUT_CARDFLG = AIRCMIB.CARD_FLG;
        WS_OUTPUT.WS_OUT_HOTFLG = AIRCMIB.HOT_FLG;
        WS_OUTPUT.WS_OUT_DRLTBAL = AIRCMIB.DRLT_BAL;
        WS_OUTPUT.WS_OUT_CRLTBAL = AIRCMIB.CRLT_BAL;
        WS_OUTPUT.WS_OUT_BALCHK = AIRCMIB.BAL_CHK;
        WS_OUTPUT.WS_OUT_APP = AIRCMIB.APP1;
        WS_OUTPUT.WS_OUT_APP1 = AIRCMIB.APP2;
        WS_OUTPUT.WS_OUT_APP2 = AIRCMIB.APP3;
        WS_OUTPUT.WS_OUT_APP3 = AIRCMIB.APP4;
        WS_OUTPUT.WS_OUT_APP4 = AIRCMIB.APP5;
        WS_OUTPUT.WS_OUT_APP5 = AIRCMIB.APP6;
        WS_OUTPUT.WS_OUT_APP6 = AIRCMIB.APP7;
        WS_OUTPUT.WS_OUT_APP7 = AIRCMIB.APP8;
        WS_OUTPUT.WS_OUT_APP8 = AIRCMIB.APP9;
        WS_OUTPUT.WS_OUT_APP9 = AIRCMIB.APP10;
        WS_OUTPUT.WS_OUT_STS = AIRCMIB.STS;
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_GLBOOK);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_BR);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_ITM);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_SEQ);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_APP);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_APP1);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_APP2);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_APP3);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_APP4);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_HOTFLG);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "AI801";
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 272;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_AIZRCMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_CMIB_MAIN, AICRCMIB);
        CEP.TRC(SCCGWA, "2222");
        CEP.TRC(SCCGWA, AICRCMIB.RC.RC_CODE);
        if (AICRCMIB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICRCMIB.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_AIZPCMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-GET-CMIB", AICPCMIB);
        CEP.TRC(SCCGWA, "1111");
        CEP.TRC(SCCGWA, AICPCMIB.RC);
        CEP.TRC(SCCGWA, AICPCMIB.RETURN_INFO);
    }
    public void S000_CALL_AIZRMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-R-MAIN-MIB", AICRMIB);
        CEP.TRC(SCCGWA, AICRMIB.RC);
        CEP.TRC(SCCGWA, AICRMIB.RETURN_INFO);
    }
    public void S000_CALL_AIZPQITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-ITM", AICPQITM);
        CEP.TRC(SCCGWA, AICPQITM.RC);
        if (AICPQITM.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPQITM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC.RC_CODE);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
