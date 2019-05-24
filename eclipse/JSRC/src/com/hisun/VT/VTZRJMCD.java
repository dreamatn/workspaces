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

public class VTZRJMCD {
    DBParm VTTJMCD_RD;
    brParm VTTJMCD_BR = new brParm();
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_TSQ_LEN = 0;
    char WS_FND_FLG = ' ';
    char WS_QFND_FLG = ' ';
    String WS_CODE = " ";
    VTCMSG_ERROR_MSG VTCMSG_ERROR_MSG = new VTCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    VTRJMCD VTRJMCD = new VTRJMCD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    VTCRJMCD VTCRJMCD;
    VTRJMCD VTRLMCD;
    public void MP(SCCGWA SCCGWA, VTCRJMCD VTCRJMCD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.VTCRJMCD = VTCRJMCD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "VTZRJMCD return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, VTCRJMCD.RC);
        VTRLMCD = (VTRJMCD) VTCRJMCD.POINTER;
        IBS.init(SCCGWA, VTRJMCD);
        VTCRJMCD.REC_LEN = 221;
        IBS.CLONE(SCCGWA, VTRLMCD, VTRJMCD);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (VTCRJMCD.FUNC == 'Q') {
            B010_QUERY_RECORD_PROC();
        } else if (VTCRJMCD.FUNC == 'B') {
            B020_BROWSE_RECORD_PROC();
        } else if (VTCRJMCD.FUNC == 'C') {
            B030_CREATE_PROC();
        } else if (VTCRJMCD.FUNC == 'R') {
            B040_READUPD_RECORD_PROC();
        } else if (VTCRJMCD.FUNC == 'U') {
            B050_UPDATE_RECORD_PROC();
        } else if (VTCRJMCD.FUNC == 'D') {
            B060_DELETE_RECORD_PROC();
        } else {
        }
        IBS.CLONE(SCCGWA, VTRJMCD, VTRLMCD);
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (VTCRJMCD.OPT == 'S') {
            T000_STARTBR_VTTJMCD();
        } else if (VTCRJMCD.OPT == 'N') {
            T000_READNEXT_VTTJMCD();
        } else if (VTCRJMCD.OPT == 'E') {
            T000_ENDBR_VTTJMCD();
        } else {
        }
    }
    public void B030_CREATE_PROC() throws IOException,SQLException,Exception {
        VTTJMCD_RD = new DBParm();
        VTTJMCD_RD.TableName = "VTTJMCD";
        IBS.WRITE(SCCGWA, VTRJMCD, VTTJMCD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRJMCD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            VTCRJMCD.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTJMCD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void B050_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        VTTJMCD_RD = new DBParm();
        VTTJMCD_RD.TableName = "VTTJMCD";
        IBS.REWRITE(SCCGWA, VTRJMCD, VTTJMCD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRJMCD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRJMCD.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTJMCD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void B040_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        VTTJMCD_RD = new DBParm();
        VTTJMCD_RD.TableName = "VTTJMCD";
        VTTJMCD_RD.where = "JM_CODE = :VTRJMCD.KEY.JM_CODE";
        VTTJMCD_RD.upd = true;
        IBS.READ(SCCGWA, VTRJMCD, this, VTTJMCD_RD);
        CEP.TRC(SCCGWA, VTCRJMCD.RETURN_INFO);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRJMCD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRJMCD.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTJMCD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void B010_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        VTTJMCD_RD = new DBParm();
        VTTJMCD_RD.TableName = "VTTJMCD";
        VTTJMCD_RD.where = "JM_CODE = :VTRJMCD.KEY.JM_CODE";
        IBS.READ(SCCGWA, VTRJMCD, this, VTTJMCD_RD);
        CEP.TRC(SCCGWA, VTCRJMCD.RETURN_INFO);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRJMCD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRJMCD.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTJMCD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void B060_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, VTRJMCD.KEY.JM_CODE);
        VTTJMCD_RD = new DBParm();
        VTTJMCD_RD.TableName = "VTTJMCD";
        IBS.DELETE(SCCGWA, VTRJMCD, VTTJMCD_RD);
    }
    public void T000_STARTBR_VTTJMCD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, VTRJMCD.KEY.JM_CODE);
        WS_CODE = VTRJMCD.KEY.JM_CODE;
        CEP.TRC(SCCGWA, WS_CODE);
        VTTJMCD_BR.rp = new DBParm();
        VTTJMCD_BR.rp.TableName = "VTTJMCD";
        VTTJMCD_BR.rp.where = "( 0 = :WS_CODE "
            + "OR JM_CODE LIKE :WS_CODE )";
        IBS.STARTBR(SCCGWA, VTRJMCD, this, VTTJMCD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRJMCD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRJMCD.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTJMCD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_READNEXT_VTTJMCD() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, VTRJMCD, this, VTTJMCD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRJMCD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRJMCD.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTJMCD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
        }
    }
    public void T000_ENDBR_VTTJMCD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, VTTJMCD_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
