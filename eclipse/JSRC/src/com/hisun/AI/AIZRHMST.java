package com.hisun.AI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGWA;

public class AIZRHMST {
    boolean pgmRtn = false;
    String TBL_NAME = "AIRHMST";
    int WS_BR = 0;
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    AIRHMST AIRHMST = new AIRHMST();
    SCCGWA SCCGWA;
    AICRHMST AICRRMST;
    String LK_REC = " ";
