package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZRICTL {
    DBParm LNTICTL_RD;
    brParm LNTICTL_BR = new brParm();
    boolean pgmRtn = false;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    LNRICTL LNRICTL = new LNRICTL();
    SCCGWA SCCGWA;
    LNCRICTL LNCRICTL;
    LNRICTL LNRICTL1;
    public void MP(SCCGWA SCCGWA, LNCRICTL LNCRICTL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCRICTL = LNCRICTL;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZRICTL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNRICTL1 = (LNRICTL) LNCRICTL.REC_PTR;
        LNCRICTL.RETURN_INFO = 'F';
        IBS.init(SCCGWA, LNRICTL);
        IBS.CLONE(SCCGWA, LNRICTL1, LNRICTL);
        CEP.TRC(SCCGWA, LNRICTL);
        LNCRICTL.RC.RC_MMO = "LN";
        LNCRICTL.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (LNCRICTL.FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRICTL.FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRICTL.FUNC == 'R') {
            B030_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRICTL.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRICTL.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRICTL.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCRICTL.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, LNRICTL, LNRICTL1);
    }
    public void B010_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNRICTL.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRICTL.KEY.CONTRACT_NO);
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
        if (LNCRICTL.OPT == 'S') {
            B060_10_START_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (LNCRICTL.OPT == 'A') {
            B060_20_STBR_BY_KEY_PROC();
            if (pgmRtn) return;
        } else if (LNCRICTL.OPT == 'K') {
            B060_20_STBR_BY_KEY1_PROC();
            if (pgmRtn) return;
        } else if (LNCRICTL.OPT == 'C') {
            B060_20_STBR_BY_KEY2_F_PROC();
            if (pgmRtn) return;
        } else if (LNCRICTL.OPT == 'B') {
            B060_20_STBR_BY_KEY2_PROC();
            if (pgmRtn) return;
        } else if (LNCRICTL.OPT == 'R') {
            B060_30_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (LNCRICTL.OPT == 'E') {
            B060_50_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + LNCRICTL.OPT + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B060_10_START_BROWSE_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_PROC();
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
    public void B060_20_STBR_BY_KEY2_F_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_KEY2_F_PROC();
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
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        IBS.READ(SCCGWA, LNRICTL, LNTICTL_RD);
        CEP.TRC(SCCGWA, LNRICTL.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ICTL_NOTFND, LNCRICTL.RC);
            LNCRICTL.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRICTL.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRICTL.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        LNTICTL_RD.errhdl = true;
        IBS.WRITE(SCCGWA, LNRICTL, LNTICTL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY, LNCRICTL.RC);
            LNCRICTL.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        LNTICTL_RD.upd = true;
        IBS.READ(SCCGWA, LNRICTL, LNTICTL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ICTL_NOTFND, LNCRICTL.RC);
            LNCRICTL.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRICTL.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRICTL.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        IBS.REWRITE(SCCGWA, LNRICTL, LNTICTL_RD);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        IBS.DELETE(SCCGWA, LNRICTL, LNTICTL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ICTL_NOTFND, LNCRICTL.RC);
            LNCRICTL.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_PROC() throws IOException,SQLException,Exception {
        LNTICTL_BR.rp = new DBParm();
        LNTICTL_BR.rp.TableName = "LNTICTL";
        LNTICTL_BR.rp.order = "CONTRACT_NO,SUB_CTA_NO";
        IBS.STARTBR(SCCGWA, LNRICTL, LNTICTL_BR);
    }
    public void T000_STARTBR_BY_KEY1_PROC() throws IOException,SQLException,Exception {
        LNTICTL_BR.rp = new DBParm();
        LNTICTL_BR.rp.TableName = "LNTICTL";
        LNTICTL_BR.rp.eqWhere = "CONTRACT_NO";
        LNTICTL_BR.rp.where = "SUB_CTA_NO >= :LNRICTL.KEY.SUB_CTA_NO";
        LNTICTL_BR.rp.order = "SUB_CTA_NO";
        IBS.STARTBR(SCCGWA, LNRICTL, this, LNTICTL_BR);
    }
    public void T000_STARTBR_BY_KEY2_PROC() throws IOException,SQLException,Exception {
        LNTICTL_BR.rp = new DBParm();
        LNTICTL_BR.rp.TableName = "LNTICTL";
        LNTICTL_BR.rp.eqWhere = "CONTRACT_NO, SUB_CTA_NO";
        LNTICTL_BR.rp.order = "SUB_CTA_NO";
        IBS.STARTBR(SCCGWA, LNRICTL, LNTICTL_BR);
    }
    public void T000_STARTBR_BY_KEY2_F_PROC() throws IOException,SQLException,Exception {
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        LNTICTL_RD.eqWhere = "CONTRACT_NO, SUB_CTA_NO";
        LNTICTL_RD.fst = true;
        LNTICTL_RD.order = "SUB_CTA_NO";
        IBS.READ(SCCGWA, LNRICTL, LNTICTL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ICTL_NOTFND, LNCRICTL.RC);
            LNCRICTL.RETURN_INFO = 'E';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BY_KEY_PROC() throws IOException,SQLException,Exception {
        LNTICTL_BR.rp = new DBParm();
        LNTICTL_BR.rp.TableName = "LNTICTL";
        LNTICTL_BR.rp.eqWhere = "CONTRACT_NO";
        LNTICTL_BR.rp.where = "SUB_CTA_NO >= :LNRICTL.KEY.SUB_CTA_NO";
        LNTICTL_BR.rp.order = "CONTRACT_NO,SUB_CTA_NO";
        IBS.STARTBR(SCCGWA, LNRICTL, this, LNTICTL_BR);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRICTL, this, LNTICTL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ICTL_NOTFND, LNCRICTL.RC);
            LNCRICTL.RETURN_INFO = 'E';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTICTL_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
