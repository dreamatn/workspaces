package com.hisun.CM;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPQPRD;
import com.hisun.BP.BPCPRMR;
import com.hisun.DC.DCRCDORD;
import com.hisun.DC.DCRPRDPR;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class CMZSBSPA {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "CM027";
    String WS_ERR_MSG = " ";
    String WS_CARD_NO_I = " ";
    String WS_BV_CODE_I = " ";
    String WS_BV_NO_I = " ";
    int WS_I = 0;
    CMZSBSPA_WS_BATH_PARM WS_BATH_PARM = new CMZSBSPA_WS_BATH_PARM();
    char WS_END_FLG = ' ';
    CMZSBSPA_WS_OUTPUT WS_OUTPUT = new CMZSBSPA_WS_OUTPUT();
    String WS_TS = " ";
    int WS_CNT_ORD = 0;
    int WS_CNT_BIN = 0;
    int WS_CNT_BIN2 = 0;
    long WS_AP_SEQ = 0;
    int WS_CNT_T = 0;
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRCDORD DCRCDORD = new DCRCDORD();
    CMRBSPM CMRBSPM = new CMRBSPM();
    CMRBSP15 CMRBSP15 = new CMRBSP15();
    CICOPDCP CICOPDCP = new CICOPDCP();
    DCCSSPEC DCCSSPEC = new DCCSSPEC();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCPRMR BPCPRMR = new BPCPRMR();
    DCRPRDPR DCRPRDPR = new DCRPRDPR();
    DCCFCDGG DCCFCDGG = new DCCFCDGG();
    DDCSTRAC DDCSTRAC = new DDCSTRAC();
    CMCBSPIN CMCBSPIN = new CMCBSPIN();
