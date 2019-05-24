package com.hisun.SO;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCENPSW;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.DB.*;

import java.io.IOException;
import java.sql.SQLException;

public class SOZATRM {
    DBParm SOTTERM_RD;
    String WS_MSGID = " ";
    SOCMSG SOCMSG = new SOCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCENPSW SCCENPSW = new SCCENPSW();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SORTERM SORTERM = new SORTERM();
    short WS_TRM_BANK_NO = 0;
    String WS_TRM_ID = " ";
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    SOCATRM SOCATRM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SORTERM SORTRM;
    SORSYS SORSYS;
    public void MP(SCCGWA SCCGWA, SOCATRM SOCATRM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.SOCATRM = SOCATRM;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        B00_CHECK_PROC();
        C00_MAIN_PROC();
        D00_OUTP_PROC();
        CEP.TRC(SCCGWA, "SOZATRM return!");
        Z_RET();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SORSYS = (SORSYS) GWA_SC_AREA.OPER_PTR;
        SOCATRM.RC.RC_CODE = 0;
        SORTRM = (SORTERM) SOCATRM.DATA_PTR;
        IBS.CLONE(SCCGWA, SORTRM, SORTERM);
        SORTERM.KEY.BANK_NO = SORSYS.BANK_NO;
    }
    public void B00_CHECK_PROC() throws IOException,SQLException,Exception {
        if (SORTERM.KEY.ID.trim().length() == 0) {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "CALL SOCATRM PARM ERROR !" + " TERM-ID IS " + SORTERM.KEY.ID;
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void C00_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SOCATRM.FUNC == '1') {
            C00_READ_ONLY_TRM();
        } else if (SOCATRM.FUNC == '2') {
            C00_READUPD_TRM();
        } else if (SOCATRM.FUNC == '3') {
            C00_REWRITE_TRM();
        } else if (SOCATRM.FUNC == '4') {
            C00_WRITE_TRM();
        } else if (SOCATRM.FUNC == '5') {
            C00_DELETE_TRM();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "CALL SOCATRM PARM ERROR !" + " ATRM-FUNC IS " + SOCATRM.FUNC;
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void C00_READ_ONLY_TRM() throws IOException,SQLException,Exception {
        SOTTERM_RD = new DBParm();
        SOTTERM_RD.TableName = "SOTTERM";
        IBS.READ(SCCGWA, SORTERM, SOTTERM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, SOCMSG.SO_TERM_NOTFND);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "SOTTERM";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void C00_READUPD_TRM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SORTERM.KEY.ID);
        SOTTERM_RD = new DBParm();
        SOTTERM_RD.TableName = "SOTTERM";
        SOTTERM_RD.upd = true;
        IBS.READ(SCCGWA, SORTERM, SOTTERM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, SOCMSG.SO_TERM_NOTFND);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "SOTTERM";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void C00_WRITE_TRM() throws IOException,SQLException,Exception {
        SOTTERM_RD = new DBParm();
        SOTTERM_RD.TableName = "SOTTERM";
        IBS.WRITE(SCCGWA, SORTERM, SOTTERM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "SOTTERM";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
        }
    }
    public void C00_REWRITE_TRM() throws IOException,SQLException,Exception {
        SOTTERM_RD = new DBParm();
        SOTTERM_RD.TableName = "SOTTERM";
        IBS.REWRITE(SCCGWA, SORTERM, SOTTERM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "SOTTERM";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            B_DB_EXCP();
        }
    }
    public void C00_DELETE_TRM() throws IOException,SQLException,Exception {
        SOTTERM_RD = new DBParm();
        SOTTERM_RD.TableName = "SOTTERM";
        IBS.DELETE(SCCGWA, SORTERM, SOTTERM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, SOCMSG.SO_TERM_NOTFND);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "SOTTERM";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "DELETE";
            B_DB_EXCP();
        }
    }
    public void D00_OUTP_PROC() throws IOException,SQLException,Exception {
        if (SOCATRM.FUNC == '1'
            || SOCATRM.FUNC == '2') {
            IBS.CLONE(SCCGWA, SORTERM, SORTRM);
        } else {
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
