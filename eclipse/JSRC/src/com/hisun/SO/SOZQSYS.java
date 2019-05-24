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

public class SOZQSYS {
    DBParm SOTSYS_RD;
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String JIBS_NumStr;
    String JIBS_f0;
    String WS_MSGID = " ";
    SOCMSG SOCMSG = new SOCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SORSYS SORSYS = new SORSYS();
    SCCFMT SCCFMT = new SCCFMT();
    SOCOP20 SOCOP20 = new SOCOP20();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPRTLT SORTLT;
    SOCISYS SOCISYS;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        C00_MAIN_PROC();
        D00_OUTP_PROC();
        CEP.TRC(SCCGWA, "SOZQSYS return!");
        Z_RET();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SOCISYS = new SOCISYS();
        IBS.init(SCCGWA, SOCISYS);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, SOCISYS);
        SORTLT = (BPRTLT) SCCGWA.COMM_AREA.TLT_AREA_PTR;
    }
    public void C00_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SORSYS);
        SORSYS.KEY.SYS_ID = " ";
        SOTSYS_RD = new DBParm();
        SOTSYS_RD.TableName = "SOTSYS";
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
    public void D00_OUTP_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SOCOP20);
        IBS.init(SCCGWA, SCCFMT);
        SOCOP20.ACTION = SOCISYS.ACTION;
        SOCOP20.SYS_ID = SORSYS.KEY.SYS_ID;
        SOCOP20.BANK_NO = SORSYS.BANK_NO;
        SOCOP20.BANK_ID = SORSYS.BANK_ID;
        SOCOP20.MBANK_NO = SORSYS.MBANK_NO;
        SOCOP20.JRN_NO = SORSYS.JRN_NO;
        SOCOP20.ENV_ID = SORSYS.ENV_ID;
        SOCOP20.OPER_DATE = SORSYS.OPER_DATE;
        SOCOP20.LAST_OPER_DATE = SORSYS.LAST_OPER_DATE;
        SOCOP20.CRE_USER = SORSYS.CRE_USER;
        SOCOP20.BANK_ID_1 = SORSYS.BANK_ID_1;
        SOCOP20.BANK_ID_2 = SORSYS.BANK_ID_2;
        SOCOP20.HQ_BANK_NO = SORSYS.HQ_BANK_NO;
        if (SORSYS.TS == null) SORSYS.TS = "";
        JIBS_tmp_int = SORSYS.TS.length();
        for (int i=0;i<26-JIBS_tmp_int;i++) SORSYS.TS += " ";
        JIBS_tmp_str[0] = "" + SOCOP20.CRE_DATE;
        JIBS_f0 = "";
        for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + SOCOP20.CRE_DATE;
        JIBS_NumStr = SORSYS.TS.substring(0, 4) + JIBS_NumStr.substring(4);
        SOCOP20.CRE_DATE = Integer.parseInt(JIBS_NumStr);
        if (SORSYS.TS == null) SORSYS.TS = "";
        JIBS_tmp_int = SORSYS.TS.length();
        for (int i=0;i<26-JIBS_tmp_int;i++) SORSYS.TS += " ";
        JIBS_tmp_str[0] = "" + SOCOP20.CRE_DATE;
        JIBS_f0 = "";
        for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + SOCOP20.CRE_DATE;
        JIBS_NumStr = JIBS_NumStr.substring(0, 5 - 1) + SORSYS.TS.substring(6 - 1, 6 + 2 - 1) + JIBS_NumStr.substring(5 + 2 - 1);
        SOCOP20.CRE_DATE = Integer.parseInt(JIBS_NumStr);
        if (SORSYS.TS == null) SORSYS.TS = "";
        JIBS_tmp_int = SORSYS.TS.length();
        for (int i=0;i<26-JIBS_tmp_int;i++) SORSYS.TS += " ";
        JIBS_tmp_str[0] = "" + SOCOP20.CRE_DATE;
        JIBS_f0 = "";
        for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + SOCOP20.CRE_DATE;
        JIBS_NumStr = JIBS_NumStr.substring(0, 7 - 1) + SORSYS.TS.substring(9 - 1, 9 + 2 - 1) + JIBS_NumStr.substring(7 + 2 - 1);
        SOCOP20.CRE_DATE = Integer.parseInt(JIBS_NumStr);
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
