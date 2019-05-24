package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class BPZSARAP {
    boolean pgmRtn = false;
    String CPN_S_GET_SEQ = "BP-S-GET-SEQ";
    char K_RUN_MODE = 'O';
    short K_NUM = 1;
    String K_BPFBAS_SEQ = "CASHMOVE";
    String K_SEQ_TYPE = "SEQ";
    String K_OUTPUT_FMT = "BP225";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    BPZSARAP_WS_OUTPUT_DATA WS_OUTPUT_DATA = new BPZSARAP_WS_OUTPUT_DATA();
    char WS_TBL_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPRCSAP BPRCSAP = new BPRCSAP();
    BPRCSAR BPRCSAR = new BPRCSAR();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    BPCO225 BPCO225 = new BPCO225();
    BPCFTLCM BPCFTLCM = new BPCFTLCM();
