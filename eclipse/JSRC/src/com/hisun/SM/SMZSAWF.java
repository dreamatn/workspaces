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

public class SMZSAWF {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    SMZSAWF_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new SMZSAWF_WS_TEMP_VARIABLE();
    char WS_SEQ_FLG = ' ';
    SMCX01 SMCX01 = new SMCX01();
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SMCUAWF SMCUAWF = new SMCUAWF();
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
    SMCSAWF SMCSAWF;
    public void MP(SCCGWA SCCGWA, SMCSAWF SMCSAWF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.SMCSAWF = SMCSAWF;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SMZSAWF return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B001_ADD_RECORD();
        if (pgmRtn) return;
        B002_OUTPUT_FOR_SUB_TX();
        if (pgmRtn) return;
    }
    public void B001_ADD_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SMCUAWF);
        SMCUAWF.RC.CODE = SMCSAWF.CODE;
        SMCUAWF.RC.NAME = SMCSAWF.NAME;
        SMCUAWF.RC.TYPE = SMCSAWF.TYPE;
        SMCUAWF.RC.FIRST_Q = SMCSAWF.FIRST_Q;
        SMCUAWF.RC.CMP_NAME = SMCSAWF.CMP_NAME;
        SMCUAWF.RC.DAYS = SMCSAWF.DAYS;
        SMCUAWF.RC.DL_FLG = SMCSAWF.DL_FLG;
        SMCUAWF.RC.TLT_FLG = SMCSAWF.TLT_FLG;
        SMCUAWF.RC.TR_CODE = SMCSAWF.TR_CODE;
        SMCUAWF.RC.Q_NO = SMCSAWF.Q_NO;
        SMCUAWF.RC.UPT_DATE = SMCSAWF.UPT_DATE;
        SMCUAWF.RC.UPT_TLT = SMCSAWF.UPT_TLT;
        S000_CALL_SMZUAWF();
        if (pgmRtn) return;
    }
    public void B002_OUTPUT_FOR_SUB_TX() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SMCX01);
        IBS.init(SCCGWA, SCCFMT);
        SMCX01.CODE = SMCUAWF.RC.CODE;
        SCCFMT.FMTID = "SMX01";
        SCCFMT.DATA_PTR = SMCX01;
        SCCFMT.DATA_LEN = 5;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_SMZUAWF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SM-U-ADD-WF", SMCUAWF);
        if (SMCUAWF.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SMCUAWF.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], SMCSAWF.RC);
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
