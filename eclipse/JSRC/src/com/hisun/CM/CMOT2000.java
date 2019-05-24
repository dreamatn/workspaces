package com.hisun.CM;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.AI.AICUUPIA;
import com.hisun.BP.BPCPOEWA;
import com.hisun.DC.DCCPACTY;
import com.hisun.DD.DDCUCRAC;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.TD.TDCTDBD;

public class CMOT2000 {
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    short WS_I = 0;
    char WS_AC_TYPE = ' ';
    char WS_AC_TYPE_A = ' ';
    String WS_CARD_NO = " ";
    String WS_CARD_NO_A = " ";
    String WS_AC_NO = " ";
    String WS_AC_NO_A = " ";
    double WS_DD_AMT = 0;
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    AICUUPIA AICUUPIA = new AICUUPIA();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    DCCPACTY DCCPACTY = new DCCPACTY();
    TDCTDBD TDCTDBD = new TDCTDBD();
    CMCF200 CMCF200 = new CMCF200();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CMB2000_AWA_2000 CMB2000_AWA_2000;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CMOT2000 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CMB2000_AWA_2000>");
        CMB2000_AWA_2000 = (CMB2000_AWA_2000) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B030_MAIN_PROC();
        if (pgmRtn) return;
        B050_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CMB2000_AWA_2000.DD_AC);
        if (CMB2000_AWA_2000.DD_AC.trim().length() > 0) {
            CEP.TRC(SCCGWA, "CHK DD INPUT DATA");
            CEP.TRC(SCCGWA, CMB2000_AWA_2000.DD_CCYTP);
            if (CMB2000_AWA_2000.DD_CCYTP == ' ') {
                WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_ERR_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, CMB2000_AWA_2000.AI_MTH);
        if (CMB2000_AWA_2000.AI_MTH == 'I' 
            || CMB2000_AWA_2000.AI_MTH == 'A') {
            CEP.TRC(SCCGWA, "CHK MIB INPUT DATA");
            CEP.TRC(SCCGWA, CMB2000_AWA_2000.MIB_AC);
            CEP.TRC(SCCGWA, CMB2000_AWA_2000.MIB_AMT);
            if (CMB2000_AWA_2000.MIB_AC.trim().length() == 0 
                || CMB2000_AWA_2000.MIB_AMT == 0) {
                WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_ERR_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, "CHK PAY AMT & MIB AMT");
            if (CMB2000_AWA_2000.PAY_AMT != CMB2000_AWA_2000.MIB_AMT) {
                WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_ERR_AMT_DIF;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (CMB2000_AWA_2000.AI_MTH == 'E') {
            CEP.TRC(SCCGWA, "CHK EVENT INPUT DATA");
            CEP.TRC(SCCGWA, CMB2000_AWA_2000.PRDMO_CD);
            CEP.TRC(SCCGWA, CMB2000_AWA_2000.EVENT_CD);
            if (CMB2000_AWA_2000.PRDMO_CD.trim().length() == 0 
                || CMB2000_AWA_2000.EVENT_CD.trim().length() == 0) {
                WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_ERR_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (!IBS.isNumeric(CMB2000_AWA_2000.AMT_NO01+"") 
                || !IBS.isNumeric(CMB2000_AWA_2000.AMT_VAL1+"") 
                || !IBS.isNumeric(CMB2000_AWA_2000.AMT_NO02+"") 
                || !IBS.isNumeric(CMB2000_AWA_2000.AMT_VAL2+"") 
                || !IBS.isNumeric(CMB2000_AWA_2000.AMT_NO03+"") 
                || !IBS.isNumeric(CMB2000_AWA_2000.AMT_VAL3+"") 
                || !IBS.isNumeric(CMB2000_AWA_2000.AMT_NO04+"") 
                || !IBS.isNumeric(CMB2000_AWA_2000.AMT_VAL4+"") 
                || !IBS.isNumeric(CMB2000_AWA_2000.AMT_NO05+"") 
                || !IBS.isNumeric(CMB2000_AWA_2000.AMT_VAL5+"")) {
                WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_MUST_INP_NUM;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B030_MAIN_PROC() throws IOException,SQLException,Exception {
        B030_01_DR_TD_PROC();
        if (pgmRtn) return;
        WS_DD_AMT = TDCTDBD.OUTPUT_DATA.BAL + TDCTDBD.OUTPUT_DATA.INT - CMB2000_AWA_2000.PAY_AMT;
        CEP.TRC(SCCGWA, WS_DD_AMT);
        if (WS_DD_AMT > 0) {
            R000_AC_TYPE_PROC();
            if (pgmRtn) return;
            B030_03_CR_DD_PROC();
            if (pgmRtn) return;
        }
        if (WS_DD_AMT < 0) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_ERR_AMT_DIF;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, CMB2000_AWA_2000.AI_MTH);
        if (CMB2000_AWA_2000.AI_MTH == 'I' 
            || CMB2000_AWA_2000.AI_MTH == 'E') {
            B030_05_CR_AI_PROC();
            if (pgmRtn) return;
        } else {
            if (CMB2000_AWA_2000.AI_MTH == 'A') {
                R000_AC_TYPE_A_PROC();
                if (pgmRtn) return;
                B030_05_CR_DD_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B030_01_DR_TD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCTDBD);
        IBS.init(SCCGWA, CMCF200);
        TDCTDBD.INPUT_DATA.OPT = CMB2000_AWA_2000.TD_DR_TP;
        TDCTDBD.INPUT_DATA.BV_TYP = CMB2000_AWA_2000.TD_BV_TP;
        TDCTDBD.INPUT_DATA.BV_NO = CMB2000_AWA_2000.TD_BV_NO;
        TDCTDBD.INPUT_DATA.MAIN_AC = CMB2000_AWA_2000.TD_AC;
        TDCTDBD.INPUT_DATA.MMO = CMB2000_AWA_2000.TD_MMO;
        TDCTDBD.INPUT_DATA.CCY = CMB2000_AWA_2000.CCY;
        TDCTDBD.INPUT_DATA.CCY_TYP = CMB2000_AWA_2000.TD_CCYTP;
        TDCTDBD.INPUT_DATA.CCY_AMT = CMB2000_AWA_2000.PAY_AMT;
        S000_CALL_TDZTDBD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDCTDBD.OUTPUT_DATA.BAL);
        CEP.TRC(SCCGWA, TDCTDBD.OUTPUT_DATA.INT);
        CMCF200.PRIN_AMT = TDCTDBD.OUTPUT_DATA.BAL;
        CMCF200.INT_AMT = TDCTDBD.OUTPUT_DATA.INT;
    }
    public void B030_03_CR_DD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUCRAC);
        DDCUCRAC.CARD_NO = WS_CARD_NO;
        DDCUCRAC.AC = WS_AC_NO;
        DDCUCRAC.CCY = CMB2000_AWA_2000.CCY;
        DDCUCRAC.CCY_TYPE = CMB2000_AWA_2000.DD_CCYTP;
        DDCUCRAC.TX_AMT = WS_DD_AMT;
        DDCUCRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCUCRAC.NARRATIVE = CMB2000_AWA_2000.TXN_NARR;
        DDCUCRAC.TX_TYPE = 'T';
        if (CMB2000_AWA_2000.DD_MMO.trim().length() == 0) {
            DDCUCRAC.TX_MMO = "101";
        } else {
            DDCUCRAC.TX_MMO = CMB2000_AWA_2000.DD_MMO;
        }
        DDCUCRAC.REMARKS = CMB2000_AWA_2000.REMARK;
        S000_CALL_DDZUCRAC();
        if (pgmRtn) return;
    }
    public void B030_05_CR_DD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUCRAC);
        DDCUCRAC.CARD_NO = WS_CARD_NO_A;
        DDCUCRAC.AC = WS_AC_NO_A;
        DDCUCRAC.CCY = "156";
        DDCUCRAC.CCY_TYPE = '1';
        DDCUCRAC.TX_AMT = CMB2000_AWA_2000.MIB_AMT;
        DDCUCRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCUCRAC.NARRATIVE = CMB2000_AWA_2000.TXN_NARR;
        DDCUCRAC.TX_TYPE = 'T';
        DDCUCRAC.TX_MMO = "101";
        DDCUCRAC.REMARKS = CMB2000_AWA_2000.REMARK;
        S000_CALL_DDZUCRAC();
        if (pgmRtn) return;
    }
    public void B030_05_CR_AI_PROC() throws IOException,SQLException,Exception {
        if (CMB2000_AWA_2000.AI_MTH == 'I') {
            IBS.init(SCCGWA, AICUUPIA);
            AICUUPIA.DATA.AC_NO = CMB2000_AWA_2000.MIB_AC;
            AICUUPIA.DATA.CCY = CMB2000_AWA_2000.CCY;
            AICUUPIA.DATA.AMT = CMB2000_AWA_2000.MIB_AMT;
            AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            AICUUPIA.DATA.POST_NARR = CMB2000_AWA_2000.TXN_NARR;
            AICUUPIA.DATA.DESC = CMB2000_AWA_2000.REMARK;
            AICUUPIA.DATA.EVENT_CODE = "CR";
            S000_CALL_AIZUUPIA();
            if (pgmRtn) return;
        }
        if (CMB2000_AWA_2000.AI_MTH == 'E') {
            IBS.init(SCCGWA, BPCPOEWA);
            BPCPOEWA.DATA.CNTR_TYPE = CMB2000_AWA_2000.PRDMO_CD;
            BPCPOEWA.DATA.PROD_CODE = CMB2000_AWA_2000.PROD_CD;
            BPCPOEWA.DATA.EVENT_CODE = CMB2000_AWA_2000.EVENT_CD;
            BPCPOEWA.DATA.OTH = CMB2000_AWA_2000.PRDGL_CD;
            BPCPOEWA.DATA.BR_OLD = CMB2000_AWA_2000.ACCT_BR1;
            BPCPOEWA.DATA.BR_NEW = CMB2000_AWA_2000.ACCT_BR2;
            BPCPOEWA.DATA.BR_3 = CMB2000_AWA_2000.ACCT_BR3;
            BPCPOEWA.DATA.BR_4 = CMB2000_AWA_2000.ACCT_BR4;
            BPCPOEWA.DATA.BR_5 = CMB2000_AWA_2000.ACCT_BR5;
            BPCPOEWA.DATA.CCY_INFO[1-1].CCY = CMB2000_AWA_2000.CCY;
            BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            if (CMB2000_AWA_2000.AMT_NO01 != 0 
                && CMB2000_AWA_2000.AMT_VAL1 != 0) {
                WS_I = CMB2000_AWA_2000.AMT_NO01;
                CEP.TRC(SCCGWA, WS_I);
                BPCPOEWA.DATA.AMT_INFO[WS_I-1].AMT = CMB2000_AWA_2000.AMT_VAL1;
            }
            if (CMB2000_AWA_2000.AMT_NO02 != 0 
                && CMB2000_AWA_2000.AMT_VAL2 != 0) {
                WS_I = CMB2000_AWA_2000.AMT_NO02;
                CEP.TRC(SCCGWA, WS_I);
                BPCPOEWA.DATA.AMT_INFO[WS_I-1].AMT = CMB2000_AWA_2000.AMT_VAL2;
            }
            if (CMB2000_AWA_2000.AMT_NO03 != 0 
                && CMB2000_AWA_2000.AMT_VAL3 != 0) {
                WS_I = CMB2000_AWA_2000.AMT_NO03;
                CEP.TRC(SCCGWA, WS_I);
                BPCPOEWA.DATA.AMT_INFO[WS_I-1].AMT = CMB2000_AWA_2000.AMT_VAL3;
            }
            if (CMB2000_AWA_2000.AMT_NO04 != 0 
                && CMB2000_AWA_2000.AMT_VAL4 != 0) {
                WS_I = CMB2000_AWA_2000.AMT_NO04;
                CEP.TRC(SCCGWA, WS_I);
                BPCPOEWA.DATA.AMT_INFO[WS_I-1].AMT = CMB2000_AWA_2000.AMT_VAL4;
            }
            if (CMB2000_AWA_2000.AMT_NO05 != 0 
                && CMB2000_AWA_2000.AMT_VAL5 != 0) {
                WS_I = CMB2000_AWA_2000.AMT_NO05;
                CEP.TRC(SCCGWA, WS_I);
                BPCPOEWA.DATA.AMT_INFO[WS_I-1].AMT = CMB2000_AWA_2000.AMT_VAL5;
            }
            BPCPOEWA.DATA.POST_NARR = CMB2000_AWA_2000.TXN_NARR;
            BPCPOEWA.DATA.DESC = CMB2000_AWA_2000.REMARK;
            S000_CALL_BPZPOEWA();
            if (pgmRtn) return;
        }
    }
    public void B050_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "35200";
        SCCFMT.ODM_FLG = 'Y';
        SCCFMT.DATA_PTR = CMCF200;
        SCCFMT.DATA_LEN = 32;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_AC_TYPE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCPACTY);
        DCCPACTY.INPUT.AC = CMB2000_AWA_2000.DD_AC;
        S000_CALL_DCZPACTY();
        if (pgmRtn) return;
