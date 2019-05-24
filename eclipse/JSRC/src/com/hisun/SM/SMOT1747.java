package com.hisun.SM;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPCPQBNK_DATA_INFO;
import com.hisun.BP.BPCXP23;
import com.hisun.BP.BPRMSG;
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

public class SMOT1747 {
    boolean pgmRtn = false;
    String CPN_PARM_MT = "SC-PARM-MAINTAIN    ";
    SMOT1747_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new SMOT1747_WS_TEMP_VARIABLE();
    BPCXP23 BPCXP23 = new BPCXP23();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCPRMM SCCPRMM = new SCCPRMM();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BPRMSG BPRRMSG = new BPRMSG();
    SCCGWA SCCGWA;
    SMB1740_AWA_1740 SMB1740_AWA_1740;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SMOT1747 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "SMB1740_AWA_1740>");
        SMB1740_AWA_1740 = (SMB1740_AWA_1740) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_READ_PROCESS();
        if (pgmRtn) return;
        B300_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B200_READ_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRRMSG);
        IBS.init(SCCGWA, SCCPRMM);
        BPRRMSG.KEY.TYP = "MSG";
        SCCPRMM.FUNC = '3';
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
        BPRRMSG.KEY.CD = WS_TEMP_VARIABLE.WS_CODE;
