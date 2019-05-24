package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPRGST {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    char WS_BR1_LVL = ' ';
    char WS_BR2_LVL = ' ';
    int WS_SUPR_BR1 = 0;
    int WS_SUPR_BR2 = 0;
    int WS_SUPR_SUPBR = 0;
    char WS_SUPR_BR1_LVL = ' ';
    char WS_SUPR_BR2_LVL = ' ';
    int WS_STAR_BR1 = 0;
    int WS_STAR_BR2 = 0;
    char WS_STAR_BR1_LVL = ' ';
    char WS_STAR_BR2_LVL = ' ';
    int WS_CUR_BR = 0;
    char WS_CUR_BR_LVL = ' ';
    int WS_MAX_BR = 0;
    char WS_MAX_BR_LVL = ' ';
    int WS_SUPR_BR = 0;
    char WS_SUPR_BR_LVL = ' ';
    int WS_BRANCH_BR1 = 0;
    int WS_BRANCH_BR2 = 0;
    char WS_JUDGE_OVER_FLG = 'N';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    BPCPRGST BPCPRGST;
    public void MP(SCCGWA SCCGWA, BPCPRGST BPCPRGST) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPRGST = BPCPRGST;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPRGST return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        BPCPRGST.RC.RC_CODE = 0;
        BPCPRGST.LVL_RELATION = ' ';
        BPCPRGST.FLAG = 'N';
        WS_JUDGE_OVER_FLG = 'N';
        if (BPCPRGST.BNK.equalsIgnoreCase("0")) {
            BPCPRGST.BNK = SCCGWA.COMM_AREA.TR_BANK;
        }
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B10_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B20_JUDGE_RELATION();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B30_JUDGE_BRANCH_CN();
            if (pgmRtn) return;
        }
    }
    public void B10_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCPRGST.BR1 == 0 
            || BPCPRGST.BR2 == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCPRGST.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCPRGST.BR1 == BPCPRGST.BR2) {
            CEP.TRC(SCCGWA, BPCPRGST.BR1);
            CEP.TRC(SCCGWA, BPCPRGST.BR2);
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_SAME_BR_PROHIBITED, BPCPRGST.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B20_JUDGE_RELATION() throws IOException,SQLException,Exception {
        B21_GET_STAR_BR();
        if (pgmRtn) return;
        B22_JUDGE_LVL();
        if (pgmRtn) return;
    }
    public void B21_GET_STAR_BR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BNK = BPCPRGST.BNK;
        BPCPQORG.BR = BPCPRGST.BR1;
        S00_CALL_BPZPQORG();
        if (pgmRtn) return;
        if (BPCPQORG.RC.RC_CODE != 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_ORG_NOTFND, BPCPRGST.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_BR1_LVL = BPCPQORG.LVL;
        WS_SUPR_BR1 = BPCPQORG.SUPR_BR;
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BNK = BPCPRGST.BNK;
        BPCPQORG.BR = BPCPRGST.BR2;
        S00_CALL_BPZPQORG();
        if (pgmRtn) return;
        if (BPCPQORG.RC.RC_CODE != 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_ORG_NOTFND, BPCPRGST.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_BR2_LVL = BPCPQORG.LVL;
        WS_SUPR_BR2 = BPCPQORG.SUPR_BR;
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BNK = BPCPRGST.BNK;
        BPCPQORG.BR = WS_SUPR_BR1;
        S00_CALL_BPZPQORG();
        if (pgmRtn) return;
        if (BPCPQORG.RC.RC_CODE != 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_ORG_NOTFND, BPCPRGST.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_SUPR_BR1_LVL = BPCPQORG.LVL;
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BNK = BPCPRGST.BNK;
        BPCPQORG.BR = WS_SUPR_BR2;
        S00_CALL_BPZPQORG();
        if (pgmRtn) return;
        if (BPCPQORG.RC.RC_CODE != 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_ORG_NOTFND, BPCPRGST.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_SUPR_BR2_LVL = BPCPQORG.LVL;
        if (WS_SUPR_BR1_LVL == WS_BR1_LVL) {
            WS_STAR_BR1 = WS_SUPR_BR1;
            WS_STAR_BR1_LVL = WS_SUPR_BR1_LVL;
        } else {
            WS_STAR_BR1 = BPCPRGST.BR1;
            WS_STAR_BR1_LVL = WS_BR1_LVL;
        }
        if (WS_SUPR_BR2_LVL == WS_BR2_LVL) {
            WS_STAR_BR2 = WS_SUPR_BR2;
            WS_STAR_BR2_LVL = WS_SUPR_BR2_LVL;
        } else {
            WS_STAR_BR2 = BPCPRGST.BR2;
            WS_STAR_BR2_LVL = WS_BR2_LVL;
        }
    }
    public void B22_JUDGE_LVL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_STAR_BR1_LVL);
        CEP.TRC(SCCGWA, WS_STAR_BR2_LVL);
        if (WS_STAR_BR1_LVL == WS_STAR_BR2_LVL) {
            if (WS_SUPR_BR1 == BPCPRGST.BR2 
                || WS_SUPR_BR2 == BPCPRGST.BR1) {
                if (WS_SUPR_BR1 == BPCPRGST.BR2) {
                    BPCPRGST.FLAG = 'Y';
                    BPCPRGST.LVL_RELATION = 'C';
                } else {
                    BPCPRGST.FLAG = 'Y';
                    BPCPRGST.LVL_RELATION = 'A';
                }
            } else {
                CEP.TRC(SCCGWA, WS_SUPR_BR1);
                CEP.TRC(SCCGWA, WS_SUPR_BR2);
                if (WS_SUPR_BR1 == WS_SUPR_BR2) {
                    BPCPRGST.FLAG = 'Y';
                    BPCPRGST.LVL_RELATION = 'B';
                } else {
                    BPCPRGST.FLAG = 'N';
                }
            }
        } else {
            CEP.TRC(SCCGWA, WS_STAR_BR1_LVL);
            CEP.TRC(SCCGWA, WS_STAR_BR2_LVL);
            if (WS_STAR_BR1_LVL < WS_STAR_BR2_LVL) {
                WS_CUR_BR = WS_STAR_BR1;
                WS_MAX_BR = WS_STAR_BR2;
                WS_MAX_BR_LVL = WS_STAR_BR2_LVL;
            } else {
                WS_CUR_BR = WS_STAR_BR2;
                WS_MAX_BR = WS_STAR_BR1;
                WS_MAX_BR_LVL = WS_STAR_BR1_LVL;
            }
            CEP.TRC(SCCGWA, WS_CUR_BR);
            CEP.TRC(SCCGWA, WS_MAX_BR);
            CEP.TRC(SCCGWA, WS_MAX_BR_LVL);
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BNK = BPCPRGST.BNK;
            BPCPQORG.BR = WS_CUR_BR;
            S00_CALL_BPZPQORG();
            if (pgmRtn) return;
            if (BPCPQORG.RC.RC_CODE != 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_ORG_NOTFND, BPCPRGST.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            WS_SUPR_BR = BPCPQORG.SUPR_BR;
            CEP.TRC(SCCGWA, WS_SUPR_BR);
            do {
                WS_CUR_BR = WS_SUPR_BR;
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BNK = BPCPRGST.BNK;
                BPCPQORG.BR = WS_CUR_BR;
                S00_CALL_BPZPQORG();
                if (pgmRtn) return;
                if (BPCPQORG.RC.RC_CODE != 0) {
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_ORG_NOTFND, BPCPRGST.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                WS_SUPR_BR = BPCPQORG.SUPR_BR;
                WS_CUR_BR_LVL = BPCPQORG.LVL;
            } while (WS_SUPR_BR != WS_CUR_BR 
                && WS_CUR_BR_LVL <= WS_MAX_BR_LVL 
                && WS_CUR_BR_LVL != WS_MAX_BR_LVL 
                || WS_CUR_BR != WS_MAX_BR);
            CEP.TRC(SCCGWA, WS_CUR_BR_LVL);
            CEP.TRC(SCCGWA, WS_MAX_BR_LVL);
            CEP.TRC(SCCGWA, WS_CUR_BR);
            CEP.TRC(SCCGWA, WS_MAX_BR);
            if (WS_CUR_BR_LVL >= WS_MAX_BR_LVL) {
                if (WS_CUR_BR == WS_MAX_BR) {
                    if (WS_BR1_LVL > WS_BR2_LVL) {
                        BPCPRGST.FLAG = 'Y';
                        BPCPRGST.LVL_RELATION = 'A';
                    } else {
                        BPCPRGST.FLAG = 'Y';
                        BPCPRGST.LVL_RELATION = 'C';
                    }
                } else {
                    BPCPRGST.FLAG = 'N';
                }
            }
        }
        if (BPCPRGST.FLAG == 'N') {
            if (WS_BR1_LVL > WS_BR2_LVL) {
                BPCPRGST.LVL_RELATION = 'A';
            } else {
                if (WS_BR1_LVL < WS_BR2_LVL) {
                    BPCPRGST.LVL_RELATION = 'C';
                } else {
                    BPCPRGST.LVL_RELATION = 'B';
                }
            }
        }
        CEP.TRC(SCCGWA, BPCPRGST.BR1);
        CEP.TRC(SCCGWA, WS_SUPR_BR1);
        CEP.TRC(SCCGWA, WS_BR1_LVL);
        CEP.TRC(SCCGWA, BPCPRGST.BR2);
        CEP.TRC(SCCGWA, WS_SUPR_BR2);
        CEP.TRC(SCCGWA, WS_BR2_LVL);
        if (WS_SUPR_BR1 == WS_SUPR_BR2 
                && WS_BR1_LVL > WS_BR2_LVL) {
            BPCPRGST.FLAG = 'Y';
            BPCPRGST.LVL_RELATION = 'A';
        } else if (WS_SUPR_BR1 == WS_SUPR_BR2 
                && WS_BR1_LVL == WS_BR2_LVL) {
            BPCPRGST.FLAG = 'Y';
            BPCPRGST.LVL_RELATION = 'B';
        } else if (WS_SUPR_BR1 == WS_SUPR_BR2 
                && WS_BR1_LVL < WS_BR2_LVL) {
            BPCPRGST.FLAG = 'Y';
            BPCPRGST.LVL_RELATION = 'C';
        } else {
            while (BPCPQORG.SUPR_BR != WS_SUPR_BR2) {
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BNK = BPCPRGST.BNK;
                BPCPQORG.BR = WS_SUPR_BR2;
                S00_CALL_BPZPQORG();
                if (pgmRtn) return;
                if (BPCPQORG.RC.RC_CODE != 0) {
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_ORG_NOTFND, BPCPRGST.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                WS_BR2_LVL = BPCPQORG.LVL;
                WS_SUPR_BR2 = BPCPQORG.SUPR_BR;
                CEP.TRC(SCCGWA, WS_SUPR_BR2);
                CEP.TRC(SCCGWA, WS_BR2_LVL);
                CEP.TRC(SCCGWA, WS_SUPR_BR2);
                if (WS_SUPR_BR1 == WS_SUPR_BR2) {
                    BPCPRGST.FLAG = 'Y';
                    BPCPRGST.LVL_RELATION = 'A';
                }
            }
    }
    public void B30_JUDGE_BRANCH_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BNK = BPCPRGST.BNK;
        BPCPQORG.BR = BPCPRGST.BR1;
        S00_CALL_BPZPQORG();
        if (pgmRtn) return;
        WS_BRANCH_BR1 = BPCPQORG.BRANCH_BR;
        CEP.TRC(SCCGWA, WS_BRANCH_BR1);
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BNK = BPCPRGST.BNK;
        BPCPQORG.BR = BPCPRGST.BR2;
        S00_CALL_BPZPQORG();
        if (pgmRtn) return;
        WS_BRANCH_BR2 = BPCPQORG.BRANCH_BR;
        CEP.TRC(SCCGWA, WS_BRANCH_BR2);
        BPCPRGST.BRANCH_FLG = 'N';
        if (WS_BRANCH_BR1 == WS_BRANCH_BR2) {
            BPCPRGST.BRANCH_FLG = 'Y';
        }
    }
    public void S00_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPRGST.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPRGST.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPRGST = ");
            CEP.TRC(SCCGWA, BPCPRGST);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
