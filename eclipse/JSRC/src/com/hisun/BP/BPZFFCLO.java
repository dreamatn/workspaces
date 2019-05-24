package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFFCLO {
    DBParm BPTFEAG_RD;
    DBParm BPTFADT_RD;
    brParm BPTFADT_BR = new brParm();
    String K_OUTPUT_FMT_X = "BPX01";
    String CPN_R_FPDT = "BP-F-R-FE-UNCHG-DTL";
    String CPN_F_F_TX_INFO = "BP-F-F-TX-INFO      ";
    String CPN_F_F_CON_CHG_INFO = "BP-F-F-CON-CHG-INFO ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRFADT BPRFADT = new BPRFADT();
    BPRFEAG BPRFEAG = new BPRFEAG();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPCFFCON BPCFFCON = new BPCFFCON();
    CICACCU CICACCU = new CICACCU();
    String WS_ERR_MSG = " ";
    short WS_ITM = 0;
    BPZFFCLO_WS_TX_INQUIRY WS_TX_INQUIRY = new BPZFFCLO_WS_TX_INQUIRY();
    BPZFFCLO_WS_BR_ARRAY[] WS_BR_ARRAY = new BPZFFCLO_WS_BR_ARRAY[5];
    BPZFFCLO_WS_FEE_ARRAY[] WS_FEE_ARRAY = new BPZFFCLO_WS_FEE_ARRAY[5];
    String WS_CHG_AC = " ";
    String WS_CHG_CCY = " ";
    String WS_CHG_CI = " ";
    String WS_HLD_NO = " ";
    int WS_I = 0;
    int WS_J = 0;
    int WS_K = 0;
    char WS_BR_FLG = ' ';
    String WS_SGN_AC = " ";
    char WS_CLT_TYP = ' ';
    int WS_CLT_BR = 0;
    String WS_FEE_CD = " ";
    char WS_FDT_TYP = ' ';
    char WS_PRC_STS = ' ';
    int WS_PROC_DT = 0;
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCSFCLO BPCSFCLO;
    BPCGCFEE BPCGCFEE;
    BPCGPFEE BPCGPFEE;
    public BPZFFCLO() {
        for (int i=0;i<5;i++) WS_BR_ARRAY[i] = new BPZFFCLO_WS_BR_ARRAY();
        for (int i=0;i<5;i++) WS_FEE_ARRAY[i] = new BPZFFCLO_WS_FEE_ARRAY();
    }
    public void MP(SCCGWA SCCGWA, BPCSFCLO BPCSFCLO) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSFCLO = BPCSFCLO;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZFFCLO return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRFADT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSFCLO.FUNC);
        if (BPCSFCLO.FUNC == '0') {
            B100_CHECK_INPUT();
            B200_CLT_INQ_PROCESS();
            B500_DATA_OUTPUT();
        } else if (BPCSFCLO.FUNC == '1') {
            B100_CHECK_INPUT();
            B300_CLT_CHG_PROCESS();
            B500_DATA_OUTPUT();
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFEAG);
        WS_SGN_AC = BPCSFCLO.CMMT_NO;
        WS_CLT_TYP = '1';
        WS_PROC_DT = BPCSFCLO.PROC_DT;
        CEP.TRC(SCCGWA, WS_SGN_AC);
        CEP.TRC(SCCGWA, WS_PROC_DT);
        T000_READ_FIRST_BPTFEAG_01();
        if (BPCSFCLO.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FE_CLT_NOTFND;
            S000_ERR_MSG_PROC();
        }
        WS_FEE_ARRAY[1-1].WS_FEEARR_CD = BPRFEAG.FEE_CODE1;
        WS_FEE_ARRAY[2-1].WS_FEEARR_CD = BPRFEAG.FEE_CODE2;
        WS_FEE_ARRAY[3-1].WS_FEEARR_CD = BPRFEAG.FEE_CODE3;
        WS_FEE_ARRAY[4-1].WS_FEEARR_CD = BPRFEAG.FEE_CODE4;
        WS_FEE_ARRAY[5-1].WS_FEEARR_CD = BPRFEAG.FEE_CODE5;
        WS_CHG_AC = BPRFEAG.CHG_AC;
        WS_CHG_CCY = BPRFEAG.CCY;
        WS_HLD_NO = BPRFEAG.HLD_NO;
        CEP.TRC(SCCGWA, WS_HLD_NO);
        CEP.TRC(SCCGWA, BPRFEAG.CHG_AC);
        CEP.TRC(SCCGWA, BPRFEAG.FEE_CODE1);
        CEP.TRC(SCCGWA, BPRFEAG.FEE_CODE2);
        CEP.TRC(SCCGWA, BPRFEAG.FEE_CODE3);
        CEP.TRC(SCCGWA, BPRFEAG.FEE_CODE4);
        CEP.TRC(SCCGWA, BPRFEAG.FEE_CODE5);
        if (WS_CHG_AC.trim().length() > 0) {
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.ENTY_TYP = '1';
            CICACCU.DATA.AGR_NO = WS_CHG_AC;
            S000_CALL_CIZACCU();
            CEP.TRC(SCCGWA, CICACCU.RC.RC_CODE);
            if (CICACCU.RC.RC_CODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
                S000_ERR_MSG_PROC();
            } else {
                WS_TX_INQUIRY.WSI_AC_NAME = CICACCU.DATA.AC_CNM;
                CEP.TRC(SCCGWA, WS_TX_INQUIRY.WSI_AC_NAME);
                WS_CHG_CI = CICACCU.DATA.CI_NO;
            }
        }
        if (BPCSFCLO.FUNC == '1') {
            if (BPCSFCLO.CLT_LOOP[1-1].CLT_BR1 == ' ' 
                || (BPCSFCLO.CLT_LOOP[1-1].F1_AMT1 <= 0 
                && BPCSFCLO.CLT_LOOP[1-1].F2_AMT1 <= 0 
                && BPCSFCLO.CLT_LOOP[1-1].F3_AMT1 <= 0)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FE_CLT_AMT_0;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B200_CLT_INQ_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFADT);
        WS_SGN_AC = BPCSFCLO.CMMT_NO;
        WS_FDT_TYP = '1';
        WS_PROC_DT = BPCSFCLO.PROC_DT;
        WS_PRC_STS = '0';
        CEP.TRC(SCCGWA, WS_SGN_AC);
        CEP.TRC(SCCGWA, WS_FDT_TYP);
        CEP.TRC(SCCGWA, WS_PROC_DT);
        CEP.TRC(SCCGWA, WS_PRC_STS);
        T000_STARTBR_BPTFADT_01();
        T000_READNEXT_BPTFADT();
        WS_I = 1;
        CEP.TRC(SCCGWA, BPCSFCLO.RETURN_INFO);
        if (BPCSFCLO.RETURN_INFO == 'F') {
            WS_BR_ARRAY[WS_I-1].WS_BR = BPRFADT.CHG_BR;
            CEP.TRC(SCCGWA, WS_BR_ARRAY[WS_I-1].WS_BR);
        }
        while (WS_I <= 5 
            && BPCSFCLO.RETURN_INFO != 'N') {
            WS_BR_FLG = 'N';
            for (WS_J = 1; WS_J <= WS_I; WS_J += 1) {
                if (WS_BR_ARRAY[WS_J-1].WS_BR == BPRFADT.CHG_BR) {
                    WS_BR_FLG = 'Y';
                }
            }
            if (WS_BR_FLG == 'N') {
                WS_I = WS_I + 1;
                WS_BR_ARRAY[WS_I-1].WS_BR = BPRFADT.CHG_BR;
                CEP.TRC(SCCGWA, WS_BR_ARRAY[WS_I-1].WS_BR);
            }
            T000_READNEXT_BPTFADT();
        }
        T000_ENDBR_BPTFADT();
        for (WS_J = 1; WS_J <= WS_I 
            && WS_BR_ARRAY[WS_J-1].WS_BR != ' '; WS_J += 1) {
            if (WS_BR_ARRAY[WS_J-1].WS_BR != ' ') {
                IBS.init(SCCGWA, BPRFADT);
                WS_SGN_AC = BPCSFCLO.CMMT_NO;
                WS_CLT_BR = WS_BR_ARRAY[WS_J-1].WS_BR;
                BPCSFCLO.CLT_LOOP[WS_J-1].CLT_BR1 = WS_BR_ARRAY[WS_J-1].WS_BR;
                WS_FDT_TYP = '1';
                WS_PROC_DT = BPCSFCLO.PROC_DT;
                WS_PRC_STS = '0';
                for (WS_K = 1; WS_K <= 3 
                    && WS_FEE_ARRAY[WS_K-1].WS_FEEARR_CD.trim().length() != 0; WS_K += 1) {
                    WS_FEE_CD = WS_FEE_ARRAY[WS_K-1].WS_FEEARR_CD;
                    CEP.TRC(SCCGWA, WS_SGN_AC);
                    CEP.TRC(SCCGWA, WS_CLT_BR);
                    CEP.TRC(SCCGWA, WS_PROC_DT);
                    CEP.TRC(SCCGWA, WS_FEE_CD);
                    T000_STARTBR_BPTFADT_02();
                    T000_READNEXT_BPTFADT();
                    while (BPCSFCLO.RETURN_INFO != 'N') {
                        if (WS_K == 1) {
                            BPCSFCLO.CLT_LOOP[WS_K-1].FEE_CD1 = WS_FEE_ARRAY[WS_K-1].WS_FEEARR_CD;
                            BPCSFCLO.CLT_LOOP[WS_J-1].F1_CNT1 = BPCSFCLO.CLT_LOOP[WS_J-1].F1_CNT1 + 1;
                            BPCSFCLO.CLT_LOOP[WS_J-1].F1_AMT1 = BPCSFCLO.CLT_LOOP[WS_J-1].F1_AMT1 + BPRFADT.CHG_AMT;
                        } else if (WS_K == 2) {
                            BPCSFCLO.CLT_LOOP[WS_K-1].FEE_CD2 = WS_FEE_ARRAY[WS_K-1].WS_FEEARR_CD;
                            BPCSFCLO.CLT_LOOP[WS_J-1].F2_CNT1 = BPCSFCLO.CLT_LOOP[WS_J-1].F2_CNT1 + 1;
                            BPCSFCLO.CLT_LOOP[WS_J-1].F2_AMT1 = BPCSFCLO.CLT_LOOP[WS_J-1].F2_AMT1 + BPRFADT.CHG_AMT;
                        } else if (WS_K == 3) {
                            BPCSFCLO.CLT_LOOP[WS_K-1].FEE_CD3 = WS_FEE_ARRAY[WS_K-1].WS_FEEARR_CD;
                            BPCSFCLO.CLT_LOOP[WS_J-1].F3_CNT1 = BPCSFCLO.CLT_LOOP[WS_J-1].F3_CNT1 + 1;
                            BPCSFCLO.CLT_LOOP[WS_J-1].F3_AMT1 = BPCSFCLO.CLT_LOOP[WS_J-1].F3_AMT1 + BPRFADT.CHG_AMT;
                        } else {
                        }
                        CEP.TRC(SCCGWA, BPCSFCLO.CLT_LOOP[WS_J-1].F1_CNT1);
                        CEP.TRC(SCCGWA, BPCSFCLO.CLT_LOOP[WS_J-1].F1_AMT1);
                        CEP.TRC(SCCGWA, BPCSFCLO.CLT_LOOP[WS_J-1].F2_CNT1);
                        CEP.TRC(SCCGWA, BPCSFCLO.CLT_LOOP[WS_J-1].F2_AMT1);
                        CEP.TRC(SCCGWA, BPCSFCLO.CLT_LOOP[WS_J-1].F1_CNT1);
                        CEP.TRC(SCCGWA, BPCSFCLO.CLT_LOOP[WS_J-1].F3_AMT1);
                        T000_READNEXT_BPTFADT();
                    }
                    T000_ENDBR_BPTFADT();
                }
            }
        }
    }
    public void B300_CLT_CHG_PROCESS() throws IOException,SQLException,Exception {
        C310_SET_FEE_INFO();
        C320_CLT_PAY_PROC();
    }
    public void C310_SET_FEE_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '0';
        BPCFFTXI.TX_DATA.FEE_CTRT = BPCSFCLO.CMMT_NO;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = WS_CHG_AC;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = WS_CHG_CCY;
        BPCFFTXI.TX_DATA.CI_NO = WS_CHG_CI;
        BPCFFTXI.TX_DATA.HLD_NO = WS_HLD_NO;
        BPCFFTXI.TX_DATA.PROC_DT = BPCSFCLO.PROC_DT;
        BPCFFTXI.TX_DATA.CCY_TYPE = '1';
        BPCFFTXI.TX_DATA.CLT_TYPE = '1';
        BPCFFTXI.TX_DATA.DRMCR_FLG = 'Y';
        S000_CALL_BPZFFTXI();
    }
    public void C320_CLT_PAY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFCON);
        for (WS_K = 1; WS_K <= 5 
            && BPCSFCLO.CLT_LOOP[WS_K-1].CLT_BR1 != ' '; WS_K += 1) {
            CEP.TRC(SCCGWA, BPCSFCLO.CLT_LOOP[WS_K-1].CLT_BR1);
            CEP.TRC(SCCGWA, BPCSFCLO.CLT_LOOP[WS_K-1].FEE_CD1);
            CEP.TRC(SCCGWA, BPCSFCLO.CLT_LOOP[WS_K-1].FEE_CD2);
            CEP.TRC(SCCGWA, BPCSFCLO.CLT_LOOP[WS_K-1].FEE_CD3);
            CEP.TRC(SCCGWA, BPCSFCLO.CLT_LOOP[WS_K-1].F1_CNT1);
            CEP.TRC(SCCGWA, BPCSFCLO.CLT_LOOP[WS_K-1].F2_CNT1);
            CEP.TRC(SCCGWA, BPCSFCLO.CLT_LOOP[WS_K-1].F3_CNT1);
            CEP.TRC(SCCGWA, BPCSFCLO.CLT_LOOP[WS_K-1].F1_AMT1);
            CEP.TRC(SCCGWA, BPCSFCLO.CLT_LOOP[WS_K-1].F2_AMT1);
            CEP.TRC(SCCGWA, BPCSFCLO.CLT_LOOP[WS_K-1].F3_AMT1);
            if (BPCSFCLO.CLT_LOOP[WS_K-1].F1_AMT1 > 0) {
                BPCFFCON.FEE_INFO.FEE_CNT += 1;
                WS_ITM = BPCFFCON.FEE_INFO.FEE_CNT;
                BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].CHG_FLG = '0';
                BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].FEE_CODE = BPCSFCLO.CLT_LOOP[WS_K-1].FEE_CD1;
                BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].TO_ACCT_CEN = BPCSFCLO.CLT_LOOP[WS_K-1].CLT_BR1;
                BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].CHG_AC_TY = '0';
                BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].CHG_AC = WS_CHG_AC;
                BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].FEE_CCY = WS_CHG_CCY;
                BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].FEE_BAS = BPCSFCLO.CLT_LOOP[WS_K-1].F1_AMT1;
                BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].FEE_AMT = BPCSFCLO.CLT_LOOP[WS_K-1].F1_AMT1;
                BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].CHG_CCY = WS_CHG_CCY;
                BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].CHG_BAS = BPCSFCLO.CLT_LOOP[WS_K-1].F1_AMT1;
                BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].CHG_AMT = BPCSFCLO.CLT_LOOP[WS_K-1].F1_AMT1;
                BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].ADJ_AMT = BPCSFCLO.CLT_LOOP[WS_K-1].F1_AMT1;
                B321_GET_AMO_DATA();
            }
            if (BPCSFCLO.CLT_LOOP[WS_K-1].F2_AMT1 > 0) {
                BPCFFCON.FEE_INFO.FEE_CNT += 1;
                WS_ITM = BPCFFCON.FEE_INFO.FEE_CNT;
                BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].CHG_FLG = '0';
                BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].FEE_CODE = BPCSFCLO.CLT_LOOP[WS_K-1].FEE_CD2;
                BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].TO_ACCT_CEN = BPCSFCLO.CLT_LOOP[WS_K-1].CLT_BR1;
                BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].CHG_AC_TY = '0';
                BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].CHG_AC = WS_CHG_AC;
                BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].FEE_CCY = WS_CHG_CCY;
                BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].FEE_BAS = BPCSFCLO.CLT_LOOP[WS_K-1].F2_AMT1;
                BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].FEE_AMT = BPCSFCLO.CLT_LOOP[WS_K-1].F2_AMT1;
                BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].CHG_CCY = WS_CHG_CCY;
                BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].CHG_BAS = BPCSFCLO.CLT_LOOP[WS_K-1].F2_AMT1;
                BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].CHG_AMT = BPCSFCLO.CLT_LOOP[WS_K-1].F2_AMT1;
                BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].ADJ_AMT = BPCSFCLO.CLT_LOOP[WS_K-1].F2_AMT1;
                B321_GET_AMO_DATA();
            }
            if (BPCSFCLO.CLT_LOOP[WS_K-1].F3_AMT1 > 0) {
                BPCFFCON.FEE_INFO.FEE_CNT += 1;
                WS_ITM = BPCFFCON.FEE_INFO.FEE_CNT;
                BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].CHG_FLG = '0';
                BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].FEE_CODE = BPCSFCLO.CLT_LOOP[WS_K-1].FEE_CD3;
                BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].TO_ACCT_CEN = BPCSFCLO.CLT_LOOP[WS_K-1].CLT_BR1;
                BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].CHG_AC_TY = '0';
                BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].CHG_AC = WS_CHG_AC;
                BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].FEE_CCY = WS_CHG_CCY;
                BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].FEE_BAS = BPCSFCLO.CLT_LOOP[WS_K-1].F3_AMT1;
                BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].FEE_AMT = BPCSFCLO.CLT_LOOP[WS_K-1].F3_AMT1;
                BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].CHG_CCY = WS_CHG_CCY;
                BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].CHG_BAS = BPCSFCLO.CLT_LOOP[WS_K-1].F3_AMT1;
                BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].CHG_AMT = BPCSFCLO.CLT_LOOP[WS_K-1].F3_AMT1;
                BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].ADJ_AMT = BPCSFCLO.CLT_LOOP[WS_K-1].F3_AMT1;
                B321_GET_AMO_DATA();
            }
        }
        S000_CALL_BPZFFCON();
    }
    public void B321_GET_AMO_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFADT);
        BPRFADT.CMMT_NO = BPCSFCLO.CMMT_NO;
        CEP.TRC(SCCGWA, BPRFADT.CMMT_NO);
        BPRFADT.CHG_AC = BPCSFCLO.AC;
        CEP.TRC(SCCGWA, BPRFADT.CHG_AC);
        BPRFADT.FEE_CODE = BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].FEE_CODE;
        CEP.TRC(SCCGWA, BPRFADT.FEE_CODE);
        T000_READ_FIRST_BPTFADT_FORAMO();
        CEP.TRC(SCCGWA, BPRFADT.AMO_FLG);
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].AMO_FLG = BPRFADT.AMO_FLG;
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].AMO_FLG);
        if (BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].AMO_FLG == 'Y') {
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].AMO_STDT = SCCGWA.COMM_AREA.AC_DATE;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].AMO_EDDT = SCCGWA.COMM_AREA.AC_DATE;
        }
    }
    public void B500_DATA_OUTPUT() throws IOException,SQLException,Exception {
        R510_TRANS_DATA_OUPUT();
        CEP.TRC(SCCGWA, WS_TX_INQUIRY.WSI_AC_NAME);
        CEP.TRC(SCCGWA, WS_TX_INQUIRY);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_X;
        SCCFMT.DATA_PTR = WS_TX_INQUIRY;
        SCCFMT.DATA_LEN = 918;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R510_TRANS_DATA_OUPUT() throws IOException,SQLException,Exception {
        WS_TX_INQUIRY.WSI_CMMT_NO = BPCSFCLO.CMMT_NO;
        WS_TX_INQUIRY.WSI_AC = BPCSFCLO.AC;
        WS_TX_INQUIRY.WSI_PROC_DT = BPCSFCLO.PROC_DT;
        for (WS_K = 1; WS_K <= 5 
            && BPCSFCLO.CLT_LOOP[WS_K-1].CLT_BR1 != ' '; WS_K += 1) {
            WS_TX_INQUIRY.WSI_CLT_LOOP[WS_K-1].WSI_CLT_BR1 = BPCSFCLO.CLT_LOOP[WS_K-1].CLT_BR1;
            WS_TX_INQUIRY.WSI_CLT_LOOP[WS_K-1].WSI_FEE_CD1 = BPCSFCLO.CLT_LOOP[1-1].FEE_CD1;
            WS_TX_INQUIRY.WSI_CLT_LOOP[WS_K-1].WSI_F1_CNT1 = BPCSFCLO.CLT_LOOP[WS_K-1].F1_CNT1;
            WS_TX_INQUIRY.WSI_CLT_LOOP[WS_K-1].WSI_F1_AMT1 = BPCSFCLO.CLT_LOOP[WS_K-1].F1_AMT1;
            WS_TX_INQUIRY.WSI_CLT_LOOP[WS_K-1].WSI_FEE_CD2 = BPCSFCLO.CLT_LOOP[2-1].FEE_CD2;
            WS_TX_INQUIRY.WSI_CLT_LOOP[WS_K-1].WSI_F2_CNT1 = BPCSFCLO.CLT_LOOP[WS_K-1].F2_CNT1;
            WS_TX_INQUIRY.WSI_CLT_LOOP[WS_K-1].WSI_F2_AMT1 = BPCSFCLO.CLT_LOOP[WS_K-1].F2_AMT1;
            WS_TX_INQUIRY.WSI_CLT_LOOP[WS_K-1].WSI_FEE_CD3 = BPCSFCLO.CLT_LOOP[3-1].FEE_CD3;
            WS_TX_INQUIRY.WSI_CLT_LOOP[WS_K-1].WSI_F3_CNT1 = BPCSFCLO.CLT_LOOP[WS_K-1].F3_CNT1;
            WS_TX_INQUIRY.WSI_CLT_LOOP[WS_K-1].WSI_F3_AMT1 = BPCSFCLO.CLT_LOOP[WS_K-1].F3_AMT1;
            CEP.TRC(SCCGWA, WS_TX_INQUIRY.WSI_CLT_LOOP[WS_K-1].WSI_CLT_BR1);
            CEP.TRC(SCCGWA, WS_TX_INQUIRY.WSI_CLT_LOOP[WS_K-1].WSI_FEE_CD1);
            CEP.TRC(SCCGWA, WS_TX_INQUIRY.WSI_CLT_LOOP[WS_K-1].WSI_F1_CNT1);
            CEP.TRC(SCCGWA, WS_TX_INQUIRY.WSI_CLT_LOOP[WS_K-1].WSI_F1_AMT1);
            CEP.TRC(SCCGWA, WS_TX_INQUIRY.WSI_CLT_LOOP[WS_K-1].WSI_FEE_CD2);
            CEP.TRC(SCCGWA, WS_TX_INQUIRY.WSI_CLT_LOOP[WS_K-1].WSI_F2_CNT1);
            CEP.TRC(SCCGWA, WS_TX_INQUIRY.WSI_CLT_LOOP[WS_K-1].WSI_F2_AMT1);
            CEP.TRC(SCCGWA, WS_TX_INQUIRY.WSI_CLT_LOOP[WS_K-1].WSI_FEE_CD3);
            CEP.TRC(SCCGWA, WS_TX_INQUIRY.WSI_CLT_LOOP[WS_K-1].WSI_F3_CNT1);
            CEP.TRC(SCCGWA, WS_TX_INQUIRY.WSI_CLT_LOOP[WS_K-1].WSI_F3_AMT1);
        }
    }
    public void S000_CALL_BPZFFTXI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_F_TX_INFO, BPCFFTXI);
        if (BPCFFTXI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFTXI.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFFCON() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_F_CON_CHG_INFO, BPCFFCON);
        if (BPCFFCON.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFCON.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU         ", CICACCU);
    }
    public void T000_READ_FIRST_BPTFEAG_01() throws IOException,SQLException,Exception {
        BPTFEAG_RD = new DBParm();
        BPTFEAG_RD.TableName = "BPTFEAG";
        BPTFEAG_RD.where = "CLT_AC = :WS_SGN_AC "
            + "AND CLT_TYPE = :WS_CLT_TYP "
            + "AND EFF_DATE <= :WS_PROC_DT "
            + "AND EXP_DATE >= :WS_PROC_DT";
        BPTFEAG_RD.fst = true;
        BPTFEAG_RD.order = "CLT_AC,CLT_TYPE";
        IBS.READ(SCCGWA, BPRFEAG, this, BPTFEAG_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCSFCLO.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCSFCLO.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READ_FIRST_BPTFADT_FORAMO() throws IOException,SQLException,Exception {
        BPTFADT_RD = new DBParm();
        BPTFADT_RD.TableName = "BPTFADT";
        BPTFADT_RD.where = "CHG_AC = :BPRFADT.CHG_AC "
            + "AND FEE_CODE = :BPRFADT.FEE_CODE "
            + "AND CMMT_NO = :BPRFADT.CMMT_NO "
            + "AND FEE_SRC = '1' "
            + "AND ( CHG_STS = '0' "
            + "OR CHG_STS = '1' )";
        BPTFADT_RD.fst = true;
        IBS.READ(SCCGWA, BPRFADT, this, BPTFADT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCSFCLO.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCSFCLO.RETURN_INFO = 'N';
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FE_PRC_STS_ERR;
            S000_ERR_MSG_PROC();
        } else {
        }
    }
    public void T000_STARTBR_BPTFADT_01() throws IOException,SQLException,Exception {
        BPTFADT_BR.rp = new DBParm();
        BPTFADT_BR.rp.TableName = "BPTFADT";
        BPTFADT_BR.rp.where = "CMMT_NO = :WS_SGN_AC "
            + "AND FEE_SRC = :WS_FDT_TYP "
            + "AND PRC_STS = :WS_PRC_STS "
            + "AND ( CHG_STS = '0' "
            + "OR CHG_STS = '1' ) "
            + "AND TRT_DT = :WS_PROC_DT";
        BPTFADT_BR.rp.order = "CMMT_NO";
        IBS.STARTBR(SCCGWA, BPRFADT, this, BPTFADT_BR);
    }
    public void T000_STARTBR_BPTFADT_02() throws IOException,SQLException,Exception {
        BPTFADT_BR.rp = new DBParm();
        BPTFADT_BR.rp.TableName = "BPTFADT";
        BPTFADT_BR.rp.where = "CMMT_NO = :WS_SGN_AC "
            + "AND CHG_BR = :WS_CLT_BR "
            + "AND FEE_SRC = :WS_FDT_TYP "
            + "AND FEE_CODE = :WS_FEE_CD "
            + "AND PRC_STS = :WS_PRC_STS "
            + "AND ( CHG_STS = '0' "
            + "OR CHG_STS = '1' ) "
            + "AND TRT_DT = :WS_PROC_DT";
        BPTFADT_BR.rp.order = "CMMT_NO";
        IBS.STARTBR(SCCGWA, BPRFADT, this, BPTFADT_BR);
    }
    public void T000_READNEXT_BPTFADT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRFADT, this, BPTFADT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCSFCLO.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCSFCLO.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTFADT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTFADT_BR);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCSFCLO.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCSFCLO = ");
            CEP.TRC(SCCGWA, BPCSFCLO);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
