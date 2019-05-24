package com.hisun.FX;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class FXZRBFXT {
    DBParm FXTBFXT_RD;
    brParm FXTBFXT_BR = new brParm();
    boolean pgmRtn = false;
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    FXRBFXT FXRBFXT = new FXRBFXT();
    String WS_TIK_NO_LOW = " ";
    String WS_TIK_NO_HIGH = " ";
    String WS_TIK_NO = " ";
    int WS_ORDER_BR_LOW = 0;
    int WS_ORDER_BR_HIGH = 0;
    char WS_STATUS = ' ';
    String WS_CI_NO = " ";
    String WS_ID_TYP = " ";
    String WS_ID_NO = " ";
    int WS_ORDER_BR = 0;
    SCCGWA SCCGWA;
    FXCRBFXT FXCRBFXT;
    FXRBFXT FXRLBFXT;
    public void MP(SCCGWA SCCGWA, FXCRBFXT FXCRBFXT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.FXCRBFXT = FXCRBFXT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "FXZRBFXT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        FXRLBFXT = (FXRBFXT) FXCRBFXT.REC_PTR;
        IBS.init(SCCGWA, FXRBFXT);
        IBS.CLONE(SCCGWA, FXRLBFXT, FXRBFXT);
        FXCRBFXT.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        FXCRBFXT.RC.RC_CODE = 0;
        CEP.TRC(SCCGWA, FXRBFXT.ORDER_BR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, FXCRBFXT.FUNC);
        CEP.TRC(SCCGWA, FXRBFXT);
        if (FXCRBFXT.FUNC == 'I') {
            B001_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (FXCRBFXT.FUNC == 'A') {
            B002_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (FXCRBFXT.FUNC == 'R') {
            B003_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (FXCRBFXT.FUNC == 'U') {
            B004_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (FXCRBFXT.FUNC == 'D') {
            B005_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (FXCRBFXT.FUNC == 'B') {
            B006_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + FXCRBFXT.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, FXRBFXT, FXRLBFXT);
        CEP.TRC(SCCGWA, FXCRBFXT.RETURN_INFO);
    }
    public void B001_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_REC_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            FXCRBFXT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            FXCRBFXT.RETURN_INFO = 'N';
        } else {
            FXCRBFXT.RETURN_INFO = 'O';
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, FXCRBFXT.RETURN_INFO);
    }
    public void B002_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "ADD REC PROC");
        T000_WRITE_REC_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            FXCRBFXT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            FXCRBFXT.RETURN_INFO = 'D';
        } else {
            FXCRBFXT.RETURN_INFO = 'O';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B003_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_REC_UPD_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            FXCRBFXT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            FXCRBFXT.RETURN_INFO = 'N';
        } else {
            FXCRBFXT.RETURN_INFO = 'O';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B004_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_REC_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            FXCRBFXT.RETURN_INFO = 'F';
        } else {
            FXCRBFXT.RETURN_INFO = 'O';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B005_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_REC_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            FXCRBFXT.RETURN_INFO = 'F';
        } else {
            FXCRBFXT.RETURN_INFO = 'O';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B006_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (FXCRBFXT.OPT == 'B') {
            B060_10_STBR_BY_TIK_STS();
            if (pgmRtn) return;
        } else if (FXCRBFXT.OPT == 'O') {
            B060_10_STBR_OUTSTANDING();
            if (pgmRtn) return;
        } else if (FXCRBFXT.OPT == 'R') {
            B060_20_READ_NEXTPROC();
            if (pgmRtn) return;
        } else if (FXCRBFXT.OPT == 'E') {
            B060_30_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID OPT(" + FXCRBFXT.OPT + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B060_10_STBR_BY_TIK_STS() throws IOException,SQLException,Exception {
        T000_STBR_BY_TIK_STS_PROC();
        if (pgmRtn) return;
    }
    public void B060_10_STBR_OUTSTANDING() throws IOException,SQLException,Exception {
        T000_STBR_OUTSTANDING();
        if (pgmRtn) return;
    }
    public void B060_20_READ_NEXTPROC() throws IOException,SQLException,Exception {
        T000_READNEXT_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            FXCRBFXT.RETURN_INFO = 'E';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B060_30_END_BROWSE_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_PROC();
        if (pgmRtn) return;
    }
    public void T000_READ_REC_PROC() throws IOException,SQLException,Exception {
        FXTBFXT_RD = new DBParm();
        FXTBFXT_RD.TableName = "FXTBFXT";
        IBS.READ(SCCGWA, FXRBFXT, FXTBFXT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READ_REC_UPD_PROC() throws IOException,SQLException,Exception {
        FXTBFXT_RD = new DBParm();
        FXTBFXT_RD.TableName = "FXTBFXT";
        FXTBFXT_RD.upd = true;
        IBS.READ(SCCGWA, FXRBFXT, FXTBFXT_RD);
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        FXTBFXT_RD = new DBParm();
        FXTBFXT_RD.TableName = "FXTBFXT";
        IBS.REWRITE(SCCGWA, FXRBFXT, FXTBFXT_RD);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        FXTBFXT_RD = new DBParm();
        FXTBFXT_RD.TableName = "FXTBFXT";
        IBS.DELETE(SCCGWA, FXRBFXT, FXTBFXT_RD);
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        FXTBFXT_RD = new DBParm();
        FXTBFXT_RD.TableName = "FXTBFXT";
        IBS.WRITE(SCCGWA, FXRBFXT, FXTBFXT_RD);
    }
    public void T000_STBR_BY_TIK_STS_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, FXRBFXT.KEY.TIK_NO);
        CEP.TRC(SCCGWA, FXRBFXT.STATUS);
        CEP.TRC(SCCGWA, FXRBFXT.CI_NO);
        CEP.TRC(SCCGWA, FXRBFXT.TRA_AC);
        CEP.TRC(SCCGWA, FXRBFXT.PROD_CD);
        CEP.TRC(SCCGWA, FXRBFXT.CI_CNM);
        CEP.TRC(SCCGWA, FXRBFXT.CI_ENM);
        CEP.TRC(SCCGWA, FXRBFXT.ID_TYP);
        CEP.TRC(SCCGWA, FXRBFXT.ID_NO);
        CEP.TRC(SCCGWA, FXRBFXT.CRE_DT);
        CEP.TRC(SCCGWA, FXRBFXT.SUP1_DT);
        CEP.TRC(SCCGWA, FXRBFXT.ORDER_BR);
        FXTBFXT_BR.rp = new DBParm();
        FXTBFXT_BR.rp.TableName = "FXTBFXT";
        FXTBFXT_BR.rp.where = "( :FXRBFXT.KEY.TIK_NO = ' ' "
            + "OR TIK_NO = :FXRBFXT.KEY.TIK_NO ) "
            + "AND ( :FXRBFXT.STATUS = ' ' "
            + "OR STATUS = :FXRBFXT.STATUS ) "
            + "AND ( :FXRBFXT.CI_NO = ' ' "
            + "OR CI_NO = :FXRBFXT.CI_NO ) "
            + "AND ( :FXRBFXT.ID_TYP = ' ' "
            + "OR ID_TYP = :FXRBFXT.ID_TYP ) "
            + "AND ( :FXRBFXT.ID_NO = ' ' "
            + "OR ID_NO = :FXRBFXT.ID_NO ) "
            + "AND ( :FXRBFXT.TRA_AC = ' ' "
            + "OR TRA_AC = :FXRBFXT.TRA_AC ) "
            + "AND ( :FXRBFXT.PROD_CD = ' ' "
            + "OR PROD_CD = :FXRBFXT.PROD_CD ) "
            + "AND ( :FXRBFXT.CRE_DT = 0 "
            + "OR CRE_DT = :FXRBFXT.CRE_DT ) "
            + "AND ( :FXRBFXT.SUP1_DT = 0 "
            + "OR SUP1_DT = :FXRBFXT.SUP1_DT ) "
            + "AND ( :FXRBFXT.ORDER_BR = 0 "
            + "OR ORDER_BR = :FXRBFXT.ORDER_BR )";
        FXTBFXT_BR.rp.order = "TIK_NO";
        IBS.STARTBR(SCCGWA, FXRBFXT, this, FXTBFXT_BR);
    }
    public void T000_STBR_OUTSTANDING() throws IOException,SQLException,Exception {
        FXTBFXT_BR.rp = new DBParm();
        FXTBFXT_BR.rp.TableName = "FXTBFXT";
        FXTBFXT_BR.rp.where = "STATUS LIKE 'P' "
            + "OR STATUS LIKE 'N'";
        IBS.STARTBR(SCCGWA, FXRBFXT, this, FXTBFXT_BR);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, FXRBFXT, this, FXTBFXT_BR);
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, FXTBFXT_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
