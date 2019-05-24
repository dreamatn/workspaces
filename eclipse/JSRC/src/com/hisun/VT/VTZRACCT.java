package com.hisun.VT;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCSUBS;

import java.io.IOException;
import java.sql.SQLException;

public class VTZRACCT {
    DBParm VTTACCT_RD;
    brParm VTTACCT_BR = new brParm();
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_TSQ_LEN = 0;
    char WS_FND_FLG = ' ';
    char WS_QFND_FLG = ' ';
    char WS_BILL_FLG = ' ';
    String WS_CODE = " ";
    char WS_TAX_FLG = ' ';
    char WS_TAX_TYPE = ' ';
    VTCMSG_ERROR_MSG VTCMSG_ERROR_MSG = new VTCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    VTRACCT VTRACCT = new VTRACCT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    VTCRACCT VTCRACCT;
    VTRACCT VTRLCCT;
    public void MP(SCCGWA SCCGWA, VTCRACCT VTCRACCT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.VTCRACCT = VTCRACCT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "VTZRACCT return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, VTCRACCT.RC);
        VTRLCCT = (VTRACCT) VTCRACCT.POINTER;
        IBS.init(SCCGWA, VTRACCT);
        VTCRACCT.REC_LEN = 133;
        IBS.CLONE(SCCGWA, VTRLCCT, VTRACCT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (VTCRACCT.FUNC == 'Q') {
            B010_QUERY_RECORD_PROC();
        } else if (VTCRACCT.FUNC == 'B') {
            B020_BROWSE_RECORD_PROC();
        } else if (VTCRACCT.FUNC == 'C') {
            B030_CREATE_PROC();
        } else if (VTCRACCT.FUNC == 'R') {
            B040_READUPD_RECORD_PROC();
        } else if (VTCRACCT.FUNC == 'U') {
            B050_UPDATE_RECORD_PROC();
        } else if (VTCRACCT.FUNC == 'D') {
            B060_DELETE_RECORD_PROC();
        } else {
        }
        IBS.CLONE(SCCGWA, VTRACCT, VTRLCCT);
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (VTCRACCT.OPT == 'S') {
            T000_STARTBR_VTTACCT();
        } else if (VTCRACCT.OPT == 'C') {
            T000_STARTBR_CODE_VTTACCT();
        } else if (VTCRACCT.OPT == 'N') {
            T000_READNEXT_VTTACCT();
        } else if (VTCRACCT.OPT == 'E') {
            T000_ENDBR_VTTACCT();
        } else {
        }
    }
    public void B030_CREATE_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, VTRACCT.KEY.BILL_FLG);
        CEP.TRC(SCCGWA, VTRACCT.KEY.CODE);
        VTTACCT_RD = new DBParm();
        VTTACCT_RD.TableName = "VTTACCT";
        IBS.WRITE(SCCGWA, VTRACCT, VTTACCT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRACCT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            VTCRACCT.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTACCT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void B050_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        VTTACCT_RD = new DBParm();
        VTTACCT_RD.TableName = "VTTACCT";
        IBS.REWRITE(SCCGWA, VTRACCT, VTTACCT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRACCT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRACCT.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTACCT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void B040_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        VTTACCT_RD = new DBParm();
        VTTACCT_RD.TableName = "VTTACCT";
        VTTACCT_RD.upd = true;
        IBS.READ(SCCGWA, VTRACCT, VTTACCT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRACCT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRACCT.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTACCT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void B010_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        VTTACCT_RD = new DBParm();
        VTTACCT_RD.TableName = "VTTACCT";
        IBS.READ(SCCGWA, VTRACCT, VTTACCT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRACCT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRACCT.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTACCT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void B060_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        VTTACCT_RD = new DBParm();
        VTTACCT_RD.TableName = "VTTACCT";
        IBS.DELETE(SCCGWA, VTRACCT, VTTACCT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRACCT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            VTCRACCT.RETURN_INFO = 'D';
        } else {
        }
    }
    public void T000_STARTBR_VTTACCT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, VTRACCT.KEY.CODE);
        WS_CODE = VTRACCT.KEY.CODE;
        WS_BILL_FLG = VTRACCT.KEY.BILL_FLG;
        WS_TAX_FLG = VTRACCT.TAX_FLG;
        WS_TAX_TYPE = VTRACCT.TAX_TYPE;
        VTTACCT_BR.rp = new DBParm();
        VTTACCT_BR.rp.TableName = "VTTACCT";
        VTTACCT_BR.rp.where = "( 0 = :WS_CODE "
            + "OR CODE = :WS_CODE ) "
            + "AND ( ' ' = :WS_BILL_FLG "
            + "OR BILL_FLG = :WS_BILL_FLG ) "
            + "AND ( ' ' = :WS_TAX_FLG "
            + "OR TAX_FLG = :WS_TAX_FLG ) "
            + "AND ( ' ' = :WS_TAX_TYPE "
            + "OR TAX_TYPE = :WS_TAX_TYPE )";
        VTTACCT_BR.rp.order = "BILL_FLG,CODE";
        IBS.STARTBR(SCCGWA, VTRACCT, this, VTTACCT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRACCT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRACCT.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTACCT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_STARTBR_CODE_VTTACCT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, VTRACCT.KEY.CODE);
        WS_CODE = VTRACCT.KEY.CODE;
        CEP.TRC(SCCGWA, WS_CODE);
        VTTACCT_BR.rp = new DBParm();
        VTTACCT_BR.rp.TableName = "VTTACCT";
        VTTACCT_BR.rp.where = "CODE = :WS_CODE";
        IBS.STARTBR(SCCGWA, VTRACCT, this, VTTACCT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRACCT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRACCT.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTACCT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_READNEXT_VTTACCT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, VTRACCT, this, VTTACCT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRACCT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRACCT.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTACCT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
        }
    }
    public void T000_ENDBR_VTTACCT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, VTTACCT_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
