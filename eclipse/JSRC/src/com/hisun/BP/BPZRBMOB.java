package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCMSG;

public class BPZRBMOB {
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRBMOB";
    String K_TBL_BMOV = "BPTBMOV ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
