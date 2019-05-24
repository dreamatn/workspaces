package com.hisun.SO;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.BP.BPRTLT;
import com.hisun.DB.*;

import java.io.IOException;
import java.sql.SQLException;

public class SOZMGRP {
    DBParm SOTSERV_RD;
    DBParm SOTGRP_RD;
    String WS_MSGID = " ";
    SOCMSG SOCMSG = new SOCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SORGRP SORGRP = new SORGRP();
    SORSERV SORSERV = new SORSERV();
    SCCFMT SCCFMT = new SCCFMT();
    SOCOP18 SOCOP18 = new SOCOP18();
    short WS_GRP_BANK_NO = 0;
    String WS_GRP_ID = " ";
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPRTLT SORTLT;
    SOCIGRP SOCIGRP;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        B00_CHECK_PROC();
        C00_MAIN_PROC();
        D00_OUTP_PROC();
        CEP.TRC(SCCGWA, "SOZMGRP return!");
        Z_RET();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SOCIGRP = new SOCIGRP();
        IBS.init(SCCGWA, SOCIGRP);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, SOCIGRP);
        SORTLT = (BPRTLT) SCCGWA.COMM_AREA.TLT_AREA_PTR;
    }
    public void B00_CHECK_PROC() throws IOException,SQLException,Exception {
        if (SOCIGRP.ACTION == 'A' 
            || SOCIGRP.ACTION == 'M' 
            && SOCIGRP.SERV_ID.trim().length() > 0) {
            B00_READ_SERV();
        }
    }
    public void B00_READ_SERV() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SORSERV);
        SORSERV.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
        SORSERV.KEY.ID = SOCIGRP.SERV_ID;
        SOTSERV_RD = new DBParm();
        SOTSERV_RD.TableName = "SOTSERV";
        SOTSERV_RD.errhdl = true;
        IBS.READ(SCCGWA, SORSERV, SOTSERV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = SOCMSG.SO_ERR_SERV_ID_NO_EXIT;
            S000_ERR_MSG_PROC();
        } else {
            WS_MSGID = SOCMSG.SO_ACCESS_TAB_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void C00_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SOCIGRP.ACTION == 'A') {
            C00_WRITE_GRP();
        } else if (SOCIGRP.ACTION == 'D') {
            C00_DELETE_GRP();
        } else if (SOCIGRP.ACTION == 'M') {
            C00_READUPD_GRP();
            C00_REWRITE_GRP();
        } else {
        }
    }
    public void C00_WRITE_GRP() throws IOException,SQLException,Exception {
        C10_MOVE_DATA_PROC();
        SOTGRP_RD = new DBParm();
        SOTGRP_RD.TableName = "SOTGRP";
        SOTGRP_RD.errhdl = true;
        IBS.WRITE(SCCGWA, SORGRP, SOTGRP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_MSGID = SOCMSG.SO_RECORD_EXIST;
            S000_ERR_MSG_PROC();
        } else {
            WS_MSGID = SOCMSG.SO_ACCESS_TAB_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void C10_MOVE_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SORGRP);
        SORGRP.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
        SORGRP.KEY.ID = SOCIGRP.ID;
        SORGRP.NAME = SOCIGRP.NAME;
        SORGRP.SERV_ID = SOCIGRP.SERV_ID;
        SORGRP.CRT_USER = SORTLT.KEY.ID;
    }
    public void C00_READUPD_GRP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SORGRP);
        SORGRP.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
        SORGRP.KEY.ID = SOCIGRP.ID;
        SOTGRP_RD = new DBParm();
        SOTGRP_RD.TableName = "SOTGRP";
        SOTGRP_RD.upd = true;
        SOTGRP_RD.errhdl = true;
        IBS.READ(SCCGWA, SORGRP, SOTGRP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = SOCMSG.SO_RECORD_NOTFND;
            S000_ERR_MSG_PROC();
        } else {
            WS_MSGID = SOCMSG.SO_ACCESS_TAB_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void C00_REWRITE_GRP() throws IOException,SQLException,Exception {
        C10_MOVE_DATA_PROC();
        SOTGRP_RD = new DBParm();
        SOTGRP_RD.TableName = "SOTGRP";
        SOTGRP_RD.errhdl = true;
        IBS.REWRITE(SCCGWA, SORGRP, SOTGRP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            WS_MSGID = SOCMSG.SO_ACCESS_TAB_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void C00_DELETE_GRP() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.trim().length() == 0) WS_GRP_BANK_NO = 0;
        else WS_GRP_BANK_NO = Short.parseShort(SCCGWA.COMM_AREA.TR_BANK);
        WS_GRP_ID = SOCIGRP.ID;
        SOTGRP_RD = new DBParm();
        SOTGRP_RD.TableName = "SOTGRP";
        SOTGRP_RD.where = "BANK_NO = :WS_GRP_BANK_NO "
            + "AND ID = :WS_GRP_ID";
        SOTGRP_RD.errhdl = true;
        IBS.DELETE(SCCGWA, SORGRP, this, SOTGRP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            WS_MSGID = SOCMSG.SO_ACCESS_TAB_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void D00_OUTP_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SOCOP18);
        IBS.init(SCCGWA, SCCFMT);
        SOCOP18.ACTION = SOCIGRP.ACTION;
        SOCOP18.ID = SOCIGRP.ID;
        SOCOP18.NAME = SOCIGRP.NAME;
        SOCOP18.SERV_ID = SOCIGRP.SERV_ID;
        SOCOP18.CRT_USER = SORTLT.KEY.ID;
        SOCOP18.TS = SCCGWA.COMM_AREA.AC_DATE;
        SCCFMT.FMTID = "SOP18";
        SCCFMT.DATA_PTR = SOCOP18;
        SCCFMT.DATA_LEN = 61;
        IBS.FMT(SCCGWA, SCCFMT);
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
