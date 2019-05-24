package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.LN.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

import java.io.IOException;
import java.sql.SQLException;

public class BPZUFEEC {
    BigDecimal bigD;
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZUFEEC";
    int K_IAMT_IDX = 1000;
    String PGM_SCSSCKDT = "SCSSCKDT";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String CPN_GET_FEE_SCH = "BP-R-MGM-FEESCH     ";
    String CPN_GET_FEE_CTR = "BP-R-MGM-FEECTR     ";
    String CPN_GET_FEE_PARM = "BP-R-MGM-CAL-PARM   ";
    String CPN_EXG_CURRENCY = "BP-EX               ";
    String CPN_GET_REF_AMT = "BP-R-MGM-REF-AMT    ";
    String CPN_GET_LN_AMT = "BP-XXXXXXXXXXXXXXXXX";
    String K_FEE_CODE = "FII01";
    String WS_TEMP_RECORD = " ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CNT1 = 0;
    int WS_IAMT_IDX = 0;
    int WS_DATE = 0;
    char WS_ACCR_TYPE = ' ';
    double WS_ACCR_AMT = 0;
    short WS_AMT_TYPE = 0;
    String WS_FEE_TYPE = " ";
    int WS_PROC_DT = 0;
    double WS_TXN_AMT = 0;
    String WS_TXN_CCY = " ";
    char WS_REL_CTRT_SRC = ' ';
    long WS_REL_COL_NO = 0;
    long WS_REF_LMT_NO = 0;
    String WS_REF_CTRT = " ";
    int WS_STA_DT = 0;
    int WS_END_DT = 0;
    char WS_FRAH_STARTBR_FLG = ' ';
    char WS_CALL_LNZIAMT_FLG = ' ';
    char WS_CALL_CLZQCHA_FLG = ' ';
    char WS_CALL_CLZIAMT_FLG = ' ';
    char WS_FCPH_STARTBR_FLG = ' ';
    BPZUFEEC_WS_REF_AMT_AREA WS_REF_AMT_AREA = new BPZUFEEC_WS_REF_AMT_AREA();
    BPZUFEEC_WS_CHG_AREA WS_CHG_AREA = new BPZUFEEC_WS_CHG_AREA();
    BPZUFEEC_WS_CAL_AREA WS_CAL_AREA = new BPZUFEEC_WS_CAL_AREA();
    BPZUFEEC_WS_PARM_AREA WS_PARM_AREA = new BPZUFEEC_WS_PARM_AREA();
    BPZUFEEC_WS_TEMP_ARRAY[] WS_TEMP_ARRAY = new BPZUFEEC_WS_TEMP_ARRAY[5];
    BPZUFEEC_WS_T1 WS_T1 = new BPZUFEEC_WS_T1();
    BPZUFEEC_WS_T2 WS_T2 = new BPZUFEEC_WS_T2();
    int WS_TEMP_DT = 0;
    BPZUFEEC_REDEFINES65 REDEFINES65 = new BPZUFEEC_REDEFINES65();
    int WS_TEMP_DT2 = 0;
    BPZUFEEC_REDEFINES70 REDEFINES70 = new BPZUFEEC_REDEFINES70();
    String WS_EX_FROM_CCY = " ";
    String WS_EX_TO_CCY = " ";
    char WS_TBL_FARM_FLAG = ' ';
    char WS_AGG_MTH_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCFX BPCFX = new BPCFX();
    BPCFPARM BPCFPARM = new BPCFPARM();
    BPCFFTOT BPCFFTOT = new BPCFFTOT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCRFRAH BPCRFRAH = new BPCRFRAH();
    BPCRFSCH BPCRFSCH = new BPCRFSCH();
    BPCTFBAS BPCTFBAS = new BPCTFBAS();
    BPCRFCTR BPCRFCTR = new BPCRFCTR();
    BPCRCPHM BPCRCPHM = new BPCRCPHM();
    BPCRDAMT BPCRDAMT = new BPCRDAMT();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCIDAY BPCIDAY = new BPCIDAY();
    LNCIAMT LNCIAMT = new LNCIAMT();
    LNCSSCHE LNCSSCHE = new LNCSSCHE();
    BPRFRAH BPRFRAH = new BPRFRAH();
    BPRFRAH BPRFRAHO = new BPRFRAH();
    BPRFCPH BPRFCPH = new BPRFCPH();
    BPRFCPH BPRFCPHO = new BPRFCPH();
    BPRFCTR BPRFCTR = new BPRFCTR();
    BPRFSCH BPRFSCH = new BPRFSCH();
    BPRFBAS BPRFBAS = new BPRFBAS();
    SCCGWA SCCGWA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPCUFEEC BPCUFEEC;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public BPZUFEEC() {
        for (int i=0;i<5;i++) WS_TEMP_ARRAY[i] = new BPZUFEEC_WS_TEMP_ARRAY();
    }
    public void MP(SCCGWA SCCGWA, BPCUFEEC BPCUFEEC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCUFEEC = BPCUFEEC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZUFEEC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        BPCUFEEC.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        BPCUFEEC.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B001_CHECK_INPUT();
        if (pgmRtn) return;
        B002_GET_WORK_INFO();
        if (pgmRtn) return;
        if (WS_CHG_AREA.WS_CHG_AMT == 0) {
            if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
                B003_CAL_TERM_FEE_CN();
                if (pgmRtn) return;
            } else {
                B003_CAL_TERM_FEE();
                if (pgmRtn) return;
            }
        } else {
            B003_CAL_FIX_FEE();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCUFEEC.EX_RATE);
        if (BPCUFEEC.EX_RATE == 0) {
            B004_GET_EXCHANGE_RATE();
            if (pgmRtn) return;
        }
    }
    public void B001_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCUFEEC.EX_RATE);
        CEP.TRC(SCCGWA, "CHECK START DATE AND END DATE");
        CEP.TRC(SCCGWA, BPCUFEEC.STA_DT);
        if (BPCUFEEC.STA_DT == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_STR_DATE_ERR, BPCUFEEC.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            WS_DATE = BPCUFEEC.STA_DT;
            R000_CHECK_DATE();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCUFEEC.END_DT);
        if (BPCUFEEC.END_DT == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_END_DATE_ERR, BPCUFEEC.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            WS_DATE = BPCUFEEC.END_DT;
            R000_CHECK_DATE();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCUFEEC.STA_DT);
        CEP.TRC(SCCGWA, BPCUFEEC.END_DT);
        if (BPCUFEEC.STA_DT > BPCUFEEC.END_DT) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_END_GT_STR, BPCUFEEC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "CHECK RUN MODE");
        CEP.TRC(SCCGWA, BPCUFEEC.RUN_MODE);
        if ((BPCUFEEC.RUN_MODE != 'B' 
            && BPCUFEEC.RUN_MODE != 'O')) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERR, BPCUFEEC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "CHECK PARTICIPANT BANK INFO");
        CEP.TRC(SCCGWA, BPCUFEEC.CI_ATTR);
        CEP.TRC(SCCGWA, BPCUFEEC.CI_NO);
        if (BPCUFEEC.CI_ATTR != ' ' 
            || BPCUFEEC.CI_NO.trim().length() > 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERR, BPCUFEEC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "CHECK CONTRACT NO");
        CEP.TRC(SCCGWA, BPCUFEEC.CTRT_NO);
        CEP.TRC(SCCGWA, BPCUFEEC.CTRT_TYP);
        if (BPCUFEEC.CTRT_NO.trim().length() == 0 
            || BPCUFEEC.CTRT_TYP == ' ') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CTRT_NO_MUST_INPUT, BPCUFEEC.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            if ((BPCUFEEC.CTRT_TYP != 'C' 
                && BPCUFEEC.CTRT_TYP != 'S')) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERR, BPCUFEEC.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B002_GET_WORK_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "INQ BPTFCTR");
        if (BPCUFEEC.CTRT_TYP == 'C') {
            IBS.init(SCCGWA, BPRFCTR);
            IBS.init(SCCGWA, BPCRFCTR);
            BPCRFCTR.INFO.POINTER = BPRFCTR;
            BPCRFCTR.INFO.REC_LEN = 889;
            BPRFCTR.KEY.CTRT_NO = BPCUFEEC.CTRT_NO;
            BPCRFCTR.INFO.FUNC = 'Q';
            S000_CALL_BPZRFCTR();
            if (pgmRtn) return;
            if (BPCTFBAS.RETURN_INFO == 'N') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_F_CTRT_NOTFND, BPCUFEEC.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            WS_AMT_TYPE = BPRFCTR.AMT_TYPE;
            WS_FEE_TYPE = BPRFCTR.FEE_TYPE;
            WS_TXN_AMT = BPRFCTR.PRIN_AMT;
            WS_TXN_CCY = BPRFCTR.TXN_CCY;
            WS_REF_CTRT = BPRFCTR.REL_CTRT_NO;
            WS_CHG_AREA.WS_CHG_AMT = BPRFCTR.CHARGE_AMT;
            WS_CHG_AREA.WS_CHG_CCY = BPRFCTR.CHARGE_CCY;
            WS_STA_DT = BPRFCTR.START_DATE;
            WS_END_DT = BPRFCTR.MATURITY_DATE;
            WS_ACCR_TYPE = BPRFCTR.ACCRUAL_TYPE;
            if (BPCUFEEC.FEE_CCY.trim().length() == 0) {
                BPCUFEEC.FEE_CCY = BPRFCTR.CHARGE_CCY;
            }
            WS_EX_TO_CCY = BPRFCTR.CHARGE_CCY;
        }
        CEP.TRC(SCCGWA, "INQ BPTFSCH");
        if (BPCUFEEC.CTRT_TYP == 'S') {
            IBS.init(SCCGWA, BPRFSCH);
            IBS.init(SCCGWA, BPCRFSCH);
            BPCRFSCH.INFO.POINTER = BPRFSCH;
            BPCRFSCH.INFO.REC_LEN = 816;
            BPRFSCH.KEY.CTRT_NO = BPCUFEEC.CTRT_NO;
            BPCRFSCH.INFO.FUNC = 'Q';
            S000_CALL_BPZRFSCH();
            if (pgmRtn) return;
            if (BPCRFSCH.RETURN_INFO == 'N') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_F_CTRT_NOTFND, BPCUFEEC.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            WS_AMT_TYPE = BPRFSCH.AMT_TYPE;
            WS_FEE_TYPE = BPRFSCH.FEE_TYPE;
            WS_TXN_AMT = BPRFSCH.PRIN_AMT;
            WS_TXN_CCY = BPRFSCH.TXN_CCY;
            WS_REL_CTRT_SRC = BPRFSCH.REL_CTRT_SRC;
            WS_REL_COL_NO = BPRFSCH.REL_COL_NO;
            WS_REF_LMT_NO = BPRFSCH.REL_LMT_NO;
            WS_REF_CTRT = BPRFSCH.REL_CTRT_NO;
            WS_CHG_AREA.WS_CHG_AMT = BPRFSCH.CHARGE_AMT;
            WS_CHG_AREA.WS_CHG_CCY = BPRFSCH.CHARGE_CCY;
            WS_STA_DT = BPRFSCH.START_DATE;
            WS_END_DT = BPRFSCH.MATURITY_DATE;
            WS_ACCR_TYPE = BPRFSCH.ACCRUAL_TYPE;
            if (BPCUFEEC.FEE_CCY.trim().length() == 0) {
                BPCUFEEC.FEE_CCY = BPRFSCH.CHARGE_CCY;
            }
            WS_EX_TO_CCY = BPRFSCH.CHARGE_CCY;
        }
        CEP.TRC(SCCGWA, WS_AMT_TYPE);
        CEP.TRC(SCCGWA, WS_FEE_TYPE);
        CEP.TRC(SCCGWA, WS_TXN_AMT);
        CEP.TRC(SCCGWA, WS_TXN_CCY);
        CEP.TRC(SCCGWA, WS_REF_CTRT);
        CEP.TRC(SCCGWA, BPCUFEEC.FEE_CCY);
        CEP.TRC(SCCGWA, "GET FEE TYPE");
        IBS.init(SCCGWA, BPRFBAS);
        IBS.init(SCCGWA, BPCTFBAS);
        BPCTFBAS.INFO.POINTER = BPRFBAS;
        BPCTFBAS.INFO.REC_LEN = 312;
        BPRFBAS.KEY.FEE_CODE = WS_FEE_TYPE;
        BPCTFBAS.INFO.FUNC = 'Q';
        S000_CALL_BPZTFBAS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCTFBAS.RC);
        if (BPCTFBAS.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FEE_CODE_NOTFND, BPCUFEEC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "******RESET STA/END DATE******");
        if (BPCUFEEC.STA_DT <= WS_STA_DT) {
            if (WS_ACCR_TYPE == 'B' 
                || WS_ACCR_TYPE == 'F') {
                BPCUFEEC.STA_DT = WS_STA_DT;
            } else {
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = WS_STA_DT;
                SCCCLDT.DAYS = 1;
                S000_CALL_SCSSCLDT();
                if (pgmRtn) return;
                BPCUFEEC.STA_DT = SCCCLDT.DATE2;
            }
        }
        CEP.TRC(SCCGWA, BPCUFEEC.STA_DT);
        CEP.TRC(SCCGWA, WS_ACCR_TYPE);
        CEP.TRC(SCCGWA, BPCUFEEC.END_DT);
        CEP.TRC(SCCGWA, WS_END_DT);
        if (BPCUFEEC.END_DT >= WS_END_DT) {
            if (WS_ACCR_TYPE == 'B' 
                || WS_ACCR_TYPE == 'L') {
                BPCUFEEC.END_DT = WS_END_DT;
            } else {
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = WS_END_DT;
                SCCCLDT.DAYS = -1;
                S000_CALL_SCSSCLDT();
                if (pgmRtn) return;
                BPCUFEEC.END_DT = SCCCLDT.DATE2;
            }
        }
        CEP.TRC(SCCGWA, BPCUFEEC.END_DT);
    }
    public void B003_CAL_FIX_FEE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "******CAL CTRT/SCHD TERM DAYS******");
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = WS_STA_DT;
        SCCCLDT.DATE2 = WS_END_DT;
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        WS_CHG_AREA.WS_CHG_DAYS_TOT = (short) SCCCLDT.DAYS;
        CEP.TRC(SCCGWA, WS_CHG_AREA.WS_CHG_DAYS_TOT);
        if (WS_ACCR_TYPE == 'B') {
            WS_CHG_AREA.WS_CHG_DAYS_TOT += 1;
        }
        CEP.TRC(SCCGWA, WS_CHG_AREA.WS_CHG_DAYS_TOT);
        CEP.TRC(SCCGWA, "******CAL REQ DAYS******");
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = BPCUFEEC.STA_DT;
        SCCCLDT.DATE2 = BPCUFEEC.END_DT;
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        WS_CHG_AREA.WS_CHG_DAYS = (short) SCCCLDT.DAYS;
        WS_CHG_AREA.WS_CHG_DAYS += 1;
        CEP.TRC(SCCGWA, WS_CHG_AREA.WS_CHG_DAYS);
        if (WS_CHG_AREA.WS_CHG_DAYS_TOT == 0) {
            WS_CAL_AREA.WS_CAL_AMT = 0;
        } else {
            WS_CAL_AREA.WS_CAL_AMT = WS_CHG_AREA.WS_CHG_AMT * WS_CHG_AREA.WS_CHG_DAYS / WS_CHG_AREA.WS_CHG_DAYS_TOT;
        }
        WS_EX_FROM_CCY = BPCUFEEC.FEE_CCY;
        WS_EX_FROM_CCY = WS_CHG_AREA.WS_CHG_CCY;
        if (!BPCUFEEC.FEE_CCY.equalsIgnoreCase(WS_CHG_AREA.WS_CHG_CCY)) {
            IBS.init(SCCGWA, BPCFX);
            BPCFX.FUNC = '3';
            BPCFX.EXR_TYPE = BPRFBAS.RATE_GROUP;
            BPCFX.BUY_CCY = WS_CHG_AREA.WS_CHG_CCY;
            BPCFX.BUY_AMT = WS_CAL_AREA.WS_CAL_AMT;
            BPCFX.SELL_CCY = BPCUFEEC.FEE_CCY;
            S000_CALL_BPZSFX();
            if (pgmRtn) return;
            BPCUFEEC.FEE_AMT = BPCFX.SELL_AMT;
        } else {
            BPCUFEEC.FEE_AMT = WS_CAL_AREA.WS_CAL_AMT;
        }
        CEP.TRC(SCCGWA, BPCUFEEC.FEE_AMT);
        if (BPCUFEEC.FEE_AMT != 0) {
            IBS.init(SCCGWA, BPCRDAMT);
            BPCRDAMT.CCY = BPCUFEEC.FEE_CCY;
            BPCRDAMT.AMT = BPCUFEEC.FEE_AMT;
            S000_CALL_BPZRDAMT();
            if (pgmRtn) return;
            BPCUFEEC.FEE_AMT = BPCRDAMT.RESULT_AMT;
            CEP.TRC(SCCGWA, BPCUFEEC.FEE_AMT);
        }
    }
    public void B003_CAL_TERM_FEE() throws IOException,SQLException,Exception {
        WS_PROC_DT = BPCUFEEC.STA_DT;
        while (WS_PROC_DT <= BPCUFEEC.END_DT) {
            R000_GET_NEXT_PARM();
            if (pgmRtn) return;
            R000_GET_NEXT_AMT();
            if (pgmRtn) return;
            R000_CAL_DAYS();
            if (pgmRtn) return;
            R000_GET_NEXT_DATE();
            if (pgmRtn) return;
        }
    }
    public void B003_CAL_TERM_FEE_CN() throws IOException,SQLException,Exception {
        WS_PROC_DT = BPCUFEEC.STA_DT;
        while (WS_PROC_DT <= BPCUFEEC.END_DT) {
            R000_GET_NEXT_PARM();
            if (pgmRtn) return;
            R000_GET_NEXT_AMT_CN();
            if (pgmRtn) return;
            R000_CAL_DAYS();
            if (pgmRtn) return;
            R000_CAL_FEE_CN();
            if (pgmRtn) return;
            R000_GET_NEXT_DATE();
            if (pgmRtn) return;
        }
    }
    public void B004_GET_EXCHANGE_RATE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_EX_FROM_CCY);
        CEP.TRC(SCCGWA, WS_EX_TO_CCY);
        if (WS_EX_FROM_CCY.trim().length() > 0 
            && WS_EX_TO_CCY.trim().length() > 0 
            && !WS_EX_FROM_CCY.equalsIgnoreCase(WS_EX_TO_CCY)) {
            IBS.init(SCCGWA, BPCFX);
            BPCFX.FUNC = '3';
            BPCFX.EXR_TYPE = BPRFBAS.RATE_GROUP;
            BPCFX.BUY_CCY = WS_EX_FROM_CCY;
            BPCFX.BUY_AMT = 10000;
            BPCFX.SELL_CCY = WS_EX_TO_CCY;
            S000_CALL_BPZSFX();
            if (pgmRtn) return;
            BPCUFEEC.EX_RATE = BPCFX.SYS_RATE;
        } else {
            BPCUFEEC.EX_RATE = 1;
        }
        CEP.TRC(SCCGWA, BPCUFEEC.EX_RATE);
    }
    public void R000_CAL_DAYS() throws IOException,SQLException,Exception {
        WS_CAL_AREA.WS_CAL_END_DT = BPCUFEEC.END_DT;
        CEP.TRC(SCCGWA, WS_CAL_AREA.WS_CAL_END_DT);
        CEP.TRC(SCCGWA, WS_REF_AMT_AREA.WS_REF_END_DT);
        if (WS_REF_AMT_AREA.WS_REF_END_DT < WS_CAL_AREA.WS_CAL_END_DT) {
            WS_CAL_AREA.WS_CAL_END_DT = WS_REF_AMT_AREA.WS_REF_END_DT;
        }
        CEP.TRC(SCCGWA, WS_PARM_AREA.WS_PARM_END_DT);
        if (WS_PARM_AREA.WS_PARM_END_DT < WS_CAL_AREA.WS_CAL_END_DT) {
            WS_CAL_AREA.WS_CAL_END_DT = WS_PARM_AREA.WS_PARM_END_DT;
        }
        CEP.TRC(SCCGWA, WS_CAL_AREA.WS_CAL_END_DT);
        CEP.TRC(SCCGWA, WS_PROC_DT);
        CEP.TRC(SCCGWA, "CAL INT. BASE DAYS");
        CEP.TRC(SCCGWA, BPRFCPHO.INT_BAS);
        IBS.init(SCCGWA, BPCIDAY);
        BPCIDAY.I_CALR_STD = BPRFCPHO.INT_BAS;
        BPCIDAY.I_BEGIN_DATE = WS_PROC_DT;
        BPCIDAY.I_END_DATE = WS_CAL_AREA.WS_CAL_END_DT;
        S000_CALL_BPZCIDAY();
        if (pgmRtn) return;
        WS_CAL_AREA.WS_CAL_BASE_DAYS = BPCIDAY.OUTPUT.O_STD_DAYS;
        WS_CAL_AREA.WS_CAL_DAYS = (short) BPCIDAY.OUTPUT.O_ORD_DAYS;
        WS_CAL_AREA.WS_CAL_LEAP_DAYS = (short) BPCIDAY.OUTPUT.O_LEAP_DAYS;
        if (BPRFCPHO.INT_BAS.equalsIgnoreCase("03")) {
            WS_DATE = WS_CAL_AREA.WS_CAL_END_DT;
            R000_CHECK_DATE();
            if (pgmRtn) return;
            if (SCCCKDT.LEAP_YEAR == 1) {
                WS_CAL_AREA.WS_CAL_LEAP_DAYS += 1;
            } else {
                WS_CAL_AREA.WS_CAL_DAYS += 1;
            }
        } else {
            WS_CAL_AREA.WS_CAL_DAYS += 1;
        }
        CEP.TRC(SCCGWA, WS_CAL_AREA.WS_CAL_BASE_DAYS);
        CEP.TRC(SCCGWA, WS_CAL_AREA.WS_CAL_DAYS);
        CEP.TRC(SCCGWA, WS_CAL_AREA.WS_CAL_LEAP_DAYS);
    }
    public void R000_CAL_FEE_CN() throws IOException,SQLException,Exception {
        WS_CAL_AREA.WS_CAL_AMT = 0;
        CEP.TRC(SCCGWA, WS_REF_AMT_AREA.WS_REF_AMT_LOC);
        CEP.TRC(SCCGWA, BPRFCPHO.MULTI);
        WS_REF_AMT_AREA.WS_REF_AMT_LOC = WS_REF_AMT_AREA.WS_REF_AMT_LOC * BPRFCPHO.MULTI / 100;
        CEP.TRC(SCCGWA, WS_REF_AMT_AREA.WS_REF_AMT_LOC);
        R000_MOVE_DATA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRFCPHO.REF_METHOD);
        CEP.TRC(SCCGWA, BPRFCPHO.AGGR_TYPE);
        if (BPRFCPHO.REF_METHOD == '0'
            && BPRFCPHO.AGGR_TYPE == '0') {
            R000_00_CAL_FEE_CN();
            if (pgmRtn) return;
        } else if (BPRFCPHO.REF_METHOD == '0'
            && BPRFCPHO.AGGR_TYPE == '1') {
            R000_01_CAL_FEE_CN();
            if (pgmRtn) return;
        } else if (BPRFCPHO.REF_METHOD == '1'
            && BPRFCPHO.AGGR_TYPE == '0') {
            R000_10_CAL_FEE_CN();
            if (pgmRtn) return;
        } else if (BPRFCPHO.REF_METHOD == '1'
            && BPRFCPHO.AGGR_TYPE == '1') {
            R000_11_CAL_FEE_CN();
            if (pgmRtn) return;
        } else {
        }
        CEP.TRC(SCCGWA, WS_CAL_AREA.WS_CAL_AMT);
        CEP.TRC(SCCGWA, WS_CAL_AREA.WS_CAL_DAYS);
        CEP.TRC(SCCGWA, WS_CAL_AREA.WS_CAL_BASE_DAYS);
        WS_CAL_AREA.WS_CAL_AMT_FII = WS_CAL_AREA.WS_CAL_AMT;
        if (WS_AGG_MTH_FLG == 'A') {
            CEP.TRC(SCCGWA, "11111111111");
            if (BPRFCPHO.INT_BAS.equalsIgnoreCase("03")) {
                CEP.TRC(SCCGWA, "22222111111");
                WS_CAL_AREA.WS_CAL_AMT = WS_CAL_AREA.WS_CAL_AMT * ( WS_CAL_AREA.WS_CAL_DAYS + WS_CAL_AREA.WS_CAL_LEAP_DAYS );
            } else {
                CEP.TRC(SCCGWA, "33333111111");
                WS_CAL_AREA.WS_CAL_AMT = WS_CAL_AREA.WS_CAL_AMT * WS_CAL_AREA.WS_CAL_DAYS;
            }
        } else {
            CEP.TRC(SCCGWA, "44444111111");
            if (BPRFCPHO.INT_BAS.equalsIgnoreCase("03")) {
                WS_CAL_AREA.WS_CAL_AMT = WS_CAL_AREA.WS_CAL_AMT * WS_CAL_AREA.WS_CAL_LEAP_DAYS / 366 + WS_CAL_AREA.WS_CAL_AMT * WS_CAL_AREA.WS_CAL_DAYS / 365;
                bigD = new BigDecimal(WS_CAL_AREA.WS_CAL_AMT);
                WS_CAL_AREA.WS_CAL_AMT = bigD.setScale(0, RoundingMode.HALF_UP).doubleValue();
            } else {
                if (WS_CAL_AREA.WS_CAL_BASE_DAYS == 0) {
                    WS_CAL_AREA.WS_CAL_AMT = 0;
                } else {
                    WS_CAL_AREA.WS_CAL_AMT = WS_CAL_AREA.WS_CAL_AMT * WS_CAL_AREA.WS_CAL_DAYS / WS_CAL_AREA.WS_CAL_BASE_DAYS;
                    bigD = new BigDecimal(WS_CAL_AREA.WS_CAL_AMT);
                    WS_CAL_AREA.WS_CAL_AMT = bigD.setScale(0, RoundingMode.HALF_UP).doubleValue();
                }
            }
        }
        CEP.TRC(SCCGWA, BPRFBAS.KEY.FEE_CODE);
        CEP.TRC(SCCGWA, WS_CAL_AREA.WS_CAL_AMT_FII);
        if (BPRFBAS.KEY.FEE_CODE.equalsIgnoreCase("FII01")) {
            WS_CAL_AREA.WS_CAL_AMT = WS_CAL_AREA.WS_CAL_AMT_FII;
        }
        CEP.TRC(SCCGWA, WS_CAL_AREA.WS_CAL_AMT);
        CEP.TRC(SCCGWA, BPCUFEEC.FEE_CCY);
        CEP.TRC(SCCGWA, WS_REF_AMT_AREA.WS_REF_CCY);
        CEP.TRC(SCCGWA, BPRFCPHO.REF_CCY);
        CEP.TRC(SCCGWA, "EXCHANGE FEE AMT");
        if (!BPCUFEEC.FEE_CCY.equalsIgnoreCase(BPRFCPHO.REF_CCY) 
            && WS_CAL_AREA.WS_CAL_AMT != 0) {
            IBS.init(SCCGWA, BPCFX);
            BPCFX.FUNC = '3';
            BPCFX.EXR_TYPE = BPRFBAS.RATE_GROUP;
            BPCFX.BUY_CCY = BPRFCPHO.REF_CCY;
            BPCFX.BUY_AMT = WS_CAL_AREA.WS_CAL_AMT;
            BPCFX.SELL_CCY = BPCUFEEC.FEE_CCY;
            S000_CALL_BPZSFX();
            if (pgmRtn) return;
            WS_ACCR_AMT = BPCFX.SELL_AMT;
        } else {
            WS_ACCR_AMT = WS_CAL_AREA.WS_CAL_AMT;
        }
        CEP.TRC(SCCGWA, WS_ACCR_AMT);
        CEP.TRC(SCCGWA, BPCUFEEC.FEE_AMT);
        if (!BPRFBAS.KEY.FEE_CODE.equalsIgnoreCase("FII01")) {
            BPCUFEEC.FEE_AMT = BPCUFEEC.FEE_AMT + WS_ACCR_AMT;
        } else {
            BPCUFEEC.FEE_AMT = WS_ACCR_AMT;
        }
        CEP.TRC(SCCGWA, BPCUFEEC.FEE_AMT);
        if (BPCUFEEC.FEE_AMT != 0) {
            IBS.init(SCCGWA, BPCRDAMT);
            BPCRDAMT.CCY = BPCUFEEC.FEE_CCY;
            BPCRDAMT.AMT = BPCUFEEC.FEE_AMT;
            S000_CALL_BPZRDAMT();
            if (pgmRtn) return;
            BPCUFEEC.FEE_AMT = BPCRDAMT.RESULT_AMT;
            CEP.TRC(SCCGWA, BPCUFEEC.FEE_AMT);
        }
    }
    public void R000_00_CAL_FEE_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_REF_AMT_AREA.WS_REF_AMT_LOC);
        WS_CAL_AREA.WS_CAL_LVL = 0;
        for (WS_CNT1 = 1; WS_CNT1 <= 5 
            && WS_CAL_AREA.WS_CAL_LVL <= 0; WS_CNT1 += 1) {
            CEP.TRC(SCCGWA, WS_TEMP_ARRAY[WS_CNT1-1].WS_UP_AMT);
            if (WS_REF_AMT_AREA.WS_REF_AMT_LOC <= WS_TEMP_ARRAY[WS_CNT1-1].WS_UP_AMT 
                && WS_TEMP_ARRAY[WS_CNT1-1].WS_UP_AMT > 0 
                && WS_REF_AMT_AREA.WS_REF_AMT_LOC > 0) {
                WS_CAL_AREA.WS_CAL_LVL = (short) WS_CNT1;
                CEP.TRC(SCCGWA, "TRUE ANSWER");
            }
        }
        CEP.TRC(SCCGWA, WS_CAL_AREA.WS_CAL_LVL);
        if (WS_CAL_AREA.WS_CAL_LVL > 0) {
            WS_CNT1 = WS_CAL_AREA.WS_CAL_LVL;
            R000_GET_AGG_METHOD_CN();
            if (pgmRtn) return;
            if (WS_AGG_MTH_FLG == 'P') {
                WS_TEMP_ARRAY[WS_CAL_AREA.WS_CAL_LVL-1].WS_FEE_AMT = WS_REF_AMT_AREA.WS_REF_AMT_LOC * WS_TEMP_ARRAY[WS_CAL_AREA.WS_CAL_LVL-1].WS_FEE_RATE;
            }
            WS_CAL_AREA.WS_CAL_AMT = WS_TEMP_ARRAY[WS_CAL_AREA.WS_CAL_LVL-1].WS_FEE_AMT;
        } else {
            WS_CAL_AREA.WS_CAL_AMT = 0;
        }
    }
    public void R000_01_CAL_FEE_CN() throws IOException,SQLException,Exception {
        WS_CAL_AREA.WS_CAL_LVL = 0;
        for (WS_CNT1 = 1; WS_CNT1 <= 5 
            && WS_CAL_AREA.WS_CAL_LVL <= 0; WS_CNT1 += 1) {
            if (WS_REF_AMT_AREA.WS_REF_AMT_LOC <= WS_TEMP_ARRAY[WS_CNT1-1].WS_UP_AMT 
                && WS_TEMP_ARRAY[WS_CNT1-1].WS_UP_AMT > 0 
                && WS_REF_AMT_AREA.WS_REF_AMT_LOC > 0) {
                WS_CAL_AREA.WS_CAL_LVL = (short) WS_CNT1;
            }
        }
        if (WS_CAL_AREA.WS_CAL_LVL > 0) {
            for (WS_CNT1 = 1; WS_CNT1 <= WS_CAL_AREA.WS_CAL_LVL; WS_CNT1 += 1) {
                R000_GET_AGG_METHOD_CN();
                if (pgmRtn) return;
                if (WS_CAL_AREA.WS_CAL_LVL == 1) {
                    if (WS_AGG_MTH_FLG == 'A') {
                        WS_CAL_AREA.WS_CAL_AMT = WS_CAL_AREA.WS_CAL_AMT + WS_TEMP_ARRAY[WS_CNT1-1].WS_FEE_AMT;
                    } else {
                        WS_CAL_AREA.WS_CAL_AMT = WS_CAL_AREA.WS_CAL_AMT + WS_REF_AMT_AREA.WS_REF_AMT_LOC * WS_TEMP_ARRAY[1-1].WS_FEE_RATE;
                    }
                } else {
                    if (WS_AGG_MTH_FLG == 'A') {
                        WS_CAL_AREA.WS_CAL_AMT = WS_CAL_AREA.WS_CAL_AMT + WS_TEMP_ARRAY[WS_CNT1-1].WS_FEE_AMT;
                    } else {
                        if (WS_CNT1 == 1) {
                            WS_CAL_AREA.WS_CAL_AMT = WS_CAL_AREA.WS_CAL_AMT + WS_TEMP_ARRAY[1-1].WS_UP_AMT * WS_TEMP_ARRAY[1-1].WS_FEE_RATE;
                        }
                        if (WS_CNT1 >= 2 
                            && WS_CNT1 < WS_CAL_AREA.WS_CAL_LVL) {
                            WS_CAL_AREA.WS_CAL_AMT = WS_CAL_AREA.WS_CAL_AMT + ( WS_TEMP_ARRAY[WS_CNT1-1].WS_UP_AMT - WS_TEMP_ARRAY[WS_CNT1 - 1-1].WS_UP_AMT ) * WS_TEMP_ARRAY[WS_CNT1-1].WS_FEE_RATE;
                        }
                        if (WS_CNT1 == WS_CAL_AREA.WS_CAL_LVL) {
                            WS_CAL_AREA.WS_CAL_AMT = WS_CAL_AREA.WS_CAL_AMT + ( WS_REF_AMT_AREA.WS_REF_AMT_LOC - WS_TEMP_ARRAY[WS_CAL_AREA.WS_CAL_LVL - 1-1].WS_UP_AMT ) * WS_TEMP_ARRAY[WS_CAL_AREA.WS_CAL_LVL-1].WS_FEE_RATE;
                        }
                    }
                }
            }
        } else {
            WS_CAL_AREA.WS_CAL_AMT = 0;
        }
    }
    public void R000_10_CAL_FEE_CN() throws IOException,SQLException,Exception {
        WS_CAL_AREA.WS_CAL_LVL = 0;
        for (WS_CNT1 = 1; WS_CNT1 <= 5 
            && WS_CAL_AREA.WS_CAL_LVL <= 0; WS_CNT1 += 1) {
            if (WS_REF_AMT_AREA.WS_REF_PCT <= WS_TEMP_ARRAY[WS_CNT1-1].WS_UP_PCT 
                && WS_TEMP_ARRAY[WS_CNT1-1].WS_UP_PCT > 0 
                && WS_REF_AMT_AREA.WS_REF_PCT > 0) {
                WS_CAL_AREA.WS_CAL_LVL = (short) WS_CNT1;
            }
        }
        if (WS_CAL_AREA.WS_CAL_LVL > 0) {
            WS_CNT1 = WS_CAL_AREA.WS_CAL_LVL;
            R000_GET_AGG_METHOD_CN();
            if (pgmRtn) return;
            if (WS_AGG_MTH_FLG == 'P') {
                WS_TEMP_ARRAY[WS_CAL_AREA.WS_CAL_LVL-1].WS_FEE_AMT = WS_REF_AMT_AREA.WS_REF_AMT_LOC * WS_TEMP_ARRAY[WS_CAL_AREA.WS_CAL_LVL-1].WS_FEE_RATE;
            }
            WS_CAL_AREA.WS_CAL_AMT = WS_TEMP_ARRAY[WS_CAL_AREA.WS_CAL_LVL-1].WS_FEE_AMT;
        } else {
            WS_CAL_AREA.WS_CAL_AMT = 0;
        }
    }
    public void R000_11_CAL_FEE_CN() throws IOException,SQLException,Exception {
        WS_CAL_AREA.WS_CAL_LVL = 0;
        for (WS_CNT1 = 1; WS_CNT1 <= 5 
            && WS_CAL_AREA.WS_CAL_LVL <= 0; WS_CNT1 += 1) {
            if (WS_REF_AMT_AREA.WS_REF_PCT <= WS_TEMP_ARRAY[WS_CNT1-1].WS_UP_PCT 
                && WS_TEMP_ARRAY[WS_CNT1-1].WS_UP_PCT > 0 
                && WS_REF_AMT_AREA.WS_REF_PCT > 0) {
                WS_CAL_AREA.WS_CAL_LVL = (short) WS_CNT1;
            }
        }
        CEP.TRC(SCCGWA, WS_CAL_AREA.WS_CAL_LVL);
        if (WS_CAL_AREA.WS_CAL_LVL > 0) {
            for (WS_CNT1 = 1; WS_CNT1 <= WS_CAL_AREA.WS_CAL_LVL; WS_CNT1 += 1) {
                R000_GET_AGG_METHOD_CN();
                if (pgmRtn) return;
                if (WS_CAL_AREA.WS_CAL_LVL == 1) {
                    if (WS_AGG_MTH_FLG == 'A') {
                        WS_CAL_AREA.WS_CAL_AMT = WS_CAL_AREA.WS_CAL_AMT + WS_TEMP_ARRAY[WS_CNT1-1].WS_FEE_AMT;
                    } else {
                        WS_CAL_AREA.WS_CAL_AMT = WS_CAL_AREA.WS_CAL_AMT + WS_REF_AMT_AREA.WS_REF_AMT_LOC * WS_TEMP_ARRAY[1-1].WS_FEE_RATE;
                    }
                } else {
                    if (WS_AGG_MTH_FLG == 'A') {
                        WS_CAL_AREA.WS_CAL_AMT = WS_CAL_AREA.WS_CAL_AMT + WS_TEMP_ARRAY[WS_CNT1-1].WS_FEE_AMT;
                    } else {
                        if (WS_CNT1 == 1) {
                            WS_CAL_AREA.WS_CAL_AMT = WS_CAL_AREA.WS_CAL_AMT + WS_REF_AMT_AREA.WS_REF_AMT_LOC * WS_TEMP_ARRAY[1-1].WS_UP_PCT * WS_TEMP_ARRAY[1-1].WS_FEE_RATE;
                        }
                        if (WS_CNT1 >= 2 
                            && WS_CNT1 < WS_CAL_AREA.WS_CAL_LVL) {
                            WS_CAL_AREA.WS_CAL_AMT = WS_CAL_AREA.WS_CAL_AMT + WS_REF_AMT_AREA.WS_REF_AMT_LOC * ( WS_TEMP_ARRAY[WS_CNT1-1].WS_UP_PCT - WS_TEMP_ARRAY[WS_CNT1 - 1-1].WS_UP_PCT ) * WS_TEMP_ARRAY[WS_CNT1-1].WS_FEE_RATE;
                        }
                        if (WS_CNT1 == WS_CAL_AREA.WS_CAL_LVL) {
                            WS_CAL_AREA.WS_CAL_AMT = WS_CAL_AREA.WS_CAL_AMT + WS_REF_AMT_AREA.WS_REF_AMT_LOC * ( WS_REF_AMT_AREA.WS_REF_PCT - WS_TEMP_ARRAY[WS_CNT1 - 1-1].WS_UP_PCT ) * WS_TEMP_ARRAY[WS_CAL_AREA.WS_CAL_LVL-1].WS_FEE_RATE;
                        }
                    }
                }
            }
        } else {
            WS_CAL_AREA.WS_CAL_AMT = 0;
        }
    }
    public void R000_GET_AGG_METHOD_CN() throws IOException,SQLException,Exception {
        WS_AGG_MTH_FLG = ' ';
        if (WS_CNT1 == 1) {
            if (BPRFCPH.AGG_MTH_1 == '0') {
                WS_AGG_MTH_FLG = 'A';
            } else {
                WS_AGG_MTH_FLG = 'P';
            }
            CEP.TRC(SCCGWA, BPRFCPH.AGG_MTH_1);
        } else if (WS_CNT1 == 2) {
            if (BPRFCPH.AGG_MTH_2 == '0') {
                WS_AGG_MTH_FLG = 'A';
            } else {
                WS_AGG_MTH_FLG = 'P';
            }
            CEP.TRC(SCCGWA, BPRFCPH.AGG_MTH_2);
        } else if (WS_CNT1 == 3) {
            if (BPRFCPH.AGG_MTH_3 == '0') {
                WS_AGG_MTH_FLG = 'A';
            } else {
                WS_AGG_MTH_FLG = 'P';
            }
        } else if (WS_CNT1 == 4) {
            if (BPRFCPH.AGG_MTH_4 == '0') {
                WS_AGG_MTH_FLG = 'A';
            } else {
                WS_AGG_MTH_FLG = 'P';
            }
        } else if (WS_CNT1 == 5) {
            if (BPRFCPH.AGG_MTH_5 == '0') {
                WS_AGG_MTH_FLG = 'A';
            } else {
                WS_AGG_MTH_FLG = 'P';
            }
        } else {
        }
    }
    public void R000_MOVE_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRFCPHO.UP_AMT_1);
        CEP.TRC(SCCGWA, WS_TEMP_ARRAY[1-1].WS_UP_AMT);
        WS_TEMP_ARRAY[1-1].WS_UP_AMT = BPRFCPHO.UP_AMT_1;
        WS_TEMP_ARRAY[1-1].WS_UP_PCT = BPRFCPHO.UP_PCT_1;
        WS_TEMP_ARRAY[1-1].WS_FEE_AMT = BPRFCPHO.FEE_AMT_1;
        WS_TEMP_ARRAY[1-1].WS_FEE_RATE = BPRFCPHO.FEE_RATE_1;
        WS_TEMP_ARRAY[2-1].WS_UP_AMT = BPRFCPHO.UP_AMT_2;
        WS_TEMP_ARRAY[2-1].WS_UP_PCT = BPRFCPHO.UP_PCT_2;
        WS_TEMP_ARRAY[2-1].WS_FEE_AMT = BPRFCPHO.FEE_AMT_2;
        WS_TEMP_ARRAY[2-1].WS_FEE_RATE = BPRFCPHO.FEE_RATE_2;
        WS_TEMP_ARRAY[3-1].WS_UP_AMT = BPRFCPHO.UP_AMT_3;
        WS_TEMP_ARRAY[3-1].WS_UP_PCT = BPRFCPHO.UP_PCT_3;
        WS_TEMP_ARRAY[3-1].WS_FEE_AMT = BPRFCPHO.FEE_AMT_3;
        WS_TEMP_ARRAY[3-1].WS_FEE_RATE = BPRFCPHO.FEE_RATE_3;
        WS_TEMP_ARRAY[4-1].WS_UP_AMT = BPRFCPHO.UP_AMT_4;
        WS_TEMP_ARRAY[4-1].WS_UP_PCT = BPRFCPHO.UP_PCT_4;
        WS_TEMP_ARRAY[4-1].WS_FEE_AMT = BPRFCPHO.FEE_AMT_4;
        WS_TEMP_ARRAY[4-1].WS_FEE_RATE = BPRFCPHO.FEE_RATE_4;
        WS_TEMP_ARRAY[5-1].WS_UP_AMT = BPRFCPHO.UP_AMT_5;
        WS_TEMP_ARRAY[5-1].WS_UP_PCT = BPRFCPHO.UP_PCT_5;
        WS_TEMP_ARRAY[5-1].WS_FEE_AMT = BPRFCPHO.FEE_AMT_5;
        WS_TEMP_ARRAY[5-1].WS_FEE_RATE = BPRFCPHO.FEE_RATE_5;
    }
    public void R000_000_CAL_FEE() throws IOException,SQLException,Exception {
        WS_CAL_AREA.WS_CAL_LVL = 0;
        for (WS_CNT1 = 1; WS_CNT1 <= 5 
            && WS_CAL_AREA.WS_CAL_LVL <= 0; WS_CNT1 += 1) {
            if (WS_REF_AMT_AREA.WS_REF_AMT_LOC <= WS_TEMP_ARRAY[WS_CNT1-1].WS_UP_AMT 
                && WS_TEMP_ARRAY[WS_CNT1-1].WS_UP_AMT > 0 
                && WS_REF_AMT_AREA.WS_REF_AMT_LOC >= 0) {
                WS_CAL_AREA.WS_CAL_LVL = (short) WS_CNT1;
            }
        }
        CEP.TRC(SCCGWA, WS_CAL_AREA.WS_CAL_LVL);
        CEP.TRC(SCCGWA, WS_TEMP_ARRAY[WS_CAL_AREA.WS_CAL_LVL-1].WS_FEE_AMT);
        if (WS_CAL_AREA.WS_CAL_LVL > 0 
            && WS_REF_AMT_AREA.WS_REF_AMT_LOC > 0) {
            WS_CAL_AREA.WS_CAL_AMT = WS_TEMP_ARRAY[WS_CAL_AREA.WS_CAL_LVL-1].WS_FEE_AMT;
        } else {
            WS_CAL_AREA.WS_CAL_AMT = 0;
        }
        CEP.TRC(SCCGWA, WS_CAL_AREA.WS_CAL_AMT);
    }
    public void R000_001_CAL_FEE() throws IOException,SQLException,Exception {
        WS_CAL_AREA.WS_CAL_LVL = 0;
        for (WS_CNT1 = 1; WS_CNT1 <= 5 
            && WS_CAL_AREA.WS_CAL_LVL <= 0; WS_CNT1 += 1) {
            if (WS_REF_AMT_AREA.WS_REF_AMT_LOC <= WS_TEMP_ARRAY[WS_CNT1-1].WS_UP_AMT 
                && WS_TEMP_ARRAY[WS_CNT1-1].WS_UP_AMT > 0 
                && WS_REF_AMT_AREA.WS_REF_AMT_LOC >= 0) {
                WS_CAL_AREA.WS_CAL_LVL = (short) WS_CNT1;
            }
        }
        if (WS_CAL_AREA.WS_CAL_LVL > 0 
            && WS_REF_AMT_AREA.WS_REF_AMT_LOC > 0) {
            for (WS_CNT1 = 1; WS_CNT1 <= WS_CAL_AREA.WS_CAL_LVL; WS_CNT1 += 1) {
                WS_CAL_AREA.WS_CAL_AMT = WS_CAL_AREA.WS_CAL_AMT + WS_TEMP_ARRAY[WS_CNT1-1].WS_FEE_AMT;
            }
        } else {
            WS_CAL_AREA.WS_CAL_AMT = 0;
        }
        CEP.TRC(SCCGWA, WS_CAL_AREA.WS_CAL_AMT);
    }
    public void R000_010_CAL_FEE() throws IOException,SQLException,Exception {
        WS_CAL_AREA.WS_CAL_LVL = 0;
        for (WS_CNT1 = 1; WS_CNT1 <= 5 
            && WS_CAL_AREA.WS_CAL_LVL <= 0; WS_CNT1 += 1) {
            if (WS_REF_AMT_AREA.WS_REF_AMT_LOC <= WS_TEMP_ARRAY[WS_CNT1-1].WS_UP_AMT 
                && WS_TEMP_ARRAY[WS_CNT1-1].WS_UP_AMT > 0 
                && WS_REF_AMT_AREA.WS_REF_AMT_LOC >= 0) {
                WS_CAL_AREA.WS_CAL_LVL = (short) WS_CNT1;
            }
        }
        CEP.TRC(SCCGWA, WS_CAL_AREA.WS_CAL_LVL);
        CEP.TRC(SCCGWA, WS_TEMP_ARRAY[WS_CAL_AREA.WS_CAL_LVL-1].WS_FEE_RATE);
        CEP.TRC(SCCGWA, WS_REF_AMT_AREA.WS_REF_AMT_LOC);
        if (WS_CAL_AREA.WS_CAL_LVL > 0) {
            WS_CAL_AREA.WS_CAL_AMT = WS_REF_AMT_AREA.WS_REF_AMT_LOC * WS_TEMP_ARRAY[WS_CAL_AREA.WS_CAL_LVL-1].WS_FEE_RATE;
        } else {
            WS_CAL_AREA.WS_CAL_AMT = 0;
        }
        CEP.TRC(SCCGWA, WS_CAL_AREA.WS_CAL_AMT);
    }
    public void R000_011_CAL_FEE() throws IOException,SQLException,Exception {
        WS_CAL_AREA.WS_CAL_LVL = 0;
        for (WS_CNT1 = 1; WS_CNT1 <= 5 
            && WS_CAL_AREA.WS_CAL_LVL <= 0; WS_CNT1 += 1) {
            if (WS_REF_AMT_AREA.WS_REF_AMT_LOC <= WS_TEMP_ARRAY[WS_CNT1-1].WS_UP_AMT 
                && WS_TEMP_ARRAY[WS_CNT1-1].WS_UP_AMT > 0 
                && WS_REF_AMT_AREA.WS_REF_AMT_LOC >= 0) {
                WS_CAL_AREA.WS_CAL_LVL = (short) WS_CNT1;
            }
        }
        CEP.TRC(SCCGWA, WS_CAL_AREA.WS_CAL_LVL);
        CEP.TRC(SCCGWA, WS_CAL_AREA.WS_CAL_DAYS);
        CEP.TRC(SCCGWA, WS_CAL_AREA.WS_CAL_BASE_DAYS);
        if (WS_CAL_AREA.WS_CAL_LVL >= 1) {
            if (WS_CAL_AREA.WS_CAL_LVL == 1) {
                WS_CAL_AREA.WS_CAL_AMT = WS_CAL_AREA.WS_CAL_AMT + WS_REF_AMT_AREA.WS_REF_AMT_LOC * WS_TEMP_ARRAY[1-1].WS_FEE_RATE;
            } else {
                WS_CAL_AREA.WS_CAL_AMT = WS_CAL_AREA.WS_CAL_AMT + WS_TEMP_ARRAY[1-1].WS_UP_AMT * WS_TEMP_ARRAY[1-1].WS_FEE_RATE;
                for (WS_CNT1 = 2; WS_CNT1 <= WS_CAL_AREA.WS_CAL_LVL - 1; WS_CNT1 += 1) {
                    WS_CAL_AREA.WS_CAL_AMT = WS_CAL_AREA.WS_CAL_AMT + ( WS_TEMP_ARRAY[WS_CNT1-1].WS_UP_AMT - WS_TEMP_ARRAY[WS_CNT1 - 1-1].WS_UP_AMT ) * WS_TEMP_ARRAY[WS_CNT1-1].WS_FEE_RATE;
                }
                WS_CAL_AREA.WS_CAL_AMT = WS_CAL_AREA.WS_CAL_AMT + ( WS_REF_AMT_AREA.WS_REF_AMT_LOC - WS_TEMP_ARRAY[WS_CAL_AREA.WS_CAL_LVL - 1-1].WS_UP_AMT ) * WS_TEMP_ARRAY[WS_CAL_AREA.WS_CAL_LVL-1].WS_FEE_RATE;
            }
        } else {
            WS_CAL_AREA.WS_CAL_AMT = 0;
        }
        CEP.TRC(SCCGWA, WS_CAL_AREA.WS_CAL_AMT);
    }
    public void R000_100_CAL_FEE() throws IOException,SQLException,Exception {
        WS_CAL_AREA.WS_CAL_LVL = 0;
        for (WS_CNT1 = 1; WS_CNT1 <= 5 
            && WS_CAL_AREA.WS_CAL_LVL <= 0; WS_CNT1 += 1) {
            if (WS_REF_AMT_AREA.WS_REF_PCT <= WS_TEMP_ARRAY[WS_CNT1-1].WS_UP_PCT 
                && WS_TEMP_ARRAY[WS_CNT1-1].WS_UP_PCT > 0 
                && WS_REF_AMT_AREA.WS_REF_PCT >= 0) {
                WS_CAL_AREA.WS_CAL_LVL = (short) WS_CNT1;
            }
        }
        if (WS_CAL_AREA.WS_CAL_LVL > 0 
            && WS_REF_AMT_AREA.WS_REF_PCT > 0) {
            WS_CAL_AREA.WS_CAL_AMT = WS_TEMP_ARRAY[WS_CAL_AREA.WS_CAL_LVL-1].WS_FEE_AMT;
        } else {
            WS_CAL_AREA.WS_CAL_AMT = 0;
        }
        CEP.TRC(SCCGWA, WS_CAL_AREA.WS_CAL_AMT);
    }
    public void R000_101_CAL_FEE() throws IOException,SQLException,Exception {
        WS_CAL_AREA.WS_CAL_LVL = 0;
        for (WS_CNT1 = 1; WS_CNT1 <= 5 
            && WS_CAL_AREA.WS_CAL_LVL <= 0; WS_CNT1 += 1) {
            if (WS_REF_AMT_AREA.WS_REF_PCT <= WS_TEMP_ARRAY[WS_CNT1-1].WS_UP_PCT 
                && WS_TEMP_ARRAY[WS_CNT1-1].WS_UP_PCT > 0 
                && WS_REF_AMT_AREA.WS_REF_PCT >= 0) {
                WS_CAL_AREA.WS_CAL_LVL = (short) WS_CNT1;
            }
        }
        CEP.TRC(SCCGWA, WS_CAL_AREA.WS_CAL_LVL);
        if (WS_CAL_AREA.WS_CAL_LVL > 0 
            && WS_REF_AMT_AREA.WS_REF_PCT > 0) {
            for (WS_CNT1 = 1; WS_CNT1 <= WS_CAL_AREA.WS_CAL_LVL; WS_CNT1 += 1) {
                WS_CAL_AREA.WS_CAL_AMT = WS_CAL_AREA.WS_CAL_AMT + WS_TEMP_ARRAY[WS_CNT1-1].WS_FEE_AMT;
            }
        } else {
            WS_CAL_AREA.WS_CAL_AMT = 0;
        }
        CEP.TRC(SCCGWA, WS_CAL_AREA.WS_CAL_AMT);
    }
    public void R000_110_CAL_FEE() throws IOException,SQLException,Exception {
        WS_CAL_AREA.WS_CAL_LVL = 0;
        for (WS_CNT1 = 1; WS_CNT1 <= 5 
            && WS_CAL_AREA.WS_CAL_LVL <= 0; WS_CNT1 += 1) {
            if (WS_REF_AMT_AREA.WS_REF_PCT <= WS_TEMP_ARRAY[WS_CNT1-1].WS_UP_PCT 
                && WS_TEMP_ARRAY[WS_CNT1-1].WS_UP_PCT > 0 
                && WS_REF_AMT_AREA.WS_REF_PCT >= 0) {
                WS_CAL_AREA.WS_CAL_LVL = (short) WS_CNT1;
            }
        }
        CEP.TRC(SCCGWA, WS_CAL_AREA.WS_CAL_LVL);
        if (WS_CAL_AREA.WS_CAL_LVL > 0 
            && WS_REF_AMT_AREA.WS_REF_PCT > 0) {
            WS_CAL_AREA.WS_CAL_AMT = WS_REF_AMT_AREA.WS_REF_AMT_LOC * WS_TEMP_ARRAY[WS_CAL_AREA.WS_CAL_LVL-1].WS_FEE_RATE;
        } else {
            WS_CAL_AREA.WS_CAL_AMT = 0;
        }
        CEP.TRC(SCCGWA, WS_CAL_AREA.WS_CAL_AMT);
    }
    public void R000_111_CAL_FEE() throws IOException,SQLException,Exception {
        WS_CAL_AREA.WS_CAL_LVL = 0;
        for (WS_CNT1 = 1; WS_CNT1 <= 5 
            && WS_CAL_AREA.WS_CAL_LVL <= 0; WS_CNT1 += 1) {
            if (WS_REF_AMT_AREA.WS_REF_PCT <= WS_TEMP_ARRAY[WS_CNT1-1].WS_UP_PCT 
                && WS_TEMP_ARRAY[WS_CNT1-1].WS_UP_PCT > 0 
                && WS_REF_AMT_AREA.WS_REF_PCT >= 0) {
                WS_CAL_AREA.WS_CAL_LVL = (short) WS_CNT1;
            }
        }
        CEP.TRC(SCCGWA, WS_CAL_AREA.WS_CAL_LVL);
        CEP.TRC(SCCGWA, WS_CAL_AREA.WS_CAL_DAYS);
        CEP.TRC(SCCGWA, WS_CAL_AREA.WS_CAL_BASE_DAYS);
        if (WS_CAL_AREA.WS_CAL_LVL >= 1) {
            if (WS_CAL_AREA.WS_CAL_LVL == 1) {
                WS_CAL_AREA.WS_CAL_AMT = WS_CAL_AREA.WS_CAL_AMT + WS_REF_AMT_AREA.WS_REF_AMT_LOC * WS_TEMP_ARRAY[1-1].WS_FEE_RATE;
            } else {
                WS_CAL_AREA.WS_CAL_AMT = WS_CAL_AREA.WS_CAL_AMT + WS_REF_AMT_AREA.WS_REF_AMT_LOC * WS_TEMP_ARRAY[1-1].WS_UP_PCT * WS_TEMP_ARRAY[1-1].WS_FEE_RATE;
                for (WS_CNT1 = 2; WS_CNT1 <= WS_CAL_AREA.WS_CAL_LVL - 1; WS_CNT1 += 1) {
                    WS_CAL_AREA.WS_CAL_AMT = WS_CAL_AREA.WS_CAL_AMT + WS_REF_AMT_AREA.WS_REF_AMT_LOC * ( WS_TEMP_ARRAY[WS_CNT1-1].WS_UP_PCT - WS_TEMP_ARRAY[WS_CNT1 - 1-1].WS_UP_PCT ) * WS_TEMP_ARRAY[WS_CNT1-1].WS_FEE_RATE;
                }
                WS_CAL_AREA.WS_CAL_AMT = WS_CAL_AREA.WS_CAL_AMT + WS_REF_AMT_AREA.WS_REF_AMT_LOC * ( WS_REF_AMT_AREA.WS_REF_PCT - WS_TEMP_ARRAY[WS_CAL_AREA.WS_CAL_LVL - 1-1].WS_UP_PCT ) * WS_TEMP_ARRAY[WS_CAL_AREA.WS_CAL_LVL-1].WS_FEE_RATE;
            }
        } else {
            WS_CAL_AREA.WS_CAL_AMT = 0;
        }
        CEP.TRC(SCCGWA, WS_CAL_AREA.WS_CAL_AMT);
    }
    public void R000_GET_NEXT_AMT() throws IOException,SQLException,Exception {
        if (WS_REF_AMT_AREA.WS_REF_END_DT < WS_PROC_DT) {
            if (WS_AMT_TYPE == 0) {
                CEP.TRC(SCCGWA, "USING TXN AMT");
                WS_REF_AMT_AREA.WS_REF_AMT = WS_TXN_AMT;
                WS_REF_AMT_AREA.WS_REF_CCY = WS_TXN_CCY;
                WS_REF_AMT_AREA.WS_REF_PCT = 100;
                WS_REF_AMT_AREA.WS_REF_STA_DT = BPCUFEEC.STA_DT;
                WS_REF_AMT_AREA.WS_REF_END_DT = BPCUFEEC.END_DT;
                WS_REF_AMT_AREA.WS_REF_N_STA_DT = 99991231;
                WS_EX_FROM_CCY = WS_REF_AMT_AREA.WS_REF_CCY;
            } else {
                if (BPCUFEEC.RUN_MODE == 'O') {
                    CEP.TRC(SCCGWA, "ONLINE MOD TO CALL LN CPN");
                    R000_GET_NEXT_AMT_ONL();
                    if (pgmRtn) return;
                } else {
                    CEP.TRC(SCCGWA, "BATCH MOD TO READ PBTFRAH");
                    R000_GET_NEXT_AMT_BAT();
                    if (pgmRtn) return;
                }
            }
            if (WS_REF_AMT_AREA.WS_REF_AMT == 0) {
                WS_REF_AMT_AREA.WS_REF_AMT_LOC = 0;
            } else {
                if (WS_REF_AMT_AREA.WS_REF_CCY.equalsIgnoreCase(BPRFCPHO.REF_CCY)) {
                    CEP.TRC(SCCGWA, "SAME REF CCY");
                    WS_REF_AMT_AREA.WS_REF_AMT_LOC = WS_REF_AMT_AREA.WS_REF_AMT;
                    CEP.TRC(SCCGWA, WS_REF_AMT_AREA.WS_REF_AMT);
                    CEP.TRC(SCCGWA, WS_REF_AMT_AREA.WS_REF_AMT_LOC);
                } else {
                    CEP.TRC(SCCGWA, "NOT SAME REF CCY");
                    CEP.TRC(SCCGWA, WS_REF_AMT_AREA.WS_REF_CCY);
                    CEP.TRC(SCCGWA, BPRFCPHO.REF_CCY);
                    CEP.TRC(SCCGWA, WS_REF_AMT_AREA.WS_REF_AMT);
                    IBS.init(SCCGWA, BPCFX);
                    BPCFX.FUNC = '3';
                    BPCFX.EXR_TYPE = BPRFBAS.RATE_GROUP;
                    BPCFX.BUY_CCY = WS_REF_AMT_AREA.WS_REF_CCY;
                    BPCFX.BUY_AMT = WS_REF_AMT_AREA.WS_REF_AMT;
                    BPCFX.SELL_CCY = BPRFCPHO.REF_CCY;
                    S000_CALL_BPZSFX();
                    if (pgmRtn) return;
                    WS_REF_AMT_AREA.WS_REF_AMT_LOC = BPCFX.SELL_AMT;
                    CEP.TRC(SCCGWA, WS_REF_AMT_AREA.WS_REF_AMT_LOC);
                }
            }
        }
    }
    public void R000_GET_NEXT_AMT_CN() throws IOException,SQLException,Exception {
        if (WS_REF_AMT_AREA.WS_REF_END_DT < WS_PROC_DT) {
            if ((WS_AMT_TYPE == 11 
                || WS_AMT_TYPE == 12 
                || WS_AMT_TYPE == 13) 
                && BPCUFEEC.RUN_MODE == 'O') {
                CEP.TRC(SCCGWA, BPCUFEEC.CAL_AMT);
                WS_TXN_AMT = BPCUFEEC.CAL_AMT;
            }
            if (WS_AMT_TYPE == 0 
                || ((WS_AMT_TYPE == 11 
                || WS_AMT_TYPE == 12 
                || WS_AMT_TYPE == 13) 
                && BPCUFEEC.RUN_MODE == 'O')) {
                CEP.TRC(SCCGWA, "USING TXN AMT");
                CEP.TRC(SCCGWA, "LN AMT");
                WS_REF_AMT_AREA.WS_REF_AMT = WS_TXN_AMT;
                WS_REF_AMT_AREA.WS_REF_CCY = WS_TXN_CCY;
                WS_REF_AMT_AREA.WS_REF_PCT = 100;
                WS_REF_AMT_AREA.WS_REF_STA_DT = BPCUFEEC.STA_DT;
                WS_REF_AMT_AREA.WS_REF_END_DT = BPCUFEEC.END_DT;
                WS_REF_AMT_AREA.WS_REF_N_STA_DT = 99991231;
                WS_EX_FROM_CCY = WS_REF_AMT_AREA.WS_REF_CCY;
            } else {
                if (BPCUFEEC.RUN_MODE == 'O') {
                    CEP.TRC(SCCGWA, "ONLINE MOD TO CALL LN CPN");
                    R000_GET_NEXT_AMT_ONL();
                    if (pgmRtn) return;
                } else {
                    CEP.TRC(SCCGWA, "BATCH MOD TO READ PBTFRAH");
                    R000_GET_NEXT_AMT_BAT_CN();
                    if (pgmRtn) return;
                }
            }
            if (WS_REF_AMT_AREA.WS_REF_AMT == 0) {
                WS_REF_AMT_AREA.WS_REF_AMT_LOC = 0;
            } else {
                if (WS_REF_AMT_AREA.WS_REF_CCY.equalsIgnoreCase(BPRFCPHO.REF_CCY)) {
                    CEP.TRC(SCCGWA, "SAME REF CCY");
                    WS_REF_AMT_AREA.WS_REF_AMT_LOC = WS_REF_AMT_AREA.WS_REF_AMT;
                    CEP.TRC(SCCGWA, WS_REF_AMT_AREA.WS_REF_AMT);
                    CEP.TRC(SCCGWA, WS_REF_AMT_AREA.WS_REF_AMT_LOC);
                } else {
                    CEP.TRC(SCCGWA, "NOT SAME REF CCY");
                    CEP.TRC(SCCGWA, WS_REF_AMT_AREA.WS_REF_CCY);
                    CEP.TRC(SCCGWA, BPRFCPHO.REF_CCY);
                    CEP.TRC(SCCGWA, WS_REF_AMT_AREA.WS_REF_AMT);
                    IBS.init(SCCGWA, BPCFX);
                    BPCFX.FUNC = '3';
                    BPCFX.EXR_TYPE = BPRFBAS.RATE_GROUP;
                    BPCFX.BUY_CCY = WS_REF_AMT_AREA.WS_REF_CCY;
                    BPCFX.BUY_AMT = WS_REF_AMT_AREA.WS_REF_AMT;
                    BPCFX.SELL_CCY = BPRFCPHO.REF_CCY;
                    S000_CALL_BPZSFX();
                    if (pgmRtn) return;
                    WS_REF_AMT_AREA.WS_REF_AMT_LOC = BPCFX.SELL_AMT;
                    CEP.TRC(SCCGWA, WS_REF_AMT_AREA.WS_REF_AMT_LOC);
                }
            }
        }
    }
    public void R000_GET_NEXT_AMT_ONL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRFSCH.REL_CTRT_SRC);
        if (BPRFSCH.REL_CTRT_SRC == '0') {
            R100_GET_NEXT_LOAN_AMT_ONL();
            if (pgmRtn) return;
        } else {
        }
    }
    public void R100_GET_NEXT_LOAN_AMT_ONL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CALL_LNZIAMT_FLG);
        if (WS_CALL_LNZIAMT_FLG != 'Y') {
            IBS.init(SCCGWA, LNCIAMT);
            LNCIAMT.CTA_NO = WS_REF_CTRT;
            LNCIAMT.AMT_TYP = WS_AMT_TYPE;
            LNCIAMT.START_DT = BPCUFEEC.STA_DT;
            LNCIAMT.END_DT = BPCUFEEC.END_DT;
            CEP.TRC(SCCGWA, WS_REF_CTRT);
            CEP.TRC(SCCGWA, WS_AMT_TYPE);
            CEP.TRC(SCCGWA, BPCUFEEC.STA_DT);
            CEP.TRC(SCCGWA, BPCUFEEC.END_DT);
            S000_CALL_LNZIAMT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, LNCIAMT.START_DT);
            CEP.TRC(SCCGWA, LNCIAMT.END_DT);
            CEP.TRC(SCCGWA, LNCIAMT.CCY);
            CEP.TRC(SCCGWA, LNCIAMT.OUTPUT[1-1].VAL_DT);
            CEP.TRC(SCCGWA, LNCIAMT.OUTPUT[1-1].AMT);
            CEP.TRC(SCCGWA, LNCIAMT.OUTPUT[1-1].PCT);
            WS_CALL_LNZIAMT_FLG = 'Y';
            WS_IAMT_IDX = 1;
        }
        CEP.TRC(SCCGWA, WS_IAMT_IDX);
        while (WS_IAMT_IDX < K_IAMT_IDX 
            && LNCIAMT.OUTPUT[WS_IAMT_IDX + 1-1].VAL_DT <= WS_PROC_DT 
            && LNCIAMT.OUTPUT[WS_IAMT_IDX + 1-1].VAL_DT != 0) {
            WS_IAMT_IDX += 1;
        }
        CEP.TRC(SCCGWA, WS_IAMT_IDX);
        if (LNCIAMT.OUTPUT[WS_IAMT_IDX-1].VAL_DT == 0) {
            CEP.TRC(SCCGWA, "AAAAAAAAAAAAAAAAAAAA");
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFOUND, BPCUFEEC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (WS_IAMT_IDX == K_IAMT_IDX 
            || LNCIAMT.OUTPUT[WS_IAMT_IDX + 1-1].VAL_DT == 0) {
            WS_REF_AMT_AREA.WS_REF_END_DT = 99991231;
            WS_REF_AMT_AREA.WS_REF_N_STA_DT = 99991231;
        } else {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = LNCIAMT.OUTPUT[WS_IAMT_IDX + 1-1].VAL_DT;
            SCCCLDT.DAYS = -1;
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            WS_REF_AMT_AREA.WS_REF_END_DT = SCCCLDT.DATE2;
            WS_REF_AMT_AREA.WS_REF_N_STA_DT = LNCIAMT.OUTPUT[WS_IAMT_IDX + 1-1].VAL_DT;
        }
        WS_REF_AMT_AREA.WS_REF_STA_DT = LNCIAMT.OUTPUT[WS_IAMT_IDX-1].VAL_DT;
        WS_REF_AMT_AREA.WS_REF_AMT = LNCIAMT.OUTPUT[WS_IAMT_IDX-1].AMT;
        WS_REF_AMT_AREA.WS_REF_CCY = LNCIAMT.CCY;
        WS_REF_AMT_AREA.WS_REF_PCT = LNCIAMT.OUTPUT[WS_IAMT_IDX-1].PCT;
        WS_EX_FROM_CCY = WS_REF_AMT_AREA.WS_REF_CCY;
        CEP.TRC(SCCGWA, WS_REF_AMT_AREA.WS_REF_AMT);
        CEP.TRC(SCCGWA, WS_REF_AMT_AREA.WS_REF_CCY);
        CEP.TRC(SCCGWA, WS_REF_AMT_AREA.WS_REF_PCT);
        CEP.TRC(SCCGWA, WS_REF_AMT_AREA.WS_REF_STA_DT);
        CEP.TRC(SCCGWA, WS_REF_AMT_AREA.WS_REF_END_DT);
        CEP.TRC(SCCGWA, WS_REF_AMT_AREA.WS_REF_N_STA_DT);
    }
    public void R000_GET_NEXT_AMT_BAT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRFSCH.REL_CTRT_SRC);
        if (BPRFSCH.REL_CTRT_SRC == '0') {
            R100_GET_NEXT_LOAN_AMT_ONL();
            if (pgmRtn) return;
        } else {
        }
    }
    public void R000_GET_NEXT_AMT_BAT_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_AMT_TYPE);
        if (WS_AMT_TYPE == '01'
            || WS_AMT_TYPE == '02') {
            R100_GET_NEXT_LOAN_AMT_ONL();
            if (pgmRtn) return;
        } else if (WS_AMT_TYPE == '11'
            || WS_AMT_TYPE == '12'
            || WS_AMT_TYPE == '13') {
            R100_GET_NEXT_LOAN_AMT_BAT_CN();
            if (pgmRtn) return;
        } else {
        }
    }
    public void R100_GET_NEXT_LOAN_AMT_BAT() throws IOException,SQLException,Exception {
        if (WS_FRAH_STARTBR_FLG != 'Y') {
            CEP.TRC(SCCGWA, "STARTBR BPTFRAH");
            IBS.init(SCCGWA, BPRFRAH);
            IBS.init(SCCGWA, BPCRFRAH);
            BPCRFRAH.INFO.POINTER = BPRFRAH;
            BPCRFRAH.INFO.REC_LEN = 738;
            BPRFRAH.KEY.CTRT_NO = WS_REF_CTRT;
            BPCRFRAH.INFO.FUNC = 'B';
            BPCRFRAH.INFO.OPT = 'S';
            S000_CALL_BPZRFRAH();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "READ A RECORD");
            CEP.TRC(SCCGWA, "*******************");
            CEP.TRC(SCCGWA, BPRFRAH.KEY.CTRT_NO);
            CEP.TRC(SCCGWA, WS_PROC_DT);
            BPCRFRAH.INFO.FUNC = 'B';
            BPCRFRAH.INFO.OPT = 'N';
            S000_CALL_BPZRFRAH();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPRFRAH.KEY.VALUE_DATE);
            if (BPCRFRAH.RETURN_INFO == 'N' 
                || BPRFRAH.KEY.VALUE_DATE > WS_PROC_DT) {
                CEP.TRC(SCCGWA, "BBBBBBBBBBBBBBBBBBBBBB");
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFOUND, BPCUFEEC.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            IBS.CLONE(SCCGWA, BPRFRAH, BPRFRAHO);
            WS_FRAH_STARTBR_FLG = 'Y';
        }
        CEP.TRC(SCCGWA, "LOOP FOR READNEXT");
        while (BPRFRAH.KEY.VALUE_DATE <= WS_PROC_DT 
            && BPCRFRAH.RETURN_INFO != 'N') {
            IBS.CLONE(SCCGWA, BPRFRAH, BPRFRAHO);
            BPCRFRAH.INFO.FUNC = 'B';
            BPCRFRAH.INFO.OPT = 'N';
            S000_CALL_BPZRFRAH();
            if (pgmRtn) return;
        }
        WS_REF_AMT_AREA.WS_REF_STA_DT = BPRFRAHO.KEY.VALUE_DATE;
        if (BPCRFRAH.RETURN_INFO == 'N') {
            WS_REF_AMT_AREA.WS_REF_END_DT = 99991231;
            WS_REF_AMT_AREA.WS_REF_N_STA_DT = 99991231;
        } else {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = BPRFRAH.KEY.VALUE_DATE;
            SCCCLDT.DAYS = -1;
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            WS_REF_AMT_AREA.WS_REF_END_DT = SCCCLDT.DATE2;
            WS_REF_AMT_AREA.WS_REF_N_STA_DT = BPRFRAH.KEY.VALUE_DATE;
        }
        WS_REF_AMT_AREA.WS_REF_CCY = BPRFRAHO.REF_CCY;
        WS_EX_FROM_CCY = WS_REF_AMT_AREA.WS_REF_CCY;
        if (WS_AMT_TYPE == 1) {
            WS_REF_AMT_AREA.WS_REF_AMT = BPRFRAHO.REF_AMT1;
            WS_REF_AMT_AREA.WS_REF_PCT = 100.00;
        } else if (WS_AMT_TYPE == 2) {
            WS_REF_AMT_AREA.WS_REF_AMT = BPRFRAHO.REF_AMT2;
            if (BPRFRAHO.REF_AMT1 == 0) {
                WS_REF_AMT_AREA.WS_REF_AMT = 0;
                WS_REF_AMT_AREA.WS_REF_PCT = 0;
            } else {
                WS_REF_AMT_AREA.WS_REF_PCT = BPRFRAHO.REF_AMT2 * 100 / BPRFRAHO.REF_AMT1;
                bigD = new BigDecimal(WS_REF_AMT_AREA.WS_REF_PCT);
                WS_REF_AMT_AREA.WS_REF_PCT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            }
        } else {
            WS_REF_AMT_AREA.WS_REF_AMT = 0;
            WS_REF_AMT_AREA.WS_REF_PCT = 0;
        }
        CEP.TRC(SCCGWA, WS_REF_AMT_AREA.WS_REF_AMT);
        CEP.TRC(SCCGWA, WS_REF_AMT_AREA.WS_REF_CCY);
        CEP.TRC(SCCGWA, WS_REF_AMT_AREA.WS_REF_PCT);
        CEP.TRC(SCCGWA, WS_REF_AMT_AREA.WS_REF_STA_DT);
        CEP.TRC(SCCGWA, WS_REF_AMT_AREA.WS_REF_END_DT);
        CEP.TRC(SCCGWA, WS_REF_AMT_AREA.WS_REF_N_STA_DT);
    }
    public void R100_GET_NEXT_LOAN_AMT_BAT_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "GET 11 12 13 LN AMT");
        IBS.init(SCCGWA, LNCSSCHE);
        LNCSSCHE.DATA_AREA.CONTRACT_NO = BPRFSCH.REL_CTRT_NO;
        LNCSSCHE.CUR_FLG = 'M';
        S000_CALL_LNZSSCHE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCSSCHE.OUT_INFO[1-1].O_PAY_AMT);
        CEP.TRC(SCCGWA, LNCSSCHE.OUT_INFO[1-1].O_PAY_PRIN);
        CEP.TRC(SCCGWA, LNCSSCHE.OUT_INFO[1-1].O_PAY_INT);
        if (WS_AMT_TYPE == 11) {
            WS_REF_AMT_AREA.WS_REF_AMT = LNCSSCHE.OUT_INFO[1-1].O_PAY_AMT;
        }
        if (WS_AMT_TYPE == 12) {
            WS_REF_AMT_AREA.WS_REF_AMT = LNCSSCHE.OUT_INFO[1-1].O_PAY_PRIN;
        }
        if (WS_AMT_TYPE == 13) {
            WS_REF_AMT_AREA.WS_REF_AMT = LNCSSCHE.OUT_INFO[1-1].O_PAY_INT;
        }
        WS_REF_AMT_AREA.WS_REF_CCY = WS_TXN_CCY;
        WS_REF_AMT_AREA.WS_REF_PCT = 100;
        WS_REF_AMT_AREA.WS_REF_STA_DT = BPCUFEEC.STA_DT;
        WS_REF_AMT_AREA.WS_REF_END_DT = BPCUFEEC.END_DT;
        WS_REF_AMT_AREA.WS_REF_N_STA_DT = 99991231;
        WS_EX_FROM_CCY = WS_REF_AMT_AREA.WS_REF_CCY;
    }
    public void R100_GET_NEXT_LMT_AMT_BAT() throws IOException,SQLException,Exception {
        if (WS_FRAH_STARTBR_FLG != 'Y') {
            CEP.TRC(SCCGWA, "STARTBR BPTFRAH");
            IBS.init(SCCGWA, BPRFRAH);
            IBS.init(SCCGWA, BPCRFRAH);
            BPCRFRAH.INFO.POINTER = BPRFRAH;
            BPCRFRAH.INFO.REC_LEN = 738;
            BPRFRAH.KEY.CTRT_NO = "" + WS_REF_LMT_NO;
            JIBS_tmp_int = BPRFRAH.KEY.CTRT_NO.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) BPRFRAH.KEY.CTRT_NO = "0" + BPRFRAH.KEY.CTRT_NO;
            BPCRFRAH.INFO.FUNC = 'B';
            BPCRFRAH.INFO.OPT = 'S';
            S000_CALL_BPZRFRAH();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "READ A RECORD");
            CEP.TRC(SCCGWA, "*******************");
            CEP.TRC(SCCGWA, BPRFRAH.KEY.CTRT_NO);
            CEP.TRC(SCCGWA, WS_PROC_DT);
            BPCRFRAH.INFO.FUNC = 'B';
            BPCRFRAH.INFO.OPT = 'N';
            S000_CALL_BPZRFRAH();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPRFRAH.KEY.VALUE_DATE);
            if (BPCRFRAH.RETURN_INFO == 'N' 
                || BPRFRAH.KEY.VALUE_DATE > WS_PROC_DT) {
                CEP.TRC(SCCGWA, "BBBBBBBBBBBBBBBBBBBBBB");
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFOUND, BPCUFEEC.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            IBS.CLONE(SCCGWA, BPRFRAH, BPRFRAHO);
            WS_FRAH_STARTBR_FLG = 'Y';
        }
        CEP.TRC(SCCGWA, "LOOP FOR READNEXT");
        while (BPRFRAH.KEY.VALUE_DATE <= WS_PROC_DT 
            && BPCRFRAH.RETURN_INFO != 'N') {
            IBS.CLONE(SCCGWA, BPRFRAH, BPRFRAHO);
            BPCRFRAH.INFO.FUNC = 'B';
            BPCRFRAH.INFO.OPT = 'N';
            S000_CALL_BPZRFRAH();
            if (pgmRtn) return;
        }
        WS_REF_AMT_AREA.WS_REF_STA_DT = BPRFRAHO.KEY.VALUE_DATE;
        if (BPCRFRAH.RETURN_INFO == 'N') {
            WS_REF_AMT_AREA.WS_REF_END_DT = 99991231;
            WS_REF_AMT_AREA.WS_REF_N_STA_DT = 99991231;
        } else {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = BPRFRAH.KEY.VALUE_DATE;
            SCCCLDT.DAYS = -1;
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            WS_REF_AMT_AREA.WS_REF_END_DT = SCCCLDT.DATE2;
            WS_REF_AMT_AREA.WS_REF_N_STA_DT = BPRFRAH.KEY.VALUE_DATE;
        }
        WS_REF_AMT_AREA.WS_REF_CCY = BPRFRAHO.REF_CCY;
        WS_EX_FROM_CCY = WS_REF_AMT_AREA.WS_REF_CCY;
        CEP.TRC(SCCGWA, WS_AMT_TYPE);
        if (WS_AMT_TYPE == 4) {
            WS_REF_AMT_AREA.WS_REF_AMT = BPRFRAHO.REF_AMT1;
            WS_REF_AMT_AREA.WS_REF_PCT = 100.00;
        } else if (WS_AMT_TYPE == 5) {
            WS_REF_AMT_AREA.WS_REF_AMT = BPRFRAHO.REF_AMT2;
            WS_REF_AMT_AREA.WS_REF_PCT = 100.00;
        } else {
            WS_REF_AMT_AREA.WS_REF_AMT = 0;
            WS_REF_AMT_AREA.WS_REF_PCT = 0;
        }
        CEP.TRC(SCCGWA, WS_REF_AMT_AREA.WS_REF_AMT);
        CEP.TRC(SCCGWA, WS_REF_AMT_AREA.WS_REF_CCY);
        CEP.TRC(SCCGWA, WS_REF_AMT_AREA.WS_REF_PCT);
        CEP.TRC(SCCGWA, WS_REF_AMT_AREA.WS_REF_STA_DT);
        CEP.TRC(SCCGWA, WS_REF_AMT_AREA.WS_REF_END_DT);
        CEP.TRC(SCCGWA, WS_REF_AMT_AREA.WS_REF_N_STA_DT);
    }
    public void R000_GET_NEXT_PARM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_PARM_AREA.WS_PARM_END_DT);
        CEP.TRC(SCCGWA, WS_PROC_DT);
        if (WS_PARM_AREA.WS_PARM_END_DT < WS_PROC_DT) {
            if (WS_FCPH_STARTBR_FLG != 'Y') {
                CEP.TRC(SCCGWA, "STARTBR BPTFCPH");
                IBS.init(SCCGWA, BPRFCPH);
                IBS.init(SCCGWA, BPCRCPHM);
                BPCRCPHM.INFO.POINTER = BPRFCPH;
                BPCRCPHM.INFO.REC_LEN = 509;
                BPRFCPH.KEY.CTRT_NO = BPCUFEEC.CTRT_NO;
                BPCRCPHM.INFO.FUNC = 'B';
                BPCRCPHM.INFO.OPT = 'S';
                S000_CALL_BPZRCPHM();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, "READ A RECORD");
                BPCRCPHM.INFO.FUNC = 'B';
                BPCRCPHM.INFO.OPT = 'N';
                S000_CALL_BPZRCPHM();
                if (pgmRtn) return;
                if (BPCRCPHM.RETURN_INFO == 'N' 
                    || BPRFCPH.KEY.VALUE_DATE > WS_PROC_DT) {
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FSTD_NOTFND, BPCUFEEC.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                IBS.CLONE(SCCGWA, BPRFCPH, BPRFCPHO);
                WS_FCPH_STARTBR_FLG = 'Y';
            }
            CEP.TRC(SCCGWA, "LOOP FOR READNEXT FCPH");
            while (BPRFCPH.KEY.VALUE_DATE <= WS_PROC_DT 
                && BPCRCPHM.RETURN_INFO != 'N') {
                CEP.TRC(SCCGWA, BPRFCPH.KEY.VALUE_DATE);
                IBS.CLONE(SCCGWA, BPRFCPH, BPRFCPHO);
                BPCRCPHM.INFO.FUNC = 'B';
                BPCRCPHM.INFO.OPT = 'N';
                S000_CALL_BPZRCPHM();
                if (pgmRtn) return;
            }
            WS_PARM_AREA.WS_PARM_STA_DT = BPRFCPHO.KEY.VALUE_DATE;
            CEP.TRC(SCCGWA, WS_PARM_AREA.WS_PARM_STA_DT);
            if (BPCRCPHM.RETURN_INFO == 'N') {
                WS_PARM_AREA.WS_PARM_END_DT = 99991231;
                WS_PARM_AREA.WS_PARM_N_STA_DT = 99991231;
            } else {
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = BPRFCPH.KEY.VALUE_DATE;
                SCCCLDT.DAYS = -1;
                S000_CALL_SCSSCLDT();
                if (pgmRtn) return;
                WS_PARM_AREA.WS_PARM_END_DT = SCCCLDT.DATE2;
                WS_PARM_AREA.WS_PARM_N_STA_DT = BPRFCPH.KEY.VALUE_DATE;
            }
            CEP.TRC(SCCGWA, WS_PARM_AREA.WS_PARM_STA_DT);
            CEP.TRC(SCCGWA, WS_PARM_AREA.WS_PARM_END_DT);
            CEP.TRC(SCCGWA, WS_PARM_AREA.WS_PARM_N_STA_DT);
        }
    }
    public void R000_GET_NEXT_DATE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_REF_AMT_AREA.WS_REF_N_STA_DT);
        CEP.TRC(SCCGWA, WS_PARM_AREA.WS_PARM_N_STA_DT);
        WS_PROC_DT = WS_REF_AMT_AREA.WS_REF_N_STA_DT;
        if (WS_PARM_AREA.WS_PARM_N_STA_DT < WS_PROC_DT) {
            WS_PROC_DT = WS_PARM_AREA.WS_PARM_N_STA_DT;
        }
        CEP.TRC(SCCGWA, WS_PROC_DT);
    }
    public void R000_CHECK_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = WS_DATE;
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            CEP.TRC(SCCGWA, "CCCCCCCCCCCCCCCCC");
            IBS.CPY2CLS(SCCGWA, SCCCKDT.RC+"", BPCUFEEC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRCPHM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-CAL-PARM", BPCRCPHM);
        if (BPCRCPHM.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DDDDDDDDDDDDDDDDD");
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRCPHM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUFEEC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNXXXXXX() throws IOException,SQLException,Exception {
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT2 = new SCSSCLDT();
        SCSSCLDT2.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            CEP.TRC(SCCGWA, "EEEEEEEEEEEEEEEEEEE");
            IBS.CPY2CLS(SCCGWA, SCCCLDT.RC+"", BPCUFEEC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRFRAH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-REF-AMT", BPCRFRAH);
        if (BPCRFRAH.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "FFFFFFFFFFFFFFF");
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRFRAH.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUFEEC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRFSCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-FEESCH", BPCRFSCH);
        if (BPCRFSCH.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "GGGGGGGGGGGGGG");
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRFSCH.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUFEEC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRFCTR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-FEECTR", BPCRFCTR);
        if (BPCRFCTR.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "HHHHHHHHHHHHHHHHH");
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRFCTR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUFEEC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSFX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-FX", BPCFX);
        if (BPCFX.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFX.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTFBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-T-FEE-INFO", BPCTFBAS);
        if (BPCTFBAS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "JJJJJJJJJJJJ");
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTFBAS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUFEEC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZCIDAY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-CALC-INT-DAYS", BPCIDAY);
        if (BPCIDAY.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "KKKKKKKKKKKKKKK");
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCIDAY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUFEEC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRDAMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-ROUND-AMT", BPCRDAMT);
        if (BPCRDAMT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "LLLLLLLLLLLLLL");
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRDAMT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUFEEC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZIAMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-I-CTA-AMT", LNCIAMT);
        CEP.TRC(SCCGWA, LNCIAMT.RC);
        if (LNCIAMT.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "MMMMMMMMMMMMM");
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCIAMT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUFEEC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSSCHE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-INQ-SSCHE", LNCSSCHE);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_FCPH_STARTBR_FLG);
        if (WS_FCPH_STARTBR_FLG == 'Y') {
            WS_FCPH_STARTBR_FLG = ' ';
            BPCRCPHM.INFO.FUNC = 'B';
            BPCRCPHM.INFO.OPT = 'E';
            S000_CALL_BPZRCPHM();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "BBBBBBBBB");
        if (WS_FRAH_STARTBR_FLG == 'Y') {
            WS_FRAH_STARTBR_FLG = ' ';
            BPCRFRAH.INFO.FUNC = 'B';
            BPCRFRAH.INFO.OPT = 'E';
            S000_CALL_BPZRFRAH();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCUFEEC.RC);
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCUFEEC.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCUFEEC = ");
            CEP.TRC(SCCGWA, BPCUFEEC);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
