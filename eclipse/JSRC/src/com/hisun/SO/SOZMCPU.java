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

public class SOZMCPU {
    DBParm SOTUSR_RD;
    DBParm SOTTERM_RD;
    String WS_MSGID = " ";
    SOCMSG SOCMSG = new SOCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCENPSW SCCENPSW = new SCCENPSW();
    SCCMSG SCCMSG = new SCCMSG();
    SORUSR SORUSR = new SORUSR();
    SORTERM SORTERM = new SORTERM();
    SCCFMT SCCFMT = new SCCFMT();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPRTLT SORTLT;
    SOCICPU SOCICPU;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        B00_CHECK_PROC();
        C00_MAIN_PROC();
        CEP.TRC(SCCGWA, "SOZMCPU return!");
        Z_RET();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SOCICPU = new SOCICPU();
        IBS.init(SCCGWA, SOCICPU);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, SOCICPU);
        SORTLT = (BPRTLT) SCCGWA.COMM_AREA.TLT_AREA_PTR;
    }
    public void B00_CHECK_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_ID.TR_CODE == 0106) {
            if (SOCICPU.OLD_PSWD.equalsIgnoreCase(SOCICPU.NEW_PSWD)) {
                WS_MSGID = SOCMSG.SO_ERR_NEW_SAME_OLD;
                S000_ERR_MSG_PROC();
            }
            if (!SOCICPU.NEW_PSWD.equalsIgnoreCase(SOCICPU.CON_PSWD)) {
                WS_MSGID = SOCMSG.SO_ERR_NEW_N_SM_CON;
                S000_ERR_MSG_PROC();
            }
        }
        if (SCCGWA.COMM_AREA.TR_ID.TR_CODE == 0107 
            && SOCICPU.ID.equalsIgnoreCase(SORTLT.KEY.ID)) {
            WS_MSGID = SOCMSG.SO_ERR_RELEASE_SELF;
            S000_ERR_MSG_PROC();
        }
    }
    public void C00_MAIN_PROC() throws IOException,SQLException,Exception {
        C00_READUPD_USR();
        C00_CHECK_PROC();
        C00_REWRITE_USR();
        if ((SCCGWA.COMM_AREA.TR_ID.TR_CODE == 0106 
            || SCCGWA.COMM_AREA.TR_ID.TR_CODE == 0108) 
            && SOCICPU.ID.equalsIgnoreCase(SORTLT.KEY.ID)) {
            SORTLT.PSW = SORUSR.PSW;
        }
    }
    public void C00_READUPD_USR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SORUSR);
        SORUSR.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
        SORUSR.KEY.ID = SOCICPU.ID;
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
    public void C00_CHECK_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_ID.TR_CODE == '0106') {
            C01_CHECK_PSW();
        } else if (SCCGWA.COMM_AREA.TR_ID.TR_CODE == '0107') {
            C01_CHECK_USR();
        } else if (SCCGWA.COMM_AREA.TR_ID.TR_CODE == '0108') {
            C02_RESET_USR();
        } else {
        }
    }
    public void C00_REWRITE_USR() throws IOException,SQLException,Exception {
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
    public void C01_CHECK_PSW() throws IOException,SQLException,Exception {
        if (SORUSR.PSW.trim().length() > 0) {
            IBS.init(SCCGWA, SCCENPSW);
            SCCENPSW.PSWD_LEN = 6;
            SCCENPSW.PSWD_IN = SOCICPU.OLD_PSWD;
            S00_CALL_SCZENPSW();
            if (!SCCENPSW.PSWD_OUT.equalsIgnoreCase(SORUSR.PSW)) {
                WS_MSGID = SOCMSG.SO_ERR_OLD_PASSWORD;
                S000_ERR_MSG_PROC();
            }
        }
        IBS.init(SCCGWA, SCCENPSW);
        SCCENPSW.PSWD_LEN = 6;
        SCCENPSW.PSWD_IN = SOCICPU.CON_PSWD;
        S00_CALL_SCZENPSW();
        SORUSR.PSW = SCCENPSW.PSWD_OUT;
    }
    public void C01_CHECK_USR() throws IOException,SQLException,Exception {
        if (SORUSR.SNON_STUS == 'F') {
            WS_MSGID = SOCMSG.SO_ERR_ALREADY_LOGOFF;
            S000_ERR_MSG_PROC();
        }
        SORUSR.SNON_STUS = 'F';
        IBS.init(SCCGWA, SORTERM);
        SORTERM.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
        SORTERM.KEY.ID = SORUSR.TERM_ID;
        SOTTERM_RD = new DBParm();
        SOTTERM_RD.TableName = "SOTTERM";
        SOTTERM_RD.upd = true;
        IBS.READ(SCCGWA, SORTERM, SOTTERM_RD);
        if (SORTERM.USR.equalsIgnoreCase(SORUSR.KEY.ID)) {
            SORTERM.SNON_STUS = 'N';
            SOTTERM_RD = new DBParm();
            SOTTERM_RD.TableName = "SOTTERM";
            IBS.REWRITE(SCCGWA, SORTERM, SOTTERM_RD);
        }
    }
    public void C02_RESET_USR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCENPSW);
        SCCENPSW.PSWD_LEN = 6;
        SCCENPSW.PSWD_IN = "CGB";
        S00_CALL_SCZENPSW();
        SORUSR.PSW = SCCENPSW.PSWD_OUT;
    }
    public void S00_CALL_SCZENPSW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-ENCRYPT-PASSWORD", SCCENPSW);
        if (SCCENPSW.RC.RC_CODE != 0) {
            WS_MSGID = SOCMSG.SO_ERR_CALL_SCZENPSW;
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
