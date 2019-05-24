package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1346 {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    BPOT1346_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPOT1346_WS_TEMP_VARIABLE();
    char WS_SEQ_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    BPCSAOBL BPCSAOBL = new BPCSAOBL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGWA SCCGWA;
    BPC1346 BPC1346;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1346 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPC1346 = new BPC1346();
        IBS.init(SCCGWA, BPC1346);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, BPC1346);
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B001_CHECK_INPUT();
        B032_ADD_PRE_HOLD();
    }
    public void B001_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPC1346.OBL_MIN > BPC1346.OBL_MAX) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_OBL_MIN_ERR, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void B032_ADD_PRE_HOLD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSAOBL);
        BPCSAOBL.SEQ_TYPE = BPC1346.SEQ_TYPE;
        BPCSAOBL.SEQ_CODE = BPC1346.SEQ_NAME;
        BPCSAOBL.OBL_MIN = BPC1346.OBL_MIN;
        BPCSAOBL.OBL_MAX = BPC1346.OBL_MAX;
        BPCSAOBL.LAST_UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSAOBL.LAST_UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 3).trim().length() == 0) BPCSAOBL.BR = 0;
        else BPCSAOBL.BR = Short.parseShort(JIBS_tmp_str[0].substring(0, 3));
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(4 - 1, 4 + 3 - 1).trim().length() == 0) BPCSAOBL.DP = 0;
        else BPCSAOBL.DP = Short.parseShort(JIBS_tmp_str[0].substring(4 - 1, 4 + 3 - 1));
        BPCSAOBL.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSAOBL.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZSAOBL();
    }
    public void S000_CALL_BPZSAOBL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-ADD-OBL", BPCSAOBL);
        if (BPCSAOBL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSAOBL.RC);
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
