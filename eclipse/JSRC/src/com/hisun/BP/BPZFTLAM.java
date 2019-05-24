package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFTLAM {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_TLR_TOT_MTN = "BP-R-TLR-TOT-MTN    ";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_F_INQUIRE_CCY = "BP-INQUIRE-CCY      ";
    String CPN_P_INQ_PUB_CD = "BP-P-INQ-PC         ";
    String K_ACCU_TYP = "ACCU ";
    double WS_AMT_P = 0;
    char WS_TBL_TOT_FLAG = ' ';
    char WS_READNEXT_TOT_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    BPCRTOTM BPCRTOTM = new BPCRTOTM();
    BPRTOT BPRTOT = new BPRTOT();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    SCCGWA SCCGWA;
    BPCFTLAM BPCFTLAM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, BPCFTLAM BPCFTLAM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFTLAM = BPCFTLAM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFTLAM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRTOT);
        IBS.init(SCCGWA, BPCRTOTM);
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCFTLAM.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, " INPUT DATA  ");
        CEP.TRC(SCCGWA, BPCFTLAM.TOT_TX_COUNT);
        CEP.TRC(SCCGWA, BPCFTLAM.TOT_AMT);
        CEP.TRC(SCCGWA, BPCFTLAM.TX_COUNT);
        CEP.TRC(SCCGWA, BPCFTLAM.AMT);
        CEP.TRC(SCCGWA, BPCFTLAM.TLR);
        if (BPCFTLAM.TLR.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCFTLAM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPCFTLAM.TLR;
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR_TYP);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        if (BPCFTLAM.CCY.trim().length() > 0) {
            IBS.init(SCCGWA, BPCQCCY);
            BPCQCCY.DATA.CCY = BPCFTLAM.CCY;
            S000_CALL_BPZQCCY();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "AAAAA");
        CEP.TRC(SCCGWA, BPCFTLAM.ACCU_TYP);
        if (BPCFTLAM.ACCU_TYP.trim().length() > 0) {
            IBS.init(SCCGWA, BPCOQPCD);
            BPCOQPCD.INPUT_DATA.TYPE = K_ACCU_TYP;
            BPCOQPCD.INPUT_DATA.CODE = BPCFTLAM.ACCU_TYP;
            S000_CALL_BPZPQPCD();
            if (pgmRtn) return;
        }
        if (BPCFTLAM.TX_COUNT == 0) {
            BPCFTLAM.TX_COUNT = 1;
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCFTLAM.OP_CODE == 'C') {
            C010_CLR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            C020_MOD_RECORD_PROC();
            if (pgmRtn) return;
        }
    }
    public void C010_CLR_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTOT);
        BPRTOT.KEY.TLR = BPCFTLAM.TLR;
        BPCRTOTM.INFO.FUNC = 'P';
        S000_CALL_BPZRTOTM();
        if (pgmRtn) return;
        while (BPCRTOTM.RETURN_INFO != 'N') {
            IBS.init(SCCGWA, BPRTOT);
            BPCRTOTM.INFO.FUNC = 'N';
            S000_CALL_BPZRTOTM();
            if (pgmRtn) return;
            if (BPCRTOTM.RETURN_INFO != 'N') {
                R000_TRANS_DATA_PARAMETER();
                if (pgmRtn) return;
                BPCRTOTM.INFO.FUNC = 'U';
                S000_CALL_BPZRTOTM();
                if (pgmRtn) return;
            }
        }
        BPCRTOTM.INFO.FUNC = 'E';
        S000_CALL_BPZRTOTM();
        if (pgmRtn) return;
    }
    public void C020_MOD_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTOT);
        BPRTOT.KEY.TLR = BPCFTLAM.TLR;
        BPRTOT.KEY.CCY = BPCFTLAM.CCY;
        BPRTOT.KEY.ACCU_TYP = BPCFTLAM.ACCU_TYP;
        BPCRTOTM.INFO.FUNC = 'R';
        S000_CALL_BPZRTOTM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCRTOTM.RETURN_INFO);
        if (BPCRTOTM.RETURN_INFO == 'N') {
            IBS.init(SCCGWA, BPRTOT);
            BPCRTOTM.INFO.FUNC = 'A';
            BPRTOT.KEY.TLR = BPCFTLAM.TLR;
            BPRTOT.TLR_BR = BPCFTLRQ.INFO.TLR_BR;
            BPRTOT.KEY.CCY = BPCFTLAM.CCY;
            BPRTOT.KEY.ACCU_TYP = BPCFTLAM.ACCU_TYP;
        } else {
            if (BPCRTOTM.RETURN_INFO == 'F') {
                BPCRTOTM.INFO.FUNC = 'U';
            } else {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCFTLAM.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        R000_TRANS_DATA_PARAMETER();
        if (pgmRtn) return;
        S000_CALL_BPZRTOTM();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_INF_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFTLAM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_INQUIRE_CCY, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFTLAM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQPCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_PUB_CD, BPCOQPCD);
        if (BPCOQPCD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFTLAM.RC);
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
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFTLAM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFTLAM.TOT_TX_COUNT);
        CEP.TRC(SCCGWA, BPCFTLAM.TOT_AMT);
        CEP.TRC(SCCGWA, BPCFTLAM.TX_COUNT);
        CEP.TRC(SCCGWA, BPCFTLAM.AMT);
        CEP.TRC(SCCGWA, BPRTOT.NORMAL_TX_COUNT);
        CEP.TRC(SCCGWA, BPRTOT.NORMAL_AMT);
        CEP.TRC(SCCGWA, BPRTOT.REVERSAL_COUNT);
        CEP.TRC(SCCGWA, BPRTOT.REVERSAL_AMT);
        CEP.TRC(SCCGWA, BPRTOT.CANCEL_COUNT);
        CEP.TRC(SCCGWA, BPRTOT.CANCEL_AMT);
        CEP.TRC(SCCGWA, BPCFTLAM.OP_CODE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CANCEL_IND);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.REVERSAL_IND);
        if (BPCFTLAM.OP_CODE == 'C') {
            BPRTOT.NORMAL_TX_COUNT = 0;
            BPRTOT.NORMAL_AMT = 0;
            BPRTOT.REVERSAL_COUNT = 0;
            BPRTOT.REVERSAL_AMT = 0;
            BPRTOT.CANCEL_COUNT = 0;
            BPRTOT.CANCEL_AMT = 0;
        } else {
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                BPRTOT.CANCEL_COUNT += BPCFTLAM.TX_COUNT;
                BPRTOT.CANCEL_AMT += BPCFTLAM.AMT;
                BPCFTLAM.TOT_TX_COUNT = BPRTOT.CANCEL_COUNT;
                BPCFTLAM.TOT_AMT = BPRTOT.CANCEL_AMT;
            } else if (SCCGWA.COMM_AREA.REVERSAL_IND == 'Y') {
                BPRTOT.REVERSAL_COUNT += BPCFTLAM.TX_COUNT;
                BPRTOT.REVERSAL_AMT += BPCFTLAM.AMT;
                BPCFTLAM.TOT_TX_COUNT = BPRTOT.REVERSAL_COUNT;
                BPCFTLAM.TOT_AMT = BPRTOT.REVERSAL_AMT;
            } else {
                CEP.TRC(SCCGWA, "NORMAL TX AAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                BPRTOT.NORMAL_TX_COUNT += BPCFTLAM.TX_COUNT;
                BPRTOT.NORMAL_AMT += BPCFTLAM.AMT;
                BPCFTLAM.TOT_TX_COUNT = BPRTOT.NORMAL_TX_COUNT;
                BPCFTLAM.TOT_AMT = BPRTOT.NORMAL_AMT;
                CEP.TRC(SCCGWA, BPCFTLAM.TOT_TX_COUNT);
                CEP.TRC(SCCGWA, BPCFTLAM.TOT_AMT);
                CEP.TRC(SCCGWA, BPCFTLAM.TX_COUNT);
                CEP.TRC(SCCGWA, BPCFTLAM.AMT);
                CEP.TRC(SCCGWA, BPRTOT.NORMAL_TX_COUNT);
                CEP.TRC(SCCGWA, BPRTOT.NORMAL_AMT);
                CEP.TRC(SCCGWA, "END   NORMAL  TX    LLLLLLLLLLLLLLLLLLL");
            }
        }
        BPRTOT.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, BPCFTLAM.TOT_TX_COUNT);
        CEP.TRC(SCCGWA, BPCFTLAM.TOT_AMT);
        CEP.TRC(SCCGWA, BPCFTLAM.TX_COUNT);
        CEP.TRC(SCCGWA, BPCFTLAM.AMT);
        CEP.TRC(SCCGWA, BPRTOT.NORMAL_TX_COUNT);
        CEP.TRC(SCCGWA, BPRTOT.NORMAL_AMT);
        CEP.TRC(SCCGWA, BPRTOT.REVERSAL_COUNT);
        CEP.TRC(SCCGWA, BPRTOT.REVERSAL_AMT);
        CEP.TRC(SCCGWA, BPRTOT.CANCEL_COUNT);
        CEP.TRC(SCCGWA, BPRTOT.CANCEL_AMT);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCFTLAM.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCFTLAM = ");
            CEP.TRC(SCCGWA, BPCFTLAM);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
