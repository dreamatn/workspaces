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

public class SMZSQWF {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    SMZSQWF_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new SMZSQWF_WS_TEMP_VARIABLE();
    char WS_SEQ_FLG = ' ';
    SMCX02 SMCX02 = new SMCX02();
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SMCUQWF SMCUQWF = new SMCUQWF();
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
    SMCSQWF SMCSQWF;
    public void MP(SCCGWA SCCGWA, SMCSQWF SMCSQWF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.SMCSQWF = SMCSQWF;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SMZSQWF return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B001_INQ_RECORD();
        if (pgmRtn) return;
    }
    public void B001_INQ_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SMCUQWF);
        SMCUQWF.RC.CODE = SMCSQWF.CODE;
        S000_CALL_SMZUQWF();
        if (pgmRtn) return;
    }
    public void S000_CALL_SMZUQWF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SM-U-INQ-WF", SMCUQWF);
        if (SMCUQWF.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SMCUQWF.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], SMCSQWF.RC);
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