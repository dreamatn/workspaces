package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSLAMO {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    String CPN_U_CASH_IN = "BP-U-CASH-IN";
    String CPN_U_CASH_OUT = "BP-U-CASH-OUT";
    String CPN_P_ADD_CASH_HIS = "BP-P-ADD-CHIS";
    String CPN_U_CMOV_REGIST = "BP-U-CMOV-REGIST";
    String CPN_S_CASH_SUSPENSE = "BP-S-CASH-SUSPENSE  ";
    int K_MAX_CCY_CNT = 5;
    int K_MAX_PAR_CNT = 12;
    char K_FILLER_X01 = 0X01;
    String WS_ERR_MSG = " ";
    int WS_CCY_CNT = 0;
    int WS_PAR_CNT = 0;
    int WS_CNT = 0;
    int WS_TEMP_CONF = 0;
    int WS_START_CNT = 0;
    int WS_INFO_CNT = 0;
    int WS_CNT1 = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCUCSIN BPCUCSIN = new BPCUCSIN();
    BPCUCSTO BPCUCSTO = new BPCUCSTO();
    BPCOLAMO BPCOLAMO = new BPCOLAMO();
    BPCPCHIS BPCPCHIS = new BPCPCHIS();
    BPCURMOV BPCURMOV = new BPCURMOV();
    BPCSCSSP BPCSCSSP = new BPCSCSSP();
    SCCGWA SCCGWA;
    BPCSLAMO BPCSLAMO;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSLAMO BPCSLAMO) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSLAMO = BPCSLAMO;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSLAMO return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSLAMO.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_CHECK_INPUT();
            B050_AUTO_SUSP_PROC_FOR_CN();
            B020_PROCESS_CASH_OUT_FOR_CN();
            for (WS_CCY_CNT = 1; WS_CCY_CNT <= K_MAX_CCY_CNT; WS_CCY_CNT += 1) {
                if (BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0 
                    && BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].CCY_AMT != 0) {
                    B030_ON_WAY_PROC_FOR_CN();
                    WS_CNT1 += 1;
                    B025_HISTORY_PROC();
                }
                if (WS_CNT1 == 1) {
                    WS_TEMP_CONF = BPCURMOV.CONF_SEQ;
                }
                BPCSLAMO.CONF_NO = WS_TEMP_CONF;
                BPCSLAMO.MOVE_DT = SCCGWA.COMM_AREA.AC_DATE;
            }
            B040_TRANS_DATA_OUTPUT();
        } else {
            B010_CHECK_INPUT();
            B020_PROCESS_CASH_OUT();
            for (WS_CCY_CNT = 1; WS_CCY_CNT <= K_MAX_CCY_CNT; WS_CCY_CNT += 1) {
                if (BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0 
                    && BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].CCY_AMT != 0) {
                    CEP.TRC(SCCGWA, "11111");
                    CEP.TRC(SCCGWA, BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].CCY_AMT);
                    CEP.TRC(SCCGWA, BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].CCY);
                    B030_ON_WAY_PROC();
                    WS_CNT1 += 1;
                }
                CEP.TRC(SCCGWA, WS_CCY_CNT);
                CEP.TRC(SCCGWA, BPCURMOV.CONF_SEQ);
                if (WS_CNT1 == 1) {
                    WS_TEMP_CONF = BPCURMOV.CONF_SEQ;
                }
                BPCSLAMO.CONF_NO = WS_TEMP_CONF;
                BPCSLAMO.MOVE_DT = SCCGWA.COMM_AREA.AC_DATE;
            }
            B040_TRANS_DATA_OUTPUT();
        }
    }
    public void B025_HISTORY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPCHIS);
        BPCPCHIS.TLR = BPCSLAMO.FROM_TLR;
        BPCPCHIS.PLBOX_NO = BPCUCSTO.PLBOX_NO;
        BPCPCHIS.CCY = BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].CCY;
        BPCPCHIS.CASH_TYP = BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPCPCHIS.AMT = BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
        BPCPCHIS.VB_FLG = '0';
        BPCPCHIS.IN_OUT = 'C';
        BPCPCHIS.CS_KIND = BPCSLAMO.CS_KIND;
        CEP.TRC(SCCGWA, BPCURMOV.CONF_SEQ);
        BPCPCHIS.CONF_SEQ = BPCURMOV.CONF_SEQ;
        for (WS_PAR_CNT = 1; WS_PAR_CNT <= K_MAX_PAR_CNT; WS_PAR_CNT += 1) {
            if (BPCUCSTO.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_VAL > 0 
                && BPCUCSTO.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_NUM > 0) {
                BPCPCHIS.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_VAL = BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_VAL;
                BPCPCHIS.PAR_INFO.PAR_REC[WS_PAR_CNT-1].M_FLG = BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].M_FLG;
                BPCPCHIS.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_NUM = BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_NUM;
            }
        }
        S000_CALL_BPZPCHIS();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSLAMO);
        CEP.TRC(SCCGWA, BPCSLAMO.FROM_BR);
        CEP.TRC(SCCGWA, BPCSLAMO.FROM_TLR);
        CEP.TRC(SCCGWA, BPCSLAMO.TO_BR);
        CEP.TRC(SCCGWA, BPCSLAMO.TO_TLR);
        CEP.TRC(SCCGWA, BPCSLAMO.CS_KIND);
        CEP.TRC(SCCGWA, BPCSLAMO.CCY_INFO[1-1].CCY);
        CEP.TRC(SCCGWA, BPCSLAMO.CCY_INFO[1-1].CCY_AMT);
        CEP.TRC(SCCGWA, BPCSLAMO.CCY_INFO[1-1].PAR_INFO[1-1].PAR_VAL);
        CEP.TRC(SCCGWA, BPCSLAMO.CCY_INFO[1-1].PAR_INFO[1-1].PAR_NUM);
        if (BPCSLAMO.FROM_BR == 0 
            && BPCSLAMO.FROM_TLR.trim().length() == 0 
            || BPCSLAMO.CS_KIND != '0' 
            && BPCSLAMO.CS_KIND != '2' 
            && BPCSLAMO.CS_KIND != '3' 
            || BPCSLAMO.CCY_INFO[1-1].CCY.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, "111111");
    }
    public void B020_PROCESS_CASH_OUT_FOR_CN() throws IOException,SQLException,Exception {
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= K_MAX_CCY_CNT; WS_CCY_CNT += 1) {
            if (BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                IBS.init(SCCGWA, BPCUCSTO);
                BPCUCSTO.CASH_STAT = '2';
                BPCUCSTO.PLBOX_TYP = '4';
                BPCUCSTO.VB_BR = BPCSLAMO.FROM_BR;
                BPCUCSTO.VB_TLR = BPCSLAMO.FROM_TLR;
                BPCUCSTO.VB_FLG = '0';
                BPCUCSTO.CS_KIND = BPCSLAMO.CS_KIND;
                BPCUCSTO.CASH_TYP = BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
                BPCUCSTO.CCY = BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].CCY;
                BPCUCSTO.TX_AMT = BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
                for (WS_PAR_CNT = 1; WS_PAR_CNT <= K_MAX_PAR_CNT; WS_PAR_CNT += 1) {
                    BPCUCSTO.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_VAL = BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_VAL;
                    BPCUCSTO.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_NUM = BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_NUM;
                    BPCUCSTO.PAR_INFO.PAR_REC[WS_PAR_CNT-1].M_FLG = BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].M_FLG;
                }
                S000_CALL_BPZUCSTO();
            }
        }
    }
    public void B020_PROCESS_CASH_OUT() throws IOException,SQLException,Exception {
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= K_MAX_CCY_CNT; WS_CCY_CNT += 1) {
            if (BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                CEP.TRC(SCCGWA, BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].CCY);
                IBS.init(SCCGWA, BPCUCSTO);
                BPCUCSTO.CASH_STAT = '0';
                BPCUCSTO.PLBOX_TYP = '4';
                BPCUCSTO.VB_TLR = BPCSLAMO.FROM_TLR;
                BPCUCSTO.VB_FLG = '0';
                BPCUCSTO.CS_KIND = BPCSLAMO.CS_KIND;
                BPCUCSTO.CASH_TYP = BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
                BPCUCSTO.CCY = BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].CCY;
                CEP.TRC(SCCGWA, BPCUCSTO.PLBOX_TYP);
                CEP.TRC(SCCGWA, "123123");
                CEP.TRC(SCCGWA, BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].CCY_AMT);
                BPCUCSTO.TX_AMT = BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
                for (WS_PAR_CNT = 1; WS_PAR_CNT <= K_MAX_PAR_CNT; WS_PAR_CNT += 1) {
                    BPCUCSTO.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_VAL = BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_VAL;
                    BPCUCSTO.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_NUM = BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_NUM;
                    BPCUCSTO.PAR_INFO.PAR_REC[WS_PAR_CNT-1].M_FLG = BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].M_FLG;
                }
                CEP.TRC(SCCGWA, BPCUCSTO);
                CEP.TRC(SCCGWA, BPCUCSTO.VB_TLR);
                CEP.TRC(SCCGWA, BPCUCSTO.CS_KIND);
                CEP.TRC(SCCGWA, BPCUCSTO.CCY);
                CEP.TRC(SCCGWA, BPCUCSTO.TX_AMT);
                S000_CALL_BPZUCSTO();
                IBS.init(SCCGWA, BPCPCHIS);
                BPCPCHIS.TLR = SCCGWA.COMM_AREA.TL_ID;
                BPCPCHIS.PLBOX_NO = BPCUCSTO.PLBOX_NO;
                BPCPCHIS.CCY = BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].CCY;
                BPCPCHIS.CASH_TYP = BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
                BPCPCHIS.AMT = BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
                BPCPCHIS.VB_FLG = '0';
                BPCPCHIS.IN_OUT = 'C';
                BPCPCHIS.CS_KIND = BPCSLAMO.CS_KIND;
                for (WS_PAR_CNT = 1; WS_PAR_CNT <= K_MAX_PAR_CNT; WS_PAR_CNT += 1) {
                    if (BPCUCSTO.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_VAL > 0 
                        && BPCUCSTO.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_NUM > 0) {
                        BPCPCHIS.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_VAL = BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_VAL;
                        BPCPCHIS.PAR_INFO.PAR_REC[WS_PAR_CNT-1].M_FLG = BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].M_FLG;
                        BPCPCHIS.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_NUM = BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_NUM;
                    }
                }
                S000_CALL_BPZPCHIS();
            }
        }
    }
    public void B030_ON_WAY_PROC_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCURMOV);
        BPCURMOV.MOV_TYPE = '5';
        BPCURMOV.CASH_TYP = BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPCURMOV.CCY = BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].CCY;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            BPCURMOV.MOVE_DATE = BPCSLAMO.MOVE_DT;
            BPCURMOV.CONF_SEQ = BPCSLAMO.CONF_NO;
        } else {
            BPCURMOV.MOVE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCURMOV.CONF_SEQ = WS_TEMP_CONF;
        }
        BPCURMOV.CS_KIND = BPCSLAMO.CS_KIND;
        BPCURMOV.OUT_TLR = BPCSLAMO.FROM_TLR;
        BPCURMOV.OUT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCURMOV.IN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCURMOV.IN_TLR = BPCSLAMO.TO_TLR;
        BPCURMOV.TOTAL_AMT = BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
        for (WS_PAR_CNT = 1; WS_PAR_CNT <= K_MAX_PAR_CNT; WS_PAR_CNT += 1) {
            BPCURMOV.CCY_INFO.CCY_DETL[WS_PAR_CNT-1].CCY_VAL = BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_VAL;
            BPCURMOV.CCY_INFO.CCY_DETL[WS_PAR_CNT-1].CCY_NUM = BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_NUM;
            BPCURMOV.CCY_INFO.CCY_DETL[WS_PAR_CNT-1].CCY_MFLG = BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].M_FLG;
        }
        S000_CALL_BPZURMOV();
    }
    public void B030_ON_WAY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCURMOV);
        BPCURMOV.MOV_TYPE = '5';
        BPCURMOV.CASH_TYP = BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPCURMOV.CCY = BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].CCY;
        CEP.TRC(SCCGWA, BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].CCY);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            BPCURMOV.MOVE_DATE = BPCSLAMO.MOVE_DT;
            BPCURMOV.CONF_SEQ = BPCSLAMO.CONF_NO;
        } else {
            BPCURMOV.MOVE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCURMOV.CONF_SEQ = WS_TEMP_CONF;
        }
        BPCURMOV.CS_KIND = BPCSLAMO.CS_KIND;
        BPCURMOV.OUT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCURMOV.OUT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCURMOV.IN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCURMOV.IN_TLR = BPCSLAMO.TO_TLR;
        CEP.TRC(SCCGWA, WS_CCY_CNT);
        BPCURMOV.TOTAL_AMT = BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSLAMO.CCY_INFO[WS_CCY_CNT-1]);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCURMOV.CCY_INFO);
        S000_CALL_BPZURMOV();
    }
    public void B040_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOLAMO);
        BPCOLAMO.FROM_TLR = BPCSLAMO.FROM_TLR;
        BPCOLAMO.MOVE_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCOLAMO.CONF_SEQ = WS_TEMP_CONF;
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5 
            && BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() != 0; WS_CCY_CNT += 1) {
            BPCOLAMO.CCY_INFO[WS_CCY_CNT-1].CASH_TYP = BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
            BPCOLAMO.CCY_INFO[WS_CCY_CNT-1].CCY = BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].CCY;
            BPCOLAMO.CCY_INFO[WS_CCY_CNT-1].AMT = BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
            CEP.TRC(SCCGWA, WS_CCY_CNT);
            BPCOLAMO.CCY_CNT = WS_CCY_CNT;
            for (WS_START_CNT = 1; WS_START_CNT <= 12 
                && BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_START_CNT-1].PAR_VAL != 0; WS_START_CNT += 1) {
                WS_INFO_CNT = ( WS_CCY_CNT - 1 ) * 12 + WS_START_CNT;
                BPCOLAMO.DT_INFO[WS_INFO_CNT-1].P_PVAL = BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_START_CNT-1].PAR_VAL;
                BPCOLAMO.DT_INFO[WS_INFO_CNT-1].P_NUM = BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_START_CNT-1].PAR_NUM;
                BPCOLAMO.DT_INFO[WS_INFO_CNT-1].P_MFLG = BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_START_CNT-1].M_FLG;
                BPCOLAMO.DT_CNT = WS_INFO_CNT;
            }
        }
        CEP.TRC(SCCGWA, BPCOLAMO);
        CEP.TRC(SCCGWA, BPCOLAMO);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCSLAMO.OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOLAMO;
        SCCFMT.DATA_LEN = 1700;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B050_AUTO_SUSP_PROC_FOR_CN() throws IOException,SQLException,Exception {
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= K_MAX_CCY_CNT; WS_CCY_CNT += 1) {
            if (BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0 
                && BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].SUSP_AMT != 0) {
                IBS.init(SCCGWA, BPCSCSSP);
                BPCSCSSP.CASH_TYP = BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
                BPCSCSSP.CCY = BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].CCY;
                BPCSCSSP.BOX_FLG = '4';
                if (BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].SUSP_AMT > 0) {
                    BPCSCSSP.TOTAL_AMT = BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].SUSP_AMT;
                    BPCSCSSP.ML_OPT = '1';
                } else {
                    BPCSCSSP.TOTAL_AMT = BPCSCSSP.TOTAL_AMT - BPCSLAMO.CCY_INFO[WS_CCY_CNT-1].SUSP_AMT;
                    BPCSCSSP.ML_OPT = '0';
                }
                BPCSCSSP.CS_KIND = '0';
                BPCSCSSP.SUSP_TYPE = '2';
                if (SCCGWA.COMM_AREA.CHNL == null) SCCGWA.COMM_AREA.CHNL = "";
                JIBS_tmp_int = SCCGWA.COMM_AREA.CHNL.length();
                for (int i=0;i<5-JIBS_tmp_int;i++) SCCGWA.COMM_AREA.CHNL += " ";
                if (!SCCGWA.COMM_AREA.CHNL.substring(0, 3).equalsIgnoreCase("103")) {
                    BPCSCSSP.TLR = BPCSLAMO.TLR;
                }
                BPCSCSSP.BOX_FLG = '4';
                CEP.TRC(SCCGWA, BPCSCSSP.BOX_FLG);
                S000_CALL_BPZSCSSP();
            }
        }
    }
    public void S000_CALL_BPZSCSSP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_CASH_SUSPENSE, BPCSCSSP);
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
    public void S000_CALL_BPZURMOV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_CMOV_REGIST, BPCURMOV);
        if (BPCURMOV.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCURMOV.RC);
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
