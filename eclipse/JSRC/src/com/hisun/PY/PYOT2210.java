package com.hisun.PY;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPOEWA;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class PYOT2210 {
    boolean pgmRtn = false;
    String K_FMT_CD = "PYG08";
    String K_CNTR_TYPE = "COCA";
    String K_EVENT_CODE = "CR";
    String CPN_U_BPZPNHIS = "BP-REC-NHIS";
    String WS_ERR_MSG = " ";
    PYOT2210_WS_OUTPUT_DATA WS_OUTPUT_DATA = new PYOT2210_WS_OUTPUT_DATA();
    PYCMSG_ERROR_MSG PYCMSG_ERROR_MSG = new PYCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    PYCI2210 PYCI2210 = new PYCI2210();
    PYRCLCT PYRCLCT = new PYRCLCT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    PYB2210_AWA_2210 PYB2210_AWA_2210;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
