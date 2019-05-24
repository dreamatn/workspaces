package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5202 {
    String CPN_S_INTR_UPD = "BP-S-INTR-UPD     ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG      ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_P_INQ_PC = "BP-P-INQ-PC       ";
    String K_RBASE = "RBASE";
    String K_RTENO = "RTENO";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_I = 0;
    short WS_SPACE_NO = 0;
    int WS_BR = 0;
    String WS_CCY = " ";
    String WS_BASE_TYP = " ";
    String WS_TENOR = " ";
    char WS_COMPLETE_FLG = ' ';
    char WS_REF_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSINTU BPCSINTU = new BPCSINTU();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    SCCGWA SCCGWA;
    BPB5201_AWA_5201 BPB5201_AWA_5201;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT5202 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5201_AWA_5201>");
        BPB5201_AWA_5201 = (BPB5201_AWA_5201) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_MAIN_PROCESS();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB5201_AWA_5201.DT != SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_DATE_NO_VALID;
            WS_FLD_NO = BPB5201_AWA_5201.DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB5201_AWA_5201.BR == 0 
            || BPB5201_AWA_5201.BR == ' ') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_BR_NO_VALID;
            WS_FLD_NO = BPB5201_AWA_5201.BR_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        } else {
            WS_REF_FLG = 'Y';
            WS_BR = BPB5201_AWA_5201.BR;
            R000_CHECK_BRANCH();
        }
        WS_COMPLETE_FLG = 'N';
        for (WS_I = 1; WS_I <= 10 
            && WS_COMPLETE_FLG != 'Y'; WS_I += 1) {
            if (BPB5201_AWA_5201.CCY_A[WS_I-1].CCY.trim().length() == 0 
                && BPB5201_AWA_5201.BASE_A[WS_I-1].BASE_TYP.trim().length() == 0 
                && (BPB5201_AWA_5201.TENOR_A[WS_I-1].TENOR.equalsIgnoreCase("0") 
                || BPB5201_AWA_5201.TENOR_A[WS_I-1].TENOR.trim().length() == 0)) {
                WS_COMPLETE_FLG = 'Y';
            } else {
                if (BPB5201_AWA_5201.BR != SCCGWA.COMM_AREA.HQT_BANK) {
                    if (BPB5201_AWA_5201.R_BR_A[WS_I-1].R_BR == 0 
                        || BPB5201_AWA_5201.R_BR_A[WS_I-1].R_BR == ' ') {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_REF_BR_NO_VALID;
                        WS_FLD_NO = BPB5201_AWA_5201.R_BR_A[WS_I-1].R_BR_NO;
                        S000_ERR_MSG_PROC_CONTINUE();
                    } else {
                        WS_REF_FLG = 'N';
                        WS_BR = BPB5201_AWA_5201.R_BR_A[WS_I-1].R_BR;
                        R000_CHECK_BRANCH();
                    }
                    if (BPB5201_AWA_5201.R_CCY_A[WS_I-1].R_CCY.trim().length() == 0) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_REF_CCY_NO_VALID;
                        WS_FLD_NO = BPB5201_AWA_5201.R_CCY_A[WS_I-1].R_CCY_NO;
                        S000_ERR_MSG_PROC_CONTINUE();
                    } else {
                        WS_CCY = BPB5201_AWA_5201.R_CCY_A[WS_I-1].R_CCY;
                        R000_CHECK_CCY();
                    }
                    if (BPB5201_AWA_5201.R_BASE_A[WS_I-1].R_BASE_T.trim().length() == 0) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_REF_BASETYP_NO_V;
                        WS_FLD_NO = BPB5201_AWA_5201.R_BASE_A[WS_I-1].R_BASE_T_NO;
                        S000_ERR_MSG_PROC_CONTINUE();
                    } else {
                        WS_BASE_TYP = BPB5201_AWA_5201.R_BASE_A[WS_I-1].R_BASE_T;
                        R000_CHECK_BASE_TYP();
                    }
                    if (BPB5201_AWA_5201.R_TERM_A[WS_I-1].R_TENOR.equalsIgnoreCase("0") 
                        || BPB5201_AWA_5201.R_TERM_A[WS_I-1].R_TENOR.trim().length() == 0) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_REF_TENOR_NO_VAL;
                        WS_FLD_NO = BPB5201_AWA_5201.R_TERM_A[WS_I-1].R_TENOR_NO;
                        S000_ERR_MSG_PROC_CONTINUE();
                    } else {
                        WS_TENOR = BPB5201_AWA_5201.R_TERM_A[WS_I-1].R_TENOR;
                        R000_CHECK_TENOR();
                    }
                    if (BPB5201_AWA_5201.FORMAT_A[WS_I-1].FORMAT == ' ' 
                        || BPB5201_AWA_5201.FORMAT_A[WS_I-1].FORMAT != 'D' 
                        && BPB5201_AWA_5201.FORMAT_A[WS_I-1].FORMAT != 'P') {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_FORMAT_NO_VALID;
                        WS_FLD_NO = BPB5201_AWA_5201.FORMAT_A[WS_I-1].FORMAT_NO;
                        S000_ERR_MSG_PROC_CONTINUE();
                    }
                }
            }
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_INPUT_ERROR;
        WS_FLD_NO = 0;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSINTU);
        BPCSINTU.BR = BPB5201_AWA_5201.BR;
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            BPCSINTU.INTR_TBL[WS_I-1].CCY = BPB5201_AWA_5201.CCY_A[WS_I-1].CCY;
            BPCSINTU.INTR_TBL[WS_I-1].BASE_TYP = BPB5201_AWA_5201.BASE_A[WS_I-1].BASE_TYP;
            BPCSINTU.INTR_TBL[WS_I-1].TENOR = BPB5201_AWA_5201.TENOR_A[WS_I-1].TENOR;
            BPCSINTU.INTR_TBL[WS_I-1].REF_BR = BPB5201_AWA_5201.R_BR_A[WS_I-1].R_BR;
            BPCSINTU.INTR_TBL[WS_I-1].REF_CCY = BPB5201_AWA_5201.R_CCY_A[WS_I-1].R_CCY;
            BPCSINTU.INTR_TBL[WS_I-1].REF_BASE_TYP = BPB5201_AWA_5201.R_BASE_A[WS_I-1].R_BASE_T;
            BPCSINTU.INTR_TBL[WS_I-1].REF_TENOR = BPB5201_AWA_5201.R_TERM_A[WS_I-1].R_TENOR;
            BPCSINTU.INTR_TBL[WS_I-1].REF_FORMAT = BPB5201_AWA_5201.FORMAT_A[WS_I-1].FORMAT;
            if (BPB5201_AWA_5201.R_CCY_A[WS_I-1].R_CCY.trim().length() > 0) {
                BPCSINTU.INTR_TBL[WS_I-1].REF_DIFF = BPB5201_AWA_5201.RATE_A[WS_I-1].RATE;
                BPCSINTU.INTR_TBL[WS_I-1].INT_RATE = 0;
            } else {
                BPCSINTU.INTR_TBL[WS_I-1].REF_DIFF = 0;
                BPCSINTU.INTR_TBL[WS_I-1].INT_RATE = BPB5201_AWA_5201.RATE_A[WS_I-1].RATE;
            }
        }
        S000_CALL_BPZSINTU();
    }
    public void R000_CHECK_BRANCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = WS_BR;
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            if (WS_REF_FLG == 'Y') {
                WS_FLD_NO = BPB5201_AWA_5201.BR_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            } else {
                WS_FLD_NO = BPB5201_AWA_5201.R_BR_A[WS_I-1].R_BR_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
    }
    public void R000_CHECK_CCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = WS_CCY;
        IBS.CALLCPN(SCCGWA, CPN_INQUIRE_CCY, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            WS_FLD_NO = BPB5201_AWA_5201.R_CCY_A[WS_I-1].R_CCY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void R000_CHECK_BASE_TYP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = K_RBASE;
        BPCOQPCD.INPUT_DATA.CODE = WS_BASE_TYP;
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_PC, BPCOQPCD);
        if (BPCOQPCD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            WS_FLD_NO = BPB5201_AWA_5201.R_BASE_A[WS_I-1].R_BASE_T_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void R000_CHECK_TENOR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = K_RTENO;
        BPCOQPCD.INPUT_DATA.CODE = WS_TENOR;
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_PC, BPCOQPCD);
        if (BPCOQPCD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            WS_FLD_NO = BPB5201_AWA_5201.R_TERM_A[WS_I-1].R_TENOR_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void S000_CALL_BPZSINTU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_INTR_UPD, BPCSINTU);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
