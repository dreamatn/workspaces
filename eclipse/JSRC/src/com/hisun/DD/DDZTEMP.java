package com.hisun.DD;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPRMR;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCTLM_MSG;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCRIPFG;
import com.hisun.SO.SORSYS;
import com.hisun.TC.TCCTPCM;

public class DDZTEMP {
    boolean pgmRtn = false;
    String K_SYS_ERR = "SC6001";
    String K_HOST_SYS_ID = "IBS";
    String PGM_TCOTLOGR = "TCOTLOGR";
    String K_ESB_ID = "ESBP";
    int WK_LEN = 0;
    int WK_RESP = 0;
    char WK_STS = ' ';
    char WK_END_FLG = ' ';
    String WS_XML_FLD = " ";
    String WS_TAG_NM = " ";
    String WS_TAG_CPNM = " ";
    short WS_TAG_OCC = 0;
    short WS_TAG_SEQ = 0;
    String WS_TAG_VAL = " ";
    String WS_TAG_TYPE = " ";
    short WS_FLOAT_CNT = 0;
    SCCCALL SCCCALL = new SCCCALL();
    SCCCTLM_MSG SCCCTLM_MSG = new SCCCTLM_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SORSYS SORSYS = new SORSYS();
    SCRIPFG SCRIPFG = new SCRIPFG();
    TCRLOGR TCRLOGR = new TCRLOGR();
    TCCLOGR TCCLOGR = new TCCLOGR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    TCCTPCM TCCTPCM;
    public void MP(SCCGWA SCCGWA, TCCTPCM TCCTPCM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TCCTPCM = TCCTPCM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_XMLC_PROC();
        if (pgmRtn) return;
        C000_SEND_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZTEMP return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        WK_STS = ' ';
    }
    public void B000_XMLC_PROC() throws IOException,SQLException,Exception {
