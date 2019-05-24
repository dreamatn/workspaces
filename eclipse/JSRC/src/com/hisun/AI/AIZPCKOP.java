package com.hisun.AI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class AIZPCKOP {
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    int WS_OPEN_PERIOD = 0;
    AIZPCKOP_WS_COMPUTE WS_COMPUTE = new AIZPCKOP_WS_COMPUTE();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPQBAI BPCPQBAI = new BPCPQBAI();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AICOCKOP AICOCKOP;
    public void MP(SCCGWA SCCGWA, AICOCKOP AICOCKOP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICOCKOP = AICOCKOP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZPCKOP return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AICOCKOP.FUNC_CD);
        CEP.TRC(SCCGWA, AICOCKOP.FORWARD_DT);
        CEP.TRC(SCCGWA, AICOCKOP.BACK_DT);
        CEP.TRC(SCCGWA, AICOCKOP.CYC_FLG);
        CEP.TRC(SCCGWA, AICOCKOP.CLOSE_DT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        IBS.init(SCCGWA, BPCPQBAI);
        if (AICOCKOP.FUNC_CD == 'A') {
            BPCPQBAI.DATA_INFO.FORWARD_DT = AICOCKOP.FORWARD_DT;
            BPCPQBAI.DATA_INFO.BACK_DT = AICOCKOP.BACK_DT;
            BPCPQBAI.DATA_INFO.CYC_FLG = AICOCKOP.CYC_FLG;
            BPCPQBAI.DATA_INFO.CLOSE_DT = AICOCKOP.CLOSE_DT;
        } else {
            BPCPQBAI.DATA_INFO.BNK = SCCGWA.COMM_AREA.TR_BANK;
            S000_CALL_BPZPQBAI();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCPQBAI.DATA_INFO.FORWARD_DT);
        CEP.TRC(SCCGWA, BPCPQBAI.DATA_INFO.BACK_DT);
        CEP.TRC(SCCGWA, BPCPQBAI.DATA_INFO.CYC_FLG);
        CEP.TRC(SCCGWA, BPCPQBAI.DATA_INFO.CLOSE_DT);
        CEP.TRC(SCCGWA, AICOCKOP.VAL_DATE);
        if (AICOCKOP.VAL_DATE < SCCGWA.COMM_AREA.AC_DATE 
            && BPCPQBAI.DATA_INFO.CYC_FLG == 'N') {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_OPEN_PERD_IS_CLOSED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, AICOCKOP.ONL_ACDT);
        if (AICOCKOP.ONL_ACDT != 0) {
            WS_COMPUTE.WS_CPU1_DATE = AICOCKOP.ONL_ACDT;
            IBS.CPY2CLS(SCCGWA, WS_COMPUTE.WS_CPU1_DATE+"", WS_COMPUTE.REDEFINES5);
        } else {
            WS_COMPUTE.WS_CPU1_DATE = SCCGWA.COMM_AREA.AC_DATE;
            IBS.CPY2CLS(SCCGWA, WS_COMPUTE.WS_CPU1_DATE+"", WS_COMPUTE.REDEFINES5);
        }
        WS_COMPUTE.WS_CPU2_DATE = AICOCKOP.VAL_DATE;
        IBS.CPY2CLS(SCCGWA, WS_COMPUTE.WS_CPU2_DATE+"", WS_COMPUTE.REDEFINES10);
        WS_OPEN_PERIOD = ( WS_COMPUTE.REDEFINES10.WS_CPU2_YYYY * 12 + WS_COMPUTE.REDEFINES10.WS_CPU2_MM ) - ( WS_COMPUTE.REDEFINES5.WS_CPU1_YYYY * 12 + WS_COMPUTE.REDEFINES5.WS_CPU1_MM );
        CEP.TRC(SCCGWA, WS_OPEN_PERIOD);
        CEP.TRC(SCCGWA, BPCPQBAI.DATA_INFO.FORWARD_DT);
        if (WS_OPEN_PERIOD < 0) {
            WS_OPEN_PERIOD = WS_OPEN_PERIOD * ( -1 );
            if (WS_OPEN_PERIOD > BPCPQBAI.DATA_INFO.BACK_DT) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_BACK_EX_OPEN;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_BPZPQBAI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-BKAI", BPCPQBAI);
        if (BPCPQBAI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQBAI.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, WS_ERR_MSG, AICOCKOP.RC);
        Z_RET();
        if (pgmRtn) return;
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (AICOCKOP.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "AICOCKOP = ");
            CEP.TRC(SCCGWA, AICOCKOP);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
