package com.hisun.AI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class AIOT5520 {
    int JIBS_tmp_int;
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
    AICSGINF AICSGINF = new AICSGINF();
    BPCPORLO BPCPORLO = new BPCPORLO();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AIB5500_AWA_5500 AIB5500_AWA_5500;
    BPCPORUP_DATA_INFO BPCPORUP;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "AIOT5520 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB5500_AWA_5500>");
        AIB5500_AWA_5500 = (AIB5500_AWA_5500) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        B020_BRW_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.LVL);
        CEP.TRC(SCCGWA, AIB5500_AWA_5500.BR);
        CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_BANK);
        WS_BR_FLG = 'N';
        CEP.TRC(SCCGWA, WS_BR_FLG);
        if (BPCPORUP.DATA_INFO.BR == AIB5500_AWA_5500.BR 
            || BPCPORUP.DATA_INFO.LVL == '9') {
            WS_BR_FLG = 'Y';
        }
        if (BPCPORUP.DATA_INFO.LVL != '2' 
            && BPCPORUP.DATA_INFO.LVL != '9') {
            IBS.init(SCCGWA, BPCPORLO);
            BPCPORLO.BNK = SCCGWA.COMM_AREA.TR_BANK;
            BPCPORLO.BR = BPCPORUP.DATA_INFO.BR;
            S000_CALL_BPZPORLO();
            CEP.TRC(SCCGWA, BPCPORLO.SUB_NUM);
            if (BPCPORLO.SUB_NUM != 0) {
                for (WS_CNT = 1; WS_CNT <= BPCPORLO.SUB_NUM 
                    && WS_BR_FLG != 'Y'; WS_CNT += 1) {
                    CEP.TRC(SCCGWA, WS_CNT);
                    CEP.TRC(SCCGWA, BPCPORLO.SUB_BR_DATA[WS_CNT-1].SUB_BR);
                    if (BPCPORLO.SUB_BR_DATA[WS_CNT-1].SUB_BR == AIB5500_AWA_5500.BR) {
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
                        if (BPCPORLO.SUB_BR_DATA[WS_CNT1-1].SUB_BR == AIB5500_AWA_5500.BR) {
                            WS_BR_FLG = 'Y';
                        }
                    }
                }
            }
        }
        CEP.TRC(SCCGWA, WS_BR_FLG);
    }
    public void B020_BRW_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICSGINF);
        AICSGINF.FUNC = 'Q';
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        if (AIB5500_AWA_5500.RVS_NO == null) AIB5500_AWA_5500.RVS_NO = "";
        JIBS_tmp_int = AIB5500_AWA_5500.RVS_NO.length();
        for (int i=0;i<21-JIBS_tmp_int;i++) AIB5500_AWA_5500.RVS_NO += " ";
        CEP.TRC(SCCGWA, AIB5500_AWA_5500.RVS_NO.substring(0, 6));
        AICSGINF.BR = AIB5500_AWA_5500.BR;
        AICSGINF.ITM = AIB5500_AWA_5500.ITM;
        AICSGINF.SEQ = AIB5500_AWA_5500.SEQ;
        AICSGINF.CCY = AIB5500_AWA_5500.CCY;
        AICSGINF.RVS_NO = AIB5500_AWA_5500.RVS_NO;
        AICSGINF.STS = AIB5500_AWA_5500.STS;
        AICSGINF.EXP_STDT = AIB5500_AWA_5500.EXP_STDT;
        AICSGINF.EXP_ENDT = AIB5500_AWA_5500.EXP_ENDT;
        AICSGINF.SUS_TERM = AIB5500_AWA_5500.SUS_TERM;
        AICSGINF.SUS_STDT = AIB5500_AWA_5500.SUS_STDT;
        AICSGINF.SUS_ENDT = AIB5500_AWA_5500.SUS_ENDT;
        AICSGINF.PAGE_ROW = AIB5500_AWA_5500.PAGE_ROW;
        AICSGINF.PAGE_NUM = AIB5500_AWA_5500.PAGE_NUM;
        CEP.TRC(SCCGWA, AIB5500_AWA_5500.BR);
        CEP.TRC(SCCGWA, AIB5500_AWA_5500.ITM);
        CEP.TRC(SCCGWA, AIB5500_AWA_5500.SEQ);
        CEP.TRC(SCCGWA, AIB5500_AWA_5500.CCY);
        CEP.TRC(SCCGWA, AIB5500_AWA_5500.RVS_NO);
        CEP.TRC(SCCGWA, AIB5500_AWA_5500.STS);
        CEP.TRC(SCCGWA, AIB5500_AWA_5500.STR_DATE);
        CEP.TRC(SCCGWA, AIB5500_AWA_5500.END_DATE);
        CEP.TRC(SCCGWA, AIB5500_AWA_5500.SUS_TERM);
        CEP.TRC(SCCGWA, AIB5500_AWA_5500.SUS_STDT);
        CEP.TRC(SCCGWA, AIB5500_AWA_5500.SUS_ENDT);
        CEP.TRC(SCCGWA, AIB5500_AWA_5500.PAGE_ROW);
        CEP.TRC(SCCGWA, AIB5500_AWA_5500.PAGE_NUM);
        S00_CALL_AIZSGINF();
    }
    public void S000_CALL_BPZPORLO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG_LOW, BPCPORLO);
        if (BPCPORLO.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPORLO.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S00_CALL_AIZSGINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-S-MAINT-GINF", AICSGINF);
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
