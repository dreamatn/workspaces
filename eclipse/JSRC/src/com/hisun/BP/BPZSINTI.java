package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSINTI {
    String JIBS_tmp_str[] = new String[10];
    String CPN_R_INTR_MAINT = "BP-R-INTR-MAINT   ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG      ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_P_INQ_PC = "BP-P-INQ-PC       ";
    String CPN_R_IRPD_MAINT = "BP-R-IRPD-MAINT   ";
    String CPN_R_IRAT_MAINT = "BP-R-IRAT-MAINT   ";
    String CPN_R_IDEV_MAINT = "BP-R-IDEV-MAINT   ";
    String K_RBASE = "RBASE";
    String K_RTENO = "RTENO";
    String K_OUTPUT_FMT = "BPX01";
    String WS_ERR_MSG = " ";
    short WS_I = 0;
    short WS_RECORD_NUMBER = 0;
    int WS_BR = 0;
    String WS_CCY = " ";
    String WS_BASE_TYP = " ";
    String WS_TENOR = " ";
    char WS_TBL_BANK_FLAG = ' ';
    char WS_COMPLETE_FLG = ' ';
    char WS_RECORD_BR_FLG = ' ';
    char WS_RECORD_DEV_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRIRPD BPRIRPD = new BPRIRPD();
    BPCRIPDM BPCRIPDM = new BPCRIPDM();
    BPCRINTM BPCRINTM = new BPCRINTM();
    BPCOINTI BPCOINTI = new BPCOINTI();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPRIRAT BPRIRAT = new BPRIRAT();
    BPCRIATM BPCRIATM = new BPCRIATM();
    BPRIDEV BPRIDEV = new BPRIDEV();
    BPCRDEVM BPCRDEVM = new BPCRDEVM();
    SCCGWA SCCGWA;
    BPCSINTI BPCSINTI;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSINTI BPCSINTI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSINTI = BPCSINTI;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSINTI return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCOINTI);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_QUERY_PROCESS();
        B300_TRANS_DATA_OUTPUT();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCSINTI.BR == ' ' 
            || BPCSINTI.BR == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_BR_NO_VALID;
            S000_ERR_MSG_PROC();
        } else {
            WS_BR = BPCSINTI.BR;
            R000_CHECK_BRANCH();
        }
        for (WS_I = 1; WS_I <= 10 
            && WS_COMPLETE_FLG != 'Y'; WS_I += 1) {
            if (BPCSINTI.INTR_TBL[WS_I-1].CCY.trim().length() == 0 
                && BPCSINTI.INTR_TBL[WS_I-1].BASE_TYP.trim().length() == 0 
                && BPCSINTI.INTR_TBL[WS_I-1].TENOR.equalsIgnoreCase("0")) {
                WS_COMPLETE_FLG = 'Y';
                WS_RECORD_NUMBER = WS_I;
            } else {
                if (BPCSINTI.INTR_TBL[WS_I-1].CCY.trim().length() == 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_CCY_NO_VALID;
                    S000_ERR_MSG_PROC();
                } else {
                    WS_CCY = BPCSINTI.INTR_TBL[WS_I-1].CCY;
                    R000_CHECK_CCY();
                }
                if (BPCSINTI.INTR_TBL[WS_I-1].BASE_TYP.trim().length() == 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_BASETYP_NO_VALID;
                    S000_ERR_MSG_PROC();
                } else {
                    WS_BASE_TYP = BPCSINTI.INTR_TBL[WS_I-1].BASE_TYP;
                    R000_CHECK_BASE_TYP();
                }
                if (BPCSINTI.INTR_TBL[WS_I-1].TENOR.equalsIgnoreCase("0")) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_TENOR_NO_VALID;
                    S000_ERR_MSG_PROC();
                } else {
                    WS_TENOR = BPCSINTI.INTR_TBL[WS_I-1].TENOR;
                    R000_CHECK_TENOR();
                }
                WS_CCY = BPCSINTI.INTR_TBL[WS_I-1].CCY;
                WS_BASE_TYP = BPCSINTI.INTR_TBL[WS_I-1].BASE_TYP;
                WS_TENOR = BPCSINTI.INTR_TBL[WS_I-1].TENOR;
                R000_CHECK_BPTIRPD();
                if (BPCSINTI.BR != SCCGWA.COMM_AREA.HQT_BANK) {
                    R000_CHECK_IDEV();
                    R000_CHECK_IRAT();
                }
            }
        }
    }
    public void B200_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOINTI);
        for (WS_I = 1; WS_I <= WS_RECORD_NUMBER; WS_I += 1) {
            IBS.init(SCCGWA, BPCRINTM);
            BPCRINTM.FUNC = 'I';
            BPCRINTM.BR = BPCSINTI.BR;
            BPCRINTM.CCY = BPCSINTI.INTR_TBL[WS_I-1].CCY;
            BPCRINTM.BASE_TYP = BPCSINTI.INTR_TBL[WS_I-1].BASE_TYP;
            BPCRINTM.TENOR = BPCSINTI.INTR_TBL[WS_I-1].TENOR;
            S000_CALL_BPZRINTM();
            B210_TRANS_DATA_OUPUT();
        }
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            BPCOINTI.O_TBL[WS_I-1].FILLER = 0X01;
            BPCOINTI.O_TBL[WS_I-1].FILLER2 = 0X01;
        }
    }
    public void B300_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOINTI;
        SCCFMT.DATA_LEN = 714;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B210_TRANS_DATA_OUPUT() throws IOException,SQLException,Exception {
        BPCOINTI.O_EFF_DT = BPCSINTI.DT;
        BPCOINTI.O_BR = BPCRINTM.BR;
        BPCOINTI.O_TBL[WS_I-1].O_CCY = BPCSINTI.INTR_TBL[WS_I-1].CCY;
        BPCOINTI.O_TBL[WS_I-1].O_BASE_TYP = BPCSINTI.INTR_TBL[WS_I-1].BASE_TYP;
        BPCOINTI.O_TBL[WS_I-1].O_TENOR = BPCSINTI.INTR_TBL[WS_I-1].TENOR;
        BPCOINTI.O_TBL[WS_I-1].O_REF_BR = BPCRINTM.REF_BR;
        BPCOINTI.O_TBL[WS_I-1].O_REF_CCY = BPCRINTM.REF_CCY;
        BPCOINTI.O_TBL[WS_I-1].O_REF_BASE_TYP = BPCRINTM.REF_BASE_TYP;
        BPCOINTI.O_TBL[WS_I-1].O_REF_TENOR = BPCRINTM.REF_TENOR;
        BPCOINTI.O_TBL[WS_I-1].O_FORMAT = BPCRINTM.FORMAT;
        if (BPCSINTI.BR != SCCGWA.COMM_AREA.HQT_BANK 
            && BPCOINTI.O_TBL[WS_I-1].O_REF_CCY.trim().length() == 0) {
            BPCOINTI.O_TBL[WS_I-1].FILLER_BR = SCCGWA.COMM_AREA.HQT_BANK;
            BPCOINTI.O_TBL[WS_I-1].FILLER_CCY = BPCOINTI.O_TBL[WS_I-1].O_CCY;
            BPCOINTI.O_TBL[WS_I-1].FILLER_BASETYP = BPCOINTI.O_TBL[WS_I-1].O_BASE_TYP;
            BPCOINTI.O_TBL[WS_I-1].FILLER_TENOR = BPCOINTI.O_TBL[WS_I-1].O_TENOR;
        }
        if (BPCRINTM.REF_CCY.trim().length() == 0) {
            BPCOINTI.O_TBL[WS_I-1].O_RATE = BPCRINTM.RATE;
        } else {
            BPCOINTI.O_TBL[WS_I-1].O_RATE = BPCRINTM.DIFF;
        }
    }
    public void R000_CHECK_BRANCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = WS_BR;
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void R000_CHECK_CCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = WS_CCY;
        IBS.CALLCPN(SCCGWA, CPN_INQUIRE_CCY, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void R000_CHECK_BASE_TYP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = K_RBASE;
        BPCOQPCD.INPUT_DATA.CODE = WS_BASE_TYP;
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_PC, BPCOQPCD);
        if (BPCOQPCD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void R000_CHECK_TENOR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = K_RTENO;
        BPCOQPCD.INPUT_DATA.CODE = WS_TENOR;
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_PC, BPCOQPCD);
        if (BPCOQPCD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            S000_ERR_MSG_PROC();
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
    public void R000_READ_BPTIRPD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIRPD);
        BPRIRPD.KEY.CCY = WS_CCY;
        BPRIRPD.KEY.BASE_TYP = WS_BASE_TYP;
        BPRIRPD.KEY.TENOR = WS_TENOR;
        IBS.init(SCCGWA, BPCRIPDM);
        BPCRIPDM.INFO.FUNC = 'Q';
        BPCRIPDM.INFO.OPT_2 = 'O';
        S000_CALL_BPZRIPDM();
    }
    public void S000_CALL_BPZRIPDM() throws IOException,SQLException,Exception {
        BPCRIPDM.INFO.POINTER = BPRIRPD;
        IBS.CALLCPN(SCCGWA, CPN_R_IRPD_MAINT, BPCRIPDM);
        if (BPCRIPDM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRIPDM.RC);
            S000_ERR_MSG_PROC();
        }
        if (BPCRIPDM.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_TENOR_COMPAGES_U;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRINTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_INTR_MAINT, BPCRINTM);
        if (BPCRINTM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRINTM.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_INT_RECORD_NOT_EXIST)) {
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRINTM.RC);
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void R000_CHECK_IDEV() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIDEV);
        BPRIDEV.KEY.TYPE = 'M';
        BPRIDEV.KEY.BR = BPCSINTI.BR;
        BPRIDEV.KEY.CCY = BPCSINTI.INTR_TBL[WS_I-1].CCY;
        BPRIDEV.KEY.BASE_TYP = BPCSINTI.INTR_TBL[WS_I-1].BASE_TYP;
        BPRIDEV.KEY.TENOR = BPCSINTI.INTR_TBL[WS_I-1].TENOR;
        IBS.init(SCCGWA, BPCRDEVM);
        BPCRDEVM.INFO.FUNC = 'I';
        S000_CALL_BPZRDEVM();
        if (BPCRDEVM.RETURN_INFO == 'N') {
            BPRIDEV.KEY.BR = SCCGWA.COMM_AREA.HQT_BANK;
            IBS.init(SCCGWA, BPCRDEVM);
            BPCRDEVM.INFO.FUNC = 'I';
            S000_CALL_BPZRDEVM();
            if (BPCRDEVM.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_NO_EXIST_DEV_TAB;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void R000_CHECK_IRAT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIRAT);
        BPRIRAT.KEY.BR = BPCSINTI.BR;
        BPRIRAT.KEY.CCY = BPCSINTI.INTR_TBL[WS_I-1].CCY;
        BPRIRAT.KEY.BASE_TYP = BPCSINTI.INTR_TBL[WS_I-1].BASE_TYP;
        BPRIRAT.KEY.TENOR = BPCSINTI.INTR_TBL[WS_I-1].TENOR;
        IBS.init(SCCGWA, BPCRIATM);
        BPCRIATM.INFO.FUNC = 'Q';
        BPCRIATM.INFO.OPT_2 = 'O';
        S000_CALL_BPZRIATM();
        if (BPCRIATM.RETURN_INFO == 'N') {
            BPRIRAT.KEY.BR = SCCGWA.COMM_AREA.HQT_BANK;
            IBS.init(SCCGWA, BPCRIATM);
            BPCRIATM.INFO.FUNC = 'Q';
            BPCRIATM.INFO.OPT_2 = 'O';
            S000_CALL_BPZRIATM();
            if (BPCRIATM.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_NO_EXIST_BR_SCOP;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void S000_CALL_BPZRIATM() throws IOException,SQLException,Exception {
        BPCRIATM.INFO.POINTER = BPRIRAT;
        IBS.CALLCPN(SCCGWA, CPN_R_IRAT_MAINT, BPCRIATM);
        if (BPCRIATM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRIATM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRDEVM() throws IOException,SQLException,Exception {
        BPCRDEVM.INFO.POINTER = BPRIDEV;
        IBS.CALLCPN(SCCGWA, CPN_R_IDEV_MAINT, BPCRDEVM);
        WS_RECORD_DEV_FLG = 'Y';
        if (BPCRDEVM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRDEVM.RC);
            S000_ERR_MSG_PROC();
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
