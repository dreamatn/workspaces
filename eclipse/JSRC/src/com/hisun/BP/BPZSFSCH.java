package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSFSCH {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_FEE_CODE = "FII01";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String K_OUTPUT_FMT_X = "BPX01";
    String K_OUTPUT_FMT_9 = "BP054";
    int K_MAX_COL = 99;
    int K_MAX_ROW = 50;
    int K_COL_CNT = 20;
    String K_HIS_REMARK = "MAINTAIN FEE SCHEDULE";
    String K_HIS_COPYBOOK = "BPCHFSCH";
    String K_CTRT_TYPE_FEES = "FEES";
    String WS_ERR_MSG = " ";
    short WS_IDX = 0;
    short WS_CNT = 0;
    short WS_CNT1 = 0;
    short WS_SCHD_COUNT = 0;
    short WS_DIARY_CNT = 0;
    int WS_OLD_START_DATE = 0;
    int WS_PRE_SETTLE_DATE = 0;
    int WS_START_DATE = 0;
    int WS_END_DATE = 0;
    BPZSFSCH_WS_LIST_DATA WS_LIST_DATA = new BPZSFSCH_WS_LIST_DATA();
    double WS_ACCR_TOT_AMT = 0;
    double WS_EVENT_TOT_AMT = 0;
    double WS_EX_RATE = 0;
    int WS_SCHD_LAST_DATE = 0;
    char WS_FOUND_FLAG = ' ';
    char WS_BRW_FLAG = ' ';
    char WS_SAME_FLAG = ' ';
    char WS_UPD_FLAG = ' ';
    char WS_FOR_MA_FLAG = ' ';
    char WS_FINAL_FLAG = ' ';
    char WS_BRW_FOUND_FLAG = ' ';
    String WS_FEE_CCY_JUDGE = " ";
    String WS_CHG_CCY_JUDGE = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRFSCH BPRFSCH = new BPRFSCH();
    BPCHFSCH BPCHFSCH = new BPCHFSCH();
    BPCHFSCH BPCHFSCO = new BPCHFSCH();
    BPRFCPH BPRFCPH = new BPRFCPH();
    BPCRFSCH BPCRFSCH = new BPCRFSCH();
    BPCRCPHM BPCRCPHM = new BPCRCPHM();
    BPCFSCH1 BPCFSCH1 = new BPCFSCH1();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCUCNGM BPCUCNGM = new BPCUCNGM();
    BPCPQBCH BPCPQBCH = new BPCPQBCH();
    BPCCGCT BPCCGCT = new BPCCGCT();
    BPCFSCHC BPCFSCHC = new BPCFSCHC();
    BPCRSCHD BPCRSCHD = new BPCRSCHD();
    BPRFSCHD BPRFSCHD = new BPRFSCHD();
    BPRPACCR BPRPACCR = new BPRPACCR();
    BPCRACCR BPCRACCR = new BPCRACCR();
    BPCPEBAS BPCPEBAS = new BPCPEBAS();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCUMENT BPCUMENT = new BPCUMENT();
    BPCUFEEC BPCUFEEC = new BPCUFEEC();
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    CICCUST CICCUST = new CICCUST();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCUGLM BPCUGLM = new BPCUGLM();
    BPCOFBAS BPCOUBAS = new BPCOFBAS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCSFSCH BPCSFSCH;
    public void MP(SCCGWA SCCGWA, BPCSFSCH BPCSFSCH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSFSCH = BPCSFSCH;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSFSCH return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRFSCH);
        IBS.init(SCCGWA, BPRFCPH);
        IBS.init(SCCGWA, BPCRFSCH);
        IBS.init(SCCGWA, BPCRCPHM);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCSFSCH.FUNC == 'Q') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSFSCH.FUNC == 'A') {
            if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
                B002_CHECK_INPUT_CN();
                if (pgmRtn) return;
            } else {
                B002_CHECK_INPUT();
                if (pgmRtn) return;
            }
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
            B021_GEN_DETAIL_SCH();
            if (pgmRtn) return;
            B080_HISTORY_PROCESS();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCSFSCH.INF.REL_COL_NO);
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSFSCH.FUNC == 'U') {
            if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
                B002_CHECK_INPUT_CN();
                if (pgmRtn) return;
                B030_MODIFY_PROCESS_CN();
                if (pgmRtn) return;
            } else {
                B002_CHECK_INPUT();
                if (pgmRtn) return;
                B030_MODIFY_PROCESS();
                if (pgmRtn) return;
            }
            B031_REGEN_DETAIL_SCH();
            if (pgmRtn) return;
            B080_HISTORY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSFSCH.FUNC == 'D') {
            B040_DELETE_PROCESS();
            if (pgmRtn) return;
            B080_HISTORY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSFSCH.FUNC == '1'
            || BPCSFSCH.FUNC == '2'
            || BPCSFSCH.FUNC == '3') {
            B050_BROWSER_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B002_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOUBAS);
        BPCOUBAS.KEY.FEE_CODE = BPCSFSCH.INF.FEE_TYPE;
        BPCOUBAS.FUNC = 'I';
        S000_CALL_BPZFUBAS();
        if (pgmRtn) return;
        if (BPCSFSCH.INF.TXN_CCY.trim().length() > 0 
            && !BPCSFSCH.INF.TXN_CCY.equalsIgnoreCase(BPCSFSCH.INF.FEE_CCY)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TXNCCY_FEECCY_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "CHECK START");
        if (BPCSFSCH.INF.CHG_CCY_REAL.trim().length() > 0 
            && BPCSFSCH.INF.CHARGE_CCY.trim().length() > 0 
            && !BPCSFSCH.INF.CHG_CCY_REAL.equalsIgnoreCase(BPCSFSCH.INF.CHARGE_CCY)) {
            CEP.TRC(SCCGWA, "CHECK IN   ");
            CEP.TRC(SCCGWA, BPCSFSCH.INF.CHG_CCY_REAL);
            CEP.TRC(SCCGWA, BPCSFSCH.INF.CHARGE_CCY);
            WS_FEE_CCY_JUDGE = BPCSFSCH.INF.CHG_CCY_REAL;
            WS_CHG_CCY_JUDGE = BPCSFSCH.INF.CHARGE_CCY;
            if ((!WS_FEE_CCY_JUDGE.equalsIgnoreCase("344") 
                && !WS_FEE_CCY_JUDGE.equalsIgnoreCase("446")) 
                || (!WS_CHG_CCY_JUDGE.equalsIgnoreCase("344") 
                && !WS_CHG_CCY_JUDGE.equalsIgnoreCase("446"))) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EXCHANGE_CCY_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, "CHECK END  ");
    }
    public void B002_CHECK_INPUT_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOUBAS);
        BPCOUBAS.KEY.FEE_CODE = BPCSFSCH.INF.FEE_TYPE;
        BPCOUBAS.FUNC = 'I';
        if (BPCSFSCH.INF.TXN_CCY.trim().length() > 0 
            && !BPCSFSCH.INF.TXN_CCY.equalsIgnoreCase(BPCSFSCH.INF.FEE_CCY)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TXNCCY_FEECCY_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFSCH);
        BPRFSCH.KEY.CTRT_NO = BPCSFSCH.KEY.CTRT_NO;
        BPCRFSCH.INFO.FUNC = 'Q';
        S000_CALL_BPZRFSCH();
        if (pgmRtn) return;
        if (BPCRFSCH.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FSCH_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B010_01_GET_FCPH();
        if (pgmRtn) return;
        B010_02_GET_REL_CTRT_INFO();
        if (pgmRtn) return;
    }
    public void B010_01_GET_FCPH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFCPH);
        BPRFCPH.KEY.CTRT_NO = BPCSFSCH.KEY.CTRT_NO;
        BPRFCPH.KEY.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        if (BPRFSCH.START_DATE >= SCCGWA.COMM_AREA.AC_DATE) {
            BPRFCPH.KEY.VALUE_DATE = BPRFSCH.START_DATE;
        }
        BPCRCPHM.INFO.FUNC = 'B';
        BPCRCPHM.INFO.OPT = '1';
        S000_CALL_BPZRCPHM();
        if (pgmRtn) return;
        if (BPCRCPHM.RETURN_INFO == 'F') {
            if (BPCSFSCH.FUNC == 'Q' 
                || BPCSFSCH.FUNC == 'D') {
                CEP.TRC(SCCGWA, "GET FCPH SUCCESSFUL");
                BPCSFSCH.INF.MULTI = BPRFCPH.MULTI;
                BPCSFSCH.INF.INT_BAS = BPRFCPH.INT_BAS;
                BPCSFSCH.INF.DATE[1-1].REF_AMT = BPRFCPH.UP_AMT_1;
                BPCSFSCH.INF.DATE[1-1].REF_PCT = BPRFCPH.UP_PCT_1;
                BPCSFSCH.INF.DATE[1-1].AGG_MTH = BPRFCPH.AGG_MTH_1;
                BPCSFSCH.INF.DATE[1-1].FEE_AMT = BPRFCPH.FEE_AMT_1;
                BPCSFSCH.INF.DATE[1-1].FEE_RATE = BPRFCPH.FEE_RATE_1;
                BPCSFSCH.INF.DATE[2-1].REF_AMT = BPRFCPH.UP_AMT_2;
                BPCSFSCH.INF.DATE[2-1].REF_PCT = BPRFCPH.UP_PCT_2;
                BPCSFSCH.INF.DATE[2-1].AGG_MTH = BPRFCPH.AGG_MTH_2;
                BPCSFSCH.INF.DATE[2-1].FEE_AMT = BPRFCPH.FEE_AMT_2;
                BPCSFSCH.INF.DATE[2-1].FEE_RATE = BPRFCPH.FEE_RATE_2;
                BPCSFSCH.INF.DATE[3-1].REF_AMT = BPRFCPH.UP_AMT_3;
                BPCSFSCH.INF.DATE[3-1].REF_PCT = BPRFCPH.UP_PCT_3;
                BPCSFSCH.INF.DATE[3-1].AGG_MTH = BPRFCPH.AGG_MTH_3;
                BPCSFSCH.INF.DATE[3-1].FEE_AMT = BPRFCPH.FEE_AMT_3;
                BPCSFSCH.INF.DATE[3-1].FEE_RATE = BPRFCPH.FEE_RATE_3;
                BPCSFSCH.INF.DATE[4-1].REF_AMT = BPRFCPH.UP_AMT_4;
                BPCSFSCH.INF.DATE[4-1].REF_PCT = BPRFCPH.UP_PCT_4;
                BPCSFSCH.INF.DATE[4-1].AGG_MTH = BPRFCPH.AGG_MTH_4;
                BPCSFSCH.INF.DATE[4-1].FEE_AMT = BPRFCPH.FEE_AMT_4;
                BPCSFSCH.INF.DATE[4-1].FEE_RATE = BPRFCPH.FEE_RATE_4;
                BPCSFSCH.INF.DATE[5-1].REF_AMT = BPRFCPH.UP_AMT_5;
                BPCSFSCH.INF.DATE[5-1].REF_PCT = BPRFCPH.UP_PCT_5;
                BPCSFSCH.INF.DATE[5-1].AGG_MTH = BPRFCPH.AGG_MTH_5;
                BPCSFSCH.INF.DATE[5-1].FEE_AMT = BPRFCPH.FEE_AMT_5;
                BPCSFSCH.INF.DATE[5-1].FEE_RATE = BPRFCPH.FEE_RATE_5;
            }
        }
    }
    public void B010_02_GET_REL_CTRT_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRFSCH.REL_CTRT_SRC);
        if (BPRFSCH.REL_CTRT_SRC == '0') {
            R100_GET_LOAN_CTR();
            if (pgmRtn) return;
        } else if (BPRFSCH.REL_CTRT_SRC == '1') {
        } else if (BPRFSCH.REL_CTRT_SRC == '2') {
        } else {
        }
    }
    public void R100_GET_LOAN_CTR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRFSCH.REL_CTRT_NO);
    }
    public void B020_CREATE_PROCESS() throws IOException,SQLException,Exception {
        B020_01_GET_CTRT_NO();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSFSCH.KEY.CTRT_NO);
        IBS.init(SCCGWA, BPRFSCH);
        R000_TRANS_DATA();
        if (pgmRtn) return;
        R001_TRANS_HIS_NEW_DATA();
        if (pgmRtn) return;
        BPCRFSCH.INFO.FUNC = 'C';
        S000_CALL_BPZRFSCH();
        if (pgmRtn) return;
        if (BPCRFSCH.RETURN_INFO == 'D') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FSCH_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPRFCPH);
        R100_TRANS_DATA_FCPH();
        if (pgmRtn) return;
        BPCRCPHM.INFO.FUNC = 'C';
        S000_CALL_BPZRCPHM();
        if (pgmRtn) return;
        if (BPCRCPHM.RETURN_INFO == 'D') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FCPH_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_01_GET_CTRT_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQBCH);
        BPCPQBCH.ACCT = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQBCH();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCCGCT);
        BPCCGCT.BR = "737";
        BPCCGCT.PRDT_CODE = BPCSFSCH.INF.PRD_TYPE;
        if (BPCCGCT.SPEC_PREFIX == null) BPCCGCT.SPEC_PREFIX = "";
        JIBS_tmp_int = BPCCGCT.SPEC_PREFIX.length();
        for (int i=0;i<3-JIBS_tmp_int;i++) BPCCGCT.SPEC_PREFIX += " ";
        BPCCGCT.SPEC_PREFIX = "53" + BPCCGCT.SPEC_PREFIX.substring(2);
        CEP.TRC(SCCGWA, BPCCGCT.BR);
        CEP.TRC(SCCGWA, BPCCGCT.PRDT_CODE);
        CEP.TRC(SCCGWA, BPCCGCT.SPEC_PREFIX);
        S000_CALL_BPZGCTNO();
        if (pgmRtn) return;
        BPCSFSCH.KEY.CTRT_NO = BPCCGCT.CTNO;
        CEP.TRC(SCCGWA, BPCSFSCH.KEY.CTRT_NO);
    }
    public void B021_GEN_DETAIL_SCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFSCHC);
        BPCFSCHC.CTRT_NO = BPCSFSCH.KEY.CTRT_NO;
        BPCFSCHC.START_DATE = BPCSFSCH.INF.START_DATE;
        BPCFSCHC.MATURITY_DATE = BPCSFSCH.INF.MATURITY_DATE;
        BPCFSCHC.SETTLE_FREQ = BPCSFSCH.INF.SETTLE_FREQ;
        BPCFSCHC.FREQ_COUNT = BPCSFSCH.INF.FREQ_COUNT;
        BPCFSCHC.SETTLE_DATE = BPCSFSCH.INF.FIRST_CHG_DATE;
        BPCFSCHC.HOLI_OVER = BPCSFSCH.INF.HOLI_OVER;
        BPCFSCHC.CAL_CODE1 = BPCSFSCH.INF.CAL_CODE1;
        BPCFSCHC.HOLI_METHOD = BPCSFSCH.INF.HOLI_METHOD;
        BPCFSCHC.CAL_CODE2 = BPCSFSCH.INF.CAL_CODE2;
        S000_CALL_BPZFSCHC();
        if (pgmRtn) return;
    }
    public void B030_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        WS_SAME_FLAG = 'Y';
        IBS.init(SCCGWA, BPRFSCH);
        BPRFSCH.KEY.CTRT_NO = BPCSFSCH.KEY.CTRT_NO;
        BPCRFSCH.INFO.FUNC = 'R';
        S000_CALL_BPZRFSCH();
        if (pgmRtn) return;
        if (BPCRFSCH.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FSCH_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_OLD_START_DATE = BPRFSCH.START_DATE;
        if (BPRFSCH.START_DATE > SCCGWA.COMM_AREA.AC_DATE 
            && BPCSFSCH.INF.START_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_START_DATE_LESS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPRFSCH.START_DATE <= SCCGWA.COMM_AREA.AC_DATE 
            && BPRFSCH.MATURITY_DATE > BPCSFSCH.INF.MATURITY_DATE) {
            CEP.TRC(SCCGWA, "CHECK MATURITY DATE");
            WS_SCHD_LAST_DATE = 0;
            IBS.init(SCCGWA, BPRFSCHD);
            IBS.init(SCCGWA, BPCRSCHD);
            BPRFSCHD.KEY.CTRT_NO = BPCSFSCH.KEY.CTRT_NO;
            BPCRSCHD.INFO.FUNC = 'B';
            BPCRSCHD.INFO.OPT = '3';
            S000_CALL_BPZRSCHD();
            if (pgmRtn) return;
            BPCRSCHD.INFO.FUNC = 'N';
            S000_CALL_BPZRSCHD();
            if (pgmRtn) return;
            while (BPCRSCHD.RETURN_INFO != 'N') {
                WS_SCHD_LAST_DATE = BPRFSCHD.KEY.SETTLE_DATE;
                BPCRSCHD.INFO.FUNC = 'N';
                S000_CALL_BPZRSCHD();
                if (pgmRtn) return;
            }
            BPCRSCHD.INFO.FUNC = 'E';
            S000_CALL_BPZRSCHD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_SCHD_LAST_DATE);
            if (WS_SCHD_LAST_DATE != 0 
                && BPCSFSCH.INF.MATURITY_DATE < WS_SCHD_LAST_DATE) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MAT_LESS_SCHD_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        R001_TRANS_HIS_OLD_DATA();
        if (pgmRtn) return;
        if (!BPCSFSCH.INF.PRD_TYPE.equalsIgnoreCase(BPRFSCH.PRD_TYPE)) {
            WS_SAME_FLAG = 'N';
        }
        R000_TRANS_DATA();
        if (pgmRtn) return;
        R001_TRANS_HIS_NEW_DATA();
        if (pgmRtn) return;
        BPCRFSCH.INFO.FUNC = 'U';
        S000_CALL_BPZRFSCH();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRFCPH);
        BPRFCPH.KEY.CTRT_NO = BPCSFSCH.KEY.CTRT_NO;
        BPRFCPH.KEY.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        if (BPRFSCH.START_DATE >= SCCGWA.COMM_AREA.AC_DATE) {
            BPRFCPH.KEY.VALUE_DATE = BPRFSCH.START_DATE;
        }
        BPCRCPHM.INFO.FUNC = 'B';
        BPCRCPHM.INFO.OPT = '1';
        S000_CALL_BPZRCPHM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCRCPHM.RETURN_INFO);
        CEP.TRC(SCCGWA, BPRFCPH.KEY.CTRT_NO);
        CEP.TRC(SCCGWA, BPRFCPH.KEY.VALUE_DATE);
        if (BPCRCPHM.RETURN_INFO == 'N') {
            IBS.init(SCCGWA, BPRFCPH);
            R100_TRANS_DATA_FCPH();
            if (pgmRtn) return;
            R001_TRANS_HIS_OLD_DATA_FCPH();
            if (pgmRtn) return;
            BPCRCPHM.INFO.FUNC = 'C';
            S000_CALL_BPZRCPHM();
            if (pgmRtn) return;
            if (BPCRCPHM.RETURN_INFO == 'D') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FCPH_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCRCPHM.RETURN_INFO == 'F') {
            BPCRCPHM.INFO.FUNC = 'R';
            S000_CALL_BPZRCPHM();
            if (pgmRtn) return;
            if (BPCRCPHM.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FCPH_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            R001_TRANS_HIS_OLD_DATA_FCPH();
            if (pgmRtn) return;
            R100_TRANS_DATA_FCPH();
            if (pgmRtn) return;
            BPCRCPHM.INFO.FUNC = 'U';
            S000_CALL_BPZRCPHM();
            if (pgmRtn) return;
        }
        B010_02_GET_REL_CTRT_INFO();
        if (pgmRtn) return;
    }
    public void B030_MODIFY_PROCESS_CN() throws IOException,SQLException,Exception {
        WS_SAME_FLAG = 'Y';
        IBS.init(SCCGWA, BPRFSCH);
        BPRFSCH.KEY.CTRT_NO = BPCSFSCH.KEY.CTRT_NO;
        BPCRFSCH.INFO.FUNC = 'R';
        S000_CALL_BPZRFSCH();
        if (pgmRtn) return;
        if (BPCRFSCH.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FSCH_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_OLD_START_DATE = BPRFSCH.START_DATE;
        if (BPRFSCH.START_DATE > SCCGWA.COMM_AREA.AC_DATE 
            && BPCSFSCH.INF.START_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_START_DATE_LESS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPRFSCH.START_DATE);
        CEP.TRC(SCCGWA, BPCSFSCH.INF.START_DATE);
        CEP.TRC(SCCGWA, BPRFSCH.FEE_STATUS);
        if (BPRFSCH.START_DATE > SCCGWA.COMM_AREA.AC_DATE 
            && BPCSFSCH.INF.START_DATE == SCCGWA.COMM_AREA.AC_DATE 
            && BPRFSCH.FEE_STATUS == '0') {
            CEP.TRC(SCCGWA, "CHANGE STATUS");
            BPCSFSCH.INF.FEE_STATUS = '1';
        }
        if (BPRFSCH.START_DATE <= SCCGWA.COMM_AREA.AC_DATE 
            && BPRFSCH.MATURITY_DATE > BPCSFSCH.INF.MATURITY_DATE) {
            CEP.TRC(SCCGWA, "CHECK MATURITY DATE");
            WS_SCHD_LAST_DATE = 0;
            IBS.init(SCCGWA, BPRFSCHD);
            IBS.init(SCCGWA, BPCRSCHD);
            BPRFSCHD.KEY.CTRT_NO = BPCSFSCH.KEY.CTRT_NO;
            BPCRSCHD.INFO.FUNC = 'B';
            BPCRSCHD.INFO.OPT = '3';
            S000_CALL_BPZRSCHD();
            if (pgmRtn) return;
            BPCRSCHD.INFO.FUNC = 'N';
            S000_CALL_BPZRSCHD();
            if (pgmRtn) return;
            while (BPCRSCHD.RETURN_INFO != 'N') {
                WS_SCHD_LAST_DATE = BPRFSCHD.KEY.SETTLE_DATE;
                BPCRSCHD.INFO.FUNC = 'N';
                S000_CALL_BPZRSCHD();
                if (pgmRtn) return;
            }
            BPCRSCHD.INFO.FUNC = 'E';
            S000_CALL_BPZRSCHD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_SCHD_LAST_DATE);
            if (WS_SCHD_LAST_DATE != 0 
                && BPCSFSCH.INF.MATURITY_DATE < WS_SCHD_LAST_DATE) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MAT_LESS_SCHD_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        R001_TRANS_HIS_OLD_DATA();
        if (pgmRtn) return;
        if (!BPCSFSCH.INF.PRD_TYPE.equalsIgnoreCase(BPRFSCH.PRD_TYPE)) {
            WS_SAME_FLAG = 'N';
        }
        R000_TRANS_DATA();
        if (pgmRtn) return;
        R001_TRANS_HIS_NEW_DATA();
        if (pgmRtn) return;
        BPCRFSCH.INFO.FUNC = 'U';
        S000_CALL_BPZRFSCH();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRFCPH);
        BPRFCPH.KEY.CTRT_NO = BPCSFSCH.KEY.CTRT_NO;
        BPRFCPH.KEY.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        if (BPRFSCH.START_DATE >= SCCGWA.COMM_AREA.AC_DATE) {
            BPRFCPH.KEY.VALUE_DATE = BPRFSCH.START_DATE;
        }
        BPCRCPHM.INFO.FUNC = 'B';
        BPCRCPHM.INFO.OPT = '1';
        S000_CALL_BPZRCPHM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCRCPHM.RETURN_INFO);
        CEP.TRC(SCCGWA, BPRFCPH.KEY.CTRT_NO);
        CEP.TRC(SCCGWA, BPRFCPH.KEY.VALUE_DATE);
        if (BPCRCPHM.RETURN_INFO == 'N') {
            IBS.init(SCCGWA, BPRFCPH);
            R100_TRANS_DATA_FCPH();
            if (pgmRtn) return;
            R001_TRANS_HIS_OLD_DATA_FCPH();
            if (pgmRtn) return;
            BPCRCPHM.INFO.FUNC = 'C';
            S000_CALL_BPZRCPHM();
            if (pgmRtn) return;
            if (BPCRCPHM.RETURN_INFO == 'D') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FCPH_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCRCPHM.RETURN_INFO == 'F') {
            BPCRCPHM.INFO.FUNC = 'R';
            S000_CALL_BPZRCPHM();
            if (pgmRtn) return;
            if (BPCRCPHM.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FCPH_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            R001_TRANS_HIS_OLD_DATA_FCPH();
            if (pgmRtn) return;
            R100_TRANS_DATA_FCPH();
            if (pgmRtn) return;
            BPCRCPHM.INFO.FUNC = 'U';
            S000_CALL_BPZRCPHM();
            if (pgmRtn) return;
        }
        B010_02_GET_REL_CTRT_INFO();
        if (pgmRtn) return;
    }
    public void B030_01_GET_PACCR() throws IOException,SQLException,Exception {
        WS_ACCR_TOT_AMT = 0;
        IBS.init(SCCGWA, BPRPACCR);
        IBS.init(SCCGWA, BPCRACCR);
        BPRPACCR.KEY.CTRT_NO = BPCSFSCH.KEY.CTRT_NO;
        BPCRFSCH.INFO.FUNC = 'Q';
        S000_CALL_BPZRACCR();
        if (pgmRtn) return;
        if (BPCRACCR.RETURN_INFO == 'F') {
            WS_ACCR_TOT_AMT = BPRPACCR.ACCRUAL_AMT_TOTAL;
        }
        CEP.TRC(SCCGWA, WS_ACCR_TOT_AMT);
    }
    public void B030_02_CHANGE_BR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPEBAS);
        S000_CALL_BPZPEBAS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = K_CTRT_TYPE_FEES;
        BPCPOEWA.DATA.PROD_CODE = BPCSFSCH.INF.PRD_TYPE;
        BPCPOEWA.DATA.EVENT_CODE = "BC";
        CEP.TRC(SCCGWA, BPRFSCH.PAY_IND);
        if (BPRFSCH.PAY_IND == 'R') {
            BPCPOEWA.DATA.AMT_INFO[17-1].AMT = WS_ACCR_TOT_AMT;
        } else {
            BPCPOEWA.DATA.AMT_INFO[18-1].AMT = WS_ACCR_TOT_AMT;
        }
        BPCPOEWA.DATA.BR_OLD = BPRFSCH.BOOK_CENTRE;
        BPCPOEWA.DATA.BR_NEW = BPCSFSCH.INF.BOOK_CENTRE;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCSFSCH.INF.CHARGE_CCY;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.AC_NO = BPCSFSCH.KEY.CTRT_NO;
        BPCPOEWA.DATA.CI_NO = BPCSFSCH.INF.CI_NO;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.CNTR_TYPE);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.PROD_CODE);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.EVENT_CODE);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[17-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[18-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.BR_OLD);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.BR_NEW);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.CCY_INFO[1-1].CCY);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.VALUE_DATE);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AC_NO);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.CI_NO);
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void B031_REGEN_DETAIL_SCH() throws IOException,SQLException,Exception {
        WS_UPD_FLAG = 'N';
        if (BPCSFSCH.INF.START_DATE > SCCGWA.COMM_AREA.AC_DATE) {
            CEP.TRC(SCCGWA, BPRFSCH.START_DATE);
            CEP.TRC(SCCGWA, BPRFSCH.MATURITY_DATE);
            CEP.TRC(SCCGWA, BPRFSCH.SETTLE_FREQ);
            CEP.TRC(SCCGWA, BPRFSCH.FREQ_COUNT);
            CEP.TRC(SCCGWA, BPRFSCH.FIRST_CHG_DATE);
            CEP.TRC(SCCGWA, BPCHFSCO.START_DATE);
            CEP.TRC(SCCGWA, BPCHFSCO.MATURITY_DATE);
            CEP.TRC(SCCGWA, BPCHFSCO.SETTLE_FREQ);
            CEP.TRC(SCCGWA, BPCHFSCO.FREQ_COUNT);
            CEP.TRC(SCCGWA, BPCHFSCO.FIRST_CHG_DATE);
            if (BPRFSCH.START_DATE != BPCHFSCO.START_DATE 
                || BPRFSCH.MATURITY_DATE != BPCHFSCO.MATURITY_DATE 
                || BPRFSCH.SETTLE_FREQ != BPCHFSCO.SETTLE_FREQ 
                || BPRFSCH.FREQ_COUNT != BPCHFSCO.FREQ_COUNT 
                || BPRFSCH.FIRST_CHG_DATE != BPCHFSCO.FIRST_CHG_DATE) {
                B031_01_DEL_OLD_SCHD();
                if (pgmRtn) return;
                B031_02_REGEN_DETAIL_SCH();
                if (pgmRtn) return;
                WS_UPD_FLAG = 'Y';
            }
        }
    }
    public void B031_01_DEL_OLD_SCHD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFSCHD);
        IBS.init(SCCGWA, BPCRSCHD);
        BPRFSCHD.KEY.CTRT_NO = BPCSFSCH.KEY.CTRT_NO;
        BPCRSCHD.INFO.FUNC = 'B';
        BPCRSCHD.INFO.OPT = '3';
        S000_CALL_BPZRSCHD();
        if (pgmRtn) return;
        BPCRSCHD.INFO.FUNC = 'N';
        S000_CALL_BPZRSCHD();
        if (pgmRtn) return;
        while (BPCRSCHD.RETURN_INFO != 'N') {
            BPCRSCHD.INFO.FUNC = 'R';
            S000_CALL_BPZRSCHD();
            if (pgmRtn) return;
            if (BPCRSCHD.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FCPH_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            BPCRSCHD.INFO.FUNC = 'D';
            S000_CALL_BPZRSCHD();
            if (pgmRtn) return;
            BPCRSCHD.INFO.FUNC = 'N';
            S000_CALL_BPZRSCHD();
            if (pgmRtn) return;
        }
        BPCRSCHD.INFO.FUNC = 'E';
        S000_CALL_BPZRSCHD();
        if (pgmRtn) return;
    }
    public void B031_02_REGEN_DETAIL_SCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFSCHC);
        BPCFSCHC.CTRT_NO = BPCSFSCH.KEY.CTRT_NO;
        BPCFSCHC.START_DATE = BPCSFSCH.INF.START_DATE;
        BPCFSCHC.MATURITY_DATE = BPCSFSCH.INF.MATURITY_DATE;
        BPCFSCHC.SETTLE_FREQ = BPCSFSCH.INF.SETTLE_FREQ;
        BPCFSCHC.FREQ_COUNT = BPCSFSCH.INF.FREQ_COUNT;
        BPCFSCHC.SETTLE_DATE = BPCSFSCH.INF.FIRST_CHG_DATE;
        BPCFSCHC.HOLI_OVER = BPCSFSCH.INF.HOLI_OVER;
        BPCFSCHC.CAL_CODE1 = BPCSFSCH.INF.CAL_CODE1;
        BPCFSCHC.HOLI_METHOD = BPCSFSCH.INF.HOLI_METHOD;
        BPCFSCHC.CAL_CODE2 = BPCSFSCH.INF.CAL_CODE2;
        S000_CALL_BPZFSCHC();
        if (pgmRtn) return;
    }
    public void B040_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFSCH);
        BPRFSCH.KEY.CTRT_NO = BPCSFSCH.KEY.CTRT_NO;
        BPCRFSCH.INFO.FUNC = 'R';
        S000_CALL_BPZRFSCH();
        if (pgmRtn) return;
        if (BPCRFSCH.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FSCH_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPRFSCH.FEE_STATUS == '3') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FSCH_DELETED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_FOUND_FLAG = 'N';
        IBS.init(SCCGWA, BPRFSCHD);
        IBS.init(SCCGWA, BPCRSCHD);
        BPRFSCHD.KEY.CTRT_NO = BPCSFSCH.KEY.CTRT_NO;
        BPRFSCHD.STS = 'C';
        BPCRSCHD.INFO.FUNC = 'B';
        BPCRSCHD.INFO.OPT = '2';
        S000_CALL_BPZRSCHD();
        if (pgmRtn) return;
        BPCRSCHD.INFO.FUNC = 'N';
        S000_CALL_BPZRSCHD();
        if (pgmRtn) return;
        while (BPCRSCHD.RETURN_INFO != 'N' 
            && WS_FOUND_FLAG != 'Y') {
            if (BPCRSCHD.RETURN_INFO == 'F') {
                WS_FOUND_FLAG = 'Y';
            }
            BPCRSCHD.INFO.FUNC = 'N';
            S000_CALL_BPZRSCHD();
            if (pgmRtn) return;
        }
        BPCRSCHD.INFO.FUNC = 'E';
        S000_CALL_BPZRSCHD();
        if (pgmRtn) return;
        if (WS_FOUND_FLAG == 'Y') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_HAS_SETTLED_SCHD;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPCSFSCH.INF.CI_NO = BPRFSCH.CI_NO;
        R001_TRANS_HIS_OLD_DATA();
        if (pgmRtn) return;
        BPRFSCH.FEE_STATUS = '3';
        BPRFSCH.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRFSCH.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
        BPRFSCH.SUP_TEL1 = SCCGWA.COMM_AREA.SUP1_ID;
        BPRFSCH.SUP_TEL2 = SCCGWA.COMM_AREA.SUP2_ID;
        BPCRFSCH.INFO.FUNC = 'U';
        S000_CALL_BPZRFSCH();
        if (pgmRtn) return;
        B010_01_GET_FCPH();
        if (pgmRtn) return;
        R001_TRANS_HIS_OLD_DATA_FCPH();
        if (pgmRtn) return;
        B010_02_GET_REL_CTRT_INFO();
        if (pgmRtn) return;
    }
    public void B050_BROWSER_PROCESS() throws IOException,SQLException,Exception {
        if (BPCSFSCH.FUNC == '1') {
            B050_01_BRW_VIEW();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B050_01_BRW_VIEW() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSFSCH.KEY.CTRT_NO);
        CEP.TRC(SCCGWA, BPCSFSCH.INF.REL_CTRT_NO);
        CEP.TRC(SCCGWA, BPCSFSCH.INF.FEE_TYPE);
        CEP.TRC(SCCGWA, BPCSFSCH.INF.CI_NO);
        CEP.TRC(SCCGWA, BPCSFSCH.INF.START_DATE);
        CEP.TRC(SCCGWA, BPCSFSCH.INF.FEE_STATUS);
        WS_BRW_FLAG = 'N';
        WS_BRW_FOUND_FLAG = 'N';
        if (BPCSFSCH.KEY.CTRT_NO.trim().length() == 0 
            && BPCSFSCH.INF.REL_CTRT_NO.trim().length() == 0 
            && BPCSFSCH.INF.FEE_TYPE.trim().length() == 0 
            && BPCSFSCH.INF.CI_NO.trim().length() == 0 
            && BPCSFSCH.INF.START_DATE == 0 
            && BPCSFSCH.INF.MATURITY_DATE == 0 
            && BPCSFSCH.INF.FEE_STATUS == ' ') {
            WS_BRW_FLAG = 'Y';
            B050_01_00_BRW_ALL();
            if (pgmRtn) return;
        }
        if (BPCSFSCH.KEY.CTRT_NO.trim().length() > 0) {
            WS_BRW_FLAG = 'Y';
            B050_01_01_QUERY_CTRT();
            if (pgmRtn) return;
        }
        if (WS_BRW_FLAG == 'N') {
            if (BPCSFSCH.INF.REL_CTRT_NO.trim().length() > 0) {
                WS_BRW_FLAG = 'Y';
                B050_01_02_BRW_REL_CTRT();
                if (pgmRtn) return;
            }
            if (WS_BRW_FLAG == 'N' 
                && (BPCSFSCH.INF.FEE_TYPE.trim().length() > 0 
                || BPCSFSCH.INF.CI_NO.trim().length() > 0 
                || BPCSFSCH.INF.FEE_STATUS != ' ')) {
                WS_BRW_FLAG = 'Y';
                B050_01_03_BRW_TYPE_CI_STS();
                if (pgmRtn) return;
            }
            if (WS_BRW_FLAG == 'N' 
                && (BPCSFSCH.INF.START_DATE != 0 
                || BPCSFSCH.INF.MATURITY_DATE != 0)) {
                WS_BRW_FLAG = 'Y';
                B050_01_04_BRW_DATE();
                if (pgmRtn) return;
            }
        }
    }
    public void B050_01_00_BRW_ALL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRFSCH);
        IBS.init(SCCGWA, BPRFSCH);
        BPCRFSCH.INFO.FUNC = 'B';
        BPCRFSCH.INFO.OPT = '0';
        S000_CALL_BPZRFSCH();
        if (pgmRtn) return;
        BPCRFSCH.INFO.FUNC = 'N';
        S000_CALL_BPZRFSCH();
        if (pgmRtn) return;
        if (BPCRFSCH.RETURN_INFO == 'F') {
            B050_01_00_OUT_TITLE();
            if (pgmRtn) return;
        }
        while (BPCRFSCH.RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E') {
            B050_01_00_OUT_BRW_DATA();
            if (pgmRtn) return;
            BPCRFSCH.INFO.FUNC = 'N';
            S000_CALL_BPZRFSCH();
            if (pgmRtn) return;
        }
        BPCRFSCH.INFO.FUNC = 'E';
        S000_CALL_BPZRFSCH();
        if (pgmRtn) return;
    }
    public void B050_01_01_QUERY_CTRT() throws IOException,SQLException,Exception {
        B010_QUERY_PROCESS();
        if (pgmRtn) return;
        B050_01_01_MATCH_INFO();
        if (pgmRtn) return;
        B050_01_00_OUT_TITLE();
        if (pgmRtn) return;
        if (WS_FOUND_FLAG == 'Y') {
            B050_01_00_OUT_BRW_DATA();
            if (pgmRtn) return;
        }
    }
    public void B050_01_02_BRW_REL_CTRT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRFSCH);
        IBS.init(SCCGWA, BPRFSCH);
        BPRFSCH.REL_CTRT_NO = BPCSFSCH.INF.REL_CTRT_NO;
        BPCRFSCH.INFO.FUNC = 'B';
        BPCRFSCH.INFO.OPT = '4';
        S000_CALL_BPZRFSCH();
        if (pgmRtn) return;
        BPCRFSCH.INFO.FUNC = 'N';
        S000_CALL_BPZRFSCH();
        if (pgmRtn) return;
        if (BPCRFSCH.RETURN_INFO == 'F') {
            B050_01_00_OUT_TITLE();
            if (pgmRtn) return;
        }
        while (BPCRFSCH.RETURN_INFO != 'N') {
            B050_01_01_MATCH_INFO();
            if (pgmRtn) return;
            if (WS_FOUND_FLAG == 'Y') {
                B050_01_00_OUT_BRW_DATA();
                if (pgmRtn) return;
            }
            BPCRFSCH.INFO.FUNC = 'N';
            S000_CALL_BPZRFSCH();
            if (pgmRtn) return;
        }
        BPCRFSCH.INFO.FUNC = 'E';
        S000_CALL_BPZRFSCH();
        if (pgmRtn) return;
    }
    public void B050_01_03_BRW_TYPE_CI_STS() throws IOException,SQLException,Exception {
        WS_FOUND_FLAG = 'N';
        IBS.init(SCCGWA, BPCRFSCH);
        IBS.init(SCCGWA, BPRFSCH);
        if (BPCSFSCH.INF.FEE_TYPE.trim().length() == 0) {
            BPRFSCH.FEE_TYPE = "%%%%%%%%%%%%%%%%";
        } else {
            BPRFSCH.FEE_TYPE = BPCSFSCH.INF.FEE_TYPE;
        }
        if (BPCSFSCH.INF.CI_NO.trim().length() == 0) {
            BPRFSCH.CI_NO = "%%%%%%%%%%%%";
        } else {
            BPRFSCH.CI_NO = BPCSFSCH.INF.CI_NO;
        }
        if (BPCSFSCH.INF.FEE_STATUS == ' ') {
            BPRFSCH.FEE_STATUS = ALL.charAt(0);
        } else {
            BPRFSCH.FEE_STATUS = BPCSFSCH.INF.FEE_STATUS;
        }
        BPCRFSCH.INFO.FUNC = 'B';
        BPCRFSCH.INFO.OPT = '5';
        S000_CALL_BPZRFSCH();
        if (pgmRtn) return;
        BPCRFSCH.INFO.FUNC = 'N';
        S000_CALL_BPZRFSCH();
        if (pgmRtn) return;
        if (BPCRFSCH.RETURN_INFO == 'F') {
            B050_01_00_OUT_TITLE();
            if (pgmRtn) return;
        }
        while (BPCRFSCH.RETURN_INFO != 'N') {
            B050_01_01_MATCH_INFO();
            if (pgmRtn) return;
            if (WS_FOUND_FLAG == 'Y') {
                B050_01_00_OUT_BRW_DATA();
                if (pgmRtn) return;
            }
            BPCRFSCH.INFO.FUNC = 'N';
            S000_CALL_BPZRFSCH();
            if (pgmRtn) return;
        }
        BPCRFSCH.INFO.FUNC = 'E';
        S000_CALL_BPZRFSCH();
        if (pgmRtn) return;
    }
    public void B050_01_04_BRW_DATE() throws IOException,SQLException,Exception {
        WS_FOUND_FLAG = 'N';
        IBS.init(SCCGWA, BPCRFSCH);
        IBS.init(SCCGWA, BPRFSCH);
        if (BPCSFSCH.INF.START_DATE != 0) {
            BPRFSCH.START_DATE = BPCSFSCH.INF.START_DATE;
        }
        BPRFSCH.MATURITY_DATE = 99991231;
        if (BPCSFSCH.INF.MATURITY_DATE != 0) {
            BPRFSCH.MATURITY_DATE = BPCSFSCH.INF.MATURITY_DATE;
        }
        CEP.TRC(SCCGWA, BPCSFSCH.INF.START_DATE);
        CEP.TRC(SCCGWA, BPCSFSCH.INF.MATURITY_DATE);
        BPCRFSCH.INFO.FUNC = 'B';
        BPCRFSCH.INFO.OPT = '6';
        S000_CALL_BPZRFSCH();
        if (pgmRtn) return;
        BPCRFSCH.INFO.FUNC = 'N';
        S000_CALL_BPZRFSCH();
        if (pgmRtn) return;
        if (BPCRFSCH.RETURN_INFO == 'F') {
            B050_01_00_OUT_TITLE();
            if (pgmRtn) return;
        }
        while (BPCRFSCH.RETURN_INFO != 'N') {
            B050_01_01_MATCH_INFO();
            if (pgmRtn) return;
            if (WS_FOUND_FLAG == 'Y') {
                B050_01_00_OUT_BRW_DATA();
                if (pgmRtn) return;
            }
            BPCRFSCH.INFO.FUNC = 'N';
            S000_CALL_BPZRFSCH();
            if (pgmRtn) return;
        }
        BPCRFSCH.INFO.FUNC = 'E';
        S000_CALL_BPZRFSCH();
        if (pgmRtn) return;
    }
    public void B050_01_01_MATCH_INFO() throws IOException,SQLException,Exception {
        WS_FOUND_FLAG = 'Y';
        if (BPCSFSCH.INF.REL_CTRT_NO.trim().length() > 0 
            && !BPCSFSCH.INF.REL_CTRT_NO.equalsIgnoreCase(BPRFSCH.REL_CTRT_NO)) {
            WS_FOUND_FLAG = 'N';
        }
        if (WS_FOUND_FLAG == 'Y' 
            && BPCSFSCH.INF.FEE_TYPE.trim().length() > 0 
            && !BPCSFSCH.INF.FEE_TYPE.equalsIgnoreCase(BPRFSCH.FEE_TYPE)) {
            WS_FOUND_FLAG = 'N';
        }
        if (WS_FOUND_FLAG == 'Y' 
            && BPCSFSCH.INF.CI_NO.trim().length() > 0 
            && !BPCSFSCH.INF.CI_NO.equalsIgnoreCase(BPRFSCH.CI_NO)) {
            WS_FOUND_FLAG = 'N';
        }
        if (WS_FOUND_FLAG == 'Y' 
            && BPCSFSCH.INF.START_DATE != 0 
            && BPCSFSCH.INF.START_DATE > BPRFSCH.START_DATE) {
            WS_FOUND_FLAG = 'N';
        }
        if (WS_FOUND_FLAG == 'Y' 
            && BPCSFSCH.INF.MATURITY_DATE != 0 
            && BPCSFSCH.INF.MATURITY_DATE < BPRFSCH.MATURITY_DATE) {
            WS_FOUND_FLAG = 'N';
        }
        if (WS_FOUND_FLAG == 'Y' 
            && BPCSFSCH.INF.FEE_STATUS != ' ' 
            && BPCSFSCH.INF.FEE_STATUS != BPRFSCH.FEE_STATUS) {
            WS_FOUND_FLAG = 'N';
        }
    }
    public void B050_01_00_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 349;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B050_01_00_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_LIST_DATA);
        WS_LIST_DATA.WS_FEE_CTRT_NO = BPRFSCH.KEY.CTRT_NO;
        WS_LIST_DATA.WS_REL_CTRT_SRC = BPRFSCH.REL_CTRT_SRC;
        WS_LIST_DATA.WS_REL_CTRT_NO = BPRFSCH.REL_CTRT_NO;
        WS_LIST_DATA.WS_FEE_TYPE = BPRFSCH.FEE_TYPE;
        WS_LIST_DATA.WS_STA_DATE = BPRFSCH.START_DATE;
        WS_LIST_DATA.WS_MATUR_DATE = BPRFSCH.MATURITY_DATE;
        WS_LIST_DATA.WS_CI_NO = BPRFSCH.CI_NO;
        if (BPRFSCH.CI_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CICCUST);
            CICCUST.DATA.CI_NO = BPRFSCH.CI_NO;
            CICCUST.FUNC = 'C';
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            WS_LIST_DATA.WS_ABBR_NAME = CICCUST.O_DATA.O_CI_NM;
        }
        WS_LIST_DATA.WS_FEE_STATUS = BPRFSCH.FEE_STATUS;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_LIST_DATA);
        SCCMPAG.DATA_LEN = 349;
        B_MPAG();
        if (pgmRtn) return;
        WS_BRW_FOUND_FLAG = 'Y';
    }
    public void B080_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARK;
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK;
        BPCPNHIS.INFO.REF_NO = BPCSFSCH.KEY.CTRT_NO;
        BPCPNHIS.INFO.CI_NO = BPCSFSCH.INF.CI_NO;
        BPCPNHIS.INFO.FMT_ID_LEN = 1086;
        if (BPCSFSCH.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
            BPCPNHIS.INFO.NEW_DAT_PT = BPCHFSCH;
        }
        if (BPCSFSCH.FUNC == 'U') {
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.OLD_DAT_PT = BPCHFSCO;
            BPCPNHIS.INFO.NEW_DAT_PT = BPCHFSCH;
        }
        if (BPCSFSCH.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
            BPCPNHIS.INFO.OLD_DAT_PT = BPCHFSCO;
        }
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B085_MAINT_EVENT() throws IOException,SQLException,Exception {
        if (BPCOUBAS.VAL.FEE_REB_FLG != '1') {
            WS_DIARY_CNT = 0;
            if (BPCSFSCH.FUNC == 'A') {
                B085_00_GET_BATNO();
                if (pgmRtn) return;
                B085_01_ADD_EVENTS();
                if (pgmRtn) return;
            }
            if (BPCSFSCH.FUNC == 'U') {
                if (WS_UPD_FLAG == 'Y') {
                    B085_00_GET_BATNO();
                    if (pgmRtn) return;
                    B085_02_UPD_EVENTS();
                    if (pgmRtn) return;
                }
            }
            if (BPCSFSCH.FUNC == 'D') {
                B085_00_GET_BATNO();
                if (pgmRtn) return;
                B085_03_DEL_EVENTS();
                if (pgmRtn) return;
            }
        }
    }
    public void B085_00_GET_BATNO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSGSEQ);
        BPCSGSEQ.TYPE = "SEQ";
        BPCSGSEQ.CODE = "DYBATNO";
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSGSEQ.RUN_MODE = 'O';
        S000_CALL_BPZSGSEQ();
        if (pgmRtn) return;
    }
    public void B085_01_ADD_EVENTS() throws IOException,SQLException,Exception {
        B085_01_01_A_FSCH_EV_IP();
        if (pgmRtn) return;
        B085_01_02_A_FSCH_EV_ST();
        if (pgmRtn) return;
        B085_01_03_A_SCHD_EVENT();
        if (pgmRtn) return;
    }
    public void B085_01_01_A_FSCH_EV_IP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUMENT);
        BPCUMENT.PROC_DATA.FUNC_CODE = "A ";
        BPCUMENT.DIARY_DATA.CONTRACT_NO = BPCSFSCH.KEY.CTRT_NO;
        BPCUMENT.DIARY_DATA.DIARY_TYPE = "IP";
        BPCUMENT.DIARY_DATA.DIARY_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCUMENT.DIARY_DATA.ACTL_DATE = SCCGWA.COMM_AREA.TR_DATE;
        BPCUMENT.DIARY_DATA.ACTL_TIME = SCCGWA.COMM_AREA.TR_TIME;
        BPCUMENT.DIARY_DATA.ACCT_CENTRE = BPCSFSCH.INF.BOOK_CENTRE;
        BPCUMENT.DIARY_DATA.CONTRACT_TYPE = BPCSFSCH.INF.CTRT_TYPE;
        BPCUMENT.DIARY_DATA.PRODUCT_TYPE = BPCSFSCH.INF.PRD_TYPE;
        BPCUMENT.DIARY_DATA.TRN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCUMENT.DIARY_DATA.TRN_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        BPCUMENT.DIARY_DATA.DIARY_BATNO = BPCSGSEQ.SEQ;
        BPCUMENT.DIARY_DATA.DIARY_STATUS = 'A';
        BPCUMENT.DIARY_DATA.AMOUNT4 = BPCSFSCH.INF.CHARGE_AMT;
        BPCUMENT.DIARY_DATA.AMOUNT5 = BPCSFSCH.INF.CHARGE_AMT;
        if (BPCSFSCH.INF.CHARGE_AMT == 0) {
            WS_START_DATE = BPCSFSCH.INF.START_DATE;
            WS_END_DATE = BPCSFSCH.INF.MATURITY_DATE;
            B086_CAL_FEE_AMT();
            if (pgmRtn) return;
            BPCUMENT.DIARY_DATA.AMOUNT4 = BPCUFEEC.FEE_AMT;
            BPCUMENT.DIARY_DATA.AMOUNT5 = BPCUFEEC.FEE_AMT;
        }
        if (BPCSFSCH.INF.PAY_IND == 'R') {
            BPCUMENT.DIARY_DATA.AMOUNT4 = 0;
        } else {
            BPCUMENT.DIARY_DATA.AMOUNT5 = 0;
        }
        BPCUMENT.DIARY_DATA.AS_OF_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCUMENT.DIARY_DATA.CCY_1 = BPCSFSCH.INF.CHARGE_CCY;
        BPCUMENT.DIARY_DATA.CLIENT_NO = BPCSFSCH.INF.CI_NO;
        BPCUMENT.DIARY_DATA.D_REFNO = 1000;
        BPCUMENT.DIARY_DATA.GEN_IND1 = 'A';
        BPCUMENT.DIARY_DATA.GEN_IND2 = BPCSFSCH.INF.PAY_IND;
        BPCUMENT.DIARY_DATA.HOLIDAY = BPCSFSCH.INF.HOLI_OVER;
        BPCUMENT.DIARY_DATA.HOLOVR_CHK = " ";
        if (WS_EX_RATE == 0) {
            BPCUMENT.DIARY_DATA.RATEX3 = 1;
        } else {
            BPCUMENT.DIARY_DATA.RATEX3 = WS_EX_RATE;
        }
        BPCUMENT.DIARY_DATA.OTH_CN_NO = BPCSFSCH.INF.REL_CTRT_NO;
        B085_90_MOVE_AC_IFNO();
        if (pgmRtn) return;
        S000_CALL_BPZUMENT();
        if (pgmRtn) return;
        WS_DIARY_CNT += 1;
    }
    public void B085_01_02_A_FSCH_EV_ST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUMENT);
        BPCUMENT.PROC_DATA.FUNC_CODE = "A ";
        BPCUMENT.DIARY_DATA.CONTRACT_NO = BPCSFSCH.KEY.CTRT_NO;
        BPCUMENT.DIARY_DATA.DIARY_TYPE = "ST";
        BPCUMENT.DIARY_DATA.DIARY_DATE = BPCSFSCH.INF.START_DATE;
        BPCUMENT.DIARY_DATA.ACTL_DATE = SCCGWA.COMM_AREA.TR_DATE;
        BPCUMENT.DIARY_DATA.ACTL_TIME = SCCGWA.COMM_AREA.TR_TIME;
        BPCUMENT.DIARY_DATA.ACCT_CENTRE = BPCSFSCH.INF.BOOK_CENTRE;
        BPCUMENT.DIARY_DATA.CONTRACT_TYPE = BPCSFSCH.INF.CTRT_TYPE;
        BPCUMENT.DIARY_DATA.PRODUCT_TYPE = BPCSFSCH.INF.PRD_TYPE;
        BPCUMENT.DIARY_DATA.TRN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCUMENT.DIARY_DATA.TRN_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        BPCUMENT.DIARY_DATA.DIARY_BATNO = BPCSGSEQ.SEQ;
        BPCUMENT.DIARY_DATA.DIARY_STATUS = 'A';
        if (BPCSFSCH.INF.START_DATE > SCCGWA.COMM_AREA.AC_DATE) {
            BPCUMENT.DIARY_DATA.DIARY_STATUS = 'U';
        }
        BPCUMENT.DIARY_DATA.AS_OF_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCUMENT.DIARY_DATA.CCY_1 = BPCSFSCH.INF.CHARGE_CCY;
        BPCUMENT.DIARY_DATA.CLIENT_NO = BPCSFSCH.INF.CI_NO;
        BPCUMENT.DIARY_DATA.D_REFNO = 0;
        BPCUMENT.DIARY_DATA.GEN_IND1 = 'A';
        BPCUMENT.DIARY_DATA.GEN_IND2 = BPCSFSCH.INF.PAY_IND;
        BPCUMENT.DIARY_DATA.HOLIDAY = BPCSFSCH.INF.HOLI_OVER;
        BPCUMENT.DIARY_DATA.HOLOVR_CHK = " ";
        if (WS_EX_RATE == 0) {
            BPCUMENT.DIARY_DATA.RATEX3 = 1;
        } else {
            BPCUMENT.DIARY_DATA.RATEX3 = WS_EX_RATE;
        }
        BPCUMENT.DIARY_DATA.OTH_CN_NO = BPCSFSCH.INF.REL_CTRT_NO;
        B085_90_MOVE_AC_IFNO();
        if (pgmRtn) return;
        S000_CALL_BPZUMENT();
        if (pgmRtn) return;
        WS_DIARY_CNT += 1;
    }
    public void B085_01_03_A_SCHD_EVENT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRSCHD);
        IBS.init(SCCGWA, BPRFSCHD);
        BPRFSCHD.KEY.CTRT_NO = BPCSFSCH.KEY.CTRT_NO;
        BPCRSCHD.INFO.FUNC = '1';
        S000_CALL_BPZRSCHD();
        if (pgmRtn) return;
        WS_SCHD_COUNT = BPCRSCHD.INFO.SCHD_COUNT;
        CEP.TRC(SCCGWA, WS_SCHD_COUNT);
        WS_CNT1 = 0;
        WS_PRE_SETTLE_DATE = 0;
        WS_EVENT_TOT_AMT = 0;
        IBS.init(SCCGWA, BPCRSCHD);
        IBS.init(SCCGWA, BPRFSCHD);
        BPRFSCHD.KEY.CTRT_NO = BPCSFSCH.KEY.CTRT_NO;
        BPCRSCHD.INFO.FUNC = 'B';
        BPCRSCHD.INFO.OPT = '3';
        S000_CALL_BPZRSCHD();
        if (pgmRtn) return;
        BPCRSCHD.INFO.FUNC = 'N';
        S000_CALL_BPZRSCHD();
        if (pgmRtn) return;
        while (BPCRSCHD.RETURN_INFO != 'N') {
            CEP.TRC(SCCGWA, BPRFSCHD.KEY.CTRT_NO);
            CEP.TRC(SCCGWA, BPRFSCHD.KEY.SETTLE_DATE);
            WS_CNT1 += 1;
            if (WS_CNT1 == WS_SCHD_COUNT) {
                WS_FINAL_FLAG = 'Y';
            }
            WS_DIARY_CNT += 1;
            B085_01_02_01_CAL_FEE();
            if (pgmRtn) return;
            B085_01_02_02_A_EVENT();
            if (pgmRtn) return;
            BPCRSCHD.INFO.FUNC = 'N';
            S000_CALL_BPZRSCHD();
            if (pgmRtn) return;
        }
        BPCRSCHD.INFO.FUNC = 'E';
        S000_CALL_BPZRSCHD();
        if (pgmRtn) return;
    }
    public void B085_01_02_01_CAL_FEE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_FOR_MA_FLAG);
        CEP.TRC(SCCGWA, WS_FINAL_FLAG);
        CEP.TRC(SCCGWA, BPRFSCH.CHARGE_AMT);
        if (WS_FOR_MA_FLAG != 'N' 
            && WS_FINAL_FLAG == 'Y' 
            && BPRFSCH.CHARGE_AMT > 0) {
            BPCUFEEC.FEE_AMT = BPRFSCH.CHARGE_AMT - WS_EVENT_TOT_AMT;
            CEP.TRC(SCCGWA, "THE FEE AMOUNT OF LAST DETAL SCHEDULE");
        } else {
            if (WS_PRE_SETTLE_DATE == 0) {
                WS_START_DATE = BPCSFSCH.INF.START_DATE;
            } else {
                WS_START_DATE = WS_PRE_SETTLE_DATE;
            }
            WS_END_DATE = BPRFSCHD.KEY.SETTLE_DATE;
            if (WS_FOR_MA_FLAG == 'Y') {
                WS_START_DATE = BPCSFSCH.INF.START_DATE;
            }
            CEP.TRC(SCCGWA, WS_START_DATE);
            CEP.TRC(SCCGWA, WS_END_DATE);
            if (BPCSFSCH.INF.ACCRUAL_TYPE == 'F' 
                || BPCSFSCH.INF.ACCRUAL_TYPE == 'B') {
                if (BPCSFSCH.INF.ACCRUAL_TYPE == 'B' 
                    && WS_SCHD_COUNT == WS_CNT1) {
                } else {
                    IBS.init(SCCGWA, SCCCLDT);
                    SCCCLDT.DATE1 = WS_END_DATE;
                    SCCCLDT.DAYS = -1;
                    S000_CALL_SCSSCLDT();
                    if (pgmRtn) return;
                    WS_END_DATE = SCCCLDT.DATE2;
                }
            }
            if (BPCSFSCH.INF.ACCRUAL_TYPE == 'L') {
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = WS_START_DATE;
                SCCCLDT.DAYS = 1;
                S000_CALL_SCSSCLDT();
                if (pgmRtn) return;
                WS_START_DATE = SCCCLDT.DATE2;
            }
            CEP.TRC(SCCGWA, WS_START_DATE);
            CEP.TRC(SCCGWA, WS_END_DATE);
            IBS.init(SCCGWA, BPCUFEEC);
            BPCUFEEC.CTRT_NO = BPCSFSCH.KEY.CTRT_NO;
            BPCUFEEC.CTRT_TYP = 'S';
            BPCUFEEC.STA_DT = WS_START_DATE;
            BPCUFEEC.END_DT = WS_END_DATE;
            BPCUFEEC.RUN_MODE = 'O';
            S000_CALL_BPZUFEEC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCUFEEC.FEE_AMT);
            CEP.TRC(SCCGWA, BPCUFEEC.EX_RATE);
            if (WS_EX_RATE == 0) {
                WS_EX_RATE = BPCUFEEC.EX_RATE;
            }
        }
    }
    public void B085_01_02_02_A_EVENT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUMENT);
        BPCUMENT.PROC_DATA.FUNC_CODE = "A ";
        BPCUMENT.DIARY_DATA.CONTRACT_NO = BPCSFSCH.KEY.CTRT_NO;
        BPCUMENT.DIARY_DATA.DIARY_TYPE = "FS";
        BPCUMENT.DIARY_DATA.DIARY_DATE = BPRFSCHD.KEY.SETTLE_DATE;
        BPCUMENT.DIARY_DATA.ACTL_DATE = SCCGWA.COMM_AREA.TR_DATE;
        BPCUMENT.DIARY_DATA.ACTL_TIME = SCCGWA.COMM_AREA.TR_TIME;
        BPCUMENT.DIARY_DATA.ACCT_CENTRE = BPCSFSCH.INF.BOOK_CENTRE;
        BPCUMENT.DIARY_DATA.CONTRACT_TYPE = BPCSFSCH.INF.CTRT_TYPE;
        BPCUMENT.DIARY_DATA.PRODUCT_TYPE = BPCSFSCH.INF.PRD_TYPE;
        BPCUMENT.DIARY_DATA.TRN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCUMENT.DIARY_DATA.TRN_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        BPCUMENT.DIARY_DATA.DIARY_BATNO = BPCSGSEQ.SEQ;
        BPCUMENT.DIARY_DATA.DIARY_STATUS = 'U';
        BPCUMENT.DIARY_DATA.AMOUNT1 = BPCUFEEC.FEE_AMT;
        BPCUMENT.DIARY_DATA.AMOUNT4 = BPCUFEEC.FEE_AMT;
        BPCUMENT.DIARY_DATA.AMOUNT5 = BPCUFEEC.FEE_AMT;
        if (BPCSFSCH.INF.PAY_IND == 'R') {
            BPCUMENT.DIARY_DATA.AMOUNT4 = 0;
        } else {
            BPCUMENT.DIARY_DATA.AMOUNT5 = 0;
        }
        BPCUMENT.DIARY_DATA.AS_OF_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCUMENT.DIARY_DATA.CCY_1 = BPCSFSCH.INF.CHARGE_CCY;
        BPCUMENT.DIARY_DATA.CLIENT_NO = BPCSFSCH.INF.CI_NO;
        BPCUMENT.DIARY_DATA.D_REFNO = 0;
        BPCUMENT.DIARY_DATA.GEN_IND1 = 'A';
        BPCUMENT.DIARY_DATA.GEN_IND2 = BPCSFSCH.INF.PAY_IND;
        BPCUMENT.DIARY_DATA.HOLIDAY = BPCSFSCH.INF.HOLI_OVER;
        BPCUMENT.DIARY_DATA.HOLOVR_CHK = " ";
        if (WS_CNT1 == WS_SCHD_COUNT) {
            BPCUMENT.DIARY_DATA.DIARY_TYPE = "MA";
            BPCUMENT.DIARY_DATA.D_REFNO = 9999;
            BPCUMENT.DIARY_DATA.AMOUNT10 = BPRFSCH.CHARGE_AMT;
            if (BPRFSCH.CHARGE_AMT <= 0) {
                WS_FOR_MA_FLAG = 'Y';
                B085_01_02_01_CAL_FEE();
                if (pgmRtn) return;
                WS_FOR_MA_FLAG = 'N';
                BPCUMENT.DIARY_DATA.AMOUNT10 = BPCUFEEC.FEE_AMT;
            }
            CEP.TRC(SCCGWA, BPCUMENT.DIARY_DATA.AMOUNT10);
            BPCUMENT.PROC_DATA.DIARY_CNT = WS_DIARY_CNT;
        }
        if (WS_EX_RATE == 0) {
            BPCUMENT.DIARY_DATA.RATEX3 = 1;
        } else {
            BPCUMENT.DIARY_DATA.RATEX3 = WS_EX_RATE;
        }
        BPCUMENT.DIARY_DATA.OTH_CN_NO = BPCSFSCH.INF.REL_CTRT_NO;
        B085_90_MOVE_AC_IFNO();
        if (pgmRtn) return;
        S000_CALL_BPZUMENT();
        if (pgmRtn) return;
        WS_PRE_SETTLE_DATE = BPRFSCHD.KEY.SETTLE_DATE;
        WS_EVENT_TOT_AMT = WS_EVENT_TOT_AMT + BPCUFEEC.FEE_AMT;
        if (BPRFSCHD.STS == 'C' 
            && BPRFSCHD.ADJ_AMT != 0) {
            WS_EVENT_TOT_AMT = WS_EVENT_TOT_AMT - BPRFSCHD.ADJ_AMT;
        }
    }
    public void B085_02_UPD_EVENTS() throws IOException,SQLException,Exception {
        B085_02_01_UPD_IP();
        if (pgmRtn) return;
        B085_02_02_UPD_ST();
        if (pgmRtn) return;
        B085_02_03_DEL_OLD_EVENT();
        if (pgmRtn) return;
        B085_01_03_A_SCHD_EVENT();
        if (pgmRtn) return;
    }
    public void B085_02_01_UPD_IP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUMENT);
        BPCUMENT.PROC_DATA.FUNC_CODE = "R ";
        BPCUMENT.DIARY_DATA.CONTRACT_NO = BPCSFSCH.KEY.CTRT_NO;
        BPCUMENT.DIARY_DATA.DIARY_TYPE = "IP";
        BPCUMENT.DIARY_DATA.DIARY_DATE = BPRFSCH.CREATE_DATE;
        S000_CALL_BPZUMENT();
        if (pgmRtn) return;
        BPCUMENT.DIARY_DATA.ACCT_CENTRE = BPCSFSCH.INF.BOOK_CENTRE;
        BPCUMENT.DIARY_DATA.PRODUCT_TYPE = BPCSFSCH.INF.PRD_TYPE;
        BPCUMENT.DIARY_DATA.TRN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCUMENT.DIARY_DATA.TRN_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        BPCUMENT.DIARY_DATA.DIARY_BATNO = BPCSGSEQ.SEQ;
        BPCUMENT.DIARY_DATA.DIARY_STATUS = 'A';
        BPCUMENT.DIARY_DATA.AMOUNT4 = BPCSFSCH.INF.CHARGE_AMT;
        BPCUMENT.DIARY_DATA.AMOUNT5 = BPCSFSCH.INF.CHARGE_AMT;
        if (BPCSFSCH.INF.CHARGE_AMT == 0) {
            WS_START_DATE = BPCSFSCH.INF.START_DATE;
            WS_END_DATE = BPCSFSCH.INF.MATURITY_DATE;
            B086_CAL_FEE_AMT();
            if (pgmRtn) return;
            BPCUMENT.DIARY_DATA.AMOUNT4 = BPCUFEEC.FEE_AMT;
            BPCUMENT.DIARY_DATA.AMOUNT5 = BPCUFEEC.FEE_AMT;
        }
        if (BPCSFSCH.INF.PAY_IND == 'R') {
            BPCUMENT.DIARY_DATA.AMOUNT4 = 0;
        } else {
            BPCUMENT.DIARY_DATA.AMOUNT5 = 0;
        }
        BPCUMENT.DIARY_DATA.CCY_1 = BPCSFSCH.INF.CHARGE_CCY;
        BPCUMENT.DIARY_DATA.CLIENT_NO = BPCSFSCH.INF.CI_NO;
        BPCUMENT.DIARY_DATA.D_REFNO = 1000;
        BPCUMENT.DIARY_DATA.GEN_IND1 = 'A';
        BPCUMENT.DIARY_DATA.GEN_IND2 = BPCSFSCH.INF.PAY_IND;
        BPCUMENT.DIARY_DATA.HOLIDAY = BPCSFSCH.INF.HOLI_OVER;
        BPCUMENT.DIARY_DATA.HOLOVR_CHK = " ";
        if (WS_EX_RATE != 0) {
            BPCUMENT.DIARY_DATA.RATEX3 = WS_EX_RATE;
        }
        BPCUMENT.DIARY_DATA.OTH_CN_NO = BPCSFSCH.INF.REL_CTRT_NO;
        B085_90_MOVE_AC_IFNO();
        if (pgmRtn) return;
        BPCUMENT.PROC_DATA.FUNC_CODE = "M2";
        S000_CALL_BPZUMENT();
        if (pgmRtn) return;
        WS_DIARY_CNT += 1;
    }
    public void B085_02_02_UPD_ST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUMENT);
        BPCUMENT.PROC_DATA.FUNC_CODE = "R ";
        BPCUMENT.DIARY_DATA.CONTRACT_NO = BPCSFSCH.KEY.CTRT_NO;
        BPCUMENT.DIARY_DATA.DIARY_TYPE = "ST";
        BPCUMENT.DIARY_DATA.DIARY_DATE = WS_OLD_START_DATE;
        S000_CALL_BPZUMENT();
        if (pgmRtn) return;
        BPCUMENT.DIARY_DATA.DIARY_DATE = BPCSFSCH.INF.START_DATE;
        BPCUMENT.DIARY_DATA.ACTL_DATE = SCCGWA.COMM_AREA.TR_DATE;
        BPCUMENT.DIARY_DATA.ACTL_TIME = SCCGWA.COMM_AREA.TR_TIME;
        BPCUMENT.DIARY_DATA.ACCT_CENTRE = BPCSFSCH.INF.BOOK_CENTRE;
        BPCUMENT.DIARY_DATA.CONTRACT_TYPE = BPCSFSCH.INF.CTRT_TYPE;
        BPCUMENT.DIARY_DATA.PRODUCT_TYPE = BPCSFSCH.INF.PRD_TYPE;
        BPCUMENT.DIARY_DATA.TRN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCUMENT.DIARY_DATA.TRN_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        BPCUMENT.DIARY_DATA.DIARY_BATNO = BPCSGSEQ.SEQ;
        BPCUMENT.DIARY_DATA.DIARY_STATUS = 'A';
        if (BPCSFSCH.INF.START_DATE > SCCGWA.COMM_AREA.AC_DATE) {
            BPCUMENT.DIARY_DATA.DIARY_STATUS = 'U';
        }
        BPCUMENT.DIARY_DATA.AS_OF_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCUMENT.DIARY_DATA.CCY_1 = BPCSFSCH.INF.CHARGE_CCY;
        BPCUMENT.DIARY_DATA.CLIENT_NO = BPCSFSCH.INF.CI_NO;
        BPCUMENT.DIARY_DATA.D_REFNO = 0;
        BPCUMENT.DIARY_DATA.GEN_IND1 = 'A';
        BPCUMENT.DIARY_DATA.GEN_IND2 = BPCSFSCH.INF.PAY_IND;
        BPCUMENT.DIARY_DATA.HOLIDAY = BPCSFSCH.INF.HOLI_OVER;
        BPCUMENT.DIARY_DATA.HOLOVR_CHK = " ";
        if (WS_EX_RATE != 0) {
            BPCUMENT.DIARY_DATA.RATEX3 = WS_EX_RATE;
        }
        BPCUMENT.DIARY_DATA.OTH_CN_NO = BPCSFSCH.INF.REL_CTRT_NO;
        B085_90_MOVE_AC_IFNO();
        if (pgmRtn) return;
        BPCUMENT.PROC_DATA.FUNC_CODE = "M2";
        S000_CALL_BPZUMENT();
        if (pgmRtn) return;
        WS_DIARY_CNT += 1;
    }
    public void B085_02_03_DEL_OLD_EVENT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUMENT);
        BPCUMENT.DIARY_DATA.CONTRACT_NO = BPCSFSCH.KEY.CTRT_NO;
        BPCUMENT.DIARY_DATA.DIARY_BATNO = BPCSGSEQ.SEQ;
        BPCUMENT.DIARY_DATA.DIARY_TYPE = "FS";
        BPCUMENT.PROC_DATA.FUNC_CODE = "D2";
        S000_CALL_BPZUMENT();
        if (pgmRtn) return;
        WS_DIARY_CNT = (short) (WS_DIARY_CNT + BPCUMENT.PROC_DATA.DIARY_CNT);
        IBS.init(SCCGWA, BPCUMENT);
        BPCUMENT.DIARY_DATA.CONTRACT_NO = BPCSFSCH.KEY.CTRT_NO;
        BPCUMENT.DIARY_DATA.DIARY_BATNO = BPCSGSEQ.SEQ;
        BPCUMENT.DIARY_DATA.DIARY_TYPE = "MA";
        BPCUMENT.PROC_DATA.FUNC_CODE = "D2";
        S000_CALL_BPZUMENT();
        if (pgmRtn) return;
        WS_DIARY_CNT = (short) (WS_DIARY_CNT + BPCUMENT.PROC_DATA.DIARY_CNT);
    }
    public void B085_03_DEL_EVENTS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUMENT);
        BPCUMENT.DIARY_DATA.CONTRACT_NO = BPCSFSCH.KEY.CTRT_NO;
        BPCUMENT.DIARY_DATA.DIARY_BATNO = BPCSGSEQ.SEQ;
        BPCUMENT.PROC_DATA.FUNC_CODE = "D1";
        S000_CALL_BPZUMENT();
        if (pgmRtn) return;
    }
    public void B085_90_MOVE_AC_IFNO() throws IOException,SQLException,Exception {
        if (BPCSFSCH.INF.PAY_IND == 'R') {
            if (BPCSFSCH.INF.CHARGE_METHOD == '0' 
                || BPCSFSCH.INF.CHARGE_METHOD == '1' 
                || BPCSFSCH.INF.CHARGE_METHOD == '2') {
                BPCUMENT.DIARY_DATA.RCV_DD_NO1 = BPCSFSCH.INF.CHARGE_AC;
                if (BPCSFSCH.INF.CHARGE_METHOD == '2') {
                    BPCUMENT.DIARY_DATA.DD_CHQ_NO1 = BPCSFSCH.INF.CHQ_NO;
                }
            } else {
                BPCUMENT.DIARY_DATA.RCV_NOSTRO_AC = BPCSFSCH.INF.NOSTRO_CD;
            }
        } else {
            if (BPCSFSCH.INF.CHARGE_METHOD == '0' 
                || BPCSFSCH.INF.CHARGE_METHOD == '1' 
                || BPCSFSCH.INF.CHARGE_METHOD == '2') {
                BPCUMENT.DIARY_DATA.PAY_DD_NO1 = BPCSFSCH.INF.CHARGE_AC;
                if (BPCSFSCH.INF.CHARGE_METHOD == '2') {
                    BPCUMENT.DIARY_DATA.DD_CHQ_NO1 = BPCSFSCH.INF.CHQ_NO;
                }
            } else {
                BPCUMENT.DIARY_DATA.PAY_NOSTRO_AC = BPCSFSCH.INF.NOSTRO_CD;
            }
        }
    }
    public void B086_CAL_FEE_AMT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_START_DATE);
        CEP.TRC(SCCGWA, WS_END_DATE);
        if (BPCSFSCH.INF.ACCRUAL_TYPE == 'F' 
            || BPCSFSCH.INF.ACCRUAL_TYPE == 'B') {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = WS_END_DATE;
            SCCCLDT.DAYS = -1;
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            WS_END_DATE = SCCCLDT.DATE2;
        }
        if (BPCSFSCH.INF.ACCRUAL_TYPE == 'L') {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = WS_START_DATE;
            SCCCLDT.DAYS = 1;
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            WS_START_DATE = SCCCLDT.DATE2;
        }
        CEP.TRC(SCCGWA, WS_START_DATE);
        CEP.TRC(SCCGWA, WS_END_DATE);
        IBS.init(SCCGWA, BPCUFEEC);
        BPCUFEEC.CTRT_NO = BPCSFSCH.KEY.CTRT_NO;
        BPCUFEEC.CTRT_TYP = 'S';
        BPCUFEEC.STA_DT = WS_START_DATE;
        BPCUFEEC.END_DT = WS_END_DATE;
        BPCUFEEC.RUN_MODE = 'O';
        S000_CALL_BPZUFEEC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCUFEEC.FEE_AMT);
        CEP.TRC(SCCGWA, BPCUFEEC.EX_RATE);
        if (WS_EX_RATE == 0) {
            WS_EX_RATE = BPCUFEEC.EX_RATE;
        }
    }
    public void B090_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFSCH1);
        B090_01_TRANS_DATA_OUPUT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCFSCH1.CTRT_DESC);
        CEP.TRC(SCCGWA, BPCFSCH1.CP_NO);
        CEP.TRC(SCCGWA, BPCFSCH1.SALE_DT);
        CEP.TRC(SCCGWA, BPCFSCH1.CCY_TYPE);
        CEP.TRC(SCCGWA, BPCFSCH1.R_CENT_DATA[1-1].R_CENT);
        CEP.TRC(SCCGWA, BPCFSCH1.R_CENT_DATA[1-1].R_BR);
        CEP.TRC(SCCGWA, BPCFSCH1.R_CENT_DATA[1-1].R_PCT_NS);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_X;
        if (BPCSFSCH.FUNC == 'A' 
            || BPCSFSCH.FUNC == 'U' 
            || BPCSFSCH.FUNC == 'D') {
            SCCFMT.FMTID = K_OUTPUT_FMT_9;
        }
        SCCFMT.DATA_PTR = BPCFSCH1;
        SCCFMT.DATA_LEN = 1273;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B090_01_TRANS_DATA_OUPUT() throws IOException,SQLException,Exception {
        BPCFSCH1.CTRT_NO = BPRFSCH.KEY.CTRT_NO;
        BPCFSCH1.CTRT_DESC = BPRFSCH.CTRT_DESC;
        BPCFSCH1.CI_NO = BPRFSCH.CI_NO;
        BPCFSCH1.CI_ABBR_NAME = BPCSFSCH.INF.AB_NAME;
        if (BPRFSCH.CI_NO.trim().length() > 0 
            && BPCSFSCH.INF.AB_NAME.trim().length() == 0) {
            IBS.init(SCCGWA, CICCUST);
            CICCUST.DATA.CI_NO = BPRFSCH.CI_NO;
            CICCUST.FUNC = 'C';
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            if (CICCUST.O_DATA.O_CI_NM.trim().length() > 0) {
                BPCFSCH1.CI_ABBR_NAME = CICCUST.O_DATA.O_CI_NM;
            } else {
                BPCFSCH1.CI_ABBR_NAME = CICCUST.O_DATA.O_CI_ENM;
            }
            BPCSFSCH.INF.AB_NAME = BPCFSCH1.CI_ABBR_NAME;
        }
        BPCFSCH1.FEE_TYPE = BPRFSCH.FEE_TYPE;
        BPCFSCH1.BOOK_CENTRE = BPRFSCH.BOOK_CENTRE;
        BPCFSCH1.PRD_TYPE = BPRFSCH.PRD_TYPE;
        BPCFSCH1.START_DATE = BPRFSCH.START_DATE;
        BPCFSCH1.MATURITY_DATE = BPRFSCH.MATURITY_DATE;
        BPCFSCH1.UCT_FLG = BPRFSCH.UCT_FLG;
        BPCFSCH1.SETTLE_FREQ = BPRFSCH.SETTLE_FREQ;
        BPCFSCH1.FREQ_COUNT = BPRFSCH.FREQ_COUNT;
        BPCFSCH1.FIRST_CHG_DATE = BPRFSCH.FIRST_CHG_DATE;
        BPCFSCH1.HOLI_OVER = BPRFSCH.HOLI_OVER;
        BPCFSCH1.CAL_CODE1 = BPRFSCH.CAL_CODE1;
        BPCFSCH1.HOLI_METHOD = BPRFSCH.HOLI_METHOD;
        BPCFSCH1.CAL_CODE2 = BPRFSCH.CAL_CODE2;
        BPCFSCH1.PAY_IND = BPRFSCH.PAY_IND;
        BPCFSCH1.CASHFLOW_IND = BPRFSCH.CASHFLOW_IND;
        BPCFSCH1.BANK_PORTF = BPRFSCH.BANK_PORTF;
        BPCFSCH1.ACCRUAL_TYPE = BPRFSCH.ACCRUAL_TYPE;
        BPCFSCH1.PRICE_METHOD = BPRFSCH.PRICE_METHOD;
        BPCFSCH1.TXN_CCY = BPRFSCH.TXN_CCY;
        BPCFSCH1.TXN_AMT = BPRFSCH.PRIN_AMT;
        BPCFSCH1.REL_CTRT_SRC = BPRFSCH.REL_CTRT_SRC;
        BPCFSCH1.REL_CTRT_NO = BPRFSCH.REL_CTRT_NO;
        BPCFSCH1.REL_CTRT_TYPE = BPCSFSCH.INF.REL_CT_TYPE;
        BPCFSCH1.REL_COL_NO = "" + BPRFSCH.REL_COL_NO;
        JIBS_tmp_int = BPCFSCH1.REL_COL_NO.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) BPCFSCH1.REL_COL_NO = "0" + BPCFSCH1.REL_COL_NO;
        BPCFSCH1.REL_LMT_NO = BPRFSCH.REL_LMT_NO;
        BPCFSCH1.FIR_DSCT = BPRFSCH.FIR_DSCT;
        BPCFSCH1.PROMPT_DAYS = BPRFSCH.PRMT_DAYS;
        BPCFSCH1.AMT_TYPE = BPRFSCH.AMT_TYPE;
        BPCFSCH1.REL_PROD_TYPE = BPCSFSCH.INF.REL_PD_TYPE;
        BPCFSCH1.MULTI = BPCSFSCH.INF.MULTI;
        BPCFSCH1.INT_BAS = BPCSFSCH.INF.INT_BAS;
        CEP.TRC(SCCGWA, BPCFSCH1.INT_BAS);
        WS_CNT = 0;
        for (WS_CNT = 1; WS_CNT <= 5; WS_CNT += 1) {
            BPCFSCH1.STD_INFO[WS_CNT-1].UP_AMT = BPCSFSCH.INF.DATE[WS_CNT-1].REF_AMT;
            BPCFSCH1.STD_INFO[WS_CNT-1].UP_PCT = BPCSFSCH.INF.DATE[WS_CNT-1].REF_PCT;
            BPCFSCH1.STD_INFO[WS_CNT-1].AGG_MTH = BPCSFSCH.INF.DATE[WS_CNT-1].AGG_MTH;
            BPCFSCH1.STD_INFO[WS_CNT-1].REF_AMT = BPCSFSCH.INF.DATE[WS_CNT-1].FEE_AMT;
            BPCFSCH1.STD_INFO[WS_CNT-1].FEE_RATE = BPCSFSCH.INF.DATE[WS_CNT-1].FEE_RATE;
        }
        if (BPCSFSCH.FUNC == 'Q' 
            || BPCSFSCH.FUNC == 'D') {
            CEP.TRC(SCCGWA, "MOVE FCPH");
            BPCFSCH1.MULTI = BPRFCPH.MULTI;
            BPCFSCH1.INT_BAS = BPRFCPH.INT_BAS;
            BPCFSCH1.STD_INFO[1-1].UP_AMT = BPRFCPH.UP_AMT_1;
            BPCFSCH1.STD_INFO[1-1].UP_PCT = BPRFCPH.UP_PCT_1;
            BPCFSCH1.STD_INFO[1-1].REF_AMT = BPRFCPH.FEE_AMT_1;
            BPCFSCH1.STD_INFO[1-1].FEE_RATE = BPRFCPH.FEE_RATE_1;
            BPCFSCH1.STD_INFO[2-1].UP_AMT = BPRFCPH.UP_AMT_2;
            BPCFSCH1.STD_INFO[2-1].UP_PCT = BPRFCPH.UP_PCT_2;
            BPCFSCH1.STD_INFO[2-1].REF_AMT = BPRFCPH.FEE_AMT_2;
            BPCFSCH1.STD_INFO[2-1].FEE_RATE = BPRFCPH.FEE_RATE_2;
            BPCFSCH1.STD_INFO[3-1].UP_AMT = BPRFCPH.UP_AMT_3;
            BPCFSCH1.STD_INFO[3-1].UP_PCT = BPRFCPH.UP_PCT_3;
            BPCFSCH1.STD_INFO[3-1].REF_AMT = BPRFCPH.FEE_AMT_3;
            BPCFSCH1.STD_INFO[3-1].FEE_RATE = BPRFCPH.FEE_RATE_3;
            BPCFSCH1.STD_INFO[4-1].UP_AMT = BPRFCPH.UP_AMT_4;
            BPCFSCH1.STD_INFO[4-1].UP_PCT = BPRFCPH.UP_PCT_4;
            BPCFSCH1.STD_INFO[4-1].REF_AMT = BPRFCPH.FEE_AMT_4;
            BPCFSCH1.STD_INFO[4-1].FEE_RATE = BPRFCPH.FEE_RATE_4;
            BPCFSCH1.STD_INFO[5-1].UP_AMT = BPRFCPH.UP_AMT_5;
            BPCFSCH1.STD_INFO[5-1].UP_PCT = BPRFCPH.UP_PCT_5;
            BPCFSCH1.STD_INFO[5-1].REF_AMT = BPRFCPH.FEE_AMT_5;
            BPCFSCH1.STD_INFO[5-1].FEE_RATE = BPRFCPH.FEE_RATE_5;
        }
        BPCFSCH1.AGGR_TYPE = BPRFSCH.AGGR_TYPE;
        BPCFSCH1.REF_CCY = BPRFSCH.REF_CCY;
        BPCFSCH1.REF_METHOD = BPRFSCH.REF_METHOD;
        BPCFSCH1.FEE_CCY = BPRFSCH.REF_CCY;
        BPCFSCH1.AUTO_CHG_FLAG = BPRFSCH.AUTO_CHG_FLAG;
        BPCFSCH1.CHARGE_CCY = BPRFSCH.CHARGE_CCY;
        BPCFSCH1.CHARGE_AMT = BPRFSCH.CHARGE_AMT;
        BPCFSCH1.CHARGE_METHOD = BPRFSCH.CHARGE_METHOD;
        BPCFSCH1.CHARGE_AC = BPRFSCH.CHARGE_AC;
        BPCFSCH1.NOSTRO_AC = BPRFSCH.NOSTRO_AC;
        BPCFSCH1.GL_MASTER1 = BPRFSCH.GL_MASTER1;
        BPCFSCH1.GL_MASTER2 = BPRFSCH.GL_MASTER2;
        BPCFSCH1.GL_MASTER3 = BPRFSCH.GL_MASTER3;
        BPCFSCH1.GL_MASTER4 = BPRFSCH.GL_MASTER4;
        CEP.TRC(SCCGWA, BPRFSCH.RESP_CENTER1);
        CEP.TRC(SCCGWA, BPRFSCH.BR1);
        CEP.TRC(SCCGWA, BPRFSCH.PCT1);
        CEP.TRC(SCCGWA, BPRFSCH.RESP_CENTER2);
        CEP.TRC(SCCGWA, BPRFSCH.BR2);
        CEP.TRC(SCCGWA, BPRFSCH.PCT2);
        BPCFSCH1.R_CENT_DATA[1-1].R_CENT = BPRFSCH.RESP_CENTER1;
        BPCFSCH1.R_CENT_DATA[1-1].R_BR = BPRFSCH.BR1;
        BPCFSCH1.R_CENT_DATA[1-1].R_PCT_NS = BPRFSCH.PCT1;
        BPCFSCH1.R_CENT_DATA[2-1].R_CENT = BPRFSCH.RESP_CENTER2;
        BPCFSCH1.R_CENT_DATA[2-1].R_BR = BPRFSCH.BR2;
        BPCFSCH1.R_CENT_DATA[2-1].R_PCT_NS = BPRFSCH.PCT2;
        BPCFSCH1.R_CENT_DATA[3-1].R_CENT = BPRFSCH.RESP_CENTER3;
        BPCFSCH1.R_CENT_DATA[3-1].R_BR = BPRFSCH.BR3;
        BPCFSCH1.R_CENT_DATA[3-1].R_PCT_NS = BPRFSCH.PCT3;
        BPCFSCH1.R_CENT_DATA[4-1].R_CENT = BPRFSCH.RESP_CENTER4;
        BPCFSCH1.R_CENT_DATA[4-1].R_BR = BPRFSCH.BR4;
        BPCFSCH1.R_CENT_DATA[4-1].R_PCT_NS = BPRFSCH.PCT4;
        BPCFSCH1.R_CENT_DATA[5-1].R_CENT = BPRFSCH.RESP_CENTER5;
        BPCFSCH1.R_CENT_DATA[5-1].R_BR = BPRFSCH.BR5;
        BPCFSCH1.R_CENT_DATA[5-1].R_PCT_NS = BPRFSCH.PCT5;
        BPCFSCH1.FEE_STATUS = BPRFSCH.FEE_STATUS;
        BPCFSCH1.REMARK = BPRFSCH.REMARK;
        BPCFSCH1.FCHG_MIN_AMT = BPRFSCH.FCHG_MIN_AMT;
        BPCFSCH1.CHG_CCY_REAL = BPRFSCH.CHG_CCY_REAL;
        BPCFSCH1.CP_NO = BPRFSCH.CARD_PSBK_NO;
        BPCFSCH1.SALE_DT = BPRFSCH.SALE_DATE;
        BPCFSCH1.CCY_TYPE = BPRFSCH.CCY_TYPE;
        BPCFSCH1.CHQ_NO = BPRFSCH.CHQ_NO;
        BPCFSCH1.CREV_NO = BPRFSCH.CREV_NO;
    }
    public void R000_TRANS_DATA() throws IOException,SQLException,Exception {
        BPRFSCH.KEY.CTRT_NO = BPCSFSCH.KEY.CTRT_NO;
        BPRFSCH.CI_NO = BPCSFSCH.INF.CI_NO;
        BPRFSCH.CTRT_DESC = BPCSFSCH.INF.CTRT_DESC;
        BPRFSCH.CTRT_TYPE = BPCSFSCH.INF.CTRT_TYPE;
        BPRFSCH.FEE_TYPE = BPCSFSCH.INF.FEE_TYPE;
        BPRFSCH.BOOK_CENTRE = BPCSFSCH.INF.BOOK_CENTRE;
        BPRFSCH.PRD_TYPE = BPCSFSCH.INF.PRD_TYPE;
        BPRFSCH.START_DATE = BPCSFSCH.INF.START_DATE;
        BPRFSCH.MATURITY_DATE = BPCSFSCH.INF.MATURITY_DATE;
        BPRFSCH.UCT_FLG = BPCSFSCH.INF.UCT_FLG;
        BPRFSCH.SETTLE_FREQ = BPCSFSCH.INF.SETTLE_FREQ;
        BPRFSCH.FREQ_COUNT = BPCSFSCH.INF.FREQ_COUNT;
        BPRFSCH.FIRST_CHG_DATE = BPCSFSCH.INF.FIRST_CHG_DATE;
        BPRFSCH.HOLI_OVER = BPCSFSCH.INF.HOLI_OVER;
        BPRFSCH.CAL_CODE1 = BPCSFSCH.INF.CAL_CODE1;
        BPRFSCH.HOLI_METHOD = BPCSFSCH.INF.HOLI_METHOD;
        BPRFSCH.CAL_CODE2 = BPCSFSCH.INF.CAL_CODE2;
        BPRFSCH.PAY_IND = BPCSFSCH.INF.PAY_IND;
        BPRFSCH.CASHFLOW_IND = BPCSFSCH.INF.CASHFLOW_IND;
        BPRFSCH.BANK_PORTF = BPCSFSCH.INF.BANK_PORTF;
        BPRFSCH.ACCRUAL_TYPE = BPCSFSCH.INF.ACCRUAL_TYPE;
        BPRFSCH.PRICE_METHOD = BPCSFSCH.INF.PRICE_METHOD;
        BPRFSCH.TXN_CCY = BPCSFSCH.INF.TXN_CCY;
        BPRFSCH.PRIN_AMT = BPCSFSCH.INF.TXN_AMT;
        BPRFSCH.REL_CTRT_SRC = BPCSFSCH.INF.REL_CTRT_SRC;
        BPRFSCH.REL_CTRT_NO = BPCSFSCH.INF.REL_CTRT_NO;
        BPRFSCH.REL_COL_NO = BPCSFSCH.INF.REL_COL_NO;
        BPRFSCH.REL_LMT_NO = BPCSFSCH.INF.REL_LMT_NO;
        BPRFSCH.FIR_DSCT = BPCSFSCH.INF.FIR_DSCT;
        BPRFSCH.PRMT_DAYS = BPCSFSCH.INF.PROMPT_DAYS;
        BPRFSCH.AMT_TYPE = BPCSFSCH.INF.AMT_TYPE;
        BPRFSCH.AGGR_TYPE = BPCSFSCH.INF.AGGR_TYPE;
        BPRFSCH.REF_CCY = BPCSFSCH.INF.REF_CCY;
        BPRFSCH.REF_METHOD = BPCSFSCH.INF.REF_METHOD;
        BPRFSCH.AUTO_CHG_FLAG = BPCSFSCH.INF.AUTO_CHG_FLAG;
        BPRFSCH.CHARGE_CCY = BPCSFSCH.INF.CHARGE_CCY;
        BPRFSCH.CHARGE_AMT = BPCSFSCH.INF.CHARGE_AMT;
        BPRFSCH.CHARGE_METHOD = BPCSFSCH.INF.CHARGE_METHOD;
        BPRFSCH.CR_TO_BR = BPCSFSCH.INF.BOOK_CENTRE;
        BPRFSCH.CHARGE_AC = BPCSFSCH.INF.CHARGE_AC;
        BPRFSCH.NOSTRO_AC = BPCSFSCH.INF.NOSTRO_CD;
        BPRFSCH.CHQ_NO = BPCSFSCH.INF.CHQ_NO;
        BPRFSCH.GL_MASTER1 = BPCSFSCH.INF.GL_MASTER1;
        BPRFSCH.GL_MASTER2 = BPCSFSCH.INF.GL_MASTER2;
        BPRFSCH.GL_MASTER3 = BPCSFSCH.INF.GL_MASTER3;
        BPRFSCH.GL_MASTER4 = BPCSFSCH.INF.GL_MASTER4;
        BPRFSCH.RESP_CENTER1 = BPCSFSCH.INF.DATE1[1-1].R_CENT;
        BPRFSCH.BR1 = BPCSFSCH.INF.DATE1[1-1].R_BR;
        BPRFSCH.PCT1 = BPCSFSCH.INF.DATE1[1-1].R_PCT_NS;
        BPRFSCH.RESP_CENTER2 = BPCSFSCH.INF.DATE1[2-1].R_CENT;
        BPRFSCH.BR2 = BPCSFSCH.INF.DATE1[2-1].R_BR;
        BPRFSCH.PCT2 = BPCSFSCH.INF.DATE1[2-1].R_PCT_NS;
        BPRFSCH.RESP_CENTER3 = BPCSFSCH.INF.DATE1[3-1].R_CENT;
        BPRFSCH.BR3 = BPCSFSCH.INF.DATE1[3-1].R_BR;
        BPRFSCH.PCT3 = BPCSFSCH.INF.DATE1[3-1].R_PCT_NS;
        BPRFSCH.RESP_CENTER4 = BPCSFSCH.INF.DATE1[4-1].R_CENT;
        BPRFSCH.BR4 = BPCSFSCH.INF.DATE1[4-1].R_BR;
        BPRFSCH.PCT4 = BPCSFSCH.INF.DATE1[4-1].R_PCT_NS;
        BPRFSCH.RESP_CENTER5 = BPCSFSCH.INF.DATE1[5-1].R_CENT;
        BPRFSCH.BR5 = BPCSFSCH.INF.DATE1[5-1].R_BR;
        BPRFSCH.PCT5 = BPCSFSCH.INF.DATE1[5-1].R_PCT_NS;
        BPRFSCH.FEE_STATUS = BPCSFSCH.INF.FEE_STATUS;
        BPRFSCH.REMARK = BPCSFSCH.INF.REMARK;
        BPRFSCH.FCHG_MIN_AMT = BPCSFSCH.INF.FCHG_MIN_AMT;
        BPRFSCH.CHG_CCY_REAL = BPCSFSCH.INF.CHG_CCY_REAL;
        BPRFSCH.CARD_PSBK_NO = BPCSFSCH.INF.CP_NO;
        BPRFSCH.SALE_DATE = BPCSFSCH.INF.SALE_DT;
        BPRFSCH.CCY_TYPE = BPCSFSCH.INF.CCY_TYPE;
        BPRFSCH.CREV_NO = BPCSFSCH.INF.CREV_NO;
        if (BPCSFSCH.FUNC == 'A') {
            if (BPCSFSCH.INF.START_DATE <= SCCGWA.COMM_AREA.AC_DATE 
                && !BPCSFSCH.INF.FEE_TYPE.equalsIgnoreCase(K_FEE_CODE)) {
                BPRFSCH.FEE_STATUS = '1';
            }
            BPRFSCH.CREATE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPRFSCH.CREATE_TELL = SCCGWA.COMM_AREA.TL_ID;
        }
        BPRFSCH.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRFSCH.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
        BPRFSCH.SUP_TEL1 = SCCGWA.COMM_AREA.SUP1_ID;
        BPRFSCH.SUP_TEL2 = SCCGWA.COMM_AREA.SUP2_ID;
    }
    public void R001_TRANS_HIS_OLD_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCHFSCO);
        BPCHFSCO.KEY.CTRT_NO = BPRFSCH.KEY.CTRT_NO;
        BPCHFSCO.CI_NO = BPRFSCH.CI_NO;
        BPCHFSCO.CTRT_DESC = BPRFSCH.CTRT_DESC;
        BPCHFSCO.CTRT_TYPE = BPRFSCH.CTRT_TYPE;
        BPCHFSCO.FEE_TYPE = BPRFSCH.FEE_TYPE;
        BPCHFSCO.BOOK_CENTRE = BPRFSCH.BOOK_CENTRE;
        BPCHFSCO.PRD_TYPE = BPRFSCH.PRD_TYPE;
        BPCHFSCO.START_DATE = BPRFSCH.START_DATE;
        BPCHFSCO.MATURITY_DATE = BPRFSCH.MATURITY_DATE;
        BPCHFSCO.SETTLE_FREQ = BPRFSCH.SETTLE_FREQ;
        BPCHFSCO.FREQ_COUNT = BPRFSCH.FREQ_COUNT;
        BPCHFSCO.FIRST_CHG_DATE = BPRFSCH.FIRST_CHG_DATE;
        BPCHFSCO.HOLI_OVER = BPRFSCH.HOLI_OVER;
        BPCHFSCO.CAL_CODE1 = BPRFSCH.CAL_CODE1;
        BPCHFSCO.HOLI_METHOD = BPRFSCH.HOLI_METHOD;
        BPCHFSCO.CAL_CODE2 = BPRFSCH.CAL_CODE2;
        BPCHFSCO.PAY_IND = BPRFSCH.PAY_IND;
        BPCHFSCO.CASHFLOW_IND = BPRFSCH.CASHFLOW_IND;
        BPCHFSCO.BANK_PORTF = BPRFSCH.BANK_PORTF;
        BPCHFSCO.ACCRUAL_TYPE = BPRFSCH.ACCRUAL_TYPE;
        BPCHFSCO.PRICE_METHOD = BPRFSCH.PRICE_METHOD;
        BPCHFSCO.TXN_CCY = BPRFSCH.TXN_CCY;
        BPCHFSCO.PRIN_AMT = BPRFSCH.PRIN_AMT;
        BPCHFSCO.REL_CTRT_SRC = BPRFSCH.REL_CTRT_SRC;
        BPCHFSCO.REL_CTRT_NO = BPRFSCH.REL_CTRT_NO;
        BPCHFSCO.REL_COL_NO = BPRFSCH.REL_COL_NO;
        BPCHFSCO.REL_LMT_NO = BPRFSCH.REL_LMT_NO;
        BPCHFSCO.FIR_DSCT = BPRFSCH.FIR_DSCT;
        BPCHFSCO.PROMPT_DAYS = BPRFSCH.PRMT_DAYS;
        BPCHFSCO.AMT_TYPE = BPRFSCH.AMT_TYPE;
        BPCHFSCO.AGGR_TYPE = BPRFSCH.AGGR_TYPE;
        BPCHFSCO.REF_CCY = BPRFSCH.REF_CCY;
        BPCHFSCO.REF_METHOD = BPRFSCH.REF_METHOD;
        BPCHFSCO.AUTO_CHG_FLAG = BPRFSCH.AUTO_CHG_FLAG;
        BPCHFSCO.CHARGE_CCY = BPRFSCH.CHARGE_CCY;
        BPCHFSCO.CHARGE_AMT = BPRFSCH.CHARGE_AMT;
        BPCHFSCO.CHARGE_METHOD = BPRFSCH.CHARGE_METHOD;
        BPCHFSCO.CHARGE_AC = BPRFSCH.CHARGE_AC;
        BPCHFSCO.CHQ_NO = BPRFSCH.CHQ_NO;
        BPCHFSCO.NOSTRO_AC = BPRFSCH.NOSTRO_AC;
        BPCHFSCO.GL_MASTER1 = BPRFSCH.GL_MASTER1;
        BPCHFSCO.GL_MASTER2 = BPRFSCH.GL_MASTER2;
        BPCHFSCO.GL_MASTER3 = BPRFSCH.GL_MASTER3;
        BPCHFSCO.GL_MASTER4 = BPRFSCH.GL_MASTER4;
        BPCHFSCO.FEE_STATUS = BPRFSCH.FEE_STATUS;
        BPCHFSCO.REMARK = BPRFSCH.REMARK;
        BPCHFSCO.FCHG_MIN_AMT = BPRFSCH.FCHG_MIN_AMT;
        BPCHFSCO.CHG_CCY_REAL = BPRFSCH.CHG_CCY_REAL;
        BPCHFSCO.CP_NO = BPRFSCH.CARD_PSBK_NO;
        BPCHFSCO.SALE_DT = BPRFSCH.SALE_DATE;
        BPCHFSCO.CCY_TYPE = BPRFSCH.CCY_TYPE;
        BPCHFSCO.CREV_NO = BPRFSCH.CREV_NO;
        BPCHFSCO.LAST_SETT_DATE = BPRFSCH.LAST_SETT_DATE;
        BPCHFSCO.CREATE_DATE = BPRFSCH.CREATE_DATE;
        BPCHFSCO.CREATE_TELL = BPRFSCH.CREATE_TELL;
        BPCHFSCO.UPDTBL_DATE = BPRFSCH.UPDTBL_DATE;
        BPCHFSCO.LAST_TELL = BPRFSCH.LAST_TELL;
        BPCHFSCO.SUP_TEL1 = BPRFSCH.SUP_TEL1;
        BPCHFSCO.SUP_TEL2 = BPRFSCH.SUP_TEL2;
    }
    public void R001_TRANS_HIS_OLD_DATA_FCPH() throws IOException,SQLException,Exception {
        BPCHFSCO.MULTI = BPRFCPH.MULTI;
        BPCHFSCO.INT_BAS = BPRFCPH.INT_BAS;
        BPCHFSCO.UP_AMT_1 = BPRFCPH.UP_AMT_1;
        BPCHFSCO.UP_CNT_1 = BPRFCPH.UP_CNT_1;
        BPCHFSCO.UP_PCT_1 = BPRFCPH.UP_PCT_1;
        BPCHFSCO.FEE_AMT_1 = BPRFCPH.FEE_AMT_1;
        BPCHFSCO.FEE_RATE_1 = BPRFCPH.FEE_RATE_1;
        BPCHFSCO.UP_AMT_2 = BPRFCPH.UP_AMT_2;
        BPCHFSCO.UP_CNT_2 = BPRFCPH.UP_CNT_2;
        BPCHFSCO.UP_PCT_2 = BPRFCPH.UP_PCT_2;
        BPCHFSCO.FEE_AMT_2 = BPRFCPH.FEE_AMT_2;
        BPCHFSCO.FEE_RATE_2 = BPRFCPH.FEE_RATE_2;
        BPCHFSCO.UP_AMT_3 = BPRFCPH.UP_AMT_3;
        BPCHFSCO.UP_CNT_3 = BPRFCPH.UP_CNT_3;
        BPCHFSCO.UP_PCT_3 = BPRFCPH.UP_PCT_3;
        BPCHFSCO.FEE_AMT_3 = BPRFCPH.FEE_AMT_3;
        BPCHFSCO.FEE_RATE_3 = BPRFCPH.FEE_RATE_3;
        BPCHFSCO.UP_AMT_4 = BPRFCPH.UP_AMT_4;
        BPCHFSCO.UP_CNT_4 = BPRFCPH.UP_CNT_4;
        BPCHFSCO.UP_PCT_4 = BPRFCPH.UP_PCT_4;
        BPCHFSCO.FEE_AMT_4 = BPRFCPH.FEE_AMT_4;
        BPCHFSCO.FEE_RATE_4 = BPRFCPH.FEE_RATE_4;
        BPCHFSCO.UP_AMT_5 = BPRFCPH.UP_AMT_5;
        BPCHFSCO.UP_CNT_5 = BPRFCPH.UP_CNT_5;
        BPCHFSCO.UP_PCT_5 = BPRFCPH.UP_PCT_5;
        BPCHFSCO.FEE_AMT_5 = BPRFCPH.FEE_AMT_5;
        BPCHFSCO.FEE_RATE_5 = BPRFCPH.FEE_RATE_5;
    }
    public void R001_TRANS_HIS_NEW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCHFSCH);
        BPCHFSCH.KEY.CTRT_NO = BPCSFSCH.KEY.CTRT_NO;
        BPCHFSCH.CI_NO = BPCSFSCH.INF.CI_NO;
        BPCHFSCH.CTRT_DESC = BPCSFSCH.INF.CTRT_DESC;
        BPCHFSCH.CTRT_TYPE = BPCSFSCH.INF.CTRT_TYPE;
        BPCHFSCH.FEE_TYPE = BPCSFSCH.INF.FEE_TYPE;
        BPCHFSCH.BOOK_CENTRE = BPCSFSCH.INF.BOOK_CENTRE;
        BPCHFSCH.PRD_TYPE = BPCSFSCH.INF.PRD_TYPE;
        BPCHFSCH.START_DATE = BPCSFSCH.INF.START_DATE;
        BPCHFSCH.MATURITY_DATE = BPCSFSCH.INF.MATURITY_DATE;
        BPCHFSCH.SETTLE_FREQ = BPCSFSCH.INF.SETTLE_FREQ;
        BPCHFSCH.FREQ_COUNT = BPCSFSCH.INF.FREQ_COUNT;
        BPCHFSCH.FIRST_CHG_DATE = BPCSFSCH.INF.FIRST_CHG_DATE;
        BPCHFSCH.HOLI_OVER = BPCSFSCH.INF.HOLI_OVER;
        BPCHFSCH.CAL_CODE1 = BPCSFSCH.INF.CAL_CODE1;
        BPCHFSCH.HOLI_METHOD = BPCSFSCH.INF.HOLI_METHOD;
        BPCHFSCH.CAL_CODE2 = BPCSFSCH.INF.CAL_CODE1;
        BPCHFSCH.PAY_IND = BPCSFSCH.INF.PAY_IND;
        BPCHFSCH.CASHFLOW_IND = BPCSFSCH.INF.CASHFLOW_IND;
        BPCHFSCH.BANK_PORTF = BPCSFSCH.INF.BANK_PORTF;
        BPCHFSCH.ACCRUAL_TYPE = BPCSFSCH.INF.ACCRUAL_TYPE;
        BPCHFSCH.PRICE_METHOD = BPCSFSCH.INF.PRICE_METHOD;
        BPCHFSCH.TXN_CCY = BPCSFSCH.INF.TXN_CCY;
        BPCHFSCH.PRIN_AMT = BPCSFSCH.INF.TXN_AMT;
        BPCHFSCH.REL_CTRT_SRC = BPCSFSCH.INF.REL_CTRT_SRC;
        BPCHFSCH.REL_CTRT_NO = BPCSFSCH.INF.REL_CTRT_NO;
        BPCHFSCH.REL_COL_NO = BPCSFSCH.INF.REL_COL_NO;
        BPCHFSCH.REL_LMT_NO = BPCSFSCH.INF.REL_LMT_NO;
        BPCHFSCH.FIR_DSCT = BPCSFSCH.INF.FIR_DSCT;
        BPCHFSCH.PROMPT_DAYS = BPCSFSCH.INF.PROMPT_DAYS;
        BPCHFSCH.AMT_TYPE = BPCSFSCH.INF.AMT_TYPE;
        BPCHFSCH.MULTI = BPCSFSCH.INF.MULTI;
        BPCHFSCH.INT_BAS = BPCSFSCH.INF.INT_BAS;
        BPCHFSCH.AGGR_TYPE = BPCSFSCH.INF.AGGR_TYPE;
        BPCHFSCH.REF_CCY = BPCSFSCH.INF.REF_CCY;
        BPCHFSCH.REF_METHOD = BPCSFSCH.INF.REF_METHOD;
        BPCHFSCH.UP_AMT_1 = BPCSFSCH.INF.DATE[1-1].REF_AMT;
        BPCHFSCH.UP_CNT_1 = 0;
        BPCHFSCH.UP_PCT_1 = BPCSFSCH.INF.DATE[1-1].REF_PCT;
        BPCHFSCH.AGG_MTH_1 = BPCSFSCH.INF.DATE[1-1].AGG_MTH;
        BPCHFSCH.FEE_AMT_1 = BPCSFSCH.INF.DATE[1-1].FEE_AMT;
        BPCHFSCH.FEE_RATE_1 = BPCSFSCH.INF.DATE[1-1].FEE_RATE;
        BPCHFSCH.UP_AMT_2 = BPCSFSCH.INF.DATE[2-1].REF_AMT;
        BPCHFSCH.UP_CNT_2 = 0;
        BPCHFSCH.UP_PCT_2 = BPCSFSCH.INF.DATE[2-1].REF_PCT;
        BPCHFSCH.AGG_MTH_2 = BPCSFSCH.INF.DATE[2-1].AGG_MTH;
        BPCHFSCH.FEE_AMT_2 = BPCSFSCH.INF.DATE[2-1].FEE_AMT;
        BPCHFSCH.FEE_RATE_2 = BPCSFSCH.INF.DATE[2-1].FEE_RATE;
        BPCHFSCH.UP_AMT_3 = BPCSFSCH.INF.DATE[3-1].REF_AMT;
        BPCHFSCH.UP_CNT_3 = 0;
        BPCHFSCH.UP_PCT_3 = BPCSFSCH.INF.DATE[3-1].REF_PCT;
        BPCHFSCH.AGG_MTH_3 = BPCSFSCH.INF.DATE[3-1].AGG_MTH;
        BPCHFSCH.FEE_AMT_3 = BPCSFSCH.INF.DATE[3-1].FEE_AMT;
        BPCHFSCH.FEE_RATE_3 = BPCSFSCH.INF.DATE[3-1].FEE_RATE;
        BPCHFSCH.UP_AMT_4 = BPCSFSCH.INF.DATE[4-1].REF_AMT;
        BPCHFSCH.UP_CNT_4 = 0;
        BPCHFSCH.UP_PCT_4 = BPCSFSCH.INF.DATE[4-1].REF_PCT;
        BPCHFSCH.AGG_MTH_4 = BPCSFSCH.INF.DATE[4-1].AGG_MTH;
        BPCHFSCH.FEE_AMT_4 = BPCSFSCH.INF.DATE[4-1].FEE_AMT;
        BPCHFSCH.FEE_RATE_4 = BPCSFSCH.INF.DATE[4-1].FEE_RATE;
        BPCHFSCH.UP_AMT_5 = BPCSFSCH.INF.DATE[5-1].REF_AMT;
        BPCHFSCH.UP_CNT_5 = 0;
        BPCHFSCH.UP_PCT_5 = BPCSFSCH.INF.DATE[5-1].REF_PCT;
        BPCHFSCH.AGG_MTH_5 = BPCSFSCH.INF.DATE[5-1].AGG_MTH;
        BPCHFSCH.FEE_AMT_5 = BPCSFSCH.INF.DATE[5-1].FEE_AMT;
        BPCHFSCH.FEE_RATE_5 = BPCSFSCH.INF.DATE[5-1].FEE_RATE;
        BPCHFSCH.AUTO_CHG_FLAG = BPCSFSCH.INF.AUTO_CHG_FLAG;
        BPCHFSCH.CHARGE_CCY = BPCSFSCH.INF.CHARGE_CCY;
        BPCHFSCH.CHARGE_AMT = BPCSFSCH.INF.CHARGE_AMT;
        BPCHFSCH.CHARGE_METHOD = BPCSFSCH.INF.CHARGE_METHOD;
        BPCHFSCH.CHARGE_AC = BPCSFSCH.INF.CHARGE_AC;
        BPCHFSCH.CHQ_NO = BPCSFSCH.INF.CHQ_NO;
        BPCHFSCH.NOSTRO_AC = BPCSFSCH.INF.NOSTRO_CD;
        BPCHFSCH.GL_MASTER1 = BPCSFSCH.INF.GL_MASTER1;
        BPCHFSCH.GL_MASTER2 = BPCSFSCH.INF.GL_MASTER2;
        BPCHFSCH.GL_MASTER3 = BPCSFSCH.INF.GL_MASTER3;
        BPCHFSCH.GL_MASTER4 = BPCSFSCH.INF.GL_MASTER4;
        BPCHFSCH.FEE_STATUS = BPCSFSCH.INF.FEE_STATUS;
        BPCHFSCH.R_CENT_1 = BPCSFSCH.INF.DATE1[1-1].R_CENT;
        BPCHFSCH.R_BR_1 = BPCSFSCH.INF.DATE1[1-1].R_BR;
        BPCHFSCH.R_PCT_NS_1 = BPCSFSCH.INF.DATE1[1-1].R_PCT_NS;
        BPCHFSCH.R_CENT_2 = BPCSFSCH.INF.DATE1[2-1].R_CENT;
        BPCHFSCH.R_BR_2 = BPCSFSCH.INF.DATE1[2-1].R_BR;
        BPCHFSCH.R_PCT_NS_2 = BPCSFSCH.INF.DATE1[2-1].R_PCT_NS;
        BPCHFSCH.R_CENT_3 = BPCSFSCH.INF.DATE1[3-1].R_CENT;
        BPCHFSCH.R_BR_3 = BPCSFSCH.INF.DATE1[3-1].R_BR;
        BPCHFSCH.R_PCT_NS_3 = BPCSFSCH.INF.DATE1[3-1].R_PCT_NS;
        BPCHFSCH.R_CENT_4 = BPCSFSCH.INF.DATE1[4-1].R_CENT;
        BPCHFSCH.R_BR_4 = BPCSFSCH.INF.DATE1[4-1].R_BR;
        BPCHFSCH.R_PCT_NS_4 = BPCSFSCH.INF.DATE1[4-1].R_PCT_NS;
        BPCHFSCH.R_CENT_5 = BPCSFSCH.INF.DATE1[5-1].R_CENT;
        BPCHFSCH.R_BR_5 = BPCSFSCH.INF.DATE1[5-1].R_BR;
        BPCHFSCH.R_PCT_NS_5 = BPCSFSCH.INF.DATE1[5-1].R_PCT_NS;
        BPCHFSCH.REMARK = BPCSFSCH.INF.REMARK;
        BPCHFSCH.FCHG_MIN_AMT = BPCSFSCH.INF.FCHG_MIN_AMT;
        BPCHFSCH.CHG_CCY_REAL = BPCSFSCH.INF.CHG_CCY_REAL;
        BPCHFSCH.CP_NO = BPCSFSCH.INF.CP_NO;
        BPCHFSCH.SALE_DT = BPCSFSCH.INF.SALE_DT;
        BPCHFSCH.CCY_TYPE = BPCSFSCH.INF.CCY_TYPE;
        BPCHFSCH.CREV_NO = BPCSFSCH.INF.CREV_NO;
        BPCHFSCH.LAST_SETT_DATE = BPRFSCH.LAST_SETT_DATE;
        BPCHFSCH.CREATE_DATE = BPRFSCH.CREATE_DATE;
        BPCHFSCH.CREATE_TELL = BPRFSCH.CREATE_TELL;
        BPCHFSCH.UPDTBL_DATE = BPRFSCH.UPDTBL_DATE;
        BPCHFSCH.LAST_TELL = BPRFSCH.LAST_TELL;
        BPCHFSCH.SUP_TEL1 = BPRFSCH.SUP_TEL1;
        BPCHFSCH.SUP_TEL2 = BPRFSCH.SUP_TEL2;
    }
    public void R100_TRANS_DATA_FCPH() throws IOException,SQLException,Exception {
        BPRFCPH.KEY.CTRT_NO = BPCSFSCH.KEY.CTRT_NO;
        BPRFCPH.KEY.VALUE_DATE = BPCSFSCH.INF.START_DATE;
        CEP.TRC(SCCGWA, BPCSFSCH.INF.INT_BAS);
        if (BPCSFSCH.INF.INT_BAS.trim().length() == 0) {
            BPCSFSCH.INF.INT_BAS = "02";
        }
        if ((BPCSFSCH.INF.INT_BAS.equalsIgnoreCase("01") 
            || BPCSFSCH.INF.INT_BAS.equalsIgnoreCase("02") 
            || BPCSFSCH.INF.INT_BAS.equalsIgnoreCase("03"))) {
            BPRFCPH.INT_BAS = BPCSFSCH.INF.INT_BAS;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CALR_STD_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPRFCPH.MULTI = BPCSFSCH.INF.MULTI;
        BPRFCPH.AGGR_TYPE = BPCSFSCH.INF.AGGR_TYPE;
        BPRFCPH.REF_CCY = BPCSFSCH.INF.REF_CCY;
        BPRFCPH.REF_METHOD = BPCSFSCH.INF.REF_METHOD;
        BPRFCPH.UP_AMT_1 = BPCSFSCH.INF.DATE[1-1].REF_AMT;
        BPRFCPH.UP_CNT_1 = 0;
        BPRFCPH.UP_PCT_1 = BPCSFSCH.INF.DATE[1-1].REF_PCT;
        BPRFCPH.AGG_MTH_1 = BPCSFSCH.INF.DATE[1-1].AGG_MTH;
        BPRFCPH.FEE_AMT_1 = BPCSFSCH.INF.DATE[1-1].FEE_AMT;
        BPRFCPH.FEE_RATE_1 = BPCSFSCH.INF.DATE[1-1].FEE_RATE;
        BPRFCPH.UP_AMT_2 = BPCSFSCH.INF.DATE[2-1].REF_AMT;
        BPRFCPH.UP_CNT_2 = 0;
        BPRFCPH.UP_PCT_2 = BPCSFSCH.INF.DATE[2-1].REF_PCT;
        BPRFCPH.AGG_MTH_2 = BPCSFSCH.INF.DATE[2-1].AGG_MTH;
        BPRFCPH.FEE_AMT_2 = BPCSFSCH.INF.DATE[2-1].FEE_AMT;
        BPRFCPH.FEE_RATE_2 = BPCSFSCH.INF.DATE[2-1].FEE_RATE;
        BPRFCPH.UP_AMT_3 = BPCSFSCH.INF.DATE[3-1].REF_AMT;
        BPRFCPH.UP_CNT_3 = 0;
        BPRFCPH.UP_PCT_3 = BPCSFSCH.INF.DATE[3-1].REF_PCT;
        BPRFCPH.AGG_MTH_3 = BPCSFSCH.INF.DATE[3-1].AGG_MTH;
        BPRFCPH.FEE_AMT_3 = BPCSFSCH.INF.DATE[3-1].FEE_AMT;
        BPRFCPH.FEE_RATE_3 = BPCSFSCH.INF.DATE[3-1].FEE_RATE;
        BPRFCPH.UP_AMT_4 = BPCSFSCH.INF.DATE[4-1].REF_AMT;
        BPRFCPH.UP_CNT_4 = 0;
        BPRFCPH.UP_PCT_4 = BPCSFSCH.INF.DATE[4-1].REF_PCT;
        BPRFCPH.AGG_MTH_4 = BPCSFSCH.INF.DATE[4-1].AGG_MTH;
        BPRFCPH.FEE_AMT_4 = BPCSFSCH.INF.DATE[4-1].FEE_AMT;
        BPRFCPH.FEE_RATE_4 = BPCSFSCH.INF.DATE[4-1].FEE_RATE;
        BPRFCPH.UP_AMT_5 = BPCSFSCH.INF.DATE[5-1].REF_AMT;
        BPRFCPH.UP_CNT_5 = 0;
        BPRFCPH.UP_PCT_5 = BPCSFSCH.INF.DATE[5-1].REF_PCT;
        BPRFCPH.AGG_MTH_5 = BPCSFSCH.INF.DATE[5-1].AGG_MTH;
        BPRFCPH.FEE_AMT_5 = BPCSFSCH.INF.DATE[5-1].FEE_AMT;
        BPRFCPH.FEE_RATE_5 = BPCSFSCH.INF.DATE[5-1].FEE_RATE;
        BPRFCPH.REMARK = " ";
        if (BPCSFSCH.FUNC == 'A') {
            BPRFCPH.CREATE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPRFCPH.CREATE_TELL = SCCGWA.COMM_AREA.TL_ID;
        }
        BPRFCPH.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRFCPH.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
        BPRFCPH.SUP_TEL1 = SCCGWA.COMM_AREA.SUP1_ID;
        BPRFCPH.SUP_TEL2 = SCCGWA.COMM_AREA.SUP2_ID;
        CEP.TRC(SCCGWA, BPRFCPH.KEY.CTRT_NO);
        CEP.TRC(SCCGWA, BPRFCPH.KEY.VALUE_DATE);
        CEP.TRC(SCCGWA, BPRFCPH.INT_BAS);
        CEP.TRC(SCCGWA, BPRFCPH.MULTI);
        CEP.TRC(SCCGWA, BPRFCPH.AGGR_TYPE);
        CEP.TRC(SCCGWA, BPRFCPH.REF_CCY);
        CEP.TRC(SCCGWA, BPRFCPH.REF_METHOD);
        CEP.TRC(SCCGWA, BPCSFSCH.INF.DATE[1-1].REF_AMT);
        CEP.TRC(SCCGWA, BPRFCPH.UP_AMT_1);
        CEP.TRC(SCCGWA, BPRFCPH.UP_CNT_1);
        CEP.TRC(SCCGWA, BPRFCPH.UP_PCT_1);
        CEP.TRC(SCCGWA, BPRFCPH.AGG_MTH_1);
        CEP.TRC(SCCGWA, BPRFCPH.FEE_AMT_1);
        CEP.TRC(SCCGWA, BPRFCPH.FEE_RATE_1);
        CEP.TRC(SCCGWA, BPRFCPH.UP_AMT_2);
        CEP.TRC(SCCGWA, BPRFCPH.UP_CNT_2);
        CEP.TRC(SCCGWA, BPRFCPH.UP_PCT_2);
        CEP.TRC(SCCGWA, BPRFCPH.AGG_MTH_2);
        CEP.TRC(SCCGWA, BPRFCPH.FEE_AMT_2);
        CEP.TRC(SCCGWA, BPRFCPH.FEE_RATE_2);
        CEP.TRC(SCCGWA, BPRFCPH.UP_AMT_3);
        CEP.TRC(SCCGWA, BPRFCPH.UP_CNT_3);
        CEP.TRC(SCCGWA, BPRFCPH.UP_PCT_3);
        CEP.TRC(SCCGWA, BPRFCPH.AGG_MTH_3);
        CEP.TRC(SCCGWA, BPRFCPH.FEE_AMT_3);
        CEP.TRC(SCCGWA, BPRFCPH.FEE_RATE_3);
        CEP.TRC(SCCGWA, BPRFCPH.UP_AMT_4);
        CEP.TRC(SCCGWA, BPRFCPH.UP_CNT_4);
        CEP.TRC(SCCGWA, BPRFCPH.UP_PCT_4);
        CEP.TRC(SCCGWA, BPRFCPH.AGG_MTH_4);
        CEP.TRC(SCCGWA, BPRFCPH.FEE_AMT_4);
        CEP.TRC(SCCGWA, BPRFCPH.FEE_RATE_4);
        CEP.TRC(SCCGWA, BPRFCPH.UP_AMT_5);
        CEP.TRC(SCCGWA, BPRFCPH.UP_CNT_5);
        CEP.TRC(SCCGWA, BPRFCPH.UP_PCT_5);
        CEP.TRC(SCCGWA, BPRFCPH.AGG_MTH_5);
        CEP.TRC(SCCGWA, BPRFCPH.FEE_AMT_5);
        CEP.TRC(SCCGWA, BPRFCPH.FEE_RATE_5);
        CEP.TRC(SCCGWA, BPRFCPH.REMARK);
        CEP.TRC(SCCGWA, BPRFCPH.CREATE_DATE);
        CEP.TRC(SCCGWA, BPRFCPH.CREATE_TELL);
        CEP.TRC(SCCGWA, BPRFCPH.UPDTBL_DATE);
        CEP.TRC(SCCGWA, BPRFCPH.LAST_TELL);
        CEP.TRC(SCCGWA, BPRFCPH.SUP_TEL1);
        CEP.TRC(SCCGWA, BPRFCPH.SUP_TEL2);
    }
    public void S000_CALL_BPZFUBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-U-MAINTAIN-FBAS", BPCOUBAS);
    }
    public void S000_CALL_BPZRACCR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-ACCR", BPCRACCR);
    }
    public void S000_CALL_BPZPEBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-BASIC-EWA", BPCPEBAS);
        if (BPCPEBAS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPEBAS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONLINE-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQBCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-BCH", BPCPQBCH);
        if (BPCPQBCH.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQBCH.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZGCTNO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-GENERTE-CTNO", BPCCGCT);
        if (BPCCGCT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCCGCT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFSCHC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-F-GEN-SCHD", BPCFSCHC);
        if (BPCFSCHC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFSCHC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRSCHD() throws IOException,SQLException,Exception {
        BPCRSCHD.INFO.POINTER = BPRFSCHD;
        BPCRSCHD.INFO.REC_LEN = 376;
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-FEESCHD", BPCRSCHD);
        if (BPCRSCHD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRSCHD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRFSCH() throws IOException,SQLException,Exception {
        BPCRFSCH.INFO.POINTER = BPRFSCH;
        BPCRFSCH.INFO.REC_LEN = 816;
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-FEESCH", BPCRFSCH);
        if (BPCRFSCH.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRFSCH.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRCPHM() throws IOException,SQLException,Exception {
        BPCRCPHM.INFO.POINTER = BPRFCPH;
        BPCRCPHM.INFO.REC_LEN = 509;
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-CAL-PARM", BPCRCPHM);
        if (BPCRCPHM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRCPHM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-GET-SEQ", BPCSGSEQ);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZUMENT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCUMENT.PROC_DATA.FUNC_CODE);
        CEP.TRC(SCCGWA, BPCUMENT.DIARY_DATA.CONTRACT_NO);
        CEP.TRC(SCCGWA, BPCUMENT.DIARY_DATA.DIARY_TYPE);
        CEP.TRC(SCCGWA, BPCUMENT.DIARY_DATA.DIARY_DATE);
        CEP.TRC(SCCGWA, BPCUMENT.DIARY_DATA.AMOUNT1);
        CEP.TRC(SCCGWA, BPCUMENT.DIARY_DATA.RATEX3);
        CEP.TRC(SCCGWA, BPCUMENT.DIARY_DATA.OTH_CN_NO);
        IBS.CALLCPN(SCCGWA, "BP-U-MENT-MAINT", BPCUMENT);
        if (BPCUMENT.PROC_DATA.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUMENT.PROC_DATA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZUFEEC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-FEE-CAL", BPCUFEEC);
        if (BPCUFEEC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUFEEC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "CALL SCSSCLDT FAIL, RC=" + SCCCLDT.RC;
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZUGLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-MAINT-GLM", BPCUGLM);
        if (BPCUGLM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUGLM.RC);
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
