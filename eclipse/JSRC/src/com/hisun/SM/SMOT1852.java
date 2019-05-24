package com.hisun.SM;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCXP36;
import com.hisun.BP.BPRPPM;
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

public class SMOT1852 {
    boolean pgmRtn = false;
    SMOT1852_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new SMOT1852_WS_TEMP_VARIABLE();
    short WS_FLD_NO = 0;
    short WS_I = 0;
    short WS_J = 0;
    String WK_NAME = "        ";
    char WK_NAME_ERROR = ' ';
    short WK_I1 = 0;
    short WK_I2 = 0;
    short WK_NUM = 0;
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
    short WK_CTL_12 = 0;
    String SMOT1852_FILLER2 = " ";
    short WK_FLD_NO = 0;
    short WK_AWA_FLD_LTH = 0;
    short WK_AWA_FLD_OST = 0;
    short WK_INPT_FLD_LTH = 0;
    short WK_INPT_FLD_OST = 0;
    short WK_OUPT_FLD_LTH = 0;
    short WK_OUPT_FLD_OST = 0;
    short WK_ARRAY_NO = 0;
    short WK_ARRAY_OST = 0;
    short WK_GRP_FLD_LTH = 0;
    short WK_NXT_OSTI = 0;
    short WK_NXT_OSTO = 0;
    String WS_TSQ_REC = " ";
    BPCXP36 BPCXP36 = new BPCXP36();
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCPRMM SCCPRMM = new SCCPRMM();
    SCRPRMT SCRPRMT = new SCRPRMT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BPRPPM BPRPPMD = new BPRPPM();
    SCCGWA SCCGWA;
    SMB1850_AWA_1850 SMB1850_AWA_1850;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SMOT1852 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "SMB1850_AWA_1850>");
        SMB1850_AWA_1850 = (SMB1850_AWA_1850) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
        IBS.init(SCCGWA, SCCPRMM);
        IBS.init(SCCGWA, SCRPRMT);
        SCCPRMM.RC.RC_APP = "BP";
        SCCPRMM.DAT_PTR = SCRPRMT;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (SMB1850_AWA_1850.FUNC == 'I') {
            B100_READ_PROCESS();
            if (pgmRtn) return;
        } else if (SMB1850_AWA_1850.FUNC == 'A') {
            B100_WRITE_PROCESS();
            if (pgmRtn) return;
        } else if (SMB1850_AWA_1850.FUNC == 'M') {
            B100_READU_PROCESS();
            if (pgmRtn) return;
            B100_REWRITE_PROCESS();
            if (pgmRtn) return;
        } else if (SMB1850_AWA_1850.FUNC == 'D') {
            B100_READ_PROCESS();
            if (pgmRtn) return;
            B100_DELETE_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_FLD_NO = SMB1850_AWA_1850.FUNC_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B200_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if ((SMB1850_AWA_1850.FLD_CNT > 150) 
            || (SMB1850_AWA_1850.FUNC1 == 'A' 
            && SMB1850_AWA_1850.FLD_CNT >= 150)) {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_FLD_NO = SMB1850_AWA_1850.FLD_CNT_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (SMB1850_AWA_1850.PRM_NO == 0) {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_FLD_NO = SMB1850_AWA_1850.PRM_NO_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (!IBS.isNumeric(SMB1850_AWA_1850.PRM_TYPE+"")) {
            SMB1850_AWA_1850.PRM_TYPE = '0';
        }
        if (!IBS.isNumeric(SMB1850_AWA_1850.SEQ_NO)) {
            SMB1850_AWA_1850.SEQ_NO = "" + 0;
            JIBS_tmp_int = SMB1850_AWA_1850.SEQ_NO.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) SMB1850_AWA_1850.SEQ_NO = "0" + SMB1850_AWA_1850.SEQ_NO;
        }
    }
    public void B100_READ_PROCESS() throws IOException,SQLException,Exception {
        SCCPRMM.FUNC = '3';
        SCRPRMT.KEY.TYP = SMB1850_AWA_1850.PTYP;
        SCRPRMT.KEY.CD = SMB1850_AWA_1850.CODE;
        SCCPRMM.EFF_DT = SMB1850_AWA_1850.EFFDATE;
        S000_CALL_SCZPRMM();
        if (pgmRtn) return;
    }
    public void B100_READU_PROCESS() throws IOException,SQLException,Exception {
        SCCPRMM.FUNC = '4';
        SCRPRMT.KEY.TYP = SMB1850_AWA_1850.PTYP;
        SCRPRMT.KEY.CD = SMB1850_AWA_1850.CODE;
        SCCPRMM.EFF_DT = SMB1850_AWA_1850.EFFDATE;
        S000_CALL_SCZPRMM();
        if (pgmRtn) return;
    }
    public void B100_REWRITE_PROCESS() throws IOException,SQLException,Exception {
        SCCPRMM.FUNC = '2';
        SCRPRMT.KEY.TYP = SMB1850_AWA_1850.PTYP;
        SCRPRMT.KEY.CD = SMB1850_AWA_1850.CODE;
        SCCPRMM.EFF_DT = SMB1850_AWA_1850.EFFDATE;
        SCCPRMM.EXP_DT = SMB1850_AWA_1850.EXPDATE;
        SCRPRMT.DESC = SMB1850_AWA_1850.DESC;
        SCRPRMT.CDESC = SMB1850_AWA_1850.CDESC;
