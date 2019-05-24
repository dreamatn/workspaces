package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPORDN {
    String JIBS_NumStr;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    int WS_BR1 = 0;
    int WS_BR2 = 0;
    int WS_CUR_BR = 0;
    int WS_MAX_BR = 0;
    char WS_BR1_LVL = ' ';
    char WS_BR2_LVL = ' ';
    short WS_IDX = 0;
    short WS_LVL = 0;
    int WS_CNT = 0;
    BPZPORDN_WS_UP_BR_INFO WS_UP_BR_INFO = new BPZPORDN_WS_UP_BR_INFO();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    BPCPORDN BPCPORDN;
    public void MP(SCCGWA SCCGWA, BPCPORDN BPCPORDN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPORDN = BPCPORDN;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPORDN return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        BPCPORDN.RC.RC_CODE = 0;
        BPCPORDN.FLAG = ' ';
        if (BPCPORDN.BNK.equalsIgnoreCase("0")) {
            BPCPORDN.BNK = SCCGWA.COMM_AREA.TR_BANK;
        }
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B10_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B20_JUDGE_RELATION();
        if (pgmRtn) return;
    }
    public void B10_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCPORDN.BR1 == 0 
            || BPCPORDN.BR2 == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCPORDN.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B20_JUDGE_RELATION() throws IOException,SQLException,Exception {
        B21_JUDGE_SAME_BR();
        if (pgmRtn) return;
        B22_SAVE_UP_BR();
        if (pgmRtn) return;
        B23_JUDGE_UP_SAME();
        if (pgmRtn) return;
    }
    public void B21_JUDGE_SAME_BR() throws IOException,SQLException,Exception {
        if (BPCPORDN.BR1 == BPCPORDN.BR2) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BNK = BPCPORDN.BNK;
            BPCPQORG.BR = BPCPORDN.BR1;
            S00_CALL_BPZPQORG();
            if (pgmRtn) return;
            BPCPORDN.FLAG = '3';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B22_SAVE_UP_BR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BNK = BPCPORDN.BNK;
        BPCPQORG.BR = BPCPORDN.BR1;
        S00_CALL_BPZPQORG();
        if (pgmRtn) return;
        WS_BR1 = BPCPQORG.BR;
        WS_BR1_LVL = BPCPQORG.LVL;
        BPCPQORG.BR = BPCPQORG.SUPR_BR;
        S00_CALL_BPZPQORG();
        if (pgmRtn) return;
        if (BPCPQORG.LVL == WS_BR1_LVL) {
            WS_BR1 = BPCPQORG.BR;
        }
        CEP.TRC(SCCGWA, WS_BR1);
        CEP.TRC(SCCGWA, WS_BR1_LVL);
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BNK = BPCPORDN.BNK;
        BPCPQORG.BR = BPCPORDN.BR2;
        S00_CALL_BPZPQORG();
        if (pgmRtn) return;
        WS_BR2 = BPCPQORG.BR;
        WS_BR2_LVL = BPCPQORG.LVL;
        BPCPQORG.BR = BPCPQORG.SUPR_BR;
        S00_CALL_BPZPQORG();
        if (pgmRtn) return;
        if (BPCPQORG.LVL == WS_BR2_LVL) {
            WS_BR2 = BPCPQORG.BR;
        }
        CEP.TRC(SCCGWA, WS_BR2);
        CEP.TRC(SCCGWA, WS_BR2_LVL);
        if (WS_BR1_LVL > WS_BR2_LVL) {
            WS_CUR_BR = WS_BR2;
            WS_MAX_BR = WS_BR1;
        } else {
            WS_CUR_BR = WS_BR1;
            WS_MAX_BR = WS_BR2;
        }
        CEP.TRC(SCCGWA, WS_CUR_BR);
        CEP.TRC(SCCGWA, WS_MAX_BR);
        do {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BNK = BPCPORDN.BNK;
            BPCPQORG.BR = WS_CUR_BR;
            CEP.TRC(SCCGWA, WS_CUR_BR);
            S00_CALL_BPZPQORG();
            if (pgmRtn) return;
            WS_CNT += 1;
            if (BPCPQORG.SUPR_BR == WS_MAX_BR) {
                BPCPORDN.FLAG = '1';
                WS_IDX = (short) WS_CNT;
                JIBS_NumStr = "" + WS_IDX;
                BPCPORDN.LVL = JIBS_NumStr.charAt(0);
                Z_RET();
                if (pgmRtn) return;
            } else {
                WS_UP_BR_INFO.WS_UP_BR[WS_CNT-1] = BPCPQORG.SUPR_BR;
                WS_CUR_BR = BPCPQORG.SUPR_BR;
            }
        } while (BPCPQORG.SUPR_BR != BPCPQORG.BR 
            && WS_CNT != 10);
    }
    public void B23_JUDGE_UP_SAME() throws IOException,SQLException,Exception {
        WS_CUR_BR = WS_MAX_BR;
        do {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BNK = BPCPORDN.BNK;
            BPCPQORG.BR = WS_CUR_BR;
            S00_CALL_BPZPQORG();
            if (pgmRtn) return;
            WS_LVL += 1;
            for (WS_IDX = 1; WS_IDX <= WS_CNT; WS_IDX += 1) {
                if (BPCPQORG.SUPR_BR == WS_UP_BR_INFO.WS_UP_BR[WS_IDX-1]) {
                    BPCPORDN.FLAG = '2';
                    if (WS_LVL > WS_IDX) {
                        JIBS_NumStr = "" + WS_LVL;
                        BPCPORDN.LVL = JIBS_NumStr.charAt(0);
                    } else {
                        JIBS_NumStr = "" + WS_IDX;
                        BPCPORDN.LVL = JIBS_NumStr.charAt(0);
                    }
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
            WS_CUR_BR = BPCPQORG.SUPR_BR;
        } while (BPCPQORG.SUPR_BR != BPCPQORG.BR);
    }
    public void S00_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPORDN.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPORDN.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPORDN = ");
            CEP.TRC(SCCGWA, BPCPORDN);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
