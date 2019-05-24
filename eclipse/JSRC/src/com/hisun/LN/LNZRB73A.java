package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZRB73A {
    DBParm LNTB73A_RD;
    brParm LNTB73A_BR = new brParm();
    boolean pgmRtn = false;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    LNRB73A LNRB73A = new LNRB73A();
    SCCGWA SCCGWA;
    LNCRB73A LNCRB73A;
    LNRB73A LNRB73AA;
    public void MP(SCCGWA SCCGWA, LNCRB73A LNCRB73A) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCRB73A = LNCRB73A;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZRB73A return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNRB73AA = (LNRB73A) LNCRB73A.REC_PTR;
        LNCRB73A.RETURN_INFO = 'F';
        IBS.init(SCCGWA, LNRB73A);
        IBS.CLONE(SCCGWA, LNRB73AA, LNRB73A);
        LNCRB73A.RC.RC_MMO = "LN";
        LNCRB73A.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (LNCRB73A.FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRB73A.FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRB73A.FUNC == 'R') {
            B030_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRB73A.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRB73A.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRB73A.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCRB73A.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, LNRB73A, LNRB73AA);
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
        if (LNCRB73A.OPT == 'S') {
            B060_10_START_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (LNCRB73A.OPT == 'R') {
            B060_30_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (LNCRB73A.OPT == 'E') {
            B060_40_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + LNCRB73A.OPT + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B060_10_START_BROWSE_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_PROC();
        if (pgmRtn) return;
    }
    public void B060_30_READ_NEXT_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_PROC();
        if (pgmRtn) return;
    }
    public void B060_40_END_BROWSE_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_PROC();
        if (pgmRtn) return;
    }
    public void T000_READ_REC_PROC() throws IOException,SQLException,Exception {
        LNTB73A_RD = new DBParm();
        LNTB73A_RD.TableName = "LNTB73A";
        IBS.READ(SCCGWA, LNRB73A, LNTB73A_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_REC_NOTFND, LNCRB73A.RC);
            LNCRB73A.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNTB73A_RD = new DBParm();
        LNTB73A_RD.TableName = "LNTB73A";
        IBS.READ(SCCGWA, LNRB73A, LNTB73A_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            LNRB73A.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            LNRB73A.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            LNRB73A.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            LNRB73A.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            LNTB73A_RD = new DBParm();
            LNTB73A_RD.TableName = "LNTB73A";
            IBS.WRITE(SCCGWA, LNRB73A, LNTB73A_RD);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY, LNCRB73A.RC);
                LNCRB73A.RETURN_INFO = 'D';
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY, LNCRB73A.RC);
            LNCRB73A.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        LNTB73A_RD = new DBParm();
        LNTB73A_RD.TableName = "LNTB73A";
        LNTB73A_RD.upd = true;
        IBS.READ(SCCGWA, LNRB73A, LNTB73A_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_REC_NOTFND, LNCRB73A.RC);
            LNCRB73A.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRB73A.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRB73A.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTB73A_RD = new DBParm();
        LNTB73A_RD.TableName = "LNTB73A";
        IBS.REWRITE(SCCGWA, LNRB73A, LNTB73A_RD);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        LNTB73A_RD = new DBParm();
        LNTB73A_RD.TableName = "LNTB73A";
        IBS.DELETE(SCCGWA, LNRB73A, LNTB73A_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_REC_NOTFND, LNCRB73A.RC);
            LNCRB73A.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_PROC() throws IOException,SQLException,Exception {
        LNTB73A_BR.rp = new DBParm();
        LNTB73A_BR.rp.TableName = "LNTB73A";
        IBS.STARTBR(SCCGWA, LNRB73A, LNTB73A_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            LNCRB73A.RETURN_INFO = 'N';
        }
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRB73A, this, LNTB73A_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            LNCRB73A.RETURN_INFO = 'E';
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTB73A_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCRB73A.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "LNCRB73A=");
            CEP.TRC(SCCGWA, LNCRB73A);
            CEP.TRC(SCCGWA, "LNRB73A =");
            CEP.TRC(SCCGWA, LNRB73A);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
