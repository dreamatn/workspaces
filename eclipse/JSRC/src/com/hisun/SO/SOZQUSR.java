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

public class SOZQUSR {
    DBParm SOTUSR_RD;
    String WS_MSGID = " ";
    SOCMSG SOCMSG = new SOCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SORUSR SORUSR = new SORUSR();
    SCCFMT SCCFMT = new SCCFMT();
    SOCOP15 SOCOP15 = new SOCOP15();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPRTLT SORTLT;
    SOCIUSR SOCIUSR;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        B00_MAIN_PROC();
        C00_OUTP_PROC();
        CEP.TRC(SCCGWA, "SOZQUSR return!");
        Z_RET();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SOCIUSR = new SOCIUSR();
        IBS.init(SCCGWA, SOCIUSR);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, SOCIUSR);
        SORTLT = (BPRTLT) SCCGWA.COMM_AREA.TLT_AREA_PTR;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SORUSR);
        SORUSR.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
        SORUSR.KEY.ID = SOCIUSR.ID;
        SOTUSR_RD = new DBParm();
        SOTUSR_RD.TableName = "SOTUSR";
        SOTUSR_RD.errhdl = true;
        IBS.READ(SCCGWA, SORUSR, SOTUSR_RD);
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
        IBS.init(SCCGWA, SOCOP15);
        IBS.init(SCCGWA, SCCFMT);
        SOCOP15.ACTION = SOCIUSR.ACTION;
        SOCOP15.ID = SORUSR.KEY.ID;
        SOCOP15.NAME = SORUSR.NAME;
        SOCOP15.TYPE = SORUSR.TYPE;
        SOCOP15.AUTH_LVL = SORUSR.AUTH_LVL;
        SOCOP15.SNON_STUS = SORUSR.SNON_STUS;
        SOCOP15.SNON_DATE = SORUSR.SNON_DATE;
        SOCOP15.SNON_TIME = SORUSR.SNON_TIME;
        SOCOP15.TERM_ID = SORUSR.TERM_ID;
        SOCOP15.TERM_TYPE = SORUSR.TERM_TYPE;
        SCCFMT.FMTID = "SOP15";
        SCCFMT.DATA_PTR = SOCOP15;
        SCCFMT.DATA_LEN = 69;
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
