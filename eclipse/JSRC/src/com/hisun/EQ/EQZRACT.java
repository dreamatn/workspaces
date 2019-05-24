package com.hisun.EQ;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class EQZRACT {
    DBParm EQTACT_RD;
    brParm EQTACT_BR = new brParm();
    boolean pgmRtn = false;
    EQCMSG_ERROR_MSG EQCMSG_ERROR_MSG = new EQCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    EQRACT EQRACT = new EQRACT();
    SCCGWA SCCGWA;
    EQCRACT EQCRACT;
    EQRACT EQRLACT;
    public void MP(SCCGWA SCCGWA, EQCRACT EQCRACT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.EQCRACT = EQCRACT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "EQZRACT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        EQRLACT = (EQRACT) EQCRACT.REC_PTR;
        EQCRACT.RETURN_INFO = 'F';
        IBS.init(SCCGWA, EQRACT);
        CEP.TRC(SCCGWA, "1111111");
        IBS.CLONE(SCCGWA, EQRLACT, EQRACT);
        EQCRACT.RC.RC_MMO = "EQ";
        EQCRACT.RC.RC_CODE = 0;
        CEP.TRC(SCCGWA, EQRACT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (EQCRACT.FUNC == 'Q') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (EQCRACT.FUNC == 'C') {
            B020_READ_CINO_PROC();
            if (pgmRtn) return;
        } else if (EQCRACT.FUNC == 'E') {
            B030_READ_EQACT_PROC();
            if (pgmRtn) return;
        } else if (EQCRACT.FUNC == 'I') {
            B040_READ_EQCINO_PROC();
            if (pgmRtn) return;
        } else if (EQCRACT.FUNC == 'V') {
            B050_READ_DIVAC_PROC();
            if (pgmRtn) return;
        } else if (EQCRACT.FUNC == 'A') {
            B060_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (EQCRACT.FUNC == 'R') {
            B070_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (EQCRACT.FUNC == 'U') {
            B080_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (EQCRACT.FUNC == 'D') {
            B090_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (EQCRACT.FUNC == 'B') {
            B100_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + EQCRACT.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, EQRACT, EQRLACT);
    }
    public void B010_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_REC_PROC();
        if (pgmRtn) return;
    }
    public void B020_READ_CINO_PROC() throws IOException,SQLException,Exception {
        T000_READ_BY_CINO_PROC();
        if (pgmRtn) return;
    }
    public void B030_READ_EQACT_PROC() throws IOException,SQLException,Exception {
        T000_READ_BY_EQACT_PROC();
        if (pgmRtn) return;
    }
    public void B040_READ_EQCINO_PROC() throws IOException,SQLException,Exception {
        T000_READ_BY_EQCINO_PROC();
        if (pgmRtn) return;
    }
    public void B050_READ_DIVAC_PROC() throws IOException,SQLException,Exception {
        T000_READ_BY_DIVAC_PROC();
        if (pgmRtn) return;
    }
    public void B060_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_REC_PROC();
        if (pgmRtn) return;
    }
    public void B070_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_UPDATE_PROC();
        if (pgmRtn) return;
    }
    public void B080_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_REC_PROC();
        if (pgmRtn) return;
    }
    public void B090_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_REC_PROC();
        if (pgmRtn) return;
    }
    public void B100_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (EQCRACT.OPT == 'S') {
            B100_10_START_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (EQCRACT.OPT == 'U') {
            B100_10_START_BROWSE_UPD_PROC();
            if (pgmRtn) return;
        } else if (EQCRACT.OPT == 'A') {
            B100_20_STBR_BY_KEY_PROC();
            if (pgmRtn) return;
        } else if (EQCRACT.OPT == 'T') {
            B100_20_STBR_BY_TYP_PROC();
            if (pgmRtn) return;
        } else if (EQCRACT.OPT == 'C') {
            B100_20_STBR_BY_STS_PROC();
            if (pgmRtn) return;
        } else if (EQCRACT.OPT == 'B') {
            B100_20_STBR_BY_TYPSTS_PROC();
            if (pgmRtn) return;
        } else if (EQCRACT.OPT == 'R') {
            B100_30_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (EQCRACT.OPT == 'E') {
            B100_50_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + EQCRACT.OPT + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B100_10_START_BROWSE_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_PROC();
        if (pgmRtn) return;
    }
    public void B100_10_START_BROWSE_UPD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_UPD_PROC();
        if (pgmRtn) return;
    }
    public void B100_20_STBR_BY_KEY_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_KEY_PROC();
        if (pgmRtn) return;
    }
    public void B100_20_STBR_BY_TYP_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_TYP_PROC();
        if (pgmRtn) return;
    }
    public void B100_20_STBR_BY_STS_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_STS_PROC();
        if (pgmRtn) return;
    }
    public void B100_20_STBR_BY_TYPSTS_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_TYPSTS_PROC();
        if (pgmRtn) return;
    }
    public void B100_30_READ_NEXT_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_PROC();
        if (pgmRtn) return;
    }
    public void B100_50_END_BROWSE_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_PROC();
        if (pgmRtn) return;
    }
    public void T000_READ_REC_PROC() throws IOException,SQLException,Exception {
        EQTACT_RD = new DBParm();
        EQTACT_RD.TableName = "EQTACT";
        EQTACT_RD.where = "EQ_BKID = :EQRACT.KEY.EQ_BKID "
            + "AND EQ_AC = :EQRACT.KEY.EQ_AC";
        IBS.READ(SCCGWA, EQRACT, this, EQTACT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, EQCMSG_ERROR_MSG.EQ_EQAC_NOTFND, EQCRACT.RC);
            EQCRACT.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BY_CINO_PROC() throws IOException,SQLException,Exception {
        EQTACT_RD = new DBParm();
        EQTACT_RD.TableName = "EQTACT";
        EQTACT_RD.where = "EQ_BKID = :EQRACT.KEY.EQ_BKID "
            + "AND CI_NO = :EQRACT.CI_NO "
            + "AND AC_STS = 'N'";
        IBS.READ(SCCGWA, EQRACT, this, EQTACT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, EQCMSG_ERROR_MSG.EQ_CINO_NOT_EXIST, EQCRACT.RC);
            EQCRACT.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BY_EQACT_PROC() throws IOException,SQLException,Exception {
        EQTACT_RD = new DBParm();
        EQTACT_RD.TableName = "EQTACT";
        EQTACT_RD.where = "EQ_BKID = :EQRACT.KEY.EQ_BKID "
            + "AND EQ_ACT = :EQRACT.EQ_ACT "
            + "AND AC_STS = 'N'";
        IBS.READ(SCCGWA, EQRACT, this, EQTACT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, EQCMSG_ERROR_MSG.EQ_EQACT_NOTFND, EQCRACT.RC);
            EQCRACT.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BY_EQCINO_PROC() throws IOException,SQLException,Exception {
        EQTACT_RD = new DBParm();
        EQTACT_RD.TableName = "EQTACT";
        EQTACT_RD.where = "EQ_BKID = :EQRACT.KEY.EQ_BKID "
            + "AND EQ_CINO = :EQRACT.EQ_CINO "
            + "AND AC_STS = 'N'";
        IBS.READ(SCCGWA, EQRACT, this, EQTACT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, EQCMSG_ERROR_MSG.EQ_EQCINO_NOTFND, EQCRACT.RC);
            EQCRACT.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BY_DIVAC_PROC() throws IOException,SQLException,Exception {
        EQTACT_RD = new DBParm();
        EQTACT_RD.TableName = "EQTACT";
        EQTACT_RD.where = "EQ_BKID = :EQRACT.KEY.EQ_BKID "
            + "AND DIV_AC = :EQRACT.DIV_AC "
            + "AND AC_STS = 'N'";
        IBS.READ(SCCGWA, EQRACT, this, EQTACT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, EQCMSG_ERROR_MSG.EQ_EQAC_NOTFND, EQCRACT.RC);
            EQCRACT.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        EQTACT_RD = new DBParm();
        EQTACT_RD.TableName = "EQTACT";
        EQTACT_RD.errhdl = true;
        IBS.WRITE(SCCGWA, EQRACT, EQTACT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, EQCMSG_ERROR_MSG.EQ_AC_EXIST, EQCRACT.RC);
            EQCRACT.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        EQTACT_RD = new DBParm();
        EQTACT_RD.TableName = "EQTACT";
        EQTACT_RD.where = "EQ_BKID = :EQRACT.KEY.EQ_BKID "
            + "AND EQ_AC = :EQRACT.KEY.EQ_AC";
        EQTACT_RD.upd = true;
        IBS.READ(SCCGWA, EQRACT, this, EQTACT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, EQCMSG_ERROR_MSG.EQ_EQAC_NOTFND, EQCRACT.RC);
            EQCRACT.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        EQRACT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRACT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQTACT_RD = new DBParm();
        EQTACT_RD.TableName = "EQTACT";
        IBS.REWRITE(SCCGWA, EQRACT, EQTACT_RD);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        EQTACT_RD = new DBParm();
        EQTACT_RD.TableName = "EQTACT";
        IBS.DELETE(SCCGWA, EQRACT, EQTACT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, EQCMSG_ERROR_MSG.EQ_EQAC_NOTFND, EQCRACT.RC);
            EQCRACT.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_PROC() throws IOException,SQLException,Exception {
        EQTACT_BR.rp = new DBParm();
        EQTACT_BR.rp.TableName = "EQTACT";
        EQTACT_BR.rp.order = "EQ_BKID,EQ_AC";
        IBS.STARTBR(SCCGWA, EQRACT, EQTACT_BR);
    }
    public void T000_STARTBR_UPD_PROC() throws IOException,SQLException,Exception {
        EQTACT_BR.rp = new DBParm();
        EQTACT_BR.rp.TableName = "EQTACT";
        EQTACT_BR.rp.upd = true;
        EQTACT_BR.rp.order = "EQ_BKID,EQ_AC";
        IBS.STARTBR(SCCGWA, EQRACT, EQTACT_BR);
    }
    public void T000_STARTBR_BY_KEY_PROC() throws IOException,SQLException,Exception {
        EQTACT_BR.rp = new DBParm();
        EQTACT_BR.rp.TableName = "EQTACT";
        EQTACT_BR.rp.where = "EQ_BKID = :EQRACT.KEY.EQ_BKID "
            + "AND EQ_AC = :EQRACT.KEY.EQ_AC";
        EQTACT_BR.rp.order = "EQ_BKID,EQ_AC";
        IBS.STARTBR(SCCGWA, EQRACT, this, EQTACT_BR);
    }
    public void T000_STARTBR_TYP_PROC() throws IOException,SQLException,Exception {
        EQTACT_BR.rp = new DBParm();
        EQTACT_BR.rp.TableName = "EQTACT";
        EQTACT_BR.rp.where = "EQ_BKID = :EQRACT.KEY.EQ_BKID "
            + "AND EQ_TYP = :EQRACT.EQ_TYP";
        EQTACT_BR.rp.order = "EQ_AC";
        IBS.STARTBR(SCCGWA, EQRACT, this, EQTACT_BR);
    }
    public void T000_STARTBR_STS_PROC() throws IOException,SQLException,Exception {
        EQTACT_BR.rp = new DBParm();
        EQTACT_BR.rp.TableName = "EQTACT";
        EQTACT_BR.rp.where = "EQ_BKID = :EQRACT.KEY.EQ_BKID "
            + "AND AC_STS = :EQRACT.AC_STS";
        EQTACT_BR.rp.order = "EQ_AC";
        IBS.STARTBR(SCCGWA, EQRACT, this, EQTACT_BR);
    }
    public void T000_STARTBR_TYPSTS_PROC() throws IOException,SQLException,Exception {
        EQTACT_BR.rp = new DBParm();
        EQTACT_BR.rp.TableName = "EQTACT";
        EQTACT_BR.rp.where = "EQ_BKID = :EQRACT.KEY.EQ_BKID "
            + "AND EQ_TYP = :EQRACT.EQ_TYP "
            + "AND AC_STS = :EQRACT.AC_STS";
        IBS.STARTBR(SCCGWA, EQRACT, this, EQTACT_BR);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, EQRACT, this, EQTACT_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "ZZZZZZZZZZZ");
            IBS.CPY2CLS(SCCGWA, EQCMSG_ERROR_MSG.EQ_EQAC_NOTFND, EQCRACT.RC);
            EQCRACT.RETURN_INFO = 'E';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, EQTACT_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
