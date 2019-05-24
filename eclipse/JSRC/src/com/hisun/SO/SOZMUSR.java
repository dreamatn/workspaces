package com.hisun.SO;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCENPSW;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.BP.BPRTLT;
import com.hisun.DB.*;

import java.io.IOException;
import java.sql.SQLException;

public class SOZMUSR {
    DBParm SOTUSR_RD;
    DBParm SOTTERM_RD;
    String WS_MSGID = " ";
    SOCMSG SOCMSG = new SOCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCENPSW SCCENPSW = new SCCENPSW();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SORUSR SORUSR = new SORUSR();
    SORTERM SORTERM = new SORTERM();
    SOCOP15 SOCOP15 = new SOCOP15();
    short WS_USR_BANK_NO = 0;
    String WS_USR_ID = " ";
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPRTLT SORTLT;
    SOCIUSR SOCIUSR;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        B00_CHECK_PROC();
        C00_MAIN_PROC();
        D00_OUTP_PROC();
        CEP.TRC(SCCGWA, "SOZMUSR return!");
        Z_RET();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SOCIUSR = new SOCIUSR();
        IBS.init(SCCGWA, SOCIUSR);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, SOCIUSR);
        SORTLT = (BPRTLT) SCCGWA.COMM_AREA.TLT_AREA_PTR;
    }
    public void B00_CHECK_PROC() throws IOException,SQLException,Exception {
        if (SOCIUSR.TYPE != 'O' 
            && SOCIUSR.TYPE != 'S') {
            WS_MSGID = SOCMSG.SO_ERR_USER_TYPE;
            S000_ERR_MSG_PROC();
        }
        if (SOCIUSR.AUTH_LVL < '0' 
            || SOCIUSR.AUTH_LVL > '9') {
            WS_MSGID = SOCMSG.SO_ERR_AUTH_LVL;
            S000_ERR_MSG_PROC();
        }
    }
    public void C00_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SOCIUSR.ACTION == 'A') {
            C00_WRITE_USR();
            C10_WRITE_TERM();
        } else if (SOCIUSR.ACTION == 'D') {
            C00_DELETE_USR();
            C10_DELETE_TERM();
        } else if (SOCIUSR.ACTION == 'M') {
            C00_READUPD_USR();
            C00_REWRITE_USR();
        } else {
        }
    }
    public void C00_WRITE_USR() throws IOException,SQLException,Exception {
        C10_MOVE_DATA_PROC();
        SOTUSR_RD = new DBParm();
        SOTUSR_RD.TableName = "SOTUSR";
        SOTUSR_RD.errhdl = true;
        IBS.WRITE(SCCGWA, SORUSR, SOTUSR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_MSGID = SOCMSG.SO_RECORD_EXIST;
            S000_ERR_MSG_PROC();
        } else {
            WS_MSGID = SOCMSG.SO_ACCESS_TAB_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void C10_WRITE_TERM() throws IOException,SQLException,Exception {
        SORTERM.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
        SORTERM.KEY.ID = SOCIUSR.ID;
        SORTERM.NAME = "SAME WITH USR";
        SORTERM.TYPE = 'T';
        SORTERM.SNON_STUS = 'N';
        SORTERM.SNON_DATE = SOCIUSR.SNON_DATE;
        SORTERM.SNON_TIME = SOCIUSR.SNON_TIME;
        SORTERM.USR = " ";
        SOTTERM_RD = new DBParm();
        SOTTERM_RD.TableName = "SOTTERM";
        SOTTERM_RD.errhdl = true;
        IBS.WRITE(SCCGWA, SORTERM, SOTTERM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_MSGID = SOCMSG.SO_RECORD_EXIST;
            S000_ERR_MSG_PROC();
        } else {
            WS_MSGID = SOCMSG.SO_ACCESS_TAB_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void C10_DELETE_TERM() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.trim().length() == 0) WS_USR_BANK_NO = 0;
        else WS_USR_BANK_NO = Short.parseShort(SCCGWA.COMM_AREA.TR_BANK);
        WS_USR_ID = SOCIUSR.ID;
        SOTTERM_RD = new DBParm();
        SOTTERM_RD.TableName = "SOTTERM";
        SOTTERM_RD.where = "BANK_NO = :WS_USR_BANK_NO "
            + "AND ID = :WS_USR_ID";
        SOTTERM_RD.errhdl = true;
        IBS.DELETE(SCCGWA, SORTERM, this, SOTTERM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            WS_MSGID = SOCMSG.SO_ACCESS_TAB_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void C10_MOVE_DATA_PROC() throws IOException,SQLException,Exception {
        SORUSR.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
        CEP.TRC(SCCGWA, SORUSR.KEY.BANK_NO);
        SORUSR.KEY.ID = SOCIUSR.ID;
        SORUSR.NAME = SOCIUSR.NAME;
        if (SOCIUSR.ACTION == 'A') {
            IBS.init(SCCGWA, SCCENPSW);
            SCCENPSW.PSWD_LEN = 6;
            CEP.TRC(SCCGWA, SOCIUSR.PSW);
            SCCENPSW.PSWD_IN = SOCIUSR.PSW;
            CEP.TRC(SCCGWA, SOCIUSR.PSW);
            CEP.TRC(SCCGWA, SCCENPSW.PSWD_IN);
            S00_CALL_SCZENPSW();
            CEP.TRC(SCCGWA, SCCENPSW.PSWD_OUT);
            SORUSR.PSW = SCCENPSW.PSWD_OUT;
            SOCIUSR.SNON_STUS = 'F';
            SOCIUSR.PSW = " PSWD ";
        }
        SORUSR.TYPE = SOCIUSR.TYPE;
        SORUSR.AUTH_LVL = SOCIUSR.AUTH_LVL;
        SORUSR.SNON_STUS = SOCIUSR.SNON_STUS;
        SORUSR.SNON_DATE = SOCIUSR.SNON_DATE;
        SORUSR.SNON_TIME = SOCIUSR.SNON_TIME;
        SORUSR.TERM_ID = SOCIUSR.TERM_ID;
        SORUSR.TERM_TYPE = SOCIUSR.TERM_TYPE;
        if (SOCIUSR.ID.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
            SORTLT.NAME = SOCIUSR.NAME;
            SORTLT.TYPE = SOCIUSR.TYPE;
            SORTLT.AUTH_LVL = SOCIUSR.AUTH_LVL;
            SORTLT.SNON_STUS = SOCIUSR.SNON_STUS;
        }
    }
    public void C00_READUPD_USR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SORUSR);
        SORUSR.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
        SORUSR.KEY.ID = SOCIUSR.ID;
        SOTUSR_RD = new DBParm();
        SOTUSR_RD.TableName = "SOTUSR";
        SOTUSR_RD.upd = true;
        SOTUSR_RD.errhdl = true;
        IBS.READ(SCCGWA, SORUSR, SOTUSR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = SOCMSG.SO_RECORD_NOTFND;
            S000_ERR_MSG_PROC();
        } else {
            WS_MSGID = SOCMSG.SO_ACCESS_TAB_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void C00_REWRITE_USR() throws IOException,SQLException,Exception {
        C10_MOVE_DATA_PROC();
        SOTUSR_RD = new DBParm();
        SOTUSR_RD.TableName = "SOTUSR";
        SOTUSR_RD.errhdl = true;
        IBS.REWRITE(SCCGWA, SORUSR, SOTUSR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            WS_MSGID = SOCMSG.SO_ACCESS_TAB_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void C00_DELETE_USR() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.trim().length() == 0) WS_USR_BANK_NO = 0;
        else WS_USR_BANK_NO = Short.parseShort(SCCGWA.COMM_AREA.TR_BANK);
        WS_USR_ID = SOCIUSR.ID;
        SOTUSR_RD = new DBParm();
        SOTUSR_RD.TableName = "SOTUSR";
        SOTUSR_RD.where = "BANK_NO = :WS_USR_BANK_NO "
            + "AND ID = :WS_USR_ID";
        SOTUSR_RD.errhdl = true;
        IBS.DELETE(SCCGWA, SORUSR, this, SOTUSR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            WS_MSGID = SOCMSG.SO_ACCESS_TAB_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void D00_OUTP_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SOCOP15);
        IBS.init(SCCGWA, SCCFMT);
        SOCOP15.ACTION = SOCIUSR.ACTION;
        SOCOP15.ID = SOCIUSR.ID;
        SOCOP15.NAME = SOCIUSR.NAME;
        SOCOP15.TYPE = SOCIUSR.TYPE;
        SOCOP15.AUTH_LVL = SOCIUSR.AUTH_LVL;
        SOCOP15.SNON_STUS = SOCIUSR.SNON_STUS;
        SOCOP15.SNON_DATE = SOCIUSR.SNON_DATE;
        SOCOP15.SNON_TIME = SOCIUSR.SNON_TIME;
        SOCOP15.TERM_ID = SOCIUSR.TERM_ID;
        SOCOP15.TERM_TYPE = SOCIUSR.TERM_TYPE;
        SCCFMT.FMTID = "SOP15";
        SCCFMT.DATA_PTR = SOCOP15;
        SCCFMT.DATA_LEN = 69;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S00_CALL_SCZENPSW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-ENCRYPT-PASSWORD", SCCENPSW);
        if (SCCENPSW.RC.RC_CODE != 0) {
            WS_MSGID = "SO6666";
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
