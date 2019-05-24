package com.hisun.GD;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class GDZRHIS {
    DBParm GDTHIS_RD;
    brParm GDTHIS_BR = new brParm();
    boolean pgmRtn = false;
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    GDRHIS GDRHIS = new GDRHIS();
    SCCGWA SCCGWA;
    GDCRHIS GDCRHIS;
    GDRHIS GDRHIS1;
    public void MP(SCCGWA SCCGWA, GDCRHIS GDCRHIS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.GDCRHIS = GDCRHIS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "GDZRHIS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCRHIS.FUNC);
        GDRHIS1 = (GDRHIS) GDCRHIS.REC_PTR;
        GDCRHIS.RETURN_INFO = 'F';
        IBS.init(SCCGWA, GDRHIS);
        IBS.CLONE(SCCGWA, GDRHIS1, GDRHIS);
        GDCRHIS.RC.RC_MMO = "GD";
        GDCRHIS.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (GDCRHIS.FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (GDCRHIS.FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (GDCRHIS.FUNC == 'R') {
            B030_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (GDCRHIS.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (GDCRHIS.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (GDCRHIS.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + GDCRHIS.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, GDRHIS, GDRHIS1);
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
        if (GDCRHIS.OPT == 'S') {
            B060_10_START_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (GDCRHIS.OPT == 'Q') {
            B060_50_STBR_BY_RSEQ_PROC();
            if (pgmRtn) return;
        } else if (GDCRHIS.OPT == 'K') {
            B060_20_STBR_BY_KEY_PROC();
            if (pgmRtn) return;
        } else if (GDCRHIS.OPT == 'R') {
            B060_30_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (GDCRHIS.OPT == 'E') {
            B060_40_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (GDCRHIS.OPT == 'C') {
            B060_60_STBR_BY_RSEQAC();
            if (pgmRtn) return;
        } else if (GDCRHIS.OPT == 'G') {
            B060_70_STBR_BY_SEQAC();
            if (pgmRtn) return;
        } else if (GDCRHIS.OPT == 'F') {
            B060_80_STBR_BY_SEQFUNC_FIRST();
            if (pgmRtn) return;
        } else if (GDCRHIS.OPT == 'D') {
            B060_90_STBR_BY_DTJRN();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + GDCRHIS.OPT + ")";
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
        T000_STARTBR_BY_RSEQ_PROC();
        if (pgmRtn) return;
    }
    public void B060_60_STBR_BY_RSEQAC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_RSEQAC();
        if (pgmRtn) return;
    }
    public void B060_70_STBR_BY_SEQAC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_SEQAC();
        if (pgmRtn) return;
    }
    public void B060_80_STBR_BY_SEQFUNC_FIRST() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_SEQFUNC_FIRST();
        if (pgmRtn) return;
    }
    public void B060_90_STBR_BY_DTJRN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDRHIS.KEY.TR_DATE);
        CEP.TRC(SCCGWA, GDRHIS.KEY.JRNNO);
        T000_STARTBR_BY_DTJRN();
        if (pgmRtn) return;
    }
    public void T000_READ_REC_PROC() throws IOException,SQLException,Exception {
        GDTHIS_RD = new DBParm();
        GDTHIS_RD.TableName = "GDTHIS";
        IBS.READ(SCCGWA, GDRHIS, GDTHIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, GDCMSG_ERROR_MSG.GD_HIS_REC_NOTFND, GDCRHIS.RC);
            GDCRHIS.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        GDTHIS_RD = new DBParm();
        GDTHIS_RD.TableName = "GDTHIS";
        GDTHIS_RD.errhdl = true;
        IBS.WRITE(SCCGWA, GDRHIS, GDTHIS_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, GDCMSG_ERROR_MSG.GD_HIS_REC_EXIST, GDCRHIS.RC);
            GDCRHIS.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        GDTHIS_RD = new DBParm();
        GDTHIS_RD.TableName = "GDTHIS";
        GDTHIS_RD.upd = true;
        IBS.READ(SCCGWA, GDRHIS, GDTHIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, GDCMSG_ERROR_MSG.GD_HIS_REC_NOTFND, GDCRHIS.RC);
            GDCRHIS.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        GDTHIS_RD = new DBParm();
        GDTHIS_RD.TableName = "GDTHIS";
        IBS.REWRITE(SCCGWA, GDRHIS, GDTHIS_RD);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        GDTHIS_RD = new DBParm();
        GDTHIS_RD.TableName = "GDTHIS";
        IBS.DELETE(SCCGWA, GDRHIS, GDTHIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, GDCMSG_ERROR_MSG.GD_HIS_REC_NOTFND, GDCRHIS.RC);
            GDCRHIS.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_PROC() throws IOException,SQLException,Exception {
        GDTHIS_BR.rp = new DBParm();
        GDTHIS_BR.rp.TableName = "GDTHIS";
        GDTHIS_BR.rp.order = "JRNNO";
        IBS.STARTBR(SCCGWA, GDRHIS, GDTHIS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, GDCMSG_ERROR_MSG.GD_HIS_REC_NOTFND, GDCRHIS.RC);
            GDCRHIS.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BY_KEY_PROC() throws IOException,SQLException,Exception {
        GDTHIS_BR.rp = new DBParm();
        GDTHIS_BR.rp.TableName = "GDTHIS";
        GDTHIS_BR.rp.where = "JRNNO = :GDRHIS.KEY.JRNNO "
            + "AND TR_DATE = :GDRHIS.KEY.TR_DATE "
            + "AND SEQ = :GDRHIS.KEY.SEQ";
        GDTHIS_BR.rp.order = "JRNNO";
        IBS.STARTBR(SCCGWA, GDRHIS, this, GDTHIS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, GDCMSG_ERROR_MSG.GD_HIS_REC_NOTFND, GDCRHIS.RC);
            GDCRHIS.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BY_RSEQ_PROC() throws IOException,SQLException,Exception {
        GDTHIS_BR.rp = new DBParm();
        GDTHIS_BR.rp.TableName = "GDTHIS";
        GDTHIS_BR.rp.where = "RSEQ = :GDRHIS.RSEQ";
        GDTHIS_BR.rp.order = "RSEQ";
        IBS.STARTBR(SCCGWA, GDRHIS, this, GDTHIS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, GDCMSG_ERROR_MSG.GD_HIS_REC_NOTFND, GDCRHIS.RC);
            GDCRHIS.RETURN_INFO = 'N';
        }
    }
    public void T000_STARTBR_BY_RSEQAC() throws IOException,SQLException,Exception {
        GDTHIS_BR.rp = new DBParm();
        GDTHIS_BR.rp.TableName = "GDTHIS";
        GDTHIS_BR.rp.where = "RSEQ = :GDRHIS.RSEQ "
            + "AND AC = :GDRHIS.AC";
        GDTHIS_BR.rp.order = "TR_DATE";
        IBS.STARTBR(SCCGWA, GDRHIS, this, GDTHIS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, GDCMSG_ERROR_MSG.GD_HIS_REC_NOTFND, GDCRHIS.RC);
            GDCRHIS.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BY_SEQAC() throws IOException,SQLException,Exception {
        GDTHIS_BR.rp = new DBParm();
        GDTHIS_BR.rp.TableName = "GDTHIS";
        GDTHIS_BR.rp.where = "SEQ = :GDRHIS.KEY.SEQ "
            + "AND AC = :GDRHIS.AC";
        GDTHIS_BR.rp.order = "TR_DATE";
        IBS.STARTBR(SCCGWA, GDRHIS, this, GDTHIS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, GDCMSG_ERROR_MSG.GD_HIS_REC_NOTFND, GDCRHIS.RC);
            GDCRHIS.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BY_SEQFUNC_FIRST() throws IOException,SQLException,Exception {
        GDTHIS_RD = new DBParm();
        GDTHIS_RD.TableName = "GDTHIS";
        GDTHIS_RD.where = "RSEQ = :GDRHIS.RSEQ "
            + "AND CAN_FLG = :GDRHIS.CAN_FLG "
            + "AND FUNC = :GDRHIS.FUNC";
        GDTHIS_RD.fst = true;
        GDTHIS_RD.order = "TR_DATE,JRNNO";
        IBS.READ(SCCGWA, GDRHIS, this, GDTHIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, GDCMSG_ERROR_MSG.GD_HIS_REC_NOTFND, GDCRHIS.RC);
            GDCRHIS.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BY_DTJRN() throws IOException,SQLException,Exception {
        GDTHIS_RD = new DBParm();
        GDTHIS_RD.TableName = "GDTHIS";
        GDTHIS_RD.where = "TR_DATE = :GDRHIS.KEY.TR_DATE "
            + "AND JRNNO = :GDRHIS.KEY.JRNNO";
        GDTHIS_RD.fst = true;
        GDTHIS_RD.order = "SEQ DESC";
        IBS.READ(SCCGWA, GDRHIS, this, GDTHIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, GDCMSG_ERROR_MSG.GD_HIS_REC_NOTFND, GDCRHIS.RC);
            GDCRHIS.RETURN_INFO = 'N';
        }
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, GDRHIS, this, GDTHIS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, GDCMSG_ERROR_MSG.GD_HIS_REC_NOTFND, GDCRHIS.RC);
            GDCRHIS.RETURN_INFO = 'E';
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, GDTHIS_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (GDCRHIS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "GDCRHIS=");
            CEP.TRC(SCCGWA, GDCRHIS);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
