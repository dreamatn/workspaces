package com.hisun.SO;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.BP.*;

import java.util.Date;
import java.text.SimpleDateFormat;

import java.io.IOException;
import java.sql.SQLException;

public class SOZMBATD {
    SimpleDateFormat JIBS_sdf;
    Date JIBS_date;
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String JIBS_NumStr;
    String JIBS_f0;
    SOZMBATD_WS_WORKING_VARIABLES WS_WORKING_VARIABLES = new SOZMBATD_WS_WORKING_VARIABLES();
    SOCMSG SOCMSG = new SOCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SOCCPSW SOCCPSW = new SOCCPSW();
    BPCSBATD BPCSBATD = new BPCSBATD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGWA_WK_INPUT_AREA WK_INPUT_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        B00_MAIN_PROC();
        CEP.TRC(SCCGWA, "SOZMBATD return!");
        Z_RET();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_WORKING_VARIABLES);
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        WK_INPUT_AREA = new SCCGWA_WK_INPUT_AREA();
        IBS.init(SCCGWA, WK_INPUT_AREA);
        IBS.CPY2CLS(SCCGWA, SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, WK_INPUT_AREA);
        JIBS_sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        JIBS_date = new Date();
        WS_WORKING_VARIABLES.TS = JIBS_sdf.format(JIBS_date);
        CEP.TRC(SCCGWA, WS_WORKING_VARIABLES.TS);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBATD);
        IBS.init(SCCGWA, SOCCPSW);
        CEP.TRC(SCCGWA, WK_INPUT_AREA.TELLER_NO);
        CEP.TRC(SCCGWA, WK_INPUT_AREA.PASSWORD);
        SOCCPSW.TL_ID = WK_INPUT_AREA.TELLER_NO;
        SOCCPSW.PSW = WK_INPUT_AREA.PASSWORD;
        SOZCPSW SOZCPSW = new SOZCPSW();
        SOZCPSW.MP(SCCGWA, SOCCPSW);
        BPCSBATD.DATA.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSBATD.DATA.STATUS = 'N';
        BPCSBATD.FUNC = 'F';
        S000_CALL_BPZSBATD();
        if (WS_WORKING_VARIABLES.TS == null) WS_WORKING_VARIABLES.TS = "";
        JIBS_tmp_int = WS_WORKING_VARIABLES.TS.length();
        for (int i=0;i<26-JIBS_tmp_int;i++) WS_WORKING_VARIABLES.TS += " ";
        JIBS_tmp_str[0] = "" + WS_WORKING_VARIABLES.TIME;
        JIBS_f0 = "";
        for (int i=0;i<6-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + WS_WORKING_VARIABLES.TIME;
        JIBS_NumStr = WS_WORKING_VARIABLES.TS.substring(12 - 1, 12 + 2 - 1) + JIBS_NumStr.substring(2);
        WS_WORKING_VARIABLES.TIME = Integer.parseInt(JIBS_NumStr);
        if (WS_WORKING_VARIABLES.TS == null) WS_WORKING_VARIABLES.TS = "";
        JIBS_tmp_int = WS_WORKING_VARIABLES.TS.length();
        for (int i=0;i<26-JIBS_tmp_int;i++) WS_WORKING_VARIABLES.TS += " ";
        JIBS_tmp_str[0] = "" + WS_WORKING_VARIABLES.TIME;
        JIBS_f0 = "";
        for (int i=0;i<6-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + WS_WORKING_VARIABLES.TIME;
        JIBS_NumStr = JIBS_NumStr.substring(0, 3 - 1) + WS_WORKING_VARIABLES.TS.substring(15 - 1, 15 + 2 - 1) + JIBS_NumStr.substring(3 + 2 - 1);
        WS_WORKING_VARIABLES.TIME = Integer.parseInt(JIBS_NumStr);
        if (WS_WORKING_VARIABLES.TS == null) WS_WORKING_VARIABLES.TS = "";
        JIBS_tmp_int = WS_WORKING_VARIABLES.TS.length();
        for (int i=0;i<26-JIBS_tmp_int;i++) WS_WORKING_VARIABLES.TS += " ";
        JIBS_tmp_str[0] = "" + WS_WORKING_VARIABLES.TIME;
        JIBS_f0 = "";
        for (int i=0;i<6-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + WS_WORKING_VARIABLES.TIME;
        JIBS_NumStr = JIBS_NumStr.substring(0, 5 - 1) + WS_WORKING_VARIABLES.TS.substring(18 - 1, 18 + 2 - 1) + JIBS_NumStr.substring(5 + 2 - 1);
        WS_WORKING_VARIABLES.TIME = Integer.parseInt(JIBS_NumStr);
        if (BPCSBATD.DATA.BAT_TIME >= WS_WORKING_VARIABLES.TIME) {
            WS_WORKING_VARIABLES.MSGID = "" + SOCMSG.BTR_WAIT;
            JIBS_tmp_int = WS_WORKING_VARIABLES.MSGID.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_WORKING_VARIABLES.MSGID = "0" + WS_WORKING_VARIABLES.MSGID;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZSBATD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-BATD-PROC", BPCSBATD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_WORKING_VARIABLES.MSGID);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
