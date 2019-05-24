package com.hisun.TD;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCSUBS;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class TDZSINQ {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm TDTOTHE_RD;
    DBParm TDTCMST_RD;
    brParm TDTOTHE_BR = new brParm();
    boolean pgmRtn = false;
    String K_TD_KD_SEQ = "KDSEQ";
    String K_OUTPUT_FMT1 = "TD588";
    String K_PRDP_TYP = "PRDPR";
    String WS_ERR_MSG = " ";
    short WS_CNT = 0;
    int WS_L_CNT = 0;
    int WS_Q_CNT = 0;
    int WS_P_ROW = 0;
    int WS_P_NUM = 0;
    int WS_T_PAGE = 0;
    int WS_L_ROW = 0;
    int WS_NUM1 = 0;
    int WS_NUM2 = 0;
    int WS_STR_NUM = 0;
    int WS_END_NUM = 0;
    char WS_CCY_FOUND = ' ';
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    char WS_DEL_FLG = ' ';
    char WS_DUP_FLG = ' ';
    char WS_EFF_TYP = ' ';
    char WS_OTHE_FND = ' ';
    TDZSINQ_CP_PROD_CD CP_PROD_CD = new TDZSINQ_CP_PROD_CD();
    short WS_TIME = 0;
    int WS_COUNT = 0;
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    TDRDOCU TDRDOCU = new TDRDOCU();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCBINF SCCBINF = new SCCBINF();
    SCCSUBS SCCSUBS = new SCCSUBS();
    TDRCMST TDRCMST = new TDRCMST();
    CICCUST CICCUST = new CICCUST();
    CICSGRS CICSGRS = new CICSGRS();
    TDCSONQ TDCSONQ = new TDCSONQ();
    TDROTHE TDROTHE = new TDROTHE();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    TDCPRDP TDCPRDP = new TDCPRDP();
    TDCQPMP TDCQPMP = new TDCQPMP();
    TDCPROD TDCPROD = new TDCPROD();
    SCCGWA SCCGWA;
    TDCSINQ TDCSINQ;
    public void MP(SCCGWA SCCGWA, TDCSINQ TDCSINQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCSINQ = TDCSINQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDZSINQ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_MSG_CI_DATA();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCSINQ.FUNC);
        CEP.TRC(SCCGWA, TDCSINQ.CI_NO);
        CEP.TRC(SCCGWA, TDCSINQ.AC_NO);
        CEP.TRC(SCCGWA, TDCSINQ.PROD_CD);
        CEP.TRC(SCCGWA, TDCSINQ.PAGE_ROW);
        CEP.TRC(SCCGWA, TDCSINQ.PAGE_NUM);
        if (TDCSINQ.AC_NO.trim().length() == 0 
            && TDCSINQ.CI_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_FIELD_MUST_IPT);
        }
        CEP.TRC(SCCGWA, TDCSINQ.PROD_CD);
        if (TDCSINQ.PROD_CD.trim().length() == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_FIELD_MUST_IPT);
        }
    }
    public void B020_MSG_CI_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCSONQ);
        IBS.init(SCCGWA, CICCUST);
        CEP.TRC(SCCGWA, TDCSINQ.AC_NO);
        CEP.TRC(SCCGWA, TDCSINQ.CI_NO);
        if (TDCSINQ.AC_NO.trim().length() == 0) {
            CICCUST.FUNC = 'C';
            CICCUST.DATA.CI_NO = TDCSINQ.CI_NO;
        } else {
            CICCUST.FUNC = 'A';
            CICCUST.DATA.AGR_NO = TDCSINQ.AC_NO;
        }
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        IBS.init(SCCGWA, TDROTHE);
        TDROTHE.PROD_CD = TDCSINQ.PROD_CD;
        TDROTHE.KEY.ACTI_NO = "" + 0X00;
        JIBS_tmp_int = TDROTHE.KEY.ACTI_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) TDROTHE.KEY.ACTI_NO = "0" + TDROTHE.KEY.ACTI_NO;
        TDROTHE.STR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDROTHE.END_DATE = SCCGWA.COMM_AREA.AC_DATE;
        B015_GET_PROD_INFO();
        if (pgmRtn) return;
        if ((CICCUST.O_DATA.O_CI_TYP == '1' 
            && BPCPQPRD.CUS_PER_FLG != '0') 
            || (CICCUST.O_DATA.O_CI_TYP == '2' 
            && BPCPQPRD.CUS_COM_FLG != '0') 
            || (CICCUST.O_DATA.O_CI_TYP == '3' 
            && BPCPQPRD.CUS_FIN_FLG != '0')) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
        }
        TDCSONQ.PROD_NAME = BPCPQPRD.PRDT_NAME;
        TDCSONQ.PART_NUM = TDCPRDP.TXN_PRM.PART_NUM;
        R000_TRANS_PAGE_ROW();
        if (pgmRtn) return;
        T000_GROUP_TDTOTHE_1();
        if (pgmRtn) return;
        WS_L_CNT = WS_COUNT;
        CEP.TRC(SCCGWA, WS_L_CNT);
        R000_PAGE_COM();
        if (pgmRtn) return;
        IBS.init(SCCGWA, TDROTHE);
        TDROTHE.PROD_CD = TDCSINQ.PROD_CD;
        TDROTHE.KEY.ACTI_NO = "" + 0X00;
        JIBS_tmp_int = TDROTHE.KEY.ACTI_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) TDROTHE.KEY.ACTI_NO = "0" + TDROTHE.KEY.ACTI_NO;
        TDROTHE.STR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDROTHE.END_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_STARTBR_TDTOTHE();
        if (pgmRtn) return;
        T000_READNEXT_TDTOTHE();
        if (pgmRtn) return;
        WS_TIME = 1;
        WS_NUM2 = 1;
        while (WS_OTHE_FND != 'N') {
            CEP.TRC(SCCGWA, TDROTHE.GRPS_NO);
            IBS.init(SCCGWA, CICSGRS);
            CICSGRS.DATA.CI_NO = TDCSINQ.CI_NO;
            CICSGRS.DATA.MULTI_DATA[1-1].GRS_NO = TDROTHE.GRPS_NO;
            S000_CALL_CIZSGRS();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICSGRS.OUT_DATA[1-1].GRPS_NO);
            if (CICSGRS.OUT_DATA[1-1].GRPS_NO.equalsIgnoreCase(CICSGRS.DATA.MULTI_DATA[1-1].GRS_NO)) {
                WS_NUM1 = WS_NUM1 + 1;
            }
            CEP.TRC(SCCGWA, WS_NUM1);
            if (WS_NUM1 > WS_STR_NUM 
                && WS_NUM1 <= WS_END_NUM 
                && CICSGRS.OUT_DATA[1-1].GRPS_NO.equalsIgnoreCase(CICSGRS.DATA.MULTI_DATA[1-1].GRS_NO)) {
                B030_OUTPUT_DATA();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, WS_NUM2);
                WS_NUM2 += 1;
            }
            T000_READNEXT_TDTOTHE();
            if (pgmRtn) return;
        }
        T000_ENDBR_TDTOTHE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_TIME);
        CEP.TRC(SCCGWA, WS_L_CNT);
        if (WS_L_CNT == 0) {
            TDCSONQ.TOTAL_NUM = 0;
            TDCSONQ.TOTAL_PAGE = 0;
            TDCSONQ.CURR_PAGE = 0;
            TDCSONQ.LAST_PAGE = 'Y';
            TDCSONQ.PAGE_ROW = 0;
        }
        B040_OUTPUT_A_DATA();
        if (pgmRtn) return;
    }
    public void B030_OUTPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDROTHE.KEY.ACTI_NO);
        CEP.TRC(SCCGWA, TDROTHE.PROD_CD);
        CEP.TRC(SCCGWA, TDROTHE.ACTI_FLG);
        CEP.TRC(SCCGWA, TDROTHE.BR);
        CEP.TRC(SCCGWA, TDROTHE.STR_DATE);
        CEP.TRC(SCCGWA, TDROTHE.END_DATE);
        CEP.TRC(SCCGWA, TDROTHE.SDT);
        CEP.TRC(SCCGWA, TDROTHE.DDT);
        CEP.TRC(SCCGWA, TDROTHE.MIN_BAL);
        CEP.TRC(SCCGWA, TDROTHE.ADD_AMT);
        CEP.TRC(SCCGWA, TDROTHE.MAX_BAL);
        CEP.TRC(SCCGWA, TDROTHE.CCY);
        CEP.TRC(SCCGWA, TDROTHE.TERM);
        CEP.TRC(SCCGWA, TDROTHE.MIN_RAT);
        CEP.TRC(SCCGWA, TDROTHE.MAX_RAT);
        CEP.TRC(SCCGWA, TDROTHE.RAT_TYP);
        CEP.TRC(SCCGWA, TDROTHE.PCT_S);
        CEP.TRC(SCCGWA, TDROTHE.FLT_RAT);
        CEP.TRC(SCCGWA, TDROTHE.RUL_CD);
        CEP.TRC(SCCGWA, TDROTHE.CONT_RAT);
        CEP.TRC(SCCGWA, TDROTHE.AFF_TYP);
        CEP.TRC(SCCGWA, TDROTHE.INT_PERD);
        CEP.TRC(SCCGWA, TDROTHE.CD_PERD);
        CEP.TRC(SCCGWA, TDROTHE.TRAN_TYP);
        CEP.TRC(SCCGWA, TDROTHE.REDE_TYP);
        CEP.TRC(SCCGWA, TDROTHE.PLED_TYP);
        CEP.TRC(SCCGWA, TDROTHE.EARLY_TYP);
        CEP.TRC(SCCGWA, TDROTHE.PRV_RAT);
        CEP.TRC(SCCGWA, TDROTHE.DOCU_NO);
        CEP.TRC(SCCGWA, TDROTHE.LATE_TYP);
        CEP.TRC(SCCGWA, TDROTHE.OVE_RAT);
        CEP.TRC(SCCGWA, TDROTHE.RES_TYP);
        CEP.TRC(SCCGWA, TDROTHE.DUE_RAT);
        CEP.TRC(SCCGWA, TDROTHE.CHNL_NO);
        CEP.TRC(SCCGWA, TDROTHE.SET_FLG);
        CEP.TRC(SCCGWA, TDROTHE.LST_TYP);
        CEP.TRC(SCCGWA, TDROTHE.LSTDT_TYP);
        CEP.TRC(SCCGWA, TDROTHE.GRPS_NO);
        TDCSONQ.DATA[WS_NUM2-1].ACTI_NO = TDROTHE.KEY.ACTI_NO;
        TDCSONQ.DATA[WS_NUM2-1].PROD_CD = TDROTHE.PROD_CD;
        TDCSONQ.DATA[WS_NUM2-1].BR = TDROTHE.BR;
        TDCSONQ.DATA[WS_NUM2-1].STR_DATE = TDROTHE.STR_DATE;
        TDCSONQ.DATA[WS_NUM2-1].END_DATE = TDROTHE.END_DATE;
        TDCSONQ.DATA[WS_NUM2-1].SDT = TDROTHE.SDT;
        TDCSONQ.DATA[WS_NUM2-1].DDT = TDROTHE.DDT;
        TDCSONQ.DATA[WS_NUM2-1].MIN_BAL = TDROTHE.MIN_BAL;
        TDCSONQ.DATA[WS_NUM2-1].ADD_AMT = TDROTHE.ADD_AMT;
        TDCSONQ.DATA[WS_NUM2-1].MAX_BAL = TDROTHE.MAX_BAL;
        TDCSONQ.DATA[WS_NUM2-1].CCY = TDROTHE.CCY;
        TDCSONQ.DATA[WS_NUM2-1].TERM = TDROTHE.TERM;
        TDCSONQ.DATA[WS_NUM2-1].MIN_RAT = TDROTHE.MIN_RAT;
        TDCSONQ.DATA[WS_NUM2-1].MAX_RAT = TDROTHE.MAX_RAT;
        TDCSONQ.DATA[WS_NUM2-1].RAT_TYP = TDROTHE.RAT_TYP;
        TDCSONQ.DATA[WS_NUM2-1].PCT_S = TDROTHE.PCT_S;
        TDCSONQ.DATA[WS_NUM2-1].FLT_RAT = TDROTHE.FLT_RAT;
        TDCSONQ.DATA[WS_NUM2-1].RUL_CD = TDROTHE.RUL_CD;
        TDCSONQ.DATA[WS_NUM2-1].CONT_RAT = TDROTHE.CONT_RAT;
        TDCSONQ.DATA[WS_NUM2-1].AFF_TYP = TDROTHE.AFF_TYP;
        TDCSONQ.DATA[WS_NUM2-1].INT_PERD = TDROTHE.INT_PERD;
        TDCSONQ.DATA[WS_NUM2-1].CD_PERD = TDROTHE.CD_PERD;
        TDCSONQ.DATA[WS_NUM2-1].TRAN_TYP = TDROTHE.TRAN_TYP;
        TDCSONQ.DATA[WS_NUM2-1].REDE_TYP = TDROTHE.REDE_TYP;
        TDCSONQ.DATA[WS_NUM2-1].PLED_TYP = TDROTHE.PLED_TYP;
        TDCSONQ.DATA[WS_NUM2-1].EARLY_TYP = TDROTHE.EARLY_TYP;
        TDCSONQ.DATA[WS_NUM2-1].PRV_RAT = TDROTHE.PRV_RAT;
        TDCSONQ.DATA[WS_NUM2-1].DOCU_NO = TDROTHE.DOCU_NO;
        TDCSONQ.DATA[WS_NUM2-1].LATE_TYP = TDROTHE.LATE_TYP;
        TDCSONQ.DATA[WS_NUM2-1].OVE_RAT = TDROTHE.OVE_RAT;
        TDCSONQ.DATA[WS_NUM2-1].RES_TYP = TDROTHE.RES_TYP;
        TDCSONQ.DATA[WS_NUM2-1].DUE_RAT = TDROTHE.DUE_RAT;
        TDCSONQ.DATA[WS_NUM2-1].CHNL_NO = TDROTHE.CHNL_NO;
        TDCSONQ.DATA[WS_NUM2-1].SET_FLG = TDROTHE.SET_FLG;
        TDCSONQ.DATA[WS_NUM2-1].LST_TYP = TDROTHE.LST_TYP;
        TDCSONQ.DATA[WS_NUM2-1].LSTDT_TYP = TDROTHE.LSTDT_TYP;
        TDCSONQ.DATA[WS_NUM2-1].GRPS_NO = TDROTHE.GRPS_NO;
        CEP.TRC(SCCGWA, TDROTHE.ADD_AMT);
        if (TDROTHE.ADD_AMT == 0 
            || TDROTHE.MIN_BAL == 0) {
            R000_GET_ADDAMT();
            if (pgmRtn) return;
        }
    }
    public void B040_OUTPUT_A_DATA() throws IOException,SQLException,Exception {
        SCCFMT.FMTID = K_OUTPUT_FMT1;
        SCCFMT.DATA_PTR = TDCSONQ;
        SCCFMT.DATA_LEN = 3197;
        IBS.FMT(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, TDCSONQ.DATA[1-1].ACTI_NO);
        CEP.TRC(SCCGWA, TDCSONQ.DATA[1-1].PROD_CD);
    }
    public void B015_GET_PROD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        CEP.TRC(SCCGWA, TDCSINQ.PROD_CD);
        BPCPQPRD.PRDT_CODE = TDCSINQ.PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        IBS.init(SCCGWA, TDCQPMP);
        IBS.init(SCCGWA, TDCPRDP);
        TDCQPMP.FUNC = 'I';
        TDCQPMP.PROD_CD = BPCPQPRD.PARM_CODE;
        TDCQPMP.DAT_PTR = TDCPROD;
        S000_CALL_TDZQPMP();
        if (pgmRtn) return;
        TDCPRDP.PRDMO_CD = TDCPROD.PRDO_CDM;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.TXN_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.TXN_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.INT_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.INT_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.EXP_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.EXP_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.OTH_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.OTH_PRM);
    }
    public void R000_TRANS_PAGE_ROW() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCSINQ.PAGE_ROW);
        if (TDCSINQ.PAGE_ROW == 0) {
            WS_P_ROW = 10;
        } else {
            if (TDCSINQ.PAGE_ROW > 10) {
                WS_P_ROW = 10;
            } else {
                WS_P_ROW = TDCSINQ.PAGE_ROW;
            }
        }
        CEP.TRC(SCCGWA, TDCSINQ.PAGE_ROW);
        CEP.TRC(SCCGWA, WS_P_NUM);
        CEP.TRC(SCCGWA, WS_P_ROW);
    }
    public void R000_GET_ADDAMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = TDROTHE.PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, TDCQPMP);
        IBS.init(SCCGWA, TDCPRDP);
        TDCQPMP.FUNC = 'I';
        TDCQPMP.PROD_CD = BPCPQPRD.PARM_CODE;
        TDCQPMP.DAT_PTR = TDCPROD;
        S000_CALL_TDZQPMP();
        if (pgmRtn) return;
        TDCPRDP.PRDMO_CD = TDCPROD.PRDO_CDM;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.TXN_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.TXN_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.INT_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.INT_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.EXP_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.EXP_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.OTH_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.OTH_PRM);
        WS_CCY_FOUND = ' ';
        CEP.TRC(SCCGWA, WS_CCY_FOUND);
        for (WS_CNT = 1; WS_CNT <= 24 
            && WS_CCY_FOUND != 'Y'; WS_CNT += 1) {
            if (TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].MIN_CCYC.equalsIgnoreCase(TDROTHE.CCY)) {
                if (TDROTHE.ADD_AMT == 0) {
                    TDCSONQ.DATA[WS_NUM2-1].ADD_AMT = TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].ADD_AMTC;
                }
                if (TDROTHE.MIN_BAL == 0) {
                    TDCSONQ.DATA[WS_NUM2-1].MIN_BAL = TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].MIN_AMTC;
                }
                WS_CCY_FOUND = 'Y';
            }
        }
        CEP.TRC(SCCGWA, WS_CCY_FOUND);
        CEP.TRC(SCCGWA, TDROTHE.CCY);
        CEP.TRC(SCCGWA, TDCSONQ.DATA[WS_NUM2-1].ADD_AMT);
    }
    public void R000_PAGE_COM() throws IOException,SQLException,Exception {
        if (WS_L_CNT != 0) {
            CEP.TRC(SCCGWA, WS_L_CNT);
            TDCSONQ.TOTAL_NUM = WS_L_CNT;
            WS_L_ROW = TDCSONQ.TOTAL_NUM % WS_P_ROW;
            WS_T_PAGE = (int) ((TDCSONQ.TOTAL_NUM - WS_L_ROW) / WS_P_ROW);
            if (WS_L_ROW == 0) {
                CEP.TRC(SCCGWA, "000000");
                TDCSONQ.TOTAL_PAGE = WS_T_PAGE;
                if (WS_L_CNT != 0) {
                    WS_L_ROW = WS_P_ROW;
                }
            } else {
                CEP.TRC(SCCGWA, "XXXXXX");
                TDCSONQ.TOTAL_PAGE = WS_T_PAGE + 1;
            }
            if (TDCSINQ.PAGE_NUM != 0) {
                if (TDCSINQ.PAGE_NUM >= TDCSONQ.TOTAL_PAGE) {
                    CEP.TRC(SCCGWA, ">>>===");
                    TDCSONQ.CURR_PAGE = TDCSONQ.TOTAL_PAGE;
                    TDCSONQ.LAST_PAGE = 'Y';
                    TDCSONQ.PAGE_ROW = WS_L_ROW;
                } else {
                    CEP.TRC(SCCGWA, "<<<<<<");
                    TDCSONQ.CURR_PAGE = TDCSINQ.PAGE_NUM;
                    TDCSONQ.LAST_PAGE = 'N';
                    TDCSONQ.PAGE_ROW = WS_P_ROW;
                }
            }
            CEP.TRC(SCCGWA, TDCSINQ.PAGE_NUM);
            if (TDCSINQ.PAGE_NUM == 0) {
                TDCSONQ.CURR_PAGE = 1;
                if (TDCSONQ.TOTAL_PAGE == 1) {
                    TDCSONQ.LAST_PAGE = 'Y';
                    TDCSONQ.PAGE_ROW = WS_L_ROW;
                } else {
                    TDCSONQ.LAST_PAGE = 'N';
                    TDCSONQ.PAGE_ROW = WS_P_ROW;
                }
            }
            CEP.TRC(SCCGWA, WS_P_NUM);
            CEP.TRC(SCCGWA, TDCSONQ.CURR_PAGE);
            WS_P_NUM = TDCSONQ.CURR_PAGE - 1;
            CEP.TRC(SCCGWA, WS_P_NUM);
            WS_STR_NUM = WS_P_NUM * WS_P_ROW;
            CEP.TRC(SCCGWA, WS_STR_NUM);
            WS_END_NUM = TDCSONQ.CURR_PAGE * WS_P_ROW;
            CEP.TRC(SCCGWA, WS_END_NUM);
            CEP.TRC(SCCGWA, "PAGE1 INFO:");
            CEP.TRC(SCCGWA, WS_L_CNT);
            CEP.TRC(SCCGWA, TDCSONQ.TOTAL_NUM);
            CEP.TRC(SCCGWA, WS_P_ROW);
            CEP.TRC(SCCGWA, WS_T_PAGE);
            CEP.TRC(SCCGWA, WS_L_ROW);
            CEP.TRC(SCCGWA, TDCSONQ.TOTAL_PAGE);
            CEP.TRC(SCCGWA, TDCSONQ.PAGE_ROW);
            CEP.TRC(SCCGWA, TDCSONQ.CURR_PAGE);
            CEP.TRC(SCCGWA, TDCSONQ.LAST_PAGE);
            CEP.TRC(SCCGWA, WS_P_NUM);
            CEP.TRC(SCCGWA, WS_STR_NUM);
            CEP.TRC(SCCGWA, WS_END_NUM);
        }
    }
    public void T000_GROUP_TDTOTHE() throws IOException,SQLException,Exception {
        TDTOTHE_RD = new DBParm();
        TDTOTHE_RD.TableName = "TDTOTHE";
        TDTOTHE_RD.set = "WS-COUNT=COUNT(*)";
        TDTOTHE_RD.where = "PROD_CD = :TDROTHE.PROD_CD "
            + "AND STR_DATE <= :TDROTHE.STR_DATE "
            + "AND END_DATE > :TDROTHE.END_DATE "
            + "AND ACTI_NO > :TDROTHE.KEY.ACTI_NO";
        IBS.GROUP(SCCGWA, TDROTHE, this, TDTOTHE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, TDROTHE.PROD_CD);
            CEP.TRC(SCCGWA, TDROTHE.KEY.ACTI_NO);
        } else {
        }
    }
    public void T000_GROUP_TDTOTHE_1() throws IOException,SQLException,Exception {
        WS_COUNT = 0;
        T000_STARTBR_TDTOTHE();
        if (pgmRtn) return;
        T000_READNEXT_TDTOTHE();
        if (pgmRtn) return;
        while (WS_OTHE_FND != 'N') {
            IBS.init(SCCGWA, CICSGRS);
            CICSGRS.DATA.CI_NO = TDCSINQ.CI_NO;
            CICSGRS.DATA.MULTI_DATA[1-1].GRS_NO = TDROTHE.GRPS_NO;
            S000_CALL_CIZSGRS();
            if (pgmRtn) return;
            if (CICSGRS.OUT_DATA[1-1].GRPS_NO.equalsIgnoreCase(CICSGRS.DATA.MULTI_DATA[1-1].GRS_NO)) {
                WS_COUNT += 1;
            }
            T000_READNEXT_TDTOTHE();
            if (pgmRtn) return;
        }
        T000_ENDBR_TDTOTHE();
        if (pgmRtn) return;
    }
    public void T000_READ_TDTCMST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRCMST.KEY.AC_NO);
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        IBS.READ(SCCGWA, TDRCMST, TDTCMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DATE_MISSING);
        }
    }
    public void T000_STARTBR_TDTOTHE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDROTHE.PROD_CD);
        CEP.TRC(SCCGWA, TDROTHE.STR_DATE);
        CEP.TRC(SCCGWA, TDROTHE.END_DATE);
        CEP.TRC(SCCGWA, TDROTHE.KEY.ACTI_NO);
        TDTOTHE_BR.rp = new DBParm();
        TDTOTHE_BR.rp.TableName = "TDTOTHE";
        TDTOTHE_BR.rp.where = "PROD_CD = :TDROTHE.PROD_CD "
            + "AND STR_DATE <= :TDROTHE.STR_DATE "
            + "AND END_DATE > :TDROTHE.END_DATE "
            + "AND ACTI_NO > :TDROTHE.KEY.ACTI_NO";
        TDTOTHE_BR.rp.order = "ACTI_NO DESC";
        IBS.STARTBR(SCCGWA, TDROTHE, this, TDTOTHE_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DATE_MISSING);
        }
    }
    public void T000_READNEXT_TDTOTHE() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDROTHE, this, TDTOTHE_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_OTHE_FND = 'N';
            } else {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DATE_MISSING);
            }
        } else {
            if (WS_TIME != 0) {
                WS_TIME += 1;
            }
            WS_OTHE_FND = 'Y';
        }
    }
    public void T000_ENDBR_TDTOTHE() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTOTHE_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DATE_MISSING);
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        CEP.TRC(SCCGWA, CICCUST.RC.RC_CODE);
        if (CICCUST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_CIZSGRS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SEARCH-GRS", CICSGRS);
        CEP.TRC(SCCGWA, CICSGRS.RC.RC_CODE);
        if (CICSGRS.RC.RC_CODE != 0 
            && CICSGRS.RC.RC_CODE != 5638) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICSGRS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_TDZQPMP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-BASE-PRD-QRY", TDCQPMP);
        CEP.TRC(SCCGWA, TDCQPMP.RC.RC_RTNCODE);
        if (TDCQPMP.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, TDCQPMP.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
