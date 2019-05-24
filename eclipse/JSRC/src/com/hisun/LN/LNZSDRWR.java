package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSDRWR {
    boolean pgmRtn = false;
    char LNZSDRWR_FILLER1 = ' ';
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    LNRLOAN LNRLOAN = new LNRLOAN();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    LNCUDRWR LNCUDRWR = new LNCUDRWR();
    SCCGWA SCCGWA;
    LNCSDRWR LNCSDRWR;
    public void MP(SCCGWA SCCGWA, LNCSDRWR LNCSDRWR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSDRWR = LNCSDRWR;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSDRWR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNCSDRWR.RC.RC_APP = "LN";
        LNCSDRWR.RC.RC_RTNCODE = 0;
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
        IBS.init(SCCGWA, LNCUDRWR);
        LNCUDRWR.COMM_DATA.TXN_DT = LNCSDRWR.COMM_DATA.TXN_DT;
        LNCUDRWR.COMM_DATA.JRNNO = LNCSDRWR.COMM_DATA.JRNNO;
        LNCUDRWR.COMM_DATA.LN_AC = LNCSDRWR.COMM_DATA.LN_AC;
        LNCUDRWR.COMM_DATA.SUF_NO = LNCSDRWR.COMM_DATA.SUF_NO;
        S000_CALL_LNZUDRWR();
        if (pgmRtn) return;
    }
    public void B300_DEBT_REV_PROCESS() throws IOException,SQLException,Exception {
    }
    public void B400_FEE_REV_PROCESS() throws IOException,SQLException,Exception {
    }
    public void B500_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
    }
    public void S000_CALL_LNZUDRWR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-REV-DRAW", LNCUDRWR);
        if (LNCUDRWR.RC.RC_RTNCODE != 0) {
            LNCSDRWR.RC.RC_APP = LNCUDRWR.RC.RC_APP;
            LNCSDRWR.RC.RC_RTNCODE = LNCUDRWR.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCSDRWR.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCSDRWR=");
            CEP.TRC(SCCGWA, LNCSDRWR);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
