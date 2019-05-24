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

public class SOZMWAT {
    DBParm SOTSERV_RD;
    DBParm SOTWAIT_RD;
    String WS_MSGID = " ";
    SOCMSG SOCMSG = new SOCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SORWAIT SORWAIT = new SORWAIT();
    SORSERV SORSERV = new SORSERV();
    SCCFMT SCCFMT = new SCCFMT();
    SOCOP19 SOCOP19 = new SOCOP19();
    short WS_WAIT_BANK_NO = 0;
    String WS_WAIT_ID = " ";
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPRTLT SORTLT;
    SOCIWAT SOCIWAT;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        B00_CHECK_PROC();
        C00_MAIN_PROC();
        D00_OUTP_PROC();
        CEP.TRC(SCCGWA, "SOZMWAT return!");
        Z_RET();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SOCIWAT = new SOCIWAT();
        IBS.init(SCCGWA, SOCIWAT);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, SOCIWAT);
        SORTLT = (BPRTLT) SCCGWA.COMM_AREA.TLT_AREA_PTR;
    }
    public void B00_CHECK_PROC() throws IOException,SQLException,Exception {
        if (SOCIWAT.ACTION == 'A' 
            || SOCIWAT.ACTION == 'M') {
            if (SOCIWAT.ID.trim().length() > 0) {
                IBS.init(SCCGWA, SORSERV);
                SORSERV.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
                SORSERV.KEY.ID = SOCIWAT.ID;
                B00_READ_SERV();
            }
            if (SOCIWAT.SERV1.trim().length() > 0) {
                IBS.init(SCCGWA, SORSERV);
                SORSERV.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
                SORSERV.KEY.ID = SOCIWAT.SERV1;
                B00_READ_SERV();
            }
            if (SOCIWAT.SERV2.trim().length() > 0) {
                IBS.init(SCCGWA, SORSERV);
                SORSERV.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
                SORSERV.KEY.ID = SOCIWAT.SERV2;
                B00_READ_SERV();
            }
            if (SOCIWAT.SERV3.trim().length() > 0) {
                IBS.init(SCCGWA, SORSERV);
                SORSERV.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
                SORSERV.KEY.ID = SOCIWAT.SERV3;
                B00_READ_SERV();
            }
            if (SOCIWAT.SERV4.trim().length() > 0) {
                IBS.init(SCCGWA, SORSERV);
                SORSERV.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
                SORSERV.KEY.ID = SOCIWAT.SERV4;
                B00_READ_SERV();
            }
            if (SOCIWAT.SERV5.trim().length() > 0) {
                IBS.init(SCCGWA, SORSERV);
                SORSERV.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
                SORSERV.KEY.ID = SOCIWAT.SERV5;
                B00_READ_SERV();
            }
            if (SOCIWAT.SERV6.trim().length() > 0) {
                IBS.init(SCCGWA, SORSERV);
                SORSERV.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
                SORSERV.KEY.ID = SOCIWAT.SERV6;
                B00_READ_SERV();
            }
            if (SOCIWAT.SERV7.trim().length() > 0) {
                IBS.init(SCCGWA, SORSERV);
                SORSERV.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
                SORSERV.KEY.ID = SOCIWAT.SERV7;
                B00_READ_SERV();
            }
            if (SOCIWAT.SERV8.trim().length() > 0) {
                IBS.init(SCCGWA, SORSERV);
                SORSERV.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
                SORSERV.KEY.ID = SOCIWAT.SERV8;
                B00_READ_SERV();
            }
        }
        if (SOCIWAT.END_DATE < SOCIWAT.PROC_DATE) {
            WS_MSGID = SOCMSG.SO_ERR_BEGIN_END_DATE;
            S000_ERR_MSG_PROC();
        }
    }
    public void B00_READ_SERV() throws IOException,SQLException,Exception {
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
        if (SOCIWAT.ACTION == 'A') {
            C00_WRITE_WAT();
        } else if (SOCIWAT.ACTION == 'D') {
            C00_DELETE_WAT();
        } else if (SOCIWAT.ACTION == 'M') {
            C00_READUPD_WAT();
            C00_REWRITE_WAT();
        } else {
        }
    }
    public void C00_WRITE_WAT() throws IOException,SQLException,Exception {
        C10_MOVE_DATA_PROC();
        SOTWAIT_RD = new DBParm();
        SOTWAIT_RD.TableName = "SOTWAIT";
        SOTWAIT_RD.errhdl = true;
        IBS.WRITE(SCCGWA, SORWAIT, SOTWAIT_RD);
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
        IBS.init(SCCGWA, SORWAIT);
        SORWAIT.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
        SORWAIT.KEY.ID = SOCIWAT.ID;
        SORWAIT.PROC_DATE = SOCIWAT.PROC_DATE;
        SORWAIT.END_DATE = SOCIWAT.END_DATE;
        SORWAIT.SERV1 = SOCIWAT.SERV1;
        SORWAIT.SERV2 = SOCIWAT.SERV2;
        SORWAIT.SERV3 = SOCIWAT.SERV3;
        SORWAIT.SERV4 = SOCIWAT.SERV4;
        SORWAIT.SERV5 = SOCIWAT.SERV5;
        SORWAIT.SERV6 = SOCIWAT.SERV6;
        SORWAIT.SERV7 = SOCIWAT.SERV7;
        SORWAIT.SERV8 = SOCIWAT.SERV8;
        SORWAIT.PRE_PROC1 = SOCIWAT.PRE_PROC1;
        SORWAIT.PRE_PROC2 = SOCIWAT.PRE_PROC2;
        SORWAIT.CRE_USER = SORTLT.KEY.ID;
    }
    public void C00_READUPD_WAT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SORWAIT);
        SORWAIT.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
        SORWAIT.KEY.ID = SOCIWAT.ID;
        SOTWAIT_RD = new DBParm();
        SOTWAIT_RD.TableName = "SOTWAIT";
        SOTWAIT_RD.upd = true;
        SOTWAIT_RD.errhdl = true;
        IBS.READ(SCCGWA, SORWAIT, SOTWAIT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = SOCMSG.SO_RECORD_NOTFND;
            S000_ERR_MSG_PROC();
        } else {
            WS_MSGID = SOCMSG.SO_ACCESS_TAB_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void C00_REWRITE_WAT() throws IOException,SQLException,Exception {
        C10_MOVE_DATA_PROC();
        SOTWAIT_RD = new DBParm();
        SOTWAIT_RD.TableName = "SOTWAIT";
        SOTWAIT_RD.errhdl = true;
        IBS.REWRITE(SCCGWA, SORWAIT, SOTWAIT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            WS_MSGID = SOCMSG.SO_ACCESS_TAB_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void C00_DELETE_WAT() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.trim().length() == 0) WS_WAIT_BANK_NO = 0;
        else WS_WAIT_BANK_NO = Short.parseShort(SCCGWA.COMM_AREA.TR_BANK);
        WS_WAIT_ID = SOCIWAT.ID;
        SOTWAIT_RD = new DBParm();
        SOTWAIT_RD.TableName = "SOTWAIT";
        SOTWAIT_RD.where = "BANK_NO = :WS_WAIT_BANK_NO "
            + "AND ID = :WS_WAIT_ID";
        SOTWAIT_RD.errhdl = true;
        IBS.DELETE(SCCGWA, SORWAIT, this, SOTWAIT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            WS_MSGID = SOCMSG.SO_ACCESS_TAB_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void D00_OUTP_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SOCOP19);
        IBS.init(SCCGWA, SCCFMT);
        SOCOP19.ACTION = SOCIWAT.ACTION;
        SOCOP19.ID = SOCIWAT.ID;
        SOCOP19.PROC_DATE = SOCIWAT.PROC_DATE;
        SOCOP19.END_DATE = SOCIWAT.END_DATE;
        SOCOP19.SERV1 = SOCIWAT.SERV1;
        SOCOP19.SERV2 = SOCIWAT.SERV2;
        SOCOP19.SERV3 = SOCIWAT.SERV3;
        SOCOP19.SERV4 = SOCIWAT.SERV4;
        SOCOP19.SERV5 = SOCIWAT.SERV5;
        SOCOP19.SERV6 = SOCIWAT.SERV6;
        SOCOP19.SERV7 = SOCIWAT.SERV7;
        SOCOP19.SERV8 = SOCIWAT.SERV8;
        SOCOP19.PRE_PROC1 = SOCIWAT.PRE_PROC1;
        SOCOP19.PRE_PROC2 = SOCIWAT.PRE_PROC2;
        SOCOP19.CRE_USER = SORTLT.KEY.ID;
        SOCOP19.TS = SCCGWA.COMM_AREA.AC_DATE;
        SCCFMT.FMTID = "SOP19";
        SCCFMT.DATA_PTR = SOCOP19;
        SCCFMT.DATA_LEN = 112;
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
