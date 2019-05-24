package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZRBALH {
    DBParm LNTBALH_RD;
    brParm LNTBALH_BR = new brParm();
    boolean pgmRtn = false;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    LNRBALH LNRBALH = new LNRBALH();
    SCCGWA SCCGWA;
    LNCRBALH LNCRBALH;
    LNRBALH LNRBALHA;
    public void MP(SCCGWA SCCGWA, LNCRBALH LNCRBALH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCRBALH = LNCRBALH;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZRBALH return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        LNRBALHA = (LNRBALH) LNCRBALH.REC_PTR;
        LNCRBALH.RETURN_INFO = 'F';
        IBS.init(SCCGWA, LNRBALH);
        IBS.CLONE(SCCGWA, LNRBALHA, LNRBALH);
        LNCRBALH.RC.RC_MMO = "LN";
        LNCRBALH.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (LNCRBALH.FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRBALH.FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRBALH.FUNC == 'R') {
            B030_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRBALH.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRBALH.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCRBALH.FUNC == 'B') {
            B060_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCRBALH.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, LNRBALH, LNRBALHA);
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
        if (LNCRBALH.OPT == 'S') {
            B060_10_START_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (LNCRBALH.OPT == 'A') {
            B060_20_STBR_BY_KEY_PROC();
            if (pgmRtn) return;
        } else if (LNCRBALH.OPT == 'R') {
            B060_30_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (LNCRBALH.OPT == 'E') {
            B060_50_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + LNCRBALH.OPT + ")";
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
        LNTBALH_RD = new DBParm();
        LNTBALH_RD.TableName = "LNTBALH";
        IBS.READ(SCCGWA, LNRBALH, LNTBALH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_BALH_NOTFND, LNCRBALH.RC);
            LNCRBALH.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRBALH.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRBALH.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRBALH.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRBALH.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTBALH_RD = new DBParm();
        LNTBALH_RD.TableName = "LNTBALH";
        LNTBALH_RD.errhdl = true;
        IBS.WRITE(SCCGWA, LNRBALH, LNTBALH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY, LNCRBALH.RC);
            LNCRBALH.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        LNTBALH_RD = new DBParm();
        LNTBALH_RD.TableName = "LNTBALH";
        LNTBALH_RD.upd = true;
        IBS.READ(SCCGWA, LNRBALH, LNTBALH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_BALH_NOTFND, LNCRBALH.RC);
            LNCRBALH.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRBALH.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRBALH.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTBALH_RD = new DBParm();
        LNTBALH_RD.TableName = "LNTBALH";
        IBS.REWRITE(SCCGWA, LNRBALH, LNTBALH_RD);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        LNTBALH_RD = new DBParm();
        LNTBALH_RD.TableName = "LNTBALH";
        IBS.DELETE(SCCGWA, LNRBALH, LNTBALH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_BALH_NOTFND, LNCRBALH.RC);
            LNCRBALH.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_PROC() throws IOException,SQLException,Exception {
        LNTBALH_BR.rp = new DBParm();
        LNTBALH_BR.rp.TableName = "LNTBALH";
        LNTBALH_BR.rp.order = "CONTRACT_NO,SUB_CTA_NO";
        IBS.STARTBR(SCCGWA, LNRBALH, LNTBALH_BR);
    }
    public void T000_STARTBR_BY_KEY_PROC() throws IOException,SQLException,Exception {
        LNTBALH_BR.rp = new DBParm();
        LNTBALH_BR.rp.TableName = "LNTBALH";
        LNTBALH_BR.rp.eqWhere = "CONTRACT_NO";
        LNTBALH_BR.rp.where = "SUB_CTA_NO >= :LNRBALH.KEY.SUB_CTA_NO "
            + "AND CTNR_NO >= :LNRBALH.KEY.CTNR_NO "
            + "AND TXN_DT >= :LNRBALH.KEY.TXN_DT";
        LNTBALH_BR.rp.order = "CONTRACT_NO,SUB_CTA_NO,CTNR_NO,TXN_DT";
        IBS.STARTBR(SCCGWA, LNRBALH, this, LNTBALH_BR);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRBALH, this, LNTBALH_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_BALH_NOTFND, LNCRBALH.RC);
            LNCRBALH.RETURN_INFO = 'E';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTBALH_BR);
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
