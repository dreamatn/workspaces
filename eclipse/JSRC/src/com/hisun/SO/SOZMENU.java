package com.hisun.SO;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCTLM_MSG;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class SOZMENU {
    boolean pgmRtn = false;
    String PGM_SOZASRV = "SOZASRV";
    short WK_REC_LEN = 0;
    int WK_RESP = 0;
    String WK_SUP_ID = " ";
    short WK_TR_BANK = 0;
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCTLM_MSG SCCCTLM_MSG = new SCCCTLM_MSG();
    SOCD01_SOD01 SOCD01_SOD01 = new SOCD01_SOD01();
    SOCASRV SOCASRV = new SOCASRV();
    SORSLVL SORSLVL = new SORSLVL();
    SORSERV SORSERV = new SORSERV();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_CHK_PROC();
        if (pgmRtn) return;
        C00_MAIN_PROC();
        if (pgmRtn) return;
        D00_OUTP_PROC();
        if (pgmRtn) return;
        E00_GO_BACK();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SOCD01_SOD01.CNT = 0;
