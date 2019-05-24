package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPVACK {
    boolean pgmRtn = false;
    String CPN_P_CHK_ACCT_CODE = "BP-P-CHK-ACCT-CODE  ";
    int K_OCCURS_TMS_MAX = 30;
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    int WS_J = 0;
    int WS_TOT_CNT = 0;
    BPZPVACK_WS_VWA_ACCUM[] WS_VWA_ACCUM = new BPZPVACK_WS_VWA_ACCUM[30];
    char WS_ITM_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOCKAT BPCOCKAT = new BPCOCKAT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRVWA BPRVWA;
    BPCOVACK BPCOVACK;
    public BPZPVACK() {
        for (int i=0;i<30;i++) WS_VWA_ACCUM[i] = new BPZPVACK_WS_VWA_ACCUM();
    }
    public void MP(SCCGWA SCCGWA, BPCOVACK BPCOVACK) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOVACK = BPCOVACK;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPVACK return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPRVWA = (BPRVWA) GWA_BP_AREA.VCH_AREA.VCH_AREA_PTR;
        IBS.init(SCCGWA, BPCOVACK.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, WS_ERR_MSG, BPCOVACK.RC);
        Z_RET();
        if (pgmRtn) return;
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCOVACK.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCOVACK = ");
            CEP.TRC(SCCGWA, BPCOVACK);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
