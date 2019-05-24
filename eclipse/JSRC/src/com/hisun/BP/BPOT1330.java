package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1330 {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    BPOT1330_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPOT1330_WS_TEMP_VARIABLE();
    char WS_SEQ_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    BPCSUSEQ BPCSUSEQ = new BPCSUSEQ();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGWA SCCGWA;
    BPC1330 BPC1330;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1330 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPC1330 = new BPC1330();
        IBS.init(SCCGWA, BPC1330);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, BPC1330);
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B001_CHECK_INPUT();
        B003_UPD_PTR_PROC();
    }
    public void B001_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPC1330.SKIP_FLG != 'Y' 
            && BPC1330.OBL_FLG == 'Y') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_OBL_FLG_ERR, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
        if (BPC1330.FIRST_NO > BPC1330.MAX_SEQ) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FIRST_NO_ERR, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
        if (BPC1330.WARN_SEQ > BPC1330.MAX_SEQ) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_WARN_NO_ERR, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
        if (BPC1330.WARN_SEQ < BPC1330.FIRST_NO) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_WARN_LESS_FIRST, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPC1330.STEP_NUM);
        if (BPC1330.STEP_NUM <= 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_STEP_NUM_MUST_INPUT, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void B003_UPD_PTR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSUSEQ);
        BPCSUSEQ.SEQ_TYPE = BPC1330.SEQ_TYPE;
        BPCSUSEQ.SEQ_CODE = BPC1330.SEQ_NAME;
        BPCSUSEQ.SEQ_DESC = BPC1330.SEQ_DESC;
        BPCSUSEQ.FIRST_NUM = BPC1330.FIRST_NO;
        BPCSUSEQ.INIT_ZERO = BPC1330.INIT_FLG;
        BPCSUSEQ.SKIP_FLG = BPC1330.SKIP_FLG;
        BPCSUSEQ.OBL_FLG = BPC1330.OBL_FLG;
        BPCSUSEQ.MAX_SEQ_NO = BPC1330.MAX_SEQ;
        BPCSUSEQ.MAX_FLG = BPC1330.MAX_FLG;
        BPCSUSEQ.WARN_SEQ_NO = BPC1330.WARN_SEQ;
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 3).trim().length() == 0) BPCSUSEQ.BR = 0;
        else BPCSUSEQ.BR = Short.parseShort(JIBS_tmp_str[0].substring(0, 3));
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(4 - 1, 4 + 3 - 1).trim().length() == 0) BPCSUSEQ.DP = 0;
        else BPCSUSEQ.DP = Short.parseShort(JIBS_tmp_str[0].substring(4 - 1, 4 + 3 - 1));
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        BPCSUSEQ.LAST_UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSUSEQ.LAST_UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSUSEQ.STEP_NUM = BPC1330.STEP_NUM;
        S000_CALL_BPZSUSEQ();
    }
    public void S000_CALL_BPZSUSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-UPD-SEQ", BPCSUSEQ);
        if (BPCSUSEQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSUSEQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERRC(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
