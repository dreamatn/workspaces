package com.hisun.SO;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPRTLT;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCENPSW;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class SOZAUSR {
    boolean pgmRtn = false;
    String WS_MSGID = " ";
    SOCMSG SOCMSG = new SOCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCENPSW SCCENPSW = new SCCENPSW();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SORUSR SORUSR = new SORUSR();
    short WS_USR_BANK_NO = 0;
    String WS_USR_ID = " ";
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    SOCAUSR SOCAUSR;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPRTLT SORUSER;
    SORSYS SORSYS;
    public void MP(SCCGWA SCCGWA, SOCAUSR SOCAUSR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.SOCAUSR = SOCAUSR;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_CHECK_PROC();
        if (pgmRtn) return;
        C00_MAIN_PROC();
        if (pgmRtn) return;
        D00_OUTP_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SOZAUSR return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SORSYS = (SORSYS) GWA_SC_AREA.OPER_PTR;
        SOCAUSR.RC.RC_CODE = 0;
        SORUSER = (BPRTLT) SOCAUSR.DATA_PTR;
