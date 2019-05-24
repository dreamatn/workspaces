package com.hisun.SM;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCWOUT;
import com.hisun.DB.*;

import java.io.IOException;
import java.sql.SQLException;

public class SMZUDBSP {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    SMZUDBSP_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new SMZUDBSP_WS_TEMP_VARIABLE();
    char WS_WF_FLG = ' ';
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCRWSPC SCCRWSPC = new SCCRWSPC();
    SCRBSPC SCRBSPC = new SCRBSPC();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SMCUQBSP SMCUQBSP;
    public void MP(SCCGWA SCCGWA, SMCUQBSP SMCUQBSP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.SMCUQBSP = SMCUQBSP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SMZUDBSP return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, SCCRWSPC);
        SCCRWSPC.PTR = SCRBSPC;
        SCCRWSPC.LEN = 382;
        SMCUQBSP.RC.RC_MMO = "SM";
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B001_DEL_BSP_REC();
        if (pgmRtn) return;
    }
    public void B001_DEL_BSP_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCRBSPC);
        SCRBSPC.KEY.SERV_CODE = SMCUQBSP.SERV_CODE;
        SCCRWSPC.FUNC = 'R';
        S000_CALL_SCZRWSPC();
        if (pgmRtn) return;
        SCCRWSPC.FUNC = 'D';
        S000_CALL_SCZRWSPC();
        if (pgmRtn) return;
    }
    public void S000_CALL_SCZRWSPC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-R-UPD-BSP-CTL", SCCRWSPC);
        if (SCCRWSPC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCRWSPC.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], SMCUQBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
