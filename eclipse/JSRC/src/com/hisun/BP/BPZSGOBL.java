package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.DB.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSGOBL {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String SCSSCKDT = "SCSSCKDT";
    String SCSSCLDT = "SCSSCLDT";
    char ERROR = 'E';
    String PGM_NAME = "BPZSGOBL";
    BPZSGOBL_WS_VARIABLES WS_VARIABLES = new BPZSGOBL_WS_VARIABLES();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCMSG_ERROR_MSG ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCRAOBL BPCRAOBL = new BPCRAOBL();
    BPROBL BPROBL = new BPROBL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPCRMSEQ BPCRMSEQ = new BPCRMSEQ();
    BPRSEQ BPRSEQ = new BPRSEQ();
    int RTCD = 0;
    SCCGWA SCCGWA;
    BPCSGOBL BPCSGOBL;
    public void MP(SCCGWA SCCGWA, BPCSGOBL BPCSGOBL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSGOBL = BPCSGOBL;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSGOBL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, BPCRAOBL);
        BPCRAOBL.PTR = BPROBL;
        BPCRAOBL.LEN = 121;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_GET_SEQ_PROCESS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPROBL);
        BPCRAOBL.FUNC = 'R';
        BPROBL.KEY.TYPE = BPCSGOBL.TYPE;
        BPROBL.KEY.NAME = BPCSGOBL.CODE;
        BPROBL.KEY.MIN_SEQ_NO = BPCSGOBL.MIN_NUM;
        S000_CALL_BPZRAOBL();
        if (pgmRtn) return;
        WS_VARIABLES.WS_TEMP_VARIABLE.CHK_FLG = 'Y';
        R00_CHECK_DATE();
        if (pgmRtn) return;
        if (WS_VARIABLES.WS_TEMP_VARIABLE.CHK_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.BP_VALDT_ERR, BPCSGOBL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPROBL.SEQ_NO == 0) {
            BPROBL.SEQ_NO = BPROBL.KEY.MIN_SEQ_NO;
        } else {
            CEP.TRC(SCCGWA, BPRSEQ.STEP_NUM);
            CEP.TRC(SCCGWA, BPROBL.SEQ_NO);
            BPROBL.SEQ_NO = BPROBL.SEQ_NO + BPRSEQ.STEP_NUM;
            CEP.TRC(SCCGWA, BPROBL.SEQ_NO);
        }
        if (BPROBL.SEQ_NO > BPROBL.MAX_SEQ_NO) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.BP_OBL_OVER_MAX, BPCSGOBL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        BPCSGOBL.SEQ = BPROBL.SEQ_NO;
        BPCRAOBL.FUNC = 'U';
        S000_CALL_BPZRAOBL();
        if (pgmRtn) return;
    }
    public void B010_GET_SEQ_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRSEQ);
        IBS.init(SCCGWA, BPCRMSEQ);
        BPRSEQ.KEY.TYPE = BPCSGOBL.TYPE;
        BPRSEQ.KEY.NAME = BPCSGOBL.CODE;
        BPCRMSEQ.LEN = 289;
        BPCRMSEQ.PTR = BPRSEQ;
        BPCRMSEQ.FUNC = 'Q';
        S000_CALL_BPZRMSEQ();
        if (pgmRtn) return;
        if (BPCRMSEQ.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.BP_RECORD_NOTFND, BPCSGOBL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R00_CHECK_DATE() throws IOException,SQLException,Exception {
        WS_VARIABLES.WS_TEMP_VARIABLE.VAL_DATE = BPROBL.VAL_DATE;
        if (BPROBL.INIT_ZERO == 'D') {
            if (BPCSGOBL.AC_DATE != BPROBL.VAL_DATE) {
                WS_VARIABLES.WS_TEMP_VARIABLE.CHK_FLG = 'N';
            }
        } else if (BPROBL.INIT_ZERO == 'W') {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = BPROBL.VAL_DATE;
            S00_LINK_SCSSCKDT();
            if (pgmRtn) return;
            WS_VARIABLES.WS_TEMP_VARIABLE.WEEK_NO = 0;
            if (SCCCKDT.WEEK_NO == 6) {
                if (BPROBL.VAL_DATE != BPCSGOBL.AC_DATE) {
                    WS_VARIABLES.WS_TEMP_VARIABLE.CHK_FLG = 'N';
                }
            } else {
                WS_VARIABLES.WS_TEMP_VARIABLE.WEEK_NO = (short) (6 - SCCCKDT.WEEK_NO);
            }
            if (WS_VARIABLES.WS_TEMP_VARIABLE.WEEK_NO != 0) {
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = BPROBL.VAL_DATE;
                SCCCLDT.DAYS = WS_VARIABLES.WS_TEMP_VARIABLE.WEEK_NO;
                S00_CALL_SCSSCLDT();
                if (pgmRtn) return;
                if (BPCSGOBL.AC_DATE > SCCCLDT.DATE2) {
                    WS_VARIABLES.WS_TEMP_VARIABLE.CHK_FLG = 'N';
                }
            }
        } else if (BPROBL.INIT_ZERO == 'M') {
            JIBS_tmp_str[0] = "" + BPCSGOBL.AC_DATE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            JIBS_tmp_str[1] = "" + WS_VARIABLES.WS_TEMP_VARIABLE.VAL_DATE;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            if (!JIBS_tmp_str[0].substring(0, 6).equalsIgnoreCase(JIBS_tmp_str[1].substring(0, 6))) {
                WS_VARIABLES.WS_TEMP_VARIABLE.CHK_FLG = 'N';
            }
        } else if (BPROBL.INIT_ZERO == 'Q') {
            JIBS_tmp_str[0] = "" + BPCSGOBL.AC_DATE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            JIBS_tmp_str[1] = "" + WS_VARIABLES.WS_TEMP_VARIABLE.VAL_DATE;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            if (JIBS_tmp_str[0].substring(0, 4).equalsIgnoreCase(JIBS_tmp_str[1].substring(0, 4))) {
                JIBS_tmp_str[0] = "" + WS_VARIABLES.WS_TEMP_VARIABLE.VAL_DATE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                JIBS_tmp_str[1] = "" + BPCSGOBL.AC_DATE;
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
                JIBS_tmp_str[1] = "" + BPCSGOBL.AC_DATE;
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
                JIBS_tmp_str[1] = "" + BPCSGOBL.AC_DATE;
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
                JIBS_tmp_str[1] = "" + BPCSGOBL.AC_DATE;
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
        } else if (BPROBL.INIT_ZERO == 'Y') {
            JIBS_tmp_str[0] = "" + BPCSGOBL.AC_DATE;
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
    public void S00_LINK_SCSSCKDT() throws IOException,SQLException,Exception {
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            WS_VARIABLES.WS_TEMP_VARIABLE.WS_MSGID.MSG_AP = "SC";
            WS_VARIABLES.WS_TEMP_VARIABLE.WS_MSGID.MSG_CODE = SCCCKDT.RC;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE.WS_MSGID);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S00_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
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
    public void S000_CALL_BPZRAOBL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-ADD-OBL", BPCRAOBL);
        if (BPCRAOBL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRAOBL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSGOBL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRMSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MAINT-SEQ", BPCRMSEQ);
        if (BPCRMSEQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRMSEQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSGOBL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
