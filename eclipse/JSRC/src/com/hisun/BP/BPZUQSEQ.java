package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.DB.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZUQSEQ {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    BPZUQSEQ_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPZUQSEQ_WS_TEMP_VARIABLE();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPRSEQ BPRSEQ = new BPRSEQ();
    BPCRMSEQ BPCRMSEQ = new BPCRMSEQ();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    BPCUQSEQ BPCUQSEQ;
    public void MP(SCCGWA SCCGWA, BPCUQSEQ BPCUQSEQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCUQSEQ = BPCUQSEQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZUQSEQ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRMSEQ);
        BPCRMSEQ.PTR = BPRSEQ;
        BPCRMSEQ.LEN = 289;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B001_QRY_PTR_PROC();
        if (pgmRtn) return;
    }
    public void B001_QRY_PTR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRSEQ);
        BPRSEQ.KEY.TYPE = BPCUQSEQ.SEQ_TYPE;
        BPRSEQ.KEY.NAME = BPCUQSEQ.SEQ_CODE;
        BPCRMSEQ.FUNC = 'Q';
        S000_CALL_BPZRMSEQ();
        if (pgmRtn) return;
        if (BPCRMSEQ.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCUQSEQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        BPCUQSEQ.SEQ_TYPE = BPRSEQ.KEY.TYPE;
        BPCUQSEQ.SEQ_CODE = BPRSEQ.KEY.NAME;
        BPCUQSEQ.SEQ_DESC = BPRSEQ.DESC;
        BPCUQSEQ.SEQ_NO = BPRSEQ.SEQ_NO;
        BPCUQSEQ.OLD_SEQ_NO = BPRSEQ.OLD_SEQ_NO;
        BPCUQSEQ.FIRST_NUM = BPRSEQ.FIRST_NUM;
        BPCUQSEQ.INIT_ZERO = BPRSEQ.INIT_ZERO;
        BPCUQSEQ.SKIP_FLG = BPRSEQ.SKIP_FLG;
        BPCUQSEQ.OBL_FLG = BPRSEQ.OBL_FLG;
        BPCUQSEQ.VIP_FLG = BPRSEQ.VIP_FLG;
        BPCUQSEQ.VAL_DATE = BPRSEQ.VAL_DATE;
        BPCUQSEQ.STEP_NUM = BPRSEQ.STEP_NUM;
        BPCUQSEQ.MAX_SEQ_NO = BPRSEQ.MAX_SEQ_NO;
        BPCUQSEQ.MAX_FLG = BPRSEQ.MAX_FLG;
        BPCUQSEQ.WARN_SEQ_NO = BPRSEQ.WARN_SEQ_NO;
        BPCUQSEQ.LAST_UPD_TLR = BPRSEQ.LAST_UPD_TLR;
        BPCUQSEQ.LAST_UPD_DATE = BPRSEQ.LAST_UPD_DATE;
        BPCUQSEQ.BR = (short) BPRSEQ.BR;
        BPCUQSEQ.DP = (short) BPRSEQ.DP;
    }
    public void S000_CALL_BPZRMSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MAINT-SEQ", BPCRMSEQ);
        if (BPCRMSEQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRMSEQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUQSEQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCUQSEQ.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCUQSEQ = ");
            CEP.TRC(SCCGWA, BPCUQSEQ);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
