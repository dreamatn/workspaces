package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCEXCP;

public class BPZRVHPB {
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRVHPB";
    String K_TBL_VHPB = "BPTVHPB ";
    int WS_VHPB_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
