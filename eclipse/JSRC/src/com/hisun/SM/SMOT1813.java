package com.hisun.SM;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPCXP53;
import com.hisun.BP.BPRAWA;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCPRMM;
import com.hisun.SC.SCCWOUT;
import com.hisun.SC.SCRPRMT;

public class SMOT1813 {
    boolean pgmRtn = false;
    SMOT1813_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new SMOT1813_WS_TEMP_VARIABLE();
    short WS_I = 0;
    String WS_TSQ_REC = " ";
    BPCXP53 BPCXP53 = new BPCXP53();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCPRMM SCCPRMM = new SCCPRMM();
    SCRPRMT SCRPRMT = new SCRPRMT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BPRAWA BPRAWAD = new BPRAWA();
    SCCGWA SCCGWA;
    SMB1800_AWA_1800 SMB1800_AWA_1800;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SMOT1813 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "SMB1800_AWA_1800>");
        SMB1800_AWA_1800 = (SMB1800_AWA_1800) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
        IBS.init(SCCGWA, SCCPRMM);
        IBS.init(SCCGWA, SCRPRMT);
        SCCPRMM.RC.RC_APP = "SM";
        SCCPRMM.DAT_PTR = SCRPRMT;
        WS_TSQ_REC = "" + SMB1800_AWA_1800.FUNC;
        JIBS_tmp_int = WS_TSQ_REC.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) WS_TSQ_REC = "0" + WS_TSQ_REC;
        if (WS_TSQ_REC == null) WS_TSQ_REC = "";
        JIBS_tmp_int = WS_TSQ_REC.length();
        for (int i=0;i<100-JIBS_tmp_int;i++) WS_TSQ_REC += " ";
        JIBS_tmp_str[0] = "" + SMB1800_AWA_1800.FUNC1;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        WS_TSQ_REC = WS_TSQ_REC.substring(0, 2 - 1) + JIBS_tmp_str[0] + WS_TSQ_REC.substring(2 + 1 - 1);
        if (WS_TSQ_REC == null) WS_TSQ_REC = "";
        JIBS_tmp_int = WS_TSQ_REC.length();
        for (int i=0;i<100-JIBS_tmp_int;i++) WS_TSQ_REC += " ";
        if (SMB1800_AWA_1800.AWA_FMT == null) SMB1800_AWA_1800.AWA_FMT = "";
        JIBS_tmp_int = SMB1800_AWA_1800.AWA_FMT.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) SMB1800_AWA_1800.AWA_FMT += " ";
        WS_TSQ_REC = WS_TSQ_REC.substring(0, 3 - 1) + SMB1800_AWA_1800.AWA_FMT + WS_TSQ_REC.substring(3 + 4 - 1);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_READ_PROCESS();
        if (pgmRtn) return;
        B200_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_READ_PROCESS() throws IOException,SQLException,Exception {
        SCCPRMM.FUNC = '3';
        SCRPRMT.KEY.TYP = "B";
        if (SMB1800_AWA_1800.CODE == null) SMB1800_AWA_1800.CODE = "";
        JIBS_tmp_int = SMB1800_AWA_1800.CODE.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) SMB1800_AWA_1800.CODE += " ";
        if (WS_TEMP_VARIABLE.WS_CODE == null) WS_TEMP_VARIABLE.WS_CODE = "";
        JIBS_tmp_int = WS_TEMP_VARIABLE.WS_CODE.length();
        for (int i=0;i<7-JIBS_tmp_int;i++) WS_TEMP_VARIABLE.WS_CODE += " ";
        WS_TEMP_VARIABLE.WS_CODE = SMB1800_AWA_1800.CODE.substring(0, 3) + WS_TEMP_VARIABLE.WS_CODE.substring(3);
        if (WS_TEMP_VARIABLE.WS_CODE == null) WS_TEMP_VARIABLE.WS_CODE = "";
        JIBS_tmp_int = WS_TEMP_VARIABLE.WS_CODE.length();
        for (int i=0;i<7-JIBS_tmp_int;i++) WS_TEMP_VARIABLE.WS_CODE += " ";
        if (SMB1800_AWA_1800.AWA_FMT == null) SMB1800_AWA_1800.AWA_FMT = "";
        JIBS_tmp_int = SMB1800_AWA_1800.AWA_FMT.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) SMB1800_AWA_1800.AWA_FMT += " ";
        WS_TEMP_VARIABLE.WS_CODE = WS_TEMP_VARIABLE.WS_CODE.substring(0, 4 - 1) + SMB1800_AWA_1800.AWA_FMT + WS_TEMP_VARIABLE.WS_CODE.substring(4 + 4 - 1);
        SCRPRMT.KEY.CD = WS_TEMP_VARIABLE.WS_CODE;
        SCCPRMM.EFF_DT = SMB1800_AWA_1800.EFFDATE;
        CEP.TRC(SCCGWA, SCRPRMT.KEY.TYP);
        CEP.TRC(SCCGWA, SCRPRMT.KEY.CD);
        CEP.TRC(SCCGWA, SCCPRMM.EFF_DT);
        S000_CALL_SCZPRMM();
        if (pgmRtn) return;
    }
    public void B200_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCXP53);
        IBS.init(SCCGWA, SCCFMT);
