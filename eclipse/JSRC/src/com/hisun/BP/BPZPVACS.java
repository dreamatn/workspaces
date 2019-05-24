package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPVACS {
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRVWA BPRVWA;
    BPCOVACS BPCOVACS;
    public void MP(SCCGWA SCCGWA, BPCOVACS BPCOVACS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOVACS = BPCOVACS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPVACS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPRVWA = (BPRVWA) GWA_BP_AREA.VCH_AREA.VCH_AREA_PTR;
        IBS.init(SCCGWA, BPCOVACS.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCOVACS.CASE_NO.compareTo(SPACE) <= 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_VCH_IPT_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPRVWA.BASIC_AREA.CASE_NO = BPCOVACS.CASE_NO;
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, WS_ERR_MSG, BPCOVACS.RC);
        Z_RET();
        if (pgmRtn) return;
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCOVACS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCOVACS = ");
            CEP.TRC(SCCGWA, BPCOVACS);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
