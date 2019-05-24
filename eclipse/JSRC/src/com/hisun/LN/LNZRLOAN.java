package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZRLOAN {
    DBParm LNTLOAN_RD;
    brParm LNTLOAN_BR = new brParm();
    boolean pgmRtn = false;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    LNRLOAN LNRLOAN = new LNRLOAN();
    SCCGWA SCCGWA;
    LNCRLOAN LNCRLOAN;
    LNRLOAN LNRLOAN1;
    public void MP(SCCGWA SCCGWA, LNCRLOAN LNCRLOAN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCRLOAN = LNCRLOAN;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZRLOAN return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNRLOAN1 = (LNRLOAN) LNCRLOAN.REC_PTR;
        LNCRLOAN.RETURN_INFO = 'F';
        IBS.init(SCCGWA, LNRLOAN);
        IBS.CLONE(SCCGWA, LNRLOAN1, LNRLOAN);
        LNCRLOAN.RC.RC_MMO = "LN";
        LNCRLOAN.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (LNCRLOAN.FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRLOAN.FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRLOAN.FUNC == 'R') {
            B030_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRLOAN.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRLOAN.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRLOAN.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCRLOAN.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, LNRLOAN, LNRLOAN1);
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
        if (LNCRLOAN.OPT == 'S') {
            B060_10_START_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (LNCRLOAN.OPT == 'U') {
            B060_15_START_BROWSE_UPT();
            if (pgmRtn) return;
        } else if (LNCRLOAN.OPT == 'A') {
            B060_30_STBR_BY_KEY_PROC();
            if (pgmRtn) return;
        } else if (LNCRLOAN.OPT == 'R') {
            B060_70_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (LNCRLOAN.OPT == 'E') {
            B060_90_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + LNCRLOAN.OPT + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B060_10_START_BROWSE_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_PROC();
        if (pgmRtn) return;
    }
    public void B060_15_START_BROWSE_UPT() throws IOException,SQLException,Exception {
        T000_STARTBR_UPDATE_PROC();
        if (pgmRtn) return;
    }
    public void B060_30_STBR_BY_KEY_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_KEY_PROC();
        if (pgmRtn) return;
    }
    public void B060_70_READ_NEXT_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_PROC();
        if (pgmRtn) return;
    }
    public void B060_90_END_BROWSE_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_PROC();
        if (pgmRtn) return;
    }
    public void T000_READ_REC_PROC() throws IOException,SQLException,Exception {
        LNTLOAN_RD = new DBParm();
        LNTLOAN_RD.TableName = "LNTLOAN";
        IBS.READ(SCCGWA, LNRLOAN, LNTLOAN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_LOAN_NOTFND, LNCRLOAN.RC);
            LNCRLOAN.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRLOAN.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRLOAN.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRLOAN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRLOAN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTLOAN_RD = new DBParm();
        LNTLOAN_RD.TableName = "LNTLOAN";
        LNTLOAN_RD.errhdl = true;
        IBS.WRITE(SCCGWA, LNRLOAN, LNTLOAN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY, LNCRLOAN.RC);
            LNCRLOAN.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        LNTLOAN_RD = new DBParm();
        LNTLOAN_RD.TableName = "LNTLOAN";
        LNTLOAN_RD.upd = true;
        IBS.READ(SCCGWA, LNRLOAN, LNTLOAN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_LOAN_NOTFND, LNCRLOAN.RC);
            LNCRLOAN.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRLOAN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRLOAN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTLOAN_RD = new DBParm();
        LNTLOAN_RD.TableName = "LNTLOAN";
        IBS.REWRITE(SCCGWA, LNRLOAN, LNTLOAN_RD);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        LNTLOAN_RD = new DBParm();
        LNTLOAN_RD.TableName = "LNTLOAN";
        IBS.DELETE(SCCGWA, LNRLOAN, LNTLOAN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_LOAN_NOTFND, LNCRLOAN.RC);
            LNCRLOAN.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_PROC() throws IOException,SQLException,Exception {
        LNTLOAN_BR.rp = new DBParm();
        LNTLOAN_BR.rp.TableName = "LNTLOAN";
        LNTLOAN_BR.rp.order = "CONTRACT_NO";
        IBS.STARTBR(SCCGWA, LNRLOAN, LNTLOAN_BR);
    }
    public void T000_STARTBR_UPDATE_PROC() throws IOException,SQLException,Exception {
        LNTLOAN_BR.rp = new DBParm();
        LNTLOAN_BR.rp.TableName = "LNTLOAN";
        LNTLOAN_BR.rp.upd = true;
        LNTLOAN_BR.rp.order = "CONTRACT_NO";
        IBS.STARTBR(SCCGWA, LNRLOAN, LNTLOAN_BR);
    }
    public void T000_STARTBR_BY_KEY_PROC() throws IOException,SQLException,Exception {
        LNTLOAN_BR.rp = new DBParm();
        LNTLOAN_BR.rp.TableName = "LNTLOAN";
        LNTLOAN_BR.rp.where = "CONTRACT_NO >= :LNRLOAN.KEY.CONTRACT_NO";
        LNTLOAN_BR.rp.order = "CONTRACT_NO";
        IBS.STARTBR(SCCGWA, LNRLOAN, this, LNTLOAN_BR);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRLOAN, this, LNTLOAN_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_LOAN_NOTFND, LNCRLOAN.RC);
            LNCRLOAN.RETURN_INFO = 'E';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTLOAN_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
