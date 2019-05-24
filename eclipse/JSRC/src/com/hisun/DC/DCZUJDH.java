package com.hisun.DC;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCCINTI;
import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPCPORUP_DATA_INFO;
import com.hisun.BP.BPCPQPRD;
import com.hisun.BP.BPCPRMM;
import com.hisun.BP.BPRPRMT;
import com.hisun.DD.DDCIMCCY;
import com.hisun.DD.DDCMSG_ERROR_MSG;
import com.hisun.LN.LNCCLNQ;
import com.hisun.LN.LNCICIQ;
import com.hisun.LN.LNCICTLM;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class DCZUJDH {
    boolean pgmRtn = false;
    String WS_OVR_NO = " ";
    String CPN_U_LNZICIQ = "LN-FUNC-CI-INFO";
    String CPN_U_LNZICTLM = "LN-SRC-ICTL-MAINT";
    String CPN_U_DDZIMCCY = "DD-I-NFIN-M-CCY";
    String CPN_U_DDZIMMST = "DD-I-NFIN-M-MST";
    String CPN_U_DDZIQPRD = "DD-I-INQ-DDPRD";
    String CPN_U_BPZCINTI = "BP-C-INTR-INQ";
    String K_PRDPR_TYPE = "PRDPR";
    char WS_LN_EOF_FLG = ' ';
    char WS_LN_RAT_FLG = ' ';
    char WS_LN_OVER = ' ';
    char WS_TABLE_FLG_3 = ' ';
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    double WS_INCOME_AMT = 0;
    double WS_DD_BAL = 0;
    double WS_DD_BAL2 = 0;
    double WS_LN_BAL_TOTAL = 0;
    double WS_EFF_LN_BAL = 0;
    double WS_EFF_DD_BAL = 0;
    double WS_DD_RATE = 0;
    double WS_AVG_LN_RATE = 0;
    double WS_LN_INT_TOTAL = 0;
    int WS_LN_CNT = 0;
    int WS_LN_CNT2 = 0;
    int WS_LN_CNT3 = 0;
    double[] WS_LN_RAT = new double[99999];
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNCICIQ LNCICIQ = new LNCICIQ();
    LNCICTLM LNCICTLM = new LNCICTLM();
    DDCIMCCY DDCIMCCY = new DDCIMCCY();
    BPCCINTI BPCCINTI = new BPCCINTI();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    DCCIAPRD DCCIAPRD = new DCCIAPRD();
    LNCCLNQ LNCCLNQ = new LNCCLNQ();
    DCRMPR DCRMPR = new DCRMPR();
    DCRIPR DCRIPR = new DCRIPR();
    SCCGWA SCCGWA;
    BPCPORUP_DATA_INFO BPCPORUP;
    DCCUJDH DCCUJDH;
    public void MP(SCCGWA SCCGWA, DCCUJDH DCCUJDH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCUJDH = DCCUJDH;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZUJDH return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        WS_LN_CNT = 0;
        WS_LN_CNT2 = 0;
        WS_LN_CNT3 = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_GET_LN_INF();
        if (pgmRtn) return;
        B200_GET_DD_INF();
        if (pgmRtn) return;
        B300_INCOME_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_GET_LN_INF() throws IOException,SQLException,Exception {
        WS_OVR_NO = DCCUJDH.OVR_NO;
        WS_LN_INT_TOTAL = 0;
        WS_LN_BAL_TOTAL = 0;
        T000_STARTBR_DCTIPR();
        if (pgmRtn) return;
        T000_READNEXT_DCTIPR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCRIPR.KEY.LN_AC_NO);
        WS_LN_OVER = 'N';
        while (WS_TABLE_FLG_3 != 'N' 
            && WS_LN_OVER != 'Y') {
            IBS.init(SCCGWA, LNCCLNQ);
            LNCCLNQ.FUNC = '4';
            LNCCLNQ.DATA.CONT_NO = DCRIPR.KEY.LN_AC_NO;
            S000_CALL_LNZICLNQ();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, LNCCLNQ.DATA.STS);
            CEP.TRC(SCCGWA, LNCCLNQ.DATA.TOT_BAL);
            CEP.TRC(SCCGWA, LNCCLNQ.DATA.CUR_RAT);
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
                    WS_LN_BAL_TOTAL = WS_LN_BAL_TOTAL + LNCCLNQ.DATA.TOT_BAL;
                }
            }
            CEP.TRC(SCCGWA, WS_LN_INT_TOTAL);
            CEP.TRC(SCCGWA, WS_LN_BAL_TOTAL);
            if (WS_LN_BAL_TOTAL > 0) {
                WS_AVG_LN_RATE = WS_LN_INT_TOTAL / WS_LN_BAL_TOTAL;
                bigD = new BigDecimal(WS_AVG_LN_RATE);
                WS_AVG_LN_RATE = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
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
                    WS_AVG_LN_RATE = WS_LN_RAT[WS_LN_CNT2-1];
                }
            }
            CEP.TRC(SCCGWA, WS_AVG_LN_RATE);
            T000_READNEXT_DCTIPR();
            if (pgmRtn) return;
        }
        T000_ENDBR_DCTIPR();
        if (pgmRtn) return;
        if (WS_LN_OVER == 'Y') {
            WS_AVG_LN_RATE = 0;
            WS_LN_BAL_TOTAL = 0;
        }
    }
    public void B200_GET_DD_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIMCCY);
        DDCIMCCY.DATA[1-1].AC = DCCUJDH.AC_NO;
        DDCIMCCY.DATA[1-1].CCY = DCCUJDH.CCY;
        DDCIMCCY.DATA[1-1].CCY_TYPE = DCCUJDH.CCY_TYPE;
        S000_CALL_DDZIMCCY();
        if (pgmRtn) return;
        WS_DD_BAL = DDCIMCCY.DATA[1-1].LAST_DAYEND_BAL;
        WS_DD_BAL2 = DDCIMCCY.DATA[1-1].LAST_DAYEND_BAL;
        CEP.TRC(SCCGWA, WS_DD_BAL);
        IBS.init(SCCGWA, BPCCINTI);
        BPCCINTI.FUNC = 'I';
        BPCCINTI.BASE_INFO.DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCCINTI.BASE_INFO.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCCINTI.BASE_INFO.BASE_TYP = "A01";
        BPCCINTI.BASE_INFO.TENOR = "D000";
        BPCCINTI.BASE_INFO.CCY = DCCUJDH.CCY;
        S000_CALL_BPZCINTI();
        if (pgmRtn) return;
        WS_DD_RATE = BPCCINTI.BASE_INFO.OWN_RATE;
    }
    public void B300_INCOME_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = DCCUJDH.PROD_CODE;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        if (BPCPQPRD.PARM_CODE.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_PROD_REC_NOTFND, DCCUJDH.RC);
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
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_IRPRD_REC_NOTFND, DCCUJDH.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
