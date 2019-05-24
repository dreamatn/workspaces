package com.hisun.SM;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SO.SORSYS;

public class SMOGMCPY {
    boolean pgmRtn = false;
    String WS_LVL = " ";
    String WS_JOB_NAME = " ";
    String WS_LINE = " ";
    int WS_RESP = 0;
    String WS_LIB = " ";
    String WS_COPYBOOK = " ";
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SORSYS SORSYS = new SORSYS();
    SCCGWA SCCGWA;
    SMCGMCPY SMCGMCPY;
    public void MP(SCCGWA SCCGWA, SMCGMCPY SMCGMCPY) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.SMCGMCPY = SMCGMCPY;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SMOGMCPY return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_GET_LVL();
        if (pgmRtn) return;
        B200_SUB_JOB();
        if (pgmRtn) return;
    }
    public void B100_GET_LVL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SORSYS);
        SOTSYS_RD = new DBParm();
        SOTSYS_RD.TableName = "SOTSYS";
        IBS.READ(SCCGWA, SORSYS, SOTSYS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ SOTSYS FAIL";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        WS_LVL = SORSYS.ENV_ID;
    }
    public void B200_SUB_JOB() throws IOException,SQLException,Exception {
        if (SMCGMCPY.APP.equalsIgnoreCase("SC")) {
            Z_RET();
            if (pgmRtn) return;
        }
        S00_SPOOLOPEN();
        if (pgmRtn) return;
        if ((SMCGMCPY.APP.equalsIgnoreCase("SC") 
            || SMCGMCPY.APP.equalsIgnoreCase("BP") 
            || SMCGMCPY.APP.equalsIgnoreCase("WF") 
            || SMCGMCPY.APP.equalsIgnoreCase("SM") 
            || SMCGMCPY.APP.equalsIgnoreCase("AU"))) {
            WS_LIB = "IBS";
        } else {
            WS_LIB = "ONL";
        }
        if (WS_COPYBOOK == null) WS_COPYBOOK = "";
        JIBS_tmp_int = WS_COPYBOOK.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) WS_COPYBOOK += " ";
        if (SMCGMCPY.APP == null) SMCGMCPY.APP = "";
        JIBS_tmp_int = SMCGMCPY.APP.length();
        for (int i=0;i<2-JIBS_tmp_int;i++) SMCGMCPY.APP += " ";
        WS_COPYBOOK = SMCGMCPY.APP + WS_COPYBOOK.substring(2);
        if (SMCGMCPY.APP.equalsIgnoreCase("SC")) {
            if (WS_COPYBOOK == null) WS_COPYBOOK = "";
            JIBS_tmp_int = WS_COPYBOOK.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) WS_COPYBOOK += " ";
            WS_COPYBOOK = WS_COPYBOOK.substring(0, 3 - 1) + "CCTLM" + WS_COPYBOOK.substring(3 + "CCTLM".length() - 1);
        } else {
            if (WS_COPYBOOK == null) WS_COPYBOOK = "";
            JIBS_tmp_int = WS_COPYBOOK.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) WS_COPYBOOK += " ";
            WS_COPYBOOK = WS_COPYBOOK.substring(0, 3 - 1) + "CMSG" + WS_COPYBOOK.substring(3 + "CMSG".length() - 1);
        }
        S00_SPOOLCLOSE();
        if (pgmRtn) return;
    }
    public void S00_SPOOLOPEN() throws IOException,SQLException,Exception {
