package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZRB51A {
    DBParm LNTB51A_RD;
    brParm LNTB51A_BR = new brParm();
    boolean pgmRtn = false;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    LNRB51A LNRB51A = new LNRB51A();
    SCCGWA SCCGWA;
    LNCRB51A LNCRB51A;
    LNRB51A LNRB51AA;
    public void MP(SCCGWA SCCGWA, LNCRB51A LNCRB51A) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCRB51A = LNCRB51A;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZRB51A return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNRB51AA = (LNRB51A) LNCRB51A.REC_PTR;
        LNCRB51A.RETURN_INFO = 'F';
        IBS.init(SCCGWA, LNRB51A);
        IBS.CLONE(SCCGWA, LNRB51AA, LNRB51A);
        LNCRB51A.RC.RC_MMO = "LN";
        LNCRB51A.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (LNCRB51A.FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRB51A.FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRB51A.FUNC == 'R') {
            B030_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRB51A.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRB51A.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRB51A.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCRB51A.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, LNRB51A, LNRB51AA);
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
        if (LNCRB51A.OPT == 'S') {
            B060_10_START_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (LNCRB51A.OPT == 'R') {
            B060_30_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (LNCRB51A.OPT == 'E') {
            B060_40_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + LNCRB51A.OPT + ")";
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
        LNTB51A_RD = new DBParm();
        LNTB51A_RD.TableName = "LNTB51A";
        IBS.READ(SCCGWA, LNRB51A, LNTB51A_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_REC_NOTFND, LNCRB51A.RC);
            LNCRB51A.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNTB51A_RD = new DBParm();
        LNTB51A_RD.TableName = "LNTB51A";
        IBS.READ(SCCGWA, LNRB51A, LNTB51A_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            LNRB51A.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            LNRB51A.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            LNRB51A.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            LNRB51A.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            LNTB51A_RD = new DBParm();
            LNTB51A_RD.TableName = "LNTB51A";
            IBS.WRITE(SCCGWA, LNRB51A, LNTB51A_RD);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY, LNCRB51A.RC);
                LNCRB51A.RETURN_INFO = 'D';
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY, LNCRB51A.RC);
            LNCRB51A.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        LNTB51A_RD = new DBParm();
        LNTB51A_RD.TableName = "LNTB51A";
        LNTB51A_RD.upd = true;
        IBS.READ(SCCGWA, LNRB51A, LNTB51A_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_REC_NOTFND, LNCRB51A.RC);
            LNCRB51A.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRB51A.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRB51A.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTB51A_RD = new DBParm();
        LNTB51A_RD.TableName = "LNTB51A";
        IBS.REWRITE(SCCGWA, LNRB51A, LNTB51A_RD);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        LNTB51A_RD = new DBParm();
        LNTB51A_RD.TableName = "LNTB51A";
        IBS.DELETE(SCCGWA, LNRB51A, LNTB51A_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_REC_NOTFND, LNCRB51A.RC);
            LNCRB51A.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_PROC() throws IOException,SQLException,Exception {
        LNTB51A_BR.rp = new DBParm();
        LNTB51A_BR.rp.TableName = "LNTB51A";
        IBS.STARTBR(SCCGWA, LNRB51A, LNTB51A_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            LNCRB51A.RETURN_INFO = 'N';
        }
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRB51A, this, LNTB51A_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            LNCRB51A.RETURN_INFO = 'E';
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTB51A_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCRB51A.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "LNCRB51A=");
            CEP.TRC(SCCGWA, LNCRB51A);
            CEP.TRC(SCCGWA, "LNRB51A =");
            CEP.TRC(SCCGWA, LNRB51A);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
