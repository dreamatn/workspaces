package com.hisun.DC;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSSHLD {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DC740";
    String PGM_TCOTLOGR = "TCOTLOGR";
    String WS_ERR_MSG = " ";
    char WS_TX_STS = ' ';
    short WS_REC_LEN1 = 0;
    short WS_REC_LEN2 = 0;
    short WS_REC_LEN3 = 0;
    short WS_REC_LEN4 = 0;
    DCZSSHLD_WS_LOGR_DATA WS_LOGR_DATA = new DCZSSHLD_WS_LOGR_DATA();
    int WK_POS = 0;
    int WK_POS1 = 0;
    int WK_POS2 = 0;
    char WS_GEN_NUM_FLG = ' ';
    short WS_PART = 0;
