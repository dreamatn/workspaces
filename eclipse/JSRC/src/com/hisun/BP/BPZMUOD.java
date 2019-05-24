package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZMUOD {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_MMO = "BP";
    String K_PGM_NAME = "BPZMUOD";
    String K_CALL_BPZFTLRQ = "BP-F-TLR-INF-QUERY ";
    String K_CALL_BPZRUOD = "BP-U-R-UOD         ";
    String K_CALL_BPZPQBNK = "BP-P-QUERY-BANK    ";
    String K_CALL_BPZFTLAM = "BP-F-TLR-ACCU-MGM   ";
    String WS_ERR_MSG = " ";
    short WS_CNT = 0;
    short WS_I = 0;
    double WS_TOT_AMT = 0;
    char WS_TBL_BANK_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    BPCOUOD BPCOUOD = new BPCOUOD();
    BPCOSUOD BPCOSUOD = new BPCOSUOD();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCFTLAM BPCFTLAM = new BPCFTLAM();
    BPCPQBNK BPCPQBNK = new BPCPQBNK();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPCFBTAQ BPCFBTAQ = new BPCFBTAQ();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    BPCOMUOD BPCOMUOD;
    public void MP(SCCGWA SCCGWA, BPCOMUOD BPCOMUOD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOMUOD = BPCOMUOD;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAINTAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZMUOD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCOUOD);
        IBS.init(SCCGWA, BPCOSUOD);
        IBS.init(SCCGWA, BPCFTLRQ);
        IBS.init(SCCGWA, BPCFTLAM);
        IBS.init(SCCGWA, BPCPQBNK);
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCOMUOD.RC);
    }
    public void B00_MAINTAIN_PROCESS() throws IOException,SQLException,Exception {
        if (BPCOMUOD.INPUT_DATA.ATH_TLR.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_ATH_TLR_SPACE, BPCOMUOD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        B010_GET_TILLER_INFORMATION();
        if (pgmRtn) return;
        B020_GET_BR_UOD_LMT();
        if (pgmRtn) return;
        B030_GET_LOC_CCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCOSUOD.VAL.UOD_AMT[10-1].UOD_LMT);
        CEP.TRC(SCCGWA, SCCGBPA_BP_AREA.COM_AREA.UOD_AMT);
        if (SCCGBPA_BP_AREA.COM_AREA.UOD_AMT != 0) {
            B040_UPDATE_TILLER_UOD_AMT();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCFTLAM.TOT_AMT);
        CEP.TRC(SCCGWA, BPCOMUOD.RC);
    }
    public void B010_GET_TILLER_INFORMATION() throws IOException,SQLException,Exception {
        BPCFTLRQ.INFO.TLR = BPCOMUOD.INPUT_DATA.ATH_TLR;
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
    }
    public void B020_GET_BR_UOD_LMT() throws IOException,SQLException,Exception {
        BPCOSUOD.KEY.UOD_BR = BPCFTLRQ.INFO.TLR_BR;
        BPCOUOD.FUNC = 'I';
        BPCOUOD.POINTER = BPCOSUOD;
        S000_CALL_BPZRUOD();
        if (pgmRtn) return;
    }
    public void B030_GET_LOC_CCY() throws IOException,SQLException,Exception {
        BPCPQBNK.DATA_INFO.BNK = BPCOSUOD.KEY.UOD_BK;
        CEP.TRC(SCCGWA, BPCPQBNK.DATA_INFO.BNK);
        S000_CALL_BPZPQBNK();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_TILLER_UOD_AMT() throws IOException,SQLException,Exception {
        R000_TRANS_DATA_PARAMETER();
        if (pgmRtn) return;
        S000_CALL_BPZFTLAM();
        if (pgmRtn) return;
        B041_QUERY_TLR_TOT_AMT();
        if (pgmRtn) return;
        T000_CHECK_UOD_LMT();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CANCEL_IND);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            BPCFTLAM.OP_CODE = 'D';
        } else {
            BPCFTLAM.OP_CODE = 'A';
        }
        BPCFTLAM.TLR = BPCOMUOD.INPUT_DATA.ATH_TLR;
        BPCFTLAM.CCY = BPCPQBNK.DATA_INFO.LOC_CCY1;
        CEP.TRC(SCCGWA, BPCFTLAM.CCY);
        BPCFTLAM.ACCU_TYP = "20";
        BPCFTLAM.TX_COUNT = 1;
        BPCFTLAM.AMT = SCCGBPA_BP_AREA.COM_AREA.UOD_AMT;
        CEP.TRC(SCCGWA, BPCFTLAM.AMT);
    }
    public void B041_QUERY_TLR_TOT_AMT() throws IOException,SQLException,Exception {
        WS_TOT_AMT = BPCFTLAM.TOT_AMT;
        IBS.init(SCCGWA, BPCFBTAQ);
        BPCFBTAQ.OP_CODE = 'T';
        BPCFBTAQ.TLR = BPCOMUOD.INPUT_DATA.ATH_TLR;
        S000_CALL_BPZFBTAQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_TOT_AMT);
        for (WS_I = 1; WS_I <= 30; WS_I += 1) {
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, BPCFBTAQ.ARRAY[WS_I-1].CCY);
            CEP.TRC(SCCGWA, BPCFBTAQ.ARRAY[WS_I-1].ACCU_TYP);
            CEP.TRC(SCCGWA, BPCFBTAQ.ARRAY[WS_I-1].NORMAL_AMT);
            CEP.TRC(SCCGWA, BPCFBTAQ.ARRAY[WS_I-1].REVERSAL_AMT);
            CEP.TRC(SCCGWA, BPCFBTAQ.ARRAY[WS_I-1].CANCEL_AMT);
            if (BPCFBTAQ.ARRAY[WS_I-1].CCY.equalsIgnoreCase(BPCPQBNK.DATA_INFO.LOC_CCY1) 
                && BPCFBTAQ.ARRAY[WS_I-1].ACCU_TYP.equalsIgnoreCase("20")) {
                if (BPCFBTAQ.ARRAY[WS_I-1].NORMAL_AMT == WS_TOT_AMT) {
                    WS_TOT_AMT = WS_TOT_AMT - BPCFBTAQ.ARRAY[WS_I-1].REVERSAL_AMT;
                    CEP.TRC(SCCGWA, WS_TOT_AMT);
                    WS_TOT_AMT = WS_TOT_AMT - BPCFBTAQ.ARRAY[WS_I-1].CANCEL_AMT;
                    CEP.TRC(SCCGWA, WS_TOT_AMT);
                }
            }
        }
    }
    public void T000_CHECK_UOD_LMT() throws IOException,SQLException,Exception {
        if (BPCFTLRQ.INFO.ATH_LVL == ' ') WS_CNT = 0;
        else WS_CNT = Short.parseShort(""+BPCFTLRQ.INFO.ATH_LVL);
        WS_CNT = (short) (WS_CNT + 1);
        CEP.TRC(SCCGWA, BPCOSUOD.VAL.UOD_AMT[WS_CNT-1].UOD_LMT);
        CEP.TRC(SCCGWA, BPCFTLAM.TOT_AMT);
        if (WS_TOT_AMT > BPCOSUOD.VAL.UOD_AMT[WS_CNT-1].UOD_LMT) {
            BPCOMUOD.RC.RC_MMO = K_MMO;
            BPCOMUOD.RC.RC_CODE = 9999;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRUOD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CALL_BPZRUOD, BPCOUOD);
        if (BPCOUOD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOUOD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCOMUOD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQBNK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPQBNK.DATA_INFO.BNK);
        CEP.TRC(SCCGWA, BPCPQBNK.RC);
        IBS.CALLCPN(SCCGWA, K_CALL_BPZPQBNK, BPCPQBNK);
        CEP.TRC(SCCGWA, BPCPQBNK.RC);
        if (BPCPQBNK.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQBNK.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCOMUOD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CALL_BPZFTLRQ, BPCFTLRQ);
        CEP.TRC(SCCGWA, BPCFTLRQ.RC);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCOMUOD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFTLAM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CALL_BPZFTLAM, BPCFTLAM);
        CEP.TRC(SCCGWA, BPCFTLAM.RC);
        if (BPCFTLAM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFTLAM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCOMUOD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFBTAQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-ACCU-QUERY", BPCFBTAQ);
        CEP.TRC(SCCGWA, BPCFBTAQ.RC);
        if (BPCFBTAQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFBTAQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCOMUOD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCOMUOD.RC);
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCOMUOD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCOMUOD = ");
            CEP.TRC(SCCGWA, BPCOMUOD);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
