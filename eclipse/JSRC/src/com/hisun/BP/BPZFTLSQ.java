package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFTLSQ {
    boolean pgmRtn = false;
    String CPN_R_STARTBR_TLT = "BP-R-STARTBR-TLT    ";
    char K_STS_SIGNON = 'O';
    BPZFTLSQ_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPZFTLSQ_WS_TEMP_VARIABLE();
    char WS_TBL_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPCRTLTB BPCRTLTB = new BPCRTLTB();
    BPRTLT BPRTLT = new BPRTLT();
    SCCGWA SCCGWA;
    BPCFTLSQ BPCFTLSQ;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCFTLSQ BPCFTLSQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFTLSQ = BPCFTLSQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFTLSQ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLT);
        IBS.init(SCCGWA, BPCRTLTB);
        IBS.CPY2CLS(SCCGWA, "BP0000", BPCFTLSQ.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_GROUP_PROCESS();
        if (pgmRtn) return;
        BPCFTLSQ.CNT = BPCRTLTB.INFO.CNT;
    }
    public void B010_GROUP_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLT);
        BPRTLT.NEW_BR = BPCFTLSQ.TLR_BR;
        if (BPCFTLSQ.SIGN_STS == ' ') {
            CEP.TRC(SCCGWA, "CC");
            BPRTLT.SIGN_STS = K_STS_SIGNON;
            BPCRTLTB.INFO.FUNC = 'G';
        } else {
            if (BPCFTLSQ.SIGN_STS == 'S') {
                CEP.TRC(SCCGWA, "AA");
                BPRTLT.SIGN_STS = K_STS_SIGNON;
                BPCRTLTB.INFO.FUNC = 'C';
            } else {
                CEP.TRC(SCCGWA, "BB");
                BPRTLT.SIGN_STS = BPCFTLSQ.SIGN_STS;
                BPCRTLTB.INFO.FUNC = 'G';
            }
        }
        BPCRTLTB.INFO.LEN = 1404;
        BPCRTLTB.INFO.POINTER = BPRTLT;
        CEP.TRC(SCCGWA, BPRTLT.SIGN_STS);
        CEP.TRC(SCCGWA, BPCRTLTB.INFO.FUNC);
        CEP.TRC(SCCGWA, BPRTLT.NEW_BR);
        S000_CALL_BPZRTLTB();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZRTLTB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_STARTBR_TLT, BPCRTLTB);
        if (BPCRTLTB.RC.RC_CODE != 0) {
            BPCFTLSQ.RC.RC_CODE = BPCRTLTB.RC.RC_CODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCFTLSQ.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCFTLSQ = ");
            CEP.TRC(SCCGWA, BPCFTLSQ);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
