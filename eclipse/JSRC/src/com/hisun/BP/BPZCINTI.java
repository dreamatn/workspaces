package com.hisun.BP;

import java.util.ArrayList;
import java.util.List;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZCINTI {
    String JIBS_tmp_str[] = new String[10];
    BPZCINTI_WS_RATE_INFO WS_RATE_INFO;
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String CPN_R_IDEV_MAINT = "BP-R-IDEV-MAINT   ";
    String CPN_R_INTR_MAINT = "BP-R-INTR-MAINT   ";
    String CPN_R_IHIT_BRW_LAST = "BP-R-IHIT-BRW-LAST";
    String CPN_R_IHIT_BRW_NEXT = "BP-R-IHIT-BRW-NEXT";
    String CPN_R_IRPD_MAINT = "BP-R-IRPD-MAINT   ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG      ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_P_INQ_PC = "BP-P-INQ-PC       ";
    String CPN_R_BK_MT = "BP-R-IRPD-MAINT";
    String K_CMP_MAIN_PARM = "BP-PARM-MAINTAIN";
    String K_REFDE_TYPE = "REFDE";
    String K_REFDE_CODE = "001";
    String K_RBASE = "RBASE";
    String K_RTENO = "RTENO";
    int K_HQ_BK = "999999";
    String WS_ERR_MSG = " ";
    short WS_I = 0;
    short WS_DEGREE = 0;
    short WS_RECORD_NUMBER = 0;
    int WS_BR = 0;
    String WS_CCY = " ";
    String WS_BASE_TYP = " ";
    String WS_TENOR = " ";
    int WS_LAST_DATE = 0;
    char WS_IRPD_DEL_FLG = ' ';
    char WS_IRPD_DEL_FLG1 = ' ';
    int WS_TMP_BR = 0;
    String WS_TMP_CCY = " ";
    String WS_TMP_BASE_TYP = " ";
    String WS_TMP_TENOR = " ";
    int WS_MOST_REF_DEGREE = 0;
    BPZCINTI_WS_IDEV_TABLE WS_IDEV_TABLE = new BPZCINTI_WS_IDEV_TABLE();
    double WS_DEV_DIFF = 0;
    double WS_NEW_RATE = 0;
    double WS_LAST_RATE = 0;
    short WS_REAL_AUTH = 0;
    char WS_AUTH_TYPE = ' ';
    char WS_LVL1 = ' ';
    char WS_LVL2 = ' ';
    String WS_BDESC = " ";
    String WS_TDESC = " ";
    char WS_BASETYP_STS = ' ';
    int WS_INTDAY = 0;
    int WS_CINTI_DT = 0;
    List<BPZCINTI_WS_RATE_INFO> WS_RATE_INFO = new ArrayList<BPZCINTI_WS_RATE_INFO>();
    int WS_RATE_DT = 0;
    int WS_RATE_BR = 0;
    String WS_RATE_CCY = " ";
    String WS_RATE_BASE_TYP = " ";
    String WS_RATE_TENOR = " ";
    char WS_IRPD_FLG = ' ';
    char WS_IRPD_CONTROL_FLG = ' ';
    char WS_REF_RECORD_FLG = ' ';
    char WS_CHECK_RATE_FLG = ' ';
    char WS_CONDITION_FLG = ' ';
    char WS_CHECK_HITRATE_FLG = ' ';
    char WS_RATE_REF_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPRIRPD BPRIRPD = new BPRIRPD();
    BPRRTFW BPRRTFW = new BPRRTFW();
    BPCRRTFW BPCRRTFW = new BPCRRTFW();
    BPCRIPDM BPCRIPDM = new BPCRIPDM();
    BPCRINTM BPCRINTM = new BPCRINTM();
    BPCRHITL BPCRHITL = new BPCRHITL();
    BPCRHITN BPCRHITN = new BPCRHITN();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPRIDEV BPRIDEV = new BPRIDEV();
    BPCRDEVM BPCRDEVM = new BPCRDEVM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    SCCGWA SCCGWA;
    BPCCINTI BPCCINTI;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA, BPCCINTI BPCCINTI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCCINTI = BPCCINTI;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZCINTI return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCCINTI.RC);
        IBS.init(SCCGWA, BPCPQORG);
        if (BPCCINTI.BASE_INFO.DT == 0) {
            BPCCINTI.BASE_INFO.DT = SCCGWA.COMM_AREA.AC_DATE;
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCCINTI.FUNC);
        if (BPCCINTI.FUNC == 'I') {
            B210_INQ_VALID_RATE_CONTROL();
            if (pgmRtn) return;
            B220_GET_BASE_RATE();
            if (pgmRtn) return;
        } else if (BPCCINTI.FUNC == 'C') {
            B210_INQ_VALID_RATE_CONTROL();
            if (pgmRtn) return;
            B300_CHK_RATE();
            if (pgmRtn) return;
        } else if (BPCCINTI.FUNC == 'P') {
            B220_GET_BASE_RATE();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_FUNC_ERROR, BPCCINTI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.BR);
        if (BPCCINTI.BASE_INFO.BR != 0 
            && BPCCINTI.BASE_INFO.BR != ' ' 
            && BPCCINTI.BASE_INFO.BR != 999999) {
            WS_BR = BPCCINTI.BASE_INFO.BR;
            R000_CHECK_BRANCH();
            if (pgmRtn) return;
        }
        if (BPCCINTI.BASE_INFO.CCY.trim().length() > 0) {
            WS_CCY = BPCCINTI.BASE_INFO.CCY;
            CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.CCY);
            R000_CHECK_CCY();
            if (pgmRtn) return;
            BPCCINTI.BASE_INFO.INTDAY = WS_INTDAY;
        }
        if (BPCCINTI.BASE_INFO.DT == ' ') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_DATE_NO_VALID, BPCCINTI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCCINTI.BASE_INFO.BASE_TYP.trim().length() > 0) {
            WS_BASE_TYP = BPCCINTI.BASE_INFO.BASE_TYP;
            R000_CHECK_BASE_TYP();
            if (pgmRtn) return;
            BPCCINTI.BASE_INFO.BDESC = WS_BDESC;
            BPCCINTI.BASE_INFO.BASETYP_STS = WS_BASETYP_STS;
            CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.BDESC);
        }
        if (BPCCINTI.BASE_INFO.TENOR.trim().length() > 0) {
            WS_TENOR = BPCCINTI.BASE_INFO.TENOR;
            CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.TENOR);
            R000_CHECK_TENOR();
            if (pgmRtn) return;
            BPCCINTI.BASE_INFO.TDESC = WS_TDESC;
            CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.TDESC);
        }
        CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.BR);
        CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.CCY);
        CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.BASE_TYP);
        CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.TENOR);
        CEP.TRC(SCCGWA, "010101010101");
        CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.DT);
        IBS.init(SCCGWA, BPRIRPD);
        WS_CCY = BPCCINTI.BASE_INFO.CCY;
        WS_BASE_TYP = BPCCINTI.BASE_INFO.BASE_TYP;
        WS_TENOR = BPCCINTI.BASE_INFO.TENOR;
        R000_CHECK_BPTIRPD();
        if (pgmRtn) return;
        if (WS_IRPD_FLG == 'D' 
            || WS_IRPD_CONTROL_FLG == 'D') {
            CEP.TRC(SCCGWA, "01");
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_TENOR_COMPAGES_U, BPCCINTI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R000_CHECK_BPZPARM();
        if (pgmRtn) return;
    }
    public void B210_INQ_VALID_RATE_CONTROL() throws IOException,SQLException,Exception {
        if (BPCCINTI.BASE_INFO.BR == ' ' 
            || BPCCINTI.BASE_INFO.BR == 0) {
            BPCCINTI.BASE_INFO.BR = SCCGWA.COMM_AREA.HQT_BANK;
            CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.BR);
        }
        if (BPCCINTI.BASE_INFO.CCY.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_CCY_NO_VALID, BPCCINTI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCCINTI.BASE_INFO.BASE_TYP.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_BASETYP_NO_VALID, BPCCINTI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCCINTI.BASE_INFO.TENOR.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_TENOR_NO_VALID, BPCCINTI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_CCY = BPCCINTI.BASE_INFO.CCY;
        WS_BASE_TYP = BPCCINTI.BASE_INFO.BASE_TYP;
        WS_TENOR = BPCCINTI.BASE_INFO.TENOR;
        R000_CHECK_BPTIRPD();
        if (pgmRtn) return;
        if (WS_IRPD_FLG == 'D' 
            || WS_IRPD_CONTROL_FLG == 'D') {
            CEP.TRC(SCCGWA, "02");
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_TENOR_COMPAGES_U, BPCCINTI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_IRPD_DEL_FLG = BPRIRPD.DEL_FLG;
    }
    public void B300_CHK_RATE() throws IOException,SQLException,Exception {
        B221_GET_TODAY_BASE_RATE();
        if (pgmRtn) return;
        if (BPCCINTI.RC.RC_CODE != 0) {
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCCINTI.BASE_INFO.RATE != BPCCINTI.BASE_INFO.OWN_RATE) {
            R000_CHECK_IDEV();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_IDEV() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIDEV);
        BPRIDEV.KEY.TYPE = 'P';
        BPRIDEV.KEY.BR = BPCCINTI.BASE_INFO.BR;
        BPRIDEV.KEY.CCY = BPCCINTI.BASE_INFO.CCY;
        BPRIDEV.KEY.BASE_TYP = BPCCINTI.BASE_INFO.BASE_TYP;
        BPRIDEV.KEY.TENOR = BPCCINTI.BASE_INFO.TENOR;
        R000_READ_BPTIDEV();
        if (pgmRtn) return;
        WS_IDEV_TABLE.OCCURS18[1-1].WS_IDEV = BPRIDEV.DEV1;
        WS_IDEV_TABLE.OCCURS18[1-1].WS_AUTH = BPRIDEV.AL1;
        WS_IDEV_TABLE.OCCURS18[2-1].WS_IDEV = BPRIDEV.DEV2;
        WS_IDEV_TABLE.OCCURS18[2-1].WS_AUTH = BPRIDEV.AL2;
        WS_IDEV_TABLE.OCCURS18[3-1].WS_IDEV = BPRIDEV.DEV3;
        WS_IDEV_TABLE.OCCURS18[3-1].WS_AUTH = BPRIDEV.AL3;
        WS_IDEV_TABLE.OCCURS18[4-1].WS_IDEV = BPRIDEV.DEV4;
        WS_IDEV_TABLE.OCCURS18[4-1].WS_AUTH = BPRIDEV.AL4;
        WS_IDEV_TABLE.OCCURS18[5-1].WS_IDEV = BPRIDEV.DEV5;
        WS_IDEV_TABLE.OCCURS18[5-1].WS_AUTH = BPRIDEV.AL5;
        WS_IDEV_TABLE.OCCURS18[6-1].WS_IDEV = BPRIDEV.DEV6;
        WS_IDEV_TABLE.OCCURS18[6-1].WS_AUTH = BPRIDEV.AL6;
        WS_IDEV_TABLE.OCCURS18[7-1].WS_IDEV = BPRIDEV.DEV7;
        WS_IDEV_TABLE.OCCURS18[7-1].WS_AUTH = BPRIDEV.AL7;
        WS_IDEV_TABLE.OCCURS18[8-1].WS_IDEV = BPRIDEV.DEV8;
        WS_IDEV_TABLE.OCCURS18[8-1].WS_AUTH = BPRIDEV.AL8;
        WS_IDEV_TABLE.OCCURS18[9-1].WS_IDEV = BPRIDEV.DEV9;
        WS_IDEV_TABLE.OCCURS18[9-1].WS_AUTH = BPRIDEV.AL9;
        WS_IDEV_TABLE.OCCURS18[10-1].WS_IDEV = BPRIDEV.DEV10;
        WS_IDEV_TABLE.OCCURS18[10-1].WS_AUTH = BPRIDEV.AL10;
        WS_DEV_DIFF = BPCCINTI.BASE_INFO.OWN_RATE - BPCCINTI.BASE_INFO.RATE;
        if (BPRIDEV.KEY.TYPE == 'P') {
            if (BPCCINTI.BASE_INFO.OWN_RATE == 0) {
                if (BPCCINTI.BASE_INFO.RATE != 0) {
                    WS_DEV_DIFF = WS_DEV_DIFF / BPCCINTI.BASE_INFO.RATE;
                }
            } else {
                WS_DEV_DIFF = WS_DEV_DIFF / BPCCINTI.BASE_INFO.OWN_RATE;
            }
        }
        if (WS_DEV_DIFF < 0) {
            WS_DEV_DIFF = 0 - WS_DEV_DIFF;
        }
        WS_REAL_AUTH = 99;
        for (WS_I = 1; WS_I <= BPRIDEV.CNT 
            && WS_I <= 10; WS_I += 1) {
            if (WS_DEV_DIFF < WS_IDEV_TABLE.OCCURS18[WS_I-1].WS_IDEV) {
                WS_REAL_AUTH = WS_IDEV_TABLE.OCCURS18[WS_I-1].WS_AUTH;
            }
        }
        R000_AUTHORIZE_PROC();
        if (pgmRtn) return;
    }
    public void B220_GET_BASE_RATE() throws IOException,SQLException,Exception {
        if (BPCCINTI.BASE_INFO.DT == 0 
            || BPCCINTI.BASE_INFO.DT >= SCCGWA.COMM_AREA.AC_DATE) {
            B220_01_CHECK_RATE();
            if (pgmRtn) return;
            if (BPCCINTI.BASE_INFO.DT > SCCGWA.COMM_AREA.AC_DATE) {
                B221_GET_TODAY_BASE_RATE();
                if (pgmRtn) return;
            } else {
                B221_GET_TODAY_BASE_RATE();
                if (pgmRtn) return;
            }
        } else {
            B220_02_CHECK_RATE();
            if (pgmRtn) return;
            if (WS_CONDITION_FLG == 'N') {
                B220_01_CHECK_HIS_RATE();
                if (pgmRtn) return;
                B222_GET_HISTORY_BASE_RATE();
                if (pgmRtn) return;
            } else {
                B221_GET_TODAY_BASE_RATE();
                if (pgmRtn) return;
            }
        }
    }
    public void B220_01_CHECK_RATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRINTM);
        BPCRINTM.FUNC = 'I';
        BPCRINTM.BR = BPCCINTI.BASE_INFO.BR;
        BPCRINTM.CCY = BPCCINTI.BASE_INFO.CCY;
        BPCRINTM.BASE_TYP = BPCCINTI.BASE_INFO.BASE_TYP;
        BPCRINTM.TENOR = BPCCINTI.BASE_INFO.TENOR;
        BPCRINTM.DT = BPCCINTI.BASE_INFO.DT;
        R000_01_CALL_BPZRINTM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "ASD");
        IBS.init(SCCGWA, BPCPQORG);
        if (BPCCINTI.BASE_INFO.BR != 999999) {
            BPCPQORG.BR = BPCCINTI.BASE_INFO.BR;
            R000_GET_UPPER_BRANCE();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "DSA");
        while (WS_CHECK_RATE_FLG != 'N' 
            && (BPCPQORG.SUPR_BR != BPCPQORG.BR) 
            && (BPCPQORG.BR != 999999)) {
            IBS.init(SCCGWA, BPCPQORG);
            if (BPCCINTI.BASE_INFO.BR != 999999) {
                BPCPQORG.BR = BPCCINTI.BASE_INFO.BR;
                R000_GET_UPPER_BRANCE();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, BPCPQORG.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_ORG_NOTFND)) {
                BPCCINTI.BASE_INFO.BR = BPCPQORG.SUPR_BR;
                CEP.TRC(SCCGWA, BPCPQORG.SUPR_BR);
                CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.BR);
                IBS.init(SCCGWA, BPCRINTM);
                BPCRINTM.FUNC = 'I';
                BPCRINTM.BR = BPCPQORG.SUPR_BR;
                CEP.TRC(SCCGWA, BPCPQORG.SUPR_BR);
                CEP.TRC(SCCGWA, BPCRINTM.BR);
                BPCRINTM.CCY = BPCCINTI.BASE_INFO.CCY;
                BPCRINTM.BASE_TYP = BPCCINTI.BASE_INFO.BASE_TYP;
                BPCRINTM.TENOR = BPCCINTI.BASE_INFO.TENOR;
                BPCRINTM.DT = BPCCINTI.BASE_INFO.DT;
                R000_01_CALL_BPZRINTM();
                if (pgmRtn) return;
            }
        }
        if (WS_CHECK_RATE_FLG == 'D' 
            && (BPCPQORG.SUPR_BR == BPCPQORG.BR)) {
            IBS.init(SCCGWA, BPCRINTM);
            BPCRINTM.FUNC = 'I';
            BPCRINTM.BR = K_HQ_BK;
            BPCCINTI.BASE_INFO.BR = K_HQ_BK;
            BPCRINTM.CCY = BPCCINTI.BASE_INFO.CCY;
            BPCRINTM.BASE_TYP = BPCCINTI.BASE_INFO.BASE_TYP;
            BPCRINTM.TENOR = BPCCINTI.BASE_INFO.TENOR;
            BPCRINTM.DT = BPCCINTI.BASE_INFO.DT;
            R000_01_CALL_BPZRINTM();
            if (pgmRtn) return;
            if (WS_CHECK_RATE_FLG == 'D') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_VALUE_NOT_INPUT, BPCCINTI.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B220_02_CHECK_RATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRINTM);
        BPCRINTM.FUNC = 'I';
        BPCRINTM.BR = BPCCINTI.BASE_INFO.BR;
        BPCRINTM.CCY = BPCCINTI.BASE_INFO.CCY;
        BPCRINTM.BASE_TYP = BPCCINTI.BASE_INFO.BASE_TYP;
        BPCRINTM.TENOR = BPCCINTI.BASE_INFO.TENOR;
        BPCRINTM.DT = BPCCINTI.BASE_INFO.DT;
        R000_01_CALL_BPZRINTM();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = BPCCINTI.BASE_INFO.BR;
        R000_GET_UPPER_BRANCE();
        if (pgmRtn) return;
        while (WS_CHECK_RATE_FLG != 'N' 
            && (BPCPQORG.SUPR_BR != BPCPQORG.BR) 
            && (BPCPQORG.BR != 999999)) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = BPCCINTI.BASE_INFO.BR;
            R000_GET_UPPER_BRANCE();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPQORG.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_ORG_NOTFND)) {
                BPCCINTI.BASE_INFO.BR = BPCPQORG.SUPR_BR;
                CEP.TRC(SCCGWA, BPCPQORG.SUPR_BR);
                CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.BR);
                IBS.init(SCCGWA, BPCRINTM);
                BPCRINTM.FUNC = 'I';
                BPCRINTM.BR = BPCPQORG.SUPR_BR;
                CEP.TRC(SCCGWA, BPCPQORG.SUPR_BR);
                CEP.TRC(SCCGWA, BPCRINTM.BR);
                BPCRINTM.CCY = BPCCINTI.BASE_INFO.CCY;
                BPCRINTM.BASE_TYP = BPCCINTI.BASE_INFO.BASE_TYP;
                BPCRINTM.TENOR = BPCCINTI.BASE_INFO.TENOR;
                BPCRINTM.DT = BPCCINTI.BASE_INFO.DT;
                R000_01_CALL_BPZRINTM();
                if (pgmRtn) return;
            }
        }
        if (WS_CHECK_RATE_FLG == 'D' 
            && (BPCPQORG.SUPR_BR == BPCPQORG.BR)) {
            IBS.init(SCCGWA, BPCRINTM);
            BPCRINTM.FUNC = 'I';
            BPCRINTM.BR = K_HQ_BK;
            BPCCINTI.BASE_INFO.BR = K_HQ_BK;
            BPCRINTM.CCY = BPCCINTI.BASE_INFO.CCY;
            BPCRINTM.BASE_TYP = BPCCINTI.BASE_INFO.BASE_TYP;
            BPCRINTM.TENOR = BPCCINTI.BASE_INFO.TENOR;
            BPCRINTM.DT = BPCCINTI.BASE_INFO.DT;
            R000_01_CALL_BPZRINTM();
            if (pgmRtn) return;
            if (WS_CHECK_RATE_FLG == 'D') {
                CEP.TRC(SCCGWA, "03");
                WS_CONDITION_FLG = 'N';
            }
        }
    }
    public void B220_01_CHECK_HIS_RATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRHITL);
        WS_LAST_RATE = 0;
        BPCRHITL.DT = BPCCINTI.BASE_INFO.DT;
        BPCRHITL.BR = BPCCINTI.BASE_INFO.BR;
        BPCRHITL.CCY = BPCCINTI.BASE_INFO.CCY;
        BPCRHITL.BASE_TYP = BPCCINTI.BASE_INFO.BASE_TYP;
        BPCRHITL.TENOR = BPCCINTI.BASE_INFO.TENOR;
        CEP.TRC(SCCGWA, BPCRHITL.DT);
        CEP.TRC(SCCGWA, BPCRHITL.BR);
        CEP.TRC(SCCGWA, BPCRHITL.CCY);
        CEP.TRC(SCCGWA, BPCRHITL.BASE_TYP);
        CEP.TRC(SCCGWA, BPCRHITL.TENOR);
        R000_CALL_BPZRHITL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "ABC");
        while (WS_CHECK_RATE_FLG != 'N' 
            && (BPCPQORG.SUPR_BR != BPCPQORG.BR) 
            && (BPCPQORG.BR != 999999)) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = BPCCINTI.BASE_INFO.BR;
            R000_GET_UPPER_BRANCE();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPQORG.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_ORG_NOTFND)) {
                BPCCINTI.BASE_INFO.BR = BPCPQORG.SUPR_BR;
                CEP.TRC(SCCGWA, BPCPQORG.SUPR_BR);
                CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.BR);
                IBS.init(SCCGWA, BPCRHITL);
                BPCRHITL.BR = BPCPQORG.SUPR_BR;
                CEP.TRC(SCCGWA, BPCPQORG.SUPR_BR);
                CEP.TRC(SCCGWA, BPCRINTM.BR);
                WS_LAST_RATE = 0;
                BPCRHITL.DT = BPCCINTI.BASE_INFO.DT;
                BPCRHITL.BR = BPCCINTI.BASE_INFO.BR;
                BPCRHITL.CCY = BPCCINTI.BASE_INFO.CCY;
                BPCRHITL.BASE_TYP = BPCCINTI.BASE_INFO.BASE_TYP;
                BPCRHITL.TENOR = BPCCINTI.BASE_INFO.TENOR;
                R000_CALL_BPZRHITL();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, "O3CHECKO3");
        CEP.TRC(SCCGWA, BPCPQORG.BR);
        CEP.TRC(SCCGWA, BPCPQORG.SUPR_BR);
        if (WS_CHECK_HITRATE_FLG == 'N' 
            && (BPCPQORG.SUPR_BR == BPCPQORG.BR)) {
            CEP.TRC(SCCGWA, "030303");
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_VALUE_NOT_INPUT, BPCCINTI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B221_GET_TODAY_BASE_RATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRINTM);
        WS_LAST_RATE = 0;
        BPCRINTM.FUNC = 'I';
        BPCRINTM.BR = BPCCINTI.BASE_INFO.BR;
        BPCRINTM.CCY = BPCCINTI.BASE_INFO.CCY;
        BPCRINTM.BASE_TYP = BPCCINTI.BASE_INFO.BASE_TYP;
        BPCRINTM.TENOR = BPCCINTI.BASE_INFO.TENOR;
        BPCRINTM.DT = BPCCINTI.BASE_INFO.DT;
        WS_CINTI_DT = BPCCINTI.BASE_INFO.DT;
        CEP.TRC(SCCGWA, WS_CINTI_DT);
        R000_CALL_BPZRINTM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCRINTM.REF_DEPTH);
        BPCCINTI.EXT_INFO.REF_DEPTH = BPCRINTM.REF_DEPTH;
        BPCCINTI.BASE_INFO.N_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCCINTI.BASE_INFO.DT = BPCRINTM.DT;
        CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.N_DT);
        CEP.TRC(SCCGWA, BPCRINTM.REF_BR);
        CEP.TRC(SCCGWA, "EXEC HERE");
        CEP.TRC(SCCGWA, BPCRINTM.REF_CCY);
        CEP.TRC(SCCGWA, BPCRINTM.REF_BASE_TYP);
        CEP.TRC(SCCGWA, BPCRINTM.REF_TENOR);
        if (BPCRINTM.REF_BR != 0 
            && BPCRINTM.REF_CCY.trim().length() > 0 
            && BPCRINTM.REF_BASE_TYP.trim().length() > 0 
            && BPCRINTM.REF_TENOR.trim().length() > 0) {
            WS_REF_RECORD_FLG = 'Y';
            WS_RATE_REF_FLG = 'Y';
            for (WS_I = 1; WS_I <= WS_MOST_REF_DEGREE 
                && WS_REF_RECORD_FLG != 'N'; WS_I += 1) {
                if (WS_CINTI_DT > SCCGWA.COMM_AREA.AC_DATE) {
                    WS_RATE_INFO = WS_RATE_INFO.get(WS_I-1);
                    WS_RATE_INFO.WS_DT = BPCRINTM.DT;
                    WS_RATE_INFO.set(WS_I-1, WS_RATE_INFO);
                } else {
                    WS_RATE_INFO = WS_RATE_INFO.get(WS_I-1);
                    WS_RATE_INFO.WS_DT = WS_CINTI_DT;
                    WS_RATE_INFO.set(WS_I-1, WS_RATE_INFO);
                    WS_RATE_INFO = BPCCINTI.BASE_INFO.WS_RATE_INFO.get(WS_I-1);
                    DT = WS_CINTI_DT;
                    BPCCINTI.BASE_INFO.WS_RATE_INFO.set(WS_I-1, WS_RATE_INFO);
                }
                WS_RATE_INFO = WS_RATE_INFO.get(WS_I-1);
                WS_RATE_INFO.WS_TM = BPCRINTM.TM;
                WS_RATE_INFO.set(WS_I-1, WS_RATE_INFO);
                WS_RATE_INFO = WS_RATE_INFO.get(WS_I-1);
                WS_RATE_INFO.WS_REF_BR = BPCRINTM.REF_BR;
                WS_RATE_INFO.set(WS_I-1, WS_RATE_INFO);
                WS_RATE_INFO = WS_RATE_INFO.get(WS_I-1);
                WS_RATE_INFO.WS_REF_CCY = BPCRINTM.REF_CCY;
                WS_RATE_INFO.set(WS_I-1, WS_RATE_INFO);
                WS_RATE_INFO = WS_RATE_INFO.get(WS_I-1);
                WS_RATE_INFO.WS_REF_BASE_TYP = BPCRINTM.REF_BASE_TYP;
                WS_RATE_INFO.set(WS_I-1, WS_RATE_INFO);
                WS_RATE_INFO = WS_RATE_INFO.get(WS_I-1);
                WS_RATE_INFO.WS_REF_TENOR = BPCRINTM.REF_TENOR;
                WS_RATE_INFO.set(WS_I-1, WS_RATE_INFO);
                WS_RATE_INFO = WS_RATE_INFO.get(WS_I-1);
                WS_RATE_INFO.WS_FORMAT = BPCRINTM.FORMAT;
                WS_RATE_INFO.set(WS_I-1, WS_RATE_INFO);
                WS_RATE_INFO = WS_RATE_INFO.get(WS_I-1);
                WS_RATE_INFO.WS_DIFF = BPCRINTM.DIFF;
                WS_RATE_INFO.set(WS_I-1, WS_RATE_INFO);
                WS_RATE_INFO = WS_RATE_INFO.get(WS_I-1);
                WS_RATE_INFO.WS_RATE = BPCRINTM.RATE;
                WS_RATE_INFO.set(WS_I-1, WS_RATE_INFO);
                CEP.TRC(SCCGWA, BPCRINTM.DT);
                CEP.TRC(SCCGWA, BPCRINTM.REF_TENOR);
                CEP.TRC(SCCGWA, BPCRINTM.FORMAT);
                CEP.TRC(SCCGWA, BPCRINTM.DIFF);
                CEP.TRC(SCCGWA, BPCRINTM.RATE);
                WS_CCY = WS_RATE_INFO.get(WS_I-1).WS_REF_CCY;
                WS_BASE_TYP = WS_RATE_INFO.get(WS_I-1).WS_REF_BASE_TYP;
                WS_TENOR = WS_RATE_INFO.get(WS_I-1).WS_REF_TENOR;
                R000_CHECK_BPTIRPD();
                if (pgmRtn) return;
                if (WS_IRPD_FLG == 'D') {
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_REF_INT_NO_EXIST, BPCCINTI.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                if (WS_IRPD_CONTROL_FLG == 'D') {
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_REF_INT_NO_CONFI, BPCCINTI.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                WS_IRPD_DEL_FLG1 = BPRIRPD.DEL_FLG;
                IBS.init(SCCGWA, BPCRINTM);
                BPCRINTM.FUNC = 'I';
                BPCRINTM.BR = WS_RATE_INFO.get(WS_I-1).WS_REF_BR;
                BPCRINTM.CCY = WS_RATE_INFO.get(WS_I-1).WS_REF_CCY;
                BPCRINTM.BASE_TYP = WS_RATE_INFO.get(WS_I-1).WS_REF_BASE_TYP;
                BPCRINTM.TENOR = WS_RATE_INFO.get(WS_I-1).WS_REF_TENOR;
                BPCRINTM.DT = WS_RATE_INFO.get(WS_I-1).WS_DT;
                WS_RATE_BR = WS_RATE_INFO.get(WS_I-1).WS_REF_BR;
                WS_RATE_CCY = WS_RATE_INFO.get(WS_I-1).WS_REF_CCY;
                WS_RATE_BASE_TYP = WS_RATE_INFO.get(WS_I-1).WS_REF_BASE_TYP;
                WS_RATE_TENOR = WS_RATE_INFO.get(WS_I-1).WS_REF_TENOR;
                WS_RATE_DT = WS_RATE_INFO.get(WS_I-1).WS_DT;
                R000_CALL_BPZRINTM_REF();
                if (pgmRtn) return;
                if (WS_IRPD_DEL_FLG1 == 'Y' 
                    && BPCRINTM.DT != SCCGWA.COMM_AREA.AC_DATE) {
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_SEL_INT_NO_CONFI, BPCCINTI.RC);
                }
                CEP.TRC(SCCGWA, BPCRINTM.REF_BR);
                CEP.TRC(SCCGWA, BPCRINTM.REF_CCY);
                CEP.TRC(SCCGWA, BPCRINTM.REF_BASE_TYP);
                CEP.TRC(SCCGWA, BPCRINTM.REF_TENOR);
                if (BPCRINTM.REF_BR == 0 
                    && BPCRINTM.REF_CCY.trim().length() == 0 
                    && BPCRINTM.REF_BASE_TYP.trim().length() == 0 
                    && BPCRINTM.REF_TENOR.trim().length() == 0) {
                    WS_REF_RECORD_FLG = 'N';
                    if (WS_RATE_REF_FLG == 'N') {
                        CEP.TRC(SCCGWA, BPCRHITL.RATE);
                        WS_LAST_RATE = BPCRHITL.RATE;
                    } else {
                        WS_LAST_RATE = BPCRINTM.RATE;
                    }
                    WS_DEGREE = WS_I;
                }
            }
            CEP.TRC(SCCGWA, BPCRINTM.REF_BR);
            CEP.TRC(SCCGWA, "EXEC HERE1");
            CEP.TRC(SCCGWA, BPCRINTM.REF_CCY);
            CEP.TRC(SCCGWA, BPCRINTM.REF_BASE_TYP);
            CEP.TRC(SCCGWA, BPCRINTM.REF_TENOR);
            if (BPCRINTM.REF_BR != 0 
                && BPCRINTM.REF_CCY.trim().length() > 0 
                && BPCRINTM.REF_BASE_TYP.trim().length() > 0 
                && BPCRINTM.REF_TENOR.trim().length() > 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_REF_DEGREE_OVER, BPCCINTI.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            WS_NEW_RATE = WS_LAST_RATE;
            CEP.TRC(SCCGWA, WS_DEGREE);
            BPCCINTI.EXT_INFO.REF_CNT = WS_DEGREE;
            CEP.TRC(SCCGWA, BPCCINTI.EXT_INFO.REF_CNT);
            CEP.TRC(SCCGWA, "ZIFU");
            CEP.TRC(SCCGWA, WS_LAST_RATE);
            CEP.TRC(SCCGWA, WS_I);
            for (WS_I = BPCCINTI.EXT_INFO.REF_CNT; WS_I >= 1; WS_I += -1) {
                R000_COMPUTE_RATE();
                if (pgmRtn) return;
            }
            for (WS_I = 1; WS_I <= WS_DEGREE 
                && WS_I <= 10; WS_I += 1) {
                BPCCINTI.EXT_INFO.REF_TBL[WS_I-1].REF_BR = WS_RATE_INFO.get(WS_I-1).WS_REF_BR;
                BPCCINTI.EXT_INFO.REF_TBL[WS_I-1].REF_CCY = WS_RATE_INFO.get(WS_I-1).WS_REF_CCY;
                BPCCINTI.EXT_INFO.REF_TBL[WS_I-1].REF_BASE_TYP = WS_RATE_INFO.get(WS_I-1).WS_REF_BASE_TYP;
                BPCCINTI.EXT_INFO.REF_TBL[WS_I-1].REF_TENOR = WS_RATE_INFO.get(WS_I-1).WS_REF_TENOR;
                BPCCINTI.EXT_INFO.REF_TBL[WS_I-1].REF_FORMAT = WS_RATE_INFO.get(WS_I-1).WS_FORMAT;
                BPCCINTI.EXT_INFO.REF_TBL[WS_I-1].REF_DIFF = WS_RATE_INFO.get(WS_I-1).WS_DIFF;
                BPCCINTI.EXT_INFO.REF_TBL[WS_I-1].REF_RATE = WS_RATE_INFO.get(WS_I-1).WS_RATE;
                CEP.TRC(SCCGWA, WS_I);
                CEP.TRC(SCCGWA, BPCCINTI.EXT_INFO.REF_TBL[WS_I-1].REF_BR);
                CEP.TRC(SCCGWA, BPCCINTI.EXT_INFO.REF_TBL[WS_I-1].REF_CCY);
                CEP.TRC(SCCGWA, BPCCINTI.EXT_INFO.REF_TBL[WS_I-1].REF_BASE_TYP);
                CEP.TRC(SCCGWA, BPCCINTI.EXT_INFO.REF_TBL[WS_I-1].REF_TENOR);
                CEP.TRC(SCCGWA, BPCCINTI.EXT_INFO.REF_TBL[WS_I-1].REF_DIFF);
                CEP.TRC(SCCGWA, BPCCINTI.EXT_INFO.REF_TBL[WS_I-1].REF_RATE);
                CEP.TRC(SCCGWA, BPCCINTI.EXT_INFO.REF_TBL[WS_I-1].REF_FORMAT);
            }
            BPCCINTI.BASE_INFO.OWN_RATE = WS_NEW_RATE;
            BPCCINTI.BASE_INFO.RATE = WS_NEW_RATE;
            CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.OWN_RATE);
            CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.RATE);
        } else {
            BPCCINTI.BASE_INFO.OWN_RATE = BPCRINTM.RATE;
            BPCCINTI.BASE_INFO.RATE = BPCRINTM.RATE;
        }
        CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.OWN_RATE);
        CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.RATE);
    }
    public void B222_GET_HISTORY_BASE_RATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRHITL);
        WS_LAST_RATE = 0;
        BPCRHITL.DT = BPCCINTI.BASE_INFO.DT;
        BPCRHITL.BR = BPCCINTI.BASE_INFO.BR;
        BPCRHITL.CCY = BPCCINTI.BASE_INFO.CCY;
        BPCRHITL.BASE_TYP = BPCCINTI.BASE_INFO.BASE_TYP;
        BPCRHITL.TENOR = BPCCINTI.BASE_INFO.TENOR;
        CEP.TRC(SCCGWA, BPCRHITL.DT);
        CEP.TRC(SCCGWA, BPCRHITL.BR);
        CEP.TRC(SCCGWA, BPCRHITL.CCY);
        CEP.TRC(SCCGWA, BPCRHITL.BASE_TYP);
        CEP.TRC(SCCGWA, BPCRHITL.TENOR);
        R000_CALL_BPZRHITL();
        if (pgmRtn) return;
        BPCCINTI.EXT_INFO.REF_DEPTH = BPCRHITL.REF_DEPTH;
        WS_LAST_DATE = BPCRHITL.DT;
        CEP.TRC(SCCGWA, BPCRHITL.REF_DEPTH);
        CEP.TRC(SCCGWA, BPCRHITL.DT);
        CEP.TRC(SCCGWA, BPCRHITL.HIS_DT);
        BPCCINTI.BASE_INFO.N_DT = 99999999;
        R000_GET_NEXT_DATE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCRHITL.REF_BR);
        CEP.TRC(SCCGWA, BPCRHITL.REF_CCY);
        CEP.TRC(SCCGWA, BPCRHITL.REF_BASE_TYP);
        CEP.TRC(SCCGWA, BPCRHITL.REF_TENOR);
        if (BPCRHITL.REF_BR != 0 
            && BPCRHITL.REF_CCY.trim().length() > 0 
            && BPCRHITL.REF_BASE_TYP.trim().length() > 0 
            && BPCRHITL.REF_TENOR.trim().length() > 0) {
            CEP.TRC(SCCGWA, "CYCLE REF");
            WS_REF_RECORD_FLG = 'Y';
            for (WS_I = 1; WS_I <= WS_MOST_REF_DEGREE 
                && WS_REF_RECORD_FLG != 'N'; WS_I += 1) {
                WS_RATE_INFO = WS_RATE_INFO.get(WS_I-1);
                WS_RATE_INFO.WS_DT = BPCRHITL.DT;
                WS_RATE_INFO.set(WS_I-1, WS_RATE_INFO);
                WS_RATE_INFO = WS_RATE_INFO.get(WS_I-1);
                WS_RATE_INFO.WS_TM = BPCRHITL.TM;
                WS_RATE_INFO.set(WS_I-1, WS_RATE_INFO);
                WS_RATE_INFO = WS_RATE_INFO.get(WS_I-1);
                WS_RATE_INFO.WS_REF_BR = BPCRHITL.REF_BR;
                WS_RATE_INFO.set(WS_I-1, WS_RATE_INFO);
                WS_RATE_INFO = WS_RATE_INFO.get(WS_I-1);
                WS_RATE_INFO.WS_REF_CCY = BPCRHITL.REF_CCY;
                WS_RATE_INFO.set(WS_I-1, WS_RATE_INFO);
                WS_RATE_INFO = WS_RATE_INFO.get(WS_I-1);
                WS_RATE_INFO.WS_REF_BASE_TYP = BPCRHITL.REF_BASE_TYP;
                WS_RATE_INFO.set(WS_I-1, WS_RATE_INFO);
                WS_RATE_INFO = WS_RATE_INFO.get(WS_I-1);
                WS_RATE_INFO.WS_REF_TENOR = BPCRHITL.REF_TENOR;
                WS_RATE_INFO.set(WS_I-1, WS_RATE_INFO);
                WS_RATE_INFO = WS_RATE_INFO.get(WS_I-1);
                WS_RATE_INFO.WS_FORMAT = BPCRHITL.FORMAT;
                WS_RATE_INFO.set(WS_I-1, WS_RATE_INFO);
                WS_RATE_INFO = WS_RATE_INFO.get(WS_I-1);
                WS_RATE_INFO.WS_DIFF = BPCRHITL.DIFF;
                WS_RATE_INFO.set(WS_I-1, WS_RATE_INFO);
                WS_RATE_INFO = WS_RATE_INFO.get(WS_I-1);
                WS_RATE_INFO.WS_RATE = BPCRHITL.RATE;
                WS_RATE_INFO.set(WS_I-1, WS_RATE_INFO);
                CEP.TRC(SCCGWA, WS_RATE_INFO.get(WS_I-1).WS_DT);
                CEP.TRC(SCCGWA, WS_RATE_INFO.get(WS_I-1).WS_TM);
                CEP.TRC(SCCGWA, WS_RATE_INFO.get(WS_I-1).WS_FORMAT);
                CEP.TRC(SCCGWA, WS_RATE_INFO.get(WS_I-1).WS_DIFF);
                CEP.TRC(SCCGWA, WS_RATE_INFO.get(WS_I-1).WS_RATE);
                WS_CCY = WS_RATE_INFO.get(WS_I-1).WS_REF_CCY;
                WS_BASE_TYP = WS_RATE_INFO.get(WS_I-1).WS_REF_BASE_TYP;
                WS_TENOR = WS_RATE_INFO.get(WS_I-1).WS_REF_TENOR;
                R000_CHECK_BPTIRPD();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, WS_I);
                CEP.TRC(SCCGWA, "R000-CHECK-BPTIRPD");
                CEP.TRC(SCCGWA, BPRIRPD.DEL_FLG);
                WS_IRPD_DEL_FLG1 = BPRIRPD.DEL_FLG;
                if (BPCRHITL.REF_BR == 0 
                    && BPCRHITL.REF_CCY.trim().length() == 0 
                    && BPCRHITL.REF_BASE_TYP.trim().length() == 0 
                    && BPCRHITL.REF_TENOR.trim().length() == 0) {
                    WS_REF_RECORD_FLG = 'N';
                    WS_LAST_RATE = BPCRHITL.RATE;
                    CEP.TRC(SCCGWA, WS_LAST_RATE);
                    WS_DEGREE = WS_I;
                } else {
                    IBS.init(SCCGWA, BPCRHITL);
                    BPCRHITL.DT = WS_RATE_INFO.get(WS_I-1).WS_DT;
                    BPCRHITL.BR = WS_RATE_INFO.get(WS_I-1).WS_REF_BR;
                    BPCRHITL.CCY = WS_RATE_INFO.get(WS_I-1).WS_REF_CCY;
                    BPCRHITL.BASE_TYP = WS_RATE_INFO.get(WS_I-1).WS_REF_BASE_TYP;
                    BPCRHITL.TENOR = WS_RATE_INFO.get(WS_I-1).WS_REF_TENOR;
                    CEP.TRC(SCCGWA, BPCRHITL.DT);
                    CEP.TRC(SCCGWA, BPCRHITL.BR);
                    CEP.TRC(SCCGWA, BPCRHITL.CCY);
                    CEP.TRC(SCCGWA, BPCRHITL.BASE_TYP);
                    CEP.TRC(SCCGWA, BPCRHITL.TENOR);
                    R000_CALL_BPZRHITL();
                    if (pgmRtn) return;
                    R000_GET_NEXT_DATE();
                    if (pgmRtn) return;
                    if (WS_IRPD_FLG == 'N') {
                        if (BPRIRPD.DEL_FLG == 'Y' 
                            && BPCRHITL.HIS_DT != WS_RATE_INFO.get(WS_I-1).WS_DT) {
                            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_REF_INT_NO_CONFI, BPCCINTI.RC);
                            Z_RET();
                            if (pgmRtn) return;
                        }
                    }
                }
            }
            if (BPCRHITL.REF_BR != 0 
                && BPCRHITL.REF_CCY.trim().length() > 0 
                && BPCRHITL.REF_BASE_TYP.trim().length() > 0 
                && BPCRHITL.REF_TENOR.trim().length() > 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_REF_DEGREE_OVER, BPCCINTI.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            WS_NEW_RATE = WS_LAST_RATE;
            BPCCINTI.EXT_INFO.REF_CNT = WS_DEGREE;
            CEP.TRC(SCCGWA, WS_NEW_RATE);
            CEP.TRC(SCCGWA, BPCCINTI.EXT_INFO.REF_CNT);
            for (WS_I = BPCCINTI.EXT_INFO.REF_CNT; WS_I >= 1; WS_I += -1) {
                R000_COMPUTE_RATE();
                if (pgmRtn) return;
            }
            for (WS_I = 1; WS_I <= WS_DEGREE 
                && WS_I <= 10; WS_I += 1) {
                BPCCINTI.EXT_INFO.REF_TBL[WS_I-1].REF_BR = WS_RATE_INFO.get(WS_I-1).WS_REF_BR;
                BPCCINTI.EXT_INFO.REF_TBL[WS_I-1].REF_CCY = WS_RATE_INFO.get(WS_I-1).WS_REF_CCY;
                BPCCINTI.EXT_INFO.REF_TBL[WS_I-1].REF_BASE_TYP = WS_RATE_INFO.get(WS_I-1).WS_REF_BASE_TYP;
                BPCCINTI.EXT_INFO.REF_TBL[WS_I-1].REF_TENOR = WS_RATE_INFO.get(WS_I-1).WS_REF_TENOR;
                BPCCINTI.EXT_INFO.REF_TBL[WS_I-1].REF_FORMAT = WS_RATE_INFO.get(WS_I-1).WS_FORMAT;
                BPCCINTI.EXT_INFO.REF_TBL[WS_I-1].REF_DIFF = WS_RATE_INFO.get(WS_I-1).WS_DIFF;
                BPCCINTI.EXT_INFO.REF_TBL[WS_I-1].REF_RATE = WS_RATE_INFO.get(WS_I-1).WS_RATE;
            }
            BPCCINTI.BASE_INFO.RATE = WS_NEW_RATE;
            BPCCINTI.BASE_INFO.OWN_RATE = WS_NEW_RATE;
        } else {
            BPCCINTI.BASE_INFO.RATE = BPCRHITL.RATE;
            BPCCINTI.BASE_INFO.OWN_RATE = BPCRHITL.RATE;
        }
    }
    public void B223_GET_FORWARD_RATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRRTFW);
        IBS.init(SCCGWA, BPCRRTFW);
        CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.BR);
        CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.BASE_TYP);
        CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.TENOR);
        CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.CCY);
        CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.DT);
        BPRRTFW.KEY.BR = BPCCINTI.BASE_INFO.BR;
        BPRRTFW.KEY.BASE_TYP = BPCCINTI.BASE_INFO.BASE_TYP;
        BPRRTFW.KEY.TENOR = BPCCINTI.BASE_INFO.TENOR;
        BPRRTFW.KEY.CCY = BPCCINTI.BASE_INFO.CCY;
        BPRRTFW.KEY.VAL_DT = BPCCINTI.BASE_INFO.DT;
        BPCRRTFW.INFO.FUNC = 'B';
        BPCRRTFW.INFO.OPT = '6';
        BPCRRTFW.INFO.POINTER = BPRRTFW;
        BPCRRTFW.INFO.LEN = 128;
        S000_CALL_BPZRRTFW();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCRRTFW.RC);
        BPCCINTI.BASE_INFO.OWN_RATE = BPRRTFW.RATE;
        BPCCINTI.BASE_INFO.RATE = BPRRTFW.RATE;
    }
    public void S000_CALL_BPZRRTFW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-RTFW-MAINT", BPCRRTFW);
        if (BPCRRTFW.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRRTFW.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCCINTI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_BRANCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = WS_BR;
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCCINTI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_CCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = WS_CCY;
        IBS.CALLCPN(SCCGWA, CPN_INQUIRE_CCY, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCCINTI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCQCCY.DATA.CALR_STD.trim().length() == 0) WS_INTDAY = 0;
        else WS_INTDAY = Integer.parseInt(BPCQCCY.DATA.CALR_STD);
    }
    public void R000_CHECK_BASE_TYP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = K_RBASE;
        BPCOQPCD.INPUT_DATA.CODE = WS_BASE_TYP;
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_PC, BPCOQPCD);
        WS_BDESC = BPCOQPCD.OUTPUT_DATA.CODE_INFO.NAME;
        if (BPCOQPCD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCCINTI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_BASETYP_STS = BPCOQPCD.OUTPUT_DATA.CODE_INFO.RBASE_TYP;
    }
    public void R000_CHECK_TENOR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = K_RTENO;
        BPCOQPCD.INPUT_DATA.CODE = WS_TENOR;
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_PC, BPCOQPCD);
        WS_TDESC = BPCOQPCD.OUTPUT_DATA.CODE_INFO.NAME;
        if (BPCOQPCD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCCINTI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_CALL_BPZRINTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_INTR_MAINT, BPCRINTM);
        CEP.TRC(SCCGWA, BPCRINTM);
        if (BPCRINTM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRINTM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCCINTI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCRINTM.RETURN_INFO == 'N') {
            CEP.TRC(SCCGWA, "04");
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_VALUE_NOT_INPUT, BPCCINTI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_CALL_BPZRINTM_REF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_INTR_MAINT, BPCRINTM);
        if (BPCRINTM.RC.RC_CODE != 0) {
            WS_RATE_REF_FLG = 'N';
            R000_GET_HIS_RATE();
            if (pgmRtn) return;
        }
        if (BPCRINTM.RETURN_INFO == 'N') {
            CEP.TRC(SCCGWA, "REF");
            WS_RATE_REF_FLG = 'N';
            R000_GET_HIS_RATE();
            if (pgmRtn) return;
        }
    }
    public void R000_01_CALL_BPZRINTM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCRINTM);
        IBS.CALLCPN(SCCGWA, CPN_R_INTR_MAINT, BPCRINTM);
        if (BPCRINTM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRINTM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCCINTI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCRINTM.RETURN_INFO == 'N') {
            WS_CHECK_RATE_FLG = 'D';
        } else {
            WS_CHECK_RATE_FLG = 'N';
        }
        CEP.TRC(SCCGWA, WS_CHECK_RATE_FLG);
    }
    public void R000_GET_UPPER_BRANCE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0 
            && BPCPQORG.RC.RC_CODE != BPCMSG_ERROR_MSG.BP_ORG_NOTFND) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCCINTI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCPQORG);
    }
    public void R000_CALL_BPZRHITL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_IHIT_BRW_LAST, BPCRHITL);
        CEP.TRC(SCCGWA, BPCRHITL);
        if (BPCRHITL.RC.RC_CODE != 0) {
            WS_CHECK_HITRATE_FLG = 'N';
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRHITL.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_INT_RECORD_NOT_EXIST)) {
                CEP.TRC(SCCGWA, "05");
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_VALUE_NOT_INPUT, BPCCINTI.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRHITL.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCCINTI.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, BPCCINTI.RC);
        CEP.TRC(SCCGWA, "NJHNJH");
    }
    public void R000_CALL_BPZRHITN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_IHIT_BRW_NEXT, BPCRHITN);
        CEP.TRC(SCCGWA, BPCRHITN);
        if (BPCRHITN.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, BPCRHITN.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRHITN.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_INT_RECORD_NOT_EXIST)) {
                BPCRHITN.DT = SCCGWA.COMM_AREA.AC_DATE;
                CEP.TRC(SCCGWA, BPCRHITN.DT);
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
            } else {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRHITN.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCCINTI.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_AUTHORIZE_PROC() throws IOException,SQLException,Exception {
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
    public void R000_GET_HIS_RATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRHITL);
        BPCRHITL.DT = WS_RATE_DT;
        BPCRHITL.BR = WS_RATE_BR;
        BPCRHITL.CCY = WS_RATE_CCY;
        BPCRHITL.BASE_TYP = WS_RATE_BASE_TYP;
        BPCRHITL.TENOR = WS_RATE_TENOR;
        CEP.TRC(SCCGWA, BPCRHITL.DT);
        CEP.TRC(SCCGWA, BPCRHITL.BR);
        CEP.TRC(SCCGWA, BPCRHITL.CCY);
        CEP.TRC(SCCGWA, BPCRHITL.BASE_TYP);
        CEP.TRC(SCCGWA, BPCRHITL.TENOR);
        R000_CALL_BPZRHITL();
        if (pgmRtn) return;
    }
    public void R000_COMPUTE_RATE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_RATE_INFO.get(WS_I-1).WS_FORMAT);
        CEP.TRC(SCCGWA, WS_NEW_RATE);
        CEP.TRC(SCCGWA, "CHECKHERE");
        if (WS_RATE_INFO.get(WS_I-1).WS_FORMAT == 'D') {
            WS_NEW_RATE = WS_NEW_RATE + WS_RATE_INFO.get(WS_I-1).WS_DIFF;
        } else {
            WS_NEW_RATE = WS_NEW_RATE * ( 1 + WS_RATE_INFO.get(WS_I-1).WS_DIFF / 100 );
        }
        CEP.TRC(SCCGWA, WS_NEW_RATE);
    }
    public void R000_GET_NEXT_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRHITN);
        BPCRHITN.BR = BPCRHITL.BR;
        BPCRHITN.CCY = BPCRHITL.CCY;
        BPCRHITN.BASE_TYP = BPCRHITL.BASE_TYP;
        BPCRHITN.TENOR = BPCRHITL.TENOR;
        BPCRHITN.DT = BPCRHITL.HIS_DT;
        R000_CALL_BPZRHITN();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_LAST_DATE);
        if (WS_LAST_DATE != 0) {
            CEP.TRC(SCCGWA, BPCRHITN.DT);
            CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.N_DT);
            if (BPCRHITN.DT < BPCCINTI.BASE_INFO.N_DT) {
                BPCCINTI.BASE_INFO.N_DT = BPCRHITN.DT;
            }
        } else {
            if ((BPRIRPD.DEL_FLG == 'N' 
                || BPCRHITN.DT == SCCGWA.COMM_AREA.AC_DATE) 
                && SCCGWA.COMM_AREA.AC_DATE < BPCCINTI.BASE_INFO.N_DT) {
                BPCCINTI.BASE_INFO.N_DT = SCCGWA.COMM_AREA.AC_DATE;
            }
        }
        CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.N_DT);
    }
    public void R000_CHECK_BPTIRPD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIRPD);
        BPRIRPD.KEY.CCY = WS_CCY;
        BPRIRPD.KEY.BASE_TYP = WS_BASE_TYP;
        BPRIRPD.KEY.TENOR = WS_TENOR;
        IBS.init(SCCGWA, BPCRIPDM);
        BPCRIPDM.INFO.FUNC = 'Q';
        BPCRIPDM.INFO.OPT_2 = 'O';
        S000_CALL_BPZRIPDM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCRIPDM.RC);
        CEP.TRC(SCCGWA, BPCRIPDM.RETURN_INFO);
        WS_IRPD_FLG = 'N';
        if (BPCRIPDM.RETURN_INFO == 'N') {
            WS_IRPD_FLG = 'D';
        }
        WS_IRPD_CONTROL_FLG = 'N';
        if (BPRIRPD.CONTRL == 'N') {
            WS_IRPD_CONTROL_FLG = 'D';
        }
        CEP.TRC(SCCGWA, "END R000-CHECK-BPTIRPD");
    }
    public void R000_CHECK_BPZPARM() throws IOException,SQLException,Exception {
        WS_MOST_REF_DEGREE = 5;
        CEP.TRC(SCCGWA, WS_MOST_REF_DEGREE);
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        BPCPRMM.DAT_PTR = BPRPRMT;
        IBS.CALLCPN(SCCGWA, K_CMP_MAIN_PARM, BPCPRMM);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_READ_BPTIDEV() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRDEVM);
        BPCRDEVM.INFO.FUNC = 'I';
        S000_CALL_BPZRDEVM();
        if (pgmRtn) return;
        if (BPCRDEVM.RETURN_INFO == 'N') {
            BPRIDEV.KEY.BR = SCCGWA.COMM_AREA.HQT_BANK;
            IBS.init(SCCGWA, BPCRDEVM);
            BPCRDEVM.INFO.FUNC = 'I';
            S000_CALL_BPZRDEVM();
            if (pgmRtn) return;
            if (BPCRDEVM.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_NO_EXIST_DEV_TAB;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_BPZRDEVM() throws IOException,SQLException,Exception {
        BPCRDEVM.INFO.POINTER = BPRIDEV;
        IBS.CALLCPN(SCCGWA, CPN_R_IDEV_MAINT, BPCRDEVM);
        if (BPCRDEVM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRDEVM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCCINTI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRIPDM() throws IOException,SQLException,Exception {
        BPCRIPDM.INFO.POINTER = BPRIRPD;
        IBS.CALLCPN(SCCGWA, CPN_R_BK_MT, BPCRIPDM);
        CEP.TRC(SCCGWA, BPCRIPDM.RC);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCCINTI.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCCINTI = ");
            CEP.TRC(SCCGWA, BPCCINTI);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
