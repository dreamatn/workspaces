package com.hisun.SO;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCTLM_MSG;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCMSG;

public class SOZWJRN {
    boolean pgmRtn = false;
    short WK_REC_LEN = 0;
    int WK_RESP = 0;
    String WS_MSGID = " ";
    SOCMSG SOCMSG = new SOCMSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCTLM_MSG SCCCTLM_MSG = new SCCCTLM_MSG();
    SORJRN SORJRN = new SORJRN();
    SOCICWA SOCICWA = new SOCICWA();
    String WS_INP_DATA = " ";
