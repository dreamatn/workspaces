package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.DB.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZUUSEQ {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    BPZUUSEQ_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPZUUSEQ_WS_TEMP_VARIABLE();
    char WS_SEQ_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    BPRSEQ BPRSEQ = new BPRSEQ();
    BPCRMSEQ BPCRMSEQ = new BPCRMSEQ();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    BPCUUSEQ BPCUUSEQ;
    public void MP(SCCGWA SCCGWA, BPCUUSEQ BPCUUSEQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCUUSEQ = BPCUUSEQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZUUSEQ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRMSEQ);
        BPCRMSEQ.PTR = BPRSEQ;
        BPCRMSEQ.LEN = 289;
        BPCUUSEQ.RC.RC_MMO = "BP";
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B003_UPD_PTR_PROC();
        if (pgmRtn) return;
    }
    public void B003_UPD_PTR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRSEQ);
        BPRSEQ.KEY.TYPE = BPCUUSEQ.SEQ_TYPE;
        BPRSEQ.KEY.NAME = BPCUUSEQ.SEQ_CODE;
        BPCRMSEQ.FUNC = 'R';
        S000_CALL_BPZRMSEQ();
        if (pgmRtn) return;
        if (BPCRMSEQ.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCUUSEQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "UPDATE SUCESSFULLY");
        if (BPCUUSEQ.SEQ_DESC.equalsIgnoreCase(BPRSEQ.DESC) 
            && BPCUUSEQ.FIRST_NUM == BPRSEQ.FIRST_NUM 
            && BPCUUSEQ.INIT_ZERO == BPRSEQ.INIT_ZERO 
            && BPCUUSEQ.SKIP_FLG == BPRSEQ.SKIP_FLG 
            && BPCUUSEQ.OBL_FLG == BPRSEQ.OBL_FLG 
            && BPCUUSEQ.VIP_FLG == BPRSEQ.VIP_FLG 
            && BPCUUSEQ.MAX_SEQ_NO == BPRSEQ.MAX_SEQ_NO 
            && BPCUUSEQ.MAX_FLG == BPRSEQ.MAX_FLG 
            && BPCUUSEQ.WARN_SEQ_NO == BPRSEQ.WARN_SEQ_NO 
            && BPCUUSEQ.STEP_NUM == BPRSEQ.STEP_NUM) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_DATA_NOT_CHANGE, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCUUSEQ.FIRST_NUM != 0) {
            if (BPCUUSEQ.FIRST_NUM > BPRSEQ.MAX_SEQ_NO) {
                CEP.TRC(SCCGWA, BPCUUSEQ.FIRST_NUM);
                CEP.TRC(SCCGWA, BPRSEQ.MAX_SEQ_NO);
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FIRST_NO_ERR, BPCUUSEQ.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        BPRSEQ.FIRST_NUM = BPCUUSEQ.FIRST_NUM;
        if (BPCUUSEQ.WARN_SEQ_NO > BPCUUSEQ.MAX_SEQ_NO) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_WARN_NO_ERR, BPCUUSEQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        BPRSEQ.DESC = BPCUUSEQ.SEQ_DESC;
        BPRSEQ.INIT_ZERO = BPCUUSEQ.INIT_ZERO;
        BPRSEQ.SKIP_FLG = BPCUUSEQ.SKIP_FLG;
        BPRSEQ.OBL_FLG = BPCUUSEQ.OBL_FLG;
        BPRSEQ.VIP_FLG = BPCUUSEQ.VIP_FLG;
        BPRSEQ.MAX_SEQ_NO = BPCUUSEQ.MAX_SEQ_NO;
        BPRSEQ.MAX_FLG = BPCUUSEQ.MAX_FLG;
        BPRSEQ.WARN_SEQ_NO = BPCUUSEQ.WARN_SEQ_NO;
        BPRSEQ.BR = BPCUUSEQ.BR;
        BPRSEQ.DP = BPCUUSEQ.DP;
        BPRSEQ.LAST_UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRSEQ.LAST_UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRSEQ.STEP_NUM = BPCUUSEQ.STEP_NUM;
        CEP.TRC(SCCGWA, BPRSEQ.STEP_NUM);
        BPCRMSEQ.FUNC = 'U';
        S000_CALL_BPZRMSEQ();
        if (pgmRtn) return;
        BPCUUSEQ.SEQ_CODE = BPRSEQ.KEY.NAME;
        BPCUUSEQ.SEQ_NO = BPRSEQ.SEQ_NO;
        BPCUUSEQ.OLD_SEQ_NO = BPRSEQ.OLD_SEQ_NO;
        BPCUUSEQ.FIRST_NUM = BPRSEQ.FIRST_NUM;
        BPCUUSEQ.INIT_ZERO = BPRSEQ.INIT_ZERO;
        BPCUUSEQ.SKIP_FLG = BPRSEQ.SKIP_FLG;
        BPCUUSEQ.OBL_FLG = BPRSEQ.OBL_FLG;
        BPCUUSEQ.VIP_FLG = BPRSEQ.VIP_FLG;
        BPCUUSEQ.VAL_DATE = BPRSEQ.VAL_DATE;
        BPCUUSEQ.MAX_SEQ_NO = BPRSEQ.MAX_SEQ_NO;
        BPCUUSEQ.MAX_FLG = BPRSEQ.MAX_FLG;
        BPCUUSEQ.WARN_SEQ_NO = BPRSEQ.WARN_SEQ_NO;
        BPCUUSEQ.LAST_UPD_TLR = BPRSEQ.LAST_UPD_TLR;
        BPCUUSEQ.LAST_UPD_DATE = BPRSEQ.LAST_UPD_DATE;
        BPCUUSEQ.BR = (short) BPRSEQ.BR;
        BPCUUSEQ.DP = (short) BPRSEQ.DP;
        BPCUUSEQ.STEP_NUM = (short) BPRSEQ.STEP_NUM;
        CEP.TRC(SCCGWA, BPCUUSEQ.STEP_NUM);
    }
    public void S000_CALL_BPZRMSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MAINT-SEQ", BPCRMSEQ);
        if (BPCRMSEQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRMSEQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUUSEQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCUUSEQ.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCUUSEQ = ");
            CEP.TRC(SCCGWA, BPCUUSEQ);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
