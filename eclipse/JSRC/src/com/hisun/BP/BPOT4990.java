package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCMSG;

public class BPOT4990 {
    boolean pgmRtn = false;
    String CPN_TLR_MAINTAIN = "BP-S-TLR-MAINTAIN   ";
    String CPN_S_AMTL_MAINTAIN = "BP-S-AMTL-MAINTAIN  ";
    String K_OUTPUT_FMT = "BP556";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_I = 0;
    BPOT4990_WS_TLR_NO WS_TLR_NO = new BPOT4990_WS_TLR_NO();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRTLT BPRTLT = new BPRTLT();
    BPRTLT BPROTLT = new BPRTLT();
    BPCSTLRM BPCSTLRM = new BPCSTLRM();
    BPCOAMTL BPCOAMTL = new BPCOAMTL();
    BPCSAMTL BPCSAMTL = new BPCSAMTL();
    BPCPQORG BPCPQORG = new BPCPQORG();
