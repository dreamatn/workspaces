package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCEXCP;

public class BPZRBUSB {
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRBUSB";
    String K_TBL_BUSE = "BPTBUSE ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
