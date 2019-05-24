package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFFACI {
    String K_PGM_NAME = "BPZFFACI";
    String WS_ERR_MSG = " ";
    short WS_CNT1 = 0;
    char WS_AC_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    BPCGPFEE BPCGPFEE;
    BPCFFACI BPCFFACI;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCFFACI BPCFFACI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFFACI = BPCFFACI;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZFFACI return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCGPFEE = (BPCGPFEE) GWA_BP_AREA.FEE_AREA.FEE_PARM_PTR;
        BPCFFACI.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        BPCFFACI.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        WS_AC_FLAG = 'D';
        if (BPCGPFEE.AC_INFO.AC_CNT < 5) {
            for (WS_CNT1 = 1; WS_CNT1 <= 5 
                && WS_AC_FLAG != 'N'; WS_CNT1 += 1) {
                if (BPCFFACI.AC_INFO.AC_NO.equalsIgnoreCase(BPCGPFEE.AC_INFO.AC_INFO1[WS_CNT1-1].AC_NO)) {
                    WS_AC_FLAG = 'N';
                }
            }
            if (WS_AC_FLAG == 'D') {
                BPCGPFEE.AC_INFO.AC_CNT += 1;
                BPCGPFEE.AC_INFO.AC_INFO1[BPCGPFEE.AC_INFO.AC_CNT-1].AC_NO = BPCFFACI.AC_INFO.AC_NO;
                BPCGPFEE.AC_INFO.AC_INFO1[BPCGPFEE.AC_INFO.AC_CNT-1].AC_REF = BPCFFACI.AC_INFO.AC_REF;
                BPCGPFEE.AC_INFO.AC_INFO1[BPCGPFEE.AC_INFO.AC_CNT-1].AC_CI = BPCFFACI.AC_INFO.AC_CI;
                BPCGPFEE.AC_INFO.AC_INFO1[BPCGPFEE.AC_INFO.AC_CNT-1].AC_STS = BPCFFACI.AC_INFO.AC_STS;
                BPCGPFEE.AC_INFO.AC_INFO1[BPCGPFEE.AC_INFO.AC_CNT-1].AC_BR = BPCFFACI.AC_INFO.AC_BR;
                BPCGPFEE.AC_INFO.AC_INFO1[BPCGPFEE.AC_INFO.AC_CNT-1].AC_PRD = BPCFFACI.AC_INFO.AC_PRD;
                BPCGPFEE.AC_INFO.AC_INFO1[BPCGPFEE.AC_INFO.AC_CNT-1].AC_BAL = BPCFFACI.AC_INFO.AC_BAL;
                BPCGPFEE.AC_INFO.AC_INFO1[BPCGPFEE.AC_INFO.AC_CNT-1].AC_AVG = BPCFFACI.AC_INFO.AC_AVG;
            }
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCFFACI.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCFFACI = ");
            CEP.TRC(SCCGWA, BPCFFACI);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
