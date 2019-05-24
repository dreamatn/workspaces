package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSRPNR {
    boolean pgmRtn = false;
    char LNZSRPNR_FILLER1 = ' ';
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    LNRLOAN LNRLOAN = new LNRLOAN();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    LNCURPNR LNCURPNR = new LNCURPNR();
    SCCGWA SCCGWA;
    LNCURPNR LNCSRPNR;
    public void MP(SCCGWA SCCGWA, LNCURPNR LNCSRPNR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSRPNR = LNCSRPNR;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSRPNR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNCSRPNR.RC.RC_APP = "LN";
        LNCSRPNR.RC.RC_RTNCODE = 0;
        CEP.TRC(SCCGWA, LNCSRPNR.COMM_DATA.LN_AC);
        CEP.TRC(SCCGWA, LNCSRPNR.COMM_DATA.SUF_NO);
        CEP.TRC(SCCGWA, LNCSRPNR.COMM_DATA.TXN_DT);
        CEP.TRC(SCCGWA, LNCSRPNR.COMM_DATA.JRNNO);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B000_CHECK() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B200_DRAW_REV_PROCESS();
        if (pgmRtn) return;
        B300_DEBT_REV_PROCESS();
        if (pgmRtn) return;
        B400_FEE_REV_PROCESS();
        if (pgmRtn) return;
        B500_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B200_DRAW_REV_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCURPNR);
        LNCURPNR.COMM_DATA.ACM_EVENT = "RLN";
        LNCURPNR.COMM_DATA.TXN_DT = LNCSRPNR.COMM_DATA.TXN_DT;
        LNCURPNR.COMM_DATA.JRNNO = LNCSRPNR.COMM_DATA.JRNNO;
        LNCURPNR.COMM_DATA.LN_AC = LNCSRPNR.COMM_DATA.LN_AC;
        LNCURPNR.COMM_DATA.SUF_NO = LNCSRPNR.COMM_DATA.SUF_NO;
        S000_CALL_LNZURPNR();
        if (pgmRtn) return;
    }
    public void B300_DEBT_REV_PROCESS() throws IOException,SQLException,Exception {
    }
    public void B400_FEE_REV_PROCESS() throws IOException,SQLException,Exception {
    }
    public void B500_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
    }
    public void S000_CALL_LNZURPNR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-REV-REPAY-NOR", LNCURPNR);
        if (LNCURPNR.RC.RC_RTNCODE != 0) {
            LNCSRPNR.RC.RC_APP = LNCURPNR.RC.RC_APP;
            LNCSRPNR.RC.RC_RTNCODE = LNCURPNR.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCSRPNR.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCSRPNR=");
            CEP.TRC(SCCGWA, LNCSRPNR);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
