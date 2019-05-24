package com.hisun.GD;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class GDZRPLDR {
    DBParm GDTPLDR_RD;
    brParm GDTPLDR_BR = new brParm();
    boolean pgmRtn = false;
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    GDRPLDR GDRPLDR = new GDRPLDR();
    SCCGWA SCCGWA;
    GDCRPLDR GDCRPLDR;
    GDRPLDR GDRPLDR1;
    public void MP(SCCGWA SCCGWA, GDCRPLDR GDCRPLDR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.GDCRPLDR = GDCRPLDR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "GDZRPLDR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GDRPLDR1 = (GDRPLDR) GDCRPLDR.REC_PTR;
        GDCRPLDR.RETURN_INFO = 'F';
        IBS.init(SCCGWA, GDRPLDR);
        IBS.CLONE(SCCGWA, GDRPLDR1, GDRPLDR);
        GDCRPLDR.RC.RC_MMO = "GD";
        GDCRPLDR.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCRPLDR.FUNC);
        if (GDCRPLDR.FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (GDCRPLDR.FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (GDCRPLDR.FUNC == 'R') {
            B030_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (GDCRPLDR.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (GDCRPLDR.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (GDCRPLDR.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (GDCRPLDR.FUNC == 'C') {
            B070_BROCTA_RECORD_PROC();
            if (pgmRtn) return;
        } else if (GDCRPLDR.FUNC == 'E') {
            B080_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + GDCRPLDR.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, GDRPLDR, GDRPLDR1);
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
        if (GDCRPLDR.OPT == 'S') {
            B060_10_START_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (GDCRPLDR.OPT == 'K') {
            B060_20_STBR_BY_KEY_PROC();
            if (pgmRtn) return;
        } else if (GDCRPLDR.OPT == 'Q') {
            B060_50_STBR_BY_RSEQ_PROC();
            if (pgmRtn) return;
        } else if (GDCRPLDR.OPT == 'C') {
            B060_60_STBR_BY_AC_PROC();
            if (pgmRtn) return;
        } else if (GDCRPLDR.OPT == 'L') {
            B060_70_STBR_BY_CTANO_PROC();
            if (pgmRtn) return;
        } else if (GDCRPLDR.OPT == 'R') {
            B060_30_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (GDCRPLDR.OPT == 'E') {
            B060_40_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + GDCRPLDR.OPT + ")";
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
    public void B060_60_STBR_BY_AC_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_AC_PROC();
        if (pgmRtn) return;
    }
    public void B060_70_STBR_BY_CTANO_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_CTANO_PROC();
        if (pgmRtn) return;
    }
    public void B070_BROCTA_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_CTA_REC_PROC();
        if (pgmRtn) return;
    }
    public void B080_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_UPDATE_PROC2();
        if (pgmRtn) return;
    }
    public void T000_READ_REC_PROC() throws IOException,SQLException,Exception {
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.upd = true;
        IBS.READ(SCCGWA, GDRPLDR, GDTPLDR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, GDCMSG_ERROR_MSG.GD_PLDR_REC_NOTFND, GDCRPLDR.RC);
            GDCRPLDR.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CTA_REC_PROC() throws IOException,SQLException,Exception {
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.where = "AC = :GDRPLDR.KEY.AC "
            + "AND AC_SEQ = :GDRPLDR.KEY.AC_SEQ "
            + "AND DEAL_CD = :GDRPLDR.DEAL_CD "
            + "AND BSREF = :GDRPLDR.BSREF "
            + "AND RELAT_STS < > 'R'";
        GDTPLDR_RD.upd = true;
        IBS.READ(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, GDCMSG_ERROR_MSG.GD_PLDR_REC_NOTFND, GDCRPLDR.RC);
            GDCRPLDR.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.errhdl = true;
        IBS.WRITE(SCCGWA, GDRPLDR, GDTPLDR_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, GDCMSG_ERROR_MSG.GD_PLDR_REC_EXIST, GDCRPLDR.RC);
            GDCRPLDR.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        if (GDRPLDR.KEY.RSEQ.trim().length() > 0) {
            GDTPLDR_RD = new DBParm();
            GDTPLDR_RD.TableName = "GDTPLDR";
            GDTPLDR_RD.where = "RSEQ = :GDRPLDR.KEY.RSEQ "
                + "AND AC = :GDRPLDR.KEY.AC "
                + "AND AC_SEQ = :GDRPLDR.KEY.AC_SEQ "
                + "AND RELAT_STS = 'N'";
            GDTPLDR_RD.upd = true;
            IBS.READ(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
        } else {
            GDTPLDR_RD = new DBParm();
            GDTPLDR_RD.TableName = "GDTPLDR";
            GDTPLDR_RD.where = "DEAL_CD = :GDRPLDR.DEAL_CD "
                + "AND BSREF = :GDRPLDR.BSREF "
                + "AND AC = :GDRPLDR.KEY.AC "
                + "AND AC_SEQ = :GDRPLDR.KEY.AC_SEQ "
                + "AND RELAT_STS = 'N'";
            GDTPLDR_RD.upd = true;
            IBS.READ(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, GDCMSG_ERROR_MSG.GD_PLDR_REC_NOTFND, GDCRPLDR.RC);
            GDCRPLDR.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC2() throws IOException,SQLException,Exception {
        if (GDRPLDR.KEY.RSEQ.trim().length() > 0) {
            GDTPLDR_RD = new DBParm();
            GDTPLDR_RD.TableName = "GDTPLDR";
            GDTPLDR_RD.where = "RSEQ = :GDRPLDR.KEY.RSEQ "
                + "AND AC = :GDRPLDR.KEY.AC "
                + "AND AC_SEQ = :GDRPLDR.KEY.AC_SEQ";
            GDTPLDR_RD.upd = true;
            IBS.READ(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
        } else {
            GDTPLDR_RD = new DBParm();
            GDTPLDR_RD.TableName = "GDTPLDR";
            GDTPLDR_RD.where = "DEAL_CD = :GDRPLDR.DEAL_CD "
                + "AND BSREF = :GDRPLDR.BSREF "
                + "AND AC = :GDRPLDR.KEY.AC "
                + "AND AC_SEQ = :GDRPLDR.KEY.AC_SEQ";
            GDTPLDR_RD.upd = true;
            IBS.READ(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, GDCMSG_ERROR_MSG.GD_PLDR_REC_NOTFND, GDCRPLDR.RC);
            GDCRPLDR.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        IBS.REWRITE(SCCGWA, GDRPLDR, GDTPLDR_RD);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        IBS.DELETE(SCCGWA, GDRPLDR, GDTPLDR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, GDCMSG_ERROR_MSG.GD_PLDR_REC_NOTFND, GDCRPLDR.RC);
            GDCRPLDR.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_PROC() throws IOException,SQLException,Exception {
        GDTPLDR_BR.rp = new DBParm();
        GDTPLDR_BR.rp.TableName = "GDTPLDR";
        GDTPLDR_BR.rp.order = "RSEQ";
        IBS.STARTBR(SCCGWA, GDRPLDR, GDTPLDR_BR);
    }
    public void T000_STARTBR_BY_KEY_PROC() throws IOException,SQLException,Exception {
        GDTPLDR_BR.rp = new DBParm();
        GDTPLDR_BR.rp.TableName = "GDTPLDR";
        GDTPLDR_BR.rp.where = "RSEQ LIKE :GDRPLDR.KEY.RSEQ "
            + "AND AC = :GDRPLDR.KEY.AC "
            + "AND AC_SEQ = :GDRPLDR.KEY.AC_SEQ";
        GDTPLDR_BR.rp.order = "RSEQ";
        IBS.STARTBR(SCCGWA, GDRPLDR, this, GDTPLDR_BR);
    }
    public void T000_STARTBR_BY_RSEQ_PROC() throws IOException,SQLException,Exception {
        GDTPLDR_BR.rp = new DBParm();
        GDTPLDR_BR.rp.TableName = "GDTPLDR";
        GDTPLDR_BR.rp.where = "RSEQ = :GDRPLDR.KEY.RSEQ";
        GDTPLDR_BR.rp.order = "AC";
        IBS.STARTBR(SCCGWA, GDRPLDR, this, GDTPLDR_BR);
    }
    public void T000_STARTBR_BY_AC_PROC() throws IOException,SQLException,Exception {
        GDTPLDR_BR.rp = new DBParm();
        GDTPLDR_BR.rp.TableName = "GDTPLDR";
        GDTPLDR_BR.rp.where = "AC = :GDRPLDR.KEY.AC";
        GDTPLDR_BR.rp.order = "RSEQ";
        IBS.STARTBR(SCCGWA, GDRPLDR, this, GDTPLDR_BR);
    }
    public void T000_STARTBR_BY_CTANO_PROC() throws IOException,SQLException,Exception {
        GDTPLDR_BR.rp = new DBParm();
        GDTPLDR_BR.rp.TableName = "GDTPLDR";
        GDTPLDR_BR.rp.where = "DEAL_CD = :GDRPLDR.DEAL_CD "
            + "AND BSREF = :GDRPLDR.BSREF "
            + "AND AC = :GDRPLDR.KEY.AC "
            + "AND AC_SEQ = :GDRPLDR.KEY.AC_SEQ";
        GDTPLDR_BR.rp.order = "DEAL_CD";
        IBS.STARTBR(SCCGWA, GDRPLDR, this, GDTPLDR_BR);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, GDRPLDR, this, GDTPLDR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, GDCMSG_ERROR_MSG.GD_PLDR_REC_NOTFND, GDCRPLDR.RC);
            GDCRPLDR.RETURN_INFO = 'E';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, GDTPLDR_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (GDCRPLDR.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "GDCRPLDR=");
            CEP.TRC(SCCGWA, GDCRPLDR);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
