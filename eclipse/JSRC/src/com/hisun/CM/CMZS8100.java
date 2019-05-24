package com.hisun.CM;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class CMZS8100 {
    boolean pgmRtn = false;
    short K_SUBR_ROW_CNT = 0;
    short K_SCR_ROW_NO = 30;
    short K_SCR_COL_CNT = 13;
    String WS_ERR_MSG = " ";
    String WS_CI_NM = " ";
    String WS_ID_TYP = " ";
    String WS_ID_NO = " ";
    String WS_TEL_NO = " ";
    short WS_I = 0;
    double WS_AC_BAL = 0;
    double WS_AVA_BAL = 0;
    long WS_AP_BATNO = 0;
    CMZS8100_WS_AP_DATE_TMP WS_AP_DATE_TMP = new CMZS8100_WS_AP_DATE_TMP();
    char WS_SCTBBTL_FLG = ' ';
    char WS_PROC1_FLG = ' ';
    char WS_PROC2_FLG = ' ';
    char WS_PROC3_FLG = ' ';
    char WS_STS_CHK_FLG = ' ';
    SCCMSG SCCMSG = new SCCMSG();
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCRBBTL SCRBBTL = new SCRBBTL();
    CMCF810 CMCF810 = new CMCF810();
