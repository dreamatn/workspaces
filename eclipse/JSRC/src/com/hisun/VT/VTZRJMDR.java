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

public class VTZRJMDR {
    DBParm VTTJMDR_RD;
    brParm VTTJMDR_BR = new brParm();
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_TSQ_LEN = 0;
    char WS_FND_FLG = ' ';
    char WS_QFND_FLG = ' ';
    String WS_PROD_CODE = " ";
    int WS_BR = 0;
    String WS_CCY = " ";
    String WS_OTH = " ";
    int WS_EFF_DATE = 0;
    int WS_EXP_DATE = 0;
    String WS_JM_CODE = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    VTCMSG_ERROR_MSG VTCMSG_ERROR_MSG = new VTCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    VTRJMDR VTRJMDR = new VTRJMDR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    VTCRJMDR VTCRJMDR;
    VTRJMDR VTRLMDR;
    public void MP(SCCGWA SCCGWA, VTCRJMDR VTCRJMDR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.VTCRJMDR = VTCRJMDR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "VTZRJMDR return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, VTCRJMDR.RC);
        VTRLMDR = (VTRJMDR) VTCRJMDR.POINTER;
        IBS.init(SCCGWA, VTRJMDR);
        VTCRJMDR.REC_LEN = 218;
        IBS.CLONE(SCCGWA, VTRLMDR, VTRJMDR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (VTCRJMDR.FUNC == 'Q') {
            B010_QUERY_RECORD_PROC();
        } else if (VTCRJMDR.FUNC == 'B') {
            B020_BROWSE_RECORD_PROC();
        } else if (VTCRJMDR.FUNC == 'C') {
            B030_CREATE_PROC();
        } else if (VTCRJMDR.FUNC == 'R') {
            B040_READUPD_RECORD_PROC();
        } else if (VTCRJMDR.FUNC == 'U') {
            B050_UPDATE_RECORD_PROC();
        } else if (VTCRJMDR.FUNC == 'D') {
            B060_DELETE_RECORD_PROC();
        } else {
        }
        IBS.CLONE(SCCGWA, VTRJMDR, VTRLMDR);
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (VTCRJMDR.OPT == 'S') {
            T000_STARTBR_VTTJMDR();
        } else if (VTCRJMDR.OPT == 'N') {
            T000_READNEXT_VTTJMDR();
        } else if (VTCRJMDR.OPT == 'E') {
            T000_ENDBR_VTTJMDR();
        } else {
        }
    }
    public void B030_CREATE_PROC() throws IOException,SQLException,Exception {
        VTTJMDR_RD = new DBParm();
        VTTJMDR_RD.TableName = "VTTJMDR";
        VTTJMDR_RD.errhdl = true;
        IBS.WRITE(SCCGWA, VTRJMDR, VTTJMDR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRJMDR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            VTCRJMDR.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTJMDR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void B050_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        VTTJMDR_RD = new DBParm();
        VTTJMDR_RD.TableName = "VTTJMDR";
        IBS.REWRITE(SCCGWA, VTRJMDR, VTTJMDR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRJMDR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRJMDR.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTJMDR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void B040_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        VTTJMDR_RD = new DBParm();
        VTTJMDR_RD.TableName = "VTTJMDR";
        VTTJMDR_RD.eqWhere = "PROD_CD,BR,CCY,OTH,EFF_DATE";
        VTTJMDR_RD.upd = true;
        IBS.READ(SCCGWA, VTRJMDR, VTTJMDR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRJMDR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRJMDR.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTJMDR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void B010_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        VTTJMDR_RD = new DBParm();
        VTTJMDR_RD.TableName = "VTTJMDR";
        IBS.READ(SCCGWA, VTRJMDR, VTTJMDR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRJMDR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRJMDR.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTJMDR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void B060_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        VTTJMDR_RD = new DBParm();
        VTTJMDR_RD.TableName = "VTTJMDR";
        IBS.DELETE(SCCGWA, VTRJMDR, VTTJMDR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRJMDR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            VTCRJMDR.RETURN_INFO = 'D';
        } else {
        }
    }
    public void T000_STARTBR_VTTJMDR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, VTRJMDR.JM_CODE);
        CEP.TRC(SCCGWA, VTRJMDR.KEY.PROD_CD);
        WS_PROD_CODE = VTRJMDR.KEY.PROD_CD;
        WS_JM_CODE = VTRJMDR.JM_CODE;
        VTTJMDR_BR.rp = new DBParm();
        VTTJMDR_BR.rp.TableName = "VTTJMDR";
        VTTJMDR_BR.rp.where = "( 0 = :WS_PROD_CODE "
            + "OR PROD_CD LIKE :WS_PROD_CODE ) "
            + "AND ( ' ' = :WS_JM_CODE "
            + "OR JM_CODE = :WS_JM_CODE )";
        IBS.STARTBR(SCCGWA, VTRJMDR, this, VTTJMDR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRJMDR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRJMDR.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTJMDR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_READNEXT_VTTJMDR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, VTRJMDR, this, VTTJMDR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRJMDR.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRJMDR.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTJMDR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
        }
    }
    public void T000_ENDBR_VTTJMDR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, VTTJMDR_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
