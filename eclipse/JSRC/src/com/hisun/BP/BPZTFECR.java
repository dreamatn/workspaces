package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;

public class BPZTFECR {
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZTFECR";
    String K_TBL_FARM = "BPTFECRE";
    String WS_TEMP_RECORD = " ";
    char WS_TBL_FARM_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRFECRE BPRFECRE = new BPRFECRE();
    SCCGWA SCCGWA;
    BPCTFECR BPCTFER;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    String LK_REC = " ";
