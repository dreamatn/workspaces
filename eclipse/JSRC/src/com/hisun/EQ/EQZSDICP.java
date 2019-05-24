package com.hisun.EQ;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.CI.CICCUST;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class EQZSDICP {
    boolean pgmRtn = false;
    int K_MAX_ROW = 18;
    int K_MAX_COL = 500;
    String K_OUTPUT_FMT_9 = "EQ205";
    String K_CMP_CALL_BPZPQPRD = "BP-P-QUERY-PRDT-INFO";
    String K_BSZ_BANKID = "01";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_DVC_FLG = ' ';
    char WS_DVCD_FLG = ' ';
    char WS_NORMAL_STS = 'N';
    EQCMSG_ERROR_MSG EQCMSG_ERROR_MSG = new EQCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    EQRDVC EQRDVC = new EQRDVC();
    EQRDVCD EQRDVCD = new EQRDVCD();
    CICCUST CICCUST = new CICCUST();
