package com.hisun.SM;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGCPT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCWOUT;
import com.hisun.BP.BPRTRT;
import com.hisun.DB.*;

import java.io.IOException;
import java.sql.SQLException;

public class SMZSUWF {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    SMZSUWF_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new SMZSUWF_WS_TEMP_VARIABLE();
    char WS_SEQ_FLG = ' ';
    SMCX01 SMCX01 = new SMCX01();
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SMCUUWF SMCUUWF = new SMCUUWF();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGCPT SCCGCPT;
    BPRTRT SMRTRTT;
    SMCSUWF SMCSUWF;
    public void MP(SCCGWA SCCGWA, SMCSUWF SMCSUWF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.SMCSUWF = SMCSUWF;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SMZSUWF return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B001_UPD_RECORD();
        if (pgmRtn) return;
        B002_OUTPUT_FOR_SUB_TX();
        if (pgmRtn) return;
    }
    public void B001_UPD_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SMCUUWF);
        SMCUUWF.RC.CODE = SMCSUWF.CODE;
        SMCUUWF.RC.NAME = SMCSUWF.NAME;
        SMCUUWF.RC.TYPE = SMCSUWF.TYPE;
        SMCUUWF.RC.FIRST_Q = SMCSUWF.FIRST_Q;
        SMCUUWF.RC.CMP_NAME = SMCSUWF.CMP_NAME;
        SMCUUWF.RC.DAYS = SMCSUWF.DAYS;
        SMCUUWF.RC.DEL_FLG = SMCSUWF.DEL_FLG;
        SMCUUWF.RC.TLT_FLG = SMCSUWF.TLT_FLG;
        SMCUUWF.RC.TR_CODE = SMCSUWF.TR_CODE;
        SMCUUWF.RC.UPT_DATE = SMCSUWF.UPT_DATE;
        SMCUUWF.RC.UPT_TLT = SMCSUWF.UPT_TLT;
        S000_CALL_SMZUUWF();
        if (pgmRtn) return;
    }
    public void B002_OUTPUT_FOR_SUB_TX() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SMCX01);
        IBS.init(SCCGWA, SCCFMT);
        SMCX01.CODE = SMCUUWF.RC.CODE;
        SCCFMT.FMTID = "SMX01";
        SCCFMT.DATA_PTR = SMCX01;
        SCCFMT.DATA_LEN = 5;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_SMZUUWF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SM-U-UPD-WF", SMCUUWF);
        if (SMCUUWF.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SMCUUWF.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], SMCSUWF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
