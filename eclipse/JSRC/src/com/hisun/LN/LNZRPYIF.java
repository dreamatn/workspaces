package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZRPYIF {
    DBParm LNTPYIF_RD;
    brParm LNTPYIF_BR = new brParm();
    boolean pgmRtn = false;
    int WS_TOT_CNT = 0;
    double WS_TOT_SUM = 0;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    LNRPYIF LNRPYIF = new LNRPYIF();
    SCCGWA SCCGWA;
    LNCRPYIF LNCRPYIF;
    LNRPYIF LNRPYIF1;
    public void MP(SCCGWA SCCGWA, LNCRPYIF LNCRPYIF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCRPYIF = LNCRPYIF;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZRPYIF return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNRPYIF1 = (LNRPYIF) LNCRPYIF.REC_PTR;
        LNCRPYIF.RETURN_INFO = 'F';
        IBS.init(SCCGWA, LNRPYIF);
        IBS.CLONE(SCCGWA, LNRPYIF1, LNRPYIF);
        LNCRPYIF.RC.RC_MMO = "LN";
        LNCRPYIF.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (LNCRPYIF.FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRPYIF.FUNC == 'G') {
            B010_GROUP_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRPYIF.FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRPYIF.FUNC == 'R') {
            B030_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRPYIF.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRPYIF.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRPYIF.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCRPYIF.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, LNRPYIF, LNRPYIF1);
    }
    public void B010_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_REC_PROC();
        if (pgmRtn) return;
    }
    public void B010_GROUP_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_GROUP_PROC();
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
        if (LNCRPYIF.OPT == 'S') {
            B060_10_START_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (LNCRPYIF.OPT == 'A') {
            B060_20_STBR_BY_KEY_PROC();
            if (pgmRtn) return;
        } else if (LNCRPYIF.OPT == 'B') {
            B060_20_STBR_BY_KEY1_F_PROC();
            if (pgmRtn) return;
        } else if (LNCRPYIF.OPT == 'C') {
            B060_20_STBR_BY_CONTRACT_PROC();
            if (pgmRtn) return;
        } else if (LNCRPYIF.OPT == 'R') {
            B060_30_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (LNCRPYIF.OPT == 'E') {
            B060_50_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + LNCRPYIF.OPT + ")";
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
    public void B060_20_STBR_BY_KEY1_F_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_KEY1_F_PROC();
        if (pgmRtn) return;
    }
    public void B060_20_STBR_BY_CONTRACT_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_CONTRACT_PROC();
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
        LNTPYIF_RD = new DBParm();
        LNTPYIF_RD.TableName = "LNTPYIF";
        IBS.READ(SCCGWA, LNRPYIF, LNTPYIF_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PYIF_NOTFND, LNCRPYIF.RC);
            LNCRPYIF.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRPYIF.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRPYIF.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTPYIF_RD = new DBParm();
        LNTPYIF_RD.TableName = "LNTPYIF";
        LNTPYIF_RD.errhdl = true;
        IBS.WRITE(SCCGWA, LNRPYIF, LNTPYIF_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY, LNCRPYIF.RC);
            LNCRPYIF.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        LNTPYIF_RD = new DBParm();
        LNTPYIF_RD.TableName = "LNTPYIF";
        LNTPYIF_RD.upd = true;
        IBS.READ(SCCGWA, LNRPYIF, LNTPYIF_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PYIF_NOTFND, LNCRPYIF.RC);
            LNCRPYIF.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRPYIF.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRPYIF.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTPYIF_RD = new DBParm();
        LNTPYIF_RD.TableName = "LNTPYIF";
        IBS.REWRITE(SCCGWA, LNRPYIF, LNTPYIF_RD);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        LNTPYIF_RD = new DBParm();
        LNTPYIF_RD.TableName = "LNTPYIF";
        IBS.DELETE(SCCGWA, LNRPYIF, LNTPYIF_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PYIF_NOTFND, LNCRPYIF.RC);
            LNCRPYIF.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_PROC() throws IOException,SQLException,Exception {
        LNTPYIF_BR.rp = new DBParm();
        LNTPYIF_BR.rp.TableName = "LNTPYIF";
        LNTPYIF_BR.rp.order = "CONTRACT_NO,SUB_CTA_NO,TERM,REPY_SEQ";
        IBS.STARTBR(SCCGWA, LNRPYIF, LNTPYIF_BR);
    }
    public void T000_STARTBR_BY_KEY_PROC() throws IOException,SQLException,Exception {
        LNTPYIF_BR.rp = new DBParm();
        LNTPYIF_BR.rp.TableName = "LNTPYIF";
        LNTPYIF_BR.rp.where = "CONTRACT_NO >= :LNRPYIF.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO >= :LNRPYIF.KEY.SUB_CTA_NO "
            + "AND TERM >= :LNRPYIF.KEY.TERM "
            + "AND REPY_SEQ >= :LNRPYIF.KEY.REPY_SEQ";
        LNTPYIF_BR.rp.order = "CONTRACT_NO,SUB_CTA_NO,TERM,REPY_SEQ";
        IBS.STARTBR(SCCGWA, LNRPYIF, this, LNTPYIF_BR);
    }
    public void T000_STARTBR_BY_KEY1_F_PROC() throws IOException,SQLException,Exception {
        LNTPYIF_RD = new DBParm();
        LNTPYIF_RD.TableName = "LNTPYIF";
        LNTPYIF_RD.where = "CONTRACT_NO = :LNRPYIF.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRPYIF.KEY.SUB_CTA_NO "
            + "AND TERM = :LNRPYIF.KEY.TERM";
        LNTPYIF_RD.fst = true;
        LNTPYIF_RD.order = "CUR_REPY_DT DESC";
        IBS.READ(SCCGWA, LNRPYIF, this, LNTPYIF_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PYIF_NOTFND, LNCRPYIF.RC);
            LNCRPYIF.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BY_CONTRACT_PROC() throws IOException,SQLException,Exception {
        LNTPYIF_BR.rp = new DBParm();
        LNTPYIF_BR.rp.TableName = "LNTPYIF";
        LNTPYIF_BR.rp.where = "CONTRACT_NO = :LNRPYIF.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRPYIF.KEY.SUB_CTA_NO "
            + "AND TERM = :LNRPYIF.KEY.TERM";
        LNTPYIF_BR.rp.order = "REPY_SEQ DESC";
        IBS.STARTBR(SCCGWA, LNRPYIF, this, LNTPYIF_BR);
    }
    public void T000_GROUP_PROC() throws IOException,SQLException,Exception {
        WS_TOT_CNT = 0;
        WS_TOT_SUM = 0;
        LNTPYIF_RD = new DBParm();
        LNTPYIF_RD.TableName = "LNTPYIF";
        LNTPYIF_RD.set = "WS-TOT-CNT=COUNT(*),WS-TOT-SUM=IFNULL(SUM(REIM_PRIN_AMT),0)";
        LNTPYIF_RD.where = "CONTRACT_NO = :LNRPYIF.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRPYIF.KEY.SUB_CTA_NO";
        IBS.GROUP(SCCGWA, LNRPYIF, this, LNTPYIF_RD);
        LNCRPYIF.TOT_CNT = WS_TOT_CNT;
        LNCRPYIF.TOT_SUM = WS_TOT_SUM;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PYIF_NOTFND, LNCRPYIF.RC);
            LNCRPYIF.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '3') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_OTHER, LNCRPYIF.RC);
            LNCRPYIF.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRPYIF, this, LNTPYIF_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PYIF_NOTFND, LNCRPYIF.RC);
            LNCRPYIF.RETURN_INFO = 'E';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTPYIF_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCRPYIF.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "LNCRPYIF=");
            CEP.TRC(SCCGWA, LNCRPYIF);
            CEP.TRC(SCCGWA, "LNRPYIF =");
            CEP.TRC(SCCGWA, LNRPYIF);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
