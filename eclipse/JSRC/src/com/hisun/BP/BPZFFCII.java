package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFFCII {
    String K_PGM_NAME = "BPZFFCII";
    String WS_ERR_MSG = " ";
    short WS_CNT1 = 0;
    char WS_CI_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    BPCGPFEE BPCGPFEE;
    BPCFFCII BPCFFCII;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCFFCII BPCFFCII) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFFCII = BPCFFCII;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZFFCII return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCGPFEE = (BPCGPFEE) GWA_BP_AREA.FEE_AREA.FEE_PARM_PTR;
        BPCFFCII.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        BPCFFCII.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        WS_CI_FLAG = 'D';
        if (BPCGPFEE.CUS_DATA.CUS_CNT < 5) {
            for (WS_CNT1 = 1; WS_CNT1 <= 5 
                && WS_CI_FLAG != 'N'; WS_CNT1 += 1) {
                if (BPCFFCII.INFO.CUS_NO.equalsIgnoreCase(BPCGPFEE.CUS_DATA.CUS_INFO[WS_CNT1-1].CUS_NO)) {
                    WS_CI_FLAG = 'N';
                }
            }
            if (WS_CI_FLAG == 'D') {
                BPCGPFEE.CUS_DATA.CUS_CNT += 1;
                BPCGPFEE.CUS_DATA.CUS_INFO[BPCGPFEE.CUS_DATA.CUS_CNT-1].CUS_NO = BPCFFCII.INFO.CUS_NO;
                BPCGPFEE.CUS_DATA.CUS_INFO[BPCGPFEE.CUS_DATA.CUS_CNT-1].CUS_TYPE = BPCFFCII.INFO.CUS_TYPE;
                BPCGPFEE.CUS_DATA.CUS_INFO[BPCGPFEE.CUS_DATA.CUS_CNT-1].CUS_STS = BPCFFCII.INFO.CUS_STS;
                BPCGPFEE.CUS_DATA.CUS_INFO[BPCGPFEE.CUS_DATA.CUS_CNT-1].CUS_GRP = BPCFFCII.INFO.CUS_GRP;
                BPCGPFEE.CUS_DATA.CUS_INFO[BPCGPFEE.CUS_DATA.CUS_CNT-1].CUS_SEG = BPCFFCII.INFO.CUS_SEG;
                BPCGPFEE.CUS_DATA.CUS_INFO[BPCGPFEE.CUS_DATA.CUS_CNT-1].CUS_CON = BPCFFCII.INFO.CUS_CON;
                for (WS_CNT1 = 1; WS_CNT1 <= 10; WS_CNT1 += 1) {
                    BPCGPFEE.CUS_DATA.CUS_INFO[BPCGPFEE.CUS_DATA.CUS_CNT-1].PRD[WS_CNT1-1].CUS_PRD = BPCFFCII.INFO.PRD[WS_CNT1-1].CUS_PRD;
                }
                BPCGPFEE.CUS_DATA.CUS_INFO[BPCGPFEE.CUS_DATA.CUS_CNT-1].CUS_AVG = BPCFFCII.INFO.CUS_AVG;
            }
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCFFCII.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCFFCII = ");
            CEP.TRC(SCCGWA, BPCFFCII);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
