package com.hisun.SO;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class SOZTXNS {
    String JIBS_tmp_str[] = new String[10];
    SOZTXNS_WS_VARS WS_VARS = new SOZTXNS_WS_VARS();
    SCCMSG SCCMSG = new SCCMSG();
    BPCTCSW BPCTCSW = new BPCTCSW();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SOCCPSW SOCCPSW = new SOCCPSW();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGWA_WK_INPUT_AREA WK_INPUT_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "SOZTXNS return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_VARS);
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        WK_INPUT_AREA = new SCCGWA_WK_INPUT_AREA();
        IBS.init(SCCGWA, WK_INPUT_AREA);
        IBS.CPY2CLS(SCCGWA, SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, WK_INPUT_AREA);
        if (!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10301")) {
            IBS.init(SCCGWA, SOCCPSW);
            SOCCPSW.TL_ID = WK_INPUT_AREA.TELLER_NO;
            SOCCPSW.PSW = WK_INPUT_AREA.PASSWORD;
            SOZCPSW SOZCPSW = new SOZCPSW();
            SOZCPSW.MP(SCCGWA, SOCCPSW);
        }
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTCSW);
        if (WK_INPUT_AREA.IND == 'E') {
            BPCTCSW.FUNC = '0';
        } else {
            BPCTCSW.FUNC = '1';
        }
        BPCTCSW.TR_CODE = WK_INPUT_AREA.TX_CODE;
        BPZTCSW BPZTCSW = new BPZTCSW();
        BPZTCSW.MP(SCCGWA, BPCTCSW);
        if (BPCTCSW.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTCSW.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
