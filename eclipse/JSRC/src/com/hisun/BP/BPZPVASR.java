package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.AI.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPVASR {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    BPZPVASR_WS_IDX WS_IDX = new BPZPVASR_WS_IDX();
    BPZPVASR_WS_ANS_CD_TEMP WS_ANS_CD_TEMP = new BPZPVASR_WS_ANS_CD_TEMP();
    String WS_ITM_TEMP = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AICOVLOG AICOVLOG = new AICOVLOG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRVWA BPRVWA;
    BPRANS BPRANS;
    BPCOVASR BPCOVASR;
    public void MP(SCCGWA SCCGWA, BPCOVASR BPCOVASR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOVASR = BPCOVASR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPVASR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPRVWA = (BPRVWA) GWA_BP_AREA.VCH_AREA.VCH_AREA_PTR;
        BPRANS = (BPRANS) GWA_BP_AREA.ANS_AREA_PTR;
        IBS.init(SCCGWA, BPCOVASR.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRVWA.BASIC_AREA.ODE_FLG);
        CEP.TRC(SCCGWA, BPRVWA.BASIC_AREA.INTF_MODE);
        if (BPRVWA.BASIC_AREA.INTF_MODE == 'O' 
            || BPRVWA.BASIC_AREA.INTF_MODE == 'B' 
            || BPRVWA.BASIC_AREA.ODE_FLG == 'Y') {
            B030_SORT_BY_CPN_SEQ();
            if (pgmRtn) return;
            if (BPRVWA.BASIC_AREA.INTF_MODE == 'O' 
                || BPRVWA.BASIC_AREA.INTF_MODE == 'B') {
                B040_GET_REL_INFO();
                if (pgmRtn) return;
            }
        }
    }
    public void B030_SORT_BY_CPN_SEQ() throws IOException,SQLException,Exception {
        for (WS_IDX.WS_I1 = 1; WS_IDX.WS_I1 <= BPRVWA.BASIC_AREA.CNT; WS_IDX.WS_I1 += 1) {
            WS_IDX.WS_I2 = BPRVWA.BASIC_AREA.CNT - WS_IDX.WS_I1;
            for (WS_IDX.WS_I3 = 1; WS_IDX.WS_I3 <= WS_IDX.WS_I2; WS_IDX.WS_I3 += 1) {
                WS_IDX.WS_I4 = WS_IDX.WS_I3 + 1;
                if (BPRVWA.VCH_AREA.get(WS_IDX.WS_I3-1).CPN_CALL_SEQ > BPRVWA.VCH_AREA.get(WS_IDX.WS_I4-1).CPN_CALL_SEQ) {
                    WS_ITM_TEMP = IBS.CLS2CPY(SCCGWA, BPRVWA.VCH_AREA.get(WS_IDX.WS_I3-1));
                    BPRVWA.VCH_AREA.set(WS_IDX.WS_I3-1, BPRVWA.VCH_AREA.get(WS_IDX.WS_I4-1));
                    BPRVWA.VCH_AREA.set(WS_IDX.WS_I4-1, WS_ITM_TEMP);
                    if (BPRVWA.BASIC_AREA.ODE_FLG == 'Y') {
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRANS.VCH_AREA.ITM.get(WS_IDX.WS_I3-1));
                        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ANS_CD_TEMP);
                        BPRANS.VCH_AREA.ITM.set(WS_IDX.WS_I3-1, BPRANS.VCH_AREA.ITM.get(WS_IDX.WS_I4-1));
                        BPRANS.VCH_AREA.ITM.set(WS_IDX.WS_I4-1, WS_ANS_CD_TEMP);
                    }
                }
            }
        }
    }
    public void B040_GET_REL_INFO() throws IOException,SQLException,Exception {
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, WS_ERR_MSG, BPCOVASR.RC);
        Z_RET();
        if (pgmRtn) return;
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCOVASR.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCOVASR = ");
            CEP.TRC(SCCGWA, BPCOVASR);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
