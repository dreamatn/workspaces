package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSDNCS {
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTTHIS_RD;
    String CPN_U_CASH_IN = "BP-U-CASH-IN";
    String CPN_U_CASH_OUT = "BP-U-CASH-OUT";
    String CPN_P_ADD_CASH_HIS = "BP-P-ADD-CHIS";
    String CPN_R_HISF = "BP-R-ADW-THIS    ";
    String CPN_R_BRE_CLIB = "BP-R-BRE-CLIB       ";
    String CPN_P_TLAM = "BP-F-TLR-ACCU-MGM ";
    String CPN_R_ADW_TLVB = "BP-R-ADW-TLVB";
    int K_MAX_CCY_CNT = 5;
    int K_MAX_PAR_CNT = 12;
    char K_FILLER_X01 = 0X01;
    String WS_ERR_MSG = " ";
    int WS_CCY_CNT = 0;
    int WS_PAR_CNT = 0;
    int WS_CNT = 0;
    int WS_CNT1 = 0;
    int WS_INFO_CNT = 0;
    int WS_START_CNT = 0;
    int WS_SEQ = 0;
    char WS_CASH_FLG = ' ';
    BPZSDNCS_WS_CRNT_TLR[] WS_CRNT_TLR = new BPZSDNCS_WS_CRNT_TLR[5];
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCUCSIN BPCUCSIN = new BPCUCSIN();
    BPCUCSTO BPCUCSTO = new BPCUCSTO();
    BPCTLVBF BPCTLVBF = new BPCTLVBF();
    BPCSNCSO BPCSNCSO = new BPCSNCSO();
    BPCPCHIS BPCPCHIS = new BPCPCHIS();
    BPCFTLAM BPCFTLAM = new BPCFTLAM();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPRCLIB BPRCLIB = new BPRCLIB();
    BPRTHIS BPRTHIS = new BPRTHIS();
    BPCTHISF BPCTHISF = new BPCTHISF();
    BPCTLIBB BPCTLIBB = new BPCTLIBB();
    SCCGWA SCCGWA;
    BPCSDNCS BPCSDNCS;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public BPZSDNCS() {
        for (int i=0;i<5;i++) WS_CRNT_TLR[i] = new BPZSDNCS_WS_CRNT_TLR();
    }
    public void MP(SCCGWA SCCGWA, BPCSDNCS BPCSDNCS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSDNCS = BPCSDNCS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSDNCS return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSDNCS.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            if (BPCSDNCS.FUNC == '0' 
                || BPCSDNCS.FUNC == '1' 
                || BPCSDNCS.FUNC == '2' 
                || BPCSDNCS.FUNC == '3') {
                B010_CHECK_INPUT();
                B020_PROCESS_CASH_FOR_CN();
                B030_TRANS_DATA_OUTPUT();
            }
            if (BPCSDNCS.FUNC == '4' 
                || BPCSDNCS.FUNC == '5') {
                B040_PROCESS_BOX_FOR_CN();
                B050_TRANS_BOX_DATA_OUTPUT();
            }
        } else {
            if (BPCSDNCS.FUNC == '0' 
                || BPCSDNCS.FUNC == '1' 
                || BPCSDNCS.FUNC == '2' 
                || BPCSDNCS.FUNC == '3') {
                B010_CHECK_INPUT();
                B020_PROCESS_CASH();
                B030_TRANS_DATA_OUTPUT();
            }
            if (BPCSDNCS.FUNC == '4' 
                || BPCSDNCS.FUNC == '5') {
                B040_PROCESS_BOX();
                B050_TRANS_BOX_DATA_OUTPUT();
            }
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSDNCS.FUNC);
        CEP.TRC(SCCGWA, BPCSDNCS.FROM_BR);
        CEP.TRC(SCCGWA, BPCSDNCS.FROM_TLR);
        CEP.TRC(SCCGWA, BPCSDNCS.TO_BR);
        CEP.TRC(SCCGWA, BPCSDNCS.TO_TLR);
        CEP.TRC(SCCGWA, BPCSDNCS.CCY_INFO[1-1].CCY);
        CEP.TRC(SCCGWA, BPCSDNCS.CCY_INFO[1-1].CCY_AMT);
        CEP.TRC(SCCGWA, BPCSDNCS.CCY_INFO[1-1].CASH_TYP);
        CEP.TRC(SCCGWA, BPCSDNCS.CS_KIND);
        if (BPCSDNCS.FUNC != '0' 
            && BPCSDNCS.FUNC != '1' 
            && BPCSDNCS.FUNC != '2' 
            && BPCSDNCS.FUNC != '3' 
            || BPCSDNCS.FROM_BR == 0 
            && BPCSDNCS.FROM_TLR.trim().length() == 0 
            || BPCSDNCS.TO_BR == 0 
            && BPCSDNCS.TO_TLR.trim().length() == 0 
            || BPCSDNCS.CS_KIND != '0' 
            && BPCSDNCS.CS_KIND != '1' 
            && BPCSDNCS.CS_KIND != '2' 
            && BPCSDNCS.CS_KIND != '3' 
            || BPCSDNCS.CCY_INFO[1-1].CCY.trim().length() == 0 
            || BPCSDNCS.CCY_INFO[1-1].CCY_AMT == 0 
            || BPCSDNCS.CCY_INFO[1-1].CASH_TYP.trim().length() == 0) {
            CEP.TRC(SCCGWA, "DEV");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
        if (BPCSDNCS.CS_KIND == '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FALSE_CCY_RESTRICTED;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_PROCESS_CASH_FOR_CN() throws IOException,SQLException,Exception {
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= K_MAX_CCY_CNT; WS_CCY_CNT += 1) {
            if (BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                CEP.TRC(SCCGWA, BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY);
                IBS.init(SCCGWA, BPCUCSTO);
                if (BPCSDNCS.FUNC == '1' 
                    || BPCSDNCS.FUNC == '0') {
                    BPCUCSTO.CASH_STAT = '1';
                    BPCUCSTO.VB_BR = BPCSDNCS.FROM_BR;
                    BPCUCSTO.VB_TLR = BPCSDNCS.FROM_TLR;
                    BPCUCSTO.VB_FLG = '1';
                } else {
                    BPCUCSTO.PLBOX_TYP = BPCSDNCS.PLBOX_TP1;
                    BPCUCSTO.CASH_STAT = '0';
                    BPCUCSTO.VB_BR = BPCSDNCS.FROM_BR;
                    BPCUCSTO.VB_TLR = BPCSDNCS.FROM_TLR;
                    BPCUCSTO.VB_FLG = '0';
                }
                if (BPCSDNCS.FUNC == '2') {
                    BPCUCSTO.CS_KIND = '0';
                } else {
                    BPCUCSTO.CS_KIND = BPCSDNCS.CS_KIND;
                }
                BPCUCSTO.CASH_TYP = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
                BPCUCSTO.CCY = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY;
                BPCUCSTO.TX_AMT = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
                for (WS_PAR_CNT = 1; WS_PAR_CNT <= K_MAX_PAR_CNT; WS_PAR_CNT += 1) {
                    BPCUCSTO.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_VAL = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_VAL;
                    BPCUCSTO.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_NUM = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_NUM;
                    BPCUCSTO.PAR_INFO.PAR_REC[WS_PAR_CNT-1].M_FLG = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].M_FLG;
                }
                S000_CALL_BPZUCSTO();
                IBS.init(SCCGWA, BPCPCHIS);
                BPCPCHIS.TLR = BPCSDNCS.FROM_TLR;
                BPCPCHIS.PLBOX_NO = BPCUCSTO.PLBOX_NO;
                BPCPCHIS.CASH_TYP = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
                BPCPCHIS.CCY = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY;
                BPCPCHIS.AMT = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
                if (BPCSDNCS.FUNC == '1' 
                    || BPCSDNCS.FUNC == '0') {
                    BPCPCHIS.VB_FLG = '1';
                } else {
                    BPCPCHIS.VB_FLG = '0';
                }
                BPCPCHIS.IN_OUT = 'C';
                BPCPCHIS.CS_KIND = BPCSDNCS.CS_KIND;
                for (WS_PAR_CNT = 1; WS_PAR_CNT <= K_MAX_PAR_CNT; WS_PAR_CNT += 1) {
                    if (BPCUCSTO.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_VAL > 0 
                        && BPCUCSTO.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_NUM > 0) {
                        BPCPCHIS.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_VAL = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_VAL;
                        BPCPCHIS.PAR_INFO.PAR_REC[WS_PAR_CNT-1].M_FLG = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].M_FLG;
                        BPCPCHIS.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_NUM = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_NUM;
                    }
                }
                S000_CALL_BPZPCHIS();
                IBS.init(SCCGWA, BPCUCSIN);
                if (BPCSDNCS.FUNC == '1' 
                    || BPCSDNCS.FUNC == '2') {
                    BPCUCSIN.CASH_STAT = '1';
                    BPCUCSIN.VB_BR = BPCSDNCS.TO_BR;
                    BPCUCSIN.VB_TLR = BPCSDNCS.TO_TLR;
                    BPCUCSIN.VB_FLG = '1';
                } else {
                    BPCUCSIN.PLBOX_TYP = BPCSDNCS.PLBOX_TP2;
                    BPCUCSIN.CASH_STAT = '0';
                    BPCUCSIN.VB_BR = BPCSDNCS.TO_BR;
                    BPCUCSIN.VB_TLR = BPCSDNCS.TO_TLR;
                    BPCUCSIN.VB_FLG = '0';
                }
                BPCUCSIN.CS_KIND = BPCSDNCS.CS_KIND;
                BPCUCSIN.CASH_TYP = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
                BPCUCSIN.CCY = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY;
                BPCUCSIN.TX_AMT = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
                for (WS_PAR_CNT = 1; WS_PAR_CNT <= K_MAX_PAR_CNT; WS_PAR_CNT += 1) {
                    BPCUCSIN.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_VAL = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_VAL;
                    BPCUCSIN.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_NUM = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_NUM;
                    BPCUCSIN.PAR_INFO.PAR_REC[WS_PAR_CNT-1].M_FLG = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].M_FLG;
                }
                S000_CALL_BPZUCSIN();
                IBS.init(SCCGWA, BPCPCHIS);
                BPCPCHIS.TLR = BPCSDNCS.TO_TLR;
                BPCPCHIS.PLBOX_NO = BPCUCSIN.PLBOX_NO;
                BPCPCHIS.CASH_TYP = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
                BPCPCHIS.CCY = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY;
                BPCPCHIS.AMT = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
                if (BPCSDNCS.FUNC == '1' 
                    || BPCSDNCS.FUNC == '2') {
                    BPCPCHIS.VB_FLG = '1';
                } else {
                    BPCPCHIS.VB_FLG = '0';
                }
                BPCPCHIS.IN_OUT = 'D';
                BPCPCHIS.CS_KIND = BPCSDNCS.CS_KIND;
                CEP.TRC(SCCGWA, BPCPCHIS.CONTR_AC);
                for (WS_PAR_CNT = 1; WS_PAR_CNT <= K_MAX_PAR_CNT; WS_PAR_CNT += 1) {
                    if (BPCUCSTO.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_VAL > 0 
                        && BPCUCSTO.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_NUM > 0) {
                        BPCPCHIS.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_VAL = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_VAL;
                        BPCPCHIS.PAR_INFO.PAR_REC[WS_PAR_CNT-1].M_FLG = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].M_FLG;
                        BPCPCHIS.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_NUM = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_NUM;
                    }
                }
                S000_CALL_BPZPCHIS();
            }
        }
    }
    public void B020_PROCESS_CASH() throws IOException,SQLException,Exception {
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= K_MAX_CCY_CNT; WS_CCY_CNT += 1) {
            if (BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                CEP.TRC(SCCGWA, BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY);
                IBS.init(SCCGWA, BPCUCSTO);
                if (BPCSDNCS.FUNC == '1' 
                    || BPCSDNCS.FUNC == '0') {
                    BPCUCSTO.CASH_STAT = '1';
                    BPCUCSTO.VB_BR = BPCSDNCS.FROM_BR;
                    BPCUCSTO.VB_FLG = '1';
                } else {
                    BPCUCSTO.PLBOX_TYP = '3';
                    BPCUCSTO.CASH_STAT = '0';
                    BPCUCSTO.VB_BR = BPCSDNCS.FROM_BR;
                    BPCUCSTO.VB_TLR = BPCSDNCS.FROM_TLR;
                    BPCUCSTO.VB_FLG = '0';
                }
                BPCUCSTO.CS_KIND = BPCSDNCS.CS_KIND;
                BPCUCSTO.CASH_TYP = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
                BPCUCSTO.CCY = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY;
                BPCUCSTO.TX_AMT = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
                for (WS_PAR_CNT = 1; WS_PAR_CNT <= K_MAX_PAR_CNT; WS_PAR_CNT += 1) {
                    BPCUCSTO.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_VAL = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_VAL;
                    BPCUCSTO.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_NUM = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_NUM;
                    BPCUCSTO.PAR_INFO.PAR_REC[WS_PAR_CNT-1].M_FLG = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].M_FLG;
                }
                S000_CALL_BPZUCSTO();
                IBS.init(SCCGWA, BPCPCHIS);
                BPCPCHIS.TLR = BPCSDNCS.FROM_TLR;
                BPCPCHIS.PLBOX_NO = BPCUCSTO.PLBOX_NO;
                BPCPCHIS.CASH_TYP = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
                BPCPCHIS.CCY = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY;
                BPCPCHIS.AMT = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
                if (BPCSDNCS.FUNC == '1' 
                    || BPCSDNCS.FUNC == '0') {
                    BPCPCHIS.VB_FLG = '1';
                } else {
                    BPCPCHIS.VB_FLG = '0';
                }
                BPCPCHIS.IN_OUT = 'C';
                BPCPCHIS.CS_KIND = BPCSDNCS.CS_KIND;
                for (WS_PAR_CNT = 1; WS_PAR_CNT <= K_MAX_PAR_CNT; WS_PAR_CNT += 1) {
                    if (BPCUCSTO.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_VAL > 0 
                        && BPCUCSTO.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_NUM > 0) {
                        BPCPCHIS.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_VAL = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_VAL;
                        BPCPCHIS.PAR_INFO.PAR_REC[WS_PAR_CNT-1].M_FLG = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].M_FLG;
                        BPCPCHIS.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_NUM = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_NUM;
                    }
                }
                S000_CALL_BPZPCHIS();
                IBS.init(SCCGWA, BPCUCSIN);
                if (BPCSDNCS.FUNC == '1' 
                    || BPCSDNCS.FUNC == '2') {
                    BPCUCSIN.CASH_STAT = '1';
                    BPCUCSIN.VB_BR = BPCSDNCS.TO_BR;
                    BPCUCSIN.VB_FLG = '1';
                } else {
                    BPCUCSIN.PLBOX_TYP = '3';
                    BPCUCSIN.CASH_STAT = '0';
                    BPCUCSIN.VB_BR = BPCSDNCS.TO_BR;
                    BPCUCSIN.VB_TLR = BPCSDNCS.TO_TLR;
                    BPCUCSIN.VB_FLG = '0';
                }
                BPCUCSIN.CS_KIND = BPCSDNCS.CS_KIND;
                BPCUCSIN.CASH_TYP = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
                BPCUCSIN.CCY = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY;
                BPCUCSIN.TX_AMT = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
                for (WS_PAR_CNT = 1; WS_PAR_CNT <= K_MAX_PAR_CNT; WS_PAR_CNT += 1) {
                    BPCUCSIN.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_VAL = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_VAL;
                    BPCUCSIN.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_NUM = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_NUM;
                    BPCUCSIN.PAR_INFO.PAR_REC[WS_PAR_CNT-1].M_FLG = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].M_FLG;
                }
                S000_CALL_BPZUCSIN();
                IBS.init(SCCGWA, BPCPCHIS);
                BPCPCHIS.TLR = BPCSDNCS.TO_TLR;
                BPCPCHIS.PLBOX_NO = BPCUCSIN.PLBOX_NO;
                BPCPCHIS.CASH_TYP = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
                BPCPCHIS.CCY = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY;
                BPCPCHIS.AMT = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
                if (BPCSDNCS.FUNC == '1' 
                    || BPCSDNCS.FUNC == '2') {
                    BPCPCHIS.VB_FLG = '1';
                } else {
                    BPCPCHIS.VB_FLG = '0';
                }
                BPCPCHIS.IN_OUT = 'D';
                BPCPCHIS.CS_KIND = BPCSDNCS.CS_KIND;
                CEP.TRC(SCCGWA, BPCPCHIS.CONTR_AC);
                for (WS_PAR_CNT = 1; WS_PAR_CNT <= K_MAX_PAR_CNT; WS_PAR_CNT += 1) {
                    if (BPCUCSTO.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_VAL > 0 
                        && BPCUCSTO.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_NUM > 0) {
                        BPCPCHIS.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_VAL = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_VAL;
                        BPCPCHIS.PAR_INFO.PAR_REC[WS_PAR_CNT-1].M_FLG = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].M_FLG;
                        BPCPCHIS.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_NUM = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_NUM;
                    }
                }
                S000_CALL_BPZPCHIS();
                if (BPCSDNCS.FUNC == '0' 
                    || BPCSDNCS.FUNC == '2') {
                    IBS.init(SCCGWA, BPCFTLAM);
                    if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                        BPCFTLAM.OP_CODE = 'D';
                    } else {
                        BPCFTLAM.OP_CODE = 'A';
                    }
                    if (BPCSDNCS.FUNC == '0') {
                        BPCFTLAM.ACCU_TYP = "01";
                        BPCFTLAM.TLR = BPCSDNCS.TO_TLR;
                    } else {
                        BPCFTLAM.ACCU_TYP = "02";
                        BPCFTLAM.TLR = BPCSDNCS.FROM_TLR;
                    }
                    BPCFTLAM.CCY = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY;
                    BPCFTLAM.AMT = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
                    CEP.TRC(SCCGWA, BPCFTLAM.TLR);
                    S000_CALL_BPZFTLAM();
                }
            }
        }
    }
    public void B030_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSNCSO);
        BPCSNCSO.FROM_BR = BPCSDNCS.FROM_BR;
        BPCSNCSO.PLBOX_NO = BPCSDNCS.PLBOX_NO;
        BPCSNCSO.TO_TLR = BPCSDNCS.TO_TLR;
        BPCSNCSO.CS_KIND = BPCSDNCS.CS_KIND;
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5 
            && BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() != 0; WS_CCY_CNT += 1) {
            BPCSNCSO.CCY_INFO[WS_CCY_CNT-1].CASH_TYP = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
            BPCSNCSO.CCY_INFO[WS_CCY_CNT-1].CCY = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY;
            BPCSNCSO.CCY_INFO[WS_CCY_CNT-1].AMT = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
            CEP.TRC(SCCGWA, BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CASH_TYP);
            CEP.TRC(SCCGWA, BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY);
            CEP.TRC(SCCGWA, BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY_AMT);
            BPCSNCSO.CCY_CNT = WS_CCY_CNT;
            for (WS_START_CNT = 1; WS_START_CNT <= 12 
                && BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_START_CNT-1].PAR_VAL != 0; WS_START_CNT += 1) {
                WS_INFO_CNT = ( WS_CCY_CNT - 1 ) * 12 + WS_START_CNT;
                BPCSNCSO.DT_INFO[WS_INFO_CNT-1].P_PVAL = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_START_CNT-1].PAR_VAL;
                BPCSNCSO.DT_INFO[WS_INFO_CNT-1].P_NUM = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_START_CNT-1].PAR_NUM;
                BPCSNCSO.DT_INFO[WS_INFO_CNT-1].P_MFLG = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_START_CNT-1].M_FLG;
                BPCSNCSO.DT_CNT = WS_INFO_CNT;
            }
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCSDNCS.OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCSNCSO;
        SCCFMT.DATA_LEN = 1696;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B040_PROCESS_BOX_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLVB);
        if (BPCSDNCS.FUNC == '4') {
            BPRTLVB.KEY.PLBOX_NO = BPCSDNCS.PLBOX_NO;
            BPRTLVB.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCTLVBF.INFO.FUNC = 'R';
            BPCTLVBF.POINTER = BPRTLVB;
            BPCTLVBF.LEN = 187;
            S000_CALL_BPZTLVBF();
            if (BPCTLVBF.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLVB_NOTFND;
                S000_ERR_MSG_PROC();
            }
            CEP.TRC(SCCGWA, BPRTLVB.PLBOX_TP);
            if (BPRTLVB.PLBOX_TP == '6') {
                WS_CNT = 0;
                WS_CNT1 = 0;
                IBS.CPY2CLS(SCCGWA, BPRTLVB.CRNT_TLR, WS_CRNT_TLR[1-1]);
                IBS.CPY2CLS(SCCGWA, BPRTLVB.CRNT_TLR1, WS_CRNT_TLR[2-1]);
                IBS.CPY2CLS(SCCGWA, BPRTLVB.CRNT_TLR2, WS_CRNT_TLR[3-1]);
                IBS.CPY2CLS(SCCGWA, BPRTLVB.CRNT_TLR3, WS_CRNT_TLR[4-1]);
                IBS.CPY2CLS(SCCGWA, BPRTLVB.CRNT_TLR4, WS_CRNT_TLR[5-1]);
                for (WS_CNT = 1; WS_CNT1 <= 5; WS_CNT += 1) {
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_CRNT_TLR[WS_CNT-1]);
                    if (JIBS_tmp_str[0].trim().length() == 0) {
                        WS_CNT1 = 6;
                    }
                    CEP.TRC(SCCGWA, WS_CNT);
                    CEP.TRC(SCCGWA, WS_CNT1);
                }
                CEP.TRC(SCCGWA, WS_CNT);
                WS_CNT = WS_CNT - 1;
                if (WS_CNT == 1) {
                    BPRTLVB.CRNT_TLR = BPCSDNCS.TO_TLR;
                } else if (WS_CNT == 2) {
                    BPRTLVB.CRNT_TLR1 = BPCSDNCS.TO_TLR;
                } else if (WS_CNT == 3) {
                    BPRTLVB.CRNT_TLR2 = BPCSDNCS.TO_TLR;
                } else if (WS_CNT == 4) {
                    BPRTLVB.CRNT_TLR3 = BPCSDNCS.TO_TLR;
                } else if (WS_CNT == 5) {
                    BPRTLVB.CRNT_TLR4 = BPCSDNCS.TO_TLR;
                } else {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CASH_BOX_INUSED);
                }
            } else {
                BPRTLVB.CRNT_TLR = BPCSDNCS.TO_TLR;
            }
            BPRTLVB.RLTD_FLG = 'N';
            BPRTLVB.LAST_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPRTLVB.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            BPCTLVBF.INFO.FUNC = 'U';
            BPCTLVBF.POINTER = BPRTLVB;
            BPCTLVBF.LEN = 187;
            S000_CALL_BPZTLVBF();
            if (BPCTLVBF.RETURN_INFO != 'F') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLVB_NOTFND;
                S000_ERR_MSG_PROC();
            }
            IBS.init(SCCGWA, BPCTLIBB);
            IBS.init(SCCGWA, BPRCLIB);
            BPRCLIB.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRCLIB.KEY.PLBOX_NO = BPCSDNCS.PLBOX_NO;
            CEP.TRC(SCCGWA, BPRCLIB.KEY.BR);
            CEP.TRC(SCCGWA, BPRCLIB.KEY.PLBOX_NO);
            BPCTLIBB.INFO.FUNC = '1';
            BPCTLIBB.POINTER = BPRCLIB;
            BPCTLIBB.LEN = 352;
            S000_CALL_BPZTLIBB();
            BPCTLIBB.INFO.FUNC = 'N';
            BPCTLIBB.POINTER = BPRCLIB;
            BPCTLIBB.LEN = 352;
            S000_CALL_BPZTLIBB();
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTLIBB.RC);
            while (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CLIB_NOTFND)) {
                CEP.TRC(SCCGWA, BPRCLIB.KEY.CCY);
                if (BPRCLIB.KEY.CCY.trim().length() > 0) {
                    WS_CCY_CNT = WS_CCY_CNT + 1;
                    BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CASH_TYP = BPRCLIB.KEY.CASH_TYP;
                    BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY = BPRCLIB.KEY.CCY;
                    BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY_AMT = BPRCLIB.BAL;
                    CEP.TRC(SCCGWA, BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CASH_TYP);
                    CEP.TRC(SCCGWA, BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY);
                    CEP.TRC(SCCGWA, BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY_AMT);
                    CEP.TRC(SCCGWA, WS_CCY_CNT);
                }
                BPRCLIB.BAL_FLG = 'N';
                BPRCLIB.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                BPCTLIBB.INFO.FUNC = 'W';
                BPCTLIBB.POINTER = BPRCLIB;
                BPCTLIBB.LEN = 352;
                S000_CALL_BPZTLIBB();
                BPCTLIBB.INFO.FUNC = 'N';
                BPCTLIBB.POINTER = BPRCLIB;
                BPCTLIBB.LEN = 352;
                S000_CALL_BPZTLIBB();
            }
            BPCTLIBB.INFO.FUNC = 'E';
            BPCTLIBB.POINTER = BPRCLIB;
            BPCTLIBB.LEN = 352;
            S000_CALL_BPZTLIBB();
        }
        if (BPCSDNCS.FUNC == '5') {
            BPRTLVB.KEY.PLBOX_NO = BPCSDNCS.PLBOX_NO;
            BPRTLVB.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCTLVBF.INFO.FUNC = 'R';
            BPCTLVBF.POINTER = BPRTLVB;
            BPCTLVBF.LEN = 187;
            S000_CALL_BPZTLVBF();
            if (BPCTLVBF.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLVB_NOTFND;
                S000_ERR_MSG_PROC();
            }
            BPRTLVB.LAST_TLR = BPRTLVB.CRNT_TLR;
            if (BPRTLVB.PLBOX_TP != '6') {
                BPRTLVB.CRNT_TLR = " ";
            } else {
                if (BPCSDNCS.FROM_TLR.equalsIgnoreCase(BPRTLVB.CRNT_TLR)) {
                    BPRTLVB.CRNT_TLR = " ";
                }
                if (BPCSDNCS.FROM_TLR.equalsIgnoreCase(BPRTLVB.CRNT_TLR1)) {
                    BPRTLVB.CRNT_TLR1 = " ";
                }
                if (BPCSDNCS.FROM_TLR.equalsIgnoreCase(BPRTLVB.CRNT_TLR2)) {
                    BPRTLVB.CRNT_TLR2 = " ";
                }
                if (BPCSDNCS.FROM_TLR.equalsIgnoreCase(BPRTLVB.CRNT_TLR3)) {
                    BPRTLVB.CRNT_TLR3 = " ";
                }
                if (BPCSDNCS.FROM_TLR.equalsIgnoreCase(BPRTLVB.CRNT_TLR4)) {
                    BPRTLVB.CRNT_TLR4 = " ";
                }
            }
            BPRTLVB.RLTD_FLG = 'Y';
            BPRTLVB.LAST_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPRTLVB.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            BPCTLVBF.INFO.FUNC = 'U';
            BPCTLVBF.POINTER = BPRTLVB;
            BPCTLVBF.LEN = 187;
            S000_CALL_BPZTLVBF();
            if (BPCTLVBF.RETURN_INFO != 'F') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLVB_NOTFND;
                S000_ERR_MSG_PROC();
            }
            IBS.init(SCCGWA, BPCTLIBB);
            IBS.init(SCCGWA, BPRCLIB);
            BPRCLIB.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRCLIB.KEY.PLBOX_NO = BPCSDNCS.PLBOX_NO;
            CEP.TRC(SCCGWA, BPRCLIB.KEY.BR);
            CEP.TRC(SCCGWA, BPRCLIB.KEY.PLBOX_NO);
            BPCTLIBB.INFO.FUNC = '1';
            BPCTLIBB.POINTER = BPRCLIB;
            BPCTLIBB.LEN = 352;
            S000_CALL_BPZTLIBB();
            BPCTLIBB.INFO.FUNC = 'N';
            BPCTLIBB.POINTER = BPRCLIB;
            BPCTLIBB.LEN = 352;
            S000_CALL_BPZTLIBB();
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTLIBB.RC);
            while (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CLIB_NOTFND)) {
                CEP.TRC(SCCGWA, BPRCLIB.KEY.CCY);
                if (BPRCLIB.KEY.CCY.trim().length() > 0) {
                    WS_CCY_CNT = WS_CCY_CNT + 1;
                    BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CASH_TYP = BPRCLIB.KEY.CASH_TYP;
                    BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY = BPRCLIB.KEY.CCY;
                    BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY_AMT = BPRCLIB.BAL;
                    CEP.TRC(SCCGWA, BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CASH_TYP);
                    CEP.TRC(SCCGWA, BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY);
                    CEP.TRC(SCCGWA, BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY_AMT);
                    CEP.TRC(SCCGWA, WS_CCY_CNT);
                }
                BPCTLIBB.INFO.FUNC = 'N';
                BPCTLIBB.POINTER = BPRCLIB;
                BPCTLIBB.LEN = 352;
                S000_CALL_BPZTLIBB();
            }
            BPCTLIBB.INFO.FUNC = 'E';
            BPCTLIBB.POINTER = BPRCLIB;
            BPCTLIBB.LEN = 352;
            S000_CALL_BPZTLIBB();
        }
    }
    public void B040_PROCESS_BOX() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLVB);
        if (BPCSDNCS.FUNC == '4') {
            BPRTLVB.KEY.PLBOX_NO = BPCSDNCS.PLBOX_NO;
            BPRTLVB.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCTLVBF.INFO.FUNC = 'R';
            BPCTLVBF.POINTER = BPRTLVB;
            BPCTLVBF.LEN = 187;
            S000_CALL_BPZTLVBF();
            if (BPCTLVBF.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLVB_NOTFND;
                S000_ERR_MSG_PROC();
            }
            BPRTLVB.CRNT_TLR = BPCSDNCS.TO_TLR;
            BPRTLVB.RLTD_FLG = 'N';
            BPCTLVBF.INFO.FUNC = 'U';
            BPCTLVBF.POINTER = BPRTLVB;
            BPCTLVBF.LEN = 187;
            S000_CALL_BPZTLVBF();
            if (BPCTLVBF.RETURN_INFO != 'F') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLVB_NOTFND;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCSDNCS.FUNC == '5') {
            BPRTLVB.KEY.PLBOX_NO = BPCSDNCS.PLBOX_NO;
            BPRTLVB.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCTLVBF.INFO.FUNC = 'R';
            BPCTLVBF.POINTER = BPRTLVB;
            BPCTLVBF.LEN = 187;
            S000_CALL_BPZTLVBF();
            if (BPCTLVBF.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLVB_NOTFND;
                S000_ERR_MSG_PROC();
            }
            BPRTLVB.LAST_TLR = BPRTLVB.CRNT_TLR;
            BPRTLVB.CRNT_TLR = " ";
            BPRTLVB.RLTD_FLG = 'Y';
            BPCTLVBF.INFO.FUNC = 'U';
            BPCTLVBF.POINTER = BPRTLVB;
            BPCTLVBF.LEN = 187;
            S000_CALL_BPZTLVBF();
            if (BPCTLVBF.RETURN_INFO != 'F') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLVB_NOTFND;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B050_TRANS_BOX_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSNCSO);
        BPCSNCSO.FROM_BR = BPCSDNCS.FROM_BR;
        BPCSNCSO.PLBOX_NO = BPCSDNCS.PLBOX_NO;
        BPCSNCSO.TO_TLR = BPCSDNCS.TO_TLR;
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5 
            && BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() != 0; WS_CCY_CNT += 1) {
            BPCSNCSO.CCY_INFO[WS_CCY_CNT-1].CASH_TYP = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
            BPCSNCSO.CCY_INFO[WS_CCY_CNT-1].CCY = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY;
            BPCSNCSO.CCY_INFO[WS_CCY_CNT-1].AMT = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
            CEP.TRC(SCCGWA, BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CASH_TYP);
            CEP.TRC(SCCGWA, BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY);
            CEP.TRC(SCCGWA, BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY_AMT);
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCSDNCS.OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCSNCSO;
        SCCFMT.DATA_LEN = 1696;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B060_WRITE_THIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTHIS);
        IBS.init(SCCGWA, BPCTHISF);
        BPRTHIS.KEY.DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRTHIS.KEY.VCH_NO = SCCGWA.COMM_AREA.VCH_NO;
        S000_GET_SEQ();
        IBS.init(SCCGWA, BPRTHIS);
        BPRTHIS.KEY.DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRTHIS.KEY.VCH_NO = SCCGWA.COMM_AREA.VCH_NO;
        WS_SEQ += 1;
        BPRTHIS.KEY.SEQ = WS_SEQ;
        BPRTHIS.AP_CODE = SCCGWA.COMM_AREA.TR_MMO;
        BPRTHIS.TR_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (WS_CASH_FLG == 'D') {
            BPRTHIS.DC_FLG = 'D';
            BPRTHIS.PAY_PBNO = BPCUCSTO.PLBOX_NO;
        } else {
            BPRTHIS.DC_FLG = 'C';
            BPRTHIS.RCE_PBNO = BPCUCSIN.PLBOX_NO;
        }
        BPRTHIS.CCY = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY;
        BPRTHIS.CCY_TYP = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CASH_TYP.charAt(0);
        BPRTHIS.AMT = BPCSDNCS.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
        BPRTHIS.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRTHIS.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRTHIS.STS = '0';
        BPCTHISF.INFO.FUNC = 'A';
        BPCTHISF.POINTER = BPRTHIS;
        BPCTHISF.LEN = 959;
        S000_CALL_BPZTHISF();
    }
    public void S000_GET_SEQ() throws IOException,SQLException,Exception {
        BPTTHIS_RD = new DBParm();
        BPTTHIS_RD.TableName = "BPTTHIS";
        BPTTHIS_RD.set = "WS-SEQ=COUNT(*)";
        BPTTHIS_RD.where = "'DATE' = :BPRTHIS.KEY.DATE "
            + "AND VCH_NO = :BPRTHIS.KEY.VCH_NO";
        IBS.GROUP(SCCGWA, BPRTHIS, this, BPTTHIS_RD);
        CEP.TRC(SCCGWA, WS_SEQ);
    }
    public void S000_CALL_BPZTHISF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRTHIS.KEY.DATE);
        CEP.TRC(SCCGWA, BPRTHIS.KEY.VCH_NO);
        CEP.TRC(SCCGWA, BPRTHIS.KEY.SEQ);
        IBS.CALLCPN(SCCGWA, CPN_R_HISF, BPCTHISF);
        if (BPCTHISF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTHISF.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZTLVBF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_TLVB, BPCTLVBF);
    }
    public void S000_CALL_BPZTLIBB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_BRE_CLIB, BPCTLIBB);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTLIBB.RC);
        if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CLIB_NOTFND) 
            && BPCTLIBB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTLIBB.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZUCSIN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_CASH_IN, BPCUCSIN);
        if (BPCUCSIN.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUCSIN.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZUCSTO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_CASH_OUT, BPCUCSTO);
        if (BPCUCSTO.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUCSTO.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPCHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_ADD_CASH_HIS, BPCPCHIS);
        if (BPCPCHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPCHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFTLAM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_TLAM, BPCFTLAM);
        if (BPCFTLAM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLAM.RC);
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
