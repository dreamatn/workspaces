package com.hisun.SM;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPRAPT;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCPRMR;
import com.hisun.SO.SORSYS;

public class SMOGCPY {
    boolean pgmRtn = false;
    String WS_LVL = " ";
    String WS_JOB_NAME = " ";
    String WS_LINE = " ";
    int WS_RESP = 0;
    String WS_COPYBOOK = " ";
    char WS_TYPE = ' ';
    String WS_LIB = " ";
    String WS_AP_MMO = " ";
    SMOGCPY_WS_CODE WS_CODE = new SMOGCPY_WS_CODE();
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SORSYS SORSYS = new SORSYS();
    BPRAPT BPRAPT = new BPRAPT();
    SCCPRMR SCCPRMR = new SCCPRMR();
    SCCGWA SCCGWA;
    SMB1800_AWA_1800 SMB1800_AWA_1800;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SMOGCPY return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "SMB1800_AWA_1800>");
        SMB1800_AWA_1800 = (SMB1800_AWA_1800) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        if (SMB1800_AWA_1800.PTYP == null) SMB1800_AWA_1800.PTYP = "";
        JIBS_tmp_int = SMB1800_AWA_1800.PTYP.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) SMB1800_AWA_1800.PTYP += " ";
        WS_TYPE = SMB1800_AWA_1800.PTYP.substring(0, 1).charAt(0);
        if (WS_JOB_NAME == null) WS_JOB_NAME = "";
        JIBS_tmp_int = WS_JOB_NAME.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) WS_JOB_NAME += " ";
        WS_JOB_NAME = SMB1800_AWA_1800.PTYP.substring(0, 1) + WS_JOB_NAME.substring(1);
        if (SMB1800_AWA_1800.CODE == null) SMB1800_AWA_1800.CODE = "";
        JIBS_tmp_int = SMB1800_AWA_1800.CODE.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) SMB1800_AWA_1800.CODE += " ";
        IBS.CPY2CLS(SCCGWA, SMB1800_AWA_1800.CODE.substring(0, 7), WS_CODE);
        if (WS_JOB_NAME == null) WS_JOB_NAME = "";
        JIBS_tmp_int = WS_JOB_NAME.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) WS_JOB_NAME += " ";
        WS_JOB_NAME = WS_JOB_NAME.substring(0, 2 - 1) + SMB1800_AWA_1800.CODE.substring(0, 7) + WS_JOB_NAME.substring(2 + 7 - 1);
        if (SMB1800_AWA_1800.PTYP == null) SMB1800_AWA_1800.PTYP = "";
        JIBS_tmp_int = SMB1800_AWA_1800.PTYP.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) SMB1800_AWA_1800.PTYP += " ";
        if (!SMB1800_AWA_1800.PTYP.substring(0, 1).equalsIgnoreCase("B") 
            && !SMB1800_AWA_1800.PTYP.substring(0, 1).equalsIgnoreCase("I") 
            && !SMB1800_AWA_1800.PTYP.substring(0, 1).equalsIgnoreCase("O")) {
            CEP.ERR(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR);
        }
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_GET_LVL();
        if (pgmRtn) return;
        B200_GET_AP_MMO();
        if (pgmRtn) return;
        B300_SUB_JOB();
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
    public void B200_GET_AP_MMO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRAPT);
        BPRAPT.KEY.TYP = "APT";
        BPRAPT.KEY.CD = "" + WS_CODE.WS_TR_AP;
        JIBS_tmp_int = BPRAPT.KEY.CD.length();
        for (int i=0;i<3-JIBS_tmp_int;i++) BPRAPT.KEY.CD = "0" + BPRAPT.KEY.CD;
        IBS.init(SCCGWA, SCCPRMR);
        SCCPRMR.DAT_PTR = BPRAPT;
        IBS.CALLCPN(SCCGWA, "SC-PARM-READ", SCCPRMR);
        if (SCCPRMR.RC.RC_RTNCODE != 0) {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ APT " + BPRAPT.KEY.CD + " FAIL";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        WS_AP_MMO = BPRAPT.DATA_TXT.AP_MMO;
    }
    public void B300_SUB_JOB() throws IOException,SQLException,Exception {
        if (WS_COPYBOOK == null) WS_COPYBOOK = "";
        JIBS_tmp_int = WS_COPYBOOK.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) WS_COPYBOOK += " ";
        if (WS_AP_MMO == null) WS_AP_MMO = "";
        JIBS_tmp_int = WS_AP_MMO.length();
        for (int i=0;i<2-JIBS_tmp_int;i++) WS_AP_MMO += " ";
        WS_COPYBOOK = WS_AP_MMO + WS_COPYBOOK.substring(2);
        if (WS_TYPE == 'B') {
            if (WS_COPYBOOK == null) WS_COPYBOOK = "";
            JIBS_tmp_int = WS_COPYBOOK.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) WS_COPYBOOK += " ";
            WS_COPYBOOK = WS_COPYBOOK.substring(0, 3 - 1) + "B" + WS_COPYBOOK.substring(3 + 1 - 1);
            if (WS_COPYBOOK == null) WS_COPYBOOK = "";
            JIBS_tmp_int = WS_COPYBOOK.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) WS_COPYBOOK += " ";
            if (WS_CODE.WS_FMT == null) WS_CODE.WS_FMT = "";
            JIBS_tmp_int = WS_CODE.WS_FMT.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_CODE.WS_FMT += " ";
            WS_COPYBOOK = WS_COPYBOOK.substring(0, 4 - 1) + WS_CODE.WS_FMT + WS_COPYBOOK.substring(4 + 4 - 1);
        } else if (WS_TYPE == 'I') {
            if (WS_COPYBOOK == null) WS_COPYBOOK = "";
            JIBS_tmp_int = WS_COPYBOOK.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) WS_COPYBOOK += " ";
            WS_COPYBOOK = WS_COPYBOOK.substring(0, 3 - 1) + "CI" + WS_COPYBOOK.substring(3 + 2 - 1);
            if (WS_COPYBOOK == null) WS_COPYBOOK = "";
            JIBS_tmp_int = WS_COPYBOOK.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) WS_COPYBOOK += " ";
            if (WS_CODE.WS_FMT == null) WS_CODE.WS_FMT = "";
            JIBS_tmp_int = WS_CODE.WS_FMT.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_CODE.WS_FMT += " ";
            WS_COPYBOOK = WS_COPYBOOK.substring(0, 5 - 1) + WS_CODE.WS_FMT + WS_COPYBOOK.substring(5 + 4 - 1);
        } else if (WS_TYPE == 'O') {
            if (WS_COPYBOOK == null) WS_COPYBOOK = "";
            JIBS_tmp_int = WS_COPYBOOK.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) WS_COPYBOOK += " ";
            WS_COPYBOOK = WS_COPYBOOK.substring(0, 3 - 1) + "CO" + WS_COPYBOOK.substring(3 + 2 - 1);
            if (WS_COPYBOOK == null) WS_COPYBOOK = "";
            JIBS_tmp_int = WS_COPYBOOK.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) WS_COPYBOOK += " ";
            if (WS_CODE.WS_FMT == null) WS_CODE.WS_FMT = "";
            JIBS_tmp_int = WS_CODE.WS_FMT.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_CODE.WS_FMT += " ";
            WS_COPYBOOK = WS_COPYBOOK.substring(0, 5 - 1) + WS_CODE.WS_FMT + WS_COPYBOOK.substring(5 + 4 - 1);
        } else {
            SCCGWA.RETURN_CODE = 12;
            CEP.TRC(SCCGWA, "INVALID WS-TYPE: ");
            CEP.TRC(SCCGWA, WS_TYPE);
            Z_RET();
            if (pgmRtn) return;
        }
        if ((WS_AP_MMO.equalsIgnoreCase("SC") 
            || WS_AP_MMO.equalsIgnoreCase("BP") 
            || WS_AP_MMO.equalsIgnoreCase("SO") 
            || WS_AP_MMO.equalsIgnoreCase("SM"))) {
            WS_LIB = "IBS";
        } else {
            WS_LIB = "ONL";
        }
        S00_SPOOLOPEN();
        if (pgmRtn) return;
        S00_SPOOLCLOSE();
        if (pgmRtn) return;
    }
    public void S00_SPOOLOPEN() throws IOException,SQLException,Exception {
