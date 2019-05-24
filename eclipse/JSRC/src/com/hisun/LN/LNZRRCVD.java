package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZRRCVD {
    DBParm LNTRCVD_RD;
    brParm LNTRCVD_BR = new brParm();
    boolean pgmRtn = false;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    LNRRCVD LNRRCVD = new LNRRCVD();
    SCCGWA SCCGWA;
    LNCRRCVD LNCRRCVD;
    LNRRCVD LNRRCVD1;
    public void MP(SCCGWA SCCGWA, LNCRRCVD LNCRRCVD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCRRCVD = LNCRRCVD;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZRRCVD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNRRCVD1 = (LNRRCVD) LNCRRCVD.REC_PTR;
        LNCRRCVD.RETURN_INFO = 'F';
        IBS.init(SCCGWA, LNRRCVD);
        IBS.CLONE(SCCGWA, LNRRCVD1, LNRRCVD);
        LNCRRCVD.RC.RC_MMO = "LN";
        LNCRRCVD.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (LNCRRCVD.FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRRCVD.FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRRCVD.FUNC == 'R') {
            B030_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRRCVD.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRRCVD.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRRCVD.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCRRCVD.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, LNRRCVD, LNRRCVD1);
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
        if (LNCRRCVD.OPT == 'S') {
            B060_10_START_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (LNCRRCVD.OPT == 'K') {
            B060_10_START_BROWSE1_PROC();
            if (pgmRtn) return;
        } else if (LNCRRCVD.OPT == 'I') {
            B060_20_STBR_BY_INDEX_PROC();
            if (pgmRtn) return;
        } else if (LNCRRCVD.OPT == 'A') {
            B060_20_STBR_BY_KEY_PROC();
            if (pgmRtn) return;
        } else if (LNCRRCVD.OPT == 'Y') {
            B060_20_STBR_BY_KEY1_PROC();
            if (pgmRtn) return;
        } else if (LNCRRCVD.OPT == 'N') {
            B060_20_STBR_BY_KEY2_PROC();
            if (pgmRtn) return;
        } else if (LNCRRCVD.OPT == '3') {
            B060_20_STBR_BY_KEY3_PROC();
            if (pgmRtn) return;
        } else if (LNCRRCVD.OPT == 'T') {
            B060_20_STBR_BY_TERM_PROC();
            if (pgmRtn) return;
        } else if (LNCRRCVD.OPT == '4') {
            B060_20_STBR_BY_KEY4_PROC();
            if (pgmRtn) return;
        } else if (LNCRRCVD.OPT == 'R') {
            B060_30_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (LNCRRCVD.OPT == 'E') {
            B060_50_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + LNCRRCVD.OPT + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B060_10_START_BROWSE_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_PROC();
        if (pgmRtn) return;
    }
    public void B060_10_START_BROWSE1_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_PROC1();
        if (pgmRtn) return;
    }
    public void B060_20_STBR_BY_KEY_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_KEY_PROC();
        if (pgmRtn) return;
    }
    public void B060_20_STBR_BY_INDEX_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_INDEX_PROC();
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
    public void B060_20_STBR_BY_KEY3_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_KEY3_PROC();
        if (pgmRtn) return;
    }
    public void B060_20_STBR_BY_TERM_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_TERM_PROC();
        if (pgmRtn) return;
    }
    public void B060_20_STBR_BY_KEY4_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_KEY4_PROC();
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
        LNTRCVD_RD = new DBParm();
        LNTRCVD_RD.TableName = "LNTRCVD";
        IBS.READ(SCCGWA, LNRRCVD, LNTRCVD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_RCVD_NOTFND, LNCRRCVD.RC);
            LNCRRCVD.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRRCVD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRRCVD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTRCVD_RD = new DBParm();
        LNTRCVD_RD.TableName = "LNTRCVD";
        LNTRCVD_RD.errhdl = true;
        IBS.WRITE(SCCGWA, LNRRCVD, LNTRCVD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY, LNCRRCVD.RC);
            LNCRRCVD.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        LNTRCVD_RD = new DBParm();
        LNTRCVD_RD.TableName = "LNTRCVD";
        LNTRCVD_RD.upd = true;
        IBS.READ(SCCGWA, LNRRCVD, LNTRCVD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_RCVD_NOTFND, LNCRRCVD.RC);
            LNCRRCVD.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRRCVD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRRCVD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTRCVD_RD = new DBParm();
        LNTRCVD_RD.TableName = "LNTRCVD";
        IBS.REWRITE(SCCGWA, LNRRCVD, LNTRCVD_RD);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        LNTRCVD_RD = new DBParm();
        LNTRCVD_RD.TableName = "LNTRCVD";
        IBS.DELETE(SCCGWA, LNRRCVD, LNTRCVD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_RCVD_NOTFND, LNCRRCVD.RC);
            LNCRRCVD.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_PROC() throws IOException,SQLException,Exception {
        LNTRCVD_BR.rp = new DBParm();
        LNTRCVD_BR.rp.TableName = "LNTRCVD";
        LNTRCVD_BR.rp.order = "CONTRACT_NO,SUB_CTA_NO,AMT_TYP,TERM";
        IBS.STARTBR(SCCGWA, LNRRCVD, LNTRCVD_BR);
    }
    public void T000_STARTBR_PROC1() throws IOException,SQLException,Exception {
        LNTRCVD_BR.rp = new DBParm();
        LNTRCVD_BR.rp.TableName = "LNTRCVD";
        LNTRCVD_BR.rp.order = "TERM_STS,DUE_DT";
        IBS.STARTBR(SCCGWA, LNRRCVD, LNTRCVD_BR);
    }
    public void T000_STARTBR_BY_INDEX_PROC() throws IOException,SQLException,Exception {
        LNTRCVD_BR.rp = new DBParm();
        LNTRCVD_BR.rp.TableName = "LNTRCVD";
        LNTRCVD_BR.rp.eqWhere = "CONTRACT_NO,SUB_CTA_NO,AMT_TYP";
        LNTRCVD_BR.rp.where = "DUE_DT <= :LNRRCVD.DUE_DT";
        LNTRCVD_BR.rp.order = "CONTRACT_NO,SUB_CTA_NO,AMT_TYP,DUE_DT";
        IBS.STARTBR(SCCGWA, LNRRCVD, this, LNTRCVD_BR);
    }
    public void T000_STARTBR_BY_KEY2_PROC() throws IOException,SQLException,Exception {
        LNTRCVD_BR.rp = new DBParm();
        LNTRCVD_BR.rp.TableName = "LNTRCVD";
        LNTRCVD_BR.rp.eqWhere = "CONTRACT_NO,SUB_CTA_NO";
        LNTRCVD_BR.rp.where = "DUE_DT <= :LNRRCVD.DUE_DT";
        LNTRCVD_BR.rp.order = "AMT_TYP,TERM";
        IBS.STARTBR(SCCGWA, LNRRCVD, this, LNTRCVD_BR);
    }
    public void T000_STARTBR_BY_KEY3_PROC() throws IOException,SQLException,Exception {
        LNTRCVD_BR.rp = new DBParm();
        LNTRCVD_BR.rp.TableName = "LNTRCVD";
        LNTRCVD_BR.rp.eqWhere = "CONTRACT_NO,SUB_CTA_NO,AMT_TYP";
        LNTRCVD_BR.rp.where = "TERM >= :LNRRCVD.KEY.TERM";
        LNTRCVD_BR.rp.order = "CONTRACT_NO,SUB_CTA_NO,AMT_TYP,TERM";
        IBS.STARTBR(SCCGWA, LNRRCVD, this, LNTRCVD_BR);
    }
    public void T000_STARTBR_BY_TERM_PROC() throws IOException,SQLException,Exception {
        LNTRCVD_BR.rp = new DBParm();
        LNTRCVD_BR.rp.TableName = "LNTRCVD";
        LNTRCVD_BR.rp.eqWhere = "CONTRACT_NO,SUB_CTA_NO,TERM";
        LNTRCVD_BR.rp.order = "CONTRACT_NO,SUB_CTA_NO";
        IBS.STARTBR(SCCGWA, LNRRCVD, LNTRCVD_BR);
    }
    public void T000_STARTBR_BY_KEY4_PROC() throws IOException,SQLException,Exception {
        LNTRCVD_BR.rp = new DBParm();
        LNTRCVD_BR.rp.TableName = "LNTRCVD";
        LNTRCVD_BR.rp.eqWhere = "CONTRACT_NO,SUB_CTA_NO";
        LNTRCVD_BR.rp.where = "TERM >= :LNRRCVD.KEY.TERM";
        LNTRCVD_BR.rp.order = "CONTRACT_NO,SUB_CTA_NO,AMT_TYP,TERM";
        IBS.STARTBR(SCCGWA, LNRRCVD, this, LNTRCVD_BR);
    }
    public void T000_STARTBR_BY_KEY1_PROC() throws IOException,SQLException,Exception {
        LNTRCVD_BR.rp = new DBParm();
        LNTRCVD_BR.rp.TableName = "LNTRCVD";
        LNTRCVD_BR.rp.where = "CONTRACT_NO >= :LNRRCVD.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO >= :LNRRCVD.KEY.SUB_CTA_NO "
            + "AND TERM_STS >= :LNRRCVD.TERM_STS";
        LNTRCVD_BR.rp.order = "DUE_DT";
        IBS.STARTBR(SCCGWA, LNRRCVD, this, LNTRCVD_BR);
    }
    public void T000_STARTBR_BY_KEY_PROC() throws IOException,SQLException,Exception {
        LNTRCVD_BR.rp = new DBParm();
        LNTRCVD_BR.rp.TableName = "LNTRCVD";
        LNTRCVD_BR.rp.where = "CONTRACT_NO >= :LNRRCVD.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO >= :LNRRCVD.KEY.SUB_CTA_NO "
            + "AND AMT_TYP >= :LNRRCVD.KEY.AMT_TYP "
            + "AND TERM >= :LNRRCVD.KEY.TERM";
        LNTRCVD_BR.rp.order = "CONTRACT_NO,SUB_CTA_NO,AMT_TYP,TERM";
        IBS.STARTBR(SCCGWA, LNRRCVD, this, LNTRCVD_BR);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRRCVD, this, LNTRCVD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_RCVD_NOTFND, LNCRRCVD.RC);
            LNCRRCVD.RETURN_INFO = 'E';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTRCVD_BR);
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
