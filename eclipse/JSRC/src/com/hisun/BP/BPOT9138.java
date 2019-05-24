package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT9138 {
    String K_OUTPUT_FMT = "BP078";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String CPN_F_F_TX_INFO = "BP-F-F-TX-INFO      ";
    String CPN_F_F_CON_CHG_INFO = "BP-F-F-CON-CHG-INFO ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_CNT = 0;
    short WS_CNT1 = 0;
    int WS_START_DATE = 0;
    int WS_SETTLE_DATE = 0;
    int WS_END_DATE = 0;
    int WS_TMP_DATE = 0;
    int WS_PRE_SETTLE_DATE = 0;
    double WS_TOT_AMT = 0;
    double WS_TMP_AMT = 0;
    double WS_SET_AMT = 0;
    double WS_CAL_AMT = 0;
    short WS_SCHD_COUNT = 0;
    char WS_FINAL_FLAG = ' ';
    char WS_LAST_SCHD_FLAG = ' ';
    BPOT9138_WS_OUT_DATA WS_OUT_DATA = new BPOT9138_WS_OUT_DATA();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCRFSCH BPCRFSCH = new BPCRFSCH();
    BPRFSCH BPRFSCH = new BPRFSCH();
    BPRFSCHD BPRFSCHD = new BPRFSCHD();
    BPCRSCHD BPCRSCHD = new BPCRSCHD();
    BPCUFEEC BPCUFEEC = new BPCUFEEC();
    BPRPACCR BPRPACCR = new BPRPACCR();
    BPCRACCR BPCRACCR = new BPCRACCR();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPCFFCON BPCFFCON = new BPCFFCON();
    SCCGWA SCCGWA;
    BPB1107_AWA_1107 BPB1107_AWA_1107;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCGCFEE BPCGCFEE;
    BPCGPFEE BPCGPFEE;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT9138 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1107_AWA_1107>");
        BPB1107_AWA_1107 = (BPB1107_AWA_1107) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCGCFEE = new BPCGCFEE();
        IBS.init(SCCGWA, BPCGCFEE);
        IBS.CPY2CLS(SCCGWA, GWA_BP_AREA.FEE_AREA.FEE_DATA_PTR, BPCGCFEE);
        BPCGPFEE = (BPCGPFEE) GWA_BP_AREA.FEE_AREA.FEE_PARM_PTR;
        IBS.init(SCCGWA, BPRFSCH);
        IBS.init(SCCGWA, BPRFSCHD);
        IBS.init(SCCGWA, BPCRFSCH);
        IBS.init(SCCGWA, BPCUFEEC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (BPB1107_AWA_1107.FUNC == '1'
            || BPB1107_AWA_1107.FUNC == '2'
            || BPB1107_AWA_1107.FUNC == '3') {
            B020_GET_FEE_SCHEDULE();
        } else if (BPB1107_AWA_1107.FUNC == '5') {
            B050_01_SET_FFTXI();
            B050_02_SET_FFCON();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "BPOT9138" + "INVALID FUNC(" + BPB1107_AWA_1107.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        B090_OUTPUT_INFO();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB1107_AWA_1107.FUNC);
        CEP.TRC(SCCGWA, BPB1107_AWA_1107.FEE_CTRT);
        CEP.TRC(SCCGWA, BPB1107_AWA_1107.FEE_CODE);
        CEP.TRC(SCCGWA, BPB1107_AWA_1107.CI_NO);
        CEP.TRC(SCCGWA, BPB1107_AWA_1107.FEE_AC);
        CEP.TRC(SCCGWA, BPB1107_AWA_1107.CHG_CCY);
        CEP.TRC(SCCGWA, BPB1107_AWA_1107.CCY_TYPE);
        CEP.TRC(SCCGWA, BPB1107_AWA_1107.BR);
        CEP.TRC(SCCGWA, BPB1107_AWA_1107.CHG_AMT);
        CEP.TRC(SCCGWA, BPB1107_AWA_1107.RMK);
        CEP.TRC(SCCGWA, BPB1107_AWA_1107.AMO_FLG);
        CEP.TRC(SCCGWA, BPB1107_AWA_1107.AMO_STDT);
        CEP.TRC(SCCGWA, BPB1107_AWA_1107.AMO_EDDT);
        IBS.init(SCCGWA, BPCRFSCH);
        IBS.init(SCCGWA, BPRFSCH);
        BPRFSCH.REL_CTRT_NO = BPB1107_AWA_1107.FEE_CTRT;
        BPCRFSCH.INFO.FUNC = 'B';
        BPCRFSCH.INFO.OPT = '4';
        S000_CALL_BPZRFSCH();
        if (BPCRFSCH.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FSCH_NOTFND;
            S000_ERR_MSG_PROC();
        }
        BPCRFSCH.INFO.FUNC = 'N';
        S000_CALL_BPZRFSCH();
        if (BPCRFSCH.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FSCH_NOTFND;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPRFSCH.KEY.CTRT_NO);
        CEP.TRC(SCCGWA, BPRFSCH.FEE_STATUS);
        if (BPRFSCH.FEE_STATUS == '2') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FSCH_COMPLETE;
            S000_ERR_MSG_PROC();
        }
        if (BPRFSCH.FEE_STATUS == '3') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FSCH_DELETED;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPB1107_AWA_1107.FEE_CODE);
        CEP.TRC(SCCGWA, BPRFSCH.FEE_TYPE);
        if (!BPB1107_AWA_1107.FEE_CODE.equalsIgnoreCase(BPRFSCH.FEE_TYPE)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CODE_NOT_SAME;
            S000_ERR_MSG_PROC();
        }
        if (BPB1107_AWA_1107.FEE_AC.trim().length() > 0 
            && !BPB1107_AWA_1107.FEE_AC.equalsIgnoreCase(BPRFSCH.CHARGE_AC)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CHARGE_AC_ERR;
            S000_ERR_MSG_PROC();
        }
        if (BPB1107_AWA_1107.CI_NO.trim().length() > 0 
            && !BPB1107_AWA_1107.CI_NO.equalsIgnoreCase(BPRFSCH.CI_NO)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AC_CI_NOT_RELATED;
            S000_ERR_MSG_PROC();
        }
        if (BPB1107_AWA_1107.CHG_CCY.trim().length() > 0 
            && !BPB1107_AWA_1107.CHG_CCY.equalsIgnoreCase(BPRFSCH.CHG_CCY_REAL)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CHG_CAL_CCY_NOT_SAME;
            S000_ERR_MSG_PROC();
        }
        BPCRFSCH.INFO.FUNC = 'E';
        S000_CALL_BPZRFSCH();
        if (BPB1107_AWA_1107.AMO_FLG == 'Y' 
            && (BPB1107_AWA_1107.AMO_STDT == 0 
            || BPB1107_AWA_1107.AMO_EDDT == 0)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_AMODT_MUSTINPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_GET_FEE_SCHEDULE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRSCHD);
        IBS.init(SCCGWA, BPRFSCHD);
        BPRFSCHD.KEY.CTRT_NO = BPRFSCH.KEY.CTRT_NO;
        BPCRSCHD.INFO.FUNC = '1';
        S000_CALL_BPZRSCHD();
        WS_SCHD_COUNT = BPCRSCHD.INFO.SCHD_COUNT;
        CEP.TRC(SCCGWA, WS_SCHD_COUNT);
        WS_CNT1 = 0;
        WS_TOT_AMT = 0;
        WS_PRE_SETTLE_DATE = 0;
        WS_FINAL_FLAG = 'N';
        IBS.init(SCCGWA, BPCRSCHD);
        IBS.init(SCCGWA, BPRFSCHD);
        BPRFSCHD.KEY.CTRT_NO = BPRFSCH.KEY.CTRT_NO;
        BPCRSCHD.INFO.FUNC = 'B';
        BPCRSCHD.INFO.OPT = '3';
        S000_CALL_BPZRSCHD();
        BPCRSCHD.INFO.FUNC = 'N';
        S000_CALL_BPZRSCHD();
        if (BPB1107_AWA_1107.FUNC == '1') {
            B020_INQ_FEE();
        } else if (BPB1107_AWA_1107.FUNC == '3'
            || BPB1107_AWA_1107.FUNC == '4') {
            B030_CHARGE_FEE();
        } else {
        }
        BPCRSCHD.INFO.FUNC = 'E';
        S000_CALL_BPZRSCHD();
    }
    public void B020_INQ_FEE() throws IOException,SQLException,Exception {
        while (BPCRSCHD.RETURN_INFO != 'N') {
            WS_CNT1 += 1;
            if (WS_CNT1 == WS_SCHD_COUNT) {
                WS_FINAL_FLAG = 'Y';
            }
            if (BPRFSCHD.STS == 'N') {
                if (WS_FINAL_FLAG == 'Y' 
                    && BPRFSCH.CHARGE_AMT > 0) {
                    BPCUFEEC.FEE_AMT = BPRFSCH.CHARGE_AMT - WS_TOT_AMT;
                } else {
                    if (WS_PRE_SETTLE_DATE == 0) {
                        WS_START_DATE = BPRFSCH.START_DATE;
                    } else {
                        WS_START_DATE = WS_PRE_SETTLE_DATE;
                    }
                    WS_END_DATE = BPRFSCHD.KEY.SETTLE_DATE;
                    if (WS_START_DATE < SCCGWA.COMM_AREA.AC_DATE 
                        && WS_END_DATE > SCCGWA.COMM_AREA.AC_DATE) {
                        WS_END_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    }
                    B020_01_CAL_FEE_AMT();
                }
            }
            if (BPRFSCHD.KEY.SETTLE_DATE <= SCCGWA.COMM_AREA.AC_DATE) {
                WS_OUT_DATA.WS_OUT_AMT1 += BPCUFEEC.FEE_AMT;
            }
            CEP.TRC(SCCGWA, WS_OUT_DATA.WS_OUT_AMT1);
            if (WS_END_DATE == SCCGWA.COMM_AREA.AC_DATE) {
                WS_OUT_DATA.WS_OUT_AMT3 = BPCUFEEC.FEE_AMT;
            }
            CEP.TRC(SCCGWA, WS_OUT_DATA.WS_OUT_AMT3);
            if (WS_START_DATE >= SCCGWA.COMM_AREA.AC_DATE) {
                WS_OUT_DATA.WS_OUT_AMT2 += BPCUFEEC.FEE_AMT;
            }
            CEP.TRC(SCCGWA, WS_OUT_DATA.WS_OUT_AMT2);
            WS_PRE_SETTLE_DATE = BPRFSCHD.KEY.SETTLE_DATE;
            CEP.TRC(SCCGWA, "11111");
            if (WS_START_DATE < SCCGWA.COMM_AREA.AC_DATE 
                && WS_END_DATE > SCCGWA.COMM_AREA.AC_DATE) {
                WS_PRE_SETTLE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            }
            CEP.TRC(SCCGWA, "22222");
            WS_TOT_AMT = WS_TOT_AMT + BPCUFEEC.FEE_AMT;
            CEP.TRC(SCCGWA, "33333");
            if (BPRFSCHD.ADJ_AMT != 0) {
                WS_TOT_AMT = WS_TOT_AMT + BPRFSCHD.ADJ_AMT;
            }
            BPCRSCHD.INFO.FUNC = 'N';
            S000_CALL_BPZRSCHD();
        }
    }
    public void B030_CHARGE_FEE() throws IOException,SQLException,Exception {
        while (BPCRSCHD.RETURN_INFO != 'N') {
            WS_CNT1 += 1;
            if (WS_CNT1 == WS_SCHD_COUNT) {
                WS_FINAL_FLAG = 'Y';
            }
            if (BPRFSCHD.STS == 'N') {
                if (WS_FINAL_FLAG == 'Y' 
                    && BPRFSCH.CHARGE_AMT > 0) {
                    BPCUFEEC.FEE_AMT = BPRFSCH.CHARGE_AMT - WS_TOT_AMT;
                } else {
                    if (WS_PRE_SETTLE_DATE == 0) {
                        WS_START_DATE = BPRFSCH.START_DATE;
                    } else {
                        WS_START_DATE = WS_PRE_SETTLE_DATE;
                    }
                    WS_END_DATE = BPRFSCHD.KEY.SETTLE_DATE;
                    B020_01_CAL_FEE_AMT();
                }
            }
            if (BPRFSCHD.KEY.SETTLE_DATE <= SCCGWA.COMM_AREA.AC_DATE 
                || (WS_START_DATE < SCCGWA.COMM_AREA.AC_DATE 
                && SCCGWA.COMM_AREA.AC_DATE < WS_END_DATE)) {
                WS_SETTLE_DATE = BPRFSCHD.KEY.SETTLE_DATE;
                WS_SET_AMT = BPCUFEEC.FEE_AMT;
                B020_02_SETTL_PROCESS();
                WS_CAL_AMT += BPCUFEEC.FEE_AMT;
            }
            if (BPB1107_AWA_1107.FUNC == '3') {
                B030_01_CHARGE_UNCALED_FEE();
            }
            WS_PRE_SETTLE_DATE = BPRFSCHD.KEY.SETTLE_DATE;
            WS_TOT_AMT = WS_TOT_AMT + BPCUFEEC.FEE_AMT;
            if (BPRFSCHD.ADJ_AMT != 0) {
                WS_TOT_AMT = WS_TOT_AMT + BPRFSCHD.ADJ_AMT;
            }
            BPCRSCHD.INFO.FUNC = 'N';
            S000_CALL_BPZRSCHD();
        }
    }
    public void B030_01_CHARGE_UNCALED_FEE() throws IOException,SQLException,Exception {
        if (WS_CAL_AMT > BPB1107_AWA_1107.CHG_AMT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AMT_LESS_THAN_FEE;
            S000_ERR_MSG_PROC();
        }
        if (WS_START_DATE >= SCCGWA.COMM_AREA.AC_DATE) {
            if (BPCUFEEC.FEE_AMT <= BPB1107_AWA_1107.CHG_AMT - WS_CAL_AMT) {
                WS_SET_AMT = BPCUFEEC.FEE_AMT;
            }
            if (BPB1107_AWA_1107.CHG_AMT - WS_CAL_AMT < BPCUFEEC.FEE_AMT 
                && BPB1107_AWA_1107.CHG_AMT - WS_CAL_AMT > 0) {
                WS_SET_AMT = BPB1107_AWA_1107.CHG_AMT - WS_CAL_AMT;
            }
            if (BPB1107_AWA_1107.CHG_AMT - WS_CAL_AMT <= 0) {
                WS_SET_AMT = 0;
            }
            WS_SETTLE_DATE = BPRFSCHD.KEY.SETTLE_DATE;
            B020_02_SETTL_PROCESS();
            WS_CAL_AMT += BPCUFEEC.FEE_AMT;
        }
    }
    public void B020_01_CAL_FEE_AMT() throws IOException,SQLException,Exception {
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
                WS_END_DATE = SCCCLDT.DATE2;
            }
        }
        if (BPRFSCH.ACCRUAL_TYPE == 'L') {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = WS_START_DATE;
            SCCCLDT.DAYS = 1;
            S000_CALL_SCSSCLDT();
            WS_START_DATE = SCCCLDT.DATE2;
        }
        IBS.init(SCCGWA, BPCUFEEC);
        BPCUFEEC.CTRT_NO = BPRFSCH.KEY.CTRT_NO;
        BPCUFEEC.CTRT_TYP = 'S';
        BPCUFEEC.STA_DT = WS_START_DATE;
        BPCUFEEC.END_DT = WS_END_DATE;
        BPCUFEEC.RUN_MODE = 'O';
        S000_CALL_BPZUFEEC();
        CEP.TRC(SCCGWA, BPCUFEEC.FEE_AMT);
    }
    public void B020_02_SETTL_PROCESS() throws IOException,SQLException,Exception {
        WS_LAST_SCHD_FLAG = 'N';
        IBS.init(SCCGWA, BPRFSCHD);
        IBS.init(SCCGWA, BPCRSCHD);
        BPRFSCHD.KEY.CTRT_NO = BPRFSCH.KEY.CTRT_NO;
        BPRFSCHD.KEY.SETTLE_DATE = WS_SETTLE_DATE;
        BPCRSCHD.INFO.FUNC = 'B';
        BPCRSCHD.INFO.OPT = 'A';
        S000_CALL_BPZRSCHD();
        CEP.TRC(SCCGWA, BPRFSCHD.KEY.CTRT_NO);
        CEP.TRC(SCCGWA, BPRFSCHD.KEY.SETTLE_DATE);
        CEP.TRC(SCCGWA, BPRFSCHD.STS);
        CEP.TRC(SCCGWA, BPCRSCHD.RETURN_INFO);
        if (BPCRSCHD.RETURN_INFO == 'F') {
            if (BPRFSCHD.STS != 'C') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_LAST_SCHD_NOT_SET;
                S000_ERR_MSG_PROC();
            }
        }
        IBS.init(SCCGWA, BPRFSCHD);
        IBS.init(SCCGWA, BPCRSCHD);
        BPRFSCHD.KEY.CTRT_NO = BPRFSCH.KEY.CTRT_NO;
        BPRFSCHD.KEY.SETTLE_DATE = WS_SETTLE_DATE;
        BPCRSCHD.INFO.FUNC = 'R';
        S000_CALL_BPZRSCHD();
        if (BPCRSCHD.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SCHD_NOTFND;
            S000_ERR_MSG_PROC();
        }
        if (BPRFSCHD.STS != 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SCHD_HAS_SETT;
            S000_ERR_MSG_PROC();
        }
        BPRFSCHD.STS = 'C';
        BPRFSCHD.CHARGE_AMT = WS_SET_AMT - BPRFSCHD.ADJ_AMT;
        BPRFSCHD.REAL_AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRFSCHD.JNL_NO = SCCGWA.COMM_AREA.JRN_NO;
        BPRFSCHD.VOUCHER_NO = SCCGWA.COMM_AREA.VCH_NO;
        BPRFSCHD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRFSCHD.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
        BPRFSCHD.CHG_AMT_REAL = WS_SET_AMT;
        BPRFSCHD.CHG_CCY_REAL = BPB1107_AWA_1107.CHG_CCY;
        CEP.TRC(SCCGWA, BPRFSCHD.CHARGE_AMT);
        CEP.TRC(SCCGWA, BPRFSCHD.REAL_AC_DATE);
        CEP.TRC(SCCGWA, BPRFSCHD.JNL_NO);
        CEP.TRC(SCCGWA, BPRFSCHD.VOUCHER_NO);
        BPCRSCHD.INFO.FUNC = 'U';
        S000_CALL_BPZRSCHD();
        CEP.TRC(SCCGWA, WS_SET_AMT);
        if (WS_SET_AMT != 0) {
            R000_01_SET_FFTXI();
            R000_02_SET_FFCON();
        }
        IBS.init(SCCGWA, BPRPACCR);
        IBS.init(SCCGWA, BPCRACCR);
        BPRPACCR.KEY.CTRT_NO = BPRFSCH.KEY.CTRT_NO;
        BPCRACCR.INFO.FUNC = 'R';
        S000_CALL_BPZRACCR();
        if (BPCRACCR.RETURN_INFO == 'F') {
            BPRPACCR.LAST_SETTLE_DATE = BPRPACCR.SETTLE_DATE;
            BPRPACCR.SETTLE_DATE = WS_SETTLE_DATE;
            WS_TMP_AMT = BPRPACCR.SETTLE_AMT_TOTAL;
            WS_TMP_AMT = WS_TMP_AMT + WS_SET_AMT;
            CEP.TRC(SCCGWA, WS_TMP_AMT);
            BPRPACCR.SETTLE_AMT_TOTAL = WS_TMP_AMT;
            WS_TMP_AMT = BPRPACCR.ACCRUAL_AMT_TOTAL;
            WS_TMP_AMT = WS_TMP_AMT - WS_SET_AMT;
            CEP.TRC(SCCGWA, WS_TMP_AMT);
            BPRPACCR.ACCRUAL_AMT_TOTAL = WS_TMP_AMT;
            BPRPACCR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPRPACCR.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
            BPRPACCR.SUP_TEL1 = SCCGWA.COMM_AREA.SUP1_ID;
            BPRPACCR.SUP_TEL2 = SCCGWA.COMM_AREA.SUP2_ID;
            BPCRACCR.INFO.FUNC = 'U';
            S000_CALL_BPZRACCR();
        } else {
            IBS.init(SCCGWA, BPRPACCR);
            IBS.init(SCCGWA, BPCRACCR);
            BPRPACCR.KEY.CTRT_NO = BPRFSCH.KEY.CTRT_NO;
            BPRPACCR.SETTLE_DATE = WS_SETTLE_DATE;
            BPRPACCR.SETTLE_AMT_TOTAL = WS_SET_AMT;
            WS_TMP_AMT = 0;
            WS_TMP_AMT = WS_TMP_AMT - WS_SET_AMT;
            BPRPACCR.ACCRUAL_AMT_TOTAL = WS_TMP_AMT;
            BPRPACCR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPRPACCR.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
            BPRPACCR.SUP_TEL1 = SCCGWA.COMM_AREA.SUP1_ID;
            BPRPACCR.SUP_TEL2 = SCCGWA.COMM_AREA.SUP2_ID;
            BPCRACCR.INFO.FUNC = 'C';
            S000_CALL_BPZRACCR();
        }
        IBS.init(SCCGWA, BPRFSCHD);
        IBS.init(SCCGWA, BPCRSCHD);
        BPRFSCHD.KEY.CTRT_NO = BPRFSCH.KEY.CTRT_NO;
        BPRFSCHD.KEY.SETTLE_DATE = WS_SETTLE_DATE;
        BPCRSCHD.INFO.FUNC = 'B';
        BPCRSCHD.INFO.OPT = 'B';
        S000_CALL_BPZRSCHD();
        CEP.TRC(SCCGWA, BPRFSCHD.STS);
        if (BPCRSCHD.RETURN_INFO == 'N') {
            WS_LAST_SCHD_FLAG = 'Y';
        }
        if (WS_LAST_SCHD_FLAG == 'Y') {
            BPCRFSCH.INFO.FUNC = 'R';
            S000_CALL_BPZRFSCH();
            BPRFSCH.FEE_STATUS = '2';
            BPRFSCH.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCRFSCH.INFO.FUNC = 'U';
            S000_CALL_BPZRFSCH();
        }
    }
    public void R000_01_SET_FFTXI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        if (BPRFSCH.PAY_IND == 'R') {
            BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = WS_SETTLE_DATE;
            CEP.TRC(SCCGWA, BPRFSCH.CHARGE_METHOD);
            BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = BPRFSCH.CHARGE_METHOD;
            if (BPRFSCH.CHARGE_METHOD == '0' 
                || BPRFSCH.CHARGE_METHOD == '1' 
                || BPRFSCH.CHARGE_METHOD == '2') {
                BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = BPRFSCH.CHARGE_AC;
            }
            BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = BPRFSCH.CHARGE_CCY;
        } else {
            BPCFFTXI.TX_DATA.DIS_AC_INFO.DIS_VAL_DATE = WS_SETTLE_DATE;
            BPCFFTXI.TX_DATA.DIS_AC_INFO.DIS_AC_TYP = BPRFSCH.CHARGE_METHOD;
            if (BPRFSCH.CHARGE_METHOD == '0' 
                || BPRFSCH.CHARGE_METHOD == '1' 
                || BPRFSCH.CHARGE_METHOD == '2') {
                BPCFFTXI.TX_DATA.DIS_AC_INFO.DIS_AC = BPRFSCH.CHARGE_AC;
            }
            BPCFFTXI.TX_DATA.DIS_AC_INFO.DIS_CCY = BPRFSCH.CHARGE_CCY;
        }
        BPCFFTXI.TX_DATA.FEE_CTRT_NO = BPRFSCH.KEY.CTRT_NO;
        BPCFFTXI.TX_DATA.REL_CTRT_NO = BPRFSCH.REL_CTRT_NO;
        BPCFFTXI.TX_DATA.LAST_FLAG = 'Y';
        CEP.TRC(SCCGWA, BPB1107_AWA_1107.CI_NO);
        BPCFFTXI.TX_DATA.CI_NO = BPB1107_AWA_1107.CI_NO;
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CI_NO);
        IBS.init(SCCGWA, BPRFSCHD);
        IBS.init(SCCGWA, BPCRSCHD);
        BPRFSCHD.KEY.CTRT_NO = BPRFSCH.KEY.CTRT_NO;
        BPRFSCHD.KEY.SETTLE_DATE = WS_SETTLE_DATE;
        BPCRSCHD.INFO.FUNC = 'B';
        BPCRSCHD.INFO.OPT = 'B';
        S000_CALL_BPZRSCHD();
        if (BPCRSCHD.RETURN_INFO == 'F') {
            BPCFFTXI.TX_DATA.LAST_FLAG = 'N';
        }
        S000_CALL_BPZFFTXI();
    }
    public void R000_02_SET_FFCON() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFCON);
        BPCFFCON.FEE_INFO.FEE_CNT += 1;
        WS_CNT = BPCFFCON.FEE_INFO.FEE_CNT;
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].CHG_FLG = '0';
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].FEE_CODE = BPRFSCH.FEE_TYPE;
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].FEE_CCY = BPB1107_AWA_1107.CHG_CCY;
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].FEE_BAS = WS_SET_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].FEE_AMT = WS_SET_AMT;
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].FEE_AMT);
        CEP.TRC(SCCGWA, BPRFSCH.CHARGE_METHOD);
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].CHG_AC_TY = BPRFSCH.CHARGE_METHOD;
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].CHG_AC_TY);
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].CHG_AC = BPRFSCH.CHARGE_AC;
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].CHG_CCY = BPB1107_AWA_1107.CHG_CCY;
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].CHG_BAS = WS_SET_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].CHG_AMT = WS_SET_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].ADJ_AMT = WS_SET_AMT;
        BPCFFCON.FEE_INFO.REMARK = BPB1107_AWA_1107.RMK;
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].CHG_CCY);
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].CHG_AMT);
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].CHG_BAS);
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].ADJ_AMT);
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.TICKET_NO);
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.EX_RATE);
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.REMARK);
        BPCFFCON.FEE_INFO.ACCOUNT_BR = BPRFSCH.BOOK_CENTRE;
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].TO_ACCT_CEN = BPRFSCH.BOOK_CENTRE;
        S000_CALL_BPZFFCON();
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_CNT);
    }
    public void B050_01_SET_FFTXI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = BPRFSCH.CHARGE_METHOD;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = BPB1107_AWA_1107.FEE_AC;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = BPB1107_AWA_1107.CHG_CCY;
        BPCFFTXI.TX_DATA.SVR_CD = SCCGWA.COMM_AREA.SERV_CODE;
        BPCFFTXI.TX_DATA.CI_NO = BPB1107_AWA_1107.CI_NO;
        BPCFFTXI.TX_DATA.CCY_TYPE = BPB1107_AWA_1107.CCY_TYPE;
        S000_CALL_BPZFFTXI();
    }
    public void B050_02_SET_FFCON() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFCON);
        BPCFFCON.FEE_INFO.FEE_CNT += 1;
        WS_CNT = BPCFFCON.FEE_INFO.FEE_CNT;
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].CHG_FLG = '0';
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].FEE_CODE = BPB1107_AWA_1107.FEE_CODE;
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].FEE_CCY = BPB1107_AWA_1107.CHG_CCY;
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].FEE_BAS = BPB1107_AWA_1107.CHG_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].FEE_AMT = BPB1107_AWA_1107.CHG_AMT;
        BPCFFCON.FEE_INFO.ACCOUNT_BR = BPRFSCH.BOOK_CENTRE;
        BPCFFCON.FEE_INFO.REMARK = BPB1107_AWA_1107.RMK;
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].CHG_AC_TY = BPRFSCH.CHARGE_METHOD;
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].CHG_AC = BPB1107_AWA_1107.FEE_AC;
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].CHG_CCY = BPB1107_AWA_1107.CHG_CCY;
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].CHG_BAS = BPB1107_AWA_1107.CHG_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].CHG_AMT = BPB1107_AWA_1107.CHG_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].ADJ_AMT = BPB1107_AWA_1107.CHG_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].AMO_FLG = BPB1107_AWA_1107.AMO_FLG;
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].AMO_STDT = BPB1107_AWA_1107.AMO_STDT;
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].AMO_EDDT = BPB1107_AWA_1107.AMO_EDDT;
        S000_CALL_BPZFFCON();
    }
    public void B090_OUTPUT_INFO() throws IOException,SQLException,Exception {
        WS_OUT_DATA.WS_OUT_CTRT_NO = BPB1107_AWA_1107.FEE_CTRT;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUT_DATA;
        SCCFMT.DATA_LEN = 73;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZRFSCH() throws IOException,SQLException,Exception {
        BPCRFSCH.INFO.POINTER = BPRFSCH;
        BPCRFSCH.INFO.REC_LEN = 816;
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-FEESCH", BPCRFSCH);
        if (BPCRFSCH.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRFSCH.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFFTXI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_F_TX_INFO, BPCFFTXI);
        if (BPCFFTXI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFTXI.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFFCON() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_F_CON_CHG_INFO, BPCFFCON);
        if (BPCFFCON.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFCON.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRSCHD() throws IOException,SQLException,Exception {
        BPCRSCHD.INFO.POINTER = BPRFSCHD;
        BPCRSCHD.INFO.REC_LEN = 376;
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-FEESCHD", BPCRSCHD);
        if (BPCRSCHD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRSCHD.RC);
            S000_ERR_MSG_PROC();
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
    public void S000_CALL_BPZUFEEC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-FEE-CAL", BPCUFEEC);
        if (BPCUFEEC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUFEEC.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRACCR() throws IOException,SQLException,Exception {
        BPCRACCR.INFO.POINTER = BPRPACCR;
        BPCRACCR.INFO.REC_LEN = 192;
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-ACCR", BPCRACCR);
        CEP.TRC(SCCGWA, BPCRACCR.RC);
        if (BPCRACCR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRACCR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
