package com.hisun.DC;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPNHIS;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class DCZUSSTS {
    boolean pgmRtn = false;
    char K_REQ_STS_NORMAL = 'N';
    char K_REQ_STS_DELETE = 'D';
    String WS_ERR_MSG = " ";
    char WS_REC_CHG_FLG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCRSSTS DCRSSTS = new DCRSSTS();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
