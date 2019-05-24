package com.hisun.DC;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCCINTI;
import com.hisun.BP.BPCPQPRD;
import com.hisun.BP.BPCPRMM;
import com.hisun.BP.BPRPRMT;
import com.hisun.DD.DDCIMCCY;
import com.hisun.DD.DDCIMMST;
import com.hisun.LN.LNCCLNQ;
import com.hisun.LN.LNCICIQ;
import com.hisun.LN.LNCICTLM;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class DCZUCBDR {
    boolean pgmRtn = false;
    String WS_OVR_NO = "                                                  ";
    String CDD_I_AVE_AMT = "DD-I-AVE-AMT";
    String CPN_I_LN_INFO = "LN-FUNC-CI-INFO";
    String CDD_I_NFIN_M_CCY = "DD-I-NFIN-M-CCY";
    String CPN_U_LNZICTLM = "LN-SRC-ICTL-MAINT";
    String CPN_U_DDZIMMST = "DD-I-NFIN-M-MST";
    String CPN_U_BPZCINTI = "BP-C-INTR-INQ";
    char K_FUNC_INQ = 'I';
    String K_PRDPR_TYPE = "PRDPR";
    int K_RAT_MTH = 36000;
    String WS_MSG_ID = "      ";
    char WS_LNAC_STS = ' ';
    double WS_PROC_AC_PCT = 0;
    DCZUCBDR_WS_C000_VARIABLES WS_C000_VARIABLES = new DCZUCBDR_WS_C000_VARIABLES();
    String WS_PROC_TYP = "          ";
    double WS_TEMP_PCT_AMT = 0;
    double WS_EFF_LN_BAL = 0;
    double WS_MIN_PCT_AMT = 0;
    double WS_DD_AC_INCOME = 0;
    double WS_DD_AC_ADD = 0;
    double WS_PROC_AC_BAL_ADD = 0;
    double WS_REF_AC_BAL_ADD = 0;
    double WS_DD_RATE = 0;
    double WS_LN_INT_TOTAL = 0;
    int WS_LN_CNT = 0;
    int WS_LN_CNT2 = 0;
    int WS_LN_CNT3 = 0;
    double[] WS_LN_RAT = new double[99999];
    char WS_DCTACTL_FLAG = ' ';
    char WS_TABLE_FLG_3 = ' ';
    char WS_LN_EOF_FLG = ' ';
    char WS_LN_RAT_FLG = ' ';
    char WS_LN_OVER = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDCSEAMT DDCSEAMT = new DDCSEAMT();
    DDCIMCCY DDCIMCCY = new DDCIMCCY();
    LNCICIQ LNCICIQ = new LNCICIQ();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCCLNQ LNCCLNQ = new LNCCLNQ();
    BPCCINTI BPCCINTI = new BPCCINTI();
    DDCIMMST DDCIMMST = new DDCIMMST();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    DCCIAPRD DCCIAPRD = new DCCIAPRD();
    DCRMPR DCRMPR = new DCRMPR();
    DCRIPR DCRIPR = new DCRIPR();
    DCRACTL DCRACTL = new DCRACTL();
    SCCGWA SCCGWA;
    DCCUCBDR DCCUCBDR;
    public void MP(SCCGWA SCCGWA, DCCUCBDR DCCUCBDR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCUCBDR = DCCUCBDR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZUCBDR return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUCBDR.O_AREA);
        WS_LN_CNT = 0;
        WS_LN_CNT2 = 0;
        WS_LN_CNT3 = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        WS_OVR_NO = DCCUCBDR.I_AREA.OVR_NO;
        CEP.TRC(SCCGWA, WS_OVR_NO);
        WS_PROC_AC_PCT = 0;
        B100_GET_LNAC_INFO();
        if (pgmRtn) return;
        B200_GET_REF_AC_INFO();
        if (pgmRtn) return;
        B300_CAL_RAT_INCOME();
        if (pgmRtn) return;
    }
    public void B100_GET_LNAC_INFO() throws IOException,SQLException,Exception {
        WS_OVR_NO = DCCUCBDR.I_AREA.OVR_NO;
        WS_LN_OVER = 'N';
        T000_STARTBR_DCTIPR();
        if (pgmRtn) return;
        T000_READNEXT_DCTIPR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCRIPR.KEY.LN_AC_NO);
        while (WS_TABLE_FLG_3 != 'N' 
            && WS_LN_OVER != 'Y') {
            IBS.init(SCCGWA, LNCCLNQ);
            LNCCLNQ.FUNC = '4';
            LNCCLNQ.DATA.CONT_NO = DCRIPR.KEY.LN_AC_NO;
            S000_CALL_LNZICLNQ();
            if (pgmRtn) return;
            if (LNCCLNQ.DATA.STS == 'G') {
                WS_LN_OVER = 'Y';
            }
            if (LNCCLNQ.DATA.STS == 'Z') {
                if (LNCCLNQ.DATA.TOT_BAL > 0) {
                    WS_LN_INT_TOTAL = WS_LN_INT_TOTAL + ( LNCCLNQ.DATA.TOT_BAL * LNCCLNQ.DATA.CUR_RAT );
                    bigD = new BigDecimal(WS_LN_INT_TOTAL);
                    WS_LN_INT_TOTAL = bigD.setScale(8, RoundingMode.HALF_UP).doubleValue();
                    WS_LN_CNT += 1;
                    WS_LN_RAT[WS_LN_CNT-1] = LNCCLNQ.DATA.CUR_RAT;
                    DCCUCBDR.O_AREA.LN_BAL = DCCUCBDR.O_AREA.LN_BAL + LNCCLNQ.DATA.TOT_BAL;
                }
            }
            CEP.TRC(SCCGWA, WS_LN_INT_TOTAL);
            CEP.TRC(SCCGWA, DCCUCBDR.O_AREA.LN_BAL);
            if (DCCUCBDR.O_AREA.LN_BAL > 0) {
                DCCUCBDR.O_AREA.LN_AVE_RAT = WS_LN_INT_TOTAL / DCCUCBDR.O_AREA.LN_BAL;
                bigD = new BigDecimal(DCCUCBDR.O_AREA.LN_AVE_RAT);
                DCCUCBDR.O_AREA.LN_AVE_RAT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                WS_LN_RAT_FLG = 'Y';
                WS_LN_EOF_FLG = 'N';
                while (WS_LN_CNT2 < WS_LN_CNT 
                    && WS_LN_EOF_FLG != 'Y' 
                    && WS_LN_RAT_FLG != 'N') {
                    WS_LN_CNT2 += 1;
                    if (WS_LN_RAT[WS_LN_CNT2-1] == 0) {
                        WS_LN_EOF_FLG = 'Y';
                    }
                    if (WS_LN_CNT2 >= 2) {
                        WS_LN_CNT3 = WS_LN_CNT2 - 1;
                        if (WS_LN_RAT[WS_LN_CNT2-1] != WS_LN_RAT[WS_LN_CNT3-1]) {
                            WS_LN_RAT_FLG = 'N';
                        }
                    }
                }
                if (WS_LN_RAT_FLG == 'Y') {
                    DCCUCBDR.O_AREA.LN_AVE_RAT = WS_LN_RAT[WS_LN_CNT2-1];
                }
            }
            CEP.TRC(SCCGWA, DCCUCBDR.O_AREA.LN_AVE_RAT);
            T000_READNEXT_DCTIPR();
            if (pgmRtn) return;
        }
        T000_ENDBR_DCTIPR();
        if (pgmRtn) return;
        if (WS_LN_OVER == 'Y') {
            DCCUCBDR.O_AREA.LN_AVE_RAT = 0;
            DCCUCBDR.O_AREA.LN_BAL = 0;
        }
    }
    public void B200_GET_REF_AC_INFO() throws IOException,SQLException,Exception {
        WS_REF_AC_BAL_ADD = 0;
        B210_GET_DD_RAT();
        if (pgmRtn) return;
        DCCUCBDR.O_AREA.DD_AVE_RAT = WS_DD_RATE;
        IBS.init(SCCGWA, DCRMPR);
        DCRMPR.KEY.OVR_NO = DCCUCBDR.I_AREA.OVR_NO;
        T000_READ_DCTMPR();
        if (pgmRtn) return;
        if (DCCUCBDR.I_AREA.GLF_FLG == 'Y') {
            T000_STARTBR_DCTACTL();
            if (pgmRtn) return;
            T100_READNEXT_DCTACTL();
            if (pgmRtn) return;
            while (WS_DCTACTL_FLAG != 'N') {
                if (DCRACTL.REF_FLG == 'Y') {
                    WS_C000_VARIABLES.WS_C000_I_AC_NO = DCRACTL.KEY.GAC_NO;
                    C000_GET_DD_AC_AMT();
                    if (pgmRtn) return;
                    DCCUCBDR.O_AREA.DD_BAL += WS_C000_VARIABLES.WS_C000_O_AC_BAL;
                    CEP.TRC(SCCGWA, "A1");
                    CEP.TRC(SCCGWA, DCCUCBDR.O_AREA.DD_BAL);
                    if (SCCGWA.COMM_AREA.AC_DATE == DCCUCBDR.I_AREA.PROCS_DATE 
                        && DCRACTL.ACDD_BAL == 0) {
                        WS_C000_VARIABLES.WS_AC_NO = DCRACTL.KEY.GAC_NO;
                        B310_CAL_AMT_ADD();
                        if (pgmRtn) return;
                        WS_C000_VARIABLES.WS_ADD_BAL = DDCSEAMT.O_AREA.AVE_AMT;
                        DCRACTL.ACDD_BAL = DDCSEAMT.O_AREA.AVE_AMT;
                        DCRACTL.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                        DCRACTL.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                        T000_REWRITE_DCTACTL();
                        if (pgmRtn) return;
                    } else {
                        WS_C000_VARIABLES.WS_ADD_BAL = DCRACTL.ACDD_BAL;
                    }
                    WS_DD_AC_ADD = WS_C000_VARIABLES.WS_C000_O_AC_BAL - WS_C000_VARIABLES.WS_ADD_BAL;
                    if (WS_DD_AC_ADD < 0) {
                        WS_DD_AC_ADD = 0;
                    }
                    WS_REF_AC_BAL_ADD = WS_REF_AC_BAL_ADD + WS_DD_AC_ADD * DCRMPR.GDD_PCTS / 100;
                    bigD = new BigDecimal(WS_REF_AC_BAL_ADD);
                    WS_REF_AC_BAL_ADD = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                }
                T100_READNEXT_DCTACTL();
                if (pgmRtn) return;
            }
            T200_ENDBR_DCTACTL();
            if (pgmRtn) return;
        }
    }
    public void B210_GET_DD_RAT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCCINTI);
        BPCCINTI.FUNC = K_FUNC_INQ;
        BPCCINTI.BASE_INFO.DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCCINTI.BASE_INFO.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCCINTI.BASE_INFO.BASE_TYP = "A01";
        BPCCINTI.BASE_INFO.TENOR = "D000";
        BPCCINTI.BASE_INFO.CCY = DCCUCBDR.I_AREA.CCY;
        S000_CALL_BPZCINTI();
        if (pgmRtn) return;
        WS_DD_RATE = BPCCINTI.BASE_INFO.OWN_RATE;
    }
    public void B300_CAL_RAT_INCOME() throws IOException,SQLException,Exception {
        WS_C000_VARIABLES.WS_C000_I_AC_NO = DCCUCBDR.I_AREA.AC_NO;
        C000_GET_DD_AC_AMT();
        if (pgmRtn) return;
        DCCUCBDR.O_AREA.DD_BAL += WS_C000_VARIABLES.WS_C000_O_AC_BAL;
        if (SCCGWA.COMM_AREA.AC_DATE == DCCUCBDR.I_AREA.PROCS_DATE 
            && DCRMPR.DD_BAL == 0) {
            WS_C000_VARIABLES.WS_AC_NO = DCCUCBDR.I_AREA.AC_NO;
            B310_CAL_AMT_ADD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDCSEAMT.O_AREA.AVE_AMT);
            DCCUCBDR.I_AREA.DD_BAL_THR = DDCSEAMT.O_AREA.AVE_AMT;
            WS_PROC_AC_BAL_ADD = WS_C000_VARIABLES.WS_C000_O_AC_BAL - DDCSEAMT.O_AREA.AVE_AMT;
        } else {
            WS_PROC_AC_BAL_ADD = WS_C000_VARIABLES.WS_C000_O_AC_BAL - DCRMPR.DD_BAL;
        }
        WS_PROC_AC_BAL_ADD = WS_PROC_AC_BAL_ADD * DCRMPR.PROC_ACP / 100;
        CEP.TRC(SCCGWA, WS_PROC_AC_BAL_ADD);
        if (WS_PROC_AC_BAL_ADD < 0) {
            WS_PROC_AC_BAL_ADD = 0;
        }
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = DCCUCBDR.I_AREA.PROD_CODE;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        if (BPCPQPRD.PARM_CODE.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_PROD_REC_NOTFND, DCCUCBDR.O_AREA.MSG_ID);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, DCCIAPRD);
        BPRPRMT.KEY.TYP = K_PRDPR_TYPE;
        if (BPRPRMT.KEY.CD == null) BPRPRMT.KEY.CD = "";
        JIBS_tmp_int = BPRPRMT.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPRMT.KEY.CD += " ";
        BPRPRMT.KEY.CD = "999999" + BPRPRMT.KEY.CD.substring(6);
        if (BPCPQPRD.PARM_CODE == null) BPCPQPRD.PARM_CODE = "";
        JIBS_tmp_int = BPCPQPRD.PARM_CODE.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) BPCPQPRD.PARM_CODE += " ";
        if (BPRPRMT.KEY.CD == null) BPRPRMT.KEY.CD = "";
        JIBS_tmp_int = BPRPRMT.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPRMT.KEY.CD += " ";
        BPRPRMT.KEY.CD = BPRPRMT.KEY.CD.substring(0, 7 - 1) + BPCPQPRD.PARM_CODE.substring(0, 8) + BPRPRMT.KEY.CD.substring(7 + 8 - 1);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCIAPRD);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT.DAT_TXT);
        BPCPRMM.FUNC = '3';
        BPCPRMM.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
