package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class BPZSMLCJ {
    boolean pgmRtn = false;
    String K_TBL_FARM = "BPTMLCJN";
    String WS_ERR_MSG = " ";
    int WS_MLCJN_LEN = 0;
    int WS_CNT = 0;
    BPZSMLCJ_WS_LINE_VAR WS_LINE_VAR = new BPZSMLCJ_WS_LINE_VAR();
    BPZSMLCJ_WS_LINE_FMT_VAR WS_LINE_FMT_VAR = new BPZSMLCJ_WS_LINE_FMT_VAR();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPRMLCJN BPRMLCJN = new BPRMLCJN();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
