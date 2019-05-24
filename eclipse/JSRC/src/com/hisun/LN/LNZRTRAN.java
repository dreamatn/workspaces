package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZRTRAN {
    DBParm LNTTRAN_RD;
    brParm LNTTRAN_BR = new brParm();
    boolean pgmRtn = false;
    int WS_DATE1 = 0;
    int WS_DATE2 = 0;
    String WS_CI_NO = " ";
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    LNRTRAN LNRTRAN = new LNRTRAN();
    SCCGWA SCCGWA;
    LNCRTRAN LNCRTRAN;
    LNRTRAN LNRTRAN1;
    public void MP(SCCGWA SCCGWA, LNCRTRAN LNCRTRAN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCRTRAN = LNCRTRAN;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZRTRAN return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNCRTRAN.RETURN_INFO = 'F';
        IBS.init(SCCGWA, LNRTRAN);
        IBS.CLONE(SCCGWA, LNRTRAN1, LNRTRAN);
        LNCRTRAN.RC.RC_MMO = "LN";
        LNCRTRAN.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (LNCRTRAN.FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRTRAN.FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRTRAN.FUNC == 'R') {
            B030_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRTRAN.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRTRAN.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRTRAN.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCRTRAN.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, LNRTRAN, LNRTRAN1);
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
        if (LNCRTRAN.OPT == 'S') {
            B060_10_START_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (LNCRTRAN.OPT == 'A') {
            B060_20_STBR_BY_KEY_PROC();
            if (pgmRtn) return;
        } else if (LNCRTRAN.OPT == 'F') {
            B060_20_STBR_TRAN1_PROC();
            if (pgmRtn) return;
        } else if (LNCRTRAN.OPT == 'I') {
            B060_20_STBR_BY_INDEX1_PROC();
            if (pgmRtn) return;
        } else if (LNCRTRAN.OPT == 'N') {
            B060_20_STBR_BY_INDEX2_PROC();
            if (pgmRtn) return;
        } else if (LNCRTRAN.OPT == 'X') {
            B060_20_STBR_CTA_CI_T_PROC();
            if (pgmRtn) return;
        } else if (LNCRTRAN.OPT == 'M') {
            B060_20_STBR_TRCH_CI_PROC();
            if (pgmRtn) return;
        } else if (LNCRTRAN.OPT == 'H') {
            B060_20_STBR_TRCH_CTA_PROC();
            if (pgmRtn) return;
        } else if (LNCRTRAN.OPT == 'W') {
            B060_20_STBR_CI_CTA_PROC();
            if (pgmRtn) return;
        } else if (LNCRTRAN.OPT == 'Z') {
            B060_20_STBR_TRCH_PROC();
            if (pgmRtn) return;
        } else if (LNCRTRAN.OPT == 'C') {
            B060_20_STBR_CI_NO_PROC();
            if (pgmRtn) return;
        } else if (LNCRTRAN.OPT == 'T') {
            B060_20_STBR_CTA_NO_PROC();
            if (pgmRtn) return;
        } else if (LNCRTRAN.OPT == 'P') {
            B060_20_STBR_OTHER_PROC();
            if (pgmRtn) return;
        } else if (LNCRTRAN.OPT == 'O') {
            B060_20_STBR_KEY2_PROC();
            if (pgmRtn) return;
        } else if (LNCRTRAN.OPT == 'V') {
            B060_20_STBR_CTANO_PROC();
            if (pgmRtn) return;
        } else if (LNCRTRAN.OPT == 'R') {
            B060_30_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (LNCRTRAN.OPT == 'E') {
            B060_50_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + LNCRTRAN.OPT + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B060_10_START_BROWSE_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_PROC();
        if (pgmRtn) return;
    }
    public void B060_20_STBR_BY_KEY_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_KEY_PROC();
        if (pgmRtn) return;
    }
    public void B060_20_STBR_KEY2_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_KEY2_PROC();
        if (pgmRtn) return;
    }
    public void B060_20_STBR_TRAN1_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_TRAN1_PROC();
        if (pgmRtn) return;
    }
    public void B060_20_STBR_BY_INDEX1_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_INDEX1_PROC();
        if (pgmRtn) return;
    }
    public void B060_20_STBR_BY_INDEX2_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_INDEX2_PROC();
        if (pgmRtn) return;
    }
    public void B060_20_STBR_CTA_CI_T_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_CTA_CI_T_PROC();
        if (pgmRtn) return;
    }
    public void B060_20_STBR_TRCH_CI_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_TRCH_CI_PROC();
        if (pgmRtn) return;
    }
    public void B060_20_STBR_TRCH_CTA_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_TRCH_CTA_PROC();
        if (pgmRtn) return;
    }
    public void B060_20_STBR_CI_CTA_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_CI_CTA_PROC();
        if (pgmRtn) return;
    }
    public void B060_20_STBR_TRCH_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_TRCH_PROC();
        if (pgmRtn) return;
    }
    public void B060_20_STBR_CI_NO_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_CI_NO_PROC();
        if (pgmRtn) return;
    }
    public void B060_20_STBR_CTA_NO_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_CTA_NO_PROC();
        if (pgmRtn) return;
    }
    public void B060_20_STBR_OTHER_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_OTHER_PROC();
        if (pgmRtn) return;
    }
    public void B060_20_STBR_CTANO_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_CIANO_PROC();
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
        LNTTRAN_RD = new DBParm();
        LNTTRAN_RD.TableName = "LNTTRAN";
        IBS.READ(SCCGWA, LNRTRAN, LNTTRAN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_TRAN_NOTFND, LNCRTRAN.RC);
            LNCRTRAN.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRTRAN.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRTRAN.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRTRAN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRTRAN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRTRAN.REQ_FRM_NO = SCCGWA.COMM_AREA.REQ_SYSTEM;
        LNTTRAN_RD = new DBParm();
        LNTTRAN_RD.TableName = "LNTTRAN";
        LNTTRAN_RD.errhdl = true;
        IBS.WRITE(SCCGWA, LNRTRAN, LNTTRAN_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY, LNCRTRAN.RC);
            LNCRTRAN.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        LNRTRAN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRTRAN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRTRAN.REQ_FRM_NO = SCCGWA.COMM_AREA.REQ_SYSTEM;
        LNTTRAN_RD = new DBParm();
        LNTTRAN_RD.TableName = "LNTTRAN";
        LNTTRAN_RD.upd = true;
        IBS.READ(SCCGWA, LNRTRAN, LNTTRAN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_TRAN_NOTFND, LNCRTRAN.RC);
            LNCRTRAN.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRTRAN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRTRAN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTTRAN_RD = new DBParm();
        LNTTRAN_RD.TableName = "LNTTRAN";
        IBS.REWRITE(SCCGWA, LNRTRAN, LNTTRAN_RD);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        LNTTRAN_RD = new DBParm();
        LNTTRAN_RD.TableName = "LNTTRAN";
        IBS.DELETE(SCCGWA, LNRTRAN, LNTTRAN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_TRAN_NOTFND, LNCRTRAN.RC);
            LNCRTRAN.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_PROC() throws IOException,SQLException,Exception {
        LNTTRAN_BR.rp = new DBParm();
        LNTTRAN_BR.rp.TableName = "LNTTRAN";
        LNTTRAN_BR.rp.order = "CONTRACT_NO,SUB_CTA_NO";
        IBS.STARTBR(SCCGWA, LNRTRAN, LNTTRAN_BR);
    }
    public void T000_STARTBR_BY_KEY_PROC() throws IOException,SQLException,Exception {
        LNTTRAN_BR.rp = new DBParm();
        LNTTRAN_BR.rp.TableName = "LNTTRAN";
        LNTTRAN_BR.rp.where = "CONTRACT_NO >= :LNRTRAN.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO >= :LNRTRAN.KEY.SUB_CTA_NO "
            + "AND TR_DATE >= :LNRTRAN.KEY.TR_DATE "
            + "AND TR_JRN_NO >= :LNRTRAN.KEY.TR_JRN_NO "
            + "AND TXN_TYP = :LNRTRAN.KEY.TXN_TYP "
            + "AND TXN_TERM >= :LNRTRAN.KEY.TXN_TERM "
            + "AND TRAN_FLG = :LNRTRAN.KEY.TRAN_FLG";
        LNTTRAN_BR.rp.order = "CONTRACT_NO,SUB_CTA_NO,TR_DATE,TR_JRN_NO";
        IBS.STARTBR(SCCGWA, LNRTRAN, this, LNTTRAN_BR);
    }
    public void T000_STARTBR_BY_KEY2_PROC() throws IOException,SQLException,Exception {
        WS_DATE1 = LNCRTRAN.DATE1;
        LNTTRAN_BR.rp = new DBParm();
        LNTTRAN_BR.rp.TableName = "LNTTRAN";
        LNTTRAN_BR.rp.eqWhere = "CONTRACT_NO,SUB_CTA_NO,TRAN_STS,TXN_TYP";
        LNTTRAN_BR.rp.where = "TR_VAL_DTE <= :LNRTRAN.TR_VAL_DTE "
            + "AND P_AMT < > :LNRTRAN.P_AMT";
        LNTTRAN_BR.rp.order = "CONTRACT_NO,TR_VAL_DTE DESC,TR_SEQ DESC,DUE_DT DESC";
        IBS.STARTBR(SCCGWA, LNRTRAN, this, LNTTRAN_BR);
    }
    public void T000_STARTBR_BY_INDEX1_PROC() throws IOException,SQLException,Exception {
        LNTTRAN_BR.rp = new DBParm();
        LNTTRAN_BR.rp.TableName = "LNTTRAN";
        LNTTRAN_BR.rp.where = "TR_DATE >= :LNRTRAN.KEY.TR_DATE "
            + "AND TR_JRN_NO >= :LNRTRAN.KEY.TR_JRN_NO "
            + "AND TXN_TYP = :LNRTRAN.KEY.TXN_TYP "
            + "AND TXN_TERM >= :LNRTRAN.KEY.TXN_TERM "
            + "AND TRAN_FLG = :LNRTRAN.KEY.TRAN_FLG";
        LNTTRAN_BR.rp.order = "TR_DATE,TR_JRN_NO";
        IBS.STARTBR(SCCGWA, LNRTRAN, this, LNTTRAN_BR);
    }
    public void T000_STARTBR_BY_INDEX2_PROC() throws IOException,SQLException,Exception {
        LNTTRAN_BR.rp = new DBParm();
        LNTTRAN_BR.rp.TableName = "LNTTRAN";
        LNTTRAN_BR.rp.where = "CONTRACT_NO >= :LNRTRAN.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO >= :LNRTRAN.KEY.SUB_CTA_NO "
            + "AND TRAN_FLG = :LNRTRAN.KEY.TRAN_FLG "
            + "AND TR_VAL_DTE >= :LNRTRAN.TR_VAL_DTE "
            + "AND TR_DATE >= :LNRTRAN.KEY.TR_DATE "
            + "AND TR_JRN_NO >= :LNRTRAN.KEY.TR_JRN_NO "
            + "AND DUE_DT >= :LNRTRAN.DUE_DT "
            + "AND TXN_TYP = :LNRTRAN.KEY.TXN_TYP";
        LNTTRAN_BR.rp.order = "CONTRACT_NO,SUB_CTA_NO,TRAN_FLG,TR_VAL_DTE";
        IBS.STARTBR(SCCGWA, LNRTRAN, this, LNTTRAN_BR);
    }
    public void T000_STARTBR_BY_CTA_CI_T_PROC() throws IOException,SQLException,Exception {
        WS_DATE1 = LNCRTRAN.DATE1;
        WS_DATE2 = LNCRTRAN.DATE2;
        LNTTRAN_BR.rp = new DBParm();
        LNTTRAN_BR.rp.TableName = "LNTTRAN";
        LNTTRAN_BR.rp.eqWhere = "CONTRACT_NO";
        LNTTRAN_BR.rp.where = "TR_DATE >= :WS_DATE1 "
            + "AND TR_DATE <= :WS_DATE2 "
            + "AND TRAN_STS = 'N' "
            + "AND ( TR_CODE = 2111 "
            + "OR TR_CODE = 2230 "
            + "OR ( TR_CODE = 2210 "
            + "AND TXN_TYP = 'T' ) "
            + "OR ( TR_CODE = 2222 "
            + "AND TXN_TYP = 'T' ) )";
        LNTTRAN_BR.rp.order = "TR_DATE,TR_SEQ,TR_JRN_NO ,CONTRACT_NO,TR_CODE";
        IBS.STARTBR(SCCGWA, LNRTRAN, this, LNTTRAN_BR);
    }
    public void T000_STARTBR_BY_TRCH_CI_PROC() throws IOException,SQLException,Exception {
        WS_DATE1 = LNCRTRAN.DATE1;
        WS_DATE2 = LNCRTRAN.DATE2;
        LNTTRAN_BR.rp = new DBParm();
        LNTTRAN_BR.rp.TableName = "LNTTRAN";
        LNTTRAN_BR.rp.where = "TR_DATE >= :WS_DATE1 "
            + "AND TR_DATE <= :WS_DATE2 "
            + "AND TRAN_STS = 'N' "
            + "AND ( TR_CODE = 2111 "
            + "OR TR_CODE = 2230 "
            + "OR ( TR_CODE = 2210 "
            + "AND TXN_TYP = 'T' ) "
            + "OR ( TR_CODE = 2222 "
            + "AND TXN_TYP = 'T' ) )";
        LNTTRAN_BR.rp.order = "TR_DATE,TR_SEQ,TR_JRN_NO ,CONTRACT_NO,TR_CODE";
        IBS.STARTBR(SCCGWA, LNRTRAN, this, LNTTRAN_BR);
    }
    public void T000_STARTBR_BY_TRCH_CTA_PROC() throws IOException,SQLException,Exception {
        WS_DATE1 = LNCRTRAN.DATE1;
        WS_DATE2 = LNCRTRAN.DATE2;
        LNTTRAN_BR.rp = new DBParm();
        LNTTRAN_BR.rp.TableName = "LNTTRAN";
        LNTTRAN_BR.rp.eqWhere = "CONTRACT_NO";
        LNTTRAN_BR.rp.where = "TR_DATE >= :WS_DATE1 "
            + "AND TR_DATE <= :WS_DATE2 "
            + "AND TRAN_STS = 'N' "
            + "AND ( TR_CODE = 2111 "
            + "OR TR_CODE = 2230 "
            + "OR ( TR_CODE = 2210 "
            + "AND TXN_TYP = 'T' ) "
            + "OR ( TR_CODE = 2222 "
            + "AND TXN_TYP = 'T' ) )";
        LNTTRAN_BR.rp.order = "TR_DATE,TR_SEQ,TR_JRN_NO ,CONTRACT_NO,TR_CODE";
        IBS.STARTBR(SCCGWA, LNRTRAN, this, LNTTRAN_BR);
    }
    public void T000_STARTBR_BY_CI_CTA_PROC() throws IOException,SQLException,Exception {
        WS_DATE1 = LNCRTRAN.DATE1;
        WS_DATE2 = LNCRTRAN.DATE2;
        LNTTRAN_BR.rp = new DBParm();
        LNTTRAN_BR.rp.TableName = "LNTTRAN";
        LNTTRAN_BR.rp.eqWhere = "CONTRACT_NO";
        LNTTRAN_BR.rp.where = "TR_DATE >= :WS_DATE1 "
            + "AND TR_DATE <= :WS_DATE2 "
            + "AND TRAN_STS = 'N' "
            + "AND ( TR_CODE = 2111 "
            + "OR TR_CODE = 2230 "
            + "OR ( TR_CODE = 2210 "
            + "AND TXN_TYP = 'T' ) "
            + "OR ( TR_CODE = 2222 "
            + "AND TXN_TYP = 'T' ) )";
        LNTTRAN_BR.rp.order = "TR_DATE,TR_SEQ,TR_JRN_NO ,CONTRACT_NO,TR_CODE";
        IBS.STARTBR(SCCGWA, LNRTRAN, this, LNTTRAN_BR);
    }
    public void T000_STARTBR_BY_TRCH_PROC() throws IOException,SQLException,Exception {
        WS_DATE1 = LNCRTRAN.DATE1;
        WS_DATE2 = LNCRTRAN.DATE2;
        LNTTRAN_BR.rp = new DBParm();
        LNTTRAN_BR.rp.TableName = "LNTTRAN";
        LNTTRAN_BR.rp.where = "TR_DATE >= :WS_DATE1 "
            + "AND TR_DATE <= :WS_DATE2 "
            + "AND TRAN_STS = 'N' "
            + "AND ( TR_CODE = 2111 "
            + "OR TR_CODE = 2230 "
            + "OR ( TR_CODE = 2210 "
            + "AND TXN_TYP = 'T' ) "
            + "OR ( TR_CODE = 2222 "
            + "AND TXN_TYP = 'T' ) )";
        LNTTRAN_BR.rp.order = "TR_DATE,TR_SEQ,TR_JRN_NO ,CONTRACT_NO,TR_CODE";
        IBS.STARTBR(SCCGWA, LNRTRAN, this, LNTTRAN_BR);
    }
    public void T000_STARTBR_BY_CI_NO_PROC() throws IOException,SQLException,Exception {
        WS_DATE1 = LNCRTRAN.DATE1;
        WS_DATE2 = LNCRTRAN.DATE2;
        LNTTRAN_BR.rp = new DBParm();
        LNTTRAN_BR.rp.TableName = "LNTTRAN";
        LNTTRAN_BR.rp.where = "TXN_TYP < > 'T' "
            + "AND ( TR_CODE = 2161 "
            + "OR TR_CODE = 2171 "
            + "OR TR_CODE = 2210 "
            + "OR TR_CODE = 2230 )";
        LNTTRAN_BR.rp.order = "CONTRACT_NO,TR_DATE,TR_SEQ,TR_JRN_NO,TR_CODE";
        IBS.STARTBR(SCCGWA, LNRTRAN, this, LNTTRAN_BR);
    }
    public void T000_STARTBR_BY_CTA_NO_PROC() throws IOException,SQLException,Exception {
        WS_DATE1 = LNCRTRAN.DATE1;
        WS_DATE2 = LNCRTRAN.DATE2;
        LNTTRAN_BR.rp = new DBParm();
        LNTTRAN_BR.rp.TableName = "LNTTRAN";
        LNTTRAN_BR.rp.eqWhere = "CONTRACT_NO";
        LNTTRAN_BR.rp.where = "TR_DATE >= :WS_DATE1 "
            + "AND TR_DATE <= :WS_DATE2 "
            + "AND TRAN_STS = 'N' "
            + "AND ( TR_CODE = 2111 "
            + "OR TR_CODE = 2230 "
            + "OR ( TR_CODE = 2210 "
            + "AND TXN_TYP = 'T' ) "
            + "OR ( TR_CODE = 2222 "
            + "AND TXN_TYP = 'T' ) )";
        LNTTRAN_BR.rp.order = "TR_DATE,TR_SEQ,TR_JRN_NO ,CONTRACT_NO,TR_CODE";
        IBS.STARTBR(SCCGWA, LNRTRAN, this, LNTTRAN_BR);
    }
    public void T000_STARTBR_BY_OTHER_PROC() throws IOException,SQLException,Exception {
        LNTTRAN_BR.rp = new DBParm();
        LNTTRAN_BR.rp.TableName = "LNTTRAN";
        LNTTRAN_BR.rp.eqWhere = "CONTRACT_NO";
        LNTTRAN_BR.rp.where = "SUB_CTA_NO = :LNRTRAN.KEY.SUB_CTA_NO "
            + "AND TR_DATE = :LNRTRAN.KEY.TR_DATE "
            + "AND TR_JRN_NO = :LNRTRAN.KEY.TR_JRN_NO "
            + "AND TXN_TYP < > :LNRTRAN.KEY.TXN_TYP "
            + "AND TRAN_STS = :LNRTRAN.TRAN_STS "
            + "AND TR_CODE = :LNRTRAN.TR_CODE";
        LNTTRAN_BR.rp.order = "DUE_DT,TXN_TYP DESC";
        IBS.STARTBR(SCCGWA, LNRTRAN, this, LNTTRAN_BR);
    }
    public void T000_STARTBR_TRAN1_PROC() throws IOException,SQLException,Exception {
        LNTTRAN_BR.rp = new DBParm();
        LNTTRAN_BR.rp.TableName = "LNTTRAN";
        LNTTRAN_BR.rp.where = "TXN_TYP < > 'T' "
            + "AND TRAN_STS = 'N'";
        LNTTRAN_BR.rp.order = "TR_DATE,TS,CONTRACT_NO,TR_CODE";
        IBS.STARTBR(SCCGWA, LNRTRAN, this, LNTTRAN_BR);
    }
    public void T000_STARTBR_BY_CIANO_PROC() throws IOException,SQLException,Exception {
        LNTTRAN_BR.rp = new DBParm();
        LNTTRAN_BR.rp.TableName = "LNTTRAN";
        LNTTRAN_BR.rp.eqWhere = "CONTRACT_NO";
        LNTTRAN_BR.rp.where = "TXN_TYP < > 'T' "
            + "AND ( TR_CODE = 2161 "
            + "OR TR_CODE = 2171 "
            + "OR TR_CODE = 2210 "
            + "OR TR_CODE = 2230 )";
        LNTTRAN_BR.rp.order = "CONTRACT_NO,TR_DATE,TR_SEQ,TR_JRN_NO,TR_CODE";
        IBS.STARTBR(SCCGWA, LNRTRAN, this, LNTTRAN_BR);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRTRAN, this, LNTTRAN_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_TRAN_NOTFND, LNCRTRAN.RC);
            LNCRTRAN.RETURN_INFO = 'E';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTTRAN_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCRTRAN.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "LNCRTRAN=");
            CEP.TRC(SCCGWA, LNCRTRAN);
            CEP.TRC(SCCGWA, "LNRTRAN =");
            CEP.TRC(SCCGWA, LNRTRAN);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
