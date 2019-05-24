package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZRPPRP {
    DBParm LNTPPRP_RD;
    brParm LNTPPRP_BR = new brParm();
    boolean pgmRtn = false;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    LNRPPRP LNRPPRP = new LNRPPRP();
    SCCGWA SCCGWA;
    LNCRPPRP LNCRPPRP;
    LNRPPRP LNRPPRP1;
    public void MP(SCCGWA SCCGWA, LNCRPPRP LNCRPPRP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCRPPRP = LNCRPPRP;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZRPPRP return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNRPPRP1 = (LNRPPRP) LNCRPPRP.REC_PTR;
        LNCRPPRP.RETURN_INFO = 'F';
        IBS.init(SCCGWA, LNRPPRP);
        IBS.CLONE(SCCGWA, LNRPPRP1, LNRPPRP);
        LNCRPPRP.RC.RC_MMO = "LN";
        LNCRPPRP.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (LNCRPPRP.FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRPPRP.FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRPPRP.FUNC == 'R') {
            B030_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRPPRP.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRPPRP.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRPPRP.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCRPPRP.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, LNRPPRP, LNRPPRP1);
    }
    public void B010_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNRPPRP.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRPPRP.KEY.VAL_DT);
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
        if (LNCRPPRP.OPT == 'S') {
            B060_10_START_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (LNCRPPRP.OPT == 'A') {
            B060_20_STBR_BY_KEY_PROC();
            if (pgmRtn) return;
        } else if (LNCRPPRP.OPT == 'R') {
            B060_30_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (LNCRPPRP.OPT == 'E') {
            B060_40_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + LNCRPPRP.OPT + ")";
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
    public void B060_40_END_BROWSE_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_PROC();
        if (pgmRtn) return;
    }
    public void T000_READ_REC_PROC() throws IOException,SQLException,Exception {
        LNTPPRP_RD = new DBParm();
        LNTPPRP_RD.TableName = "LNTPPRP";
        IBS.READ(SCCGWA, LNRPPRP, LNTPPRP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_REC_NOTFND, LNCRPPRP.RC);
            LNCRPPRP.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNTPPRP_RD = new DBParm();
        LNTPPRP_RD.TableName = "LNTPPRP";
        IBS.READ(SCCGWA, LNRPPRP, LNTPPRP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            LNRPPRP.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            LNRPPRP.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            LNRPPRP.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            LNRPPRP.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            LNTPPRP_RD = new DBParm();
            LNTPPRP_RD.TableName = "LNTPPRP";
            IBS.WRITE(SCCGWA, LNRPPRP, LNTPPRP_RD);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY, LNCRPPRP.RC);
                LNCRPPRP.RETURN_INFO = 'D';
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY, LNCRPPRP.RC);
            LNCRPPRP.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        LNTPPRP_RD = new DBParm();
        LNTPPRP_RD.TableName = "LNTPPRP";
        LNTPPRP_RD.upd = true;
        IBS.READ(SCCGWA, LNRPPRP, LNTPPRP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_REC_NOTFND, LNCRPPRP.RC);
            LNCRPPRP.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRPPRP.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRPPRP.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTPPRP_RD = new DBParm();
        LNTPPRP_RD.TableName = "LNTPPRP";
        IBS.REWRITE(SCCGWA, LNRPPRP, LNTPPRP_RD);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        LNTPPRP_RD = new DBParm();
        LNTPPRP_RD.TableName = "LNTPPRP";
        IBS.DELETE(SCCGWA, LNRPPRP, LNTPPRP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_REC_NOTFND, LNCRPPRP.RC);
            LNCRPPRP.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_PROC() throws IOException,SQLException,Exception {
        LNTPPRP_BR.rp = new DBParm();
        LNTPPRP_BR.rp.TableName = "LNTPPRP";
        LNTPPRP_BR.rp.where = "VAL_DT >= :LNRPPRP.KEY.VAL_DT";
        LNTPPRP_BR.rp.order = "VAL_DT,CONTRACT_NO";
        IBS.STARTBR(SCCGWA, LNRPPRP, this, LNTPPRP_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            LNCRPPRP.RETURN_INFO = 'N';
        }
    }
    public void T000_STARTBR_BY_KEY_PROC() throws IOException,SQLException,Exception {
        LNTPPRP_BR.rp = new DBParm();
        LNTPPRP_BR.rp.TableName = "LNTPPRP";
        LNTPPRP_BR.rp.where = "CONTRACT_NO = :LNRPPRP.KEY.CONTRACT_NO "
            + "AND VAL_DT >= :LNRPPRP.KEY.VAL_DT";
        LNTPPRP_BR.rp.order = "VAL_DT,CONTRACT_NO";
        IBS.STARTBR(SCCGWA, LNRPPRP, this, LNTPPRP_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            LNCRPPRP.RETURN_INFO = 'N';
        }
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRPPRP, this, LNTPPRP_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            LNCRPPRP.RETURN_INFO = 'E';
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTPPRP_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCRPPRP.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "LNCRPPRP=");
            CEP.TRC(SCCGWA, LNCRPPRP);
            CEP.TRC(SCCGWA, "LNRPPRP =");
            CEP.TRC(SCCGWA, LNRPPRP);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
