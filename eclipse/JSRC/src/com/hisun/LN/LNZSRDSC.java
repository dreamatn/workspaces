package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.DD.*;
import com.hisun.IB.*;
import com.hisun.AI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSRDSC {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    LNZSRDSC_WS_DD_NARRATIVE WS_DD_NARRATIVE = new LNZSRDSC_WS_DD_NARRATIVE();
    double WS_AMT = 0;
    String WS_PAY_AC = " ";
    LNZSRDSC_WS_CHECK_INFO WS_CHECK_INFO = new LNZSRDSC_WS_CHECK_INFO();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    LNCUDRW LNCUDRW = new LNCUDRW();
    LNCUDRWR LNCUDRWR = new LNCUDRWR();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    IBCPOSTA IBCPOSTA = new IBCPOSTA();
    AICUUPIA AICUUPIA = new AICUUPIA();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    LNCUDSC LNCUDSC = new LNCUDSC();
    AICPQIA AICPQIA = new AICPQIA();
    LNCICRCM LNCICRCM = new LNCICRCM();
    LNCTRANM LNCTRANM = new LNCTRANM();
    LNCICAL LNCICAL = new LNCICAL();
    SCCGWA SCCGWA;
    LNCSRDSC LNCSRDSC;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, LNCSRDSC LNCSRDSC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSRDSC = LNCSRDSC;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSRDSC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        LNCSRDSC.RC.RC_APP = "LN";
        LNCSRDSC.RC.RC_RTNCODE = 0;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CALL_LNZUDSC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B210_LOAN_DARWDN_PROC();
            if (pgmRtn) return;
            B600_GENER_RCVD_PROC();
            if (pgmRtn) return;
        } else {
            B300_DELETE_DARWDN_PROC();
            if (pgmRtn) return;
        }
        B400_DRAW_AC_PROC();
        if (pgmRtn) return;
        if (LNCSRDSC.COMM_DATA.PAYI_MEH == '2' 
            || LNCSRDSC.COMM_DATA.PAYI_MEH == '3' 
            || LNCSRDSC.COMM_DATA.PAYI_MEH == '4') {
            B500_PAY_INT_AC_PROC();
            if (pgmRtn) return;
        }
        B500_LOAN_LIMIT_PROC();
        if (pgmRtn) return;
    }
    public void B100_CALL_LNZUDSC() throws IOException,SQLException,Exception {
        B420_GET_NOS_AC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCUDSC);
        LNCUDSC.FUNC = '1';
        LNCUDSC.COMM_DATA.CONTRACT_NO = LNCSRDSC.COMM_DATA.CONTRACT_NO;
        LNCUDSC.COMM_DATA.CCY = LNCSRDSC.COMM_DATA.CCY;
        LNCUDSC.COMM_DATA.DOMI_BR = LNCSRDSC.COMM_DATA.DOMI_BR;
        LNCUDSC.COMM_DATA.BOOK_BR = LNCSRDSC.COMM_DATA.BOOK_BR;
        LNCUDSC.COMM_DATA.PROD_CD = LNCSRDSC.COMM_DATA.PROD_CD;
        LNCUDSC.COMM_DATA.CI_NO = LNCSRDSC.COMM_DATA.CI_NO;
        LNCUDSC.COMM_DATA.LN_TOT_AMT = LNCSRDSC.COMM_DATA.AMT;
        LNCUDSC.COMM_DATA.VAL_DT = LNCSRDSC.COMM_DATA.VAL_DT;
        LNCUDSC.COMM_DATA.DUE_DT = LNCSRDSC.COMM_DATA.DUE_DT;
        LNCUDSC.COMM_DATA.DSC_TYP = LNCSRDSC.COMM_DATA.DSC_TYP;
        LNCUDSC.COMM_DATA.DSC_METH = LNCSRDSC.COMM_DATA.DSC_METH;
        LNCUDSC.COMM_DATA.N_EFF_RAT = LNCSRDSC.COMM_DATA.DSC_NINR;
        LNCUDSC.COMM_DATA.O_EFF_RAT = LNCSRDSC.COMM_DATA.DSC_OINR;
        LNCUDSC.COMM_DATA.LN_INT_AMT = LNCSRDSC.COMM_DATA.DSC_INTA;
        LNCUDSC.COMM_DATA.GRA_DAYS_ACC = 0;
        LNCUDSC.COMM_DATA.DRFT_TYP = LNCSRDSC.COMM_DATA.DRFT_TYP;
        LNCUDSC.COMM_DATA.DRFT_NO = LNCSRDSC.COMM_DATA.DRFT_NO;
        LNCUDSC.COMM_DATA.DRFT_BOT = LNCSRDSC.COMM_DATA.DRFT_BOT;
        LNCUDSC.COMM_DATA.DRFT_BNO = LNCSRDSC.COMM_DATA.DRFT_BNO;
        LNCUDSC.COMM_DATA.DRFT_AMT = LNCSRDSC.COMM_DATA.DRFT_AMT;
        LNCUDSC.COMM_DATA.DRFT_SDT = LNCSRDSC.COMM_DATA.DRFT_SDT;
        LNCUDSC.COMM_DATA.DRFT_EDT = LNCSRDSC.COMM_DATA.DRFT_EDT;
        LNCUDSC.COMM_DATA.PAYEE_NM = LNCSRDSC.COMM_DATA.PAYEE_NM;
        LNCUDSC.COMM_DATA.PAYEE_AC = LNCSRDSC.COMM_DATA.PAYEE_AC;
        LNCUDSC.COMM_DATA.BILL_FRM = LNCSRDSC.COMM_DATA.BILL_FRM;
        LNCUDSC.COMM_DATA.DRFT_FLG = LNCSRDSC.COMM_DATA.DRFT_FLG;
        LNCUDSC.COMM_DATA.DRAW_ACT = LNCSRDSC.COMM_DATA.DRAW_ACT;
        LNCUDSC.COMM_DATA.DRAW_AC = LNCSRDSC.COMM_DATA.DRAW_AC;
        LNCUDSC.COMM_DATA.PAYI_MEH = LNCSRDSC.COMM_DATA.PAYI_MEH;
        LNCUDSC.COMM_DATA.PAYI_PCT = LNCSRDSC.COMM_DATA.PAYI_PCT;
        LNCUDSC.COMM_DATA.PAYI_AMT = LNCSRDSC.COMM_DATA.PAYI_AMT;
        LNCUDSC.COMM_DATA.PAYI_ACT = LNCSRDSC.COMM_DATA.PAYI_ACT;
        LNCUDSC.COMM_DATA.PAYI_AC = LNCSRDSC.COMM_DATA.PAYI_AC;
        LNCUDSC.COMM_DATA.SETL_FLG = LNCSRDSC.COMM_DATA.SETL_FLG;
        LNCUDSC.COMM_DATA.REMARK1 = LNCSRDSC.COMM_DATA.REMARK;
        LNCUDSC.COMM_DATA.TRAN_NO = LNCSRDSC.COMM_DATA.TRAN_NO;
        LNCUDSC.COMM_DATA.TRAN_SEQ = LNCSRDSC.COMM_DATA.TRAN_SEQ;
        LNCUDSC.COMM_DATA.INT_DAY_BAS = LNCSRDSC.COMM_DATA.CALR_STD;
        LNCUDSC.COMM_DATA.DRFT_NM = LNCSRDSC.COMM_DATA.DRFT_NM;
        LNCUDSC.COMM_DATA.DRFT_AC = LNCSRDSC.COMM_DATA.DRFT_AC;
        LNCUDSC.COMM_DATA.DRFT_DNO = LNCSRDSC.COMM_DATA.DRFT_DNO;
        LNCUDSC.COMM_DATA.DRFT_DNM = LNCSRDSC.COMM_DATA.DRFT_DNM;
        LNCUDSC.COMM_DATA.DRFT_ANO = LNCSRDSC.COMM_DATA.DRFT_ANO;
        LNCUDSC.COMM_DATA.DRFT_ANM = LNCSRDSC.COMM_DATA.DRFT_ANM;
        LNCUDSC.COMM_DATA.BB_SDT = LNCSRDSC.COMM_DATA.BB_SDT;
        LNCUDSC.COMM_DATA.ACCRUAL_TYPE = LNCSRDSC.COMM_DATA.ACCRUAL_TYPE;
        LNCUDSC.COMM_DATA.ICAL_DT = LNCSRDSC.COMM_DATA.ICAL_DT;
        LNCUDSC.COMM_DATA.TRCHCMMT_NO = LNCSRDSC.COMM_DATA.CMMT_NO;
        S000_CALL_LNZUDSC();
        if (pgmRtn) return;
    }
    public void B210_LOAN_DARWDN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCUDRW);
        LNCUDRW.COMM_DATA.LN_AC = LNCSRDSC.COMM_DATA.CONTRACT_NO;
        LNCUDRW.COMM_DATA.CCY = LNCSRDSC.COMM_DATA.CCY;
        LNCUDRW.COMM_DATA.ACM_EVENT = "ST";
        LNCUDRW.COMM_DATA.DRAW_AMT = LNCSRDSC.COMM_DATA.AMT;
        CEP.TRC(SCCGWA, LNCSRDSC.COMM_DATA.DSC_INTA);
        LNCUDRW.COMM_DATA.PREP_INT = LNCSRDSC.COMM_DATA.DSC_INTA;
        LNCUDRW.COMM_DATA.VAL_DT = LNCSRDSC.COMM_DATA.VAL_DT;
        S000_CALL_LNZUDRW();
        if (pgmRtn) return;
    }
    public void B300_DELETE_DARWDN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCUDRWR);
        LNCUDRWR.COMM_DATA.LN_AC = LNCSRDSC.COMM_DATA.CONTRACT_NO;
        LNCUDRWR.COMM_DATA.ACM_EVENT = "ST";
        LNCUDRWR.COMM_DATA.DRAW_AMT = LNCSRDSC.COMM_DATA.AMT;
        LNCUDRWR.COMM_DATA.PREP_INT = LNCSRDSC.COMM_DATA.DSC_INTA;
        LNCUDRWR.COMM_DATA.VAL_DT = LNCSRDSC.COMM_DATA.VAL_DT;
        LNCUDRWR.COMM_DATA.TXN_DT = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        LNCUDRWR.COMM_DATA.JRNNO = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
        S000_CALL_LNZUDRWR();
        if (pgmRtn) return;
    }
    public void B400_DRAW_AC_PROC() throws IOException,SQLException,Exception {
        WS_AMT = LNCSRDSC.COMM_DATA.AMT - LNCSRDSC.COMM_DATA.DSC_INTA + LNCSRDSC.COMM_DATA.PAYI_AMT;
        WS_DD_NARRATIVE.WS_NARR_CTA_NO = LNCSRDSC.COMM_DATA.CONTRACT_NO;
        WS_DD_NARRATIVE.WS_NARR_EV_CD = "ST ";
        if (LNCSRDSC.COMM_DATA.DRAW_ACT.equalsIgnoreCase("01")) {
            WS_PAY_AC = LNCSRDSC.COMM_DATA.DRAW_AC;
            B410_DD_AC_C();
            if (pgmRtn) return;
        } else if (LNCSRDSC.COMM_DATA.DRAW_ACT.equalsIgnoreCase("02")) {
            WS_PAY_AC = AICPQIA.AC;
            B420_NOS_AC_C();
            if (pgmRtn) return;
        } else if (LNCSRDSC.COMM_DATA.DRAW_ACT.equalsIgnoreCase("03")) {
            WS_PAY_AC = LNCSRDSC.COMM_DATA.DRAW_AC;
            B430_IB_AC_C();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCSRDSC.COMM_DATA.DRAW_ACT + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B500_PAY_INT_AC_PROC() throws IOException,SQLException,Exception {
        WS_DD_NARRATIVE.WS_NARR_CTA_NO = LNCSRDSC.COMM_DATA.CONTRACT_NO;
        WS_DD_NARRATIVE.WS_NARR_EV_CD = "ST ";
        if (LNCSRDSC.COMM_DATA.PAYI_ACT.equalsIgnoreCase("01")
            || LNCSRDSC.COMM_DATA.PAYI_ACT.equalsIgnoreCase("05")
            || LNCSRDSC.COMM_DATA.PAYI_ACT.equalsIgnoreCase("06")) {
            B510_DD_AC_D();
            if (pgmRtn) return;
        } else if (LNCSRDSC.COMM_DATA.PAYI_ACT.equalsIgnoreCase("02")) {
            B520_NOS_AC_D();
            if (pgmRtn) return;
        } else if (LNCSRDSC.COMM_DATA.PAYI_ACT.equalsIgnoreCase("03")) {
            B530_IB_AC_D();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCSRDSC.COMM_DATA.PAYI_ACT + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B410_DD_AC_C() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUCRAC);
        DDCUCRAC.AC = WS_PAY_AC;
        DDCUCRAC.CCY = LNCSRDSC.COMM_DATA.CCY;
        DDCUCRAC.TX_AMT = WS_AMT;
        DDCUCRAC.OTHER_AC = LNCSRDSC.COMM_DATA.CONTRACT_NO;
        DDCUCRAC.TX_REF = LNCSRDSC.COMM_DATA.CONTRACT_NO;
        DDCUCRAC.VAL_DATE = LNCSRDSC.COMM_DATA.VAL_DT;
        DDCUCRAC.TX_MMO = "X7Y";
        DDCUCRAC.TX_TYPE = 'T';
        S000_CALL_DDZUCRAC();
        if (pgmRtn) return;
    }
    public void B420_GET_NOS_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICPQIA);
        AICPQIA.CD.AC_TYP = "2";
        if (LNCSRDSC.COMM_DATA.DRFT_FLG == 'P') {
            if (LNCSRDSC.COMM_DATA.BILL_FRM == '2') {
                AICPQIA.CD.BUSI_KND = "RDISCOUNT";
            } else {
                AICPQIA.CD.BUSI_KND = "PDISCOUNT";
            }
        } else {
            AICPQIA.CD.BUSI_KND = "ERDISCOUNT";
        }
        AICPQIA.BR = LNCSRDSC.COMM_DATA.BOOK_BR;
        AICPQIA.CCY = LNCSRDSC.COMM_DATA.CCY;
        AICPQIA.SIGN = 'C';
        S000_CALL_AIZPQIA();
        if (pgmRtn) return;
        LNCSRDSC.COMM_DATA.DRAW_AC = AICPQIA.AC;
    }
    public void B420_NOS_AC_C() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = WS_PAY_AC;
        AICUUPIA.DATA.CCY = LNCSRDSC.COMM_DATA.CCY;
        AICUUPIA.DATA.AMT = WS_AMT;
        AICUUPIA.DATA.VALUE_DATE = LNCSRDSC.COMM_DATA.VAL_DT;
        CEP.TRC(SCCGWA, LNCSRDSC.COMM_DATA.RVS_NO);
        AICUUPIA.DATA.RVS_NO = LNCSRDSC.COMM_DATA.RVS_NO;
        AICUUPIA.DATA.EVENT_CODE = "CR";
        S000_CALL_AIZUUPIA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICUUPIA.DATA.RVS_NO);
        LNCSRDSC.COMM_DATA.PAYI_SEQ = AICUUPIA.DATA.RVS_NO;
    }
    public void B430_IB_AC_C() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCPOSTA);
        IBCPOSTA.NOSTRO_CD = WS_PAY_AC;
        IBCPOSTA.CCY = LNCSRDSC.COMM_DATA.CCY;
        IBCPOSTA.AMT = WS_AMT;
        IBCPOSTA.ACT_CTR = LNCSRDSC.COMM_DATA.BOOK_BR;
        IBCPOSTA.SIGN = 'C';
        IBCPOSTA.VAL_DATE = LNCSRDSC.COMM_DATA.VAL_DT;
        IBCPOSTA.ENTRY_FLG = '1';
        IBCPOSTA.THR_REF = LNCSRDSC.COMM_DATA.CONTRACT_NO;
        IBCPOSTA.OUR_REF = LNCSRDSC.COMM_DATA.CONTRACT_NO;
        S000_CALL_IBZCRAC();
        if (pgmRtn) return;
    }
    public void B510_DD_AC_D() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUDRAC);
        CEP.TRC(SCCGWA, LNCSRDSC.COMM_DATA.PAYI_ACT);
        if (LNCSRDSC.COMM_DATA.PAYI_ACT.equalsIgnoreCase("01")) {
            DDCUDRAC.AC = LNCSRDSC.COMM_DATA.PAYI_AC;
        }
        if (LNCSRDSC.COMM_DATA.PAYI_ACT.equalsIgnoreCase("05")) {
            DDCUDRAC.CARD_NO = LNCSRDSC.COMM_DATA.PAYI_AC;
        }
        DDCUDRAC.CCY = LNCSRDSC.COMM_DATA.CCY;
        DDCUDRAC.TX_AMT = LNCSRDSC.COMM_DATA.PAYI_AMT;
        DDCUDRAC.OTHER_AC = LNCSRDSC.COMM_DATA.CONTRACT_NO;
        DDCUDRAC.TX_REF = LNCSRDSC.COMM_DATA.CONTRACT_NO;
        DDCUDRAC.VAL_DATE = LNCSRDSC.COMM_DATA.VAL_DT;
        DDCUDRAC.TX_MMO = "X7Y";
        DDCUDRAC.CHK_PSW_FLG = 'N';
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.BANK_DR_FLG = 'Y';
        if (LNCSRDSC.COMM_DATA.CCY.equalsIgnoreCase("156")) {
            DDCUCRAC.CCY_TYPE = '1';
        } else {
            DDCUCRAC.CCY_TYPE = '2';
        }
        S000_CALL_DDZUDRAC();
        if (pgmRtn) return;
    }
    public void B520_NOS_AC_D() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = LNCSRDSC.COMM_DATA.PAYI_AC;
        AICUUPIA.DATA.CCY = LNCSRDSC.COMM_DATA.CCY;
        AICUUPIA.DATA.AMT = LNCSRDSC.COMM_DATA.PAYI_AMT;
        AICUUPIA.DATA.VALUE_DATE = LNCSRDSC.COMM_DATA.VAL_DT;
        AICUUPIA.DATA.EVENT_CODE = "DR";
        S000_CALL_AIZUUPIA();
        if (pgmRtn) return;
    }
    public void B530_IB_AC_D() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCPOSTA);
        IBCPOSTA.NOSTRO_CD = LNCSRDSC.COMM_DATA.PAYI_AC;
        IBCPOSTA.CCY = LNCSRDSC.COMM_DATA.CCY;
        IBCPOSTA.AMT = LNCSRDSC.COMM_DATA.PAYI_AMT;
        IBCPOSTA.ACT_CTR = LNCSRDSC.COMM_DATA.BOOK_BR;
        IBCPOSTA.SIGN = 'D';
        IBCPOSTA.VAL_DATE = LNCSRDSC.COMM_DATA.VAL_DT;
        IBCPOSTA.ENTRY_FLG = '1';
        IBCPOSTA.THR_REF = LNCSRDSC.COMM_DATA.CONTRACT_NO;
        IBCPOSTA.OUR_REF = LNCSRDSC.COMM_DATA.CONTRACT_NO;
        IBCPOSTA.TXTYPE = "01";
        S000_CALL_IBZDRAC();
        if (pgmRtn) return;
    }
    public void B500_LOAN_LIMIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCSRDSC.COMM_DATA.REV_CMMT);
        if (LNCSRDSC.COMM_DATA.REV_CMMT == 'Y') {
            IBS.init(SCCGWA, LNCICRCM);
            LNCICRCM.DATA.C_T_NO = LNCSRDSC.COMM_DATA.CMMT_NO;
            LNCICRCM.DATA.CONT_NO = LNCSRDSC.COMM_DATA.CONTRACT_NO;
            LNCICRCM.DATA.CCY = LNCSRDSC.COMM_DATA.CCY;
            LNCICRCM.DATA.AMT = LNCSRDSC.COMM_DATA.AMT;
            LNCICRCM.DATA.CI_NO = LNCSRDSC.COMM_DATA.CI_NO;
            LNCICRCM.DATA.VALUE_DATE = LNCSRDSC.COMM_DATA.VAL_DT;
            LNCICRCM.DATA.PROD_TYPE = LNCSRDSC.COMM_DATA.PROD_CD;
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                LNCICRCM.FUNC = 'U';
            } else {
                LNCICRCM.FUNC = 'R';
            }
            LNCICRCM.DATA.TRAN_TYPE = 'D';
            S000_CALL_LNZICRCM();
            if (pgmRtn) return;
        }
    }
    public void B310_READ_LNTTRAN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCTRANM);
        LNCTRANM.FUNC = '4';
        LNCTRANM.REC_DATA.KEY.CONTRACT_NO = LNCSRDSC.COMM_DATA.CONTRACT_NO;
        LNCTRANM.REC_DATA.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCTRANM.REC_DATA.KEY.TR_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        LNCTRANM.REC_DATA.KEY.TXN_TYP = 'P';
        LNCTRANM.REC_DATA.KEY.TXN_TERM = 0;
        LNCTRANM.REC_DATA.KEY.TRAN_FLG = 'D';
        S000_CALL_LNZTRANM();
        if (pgmRtn) return;
    }
    public void B320_UPDATE_LNTTRAN() throws IOException,SQLException,Exception {
        LNCTRANM.FUNC = '2';
        LNCTRANM.REC_DATA.F_AMT = 0;
        LNCTRANM.REC_DATA.REQ_FRM_NO = SCCGWA.COMM_AREA.REQ_SYSTEM;
        LNCTRANM.REC_DATA.TR_MMO = SCCGWA.COMM_AREA.TR_MMO;
        LNCTRANM.REC_DATA.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_LNZTRANM();
        if (pgmRtn) return;
    }
    public void B600_GENER_RCVD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICAL);
        LNCICAL.COMM_DATA.FUNC_CODE = 'U';
        LNCICAL.COMM_DATA.LN_AC = LNCSRDSC.COMM_DATA.CONTRACT_NO;
        LNCICAL.COMM_DATA.SUF_NO = "" + 0;
        JIBS_tmp_int = LNCICAL.COMM_DATA.SUF_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCICAL.COMM_DATA.SUF_NO = "0" + LNCICAL.COMM_DATA.SUF_NO;
        LNCICAL.COMM_DATA.VAL_DATE = LNCSRDSC.COMM_DATA.DUE_DT;
        LNCICAL.COMM_DATA.FUNC_TYPE = 'I';
        if (LNCSRDSC.COMM_DATA.VAL_DT == LNCSRDSC.COMM_DATA.DUE_DT) {
            LNCICAL.COMM_DATA.FUNC_TYPE = ' ';
        }
        S000_CALL_LNZICAL();
        if (pgmRtn) return;
    }
    public void S000_CALL_LNZICAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-INT-CAL-BASE", LNCICAL);
        if (LNCICAL.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCICAL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRDSC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZUDSC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-DISCLOAN-CRDW", LNCUDSC);
        if (LNCUDSC.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCUDSC.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRDSC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZUDRW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-DRAWDOWN", LNCUDRW);
        if (LNCUDRW.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCUDRW.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRDSC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZUDRWR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-REV-DRAW", LNCUDRWR);
        if (LNCUDRWR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCUDRWR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRDSC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC, true);
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC, true);
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZPQIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-IA", AICPQIA);
        CEP.TRC(SCCGWA, AICPQIA.AC);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, AICPQIA.RC);
        if (AICPQIA.RC.RC_CODE != 0 
            || JIBS_tmp_str[1].trim().length() == 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQIA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRDSC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
        CEP.TRC(SCCGWA, AICUUPIA.RC);
        if (AICUUPIA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRDSC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_IBZCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZCRAC", IBCPOSTA);
        if (IBCPOSTA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCPOSTA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRDSC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_IBZDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-DEBIT-AC", IBCPOSTA);
        if (IBCPOSTA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCPOSTA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRDSC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICRCM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-I-COMMITMENT-USE", LNCICRCM);
        if (LNCICRCM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCICRCM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRDSC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZTRANM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-TRAN-MAINT", LNCTRANM);
        if (LNCTRANM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCTRANM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRDSC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
