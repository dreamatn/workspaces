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

public class SOZMSYS {
    DBParm SOTSYS_RD;
    String WS_MSGID = " ";
    SOCMSG SOCMSG = new SOCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SORSYS SORSYS = new SORSYS();
    SCCFMT SCCFMT = new SCCFMT();
    SOCOP20 SOCOP20 = new SOCOP20();
    String WS_SYS_ID = " ";
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPRTLT SORTLT;
    SOCISYS SOCISYS;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        B00_CHECK_PROC();
        C00_MAIN_PROC();
        D00_OUTP_PROC();
        CEP.TRC(SCCGWA, "SOZMSYS return!");
        Z_RET();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SOCISYS = new SOCISYS();
        IBS.init(SCCGWA, SOCISYS);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, SOCISYS);
        SORTLT = (BPRTLT) SCCGWA.COMM_AREA.TLT_AREA_PTR;
    }
    public void B00_CHECK_PROC() throws IOException,SQLException,Exception {
        if (SOCISYS.OPER_DATE < SOCISYS.LAST_OPER_DATE) {
            WS_MSGID = SOCMSG.SO_ERR_BEGIN_END_DATE;
            S000_ERR_MSG_PROC();
        }
    }
    public void C00_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SOCISYS.ACTION == 'A') {
            C00_WRITE_SYS();
        } else if (SOCISYS.ACTION == 'D') {
            C00_DELETE_SYS();
        } else if (SOCISYS.ACTION == 'M') {
            C00_READUPD_SYS();
            C00_REWRITE_SYS();
        } else {
        }
    }
    public void C00_WRITE_SYS() throws IOException,SQLException,Exception {
        C10_MOVE_DATA_PROC();
        SOTSYS_RD = new DBParm();
        SOTSYS_RD.TableName = "SOTSYS";
        SOTSYS_RD.errhdl = true;
        IBS.WRITE(SCCGWA, SORSYS, SOTSYS_RD);
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
        IBS.init(SCCGWA, SORSYS);
        SORSYS.KEY.SYS_ID = " ";
        SORSYS.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
        SORSYS.BANK_ID = SOCISYS.BANK_ID;
        SORSYS.MBANK_NO = SOCISYS.MBANK_NO;
        SORSYS.JRN_NO = SOCISYS.JRN_NO;
        SORSYS.ENV_ID = SOCISYS.ENV_ID;
        SORSYS.OPER_DATE = SOCISYS.OPER_DATE;
        SORSYS.LAST_OPER_DATE = SOCISYS.LAST_OPER_DATE;
        SORSYS.BANK_ID_1 = SOCISYS.BANK_ID_1;
        SORSYS.BANK_ID_2 = SOCISYS.BANK_ID_2;
        SORSYS.HQ_BANK_NO = SOCISYS.HQ_BANK_NO;
        SORSYS.CRE_USER = SORTLT.KEY.ID;
    }
    public void C00_READUPD_SYS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SORSYS);
        SORSYS.KEY.SYS_ID = " ";
        SOTSYS_RD = new DBParm();
        SOTSYS_RD.TableName = "SOTSYS";
        SOTSYS_RD.upd = true;
        SOTSYS_RD.errhdl = true;
        IBS.READ(SCCGWA, SORSYS, SOTSYS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = SOCMSG.SO_RECORD_NOTFND;
            S000_ERR_MSG_PROC();
        } else {
            WS_MSGID = SOCMSG.SO_ACCESS_TAB_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void C00_REWRITE_SYS() throws IOException,SQLException,Exception {
        C10_MOVE_DATA_PROC();
        SOTSYS_RD = new DBParm();
        SOTSYS_RD.TableName = "SOTSYS";
        SOTSYS_RD.errhdl = true;
        IBS.REWRITE(SCCGWA, SORSYS, SOTSYS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            WS_MSGID = SOCMSG.SO_ACCESS_TAB_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void C00_DELETE_SYS() throws IOException,SQLException,Exception {
        WS_SYS_ID = " ";
        SOTSYS_RD = new DBParm();
        SOTSYS_RD.TableName = "SOTSYS";
        SOTSYS_RD.where = "SYS_ID = :WS_SYS_ID";
        SOTSYS_RD.errhdl = true;
        IBS.DELETE(SCCGWA, SORSYS, this, SOTSYS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            WS_MSGID = SOCMSG.SO_ACCESS_TAB_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void D00_OUTP_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SOCOP20);
        IBS.init(SCCGWA, SCCFMT);
        SOCOP20.ACTION = SOCISYS.ACTION;
        SOCOP20.SYS_ID = SOCISYS.SYS_ID;
        SOCOP20.BANK_NO = SOCISYS.BANK_NO;
        SOCOP20.BANK_ID = SOCISYS.BANK_ID;
        SOCOP20.MBANK_NO = SOCISYS.MBANK_NO;
        SOCOP20.JRN_NO = SOCISYS.JRN_NO;
        SOCOP20.ENV_ID = SOCISYS.ENV_ID;
        SOCOP20.OPER_DATE = SOCISYS.OPER_DATE;
        SOCOP20.LAST_OPER_DATE = SOCISYS.LAST_OPER_DATE;
        SOCOP20.BANK_ID_1 = SOCISYS.BANK_ID_1;
        SOCOP20.BANK_ID_2 = SOCISYS.BANK_ID_2;
        SOCOP20.HQ_BANK_NO = SOCISYS.HQ_BANK_NO;
        SOCOP20.CRE_USER = SORTLT.KEY.ID;
        SOCOP20.CRE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        SCCFMT.FMTID = "SOP20";
        SCCFMT.DATA_PTR = SOCOP20;
        SCCFMT.DATA_LEN = 75;
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
