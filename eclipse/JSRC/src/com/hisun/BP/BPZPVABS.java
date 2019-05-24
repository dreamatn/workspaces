package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPVABS {
    String JIBS_tmp_str[] = new String[10];
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
    BPCOVABS BPCOVABS;
    public void MP(SCCGWA SCCGWA, BPCOVABS BPCOVABS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOVABS = BPCOVABS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPVABS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCOVABS.RC);
        CEP.TRC(SCCGWA, BPCOVABS.REDEFINES19.VH_IND);
        if (BPCOVABS.REDEFINES19.VH_IND == 'Y') {
            BPRVWA = (BPRVWA) BPCOVABS.REDEFINES19.VH_PTR;
        } else {
            BPRVWA = (BPRVWA) GWA_BP_AREA.VCH_AREA.VCH_AREA_PTR;
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (GWA_BP_AREA.VCH_AREA.VCH_MAX_CNT <= 0 
            && BPCOVABS.REDEFINES19.VH_IND != 'Y') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_ALLO_MAX_CNT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCOVABS.ODE_FLG);
        BPRVWA.BASIC_AREA.CASE_NO = BPCOVABS.CASE_NO;
        BPRVWA.BASIC_AREA.TR_TYPE = BPCOVABS.TR_TYPE;
        BPRVWA.BASIC_AREA.ODE_FLG = BPCOVABS.ODE_FLG;
        BPRVWA.BASIC_AREA.ODE_GRP_NO = BPCOVABS.ODE_GRP_NO;
        if (BPCOVABS.OTHSYS_KEY.trim().length() > 0) {
            BPRVWA.BASIC_AREA.OTHSYS_KEY = BPCOVABS.OTHSYS_KEY;
        }
        BPRVWA.BASIC_AREA.CHNL = BPCOVABS.CHNL;
        BPRVWA.BASIC_AREA.OTH_TR_DATE = BPCOVABS.OTH_TR_DATE;
        BPRVWA.BASIC_AREA.OTHSYS_ID = BPCOVABS.OTHSYS_ID;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOVABS.CNTR_INFO);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRVWA.BASIC_AREA.CNTR_INFO);
        BPRVWA.BASIC_AREA.INTF_MODE = BPCOVABS.INTF_MODE;
        BPRVWA.BASIC_AREA.BASIC_FLR = BPCOVABS.BASIC_FLR;
        IBS.CPY2CLS(SCCGWA, BPRVWA.BASIC_AREA.BASIC_FLR, BPRVWA.BASIC_AREA.REDEFINES17);
        CEP.TRC(SCCGWA, BPCOVABS.REDEFINES19.VH_IND);
        CEP.TRC(SCCGWA, BPRVWA.BASIC_AREA.REDEFINES17.VH_IND);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, WS_ERR_MSG, BPCOVABS.RC);
        Z_RET();
        if (pgmRtn) return;
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCOVABS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCOVABS = ");
            CEP.TRC(SCCGWA, BPCOVABS);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
