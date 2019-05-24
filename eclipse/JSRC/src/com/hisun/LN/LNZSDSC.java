package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.DD.*;
import com.hisun.IB.*;
import com.hisun.AI.*;
import com.hisun.CI.*;
import com.hisun.DC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSDSC {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    LNZSDSC_WS_DD_NARRATIVE WS_DD_NARRATIVE = new LNZSDSC_WS_DD_NARRATIVE();
    double WS_AMT = 0;
    String WS_PAY_AC = " ";
    String WS_RVS_NO = " ";
    LNZSDSC_WS_CHECK_INFO WS_CHECK_INFO = new LNZSDSC_WS_CHECK_INFO();
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
    CICSAGEN CICSAGEN = new CICSAGEN();
    LNCICRCM LNCICRCM = new LNCICRCM();
    LNCTRANM LNCTRANM = new LNCTRANM();
    DCCPACTY DCCPACTY = new DCCPACTY();
    LNCICAL LNCICAL = new LNCICAL();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    LNCSDSC LNCSDSC;
    public void MP(SCCGWA SCCGWA, LNCSDSC LNCSDSC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSDSC = LNCSDSC;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSDSC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        LNCSDSC.RC.RC_APP = "LN";
        LNCSDSC.RC.RC_RTNCODE = 0;
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
        if (LNCSDSC.COMM_DATA.PAYI_MEH == '2' 
            || LNCSDSC.COMM_DATA.PAYI_MEH == '3' 
            || LNCSDSC.COMM_DATA.PAYI_MEH == '4') {
            B500_PAY_INT_AC_PROC();
            if (pgmRtn) return;
        }
        B500_LOAN_LIMIT_PROC();
        if (pgmRtn) return;
    }
    public void B100_CALL_LNZUDSC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCUDSC);
        LNCUDSC.FUNC = '1';
        LNCUDSC.COMM_DATA.CONTRACT_NO = LNCSDSC.COMM_DATA.CONTRACT_NO;
        LNCUDSC.COMM_DATA.CCY = LNCSDSC.COMM_DATA.CCY;
        LNCUDSC.COMM_DATA.DOMI_BR = LNCSDSC.COMM_DATA.DOMI_BR;
        LNCUDSC.COMM_DATA.BOOK_BR = LNCSDSC.COMM_DATA.BOOK_BR;
        LNCUDSC.COMM_DATA.PROD_CD = LNCSDSC.COMM_DATA.PROD_CD;
        LNCUDSC.COMM_DATA.CI_NO = LNCSDSC.COMM_DATA.CI_NO;
        LNCUDSC.COMM_DATA.LN_TOT_AMT = LNCSDSC.COMM_DATA.AMT;
        LNCUDSC.COMM_DATA.EX_RATE = LNCSDSC.COMM_DATA.EX_RATE;
        LNCUDSC.COMM_DATA.T_EQUI = LNCSDSC.COMM_DATA.T_C_EQUI;
        LNCUDSC.COMM_DATA.VAL_DT = LNCSDSC.COMM_DATA.VAL_DT;
        LNCUDSC.COMM_DATA.DUE_DT = LNCSDSC.COMM_DATA.DUE_DT;
        LNCUDSC.COMM_DATA.DSC_TYP = LNCSDSC.COMM_DATA.DSC_TYP;
        LNCUDSC.COMM_DATA.DSC_METH = LNCSDSC.COMM_DATA.DSC_METH;
        LNCUDSC.COMM_DATA.N_EFF_RAT = LNCSDSC.COMM_DATA.DSC_NINR;
        LNCUDSC.COMM_DATA.O_EFF_RAT = LNCSDSC.COMM_DATA.DSC_OINR;
        LNCUDSC.COMM_DATA.LN_INT_AMT = LNCSDSC.COMM_DATA.DSC_INTA;
        LNCUDSC.COMM_DATA.GRA_DAYS_ACC = 0;
        LNCUDSC.COMM_DATA.DRFT_TYP = LNCSDSC.COMM_DATA.DRFT_TYP;
        LNCUDSC.COMM_DATA.DRFT_NO = LNCSDSC.COMM_DATA.DRFT_NO;
        LNCUDSC.COMM_DATA.BATCH_NO = LNCSDSC.COMM_DATA.BATCH_NO;
        LNCUDSC.COMM_DATA.DRFT_FLG = LNCSDSC.COMM_DATA.DRFT_FLG;
        LNCUDSC.COMM_DATA.DRAW_ACT = LNCSDSC.COMM_DATA.DRAW_ACT;
        LNCUDSC.COMM_DATA.DRAW_AC = LNCSDSC.COMM_DATA.DRAW_AC;
        LNCUDSC.COMM_DATA.DRAW_ACT_O = LNCSDSC.COMM_DATA.DRAW_ACT_O;
        LNCUDSC.COMM_DATA.DRAW_AC_O = LNCSDSC.COMM_DATA.DRAW_AC_O;
        LNCUDSC.COMM_DATA.PAYI_MEH = LNCSDSC.COMM_DATA.PAYI_MEH;
        LNCUDSC.COMM_DATA.PAYI_PCT = LNCSDSC.COMM_DATA.PAYI_PCT;
        LNCUDSC.COMM_DATA.PAYI_AMT = LNCSDSC.COMM_DATA.PAYI_AMT;
        LNCUDSC.COMM_DATA.PAYI_ACT = LNCSDSC.COMM_DATA.PAYI_ACT;
        LNCUDSC.COMM_DATA.PAYI_AC = LNCSDSC.COMM_DATA.PAYI_AC;
        LNCUDSC.COMM_DATA.ICAL_DT = LNCSDSC.COMM_DATA.ICAL_DT;
        LNCUDSC.COMM_DATA.SETL_FLG = LNCSDSC.COMM_DATA.SETL_FLG;
        LNCUDSC.COMM_DATA.TRU_FLG = LNCSDSC.COMM_DATA.TRU_FLG;
        LNCUDSC.COMM_DATA.TRU_FEE = LNCSDSC.COMM_DATA.TRU_FEE;
        LNCUDSC.COMM_DATA.TRU_CINO = LNCSDSC.COMM_DATA.TRU_CINO;
        LNCUDSC.COMM_DATA.TRU_ACNO = LNCSDSC.COMM_DATA.TRU_ACNO;
        LNCUDSC.COMM_DATA.REMARK1 = LNCSDSC.COMM_DATA.REMARK;
        LNCUDSC.COMM_DATA.INT_DAY_BAS = LNCSDSC.COMM_DATA.CALR_STD;
        LNCUDSC.COMM_DATA.DRFT_BOT = LNCSDSC.COMM_DATA.DRFT_BOT;
        LNCUDSC.COMM_DATA.DRFT_AMT = LNCSDSC.COMM_DATA.DRFT_AMT;
        LNCUDSC.COMM_DATA.DRFT_SDT = LNCSDSC.COMM_DATA.DRFT_SDT;
        LNCUDSC.COMM_DATA.DRFT_EDT = LNCSDSC.COMM_DATA.DRFT_EDT;
        LNCUDSC.COMM_DATA.DRFT_NM = LNCSDSC.COMM_DATA.DRFT_NM;
        LNCUDSC.COMM_DATA.DRFT_AC = LNCSDSC.COMM_DATA.DRFT_AC;
        LNCUDSC.COMM_DATA.DRFT_DNO = LNCSDSC.COMM_DATA.DRFT_DNO;
        LNCUDSC.COMM_DATA.DRFT_DNM = LNCSDSC.COMM_DATA.DRFT_DNM;
        LNCUDSC.COMM_DATA.DRFT_ANO = LNCSDSC.COMM_DATA.DRFT_ANO;
        LNCUDSC.COMM_DATA.DRFT_ANM = LNCSDSC.COMM_DATA.DRFT_ANM;
        LNCUDSC.COMM_DATA.PAYEE_NM = LNCSDSC.COMM_DATA.PAYEE_NM;
        LNCUDSC.COMM_DATA.PAYEE_AC = LNCSDSC.COMM_DATA.PAYEE_AC;
        LNCUDSC.COMM_DATA.BB_SDT = LNCSDSC.COMM_DATA.BB_SDT;
        LNCUDSC.COMM_DATA.TRAN_NO = LNCSDSC.COMM_DATA.TRAN_NO;
        LNCUDSC.COMM_DATA.TRAN_NAM = LNCSDSC.COMM_DATA.TRAN_NAM;
        LNCUDSC.COMM_DATA.TRAN_SEQ = LNCSDSC.COMM_DATA.TRAN_SEQ;
        LNCUDSC.COMM_DATA.TRCHCMMT_NO = LNCSDSC.COMM_DATA.CMMT_NO;
        LNCUDSC.COMM_DATA.ACCRUAL_TYPE = LNCSDSC.COMM_DATA.ACCRUAL_TYPE;
        LNCUDSC.COMM_DATA.DRFT_BNO = LNCSDSC.COMM_DATA.DRFT_BNO;
        LNCUDSC.COMM_DATA.BILL_FRM = '2';
        S000_CALL_LNZUDSC();
        if (pgmRtn) return;
    }
    public void B210_LOAN_DARWDN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCUDRW);
        LNCUDRW.COMM_DATA.LN_AC = LNCSDSC.COMM_DATA.CONTRACT_NO;
        LNCUDRW.COMM_DATA.CCY = LNCSDSC.COMM_DATA.CCY;
        LNCUDRW.COMM_DATA.ACM_EVENT = "ST";
        LNCUDRW.COMM_DATA.DRAW_AMT = LNCSDSC.COMM_DATA.AMT;
        CEP.TRC(SCCGWA, LNCSDSC.COMM_DATA.DSC_INTA);
        LNCUDRW.COMM_DATA.PREP_INT = LNCSDSC.COMM_DATA.DSC_INTA;
        LNCUDRW.COMM_DATA.VAL_DT = LNCSDSC.COMM_DATA.VAL_DT;
        LNCUDRW.COMM_DATA.TRU_FEE = LNCSDSC.COMM_DATA.TRU_FEE;
        S000_CALL_LNZUDRW();
        if (pgmRtn) return;
    }
    public void B300_DELETE_DARWDN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCUDRWR);
        LNCUDRWR.COMM_DATA.LN_AC = LNCSDSC.COMM_DATA.CONTRACT_NO;
        LNCUDRWR.COMM_DATA.ACM_EVENT = "ST";
        LNCUDRWR.COMM_DATA.DRAW_AMT = LNCSDSC.COMM_DATA.AMT;
        LNCUDRWR.COMM_DATA.PREP_INT = LNCSDSC.COMM_DATA.DSC_INTA;
        LNCUDRWR.COMM_DATA.VAL_DT = LNCSDSC.COMM_DATA.VAL_DT;
        LNCUDRWR.COMM_DATA.TXN_DT = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        LNCUDRWR.COMM_DATA.JRNNO = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
        S000_CALL_LNZUDRWR();
        if (pgmRtn) return;
    }
    public void B400_DRAW_AC_PROC() throws IOException,SQLException,Exception {
        WS_AMT = LNCSDSC.COMM_DATA.AMT - LNCSDSC.COMM_DATA.DSC_INTA + LNCSDSC.COMM_DATA.PAYI_AMT;
        if (LNCSDSC.COMM_DATA.TRU_FLG == 'Y') {
            B600_AGENT_TRAN_PROC();
            if (pgmRtn) return;
            if (LNCSDSC.COMM_DATA.TRU_FEE != 0) {
                B400_TRU_ACNO_PROC();
                if (pgmRtn) return;
            }
        }
        WS_DD_NARRATIVE.WS_NARR_CTA_NO = LNCSDSC.COMM_DATA.CONTRACT_NO;
        WS_DD_NARRATIVE.WS_NARR_EV_CD = "ST ";
        if (LNCSDSC.COMM_DATA.DRAW_ACT.equalsIgnoreCase("01")
            || LNCSDSC.COMM_DATA.DRAW_ACT.equalsIgnoreCase("05")
            || LNCSDSC.COMM_DATA.DRAW_ACT.equalsIgnoreCase("06")) {
            WS_PAY_AC = LNCSDSC.COMM_DATA.DRAW_AC;
            B410_DD_AC_C();
            if (pgmRtn) return;
        } else if (LNCSDSC.COMM_DATA.DRAW_ACT.equalsIgnoreCase("02")) {
            WS_PAY_AC = LNCSDSC.COMM_DATA.DRAW_AC;
            WS_RVS_NO = LNCSDSC.COMM_DATA.DRAW_SEQ;
            B420_NOS_AC_C();
            if (pgmRtn) return;
        } else if (LNCSDSC.COMM_DATA.DRAW_ACT.equalsIgnoreCase("03")) {
            WS_PAY_AC = LNCSDSC.COMM_DATA.DRAW_AC;
            B430_IB_AC_C();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCSDSC.COMM_DATA.DRAW_ACT + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B600_AGENT_TRAN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            IBS.init(SCCGWA, CICSAGEN);
            CICSAGEN.FUNC = 'A';
            CICSAGEN.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
            CICSAGEN.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
            CICSAGEN.TX_CODE = "" + SCCGWA.COMM_AREA.TR_ID.TR_CODE;
            JIBS_tmp_int = CICSAGEN.TX_CODE.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) CICSAGEN.TX_CODE = "0" + CICSAGEN.TX_CODE;
            CICSAGEN.BR = LNCSDSC.COMM_DATA.BOOK_BR;
            CICSAGEN.TLR_NO = SCCGWA.COMM_AREA.TL_ID;
            CICSAGEN.CI_NO = LNCSDSC.COMM_DATA.CI_NO;
            CICSAGEN.AGE_CINO = LNCSDSC.COMM_DATA.TRU_CINO;
            CICSAGEN.OUT_AC = LNCSDSC.COMM_DATA.CONTRACT_NO;
            S000_CALL_CIZSAGEN();
            if (pgmRtn) return;
        }
    }
    public void B400_TRU_ACNO_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUCRAC);
        IBS.init(SCCGWA, DCCPACTY);
        DCCPACTY.INPUT.AC = LNCSDSC.COMM_DATA.TRU_ACNO;
        DCCPACTY.INPUT.FUNC = '1';
        S000_CALL_DCZPACTY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.AC_TYPE);
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.STD_APP);
        if (DCCPACTY.OUTPUT.AC_TYPE == 'D' 
                && DCCPACTY.OUTPUT.STD_APP.equalsIgnoreCase("IB")) {
        } else if (DCCPACTY.OUTPUT.AC_TYPE == 'D' 
                && DCCPACTY.OUTPUT.STD_APP.equalsIgnoreCase("DD")) {
            DDCUCRAC.AC = LNCSDSC.COMM_DATA.TRU_ACNO;
        } else if (DCCPACTY.OUTPUT.AC_TYPE == 'K') {
            DDCUCRAC.CARD_NO = LNCSDSC.COMM_DATA.TRU_ACNO;
        } else if (DCCPACTY.OUTPUT.AC_TYPE == 'G') {
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.SET_MET_NOT_RIGHT, LNCSDSC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        DDCUCRAC.CCY = LNCSDSC.COMM_DATA.CCY;
        DDCUCRAC.TX_AMT = LNCSDSC.COMM_DATA.TRU_FEE;
        DDCUCRAC.OTHER_AC = LNCSDSC.COMM_DATA.CONTRACT_NO;
        DDCUCRAC.TX_REF = LNCSDSC.COMM_DATA.CONTRACT_NO;
        DDCUCRAC.VAL_DATE = LNCSDSC.COMM_DATA.VAL_DT;
        DDCUCRAC.TX_MMO = "X7T";
        DDCUCRAC.TX_TYPE = 'T';
        DDCUCRAC.BANK_CR_FLG = 'Y';
        if (LNCSDSC.COMM_DATA.CCY.equalsIgnoreCase("156")) {
            DDCUCRAC.CCY_TYPE = '1';
        } else {
            DDCUCRAC.CCY_TYPE = '2';
        }
        S000_CALL_DDZUCRAC();
        if (pgmRtn) return;
        WS_AMT = WS_AMT - LNCSDSC.COMM_DATA.TRU_FEE;
    }
    public void B500_PAY_INT_AC_PROC() throws IOException,SQLException,Exception {
        WS_DD_NARRATIVE.WS_NARR_CTA_NO = LNCSDSC.COMM_DATA.CONTRACT_NO;
        WS_DD_NARRATIVE.WS_NARR_EV_CD = "ST ";
        if (LNCSDSC.COMM_DATA.PAYI_ACT.equalsIgnoreCase("01")
            || LNCSDSC.COMM_DATA.PAYI_ACT.equalsIgnoreCase("05")
            || LNCSDSC.COMM_DATA.PAYI_ACT.equalsIgnoreCase("06")) {
            B510_DD_AC_D();
            if (pgmRtn) return;
        } else if (LNCSDSC.COMM_DATA.PAYI_ACT.equalsIgnoreCase("02")) {
            B520_NOS_AC_D();
            if (pgmRtn) return;
        } else if (LNCSDSC.COMM_DATA.PAYI_ACT.equalsIgnoreCase("03")) {
            B530_IB_AC_D();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCSDSC.COMM_DATA.PAYI_ACT + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B410_DD_AC_C() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUCRAC);
        if (LNCSDSC.COMM_DATA.DRAW_ACT.equalsIgnoreCase("01")) {
            DDCUCRAC.AC = WS_PAY_AC;
        }
        if (LNCSDSC.COMM_DATA.DRAW_ACT.equalsIgnoreCase("05")) {
            DDCUDRAC.CARD_NO = WS_PAY_AC;
        }
        DDCUCRAC.CCY = LNCSDSC.COMM_DATA.CCY;
        DDCUCRAC.TX_AMT = WS_AMT;
        DDCUCRAC.OTHER_AC = LNCSDSC.COMM_DATA.CONTRACT_NO;
        DDCUCRAC.TX_REF = LNCSDSC.COMM_DATA.CONTRACT_NO;
        DDCUDRAC.OTHER_AC_NM = LNCSDSC.COMM_DATA.CONTRACT_NO;
        DDCUCRAC.VAL_DATE = LNCSDSC.COMM_DATA.VAL_DT;
        DDCUCRAC.TX_MMO = "X7T";
        DDCUCRAC.TX_TYPE = 'T';
        DDCUCRAC.BANK_CR_FLG = 'Y';
        if (LNCSDSC.COMM_DATA.CCY.equalsIgnoreCase("156")) {
            DDCUCRAC.CCY_TYPE = '1';
        } else {
            DDCUCRAC.CCY_TYPE = '2';
        }
        S000_CALL_DDZUCRAC();
        if (pgmRtn) return;
    }
    public void B420_NOS_AC_C() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = WS_PAY_AC;
        AICUUPIA.DATA.CCY = LNCSDSC.COMM_DATA.CCY;
        AICUUPIA.DATA.AMT = WS_AMT;
        AICUUPIA.DATA.VALUE_DATE = LNCSDSC.COMM_DATA.VAL_DT;
        AICUUPIA.DATA.RVS_NO = WS_RVS_NO;
        AICUUPIA.DATA.EVENT_CODE = "CR";
        if (AICUUPIA.DATA.AMT != 0) {
            S000_CALL_AIZUUPIA();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, AICUUPIA.DATA.RVS_NO);
        WS_RVS_NO = AICUUPIA.DATA.RVS_NO;
        LNCSDSC.COMM_DATA.DRAW_SEQ = AICUUPIA.DATA.RVS_NO;
    }
    public void B430_IB_AC_C() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCPOSTA);
        IBCPOSTA.NOSTRO_CD = WS_PAY_AC;
        IBCPOSTA.CCY = LNCSDSC.COMM_DATA.CCY;
        IBCPOSTA.AMT = WS_AMT;
        IBCPOSTA.ACT_CTR = LNCSDSC.COMM_DATA.BOOK_BR;
        IBCPOSTA.SIGN = 'C';
        IBCPOSTA.VAL_DATE = LNCSDSC.COMM_DATA.VAL_DT;
        IBCPOSTA.ENTRY_FLG = '1';
        IBCPOSTA.THR_REF = LNCSDSC.COMM_DATA.CONTRACT_NO;
        IBCPOSTA.OUR_REF = LNCSDSC.COMM_DATA.CONTRACT_NO;
        S000_CALL_IBZCRAC();
        if (pgmRtn) return;
    }
    public void B510_DD_AC_D() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUDRAC);
        CEP.TRC(SCCGWA, LNCSDSC.COMM_DATA.PAYI_ACT);
        if (LNCSDSC.COMM_DATA.PAYI_ACT.equalsIgnoreCase("01")) {
            DDCUDRAC.AC = LNCSDSC.COMM_DATA.PAYI_AC;
        }
        if (LNCSDSC.COMM_DATA.PAYI_ACT.equalsIgnoreCase("05")) {
            DDCUDRAC.CARD_NO = LNCSDSC.COMM_DATA.PAYI_AC;
        }
        DDCUDRAC.CCY = LNCSDSC.COMM_DATA.CCY;
        DDCUDRAC.TX_AMT = LNCSDSC.COMM_DATA.PAYI_AMT;
        DDCUDRAC.OTHER_AC = LNCSDSC.COMM_DATA.CONTRACT_NO;
        DDCUDRAC.TX_REF = LNCSDSC.COMM_DATA.CONTRACT_NO;
        DDCUDRAC.VAL_DATE = LNCSDSC.COMM_DATA.VAL_DT;
        DDCUDRAC.TX_MMO = "X7T";
        DDCUDRAC.CHK_PSW_FLG = 'N';
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.BANK_DR_FLG = 'Y';
        if (LNCSDSC.COMM_DATA.CCY.equalsIgnoreCase("156")) {
            DDCUCRAC.CCY_TYPE = '1';
        } else {
            DDCUCRAC.CCY_TYPE = '2';
        }
        S000_CALL_DDZUDRAC();
        if (pgmRtn) return;
    }
    public void B520_NOS_AC_D() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = LNCSDSC.COMM_DATA.PAYI_AC;
        AICUUPIA.DATA.CCY = LNCSDSC.COMM_DATA.CCY;
        AICUUPIA.DATA.AMT = LNCSDSC.COMM_DATA.PAYI_AMT;
        AICUUPIA.DATA.RVS_NO = LNCSDSC.COMM_DATA.PAYI_SEQ;
        AICUUPIA.DATA.VALUE_DATE = LNCSDSC.COMM_DATA.VAL_DT;
        AICUUPIA.DATA.EVENT_CODE = "DR";
        if (AICUUPIA.DATA.AMT != 0) {
            S000_CALL_AIZUUPIA();
            if (pgmRtn) return;
        }
    }
    public void B530_IB_AC_D() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCPOSTA);
        IBCPOSTA.ACT_CTR = LNCSDSC.COMM_DATA.BOOK_BR;
        CEP.TRC(SCCGWA, IBCPOSTA.ACT_CTR);
        IBCPOSTA.NOSTRO_CD = LNCSDSC.COMM_DATA.PAYI_AC;
        IBCPOSTA.CCY = LNCSDSC.COMM_DATA.CCY;
        IBCPOSTA.AMT = LNCSDSC.COMM_DATA.PAYI_AMT;
        CEP.TRC(SCCGWA, IBCPOSTA.NOSTRO_CD);
        CEP.TRC(SCCGWA, IBCPOSTA.CCY);
        CEP.TRC(SCCGWA, IBCPOSTA.AMT);
        IBCPOSTA.TXTYPE = "01";
        IBCPOSTA.VAL_DATE = LNCSDSC.COMM_DATA.VAL_DT;
        IBCPOSTA.ENTRY_FLG = '1';
        IBCPOSTA.SIGN = 'D';
        IBCPOSTA.OUR_REF = LNCSDSC.COMM_DATA.CONTRACT_NO;
        IBCPOSTA.THR_REF = LNCSDSC.COMM_DATA.CONTRACT_NO;
        S000_CALL_IBZDRAC();
        if (pgmRtn) return;
    }
    public void B500_LOAN_LIMIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCSDSC.COMM_DATA.REV_CMMT);
        if (LNCSDSC.COMM_DATA.REV_CMMT == 'Y') {
            IBS.init(SCCGWA, LNCICRCM);
            LNCICRCM.DATA.C_T_NO = LNCSDSC.COMM_DATA.CMMT_NO;
            LNCICRCM.DATA.CONT_NO = LNCSDSC.COMM_DATA.CONTRACT_NO;
            LNCICRCM.DATA.CCY = LNCSDSC.COMM_DATA.CCY;
            LNCICRCM.DATA.AMT = LNCSDSC.COMM_DATA.AMT;
            LNCICRCM.DATA.EQU_AMT = LNCSDSC.COMM_DATA.T_C_EQUI;
            LNCICRCM.DATA.CI_NO = LNCSDSC.COMM_DATA.CI_NO;
            LNCICRCM.DATA.VALUE_DATE = LNCSDSC.COMM_DATA.VAL_DT;
            LNCICRCM.DATA.EX_RATE = LNCSDSC.COMM_DATA.EX_RATE;
            LNCICRCM.DATA.PROD_TYPE = LNCSDSC.COMM_DATA.PROD_CD;
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
    public void B600_GENER_RCVD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICAL);
        LNCICAL.COMM_DATA.FUNC_CODE = 'U';
        LNCICAL.COMM_DATA.LN_AC = LNCSDSC.COMM_DATA.CONTRACT_NO;
        LNCICAL.COMM_DATA.SUF_NO = "" + 0;
        JIBS_tmp_int = LNCICAL.COMM_DATA.SUF_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCICAL.COMM_DATA.SUF_NO = "0" + LNCICAL.COMM_DATA.SUF_NO;
        LNCICAL.COMM_DATA.VAL_DATE = LNCSDSC.COMM_DATA.DUE_DT;
        LNCICAL.COMM_DATA.FUNC_TYPE = 'I';
        S000_CALL_LNZICAL();
        if (pgmRtn) return;
    }
    public void B310_READ_LNTTRAN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCTRANM);
        LNCTRANM.FUNC = '4';
        LNCTRANM.REC_DATA.KEY.CONTRACT_NO = LNCSDSC.COMM_DATA.CONTRACT_NO;
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
        if (LNCSDSC.COMM_DATA.TRU_FLG == 'N') {
            LNCTRANM.REC_DATA.F_AMT = 0;
        }
        LNCTRANM.REC_DATA.REQ_FRM_NO = SCCGWA.COMM_AREA.REQ_SYSTEM;
        LNCTRANM.REC_DATA.TR_MMO = SCCGWA.COMM_AREA.TR_MMO;
        LNCTRANM.REC_DATA.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_LNZTRANM();
        if (pgmRtn) return;
    }
    public void S000_CALL_CIZSAGEN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SVR-CIZSAGEN", CICSAGEN);
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        CEP.TRC(SCCGWA, DCCPACTY.RC.RC_CODE);
        if (DCCPACTY.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSDSC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-INT-CAL-BASE", LNCICAL);
        if (LNCICAL.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCICAL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSDSC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZUDSC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-DISCLOAN-CRDW", LNCUDSC);
        if (LNCUDSC.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCUDSC.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSDSC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZUDRW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-DRAWDOWN", LNCUDRW);
        if (LNCUDRW.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCUDRW.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSDSC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZUDRWR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-REV-DRAW", LNCUDRWR);
        if (LNCUDRWR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCUDRWR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSDSC.RC);
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
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
        CEP.TRC(SCCGWA, AICUUPIA.RC);
        if (AICUUPIA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSDSC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_IBZCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZCRAC", IBCPOSTA);
        if (IBCPOSTA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCPOSTA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSDSC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_IBZDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-DEBIT-AC", IBCPOSTA);
        if (IBCPOSTA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCPOSTA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSDSC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICRCM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-I-COMMITMENT-USE", LNCICRCM);
        if (LNCICRCM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCICRCM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSDSC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZTRANM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-TRAN-MAINT", LNCTRANM);
        if (LNCTRANM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCTRANM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSDSC.RC);
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCSDSC.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCSDSC=");
            CEP.TRC(SCCGWA, LNCSDSC);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
