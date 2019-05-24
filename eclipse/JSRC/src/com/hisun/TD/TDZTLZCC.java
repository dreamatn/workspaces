package com.hisun.TD;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.AI.AICUUPIA;
import com.hisun.BP.BPCUABOX;
import com.hisun.DD.DDCUDRAC;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCMSG;

public class TDZTLZCC {
    boolean pgmRtn = false;
    String K_AP_MMO = "TD";
    String K_OUTPUT_FMT = "TD129";
    String WS_MSGID = " ";
    String WS_CI_NO = " ";
    String WS_OPP_CI_NO = " ";
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCUABOX BPCUABOX = new BPCUABOX();
    TDCACCRU TDCACCRU = new TDCACCRU();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    AICUUPIA AICUUPIA = new AICUUPIA();
    SCCFMT SCCFMT = new SCCFMT();
