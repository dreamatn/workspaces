package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZRFWDH {
    DBParm LNTFWDH_RD;
    brParm LNTFWDH_BR = new brParm();
    boolean pgmRtn = false;
    String WS_STR_CTA = " ";
    String WS_END_CTA = " ";
    int WS_STR_DATE = 0;
    int WS_END_DATE = 0;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    LNRFWDH LNRFWDH = new LNRFWDH();
    SCCGWA SCCGWA;
    LNCRFWDH LNCRFWDH;
    LNRFWDH LNRFWDH1;
    public void MP(SCCGWA SCCGWA, LNCRFWDH LNCRFWDH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCRFWDH = LNCRFWDH;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZRFWDH return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNRFWDH1 = (LNRFWDH) LNCRFWDH.REC_PTR;
        LNCRFWDH.RETURN_INFO = 'F';
        IBS.init(SCCGWA, LNRFWDH);
        IBS.CLONE(SCCGWA, LNRFWDH1, LNRFWDH);
        LNCRFWDH.RC.RC_MMO = "LN";
        LNCRFWDH.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (LNCRFWDH.FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRFWDH.FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRFWDH.FUNC == 'R') {
            B030_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRFWDH.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRFWDH.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRFWDH.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCRFWDH.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, LNRFWDH, LNRFWDH1);
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
        if (LNCRFWDH.OPT == 'S') {
            B060_10_START_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (LNCRFWDH.OPT == 'U') {
            B060_10_START_BRW_UP_PROC();
            if (pgmRtn) return;
        } else if (LNCRFWDH.OPT == 'D') {
            B060_15_STBR_BY_DT_SPC_PRC();
            if (pgmRtn) return;
        } else if (LNCRFWDH.OPT == 'N') {
            B060_15_STBR_BY_CTANO_PROC();
            if (pgmRtn) return;
        } else if (LNCRFWDH.OPT == 'A') {
            B060_20_STBR_BY_KEY_PROC();
            if (pgmRtn) return;
        } else if (LNCRFWDH.OPT == 'K') {
            B060_20_STBR_BY_KEY2_PROC();
            if (pgmRtn) return;
        } else if (LNCRFWDH.OPT == 'T') {
            B060_21_STBR_BY_TERM_CTA_PROC();
            if (pgmRtn) return;
        } else if (LNCRFWDH.OPT == 'R') {
            B060_30_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (LNCRFWDH.OPT == 'E') {
            B060_50_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + LNCRFWDH.OPT + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B060_10_START_BROWSE_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_PROC();
        if (pgmRtn) return;
    }
    public void B060_10_START_BRW_UP_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_UPDATE_PROC();
        if (pgmRtn) return;
    }
    public void B060_15_STBR_BY_DT_SPC_PRC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_DT_SPC_PRC();
        if (pgmRtn) return;
    }
    public void B060_15_STBR_BY_CTANO_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_CTANO_PROC();
        if (pgmRtn) return;
    }
    public void B060_21_STBR_BY_TERM_CTA_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_TERM_CTA_PROC();
        if (pgmRtn) return;
    }
    public void B060_20_STBR_BY_KEY2_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_KEY2_PROC();
        if (pgmRtn) return;
    }
    public void B060_20_STBR_BY_INDEX3_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_INDEX3_PROC();
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
        LNTFWDH_RD = new DBParm();
        LNTFWDH_RD.TableName = "LNTFWDH";
        IBS.READ(SCCGWA, LNRFWDH, LNTFWDH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_FWDH_NOTFND, LNCRFWDH.RC);
            LNCRFWDH.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRFWDH.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRFWDH.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRFWDH.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRFWDH.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTFWDH_RD = new DBParm();
        LNTFWDH_RD.TableName = "LNTFWDH";
        LNTFWDH_RD.errhdl = true;
        IBS.WRITE(SCCGWA, LNRFWDH, LNTFWDH_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY, LNCRFWDH.RC);
            LNCRFWDH.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        LNTFWDH_RD = new DBParm();
        LNTFWDH_RD.TableName = "LNTFWDH";
        LNTFWDH_RD.upd = true;
        IBS.READ(SCCGWA, LNRFWDH, LNTFWDH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_FWDH_NOTFND, LNCRFWDH.RC);
            LNCRFWDH.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRFWDH.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRFWDH.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTFWDH_RD = new DBParm();
        LNTFWDH_RD.TableName = "LNTFWDH";
        IBS.REWRITE(SCCGWA, LNRFWDH, LNTFWDH_RD);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        LNTFWDH_RD = new DBParm();
        LNTFWDH_RD.TableName = "LNTFWDH";
        IBS.DELETE(SCCGWA, LNRFWDH, LNTFWDH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_FWDH_NOTFND, LNCRFWDH.RC);
            LNCRFWDH.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_UPDATE_PROC() throws IOException,SQLException,Exception {
        null.rp.upd = true;
        LNTFWDH_BR.rp = new DBParm();
        LNTFWDH_BR.rp.TableName = "LNTFWDH";
        LNTFWDH_BR.rp.order = "CONTRACT_NO,TR_JRN_NO";
        IBS.STARTBR(SCCGWA, LNRFWDH, LNTFWDH_BR);
    }
    public void T000_STARTBR_PROC() throws IOException,SQLException,Exception {
        LNTFWDH_BR.rp = new DBParm();
        LNTFWDH_BR.rp.TableName = "LNTFWDH";
        LNTFWDH_BR.rp.order = "CONTRACT_NO,TR_JRN_NO";
        IBS.STARTBR(SCCGWA, LNRFWDH, LNTFWDH_BR);
    }
    public void T000_STARTBR_BY_DT_SPC_PRC() throws IOException,SQLException,Exception {
        WS_STR_DATE = LNCRFWDH.STR_DATE;
        WS_END_DATE = LNCRFWDH.END_DATE;
        LNTFWDH_BR.rp = new DBParm();
        LNTFWDH_BR.rp.TableName = "LNTFWDH";
        LNTFWDH_BR.rp.where = "TR_VAL_DATE <= :WS_END_DATE "
            + "AND TRAN_STS = :LNRFWDH.TRAN_STS";
        IBS.STARTBR(SCCGWA, LNRFWDH, this, LNTFWDH_BR);
    }
    public void T000_STARTBR_BY_CTANO_PROC() throws IOException,SQLException,Exception {
        null.rp.upd = true;
        LNTFWDH_BR.rp = new DBParm();
        LNTFWDH_BR.rp.TableName = "LNTFWDH";
        LNTFWDH_BR.rp.where = "CONTRACT_NO = :LNRFWDH.KEY.CONTRACT_NO";
        IBS.STARTBR(SCCGWA, LNRFWDH, this, LNTFWDH_BR);
    }
    public void T000_STARTBR_BY_TERM_CTA_PROC() throws IOException,SQLException,Exception {
        WS_STR_CTA = LNCRFWDH.STR_CTA;
        WS_END_CTA = LNCRFWDH.END_CTA;
        WS_STR_DATE = LNCRFWDH.STR_DATE;
        WS_END_DATE = LNCRFWDH.END_DATE;
        LNTFWDH_BR.rp = new DBParm();
        LNTFWDH_BR.rp.TableName = "LNTFWDH";
        LNTFWDH_BR.rp.where = "CONTRACT_NO >= :WS_STR_CTA "
            + "AND CONTRACT_NO < :WS_END_CTA "
            + "AND TR_VAL_DATE > :WS_STR_DATE "
            + "AND TR_VAL_DATE <= :WS_END_DATE "
            + "AND TRAN_STS = :LNRFWDH.TRAN_STS";
        LNTFWDH_BR.rp.order = "TR_VAL_DATE";
        IBS.STARTBR(SCCGWA, LNRFWDH, this, LNTFWDH_BR);
    }
    public void T000_STARTBR_BY_KEY2_PROC() throws IOException,SQLException,Exception {
        LNTFWDH_BR.rp = new DBParm();
        LNTFWDH_BR.rp.TableName = "LNTFWDH";
        LNTFWDH_BR.rp.where = "TR_VAL_DATE >= :LNRFWDH.KEY.TR_VAL_DATE "
            + "AND TRAN_STS >= :LNRFWDH.TRAN_STS";
        LNTFWDH_BR.rp.order = "TR_VAL_DATE,TRAN_STS";
        IBS.STARTBR(SCCGWA, LNRFWDH, this, LNTFWDH_BR);
    }
    public void T000_STARTBR_BY_INDEX3_PROC() throws IOException,SQLException,Exception {
    }
    public void T000_STARTBR_BY_KEY_PROC() throws IOException,SQLException,Exception {
        LNTFWDH_BR.rp = new DBParm();
        LNTFWDH_BR.rp.TableName = "LNTFWDH";
        LNTFWDH_BR.rp.where = "CONTRACT_NO >= :LNRFWDH.KEY.CONTRACT_NO "
            + "AND TR_VAL_DATE >= :LNRFWDH.KEY.TR_VAL_DATE "
            + "AND RECEIPT_NO >= :LNRFWDH.KEY.RECEIPT_NO";
        LNTFWDH_BR.rp.order = "CONTRACT_NO,TR_JRN_NO";
        IBS.STARTBR(SCCGWA, LNRFWDH, this, LNTFWDH_BR);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRFWDH, this, LNTFWDH_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_FWDH_NOTFND, LNCRFWDH.RC);
            LNCRFWDH.RETURN_INFO = 'E';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTFWDH_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        if (LNCRFWDH.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "LNCRFWDH=");
            CEP.TRC(SCCGWA, LNCRFWDH);
            CEP.TRC(SCCGWA, "LNRFWDH =");
            CEP.TRC(SCCGWA, LNRFWDH);
        }
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
