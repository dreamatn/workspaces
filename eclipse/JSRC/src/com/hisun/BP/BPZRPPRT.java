package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCEXCP;

public class BPZRPPRT {
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRPPRT";
    int WS_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRPPRT BPRPPRT = new BPRPPRT();
