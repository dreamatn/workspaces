package com.hisun.EQ;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class EQZRDVC {
    DBParm EQTDVC_RD;
    brParm EQTDVC_BR = new brParm();
    boolean pgmRtn = false;
    EQCMSG_ERROR_MSG EQCMSG_ERROR_MSG = new EQCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    EQRDVC EQRDVC = new EQRDVC();
    SCCGWA SCCGWA;
    EQCRDVC EQCRDVC;
    EQRDVC EQRLDVC;
    public void MP(SCCGWA SCCGWA, EQCRDVC EQCRDVC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.EQCRDVC = EQCRDVC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "EQZRDVC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        EQRLDVC = (EQRDVC) EQCRDVC.REC_PTR;
        EQCRDVC.RETURN_INFO = 'F';
        IBS.init(SCCGWA, EQRDVC);
        CEP.TRC(SCCGWA, "1111111");
        IBS.CLONE(SCCGWA, EQRLDVC, EQRDVC);
        EQCRDVC.RC.RC_MMO = "EQ";
        EQCRDVC.RC.RC_CODE = 0;
        CEP.TRC(SCCGWA, EQRDVC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (EQCRDVC.FUNC == 'Q') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (EQCRDVC.FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (EQCRDVC.FUNC == 'C') {
            B030_READ_RECORD_PROC();
            if (pgmRtn) return;
        } else if (EQCRDVC.FUNC == 'R') {
            B040_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (EQCRDVC.FUNC == 'U') {
            B050_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (EQCRDVC.FUNC == 'D') {
            B060_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (EQCRDVC.FUNC == 'B') {
            B070_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + EQCRDVC.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, EQRDVC, EQRLDVC);
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
        if (EQCRDVC.OPT == 'S') {
            B070_10_START_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (EQCRDVC.OPT == 'U') {
            B070_10_START_BROWSE_UPD_PROC();
            if (pgmRtn) return;
        } else if (EQCRDVC.OPT == 'A') {
            B070_20_STBR_BY_KEY_PROC();
            if (pgmRtn) return;
        } else if (EQCRDVC.OPT == 'F') {
            B070_20_STBR_BY_FLG_PROC();
            if (pgmRtn) return;
        } else if (EQCRDVC.OPT == 'D') {
            B070_20_STBR_BY_DT_PROC();
            if (pgmRtn) return;
        } else if (EQCRDVC.OPT == 'T') {
            B070_20_STBR_BY_STS_PROC();
            if (pgmRtn) return;
        } else if (EQCRDVC.OPT == 'B') {
            B070_20_STBR_BY_DT_FLG_PROC();
            if (pgmRtn) return;
        } else if (EQCRDVC.OPT == 'C') {
            B070_20_STBR_BY_DT_STS_PROC();
            if (pgmRtn) return;
        } else if (EQCRDVC.OPT == 'G') {
            B070_20_STBR_BY_STS_FLG_PROC();
            if (pgmRtn) return;
        } else if (EQCRDVC.OPT == 'L') {
            B070_20_STBR_BY_DT_FLG_STS_PROC();
            if (pgmRtn) return;
        } else if (EQCRDVC.OPT == 'R') {
            B070_30_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (EQCRDVC.OPT == 'E') {
            B070_50_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + EQCRDVC.OPT + ")";
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
    public void B070_20_STBR_BY_FLG_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_FLG_PROC();
        if (pgmRtn) return;
    }
    public void B070_20_STBR_BY_DT_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_DT_PROC();
        if (pgmRtn) return;
    }
    public void B070_20_STBR_BY_STS_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_STS_PROC();
        if (pgmRtn) return;
    }
    public void B070_20_STBR_BY_DT_FLG_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_DT_FLG_PROC();
        if (pgmRtn) return;
    }
    public void B070_20_STBR_BY_DT_STS_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_DT_STS_PROC();
        if (pgmRtn) return;
    }
    public void B070_20_STBR_BY_STS_FLG_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_STS_FLG_PROC();
        if (pgmRtn) return;
    }
    public void B070_20_STBR_BY_DT_FLG_STS_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_DT_FLG_STS_PROC();
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
        EQTDVC_RD = new DBParm();
        EQTDVC_RD.TableName = "EQTDVC";
        EQTDVC_RD.where = "EQ_BKID = :EQRDVC.KEY.EQ_BKID";
        IBS.READ(SCCGWA, EQRDVC, this, EQTDVC_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, EQCMSG_ERROR_MSG.EQ_DVC_NOTFND, EQCRDVC.RC);
            EQCRDVC.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        EQRDVC.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRDVC.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQRDVC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRDVC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQTDVC_RD = new DBParm();
        EQTDVC_RD.TableName = "EQTDVC";
        EQTDVC_RD.errhdl = true;
        IBS.WRITE(SCCGWA, EQRDVC, EQTDVC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, EQCMSG_ERROR_MSG.EQ_DVC_EXIST, EQCRDVC.RC);
            EQCRDVC.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_REC_KEY_PROC() throws IOException,SQLException,Exception {
        EQTDVC_RD = new DBParm();
        EQTDVC_RD.TableName = "EQTDVC";
        EQTDVC_RD.where = "EQ_BKID = :EQRDVC.KEY.EQ_BKID "
            + "AND DIV_CPN_FLG = :EQRDVC.KEY.DIV_CPN_FLG "
            + "AND PROC_DT = :EQRDVC.PROC_DT";
        IBS.READ(SCCGWA, EQRDVC, this, EQTDVC_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, EQCMSG_ERROR_MSG.EQ_DVC_NOTFND, EQCRDVC.RC);
            EQCRDVC.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        EQTDVC_RD = new DBParm();
        EQTDVC_RD.TableName = "EQTDVC";
        EQTDVC_RD.upd = true;
        IBS.READ(SCCGWA, EQRDVC, EQTDVC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, EQCMSG_ERROR_MSG.EQ_DVC_NOTFND, EQCRDVC.RC);
            EQCRDVC.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        EQRDVC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRDVC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQTDVC_RD = new DBParm();
        EQTDVC_RD.TableName = "EQTDVC";
        IBS.REWRITE(SCCGWA, EQRDVC, EQTDVC_RD);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        EQTDVC_RD = new DBParm();
        EQTDVC_RD.TableName = "EQTDVC";
        IBS.DELETE(SCCGWA, EQRDVC, EQTDVC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, EQCMSG_ERROR_MSG.EQ_DVC_NOTFND, EQCRDVC.RC);
            EQCRDVC.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_PROC() throws IOException,SQLException,Exception {
        EQTDVC_BR.rp = new DBParm();
        EQTDVC_BR.rp.TableName = "EQTDVC";
        EQTDVC_BR.rp.where = "EQ_BKID = :EQRDVC.KEY.EQ_BKID";
        EQTDVC_BR.rp.order = "EQ_BKID,DIV_CPN_FLG,PROC_DT";
        IBS.STARTBR(SCCGWA, EQRDVC, this, EQTDVC_BR);
    }
    public void T000_STARTBR_UPD_PROC() throws IOException,SQLException,Exception {
        EQTDVC_BR.rp = new DBParm();
        EQTDVC_BR.rp.TableName = "EQTDVC";
        EQTDVC_BR.rp.where = "EQ_BKID = :EQRDVC.KEY.EQ_BKID";
        EQTDVC_BR.rp.upd = true;
        EQTDVC_BR.rp.order = "EQ_BKID,DIV_CPN_FLG,PROC_DT";
        IBS.STARTBR(SCCGWA, EQRDVC, this, EQTDVC_BR);
    }
    public void T000_STARTBR_BY_KEY_PROC() throws IOException,SQLException,Exception {
        EQTDVC_BR.rp = new DBParm();
        EQTDVC_BR.rp.TableName = "EQTDVC";
        EQTDVC_BR.rp.where = "EQ_BKID = :EQRDVC.KEY.EQ_BKID "
            + "AND DIV_CPN_FLG = :EQRDVC.KEY.DIV_CPN_FLG "
            + "AND PROC_DT = :EQRDVC.PROC_DT";
        EQTDVC_BR.rp.order = "EQ_BKID,DIV_CPN_FLG,PROC_DT";
        IBS.STARTBR(SCCGWA, EQRDVC, this, EQTDVC_BR);
    }
    public void T000_STARTBR_FLG_PROC() throws IOException,SQLException,Exception {
        EQTDVC_BR.rp = new DBParm();
        EQTDVC_BR.rp.TableName = "EQTDVC";
        EQTDVC_BR.rp.where = "EQ_BKID = :EQRDVC.KEY.EQ_BKID "
            + "AND DIV_CPN_FLG = :EQRDVC.KEY.DIV_CPN_FLG";
        IBS.STARTBR(SCCGWA, EQRDVC, this, EQTDVC_BR);
    }
    public void T000_STARTBR_DT_PROC() throws IOException,SQLException,Exception {
        EQTDVC_BR.rp = new DBParm();
        EQTDVC_BR.rp.TableName = "EQTDVC";
        EQTDVC_BR.rp.where = "EQ_BKID = :EQRDVC.KEY.EQ_BKID "
            + "AND DIV_CPN_DT = :EQRDVC.KEY.DIV_CPN_DT";
        IBS.STARTBR(SCCGWA, EQRDVC, this, EQTDVC_BR);
    }
    public void T000_STARTBR_STS_PROC() throws IOException,SQLException,Exception {
        EQTDVC_BR.rp = new DBParm();
        EQTDVC_BR.rp.TableName = "EQTDVC";
        EQTDVC_BR.rp.where = "EQ_BKID = :EQRDVC.KEY.EQ_BKID "
            + "AND REC_STS = :EQRDVC.REC_STS";
        IBS.STARTBR(SCCGWA, EQRDVC, this, EQTDVC_BR);
    }
    public void T000_STARTBR_DT_FLG_PROC() throws IOException,SQLException,Exception {
        EQTDVC_BR.rp = new DBParm();
        EQTDVC_BR.rp.TableName = "EQTDVC";
        EQTDVC_BR.rp.where = "EQ_BKID = :EQRDVC.KEY.EQ_BKID "
            + "AND DIV_CPN_DT = :EQRDVC.KEY.DIV_CPN_DT "
            + "AND DIV_CPN_FLG = :EQRDVC.KEY.DIV_CPN_FLG";
        IBS.STARTBR(SCCGWA, EQRDVC, this, EQTDVC_BR);
    }
    public void T000_STARTBR_DT_STS_PROC() throws IOException,SQLException,Exception {
        EQTDVC_BR.rp = new DBParm();
        EQTDVC_BR.rp.TableName = "EQTDVC";
        EQTDVC_BR.rp.where = "EQ_BKID = :EQRDVC.KEY.EQ_BKID "
            + "AND DIV_CPN_DT = :EQRDVC.KEY.DIV_CPN_DT "
            + "AND REC_STS = :EQRDVC.REC_STS";
        IBS.STARTBR(SCCGWA, EQRDVC, this, EQTDVC_BR);
    }
    public void T000_STARTBR_STS_FLG_PROC() throws IOException,SQLException,Exception {
        EQTDVC_BR.rp = new DBParm();
        EQTDVC_BR.rp.TableName = "EQTDVC";
        EQTDVC_BR.rp.where = "EQ_BKID = :EQRDVC.KEY.EQ_BKID "
            + "AND DIV_CPN_FLG = :EQRDVC.KEY.DIV_CPN_FLG "
            + "AND REC_STS = :EQRDVC.REC_STS";
        IBS.STARTBR(SCCGWA, EQRDVC, this, EQTDVC_BR);
    }
    public void T000_STARTBR_DT_FLG_STS_PROC() throws IOException,SQLException,Exception {
        EQTDVC_BR.rp = new DBParm();
        EQTDVC_BR.rp.TableName = "EQTDVC";
        EQTDVC_BR.rp.where = "EQ_BKID = :EQRDVC.KEY.EQ_BKID "
            + "AND DIV_CPN_DT = :EQRDVC.KEY.DIV_CPN_DT "
            + "AND DIV_CPN_FLG = :EQRDVC.KEY.DIV_CPN_FLG "
            + "AND REC_STS = :EQRDVC.REC_STS";
        IBS.STARTBR(SCCGWA, EQRDVC, this, EQTDVC_BR);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, EQRDVC, this, EQTDVC_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "ZZZZZZZZZZZ");
            IBS.CPY2CLS(SCCGWA, EQCMSG_ERROR_MSG.EQ_DVC_NOTFND, EQCRDVC.RC);
            EQCRDVC.RETURN_INFO = 'E';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, EQTDVC_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
