package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZRFUND {
    DBParm LNTFUND_RD;
    brParm LNTFUND_BR = new brParm();
    boolean pgmRtn = false;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    LNRFUND LNRFUND = new LNRFUND();
    SCCGWA SCCGWA;
    LNCRFUND LNCRFUND;
    LNRFUND LNRFUND1;
    public void MP(SCCGWA SCCGWA, LNCRFUND LNCRFUND) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCRFUND = LNCRFUND;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZRFUND return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNRFUND1 = (LNRFUND) LNCRFUND.REC_PTR;
        LNCRFUND.RETURN_INFO = 'F';
        IBS.init(SCCGWA, LNRFUND);
        IBS.CLONE(SCCGWA, LNRFUND1, LNRFUND);
        LNCRFUND.RC.RC_MMO = "LN";
        LNCRFUND.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (LNCRFUND.FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRFUND.FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRFUND.FUNC == 'R') {
            B030_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRFUND.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRFUND.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRFUND.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCRFUND.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, LNRFUND, LNRFUND1);
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
        if (LNCRFUND.OPT == 'N') {
            B060_10_STBR_BY_CI_NO();
            if (pgmRtn) return;
        } else if (LNCRFUND.OPT == 'S') {
            B060_20_STBR_BY_ALL();
            if (pgmRtn) return;
        } else if (LNCRFUND.OPT == 'R') {
            B060_30_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (LNCRFUND.OPT == 'E') {
            B060_40_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + LNCRFUND.OPT + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B060_10_STBR_BY_CI_NO() throws IOException,SQLException,Exception {
        T000_STARTBR_PROC_BY_CI_NO();
        if (pgmRtn) return;
    }
    public void B060_20_STBR_BY_ALL() throws IOException,SQLException,Exception {
        T000_STARTBR_PROC_BY_ALL();
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
    public void T000_READ_REC_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNRFUND.KEY.PROJ_NO);
        LNTFUND_RD = new DBParm();
        LNTFUND_RD.TableName = "LNTFUND";
        IBS.READ(SCCGWA, LNRFUND, LNTFUND_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "333333");
            LNCRFUND.RETURN_INFO = 'F';
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "4444444");
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUND_NOT_EXIST, LNCRFUND.RC);
            LNCRFUND.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRFUND.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRFUND.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRFUND.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRFUND.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTFUND_RD = new DBParm();
        LNTFUND_RD.TableName = "LNTFUND";
        LNTFUND_RD.errhdl = true;
        IBS.WRITE(SCCGWA, LNRFUND, LNTFUND_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY, LNCRFUND.RC);
            LNCRFUND.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        LNTFUND_RD = new DBParm();
        LNTFUND_RD.TableName = "LNTFUND";
        LNTFUND_RD.upd = true;
        IBS.READ(SCCGWA, LNRFUND, LNTFUND_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUND_NOT_EXIST, LNCRFUND.RC);
            LNCRFUND.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRFUND.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRFUND.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTFUND_RD = new DBParm();
        LNTFUND_RD.TableName = "LNTFUND";
        IBS.REWRITE(SCCGWA, LNRFUND, LNTFUND_RD);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        LNTFUND_RD = new DBParm();
        LNTFUND_RD.TableName = "LNTFUND";
        IBS.DELETE(SCCGWA, LNRFUND, LNTFUND_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUND_NOT_EXIST, LNCRFUND.RC);
            LNCRFUND.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_PROC_BY_CI_NO() throws IOException,SQLException,Exception {
        LNTFUND_BR.rp = new DBParm();
        LNTFUND_BR.rp.TableName = "LNTFUND";
        LNTFUND_BR.rp.where = "CI_NO = :LNRFUND.CI_NO";
        LNTFUND_BR.rp.order = "PROJ_NO";
        IBS.STARTBR(SCCGWA, LNRFUND, this, LNTFUND_BR);
    }
    public void T000_STARTBR_PROC_BY_ALL() throws IOException,SQLException,Exception {
        LNTFUND_BR.rp = new DBParm();
        LNTFUND_BR.rp.TableName = "LNTFUND";
        LNTFUND_BR.rp.order = "PROJ_NO";
        IBS.STARTBR(SCCGWA, LNRFUND, LNTFUND_BR);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRFUND, this, LNTFUND_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUND_NOT_EXIST, LNCRFUND.RC);
            LNCRFUND.RETURN_INFO = 'E';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTFUND_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCRFUND.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "LNCRFUND=");
            CEP.TRC(SCCGWA, LNCRFUND);
            CEP.TRC(SCCGWA, "LNRFUND =");
            CEP.TRC(SCCGWA, LNRFUND);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
