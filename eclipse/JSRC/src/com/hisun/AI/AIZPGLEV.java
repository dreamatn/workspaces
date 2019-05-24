package com.hisun.AI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class AIZPGLEV {
    boolean pgmRtn = false;
    int K_OCCURS_MAX1 = 10;
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AICOGLEV AICOGLEV;
    public void MP(SCCGWA SCCGWA, AICOGLEV AICOGLEV) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICOGLEV = AICOGLEV;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZPGLEV return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHK_IPT();
        if (pgmRtn) return;
        B020_GEN_EWA();
        if (pgmRtn) return;
    }
    public void B010_CHK_IPT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AICOGLEV.EVENT_INFO.SIGN);
        CEP.TRC(SCCGWA, AICOGLEV.EVENT_INFO.BR);
        CEP.TRC(SCCGWA, AICOGLEV.EVENT_INFO.CCY);
        CEP.TRC(SCCGWA, AICOGLEV.EVENT_INFO.VAL_DATE);
        CEP.TRC(SCCGWA, AICOGLEV.EVENT_INFO.AMT);
        if ((AICOGLEV.EVENT_INFO.SIGN != 'D' 
            && AICOGLEV.EVENT_INFO.SIGN != 'C')) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_DCFLG_NOT_VAL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (AICOGLEV.EVENT_INFO.AMT == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_AMT_NOT_VAL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_GEN_EWA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "GL";
        if (AICOGLEV.EVENT_INFO.SIGN == 'D') {
            BPCPOEWA.DATA.EVENT_CODE = "DR";
        } else {
            BPCPOEWA.DATA.EVENT_CODE = "CR";
        }
        BPCPOEWA.DATA.BR_OLD = AICOGLEV.EVENT_INFO.BR;
        for (WS_I = 1; WS_I <= K_OCCURS_MAX1; WS_I += 1) {
            CEP.TRC(SCCGWA, AICOGLEV.EVENT_INFO.ITM_INFO.ITM[WS_I-1]);
            BPCPOEWA.DATA.GLAC_INFO[WS_I-1].ITM1 = AICOGLEV.EVENT_INFO.ITM_INFO.ITM[WS_I-1];
        }
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = AICOGLEV.EVENT_INFO.CCY;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = AICOGLEV.EVENT_INFO.AMT;
        BPCPOEWA.DATA.VALUE_DATE = AICOGLEV.EVENT_INFO.VAL_DATE;
        BPCPOEWA.DATA.CI_NO = AICOGLEV.EVENT_INFO.CI_NO;
        BPCPOEWA.DATA.REF_NO = AICOGLEV.EVENT_INFO.REF_NO;
        BPCPOEWA.DATA.PORT = AICOGLEV.EVENT_INFO.PORT;
        BPCPOEWA.DATA.CHQ_NO = AICOGLEV.EVENT_INFO.CHQ_NO;
        BPCPOEWA.DATA.POST_DATE = AICOGLEV.EVENT_INFO.POST_DATE;
        BPCPOEWA.DATA.POST_NARR = AICOGLEV.EVENT_INFO.POST_NARR;
        BPCPOEWA.DATA.NARR_CD = AICOGLEV.EVENT_INFO.NARR_CD;
        BPCPOEWA.DATA.DESC = AICOGLEV.EVENT_INFO.DESC;
        BPCPOEWA.DATA.EFF_DAYS = AICOGLEV.EVENT_INFO.EFF_DAYS;
        BPCPOEWA.DATA.FILLER = AICOGLEV.EVENT_INFO.FILLER;
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, WS_ERR_MSG, AICOGLEV.RC);
        Z_RET();
        if (pgmRtn) return;
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (AICOGLEV.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "AICOGLEV = ");
            CEP.TRC(SCCGWA, AICOGLEV);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
