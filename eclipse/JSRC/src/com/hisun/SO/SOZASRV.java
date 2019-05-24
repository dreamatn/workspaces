package com.hisun.SO;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPRTRT;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCENPSW;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class SOZASRV {
    boolean pgmRtn = false;
    String WS_MSGID = " ";
    SOCMSG SOCMSG = new SOCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCENPSW SCCENPSW = new SCCENPSW();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SORSERV SORSERV = new SORSERV();
    short WS_SRV_BANK_NO = 0;
    String WS_SRV_ID = " ";
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    SOCASRV SOCASRV;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPRTRT SORSRV;
    SORSYS SORSYS;
    public void MP(SCCGWA SCCGWA, SOCASRV SOCASRV) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.SOCASRV = SOCASRV;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_CHECK_PROC();
        if (pgmRtn) return;
        C00_MAIN_PROC();
        if (pgmRtn) return;
        D00_OUTP_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SOZASRV return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SORSYS = (SORSYS) GWA_SC_AREA.OPER_PTR;
        SOCASRV.RC.RC_CODE = 0;
        SORSRV = (BPRTRT) SOCASRV.DATA_PTR;
        CEP.TRC(SCCGWA, SORSRV);
