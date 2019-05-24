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

public class SMZSAWFW {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    SMZSAWFW_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new SMZSAWFW_WS_TEMP_VARIABLE();
    char WS_SEQ_FLG = ' ';
    SMCX02 SMCX02 = new SMCX02();
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SMCUAWFW SMCUAWFW = new SMCUAWFW();
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
    SMCSAWFW SMCSAWFW;
    public void MP(SCCGWA SCCGWA, SMCSAWFW SMCSAWFW) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.SMCSAWFW = SMCSAWFW;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SMZSAWFW return!");
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
        IBS.init(SCCGWA, SMCUAWFW);
        SMCUAWFW.RC.CODE = SMCSAWFW.CODE;
        SMCUAWFW.RC.Q_NAME = SMCSAWFW.Q_NAME;
        CEP.TRC(SCCGWA, SMCSAWFW.CODE);
        CEP.TRC(SCCGWA, SMCSAWFW.Q_NAME);
        CEP.TRC(SCCGWA, SMCSAWFW.Q_STS);
        CEP.TRC(SCCGWA, SMCSAWFW.REL_TYPE);
        CEP.TRC(SCCGWA, SMCSAWFW.Q_FMCODE);
        CEP.TRC(SCCGWA, SMCSAWFW.QCODE);
        CEP.TRC(SCCGWA, SMCSAWFW.OUT_FLG);
        CEP.TRC(SCCGWA, SMCSAWFW.UPD_FLG);
        CEP.TRC(SCCGWA, SMCSAWFW.STEP_NO);
        CEP.TRC(SCCGWA, SMCSAWFW.SUB_BR);
        CEP.TRC(SCCGWA, SMCSAWFW.APP_MMO);
        SMCUAWFW.RC.Q_STS = SMCSAWFW.Q_STS;
        SMCUAWFW.RC.REL_TYPE = SMCSAWFW.REL_TYPE;
        SMCUAWFW.RC.Q_FMCODE = SMCSAWFW.Q_FMCODE;
        SMCUAWFW.RC.QCODE = SMCSAWFW.QCODE;
        SMCUAWFW.RC.OUT_FLG = SMCSAWFW.OUT_FLG;
        SMCUAWFW.RC.UPD_FLG = SMCSAWFW.UPD_FLG;
        SMCUAWFW.RC.STEP_NO = SMCSAWFW.STEP_NO;
        SMCUAWFW.RC.SUB_BR = SMCSAWFW.SUB_BR;
        SMCUAWFW.RC.APP_MMO = SMCSAWFW.APP_MMO;
        for (WS_TEMP_VARIABLE.WS_POS = 1; WS_TEMP_VARIABLE.WS_POS <= 9; WS_TEMP_VARIABLE.WS_POS += 1) {
            SMCUAWFW.RC.DATA[WS_TEMP_VARIABLE.WS_POS-1].KEY_TYPE = SMCSAWFW.DATA[WS_TEMP_VARIABLE.WS_POS-1].KEY_TYPE;
            SMCUAWFW.RC.DATA[WS_TEMP_VARIABLE.WS_POS-1].TQ_FLG = SMCSAWFW.DATA[WS_TEMP_VARIABLE.WS_POS-1].TQ_FLG;
            SMCUAWFW.RC.DATA[WS_TEMP_VARIABLE.WS_POS-1].TQ_STS = SMCSAWFW.DATA[WS_TEMP_VARIABLE.WS_POS-1].TQ_STS;
            SMCUAWFW.RC.DATA[WS_TEMP_VARIABLE.WS_POS-1].CMP_NAME = SMCSAWFW.DATA[WS_TEMP_VARIABLE.WS_POS-1].CMP_NAME;
            SMCUAWFW.RC.DATA[WS_TEMP_VARIABLE.WS_POS-1].TRCODE = SMCSAWFW.DATA[WS_TEMP_VARIABLE.WS_POS-1].TRCODE;
            SMCUAWFW.RC.DATA[WS_TEMP_VARIABLE.WS_POS-1].MSG_FLG = SMCSAWFW.DATA[WS_TEMP_VARIABLE.WS_POS-1].MSG_FLG;
            SMCUAWFW.RC.DATA[WS_TEMP_VARIABLE.WS_POS-1].BK_FLG = SMCSAWFW.DATA[WS_TEMP_VARIABLE.WS_POS-1].BK_FLG;
        }
        S000_CALL_SMZUAWFW();
        if (pgmRtn) return;
    }
    public void B002_OUTPUT_FOR_SUB_TX() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SMCX02);
        IBS.init(SCCGWA, SCCFMT);
        SMCX02.CODE = SMCUAWFW.RC.CODE;
        SCCFMT.FMTID = "SMX01";
        SCCFMT.DATA_PTR = SMCX02;
        SCCFMT.DATA_LEN = 5;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_SMZUAWFW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SM-U-ADD-WFW", SMCUAWFW);
        if (SMCUAWFW.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SMCUAWFW.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], SMCSAWFW.RC);
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
