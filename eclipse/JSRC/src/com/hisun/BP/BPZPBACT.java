package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPBACT {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    int K_ACT_CNT = 100;
    String CPN_ORGM_READ = "BP-R-BRW-ORGM       ";
    BPZPBACT_WS_ORGM_CD WS_ORGM_CD = new BPZPBACT_WS_ORGM_CD();
    String WS_ORGM_BCH = " ";
    String WS_TR_BCH = " ";
    char WS_BRW_END_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCRORGM BPCRORGM = new BPCRORGM();
    BPRORGM BPRPORGM = new BPRORGM();
    SCCGWA SCCGWA;
    BPCPBACT BPCPBACT;
    public void MP(SCCGWA SCCGWA, BPCPBACT BPCPBACT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPBACT = BPCPBACT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPBACT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPBACT.RC);
        BPCPBACT.OUTPUT_DATA.END_FLG = 'N';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCPBACT.INPUT_DATA.FUCN == 'S') {
            B100_START_PROCESS();
            if (pgmRtn) return;
        } else if (BPCPBACT.INPUT_DATA.FUCN == 'N'
            || BPCPBACT.INPUT_DATA.FUCN == 'B') {
            B200_RDNXT_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FUNC_ERROR, BPCPBACT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B100_START_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPORGM);
        IBS.init(SCCGWA, BPCRORGM);
        BPCRORGM.INFO.FUNC = 'S';
        BPRPORGM.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        BPCRORGM.INFO.POINTER = BPRPORGM;
        BPCRORGM.INFO.LEN = 1252;
        S000_CALL_BPZRORGM();
        if (pgmRtn) return;
    }
    public void B200_RDNXT_PROCESS() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        WS_TR_BCH = JIBS_tmp_str[0].substring(0, 3);
        BPCPBACT.OUTPUT_DATA.ACT_CNT = 0;
        WS_BRW_END_FLG = 'N';
        while (BPCPBACT.OUTPUT_DATA.END_FLG != 'Y' 
            && WS_BRW_END_FLG != 'Y') {
            IBS.init(SCCGWA, BPCRORGM.RC);
            BPCRORGM.INFO.FUNC = 'N';
            BPCRORGM.INFO.POINTER = BPRPORGM;
            BPCRORGM.INFO.LEN = 1252;
            S000_CALL_BPZRORGM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPRPORGM.KEY.BR);
            if (BPCPBACT.OUTPUT_DATA.END_FLG != 'Y') {
                WS_ORGM_CD.WS_ORGM_BR = BPRPORGM.KEY.BR;
                JIBS_tmp_str[0] = "" + WS_ORGM_CD.WS_ORGM_BR;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                WS_ORGM_BCH = JIBS_tmp_str[0].substring(0, 3);
                BPCPBACT.OUTPUT_DATA.ACT_CNT += 1;
                BPCPBACT.OUTPUT_DATA.ACT[BPCPBACT.OUTPUT_DATA.ACT_CNT-1] = WS_ORGM_CD.WS_ORGM_BR;
                if (BPCPBACT.OUTPUT_DATA.ACT_CNT == K_ACT_CNT) {
                    WS_BRW_END_FLG = 'Y';
                }
            }
        }
        if (BPCPBACT.OUTPUT_DATA.END_FLG == 'Y') {
            IBS.init(SCCGWA, BPCRORGM.RC);
            BPCRORGM.INFO.FUNC = 'E';
            BPCRORGM.INFO.POINTER = BPRPORGM;
            BPCRORGM.INFO.LEN = 1252;
            S000_CALL_BPZRORGM();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRORGM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_ORGM_READ, BPCRORGM);
        if (BPCRORGM.RC.RC_CODE != 0 
            && BPCRORGM.RETURN_INFO != 'N') {
            BPCPBACT.OUTPUT_DATA.END_FLG = 'Y';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_NOTFND, BPCPBACT.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            if (BPCRORGM.RETURN_INFO == 'N') {
                BPCPBACT.OUTPUT_DATA.END_FLG = 'Y';
            }
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPBACT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPBACT = ");
            CEP.TRC(SCCGWA, BPCPBACT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
