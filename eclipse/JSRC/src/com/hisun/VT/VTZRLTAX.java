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

public class VTZRLTAX {
    DBParm VTTLTAX_RD;
    brParm VTTLTAX_BR = new brParm();
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_TSQ_LEN = 0;
    char WS_FND_FLG = ' ';
    char WS_QFND_FLG = ' ';
    VTCMSG_ERROR_MSG VTCMSG_ERROR_MSG = new VTCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    VTRLTAX VTRLTAX = new VTRLTAX();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    VTCRLTAX VTCRLTAX;
    VTRLTAX VTRLLAX;
    public void MP(SCCGWA SCCGWA, VTCRLTAX VTCRLTAX) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.VTCRLTAX = VTCRLTAX;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "VTZRLTAX return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, VTCRLTAX.RC);
        VTRLLAX = (VTRLTAX) VTCRLTAX.POINTER;
        IBS.init(SCCGWA, VTRLTAX);
        VTCRLTAX.REC_LEN = 115;
        IBS.CLONE(SCCGWA, VTRLLAX, VTRLTAX);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (VTCRLTAX.FUNC == 'Q') {
            B010_QUERY_RECORD_PROC();
        } else if (VTCRLTAX.FUNC == 'B') {
            B020_BROWSE_RECORD_PROC();
        } else if (VTCRLTAX.FUNC == 'C') {
            B030_CREATE_PROC();
        } else if (VTCRLTAX.FUNC == 'R') {
            B040_READUPD_RECORD_PROC();
        } else if (VTCRLTAX.FUNC == 'U') {
            B050_UPDATE_RECORD_PROC();
        } else if (VTCRLTAX.FUNC == 'D') {
            B060_DELETE_RECORD_PROC();
        } else {
        }
        IBS.CLONE(SCCGWA, VTRLTAX, VTRLLAX);
    }
    public void B020_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (VTCRLTAX.OPT == 'S') {
            T000_STARTBR_VTTLTAX();
        } else if (VTCRLTAX.OPT == 'N') {
            T000_READNEXT_VTTLTAX();
        } else if (VTCRLTAX.OPT == 'E') {
            T000_ENDBR_VTTLTAX();
        } else {
        }
    }
    public void B030_CREATE_PROC() throws IOException,SQLException,Exception {
        VTTLTAX_RD = new DBParm();
        VTTLTAX_RD.TableName = "VTTLTAX";
        IBS.WRITE(SCCGWA, VTRLTAX, VTTLTAX_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRLTAX.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            VTCRLTAX.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTLTAX";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void B050_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        VTTLTAX_RD = new DBParm();
        VTTLTAX_RD.TableName = "VTTLTAX";
        IBS.REWRITE(SCCGWA, VTRLTAX, VTTLTAX_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRLTAX.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRLTAX.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTLTAX";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void B040_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        VTTLTAX_RD = new DBParm();
        VTTLTAX_RD.TableName = "VTTLTAX";
        VTTLTAX_RD.upd = true;
        IBS.READ(SCCGWA, VTRLTAX, VTTLTAX_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRLTAX.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRLTAX.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTLTAX";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void B010_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        VTTLTAX_RD = new DBParm();
        VTTLTAX_RD.TableName = "VTTLTAX";
        IBS.READ(SCCGWA, VTRLTAX, VTTLTAX_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRLTAX.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            VTCRLTAX.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTLTAX";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void B060_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        VTTLTAX_RD = new DBParm();
        VTTLTAX_RD.TableName = "VTTLTAX";
        IBS.DELETE(SCCGWA, VTRLTAX, VTTLTAX_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            VTCRLTAX.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            VTCRLTAX.RETURN_INFO = 'D';
        } else {
        }
    }
    public void T000_STARTBR_VTTLTAX() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, VTRLTAX.KEY.TR_DATE);
        VTTLTAX_BR.rp = new DBParm();
        VTTLTAX_BR.rp.TableName = "VTTLTAX";
        VTTLTAX_BR.rp.where = "TR_DATE <= :VTRLTAX.KEY.TR_DATE";
        VTTLTAX_BR.rp.order = "TR_DATE,SET_NO,SET_SEQ";
        IBS.STARTBR(SCCGWA, VTRLTAX, this, VTTLTAX_BR);
    }
    public void T000_READNEXT_VTTLTAX() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, VTRLTAX, this, VTTLTAX_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "111");
            VTCRLTAX.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "222");
            VTCRLTAX.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTLTAX";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
        }
    }
    public void T000_ENDBR_VTTLTAX() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, VTTLTAX_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
