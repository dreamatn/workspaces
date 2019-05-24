package com.hisun.SM;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCXP61;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCPRMM;
import com.hisun.SC.SCCWOUT;
import com.hisun.SC.SCRPRMT;

public class SMOT2100 {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BPX02";
    SMOT2100_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new SMOT2100_WS_TEMP_VARIABLE();
    short WS_FLD_NO = 0;
    String WS_INFO = " ";
    short WS_I = 0;
    short WS_J = 0;
    String WS_TSQ_NM = " ";
    String WS_TSQ_REC = " ";
    BPCXP61 BPCXP61 = new BPCXP61();
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCPRMM SCCPRMM = new SCCPRMM();
    SCRPRMT SCRPRMT = new SCRPRMT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
