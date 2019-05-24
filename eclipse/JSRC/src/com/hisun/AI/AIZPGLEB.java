package com.hisun.AI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class AIZPGLEB {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_OCCURS_MAX1 = 10;
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCBWEVT BPCBWEVT = new BPCBWEVT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AICOGLEB AICOGLEB;
    public void MP(SCCGWA SCCGWA, AICOGLEB AICOGLEB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICOGLEB = AICOGLEB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZPGLEB return!");
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
        B020_GEN_EVENT();
        if (pgmRtn) return;
    }
    public void B010_CHK_IPT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AICOGLEB.EVENT_INFO.EVENT.SIGN);
        CEP.TRC(SCCGWA, AICOGLEB.EVENT_INFO.EVENT.BR);
        CEP.TRC(SCCGWA, AICOGLEB.EVENT_INFO.EVENT.CCY);
        CEP.TRC(SCCGWA, AICOGLEB.EVENT_INFO.EVENT.VAL_DATE);
        CEP.TRC(SCCGWA, AICOGLEB.EVENT_INFO.EVENT.AMT);
        if ((AICOGLEB.EVENT_INFO.EVENT.SIGN != 'D' 
            && AICOGLEB.EVENT_INFO.EVENT.SIGN != 'C')) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_DCFLG_NOT_VAL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (AICOGLEB.EVENT_INFO.EVENT.AMT == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_AMT_NOT_VAL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_GEN_EVENT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCBWEVT);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICOGLEB.BASIC_INFO.SET_NO);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCBWEVT.INFO.SET_NO);
        BPCBWEVT.INFO.AP_MMO = AICOGLEB.BASIC_INFO.AP_MMO;
        BPCBWEVT.INFO.TR_CODE = AICOGLEB.BASIC_INFO.TR_CODE;
        BPCBWEVT.INFO.TR_MMO = AICOGLEB.BASIC_INFO.TR_MMO;
        BPCBWEVT.INFO.TR_DATE = AICOGLEB.BASIC_INFO.TR_DATE;
        BPCBWEVT.INFO.TR_TIME = AICOGLEB.BASIC_INFO.TR_TIME;
        BPCBWEVT.INFO.TR_BK = AICOGLEB.BASIC_INFO.TR_BK;
        BPCBWEVT.INFO.TR_BR = AICOGLEB.BASIC_INFO.TR_BR;
        BPCBWEVT.INFO.TR_TELLER = AICOGLEB.BASIC_INFO.TR_TELLER;
        BPCBWEVT.INFO.TM_NO = AICOGLEB.BASIC_INFO.TM_NO;
        BPCBWEVT.INFO.CHNL_NO = AICOGLEB.BASIC_INFO.CHNL_NO;
        BPCBWEVT.INFO.TR_TYPE = AICOGLEB.BASIC_INFO.TR_TYPE;
        BPCBWEVT.INFO.EVENT.CNTR_TYPE = "GL";
        if (AICOGLEB.EVENT_INFO.EVENT.SIGN == 'D') {
            BPCBWEVT.INFO.EVENT.EVENT_CODE = "DR";
        } else {
            BPCBWEVT.INFO.EVENT.EVENT_CODE = "CR";
        }
        BPCBWEVT.INFO.EVENT.BR_OLD = AICOGLEB.EVENT_INFO.EVENT.BR;
        for (WS_I = 1; WS_I <= K_OCCURS_MAX1; WS_I += 1) {
            CEP.TRC(SCCGWA, AICOGLEB.EVENT_INFO.EVENT.ITM_INFO.ITM[WS_I-1]);
            BPCBWEVT.INFO.EVENT.GL_AC[WS_I-1].ITM1 = AICOGLEB.EVENT_INFO.EVENT.ITM_INFO.ITM[WS_I-1];
        }
        BPCBWEVT.INFO.EVENT.EVENT_CCY[1-1].CCY = AICOGLEB.EVENT_INFO.EVENT.CCY;
        BPCBWEVT.INFO.EVENT.EVENT_AMT[1-1].AMT = AICOGLEB.EVENT_INFO.EVENT.AMT;
        BPCBWEVT.INFO.EVENT.VAL_DATE = AICOGLEB.EVENT_INFO.EVENT.VAL_DATE;
        BPCBWEVT.INFO.EVENT.CI_NO = AICOGLEB.EVENT_INFO.EVENT.CI_NO;
        BPCBWEVT.INFO.EVENT.REF_NO = AICOGLEB.EVENT_INFO.EVENT.REF_NO;
        BPCBWEVT.INFO.EVENT.PORTFO_CD = AICOGLEB.EVENT_INFO.EVENT.PORTFO_CD;
        BPCBWEVT.INFO.EVENT.CHQ_NO = AICOGLEB.EVENT_INFO.EVENT.CHQ_NO;
        BPCBWEVT.INFO.EVENT.POST_DATE = AICOGLEB.EVENT_INFO.EVENT.POST_DATE;
        BPCBWEVT.INFO.EVENT.POST_NARR = AICOGLEB.EVENT_INFO.EVENT.POST_NARR;
        BPCBWEVT.INFO.EVENT.NARR_CD = AICOGLEB.EVENT_INFO.EVENT.NARR_CD;
        BPCBWEVT.INFO.EVENT.DESC = AICOGLEB.EVENT_INFO.EVENT.DESC;
        BPCBWEVT.INFO.EVENT.EFF_DAYS = AICOGLEB.EVENT_INFO.EVENT.EFF_DAYS;
        BPCBWEVT.INFO.OTHSYS_KEY = AICOGLEB.EVENT_INFO.EVENT.OTHSYS_KEY;
        BPCBWEVT.INFO.EVENT.FLR = AICOGLEB.EVENT_INFO.EVENT.FLR;
        S000_CALL_BPZBWEVT();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCBWEVT.INFO.SET_NO);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], AICOGLEB.BASIC_INFO.SET_NO);
    }
    public void S000_CALL_BPZBWEVT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-BAT-EVENT", BPCBWEVT);
        if (BPCBWEVT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCBWEVT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, WS_ERR_MSG, AICOGLEB.RC);
        Z_RET();
        if (pgmRtn) return;
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (AICOGLEB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "AICOGLEB = ");
            CEP.TRC(SCCGWA, AICOGLEB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
