package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCEXCP;

public class BPZTCHIB {
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZTCHIB";
    String K_TBL_CHIS = "BPTCHIS ";
    char WS_TBL_CHIS_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRCHIS BPRCHIS = new BPRCHIS();
