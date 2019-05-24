package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPORLO {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    String CPN_ORGM_READ = "BP-R-BRW-ORGM       ";
    BPZPORLO_WS_ORGM_KEY WS_ORGM_KEY = new BPZPORLO_WS_ORGM_KEY();
    short WS_I = 0;
    char WS_JUDGE_OVER_FLG = 'N';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCRORGM BPCRORGM = new BPCRORGM();
    BPRORGM BPRPORGM = new BPRORGM();
    SCCGWA SCCGWA;
    BPCPORLO BPCPORLO;
    public void MP(SCCGWA SCCGWA, BPCPORLO BPCPORLO) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPORLO = BPCPORLO;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPORLO return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        BPCPORLO.RC.RC_CODE = 0;
        BPCPORLO.FLAG = 'N';
        if (BPCPORLO.BNK.equalsIgnoreCase("0")) {
            BPCPORLO.BNK = SCCGWA.COMM_AREA.TR_BANK;
        }
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B10_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B20_FIND_UNDERLING();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B30_OUTPUT_SUB_BR_CN();
            if (pgmRtn) return;
        }
    }
    public void B10_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCPORLO.BR == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCPORLO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B20_FIND_UNDERLING() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPORGM);
        IBS.init(SCCGWA, BPCRORGM);
        BPRPORGM.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        WS_ORGM_KEY.WS_ORGM_BNK = BPCPORLO.BNK;
        BPRPORGM.KEY.BR = WS_ORGM_KEY.WS_ORGM_BR;
        CEP.TRC(SCCGWA, BPRPORGM.KEY.BR);
        BPCRORGM.INFO.FUNC = 'S';
        BPCRORGM.INFO.POINTER = BPRPORGM;
        BPCRORGM.INFO.LEN = 1252;
        S000_CALL_BPZRORGM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "20140708");
        CEP.TRC(SCCGWA, "20140709");
        BPCRORGM.RETURN_INFO = ' ';
        BPCRORGM.INFO.FUNC = 'N';
        BPCRORGM.INFO.POINTER = BPRPORGM;
        BPCRORGM.INFO.LEN = 1252;
        S000_CALL_BPZRORGM();
        if (pgmRtn) return;
        if (BPCRORGM.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_NOTFND, BPCPORLO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_ORGM_KEY.WS_ORGM_BR = BPRPORGM.KEY.BR;
        while (BPCRORGM.RETURN_INFO != 'N' 
            && WS_ORGM_KEY.WS_ORGM_BNK.equalsIgnoreCase(BPCPORLO.BNK) 
            && BPCPORLO.FLAG != 'Y') {
            if (BPRPORGM.SUPR_BR != WS_ORGM_KEY.WS_ORGM_BR 
                && BPRPORGM.SUPR_BR == BPCPORLO.BR) {
                BPCPORLO.FLAG = 'Y';
                if (BPRPORGM.LVL > BPCPORLO.UNDER_LVL) {
                    BPCPORLO.UNDER_LVL = BPRPORGM.LVL;
                }
            }
            BPCRORGM.RETURN_INFO = ' ';
            BPCRORGM.INFO.FUNC = 'N';
            BPCRORGM.INFO.POINTER = BPRPORGM;
            BPCRORGM.INFO.LEN = 1252;
            S000_CALL_BPZRORGM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "20140710");
            CEP.TRC(SCCGWA, BPRPORGM.KEY.BR);
            WS_ORGM_KEY.WS_ORGM_BR = BPRPORGM.KEY.BR;
        }
        BPCRORGM.RETURN_INFO = ' ';
        BPCRORGM.INFO.FUNC = 'E';
        BPCRORGM.INFO.POINTER = BPRPORGM;
        BPCRORGM.INFO.LEN = 1252;
        S000_CALL_BPZRORGM();
        if (pgmRtn) return;
    }
    public void B30_OUTPUT_SUB_BR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BNK = BPCPORLO.BNK;
        BPCPQORG.BR = BPCPORLO.BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQORG.LVL);
        if (BPCPQORG.LVL == '6') {
            B30_1_OUTPUT_PROCESS_CN();
            if (pgmRtn) return;
        } else if (BPCPQORG.LVL == '4') {
            B30_2_OUTPUT_PROCESS_CN();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B30_1_OUTPUT_PROCESS_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPORGM);
        IBS.init(SCCGWA, BPCRORGM);
        IBS.init(SCCGWA, WS_ORGM_KEY);
        BPRPORGM.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        WS_ORGM_KEY.WS_ORGM_BNK = BPCPORLO.BNK;
        BPRPORGM.KEY.BR = WS_ORGM_KEY.WS_ORGM_BR;
        CEP.TRC(SCCGWA, BPCPORLO.LAST_BR);
        CEP.TRC(SCCGWA, BPRPORGM.KEY.BR);
        WS_I = 1;
        if (BPCPORLO.LAST_BR != 0) {
            WS_ORGM_KEY.WS_ORGM_BR = BPCPORLO.LAST_BR;
            BPRPORGM.KEY.BR = WS_ORGM_KEY.WS_ORGM_BR;
            WS_I += 1;
        }
        CEP.TRC(SCCGWA, BPRPORGM.KEY.BR);
        CEP.TRC(SCCGWA, WS_I);
        BPCRORGM.INFO.FUNC = 'S';
        BPCRORGM.INFO.POINTER = BPRPORGM;
        BPCRORGM.INFO.LEN = 1252;
        S000_CALL_BPZRORGM();
        if (pgmRtn) return;
        while (WS_I != 0) {
            BPCRORGM.RETURN_INFO = ' ';
            BPCRORGM.INFO.FUNC = 'N';
            BPCRORGM.INFO.POINTER = BPRPORGM;
            BPCRORGM.INFO.LEN = 1252;
            S000_CALL_BPZRORGM();
            if (pgmRtn) return;
            WS_I = (short) (WS_I - 1);
        }
        WS_ORGM_KEY.WS_ORGM_BR = BPRPORGM.KEY.BR;
        BPCPORLO.SUB_NUM = 0;
        BPCPORLO.LAST_BR = 0;
        BPCPORLO.NEXT_CALL_FLG = 'N';
        while (BPCRORGM.RETURN_INFO != 'N' 
            && WS_ORGM_KEY.WS_ORGM_BNK.equalsIgnoreCase(BPCPORLO.BNK) 
            && BPCPORLO.SUB_NUM < 100) {
            CEP.TRC(SCCGWA, BPRPORGM.BRANCH_BR);
            CEP.TRC(SCCGWA, BPCPORLO.BR);
            CEP.TRC(SCCGWA, WS_ORGM_KEY.WS_ORGM_BR);
            CEP.TRC(SCCGWA, BPRPORGM.SUPR_BR);
            CEP.TRC(SCCGWA, BPRPORGM.ATTR);
            if (BPRPORGM.SUPR_BR != WS_ORGM_KEY.WS_ORGM_BR 
                && BPRPORGM.BRANCH_BR == BPCPORLO.BR 
                && BPRPORGM.ATTR != '0') {
                BPCPORLO.SUB_NUM = (short) (BPCPORLO.SUB_NUM + 1);
                BPCPORLO.SUB_BR_DATA[BPCPORLO.SUB_NUM-1].SUB_BR = WS_ORGM_KEY.WS_ORGM_BR;
                BPCPORLO.SUB_BR_DATA[BPCPORLO.SUB_NUM-1].ATTR = BPRPORGM.ATTR;
                BPCPORLO.SUB_BR_DATA[BPCPORLO.SUB_NUM-1].TYP = BPRPORGM.TYPE;
                BPCPORLO.SUB_BR_DATA[BPCPORLO.SUB_NUM-1].LVL = BPRPORGM.LVL;
                CEP.TRC(SCCGWA, BPCPORLO.SUB_BR_DATA[BPCPORLO.SUB_NUM-1].ATTR);
                CEP.TRC(SCCGWA, BPCPORLO.SUB_BR_DATA[BPCPORLO.SUB_NUM-1].TYP);
                CEP.TRC(SCCGWA, BPCPORLO.SUB_BR_DATA[BPCPORLO.SUB_NUM-1].LVL);
            }
            if (BPCPORLO.SUB_NUM == 100) {
                BPCPORLO.LAST_BR = WS_ORGM_KEY.WS_ORGM_BR;
            }
            BPCRORGM.RETURN_INFO = ' ';
            BPCRORGM.INFO.FUNC = 'N';
            BPCRORGM.INFO.POINTER = BPRPORGM;
            BPCRORGM.INFO.LEN = 1252;
            S000_CALL_BPZRORGM_CN();
            if (pgmRtn) return;
            WS_ORGM_KEY.WS_ORGM_BR = BPRPORGM.KEY.BR;
            if (BPCPORLO.SUB_NUM == 100 
                && BPCRORGM.RETURN_INFO == 'N') {
                BPCPORLO.NEXT_CALL_FLG = 'Y';
            }
            CEP.TRC(SCCGWA, BPCRORGM.RETURN_INFO);
        }
        CEP.TRC(SCCGWA, BPCRORGM.RETURN_INFO);
        CEP.TRC(SCCGWA, BPCPORLO.SUB_NUM);
        BPCRORGM.RETURN_INFO = ' ';
        BPCRORGM.INFO.FUNC = 'E';
        BPCRORGM.INFO.POINTER = BPRPORGM;
        BPCRORGM.INFO.LEN = 1252;
        S000_CALL_BPZRORGM();
        if (pgmRtn) return;
    }
    public void B30_2_OUTPUT_PROCESS_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPORGM);
        IBS.init(SCCGWA, BPCRORGM);
        IBS.init(SCCGWA, WS_ORGM_KEY);
        BPRPORGM.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        WS_ORGM_KEY.WS_ORGM_BNK = BPCPORLO.BNK;
        BPRPORGM.KEY.BR = WS_ORGM_KEY.WS_ORGM_BR;
        CEP.TRC(SCCGWA, BPCPORLO.LAST_BR);
        CEP.TRC(SCCGWA, BPRPORGM.KEY.BR);
        WS_I = 1;
        if (BPCPORLO.LAST_BR != 0) {
            WS_ORGM_KEY.WS_ORGM_BR = BPCPORLO.LAST_BR;
            BPRPORGM.KEY.BR = WS_ORGM_KEY.WS_ORGM_BR;
            WS_I += 1;
        }
        CEP.TRC(SCCGWA, WS_I);
        CEP.TRC(SCCGWA, BPRPORGM.KEY.BR);
        BPCRORGM.INFO.FUNC = 'S';
        BPCRORGM.INFO.POINTER = BPRPORGM;
        BPCRORGM.INFO.LEN = 1252;
        S000_CALL_BPZRORGM();
        if (pgmRtn) return;
        while (WS_I != 0) {
            BPCRORGM.RETURN_INFO = ' ';
            BPCRORGM.INFO.FUNC = 'N';
            BPCRORGM.INFO.POINTER = BPRPORGM;
            BPCRORGM.INFO.LEN = 1252;
            S000_CALL_BPZRORGM();
            if (pgmRtn) return;
            WS_I = (short) (WS_I - 1);
        }
        WS_ORGM_KEY.WS_ORGM_BR = BPRPORGM.KEY.BR;
        BPCPORLO.SUB_NUM = 0;
        BPCPORLO.LAST_BR = 0;
        BPCPORLO.NEXT_CALL_FLG = 'N';
        while (BPCRORGM.RETURN_INFO != 'N' 
            && WS_ORGM_KEY.WS_ORGM_BNK.equalsIgnoreCase(BPCPORLO.BNK) 
            && BPCPORLO.SUB_NUM < 100) {
            CEP.TRC(SCCGWA, BPCPORLO.BR);
            CEP.TRC(SCCGWA, BPRPORGM.SUPR_BR);
            CEP.TRC(SCCGWA, WS_ORGM_KEY.WS_ORGM_BR);
            CEP.TRC(SCCGWA, BPRPORGM.ATTR);
            if (BPRPORGM.SUPR_BR != WS_ORGM_KEY.WS_ORGM_BR 
                && BPRPORGM.SUPR_BR == BPCPORLO.BR) {
                BPCPORLO.SUB_NUM = (short) (BPCPORLO.SUB_NUM + 1);
                BPCPORLO.SUB_BR_DATA[BPCPORLO.SUB_NUM-1].SUB_BR = WS_ORGM_KEY.WS_ORGM_BR;
                BPCPORLO.SUB_BR_DATA[BPCPORLO.SUB_NUM-1].ATTR = BPRPORGM.ATTR;
                BPCPORLO.SUB_BR_DATA[BPCPORLO.SUB_NUM-1].TYP = BPRPORGM.TYPE;
                BPCPORLO.SUB_BR_DATA[BPCPORLO.SUB_NUM-1].LVL = BPRPORGM.LVL;
                CEP.TRC(SCCGWA, BPCPORLO.SUB_BR_DATA[BPCPORLO.SUB_NUM-1].ATTR);
                CEP.TRC(SCCGWA, BPCPORLO.SUB_BR_DATA[BPCPORLO.SUB_NUM-1].TYP);
                CEP.TRC(SCCGWA, BPCPORLO.SUB_BR_DATA[BPCPORLO.SUB_NUM-1].LVL);
            }
            if (BPCPORLO.SUB_NUM == 100) {
                BPCPORLO.LAST_BR = WS_ORGM_KEY.WS_ORGM_BR;
            }
            BPCRORGM.RETURN_INFO = ' ';
            BPCRORGM.INFO.FUNC = 'N';
            BPCRORGM.INFO.POINTER = BPRPORGM;
            BPCRORGM.INFO.LEN = 1252;
            S000_CALL_BPZRORGM_CN();
            if (pgmRtn) return;
            WS_ORGM_KEY.WS_ORGM_BR = BPRPORGM.KEY.BR;
            if (BPCPORLO.SUB_NUM == 100 
                && BPCRORGM.RETURN_INFO == 'N') {
                BPCPORLO.NEXT_CALL_FLG = 'Y';
            }
            CEP.TRC(SCCGWA, BPCRORGM.RETURN_INFO);
        }
        CEP.TRC(SCCGWA, BPCPORLO.SUB_NUM);
        BPCRORGM.RETURN_INFO = ' ';
        BPCRORGM.INFO.FUNC = 'E';
        BPCRORGM.INFO.POINTER = BPRPORGM;
        BPCRORGM.INFO.LEN = 1252;
        S000_CALL_BPZRORGM();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZRORGM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_ORGM_READ, BPCRORGM);
        if (BPCRORGM.RC.RC_CODE != 0 
            && BPCRORGM.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_NOTFND, BPCPORLO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRORGM_CN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_ORGM_READ, BPCRORGM);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC.RC_CODE);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPORLO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPORLO.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPORLO = ");
            CEP.TRC(SCCGWA, BPCPORLO);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
