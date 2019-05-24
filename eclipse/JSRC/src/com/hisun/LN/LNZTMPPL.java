package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZTMPPL {
    DBParm LNTTMPP_RD;
    brParm LNTTMPP_BR = new brParm();
    boolean pgmRtn = false;
    String K_TBL_TMPP = "LNTTMPP";
    int WS_LEN = 0;
    short WS_TOT_TENORS = 0;
    double WS_TOT_PRIN = 0;
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    LNRTMPP LNRTMPP = new LNRTMPP();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCGWA SCCGWA;
    LNCRTMPP LNCRTMPP;
    LNRTMPP LNRTMPP1;
    public void MP(SCCGWA SCCGWA, LNCRTMPP LNCRTMPP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCRTMPP = LNCRTMPP;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZTMPPL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRTMPP);
        LNRTMPP1 = (LNRTMPP) LNCRTMPP.REC_PTR;
        LNCRTMPP.RETURN_INFO = 'F';
        IBS.CLONE(SCCGWA, LNRTMPP1, LNRTMPP);
        LNCRTMPP.RC.RC_MMO = "LN";
        LNCRTMPP.RC.RC_CODE = 0;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNRTMPP.KEY.PHS_NO);
        CEP.TRC(SCCGWA, LNRTMPP.ACTION);
        if (LNCRTMPP.FUNC == 'A') {
            B02_FUNC_WRITE();
            if (pgmRtn) return;
        } else if (LNCRTMPP.FUNC == 'D') {
            B03_FUNC_DELETE();
            if (pgmRtn) return;
        } else if (LNCRTMPP.FUNC == 'U') {
            B04_FUNC_REWRITE();
            if (pgmRtn) return;
        } else if (LNCRTMPP.FUNC == 'I') {
            B05_FUNC_READ();
            if (pgmRtn) return;
        } else if (LNCRTMPP.FUNC == 'R') {
            B06_FUNC_READ_UPDATE();
            if (pgmRtn) return;
        } else if (LNCRTMPP.FUNC == 'B') {
            B07_FUNC_BROWSE();
            if (pgmRtn) return;
        } else if (LNCRTMPP.FUNC == 'G') {
            B08_FUNC_GROUP();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE, LNCRTMPP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, LNRTMPP, LNRTMPP1);
    }
    public void B01_CHECK() throws IOException,SQLException,Exception {
    }
    public void B02_FUNC_WRITE() throws IOException,SQLException,Exception {
        T00_WRITE_LNTTMPP();
        if (pgmRtn) return;
    }
    public void B03_FUNC_DELETE() throws IOException,SQLException,Exception {
        T00_DELETE_LNTTMPP();
        if (pgmRtn) return;
    }
    public void B04_FUNC_REWRITE() throws IOException,SQLException,Exception {
        T00_REWRITE_LNTTMPP();
        if (pgmRtn) return;
    }
    public void B05_FUNC_READ() throws IOException,SQLException,Exception {
        T00_READ_LNTTMPP();
        if (pgmRtn) return;
    }
    public void B06_FUNC_READ_UPDATE() throws IOException,SQLException,Exception {
        WS_UPDATE_FLG = 'Y';
        CEP.TRC(SCCGWA, LNRTMPP.KEY.TRAN_SEQ);
        CEP.TRC(SCCGWA, LNRTMPP.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRTMPP.KEY.SUB_CTA_NO);
        CEP.TRC(SCCGWA, LNRTMPP.KEY.PHS_NO);
        T00_READUPDATE_LNTTMPP();
        if (pgmRtn) return;
        if (WS_FOUND_FLG != 'Y') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_TMPP_NOTFND, LNCRTMPP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNRTMPP.KEY.TRAN_SEQ);
        CEP.TRC(SCCGWA, LNRTMPP.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRTMPP.KEY.SUB_CTA_NO);
        CEP.TRC(SCCGWA, LNRTMPP.KEY.PHS_NO);
    }
    public void B07_FUNC_BROWSE() throws IOException,SQLException,Exception {
        if (LNCRTMPP.OPT == 'S') {
            T000_STARTBR_PROCESS();
            if (pgmRtn) return;
        }
        if (LNCRTMPP.OPT == 'O') {
            T000_STARTBR_PROCESS1();
            if (pgmRtn) return;
        }
        if (LNCRTMPP.OPT == 'R') {
            T000_READ_NEXT_PROCESS();
            if (pgmRtn) return;
        }
        if (LNCRTMPP.OPT == 'E') {
            T000_ENDBR_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void B08_FUNC_GROUP() throws IOException,SQLException,Exception {
        T00_GROUP_LNTTMPP();
        if (pgmRtn) return;
    }
    public void R00_READ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRTMPP);
        if (WS_UPDATE_FLG == 'Y') {
            T00_READUPDATE_LNTTMPP();
            if (pgmRtn) return;
        } else {
            T00_READ_LNTTMPP();
            if (pgmRtn) return;
        }
    }
    public void T00_WRITE_LNTTMPP() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "1477WRITE");
        CEP.TRC(SCCGWA, LNRTMPP.KEY.PHS_NO);
        LNRTMPP.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRTMPP.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRTMPP.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRTMPP.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTTMPP_RD = new DBParm();
        LNTTMPP_RD.TableName = "LNTTMPP";
        LNTTMPP_RD.errhdl = true;
        IBS.WRITE(SCCGWA, LNRTMPP, LNTTMPP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            LNCRTMPP.RETURN_INFO = 'F';
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_NORMAL, LNCRTMPP.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            LNCRTMPP.RETURN_INFO = 'D';
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_TMPP_EXIST, LNCRTMPP.RC);
        } else {
            LNCRTMPP.RETURN_INFO = 'O';
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_TMPP;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
        }
    }
    public void T00_DELETE_LNTTMPP() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "1477DELETE");
        CEP.TRC(SCCGWA, LNRTMPP.KEY.PHS_NO);
        LNTTMPP_RD = new DBParm();
        LNTTMPP_RD.TableName = "LNTTMPP";
        IBS.DELETE(SCCGWA, LNRTMPP, LNTTMPP_RD);
    }
    public void T00_READ_LNTTMPP() throws IOException,SQLException,Exception {
        LNTTMPP_RD = new DBParm();
        LNTTMPP_RD.TableName = "LNTTMPP";
        LNTTMPP_RD.errhdl = true;
        IBS.READ(SCCGWA, LNRTMPP, LNTTMPP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            LNCRTMPP.RETURN_INFO = 'F';
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_NORMAL, LNCRTMPP.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            LNCRTMPP.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_TMPP_NOTFND, LNCRTMPP.RC);
        } else {
            LNCRTMPP.RETURN_INFO = 'O';
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_TMPP;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
        }
    }
    public void T00_READUPDATE_LNTTMPP() throws IOException,SQLException,Exception {
        LNTTMPP_RD = new DBParm();
        LNTTMPP_RD.TableName = "LNTTMPP";
        LNTTMPP_RD.upd = true;
        LNTTMPP_RD.errhdl = true;
        IBS.READ(SCCGWA, LNRTMPP, LNTTMPP_RD);
        WS_FOUND_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        }
    }
    public void T00_REWRITE_LNTTMPP() throws IOException,SQLException,Exception {
        LNRTMPP.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRTMPP.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTTMPP_RD = new DBParm();
        LNTTMPP_RD.TableName = "LNTTMPP";
        IBS.REWRITE(SCCGWA, LNRTMPP, LNTTMPP_RD);
    }
    public void T000_STARTBR_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNRTMPP.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRTMPP.KEY.TRAN_SEQ);
        LNTTMPP_BR.rp = new DBParm();
        LNTTMPP_BR.rp.TableName = "LNTTMPP";
        LNTTMPP_BR.rp.where = "CONTRACT_NO = :LNRTMPP.KEY.CONTRACT_NO "
            + "AND TRAN_SEQ = :LNRTMPP.KEY.TRAN_SEQ "
            + "AND ( ACTION = 'I' "
            + "OR ACTION = 'A' "
            + "OR ACTION = 'U' "
            + "OR ACTION = 'M' "
            + "OR ACTION = 'D' )";
        LNTTMPP_BR.rp.errhdl = true;
        LNTTMPP_BR.rp.order = "CONTRACT_NO, SUB_CTA_NO, PHS_NO";
        IBS.STARTBR(SCCGWA, LNRTMPP, this, LNTTMPP_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            LNCRTMPP.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_TMPP_NOTFND, LNCRTMPP.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            LNCRTMPP.RETURN_INFO = 'F';
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_NORMAL, LNCRTMPP.RC);
        } else {
            LNCRTMPP.RETURN_INFO = 'O';
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCRTMPP.RC);
        }
    }
    public void T000_STARTBR_PROCESS1() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNRTMPP.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRTMPP.KEY.TRAN_SEQ);
        LNTTMPP_BR.rp = new DBParm();
        LNTTMPP_BR.rp.TableName = "LNTTMPP";
        LNTTMPP_BR.rp.where = "CONTRACT_NO = :LNRTMPP.KEY.CONTRACT_NO "
            + "AND TRAN_SEQ = :LNRTMPP.KEY.TRAN_SEQ "
            + "AND ( ACTION = 'I' "
            + "OR ACTION = 'A' "
            + "OR ACTION = 'U' "
            + "OR ACTION = 'M' )";
        LNTTMPP_BR.rp.errhdl = true;
        LNTTMPP_BR.rp.order = "CONTRACT_NO, SUB_CTA_NO, PHS_NO";
        IBS.STARTBR(SCCGWA, LNRTMPP, this, LNTTMPP_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            LNCRTMPP.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_TMPP_NOTFND, LNCRTMPP.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            LNCRTMPP.RETURN_INFO = 'F';
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_NORMAL, LNCRTMPP.RC);
        } else {
            LNCRTMPP.RETURN_INFO = 'O';
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCRTMPP.RC);
        }
    }
    public void T000_READ_NEXT_PROCESS() throws IOException,SQLException,Exception {
        LNTTMPP_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, LNRTMPP, this, LNTTMPP_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            LNCRTMPP.RETURN_INFO = 'E';
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_TMPP_NOTFND, LNCRTMPP.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            LNCRTMPP.RETURN_INFO = 'F';
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_NORMAL, LNCRTMPP.RC);
        } else {
            LNCRTMPP.RETURN_INFO = 'O';
        }
    }
    public void T000_ENDBR_PROCESS() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTTMPP_BR);
    }
    public void T00_GROUP_LNTTMPP() throws IOException,SQLException,Exception {
        WS_TOT_TENORS = 0;
        WS_TOT_PRIN = 0;
        LNTTMPP_RD = new DBParm();
        LNTTMPP_RD.TableName = "LNTTMPP";
        LNTTMPP_RD.set = "WS-TOT-TENORS=IFNULL(SUM(PHS_TOT_TERM),0),WS-TOT-PRIN=IFNULL(SUM(PHS_PRIN_AMT),0),";
        LNTTMPP_RD.where = "( TRAN_SEQ = :LNRTMPP.KEY.TRAN_SEQ ) "
            + "AND ( CONTRACT_NO = :LNRTMPP.KEY.CONTRACT_NO ) "
            + "AND ( PHS_NO < :LNRTMPP.KEY.PHS_NO ) "
            + "AND ( ACTION = 'I' "
            + "OR ACTION = 'A' "
            + "OR ACTION = 'U' "
            + "OR ACTION = 'M' )";
        IBS.GROUP(SCCGWA, LNRTMPP, this, LNTTMPP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            LNCRTMPP.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_TMPP_NOTFND, LNCRTMPP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '3') {
            LNCRTMPP.RETURN_INFO = 'O';
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCRTMPP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        LNCRTMPP.TOT_TENORS = WS_TOT_TENORS;
        LNCRTMPP.TOT_PRIN = WS_TOT_PRIN;
        CEP.TRC(SCCGWA, "147777777");
        CEP.TRC(SCCGWA, LNCRTMPP.TOT_TENORS);
        CEP.TRC(SCCGWA, LNCRTMPP.TOT_PRIN);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCRTMPP.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "LNCRTMPP=");
            CEP.TRC(SCCGWA, LNCRTMPP);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
