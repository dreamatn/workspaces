package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;
import com.hisun.BP.*;
import com.hisun.DP.*;

import java.io.IOException;
import java.sql.SQLException;

public class TDOT6810 {
    DBParm BPTIRAT_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_IRUL_TYP = "TIRUL";
    String K_ZJ_TYPE = "01";
    String K_JLT_TYPE = "03";
    String K_DZ_TYPE = "05";
    String K_ZNT_TYPE = "09";
    String K_0G_TYPE = "02";
    String K_CHNL_DZ = "EBS";
    String K_CHNL_ZJ = "FIM";
    String K_OUTPUT_FMT = "TD681";
    String K_BASE_RAT_TYP = "A02";
    String WS_MSGID = " ";
    String WS_MMO = " ";
    short WS_FLD_NO = 0;
    TDOT6810_CP_PROD_CD CP_PROD_CD = new TDOT6810_CP_PROD_CD();
    String WS_RUL_CDC = " ";
    TDOT6810_WS_INT_RAT WS_INT_RAT = new TDOT6810_WS_INT_RAT();
    char WS_CCY_FOUND = ' ';
    short WS_CNT = 0;
    short WS_I = 0;
    short WS_W = 0;
    String WS_MIN_CCYC = " ";
    double WS_MIN_AMTC = 0;
    double WS_MIN_DRAW_AMT = 0;
    double WS_MIN_LEFT_AMTC = 0;
    double WS_MAX_AMTC = 0;
    String WS_FORMAL_TERM = " ";
    char WS_CALD_FLG = ' ';
    String WS_IRAT_CD = " ";
    char WS_SPE_FLG = ' ';
    String WS_PR_IRAT_CD = " ";
    char WS_PRD_RAT_XZ = ' ';
    double WS_MAX_PNT = 0;
    double WS_MAX_PCT = 0;
    short WS_AGE = 0;
    char WS_SVR_LVL = ' ';
    char WS_SEX = ' ';
    char WS_BIRTH_DT = ' ';
    char WS_GRS_NO = ' ';
    short WS_TIME = 0;
    short WS_TIM = 0;
    short WS_TI_CHK = 0;
    short WS_TIME2 = 0;
    double WS_RAT_1 = 0;
    double WS_INT_1 = 0;
    short WS_GWA_MATH = 0;
    short WS_AGE_MATH = 0;
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCBINF SCCBINF = new SCCBINF();
    CICCUST CICCUST = new CICCUST();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    TDCCEINT TDCCEINT = new TDCCEINT();
    TDCIRULP TDCIRULP = new TDCIRULP();
    DPCPARMP DPCPARMP = new DPCPARMP();
    TDCPRDP TDCPRDP = new TDCPRDP();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    CICKIDD CICKIDD = new CICKIDD();
    TDRCIIN TDRCIIN = new TDRCIIN();
    CICOKIDD CICOKIDD = new CICOKIDD();
    TDCPROD TDCPROD = new TDCPROD();
    CICSGRS CICSGRS = new CICSGRS();
    TDRRACD TDRRACD = new TDRRACD();
    TDCPIOD TDCPIOD = new TDCPIOD();
    BPRIRAT BPRIRAT = new BPRIRAT();
    SCCGWA SCCGWA;
    TDB6810_AWA_6810 TDB6810_AWA_6810;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDOT6810 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "TDB6810_AWA_6810>");
        TDB6810_AWA_6810 = (TDB6810_AWA_6810) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "JFTT");
        CEP.TRC(SCCGWA, TDB6810_AWA_6810.SPRD_PCT);
        CEP.TRC(SCCGWA, TDB6810_AWA_6810.SPRD_PNT);
        B010_GET_PROD_MODEL_PROC();
        if (pgmRtn) return;
        S000_CALL_TDZPROD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDCPIOD.ACTI_NO);
        B050_CAL_EXP_INT();
        if (pgmRtn) return;
        B060_OUTPUT_INF();
        if (pgmRtn) return;
    }
    public void B010_GET_PROD_MODEL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        CEP.TRC(SCCGWA, TDB6810_AWA_6810.PROD_CD);
        BPCPQPRD.PRDT_CODE = TDB6810_AWA_6810.PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQPRD.BUSI_TYPE);
        CEP.TRC(SCCGWA, BPCPQPRD.CTRACT_GROUP);
        CEP.TRC(SCCGWA, BPCPQPRD.PRDT_MODEL);
        DPCPARMP.AC_TYPE = BPCPQPRD.AC_TYPE;
    }
    public void T000_READ_BPTIRAT() throws IOException,SQLException,Exception {
        BPTIRAT_RD = new DBParm();
        BPTIRAT_RD.TableName = "BPTIRAT";
        IBS.READ(SCCGWA, BPRIRAT, BPTIRAT_RD);
    }
    public void B060_OUTPUT_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_INT_RAT;
        SCCFMT.DATA_LEN = 29;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B050_CAL_EXP_INT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCCEINT);
        CEP.TRC(SCCGWA, TDCPIOD.ACTI_NO);
        TDCCEINT.IRUL_PTR = TDCPIOD;
        TDCCEINT.OPT = '0';
        if (TDB6810_AWA_6810.INT_RAT == 0 
            && TDB6810_AWA_6810.RUL_CD.trim().length() == 0 
            && TDB6810_AWA_6810.SPRD_PNT == 0 
            && TDB6810_AWA_6810.SPRD_PCT == 0) {
            TDCCEINT.XZ_FLG = '0';
        }
        CEP.TRC(SCCGWA, TDCCEINT.XZ_FLG);
        TDCCEINT.RAT_1 = TDB6810_AWA_6810.INT_RAT;
        TDCCEINT.RAT_XS = TDB6810_AWA_6810.INT_RAT;
        TDCCEINT.RAT = TDB6810_AWA_6810.INT_RAT;
        TDCCEINT.PROD_CD = TDB6810_AWA_6810.PROD_CD;
        TDCCEINT.CI_NO = TDB6810_AWA_6810.CI_NO;
        TDCCEINT.CCY = TDB6810_AWA_6810.CCY;
        TDCCEINT.TXN_AMT = TDB6810_AWA_6810.TXN_AMT;
        TDCCEINT.VAL_DATE = TDB6810_AWA_6810.VAL_DATE;
        TDCCEINT.EXP_DATE = TDB6810_AWA_6810.EXP_DATE;
        TDCCEINT.CD_PERD = TDB6810_AWA_6810.CD_PERD;
        TDCCEINT.CD_AMT = TDB6810_AWA_6810.CD_AMT;
        TDCCEINT.IRAT_CD = WS_IRAT_CD;
        CEP.TRC(SCCGWA, TDCCEINT.IRAT_CD);
        TDCCEINT.CALR_STD = TDB6810_AWA_6810.CALR_STD;
        TDCCEINT.AC_RUL_CD = TDB6810_AWA_6810.RUL_CD;
        TDCCEINT.SPRD_PNT = TDB6810_AWA_6810.SPRD_PNT;
        TDCCEINT.SPRD_PCT = TDB6810_AWA_6810.SPRD_PCT;
        TDCCEINT.TXN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDCCEINT.OPEN_BK = SCCGWA.COMM_AREA.TR_BANK;
        TDCCEINT.PRDAC_CD = BPCPQPRD.AC_TYPE;
        CEP.TRC(SCCGWA, TDCCEINT.PRDAC_CD);
        CEP.TRC(SCCGWA, TDCCEINT.PROD_CD);
        TDCCEINT.CI_TYP = CICCUST.O_DATA.O_CI_TYP;
        TDCCEINT.SRV_LVL = CICCUST.O_DATA.O_SVR_LVL;
        if ((DPCPARMP.AC_TYPE.equalsIgnoreCase("025") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("026")) 
            && TDB6810_AWA_6810.TERM.equalsIgnoreCase("Y006")) {
            TDCCEINT.TERM = "Y005";
        } else {
            TDCCEINT.TERM = TDB6810_AWA_6810.TERM;
        }
        TDCCEINT.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        CEP.TRC(SCCGWA, TDCCEINT.CALD_FLG);
        if (TDB6810_AWA_6810.RULE != ' ') {
            TDCCEINT.CALD_FLG = TDB6810_AWA_6810.RULE;
        }
        if (TDCCEINT.IRAT_CD.trim().length() > 0) {
            S000_CALL_TDZCEINT();
            if (pgmRtn) return;
            WS_INT_RAT.INT_RAT = TDCCEINT.RAT;
            WS_INT_RAT.WS_INT = TDCCEINT.INT;
        } else {
            WS_INT_RAT.INT_RAT = 0;
        }
        if ((DPCPARMP.AC_TYPE.equalsIgnoreCase("022") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("023") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("036"))) {
            WS_INT_RAT.WS_INT = 0;
        }
        CEP.TRC(SCCGWA, WS_INT_RAT.INT_RAT);
        CEP.TRC(SCCGWA, WS_INT_RAT.WS_INT);
        if (TDB6810_AWA_6810.INT_RAT != 0 
            || TDB6810_AWA_6810.SPRD_PNT != 0 
            || TDB6810_AWA_6810.SPRD_PCT != 0 
            || TDB6810_AWA_6810.RUL_CD.trim().length() > 0) {
            IBS.init(SCCGWA, BPRIRAT);
            BPRIRAT.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRIRAT.KEY.CCY = TDB6810_AWA_6810.CCY;
            BPRIRAT.KEY.BASE_TYP = WS_IRAT_CD;
            BPRIRAT.KEY.TENOR = TDB6810_AWA_6810.TERM;
            T000_READ_BPTIRAT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPRIRAT.HIGH);
            CEP.TRC(SCCGWA, BPRIRAT.LOW);
            CEP.TRC(SCCGWA, WS_INT_RAT.INT_RAT);
            if (BPRIRAT.HIGH > 0) {
                if (WS_INT_RAT.INT_RAT > BPRIRAT.HIGH) {
                    CEP.TRC(SCCGWA, "ERR-HIGH");
                    CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_RAT_ERR);
                }
                if (WS_INT_RAT.INT_RAT < BPRIRAT.LOW) {
                    CEP.TRC(SCCGWA, "ERR-HIGH");
                    CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_RAT_ERR);
                }
            }
        }
    }
    public void S000_CALL_CICCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
    }
    public void S000_CALL_CICSGRS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SEARCH-GRS", CICSGRS);
    }
    public void S000_LINK_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            CEP.ERR(SCCGWA, WS_MSGID);
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            CEP.ERR(SCCGWA, WS_MSGID);
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_TDZPROD() throws IOException,SQLException,Exception {
        TDCPIOD.PROD_CD = BPCPQPRD.PARM_CODE;
        TDCPIOD.C_PROD_CD = TDB6810_AWA_6810.PROD_CD;
        if (TDB6810_AWA_6810.ACTI_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, TDB6810_AWA_6810.TERM);
            TDCPIOD.INTERM = TDB6810_AWA_6810.TERM;
            TDCPIOD.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            TDCPIOD.CCY = TDB6810_AWA_6810.CCY;
        } else {
            TDCPIOD.ACTI_NO = TDB6810_AWA_6810.ACTI_NO;
        }
        TDCPIOD.BAL = TDB6810_AWA_6810.TXN_AMT;
        TDCPIOD.ACCRU_FLG = 'Y';
        IBS.CALLCPN(SCCGWA, "TD-GET-PROD", TDCPIOD);
        while (WS_TIME2 < 24 
            && !TDB6810_AWA_6810.CCY.equalsIgnoreCase(TDCPIOD.OTH_PRM.CCY_INF[WS_TIME2-1].MIN_CCYC)) {
            CEP.TRC(SCCGWA, WS_TIME2);
            WS_TIME2 += 1;
            if (TDB6810_AWA_6810.CCY.equalsIgnoreCase(TDCPIOD.OTH_PRM.CCY_INF[WS_TIME2-1].MIN_CCYC)) {
                CEP.TRC(SCCGWA, WS_TIME2);
                WS_IRAT_CD = TDCPIOD.OTH_PRM.CCY_INF[WS_TIME2-1].RAT_CD;
                if (DPCPARMP.AC_TYPE.equalsIgnoreCase("026")) {
                    WS_IRAT_CD = TDCPIOD.OTH_PRM.CCY_INF[WS_TIME2-1].PRRAT_CD;
                }
                CEP.TRC(SCCGWA, WS_IRAT_CD);
            }
        }
        CEP.TRC(SCCGWA, TDB6810_AWA_6810.RAT_TYP);
        if (TDB6810_AWA_6810.RAT_TYP.trim().length() > 0) {
            WS_IRAT_CD = TDB6810_AWA_6810.RAT_TYP;
        }
    }
    public void S000_CALL_TDZCEINT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-CAL-EXP-INT", TDCCEINT);
        if (TDCCEINT.RC_MSG.RC != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, TDCCEINT.RC_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, SCCBINF);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
