package com.hisun.AI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCQBKPM;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class AIZPUITS {
    boolean pgmRtn = false;
    String TBL_AITITM = "AITITM  ";
    String TBL_AIRMST = "AIRMST";
    String AI_HIS_MSTRMKS = "GL BAT MAINTAIN HISTORY";
    String K_PARM_TYPE = "AMGLM";
    String TBL_AITCMIB = "AITCMIB";
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    int WS_CNT = 0;
    String WS_ITM_COA_FLG = " ";
    String WS_ITM_NO = " ";
    String WS_MST_ITM_NO = " ";
    int WS_QBKPM_CNT = 0;
    String WS_COA_FLG_TMP = " ";
    Object WS_ITM_PTR;
    char WS_RETURN_INFO = ' ';
    AIZPUITS_WS_MST_KEY WS_MST_KEY = new AIZPUITS_WS_MST_KEY();
    List<AIZPUITS_WS_BOOK_FLG_ALL> WS_BOOK_FLG_ALL = new ArrayList<AIZPUITS_WS_BOOK_FLG_ALL>();
    String WS_BOOK_FLG_MST = " ";
    char WS_EOF_FLG = ' ';
    char WS_BAL_ZERO = ' ';
    char WS_AITCMIB_FLG = ' ';
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCBINF SCCBINF = new SCCBINF();
    BPCQBKPM BPCQBKPM = new BPCQBKPM();
    AIRITM AIRITM = new AIRITM();
    AIRITM AIROITM = new AIRITM();
    AIRITM AIRNITM = new AIRITM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    AIRMST AIRMST = new AIRMST();
    AIRMST AIROMST = new AIRMST();
    AIRMST AIRNMST = new AIRMST();
    AICRMIB AICRMIB = new AICRMIB();
    AICRCMIB AICRCMIB = new AICRCMIB();
    AIRMIB AIRMIB = new AIRMIB();
    AIRCMIB AIRCMIB = new AIRCMIB();
    AIRCMIB AIRMCMIB = new AIRCMIB();
    AICSMIB AICSMIB = new AICSMIB();
    AICSCMIB AICSCMIB = new AICSCMIB();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AICOUITS AICOUITS;
    public void MP(SCCGWA SCCGWA, AICOUITS AICOUITS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICOUITS = AICOUITS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZPUITS return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "TEST");
        B020_UPD_ITM_STS();
        if (pgmRtn) return;
    }
    public void B020_UPD_ITM_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRITM);
        AIRITM.KEY.COA_FLG = AICOUITS.DATA.COA_FLG;
        AIRITM.KEY.NO = AICOUITS.DATA.COA_NO;
        CEP.TRC(SCCGWA, AIRITM.KEY.NO);
        CEP.TRC(SCCGWA, AIRITM.KEY.COA_FLG);
        T000_READUPD_RECORD_PROC();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, AIRITM, AIROITM);
        CEP.TRC(SCCGWA, AIROITM);
        if (AICOUITS.DATA.FUNC_CD == 'C' 
            || AICOUITS.DATA.FUNC_CD == 'S') {
            B021_BOOK_BAL_IS_ZERO();
            if (pgmRtn) return;
            if (WS_BAL_ZERO != 'N') {
                if (AIRITM.BAL_SIGN_FLG == 'B') {
                    B022_MIB_BAL_IS_ZERO();
                    if (pgmRtn) return;
                }
            }
            CEP.TRC(SCCGWA, WS_BAL_ZERO);
            if (WS_BAL_ZERO != 'N') {
                AIRITM.STS = 'C';
                AIRITM.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                AIRITM.DEL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            } else {
                AIRITM.STS = 'S';
                AIRITM.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            }
            B023_MIB_CBAL_STOP();
            if (pgmRtn) return;
        }
        if (AICOUITS.DATA.FUNC_CD == 'A') {
            AIRITM.STS = 'A';
            AIRITM.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (AICOUITS.DATA.FUNC_CD == 'H' 
            && AIRITM.STS != 'S') {
            AIRITM.STS = 'H';
            AIRITM.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            B024_HOLD_CMIB();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, AIRITM, AIRNITM);
        CEP.TRC(SCCGWA, AIRNITM);
        T000_UPDATE_RECORD_PROC();
        if (pgmRtn) return;
        S000_HIS_PROC();
        if (pgmRtn) return;
    }
    public void B021_BOOK_BAL_IS_ZERO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQBKPM);
        BPCQBKPM.FUNC = 'B';
        S000_CALL_BPZQBKPM();
        if (pgmRtn) return;
        for (WS_CNT = 1; WS_CNT <= 10 
            && BPCQBKPM.DATA[WS_CNT-1].COA_FLG.trim().length() != 0; WS_CNT += 1) {
            if (BPCQBKPM.DATA[WS_CNT-1].COA_FLG.equalsIgnoreCase(AICOUITS.DATA.COA_FLG)) {
                IBS.init(SCCGWA, AIRMST);
                AIRMST.KEY.GL_BOOK_FLG = BPCQBKPM.DATA[WS_CNT-1].BOOK_FLG;
                AIRMST.KEY.ITM_NO = AICOUITS.DATA.COA_NO;
                T00_OPEN_AITMST();
                if (pgmRtn) return;
                T00_FETCH_AITMST();
                if (pgmRtn) return;
                while (WS_EOF_FLG != 'Y') {
                    if (AIRMST.CDDBAL != 0 
                        || AIRMST.CDCBAL != 0) {
                        WS_BAL_ZERO = 'N';
                    }
                    T00_FETCH_AITMST();
                    if (pgmRtn) return;
                }
                T00_CLOSE_AITMST();
                if (pgmRtn) return;
            }
        }
    }
    public void B023_MIB_CBAL_STOP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQBKPM);
        BPCQBKPM.FUNC = 'B';
        S000_CALL_BPZQBKPM();
        if (pgmRtn) return;
        for (WS_CNT = 1; WS_CNT <= 10 
            && BPCQBKPM.DATA[WS_CNT-1].COA_FLG.trim().length() != 0; WS_CNT += 1) {
            if (BPCQBKPM.DATA[WS_CNT-1].COA_FLG.equalsIgnoreCase(AICOUITS.DATA.COA_FLG)) {
                IBS.init(SCCGWA, AIRMCMIB);
                AIRMCMIB.KEY.GL_BOOK = BPCQBKPM.DATA[WS_CNT-1].BOOK_FLG;
                AIRMCMIB.KEY.ITM = AICOUITS.DATA.COA_NO;
                T000_STARTBR_CMIB();
                if (pgmRtn) return;
                T000_READNEXT_CMIB();
                if (pgmRtn) return;
                while (WS_AITCMIB_FLG != 'N') {
                    if (AIRMCMIB.STS == 'N' 
                        || AIRMCMIB.STS == 'H' 
                        || AIRMCMIB.STS == 'P') {
                        IBS.init(SCCGWA, AICSCMIB);
                        AICSCMIB.FUNC = 'S';
                        AICSCMIB.GL_BOOK = AIRMCMIB.KEY.GL_BOOK;
                        AICSCMIB.BR = AIRMCMIB.KEY.BR;
                        AICSCMIB.ITM = AIRMCMIB.KEY.ITM;
                        AICSCMIB.SEQ = AIRMCMIB.KEY.SEQ;
                        S000_CALL_AIZSCMIB();
                        if (pgmRtn) return;
                    }
                    T000_READNEXT_CMIB();
                    if (pgmRtn) return;
                }
                T000_ENDBR_CMIB();
                if (pgmRtn) return;
            }
        }
    }
    public void B024_HOLD_CMIB() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQBKPM);
        BPCQBKPM.FUNC = 'B';
        S000_CALL_BPZQBKPM();
        if (pgmRtn) return;
        for (WS_CNT = 1; WS_CNT <= 10 
            && BPCQBKPM.DATA[WS_CNT-1].COA_FLG.trim().length() != 0; WS_CNT += 1) {
            if (BPCQBKPM.DATA[WS_CNT-1].COA_FLG.equalsIgnoreCase(AICOUITS.DATA.COA_FLG)) {
                IBS.init(SCCGWA, AIRCMIB);
                IBS.init(SCCGWA, AICRCMIB);
                AICRCMIB.FUNC = 'B';
                AICRCMIB.OPT = 'I';
                AIRCMIB.KEY.GL_BOOK = BPCQBKPM.DATA[WS_CNT-1].BOOK_FLG;
                AIRCMIB.KEY.ITM = AICOUITS.DATA.COA_NO;
                AICRCMIB.POINTER = AIRCMIB;
                AICRCMIB.REC_LEN = 407;
                S000_CALL_AIZRCMIB();
                if (pgmRtn) return;
                AICRCMIB.OPT = 'N';
                S000_CALL_AIZRCMIB();
                if (pgmRtn) return;
                while (AICRCMIB.RETURN_INFO != 'N') {
                    if (AIRCMIB.STS == 'N') {
                        IBS.init(SCCGWA, AICSCMIB);
                        AICSCMIB.FUNC = 'H';
                        AICSCMIB.GL_BOOK = AIRCMIB.KEY.GL_BOOK;
                        AICSCMIB.BR = AIRCMIB.KEY.BR;
                        AICSCMIB.ITM = AIRCMIB.KEY.ITM;
                        AICSCMIB.SEQ = AIRCMIB.KEY.SEQ;
                        S000_CALL_AIZSCMIB();
                        if (pgmRtn) return;
                    }
                    B025_HOLD_MIB();
                    if (pgmRtn) return;
                    AICRCMIB.OPT = 'N';
                    S000_CALL_AIZRCMIB();
                    if (pgmRtn) return;
                }
                AICRMIB.OPT = 'E';
                S000_CALL_AIZRCMIB();
                if (pgmRtn) return;
            }
        }
    }
    public void B025_HOLD_MIB() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRMIB);
        IBS.init(SCCGWA, AICRMIB);
        AICRMIB.FUNC = 'B';
        AICRMIB.OPT = '2';
        AIRMIB.KEY.GL_BOOK = AIRCMIB.KEY.GL_BOOK;
        AIRMIB.KEY.ITM_NO = AIRCMIB.KEY.ITM;
        AIRMIB.KEY.SEQ = AIRCMIB.KEY.SEQ;
        CEP.TRC(SCCGWA, AIRMIB.KEY.GL_BOOK);
        CEP.TRC(SCCGWA, AIRMIB.KEY.ITM_NO);
        CEP.TRC(SCCGWA, AIRMIB.KEY.SEQ);
        AICRMIB.POINTER = AIRMIB;
        AICRMIB.REC_LEN = 634;
        S000_CALL_AIZRMIB();
        if (pgmRtn) return;
        AICRMIB.OPT = 'N';
        S000_CALL_AIZRMIB();
        if (pgmRtn) return;
        while (AICRMIB.RETURN_INFO != 'N') {
            if (AIRMIB.STS == 'N') {
                IBS.init(SCCGWA, AICSCMIB);
                AICSMIB.FUNC = 'H';
                AICSMIB.GL_BOOK = AIRMIB.KEY.GL_BOOK;
                AICSMIB.BR = AIRMIB.KEY.BR;
                AICSMIB.ITM_NO = AIRMIB.KEY.ITM_NO;
                AICSMIB.SEQ = AIRMIB.KEY.SEQ;
                AICSMIB.CCY = AIRMIB.KEY.CCY;
                S000_CALL_AIZSMIB();
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
    public void B022_MIB_BAL_IS_ZERO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQBKPM);
        BPCQBKPM.FUNC = 'B';
        S000_CALL_BPZQBKPM();
        if (pgmRtn) return;
        for (WS_CNT = 1; WS_CNT <= 10 
            && BPCQBKPM.DATA[WS_CNT-1].COA_FLG.trim().length() != 0; WS_CNT += 1) {
            if (BPCQBKPM.DATA[WS_CNT-1].COA_FLG.equalsIgnoreCase(AICOUITS.DATA.COA_FLG)) {
                IBS.init(SCCGWA, AIRMIB);
                IBS.init(SCCGWA, AICRMIB);
                AICRMIB.FUNC = 'B';
                AICRMIB.OPT = '1';
                AIRMIB.KEY.GL_BOOK = BPCQBKPM.DATA[WS_CNT-1].BOOK_FLG;
                AIRMIB.KEY.ITM_NO = AICOUITS.DATA.COA_NO;
                AICRMIB.POINTER = AIRMIB;
                AICRMIB.REC_LEN = 634;
                S000_CALL_AIZRMIB();
                if (pgmRtn) return;
                AICRMIB.OPT = 'N';
                S000_CALL_AIZRMIB();
                if (pgmRtn) return;
                if (AICRMIB.RETURN_INFO == 'N') {
                    WS_BAL_ZERO = 'Y';
                }
                while (AICRMIB.RETURN_INFO != 'N') {
                    CEP.TRC(SCCGWA, AIRMIB.CBAL);
                    if (AIRMIB.CBAL != 0) {
                        WS_BAL_ZERO = 'N';
                    }
                    if (AIRMIB.STS == 'N') {
                        IBS.init(SCCGWA, AICSMIB);
                        AICSMIB.FUNC = 'S';
                        AICSMIB.GL_BOOK = AIRMIB.KEY.GL_BOOK;
                        AICSMIB.BR = AIRMIB.KEY.BR;
                        AICSMIB.ITM_NO = AIRMIB.KEY.ITM_NO;
                        AICSMIB.SEQ = AIRMIB.KEY.SEQ;
                        AICSMIB.CCY = AIRMIB.KEY.CCY;
                        S000_CALL_AIZSMIB();
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
        }
    }
    public void T000_STARTBR_CMIB() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BROWSE ITM");
        AITCMIB_BR.rp = new DBParm();
        AITCMIB_BR.rp.TableName = "AITCMIB";
        AITCMIB_BR.rp.where = "GL_BOOK = :AIRMCMIB.KEY.GL_BOOK "
            + "AND ITM = :AIRMCMIB.KEY.ITM";
        AITCMIB_BR.rp.order = "ITM,SEQ,BR";
        IBS.STARTBR(SCCGWA, AIRCMIB, this, AITCMIB_BR);
    }
    public void T000_READNEXT_CMIB() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, AIRCMIB, this, AITCMIB_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            IBS.CLONE(SCCGWA, AIRCMIB, AIRMCMIB);
            WS_AITCMIB_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_AITCMIB_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_AITCMIB;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_CMIB() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, AITCMIB_BR);
    }
    public void S000_CALL_AIZRMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-R-MAIN-MIB", AICRMIB);
        if (AICRMIB.RC.RC_CODE != 0) {
            WS_ERR_MSG = "" + AICRMIB.RC.RC_CODE;
            JIBS_tmp_int = WS_ERR_MSG.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_ERR_MSG = "0" + WS_ERR_MSG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZRCMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-R-MAIN-CMIB", AICRCMIB);
        if (AICRCMIB.RC.RC_CODE != 0) {
            WS_ERR_MSG = "" + AICRCMIB.RC.RC_CODE;
            JIBS_tmp_int = WS_ERR_MSG.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_ERR_MSG = "0" + WS_ERR_MSG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZSMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-S-MAIN-MIB", AICSMIB);
        if (!(SCCGWA.COMM_AREA.DBIO_FLG == '0' 
            || SCCGWA.COMM_AREA.DBIO_FLG == '1')) {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ AITMIB ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITMIB";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZSCMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-S-MAIN-CMIB", AICSCMIB);
        if (!(SCCGWA.COMM_AREA.DBIO_FLG == '0' 
            || SCCGWA.COMM_AREA.DBIO_FLG == '1')) {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ AITCMIB ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITCMIB";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B21_01_FOUND_GL_BOOK_FLG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQBKPM);
        BPCQBKPM.FUNC = 'B';
        S000_CALL_BPZQBKPM();
        if (pgmRtn) return;
        WS_QBKPM_CNT = BPCQBKPM.CNT;
        for (WS_CNT = 1; WS_CNT <= BPCQBKPM.CNT; WS_CNT += 1) {
            WS_BOOK_FLG_ALL = WS_BOOK_FLG_ALL.get(WS_CNT-1);
            WS_BOOK_FLG_ALL.WS_BOOK_FLG = BPCQBKPM.DATA[WS_CNT-1].BOOK_FLG;
            WS_BOOK_FLG_ALL.set(WS_CNT-1, WS_BOOK_FLG_ALL);
            WS_BOOK_FLG_ALL = WS_BOOK_FLG_ALL.get(WS_CNT-1);
            WS_BOOK_FLG_ALL.WS_COA_FLG = BPCQBKPM.DATA[WS_CNT-1].COA_FLG;
            WS_BOOK_FLG_ALL.set(WS_CNT-1, WS_BOOK_FLG_ALL);
        }
    }
    public void B21_02_MOVE_GL_BOOK_FLG() throws IOException,SQLException,Exception {
        if (!AIRITM.KEY.COA_FLG.equalsIgnoreCase(WS_COA_FLG_TMP)) {
            WS_I = 0;
            for (WS_CNT = 1; WS_CNT <= WS_QBKPM_CNT; WS_CNT += 1) {
                if (AIRITM.KEY.COA_FLG.equalsIgnoreCase(WS_BOOK_FLG_ALL.get(WS_CNT-1).WS_COA_FLG)) {
                    WS_I = WS_I + 1;
                    WS_BOOK_FLG_ALL = WS_BOOK_FLG_ALL.get(WS_I-1);
                    WS_BOOK_FLG_ALL.WS_BOOK_FLG_TMP = WS_BOOK_FLG_ALL.get(WS_CNT-1).WS_BOOK_FLG;
                    WS_BOOK_FLG_ALL.set(WS_I-1, WS_BOOK_FLG_ALL);
                }
                CEP.TRC(SCCGWA, AIRITM.KEY.COA_FLG);
                CEP.TRC(SCCGWA, WS_BOOK_FLG_ALL.get(WS_I-1).WS_BOOK_FLG_TMP);
            }
            WS_COA_FLG_TMP = AIRITM.KEY.COA_FLG;
        }
    }
    public void S000_CALL_BPZQBKPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-Q-MAINT-AMBKP", BPCQBKPM);
        if (BPCQBKPM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = "" + BPCQBKPM.RC.RC_RTNCODE;
            JIBS_tmp_int = WS_ERR_MSG.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_ERR_MSG = "0" + WS_ERR_MSG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T00_OPEN_AITMST() throws IOException,SQLException,Exception {
        AITMST_BR.rp = new DBParm();
        AITMST_BR.rp.TableName = "AITMST";
        AITMST_BR.rp.where = "GL_BOOK_FLG = :AIRMST.KEY.GL_BOOK_FLG "
            + "AND ITM_NO = :AIRMST.KEY.ITM_NO";
        IBS.STARTBR(SCCGWA, AIRMST, this, AITMST_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            WS_MST_ITM_NO = AIRMST.KEY.ITM_NO;
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "OPEN AITMST ERROR!" + WS_MST_ITM_NO;
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T00_FETCH_AITMST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, AIRMST, this, AITMST_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_EOF_FLG = 'Y';
        } else {
            WS_MST_ITM_NO = AIRMST.KEY.ITM_NO;
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "FETCH AITMST ERROR!" + WS_MST_ITM_NO;
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T00_CLOSE_AITMST() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, AITMST_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "CLOSE AITMST ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.TX_RMK = " ";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.FMT_ID = "AIRITM";
