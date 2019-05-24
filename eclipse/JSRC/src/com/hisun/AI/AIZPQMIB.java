package com.hisun.AI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class AIZPQMIB {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm AITMIBA_RD;
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    AIZPQMIB_WS_INTERNAL_AC WS_INTERNAL_AC = new AIZPQMIB_WS_INTERNAL_AC();
    char WS_MIBA_FLG = ' ';
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AICRMIB AICRMIB = new AICRMIB();
    AICRONA AICRONA = new AICRONA();
    AIRMIBA AIRMIBA = new AIRMIBA();
    AICPCMIB AICPCMIB = new AICPCMIB();
    BPCQCCY BPCQCCY = new BPCQCCY();
    AICUIANO AICUIANO = new AICUIANO();
    BPCQBKPM BPCQBKPM = new BPCQBKPM();
    AIRMIB AIRMIB = new AIRMIB();
    AIRCMIB AIRCMIB = new AIRCMIB();
    AIRMIBH AIRMIBH = new AIRMIBH();
    AICRMIBH AICRMIBH = new AICRMIBH();
    AIRONA AIRONA = new AIRONA();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    AICPQMIB AICPQMIB;
    public void MP(SCCGWA SCCGWA, AICPQMIB AICPQMIB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICPQMIB = AICPQMIB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZPQMIB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_INQ_MIB_INFO_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AICPQMIB.INPUT_DATA.AC);
        CEP.TRC(SCCGWA, AICPQMIB.INPUT_DATA.CCY);
        if (AICPQMIB.INPUT_DATA.GL_BOOK.trim().length() == 0) {
            IBS.init(SCCGWA, BPCQBKPM);
            BPCQBKPM.FUNC = 'B';
            S000_CALL_BPZQBKPM();
            if (pgmRtn) return;
            AICPQMIB.INPUT_DATA.GL_BOOK = BPCQBKPM.DATA[1-1].BOOK_FLG;
        }
        if (AICPQMIB.INPUT_DATA.AC.trim().length() == 0 
            && (AICPQMIB.INPUT_DATA.BR == 0 
            || AICPQMIB.INPUT_DATA.ITM_NO.trim().length() == 0 
            || AICPQMIB.INPUT_DATA.SEQ == 0 
            || AICPQMIB.INPUT_DATA.CCY.trim().length() == 0)) {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_AC_OR_BR_M_INP_ONE, AICPQMIB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (AICPQMIB.INPUT_DATA.AC.trim().length() > 0) {
            AICRONA.FUNC = 'B';
            AIRONA.KEY.OAC_NO = AICPQMIB.INPUT_DATA.AC;
            S000_CALL_AIZRONA();
            if (pgmRtn) return;
            if (AICPQMIB.INPUT_DATA.AC == null) AICPQMIB.INPUT_DATA.AC = "";
            JIBS_tmp_int = AICPQMIB.INPUT_DATA.AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) AICPQMIB.INPUT_DATA.AC += " ";
            if (AICPQMIB.INPUT_DATA.AC == null) AICPQMIB.INPUT_DATA.AC = "";
            JIBS_tmp_int = AICPQMIB.INPUT_DATA.AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) AICPQMIB.INPUT_DATA.AC += " ";
            if ((AICPQMIB.INPUT_DATA.AC.substring(25 - 1, 25 + 1 - 1).trim().length() == 0 
                || AICPQMIB.INPUT_DATA.AC.substring(26 - 1, 26 + 1 - 1).trim().length() > 0) 
                && AICRONA.RETURN_INFO == 'N') {
                IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.NOT_INVALID_ACCOUNT, AICPQMIB.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (AICRONA.RETURN_INFO == 'F') {
                AICPQMIB.INPUT_DATA.AC = AIRONA.AC_NO;
                AICPQMIB.INPUT_DATA.CCY = AIRONA.CCY;
            }
        }
        if (AICPQMIB.INPUT_DATA.AC.trim().length() > 0) {
            IBS.init(SCCGWA, BPCQCCY);
            if (AICPQMIB.INPUT_DATA.AC == null) AICPQMIB.INPUT_DATA.AC = "";
            JIBS_tmp_int = AICPQMIB.INPUT_DATA.AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) AICPQMIB.INPUT_DATA.AC += " ";
            BPCQCCY.DATA.CCY = AICPQMIB.INPUT_DATA.AC.substring(7 - 1, 7 + 3 - 1);
            CEP.TRC(SCCGWA, BPCQCCY.DATA.CCY);
            S000_CALL_BPZQCCY();
            if (pgmRtn) return;
        }
        if (AICPQMIB.INPUT_DATA.CCY.trim().length() > 0) {
            IBS.init(SCCGWA, BPCQCCY);
            BPCQCCY.DATA.CCY = AICPQMIB.INPUT_DATA.CCY;
            CEP.TRC(SCCGWA, BPCQCCY.DATA.CCY);
            S000_CALL_BPZQCCY();
            if (pgmRtn) return;
        }
    }
    public void B020_INQ_MIB_INFO_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICPQMIB.OUTPUT_DATA);
        IBS.init(SCCGWA, AIRMIB);
        IBS.init(SCCGWA, AICRMIB);
        CEP.TRC(SCCGWA, AICPQMIB.INPUT_DATA.AC);
        if (AICPQMIB.INPUT_DATA.AC.trim().length() > 0) {
            CEP.TRC(SCCGWA, AICPQMIB.INPUT_DATA.AC);
            CEP.TRC(SCCGWA, "BOBO1");
            if (AICPQMIB.INPUT_DATA.AC == null) AICPQMIB.INPUT_DATA.AC = "";
            JIBS_tmp_int = AICPQMIB.INPUT_DATA.AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) AICPQMIB.INPUT_DATA.AC += " ";
            if (!IBS.isNumeric(AICPQMIB.INPUT_DATA.AC.substring(0, 25))) {
                IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.NOT_INVALID_ACCOUNT, AICPQMIB.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, "BOBO2");
            IBS.CPY2CLS(SCCGWA, AICPQMIB.INPUT_DATA.AC, WS_INTERNAL_AC);
            AIRMIB.KEY.BR = WS_INTERNAL_AC.WS_INTERNAL_BR;
            AICPQMIB.INPUT_DATA.BR = WS_INTERNAL_AC.WS_INTERNAL_BR;
            AIRMIB.KEY.ITM_NO = WS_INTERNAL_AC.WS_INTERNAL_ITM;
            AICPQMIB.INPUT_DATA.ITM_NO = WS_INTERNAL_AC.WS_INTERNAL_ITM;
            AIRMIB.KEY.SEQ = WS_INTERNAL_AC.WS_INTERNAL_SEQ;
            AICPQMIB.INPUT_DATA.SEQ = WS_INTERNAL_AC.WS_INTERNAL_SEQ;
            AIRMIB.AC_NO = AICPQMIB.INPUT_DATA.AC;
            AIRMIB.KEY.CCY = WS_INTERNAL_AC.WS_INTERNAL_CCY;
        } else {
            WS_INTERNAL_AC.WS_INTERNAL_BR = AICPQMIB.INPUT_DATA.BR;
            WS_INTERNAL_AC.WS_INTERNAL_ITM = AICPQMIB.INPUT_DATA.ITM_NO;
            WS_INTERNAL_AC.WS_INTERNAL_SEQ = AICPQMIB.INPUT_DATA.SEQ;
            WS_INTERNAL_AC.WS_INTERNAL_CCY = AICPQMIB.INPUT_DATA.CCY;
            AIRMIB.KEY.BR = AICPQMIB.INPUT_DATA.BR;
            AIRMIB.KEY.ITM_NO = AICPQMIB.INPUT_DATA.ITM_NO;
            AIRMIB.KEY.SEQ = AICPQMIB.INPUT_DATA.SEQ;
            AIRMIB.AC_NO = IBS.CLS2CPY(SCCGWA, WS_INTERNAL_AC);
            AICPQMIB.INPUT_DATA.AC = IBS.CLS2CPY(SCCGWA, WS_INTERNAL_AC);
            AIRMIB.KEY.CCY = AICPQMIB.INPUT_DATA.CCY;
        }
        AICRMIB.FUNC = 'Q';
        AIRMIB.KEY.GL_BOOK = AICPQMIB.INPUT_DATA.GL_BOOK;
        AICRMIB.POINTER = AIRMIB;
        AICRMIB.REC_LEN = 634;
        S000_CALL_AIZRMIB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICPQMIB.INPUT_DATA.TX_FLG);
        if (AICRMIB.RETURN_INFO == 'N' 
            && AICPQMIB.INPUT_DATA.TX_FLG == 'O') {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_AITMIB_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        R00_QUERY_CMIB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICRMIB.RETURN_INFO);
        if (AICRMIB.RETURN_INFO == 'N') {
            R00_RETURN_DATA1();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, AIRMIBA);
            AIRMIBA.KEY.BOOK_FLG = AIRMIB.KEY.GL_BOOK;
            AIRMIBA.KEY.AC = AIRMIB.AC_NO;
            T000_READ_AITMIBA_FIRST();
            if (pgmRtn) return;
            if (WS_MIBA_FLG == 'Y') {
                AIRMIB.LBAL = AIRMIBA.L_BAL;
            }
            CEP.TRC(SCCGWA, AIRMIB.HOT_FLG);
            if (AIRMIB.HOT_FLG == 'H') {
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
                    AIRMIB.CBAL = AICRMIBH.CBAL_SUM;
                }
            }
            R00_RETURN_DATA();
            if (pgmRtn) return;
        }
    }
    public void R00_QUERY_CMIB() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRCMIB);
        IBS.init(SCCGWA, AICPCMIB);
        AIRCMIB.KEY.GL_BOOK = AIRMIB.KEY.GL_BOOK;
        AIRCMIB.KEY.BR = AIRMIB.KEY.BR;
        AIRCMIB.KEY.ITM = AIRMIB.KEY.ITM_NO;
        AIRCMIB.KEY.SEQ = AIRMIB.KEY.SEQ;
        CEP.TRC(SCCGWA, AIRCMIB.KEY.GL_BOOK);
        CEP.TRC(SCCGWA, AIRCMIB.KEY.BR);
        CEP.TRC(SCCGWA, AIRCMIB.KEY.ITM);
        CEP.TRC(SCCGWA, AIRCMIB.KEY.SEQ);
        AICPCMIB.POINTER = AIRCMIB;
        AICPCMIB.REC_LEN = 407;
        S000_CALL_AIZPCMIB();
        if (pgmRtn) return;
        if (AICPCMIB.RETURN_INFO == 'N') {
            IBS.init(SCCGWA, AIRCMIB);
            IBS.init(SCCGWA, AICPCMIB);
            AIRCMIB.KEY.GL_BOOK = AIRMIB.KEY.GL_BOOK;
            AIRCMIB.KEY.BR = AIRMIB.KEY.BR;
            AIRCMIB.KEY.ITM = AIRMIB.KEY.ITM_NO;
            AIRCMIB.KEY.SEQ = 999999;
            CEP.TRC(SCCGWA, AIRCMIB.KEY.GL_BOOK);
            CEP.TRC(SCCGWA, AIRCMIB.KEY.BR);
            CEP.TRC(SCCGWA, AIRCMIB.KEY.ITM);
            CEP.TRC(SCCGWA, AIRCMIB.KEY.SEQ);
            AICPCMIB.POINTER = AIRCMIB;
            AICPCMIB.REC_LEN = 407;
            S000_CALL_AIZPCMIB();
            if (pgmRtn) return;
            if (AICPCMIB.RETURN_INFO == 'N') {
                IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_READ_AITCMIB_NOTFND, AICPQMIB.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void R00_RETURN_DATA() throws IOException,SQLException,Exception {
        AICPQMIB.INPUT_DATA.BR = AIRMIB.KEY.BR;
        AICPQMIB.INPUT_DATA.ITM_NO = AIRMIB.KEY.ITM_NO;
        AICPQMIB.INPUT_DATA.SEQ = AIRMIB.KEY.SEQ;
        AICPQMIB.INPUT_DATA.AC = AIRMIB.AC_NO;
        AICPQMIB.INPUT_DATA.CCY = AIRMIB.KEY.CCY;
        AICPQMIB.OUTPUT_DATA.CHS_NM = AIRMIB.CHS_NM;
        if (AIRMIB.CHS_NM.trim().length() == 0) {
            AICPQMIB.OUTPUT_DATA.CHS_NM = AIRCMIB.CHS_NM;
        }
        AICPQMIB.OUTPUT_DATA.ENG_NM = AIRMIB.ENG_NM;
        AICPQMIB.OUTPUT_DATA.STS = AIRMIB.STS;
        AICPQMIB.OUTPUT_DATA.AC_TYP = AIRMIB.AC_TYP;
        AICPQMIB.OUTPUT_DATA.CCY_LMT = AIRMIB.CCY_LMT;
        AICPQMIB.OUTPUT_DATA.BAL_DIR = AIRMIB.BAL_DIR;
        AICPQMIB.OUTPUT_DATA.BAL_RFLG = AIRMIB.BAL_RFLG;
        AICPQMIB.OUTPUT_DATA.AMT_DIR = AIRMIB.AMT_DIR;
        AICPQMIB.OUTPUT_DATA.DTL_FLG = AIRMIB.DTL_FLG;
        AICPQMIB.OUTPUT_DATA.RVS_TYP = AIRMIB.RVS_TYP;
        AICPQMIB.OUTPUT_DATA.RVS_KND = AIRMIB.RVS_KND;
        AICPQMIB.OUTPUT_DATA.RVS_EXP = AIRMIB.RVS_EXP;
        AICPQMIB.OUTPUT_DATA.RVS_UNIT = AIRMIB.RVS_UNIT;
        AICPQMIB.OUTPUT_DATA.AC_EXP = AIRMIB.AC_EXP;
        AICPQMIB.OUTPUT_DATA.MANUAL_FLG = AIRMIB.MANUAL_FLG;
        AICPQMIB.OUTPUT_DATA.ONL_FLG = AIRMIB.ONL_FLG;
        AICPQMIB.OUTPUT_DATA.CARD_FLG = AIRMIB.CARD_FLG;
        AICPQMIB.OUTPUT_DATA.HOT_FLG = AIRMIB.HOT_FLG;
        AICPQMIB.OUTPUT_DATA.DRLT_BAL = AIRMIB.DRLT_BAL;
        AICPQMIB.OUTPUT_DATA.CRLT_BAL = AIRMIB.CRLT_BAL;
        AICPQMIB.OUTPUT_DATA.CHK_FLG = AIRMIB.BAL_CHK;
        AICPQMIB.OUTPUT_DATA.APP1 = AIRMIB.APP1;
        AICPQMIB.OUTPUT_DATA.APP2 = AIRMIB.APP2;
        AICPQMIB.OUTPUT_DATA.APP3 = AIRMIB.APP3;
        AICPQMIB.OUTPUT_DATA.APP4 = AIRMIB.APP4;
        AICPQMIB.OUTPUT_DATA.APP5 = AIRMIB.APP5;
        AICPQMIB.OUTPUT_DATA.CLN_REN = AIRMIB.CLN_REN;
        AICPQMIB.OUTPUT_DATA.OPEN_DATE = AIRMIB.OPEN_DATE;
        AICPQMIB.OUTPUT_DATA.CLS_DATE = AIRMIB.CLS_DATE;
        AICPQMIB.OUTPUT_DATA.LAST_TX_DT = AIRMIB.LAST_TX_DT;
        AICPQMIB.OUTPUT_DATA.LBAL = AIRMIB.LBAL;
        AICPQMIB.OUTPUT_DATA.CBAL = AIRMIB.CBAL;
    }
    public void R00_RETURN_DATA1() throws IOException,SQLException,Exception {
        AICPQMIB.INPUT_DATA.ITM_NO = AIRCMIB.KEY.ITM;
        AICPQMIB.INPUT_DATA.SEQ = AIRCMIB.KEY.SEQ;
        AICPQMIB.OUTPUT_DATA.CHS_NM = AIRCMIB.CHS_NM;
        AICPQMIB.OUTPUT_DATA.ENG_NM = AIRCMIB.ENG_NM;
        AICPQMIB.OUTPUT_DATA.STS = AIRCMIB.STS;
        AICPQMIB.OUTPUT_DATA.AC_TYP = AIRCMIB.AC_TYP;
        AICPQMIB.OUTPUT_DATA.CCY_LMT = AIRCMIB.CCY_LMT;
        AICPQMIB.OUTPUT_DATA.BAL_DIR = AIRCMIB.BAL_DIR;
        AICPQMIB.OUTPUT_DATA.BAL_RFLG = AIRCMIB.BAL_RFLG;
        AICPQMIB.OUTPUT_DATA.AMT_DIR = AIRCMIB.AMT_DIR;
        AICPQMIB.OUTPUT_DATA.DTL_FLG = AIRCMIB.DTL_FLG;
        AICPQMIB.OUTPUT_DATA.RVS_TYP = AIRCMIB.RVS_TYP;
        CEP.TRC(SCCGWA, AIRCMIB.RVS_TYP);
        AICPQMIB.OUTPUT_DATA.RVS_KND = AIRCMIB.RVS_KND;
        AICPQMIB.OUTPUT_DATA.RVS_EXP = AIRCMIB.RVS_EXP;
        AICPQMIB.OUTPUT_DATA.RVS_UNIT = AIRCMIB.RVS_UNIT;
        AICPQMIB.OUTPUT_DATA.AC_EXP = AIRCMIB.AC_EXP;
        AICPQMIB.OUTPUT_DATA.MANUAL_FLG = AIRCMIB.MANUAL_FLG;
        AICPQMIB.OUTPUT_DATA.ONL_FLG = AIRCMIB.ONL_FLG;
        AICPQMIB.OUTPUT_DATA.CARD_FLG = AIRCMIB.CARD_FLG;
        AICPQMIB.OUTPUT_DATA.HOT_FLG = AIRCMIB.HOT_FLG;
        AICPQMIB.OUTPUT_DATA.DRLT_BAL = AIRCMIB.DRLT_BAL;
        AICPQMIB.OUTPUT_DATA.CRLT_BAL = AIRCMIB.CRLT_BAL;
        AICPQMIB.OUTPUT_DATA.CHK_FLG = AIRCMIB.BAL_CHK;
        AICPQMIB.OUTPUT_DATA.APP1 = AIRCMIB.APP1;
        AICPQMIB.OUTPUT_DATA.APP2 = AIRCMIB.APP2;
        AICPQMIB.OUTPUT_DATA.APP3 = AIRCMIB.APP3;
        AICPQMIB.OUTPUT_DATA.APP4 = AIRCMIB.APP4;
        AICPQMIB.OUTPUT_DATA.APP5 = AIRCMIB.APP5;
        AICPQMIB.OUTPUT_DATA.LBAL = 0;
        AICPQMIB.OUTPUT_DATA.CBAL = 0;
    }
    public void S000_CALL_AIZRMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-R-MAIN-MIB", AICRMIB);
        CEP.TRC(SCCGWA, AICRMIB.RC);
        if (AICRMIB.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICRMIB.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], AICPQMIB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZRONA() throws IOException,SQLException,Exception {
        AICRONA.POINTER = AIRONA;
        AICRONA.REC_LEN = 82;
        IBS.CALLCPN(SCCGWA, "AI-R-MAIN-ONA", AICRONA);
        CEP.TRC(SCCGWA, AICRONA.RC);
        CEP.TRC(SCCGWA, AICRONA.RETURN_INFO);
    }
    public void S000_CALL_AIZPCMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-GET-CMIB", AICPCMIB);
        CEP.TRC(SCCGWA, AICPCMIB.RC);
        if (AICPCMIB.RETURN_INFO != 'N') {
            if (AICPCMIB.RC.RC_CODE != 0) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPCMIB.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], AICPQMIB.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_READ_AITMIBA_FIRST() throws IOException,SQLException,Exception {
        AITMIBA_RD = new DBParm();
        AITMIBA_RD.TableName = "AITMIBA";
        AITMIBA_RD.where = "BOOK_FLG = :AIRMIBA.KEY.BOOK_FLG "
            + "AND AC = :AIRMIBA.KEY.AC";
        AITMIBA_RD.fst = true;
        AITMIBA_RD.order = "AC_DT DESC";
        IBS.READ(SCCGWA, AIRMIBA, this, AITMIBA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_MIBA_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MIBA_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITMIBA";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZQBKPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-Q-MAINT-AMBKP", BPCQBKPM);
        CEP.TRC(SCCGWA, BPCQBKPM.RC.RC_RTNCODE);
        if (BPCQBKPM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQBKPM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], AICPQMIB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], AICPQMIB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZRMIBH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-R-MAIN-MIBH", AICRMIBH);
        CEP.TRC(SCCGWA, AICRMIBH.RC);
        CEP.TRC(SCCGWA, AICRMIBH.RC.RC_CODE);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (AICPQMIB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "AICPQMIB = ");
            CEP.TRC(SCCGWA, AICPQMIB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
