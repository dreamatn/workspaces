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

public class SOZQGRP {
    DBParm SOTGRP_RD;
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String JIBS_NumStr;
    String JIBS_f0;
    String WS_MSGID = " ";
    SOCMSG SOCMSG = new SOCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SORGRP SORGRP = new SORGRP();
    SCCFMT SCCFMT = new SCCFMT();
    SOCOP18 SOCOP18 = new SOCOP18();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPRTLT SORTLT;
    SOCIGRP SOCIGRP;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        B00_MAIN_PROC();
        C00_OUTP_PROC();
        CEP.TRC(SCCGWA, "SOZQGRP return!");
        Z_RET();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SOCIGRP = new SOCIGRP();
        IBS.init(SCCGWA, SOCIGRP);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, SOCIGRP);
        SORTLT = (BPRTLT) SCCGWA.COMM_AREA.TLT_AREA_PTR;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SORGRP);
        SORGRP.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
        SORGRP.KEY.ID = SOCIGRP.ID;
        SOTGRP_RD = new DBParm();
        SOTGRP_RD.TableName = "SOTGRP";
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
    public void C00_OUTP_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SOCOP18);
        IBS.init(SCCGWA, SCCFMT);
        SOCOP18.ACTION = SOCIGRP.ACTION;
        SOCOP18.ID = SORGRP.KEY.ID;
        SOCOP18.NAME = SORGRP.NAME;
        SOCOP18.SERV_ID = SORGRP.SERV_ID;
        SOCOP18.CRT_USER = SORGRP.CRT_USER;
        if (SORGRP.TS == null) SORGRP.TS = "";
        JIBS_tmp_int = SORGRP.TS.length();
        for (int i=0;i<26-JIBS_tmp_int;i++) SORGRP.TS += " ";
        JIBS_tmp_str[0] = "" + SOCOP18.TS;
        JIBS_f0 = "";
        for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + SOCOP18.TS;
        JIBS_NumStr = SORGRP.TS.substring(0, 4) + JIBS_NumStr.substring(4);
        SOCOP18.TS = Integer.parseInt(JIBS_NumStr);
        if (SORGRP.TS == null) SORGRP.TS = "";
        JIBS_tmp_int = SORGRP.TS.length();
        for (int i=0;i<26-JIBS_tmp_int;i++) SORGRP.TS += " ";
        JIBS_tmp_str[0] = "" + SOCOP18.TS;
        JIBS_f0 = "";
        for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + SOCOP18.TS;
        JIBS_NumStr = JIBS_NumStr.substring(0, 5 - 1) + SORGRP.TS.substring(6 - 1, 6 + 2 - 1) + JIBS_NumStr.substring(5 + 2 - 1);
        SOCOP18.TS = Integer.parseInt(JIBS_NumStr);
        if (SORGRP.TS == null) SORGRP.TS = "";
        JIBS_tmp_int = SORGRP.TS.length();
        for (int i=0;i<26-JIBS_tmp_int;i++) SORGRP.TS += " ";
        JIBS_tmp_str[0] = "" + SOCOP18.TS;
        JIBS_f0 = "";
        for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + SOCOP18.TS;
        JIBS_NumStr = JIBS_NumStr.substring(0, 7 - 1) + SORGRP.TS.substring(9 - 1, 9 + 2 - 1) + JIBS_NumStr.substring(7 + 2 - 1);
        SOCOP18.TS = Integer.parseInt(JIBS_NumStr);
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
