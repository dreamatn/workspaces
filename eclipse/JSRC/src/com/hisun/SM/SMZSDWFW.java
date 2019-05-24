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

public class SMZSDWFW {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    SMZSDWFW_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new SMZSDWFW_WS_TEMP_VARIABLE();
    char WS_SEQ_FLG = ' ';
    SMCX01 SMCX01 = new SMCX01();
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SMCUDWFW SMCUDWFW = new SMCUDWFW();
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
    SMCSDWFW SMCSDWFW;
    public void MP(SCCGWA SCCGWA, SMCSDWFW SMCSDWFW) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.SMCSDWFW = SMCSDWFW;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SMZSDWFW return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B001_DEL_RECORD();
        if (pgmRtn) return;
        B002_OUTPUT_FOR_SUB_TX();
        if (pgmRtn) return;
    }
    public void B001_DEL_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SMCUDWFW);
        SMCUDWFW.RC.CODE = SMCSDWFW.CODE;
        SMCUDWFW.RC.Q_STS = SMCSDWFW.Q_STS;
        S000_CALL_SMZUDWFW();
        if (pgmRtn) return;
    }
    public void B002_OUTPUT_FOR_SUB_TX() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SMCX01);
        IBS.init(SCCGWA, SCCFMT);
        SMCX01.CODE = SMCUDWFW.RC.CODE;
        SCCFMT.FMTID = "SMX01";
        SCCFMT.DATA_PTR = SMCX01;
        SCCFMT.DATA_LEN = 5;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_SMZUDWFW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SM-U-DEL-WFW", SMCUDWFW);
        if (SMCUDWFW.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SMCUDWFW.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], SMCSDWFW.RC);
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
