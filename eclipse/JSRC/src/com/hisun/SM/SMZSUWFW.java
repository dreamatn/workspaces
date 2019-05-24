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

public class SMZSUWFW {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    SMZSUWFW_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new SMZSUWFW_WS_TEMP_VARIABLE();
    char WS_SEQ_FLG = ' ';
    SMCX01 SMCX01 = new SMCX01();
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SMCUUWFW SMCUUWFW = new SMCUUWFW();
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
    SMCSUWFW SMCSUWFW;
    public void MP(SCCGWA SCCGWA, SMCSUWFW SMCSUWFW) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.SMCSUWFW = SMCSUWFW;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SMZSUWFW return!");
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
        IBS.init(SCCGWA, SMCUUWFW);
        SMCUUWFW.RC.CODE = SMCSUWFW.CODE;
        SMCUUWFW.RC.Q_NAME = SMCSUWFW.Q_NAME;
        SMCUUWFW.RC.Q_STS = SMCSUWFW.Q_STS;
        SMCUUWFW.RC.REL_TYPE = SMCSUWFW.REL_TYPE;
        SMCUUWFW.RC.Q_FMCODE = SMCSUWFW.Q_FMCODE;
        SMCUUWFW.RC.QCODE = SMCSUWFW.QCODE;
        SMCUUWFW.RC.OUT_FLG = SMCSUWFW.OUT_FLG;
        SMCUUWFW.RC.UPD_FLG = SMCSUWFW.UPD_FLG;
        SMCUUWFW.RC.STEP_NO = SMCSUWFW.STEP_NO;
        SMCUUWFW.RC.SUB_BR = SMCSUWFW.SUB_BR;
        SMCUUWFW.RC.APP_MMO = SMCSUWFW.APP_MMO;
        for (WS_TEMP_VARIABLE.WS_POS = 1; WS_TEMP_VARIABLE.WS_POS <= 9; WS_TEMP_VARIABLE.WS_POS += 1) {
            SMCUUWFW.RC.DATA[WS_TEMP_VARIABLE.WS_POS-1].KEY_TYPE = SMCSUWFW.DATA[WS_TEMP_VARIABLE.WS_POS-1].KEY_TYPE;
            SMCUUWFW.RC.DATA[WS_TEMP_VARIABLE.WS_POS-1].TQ_FLG = SMCSUWFW.DATA[WS_TEMP_VARIABLE.WS_POS-1].TQ_FLG;
            SMCUUWFW.RC.DATA[WS_TEMP_VARIABLE.WS_POS-1].TQ_STS = SMCSUWFW.DATA[WS_TEMP_VARIABLE.WS_POS-1].TQ_STS;
            SMCUUWFW.RC.DATA[WS_TEMP_VARIABLE.WS_POS-1].CMP_NAME = SMCSUWFW.DATA[WS_TEMP_VARIABLE.WS_POS-1].CMP_NAME;
            SMCUUWFW.RC.DATA[WS_TEMP_VARIABLE.WS_POS-1].TRCODE = SMCSUWFW.DATA[WS_TEMP_VARIABLE.WS_POS-1].TRCODE;
            SMCUUWFW.RC.DATA[WS_TEMP_VARIABLE.WS_POS-1].MSG_FLG = SMCSUWFW.DATA[WS_TEMP_VARIABLE.WS_POS-1].MSG_FLG;
            SMCUUWFW.RC.DATA[WS_TEMP_VARIABLE.WS_POS-1].BK_FLG = SMCSUWFW.DATA[WS_TEMP_VARIABLE.WS_POS-1].BK_FLG;
        }
        S000_CALL_SMZUUWFW();
        if (pgmRtn) return;
    }
    public void B002_OUTPUT_FOR_SUB_TX() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SMCX01);
        IBS.init(SCCGWA, SCCFMT);
        SMCX01.CODE = SMCUUWFW.RC.CODE;
        SCCFMT.FMTID = "SMX01";
        SCCFMT.DATA_PTR = SMCX01;
        SCCFMT.DATA_LEN = 5;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_SMZUUWFW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SM-U-UPD-WFW", SMCUUWFW);
        if (SMCUUWFW.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SMCUUWFW.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], SMCSUWFW.RC);
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
