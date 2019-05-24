package com.hisun.AI;

import java.util.ArrayList;
import java.util.List;
import com.hisun.BP.*;
import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class AIZSCMIB {
    AIZSCMIB_WS_DETAIL_CMIB WS_DETAIL_CMIB;
    AIZSCMIB_WS_RVSNO_DATA WS_RVSNO_DATA;
    int JIBS_tmp_int;
    DBParm AITMIBH_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    double WS_BAL = 0;
    int WS_CNT = 0;
    int WS_CNT1 = 0;
    int WS_J = 0;
    int WS_K = 0;
    char WS_CMIB_STS = ' ';
    char WS_RVS_FLG = ' ';
    char WS_RED_FLG = ' ';
    char WS_RVS_MOD_FLG = ' ';
    char WS_RVS_SYN_FLG = ' ';
    char WS_ACEXP_MOD_FLG = ' ';
    char WS_RVS_CHECK = ' ';
    AIZSCMIB_WS_BRW_OUTPUT WS_BRW_OUTPUT = new AIZSCMIB_WS_BRW_OUTPUT();
    AIZSCMIB_WS_OUTPUT WS_OUTPUT = new AIZSCMIB_WS_OUTPUT();
    List<AIZSCMIB_WS_RVSNO_DATA> WS_RVSNO_DATA = new ArrayList<AIZSCMIB_WS_RVSNO_DATA>();
    List<AIZSCMIB_WS_DETAIL_CMIB> WS_DETAIL_CMIB = new ArrayList<AIZSCMIB_WS_DETAIL_CMIB>();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    AICRCMIB AICRCMIB = new AICRCMIB();
    AICUPRVS AICUPRVS = new AICUPRVS();
    AICRMIB AICRMIB = new AICRMIB();
    AICPQITM AICPQITM = new AICPQITM();
    AIRCMIB AIRCMIB = new AIRCMIB();
    AICOCMIB AICOCMIB = new AICOCMIB();
    AICOCMIB AICCOMIB = new AICOCMIB();
    AIRMIB AIRMIB = new AIRMIB();
    AICSMIB AICSMIB = new AICSMIB();
    AIRGINF AIRGINF = new AIRGINF();
    AICRGINF AICRGINF = new AICRGINF();
    AIRMIBH AIRMIBH = new AIRMIBH();
    AICRMIBH AICRMIBH = new AICRMIBH();
    char WS_DEAL_FLG = ' ';
    SCCGWA SCCGWA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    AICSCMIB AICSCMIB;
    public void MP(SCCGWA SCCGWA, AICSCMIB AICSCMIB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICSCMIB = AICSCMIB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZSCMIB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (AICSCMIB.FUNC == 'B') {
            B020_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else if (AICSCMIB.FUNC == 'Q') {
            B030_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (AICSCMIB.FUNC == 'A') {
            B040_ADD_PROCESS();
            if (pgmRtn) return;
        } else if (AICSCMIB.FUNC == 'M') {
            B050_MODIFY_PROCESS();
            if (pgmRtn) return;
        } else if (AICSCMIB.FUNC == 'R') {
            B060_REACTIVE_PROCESS();
            if (pgmRtn) return;
        } else if (AICSCMIB.FUNC == 'H') {
            B070_HOLD_PROCESS();
            if (pgmRtn) return;
        } else if (AICSCMIB.FUNC == 'U') {
            B080_UNHOLD_PROCESS();
            if (pgmRtn) return;
        } else if (AICSCMIB.FUNC == 'S') {
            B090_STOP_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (AICSCMIB.FUNC != 'B') {
            R000_DATA_OUTPUT();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void R000_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT);
        WS_OUTPUT.WS_OUT_GL_BOOK = AIRCMIB.KEY.GL_BOOK;
        WS_OUTPUT.WS_OUT_BR = AIRCMIB.KEY.BR;
        WS_OUTPUT.WS_OUT_ITM = AIRCMIB.KEY.ITM;
        WS_OUTPUT.WS_OUT_SEQ = AIRCMIB.KEY.SEQ;
        WS_OUTPUT.WS_OUT_NAME = AIRCMIB.CHS_NM;
        WS_OUTPUT.WS_OUT_AC_TYP = AIRCMIB.AC_TYP;
        WS_OUTPUT.WS_OUT_CCY_LMT = AIRCMIB.CCY_LMT;
        WS_OUTPUT.WS_OUT_BAL_DIR = AIRCMIB.BAL_DIR;
        WS_OUTPUT.WS_OUT_BAL_RFLG = AIRCMIB.BAL_RFLG;
        WS_OUTPUT.WS_OUT_DTL_FLG = AIRCMIB.DTL_FLG;
        WS_OUTPUT.WS_OUT_RVS_TYP = AIRCMIB.RVS_TYP;
        WS_OUTPUT.WS_OUT_RVS_KND = AIRCMIB.RVS_KND;
        WS_OUTPUT.WS_OUT_RVS_EXP = AIRCMIB.RVS_EXP;
        WS_OUTPUT.WS_OUT_RVS_UNIT = AIRCMIB.RVS_UNIT;
        WS_OUTPUT.WS_OUT_AC_EXP = AIRCMIB.AC_EXP;
        WS_OUTPUT.WS_OUT_MANUAL = AIRCMIB.MANUAL_FLG;
        WS_OUTPUT.WS_OUT_AMT_DIR = AIRCMIB.AMT_DIR;
        WS_OUTPUT.WS_OUT_ONL_FLG = AIRCMIB.ONL_FLG;
        WS_OUTPUT.WS_OUT_CARD_FLG = AIRCMIB.CARD_FLG;
        WS_OUTPUT.WS_OUT_HOT_FLG = AIRCMIB.HOT_FLG;
        WS_OUTPUT.WS_OUT_DRLT_BAL = AIRCMIB.DRLT_BAL;
        WS_OUTPUT.WS_OUT_CRLT_BAL = AIRCMIB.CRLT_BAL;
        WS_OUTPUT.WS_OUT_BAL_CHK = AIRCMIB.BAL_CHK;
        WS_OUTPUT.WS_OUT_EFF_DATE = AIRCMIB.EFF_DATE;
        WS_OUTPUT.WS_OUT_EXP_DATE = AIRCMIB.EXP_DATE;
        WS_OUTPUT.WS_OUT_APP1 = AIRCMIB.APP1;
        WS_OUTPUT.WS_OUT_APP2 = AIRCMIB.APP2;
        WS_OUTPUT.WS_OUT_APP3 = AIRCMIB.APP3;
        WS_OUTPUT.WS_OUT_APP4 = AIRCMIB.APP4;
        WS_OUTPUT.WS_OUT_APP5 = AIRCMIB.APP5;
        WS_OUTPUT.WS_OUT_APP6 = AIRCMIB.APP6;
        WS_OUTPUT.WS_OUT_APP7 = AIRCMIB.APP7;
        WS_OUTPUT.WS_OUT_APP8 = AIRCMIB.APP8;
        WS_OUTPUT.WS_OUT_APP9 = AIRCMIB.APP9;
        WS_OUTPUT.WS_OUT_APP10 = AIRCMIB.APP10;
        WS_OUTPUT.WS_OUT_STS = AIRCMIB.STS;
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_EFF_DATE);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_EXP_DATE);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_APP1);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_APP2);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_APP3);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_APP4);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_APP5);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "AI702";
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 296;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B030_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRCMIB);
        IBS.init(SCCGWA, AICRCMIB);
        AICRCMIB.FUNC = 'Q';
        AIRCMIB.KEY.GL_BOOK = AICSCMIB.GL_BOOK;
        AIRCMIB.KEY.ITM = AICSCMIB.ITM;
        AIRCMIB.KEY.BR = AICSCMIB.BR;
        AIRCMIB.KEY.SEQ = AICSCMIB.SEQ;
        AICRCMIB.POINTER = AIRCMIB;
        AICRCMIB.REC_LEN = 407;
        S000_CALL_AIZRCMIB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICSCMIB.GL_BOOK);
        CEP.TRC(SCCGWA, AICSCMIB.ITM);
        CEP.TRC(SCCGWA, AICSCMIB.BR);
        CEP.TRC(SCCGWA, AICSCMIB.SEQ);
        CEP.TRC(SCCGWA, AIRCMIB.KEY.GL_BOOK);
        CEP.TRC(SCCGWA, AIRCMIB.KEY.ITM);
        CEP.TRC(SCCGWA, AIRCMIB.KEY.BR);
        CEP.TRC(SCCGWA, AIRCMIB.KEY.SEQ);
        if (AICRCMIB.RETURN_INFO == 'N') {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_AITCMIB_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B040_ADD_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AICSCMIB.SEQ);
        CEP.TRC(SCCGWA, AICSCMIB.ITM);
        CEP.TRC(SCCGWA, AICSCMIB.BR);
        CEP.TRC(SCCGWA, AICSCMIB.GL_BOOK);
        CEP.TRC(SCCGWA, AICSCMIB.RVS_EXP);
        CEP.TRC(SCCGWA, AICSCMIB.DRLT_BAL);
        CEP.TRC(SCCGWA, AICSCMIB.CRLT_BAL);
        CEP.TRC(SCCGWA, AICSCMIB.CHS_NM);
        CEP.TRC(SCCGWA, AICSCMIB.AC_TYP);
        CEP.TRC(SCCGWA, AICSCMIB.CCY_LMT);
        CEP.TRC(SCCGWA, AICSCMIB.BAL_DIR);
        CEP.TRC(SCCGWA, AICSCMIB.BAL_RFLG);
        CEP.TRC(SCCGWA, AICSCMIB.DTL_FLG);
        CEP.TRC(SCCGWA, AICSCMIB.RVS_TYP);
        CEP.TRC(SCCGWA, AICSCMIB.RVS_KND);
        CEP.TRC(SCCGWA, AICSCMIB.RVS_UNIT);
        CEP.TRC(SCCGWA, AICSCMIB.AC_EXP);
        CEP.TRC(SCCGWA, AICSCMIB.MANUAL_FLG);
        CEP.TRC(SCCGWA, AICSCMIB.AMT_DIR);
        CEP.TRC(SCCGWA, AICSCMIB.ONL_FLG);
        CEP.TRC(SCCGWA, AICSCMIB.CARD_FLG);
        CEP.TRC(SCCGWA, AICSCMIB.HOT_FLG);
        CEP.TRC(SCCGWA, AICSCMIB.BAL_CHK);
        CEP.TRC(SCCGWA, AICSCMIB.APP1);
        CEP.TRC(SCCGWA, AICSCMIB.APP2);
        CEP.TRC(SCCGWA, AICSCMIB.APP3);
        CEP.TRC(SCCGWA, AICSCMIB.APP4);
        CEP.TRC(SCCGWA, AICSCMIB.APP5);
        CEP.TRC(SCCGWA, AICSCMIB.APP6);
        CEP.TRC(SCCGWA, AICSCMIB.APP7);
        CEP.TRC(SCCGWA, AICSCMIB.APP8);
        CEP.TRC(SCCGWA, AICSCMIB.APP9);
        CEP.TRC(SCCGWA, AICSCMIB.APP10);
        CEP.TRC(SCCGWA, AICSCMIB.EFF_DATE);
        CEP.TRC(SCCGWA, AICSCMIB.EXP_DATE);
        IBS.init(SCCGWA, AICRCMIB);
        IBS.init(SCCGWA, AIRCMIB);
        AICRCMIB.FUNC = 'B';
        AICRCMIB.OPT = 'I';
        AIRCMIB.KEY.GL_BOOK = AICSCMIB.GL_BOOK;
        AIRCMIB.KEY.ITM = AICSCMIB.ITM;
        AICRCMIB.POINTER = AIRCMIB;
        AICRCMIB.REC_LEN = 407;
        S000_CALL_AIZRCMIB();
        if (pgmRtn) return;
        AICRCMIB.OPT = 'N';
        S000_CALL_AIZRCMIB();
        if (pgmRtn) return;
        WS_RVS_CHECK = 'N';
        while (AICRCMIB.RETURN_INFO != 'N') {
            if ((AICSCMIB.SEQ == 999999 
                && AICSCMIB.SEQ != AIRCMIB.KEY.SEQ) 
                || (AICSCMIB.SEQ != 999999 
                && AIRCMIB.KEY.SEQ == 999999)) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_SEQ_IS_NOT_ALL_NINE);
            }
            if (AIRCMIB.RVS_TYP == 'N') {
                if (AICSCMIB.RVS_TYP != 'N') {
                    WS_RVS_CHECK = 'Y';
                }
            } else {
                if (AICSCMIB.RVS_TYP == 'N') {
                    WS_RVS_CHECK = 'Y';
                }
            }
            AICRCMIB.OPT = 'N';
            S000_CALL_AIZRCMIB();
            if (pgmRtn) return;
        }
        if (WS_RVS_CHECK == 'Y') {
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_RVS_TYP_M_KEEP_SAME);
        }
        AICRCMIB.OPT = 'E';
        S000_CALL_AIZRCMIB();
        if (pgmRtn) return;
        if (AICSCMIB.BR != 999999) {
            IBS.init(SCCGWA, AIRMIB);
            IBS.init(SCCGWA, AICRMIB);
            WS_OUTPUT.WS_MIB_FOUND = ' ';
            WS_OUTPUT.WS_MIB_MOD = ' ';
            AICRMIB.FUNC = 'B';
            AICRMIB.OPT = '2';
            if (AICSCMIB.SEQ == 999999) {
                AICRMIB.OPT = '9';
            }
            CEP.TRC(SCCGWA, AICRMIB.OPT);
            AIRMIB.KEY.ITM_NO = AICSCMIB.ITM;
            AIRMIB.KEY.BR = AICSCMIB.BR;
            AIRMIB.KEY.SEQ = AICSCMIB.SEQ;
            AIRMIB.KEY.GL_BOOK = AICSCMIB.GL_BOOK;
            S000_CALL_AIZRMIB();
            if (pgmRtn) return;
            AICRMIB.OPT = 'N';
            S000_CALL_AIZRMIB();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, AICRMIB.RETURN_INFO);
            CEP.TRC(SCCGWA, WS_OUTPUT.WS_MIB_FOUND);
            while (AICRMIB.RETURN_INFO != 'N') {
                CEP.TRC(SCCGWA, AIRMIB.STS);
                if (AIRMIB.STS == 'C') {
                    WS_OUTPUT.WS_MIB_FOUND = 'Y';
                } else {
                    WS_OUTPUT.WS_MIB_MOD = 'N';
                }
                AICRMIB.OPT = 'N';
                S000_CALL_AIZRMIB();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, WS_OUTPUT.WS_MIB_FOUND);
                CEP.TRC(SCCGWA, WS_OUTPUT.WS_MIB_MOD);
            }
            AICRMIB.OPT = 'E';
            S000_CALL_AIZRMIB();
            if (pgmRtn) return;
            if (WS_OUTPUT.WS_MIB_MOD == 'N') {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_CMIB_NOT_ADD;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        SCCSUBS.AP_CODE = 1;
        SCCSUBS.TR_CODE = 5702;
        S000_SET_SUBS_TRN();
        if (pgmRtn) return;
        if (AICSCMIB.EFF_DATE <= SCCGWA.COMM_AREA.AC_DATE) {
            AICSCMIB.STS = 'N';
        } else {
            if (AICSCMIB.EFF_DATE > SCCGWA.COMM_AREA.AC_DATE) {
                AICSCMIB.STS = 'P';
            }
        }
    }
    CEP.TRC(SCCGWA, AICSCMIB.STS);
    IBS.init(SCCGWA, AIRCMIB);
    IBS.init(SCCGWA, AICRCMIB);
    IBS.init(SCCGWA, AICOCMIB);
    AICRCMIB.FUNC = 'C';
    AIRCMIB.KEY.GL_BOOK = AICSCMIB.GL_BOOK;
    AIRCMIB.KEY.SEQ = AICSCMIB.SEQ;
    AIRCMIB.KEY.ITM = AICSCMIB.ITM;
    AIRCMIB.KEY.BR = AICSCMIB.BR;
    AIRCMIB.RVS_EXP = AICSCMIB.RVS_EXP;
    AIRCMIB.DRLT_BAL = AICSCMIB.DRLT_BAL;
    AIRCMIB.CRLT_BAL = AICSCMIB.CRLT_BAL;
    AIRCMIB.CHS_NM = AICSCMIB.CHS_NM;
    AIRCMIB.AC_TYP = AICSCMIB.AC_TYP;
    AIRCMIB.CCY_LMT = AICSCMIB.CCY_LMT;
    AIRCMIB.BAL_DIR = AICSCMIB.BAL_DIR;
    AIRCMIB.BAL_RFLG = AICSCMIB.BAL_RFLG;
    AIRCMIB.DTL_FLG = AICSCMIB.DTL_FLG;
    AIRCMIB.RVS_TYP = AICSCMIB.RVS_TYP;
    AIRCMIB.RVS_KND = AICSCMIB.RVS_KND;
    AIRCMIB.RVS_UNIT = AICSCMIB.RVS_UNIT;
    AIRCMIB.AC_EXP = AICSCMIB.AC_EXP;
    AIRCMIB.MANUAL_FLG = AICSCMIB.MANUAL_FLG;
    AIRCMIB.AMT_DIR = AICSCMIB.AMT_DIR;
    AIRCMIB.CARD_FLG = AICSCMIB.CARD_FLG;
    AIRCMIB.HOT_FLG = AICSCMIB.HOT_FLG;
    AIRCMIB.ONL_FLG = AICSCMIB.ONL_FLG;
    AIRCMIB.BAL_CHK = AICSCMIB.BAL_CHK;
    AIRCMIB.APP1 = AICSCMIB.APP1;
    AIRCMIB.APP2 = AICSCMIB.APP2;
    AIRCMIB.APP3 = AICSCMIB.APP3;
    AIRCMIB.APP4 = AICSCMIB.APP4;
    AIRCMIB.APP5 = AICSCMIB.APP5;
    AIRCMIB.APP6 = AICSCMIB.APP6;
    AIRCMIB.APP7 = AICSCMIB.APP7;
    AIRCMIB.APP8 = AICSCMIB.APP8;
    AIRCMIB.APP9 = AICSCMIB.APP9;
    AIRCMIB.APP10 = AICSCMIB.APP10;
    AIRCMIB.EFF_DATE = AICSCMIB.EFF_DATE;
    AIRCMIB.EXP_DATE = AICSCMIB.EXP_DATE;
    AIRCMIB.STS = AICSCMIB.STS;
    AIRCMIB.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
    AIRCMIB.LAST_UPDATE_TLR = SCCGWA.COMM_AREA.TL_ID;
    AICRCMIB.POINTER = AIRCMIB;
    AICRCMIB.REC_LEN = 407;
    S000_CALL_AIZRCMIB();
    if (pgmRtn) return;
    if (AICRCMIB.RC.RC_CODE != 0) {
        WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICRCMIB.RC);
        S000_ERR_MSG_PROC();
        if (pgmRtn) return;
    }
    R000_SAVE_NEW_HIS_DATA();
    if (pgmRtn) return;
    H000_HISTORY_PROCESS();
    if (pgmRtn) return;
    if (WS_OUTPUT.WS_MIB_FOUND == 'Y') {
        CEP.TRC(SCCGWA, AICSCMIB.BR);
        CEP.TRC(SCCGWA, AIRMIB.KEY.CCY);
        IBS.init(SCCGWA, AIRMIB);
        IBS.init(SCCGWA, AICRMIB);
        AICRMIB.FUNC = 'B';
        AICRMIB.OPT = '2';
        if (AICSCMIB.SEQ == 999999) {
            AICRMIB.OPT = '9';
        }
        CEP.TRC(SCCGWA, AICRMIB.OPT);
        AIRMIB.KEY.ITM_NO = AICSCMIB.ITM;
        AIRMIB.KEY.BR = AICSCMIB.BR;
        AIRMIB.KEY.SEQ = AICSCMIB.SEQ;
        AIRMIB.KEY.GL_BOOK = AICSCMIB.GL_BOOK;
        S000_CALL_AIZRMIB();
        if (pgmRtn) return;
        AICRMIB.OPT = 'N';
        S000_CALL_AIZRMIB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICRMIB.RETURN_INFO);
        while (AICRMIB.RETURN_INFO != 'N') {
            CEP.TRC(SCCGWA, "KKKK");
            CEP.TRC(SCCGWA, AIRMIB.AC_NO);
            AICRMIB.FUNC = 'R';
            S000_CALL_AIZRMIB();
            if (pgmRtn) return;
            if (AICRMIB.RETURN_INFO == 'N') {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_AITMIB_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, AICSMIB);
            AICRMIB.FUNC = 'U';
            AIRMIB.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            AIRMIB.LAST_UPDATE_TLR = SCCGWA.COMM_AREA.TL_ID;
            AIRMIB.AC_TYP = AICSCMIB.AC_TYP;
            AIRMIB.BAL_DIR = AICSCMIB.BAL_DIR;
            AIRMIB.BAL_RFLG = AICSCMIB.BAL_RFLG;
            AIRMIB.DTL_FLG = AICSCMIB.DTL_FLG;
            AIRMIB.RVS_TYP = AICSCMIB.RVS_TYP;
            AIRMIB.RVS_KND = AICSCMIB.RVS_KND;
            AIRMIB.RVS_EXP = AICSCMIB.RVS_EXP;
            AIRMIB.RVS_UNIT = AICSCMIB.RVS_UNIT;
            AIRMIB.MANUAL_FLG = AICSCMIB.MANUAL_FLG;
            AIRMIB.DRLT_BAL = AICSCMIB.DRLT_BAL;
            AIRMIB.CRLT_BAL = AICSCMIB.CRLT_BAL;
            AIRMIB.AC_EXP = AICSCMIB.AC_EXP;
            AIRMIB.AMT_DIR = AICSCMIB.AMT_DIR;
            AIRMIB.ONL_FLG = AICSCMIB.ONL_FLG;
            AIRMIB.CARD_FLG = AICSCMIB.CARD_FLG;
            AIRMIB.HOT_FLG = AICSCMIB.HOT_FLG;
            AIRMIB.BAL_CHK = AICSCMIB.BAL_CHK;
            AIRMIB.APP1 = AICSCMIB.APP1;
            AIRMIB.APP2 = AICSCMIB.APP2;
            AIRMIB.APP3 = AICSCMIB.APP3;
            AIRMIB.APP4 = AICSCMIB.APP4;
            AIRMIB.APP5 = AICSCMIB.APP5;
            AIRMIB.APP6 = AICSCMIB.APP6;
            AIRMIB.APP7 = AICSCMIB.APP7;
            AIRMIB.APP8 = AICSCMIB.APP8;
            AIRMIB.APP9 = AICSCMIB.APP9;
            AIRMIB.APP10 = AICSCMIB.APP10;
            S000_CALL_AIZRMIB();
            if (pgmRtn) return;
            AICRMIB.FUNC = 'B';
            AICRMIB.OPT = 'N';
            S000_CALL_AIZRMIB();
            if (pgmRtn) return;
        }
        AICRMIB.OPT = 'E';
        S000_CALL_AIZRMIB();
        if (pgmRtn) return;
    }
    public void B050_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AICSCMIB.ITM);
        CEP.TRC(SCCGWA, AICSCMIB.BR);
        IBS.init(SCCGWA, AICRCMIB);
        IBS.init(SCCGWA, AIRCMIB);
        AICRCMIB.FUNC = 'B';
        AICRCMIB.OPT = 'I';
        AIRCMIB.KEY.GL_BOOK = AICSCMIB.GL_BOOK;
        AIRCMIB.KEY.ITM = AICSCMIB.ITM;
        AICRCMIB.POINTER = AIRCMIB;
        AICRCMIB.REC_LEN = 407;
        S000_CALL_AIZRCMIB();
        if (pgmRtn) return;
        AICRCMIB.OPT = 'N';
        S000_CALL_AIZRCMIB();
        if (pgmRtn) return;
        while (AICRCMIB.RETURN_INFO != 'N') {
            if (AICSCMIB.SEQ == AIRCMIB.KEY.SEQ 
                && AIRCMIB.KEY.BR != 999999) {
                WS_CNT1 = WS_CNT1 + 1;
                if (WS_CNT1 == 999999) {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.EXPECIAL_BR_REC_TOO_MUC;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, WS_CNT1);
                WS_DETAIL_CMIB = WS_DETAIL_CMIB.get(WS_CNT1-1);
                WS_DETAIL_CMIB.WS_DETAIL_BR = AIRCMIB.KEY.BR;
                WS_DETAIL_CMIB.set(WS_CNT1-1, WS_DETAIL_CMIB);
                CEP.TRC(SCCGWA, WS_DETAIL_CMIB.get(WS_CNT1-1).WS_DETAIL_BR);
            }
            if (AICSCMIB.SEQ == 999999 
                && AICSCMIB.SEQ != AIRCMIB.KEY.SEQ) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_SEQ_IS_NOT_ALL_NINE);
            }
            CEP.TRC(SCCGWA, AIRCMIB.RVS_TYP);
            CEP.TRC(SCCGWA, AICSCMIB.RVS_TYP);
            CEP.TRC(SCCGWA, AIRCMIB.KEY.SEQ);
            CEP.TRC(SCCGWA, AICSCMIB.SEQ);
            AICRCMIB.OPT = 'N';
            S000_CALL_AIZRCMIB();
            if (pgmRtn) return;
        }
        AICRCMIB.OPT = 'E';
        S000_CALL_AIZRCMIB();
        if (pgmRtn) return;
        IBS.init(SCCGWA, AIRCMIB);
        IBS.init(SCCGWA, AICRCMIB);
        IBS.init(SCCGWA, AICOCMIB);
        AICRCMIB.FUNC = 'R';
        AIRCMIB.KEY.GL_BOOK = AICSCMIB.GL_BOOK;
        AIRCMIB.KEY.ITM = AICSCMIB.ITM;
        AIRCMIB.KEY.BR = AICSCMIB.BR;
        AIRCMIB.KEY.SEQ = AICSCMIB.SEQ;
        AICRCMIB.POINTER = AIRCMIB;
        AICRCMIB.REC_LEN = 407;
        S000_CALL_AIZRCMIB();
        if (pgmRtn) return;
        if (AICRCMIB.RETURN_INFO == 'N') {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_AITCMIB_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, AIRCMIB.DRLT_BAL);
        CEP.TRC(SCCGWA, AICSCMIB.DRLT_BAL);
        CEP.TRC(SCCGWA, AIRCMIB.CRLT_BAL);
        CEP.TRC(SCCGWA, AICSCMIB.CRLT_BAL);
        CEP.TRC(SCCGWA, AIRCMIB.RVS_TYP);
        CEP.TRC(SCCGWA, AICSCMIB.RVS_TYP);
        if (AIRCMIB.DRLT_BAL != AICSCMIB.DRLT_BAL 
            || AIRCMIB.CRLT_BAL != AICSCMIB.CRLT_BAL 
            || AIRCMIB.RVS_TYP != AICSCMIB.RVS_TYP 
            || AIRCMIB.BAL_DIR != AICSCMIB.BAL_DIR) {
            IBS.init(SCCGWA, AIRMIB);
            IBS.init(SCCGWA, AICRMIB);
            AICRMIB.FUNC = 'B';
            AICRMIB.OPT = '2';
            if (AICSCMIB.SEQ == 999999) {
                AICRMIB.OPT = '9';
            }
            CEP.TRC(SCCGWA, AICRMIB.OPT);
            AIRMIB.KEY.ITM_NO = AICSCMIB.ITM;
            if (AICSCMIB.BR != 999999) {
                AIRMIB.KEY.BR = AICSCMIB.BR;
            }
            AIRMIB.KEY.SEQ = AICSCMIB.SEQ;
            AIRMIB.KEY.GL_BOOK = AICSCMIB.GL_BOOK;
            S000_CALL_AIZRMIB();
            if (pgmRtn) return;
            AICRMIB.OPT = 'N';
            S000_CALL_AIZRMIB();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, AICRMIB.RETURN_INFO);
            while (AICRMIB.RETURN_INFO != 'N') {
                CEP.TRC(SCCGWA, AIRMIB.KEY.CCY);
                CEP.TRC(SCCGWA, AIRMIB.CBAL);
                CEP.TRC(SCCGWA, AIRCMIB.BAL_DIR);
                CEP.TRC(SCCGWA, AICSCMIB.BAL_DIR);
                WS_DEAL_FLG = ' ';
                if (AICSCMIB.BR == 999999 
                    && WS_CNT1 > 0) {
                    for (WS_K = 1; WS_K <= WS_CNT1 
                        && WS_DEAL_FLG != 'N'; WS_K += 1) {
                        if (AIRMIB.KEY.BR == WS_DETAIL_CMIB.get(WS_CNT1-1).WS_DETAIL_BR) {
                            WS_DEAL_FLG = 'N';
                        } else {
                            WS_DEAL_FLG = 'Y';
                        }
                    }
                } else {
                    WS_DEAL_FLG = 'Y';
                }
                CEP.TRC(SCCGWA, "DD111");
                CEP.TRC(SCCGWA, WS_DEAL_FLG);
                if (AICSCMIB.BAL_DIR != 'B' 
                    && AIRCMIB.BAL_DIR != AICSCMIB.BAL_DIR 
                    && WS_DEAL_FLG == 'Y') {
                    if (AIRMIB.CBAL > 0 
                        && AICSCMIB.BAL_DIR == 'D') {
                        WS_ERR_MSG = AICMSG_ERROR_MSG.AI_CBAL_NOT_BALDIR;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    if (AIRMIB.CBAL < 0 
                        && AICSCMIB.BAL_DIR == 'C') {
                        WS_ERR_MSG = AICMSG_ERROR_MSG.AI_DBAL_NOT_BALDIR;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
                CEP.TRC(SCCGWA, AIRMIB.DRLT_BAL);
                CEP.TRC(SCCGWA, AIRMIB.CRLT_BAL);
                if ((AICSCMIB.DRLT_BAL < AIRMIB.DRLT_BAL 
                    || AICSCMIB.CRLT_BAL < AIRMIB.CRLT_BAL) 
                    && WS_DEAL_FLG == 'Y') {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_CMIB_LT_MIB_CTLBAL;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (AICSCMIB.AMT_DIR != 'B' 
                    && WS_DEAL_FLG == 'Y') {
                    if (AIRMIB.CBAL > 0 
                        && AICSCMIB.RVS_TYP == 'D') {
                        WS_ERR_MSG = AICMSG_ERROR_MSG.RVS_TYP_WRONG;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    if (AIRMIB.CBAL < 0 
                        && AICSCMIB.RVS_TYP == 'C') {
                        WS_ERR_MSG = AICMSG_ERROR_MSG.RVS_TYP_WRONG;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                } else {
                    if (AIRMIB.CBAL > 0 
                        && AICSCMIB.RVS_TYP == 'D' 
                        && WS_DEAL_FLG == 'Y') {
                        WS_ERR_MSG = AICMSG_ERROR_MSG.AI_CBAL_NOT_RVSTYP;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    if (AIRMIB.CBAL < 0 
                        && AICSCMIB.RVS_TYP == 'C' 
                        && WS_DEAL_FLG == 'Y') {
                        WS_ERR_MSG = AICMSG_ERROR_MSG.AI_DBAL_NOT_RVSTYP;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
                if (AIRMIB.CBAL > 0 
                    && WS_DEAL_FLG == 'Y') {
                    if (AIRMIB.CBAL > AICSCMIB.CRLT_BAL) {
                        WS_ERR_MSG = AICMSG_ERROR_MSG.AI_MIB_CBAL_OVERLMT;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                } else {
                    WS_BAL = AIRMIB.CBAL * ( -1 );
                    if (WS_BAL > AICSCMIB.DRLT_BAL 
                        && WS_DEAL_FLG == 'Y') {
                        WS_ERR_MSG = AICMSG_ERROR_MSG.AI_MIB_CBAL_OVERLMT;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
                AICRMIB.OPT = 'N';
                S000_CALL_AIZRMIB();
                if (pgmRtn) return;
            }
            AICRMIB.OPT = 'E';
            S000_CALL_AIZRMIB();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, AIRCMIB.EFF_DATE);
        CEP.TRC(SCCGWA, AICSCMIB.EFF_DATE);
        CEP.TRC(SCCGWA, AICSCMIB.STS);
        CEP.TRC(SCCGWA, AIRCMIB.STS);
        if ((AICSCMIB.STS == 'N' 
            && AIRCMIB.STS != 'P') 
            && AIRCMIB.EFF_DATE != AICSCMIB.EFF_DATE) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_EFFICT_NOT_MOD_EFFDT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, AIRCMIB.AC_EXP);
        CEP.TRC(SCCGWA, AICSCMIB.AC_EXP);
        if (AIRCMIB.AC_EXP != AICSCMIB.AC_EXP) {
            IBS.init(SCCGWA, AIRMIB);
            IBS.init(SCCGWA, AICRMIB);
            WS_ACEXP_MOD_FLG = 'Y';
            AICRMIB.FUNC = 'B';
            AICRMIB.OPT = '2';
            if (AICSCMIB.SEQ == 999999) {
                AICRMIB.OPT = '9';
            }
            CEP.TRC(SCCGWA, AICRMIB.OPT);
            AIRMIB.KEY.ITM_NO = AICSCMIB.ITM;
            if (AICSCMIB.BR != 999999) {
                AIRMIB.KEY.BR = AICSCMIB.BR;
            }
            AIRMIB.KEY.SEQ = AICSCMIB.SEQ;
            AIRMIB.KEY.GL_BOOK = AICSCMIB.GL_BOOK;
            S000_CALL_AIZRMIB();
            if (pgmRtn) return;
            AICRMIB.OPT = 'N';
            S000_CALL_AIZRMIB();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, AICRMIB.RETURN_INFO);
            while (AICRMIB.RETURN_INFO != 'N') {
                WS_DEAL_FLG = ' ';
                if (AICSCMIB.BR == 999999 
                    && WS_CNT1 > 0) {
                    for (WS_K = 1; WS_K <= WS_CNT1 
                        && WS_DEAL_FLG != 'N'; WS_K += 1) {
                        if (AIRMIB.KEY.BR == WS_DETAIL_CMIB.get(WS_CNT1-1).WS_DETAIL_BR) {
                            WS_DEAL_FLG = 'N';
                        } else {
                            WS_DEAL_FLG = 'Y';
                        }
                    }
                } else {
                    WS_DEAL_FLG = 'Y';
                }
                CEP.TRC(SCCGWA, "DD222");
                CEP.TRC(SCCGWA, WS_DEAL_FLG);
                IBS.init(SCCGWA, AIRGINF);
                IBS.init(SCCGWA, AICRGINF);
                AICRGINF.INFO.FUNC = 'B';
                AICRGINF.INFO.OPT = '6';
                AIRGINF.AC = AIRMIB.AC_NO;
                CEP.TRC(SCCGWA, AIRMIB.AC_NO);
                S000_CALL_AIZRGINF();
                if (pgmRtn) return;
                AICRGINF.INFO.OPT = 'N';
                S000_CALL_AIZRGINF();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, AICRGINF.RETURN_INFO);
                while (AICRGINF.RETURN_INFO != 'N' 
                    && WS_DEAL_FLG != 'N') {
                    CEP.TRC(SCCGWA, AIRGINF.KEY.RVS_NO);
                    CEP.TRC(SCCGWA, AIRGINF.RVS_EXP);
                    if (AICSCMIB.AC_EXP < AIRGINF.RVS_EXP 
                        && WS_DEAL_FLG == 'Y') {
                        WS_CNT = WS_CNT + 1;
                        WS_RVSNO_DATA = WS_RVSNO_DATA.get(WS_CNT-1);
                        WS_RVSNO_DATA.WS_REV_NO = AIRGINF.KEY.RVS_NO;
                        WS_RVSNO_DATA.set(WS_CNT-1, WS_RVSNO_DATA);
                        CEP.TRC(SCCGWA, WS_CNT);
                        CEP.TRC(SCCGWA, WS_RVSNO_DATA.get(WS_CNT-1).WS_REV_NO);
                    }
                    AICRGINF.INFO.OPT = 'N';
                    S000_CALL_AIZRGINF();
                    if (pgmRtn) return;
                }
                AICRGINF.INFO.OPT = 'E';
                S000_CALL_AIZRGINF();
                if (pgmRtn) return;
                AICRMIB.OPT = 'N';
                S000_CALL_AIZRMIB();
                if (pgmRtn) return;
            }
            AICRMIB.OPT = 'E';
            S000_CALL_AIZRMIB();
            if (pgmRtn) return;
            for (WS_J = 1; WS_J <= WS_CNT; WS_J += 1) {
                IBS.init(SCCGWA, AIRGINF);
                IBS.init(SCCGWA, AICRGINF);
                AIRGINF.KEY.RVS_NO = WS_RVSNO_DATA.get(WS_J-1).WS_REV_NO;
                CEP.TRC(SCCGWA, WS_J);
                CEP.TRC(SCCGWA, WS_RVSNO_DATA.get(WS_J-1).WS_REV_NO);
                AICRGINF.INFO.FUNC = 'R';
                S000_CALL_AIZRGINF();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, AIRGINF.KEY.RVS_NO);
                CEP.TRC(SCCGWA, AIRGINF.RVS_EXP);
                AICRGINF.INFO.FUNC = 'U';
                AIRGINF.RVS_EXP = AICSCMIB.AC_EXP;
                S000_CALL_AIZRGINF();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, AIRCMIB.CCY_LMT);
        CEP.TRC(SCCGWA, AICSCMIB.CCY_LMT);
        if (AIRCMIB.CCY_LMT == '0' 
            && AICSCMIB.CCY_LMT != '0') {
            IBS.init(SCCGWA, AIRMIB);
            IBS.init(SCCGWA, AICRMIB);
            AICRMIB.FUNC = 'B';
            AICRMIB.OPT = '2';
            if (AICSCMIB.SEQ == 999999) {
                AICRMIB.OPT = '9';
            }
            CEP.TRC(SCCGWA, AICRMIB.OPT);
            AIRMIB.KEY.ITM_NO = AICSCMIB.ITM;
            if (AICSCMIB.BR != 999999) {
                AIRMIB.KEY.BR = AICSCMIB.BR;
            }
            AIRMIB.KEY.SEQ = AICSCMIB.SEQ;
            AIRMIB.KEY.GL_BOOK = AICSCMIB.GL_BOOK;
            S000_CALL_AIZRMIB();
            if (pgmRtn) return;
            AICRMIB.OPT = 'N';
            S000_CALL_AIZRMIB();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, AICRMIB.RETURN_INFO);
            while (AICRMIB.RETURN_INFO != 'N') {
                WS_DEAL_FLG = ' ';
                if (AICSCMIB.BR == 999999 
                    && WS_CNT1 > 0) {
                    for (WS_K = 1; WS_K <= WS_CNT1 
                        && WS_DEAL_FLG != 'N'; WS_K += 1) {
                        if (AIRMIB.KEY.BR == WS_DETAIL_CMIB.get(WS_CNT1-1).WS_DETAIL_BR) {
                            WS_DEAL_FLG = 'N';
                        } else {
                            WS_DEAL_FLG = 'Y';
                        }
                    }
                } else {
                    WS_DEAL_FLG = 'Y';
                }
                CEP.TRC(SCCGWA, "DD333");
                CEP.TRC(SCCGWA, WS_DEAL_FLG);
                CEP.TRC(SCCGWA, AIRMIB.KEY.CCY);
                if (AICSCMIB.CCY_LMT == '1' 
                    && !AIRMIB.KEY.CCY.equalsIgnoreCase(BPCRBANK.LOC_CCY1) 
                    && WS_DEAL_FLG == 'Y') {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_FOREIGN_CCY_EXIST;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (AICSCMIB.CCY_LMT == '2' 
                    && AIRMIB.KEY.CCY.equalsIgnoreCase(BPCRBANK.LOC_CCY1) 
                    && WS_DEAL_FLG == 'Y') {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_LOCAL_CCY_EXIST;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                AICRMIB.OPT = 'N';
                S000_CALL_AIZRMIB();
                if (pgmRtn) return;
            }
            AICRMIB.OPT = 'E';
            S000_CALL_AIZRMIB();
            if (pgmRtn) return;
        }
        if ((AIRCMIB.CCY_LMT == '1' 
            && AICSCMIB.CCY_LMT == '2') 
            || (AIRCMIB.CCY_LMT == '2' 
            && AICSCMIB.CCY_LMT == '1')) {
            IBS.init(SCCGWA, AIRMIB);
            IBS.init(SCCGWA, AICRMIB);
            AICRMIB.FUNC = 'B';
            AICRMIB.OPT = '2';
            if (AICSCMIB.SEQ == 999999) {
                AICRMIB.OPT = '9';
            }
            CEP.TRC(SCCGWA, AICRMIB.OPT);
            AIRMIB.KEY.ITM_NO = AICSCMIB.ITM;
            if (AICSCMIB.BR != 999999) {
                AIRMIB.KEY.BR = AICSCMIB.BR;
            }
            AIRMIB.KEY.SEQ = AICSCMIB.SEQ;
            AIRMIB.KEY.GL_BOOK = AICSCMIB.GL_BOOK;
            S000_CALL_AIZRMIB();
            if (pgmRtn) return;
            AICRMIB.OPT = 'N';
            S000_CALL_AIZRMIB();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, AICRMIB.RETURN_INFO);
            while (AICRMIB.RETURN_INFO != 'N') {
                WS_DEAL_FLG = ' ';
                if (AICSCMIB.BR == 999999 
                    && WS_CNT1 > 0) {
                    for (WS_K = 1; WS_K <= WS_CNT1 
                        && WS_DEAL_FLG != 'N'; WS_K += 1) {
                        if (AIRMIB.KEY.BR == WS_DETAIL_CMIB.get(WS_CNT1-1).WS_DETAIL_BR) {
                            WS_DEAL_FLG = 'N';
                        } else {
                            WS_DEAL_FLG = 'Y';
                        }
                    }
                } else {
                    WS_DEAL_FLG = 'Y';
                }
                CEP.TRC(SCCGWA, "DD444");
                CEP.TRC(SCCGWA, WS_DEAL_FLG);
                CEP.TRC(SCCGWA, AIRMIB.KEY.CCY);
                if (AICSCMIB.CCY_LMT == '1' 
                    && !AIRMIB.KEY.CCY.equalsIgnoreCase(BPCRBANK.LOC_CCY1) 
                    && WS_DEAL_FLG == 'Y') {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_FOREIGN_CCY_EXIST;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (AICSCMIB.CCY_LMT == '2' 
                    && AIRMIB.KEY.CCY.equalsIgnoreCase(BPCRBANK.LOC_CCY1) 
                    && WS_DEAL_FLG == 'Y') {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_LOCAL_CCY_EXIST;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                AICRMIB.OPT = 'N';
                S000_CALL_AIZRMIB();
                if (pgmRtn) return;
            }
            AICRMIB.OPT = 'E';
            S000_CALL_AIZRMIB();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, AIRCMIB.RVS_KND);
        CEP.TRC(SCCGWA, AICSCMIB.RVS_KND);
        if ((AIRCMIB.RVS_TYP != 'N' 
            && AICSCMIB.RVS_TYP == 'N') 
            || (AIRCMIB.RVS_KND == '2' 
            && (AICSCMIB.RVS_KND == '1' 
            || AICSCMIB.RVS_KND == '3')) 
            || (AIRCMIB.RVS_KND == '4' 
            && (AICSCMIB.RVS_KND == '1' 
            || AICSCMIB.RVS_KND == '3'))) {
            WS_RVS_MOD_FLG = 'Y';
        }
        CEP.TRC(SCCGWA, WS_RVS_MOD_FLG);
        CEP.TRC(SCCGWA, AICSCMIB.BAL_RFLG);
        CEP.TRC(SCCGWA, AIRCMIB.BAL_RFLG);
        if (AIRCMIB.BAL_RFLG == 'Y' 
            && AICSCMIB.BAL_RFLG == 'N') {
            WS_RED_FLG = 'Y';
        }
        CEP.TRC(SCCGWA, WS_RED_FLG);
        if (WS_RVS_MOD_FLG == 'Y' 
            || WS_RED_FLG == 'Y') {
            IBS.init(SCCGWA, AIRMIB);
            IBS.init(SCCGWA, AICRMIB);
            AICRMIB.FUNC = 'B';
            AICRMIB.OPT = '2';
            if (AICSCMIB.SEQ == 999999) {
                AICRMIB.OPT = '9';
            }
            CEP.TRC(SCCGWA, AICRMIB.OPT);
            AIRMIB.KEY.ITM_NO = AICSCMIB.ITM;
            if (AICSCMIB.BR != 999999) {
                AIRMIB.KEY.BR = AICSCMIB.BR;
            }
            AIRMIB.KEY.SEQ = AICSCMIB.SEQ;
            AIRMIB.KEY.GL_BOOK = AICSCMIB.GL_BOOK;
            S000_CALL_AIZRMIB();
            if (pgmRtn) return;
            AICRMIB.OPT = 'N';
            S000_CALL_AIZRMIB();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, AICRMIB.RETURN_INFO);
            while (AICRMIB.RETURN_INFO != 'N') {
                WS_DEAL_FLG = ' ';
                if (AICSCMIB.BR == 999999 
                    && WS_CNT1 > 0) {
                    for (WS_K = 1; WS_K <= WS_CNT1 
                        && WS_DEAL_FLG != 'N'; WS_K += 1) {
                        if (AIRMIB.KEY.BR == WS_DETAIL_CMIB.get(WS_CNT1-1).WS_DETAIL_BR) {
                            WS_DEAL_FLG = 'N';
                        } else {
                            WS_DEAL_FLG = 'Y';
                        }
                    }
                } else {
                    WS_DEAL_FLG = 'Y';
                }
                CEP.TRC(SCCGWA, "DD555");
                CEP.TRC(SCCGWA, WS_DEAL_FLG);
                if (WS_RVS_MOD_FLG == 'Y' 
                    && WS_DEAL_FLG == 'Y') {
                    CEP.TRC(SCCGWA, AIRMIB.KEY.CCY);
                    CEP.TRC(SCCGWA, AIRMIB.CBAL);
                    if (AIRMIB.CBAL != 0) {
                        WS_ERR_MSG = AICMSG_ERROR_MSG.CBAL_NOT_EQ_ZERO;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
                if (WS_RED_FLG == 'Y' 
                    && WS_DEAL_FLG == 'Y') {
                    CEP.TRC(SCCGWA, AIRMIB.BAL_DIR);
                    CEP.TRC(SCCGWA, AIRMIB.CBAL);
                    if ((AIRMIB.BAL_DIR == 'C' 
                        && AIRMIB.CBAL < 0) 
                        || (AIRMIB.BAL_DIR == 'D' 
                        && AIRMIB.CBAL > 0)) {
                        WS_ERR_MSG = AICMSG_ERROR_MSG.AI_REFUSE_FOR_OVERDRAFT;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
                AICRMIB.OPT = 'N';
                S000_CALL_AIZRMIB();
                if (pgmRtn) return;
            }
            AICRMIB.OPT = 'E';
            S000_CALL_AIZRMIB();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, AIRCMIB.RVS_TYP);
        CEP.TRC(SCCGWA, AICSCMIB.RVS_TYP);
        if (AIRCMIB.RVS_TYP != 'N' 
            && AICSCMIB.RVS_TYP != 'N' 
            && AIRCMIB.RVS_TYP != AICSCMIB.RVS_TYP) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.C_SUS_CANT_CHANGE_EACH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, AICSCMIB.BAL_DIR);
        CEP.TRC(SCCGWA, AICPQITM.OUTPUT_DATA.BAL_SIGN_FLG);
        if ((AICSCMIB.BAL_DIR == 'C' 
            && AICPQITM.OUTPUT_DATA.BAL_SIGN_FLG == 'D') 
            || (AICSCMIB.BAL_DIR == 'D' 
            && AICPQITM.OUTPUT_DATA.BAL_SIGN_FLG == 'C') 
            || (AICSCMIB.BAL_DIR == 'B' 
            && AICPQITM.OUTPUT_DATA.BAL_SIGN_FLG == 'C') 
            || (AICSCMIB.BAL_DIR == 'B' 
            && AICPQITM.OUTPUT_DATA.BAL_SIGN_FLG == 'D')) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.CMIB_DC_CTL_MUST_EQ_GL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (AIRCMIB.RVS_TYP == 'N' 
            && AICSCMIB.RVS_TYP != 'N' 
            && AIRMIB.CBAL != 0) {
            IBS.init(SCCGWA, AICPQITM);
            AICPQITM.INPUT_DATA.NO = AICSCMIB.ITM;
            AICPQITM.INPUT_DATA.BOOK_FLG = AICSCMIB.GL_BOOK;
            S000_CALL_AIZPQITM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, AICSCMIB.RVS_KND);
            if (AICSCMIB.RVS_KND == '2' 
                || AICSCMIB.RVS_KND == '4') {
                WS_RVS_FLG = 'Y';
                CEP.TRC(SCCGWA, WS_RVS_FLG);
            } else {
                WS_ERR_MSG = AICMSG_ERROR_MSG.RVS_KND_WRONG;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (AICSCMIB.RVS_TYP != AIRCMIB.RVS_TYP 
            || AICSCMIB.RVS_KND != AIRCMIB.RVS_KND) {
            WS_RVS_SYN_FLG = 'Y';
        }
        R000_SAVE_OLD_HIS_DATA();
        if (pgmRtn) return;
        IBS.init(SCCGWA, AIRCMIB);
        AICRCMIB.FUNC = 'U';
        AIRCMIB.KEY.GL_BOOK = AICSCMIB.GL_BOOK;
        AIRCMIB.KEY.SEQ = AICSCMIB.SEQ;
        AIRCMIB.KEY.ITM = AICSCMIB.ITM;
        AIRCMIB.KEY.BR = AICSCMIB.BR;
        AIRCMIB.RVS_EXP = AICSCMIB.RVS_EXP;
        AIRCMIB.DRLT_BAL = AICSCMIB.DRLT_BAL;
        AIRCMIB.CRLT_BAL = AICSCMIB.CRLT_BAL;
        AIRCMIB.CHS_NM = AICSCMIB.CHS_NM;
        AIRCMIB.AC_TYP = AICSCMIB.AC_TYP;
        AIRCMIB.CCY_LMT = AICSCMIB.CCY_LMT;
        AIRCMIB.BAL_DIR = AICSCMIB.BAL_DIR;
        AIRCMIB.BAL_RFLG = AICSCMIB.BAL_RFLG;
        AIRCMIB.DTL_FLG = AICSCMIB.DTL_FLG;
        AIRCMIB.RVS_TYP = AICSCMIB.RVS_TYP;
        AIRCMIB.RVS_KND = AICSCMIB.RVS_KND;
        AIRCMIB.RVS_UNIT = AICSCMIB.RVS_UNIT;
        AIRCMIB.AC_EXP = AICSCMIB.AC_EXP;
        AIRCMIB.MANUAL_FLG = AICSCMIB.MANUAL_FLG;
        AIRCMIB.AMT_DIR = AICSCMIB.AMT_DIR;
        AIRCMIB.CARD_FLG = AICSCMIB.CARD_FLG;
        AIRCMIB.HOT_FLG = AICSCMIB.HOT_FLG;
        AIRCMIB.ONL_FLG = AICSCMIB.ONL_FLG;
        AIRCMIB.BAL_CHK = AICSCMIB.BAL_CHK;
        AIRCMIB.APP1 = AICSCMIB.APP1;
        AIRCMIB.APP2 = AICSCMIB.APP2;
        AIRCMIB.APP3 = AICSCMIB.APP3;
        AIRCMIB.APP4 = AICSCMIB.APP4;
        AIRCMIB.APP5 = AICSCMIB.APP5;
        AIRCMIB.APP6 = AICSCMIB.APP6;
        AIRCMIB.APP7 = AICSCMIB.APP7;
        AIRCMIB.APP8 = AICSCMIB.APP8;
        AIRCMIB.APP9 = AICSCMIB.APP9;
        AIRCMIB.APP10 = AICSCMIB.APP10;
        AIRCMIB.EFF_DATE = AICSCMIB.EFF_DATE;
        AIRCMIB.EXP_DATE = AICSCMIB.EXP_DATE;
        AIRCMIB.STS = AICSCMIB.STS;
        AIRCMIB.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AIRCMIB.LAST_UPDATE_TLR = SCCGWA.COMM_AREA.TL_ID;
        AICRCMIB.POINTER = AIRCMIB;
        AICRCMIB.REC_LEN = 407;
        S000_CALL_AIZRCMIB();
        if (pgmRtn) return;
        R000_MOD_MIB_RECORD();
        if (pgmRtn) return;
        R000_SAVE_NEW_HIS_DATA();
        if (pgmRtn) return;
        H000_HISTORY_PROCESS();
        if (pgmRtn) return;
    }
    public void B060_REACTIVE_PROCESS() throws IOException,SQLException,Exception {
        SCCSUBS.AP_CODE = 1;
        SCCSUBS.TR_CODE = 5706;
        S000_SET_SUBS_TRN();
        if (pgmRtn) return;
        R000_READUDP_PROCESS();
        if (pgmRtn) return;
        if (AIRCMIB.STS == 'S') {
            R000_SAVE_OLD_HIS_DATA();
            if (pgmRtn) return;
            AICRCMIB.FUNC = 'U';
            IBS.init(SCCGWA, AICPQITM);
            AICPQITM.INPUT_DATA.NO = AICSCMIB.ITM;
            AICPQITM.INPUT_DATA.BOOK_FLG = AICSCMIB.GL_BOOK;
            S000_CALL_AIZPQITM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, AICPQITM.OUTPUT_DATA.STS);
            if (AICPQITM.OUTPUT_DATA.STS == 'H') {
                WS_CMIB_STS = 'H';
            } else {
                if (AIRCMIB.EFF_DATE <= SCCGWA.COMM_AREA.AC_DATE) {
                    WS_CMIB_STS = 'N';
                } else {
                    WS_CMIB_STS = 'P';
                }
            }
            AIRCMIB.STS = WS_CMIB_STS;
            AIRCMIB.AC_EXP = AICSCMIB.AC_EXP;
            AIRCMIB.EXP_DATE = AICSCMIB.EXP_DATE;
            AIRCMIB.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            AIRCMIB.LAST_UPDATE_TLR = SCCGWA.COMM_AREA.TL_ID;
            AICRCMIB.POINTER = AIRCMIB;
            AICRCMIB.REC_LEN = 407;
            S000_CALL_AIZRCMIB();
            if (pgmRtn) return;
            AICCOMIB.STS = WS_CMIB_STS;
            R000_SAVE_NEW_HIS_DATA();
            if (pgmRtn) return;
            H000_HISTORY_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_CMIB_STS_NOTSTOP;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B070_HOLD_PROCESS() throws IOException,SQLException,Exception {
        R000_READUDP_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AIRCMIB.STS);
        if (AIRCMIB.STS == 'N') {
            R000_SAVE_OLD_HIS_DATA();
            if (pgmRtn) return;
            AICRCMIB.FUNC = 'U';
            AIRCMIB.STS = 'H';
            AIRCMIB.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            AIRCMIB.LAST_UPDATE_TLR = SCCGWA.COMM_AREA.TL_ID;
            AICRCMIB.POINTER = AIRCMIB;
            AICRCMIB.REC_LEN = 407;
            S000_CALL_AIZRCMIB();
            if (pgmRtn) return;
            AICCOMIB.STS = 'H';
            R000_SAVE_NEW_HIS_DATA();
            if (pgmRtn) return;
            H000_HISTORY_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_CMIB_STS_WRONG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B080_UNHOLD_PROCESS() throws IOException,SQLException,Exception {
        SCCSUBS.AP_CODE = 1;
        SCCSUBS.TR_CODE = 5711;
        S000_SET_SUBS_TRN();
        if (pgmRtn) return;
        R000_READUDP_PROCESS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, AICPQITM);
        AICPQITM.INPUT_DATA.NO = AICSCMIB.ITM;
        AICPQITM.INPUT_DATA.BOOK_FLG = AICSCMIB.GL_BOOK;
        S000_CALL_AIZPQITM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICPQITM.OUTPUT_DATA.STS);
        if (AICPQITM.OUTPUT_DATA.STS == 'H') {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_ITM_STS_HOLD;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (AIRCMIB.STS == 'H') {
            R000_SAVE_OLD_HIS_DATA();
            if (pgmRtn) return;
            AICRCMIB.FUNC = 'U';
            AIRCMIB.STS = 'N';
            AIRCMIB.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            AIRCMIB.LAST_UPDATE_TLR = SCCGWA.COMM_AREA.TL_ID;
            AICRCMIB.POINTER = AIRCMIB;
            AICRCMIB.REC_LEN = 407;
            S000_CALL_AIZRCMIB();
            if (pgmRtn) return;
            R000_SAVE_NEW_HIS_DATA();
            if (pgmRtn) return;
            H000_HISTORY_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_CMIB_STS_NOTHOLD;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B090_STOP_PROCESS() throws IOException,SQLException,Exception {
        SCCSUBS.AP_CODE = 1;
        SCCSUBS.TR_CODE = 5703;
        S000_SET_SUBS_TRN();
        if (pgmRtn) return;
        IBS.init(SCCGWA, AICRCMIB);
        IBS.init(SCCGWA, AIRCMIB);
        AICRCMIB.FUNC = 'B';
        AICRCMIB.OPT = 'I';
        AIRCMIB.KEY.GL_BOOK = AICSCMIB.GL_BOOK;
        AIRCMIB.KEY.ITM = AICSCMIB.ITM;
        AICRCMIB.POINTER = AIRCMIB;
        AICRCMIB.REC_LEN = 407;
        S000_CALL_AIZRCMIB();
        if (pgmRtn) return;
        AICRCMIB.OPT = 'N';
        while (AICRCMIB.RETURN_INFO != 'N') {
            if (AICSCMIB.SEQ == AIRCMIB.KEY.SEQ 
                && AIRCMIB.KEY.BR != 999999) {
                WS_CNT1 = WS_CNT1 + 1;
                if (WS_CNT1 == 999999) {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.EXPECIAL_BR_REC_TOO_MUC;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, WS_CNT1);
                WS_DETAIL_CMIB = WS_DETAIL_CMIB.get(WS_CNT1-1);
                WS_DETAIL_CMIB.WS_DETAIL_BR = AIRCMIB.KEY.BR;
                WS_DETAIL_CMIB.set(WS_CNT1-1, WS_DETAIL_CMIB);
                CEP.TRC(SCCGWA, WS_DETAIL_CMIB.get(WS_CNT1-1).WS_DETAIL_BR);
            }
            AICRCMIB.OPT = 'N';
            S000_CALL_AIZRCMIB();
            if (pgmRtn) return;
        }
        AICRCMIB.OPT = 'E';
        S000_CALL_AIZRCMIB();
        if (pgmRtn) return;
        R000_READUDP_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AIRCMIB.STS);
        CEP.TRC(SCCGWA, AIRCMIB.ONL_FLG);
        if (AIRCMIB.ONL_FLG == 'N' 
            && (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT"))) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_NOT_STOP_CMIB;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (AIRCMIB.STS == 'N' 
            || AIRCMIB.STS == 'H' 
            || (AIRCMIB.STS == 'P' 
            && (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")))) {
            R000_SAVE_OLD_HIS_DATA();
            if (pgmRtn) return;
            AICRCMIB.FUNC = 'U';
            AIRCMIB.STS = 'S';
            if (AIRCMIB.HOT_FLG == 'H') {
                CEP.TRC(SCCGWA, AIRCMIB.HOT_FLG);
                AIRCMIB.HOT_FLG = 'N';
            }
            AIRCMIB.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            AIRCMIB.LAST_UPDATE_TLR = SCCGWA.COMM_AREA.TL_ID;
            AICRCMIB.POINTER = AIRCMIB;
            AICRCMIB.REC_LEN = 407;
            S000_CALL_AIZRCMIB();
            if (pgmRtn) return;
            R000_MOD_MIB_RECORD();
            if (pgmRtn) return;
            R000_SAVE_NEW_HIS_DATA();
            if (pgmRtn) return;
            H000_HISTORY_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_CMIB_STS_WRONG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_READUDP_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRCMIB);
        IBS.init(SCCGWA, AICRCMIB);
        IBS.init(SCCGWA, AICOCMIB);
        IBS.init(SCCGWA, AICCOMIB);
        AICRCMIB.FUNC = 'R';
        CEP.TRC(SCCGWA, AICSCMIB.GL_BOOK);
        CEP.TRC(SCCGWA, AICSCMIB.ITM);
        CEP.TRC(SCCGWA, AICSCMIB.BR);
        CEP.TRC(SCCGWA, AICSCMIB.SEQ);
        AIRCMIB.KEY.GL_BOOK = AICSCMIB.GL_BOOK;
        AIRCMIB.KEY.ITM = AICSCMIB.ITM;
        AIRCMIB.KEY.BR = AICSCMIB.BR;
        AIRCMIB.KEY.SEQ = AICSCMIB.SEQ;
        AICRCMIB.POINTER = AIRCMIB;
        AICRCMIB.REC_LEN = 407;
        S000_CALL_AIZRCMIB();
        if (pgmRtn) return;
        if (AICRCMIB.RETURN_INFO == 'N') {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_AITCMIB_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_MOD_MIB_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICRMIB);
        IBS.init(SCCGWA, AIRMIB);
        AICRMIB.FUNC = 'B';
        AICRMIB.OPT = '2';
        if (AICSCMIB.SEQ == 999999) {
            AICRMIB.OPT = '9';
        }
        CEP.TRC(SCCGWA, AICRMIB.OPT);
        AIRMIB.KEY.GL_BOOK = AICSCMIB.GL_BOOK;
        AIRMIB.KEY.ITM_NO = AICSCMIB.ITM;
        if (AICSCMIB.BR != 999999) {
            AIRMIB.KEY.BR = AICSCMIB.BR;
        }
        AIRMIB.KEY.SEQ = AICSCMIB.SEQ;
        CEP.TRC(SCCGWA, AIRMIB.KEY.GL_BOOK);
        CEP.TRC(SCCGWA, AIRMIB.KEY.ITM_NO);
        CEP.TRC(SCCGWA, AIRMIB.APP1);
        CEP.TRC(SCCGWA, AIRMIB.APP2);
        CEP.TRC(SCCGWA, AIRMIB.APP3);
        CEP.TRC(SCCGWA, AIRMIB.APP4);
        CEP.TRC(SCCGWA, AIRMIB.APP5);
        CEP.TRC(SCCGWA, AIRMIB.KEY.BR);
        CEP.TRC(SCCGWA, AIRMIB.KEY.SEQ);
        S000_CALL_AIZRMIB();
        if (pgmRtn) return;
        AICRMIB.OPT = 'N';
        S000_CALL_AIZRMIB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICRMIB.RETURN_INFO);
        while (AICRMIB.RETURN_INFO != 'N') {
            WS_DEAL_FLG = ' ';
            CEP.TRC(SCCGWA, AICSCMIB.BR);
            CEP.TRC(SCCGWA, "QWE");
            CEP.TRC(SCCGWA, AIRMIB.AC_NO);
            if (AICSCMIB.BR == 999999 
                && WS_CNT1 > 0) {
                for (WS_K = 1; WS_K <= WS_CNT1 
                    && WS_DEAL_FLG != 'N'; WS_K += 1) {
                    if (AIRMIB.KEY.BR == WS_DETAIL_CMIB.get(WS_CNT1-1).WS_DETAIL_BR) {
                        WS_DEAL_FLG = 'N';
                    } else {
                        CEP.TRC(SCCGWA, "QWE");
                        WS_DEAL_FLG = 'Y';
                    }
                }
            } else {
                WS_DEAL_FLG = 'Y';
            }
            CEP.TRC(SCCGWA, "DD666");
            CEP.TRC(SCCGWA, WS_DEAL_FLG);
            if (WS_DEAL_FLG == 'Y') {
                R000_TRANS_DEAL_RECORD();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, WS_RVS_FLG);
            CEP.TRC(SCCGWA, AIRMIB.CBAL);
            if (WS_RVS_FLG == 'Y' 
                && AIRMIB.CBAL != 0 
                && ((AIRMIB.RVS_TYP == 'C' 
                && AIRMIB.CBAL > 0) 
                || (AIRMIB.RVS_TYP == 'D' 
                && AIRMIB.CBAL < 0)) 
                && WS_DEAL_FLG == 'Y') {
                if (AIRMIB.RVS_TYP == 'D') {
                    AIRMIB.CBAL = AIRMIB.CBAL * ( -1 );
                }
                R000_TRANS_TO_PRVS();
                if (pgmRtn) return;
            }
            AICRMIB.FUNC = 'B';
            AICRMIB.OPT = 'N';
            S000_CALL_AIZRMIB();
            if (pgmRtn) return;
        }
        AICRMIB.OPT = 'E';
        S000_CALL_AIZRMIB();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DEAL_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICRMIB);
        IBS.init(SCCGWA, AICSMIB);
        if (AICSCMIB.FUNC == 'S') {
            AICSMIB.FUNC = 'S';
            AICSMIB.GL_BOOK = AIRMIB.KEY.GL_BOOK;
            AICSMIB.ITM_NO = AIRMIB.KEY.ITM_NO;
            AICSMIB.BR = AIRMIB.KEY.BR;
            AICSMIB.SEQ = AIRMIB.KEY.SEQ;
            AICSMIB.CCY = AIRMIB.KEY.CCY;
            CEP.TRC(SCCGWA, AICSMIB.GL_BOOK);
            CEP.TRC(SCCGWA, AICSMIB.ITM_NO);
            CEP.TRC(SCCGWA, AICSMIB.BR);
            CEP.TRC(SCCGWA, AICSMIB.SEQ);
            CEP.TRC(SCCGWA, AICSMIB.CCY);
            S000_CALL_AIZSMIB();
            if (pgmRtn) return;
        }
        if (AICSCMIB.FUNC == 'M') {
            AICRMIB.FUNC = 'R';
            S000_CALL_AIZRMIB();
            if (pgmRtn) return;
            if (AICRMIB.RETURN_INFO == 'N') {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_AITMIB_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            AICRMIB.FUNC = 'U';
            if (AICSCMIB.SEQ != 999999) {
                AIRMIB.CHS_NM = AICSCMIB.CHS_NM;
            }
            AIRMIB.ONL_FLG = AICSCMIB.ONL_FLG;
            AIRMIB.BAL_RFLG = AICSCMIB.BAL_RFLG;
            AIRMIB.BAL_CHK = AICSCMIB.BAL_CHK;
            AIRMIB.AMT_DIR = AICSCMIB.AMT_DIR;
            AIRMIB.MANUAL_FLG = AICSCMIB.MANUAL_FLG;
            AIRMIB.CARD_FLG = AICSCMIB.CARD_FLG;
            if (AICSCMIB.HOT_FLG == 'N' 
                && AIRMIB.HOT_FLG == 'H') {
                CEP.TRC(SCCGWA, AIRMIB.HOT_FLG);
                IBS.init(SCCGWA, AIRMIBH);
                IBS.init(SCCGWA, AICRMIBH);
                AIRMIBH.KEY.GL_BOOK = AIRMIB.KEY.GL_BOOK;
                AIRMIBH.KEY.BR = AIRMIB.KEY.BR;
                AIRMIBH.KEY.CCY = AIRMIB.KEY.CCY;
                AIRMIBH.KEY.ITM_NO = AIRMIB.KEY.ITM_NO;
                AIRMIBH.KEY.SEQ = AIRMIB.KEY.SEQ;
                CEP.TRC(SCCGWA, AIRMIBH.KEY.GL_BOOK);
                CEP.TRC(SCCGWA, AIRMIBH.KEY.BR);
                CEP.TRC(SCCGWA, AIRMIBH.KEY.CCY);
                CEP.TRC(SCCGWA, AIRMIBH.KEY.ITM_NO);
                CEP.TRC(SCCGWA, AIRMIBH.KEY.SEQ);
                AICRMIBH.FUNC = 'S';
                AICRMIBH.POINTER = AIRMIBH;
                AICRMIBH.REC_LEN = 637;
                S000_CALL_AIZRMIBH();
                if (pgmRtn) return;
                if (AICRMIBH.RETURN_INFO == 'F') {
                    CEP.TRC(SCCGWA, AICRMIBH.LBAL_SUM);
                    CEP.TRC(SCCGWA, AICRMIBH.CBAL_SUM);
                    AIRMIB.LBAL = AICRMIBH.LBAL_SUM;
                    AIRMIB.CBAL = AICRMIBH.CBAL_SUM;
                    IBS.init(SCCGWA, AIRMIBH);
                    IBS.init(SCCGWA, AICRMIBH);
                    AIRMIBH.KEY.GL_BOOK = AIRMIB.KEY.GL_BOOK;
                    AIRMIBH.KEY.BR = AIRMIB.KEY.BR;
                    AIRMIBH.KEY.CCY = AIRMIB.KEY.CCY;
                    AIRMIBH.KEY.ITM_NO = AIRMIB.KEY.ITM_NO;
                    AIRMIBH.KEY.SEQ = AIRMIB.KEY.SEQ;
                    AICRMIBH.FUNC = 'D';
                    AICRMIBH.POINTER = AIRMIBH;
                    AICRMIBH.REC_LEN = 637;
                    S000_CALL_AIZRMIBH();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, AICSCMIB.HOT_FLG);
                AIRMIB.HOT_FLG = AICSCMIB.HOT_FLG;
            }
            if (AICSCMIB.HOT_FLG == 'H' 
                && AIRMIB.HOT_FLG == 'N') {
                AIRMIB.HOT_FLG = AICSCMIB.HOT_FLG;
                T000_WRITE_AITMIBH();
                if (pgmRtn) return;
                AIRMIB.CBAL = 0;
            }
            AIRMIB.DTL_FLG = AICSCMIB.DTL_FLG;
            CEP.TRC(SCCGWA, WS_ACEXP_MOD_FLG);
            CEP.TRC(SCCGWA, AICSCMIB.AC_EXP);
            if (WS_ACEXP_MOD_FLG == 'Y') {
                AIRMIB.AC_EXP = AICSCMIB.AC_EXP;
            }
            AIRMIB.BAL_DIR = AICSCMIB.BAL_DIR;
            CEP.TRC(SCCGWA, AICSCMIB.RVS_UNIT);
            CEP.TRC(SCCGWA, WS_RVS_SYN_FLG);
            if (WS_RVS_SYN_FLG == 'Y') {
                AIRMIB.RVS_TYP = AICSCMIB.RVS_TYP;
                AIRMIB.RVS_KND = AICSCMIB.RVS_KND;
                AIRMIB.RVS_EXP = AICSCMIB.RVS_EXP;
                AIRMIB.RVS_UNIT = AICSCMIB.RVS_UNIT;
            }
            AIRMIB.APP1 = AICSCMIB.APP1;
            AIRMIB.APP2 = AICSCMIB.APP2;
            AIRMIB.APP3 = AICSCMIB.APP3;
            AIRMIB.APP4 = AICSCMIB.APP4;
            AIRMIB.APP5 = AICSCMIB.APP5;
            AIRMIB.APP6 = AICSCMIB.APP6;
            AIRMIB.APP7 = AICSCMIB.APP7;
            AIRMIB.APP8 = AICSCMIB.APP8;
            AIRMIB.APP9 = AICSCMIB.APP9;
            AIRMIB.APP10 = AICSCMIB.APP10;
            CEP.TRC(SCCGWA, AIRMIB.KEY.GL_BOOK);
            CEP.TRC(SCCGWA, AIRMIB.KEY.ITM_NO);
            CEP.TRC(SCCGWA, AIRMIB.KEY.BR);
            CEP.TRC(SCCGWA, AIRMIB.KEY.SEQ);
            CEP.TRC(SCCGWA, AIRMIB.KEY.CCY);
            CEP.TRC(SCCGWA, AIRMIB.APP1);
            CEP.TRC(SCCGWA, AIRMIB.APP2);
            CEP.TRC(SCCGWA, AIRMIB.APP3);
            CEP.TRC(SCCGWA, AIRMIB.APP4);
            CEP.TRC(SCCGWA, AIRMIB.APP5);
            S000_CALL_AIZRMIB();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_AITMIBH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRMIBH);
        AIRMIBH.KEY.PART_NO = 1;
        AIRMIBH.KEY.GL_BOOK = AIRMIB.KEY.GL_BOOK;
        AIRMIBH.KEY.BR = AIRMIB.KEY.BR;
        AIRMIBH.KEY.ITM_NO = AIRMIB.KEY.ITM_NO;
        AIRMIBH.KEY.SEQ = AIRMIB.KEY.SEQ;
        AIRMIBH.KEY.CCY = AIRMIB.KEY.CCY;
        AIRMIBH.AC_NO = AIRMIB.AC_NO;
        AIRMIBH.ENG_NM = AIRMIB.ENG_NM;
        AIRMIBH.CHS_NM = AIRMIB.CHS_NM;
        AIRMIBH.STS = AIRMIB.STS;
        AIRMIBH.MAX_SEQ = AIRMIB.MAX_SEQ;
        AIRMIBH.AC_TYP = AIRMIB.AC_TYP;
        AIRMIBH.CCY_LMT = AIRMIB.CCY_LMT;
        AIRMIBH.BAL_DIR = AIRMIB.BAL_DIR;
        AIRMIBH.BAL_RFLG = AIRMIB.BAL_RFLG;
        AIRMIBH.AMT_DIR = AIRMIB.AMT_DIR;
        AIRMIBH.DTL_FLG = AIRMIB.DTL_FLG;
        AIRMIBH.RVS_TYP = AIRMIB.RVS_TYP;
        AIRMIBH.RVS_KND = AIRMIB.RVS_KND;
        AIRMIBH.RVS_EXP = AIRMIB.RVS_EXP;
        AIRMIBH.RVS_UNIT = AIRMIB.RVS_UNIT;
        AIRMIBH.AC_EXP = AIRMIB.AC_EXP;
        AIRMIBH.MANUAL_FLG = AIRMIB.MANUAL_FLG;
        AIRMIBH.ONL_FLG = AIRMIB.ONL_FLG;
        AIRMIBH.CARD_FLG = AIRMIB.CARD_FLG;
        AIRMIBH.HOT_FLG = AIRMIB.HOT_FLG;
        AIRMIBH.DRLT_BAL = AIRMIB.DRLT_BAL;
        AIRMIBH.CRLT_BAL = AIRMIB.CRLT_BAL;
        AIRMIBH.BAL_CHK = AIRMIB.BAL_CHK;
        AIRMIBH.APP1 = AIRMIB.APP1;
        AIRMIBH.APP2 = AIRMIB.APP2;
        AIRMIBH.APP3 = AIRMIB.APP3;
        AIRMIBH.APP4 = AIRMIB.APP4;
        AIRMIBH.APP5 = AIRMIB.APP5;
        AIRMIBH.APP6 = AIRMIB.APP6;
        AIRMIBH.APP7 = AIRMIB.APP7;
        AIRMIBH.APP8 = AIRMIB.APP8;
        AIRMIBH.APP9 = AIRMIB.APP9;
        AIRMIBH.APP10 = AIRMIB.APP10;
        AIRMIBH.CLN_REN = AIRMIB.CLN_REN;
        AIRMIBH.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AIRMIBH.CLS_DATE = 0;
        AIRMIBH.OPEN_TLR = SCCGWA.COMM_AREA.TL_ID;
        AIRMIBH.CLS_TLR = " ";
        AIRMIBH.LAST_TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        AIRMIBH.LBAL = AIRMIB.LBAL;
        AIRMIBH.CBAL = AIRMIB.CBAL;
        AIRMIBH.ADDR = AIRMIB.ADDR;
        AIRMIBH.LAST_UPDATE_TLR = SCCGWA.COMM_AREA.TL_ID;
        AIRMIBH.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AIRMIBH.TS = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = AIRMIBH.TS.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) AIRMIBH.TS = "0" + AIRMIBH.TS;
        AITMIBH_RD = new DBParm();
        AITMIBH_RD.TableName = "AITMIBH";
        AITMIBH_RD.errhdl = true;
        IBS.WRITE(SCCGWA, AIRMIBH, AITMIBH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_MIBH_ALREADY_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITMIBH";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZPQITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-ITM", AICPQITM);
        CEP.TRC(SCCGWA, AICPQITM.RC);
        CEP.TRC(SCCGWA, AICPQITM.OUTPUT_DATA.BAL_SIGN_FLG);
        if (AICPQITM.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPQITM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_TRANS_TO_PRVS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIRMIB.KEY.GL_BOOK);
        CEP.TRC(SCCGWA, AIRMIB.AC_NO);
        CEP.TRC(SCCGWA, AIRMIB.KEY.CCY);
        CEP.TRC(SCCGWA, AIRMIB.RVS_TYP);
        CEP.TRC(SCCGWA, AIRMIB.CBAL);
        IBS.init(SCCGWA, AICUPRVS);
        AICUPRVS.DATA.FUNC = 'C';
        AICUPRVS.DATA.GL_BOOK = AIRMIB.KEY.GL_BOOK;
        AICUPRVS.DATA.AC = AIRMIB.AC_NO;
        AICUPRVS.DATA.CCY = AIRMIB.KEY.CCY;
        AICUPRVS.DATA.SIGN = AIRMIB.RVS_TYP;
        AICUPRVS.DATA.AMT = AIRMIB.CBAL;
        AICUPRVS.DATA.TR_TELLER = SCCGWA.COMM_AREA.TL_ID;
        AICUPRVS.DATA.TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        AICUPRVS.DATA.VAL_DT = SCCGWA.COMM_AREA.AC_DATE;
        AICUPRVS.DATA.TR_CODE = "0015704";
        AICUPRVS.DATA.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        AICUPRVS.DATA.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUPRVS.DATA.APP = "AI";
        S000_CALL_AIZUPRVS();
        if (pgmRtn) return;
        if (AICUPRVS.RC.RC_CODE == 0) {
            AICUPRVS.DATA.FUNC = 'P';
            S000_CALL_AIZUPRVS();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZUPRVS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-PROC-RVS", AICUPRVS);
        CEP.TRC(SCCGWA, AICUPRVS.RC);
        if (AICUPRVS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICUPRVS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZRMIBH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-R-MAIN-MIBH", AICRMIBH);
        CEP.TRC(SCCGWA, AICRMIBH.RC);
        CEP.TRC(SCCGWA, AICRMIBH.RC.RC_CODE);
    }
    public void R000_SAVE_OLD_HIS_DATA() throws IOException,SQLException,Exception {
        AICOCMIB.STS = AIRCMIB.STS;
        AICOCMIB.SEQ = AIRCMIB.KEY.SEQ;
        AICOCMIB.ITM = AIRCMIB.KEY.ITM;
        AICOCMIB.BR = AIRCMIB.KEY.BR;
    }
    public void R000_SAVE_NEW_HIS_DATA() throws IOException,SQLException,Exception {
        AICCOMIB.SEQ = AIRCMIB.KEY.SEQ;
        AICCOMIB.ITM = AIRCMIB.KEY.ITM;
        AICCOMIB.BR = AIRCMIB.KEY.BR;
    }
    public void B070_TRANS_DATA_BRW_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_BRW_OUTPUT);
        WS_BRW_OUTPUT.WS_BRW_OUT_BR = AIRCMIB.KEY.BR;
        WS_BRW_OUTPUT.WS_BRW_OUT_ITM = AIRCMIB.KEY.ITM;
        WS_BRW_OUTPUT.WS_BRW_OUT_SEQ = AIRCMIB.KEY.SEQ;
        WS_BRW_OUTPUT.WS_BRW_OUT_NAME = AIRCMIB.CHS_NM;
        WS_BRW_OUTPUT.WS_BRW_OUT_STS = AIRCMIB.STS;
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_OUT_STS);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_OUT_BR);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_OUT_ITM);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_OUT_SEQ);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_OUT_NAME);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_BRW_OUTPUT);
        SCCMPAG.DATA_LEN = 185;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B020_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 185;
        SCCMPAG.SCR_ROW_CNT = 20;
        SCCMPAG.SCR_COL_CNT = 5;
        B_MPAG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, AICRCMIB);
        IBS.init(SCCGWA, AIRCMIB);
        AICRCMIB.FUNC = 'B';
        if (AICSCMIB.ITM.trim().length() > 0) {
            AICRCMIB.OPT = 'R';
        } else {
            AICRCMIB.OPT = 'C';
        }
        if (AICSCMIB.BR == 999999) {
            AICRCMIB.OPT = 'A';
        } else {
            AICRCMIB.OPT = 'O';
        }
        CEP.TRC(SCCGWA, AICRCMIB.OPT);
        AIRCMIB.KEY.GL_BOOK = AICSCMIB.GL_BOOK;
        AIRCMIB.KEY.ITM = AICSCMIB.ITM;
        AIRCMIB.KEY.BR = AICSCMIB.BR;
        AIRCMIB.KEY.SEQ = AICSCMIB.SEQ;
        CEP.TRC(SCCGWA, AICSCMIB.CHS_NM);
        AIRCMIB.CHS_NM = AICSCMIB.CHS_NM;
        AICRCMIB.POINTER = AIRCMIB;
        AICRCMIB.REC_LEN = 407;
        S000_CALL_AIZRCMIB();
        if (pgmRtn) return;
        AICRCMIB.OPT = 'N';
        S000_CALL_AIZRCMIB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICRCMIB.RETURN_INFO);
        if (AICRCMIB.RETURN_INFO == 'N') {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_AITCMIB_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        while (AICRCMIB.RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E') {
            B070_TRANS_DATA_BRW_OUTPUT();
            if (pgmRtn) return;
            AICRCMIB.OPT = 'N';
            S000_CALL_AIZRCMIB();
            if (pgmRtn) return;
        }
        AICRCMIB.OPT = 'E';
        S000_CALL_AIZRCMIB();
        if (pgmRtn) return;
    }
    public void H000_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        if (AICSCMIB.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
            BPCPNHIS.INFO.TX_RMK = "ADD AITCMIB DEFINITION";
        } else if ((AICSCMIB.FUNC == 'M' 
                || AICSCMIB.FUNC == 'H' 
                || AICSCMIB.FUNC == 'U' 
                || AICSCMIB.FUNC == 'R' 
                || AICSCMIB.FUNC == 'S')) {
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.TX_RMK = "CHANGE AITCMIB DEFINITION";
        } else {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID = "AICOCMIB";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.FMT_ID_LEN = 270;
        BPCPNHIS.INFO.OLD_DAT_PT = AICOCMIB;
        BPCPNHIS.INFO.NEW_DAT_PT = AICCOMIB;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_AIZRCMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-R-MAIN-CMIB", AICRCMIB);
        if (AICRCMIB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICRCMIB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZSMIB() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "AI-S-MAIN-MIB";
        SCCCALL.COMMAREA_PTR = AICSMIB;
        SCCCALL.ERRHDL_FLG = 'Y';
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_CALL_AIZRMIB() throws IOException,SQLException,Exception {
        AICRMIB.POINTER = AIRMIB;
        AICRMIB.REC_LEN = 634;
        IBS.CALLCPN(SCCGWA, "AI-R-MAIN-MIB", AICRMIB);
    }
    public void S000_CALL_AIZRGINF() throws IOException,SQLException,Exception {
        AICRGINF.INFO.POINTER = AIRGINF;
        AICRGINF.INFO.LEN = 242;
        IBS.CALLCPN(SCCGWA, "AI-R-MAIN-GINF", AICRGINF);
        if (AICRGINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICRGINF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-SET-SUBS-TRANS", SCCSUBS);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
