package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCMSG;

public class BPZRPLMT {
    boolean pgmRtn = false;
    int WS_REC_LEN = 0;
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPRPLMT BPRPLMT = new BPRPLMT();
    SCCMSG SCCMSG = new SCCMSG();
