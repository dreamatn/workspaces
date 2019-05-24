package com.hisun.PY;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class PYOT8110 {
    boolean pgmRtn = false;
    String CPN_U_PYZRFPCI = "PY-R-ADW-FPCI";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    PYOT8110_WS_OUT_DATA WS_OUT_DATA = new PYOT8110_WS_OUT_DATA();
    char WS_FPCI_FLG = ' ';
    int WS_CNT = 0;
    short WS_LEN = 0;
    PYCMSG_ERROR_MSG PYCMSG_ERROR_MSG = new PYCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    PYCRFPCI PYCRFPCI = new PYCRFPCI();
    PYRFPCI PYRFPCI = new PYRFPCI();
    SCCGWA SCCGWA;
    PYCI8110 PYCI8110;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "PYOT8110 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        PYCI8110 = new PYCI8110();
        IBS.init(SCCGWA, PYCI8110);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, PYCI8110);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_READ_FPCI_PROC();
        if (pgmRtn) return;
    }
    public void B100_READ_FPCI_PROC() throws IOException,SQLException,Exception {
        S000_INIT_MPAG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, PYCRFPCI);
        IBS.init(SCCGWA, PYRFPCI);
        PYRFPCI.CCY = PYCI8110.CCY;
        PYRFPCI.KEY.TX_DT = PYCI8110.TR_DT;
        if (PYCI8110.P_TYP == '1') {
            PYRFPCI.PAY_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        }
        if (PYCI8110.P_TYP == '2') {
            PYRFPCI.CLT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        }
        T000_STARTBR_PYTFPCI();
        if (pgmRtn) return;
