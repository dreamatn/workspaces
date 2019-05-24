package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZRACRU {
    DBParm LNTACRU_RD;
    brParm LNTACRU_BR = new brParm();
    boolean pgmRtn = false;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    LNRACRU LNRACRU = new LNRACRU();
    SCCGWA SCCGWA;
    LNCRACRU LNCRACRU;
    LNRACRU LNRACRUA;
    public void MP(SCCGWA SCCGWA, LNCRACRU LNCRACRU) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCRACRU = LNCRACRU;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZRACRU return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        LNRACRUA = (LNRACRU) LNCRACRU.REC_PTR;
        LNCRACRU.RETURN_INFO = 'F';
        IBS.init(SCCGWA, LNRACRU);
        IBS.CLONE(SCCGWA, LNRACRUA, LNRACRU);
        LNCRACRU.RC.RC_MMO = "LN";
        LNCRACRU.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (LNCRACRU.FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRACRU.FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRACRU.FUNC == 'R') {
            B030_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRACRU.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRACRU.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRACRU.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCRACRU.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, LNRACRU, LNRACRUA);
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
        if (LNCRACRU.OPT == 'S') {
            B060_10_START_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (LNCRACRU.OPT == 'A') {
            B060_20_STBR_BY_KEY_PROC();
            if (pgmRtn) return;
        } else if (LNCRACRU.OPT == 'R') {
            B060_30_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (LNCRACRU.OPT == 'E') {
            B060_50_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + LNCRACRU.OPT + ")";
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
    public void B060_30_READ_NEXT_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_PROC();
        if (pgmRtn) return;
    }
    public void B060_50_END_BROWSE_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_PROC();
        if (pgmRtn) return;
    }
    public void T000_READ_REC_PROC() throws IOException,SQLException,Exception {
        LNTACRU_RD = new DBParm();
        LNTACRU_RD.TableName = "LNTACRU";
        IBS.READ(SCCGWA, LNRACRU, LNTACRU_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ACRU_NOTFND, LNCRACRU.RC);
            LNCRACRU.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRACRU.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRACRU.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTACRU_RD = new DBParm();
        LNTACRU_RD.TableName = "LNTACRU";
        LNTACRU_RD.errhdl = true;
        IBS.WRITE(SCCGWA, LNRACRU, LNTACRU_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY, LNCRACRU.RC);
            LNCRACRU.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        LNTACRU_RD = new DBParm();
        LNTACRU_RD.TableName = "LNTACRU";
        LNTACRU_RD.upd = true;
        IBS.READ(SCCGWA, LNRACRU, LNTACRU_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ACRU_NOTFND, LNCRACRU.RC);
            LNCRACRU.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRACRU.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRACRU.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTACRU_RD = new DBParm();
        LNTACRU_RD.TableName = "LNTACRU";
        IBS.REWRITE(SCCGWA, LNRACRU, LNTACRU_RD);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        LNTACRU_RD = new DBParm();
        LNTACRU_RD.TableName = "LNTACRU";
        IBS.DELETE(SCCGWA, LNRACRU, LNTACRU_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ACRU_NOTFND, LNCRACRU.RC);
            LNCRACRU.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_PROC() throws IOException,SQLException,Exception {
        LNTACRU_BR.rp = new DBParm();
        LNTACRU_BR.rp.TableName = "LNTACRU";
        LNTACRU_BR.rp.order = "CONTRACT_NO,START_DATE";
        IBS.STARTBR(SCCGWA, LNRACRU, LNTACRU_BR);
    }
    public void T000_STARTBR_BY_KEY_PROC() throws IOException,SQLException,Exception {
        LNTACRU_BR.rp = new DBParm();
        LNTACRU_BR.rp.TableName = "LNTACRU";
        LNTACRU_BR.rp.eqWhere = "CONTRACT_NO";
        LNTACRU_BR.rp.where = "START_DATE >= :LNRACRU.KEY.START_DATE";
        LNTACRU_BR.rp.order = "CONTRACT_NO,START_DATE";
        IBS.STARTBR(SCCGWA, LNRACRU, this, LNTACRU_BR);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRACRU, this, LNTACRU_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ACRU_NOTFND, LNCRACRU.RC);
            LNCRACRU.RETURN_INFO = 'E';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTACRU_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
