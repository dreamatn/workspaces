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

public class VTZSJMDR {
    int JIBS_tmp_int;
    String JIBS_NumStr;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_P_INQ_ORG_LOW = "BP-P-INQ-ORG-LOW";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY";
    String WS_ERR_MSG = " ";
    short WS_CNT = 0;
    short WS_LEN = 0;
    VTZSJMDR_WS_OUTPUT WS_OUTPUT = new VTZSJMDR_WS_OUTPUT();
    VTZSJMDR_WS_BROWSE_OUTPUT WS_BROWSE_OUTPUT = new VTZSJMDR_WS_BROWSE_OUTPUT();
    char WS_FND_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    VTCMSG_ERROR_MSG VTCMSG_ERROR_MSG = new VTCMSG_ERROR_MSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCSUBS SCCSUBS = new SCCSUBS();
    VTRJMDR VTROJMDR = new VTRJMDR();
    VTRJMDR VTRNJMDR = new VTRJMDR();
    SCCMPAG SCCMPAG = new SCCMPAG();
    VTRJMDR VTRJMDR = new VTRJMDR();
    VTCRJMDR VTCRJMDR = new VTCRJMDR();
    VTRJMCD VTRJMCD = new VTRJMCD();
    VTCRJMCD VTCRJMCD = new VTCRJMCD();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCPQRGC BPCPQRGC = new BPCPQRGC();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCOFBAS BPCOUBAS = new BPCOFBAS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    VTCSJMDR VTCSJMDR;
    public void MP(SCCGWA SCCGWA, VTCSJMDR VTCSJMDR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.VTCSJMDR = VTCSJMDR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "VTZSJMDR return!");
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
        if (VTCSJMDR.FUNC == 'B') {
            B020_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else if (VTCSJMDR.FUNC == 'A') {
            B040_ADD_PROCESS();
            if (pgmRtn) return;
        } else if (VTCSJMDR.FUNC == 'Q') {
            B030_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (VTCSJMDR.FUNC == 'M') {
            B050_MODIFY_RECORD();
            if (pgmRtn) return;
        } else if (VTCSJMDR.FUNC == 'D') {
            B070_DELETE_RECORD();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_FUNC_CODE_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (VTCSJMDR.FUNC != 'B') {
            R000_JMDR_INFO_OUTPUT();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (VTCSJMDR.FUNC == 'A') {
            if (VTCSJMDR.JM_CODE.trim().length() > 0) {
                IBS.init(SCCGWA, VTRJMCD);
                IBS.init(SCCGWA, VTCRJMCD);
                CEP.TRC(SCCGWA, VTCSJMDR.JM_CODE);
                VTRJMCD.KEY.JM_CODE = VTCSJMDR.JM_CODE;
                VTCRJMCD.FUNC = 'Q';
                CEP.TRC(SCCGWA, VTRJMCD.KEY.JM_CODE);
                VTCRJMCD.POINTER = VTRJMCD;
                VTCRJMCD.REC_LEN = 221;
                S000_CALL_VTZRJMCD();
                if (pgmRtn) return;
                if (VTCRJMCD.RETURN_INFO == 'N') {
                    WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_JM_CODE_NOT_FOUND;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            IBS.init(SCCGWA, BPCPQPRD);
            BPCPQPRD.PRDT_CODE = VTCSJMDR.PROD_CD;
            S010_CALL_BPZPQPRD();
            if (pgmRtn) return;
            if (BPCPQPRD.RC.RC_CODE != 0) {
                WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_PROD_NOT_FOUND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, BPCPQPRD.RC);
            CEP.TRC(SCCGWA, BPCOUBAS.MSG_CODE);
            if (VTCSJMDR.CCY.trim().length() > 0 
                && !VTCSJMDR.CCY.equalsIgnoreCase("999")) {
                IBS.init(SCCGWA, BPCQCCY);
                BPCQCCY.DATA.CCY = VTCSJMDR.CCY;
                S000_CALL_BPZQCCY();
                if (pgmRtn) return;
            }
            if (VTCSJMDR.BR != 999999) {
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BR = VTCSJMDR.BR;
                S000_CALL_BPZPQORG();
                if (pgmRtn) return;
            }
        }
        if (VTCSJMDR.FUNC == 'A' 
            || VTCSJMDR.FUNC == 'M') {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, VTCSJMDR.OTH);
            if (JIBS_tmp_str[0].substring(2 - 1, 2 + 1 - 1).trim().length() > 0) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, VTCSJMDR.OTH);
                JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, VTCSJMDR.OTH);
                if (JIBS_tmp_str[0].substring(3 - 1, 3 + 1 - 1).trim().length() > 0 
                    || JIBS_tmp_str[1].substring(4 - 1, 4 + 1 - 1).trim().length() > 0) {
                    WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_THREE_OLNY_INPUT_ONE;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, VTCSJMDR.OTH);
                    JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, VTCSJMDR.OTH);
                    if (JIBS_tmp_str[0].substring(6 - 1, 6 + 1 - 1).trim().length() > 0 
                        || JIBS_tmp_str[1].substring(7 - 1, 7 + 1 - 1).trim().length() > 0) {
                        WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_OVER_NCB_NO_M_INPUT;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, VTCSJMDR.OTH);
            if (JIBS_tmp_str[0].substring(3 - 1, 3 + 1 - 1).trim().length() > 0) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, VTCSJMDR.OTH);
                JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, VTCSJMDR.OTH);
                if (JIBS_tmp_str[0].substring(2 - 1, 2 + 1 - 1).trim().length() > 0 
                    || JIBS_tmp_str[1].substring(4 - 1, 4 + 1 - 1).trim().length() > 0) {
                    WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_THREE_OLNY_INPUT_ONE;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, VTCSJMDR.OTH);
                    JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, VTCSJMDR.OTH);
                    if (JIBS_tmp_str[0].substring(6 - 1, 6 + 1 - 1).trim().length() > 0 
                        || JIBS_tmp_str[1].substring(7 - 1, 7 + 1 - 1).trim().length() > 0) {
                        WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_OVER_NCB_NO_M_INPUT;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, VTCSJMDR.OTH);
            if (JIBS_tmp_str[0].substring(4 - 1, 4 + 1 - 1).trim().length() > 0) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, VTCSJMDR.OTH);
                JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, VTCSJMDR.OTH);
                if (JIBS_tmp_str[0].substring(2 - 1, 2 + 1 - 1).trim().length() > 0 
                    || JIBS_tmp_str[1].substring(3 - 1, 3 + 1 - 1).trim().length() > 0) {
                    WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_THREE_OLNY_INPUT_ONE;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, VTCSJMDR.OTH);
                    JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, VTCSJMDR.OTH);
                    if (JIBS_tmp_str[0].substring(6 - 1, 6 + 1 - 1).trim().length() > 0 
                        || JIBS_tmp_str[1].substring(7 - 1, 7 + 1 - 1).trim().length() > 0) {
                        WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_OVER_NCB_NO_M_INPUT;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, VTCSJMDR.OTH);
            if (JIBS_tmp_str[0].substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("2")) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, VTCSJMDR.OTH);
                if (!JIBS_tmp_str[0].substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("Y")) {
                    WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_NCB_FLG_M_INPUT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, VTCSJMDR.OTH);
                    JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, VTCSJMDR.OTH);
                    JIBS_tmp_str[2] = IBS.CLS2CPY(SCCGWA, VTCSJMDR.OTH);
                    JIBS_tmp_str[3] = IBS.CLS2CPY(SCCGWA, VTCSJMDR.OTH);
                    if (JIBS_tmp_str[0].substring(2 - 1, 2 + 1 - 1).trim().length() > 0 
                        || JIBS_tmp_str[1].substring(3 - 1, 3 + 1 - 1).trim().length() > 0 
                        || JIBS_tmp_str[2].substring(4 - 1, 4 + 1 - 1).trim().length() > 0 
                        || JIBS_tmp_str[3].substring(5 - 1, 5 + 1 - 1).trim().length() > 0) {
                        WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_JM_NO_M_INPUT;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, VTCSJMDR.OTH);
            if (!JIBS_tmp_str[0].substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("Y")) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, VTCSJMDR.OTH);
                if (JIBS_tmp_str[0].substring(6 - 1, 6 + 1 - 1).trim().length() == 0 
                    || JIBS_tmp_str[0].substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("0")) {
                    WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_OVER_FLG_M_INPUT;
                } else {
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, VTCSJMDR.OTH);
                    JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, VTCSJMDR.OTH);
                    JIBS_tmp_str[2] = IBS.CLS2CPY(SCCGWA, VTCSJMDR.OTH);
                    JIBS_tmp_str[3] = IBS.CLS2CPY(SCCGWA, VTCSJMDR.OTH);
                    if (JIBS_tmp_str[0].substring(2 - 1, 2 + 1 - 1).trim().length() > 0 
                        || JIBS_tmp_str[1].substring(3 - 1, 3 + 1 - 1).trim().length() > 0 
                        || JIBS_tmp_str[2].substring(4 - 1, 4 + 1 - 1).trim().length() > 0 
                        || JIBS_tmp_str[3].substring(5 - 1, 5 + 1 - 1).trim().length() > 0) {
                        WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_JM_NO_M_INPUT;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
            CEP.TRC(SCCGWA, VTCSJMDR.EFF_DATE);
            CEP.TRC(SCCGWA, VTCSJMDR.EXP_DATE);
            if ((VTCSJMDR.EFF_DATE == ' ' 
                || VTCSJMDR.EFF_DATE == 0) 
                || (VTCSJMDR.EXP_DATE == ' ' 
                || VTCSJMDR.EXP_DATE == 0)) {
                WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_DATE_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (VTCSJMDR.EFF_DATE > SCCGWA.COMM_AREA.AC_DATE 
                || VTCSJMDR.EXP_DATE < SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_CODE_IS_EXP;
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
        SCCMPAG.MAX_COL_NO = 339;
        SCCMPAG.SCR_ROW_CNT = 20;
        SCCMPAG.SCR_COL_CNT = 5;
        B_MPAG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, VTRJMDR);
        CEP.TRC(SCCGWA, VTCSJMDR.PROD_CD);
        CEP.TRC(SCCGWA, VTCSJMDR.JM_CODE);
        VTRJMDR.KEY.PROD_CD = VTCSJMDR.PROD_CD;
        VTRJMDR.JM_CODE = VTCSJMDR.JM_CODE;
        CEP.TRC(SCCGWA, VTRJMDR.KEY.PROD_CD);
        CEP.TRC(SCCGWA, VTRJMDR.JM_CODE);
        VTCRJMDR.FUNC = 'B';
        VTCRJMDR.OPT = 'S';
        VTCRJMDR.POINTER = VTRJMDR;
        VTCRJMDR.REC_LEN = 218;
        S000_CALL_VTZRJMDR();
        if (pgmRtn) return;
        VTCRJMDR.OPT = 'N';
        S000_CALL_VTZRJMDR();
        if (pgmRtn) return;
        while (VTCRJMDR.RETURN_INFO != 'N') {
            CEP.TRC(SCCGWA, VTRJMDR);
            R00_BRW_OUTPUT();
            if (pgmRtn) return;
            VTCRJMDR.OPT = 'N';
            S000_CALL_VTZRJMDR();
            if (pgmRtn) return;
        }
        VTCRJMDR.OPT = 'E';
        S000_CALL_VTZRJMDR();
        if (pgmRtn) return;
    }
    public void B040_ADD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, VTCRJMDR);
        IBS.init(SCCGWA, VTRJMDR);
        IBS.init(SCCGWA, VTROJMDR);
        IBS.init(SCCGWA, VTRNJMDR);
        CEP.TRC(SCCGWA, VTCSJMDR.PROD_CD);
        CEP.TRC(SCCGWA, VTCSJMDR.BR);
        CEP.TRC(SCCGWA, VTCSJMDR.CCY);
        CEP.TRC(SCCGWA, VTCSJMDR.OTH);
        CEP.TRC(SCCGWA, VTCSJMDR.JM_CODE);
        CEP.TRC(SCCGWA, VTCSJMDR.EFF_DATE);
        CEP.TRC(SCCGWA, VTCSJMDR.EXP_DATE);
        CEP.TRC(SCCGWA, VTCSJMDR.BILL_FLG);
        CEP.TRC(SCCGWA, VTCSJMDR.TAX_FLG);
        CEP.TRC(SCCGWA, VTCSJMDR.TAX_TYPE);
        CEP.TRC(SCCGWA, VTCSJMDR.FREE_TYPE);
        CEP.TRC(SCCGWA, VTCSJMDR.BILL_LIM);
        CEP.TRC(SCCGWA, VTCSJMDR.DESC);
        VTRJMDR.KEY.PROD_CD = VTCSJMDR.PROD_CD;
        VTRJMDR.KEY.BR = VTCSJMDR.BR;
        VTRJMDR.KEY.CCY = VTCSJMDR.CCY;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, VTCSJMDR.OTH);
        VTRJMDR.KEY.OTH = JIBS_tmp_str[0].substring(2 - 1, 2 + 49 - 1);
        VTRJMDR.JM_CODE = VTCSJMDR.JM_CODE;
        VTRJMDR.KEY.EFF_DATE = VTCSJMDR.EFF_DATE;
        VTRJMDR.KEY.EXP_DATE = VTCSJMDR.EXP_DATE;
        VTCRJMDR.FUNC = 'Q';
        VTCRJMDR.POINTER = VTRJMDR;
        VTCRJMDR.REC_LEN = 218;
        S000_CALL_VTZRJMDR();
        if (pgmRtn) return;
        if (VTCRJMDR.RETURN_INFO == 'F') {
            WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_JMAC_ALREADY_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        VTRJMDR.BILL_FLG = VTCSJMDR.BILL_FLG;
        VTRJMDR.TAX_FLG = VTCSJMDR.TAX_FLG;
        VTRJMDR.TAX_TYPE = VTCSJMDR.TAX_TYPE;
        VTRJMDR.FREE_TYPE = VTCSJMDR.FREE_TYPE;
        VTRJMDR.BILL_LIM = VTCSJMDR.BILL_LIM;
        VTRJMDR.RMK = VTCSJMDR.DESC;
        VTRJMDR.OPEN_TLR = SCCGWA.COMM_AREA.TL_ID;
        VTRJMDR.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        VTRJMDR.STS = 'N';
        CEP.TRC(SCCGWA, VTRJMDR.KEY.PROD_CD);
        CEP.TRC(SCCGWA, VTRJMDR.KEY.BR);
        CEP.TRC(SCCGWA, VTRJMDR.KEY.CCY);
        CEP.TRC(SCCGWA, VTRJMDR.KEY.OTH);
        CEP.TRC(SCCGWA, VTRJMDR.JM_CODE);
        CEP.TRC(SCCGWA, VTRJMDR.KEY.EFF_DATE);
        CEP.TRC(SCCGWA, VTRJMDR.KEY.EXP_DATE);
        CEP.TRC(SCCGWA, VTRJMDR.BILL_FLG);
        CEP.TRC(SCCGWA, VTRJMDR.TAX_FLG);
        CEP.TRC(SCCGWA, VTRJMDR.TAX_TYPE);
        CEP.TRC(SCCGWA, VTRJMDR.FREE_TYPE);
        CEP.TRC(SCCGWA, VTRJMDR.BILL_LIM);
        CEP.TRC(SCCGWA, VTRJMDR.RMK);
        CEP.TRC(SCCGWA, VTRJMDR.OPEN_TLR);
        CEP.TRC(SCCGWA, VTRJMDR.OPEN_DATE);
        CEP.TRC(SCCGWA, VTRJMDR.STS);
        VTCRJMDR.FUNC = 'C';
        VTCRJMDR.POINTER = VTRJMDR;
        VTCRJMDR.REC_LEN = 218;
        S000_CALL_VTZRJMDR();
        if (pgmRtn) return;
        S000_SAVE_NEW_DATA();
        if (pgmRtn) return;
        H000_HISTORY_PROCESS();
        if (pgmRtn) return;
    }
    public void B050_MODIFY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, VTCRJMDR);
        IBS.init(SCCGWA, VTRJMDR);
        IBS.init(SCCGWA, VTROJMDR);
        IBS.init(SCCGWA, VTRNJMDR);
        VTRJMDR.KEY.PROD_CD = VTCSJMDR.PROD_CD;
        VTRJMDR.KEY.BR = VTCSJMDR.BR;
        VTRJMDR.KEY.CCY = VTCSJMDR.CCY;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, VTCSJMDR.OTH);
        VTRJMDR.KEY.OTH = JIBS_tmp_str[0].substring(2 - 1, 2 + 49 - 1);
        VTRJMDR.KEY.EFF_DATE = VTCSJMDR.EFF_DATE;
        VTRJMDR.KEY.EXP_DATE = VTCSJMDR.EXP_DATE;
        VTCRJMDR.FUNC = 'Q';
        VTCRJMDR.POINTER = VTRJMDR;
        VTCRJMDR.REC_LEN = 218;
        S000_CALL_VTZRJMDR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, VTRJMDR.STS);
        VTCRJMDR.FUNC = 'R';
        VTCRJMDR.POINTER = VTRJMDR;
        VTCRJMDR.REC_LEN = 218;
        S000_CALL_VTZRJMDR();
        if (pgmRtn) return;
        S000_SAVE_OLD_DATA();
        if (pgmRtn) return;
        VTCRJMDR.FUNC = 'U';
        VTRJMDR.JM_CODE = VTCSJMDR.JM_CODE;
        VTRJMDR.BILL_FLG = VTCSJMDR.BILL_FLG;
        VTRJMDR.TAX_FLG = VTCSJMDR.TAX_FLG;
        VTRJMDR.TAX_TYPE = VTCSJMDR.TAX_TYPE;
        VTRJMDR.FREE_TYPE = VTCSJMDR.FREE_TYPE;
        VTRJMDR.BILL_LIM = VTCSJMDR.BILL_LIM;
        VTRJMDR.RMK = VTCSJMDR.DESC;
        VTRJMDR.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        VTRJMDR.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_VTZRJMDR();
        if (pgmRtn) return;
        S000_SAVE_NEW_DATA();
        if (pgmRtn) return;
        H000_HISTORY_PROCESS();
        if (pgmRtn) return;
    }
    public void B070_DELETE_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, VTCRJMDR);
        IBS.init(SCCGWA, VTRJMDR);
        IBS.init(SCCGWA, VTROJMDR);
        IBS.init(SCCGWA, VTRNJMDR);
        VTRJMDR.KEY.PROD_CD = VTCSJMDR.PROD_CD;
        VTRJMDR.KEY.BR = VTCSJMDR.BR;
        VTRJMDR.KEY.CCY = VTCSJMDR.CCY;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, VTCSJMDR.OTH);
        VTRJMDR.KEY.OTH = JIBS_tmp_str[0].substring(2 - 1, 2 + 49 - 1);
        VTRJMDR.KEY.EFF_DATE = VTCSJMDR.EFF_DATE;
        VTRJMDR.KEY.EXP_DATE = VTCSJMDR.EXP_DATE;
        VTRJMDR.JM_CODE = VTCSJMDR.JM_CODE;
        VTCRJMDR.FUNC = 'Q';
        VTCRJMDR.POINTER = VTRJMDR;
        VTCRJMDR.REC_LEN = 218;
        S000_CALL_VTZRJMDR();
        if (pgmRtn) return;
        VTCRJMDR.FUNC = 'R';
        VTCRJMDR.POINTER = VTRJMDR;
        VTCRJMDR.REC_LEN = 218;
        S000_CALL_VTZRJMDR();
        if (pgmRtn) return;
        S000_SAVE_OLD_DATA();
        if (pgmRtn) return;
        VTCRJMDR.FUNC = 'D';
        S000_CALL_VTZRJMDR();
        if (pgmRtn) return;
        S000_SAVE_OLD_DATA();
        if (pgmRtn) return;
        H000_HISTORY_PROCESS();
        if (pgmRtn) return;
    }
    public void B030_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, VTRJMDR);
        IBS.init(SCCGWA, VTCRJMDR);
        VTRJMDR.KEY.PROD_CD = VTCSJMDR.PROD_CD;
        VTRJMDR.KEY.BR = VTCSJMDR.BR;
        VTRJMDR.KEY.CCY = VTCSJMDR.CCY;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, VTCSJMDR.OTH);
        VTRJMDR.KEY.OTH = JIBS_tmp_str[0].substring(2 - 1, 2 + 49 - 1);
        VTRJMDR.KEY.EFF_DATE = VTCSJMDR.EFF_DATE;
        VTRJMDR.KEY.EXP_DATE = VTCSJMDR.EXP_DATE;
        VTRJMDR.JM_CODE = VTCSJMDR.JM_CODE;
        VTCRJMDR.FUNC = 'Q';
        VTCRJMDR.POINTER = VTRJMDR;
        VTCRJMDR.REC_LEN = 218;
        S000_CALL_VTZRJMDR();
        if (pgmRtn) return;
        if (VTCRJMDR.RETURN_INFO == 'N') {
            WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_TAX_JMDR_NOT_FOUND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_JMDR_INFO_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        IBS.init(SCCGWA, WS_OUTPUT);
        WS_OUTPUT.WS_PROD_CD = VTRJMDR.KEY.PROD_CD;
        WS_OUTPUT.WS_BR = VTRJMDR.KEY.BR;
        WS_OUTPUT.WS_CCY = VTRJMDR.KEY.CCY;
        if (VTRJMDR.KEY.OTH == null) VTRJMDR.KEY.OTH = "";
        JIBS_tmp_int = VTRJMDR.KEY.OTH.length();
        for (int i=0;i<50-JIBS_tmp_int;i++) VTRJMDR.KEY.OTH += " ";
        WS_OUTPUT.WS_WEI_FLG = VTRJMDR.KEY.OTH.substring(2 - 1, 2 + 1 - 1).charAt(0);
        if (VTRJMDR.KEY.OTH == null) VTRJMDR.KEY.OTH = "";
        JIBS_tmp_int = VTRJMDR.KEY.OTH.length();
        for (int i=0;i<50-JIBS_tmp_int;i++) VTRJMDR.KEY.OTH += " ";
        WS_OUTPUT.WS_FARM_FLG = VTRJMDR.KEY.OTH.substring(3 - 1, 3 + 1 - 1).charAt(0);
        if (VTRJMDR.KEY.OTH == null) VTRJMDR.KEY.OTH = "";
        JIBS_tmp_int = VTRJMDR.KEY.OTH.length();
        for (int i=0;i<50-JIBS_tmp_int;i++) VTRJMDR.KEY.OTH += " ";
        WS_OUTPUT.WS_IIC_FLG = VTRJMDR.KEY.OTH.substring(4 - 1, 4 + 1 - 1).charAt(0);
        if (VTRJMDR.KEY.OTH == null) VTRJMDR.KEY.OTH = "";
        JIBS_tmp_int = VTRJMDR.KEY.OTH.length();
        for (int i=0;i<50-JIBS_tmp_int;i++) VTRJMDR.KEY.OTH += " ";
        WS_OUTPUT.WS_OVER_FLG = VTRJMDR.KEY.OTH.substring(6 - 1, 6 + 1 - 1).charAt(0);
        if (VTRJMDR.KEY.OTH == null) VTRJMDR.KEY.OTH = "";
        JIBS_tmp_int = VTRJMDR.KEY.OTH.length();
        for (int i=0;i<50-JIBS_tmp_int;i++) VTRJMDR.KEY.OTH += " ";
        WS_OUTPUT.WS_NCB_FLG = VTRJMDR.KEY.OTH.substring(7 - 1, 7 + 1 - 1).charAt(0);
        JIBS_NumStr = "" + VTRJMDR.KEY.EFF_DATE;
        WS_OUTPUT.WS_EFF_DATE = JIBS_NumStr.charAt(0);
        JIBS_NumStr = "" + VTRJMDR.KEY.EXP_DATE;
        WS_OUTPUT.WS_EXP_DATE = JIBS_NumStr.charAt(0);
        WS_OUTPUT.WS_CODE = VTRJMDR.JM_CODE;
        WS_OUTPUT.WS_BILL_FLG = VTRJMDR.BILL_FLG;
        WS_OUTPUT.WS_TAX_FLG = VTRJMDR.TAX_FLG;
        WS_OUTPUT.WS_TAX_TYPE = VTRJMDR.TAX_TYPE;
        WS_OUTPUT.WS_FREE_TYPE = VTRJMDR.FREE_TYPE;
        WS_OUTPUT.WS_BILL_LIM = VTRJMDR.BILL_LIM;
        WS_OUTPUT.WS_DESC = VTRJMDR.RMK;
        SCCFMT.FMTID = "VT021";
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 340;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R00_BRW_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        IBS.init(SCCGWA, WS_BROWSE_OUTPUT);
        WS_BROWSE_OUTPUT.WS_BRW_PROD_CD = VTRJMDR.KEY.PROD_CD;
        WS_BROWSE_OUTPUT.WS_BRW_BR = VTRJMDR.KEY.BR;
        WS_BROWSE_OUTPUT.WS_BRW_CCY = VTRJMDR.KEY.CCY;
        if (VTRJMDR.KEY.OTH == null) VTRJMDR.KEY.OTH = "";
        JIBS_tmp_int = VTRJMDR.KEY.OTH.length();
        for (int i=0;i<50-JIBS_tmp_int;i++) VTRJMDR.KEY.OTH += " ";
        WS_OUTPUT.WS_WEI_FLG = VTRJMDR.KEY.OTH.substring(2 - 1, 2 + 1 - 1).charAt(0);
        if (VTRJMDR.KEY.OTH == null) VTRJMDR.KEY.OTH = "";
        JIBS_tmp_int = VTRJMDR.KEY.OTH.length();
        for (int i=0;i<50-JIBS_tmp_int;i++) VTRJMDR.KEY.OTH += " ";
        WS_OUTPUT.WS_FARM_FLG = VTRJMDR.KEY.OTH.substring(3 - 1, 3 + 1 - 1).charAt(0);
        if (VTRJMDR.KEY.OTH == null) VTRJMDR.KEY.OTH = "";
        JIBS_tmp_int = VTRJMDR.KEY.OTH.length();
        for (int i=0;i<50-JIBS_tmp_int;i++) VTRJMDR.KEY.OTH += " ";
        WS_OUTPUT.WS_IIC_FLG = VTRJMDR.KEY.OTH.substring(4 - 1, 4 + 1 - 1).charAt(0);
        if (VTRJMDR.KEY.OTH == null) VTRJMDR.KEY.OTH = "";
        JIBS_tmp_int = VTRJMDR.KEY.OTH.length();
        for (int i=0;i<50-JIBS_tmp_int;i++) VTRJMDR.KEY.OTH += " ";
        WS_OUTPUT.WS_OVER_FLG = VTRJMDR.KEY.OTH.substring(6 - 1, 6 + 1 - 1).charAt(0);
        if (VTRJMDR.KEY.OTH == null) VTRJMDR.KEY.OTH = "";
        JIBS_tmp_int = VTRJMDR.KEY.OTH.length();
        for (int i=0;i<50-JIBS_tmp_int;i++) VTRJMDR.KEY.OTH += " ";
        WS_OUTPUT.WS_NCB_FLG = VTRJMDR.KEY.OTH.substring(7 - 1, 7 + 1 - 1).charAt(0);
        WS_BROWSE_OUTPUT.WS_BRW_CODE = VTRJMDR.JM_CODE;
        WS_BROWSE_OUTPUT.WS_BRW_BILL_FLG = VTRJMDR.BILL_FLG;
        WS_BROWSE_OUTPUT.WS_BRW_TAX_FLG = VTRJMDR.TAX_FLG;
        WS_BROWSE_OUTPUT.WS_BRW_TAX_TYPE = VTRJMDR.TAX_TYPE;
        WS_BROWSE_OUTPUT.WS_BRW_FREE_TYPE = VTRJMDR.FREE_TYPE;
        WS_BROWSE_OUTPUT.WS_BRW_BILL_LIM = VTRJMDR.BILL_LIM;
        WS_BROWSE_OUTPUT.WS_BRW_DESC = VTRJMDR.RMK;
        WS_BROWSE_OUTPUT.WS_BRW_STS = VTRJMDR.STS;
        SCCMPAG.FUNC = 'D';
        SCCFMT.FMTID = "VT020";
        CEP.TRC(SCCGWA, VTRJMDR.KEY.OTH);
        CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_BRW_WEI_FLG);
        CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_BRW_FARM_FLG);
        CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_BRW_IIC_FLG);
        CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_BRW_OVER_FLG);
        CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_BRW_NCB_FLG);
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_BROWSE_OUTPUT);
        SCCMPAG.DATA_LEN = 339;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_SAVE_NEW_DATA() throws IOException,SQLException,Exception {
        VTRNJMDR.KEY.PROD_CD = VTCSJMDR.PROD_CD;
        VTRNJMDR.KEY.BR = VTCSJMDR.BR;
        VTRNJMDR.KEY.CCY = VTCSJMDR.CCY;
        VTRNJMDR.KEY.OTH = IBS.CLS2CPY(SCCGWA, VTCSJMDR.OTH);
        VTRNJMDR.KEY.EFF_DATE = VTCSJMDR.EFF_DATE;
        VTRNJMDR.KEY.EXP_DATE = VTCSJMDR.EXP_DATE;
        VTRNJMDR.JM_CODE = VTCSJMDR.JM_CODE;
        VTRNJMDR.BILL_FLG = VTCSJMDR.BILL_FLG;
        VTRNJMDR.TAX_FLG = VTCSJMDR.TAX_FLG;
        VTRNJMDR.TAX_TYPE = VTCSJMDR.TAX_TYPE;
        VTRNJMDR.FREE_TYPE = VTCSJMDR.FREE_TYPE;
        VTRNJMDR.BILL_LIM = VTCSJMDR.BILL_LIM;
        VTRNJMDR.RMK = VTCSJMDR.DESC;
        VTRNJMDR.OPEN_TLR = SCCGWA.COMM_AREA.TL_ID;
        VTRNJMDR.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        if (VTCSJMDR.FUNC == 'A'
            || VTCSJMDR.FUNC == 'M') {
            VTRNJMDR.STS = 'N';
        } else {
            WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_FUNC_CODE_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_SAVE_OLD_DATA() throws IOException,SQLException,Exception {
        VTROJMDR.KEY.PROD_CD = VTRJMDR.KEY.PROD_CD;
        VTROJMDR.KEY.BR = VTRJMDR.KEY.BR;
        VTROJMDR.KEY.CCY = VTRJMDR.KEY.CCY;
        VTROJMDR.KEY.OTH = VTRJMDR.KEY.OTH;
        VTROJMDR.KEY.EFF_DATE = VTRJMDR.KEY.EFF_DATE;
        VTROJMDR.KEY.EXP_DATE = VTRJMDR.KEY.EXP_DATE;
        VTROJMDR.JM_CODE = VTRJMDR.JM_CODE;
        VTROJMDR.BILL_FLG = VTRJMDR.BILL_FLG;
        VTROJMDR.TAX_FLG = VTRJMDR.TAX_FLG;
        VTROJMDR.TAX_TYPE = VTRJMDR.TAX_TYPE;
        VTROJMDR.FREE_TYPE = VTRJMDR.FREE_TYPE;
        VTROJMDR.BILL_LIM = VTRJMDR.BILL_LIM;
        VTROJMDR.RMK = VTRJMDR.RMK;
        VTROJMDR.OPEN_TLR = VTRJMDR.OPEN_TLR;
        VTROJMDR.OPEN_DATE = VTRJMDR.OPEN_DATE;
        VTROJMDR.STS = VTRJMDR.STS;
    }
    public void H000_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        if (VTCSJMDR.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
            BPCPNHIS.INFO.TX_RMK = "ADD VTTJMDR INFO";
        } else if (VTCSJMDR.FUNC == 'M') {
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.TX_RMK = "CHANGE VTTJMDR INFO";
        } else if (VTCSJMDR.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
            BPCPNHIS.INFO.TX_RMK = "DELETE VTTJMDR INFO";
        } else {
            WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_FUNC_CODE_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID = "VT020";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.FMT_ID_LEN = 340;
        BPCPNHIS.INFO.OLD_DAT_PT = VTROJMDR;
        BPCPNHIS.INFO.NEW_DAT_PT = VTRNJMDR;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_VTZRJMDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "VT-R-JMDR-MAINTAIN", VTCRJMDR);
    }
    public void S000_CALL_VTZRJMCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "VT-R-JMCD-MAINTAIN", VTCRJMCD);
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
