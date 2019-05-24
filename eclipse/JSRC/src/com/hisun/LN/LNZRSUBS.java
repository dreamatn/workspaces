package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZRSUBS {
    DBParm LNTSUBS_RD;
    brParm LNTSUBS_BR = new brParm();
    boolean pgmRtn = false;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    LNRSUBS LNRSUBS = new LNRSUBS();
    SCCGWA SCCGWA;
    LNCRSUBS LNCRSUBS;
    LNRSUBS LNRSUBS1;
    public void MP(SCCGWA SCCGWA, LNCRSUBS LNCRSUBS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCRSUBS = LNCRSUBS;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZRSUBS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNRSUBS1 = (LNRSUBS) LNCRSUBS.REC_PTR;
        LNCRSUBS.RETURN_INFO = 'F';
        IBS.init(SCCGWA, LNRSUBS);
        IBS.CLONE(SCCGWA, LNRSUBS1, LNRSUBS);
        LNCRSUBS.RC.RC_MMO = "LN";
        LNCRSUBS.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (LNCRSUBS.FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRSUBS.FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRSUBS.FUNC == 'R') {
            B030_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRSUBS.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRSUBS.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRSUBS.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCRSUBS.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, LNRSUBS, LNRSUBS1);
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
        if (LNCRSUBS.OPT == 'S') {
            B060_10_START_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (LNCRSUBS.OPT == 'A') {
            B060_20_STBR_BY_KEY_PROC();
            if (pgmRtn) return;
        } else if (LNCRSUBS.OPT == 'T') {
            B060_20_STBR_CONT_TYPE_PROC();
            if (pgmRtn) return;
        } else if (LNCRSUBS.OPT == 'N') {
            B060_20_STBR_CONT_NO_TYP_PROC();
            if (pgmRtn) return;
        } else if (LNCRSUBS.OPT == 'R') {
            B060_30_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (LNCRSUBS.OPT == 'E') {
            B060_50_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + LNCRSUBS.OPT + ")";
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
    public void B060_20_STBR_CONT_TYPE_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_CONT_TYPE_PROC();
        if (pgmRtn) return;
    }
    public void B060_20_STBR_CONT_NO_TYP_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_CONT_NO_TYP_PROC();
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
        LNTSUBS_RD = new DBParm();
        LNTSUBS_RD.TableName = "LNTSUBS";
        IBS.READ(SCCGWA, LNRSUBS, LNTSUBS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SUBS_EXIST, LNCRSUBS.RC);
            LNCRSUBS.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRSUBS.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRSUBS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRSUBS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRSUBS.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTSUBS_RD = new DBParm();
        LNTSUBS_RD.TableName = "LNTSUBS";
        LNTSUBS_RD.errhdl = true;
        IBS.WRITE(SCCGWA, LNRSUBS, LNTSUBS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY, LNCRSUBS.RC);
            LNCRSUBS.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        LNTSUBS_RD = new DBParm();
        LNTSUBS_RD.TableName = "LNTSUBS";
        LNTSUBS_RD.upd = true;
        IBS.READ(SCCGWA, LNRSUBS, LNTSUBS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SUBS_EXIST, LNCRSUBS.RC);
            LNCRSUBS.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRSUBS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRSUBS.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTSUBS_RD = new DBParm();
        LNTSUBS_RD.TableName = "LNTSUBS";
        IBS.REWRITE(SCCGWA, LNRSUBS, LNTSUBS_RD);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        LNTSUBS_RD = new DBParm();
        LNTSUBS_RD.TableName = "LNTSUBS";
        IBS.DELETE(SCCGWA, LNRSUBS, LNTSUBS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SUBS_EXIST, LNCRSUBS.RC);
            LNCRSUBS.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_PROC() throws IOException,SQLException,Exception {
        LNTSUBS_BR.rp = new DBParm();
        LNTSUBS_BR.rp.TableName = "LNTSUBS";
        LNTSUBS_BR.rp.order = "PROJ_NO";
        IBS.STARTBR(SCCGWA, LNRSUBS, LNTSUBS_BR);
    }
    public void T000_STARTBR_BY_KEY_PROC() throws IOException,SQLException,Exception {
        LNTSUBS_BR.rp = new DBParm();
        LNTSUBS_BR.rp.TableName = "LNTSUBS";
        LNTSUBS_BR.rp.where = "PROJ_NO = :LNRSUBS.KEY.PROJ_NO";
        LNTSUBS_BR.rp.order = "PROJ_NO";
        IBS.STARTBR(SCCGWA, LNRSUBS, this, LNTSUBS_BR);
    }
    public void T000_STARTBR_CONT_TYPE_PROC() throws IOException,SQLException,Exception {
        LNTSUBS_BR.rp = new DBParm();
        LNTSUBS_BR.rp.TableName = "LNTSUBS";
        LNTSUBS_BR.rp.where = "PROJ_NO = :LNRSUBS.KEY.PROJ_NO "
            + "AND TYP = :LNRSUBS.TYP";
        LNTSUBS_BR.rp.order = "PROJ_NO";
        IBS.STARTBR(SCCGWA, LNRSUBS, this, LNTSUBS_BR);
    }
    public void T000_STARTBR_CONT_NO_TYP_PROC() throws IOException,SQLException,Exception {
        LNTSUBS_BR.rp = new DBParm();
        LNTSUBS_BR.rp.TableName = "LNTSUBS";
        LNTSUBS_BR.rp.where = "PROJ_NO = :LNRSUBS.KEY.PROJ_NO "
            + "AND CI_NO = :LNRSUBS.CI_NO "
            + "AND TYP = :LNRSUBS.TYP";
        LNTSUBS_BR.rp.order = "PROJ_NO";
        IBS.STARTBR(SCCGWA, LNRSUBS, this, LNTSUBS_BR);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRSUBS, this, LNTSUBS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SUBS_EXIST, LNCRSUBS.RC);
            LNCRSUBS.RETURN_INFO = 'E';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTSUBS_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
