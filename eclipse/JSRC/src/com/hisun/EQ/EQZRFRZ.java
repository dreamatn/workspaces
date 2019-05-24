package com.hisun.EQ;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class EQZRFRZ {
    DBParm EQTFRZ_RD;
    brParm EQTFRZ_BR = new brParm();
    boolean pgmRtn = false;
    EQCMSG_ERROR_MSG EQCMSG_ERROR_MSG = new EQCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    EQRFRZ EQRFRZ = new EQRFRZ();
    SCCGWA SCCGWA;
    EQCRFRZ EQCRFRZ;
    EQRFRZ EQRLFRZ;
    public void MP(SCCGWA SCCGWA, EQCRFRZ EQCRFRZ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.EQCRFRZ = EQCRFRZ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "EQZRFRZ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        EQRLFRZ = (EQRFRZ) EQCRFRZ.REC_PTR;
        EQCRFRZ.RETURN_INFO = 'F';
        IBS.init(SCCGWA, EQRFRZ);
        CEP.TRC(SCCGWA, "1111111");
        IBS.CLONE(SCCGWA, EQRLFRZ, EQRFRZ);
        EQCRFRZ.RC.RC_MMO = "EQ";
        EQCRFRZ.RC.RC_CODE = 0;
        CEP.TRC(SCCGWA, EQRFRZ);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (EQCRFRZ.FUNC == 'Q') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (EQCRFRZ.FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (EQCRFRZ.FUNC == 'C') {
            B030_READ_RECORD_PROC();
            if (pgmRtn) return;
        } else if (EQCRFRZ.FUNC == 'R') {
            B040_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (EQCRFRZ.FUNC == 'U') {
            B050_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (EQCRFRZ.FUNC == 'D') {
            B060_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (EQCRFRZ.FUNC == 'B') {
            B070_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + EQCRFRZ.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, EQRFRZ, EQRLFRZ);
    }
    public void B010_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_REC_PROC();
        if (pgmRtn) return;
    }
    public void B020_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_REC_PROC();
        if (pgmRtn) return;
    }
    public void B030_READ_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_REC_KEY_PROC();
        if (pgmRtn) return;
    }
    public void B040_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_UPDATE_PROC();
        if (pgmRtn) return;
    }
    public void B050_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_REC_PROC();
        if (pgmRtn) return;
    }
    public void B060_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_REC_PROC();
        if (pgmRtn) return;
    }
    public void B070_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (EQCRFRZ.OPT == 'S') {
            B070_10_START_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (EQCRFRZ.OPT == 'U') {
            B070_10_START_BROWSE_UPD_PROC();
            if (pgmRtn) return;
        } else if (EQCRFRZ.OPT == 'A') {
            B070_20_STBR_BY_KEY_PROC();
            if (pgmRtn) return;
        } else if (EQCRFRZ.OPT == 'F') {
            B070_20_STBR_BY_EQAC_PROC();
            if (pgmRtn) return;
        } else if (EQCRFRZ.OPT == 'R') {
            B070_30_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (EQCRFRZ.OPT == 'E') {
            B070_50_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + EQCRFRZ.OPT + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B070_10_START_BROWSE_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_PROC();
        if (pgmRtn) return;
    }
    public void B070_10_START_BROWSE_UPD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_UPD_PROC();
        if (pgmRtn) return;
    }
    public void B070_20_STBR_BY_KEY_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_KEY_PROC();
        if (pgmRtn) return;
    }
    public void B070_20_STBR_BY_EQAC_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_EQAC_PROC();
        if (pgmRtn) return;
    }
    public void B070_30_READ_NEXT_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_PROC();
        if (pgmRtn) return;
    }
    public void B070_50_END_BROWSE_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_PROC();
        if (pgmRtn) return;
    }
    public void T000_READ_REC_PROC() throws IOException,SQLException,Exception {
        EQTFRZ_RD = new DBParm();
        EQTFRZ_RD.TableName = "EQTFRZ";
        EQTFRZ_RD.where = "EQ_BKID = :EQRFRZ.KEY.EQ_BKID";
        IBS.READ(SCCGWA, EQRFRZ, this, EQTFRZ_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, EQCMSG_ERROR_MSG.EQ_FRZ_NOT_FOUND, EQCRFRZ.RC);
            EQCRFRZ.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        EQRFRZ.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRFRZ.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQRFRZ.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRFRZ.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQTFRZ_RD = new DBParm();
        EQTFRZ_RD.TableName = "EQTFRZ";
        EQTFRZ_RD.errhdl = true;
        IBS.WRITE(SCCGWA, EQRFRZ, EQTFRZ_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, EQCMSG_ERROR_MSG.EQ_FRZ_EXIST, EQCRFRZ.RC);
            EQCRFRZ.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_REC_KEY_PROC() throws IOException,SQLException,Exception {
        EQTFRZ_RD = new DBParm();
        EQTFRZ_RD.TableName = "EQTFRZ";
        EQTFRZ_RD.where = "EQ_BKID = :EQRFRZ.KEY.EQ_BKID "
            + "AND FRZ_NO = :EQRFRZ.FRZ_NO";
        IBS.READ(SCCGWA, EQRFRZ, this, EQTFRZ_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, EQCMSG_ERROR_MSG.EQ_FRZ_NOT_FOUND, EQCRFRZ.RC);
            EQCRFRZ.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        EQTFRZ_RD = new DBParm();
        EQTFRZ_RD.TableName = "EQTFRZ";
        EQTFRZ_RD.upd = true;
        IBS.READ(SCCGWA, EQRFRZ, EQTFRZ_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, EQCMSG_ERROR_MSG.EQ_FRZ_NOT_FOUND, EQCRFRZ.RC);
            EQCRFRZ.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        EQRFRZ.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRFRZ.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQTFRZ_RD = new DBParm();
        EQTFRZ_RD.TableName = "EQTFRZ";
        IBS.REWRITE(SCCGWA, EQRFRZ, EQTFRZ_RD);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        EQTFRZ_RD = new DBParm();
        EQTFRZ_RD.TableName = "EQTFRZ";
        IBS.DELETE(SCCGWA, EQRFRZ, EQTFRZ_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, EQCMSG_ERROR_MSG.EQ_FRZ_NOT_FOUND, EQCRFRZ.RC);
            EQCRFRZ.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_PROC() throws IOException,SQLException,Exception {
        EQTFRZ_BR.rp = new DBParm();
        EQTFRZ_BR.rp.TableName = "EQTFRZ";
        EQTFRZ_BR.rp.where = "EQ_BKID = :EQRFRZ.KEY.EQ_BKID";
        EQTFRZ_BR.rp.order = "EQ_BKID,EQ_AC,FRZ_NO";
        IBS.STARTBR(SCCGWA, EQRFRZ, this, EQTFRZ_BR);
    }
    public void T000_STARTBR_UPD_PROC() throws IOException,SQLException,Exception {
        EQTFRZ_BR.rp = new DBParm();
        EQTFRZ_BR.rp.TableName = "EQTFRZ";
        EQTFRZ_BR.rp.where = "EQ_BKID = :EQRFRZ.KEY.EQ_BKID";
        EQTFRZ_BR.rp.upd = true;
        EQTFRZ_BR.rp.order = "EQ_BKID,EQ_AC,FRZ_NO";
        IBS.STARTBR(SCCGWA, EQRFRZ, this, EQTFRZ_BR);
    }
    public void T000_STARTBR_BY_KEY_PROC() throws IOException,SQLException,Exception {
        EQTFRZ_BR.rp = new DBParm();
        EQTFRZ_BR.rp.TableName = "EQTFRZ";
        EQTFRZ_BR.rp.where = "EQ_BKID = :EQRFRZ.KEY.EQ_BKID "
            + "AND FRZ_NO = :EQRFRZ.FRZ_NO";
        EQTFRZ_BR.rp.order = "EQ_BKID,EQ_AC,FRZ_NO";
        IBS.STARTBR(SCCGWA, EQRFRZ, this, EQTFRZ_BR);
    }
    public void T000_STARTBR_EQAC_PROC() throws IOException,SQLException,Exception {
        EQTFRZ_BR.rp = new DBParm();
        EQTFRZ_BR.rp.TableName = "EQTFRZ";
        EQTFRZ_BR.rp.where = "EQ_BKID = :EQRFRZ.KEY.EQ_BKID "
            + "AND EQ_AC = :EQRFRZ.KEY.EQ_AC";
        IBS.STARTBR(SCCGWA, EQRFRZ, this, EQTFRZ_BR);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, EQRFRZ, this, EQTFRZ_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "ZZZZZZZZZZZ");
            IBS.CPY2CLS(SCCGWA, EQCMSG_ERROR_MSG.EQ_FRZ_NOT_FOUND, EQCRFRZ.RC);
            EQCRFRZ.RETURN_INFO = 'E';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, EQTFRZ_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
