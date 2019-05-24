package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZRSCHT {
    DBParm LNTSCHT_RD;
    brParm LNTSCHT_BR = new brParm();
    boolean pgmRtn = false;
    int WS_TOT_CNT = 0;
    double WS_TOT_SUM = 0;
    int WS_STR_DATE = 0;
    int WS_END_DATE = 0;
    short WS_TERM_MAX = 0;
    short WS_TERM_MIN = 0;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    LNRSCHT LNRSCHT = new LNRSCHT();
    SCCGWA SCCGWA;
    LNCRSCHT LNCRSCHT;
    LNRSCHT LNRSCHT1;
    public void MP(SCCGWA SCCGWA, LNCRSCHT LNCRSCHT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCRSCHT = LNCRSCHT;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZRSCHT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNRSCHT1 = (LNRSCHT) LNCRSCHT.REC_PTR;
        LNCRSCHT.RETURN_INFO = 'F';
        IBS.init(SCCGWA, LNRSCHT);
        IBS.CLONE(SCCGWA, LNRSCHT1, LNRSCHT);
        LNCRSCHT.RC.RC_MMO = "LN";
        LNCRSCHT.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (LNCRSCHT.FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRSCHT.FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRSCHT.FUNC == 'R') {
            B030_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRSCHT.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRSCHT.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRSCHT.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRSCHT.FUNC == 'G') {
            B060_20_GROUP_TERM_PROC();
            if (pgmRtn) return;
        } else if (LNCRSCHT.FUNC == 'H') {
            B060_20_GROUP_MAX_TERM_PROC();
            if (pgmRtn) return;
        } else if (LNCRSCHT.FUNC == 'M') {
            B060_20_GROUP_MIN_TERM_PROC();
            if (pgmRtn) return;
        } else if (LNCRSCHT.FUNC == 'E') {
            B060_20_GROUP_MIN_TERM1_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCRSCHT.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, LNRSCHT, LNRSCHT1);
    }
    public void B010_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_REC_PROC();
        if (pgmRtn) return;
    }
    public void B020_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_REC_PROC();
        if (pgmRtn) return;
    }
    public void B030_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_UPDATE_PROC();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_REC_PROC();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_REC_PROC();
        if (pgmRtn) return;
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (LNCRSCHT.OPT == 'S') {
            B060_10_START_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (LNCRSCHT.OPT == 'A') {
            B060_20_STBR_BY_KEY_PROC();
            if (pgmRtn) return;
        } else if (LNCRSCHT.OPT == 'T') {
            B060_20_STBR_BY_TERM_PROC();
            if (pgmRtn) return;
        } else if (LNCRSCHT.OPT == 'H') {
            B060_20_STBR_BY_KEY_SHOW();
            if (pgmRtn) return;
        } else if (LNCRSCHT.OPT == 'F') {
            B060_20_STBR_BY_KEY_FIRST_PROC();
            if (pgmRtn) return;
        } else if (LNCRSCHT.OPT == 'I') {
            B060_20_STBR_FIRST2_PROC();
            if (pgmRtn) return;
        } else if (LNCRSCHT.OPT == 'Y') {
            B060_20_STBR_BY_DUE_DTE_FST_2();
            if (pgmRtn) return;
        } else if (LNCRSCHT.OPT == 'D') {
            B060_20_STBR_BY_DUE_DTE_FIRST();
            if (pgmRtn) return;
        } else if (LNCRSCHT.OPT == 'G') {
            B060_20_STBR_BY_DUE_DTE();
            if (pgmRtn) return;
        } else if (LNCRSCHT.OPT == 'C') {
            B060_20_STBR_BY_DUE_DTE_DESC();
            if (pgmRtn) return;
        } else if (LNCRSCHT.OPT == 'K') {
            B060_20_STBR_UPDATE_PROC();
            if (pgmRtn) return;
        } else if (LNCRSCHT.OPT == 'M') {
            B060_20_STBR_BY_SUM_AMT_PROC();
            if (pgmRtn) return;
        } else if (LNCRSCHT.OPT == 'R') {
            B060_30_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (LNCRSCHT.OPT == 'E') {
            B060_50_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (LNCRSCHT.OPT == 'X') {
            B060_70_STBR_BY_KEY_PROC();
            if (pgmRtn) return;
        } else if (LNCRSCHT.OPT == 'Z') {
            B060_70_STBR_BY_KEY_SHOW();
            if (pgmRtn) return;
        } else if (LNCRSCHT.OPT == 'V') {
            B060_70_STBR_BY_VAL_DTE();
            if (pgmRtn) return;
        } else if (LNCRSCHT.OPT == 'W') {
            B060_70_STBR_BY_VAL_DTE_FIRST();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + LNCRSCHT.OPT + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B060_10_START_BROWSE_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_PROC();
        if (pgmRtn) return;
    }
    public void B060_20_STBR_UPDATE_PROC() throws IOException,SQLException,Exception {
        if (LNRSCHT.KEY.TYPE == ' ') {
            T000_STARTBR_UPDATE_PROC();
            if (pgmRtn) return;
        } else {
            T000_STARTBR_UPDATE_PROC2();
            if (pgmRtn) return;
        }
    }
    public void B060_20_STBR_BY_KEY_PROC() throws IOException,SQLException,Exception {
        if (LNRSCHT.KEY.TYPE == ' ') {
            T000_STARTBR_BY_KEY_PROC();
            if (pgmRtn) return;
        } else {
            T000_STARTBR_BY_KEY_PROC2();
            if (pgmRtn) return;
        }
    }
    public void B060_70_STBR_BY_KEY_PROC() throws IOException,SQLException,Exception {
        if (LNRSCHT.KEY.TYPE == ' ') {
            T000_STARTBR_BY_KEY_VD();
            if (pgmRtn) return;
        } else {
            T000_STARTBR_BY_KEY_VD2();
            if (pgmRtn) return;
        }
    }
    public void B060_20_STBR_BY_TERM_PROC() throws IOException,SQLException,Exception {
        if (LNRSCHT.KEY.TYPE == ' ') {
            T000_STARTBR_BY_TERM_PROC();
            if (pgmRtn) return;
        } else {
            T000_STARTBR_BY_TERM_PROC2();
            if (pgmRtn) return;
        }
    }
    public void B060_20_STBR_BY_KEY_SHOW() throws IOException,SQLException,Exception {
        if (LNRSCHT.KEY.TYPE == ' ') {
            T000_STARTBR_BY_KEY_SHOW();
            if (pgmRtn) return;
        } else {
            T000_STARTBR_BY_KEY_SHOW2();
            if (pgmRtn) return;
        }
    }
    public void B060_70_STBR_BY_KEY_SHOW() throws IOException,SQLException,Exception {
        if (LNRSCHT.KEY.TYPE == ' ') {
            T000_STARTBR_BY_KEY_SVD();
            if (pgmRtn) return;
        } else {
            T000_STARTBR_BY_KEY_SVD2();
            if (pgmRtn) return;
        }
    }
    public void B060_20_STBR_BY_KEY_FIRST_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_KEY_FIRST();
        if (pgmRtn) return;
    }
    public void B060_20_STBR_FIRST2_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_KEY_FIRST2();
        if (pgmRtn) return;
    }
    public void B060_20_STBR_BY_DUE_DTE_FST_2() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_DUE_DTE_FST_2();
        if (pgmRtn) return;
    }
    public void B060_20_STBR_BY_DUE_DTE_FIRST() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_DUE_DTE_FIRST();
        if (pgmRtn) return;
    }
    public void B060_70_STBR_BY_VAL_DTE_FIRST() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_VAL_DTE_FIRST();
        if (pgmRtn) return;
    }
    public void B060_20_STBR_BY_DUE_DTE() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_DUE_DTE();
        if (pgmRtn) return;
    }
    public void B060_70_STBR_BY_VAL_DTE() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_VAL_DTE();
        if (pgmRtn) return;
    }
    public void B060_20_STBR_BY_DUE_DTE_DESC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_DUE_DTE_DESC();
        if (pgmRtn) return;
    }
    public void B060_20_STBR_BY_SUM_AMT_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_SUM_AMT_PROC();
        if (pgmRtn) return;
    }
    public void B060_20_GROUP_TERM_PROC() throws IOException,SQLException,Exception {
        T000_GROUP_TERM_PROC();
        if (pgmRtn) return;
    }
    public void B060_20_GROUP_MAX_TERM_PROC() throws IOException,SQLException,Exception {
        T000_GROUP_MAX_TERM_PROC();
        if (pgmRtn) return;
    }
    public void B060_20_GROUP_MIN_TERM_PROC() throws IOException,SQLException,Exception {
        T000_GROUP_MIN_TERM_PROC();
        if (pgmRtn) return;
    }
    public void B060_20_GROUP_MIN_TERM1_PROC() throws IOException,SQLException,Exception {
        T000_GROUP_MIN_TERM1_PROC();
        if (pgmRtn) return;
    }
    public void B060_30_READ_NEXT_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_PROC();
        if (pgmRtn) return;
    }
    public void B060_50_END_BROWSE_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_PROC();
        if (pgmRtn) return;
    }
    public void T000_READ_REC_PROC() throws IOException,SQLException,Exception {
        LNTSCHT_RD = new DBParm();
        LNTSCHT_RD.TableName = "LNTSCHT";
        IBS.READ(SCCGWA, LNRSCHT, LNTSCHT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_SCHT_NOTFND, LNCRSCHT.RC);
            LNCRSCHT.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRSCHT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRSCHT.CRT_TELR = SCCGWA.COMM_AREA.TL_ID;
        LNRSCHT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRSCHT.UPD_TELR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, LNRSCHT.REMARK);
        LNTSCHT_RD = new DBParm();
        LNTSCHT_RD.TableName = "LNTSCHT";
        LNTSCHT_RD.errhdl = true;
        IBS.WRITE(SCCGWA, LNRSCHT, LNTSCHT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY, LNCRSCHT.RC);
            LNCRSCHT.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        LNTSCHT_RD = new DBParm();
        LNTSCHT_RD.TableName = "LNTSCHT";
        LNTSCHT_RD.upd = true;
        IBS.READ(SCCGWA, LNRSCHT, LNTSCHT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_SCHT_NOTFND, LNCRSCHT.RC);
            LNCRSCHT.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRSCHT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRSCHT.UPD_TELR = SCCGWA.COMM_AREA.TL_ID;
        LNTSCHT_RD = new DBParm();
        LNTSCHT_RD.TableName = "LNTSCHT";
        IBS.REWRITE(SCCGWA, LNRSCHT, LNTSCHT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_SCHT_NOTFND, LNCRSCHT.RC);
            LNCRSCHT.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNRSCHT.KEY.SUB_CTA_NO);
        CEP.TRC(SCCGWA, LNRSCHT.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRSCHT.KEY.TRAN_SEQ);
        CEP.TRC(SCCGWA, LNRSCHT.KEY.TYPE);
        CEP.TRC(SCCGWA, LNRSCHT.KEY.TERM);
        LNTSCHT_RD = new DBParm();
        LNTSCHT_RD.TableName = "LNTSCHT";
        IBS.DELETE(SCCGWA, LNRSCHT, LNTSCHT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_SCHT_NOTFND, LNCRSCHT.RC);
            LNCRSCHT.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_PROC() throws IOException,SQLException,Exception {
        LNTSCHT_BR.rp = new DBParm();
        LNTSCHT_BR.rp.TableName = "LNTSCHT";
        LNTSCHT_BR.rp.order = "TRAN_SEQ,CONTRACT_NO,SUB_CTA_NO,TYPE,TERM";
        IBS.STARTBR(SCCGWA, LNRSCHT, LNTSCHT_BR);
    }
    public void T000_STARTBR_UPDATE_PROC() throws IOException,SQLException,Exception {
        null.rp.upd = true;
        LNTSCHT_BR.rp = new DBParm();
        LNTSCHT_BR.rp.TableName = "LNTSCHT";
        LNTSCHT_BR.rp.where = "TRAN_SEQ = :LNRSCHT.KEY.TRAN_SEQ "
            + "AND CONTRACT_NO = :LNRSCHT.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRSCHT.KEY.SUB_CTA_NO "
            + "AND TYPE >= :LNRSCHT.KEY.TYPE "
            + "AND TERM >= :LNRSCHT.KEY.TERM";
        IBS.STARTBR(SCCGWA, LNRSCHT, this, LNTSCHT_BR);
    }
    public void T000_STARTBR_UPDATE_PROC2() throws IOException,SQLException,Exception {
        null.rp.upd = true;
        LNTSCHT_BR.rp = new DBParm();
        LNTSCHT_BR.rp.TableName = "LNTSCHT";
        LNTSCHT_BR.rp.where = "TRAN_SEQ = :LNRSCHT.KEY.TRAN_SEQ "
            + "AND CONTRACT_NO = :LNRSCHT.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRSCHT.KEY.SUB_CTA_NO "
            + "AND TYPE = :LNRSCHT.KEY.TYPE "
            + "AND TERM >= :LNRSCHT.KEY.TERM";
        IBS.STARTBR(SCCGWA, LNRSCHT, this, LNTSCHT_BR);
    }
    public void T000_STARTBR_BY_KEY_PROC() throws IOException,SQLException,Exception {
        LNTSCHT_BR.rp = new DBParm();
        LNTSCHT_BR.rp.TableName = "LNTSCHT";
        LNTSCHT_BR.rp.where = "TRAN_SEQ = :LNRSCHT.KEY.TRAN_SEQ "
            + "AND CONTRACT_NO = :LNRSCHT.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRSCHT.KEY.SUB_CTA_NO "
            + "AND TYPE >= :LNRSCHT.KEY.TYPE "
            + "AND TERM >= :LNRSCHT.KEY.TERM";
        LNTSCHT_BR.rp.errhdl = true;
        LNTSCHT_BR.rp.order = "DUE_DTE,TYPE";
        IBS.STARTBR(SCCGWA, LNRSCHT, this, LNTSCHT_BR);
    }
    public void T000_STARTBR_BY_KEY_VD() throws IOException,SQLException,Exception {
        LNTSCHT_BR.rp = new DBParm();
        LNTSCHT_BR.rp.TableName = "LNTSCHT";
        LNTSCHT_BR.rp.where = "TRAN_SEQ = :LNRSCHT.KEY.TRAN_SEQ "
            + "AND CONTRACT_NO = :LNRSCHT.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRSCHT.KEY.SUB_CTA_NO "
            + "AND TYPE >= :LNRSCHT.KEY.TYPE "
            + "AND TERM >= :LNRSCHT.KEY.TERM";
        LNTSCHT_BR.rp.errhdl = true;
        LNTSCHT_BR.rp.order = "VAL_DTE,TYPE";
        IBS.STARTBR(SCCGWA, LNRSCHT, this, LNTSCHT_BR);
    }
    public void T000_STARTBR_BY_TERM_PROC() throws IOException,SQLException,Exception {
        LNTSCHT_BR.rp = new DBParm();
        LNTSCHT_BR.rp.TableName = "LNTSCHT";
        LNTSCHT_BR.rp.where = "TRAN_SEQ = :LNRSCHT.KEY.TRAN_SEQ "
            + "AND CONTRACT_NO = :LNRSCHT.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO > :LNRSCHT.KEY.SUB_CTA_NO "
            + "AND TYPE = :LNRSCHT.KEY.TYPE "
            + "AND TERM = :LNRSCHT.KEY.TERM";
        LNTSCHT_BR.rp.errhdl = true;
        LNTSCHT_BR.rp.order = "SUB_CTA_NO,DUE_DTE,TYPE";
        IBS.STARTBR(SCCGWA, LNRSCHT, this, LNTSCHT_BR);
    }
    public void T000_STARTBR_BY_KEY_SHOW() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNRSCHT.KEY.TRAN_SEQ);
        CEP.TRC(SCCGWA, LNRSCHT.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRSCHT.KEY.SUB_CTA_NO);
        CEP.TRC(SCCGWA, LNRSCHT.KEY.TYPE);
        CEP.TRC(SCCGWA, LNRSCHT.KEY.TERM);
        LNTSCHT_BR.rp = new DBParm();
        LNTSCHT_BR.rp.TableName = "LNTSCHT";
        LNTSCHT_BR.rp.where = "TRAN_SEQ = :LNRSCHT.KEY.TRAN_SEQ "
            + "AND CONTRACT_NO = :LNRSCHT.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRSCHT.KEY.SUB_CTA_NO "
            + "AND TYPE >= :LNRSCHT.KEY.TYPE "
            + "AND TERM >= :LNRSCHT.KEY.TERM "
            + "AND ACTION < > 'C' "
            + "AND ACTION < > 'D'";
        LNTSCHT_BR.rp.order = "DUE_DTE,TYPE";
        IBS.STARTBR(SCCGWA, LNRSCHT, this, LNTSCHT_BR);
    }
    public void T000_STARTBR_BY_KEY_SVD() throws IOException,SQLException,Exception {
        LNTSCHT_BR.rp = new DBParm();
        LNTSCHT_BR.rp.TableName = "LNTSCHT";
        LNTSCHT_BR.rp.where = "TRAN_SEQ = :LNRSCHT.KEY.TRAN_SEQ "
            + "AND CONTRACT_NO = :LNRSCHT.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRSCHT.KEY.SUB_CTA_NO "
            + "AND TYPE >= :LNRSCHT.KEY.TYPE "
            + "AND TERM >= :LNRSCHT.KEY.TERM "
            + "AND ACTION < > 'C' "
            + "AND ACTION < > 'D'";
        LNTSCHT_BR.rp.order = "VAL_DTE,TYPE";
        IBS.STARTBR(SCCGWA, LNRSCHT, this, LNTSCHT_BR);
    }
    public void T000_STARTBR_BY_KEY_FIRST() throws IOException,SQLException,Exception {
        LNTSCHT_RD = new DBParm();
        LNTSCHT_RD.TableName = "LNTSCHT";
        LNTSCHT_RD.where = "TRAN_SEQ = :LNRSCHT.KEY.TRAN_SEQ "
            + "AND CONTRACT_NO = :LNRSCHT.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRSCHT.KEY.SUB_CTA_NO "
            + "AND TYPE = :LNRSCHT.KEY.TYPE "
            + "AND ACTION < > 'C' "
            + "AND ACTION < > 'D' "
            + "AND VAL_DTE >= :LNRSCHT.VAL_DTE "
            + "AND VAL_DTE < :LNRSCHT.DUE_DTE";
        LNTSCHT_RD.fst = true;
        LNTSCHT_RD.order = "TRAN_SEQ,CONTRACT_NO,TYPE,DUE_DTE DESC";
        IBS.READ(SCCGWA, LNRSCHT, this, LNTSCHT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_SCHT_NOTFND, LNCRSCHT.RC);
            LNCRSCHT.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '3') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_OTHER, LNCRSCHT.RC);
            LNCRSCHT.RETURN_INFO = 'O';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BY_KEY_FIRST2() throws IOException,SQLException,Exception {
        LNTSCHT_RD = new DBParm();
        LNTSCHT_RD.TableName = "LNTSCHT";
        LNTSCHT_RD.where = "TRAN_SEQ = :LNRSCHT.KEY.TRAN_SEQ "
            + "AND CONTRACT_NO = :LNRSCHT.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRSCHT.KEY.SUB_CTA_NO "
            + "AND TYPE = :LNRSCHT.KEY.TYPE "
            + "AND ACTION < > 'C' "
            + "AND ACTION < > 'D'";
        LNTSCHT_RD.fst = true;
        LNTSCHT_RD.order = "TRAN_SEQ,CONTRACT_NO,TYPE";
        IBS.READ(SCCGWA, LNRSCHT, this, LNTSCHT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_SCHT_NOTFND, LNCRSCHT.RC);
            LNCRSCHT.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '3') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_OTHER, LNCRSCHT.RC);
            LNCRSCHT.RETURN_INFO = 'O';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BY_DUE_DTE_FST_2() throws IOException,SQLException,Exception {
        LNTSCHT_RD = new DBParm();
        LNTSCHT_RD.TableName = "LNTSCHT";
        LNTSCHT_RD.where = "TRAN_SEQ = :LNRSCHT.KEY.TRAN_SEQ "
            + "AND CONTRACT_NO = :LNRSCHT.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRSCHT.KEY.SUB_CTA_NO "
            + "AND TYPE = :LNRSCHT.KEY.TYPE "
            + "AND DUE_DTE = :LNRSCHT.DUE_DTE "
            + "AND ACTION < > 'C' "
            + "AND ACTION < > 'D'";
        LNTSCHT_RD.fst = true;
        IBS.READ(SCCGWA, LNRSCHT, this, LNTSCHT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_SCHT_NOTFND, LNCRSCHT.RC);
            LNCRSCHT.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '3') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_OTHER, LNCRSCHT.RC);
            LNCRSCHT.RETURN_INFO = 'O';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BY_DUE_DTE_FIRST() throws IOException,SQLException,Exception {
        LNTSCHT_RD = new DBParm();
        LNTSCHT_RD.TableName = "LNTSCHT";
        LNTSCHT_RD.where = "TRAN_SEQ = :LNRSCHT.KEY.TRAN_SEQ "
            + "AND CONTRACT_NO = :LNRSCHT.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRSCHT.KEY.SUB_CTA_NO "
            + "AND TYPE = :LNRSCHT.KEY.TYPE "
            + "AND DUE_DTE >= :LNRSCHT.DUE_DTE";
        LNTSCHT_RD.fst = true;
        LNTSCHT_RD.order = "TYPE,DUE_DTE";
        IBS.READ(SCCGWA, LNRSCHT, this, LNTSCHT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_SCHT_NOTFND, LNCRSCHT.RC);
            LNCRSCHT.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '3') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_OTHER, LNCRSCHT.RC);
            LNCRSCHT.RETURN_INFO = 'O';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BY_VAL_DTE_FIRST() throws IOException,SQLException,Exception {
        LNTSCHT_RD = new DBParm();
        LNTSCHT_RD.TableName = "LNTSCHT";
        LNTSCHT_RD.where = "TRAN_SEQ = :LNRSCHT.KEY.TRAN_SEQ "
            + "AND CONTRACT_NO = :LNRSCHT.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRSCHT.KEY.SUB_CTA_NO "
            + "AND TYPE = :LNRSCHT.KEY.TYPE "
            + "AND VAL_DTE >= :LNRSCHT.VAL_DTE";
        LNTSCHT_RD.fst = true;
        LNTSCHT_RD.order = "TYPE,VAL_DTE";
        IBS.READ(SCCGWA, LNRSCHT, this, LNTSCHT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_SCHT_NOTFND, LNCRSCHT.RC);
            LNCRSCHT.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '3') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_OTHER, LNCRSCHT.RC);
            LNCRSCHT.RETURN_INFO = 'O';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BY_DUE_DTE() throws IOException,SQLException,Exception {
        LNTSCHT_BR.rp = new DBParm();
        LNTSCHT_BR.rp.TableName = "LNTSCHT";
        LNTSCHT_BR.rp.where = "TRAN_SEQ = :LNRSCHT.KEY.TRAN_SEQ "
            + "AND CONTRACT_NO = :LNRSCHT.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRSCHT.KEY.SUB_CTA_NO "
            + "AND TYPE = :LNRSCHT.KEY.TYPE "
            + "AND DUE_DTE >= :LNRSCHT.DUE_DTE "
            + "AND ACTION < > 'C' "
            + "AND ACTION < > 'D'";
        LNTSCHT_BR.rp.order = "TYPE,DUE_DTE";
        IBS.STARTBR(SCCGWA, LNRSCHT, this, LNTSCHT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_SCHT_NOTFND, LNCRSCHT.RC);
            LNCRSCHT.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '3') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_OTHER, LNCRSCHT.RC);
            LNCRSCHT.RETURN_INFO = 'O';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BY_VAL_DTE() throws IOException,SQLException,Exception {
        LNTSCHT_BR.rp = new DBParm();
        LNTSCHT_BR.rp.TableName = "LNTSCHT";
        LNTSCHT_BR.rp.where = "TRAN_SEQ = :LNRSCHT.KEY.TRAN_SEQ "
            + "AND CONTRACT_NO = :LNRSCHT.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRSCHT.KEY.SUB_CTA_NO "
            + "AND TYPE = :LNRSCHT.KEY.TYPE "
            + "AND VAL_DTE >= :LNRSCHT.VAL_DTE "
            + "AND ACTION < > 'C' "
            + "AND ACTION < > 'D'";
        LNTSCHT_BR.rp.order = "TYPE,VAL_DTE";
        IBS.STARTBR(SCCGWA, LNRSCHT, this, LNTSCHT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_SCHT_NOTFND, LNCRSCHT.RC);
            LNCRSCHT.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '3') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_OTHER, LNCRSCHT.RC);
            LNCRSCHT.RETURN_INFO = 'O';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BY_DUE_DTE_DESC() throws IOException,SQLException,Exception {
        LNTSCHT_BR.rp = new DBParm();
        LNTSCHT_BR.rp.TableName = "LNTSCHT";
        LNTSCHT_BR.rp.where = "TRAN_SEQ = :LNRSCHT.KEY.TRAN_SEQ "
            + "AND CONTRACT_NO = :LNRSCHT.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRSCHT.KEY.SUB_CTA_NO "
            + "AND TYPE = :LNRSCHT.KEY.TYPE "
            + "AND DUE_DTE < :LNRSCHT.DUE_DTE "
            + "AND ACTION < > 'C' "
            + "AND ACTION < > 'D'";
        LNTSCHT_BR.rp.order = "TYPE,DUE_DTE DESC";
        IBS.STARTBR(SCCGWA, LNRSCHT, this, LNTSCHT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_SCHT_NOTFND, LNCRSCHT.RC);
            LNCRSCHT.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '3') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_OTHER, LNCRSCHT.RC);
            LNCRSCHT.RETURN_INFO = 'O';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BY_KEY_PROC2() throws IOException,SQLException,Exception {
        LNTSCHT_BR.rp = new DBParm();
        LNTSCHT_BR.rp.TableName = "LNTSCHT";
        LNTSCHT_BR.rp.where = "TRAN_SEQ = :LNRSCHT.KEY.TRAN_SEQ "
            + "AND CONTRACT_NO = :LNRSCHT.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRSCHT.KEY.SUB_CTA_NO "
            + "AND TYPE = :LNRSCHT.KEY.TYPE "
            + "AND TERM >= :LNRSCHT.KEY.TERM";
        LNTSCHT_BR.rp.errhdl = true;
        LNTSCHT_BR.rp.order = "DUE_DTE,TYPE";
        IBS.STARTBR(SCCGWA, LNRSCHT, this, LNTSCHT_BR);
    }
    public void T000_STARTBR_BY_KEY_VD2() throws IOException,SQLException,Exception {
        LNTSCHT_BR.rp = new DBParm();
        LNTSCHT_BR.rp.TableName = "LNTSCHT";
        LNTSCHT_BR.rp.where = "TRAN_SEQ = :LNRSCHT.KEY.TRAN_SEQ "
            + "AND CONTRACT_NO = :LNRSCHT.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRSCHT.KEY.SUB_CTA_NO "
            + "AND TYPE = :LNRSCHT.KEY.TYPE "
            + "AND TERM >= :LNRSCHT.KEY.TERM";
        LNTSCHT_BR.rp.errhdl = true;
        LNTSCHT_BR.rp.order = "VAL_DTE,TYPE";
        IBS.STARTBR(SCCGWA, LNRSCHT, this, LNTSCHT_BR);
    }
    public void T000_STARTBR_BY_TERM_PROC2() throws IOException,SQLException,Exception {
        LNTSCHT_BR.rp = new DBParm();
        LNTSCHT_BR.rp.TableName = "LNTSCHT";
        LNTSCHT_BR.rp.where = "TRAN_SEQ = :LNRSCHT.KEY.TRAN_SEQ "
            + "AND CONTRACT_NO = :LNRSCHT.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO > :LNRSCHT.KEY.SUB_CTA_NO "
            + "AND TYPE = :LNRSCHT.KEY.TYPE "
            + "AND TERM = :LNRSCHT.KEY.TERM";
        LNTSCHT_BR.rp.errhdl = true;
        LNTSCHT_BR.rp.order = "SUB_CTA_NO,DUE_DTE,TYPE";
        IBS.STARTBR(SCCGWA, LNRSCHT, this, LNTSCHT_BR);
    }
    public void T000_STARTBR_BY_KEY_SHOW2() throws IOException,SQLException,Exception {
        LNTSCHT_BR.rp = new DBParm();
        LNTSCHT_BR.rp.TableName = "LNTSCHT";
        LNTSCHT_BR.rp.where = "TRAN_SEQ = :LNRSCHT.KEY.TRAN_SEQ "
            + "AND CONTRACT_NO = :LNRSCHT.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRSCHT.KEY.SUB_CTA_NO "
            + "AND TYPE = :LNRSCHT.KEY.TYPE "
            + "AND TERM >= :LNRSCHT.KEY.TERM "
            + "AND ACTION < > 'C' "
            + "AND ACTION < > 'D'";
        LNTSCHT_BR.rp.order = "DUE_DTE,TYPE";
        IBS.STARTBR(SCCGWA, LNRSCHT, this, LNTSCHT_BR);
    }
    public void T000_STARTBR_BY_KEY_SVD2() throws IOException,SQLException,Exception {
        LNTSCHT_BR.rp = new DBParm();
        LNTSCHT_BR.rp.TableName = "LNTSCHT";
        LNTSCHT_BR.rp.where = "TRAN_SEQ = :LNRSCHT.KEY.TRAN_SEQ "
            + "AND CONTRACT_NO = :LNRSCHT.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRSCHT.KEY.SUB_CTA_NO "
            + "AND TYPE = :LNRSCHT.KEY.TYPE "
            + "AND TERM >= :LNRSCHT.KEY.TERM "
            + "AND ACTION < > 'C' "
            + "AND ACTION < > 'D'";
        LNTSCHT_BR.rp.order = "VAL_DTE,TYPE";
        IBS.STARTBR(SCCGWA, LNRSCHT, this, LNTSCHT_BR);
    }
    public void T000_GROUP_TERM_PROC() throws IOException,SQLException,Exception {
        WS_TERM_MAX = 0;
        LNTSCHT_RD = new DBParm();
        LNTSCHT_RD.TableName = "LNTSCHT";
        LNTSCHT_RD.set = "WS-TERM-MAX=IFNULL(MAX(TERM),0)";
        LNTSCHT_RD.where = "ACTION < > 'C' "
            + "AND ACTION < > 'D'";
        LNTSCHT_RD.eqWhere = "TYPE,TRAN_SEQ,SUB_CTA_NO,CONTRACT_NO";
        IBS.GROUP(SCCGWA, LNRSCHT, this, LNTSCHT_RD);
        LNCRSCHT.TERM_MAX = WS_TERM_MAX;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_SCHT_NOTFND, LNCRSCHT.RC);
            LNCRSCHT.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '3') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_OTHER, LNCRSCHT.RC);
            LNCRSCHT.RETURN_INFO = 'O';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_GROUP_MAX_TERM_PROC() throws IOException,SQLException,Exception {
        WS_TERM_MAX = 0;
        LNTSCHT_RD = new DBParm();
        LNTSCHT_RD.TableName = "LNTSCHT";
        LNTSCHT_RD.set = "WS-TERM-MAX=IFNULL(MAX(TERM),0)";
        LNTSCHT_RD.eqWhere = "TYPE,TRAN_SEQ,SUB_CTA_NO,CONTRACT_NO";
        IBS.GROUP(SCCGWA, LNRSCHT, this, LNTSCHT_RD);
        LNCRSCHT.TERM_MAX = WS_TERM_MAX;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_SCHT_NOTFND, LNCRSCHT.RC);
            LNCRSCHT.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '3') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_OTHER, LNCRSCHT.RC);
            LNCRSCHT.RETURN_INFO = 'O';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_GROUP_MIN_TERM_PROC() throws IOException,SQLException,Exception {
        WS_TERM_MIN = 0;
        LNTSCHT_RD = new DBParm();
        LNTSCHT_RD.TableName = "LNTSCHT";
        LNTSCHT_RD.set = "WS-TERM-MIN=IFNULL(MIN(TERM),0)";
        LNTSCHT_RD.where = "ACTION = 'D'";
        LNTSCHT_RD.eqWhere = "TYPE,TRAN_SEQ,SUB_CTA_NO,CONTRACT_NO";
        IBS.GROUP(SCCGWA, LNRSCHT, this, LNTSCHT_RD);
        LNCRSCHT.TERM_MIN = WS_TERM_MIN;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_SCHT_NOTFND, LNCRSCHT.RC);
            LNCRSCHT.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '3') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_OTHER, LNCRSCHT.RC);
            LNCRSCHT.RETURN_INFO = 'O';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_GROUP_MIN_TERM1_PROC() throws IOException,SQLException,Exception {
        WS_TERM_MIN = 0;
        if (LNRSCHT.KEY.TYPE == 'P') {
            LNTSCHT_RD = new DBParm();
            LNTSCHT_RD.TableName = "LNTSCHT";
            LNTSCHT_RD.set = "WS-TERM-MIN=IFNULL(MIN(TERM),0)";
            LNTSCHT_RD.eqWhere = "TYPE,TRAN_SEQ,SUB_CTA_NO,CONTRACT_NO";
            IBS.GROUP(SCCGWA, LNRSCHT, this, LNTSCHT_RD);
        } else {
            LNTSCHT_RD = new DBParm();
            LNTSCHT_RD.TableName = "LNTSCHT";
            LNTSCHT_RD.set = "WS-TERM-MIN=IFNULL(MIN(TERM),0)";
            LNTSCHT_RD.where = "TRAN_SEQ = :LNRSCHT.KEY.TRAN_SEQ "
                + "AND SUB_CTA_NO = :LNRSCHT.KEY.SUB_CTA_NO "
                + "AND CONTRACT_NO = :LNRSCHT.KEY.CONTRACT_NO "
                + "AND TYPE < > 'P'";
            IBS.GROUP(SCCGWA, LNRSCHT, this, LNTSCHT_RD);
        }
        LNCRSCHT.TERM_MIN = WS_TERM_MIN;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_SCHT_NOTFND, LNCRSCHT.RC);
            LNCRSCHT.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '3') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_OTHER, LNCRSCHT.RC);
            LNCRSCHT.RETURN_INFO = 'O';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BY_SUM_AMT_PROC() throws IOException,SQLException,Exception {
        WS_TOT_CNT = 0;
        WS_TOT_SUM = 0;
        LNTSCHT_BR.rp = new DBParm();
        LNTSCHT_BR.rp.TableName = "LNTSCHT";
        LNTSCHT_BR.rp.set = "WS-TOT-CNT=COUNT(*),WS-TOT-SUM=SUM(AMOUNT)";
        LNTSCHT_BR.rp.where = "TRAN_SEQ = :LNRSCHT.KEY.TRAN_SEQ "
            + "AND SUB_CTA_NO = :LNRSCHT.KEY.SUB_CTA_NO "
            + "AND CONTRACT_NO = :LNRSCHT.KEY.CONTRACT_NO "
            + "AND TYPE = :LNRSCHT.KEY.TYPE";
        LNTSCHT_BR.rp.grp = "TRAN_SEQ,TYPE";
        LNTSCHT_BR.rp.order = "TRAN_SEQ,TYPE";
        IBS.STARTBR(SCCGWA, LNRSCHT, this, LNTSCHT_BR);
        LNCRSCHT.TOT_CNT = WS_TOT_CNT;
        LNCRSCHT.TOT_SUM = WS_TOT_SUM;
        CEP.TRC(SCCGWA, LNCRSCHT.TOT_CNT);
        CEP.TRC(SCCGWA, LNCRSCHT.TOT_SUM);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_SCHT_NOTFND, LNCRSCHT.RC);
            LNCRSCHT.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '3') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_OTHER, LNCRSCHT.RC);
            LNCRSCHT.RETURN_INFO = 'O';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        WS_TOT_CNT = 0;
        WS_TOT_SUM = 0;
        IBS.READNEXT(SCCGWA, LNRSCHT, this, LNTSCHT_BR);
        LNCRSCHT.TOT_CNT = WS_TOT_CNT;
        LNCRSCHT.TOT_SUM = WS_TOT_SUM;
        CEP.TRC(SCCGWA, LNCRSCHT.TOT_CNT);
        CEP.TRC(SCCGWA, LNCRSCHT.TOT_SUM);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            LNCRSCHT.RETURN_INFO = 'E';
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTSCHT_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCRSCHT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "LNCRSCHT=");
            CEP.TRC(SCCGWA, LNCRSCHT);
            CEP.TRC(SCCGWA, "LNRSCHT =");
            CEP.TRC(SCCGWA, LNRSCHT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
