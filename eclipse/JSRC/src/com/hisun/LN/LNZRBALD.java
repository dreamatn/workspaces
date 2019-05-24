package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZRBALD {
    DBParm LNTBALD_RD;
    brParm LNTBALD_BR = new brParm();
    boolean pgmRtn = false;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    LNRBALD LNRBALD = new LNRBALD();
    SCCGWA SCCGWA;
    LNCRBALD LNCRBALD;
    LNRBALD LNRBALDA;
    public void MP(SCCGWA SCCGWA, LNCRBALD LNCRBALD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCRBALD = LNCRBALD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZRBALD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        LNRBALDA = (LNRBALD) LNCRBALD.REC_PTR;
        LNCRBALD.RETURN_INFO = 'F';
        IBS.init(SCCGWA, LNRBALD);
        IBS.CLONE(SCCGWA, LNRBALDA, LNRBALD);
        LNCRBALD.RC.RC_MMO = "LN";
        LNCRBALD.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (LNCRBALD.FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRBALD.FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRBALD.FUNC == 'R') {
            B030_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRBALD.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRBALD.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRBALD.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCRBALD.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, LNRBALD, LNRBALDA);
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
        if (LNCRBALD.OPT == 'S') {
            B060_10_START_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (LNCRBALD.OPT == 'A') {
            B060_20_STBR_BY_KEY_PROC();
            if (pgmRtn) return;
        } else if (LNCRBALD.OPT == 'R') {
            B060_30_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (LNCRBALD.OPT == 'E') {
            B060_50_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + LNCRBALD.OPT + ")";
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
    public void B060_50_END_BROWSE_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_PROC();
        if (pgmRtn) return;
    }
    public void T000_READ_REC_PROC() throws IOException,SQLException,Exception {
        LNTBALD_RD = new DBParm();
        LNTBALD_RD.TableName = "LNTBALD";
        IBS.READ(SCCGWA, LNRBALD, LNTBALD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_BALD_NOTFND, LNCRBALD.RC);
            LNCRBALD.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRBALD.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRBALD.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRBALD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRBALD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTBALD_RD = new DBParm();
        LNTBALD_RD.TableName = "LNTBALD";
        LNTBALD_RD.errhdl = true;
        IBS.WRITE(SCCGWA, LNRBALD, LNTBALD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY, LNCRBALD.RC);
            LNCRBALD.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        LNTBALD_RD = new DBParm();
        LNTBALD_RD.TableName = "LNTBALD";
        LNTBALD_RD.upd = true;
        IBS.READ(SCCGWA, LNRBALD, LNTBALD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_BALD_NOTFND, LNCRBALD.RC);
            LNCRBALD.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRBALD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRBALD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTBALD_RD = new DBParm();
        LNTBALD_RD.TableName = "LNTBALD";
        IBS.REWRITE(SCCGWA, LNRBALD, LNTBALD_RD);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        LNTBALD_RD = new DBParm();
        LNTBALD_RD.TableName = "LNTBALD";
        IBS.DELETE(SCCGWA, LNRBALD, LNTBALD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_BALD_NOTFND, LNCRBALD.RC);
            LNCRBALD.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_PROC() throws IOException,SQLException,Exception {
        LNTBALD_BR.rp = new DBParm();
        LNTBALD_BR.rp.TableName = "LNTBALD";
        LNTBALD_BR.rp.order = "CONTRACT_NO,SUB_CTA_NO";
        IBS.STARTBR(SCCGWA, LNRBALD, LNTBALD_BR);
    }
    public void T000_STARTBR_BY_KEY_PROC() throws IOException,SQLException,Exception {
        LNTBALD_BR.rp = new DBParm();
        LNTBALD_BR.rp.TableName = "LNTBALD";
        LNTBALD_BR.rp.eqWhere = "CONTRACT_NO";
        LNTBALD_BR.rp.where = "SUB_CTA_NO >= :LNRBALD.KEY.SUB_CTA_NO "
            + "AND CTNR_NO >= :LNRBALD.KEY.CTNR_NO "
            + "AND TXN_AC_DT >= :LNRBALD.KEY.TXN_AC_DT "
            + "AND JRN_NO >= :LNRBALD.KEY.JRN_NO "
            + "AND TXN_VAL_DT >= :LNRBALD.KEY.TXN_VAL_DT";
        LNTBALD_BR.rp.order = "CONTRACT_NO,SUB_CTA_NO,CTNR_NO,TXN_AC_DT, JRN_NO,TXN_VAL_DT";
        IBS.STARTBR(SCCGWA, LNRBALD, this, LNTBALD_BR);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRBALD, this, LNTBALD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_BALD_NOTFND, LNCRBALD.RC);
            LNCRBALD.RETURN_INFO = 'E';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTBALD_BR);
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
