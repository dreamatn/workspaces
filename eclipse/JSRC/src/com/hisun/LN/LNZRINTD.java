package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZRINTD {
    DBParm LNTINTD_RD;
    brParm LNTINTD_BR = new brParm();
    boolean pgmRtn = false;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    LNRINTD LNRINTD = new LNRINTD();
    SCCGWA SCCGWA;
    LNCRINTD LNCRINTD;
    LNRINTD LNRINTD1;
    public void MP(SCCGWA SCCGWA, LNCRINTD LNCRINTD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCRINTD = LNCRINTD;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZRINTD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNRINTD1 = (LNRINTD) LNCRINTD.REC_PTR;
        LNCRINTD.RETURN_INFO = 'F';
        IBS.init(SCCGWA, LNRINTD);
        IBS.CLONE(SCCGWA, LNRINTD1, LNRINTD);
        LNCRINTD.RC.RC_MMO = "LN";
        LNCRINTD.RC.RC_CODE = 0;
        CEP.TRC(SCCGWA, LNRINTD.KEY.CONTRACT_NO);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (LNCRINTD.FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRINTD.FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRINTD.FUNC == 'R') {
            B030_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRINTD.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRINTD.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRINTD.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCRINTD.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, LNRINTD, LNRINTD1);
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
        if (LNCRINTD.OPT == 'S') {
            B060_10_START_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (LNCRINTD.OPT == 'A') {
            B060_20_STBR_BY_KEY_PROC();
            if (pgmRtn) return;
        } else if (LNCRINTD.OPT == 'C') {
            B060_20_STBR_BY_KEY1_PROC();
            if (pgmRtn) return;
        } else if (LNCRINTD.OPT == 'D') {
            B060_20_STBR_BY_KEY2_PROC();
            if (pgmRtn) return;
        } else if (LNCRINTD.OPT == 'T') {
            B060_20_STBR_BY_CTANO_PROC();
            if (pgmRtn) return;
        } else if (LNCRINTD.OPT == 'R') {
            B060_30_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (LNCRINTD.OPT == 'E') {
            B060_50_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + LNCRINTD.OPT + ")";
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
    public void B060_20_STBR_BY_KEY1_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_KEY1_PROC();
        if (pgmRtn) return;
    }
    public void B060_20_STBR_BY_KEY2_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_KEY2_PROC();
        if (pgmRtn) return;
    }
    public void B060_20_STBR_BY_CTANO_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_CTANO_PROC();
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
        LNTINTD_RD = new DBParm();
        LNTINTD_RD.TableName = "LNTINTD";
        IBS.READ(SCCGWA, LNRINTD, LNTINTD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_INTD_NOTFND, LNCRINTD.RC);
            LNCRINTD.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRINTD.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRINTD.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRINTD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRINTD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTINTD_RD = new DBParm();
        LNTINTD_RD.TableName = "LNTINTD";
        LNTINTD_RD.errhdl = true;
        IBS.WRITE(SCCGWA, LNRINTD, LNTINTD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY, LNCRINTD.RC);
            LNCRINTD.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        LNTINTD_RD = new DBParm();
        LNTINTD_RD.TableName = "LNTINTD";
        LNTINTD_RD.upd = true;
        IBS.READ(SCCGWA, LNRINTD, LNTINTD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_INTD_NOTFND, LNCRINTD.RC);
            LNCRINTD.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRINTD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRINTD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTINTD_RD = new DBParm();
        LNTINTD_RD.TableName = "LNTINTD";
        IBS.REWRITE(SCCGWA, LNRINTD, LNTINTD_RD);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        LNTINTD_RD = new DBParm();
        LNTINTD_RD.TableName = "LNTINTD";
        IBS.DELETE(SCCGWA, LNRINTD, LNTINTD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_INTD_NOTFND, LNCRINTD.RC);
            LNCRINTD.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_PROC() throws IOException,SQLException,Exception {
        LNTINTD_BR.rp = new DBParm();
        LNTINTD_BR.rp.TableName = "LNTINTD";
        LNTINTD_BR.rp.order = "CONTRACT_NO,SUB_CTA_NO,REPY_MTH,TERM,INT_TYP, IRUL_SEQ,CTNR_NO,VAL_DT";
        IBS.STARTBR(SCCGWA, LNRINTD, LNTINTD_BR);
    }
    public void T000_STARTBR_BY_KEY_PROC() throws IOException,SQLException,Exception {
        LNTINTD_BR.rp = new DBParm();
        LNTINTD_BR.rp.TableName = "LNTINTD";
        LNTINTD_BR.rp.where = "CONTRACT_NO >= :LNRINTD.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO >= :LNRINTD.KEY.SUB_CTA_NO "
            + "AND REPY_MTH >= :LNRINTD.KEY.REPY_MTH "
            + "AND TERM >= :LNRINTD.KEY.TERM "
            + "AND INT_TYP >= :LNRINTD.KEY.INT_TYP "
            + "AND IRUL_SEQ >= :LNRINTD.KEY.IRUL_SEQ "
            + "AND CTNR_NO >= :LNRINTD.KEY.CTNR_NO "
            + "AND VAL_DT >= :LNRINTD.KEY.VAL_DT";
        LNTINTD_BR.rp.order = "CONTRACT_NO,SUB_CTA_NO,REPY_MTH,TERM,INT_TYP, IRUL_SEQ,CTNR_NO,VAL_DT";
        IBS.STARTBR(SCCGWA, LNRINTD, this, LNTINTD_BR);
    }
    public void T000_STARTBR_BY_CTANO_PROC() throws IOException,SQLException,Exception {
        null.rp.upd = true;
        LNTINTD_BR.rp = new DBParm();
        LNTINTD_BR.rp.TableName = "LNTINTD";
        LNTINTD_BR.rp.eqWhere = "CONTRACT_NO";
        LNTINTD_BR.rp.order = "TERM";
        IBS.STARTBR(SCCGWA, LNRINTD, LNTINTD_BR);
    }
    public void T000_STARTBR_BY_KEY1_PROC() throws IOException,SQLException,Exception {
        LNTINTD_BR.rp = new DBParm();
        LNTINTD_BR.rp.TableName = "LNTINTD";
        LNTINTD_BR.rp.upd = true;
        LNTINTD_BR.rp.eqWhere = "CONTRACT_NO,SUB_CTA_NO";
        LNTINTD_BR.rp.where = "INT_TYP = :LNRINTD.KEY.INT_TYP "
            + "AND REPY_MTH = :LNRINTD.KEY.REPY_MTH "
            + "AND CUT_OFF_DT <= :LNRINTD.CUT_OFF_DT";
        LNTINTD_BR.rp.order = "TERM,VAL_DT";
        IBS.STARTBR(SCCGWA, LNRINTD, this, LNTINTD_BR);
    }
    public void T000_STARTBR_BY_KEY2_PROC() throws IOException,SQLException,Exception {
        LNTINTD_BR.rp = new DBParm();
        LNTINTD_BR.rp.TableName = "LNTINTD";
        LNTINTD_BR.rp.upd = true;
        LNTINTD_BR.rp.eqWhere = "CONTRACT_NO,SUB_CTA_NO";
        LNTINTD_BR.rp.where = "TERM = :LNRINTD.KEY.TERM "
            + "AND INT_TYP = :LNRINTD.KEY.INT_TYP "
            + "AND CUT_OFF_DT <= :LNRINTD.CUT_OFF_DT";
        LNTINTD_BR.rp.order = "VAL_DT DESC";
        IBS.STARTBR(SCCGWA, LNRINTD, this, LNTINTD_BR);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRINTD, this, LNTINTD_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_INTD_NOTFND, LNCRINTD.RC);
            LNCRINTD.RETURN_INFO = 'E';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTINTD_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCRINTD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "LNCRINTD=");
            CEP.TRC(SCCGWA, LNCRINTD);
            CEP.TRC(SCCGWA, "LNRINTD =");
            CEP.TRC(SCCGWA, LNRINTD);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
