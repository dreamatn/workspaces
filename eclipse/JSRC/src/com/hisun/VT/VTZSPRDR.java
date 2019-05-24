package com.hisun.VT;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCSUBS;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class VTZSPRDR {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_P_INQ_ORG_LOW = "BP-P-INQ-ORG-LOW";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY";
    String WS_ERR_MSG = " ";
    short WS_CNT = 0;
    short WS_LEN = 0;
    VTZSPRDR_WS_BR_RANGE WS_BR_RANGE = new VTZSPRDR_WS_BR_RANGE();
    VTZSPRDR_WS_OUTPUT WS_OUTPUT = new VTZSPRDR_WS_OUTPUT();
    VTZSPRDR_WS_BROWSE_OUTPUT WS_BROWSE_OUTPUT = new VTZSPRDR_WS_BROWSE_OUTPUT();
    char WS_FND_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    VTCMSG_ERROR_MSG VTCMSG_ERROR_MSG = new VTCMSG_ERROR_MSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCSUBS SCCSUBS = new SCCSUBS();
    VTRPRDR VTROPRDR = new VTRPRDR();
    VTRPRDR VTRNPRDR = new VTRPRDR();
    SCCMPAG SCCMPAG = new SCCMPAG();
    VTRPRDR VTRPRDR = new VTRPRDR();
    VTCRPRDR VTCRPRDR = new VTCRPRDR();
    VTRVTCD VTRVTCD = new VTRVTCD();
    VTCRVTCD VTCRVTCD = new VTCRVTCD();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCPQRGC BPCPQRGC = new BPCPQRGC();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCOFBAS BPCOUBAS = new BPCOFBAS();
    VTRACCT VTRACCT = new VTRACCT();
    VTCRACCT VTCRACCT = new VTCRACCT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    VTCSPRDR VTCSPRDR;
    public void MP(SCCGWA SCCGWA, VTCSPRDR VTCSPRDR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.VTCSPRDR = VTCSPRDR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "VTZSPRDR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (VTCSPRDR.FUNC == 'B') {
            B020_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else if (VTCSPRDR.FUNC == 'A') {
            B040_ADD_PROCESS();
            if (pgmRtn) return;
        } else if (VTCSPRDR.FUNC == 'Q') {
            B030_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (VTCSPRDR.FUNC == 'M') {
            B050_MODIFY_RECORD();
            if (pgmRtn) return;
        } else if (VTCSPRDR.FUNC == 'D') {
            B070_DELETE_RECORD();
            if (pgmRtn) return;
        } else if (VTCSPRDR.FUNC == 'R') {
            B060_REACTIVE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_FUNC_CODE_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (VTCSPRDR.FUNC != 'B') {
            R000_PRDR_INFO_OUTPUT();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (VTCSPRDR.FUNC == 'A') {
            if (VTCSPRDR.CODE.trim().length() > 0) {
                IBS.init(SCCGWA, VTRVTCD);
                IBS.init(SCCGWA, VTCRVTCD);
                CEP.TRC(SCCGWA, VTCSPRDR.CODE);
                VTRVTCD.KEY.CODE = VTCSPRDR.CODE;
                VTCRVTCD.FUNC = 'Q';
                CEP.TRC(SCCGWA, VTRVTCD.KEY.CODE);
                VTCRVTCD.POINTER = VTRVTCD;
                VTCRVTCD.REC_LEN = 206;
                S000_CALL_VTZRVTCD();
                if (pgmRtn) return;
                if (VTCRVTCD.RETURN_INFO == 'N') {
                    WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_TAX_CODE_NOT_FOUND;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (VTCSPRDR.CNTR_TYPE.equalsIgnoreCase("FEES")) {
                IBS.init(SCCGWA, BPCOUBAS);
                BPCOUBAS.FUNC = 'I';
                BPCOUBAS.KEY.FEE_CODE = VTCSPRDR.PROD_CD;
                S000_CALL_BPZFUBAS();
                if (pgmRtn) return;
                if (BPCOUBAS.MSG_CODE.trim().length() > 0) {
                    WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_FEECODE_NOT_FOUND;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            } else {
                IBS.init(SCCGWA, BPCPQPRD);
                BPCPQPRD.PRDT_CODE = VTCSPRDR.PROD_CD;
                S010_CALL_BPZPQPRD();
                if (pgmRtn) return;
                if (BPCPQPRD.RC.RC_CODE != 0) {
                    WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_PROD_NOT_FOUND;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (!BPCPQPRD.PRDT_MODEL.equalsIgnoreCase(VTCSPRDR.CNTR_TYPE) 
                    && !BPCPQPRD.PRDT_MODEL1.equalsIgnoreCase(VTCSPRDR.CNTR_TYPE) 
                    && !BPCPQPRD.PRDT_MODEL2.equalsIgnoreCase(VTCSPRDR.CNTR_TYPE) 
                    && !BPCPQPRD.PRDT_MODEL3.equalsIgnoreCase(VTCSPRDR.CNTR_TYPE) 
                    && !BPCPQPRD.PRDT_MODEL4.equalsIgnoreCase(VTCSPRDR.CNTR_TYPE) 
                    && !BPCPQPRD.PRDT_MODEL5.equalsIgnoreCase(VTCSPRDR.CNTR_TYPE)) {
                    WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_PROD_NOT_CNTR_TYPE;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            CEP.TRC(SCCGWA, BPCPQPRD.RC);
            CEP.TRC(SCCGWA, BPCOUBAS.MSG_CODE);
            if (VTCSPRDR.CCY.trim().length() > 0 
                && !VTCSPRDR.CCY.equalsIgnoreCase("999")) {
                IBS.init(SCCGWA, BPCQCCY);
                BPCQCCY.DATA.CCY = VTCSPRDR.CCY;
                S000_CALL_BPZQCCY();
                if (pgmRtn) return;
            }
            if (VTCSPRDR.BR != 999999) {
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BR = VTCSPRDR.BR;
                S000_CALL_BPZPQORG();
                if (pgmRtn) return;
            }
        }
        if (VTCSPRDR.FUNC == 'A' 
            || VTCSPRDR.FUNC == 'M') {
            if (VTCSPRDR.BR_RGN.trim().length() > 0) {
                VTCSPRDR.BR_RGN = " ";
            }
        }
        if (VTCSPRDR.FUNC == 'A' 
            || VTCSPRDR.FUNC == 'M') {
            IBS.init(SCCGWA, VTRVTCD);
            IBS.init(SCCGWA, VTCRVTCD);
            VTRVTCD.KEY.CODE = VTCSPRDR.CODE;
            VTCRVTCD.FUNC = 'Q';
            VTCRVTCD.POINTER = VTRVTCD;
            VTCRVTCD.REC_LEN = 206;
            S000_CALL_VTZRVTCD();
            if (pgmRtn) return;
            if (VTRVTCD.RT == 0 
                && VTCSPRDR.TAX_TYPE != '0') {
                WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_RT_TYPE_ZERO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (VTRVTCD.EFF_DATE > SCCGWA.COMM_AREA.AC_DATE 
                || VTRVTCD.EXP_DATE < SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_CODE_IS_EXP;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (VTRVTCD.RT != 0 
                && VTCSPRDR.TAX_TYPE == '0') {
                WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_RT_TAX_TYPE_ZERO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (VTCSPRDR.FREE_TYPE == ' ' 
                && VTCSPRDR.TAX_TYPE == '0') {
                CEP.TRC(SCCGWA, "LZY");
                WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_FREE_TAX_TYPE_NULL;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 338;
        SCCMPAG.SCR_ROW_CNT = 20;
        SCCMPAG.SCR_COL_CNT = 5;
        B_MPAG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, VTRPRDR);
        WS_LEN = 10;
        for (WS_CNT = 1; WS_CNT <= WS_LEN; WS_CNT += 1) {
            if (VTCSPRDR.PROD_CD == null) VTCSPRDR.PROD_CD = "";
            JIBS_tmp_int = VTCSPRDR.PROD_CD.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) VTCSPRDR.PROD_CD += " ";
            if (VTCSPRDR.PROD_CD.substring(WS_CNT - 1, WS_CNT + 1 - 1).trim().length() > 0) {
                if (VTCSPRDR.PROD_CD == null) VTCSPRDR.PROD_CD = "";
                JIBS_tmp_int = VTCSPRDR.PROD_CD.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) VTCSPRDR.PROD_CD += " ";
                if (VTRPRDR.KEY.PROD_CD == null) VTRPRDR.KEY.PROD_CD = "";
                JIBS_tmp_int = VTRPRDR.KEY.PROD_CD.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) VTRPRDR.KEY.PROD_CD += " ";
                VTRPRDR.KEY.PROD_CD = VTRPRDR.KEY.PROD_CD.substring(0, WS_CNT - 1) + VTCSPRDR.PROD_CD.substring(WS_CNT - 1, WS_CNT + 1 - 1) + VTRPRDR.KEY.PROD_CD.substring(WS_CNT + 1 - 1);
            } else {
                if (VTRPRDR.KEY.PROD_CD == null) VTRPRDR.KEY.PROD_CD = "";
                JIBS_tmp_int = VTRPRDR.KEY.PROD_CD.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) VTRPRDR.KEY.PROD_CD += " ";
                VTRPRDR.KEY.PROD_CD = VTRPRDR.KEY.PROD_CD.substring(0, WS_CNT - 1) + "%" + VTRPRDR.KEY.PROD_CD.substring(WS_CNT + 1 - 1);
            }
        }
        VTRPRDR.KEY.CNTR_TYPE = VTCSPRDR.CNTR_TYPE;
        VTRPRDR.KEY.CODE = VTCSPRDR.CODE;
        VTRPRDR.BILL_FLG = VTCSPRDR.BILL_FLG;
        VTCRPRDR.FUNC = 'B';
        VTCRPRDR.OPT = 'S';
        VTCRPRDR.POINTER = VTRPRDR;
        VTCRPRDR.REC_LEN = 179;
        S000_CALL_VTZRPRDR();
        if (pgmRtn) return;
        VTCRPRDR.OPT = 'N';
        S000_CALL_VTZRPRDR();
        if (pgmRtn) return;
        while (VTCRPRDR.RETURN_INFO != 'N') {
            R00_BRW_OUTPUT();
            if (pgmRtn) return;
            VTCRPRDR.OPT = 'N';
            S000_CALL_VTZRPRDR();
            if (pgmRtn) return;
        }
        VTCRPRDR.OPT = 'E';
        S000_CALL_VTZRPRDR();
        if (pgmRtn) return;
    }
    public void B040_ADD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, VTRACCT);
        IBS.init(SCCGWA, VTCRACCT);
        VTRACCT.KEY.CODE = VTCSPRDR.CODE;
        VTRACCT.KEY.BILL_FLG = VTCSPRDR.BILL_FLG;
        VTRACCT.KEY.VAT_TYPE = VTCSPRDR.VAT_TYPE;
        VTCRACCT.FUNC = 'Q';
        VTCRACCT.POINTER = VTRACCT;
        VTCRACCT.REC_LEN = 133;
        S000_CALL_VTZRACCT();
        if (pgmRtn) return;
        if (VTCRACCT.RETURN_INFO == 'N') {
            WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_ACCT_REC_NOT_FOUND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, VTCRPRDR);
        IBS.init(SCCGWA, VTRPRDR);
        IBS.init(SCCGWA, VTROPRDR);
        IBS.init(SCCGWA, VTRNPRDR);
        VTRPRDR.KEY.CNTR_TYPE = VTCSPRDR.CNTR_TYPE;
        VTRPRDR.KEY.PROD_CD = VTCSPRDR.PROD_CD;
        VTRPRDR.KEY.BR = VTCSPRDR.BR;
        VTRPRDR.KEY.CCY = VTCSPRDR.CCY;
        VTRPRDR.KEY.CODE = VTCSPRDR.CODE;
        VTCRPRDR.FUNC = 'R';
        VTCRPRDR.POINTER = VTRPRDR;
        VTCRPRDR.REC_LEN = 179;
        S000_CALL_VTZRPRDR();
        if (pgmRtn) return;
        VTRPRDR.BR_RGN = VTCSPRDR.BR_RGN;
        VTRPRDR.BILL_FLG = VTCSPRDR.BILL_FLG;
        VTRPRDR.TAX_FLG = VTCSPRDR.TAX_FLG;
        VTRPRDR.TAX_TYPE = VTCSPRDR.TAX_TYPE;
        VTRPRDR.FREE_TYPE = VTCSPRDR.FREE_TYPE;
        VTRPRDR.BILL_LIM = VTCSPRDR.BILL_LIM;
        VTRPRDR.RMK = VTCSPRDR.DESC;
        VTRPRDR.OPEN_TLR = SCCGWA.COMM_AREA.TL_ID;
        VTRPRDR.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        VTRPRDR.VAT_TYPE = VTCSPRDR.VAT_TYPE;
        if (VTCRPRDR.RETURN_INFO == 'F') {
            if (VTRPRDR.STS == 'D') {
                VTCRPRDR.FUNC = 'U';
                VTRPRDR.STS = 'N';
                S000_CALL_VTZRPRDR();
                if (pgmRtn) return;
                S000_SAVE_NEW_DATA();
                if (pgmRtn) return;
                H000_HISTORY_PROCESS();
                if (pgmRtn) return;
            } else {
                WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_PRDR_KEY_FOUND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            VTRPRDR.STS = 'N';
            VTCRPRDR.FUNC = 'C';
            VTCRPRDR.POINTER = VTRPRDR;
            VTCRPRDR.REC_LEN = 179;
            S000_CALL_VTZRPRDR();
            if (pgmRtn) return;
            S000_SAVE_NEW_DATA();
            if (pgmRtn) return;
            H000_HISTORY_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void B050_MODIFY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, VTRACCT);
        IBS.init(SCCGWA, VTCRACCT);
        VTRACCT.KEY.CODE = VTCSPRDR.CODE;
        VTRACCT.KEY.BILL_FLG = VTCSPRDR.BILL_FLG;
        VTRACCT.KEY.VAT_TYPE = VTCSPRDR.VAT_TYPE;
        CEP.TRC(SCCGWA, VTCSPRDR.CODE);
        CEP.TRC(SCCGWA, VTRACCT.KEY.CODE);
        CEP.TRC(SCCGWA, VTCSPRDR.BILL_FLG);
        CEP.TRC(SCCGWA, VTRACCT.KEY.BILL_FLG);
        CEP.TRC(SCCGWA, VTCSPRDR.VAT_TYPE);
        CEP.TRC(SCCGWA, VTRACCT.KEY.VAT_TYPE);
        VTCRACCT.FUNC = 'Q';
        VTCRACCT.POINTER = VTRACCT;
        VTCRACCT.REC_LEN = 133;
        S000_CALL_VTZRACCT();
        if (pgmRtn) return;
        if (VTCRACCT.RETURN_INFO == 'N') {
            WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_ACCT_REC_NOT_FOUND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, VTCRPRDR);
        IBS.init(SCCGWA, VTRPRDR);
        IBS.init(SCCGWA, VTROPRDR);
        IBS.init(SCCGWA, VTRNPRDR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_BANK);
        VTRPRDR.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        VTRPRDR.KEY.CNTR_TYPE = VTCSPRDR.CNTR_TYPE;
        VTRPRDR.KEY.PROD_CD = VTCSPRDR.PROD_CD;
        VTRPRDR.KEY.BR = VTCSPRDR.BR;
        VTRPRDR.KEY.CCY = VTCSPRDR.CCY;
        VTRPRDR.KEY.CODE = VTCSPRDR.CODE;
        VTCRPRDR.FUNC = 'Q';
        VTCRPRDR.POINTER = VTRPRDR;
        VTCRPRDR.REC_LEN = 179;
        S000_CALL_VTZRPRDR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, VTRPRDR.STS);
        if (VTCRPRDR.RETURN_INFO == 'N') {
            WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_TAX_PRDR_NOT_FOUND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (VTRPRDR.STS == 'D') {
            WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_PROD_HAD_DELETE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        VTCRPRDR.FUNC = 'R';
        VTCRPRDR.POINTER = VTRPRDR;
        VTCRPRDR.REC_LEN = 179;
        S000_CALL_VTZRPRDR();
        if (pgmRtn) return;
        S000_SAVE_OLD_DATA();
        if (pgmRtn) return;
        VTCRPRDR.FUNC = 'U';
        VTRPRDR.BR_RGN = VTCSPRDR.BR_RGN;
        VTRPRDR.BILL_FLG = VTCSPRDR.BILL_FLG;
        VTRPRDR.TAX_FLG = VTCSPRDR.TAX_FLG;
        VTRPRDR.TAX_TYPE = VTCSPRDR.TAX_TYPE;
        VTRPRDR.FREE_TYPE = VTCSPRDR.FREE_TYPE;
        VTRPRDR.BILL_LIM = VTCSPRDR.BILL_LIM;
        VTRPRDR.RMK = VTCSPRDR.DESC;
        VTRPRDR.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        VTRPRDR.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        VTRPRDR.VAT_TYPE = VTCSPRDR.VAT_TYPE;
        S000_CALL_VTZRPRDR();
        if (pgmRtn) return;
        S000_SAVE_NEW_DATA();
        if (pgmRtn) return;
        H000_HISTORY_PROCESS();
        if (pgmRtn) return;
    }
    public void B060_REACTIVE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, VTCRPRDR);
        IBS.init(SCCGWA, VTRPRDR);
        IBS.init(SCCGWA, VTROPRDR);
        IBS.init(SCCGWA, VTRNPRDR);
        VTRPRDR.KEY.CNTR_TYPE = VTCSPRDR.CNTR_TYPE;
        VTRPRDR.KEY.PROD_CD = VTCSPRDR.PROD_CD;
        VTRPRDR.KEY.BR = VTCSPRDR.BR;
        VTRPRDR.KEY.CCY = VTCSPRDR.CCY;
        VTRPRDR.KEY.CODE = VTCSPRDR.CODE;
        VTCRPRDR.FUNC = 'Q';
        VTCRPRDR.POINTER = VTRPRDR;
        VTCRPRDR.REC_LEN = 179;
        S000_CALL_VTZRPRDR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, VTRPRDR.STS);
        if (VTRPRDR.STS == 'D') {
            VTCRPRDR.FUNC = 'R';
            VTCRPRDR.POINTER = VTRPRDR;
            VTCRPRDR.REC_LEN = 179;
            S000_CALL_VTZRPRDR();
            if (pgmRtn) return;
            S000_SAVE_OLD_DATA();
            if (pgmRtn) return;
            VTCRPRDR.FUNC = 'U';
            VTRPRDR.STS = 'N';
            S000_CALL_VTZRPRDR();
            if (pgmRtn) return;
            S000_SAVE_NEW_DATA();
            if (pgmRtn) return;
            H000_HISTORY_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void B070_DELETE_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, VTCRPRDR);
        IBS.init(SCCGWA, VTRPRDR);
        IBS.init(SCCGWA, VTROPRDR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_BANK);
        VTRPRDR.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        VTRPRDR.KEY.CNTR_TYPE = VTCSPRDR.CNTR_TYPE;
        VTRPRDR.KEY.PROD_CD = VTCSPRDR.PROD_CD;
        VTRPRDR.KEY.BR = VTCSPRDR.BR;
        VTRPRDR.KEY.CCY = VTCSPRDR.CCY;
        VTRPRDR.KEY.CODE = VTCSPRDR.CODE;
        VTCRPRDR.FUNC = 'Q';
        VTCRPRDR.POINTER = VTRPRDR;
        VTCRPRDR.REC_LEN = 179;
        S000_CALL_VTZRPRDR();
        if (pgmRtn) return;
        if (VTCRPRDR.RETURN_INFO == 'N') {
            WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_TAX_PRDR_NOT_FOUND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, VTRPRDR.STS);
        if (VTRPRDR.STS == 'D') {
            WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_PROD_HAD_DELETE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        VTCRPRDR.FUNC = 'R';
        VTCRPRDR.POINTER = VTRPRDR;
        VTCRPRDR.REC_LEN = 179;
        S000_CALL_VTZRPRDR();
        if (pgmRtn) return;
        S000_SAVE_OLD_DATA();
        if (pgmRtn) return;
        VTCRPRDR.FUNC = 'U';
        VTRPRDR.STS = 'D';
        S000_CALL_VTZRPRDR();
        if (pgmRtn) return;
        S000_SAVE_NEW_DATA();
        if (pgmRtn) return;
        H000_HISTORY_PROCESS();
        if (pgmRtn) return;
    }
    public void B030_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, VTRPRDR);
        IBS.init(SCCGWA, VTCRPRDR);
        VTRPRDR.KEY.CNTR_TYPE = VTCSPRDR.CNTR_TYPE;
        VTRPRDR.KEY.PROD_CD = VTCSPRDR.PROD_CD;
        VTRPRDR.KEY.BR = VTCSPRDR.BR;
        VTRPRDR.KEY.CCY = VTCSPRDR.CCY;
        VTRPRDR.KEY.CODE = VTCSPRDR.CODE;
        VTCRPRDR.FUNC = 'Q';
        VTCRPRDR.POINTER = VTRPRDR;
        VTCRPRDR.REC_LEN = 179;
        S000_CALL_VTZRPRDR();
        if (pgmRtn) return;
        if (VTCRPRDR.RETURN_INFO == 'N') {
            WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_TAX_PRDR_NOT_FOUND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_PRDR_INFO_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        IBS.init(SCCGWA, WS_OUTPUT);
        WS_OUTPUT.WS_PROD_CD = VTRPRDR.KEY.PROD_CD;
        WS_OUTPUT.WS_BR = VTRPRDR.KEY.BR;
        WS_OUTPUT.WS_CCY = VTRPRDR.KEY.CCY;
        WS_OUTPUT.WS_CODE = VTRPRDR.KEY.CODE;
        WS_OUTPUT.WS_BR_RGN = VTRPRDR.BR_RGN;
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_BR_RGN);
        WS_OUTPUT.WS_BILL_FLG = VTRPRDR.BILL_FLG;
        WS_OUTPUT.WS_TAX_FLG = VTRPRDR.TAX_FLG;
        WS_OUTPUT.WS_TAX_TYPE = VTRPRDR.TAX_TYPE;
        WS_OUTPUT.WS_FREE_TYPE = VTRPRDR.FREE_TYPE;
        WS_OUTPUT.WS_BILL_LIM = VTRPRDR.BILL_LIM;
        WS_OUTPUT.WS_DESC = VTRPRDR.RMK;
        WS_OUTPUT.WS_CNTR_TYPE = VTRPRDR.KEY.CNTR_TYPE;
        WS_OUTPUT.WS_VAT_TYPE = VTRPRDR.VAT_TYPE;
        if (!VTRPRDR.KEY.CNTR_TYPE.equalsIgnoreCase("FEES")) {
            IBS.init(SCCGWA, BPCPQPRD);
            BPCPQPRD.PRDT_CODE = VTRPRDR.KEY.PROD_CD;
            S010_CALL_BPZPQPRD();
            if (pgmRtn) return;
            WS_OUTPUT.WS_PROD_NM = BPCPQPRD.PRDT_NAME;
        } else {
            IBS.init(SCCGWA, BPCOUBAS);
            BPCOUBAS.FUNC = 'I';
            BPCOUBAS.KEY.FEE_CODE = VTRPRDR.KEY.PROD_CD;
            S000_CALL_BPZFUBAS();
            if (pgmRtn) return;
            WS_OUTPUT.WS_PROD_NM = BPCOUBAS.VAL.FEE_DESC;
            CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_BRW_PROD_NM);
        }
        IBS.init(SCCGWA, VTRVTCD);
        IBS.init(SCCGWA, VTCRVTCD);
        VTRVTCD.KEY.CODE = VTRPRDR.KEY.CODE;
        VTCRVTCD.FUNC = 'Q';
        VTCRVTCD.POINTER = VTRVTCD;
        VTCRVTCD.REC_LEN = 206;
        S000_CALL_VTZRVTCD();
        if (pgmRtn) return;
        WS_OUTPUT.WS_RT = VTRVTCD.RT;
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_RT);
        SCCFMT.FMTID = "VT020";
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 345;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R00_BRW_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        IBS.init(SCCGWA, WS_BROWSE_OUTPUT);
        WS_BROWSE_OUTPUT.WS_BRW_PROD_CD = VTRPRDR.KEY.PROD_CD;
        WS_BROWSE_OUTPUT.WS_BRW_BR = VTRPRDR.KEY.BR;
        WS_BROWSE_OUTPUT.WS_BRW_CCY = VTRPRDR.KEY.CCY;
        WS_BROWSE_OUTPUT.WS_BRW_CODE = VTRPRDR.KEY.CODE;
        WS_BROWSE_OUTPUT.WS_BRW_VAT_TYPE = VTRPRDR.VAT_TYPE;
        WS_BROWSE_OUTPUT.WS_BRW_BILL_FLG = VTRPRDR.BILL_FLG;
        WS_BROWSE_OUTPUT.WS_BRW_TAX_FLG = VTRPRDR.TAX_FLG;
        WS_BROWSE_OUTPUT.WS_BRW_TAX_TYPE = VTRPRDR.TAX_TYPE;
        WS_BROWSE_OUTPUT.WS_BRW_FREE_TYPE = VTRPRDR.FREE_TYPE;
        WS_BROWSE_OUTPUT.WS_BRW_BILL_LIM = VTRPRDR.BILL_LIM;
        WS_BROWSE_OUTPUT.WS_BRW_DESC = VTRPRDR.RMK;
        WS_BROWSE_OUTPUT.WS_BRW_STS = VTRPRDR.STS;
        WS_BROWSE_OUTPUT.WS_BRW_CNTR_TYPE = VTRPRDR.KEY.CNTR_TYPE;
        if (!VTRPRDR.KEY.CNTR_TYPE.equalsIgnoreCase("FEES")) {
            IBS.init(SCCGWA, BPCPQPRD);
            BPCPQPRD.PRDT_CODE = VTRPRDR.KEY.PROD_CD;
            S010_CALL_BPZPQPRD();
            if (pgmRtn) return;
            WS_OUTPUT.WS_PROD_NM = BPCPQPRD.PRDT_NAME;
        } else {
            IBS.init(SCCGWA, BPCOUBAS);
            BPCOUBAS.FUNC = 'I';
            BPCOUBAS.KEY.FEE_CODE = VTRPRDR.KEY.PROD_CD;
            S000_CALL_BPZFUBAS();
            if (pgmRtn) return;
            WS_OUTPUT.WS_PROD_NM = BPCOUBAS.VAL.FEE_DESC;
            CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_BRW_PROD_NM);
        }
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_BROWSE_OUTPUT);
        SCCMPAG.DATA_LEN = 338;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_SAVE_NEW_DATA() throws IOException,SQLException,Exception {
        VTRNPRDR.KEY.CNTR_TYPE = VTCSPRDR.CNTR_TYPE;
        VTRNPRDR.KEY.PROD_CD = VTCSPRDR.PROD_CD;
        VTRNPRDR.KEY.BR = VTCSPRDR.BR;
        VTRNPRDR.KEY.CCY = VTCSPRDR.CCY;
        VTRNPRDR.KEY.CODE = VTCSPRDR.CODE;
        VTRNPRDR.BR_RGN = VTCSPRDR.BR_RGN;
        VTRNPRDR.BILL_FLG = VTCSPRDR.BILL_FLG;
        VTRNPRDR.TAX_FLG = VTCSPRDR.TAX_FLG;
        VTRNPRDR.TAX_TYPE = VTCSPRDR.TAX_TYPE;
        VTRNPRDR.FREE_TYPE = VTCSPRDR.FREE_TYPE;
        VTRNPRDR.BILL_LIM = VTCSPRDR.BILL_LIM;
        VTRNPRDR.RMK = VTCSPRDR.DESC;
        VTRNPRDR.OPEN_TLR = SCCGWA.COMM_AREA.TL_ID;
        VTRNPRDR.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        VTRNPRDR.VAT_TYPE = VTCSPRDR.VAT_TYPE;
        if (VTCSPRDR.FUNC == 'A'
            || VTCSPRDR.FUNC == 'M'
            || VTCSPRDR.FUNC == 'R') {
            VTRNPRDR.STS = 'N';
        } else if (VTCSPRDR.FUNC == 'D') {
            VTRNPRDR.STS = 'D';
        } else {
            WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_FUNC_CODE_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_SAVE_OLD_DATA() throws IOException,SQLException,Exception {
        VTROPRDR.KEY.CNTR_TYPE = VTRPRDR.KEY.CNTR_TYPE;
        VTROPRDR.KEY.PROD_CD = VTRPRDR.KEY.PROD_CD;
        VTROPRDR.KEY.BR = VTRPRDR.KEY.BR;
        VTROPRDR.KEY.CCY = VTRPRDR.KEY.CCY;
        VTROPRDR.KEY.CODE = VTRPRDR.KEY.CODE;
        VTROPRDR.BR_RGN = VTRPRDR.BR_RGN;
        VTROPRDR.BILL_FLG = VTRPRDR.BILL_FLG;
        VTROPRDR.TAX_FLG = VTRPRDR.TAX_FLG;
        VTROPRDR.TAX_TYPE = VTRPRDR.TAX_TYPE;
        VTROPRDR.FREE_TYPE = VTRPRDR.FREE_TYPE;
        VTROPRDR.BILL_LIM = VTRPRDR.BILL_LIM;
        VTROPRDR.RMK = VTRPRDR.RMK;
        VTROPRDR.OPEN_TLR = VTRPRDR.OPEN_TLR;
        VTROPRDR.OPEN_DATE = VTRPRDR.OPEN_DATE;
        VTROPRDR.STS = VTRPRDR.STS;
        VTROPRDR.VAT_TYPE = VTRPRDR.VAT_TYPE;
    }
    public void H000_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        if (VTCSPRDR.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
            BPCPNHIS.INFO.TX_RMK = "ADD VTTPRDR INFO";
        } else if (VTCSPRDR.FUNC == 'M'
            || VTCSPRDR.FUNC == 'R') {
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.TX_RMK = "CHANGE VTTPRDR INFO";
        } else if (VTCSPRDR.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
            BPCPNHIS.INFO.TX_RMK = "DELETE VTTPRDR INFO";
        } else {
            WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_FUNC_CODE_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID = "VT020";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.FMT_ID_LEN = 345;
        BPCPNHIS.INFO.OLD_DAT_PT = VTROPRDR;
        BPCPNHIS.INFO.NEW_DAT_PT = VTRNPRDR;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_VTZRACCT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "VT-R-ACCT-MAINTAIN", VTCRACCT);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQRGC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-RGNC", BPCPQRGC);
        if (BPCPQRGC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQRGC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_VTZRPRDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "VT-R-PRDR-MAINTAIN", VTCRPRDR);
    }
    public void S000_CALL_VTZRVTCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "VT-R-TAX-MAINTAIN", VTCRVTCD);
    }
    public void S010_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        CEP.TRC(SCCGWA, BPCPQPRD.RC);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFUBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-U-MAINTAIN-FBAS", BPCOUBAS);
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_INQUIRE_CCY, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG);
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
