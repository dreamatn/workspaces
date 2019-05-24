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

public class SOZQWAT {
    DBParm SOTWAIT_RD;
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String JIBS_NumStr;
    String JIBS_f0;
    String WS_MSGID = " ";
    SOCMSG SOCMSG = new SOCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SORWAIT SORWAIT = new SORWAIT();
    SCCFMT SCCFMT = new SCCFMT();
    SOCOP19 SOCOP19 = new SOCOP19();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPRTLT SORTLT;
    SOCIWAT SOCIWAT;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        B00_MAIN_PROC();
        C00_OUTP_PROC();
        CEP.TRC(SCCGWA, "SOZQWAT return!");
        Z_RET();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SOCIWAT = new SOCIWAT();
        IBS.init(SCCGWA, SOCIWAT);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, SOCIWAT);
        SORTLT = (BPRTLT) SCCGWA.COMM_AREA.TLT_AREA_PTR;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SORWAIT);
        SORWAIT.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
        SORWAIT.KEY.ID = SOCIWAT.ID;
        SOTWAIT_RD = new DBParm();
        SOTWAIT_RD.TableName = "SOTWAIT";
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
    public void C00_OUTP_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SOCOP19);
        IBS.init(SCCGWA, SCCFMT);
        SOCOP19.ACTION = SOCIWAT.ACTION;
        SOCOP19.ID = SORWAIT.KEY.ID;
        SOCOP19.PROC_DATE = SORWAIT.PROC_DATE;
        SOCOP19.END_DATE = SORWAIT.END_DATE;
        SOCOP19.SERV1 = SORWAIT.SERV1;
        SOCOP19.SERV2 = SORWAIT.SERV2;
        SOCOP19.SERV3 = SORWAIT.SERV3;
        SOCOP19.SERV4 = SORWAIT.SERV4;
        SOCOP19.SERV5 = SORWAIT.SERV5;
        SOCOP19.SERV6 = SORWAIT.SERV6;
        SOCOP19.SERV7 = SORWAIT.SERV7;
        SOCOP19.SERV8 = SORWAIT.SERV8;
        SOCOP19.PRE_PROC1 = SORWAIT.PRE_PROC1;
        SOCOP19.PRE_PROC2 = SORWAIT.PRE_PROC2;
        SOCOP19.CRE_USER = SORWAIT.CRE_USER;
        if (SORWAIT.TS == null) SORWAIT.TS = "";
        JIBS_tmp_int = SORWAIT.TS.length();
        for (int i=0;i<26-JIBS_tmp_int;i++) SORWAIT.TS += " ";
        JIBS_tmp_str[0] = "" + SOCOP19.TS;
        JIBS_f0 = "";
        for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + SOCOP19.TS;
        JIBS_NumStr = SORWAIT.TS.substring(0, 4) + JIBS_NumStr.substring(4);
        SOCOP19.TS = Integer.parseInt(JIBS_NumStr);
        if (SORWAIT.TS == null) SORWAIT.TS = "";
        JIBS_tmp_int = SORWAIT.TS.length();
        for (int i=0;i<26-JIBS_tmp_int;i++) SORWAIT.TS += " ";
        JIBS_tmp_str[0] = "" + SOCOP19.TS;
        JIBS_f0 = "";
        for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + SOCOP19.TS;
        JIBS_NumStr = JIBS_NumStr.substring(0, 5 - 1) + SORWAIT.TS.substring(6 - 1, 6 + 2 - 1) + JIBS_NumStr.substring(5 + 2 - 1);
        SOCOP19.TS = Integer.parseInt(JIBS_NumStr);
        if (SORWAIT.TS == null) SORWAIT.TS = "";
        JIBS_tmp_int = SORWAIT.TS.length();
        for (int i=0;i<26-JIBS_tmp_int;i++) SORWAIT.TS += " ";
        JIBS_tmp_str[0] = "" + SOCOP19.TS;
        JIBS_f0 = "";
        for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + SOCOP19.TS;
        JIBS_NumStr = JIBS_NumStr.substring(0, 7 - 1) + SORWAIT.TS.substring(9 - 1, 9 + 2 - 1) + JIBS_NumStr.substring(7 + 2 - 1);
        SOCOP19.TS = Integer.parseInt(JIBS_NumStr);
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
