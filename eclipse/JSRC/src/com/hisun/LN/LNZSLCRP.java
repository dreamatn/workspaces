package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.AI.*;
import com.hisun.DD.*;
import com.hisun.IB.*;
import com.hisun.BP.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSLCRP {
    BigDecimal bigD;
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm LNTPARS_RD;
    DBParm LNTSETL_RD;
    boolean pgmRtn = false;
    String K_DD_AC = "01";
    String K_INTERNAL = "02";
    String K_IB_AC = "03";
    String K_DC_AC = "05";
    String K_EB_AC = "06";
    String K_CNY = "156";
    String K_CLDD = "CLDD";
    String K_MMO = "12010001";
    short WS_I = 0;
    short WS_J = 0;
    double WS_SUM_PCT = 0;
    short WS_ADJ_NUM = 0;
    short WS_MBANK_NUM = 0;
    String WS_YHS_AC = " ";
    double WS_YHS_AMT = 0;
    short WS_CONT_SEQ = 0;
    short WS_PART_NUM = 0;
    short WS_ADJ_SEQ = 0;
    double WS_TOT_AMT = 0;
    String WS_ACCOUNT_AC = " ";
    String WS_ACCOUNT_AC_TYP = " ";
    double WS_ACCOUNT_AMT = 0;
    String WS_AC_TYP = " ";
    LNZSLCRP_WS_AI_AC WS_AI_AC = new LNZSLCRP_WS_AI_AC();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    LNCTRPRM LNCTRPRM = new LNCTRPRM();
    CICQACRI CICQACRI = new CICQACRI();
    AICUUPIA AICUUPIA = new AICUUPIA();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    IBCPOSTA IBCPOSTA = new IBCPOSTA();
    BPCQCNGL BPCQCNGL = new BPCQCNGL();
    BPCACLDD BPCACLDD = new BPCACLDD();
    BPCUCNGM BPCUCNGM = new BPCUCNGM();
    BPCPQORG BPCPQORG = new BPCPQORG();
    LNRPARS LNRPARS = new LNRPARS();
    LNRSETL LNRSETL = new LNRSETL();
    SCCGWA SCCGWA;
    LNCSLCRP LNCSLCRP;
    public void MP(SCCGWA SCCGWA, LNCSLCRP LNCSLCRP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSLCRP = LNCSLCRP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSLCRP return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        LNCSLCRP.RC.RC_MMO = "LN";
        LNCSLCRP.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHK_PART_INF();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            B600_MODIFY_PART_INF();
            if (pgmRtn) return;
            B700_NOTIFI_SMS_PROC();
            if (pgmRtn) return;
        } else {
            B300_CREATE_PART_INF();
            if (pgmRtn) return;
            if (LNCSLCRP.SYN_TYP == 'O') {
                B400_CREATE_SETL_INF();
                if (pgmRtn) return;
            }
        }
    }
    public void B100_CHK_PART_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_SUM_PCT);
        CEP.TRC(SCCGWA, LNCSLCRP.PART_INF[1-1].PART_PCT);
        CEP.TRC(SCCGWA, LNCSLCRP.PART_INF[2-1].PART_PCT);
        CEP.TRC(SCCGWA, LNCSLCRP.PART_INF[3-1].PART_PCT);
        CEP.TRC(SCCGWA, LNCSLCRP.PART_INF[4-1].PART_PCT);
        CEP.TRC(SCCGWA, LNCSLCRP.PART_INF[5-1].PART_PCT);
        CEP.TRC(SCCGWA, LNCSLCRP.PART_INF[6-1].PART_PCT);
        CEP.TRC(SCCGWA, LNCSLCRP.PART_INF[7-1].PART_PCT);
        CEP.TRC(SCCGWA, LNCSLCRP.PART_INF[8-1].PART_PCT);
        CEP.TRC(SCCGWA, LNCSLCRP.PART_INF[9-1].PART_PCT);
        CEP.TRC(SCCGWA, LNCSLCRP.PART_INF[10-1].PART_PCT);
        CEP.TRC(SCCGWA, LNCSLCRP.PART_INF[1-1].PART_PCT);
        CEP.TRC(SCCGWA, LNCSLCRP.PART_INF[2-1].PART_PCT);
        CEP.TRC(SCCGWA, LNCSLCRP.PART_INF[3-1].PART_PCT);
        CEP.TRC(SCCGWA, LNCSLCRP.PART_INF[4-1].PART_PCT);
        CEP.TRC(SCCGWA, LNCSLCRP.PART_INF[5-1].PART_PCT);
        CEP.TRC(SCCGWA, LNCSLCRP.PART_INF[6-1].PART_PCT);
        CEP.TRC(SCCGWA, LNCSLCRP.PART_INF[7-1].PART_PCT);
        CEP.TRC(SCCGWA, LNCSLCRP.PART_INF[8-1].PART_PCT);
        CEP.TRC(SCCGWA, LNCSLCRP.PART_INF[9-1].PART_PCT);
        CEP.TRC(SCCGWA, LNCSLCRP.PART_INF[10-1].PART_PCT);
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            WS_SUM_PCT += LNCSLCRP.PART_INF[WS_I-1].PART_PCT;
        }
        WS_CONT_SEQ = 1;
        WS_SUM_PCT = 0;
        for (WS_I = 1; WS_SUM_PCT != 100; WS_I += 1) {
            CEP.TRC(SCCGWA, WS_I);
            if (LNCSLCRP.PART_INF[WS_I-1].LOC_BANK == ' ') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_LOC_BANK_M_INPUT, LNCSLCRP.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (LNCSLCRP.PART_INF[WS_I-1].LOC_BANK != 'Y' 
                && LNCSLCRP.PART_INF[WS_I-1].LOC_BANK != 'N') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_LOC_BANK_VAL_ERR, LNCSLCRP.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (LNCSLCRP.PART_INF[WS_I-1].PART_PCT <= 0) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PART_PCT_MGT_ZERO, LNCSLCRP.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (LNCSLCRP.PART_INF[WS_I-1].REL_TYPE == ' ') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_REL_TYPE_M_INPUT, LNCSLCRP.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (LNCSLCRP.PART_INF[WS_I-1].REL_TYPE != 'P' 
                && LNCSLCRP.PART_INF[WS_I-1].REL_TYPE != 'M') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_REL_TYPE_VAL_ERR, LNCSLCRP.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (LNCSLCRP.PART_INF[WS_I-1].REL_TYPE == 'M') {
                WS_MBANK_NUM += 1;
                if (WS_MBANK_NUM > 1) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_MBANK_MAX_ONE, LNCSLCRP.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
            if (LNCSLCRP.SYN_TYP == 'I' 
                && LNCSLCRP.PART_INF[WS_I-1].LOC_BANK != 'Y') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_SYNI_M_LOC_BANK, LNCSLCRP.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (LNCSLCRP.SYN_TYP == 'O') {
                if (LNCSLCRP.PART_INF[WS_I-1].PART_NO == 0) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PART_NO_M_INPUT, LNCSLCRP.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                if (LNCSLCRP.PART_INF[WS_I-1].REL_TYPE == 'M' 
                    && LNCSLCRP.PART_INF[WS_I-1].LOC_BANK != 'Y') {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_M_MANAGE_BANK, LNCSLCRP.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                if (LNCSLCRP.PART_INF[WS_I-1].LOC_BANK == 'N') {
                    if (LNCSLCRP.PART_INF[WS_I-1].REL_TYPE != 'P') {
                        IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_MUST_PART_BANK, LNCSLCRP.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    }
                    if (LNCSLCRP.PART_INF[WS_I-1].PART_ACT.trim().length() == 0) {
                        IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_ACT_TYP_M_I, LNCSLCRP.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    }
                    CEP.TRC(SCCGWA, LNCSLCRP.PART_INF[WS_I-1].PART_ACT);
                    if (!LNCSLCRP.PART_INF[WS_I-1].PART_ACT.equalsIgnoreCase("01") 
                        && !LNCSLCRP.PART_INF[WS_I-1].PART_ACT.equalsIgnoreCase("02") 
                        && !LNCSLCRP.PART_INF[WS_I-1].PART_ACT.equalsIgnoreCase("03")) {
                        IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CTA_TYPE, LNCSLCRP.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    }
                    if (LNCSLCRP.PART_INF[WS_I-1].PART_AC.trim().length() == 0) {
                        IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_AC_MUST_INPUT, LNCSLCRP.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    }
                    IBS.init(SCCGWA, CICQACRI);
                    CICQACRI.DATA.AGR_NO = LNCSLCRP.PART_INF[WS_I-1].PART_AC;
                    CICQACRI.FUNC = 'A';
                    S000_CALL_CIZQACRI();
                    if (pgmRtn) return;
                    WS_J += 1;
                    if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("IB")) {
                        WS_AC_TYP = "03";
                    } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
                        WS_AC_TYP = "01";
                    } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
                        WS_AC_TYP = "01";
                    } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
                        WS_AC_TYP = "02";
                    } else {
                        IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_AC_TYP_NOT_MATCH, LNCSLCRP.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    }
                    if (!LNCSLCRP.PART_INF[WS_I-1].PART_ACT.equalsIgnoreCase(WS_AC_TYP)) {
                        IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_AC_AND_TYP_NMATCH, LNCSLCRP.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    }
                }
            }
            if (LNCSLCRP.PART_INF[WS_I-1].LOC_BANK == 'Y') {
                if (LNCSLCRP.PART_INF[WS_I-1].BR == 0) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PART_BR_M_INPUT, LNCSLCRP.RC);
                    Z_RET();
                    if (pgmRtn) return;
                } else {
                    IBS.init(SCCGWA, BPCPQORG);
                    BPCPQORG.BR = LNCSLCRP.PART_INF[WS_I-1].BR;
                    S000_CALL_BPZPQORG();
                    if (pgmRtn) return;
                    WS_J += 1;
                }
            }
            if (LNCSLCRP.PART_INF[WS_I-1].ADJ_FLG != 'Y' 
                && LNCSLCRP.PART_INF[WS_I-1].ADJ_FLG != 'N') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ADJ_FLG_MUST_Y_OR_N, LNCSLCRP.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (LNCSLCRP.PART_INF[WS_I-1].ADJ_FLG == 'Y') {
                WS_ADJ_NUM += 1;
                LNCSLCRP.PART_INF[WS_I-1].PART_CONT_NO = LNCSLCRP.CONT_NO + "0001";
                if (LNCSLCRP.SYN_TYP == 'I') {
                    LNCSLCRP.PART_INF[WS_I-1].PART_NO = 1;
                }
                WS_ADJ_SEQ = WS_I;
            } else {
                WS_CONT_SEQ += 1;
                LNCSLCRP.PART_INF[WS_I-1].PART_CONT_NO = LNCSLCRP.CONT_NO + WS_CONT_SEQ;
                if (LNCSLCRP.SYN_TYP == 'I') {
                    LNCSLCRP.PART_INF[WS_I-1].PART_NO = WS_CONT_SEQ;
                }
                LNCSLCRP.PART_INF[WS_I-1].PART_AMT = LNCSLCRP.CONT_AMT * LNCSLCRP.PART_INF[WS_I-1].PART_PCT / 100;
                bigD = new BigDecimal(LNCSLCRP.PART_INF[WS_I-1].PART_AMT);
                LNCSLCRP.PART_INF[WS_I-1].PART_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                WS_TOT_AMT += LNCSLCRP.PART_INF[WS_I-1].PART_AMT;
            }
            WS_SUM_PCT += LNCSLCRP.PART_INF[WS_I-1].PART_PCT;
            if (LNCSLCRP.PART_INF[WS_I-1].YHS_FLG != 'Y' 
                && LNCSLCRP.PART_INF[WS_I-1].YHS_FLG != 'N') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_YHS_FLG_MUST_Y_OR_N, LNCSLCRP.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (LNCSLCRP.PART_INF[WS_I-1].YHS_FLG == 'Y') {
                if (LNCSLCRP.PART_INF[WS_I-1].LOC_BANK != 'Y') {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_YHS_M_LOC_BANK, LNCSLCRP.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                if (LNCSLCRP.PART_INF[WS_I-1].YHS_AC.trim().length() == 0) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_YHS_AC_M_INPUT, LNCSLCRP.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                WS_J += 1;
            }
            WS_PART_NUM += 1;
            if (LNCSLCRP.PART_INF[WS_I-1].REL_TYPE == 'M' 
                && LNCSLCRP.PART_INF[WS_I-1].BR != LNCSLCRP.BOOK_BR) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_BR_NOT_EQ, LNCSLCRP.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (WS_ADJ_NUM != 1) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ADJ_NUM_MUST_ONE, LNCSLCRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        LNCSLCRP.PART_INF[WS_ADJ_SEQ-1].PART_AMT = LNCSLCRP.CONT_AMT - WS_TOT_AMT;
    }
    public void B200_YHS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCTRPRM);
        LNCTRPRM.COMM_DATA.FUNC = 'I';
        LNCTRPRM.COMM_DATA.TYP = "LNPRM";
        LNCTRPRM.COMM_DATA.CODE = "LN-YHS-RAT";
        S000_CALL_LNZTRPRM();
        if (pgmRtn) return;
        if (LNCTRPRM.COMM_DATA.DATA_TXT.STS != 'Y') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_YHS_RATE_INFO, LNCSLCRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        for (WS_I = 1; WS_I <= WS_PART_NUM; WS_I += 1) {
            if (LNCSLCRP.PART_INF[WS_I-1].YHS_FLG == 'Y') {
                WS_YHS_AC = LNCSLCRP.PART_INF[WS_I-1].YHS_AC;
                WS_YHS_AMT = LNCSLCRP.PART_INF[WS_I-1].PART_AMT * LNCTRPRM.COMM_DATA.DATA_TXT.CTL_TAX;
                bigD = new BigDecimal(WS_YHS_AMT);
                WS_YHS_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                CEP.TRC(SCCGWA, WS_YHS_AMT);
                LNCSLCRP.PART_INF[WS_I-1].YHS_AMT = WS_YHS_AMT;
                WS_ACCOUNT_AC_TYP = LNCSLCRP.PART_INF[WS_I-1].YHS_AC_TYP;
                WS_ACCOUNT_AC = LNCSLCRP.PART_INF[WS_I-1].YHS_AC;
                WS_ACCOUNT_AMT = WS_YHS_AMT;
                if (WS_ACCOUNT_AMT != 0) {
                    B210_ACCOUNT_PROC();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B300_CREATE_PART_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPARS);
        LNRPARS.KEY.CONTRACT_NO = LNCSLCRP.CONT_NO;
        LNRPARS.INNER_OUT_FLG = LNCSLCRP.SYN_TYP;
        LNRPARS.STATUS = 'A';
        for (WS_I = 1; WS_I <= WS_PART_NUM; WS_I += 1) {
            LNRPARS.LOCAL_BANK_FLAG = LNCSLCRP.PART_INF[WS_I-1].LOC_BANK;
            LNRPARS.KEY.SEQ_NO = LNCSLCRP.PART_INF[WS_I-1].PART_NO;
            LNRPARS.BOOK_BR = LNCSLCRP.PART_INF[WS_I-1].BR;
            LNRPARS.PARTI_NAME = LNCSLCRP.PART_INF[WS_I-1].PART_NM;
            LNRPARS.REL_TYPE = LNCSLCRP.PART_INF[WS_I-1].REL_TYPE;
            LNRPARS.SUB_LN_AC = LNCSLCRP.PART_INF[WS_I-1].PART_CONT_NO;
            LNRPARS.ADJ_BANK_FLAG = LNCSLCRP.PART_INF[WS_I-1].ADJ_FLG;
            LNRPARS.PARTI_PCT = LNCSLCRP.PART_INF[WS_I-1].PART_PCT;
            LNRPARS.PARTI_AMT = LNCSLCRP.PART_INF[WS_I-1].PART_AMT;
            LNRPARS.YHS_AMT = LNCSLCRP.PART_INF[WS_I-1].YHS_AMT;
            T000_WRITE_LNTPARS();
            if (pgmRtn) return;
        }
    }
    public void B400_CREATE_SETL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRSETL);
        LNRSETL.KEY.CONTRACT_NO = LNCSLCRP.CONT_NO;
        LNRSETL.KEY.CI_TYPE = 'P';
        LNRSETL.KEY.CCY = LNCSLCRP.CCY;
        LNRSETL.KEY.SETTLE_TYPE = 'C';
        LNRSETL.KEY.SEQ_NO = 0;
        LNRSETL.MWHD_AC_FLG = 'N';
        LNRSETL.AC_FLG = '0';
        LNRSETL.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRSETL.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRSETL.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRSETL.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        for (WS_I = 1; WS_I <= WS_PART_NUM; WS_I += 1) {
            if (LNCSLCRP.PART_INF[WS_I-1].LOC_BANK == 'N') {
                LNRSETL.KEY.PART_BK = "" + LNCSLCRP.PART_INF[WS_I-1].PART_NO;
                JIBS_tmp_int = LNRSETL.KEY.PART_BK.length();
                for (int i=0;i<8-JIBS_tmp_int;i++) LNRSETL.KEY.PART_BK = "0" + LNRSETL.KEY.PART_BK;
                CEP.TRC(SCCGWA, LNRSETL.KEY.PART_BK);
                LNRSETL.AC_TYP = LNCSLCRP.PART_INF[WS_I-1].PART_ACT;
                LNRSETL.AC = LNCSLCRP.PART_INF[WS_I-1].PART_AC;
                T000_WRITE_LNTSETL();
                if (pgmRtn) return;
            }
        }
    }
    public void B500_WRITE_GL_MASTER_PROC() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= WS_PART_NUM; WS_I += 1) {
            if (LNCSLCRP.PART_INF[WS_I-1].LOC_BANK == 'Y') {
                IBS.init(SCCGWA, BPCQCNGL);
                IBS.init(SCCGWA, BPCACLDD);
                BPCQCNGL.DAT.INPUT.CNTR_TYPE = LNCSLCRP.PRODMO;
                BPCQCNGL.DAT.INPUT.BR = LNCSLCRP.PART_INF[WS_I-1].BR;
                BPCACLDD.PROD_CD = LNCSLCRP.PROD_TYP;
                BPCQCNGL.DAT.INPUT.OTH_PTR_LEN = 31;
                BPCQCNGL.DAT.INPUT.OTH_PTR = BPCACLDD;
                S000_CALL_BPZQCNGL();
                if (pgmRtn) return;
                IBS.init(SCCGWA, BPCUCNGM);
                BPCUCNGM.KEY.AC = LNCSLCRP.PART_INF[WS_I-1].PART_CONT_NO;
                BPCUCNGM.KEY.REL_SEQ = " ";
                BPCUCNGM.KEY.CNTR_TYPE = LNCSLCRP.PRODMO;
                BPCUCNGM.PROD_TYPE = LNCSLCRP.PROD_TYP;
                BPCUCNGM.BR = LNCSLCRP.PART_INF[WS_I-1].BR;
                BPCUCNGM.MOD_NO = BPCQCNGL.DAT.OUTPUT.MOD_NO;
                BPCUCNGM.DATA[1-1].GLMST = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[1-1].MSTNO;
                BPCUCNGM.DATA[2-1].GLMST = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[2-1].MSTNO;
                BPCUCNGM.DATA[3-1].GLMST = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[3-1].MSTNO;
                BPCUCNGM.DATA[4-1].GLMST = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[4-1].MSTNO;
                BPCUCNGM.FUNC = 'A';
                S000_CALL_BPZUCNGM();
                if (pgmRtn) return;
            }
        }
    }
    public void B600_MODIFY_PART_INF() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= WS_PART_NUM; WS_I += 1) {
            IBS.init(SCCGWA, LNRPARS);
            LNRPARS.KEY.CONTRACT_NO = LNCSLCRP.CONT_NO;
            LNRPARS.KEY.SEQ_NO = LNCSLCRP.PART_INF[WS_I-1].PART_NO;
            LNRPARS.BOOK_BR = LNCSLCRP.PART_INF[WS_I-1].BR;
            T000_READUPD_LNTPARS();
            if (pgmRtn) return;
            LNRPARS.STATUS = 'D';
            T000_REWRITE_LNPARS();
            if (pgmRtn) return;
        }
    }
    public void B700_NOTIFI_SMS_PROC() throws IOException,SQLException,Exception {
    }
    public void B210_ACCOUNT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_ACCOUNT_AC_TYP);
        CEP.TRC(SCCGWA, WS_ACCOUNT_AC);
        if (WS_ACCOUNT_AC_TYP.equalsIgnoreCase(K_INTERNAL)) {
            B210_AI_IN_AC_D();
            if (pgmRtn) return;
        } else if (WS_ACCOUNT_AC_TYP.equalsIgnoreCase(K_DD_AC)
            || WS_ACCOUNT_AC_TYP.equalsIgnoreCase(K_DC_AC)) {
            B210_DD_AC_D();
            if (pgmRtn) return;
        } else if (WS_ACCOUNT_AC_TYP.equalsIgnoreCase(K_IB_AC)) {
            B210_IB_AC_D();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.SET_MET_NOT_RIGHT, LNCSLCRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B210_AI_IN_AC_D() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = WS_ACCOUNT_AC;
        AICUUPIA.DATA.CCY = LNCSLCRP.CCY;
        AICUUPIA.DATA.AMT = WS_ACCOUNT_AMT;
        AICUUPIA.DATA.VALUE_DATE = LNCSLCRP.START_DT;
        AICUUPIA.DATA.EVENT_CODE = "DR";
        S000_CALL_AIZUUPIA();
        if (pgmRtn) return;
    }
    public void B210_DD_AC_D() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUDRAC);
        DDCUDRAC.CHK_PSW_FLG = 'N';
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.BANK_DR_FLG = 'Y';
        DDCUDRAC.TX_AMT = WS_ACCOUNT_AMT;
        DDCUDRAC.CARD_NO = WS_ACCOUNT_AC;
        DDCUDRAC.CCY = LNCSLCRP.CCY;
        DDCUDRAC.VAL_DATE = LNCSLCRP.START_DT;
        DDCUDRAC.TX_MMO = K_MMO;
        DDCUDRAC.OTHER_AC = LNCSLCRP.CONT_NO;
        DDCUDRAC.OTHER_CCY = LNCSLCRP.CCY;
        DDCUDRAC.OTHER_AMT = WS_ACCOUNT_AMT;
        S000_CALL_DDZUDRAC();
        if (pgmRtn) return;
    }
    public void B210_IB_AC_D() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCPOSTA);
        IBCPOSTA.ACT_CTR = LNCSLCRP.BOOK_BR;
        IBCPOSTA.NOSTRO_CD = WS_ACCOUNT_AC;
        IBCPOSTA.CCY = LNCSLCRP.CCY;
        IBCPOSTA.AMT = WS_ACCOUNT_AMT;
        IBCPOSTA.TXTYPE = "01";
        IBCPOSTA.VAL_DATE = LNCSLCRP.START_DT;
        IBCPOSTA.ENTRY_FLG = '1';
        IBCPOSTA.SIGN = 'D';
        IBCPOSTA.OUR_REF = LNCSLCRP.CONT_NO;
        IBCPOSTA.THR_REF = LNCSLCRP.CONT_NO;
        S000_CALL_IBZDRAC();
        if (pgmRtn) return;
    }
    public void S000_CALL_LNZTRPRM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-TRAN-PARM-M", LNCTRPRM);
        if (LNCTRPRM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCTRPRM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSLCRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICQACRI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACRI.RC);
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSLCRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
        if (AICUUPIA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSLCRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC, true);
    }
    public void S000_CALL_IBZDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-DEBIT-AC", IBCPOSTA);
        if (IBCPOSTA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCPOSTA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSLCRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZQCNGL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-CNGL", BPCQCNGL);
        if (BPCQCNGL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCNGL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSLCRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZUCNGM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-MAINT-CNGM", BPCUCNGM);
        if (BPCUCNGM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUCNGM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSLCRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READUPD_LNTPARS() throws IOException,SQLException,Exception {
        LNTPARS_RD = new DBParm();
        LNTPARS_RD.TableName = "LNTPARS";
        LNTPARS_RD.upd = true;
        IBS.READ(SCCGWA, LNRPARS, LNTPARS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PARS_REC_NOTFND, LNCSLCRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_LNPARS() throws IOException,SQLException,Exception {
        LNRPARS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRPARS.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTPARS_RD = new DBParm();
        LNTPARS_RD.TableName = "LNTPARS";
        IBS.REWRITE(SCCGWA, LNRPARS, LNTPARS_RD);
    }
    public void T000_WRITE_LNTSETL() throws IOException,SQLException,Exception {
        LNTSETL_RD = new DBParm();
        LNTSETL_RD.TableName = "LNTSETL";
        LNTSETL_RD.errhdl = true;
        IBS.WRITE(SCCGWA, LNRSETL, LNTSETL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY, LNCSLCRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_LNTPARS() throws IOException,SQLException,Exception {
        LNRPARS.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRPARS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRPARS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRPARS.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTPARS_RD = new DBParm();
        LNTPARS_RD.TableName = "LNTPARS";
        LNTPARS_RD.errhdl = true;
        IBS.WRITE(SCCGWA, LNRPARS, LNTPARS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY, LNCSLCRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
