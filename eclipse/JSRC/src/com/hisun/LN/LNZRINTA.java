package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZRINTA {
    DBParm LNTINTA_RD;
    brParm LNTINTA_BR = new brParm();
    boolean pgmRtn = false;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    LNRINTA LNRINTA = new LNRINTA();
    SCCGWA SCCGWA;
    LNCRINTA LNCRINTA;
    LNRINTA LNRINTA1;
    public void MP(SCCGWA SCCGWA, LNCRINTA LNCRINTA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCRINTA = LNCRINTA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZRINTA return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNRINTA1 = (LNRINTA) LNCRINTA.REC_PTR;
        LNCRINTA.RETURN_INFO = 'F';
        IBS.init(SCCGWA, LNRINTA);
        IBS.CLONE(SCCGWA, LNRINTA1, LNRINTA);
        LNCRINTA.RC.RC_MMO = "LN";
        LNCRINTA.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (LNCRINTA.FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRINTA.FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRINTA.FUNC == 'R') {
            B030_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRINTA.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRINTA.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRINTA.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCRINTA.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, LNRINTA, LNRINTA1);
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
        if (LNCRINTA.OPT == 'S') {
            B060_10_START_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (LNCRINTA.OPT == 'A') {
            B060_20_STBR_BY_KEY_PROC();
            if (pgmRtn) return;
        } else if (LNCRINTA.OPT == 'R') {
            B060_30_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (LNCRINTA.OPT == 'E') {
            B060_50_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + LNCRINTA.OPT + ")";
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
        LNTINTA_RD = new DBParm();
        LNTINTA_RD.TableName = "LNTINTA";
        IBS.READ(SCCGWA, LNRINTA, LNTINTA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_RECO_NOTFND, LNCRINTA.RC);
            LNCRINTA.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRINTA.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRINTA.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRINTA.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRINTA.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTINTA_RD = new DBParm();
        LNTINTA_RD.TableName = "LNTINTA";
        LNTINTA_RD.errhdl = true;
        IBS.WRITE(SCCGWA, LNRINTA, LNTINTA_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.TRC(SCCGWA, "DUPKEY.......");
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY, LNCRINTA.RC);
            LNCRINTA.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        LNTINTA_RD = new DBParm();
        LNTINTA_RD.TableName = "LNTINTA";
        LNTINTA_RD.upd = true;
        IBS.READ(SCCGWA, LNRINTA, LNTINTA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_RECO_NOTFND, LNCRINTA.RC);
            LNCRINTA.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRINTA.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRINTA.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTINTA_RD = new DBParm();
        LNTINTA_RD.TableName = "LNTINTA";
        IBS.REWRITE(SCCGWA, LNRINTA, LNTINTA_RD);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        LNTINTA_RD = new DBParm();
        LNTINTA_RD.TableName = "LNTINTA";
        IBS.DELETE(SCCGWA, LNRINTA, LNTINTA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_RECO_NOTFND, LNCRINTA.RC);
            LNCRINTA.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_PROC() throws IOException,SQLException,Exception {
        LNTINTA_BR.rp = new DBParm();
        LNTINTA_BR.rp.TableName = "LNTINTA";
        IBS.STARTBR(SCCGWA, LNRINTA, LNTINTA_BR);
    }
    public void T000_STARTBR_BY_KEY_PROC() throws IOException,SQLException,Exception {
        LNTINTA_RD = new DBParm();
        LNTINTA_RD.TableName = "LNTINTA";
        LNTINTA_RD.where = "CONTRACT_NO = :LNRINTA.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRINTA.KEY.SUB_CTA_NO "
            + "AND REPY_MTH = :LNRINTA.KEY.REPY_MTH "
            + "AND REPY_TERM = :LNRINTA.KEY.REPY_TERM";
        LNTINTA_RD.fst = true;
        LNTINTA_RD.order = "ADJ_SEQ DESC";
        IBS.READ(SCCGWA, LNRINTA, this, LNTINTA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_RECO_NOTFND, LNCRINTA.RC);
            LNCRINTA.RETURN_INFO = 'E';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRINTA, this, LNTINTA_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_RECO_NOTFND, LNCRINTA.RC);
            LNCRINTA.RETURN_INFO = 'E';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTINTA_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCRINTA.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "LNCRINTA=");
            CEP.TRC(SCCGWA, LNCRINTA);
            CEP.TRC(SCCGWA, "LNRINTA =");
            CEP.TRC(SCCGWA, LNRINTA);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
