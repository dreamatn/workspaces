package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.DB.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZUBSEQ {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    short K_Q_MAX_CNT = 5000;
    BPZUBSEQ_WS_MSGID WS_MSGID = new BPZUBSEQ_WS_MSGID();
    BPZUBSEQ_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPZUBSEQ_WS_TEMP_VARIABLE();
    short WS_TS_CNT = 0;
    BPZUBSEQ_WS_SEQ_DATA WS_SEQ_DATA = new BPZUBSEQ_WS_SEQ_DATA();
    BPZUBSEQ_WS_TIT1 WS_TIT1 = new BPZUBSEQ_WS_TIT1();
    BPZUBSEQ_WS_TIT2 WS_TIT2 = new BPZUBSEQ_WS_TIT2();
    char WS_SEQ_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCRBSEQ BPCRBSEQ = new BPCRBSEQ();
    BPRSEQ BPRSEQ = new BPRSEQ();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCTLM_MSG SCCCTLM_MSG = new SCCCTLM_MSG();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    BPCUBSEQ BPCUBSEQ;
    public void MP(SCCGWA SCCGWA, BPCUBSEQ BPCUBSEQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCUBSEQ = BPCUBSEQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZUBSEQ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRBSEQ);
        BPCRBSEQ.PTR = BPRSEQ;
        BPCRBSEQ.LEN = 289;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B001_READY_TS_HEAD_TIT();
        if (pgmRtn) return;
        B009_OUTPUT_PTR_REC();
        if (pgmRtn) return;
    }
    public void B001_READY_TS_HEAD_TIT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 254;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
        WS_TS_CNT = 0;
    }
    public void B009_OUTPUT_PTR_REC() throws IOException,SQLException,Exception {
        B009_001_GET_KEY();
        if (pgmRtn) return;
        S000_CALL_BPZRBSEQ();
        if (pgmRtn) return;
        BPCRBSEQ.FUN = 'R';
        S000_CALL_BPZRBSEQ();
        if (pgmRtn) return;
        while (BPCRBSEQ.FUN != 'E' 
            && SCCMPAG.FUNC != 'E') {
            R00_OUT_RECORD();
            if (pgmRtn) return;
            S000_CALL_BPZRBSEQ();
            if (pgmRtn) return;
        }
        S000_CALL_BPZRBSEQ();
        if (pgmRtn) return;
    }
    public void B009_001_GET_KEY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRSEQ);
        CEP.TRC(SCCGWA, BPCUBSEQ.SEQ_TYPE);
        CEP.TRC(SCCGWA, BPCUBSEQ.SEQ_CODE);
        if (BPCUBSEQ.SEQ_TYPE.trim().length() > 0) {
            BPRSEQ.KEY.TYPE = BPCUBSEQ.SEQ_TYPE;
            BPRSEQ.KEY.NAME = BPCUBSEQ.SEQ_CODE;
            if (BPRSEQ.KEY.NAME.trim().length() == 0) {
                BPCRBSEQ.FUN = 'T';
            } else {
                BPCRBSEQ.FUN = 'S';
                BPRSEQ.KEY.NAME = "%%%%%%%%%%";
                WS_TEMP_VARIABLE.WS_TMP_LEN = 10;
                WS_TEMP_VARIABLE.WS_EOF = ' ';
                for (WS_TEMP_VARIABLE.WS_POS = WS_TEMP_VARIABLE.WS_TMP_LEN; WS_TEMP_VARIABLE.WS_POS > 1 
                    && WS_TEMP_VARIABLE.WS_EOF != 'Y'; WS_TEMP_VARIABLE.WS_POS += -1) {
                    if (BPCUBSEQ.SEQ_CODE == null) BPCUBSEQ.SEQ_CODE = "";
                    JIBS_tmp_int = BPCUBSEQ.SEQ_CODE.length();
                    for (int i=0;i<10-JIBS_tmp_int;i++) BPCUBSEQ.SEQ_CODE += " ";
                    if (BPCUBSEQ.SEQ_CODE.substring(WS_TEMP_VARIABLE.WS_POS - 1, WS_TEMP_VARIABLE.WS_POS + 1 - 1).trim().length() > 0) {
                        WS_TEMP_VARIABLE.WS_EOF = 'Y';
                        if (BPCUBSEQ.SEQ_CODE == null) BPCUBSEQ.SEQ_CODE = "";
                        JIBS_tmp_int = BPCUBSEQ.SEQ_CODE.length();
                        for (int i=0;i<10-JIBS_tmp_int;i++) BPCUBSEQ.SEQ_CODE += " ";
                        if (BPRSEQ.KEY.NAME == null) BPRSEQ.KEY.NAME = "";
                        JIBS_tmp_int = BPRSEQ.KEY.NAME.length();
                        for (int i=0;i<10-JIBS_tmp_int;i++) BPRSEQ.KEY.NAME += " ";
                        BPRSEQ.KEY.NAME = BPCUBSEQ.SEQ_CODE.substring(0, WS_TEMP_VARIABLE.WS_POS) + BPRSEQ.KEY.NAME.substring(WS_TEMP_VARIABLE.WS_POS);
                    }
                }
            }
        } else {
            if (BPCUBSEQ.SEQ_CODE.trim().length() == 0) {
                BPCRBSEQ.FUN = 'A';
            } else {
                BPRSEQ.KEY.NAME = BPCUBSEQ.SEQ_CODE;
                BPCRBSEQ.FUN = 'N';
            }
        }
    }
    public void R00_OUT_RECORD() throws IOException,SQLException,Exception {
        WS_SEQ_DATA.WS_SEQ_TYPE = BPRSEQ.KEY.TYPE;
        WS_SEQ_DATA.WS_SEQ_NAME = BPRSEQ.KEY.NAME;
        WS_SEQ_DATA.WS_SEQ_NO = BPRSEQ.SEQ_NO;
        WS_SEQ_DATA.WS_CYCLE_DATE = BPRSEQ.INIT_ZERO;
        WS_SEQ_DATA.WS_OBL_FLAG = BPRSEQ.OBL_FLG;
        WS_SEQ_DATA.WS_FIRST_NO = BPRSEQ.FIRST_NUM;
        WS_SEQ_DATA.WS_MAX_SEQ = BPRSEQ.MAX_SEQ_NO;
        WS_SEQ_DATA.WS_MAX_FLAG = BPRSEQ.MAX_FLG;
        WS_SEQ_DATA.WS_WARN_SEQ = BPRSEQ.WARN_SEQ_NO;
        WS_TEMP_VARIABLE.WS_TS_REC = " ";
        WS_TEMP_VARIABLE.WS_TS_REC = IBS.CLS2CPY(SCCGWA, WS_SEQ_DATA);
        WS_TEMP_VARIABLE.WS_LEN = 78;
        WS_TEMP_VARIABLE.WS_ACCU_LEN += 1;
        if (WS_TEMP_VARIABLE.WS_ACCU_LEN <= 1000) {
            S000_WRITE_TS();
            if (pgmRtn) return;
        } else {
            WS_TEMP_VARIABLE.WS_TS_REC = " ";
            WS_TEMP_VARIABLE.WS_TS_REC = "TO BE CONTINUED";
            WS_TEMP_VARIABLE.WS_LEN = 78;
            S000_WRITE_TS();
            if (pgmRtn) return;
            WS_TEMP_VARIABLE.WS_EOF = 'Y';
        }
    }
    public void S000_WRITE_TS() throws IOException,SQLException,Exception {
        if (WS_TS_CNT > K_Q_MAX_CNT) {
            IBS.CPY2CLS(SCCGWA, SCCCTLM_MSG.SC_ERR_ROW_LIMIT, WS_MSGID);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_MSGID);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = WS_TEMP_VARIABLE.WS_TS_REC;
        SCCMPAG.DATA_LEN = WS_TEMP_VARIABLE.WS_LEN;
        B_MPAG();
        if (pgmRtn) return;
        WS_TS_CNT += 1;
    }
    public void S000_CALL_BPZRBSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-BRW-SEQ", BPCRBSEQ);
        if (BPCRBSEQ.RC.RC_CODE != 0) {
            IBS.CPY2CLS(SCCGWA, BPCRBSEQ.RC.RC_MMO, BPCUBSEQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCUBSEQ.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCUBSEQ = ");
            CEP.TRC(SCCGWA, BPCUBSEQ);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
