package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFBTAQ {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_TLR_TOT_MTN = "BP-R-TLR-TOT-MTN    ";
    String CPN_F_TLR_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_F_INQ_ORG = "BP-P-INQ-ORG        ";
    int WS_I = 0;
    int WS_J = 0;
    char WS_TBL_TOT_FLAG = ' ';
    char WS_READNEXT_TOT_FLAG = ' ';
    char WS_REC_FND_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPCRTOTM BPCRTOTM = new BPCRTOTM();
    BPRTOT BPRTOT = new BPRTOT();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    BPCFBTAQ BPCFBTAQ;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, BPCFBTAQ BPCFBTAQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFBTAQ = BPCFBTAQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFBTAQ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRTOT);
        IBS.init(SCCGWA, BPCRTOTM);
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCFBTAQ.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if ((BPCFBTAQ.TLR.trim().length() == 0 
            && BPCFBTAQ.BR == 0) 
            || (BPCFBTAQ.TLR.trim().length() > 0 
            && BPCFBTAQ.BR != 0)) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCFBTAQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCFBTAQ.TLR.trim().length() > 0) {
            IBS.init(SCCGWA, BPCFTLRQ);
            BPCFTLRQ.INFO.TLR = BPCFBTAQ.TLR;
            S000_CALL_BPZFTLRQ();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
            if (BPCFTLRQ.INFO.TLR_TYP == 'C' 
                || BPCFTLRQ.INFO.TLR_TYP == 'S' 
                && !SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NOT_COUNTER_TELLER, BPCFBTAQ.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (BPCFBTAQ.BR != 0) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = BPCFBTAQ.BR;
            S000_CALL_BPCPQORG();
            if (pgmRtn) return;
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCFBTAQ.OP_CODE == 'B') {
            C010_QUERY_BR_TOT_PROC();
            if (pgmRtn) return;
        } else if (BPCFBTAQ.OP_CODE == 'T') {
            C020_QUERY_TLR_TOT_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRTOTM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void C010_QUERY_BR_TOT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTOT);
        BPRTOT.TLR_BR = BPCFBTAQ.BR;
        BPCRTOTM.INFO.FUNC = 'B';
        S000_CALL_BPZRTOTM();
        if (pgmRtn) return;
        WS_I = 1;
        while (WS_READNEXT_TOT_FLAG != 'Y') {
            BPCRTOTM.INFO.FUNC = 'N';
            S000_CALL_BPZRTOTM();
            if (pgmRtn) return;
            if (BPCRTOTM.RETURN_INFO == 'N') {
                WS_READNEXT_TOT_FLAG = 'Y';
            } else {
                if (BPRTOT.LAST_DATE == SCCGWA.COMM_AREA.AC_DATE) {
                    R000_SUM_DATA_PARAMETER();
                    if (pgmRtn) return;
                }
            }
        }
        BPCFBTAQ.CNT = WS_I - 1;
        BPCRTOTM.INFO.FUNC = 'E';
        S000_CALL_BPZRTOTM();
        if (pgmRtn) return;
    }
    public void C020_QUERY_TLR_TOT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTOT);
        BPRTOT.KEY.TLR = BPCFBTAQ.TLR;
        BPCRTOTM.INFO.FUNC = 'T';
        S000_CALL_BPZRTOTM();
        if (pgmRtn) return;
        WS_I = 1;
        while (WS_READNEXT_TOT_FLAG != 'Y' 
            && WS_I <= 300) {
            BPCRTOTM.INFO.FUNC = 'N';
            S000_CALL_BPZRTOTM();
            if (pgmRtn) return;
            if (BPCRTOTM.RETURN_INFO == 'N') {
                WS_READNEXT_TOT_FLAG = 'Y';
            } else {
                if (BPRTOT.LAST_DATE == SCCGWA.COMM_AREA.AC_DATE) {
                    R000_TRANS_DATA_PARAMETER();
                    if (pgmRtn) return;
                } else {
                    WS_READNEXT_TOT_FLAG = 'Y';
                }
            }
        }
        BPCFBTAQ.CNT = WS_I - 1;
        CEP.TRC(SCCGWA, BPCFBTAQ.CNT);
        BPCRTOTM.INFO.FUNC = 'E';
        S000_CALL_BPZRTOTM();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFBTAQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPCPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFBTAQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRTOTM() throws IOException,SQLException,Exception {
        BPCRTOTM.INFO.POINTER = BPRTOT;
        BPCRTOTM.INFO.REC_LEN = 95;
        IBS.CALLCPN(SCCGWA, CPN_R_TLR_TOT_MTN, BPCRTOTM);
        if (BPCRTOTM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRTOTM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFBTAQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_SUM_DATA_PARAMETER() throws IOException,SQLException,Exception {
        if (WS_I == 1) {
            BPCFBTAQ.ARRAY[WS_I-1].CCY = BPRTOT.KEY.CCY;
            BPCFBTAQ.ARRAY[WS_I-1].ACCU_TYP = BPRTOT.KEY.ACCU_TYP;
            BPCFBTAQ.ARRAY[WS_I-1].NORMAL_TX_COUNT = BPRTOT.NORMAL_TX_COUNT;
            BPCFBTAQ.ARRAY[WS_I-1].NORMAL_AMT = BPRTOT.NORMAL_AMT;
            BPCFBTAQ.ARRAY[WS_I-1].REVERSAL_COUNT = BPRTOT.REVERSAL_COUNT;
            BPCFBTAQ.ARRAY[WS_I-1].REVERSAL_AMT = BPRTOT.REVERSAL_AMT;
            BPCFBTAQ.ARRAY[WS_I-1].CANCEL_COUNT = BPRTOT.CANCEL_COUNT;
            BPCFBTAQ.ARRAY[WS_I-1].CANCEL_AMT = BPRTOT.CANCEL_AMT;
            WS_I += 1;
        } else {
            WS_REC_FND_FLG = 'N';
            for (WS_J = 1; WS_J <= WS_I 
                && WS_REC_FND_FLG != 'Y'; WS_J += 1) {
                if (BPRTOT.KEY.CCY.equalsIgnoreCase(BPCFBTAQ.ARRAY[WS_J-1].CCY) 
                    && BPRTOT.KEY.ACCU_TYP.equalsIgnoreCase(BPCFBTAQ.ARRAY[WS_J-1].ACCU_TYP)) {
                    BPCFBTAQ.ARRAY[WS_I-1].NORMAL_TX_COUNT += BPRTOT.NORMAL_TX_COUNT;
                    BPCFBTAQ.ARRAY[WS_I-1].NORMAL_AMT += BPRTOT.NORMAL_AMT;
                    BPCFBTAQ.ARRAY[WS_I-1].REVERSAL_COUNT += BPRTOT.REVERSAL_COUNT;
                    BPCFBTAQ.ARRAY[WS_I-1].REVERSAL_AMT += BPRTOT.REVERSAL_AMT;
                    BPCFBTAQ.ARRAY[WS_I-1].CANCEL_COUNT += BPRTOT.CANCEL_COUNT;
                    BPCFBTAQ.ARRAY[WS_I-1].CANCEL_AMT += BPRTOT.CANCEL_AMT;
                    WS_REC_FND_FLG = 'Y';
                }
            }
            if (WS_REC_FND_FLG == 'N') {
                BPCFBTAQ.ARRAY[WS_I-1].CCY = BPRTOT.KEY.CCY;
                BPCFBTAQ.ARRAY[WS_I-1].ACCU_TYP = BPRTOT.KEY.ACCU_TYP;
                BPCFBTAQ.ARRAY[WS_I-1].NORMAL_TX_COUNT = BPRTOT.NORMAL_TX_COUNT;
                BPCFBTAQ.ARRAY[WS_I-1].NORMAL_AMT = BPRTOT.NORMAL_AMT;
                BPCFBTAQ.ARRAY[WS_I-1].REVERSAL_COUNT = BPRTOT.REVERSAL_COUNT;
                BPCFBTAQ.ARRAY[WS_I-1].REVERSAL_AMT = BPRTOT.REVERSAL_AMT;
                BPCFBTAQ.ARRAY[WS_I-1].CANCEL_COUNT = BPRTOT.CANCEL_COUNT;
                BPCFBTAQ.ARRAY[WS_I-1].CANCEL_AMT = BPRTOT.CANCEL_AMT;
                WS_I += 1;
            }
        }
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRTOT.TLR_BR);
        CEP.TRC(SCCGWA, BPRTOT.KEY.CCY);
        CEP.TRC(SCCGWA, BPRTOT.KEY.ACCU_TYP);
        CEP.TRC(SCCGWA, BPRTOT.NORMAL_TX_COUNT);
        CEP.TRC(SCCGWA, BPRTOT.NORMAL_AMT);
        CEP.TRC(SCCGWA, BPRTOT.REVERSAL_COUNT);
        CEP.TRC(SCCGWA, BPRTOT.REVERSAL_AMT);
        CEP.TRC(SCCGWA, BPRTOT.CANCEL_COUNT);
        CEP.TRC(SCCGWA, BPRTOT.CANCEL_AMT);
        BPCFBTAQ.BR = BPRTOT.TLR_BR;
        BPCFBTAQ.ARRAY[WS_I-1].CCY = BPRTOT.KEY.CCY;
        BPCFBTAQ.ARRAY[WS_I-1].ACCU_TYP = BPRTOT.KEY.ACCU_TYP;
        BPCFBTAQ.ARRAY[WS_I-1].NORMAL_TX_COUNT = BPRTOT.NORMAL_TX_COUNT;
        BPCFBTAQ.ARRAY[WS_I-1].NORMAL_AMT = BPRTOT.NORMAL_AMT;
        BPCFBTAQ.ARRAY[WS_I-1].REVERSAL_COUNT = BPRTOT.REVERSAL_COUNT;
        BPCFBTAQ.ARRAY[WS_I-1].REVERSAL_AMT = BPRTOT.REVERSAL_AMT;
        BPCFBTAQ.ARRAY[WS_I-1].CANCEL_COUNT = BPRTOT.CANCEL_COUNT;
        BPCFBTAQ.ARRAY[WS_I-1].CANCEL_AMT = BPRTOT.CANCEL_AMT;
        WS_I += 1;
        CEP.TRC(SCCGWA, WS_I);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCFBTAQ.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCFBTAQ = ");
            CEP.TRC(SCCGWA, BPCFBTAQ);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
