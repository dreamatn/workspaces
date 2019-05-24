package com.hisun.FS;

import java.io.IOException;
import java.sql.SQLException;

public class FSOENTR {
    boolean pgmRtn = false;
    String PGM_SCZIGWA = "SCZIGWA";
    String PGM_SCZITRC = "SCZITRC";
    int WK_TIA_LEN = 0;
    int WK_TOA_LEN = 0;
    short WK_HEAD_LEN = 0;
    short WK_MAX_LEN = 0;
    short WK_RC = 0;
    short WK_DATA_LEN = 0;
    short WK_MSG_SYS_ERR = 6001;
    String WK_ABCD = " ";
    String WK_AB_PGM = " ";
    String WK_AB_PSW = " ";
    String FSOENTR_FILLER1 = "TRC";
    String WK_TRC_TL_ID = " ";
    String FSOENTR_FILLER3 = "      ";
    long WK_DATE_TIME = 0;
    int WK_DATE = 0;
    int WK_TIME = 0;
    short WS_RESP = 0;
    String WK_AUTH_MSG = " ";
    short WK_I = 0;
    short WK_TMP_LEN = 0;
