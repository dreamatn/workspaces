package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.DB.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZUAPBL {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String SCSOCWA = "SCSOCWA ";
    String SCSSCKDT = "SCSSCKDT";
    String SCSSCLDT = "SCSSCLDT";
    char ERROR = 'E';
    BPZUAPBL_WS_VARIABLES WS_VARIABLES = new BPZUAPBL_WS_VARIABLES();
    BPZUAPBL_WS_COND_FLG WS_COND_FLG = new BPZUAPBL_WS_COND_FLG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCMSG_ERROR_MSG ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCRAOBL BPCRAOBL = new BPCRAOBL();
    BPCRBOBL BPCRBOBL = new BPCRBOBL();
    BPCRMSEQ BPCRMSEQ = new BPCRMSEQ();
    BPRSEQ BPRSEQ = new BPRSEQ();
    BPROBL BPROBL = new BPROBL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    int RTCD = 0;
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA BP_AREA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCCWA SCCCWA;
    BPCUAOBL BPCUAOBL;
    public void MP(SCCGWA SCCGWA, BPCUAOBL BPCUAOBL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCUAOBL = BPCUAOBL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZUAPBL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCCWA = (SCCCWA) SC_AREA.CWA_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, BPCRMSEQ);
        IBS.init(SCCGWA, BPCRAOBL);
        IBS.init(SCCGWA, BPCRBOBL);
        BPCRMSEQ.PTR = BPRSEQ;
        BPCRAOBL.PTR = BPROBL;
        BPCRBOBL.PTR = BPROBL;
        BPCRMSEQ.LEN = 289;
        BPCRAOBL.LEN = 121;
        BPCRBOBL.LEN = 121;
        BPCUAOBL.RC.RC_MMO = "BP";
        IBS.init(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE);
        IBS.init(SCCGWA, WS_COND_FLG);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B032_ADD_PRE_HOLD();
        if (pgmRtn) return;
    }
    public void B032_ADD_PRE_HOLD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRSEQ);
        BPRSEQ.KEY.TYPE = BPCUAOBL.SEQ_TYPE;
        BPRSEQ.KEY.NAME = BPCUAOBL.SEQ_CODE;
        BPCRMSEQ.FUNC = 'R';
        S000_CALL_BPZRMSEQ();
        if (pgmRtn) return;
        if (BPCRMSEQ.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.BP_RECORD_NOTFND, BPCUAOBL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPRSEQ.OBL_FLG != 'Y') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.BP_CAN_NOT_PREHOLD, BPCUAOBL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCUAOBL.OBL_MIN > BPRSEQ.MAX_SEQ_NO) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.BP_PREHOLD_OVER_MAX, BPCUAOBL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCUAOBL.OBL_MAX > BPRSEQ.MAX_SEQ_NO) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.BP_PREHOLD_OVER_MAX, BPCUAOBL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCUAOBL.OBL_MIN > BPRSEQ.WARN_SEQ_NO) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.BP_OBL_MIN_ERR, BPCUAOBL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCUAOBL.OBL_MIN < BPRSEQ.FIRST_NUM) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.BP_OBL_LESS_FIRST, BPCUAOBL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCUAOBL.OBL_MAX > BPRSEQ.WARN_SEQ_NO) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.BP_OBL_MAX_ERR, BPCUAOBL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        BPCRBOBL.FUN = 'P';
        BPROBL.KEY.TYPE = BPCUAOBL.SEQ_TYPE;
        BPROBL.KEY.NAME = BPCUAOBL.SEQ_CODE;
        BPROBL.KEY.MIN_SEQ_NO = BPCUAOBL.OBL_MIN;
        BPROBL.MAX_SEQ_NO = BPCUAOBL.OBL_MAX;
        BPCRBOBL.STR_SEQ = BPCUAOBL.OBL_MIN;
        BPCRBOBL.END_SEQ = BPCUAOBL.OBL_MAX;
        S000_CALL_BPZRBOBL();
        if (pgmRtn) return;
        BPCRBOBL.FUN = 'R';
        S000_CALL_BPZRBOBL();
        if (pgmRtn) return;
        if (BPCRBOBL.RETURN_INFO == 'N') {
        } else if (BPCRBOBL.RETURN_INFO == 'F') {
            if (BPROBL.KEY.MIN_SEQ_NO < BPCRBOBL.STR_SEQ) {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.BP_SEQ_BEEN_HOLD, BPCUAOBL.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.BP_OVER_PREHOLD, BPCUAOBL.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
        }
        BPCRBOBL.FUN = 'E';
        S000_CALL_BPZRBOBL();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPROBL);
        BPROBL.KEY.TYPE = BPCUAOBL.SEQ_TYPE;
        BPROBL.KEY.NAME = BPCUAOBL.SEQ_CODE;
        BPROBL.KEY.MIN_SEQ_NO = BPCUAOBL.OBL_MIN;
        BPROBL.MAX_SEQ_NO = BPCUAOBL.OBL_MAX;
        BPROBL.SEQ_NO = 0;
        BPROBL.VAL_DATE = BPCUAOBL.AC_DATE;
        BPROBL.INIT_ZERO = BPRSEQ.INIT_ZERO;
        BPROBL.TLR = BPCUAOBL.TLR;
        BPROBL.BR = BPCUAOBL.BR;
        BPROBL.DP = BPCUAOBL.DP;
        BPCRAOBL.FUNC = 'C';
        S000_CALL_BPZRAOBL();
        if (pgmRtn) return;
        WS_VARIABLES.WS_TEMP_VARIABLE.CHK_FLG = 'Y';
        R00_CHECK_DATE();
        if (pgmRtn) return;
        if (WS_VARIABLES.WS_TEMP_VARIABLE.CHK_FLG == 'N') {
            BPCRMSEQ.FUNC = 'U';
            BPRSEQ.OLD_SEQ_NO = BPRSEQ.SEQ_NO;
            BPRSEQ.SEQ_NO = BPRSEQ.FIRST_NUM;
            BPRSEQ.VAL_DATE = BPCUAOBL.AC_DATE;
            S000_CALL_BPZRMSEQ();
            if (pgmRtn) return;
        }
    }
    public void R00_CHECK_DATE() throws IOException,SQLException,Exception {
        WS_VARIABLES.WS_TEMP_VARIABLE.VAL_DATE = BPRSEQ.VAL_DATE;
        if (BPRSEQ.INIT_ZERO == 'D') {
            if (BPCUAOBL.AC_DATE != BPRSEQ.VAL_DATE) {
                WS_VARIABLES.WS_TEMP_VARIABLE.CHK_FLG = 'N';
            }
        } else if (BPRSEQ.INIT_ZERO == 'W') {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = BPRSEQ.VAL_DATE;
            S000_CALL_SCSSCKDT();
            if (pgmRtn) return;
            WS_VARIABLES.WS_TEMP_VARIABLE.WEEK_NO = 0;
            if (SCCCKDT.WEEK_NO == 6) {
                if (BPRSEQ.VAL_DATE != BPCUAOBL.AC_DATE) {
                    WS_VARIABLES.WS_TEMP_VARIABLE.CHK_FLG = 'N';
                }
            } else {
                WS_VARIABLES.WS_TEMP_VARIABLE.WEEK_NO = (short) (6 - SCCCKDT.WEEK_NO);
            }
            if (WS_VARIABLES.WS_TEMP_VARIABLE.WEEK_NO != 0) {
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = BPRSEQ.VAL_DATE;
                SCCCLDT.DAYS = WS_VARIABLES.WS_TEMP_VARIABLE.WEEK_NO;
                S000_CALL_SCSSCLDT();
                if (pgmRtn) return;
                if (BPCUAOBL.AC_DATE > SCCCLDT.DATE2) {
                    WS_VARIABLES.WS_TEMP_VARIABLE.CHK_FLG = 'N';
                }
            }
        } else if (BPRSEQ.INIT_ZERO == 'M') {
            JIBS_tmp_str[0] = "" + BPCUAOBL.AC_DATE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            JIBS_tmp_str[1] = "" + WS_VARIABLES.WS_TEMP_VARIABLE.VAL_DATE;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            if (!JIBS_tmp_str[0].substring(0, 6).equalsIgnoreCase(JIBS_tmp_str[1].substring(0, 6))) {
                WS_VARIABLES.WS_TEMP_VARIABLE.CHK_FLG = 'N';
            }
        } else if (BPRSEQ.INIT_ZERO == 'Q') {
            JIBS_tmp_str[0] = "" + BPCUAOBL.AC_DATE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            JIBS_tmp_str[1] = "" + WS_VARIABLES.WS_TEMP_VARIABLE.VAL_DATE;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            if (JIBS_tmp_str[0].substring(0, 4).equalsIgnoreCase(JIBS_tmp_str[1].substring(0, 4))) {
                JIBS_tmp_str[0] = "" + WS_VARIABLES.WS_TEMP_VARIABLE.VAL_DATE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                JIBS_tmp_str[1] = "" + BPCUAOBL.AC_DATE;
                JIBS_tmp_int = JIBS_tmp_str[1].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
                if ((JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("1") 
                    || JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("2") 
                    || JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("3")) 
                    && (!JIBS_tmp_str[1].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("1") 
                    && !JIBS_tmp_str[1].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("2") 
                    && !JIBS_tmp_str[1].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("3"))) {
                    WS_VARIABLES.WS_TEMP_VARIABLE.CHK_FLG = 'N';
                }
                JIBS_tmp_str[0] = "" + WS_VARIABLES.WS_TEMP_VARIABLE.VAL_DATE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                JIBS_tmp_str[1] = "" + BPCUAOBL.AC_DATE;
                JIBS_tmp_int = JIBS_tmp_str[1].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
                if ((JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("4") 
                    || JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("5") 
                    || JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("6")) 
                    && (!JIBS_tmp_str[1].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("4") 
                    && !JIBS_tmp_str[1].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("5") 
                    && !JIBS_tmp_str[1].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("6"))) {
                    WS_VARIABLES.WS_TEMP_VARIABLE.CHK_FLG = 'N';
                }
                JIBS_tmp_str[0] = "" + WS_VARIABLES.WS_TEMP_VARIABLE.VAL_DATE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                JIBS_tmp_str[1] = "" + BPCUAOBL.AC_DATE;
                JIBS_tmp_int = JIBS_tmp_str[1].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
                if ((JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("7") 
                    || JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("8") 
                    || JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("9")) 
                    && (!JIBS_tmp_str[1].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("7") 
                    && !JIBS_tmp_str[1].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("8") 
                    && !JIBS_tmp_str[1].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("9"))) {
                    WS_VARIABLES.WS_TEMP_VARIABLE.CHK_FLG = 'N';
                }
                JIBS_tmp_str[0] = "" + WS_VARIABLES.WS_TEMP_VARIABLE.VAL_DATE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                JIBS_tmp_str[1] = "" + BPCUAOBL.AC_DATE;
                JIBS_tmp_int = JIBS_tmp_str[1].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
                if ((JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("10") 
                    || JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("11") 
                    || JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("12")) 
                    && (!JIBS_tmp_str[1].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("10") 
                    && !JIBS_tmp_str[1].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("11") 
                    && !JIBS_tmp_str[1].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("12"))) {
                    WS_VARIABLES.WS_TEMP_VARIABLE.CHK_FLG = 'N';
                }
            } else {
                WS_VARIABLES.WS_TEMP_VARIABLE.CHK_FLG = 'N';
            }
        } else if (BPRSEQ.INIT_ZERO == 'Y') {
            JIBS_tmp_str[0] = "" + BPCUAOBL.AC_DATE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            JIBS_tmp_str[1] = "" + WS_VARIABLES.WS_TEMP_VARIABLE.VAL_DATE;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            if (!JIBS_tmp_str[0].substring(0, 4).equalsIgnoreCase(JIBS_tmp_str[1].substring(0, 4))) {
                WS_VARIABLES.WS_TEMP_VARIABLE.CHK_FLG = 'N';
            }
        } else {
        }
    }
    public void S000_CALL_SCSSCKDT() throws IOException,SQLException,Exception {
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            WS_VARIABLES.WS_TEMP_VARIABLE.WS_MSGID.MSG_AP = "SC";
            WS_VARIABLES.WS_TEMP_VARIABLE.WS_MSGID.MSG_CODE = SCCCKDT.RC;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE.WS_MSGID);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT2 = new SCSSCLDT();
        SCSSCLDT2.MP(SCCGWA, SCCCLDT);
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == ERROR) {
            Z_RET();
            if (pgmRtn) return;
        }
        if (SCCCLDT.RC != 0) {
            WS_VARIABLES.WS_TEMP_VARIABLE.WS_MSGID.MSG_AP = "SC";
            WS_VARIABLES.WS_TEMP_VARIABLE.WS_MSGID.MSG_CODE = SCCCLDT.RC;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE.WS_MSGID);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZRMSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MAINT-SEQ", BPCRMSEQ);
        CEP.TRC(SCCGWA, BPCRMSEQ.RC.RC_CODE);
        if (BPCRMSEQ.RC.RC_CODE != 0) {
            IBS.CPY2CLS(SCCGWA, BPCRMSEQ.RC.RC_MMO, BPCUAOBL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRAOBL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-ADD-OBL", BPCRAOBL);
        if (BPCRAOBL.RETURN_INFO == 'D') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.BP_RECORD_EXIST, BPCUAOBL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCRAOBL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRAOBL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUAOBL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRBOBL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-BRW-OBL", BPCRBOBL);
        if (BPCRBOBL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRBOBL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUAOBL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCUAOBL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCUAOBL = ");
            CEP.TRC(SCCGWA, BPCUAOBL);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
