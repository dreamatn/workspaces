package com.hisun.SM;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCXP34;
import com.hisun.BP.BPRVCH;
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

public class SMOT1842 {
    boolean pgmRtn = false;
    SMOT1842_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new SMOT1842_WS_TEMP_VARIABLE();
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
    String SMOT1842_FILLER2 = " ";
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
    BPCXP34 BPCXP34 = new BPCXP34();
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCPRMM SCCPRMM = new SCCPRMM();
    SCRPRMT SCRPRMT = new SCRPRMT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BPRVCH BPRVCHD = new BPRVCH();
    SCCGWA SCCGWA;
    SMB1830_AWA_1830 SMB1830_AWA_1830;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SMOT1842 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "SMB1830_AWA_1830>");
        SMB1830_AWA_1830 = (SMB1830_AWA_1830) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
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
        if (SMB1830_AWA_1830.FUNC == 'I') {
            B100_READ_PROCESS();
            if (pgmRtn) return;
        } else if (SMB1830_AWA_1830.FUNC == 'A') {
            B100_WRITE_PROCESS();
            if (pgmRtn) return;
        } else if (SMB1830_AWA_1830.FUNC == 'M') {
            B100_READU_PROCESS();
            if (pgmRtn) return;
            B100_REWRITE_PROCESS();
            if (pgmRtn) return;
        } else if (SMB1830_AWA_1830.FUNC == 'D') {
            B100_READ_PROCESS();
            if (pgmRtn) return;
            B100_DELETE_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_FLD_NO = SMB1830_AWA_1830.FUNC_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B200_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if ((SMB1830_AWA_1830.FLD_CNT > 150) 
            || (SMB1830_AWA_1830.FUNC1 == 'A' 
            && SMB1830_AWA_1830.FLD_CNT >= 150)) {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_FLD_NO = SMB1830_AWA_1830.FLD_CNT_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (SMB1830_AWA_1830.FLD_ITEM.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_FLD_NO = SMB1830_AWA_1830.FLD_ITEM_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (!IBS.isNumeric(SMB1830_AWA_1830.SEQ_NO)) {
            SMB1830_AWA_1830.SEQ_NO = "" + 0;
            JIBS_tmp_int = SMB1830_AWA_1830.SEQ_NO.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) SMB1830_AWA_1830.SEQ_NO = "0" + SMB1830_AWA_1830.SEQ_NO;
        }
    }
    public void B100_READ_PROCESS() throws IOException,SQLException,Exception {
        SCCPRMM.FUNC = '3';
        SCRPRMT.KEY.TYP = SMB1830_AWA_1830.PTYP;
        SCRPRMT.KEY.CD = SMB1830_AWA_1830.CODE;
        SCCPRMM.EFF_DT = SMB1830_AWA_1830.EFFDATE;
        S000_CALL_SCZPRMM();
        if (pgmRtn) return;
    }
    public void B100_READU_PROCESS() throws IOException,SQLException,Exception {
        SCCPRMM.FUNC = '4';
        SCRPRMT.KEY.TYP = SMB1830_AWA_1830.PTYP;
        SCRPRMT.KEY.CD = SMB1830_AWA_1830.CODE;
        SCCPRMM.EFF_DT = SMB1830_AWA_1830.EFFDATE;
        S000_CALL_SCZPRMM();
        if (pgmRtn) return;
    }
    public void B100_REWRITE_PROCESS() throws IOException,SQLException,Exception {
        SCCPRMM.FUNC = '2';
        SCRPRMT.KEY.TYP = SMB1830_AWA_1830.PTYP;
        SCRPRMT.KEY.CD = SMB1830_AWA_1830.CODE;
        SCCPRMM.EFF_DT = SMB1830_AWA_1830.EFFDATE;
        SCCPRMM.EXP_DT = SMB1830_AWA_1830.EXPDATE;
        SCRPRMT.DESC = SMB1830_AWA_1830.DESC;
        SCRPRMT.CDESC = SMB1830_AWA_1830.CDESC;
