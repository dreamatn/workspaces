package com.hisun.SO;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.DB.*;

import java.io.IOException;
import java.sql.SQLException;

public class SOZMSLVL {
    DBParm SOTSERV_RD;
    DBParm SOTSLVL_RD;
    int JIBS_tmp_int;
    short WS_I = 0;
    String WS_MSGID = " ";
    SOCMSG SOCMSG = new SOCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SORSERV SORSERV = new SORSERV();
    SCCFMT SCCFMT = new SCCFMT();
    SOCOP21 SOCOP21 = new SOCOP21();
    short WS_SLVL_BANK_NO = 0;
    String WS_SLVL_ID = " ";
    DB2RTCD DB2RTCD = new DB2RTCD();
    SORSLVL SORSLVL = new SORSLVL();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SOCISLVL SOCISLVL;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        B00_CHECK_PROC();
        C00_MAIN_PROC();
        D00_OUTP_PROC();
        CEP.TRC(SCCGWA, "SOZMSLVL return!");
        Z_RET();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SOCISLVL = new SOCISLVL();
        IBS.init(SCCGWA, SOCISLVL);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, SOCISLVL);
    }
    public void B00_CHECK_PROC() throws IOException,SQLException,Exception {
        if (SOCISLVL.ACTION == 'A' 
            || SOCISLVL.ACTION == 'M' 
            && SOCISLVL.KEY.ID.trim().length() > 0 
            && SOCISLVL.SUP_ID.trim().length() > 0) {
            B00_READ_SERV();
        }
    }
    public void B00_READ_SERV() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SORSERV);
        SORSERV.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
        SORSERV.KEY.ID = SOCISLVL.KEY.ID;
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
        SORSERV.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
        SORSERV.KEY.ID = SOCISLVL.SUP_ID;
        SOTSERV_RD = new DBParm();
        SOTSERV_RD.TableName = "SOTSERV";
        SOTSERV_RD.errhdl = true;
        IBS.READ(SCCGWA, SORSERV, SOTSERV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = SOCMSG.SO_ERR_SUP_ID_NO_EXIT;
            S000_ERR_MSG_PROC();
        } else {
            WS_MSGID = SOCMSG.SO_ACCESS_TAB_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void C00_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SOCISLVL.ACTION == 'A') {
            C00_WRITE_SLVL();
        } else if (SOCISLVL.ACTION == 'D') {
            C00_DELETE_SLVL();
        } else if (SOCISLVL.ACTION == 'M') {
            C00_READUPD_SLVL();
            C00_REWRITE_SLVL();
        } else {
        }
    }
    public void C00_WRITE_SLVL() throws IOException,SQLException,Exception {
        C10_MOVE_DATA_PROC();
        SOTSLVL_RD = new DBParm();
        SOTSLVL_RD.TableName = "SOTSLVL";
        SOTSLVL_RD.errhdl = true;
        IBS.WRITE(SCCGWA, SORSLVL, SOTSLVL_RD);
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
        SORSLVL.KEY.BANK_NO = "" + SOCISLVL.KEY.BANK_NO;
        JIBS_tmp_int = SORSLVL.KEY.BANK_NO.length();
        for (int i=0;i<3-JIBS_tmp_int;i++) SORSLVL.KEY.BANK_NO = "0" + SORSLVL.KEY.BANK_NO;
        SORSLVL.KEY.ID = SOCISLVL.KEY.ID;
        SORSLVL.SUP_ID = SOCISLVL.SUP_ID;
        SORSLVL.CRE_USER = SCCGWA.COMM_AREA.TL_ID;
    }
    public void C00_READUPD_SLVL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SORSLVL);
        SORSLVL.KEY.BANK_NO = "" + SOCISLVL.KEY.BANK_NO;
        JIBS_tmp_int = SORSLVL.KEY.BANK_NO.length();
        for (int i=0;i<3-JIBS_tmp_int;i++) SORSLVL.KEY.BANK_NO = "0" + SORSLVL.KEY.BANK_NO;
        SORSLVL.KEY.ID = SOCISLVL.KEY.ID;
        SOTSLVL_RD = new DBParm();
        SOTSLVL_RD.TableName = "SOTSLVL";
        SOTSLVL_RD.upd = true;
        SOTSLVL_RD.errhdl = true;
        IBS.READ(SCCGWA, SORSLVL, SOTSLVL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = SOCMSG.SO_RECORD_NOTFND;
            S000_ERR_MSG_PROC();
        } else {
            WS_MSGID = SOCMSG.SO_ACCESS_TAB_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void C00_REWRITE_SLVL() throws IOException,SQLException,Exception {
        C10_MOVE_DATA_PROC();
        SOTSLVL_RD = new DBParm();
        SOTSLVL_RD.TableName = "SOTSLVL";
        SOTSLVL_RD.errhdl = true;
        IBS.REWRITE(SCCGWA, SORSLVL, SOTSLVL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            WS_MSGID = SOCMSG.SO_ACCESS_TAB_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void C00_DELETE_SLVL() throws IOException,SQLException,Exception {
        SORSLVL.KEY.BANK_NO = "" + SOCISLVL.KEY.BANK_NO;
        JIBS_tmp_int = SORSLVL.KEY.BANK_NO.length();
        for (int i=0;i<3-JIBS_tmp_int;i++) SORSLVL.KEY.BANK_NO = "0" + SORSLVL.KEY.BANK_NO;
        SORSLVL.KEY.ID = SOCISLVL.KEY.ID;
        SOTSLVL_RD = new DBParm();
        SOTSLVL_RD.TableName = "SOTSLVL";
        SOTSLVL_RD.where = "BANK_NO = :SORSLVL.KEY.BANK_NO "
            + "AND ID = :SORSLVL.KEY.ID";
        SOTSLVL_RD.errhdl = true;
        IBS.DELETE(SCCGWA, SORSLVL, this, SOTSLVL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            WS_MSGID = SOCMSG.SO_ACCESS_TAB_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void D00_OUTP_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SOCOP21);
        IBS.init(SCCGWA, SCCFMT);
        SOCOP21.ACTION = SOCISLVL.ACTION;
        SOCOP21.KEY.BANK_NO = SORSLVL.KEY.BANK_NO;
        SOCOP21.KEY.ID = SORSLVL.KEY.ID;
        SOCOP21.SUP_ID = SORSLVL.SUP_ID;
        SOCOP21.CRE_USER = SORSLVL.CRE_USER;
        SOCOP21.TS = SORSLVL.TS;
        SCCFMT.FMTID = "SOP21";
        SCCFMT.DATA_PTR = SOCOP21;
        SCCFMT.DATA_LEN = 51;
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
