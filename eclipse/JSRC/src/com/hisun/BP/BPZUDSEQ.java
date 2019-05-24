package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.DB.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZUDSEQ {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    BPZUDSEQ_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPZUDSEQ_WS_TEMP_VARIABLE();
    char WS_SEQ_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPRSEQ BPRSEQ = new BPRSEQ();
    BPROBL BPROBL = new BPROBL();
    BPCRMSEQ BPCRMSEQ = new BPCRMSEQ();
    BPCRBOBL BPCRBOBL = new BPCRBOBL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    BPCUDSEQ BPCUDSEQ;
    public void MP(SCCGWA SCCGWA, BPCUDSEQ BPCUDSEQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCUDSEQ = BPCUDSEQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZUDSEQ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRMSEQ);
        IBS.init(SCCGWA, BPCRBOBL);
        BPCRMSEQ.PTR = BPRSEQ;
        BPCRBOBL.PTR = BPROBL;
        BPCRMSEQ.LEN = 289;
        BPCRBOBL.LEN = 121;
        BPCUDSEQ.RC.RC_MMO = "BP";
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B006_DEL_PTR_PROC();
        if (pgmRtn) return;
    }
    public void B006_DEL_PTR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRSEQ);
        BPCRMSEQ.FUNC = 'R';
        BPRSEQ.KEY.TYPE = BPCUDSEQ.SEQ_TYPE;
        BPRSEQ.KEY.NAME = BPCUDSEQ.SEQ_CODE;
        S000_CALL_BPZRMSEQ();
        if (pgmRtn) return;
        if (BPCRMSEQ.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCUDSEQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        BPCUDSEQ.SEQ_TYPE = BPRSEQ.KEY.TYPE;
        BPCUDSEQ.SEQ_CODE = BPRSEQ.KEY.NAME;
        BPCUDSEQ.SEQ_DESC = BPRSEQ.DESC;
        BPCUDSEQ.SEQ_NO = BPRSEQ.SEQ_NO;
        BPCUDSEQ.OLD_SEQ_NO = BPRSEQ.OLD_SEQ_NO;
        BPCUDSEQ.FIRST_NUM = BPRSEQ.FIRST_NUM;
        BPCUDSEQ.INIT_ZERO = BPRSEQ.INIT_ZERO;
        BPCUDSEQ.SKIP_FLG = BPRSEQ.SKIP_FLG;
        BPCUDSEQ.OBL_FLG = BPRSEQ.OBL_FLG;
        BPCUDSEQ.VIP_FLG = BPRSEQ.VIP_FLG;
        BPCUDSEQ.VAL_DATE = BPRSEQ.VAL_DATE;
        BPCUDSEQ.MAX_SEQ_NO = BPRSEQ.MAX_SEQ_NO;
        BPCUDSEQ.MAX_FLG = BPRSEQ.MAX_FLG;
        BPCUDSEQ.WARN_SEQ_NO = BPRSEQ.WARN_SEQ_NO;
        BPCUDSEQ.LAST_UPD_TLR = BPRSEQ.LAST_UPD_TLR;
        BPCUDSEQ.LAST_UPD_DATE = BPRSEQ.LAST_UPD_DATE;
        BPCUDSEQ.BR = (short) BPRSEQ.BR;
        BPCUDSEQ.DP = (short) BPRSEQ.DP;
        BPCUDSEQ.STEP_NUM = (short) BPRSEQ.STEP_NUM;
        CEP.TRC(SCCGWA, BPRSEQ.STEP_NUM);
        BPCRMSEQ.FUNC = 'D';
        S000_CALL_BPZRMSEQ();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPROBL);
        BPROBL.KEY.NAME = BPCUDSEQ.SEQ_CODE;
        BPCRBOBL.FUN = 'U';
        S000_CALL_BPZRBOBL();
        if (pgmRtn) return;
        BPCRBOBL.FUN = 'R';
        S000_CALL_BPZRBOBL();
        if (pgmRtn) return;
        while (BPCRBOBL.RETURN_INFO != 'N') {
            BPCRBOBL.FUN = 'D';
            S000_CALL_BPZRBOBL();
            if (pgmRtn) return;
            BPCRBOBL.FUN = 'R';
            S000_CALL_BPZRBOBL();
            if (pgmRtn) return;
        }
        S000_CALL_BPZRBOBL();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZRMSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MAINT-SEQ", BPCRMSEQ);
        if (BPCRMSEQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRMSEQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUDSEQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRBOBL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-BRW-OBL", BPCRBOBL);
        if (BPCRBOBL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRBOBL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUDSEQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCUDSEQ.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCUDSEQ = ");
            CEP.TRC(SCCGWA, BPCUDSEQ);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
