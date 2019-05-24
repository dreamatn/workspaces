package com.hisun.SO;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class SOZQBATD {
    int JIBS_tmp_int;
    SOZQBATD_WS_OUTPUT_DATA WS_OUTPUT_DATA = new SOZQBATD_WS_OUTPUT_DATA();
    SOCMSG SOCMSG = new SOCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCSBATD BPCSBATD = new BPCSBATD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    BPRTLT BPRTLT;
    SOCIWAT SOCIWAT;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        B00_MAIN_PROC();
        C00_OUTP_PROC();
        CEP.TRC(SCCGWA, "SOZQBATD return!");
        Z_RET();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBATD);
        BPCSBATD.DATA.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSBATD.DATA.STATUS = 'N';
        BPCSBATD.FUNC = 'F';
        S000_CALL_BPZSBATD();
    }
    public void C00_OUTP_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT_DATA);
        IBS.init(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, "OUTPUT");
        WS_OUTPUT_DATA.O_AC_DATE = BPCSBATD.DATA.AC_DATE;
        WS_OUTPUT_DATA.O_REQ_TIME = BPCSBATD.DATA.REQ_TIME;
        WS_OUTPUT_DATA.O_BAT_TIME = BPCSBATD.DATA.BAT_TIME;
        if (BPCSBATD.DATA.USER_ID == null) BPCSBATD.DATA.USER_ID = "";
        JIBS_tmp_int = BPCSBATD.DATA.USER_ID.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) BPCSBATD.DATA.USER_ID += " ";
        WS_OUTPUT_DATA.O_USER_ID = BPCSBATD.DATA.USER_ID.substring(0, 7);
        WS_OUTPUT_DATA.O_USER_NAME = BPCSBATD.DATA.USER_CNM;
        WS_OUTPUT_DATA.O_TEL_NO = BPCSBATD.DATA.TEL_NO;
        WS_OUTPUT_DATA.O_REASON = BPCSBATD.DATA.REASON;
        SCCFMT.FMTID = "SOP22";
        SCCFMT.DATA_PTR = WS_OUTPUT_DATA;
        SCCFMT.DATA_LEN = 537;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZSBATD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-BATD-PROC", BPCSBATD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
