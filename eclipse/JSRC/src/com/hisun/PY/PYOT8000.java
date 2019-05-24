package com.hisun.PY;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class PYOT8000 {
    boolean pgmRtn = false;
    String K_FMT_CD = "PYG09";
    int K_MTHS = 1;
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_ERR_MSG = " ";
    int WS_VAL_EXP_EDDT = 0;
    int WS_CLCT_CNT = 0;
    char WS_CLCT_FLG = ' ';
    PYOT8000_WS_OUTPUT_DATA WS_OUTPUT_DATA = new PYOT8000_WS_OUTPUT_DATA();
    PYCMSG_ERROR_MSG PYCMSG_ERROR_MSG = new PYCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    PYRCLCT PYRCLCT = new PYRCLCT();
    char WS_BEG_BUSI_TYP = ' ';
    char WS_END_BUSI_TYP = ' ';
    char WS_BEG_BUSI_STS = ' ';
    char WS_END_BUSI_STS = ' ';
    int WS_EXP_STDT = 0;
    int WS_EXP_EDDT = 0;
    int WS_ACT_STDT = 0;
    int WS_ACT_EDDT = 0;
    double WS_BEG_COL_AMT = 0;
    double WS_END_COL_AMT = 0;
    String WS_BEG_REC_ACNO = " ";
    String WS_END_REC_ACNO = " ";
    String WS_BEG_PAY_ACNO = " ";
    String WS_END_PAY_ACNO = " ";
    String WS_BEG_BILL_NO = " ";
    String WS_END_BILL_NO = " ";
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    PYCI8000 PYCI8000;
    PYB8000_AWA_8000 PYB8000_AWA_8000;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
