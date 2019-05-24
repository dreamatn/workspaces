package com.hisun.SM;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCRMBPM;
import com.hisun.BP.BPCXP31;
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
import com.hisun.SC.SCRPARM;
import com.hisun.SC.SCRPRMT;

public class SMOT1805 {
    boolean pgmRtn = false;
    SMOT1805_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new SMOT1805_WS_TEMP_VARIABLE();
    short WS_FLD_NO = 0;
    short WS_I = 0;
    short WS_J = 0;
    String WK_NAME = "        ";
    char WK_NAME_ERROR = ' ';
    short WK_I1 = 0;
    short WK_I2 = 0;
    short WK_NUM = 0;
    String WS_CODE = " ";
    String WS_EFF_DATE = " ";
    String WS_EXP_DATE = " ";
    String WK_TYPE = " ";
    short WK_AP = 0;
    String WK_AWA_FMT = " ";
    String WK_FILLER = " ";
    String WK_LINK_PRGM = "SMOGCPY";
    short WK_REC_LEN = 0;
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
    String SMOT1805_FILLER2 = " ";
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
    String WS_TSQ_NM = "SMOT1805";
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
    BPCXP31 BPCXP31 = new BPCXP31();
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCPRMM SCCPRMM = new SCCPRMM();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    SCRPRMT SCRPRMT = new SCRPRMT();
    SCRPARM SCRPARM = new SCRPARM();
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
        CEP.TRC(SCCGWA, "SMOT1805 return!");
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
        IBS.init(SCCGWA, BPCRMBPM);
        IBS.init(SCCGWA, SCRPRMT);
        IBS.init(SCCGWA, SCRPARM);
        SCCPRMM.RC.RC_APP = "BP";
        BPCRMBPM.RC.RC_MMO = "BP";
        SCCPRMM.DAT_PTR = SCRPRMT;
        BPCRMBPM.PTR = SCRPARM;
        WS_TSQ_NM = SCCGWA.COMM_AREA.TERM_ID;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        if (SMB1800_AWA_1800.FUNC == 'C') {
            B010_CHECK_INPUT();
            if (pgmRtn) return;
            B020_GET_AWADAT();
            if (pgmRtn) return;
            B030_MAIN_I_PROC();
            if (pgmRtn) return;
            B030_MAIN_O_PROC();
            if (pgmRtn) return;
        } else {
            B200_LINK_SMOGCPY();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SMB1800_AWA_1800.PTYP);
        if (!SMB1800_AWA_1800.PTYP.equalsIgnoreCase("B")) {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_FLD_NO = SMB1800_AWA_1800.PTYP_NO;
            WS_TEMP_VARIABLE.WS_INFO = "TYPE IS " + SMB1800_AWA_1800.PTYP + " BUT MUST BE B";
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (SMB1800_AWA_1800.CODE.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_FLD_NO = SMB1800_AWA_1800.CODE_NO;
            WS_TEMP_VARIABLE.WS_INFO = "CODE IS " + SMB1800_AWA_1800.CODE + " BUT MUST NOT BE SPACE";
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_AWADAT() throws IOException,SQLException,Exception {
        SCCPRMM.FUNC = '3';
        SCRPRMT.KEY.TYP = SMB1800_AWA_1800.PTYP;
        SCRPRMT.KEY.CD = SMB1800_AWA_1800.CODE;
        SCCPRMM.EFF_DT = SMB1800_AWA_1800.EFFDATE;
        CEP.TRC(SCCGWA, SMB1800_AWA_1800.PTYP);
        CEP.TRC(SCCGWA, SMB1800_AWA_1800.CODE);
        CEP.TRC(SCCGWA, SMB1800_AWA_1800.EFFDATE);
        S000_CALL_SCZPRMM();
        if (pgmRtn) return;
        BPRAWAD.KEY.TYP = SCRPRMT.KEY.TYP;
        BPRAWAD.KEY.CD = SCRPRMT.KEY.CD;
        BPRAWAD.DESC = SCRPRMT.DESC;
        BPRAWAD.CDESC = SCRPRMT.CDESC;
