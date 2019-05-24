package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.DB.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZUBOBL {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    char K_ERROR = 'E';
    short K_Q_MAX_CNT = 5000;
    short WS_CURSOR = 0;
    BPZUBOBL_WS_MSGID WS_MSGID = new BPZUBOBL_WS_MSGID();
    BPZUBOBL_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPZUBOBL_WS_TEMP_VARIABLE();
    short WS_TS_CNT = 0;
    BPZUBOBL_WS_PREHD_DATA WS_PREHD_DATA = new BPZUBOBL_WS_PREHD_DATA();
    BPZUBOBL_WS_TIT3 WS_TIT3 = new BPZUBOBL_WS_TIT3();
    BPZUBOBL_WS_TIT4 WS_TIT4 = new BPZUBOBL_WS_TIT4();
    char WS_OBL_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCRBOBL BPCRBOBL = new BPCRBOBL();
    BPROBL BPROBL = new BPROBL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCTLM_MSG SCCCTLM_MSG = new SCCCTLM_MSG();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    BPCUBOBL BPCUBOBL;
    public void MP(SCCGWA SCCGWA, BPCUBOBL BPCUBOBL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCUBOBL = BPCUBOBL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZUBOBL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRBOBL);
        BPCRBOBL.PTR = BPROBL;
        BPCRBOBL.LEN = 121;
        BPCRBOBL.RC.RC_MMO = "BP";
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
        SCCMPAG.MAX_COL_NO = 250;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
        WS_TS_CNT = 0;
    }
    public void B009_OUTPUT_PTR_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPROBL);
        BPROBL.KEY.TYPE = BPCUBOBL.SEQ_TYPE;
        BPROBL.KEY.NAME = BPCUBOBL.SEQ_CODE;
        CEP.TRC(SCCGWA, BPCUBOBL.SEQ_TYPE);
        CEP.TRC(SCCGWA, BPCUBOBL.SEQ_CODE);
        B009_001_GET_KEY();
        if (pgmRtn) return;
        S000_CALL_BPZRBOBL();
        if (pgmRtn) return;
        BPCRBOBL.FUN = 'R';
        S000_CALL_BPZRBOBL();
        if (pgmRtn) return;
        while (BPCRBOBL.FUN != 'E' 
            && SCCMPAG.FUNC != 'E') {
            R000_OUT_RECORD();
            if (pgmRtn) return;
            S000_CALL_BPZRBOBL();
            if (pgmRtn) return;
        }
        S000_CALL_BPZRBOBL();
        if (pgmRtn) return;
    }
    public void B009_001_GET_KEY() throws IOException,SQLException,Exception {
        if (BPCUBOBL.SEQ_TYPE.trim().length() > 0) {
            if (BPCUBOBL.SEQ_CODE.trim().length() == 0) {
                BPCRBOBL.FUN = 'T';
            } else {
                BPCRBOBL.FUN = 'S';
                BPROBL.KEY.NAME = "%%%%%%%%%%%%%%%%";
                WS_TEMP_VARIABLE.WS_TMP_LEN = 10;
                WS_TEMP_VARIABLE.WS_EOF = ' ';
                for (WS_TEMP_VARIABLE.WS_POS = WS_TEMP_VARIABLE.WS_TMP_LEN; WS_TEMP_VARIABLE.WS_POS > 1 
                    && WS_TEMP_VARIABLE.WS_EOF != 'Y'; WS_TEMP_VARIABLE.WS_POS += -1) {
                    if (BPCUBOBL.SEQ_CODE == null) BPCUBOBL.SEQ_CODE = "";
                    JIBS_tmp_int = BPCUBOBL.SEQ_CODE.length();
                    for (int i=0;i<10-JIBS_tmp_int;i++) BPCUBOBL.SEQ_CODE += " ";
                    if (BPCUBOBL.SEQ_CODE.substring(WS_TEMP_VARIABLE.WS_POS - 1, WS_TEMP_VARIABLE.WS_POS + 1 - 1).trim().length() > 0) {
                        WS_TEMP_VARIABLE.WS_EOF = 'Y';
                        if (BPCUBOBL.SEQ_CODE == null) BPCUBOBL.SEQ_CODE = "";
                        JIBS_tmp_int = BPCUBOBL.SEQ_CODE.length();
                        for (int i=0;i<10-JIBS_tmp_int;i++) BPCUBOBL.SEQ_CODE += " ";
                        if (BPROBL.KEY.NAME == null) BPROBL.KEY.NAME = "";
                        JIBS_tmp_int = BPROBL.KEY.NAME.length();
                        for (int i=0;i<16-JIBS_tmp_int;i++) BPROBL.KEY.NAME += " ";
                        BPROBL.KEY.NAME = BPCUBOBL.SEQ_CODE.substring(0, WS_TEMP_VARIABLE.WS_POS) + BPROBL.KEY.NAME.substring(WS_TEMP_VARIABLE.WS_POS);
                    }
                }
            }
        } else {
            if (BPCUBOBL.SEQ_CODE.trim().length() == 0) {
                BPCRBOBL.FUN = 'A';
            } else {
                BPCRBOBL.FUN = 'N';
            }
        }
    }
    public void R000_OUT_RECORD() throws IOException,SQLException,Exception {
        WS_PREHD_DATA.WS_HD_TYPE = BPROBL.KEY.TYPE;
        WS_PREHD_DATA.WS_HD_CODE = BPROBL.KEY.NAME;
        WS_PREHD_DATA.WS_HD_SEQ = BPROBL.SEQ_NO;
        WS_PREHD_DATA.WS_HD_MIN = BPROBL.KEY.MIN_SEQ_NO;
        WS_PREHD_DATA.WS_HD_MAX = BPROBL.MAX_SEQ_NO;
        WS_TEMP_VARIABLE.WS_TS_REC = " ";
        WS_TEMP_VARIABLE.WS_TS_REC = IBS.CLS2CPY(SCCGWA, WS_PREHD_DATA);
        WS_TEMP_VARIABLE.WS_LEN = 60;
        WS_TEMP_VARIABLE.WS_ACCU_LEN += 1;
        if (WS_TEMP_VARIABLE.WS_ACCU_LEN <= 1000) {
            S000_WRITE_TS();
            if (pgmRtn) return;
        } else {
            WS_TEMP_VARIABLE.WS_TS_REC = " ";
            WS_TEMP_VARIABLE.WS_TS_REC = "TO BE CONTINUED";
            S000_WRITE_TS();
            if (pgmRtn) return;
            WS_TEMP_VARIABLE.WS_EOF = 'Y';
        }
    }
    public void S000_WRITE_TS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_LEN);
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
    public void S000_CALL_BPZRBOBL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-BRW-OBL", BPCRBOBL);
        if (BPCRBOBL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRBOBL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUBOBL.RC);
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
        if (BPCUBOBL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCUBOBL = ");
            CEP.TRC(SCCGWA, BPCUBOBL);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
