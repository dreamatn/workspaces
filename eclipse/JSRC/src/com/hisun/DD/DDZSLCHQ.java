package com.hisun.DD;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCFCSTS;
import com.hisun.BP.BPCFFTXI;
import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPCO1821_OPT_1821;
import com.hisun.BP.BPCPLOSS;
import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPRMM;
import com.hisun.BP.BPCSGSEQ;
import com.hisun.BP.BPCSLOSS;
import com.hisun.BP.BPCTCALF;
import com.hisun.BP.BPCUBUSE;
import com.hisun.BP.BPRLOSS;
import com.hisun.BP.BPRPRMT;
import com.hisun.CI.CICACCU;
import com.hisun.CI.CICQACAC;
import com.hisun.DC.DCCPACTY;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class DDZSLCHQ {
    boolean pgmRtn = false;
    String K_STS_TABLE_APP = "DD";
    String K_CHK_STS_TBL_C = "5443";
    String K_CHK_STS_TBL_P = "0002";
    String K_OUTPUT_FMT = "DD904";
    String K_OUTPUT_FMT3 = "DD516";
    String K_HIS_CPB_NM = "DDCHSTOP";
    String K_CTL_PRM_TYP = "PDD04";
    String WS_CHQ_BV_CD = "001";
    String K_HIS_RMKS = "CHEQUE STOP MAINTAIN";
    String CPN_SVR_LCHQ_PROC = "DD-SVR-LCHQ-PROC";
    String CPN_SCSSCKDT = "SCSSCKDT";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_ERR_MSG = " ";
    short WS_IDX = 0;
    long WS_STR_NO = 0;
    long WS_STR_CHQ_NO = 0;
    int WS_CHQ_CNT = 0;
    String WS_CHQ_STS = " ";
    char WS_CHQ_ULOST = ' ';
    int WS_CNT = 0;
    short WS_STR_POS = 0;
    short WS_CUR_POS = 0;
    int WS_EFF_DATE = 0;
    int WS_EXP_DATE = 0;
    int WS_LOST_EFF_DATE = 0;
    double WS_TOT_AMT = 0;
    long WS_CHQ_NO = 0;
    long WS_CHQ_STR_CHQ_NO = 0;
    long WS_CHQ_END_CHQ_NO = 0;
    long WS_SLCHQ_END_CHQ_NO = 0;
    long WS_SLCHQ_STR_CHQ_NO = 0;
    DDZSLCHQ_WS_STOP_STR_CHQ_NO WS_STOP_STR_CHQ_NO = new DDZSLCHQ_WS_STOP_STR_CHQ_NO();
    DDZSLCHQ_WS_STOP_END_CHQ_NO WS_STOP_END_CHQ_NO = new DDZSLCHQ_WS_STOP_END_CHQ_NO();
    int WS_LEN = 0;
    int WS_LEE = 0;
    String WS_SLCHQ_STR_CHQ_X = " ";
    String WS_SLCHQ_END_CHQ_X = " ";
    String WS_LOST_NO = " ";
    String WS_LOS_NO = " ";
    char WS_STS = ' ';
    char WS_CHQ_FLG = ' ';
    char WS_STOP_FLG = ' ';
    char WS_READ_STOP_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DDCOSTP3 DDCOSTP3 = new DDCOSTP3();
    DDCOSTNT DDCOSTNT = new DDCOSTNT();
    DDCHSTOP DDCSTOPO = new DDCHSTOP();
    DDCHSTOP DDCSTOPN = new DDCHSTOP();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    BPCUBUSE BPCUBUSE = new BPCUBUSE();
    BPCTCALF BPCTCALF = new BPCTCALF();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    CICACCU CICACCU = new CICACCU();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCCLDT SCCCLDT = new SCCCLDT();
    DPCPARMP DPCPARMP = new DPCPARMP();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    DDCSPCHQ DDCSPCHQ = new DDCSPCHQ();
    DDCPWTT DDCPWTT = new DDCPWTT();
    DCCPACTY DCCPACTY = new DCCPACTY();
    CICQACAC CICQACAC = new CICQACAC();
    BPCSLOSS BPCSLOSS = new BPCSLOSS();
    BPRLOSS BPRLOSS = new BPRLOSS();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    BPCPLOSS BPCPLOSS = new BPCPLOSS();
    BPCO1821_OPT_1821 BPCO1821_OPT_1821 = new BPCO1821_OPT_1821();
    DDRMST DDRMST = new DDRMST();
    DDRCHQ DDRCHQ = new DDRCHQ();
    DDRCCY DDRCCY = new DDRCCY();
    DDRSTOP DDRSTOP = new DDRSTOP();
    DDRPAID DDRPAID = new DDRPAID();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    DDCSLCHQ DDCSLCHQ;
    public void MP(SCCGWA SCCGWA, DDCSLCHQ DDCSLCHQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSLCHQ = DDCSLCHQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSLCHQ return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        WS_LEN = 0;
        WS_LEN = DDCSLCHQ.STR_CHQ_NO.trim().length();
        if (DDCSLCHQ.STR_CHQ_NO.trim().length() > 0) {
            if (DDCSLCHQ.STR_CHQ_NO == null) DDCSLCHQ.STR_CHQ_NO = "";
            JIBS_tmp_int = DDCSLCHQ.STR_CHQ_NO.length();
            for (int i=0;i<16-JIBS_tmp_int;i++) DDCSLCHQ.STR_CHQ_NO += " ";
            if (!IBS.isNumeric(DDCSLCHQ.STR_CHQ_NO.substring(0, WS_LEN))) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B005_GET_ACAC_INFO();
        if (pgmRtn) return;
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B030_GET_AC_INF_PROC();
        if (pgmRtn) return;
        B035_CHK_AC_STS();
        if (pgmRtn) return;
        B040_CI_INF_PROC();
        if (pgmRtn) return;
        B050_CHK_STS_TBL_PROC();
        if (pgmRtn) return;
        if (DDCSLCHQ.FUNC == '1') {
            B070_STOP_CHQ_PROC();
            if (pgmRtn) return;
            B080_CRT_STOP_PROC();
            if (pgmRtn) return;
            B090_CHARGES_PROC();
            if (pgmRtn) return;
        } else if (DDCSLCHQ.FUNC == '2') {
            B110_RELEASE_CHQ_PROC();
            if (pgmRtn) return;
            B100_RELEASE_STOP_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DDCSLCHQ.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        B120_NFIN_TXN_HIS_PROC();
        if (pgmRtn) return;
        B130_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B005_GET_ACAC_INFO() throws IOException,SQLException,Exception {
        if (DDCSLCHQ.AC_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.DATA.AGR_NO = DDCSLCHQ.AC_NO;
            CICQACAC.DATA.CCY_ACAC = "156";
            CICQACAC.DATA.CR_FLG = '1';
            CICQACAC.FUNC = 'C';
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSLCHQ.FUNC);
        CEP.TRC(SCCGWA, DDCSLCHQ.AC_NO);
        CEP.TRC(SCCGWA, DDCSLCHQ.CHQ_TYP);
        CEP.TRC(SCCGWA, DDCSLCHQ.STR_CHQ_NO);
        CEP.TRC(SCCGWA, DDCSLCHQ.LOST_TYPE);
        CEP.TRC(SCCGWA, DDCSLCHQ.UNW_LOST_DATE);
        CEP.TRC(SCCGWA, DDRCHQ.KEY.END_CHQ_NO);
        CEP.TRC(SCCGWA, DDCSLCHQ.W_LOST_DATE);
        if (DDCSLCHQ.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSLCHQ.FUNC == '1') {
            if (DDCSLCHQ.LOS_DATE > SCCGWA.COMM_AREA.AC_DATE 
                && DDCSLCHQ.LOS_DATE != 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_DT_CANNOT_GT_ACDT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCSLCHQ.UNW_LOST_DATE == 0) {
                WS_LOST_EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
                CEP.TRC(SCCGWA, WS_LOST_EFF_DATE);
            } else {
                CEP.TRC(SCCGWA, "11111111111111");
                IBS.init(SCCGWA, SCCCKDT);
                SCCCKDT.DATE = DDCSLCHQ.UNW_LOST_DATE;
                CEP.TRC(SCCGWA, "2222222222222222222");
                SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
                SCSSCKDT1.MP(SCCGWA, SCCCKDT);
                CEP.TRC(SCCGWA, "333333333333333333");
                if (SCCCKDT.RC != 0) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_EFF_DT_INVALID;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, "AAAAAAAAA");
                CEP.TRC(SCCGWA, DDCSLCHQ.UNW_LOST_DATE);
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
                if (DDCSLCHQ.UNW_LOST_DATE < SCCGWA.COMM_AREA.AC_DATE 
                    && DDCSLCHQ.LOST_TYPE == '3') {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_EFF_DATE_LT_ACDT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                WS_LOST_EFF_DATE = DDCSLCHQ.UNW_LOST_DATE;
            }
            if (DDCSLCHQ.W_LOST_DATE == 0) {
                WS_LOST_EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
            } else {
                IBS.init(SCCGWA, SCCCKDT);
                SCCCKDT.DATE = DDCSLCHQ.W_LOST_DATE;
                SCSSCKDT SCSSCKDT2 = new SCSSCKDT();
                SCSSCKDT2.MP(SCCGWA, SCCCKDT);
                if (SCCCKDT.RC != 0) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_EFF_DT_INVALID;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (DDCSLCHQ.W_LOST_DATE < SCCGWA.COMM_AREA.AC_DATE 
                    && DDCSLCHQ.LOST_TYPE == '4') {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_EFF_DATE_LT_ACDT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                WS_LOST_EFF_DATE = DDCSLCHQ.W_LOST_DATE;
            }
            if (DDCSLCHQ.EXP_DATE != 0) {
                IBS.init(SCCGWA, SCCCKDT);
                SCCCKDT.DATE = DDCSLCHQ.EXP_DATE;
                SCSSCKDT SCSSCKDT3 = new SCSSCKDT();
                SCSSCKDT3.MP(SCCGWA, SCCCKDT);
                if (SCCCKDT.RC != 0) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_EXP_DT_INVALID;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (DDCSLCHQ.EXP_DATE < WS_LOST_EFF_DATE) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_EXP_DATE_LT_EFFDT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (DDCSLCHQ.STR_CHQ_NO.equalsIgnoreCase("0")) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_STR_CHQ_NO_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DDCSLCHQ.FUNC == '2') {
            if (DDCSLCHQ.LOST_TYPE == '3' 
                && DDCSLCHQ.UNW_LOST_DATE == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_UNW_EFF_DT_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCSLCHQ.LOST_TYPE == '4' 
                && DDCSLCHQ.W_LOST_DATE == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_W_EFF_DT_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCSLCHQ.LOS_NO.trim().length() == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_LOS_NO_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, DDCSLCHQ.CHQ_TYP);
        if (DDCSLCHQ.CHQ_TYP != ' ' 
            && DDCSLCHQ.FUNC == '1') {
            if (DDCSLCHQ.CHQ_TYP == '4' 
                || DDCSLCHQ.CHQ_TYP == '6') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_LOST_TYPE_ERROR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCSLCHQ.CHQ_TYP != '1' 
                && DDCSLCHQ.CHQ_TYP != '2' 
                && DDCSLCHQ.CHQ_TYP != '3' 
                && DDCSLCHQ.CHQ_TYP != '4' 
                && DDCSLCHQ.CHQ_TYP != '5' 
                && DDCSLCHQ.CHQ_TYP != '6') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ERR_CHQ_TYPE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, DDCSLCHQ.CHQ_DATE);
        if (DDCSLCHQ.CHQ_DATE > SCCGWA.COMM_AREA.AC_DATE 
            && DDCSLCHQ.FUNC == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQ_DATE_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_GET_AC_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCSLCHQ.AC_NO;
        T000_READ_DDTMST();
        if (pgmRtn) return;
    }
    public void B035_CHK_AC_STS() throws IOException,SQLException,Exception {
        if (DDRMST.AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRMST.AC_STS == 'D') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_IS_LONG_HOVER;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B040_CI_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDCSLCHQ.AC_NO;
        S000_CALL_CISOACCU();
        if (pgmRtn) return;
    }
    public void B050_CHK_STS_TBL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFCSTS);
        BPCFCSTS.AP_MMO = K_STS_TABLE_APP;
        if (CICACCU.DATA.CI_TYP == '1') {
            BPCFCSTS.TBL_NO = K_CHK_STS_TBL_P;
        } else {
            BPCFCSTS.TBL_NO = K_CHK_STS_TBL_C;
        }
        if (CICACCU.DATA.CI_STSW == null) CICACCU.DATA.CI_STSW = "";
        JIBS_tmp_int = CICACCU.DATA.CI_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICACCU.DATA.CI_STSW += " ";
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        BPCFCSTS.STATUS_WORD = CICACCU.DATA.CI_STSW.substring(0, 80) + BPCFCSTS.STATUS_WORD.substring(80);
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 101 - 1) + DDRMST.AC_STS_WORD + BPCFCSTS.STATUS_WORD.substring(101 + 99 - 1);
        CEP.TRC(SCCGWA, BPCFCSTS.AP_MMO);
        CEP.TRC(SCCGWA, BPCFCSTS.TBL_NO);
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD);
        S000_CALL_BPZFCSTS();
        if (pgmRtn) return;
    }
    public void B090_CHARGES_PROC() throws IOException,SQLException,Exception {
        if (DDCSLCHQ.FUNC == '1' 
            && DDCSLCHQ.LOST_TYPE == '3') {
            B090_30_SET_FEE_INFO();
            if (pgmRtn) return;
            B090_50_CALL_FEE();
            if (pgmRtn) return;
        }
    }
    public void B090_10_GET_TOT_CHQ_AMT() throws IOException,SQLException,Exception {
        WS_TOT_AMT = 0;
        if (DDCSLCHQ.STR_CHQ_NO.trim().length() == 0) WS_STR_CHQ_NO = 0;
        else WS_STR_CHQ_NO = Long.parseLong(DDCSLCHQ.STR_CHQ_NO);
        IBS.init(SCCGWA, DDRPAID);
        DDRPAID.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRPAID.KEY.CHQ_TYP = DDCSLCHQ.CHQ_TYP;
        DDRPAID.KEY.CHQ_NO = "" + WS_STR_CHQ_NO;
        JIBS_tmp_int = DDRPAID.KEY.CHQ_NO.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) DDRPAID.KEY.CHQ_NO = "0" + DDRPAID.KEY.CHQ_NO;
        CEP.TRC(SCCGWA, DDRPAID.KEY.AC);
        CEP.TRC(SCCGWA, DDRPAID.KEY.CHQ_NO);
        T000_READ_DDTPAID();
        if (pgmRtn) return;
        WS_TOT_AMT = WS_TOT_AMT + DDRPAID.AMT;
    }
    public void B090_30_SET_FEE_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
            BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '0';
            BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = DDCSLCHQ.AC_NO;
        }
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DC")) {
            BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '4';
            BPCFFTXI.TX_DATA.CARD_PSBK_NO = DDCSLCHQ.AC_NO;
        }
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = DDCSLCHQ.AC_NO;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = "156";
        BPCFFTXI.TX_DATA.CCY_TYPE = '1';
        BPCFFTXI.TX_DATA.SVR_CD = SCCGWA.COMM_AREA.SERV_CODE;
        BPCFFTXI.TX_DATA.CI_NO = CICACCU.DATA.CI_NO;
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY);
        S000_CALL_BPZFFTXI();
        if (pgmRtn) return;
    }
    public void B090_50_CALL_FEE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTCALF);
        BPCTCALF.INPUT_AREA.FUNC_CODE = 'C';
        BPCTCALF.INPUT_AREA.TX_AC = DDCSLCHQ.AC_NO;
        BPCTCALF.INPUT_AREA.TX_CCY = "156";
        BPCTCALF.INPUT_AREA.TX_AMT = DDCSLCHQ.CHQ_AMT;
        if ("1".trim().length() == 0) BPCTCALF.INPUT_AREA.TX_CNT = 0;
        else BPCTCALF.INPUT_AREA.TX_CNT = Short.parseShort("1");
        BPCTCALF.INPUT_AREA.TX_CI = CICACCU.DATA.CI_NO;
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.TX_AMT);
        BPCTCALF.INPUT_AREA.BVF_CODE = DDCSLCHQ.BV_CODE;
        BPCTCALF.INPUT_AREA.PROD_CODE = DDRMST.PROD_CODE;
        BPCTCALF.INPUT_AREA.PROD_CODE1 = DDRMST.PROD_CODE;
        BPCTCALF.INPUT_AREA.CPN_ID = CPN_SVR_LCHQ_PROC;
        S000_CALL_BPZFCALF();
        if (pgmRtn) return;
    }
    public void B060_REGISTER_PROC() throws IOException,SQLException,Exception {
    }
    public void B070_STOP_CHQ_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCHQ);
        DDRCHQ.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRCHQ.KEY.CHQ_TYP = DDCSLCHQ.CHQ_TYP;
        DDRCHQ.KEY.STR_CHQ_NO = DDCSLCHQ.STR_CHQ_NO;
        DDRCHQ.KEY.END_CHQ_NO = DDCSLCHQ.STR_CHQ_NO;
        if (DDCSLCHQ.STR_CHQ_NO.trim().length() == 0) WS_CHQ_NO = 0;
        else WS_CHQ_NO = Long.parseLong(DDCSLCHQ.STR_CHQ_NO);
        if (DDCSLCHQ.STR_CHQ_NO.trim().length() == 0) WS_SLCHQ_STR_CHQ_NO = 0;
        else WS_SLCHQ_STR_CHQ_NO = Long.parseLong(DDCSLCHQ.STR_CHQ_NO);
        if (DDCSLCHQ.STR_CHQ_NO.trim().length() == 0) WS_SLCHQ_END_CHQ_NO = 0;
        else WS_SLCHQ_END_CHQ_NO = Long.parseLong(DDCSLCHQ.STR_CHQ_NO);
        T000_STARTBR_DDTCHQ();
        if (pgmRtn) return;
        T000_READNEXT_DDTCHQ();
        if (pgmRtn) return;
        if (WS_CHQ_FLG == 'N') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQB_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDRCHQ.CRT_DATE);
        CEP.TRC(SCCGWA, DDCSLCHQ.CHQ_DATE);
        if (DDCSLCHQ.CHQ_DATE < DDRCHQ.CRT_DATE) {
            CEP.TRC(SCCGWA, "CHQ-DATE-ERR");
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQ_DATE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        while (WS_CHQ_FLG != 'N' 
            && WS_CHQ_NO <= WS_SLCHQ_END_CHQ_NO) {
            if (DDRCHQ.KEY.STR_CHQ_NO.trim().length() == 0) WS_CHQ_STR_CHQ_NO = 0;
            else WS_CHQ_STR_CHQ_NO = Long.parseLong(DDRCHQ.KEY.STR_CHQ_NO);
            if (DDRCHQ.KEY.END_CHQ_NO.trim().length() == 0) WS_CHQ_END_CHQ_NO = 0;
            else WS_CHQ_END_CHQ_NO = Long.parseLong(DDRCHQ.KEY.END_CHQ_NO);
            if ((WS_CHQ_NO >= WS_CHQ_STR_CHQ_NO) 
                && (WS_CHQ_NO <= WS_CHQ_END_CHQ_NO)) {
                WS_STR_POS = (short) (WS_CHQ_NO - WS_CHQ_STR_CHQ_NO + 1);
                CEP.TRC(SCCGWA, WS_STR_POS);
                if (WS_SLCHQ_END_CHQ_NO < WS_CHQ_END_CHQ_NO) {
                    WS_CHQ_CNT = (int) (WS_SLCHQ_END_CHQ_NO - WS_CHQ_NO + 1);
                } else {
                    WS_CHQ_CNT = (int) (WS_CHQ_END_CHQ_NO - WS_CHQ_NO + 1);
                }
                CEP.TRC(SCCGWA, WS_CHQ_CNT);
                CEP.TRC(SCCGWA, DDCSLCHQ.LOST_TYPE);
                WS_CHQ_STS = DDRCHQ.CHQ_STS;
                WS_CHQ_ULOST = ' ';
                for (WS_CNT = 1; WS_CNT <= WS_CHQ_CNT; WS_CNT += 1) {
                    WS_CUR_POS = (short) (WS_STR_POS + WS_CNT - 1);
                    CEP.TRC(SCCGWA, WS_CUR_POS);
                    if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
                    JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
                    for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
                    WS_CHQ_ULOST = DDRCHQ.CHQ_STS.substring(WS_CUR_POS - 1, WS_CUR_POS + 1 - 1).charAt(0);
                    if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
                    JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
                    for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
                    if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
                    JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
                    for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
                    if (!DDRCHQ.CHQ_STS.substring(WS_CUR_POS - 1, WS_CUR_POS + 1 - 1).equalsIgnoreCase("1") 
                        && !DDRCHQ.CHQ_STS.substring(WS_CUR_POS - 1, WS_CUR_POS + 1 - 1).equalsIgnoreCase("3")) {
                        if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
                        JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
                        for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
                        CEP.TRC(SCCGWA, DDRCHQ.CHQ_STS.substring(WS_CUR_POS - 1, WS_CUR_POS + 1 - 1));
                        CEP.TRC(SCCGWA, DDRCHQ.CHQ_STS);
                        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQ_STS_INVALID;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    } else {
                        if (DDCSLCHQ.LOST_TYPE == '3') {
                            CEP.TRC(SCCGWA, "IF");
                            if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
                            JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
                            for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
                            if (DDRCHQ.CHQ_STS.substring(WS_CUR_POS - 1, WS_CUR_POS + 1 - 1).equalsIgnoreCase("3")) {
                                CEP.TRC(SCCGWA, "CHQ-CHQ-STS(WS-CUR-POS:1) = ");
