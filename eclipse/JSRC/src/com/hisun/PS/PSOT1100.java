package com.hisun.PS;

import java.io.IOException;
import java.sql.SQLException;

public class PSOT1100 {
    boolean pgmRtn = false;
    String K_EXG_BK_NO = "001";
    String K_OUTPUT_FMT = "PS110";
    String CPN_U_PSZOTCRP = "PS-P-OTCR-PROC";
    char K_ERROR = 'E';
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    PSOT1100_WS_FMT WS_FMT = new PSOT1100_WS_FMT();
    PSCMSG_ERROR_MSG PSCMSG_ERROR_MSG = new PSCMSG_ERROR_MSG();
