package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCMSG;

public class BPZRFPDT {
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZRFPDT";
    String K_TBL_FADT = "BPTFADT";
    String K_TBL_FPDT = "BPTFPDT";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRFADT BPRFADT = new BPRFADT();
    BPRFPDT BPRFPDT = new BPRFPDT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
