package com.hisun.SO;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.DB.*;
import com.hisun.AI.*;

import java.io.IOException;
import java.sql.SQLException;

public class SOZINPUT {
    String WS_MSGID = " ";
    SOCMSG SOCMSG = new SOCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    short WS_USR_BANK_NO = 0;
    String WS_USR_ID = " ";
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    AICSCPRD AICSCPRD;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        B00_CHECK_PROC();
        C00_MAIN_PROC();
        CEP.TRC(SCCGWA, "SOZINPUT return!");
        Z_RET();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        AICSCPRD = new AICSCPRD();
        IBS.init(SCCGWA, AICSCPRD);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, AICSCPRD);
    }
    public void B00_CHECK_PROC() throws IOException,SQLException,Exception {
    }
    public void C00_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-S-MAIN-CPRD", AICSCPRD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
