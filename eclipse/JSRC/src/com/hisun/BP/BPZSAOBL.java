package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.DB.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSAOBL {
    String JIBS_tmp_str[] = new String[10];
    BPZSAOBL_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPZSAOBL_WS_TEMP_VARIABLE();
    BPZSAOBL_WS_MSGID WS_MSGID = new BPZSAOBL_WS_MSGID();
    char WS_SEQ_FLG = ' ';
    BPCX02 BPCX02 = new BPCX02();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCUAOBL BPCUAOBL = new BPCUAOBL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    BPCSAOBL BPCSAOBL;
    public void MP(SCCGWA SCCGWA, BPCSAOBL BPCSAOBL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSAOBL = BPCSAOBL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSAOBL return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B032_ADD_PRE_HOLD();
        B034_OUTPUT_FOR_PRINT();
    }
    public void B032_ADD_PRE_HOLD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUAOBL);
        BPCUAOBL.SEQ_TYPE = BPCSAOBL.SEQ_TYPE;
        BPCUAOBL.SEQ_CODE = BPCSAOBL.SEQ_CODE;
        BPCUAOBL.OBL_MIN = BPCSAOBL.OBL_MIN;
        BPCUAOBL.OBL_MAX = BPCSAOBL.OBL_MAX;
        BPCUAOBL.LAST_UPD_TLR = BPCSAOBL.LAST_UPD_TLR;
        BPCUAOBL.LAST_UPD_DATE = BPCSAOBL.LAST_UPD_DATE;
        BPCUAOBL.BR = BPCSAOBL.BR;
        BPCUAOBL.DP = BPCSAOBL.DP;
        BPCUAOBL.AC_DATE = BPCSAOBL.AC_DATE;
        BPCUAOBL.TLR = BPCSAOBL.TLR;
        S000_CALL_BPZUAOBL();
    }
    public void B034_OUTPUT_FOR_PRINT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCX02);
        IBS.init(SCCGWA, SCCFMT);
        BPCX02.OBL_MIN = BPCUAOBL.OBL_MIN;
        BPCX02.OBL_MAX = BPCUAOBL.OBL_MAX;
        SCCFMT.FMTID = "BPO05";
        SCCFMT.DATA_PTR = BPCX02;
        SCCFMT.DATA_LEN = 30;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZUAOBL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-ADD-OBL", BPCUAOBL);
        if (BPCUAOBL.RC.RC_CODE != 0) {
            WS_MSGID.WS_MSG_AP = BPCUAOBL.RC.RC_MMO;
            WS_MSGID.WS_MSG_CODE = BPCUAOBL.RC.RC_CODE;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_MSGID);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
