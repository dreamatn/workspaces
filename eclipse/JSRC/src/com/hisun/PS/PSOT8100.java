package com.hisun.PS;

import java.io.IOException;
import java.sql.SQLException;

public class PSOT8100 {
    boolean pgmRtn = false;
    String K_EXG_BK_NO = "001";
    String K_OUTPUT_FMT = "PS810";
    String CPN_U_PSZRQRYP = "PS-P-QRY-PROC";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_EXG_AREA_NO = " ";
    PSCMSG_ERROR_MSG PSCMSG_ERROR_MSG = new PSCMSG_ERROR_MSG();
