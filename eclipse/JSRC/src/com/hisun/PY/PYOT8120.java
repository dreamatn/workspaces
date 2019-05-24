package com.hisun.PY;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPQORG;
import com.hisun.DC.DCCPACTY;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class PYOT8120 {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "PYZ07";
    String CPN_U_PYZRFPCI = "PY-R-ADW-FPCI";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_FPCI_FLG = ' ';
    int WS_CNT = 0;
    short WS_LEN = 0;
    PYOT8120_WS_OUT_DATA WS_OUT_DATA = new PYOT8120_WS_OUT_DATA();
    PYCMSG_ERROR_MSG PYCMSG_ERROR_MSG = new PYCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    PYCRFPCI PYCRFPCI = new PYCRFPCI();
    PYRFPCI PYRFPCI = new PYRFPCI();
    BPCPQORG BPCPQORG = new BPCPQORG();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DDCUINQV DDCUINQV = new DDCUINQV();
    int WS_P_DT = 0;
    int WS_P_BR = 0;
    int WS_P_CNT_LOW = 0;
    int WS_P_CNT_TOP = 0;
    long WS_P_JRN_LOW = 0;
    long WS_P_JRN_TOP = 0;
    SCCGWA SCCGWA;
    PYCI8120 PYCI8120;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "PYOT8120 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        PYCI8120 = new PYCI8120();
        IBS.init(SCCGWA, PYCI8120);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, PYCI8120);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_INPUT_CHECK_PROC();
        if (pgmRtn) return;
        B200_READ_FPCI_PROC();
        if (pgmRtn) return;
    }
    public void B100_INPUT_CHECK_PROC() throws IOException,SQLException,Exception {
        if (!(PYCI8120.P_TYP == '1' 
            || PYCI8120.P_TYP == '2')) {
            WS_ERR_MSG = PYCMSG_ERROR_MSG.PY_P_TYP_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PYCI8120.TR_DT == 0) {
            WS_ERR_MSG = PYCMSG_ERROR_MSG.PY_TR_DT_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B200_READ_FPCI_PROC() throws IOException,SQLException,Exception {
        if (PYCI8120.P_TYP == '1') {
            WS_P_CNT_LOW = 0;
            WS_P_CNT_TOP = 0;
        }
        if (PYCI8120.P_TYP == '2') {
            WS_P_CNT_LOW = 1;
            WS_P_CNT_TOP = 999999999;
        }
        if (PYCI8120.TR_JRN != 0) {
            WS_P_JRN_LOW = PYCI8120.TR_JRN;
            WS_P_JRN_TOP = PYCI8120.TR_JRN;
        } else {
            WS_P_JRN_LOW = 0;
            WS_P_JRN_TOP = 999999999999;
        }
        WS_P_DT = PYCI8120.TR_DT;
        WS_P_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_INIT_MPAG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, PYCRFPCI);
        IBS.init(SCCGWA, PYRFPCI);
        T000_STARTBR_PYTFPCI();
        if (pgmRtn) return;
