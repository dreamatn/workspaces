package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.CI.CICCUST;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class BPZFFEDT {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BP056";
    String K_TBL_FEHIS = "BPTFEHIS";
    short K_SUBR_ROW_CNT = 0;
    short K_MAX_COL_NO = 500;
    short K_SCR_ROW_NO = 24;
    short K_SCR_COL_CNT = 16;
    short K_MAX_BUTT_CNT = 9;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_ERR_INFO = " ";
    int WS_RCD_SEQ = 0;
    int WS_RD_NUM = 0;
    short WS_RECORD_NUM = 0;
    BPZFFEDT_WS_TS_BROWSE WS_TS_BROWSE = new BPZFFEDT_WS_TS_BROWSE();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCBINF SCCBINF = new SCCBINF();
    BPRFEHIS BPRFEHIS = new BPRFEHIS();
    BPCTFBAS BPCTFBAS = new BPCTFBAS();
    BPRFBAS BPRFBAS = new BPRFBAS();
    BPCOFEDT BPCOFEDT = new BPCOFEDT();
    BPCQFEDT BPCQFEDT = new BPCQFEDT();
    CICCUST CICCUST = new CICCUST();
