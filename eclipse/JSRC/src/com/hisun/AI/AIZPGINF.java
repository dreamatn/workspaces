package com.hisun.AI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCFTLRQ;
import com.hisun.BP.BPCPRMR;
import com.hisun.BP.BPRMSG;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCPRMM;
import com.hisun.SC.SCRPRMT;

public class AIZPGINF {
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    char WS_MSG_TYPE = ' ';
    int PGINF_CNT = 0;
    char WS_AITGINF_FLG = ' ';
    String WS_INFO = " ";
    AIZPGINF_REDEFINES2 REDEFINES2 = new AIZPGINF_REDEFINES2();
    List<AIZPGINF_WS_OUT_OCCURS> WS_OUT_OCCURS = new ArrayList<AIZPGINF_WS_OUT_OCCURS>();
    int WS_BR = 0;
    int WS_AC_DATE = 0;
    String CPN_PARM_READ = "BP-PARM-READ        ";
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AIRGINF AIRGINF = new AIRGINF();
    BPRMSG BPRMSG = new BPRMSG();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCPRMM SCCSRMM = new SCCPRMM();
    SCRPRMT SCRSRMT = new SCRPRMT();
    SCCGWA SCCGWA;
    AICSMAPS AICSMAPS;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AICPGINF AICPGINF;
    public void MP(SCCGWA SCCGWA, AICPGINF AICPGINF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICPGINF = AICPGINF;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZPGINF return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, AICPGINF.RC);
        if (AICPGINF.BR == 0) {
            WS_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        } else {
            WS_BR = AICPGINF.BR;
        }
        WS_AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_BANK);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_AITGINF();
        if (pgmRtn) return;
        T000_READNEXT_AITGINF();
        if (pgmRtn) return;
        while (WS_AITGINF_FLG != 'N' 
            && PGINF_CNT < 29) {
            CEP.TRC(SCCGWA, AIRGINF.KEY.RVS_NO);
            CEP.TRC(SCCGWA, AIRGINF.G_AMT);
            CEP.TRC(SCCGWA, AIRGINF.G_BAL);
            R000_DATA_OUTPUT();
            if (pgmRtn) return;
            IBS.CPY2CLS(SCCGWA, "AI3801", AICPGINF.RC);
            T000_READNEXT_AITGINF();
            if (pgmRtn) return;
        }
        T000_ENDBR_AITGINF();
        if (pgmRtn) return;
        B020_01_PUTMSG_TERMINAL();
        if (pgmRtn) return;
    }
    public void R000_DATA_OUTPUT() throws IOException,SQLException,Exception {
        PGINF_CNT = PGINF_CNT + 1;
        WS_OUT_OCCURS = WS_OUT_OCCURS.get(PGINF_CNT-1);
        WS_OUT_OCCURS.WS_INF_RVS_NO = AIRGINF.KEY.RVS_NO;
        WS_OUT_OCCURS.set(PGINF_CNT-1, WS_OUT_OCCURS);
        WS_OUT_OCCURS = WS_OUT_OCCURS.get(PGINF_CNT-1);
        WS_OUT_OCCURS.WS_INF_AC = AIRGINF.AC;
        WS_OUT_OCCURS.set(PGINF_CNT-1, WS_OUT_OCCURS);
        WS_OUT_OCCURS = WS_OUT_OCCURS.get(PGINF_CNT-1);
        WS_OUT_OCCURS.WS_INF_BR = AIRGINF.BR;
        WS_OUT_OCCURS.set(PGINF_CNT-1, WS_OUT_OCCURS);
        WS_OUT_OCCURS = WS_OUT_OCCURS.get(PGINF_CNT-1);
        WS_OUT_OCCURS.WS_INF_ITM = AIRGINF.ITM;
        WS_OUT_OCCURS.set(PGINF_CNT-1, WS_OUT_OCCURS);
        WS_OUT_OCCURS = WS_OUT_OCCURS.get(PGINF_CNT-1);
        WS_OUT_OCCURS.WS_INF_SEQ = AIRGINF.SEQ;
        WS_OUT_OCCURS.set(PGINF_CNT-1, WS_OUT_OCCURS);
        WS_OUT_OCCURS = WS_OUT_OCCURS.get(PGINF_CNT-1);
        WS_OUT_OCCURS.WS_INF_CCY = AIRGINF.CCY;
        WS_OUT_OCCURS.set(PGINF_CNT-1, WS_OUT_OCCURS);
        WS_OUT_OCCURS = WS_OUT_OCCURS.get(PGINF_CNT-1);
        WS_OUT_OCCURS.WS_INF_RVS_KND = AIRGINF.RVS_KND;
        WS_OUT_OCCURS.set(PGINF_CNT-1, WS_OUT_OCCURS);
        WS_OUT_OCCURS = WS_OUT_OCCURS.get(PGINF_CNT-1);
        WS_OUT_OCCURS.WS_INF_RVS_EXP = AIRGINF.RVS_EXP;
        WS_OUT_OCCURS.set(PGINF_CNT-1, WS_OUT_OCCURS);
        WS_OUT_OCCURS.get(PGINF_CNT-1).WS_INF_G_AMT = AIRGINF.G_AMT + 0;
        WS_OUT_OCCURS.get(PGINF_CNT-1).WS_INF_G_BAL = AIRGINF.G_BAL + 0;
    }
    public void T000_STARTBR_AITGINF() throws IOException,SQLException,Exception {
        AITGINF_BR.rp = new DBParm();
        AITGINF_BR.rp.TableName = "AITGINF";
        AITGINF_BR.rp.where = "( BR = :WS_BR "
            + "AND RVS_EXP <= :WS_AC_DATE ) "
            + "AND ( STS = 'N' "
            + "OR STS = 'P' )";
        IBS.STARTBR(SCCGWA, AIRGINF, this, AITGINF_BR);
    }
    public void B020_01_PUTMSG_TERMINAL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, PGINF_CNT);
        if (PGINF_CNT > 0) {
            CEP.TRC(SCCGWA, BPCFTLRQ.INFO.ATH_LVL);
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_GRVS_EXP_EXIST;
            WS_CNT = 1;
            while (WS_CNT <= PGINF_CNT 
                && WS_CNT <= 29) {
                WS_INFO = " ";
                REDEFINES2.WS_RVS_TXT = "RVS:";
                WS_INFO = IBS.CLS2CPY(SCCGWA, REDEFINES2);
                REDEFINES2.WS_FIL1 = '-';
                WS_INFO = IBS.CLS2CPY(SCCGWA, REDEFINES2);
                REDEFINES2.WS_FIL2 = '-';
                WS_INFO = IBS.CLS2CPY(SCCGWA, REDEFINES2);
                REDEFINES2.WS_FIL3 = '-';
                WS_INFO = IBS.CLS2CPY(SCCGWA, REDEFINES2);
                REDEFINES2.WS_RVS_NO = WS_OUT_OCCURS.get(WS_CNT-1).WS_INF_RVS_NO;
                WS_INFO = IBS.CLS2CPY(SCCGWA, REDEFINES2);
                REDEFINES2.WS_RVS_EXP = WS_OUT_OCCURS.get(WS_CNT-1).WS_INF_RVS_EXP;
                WS_INFO = IBS.CLS2CPY(SCCGWA, REDEFINES2);
                REDEFINES2.WS_G_AMT = WS_OUT_OCCURS.get(WS_CNT-1).WS_INF_G_AMT;
                WS_INFO = IBS.CLS2CPY(SCCGWA, REDEFINES2);
                REDEFINES2.WS_G_BAL = WS_OUT_OCCURS.get(WS_CNT-1).WS_INF_G_BAL;
                WS_INFO = IBS.CLS2CPY(SCCGWA, REDEFINES2);
                if (WS_CNT == 29) {
                    if (WS_INFO == null) WS_INFO = "";
                    JIBS_tmp_int = WS_INFO.length();
                    for (int i=0;i<72-JIBS_tmp_int;i++) WS_INFO += " ";
                    WS_INFO = WS_INFO.substring(0, 20 - 1) + " AND MORE..." + WS_INFO.substring(20 + 14 - 1);
                    IBS.CPY2CLS(SCCGWA, WS_INFO, REDEFINES2);
                }
                CEP.TRC(SCCGWA, WS_CNT);
                CEP.TRC(SCCGWA, PGINF_CNT);
                CEP.TRC(SCCGWA, "AABB");
                WS_CNT = WS_CNT + 1;
            }
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PARM_READ, BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-PARM-MAINTAIN", SCCSRMM);
        if (SCCSRMM.RC.RC_RTNCODE != 0) {
