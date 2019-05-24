package com.hisun.AI;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class AIZRMIB {
    DBParm AITMIB_RD;
    brParm AITMIB_BR = new brParm();
    boolean pgmRtn = false;
    String TBL_AITMIB = "AITMIB";
    String WS_TX_CD = " ";
    char WS_CON_FLG = ' ';
    AIZRMIB_WS_AC_NO WS_AC_NO = new AIZRMIB_WS_AC_NO();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    AIRMIB AIRMIB = new AIRMIB();
    SCCGWA SCCGWA;
    AICRMIB AICRMIB;
    AIRMIB AIRLMIB;
    public void MP(SCCGWA SCCGWA, AICRMIB AICRMIB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICRMIB = AICRMIB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZRMIB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICRMIB.RC);
        AIRLMIB = (AIRMIB) AICRMIB.POINTER;
        IBS.init(SCCGWA, AIRMIB);
        AICRMIB.REC_LEN = 634;
        IBS.CLONE(SCCGWA, AIRLMIB, AIRMIB);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (AICRMIB.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRMIB.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRMIB.FUNC == 'U') {
            B030_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRMIB.FUNC == 'Q') {
            B040_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRMIB.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRMIB.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRMIB.FUNC == 'L') {
            B070_GET_BAL_COUNT();
            if (pgmRtn) return;
        } else if (AICRMIB.FUNC == 'S') {
            B080_GET_STS_COUNT();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_INPUT_ERROR, AICRMIB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, AIRMIB, AIRLMIB);
    }
    public void R000_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (AIRMIB.KEY.GL_BOOK.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_BOOK_FLG_MUST_INPUT, AICRMIB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if ((AIRMIB.KEY.BR == 0 
            && AIRMIB.KEY.CCY.trim().length() == 0 
            && AIRMIB.KEY.SEQ == 0 
            && AIRMIB.KEY.ITM_NO.trim().length() == 0) 
            && AIRMIB.AC_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_INPUT_ERROR, AICRMIB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (AIRMIB.AC_NO.trim().length() > 0) {
            WS_CON_FLG = '1';
        } else {
            WS_CON_FLG = '2';
        }
        CEP.TRC(SCCGWA, WS_CON_FLG);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        AITMIB_RD = new DBParm();
        AITMIB_RD.TableName = "AITMIB";
        AITMIB_RD.errhdl = true;
        IBS.WRITE(SCCGWA, AIRMIB, AITMIB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRMIB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            AICRMIB.RETURN_INFO = 'D';
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_MIB_ALREADY_EXIST, AICRMIB.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_AITMIB;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        AITMIB_RD = new DBParm();
        AITMIB_RD.TableName = "AITMIB";
        AITMIB_RD.upd = true;
        IBS.READ(SCCGWA, AIRMIB, AITMIB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRMIB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRMIB.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_AITMIB;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        AITMIB_RD = new DBParm();
        AITMIB_RD.TableName = "AITMIB";
        IBS.REWRITE(SCCGWA, AIRMIB, AITMIB_RD);
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        AITMIB_RD = new DBParm();
        AITMIB_RD.TableName = "AITMIB";
        IBS.READ(SCCGWA, AIRMIB, AITMIB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRMIB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRMIB.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_AITMIB;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        AITMIB_RD = new DBParm();
        AITMIB_RD.TableName = "AITMIB";
        IBS.DELETE(SCCGWA, AIRMIB, AITMIB_RD);
    }
    public void T000_READ_AITMIB_CON1() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIRMIB.AC_NO);
        IBS.CPY2CLS(SCCGWA, AIRMIB.AC_NO, WS_AC_NO);
        AIRMIB.KEY.BR = WS_AC_NO.WS_BR;
        AIRMIB.KEY.CCY = WS_AC_NO.WS_CCY;
        AIRMIB.KEY.ITM_NO = WS_AC_NO.WS_ITM;
        AIRMIB.KEY.SEQ = WS_AC_NO.WS_AC_SEQ;
        AITMIB_RD = new DBParm();
        AITMIB_RD.TableName = "AITMIB";
        AITMIB_RD.where = "ITM_NO = :AIRMIB.KEY.ITM_NO "
            + "AND BR = :AIRMIB.KEY.BR "
            + "AND CCY = :AIRMIB.KEY.CCY "
            + "AND SEQ = :AIRMIB.KEY.SEQ "
            + "AND GL_BOOK = :AIRMIB.KEY.GL_BOOK";
        AITMIB_RD.fst = true;
        IBS.READ(SCCGWA, AIRMIB, this, AITMIB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRMIB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRMIB.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_AITMIB;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_AITMIB_CON2() throws IOException,SQLException,Exception {
        AITMIB_RD = new DBParm();
        AITMIB_RD.TableName = "AITMIB";
        AITMIB_RD.where = "GL_BOOK = :AIRMIB.KEY.GL_BOOK "
            + "AND ITM_NO = :AIRMIB.KEY.ITM_NO "
            + "AND BR = :AIRMIB.KEY.BR "
            + "AND CCY = :AIRMIB.KEY.CCY "
            + "AND SEQ = :AIRMIB.KEY.SEQ";
        AITMIB_RD.fst = true;
        IBS.READ(SCCGWA, AIRMIB, this, AITMIB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRMIB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRMIB.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_AITMIB;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AICRMIB.OPT);
        if (AICRMIB.OPT == 'I') {
            T000_STARTBR_ITM();
            if (pgmRtn) return;
        } else if (AICRMIB.OPT == '3') {
            T000_STARTBR_ITM_BR();
            if (pgmRtn) return;
        } else if (AICRMIB.OPT == '1') {
            CEP.TRC(SCCGWA, AIRMIB.KEY.GL_BOOK);
            if (AIRMIB.KEY.GL_BOOK.trim().length() == 0) {
                T000_STARTBR_AITMIB_1_1();
                if (pgmRtn) return;
            } else {
                T000_STARTBR_AITMIB_1_2();
                if (pgmRtn) return;
            }
        } else if (AICRMIB.OPT == '2') {
            CEP.TRC(SCCGWA, AIRMIB.KEY.BR);
            if (AIRMIB.KEY.BR == 0) {
                T000_STARTBR_AITMIB_2_1();
                if (pgmRtn) return;
            } else {
                T000_STARTBR_AITMIB_2_2();
                if (pgmRtn) return;
            }
        } else if (AICRMIB.OPT == 'F') {
            R000_CHECK_INPUT();
            if (pgmRtn) return;
            if (WS_CON_FLG == '1') {
                T000_READ_AITMIB_CON1();
                if (pgmRtn) return;
            } else {
                T000_READ_AITMIB_CON2();
                if (pgmRtn) return;
            }
        } else if (AICRMIB.OPT == '9') {
            CEP.TRC(SCCGWA, AIRMIB.KEY.GL_BOOK);
            CEP.TRC(SCCGWA, AIRMIB.KEY.BR);
            CEP.TRC(SCCGWA, AIRMIB.KEY.ITM_NO);
            T000_STARTBR_AITMIB_9();
            if (pgmRtn) return;
        } else if (AICRMIB.OPT == 'C') {
            T000_STARTBR_CNM();
            if (pgmRtn) return;
        } else if (AICRMIB.OPT == 'M') {
            T000_STARTBR_CNM_BR();
            if (pgmRtn) return;
        } else if (AICRMIB.OPT == 'N') {
            T000_READNEXT_AITMIB();
            if (pgmRtn) return;
        } else if (AICRMIB.OPT == 'E') {
            T000_ENDBR_AITMIB();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_INPUT_ERROR, AICRMIB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_AC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BROWSE AC");
        CEP.TRC(SCCGWA, AIRMIB.AC_NO);
        CEP.TRC(SCCGWA, AIRMIB.KEY.GL_BOOK);
        CEP.TRC(SCCGWA, AIRMIB.KEY.ITM_NO);
        CEP.TRC(SCCGWA, AIRMIB.KEY.BR);
        CEP.TRC(SCCGWA, AIRMIB.KEY.SEQ);
        CEP.TRC(SCCGWA, AIRMIB.KEY.CCY);
        AITMIB_BR.rp = new DBParm();
        AITMIB_BR.rp.TableName = "AITMIB";
        AITMIB_BR.rp.where = "GL_BOOK = :AIRMIB.KEY.GL_BOOK "
            + "AND ITM_NO = :AIRMIB.KEY.ITM_NO "
            + "AND BR = :AIRMIB.KEY.BR "
            + "AND CCY = :AIRMIB.KEY.CCY "
            + "AND SEQ = :AIRMIB.KEY.SEQ";
        IBS.STARTBR(SCCGWA, AIRMIB, this, AITMIB_BR);
    }
    public void T000_STARTBR_ITM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BROWSE ITM");
        CEP.TRC(SCCGWA, AIRMIB.AC_NO);
        CEP.TRC(SCCGWA, AIRMIB.KEY.GL_BOOK);
        CEP.TRC(SCCGWA, AIRMIB.KEY.ITM_NO);
        CEP.TRC(SCCGWA, AIRMIB.KEY.BR);
        CEP.TRC(SCCGWA, AIRMIB.KEY.SEQ);
        CEP.TRC(SCCGWA, AIRMIB.AC_NO);
        AITMIB_BR.rp = new DBParm();
        AITMIB_BR.rp.TableName = "AITMIB";
        AITMIB_BR.rp.where = "GL_BOOK = :AIRMIB.KEY.GL_BOOK "
            + "AND ITM_NO = :AIRMIB.KEY.ITM_NO "
            + "AND ( ' ' = :AIRMIB.KEY.BR "
            + "OR BR = :AIRMIB.KEY.BR ) "
            + "AND ( 0 = :AIRMIB.KEY.CCY "
            + "OR CCY = :AIRMIB.KEY.CCY ) "
            + "AND ( ' ' = :AIRMIB.KEY.SEQ "
            + "OR SEQ = :AIRMIB.KEY.SEQ ) "
            + "AND ( 0 = :AIRMIB.AC_NO "
            + "OR AC_NO = :AIRMIB.AC_NO )";
        AITMIB_BR.rp.order = "AC_NO,ITM_NO,SEQ,GL_BOOK,BR,CCY";
        IBS.STARTBR(SCCGWA, AIRMIB, this, AITMIB_BR);
    }
    public void T000_STARTBR_ITM_BR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BROWSE ITM AND BR");
        CEP.TRC(SCCGWA, AIRMIB.AC_NO);
        CEP.TRC(SCCGWA, AIRMIB.KEY.GL_BOOK);
        CEP.TRC(SCCGWA, AIRMIB.KEY.ITM_NO);
        CEP.TRC(SCCGWA, AIRMIB.KEY.BR);
        CEP.TRC(SCCGWA, AIRMIB.KEY.SEQ);
        AITMIB_BR.rp = new DBParm();
        AITMIB_BR.rp.TableName = "AITMIB";
        AITMIB_BR.rp.where = "GL_BOOK = :AIRMIB.KEY.GL_BOOK "
            + "AND ITM_NO = :AIRMIB.KEY.ITM_NO "
            + "AND BR = :AIRMIB.KEY.BR "
            + "AND ( 0 = :AIRMIB.KEY.CCY "
            + "OR CCY = :AIRMIB.KEY.CCY ) "
            + "AND ( ' ' = :AIRMIB.KEY.SEQ "
            + "OR SEQ = :AIRMIB.KEY.SEQ ) "
            + "AND ( 0 = :AIRMIB.AC_NO "
            + "OR AC_NO = :AIRMIB.AC_NO )";
        AITMIB_BR.rp.order = "ITM_NO,BR,CCY,SEQ,AC_NO";
        IBS.STARTBR(SCCGWA, AIRMIB, this, AITMIB_BR);
    }
    public void T000_STARTBR_BR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BROWSE BR");
        CEP.TRC(SCCGWA, AIRMIB.KEY.BR);
        CEP.TRC(SCCGWA, AIRMIB.AC_NO);
        CEP.TRC(SCCGWA, AIRMIB.KEY.GL_BOOK);
        CEP.TRC(SCCGWA, AIRMIB.KEY.ITM_NO);
        CEP.TRC(SCCGWA, AIRMIB.KEY.SEQ);
        AITMIB_BR.rp = new DBParm();
        AITMIB_BR.rp.TableName = "AITMIB";
        AITMIB_BR.rp.where = "GL_BOOK = :AIRMIB.KEY.GL_BOOK "
            + "AND BR = :AIRMIB.KEY.BR "
            + "AND ( 0 = :AIRMIB.KEY.ITM_NO "
            + "OR ITM_NO = :AIRMIB.KEY.ITM_NO ) "
            + "AND ( ' ' = :AIRMIB.KEY.CCY "
            + "OR CCY = :AIRMIB.KEY.CCY ) "
            + "AND ( ' ' = :AIRMIB.KEY.SEQ "
            + "OR SEQ = :AIRMIB.KEY.SEQ ) "
            + "AND ( 0 = :AIRMIB.AC_NO "
            + "OR AC_NO = :AIRMIB.AC_NO )";
        AITMIB_BR.rp.order = "BR,ITM_NO,CCY,SEQ,AC_NO";
        IBS.STARTBR(SCCGWA, AIRMIB, this, AITMIB_BR);
    }
    public void T000_STARTBR_AITMIB_1_1() throws IOException,SQLException,Exception {
        AITMIB_BR.rp = new DBParm();
        AITMIB_BR.rp.TableName = "AITMIB";
        AITMIB_BR.rp.where = "ITM_NO = :AIRMIB.KEY.ITM_NO";
        IBS.STARTBR(SCCGWA, AIRMIB, this, AITMIB_BR);
    }
    public void T000_STARTBR_AITMIB_1_2() throws IOException,SQLException,Exception {
        AITMIB_BR.rp = new DBParm();
        AITMIB_BR.rp.TableName = "AITMIB";
        AITMIB_BR.rp.where = "ITM_NO = :AIRMIB.KEY.ITM_NO "
            + "AND GL_BOOK = :AIRMIB.KEY.GL_BOOK";
        IBS.STARTBR(SCCGWA, AIRMIB, this, AITMIB_BR);
    }
    public void T000_STARTBR_AITMIB_2_1() throws IOException,SQLException,Exception {
        AITMIB_BR.rp = new DBParm();
        AITMIB_BR.rp.TableName = "AITMIB";
        AITMIB_BR.rp.where = "ITM_NO = :AIRMIB.KEY.ITM_NO "
            + "AND SEQ = :AIRMIB.KEY.SEQ "
            + "AND GL_BOOK = :AIRMIB.KEY.GL_BOOK";
        IBS.STARTBR(SCCGWA, AIRMIB, this, AITMIB_BR);
    }
    public void T000_STARTBR_AITMIB_2_2() throws IOException,SQLException,Exception {
        AITMIB_BR.rp = new DBParm();
        AITMIB_BR.rp.TableName = "AITMIB";
        AITMIB_BR.rp.where = "ITM_NO = :AIRMIB.KEY.ITM_NO "
            + "AND BR = :AIRMIB.KEY.BR "
            + "AND SEQ = :AIRMIB.KEY.SEQ "
            + "AND GL_BOOK = :AIRMIB.KEY.GL_BOOK";
        IBS.STARTBR(SCCGWA, AIRMIB, this, AITMIB_BR);
    }
    public void T000_STARTBR_AITMIB_9() throws IOException,SQLException,Exception {
        AITMIB_BR.rp = new DBParm();
        AITMIB_BR.rp.TableName = "AITMIB";
        AITMIB_BR.rp.where = "ITM_NO = :AIRMIB.KEY.ITM_NO "
            + "AND GL_BOOK = :AIRMIB.KEY.GL_BOOK";
        AITMIB_BR.rp.order = "AC_NO";
        IBS.STARTBR(SCCGWA, AIRMIB, this, AITMIB_BR);
    }
    public void T000_STARTBR_CNM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BROWSE CNM");
        CEP.TRC(SCCGWA, AIRMIB.KEY.GL_BOOK);
        CEP.TRC(SCCGWA, AIRMIB.CHS_NM);
        AITMIB_BR.rp = new DBParm();
        AITMIB_BR.rp.TableName = "AITMIB";
        AITMIB_BR.rp.where = "GL_BOOK = :AIRMIB.KEY.GL_BOOK "
            + "AND ( ' ' = :AIRMIB.CHS_NM "
            + "OR CHS_NM LIKE :AIRMIB.CHS_NM )";
        AITMIB_BR.rp.order = "BR,ITM_NO,CCY,SEQ,AC_NO";
        IBS.STARTBR(SCCGWA, AIRMIB, this, AITMIB_BR);
    }
    public void T000_STARTBR_CNM_BR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BROWSE CNM AND BR");
        CEP.TRC(SCCGWA, AIRMIB.KEY.GL_BOOK);
        CEP.TRC(SCCGWA, AIRMIB.KEY.BR);
        CEP.TRC(SCCGWA, AIRMIB.CHS_NM);
        AITMIB_BR.rp = new DBParm();
        AITMIB_BR.rp.TableName = "AITMIB";
        AITMIB_BR.rp.where = "GL_BOOK = :AIRMIB.KEY.GL_BOOK "
            + "AND BR = :AIRMIB.KEY.BR "
            + "AND ( 0 = :AIRMIB.CHS_NM "
            + "OR CHS_NM LIKE :AIRMIB.CHS_NM )";
        AITMIB_BR.rp.order = "BR,ITM_NO,CCY,SEQ,AC_NO";
        IBS.STARTBR(SCCGWA, AIRMIB, this, AITMIB_BR);
    }
    public void T000_READNEXT_AITMIB() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, AIRMIB, this, AITMIB_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRMIB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRMIB.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_AITMIB;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_AITMIB() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, AITMIB_BR);
    }
    public void B070_GET_BAL_COUNT() throws IOException,SQLException,Exception {
        AITMIB_RD = new DBParm();
        AITMIB_RD.TableName = "AITMIB";
        AITMIB_RD.set = "RMIB-HAV-BAL-CNT=COUNT(*)";
        AITMIB_RD.eqWhere = "BR,ITM_NO";
        AITMIB_RD.where = "CBAL < > 0";
        IBS.GROUP(SCCGWA, AIRMIB, this, AITMIB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRMIB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRMIB.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_AITMIB;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B080_GET_STS_COUNT() throws IOException,SQLException,Exception {
        AITMIB_RD = new DBParm();
        AITMIB_RD.TableName = "AITMIB";
        AITMIB_RD.set = "RMIB-HAV-STS-CNT=COUNT(*)";
        AITMIB_RD.eqWhere = "BR,ITM_NO";
        AITMIB_RD.where = "STS < > 'N' "
            + "AND STS < > 'C'";
        IBS.GROUP(SCCGWA, AIRMIB, this, AITMIB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRMIB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRMIB.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_AITMIB;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (AICRMIB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "AICRMIB = ");
            CEP.TRC(SCCGWA, AICRMIB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
