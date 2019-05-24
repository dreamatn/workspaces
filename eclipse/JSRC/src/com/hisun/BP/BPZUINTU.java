package com.hisun.BP;

import com.hisun.SC.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

import java.io.IOException;
import java.sql.SQLException;

public class BPZUINTU {
    BigDecimal bigD;
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    String CPN_R_IDEV_MAINT = "BP-R-IDEV-MAINT   ";
    String CPN_R_INTR_BRW = "BP-R-INTR-BRW     ";
    String CPN_R_INTR_MAINT = "BP-R-INTR-MAINT   ";
    String CPN_R_IRAT_MAINT = "BP-R-IRAT-MAINT   ";
    String CPN_R_IRPD_MAINT = "BP-R-IRPD-MAINT   ";
    String CPN_R_IHIS_MAINT = "BP-R-IHIS-MAINT   ";
    String CPN_R_IHIT_MAINT = "BP-R-IHIT-MAINT   ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG      ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_P_INQ_PC = "BP-P-INQ-PC       ";
    String CPN_R_BK_MT = "BP-R-IRPD-MAINT";
    String K_CMP_MAIN_PARM = "BP-PARM-MAINTAIN";
    String K_RBASE = "RBASE";
    String K_RTENO = "RTENO";
    String K_CMP_TXN_HIS = "BP-REC-NHIS";
    String K_HIS_COPYBOOK_NAME = "BPCHINTR";
    String K_HIS_REMARKS = "INTEREST MAINTENANCE";
    String K_REFDE_TYPE = "REFDE";
    String K_REFDE_CODE = "001";
    int WS_RATE_FW_TM = 000001;
    char WS_RT_TYP_A = 'A';
    char WS_RT_TYP_B = 'B';
    String WS_ERR_MSG = " ";
    short WS_I = 0;
    short WS_DEGREE = 0;
    int WS_BR = 0;
    String WS_CCY = " ";
    String WS_BASE_TYP = " ";
    String WS_TENOR = " ";
    int WS_DATE = 0;
    int WS_MOST_REF_DEGREE = 0;
    int WS_REF_DEGREE = 0;
    BPZUINTU_WS_IDEV_TABLE WS_IDEV_TABLE = new BPZUINTU_WS_IDEV_TABLE();
    double WS_DEV_DIFF = 0;
    double WS_DIFF_RATE = 0;
    double WS_COMPUTE_RATE = 0;
    double WS_NEW_RATE = 0;
    double WS_OLD_RATE = 0;
    double WS_LAST_RATE = 0;
    double WS_SAVE_DATA = 0;
    short WS_REAL_AUTH = 0;
    short WS_REAL_AUTH_FLG = 0;
    char WS_AUTH_TYPE = ' ';
    char WS_LVL1 = ' ';
    char WS_LVL2 = ' ';
    int WS_NEW_REF_DEPTH = 0;
    int WS_OLD_REF_DEPTH = 0;
    int WS_CUR_REF_DEPTH = 0;
    int WS_REF_DEPTH = 0;
    int WS_OLD_REF_BR = 0;
    String WS_OLD_REF_CCY = " ";
    String WS_OLD_REF_BASE_TYP = " ";
    String WS_OLD_REF_TENOR = " ";
    int WS_NEW_REF_BR = 0;
    String WS_NEW_REF_CCY = " ";
    String WS_NEW_REF_BASE_TYP = " ";
    String WS_NEW_REF_TENOR = " ";
    int WS_INSERT_REF_DEPTH = 0;
    int WS_LAST_REF_DEPTH = 0;
    int WS_LOOP_CNT = 0;
    double WS_IRAT_HIGH = 0;
    double WS_IRAT_LOW = 0;
    double WS_HQT_BANK_RATE = 0;
    int WS_TR_DATE = 0;
    int WS_TR_TIME = 0;
    String WS_TL_ID = " ";
    String WS_OVR1 = " ";
    String WS_OVR2 = " ";
    int WS_RATE_DT = 0;
    BPZUINTU_WS_TMP_INF WS_TMP_INF = new BPZUINTU_WS_TMP_INF();
    BPZUINTU_WS_KEY_INF WS_KEY_INF = new BPZUINTU_WS_KEY_INF();
    char WS_RATE_FLG = ' ';
    char WS_IRAT_FLG = ' ';
    char WS_TBL_BANK_FLAG = ' ';
    char WS_REF_RECORD_FLG = ' ';
    char WS_RECORD_FLG = ' ';
    char WS_INTR_RECORD_FLG = ' ';
    char WS_IHIT_RECORD_FLG = ' ';
    char WS_INSERT_OR_UPDATE_FLG = ' ';
    char WS_YES_NO_REF_FLG = ' ';
    char WS_IS_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRBANK BPRBANK = new BPRBANK();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPRIRPD BPRIRPD = new BPRIRPD();
    BPCRIPDM BPCRIPDM = new BPCRIPDM();
    BPRIDEV BPRIDEV = new BPRIDEV();
    BPCRDEVM BPCRDEVM = new BPCRDEVM();
    BPCRINTM BPCRINTM = new BPCRINTM();
    BPCRINTB BPCRINTB = new BPCRINTB();
    BPCRHITM BPCRHITM = new BPCRHITM();
    BPCRHISM BPCRHISM = new BPCRHISM();
    BPRIRAT BPRIRAT = new BPRIRAT();
    BPCRIATM BPCRIATM = new BPCRIATM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCHINTR BPCOLD = new BPCHINTR();
    BPCHINTR BPCNEW = new BPCHINTR();
    BPCPARM BPCPARM = new BPCPARM();
    BPRPARM BPRPARM = new BPRPARM();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    SCCGWA SCCGWA;
    BPCUINTU BPCUINTU;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGMSG SCCGMSG;
    public void MP(SCCGWA SCCGWA, BPCUINTU BPCUINTU) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCUINTU = BPCUINTU;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZUINTU return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGMSG = (SCCGMSG) SCCGSCA_SC_AREA.MSG_AREA_PTR;
        R000_CHECK_PARAMETER();
        WS_TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        WS_TL_ID = SCCGWA.COMM_AREA.TL_ID;
        WS_OVR1 = SCCGMSG.SUP1_ID;
        WS_OVR2 = SCCGMSG.SUP2_ID;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_MAIN_PROC();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCUINTU.BR != 0) {
            if (BPCUINTU.BR != 999999) {
                WS_BR = BPCUINTU.BR;
                WS_IS_FLG = 'Y';
                R000_CHECK_BRANCH();
            }
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_BR_NO_VALID;
            S000_ERR_MSG_PROC();
        }
        if (BPCUINTU.CCY.trim().length() > 0) {
            CEP.TRC(SCCGWA, BPCUINTU.CCY);
            WS_CCY = BPCUINTU.CCY;
            WS_IS_FLG = 'Y';
            R000_CHECK_CCY();
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_CCY_NO_VALID;
            S000_ERR_MSG_PROC();
        }
        if (BPCUINTU.BASE_TYP.trim().length() > 0) {
            WS_BASE_TYP = BPCUINTU.BASE_TYP;
            WS_IS_FLG = 'Y';
            R000_CHECK_BASE_TYP();
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_BASETYP_NO_VALID;
            S000_ERR_MSG_PROC();
        }
        if (BPCUINTU.TENOR.trim().length() > 0) {
            WS_TENOR = BPCUINTU.TENOR;
            WS_IS_FLG = 'Y';
            R000_CHECK_TENOR();
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_TENOR_NO_VALID;
            S000_ERR_MSG_PROC();
        }
        WS_CCY = BPCUINTU.CCY;
        WS_BASE_TYP = BPCUINTU.BASE_TYP;
        WS_TENOR = BPCUINTU.TENOR;
        R000_CHECK_BPTIRPD();
        if (BPCUINTU.REF_BR != 0) {
            if (BPCUINTU.REF_BR != 999999) {
                WS_BR = BPCUINTU.REF_BR;
                WS_IS_FLG = 'N';
                R000_CHECK_BRANCH();
            }
        }
        if (BPCUINTU.REF_CCY.trim().length() > 0) {
            CEP.TRC(SCCGWA, BPCUINTU.REF_CCY);
            WS_CCY = BPCUINTU.REF_CCY;
            WS_IS_FLG = 'N';
            R000_CHECK_CCY();
        }
        if (BPCUINTU.REF_BASE_TYP.trim().length() > 0) {
            WS_BASE_TYP = BPCUINTU.REF_BASE_TYP;
            WS_IS_FLG = 'N';
            R000_CHECK_BASE_TYP();
        }
        if (BPCUINTU.REF_TENOR.trim().length() > 0) {
            WS_TENOR = BPCUINTU.REF_TENOR;
            WS_IS_FLG = 'N';
            R000_CHECK_TENOR();
        }
        if (BPCUINTU.REF_BR == 0 
            && BPCUINTU.REF_CCY.trim().length() == 0 
            && BPCUINTU.REF_BASE_TYP.trim().length() == 0 
            && BPCUINTU.REF_TENOR.trim().length() == 0) {
            CEP.TRC(SCCGWA, "AAAAA");
        } else {
            CEP.TRC(SCCGWA, "BBBBB");
            WS_CCY = BPCUINTU.REF_CCY;
            WS_BASE_TYP = BPCUINTU.REF_BASE_TYP;
            WS_TENOR = BPCUINTU.REF_TENOR;
            R000_CHECK_REF_BPTIRPD();
        }
        if (BPCUINTU.DT <= SCCGWA.COMM_AREA.AC_DATE) {
            WS_RATE_FLG = 'C';
        } else {
            WS_RATE_FLG = 'F';
        }
        if (WS_RATE_FLG == 'F') {
            WS_TR_TIME = WS_RATE_FW_TM;
        } else {
            WS_TR_TIME = SCCGWA.COMM_AREA.TR_TIME;
        }
        CEP.TRC(SCCGWA, "0015NJH");
        CEP.TRC(SCCGWA, WS_RATE_FW_TM);
        CEP.TRC(SCCGWA, WS_TR_TIME);
    }
    public void B200_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "COMEHERE");
        if (BPCUINTU.REF_CCY.trim().length() > 0 
            && BPCUINTU.REF_BASE_TYP.trim().length() > 0 
            && BPCUINTU.REF_TENOR.trim().length() > 0) {
            B210_GET_NEW_BASE_RATE();
            if (BPCUINTU.FORMAT == 'D') {
                WS_COMPUTE_RATE = WS_COMPUTE_RATE + BPCUINTU.DIFF;
            } else {
                WS_COMPUTE_RATE = WS_COMPUTE_RATE * ( 1 + BPCUINTU.DIFF / 100 );
                bigD = new BigDecimal(WS_COMPUTE_RATE);
                WS_COMPUTE_RATE = bigD.setScale(8, RoundingMode.HALF_UP).doubleValue();
            }
            WS_NEW_RATE = WS_COMPUTE_RATE;
        } else {
            WS_NEW_RATE = BPCUINTU.RATE;
            CEP.TRC(SCCGWA, "CANKAOXINXI");
        }
        CEP.TRC(SCCGWA, WS_NEW_RATE);
        IBS.init(SCCGWA, BPCRINTM);
        WS_RATE_DT = 0;
        BPCRINTM.BR = BPCUINTU.BR;
        BPCRINTM.CCY = BPCUINTU.CCY;
        BPCRINTM.BASE_TYP = BPCUINTU.BASE_TYP;
        BPCRINTM.TENOR = BPCUINTU.TENOR;
        if (WS_RATE_FLG == 'F') {
            BPCRINTM.DT = 20991231;
        } else {
            BPCRINTM.DT = BPCUINTU.DT;
        }
        CEP.TRC(SCCGWA, BPCRINTM.DT);
        CEP.TRC(SCCGWA, BPCRINTM.BR);
        CEP.TRC(SCCGWA, BPCRINTM.CCY);
        CEP.TRC(SCCGWA, BPCRINTM.BASE_TYP);
        CEP.TRC(SCCGWA, BPCRINTM.TENOR);
        CEP.TRC(SCCGWA, BPCRINTM.DT);
        CEP.TRC(SCCGWA, BPCRINTM.RATE);
        R000_READ_BPZRINTM();
        CEP.TRC(SCCGWA, WS_INTR_RECORD_FLG);
        if (WS_INTR_RECORD_FLG == 'N') {
            WS_INSERT_OR_UPDATE_FLG = 'Y';
            WS_OLD_RATE = 0;
        } else {
            if (WS_RATE_FLG == 'F' 
                && BPCRINTM.DT <= SCCGWA.COMM_AREA.AC_DATE) {
                WS_INSERT_OR_UPDATE_FLG = 'Y';
                WS_OLD_RATE = 0;
            } else {
                WS_INSERT_OR_UPDATE_FLG = 'N';
                WS_RATE_DT = BPCRINTM.DT;
                B220_GET_OLD_BASE_RATE();
                WS_OLD_RATE = WS_COMPUTE_RATE;
                CEP.TRC(SCCGWA, WS_COMPUTE_RATE);
                CEP.TRC(SCCGWA, "461");
            }
        }
        if (BPCUINTU.BR != SCCGWA.COMM_AREA.HQT_BANK 
            && BPCUINTU.BR != 999999) {
            R000_READ_BPTIRAT();
            R000_GET_HEAD_BRANCH_RATE();
            WS_HQT_BANK_RATE = WS_COMPUTE_RATE;
            CEP.TRC(SCCGWA, WS_COMPUTE_RATE);
            CEP.TRC(SCCGWA, "470");
            CEP.TRC(SCCGWA, WS_HQT_BANK_RATE);
            R000_COMPARE_RATE();
        }
        if (WS_INSERT_OR_UPDATE_FLG == 'N') {
            CEP.TRC(SCCGWA, WS_NEW_RATE);
            CEP.TRC(SCCGWA, WS_OLD_RATE);
            if (WS_NEW_RATE != WS_OLD_RATE) {
                CEP.TRC(SCCGWA, "888888");
                if (BPCUINTU.BR != 999999) {
                    R000_CHECK_IDEV();
                }
            }
        }
        B230_UPDATE_RATE();
        B240_UPDATE_IHIT();
        B250_UPDATE_IHIS();
    }
    public void B210_GET_NEW_BASE_RATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRINTM);
        WS_LAST_RATE = 0;
        BPCRINTM.BR = BPCUINTU.REF_BR;
        BPCRINTM.CCY = BPCUINTU.REF_CCY;
        BPCRINTM.BASE_TYP = BPCUINTU.REF_BASE_TYP;
        BPCRINTM.TENOR = BPCUINTU.REF_TENOR;
        BPCRINTM.DT = BPCUINTU.DT;
        CEP.TRC(SCCGWA, BPCRINTM.BR);
        CEP.TRC(SCCGWA, BPCRINTM.CCY);
        CEP.TRC(SCCGWA, BPCRINTM.BASE_TYP);
        CEP.TRC(SCCGWA, BPCRINTM.TENOR);
        R000_READ_BPZRINTM_PROC();
        CEP.TRC(SCCGWA, BPCRINTM.REF_BR);
        CEP.TRC(SCCGWA, BPCRINTM.REF_CCY);
        CEP.TRC(SCCGWA, BPCRINTM.REF_BASE_TYP);
        CEP.TRC(SCCGWA, BPCRINTM.REF_TENOR);
        CEP.TRC(SCCGWA, BPCRINTM.REF_DEPTH);
        WS_NEW_REF_BR = BPCRINTM.REF_BR;
        WS_NEW_REF_CCY = BPCRINTM.REF_CCY;
        WS_NEW_REF_BASE_TYP = BPCRINTM.REF_BASE_TYP;
        WS_NEW_REF_TENOR = BPCRINTM.REF_TENOR;
        WS_NEW_REF_DEPTH = BPCRINTM.REF_DEPTH;
        WS_DATE = BPCRINTM.DT;
        R000_GET_BASE_RATE();
    }
    public void B220_GET_OLD_BASE_RATE() throws IOException,SQLException,Exception {
        WS_LAST_RATE = 0;
        WS_OLD_REF_BR = BPCRINTM.REF_BR;
        WS_OLD_REF_CCY = BPCRINTM.REF_CCY;
        WS_OLD_REF_BASE_TYP = BPCRINTM.REF_BASE_TYP;
        WS_OLD_REF_TENOR = BPCRINTM.REF_TENOR;
        WS_REF_DEPTH = BPCRINTM.REF_DEPTH;
        WS_DATE = BPCUINTU.DT;
        R000_GET_BASE_RATE();
    }
    public void B230_UPDATE_RATE() throws IOException,SQLException,Exception {
        if (WS_INSERT_OR_UPDATE_FLG == 'N') {
            B231_UPDATE_BASE_RATE_INF();
            B232_UPDATE_OLD_REF_RATE_DEPTH();
            B233_UPDATE_NEW_REF_RATE_DEPTH();
        } else {
            WS_INSERT_REF_DEPTH = 0;
            B234_ADD_RATE_INF();
            B233_UPDATE_NEW_REF_RATE_DEPTH();
        }
    }
    public void B231_UPDATE_BASE_RATE_INF() throws IOException,SQLException,Exception {
        WS_INSERT_REF_DEPTH = WS_REF_DEPTH;
        CEP.TRC(SCCGWA, WS_INSERT_REF_DEPTH);
        IBS.init(SCCGWA, BPCRINTM);
        BPCRINTM.BR = BPCUINTU.BR;
        BPCRINTM.CCY = BPCUINTU.CCY;
        BPCRINTM.BASE_TYP = BPCUINTU.BASE_TYP;
        BPCRINTM.TENOR = BPCUINTU.TENOR;
        BPCRINTM.DT = WS_RATE_DT;
        R000_READUP_BPTINTR();
        CEP.TRC(SCCGWA, BPCRINTM.DT);
        CEP.TRC(SCCGWA, BPCUINTU.RATE);
        CEP.TRC(SCCGWA, BPCRINTM.RATE);
        if (BPCRINTM.RETURN_INFO == 'F') {
            IBS.init(SCCGWA, BPCRINTM);
            BPCRINTM.BR = BPCUINTU.BR;
            BPCRINTM.CCY = BPCUINTU.CCY;
            BPCRINTM.BASE_TYP = BPCUINTU.BASE_TYP;
            BPCRINTM.TENOR = BPCUINTU.TENOR;
            if (WS_RATE_FLG == 'F') {
                BPCRINTM.DT = BPCUINTU.DT;
            } else {
                BPCRINTM.DT = WS_TR_DATE;
            }
            BPCRINTM.TM = WS_TR_TIME;
            BPCRINTM.REF_BR = BPCUINTU.REF_BR;
            BPCRINTM.REF_CCY = BPCUINTU.REF_CCY;
            BPCRINTM.REF_BASE_TYP = BPCUINTU.REF_BASE_TYP;
            BPCRINTM.REF_TENOR = BPCUINTU.REF_TENOR;
            BPCRINTM.FORMAT = BPCUINTU.FORMAT;
            BPCRINTM.DIFF = BPCUINTU.DIFF;
            BPCRINTM.RATE = WS_NEW_RATE;
            BPCRINTM.REF_DEPTH = WS_INSERT_REF_DEPTH;
            BPCRINTM.TELLER = WS_TL_ID;
            BPCRINTM.OVR1 = WS_OVR1;
            BPCRINTM.OVR2 = WS_OVR2;
            R000_UPDATE_BPTINTR();
        } else {
            IBS.init(SCCGWA, BPCRINTM);
            BPCRINTM.BR = BPCUINTU.BR;
            BPCRINTM.CCY = BPCUINTU.CCY;
            BPCRINTM.BASE_TYP = BPCUINTU.BASE_TYP;
            BPCRINTM.TENOR = BPCUINTU.TENOR;
            if (WS_RATE_FLG == 'F') {
                BPCRINTM.DT = BPCUINTU.DT;
            } else {
                BPCRINTM.DT = WS_TR_DATE;
            }
            BPCRINTM.TM = WS_TR_TIME;
            BPCRINTM.REF_BR = BPCUINTU.REF_BR;
            BPCRINTM.REF_CCY = BPCUINTU.REF_CCY;
            BPCRINTM.REF_BASE_TYP = BPCUINTU.REF_BASE_TYP;
            BPCRINTM.REF_TENOR = BPCUINTU.REF_TENOR;
            BPCRINTM.FORMAT = BPCUINTU.FORMAT;
            BPCRINTM.DIFF = BPCUINTU.DIFF;
            BPCRINTM.RATE = WS_NEW_RATE;
            BPCRINTM.REF_DEPTH = WS_INSERT_REF_DEPTH;
            BPCRINTM.TELLER = WS_TL_ID;
            BPCRINTM.OVR1 = WS_OVR1;
            BPCRINTM.OVR2 = WS_OVR2;
            R000_INSERT_BPTINTR();
        }
    }
    public void B232_UPDATE_OLD_REF_RATE_DEPTH() throws IOException,SQLException,Exception {
        if ((WS_OLD_REF_BR != 0 
            && WS_OLD_REF_CCY.trim().length() > 0 
            && WS_OLD_REF_BASE_TYP.trim().length() > 0 
            && WS_OLD_REF_TENOR.trim().length() > 0) 
            && (WS_OLD_REF_BR != BPCUINTU.REF_BR 
            && !WS_OLD_REF_CCY.equalsIgnoreCase(BPCUINTU.REF_CCY) 
            && !WS_OLD_REF_BASE_TYP.equalsIgnoreCase(BPCUINTU.REF_BASE_TYP) 
            && !WS_OLD_REF_TENOR.equalsIgnoreCase(BPCUINTU.REF_TENOR))) {
            WS_CUR_REF_DEPTH = WS_INSERT_REF_DEPTH;
            IBS.init(SCCGWA, BPCRINTM);
            BPCRINTM.BR = WS_OLD_REF_BR;
            BPCRINTM.CCY = WS_OLD_REF_CCY;
            BPCRINTM.BASE_TYP = WS_OLD_REF_BASE_TYP;
            BPCRINTM.TENOR = WS_OLD_REF_TENOR;
            BPCRINTM.DT = WS_DATE;
            R000_READ_BPZRINTM_PROC();
            WS_OLD_REF_DEPTH = BPCRINTM.REF_DEPTH;
            if (( WS_CUR_REF_DEPTH + 1 ) >= WS_OLD_REF_DEPTH) {
                IBS.init(SCCGWA, BPCRINTB);
                BPCRINTB.REF_BR = WS_OLD_REF_BR;
                BPCRINTB.REF_CCY = WS_OLD_REF_CCY;
                BPCRINTB.REF_BASE_TYP = WS_OLD_REF_BASE_TYP;
                BPCRINTB.REF_TENOR = WS_OLD_REF_TENOR;
                WS_YES_NO_REF_FLG = 'N';
                R000_SELECT_BPTINTR_DEPTH();
                if (WS_YES_NO_REF_FLG == 'Y') {
                    WS_LAST_REF_DEPTH = 0;
                } else {
                    WS_LAST_REF_DEPTH = BPCRINTB.REF_DEPTH;
                    WS_LAST_REF_DEPTH += 1;
                }
                if (WS_LAST_REF_DEPTH != WS_OLD_REF_DEPTH) {
                    R000_UPDATE_OLD_REF_DEPTH();
                }
            }
        }
    }
    public void B233_UPDATE_NEW_REF_RATE_DEPTH() throws IOException,SQLException,Exception {
        if ((BPCUINTU.REF_BR != 0 
            && BPCUINTU.REF_CCY.trim().length() > 0 
            && BPCUINTU.REF_BASE_TYP.trim().length() > 0 
            && BPCUINTU.REF_TENOR.trim().length() > 0) 
            && (WS_OLD_REF_BR != BPCUINTU.REF_BR 
            && !WS_OLD_REF_CCY.equalsIgnoreCase(BPCUINTU.REF_CCY) 
            && !WS_OLD_REF_BASE_TYP.equalsIgnoreCase(BPCUINTU.REF_BASE_TYP) 
            && !WS_OLD_REF_TENOR.equalsIgnoreCase(BPCUINTU.REF_TENOR))) {
            WS_LAST_REF_DEPTH = WS_INSERT_REF_DEPTH;
            CEP.TRC(SCCGWA, WS_LAST_REF_DEPTH);
            CEP.TRC(SCCGWA, WS_NEW_REF_DEPTH);
            if (( WS_LAST_REF_DEPTH + 1 ) > WS_NEW_REF_DEPTH) {
                R000_UPDATE_NEW_REF_DEPTH();
            }
        }
    }
    public void B234_ADD_RATE_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRINTM);
        BPCRINTM.BR = BPCUINTU.BR;
        BPCRINTM.CCY = BPCUINTU.CCY;
        BPCRINTM.BASE_TYP = BPCUINTU.BASE_TYP;
        BPCRINTM.TENOR = BPCUINTU.TENOR;
        if (WS_RATE_FLG == 'F') {
            BPCRINTM.DT = BPCUINTU.DT;
        } else {
            BPCRINTM.DT = WS_TR_DATE;
        }
        BPCRINTM.TM = WS_TR_TIME;
        BPCRINTM.REF_BR = BPCUINTU.REF_BR;
        BPCRINTM.REF_CCY = BPCUINTU.REF_CCY;
        BPCRINTM.REF_BASE_TYP = BPCUINTU.REF_BASE_TYP;
        BPCRINTM.REF_TENOR = BPCUINTU.REF_TENOR;
        BPCRINTM.FORMAT = BPCUINTU.FORMAT;
        BPCRINTM.DIFF = BPCUINTU.DIFF;
        BPCRINTM.RATE = WS_NEW_RATE;
        BPCRINTM.REF_DEPTH = WS_INSERT_REF_DEPTH;
        BPCRINTM.TELLER = WS_TL_ID;
        BPCRINTM.OVR1 = WS_OVR1;
        BPCRINTM.OVR2 = WS_OVR2;
        R000_INSERT_BPTINTR();
    }
    public void B240_UPDATE_IHIT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRHITM);
        BPCRHITM.BR = BPCUINTU.BR;
        BPCRHITM.CCY = BPCUINTU.CCY;
        BPCRHITM.BASE_TYP = BPCUINTU.BASE_TYP;
        BPCRHITM.TENOR = BPCUINTU.TENOR;
        if (WS_RATE_FLG == 'F') {
            BPCRHITM.DT = BPCUINTU.DT;
        } else {
            BPCRHITM.DT = WS_TR_DATE;
        }
        BPCRHITM.TM = WS_TR_TIME;
        BPCRHITM.REF_BR = BPCUINTU.REF_BR;
        BPCRHITM.REF_CCY = BPCUINTU.REF_CCY;
        BPCRHITM.REF_BASE_TYP = BPCUINTU.REF_BASE_TYP;
        BPCRHITM.REF_TENOR = BPCUINTU.REF_TENOR;
        BPCRHITM.FORMAT = BPCUINTU.FORMAT;
        BPCRHITM.DIFF = BPCUINTU.DIFF;
        BPCRHITM.RATE = BPCUINTU.RATE;
        BPCRHITM.REF_DEPTH = WS_INSERT_REF_DEPTH;
        BPCRHITM.TELLER = WS_TL_ID;
        BPCRHITM.OVR1 = WS_OVR1;
        BPCRHITM.OVR2 = WS_OVR2;
        if (WS_RATE_FLG == 'F') {
            R000_INSERT_BPTIHIT();
        } else {
            R000_READUP_BPTIHIT();
            if (WS_IHIT_RECORD_FLG == 'N') {
                R000_INSERT_BPTIHIT();
            } else {
                R000_UPDATE_BPTIHIT();
            }
        }
    }
    public void B250_UPDATE_IHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRHISM);
        BPCRHISM.BR = BPCUINTU.BR;
        BPCRHISM.CCY = BPCUINTU.CCY;
        BPCRHISM.BASE_TYP = BPCUINTU.BASE_TYP;
        BPCRHISM.TENOR = BPCUINTU.TENOR;
        if (WS_RATE_FLG == 'F') {
            BPCRHISM.DT = BPCUINTU.DT;
            CEP.TRC(SCCGWA, BPCUINTU.DT);
            CEP.TRC(SCCGWA, BPCRHISM.DT);
        } else {
            BPCRHISM.DT = WS_TR_DATE;
            CEP.TRC(SCCGWA, BPCRHISM.DT);
            CEP.TRC(SCCGWA, WS_TR_DATE);
        }
        BPCRHISM.TM = WS_TR_TIME;
        BPCRHISM.REF_BR = BPCUINTU.REF_BR;
        BPCRHISM.REF_CCY = BPCUINTU.REF_CCY;
        BPCRHISM.REF_BASE_TYP = BPCUINTU.REF_BASE_TYP;
        BPCRHISM.REF_TENOR = BPCUINTU.REF_TENOR;
        BPCRHISM.FORMAT = BPCUINTU.FORMAT;
        BPCRHISM.DIFF = BPCUINTU.DIFF;
        BPCRHISM.RATE = BPCUINTU.RATE;
        BPCRHISM.REF_DEPTH = WS_INSERT_REF_DEPTH;
        BPCRHISM.TELLER = WS_TL_ID;
        BPCRHISM.OVR1 = WS_OVR1;
        BPCRHISM.OVR2 = WS_OVR2;
        R000_INSERT_BPTIHIS();
    }
    public void R000_CHECK_IDEV() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIDEV);
        BPRIDEV.KEY.TYPE = 'M';
        CEP.TRC(SCCGWA, BPCUINTU.BR);
        CEP.TRC(SCCGWA, BPCUINTU.CCY);
        CEP.TRC(SCCGWA, BPCUINTU.BASE_TYP);
        CEP.TRC(SCCGWA, BPCUINTU.TENOR);
        BPRIDEV.KEY.BR = BPCUINTU.BR;
        BPRIDEV.KEY.CCY = BPCUINTU.CCY;
        BPRIDEV.KEY.BASE_TYP = BPCUINTU.BASE_TYP;
        BPRIDEV.KEY.TENOR = BPCUINTU.TENOR;
        R000_READ_BPTIDEV();
        WS_IDEV_TABLE.OCCURS12[1-1].WS_IDEV = BPRIDEV.DEV1;
        WS_IDEV_TABLE.OCCURS12[1-1].WS_AUTH = BPRIDEV.AL1;
        WS_IDEV_TABLE.OCCURS12[2-1].WS_IDEV = BPRIDEV.DEV2;
        WS_IDEV_TABLE.OCCURS12[2-1].WS_AUTH = BPRIDEV.AL2;
        WS_IDEV_TABLE.OCCURS12[3-1].WS_IDEV = BPRIDEV.DEV3;
        WS_IDEV_TABLE.OCCURS12[3-1].WS_AUTH = BPRIDEV.AL3;
        WS_IDEV_TABLE.OCCURS12[4-1].WS_IDEV = BPRIDEV.DEV4;
        WS_IDEV_TABLE.OCCURS12[4-1].WS_AUTH = BPRIDEV.AL4;
        WS_IDEV_TABLE.OCCURS12[5-1].WS_IDEV = BPRIDEV.DEV5;
        WS_IDEV_TABLE.OCCURS12[5-1].WS_AUTH = BPRIDEV.AL5;
        WS_IDEV_TABLE.OCCURS12[6-1].WS_IDEV = BPRIDEV.DEV6;
        WS_IDEV_TABLE.OCCURS12[6-1].WS_AUTH = BPRIDEV.AL6;
        WS_IDEV_TABLE.OCCURS12[7-1].WS_IDEV = BPRIDEV.DEV7;
        WS_IDEV_TABLE.OCCURS12[7-1].WS_AUTH = BPRIDEV.AL7;
        WS_IDEV_TABLE.OCCURS12[8-1].WS_IDEV = BPRIDEV.DEV8;
        WS_IDEV_TABLE.OCCURS12[8-1].WS_AUTH = BPRIDEV.AL8;
        WS_IDEV_TABLE.OCCURS12[9-1].WS_IDEV = BPRIDEV.DEV9;
        WS_IDEV_TABLE.OCCURS12[9-1].WS_AUTH = BPRIDEV.AL9;
        WS_IDEV_TABLE.OCCURS12[10-1].WS_IDEV = BPRIDEV.DEV10;
        WS_IDEV_TABLE.OCCURS12[10-1].WS_AUTH = BPRIDEV.AL10;
        WS_DEV_DIFF = WS_NEW_RATE - WS_OLD_RATE;
        CEP.TRC(SCCGWA, WS_NEW_RATE);
        CEP.TRC(SCCGWA, WS_OLD_RATE);
        CEP.TRC(SCCGWA, WS_DEV_DIFF);
        if (BPRIDEV.KEY.TYPE == 'M' 
            && BPRIDEV.FORMAT == 'P') {
            if (WS_OLD_RATE != 0) {
                WS_DEV_DIFF = WS_DEV_DIFF / WS_OLD_RATE;
                bigD = new BigDecimal(WS_DEV_DIFF);
                WS_DEV_DIFF = bigD.setScale(8, RoundingMode.HALF_UP).doubleValue();
            } else {
                WS_DEV_DIFF = WS_DEV_DIFF / WS_NEW_RATE;
                bigD = new BigDecimal(WS_DEV_DIFF);
                WS_DEV_DIFF = bigD.setScale(8, RoundingMode.HALF_UP).doubleValue();
            }
        }
        if (WS_DEV_DIFF < 0) {
            WS_DEV_DIFF = 0 - WS_DEV_DIFF;
        }
        CEP.TRC(SCCGWA, WS_DEV_DIFF);
        CEP.TRC(SCCGWA, WS_DEV_DIFF);
        WS_REAL_AUTH_FLG = 99;
        WS_REAL_AUTH = 0;
        CEP.TRC(SCCGWA, WS_REAL_AUTH);
        CEP.TRC(SCCGWA, WS_I);
        CEP.TRC(SCCGWA, BPRIDEV.CNT);
        for (WS_I = 1; WS_I <= BPRIDEV.CNT 
            && WS_I <= 10; WS_I += 1) {
            CEP.TRC(SCCGWA, "IDEV-FORMAT");
            CEP.TRC(SCCGWA, WS_I);
            if (WS_IDEV_TABLE.OCCURS12[WS_I-1].WS_IDEV != 0) {
                if (BPRIDEV.FORMAT == 'D') {
                    CEP.TRC(SCCGWA, "IDEV-FORMAT D");
                    if (WS_DEV_DIFF > WS_IDEV_TABLE.OCCURS12[WS_I-1].WS_IDEV) {
                        WS_REAL_AUTH = WS_IDEV_TABLE.OCCURS12[WS_I-1].WS_AUTH;
                        WS_REAL_AUTH_FLG = WS_IDEV_TABLE.OCCURS12[WS_I-1].WS_AUTH;
                    }
                    CEP.TRC(SCCGWA, WS_IDEV_TABLE.OCCURS12[WS_I-1].WS_IDEV);
                    CEP.TRC(SCCGWA, WS_DEV_DIFF);
                    CEP.TRC(SCCGWA, WS_REAL_AUTH);
                } else {
                    CEP.TRC(SCCGWA, "IDEV-FORMAT P");
                    CEP.TRC(SCCGWA, WS_IDEV_TABLE.OCCURS12[WS_I-1].WS_IDEV);
                    CEP.TRC(SCCGWA, WS_DEV_DIFF);
                    if (WS_DEV_DIFF > ( null / 100 )) {
                        WS_REAL_AUTH = WS_IDEV_TABLE.OCCURS12[WS_I-1].WS_AUTH;
                        WS_REAL_AUTH_FLG = WS_IDEV_TABLE.OCCURS12[WS_I-1].WS_AUTH;
                    }
                    CEP.TRC(SCCGWA, WS_REAL_AUTH);
                }
            }
        }
        CEP.TRC(SCCGWA, WS_REAL_AUTH_FLG);
        if (WS_REAL_AUTH_FLG != 99) {
            R000_AUTHORIZE_PROC();
        }
    }
    public void R000_UPDATE_OLD_REF_DEPTH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRINTM);
        BPCRINTM.BR = WS_OLD_REF_BR;
        BPCRINTM.CCY = WS_OLD_REF_CCY;
        BPCRINTM.BASE_TYP = WS_OLD_REF_BASE_TYP;
        BPCRINTM.TENOR = WS_OLD_REF_TENOR;
        BPCRINTM.DT = WS_DATE;
        R000_READUP_BPTINTR();
        BPCRINTM.REF_DEPTH = WS_LAST_REF_DEPTH;
        R000_UPDATE_BPTINTR();
        WS_LAST_REF_DEPTH += 1;
        WS_REF_DEGREE = 0;
        WS_REF_DEGREE = WS_MOST_REF_DEGREE;
        if (BPCRINTM.REF_BR != 0 
            && BPCRINTM.REF_CCY.trim().length() > 0 
            && BPCRINTM.REF_BASE_TYP.trim().length() > 0 
            && BPCRINTM.REF_TENOR.trim().length() > 0) {
            WS_REF_RECORD_FLG = 'Y';
            for (WS_I = 1; WS_I <= WS_MOST_REF_DEGREE 
                && WS_REF_RECORD_FLG != 'N'; WS_I += 1) {
                WS_TMP_INF.WS_REF_BR = BPCRINTM.REF_BR;
                WS_TMP_INF.WS_REF_CCY = BPCRINTM.REF_CCY;
                WS_TMP_INF.WS_REF_BASE_TYP = BPCRINTM.REF_BASE_TYP;
                WS_TMP_INF.WS_REF_TENOR = BPCRINTM.REF_TENOR;
                WS_DATE = BPCRINTM.DT;
                IBS.init(SCCGWA, BPCRINTM);
                BPCRINTM.BR = WS_TMP_INF.WS_REF_BR;
                BPCRINTM.CCY = WS_TMP_INF.WS_REF_CCY;
                BPCRINTM.BASE_TYP = WS_TMP_INF.WS_REF_BASE_TYP;
                BPCRINTM.TENOR = WS_TMP_INF.WS_REF_TENOR;
                BPCRINTM.DT = WS_DATE;
                R000_READUP_BPTINTR();
                BPCRINTM.REF_DEPTH = WS_LAST_REF_DEPTH;
                R000_UPDATE_BPTINTR();
                WS_LAST_REF_DEPTH += 1;
                if (BPCRINTM.REF_BR == 0 
                    && BPCRINTM.REF_CCY.trim().length() == 0 
                    && BPCRINTM.REF_BASE_TYP.trim().length() == 0 
                    && BPCRINTM.REF_TENOR.trim().length() == 0) {
                    WS_REF_RECORD_FLG = 'N';
                }
            }
            if (BPCRINTM.REF_BR != 0 
                && BPCRINTM.REF_CCY.trim().length() > 0 
                && BPCRINTM.REF_BASE_TYP.trim().length() > 0 
                && BPCRINTM.REF_TENOR.trim().length() > 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_REF_DEGREE_OVER;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void R000_UPDATE_NEW_REF_DEPTH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRINTM);
        BPCRINTM.BR = BPCUINTU.REF_BR;
        BPCRINTM.CCY = BPCUINTU.REF_CCY;
        BPCRINTM.BASE_TYP = BPCUINTU.REF_BASE_TYP;
        BPCRINTM.TENOR = BPCUINTU.REF_TENOR;
        BPCRINTM.DT = BPCUINTU.DT;
        CEP.TRC(SCCGWA, BPCUINTU.DT);
        CEP.TRC(SCCGWA, BPCRINTM.DT);
        R000_READUP_BPTINTR();
        WS_LAST_REF_DEPTH += 1;
        BPCRINTM.REF_DEPTH = WS_LAST_REF_DEPTH;
        R000_UPDATE_BPTINTR();
        WS_REF_DEGREE = 0;
        WS_REF_DEGREE = WS_MOST_REF_DEGREE;
        CEP.TRC(SCCGWA, WS_MOST_REF_DEGREE);
        CEP.TRC(SCCGWA, BPCRINTM.REF_BR);
        CEP.TRC(SCCGWA, BPCRINTM.REF_CCY);
        CEP.TRC(SCCGWA, BPCRINTM.REF_BASE_TYP);
        CEP.TRC(SCCGWA, BPCRINTM.REF_TENOR);
        if (BPCRINTM.REF_BR != 0 
            && BPCRINTM.REF_CCY.trim().length() > 0 
            && BPCRINTM.REF_BASE_TYP.trim().length() > 0 
            && BPCRINTM.REF_TENOR.trim().length() > 0) {
            WS_REF_RECORD_FLG = 'Y';
            for (WS_I = 1; WS_I <= WS_MOST_REF_DEGREE 
                && WS_REF_RECORD_FLG != 'N'; WS_I += 1) {
                WS_TMP_INF.WS_REF_BR = BPCRINTM.REF_BR;
                WS_TMP_INF.WS_REF_CCY = BPCRINTM.REF_CCY;
                WS_TMP_INF.WS_REF_BASE_TYP = BPCRINTM.REF_BASE_TYP;
                WS_TMP_INF.WS_REF_TENOR = BPCRINTM.REF_TENOR;
                WS_DATE = BPCRINTM.DT;
                IBS.init(SCCGWA, BPCRINTM);
                BPCRINTM.BR = WS_TMP_INF.WS_REF_BR;
                BPCRINTM.CCY = WS_TMP_INF.WS_REF_CCY;
                BPCRINTM.BASE_TYP = WS_TMP_INF.WS_REF_BASE_TYP;
                BPCRINTM.TENOR = WS_TMP_INF.WS_REF_TENOR;
                BPCRINTM.DT = WS_DATE;
                R000_READUP_BPTINTR();
                CEP.TRC(SCCGWA, WS_LAST_REF_DEPTH);
                WS_LAST_REF_DEPTH += 1;
                BPCRINTM.REF_DEPTH = WS_LAST_REF_DEPTH;
                CEP.TRC(SCCGWA, BPCRINTM.REF_DEPTH);
                R000_UPDATE_BPTINTR();
                CEP.TRC(SCCGWA, BPCRINTM.REF_BR);
                CEP.TRC(SCCGWA, BPCRINTM.REF_CCY);
                CEP.TRC(SCCGWA, BPCRINTM.REF_BASE_TYP);
                CEP.TRC(SCCGWA, BPCRINTM.REF_TENOR);
                if (BPCRINTM.REF_BR == 0 
                    && BPCRINTM.REF_CCY.trim().length() == 0 
                    && BPCRINTM.REF_BASE_TYP.trim().length() == 0 
                    && BPCRINTM.REF_TENOR.trim().length() == 0) {
                    WS_REF_RECORD_FLG = 'N';
                }
            }
            if (BPCRINTM.REF_BR != 0 
                && BPCRINTM.REF_CCY.trim().length() > 0 
                && BPCRINTM.REF_BASE_TYP.trim().length() > 0 
                && BPCRINTM.REF_TENOR.trim().length() > 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_REF_DEGREE_OVER;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void R000_SELECT_BPTINTR_DEPTH() throws IOException,SQLException,Exception {
        BPCRINTB.FUNC = 'S';
        BPCRINTB.COND = '8';
        S000_CALL_BPZRINTB();
        BPCRINTB.FUNC = 'N';
        S000_CALL_BPZRINTB();
        BPCRINTB.FUNC = 'E';
        S000_CALL_BPZRINTB();
    }
    public void R000_GET_BASE_RATE() throws IOException,SQLException,Exception {
        WS_REF_DEGREE = 0;
        WS_REF_DEGREE = WS_MOST_REF_DEGREE;
        CEP.TRC(SCCGWA, WS_REF_DEGREE);
        CEP.TRC(SCCGWA, BPCRINTM.REF_BR);
        CEP.TRC(SCCGWA, BPCRINTM.REF_CCY);
        CEP.TRC(SCCGWA, BPCRINTM.REF_BASE_TYP);
        CEP.TRC(SCCGWA, BPCRINTM.REF_TENOR);
        if (BPCRINTM.REF_BR != 0 
            && BPCRINTM.REF_CCY.trim().length() > 0 
            && BPCRINTM.REF_BASE_TYP.trim().length() > 0 
            && BPCRINTM.REF_TENOR.trim().length() > 0) {
            WS_REF_RECORD_FLG = 'Y';
            for (WS_I = 1; WS_I <= WS_MOST_REF_DEGREE 
                && WS_REF_RECORD_FLG != 'N'; WS_I += 1) {
                WS_TMP_INF.WS_REF_BR = BPCRINTM.REF_BR;
                WS_TMP_INF.WS_REF_CCY = BPCRINTM.REF_CCY;
                WS_TMP_INF.WS_REF_BASE_TYP = BPCRINTM.REF_BASE_TYP;
                WS_TMP_INF.WS_REF_TENOR = BPCRINTM.REF_TENOR;
                WS_TMP_INF.WS_FORMAT = BPCRINTM.FORMAT;
                WS_TMP_INF.WS_DIFF = BPCRINTM.DIFF;
                WS_TMP_INF.WS_RATE = BPCRINTM.RATE;
                WS_DATE = BPCUINTU.DT;
                CEP.TRC(SCCGWA, BPCUINTU.DT);
                CEP.TRC(SCCGWA, WS_DATE);
                WS_CCY = WS_TMP_INF.WS_REF_CCY;
                WS_BASE_TYP = WS_TMP_INF.WS_REF_BASE_TYP;
                WS_TENOR = WS_TMP_INF.WS_REF_TENOR;
                R000_CHECK_REF_BPTIRPD();
                IBS.init(SCCGWA, BPCRINTM);
                BPCRINTM.BR = WS_TMP_INF.WS_REF_BR;
                BPCRINTM.CCY = WS_TMP_INF.WS_REF_CCY;
                BPCRINTM.BASE_TYP = WS_TMP_INF.WS_REF_BASE_TYP;
                BPCRINTM.TENOR = WS_TMP_INF.WS_REF_TENOR;
                BPCRINTM.DT = WS_DATE;
                R000_READ_BPZRINTM_PROC();
                CEP.TRC(SCCGWA, BPCRINTM.REF_BR);
                CEP.TRC(SCCGWA, BPCRINTM.REF_CCY);
                CEP.TRC(SCCGWA, BPCRINTM.REF_BASE_TYP);
                CEP.TRC(SCCGWA, BPCRINTM.REF_TENOR);
                CEP.TRC(SCCGWA, BPCRINTM.FORMAT);
                CEP.TRC(SCCGWA, BPCRINTM.DIFF);
                CEP.TRC(SCCGWA, BPCRINTM.RATE);
                if (BPCRINTM.REF_BR == 0 
                    && BPCRINTM.REF_CCY.trim().length() == 0 
                    && BPCRINTM.REF_BASE_TYP.trim().length() == 0 
                    && BPCRINTM.REF_TENOR.trim().length() == 0) {
                    WS_REF_RECORD_FLG = 'N';
                    WS_LAST_RATE = BPCRINTM.RATE;
                    CEP.TRC(SCCGWA, WS_I);
                    WS_DEGREE = WS_I;
                }
            }
            if (BPCRINTM.REF_BR != 0 
                && BPCRINTM.REF_CCY.trim().length() > 0 
                && BPCRINTM.REF_BASE_TYP.trim().length() > 0 
                && BPCRINTM.REF_TENOR.trim().length() > 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_REF_DEGREE_OVER;
                S000_ERR_MSG_PROC();
            }
            WS_COMPUTE_RATE = WS_LAST_RATE;
            WS_LOOP_CNT = WS_DEGREE - 1;
            for (WS_I = WS_LOOP_CNT; WS_I != 0; WS_I += -1) {
                R000_COMPUTE_RATE();
            }
        } else {
            WS_COMPUTE_RATE = BPCRINTM.RATE;
            CEP.TRC(SCCGWA, WS_COMPUTE_RATE);
            CEP.TRC(SCCGWA, "1065");
        }
    }
    public void R000_TXN_HIS_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        if (BPCRINTM.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
        }
        if (BPCRINTM.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
        }
        if (BPCRINTM.FUNC == 'U') {
            BPCPNHIS.INFO.TX_TYP = 'M';
        }
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK_NAME;
        WS_KEY_INF.WS_K_BR = BPCRINTM.BR;
        WS_KEY_INF.WS_K_CCY = BPCRINTM.CCY;
        WS_KEY_INF.WS_K_BASE_TYP = BPCRINTM.BASE_TYP;
        WS_KEY_INF.WS_K_TENOR = BPCRINTM.TENOR;
        BPCPNHIS.INFO.REF_NO = IBS.CLS2CPY(SCCGWA, WS_KEY_INF);
        if (BPCRINTM.FUNC == 'U') {
            BPCPNHIS.INFO.OLD_DAT_PT = BPCOLD;
            BPCPNHIS.INFO.NEW_DAT_PT = BPCNEW;
        }
        S000_CALL_BPZPNHIS();
    }
    public void R000_CHECK_BRANCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = WS_BR;
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            if (WS_IS_FLG == 'Y') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_BR_NO_VALID;
            } else {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_REF_BR_NO_VALID;
            }
            S000_ERR_MSG_PROC();
        }
    }
    public void R000_CHECK_CCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = WS_CCY;
        IBS.CALLCPN(SCCGWA, CPN_INQUIRE_CCY, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            if (WS_IS_FLG == 'Y') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_CCY_NO_VALID;
            } else {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_REF_CCY_NO_VALID;
            }
            S000_ERR_MSG_PROC();
        }
    }
    public void R000_CHECK_BASE_TYP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = K_RBASE;
        BPCOQPCD.INPUT_DATA.CODE = WS_BASE_TYP;
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_PC, BPCOQPCD);
        if (BPCOQPCD.RC.RC_CODE != 0) {
            if (WS_IS_FLG == 'Y') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_BASETYP_NO_VALID;
            } else {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_REF_BASETYP_NO_V;
            }
            S000_ERR_MSG_PROC();
        }
    }
    public void R000_CHECK_TENOR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = K_RTENO;
        BPCOQPCD.INPUT_DATA.CODE = WS_TENOR;
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_PC, BPCOQPCD);
        if (BPCOQPCD.RC.RC_CODE != 0) {
            if (WS_IS_FLG == 'Y') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_TENOR_NO_VALID;
            } else {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_REF_TENOR_NO_VAL;
            }
            S000_ERR_MSG_PROC();
        }
    }
    public void R000_AUTHORIZE_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_REAL_AUTH);
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_AUTHORIZE;
        WS_AUTH_TYPE = 'A';
        JIBS_tmp_str[0] = "" + WS_REAL_AUTH;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        WS_LVL1 = JIBS_tmp_str[0].substring(0, 1).charAt(0);
        JIBS_tmp_str[0] = "" + WS_REAL_AUTH;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<2-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        WS_LVL2 = JIBS_tmp_str[0].substring(2 - 1, 2 + 1 - 1).charAt(0);
        SCCMSG SCCMSG = new SCCMSG();
        IBS.init(SCCGWA, SCCMSG);
        SCCMSG.MSGID = WS_ERR_MSG;
        SCCMSG.TYPE = WS_AUTH_TYPE;
        SCCMSG.LVL.LVL1 = WS_LVL1;
        SCCMSG.LVL.LVL2 = WS_LVL2;
        CEP.ERR(SCCGWA, SCCMSG);
    }
    public void R000_READ_BPTIRAT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIRAT);
        BPRIRAT.KEY.BR = BPCUINTU.BR;
        BPRIRAT.KEY.CCY = BPCUINTU.CCY;
        BPRIRAT.KEY.BASE_TYP = BPCUINTU.BASE_TYP;
        BPRIRAT.KEY.TENOR = BPCUINTU.TENOR;
        R000_READ_BPTIATM();
        WS_IRAT_HIGH = BPRIRAT.HIGH;
        WS_IRAT_LOW = BPRIRAT.LOW;
        CEP.TRC(SCCGWA, BPRIRAT.HIGH);
        CEP.TRC(SCCGWA, BPRIRAT.LOW);
    }
    public void R000_GET_HEAD_BRANCH_RATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRINTM);
        WS_LAST_RATE = 0;
        BPCRINTM.BR = SCCGWA.COMM_AREA.HQT_BANK;
        BPCRINTM.CCY = BPCUINTU.CCY;
        CEP.TRC(SCCGWA, BPCUINTU.BASE_TYP);
        if (BPCUINTU.BASE_TYP == null) BPCUINTU.BASE_TYP = "";
        JIBS_tmp_int = BPCUINTU.BASE_TYP.length();
        for (int i=0;i<3-JIBS_tmp_int;i++) BPCUINTU.BASE_TYP += " ";
        CEP.TRC(SCCGWA, BPCUINTU.BASE_TYP.substring(0, 1));
        BPCRINTM.BASE_TYP = BPCUINTU.BASE_TYP;
        CEP.TRC(SCCGWA, BPCRINTM.BASE_TYP);
        BPCRINTM.TENOR = BPCUINTU.TENOR;
        BPCRINTM.DT = BPCUINTU.DT;
        CEP.TRC(SCCGWA, BPCUINTU.DT);
        CEP.TRC(SCCGWA, BPCRINTM.DT);
        R000_READ_BPZRINTM_PROC1();
        R000_GET_BASE_RATE();
    }
    public void R000_COMPARE_RATE() throws IOException,SQLException,Exception {
        WS_DIFF_RATE = WS_NEW_RATE - WS_HQT_BANK_RATE;
        CEP.TRC(SCCGWA, WS_DIFF_RATE);
        CEP.TRC(SCCGWA, WS_NEW_RATE);
        CEP.TRC(SCCGWA, WS_HQT_BANK_RATE);
        if (BPRIRAT.FORMAT == 'P') {
            if (WS_HQT_BANK_RATE != 0) {
                WS_SAVE_DATA = ( WS_DIFF_RATE / WS_HQT_BANK_RATE ) * 100;
                CEP.TRC(SCCGWA, WS_SAVE_DATA);
            } else {
                CEP.TRC(SCCGWA, "HQT BANK NULL");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_HEAD_BR_NO_INPUT;
                S000_ERR_MSG_PROC();
            }
            if (WS_SAVE_DATA >= 1000) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_OVER_BR_RATE_SCO;
                CEP.TRC(SCCGWA, "LILVPIANCHA1");
                S000_ERR_MSG_PROC();
            } else {
                WS_DIFF_RATE = WS_SAVE_DATA;
            }
        }
        CEP.TRC(SCCGWA, WS_DIFF_RATE);
        CEP.TRC(SCCGWA, WS_DIFF_RATE);
        CEP.TRC(SCCGWA, WS_IRAT_HIGH);
        CEP.TRC(SCCGWA, WS_IRAT_LOW);
        if (WS_IRAT_FLG == 'Y') {
            if (WS_DIFF_RATE >= 0) {
                if (WS_DIFF_RATE > WS_IRAT_HIGH) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_OVER_BR_RATE_SCO;
                    CEP.TRC(SCCGWA, "LILVPIANCHA2");
                    S000_ERR_MSG_PROC();
                }
            } else {
                WS_DIFF_RATE = 0 - WS_DIFF_RATE;
                if (WS_DIFF_RATE > WS_IRAT_LOW) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_OVER_BR_RATE_SCO;
                    CEP.TRC(SCCGWA, "LILVPIANCHA3");
                    S000_ERR_MSG_PROC();
                }
            }
        }
    }
    public void R000_COMPUTE_RATE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_COMPUTE_RATE);
        CEP.TRC(SCCGWA, "1288");
        CEP.TRC(SCCGWA, WS_TMP_INF.WS_DIFF);
        CEP.TRC(SCCGWA, "ZUINTU-COMPUTE");
        if (WS_TMP_INF.WS_FORMAT == 'D') {
            WS_COMPUTE_RATE = WS_COMPUTE_RATE * ( 1 + WS_TMP_INF.WS_DIFF );
        } else {
            WS_COMPUTE_RATE = WS_COMPUTE_RATE * ( 1 + WS_TMP_INF.WS_DIFF / 100 );
            bigD = new BigDecimal(WS_COMPUTE_RATE);
            WS_COMPUTE_RATE = bigD.setScale(8, RoundingMode.HALF_UP).doubleValue();
        }
    }
    public void R000_CHECK_BPTIRPD() throws IOException,SQLException,Exception {
        R000_READ_BPTIRPD();
        if (BPRIRPD.CONTRL == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_TENOR_COMPAGES_U;
            S000_ERR_MSG_PROC();
        }
    }
    public void R000_CHECK_REF_BPTIRPD() throws IOException,SQLException,Exception {
        R000_READ_BPTIRPD();
        if (BPRIRPD.CONTRL == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_REF_INT_UNDO;
            S000_ERR_MSG_PROC();
        }
    }
    public void R000_CHECK_PARAMETER() throws IOException,SQLException,Exception {
        WS_MOST_REF_DEGREE = 5;
        CEP.TRC(SCCGWA, WS_MOST_REF_DEGREE);
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        BPCPRMM.DAT_PTR = BPRPRMT;
        IBS.CALLCPN(SCCGWA, K_CMP_MAIN_PARM, BPCPRMM);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void R000_READ_BPZRINTM() throws IOException,SQLException,Exception {
        BPCRINTM.FUNC = 'I';
        S000_CALL_BPZRINTM();
    }
    public void R000_READ_BPZRINTM_PROC() throws IOException,SQLException,Exception {
        BPCRINTM.FUNC = 'I';
        S000_CALL_BPZRINTM();
        if (WS_INTR_RECORD_FLG == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_HEAD_BR_NO_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void R000_READ_BPZRINTM_PROC1() throws IOException,SQLException,Exception {
        BPCRINTM.FUNC = 'I';
        S000_CALL_BPZRINTM();
        if (WS_INTR_RECORD_FLG == 'N') {
            BPCRINTM.BR = 999999;
            BPCRINTM.DT = BPCUINTU.DT;
            CEP.TRC(SCCGWA, BPCUINTU.DT);
            CEP.TRC(SCCGWA, BPCRINTM.DT);
            BPCRINTM.FUNC = 'I';
            S000_CALL_BPZRINTM();
            if (WS_INTR_RECORD_FLG == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_HEAD_BR_NO_INPUT;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void R000_READUP_BPTINTR() throws IOException,SQLException,Exception {
        BPCRINTM.FUNC = 'R';
        S000_CALL_BPZRINTM();
        if (WS_INTR_RECORD_FLG == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_VALUE_NOT_INPUT;
            S000_ERR_MSG_PROC();
        }
        BPCOLD.BR = BPCRINTM.BR;
        BPCOLD.CCY = BPCRINTM.CCY;
        BPCOLD.BASE_TYP = BPCRINTM.BASE_TYP;
        BPCOLD.TENOR = BPCRINTM.TENOR;
        BPCOLD.REF_BR = BPCRINTM.REF_BR;
        BPCOLD.REF_CCY = BPCRINTM.REF_CCY;
        BPCOLD.REF_BASE_TYP = BPCRINTM.REF_BASE_TYP;
        BPCOLD.REF_TENOR = BPCRINTM.REF_TENOR;
        BPCOLD.FORMAT = BPCRINTM.FORMAT;
        BPCOLD.DIFF = BPCRINTM.DIFF;
        BPCOLD.RATE = BPCRINTM.RATE;
        BPCOLD.REF_DEPTH = BPCRINTM.REF_DEPTH;
    }
    public void R000_INSERT_BPTINTR() throws IOException,SQLException,Exception {
        BPCRINTM.FUNC = 'A';
        S000_CALL_BPZRINTM();
        R000_TXN_HIS_PROCESS();
    }
    public void R000_UPDATE_BPTINTR() throws IOException,SQLException,Exception {
        BPCRINTM.FUNC = 'U';
        S000_CALL_BPZRINTM();
        BPCNEW.BR = BPCRINTM.BR;
        BPCNEW.CCY = BPCRINTM.CCY;
        BPCNEW.BASE_TYP = BPCRINTM.BASE_TYP;
        BPCNEW.TENOR = BPCRINTM.TENOR;
        BPCNEW.REF_BR = BPCRINTM.REF_BR;
        BPCNEW.REF_CCY = BPCRINTM.REF_CCY;
        BPCNEW.REF_BASE_TYP = BPCRINTM.REF_BASE_TYP;
        BPCNEW.REF_TENOR = BPCRINTM.REF_TENOR;
        BPCNEW.FORMAT = BPCRINTM.FORMAT;
        BPCNEW.DIFF = BPCRINTM.DIFF;
        BPCNEW.RATE = BPCRINTM.RATE;
        BPCNEW.REF_DEPTH = BPCRINTM.REF_DEPTH;
        R000_TXN_HIS_PROCESS();
    }
    public void R000_READ_BPTIATM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRIATM);
        BPCRIATM.INFO.FUNC = 'Q';
        BPCRIATM.INFO.OPT_2 = 'O';
        WS_IRAT_FLG = ' ';
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.HQT_BANK);
        CEP.TRC(SCCGWA, BPRIRAT.KEY.BR);
        CEP.TRC(SCCGWA, BPRIRAT.KEY.CCY);
        CEP.TRC(SCCGWA, BPRIRAT.KEY.BASE_TYP);
        CEP.TRC(SCCGWA, BPRIRAT.KEY.TENOR);
        S000_CALL_BPZRIATM();
        if (BPCRIATM.RETURN_INFO == 'F') {
            WS_IRAT_FLG = 'Y';
        }
        if (BPCRIATM.RETURN_INFO == 'N') {
            BPRIRAT.KEY.BR = SCCGWA.COMM_AREA.HQT_BANK;
            IBS.init(SCCGWA, BPCRIATM);
            BPCRIATM.INFO.FUNC = 'Q';
            BPCRIATM.INFO.OPT_2 = 'O';
            S000_CALL_BPZRIATM();
            if (BPCRIATM.RETURN_INFO == 'F') {
                WS_IRAT_FLG = 'Y';
            }
            if (BPCRIATM.RETURN_INFO == 'N') {
                WS_IRAT_FLG = 'N';
            }
        }
    }
    public void R000_READUP_BPTIHIT() throws IOException,SQLException,Exception {
        BPCRHITM.FUNC = 'R';
        S000_CALL_BPZRHITM();
    }
    public void R000_UPDATE_BPTIHIT() throws IOException,SQLException,Exception {
        BPCRHITM.FUNC = 'U';
        S000_CALL_BPZRHITM();
    }
    public void R000_INSERT_BPTIHIT() throws IOException,SQLException,Exception {
        BPCRHITM.FUNC = 'A';
        S000_CALL_BPZRHITM();
    }
    public void R000_READ_BPTIHIS() throws IOException,SQLException,Exception {
        BPCRHISM.FUNC = 'I';
        S000_CALL_BPZRHISM();
    }
    public void R000_UPDATE_BPTIHIS() throws IOException,SQLException,Exception {
        BPCRHISM.FUNC = 'U';
        S000_CALL_BPZRHISM();
    }
    public void R000_INSERT_BPTIHIS() throws IOException,SQLException,Exception {
        BPCRHISM.FUNC = 'A';
        S000_CALL_BPZRHISM();
    }
    public void R000_READ_BPTIRPD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIRPD);
        BPRIRPD.KEY.CCY = WS_CCY;
        BPRIRPD.KEY.BASE_TYP = WS_BASE_TYP;
        BPRIRPD.KEY.TENOR = WS_TENOR;
        IBS.init(SCCGWA, BPCRIPDM);
        BPCRIPDM.INFO.FUNC = 'Q';
        BPCRIPDM.INFO.OPT_2 = 'O';
        CEP.TRC(SCCGWA, BPRIRPD.KEY.CCY);
        CEP.TRC(SCCGWA, BPRIRPD.KEY.BASE_TYP);
        CEP.TRC(SCCGWA, BPRIRPD.KEY.TENOR);
        S000_CALL_BPZRIPDM();
    }
    public void R000_READ_BPTIDEV() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRDEVM);
        BPCRDEVM.INFO.FUNC = 'I';
        S000_CALL_BPZRDEVM();
        if (BPCRDEVM.RETURN_INFO == 'N') {
            IBS.init(SCCGWA, BPRIDEV);
            BPRIDEV.KEY.TYPE = 'M';
            BPRIDEV.KEY.BR = SCCGWA.COMM_AREA.HQT_BANK;
            BPRIDEV.KEY.CCY = BPCUINTU.CCY;
            BPRIDEV.KEY.BASE_TYP = BPCUINTU.BASE_TYP;
            BPRIDEV.KEY.TENOR = BPCUINTU.TENOR;
            CEP.TRC(SCCGWA, "HOT RATE");
            CEP.TRC(SCCGWA, BPRIDEV.KEY.BR);
            CEP.TRC(SCCGWA, BPRIDEV.KEY.CCY);
            CEP.TRC(SCCGWA, BPRIDEV.KEY.BASE_TYP);
            CEP.TRC(SCCGWA, BPRIDEV.KEY.TENOR);
            IBS.init(SCCGWA, BPCRDEVM);
            BPCRDEVM.INFO.FUNC = 'I';
            S000_CALL_BPZRDEVM();
            if (BPCRDEVM.RETURN_INFO == 'N') {
            }
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CMP_TXN_HIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRINTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_INTR_MAINT, BPCRINTM);
        WS_INTR_RECORD_FLG = 'Y';
        if (BPCRINTM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRINTM.RC);
            S000_ERR_MSG_PROC();
        }
        if (BPCRINTM.RETURN_INFO == 'N') {
            WS_INTR_RECORD_FLG = 'N';
        }
    }
    public void S000_CALL_BPZRINTB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_INTR_BRW, BPCRINTB);
        if (BPCRINTB.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRINTB.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_INT_RECORD_NOT_EXIST)) {
                WS_YES_NO_REF_FLG = 'Y';
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRINTB.RC);
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void S000_CALL_BPZRIATM() throws IOException,SQLException,Exception {
        BPCRIATM.INFO.POINTER = BPRIRAT;
        IBS.CALLCPN(SCCGWA, CPN_R_IRAT_MAINT, BPCRIATM);
        if (BPCRIATM.RC.RC_CODE != 0) {
            if (BPCRIATM.RETURN_INFO != 'N') {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRIATM.RC);
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void S000_CALL_BPZRHITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_IHIT_MAINT, BPCRHITM);
        WS_IHIT_RECORD_FLG = 'Y';
        if (BPCRHITM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRHITM.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_INT_RECORD_NOT_EXIST)) {
                WS_IHIT_RECORD_FLG = 'N';
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRHITM.RC);
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void S000_CALL_BPZRHISM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_IHIS_MAINT, BPCRHISM);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCRHISM.RC);
        if (BPCRHISM.RC.RC_CODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_INT_RECORD_NOT_EXIST)) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRHISM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRIPDM() throws IOException,SQLException,Exception {
        BPCRIPDM.INFO.POINTER = BPRIRPD;
        IBS.CALLCPN(SCCGWA, CPN_R_BK_MT, BPCRIPDM);
        if (BPCRIPDM.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "11");
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRIPDM.RC);
            S000_ERR_MSG_PROC();
        }
        if (BPCRIPDM.RETURN_INFO == 'N') {
            CEP.TRC(SCCGWA, "22");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_TENOR_COMPAGES_U;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRDEVM() throws IOException,SQLException,Exception {
        BPCRDEVM.INFO.POINTER = BPRIDEV;
        IBS.CALLCPN(SCCGWA, CPN_R_IDEV_MAINT, BPCRDEVM);
        if (BPCRDEVM.RC.RC_CODE != 0) {
            if (BPCRDEVM.RETURN_INFO != 'N') {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRDEVM.RC);
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
