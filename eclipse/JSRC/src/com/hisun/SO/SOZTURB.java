package com.hisun.SO;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCRCWAT;
import com.hisun.TC.*;

import java.io.IOException;
import java.sql.SQLException;

public class SOZTURB {
    boolean pgmRtn = false;
    int RESP_CODE = 0;
    String WS_MSGID = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SOCMSG SOCMSG = new SOCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    TCCASMSG TCCASMSG = new TCCASMSG();
    SCCGWA SCCGWA;
    SCRCWAT SCRCWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        IBS.init(SCCGWA, TCCASMSG);
        TCCASMSG.REQ_APP = "INTGRT";
        TCCASMSG.MSG_CODE = "TCSY002";
        TCCASMSG.ENA_MODE = '1';
        TCCASMSG.LEN = 1;
        S000_CALL_TCZSYMSG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SOZTURB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void S000_CALL_TCZSYMSG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TC-SYNC-MSG", TCCASMSG);
        if (TCCASMSG.RTNCODE != 0) {
            CEP.TRC(SCCGWA, TCCASMSG.RTNCODE);
            if (TCCASMSG.RTNCODE == 8510) {
                WS_MSGID = SOCMSG.BAL_NOT;
                S000_ERROR_PROCESS();
                if (pgmRtn) return;
            } else {
                WS_MSGID = SOCMSG.SO_ERR_CALL_TC;
                S000_ERROR_PROCESS();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_ERROR_PROCESS() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID);
        Z_RET();
        if (pgmRtn) return;
    }
    public void S000_SEND_MSG_TO_OP() throws IOException,SQLException,Exception {
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
