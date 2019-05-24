package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZRA002 {
    DBParm LNTA002_RD;
    brParm LNTA002_BR = new brParm();
    boolean pgmRtn = false;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    LNRA002 LNRA002 = new LNRA002();
    SCCGWA SCCGWA;
    LNCRA002 LNCRA002;
    LNRA002 LNRA002A;
    public void MP(SCCGWA SCCGWA, LNCRA002 LNCRA002) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCRA002 = LNCRA002;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZRA002 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        LNRA002A = (LNRA002) LNCRA002.REC_PTR;
        LNCRA002.RETURN_INFO = 'F';
        IBS.init(SCCGWA, LNRA002);
        IBS.CLONE(SCCGWA, LNRA002A, LNRA002);
        LNCRA002.RC.RC_MMO = "LN";
        LNCRA002.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (LNCRA002.FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRA002.FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRA002.FUNC == 'R') {
            B030_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRA002.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRA002.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRA002.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCRA002.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, LNRA002, LNRA002A);
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
        if (LNCRA002.OPT == 'S') {
            B060_10_START_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (LNCRA002.OPT == 'A') {
            B060_20_STBR_BY_KEY_PROC();
            if (pgmRtn) return;
        } else if (LNCRA002.OPT == 'R') {
            B060_30_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (LNCRA002.OPT == 'E') {
            B060_50_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + LNCRA002.OPT + ")";
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
        LNTA002_RD = new DBParm();
        LNTA002_RD.TableName = "LNTA002";
        IBS.READ(SCCGWA, LNRA002, LNTA002_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_RECO_NOTFND, LNCRA002.RC);
            LNCRA002.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRA002.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRA002.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTA002_RD = new DBParm();
        LNTA002_RD.TableName = "LNTA002";
        LNTA002_RD.errhdl = true;
        IBS.WRITE(SCCGWA, LNRA002, LNTA002_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY, LNCRA002.RC);
            LNCRA002.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        LNTA002_RD = new DBParm();
        LNTA002_RD.TableName = "LNTA002";
        LNTA002_RD.upd = true;
        IBS.READ(SCCGWA, LNRA002, LNTA002_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_RECO_NOTFND, LNCRA002.RC);
            LNCRA002.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRA002.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRA002.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTA002_RD = new DBParm();
        LNTA002_RD.TableName = "LNTA002";
        IBS.REWRITE(SCCGWA, LNRA002, LNTA002_RD);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        LNTA002_RD = new DBParm();
        LNTA002_RD.TableName = "LNTA002";
        IBS.DELETE(SCCGWA, LNRA002, LNTA002_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_RECO_NOTFND, LNCRA002.RC);
            LNCRA002.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_PROC() throws IOException,SQLException,Exception {
        LNTA002_BR.rp = new DBParm();
        LNTA002_BR.rp.TableName = "LNTA002";
        IBS.STARTBR(SCCGWA, LNRA002, LNTA002_BR);
    }
    public void T000_STARTBR_BY_KEY_PROC() throws IOException,SQLException,Exception {
        LNTA002_BR.rp = new DBParm();
        LNTA002_BR.rp.TableName = "LNTA002";
        LNTA002_BR.rp.where = "CONTRACT_NO = :LNRA002.KEY.CONTRACT_NO "
            + "AND DUE_DATE >= :LNRA002.KEY.DUE_DATE";
        LNTA002_BR.rp.order = "CONTRACT_NO,DUE_DATE";
        IBS.STARTBR(SCCGWA, LNRA002, this, LNTA002_BR);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRA002, this, LNTA002_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_RECO_NOTFND, LNCRA002.RC);
            LNCRA002.RETURN_INFO = 'E';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTA002_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCRA002.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "LNCRA002=");
            CEP.TRC(SCCGWA, LNCRA002);
            CEP.TRC(SCCGWA, "LNRA002 =");
            CEP.TRC(SCCGWA, LNRA002);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
