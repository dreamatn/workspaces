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

public class VTZRVTCD {
    DBParm VTTVTCD_RD;
    brParm VTTVTCD_BR = new brParm();
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
    VTRVTCD VTRVTCD = new VTRVTCD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    VTCRVTCD VTCRVTCD;
    VTRVTCD VTRLVTCD;
    public void MP(SCCGWA SCCGWA, VTCRVTCD VTCRVTCD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.VTCRVTCD = VTCRVTCD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "VTZRVTCD return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, VTCRVTCD.RC);
        VTRLVTCD = (VTRVTCD) VTCRVTCD.POINTER;
        IBS.init(SCCGWA, VTRVTCD);
        VTCRVTCD.REC_LEN = 206;
        IBS.CLONE(SCCGWA, VTRLVTCD, VTRVTCD);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (VTCRVTCD.FUNC == 'Q') {
            B010_QUERY_RECORD_PROC();
        } else if (VTCRVTCD.FUNC == 'B') {
            B020_BROWSE_RECORD_PROC();
        } else if (VTCRVTCD.FUNC == 'C') {
            B030_CREATE_PROC();
        } else if (VTCRVTCD.FUNC == 'R') {
            B040_READUPD_RECORD_PROC();
        } else if (VTCRVTCD.FUNC == 'U') {
            B050_UPDATE_RECORD_PROC();
        } else if (VTCRVTCD.FUNC == 'D') {
            B060_DELETE_RECORD_PROC();
        } else {
        }
        IBS.CLONE(SCCGWA, VTRVTCD, VTRLVTCD);
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (VTCRVTCD.OPT == 'S') {
            T000_STARTBR_VTTVTCD();
        } else if (VTCRVTCD.OPT == 'N') {
            T000_READNEXT_VTTVTCD();
        } else if (VTCRVTCD.OPT == 'E') {
            T000_ENDBR_VTTVTCD();
        } else {
        }
    }
    public void B030_CREATE_PROC() throws IOException,SQLException,Exception {
        VTTVTCD_RD = new DBParm();
        VTTVTCD_RD.TableName = "VTTVTCD";
        IBS.WRITE(SCCGWA, VTRVTCD, VTTVTCD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRVTCD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            VTCRVTCD.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTVTCD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void B050_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        VTTVTCD_RD = new DBParm();
        VTTVTCD_RD.TableName = "VTTVTCD";
        IBS.REWRITE(SCCGWA, VTRVTCD, VTTVTCD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRVTCD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRVTCD.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTVTCD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void B040_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        VTTVTCD_RD = new DBParm();
        VTTVTCD_RD.TableName = "VTTVTCD";
        VTTVTCD_RD.upd = true;
        IBS.READ(SCCGWA, VTRVTCD, VTTVTCD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRVTCD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRVTCD.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTVTCD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void B010_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        VTTVTCD_RD = new DBParm();
        VTTVTCD_RD.TableName = "VTTVTCD";
        IBS.READ(SCCGWA, VTRVTCD, VTTVTCD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRVTCD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRVTCD.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTVTCD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void B060_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, VTRVTCD.KEY.CODE);
        VTTVTCD_RD = new DBParm();
        VTTVTCD_RD.TableName = "VTTVTCD";
        IBS.DELETE(SCCGWA, VTRVTCD, VTTVTCD_RD);
    }
    public void T000_STARTBR_VTTVTCD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, VTRVTCD.KEY.CODE);
        WS_CODE = VTRVTCD.KEY.CODE;
        CEP.TRC(SCCGWA, WS_CODE);
        VTTVTCD_BR.rp = new DBParm();
        VTTVTCD_BR.rp.TableName = "VTTVTCD";
        VTTVTCD_BR.rp.where = "( 0 = :WS_CODE "
            + "OR CODE = :WS_CODE )";
        VTTVTCD_BR.rp.order = "CODE,EFF_DATE";
        IBS.STARTBR(SCCGWA, VTRVTCD, this, VTTVTCD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRVTCD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRVTCD.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTVTCD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_READNEXT_VTTVTCD() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, VTRVTCD, this, VTTVTCD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRVTCD.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRVTCD.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTVTCD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
        }
    }
    public void T000_ENDBR_VTTVTCD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, VTTVTCD_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
