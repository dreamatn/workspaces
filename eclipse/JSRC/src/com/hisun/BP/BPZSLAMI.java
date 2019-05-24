package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSLAMI {
    String CPN_U_CASH_IN = "BP-U-CASH-IN";
    String CPN_U_CASH_OUT = "BP-U-CASH-OUT";
    String CPN_P_ADD_CASH_HIS = "BP-P-ADD-CHIS";
    String CPN_U_CMOV_REGIST = "BP-U-CMOV-REGIST";
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
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCUCSIN BPCUCSIN = new BPCUCSIN();
    BPCUCSTO BPCUCSTO = new BPCUCSTO();
    BPCOLAMI BPCOLAMI = new BPCOLAMI();
    BPCPCHIS BPCPCHIS = new BPCPCHIS();
    BPCURMOV BPCURMOV = new BPCURMOV();
    SCCGWA SCCGWA;
    BPCSLAMI BPCSLAMI;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSLAMI BPCSLAMI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSLAMI = BPCSLAMI;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSLAMI return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSLAMI.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_CHECK_INPUT_FOR_CN();
            B020_PROCESS_CASH_OUT_FOR_CN();
        } else {
            B010_CHECK_INPUT();
            B020_PROCESS_CASH_OUT();
        }
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= K_MAX_CCY_CNT; WS_CCY_CNT += 1) {
            if (BPCSLAMI.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                B030_ON_WAY_PROC();
            }
            CEP.TRC(SCCGWA, WS_CCY_CNT);
            CEP.TRC(SCCGWA, BPCURMOV.CONF_SEQ);
            if (WS_CCY_CNT == 1) {
                WS_TEMP_CONF = BPCURMOV.CONF_SEQ;
            }
            BPCSLAMI.CONF_NO = WS_TEMP_CONF;
            BPCSLAMI.MOVE_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
    }
    public void B010_CHECK_INPUT_FOR_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSLAMI);
        CEP.TRC(SCCGWA, BPCSLAMI.FROM_BR);
        CEP.TRC(SCCGWA, BPCSLAMI.FROM_TLR);
        CEP.TRC(SCCGWA, BPCSLAMI.TO_BR);
        CEP.TRC(SCCGWA, BPCSLAMI.TO_TLR);
        CEP.TRC(SCCGWA, BPCSLAMI.CS_KIND);
        CEP.TRC(SCCGWA, BPCSLAMI.CCY_INFO[1-1].CCY);
        CEP.TRC(SCCGWA, BPCSLAMI.CCY_INFO[1-1].CCY_AMT);
        CEP.TRC(SCCGWA, BPCSLAMI.CCY_INFO[1-1].PAR_INFO[1-1].PAR_VAL);
        CEP.TRC(SCCGWA, BPCSLAMI.CCY_INFO[1-1].PAR_INFO[1-1].PAR_NUM);
        if (BPCSLAMI.FROM_BR == 0 
            && BPCSLAMI.FROM_TLR.trim().length() == 0 
            || BPCSLAMI.TO_BR == 0 
            && BPCSLAMI.TO_TLR.trim().length() == 0 
            || BPCSLAMI.CS_KIND != '0' 
            && BPCSLAMI.CS_KIND != '2' 
            && BPCSLAMI.CS_KIND != '3' 
            || BPCSLAMI.CCY_INFO[1-1].CCY.trim().length() == 0 
            || BPCSLAMI.CCY_INFO[1-1].CCY_AMT == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, "111111");
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSLAMI);
        CEP.TRC(SCCGWA, BPCSLAMI.FROM_BR);
        CEP.TRC(SCCGWA, BPCSLAMI.FROM_TLR);
        CEP.TRC(SCCGWA, BPCSLAMI.TO_BR);
        CEP.TRC(SCCGWA, BPCSLAMI.TO_TLR);
        CEP.TRC(SCCGWA, BPCSLAMI.CS_KIND);
        CEP.TRC(SCCGWA, BPCSLAMI.CCY_INFO[1-1].CCY);
        CEP.TRC(SCCGWA, BPCSLAMI.CCY_INFO[1-1].CCY_AMT);
        CEP.TRC(SCCGWA, BPCSLAMI.CCY_INFO[1-1].PAR_INFO[1-1].PAR_VAL);
        CEP.TRC(SCCGWA, BPCSLAMI.CCY_INFO[1-1].PAR_INFO[1-1].PAR_NUM);
        if (BPCSLAMI.FROM_BR == 0 
            && BPCSLAMI.FROM_TLR.trim().length() == 0 
            || BPCSLAMI.TO_BR == 0 
            && BPCSLAMI.TO_TLR.trim().length() == 0 
            || BPCSLAMI.CS_KIND != '0' 
            && BPCSLAMI.CS_KIND != '2' 
            && BPCSLAMI.CS_KIND != '3' 
            || BPCSLAMI.CCY_INFO[1-1].CCY.trim().length() == 0 
            || BPCSLAMI.CCY_INFO[1-1].CCY_AMT == 0 
            || BPCSLAMI.CCY_INFO[1-1].PAR_INFO[1-1].PAR_VAL == 0 
            || BPCSLAMI.CCY_INFO[1-1].PAR_INFO[1-1].PAR_NUM == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, "111111");
    }
    public void B020_PROCESS_CASH_OUT_FOR_CN() throws IOException,SQLException,Exception {
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= K_MAX_CCY_CNT; WS_CCY_CNT += 1) {
            if (BPCSLAMI.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                IBS.init(SCCGWA, BPCUCSTO);
                BPCUCSTO.CASH_STAT = '0';
                BPCUCSTO.VB_BR = BPCSLAMI.FROM_BR;
                BPCUCSTO.VB_TLR = BPCSLAMI.FROM_TLR;
                BPCUCSTO.PLBOX_TYP = BPCSLAMI.PLBOX_TYP;
                CEP.TRC(SCCGWA, BPCUCSTO.PLBOX_TYP);
                if (BPCUCSTO.PLBOX_TYP == '3' 
                    || BPCUCSTO.PLBOX_TYP == '6') {
                    BPCUCSTO.VB_FLG = '0';
                } else {
                    BPCUCSTO.VB_FLG = '1';
                }
                BPCUCSTO.CS_KIND = BPCSLAMI.CS_KIND;
                BPCUCSTO.CASH_TYP = BPCSLAMI.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
                BPCUCSTO.CCY = BPCSLAMI.CCY_INFO[WS_CCY_CNT-1].CCY;
                BPCUCSTO.TX_AMT = BPCSLAMI.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
                for (WS_PAR_CNT = 1; WS_PAR_CNT <= K_MAX_PAR_CNT; WS_PAR_CNT += 1) {
                    BPCUCSTO.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_VAL = BPCSLAMI.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_VAL;
                    BPCUCSTO.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_NUM = BPCSLAMI.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_NUM;
                    BPCUCSTO.PAR_INFO.PAR_REC[WS_PAR_CNT-1].M_FLG = BPCSLAMI.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].M_FLG;
                }
                S000_CALL_BPZUCSTO();
            }
        }
    }
    public void B020_PROCESS_CASH_OUT() throws IOException,SQLException,Exception {
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= K_MAX_CCY_CNT; WS_CCY_CNT += 1) {
            if (BPCSLAMI.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                CEP.TRC(SCCGWA, BPCSLAMI.CCY_INFO[WS_CCY_CNT-1].CCY);
                IBS.init(SCCGWA, BPCUCSTO);
                BPCUCSTO.CASH_STAT = '0';
                BPCUCSTO.VB_TLR = BPCSLAMI.FROM_TLR;
                BPCUCSTO.PLBOX_TYP = '3';
                BPCUCSTO.VB_FLG = '0';
                BPCUCSTO.CS_KIND = BPCSLAMI.CS_KIND;
                BPCUCSTO.CASH_TYP = BPCSLAMI.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
                BPCUCSTO.CCY = BPCSLAMI.CCY_INFO[WS_CCY_CNT-1].CCY;
                CEP.TRC(SCCGWA, "123123");
                CEP.TRC(SCCGWA, BPCSLAMI.CCY_INFO[WS_CCY_CNT-1].CCY_AMT);
                BPCUCSTO.TX_AMT = BPCSLAMI.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
                for (WS_PAR_CNT = 1; WS_PAR_CNT <= K_MAX_PAR_CNT; WS_PAR_CNT += 1) {
                    BPCUCSTO.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_VAL = BPCSLAMI.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_VAL;
                    BPCUCSTO.PAR_INFO.PAR_REC[WS_PAR_CNT-1].PAR_NUM = BPCSLAMI.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_NUM;
                    BPCUCSTO.PAR_INFO.PAR_REC[WS_PAR_CNT-1].M_FLG = BPCSLAMI.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].M_FLG;
                }
                CEP.TRC(SCCGWA, BPCUCSTO);
                CEP.TRC(SCCGWA, BPCUCSTO.VB_TLR);
                CEP.TRC(SCCGWA, BPCUCSTO.CS_KIND);
                CEP.TRC(SCCGWA, BPCUCSTO.CCY);
                CEP.TRC(SCCGWA, BPCUCSTO.TX_AMT);
                S000_CALL_BPZUCSTO();
            }
        }
    }
    public void B030_ON_WAY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCURMOV);
        BPCURMOV.MOV_TYPE = '4';
        BPCURMOV.CASH_TYP = BPCSLAMI.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPCURMOV.CCY = BPCSLAMI.CCY_INFO[WS_CCY_CNT-1].CCY;
        CEP.TRC(SCCGWA, BPCSLAMI.CCY_INFO[WS_CCY_CNT-1].CCY);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            BPCURMOV.MOVE_DATE = BPCSLAMI.MOVE_DT;
            BPCURMOV.CONF_SEQ = BPCSLAMI.CONF_NO;
        } else {
            BPCURMOV.MOVE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCURMOV.CONF_SEQ = WS_TEMP_CONF;
        }
        BPCURMOV.CS_KIND = BPCSLAMI.CS_KIND;
        BPCURMOV.OUT_TLR = BPCSLAMI.FROM_TLR;
        BPCURMOV.OUT_BR = BPCSLAMI.FROM_BR;
        BPCURMOV.IN_BR = BPCSLAMI.TO_BR;
        BPCURMOV.IN_TLR = BPCSLAMI.TO_TLR;
        CEP.TRC(SCCGWA, WS_CCY_CNT);
        BPCURMOV.TOTAL_AMT = BPCSLAMI.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
        for (WS_PAR_CNT = 1; WS_PAR_CNT <= K_MAX_PAR_CNT; WS_PAR_CNT += 1) {
            BPCURMOV.CCY_INFO.CCY_DETL[WS_PAR_CNT-1].CCY_VAL = BPCSLAMI.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_VAL;
            BPCURMOV.CCY_INFO.CCY_DETL[WS_PAR_CNT-1].CCY_NUM = BPCSLAMI.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].PAR_NUM;
            BPCURMOV.CCY_INFO.CCY_DETL[WS_PAR_CNT-1].CCY_MFLG = BPCSLAMI.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_PAR_CNT-1].M_FLG;
        }
        S000_CALL_BPZURMOV();
    }
    public void B040_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOLAMI);
        BPCOLAMI.FROM_TLR = BPCSLAMI.FROM_TLR;
        BPCOLAMI.TO_TLR = BPCSLAMI.TO_TLR;
        BPCOLAMI.MOVE_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCOLAMI.CONF_SEQ = WS_TEMP_CONF;
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5 
            && BPCSLAMI.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() != 0; WS_CCY_CNT += 1) {
            BPCOLAMI.CCY_INFO[WS_CCY_CNT-1].CASH_TYP = BPCSLAMI.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
            BPCOLAMI.CCY_INFO[WS_CCY_CNT-1].CCY = BPCSLAMI.CCY_INFO[WS_CCY_CNT-1].CCY;
            BPCOLAMI.CCY_INFO[WS_CCY_CNT-1].AMT = BPCSLAMI.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
            for (WS_START_CNT = 1; WS_START_CNT <= 12 
                && BPCSLAMI.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_START_CNT-1].PAR_VAL != 0; WS_START_CNT += 1) {
                WS_INFO_CNT = ( WS_CCY_CNT - 1 ) * 12 + WS_START_CNT;
                BPCOLAMI.DT_INFO[WS_INFO_CNT-1].P_PVAL = BPCSLAMI.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_START_CNT-1].PAR_VAL;
                BPCOLAMI.DT_INFO[WS_INFO_CNT-1].P_NUM = BPCSLAMI.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_START_CNT-1].PAR_NUM;
                BPCOLAMI.DT_INFO[WS_INFO_CNT-1].P_MFLG = BPCSLAMI.CCY_INFO[WS_CCY_CNT-1].PAR_INFO[WS_START_CNT-1].M_FLG;
            }
        }
        BPCOLAMI.CCY_CNT = WS_CCY_CNT;
        BPCOLAMI.DT_CNT = WS_INFO_CNT;
        CEP.TRC(SCCGWA, BPCOLAMI);
        CEP.TRC(SCCGWA, BPCOLAMI);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCSLAMI.OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOLAMI;
        SCCFMT.DATA_LEN = 1713;
        IBS.FMT(SCCGWA, SCCFMT);
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
