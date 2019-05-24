package com.hisun.SM;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCXP63;
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
import com.hisun.SC.SCRIPFG;
import com.hisun.SC.SCRPRMT;

public class SMOT2200 {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BPX04";
    SMOT2200_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new SMOT2200_WS_TEMP_VARIABLE();
    short WS_FLD_NO = 0;
    String WS_INFO = " ";
    short WS_I = 0;
    short WS_J = 0;
    String WS_TSQ_NM = " ";
    String WS_TSQ_REC = " ";
    BPCXP63 BPCXP63 = new BPCXP63();
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCPRMM SCCPRMM = new SCCPRMM();
    SCRPRMT SCRPRMT = new SCRPRMT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCRIPFG SCRIPFG = new SCRIPFG();
    SCCGWA SCCGWA;
    SMB2200_AWA_2200 SMB2200_AWA_2200;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SMOT2200 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "SMB2200_AWA_2200>");
        SMB2200_AWA_2200 = (SMB2200_AWA_2200) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
        IBS.init(SCCGWA, SCCPRMM);
        IBS.init(SCCGWA, SCRPRMT);
        SCCPRMM.RC.RC_APP = "SM";
        SCCPRMM.DAT_PTR = SCRPRMT;
        WS_TSQ_NM = SCCGWA.COMM_AREA.TERM_ID;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SMB2200_AWA_2200.FUNC);
        CEP.TRC(SCCGWA, SMB2200_AWA_2200.TYPE);
        CEP.TRC(SCCGWA, SMB2200_AWA_2200.CODE);
        CEP.TRC(SCCGWA, SMB2200_AWA_2200.DESC);
        CEP.TRC(SCCGWA, SMB2200_AWA_2200.CDESC);
        CEP.TRC(SCCGWA, SMB2200_AWA_2200.EFF_DATE);
        CEP.TRC(SCCGWA, SMB2200_AWA_2200.EXP_DATE);
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (SMB2200_AWA_2200.FUNC == 'I') {
            B100_READ_PROCESS();
            if (pgmRtn) return;
        } else if (SMB2200_AWA_2200.FUNC == 'A') {
            B100_WRITE_PROCESS();
            if (pgmRtn) return;
        } else if (SMB2200_AWA_2200.FUNC == 'M') {
            B100_READU_PROCESS();
            if (pgmRtn) return;
            B100_REWRITE_PROCESS();
            if (pgmRtn) return;
        } else if (SMB2200_AWA_2200.FUNC == 'D') {
            B100_DELETE_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_FLD_NO = SMB2200_AWA_2200.FUNC_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B200_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (SMB2200_AWA_2200.FUNC != 'I' 
            && SMB2200_AWA_2200.FUNC != 'M' 
            && SMB2200_AWA_2200.FUNC != 'D' 
            && SMB2200_AWA_2200.FUNC != 'A') {
            CEP.TRC(SCCGWA, "AWA-FUNC ERR");
            CEP.TRC(SCCGWA, SMB2200_AWA_2200.FUNC);
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_FLD_NO = SMB2200_AWA_2200.FUNC_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B100_READ_PROCESS() throws IOException,SQLException,Exception {
        SCCPRMM.FUNC = '3';
        SCRPRMT.KEY.TYP = SMB2200_AWA_2200.TYPE;
        SCRPRMT.KEY.CD = SMB2200_AWA_2200.CODE;
        SCCPRMM.EFF_DT = SMB2200_AWA_2200.EFF_DATE;
        S000_CALL_SCZPRMM();
        if (pgmRtn) return;
    }
    public void B100_READU_PROCESS() throws IOException,SQLException,Exception {
        SCCPRMM.FUNC = '4';
        SCRPRMT.KEY.TYP = SMB2200_AWA_2200.TYPE;
        SCRPRMT.KEY.CD = SMB2200_AWA_2200.CODE;
        SCCPRMM.EFF_DT = SMB2200_AWA_2200.EFF_DATE;
        S000_CALL_SCZPRMM();
        if (pgmRtn) return;
    }
    public void B100_REWRITE_PROCESS() throws IOException,SQLException,Exception {
        SCCPRMM.FUNC = '2';
        SCRPRMT.KEY.TYP = SMB2200_AWA_2200.TYPE;
        SCRPRMT.KEY.CD = SMB2200_AWA_2200.CODE;
        SCCPRMM.EFF_DT = SMB2200_AWA_2200.EFF_DATE;
        SCCPRMM.EXP_DT = SMB2200_AWA_2200.EXP_DATE;
        SCRPRMT.DESC = SMB2200_AWA_2200.DESC;
        SCRPRMT.CDESC = SMB2200_AWA_2200.CDESC;
        B300_DATA_TXT_PROC();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCRIPFG.DATA_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], SCRPRMT.DAT_TXT);
        S000_CALL_SCZPRMM();
        if (pgmRtn) return;
    }
    public void B100_DELETE_PROCESS() throws IOException,SQLException,Exception {
        SCCPRMM.FUNC = '1';
        SCRPRMT.KEY.TYP = SMB2200_AWA_2200.TYPE;
        SCRPRMT.KEY.CD = SMB2200_AWA_2200.CODE;
        SCCPRMM.EFF_DT = SMB2200_AWA_2200.EFF_DATE;
        S000_CALL_SCZPRMM();
        if (pgmRtn) return;
    }
    public void B100_WRITE_PROCESS() throws IOException,SQLException,Exception {
        SCCPRMM.FUNC = '0';
        SCRPRMT.KEY.TYP = SMB2200_AWA_2200.TYPE;
        SCRPRMT.KEY.CD = SMB2200_AWA_2200.CODE;
        SCCPRMM.EFF_DT = SMB2200_AWA_2200.EFF_DATE;
        SCCPRMM.EXP_DT = SMB2200_AWA_2200.EXP_DATE;
        SCRPRMT.DESC = SMB2200_AWA_2200.DESC;
        SCRPRMT.CDESC = SMB2200_AWA_2200.CDESC;
        B300_DATA_TXT_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCRPRMT.KEY.TYP);
        CEP.TRC(SCCGWA, SCRPRMT.KEY.CD);
        CEP.TRC(SCCGWA, SCRPRMT.DESC);
        CEP.TRC(SCCGWA, SCRPRMT.CDESC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCRIPFG.DATA_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], SCRPRMT.DAT_TXT);
        S000_CALL_SCZPRMM();
        if (pgmRtn) return;
    }
    public void B300_DATA_TXT_PROC() throws IOException,SQLException,Exception {
        SCRIPFG.DATA_TXT.IP_ADD = SMB2200_AWA_2200.IP_ADDR;
        SCRIPFG.DATA_TXT.PORT = SMB2200_AWA_2200.PORT_ID;
        SCRIPFG.DATA_TXT.DSN_INF = SMB2200_AWA_2200.DSN_INF;
        SCRIPFG.DATA_TXT.PORT2 = SMB2200_AWA_2200.PORT_ID2;
    }
    public void B200_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCXP63);
        IBS.init(SCCGWA, SCCFMT);
        BPCXP63.TYPE = SCRPRMT.KEY.TYP;
        BPCXP63.CODE = SCRPRMT.KEY.CD;
        BPCXP63.EFF_DATE = SMB2200_AWA_2200.EFF_DATE;
        BPCXP63.EXP_DATE = SCCPRMM.EXP_DT;
        BPCXP63.DESC = SCRPRMT.DESC;
        BPCXP63.CDESC = SCRPRMT.CDESC;
