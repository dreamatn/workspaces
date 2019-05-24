package com.hisun.AI;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class AIZRHMIB {
    DBParm AITHMIB_RD;
    brParm AITHMIB_BR = new brParm();
    boolean pgmRtn = false;
    String TBL_AITHMIB = "AITHMIB";
    int WS_END_DT = 0;
    double WS_TOT_AMT = 0;
    int WS_TOT_CNT = 0;
    double WS_END_AMT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    AIRHMIB AIRHMIB = new AIRHMIB();
    SCCGWA SCCGWA;
    AICRHMIB AICRHMIB;
    AIRHMIB AIRLHMIB;
    public void MP(SCCGWA SCCGWA, AICRHMIB AICRHMIB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICRHMIB = AICRHMIB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZRHMIB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        AIRLHMIB = (AIRHMIB) AICRHMIB.POINTER;
        IBS.init(SCCGWA, AIRHMIB);
        AICRHMIB.REC_LEN = 1202;
        IBS.CLONE(SCCGWA, AIRLHMIB, AIRHMIB);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (AICRHMIB.FUNC == 'Q') {
            B040_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRHMIB.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRHMIB.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRHMIB.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRHMIB.FUNC == 'U') {
            B030_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (AICRHMIB.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_INPUT_ERROR, AICRHMIB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, AIRHMIB, AIRLHMIB);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        AITHMIB_RD = new DBParm();
        AITHMIB_RD.TableName = "AITHMIB";
        AITHMIB_RD.errhdl = true;
        IBS.WRITE(SCCGWA, AIRHMIB, AITHMIB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRHMIB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            AICRHMIB.RETURN_INFO = 'D';
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_HMIB_ALREADY_EXIST, AICRHMIB.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_AITHMIB;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        AITHMIB_RD = new DBParm();
        AITHMIB_RD.TableName = "AITHMIB";
        AITHMIB_RD.upd = true;
        IBS.READ(SCCGWA, AIRHMIB, AITHMIB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRHMIB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRHMIB.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_AITHMIB;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        AITHMIB_RD = new DBParm();
        AITHMIB_RD.TableName = "AITHMIB";
        IBS.REWRITE(SCCGWA, AIRHMIB, AITHMIB_RD);
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        AITHMIB_RD = new DBParm();
        AITHMIB_RD.TableName = "AITHMIB";
        IBS.DELETE(SCCGWA, AIRHMIB, AITHMIB_RD);
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AICRHMIB);
        CEP.TRC(SCCGWA, AIRHMIB);
        AITHMIB_RD = new DBParm();
        AITHMIB_RD.TableName = "AITHMIB";
        IBS.READ(SCCGWA, AIRHMIB, AITHMIB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRHMIB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRHMIB.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_AITHMIB;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (AICRHMIB.OPT == 'S') {
            T000_STARTBR_AITHMIB();
            if (pgmRtn) return;
        } else if (AICRHMIB.OPT == 'F') {
            T000_STARTBR_AITHMIB_01();
            if (pgmRtn) return;
        } else if (AICRHMIB.OPT == 'Y') {
            T000_STARTBR_AITHMIB_CCY();
            if (pgmRtn) return;
        } else if (AICRHMIB.OPT == 'N') {
            T000_READNEXT_AITHMIB();
            if (pgmRtn) return;
        } else if (AICRHMIB.OPT == 'E') {
            T000_ENDBR_AITHMIB();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_INPUT_ERROR, AICRHMIB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_AITHMIB() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BROWSE");
        CEP.TRC(SCCGWA, AIRHMIB.KEY.BOOK_FLG);
        CEP.TRC(SCCGWA, AIRHMIB.KEY.AC);
        CEP.TRC(SCCGWA, AIRHMIB.VAL_DATE);
        CEP.TRC(SCCGWA, AICRHMIB.END_DT);
        CEP.TRC(SCCGWA, AIRHMIB.KEY.SET_NO);
        CEP.TRC(SCCGWA, AIRHMIB.SIGN);
        WS_END_DT = AICRHMIB.END_DT;
        WS_END_AMT = AICRHMIB.END_AMT;
        AITHMIB_BR.rp = new DBParm();
        AITHMIB_BR.rp.TableName = "AITHMIB";
        AITHMIB_BR.rp.where = "AC = :AIRHMIB.KEY.AC "
            + "AND TR_DATE BETWEEN :AIRHMIB.VAL_DATE "
            + "AND :WS_END_DT "
            + "AND BOOK_FLG = :AIRHMIB.KEY.BOOK_FLG "
            + "AND ( ' ' = :AIRHMIB.KEY.SET_NO "
            + "OR SET_NO = :AIRHMIB.KEY.SET_NO ) "
            + "AND ( ' ' = :AIRHMIB.SIGN "
            + "OR SIGN = :AIRHMIB.SIGN ) "
            + "AND ( ( ' ' = :AIRHMIB.AMT "
            + "AND 0 = :WS_END_AMT ) "
            + "OR ( AMT BETWEEN :AIRHMIB.AMT "
            + "AND :WS_END_AMT ) )";
        AITHMIB_BR.rp.order = "AC,TR_DATE,SET_NO,SET_SEQ,TR_TIME";
        IBS.STARTBR(SCCGWA, AIRHMIB, this, AITHMIB_BR);
    }
    public void T000_STARTBR_AITHMIB_CCY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BROWSE CCY");
        CEP.TRC(SCCGWA, AIRHMIB.KEY.BOOK_FLG);
        CEP.TRC(SCCGWA, AIRHMIB.BR);
        CEP.TRC(SCCGWA, AIRHMIB.ITM);
        CEP.TRC(SCCGWA, AIRHMIB.SEQ);
        CEP.TRC(SCCGWA, AIRHMIB.VAL_DATE);
        CEP.TRC(SCCGWA, AICRHMIB.END_DT);
        CEP.TRC(SCCGWA, AIRHMIB.KEY.SET_NO);
        CEP.TRC(SCCGWA, AIRHMIB.SIGN);
        CEP.TRC(SCCGWA, "TSTCCY");
        WS_END_DT = AICRHMIB.END_DT;
        WS_END_AMT = AICRHMIB.END_AMT;
        AITHMIB_BR.rp = new DBParm();
        AITHMIB_BR.rp.TableName = "AITHMIB";
        AITHMIB_BR.rp.where = "BR = :AIRHMIB.BR "
            + "AND ITM = :AIRHMIB.ITM "
            + "AND SEQ = :AIRHMIB.SEQ "
            + "AND TR_DATE BETWEEN :AIRHMIB.VAL_DATE "
            + "AND :WS_END_DT "
            + "AND BOOK_FLG = :AIRHMIB.KEY.BOOK_FLG "
            + "AND ( ' ' = :AIRHMIB.KEY.SET_NO "
            + "OR SET_NO = :AIRHMIB.KEY.SET_NO ) "
            + "AND ( ' ' = :AIRHMIB.SIGN "
            + "OR SIGN = :AIRHMIB.SIGN ) "
            + "AND ( ( ' ' = :AIRHMIB.AMT "
            + "AND 0 = :WS_END_AMT ) "
            + "OR ( AMT BETWEEN :AIRHMIB.AMT "
            + "AND :WS_END_AMT ) )";
        AITHMIB_BR.rp.order = "AC,TR_DATE,SET_NO,SET_SEQ,TR_TIME";
        IBS.STARTBR(SCCGWA, AIRHMIB, this, AITHMIB_BR);
    }
    public void T000_STARTBR_AITHMIB_01() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BROWSE");
        CEP.TRC(SCCGWA, AIRHMIB.KEY.AC);
        AITHMIB_RD = new DBParm();
        AITHMIB_RD.TableName = "AITHMIB";
        AITHMIB_RD.col = "SET_SEQ";
        AITHMIB_RD.where = "AC = :AIRHMIB.KEY.AC "
            + "AND TR_DATE = :AIRHMIB.KEY.TR_DATE "
            + "AND BOOK_FLG = :AIRHMIB.KEY.BOOK_FLG";
        AITHMIB_RD.fst = true;
        AITHMIB_RD.order = "SET_SEQ DESC";
        IBS.READ(SCCGWA, AIRHMIB, this, AITHMIB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRHMIB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRHMIB.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_AITHMIB;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_AITHMIB() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, AIRHMIB, this, AITHMIB_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRHMIB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRHMIB.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_AITHMIB;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_AITHMIB() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, AITHMIB_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (AICRHMIB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "AICRHMIB = ");
            CEP.TRC(SCCGWA, AICRHMIB);
            CEP.TRC(SCCGWA, "AIRHMIB = ");
            CEP.TRC(SCCGWA, AIRHMIB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
