package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1320 {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    BPOT1320_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPOT1320_WS_TEMP_VARIABLE();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    BPCSASEQ BPCSASEQ = new BPCSASEQ();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGWA SCCGWA;
    BPC1320 BPC1320;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1320 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPC1320 = new BPC1320();
        IBS.init(SCCGWA, BPC1320);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, BPC1320);
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B001_CHECK_INPUT();
        B005_ADD_PTR_PROC();
    }
    public void B001_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPC1320.SEQ_TYPE);
        CEP.TRC(SCCGWA, BPC1320.SEQ_NAME);
        CEP.TRC(SCCGWA, BPC1320.SEQ_DESC);
        CEP.TRC(SCCGWA, BPC1320.INIT_FLG);
        CEP.TRC(SCCGWA, BPC1320.SKIP_FLG);
        CEP.TRC(SCCGWA, BPC1320.OBL_FLG);
        CEP.TRC(SCCGWA, BPC1320.FIRST_NO);
        CEP.TRC(SCCGWA, BPC1320.MAX_SEQ);
        CEP.TRC(SCCGWA, BPC1320.MAX_FLG);
        CEP.TRC(SCCGWA, BPC1320.WARN_SEQ);
        CEP.TRC(SCCGWA, BPC1320.STEP_NUM);
        if (BPC1320.SKIP_FLG != 'Y' 
            && BPC1320.OBL_FLG == 'Y') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_OBL_FLG_ERR, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
        if (BPC1320.FIRST_NO > BPC1320.MAX_SEQ) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FIRST_NO_ERR, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
        if (BPC1320.WARN_SEQ > BPC1320.MAX_SEQ) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_WARN_NO_ERR, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
        if (BPC1320.WARN_SEQ < BPC1320.FIRST_NO) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_WARN_LESS_FIRST, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPC1320.STEP_NUM);
        if (BPC1320.STEP_NUM <= 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_STEP_NUM_MUST_INPUT, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void B005_ADD_PTR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSASEQ);
        BPCSASEQ.SEQ_NO = 0;
        BPCSASEQ.OLD_SEQ_NO = 0;
        BPCSASEQ.SEQ_TYPE = BPC1320.SEQ_TYPE;
        BPCSASEQ.SEQ_CODE = BPC1320.SEQ_NAME;
        BPCSASEQ.SEQ_DESC = BPC1320.SEQ_DESC;
        BPCSASEQ.FIRST_NUM = BPC1320.FIRST_NO;
        BPCSASEQ.INIT_ZERO = BPC1320.INIT_FLG;
        BPCSASEQ.SKIP_FLG = BPC1320.SKIP_FLG;
        BPCSASEQ.OBL_FLG = BPC1320.OBL_FLG;
        BPCSASEQ.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSASEQ.MAX_SEQ_NO = BPC1320.MAX_SEQ;
        BPCSASEQ.MAX_FLG = BPC1320.MAX_FLG;
        BPCSASEQ.WARN_SEQ_NO = BPC1320.WARN_SEQ;
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 3).trim().length() == 0) BPCSASEQ.BR = 0;
        else BPCSASEQ.BR = Short.parseShort(JIBS_tmp_str[0].substring(0, 3));
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(4 - 1, 4 + 3 - 1).trim().length() == 0) BPCSASEQ.DP = 0;
        else BPCSASEQ.DP = Short.parseShort(JIBS_tmp_str[0].substring(4 - 1, 4 + 3 - 1));
        BPCSASEQ.LAST_UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSASEQ.LAST_UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSASEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSASEQ.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSASEQ.STEP_NUM = BPC1320.STEP_NUM;
        S000_CALL_BPZSASEQ();
    }
    public void S000_CALL_BPZSASEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-ADD-SEQ", BPCSASEQ);
        if (BPCSASEQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSASEQ.RC);
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
