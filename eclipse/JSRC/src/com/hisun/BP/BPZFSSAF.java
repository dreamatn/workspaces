package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class BPZFSSAF {
    boolean pgmRtn = false;
    int K_MAX_COL = 99;
    int K_MAX_ROW = 50;
    int K_COL_CNT = 20;
    BPZFSSAF_WS_LINE WS_LINE = new BPZFSSAF_WS_LINE();
    String WS_ERR_MSG = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPRFSAF BPRFSAF = new BPRFSAF();
    BPRFAGOR BPRFAGOR = new BPRFAGOR();
    BPCRFSAF BPCRFSAF = new BPCRFSAF();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
