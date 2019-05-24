package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.LN.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSSCHD {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String PGM_SCSSCLDT = "SCSSCLDT";
    String K_OUTPUT_FMT_X = "BPX01";
    String K_OUTPUT_FMT_9 = "BP070";
    String K_OUTPUT_FMT_7 = "BP074";
    String K_OUTPUT_FMT_8 = "BP091";
    int K_MAX_COL = 99;
    int K_MAX_ROW = 50;
    int K_COL_CNT = 20;
    String K_HIS_REMARK = "MAINTAIN FEE DETAIL SCHEDULE";
    String K_HIS_COPYBOOK = "BPRFSCHD";
    String K_FEE_CODE = "FII01";
    String WS_ERR_MSG = " ";
    short WS_IDX = 0;
    short WS_CNT = 0;
    short WS_CNT1 = 0;
    short WS_DIARY_CNT = 0;
    int WS_START_DATE = 0;
    int WS_END_DATE = 0;
    int WS_PRE_SETTLE_DATE = 0;
    int WS_PRE_SETTLE_DATE2 = 0;
    int WS_NXT_SETTLE_DATE = 0;
    int WS_NXT_SETTLE_DATE2 = 0;
    int WS_LST_SETTLE_DATE = 0;
    int WS_NEXT_SETTLE_DATE = 0;
    double WS_TOT_AMT = 0;
    short WS_SCHD_COUNT = 0;
    String WS_DIARY_TYPE = " ";
    double WS_REAL_CHG_AMT = 0;
    double WS_TMP_AMT = 0;
    int WS_PRE_SETTLE_DT = 0;
    BPZSSCHD_WS_LIST_DATA WS_LIST_DATA = new BPZSSCHD_WS_LIST_DATA();
    BPZSSCHD_WS_REVE_DATA WS_REVE_DATA = new BPZSSCHD_WS_REVE_DATA();
    double WS_EX_RATE = 0;
    double WS_TEMP_FEE_AMT = 0;
    double WS_UFEEC_FEE_AMT = 0;
    char WS_FOUND_FLAG = ' ';
    char WS_UPD_FLAG = ' ';
    char WS_FINAL_FLAG = ' ';
    char WS_FINAL_CHG_FLAG = ' ';
    char WS_BROWSE_FLAG = ' ';
    char WS_CAL_LN_FEE_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPRFSCH BPRFSCH = new BPRFSCH();
    BPRFSCHD BPRFSCHD = new BPRFSCHD();
    BPRFSCHD BPRSCHDO = new BPRFSCHD();
    BPCRFSCH BPCRFSCH = new BPCRFSCH();
    BPCSCHD1 BPCSCHD1 = new BPCSCHD1();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCUCNGM BPCUCNGM = new BPCUCNGM();
    BPCPQBCH BPCPQBCH = new BPCPQBCH();
    BPCRSCHD BPCRSCHD = new BPCRSCHD();
    BPCUFEEC BPCUFEEC = new BPCUFEEC();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    BPCUMENT BPCUMENT = new BPCUMENT();
    CICCUST CICCUST = new CICCUST();
    BPCSSET1 BPCSSET1 = new BPCSSET1();
    LNCSSCHE LNCSSCHE = new LNCSSCHE();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCSSCHD BPCSSCHD;
    public void MP(SCCGWA SCCGWA, BPCSSCHD BPCSSCHD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSSCHD = BPCSSCHD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSSCHD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRFSCH);
        IBS.init(SCCGWA, BPRFSCHD);
        IBS.init(SCCGWA, BPCRFSCH);
        IBS.init(SCCGWA, BPCUFEEC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSSCHD.FUNC);
        CEP.TRC(SCCGWA, BPCSSCHD.FUNC_OPT);
        CEP.TRC(SCCGWA, BPCSSCHD.KEY.CTRT_NO);
        CEP.TRC(SCCGWA, BPCSSCHD.KEY.SETTLE_DATE);
        B001_QUERY_FSCH();
        if (pgmRtn) return;
        if (BPCSSCHD.FUNC == 'Q') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
            if (BPCSSCHD.FUNC_OPT == 'R') {
                CEP.TRC(SCCGWA, "1111");
                B089_DATA_OUTPUT_REVE();
                if (pgmRtn) return;
            } else if (BPCSSCHD.FUNC_OPT == 'S') {
                CEP.TRC(SCCGWA, "2222");
                B089_DATA_OUTPUT_SETT();
                if (pgmRtn) return;
            } else {
                CEP.TRC(SCCGWA, "3333");
                B090_DATA_OUTPUT();
                if (pgmRtn) return;
            }
        } else if (BPCSSCHD.FUNC == 'A') {
            B002_CHECK_INPUT();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
                B020_CREATE_PROCESS_CN();
                if (pgmRtn) return;
            } else {
                B020_CREATE_PROCESS();
                if (pgmRtn) return;
            }
            B080_HISTORY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
            B088_WF_REF_NO();
            if (pgmRtn) return;
        } else if (BPCSSCHD.FUNC == 'U') {
            B002_CHECK_INPUT();
            if (pgmRtn) return;
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
            B080_HISTORY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
            B088_WF_REF_NO();
            if (pgmRtn) return;
        } else if (BPCSSCHD.FUNC == 'D') {
            if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
                B040_DELETE_PROCESS_CN();
                if (pgmRtn) return;
            } else {
                B040_DELETE_PROCESS();
                if (pgmRtn) return;
            }
            B080_HISTORY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
            B088_WF_REF_NO();
            if (pgmRtn) return;
        } else if (BPCSSCHD.FUNC == '1'
            || BPCSSCHD.FUNC == '2'
            || BPCSSCHD.FUNC == '3') {
            B050_BROWSER_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B001_QUERY_FSCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFSCH);
        IBS.init(SCCGWA, BPCRFSCH);
        BPRFSCH.KEY.CTRT_NO = BPCSSCHD.KEY.CTRT_NO;
        BPCRFSCH.INFO.FUNC = 'Q';
        S000_CALL_BPZRFSCH();
        if (pgmRtn) return;
        if (BPCRFSCH.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FSCH_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPRFSCH.REL_CTRT_NO);
        if (BPRFSCH.REL_CTRT_NO.trim().length() > 0 
            && (BPRFSCH.AMT_TYPE == 11 
            || BPRFSCH.AMT_TYPE == 12 
            || BPRFSCH.AMT_TYPE == 13)) {
            CEP.TRC(SCCGWA, "GET 11 12 13 LN AMT");
            IBS.init(SCCGWA, LNCSSCHE);
            LNCSSCHE.DATA_AREA.CONTRACT_NO = BPRFSCH.REL_CTRT_NO;
            LNCSSCHE.CUR_FLG = 'M';
            S000_CALL_LNZSSCHE();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, LNCSSCHE.OUT_INFO[1-1].O_PAY_AMT);
            CEP.TRC(SCCGWA, LNCSSCHE.OUT_INFO[1-1].O_PAY_PRIN);
            CEP.TRC(SCCGWA, LNCSSCHE.OUT_INFO[1-1].O_PAY_INT);
            CEP.TRC(SCCGWA, LNCSSCHE.OUT_INFO[2-1].O_PAY_AMT);
            CEP.TRC(SCCGWA, LNCSSCHE.OUT_INFO[2-1].O_PAY_PRIN);
            CEP.TRC(SCCGWA, LNCSSCHE.OUT_INFO[2-1].O_PAY_INT);
        }
    }
    public void B002_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCSSCHD.FUNC == 'A') {
            if (BPRFSCH.FEE_STATUS == '2') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FSCH_COMPLETE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCSSCHD.FUNC == 'A' 
            || BPCSSCHD.FUNC == 'U' 
            || BPCSSCHD.FUNC == 'D') {
            if (BPRFSCH.FEE_STATUS == '3') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FSCH_DELETED;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPRFSCH.UCT_FLG == 'Y') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FSCH_UCT_FLG_Y;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCSSCHD.KEY.SETTLE_DATE < BPRFSCH.START_DATE 
            || BPCSSCHD.KEY.SETTLE_DATE > BPRFSCH.MATURITY_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_OUT_OF_FSCH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCSSCHD.INF.CHARGE_AMT);
        if (BPCSSCHD.FUNC == 'U' 
            && BPCSSCHD.INF.CHARGE_AMT >= 0) {
            B002_01_CHECK_ADJ_AMT();
            if (pgmRtn) return;
        }
    }
    public void B002_01_CHECK_ADJ_AMT() throws IOException,SQLException,Exception {
        if (BPCSSCHD.INF.ADJUST_AMT < 0) {
            WS_TMP_AMT = 0;
            WS_TMP_AMT = BPCSSCHD.INF.ADJUST_AMT * ( -1 );
            if (BPCSSCHD.INF.CHARGE_AMT < WS_TMP_AMT) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AMT_INPUT_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        if (BPCSSCHD.FUNC_OPT != 'A') {
            IBS.init(SCCGWA, BPRFSCHD);
            IBS.init(SCCGWA, BPCRSCHD);
            BPRFSCHD.KEY.CTRT_NO = BPCSSCHD.KEY.CTRT_NO;
            BPRFSCHD.KEY.SETTLE_DATE = BPCSSCHD.KEY.SETTLE_DATE;
            CEP.TRC(SCCGWA, BPCSSCHD.KEY.CTRT_NO);
            CEP.TRC(SCCGWA, BPCSSCHD.KEY.SETTLE_DATE);
            BPCRSCHD.INFO.FUNC = 'Q';
            S000_CALL_BPZRSCHD();
            if (pgmRtn) return;
            if (BPCRSCHD.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SCHD_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, BPCSSCHD.KEY.SETTLE_DATE);
        if (BPCSSCHD.FUNC == 'Q' 
            && (BPCSSCHD.FUNC_OPT == 'M' 
            || BPCSSCHD.FUNC_OPT == 'D')) {
            if (BPRFSCHD.STS == 'C') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SCHD_HAS_SETT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCSSCHD.FUNC_OPT != 'A') {
            if (BPCSSCHD.FUNC_OPT != 'R') {
                WS_FINAL_FLAG = 'N';
                WS_FINAL_CHG_FLAG = 'N';
                if (BPRFSCHD.STS == 'C') {
                    BPCUFEEC.FEE_AMT = BPRFSCHD.CHARGE_AMT;
                } else {
                    B120_CHECK_FINAL_FLAG();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, WS_FINAL_FLAG);
                    if (WS_FINAL_FLAG == 'Y' 
                        && BPRFSCH.CHARGE_AMT > 0) {
                        WS_FINAL_CHG_FLAG = 'Y';
                    }
                    CEP.TRC(SCCGWA, WS_FINAL_CHG_FLAG);
                    if (WS_FINAL_CHG_FLAG == 'Y') {
                        B150_CAL_LAST_FEE_AMT();
                        if (pgmRtn) return;
                    } else {
                        WS_PRE_SETTLE_DATE = 0;
                        B110_GET_LAST_DATE();
                        if (pgmRtn) return;
                        if (BPCRSCHD.RETURN_INFO == 'F') {
                            WS_PRE_SETTLE_DATE = BPRFSCHD.KEY.SETTLE_DATE;
                        }
                        CEP.TRC(SCCGWA, WS_PRE_SETTLE_DATE);
                        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
                            B100_CAL_FEE_AMT_CN();
                            if (pgmRtn) return;
                        } else {
                            B100_CAL_FEE_AMT();
                            if (pgmRtn) return;
                        }
                    }
                }
            }
            IBS.init(SCCGWA, BPRFSCHD);
            IBS.init(SCCGWA, BPCRSCHD);
            BPRFSCHD.KEY.CTRT_NO = BPCSSCHD.KEY.CTRT_NO;
            BPRFSCHD.KEY.SETTLE_DATE = BPCSSCHD.KEY.SETTLE_DATE;
            CEP.TRC(SCCGWA, BPCSSCHD.KEY.CTRT_NO);
            CEP.TRC(SCCGWA, BPCSSCHD.KEY.SETTLE_DATE);
            BPCRSCHD.INFO.FUNC = 'Q';
            S000_CALL_BPZRSCHD();
            if (pgmRtn) return;
            if (BPCRSCHD.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SCHD_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCSSCHD.FUNC_OPT == 'S') {
            if (BPRFSCHD.STS == 'C') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SCHD_HAS_SETT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPRFSCH.FEE_STATUS == '3') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FSCH_DELETED;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, BPRFSCHD);
            IBS.init(SCCGWA, BPCRSCHD);
            BPRFSCHD.KEY.CTRT_NO = BPCSSCHD.KEY.CTRT_NO;
            BPRFSCHD.KEY.SETTLE_DATE = BPCSSCHD.KEY.SETTLE_DATE;
            BPCRSCHD.INFO.FUNC = 'B';
            BPCRSCHD.INFO.OPT = 'A';
            S000_CALL_BPZRSCHD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPRFSCHD.KEY.CTRT_NO);
            CEP.TRC(SCCGWA, BPRFSCHD.KEY.SETTLE_DATE);
            CEP.TRC(SCCGWA, BPRFSCHD.STS);
            CEP.TRC(SCCGWA, BPCRSCHD.RETURN_INFO);
            if (BPCRSCHD.RETURN_INFO == 'F') {
                if (BPRFSCHD.STS != 'C') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_LAST_SCHD_NOT_SET;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        if (BPCSSCHD.FUNC_OPT == 'R') {
            if (BPRFSCHD.STS != 'C') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SCHD_NOT_SETT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPRFSCH.FEE_STATUS == '3') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FSCH_DELETED;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, BPRFSCHD);
            IBS.init(SCCGWA, BPCRSCHD);
            BPRFSCHD.KEY.CTRT_NO = BPCSSCHD.KEY.CTRT_NO;
            BPRFSCHD.KEY.SETTLE_DATE = BPCSSCHD.KEY.SETTLE_DATE;
            BPCRSCHD.INFO.FUNC = 'B';
            BPCRSCHD.INFO.OPT = 'B';
            S000_CALL_BPZRSCHD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPRFSCHD.STS);
            if (BPCRSCHD.RETURN_INFO == 'F') {
                if (BPRFSCHD.STS == 'C') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NEXT_SCHD_NOT_REV;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        if (BPCSSCHD.FUNC_OPT == 'S' 
            || BPCSSCHD.FUNC_OPT == 'R') {
            IBS.init(SCCGWA, BPRFSCHD);
            IBS.init(SCCGWA, BPCRSCHD);
            BPRFSCHD.KEY.CTRT_NO = BPCSSCHD.KEY.CTRT_NO;
            BPRFSCHD.KEY.SETTLE_DATE = BPCSSCHD.KEY.SETTLE_DATE;
            CEP.TRC(SCCGWA, BPCSSCHD.KEY.CTRT_NO);
            CEP.TRC(SCCGWA, BPCSSCHD.KEY.SETTLE_DATE);
            BPCRSCHD.INFO.FUNC = 'Q';
            S000_CALL_BPZRSCHD();
            if (pgmRtn) return;
            if (BPCRSCHD.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SCHD_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        BPCSSCHD.INF.REL_CTRT_NO = BPRFSCH.REL_CTRT_NO;
        BPCSSCHD.INF.CI_NO = BPRFSCH.CI_NO;
        BPCSSCHD.INF.PRD_TYPE = BPRFSCH.PRD_TYPE;
        BPCSSCHD.INF.FEE_TYPE = BPRFSCH.FEE_TYPE;
        BPCSSCHD.INF.PAY_IND = BPRFSCH.PAY_IND;
        BPCSSCHD.KEY.SETTLE_DATE = BPRFSCHD.KEY.SETTLE_DATE;
        BPCSSCHD.INF.CHARGE_CCY = BPRFSCH.CHARGE_CCY;
        BPCSSCHD.INF.CHARGE_AMT = BPCUFEEC.FEE_AMT;
        BPCSSCHD.INF.ADJUST_AMT = BPRFSCHD.ADJ_AMT;
        BPCSSCHD.INF.REMARK = BPRFSCHD.REMARK;
        BPCSSCHD.INF.CHG_CCY_REAL = BPRFSCHD.CHG_CCY_REAL;
        BPCSSCHD.INF.CHG_AMT_REAL = BPRFSCHD.CHG_AMT_REAL;
        BPCSSCHD.INF.RATE = BPRFSCHD.RATE;
    }
    public void B020_CREATE_PROCESS() throws IOException,SQLException,Exception {
        if (BPCSSCHD.KEY.SETTLE_DATE < BPRFSCH.START_DATE 
            || BPCSSCHD.KEY.SETTLE_DATE > BPRFSCH.MATURITY_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SETT_DATE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPRFSCHD);
        R000_TRANS_DATA();
        if (pgmRtn) return;
        BPCRSCHD.INFO.FUNC = 'C';
        S000_CALL_BPZRSCHD();
        if (pgmRtn) return;
        if (BPCRSCHD.RETURN_INFO == 'D') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SCHD_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_PRE_SETTLE_DATE = 0;
        B110_GET_LAST_DATE();
        if (pgmRtn) return;
        if (BPCRSCHD.RETURN_INFO == 'F') {
            WS_PRE_SETTLE_DATE = BPRFSCHD.KEY.SETTLE_DATE;
        }
        CEP.TRC(SCCGWA, WS_PRE_SETTLE_DATE);
        B100_CAL_FEE_AMT();
        if (pgmRtn) return;
        BPCSSCHD.INF.CHARGE_AMT = BPCUFEEC.FEE_AMT;
    }
    public void B020_CREATE_PROCESS_CN() throws IOException,SQLException,Exception {
        if (BPCSSCHD.KEY.SETTLE_DATE < BPRFSCH.START_DATE 
            || BPCSSCHD.KEY.SETTLE_DATE > BPRFSCH.MATURITY_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SETT_DATE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPRFSCHD);
        IBS.init(SCCGWA, BPCRSCHD);
        BPRFSCHD.KEY.CTRT_NO = BPCSSCHD.KEY.CTRT_NO;
        BPRFSCHD.KEY.SETTLE_DATE = BPCSSCHD.KEY.SETTLE_DATE;
        BPCRSCHD.INFO.FUNC = 'B';
        BPCRSCHD.INFO.OPT = 'B';
        S000_CALL_BPZRSCHD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRFSCHD.CHARGE_AMT);
        CEP.TRC(SCCGWA, "20150611");
        if (BPCRSCHD.RETURN_INFO == 'F') {
            if (BPRFSCHD.STS == 'C') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NEXT_SCHD_NOT_REV;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, BPCSSCHD.KEY.SETTLE_DATE);
            CEP.TRC(SCCGWA, BPRFSCHD.KEY.SETTLE_DATE);
            CEP.TRC(SCCGWA, BPRFSCHD.ADJ_AMT);
            WS_PRE_SETTLE_DATE = BPCSSCHD.KEY.SETTLE_DATE;
            WS_NEXT_SETTLE_DATE = BPRFSCHD.KEY.SETTLE_DATE;
            B100_CAL_FEE_AMT_CN();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCUFEEC.FEE_AMT);
            CEP.TRC(SCCGWA, BPRFSCHD.ADJ_AMT);
            if (( BPCUFEEC.FEE_AMT + BPRFSCHD.ADJ_AMT ) < 0) {
                CEP.TRC(SCCGWA, "MINUS AMT HAPPEN");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NEXT_AMT_MINUS;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        WS_NEXT_SETTLE_DATE = 0;
        IBS.init(SCCGWA, BPRFSCHD);
        R000_TRANS_DATA();
        if (pgmRtn) return;
        BPCRSCHD.INFO.FUNC = 'C';
        S000_CALL_BPZRSCHD();
        if (pgmRtn) return;
        if (BPCRSCHD.RETURN_INFO == 'D') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SCHD_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_PRE_SETTLE_DATE = 0;
        B110_GET_LAST_DATE();
        if (pgmRtn) return;
        if (BPCRSCHD.RETURN_INFO == 'F') {
            WS_PRE_SETTLE_DATE = BPRFSCHD.KEY.SETTLE_DATE;
        }
        CEP.TRC(SCCGWA, WS_PRE_SETTLE_DATE);
        B100_CAL_FEE_AMT_CN();
        if (pgmRtn) return;
        BPCSSCHD.INF.CHARGE_AMT = BPCUFEEC.FEE_AMT;
        CEP.TRC(SCCGWA, BPCSSCHD.INF.CHARGE_AMT);
        CEP.TRC(SCCGWA, BPCSSCHD.INF.ADJUST_AMT);
        B002_01_CHECK_ADJ_AMT();
        if (pgmRtn) return;
    }
    public void B030_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        WS_PRE_SETTLE_DATE = 0;
        IBS.init(SCCGWA, BPRFSCHD);
        IBS.init(SCCGWA, BPCRSCHD);
        BPRFSCHD.KEY.CTRT_NO = BPCSSCHD.KEY.CTRT_NO;
        BPRFSCHD.KEY.SETTLE_DATE = BPCSSCHD.INF.OLD_SETT_DATE;
        BPCRSCHD.INFO.FUNC = 'B';
        BPCRSCHD.INFO.OPT = 'A';
        S000_CALL_BPZRSCHD();
        if (pgmRtn) return;
        if (BPCRSCHD.RETURN_INFO == 'F') {
            WS_PRE_SETTLE_DATE = BPRFSCHD.KEY.SETTLE_DATE;
        }
        WS_NXT_SETTLE_DATE = 0;
        IBS.init(SCCGWA, BPRFSCHD);
        IBS.init(SCCGWA, BPCRSCHD);
        BPRFSCHD.KEY.CTRT_NO = BPCSSCHD.KEY.CTRT_NO;
        BPRFSCHD.KEY.SETTLE_DATE = BPCSSCHD.INF.OLD_SETT_DATE;
        BPCRSCHD.INFO.FUNC = 'B';
        BPCRSCHD.INFO.OPT = 'B';
        S000_CALL_BPZRSCHD();
        if (pgmRtn) return;
        if (BPCRSCHD.RETURN_INFO == 'F') {
            WS_NXT_SETTLE_DATE = BPRFSCHD.KEY.SETTLE_DATE;
        }
        if (WS_NXT_SETTLE_DATE != 0) {
            IBS.init(SCCGWA, BPRFSCHD);
            IBS.init(SCCGWA, BPCRSCHD);
            BPRFSCHD.KEY.CTRT_NO = BPCSSCHD.KEY.CTRT_NO;
            BPRFSCHD.KEY.SETTLE_DATE = WS_NXT_SETTLE_DATE;
            BPCRSCHD.INFO.FUNC = 'B';
            BPCRSCHD.INFO.OPT = 'B';
            S000_CALL_BPZRSCHD();
            if (pgmRtn) return;
            if (BPCRSCHD.RETURN_INFO == 'F') {
                WS_NXT_SETTLE_DATE2 = BPRFSCHD.KEY.SETTLE_DATE;
            }
        }
        if (WS_PRE_SETTLE_DATE != 0 
            && WS_PRE_SETTLE_DATE >= BPCSSCHD.KEY.SETTLE_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_OUT_OF_PERIOD;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_NXT_SETTLE_DATE != 0 
            && WS_NXT_SETTLE_DATE <= BPCSSCHD.KEY.SETTLE_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_OUT_OF_PERIOD;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_UPD_FLAG = 'N';
        if (BPCSSCHD.INF.OLD_SETT_DATE != BPCSSCHD.KEY.SETTLE_DATE) {
            WS_UPD_FLAG = 'Y';
        }
        IBS.init(SCCGWA, BPRFSCHD);
        BPRFSCHD.KEY.CTRT_NO = BPCSSCHD.KEY.CTRT_NO;
        BPRFSCHD.KEY.SETTLE_DATE = BPCSSCHD.INF.OLD_SETT_DATE;
        BPCRSCHD.INFO.FUNC = 'R';
        S000_CALL_BPZRSCHD();
        if (pgmRtn) return;
        if (BPCRSCHD.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SCHD_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPRFSCHD.STS == 'C') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SCHD_HAS_SETT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRFSCHD, BPRSCHDO);
        if (BPCSSCHD.INF.OLD_SETT_DATE == BPCSSCHD.KEY.SETTLE_DATE) {
            BPCRSCHD.INFO.FUNC = 'U';
            R000_TRANS_DATA();
            if (pgmRtn) return;
            S000_CALL_BPZRSCHD();
            if (pgmRtn) return;
        } else {
            BPCRSCHD.INFO.FUNC = 'D';
            S000_CALL_BPZRSCHD();
            if (pgmRtn) return;
            R000_TRANS_DATA();
            if (pgmRtn) return;
            BPCRSCHD.INFO.FUNC = 'C';
            S000_CALL_BPZRSCHD();
            if (pgmRtn) return;
        }
        WS_PRE_SETTLE_DATE = 0;
        B110_GET_LAST_DATE();
        if (pgmRtn) return;
        if (BPCRSCHD.RETURN_INFO == 'F') {
            WS_PRE_SETTLE_DATE = BPRFSCHD.KEY.SETTLE_DATE;
        }
        CEP.TRC(SCCGWA, WS_PRE_SETTLE_DATE);
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B100_CAL_FEE_AMT_CN();
            if (pgmRtn) return;
        } else {
            B100_CAL_FEE_AMT();
            if (pgmRtn) return;
        }
        BPCSSCHD.INF.CHARGE_AMT = BPCUFEEC.FEE_AMT;
    }
    public void B040_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFSCHD);
        BPRFSCHD.KEY.CTRT_NO = BPCSSCHD.KEY.CTRT_NO;
        BPRFSCHD.KEY.SETTLE_DATE = BPCSSCHD.KEY.SETTLE_DATE;
        BPCRSCHD.INFO.FUNC = 'R';
        S000_CALL_BPZRSCHD();
        if (pgmRtn) return;
        if (BPCRSCHD.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SCHD_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPRFSCHD.STS == 'C') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SCHD_HAS_SETT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPCRSCHD.INFO.FUNC = 'D';
        S000_CALL_BPZRSCHD();
        if (pgmRtn) return;
    }
    public void B040_DELETE_PROCESS_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFSCHD);
        BPRFSCHD.KEY.CTRT_NO = BPCSSCHD.KEY.CTRT_NO;
        BPRFSCHD.KEY.SETTLE_DATE = BPCSSCHD.KEY.SETTLE_DATE;
        BPCRSCHD.INFO.FUNC = 'R';
        S000_CALL_BPZRSCHD();
        if (pgmRtn) return;
        if (BPCRSCHD.RETURN_INFO == 'N') {
            CEP.TRC(SCCGWA, "DONT TALK");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SCHD_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPRFSCHD.STS == 'C') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SCHD_HAS_SETT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPRFSCHD);
        IBS.init(SCCGWA, BPCRSCHD);
        BPRFSCHD.KEY.CTRT_NO = BPCSSCHD.KEY.CTRT_NO;
        BPRFSCHD.KEY.SETTLE_DATE = BPCSSCHD.KEY.SETTLE_DATE;
        BPCRSCHD.INFO.FUNC = 'B';
        BPCRSCHD.INFO.OPT = 'B';
        S000_CALL_BPZRSCHD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "20150708");
        if (BPCRSCHD.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_LAST_SCHD_CANT_DEL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPCRSCHD.INFO.FUNC = 'D';
        S000_CALL_BPZRSCHD();
        if (pgmRtn) return;
    }
    public void B050_BROWSER_PROCESS() throws IOException,SQLException,Exception {
        if (BPCSSCHD.FUNC == '1') {
            B050_01_BRW_VIEW();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B050_01_BRW_VIEW() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSSCHD.KEY.CTRT_NO);
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B050_01_01_BRW_SCHD_CN();
            if (pgmRtn) return;
        } else {
            B050_01_01_BRW_SCHD();
            if (pgmRtn) return;
        }
    }
    public void B050_01_01_BRW_SCHD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRFSCH);
        IBS.init(SCCGWA, BPRFSCH);
        BPRFSCH.KEY.CTRT_NO = BPCSSCHD.KEY.CTRT_NO;
        BPCRFSCH.INFO.FUNC = 'Q';
        S000_CALL_BPZRFSCH();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCRSCHD);
        IBS.init(SCCGWA, BPRFSCHD);
        BPRFSCHD.KEY.CTRT_NO = BPCSSCHD.KEY.CTRT_NO;
        BPCRSCHD.INFO.FUNC = '1';
        S000_CALL_BPZRSCHD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "1");
        WS_SCHD_COUNT = BPCRSCHD.INFO.SCHD_COUNT;
        CEP.TRC(SCCGWA, "2");
        CEP.TRC(SCCGWA, WS_SCHD_COUNT);
        CEP.TRC(SCCGWA, "3");
        WS_CNT1 = 0;
        WS_TOT_AMT = 0;
        WS_PRE_SETTLE_DATE = 0;
        WS_BROWSE_FLAG = 'Y';
        WS_FINAL_FLAG = 'N';
        IBS.init(SCCGWA, BPCRSCHD);
        IBS.init(SCCGWA, BPRFSCHD);
        BPRFSCHD.KEY.CTRT_NO = BPCSSCHD.KEY.CTRT_NO;
        BPCRSCHD.INFO.FUNC = 'B';
        BPCRSCHD.INFO.OPT = '3';
        S000_CALL_BPZRSCHD();
        if (pgmRtn) return;
        BPCRSCHD.INFO.FUNC = 'N';
        S000_CALL_BPZRSCHD();
        if (pgmRtn) return;
        if (BPCRSCHD.RETURN_INFO == 'F') {
            if (BPCSSCHD.FUNC == '1') {
                B050_01_OUT_TITLE();
                if (pgmRtn) return;
            }
        }
        while (BPCRSCHD.RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E') {
            CEP.TRC(SCCGWA, BPRFSCHD.KEY.CTRT_NO);
            CEP.TRC(SCCGWA, BPRFSCHD.KEY.SETTLE_DATE);
            WS_CNT1 += 1;
            if (WS_CNT1 == WS_SCHD_COUNT) {
                WS_FINAL_FLAG = 'Y';
            }
            B050_02_OUT_BRW_DATA();
            if (pgmRtn) return;
            BPCRSCHD.INFO.FUNC = 'N';
            S000_CALL_BPZRSCHD();
            if (pgmRtn) return;
        }
        BPCRSCHD.INFO.FUNC = 'E';
        S000_CALL_BPZRSCHD();
        if (pgmRtn) return;
        WS_BROWSE_FLAG = 'N';
    }
    public void B050_01_01_BRW_SCHD_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRFSCH);
        IBS.init(SCCGWA, BPRFSCH);
        BPRFSCH.KEY.CTRT_NO = BPCSSCHD.KEY.CTRT_NO;
        BPCRFSCH.INFO.FUNC = 'Q';
        S000_CALL_BPZRFSCH();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCRSCHD);
        IBS.init(SCCGWA, BPRFSCHD);
        BPRFSCHD.KEY.CTRT_NO = BPCSSCHD.KEY.CTRT_NO;
        BPCRSCHD.INFO.FUNC = '1';
        S000_CALL_BPZRSCHD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "1");
        WS_SCHD_COUNT = BPCRSCHD.INFO.SCHD_COUNT;
        CEP.TRC(SCCGWA, "2");
        CEP.TRC(SCCGWA, WS_SCHD_COUNT);
        CEP.TRC(SCCGWA, "3");
        WS_CNT1 = 0;
        WS_TOT_AMT = 0;
        WS_PRE_SETTLE_DATE = 0;
        WS_BROWSE_FLAG = 'Y';
        WS_FINAL_FLAG = 'N';
        IBS.init(SCCGWA, BPCRSCHD);
        IBS.init(SCCGWA, BPRFSCHD);
        BPRFSCHD.KEY.CTRT_NO = BPCSSCHD.KEY.CTRT_NO;
        BPCRSCHD.INFO.FUNC = 'B';
        BPCRSCHD.INFO.OPT = '3';
        S000_CALL_BPZRSCHD();
        if (pgmRtn) return;
        BPCRSCHD.INFO.FUNC = 'N';
        S000_CALL_BPZRSCHD();
        if (pgmRtn) return;
        if (BPCRSCHD.RETURN_INFO == 'F') {
            if (BPCSSCHD.FUNC == '1') {
                B050_01_OUT_TITLE();
                if (pgmRtn) return;
            }
        }
        while (BPCRSCHD.RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E') {
            CEP.TRC(SCCGWA, BPRFSCHD.KEY.CTRT_NO);
            CEP.TRC(SCCGWA, BPRFSCHD.KEY.SETTLE_DATE);
            WS_CNT1 += 1;
            if (WS_CNT1 == WS_SCHD_COUNT) {
                WS_FINAL_FLAG = 'Y';
            }
            B050_02_OUT_BRW_DATA();
            if (pgmRtn) return;
            BPCRSCHD.INFO.FUNC = 'N';
            S000_CALL_BPZRSCHD();
            if (pgmRtn) return;
        }
        BPCRSCHD.INFO.FUNC = 'E';
        S000_CALL_BPZRSCHD();
        if (pgmRtn) return;
        WS_BROWSE_FLAG = 'N';
    }
    public void B050_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 459;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B050_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRFSCHD.STS);
        if (BPRFSCHD.STS == 'C') {
            WS_UFEEC_FEE_AMT = BPCUFEEC.FEE_AMT;
            BPCUFEEC.FEE_AMT = BPRFSCHD.CHARGE_AMT;
            CEP.TRC(SCCGWA, BPCUFEEC.FEE_AMT);
            if (BPRFSCH.FEE_TYPE.equalsIgnoreCase(K_FEE_CODE) 
                && BPRFSCH.FIR_DSCT > 0) {
                BPCUFEEC.FEE_AMT = BPRFSCHD.CHARGE_AMT * 100 / BPRFSCH.FIR_DSCT;
                WS_TEMP_FEE_AMT = BPRFSCHD.CHARGE_AMT;
            }
        } else {
            CEP.TRC(SCCGWA, WS_CNT1);
            if (WS_FINAL_FLAG == 'Y' 
                && BPRFSCH.CHARGE_AMT > 0) {
                BPCUFEEC.FEE_AMT = BPRFSCH.CHARGE_AMT - WS_TOT_AMT;
                CEP.TRC(SCCGWA, "THE FEE AMOUNT OF LAST DETAL SCHEDULE");
                CEP.TRC(SCCGWA, WS_TOT_AMT);
                CEP.TRC(SCCGWA, BPCUFEEC.FEE_AMT);
            } else {
                if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
                    B100_CAL_FEE_AMT_CN();
                    if (pgmRtn) return;
                } else {
                    B100_CAL_FEE_AMT();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, WS_TOT_AMT);
                CEP.TRC(SCCGWA, BPCUFEEC.FEE_AMT);
            }
        }
        WS_PRE_SETTLE_DATE = BPRFSCHD.KEY.SETTLE_DATE;
        WS_TOT_AMT = WS_TOT_AMT + BPCUFEEC.FEE_AMT;
        CEP.TRC(SCCGWA, WS_TOT_AMT);
        CEP.TRC(SCCGWA, BPRFSCH.FEE_TYPE);
        CEP.TRC(SCCGWA, "JINJING");
        CEP.TRC(SCCGWA, BPCUFEEC.FEE_AMT);
        if (BPRFSCH.FEE_TYPE.equalsIgnoreCase(K_FEE_CODE) 
            && BPRFSCH.FIR_DSCT > 0) {
            WS_TEMP_FEE_AMT = BPCUFEEC.FEE_AMT * BPRFSCH.FIR_DSCT / 100;
        } else {
            WS_TEMP_FEE_AMT = BPCUFEEC.FEE_AMT;
        }
        if (BPCSSCHD.FUNC == '1') {
            IBS.init(SCCGWA, WS_LIST_DATA);
            WS_LIST_DATA.WS_FEE_CTRT_NO = BPRFSCHD.KEY.CTRT_NO;
            WS_LIST_DATA.WS_REL_CTRT_NO = BPRFSCH.REL_CTRT_NO;
            WS_LIST_DATA.WS_FEE_TYPE = BPRFSCH.FEE_TYPE;
            WS_LIST_DATA.WS_SETTLE_DATE = BPRFSCHD.KEY.SETTLE_DATE;
            WS_LIST_DATA.WS_FEE_AMT = BPCUFEEC.FEE_AMT;
            WS_LIST_DATA.WS_ADJ_AMT = BPRFSCHD.ADJ_AMT;
            if (WS_TEMP_FEE_AMT < BPRFSCH.FCHG_MIN_AMT) {
                WS_LIST_DATA.WS_CHG_AMT = BPRFSCH.FCHG_MIN_AMT + WS_LIST_DATA.WS_ADJ_AMT;
            } else {
                WS_LIST_DATA.WS_CHG_AMT = WS_TEMP_FEE_AMT + WS_LIST_DATA.WS_ADJ_AMT;
            }
            CEP.TRC(SCCGWA, BPCUFEEC.FEE_AMT);
            CEP.TRC(SCCGWA, BPRFSCHD.ADJ_AMT);
            CEP.TRC(SCCGWA, WS_LIST_DATA.WS_CHG_AMT);
            WS_LIST_DATA.WS_PART_CI_NO = BPRFSCH.CI_NO;
            if (BPRFSCH.CI_NO.trim().length() > 0) {
                IBS.init(SCCGWA, CICCUST);
                CICCUST.DATA.CI_NO = BPRFSCH.CI_NO;
                CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
                S000_CALL_CIZCUST();
                if (pgmRtn) return;
                WS_LIST_DATA.WS_PART_FULL_NAME = CICCUST.O_DATA.O_CI_NM;
            }
            WS_LIST_DATA.WS_FEE_STATUS = BPRFSCHD.STS;
            CEP.TRC(SCCGWA, BPRFSCHD.STS);
            CEP.TRC(SCCGWA, WS_LIST_DATA.WS_FEE_STATUS);
            WS_LIST_DATA.WS_ORI_TXN_DT = BPRFSCHD.REAL_AC_DATE;
            WS_LIST_DATA.WS_ORI_JRN_NO = BPRFSCHD.JNL_NO;
            WS_LIST_DATA.WS_ORI_TXN_CD = "9999156";
            WS_LIST_DATA.WS_FIR_DSCT = BPRFSCH.FIR_DSCT;
            WS_LIST_DATA.WS_CHG_CCY = BPRFSCH.CHARGE_CCY;
            WS_LIST_DATA.WS_CHG_CCY_REAL = BPRFSCHD.CHG_CCY_REAL;
            WS_LIST_DATA.WS_CHG_AMT_REAL = BPRFSCHD.CHG_AMT_REAL;
            WS_LIST_DATA.WS_RATE = BPRFSCHD.RATE;
            if (BPRFSCHD.STS == 'C' 
                && BPRFSCH.FEE_TYPE.equalsIgnoreCase(K_FEE_CODE)) {
                WS_LIST_DATA.WS_FEE_AMT = BPRFSCHD.CHARGE_AMT;
            }
            IBS.init(SCCGWA, SCCMPAG);
            SCCMPAG.FUNC = 'D';
            SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_LIST_DATA);
            SCCMPAG.DATA_LEN = 459;
            CEP.TRC(SCCGWA, WS_LIST_DATA);
            B_MPAG();
            if (pgmRtn) return;
        }
    }
    public void B080_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARK;
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK;
        BPCPNHIS.INFO.FMT_ID_LEN = 376;
        if (BPCSSCHD.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
            BPCPNHIS.INFO.NEW_DAT_PT = BPRFSCHD;
        }
        if (BPCSSCHD.FUNC == 'U') {
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.OLD_DAT_PT = BPRSCHDO;
            BPCPNHIS.INFO.NEW_DAT_PT = BPRFSCHD;
        }
        if (BPCSSCHD.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
            BPCPNHIS.INFO.OLD_DAT_PT = BPRSCHDO;
        }
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B085_MAINT_EVENT() throws IOException,SQLException,Exception {
        WS_DIARY_CNT = 0;
        if (BPCSSCHD.FUNC == 'A') {
            B085_00_GET_BATNO();
            if (pgmRtn) return;
            B085_00_GET_DATE();
            if (pgmRtn) return;
            B085_01_ADD_EVENTS();
            if (pgmRtn) return;
        }
        if (BPCSSCHD.FUNC == 'U') {
            if (WS_UPD_FLAG == 'Y') {
                B085_00_GET_BATNO();
                if (pgmRtn) return;
                B085_00_GET_DATE();
                if (pgmRtn) return;
                B085_02_UPD_EVENTS();
                if (pgmRtn) return;
            }
        }
        if (BPCSSCHD.FUNC == 'D') {
            B085_00_GET_BATNO();
            if (pgmRtn) return;
            B085_00_GET_DATE();
            if (pgmRtn) return;
            B085_03_DEL_EVENTS();
            if (pgmRtn) return;
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
    public void B085_00_GET_DATE() throws IOException,SQLException,Exception {
        WS_PRE_SETTLE_DT = 0;
        WS_NXT_SETTLE_DATE = 0;
        WS_NXT_SETTLE_DATE2 = 0;
        WS_LST_SETTLE_DATE = 0;
        IBS.init(SCCGWA, BPRFSCHD);
        IBS.init(SCCGWA, BPCRSCHD);
        BPRFSCHD.KEY.CTRT_NO = BPCSSCHD.KEY.CTRT_NO;
        BPRFSCHD.KEY.SETTLE_DATE = BPCSSCHD.KEY.SETTLE_DATE;
        BPCRSCHD.INFO.FUNC = 'B';
        BPCRSCHD.INFO.OPT = 'A';
        S000_CALL_BPZRSCHD();
        if (pgmRtn) return;
        if (BPCRSCHD.RETURN_INFO == 'F') {
            WS_PRE_SETTLE_DT = BPRFSCHD.KEY.SETTLE_DATE;
        }
        IBS.init(SCCGWA, BPRFSCHD);
        IBS.init(SCCGWA, BPCRSCHD);
        BPRFSCHD.KEY.CTRT_NO = BPCSSCHD.KEY.CTRT_NO;
        BPRFSCHD.KEY.SETTLE_DATE = BPCSSCHD.KEY.SETTLE_DATE;
        BPCRSCHD.INFO.FUNC = 'B';
        BPCRSCHD.INFO.OPT = 'B';
        S000_CALL_BPZRSCHD();
        if (pgmRtn) return;
        if (BPCRSCHD.RETURN_INFO == 'F') {
            WS_NXT_SETTLE_DATE = BPRFSCHD.KEY.SETTLE_DATE;
        }
        if (BPCRSCHD.RETURN_INFO == 'N') {
            WS_LST_SETTLE_DATE = BPCSSCHD.KEY.SETTLE_DATE;
        }
        if (WS_NXT_SETTLE_DATE != 0) {
            IBS.init(SCCGWA, BPRFSCHD);
            IBS.init(SCCGWA, BPCRSCHD);
            BPRFSCHD.KEY.CTRT_NO = BPCSSCHD.KEY.CTRT_NO;
            BPRFSCHD.KEY.SETTLE_DATE = WS_NXT_SETTLE_DATE;
            BPCRSCHD.INFO.FUNC = 'B';
            BPCRSCHD.INFO.OPT = 'B';
            S000_CALL_BPZRSCHD();
            if (pgmRtn) return;
            if (BPCRSCHD.RETURN_INFO == 'F') {
                WS_NXT_SETTLE_DATE2 = BPRFSCHD.KEY.SETTLE_DATE;
            }
        }
        CEP.TRC(SCCGWA, WS_PRE_SETTLE_DT);
        CEP.TRC(SCCGWA, WS_NXT_SETTLE_DATE);
        CEP.TRC(SCCGWA, WS_NXT_SETTLE_DATE2);
        CEP.TRC(SCCGWA, WS_LST_SETTLE_DATE);
    }
    public void B085_01_ADD_EVENTS() throws IOException,SQLException,Exception {
        WS_DIARY_CNT += 1;
        if (WS_PRE_SETTLE_DT == 0) {
            WS_START_DATE = BPRFSCH.START_DATE;
        } else {
            WS_START_DATE = WS_PRE_SETTLE_DT;
        }
        WS_END_DATE = BPCSSCHD.KEY.SETTLE_DATE;
        B085_01_01_CAL_FEE();
        if (pgmRtn) return;
        B085_01_02_A_EVENT();
        if (pgmRtn) return;
        if (WS_NXT_SETTLE_DATE != 0 
            || (WS_NXT_SETTLE_DATE == 0 
            && WS_PRE_SETTLE_DT != 0)) {
            WS_DIARY_CNT += 1;
            if (WS_NXT_SETTLE_DATE != 0) {
                WS_START_DATE = BPCSSCHD.KEY.SETTLE_DATE;
                WS_END_DATE = WS_NXT_SETTLE_DATE;
                B085_01_01_CAL_FEE();
                if (pgmRtn) return;
            }
            B085_01_03_U_EVENT();
            if (pgmRtn) return;
        }
    }
    public void B085_01_01_CAL_FEE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_START_DATE);
        CEP.TRC(SCCGWA, WS_END_DATE);
        if (BPRFSCH.ACCRUAL_TYPE == 'F' 
            || BPRFSCH.ACCRUAL_TYPE == 'B') {
            if (BPRFSCH.ACCRUAL_TYPE == 'B' 
                && WS_LST_SETTLE_DATE != 0) {
            } else {
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = WS_END_DATE;
                SCCCLDT.DAYS = -1;
                S000_CALL_SCSSCLDT();
                if (pgmRtn) return;
                WS_END_DATE = SCCCLDT.DATE2;
            }
        }
        if (BPRFSCH.ACCRUAL_TYPE == 'L') {
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
        BPCUFEEC.CTRT_NO = BPCSSCHD.KEY.CTRT_NO;
        BPCUFEEC.CTRT_TYP = 'S';
        BPCUFEEC.STA_DT = WS_START_DATE;
        BPCUFEEC.END_DT = WS_END_DATE;
        BPCUFEEC.RUN_MODE = 'O';
        CEP.TRC(SCCGWA, BPCUFEEC.FEE_AMT);
        CEP.TRC(SCCGWA, BPCUFEEC.EX_RATE);
        if (WS_EX_RATE == 0) {
            WS_EX_RATE = BPCUFEEC.EX_RATE;
        }
    }
    public void B085_01_02_A_EVENT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUMENT);
        BPCUMENT.PROC_DATA.FUNC_CODE = "A ";
        BPCUMENT.DIARY_DATA.CONTRACT_NO = BPCSSCHD.KEY.CTRT_NO;
        BPCUMENT.DIARY_DATA.DIARY_TYPE = "FS";
        BPCUMENT.DIARY_DATA.DIARY_DATE = BPCSSCHD.KEY.SETTLE_DATE;
        BPCUMENT.DIARY_DATA.ACTL_DATE = SCCGWA.COMM_AREA.TR_DATE;
        BPCUMENT.DIARY_DATA.ACTL_TIME = SCCGWA.COMM_AREA.TR_TIME;
        BPCUMENT.DIARY_DATA.ACCT_CENTRE = BPRFSCH.BOOK_CENTRE;
        BPCUMENT.DIARY_DATA.CONTRACT_TYPE = BPRFSCH.CTRT_TYPE;
        BPCUMENT.DIARY_DATA.PRODUCT_TYPE = BPRFSCH.PRD_TYPE;
        BPCUMENT.DIARY_DATA.TRN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCUMENT.DIARY_DATA.TRN_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        BPCUMENT.DIARY_DATA.DIARY_BATNO = BPCSGSEQ.SEQ;
        BPCUMENT.DIARY_DATA.DIARY_STATUS = 'U';
        if (WS_LST_SETTLE_DATE != 0) {
            B150_CAL_LAST_FEE_AMT();
            if (pgmRtn) return;
        }
        BPCUMENT.DIARY_DATA.AMOUNT1 = BPCUFEEC.FEE_AMT;
        BPCUMENT.DIARY_DATA.AMOUNT4 = BPCUFEEC.FEE_AMT;
        BPCUMENT.DIARY_DATA.AMOUNT5 = BPCUFEEC.FEE_AMT;
        if (BPRFSCH.PAY_IND == 'R') {
            BPCUMENT.DIARY_DATA.AMOUNT4 = 0;
        } else {
            BPCUMENT.DIARY_DATA.AMOUNT5 = 0;
        }
        BPCUMENT.DIARY_DATA.AS_OF_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCUMENT.DIARY_DATA.CCY_1 = BPRFSCH.CHARGE_CCY;
        BPCUMENT.DIARY_DATA.CLIENT_NO = BPRFSCH.CI_NO;
        BPCUMENT.DIARY_DATA.D_REFNO = 0;
        BPCUMENT.DIARY_DATA.GEN_IND1 = 'A';
        BPCUMENT.DIARY_DATA.GEN_IND2 = BPRFSCH.PAY_IND;
        BPCUMENT.DIARY_DATA.HOLIDAY = BPRFSCH.HOLI_OVER;
        BPCUMENT.DIARY_DATA.HOLOVR_CHK = " ";
        if (WS_LST_SETTLE_DATE != 0) {
            BPCUMENT.DIARY_DATA.DIARY_TYPE = "MA";
            BPCUMENT.DIARY_DATA.D_REFNO = 9999;
            BPCUMENT.DIARY_DATA.AMOUNT10 = BPRFSCH.CHARGE_AMT;
            if (BPRFSCH.CHARGE_AMT == 0) {
                WS_START_DATE = BPRFSCH.START_DATE;
                WS_END_DATE = BPCSSCHD.KEY.SETTLE_DATE;
                B085_01_01_CAL_FEE();
                if (pgmRtn) return;
                BPCUMENT.DIARY_DATA.AMOUNT10 = BPCUFEEC.FEE_AMT;
            }
            CEP.TRC(SCCGWA, BPRFSCH.CHARGE_AMT);
            CEP.TRC(SCCGWA, BPCUMENT.DIARY_DATA.AMOUNT10);
            BPCUMENT.PROC_DATA.DIARY_CNT = WS_DIARY_CNT;
        }
        if (WS_EX_RATE == 0) {
            BPCUMENT.DIARY_DATA.RATEX3 = 1;
        } else {
            BPCUMENT.DIARY_DATA.RATEX3 = WS_EX_RATE;
        }
        BPCUMENT.DIARY_DATA.OTH_CN_NO = BPRFSCH.REL_CTRT_NO;
        B085_90_MOVE_AC_IFNO();
        if (pgmRtn) return;
        S000_CALL_BPZUMENT();
        if (pgmRtn) return;
    }
    public void B085_01_03_U_EVENT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUMENT);
        BPCUMENT.PROC_DATA.FUNC_CODE = "R ";
        BPCUMENT.DIARY_DATA.CONTRACT_NO = BPCSSCHD.KEY.CTRT_NO;
        if (WS_NXT_SETTLE_DATE != 0) {
            BPCUMENT.DIARY_DATA.DIARY_DATE = WS_NXT_SETTLE_DATE;
            BPCUMENT.DIARY_DATA.DIARY_TYPE = "FS";
            if (WS_NXT_SETTLE_DATE2 == 0) {
                BPCUMENT.DIARY_DATA.DIARY_TYPE = "MA";
            }
        } else {
            BPCUMENT.DIARY_DATA.DIARY_DATE = WS_PRE_SETTLE_DT;
            BPCUMENT.DIARY_DATA.DIARY_TYPE = "MA";
        }
        S000_CALL_BPZUMENT();
        if (pgmRtn) return;
        BPCUMENT.DIARY_DATA.DIARY_TYPE = "FS";
        if (WS_NXT_SETTLE_DATE != 0 
            && WS_NXT_SETTLE_DATE2 == 0) {
            BPCUMENT.DIARY_DATA.DIARY_TYPE = "MA";
        }
        BPCUMENT.DIARY_DATA.TRN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCUMENT.DIARY_DATA.TRN_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        BPCUMENT.DIARY_DATA.DIARY_BATNO = BPCSGSEQ.SEQ;
        if (BPCUMENT.DIARY_DATA.DIARY_TYPE.equalsIgnoreCase("MA")) {
            B150_CAL_LAST_FEE_AMT();
            if (pgmRtn) return;
        } else {
            if (WS_NXT_SETTLE_DATE != 0) {
                WS_START_DATE = BPCSSCHD.KEY.SETTLE_DATE;
            } else {
                IBS.init(SCCGWA, BPRFSCHD);
                IBS.init(SCCGWA, BPCRSCHD);
                BPRFSCHD.KEY.CTRT_NO = BPCSSCHD.KEY.CTRT_NO;
                BPRFSCHD.KEY.SETTLE_DATE = WS_PRE_SETTLE_DT;
                BPCRSCHD.INFO.FUNC = 'B';
                BPCRSCHD.INFO.OPT = 'A';
                S000_CALL_BPZRSCHD();
                if (pgmRtn) return;
                if (BPCRSCHD.RETURN_INFO == 'F') {
                    WS_PRE_SETTLE_DATE2 = BPRFSCHD.KEY.SETTLE_DATE;
                } else {
                    WS_PRE_SETTLE_DATE2 = BPRFSCH.START_DATE;
                }
                WS_START_DATE = WS_PRE_SETTLE_DATE2;
            }
            WS_END_DATE = BPCUMENT.DIARY_DATA.DIARY_DATE;
            B085_01_01_CAL_FEE();
            if (pgmRtn) return;
        }
        BPCUMENT.DIARY_DATA.AMOUNT1 = BPCUFEEC.FEE_AMT;
        BPCUMENT.DIARY_DATA.AMOUNT4 = BPCUFEEC.FEE_AMT;
        BPCUMENT.DIARY_DATA.AMOUNT5 = BPCUFEEC.FEE_AMT;
        if (BPRFSCH.PAY_IND == 'R') {
            BPCUMENT.DIARY_DATA.AMOUNT4 = 0;
        } else {
            BPCUMENT.DIARY_DATA.AMOUNT5 = 0;
        }
        if (!BPCUMENT.DIARY_DATA.DIARY_TYPE.equalsIgnoreCase("MA")) {
            BPCUMENT.DIARY_DATA.AMOUNT10 = 0;
        } else {
            BPCUMENT.DIARY_DATA.AMOUNT10 = BPRFSCH.CHARGE_AMT;
            if (BPRFSCH.CHARGE_AMT == 0) {
                WS_START_DATE = BPRFSCH.START_DATE;
                WS_END_DATE = BPCSSCHD.KEY.SETTLE_DATE;
                B085_01_01_CAL_FEE();
                if (pgmRtn) return;
                BPCUMENT.DIARY_DATA.AMOUNT10 = BPCUFEEC.FEE_AMT;
            }
        }
        CEP.TRC(SCCGWA, BPRFSCH.CHARGE_AMT);
        CEP.TRC(SCCGWA, BPCUMENT.DIARY_DATA.AMOUNT10);
        BPCUMENT.PROC_DATA.DIARY_CNT = WS_DIARY_CNT;
        if (WS_EX_RATE != 0) {
            BPCUMENT.DIARY_DATA.RATEX3 = WS_EX_RATE;
        }
        BPCUMENT.DIARY_DATA.OTH_CN_NO = BPRFSCH.REL_CTRT_NO;
        BPCUMENT.PROC_DATA.FUNC_CODE = "M2";
        S000_CALL_BPZUMENT();
        if (pgmRtn) return;
    }
    public void B085_02_UPD_EVENTS() throws IOException,SQLException,Exception {
        WS_DIARY_CNT += 1;
        IBS.init(SCCGWA, BPCUMENT);
        BPCUMENT.PROC_DATA.FUNC_CODE = "R ";
        BPCUMENT.DIARY_DATA.CONTRACT_NO = BPCSSCHD.KEY.CTRT_NO;
        BPCUMENT.DIARY_DATA.DIARY_TYPE = "FS";
        if (WS_NXT_SETTLE_DATE == 0) {
            BPCUMENT.DIARY_DATA.DIARY_TYPE = "MA";
        }
        BPCUMENT.DIARY_DATA.DIARY_DATE = BPCSSCHD.INF.OLD_SETT_DATE;
        S000_CALL_BPZUMENT();
        if (pgmRtn) return;
        BPCUMENT.DIARY_DATA.DIARY_DATE = BPCSSCHD.KEY.SETTLE_DATE;
        BPCUMENT.DIARY_DATA.TRN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCUMENT.DIARY_DATA.TRN_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        BPCUMENT.DIARY_DATA.DIARY_BATNO = BPCSGSEQ.SEQ;
        if (WS_PRE_SETTLE_DT == 0) {
            WS_START_DATE = BPRFSCH.START_DATE;
        } else {
            WS_START_DATE = WS_PRE_SETTLE_DT;
        }
        WS_END_DATE = BPCSSCHD.KEY.SETTLE_DATE;
        B085_01_01_CAL_FEE();
        if (pgmRtn) return;
        BPCUMENT.DIARY_DATA.AMOUNT1 = BPCUFEEC.FEE_AMT;
        BPCUMENT.DIARY_DATA.AMOUNT4 = BPCUFEEC.FEE_AMT;
        BPCUMENT.DIARY_DATA.AMOUNT5 = BPCUFEEC.FEE_AMT;
        if (WS_NXT_SETTLE_DATE == 0) {
            WS_START_DATE = BPRFSCH.START_DATE;
            WS_END_DATE = BPCSSCHD.KEY.SETTLE_DATE;
            B085_01_01_CAL_FEE();
            if (pgmRtn) return;
            BPCUMENT.DIARY_DATA.AMOUNT10 = BPCUFEEC.FEE_AMT;
            BPCUMENT.PROC_DATA.DIARY_CNT = WS_DIARY_CNT;
        }
        if (BPRFSCH.PAY_IND == 'R') {
            BPCUMENT.DIARY_DATA.AMOUNT4 = 0;
        } else {
            BPCUMENT.DIARY_DATA.AMOUNT5 = 0;
        }
        if (WS_EX_RATE != 0) {
            BPCUMENT.DIARY_DATA.RATEX3 = WS_EX_RATE;
        }
        BPCUMENT.DIARY_DATA.OTH_CN_NO = BPRFSCH.REL_CTRT_NO;
        BPCUMENT.PROC_DATA.FUNC_CODE = "M2";
        S000_CALL_BPZUMENT();
        if (pgmRtn) return;
        if (WS_NXT_SETTLE_DATE != 0) {
            WS_DIARY_CNT += 1;
            IBS.init(SCCGWA, BPCUMENT);
            BPCUMENT.PROC_DATA.FUNC_CODE = "R ";
            BPCUMENT.DIARY_DATA.CONTRACT_NO = BPCSSCHD.KEY.CTRT_NO;
            BPCUMENT.DIARY_DATA.DIARY_TYPE = "FS";
            if (WS_NXT_SETTLE_DATE2 == 0) {
                BPCUMENT.DIARY_DATA.DIARY_TYPE = "MA";
            }
            BPCUMENT.DIARY_DATA.DIARY_DATE = WS_NXT_SETTLE_DATE;
            S000_CALL_BPZUMENT();
            if (pgmRtn) return;
            BPCUMENT.DIARY_DATA.TRN_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCUMENT.DIARY_DATA.TRN_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
            BPCUMENT.DIARY_DATA.DIARY_BATNO = BPCSGSEQ.SEQ;
            WS_START_DATE = BPCSSCHD.KEY.SETTLE_DATE;
            WS_END_DATE = WS_NXT_SETTLE_DATE;
            B085_01_01_CAL_FEE();
            if (pgmRtn) return;
            if (BPCUMENT.DIARY_DATA.DIARY_TYPE.equalsIgnoreCase("MA")) {
                B150_CAL_LAST_FEE_AMT();
                if (pgmRtn) return;
            }
            BPCUMENT.DIARY_DATA.AMOUNT1 = BPCUFEEC.FEE_AMT;
            BPCUMENT.DIARY_DATA.AMOUNT4 = BPCUFEEC.FEE_AMT;
            BPCUMENT.DIARY_DATA.AMOUNT5 = BPCUFEEC.FEE_AMT;
            if (WS_NXT_SETTLE_DATE2 == 0) {
                WS_START_DATE = BPRFSCH.START_DATE;
                WS_END_DATE = WS_NXT_SETTLE_DATE;
                B085_01_01_CAL_FEE();
                if (pgmRtn) return;
                BPCUMENT.DIARY_DATA.AMOUNT10 = BPCUFEEC.FEE_AMT;
            }
            if (BPRFSCH.PAY_IND == 'R') {
                BPCUMENT.DIARY_DATA.AMOUNT4 = 0;
            } else {
                BPCUMENT.DIARY_DATA.AMOUNT5 = 0;
            }
            BPCUMENT.PROC_DATA.DIARY_CNT = WS_DIARY_CNT;
            if (WS_EX_RATE != 0) {
                BPCUMENT.DIARY_DATA.RATEX3 = WS_EX_RATE;
            }
            BPCUMENT.DIARY_DATA.OTH_CN_NO = BPRFSCH.REL_CTRT_NO;
            BPCUMENT.PROC_DATA.FUNC_CODE = "M2";
            S000_CALL_BPZUMENT();
            if (pgmRtn) return;
        }
    }
    public void B085_03_DEL_EVENTS() throws IOException,SQLException,Exception {
        WS_DIARY_CNT += 1;
        IBS.init(SCCGWA, BPCUMENT);
        BPCUMENT.PROC_DATA.FUNC_CODE = "D2";
        BPCUMENT.DIARY_DATA.CONTRACT_NO = BPCSSCHD.KEY.CTRT_NO;
        BPCUMENT.DIARY_DATA.DIARY_TYPE = "FS";
        if (WS_NXT_SETTLE_DATE == 0) {
            BPCUMENT.DIARY_DATA.DIARY_TYPE = "MA";
        }
        BPCUMENT.DIARY_DATA.DIARY_DATE = BPCSSCHD.KEY.SETTLE_DATE;
        BPCUMENT.PROC_DATA.START_DATE = BPCSSCHD.KEY.SETTLE_DATE;
        BPCUMENT.PROC_DATA.END_DATE = BPCSSCHD.KEY.SETTLE_DATE;
        BPCUMENT.DIARY_DATA.DIARY_BATNO = BPCSGSEQ.SEQ;
        S000_CALL_BPZUMENT();
        if (pgmRtn) return;
        if (WS_NXT_SETTLE_DATE != 0) {
            WS_DIARY_CNT += 1;
            IBS.init(SCCGWA, BPCUMENT);
            BPCUMENT.PROC_DATA.FUNC_CODE = "R ";
            BPCUMENT.DIARY_DATA.CONTRACT_NO = BPCSSCHD.KEY.CTRT_NO;
            BPCUMENT.DIARY_DATA.DIARY_TYPE = "FS";
            if (WS_NXT_SETTLE_DATE2 == 0) {
                BPCUMENT.DIARY_DATA.DIARY_TYPE = "MA";
            }
            BPCUMENT.DIARY_DATA.DIARY_DATE = WS_NXT_SETTLE_DATE;
            S000_CALL_BPZUMENT();
            if (pgmRtn) return;
            BPCUMENT.DIARY_DATA.TRN_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCUMENT.DIARY_DATA.TRN_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
            BPCUMENT.DIARY_DATA.DIARY_BATNO = BPCSGSEQ.SEQ;
            if (WS_PRE_SETTLE_DT == 0) {
                WS_START_DATE = BPRFSCH.START_DATE;
            } else {
                WS_START_DATE = WS_PRE_SETTLE_DT;
            }
            WS_END_DATE = WS_NXT_SETTLE_DATE;
            B085_01_01_CAL_FEE();
            if (pgmRtn) return;
            if (BPCUMENT.DIARY_DATA.DIARY_TYPE.equalsIgnoreCase("MA")) {
                B150_CAL_LAST_FEE_AMT();
                if (pgmRtn) return;
            }
            BPCUMENT.DIARY_DATA.AMOUNT1 = BPCUFEEC.FEE_AMT;
            BPCUMENT.DIARY_DATA.AMOUNT4 = BPCUFEEC.FEE_AMT;
            BPCUMENT.DIARY_DATA.AMOUNT5 = BPCUFEEC.FEE_AMT;
            if (WS_NXT_SETTLE_DATE2 == 0) {
                BPCUMENT.DIARY_DATA.DIARY_TYPE = "MA";
                BPCUMENT.DIARY_DATA.D_REFNO = 9999;
                BPCUMENT.DIARY_DATA.AMOUNT10 = BPRFSCH.CHARGE_AMT;
                if (BPRFSCH.CHARGE_AMT == 0) {
                    WS_START_DATE = BPRFSCH.START_DATE;
                    WS_END_DATE = WS_NXT_SETTLE_DATE;
                    B085_01_01_CAL_FEE();
                    if (pgmRtn) return;
                    BPCUMENT.DIARY_DATA.AMOUNT10 = BPCUFEEC.FEE_AMT;
                }
            }
            if (BPRFSCH.PAY_IND == 'R') {
                BPCUMENT.DIARY_DATA.AMOUNT4 = 0;
            } else {
                BPCUMENT.DIARY_DATA.AMOUNT5 = 0;
            }
            BPCUMENT.PROC_DATA.DIARY_CNT = WS_DIARY_CNT;
            if (WS_EX_RATE != 0) {
                BPCUMENT.DIARY_DATA.RATEX3 = WS_EX_RATE;
            }
            BPCUMENT.DIARY_DATA.OTH_CN_NO = BPRFSCH.REL_CTRT_NO;
            BPCUMENT.PROC_DATA.FUNC_CODE = "M2";
            S000_CALL_BPZUMENT();
            if (pgmRtn) return;
        }
        if (WS_PRE_SETTLE_DT != 0) {
            WS_DIARY_CNT += 1;
            IBS.init(SCCGWA, BPCUMENT);
            BPCUMENT.PROC_DATA.FUNC_CODE = "R ";
            BPCUMENT.DIARY_DATA.CONTRACT_NO = BPCSSCHD.KEY.CTRT_NO;
            BPCUMENT.DIARY_DATA.DIARY_TYPE = "FS";
            BPCUMENT.DIARY_DATA.DIARY_DATE = WS_PRE_SETTLE_DT;
            S000_CALL_BPZUMENT();
            if (pgmRtn) return;
            BPCUMENT.DIARY_DATA.TRN_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCUMENT.DIARY_DATA.TRN_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
            BPCUMENT.DIARY_DATA.DIARY_BATNO = BPCSGSEQ.SEQ;
            if (WS_NXT_SETTLE_DATE == 0) {
                BPCUMENT.DIARY_DATA.DIARY_TYPE = "MA";
                BPCUMENT.DIARY_DATA.D_REFNO = 9999;
                B150_CAL_LAST_FEE_AMT();
                if (pgmRtn) return;
                BPCUMENT.DIARY_DATA.AMOUNT1 = BPCUFEEC.FEE_AMT;
                BPCUMENT.DIARY_DATA.AMOUNT4 = BPCUFEEC.FEE_AMT;
                BPCUMENT.DIARY_DATA.AMOUNT5 = BPCUFEEC.FEE_AMT;
                if (BPRFSCH.PAY_IND == 'R') {
                    BPCUMENT.DIARY_DATA.AMOUNT4 = 0;
                } else {
                    BPCUMENT.DIARY_DATA.AMOUNT5 = 0;
                }
                BPCUMENT.DIARY_DATA.AMOUNT10 = BPRFSCH.CHARGE_AMT;
                if (BPRFSCH.CHARGE_AMT == 0) {
                    WS_START_DATE = BPRFSCH.START_DATE;
                    WS_END_DATE = WS_PRE_SETTLE_DT;
                    B085_01_01_CAL_FEE();
                    if (pgmRtn) return;
                    BPCUMENT.DIARY_DATA.AMOUNT10 = BPCUFEEC.FEE_AMT;
                }
            }
            BPCUMENT.PROC_DATA.DIARY_CNT = WS_DIARY_CNT;
            if (WS_EX_RATE != 0) {
                BPCUMENT.DIARY_DATA.RATEX3 = WS_EX_RATE;
            }
            BPCUMENT.DIARY_DATA.OTH_CN_NO = BPRFSCH.REL_CTRT_NO;
            BPCUMENT.PROC_DATA.FUNC_CODE = "M2";
            S000_CALL_BPZUMENT();
            if (pgmRtn) return;
        }
    }
    public void B085_90_MOVE_AC_IFNO() throws IOException,SQLException,Exception {
        if (BPRFSCH.PAY_IND == 'R') {
            if (BPRFSCH.CHARGE_METHOD == '0' 
                || BPRFSCH.CHARGE_METHOD == '1' 
                || BPRFSCH.CHARGE_METHOD == '2') {
                BPCUMENT.DIARY_DATA.RCV_DD_NO1 = BPRFSCH.CHARGE_AC;
                if (BPRFSCH.CHARGE_METHOD == '2') {
                    BPCUMENT.DIARY_DATA.DD_CHQ_NO1 = BPRFSCH.CHQ_NO;
                }
            } else {
                BPCUMENT.DIARY_DATA.RCV_NOSTRO_AC = BPRFSCH.NOSTRO_AC;
            }
        } else {
            if (BPRFSCH.CHARGE_METHOD == '0' 
                || BPRFSCH.CHARGE_METHOD == '1' 
                || BPRFSCH.CHARGE_METHOD == '2') {
                BPCUMENT.DIARY_DATA.PAY_DD_NO1 = BPRFSCH.CHARGE_AC;
                if (BPRFSCH.CHARGE_METHOD == '2') {
                    BPCUMENT.DIARY_DATA.DD_CHQ_NO1 = BPRFSCH.CHQ_NO;
                }
            } else {
                BPCUMENT.DIARY_DATA.PAY_NOSTRO_AC = BPRFSCH.NOSTRO_AC;
            }
        }
    }
    public void B088_WF_REF_NO() throws IOException,SQLException,Exception {
    }
    public void B089_DATA_OUTPUT_REVE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_REVE_DATA);
        WS_REVE_DATA.WS_INPUT_DATE = BPRFSCHD.REAL_AC_DATE;
        WS_REVE_DATA.WS_JRN_NO = BPRFSCHD.JNL_NO;
        WS_REVE_DATA.WS_ORI_TXN_CODE = "9999156";
        WS_REVE_DATA.WS_VOUCHER_NO = BPRFSCHD.VOUCHER_NO;
        WS_REVE_DATA.WS_PRINT_FLAG = 'N';
        CEP.TRC(SCCGWA, WS_REVE_DATA.WS_INPUT_DATE);
        CEP.TRC(SCCGWA, WS_REVE_DATA.WS_JRN_NO);
        CEP.TRC(SCCGWA, WS_REVE_DATA.WS_ORI_TXN_CODE);
        CEP.TRC(SCCGWA, WS_REVE_DATA.WS_VOUCHER_NO);
        CEP.TRC(SCCGWA, WS_REVE_DATA.WS_PRINT_FLAG);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_X;
        SCCFMT.DATA_PTR = WS_REVE_DATA;
        SCCFMT.DATA_LEN = 40;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B089_DATA_OUTPUT_SETT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSSET1);
        BPCSSET1.CTRT_NO = BPCSSCHD.KEY.CTRT_NO;
        BPCSSET1.REL_CTRT_NO = BPCSSCHD.INF.REL_CTRT_NO;
        BPCSSET1.CI_NO = BPCSSCHD.INF.CI_NO;
        if (BPCSSCHD.INF.CI_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CICCUST);
            CICCUST.DATA.CI_NO = BPCSSCHD.INF.CI_NO;
            CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            if (CICCUST.O_DATA.O_CI_NM.trim().length() > 0) {
                BPCSSET1.ABBR_NAME = CICCUST.O_DATA.O_CI_NM;
            } else {
                BPCSSET1.ABBR_NAME = CICCUST.O_DATA.O_CI_ENM;
            }
        }
        CEP.TRC(SCCGWA, BPCSSET1.ABBR_NAME);
        BPCSSET1.FEE_TYPE = BPCSSCHD.INF.FEE_TYPE;
        CEP.TRC(SCCGWA, "111111111");
        BPCSSET1.PAY_IND = BPCSSCHD.INF.PAY_IND;
        CEP.TRC(SCCGWA, "111111112");
        BPCSSET1.SETTLE_DATE = BPCSSCHD.KEY.SETTLE_DATE;
        CEP.TRC(SCCGWA, "111111113");
        BPCSSET1.CHARGE_CCY = BPCSSCHD.INF.CHARGE_CCY;
        CEP.TRC(SCCGWA, "111111114");
        BPCSSET1.CHARGE_AMT = BPCSSCHD.INF.CHARGE_AMT;
        CEP.TRC(SCCGWA, "111111115");
        if (BPCSSCHD.FUNC_OPT == 'S') {
            CEP.TRC(SCCGWA, "111111116");
            WS_REAL_CHG_AMT = 0;
            WS_REAL_CHG_AMT = BPCSSCHD.INF.CHARGE_AMT;
            CEP.TRC(SCCGWA, WS_REAL_CHG_AMT);
            if (WS_REAL_CHG_AMT > 0) {
                CEP.TRC(SCCGWA, "111111117");
                if (BPRFSCH.FEE_TYPE.equalsIgnoreCase(K_FEE_CODE) 
                    && BPRFSCH.FIR_DSCT > 0) {
                    BPCSSET1.CHARGE_AMT = WS_REAL_CHG_AMT * BPRFSCH.FIR_DSCT / 100;
                    if (BPCSSET1.CHARGE_AMT < BPRFSCH.FCHG_MIN_AMT) {
                        BPCSSET1.CHARGE_AMT = BPRFSCH.FCHG_MIN_AMT;
                    }
                } else {
                    if (WS_REAL_CHG_AMT < BPRFSCH.FCHG_MIN_AMT) {
                        BPCSSET1.CHARGE_AMT = BPRFSCH.FCHG_MIN_AMT;
                    } else {
                        BPCSSET1.CHARGE_AMT = WS_REAL_CHG_AMT;
                    }
                }
            } else {
                BPCSSET1.CHARGE_AMT = 0;
            }
            BPCSSET1.CHARGE_AMT = BPCSSET1.CHARGE_AMT + BPCSSCHD.INF.ADJUST_AMT;
        }
        CEP.TRC(SCCGWA, BPCSSET1.CHARGE_AMT);
        BPCSSET1.CHARGE_METHOD = BPRFSCH.CHARGE_METHOD;
        BPCSSET1.CHARGE_AC = BPRFSCH.CHARGE_AC;
        BPCSSET1.NOSTRO_AC_CD = BPRFSCH.NOSTRO_AC;
        BPCSSET1.TICKET = " ";
        BPCSSET1.AMT_REAL = 0;
        BPCSSET1.CCY_REAL = BPRFSCH.CHG_CCY_REAL;
        BPCSSET1.RATE = 0;
        BPCSSET1.EXG_DT = 0;
        BPCSSET1.EXG_TM = 0;
        BPCSSET1.REMARK = BPRFSCHD.REMARK;
        CEP.TRC(SCCGWA, BPCSSET1.CTRT_NO);
        CEP.TRC(SCCGWA, BPCSSET1.REL_CTRT_NO);
        CEP.TRC(SCCGWA, BPCSSET1.CI_NO);
        CEP.TRC(SCCGWA, BPCSSET1.FEE_TYPE);
        CEP.TRC(SCCGWA, BPCSSET1.PAY_IND);
        CEP.TRC(SCCGWA, BPCSSET1.SETTLE_DATE);
        CEP.TRC(SCCGWA, BPCSSET1.CHARGE_CCY);
        CEP.TRC(SCCGWA, BPCSSET1.CHARGE_METHOD);
        CEP.TRC(SCCGWA, BPCSSET1.CHARGE_AC);
        CEP.TRC(SCCGWA, BPCSSET1.NOSTRO_AC_CD);
        CEP.TRC(SCCGWA, BPCSSET1.TICKET);
        CEP.TRC(SCCGWA, BPCSSET1.AMT_REAL);
        CEP.TRC(SCCGWA, BPCSSET1.CCY_REAL);
        CEP.TRC(SCCGWA, BPCSSET1.RATE);
        CEP.TRC(SCCGWA, BPCSSET1.EXG_DT);
        CEP.TRC(SCCGWA, BPCSSET1.EXG_TM);
        CEP.TRC(SCCGWA, BPCSSET1.REMARK);
        CEP.TRC(SCCGWA, BPCSSET1);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_7;
        SCCFMT.DATA_PTR = BPCSSET1;
        SCCFMT.DATA_LEN = 583;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B090_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSCHD1);
        B090_01_TRANS_DATA_OUPUT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSCHD1.CHARGE_AMT);
        IBS.init(SCCGWA, SCCFMT);
        if (BPCSSCHD.FUNC == 'Q') {
            SCCFMT.FMTID = K_OUTPUT_FMT_8;
        } else {
            SCCFMT.FMTID = K_OUTPUT_FMT_9;
        }
        if (BPCSSCHD.FUNC == 'Q' 
            && BPCSSCHD.FUNC_OPT == 'S') {
            SCCFMT.FMTID = K_OUTPUT_FMT_X;
        }
        SCCFMT.DATA_PTR = BPCSCHD1;
        SCCFMT.DATA_LEN = 507;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B090_01_TRANS_DATA_OUPUT() throws IOException,SQLException,Exception {
        BPCSCHD1.CTRT_NO = BPCSSCHD.KEY.CTRT_NO;
        BPCSCHD1.REL_CTRT_NO = BPCSSCHD.INF.REL_CTRT_NO;
        BPCSCHD1.CI_NO = BPCSSCHD.INF.CI_NO;
        BPCSCHD1.ABBR_NAME = BPCSSCHD.INF.ABBR_NAME;
        if (BPCSSCHD.INF.CI_NO.trim().length() > 0 
            && BPCSSCHD.INF.ABBR_NAME.trim().length() == 0) {
            IBS.init(SCCGWA, CICCUST);
            CICCUST.DATA.CI_NO = BPCSSCHD.INF.CI_NO;
            CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            if (CICCUST.O_DATA.O_CI_NM.trim().length() > 0) {
                BPCSCHD1.ABBR_NAME = CICCUST.O_DATA.O_CI_NM;
            } else {
                BPCSCHD1.ABBR_NAME = CICCUST.O_DATA.O_CI_ENM;
            }
            BPCSSCHD.INF.ABBR_NAME = BPCSCHD1.ABBR_NAME;
        }
        BPCSCHD1.PRD_TYPE = BPCSSCHD.INF.PRD_TYPE;
        BPCSCHD1.FEE_TYPE = BPCSSCHD.INF.FEE_TYPE;
        BPCSCHD1.PAY_IND = BPCSSCHD.INF.PAY_IND;
        BPCSCHD1.SETTLE_DATE = BPCSSCHD.KEY.SETTLE_DATE;
        BPCSCHD1.CHARGE_CCY = BPCSSCHD.INF.CHARGE_CCY;
        BPCSCHD1.CHARGE_AMT = BPCSSCHD.INF.CHARGE_AMT;
        BPCSCHD1.ADJUST_AMT = BPCSSCHD.INF.ADJUST_AMT;
        BPCSCHD1.REMARK = BPCSSCHD.INF.REMARK;
        if (BPCSSCHD.FUNC_OPT == 'S') {
            WS_REAL_CHG_AMT = 0;
            WS_REAL_CHG_AMT = BPCSCHD1.CHARGE_AMT + BPCSCHD1.ADJUST_AMT;
            if (WS_REAL_CHG_AMT > 0) {
                BPCSCHD1.CHARGE_AMT = WS_REAL_CHG_AMT;
            } else {
                BPCSCHD1.CHARGE_AMT = 0;
            }
        }
    }
    public void R000_TRANS_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSSCHD.KEY.CTRT_NO);
        CEP.TRC(SCCGWA, BPCSSCHD.KEY.SETTLE_DATE);
        CEP.TRC(SCCGWA, BPCSSCHD.INF.CHARGE_CCY);
        CEP.TRC(SCCGWA, BPCSSCHD.INF.CHARGE_AMT);
        CEP.TRC(SCCGWA, BPCSSCHD.INF.ADJUST_AMT);
        CEP.TRC(SCCGWA, BPCSSCHD.INF.REMARK);
        BPRFSCHD.KEY.CTRT_NO = BPCSSCHD.KEY.CTRT_NO;
        BPRFSCHD.KEY.SETTLE_DATE = BPCSSCHD.KEY.SETTLE_DATE;
        BPRFSCHD.CHARGE_CCY = BPCSSCHD.INF.CHARGE_CCY;
        BPRFSCHD.CHARGE_AMT = BPCSSCHD.INF.CHARGE_AMT;
        BPRFSCHD.ADJ_AMT = BPCSSCHD.INF.ADJUST_AMT;
        BPRFSCHD.REMARK = BPCSSCHD.INF.REMARK;
        if (BPCSSCHD.FUNC == 'A') {
            BPRFSCHD.STS = 'N';
        }
        if (BPCSSCHD.FUNC == 'U') {
            BPRFSCHD.STS = BPRSCHDO.STS;
            BPRFSCHD.FAIL_REASON = BPRSCHDO.FAIL_REASON;
            BPRFSCHD.FAIL_CNT = BPRSCHDO.FAIL_CNT;
        }
        BPRFSCH.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRFSCH.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
        BPRFSCH.SUP_TEL1 = SCCGWA.COMM_AREA.SUP1_ID;
        BPRFSCH.SUP_TEL2 = SCCGWA.COMM_AREA.SUP2_ID;
    }
    public void B100_CAL_FEE_AMT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_PRE_SETTLE_DATE);
        CEP.TRC(SCCGWA, BPCSSCHD.KEY.SETTLE_DATE);
        CEP.TRC(SCCGWA, BPRFSCHD.KEY.SETTLE_DATE);
        if (WS_PRE_SETTLE_DATE == 0) {
            WS_START_DATE = BPRFSCH.START_DATE;
        } else {
            WS_START_DATE = WS_PRE_SETTLE_DATE;
        }
        WS_END_DATE = BPCSSCHD.KEY.SETTLE_DATE;
        if (WS_BROWSE_FLAG == 'Y') {
            WS_END_DATE = BPRFSCHD.KEY.SETTLE_DATE;
        }
        CEP.TRC(SCCGWA, WS_START_DATE);
        CEP.TRC(SCCGWA, WS_END_DATE);
        if (BPRFSCH.ACCRUAL_TYPE == 'F' 
            || BPRFSCH.ACCRUAL_TYPE == 'B') {
            if (BPRFSCH.ACCRUAL_TYPE == 'B' 
                && WS_FINAL_FLAG == 'Y') {
            } else {
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = WS_END_DATE;
                SCCCLDT.DAYS = -1;
                S000_CALL_SCSSCLDT();
                if (pgmRtn) return;
                WS_END_DATE = SCCCLDT.DATE2;
            }
        }
        if (BPRFSCH.ACCRUAL_TYPE == 'L') {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = WS_START_DATE;
            SCCCLDT.DAYS = 1;
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            WS_START_DATE = SCCCLDT.DATE2;
        }
        CEP.TRC(SCCGWA, WS_START_DATE);
        CEP.TRC(SCCGWA, WS_END_DATE);
        CEP.TRC(SCCGWA, BPCSSCHD.KEY.CTRT_NO);
        IBS.init(SCCGWA, BPCUFEEC);
        BPCUFEEC.CTRT_NO = BPCSSCHD.KEY.CTRT_NO;
        BPCUFEEC.CTRT_TYP = 'S';
        BPCUFEEC.STA_DT = WS_START_DATE;
        BPCUFEEC.END_DT = WS_END_DATE;
        BPCUFEEC.RUN_MODE = 'O';
        CEP.TRC(SCCGWA, BPCUFEEC.FEE_AMT);
        CEP.TRC(SCCGWA, BPCUFEEC.EX_RATE);
        if (WS_EX_RATE == 0) {
            WS_EX_RATE = BPCUFEEC.EX_RATE;
        }
    }
    public void B100_CAL_FEE_AMT_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_PRE_SETTLE_DATE);
        CEP.TRC(SCCGWA, BPCSSCHD.KEY.SETTLE_DATE);
        CEP.TRC(SCCGWA, BPRFSCHD.KEY.SETTLE_DATE);
        if (WS_PRE_SETTLE_DATE == 0) {
            WS_START_DATE = BPRFSCH.START_DATE;
        } else {
            WS_START_DATE = WS_PRE_SETTLE_DATE;
        }
        if (WS_NEXT_SETTLE_DATE == 0) {
            WS_END_DATE = BPCSSCHD.KEY.SETTLE_DATE;
        } else {
            WS_END_DATE = WS_NEXT_SETTLE_DATE;
        }
        CEP.TRC(SCCGWA, WS_START_DATE);
        CEP.TRC(SCCGWA, WS_END_DATE);
        if (WS_BROWSE_FLAG == 'Y') {
            WS_END_DATE = BPRFSCHD.KEY.SETTLE_DATE;
        }
        CEP.TRC(SCCGWA, WS_START_DATE);
        CEP.TRC(SCCGWA, WS_END_DATE);
        if (BPRFSCH.ACCRUAL_TYPE == 'F' 
            || BPRFSCH.ACCRUAL_TYPE == 'B') {
            if (BPRFSCH.ACCRUAL_TYPE == 'B' 
                && WS_FINAL_FLAG == 'Y') {
            } else {
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = WS_END_DATE;
                SCCCLDT.DAYS = -1;
                S000_CALL_SCSSCLDT();
                if (pgmRtn) return;
                WS_END_DATE = SCCCLDT.DATE2;
            }
        }
        if (BPRFSCH.ACCRUAL_TYPE == 'L') {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = WS_START_DATE;
            SCCCLDT.DAYS = 1;
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            WS_START_DATE = SCCCLDT.DATE2;
        }
        CEP.TRC(SCCGWA, WS_START_DATE);
        CEP.TRC(SCCGWA, WS_END_DATE);
        CEP.TRC(SCCGWA, BPCSSCHD.KEY.CTRT_NO);
        IBS.init(SCCGWA, BPCUFEEC);
        BPCUFEEC.CTRT_NO = BPCSSCHD.KEY.CTRT_NO;
        BPCUFEEC.CTRT_TYP = 'S';
        BPCUFEEC.STA_DT = WS_START_DATE;
        BPCUFEEC.END_DT = WS_END_DATE;
        BPCUFEEC.RUN_MODE = 'O';
        WS_CAL_LN_FEE_FLAG = 'N';
        CEP.TRC(SCCGWA, WS_CNT1);
        CEP.TRC(SCCGWA, BPCSSCHD.FUNC);
        if (!(BPCSSCHD.FUNC == '1' 
            || BPCSSCHD.FUNC == '2' 
            || BPCSSCHD.FUNC == '3')) {
            CEP.TRC(SCCGWA, "NOT BROWSE");
            WS_CNT1 = 1;
        }
        CEP.TRC(SCCGWA, WS_CNT1);
        if (BPRFSCH.REL_CTRT_NO.trim().length() > 0 
            && (BPRFSCH.AMT_TYPE == 11 
            || BPRFSCH.AMT_TYPE == 12 
            || BPRFSCH.AMT_TYPE == 13) 
            && WS_CNT1 < 21) {
            CEP.TRC(SCCGWA, "CALL LNZSSCHE FOR AMT");
            CEP.TRC(SCCGWA, LNCSSCHE.OUT_INFO[WS_CNT1-1].O_PAY_AMT);
            CEP.TRC(SCCGWA, LNCSSCHE.OUT_INFO[WS_CNT1-1].O_PAY_PRIN);
            CEP.TRC(SCCGWA, LNCSSCHE.OUT_INFO[WS_CNT1-1].O_PAY_INT);
            if (BPRFSCH.AMT_TYPE == 11 
                && LNCSSCHE.OUT_INFO[WS_CNT1-1].O_PAY_AMT != 0) {
                BPCUFEEC.CAL_AMT = LNCSSCHE.OUT_INFO[WS_CNT1-1].O_PAY_AMT;
                WS_CAL_LN_FEE_FLAG = 'Y';
            }
            if (BPRFSCH.AMT_TYPE == 12 
                && LNCSSCHE.OUT_INFO[WS_CNT1-1].O_PAY_PRIN != 0) {
                BPCUFEEC.CAL_AMT = LNCSSCHE.OUT_INFO[WS_CNT1-1].O_PAY_PRIN;
                WS_CAL_LN_FEE_FLAG = 'Y';
            }
            if (BPRFSCH.AMT_TYPE == 13 
                && LNCSSCHE.OUT_INFO[WS_CNT1-1].O_PAY_INT != 0) {
                BPCUFEEC.CAL_AMT = LNCSSCHE.OUT_INFO[WS_CNT1-1].O_PAY_INT;
                WS_CAL_LN_FEE_FLAG = 'Y';
            }
        }
        CEP.TRC(SCCGWA, BPCUFEEC.CAL_AMT);
        if (WS_CAL_LN_FEE_FLAG == 'N' 
            && BPRFSCH.REL_CTRT_NO.trim().length() > 0 
            && (BPRFSCH.AMT_TYPE == 11 
            || BPRFSCH.AMT_TYPE == 12 
            || BPRFSCH.AMT_TYPE == 13)) {
            CEP.TRC(SCCGWA, "NOT CALCULATING LN FEE");
        } else {
            S000_CALL_BPZUFEEC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCUFEEC.FEE_AMT);
        CEP.TRC(SCCGWA, BPCUFEEC.EX_RATE);
        if (WS_EX_RATE == 0) {
            WS_EX_RATE = BPCUFEEC.EX_RATE;
        }
    }
    public void B110_GET_LAST_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFSCHD);
        IBS.init(SCCGWA, BPCRSCHD);
        BPRFSCHD.KEY.CTRT_NO = BPCSSCHD.KEY.CTRT_NO;
        BPRFSCHD.KEY.SETTLE_DATE = BPCSSCHD.KEY.SETTLE_DATE;
        BPCRSCHD.INFO.FUNC = 'B';
        BPCRSCHD.INFO.OPT = 'A';
        S000_CALL_BPZRSCHD();
        if (pgmRtn) return;
    }
    public void B120_CHECK_FINAL_FLAG() throws IOException,SQLException,Exception {
        WS_FINAL_FLAG = 'N';
        IBS.init(SCCGWA, BPRFSCHD);
        IBS.init(SCCGWA, BPCRSCHD);
        BPRFSCHD.KEY.CTRT_NO = BPCSSCHD.KEY.CTRT_NO;
        BPRFSCHD.KEY.SETTLE_DATE = BPCSSCHD.KEY.SETTLE_DATE;
        BPCRSCHD.INFO.FUNC = 'B';
        BPCRSCHD.INFO.OPT = 'B';
        S000_CALL_BPZRSCHD();
        if (pgmRtn) return;
        if (BPCRSCHD.RETURN_INFO == 'N') {
            WS_FINAL_FLAG = 'Y';
        }
    }
    public void B150_CAL_LAST_FEE_AMT() throws IOException,SQLException,Exception {
        WS_BROWSE_FLAG = 'Y';
        B050_01_01_BRW_SCHD();
        if (pgmRtn) return;
        WS_BROWSE_FLAG = 'N';
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
    public void S000_CALL_BPZUFEEC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-FEE-CAL", BPCUFEEC);
        if (BPCUFEEC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUFEEC.RC);
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
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSSCHE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-INQ-SSCHE", LNCSSCHE);
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
