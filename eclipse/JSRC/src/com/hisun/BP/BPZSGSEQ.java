package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.DB.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSGSEQ {
    DBParm BPTACOBL_RD;
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    char ERROR = 'E';
    String PGM_NAME = "BPZSGSEQ";
    String SCSOCWA = "SCSOCWA ";
    String SCSSCKDT = "SCSSCKDT";
    String SCSSCLDT = "SCSSCLDT";
    BPZSGSEQ_WS_VARIABLES WS_VARIABLES = new BPZSGSEQ_WS_VARIABLES();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCMSG_ERROR_MSG ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCRMSEQ BPCRMSEQ = new BPCRMSEQ();
    BPCRBOBL BPCRBOBL = new BPCRBOBL();
    BPRACOBL BPRACOBL = new BPRACOBL();
    BPRSEQ BPRSEQ = new BPRSEQ();
    BPROBL BPROBL = new BPROBL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCCALL_WS_SQL_VARIABLES WS_SQL_VARIABLES = new SCCCALL_WS_SQL_VARIABLES();
    int RTCD = 0;
    SCCGWA SCCGWA;
    BPCSGSEQ BPCSGSEQ;
    public void MP(SCCGWA SCCGWA, BPCSGSEQ BPCSGSEQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSGSEQ = BPCSGSEQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSGSEQ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, ERROR_MSG.BP_NORMAL, BPCSGSEQ.RC);
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, BPCRMSEQ);
        IBS.init(SCCGWA, BPCRBOBL);
        BPCRMSEQ.PTR = BPRSEQ;
        BPCRBOBL.PTR = BPROBL;
        BPCRMSEQ.LEN = 289;
        BPCRBOBL.LEN = 121;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSGSEQ.TYPE);
        CEP.TRC(SCCGWA, BPCSGSEQ.CODE);
        IBS.init(SCCGWA, BPRSEQ);
        BPRSEQ.KEY.TYPE = BPCSGSEQ.TYPE;
        BPRSEQ.KEY.NAME = BPCSGSEQ.CODE;
        BPCRMSEQ.FUNC = 'R';
        CEP.TRC(SCCGWA, BPRSEQ.KEY.TYPE);
        CEP.TRC(SCCGWA, BPRSEQ.KEY.NAME);
        S000_CALL_BPZRMSEQ();
        if (pgmRtn) return;
        if (BPCRMSEQ.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.BP_RECORD_NOTFND, BPCSGSEQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCSGSEQ.NUM);
        CEP.TRC(SCCGWA, BPRSEQ.SEQ_NO);
        CEP.TRC(SCCGWA, BPRSEQ.OLD_SEQ_NO);
        CEP.TRC(SCCGWA, BPRSEQ.FIRST_NUM);
        CEP.TRC(SCCGWA, BPRSEQ.INIT_ZERO);
        CEP.TRC(SCCGWA, BPRSEQ.STEP_NUM);
        WS_VARIABLES.WS_TEMP_VARIABLE.CHK_FLG = 'N';
        R00_CHECK_DATE();
        if (pgmRtn) return;
        if (BPCSGSEQ.NUM < 0) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.BP_INPUT_ERROR, BPCSGSEQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCSGSEQ.NUM);
        if (BPCSGSEQ.NUM > 0) {
            WS_VARIABLES.WS_TEMP_VARIABLE.TEMP_NUM = (short) (BPRSEQ.STEP_NUM * BPCSGSEQ.NUM);
        } else {
            WS_VARIABLES.WS_TEMP_VARIABLE.TEMP_NUM = (short) BPRSEQ.STEP_NUM;
        }
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE.TEMP_NUM);
        CEP.TRC(SCCGWA, BPRSEQ.SEQ_NO);
        CEP.TRC(SCCGWA, BPRSEQ.FIRST_NUM);
        if (BPRSEQ.SEQ_NO == 0 
            && BPRSEQ.FIRST_NUM != 0) {
            BPRSEQ.SEQ_NO = BPRSEQ.FIRST_NUM - BPRSEQ.STEP_NUM;
        }
        CEP.TRC(SCCGWA, BPRSEQ.SEQ_NO);
        if (BPCSGSEQ.RUN_MODE == 'O' 
            && WS_VARIABLES.WS_TEMP_VARIABLE.CHK_FLG == 'Y') {
            BPRSEQ.OLD_SEQ_NO = BPRSEQ.SEQ_NO;
            BPRSEQ.SEQ_NO = BPRSEQ.FIRST_NUM;
            BPRSEQ.VAL_DATE = BPCSGSEQ.AC_DATE;
        }
        if (BPCSGSEQ.RUN_MODE == 'B' 
            && WS_VARIABLES.WS_TEMP_VARIABLE.CHK_FLG == 'Y') {
            WS_VARIABLES.WS_TEMP_VARIABLE.SEQ_NEW = BPRSEQ.OLD_SEQ_NO;
        } else {
            WS_VARIABLES.WS_TEMP_VARIABLE.SEQ_NEW = BPRSEQ.SEQ_NO;
        }
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE.SEQ_NO);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE.SEQ_NEW);
        CEP.TRC(SCCGWA, BPRSEQ.STEP_NUM);
        WS_VARIABLES.WS_TEMP_VARIABLE.SEQ_NO = WS_VARIABLES.WS_TEMP_VARIABLE.SEQ_NEW + BPRSEQ.STEP_NUM;
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE.SEQ_NO);
        CEP.TRC(SCCGWA, "1");
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE.SEQ_NEW);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE.TEMP_NUM);
        WS_VARIABLES.WS_TEMP_VARIABLE.SEQ_NEW = WS_VARIABLES.WS_TEMP_VARIABLE.SEQ_NEW + WS_VARIABLES.WS_TEMP_VARIABLE.TEMP_NUM;
        IBS.init(SCCGWA, BPROBL);
        BPROBL.KEY.TYPE = BPRSEQ.KEY.TYPE;
        BPROBL.KEY.NAME = BPRSEQ.KEY.NAME;
        BPROBL.KEY.MIN_SEQ_NO = WS_VARIABLES.WS_TEMP_VARIABLE.SEQ_NO;
        BPROBL.MAX_SEQ_NO = WS_VARIABLES.WS_TEMP_VARIABLE.SEQ_NEW;
        B010_CHECK_OBL();
        if (pgmRtn) return;
        while (BPCRBOBL.RETURN_INFO != 'N') {
            WS_VARIABLES.WS_TEMP_VARIABLE.SEQ_NO = BPROBL.MAX_SEQ_NO + BPRSEQ.STEP_NUM;
            WS_VARIABLES.WS_TEMP_VARIABLE.SEQ_NEW = WS_VARIABLES.WS_TEMP_VARIABLE.SEQ_NEW + WS_VARIABLES.WS_TEMP_VARIABLE.TEMP_NUM;
            IBS.init(SCCGWA, BPROBL);
            BPROBL.KEY.TYPE = BPRSEQ.KEY.TYPE;
            BPROBL.KEY.NAME = BPRSEQ.KEY.NAME;
            BPROBL.KEY.MIN_SEQ_NO = WS_VARIABLES.WS_TEMP_VARIABLE.SEQ_NO;
            BPROBL.MAX_SEQ_NO = WS_VARIABLES.WS_TEMP_VARIABLE.SEQ_NEW;
            B010_CHECK_OBL();
            if (pgmRtn) return;
        }
        B011_CHECK_ACOBL();
        if (pgmRtn) return;
        while (WS_VARIABLES.WS_TEMP_VARIABLE.ACOBL_FLG != 'N') {
            WS_VARIABLES.WS_TEMP_VARIABLE.SEQ_NO = BPROBL.MAX_SEQ_NO + BPRSEQ.STEP_NUM;
            WS_VARIABLES.WS_TEMP_VARIABLE.SEQ_NEW = WS_VARIABLES.WS_TEMP_VARIABLE.SEQ_NEW + WS_VARIABLES.WS_TEMP_VARIABLE.TEMP_NUM;
            IBS.init(SCCGWA, BPROBL);
            BPROBL.KEY.TYPE = BPRSEQ.KEY.TYPE;
            BPROBL.KEY.NAME = BPRSEQ.KEY.NAME;
            BPROBL.KEY.MIN_SEQ_NO = WS_VARIABLES.WS_TEMP_VARIABLE.SEQ_NO;
            BPROBL.MAX_SEQ_NO = WS_VARIABLES.WS_TEMP_VARIABLE.SEQ_NEW;
            B011_CHECK_ACOBL();
            if (pgmRtn) return;
        }
        B020_CHECK_MAX_SEQ();
        if (pgmRtn) return;
        if (BPCSGSEQ.RUN_MODE == 'B' 
            && WS_VARIABLES.WS_TEMP_VARIABLE.CHK_FLG == 'Y') {
            BPRSEQ.OLD_SEQ_NO = WS_VARIABLES.WS_TEMP_VARIABLE.SEQ_NEW;
        } else {
            BPRSEQ.SEQ_NO = WS_VARIABLES.WS_TEMP_VARIABLE.SEQ_NEW;
        }
        BPRSEQ.LAST_UPD_DATE = BPCSGSEQ.AC_DATE;
        BPRSEQ.LAST_UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCRMSEQ.FUNC = 'U';
        CEP.TRC(SCCGWA, BPRSEQ.KEY.TYPE);
        CEP.TRC(SCCGWA, BPRSEQ.KEY.NAME);
        S000_CALL_BPZRMSEQ();
        if (pgmRtn) return;
        BPCSGSEQ.SEQ = WS_VARIABLES.WS_TEMP_VARIABLE.SEQ_NO;
        BPCSGSEQ.STEP_NUM = (short) BPRSEQ.STEP_NUM;
        CEP.TRC(SCCGWA, "END BPZSGSEQ");
    }
    public void B010_CHECK_OBL() throws IOException,SQLException,Exception {
        if (BPRSEQ.OBL_FLG == 'Y') {
            BPCRBOBL.FUN = 'P';
            S000_CALL_BPZRBOBL();
            if (pgmRtn) return;
            BPCRBOBL.FUN = 'R';
            S000_CALL_BPZRBOBL();
            if (pgmRtn) return;
            BPCRBOBL.FUN = 'E';
            S000_CALL_BPZRBOBL();
            if (pgmRtn) return;
        } else {
            BPCRBOBL.RETURN_INFO = 'N';
        }
    }
    public void B011_CHECK_ACOBL() throws IOException,SQLException,Exception {
        WS_VARIABLES.WS_TEMP_VARIABLE.ACOBL_FLG = 'N';
        if (BPRSEQ.OBL_FLG == 'Y') {
            IBS.init(SCCGWA, BPRACOBL);
            WS_SQL_VARIABLES.ACOBL_MINSEQ = WS_VARIABLES.WS_TEMP_VARIABLE.SEQ_NO;
            WS_SQL_VARIABLES.ACOBL_MAXSEQ = WS_VARIABLES.WS_TEMP_VARIABLE.SEQ_NEW;
            T000_READ_BPTACOBL_FIRST();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTACOBL_FIRST() throws IOException,SQLException,Exception {
        BPTACOBL_RD = new DBParm();
        BPTACOBL_RD.TableName = "BPTACOBL";
        BPTACOBL_RD.where = "AC_NO_SEQ >= :WS_SQL_VARIABLES.ACOBL_MINSEQ "
            + "AND AC_NO_SEQ <= :WS_SQL_VARIABLES.ACOBL_MAXSEQ";
        BPTACOBL_RD.fst = true;
        IBS.READ(SCCGWA, BPRACOBL, this, BPTACOBL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_VARIABLES.WS_TEMP_VARIABLE.ACOBL_FLG = 'Y';
        } else {
            WS_VARIABLES.WS_TEMP_VARIABLE.ACOBL_FLG = 'N';
        }
    }
    public void B020_CHECK_MAX_SEQ() throws IOException,SQLException,Exception {
        if (WS_VARIABLES.WS_TEMP_VARIABLE.SEQ_NEW > BPRSEQ.MAX_SEQ_NO) {
            if (BPRSEQ.MAX_FLG != 'E') {
                WS_VARIABLES.WS_TEMP_VARIABLE.SEQ_NO = BPRSEQ.FIRST_NUM;
                if (BPCSGSEQ.NUM > 0) {
                    WS_VARIABLES.WS_TEMP_VARIABLE.SEQ_NEW = BPRSEQ.FIRST_NUM + WS_VARIABLES.WS_TEMP_VARIABLE.TEMP_NUM;
                } else {
                    WS_VARIABLES.WS_TEMP_VARIABLE.SEQ_NEW = BPRSEQ.FIRST_NUM;
                }
                if (BPRSEQ.FIRST_NUM == 0) {
                    WS_VARIABLES.WS_TEMP_VARIABLE.SEQ_NO = WS_VARIABLES.WS_TEMP_VARIABLE.SEQ_NO + BPRSEQ.STEP_NUM;
                    WS_VARIABLES.WS_TEMP_VARIABLE.SEQ_NEW = WS_VARIABLES.WS_TEMP_VARIABLE.SEQ_NEW + BPRSEQ.STEP_NUM;
                }
            } else {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.BP_SEQ_OVER_MAX, BPCSGSEQ.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (WS_VARIABLES.WS_TEMP_VARIABLE.SEQ_NEW >= BPRSEQ.WARN_SEQ_NO) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.BP_SEQ_OVER_WARN, BPCSGSEQ.RC);
        }
    }
    public void R00_CHECK_DATE() throws IOException,SQLException,Exception {
        WS_VARIABLES.WS_TEMP_VARIABLE.VAL_DATE = BPRSEQ.VAL_DATE;
        CEP.TRC(SCCGWA, BPRSEQ.INIT_ZERO);
        CEP.TRC(SCCGWA, BPRSEQ.VAL_DATE);
        CEP.TRC(SCCGWA, BPCSGSEQ.AC_DATE);
        if (BPRSEQ.INIT_ZERO == 'D') {
            if (BPCSGSEQ.AC_DATE != BPRSEQ.VAL_DATE) {
                WS_VARIABLES.WS_TEMP_VARIABLE.CHK_FLG = 'Y';
            }
        } else if (BPRSEQ.INIT_ZERO == 'W') {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = BPRSEQ.VAL_DATE;
            S000_CALL_SCSSCKDT();
            if (pgmRtn) return;
            WS_VARIABLES.WS_TEMP_VARIABLE.WEEK_NO = 0;
            if (SCCCKDT.WEEK_NO == 6) {
                if (BPRSEQ.VAL_DATE != BPCSGSEQ.AC_DATE) {
                    WS_VARIABLES.WS_TEMP_VARIABLE.CHK_FLG = 'Y';
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
                if (BPCSGSEQ.AC_DATE > SCCCLDT.DATE2) {
                    WS_VARIABLES.WS_TEMP_VARIABLE.CHK_FLG = 'Y';
                }
            }
        } else if (BPRSEQ.INIT_ZERO == 'M') {
            JIBS_tmp_str[0] = "" + BPCSGSEQ.AC_DATE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            JIBS_tmp_str[1] = "" + WS_VARIABLES.WS_TEMP_VARIABLE.VAL_DATE;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            if (!JIBS_tmp_str[0].substring(0, 6).equalsIgnoreCase(JIBS_tmp_str[1].substring(0, 6))) {
                WS_VARIABLES.WS_TEMP_VARIABLE.CHK_FLG = 'Y';
            }
        } else if (BPRSEQ.INIT_ZERO == 'Q') {
            JIBS_tmp_str[0] = "" + BPCSGSEQ.AC_DATE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            JIBS_tmp_str[1] = "" + WS_VARIABLES.WS_TEMP_VARIABLE.VAL_DATE;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            if (JIBS_tmp_str[0].substring(0, 4).equalsIgnoreCase(JIBS_tmp_str[1].substring(0, 4))) {
                JIBS_tmp_str[0] = "" + WS_VARIABLES.WS_TEMP_VARIABLE.VAL_DATE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                JIBS_tmp_str[1] = "" + BPCSGSEQ.AC_DATE;
                JIBS_tmp_int = JIBS_tmp_str[1].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
                if ((JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("1") 
                    || JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("2") 
                    || JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("3")) 
                    && (!JIBS_tmp_str[1].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("1") 
                    && !JIBS_tmp_str[1].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("2") 
                    && !JIBS_tmp_str[1].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("3"))) {
                    WS_VARIABLES.WS_TEMP_VARIABLE.CHK_FLG = 'Y';
                }
                JIBS_tmp_str[0] = "" + WS_VARIABLES.WS_TEMP_VARIABLE.VAL_DATE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                JIBS_tmp_str[1] = "" + BPCSGSEQ.AC_DATE;
                JIBS_tmp_int = JIBS_tmp_str[1].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
                if ((JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("4") 
                    || JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("5") 
                    || JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("6")) 
                    && (!JIBS_tmp_str[1].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("4") 
                    && !JIBS_tmp_str[1].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("5") 
                    && !JIBS_tmp_str[1].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("6"))) {
                    WS_VARIABLES.WS_TEMP_VARIABLE.CHK_FLG = 'Y';
                }
                JIBS_tmp_str[0] = "" + WS_VARIABLES.WS_TEMP_VARIABLE.VAL_DATE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                JIBS_tmp_str[1] = "" + BPCSGSEQ.AC_DATE;
                JIBS_tmp_int = JIBS_tmp_str[1].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
                if ((JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("7") 
                    || JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("8") 
                    || JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("9")) 
                    && (!JIBS_tmp_str[1].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("7") 
                    && !JIBS_tmp_str[1].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("8") 
                    && !JIBS_tmp_str[1].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("9"))) {
                    WS_VARIABLES.WS_TEMP_VARIABLE.CHK_FLG = 'Y';
                }
                JIBS_tmp_str[0] = "" + WS_VARIABLES.WS_TEMP_VARIABLE.VAL_DATE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                JIBS_tmp_str[1] = "" + BPCSGSEQ.AC_DATE;
                JIBS_tmp_int = JIBS_tmp_str[1].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
                if ((JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("10") 
                    || JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("11") 
                    || JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("12")) 
                    && (!JIBS_tmp_str[1].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("10") 
                    && !JIBS_tmp_str[1].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("11") 
                    && !JIBS_tmp_str[1].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("12"))) {
                    WS_VARIABLES.WS_TEMP_VARIABLE.CHK_FLG = 'Y';
                }
            } else {
                WS_VARIABLES.WS_TEMP_VARIABLE.CHK_FLG = 'Y';
            }
        } else if (BPRSEQ.INIT_ZERO == 'Y') {
            JIBS_tmp_str[0] = "" + BPCSGSEQ.AC_DATE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            JIBS_tmp_str[1] = "" + WS_VARIABLES.WS_TEMP_VARIABLE.VAL_DATE;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            if (!JIBS_tmp_str[0].substring(0, 4).equalsIgnoreCase(JIBS_tmp_str[1].substring(0, 4))) {
                WS_VARIABLES.WS_TEMP_VARIABLE.CHK_FLG = 'Y';
            }
        } else {
        }
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE.CHK_FLG);
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
        if (BPCRMSEQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRMSEQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSGSEQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRBOBL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-BRW-OBL", BPCRBOBL);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
