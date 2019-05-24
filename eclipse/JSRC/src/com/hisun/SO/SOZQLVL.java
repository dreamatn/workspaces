package com.hisun.SO;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.BP.BPRTLT;
import com.hisun.DB.*;

import java.io.IOException;
import java.sql.SQLException;

public class SOZQLVL {
    DBParm SOTSLVL_RD;
    String WS_MSGID = " ";
    short WS_I = 0;
    SOCMSG SOCMSG = new SOCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SOCOP21 SOCOP21 = new SOCOP21();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SORSLVL SORSLVL = new SORSLVL();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPRTLT SORTLT;
    SORILVL SORILVL;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        B00_MAIN_PROC();
        C00_OUTP_PROC();
        CEP.TRC(SCCGWA, "SOZQLVL return!");
        Z_RET();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SORILVL = new SORILVL();
        IBS.init(SCCGWA, SORILVL);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, SORILVL);
        SORTLT = (BPRTLT) SCCGWA.COMM_AREA.TLT_AREA_PTR;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SORSLVL);
        SORSLVL.KEY.BANK_NO = SORILVL.KEY.BANK_NO;
        SORSLVL.KEY.ID = SORILVL.KEY.ID;
        SOTSLVL_RD = new DBParm();
        SOTSLVL_RD.TableName = "SOTSLVL";
        SOTSLVL_RD.errhdl = true;
        IBS.READ(SCCGWA, SORSLVL, SOTSLVL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = SOCMSG.SO_RECORD_NOTFND;
            S000_ERR_MSG_PROC();
        } else {
            WS_MSGID = SOCMSG.SO_ACCESS_TAB_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void C00_OUTP_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SOCOP21);
        IBS.init(SCCGWA, SCCFMT);
        SOCOP21.ACTION = SORILVL.ACTION;
        SOCOP21.KEY.BANK_NO = SORILVL.KEY.BANK_NO;
        SOCOP21.KEY.ID = SORILVL.KEY.ID;
        SOCOP21.SUP_ID = SORILVL.SUP_ID;
        SOCOP21.CRE_USER = SORILVL.CRE_USER;
        SOCOP21.TS = SORILVL.TS;
        SCCFMT.FMTID = "SOP21";
        SCCFMT.DATA_PTR = SOCOP21;
        SCCFMT.DATA_LEN = 51;
        IBS.FMT(SCCGWA, SCCFMT);
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
