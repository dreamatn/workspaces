package com.hisun.FX;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCMSG;

public class FXOT3002 {
    boolean pgmRtn = false;
    int WK_MAX_ROW = 5;
    String WK_PROD_CD = "1303020403";
    int WS_CNT = 0;
    int WS_I = 0;
    char WS_END_FLG = ' ';
    FXCMSG_ERROR_MSG FXCMSG_ERROR_MSG = new FXCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    FXRDIRFX FXRDIRFX = new FXRDIRFX();
    FXCF002 FXCF002 = new FXCF002();
