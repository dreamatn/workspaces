package com.hisun.GD;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class GDZRTRAN {
    DBParm GDTTRAN_RD;
    brParm GDTTRAN_BR = new brParm();
    boolean pgmRtn = false;
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    GDRTRAN GDRTRAN = new GDRTRAN();
    SCCGWA SCCGWA;
    GDCRTRAN GDCRTRAN;
    GDRTRAN GDRTRAN1;
    public void MP(SCCGWA SCCGWA, GDCRTRAN GDCRTRAN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.GDCRTRAN = GDCRTRAN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "GDZRTRAN return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCRTRAN.FUNC);
        GDRTRAN1 = (GDRTRAN) GDCRTRAN.REC_PTR;
        GDCRTRAN.RETURN_INFO = 'F';
        IBS.init(SCCGWA, GDRTRAN);
        IBS.CLONE(SCCGWA, GDRTRAN1, GDRTRAN);
        GDCRTRAN.RC.RC_MMO = "GD";
        GDCRTRAN.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (GDCRTRAN.FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (GDCRTRAN.FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (GDCRTRAN.FUNC == 'R') {
            B030_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (GDCRTRAN.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (GDCRTRAN.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (GDCRTRAN.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + GDCRTRAN.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, GDRTRAN, GDRTRAN1);
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
        if (GDCRTRAN.OPT == 'S') {
            B060_10_START_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (GDCRTRAN.OPT == 'K') {
            B060_20_STBR_BY_KEY_PROC();
            if (pgmRtn) return;
        } else if (GDCRTRAN.OPT == 'R') {
            B060_30_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (GDCRTRAN.OPT == 'E') {
            B060_40_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + GDCRTRAN.OPT + ")";
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
    public void B060_50_STBR_BY_RSEQ_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_DEALCD_PROC();
        if (pgmRtn) return;
    }
    public void T000_READ_REC_PROC() throws IOException,SQLException,Exception {
        GDTTRAN_RD = new DBParm();
        GDTTRAN_RD.TableName = "GDTTRAN";
        IBS.READ(SCCGWA, GDRTRAN, GDTTRAN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, GDCMSG_ERROR_MSG.GD_TRAN_REC_NOTFND, GDCRTRAN.RC);
            GDCRTRAN.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        GDTTRAN_RD = new DBParm();
        GDTTRAN_RD.TableName = "GDTTRAN";
        GDTTRAN_RD.errhdl = true;
        IBS.WRITE(SCCGWA, GDRTRAN, GDTTRAN_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, GDCMSG_ERROR_MSG.GD_TRAN_REC_EXIST, GDCRTRAN.RC);
            GDCRTRAN.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        GDTTRAN_RD = new DBParm();
        GDTTRAN_RD.TableName = "GDTTRAN";
        GDTTRAN_RD.upd = true;
        IBS.READ(SCCGWA, GDRTRAN, GDTTRAN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, GDCMSG_ERROR_MSG.GD_TRAN_REC_NOTFND, GDCRTRAN.RC);
            GDCRTRAN.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        GDTTRAN_RD = new DBParm();
        GDTTRAN_RD.TableName = "GDTTRAN";
        IBS.REWRITE(SCCGWA, GDRTRAN, GDTTRAN_RD);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        GDTTRAN_RD = new DBParm();
        GDTTRAN_RD.TableName = "GDTTRAN";
        IBS.DELETE(SCCGWA, GDRTRAN, GDTTRAN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, GDCMSG_ERROR_MSG.GD_TRAN_REC_NOTFND, GDCRTRAN.RC);
            GDCRTRAN.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_PROC() throws IOException,SQLException,Exception {
        GDTTRAN_BR.rp = new DBParm();
        GDTTRAN_BR.rp.TableName = "GDTTRAN";
        GDTTRAN_BR.rp.order = "TR_DATE";
        IBS.STARTBR(SCCGWA, GDRTRAN, GDTTRAN_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, GDCMSG_ERROR_MSG.GD_TRAN_REC_NOTFND, GDCRTRAN.RC);
            GDCRTRAN.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BY_KEY_PROC() throws IOException,SQLException,Exception {
        GDTTRAN_BR.rp = new DBParm();
        GDTTRAN_BR.rp.TableName = "GDTTRAN";
        GDTTRAN_BR.rp.where = "DEAL_CD = :GDRTRAN.KEY.DEAL_CD "
            + "AND BSREF = :GDRTRAN.KEY.BSREF "
            + "AND TR_DATE = :GDRTRAN.KEY.TR_DATE";
        GDTTRAN_BR.rp.order = "TS";
        IBS.STARTBR(SCCGWA, GDRTRAN, this, GDTTRAN_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, GDCMSG_ERROR_MSG.GD_TRAN_REC_NOTFND, GDCRTRAN.RC);
            GDCRTRAN.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BY_DEALCD_PROC() throws IOException,SQLException,Exception {
        GDTTRAN_BR.rp = new DBParm();
        GDTTRAN_BR.rp.TableName = "GDTTRAN";
        GDTTRAN_BR.rp.where = "DEAL_CD = :GDRTRAN.KEY.DEAL_CD";
        GDTTRAN_BR.rp.order = "TS";
        IBS.STARTBR(SCCGWA, GDRTRAN, this, GDTTRAN_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, GDCMSG_ERROR_MSG.GD_TRAN_REC_NOTFND, GDCRTRAN.RC);
            GDCRTRAN.RETURN_INFO = 'N';
        }
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, GDRTRAN, this, GDTTRAN_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, GDCMSG_ERROR_MSG.GD_TRAN_REC_NOTFND, GDCRTRAN.RC);
            GDCRTRAN.RETURN_INFO = 'E';
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, GDTTRAN_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (GDCRTRAN.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "GDCRTRAN=");
            CEP.TRC(SCCGWA, GDCRTRAN);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
