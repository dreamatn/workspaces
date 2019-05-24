package com.hisun.SM;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCMSG_ERROR_MSG;
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
import com.hisun.SC.SCRPRMT;

public class SMOT1740 {
    boolean pgmRtn = false;
    SMOT1740_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new SMOT1740_WS_TEMP_VARIABLE();
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
        CEP.TRC(SCCGWA, "SMOT1740 return!");
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
        SCCPRMM.DAT_PTR = SCRPRMT;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        if (SMB1740_AWA_1740.FUNC != 'A') {
            B100_READ_PROCESS();
            if (pgmRtn) return;
        }
        B200_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_READ_PROCESS() throws IOException,SQLException,Exception {
        SCCPRMM.FUNC = '3';
        SCRPRMT.KEY.TYP = SMB1740_AWA_1740.PTYP;
        SCRPRMT.KEY.CD = SMB1740_AWA_1740.CODE;
        SCCPRMM.EFF_DT = SMB1740_AWA_1740.EFFDATE;
        S000_CALL_SCZPRMM();
        if (pgmRtn) return;
    }
    public void B200_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCXP23);
        IBS.init(SCCGWA, SCCFMT);
        BPCXP23.FUNC = SMB1740_AWA_1740.FUNC;
        BPCXP23.TYPE = "MSG  ";
        BPCXP23.CODE = SCRPRMT.KEY.CD;
        BPCXP23.EFF_DATE = SMB1740_AWA_1740.EFFDATE;
        BPCXP23.EXP_DATE = SCCPRMM.EXP_DT;
        BPCXP23.DESC = SCRPRMT.DESC;
        BPCXP23.CDESC = SCRPRMT.CDESC;
        BPCXP23.FLAG1 = 0X02;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCRPRMT.DAT_TXT);
