package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSINTU {
    String CPN_U_INTR_UPD = "BP-U-INTR-UPD     ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG      ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_P_INQ_PC = "BP-P-INQ-PC       ";
    String CPN_R_BK_MT = "BP-R-IRPD-MAINT";
    String K_RBASE = "RBASE";
    String K_RTENO = "RTENO";
    String K_OUTPUT_FMT = "BP301";
    String WS_ERR_MSG = " ";
    short WS_I = 0;
    short WS_RECORD_NUMBER = 0;
    int WS_BR = 0;
    String WS_CCY = " ";
    String WS_BASE_TYP = " ";
    String WS_TENOR = " ";
    char WS_RECORD_FLG = ' ';
    char WS_COMPLETE_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCUINTU BPCUINTU = new BPCUINTU();
    BPCOINTU BPCOINTU = new BPCOINTU();
    BPCRIPDM BPCRIPDM = new BPCRIPDM();
    BPRIRPD BPRIRPD = new BPRIRPD();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    SCCGWA SCCGWA;
    BPCSINTU BPCSINTU;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSINTU BPCSINTU) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSINTU = BPCSINTU;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSINTU return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCOINTU);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_QUERY_PROCESS();
        B300_TRANS_DATA_OUTPUT();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCSINTU.BR == ' ' 
            || BPCSINTU.BR == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_BR_NO_VALID;
            S000_ERR_MSG_PROC();
        } else {
            WS_BR = BPCSINTU.BR;
            R000_CHECK_BRANCH();
        }
        for (WS_I = 1; WS_I <= 10 
            && WS_COMPLETE_FLG != 'Y'; WS_I += 1) {
            if (BPCSINTU.INTR_TBL[WS_I-1].CCY.trim().length() == 0 
                && BPCSINTU.INTR_TBL[WS_I-1].BASE_TYP.trim().length() == 0 
                && BPCSINTU.INTR_TBL[WS_I-1].TENOR.equalsIgnoreCase("0")) {
                WS_COMPLETE_FLG = 'Y';
                WS_RECORD_NUMBER = WS_I;
            } else {
                if (BPCSINTU.INTR_TBL[WS_I-1].REF_BR == 0 
                    && BPCSINTU.INTR_TBL[WS_I-1].REF_CCY.trim().length() == 0 
                    && BPCSINTU.INTR_TBL[WS_I-1].REF_BASE_TYP.trim().length() == 0 
                    && BPCSINTU.INTR_TBL[WS_I-1].REF_TENOR.equalsIgnoreCase("0") 
                    && BPCSINTU.INTR_TBL[WS_I-1].REF_FORMAT == ' ') {
                    if (BPCSINTU.INTR_TBL[WS_I-1].INT_RATE == 0) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_INPUT_ERROR;
                        S000_ERR_MSG_PROC();
                    }
                } else {
                    if (BPCSINTU.INTR_TBL[WS_I-1].REF_BR == 0) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_BR_NO_VALID;
                        S000_ERR_MSG_PROC();
                    } else {
                        WS_BR = BPCSINTU.INTR_TBL[WS_I-1].REF_BR;
                        R000_CHECK_BRANCH();
                    }
                    if (BPCSINTU.INTR_TBL[WS_I-1].REF_CCY.trim().length() == 0) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_CCY_NO_VALID;
                        S000_ERR_MSG_PROC();
                    } else {
                        WS_CCY = BPCSINTU.INTR_TBL[WS_I-1].REF_CCY;
                        R000_CHECK_CCY();
                    }
                    if (BPCSINTU.INTR_TBL[WS_I-1].REF_BASE_TYP.trim().length() == 0) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_BASETYP_NO_VALID;
                        S000_ERR_MSG_PROC();
                    } else {
                        WS_BASE_TYP = BPCSINTU.INTR_TBL[WS_I-1].REF_BASE_TYP;
                        R000_CHECK_BASE_TYP();
                    }
                    if (BPCSINTU.INTR_TBL[WS_I-1].REF_TENOR.trim().length() == 0) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_TENOR_NO_VALID;
                        S000_ERR_MSG_PROC();
                    } else {
                        WS_TENOR = BPCSINTU.INTR_TBL[WS_I-1].REF_TENOR;
                        R000_CHECK_TENOR();
                    }
                    CEP.TRC(SCCGWA, BPCSINTU.INTR_TBL[WS_I-1].REF_FORMAT);
                    if ((BPCSINTU.INTR_TBL[WS_I-1].REF_FORMAT != 'P' 
                        && BPCSINTU.INTR_TBL[WS_I-1].REF_FORMAT != 'D')) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_FORMAT_NO_VALID;
                        S000_ERR_MSG_PROC();
                    }
                }
                WS_CCY = BPCSINTU.INTR_TBL[WS_I-1].CCY;
                WS_BASE_TYP = BPCSINTU.INTR_TBL[WS_I-1].BASE_TYP;
                WS_TENOR = BPCSINTU.INTR_TBL[WS_I-1].TENOR;
                R000_CHECK_RATE_TENOR();
            }
        }
    }
    public void B200_QUERY_PROCESS() throws IOException,SQLException,Exception {
        WS_RECORD_FLG = 'Y';
        BPCOINTU.DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCOINTU.BR = BPCSINTU.BR;
        for (WS_I = 1; WS_I <= 10 
            && WS_RECORD_FLG != 'N'; WS_I += 1) {
            if (BPCSINTU.INTR_TBL[WS_I-1].CCY.trim().length() == 0) {
                WS_RECORD_FLG = 'N';
            } else {
                IBS.init(SCCGWA, BPCUINTU);
                BPCUINTU.BR = BPCSINTU.BR;
                CEP.TRC(SCCGWA, BPCSINTU.BR);
                BPCUINTU.CCY = BPCSINTU.INTR_TBL[WS_I-1].CCY;
                BPCUINTU.BASE_TYP = BPCSINTU.INTR_TBL[WS_I-1].BASE_TYP;
                CEP.TRC(SCCGWA, BPCSINTU.INTR_TBL[WS_I-1].BASE_TYP);
                BPCUINTU.TENOR = BPCSINTU.INTR_TBL[WS_I-1].TENOR;
                CEP.TRC(SCCGWA, BPCSINTU.INTR_TBL[WS_I-1].TENOR);
                BPCUINTU.REF_BR = BPCSINTU.INTR_TBL[WS_I-1].REF_BR;
                CEP.TRC(SCCGWA, BPCSINTU.INTR_TBL[WS_I-1].REF_BR);
                BPCUINTU.REF_CCY = BPCSINTU.INTR_TBL[WS_I-1].REF_CCY;
                CEP.TRC(SCCGWA, BPCSINTU.INTR_TBL[WS_I-1].REF_CCY);
                BPCUINTU.REF_BASE_TYP = BPCSINTU.INTR_TBL[WS_I-1].REF_BASE_TYP;
                BPCUINTU.REF_TENOR = BPCSINTU.INTR_TBL[WS_I-1].REF_TENOR;
                BPCUINTU.FORMAT = BPCSINTU.INTR_TBL[WS_I-1].REF_FORMAT;
                BPCUINTU.DIFF = BPCSINTU.INTR_TBL[WS_I-1].REF_DIFF;
                BPCUINTU.RATE = BPCSINTU.INTR_TBL[WS_I-1].INT_RATE;
                S000_CALL_BPZUINTU();
                B210_TRANS_DATA_OUPUT();
            }
        }
    }
    public void B300_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOINTU;
        SCCFMT.DATA_LEN = 419;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B210_TRANS_DATA_OUPUT() throws IOException,SQLException,Exception {
        BPCOINTU.INTR_TBL[WS_I-1].CCY = BPCUINTU.CCY;
        BPCOINTU.INTR_TBL[WS_I-1].BASE_TYP = BPCUINTU.BASE_TYP;
        BPCOINTU.INTR_TBL[WS_I-1].TENOR = BPCUINTU.TENOR;
        BPCOINTU.INTR_TBL[WS_I-1].REF_BR = BPCUINTU.REF_BR;
        BPCOINTU.INTR_TBL[WS_I-1].REF_CCY = BPCUINTU.REF_CCY;
        BPCOINTU.INTR_TBL[WS_I-1].REF_BASE_TYP = BPCUINTU.REF_BASE_TYP;
        BPCOINTU.INTR_TBL[WS_I-1].REF_TENOR = BPCUINTU.REF_TENOR;
        BPCOINTU.INTR_TBL[WS_I-1].REF_FORMAT = BPCUINTU.FORMAT;
        if (BPCUINTU.REF_CCY.trim().length() == 0) {
            BPCOINTU.INTR_TBL[WS_I-1].INT_RATE = BPCUINTU.RATE;
        } else {
            BPCOINTU.INTR_TBL[WS_I-1].INT_RATE = BPCUINTU.DIFF;
        }
        BPCOINTU.CNT = WS_I;
        BPCOINTU.INTR_TBL[WS_I-1].FILLER = 0X01;
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
    public void R000_CHECK_RATE_TENOR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIRPD);
        BPRIRPD.KEY.CCY = WS_CCY;
        BPRIRPD.KEY.BASE_TYP = WS_BASE_TYP;
        BPRIRPD.KEY.TENOR = WS_TENOR;
        IBS.init(SCCGWA, BPCRIPDM);
        BPCRIPDM.INFO.FUNC = 'Q';
        BPCRIPDM.INFO.OPT_2 = 'O';
        S000_CALL_BPZRIPDM();
        if (BPCRIPDM.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_TENOR_COMPAGES_U;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRIPDM() throws IOException,SQLException,Exception {
        BPCRIPDM.INFO.POINTER = BPRIRPD;
        IBS.CALLCPN(SCCGWA, CPN_R_BK_MT, BPCRIPDM);
        if (BPCRIPDM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRIPDM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZUINTU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_INTR_UPD, BPCUINTU);
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
