package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFCTRB {
    int JIBS_tmp_int;
    brParm BPTFCTR_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    BPZFCTRB_WS_LINE WS_LINE = new BPZFCTRB_WS_LINE();
    int WS_REC_CNT = 0;
    String WS_ERR_MSG = " ";
    int WS_COUNT = 0;
    String WS_LOW_CTRT_NO = " ";
    String WS_HIGH_CTRT_NO = " ";
    String WS_LOW_FEE_TYPE = " ";
    String WS_HIGH_FEE_TYPE = " ";
    String WS_LOW_REL_CTRT = " ";
    String WS_HIGH_REL_CTRT = " ";
    String WS_LOW_CI_NO = " ";
    String WS_HIGH_CI_NO = " ";
    int WS_LOW_START_DATE = 0;
    int WS_HIGH_START_DATE = 0;
    int WS_LOW_MATURITY_DATE = 0;
    int WS_HIGH_MATURITY_DATE = 0;
    char WS_LOW_FEE_STATUS = ' ';
    char WS_HIGH_FEE_STATUS = ' ';
    char WS_BRW_FLAG = ' ';
    char WS_FEECD_FLG = ' ';
    char WS_RELCTRT_FLG = ' ';
    char WS_CINO_FLG = ' ';
    char WS_STS_FLG = ' ';
    char WS_DATE_FLG = ' ';
    char WS_STARTBR_FLG = ' ';
    BPRFCTR BPRFCTR = new BPRFCTR();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CICCUST CICCUST = new CICCUST();
    SCCCTLM_MSG SCCCTLM_MSG = new SCCCTLM_MSG();
    SCCGWA SCCGWA;
    BPCFCTRB BPCFCTRB;
    public void MP(SCCGWA SCCGWA, BPCFCTRB BPCFCTRB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFCTRB = BPCFCTRB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFCTRB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "IIIIIIIIII");
        CEP.TRC(SCCGWA, BPCFCTRB.CTRT_NO);
        CEP.TRC(SCCGWA, BPCFCTRB.FEE_TYPE);
        CEP.TRC(SCCGWA, BPCFCTRB.REL_CTRT);
        CEP.TRC(SCCGWA, BPCFCTRB.CI_NO);
        CEP.TRC(SCCGWA, BPCFCTRB.START_DATE);
        CEP.TRC(SCCGWA, BPCFCTRB.MATURITY_DATE);
        CEP.TRC(SCCGWA, BPCFCTRB.FEE_STATUS);
        B100_MPAG_START();
        if (pgmRtn) return;
        B200_STARTBR();
        if (pgmRtn) return;
        B300_OUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_MPAG_START() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 348;
        SCCMPAG.SCR_ROW_CNT = 50;
        SCCMPAG.SCR_COL_CNT = 100;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B200_STARTBR() throws IOException,SQLException,Exception {
        BPRFCTR.KEY.CTRT_NO = BPCFCTRB.CTRT_NO;
        BPRFCTR.FEE_TYPE = BPCFCTRB.FEE_TYPE;
        BPRFCTR.REL_CTRT_NO = BPCFCTRB.REL_CTRT;
        BPRFCTR.CI_NO = BPCFCTRB.CI_NO;
        BPRFCTR.START_DATE = BPCFCTRB.START_DATE;
        BPRFCTR.MATURITY_DATE = BPCFCTRB.MATURITY_DATE;
        BPRFCTR.FEE_STATUS = BPCFCTRB.FEE_STATUS;
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            T000_STARTBR_BPTFCTR_CN();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_STARTBR_FLG);
            if (WS_STARTBR_FLG == '4') {
                T000_READNEXT_BPTFCTR_4();
                if (pgmRtn) return;
            } else if (WS_STARTBR_FLG == '5') {
                T000_READNEXT_BPTFCTR_5();
                if (pgmRtn) return;
            } else if (WS_STARTBR_FLG == '6') {
                T000_READNEXT_BPTFCTR_6();
                if (pgmRtn) return;
            } else {
                T000_READNEXT_BPTFCTR();
                if (pgmRtn) return;
            }
        } else {
            T000_STARTBR_BPTFCTR();
            if (pgmRtn) return;
            T000_READNEXT_BPTFCTR();
            if (pgmRtn) return;
        }
    }
    public void B300_OUT_PROCESS() throws IOException,SQLException,Exception {
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && SCCMPAG.FUNC != 'E') {
            WS_FEECD_FLG = 'N';
            WS_RELCTRT_FLG = 'N';
            WS_CINO_FLG = 'N';
            WS_STS_FLG = 'N';
            WS_DATE_FLG = 'N';
            if (BPCFCTRB.FEE_TYPE.trim().length() > 0) {
                if (BPCFCTRB.FEE_TYPE.equalsIgnoreCase(BPRFCTR.FEE_TYPE)) {
                    CEP.TRC(SCCGWA, "FEE CODE MATCH");
                    WS_FEECD_FLG = 'Y';
                }
            } else {
                CEP.TRC(SCCGWA, "FEE CODE SPACE");
                WS_FEECD_FLG = 'Y';
            }
            CEP.TRC(SCCGWA, BPCFCTRB.FEE_TYPE);
            CEP.TRC(SCCGWA, BPRFCTR.FEE_TYPE);
            if (BPCFCTRB.REL_CTRT.trim().length() > 0) {
                if (BPCFCTRB.REL_CTRT.equalsIgnoreCase(BPRFCTR.REL_CTRT_NO)) {
                    CEP.TRC(SCCGWA, "REL CTRT MATCH");
                    WS_RELCTRT_FLG = 'Y';
                }
            } else {
                CEP.TRC(SCCGWA, "REL CTRT SPACE");
                WS_RELCTRT_FLG = 'Y';
            }
            CEP.TRC(SCCGWA, BPCFCTRB.REL_CTRT);
            CEP.TRC(SCCGWA, BPRFCTR.REL_CTRT_NO);
            if (BPCFCTRB.CI_NO.trim().length() > 0) {
                if (BPCFCTRB.CI_NO.equalsIgnoreCase(BPRFCTR.CI_NO)) {
                    CEP.TRC(SCCGWA, "CI NO MATCH");
                    WS_CINO_FLG = 'Y';
                }
            } else {
                CEP.TRC(SCCGWA, "CI NO SPACE");
                WS_CINO_FLG = 'Y';
            }
            CEP.TRC(SCCGWA, BPCFCTRB.CI_NO);
            CEP.TRC(SCCGWA, BPRFCTR.CI_NO);
            if (BPCFCTRB.FEE_STATUS != ' ') {
                if (BPCFCTRB.FEE_STATUS == BPRFCTR.FEE_STATUS) {
                    CEP.TRC(SCCGWA, "FEE STATUS MATCH");
                    WS_STS_FLG = 'Y';
                }
            } else {
                CEP.TRC(SCCGWA, "FEE STATUS SPACE");
                WS_STS_FLG = 'Y';
            }
            CEP.TRC(SCCGWA, BPCFCTRB.FEE_STATUS);
            CEP.TRC(SCCGWA, BPRFCTR.FEE_STATUS);
            CEP.TRC(SCCGWA, WS_STARTBR_FLG);
            CEP.TRC(SCCGWA, BPRFCTR.START_DATE);
            CEP.TRC(SCCGWA, BPCFCTRB.START_DATE);
            CEP.TRC(SCCGWA, BPRFCTR.MATURITY_DATE);
            CEP.TRC(SCCGWA, BPCFCTRB.MATURITY_DATE);
            if (WS_STARTBR_FLG == '6') {
                if (BPRFCTR.START_DATE >= BPCFCTRB.START_DATE 
                    && BPRFCTR.MATURITY_DATE <= BPCFCTRB.MATURITY_DATE) {
                    WS_DATE_FLG = 'Y';
                }
            } else {
                WS_DATE_FLG = 'Y';
            }
            CEP.TRC(SCCGWA, BPCFCTRB.CTRT_NO);
            CEP.TRC(SCCGWA, WS_FEECD_FLG);
            CEP.TRC(SCCGWA, WS_RELCTRT_FLG);
            CEP.TRC(SCCGWA, WS_CINO_FLG);
            CEP.TRC(SCCGWA, WS_STS_FLG);
            if (BPCFCTRB.CTRT_NO.trim().length() > 0 
                || (WS_FEECD_FLG == 'Y' 
                && WS_RELCTRT_FLG == 'Y' 
                && WS_CINO_FLG == 'Y' 
                && WS_STS_FLG == 'Y' 
                && WS_DATE_FLG == 'Y')) {
                IBS.init(SCCGWA, WS_LINE);
                WS_LINE.WS_LINE_CTRT_NO = BPRFCTR.KEY.CTRT_NO;
                WS_LINE.WS_LINE_FEE_TYPE = BPRFCTR.FEE_TYPE;
                WS_LINE.WS_LINE_REL_CTRT_NO = BPRFCTR.REL_CTRT_NO;
                WS_LINE.WS_LINE_CI_NO = BPRFCTR.CI_NO;
                R000_GET_CI_ABBR_NAME();
                if (pgmRtn) return;
                WS_LINE.WS_LINE_START_DATE = BPRFCTR.START_DATE;
                WS_LINE.WS_LINE_MATURITY_DATE = BPRFCTR.MATURITY_DATE;
                WS_LINE.WS_LINE_FEE_STATUS = BPRFCTR.FEE_STATUS;
                CEP.TRC(SCCGWA, WS_LINE.WS_LINE_CTRT_NO);
                CEP.TRC(SCCGWA, WS_LINE.WS_LINE_FEE_TYPE);
                CEP.TRC(SCCGWA, WS_LINE.WS_LINE_REL_CTRT_NO);
                CEP.TRC(SCCGWA, WS_LINE.WS_LINE_CI_NO);
                CEP.TRC(SCCGWA, WS_LINE.WS_LINE_CI_ABBR_NM);
                CEP.TRC(SCCGWA, WS_LINE.WS_LINE_START_DATE);
                CEP.TRC(SCCGWA, WS_LINE.WS_LINE_MATURITY_DATE);
                CEP.TRC(SCCGWA, WS_LINE.WS_LINE_FEE_STATUS);
                IBS.init(SCCGWA, SCCMPAG);
                SCCMPAG.FUNC = 'D';
                SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_LINE);
                SCCMPAG.DATA_LEN = 348;
                CEP.TRC(SCCGWA, SCCMPAG.DATA_LEN);
                B_MPAG();
                if (pgmRtn) return;
                WS_REC_CNT += 1;
            }
            CEP.TRC(SCCGWA, WS_STARTBR_FLG);
            if (WS_STARTBR_FLG == '4') {
                T000_READNEXT_BPTFCTR_4();
                if (pgmRtn) return;
            } else if (WS_STARTBR_FLG == '5') {
                T000_READNEXT_BPTFCTR_5();
                if (pgmRtn) return;
            } else if (WS_STARTBR_FLG == '6') {
                T000_READNEXT_BPTFCTR_6();
                if (pgmRtn) return;
            } else {
                T000_READNEXT_BPTFCTR();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, WS_STARTBR_FLG);
        if (WS_STARTBR_FLG == '4') {
            T000_ENDBR_BPTFCTR_4();
            if (pgmRtn) return;
        } else if (WS_STARTBR_FLG == '5') {
            T000_ENDBR_BPTFCTR_5();
            if (pgmRtn) return;
        } else if (WS_STARTBR_FLG == '6') {
            T000_ENDBR_BPTFCTR_6();
            if (pgmRtn) return;
        } else {
            T000_ENDBR_BPTFCTR();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_REC_CNT);
        if (WS_REC_CNT == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_REC_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_REC_CNT > 3500) {
            WS_ERR_MSG = SCCCTLM_MSG.SC_ERR_ROW_LIMIT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_GET_CI_ABBR_NAME() throws IOException,SQLException,Exception {
        if (BPRFCTR.CI_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CICCUST);
            CICCUST.DATA.CI_NO = BPRFCTR.CI_NO;
            CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            if (CICCUST.O_DATA.O_CI_NM.trim().length() > 0) {
                WS_LINE.WS_LINE_CI_ABBR_NM = CICCUST.O_DATA.O_CI_NM;
            } else {
                WS_LINE.WS_LINE_CI_ABBR_NM = CICCUST.O_DATA.O_CI_ENM;
            }
        }
    }
    public void T000_STARTBR_BPTFCTR() throws IOException,SQLException,Exception {
        if (BPRFCTR.KEY.CTRT_NO.trim().length() == 0) {
            WS_LOW_CTRT_NO = "" + 0X00;
            JIBS_tmp_int = WS_LOW_CTRT_NO.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) WS_LOW_CTRT_NO = "0" + WS_LOW_CTRT_NO;
            WS_HIGH_CTRT_NO = "" + 0XFF;
            JIBS_tmp_int = WS_HIGH_CTRT_NO.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) WS_HIGH_CTRT_NO = "0" + WS_HIGH_CTRT_NO;
        } else {
            WS_LOW_CTRT_NO = BPRFCTR.KEY.CTRT_NO;
            WS_HIGH_CTRT_NO = BPRFCTR.KEY.CTRT_NO;
        }
        if (BPRFCTR.FEE_TYPE.trim().length() == 0) {
            WS_LOW_FEE_TYPE = "" + 0X00;
            JIBS_tmp_int = WS_LOW_FEE_TYPE.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) WS_LOW_FEE_TYPE = "0" + WS_LOW_FEE_TYPE;
            WS_HIGH_FEE_TYPE = "" + 0XFF;
            JIBS_tmp_int = WS_HIGH_FEE_TYPE.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) WS_HIGH_FEE_TYPE = "0" + WS_HIGH_FEE_TYPE;
        } else {
            WS_LOW_FEE_TYPE = BPRFCTR.FEE_TYPE;
            WS_HIGH_FEE_TYPE = BPRFCTR.FEE_TYPE;
        }
        if (BPRFCTR.START_DATE == 0) {
            WS_LOW_START_DATE = 19001231;
            WS_HIGH_START_DATE = 99991231;
        } else {
            WS_LOW_START_DATE = BPRFCTR.START_DATE;
            WS_HIGH_START_DATE = BPRFCTR.START_DATE;
        }
        if (BPRFCTR.MATURITY_DATE == 0) {
            WS_LOW_MATURITY_DATE = 19001231;
            WS_HIGH_MATURITY_DATE = 99991231;
        } else {
            WS_LOW_MATURITY_DATE = BPRFCTR.MATURITY_DATE;
            WS_HIGH_MATURITY_DATE = BPRFCTR.MATURITY_DATE;
        }
        if (BPRFCTR.REL_CTRT_NO.trim().length() == 0) {
            WS_LOW_REL_CTRT = "" + 0X00;
            JIBS_tmp_int = WS_LOW_REL_CTRT.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) WS_LOW_REL_CTRT = "0" + WS_LOW_REL_CTRT;
            WS_HIGH_REL_CTRT = "" + 0XFF;
            JIBS_tmp_int = WS_HIGH_REL_CTRT.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) WS_HIGH_REL_CTRT = "0" + WS_HIGH_REL_CTRT;
        } else {
            WS_LOW_REL_CTRT = BPRFCTR.REL_CTRT_NO;
            WS_HIGH_REL_CTRT = BPRFCTR.REL_CTRT_NO;
        }
        if (BPRFCTR.CI_NO.trim().length() == 0) {
            WS_LOW_CI_NO = "" + 0X00;
            JIBS_tmp_int = WS_LOW_CI_NO.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) WS_LOW_CI_NO = "0" + WS_LOW_CI_NO;
            WS_HIGH_CI_NO = "" + 0XFF;
            JIBS_tmp_int = WS_HIGH_CI_NO.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) WS_HIGH_CI_NO = "0" + WS_HIGH_CI_NO;
        } else {
            WS_LOW_CI_NO = BPRFCTR.CI_NO;
            WS_HIGH_CI_NO = BPRFCTR.CI_NO;
        }
        if (BPRFCTR.FEE_STATUS == ' ') {
            WS_LOW_FEE_STATUS = 0X00;
            WS_HIGH_FEE_STATUS = 0XFF;
        } else {
            WS_LOW_FEE_STATUS = BPRFCTR.FEE_STATUS;
            WS_HIGH_FEE_STATUS = BPRFCTR.FEE_STATUS;
        }
        BPTFCTR_BR.rp = new DBParm();
        BPTFCTR_BR.rp.TableName = "BPTFCTR";
        BPTFCTR_BR.rp.where = "( CTRT_NO BETWEEN :WS_LOW_CTRT_NO "
            + "AND :WS_HIGH_CTRT_NO ) "
            + "AND ( FEE_TYPE BETWEEN :WS_LOW_FEE_TYPE "
            + "AND :WS_HIGH_FEE_TYPE ) "
            + "AND ( START_DATE BETWEEN :WS_LOW_START_DATE "
            + "AND :WS_HIGH_START_DATE ) "
            + "AND ( MATURITY_DATE BETWEEN :WS_LOW_MATURITY_DATE "
            + "AND :WS_HIGH_MATURITY_DATE ) "
            + "AND ( REL_CTRT_NO BETWEEN :WS_LOW_REL_CTRT "
            + "AND :WS_HIGH_REL_CTRT ) "
            + "AND ( CI_NO BETWEEN :WS_LOW_CI_NO "
            + "AND :WS_HIGH_CI_NO ) "
            + "AND ( FEE_STATUS BETWEEN :WS_LOW_FEE_STATUS "
            + "AND :WS_HIGH_FEE_STATUS )";
        BPTFCTR_BR.rp.order = "CTRT_NO";
        IBS.STARTBR(SCCGWA, BPRFCTR, this, BPTFCTR_BR);
    }
    public void T000_STARTBR_BPTFCTR_CN() throws IOException,SQLException,Exception {
        if (BPRFCTR.START_DATE == 0) {
            WS_LOW_START_DATE = SCCGWA.COMM_AREA.AC_DATE;
            WS_LOW_MATURITY_DATE = SCCGWA.COMM_AREA.AC_DATE;
        } else {
            WS_LOW_START_DATE = BPRFCTR.START_DATE;
            WS_LOW_MATURITY_DATE = BPRFCTR.START_DATE;
        }
        if (BPRFCTR.MATURITY_DATE == 0) {
            WS_HIGH_MATURITY_DATE = WS_LOW_START_DATE;
            WS_HIGH_START_DATE = WS_LOW_START_DATE;
        } else {
            WS_HIGH_MATURITY_DATE = BPRFCTR.MATURITY_DATE;
            WS_HIGH_START_DATE = BPRFCTR.MATURITY_DATE;
        }
        CEP.TRC(SCCGWA, WS_LOW_START_DATE);
        CEP.TRC(SCCGWA, WS_HIGH_START_DATE);
        CEP.TRC(SCCGWA, WS_LOW_MATURITY_DATE);
        CEP.TRC(SCCGWA, WS_HIGH_MATURITY_DATE);
        WS_BRW_FLAG = 'N';
        if (BPRFCTR.KEY.CTRT_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, "BROWSE BY CTRT-NO");
            WS_BRW_FLAG = 'Y';
            T000_STARTBR_BY_CTRT_NO();
            if (pgmRtn) return;
        }
        if (WS_BRW_FLAG == 'N' 
            && BPRFCTR.CI_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, "BROWSE BY CI NO");
            WS_BRW_FLAG = 'Y';
            T000_STARTBR_BY_CI_NO();
            if (pgmRtn) return;
        }
        if (WS_BRW_FLAG == 'N' 
            && BPRFCTR.REL_CTRT_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, "BROWSE BY REL-CTRT-NO");
            WS_BRW_FLAG = 'Y';
            T000_STARTBR_BY_REL_CTRT();
            if (pgmRtn) return;
        }
        if (WS_BRW_FLAG == 'N' 
            && BPRFCTR.FEE_TYPE.trim().length() > 0) {
            if (BPRFCTR.FEE_STATUS != ' ') {
                CEP.TRC(SCCGWA, "BROWSE BY FEE CODE AND STATUS");
                WS_BRW_FLAG = 'Y';
                T000_STARTBR_BY_TYPE_STS();
                if (pgmRtn) return;
                WS_STARTBR_FLG = '6';
            } else {
                CEP.TRC(SCCGWA, "BROWSE BY FEE CODE");
                WS_BRW_FLAG = 'Y';
                T000_STARTBR_BY_FEE_TYPE();
                if (pgmRtn) return;
                WS_STARTBR_FLG = '4';
            }
        }
        if (WS_BRW_FLAG == 'N' 
            && BPRFCTR.FEE_STATUS != ' ') {
            CEP.TRC(SCCGWA, "BROWSE BY FEE STATUS");
            WS_BRW_FLAG = 'Y';
            T000_STARTBR_BY_FEE_STS();
            if (pgmRtn) return;
            WS_STARTBR_FLG = '5';
        }
        if (WS_BRW_FLAG == 'N') {
            WS_BRW_FLAG = 'Y';
            T000_STARTBR_BY_DATE();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BY_CTRT_NO() throws IOException,SQLException,Exception {
        BPTFCTR_BR.rp = new DBParm();
        BPTFCTR_BR.rp.TableName = "BPTFCTR";
        BPTFCTR_BR.rp.col = "CTRT_NO,REL_CTRT_NO,FEE_TYPE,CI_NO, START_DATE,MATURITY_DATE,FEE_STATUS";
        BPTFCTR_BR.rp.where = "CTRT_NO = :BPRFCTR.KEY.CTRT_NO";
        IBS.STARTBR(SCCGWA, BPRFCTR, this, BPTFCTR_BR);
    }
    public void T000_STARTBR_BY_CI_NO() throws IOException,SQLException,Exception {
        BPTFCTR_BR.rp = new DBParm();
        BPTFCTR_BR.rp.TableName = "BPTFCTR";
        BPTFCTR_BR.rp.col = "CTRT_NO,REL_CTRT_NO,FEE_TYPE,CI_NO, START_DATE,MATURITY_DATE,FEE_STATUS";
        BPTFCTR_BR.rp.where = "CI_NO = :BPRFCTR.CI_NO "
            + "AND ( START_DATE BETWEEN :WS_LOW_START_DATE "
            + "AND :WS_HIGH_START_DATE ) "
            + "AND ( MATURITY_DATE BETWEEN :WS_LOW_MATURITY_DATE "
            + "AND :WS_HIGH_MATURITY_DATE )";
        IBS.STARTBR(SCCGWA, BPRFCTR, this, BPTFCTR_BR);
    }
    public void T000_STARTBR_BY_REL_CTRT() throws IOException,SQLException,Exception {
        BPTFCTR_BR.rp = new DBParm();
        BPTFCTR_BR.rp.TableName = "BPTFCTR";
        BPTFCTR_BR.rp.col = "CTRT_NO,REL_CTRT_NO,FEE_TYPE,CI_NO, START_DATE,MATURITY_DATE,FEE_STATUS";
        BPTFCTR_BR.rp.where = "REL_CTRT_NO = :BPRFCTR.REL_CTRT_NO "
            + "AND ( START_DATE BETWEEN :WS_LOW_START_DATE "
            + "AND :WS_HIGH_START_DATE ) "
            + "AND ( MATURITY_DATE BETWEEN :WS_LOW_MATURITY_DATE "
            + "AND :WS_HIGH_MATURITY_DATE )";
        IBS.STARTBR(SCCGWA, BPRFCTR, this, BPTFCTR_BR);
    }
    public void T000_STARTBR_BY_FEE_TYPE() throws IOException,SQLException,Exception {
        BPTFCTR_BR.rp = new DBParm();
        BPTFCTR_BR.rp.TableName = "BPTFCTR";
        BPTFCTR_BR.rp.col = "CTRT_NO,REL_CTRT_NO,FEE_TYPE,CI_NO, START_DATE,MATURITY_DATE,FEE_STATUS";
        BPTFCTR_BR.rp.where = "FEE_TYPE = :BPRFCTR.FEE_TYPE "
            + "AND ( START_DATE BETWEEN :WS_LOW_START_DATE "
            + "AND :WS_HIGH_START_DATE ) "
            + "AND ( MATURITY_DATE BETWEEN :WS_LOW_MATURITY_DATE "
            + "AND :WS_HIGH_MATURITY_DATE )";
        BPTFCTR_BR.rp.Reqid = 1;
        IBS.STARTBR(SCCGWA, BPRFCTR, this, BPTFCTR_BR);
    }
    public void T000_STARTBR_BY_FEE_STS() throws IOException,SQLException,Exception {
        BPTFCTR_BR.rp = new DBParm();
        BPTFCTR_BR.rp.TableName = "BPTFCTR";
        BPTFCTR_BR.rp.col = "CTRT_NO,REL_CTRT_NO,FEE_TYPE,CI_NO, START_DATE,MATURITY_DATE,FEE_STATUS";
        BPTFCTR_BR.rp.where = "FEE_STATUS = :BPRFCTR.FEE_STATUS "
            + "AND ( START_DATE BETWEEN :WS_LOW_START_DATE "
            + "AND :WS_HIGH_START_DATE ) "
            + "AND ( MATURITY_DATE BETWEEN :WS_LOW_MATURITY_DATE "
            + "AND :WS_HIGH_MATURITY_DATE )";
        BPTFCTR_BR.rp.Reqid = 2;
        IBS.STARTBR(SCCGWA, BPRFCTR, this, BPTFCTR_BR);
    }
    public void T000_STARTBR_BY_TYPE_STS() throws IOException,SQLException,Exception {
        BPTFCTR_BR.rp = new DBParm();
        BPTFCTR_BR.rp.TableName = "BPTFCTR";
        BPTFCTR_BR.rp.col = "CTRT_NO,REL_CTRT_NO,FEE_TYPE,CI_NO, START_DATE,MATURITY_DATE,FEE_STATUS";
        BPTFCTR_BR.rp.where = "FEE_STATUS = :BPRFCTR.FEE_STATUS "
            + "AND FEE_TYPE = :BPRFCTR.FEE_TYPE";
        BPTFCTR_BR.rp.Reqid = 3;
        IBS.STARTBR(SCCGWA, BPRFCTR, this, BPTFCTR_BR);
    }
    public void T000_STARTBR_BY_DATE() throws IOException,SQLException,Exception {
        BPTFCTR_BR.rp = new DBParm();
        BPTFCTR_BR.rp.TableName = "BPTFCTR";
        BPTFCTR_BR.rp.col = "CTRT_NO,REL_CTRT_NO,FEE_TYPE,CI_NO, START_DATE,MATURITY_DATE,FEE_STATUS";
        BPTFCTR_BR.rp.where = "( START_DATE BETWEEN :WS_LOW_START_DATE "
            + "AND :WS_HIGH_START_DATE ) "
            + "AND ( MATURITY_DATE BETWEEN :WS_LOW_MATURITY_DATE "
            + "AND :WS_HIGH_MATURITY_DATE )";
        BPTFCTR_BR.rp.order = "START_DATE";
        IBS.STARTBR(SCCGWA, BPRFCTR, this, BPTFCTR_BR);
    }
    public void T000_READNEXT_BPTFCTR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRFCTR, this, BPTFCTR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "GWA-DBIO-NORMAL");
            BPCFCTRB.DB2_RC = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "GWA-DBIO-NOTFND");
            BPCFCTRB.DB2_RC = 'N';
        } else {
        }
    }
    public void T000_READNEXT_BPTFCTR_4() throws IOException,SQLException,Exception {
        BPTFCTR_BR.rp.Reqid = 1;
        IBS.READNEXT(SCCGWA, BPRFCTR, this, BPTFCTR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "GWA-DBIO-NORMAL");
            BPCFCTRB.DB2_RC = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "GWA-DBIO-NOTFND");
            BPCFCTRB.DB2_RC = 'N';
        } else {
        }
    }
    public void T000_READNEXT_BPTFCTR_5() throws IOException,SQLException,Exception {
        WS_COUNT += 1;
        CEP.TRC(SCCGWA, WS_COUNT);
        BPTFCTR_BR.rp.Reqid = 2;
        IBS.READNEXT(SCCGWA, BPRFCTR, this, BPTFCTR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "GWA-DBIO-NORMAL");
            BPCFCTRB.DB2_RC = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "GWA-DBIO-NOTFND");
            BPCFCTRB.DB2_RC = 'N';
        } else {
        }
    }
    public void T000_READNEXT_BPTFCTR_6() throws IOException,SQLException,Exception {
        WS_COUNT += 1;
        CEP.TRC(SCCGWA, WS_COUNT);
        BPTFCTR_BR.rp.Reqid = 3;
        IBS.READNEXT(SCCGWA, BPRFCTR, this, BPTFCTR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "GWA-DBIO-NORMAL");
            BPCFCTRB.DB2_RC = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "GWA-DBIO-NOTFND");
            BPCFCTRB.DB2_RC = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTFCTR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTFCTR_BR);
    }
    public void T000_ENDBR_BPTFCTR_4() throws IOException,SQLException,Exception {
        BPTFCTR_BR.rp.Reqid = 1;
        IBS.ENDBR(SCCGWA, BPTFCTR_BR);
    }
    public void T000_ENDBR_BPTFCTR_5() throws IOException,SQLException,Exception {
        BPTFCTR_BR.rp.Reqid = 2;
        IBS.ENDBR(SCCGWA, BPTFCTR_BR);
    }
    public void T000_ENDBR_BPTFCTR_6() throws IOException,SQLException,Exception {
        BPTFCTR_BR.rp.Reqid = 3;
        IBS.ENDBR(SCCGWA, BPTFCTR_BR);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
        if (BPCFCTRB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCFCTRB = ");
            CEP.TRC(SCCGWA, BPCFCTRB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
