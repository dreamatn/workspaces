package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.DB.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZUASEQ {
    boolean pgmRtn = false;
    BPZUASEQ_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPZUASEQ_WS_TEMP_VARIABLE();
    char WS_SEQ_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    BPCRMSEQ BPCRMSEQ = new BPCRMSEQ();
    BPRSEQ BPRSEQ = new BPRSEQ();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCUASEQ BPCUASEQ;
    public void MP(SCCGWA SCCGWA, BPCUASEQ BPCUASEQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCUASEQ = BPCUASEQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZUASEQ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRMSEQ);
        BPCRMSEQ.PTR = BPRSEQ;
        BPCRMSEQ.LEN = 289;
        BPCUASEQ.RC.RC_MMO = "BP";
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B005_ADD_PTR_PROC();
        if (pgmRtn) return;
    }
    public void B005_ADD_PTR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRSEQ);
        BPRSEQ.SEQ_NO = 0;
        BPRSEQ.OLD_SEQ_NO = 0;
        BPRSEQ.KEY.TYPE = BPCUASEQ.SEQ_TYPE;
        BPRSEQ.KEY.NAME = BPCUASEQ.SEQ_CODE;
        BPRSEQ.DESC = BPCUASEQ.SEQ_DESC;
        BPRSEQ.FIRST_NUM = BPCUASEQ.FIRST_NUM;
        BPRSEQ.INIT_ZERO = BPCUASEQ.INIT_ZERO;
        BPRSEQ.SKIP_FLG = BPCUASEQ.SKIP_FLG;
        BPRSEQ.OBL_FLG = BPCUASEQ.OBL_FLG;
        BPRSEQ.VIP_FLG = BPCUASEQ.VIP_FLG;
        BPRSEQ.VAL_DATE = BPCUASEQ.AC_DATE;
        BPRSEQ.MAX_SEQ_NO = BPCUASEQ.MAX_SEQ_NO;
        BPRSEQ.MAX_FLG = BPCUASEQ.MAX_FLG;
        BPRSEQ.WARN_SEQ_NO = BPCUASEQ.WARN_SEQ_NO;
        BPRSEQ.BR = BPCUASEQ.BR;
        BPRSEQ.DP = BPCUASEQ.DP;
        BPRSEQ.LAST_UPD_TLR = BPCUASEQ.TLR;
        BPRSEQ.LAST_UPD_DATE = BPCUASEQ.AC_DATE;
        BPRSEQ.STEP_NUM = BPCUASEQ.STEP_NUM;
        CEP.TRC(SCCGWA, BPRSEQ.STEP_NUM);
        BPCRMSEQ.FUNC = 'C';
        S000_CALL_BPZRMSEQ();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZRMSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MAINT-SEQ", BPCRMSEQ);
        if (BPCRMSEQ.RETURN_INFO == 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_EXIST, BPCUASEQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCUASEQ.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCUASEQ = ");
            CEP.TRC(SCCGWA, BPCUASEQ);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
