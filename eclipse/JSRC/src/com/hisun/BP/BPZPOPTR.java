package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPOPTR {
    String K_OUTPUT_FMT = "BPPTN";
    int WS_BRANCH = 0;
    String WS_ERR_MSG = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCOPTR BPCOPTR = new BPCOPTR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRTLT BPRTLT;
    BPCFTLPM BPCFTLPM;
    public void MP(SCCGWA SCCGWA, BPCFTLPM BPCFTLPM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFTLPM = BPCFTLPM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZPOPTR return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPRTLT = (BPRTLT) SCCGWA.COMM_AREA.TLT_AREA_PTR;
        IBS.init(SCCGWA, BPCOPTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        BPCOPTR.DEF_PTR = BPRTLT.PRT_NAME1;
        BPCOPTR.PAY_PTR = BPRTLT.PRT_NAME2;
        BPCOPTR.CFM_PTR = BPRTLT.PRT_NAME3;
        if (BPCOPTR.DEF_PTR.trim().length() == 0 
            || BPCOPTR.PAY_PTR.trim().length() == 0 
            || BPCOPTR.CFM_PTR.trim().length() == 0) {
            B100_GET_ACCT_PTR();
        }
        if (BPCOPTR.DEF_PTR.trim().length() == 0 
            || BPCOPTR.PAY_PTR.trim().length() == 0 
            || BPCOPTR.CFM_PTR.trim().length() == 0) {
            B200_GET_BRANCH_PTR();
        }
        B300_FMT_OUTPUT();
    }
    public void B100_GET_ACCT_PTR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        if (BPCOPTR.DEF_PTR.trim().length() == 0) {
            BPCOPTR.DEF_PTR = BPCPQORG.DEF_PTR;
        }
        if (BPCOPTR.PAY_PTR.trim().length() == 0) {
            BPCOPTR.PAY_PTR = BPCPQORG.PAY_PTR;
        }
        if (BPCOPTR.CFM_PTR.trim().length() == 0) {
            BPCOPTR.CFM_PTR = BPCPQORG.CFM_PTR;
        }
        WS_BRANCH = BPCPQORG.SUPR_BR;
    }
    public void B200_GET_BRANCH_PTR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
        BPCPQORG.BR = WS_BRANCH;
        S000_CALL_BPZPQORG();
        if (BPCOPTR.DEF_PTR.trim().length() == 0) {
            BPCOPTR.DEF_PTR = BPCPQORG.DEF_PTR;
        }
        if (BPCOPTR.PAY_PTR.trim().length() == 0) {
            BPCOPTR.PAY_PTR = BPCPQORG.PAY_PTR;
        }
        if (BPCOPTR.CFM_PTR.trim().length() == 0) {
            BPCOPTR.CFM_PTR = BPCPQORG.CFM_PTR;
        }
    }
    public void B300_FMT_OUTPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCOPTR.DEF_PTR);
        CEP.TRC(SCCGWA, BPCOPTR.PAY_PTR);
        CEP.TRC(SCCGWA, BPCOPTR.CFM_PTR);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOPTR;
        SCCFMT.DATA_LEN = 240;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPQORG);
        BPCPQORG.SUPR_BR = 999;
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCFTLPM.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCFTLPM = ");
            CEP.TRC(SCCGWA, BPCFTLPM);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
