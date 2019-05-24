package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class TDZQPMP {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    DBParm TDTPMP_RD;
    DBParm BPTBCCY_RD;
    DBParm TDTPMPD_RD;
    brParm TDTPMPD_BR = new brParm();
    String K_AP_MMO = "TD";
    String WS_MSGID = " ";
    short WS_X = 0;
    short WS_CNT3 = 0;
    short WS_PMPD_SEQ = 0;
    short WS_I = 0;
    short WS_K = 0;
    short WS_J = 0;
    short WS_M = 0;
    short WS_C_A = 0;
    short WS_N = 0;
    short WS_T = 0;
    short WS_Y = 0;
    char WS_PMPD_FLG = ' ';
    char WS_CCY_PMPD_FLG = ' ';
    char WS_PMP_FLG = ' ';
    String WS_CCY = " ";
    String WS_CCY_CHK = " ";
    String WS_REF_CCY = " ";
    double WS_BUY_AMT = 0;
    double WS_SELL_AMT = 0;
    char WS_EXT_FLG = ' ';
    char WS_REF_CCY_FLG = ' ';
    char WS_REF_FLG = ' ';
    TDZQPMP_WS_CCY_ALL_LST WS_CCY_ALL_LST = new TDZQPMP_WS_CCY_ALL_LST();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    TDCSRDMA TDCSRDMA = new TDCSRDMA();
    TDCPRDP TDCPRDP = new TDCPRDP();
    TDRPMP TDRPMP = new TDRPMP();
    BPRBCCY BPRBCCY = new BPRBCCY();
    TDRPMPD TDRPMPD = new TDRPMPD();
    BPCRCCY BPCRCCY = new BPCRCCY();
    BPCFX BPCFX = new BPCFX();
    short WS_C_A_C = 0;
    SCCGWA SCCGWA;
    TDCPROD TDCPROD;
    TDCQPMP TDCQPMP;
    public void MP(SCCGWA SCCGWA, TDCQPMP TDCQPMP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCQPMP = TDCQPMP;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, "JIFEI1");
        CEP.TRC(SCCGWA, TDCQPMP.FUNC);
        CEP.TRC(SCCGWA, TDCQPMP.PROD_CD);
        CEP.TRC(SCCGWA, TDCQPMP.PROD_CD_M);
        CEP.TRC(SCCGWA, TDCQPMP.PRDMO_CD);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDZQPMP return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        TDCPROD = (TDCPROD) TDCQPMP.DAT_PTR;
        TDCQPMP.RC.RC_RTNCODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "***********");
        CEP.TRC(SCCGWA, "***********");
        B010_CHECK_INPUT();
        if (TDCQPMP.FUNC == 'I' 
            || TDCQPMP.FUNC == '1') {
            B020_INQ_DATA();
        } else {
            if (TDCQPMP.FUNC == 'Q') {
                B030_INQ_DATA_BY_CCY();
            } else {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_INVALID_OPT;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "CHECK1");
        if (TDCQPMP.PROD_CD.trim().length() == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_PRD_NOT_MATCH;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_INQ_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRPMP);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_BANK);
        TDRPMP.KEY.PRD_CD = TDCQPMP.PROD_CD;
        TDRPMP.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        T000_READ_TDTPMP();
        if (WS_PMP_FLG == 'N') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_PMP_REC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
        WS_REF_CCY = TDRPMP.REF_CCY;
        R_OUTPUT_INF_PMP();
        IBS.init(SCCGWA, TDRPMPD);
        TDRPMPD.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        TDRPMPD.KEY.PRD_CD = TDCQPMP.PROD_CD;
        T000_STARTBR_TDTPMPD();
        T000_READNEXT_TDTPMPD();
        WS_I = 1;
        WS_C_A_C = 1;
        while (WS_PMPD_FLG != 'N' 
            && WS_I <= 16) {
            CEP.TRC(SCCGWA, TDRPMP.CCY_TYP);
            CEP.TRC(SCCGWA, TDRPMPD.KEY.PRM_TYP);
            if (TDRPMP.CCY_TYP == 'M' 
                && TDRPMPD.KEY.PRM_TYP == 'A') {
                CEP.TRC(SCCGWA, WS_I);
                TDCPROD.PROD_DATA.OTH_PRM.AVA_CCY_ARRY[WS_I-1].AVA_CCY = TDRPMPD.AVA_CCY;
                WS_I += +1;
                WS_C_A_C += 1;
            }
            T000_READNEXT_TDTPMPD();
        }
        T000_ENDBR_TDTPMPD();
        IBS.init(SCCGWA, TDRPMPD);
        WS_K = 0;
        WS_J = 0;
        TDRPMPD.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        TDRPMPD.KEY.PRD_CD = TDCQPMP.PROD_CD;
        T000_STARTBR_TDTPMPD();
        T000_READNEXT_TDTPMPD();
        WS_K = 1;
        while (WS_PMPD_FLG != 'N' 
            && WS_K <= 10) {
            CEP.TRC(SCCGWA, TDRPMPD.MIN_CCYC);
            WS_CCY = TDRPMPD.MIN_CCYC;
            if (TDRPMPD.KEY.PRM_TYP == 'M' 
                && (WS_CCY.equalsIgnoreCase("036") 
                || WS_CCY.equalsIgnoreCase("124") 
                || WS_CCY.equalsIgnoreCase("156") 
                || WS_CCY.equalsIgnoreCase("157") 
                || WS_CCY.equalsIgnoreCase("208") 
                || WS_CCY.equalsIgnoreCase("344") 
                || WS_CCY.equalsIgnoreCase("356") 
                || WS_CCY.equalsIgnoreCase("360") 
                || WS_CCY.equalsIgnoreCase("392") 
                || WS_CCY.equalsIgnoreCase("446") 
                || WS_CCY.equalsIgnoreCase("458") 
                || WS_CCY.equalsIgnoreCase("554") 
                || WS_CCY.equalsIgnoreCase("578") 
                || WS_CCY.equalsIgnoreCase("586") 
                || WS_CCY.equalsIgnoreCase("608") 
                || WS_CCY.equalsIgnoreCase("702") 
                || WS_CCY.equalsIgnoreCase("752") 
                || WS_CCY.equalsIgnoreCase("756") 
                || WS_CCY.equalsIgnoreCase("764") 
                || WS_CCY.equalsIgnoreCase("826") 
                || WS_CCY.equalsIgnoreCase("840") 
                || WS_CCY.equalsIgnoreCase("901") 
                || WS_CCY.equalsIgnoreCase("978"))) {
                if (TDRPMPD.MIN_CCYC.equalsIgnoreCase(TDRPMP.REF_CCY)) {
                    WS_J = WS_K;
                    TDCQPMP.REF_CCY_SUB = WS_K;
                    CEP.TRC(SCCGWA, TDCQPMP.REF_CCY_SUB);
                }
                CEP.TRC(SCCGWA, WS_K);
                CEP.TRC(SCCGWA, TDRPMPD.MIN_CCYC);
                R_OUTPUT_INF_PMPD();
                WS_K += +1;
            }
            T000_READNEXT_TDTPMPD();
        }
        T000_ENDBR_TDTPMPD();
        CEP.TRC(SCCGWA, WS_K);
        CEP.TRC(SCCGWA, TDRPMP.CCY_TYP);
        if (TDRPMP.CCY_TYP == 'A' 
            || TDRPMP.CCY_TYP == 'F') {
            R_INI_ALL_CCY();
            for (WS_T = 1; WS_T <= WS_C_A_C; WS_T += 1) {
                WS_CCY_CHK = WS_CCY_ALL_LST.WS_CCY_ALL[WS_T-1];
                if (TDRPMP.CCY_TYP == 'F' 
                    && WS_CCY_ALL_LST.WS_CCY_ALL[WS_T-1].equalsIgnoreCase("156")) {
                } else {
                    R_CHK_CCY_EXIT_LST();
                    CEP.TRC(SCCGWA, WS_EXT_FLG);
                    CEP.TRC(SCCGWA, WS_J);
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_J-1]);
                    if ((WS_EXT_FLG != 'Y') 
                        && WS_J > 0 
                        && JIBS_tmp_str[0].trim().length() > 0) {
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_J-1]);
                        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1]);
                        TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].MIN_CCYC = WS_CCY_ALL_LST.WS_CCY_ALL[WS_T-1];
                        WS_CCY = WS_CCY_ALL_LST.WS_CCY_ALL[WS_T-1];
                        TDCQPMP.NEED_EX_FLG[WS_K-1] = 'Y';
                        CEP.TRC(SCCGWA, TDCQPMP.NEED_EX_FLG[WS_K-1]);
                        CEP.TRC(SCCGWA, "REPLACE WITH REF CCY");
                        CEP.TRC(SCCGWA, TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].MIN_CCYC);
                        WS_K += +1;
                    }
                    CEP.TRC(SCCGWA, WS_K);
                }
            }
        }
        if (TDRPMP.CCY_TYP == 'M') {
            for (WS_Y = 1; WS_Y <= 16 
                && TDCPROD.PROD_DATA.OTH_PRM.AVA_CCY_ARRY[WS_Y-1].AVA_CCY.trim().length() != 0; WS_Y += 1) {
                WS_CCY_CHK = TDCPROD.PROD_DATA.OTH_PRM.AVA_CCY_ARRY[WS_Y-1].AVA_CCY;
                R_CHK_CCY_EXIT_LST();
                CEP.TRC(SCCGWA, WS_EXT_FLG);
                CEP.TRC(SCCGWA, WS_J);
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_J-1]);
                if ((WS_EXT_FLG != 'Y') 
                    && WS_J > 0 
                    && JIBS_tmp_str[0].trim().length() > 0) {
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_J-1]);
                    IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1]);
                    TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].MIN_CCYC = TDCPROD.PROD_DATA.OTH_PRM.AVA_CCY_ARRY[WS_Y-1].AVA_CCY;
                    TDCQPMP.NEED_EX_FLG[WS_K-1] = 'Y';
                    CEP.TRC(SCCGWA, TDCQPMP.NEED_EX_FLG[WS_K-1]);
                    CEP.TRC(SCCGWA, "REPLACE WITH REF CCY");
                    CEP.TRC(SCCGWA, TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].MIN_CCYC);
                    WS_K += +1;
                }
                CEP.TRC(SCCGWA, WS_Y);
            }
        }
    }
    public void B030_INQ_DATA_BY_CCY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCQPMP.PROD_CD_M);
        if (TDCQPMP.PROD_CD_M == null) TDCQPMP.PROD_CD_M = "";
        JIBS_tmp_int = TDCQPMP.PROD_CD_M.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) TDCQPMP.PROD_CD_M += " ";
        WS_CCY = TDCQPMP.PROD_CD_M.substring(0, 3);
        if ((!WS_CCY.equalsIgnoreCase("036") 
            && !WS_CCY.equalsIgnoreCase("124") 
            && !WS_CCY.equalsIgnoreCase("156") 
            && !WS_CCY.equalsIgnoreCase("157") 
            && !WS_CCY.equalsIgnoreCase("208") 
            && !WS_CCY.equalsIgnoreCase("344") 
            && !WS_CCY.equalsIgnoreCase("356") 
            && !WS_CCY.equalsIgnoreCase("360") 
            && !WS_CCY.equalsIgnoreCase("392") 
            && !WS_CCY.equalsIgnoreCase("446") 
            && !WS_CCY.equalsIgnoreCase("458") 
            && !WS_CCY.equalsIgnoreCase("554") 
            && !WS_CCY.equalsIgnoreCase("578") 
            && !WS_CCY.equalsIgnoreCase("586") 
            && !WS_CCY.equalsIgnoreCase("608") 
            && !WS_CCY.equalsIgnoreCase("702") 
            && !WS_CCY.equalsIgnoreCase("752") 
            && !WS_CCY.equalsIgnoreCase("756") 
            && !WS_CCY.equalsIgnoreCase("764") 
            && !WS_CCY.equalsIgnoreCase("826") 
            && !WS_CCY.equalsIgnoreCase("840") 
            && !WS_CCY.equalsIgnoreCase("901") 
            && !WS_CCY.equalsIgnoreCase("978"))) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_CCY_I_ERR;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, TDRPMP);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_BANK);
        TDRPMP.KEY.PRD_CD = TDCQPMP.PROD_CD;
        TDRPMP.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        T000_READ_TDTPMP();
        if (WS_PMP_FLG == 'N') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_PMP_REC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
        WS_REF_CCY = TDRPMP.REF_CCY;
        R_OUTPUT_INF_PMP();
        IBS.init(SCCGWA, TDRPMPD);
        TDRPMPD.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        TDRPMPD.KEY.PRD_CD = TDCQPMP.PROD_CD;
        T000_STARTBR_TDTPMPD();
        T000_READNEXT_TDTPMPD();
        WS_I = 1;
        while (WS_PMPD_FLG != 'N' 
            && WS_I <= 16) {
            CEP.TRC(SCCGWA, TDRPMP.CCY_TYP);
            CEP.TRC(SCCGWA, TDRPMPD.KEY.PRM_TYP);
            if (TDRPMP.CCY_TYP == 'M' 
                && TDRPMPD.KEY.PRM_TYP == 'A') {
                CEP.TRC(SCCGWA, WS_I);
                TDCPROD.PROD_DATA.OTH_PRM.AVA_CCY_ARRY[WS_I-1].AVA_CCY = TDRPMPD.AVA_CCY;
                WS_I += +1;
            }
            T000_READNEXT_TDTPMPD();
        }
        T000_ENDBR_TDTPMPD();
        WS_REF_FLG = 'N';
        IBS.init(SCCGWA, TDRPMPD);
        TDRPMPD.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        TDRPMPD.KEY.PRD_CD = TDCQPMP.PROD_CD;
        TDRPMPD.MIN_CCYC = WS_CCY;
        T000_READ_TDTPMPD();
        if (WS_PMPD_FLG == 'Y') {
            WS_K = 1;
            R_OUTPUT_INF_PMPD();
        }
        WS_REF_FLG = ' ';
        if (WS_PMPD_FLG == 'N') {
            WS_CCY_PMPD_FLG = 'N';
            if (TDRPMP.REF_CCY.trim().length() > 0) {
                WS_REF_FLG = 'Y';
                IBS.init(SCCGWA, TDRPMPD);
                TDRPMPD.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
                TDRPMPD.KEY.PRD_CD = TDCQPMP.PROD_CD;
                TDRPMPD.MIN_CCYC = TDRPMP.REF_CCY;
                T000_READ_TDTPMPD();
                WS_K = 1;
                R_OUTPUT_INF_PMPD();
            } else {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_PMPD_REC_EXIST;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void R_INI_ALL_CCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBCCY);
        T000_GROUP_BPTBCCY();
        CEP.TRC(SCCGWA, WS_C_A_C);
        IBS.init(SCCGWA, BPCRCCY);
        BPCRCCY.OP_FUNC = 'S';
        S000_CALL_BPZRCCY();
        BPCRCCY.OP_FUNC = 'R';
        S000_CALL_BPZRCCY();
        WS_C_A = 1;
        while (WS_C_A <= WS_C_A_C) {
            CEP.TRC(SCCGWA, "AA");
            CEP.TRC(SCCGWA, BPCRCCY.DATA.CCY);
            CEP.TRC(SCCGWA, WS_C_A);
            if (BPCRCCY.DATA.CASH_FLG == '0') {
                WS_CCY_ALL_LST.WS_CCY_ALL[WS_C_A-1] = BPCRCCY.DATA.CCY;
            }
            CEP.TRC(SCCGWA, "BB");
            CEP.TRC(SCCGWA, WS_C_A);
            CEP.TRC(SCCGWA, "CC");
            WS_C_A += 1;
            BPCRCCY.OP_FUNC = 'R';
            S000_CALL_BPZRCCY();
        }
        BPCRCCY.OP_FUNC = 'E';
        S000_CALL_BPZRCCY();
    }
    public void R_CHK_CCY_EXIT_LST() throws IOException,SQLException,Exception {
        WS_M = (short) (WS_K - 1);
        WS_EXT_FLG = ' ';
        CEP.TRC(SCCGWA, WS_EXT_FLG);
        for (WS_N = 1; WS_N <= WS_M 
            && WS_EXT_FLG != 'Y'; WS_N += 1) {
            CEP.TRC(SCCGWA, WS_CCY_CHK);
            CEP.TRC(SCCGWA, TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_N-1].MIN_CCYC);
            if (WS_CCY_CHK.equalsIgnoreCase(TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_N-1].MIN_CCYC)) {
                WS_EXT_FLG = 'Y';
            }
        }
    }
    public void R_OUTPUT_INF_PMP() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRPMP.KEY.PRD_CD);
        CEP.TRC(SCCGWA, TDRPMP.EDESC);
        CEP.TRC(SCCGWA, TDRPMP.PART_NUM);
        TDCPROD.PRD_CD = TDRPMP.KEY.PRD_CD;
        TDCPROD.PRDO_CDM = TDRPMP.PRDMO_CD;
        TDCPROD.EFF_DT = TDRPMP.SDT;
        TDCPROD.EXP_DT = TDRPMP.DDT;
        TDCPROD.DESC = TDRPMP.EDESC;
        TDCPROD.CDESC = TDRPMP.CDESC;
        TDCPROD.PRD_CDM = TDRPMP.PRDMO_CD;
        TDCPROD.PROD_DATA.TXN_PRM.PART_NUM = TDRPMP.PART_NUM;
        TDCPROD.PROD_DATA.TXN_PRM.CASH_FLG = TDRPMP.CASH_FLG;
        TDCPROD.PROD_DATA.TXN_PRM.OTH_FLG = TDRPMP.OTH_FLG;
        TDCPROD.PROD_DATA.TXN_PRM.BV_TYP = TDRPMP.BV_TYP;
        TDCPROD.PROD_DATA.TXN_PRM.DRAW_MTH = TDRPMP.DRAW_MTH;
        TDCPROD.PROD_DATA.TXN_PRM.CR_LMT = TDRPMP.CR_LMT;
        TDCPROD.PROD_DATA.TXN_PRM.DR_LMT = TDRPMP.DR_LMT;
        TDCPROD.PROD_DATA.TXN_PRM.STA_LMT = TDRPMP.STA_LMT;
        TDCPROD.PROD_DATA.TXN_PRM.RSID_LMT = TDRPMP.RSID_LMT;
        TDCPROD.PROD_DATA.TXN_PRM.CUST_CTL = TDRPMP.CUST_CTL;
        TDCPROD.PROD_DATA.TXN_PRM.NON_CTL = TDRPMP.NON_CTL;
        TDCPROD.PROD_DATA.INT_PRM.BUD_FLG = TDRPMP.BUD_FLG;
        TDCPROD.PROD_DATA.INT_PRM.BUD_PERD = TDRPMP.BUD_PERD;
        TDCPROD.PROD_DATA.INT_PRM.BUD_DATE = TDRPMP.BUD_DATE;
        TDCPROD.PROD_DATA.INT_PRM.IRAT_TYP = TDRPMP.IRAT_TYP;
        TDCPROD.PROD_DATA.INT_PRM.RAT_INX = TDRPMP.RAT_INX;
        TDCPROD.PROD_DATA.EXP_PRM.NORM_TYP = TDRPMP.NORM_TYP;
        TDCPROD.PROD_DATA.EXP_PRM.ERLY_TYP = TDRPMP.ERLY_TYP;
        TDCPROD.PROD_DATA.EXP_PRM.LATE_TYP = TDRPMP.LATE_TYP;
        TDCPROD.PROD_DATA.EXP_PRM.RES_TYP = TDRPMP.RES_TYP;
        TDCPROD.PROD_DATA.EXP_PRM.DOCU_TYP = TDRPMP.DOCU_TYP;
        TDCPROD.PROD_DATA.EXP_PRM.TAX_CD = TDRPMP.TAX_CD;
        TDCPROD.PROD_DATA.EXP_PRM.TAX_TYP = TDRPMP.TAX_TYP;
        TDCPROD.PROD_DATA.EXP_PRM.SPRD_FLG = TDRPMP.SPRD_FLG;
        TDCPROD.PROD_DATA.EXP_PRM.TWAV_FLG = TDRPMP.TWAV_FLG;
        TDCPROD.PROD_DATA.EXP_PRM.DUE_FLG = TDRPMP.DUE_FLG;
        TDCPROD.PROD_DATA.EXP_PRM.OFD_TYP = TDRPMP.OFD_TYP;
        TDCPROD.PROD_DATA.EXP_PRM.HLID_RUL = TDRPMP.HLID_RUL;
        TDCPROD.PROD_DATA.EXP_PRM.INR_MTH = TDRPMP.INR_MTH;
        TDCPROD.PROD_DATA.EXP_PRM.RNEW_FLG = TDRPMP.RNEW_FLG;
        TDCPROD.PROD_DATA.EXP_PRM.NENEW_RUL_CD = TDRPMP.NENEW_RUL_CD;
        TDCPROD.PROD_DATA.EXP_PRM.LMT_FLG = TDRPMP.LMT_FLG;
        TDCPROD.PROD_DATA.EXP_PRM.ONTM_FLG = TDRPMP.ONTM_FLG;
        TDCPROD.PROD_DATA.EXP_PRM.OPTM_FLG = TDRPMP.OPTM_FLG;
        TDCPROD.PROD_DATA.EXP_PRM.MDF_FLG = TDRPMP.MDF_FLG;
        TDCPROD.PROD_DATA.OTH_PRM.PRD_TYP = TDRPMP.PRD_TYP;
        TDCPROD.PROD_DATA.OTH_PRM.MAX_NUM = TDRPMP.MAX_NUM;
        TDCPROD.PROD_DATA.OTH_PRM.PAY_GRCE = TDRPMP.PAY_GRCE;
        TDCPROD.PROD_DATA.OTH_PRM.PAY_PERD = TDRPMP.PAY_PERD;
        TDCPROD.PROD_DATA.OTH_PRM.PLAN_FLG = TDRPMP.PLAN_FLG;
        TDCPROD.PROD_DATA.OTH_PRM.MIN_TERM = TDRPMP.MIN_TERM;
        TDCPROD.PROD_DATA.OTH_PRM.MAX_TERM = TDRPMP.MAX_TERM;
        TDCPROD.PROD_DATA.OTH_PRM.INT_PRD1 = TDRPMP.INT_PRD1;
        TDCPROD.PROD_DATA.OTH_PRM.INT_PRD2 = TDRPMP.INT_PRD2;
        TDCPROD.PROD_DATA.OTH_PRM.INT_PRD3 = TDRPMP.INT_PRD3;
        TDCPROD.PROD_DATA.OTH_PRM.COMP_FLG = TDRPMP.COMP_FLG;
        TDCPROD.PROD_DATA.OTH_PRM.INTP_FLG = TDRPMP.INTP_FLG;
        TDCPROD.PROD_DATA.OTH_PRM.PENA_FLG = TDRPMP.PENA_FLG;
        TDCPROD.PROD_DATA.OTH_PRM.UNIT_DAY = TDRPMP.UNIT_DAY;
        TDCPROD.PROD_DATA.OTH_PRM.INT_PERD = TDRPMP.INT_PERD;
        TDCPROD.PROD_DATA.OTH_PRM.MAX_GRCE = TDRPMP.MAX_GRCE;
        TDCPROD.PROD_DATA.OTH_PRM.CCY_TYP = TDRPMP.CCY_TYP;
        TDCPROD.PROD_DATA.OTH_PRM.DOCU_FLG = TDRPMP.DOCU_FLG;
        TDCPROD.PROD_DATA.OTH_PRM.REF_CCY = TDRPMP.REF_CCY;
        TDCPROD.PROD_DATA.OTH_PRM.NO_NOTIFY_FLG = TDRPMP.NO_NOTIFY_FLG;
        TDCPROD.PROD_DATA.OTH_PRM.BAL_FLG = TDRPMP.BAL_FLG;
        TDCPROD.PROD_DATA.OTH_PRM.MID_FLG = TDRPMP.MID_FLG;
        TDCPROD.PROD_DATA.OTH_PRM.DELA_FLG = TDRPMP.DELA_FLG;
        TDCPROD.PROD_DATA.OTH_PRM.CPRA_TYP = TDRPMP.CPRA_TYP;
        TDCPROD.PROD_DATA.OTH_PRM.ACTI_FLG = TDRPMP.ACTI_FLG;
        TDCPROD.PROD_DATA.OTH_PRM.FRZ_FLG = TDRPMP.FRZ_FLG;
        CEP.TRC(SCCGWA, TDRPMP.REF_CCY);
        CEP.TRC(SCCGWA, TDRPMP.FRZ_FLG);
    }
    public void R_OUTPUT_INF_PMPD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRPMPD);
        TDCPROD.PRD_CD = TDRPMPD.KEY.PRD_CD;
        if (TDCQPMP.FUNC == 'Q' 
            && WS_REF_FLG == 'Y') {
            TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].MIN_CCYC = WS_CCY;
        } else {
            TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].MIN_CCYC = TDRPMPD.MIN_CCYC;
        }
        CEP.TRC(SCCGWA, TDRPMPD.MIN_CCYC);
        CEP.TRC(SCCGWA, TDRPMPD.ADD_AMTC);
        CEP.TRC(SCCGWA, TDRPMPD.MIN_AMTC);
        CEP.TRC(SCCGWA, TDRPMPD.FST_AMTC);
        CEP.TRC(SCCGWA, TDRPMPD.MDRW_AMT);
        CEP.TRC(SCCGWA, TDRPMPD.MLET_AMT);
        if (TDCQPMP.FUNC == 'Q' 
            && WS_REF_FLG == 'Y') {
            if (TDRPMPD.ADD_AMTC > 0) {
                TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].ADD_AMTC = TDRPMPD.ADD_AMTC;
                if (!WS_CCY.equalsIgnoreCase(WS_REF_CCY) 
                    && WS_REF_CCY.trim().length() > 0 
                    && WS_CCY_PMPD_FLG == 'N') {
                    WS_BUY_AMT = TDRPMPD.ADD_AMTC;
                    R000_AMT_EX_PROC();
                    TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].ADD_AMTC = WS_SELL_AMT;
                }
            }
        } else {
            TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].ADD_AMTC = TDRPMPD.ADD_AMTC;
        }
        CEP.TRC(SCCGWA, TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].ADD_AMTC);
        if (TDCQPMP.FUNC == 'Q' 
            && WS_REF_FLG == 'Y') {
            if (TDRPMPD.MIN_AMTC > 0) {
                TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].MIN_AMTC = TDRPMPD.MIN_AMTC;
                if (!WS_CCY.equalsIgnoreCase(WS_REF_CCY) 
                    && WS_REF_CCY.trim().length() > 0 
                    && WS_CCY_PMPD_FLG == 'N') {
                    WS_BUY_AMT = TDRPMPD.MIN_AMTC;
                    R000_AMT_EX_PROC();
                    TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].MIN_AMTC = WS_SELL_AMT;
                }
            }
        } else {
            TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].MIN_AMTC = TDRPMPD.MIN_AMTC;
        }
        CEP.TRC(SCCGWA, TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].MIN_AMTC);
        if (TDCQPMP.FUNC == 'Q' 
            && WS_REF_FLG == 'Y') {
            if (TDRPMPD.FST_AMTC > 0) {
                TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].FST_AMTC = TDRPMPD.FST_AMTC;
                if (!WS_CCY.equalsIgnoreCase(WS_REF_CCY) 
                    && WS_REF_CCY.trim().length() > 0 
                    && WS_CCY_PMPD_FLG == 'N') {
                    WS_BUY_AMT = TDRPMPD.FST_AMTC;
                    R000_AMT_EX_PROC();
                    TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].FST_AMTC = WS_SELL_AMT;
                }
            }
        } else {
            TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].FST_AMTC = TDRPMPD.FST_AMTC;
        }
        CEP.TRC(SCCGWA, TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].FST_AMTC);
        if (TDCQPMP.FUNC == 'Q' 
            && WS_REF_FLG == 'Y') {
            if (TDRPMPD.MDRW_AMT > 0) {
                TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].MDRW_AMT = TDRPMPD.MDRW_AMT;
                if (!WS_CCY.equalsIgnoreCase(WS_REF_CCY) 
                    && WS_REF_CCY.trim().length() > 0 
                    && WS_CCY_PMPD_FLG == 'N') {
                    WS_BUY_AMT = TDRPMPD.MDRW_AMT;
                    R000_AMT_EX_PROC();
                    TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].MDRW_AMT = WS_SELL_AMT;
                }
            }
        } else {
            TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].MDRW_AMT = TDRPMPD.MDRW_AMT;
        }
        CEP.TRC(SCCGWA, TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].MDRW_AMT);
        if (TDCQPMP.FUNC == 'Q' 
            && WS_REF_FLG == 'Y') {
            if (TDRPMPD.MLET_AMT > 0) {
                TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].MLET_AMT = TDRPMPD.MLET_AMT;
                if (!WS_CCY.equalsIgnoreCase(WS_REF_CCY) 
                    && WS_REF_CCY.trim().length() > 0 
                    && WS_CCY_PMPD_FLG == 'N') {
                    WS_BUY_AMT = TDRPMPD.MLET_AMT;
                    R000_AMT_EX_PROC();
                    TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].MLET_AMT = WS_SELL_AMT;
                }
            }
        } else {
            TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].MLET_AMT = TDRPMPD.MLET_AMT;
        }
        CEP.TRC(SCCGWA, TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].MLET_AMT);
        if (TDCQPMP.FUNC == 'Q' 
            && WS_REF_FLG == 'Y') {
            if (TDRPMPD.MAX_AMT > 0) {
                TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].MAX_AMT = TDRPMPD.MAX_AMT;
                if (!WS_CCY.equalsIgnoreCase(WS_REF_CCY) 
                    && WS_REF_CCY.trim().length() > 0 
                    && WS_CCY_PMPD_FLG == 'N') {
                    WS_BUY_AMT = TDRPMPD.MAX_AMT;
                    R000_AMT_EX_PROC();
                    TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].MAX_AMT = WS_SELL_AMT;
                }
            }
        } else {
            TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].MAX_AMT = TDRPMPD.MAX_AMT;
        }
        CEP.TRC(SCCGWA, TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].MAX_AMT);
        TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].FAVO_FLG = TDRPMPD.FAVO_FLG;
        TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].RUL_CD.RUL_CD1 = TDRPMPD.RUL_CD1;
        TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].RUL_CD.RUL_CD2 = TDRPMPD.RUL_CD2;
        TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].RUL_CD.RUL_CD3 = TDRPMPD.RUL_CD3;
        TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].RUL_CD.RUL_CD4 = TDRPMPD.RUL_CD4;
        TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].RUL_CD.RUL_CD5 = TDRPMPD.RUL_CD5;
        TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].RUL_CD.RUL_CD6 = TDRPMPD.RUL_CD6;
        TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].RUL_CD.RUL_CD7 = TDRPMPD.RUL_CD7;
        TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].RUL_CD.RUL_CD8 = TDRPMPD.RUL_CD8;
        TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].RUL_CD.RUL_CD9 = TDRPMPD.RUL_CD9;
        TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].RUL_CD.RUL_CD10 = TDRPMPD.RUL_CD10;
        TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].TERM = TDRPMPD.TERM;
        TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].TERM_T1 = TDRPMPD.TERM_T1;
        TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].TERM1 = TDRPMPD.TERM1;
        TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].TERM_T2 = TDRPMPD.TERM_T2;
        TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].TERM2 = TDRPMPD.TERM2;
        TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].TERM_T3 = TDRPMPD.TERM_T3;
        TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].TERM3 = TDRPMPD.TERM3;
        TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].TERM_T4 = TDRPMPD.TERM_T4;
        TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].TERM4 = TDRPMPD.TERM4;
        TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].TERM_T5 = TDRPMPD.TERM_T5;
        TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].TERM5 = TDRPMPD.TERM5;
        TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].TERM_T6 = TDRPMPD.TERM_T6;
        TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].TERM6 = TDRPMPD.TERM6;
        TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].INT_RUL = TDRPMPD.INT_RUL;
        TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].RAT_CD = TDRPMPD.RAT_CD;
        TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].OFRAT_CD = TDRPMPD.OFRAT_CD;
        TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].SEG_MTH = TDRPMPD.SEG_MTH;
        TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].NERAT_CD = TDRPMPD.NERAT_CD;
        TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].PRRAT_CD = TDRPMPD.PRRAT_CD;
        TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].DOCU_NO = TDRPMPD.DOCU_NO;
        TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].FD_TOP_IRAT_CD = TDRPMPD.FD_TOP_IRAT_CD;
        TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].FD_TOP_PCT = TDRPMPD.FD_TOP_PCT;
        TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].EXC_LMT_PROC = TDRPMPD.EXC_LMT_PROC;
        TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[WS_K-1].AUTH_LEL = TDRPMPD.AUTH_LEL;
        CEP.TRC(SCCGWA, TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[1-1].FD_TOP_IRAT_CD);
        CEP.TRC(SCCGWA, TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[1-1].FD_TOP_PCT);
        CEP.TRC(SCCGWA, TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[1-1].EXC_LMT_PROC);
        CEP.TRC(SCCGWA, TDCPROD.PROD_DATA.OTH_PRM.CCY_INF[1-1].AUTH_LEL);
    }
    public void R000_AMT_EX_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFX);
        WS_SELL_AMT = 0;
        BPCFX.FUNC = '3';
        BPCFX.CHNL = SCCGWA.COMM_AREA.CHNL;
        BPCFX.EXR_TYPE = "MDR";
        BPCFX.BUY_CCY = WS_REF_CCY;
        BPCFX.BUY_AMT = WS_BUY_AMT;
        BPCFX.SELL_CCY = WS_CCY;
        CEP.TRC(SCCGWA, BPCFX.BUY_CCY);
        CEP.TRC(SCCGWA, BPCFX.BUY_AMT);
        CEP.TRC(SCCGWA, BPCFX.SELL_CCY);
        S000_CALL_BPZSFX();
        CEP.TRC(SCCGWA, WS_REF_CCY);
        CEP.TRC(SCCGWA, WS_BUY_AMT);
        CEP.TRC(SCCGWA, WS_CCY);
        WS_SELL_AMT = BPCFX.SELL_AMT;
        CEP.TRC(SCCGWA, WS_SELL_AMT);
    }
    public void S000_CALL_BPZSFX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-FX", BPCFX);
        if (BPCFX.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCFX.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-RESOURCE-CCY", BPCRCCY);
    }
    public void T000_READ_TDTPMP() throws IOException,SQLException,Exception {
        TDTPMP_RD = new DBParm();
        TDTPMP_RD.TableName = "TDTPMP";
        TDTPMP_RD.where = "IBS_AC_BK = :TDRPMP.KEY.IBS_AC_BK "
            + "AND PRD_CD = :TDRPMP.KEY.PRD_CD";
        IBS.READ(SCCGWA, TDRPMP, this, TDTPMP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_PMP_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_PMP_FLG = 'N';
        } else {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_OTHER_ERR);
        }
    }
    public void T000_GROUP_BPTBCCY() throws IOException,SQLException,Exception {
        BPTBCCY_RD = new DBParm();
        BPTBCCY_RD.TableName = "BPTBCCY";
        BPTBCCY_RD.where = "CCY LIKE '%'";
        BPTBCCY_RD.set = "WS-C-A-C=COUNT(*)";
        IBS.GROUP(SCCGWA, BPRBCCY, this, BPTBCCY_RD);
    }
    public void T000_READ_TDTPMPD() throws IOException,SQLException,Exception {
        TDTPMPD_RD = new DBParm();
        TDTPMPD_RD.TableName = "TDTPMPD";
        TDTPMPD_RD.where = "IBS_AC_BK = :TDRPMPD.KEY.IBS_AC_BK "
            + "AND PRD_CD = :TDRPMPD.KEY.PRD_CD "
            + "AND PRM_TYP = 'M' "
            + "AND MIN_CCYC = :TDRPMPD.MIN_CCYC";
        IBS.READ(SCCGWA, TDRPMPD, this, TDTPMPD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_PMPD_FLG = 'Y';
        } else {
            WS_PMPD_FLG = 'N';
        }
    }
    public void T000_STARTBR_TDTPMPD() throws IOException,SQLException,Exception {
        TDTPMPD_BR.rp = new DBParm();
        TDTPMPD_BR.rp.TableName = "TDTPMPD";
        TDTPMPD_BR.rp.where = "IBS_AC_BK = :TDRPMPD.KEY.IBS_AC_BK "
            + "AND PRD_CD = :TDRPMPD.KEY.PRD_CD";
        TDTPMPD_BR.rp.order = "SEQ";
        IBS.STARTBR(SCCGWA, TDRPMPD, this, TDTPMPD_BR);
    }
    public void T000_READNEXT_TDTPMPD() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRPMPD, this, TDTPMPD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_PMPD_FLG = 'Y';
        } else {
            WS_PMPD_FLG = 'N';
        }
    }
    public void T000_ENDBR_TDTPMPD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTPMPD_BR);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, SCCBINF);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
