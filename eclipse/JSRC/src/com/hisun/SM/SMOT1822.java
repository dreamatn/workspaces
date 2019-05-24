package com.hisun.SM;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCXP32;
import com.hisun.BP.BPRAWA;
import com.hisun.BP.BPRIPT;
import com.hisun.BP.BPROPT;
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

public class SMOT1822 {
    boolean pgmRtn = false;
    SMOT1822_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new SMOT1822_WS_TEMP_VARIABLE();
    short WS_FLD_NO = 0;
    short WS_I = 0;
    short WS_J = 0;
    String WK_NAME = "        ";
    char WK_NAME_ERROR = ' ';
    short WK_I1 = 0;
    short WK_I2 = 0;
    short WK_NUM = 0;
    String WS_INFO = " ";
    String WS_CODE = " ";
    Object WK_PTR;
    short WK_PNT = 0;
    short WK_PT1 = 0;
    short WK_PT2 = 0;
    short WK_I = 0;
    short WK_J = 0;
    short WK_NUM1 = 0;
    short WK_NUM2 = 0;
    short WK_NUM3 = 0;
    short WK_NUM4 = 0;
    short WK_NUM4_CNT = 0;
    short WK_GRP_NO1 = 0;
    short WK_GRP_NO2 = 0;
    short WK_QTY1 = 0;
    short WK_QTY2 = 0;
    short I = 0;
    short J = 0;
    short WK_SBSR = 0;
    short WK_CTL_12 = 0;
    String SMOT1822_FILLER2 = " ";
    short WK_FLD_NO = 0;
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
    String WS_TSQ_NM = "SMOT1822";
    String WS_TSQ_REC = " ";
    String SMMINPTR_FLD_NAME = " ";
    short SMMINPTR_FLD_SBSR = 0;
    String SMMINPTR_FLD_CTL = " ";
    char SMMINPTR_FLD_TYPE = ' ';
    int SMMINPTR_FLD_LTH = 0;
    short SMMINPTR_FLD_DCM = 0;
    char SMMINPTR_FLD_SIGN = ' ';
    int SMMINPTR_FLD_OSTI = 0;
    int SMMINPTR_FLD_OSTA = 0;
    String SMMINPTR_FLD_DNAM = " ";
    short SMMINPTR_FLD_DFMT = 0;
    String SMMINPTR_FLD_RMK = " ";
    String SMMOUPTR_FLD_NAME = " ";
    short SMMOUPTR_FLD_SBSR = 0;
    String SMMOUPTR_FLD_CTL = " ";
    char SMMOUPTR_FLD_TYP = ' ';
    int SMMOUPTR_FLD_LTH = 0;
    short SMMOUPTR_FLD_DCM = 0;
    int SMMOUPTR_FLD_OSTO = 0;
    int SMMOUPTR_FLD_OSTA = 0;
    String SMMOUPTR_FLD_RMK = " ";
    BPCXP32 BPCXP32 = new BPCXP32();
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
    BPRIPT BPRIPTD = new BPRIPT();
    BPROPT BPROPTD = new BPROPT();
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
        CEP.TRC(SCCGWA, "SMOT1822 return!");
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
        SCCPRMM.RC.RC_APP = "BP";
        SCCPRMM.DAT_PTR = SCRPRMT;
        WS_TSQ_NM = SCCGWA.COMM_AREA.TERM_ID;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (SMB1800_AWA_1800.FUNC == 'A' 
            || SMB1800_AWA_1800.FUNC == 'M') {
            B020_GET_AWADAT();
            if (pgmRtn) return;
        }
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
            B100_READ_PROCESS();
            if (pgmRtn) return;
            B100_DELETE_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_FLD_NO = SMB1800_AWA_1800.FLD_CNT_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B200_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if ((SMB1800_AWA_1800.FLD_CNT > 150) 
            || (SMB1800_AWA_1800.FUNC1 == 'A' 
            && SMB1800_AWA_1800.FLD_CNT >= 150)) {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_FLD_NO = SMB1800_AWA_1800.FLD_CNT_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (SMB1800_AWA_1800.FLD_NAME.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_FLD_NO = SMB1800_AWA_1800.FLD_NAME_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (!IBS.isNumeric(SMB1800_AWA_1800.SEQ_NO)) {
            SMB1800_AWA_1800.SEQ_NO = "" + 0;
            JIBS_tmp_int = SMB1800_AWA_1800.SEQ_NO.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) SMB1800_AWA_1800.SEQ_NO = "0" + SMB1800_AWA_1800.SEQ_NO;
        }
        WK_NAME = SMB1800_AWA_1800.FLD_NAME;
        S00_CHK_NAME();
        if (pgmRtn) return;
        if (WK_NAME_ERROR == 'Y') {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_FIELD_NAME_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_FLD_NO = SMB1800_AWA_1800.FLD_NAME_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_AWADAT() throws IOException,SQLException,Exception {
        SCCPRMM.FUNC = '3';
        SCRPRMT.KEY.TYP = "B";
        SCRPRMT.KEY.CD = SMB1800_AWA_1800.CODE;
        if (SCRPRMT.KEY.CD == null) SCRPRMT.KEY.CD = "";
        JIBS_tmp_int = SCRPRMT.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) SCRPRMT.KEY.CD += " ";
        if (SMB1800_AWA_1800.AWA_FMT == null) SMB1800_AWA_1800.AWA_FMT = "";
        JIBS_tmp_int = SMB1800_AWA_1800.AWA_FMT.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) SMB1800_AWA_1800.AWA_FMT += " ";
        SCRPRMT.KEY.CD = SCRPRMT.KEY.CD.substring(0, 4 - 1) + SMB1800_AWA_1800.AWA_FMT + SCRPRMT.KEY.CD.substring(4 + 4 - 1);
        SCCPRMM.EFF_DT = SMB1800_AWA_1800.EFFDATE;
        S000_CALL_SCZPRMM();
        if (pgmRtn) return;
        BPRAWAD.KEY.TYP = SCRPRMT.KEY.TYP;
        BPRAWAD.KEY.CD = SCRPRMT.KEY.CD;
        BPRAWAD.DESC = SCRPRMT.DESC;
        BPRAWAD.CDESC = SCRPRMT.CDESC;
