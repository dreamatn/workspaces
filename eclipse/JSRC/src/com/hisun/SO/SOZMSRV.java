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

public class SOZMSRV {
    int JIBS_tmp_int;
    DBParm SOTSERV_RD;
    short WS_I = 0;
    String WS_MSGID = " ";
    SOCMSG SOCMSG = new SOCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SORSERV SORSERV = new SORSERV();
    SCCFMT SCCFMT = new SCCFMT();
    SOCOP16 SOCOP16 = new SOCOP16();
    short WS_SERV_BANK_NO = 0;
    String WS_SERV_ID = " ";
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPRTLT SORTLT;
    SOCISRV SOCISRV;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        B00_CHECK_PROC();
        C00_MAIN_PROC();
        D00_OUTP_PROC();
        CEP.TRC(SCCGWA, "SOZMSRV return!");
        Z_RET();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SOCISRV = new SOCISRV();
        IBS.init(SCCGWA, SOCISRV);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, SOCISRV);
        SORTLT = (BPRTLT) SCCGWA.COMM_AREA.TLT_AREA_PTR;
    }
    public void B00_CHECK_PROC() throws IOException,SQLException,Exception {
        if (SOCISRV.STUS != 'A' 
            && SOCISRV.STUS != 'I') {
            WS_MSGID = SOCMSG.SO_ERR_SERV_STUS;
            S000_ERR_MSG_PROC();
        }
        if (SOCISRV.TYPE != 'T' 
            && SOCISRV.TYPE != 'M') {
            WS_MSGID = SOCMSG.SO_ERR_SERV_TYPE;
            S000_ERR_MSG_PROC();
        }
        if (SOCISRV.TYPE == 'T' 
            && SOCISRV.PGM_NAME.trim().length() == 0) {
            WS_MSGID = SOCMSG.SO_INPUT_INVALID;
            S000_ERR_MSG_PROC();
        }
        for (WS_I = 1; WS_I <= 64; WS_I += 1) {
            if (SOCISRV.CTRL_WORD == null) SOCISRV.CTRL_WORD = "";
            JIBS_tmp_int = SOCISRV.CTRL_WORD.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) SOCISRV.CTRL_WORD += " ";
            if (!SOCISRV.CTRL_WORD.substring(WS_I - 1, WS_I + 1 - 1).equalsIgnoreCase("0") 
                && !SOCISRV.CTRL_WORD.substring(WS_I - 1, WS_I + 1 - 1).equalsIgnoreCase("1")) {
                WS_MSGID = SOCMSG.SO_ERR_SERV_CTRL_WORD;
                S000_ERR_MSG_PROC();
            }
        }
        if ((SOCISRV.SUB_PROC1.trim().length() > 0) 
            && (SOCISRV.SUB_PROC2.trim().length() > 0)) {
            WS_MSGID = SOCMSG.SO_INPUT_INVALID;
            S000_ERR_MSG_PROC();
        }
    }
    public void C00_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SOCISRV.ACTION == 'A') {
            C00_WRITE_SRV();
        } else if (SOCISRV.ACTION == 'D') {
            C00_DELETE_SRV();
        } else if (SOCISRV.ACTION == 'M') {
            C00_READUPD_SRV();
            C00_REWRITE_SRV();
        } else {
        }
    }
    public void C00_WRITE_SRV() throws IOException,SQLException,Exception {
        C10_MOVE_DATA_PROC();
        SOTSERV_RD = new DBParm();
        SOTSERV_RD.TableName = "SOTSERV";
        SOTSERV_RD.errhdl = true;
        IBS.WRITE(SCCGWA, SORSERV, SOTSERV_RD);
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
        IBS.init(SCCGWA, SORSERV);
        SORSERV.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
        SORSERV.KEY.ID = SOCISRV.ID;
        SORSERV.NAME = SOCISRV.NAME;
        SORSERV.STUS = SOCISRV.STUS;
        SORSERV.TYPE = SOCISRV.TYPE;
        SORSERV.CTRL_WORD = SOCISRV.CTRL_WORD;
        SORSERV.AUTH_LVL = "" + SOCISRV.AUTH_LVL;
        JIBS_tmp_int = SORSERV.AUTH_LVL.length();
        for (int i=0;i<2-JIBS_tmp_int;i++) SORSERV.AUTH_LVL = "0" + SORSERV.AUTH_LVL;
        SORSERV.PGM_NAME = SOCISRV.PGM_NAME;
        SORSERV.SUB_PROC1 = SOCISRV.SUB_PROC1;
        SORSERV.SUB_PROC2 = SOCISRV.SUB_PROC2;
        SORSERV.SUB_SERV = SOCISRV.SUB_SERV;
        SORSERV.READ_IND = SOCISRV.READ_IND;
        SORSERV.BROW_IND = SOCISRV.BROW_IND;
        SORSERV.WRITE_IND = SOCISRV.WRITE_IND;
        SORSERV.UPD_IND = SOCISRV.UPD_IND;
        SORSERV.DEL_IND = SOCISRV.DEL_IND;
        SORSERV.READ_LVL = SOCISRV.READ_LVL;
        SORSERV.BROW_LVL = SOCISRV.BROW_LVL;
        SORSERV.WRITE_LVL = SOCISRV.WRITE_LVL;
        SORSERV.UPD_LVL = SOCISRV.UPD_LVL;
        SORSERV.DEL_LVL = SOCISRV.DEL_LVL;
        SORSERV.CRE_USER = SORTLT.KEY.ID;
    }
    public void C00_READUPD_SRV() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SORSERV);
        SORSERV.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
        SORSERV.KEY.ID = SOCISRV.ID;
        SOTSERV_RD = new DBParm();
        SOTSERV_RD.TableName = "SOTSERV";
        SOTSERV_RD.upd = true;
        SOTSERV_RD.errhdl = true;
        IBS.READ(SCCGWA, SORSERV, SOTSERV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = SOCMSG.SO_RECORD_NOTFND;
            S000_ERR_MSG_PROC();
        } else {
            WS_MSGID = SOCMSG.SO_ACCESS_TAB_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void C00_REWRITE_SRV() throws IOException,SQLException,Exception {
        C10_MOVE_DATA_PROC();
        SOTSERV_RD = new DBParm();
        SOTSERV_RD.TableName = "SOTSERV";
        SOTSERV_RD.errhdl = true;
        IBS.REWRITE(SCCGWA, SORSERV, SOTSERV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            WS_MSGID = SOCMSG.SO_ACCESS_TAB_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void C00_DELETE_SRV() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.trim().length() == 0) WS_SERV_BANK_NO = 0;
        else WS_SERV_BANK_NO = Short.parseShort(SCCGWA.COMM_AREA.TR_BANK);
        WS_SERV_ID = SOCISRV.ID;
        SOTSERV_RD = new DBParm();
        SOTSERV_RD.TableName = "SOTSERV";
        SOTSERV_RD.where = "BANK_NO = :WS_SERV_BANK_NO "
            + "AND ID = :WS_SERV_ID";
        SOTSERV_RD.errhdl = true;
        IBS.DELETE(SCCGWA, SORSERV, this, SOTSERV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            WS_MSGID = SOCMSG.SO_ACCESS_TAB_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void D00_OUTP_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SOCOP16);
        IBS.init(SCCGWA, SCCFMT);
        SOCOP16.ACTION = SOCISRV.ACTION;
        SOCOP16.ID = SOCISRV.ID;
        SOCOP16.NAME = SOCISRV.NAME;
        SOCOP16.NAME_FIL = 0X02;
        SOCOP16.STUS = SOCISRV.STUS;
        SOCOP16.TYPE = SOCISRV.TYPE;
        SOCOP16.CTRL_WORD = SOCISRV.CTRL_WORD;
        SOCOP16.AUTH_LVL = SOCISRV.AUTH_LVL;
        SOCOP16.PGM_NAME = SOCISRV.PGM_NAME;
        SOCOP16.SUB_PROC1 = SOCISRV.SUB_PROC1;
        SOCOP16.SUB_PROC2 = SOCISRV.SUB_PROC2;
        SOCOP16.SUB_SERV = SOCISRV.SUB_SERV;
        SOCOP16.READ_IND = SOCISRV.READ_IND;
        SOCOP16.BROW_IND = SOCISRV.BROW_IND;
        SOCOP16.WRITE_IND = SOCISRV.WRITE_IND;
        SOCOP16.UPD_IND = SOCISRV.UPD_IND;
        SOCOP16.DEL_IND = SOCISRV.DEL_IND;
        SOCOP16.READ_LVL = SOCISRV.READ_LVL;
        SOCOP16.BROW_LVL = SOCISRV.BROW_LVL;
        SOCOP16.WRITE_LVL = SOCISRV.WRITE_LVL;
        SOCOP16.UPD_LVL = SOCISRV.UPD_LVL;
        SOCOP16.DEL_LVL = SOCISRV.DEL_LVL;
        SOCOP16.CRE_USER = SORTLT.KEY.ID;
        SOCOP16.CRE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        SCCFMT.FMTID = "SOP16";
        SCCFMT.DATA_PTR = SOCOP16;
        SCCFMT.DATA_LEN = 167;
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
