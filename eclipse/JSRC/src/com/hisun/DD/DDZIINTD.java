package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZIINTD {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String PGM_SCSSCLDT = "SCSSCLDT";
    char WS_FOUND_FLAG = ' ';
    DDZIINTD_WS_SORT_DT_INFO[] WS_SORT_DT_INFO = new DDZIINTD_WS_SORT_DT_INFO[3];
    DDZIINTD_WS_TMP_DT_INFO[] WS_TMP_DT_INFO = new DDZIINTD_WS_TMP_DT_INFO[5];
    DDZIINTD_WS_DT_INFO[] WS_DT_INFO = new DDZIINTD_WS_DT_INFO[5];
    short WS_CNT = 0;
    short WS_I = 0;
    short WS_J = 0;
    short WS_K = 0;
    short WS_L = 0;
    short WS_DIFF = 0;
    short WS_N_I = 0;
    short WS_B_I = 0;
    short WS_IDX = 0;
    short WS_Z = 0;
    DDZIINTD_WS_BAK_DT[] WS_BAK_DT = new DDZIINTD_WS_BAK_DT[8];
    short WS_MON_TOT = 0;
    int WS_COMP_MON_DT = 0;
    short WS_TOTAL_DAYS = 0;
    String WS_CAL_CD = " ";
    short WS_CURR_MON = 0;
    short WS_NEXT_MON = 0;
    DDZIINTD_WS_EDIT_DATE WS_EDIT_DATE = new DDZIINTD_WS_EDIT_DATE();
    short WS_CURR_WEEKNO = 0;
    short WS_NEXT_WEEKNO = 0;
    char WS_POST_PERIOD = ' ';
    DDZIINTD_WS_POST_MMDD WS_POST_MMDD = new DDZIINTD_WS_POST_MMDD();
    int WS_POST_DATE = 0;
    int WS_DEP_POST_DATE = 0;
    int WS_OD_POST_DATE = 0;
    int WS_EOM_DATE = 0;
    char WS_POST_DEP_DATA = 'N';
    char WS_DEP_RETAIN = 'N';
    char WS_OD_RETAIN = 'N';
    char WS_INPUT_ERR = 'N';
    char WS_SKIP_FLAG = 'N';
    DDZIINTD_WS_OUTPUT_INFO WS_OUTPUT_INFO = new DDZIINTD_WS_OUTPUT_INFO();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    BPCOCKWD BPCOCKWD = new BPCOCKWD();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCGWA SCCGWA;
    SCCBKPO SCCBKPO;
    SCCBATH SCCBATH;
    BPCPQBNK_DATA_INFO BPCRBANK;
    DDCIINTD DDCIINTD;
    public DDZIINTD() {
        for (int i=0;i<3;i++) WS_SORT_DT_INFO[i] = new DDZIINTD_WS_SORT_DT_INFO();
        for (int i=0;i<5;i++) WS_TMP_DT_INFO[i] = new DDZIINTD_WS_TMP_DT_INFO();
        for (int i=0;i<5;i++) WS_DT_INFO[i] = new DDZIINTD_WS_DT_INFO();
        for (int i=0;i<8;i++) WS_BAK_DT[i] = new DDZIINTD_WS_BAK_DT();
    }
    public void MP(SCCGWA SCCGWA, DDCIINTD DDCIINTD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCIINTD = DDCIINTD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZIINTD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCBATH = (SCCBATH) SCCGWA.COMM_AREA.BAT_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        DDCIINTD.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        DDCIINTD.RC.RC_CODE = 0;
        CEP.TRC(SCCGWA, BPCRBANK.LOC_CCY1);
        DDCIINTD.CCY = BPCRBANK.LOC_CCY1;
        CEP.TRC(SCCGWA, DDCIINTD.CCY);
        CEP.TRC(SCCGWA, DDCIINTD.DEP_POST_PERIOD1);
        CEP.TRC(SCCGWA, DDCIINTD.DEP_POST_DATE1);
        CEP.TRC(SCCGWA, DDCIINTD.DEP_POST_PERIOD2);
        CEP.TRC(SCCGWA, DDCIINTD.DEP_POST_DATE2);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_PROC();
        if (pgmRtn) return;
        B200_COMPUTE_POST_DATE();
        if (pgmRtn) return;
        B300_COMPRESS_DT_INFO();
        if (pgmRtn) return;
        B400_COMPUTE_INT_DAY();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT_PROC() throws IOException,SQLException,Exception {
        if ((DDCIINTD.DEP_POST_PERIOD1 == ' ') 
            && (DDCIINTD.DEP_POST_PERIOD2 == ' ')) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_POST_INT_CYC_M_INPUT, DDCIINTD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DDCIINTD.DEP_POST_PERIOD1 != ' ') {
            WS_POST_PERIOD = DDCIINTD.DEP_POST_PERIOD1;
            CEP.TRC(SCCGWA, "0");
            CEP.TRC(SCCGWA, DDCIINTD.DEP_POST_DATE1);
            IBS.CPY2CLS(SCCGWA, DDCIINTD.DEP_POST_DATE1+"", WS_POST_MMDD);
            CEP.TRC(SCCGWA, "1");
            WS_POST_DEP_DATA = 'Y';
            CEP.TRC(SCCGWA, "2");
            WS_INPUT_ERR = 'N';
            B100_01_CHECK_POST_DATE();
            if (pgmRtn) return;
        }
        if (DDCIINTD.DEP_POST_PERIOD2 != ' ') {
            WS_POST_PERIOD = DDCIINTD.DEP_POST_PERIOD2;
            IBS.CPY2CLS(SCCGWA, DDCIINTD.DEP_POST_DATE2+"", WS_POST_MMDD);
            WS_POST_DEP_DATA = 'N';
            WS_INPUT_ERR = 'N';
            B100_01_CHECK_POST_DATE();
            if (pgmRtn) return;
        }
        if (DDCIINTD.CURR_DATE == 0) {
            DDCIINTD.CURR_DATE = SCCBATH.JPRM.AC_DATE;
        }
        if (DDCIINTD.NEXT_DATE == 0) {
            DDCIINTD.NEXT_DATE = SCCBATH.JPRM.NEXT_AC_DATB;
        }
    }
    public void B100_01_CHECK_POST_DATE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "-----------------------");
        CEP.TRC(SCCGWA, WS_POST_PERIOD);
        if (WS_POST_PERIOD != 'D' 
            && (WS_POST_PERIOD != ' ')) {
            CEP.TRC(SCCGWA, WS_POST_MMDD.WS_POST_DAY);
            if (WS_POST_MMDD.WS_POST_DAY <= 0) {
                if (WS_POST_DEP_DATA == 'Y') {
                    CEP.TRC(SCCGWA, "3");
                    IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_DEP_POST_DT_INVALID, DDCIINTD.RC);
                    Z_RET();
                    if (pgmRtn) return;
                } else {
                    IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_OD_POST_DT_INVALID, DDCIINTD.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
        }
        if (WS_POST_DEP_DATA == 'Y') {
            if (WS_POST_PERIOD == 'R') {
                WS_DEP_RETAIN = 'Y';
            } else {
                WS_DEP_RETAIN = 'N';
            }
        } else {
            if (WS_POST_PERIOD == 'R') {
                WS_OD_RETAIN = 'Y';
            } else {
                WS_OD_RETAIN = 'N';
            }
        }
        if (WS_POST_PERIOD == 'D') {
            CEP.TRC(SCCGWA, WS_POST_MMDD.WS_POST_DAY);
            if (WS_POST_MMDD.WS_POST_DAY != 0) {
                WS_INPUT_ERR = 'Y';
            }
        } else if (WS_POST_PERIOD == 'W') {
            CEP.TRC(SCCGWA, WS_POST_DEP_DATA);
            CEP.TRC(SCCGWA, WS_POST_MMDD.WS_POST_DAY);
            if ((WS_POST_MMDD.WS_POST_DAY > 7) 
                || (WS_POST_MMDD.WS_POST_DAY < 1)) {
                WS_INPUT_ERR = 'Y';
            }
        } else if (WS_POST_PERIOD == 'M'
            || WS_POST_PERIOD == 'R') {
            if ((WS_POST_MMDD.WS_POST_DAY > 31)) {
                WS_INPUT_ERR = 'Y';
            }
        } else if (WS_POST_PERIOD == 'Q') {
            CEP.TRC(SCCGWA, WS_POST_MMDD.WS_POST_MON);
            CEP.TRC(SCCGWA, WS_POST_MMDD.WS_POST_DAY);
            if ((WS_POST_MMDD.WS_POST_MON > 3) 
                || (WS_POST_MMDD.WS_POST_MON < 1)) {
                WS_INPUT_ERR = 'Y';
            }
            if ((WS_POST_MMDD.WS_POST_DAY > 31) 
                || (WS_POST_MMDD.WS_POST_DAY < 1)) {
                WS_INPUT_ERR = 'Y';
            }
        } else if (WS_POST_PERIOD == 'H') {
            if ((WS_POST_MMDD.WS_POST_MON > 6) 
                || (WS_POST_MMDD.WS_POST_MON < 1)) {
                WS_INPUT_ERR = 'Y';
            }
            if ((WS_POST_MMDD.WS_POST_DAY > 31) 
                || (WS_POST_MMDD.WS_POST_DAY < 1)) {
                WS_INPUT_ERR = 'Y';
            }
        } else if (WS_POST_PERIOD == 'Y') {
            if ((WS_POST_MMDD.WS_POST_MON > 12) 
                || (WS_POST_MMDD.WS_POST_MON < 1)) {
                WS_INPUT_ERR = 'Y';
            }
            if ((WS_POST_MMDD.WS_POST_DAY > 31) 
                || (WS_POST_MMDD.WS_POST_DAY < 1)) {
                WS_INPUT_ERR = 'Y';
            }
        } else {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_POST_INT_CYC_M_INPUT, DDCIINTD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (WS_INPUT_ERR == 'Y') {
            if (WS_POST_DEP_DATA == 'Y') {
                IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_DEP_POST_DT_INVALID, DDCIINTD.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_OD_POST_DT_INVALID, DDCIINTD.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B200_COMPUTE_POST_DATE() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, DDCIINTD.CURR_DATE+"", WS_EDIT_DATE);
        WS_Z = 1;
        WS_TMP_DT_INFO[WS_Z-1].WS_TMP_DATE = DDCIINTD.CURR_DATE;
        WS_TMP_DT_INFO[WS_Z-1].WS_TMP_AC_DT_FLAG = 'Y';
        B200_01_GET_DT_INFO();
        if (pgmRtn) return;
        WS_EDIT_DATE.WS_EDIT_DAY = WS_TOTAL_DAYS;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_EDIT_DATE);
        WS_EOM_DATE = Integer.parseInt(JIBS_tmp_str[0]);
        IBS.CPY2CLS(SCCGWA, DDCIINTD.CURR_DATE+"", WS_EDIT_DATE);
        WS_POST_DATE = 0;
        if (DDCIINTD.DEP_POST_PERIOD1 != ' ') {
            WS_POST_PERIOD = DDCIINTD.DEP_POST_PERIOD1;
            IBS.CPY2CLS(SCCGWA, DDCIINTD.DEP_POST_DATE1+"", WS_POST_MMDD);
            B200_02_GET_POST_DATE();
            if (pgmRtn) return;
        }
        WS_DEP_POST_DATE = WS_POST_DATE;
        CEP.TRC(SCCGWA, WS_DEP_POST_DATE);
        WS_POST_DATE = 0;
        if (DDCIINTD.DEP_POST_PERIOD2 != ' ') {
            WS_POST_PERIOD = DDCIINTD.DEP_POST_PERIOD2;
            IBS.CPY2CLS(SCCGWA, DDCIINTD.DEP_POST_DATE2+"", WS_POST_MMDD);
            B200_02_GET_POST_DATE();
            if (pgmRtn) return;
        }
        WS_OD_POST_DATE = WS_POST_DATE;
        CEP.TRC(SCCGWA, WS_OD_POST_DATE);
        CEP.TRC(SCCGWA, WS_EOM_DATE);
        WS_K = 0;
        if ((WS_EOM_DATE >= DDCIINTD.CURR_DATE) 
            && (WS_EOM_DATE < DDCIINTD.NEXT_DATE)) {
            WS_K = (short) (WS_K + 1);
            WS_SORT_DT_INFO[WS_K-1].WS_SORT_DT = WS_EOM_DATE;
            WS_SORT_DT_INFO[WS_K-1].WS_SORT_EOF_OF_MON_FLAG = 'Y';
        }
        if ((WS_DEP_POST_DATE >= DDCIINTD.CURR_DATE) 
            && (WS_DEP_POST_DATE < DDCIINTD.NEXT_DATE)) {
            WS_K = (short) (WS_K + 1);
            WS_SORT_DT_INFO[WS_K-1].WS_SORT_DT = WS_DEP_POST_DATE;
            WS_SORT_DT_INFO[WS_K-1].WS_SORT_DEP_POSTDT_FLAG = 'Y';
        }
        if ((WS_OD_POST_DATE >= DDCIINTD.CURR_DATE) 
            && (WS_OD_POST_DATE < DDCIINTD.NEXT_DATE)) {
            WS_K = (short) (WS_K + 1);
            WS_SORT_DT_INFO[WS_K-1].WS_SORT_DT = WS_OD_POST_DATE;
            WS_SORT_DT_INFO[WS_K-1].WS_SORT_OD_POST_DT_FLAG = 'Y';
        }
        CEP.TRC(SCCGWA, WS_K);
        if (WS_K == 1) {
            WS_IDX = WS_K;
            R000_INTO_DATE_ARRAY();
            if (pgmRtn) return;
        }
        if (WS_K == 2) {
            if (WS_SORT_DT_INFO[1-1].WS_SORT_DT <= WS_SORT_DT_INFO[2-1].WS_SORT_DT) {
                WS_IDX = 1;
                R000_INTO_DATE_ARRAY();
                if (pgmRtn) return;
                WS_IDX = 2;
                R000_INTO_DATE_ARRAY();
                if (pgmRtn) return;
            } else {
                WS_IDX = 2;
                R000_INTO_DATE_ARRAY();
                if (pgmRtn) return;
                WS_IDX = 1;
                R000_INTO_DATE_ARRAY();
                if (pgmRtn) return;
            }
        }
        if (WS_K == 3) {
            CEP.TRC(SCCGWA, "IN WS-K ========== 3");
            WS_SKIP_FLAG = 'N';
            if ((WS_SORT_DT_INFO[1-1].WS_SORT_DT <= WS_SORT_DT_INFO[2-1].WS_SORT_DT) 
                && (WS_SORT_DT_INFO[1-1].WS_SORT_DT <= WS_SORT_DT_INFO[3-1].WS_SORT_DT)) {
                WS_IDX = 1;
                R000_INTO_DATE_ARRAY();
                if (pgmRtn) return;
                if (WS_SORT_DT_INFO[2-1].WS_SORT_DT <= WS_SORT_DT_INFO[3-1].WS_SORT_DT) {
                    WS_IDX = 2;
                    R000_INTO_DATE_ARRAY();
                    if (pgmRtn) return;
                    WS_IDX = 3;
                    R000_INTO_DATE_ARRAY();
                    if (pgmRtn) return;
                } else {
                    WS_IDX = 3;
                    R000_INTO_DATE_ARRAY();
                    if (pgmRtn) return;
                    WS_IDX = 2;
                    R000_INTO_DATE_ARRAY();
                    if (pgmRtn) return;
                }
                WS_SKIP_FLAG = 'Y';
            }
            if ((WS_SORT_DT_INFO[2-1].WS_SORT_DT <= WS_SORT_DT_INFO[1-1].WS_SORT_DT) 
                && (WS_SORT_DT_INFO[2-1].WS_SORT_DT <= WS_SORT_DT_INFO[3-1].WS_SORT_DT) 
                && (WS_SKIP_FLAG != 'Y')) {
                CEP.TRC(SCCGWA, "IN SORT   ============2");
                WS_IDX = 2;
                R000_INTO_DATE_ARRAY();
                if (pgmRtn) return;
                if (WS_SORT_DT_INFO[1-1].WS_SORT_DT <= WS_SORT_DT_INFO[3-1].WS_SORT_DT) {
                    WS_IDX = 1;
                    R000_INTO_DATE_ARRAY();
                    if (pgmRtn) return;
                    WS_IDX = 3;
                    R000_INTO_DATE_ARRAY();
                    if (pgmRtn) return;
                } else {
                    WS_IDX = 3;
                    R000_INTO_DATE_ARRAY();
                    if (pgmRtn) return;
                    WS_IDX = 1;
                    R000_INTO_DATE_ARRAY();
                    if (pgmRtn) return;
                }
                WS_SKIP_FLAG = 'Y';
            }
            if ((WS_SORT_DT_INFO[3-1].WS_SORT_DT <= WS_SORT_DT_INFO[1-1].WS_SORT_DT) 
                && (WS_SORT_DT_INFO[3-1].WS_SORT_DT <= WS_SORT_DT_INFO[2-1].WS_SORT_DT) 
                && (WS_SKIP_FLAG != 'Y')) {
                WS_IDX = 3;
                R000_INTO_DATE_ARRAY();
                if (pgmRtn) return;
                if (WS_SORT_DT_INFO[2-1].WS_SORT_DT <= WS_SORT_DT_INFO[1-1].WS_SORT_DT) {
                    WS_IDX = 2;
                    R000_INTO_DATE_ARRAY();
                    if (pgmRtn) return;
                    WS_IDX = 1;
                    R000_INTO_DATE_ARRAY();
                    if (pgmRtn) return;
                } else {
                    WS_IDX = 1;
                    R000_INTO_DATE_ARRAY();
                    if (pgmRtn) return;
                    WS_IDX = 2;
                    R000_INTO_DATE_ARRAY();
                    if (pgmRtn) return;
                }
            }
        }
        WS_Z = (short) (WS_Z + 1);
        WS_TMP_DT_INFO[WS_Z-1].WS_TMP_DATE = DDCIINTD.NEXT_DATE;
        WS_TMP_DT_INFO[WS_Z-1].WS_TMP_NEXT_AC_DT_FLAG = 'Y';
        CEP.TRC(SCCGWA, WS_Z);
        for (WS_I = 1; WS_I <= 5; WS_I += 1) {
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, "-------------");
            CEP.TRC(SCCGWA, WS_TMP_DT_INFO[WS_I-1].WS_TMP_DATE);
            CEP.TRC(SCCGWA, WS_TMP_DT_INFO[WS_I-1].WS_TMP_AC_DT_FLAG);
            CEP.TRC(SCCGWA, WS_TMP_DT_INFO[WS_I-1].WS_TMP_DEP_POST_DT_FLAG);
            CEP.TRC(SCCGWA, WS_TMP_DT_INFO[WS_I-1].WS_TMP_OD_POST_DT_FLAG);
            CEP.TRC(SCCGWA, WS_TMP_DT_INFO[WS_I-1].WS_TMP_EOF_OF_MON_FLAG);
            CEP.TRC(SCCGWA, WS_TMP_DT_INFO[WS_I-1].WS_TMP_NEXT_AC_DT_FLAG);
        }
    }
    public void R000_INTO_DATE_ARRAY() throws IOException,SQLException,Exception {
        if ((WS_SORT_DT_INFO[WS_IDX-1].WS_SORT_DT >= DDCIINTD.CURR_DATE) 
            && (WS_SORT_DT_INFO[WS_IDX-1].WS_SORT_DT < DDCIINTD.NEXT_DATE)) {
            WS_Z = (short) (WS_Z + 1);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_SORT_DT_INFO[WS_IDX-1]);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TMP_DT_INFO[WS_Z-1]);
        }
    }
    public void B200_02_GET_POST_DATE() throws IOException,SQLException,Exception {
        if (WS_POST_PERIOD == 'D') {
            if (WS_POST_MMDD.WS_POST_DAY != 0) {
                WS_INPUT_ERR = 'Y';
            }
            WS_POST_DATE = DDCIINTD.CURR_DATE;
        } else if (WS_POST_PERIOD == 'W') {
            if (WS_POST_MMDD.WS_POST_DAY == WS_CURR_WEEKNO) {
                WS_POST_DATE = DDCIINTD.CURR_DATE;
            } else {
                if (WS_CURR_WEEKNO < WS_POST_MMDD.WS_POST_DAY) {
                    WS_DIFF = (short) (WS_POST_MMDD.WS_POST_DAY - WS_CURR_WEEKNO);
                } else {
                    WS_DIFF = (short) (7 - ( WS_CURR_WEEKNO - WS_POST_MMDD.WS_POST_DAY ));
                }
                CEP.TRC(SCCGWA, WS_DIFF);
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = DDCIINTD.CURR_DATE;
                SCCCLDT.DAYS = WS_DIFF;
                S00_CALL_SCSSCLDT();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, SCCCLDT.DATE2);
                if (SCCCLDT.DATE2 < DDCIINTD.NEXT_DATE) {
                    WS_POST_DATE = SCCCLDT.DATE2;
                } else {
                    WS_POST_DATE = 0;
                }
            }
            CEP.TRC(SCCGWA, WS_POST_DATE);
        } else if (WS_POST_PERIOD == 'M'
            || WS_POST_PERIOD == 'R') {
            CEP.TRC(SCCGWA, WS_EDIT_DATE.WS_EDIT_DAY);
            if (WS_POST_MMDD.WS_POST_DAY > WS_TOTAL_DAYS) {
                WS_POST_MMDD.WS_POST_DAY = WS_TOTAL_DAYS;
            }
            if (WS_POST_MMDD.WS_POST_DAY == WS_EDIT_DATE.WS_EDIT_DAY) {
                WS_POST_DATE = DDCIINTD.CURR_DATE;
            } else {
                if (WS_EDIT_DATE.WS_EDIT_DAY < WS_POST_MMDD.WS_POST_DAY) {
                    WS_DIFF = (short) (WS_POST_MMDD.WS_POST_DAY - WS_EDIT_DATE.WS_EDIT_DAY);
                } else {
                    WS_DIFF = (short) (WS_TOTAL_DAYS - ( WS_EDIT_DATE.WS_EDIT_DAY - WS_POST_MMDD.WS_POST_DAY ));
                }
                CEP.TRC(SCCGWA, WS_DIFF);
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = DDCIINTD.CURR_DATE;
                SCCCLDT.DAYS = WS_DIFF;
                S00_CALL_SCSSCLDT();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, SCCCLDT.DATE2);
                if (SCCCLDT.DATE2 < DDCIINTD.NEXT_DATE) {
                    WS_POST_DATE = SCCCLDT.DATE2;
                } else {
                    WS_POST_DATE = 0;
                }
            }
            CEP.TRC(SCCGWA, WS_POST_DATE);
        } else if (WS_POST_PERIOD == 'Q') {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_EDIT_DATE);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_BAK_DT[1-1]);
            WS_BAK_DT[1-1].WS_BAK_MON = WS_POST_MMDD.WS_POST_MON;
            WS_BAK_DT[1-1].WS_BAK_DAY = WS_POST_MMDD.WS_POST_DAY;
            for (WS_I = 2; WS_I <= 4; WS_I += 1) {
                WS_B_I = (short) (WS_I - 1);
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_BAK_DT[WS_B_I-1]);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_BAK_DT[WS_I-1]);
                WS_BAK_DT[WS_I-1].WS_BAK_MON = (short) (WS_BAK_DT[WS_I-1].WS_BAK_MON + 3);
            }
            for (WS_I = 1; WS_I <= 4; WS_I += 1) {
                WS_N_I = (short) (WS_I + 4);
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_BAK_DT[WS_I-1]);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_BAK_DT[WS_N_I-1]);
                WS_BAK_DT[WS_N_I-1].WS_BAK_YEAR = (short) (WS_BAK_DT[WS_N_I-1].WS_BAK_YEAR + 1);
            }
            for (WS_I = 1; WS_I <= 8; WS_I += 1) {
                CEP.TRC(SCCGWA, WS_BAK_DT[WS_I-1]);
            }
            for (WS_I = 1; WS_I <= 8 
                && WS_POST_DATE == 0; WS_I += 1) {
                CEP.TRC(SCCGWA, WS_I);
                CEP.TRC(SCCGWA, "---------------");
                CEP.TRC(SCCGWA, WS_BAK_DT[WS_I-1]);
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_BAK_DT[WS_I-1]);
                JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, WS_BAK_DT[WS_I-1]);
                if ((JIBS_tmp_str[0].compareTo(DDCIINTD.CURR_DATE) >= 0) 
                    && (JIBS_tmp_str[1].compareTo(DDCIINTD.NEXT_DATE) < 0)) {
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_BAK_DT[WS_I-1]);
                    WS_COMP_MON_DT = Integer.parseInt(JIBS_tmp_str[0]);
                    R000_GET_MON_TOT_DAYS();
                    if (pgmRtn) return;
                    if (WS_BAK_DT[WS_I-1].WS_BAK_DAY > WS_MON_TOT) {
                        WS_BAK_DT[WS_I-1].WS_BAK_DAY = WS_MON_TOT;
                    }
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_BAK_DT[WS_I-1]);
                    WS_POST_DATE = Integer.parseInt(JIBS_tmp_str[0]);
                }
            }
            CEP.TRC(SCCGWA, WS_POST_DATE);
        } else if (WS_POST_PERIOD == 'H') {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_EDIT_DATE);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_BAK_DT[1-1]);
            WS_BAK_DT[1-1].WS_BAK_MON = WS_POST_MMDD.WS_POST_MON;
            WS_BAK_DT[1-1].WS_BAK_DAY = WS_POST_MMDD.WS_POST_DAY;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_BAK_DT[1-1]);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_BAK_DT[2-1]);
            if (WS_BAK_DT[2-1].WS_BAK_MON <= 6) {
                WS_BAK_DT[2-1].WS_BAK_MON = (short) (WS_BAK_DT[2-1].WS_BAK_MON + 6);
            } else {
                WS_BAK_DT[2-1].WS_BAK_MON = (short) (WS_BAK_DT[2-1].WS_BAK_MON - 6);
            }
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_BAK_DT[1-1]);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_BAK_DT[3-1]);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_BAK_DT[2-1]);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_BAK_DT[4-1]);
            WS_BAK_DT[3-1].WS_BAK_YEAR = (short) (WS_BAK_DT[3-1].WS_BAK_YEAR + 1);
            WS_BAK_DT[4-1].WS_BAK_YEAR = (short) (WS_BAK_DT[4-1].WS_BAK_YEAR + 1);
            CEP.TRC(SCCGWA, WS_BAK_DT[1-1]);
            CEP.TRC(SCCGWA, WS_BAK_DT[2-1]);
            CEP.TRC(SCCGWA, WS_BAK_DT[3-1]);
            CEP.TRC(SCCGWA, WS_BAK_DT[4-1]);
            for (WS_I = 1; WS_I <= 4 
                && WS_POST_DATE == 0; WS_I += 1) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_BAK_DT[WS_I-1]);
                JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, WS_BAK_DT[WS_I-1]);
                if ((JIBS_tmp_str[0].compareTo(DDCIINTD.CURR_DATE) >= 0) 
                    && (JIBS_tmp_str[1].compareTo(DDCIINTD.NEXT_DATE) < 0)) {
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_BAK_DT[WS_I-1]);
                    WS_COMP_MON_DT = Integer.parseInt(JIBS_tmp_str[0]);
                    R000_GET_MON_TOT_DAYS();
                    if (pgmRtn) return;
                    if (WS_BAK_DT[WS_I-1].WS_BAK_DAY > WS_MON_TOT) {
                        WS_BAK_DT[WS_I-1].WS_BAK_DAY = WS_MON_TOT;
                    }
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_BAK_DT[WS_I-1]);
                    WS_POST_DATE = Integer.parseInt(JIBS_tmp_str[0]);
                }
            }
            CEP.TRC(SCCGWA, WS_POST_DATE);
        } else if (WS_POST_PERIOD == 'Y') {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_EDIT_DATE);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_BAK_DT[1-1]);
            WS_BAK_DT[1-1].WS_BAK_MON = WS_POST_MMDD.WS_POST_MON;
            WS_BAK_DT[1-1].WS_BAK_DAY = WS_POST_MMDD.WS_POST_DAY;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_BAK_DT[1-1]);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_BAK_DT[2-1]);
            WS_BAK_DT[2-1].WS_BAK_YEAR = (short) (WS_BAK_DT[2-1].WS_BAK_YEAR + 1);
            CEP.TRC(SCCGWA, WS_BAK_DT[1-1]);
            CEP.TRC(SCCGWA, WS_BAK_DT[2-1]);
            for (WS_I = 1; WS_I <= 2 
                && WS_POST_DATE == 0; WS_I += 1) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_BAK_DT[WS_I-1]);
                JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, WS_BAK_DT[WS_I-1]);
                if ((JIBS_tmp_str[0].compareTo(DDCIINTD.CURR_DATE) >= 0) 
                    && (JIBS_tmp_str[1].compareTo(DDCIINTD.NEXT_DATE) < 0)) {
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_BAK_DT[WS_I-1]);
                    WS_COMP_MON_DT = Integer.parseInt(JIBS_tmp_str[0]);
                    R000_GET_MON_TOT_DAYS();
                    if (pgmRtn) return;
                    if (WS_BAK_DT[WS_I-1].WS_BAK_DAY > WS_MON_TOT) {
                        WS_BAK_DT[WS_I-1].WS_BAK_DAY = WS_MON_TOT;
                    }
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_BAK_DT[WS_I-1]);
                    WS_POST_DATE = Integer.parseInt(JIBS_tmp_str[0]);
                }
            }
            CEP.TRC(SCCGWA, WS_POST_DATE);
        } else {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_POST_INT_CYC_M_INPUT, DDCIINTD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B200_01_GET_DT_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = DDCIINTD.CCY;
        S00_LINK_BPCQCCY();
        if (pgmRtn) return;
        WS_CAL_CD = BPCQCCY.DATA.CAL_CD;
        WS_COMP_MON_DT = DDCIINTD.CURR_DATE;
        CEP.TRC(SCCGWA, DDCIINTD.CURR_DATE);
        R000_GET_MON_TOT_DAYS();
        if (pgmRtn) return;
        WS_TOTAL_DAYS = WS_MON_TOT;
        CEP.TRC(SCCGWA, DDCIINTD.NEXT_DATE);
        IBS.init(SCCGWA, BPCOCLWD);
        BPCOCLWD.DATE1 = DDCIINTD.CURR_DATE;
        BPCOCLWD.DATE2 = DDCIINTD.NEXT_DATE;
        BPCOCLWD.DAYE_FLG = 'Y';
        BPCOCLWD.CAL_CODE = BPCQCCY.DATA.CAL_CD;
        S00_LINK_BPCOCLWD();
        if (pgmRtn) return;
        WS_CURR_WEEKNO = BPCOCLWD.WEEK_NO1;
        WS_NEXT_WEEKNO = BPCOCLWD.WEEK_NO2;
        CEP.TRC(SCCGWA, BPCOCLWD.WEEK_NO1);
        CEP.TRC(SCCGWA, BPCOCLWD.WEEK_NO2);
    }
    public void R000_GET_MON_TOT_DAYS() throws IOException,SQLException,Exception {
        WS_COMP_MON_DT = WS_COMP_MON_DT / 100;
        WS_COMP_MON_DT = WS_COMP_MON_DT * 100 + 1;
        IBS.init(SCCGWA, BPCOCKWD);
        BPCOCKWD.STAT_FLG = 'Y';
        BPCOCKWD.DATE = WS_COMP_MON_DT;
        BPCOCKWD.CAL_CODE = WS_CAL_CD;
        CEP.TRC(SCCGWA, BPCOCKWD.DATE);
        CEP.TRC(SCCGWA, BPCOCKWD.CAL_CODE);
        S00_LINK_BPCOCKWD();
        if (pgmRtn) return;
        WS_MON_TOT = (short) (BPCOCKWD.MON_DAYS[1-1] + BPCOCKWD.MON_DAYS[2-1]);
    }
    public void B300_COMPRESS_DT_INFO() throws IOException,SQLException,Exception {
        WS_CNT = 1;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TMP_DT_INFO[1-1]);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_DT_INFO[WS_CNT-1]);
        for (WS_I = 2; WS_I <= 5 
            && WS_TMP_DT_INFO[WS_I-1].WS_TMP_DATE == 0; WS_I += 1) {
        }
        CEP.TRC(SCCGWA, WS_I);
        WS_J = WS_I;
        WS_CNT = (short) (WS_CNT + 1);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TMP_DT_INFO[WS_I-1]);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_DT_INFO[WS_CNT-1]);
        if (WS_I < 5) {
            for (WS_I = WS_J; WS_I <= 5 
                && WS_TMP_DT_INFO[WS_I-1].WS_TMP_DATE != 0; WS_I += 1) {
                if (WS_TMP_DT_INFO[WS_I-1].WS_TMP_DATE == WS_DT_INFO[WS_CNT-1].WS_DATE) {
                    if (WS_TMP_DT_INFO[WS_I-1].WS_TMP_NEXT_AC_DT_FLAG == 'Y') {
                        WS_DT_INFO[WS_CNT-1].WS_NEXT_AC_DT_FLAG = 'Y';
                    }
                    if (WS_TMP_DT_INFO[WS_I-1].WS_TMP_DEP_POST_DT_FLAG == 'Y') {
                        WS_DT_INFO[WS_CNT-1].WS_DEP_POST_DT_FLAG = 'Y';
                    }
                    if (WS_TMP_DT_INFO[WS_I-1].WS_TMP_OD_POST_DT_FLAG == 'Y') {
                        WS_DT_INFO[WS_CNT-1].WS_OD_POST_DT_FLAG = 'Y';
                    }
                    if (WS_TMP_DT_INFO[WS_I-1].WS_TMP_EOF_OF_MON_FLAG == 'Y') {
                        WS_DT_INFO[WS_CNT-1].WS_EOF_OF_MON_FLAG = 'Y';
                    }
                } else {
                    WS_CNT = (short) (WS_CNT + 1);
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TMP_DT_INFO[WS_I-1]);
                    IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_DT_INFO[WS_CNT-1]);
                }
            }
        }
        CEP.TRC(SCCGWA, WS_CNT);
        CEP.TRC(SCCGWA, "@@@@@@@@@@@@@@@@@@@@@@@@@");
        for (WS_I = 1; WS_I <= 5 
            && WS_DT_INFO[WS_I-1].WS_DATE != 0; WS_I += 1) {
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, WS_DT_INFO[WS_I-1].WS_DATE);
            CEP.TRC(SCCGWA, WS_DT_INFO[WS_I-1].WS_AC_DT_FLAG);
            CEP.TRC(SCCGWA, WS_DT_INFO[WS_I-1].WS_DEP_POST_DT_FLAG);
            CEP.TRC(SCCGWA, WS_DT_INFO[WS_I-1].WS_OD_POST_DT_FLAG);
            CEP.TRC(SCCGWA, WS_DT_INFO[WS_I-1].WS_EOF_OF_MON_FLAG);
            CEP.TRC(SCCGWA, WS_DT_INFO[WS_I-1].WS_NEXT_AC_DT_FLAG);
        }
    }
    public void B400_COMPUTE_INT_DAY() throws IOException,SQLException,Exception {
        WS_I = 1;
        IBS.init(SCCGWA, WS_OUTPUT_INFO);
        for (WS_I = 1; WS_I <= 4; WS_I += 1) {
            WS_OUTPUT_INFO.WS_OUTPUT_DAY = 0;
            WS_N_I = (short) (WS_I + 1);
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, WS_DT_INFO[WS_N_I-1].WS_DATE);
            CEP.TRC(SCCGWA, WS_DT_INFO[WS_I-1].WS_DATE);
            if (WS_DT_INFO[WS_N_I-1].WS_DATE > 0 
                && WS_DT_INFO[WS_I-1].WS_DATE > 0) {
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = WS_DT_INFO[WS_N_I-1].WS_DATE;
                SCCCLDT.DATE2 = WS_DT_INFO[WS_I-1].WS_DATE;
                S00_CALL_SCSSCLDT();
                if (pgmRtn) return;
                WS_OUTPUT_INFO.WS_OUTPUT_DAY = (short) SCCCLDT.DAYS;
            }
            if (WS_DT_INFO[WS_I-1].WS_AC_DT_FLAG == 'Y') {
                WS_OUTPUT_INFO.WS_OUTPUT_DAY = (short) (WS_OUTPUT_INFO.WS_OUTPUT_DAY + 1);
            }
            if (WS_DT_INFO[WS_N_I-1].WS_NEXT_AC_DT_FLAG == 'Y') {
                WS_OUTPUT_INFO.WS_OUTPUT_DAY = (short) (WS_OUTPUT_INFO.WS_OUTPUT_DAY - 1);
            }
            CEP.TRC(SCCGWA, WS_OUTPUT_INFO.WS_OUTPUT_DAY);
            if (WS_OUTPUT_INFO.WS_OUTPUT_DAY > 0) {
                CEP.TRC(SCCGWA, WS_I);
                if (WS_DT_INFO[WS_N_I-1].WS_DEP_POST_DT_FLAG == 'Y' 
                        && WS_DT_INFO[WS_N_I-1].WS_OD_POST_DT_FLAG == 'Y') {
                    DDCIINTD.OUTPUT_INFO[WS_I-1].INT_DAY = WS_OUTPUT_INFO.WS_OUTPUT_DAY;
                    DDCIINTD.OUTPUT_INFO[WS_I-1].POST_FLAG = '3';
                    if (WS_DEP_RETAIN == 'Y' 
                        && WS_OD_RETAIN == 'Y') {
                        DDCIINTD.OUTPUT_INFO[WS_I-1].POST_FLAG = '6';
                    } else {
                        if (WS_DEP_RETAIN == 'Y' 
                            && WS_OD_RETAIN == 'N') {
                            DDCIINTD.OUTPUT_INFO[WS_I-1].POST_FLAG = '8';
                        }
                        if (WS_DEP_RETAIN == 'N' 
                            && WS_OD_RETAIN == 'Y') {
                            DDCIINTD.OUTPUT_INFO[WS_I-1].POST_FLAG = '7';
                        }
                    }
                } else if ((WS_DT_INFO[WS_N_I-1].WS_DEP_POST_DT_FLAG == 'Y' 
                        && (WS_DT_INFO[WS_N_I-1].WS_OD_POST_DT_FLAG == 'N' 
                        || WS_DT_INFO[WS_N_I-1].WS_OD_POST_DT_FLAG == ' '))) {
                    DDCIINTD.OUTPUT_INFO[WS_I-1].INT_DAY = WS_OUTPUT_INFO.WS_OUTPUT_DAY;
                    DDCIINTD.OUTPUT_INFO[WS_I-1].POST_FLAG = '1';
                    if (WS_DEP_RETAIN == 'Y') {
                        DDCIINTD.OUTPUT_INFO[WS_I-1].POST_FLAG = '4';
                    }
                } else if (((WS_DT_INFO[WS_N_I-1].WS_DEP_POST_DT_FLAG == 'N' 
                        || WS_DT_INFO[WS_N_I-1].WS_DEP_POST_DT_FLAG == ' ') 
                        && WS_DT_INFO[WS_N_I-1].WS_OD_POST_DT_FLAG == 'Y')) {
                    DDCIINTD.OUTPUT_INFO[WS_I-1].INT_DAY = WS_OUTPUT_INFO.WS_OUTPUT_DAY;
                    DDCIINTD.OUTPUT_INFO[WS_I-1].POST_FLAG = '2';
                    if (WS_OD_RETAIN == 'Y') {
                        DDCIINTD.OUTPUT_INFO[WS_I-1].POST_FLAG = '5';
                    }
                } else if (((WS_DT_INFO[WS_N_I-1].WS_DEP_POST_DT_FLAG == 'N' 
                        || WS_DT_INFO[WS_N_I-1].WS_DEP_POST_DT_FLAG == ' ') 
                        && (WS_DT_INFO[WS_N_I-1].WS_OD_POST_DT_FLAG == 'N' 
                        || WS_DT_INFO[WS_N_I-1].WS_OD_POST_DT_FLAG == ' '))) {
                    DDCIINTD.OUTPUT_INFO[WS_I-1].INT_DAY = WS_OUTPUT_INFO.WS_OUTPUT_DAY;
                    DDCIINTD.OUTPUT_INFO[WS_I-1].POST_FLAG = '0';
                } else {
                }
                if (WS_DT_INFO[WS_N_I-1].WS_DATE > WS_EOM_DATE) {
                    DDCIINTD.OUTPUT_INFO[WS_I-1].POST_AC_DATE = DDCIINTD.NEXT_DATE;
                } else {
                    DDCIINTD.OUTPUT_INFO[WS_I-1].POST_AC_DATE = DDCIINTD.CURR_DATE;
                }
            }
        }
        DDCIINTD.D_POST_DATE1 = WS_DEP_POST_DATE;
        DDCIINTD.D_POST_DATE2 = WS_OD_POST_DATE;
        for (WS_I = 1; WS_I <= 6; WS_I += 1) {
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, DDCIINTD.OUTPUT_INFO[WS_I-1].INT_DAY);
            CEP.TRC(SCCGWA, DDCIINTD.OUTPUT_INFO[WS_I-1].POST_FLAG);
            CEP.TRC(SCCGWA, DDCIINTD.OUTPUT_INFO[WS_I-1].POST_AC_DATE);
        }
        CEP.TRC(SCCGWA, DDCIINTD.D_POST_DATE1);
        CEP.TRC(SCCGWA, DDCIINTD.D_POST_DATE2);
    }
    public void S00_LINK_BPCQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCIINTD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S00_LINK_BPCOCKWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CHK-WORK-DAY", BPCOCKWD);
        if (BPCOCKWD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOCKWD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCIINTD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S00_LINK_BPCOCLWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CAL-WORK-DAY", BPCOCLWD);
        if (BPCOCLWD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOCLWD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCIINTD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S00_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            DDCIINTD.RC.RC_MMO = "SC";
            DDCIINTD.RC.RC_CODE = SCCCLDT.RC;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCIINTD.RC);
        CEP.TRC(SCCGWA, DDCIINTD.OUTPUT_INFO[1-1].INT_DAY);
        CEP.TRC(SCCGWA, DDCIINTD.OUTPUT_INFO[1-1].POST_FLAG);
        CEP.TRC(SCCGWA, DDCIINTD.OUTPUT_INFO[1-1].POST_AC_DATE);
        CEP.TRC(SCCGWA, DDCIINTD.OUTPUT_INFO[2-1].INT_DAY);
        CEP.TRC(SCCGWA, DDCIINTD.OUTPUT_INFO[2-1].POST_FLAG);
        CEP.TRC(SCCGWA, DDCIINTD.OUTPUT_INFO[2-1].POST_AC_DATE);
        CEP.TRC(SCCGWA, DDCIINTD.OUTPUT_INFO[3-1].INT_DAY);
        CEP.TRC(SCCGWA, DDCIINTD.OUTPUT_INFO[3-1].POST_FLAG);
        CEP.TRC(SCCGWA, DDCIINTD.OUTPUT_INFO[3-1].POST_AC_DATE);
        CEP.TRC(SCCGWA, DDCIINTD.OUTPUT_INFO[4-1].INT_DAY);
        CEP.TRC(SCCGWA, DDCIINTD.OUTPUT_INFO[4-1].POST_FLAG);
        CEP.TRC(SCCGWA, DDCIINTD.OUTPUT_INFO[4-1].POST_AC_DATE);
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (DDCIINTD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DDCIINTD=");
            CEP.TRC(SCCGWA, DDCIINTD);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
