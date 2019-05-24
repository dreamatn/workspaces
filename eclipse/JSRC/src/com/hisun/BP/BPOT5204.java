package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5204 {
    String CPN_S_TNRS_ATIR_UPD = "BP-S-TNRS-ATIR-UPD";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG      ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_P_INQ_PC = "BP-P-INQ-PC       ";
    String K_RBASE = "RBASE";
    String K_RTENO = "RTENO";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_I = 0;
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
    BPCSTNRU BPCSTNRU = new BPCSTNRU();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    SCCGWA SCCGWA;
    BPB5203_AWA_5203 BPB5203_AWA_5203;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT5204 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5203_AWA_5203>");
        BPB5203_AWA_5203 = (BPB5203_AWA_5203) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_MAIN_PROCESS();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB5203_AWA_5203.DT != SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_DATE_NO_VALID;
            WS_FLD_NO = BPB5203_AWA_5203.DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB5203_AWA_5203.BR == ' ' 
            || BPB5203_AWA_5203.BR == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_BR_NO_VALID;
            WS_FLD_NO = BPB5203_AWA_5203.BR_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        } else {
            WS_REF_FLG = 'Y';
            WS_BR = BPB5203_AWA_5203.BR;
            R000_CHECK_BRANCH();
        }
        if (BPB5203_AWA_5203.CCY.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_CCY_NO_VALID;
            WS_FLD_NO = BPB5203_AWA_5203.CCY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        } else {
            WS_REF_FLG = 'Y';
            WS_CCY = BPB5203_AWA_5203.CCY;
            R000_CHECK_CCY();
        }
        if (BPB5203_AWA_5203.BASE_TYP.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_BASETYP_NO_VALID;
            WS_FLD_NO = BPB5203_AWA_5203.BASE_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        } else {
            WS_REF_FLG = 'Y';
            WS_BASE_TYP = BPB5203_AWA_5203.BASE_TYP;
            R000_CHECK_BASE_TYP();
        }
        WS_COMPLETE_FLG = 'N';
        for (WS_I = 1; WS_I <= 20 
            && WS_COMPLETE_FLG != 'Y'; WS_I += 1) {
            if (BPB5203_AWA_5203.TAL_A[WS_I-1].TENOR.equalsIgnoreCase("0") 
                || BPB5203_AWA_5203.TAL_A[WS_I-1].TENOR.trim().length() == 0) {
                WS_COMPLETE_FLG = 'Y';
            } else {
                if (BPB5203_AWA_5203.BR != SCCGWA.COMM_AREA.HQT_BANK) {
                    if (BPB5203_AWA_5203.TAL_A[WS_I-1].R_BR == 0 
                        || BPB5203_AWA_5203.TAL_A[WS_I-1].R_BR == ' ') {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_REF_BR_NO_VALID;
                        WS_FLD_NO = BPB5203_AWA_5203.TAL_A[WS_I-1].R_BR_NO;
                        S000_ERR_MSG_PROC_CONTINUE();
                    } else {
                        WS_REF_FLG = 'N';
                        WS_BR = BPB5203_AWA_5203.TAL_A[WS_I-1].R_BR;
                        R000_CHECK_BRANCH();
                    }
                    if (BPB5203_AWA_5203.TAL_A[WS_I-1].R_CCY.trim().length() == 0) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_REF_CCY_NO_VALID;
                        WS_FLD_NO = BPB5203_AWA_5203.TAL_A[WS_I-1].R_CCY_NO;
                        S000_ERR_MSG_PROC_CONTINUE();
                    } else {
                        WS_REF_FLG = 'N';
                        WS_CCY = BPB5203_AWA_5203.TAL_A[WS_I-1].R_CCY;
                        R000_CHECK_CCY();
                    }
                    if (BPB5203_AWA_5203.TAL_A[WS_I-1].R_BASE_T.trim().length() == 0) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_REF_BASETYP_NO_V;
                        WS_FLD_NO = BPB5203_AWA_5203.TAL_A[WS_I-1].R_BASE_T_NO;
                        S000_ERR_MSG_PROC_CONTINUE();
                    } else {
                        WS_REF_FLG = 'N';
                        WS_BASE_TYP = BPB5203_AWA_5203.TAL_A[WS_I-1].R_BASE_T;
                        R000_CHECK_BASE_TYP();
                    }
                    if (BPB5203_AWA_5203.TAL_A[WS_I-1].R_TENOR.equalsIgnoreCase("0") 
                        || BPB5203_AWA_5203.TAL_A[WS_I-1].R_TENOR.trim().length() == 0) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_REF_TENOR_NO_VAL;
                        WS_FLD_NO = BPB5203_AWA_5203.TAL_A[WS_I-1].R_TENOR_NO;
                        S000_ERR_MSG_PROC_CONTINUE();
                    } else {
                        WS_REF_FLG = 'N';
                        WS_TENOR = BPB5203_AWA_5203.TAL_A[WS_I-1].R_TENOR;
                        R000_CHECK_TENOR();
                    }
                    if (BPB5203_AWA_5203.TAL_A[WS_I-1].FORMAT == ' ' 
                        || BPB5203_AWA_5203.TAL_A[WS_I-1].FORMAT != 'D' 
                        && BPB5203_AWA_5203.TAL_A[WS_I-1].FORMAT != 'P') {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INT_FORMAT_NO_VALID;
                        WS_FLD_NO = BPB5203_AWA_5203.TAL_A[WS_I-1].FORMAT_NO;
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
        CEP.TRC(SCCGWA, "ENTER-MAIN");
        IBS.init(SCCGWA, BPCSTNRU);
        BPCSTNRU.BR = BPB5203_AWA_5203.BR;
        BPCSTNRU.CCY = BPB5203_AWA_5203.CCY;
        BPCSTNRU.BASE_TYP = BPB5203_AWA_5203.BASE_TYP;
        for (WS_I = 1; WS_I <= 20; WS_I += 1) {
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, BPB5203_AWA_5203.TAL_A[WS_I-1].TENOR);
            CEP.TRC(SCCGWA, BPB5203_AWA_5203.TAL_A[WS_I-1].R_BR);
            CEP.TRC(SCCGWA, BPB5203_AWA_5203.TAL_A[WS_I-1].R_CCY);
            CEP.TRC(SCCGWA, BPB5203_AWA_5203.TAL_A[WS_I-1].R_BASE_T);
            CEP.TRC(SCCGWA, BPB5203_AWA_5203.TAL_A[WS_I-1].R_TENOR);
            CEP.TRC(SCCGWA, BPB5203_AWA_5203.TAL_A[WS_I-1].FORMAT);
            CEP.TRC(SCCGWA, BPB5203_AWA_5203.TAL_A[WS_I-1].R_CCY);
            CEP.TRC(SCCGWA, BPB5203_AWA_5203.TAL_A[WS_I-1].RATE);
            BPCSTNRU.INTR_TBL[WS_I-1].TENOR = BPB5203_AWA_5203.TAL_A[WS_I-1].TENOR;
            BPCSTNRU.INTR_TBL[WS_I-1].REF_BR = BPB5203_AWA_5203.TAL_A[WS_I-1].R_BR;
            BPCSTNRU.INTR_TBL[WS_I-1].REF_CCY = BPB5203_AWA_5203.TAL_A[WS_I-1].R_CCY;
            BPCSTNRU.INTR_TBL[WS_I-1].REF_BASE_TYP = BPB5203_AWA_5203.TAL_A[WS_I-1].R_BASE_T;
            BPCSTNRU.INTR_TBL[WS_I-1].REF_TENOR = BPB5203_AWA_5203.TAL_A[WS_I-1].R_TENOR;
            BPCSTNRU.INTR_TBL[WS_I-1].REF_FORMAT = BPB5203_AWA_5203.TAL_A[WS_I-1].FORMAT;
            if (BPB5203_AWA_5203.TAL_A[WS_I-1].R_CCY.trim().length() > 0) {
                BPCSTNRU.INTR_TBL[WS_I-1].REF_DIFF = BPB5203_AWA_5203.TAL_A[WS_I-1].RATE;
                BPCSTNRU.INTR_TBL[WS_I-1].INT_RATE = 0;
            } else {
                BPCSTNRU.INTR_TBL[WS_I-1].REF_DIFF = 0;
                BPCSTNRU.INTR_TBL[WS_I-1].INT_RATE = BPB5203_AWA_5203.TAL_A[WS_I-1].RATE;
            }
        }
        CEP.TRC(SCCGWA, "BEFROE-CALL");
        S000_CALL_BPZSTNRU();
        CEP.TRC(SCCGWA, "AFTER-CALL");
    }
    public void R000_CHECK_BRANCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = WS_BR;
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            if (WS_REF_FLG == 'Y') {
                WS_FLD_NO = BPB5203_AWA_5203.BR_NO;
            } else {
                WS_FLD_NO = BPB5203_AWA_5203.TAL_A[WS_I-1].R_BR_NO;
            }
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void R000_CHECK_CCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = WS_CCY;
        IBS.CALLCPN(SCCGWA, CPN_INQUIRE_CCY, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            if (WS_REF_FLG == 'Y') {
                WS_FLD_NO = BPB5203_AWA_5203.CCY_NO;
            } else {
                WS_FLD_NO = BPB5203_AWA_5203.TAL_A[WS_I-1].R_CCY_NO;
            }
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
            if (WS_REF_FLG == 'Y') {
                WS_FLD_NO = BPB5203_AWA_5203.BASE_TYP_NO;
            } else {
                WS_FLD_NO = BPB5203_AWA_5203.TAL_A[WS_I-1].R_BASE_T_NO;
            }
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
            WS_FLD_NO = BPB5203_AWA_5203.TAL_A[WS_I-1].R_TENOR_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void S000_CALL_BPZSTNRU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_TNRS_ATIR_UPD, BPCSTNRU);
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
