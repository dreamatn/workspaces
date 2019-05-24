package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZRSETL {
    DBParm LNTSETL_RD;
    brParm LNTSETL_BR = new brParm();
    boolean pgmRtn = false;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    LNRSETL LNRSETL = new LNRSETL();
    SCCGWA SCCGWA;
    LNCRSETL LNCRSETL;
    LNRSETL LNRSETL1;
    public void MP(SCCGWA SCCGWA, LNCRSETL LNCRSETL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCRSETL = LNCRSETL;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZRSETL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNRSETL1 = (LNRSETL) LNCRSETL.REC_PTR;
        LNCRSETL.RETURN_INFO = 'F';
        IBS.CLONE(SCCGWA, LNRSETL1, LNRSETL);
        LNCRSETL.RC.RC_MMO = "LN";
        LNCRSETL.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNRSETL);
        CEP.TRC(SCCGWA, LNRSETL.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRSETL.KEY.CI_TYPE);
        CEP.TRC(SCCGWA, LNRSETL.KEY.CCY);
        CEP.TRC(SCCGWA, LNRSETL.KEY.SETTLE_TYPE);
        CEP.TRC(SCCGWA, LNRSETL.MWHD_AC_FLG);
        CEP.TRC(SCCGWA, LNRSETL.KEY.SEQ_NO);
        if (LNCRSETL.FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRSETL.FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRSETL.FUNC == 'R') {
            B030_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRSETL.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRSETL.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRSETL.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRSETL.FUNC == 'K') {
            B070_READ_RECORD_PROC_BY_KEY();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCRSETL.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, LNRSETL, LNRSETL1);
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
    public void B070_READ_RECORD_PROC_BY_KEY() throws IOException,SQLException,Exception {
        T000_READ_RECORD_PROC_BY_KEY();
        if (pgmRtn) return;
    }
    public void B060_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (LNCRSETL.OPT == 'S') {
            B060_10_START_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (LNCRSETL.OPT == 'A') {
            B060_30_STBR_BY_KEY_PROC();
            if (pgmRtn) return;
        } else if (LNCRSETL.OPT == 'E') {
            B060_30_STBR_BY_KEY1_PROC();
            if (pgmRtn) return;
        } else if (LNCRSETL.OPT == 'Z') {
            B060_30_STBR_BY_CTA_PROC();
            if (pgmRtn) return;
        } else if (LNCRSETL.OPT == 'C') {
            B060_50_STBR_FOR_CCY_PROC();
            if (pgmRtn) return;
        } else if (LNCRSETL.OPT == 'T') {
            B060_50_STBR_BY_CTANO_PROC();
            if (pgmRtn) return;
        } else if (LNCRSETL.OPT == 'R') {
            B060_70_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (LNCRSETL.OPT == 'E') {
            B060_90_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + LNCRSETL.OPT + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B060_10_START_BROWSE_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_PROC();
        if (pgmRtn) return;
    }
    public void B060_30_STBR_BY_KEY_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_KEY_PROC();
        if (pgmRtn) return;
    }
    public void B060_30_STBR_BY_KEY1_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_KEY1_PROC();
        if (pgmRtn) return;
    }
    public void B060_30_STBR_BY_CTA_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_CTA_PROC();
        if (pgmRtn) return;
    }
    public void B060_50_STBR_FOR_CCY_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_FOR_CCY_PROC();
        if (pgmRtn) return;
    }
    public void B060_50_STBR_BY_CTANO_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BY_CTANO_PROC();
        if (pgmRtn) return;
    }
    public void B060_70_READ_NEXT_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_PROC();
        if (pgmRtn) return;
    }
    public void B060_90_END_BROWSE_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_PROC();
        if (pgmRtn) return;
    }
    public void T000_READ_REC_PROC() throws IOException,SQLException,Exception {
        LNTSETL_RD = new DBParm();
        LNTSETL_RD.TableName = "LNTSETL";
        IBS.READ(SCCGWA, LNRSETL, LNTSETL_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_SETL_NOTFND, LNCRSETL.RC);
            LNCRSETL.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNRSETL.AC);
    }
    public void T000_READ_RECORD_PROC_BY_KEY() throws IOException,SQLException,Exception {
        LNTSETL_RD = new DBParm();
        LNTSETL_RD.TableName = "LNTSETL";
        LNTSETL_RD.where = "CONTRACT_NO = :LNRSETL.KEY.CONTRACT_NO "
            + "AND SETTLE_TYPE = :LNRSETL.KEY.SETTLE_TYPE";
        LNTSETL_RD.fst = true;
        IBS.READ(SCCGWA, LNRSETL, this, LNTSETL_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_SETL_NOTFND, LNCRSETL.RC);
            LNCRSETL.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNRSETL.AC);
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRSETL.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRSETL.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRSETL.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRSETL.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTSETL_RD = new DBParm();
        LNTSETL_RD.TableName = "LNTSETL";
        LNTSETL_RD.errhdl = true;
        IBS.WRITE(SCCGWA, LNRSETL, LNTSETL_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.TRC(SCCGWA, "DUPKEY");
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY, LNCRSETL.RC);
            LNCRSETL.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.TRC(SCCGWA, "NON NORMAL");
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY, LNCRSETL.RC);
            LNCRSETL.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        LNTSETL_RD = new DBParm();
        LNTSETL_RD.TableName = "LNTSETL";
        LNTSETL_RD.upd = true;
        IBS.READ(SCCGWA, LNRSETL, LNTSETL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_SETL_NOTFND, LNCRSETL.RC);
            LNCRSETL.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRSETL.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRSETL.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTSETL_RD = new DBParm();
        LNTSETL_RD.TableName = "LNTSETL";
        IBS.REWRITE(SCCGWA, LNRSETL, LNTSETL_RD);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        LNTSETL_RD = new DBParm();
        LNTSETL_RD.TableName = "LNTSETL";
        IBS.DELETE(SCCGWA, LNRSETL, LNTSETL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_SETL_NOTFND, LNCRSETL.RC);
            LNCRSETL.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_PROC() throws IOException,SQLException,Exception {
        LNTSETL_BR.rp = new DBParm();
        LNTSETL_BR.rp.TableName = "LNTSETL";
        LNTSETL_BR.rp.order = "CONTRACT_NO,CI_TYPE, CCY,SETTLE_TYPE";
        IBS.STARTBR(SCCGWA, LNRSETL, LNTSETL_BR);
    }
    public void T000_STARTBR_BY_KEY_PROC() throws IOException,SQLException,Exception {
        LNTSETL_BR.rp = new DBParm();
        LNTSETL_BR.rp.TableName = "LNTSETL";
        LNTSETL_BR.rp.eqWhere = "CONTRACT_NO,CI_TYPE,SETTLE_TYPE";
        LNTSETL_BR.rp.order = "CONTRACT_NO,CI_TYPE, CCY,SETTLE_TYPE";
        IBS.STARTBR(SCCGWA, LNRSETL, LNTSETL_BR);
    }
    public void T000_STARTBR_BY_KEY1_PROC() throws IOException,SQLException,Exception {
        LNTSETL_BR.rp = new DBParm();
        LNTSETL_BR.rp.TableName = "LNTSETL";
        LNTSETL_BR.rp.eqWhere = "CONTRACT_NO,SETTLE_TYPE";
        LNTSETL_BR.rp.order = "CONTRACT_NO";
        IBS.STARTBR(SCCGWA, LNRSETL, LNTSETL_BR);
    }
    public void T000_STARTBR_BY_CTA_PROC() throws IOException,SQLException,Exception {
        LNTSETL_BR.rp = new DBParm();
        LNTSETL_BR.rp.TableName = "LNTSETL";
        LNTSETL_BR.rp.where = "CONTRACT_NO = :LNRSETL.KEY.CONTRACT_NO";
        LNTSETL_BR.rp.order = "CONTRACT_NO";
        IBS.STARTBR(SCCGWA, LNRSETL, this, LNTSETL_BR);
    }
    public void T000_STARTBR_FOR_CCY_PROC() throws IOException,SQLException,Exception {
        LNTSETL_BR.rp = new DBParm();
        LNTSETL_BR.rp.TableName = "LNTSETL";
        LNTSETL_BR.rp.where = "CONTRACT_NO = :LNRSETL.KEY.CONTRACT_NO "
            + "AND CI_TYPE = :LNRSETL.KEY.CI_TYPE";
        LNTSETL_BR.rp.order = "CCY";
        IBS.STARTBR(SCCGWA, LNRSETL, this, LNTSETL_BR);
    }
    public void T000_STARTBR_BY_CTANO_PROC() throws IOException,SQLException,Exception {
        null.rp.upd = true;
        LNTSETL_BR.rp = new DBParm();
        LNTSETL_BR.rp.TableName = "LNTSETL";
        LNTSETL_BR.rp.where = "CONTRACT_NO = :LNRSETL.KEY.CONTRACT_NO";
        IBS.STARTBR(SCCGWA, LNRSETL, this, LNTSETL_BR);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRSETL, this, LNTSETL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_SETL_NOTFND, LNCRSETL.RC);
            LNCRSETL.RETURN_INFO = 'E';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTSETL_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCRSETL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "LNCRSETL=");
            CEP.TRC(SCCGWA, LNCRSETL);
            CEP.TRC(SCCGWA, "LNRSETL =");
            CEP.TRC(SCCGWA, LNRSETL);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
