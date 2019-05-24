package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCEXCP;

public class BPZRCPHM {
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRCPHM";
    String K_TBL_FARM = "BPTFCPH ";
    String WS_TEMP_RECORD = " ";
    int WS_FSCH_LEN = 0;
    int WS_FCPH_LEN = 0;
    char WS_TBL_FARM_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
