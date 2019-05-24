package com.hisun.SO;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.DB.*;

import java.io.IOException;
import java.sql.SQLException;

public class SOZQSLVL {
    int JIBS_tmp_int;
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
    SOCISLVL SOCISLVL;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        B00_MAIN_PROC();
        C00_OUTP_PROC();
        CEP.TRC(SCCGWA, "SOZQSLVL return!");
        Z_RET();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SOCISLVL = new SOCISLVL();
        IBS.init(SCCGWA, SOCISLVL);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, SOCISLVL);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SORSLVL);
        SORSLVL.KEY.BANK_NO = "" + SOCISLVL.KEY.BANK_NO;
        JIBS_tmp_int = SORSLVL.KEY.BANK_NO.length();
        for (int i=0;i<3-JIBS_tmp_int;i++) SORSLVL.KEY.BANK_NO = "0" + SORSLVL.KEY.BANK_NO;
        SORSLVL.KEY.ID = SOCISLVL.KEY.ID;
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
        SOCOP21.ACTION = SOCISLVL.ACTION;
        SOCOP21.KEY.BANK_NO = SORSLVL.KEY.BANK_NO;
        SOCOP21.KEY.ID = SORSLVL.KEY.ID;
        SOCOP21.SUP_ID = SORSLVL.SUP_ID;
        SOCOP21.CRE_USER = SORSLVL.CRE_USER;
        SOCOP21.TS = SORSLVL.TS;
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
