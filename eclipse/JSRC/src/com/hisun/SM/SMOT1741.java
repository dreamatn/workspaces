package com.hisun.SM;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCFTLRQ;
import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPCPQBRN;
import com.hisun.BP.BPCXP23;
import com.hisun.BP.BPRMSG;
import com.hisun.BP.BPRMSGCK;
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

public class SMOT1741 {
    boolean pgmRtn = false;
    SMOT1741_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new SMOT1741_WS_TEMP_VARIABLE();
    String WS_TSQ_REC = " ";
    BPCXP23 BPCXP23 = new BPCXP23();
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
    BPRMSG BPRRMSG = new BPRMSG();
    BPRMSGCK BPRMSGCK = new BPRMSGCK();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCPQBRN BPCPQBRN = new BPCPQBRN();
    SCCGWA SCCGWA;
    SMB1740_AWA_1740 SMB1740_AWA_1740;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SMOT1741 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "SMB1740_AWA_1740>");
        SMB1740_AWA_1740 = (SMB1740_AWA_1740) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
        IBS.init(SCCGWA, SCCPRMM);
        IBS.init(SCCGWA, SCRPRMT);
        SCCPRMM.RC.RC_APP = "SM";
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        if (WS_TEMP_VARIABLE.WS_CODE == null) WS_TEMP_VARIABLE.WS_CODE = "";
        JIBS_tmp_int = WS_TEMP_VARIABLE.WS_CODE.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) WS_TEMP_VARIABLE.WS_CODE += " ";
        if (SMB1740_AWA_1740.MSGCLASS == null) SMB1740_AWA_1740.MSGCLASS = "";
        JIBS_tmp_int = SMB1740_AWA_1740.MSGCLASS.length();
        for (int i=0;i<2-JIBS_tmp_int;i++) SMB1740_AWA_1740.MSGCLASS += " ";
        WS_TEMP_VARIABLE.WS_CODE = SMB1740_AWA_1740.MSGCLASS + WS_TEMP_VARIABLE.WS_CODE.substring(2);
        if (WS_TEMP_VARIABLE.WS_CODE == null) WS_TEMP_VARIABLE.WS_CODE = "";
        JIBS_tmp_int = WS_TEMP_VARIABLE.WS_CODE.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) WS_TEMP_VARIABLE.WS_CODE += " ";
        JIBS_tmp_str[0] = "" + SMB1740_AWA_1740.MSGCODE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        WS_TEMP_VARIABLE.WS_CODE = WS_TEMP_VARIABLE.WS_CODE.substring(0, 3 - 1) + JIBS_tmp_str[0] + WS_TEMP_VARIABLE.WS_CODE.substring(3 + 4 - 1);
        if (WS_TEMP_VARIABLE.WS_CODE == null) WS_TEMP_VARIABLE.WS_CODE = "";
        JIBS_tmp_int = WS_TEMP_VARIABLE.WS_CODE.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) WS_TEMP_VARIABLE.WS_CODE += " ";
        if (SMB1740_AWA_1740.TXT_34 == null) SMB1740_AWA_1740.TXT_34 = "";
        JIBS_tmp_int = SMB1740_AWA_1740.TXT_34.length();
        for (int i=0;i<34-JIBS_tmp_int;i++) SMB1740_AWA_1740.TXT_34 += " ";
        WS_TEMP_VARIABLE.WS_CODE = WS_TEMP_VARIABLE.WS_CODE.substring(0, 7 - 1) + SMB1740_AWA_1740.TXT_34 + WS_TEMP_VARIABLE.WS_CODE.substring(7 + 34 - 1);
        if (SMB1740_AWA_1740.FUNC == 'I') {
            B100_READ_PROCESS();
            if (pgmRtn) return;
        } else if (SMB1740_AWA_1740.FUNC == 'A') {
            B100_CHECK_INPUT();
            if (pgmRtn) return;
            B100_WRITE_PROCESS();
            if (pgmRtn) return;
        } else if (SMB1740_AWA_1740.FUNC == 'M') {
            B100_CHECK_INPUT();
            if (pgmRtn) return;
            B100_READU_PROCESS();
            if (pgmRtn) return;
            B100_REWRITE_PROCESS();
            if (pgmRtn) return;
        } else if (SMB1740_AWA_1740.FUNC == 'D') {
            B100_READ_PROCESS();
            if (pgmRtn) return;
            B100_DELETE_PROCESS();
            if (pgmRtn) return;
        } else {
            Z_RET();
            if (pgmRtn) return;
        }
        B200_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (SMB1740_AWA_1740.MSG_TYPE != 'E' 
            && SMB1740_AWA_1740.MSG_TYPE != 'W' 
            && SMB1740_AWA_1740.MSG_TYPE != 'A') {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = SMB1740_AWA_1740.MSG_TYPE_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B100_READ_PROCESS() throws IOException,SQLException,Exception {
        SCCPRMM.FUNC = '3';
        SCRPRMT.KEY.TYP = SMB1740_AWA_1740.PTYP;
        SCRPRMT.KEY.CD = WS_TEMP_VARIABLE.WS_CODE;
        SCCPRMM.EFF_DT = SMB1740_AWA_1740.EFFDATE;
        S000_CALL_SCZPRMM();
        if (pgmRtn) return;
