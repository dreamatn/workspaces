package com.hisun.LN;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZPCFTA {
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    LNZPCFTA_WS_MSGID WS_MSGID = new LNZPCFTA_WS_MSGID();
    short WS_IDX = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRPORGM BPRPORGM = new BPRPORGM();
    BPCRMOGS BPCRMOGS = new BPCRMOGS();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPRORGS BPRORGS = new BPRORGS();
    SCCGWA SCCGWA;
    LNCPCFTA LNCPCFTA;
    public void MP(SCCGWA SCCGWA, LNCPCFTA LNCPCFTA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCPCFTA = LNCPCFTA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZPCFTA return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCPCFTA.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B200_GET_ORG_INFO();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (LNCPCFTA.BR_GP[1-1].BR == 0 
            || LNCPCFTA.BR_GP[2-1].BR == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.PC_FTA_NULL, LNCPCFTA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B200_GET_ORG_INFO() throws IOException,SQLException,Exception {
        for (WS_IDX = 1; LNCPCFTA.BR_GP[WS_IDX-1].BR != 0 
            && WS_IDX <= 5; WS_IDX += 1) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = LNCPCFTA.BR_GP[WS_IDX-1].BR;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            LNCPCFTA.FLG_DATA[WS_IDX-1].TRA_TYP = BPCPQORG.TRA_TYP;
            CEP.TRC(SCCGWA, LNCPCFTA.FLG_DATA[WS_IDX-1].TRA_TYP);
            if (!LNCPCFTA.FLG_DATA[1-1].TRA_TYP.equalsIgnoreCase(LNCPCFTA.FLG_DATA[WS_IDX-1].TRA_TYP)) {
                LNCPCFTA.FTA_FLG = 'Y';
            }
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
