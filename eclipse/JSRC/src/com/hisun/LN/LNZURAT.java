package com.hisun.LN;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZURAT {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    char WS_GMST_END = ' ';
    char WS_RATE_FLT_FLG = ' ';
    char WS_RATE_CPAR_MTH = ' ';
    char WS_FLT_MTH = ' ';
    double WS_COMP_RATE = 0;
    double WS_DIFF_RATE = 0;
    double WS_RATE = 0;
    double WS_RATE1 = 0;
    double WS_RATE2 = 0;
    double WS_RATE3 = 0;
    String WS_RT_CODE = " ";
    String WS_RT_TYPE = " ";
    String WS_RT_CLASS = " ";
    double WS_SPREAD_PNT = 0;
    double WS_SPREAD_PCT = 0;
    int WS_VAL_DATE = 0;
    int WS_RAT_CNT = 0;
    char WS_RAT_FLG = ' ';
    BPCCINTI BPCCINTI = new BPCCINTI();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    LNCURAT LNCURAT;
    public void MP(SCCGWA SCCGWA, LNCURAT LNCURAT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCURAT = LNCURAT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZURAT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        WS_RATE_FLT_FLG = LNCURAT.RFLT_FLG;
        WS_RATE_CPAR_MTH = LNCURAT.COMP_MTH;
        WS_FLT_MTH = LNCURAT.FLT_MTH;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK_PROC();
        if (pgmRtn) return;
        B010_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B000_CHECK_PROC() throws IOException,SQLException,Exception {
        if (LNCURAT.IRAT_CD1.trim().length() > 0) {
            WS_RAT_FLG = 'Y';
        } else {
        }
        if (LNCURAT.IRATCLS1.trim().length() > 0) {
            WS_RAT_FLG = 'Y';
        } else {
        }
        if (LNCURAT.INT_RTV1 != 0) {
            WS_RAT_FLG = 'Y';
        } else {
        }
        if (LNCURAT.RAT_PCT1 != 0) {
            WS_RAT_FLG = 'Y';
        } else {
        }
        if (LNCURAT.INT_RAT1 != 0) {
            WS_RAT_FLG = 'Y';
        } else {
        }
        if (LNCURAT.IRAT_CD2.trim().length() > 0) {
            WS_RAT_FLG = 'Y';
        } else {
        }
        if (LNCURAT.IRATCLS2.trim().length() > 0) {
            WS_RAT_FLG = 'Y';
        } else {
        }
        if (LNCURAT.INT_RTV2 != 0) {
            WS_RAT_FLG = 'Y';
        } else {
        }
        if (LNCURAT.RAT_PCT2 != 0) {
            WS_RAT_FLG = 'Y';
        } else {
        }
        if (LNCURAT.INT_RAT2 != 0) {
            WS_RAT_FLG = 'Y';
        } else {
        }
        if (LNCURAT.IRAT_CD3.trim().length() > 0) {
            WS_RAT_FLG = 'Y';
        } else {
        }
        if (LNCURAT.IRATCLS3.trim().length() > 0) {
            WS_RAT_FLG = 'Y';
        } else {
        }
        if (LNCURAT.INT_RTV3 != 0) {
            WS_RAT_FLG = 'Y';
        } else {
        }
        if (LNCURAT.RAT_PCT3 != 0) {
            WS_RAT_FLG = 'Y';
        } else {
        }
        if (LNCURAT.INT_RAT3 != 0) {
            WS_RAT_FLG = 'Y';
        } else {
        }
        if (LNCURAT.FLT_MTH != ' ') {
            WS_RAT_FLG = 'Y';
        } else {
        }
        if (LNCURAT.ALL_RAT != 0) {
            WS_RAT_FLG = 'Y';
        } else {
        }
        CEP.TRC(SCCGWA, WS_RAT_FLG);
        if (WS_RAT_FLG == 'Y') {
            LNCURAT.RAT_FLG = 'Y';
        }
    }
    public void B010_MAIN_PROC() throws IOException,SQLException,Exception {
        if (WS_RAT_FLG == 'Y') {
            B020_MAIN_PROC();
            if (pgmRtn) return;
        } else {
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        B020_CHECK_PROC();
        if (pgmRtn) return;
        if (LNCURAT.IRAT_CD1.trim().length() == 0 
            && LNCURAT.IRAT_CD2.trim().length() == 0 
            && LNCURAT.IRAT_CD3.trim().length() == 0) {
        } else {
            if (WS_RATE_CPAR_MTH == 'N' 
                && (LNCURAT.IRAT_CD2.trim().length() > 0 
                || LNCURAT.IRAT_CD3.trim().length() > 0 
                || LNCURAT.INT_RTV2 != 0 
                || LNCURAT.INT_RTV3 != 0)) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MORE_ONE_REFRAT, LNCURAT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            B021_SRTN_RAT_COMP();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_COMP_RATE);
            LNCURAT.COST_RAT = WS_COMP_RATE;
            LNCURAT.ALL_RAT = LNCURAT.COST_RAT + LNCURAT.PREM_RAT + LNCURAT.ADD_RAT;
            CEP.TRC(SCCGWA, LNCURAT.ALL_RAT);
            CEP.TRC(SCCGWA, LNCURAT.COST_RAT);
        }
    }
    public void B020_CHECK_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_RATE_FLT_FLG);
        if ((WS_RATE_FLT_FLG != '0' 
            && WS_RATE_FLT_FLG != '1' 
            && WS_RATE_FLT_FLG != '2' 
            && WS_RATE_FLT_FLG != '3')) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_RATE_FLT_VALID, LNCURAT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_RATE_CPAR_MTH);
        if ((WS_RATE_CPAR_MTH != 'N' 
            && WS_RATE_CPAR_MTH != 'H' 
            && WS_RATE_CPAR_MTH != 'L' 
            && WS_RATE_CPAR_MTH != 'V')) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_RATE_CMP_VALID, LNCURAT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_FLT_MTH);
        if ((WS_FLT_MTH != '0' 
            && WS_FLT_MTH != '1' 
            && WS_FLT_MTH != '2' 
            && WS_FLT_MTH != '3' 
            && WS_FLT_MTH != '4')) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FLT_MTH_VALID, LNCURAT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCURAT.PAY_YSX == '0') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CANNOT_B_LOAN, LNCURAT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (WS_RATE_FLT_FLG == '1') {
            if (LNCURAT.FLT_PERD == 0) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PERD_INPUT, LNCURAT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (LNCURAT.FLT_PUNT == ' ') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PUNT_INPUT, LNCURAT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, WS_RATE_FLT_FLG);
        CEP.TRC(SCCGWA, LNCURAT.IRAT_CD1);
        CEP.TRC(SCCGWA, LNCURAT.IRATCLS1);
        if (WS_RATE_FLT_FLG == '1' 
            || WS_RATE_FLT_FLG == '2' 
            || WS_RATE_FLT_FLG == '3') {
            if (LNCURAT.IRAT_CD1.trim().length() == 0 
                || LNCURAT.IRATCLS1.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_IRAT_CD, LNCURAT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (LNCURAT.FLT_PERD != 0 
            && LNCURAT.FLT_PUNT == ' ') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PUNT_INPUT, LNCURAT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (WS_RATE_CPAR_MTH != 'N' 
            && WS_RATE_CPAR_MTH != ' ') {
            if (LNCURAT.IRAT_CD1.trim().length() == 0 
                && LNCURAT.IRAT_CD2.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.M_E_IN_2_RT_TYP_CLAS, LNCURAT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (LNCURAT.IRAT_CD3.trim().length() == 0 
                && LNCURAT.IRAT_CD2.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.M_E_IN_2_RT_TYP_CLAS, LNCURAT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (LNCURAT.IRAT_CD1.trim().length() == 0 
                && LNCURAT.IRAT_CD3.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.M_E_IN_2_RT_TYP_CLAS, LNCURAT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (LNCURAT.IRATCLS1.trim().length() == 0 
                && LNCURAT.IRATCLS2.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.M_E_IN_2_RT_TYP_CLAS, LNCURAT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (LNCURAT.IRATCLS3.trim().length() == 0 
                && LNCURAT.IRATCLS2.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.M_E_IN_2_RT_TYP_CLAS, LNCURAT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (LNCURAT.IRATCLS1.trim().length() == 0 
                && LNCURAT.IRATCLS3.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.M_E_IN_2_RT_TYP_CLAS, LNCURAT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B021_SRTN_RAT_COMP() throws IOException,SQLException,Exception {
        WS_RATE = 0;
        WS_RATE1 = 0;
        WS_RT_TYPE = LNCURAT.IRAT_CD1;
        WS_RT_CLASS = LNCURAT.IRATCLS1;
        WS_VAL_DATE = LNCURAT.VAL_DATE;
        WS_SPREAD_PNT = LNCURAT.INT_RTV1;
        WS_SPREAD_PCT = LNCURAT.RAT_PCT1;
        CEP.TRC(SCCGWA, "YE111");
        CEP.TRC(SCCGWA, WS_RT_TYPE);
        CEP.TRC(SCCGWA, WS_RT_CLASS);
        CEP.TRC(SCCGWA, WS_SPREAD_PNT);
        CEP.TRC(SCCGWA, WS_SPREAD_PCT);
        R000_GET_TYPE_RATE();
        if (pgmRtn) return;
        WS_RATE1 = WS_RATE;
        WS_COMP_RATE = WS_RATE;
        LNCURAT.INT_RAT1 = WS_RATE;
        CEP.TRC(SCCGWA, LNCURAT.INT_RAT1);
        if (WS_RATE_CPAR_MTH != 'N' 
            && WS_RATE_CPAR_MTH != ' ') {
            R100_GEN_CPAR_RATE();
            if (pgmRtn) return;
            LNCURAT.INT_RAT2 = WS_RATE2;
            LNCURAT.INT_RAT3 = WS_RATE3;
        }
    }
    public void R000_GET_TYPE_RATE() throws IOException,SQLException,Exception {
        WS_RATE = 0;
        if (WS_RT_TYPE.trim().length() > 0) {
            IBS.init(SCCGWA, BPCCINTI);
            BPCCINTI.BASE_INFO.DT = WS_VAL_DATE;
            BPCCINTI.BASE_INFO.CCY = LNCURAT.CCY;
            BPCCINTI.BASE_INFO.BASE_TYP = WS_RT_TYPE;
            BPCCINTI.BASE_INFO.TENOR = WS_RT_CLASS;
            BPCCINTI.FUNC = 'I';
            CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.DT);
            S000_CALL_BPZCINTI();
            if (pgmRtn) return;
            WS_RATE = BPCCINTI.BASE_INFO.RATE;
        }
        CEP.TRC(SCCGWA, WS_FLT_MTH);
        if (WS_FLT_MTH == '0') {
            if (WS_SPREAD_PCT != 0 
                && WS_SPREAD_PNT != 0) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_E_NO_IN_PEN_PER_PNT, LNCURAT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (WS_FLT_MTH == '1') {
            CEP.TRC(SCCGWA, WS_SPREAD_PNT);
            WS_RATE = WS_RATE + WS_SPREAD_PNT;
        }
        if (WS_FLT_MTH == '2') {
            CEP.TRC(SCCGWA, WS_SPREAD_PCT);
            WS_RATE = WS_RATE * ( 1 + WS_SPREAD_PCT / 100 );
        }
        if (WS_FLT_MTH == '3') {
            CEP.TRC(SCCGWA, WS_SPREAD_PNT);
            CEP.TRC(SCCGWA, WS_SPREAD_PCT);
            if (WS_SPREAD_PCT == 0 
                || WS_SPREAD_PNT == 0) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_E_M_IN_PER_PNT, LNCURAT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            WS_RATE = WS_RATE * ( 1 + WS_SPREAD_PCT / 100 ) + WS_SPREAD_PNT;
        }
        if (WS_FLT_MTH == '4') {
            CEP.TRC(SCCGWA, WS_SPREAD_PNT);
            CEP.TRC(SCCGWA, WS_SPREAD_PCT);
            if (WS_SPREAD_PCT == 0 
                || WS_SPREAD_PNT == 0) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_E_M_IN_PER_PNT, LNCURAT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            WS_RATE = ( WS_RATE + WS_SPREAD_PNT ) * ( 1 + WS_SPREAD_PCT / 100 );
        }
    }
    public void R100_GEN_CPAR_RATE() throws IOException,SQLException,Exception {
        WS_RATE = 0;
        WS_RATE2 = 0;
        WS_RT_TYPE = LNCURAT.IRAT_CD2;
        WS_RT_CLASS = LNCURAT.IRATCLS2;
        WS_VAL_DATE = LNCURAT.VAL_DATE;
        WS_SPREAD_PNT = LNCURAT.INT_RTV2;
        WS_SPREAD_PCT = LNCURAT.RAT_PCT2;
        CEP.TRC(SCCGWA, "YE222");
        CEP.TRC(SCCGWA, LNCURAT.IRATCLS2);
        CEP.TRC(SCCGWA, WS_RT_TYPE);
        CEP.TRC(SCCGWA, WS_RT_CLASS);
        R000_GET_TYPE_RATE();
        if (pgmRtn) return;
        WS_RATE2 = WS_RATE;
        if (WS_RATE2 != 0) {
            if (WS_RATE_CPAR_MTH == 'H') {
                if (WS_RATE2 > WS_COMP_RATE) {
                    WS_COMP_RATE = WS_RATE2;
                }
            }
            if (WS_RATE_CPAR_MTH == 'L') {
                if (WS_RATE2 < WS_COMP_RATE) {
                    WS_COMP_RATE = WS_RATE2;
                }
            }
        }
        WS_RATE = 0;
        WS_RATE3 = 0;
        WS_RT_TYPE = LNCURAT.IRAT_CD3;
        WS_RT_CLASS = LNCURAT.IRATCLS3;
        WS_VAL_DATE = LNCURAT.VAL_DATE;
        WS_SPREAD_PNT = LNCURAT.INT_RTV3;
        WS_SPREAD_PCT = LNCURAT.RAT_PCT3;
        CEP.TRC(SCCGWA, "YE333");
        CEP.TRC(SCCGWA, WS_RT_TYPE);
        CEP.TRC(SCCGWA, WS_RT_CLASS);
        R000_GET_TYPE_RATE();
        if (pgmRtn) return;
        WS_RATE3 = WS_RATE;
        if (WS_RATE3 != 0) {
            if (WS_RATE_CPAR_MTH == 'H') {
                if (WS_RATE3 > WS_COMP_RATE) {
                    WS_COMP_RATE = WS_RATE3;
                }
            }
            if (WS_RATE_CPAR_MTH == 'L') {
                if (WS_RATE3 < WS_COMP_RATE) {
                    WS_COMP_RATE = WS_RATE3;
                }
            }
        }
        if (WS_RATE_CPAR_MTH == 'V') {
            WS_RAT_CNT = 0;
            if (WS_RATE1 != 0) {
                WS_RAT_CNT += 1;
            }
            if (WS_RATE2 != 0) {
                WS_RAT_CNT += 1;
            }
            if (WS_RATE3 != 0) {
                WS_RAT_CNT += 1;
            }
            if (WS_RAT_CNT == 0) {
                WS_RAT_CNT = 1;
            }
            WS_COMP_RATE = ( WS_RATE1 + WS_RATE2 + WS_RATE3 ) / WS_RAT_CNT;
        }
    }
    public void S000_CALL_BPZCINTI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-C-INTR-INQ", BPCCINTI);
        if (BPCCINTI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCCINTI.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCURAT.RC);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCURAT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCURAT.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCURAT=");
            CEP.TRC(SCCGWA, LNCURAT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
