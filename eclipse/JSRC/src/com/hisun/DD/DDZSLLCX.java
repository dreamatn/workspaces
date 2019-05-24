package com.hisun.DD;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCCINTI;
import com.hisun.BP.BPCPQBNK_DATA_INFO;
import com.hisun.CI.CICACCU;
import com.hisun.CI.CICQACAC;
import com.hisun.CI.CICQACRL;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.TD.TDCCRACD;
import com.hisun.TD.TDCMSG_ERROR_MSG;

public class DDZSLLCX {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DD130";
    String K_STS_TABLE_APP = "DD";
    String CPN_I_INQ_DDPRD = "DD-I-INQ-DDPRD";
    String CPN_UNI_CIZACCU = "CI-INQ-ACCU";
    String K_HIS_COPYBOOK_NAME = "DDCSLLCX";
    String K_HIS_REMARKS = "ACTIVATION ACCOUNT";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_ERR_MSG = " ";
    short WS_CNT = 0;
    short WS_IDX = 0;
    short WS_IDX1 = 0;
    char WS_MSTR_STS = ' ';
    String WS_MTDF_AC = " ";
    double WS_MAX_AMT = 0;
    double WS_MAX_AMT1 = 0;
    double WS_MAX_AMT2 = 0;
    double WS_MAX_AMT3 = 0;
    double WS_MAX_AMT4 = 0;
    double WS_MAX_AMT5 = 0;
    double WS_BAS_RATE = 0;
    char WS_HL_FLG = ' ';
    String WS_RUL_CD = " ";
    short WS_CNT1 = 0;
    short WS_CNT2 = 0;
    short WS_CNT3 = 0;
    DDZSLLCX_WS_CHEK_INF[] WS_CHEK_INF = new DDZSLLCX_WS_CHEK_INF[5];
    char WS_MSTR_FLG = ' ';
    char WS_CCY_FLG = ' ';
    char WS_MSTR_DU_KEY_FLG = ' ';
    char WS_MSTR_OPEN_FLG = ' ';
    char WS_MSTR_SAME_DAY_FLG = ' ';
    char WS_MSTR_SAME_STRDATE_FLG = ' ';
    DDCSRATE DDCSRATE = new DDCSRATE();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDVMRAT DDVMRAT = new DDVMRAT();
    SCCCKDT SCCCKDT = new SCCCKDT();
    CICACCU CICACCU = new CICACCU();
    DDRMSTR DDRMSTR = new DDRMSTR();
    DDCOLLCX DDCOLLCX = new DDCOLLCX();
    CICQACRL CICQACRL = new CICQACRL();
    CICQACAC CICQACAC = new CICQACAC();
    DDCITIRR DDCITIRR = new DDCITIRR();
    BPCCINTI BPCCINTI = new BPCCINTI();
    TDCCRACD TDCCRACD = new TDCCRACD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    DDCSLLCX DDCSLLCX;
    public DDZSLLCX() {
        for (int i=0;i<5;i++) WS_CHEK_INF[i] = new DDZSLLCX_WS_CHEK_INF();
    }
    public void MP(SCCGWA SCCGWA, DDCSLLCX DDCSLLCX) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSLLCX = DDCSLLCX;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSLLCX return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B015_CALL_ACTY_PROC();
        if (pgmRtn) return;
        B020_GET_CI_INF_PROC();
        if (pgmRtn) return;
        B035_GET_CCY_INF_PROC();
        if (pgmRtn) return;
        B050_MSTR_INF_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSLLCX.AC);
        if (DDCSLLCX.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B015_CALL_ACTY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCSLLCX.AC;
        T000_READ_MST_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.init(SCCGWA, CICQACRL);
            CICQACRL.FUNC = 'I';
            CICQACRL.DATA.AC_NO = DDCSLLCX.AC;
            S000_CALL_CIZQACRL();
            if (pgmRtn) return;
            DDCSLLCX.AC = CICQACRL.O_DATA.O_AC_REL;
        }
    }
    public void B020_GET_CI_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDCSLLCX.AC;
        B021_CALL_CIZACCU();
        if (pgmRtn) return;
        if (CICACCU.DATA.CI_TYP == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CI_NOMTCH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B035_GET_CCY_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = DDCSLLCX.AC;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO);
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READ_DDTCCY();
        if (pgmRtn) return;
    }
    public void B021_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_UNI_CIZACCU, CICACCU);
    }
    public void B050_MSTR_INF_PROC() throws IOException,SQLException,Exception {
        B080_4_QRY_RAT_PROC();
        if (pgmRtn) return;
    }
    public void B100_GET_PRD_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIQPRD);
        DDCIQPRD.INPUT_DATA.PROD_CODE = DDRCCY.PROD_CODE;
        CEP.TRC(SCCGWA, DDRCCY.PROD_CODE);
        DDCIQPRD.INPUT_DATA.CCY = DDRCCY.CCY;
        CEP.TRC(SCCGWA, DDRCCY.CCY);
        DDCIQPRD.DDVMPRD_PTR = DDVMPRD;
        DDCIQPRD.DDVMRAT_PTR = DDVMRAT;
        S000_CALL_DDZIQPRD();
        if (pgmRtn) return;
    }
    public void B080_4_QRY_RAT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMSTR);
        DDRMSTR.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READ_DDTMSTR();
        if (pgmRtn) return;
        if (WS_MSTR_FLG == 'F') {
            IBS.init(SCCGWA, DDCOLLCX);
            DDCOLLCX.AC = DDCSLLCX.AC;
            DDCOLLCX.CCY = CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC;
            DDCOLLCX.FLG = CICQACAC.O_DATA.O_ACAC_DATA.O_CR_FLG;
            DDCOLLCX.CI_CNM = CICACCU.DATA.AC_CNM;
            DDCOLLCX.CI_ENM = CICACCU.DATA.CI_ENM;
            DDCOLLCX.ADP_NO = DDRMSTR.ADP_NO;
            DDCOLLCX.ADP_DATE = DDRMSTR.ADP_DATE;
            CEP.TRC(SCCGWA, DDRMSTR.ADP_DATE);
            CEP.TRC(SCCGWA, DDCOLLCX.ADP_DATE);
            DDCOLLCX.ADP_STRDATE = DDRMSTR.KEY.ADP_STRDATE;
            DDCSLLCX.ADP_STRDATE = DDRMSTR.KEY.ADP_STRDATE;
            DDCOLLCX.ADP_EXPDATE = DDRMSTR.ADP_EXPDATE;
            DDCSLLCX.ADP_EXPDATE = DDRMSTR.ADP_EXPDATE;
            DDCOLLCX.ADP_POST_PERIOD = DDRMSTR.ADP_POST_PERIOD;
            DDCSLLCX.ADP_POST_PERIOD = DDRMSTR.ADP_POST_PERIOD;
            DDCOLLCX.ADP_POST_FEQ = DDRMSTR.ADP_POST_DATE;
            DDCSLLCX.ADP_POST_DT = DDRMSTR.ADP_POST_DATE;
            DDCOLLCX.TIR_TYPE = DDRMSTR.TIR_TYPE;
            DDCSLLCX.TIR_TYPE = DDRMSTR.TIR_TYPE;
            DDCOLLCX.AGSP_FLG = DDRMSTR.AGSP_FLG;
            CEP.TRC(SCCGWA, DDCOLLCX.TIR_TYPE);
            CEP.TRC(SCCGWA, DDCOLLCX.ADP_POST_PERIOD);
            S000_MOVE_MSTR_TIR_TO_OLLCX_TIR();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "44444");
            CEP.TRC(SCCGWA, DDRMSTR.ADP_STS);
            if (DDRMSTR.ADP_STS == 'U') {
                B100_GET_PRD_INF_PROC();
                if (pgmRtn) return;
                B200_GET_PRD_RATE_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, SCCFMT);
            SCCFMT.FMTID = K_OUTPUT_FMT;
            SCCFMT.DATA_PTR = DDCOLLCX;
            CEP.TRC(SCCGWA, DDCOLLCX);
            SCCFMT.DATA_LEN = 935;
            IBS.FMT(SCCGWA, SCCFMT);
            if (DDCSLLCX.FUNC == 'L') {
                B100_GET_PRD_INF_PROC();
                if (pgmRtn) return;
                B300_GET_OD_RATE_PROC();
                if (pgmRtn) return;
            }
        } else {
            B100_GET_PRD_INF_PROC();
            if (pgmRtn) return;
            B200_GET_PRD_RATE_PROC();
            if (pgmRtn) return;
            if (DDCSLLCX.FUNC == 'L') {
                B310_GET_OD_RATE_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B300_GET_OD_RATE_PROC() throws IOException,SQLException,Exception {
        if (DDVMRAT.VAL.OD_RATE != 0) {
            DDCSLLCX.OD_RATE = DDVMRAT.VAL.OD_RATE;
        } else {
            DDCSLLCX.OD_TYPE = DDVMRAT.VAL.OD_INT_RBAS;
            DDCSLLCX.OD_TERM = DDVMRAT.VAL.OD_INT_RCD;
            if (DDVMRAT.VAL.OD_SPR != 0) {
                DDCSLLCX.OD_SPR_TYPE = '1';
                DDCSLLCX.OD_SPREAD = DDVMRAT.VAL.OD_SPR;
            } else {
                if (DDVMRAT.VAL.OD_PCT != 0) {
                    DDCSLLCX.OD_SPR_TYPE = '2';
                }
                DDCSLLCX.OD_PCT = DDVMRAT.VAL.OD_PCT;
            }
        }
        if (DDVMPRD.VAL.OD_POST_PERIOD1 == 'Y') {
            DDCSLLCX.OD_INTS_CYC = '1';
        } else if (DDVMPRD.VAL.OD_POST_PERIOD1 == 'H') {
            DDCSLLCX.OD_INTS_CYC = '2';
        } else if (DDVMPRD.VAL.OD_POST_PERIOD1 == 'Q') {
            DDCSLLCX.OD_INTS_CYC = '3';
        } else if (DDVMPRD.VAL.OD_POST_PERIOD1 == 'M') {
            DDCSLLCX.OD_INTS_CYC = '4';
        } else if (DDVMPRD.VAL.OD_POST_PERIOD1 == 'D') {
            DDCSLLCX.OD_INTS_CYC = '5';
        }
        if (DDCSLLCX.OD_RATE != 0) {
            DDCSLLCX.OD_CON_RATE = DDCSLLCX.OD_RATE;
        } else {
            if (DDCSLLCX.OD_TYPE.trim().length() > 0) {
                B400_GET_CON_RATE();
                if (pgmRtn) return;
            }
        }
    }
    public void B310_GET_OD_RATE_PROC() throws IOException,SQLException,Exception {
        if (DDVMRAT.VAL.TIER[1-1].FIX_RATE != 0) {
            DDCSLLCX.RATE_MTH = '1';
            DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_FIX_RATE1 = DDVMRAT.VAL.TIER[1-1].FIX_RATE;
        } else {
            DDCSLLCX.RATE_MTH = '2';
            DDCSLLCX.SPR_TYPE = DDVMRAT.VAL.TIER[1-1].SPR_TYPE;
            DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_RBAS1 = DDVMRAT.VAL.TIER[1-1].TIER_IR[1-1].INT_RBAS;
            DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_RCD1 = DDVMRAT.VAL.TIER[1-1].TIER_IR[1-1].INT_RCD;
            DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_SPR1 = DDVMRAT.VAL.TIER[1-1].TIER_IR[1-1].TIER_SPR;
            DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_SPR_PCT1 = DDVMRAT.VAL.TIER[1-1].TIER_IR[1-1].TIER_SPR_PCT;
        }
        DDCSLLCX.ADP_POST_PERIOD = DDVMPRD.VAL.DEP_POST_PERIOD1;
        DDCSLLCX.ADP_POST_DT = DDVMPRD.VAL.DEP_POST_DATE1;
        if (DDVMPRD.VAL.DEP_POST_PERIOD1 == 'Y') {
            DDCSLLCX.INTS_CYC = '1';
        } else if (DDVMPRD.VAL.DEP_POST_PERIOD1 == 'H') {
            DDCSLLCX.INTS_CYC = '2';
        } else if (DDVMPRD.VAL.DEP_POST_PERIOD1 == 'Q') {
            DDCSLLCX.INTS_CYC = '3';
        } else if (DDVMPRD.VAL.DEP_POST_PERIOD1 == 'M') {
            DDCSLLCX.INTS_CYC = '4';
        } else if (DDVMPRD.VAL.DEP_POST_PERIOD1 == 'D') {
            DDCSLLCX.INTS_CYC = '5';
        }
        if (DDVMRAT.VAL.OD_RATE != 0) {
            DDCSLLCX.OD_RATE = DDVMRAT.VAL.OD_RATE;
        } else {
            DDCSLLCX.OD_TYPE = DDVMRAT.VAL.OD_INT_RBAS;
            DDCSLLCX.OD_TERM = DDVMRAT.VAL.OD_INT_RCD;
            if (DDVMRAT.VAL.OD_SPR != 0) {
                DDCSLLCX.OD_SPR_TYPE = '1';
                DDCSLLCX.OD_SPREAD = DDVMRAT.VAL.OD_SPR;
            } else {
                if (DDVMRAT.VAL.OD_PCT != 0) {
                    DDCSLLCX.OD_SPR_TYPE = '2';
                }
                DDCSLLCX.OD_PCT = DDVMRAT.VAL.OD_PCT;
            }
        }
        if (DDVMPRD.VAL.OD_POST_PERIOD1 == 'Y') {
            DDCSLLCX.OD_INTS_CYC = '1';
        } else if (DDVMPRD.VAL.OD_POST_PERIOD1 == 'H') {
            DDCSLLCX.OD_INTS_CYC = '2';
        } else if (DDVMPRD.VAL.OD_POST_PERIOD1 == 'Q') {
            DDCSLLCX.OD_INTS_CYC = '3';
        } else if (DDVMPRD.VAL.OD_POST_PERIOD1 == 'M') {
            DDCSLLCX.OD_INTS_CYC = '4';
        } else if (DDVMPRD.VAL.OD_POST_PERIOD1 == 'D') {
            DDCSLLCX.OD_INTS_CYC = '5';
        }
        if (DDCSLLCX.OD_RATE != 0) {
            DDCSLLCX.OD_CON_RATE = DDCSLLCX.OD_RATE;
        } else {
            if (DDCSLLCX.OD_TYPE.trim().length() > 0) {
                B400_GET_CON_RATE();
                if (pgmRtn) return;
            }
        }
    }
    public void B400_GET_CON_RATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSRATE);
        DDCSRATE.RATE_TYP = DDCSLLCX.OD_TYPE;
        DDCSRATE.RAT_TERM = DDCSLLCX.OD_TERM;
        DDCSRATE.FLOAT_TP = DDCSLLCX.OD_SPR_TYPE;
        DDCSRATE.F_SPRD = DDCSLLCX.OD_SPREAD;
        DDCSRATE.F_PCNT = DDCSLLCX.OD_PCT;
        DDCSRATE.CCY = DDRCCY.CCY;
        CEP.TRC(SCCGWA, DDCSRATE.RATE_TYP);
        CEP.TRC(SCCGWA, DDCSRATE.RAT_TERM);
        CEP.TRC(SCCGWA, DDCSRATE.FLOAT_TP);
        CEP.TRC(SCCGWA, DDCSRATE.F_SPRD);
        CEP.TRC(SCCGWA, DDCSRATE.F_PCNT);
        CEP.TRC(SCCGWA, DDCSRATE.CCY);
        S000_CALL_DDZSRATE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCSRATE.CON_RATE);
        DDCSLLCX.OD_CON_RATE = DDCSRATE.CON_RATE;
    }
    public void B200_GET_PRD_RATE_PROC() throws IOException,SQLException,Exception {
        if (DDVMPRD.VAL.CAL_DINT_METH != '3') {
            IBS.init(SCCGWA, DDCITIRR);
            if (DDVMRAT.VAL.TIER[1-1].TAMT != 0 
                || DDVMRAT.VAL.TIER[1-1].TGRP.trim().length() > 0 
                || !DDVMRAT.VAL.TIER[1-1].TCLS.equalsIgnoreCase("0")) {
                for (WS_CNT = 1; WS_CNT <= 5; WS_CNT += 1) {
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDVMRAT.VAL.TIER[1-1].TIER_IR[WS_CNT-1]);
                    IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCITIRR.RBASE_INFO[WS_CNT-1]);
                }
                CEP.TRC(SCCGWA, DDCITIRR.RBASE_INFO[1-1]);
                DDCITIRR.AC_NO = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
                DDCITIRR.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
                DDCITIRR.CCY = DDRCCY.CCY;
                DDCITIRR.RUL_CD = DDVMRAT.VAL.TIER[1-1].TGRP;
                DDCITIRR.TYPE = DDVMRAT.VAL.TIER[1-1].SPR_TYPE;
                DDCITIRR.HL_FLAG = DDVMRAT.VAL.TIER[1-1].HL_FLG;
                DDCITIRR.MAX_RATE = DDVMRAT.VAL.TIER[1-1].MAX_RATE;
                DDCITIRR.MIN_RATE = DDVMRAT.VAL.TIER[1-1].MIN_RATE;
                DDCITIRR.FIX_RATE = DDVMRAT.VAL.TIER[1-1].FIX_RATE;
                CEP.TRC(SCCGWA, DDVMRAT.VAL.TIER[1-1].SPR_TYPE);
                CEP.TRC(SCCGWA, DDCITIRR);
                S000_CALL_DDZITIRR();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, DDCITIRR.COMPUTED_RATE);
                DDCOLLCX.CON_RATE = DDCITIRR.COMPUTED_RATE;
                DDCSLLCX.CON_RATE = DDCITIRR.COMPUTED_RATE;
            } else {
                DDCOLLCX.CON_RATE = 0;
                DDCSLLCX.CON_RATE = 0;
            }
            if (DDRMSTR.ADP_STS != 'U' 
                || DDCSLLCX.FUNC == 'L') {
                S000_MOVE_OUT_TO_OLLCX_PROC();
                if (pgmRtn) return;
            }
        } else {
            CEP.TRC(SCCGWA, "MPRD-CAL-DINT-NOTINT");
            S000_MOVE_OUT_TO_OLLCX_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_MOVE_OUT_TO_OLLCX_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOLLCX);
        DDCOLLCX.AC = DDCSLLCX.AC;
        DDCOLLCX.CCY = CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC;
        DDCOLLCX.FLG = CICQACAC.O_DATA.O_ACAC_DATA.O_CR_FLG;
        DDCOLLCX.CI_CNM = CICACCU.DATA.AC_CNM;
        DDCOLLCX.CI_ENM = CICACCU.DATA.CI_ENM;
        DDCOLLCX.TIR_TYPE = DDVMRAT.VAL.TIER_TYPE;
        DDCSLLCX.TIR_TYPE = DDVMRAT.VAL.TIER_TYPE;
        DDCOLLCX.AGSP_FLG = DDVMRAT.VAL.AMT_TIER_TYPE;
        DDCOLLCX.CON_RATE = DDCITIRR.COMPUTED_RATE;
        DDCSLLCX.CON_RATE = DDCITIRR.COMPUTED_RATE;
        S000_MOVE_PRD_TIR_TO_OLLCX_TIR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "55555");
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DDCOLLCX;
        CEP.TRC(SCCGWA, DDCOLLCX);
        SCCFMT.DATA_LEN = 935;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_DDTMSTR() throws IOException,SQLException,Exception {
        WS_MSTR_FLG = 'N';
        DDTMSTR_RD = new DBParm();
        DDTMSTR_RD.TableName = "DDTMSTR";
        DDTMSTR_RD.where = "AC = :DDRMSTR.KEY.AC "
            + "AND ADP_STS < > 'F' "
            + "AND ADP_TYPE = '1'";
        DDTMSTR_RD.fst = true;
        IBS.READ(SCCGWA, DDRMSTR, this, DDTMSTR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_MSTR_FLG = 'F';
        } else {
            WS_MSTR_FLG = 'N';
        }
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
        if (CICQACRL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACRL.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_I_INQ_DDPRD, DDCIQPRD);
        if (DDCIQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZSRATE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-RATE", DDCSRATE);
    }
    public void T000_READ_MST_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.where = "CUS_AC = :DDRMST.KEY.CUS_AC";
        DDTMST_RD.fst = true;
        DDTMST_RD.order = "CUS_AC";
        IBS.READ(SCCGWA, DDRMST, this, DDTMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CCY_FLG = 'F';
        }
    }
    public void S000_MOVE_PRD_TIR_TO_OLLCX_TIR() throws IOException,SQLException,Exception {
        for (WS_IDX = 1; WS_IDX <= 5; WS_IDX += 1) {
            DDCOLLCX.TIR_DATA.TIR_INF[WS_IDX-1].TIR_AMT1 = DDVMRAT.VAL.TIER[WS_IDX-1].TAMT;
            DDCOLLCX.TIR_DATA.TIR_INF[WS_IDX-1].TIR_RBAS1 = DDVMRAT.VAL.TIER[1-1].TIER_IR[WS_IDX-1].INT_RBAS;
            DDCOLLCX.TIR_DATA.TIR_INF[WS_IDX-1].TIR_RCD1 = DDVMRAT.VAL.TIER[1-1].TIER_IR[WS_IDX-1].INT_RCD;
            DDCOLLCX.TIR_DATA.TIR_INF[WS_IDX-1].TIR_SPR1 = DDVMRAT.VAL.TIER[1-1].TIER_IR[WS_IDX-1].TIER_SPR;
            DDCOLLCX.TIR_DATA.TIR_INF[WS_IDX-1].TIR_SPR_PCT1 = DDVMRAT.VAL.TIER[1-1].TIER_IR[WS_IDX-1].TIER_SPR_PCT;
            if (DDVMRAT.VAL.TIER[1-1].TIER_IR[WS_IDX-1].TIER_SPR != 0) {
                DDCOLLCX.TIR_DATA.TIR_INF[WS_IDX-1].TIR_SPR_TYPE1 = '1';
            } else {
                if (DDVMRAT.VAL.TIER[1-1].TIER_IR[WS_IDX-1].TIER_SPR_PCT != 0) {
                    DDCOLLCX.TIR_DATA.TIR_INF[WS_IDX-1].TIR_SPR_TYPE1 = '2';
                }
            }
        }
    }
    public void S000_MOVE_MSTR_TIR_TO_OLLCX_TIR() throws IOException,SQLException,Exception {
        DDCOLLCX.TIR_DATA.TIR_INF[1-1].TIR_AMT1 = DDRMSTR.TIR_AMT11;
        DDCOLLCX.TIR_DATA.TIR_INF[1-1].TIR_RBAS1 = DDRMSTR.TIR_RBAS11;
        DDCOLLCX.TIR_DATA.TIR_INF[1-1].TIR_RCD1 = DDRMSTR.TIR_RCD11;
        DDCOLLCX.TIR_DATA.TIR_INF[1-1].TIR_SPR1 = DDRMSTR.TIR_SPR11;
        DDCOLLCX.TIR_DATA.TIR_INF[1-1].TIR_SPR_PCT1 = DDRMSTR.TIR_SPR_PCT11;
        DDCOLLCX.TIR_DATA.TIR_INF[1-1].TIR_FIX_RATE1 = DDRMSTR.TIR_FIX_RATE11;
        DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_AMT1 = DDRMSTR.TIR_AMT11;
        DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_RBAS1 = DDRMSTR.TIR_RBAS11;
        DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_RCD1 = DDRMSTR.TIR_RCD11;
        DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_SPR1 = DDRMSTR.TIR_SPR11;
        DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_SPR_PCT1 = DDRMSTR.TIR_SPR_PCT11;
        DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_FIX_RATE1 = DDRMSTR.TIR_FIX_RATE11;
        if (DDRMSTR.TIR_FIX_RATE11 != 0) {
            DDCSLLCX.CON_RATE = DDRMSTR.TIR_FIX_RATE11;
        } else {
            if (DDRMSTR.TIR_RBAS11.trim().length() > 0) {
                IBS.init(SCCGWA, BPCCINTI);
                BPCCINTI.FUNC = 'I';
                BPCCINTI.BASE_INFO.DT = SCCGWA.COMM_AREA.AC_DATE;
                BPCCINTI.BASE_INFO.BR = SCCGWA.COMM_AREA.HQT_BANK;
                BPCCINTI.BASE_INFO.BASE_TYP = DDRMSTR.TIR_RBAS11;
                BPCCINTI.BASE_INFO.TENOR = DDRMSTR.TIR_RCD11;
                BPCCINTI.BASE_INFO.CCY = DDRCCY.CCY;
                S00_CALL_INQUIRE_RATE();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.OWN_RATE);
                WS_BAS_RATE = BPCCINTI.BASE_INFO.OWN_RATE;
                WS_HL_FLG = 'H';
                if (DDRMSTR.TIR_SPR11 == 0 
                    && DDRMSTR.TIR_SPR_PCT11 == 0 
                    && DDRMSTR.TIR_GRP11.trim().length() == 0) {
                    DDCSLLCX.CON_RATE = BPCCINTI.BASE_INFO.OWN_RATE;
                }
                if (DDRMSTR.TIR_SPR11 != 0) {
                    DDCSLLCX.CON_RATE = BPCCINTI.BASE_INFO.OWN_RATE + DDRMSTR.TIR_SPR11;
                } else {
                    if (DDRMSTR.TIR_SPR_PCT11 != 0) {
                        DDCSLLCX.CON_RATE = BPCCINTI.BASE_INFO.OWN_RATE + ( BPCCINTI.BASE_INFO.OWN_RATE * DDRMSTR.TIR_SPR_PCT11 / 100 );
                    }
                }
                if (DDRMSTR.TIR_GRP11.trim().length() > 0) {
                    WS_RUL_CD = DDRMSTR.TIR_GRP11;
                    R000_GET_RULE_RATE();
                    if (pgmRtn) return;
                    DDCSLLCX.CON_RATE = TDCCRACD.OUT_INF.RATE;
                }
            }
        }
        DDCOLLCX.TIR_DATA.TIR_INF[2-1].TIR_AMT1 = DDRMSTR.TIR_AMT12;
        DDCOLLCX.TIR_DATA.TIR_INF[2-1].TIR_RBAS1 = DDRMSTR.TIR_RBAS12;
        DDCOLLCX.TIR_DATA.TIR_INF[2-1].TIR_RCD1 = DDRMSTR.TIR_RCD12;
        DDCOLLCX.TIR_DATA.TIR_INF[2-1].TIR_SPR1 = DDRMSTR.TIR_SPR12;
        DDCOLLCX.TIR_DATA.TIR_INF[2-1].TIR_SPR_PCT1 = DDRMSTR.TIR_SPR_PCT12;
        DDCOLLCX.TIR_DATA.TIR_INF[2-1].TIR_FIX_RATE1 = DDRMSTR.TIR_FIX_RATE12;
        DDCOLLCX.TIR_DATA.TIR_INF[3-1].TIR_AMT1 = DDRMSTR.TIR_AMT13;
        DDCOLLCX.TIR_DATA.TIR_INF[3-1].TIR_RBAS1 = DDRMSTR.TIR_RBAS13;
        DDCOLLCX.TIR_DATA.TIR_INF[3-1].TIR_RCD1 = DDRMSTR.TIR_RCD13;
        DDCOLLCX.TIR_DATA.TIR_INF[3-1].TIR_SPR1 = DDRMSTR.TIR_SPR13;
        DDCOLLCX.TIR_DATA.TIR_INF[3-1].TIR_SPR_PCT1 = DDRMSTR.TIR_SPR_PCT13;
        DDCOLLCX.TIR_DATA.TIR_INF[3-1].TIR_FIX_RATE1 = DDRMSTR.TIR_FIX_RATE13;
        DDCOLLCX.TIR_DATA.TIR_INF[4-1].TIR_AMT1 = DDRMSTR.TIR_AMT14;
        DDCOLLCX.TIR_DATA.TIR_INF[4-1].TIR_RBAS1 = DDRMSTR.TIR_RBAS14;
        DDCOLLCX.TIR_DATA.TIR_INF[4-1].TIR_RCD1 = DDRMSTR.TIR_RCD14;
        DDCOLLCX.TIR_DATA.TIR_INF[4-1].TIR_SPR1 = DDRMSTR.TIR_SPR14;
        DDCOLLCX.TIR_DATA.TIR_INF[4-1].TIR_SPR_PCT1 = DDRMSTR.TIR_SPR_PCT14;
        DDCOLLCX.TIR_DATA.TIR_INF[4-1].TIR_FIX_RATE1 = DDRMSTR.TIR_FIX_RATE14;
        DDCOLLCX.TIR_DATA.TIR_INF[5-1].TIR_AMT1 = DDRMSTR.TIR_AMT15;
        DDCOLLCX.TIR_DATA.TIR_INF[5-1].TIR_RBAS1 = DDRMSTR.TIR_RBAS15;
        DDCOLLCX.TIR_DATA.TIR_INF[5-1].TIR_RCD1 = DDRMSTR.TIR_RCD15;
        DDCOLLCX.TIR_DATA.TIR_INF[5-1].TIR_SPR1 = DDRMSTR.TIR_SPR15;
        DDCOLLCX.TIR_DATA.TIR_INF[5-1].TIR_SPR_PCT1 = DDRMSTR.TIR_SPR_PCT15;
        DDCOLLCX.TIR_DATA.TIR_INF[5-1].TIR_FIX_RATE1 = DDRMSTR.TIR_FIX_RATE15;
        for (WS_IDX = 1; WS_IDX <= 5; WS_IDX += 1) {
            if (DDCOLLCX.TIR_DATA.TIR_INF[WS_IDX-1].TIR_RBAS1.trim().length() > 0 
                && DDCOLLCX.TIR_DATA.TIR_INF[WS_IDX-1].TIR_RCD1.trim().length() > 0 
                && DDCOLLCX.TIR_DATA.TIR_INF[WS_IDX-1].TIR_FIX_RATE1 == 0) {
                DDCOLLCX.TIR_DATA.TIR_INF[WS_IDX-1].TIR_SPR_TYPE1 = '0';
            }
            if (DDCOLLCX.TIR_DATA.TIR_INF[WS_IDX-1].TIR_SPR1 != 0) {
                DDCOLLCX.TIR_DATA.TIR_INF[WS_IDX-1].TIR_SPR_TYPE1 = '1';
            } else {
                if (DDCOLLCX.TIR_DATA.TIR_INF[WS_IDX-1].TIR_SPR_PCT1 != 0) {
                    DDCOLLCX.TIR_DATA.TIR_INF[WS_IDX-1].TIR_SPR_TYPE1 = '2';
                }
            }
        }
        for (WS_IDX1 = 1; WS_IDX1 <= 5; WS_IDX1 += 1) {
            if (DDCOLLCX.TIR_DATA.TIR_INF[WS_IDX1-1].TIR_FIX_RATE1 != 0) {
                if (DDRMSTR.ADP_STS != 'U') {
                    DDCOLLCX.CON_RATE = DDCOLLCX.TIR_DATA.TIR_INF[WS_IDX1-1].TIR_FIX_RATE1;
                }
                DDCOLLCX.TIR_DATA.TIR_INF[WS_IDX1-1].TIR_FLOOR_RATE1 = DDCOLLCX.TIR_DATA.TIR_INF[WS_IDX1-1].TIR_FIX_RATE1;
            } else {
                if (DDCOLLCX.TIR_DATA.TIR_INF[WS_IDX1-1].TIR_RBAS1.trim().length() > 0) {
                    IBS.init(SCCGWA, BPCCINTI);
                    BPCCINTI.FUNC = 'I';
                    BPCCINTI.BASE_INFO.DT = SCCGWA.COMM_AREA.AC_DATE;
                    BPCCINTI.BASE_INFO.BR = SCCGWA.COMM_AREA.HQT_BANK;
                    BPCCINTI.BASE_INFO.BASE_TYP = DDCOLLCX.TIR_DATA.TIR_INF[WS_IDX1-1].TIR_RBAS1;
                    BPCCINTI.BASE_INFO.TENOR = DDCOLLCX.TIR_DATA.TIR_INF[WS_IDX1-1].TIR_RCD1;
                    BPCCINTI.BASE_INFO.CCY = DDRCCY.CCY;
                    S00_CALL_INQUIRE_RATE();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.OWN_RATE);
                    if (DDCOLLCX.TIR_DATA.TIR_INF[WS_IDX1-1].TIR_SPR1 == 0 
                        && DDCOLLCX.TIR_DATA.TIR_INF[WS_IDX1-1].TIR_SPR_PCT1 == 0) {
                        if (DDRMSTR.ADP_STS != 'U') {
                            DDCOLLCX.CON_RATE = BPCCINTI.BASE_INFO.OWN_RATE;
                        }
                        DDCOLLCX.TIR_DATA.TIR_INF[WS_IDX1-1].TIR_FLOOR_RATE1 = BPCCINTI.BASE_INFO.OWN_RATE;
                    }
                    if (DDCOLLCX.TIR_DATA.TIR_INF[WS_IDX1-1].TIR_SPR1 != 0) {
                        if (DDRMSTR.ADP_STS != 'U') {
                            DDCOLLCX.CON_RATE = BPCCINTI.BASE_INFO.OWN_RATE + DDCOLLCX.TIR_DATA.TIR_INF[WS_IDX1-1].TIR_SPR1;
                        }
                        DDCOLLCX.TIR_DATA.TIR_INF[WS_IDX1-1].TIR_FLOOR_RATE1 = BPCCINTI.BASE_INFO.OWN_RATE + DDCOLLCX.TIR_DATA.TIR_INF[WS_IDX1-1].TIR_SPR1;
                    } else {
                        if (DDCOLLCX.TIR_DATA.TIR_INF[WS_IDX1-1].TIR_SPR_PCT1 != 0) {
                            if (DDRMSTR.ADP_STS != 'U') {
                                DDCOLLCX.CON_RATE = BPCCINTI.BASE_INFO.OWN_RATE + ( BPCCINTI.BASE_INFO.OWN_RATE * DDCOLLCX.TIR_DATA.TIR_INF[WS_IDX1-1].TIR_SPR_PCT1 / 100 );
                            }
                            DDCOLLCX.TIR_DATA.TIR_INF[WS_IDX1-1].TIR_FLOOR_RATE1 = BPCCINTI.BASE_INFO.OWN_RATE + ( BPCCINTI.BASE_INFO.OWN_RATE * DDCOLLCX.TIR_DATA.TIR_INF[WS_IDX1-1].TIR_SPR_PCT1 / 100 );
                        }
                    }
                }
            }
            if (DDCOLLCX.TIR_DATA.TIR_INF[WS_IDX1-1].TIR_AMT1 == 99999999999999.99) {
                CEP.TRC(SCCGWA, "LAST FLOOR EXIT PERFORM");
