package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSATMI {
    String CPN_U_CASH_IN = "BP-U-CASH-IN";
    String CPN_U_CASH_OUT = "BP-U-CASH-OUT";
    String CPN_P_ADD_CASH_HIS = "BP-P-ADD-CHIS";
    int K_MAX_CCY_CNT = 5;
    int K_MAX_PAR_CNT = 12;
    char K_FILLER_X01 = 0X01;
    String WS_ERR_MSG = " ";
    int WS_CCY_CNT = 0;
    int WS_PAR_CNT = 0;
    int WS_CNT = 0;
    int WS_START_CNT = 0;
    int WS_INFO_CNT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCUCSIN BPCUCSIN = new BPCUCSIN();
    BPCUCSTO BPCUCSTO = new BPCUCSTO();
    BPCDNCSO BPCDNCSO = new BPCDNCSO();
    BPCPCHIS BPCPCHIS = new BPCPCHIS();
    SCCGWA SCCGWA;
    BPCSATMI BPCSATMI;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSATMI BPCSATMI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSATMI = BPCSATMI;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSATMI return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSATMI.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_PROCESS_CASH_OUT();
        B030_PROCESS_CASH_IN();
        B040_TRANS_DATA_OUTPUT();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSATMI);
        CEP.TRC(SCCGWA, BPCSATMI.FUNC);
        CEP.TRC(SCCGWA, BPCSATMI.FROM_BR);
        CEP.TRC(SCCGWA, BPCSATMI.FROM_TLR);
        CEP.TRC(SCCGWA, BPCSATMI.TO_BR);
        CEP.TRC(SCCGWA, BPCSATMI.TO_TLR);
        CEP.TRC(SCCGWA, BPCSATMI.CS_KIND);
        CEP.TRC(SCCGWA, BPCSATMI.CCY_INFO[1-1].PAR_INFO[1-1].M_FLG);
        if (BPCSATMI.FROM_BR == 0 
            && BPCSATMI.FROM_TLR.trim().length() == 0 
            || BPCSATMI.TO_BR == 0 
            && BPCSATMI.TO_TLR.trim().length() == 0 
            || BPCSATMI.CS_KIND != '0' 
            && BPCSATMI.CS_KIND != '2' 
            && BPCSATMI.CS_KIND != '3' 
            || BPCSATMI.CCY_INFO[1-1].CCY.trim().length() == 0 
            || BPCSATMI.CCY_INFO[1-1].CCY_AMT == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_PROCESS_CASH_OUT() throws IOException,SQLException,Exception {
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= K_MAX_CCY_CNT; WS_CCY_CNT += 1) {
            if (BPCSATMI.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                CEP.TRC(SCCGWA, BPCSATMI.CCY_INFO[WS_CCY_CNT-1].CCY);
                IBS.init(SCCGWA, BPCUCSTO);
                BPCUCSTO.CASH_STAT = '0';
                BPCUCSTO.VB_BR = BPCSATMI.FROM_BR;
                BPCUCSTO.VB_TLR = BPCSATMI.FROM_TLR;
                BPCUCSTO.PLBOX_TYP = BPCSATMI.FROM_TYP;
                BPCUCSTO.VB_FLG = '0';
                BPCUCSTO.CS_KIND = BPCSATMI.CS_KIND;
                BPCUCSTO.CASH_TYP = BPCSATMI.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
                BPCUCSTO.CCY = BPCSATMI.CCY_INFO[WS_CCY_CNT-1].CCY;
                BPCUCSTO.TX_AMT = BPCSATMI.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
                for (WS_PAR_CNT = 1; WS_PAR_CNT <= K_MAX_PAR_CNT; WS_PAR_CNT += 1) {
                    BPCUCSTO.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_VAL = BPCSATMI.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_VAL;
                    BPCUCSTO.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_NUM = BPCSATMI.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_NUM;
                    BPCUCSTO.PAR_INFO.PAR_REC[WS_PAR_CNT-1].M_FLG = BPCSATMI.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].M_FLG;
                }
                CEP.TRC(SCCGWA, BPCUCSTO);
                CEP.TRC(SCCGWA, BPCUCSTO.VB_TLR);
                CEP.TRC(SCCGWA, BPCUCSTO.CS_KIND);
                CEP.TRC(SCCGWA, BPCUCSTO.CCY);
                CEP.TRC(SCCGWA, BPCUCSTO.TX_AMT);
                S000_CALL_BPZUCSTO();
                IBS.init(SCCGWA, BPCPCHIS);
                BPCPCHIS.TLR = SCCGWA.COMM_AREA.TL_ID;
                BPCPCHIS.PLBOX_NO = BPCUCSTO.PLBOX_NO;
                BPCPCHIS.CASH_TYP = BPCSATMI.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
                BPCPCHIS.CCY = BPCSATMI.CCY_INFO[WS_CCY_CNT-1].CCY;
                BPCPCHIS.AMT = BPCSATMI.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
                BPCPCHIS.VB_FLG = '0';
                BPCPCHIS.IN_OUT = 'C';
                BPCPCHIS.CS_KIND = BPCSATMI.CS_KIND;
                for (WS_PAR_CNT = 1; WS_PAR_CNT <= K_MAX_PAR_CNT; WS_PAR_CNT += 1) {
                    if (BPCUCSTO.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_VAL > 0 
                        && BPCUCSTO.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_NUM > 0) {
                        BPCPCHIS.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_VAL = BPCSATMI.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_VAL;
                        BPCPCHIS.PAR_INFO.PAR_REC[WS_PAR_CNT-1].M_FLG = BPCSATMI.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].M_FLG;
                        BPCPCHIS.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_NUM = BPCSATMI.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_NUM;
                    }
                }
                S000_CALL_BPZPCHIS();
            }
        }
    }
    public void B030_PROCESS_CASH_IN() throws IOException,SQLException,Exception {
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= K_MAX_CCY_CNT; WS_CCY_CNT += 1) {
            CEP.TRC(SCCGWA, BPCSATMI.CCY_INFO[WS_CCY_CNT-1].CCY);
            CEP.TRC(SCCGWA, BPCSATMI.FUNC);
            if (BPCSATMI.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                IBS.init(SCCGWA, BPCUCSIN);
                BPCUCSIN.CASH_STAT = '0';
                BPCUCSIN.VB_FLG = '0';
                BPCUCSIN.VB_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPCUCSIN.VB_TLR = BPCSATMI.TO_TLR;
                BPCUCSIN.PLBOX_TYP = BPCSATMI.TO_TYP;
                BPCUCSIN.CS_KIND = BPCSATMI.CS_KIND;
                BPCUCSIN.CASH_TYP = BPCSATMI.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
                BPCUCSIN.CCY = BPCSATMI.CCY_INFO[WS_CCY_CNT-1].CCY;
                BPCUCSIN.TX_AMT = BPCSATMI.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
                for (WS_PAR_CNT = 1; WS_PAR_CNT <= K_MAX_PAR_CNT; WS_PAR_CNT += 1) {
                    BPCUCSIN.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_VAL = BPCSATMI.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_VAL;
                    BPCUCSIN.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_NUM = BPCSATMI.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_NUM;
                    BPCUCSIN.PAR_INFO.PAR_REC[WS_PAR_CNT-1].M_FLG = BPCSATMI.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].M_FLG;
                }
                S000_CALL_BPZUCSIN();
                IBS.init(SCCGWA, BPCPCHIS);
                BPCPCHIS.TLR = BPCSATMI.TO_TLR;
                BPCPCHIS.PLBOX_NO = BPCUCSIN.PLBOX_NO;
                BPCPCHIS.CASH_TYP = BPCSATMI.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
                BPCPCHIS.CCY = BPCSATMI.CCY_INFO[WS_CCY_CNT-1].CCY;
                BPCPCHIS.AMT = BPCSATMI.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
                BPCPCHIS.VB_FLG = '0';
                BPCPCHIS.IN_OUT = 'D';
                BPCPCHIS.CS_KIND = BPCSATMI.CS_KIND;
                for (WS_PAR_CNT = 1; WS_PAR_CNT <= K_MAX_PAR_CNT; WS_PAR_CNT += 1) {
                    if (BPCUCSTO.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_VAL > 0 
                        && BPCUCSTO.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_NUM > 0) {
                        BPCPCHIS.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_VAL = BPCSATMI.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_VAL;
                        BPCPCHIS.PAR_INFO.PAR_REC[WS_PAR_CNT-1].M_FLG = BPCSATMI.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].M_FLG;
                        BPCPCHIS.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_NUM = BPCSATMI.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_NUM;
                    }
                }
                S000_CALL_BPZPCHIS();
            }
        }
    }
    public void B040_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCDNCSO);
        BPCDNCSO.FROM_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCDNCSO.FROM_TLR = BPCSATMI.FROM_TLR;
        BPCDNCSO.TO_TLR = BPCSATMI.TO_TLR;
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5 
            && BPCSATMI.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() != 0; WS_CCY_CNT += 1) {
            BPCDNCSO.CCY_INFO[WS_CCY_CNT-1].CASH_TYP = BPCSATMI.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
            BPCDNCSO.CCY_INFO[WS_CCY_CNT-1].CCY = BPCSATMI.CCY_INFO[WS_CCY_CNT-1].CCY;
            BPCDNCSO.CCY_INFO[WS_CCY_CNT-1].AMT = BPCSATMI.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
            for (WS_START_CNT = 1; WS_START_CNT <= 12 
                && BPCSATMI.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_START_CNT-1].PAR_VAL != 0; WS_START_CNT += 1) {
                WS_INFO_CNT = ( WS_CCY_CNT - 1 ) * 12 + WS_START_CNT;
                BPCDNCSO.DT_INFO[WS_INFO_CNT-1].P_PVAL = BPCSATMI.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_START_CNT-1].PAR_VAL;
                BPCDNCSO.DT_INFO[WS_INFO_CNT-1].P_NUM = BPCSATMI.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_START_CNT-1].PAR_NUM;
                BPCDNCSO.DT_INFO[WS_INFO_CNT-1].P_MFLG = BPCSATMI.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_START_CNT-1].M_FLG;
            }
        }
        BPCDNCSO.CCY_CNT = WS_CCY_CNT;
        BPCDNCSO.DT_CNT = WS_INFO_CNT;
        CEP.TRC(SCCGWA, BPCDNCSO);
        CEP.TRC(SCCGWA, BPCDNCSO);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCSATMI.OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCDNCSO;
        SCCFMT.DATA_LEN = 1702;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZUCSIN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_CASH_IN, BPCUCSIN);
        if (BPCUCSIN.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUCSIN.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZUCSTO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_CASH_OUT, BPCUCSTO);
        if (BPCUCSTO.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUCSTO.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPCHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_ADD_CASH_HIS, BPCPCHIS);
        if (BPCPCHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPCHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
