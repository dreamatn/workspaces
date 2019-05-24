package com.hisun.PY;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCAOTH;
import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPOEWA;
import com.hisun.BP.BPCQCNGL;
import com.hisun.BP.BPCSGSEQ;
import com.hisun.BP.BPCUCNGM;
import com.hisun.CI.CICACCU;
import com.hisun.CI.CICQACRI;
import com.hisun.DC.DCCPACTY;
import com.hisun.DD.DDCIMMST;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class PYOT2200 {
    boolean pgmRtn = false;
    String K_FMT_CD = "PYG06";
    String K_SEQ_TYPE = "SEQ";
    String K_SEQ_CODE = "PYNO";
    String K_CNTR_TYPE = "COCA";
    String K_EVENT_CODE = "DR";
    String CPN_U_BPZPNHIS = "BP-REC-NHIS";
    String WS_ERR_MSG = " ";
    PYOT2200_WS_OUTPUT_DATA WS_OUTPUT_DATA = new PYOT2200_WS_OUTPUT_DATA();
    PYCMSG_ERROR_MSG PYCMSG_ERROR_MSG = new PYCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DDCIMMST DDCIMMST = new DDCIMMST();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    BPCQCNGL BPCQCNGL = new BPCQCNGL();
    BPCAOTH BPCAOTH = new BPCAOTH();
    BPCUCNGM BPCUCNGM = new BPCUCNGM();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICQACRI CICQACRI = new CICQACRI();
    CICACCU CICACCU = new CICACCU();
    PYCPCFTA PYCPCFTA = new PYCPCFTA();
    PYRCLCT PYRCLCT = new PYRCLCT();
    PYCI2200 PYCI2200 = new PYCI2200();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    PYB2200_AWA_2200 PYB2200_AWA_2200;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
