package com.hisun.AI;

import java.util.ArrayList;
import java.util.List;
import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class AIZSHMIB {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    brParm BPTVCHT_BR = new brParm();
    DBParm BPTVCHT_RD;
    boolean pgmRtn = false;
    String TBL_BPTPARM = "BPTPARM";
    int K_OUTPUT_CNT = 4999;
    int K_OUTPUT_MAX = 999999999;
    String TBL_BPTVCHT = "BPTVCHT";
    String CPN_P_INQ_ORG_LOW = "BP-P-INQ-ORG-LOW";
    int WS_END_DT = 0;
    double WS_END_AMT = 0;
    short WS_PART_NO = 0;
    List<AIZSHMIB_WS_BR_DATA> WS_BR_DATA = new ArrayList<AIZSHMIB_WS_BR_DATA>();
    String WS_ERR_MSG = " ";
    int WS_COUNT = 0;
    int WS_COUNT_OUTPUT = 0;
    int WS_CNT = 0;
    int WS_CNT1 = 0;
    int WS_I = 0;
    int WS_Z = 0;
    int WS_LAST_BR = 0;
    int WS_J = 0;
    String WS_AC = " ";
    int WS_TMP_BR = 0;
    int WS_TMP_BBR = 0;
    AIZSHMIB_WS_BRW_OUTPUT WS_BRW_OUTPUT = new AIZSHMIB_WS_BRW_OUTPUT();
    AIZSHMIB_WS_AC_NO WS_AC_NO = new AIZSHMIB_WS_AC_NO();
    int WS_ACSEQ = 0;
    double WS_OPEN_AMT = 0;
    String WS_VCHT_CCY = " ";
    List<AIZSHMIB_WS_BR_DATA> WS_BR_DATA = new ArrayList<AIZSHMIB_WS_BR_DATA>();
    char WS_ONL_FLG = ' ';
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPRPARM BPRPARM = new BPRPARM();
    AIRPAI1 AIRPAI1 = new AIRPAI1();
    AIRHMIB AIRHMIB = new AIRHMIB();
    AIRMIBA AIRMIBA = new AIRMIBA();
    AICRMIBA AICRMIBA = new AICRMIBA();
    SCCSUBS SCCSUBS = new SCCSUBS();
    AICRHMIB AICRHMIB = new AICRHMIB();
    BPCPORLO BPCPORLO = new BPCPORLO();
    AICUIANO AICUIANO = new AICUIANO();
    AICPQMIB AICPQMIB = new AICPQMIB();
    AIRCMIB AIRCMIB = new AIRCMIB();
    AICPCMIB AICPCMIB = new AICPCMIB();
    BPRVCHT BPRVCHT = new BPRVCHT();
    BPCTVCHT BPCTVCHT = new BPCTVCHT();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    BPCPORUP_DATA_INFO BPCPORUP;
    AICSHMIB AICSHMIB;
    public void MP(SCCGWA SCCGWA, AICSHMIB AICSHMIB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICSHMIB = AICSHMIB;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, "PROGRAM AIZSHMIB PROCESS START!");
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "PROGRAM AIZSHMIB END SUCCESSFULLY!");
        CEP.TRC(SCCGWA, "AIZSHMIB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        IBS.init(SCCGWA, BPCTVCHT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICSHMIB.FUNC);
        if (AICSHMIB.FUNC == 'B') {
            CEP.TRC(SCCGWA, "111111111");
            B020_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else if (AICSHMIB.FUNC == 'Q') {
            CEP.TRC(SCCGWA, "QUERY-PROCESS START!");
            B030_QUERY_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_INPUT_ERROR;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRCMIB);
        IBS.init(SCCGWA, AICPCMIB);
        AIRCMIB.KEY.GL_BOOK = AICSHMIB.GL_BOOK;
        if (AICSHMIB.FUNC == 'B') {
            AIRCMIB.KEY.BR = AICSHMIB.BR;
            AIRCMIB.KEY.ITM = AICSHMIB.ITM;
            AIRCMIB.KEY.SEQ = AICSHMIB.SEQ;
        } else {
            IBS.CPY2CLS(SCCGWA, AICSHMIB.AC, WS_AC_NO);
            AIRCMIB.KEY.BR = WS_AC_NO.WS_BR;
            AIRCMIB.KEY.ITM = WS_AC_NO.WS_ITM;
            AIRCMIB.KEY.SEQ = WS_AC_NO.WS_AC_SEQ;
            AICSHMIB.SEQ = WS_AC_NO.WS_AC_SEQ;
            CEP.TRC(SCCGWA, AICSHMIB.AC);
            CEP.TRC(SCCGWA, AIRCMIB.KEY.BR);
        }
        S000_CALL_AIZPCMIB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AIRCMIB.ONL_FLG);
        if (AIRCMIB.ONL_FLG == 'N') {
            WS_ONL_FLG = 'N';
        }
    }
    public void R000_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_BRW_OUTPUT);
        WS_BRW_OUTPUT.WS_BRW_TRDATE = AIRHMIB.KEY.TR_DATE;
        WS_BRW_OUTPUT.WS_BRW_TRTIME = AIRHMIB.TR_TIME;
        WS_BRW_OUTPUT.WS_BRW_BR = AIRHMIB.BR;
        WS_BRW_OUTPUT.WS_BRW_ITM = AIRHMIB.ITM;
        WS_BRW_OUTPUT.WS_BRW_SEQ = AIRHMIB.SEQ;
        WS_BRW_OUTPUT.WS_BRW_VALDATE = AIRHMIB.VAL_DATE;
        WS_BRW_OUTPUT.WS_BRW_CCY = AIRHMIB.CCY;
        WS_BRW_OUTPUT.WS_BRW_SIGN = AIRHMIB.SIGN;
        WS_BRW_OUTPUT.WS_BRW_AMT = AIRHMIB.AMT;
        WS_BRW_OUTPUT.WS_BRW_AC = AIRHMIB.KEY.AC;
        CEP.TRC(SCCGWA, AIRHMIB.KEY.SET_NO);
        CEP.TRC(SCCGWA, AIRHMIB.KEY.SET_SEQ);
        WS_BRW_OUTPUT.WS_BRW_AFTAMT = AIRHMIB.AFT_AMT;
        WS_BRW_OUTPUT.WS_BRW_TRCODE = AIRHMIB.TR_CODE;
        WS_BRW_OUTPUT.WS_BRW_TRBR = AIRHMIB.TR_BR;
        WS_BRW_OUTPUT.WS_BRW_TRTELLER = AIRHMIB.TR_TELLER;
        WS_BRW_OUTPUT.WS_BRW_DESC = AIRHMIB.DESC;
        WS_BRW_OUTPUT.WS_BRW_TMNO = AIRHMIB.TM_NO;
        WS_BRW_OUTPUT.WS_BRW_CHNLNO = AIRHMIB.CHNL_NO;
        WS_BRW_OUTPUT.WS_BRW_RVSNO = AIRHMIB.RVS_NO;
        WS_BRW_OUTPUT.WS_BRW_RVSSEQ = AIRHMIB.RVS_SEQ;
        WS_BRW_OUTPUT.WS_BRW_ECIND = AIRHMIB.EC_IND;
        WS_BRW_OUTPUT.WS_BRW_ODEFLG = AIRHMIB.ODE_FLG;
        WS_BRW_OUTPUT.WS_BRW_OTHSYSKEY = AIRHMIB.OTHSYS_KEY;
        WS_BRW_OUTPUT.WS_BRW_CREVNO = AIRHMIB.CREV_NO;
        WS_BRW_OUTPUT.WS_BRW_SETNO = AIRHMIB.KEY.SET_NO;
        WS_BRW_OUTPUT.WS_BRW_SETSEQ = AIRHMIB.KEY.SET_SEQ;
        WS_BRW_OUTPUT.WS_BRW_THEIR_AC = AIRHMIB.THEIR_AC;
        WS_BRW_OUTPUT.WS_BRW_PAY_MAN = AIRHMIB.PAY_MAN;
        CEP.TRC(SCCGWA, AIRHMIB.PAY_MAN);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_PAY_MAN);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_TRDATE);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_TRTIME);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_VALDATE);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_VALDATE);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_BR);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_ITM);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_SEQ);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_CCY);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_SIGN);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_AMT);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_AFTAMT);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_TRCODE);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_TRBR);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_TRTELLER);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_DESC);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_TMNO);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_CHNLNO);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_RVSNO);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_RVSSEQ);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_ECIND);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_ODEFLG);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_OTHSYSKEY);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_CREVNO);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_SETNO);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_SETSEQ);
        CEP.TRC(SCCGWA, AIRHMIB.THEIR_AC);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_THEIR_AC);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT);
    }
    public void B030_QUERY_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AICSHMIB.SET_NO);
        CEP.TRC(SCCGWA, AICSHMIB.SET_SEQ);
        IBS.init(SCCGWA, AIRHMIB);
        IBS.init(SCCGWA, AICRHMIB);
        AICRHMIB.FUNC = 'Q';
        AIRHMIB.KEY.BOOK_FLG = AICSHMIB.GL_BOOK;
        AIRHMIB.KEY.AC = AICSHMIB.AC;
        IBS.init(SCCGWA, BPCPQORG);
        if (AIRHMIB.KEY.AC == null) AIRHMIB.KEY.AC = "";
        JIBS_tmp_int = AIRHMIB.KEY.AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) AIRHMIB.KEY.AC += " ";
        if (AIRHMIB.KEY.AC.substring(0, 6).trim().length() == 0) BPCPQORG.BR = 0;
        else BPCPQORG.BR = Integer.parseInt(AIRHMIB.KEY.AC.substring(0, 6));
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        WS_TMP_BBR = BPCPQORG.BBR;
        if (BPCPQORG.ATTR == '3') {
            if (BPCPQORG.BBR != 0) {
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BR = WS_TMP_BBR;
                S000_CALL_BPZPQORG();
                if (pgmRtn) return;
                if (BPCPQORG.ATTR == '2') {
                    if (AIRHMIB.KEY.AC == null) AIRHMIB.KEY.AC = "";
                    JIBS_tmp_int = AIRHMIB.KEY.AC.length();
                    for (int i=0;i<32-JIBS_tmp_int;i++) AIRHMIB.KEY.AC += " ";
                    JIBS_tmp_str[0] = "" + BPCPQORG.BBR;
                    JIBS_tmp_int = JIBS_tmp_str[0].length();
                    for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                    AIRHMIB.KEY.AC = JIBS_tmp_str[0] + AIRHMIB.KEY.AC.substring(6);
                }
            }
        }
        AIRHMIB.BR = AICSHMIB.BR;
        AIRHMIB.CCY = AICSHMIB.CCY;
        AIRHMIB.ITM = AICSHMIB.ITM;
        AIRHMIB.SEQ = AICSHMIB.SEQ;
        AIRHMIB.KEY.TR_DATE = AICSHMIB.STR_DT;
        AIRHMIB.KEY.SET_NO = AICSHMIB.SET_NO;
        AIRHMIB.KEY.SET_SEQ = AICSHMIB.SET_SEQ;
        IBS.CPY2CLS(SCCGWA, AICSHMIB.AC, WS_AC_NO);
        AIRHMIB.BR = WS_AC_NO.WS_BR;
        AIRHMIB.CCY = WS_AC_NO.WS_CCY;
        AIRHMIB.ITM = WS_AC_NO.WS_ITM;
        AIRHMIB.SEQ = WS_AC_NO.WS_AC_SEQ;
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = AIRHMIB.BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        WS_TMP_BBR = BPCPQORG.BBR;
        if (BPCPQORG.ATTR == '3') {
            if (BPCPQORG.BBR != 0) {
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BR = WS_TMP_BBR;
                S000_CALL_BPZPQORG();
                if (pgmRtn) return;
                if (BPCPQORG.ATTR == '2') {
                    AIRHMIB.BR = BPCPQORG.BBR;
                }
            }
        }
        CEP.TRC(SCCGWA, AIRHMIB.KEY.BOOK_FLG);
        CEP.TRC(SCCGWA, AIRHMIB.KEY.AC);
        CEP.TRC(SCCGWA, AIRHMIB.BR);
        CEP.TRC(SCCGWA, AIRHMIB.CCY);
        CEP.TRC(SCCGWA, AIRHMIB.ITM);
        CEP.TRC(SCCGWA, AIRHMIB.SEQ);
        CEP.TRC(SCCGWA, AIRHMIB.KEY.TR_DATE);
        CEP.TRC(SCCGWA, AIRHMIB.KEY.SET_SEQ);
        CEP.TRC(SCCGWA, AIRHMIB.KEY.SET_NO);
        AICRHMIB.POINTER = AIRHMIB;
        AICRHMIB.REC_LEN = 1202;
        S000_CALL_AIZRHMIB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "WLL02");
        if (AICRHMIB.RETURN_INFO == 'N') {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_AITHMIB_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        R000_DATA_OUTPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "AI510";
        SCCFMT.DATA_PTR = WS_BRW_OUTPUT;
        SCCFMT.DATA_LEN = 660;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B020_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BROWSE");
        WS_COUNT = 0;
        WS_COUNT_OUTPUT = 0;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 660;
        SCCMPAG.SCR_ROW_CNT = 20;
        SCCMPAG.SCR_COL_CNT = 5;
        B_MPAG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, AICUIANO);
        AICUIANO.INPUT_DATA.GL_BOOK = AICSHMIB.GL_BOOK;
        AICUIANO.INPUT_DATA.BR = AICSHMIB.BR;
        AICUIANO.INPUT_DATA.CCY = AICSHMIB.CCY;
        AICUIANO.INPUT_DATA.ITM = AICSHMIB.ITM;
        AICUIANO.INPUT_DATA.SEQ = AICSHMIB.SEQ;
        S000_CALL_AIZUIANO();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICUIANO.INPUT_DATA.AC);
        WS_AC = AICUIANO.INPUT_DATA.AC;
        IBS.init(SCCGWA, AICRHMIB);
        IBS.init(SCCGWA, AIRHMIB);
        AICRHMIB.FUNC = 'B';
        CEP.TRC(SCCGWA, "RHMIB-STR");
        if (AICSHMIB.END_DT == 0) {
            AICRHMIB.END_DT = 99991231;
        } else {
            AICRHMIB.END_DT = AICSHMIB.END_DT;
        }
        if (AICSHMIB.CCY.trim().length() == 0) {
            AICRHMIB.OPT = 'Y';
        } else {
            AICRHMIB.OPT = 'S';
        }
        AIRHMIB.KEY.BOOK_FLG = AICSHMIB.GL_BOOK;
        AIRHMIB.KEY.AC = WS_AC;
        IBS.init(SCCGWA, BPCPQORG);
        if (AIRHMIB.KEY.AC == null) AIRHMIB.KEY.AC = "";
        JIBS_tmp_int = AIRHMIB.KEY.AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) AIRHMIB.KEY.AC += " ";
        if (AIRHMIB.KEY.AC.substring(0, 6).trim().length() == 0) BPCPQORG.BR = 0;
        else BPCPQORG.BR = Integer.parseInt(AIRHMIB.KEY.AC.substring(0, 6));
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        WS_TMP_BBR = BPCPQORG.BBR;
        if (BPCPQORG.ATTR == '3') {
            if (BPCPQORG.BBR != 0) {
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BR = WS_TMP_BBR;
                S000_CALL_BPZPQORG();
                if (pgmRtn) return;
                if (BPCPQORG.ATTR == '2') {
                    if (AIRHMIB.KEY.AC == null) AIRHMIB.KEY.AC = "";
                    JIBS_tmp_int = AIRHMIB.KEY.AC.length();
                    for (int i=0;i<32-JIBS_tmp_int;i++) AIRHMIB.KEY.AC += " ";
                    JIBS_tmp_str[0] = "" + BPCPQORG.BBR;
                    JIBS_tmp_int = JIBS_tmp_str[0].length();
                    for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                    AIRHMIB.KEY.AC = JIBS_tmp_str[0] + AIRHMIB.KEY.AC.substring(6);
                }
            }
        }
        AIRHMIB.BR = AICSHMIB.BR;
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = AIRHMIB.BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        WS_TMP_BBR = BPCPQORG.BBR;
        if (BPCPQORG.ATTR == '3') {
            if (BPCPQORG.BBR != 0) {
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BR = WS_TMP_BBR;
                S000_CALL_BPZPQORG();
                if (pgmRtn) return;
                if (BPCPQORG.ATTR == '2') {
                    AIRHMIB.BR = BPCPQORG.BBR;
                }
            }
        }
        AIRHMIB.CCY = AICSHMIB.CCY;
        AIRHMIB.ITM = AICSHMIB.ITM;
        AIRHMIB.SEQ = AICSHMIB.SEQ;
        AIRHMIB.VAL_DATE = AICSHMIB.STR_DT;
        AIRHMIB.KEY.SET_NO = AICSHMIB.SET_NO;
        AIRHMIB.SIGN = AICSHMIB.SIGN;
        AIRHMIB.AMT = AICSHMIB.STR_AMT;
        AICRHMIB.END_AMT = AICSHMIB.END_AMT;
        CEP.TRC(SCCGWA, AICSHMIB.SET_NO);
        AICRHMIB.POINTER = AIRHMIB;
        AICRHMIB.REC_LEN = 1202;
        CEP.TRC(SCCGWA, AIRHMIB.KEY.BOOK_FLG);
        CEP.TRC(SCCGWA, AIRHMIB.BR);
        CEP.TRC(SCCGWA, AIRHMIB.CCY);
        CEP.TRC(SCCGWA, AIRHMIB.ITM);
        CEP.TRC(SCCGWA, AICSHMIB.SIGN);
        CEP.TRC(SCCGWA, AIRHMIB.SEQ);
        CEP.TRC(SCCGWA, AIRHMIB.VAL_DATE);
        CEP.TRC(SCCGWA, AICRHMIB.END_DT);
        S000_CALL_AIZRHMIB();
        if (pgmRtn) return;
        AICRHMIB.OPT = 'N';
        S000_CALL_AIZRHMIB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICRHMIB.RETURN_INFO);
        while (AICRHMIB.RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E') {
            WS_COUNT = WS_COUNT + 1;
            CEP.TRC(SCCGWA, WS_COUNT);
            R000_DATA_OUTPUT();
            if (pgmRtn) return;
            IBS.init(SCCGWA, SCCMPAG);
            SCCMPAG.FUNC = 'D';
            SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_BRW_OUTPUT);
            SCCMPAG.DATA_LEN = 660;
            B_MPAG();
            if (pgmRtn) return;
            AICRHMIB.OPT = 'N';
            CEP.TRC(SCCGWA, "HH1");
            S000_CALL_AIZRHMIB();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "HH2");
            CEP.TRC(SCCGWA, AICRHMIB.RETURN_INFO);
        }
        AICRHMIB.OPT = 'E';
        S000_CALL_AIZRHMIB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_COUNT);
    }
    public void B021_BROWSE_BPTVCHT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRVCHT);
        BPRVCHT.BOOK_FLG = AICSHMIB.GL_BOOK;
        BPRVCHT.BR = AICSHMIB.BR;
        BPRVCHT.CCY = AICSHMIB.CCY;
        BPRVCHT.ITM = AICSHMIB.ITM;
        BPRVCHT.KEY.AC_DATE = AICSHMIB.STR_DT;
        WS_END_DT = AICSHMIB.END_DT;
        BPRVCHT.KEY.SET_NO = AICSHMIB.SET_NO;
        BPRVCHT.SIGN = AICSHMIB.SIGN;
        BPRVCHT.AMT = AICSHMIB.STR_AMT;
        WS_END_AMT = AICSHMIB.END_AMT;
        if (BPRVCHT.KEY.SET_NO.trim().length() == 0) {
            T000_STARTBR_BPTVCHT();
            if (pgmRtn) return;
        } else {
            if (BPRVCHT.KEY.SET_NO == null) BPRVCHT.KEY.SET_NO = "";
            JIBS_tmp_int = BPRVCHT.KEY.SET_NO.length();
            for (int i=0;i<12-JIBS_tmp_int;i++) BPRVCHT.KEY.SET_NO += " ";
            if (BPRVCHT.KEY.SET_NO.substring(11 - 1, 11 + 2 - 1).trim().length() == 0) WS_PART_NO = 0;
            else WS_PART_NO = Short.parseShort(BPRVCHT.KEY.SET_NO.substring(11 - 1, 11 + 2 - 1));
            T000_STARTBR_BPTVCHT_NO();
            if (pgmRtn) return;
        }
        T000_READNEXT_BPTVCHT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCTVCHT.RETURN_INFO);
        while (BPCTVCHT.RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E') {
            if (BPRVCHT.MIB_AC == null) BPRVCHT.MIB_AC = "";
            JIBS_tmp_int = BPRVCHT.MIB_AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) BPRVCHT.MIB_AC += " ";
            if (BPRVCHT.MIB_AC.substring(20 - 1, 20 + 6 - 1).trim().length() == 0) WS_ACSEQ = 0;
            else WS_ACSEQ = Integer.parseInt(BPRVCHT.MIB_AC.substring(20 - 1, 20 + 6 - 1));
            CEP.TRC(SCCGWA, BPRVCHT.MIB_AC);
            CEP.TRC(SCCGWA, WS_ACSEQ);
            CEP.TRC(SCCGWA, AICSHMIB.SEQ);
            if (AICSHMIB.SEQ == WS_ACSEQ) {
                R001_DATA_OUTPUT();
                if (pgmRtn) return;
                IBS.init(SCCGWA, SCCMPAG);
                SCCMPAG.FUNC = 'D';
                SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_BRW_OUTPUT);
                SCCMPAG.DATA_LEN = 660;
                B_MPAG();
                if (pgmRtn) return;
            }
            T000_READNEXT_BPTVCHT();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "111111");
        T000_ENDBR_BPTVCHT();
        if (pgmRtn) return;
    }
    public void B031_QUERY_BPTVCHT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRVCHT);
        BPRVCHT.BOOK_FLG = AICSHMIB.GL_BOOK;
        BPRVCHT.BR = AICSHMIB.BR;
        BPRVCHT.CCY = AICSHMIB.CCY;
        BPRVCHT.ITM = AICSHMIB.ITM;
        BPRVCHT.KEY.AC_DATE = AICSHMIB.STR_DT;
        BPRVCHT.KEY.SET_NO = AICSHMIB.SET_NO;
        BPRVCHT.KEY.SET_SEQ = AICSHMIB.SET_SEQ;
        CEP.TRC(SCCGWA, BPRVCHT.KEY.AC_DATE);
        CEP.TRC(SCCGWA, BPRVCHT.KEY.SET_NO);
        CEP.TRC(SCCGWA, BPRVCHT.KEY.SET_SEQ);
        T000_READ_BPTVCHT();
        if (pgmRtn) return;
        if (BPCTVCHT.RETURN_INFO == 'N') {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_AITHMIB_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        R001_DATA_OUTPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "AI510";
        SCCFMT.DATA_PTR = WS_BRW_OUTPUT;
        SCCFMT.DATA_LEN = 660;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R001_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_BRW_OUTPUT);
        if (!WS_VCHT_CCY.equalsIgnoreCase(BPRVCHT.CCY)) {
            IBS.init(SCCGWA, AICPQMIB);
            AICPQMIB.INPUT_DATA.GL_BOOK = BPRVCHT.BOOK_FLG;
            AICPQMIB.INPUT_DATA.AC = BPRVCHT.MIB_AC;
            AICPQMIB.INPUT_DATA.CCY = BPRVCHT.CCY;
            WS_VCHT_CCY = BPRVCHT.CCY;
            S000_CALL_AIZPQMIB();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, AICPQMIB.OUTPUT_DATA.LBAL);
            WS_OPEN_AMT = AICPQMIB.OUTPUT_DATA.LBAL;
        }
        CEP.TRC(SCCGWA, WS_OPEN_AMT);
        CEP.TRC(SCCGWA, WS_VCHT_CCY);
        WS_BRW_OUTPUT.WS_BRW_TRDATE = BPRVCHT.KEY.AC_DATE;
        WS_BRW_OUTPUT.WS_BRW_TRTIME = BPRVCHT.TR_TIME;
        WS_BRW_OUTPUT.WS_BRW_BR = BPRVCHT.BR;
        WS_BRW_OUTPUT.WS_BRW_ITM = BPRVCHT.ITM;
        WS_BRW_OUTPUT.WS_BRW_SEQ = AICSHMIB.SEQ;
        WS_BRW_OUTPUT.WS_BRW_VALDATE = BPRVCHT.TR_DATE;
        WS_BRW_OUTPUT.WS_BRW_CCY = BPRVCHT.CCY;
        WS_BRW_OUTPUT.WS_BRW_SIGN = BPRVCHT.SIGN;
        WS_BRW_OUTPUT.WS_BRW_AMT = BPRVCHT.AMT;
        if (BPRVCHT.SIGN == 'D') {
            WS_OPEN_AMT = WS_OPEN_AMT - BPRVCHT.AMT;
        } else {
            WS_OPEN_AMT = WS_OPEN_AMT + BPRVCHT.AMT;
        }
        WS_BRW_OUTPUT.WS_BRW_AFTAMT = WS_OPEN_AMT;
        WS_BRW_OUTPUT.WS_BRW_TRCODE = BPRVCHT.TR_CODE;
        WS_BRW_OUTPUT.WS_BRW_TRBR = BPRVCHT.TR_BR;
        WS_BRW_OUTPUT.WS_BRW_TRTELLER = BPRVCHT.TR_TELLER;
        WS_BRW_OUTPUT.WS_BRW_DESC = BPRVCHT.DESC;
        WS_BRW_OUTPUT.WS_BRW_TMNO = BPRVCHT.TM_NO;
        WS_BRW_OUTPUT.WS_BRW_CHNLNO = BPRVCHT.CHNL_NO;
        WS_BRW_OUTPUT.WS_BRW_RVSNO = BPRVCHT.CRVS_NO;
        WS_BRW_OUTPUT.WS_BRW_RVSSEQ = BPRVCHT.CRVS_SEQ;
        WS_BRW_OUTPUT.WS_BRW_ECIND = BPRVCHT.EC_IND;
        WS_BRW_OUTPUT.WS_BRW_ODEFLG = BPRVCHT.ODE_FLG;
        WS_BRW_OUTPUT.WS_BRW_OTHSYSKEY = BPRVCHT.OTHSYS_KEY;
        WS_BRW_OUTPUT.WS_BRW_CREVNO = BPRVCHT.AC_NO;
        WS_BRW_OUTPUT.WS_BRW_SETNO = BPRVCHT.KEY.SET_NO;
        WS_BRW_OUTPUT.WS_BRW_SETSEQ = (short) BPRVCHT.KEY.SET_SEQ;
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_TRDATE);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_TRTIME);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_VALDATE);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_VALDATE);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_BR);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_ITM);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_SEQ);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_CCY);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_SIGN);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_AMT);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_AFTAMT);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_TRCODE);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_TRBR);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_TRTELLER);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_DESC);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_TMNO);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_CHNLNO);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_RVSNO);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_RVSSEQ);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_ECIND);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_ODEFLG);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_OTHSYSKEY);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_CREVNO);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_SETNO);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_SETSEQ);
    }
    public void S000_CALL_AIZRHMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-S-MAIN-HMIB", AICRHMIB);
        CEP.TRC(SCCGWA, AICRHMIB.RC.RC_CODE);
        if (AICRHMIB.RC.RC_CODE != 0) {
            WS_ERR_MSG = "" + AICRHMIB.RC.RC_CODE;
            JIBS_tmp_int = WS_ERR_MSG.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_ERR_MSG = "0" + WS_ERR_MSG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZUIANO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-GEN-IANO", AICUIANO);
    }
    public void S000_CALL_AIZPCMIB() throws IOException,SQLException,Exception {
        AICPCMIB.POINTER = AIRCMIB;
        AICPCMIB.REC_LEN = 407;
        IBS.CALLCPN(SCCGWA, "AI-P-GET-CMIB", AICPCMIB);
        CEP.TRC(SCCGWA, AICPCMIB.RC.RC_CODE);
        CEP.TRC(SCCGWA, AICPCMIB.RETURN_INFO);
    }
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB);
        if (AICPQMIB.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQMIB.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void T000_STARTBR_BPTVCHT() throws IOException,SQLException,Exception {
        BPTVCHT_BR.rp = new DBParm();
        if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTVCHT_BR.rp.TableName = "BPTVCHT1";
        else BPTVCHT_BR.rp.TableName = "BPTVCHT2";
        BPTVCHT_BR.rp.where = "BR = :BPRVCHT.BR "
            + "AND ITM = :BPRVCHT.ITM "
            + "AND ( ' ' = :BPRVCHT.CCY "
            + "OR CCY = :BPRVCHT.CCY ) "
            + "AND BOOK_FLG = :BPRVCHT.BOOK_FLG "
            + "AND AC_DATE = :BPRVCHT.KEY.AC_DATE "
            + "AND ( 0 = :BPRVCHT.SIGN "
            + "OR SIGN = :BPRVCHT.SIGN ) "
            + "AND ( ( ' ' = :BPRVCHT.AMT "
            + "AND 0 = :WS_END_AMT ) "
            + "OR ( AMT BETWEEN :BPRVCHT.AMT "
            + "AND :WS_END_AMT ) )";
        BPTVCHT_BR.rp.order = "MIB_AC,TR_DATE,SET_NO,SET_SEQ";
        IBS.STARTBR(SCCGWA, BPRVCHT, this, BPTVCHT_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_STARTBR_BPTVCHT_NO() throws IOException,SQLException,Exception {
        BPTVCHT_BR.rp = new DBParm();
        if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTVCHT_BR.rp.TableName = "BPTVCHT1";
        else BPTVCHT_BR.rp.TableName = "BPTVCHT2";
        BPTVCHT_BR.rp.where = "PART_NO = :WS_PART_NO "
            + "AND SET_NO = :BPRVCHT.KEY.SET_NO "
            + "AND AC_DATE = :BPRVCHT.KEY.AC_DATE "
            + "AND BR = :BPRVCHT.BR "
            + "AND ITM = :BPRVCHT.ITM "
            + "AND ( ' ' = :BPRVCHT.CCY "
            + "OR CCY = :BPRVCHT.CCY ) "
            + "AND ( ' ' = :BPRVCHT.SIGN "
            + "OR SIGN = :BPRVCHT.SIGN ) "
            + "AND ( ( ' ' = :BPRVCHT.AMT "
            + "AND 0 = :WS_END_AMT ) "
            + "OR ( AMT BETWEEN :BPRVCHT.AMT "
            + "AND :WS_END_AMT ) )";
        BPTVCHT_BR.rp.order = "SET_SEQ";
        IBS.STARTBR(SCCGWA, BPRVCHT, this, BPTVCHT_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READ_BPTVCHT() throws IOException,SQLException,Exception {
        BPTVCHT_RD = new DBParm();
        if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTVCHT_RD.TableName = "BPTVCHT1";
        else BPTVCHT_RD.TableName = "BPTVCHT2";
        IBS.READ(SCCGWA, BPRVCHT, BPTVCHT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTVCHT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTVCHT.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTVCHT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_BPTVCHT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRVCHT, this, BPTVCHT_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTVCHT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "DDDDD");
            BPCTVCHT.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTVCHT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTVCHT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTVCHT_BR);
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
