package com.hisun.SM;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCXP30;
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

public class SMOT1802 {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BPP43";
    String CPN_DDIC_MAINTAIN = "SM-S_DDIC_MAINTAIN  ";
    SMOT1802_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new SMOT1802_WS_TEMP_VARIABLE();
    short WS_FLD_NO = 0;
    short WS_I = 0;
    short WS_J = 0;
    short WS_CNT = 0;
    String WK_NAME = "        ";
    char WK_NAME_ERROR = ' ';
    char WK_NAME_FIRST_ERROR = ' ';
    short WK_I1 = 0;
    short WK_I2 = 0;
    short WK_NUM = 0;
    short WK_NUM1 = 0;
    String WS_CODE = " ";
    String WS_EFF_DATE = " ";
    String WS_EXP_DATE = " ";
    int WK_AWA_FLD_LTH = 0;
    int WK_AWA_FLD_OST = 0;
    int WK_INPT_FLD_LTH = 0;
    int WK_INPT_FLD_OST = 0;
    int WK_OUPT_FLD_LTH = 0;
    int WK_OUPT_FLD_OST = 0;
    short WK_ARRAY_NO = 0;
    int WK_ARRAY_OST = 0;
    int WK_GRP_FLD_LTH = 0;
    int WK_NXT_OSTI = 0;
    int WK_NXT_OSTO = 0;
    String WS_TSQ_NM = " ";
    String WS_TSQ_REC = " ";
    int WK_NAME_POS = 0;
    BPCXP30 BPCXP30 = new BPCXP30();
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
    SMCODICM SMCODICM = new SMCODICM();
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
        CEP.TRC(SCCGWA, "SMOT1802 return!");
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
        WS_TSQ_NM = SCCGWA.COMM_AREA.TERM_ID;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (SMB1800_AWA_1800.FUNC == 'I') {
            B100_READ_PROCESS();
            if (pgmRtn) return;
        } else if (SMB1800_AWA_1800.FUNC == 'A') {
            B100_WRITE_PROCESS();
            if (pgmRtn) return;
        } else if (SMB1800_AWA_1800.FUNC == 'M') {
            B100_READU_PROCESS();
            if (pgmRtn) return;
            B100_REWRITE_PROCESS();
            if (pgmRtn) return;
        } else if (SMB1800_AWA_1800.FUNC == 'D') {
            B100_DELETE_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_FLD_NO = SMB1800_AWA_1800.FUNC_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B200_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if ((SMB1800_AWA_1800.FLD_CNT > 180) 
            || (SMB1800_AWA_1800.FUNC1 == 'A' 
            && SMB1800_AWA_1800.FLD_CNT >= 180)) {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_FLD_NO = SMB1800_AWA_1800.FLD_CNT_NO;
            WS_TEMP_VARIABLE.WS_INFO = "AWA-FLD-CNT IS  " + SMB1800_AWA_1800.FLD_CNT + "MUST<=180 OR <180" + "WHEN AWA-FUNC1 IS A";
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((SMB1800_AWA_1800.FLD_NAME.trim().length() == 0) 
            && (SMB1800_AWA_1800.FUNC == 'A' 
            || SMB1800_AWA_1800.FUNC == 'M')) {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_FLD_NO = SMB1800_AWA_1800.FLD_NAME_NO;
            WS_TEMP_VARIABLE.WS_INFO = "AWA-FLD-NAME IS " + SMB1800_AWA_1800.FLD_NAME + "MUST NOT BE SPACE" + "WHEN AWA-FUNC1 IS A OR M";
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (!IBS.isNumeric(SMB1800_AWA_1800.SEQ_NO)) {
            SMB1800_AWA_1800.SEQ_NO = "" + 0;
            JIBS_tmp_int = SMB1800_AWA_1800.SEQ_NO.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) SMB1800_AWA_1800.SEQ_NO = "0" + SMB1800_AWA_1800.SEQ_NO;
        }
        WK_NAME_ERROR = 'N';
        WK_NAME = SMB1800_AWA_1800.FLD_NAME;
        S00_CHK_NAME();
        if (pgmRtn) return;
        if (WK_NAME_ERROR == 'Y') {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_FIELD_NAME_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_FLD_NO = SMB1800_AWA_1800.FLD_NAME_NO;
            CEP.TRC(SCCGWA, WS_FLD_NO);
            if (WK_NAME == null) WK_NAME = "";
            JIBS_tmp_int = WK_NAME.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) WK_NAME += " ";
            WS_TEMP_VARIABLE.WS_INFO = WK_NAME_POS + " POSITION OF " + WK_NAME + " IS " + WS_FLD_NO + WK_NAME + " BUT MUST BE NUM/ALPHA/-";
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WK_NAME_FIRST_ERROR == 'Y') {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_FIELD_NAME_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_FLD_NO = SMB1800_AWA_1800.FLD_NAME_NO;
            if (WK_NAME == null) WK_NAME = "";
            JIBS_tmp_int = WK_NAME.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) WK_NAME += " ";
            WS_TEMP_VARIABLE.WS_INFO = "FIRST POSITION OF  " + WK_NAME + " IS " + WK_NAME + " BUT MUST BE ALPHA/-";
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (!IBS.isNumeric(SMB1800_AWA_1800.FLD_DFMT+"")) {
            SMB1800_AWA_1800.FLD_DFMT = 0;
        }
    }
    public void B100_READ_PROCESS() throws IOException,SQLException,Exception {
        SCCPRMM.FUNC = '3';
        SCRPRMT.KEY.TYP = SMB1800_AWA_1800.PTYP;
        SCRPRMT.KEY.CD = SMB1800_AWA_1800.CODE;
        SCCPRMM.EFF_DT = SMB1800_AWA_1800.EFFDATE;
        S000_CALL_SCZPRMM();
        if (pgmRtn) return;
    }
    public void B100_READU_PROCESS() throws IOException,SQLException,Exception {
        SCCPRMM.FUNC = '4';
        SCRPRMT.KEY.TYP = SMB1800_AWA_1800.PTYP;
        SCRPRMT.KEY.CD = SMB1800_AWA_1800.CODE;
        SCCPRMM.EFF_DT = SMB1800_AWA_1800.EFFDATE;
        S000_CALL_SCZPRMM();
        if (pgmRtn) return;
    }
    public void B100_REWRITE_PROCESS() throws IOException,SQLException,Exception {
        SCCPRMM.FUNC = '2';
        SCRPRMT.KEY.TYP = SMB1800_AWA_1800.PTYP;
        SCRPRMT.KEY.CD = SMB1800_AWA_1800.CODE;
        SCCPRMM.EFF_DT = SMB1800_AWA_1800.EFFDATE;
        SCCPRMM.EXP_DT = SMB1800_AWA_1800.EXPDATE;
        SCRPRMT.DESC = SMB1800_AWA_1800.DESC;
        SCRPRMT.CDESC = SMB1800_AWA_1800.CDESC;
