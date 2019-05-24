package com.hisun.CM;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.CI.CICQACRI;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class CMZS8110 {
    boolean pgmRtn = false;
    short K_SUBR_ROW_CNT = 0;
    short K_SCR_ROW_NO = 30;
    short K_SCR_COL_CNT = 13;
    int WK_WARNING_CNT = 9999;
    String WS_ERR_MSG = " ";
    String WS_CI_NM = " ";
    String WS_ID_TYP = " ";
    String WS_ID_NO = " ";
    String WS_TEL_NO = " ";
    short WS_I = 0;
    short WS_J = 0;
    double WS_AC_BAL = 0;
    double WS_AVA_BAL = 0;
    int WS_TOT_NUM = 0;
    int WS_TOT_PAGE = 0;
    int WS_REMAIN_CNT = 0;
    int WS_INQ_PAGE_NUM = 0;
    int WS_INQ_PAGE_ROW = 0;
    int WS_INQ_PARG_START_ROW = 0;
    int WS_INQ_PARG_END_ROW = 0;
    int WS_INQ_CURR_PAGE_ROW = 0;
    char WS_INQ_LAST_PAGE_FLG = ' ';
    char WS_CMTDELAY_FLG = ' ';
    String WS_PAY_NM = " ";
    String WS_GET_NM = " ";
    SCCMSG SCCMSG = new SCCMSG();
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CMRDELAY CMRDELAY = new CMRDELAY();
    CMCF811 CMCF811 = new CMCF811();
    CICQACRI CICQACRI = new CICQACRI();
    CMCS8840 CMCS8840 = new CMCS8840();
