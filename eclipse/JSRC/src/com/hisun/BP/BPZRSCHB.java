package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCEXCP;

public class BPZRSCHB {
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRSCHB";
    String K_TBL_SCHS = "BPRSCHS";
    char WS_TBL_TXIF_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRSCHS BPRSCHS = new BPRSCHS();
