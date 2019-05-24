package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class BPZFFPDD {
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZFFPDD";
    String K_TBL_FADT = "BPTFADT";
    String K_TBL_FPDT = "BPTFPDT";
    String CPN_S_BPZFFPDT = "BP-F-S-FE-UNCHG-DTL";
    String WS_ERR_MSG = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    BPRFADT BPRFADT = new BPRFADT();
    BPRFPDT BPRFPDT = new BPRFPDT();
    BPCSFPDT BPCSFPDT = new BPCSFPDT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
