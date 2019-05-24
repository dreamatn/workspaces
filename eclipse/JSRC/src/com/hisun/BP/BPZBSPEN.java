package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZBSPEN {
    String PGM_BPZBSPEN = "BPZBSPEN";
    String CPN_F_B_MAINTAIN_UPEN = "BP-F-B-MAINTAIN-UPEN";
    String WS_ERR_MSG = " ";
    BPZBSPEN_WS_RC WS_RC = new BPZBSPEN_WS_RC();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCRJPRM SCRJPRM = new SCRJPRM();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCBOPEN BPCBOPEN;
    BPCBFPEN BPCBFPEN;
    public void MP(SCCGWA SCCGWA, BPCBOPEN BPCBOPEN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCBOPEN = BPCBOPEN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZBSPEN return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCBFPEN = (BPCBFPEN) BPCBOPEN.RC.POINTER;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_CALL_BPZBUPEN();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCBFPEN.FEE_CODE);
        if (BPCBFPEN.FEE_CODE.trim().length() == 0) {
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPCBFPEN.FEE_AC);
        if (BPCBFPEN.FEE_AC.trim().length() == 0) {
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPCBFPEN.FEE_CCY);
        if (BPCBFPEN.FEE_CCY.trim().length() == 0) {
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPCBFPEN.FEE_ACCT);
        if (BPCBFPEN.FEE_ACCT == ' ') {
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPCBFPEN.SUS_ACCT);
        if (BPCBFPEN.SUS_ACCT == ' ') {
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPCBFPEN.FEE_AMT);
        if (BPCBFPEN.FEE_AMT <= 0) {
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPCBFPEN.NEXT_DATE);
        if (BPCBFPEN.NEXT_DATE == 0) {
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPCBFPEN.FAIL_CHG_FLG);
        if (BPCBFPEN.FAIL_CHG_FLG == ' ') {
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, " B010-CHECK-END");
    }
    public void B020_CALL_BPZBUPEN() throws IOException,SQLException,Exception {
        BPCBOPEN.RC.POINTER = BPCBFPEN;
        S000_CALL_BPZBUPEN();
    }
    public void S000_CALL_BPZBUPEN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_B_MAINTAIN_UPEN, BPCBOPEN);
        if (BPCBOPEN.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCBOPEN.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCBOPEN.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCBOPEN = ");
            CEP.TRC(SCCGWA, BPCBOPEN);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
