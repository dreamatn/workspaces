package com.hisun.VT;

import com.hisun.BP.*;
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

public class VTZRPRDR {
    DBParm VTTPRDR_RD;
    brParm VTTPRDR_BR = new brParm();
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_TSQ_LEN = 0;
    char WS_FND_FLG = ' ';
    char WS_QFND_FLG = ' ';
    String WS_PROD_CODE = " ";
    String WS_CODE = " ";
    char WS_BILL_FLG = ' ';
    int WS_BR = 0;
    String WS_CCY = " ";
    String WS_CNTR_TYPE = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    VTCMSG_ERROR_MSG VTCMSG_ERROR_MSG = new VTCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    VTRPRDR VTRPRDR = new VTRPRDR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    VTCRPRDR VTCRPRDR;
    VTRPRDR VTRLPRDR;
    public void MP(SCCGWA SCCGWA, VTCRPRDR VTCRPRDR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.VTCRPRDR = VTCRPRDR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "VTZRPRDR return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, VTCRPRDR.RC);
        VTRLPRDR = (VTRPRDR) VTCRPRDR.POINTER;
        IBS.init(SCCGWA, VTRPRDR);
        VTCRPRDR.REC_LEN = 179;
        IBS.CLONE(SCCGWA, VTRLPRDR, VTRPRDR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (VTCRPRDR.FUNC == 'Q') {
            B010_QUERY_RECORD_PROC();
        } else if (VTCRPRDR.FUNC == 'B') {
            B020_BROWSE_RECORD_PROC();
        } else if (VTCRPRDR.FUNC == 'C') {
            B030_CREATE_PROC();
        } else if (VTCRPRDR.FUNC == 'R') {
            B040_READUPD_RECORD_PROC();
        } else if (VTCRPRDR.FUNC == 'U') {
            B050_UPDATE_RECORD_PROC();
        } else if (VTCRPRDR.FUNC == 'D') {
            B060_DELETE_RECORD_PROC();
        } else {
        }
        IBS.CLONE(SCCGWA, VTRPRDR, VTRLPRDR);
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (VTCRPRDR.OPT == 'S') {
            T000_STARTBR_VTTPRDR();
        } else if (VTCRPRDR.OPT == 'C') {
            T000_STARTBR_VTTPRDR_FOR_CODE();
        } else if (VTCRPRDR.OPT == 'W') {
            T000_STARTBR_VTTPRDR_CODE();
        } else if (VTCRPRDR.OPT == 'N') {
            T000_READNEXT_VTTPRDR();
        } else if (VTCRPRDR.OPT == 'E') {
            T000_ENDBR_VTTPRDR();
        } else {
        }
    }
    public void B030_CREATE_PROC() throws IOException,SQLException,Exception {
        VTTPRDR_RD = new DBParm();
        VTTPRDR_RD.TableName = "VTTPRDR";
        VTTPRDR_RD.errhdl = true;
        IBS.WRITE(SCCGWA, VTRPRDR, VTTPRDR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRPRDR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            VTCRPRDR.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTPRDR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void B050_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        VTTPRDR_RD = new DBParm();
        VTTPRDR_RD.TableName = "VTTPRDR";
        IBS.REWRITE(SCCGWA, VTRPRDR, VTTPRDR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRPRDR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRPRDR.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTPRDR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void B040_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        VTTPRDR_RD = new DBParm();
        VTTPRDR_RD.TableName = "VTTPRDR";
        VTTPRDR_RD.upd = true;
        IBS.READ(SCCGWA, VTRPRDR, VTTPRDR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRPRDR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRPRDR.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTPRDR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void B010_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        VTTPRDR_RD = new DBParm();
        VTTPRDR_RD.TableName = "VTTPRDR";
        IBS.READ(SCCGWA, VTRPRDR, VTTPRDR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRPRDR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRPRDR.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTPRDR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void B060_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        VTTPRDR_RD = new DBParm();
        VTTPRDR_RD.TableName = "VTTPRDR";
        IBS.DELETE(SCCGWA, VTRPRDR, VTTPRDR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRPRDR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            VTCRPRDR.RETURN_INFO = 'D';
        } else {
        }
    }
    public void T000_STARTBR_VTTPRDR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, VTRPRDR.KEY.CODE);
        CEP.TRC(SCCGWA, VTRPRDR.KEY.PROD_CD);
        CEP.TRC(SCCGWA, VTRPRDR.BILL_FLG);
        CEP.TRC(SCCGWA, VTRPRDR.KEY.CNTR_TYPE);
        WS_PROD_CODE = VTRPRDR.KEY.PROD_CD;
        WS_CODE = VTRPRDR.KEY.CODE;
        WS_BILL_FLG = VTRPRDR.BILL_FLG;
        WS_CNTR_TYPE = VTRPRDR.KEY.CNTR_TYPE;
        VTTPRDR_BR.rp = new DBParm();
        VTTPRDR_BR.rp.TableName = "VTTPRDR";
        VTTPRDR_BR.rp.where = "( 0 = :WS_PROD_CODE "
            + "OR PROD_CD LIKE :WS_PROD_CODE ) "
            + "AND ( ' ' = :WS_CODE "
            + "OR CODE = :WS_CODE ) "
            + "AND ( ' ' = :WS_BILL_FLG "
            + "OR BILL_FLG = :WS_BILL_FLG ) "
            + "AND ( ' ' = :WS_CNTR_TYPE "
            + "OR CNTR_TYPE = :WS_CNTR_TYPE )";
        VTTPRDR_BR.rp.order = "CODE,EFF_DATE";
        IBS.STARTBR(SCCGWA, VTRPRDR, this, VTTPRDR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRPRDR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRPRDR.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTPRDR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_STARTBR_VTTPRDR_FOR_CODE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, VTRPRDR.KEY.PROD_CD);
        CEP.TRC(SCCGWA, VTRPRDR.KEY.BR);
        CEP.TRC(SCCGWA, VTRPRDR.KEY.CCY);
        WS_PROD_CODE = VTRPRDR.KEY.PROD_CD;
        WS_BR = VTRPRDR.KEY.BR;
        WS_CCY = VTRPRDR.KEY.CCY;
        WS_CNTR_TYPE = VTRPRDR.KEY.CNTR_TYPE;
        VTTPRDR_RD = new DBParm();
        VTTPRDR_RD.TableName = "VTTPRDR";
        VTTPRDR_RD.where = "PROD_CD = :WS_PROD_CODE "
            + "AND ( ' ' = :WS_CNTR_TYPE "
            + "OR CNTR_TYPE = :WS_CNTR_TYPE ) "
            + "AND BR = :WS_BR "
            + "AND ( CCY = :WS_CCY "
            + "OR CCY = '999' ) "
            + "AND STS = 'N'";
        VTTPRDR_RD.fst = true;
        IBS.READ(SCCGWA, VTRPRDR, this, VTTPRDR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRPRDR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRPRDR.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTPRDR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_READNEXT_VTTPRDR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, VTRPRDR, this, VTTPRDR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRPRDR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRPRDR.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTPRDR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
        }
    }
    public void T000_STARTBR_VTTPRDR_CODE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, VTRPRDR.KEY.CODE);
        WS_CODE = VTRPRDR.KEY.CODE;
        VTTPRDR_BR.rp = new DBParm();
        VTTPRDR_BR.rp.TableName = "VTTPRDR";
        VTTPRDR_BR.rp.where = "CODE = :WS_CODE";
        IBS.STARTBR(SCCGWA, VTRPRDR, this, VTTPRDR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRPRDR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRPRDR.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTPRDR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_ENDBR_VTTPRDR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, VTTPRDR_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
