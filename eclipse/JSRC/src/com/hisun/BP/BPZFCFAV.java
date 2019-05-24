package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.DD.*;
import com.hisun.DC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFCFAV {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_GET_TOT_INFO = "BP-F-F-GET-TOT-INFO ";
    String CPN_R_FEE_PARM_MAIN = "BP-F-F-MAINTAIN-PARM";
    String CPN_CI_CISOCUST = "CI-CISOCUST";
    String CPN_CI_CISOACCU = "CI-CISOACCU";
    String K_PARM_TYPE = "FFAV ";
    String CPN_I_AC_STS_PROC = "DD-I-NFIN-M-MST";
    char WS_EMP_RECORD = ' ';
    String WS_ERR_MSG = " ";
    BPZFCFAV_WS_CAL_INFO WS_CAL_INFO = new BPZFCFAV_WS_CAL_INFO();
    String WS_CARD_TYP = " ";
    short WS_H_CNT = 0;
    char WS_SIR_CARD_FLG = ' ';
    double WS_FAV_AMT1 = 0;
    double WS_FAV_AMT2 = 0;
    String WS_ATM_AC = " ";
    int WS_ATM_NUM = 0;
    char WS_TBL_FFAV_FLAG = ' ';
    char WS_GET_AGGRAGATE = ' ';
    char WS_CI_FLG = ' ';
    char WS_AC_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    BPVFFAV BPVFFAV = new BPVFFAV();
    BPCFPARM BPCFPARM = new BPCFPARM();
    BPCFFTOT BPCFFTOT = new BPCFFTOT();
    CICACCU CICACCU = new CICACCU();
    DDCIMMST DDCIMMST = new DDCIMMST();
    CICSGRS CICSGRS = new CICSGRS();
    CICCUST CICCUST = new CICCUST();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    BPCPQORG BPCPQORG = new BPCPQORG();
    DDCICCYT DDCICCYT = new DDCICCYT();
    DCCUCINF DCCUCINF = new DCCUCINF();
    BPCFESIR BPCFESIR = new BPCFESIR();
    DCCUATMS DCCUATMS = new DCCUATMS();
    SCCGWA SCCGWA;
    BPCTCFAV BPCTCFAV;
    public void MP(SCCGWA SCCGWA, BPCTCFAV BPCTCFAV) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCTCFAV = BPCTCFAV;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFCFAV return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPVFFAV);
        IBS.init(SCCGWA, BPCFPARM);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GET_FFAV_INFO();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B030_CAL_FAV_CN();
            if (pgmRtn) return;
            B090_OUTPUT_INFO_CN();
            if (pgmRtn) return;
        } else {
            B090_OUTPUT_INFO();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCTCFAV.INPUT_AREA.FAV_CODE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B015_GET_TOT_FEE_INFO() throws IOException,SQLException,Exception {
        if (BPVFFAV.VAL.CAL_SOURCE == '0' 
            || BPVFFAV.VAL.CAL_SOURCE == '2') {
            IBS.init(SCCGWA, BPCFFTOT);
            BPCFFTOT.DATA.SUR_CYC = BPVFFAV.VAL.CAL_CYC;
            BPCFFTOT.DATA.CYC_CNT = BPVFFAV.VAL.CYC_NUM;
            BPCFFTOT.DATA.CLASSFY_TYPE = BPVFFAV.VAL.FAV_SPLT_FLG;
            BPCFFTOT.DATA.AMT_SUR = BPVFFAV.VAL.FAV_SPLT_FLG;
            BPCFFTOT.DATA.MST_SUR = BPVFFAV.VAL.COLL_TYPE;
            BPCFFTOT.DATA.TX_CODE = "" + SCCGWA.COMM_AREA.TR_ID.TR_CODE;
            JIBS_tmp_int = BPCFFTOT.DATA.TX_CODE.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) BPCFFTOT.DATA.TX_CODE = "0" + BPCFFTOT.DATA.TX_CODE;
            S000_CALL_BPZFFTOT();
            if (pgmRtn) return;
            WS_CAL_INFO.WS_CONSULT_AMT = BPCFFTOT.DATA.TOT_AMT;
            WS_CAL_INFO.WS_CONSULT_CNT = BPCFFTOT.DATA.TOT_CNT;
        }
    }
    public void S000_CALL_DDCIMMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_I_AC_STS_PROC, DDCIMMST);
    }
    public void B020_GET_FFAV_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPVFFAV);
        BPCFPARM.INFO.TYPE = "FFAV ";
        BPVFFAV.KEY.PRFR_CODE = BPCTCFAV.INPUT_AREA.FAV_CODE;
        CEP.TRC(SCCGWA, BPVFFAV.KEY.PRFR_CODE);
        BPCFPARM.INFO.POINTER = BPVFFAV;
        BPCFPARM.INFO.FUNC = '3';
        S000_CALL_BPZFPARM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CHECK-FFAV");
        CEP.TRC(SCCGWA, BPVFFAV.VAL.FAV_DATA[1-1].FAV_AMT);
        CEP.TRC(SCCGWA, BPVFFAV.VAL.MAX_FAV_AMT);
        CEP.TRC(SCCGWA, BPVFFAV.VAL.FAV_DATA[1-1].STR_CNT);
        CEP.TRC(SCCGWA, BPVFFAV.VAL.FAV_DATA[1-1].CI_LEVEL);
        CEP.TRC(SCCGWA, BPVFFAV.VAL.FAV_DATA[1-1].AC_LEVEL);
        CEP.TRC(SCCGWA, BPVFFAV.VAL.FAV_DATA[1-1].STATUS);
        CEP.TRC(SCCGWA, BPVFFAV.VAL.FAV_DATA[1-1].GROUP);
        CEP.TRC(SCCGWA, BPVFFAV.VAL.FAV_DATA[1-1].SEGMENT);
        CEP.TRC(SCCGWA, BPVFFAV.VAL.FAV_DATA[1-1].PROD_CODE);
        CEP.TRC(SCCGWA, BPVFFAV.VAL.FAV_DATA[1-1].BR);
        CEP.TRC(SCCGWA, BPVFFAV.VAL.CAL_SOURCE);
        if (BPCFPARM.RETURN_INFO == 'N') {
            BPCTCFAV.OUTPUT_AREA.FAV_PCT = 0;
            BPCTCFAV.OUTPUT_AREA.FAV_AMT = 0;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_CAL_FAV_CN() throws IOException,SQLException,Exception {
        B030_01_CHECK_FAV_CODE_DATE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPVFFAV.VAL.FAV_TYPE);
        if (BPVFFAV.VAL.FAV_TYPE.equalsIgnoreCase("0")) {
            BPCTCFAV.OUTPUT_AREA.FAV_AMT = BPVFFAV.VAL.MAX_FAV_AMT;
            BPCTCFAV.OUTPUT_AREA.FAV_PCT = BPVFFAV.VAL.MAX_FAV_PCT;
        } else if (BPVFFAV.VAL.FAV_TYPE.equalsIgnoreCase("11")) {
            WS_CAL_INFO.WS_CONSULT_AMT = BPCTCFAV.INPUT_AREA.TX_AMT;
            B030_05_CAL_FAV_BY_AMT();
            if (pgmRtn) return;
        } else if (BPVFFAV.VAL.FAV_TYPE.equalsIgnoreCase("12")) {
            B030_10_GET_CI_INFO_CN();
            if (pgmRtn) return;
            B030_05_CAL_FAV_BY_AMT();
            if (pgmRtn) return;
        } else if (BPVFFAV.VAL.FAV_TYPE.equalsIgnoreCase("13")) {
            B030_10_GET_CI_INFO_CN();
            if (pgmRtn) return;
            B030_05_CAL_FAV_BY_AMT();
            if (pgmRtn) return;
        } else if (BPVFFAV.VAL.FAV_TYPE.equalsIgnoreCase("14")) {
            B030_20_GET_AC_INFO_CN();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPVFFAV.VAL.CAL_CYC);
            if (BPVFFAV.VAL.CAL_CYC == ' ' 
                || BPVFFAV.VAL.CAL_CYC == '0') {
                B030_20_GET_AC_BAL_CN();
                if (pgmRtn) return;
            } else {
                B030_21_GET_AC_BAL_CN();
                if (pgmRtn) return;
            }
            B030_05_CAL_FAV_BY_AMT();
            if (pgmRtn) return;
        } else if (BPVFFAV.VAL.FAV_TYPE.equalsIgnoreCase("15")) {
            B030_20_GET_AC_INFO_CN();
            if (pgmRtn) return;
            B030_25_GET_AC_AVEBAL_CN();
            if (pgmRtn) return;
            B030_05_CAL_FAV_BY_AMT();
            if (pgmRtn) return;
        } else if (BPVFFAV.VAL.FAV_TYPE.equalsIgnoreCase("21")) {
            WS_CAL_INFO.WS_CONSULT_CNT = BPCTCFAV.INPUT_AREA.TX_CNT;
            B030_30_CAL_FAV_BY_CNT();
            if (pgmRtn) return;
        } else if (BPVFFAV.VAL.FAV_TYPE.equalsIgnoreCase("31")) {
            WS_CAL_INFO.WS_CONSULT_TIME = SCCGWA.COMM_AREA.TR_TIME;
            B030_40_CAL_FAV_BY_TX_TIME();
            if (pgmRtn) return;
        } else if (BPVFFAV.VAL.FAV_TYPE.equalsIgnoreCase("32")) {
            WS_CAL_INFO.WS_CONSULT_PRD_CODE = BPCTCFAV.INPUT_AREA.PRD_CODE;
            B030_50_CAL_FAV_BY_PRD_CODE();
            if (pgmRtn) return;
        } else if (BPVFFAV.VAL.FAV_TYPE.equalsIgnoreCase("41")) {
            B030_10_GET_CI_INFO_CN();
            if (pgmRtn) return;
            B030_60_CAL_FAV_BY_CI_LEVEL();
            if (pgmRtn) return;
        } else if (BPVFFAV.VAL.FAV_TYPE.equalsIgnoreCase("42")) {
            IBS.init(SCCGWA, DDCIMMST);
            DDCIMMST.DATA.KEY.AC_NO = BPCTCFAV.INPUT_AREA.TX_AC;
            DDCIMMST.TX_TYPE = 'I';
            CEP.TRC(SCCGWA, BPCTCFAV.INPUT_AREA.TX_AC);
            S000_CALL_DDCIMMST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDCIMMST.DATA.AC_STS_WORD);
            CEP.TRC(SCCGWA, "EXEC HERE");
            WS_CAL_INFO.WS_CONSULT_STSW = DDCIMMST.DATA.AC_STS_WORD;
            B030_70_CAL_FAV_BY_STATUS();
            if (pgmRtn) return;
        } else if (BPVFFAV.VAL.FAV_TYPE.equalsIgnoreCase("51")) {
            B030_10_GET_CI_INFO_CN();
            if (pgmRtn) return;
            B030_11_GET_CI_GROUP();
            if (pgmRtn) return;
            B030_80_CAL_FAV_BY_CI_GROUP();
            if (pgmRtn) return;
        } else if (BPVFFAV.VAL.FAV_TYPE.equalsIgnoreCase("52")) {
            B030_10_GET_CI_INFO_CN();
            if (pgmRtn) return;
            B030_11_GET_CI_GROUP();
            if (pgmRtn) return;
            B030_80_CAL_FAV_BY_CI_GROUP();
            if (pgmRtn) return;
        } else if (BPVFFAV.VAL.FAV_TYPE.equalsIgnoreCase("53")) {
            B030_10_GET_CI_INFO_CN();
            if (pgmRtn) return;
            B030_70_CAL_FAV_BY_STATUS();
            if (pgmRtn) return;
        } else if (BPVFFAV.VAL.FAV_TYPE.equalsIgnoreCase("60")) {
            if (BPVFFAV.VAL.DIF_VAL == null) BPVFFAV.VAL.DIF_VAL = "";
            JIBS_tmp_int = BPVFFAV.VAL.DIF_VAL.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) BPVFFAV.VAL.DIF_VAL += " ";
            if (BPVFFAV.VAL.DIF_VAL.substring(0, 1).equalsIgnoreCase(BPCTCFAV.INPUT_AREA.URG_RMT_FLG+"")) {
                BPCTCFAV.OUTPUT_AREA.FAV_AMT = BPVFFAV.VAL.MAX_FAV_AMT;
                BPCTCFAV.OUTPUT_AREA.FAV_PCT = BPVFFAV.VAL.MAX_FAV_PCT;
            }
        } else if (BPVFFAV.VAL.FAV_TYPE.equalsIgnoreCase("71")) {
            CEP.TRC(SCCGWA, BPCTCFAV.INPUT_AREA.TX_AC);
            if (BPCTCFAV.INPUT_AREA.TX_AC.trim().length() > 0) {
                B030_10_GET_AC_STS();
                if (pgmRtn) return;
                B030_71_CAL_FAV_BY_AC_STS();
                if (pgmRtn) return;
            }
        } else if (BPVFFAV.VAL.FAV_TYPE.equalsIgnoreCase("80")) {
            CEP.TRC(SCCGWA, BPCTCFAV.INPUT_AREA.BR);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
            WS_CAL_INFO.WS_CONSULT_BR = BPCTCFAV.INPUT_AREA.BR;
            B030_80_CAL_FAV_BY_BR();
            if (pgmRtn) return;
            if (WS_GET_AGGRAGATE == 'N' 
                && BPCTCFAV.INPUT_AREA.BR != 0) {
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BR = BPCTCFAV.INPUT_AREA.BR;
                IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
                CEP.TRC(SCCGWA, BPCPQORG.BRANCH_BR);
                CEP.TRC(SCCGWA, "HZ-FAV-80");
                if (BPCPQORG.BRANCH_BR != 0) {
                    WS_CAL_INFO.WS_CONSULT_BR = BPCPQORG.BRANCH_BR;
                    B030_80_CAL_FAV_BY_BR();
                    if (pgmRtn) return;
                }
            }
            if (WS_GET_AGGRAGATE == 'N') {
                WS_CAL_INFO.WS_CONSULT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                B030_80_CAL_FAV_BY_BR();
                if (pgmRtn) return;
                if (WS_GET_AGGRAGATE == 'N' 
                    && SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != BPCTCFAV.INPUT_AREA.BR) {
                    IBS.init(SCCGWA, BPCPQORG);
                    BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                    IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
                    CEP.TRC(SCCGWA, BPCPQORG.BRANCH_BR);
                    CEP.TRC(SCCGWA, "HZ-FAV-80-2");
                    if (BPCPQORG.BRANCH_BR != BPCTCFAV.INPUT_AREA.BR) {
                        WS_CAL_INFO.WS_CONSULT_BR = BPCPQORG.BRANCH_BR;
                        B030_80_CAL_FAV_BY_BR();
                        if (pgmRtn) return;
                    }
                }
            }
        } else {
            BPCTCFAV.OUTPUT_AREA.FAV_AMT = 0;
            BPCTCFAV.OUTPUT_AREA.FAV_PCT = 0;
        }
        CEP.TRC(SCCGWA, BPCTCFAV.OUTPUT_AREA.FAV_AMT);
        CEP.TRC(SCCGWA, BPVFFAV.VAL.MAX_FAV_AMT);
        CEP.TRC(SCCGWA, BPCTCFAV.OUTPUT_AREA.FAV_PCT);
        CEP.TRC(SCCGWA, BPVFFAV.VAL.MAX_FAV_PCT);
        if (BPCTCFAV.OUTPUT_AREA.FAV_AMT > BPVFFAV.VAL.MAX_FAV_AMT 
            && BPVFFAV.VAL.MAX_FAV_AMT > 0) {
            BPCTCFAV.OUTPUT_AREA.FAV_AMT = BPVFFAV.VAL.MAX_FAV_AMT;
        }
        if (BPCTCFAV.OUTPUT_AREA.FAV_PCT > BPVFFAV.VAL.MAX_FAV_PCT 
            && BPVFFAV.VAL.MAX_FAV_PCT > 0) {
            BPCTCFAV.OUTPUT_AREA.FAV_PCT = BPVFFAV.VAL.MAX_FAV_PCT;
        }
    }
    public void B030_01_CHECK_FAV_CODE_DATE() throws IOException,SQLException,Exception {
        if (BPVFFAV.DATE.EFF_DATE > SCCGWA.COMM_AREA.AC_DATE 
            || BPVFFAV.DATE.EXP_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            BPCTCFAV.OUTPUT_AREA.FAV_PCT = 0;
            BPCTCFAV.OUTPUT_AREA.FAV_AMT = 0;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_05_CAL_FAV_BY_AMT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CONSULT_AMT);
        WS_GET_AGGRAGATE = 'N';
        WS_CAL_INFO.WS_LOC = 0;
        for (WS_CAL_INFO.WS_CNT = 1; WS_CAL_INFO.WS_CNT <= 10 
            && WS_GET_AGGRAGATE != 'Y'; WS_CAL_INFO.WS_CNT += 1) {
            CEP.TRC(SCCGWA, BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].STR_AMT);
            CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CONSULT_AMT);
            CEP.TRC(SCCGWA, "HANZHEN3");
            if ((BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].STR_AMT < WS_CAL_INFO.WS_CONSULT_AMT) 
                && BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].STR_AMT > 0 
                && WS_CAL_INFO.WS_CONSULT_AMT > 0) {
                CEP.TRC(SCCGWA, "HANZHEN1");
                CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CNT);
                CEP.TRC(SCCGWA, BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].STR_AMT);
                CEP.TRC(SCCGWA, BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].FAV_AMT);
                BPCTCFAV.OUTPUT_AREA.FAV_AMT = BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT + 1-1].FAV_AMT;
                BPCTCFAV.OUTPUT_AREA.FAV_PCT = BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT + 1-1].FAV_PCT;
            } else {
                WS_GET_AGGRAGATE = 'Y';
                if (WS_CAL_INFO.WS_CONSULT_AMT == 0) {
                    BPCTCFAV.OUTPUT_AREA.FAV_AMT = 0;
                    BPCTCFAV.OUTPUT_AREA.FAV_PCT = 0;
                } else {
                    BPCTCFAV.OUTPUT_AREA.FAV_AMT = BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].FAV_AMT;
                    BPCTCFAV.OUTPUT_AREA.FAV_PCT = BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].FAV_PCT;
                }
            }
        }
        CEP.TRC(SCCGWA, "FAV-INFO");
        CEP.TRC(SCCGWA, BPCTCFAV.OUTPUT_AREA.FAV_AMT);
        CEP.TRC(SCCGWA, BPCTCFAV.OUTPUT_AREA.FAV_PCT);
    }
    public void B030_10_GET_CI_INFO_CN() throws IOException,SQLException,Exception {
        WS_CI_FLG = 'N';
        if (BPCTCFAV.INPUT_AREA.TX_CI.trim().length() == 0 
            && BPCTCFAV.INPUT_AREA.TX_AC.trim().length() > 0) {
            B030_20_GET_AC_INFO_CN();
            if (pgmRtn) return;
            BPCTCFAV.INPUT_AREA.TX_CI = CICACCU.DATA.CI_NO;
        }
        if (BPCTCFAV.INPUT_AREA.TX_CI.trim().length() > 0) {
            IBS.init(SCCGWA, CICCUST);
            CICCUST.DATA.CI_NO = BPCTCFAV.INPUT_AREA.TX_CI;
            CICCUST.FUNC = 'C';
            S000_CALL_CIZCUST_CN();
            if (pgmRtn) return;
            WS_CAL_INFO.WS_CONSULT_CI_LEVEL = CICCUST.O_DATA.O_SVR_LVL;
        }
    }
    public void B030_20_GET_AC_INFO_CN() throws IOException,SQLException,Exception {
        WS_AC_FLG = 'N';
        if (BPCTCFAV.INPUT_AREA.TX_AC.trim().length() > 0) {
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = BPCTCFAV.INPUT_AREA.TX_AC;
            S000_CALL_CIZACCU_CN();
            if (pgmRtn) return;
        }
        WS_CAL_INFO.WS_CONSULT_STSW = "" + CICACCU.DATA.STS;
        JIBS_tmp_int = WS_CAL_INFO.WS_CONSULT_STSW.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) WS_CAL_INFO.WS_CONSULT_STSW = "0" + WS_CAL_INFO.WS_CONSULT_STSW;
    }
    public void B030_20_GET_AC_BAL_CN() throws IOException,SQLException,Exception {
        if (BPCTCFAV.INPUT_AREA.FAV_CCY.equalsIgnoreCase("156") 
            && BPCTCFAV.INPUT_AREA.TX_AC.trim().length() > 0) {
            IBS.init(SCCGWA, DDCIQBAL);
            CEP.TRC(SCCGWA, BPCTCFAV.INPUT_AREA.TX_AC);
            CEP.TRC(SCCGWA, BPCTCFAV.INPUT_AREA.FAV_CCY);
            DDCIQBAL.DATA.AC = BPCTCFAV.INPUT_AREA.TX_AC;
            DDCIQBAL.DATA.CCY = BPCTCFAV.INPUT_AREA.FAV_CCY;
            DDCIQBAL.DATA.CCY_TYPE = '1';
            CEP.TRC(SCCGWA, DDCIQBAL.DATA.CCY_TYPE);
            S000_CALL_DDZIQBAL();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDCIQBAL.DATA.CURR_BAL);
            WS_CAL_INFO.WS_CONSULT_AMT = DDCIQBAL.DATA.CURR_BAL;
        } else {
            WS_CAL_INFO.WS_CONSULT_AMT = 0;
        }
    }
    public void B030_21_GET_AC_BAL_CN() throws IOException,SQLException,Exception {
        if (BPCTCFAV.INPUT_AREA.FAV_CCY.equalsIgnoreCase("156") 
            && BPCTCFAV.INPUT_AREA.TX_AC.trim().length() > 0) {
            IBS.init(SCCGWA, DDCICCYT);
            CEP.TRC(SCCGWA, BPCTCFAV.INPUT_AREA.TX_AC);
            CEP.TRC(SCCGWA, BPCTCFAV.INPUT_AREA.FAV_CCY);
            DDCICCYT.DATA.AC = BPCTCFAV.INPUT_AREA.TX_AC;
            DDCICCYT.DATA.CCY = BPCTCFAV.INPUT_AREA.FAV_CCY;
            DDCICCYT.DATA.CCY_TYPE = '1';
            CEP.TRC(SCCGWA, DDCICCYT.DATA.CCY_TYPE);
            S000_CALL_DDZICCYT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDCICCYT.DATA.LAST_MON_AVE_BAL);
            CEP.TRC(SCCGWA, DDCICCYT.DATA.LAST_3MON_AVE_BAL);
            CEP.TRC(SCCGWA, DDCICCYT.DATA.LAST_6MON_AVE_BAL);
            CEP.TRC(SCCGWA, DDCICCYT.DATA.LAST_12MON_AVE_BAL);
            CEP.TRC(SCCGWA, DDCICCYT.DATA.ADP_AVE_BAL);
            CEP.TRC(SCCGWA, DDCICCYT.DATA.LAST_3MON_BAL);
            CEP.TRC(SCCGWA, BPVFFAV.VAL.CAL_CYC);
            if (BPVFFAV.VAL.CAL_CYC == '1') {
                WS_CAL_INFO.WS_CONSULT_AMT = DDCICCYT.DATA.LAST_MON_AVE_BAL;
            } else if (BPVFFAV.VAL.CAL_CYC == '2') {
                WS_CAL_INFO.WS_CONSULT_AMT = DDCICCYT.DATA.LAST_3MON_AVE_BAL;
            } else if (BPVFFAV.VAL.CAL_CYC == '3') {
                WS_CAL_INFO.WS_CONSULT_AMT = DDCICCYT.DATA.LAST_6MON_AVE_BAL;
            } else if (BPVFFAV.VAL.CAL_CYC == '4') {
                WS_CAL_INFO.WS_CONSULT_AMT = DDCICCYT.DATA.LAST_3MON_BAL;
            } else if (BPVFFAV.VAL.CAL_CYC == '5') {
                WS_CAL_INFO.WS_CONSULT_AMT = DDCICCYT.DATA.LAST_YEAR_BAL;
            } else {
                WS_CAL_INFO.WS_CONSULT_AMT = 0;
            }
    }
    public void S000_GET_ATM_NUM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUATMS);
        DCCUATMS.FUNC = 'Q';
        DCCUATMS.CARD_NO = WS_ATM_AC;
        CEP.TRC(SCCGWA, BPCTCFAV.INPUT_AREA.FEE_CODE);
        CEP.TRC(SCCGWA, "FFAV ATM");
        CEP.TRC(SCCGWA, BPCTCFAV.INPUT_AREA.FEE_CODE);
        if (BPCTCFAV.INPUT_AREA.FEE_CODE.equalsIgnoreCase("504000006")) {
            DCCUATMS.TC_FLG = "01";
            DCCUATMS.TXN_TYPE = "02";
            DCCUATMS.REGN_TYPE = "01";
        } else if (BPCTCFAV.INPUT_AREA.FEE_CODE.equalsIgnoreCase("504000009")) {
            DCCUATMS.TC_FLG = "01";
            DCCUATMS.TXN_TYPE = "01";
            DCCUATMS.REGN_TYPE = "02";
        } else if (BPCTCFAV.INPUT_AREA.FEE_CODE.equalsIgnoreCase("504000010")) {
            DCCUATMS.TC_FLG = "01";
            DCCUATMS.TXN_TYPE = "02";
            DCCUATMS.REGN_TYPE = "02";
        } else if (BPCTCFAV.INPUT_AREA.FEE_CODE.equalsIgnoreCase("504000011")) {
            DCCUATMS.TC_FLG = "02";
            DCCUATMS.TXN_TYPE = "01";
            DCCUATMS.REGN_TYPE = "02";
        } else if (BPCTCFAV.INPUT_AREA.FEE_CODE.equalsIgnoreCase("504000012")) {
            DCCUATMS.TC_FLG = "02";
            DCCUATMS.TXN_TYPE = "02";
            DCCUATMS.REGN_TYPE = "02";
        } else if (BPCTCFAV.INPUT_AREA.FEE_CODE.equalsIgnoreCase("504000014")) {
            DCCUATMS.TC_FLG = "01";
            DCCUATMS.TXN_TYPE = "03";
            DCCUATMS.REGN_TYPE = "02";
        } else {
        }
        CEP.TRC(SCCGWA, DCCUATMS.TC_FLG);
        CEP.TRC(SCCGWA, DCCUATMS.TXN_TYPE);
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        DCCUATMS.MONTH = JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1);
        if (DCCUATMS.TC_FLG.trim().length() > 0) {
            IBS.CALLCPN(SCCGWA, "DC-U-ATMS-INF", DCCUATMS);
        }
        CEP.TRC(SCCGWA, DCCUATMS.OUTPUT.O_CNT);
        WS_ATM_NUM = DCCUATMS.OUTPUT.O_CNT;
    }
    public void B030_10_GET_AC_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUCINF);
        DCCUCINF.CARD_NO = BPCTCFAV.INPUT_AREA.TX_AC;
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF, true);
        CEP.TRC(SCCGWA, DCCUCINF.RC);
        CEP.TRC(SCCGWA, DCCUCINF.CARD_TYP);
        if (DCCUCINF.RC.RC_CODE == 0) {
            WS_CARD_TYP = DCCUCINF.CARD_TYP;
        }
        WS_SIR_CARD_FLG = 'N';
        IBS.init(SCCGWA, BPCFESIR);
        BPCFESIR.INP_DATA.AC_NO = BPCTCFAV.INPUT_AREA.TX_AC;
        IBS.CALLCPN(SCCGWA, "BP-S-FESIR", BPCFESIR);
        CEP.TRC(SCCGWA, BPCFESIR.RET_INFO);
        if (BPCFESIR.RET_INFO == 'Y') {
            WS_SIR_CARD_FLG = 'Y';
        }
    }
    public void S000_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-CCY-BAL", DDCIQBAL);
    }
    public void B030_25_GET_AC_AVEBAL_CN() throws IOException,SQLException,Exception {
        if (BPCTCFAV.INPUT_AREA.FAV_CCY.equalsIgnoreCase("156")) {
            IBS.init(SCCGWA, DDCICCYT);
            CEP.TRC(SCCGWA, BPCTCFAV.INPUT_AREA.TX_AC);
            CEP.TRC(SCCGWA, BPCTCFAV.INPUT_AREA.FAV_CCY);
            DDCICCYT.DATA.AC = BPCTCFAV.INPUT_AREA.TX_AC;
            DDCICCYT.DATA.CCY = BPCTCFAV.INPUT_AREA.FAV_CCY;
            DDCICCYT.DATA.CCY_TYPE = '1';
            CEP.TRC(SCCGWA, DDCICCYT.DATA.CCY_TYPE);
            S000_CALL_DDZICCYT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDCICCYT.DATA.LAST_MON_AVE_BAL);
            WS_CAL_INFO.WS_CONSULT_AMT = DDCICCYT.DATA.LAST_MON_AVE_BAL;
        } else {
            WS_CAL_INFO.WS_CONSULT_AMT = 0;
        }
    }
    public void B030_11_GET_CI_GROUP() throws IOException,SQLException,Exception {
        if (BPCTCFAV.INPUT_AREA.TX_CI.trim().length() > 0) {
            IBS.init(SCCGWA, CICSGRS);
            CICSGRS.DATA.CI_NO = BPCTCFAV.INPUT_AREA.TX_CI;
            S000_CALL_CIZSGRS();
            if (pgmRtn) return;
        }
    }
    public void B030_30_CAL_FAV_BY_CNT() throws IOException,SQLException,Exception {
        WS_CAL_INFO.WS_SAV_FFAV_FAV_AMT = 0;
        WS_CAL_INFO.WS_SAV_FFAV_FAV_PCT = 0;
        WS_GET_AGGRAGATE = 'N';
        for (WS_CAL_INFO.WS_CNT = 1; WS_CAL_INFO.WS_CNT <= 10 
            && WS_GET_AGGRAGATE != 'Y'; WS_CAL_INFO.WS_CNT += 1) {
            if (BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].STR_CNT <= WS_CAL_INFO.WS_CONSULT_CNT 
                && WS_CAL_INFO.WS_CONSULT_CNT > 0 
                && BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].STR_CNT > 0) {
                WS_CAL_INFO.WS_SAV_FFAV_FAV_AMT = BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].FAV_AMT;
                WS_CAL_INFO.WS_SAV_FFAV_FAV_PCT = BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].FAV_PCT;
            } else {
                WS_GET_AGGRAGATE = 'Y';
                BPCTCFAV.OUTPUT_AREA.FAV_AMT = 0;
                BPCTCFAV.OUTPUT_AREA.FAV_PCT = 0;
            }
        }
        if (WS_CAL_INFO.WS_SAV_FFAV_FAV_AMT != 0 
            || WS_CAL_INFO.WS_SAV_FFAV_FAV_PCT != 0) {
            BPCTCFAV.OUTPUT_AREA.FAV_AMT = WS_CAL_INFO.WS_SAV_FFAV_FAV_AMT;
            BPCTCFAV.OUTPUT_AREA.FAV_PCT = WS_CAL_INFO.WS_SAV_FFAV_FAV_PCT;
        }
    }
    public void B030_40_CAL_FAV_BY_TX_TIME() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_TIME);
        CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CONSULT_TIME);
        WS_GET_AGGRAGATE = 'N';
        for (WS_CAL_INFO.WS_CNT = 1; WS_CAL_INFO.WS_CNT <= 10 
            && WS_GET_AGGRAGATE != 'Y'; WS_CAL_INFO.WS_CNT += 1) {
            CEP.TRC(SCCGWA, BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].FAV_AMT);
            CEP.TRC(SCCGWA, BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].FAV_PCT);
            CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CNT);
            CEP.TRC(SCCGWA, BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].STR_TIME);
            CEP.TRC(SCCGWA, BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].END_TIME);
            if (BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].STR_TIME <= WS_CAL_INFO.WS_CONSULT_TIME 
                && WS_CAL_INFO.WS_CONSULT_TIME < BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].END_TIME) {
                CEP.TRC(SCCGWA, "FOUND");
                WS_GET_AGGRAGATE = 'Y';
                BPCTCFAV.OUTPUT_AREA.FAV_AMT = BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].FAV_AMT;
                BPCTCFAV.OUTPUT_AREA.FAV_PCT = BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].FAV_PCT;
            } else {
                BPCTCFAV.OUTPUT_AREA.FAV_AMT = 0;
                BPCTCFAV.OUTPUT_AREA.FAV_PCT = 0;
            }
        }
    }
    public void B030_50_CAL_FAV_BY_PRD_CODE() throws IOException,SQLException,Exception {
        WS_GET_AGGRAGATE = 'N';
        for (WS_CAL_INFO.WS_CNT = 1; WS_CAL_INFO.WS_CNT <= 10 
            && WS_GET_AGGRAGATE != 'Y'; WS_CAL_INFO.WS_CNT += 1) {
            if (BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].PROD_CODE.equalsIgnoreCase(WS_CAL_INFO.WS_CONSULT_PRD_CODE)) {
                WS_GET_AGGRAGATE = 'Y';
                BPCTCFAV.OUTPUT_AREA.FAV_AMT = BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].FAV_AMT;
                BPCTCFAV.OUTPUT_AREA.FAV_PCT = BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].FAV_PCT;
            } else {
                BPCTCFAV.OUTPUT_AREA.FAV_AMT = 0;
                BPCTCFAV.OUTPUT_AREA.FAV_PCT = 0;
            }
        }
    }
    public void B030_60_CAL_FAV_BY_CI_LEVEL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CONSULT_CI_LEVEL);
        WS_GET_AGGRAGATE = 'N';
        for (WS_CAL_INFO.WS_CNT = 1; WS_CAL_INFO.WS_CNT <= 10 
            && WS_GET_AGGRAGATE != 'Y'; WS_CAL_INFO.WS_CNT += 1) {
            CEP.TRC(SCCGWA, BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].CI_LEVEL);
            if (BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].CI_LEVEL.equalsIgnoreCase(WS_CAL_INFO.WS_CONSULT_CI_LEVEL)) {
                CEP.TRC(SCCGWA, "FOUND");
                WS_GET_AGGRAGATE = 'Y';
                BPCTCFAV.OUTPUT_AREA.FAV_AMT = BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].FAV_AMT;
                BPCTCFAV.OUTPUT_AREA.FAV_PCT = BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].FAV_PCT;
                WS_H_CNT = WS_CAL_INFO.WS_CNT;
            } else {
                BPCTCFAV.OUTPUT_AREA.FAV_AMT = 0;
                BPCTCFAV.OUTPUT_AREA.FAV_PCT = 0;
            }
        }
        S000_DEAL_ATM_NUM();
        if (pgmRtn) return;
    }
    public void B030_70_CAL_FAV_BY_STATUS() throws IOException,SQLException,Exception {
        WS_GET_AGGRAGATE = 'N';
        for (WS_CAL_INFO.WS_CNT = 1; WS_CAL_INFO.WS_CNT <= 10 
            && WS_GET_AGGRAGATE != 'Y'; WS_CAL_INFO.WS_CNT += 1) {
            CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CNT);
            CEP.TRC(SCCGWA, BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].STATUS);
            if (!BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].STATUS.equalsIgnoreCase("0") 
                && BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].STATUS.trim().length() > 0) {
                if (BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].STATUS.trim().length() == 0) WS_CAL_INFO.WS_LOC = 0;
                else WS_CAL_INFO.WS_LOC = Short.parseShort(BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].STATUS);
                CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CONSULT_STS);
                if (WS_CAL_INFO.WS_CONSULT_STS == '1') {
                    CEP.TRC(SCCGWA, "FIND-FAV-CODE");
                    WS_GET_AGGRAGATE = 'Y';
                    WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_OUT_AMT = BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].FAV_AMT;
                    WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_OUT_PCT = BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].FAV_PCT;
                }
            }
        }
        R000_GET_FAV_AMT();
        if (pgmRtn) return;
    }
    public void B030_80_CAL_FAV_BY_CI_GROUP() throws IOException,SQLException,Exception {
        BPCTCFAV.OUTPUT_AREA.FAV_AMT = 0;
        BPCTCFAV.OUTPUT_AREA.FAV_PCT = 0;
        for (WS_CAL_INFO.WS_CNT = 1; WS_CAL_INFO.WS_CNT <= 10 
            && BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].GROUP.trim().length() != 0; WS_CAL_INFO.WS_CNT += 1) {
            CEP.TRC(SCCGWA, CICSGRS.DATA.MULTI_DATA[WS_CAL_INFO.WS_CNT-1].GRS_NO);
            CEP.TRC(SCCGWA, CICSGRS.OUT_DATA[WS_CAL_INFO.WS_CNT-1].GRPS_NO);
            CEP.TRC(SCCGWA, BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].GROUP);
            for (WS_CAL_INFO.WS_I = 1; WS_CAL_INFO.WS_I <= 5 
                && CICSGRS.OUT_DATA[WS_CAL_INFO.WS_I-1].GRPS_NO.trim().length() != 0; WS_CAL_INFO.WS_I += 1) {
                if (BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].GROUP.equalsIgnoreCase(CICSGRS.OUT_DATA[WS_CAL_INFO.WS_I-1].GRPS_NO)) {
                    if (BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].FAV_AMT > BPCTCFAV.OUTPUT_AREA.FAV_AMT) {
                        BPCTCFAV.OUTPUT_AREA.FAV_AMT = BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].FAV_AMT;
                    }
                    if (BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].FAV_PCT > BPCTCFAV.OUTPUT_AREA.FAV_PCT) {
                        BPCTCFAV.OUTPUT_AREA.FAV_PCT = BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].FAV_PCT;
                    }
                }
            }
        }
    }
    public void B030_71_CAL_FAV_BY_AC_STS() throws IOException,SQLException,Exception {
        WS_GET_AGGRAGATE = 'N';
        CEP.TRC(SCCGWA, BPCTCFAV.INPUT_AREA.TX_AMT);
        CEP.TRC(SCCGWA, BPVFFAV.VAL.FAV_DATA[1-1].STATUS);
        CEP.TRC(SCCGWA, BPVFFAV.VAL.FAV_DATA[2-1].STATUS);
        CEP.TRC(SCCGWA, BPVFFAV.VAL.FAV_DATA[3-1].STATUS);
        CEP.TRC(SCCGWA, BPVFFAV.VAL.FAV_DATA[4-1].STATUS);
        CEP.TRC(SCCGWA, BPVFFAV.VAL.FAV_DATA[5-1].STATUS);
        CEP.TRC(SCCGWA, DCCUCINF.CARD_TYP);
        for (WS_CAL_INFO.WS_CNT = 1; WS_CAL_INFO.WS_CNT <= 10 
            && WS_GET_AGGRAGATE != 'Y' 
            && BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].STATUS.trim().length() != 0; WS_CAL_INFO.WS_CNT += 1) {
            if (DCCUCINF.CARD_TYP == null) DCCUCINF.CARD_TYP = "";
            JIBS_tmp_int = DCCUCINF.CARD_TYP.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) DCCUCINF.CARD_TYP += " ";
            if (DCCUCINF.CARD_TYP == null) DCCUCINF.CARD_TYP = "";
            JIBS_tmp_int = DCCUCINF.CARD_TYP.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) DCCUCINF.CARD_TYP += " ";
            if (DCCUCINF.CARD_TYP == null) DCCUCINF.CARD_TYP = "";
            JIBS_tmp_int = DCCUCINF.CARD_TYP.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) DCCUCINF.CARD_TYP += " ";
            if (DCCUCINF.CARD_TYP == null) DCCUCINF.CARD_TYP = "";
            JIBS_tmp_int = DCCUCINF.CARD_TYP.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) DCCUCINF.CARD_TYP += " ";
            if ((BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].STATUS.equalsIgnoreCase("1") 
                && DCCUCINF.CARD_TYP.substring(0, 1).equalsIgnoreCase("1")) 
                || (BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].STATUS.equalsIgnoreCase("2") 
                && DCCUCINF.CARD_TYP.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) 
                || (BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].STATUS.equalsIgnoreCase("3") 
                && DCCUCINF.CARD_TYP.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) 
                || (BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].STATUS.equalsIgnoreCase("4") 
                && DCCUCINF.CARD_TYP.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) 
                || (BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].STATUS.equalsIgnoreCase("5") 
                && WS_SIR_CARD_FLG == 'Y')) {
                WS_FAV_AMT1 = BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].FAV_AMT;
                if (BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].FAV_PCT != 0) {
                    WS_FAV_AMT1 = BPCTCFAV.INPUT_AREA.TX_AMT * BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].FAV_PCT;
                }
                if (WS_FAV_AMT1 > WS_FAV_AMT2) {
                    WS_FAV_AMT2 = WS_FAV_AMT1;
                    WS_H_CNT = WS_CAL_INFO.WS_CNT;
                    CEP.TRC(SCCGWA, WS_FAV_AMT2);
                }
            }
        }
        CEP.TRC(SCCGWA, WS_H_CNT);
        CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CNT);
        if (WS_H_CNT != 0 
            && BPVFFAV.VAL.FAV_DATA[WS_H_CNT-1].STATUS.trim().length() > 0) {
            WS_GET_AGGRAGATE = 'Y';
            BPCTCFAV.OUTPUT_AREA.FAV_AMT = BPVFFAV.VAL.FAV_DATA[WS_H_CNT-1].FAV_AMT;
            BPCTCFAV.OUTPUT_AREA.FAV_PCT = BPVFFAV.VAL.FAV_DATA[WS_H_CNT-1].FAV_PCT;
            CEP.TRC(SCCGWA, "FAV-HZ-71");
            CEP.TRC(SCCGWA, DCCUCINF.CARD_TYP);
        } else {
            BPCTCFAV.OUTPUT_AREA.FAV_AMT = 0;
            BPCTCFAV.OUTPUT_AREA.FAV_PCT = 0;
        }
    }
    public void B030_80_CAL_FAV_BY_BR() throws IOException,SQLException,Exception {
        WS_GET_AGGRAGATE = 'N';
        for (WS_CAL_INFO.WS_CNT = 1; WS_CAL_INFO.WS_CNT <= 10 
            && WS_GET_AGGRAGATE != 'Y'; WS_CAL_INFO.WS_CNT += 1) {
            if (BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].BR == WS_CAL_INFO.WS_CONSULT_BR) {
                WS_GET_AGGRAGATE = 'Y';
                BPCTCFAV.OUTPUT_AREA.FAV_AMT = BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].FAV_AMT;
                BPCTCFAV.OUTPUT_AREA.FAV_PCT = BPVFFAV.VAL.FAV_DATA[WS_CAL_INFO.WS_CNT-1].FAV_PCT;
                WS_H_CNT = WS_CAL_INFO.WS_CNT;
            } else {
                BPCTCFAV.OUTPUT_AREA.FAV_AMT = 0;
                BPCTCFAV.OUTPUT_AREA.FAV_PCT = 0;
            }
        }
        S000_DEAL_ATM_NUM();
        if (pgmRtn) return;
    }
    public void S000_DEAL_ATM_NUM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCTCFAV.INPUT_AREA.TX_AC);
        CEP.TRC(SCCGWA, WS_H_CNT);
        if ((BPCTCFAV.INPUT_AREA.FEE_CODE.equalsIgnoreCase("504000006") 
            || BPCTCFAV.INPUT_AREA.FEE_CODE.equalsIgnoreCase("504000009") 
            || BPCTCFAV.INPUT_AREA.FEE_CODE.equalsIgnoreCase("504000010") 
            || BPCTCFAV.INPUT_AREA.FEE_CODE.equalsIgnoreCase("504000011") 
            || BPCTCFAV.INPUT_AREA.FEE_CODE.equalsIgnoreCase("504000012") 
            || BPCTCFAV.INPUT_AREA.FEE_CODE.equalsIgnoreCase("504000014")) 
            && WS_H_CNT != 0) {
            CEP.TRC(SCCGWA, BPVFFAV.VAL.FAV_DATA[WS_H_CNT-1].FAV_CNT);
            WS_ATM_AC = BPCTCFAV.INPUT_AREA.TX_AC;
            S000_GET_ATM_NUM();
            if (pgmRtn) return;
            if (WS_ATM_NUM > BPVFFAV.VAL.FAV_DATA[WS_H_CNT-1].FAV_CNT 
                && BPVFFAV.VAL.FAV_DATA[WS_H_CNT-1].FAV_CNT != 0) {
                WS_GET_AGGRAGATE = 'N';
                BPCTCFAV.OUTPUT_AREA.FAV_AMT = 0;
                BPCTCFAV.OUTPUT_AREA.FAV_PCT = 0;
            }
        }
    }
    public void B030_90_CAL_FAV_BY_SEG_CODE() throws IOException,SQLException,Exception {
    }
    public void B090_OUTPUT_INFO() throws IOException,SQLException,Exception {
    }
    public void B090_OUTPUT_INFO_CN() throws IOException,SQLException,Exception {
        BPCTCFAV.OUTPUT_AREA.ADJ_TYP = BPVFFAV.VAL.ADJ_TYP;
    }
    public void R000_GET_FAV_AMT() throws IOException,SQLException,Exception {
        for (WS_CAL_INFO.WS_CNT = 1; WS_CAL_INFO.WS_CNT <= 10; WS_CAL_INFO.WS_CNT += 1) {
            CEP.TRC(SCCGWA, "LIUZHIYONG");
            CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CNT);
            CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_OUT_AMT);
            CEP.TRC(SCCGWA, WS_CAL_INFO.WS_FAV_INFO.WS_MAX_AMT);
            CEP.TRC(SCCGWA, WS_CAL_INFO.WS_FAV_INFO.WS_MIN_AMT);
            if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_OUT_AMT > 0) {
                if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_OUT_AMT > WS_CAL_INFO.WS_FAV_INFO.WS_MAX_AMT) {
                    WS_CAL_INFO.WS_FAV_INFO.WS_MAX_AMT = WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_OUT_AMT;
                }
                if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_OUT_AMT < WS_CAL_INFO.WS_FAV_INFO.WS_MIN_AMT) {
                    WS_CAL_INFO.WS_FAV_INFO.WS_MIN_AMT = WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_OUT_AMT;
                }
                WS_CAL_INFO.WS_FAV_INFO.WS_MEG_AMT += WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_OUT_AMT;
            }
            if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_OUT_PCT > 0) {
                if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_OUT_PCT > WS_CAL_INFO.WS_FAV_INFO.WS_MAX_PCT) {
                    WS_CAL_INFO.WS_FAV_INFO.WS_MAX_PCT = WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_OUT_PCT;
                }
                if (WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_OUT_PCT < WS_CAL_INFO.WS_FAV_INFO.WS_MIN_PCT) {
                    WS_CAL_INFO.WS_FAV_INFO.WS_MIN_PCT = WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_OUT_PCT;
                }
                WS_CAL_INFO.WS_FAV_INFO.WS_MEG_PCT += WS_CAL_INFO.WS_CNT_INFO[WS_CAL_INFO.WS_CNT-1].WS_OUT_PCT;
            }
        }
        CEP.TRC(SCCGWA, BPVFFAV.VAL.FAV_COLL);
        CEP.TRC(SCCGWA, WS_CAL_INFO.WS_FAV_INFO.WS_MAX_AMT);
        CEP.TRC(SCCGWA, WS_CAL_INFO.WS_FAV_INFO.WS_MIN_AMT);
        CEP.TRC(SCCGWA, WS_CAL_INFO.WS_FAV_INFO.WS_MEG_AMT);
        if (BPVFFAV.VAL.FAV_COLL == 0) {
            BPCTCFAV.OUTPUT_AREA.FAV_AMT = WS_CAL_INFO.WS_FAV_INFO.WS_MAX_AMT;
            BPCTCFAV.OUTPUT_AREA.FAV_PCT = WS_CAL_INFO.WS_FAV_INFO.WS_MAX_PCT;
        } else if (BPVFFAV.VAL.FAV_COLL == 1) {
            BPCTCFAV.OUTPUT_AREA.FAV_AMT = WS_CAL_INFO.WS_FAV_INFO.WS_MIN_AMT;
            BPCTCFAV.OUTPUT_AREA.FAV_PCT = WS_CAL_INFO.WS_FAV_INFO.WS_MIN_PCT;
        } else if (BPVFFAV.VAL.FAV_COLL == 2) {
            BPCTCFAV.OUTPUT_AREA.FAV_AMT = WS_CAL_INFO.WS_FAV_INFO.WS_MEG_AMT;
            BPCTCFAV.OUTPUT_AREA.FAV_PCT = WS_CAL_INFO.WS_FAV_INFO.WS_MEG_PCT;
        } else {
            BPCTCFAV.OUTPUT_AREA.FAV_AMT = WS_CAL_INFO.WS_FAV_INFO.WS_MAX_AMT;
            BPCTCFAV.OUTPUT_AREA.FAV_PCT = WS_CAL_INFO.WS_FAV_INFO.WS_MAX_PCT;
        }
    }
    public void S000_CALL_BPZFFTOT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "ABC0000000000000000");
        IBS.CALLCPN(SCCGWA, CPN_GET_TOT_INFO, BPCFFTOT);
        CEP.TRC(SCCGWA, "ABC1111111111111111");
        if (BPCFFTOT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFTOT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFPARM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_FEE_PARM_MAIN, BPCFPARM);
    }
    public void S000_CALL_CIZACCU_CN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU         ", CICACCU);
    }
    public void S000_CALL_CIZCUST_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
    }
    public void S000_CALL_CIZSGRS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SEARCH-GRS       ", CICSGRS);
    }
    public void S000_CALL_DDZICCYT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-CCYT-PROC", DDCICCYT);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCTCFAV.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCTCFAV = ");
            CEP.TRC(SCCGWA, BPCTCFAV);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
