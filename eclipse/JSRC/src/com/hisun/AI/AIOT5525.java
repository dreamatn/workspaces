package com.hisun.AI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class AIOT5525 {
    String CPN_P_INQ_ORG_LOW = "BP-P-INQ-ORG-LOW";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_LAST_BR = 0;
    int WS_CNT = 0;
    int WS_CNT1 = 0;
    char WS_BR_FLG = ' ';
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AICSGGC AICSGGC = new AICSGGC();
    BPCPORLO BPCPORLO = new BPCPORLO();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AIB5525_AWA_5525 AIB5525_AWA_5525;
    BPCPORUP_DATA_INFO BPCPORUP;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "AIOT5525 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "LBY-----------------------");
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB5525_AWA_5525>");
        AIB5525_AWA_5525 = (AIB5525_AWA_5525) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        CEP.TRC(SCCGWA, "TEST");
        CEP.TRC(SCCGWA, AIB5525_AWA_5525.PAGE_ROW);
        CEP.TRC(SCCGWA, AIB5525_AWA_5525.PAGE_NUM);
        CEP.TRC(SCCGWA, AIB5525_AWA_5525.BR);
        CEP.TRC(SCCGWA, AIB5525_AWA_5525.ITM);
        CEP.TRC(SCCGWA, AIB5525_AWA_5525.SEQ);
        CEP.TRC(SCCGWA, AIB5525_AWA_5525.STS);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        B020_BRW_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        WS_BR_FLG = 'N';
        if (BPCPORUP.DATA_INFO.LVL == '9' 
            || BPCPORUP.DATA_INFO.BR == AIB5525_AWA_5525.BR 
            || AIB5525_AWA_5525.BR == 0) {
            WS_BR_FLG = 'Y';
        }
        if (BPCPORUP.DATA_INFO.LVL != '2' 
            && BPCPORUP.DATA_INFO.LVL != '9') {
            IBS.init(SCCGWA, BPCPORLO);
            BPCPORLO.BNK = SCCGWA.COMM_AREA.TR_BANK;
            BPCPORLO.BR = BPCPORUP.DATA_INFO.BR;
            CEP.TRC(SCCGWA, BPCPORLO.BR);
            S000_CALL_BPZPORLO();
            CEP.TRC(SCCGWA, BPCPORLO.SUB_NUM);
            if (BPCPORLO.SUB_NUM != 0) {
                for (WS_CNT = 1; WS_CNT <= BPCPORLO.SUB_NUM 
                    && WS_BR_FLG != 'Y'; WS_CNT += 1) {
                    CEP.TRC(SCCGWA, WS_CNT);
                    CEP.TRC(SCCGWA, BPCPORLO.SUB_BR_DATA[WS_CNT-1].SUB_BR);
                    if (BPCPORLO.SUB_BR_DATA[WS_CNT-1].SUB_BR == AIB5525_AWA_5525.BR) {
                        WS_BR_FLG = 'Y';
                    }
                }
            }
            CEP.TRC(SCCGWA, BPCPORLO.NEXT_CALL_FLG);
            CEP.TRC(SCCGWA, WS_BR_FLG);
            CEP.TRC(SCCGWA, BPCPORLO.LAST_BR);
            if (BPCPORLO.NEXT_CALL_FLG == 'Y' 
                && WS_BR_FLG == 'N') {
                WS_LAST_BR = BPCPORLO.LAST_BR;
                IBS.init(SCCGWA, BPCPORLO);
                BPCPORLO.BNK = SCCGWA.COMM_AREA.TR_BANK;
                BPCPORLO.BR = BPCPORUP.DATA_INFO.BR;
                BPCPORLO.LAST_BR = WS_LAST_BR;
                S000_CALL_BPZPORLO();
                CEP.TRC(SCCGWA, BPCPORLO.SUB_NUM);
                if (BPCPORLO.SUB_NUM != 0) {
                    for (WS_CNT1 = 1; WS_CNT1 <= BPCPORLO.SUB_NUM 
                        && WS_BR_FLG != 'Y'; WS_CNT1 += 1) {
                        CEP.TRC(SCCGWA, WS_CNT1);
                        CEP.TRC(SCCGWA, BPCPORLO.SUB_BR_DATA[WS_CNT1-1].SUB_BR);
                        if (BPCPORLO.SUB_BR_DATA[WS_CNT1-1].SUB_BR == AIB5525_AWA_5525.BR) {
                            WS_BR_FLG = 'Y';
                        }
                    }
                }
            }
        }
        CEP.TRC(SCCGWA, WS_BR_FLG);
        CEP.TRC(SCCGWA, "AAAA");
    }
    public void B020_BRW_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICSGGC);
        AICSGGC.FUNC = 'Q';
        AICSGGC.BR = AIB5525_AWA_5525.BR;
        AICSGGC.ITM = AIB5525_AWA_5525.ITM;
        AICSGGC.SEQ = AIB5525_AWA_5525.SEQ;
        AICSGGC.CCY = AIB5525_AWA_5525.CCY;
        AICSGGC.RVS_NO = AIB5525_AWA_5525.RVS_NO;
        AICSGGC.STS = AIB5525_AWA_5525.STS;
        AICSGGC.EXP_STDT = AIB5525_AWA_5525.EXP_STDT;
        AICSGGC.EXP_ENDT = AIB5525_AWA_5525.EXP_ENDT;
        AICSGGC.SUS_TERM = AIB5525_AWA_5525.SUS_TERM;
        AICSGGC.SUS_STDT = AIB5525_AWA_5525.STR_DATE;
        AICSGGC.SUS_ENDT = AIB5525_AWA_5525.END_DATE;
        AICSGGC.STR_AMT = AIB5525_AWA_5525.STR_AMT;
        AICSGGC.END_AMT = AIB5525_AWA_5525.END_AMT;
        AICSGGC.THEIR_AC = AIB5525_AWA_5525.THEIR_AC;
        AICSGGC.PAGE_ROW = AIB5525_AWA_5525.PAGE_ROW;
        AICSGGC.PAGE_NUM = AIB5525_AWA_5525.PAGE_NUM;
        CEP.TRC(SCCGWA, AIB5525_AWA_5525.BR);
        CEP.TRC(SCCGWA, AIB5525_AWA_5525.ITM);
        CEP.TRC(SCCGWA, AIB5525_AWA_5525.SEQ);
        CEP.TRC(SCCGWA, AIB5525_AWA_5525.CCY);
        CEP.TRC(SCCGWA, AIB5525_AWA_5525.RVS_NO);
        CEP.TRC(SCCGWA, AIB5525_AWA_5525.STS);
        CEP.TRC(SCCGWA, AIB5525_AWA_5525.STR_DATE);
        CEP.TRC(SCCGWA, AIB5525_AWA_5525.END_DATE);
        CEP.TRC(SCCGWA, AIB5525_AWA_5525.EXP_STDT);
        CEP.TRC(SCCGWA, AIB5525_AWA_5525.EXP_ENDT);
        CEP.TRC(SCCGWA, AIB5525_AWA_5525.SUS_TERM);
        CEP.TRC(SCCGWA, AIB5525_AWA_5525.SUS_STDT);
        CEP.TRC(SCCGWA, AIB5525_AWA_5525.SUS_ENDT);
        CEP.TRC(SCCGWA, AIB5525_AWA_5525.STR_AMT);
        CEP.TRC(SCCGWA, AIB5525_AWA_5525.END_AMT);
        CEP.TRC(SCCGWA, AIB5525_AWA_5525.PAGE_ROW);
        CEP.TRC(SCCGWA, AIB5525_AWA_5525.PAGE_NUM);
        S00_CALL_AIZSGGC();
    }
    public void S00_CALL_AIZSGGC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-S-MAINT-GGC", AICSGGC);
    }
    public void S000_CALL_BPZPORLO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG_LOW, BPCPORLO);
        if (BPCPORLO.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPORLO.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
