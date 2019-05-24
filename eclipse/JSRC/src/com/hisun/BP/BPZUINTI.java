package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZUINTI {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String BP_TENOR = "S301";
    String WS_ERR_MSG = " ";
    int WS_IDX = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRRTID BPRRTID = new BPRRTID();
    BPCRMRTD BPCRMRTD = new BPCRMRTD();
    BPCCINTI BPCCINTI = new BPCCINTI();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPCUINTI BPCUINTI;
    public void MP(SCCGWA SCCGWA, BPCUINTI BPCUINTI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCUINTI = BPCUINTI;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZUINTI return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCUINTI);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        CEP.TRC(SCCGWA, BPCUINTI);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_QUERY_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCUINTI.CCY);
        CEP.TRC(SCCGWA, BPCUINTI.BASE_TYP);
        if (BPCUINTI.CCY.trim().length() == 0 
            || BPCUINTI.BASE_TYP.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_R_C_T_MUST_INPUT_ONE, BPCUINTI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCUINTI.TENOR.trim().length() == 0) {
            BPCUINTI.TENOR = BP_TENOR;
        }
        CEP.TRC(SCCGWA, BPCUINTI.BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, BPCRBANK.HEAD_BR);
        if (BPCUINTI.BR == 0) {
            BPCUINTI.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        }
    }
    public void B020_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRRTID);
        IBS.init(SCCGWA, BPCRMRTD);
        IBS.init(SCCGWA, BPCCINTI);
        CEP.TRC(SCCGWA, BPCUINTI.CCY);
        CEP.TRC(SCCGWA, BPCUINTI.BASE_TYP);
        CEP.TRC(SCCGWA, BPCUINTI.TENOR);
        CEP.TRC(SCCGWA, BPCUINTI.DT);
        CEP.TRC(SCCGWA, BPCUINTI.BR);
        BPCCINTI.FUNC = 'I';
        BPCCINTI.BASE_INFO.BR = BPCUINTI.BR;
        BPCCINTI.BASE_INFO.CCY = BPCUINTI.CCY;
        BPCCINTI.BASE_INFO.BASE_TYP = BPCUINTI.BASE_TYP;
        BPCCINTI.BASE_INFO.TENOR = BPCUINTI.TENOR;
        BPCCINTI.BASE_INFO.DT = BPCUINTI.DT;
        S000_CALL_BPZCINTI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCCINTI.RC.RC_CODE);
        CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.OWN_RATE);
        CEP.TRC(SCCGWA, BPCUINTI.OWN_RATE);
        if (BPCCINTI.RC.RC_CODE == 0) {
            BPCUINTI.OWN_RATE = BPCCINTI.BASE_INFO.OWN_RATE;
            BPCUINTI.INTDAY = BPCCINTI.BASE_INFO.INTDAY;
            BPCUINTI.N_DT = BPCCINTI.BASE_INFO.N_DT;
            BPCUINTI.BDESC = BPCCINTI.BASE_INFO.BDESC;
            BPCUINTI.TDESC = BPCCINTI.BASE_INFO.TDESC;
            CEP.TRC(SCCGWA, "AAAAAAAAAAAAAA");
            CEP.TRC(SCCGWA, BPCUINTI.OWN_RATE);
        } else {
            CEP.TRC(SCCGWA, "BBBBBBBBBBBB");
            CEP.TRC(SCCGWA, BPCCINTI.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCCINTI.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUINTI.RC);
        }
    }
    public void S000_CALL_BPZCINTI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-C-INTR-INQ", BPCCINTI);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCUINTI.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCUINTI = ");
            CEP.TRC(SCCGWA, BPCUINTI);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
