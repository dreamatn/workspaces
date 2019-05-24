package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPCKRG {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_P_INQ_ORG_REL = "BP-P-INQ-ORG-REL    ";
    int WS_CUR_BR = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPCPQORR BPCPQORR = new BPCPQORR();
    SCCGWA SCCGWA;
    BPCPCKRG BPCPCKRG;
    public void MP(SCCGWA SCCGWA, BPCPCKRG BPCPCKRG) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPCKRG = BPCPCKRG;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPCKRG return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        BPCPCKRG.RC.RC_CODE = 0;
        BPCPCKRG.FLAG = 'N';
        if (BPCPCKRG.BNK.equalsIgnoreCase("0")) {
            BPCPCKRG.BNK = SCCGWA.COMM_AREA.TR_BANK;
        }
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B10_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B20_JUDGE_RELATION();
        if (pgmRtn) return;
    }
    public void B10_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCPCKRG.BR1 == 0 
            || BPCPCKRG.BR2 == 0 
            || BPCPCKRG.REL_TYPE.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCPCKRG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCPCKRG.BR1);
        CEP.TRC(SCCGWA, BPCPCKRG.BR2);
        CEP.TRC(SCCGWA, BPCPCKRG.REL_TYPE);
    }
    public void B20_JUDGE_RELATION() throws IOException,SQLException,Exception {
        B21_JUDGE_SAME_BR();
        if (pgmRtn) return;
        B22_JUDGE_RELATION();
        if (pgmRtn) return;
    }
    public void B21_JUDGE_SAME_BR() throws IOException,SQLException,Exception {
        if (BPCPCKRG.BR1 == BPCPCKRG.BR2) {
            IBS.init(SCCGWA, BPCPQORR);
            BPCPQORR.BNK = BPCPCKRG.BNK;
            BPCPQORR.BR = BPCPCKRG.BR1;
            BPCPQORR.TYP = BPCPCKRG.REL_TYPE;
            S00_CALL_BPZPQORR();
            if (pgmRtn) return;
            BPCPCKRG.FLAG = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B22_JUDGE_RELATION() throws IOException,SQLException,Exception {
        WS_CUR_BR = BPCPCKRG.BR1;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORR.RC);
        do {
            IBS.init(SCCGWA, BPCPQORR);
            BPCPQORR.BNK = BPCPCKRG.BNK;
            BPCPQORR.BR = WS_CUR_BR;
            BPCPQORR.TYP = BPCPCKRG.REL_TYPE;
            S00_CALL_BPZPQORR();
            if (pgmRtn) return;
            if (BPCPQORR.RC.RC_CODE == 0) {
                CEP.TRC(SCCGWA, BPCPQORR.REL_BR);
                if (BPCPQORR.REL_BR == BPCPCKRG.BR2) {
                    BPCPCKRG.FLAG = 'Y';
                }
                WS_CUR_BR = BPCPQORR.REL_BR;
            }
        } while (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_ORGREL_NOTFND) 
            && BPCPCKRG.FLAG != 'Y');
        if (BPCPCKRG.FLAG != 'Y') {
            WS_CUR_BR = BPCPCKRG.BR2;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORR.RC);
            do {
                IBS.init(SCCGWA, BPCPQORR);
                BPCPQORR.BNK = BPCPCKRG.BNK;
                BPCPQORR.BR = WS_CUR_BR;
                BPCPQORR.TYP = BPCPCKRG.REL_TYPE;
                S00_CALL_BPZPQORR();
                if (pgmRtn) return;
                if (BPCPQORR.RC.RC_CODE == 0) {
                    if (BPCPQORR.REL_BR == BPCPCKRG.BR1) {
                        BPCPCKRG.FLAG = 'Y';
                    }
                    WS_CUR_BR = BPCPQORR.REL_BR;
                }
            } while (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_ORGREL_NOTFND) 
                && BPCPCKRG.FLAG != 'Y');
        }
    }
    public void S00_CALL_BPZPQORR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG_REL, BPCPQORR);
        CEP.TRC(SCCGWA, BPCPQORR.RC);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCPQORR.RC);
        if (BPCPQORR.RC.RC_CODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_ORGREL_NOTFND)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPCKRG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPCKRG.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPCKRG = ");
            CEP.TRC(SCCGWA, BPCPCKRG);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
