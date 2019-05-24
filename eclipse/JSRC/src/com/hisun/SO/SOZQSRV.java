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

public class SOZQSRV {
    DBParm SOTSERV_RD;
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String JIBS_NumStr;
    String JIBS_f0;
    String WS_MSGID = " ";
    SOCMSG SOCMSG = new SOCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SORSERV SORSERV = new SORSERV();
    SCCFMT SCCFMT = new SCCFMT();
    SOCOP16 SOCOP16 = new SOCOP16();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPRTLT SORTLT;
    SOCISRV SOCISRV;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        B00_MAIN_PROC();
        C00_OUTP_PROC();
        CEP.TRC(SCCGWA, "SOZQSRV return!");
        Z_RET();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SOCISRV = new SOCISRV();
        IBS.init(SCCGWA, SOCISRV);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, SOCISRV);
        SORTLT = (BPRTLT) SCCGWA.COMM_AREA.TLT_AREA_PTR;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SORSERV);
        SORSERV.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
        SORSERV.KEY.ID = SOCISRV.ID;
        SOTSERV_RD = new DBParm();
        SOTSERV_RD.TableName = "SOTSERV";
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
    public void C00_OUTP_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SOCOP16);
        IBS.init(SCCGWA, SCCFMT);
        SOCOP16.ACTION = SOCISRV.ACTION;
        SOCOP16.ID = SORSERV.KEY.ID;
        SOCOP16.NAME = SORSERV.NAME;
        SOCOP16.NAME_FIL = 0X02;
        SOCOP16.STUS = SORSERV.STUS;
        SOCOP16.TYPE = SORSERV.TYPE;
        SOCOP16.CTRL_WORD = SORSERV.CTRL_WORD;
        if (SORSERV.AUTH_LVL.trim().length() == 0) SOCOP16.AUTH_LVL = 0;
        else SOCOP16.AUTH_LVL = Short.parseShort(SORSERV.AUTH_LVL);
        SOCOP16.PGM_NAME = SORSERV.PGM_NAME;
        SOCOP16.SUB_PROC1 = SORSERV.SUB_PROC1;
        SOCOP16.SUB_PROC2 = SORSERV.SUB_PROC2;
        SOCOP16.SUB_SERV = SORSERV.SUB_SERV;
        SOCOP16.READ_IND = SORSERV.READ_IND;
        SOCOP16.BROW_IND = SORSERV.BROW_IND;
        SOCOP16.WRITE_IND = SORSERV.WRITE_IND;
        SOCOP16.UPD_IND = SORSERV.UPD_IND;
        SOCOP16.DEL_IND = SORSERV.DEL_IND;
        SOCOP16.READ_LVL = SORSERV.READ_LVL;
        SOCOP16.BROW_LVL = SORSERV.BROW_LVL;
        SOCOP16.WRITE_LVL = SORSERV.WRITE_LVL;
        SOCOP16.UPD_LVL = SORSERV.UPD_LVL;
        SOCOP16.DEL_LVL = SORSERV.DEL_LVL;
        SOCOP16.CRE_USER = SORSERV.CRE_USER;
        if (SORSERV.TS == null) SORSERV.TS = "";
        JIBS_tmp_int = SORSERV.TS.length();
        for (int i=0;i<26-JIBS_tmp_int;i++) SORSERV.TS += " ";
        JIBS_tmp_str[0] = "" + SOCOP16.CRE_DATE;
        JIBS_f0 = "";
        for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + SOCOP16.CRE_DATE;
        JIBS_NumStr = SORSERV.TS.substring(0, 4) + JIBS_NumStr.substring(4);
        SOCOP16.CRE_DATE = Integer.parseInt(JIBS_NumStr);
        if (SORSERV.TS == null) SORSERV.TS = "";
        JIBS_tmp_int = SORSERV.TS.length();
        for (int i=0;i<26-JIBS_tmp_int;i++) SORSERV.TS += " ";
        JIBS_tmp_str[0] = "" + SOCOP16.CRE_DATE;
        JIBS_f0 = "";
        for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + SOCOP16.CRE_DATE;
        JIBS_NumStr = JIBS_NumStr.substring(0, 5 - 1) + SORSERV.TS.substring(6 - 1, 6 + 2 - 1) + JIBS_NumStr.substring(5 + 2 - 1);
        SOCOP16.CRE_DATE = Integer.parseInt(JIBS_NumStr);
        if (SORSERV.TS == null) SORSERV.TS = "";
        JIBS_tmp_int = SORSERV.TS.length();
        for (int i=0;i<26-JIBS_tmp_int;i++) SORSERV.TS += " ";
        JIBS_tmp_str[0] = "" + SOCOP16.CRE_DATE;
        JIBS_f0 = "";
        for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + SOCOP16.CRE_DATE;
        JIBS_NumStr = JIBS_NumStr.substring(0, 7 - 1) + SORSERV.TS.substring(9 - 1, 9 + 2 - 1) + JIBS_NumStr.substring(7 + 2 - 1);
        SOCOP16.CRE_DATE = Integer.parseInt(JIBS_NumStr);
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
