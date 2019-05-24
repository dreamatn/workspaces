package com.hisun.DC;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCEXCP;

public class DCZRBSP3 {
    boolean pgmRtn = false;
    String WS_BSPNAME = " ";
    char WS_TBL_FARM_FLAG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
