package com.hisun.SO;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCRCWAT;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class SOZCLBR {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WK_TEMP_CODE = " ";
    short WK_CNT = 0;
    int RESP_CODE = 0;
    SOZCLBR_WS_MSGID WS_MSGID = new SOZCLBR_WS_MSGID();
    SCCMSG SCCMSG = new SCCMSG();
    SOCMSG SOCMSG = new SOCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPCPOCBR BPCPOCBR = new BPCPOCBR();
    SCCGWA SCCGWA;
    SCRCWAT SCRCWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    int WK_BRANCH = 0;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SOZCLBR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        WK_BRANCH = SCCGSCA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOCBR);
        BPCPOCBR.INPUT_DATA.FUCN = 'F';
        BPCPOCBR.INPUT_DATA.FLAG = 'S';
        BPCPOCBR.INPUT_DATA.BR = WK_BRANCH;
        CEP.TRC(SCCGWA, BPCPOCBR);
        S000_CALL_BPZPOCBR();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPOCBR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-OPEN-CLOSE-BR", BPCPOCBR);
        if (BPCPOCBR.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPOCBR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_MSGID);
            S000_ERROR_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void S000_ERROR_PROCESS() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        Z_RET();
        if (pgmRtn) return;
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
