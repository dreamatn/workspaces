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

public class SMOT1744 {
    boolean pgmRtn = false;
    SMOT1744_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new SMOT1744_WS_TEMP_VARIABLE();
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
        CEP.TRC(SCCGWA, "SMOT1744 return!");
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
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        if (SMB1740_AWA_1740.FUNC == 'I') {
            B100_READ_PROCESS();
            if (pgmRtn) return;
        } else if (SMB1740_AWA_1740.FUNC == 'A') {
            B100_WRITE_PROCESS();
            if (pgmRtn) return;
        } else if (SMB1740_AWA_1740.FUNC == 'M') {
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
        SCRPRMT.KEY.CD = SMB1740_AWA_1740.CODE;
        SCCPRMM.EFF_DT = SMB1740_AWA_1740.EFFDATE;
        S000_CALL_SCZPRMM();
        if (pgmRtn) return;
    }
    public void B100_READU_PROCESS() throws IOException,SQLException,Exception {
        SCCPRMM.FUNC = '4';
        SCRPRMT.KEY.TYP = SMB1740_AWA_1740.PTYP;
        SCRPRMT.KEY.CD = SMB1740_AWA_1740.CODE;
        SCCPRMM.EFF_DT = SMB1740_AWA_1740.EFFDATE;
        S000_CALL_SCZPRMM();
        if (pgmRtn) return;
    }
    public void B100_REWRITE_PROCESS() throws IOException,SQLException,Exception {
        SCCPRMM.FUNC = '2';
        SCRPRMT.KEY.TYP = SMB1740_AWA_1740.PTYP;
        SCRPRMT.KEY.CD = SMB1740_AWA_1740.CODE;
        SCCPRMM.EFF_DT = SMB1740_AWA_1740.EFFDATE;
        SCCPRMM.EXP_DT = SMB1740_AWA_1740.EXPDATE;
        SCRPRMT.DESC = SMB1740_AWA_1740.DESC;
        SCRPRMT.CDESC = SMB1740_AWA_1740.CDESC;
        IBS.init(SCCGWA, BPRRMSG.DATA_TXT);
        BPRRMSG.DATA_TXT.MSG_TYPE = SMB1740_AWA_1740.MSG_TYPE;
        BPRRMSG.DATA_TXT.MSG_LVL.MSG_LVL1 = SMB1740_AWA_1740.MSG_LVL1;
        BPRRMSG.DATA_TXT.MSG_LVL.MSG_LVL2 = SMB1740_AWA_1740.MSG_LVL2;
        BPRRMSG.DATA_TXT.OPER_FLG = SMB1740_AWA_1740.OPER_FLG;
        BPRRMSG.DATA_TXT.OUTER_MSG1 = SMB1740_AWA_1740.OUTER_M1;
        BPRRMSG.DATA_TXT.OUTER_MSG2 = SMB1740_AWA_1740.OUTER_M2;
        BPRRMSG.DATA_TXT.OUTER_MSG3 = SMB1740_AWA_1740.OUTER_M3;
        BPRRMSG.DATA_TXT.MSG_NAME = SMB1740_AWA_1740.MSG_NAME;
        BPRRMSG.DATA_TXT.MSG_TEXT = SMB1740_AWA_1740.MSG_TEXT;
        BPRRMSG.DATA_TXT.CMSG_TEXT = SMB1740_AWA_1740.CMSGTEXT;
        BPRRMSG.DATA_TXT.MSG_88 = SMB1740_AWA_1740.MSG_88;
