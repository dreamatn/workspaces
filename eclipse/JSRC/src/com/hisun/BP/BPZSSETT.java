package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.VT.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSSETT {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    brParm BPTFEHIS_BR = new brParm();
    boolean pgmRtn = false;
    String K_OUTPUT_FMT_9 = "BP074";
    String K_HIS_REMARK = "FEE DETAIL SCHEDULE SETTLE";
    String K_HIS_COPYBOOK = "BPRFSCHD";
    String K_FEE_CODE = "FII01";
    String WS_ERR_MSG = " ";
    short WS_IDX = 0;
    short WS_CNT = 0;
    double WS_TMP_AMT = 0;
    double WS_AMO_AMT = 0;
    double WS_ACCR_AMT = 0;
    double WS_FSCHD_ADJ_AMT = 0;
    char WS_FSCHD_DEL_FLG = ' ';
    char WS_FOUND_FLAG = ' ';
    char WS_LAST_SCHD_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRFSCH BPRFSCH = new BPRFSCH();
    BPRFSCHD BPRFSCHD = new BPRFSCHD();
    BPCRFSCH BPCRFSCH = new BPCRFSCH();
    BPCSSET1 BPCSSET1 = new BPCSSET1();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    BPCUCNGM BPCUCNGM = new BPCUCNGM();
    BPCPQBCH BPCPQBCH = new BPCPQBCH();
    BPCRSCHD BPCRSCHD = new BPCRSCHD();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPCFFCON BPCFFCON = new BPCFFCON();
    BPCUMENT BPCUMENT = new BPCUMENT();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    BPRPACCR BPRPACCR = new BPRPACCR();
    BPCRACCR BPCRACCR = new BPCRACCR();
    BPCSFECT BPCSFECT = new BPCSFECT();
    BPCSFSCH BPCSFSCH = new BPCSFSCH();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    VTCPQTAX VTCPQTAX = new VTCPQTAX();
    BPRFEHIS BPRFEHIS = new BPRFEHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCSSETT BPCSSETT;
    BPCGCFEE BPCGCFEE;
    public void MP(SCCGWA SCCGWA, BPCSSETT BPCSSETT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSSETT = BPCSSETT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSSETT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCGCFEE = new BPCGCFEE();
        IBS.init(SCCGWA, BPCGCFEE);
        IBS.CPY2CLS(SCCGWA, GWA_BP_AREA.FEE_AREA.FEE_DATA_PTR, BPCGCFEE);
        IBS.init(SCCGWA, BPRFSCH);
        IBS.init(SCCGWA, BPRFSCHD);
        IBS.init(SCCGWA, BPCRFSCH);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSSETT.KEY.CTRT_NO);
        CEP.TRC(SCCGWA, BPCSSETT.KEY.SETTLE_DATE);
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B001_CHECK_PROCESS_CN();
            if (pgmRtn) return;
        } else {
            B001_CHECK_PROCESS();
            if (pgmRtn) return;
        }
        if (BPCSSETT.FUNC == 'S') {
            if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
                CEP.TRC(SCCGWA, BPRFSCH.UCT_FLG);
                if (BPRFSCH.UCT_FLG == 'Y') {
                    B010_SETTTL_UCT_PROCESS();
                    if (pgmRtn) return;
                } else {
                    B010_SETTL_PROCESS_CN();
                    if (pgmRtn) return;
                }
            } else {
                B010_SETTL_PROCESS();
                if (pgmRtn) return;
            }
        } else if (BPCSSETT.FUNC == 'R') {
            B020_REVER_PROCESS_NCB();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B090_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B001_CHECK_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFSCH);
        IBS.init(SCCGWA, BPCRFSCH);
        BPRFSCH.KEY.CTRT_NO = BPCSSETT.KEY.CTRT_NO;
        BPCRFSCH.INFO.FUNC = 'Q';
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
    }
    public void B001_CHECK_PROCESS_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFSCH);
        IBS.init(SCCGWA, BPCRFSCH);
        BPRFSCH.KEY.CTRT_NO = BPCSSETT.KEY.CTRT_NO;
        BPCRFSCH.INFO.FUNC = 'Q';
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
        CEP.TRC(SCCGWA, BPCSSETT.FUNC);
        CEP.TRC(SCCGWA, BPRFSCH.FEE_STATUS);
        if (BPRFSCH.FEE_STATUS == '0' 
            && BPCSSETT.FUNC == 'S') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FSCH_NOT_EFFECT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_SETTL_PROCESS() throws IOException,SQLException,Exception {
        WS_LAST_SCHD_FLAG = 'N';
        IBS.init(SCCGWA, BPRFSCHD);
        IBS.init(SCCGWA, BPCRSCHD);
        BPRFSCHD.KEY.CTRT_NO = BPCSSETT.KEY.CTRT_NO;
        BPRFSCHD.KEY.SETTLE_DATE = BPCSSETT.KEY.SETTLE_DATE;
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
        IBS.init(SCCGWA, BPRFSCHD);
        IBS.init(SCCGWA, BPCRSCHD);
        BPRFSCHD.KEY.CTRT_NO = BPCSSETT.KEY.CTRT_NO;
        BPRFSCHD.KEY.SETTLE_DATE = BPCSSETT.KEY.SETTLE_DATE;
        BPCRSCHD.INFO.FUNC = 'R';
        S000_CALL_BPZRSCHD();
        if (pgmRtn) return;
        if (BPCRSCHD.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SCHD_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPRFSCHD.STS != 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SCHD_HAS_SETT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPRFSCHD.STS = 'C';
        BPRFSCHD.CHARGE_AMT = BPCSSETT.INF.CHARGE_AMT - BPRFSCHD.ADJ_AMT;
        BPRFSCHD.REAL_AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRFSCHD.JNL_NO = SCCGWA.COMM_AREA.JRN_NO;
        BPRFSCHD.VOUCHER_NO = SCCGWA.COMM_AREA.VCH_NO;
        BPRFSCHD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRFSCHD.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
        BPRFSCHD.CHG_AMT_REAL = BPCSSETT.INF.AMT_REAL;
        BPRFSCHD.CHG_CCY_REAL = BPCSSETT.INF.CCY_REAL;
        BPRFSCHD.RATE = BPCSSETT.INF.RATE;
        CEP.TRC(SCCGWA, BPRFSCH.FEE_TYPE);
        CEP.TRC(SCCGWA, BPRFSCHD.CHARGE_AMT);
        CEP.TRC(SCCGWA, BPRFSCHD.REAL_AC_DATE);
        CEP.TRC(SCCGWA, BPRFSCHD.JNL_NO);
        CEP.TRC(SCCGWA, BPRFSCHD.VOUCHER_NO);
        BPCRSCHD.INFO.FUNC = 'U';
        S000_CALL_BPZRSCHD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSSETT.INF.CHARGE_AMT);
        if (BPCSSETT.INF.CHARGE_AMT != 0) {
            B010_01_SET_FFTXI();
            if (pgmRtn) return;
            B010_02_SET_FFCON();
            if (pgmRtn) return;
        }
        if (!BPRFSCH.FEE_TYPE.equalsIgnoreCase(K_FEE_CODE)) {
            IBS.init(SCCGWA, BPRPACCR);
            IBS.init(SCCGWA, BPCRACCR);
            BPRPACCR.KEY.CTRT_NO = BPCSSETT.KEY.CTRT_NO;
            BPCRACCR.INFO.FUNC = 'R';
            S000_CALL_BPZRACCR();
            if (pgmRtn) return;
            if (BPCRACCR.RETURN_INFO == 'F') {
                BPRPACCR.LAST_SETTLE_DATE = BPRPACCR.SETTLE_DATE;
                BPRPACCR.SETTLE_DATE = BPCSSETT.KEY.SETTLE_DATE;
                WS_TMP_AMT = BPRPACCR.SETTLE_AMT_TOTAL;
                WS_TMP_AMT = WS_TMP_AMT + BPCSSETT.INF.CHARGE_AMT;
                CEP.TRC(SCCGWA, WS_TMP_AMT);
                BPRPACCR.SETTLE_AMT_TOTAL = WS_TMP_AMT;
                WS_TMP_AMT = BPRPACCR.ACCRUAL_AMT_TOTAL;
                WS_TMP_AMT = WS_TMP_AMT - BPCSSETT.INF.CHARGE_AMT;
                CEP.TRC(SCCGWA, WS_TMP_AMT);
                BPRPACCR.ACCRUAL_AMT_TOTAL = WS_TMP_AMT;
                BPRPACCR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                BPRPACCR.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
                BPRPACCR.SUP_TEL1 = SCCGWA.COMM_AREA.SUP1_ID;
                BPRPACCR.SUP_TEL2 = SCCGWA.COMM_AREA.SUP2_ID;
                BPCRACCR.INFO.FUNC = 'U';
                S000_CALL_BPZRACCR();
                if (pgmRtn) return;
            } else {
                IBS.init(SCCGWA, BPRPACCR);
                IBS.init(SCCGWA, BPCRACCR);
                BPRPACCR.KEY.CTRT_NO = BPCSSETT.KEY.CTRT_NO;
                BPRPACCR.SETTLE_DATE = BPCSSETT.KEY.SETTLE_DATE;
                BPRPACCR.SETTLE_AMT_TOTAL = BPCSSETT.INF.CHARGE_AMT;
                WS_TMP_AMT = 0;
                WS_TMP_AMT = WS_TMP_AMT - BPCSSETT.INF.CHARGE_AMT;
                BPRPACCR.ACCRUAL_AMT_TOTAL = WS_TMP_AMT;
                BPRPACCR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                BPRPACCR.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
                BPRPACCR.SUP_TEL1 = SCCGWA.COMM_AREA.SUP1_ID;
                BPRPACCR.SUP_TEL2 = SCCGWA.COMM_AREA.SUP2_ID;
                BPCRACCR.INFO.FUNC = 'C';
                S000_CALL_BPZRACCR();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, BPRFSCHD);
        IBS.init(SCCGWA, BPCRSCHD);
        BPRFSCHD.KEY.CTRT_NO = BPCSSETT.KEY.CTRT_NO;
        BPRFSCHD.KEY.SETTLE_DATE = BPCSSETT.KEY.SETTLE_DATE;
        BPCRSCHD.INFO.FUNC = 'B';
        BPCRSCHD.INFO.OPT = 'B';
        S000_CALL_BPZRSCHD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRFSCHD.STS);
        if (BPCRSCHD.RETURN_INFO == 'N') {
            WS_LAST_SCHD_FLAG = 'Y';
        }
        if (WS_LAST_SCHD_FLAG == 'Y' 
            && BPRFSCH.MATURITY_DATE <= SCCGWA.COMM_AREA.AC_DATE) {
            IBS.init(SCCGWA, BPRFSCH);
            IBS.init(SCCGWA, BPCRFSCH);
            BPRFSCH.KEY.CTRT_NO = BPCSSETT.KEY.CTRT_NO;
            BPCRFSCH.INFO.FUNC = 'R';
            S000_CALL_BPZRFSCH();
            if (pgmRtn) return;
            BPRFSCH.FEE_STATUS = '2';
            BPRFSCH.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCRFSCH.INFO.FUNC = 'U';
            S000_CALL_BPZRFSCH();
            if (pgmRtn) return;
        }
    }
    public void B010_SETTL_PROCESS_CN() throws IOException,SQLException,Exception {
        WS_LAST_SCHD_FLAG = 'N';
        IBS.init(SCCGWA, BPRFSCHD);
        IBS.init(SCCGWA, BPCRSCHD);
        BPRFSCHD.KEY.CTRT_NO = BPCSSETT.KEY.CTRT_NO;
        BPRFSCHD.KEY.SETTLE_DATE = BPCSSETT.KEY.SETTLE_DATE;
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
        IBS.init(SCCGWA, BPRFSCHD);
        IBS.init(SCCGWA, BPCRSCHD);
        BPRFSCHD.KEY.CTRT_NO = BPCSSETT.KEY.CTRT_NO;
        BPRFSCHD.KEY.SETTLE_DATE = BPCSSETT.KEY.SETTLE_DATE;
        BPCRSCHD.INFO.FUNC = 'R';
        S000_CALL_BPZRSCHD();
        if (pgmRtn) return;
        if (BPCRSCHD.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SCHD_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPRFSCHD.STS != 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SCHD_HAS_SETT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPRFSCHD.STS = 'C';
        BPRFSCHD.CHARGE_AMT = BPCSSETT.INF.CHARGE_AMT - BPRFSCHD.ADJ_AMT;
        BPRFSCHD.REAL_AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRFSCHD.JNL_NO = SCCGWA.COMM_AREA.JRN_NO;
        BPRFSCHD.VOUCHER_NO = SCCGWA.COMM_AREA.VCH_NO;
        BPRFSCHD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRFSCHD.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
        BPRFSCHD.CHG_AMT_REAL = BPCSSETT.INF.AMT_REAL;
        BPRFSCHD.CHG_CCY_REAL = BPCSSETT.INF.CCY_REAL;
        BPRFSCHD.RATE = BPCSSETT.INF.RATE;
        CEP.TRC(SCCGWA, BPRFSCHD.CHG_AMT_REAL);
        CEP.TRC(SCCGWA, BPRFSCHD.CHG_CCY_REAL);
        CEP.TRC(SCCGWA, BPRFSCHD.CHARGE_AMT);
        CEP.TRC(SCCGWA, BPRFSCHD.REAL_AC_DATE);
        CEP.TRC(SCCGWA, BPRFSCHD.JNL_NO);
        CEP.TRC(SCCGWA, BPRFSCHD.VOUCHER_NO);
        BPCRSCHD.INFO.FUNC = 'U';
        S000_CALL_BPZRSCHD();
        if (pgmRtn) return;
        if (BPRFSCHD.ADJ_AMT != 0) {
            IBS.init(SCCGWA, BPCSFSCH);
            BPCSFSCH.KEY.CTRT_NO = BPRFSCHD.KEY.CTRT_NO;
            BPCSFSCH.FUNC = 'Q';
            S000_CALL_BPZSFSCH();
            if (pgmRtn) return;
            IBS.init(SCCGWA, BPCPOEWA);
            BPCPOEWA.DATA.PROD_CODE = BPRFSCH.FEE_TYPE;
            BPCPOEWA.DATA.PROD_CODE_REL = BPRFSCH.PRD_TYPE;
            BPCPOEWA.DATA.CNTR_TYPE = "FEES";
            BPCPOEWA.DATA.BR_OLD = BPRFSCH.BOOK_CENTRE;
            BPCPOEWA.DATA.BR_NEW = BPRFSCH.BOOK_CENTRE;
            BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPRFSCHD.CHARGE_CCY;
            BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCPOEWA.DATA.DESC = BPRFSCH.REMARK;
            IBS.init(SCCGWA, VTCPQTAX);
            VTCPQTAX.INPUT_DATA.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
            VTCPQTAX.INPUT_DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
            VTCPQTAX.INPUT_DATA.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            VTCPQTAX.INPUT_DATA.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            VTCPQTAX.INPUT_DATA.BR = BPRFSCH.BOOK_CENTRE;
            VTCPQTAX.INPUT_DATA.PROD_CD = BPRFSCH.FEE_TYPE;
            VTCPQTAX.INPUT_DATA.PROD_CD_REL = BPRFSCH.PRD_TYPE;
            VTCPQTAX.INPUT_DATA.CI_NO = BPRFSCH.CI_NO;
            VTCPQTAX.INPUT_DATA.CCY = BPRFSCHD.CHARGE_CCY;
            VTCPQTAX.INPUT_DATA.YJ_AMT = BPRFSCHD.ADJ_AMT;
            S000_CALL_VTZPQTAX();
            if (pgmRtn) return;
            BPCPOEWA.DATA.EVENT_CODE = "DY";
            if (BPRFSCHD.ADJ_AMT > 0) {
                BPCPOEWA.DATA.AMT_INFO[05-1].AMT = BPRFSCHD.ADJ_AMT;
            } else {
                BPCPOEWA.DATA.AMT_INFO[06-1].AMT = 0 - BPRFSCHD.ADJ_AMT;
            }
            if (VTCPQTAX.OUTPUT_DATA.TAX_AMT > 0) {
                BPCPOEWA.DATA.AMT_INFO[07-1].AMT = VTCPQTAX.OUTPUT_DATA.TAX_AMT;
            } else {
                BPCPOEWA.DATA.AMT_INFO[08-1].AMT = 0 - VTCPQTAX.OUTPUT_DATA.TAX_AMT;
            }
            BPCPOEWA.DATA.CI_NO = BPRFSCH.CI_NO;
            BPCPOEWA.DATA.THEIR_AC = BPRFSCH.CHARGE_AC;
            S000_CALL_BPZPOEWA();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCSSETT.INF.CHARGE_AMT);
        if (BPCSSETT.INF.CHARGE_AMT != 0) {
            B010_01_SET_FFTXI_CN();
            if (pgmRtn) return;
            B010_02_SET_FFCON_CN();
            if (pgmRtn) return;
        }
        if (!BPRFSCH.FEE_TYPE.equalsIgnoreCase(K_FEE_CODE)) {
            IBS.init(SCCGWA, BPRPACCR);
            IBS.init(SCCGWA, BPCRACCR);
            BPRPACCR.KEY.CTRT_NO = BPCSSETT.KEY.CTRT_NO;
            BPCRACCR.INFO.FUNC = 'R';
            S000_CALL_BPZRACCR();
            if (pgmRtn) return;
            if (BPCRACCR.RETURN_INFO == 'F') {
                BPRPACCR.LAST_SETTLE_DATE = BPRPACCR.SETTLE_DATE;
                BPRPACCR.SETTLE_DATE = BPCSSETT.KEY.SETTLE_DATE;
                CEP.TRC(SCCGWA, BPRPACCR.SETTLE_AMT_TOTAL);
                WS_TMP_AMT = BPRPACCR.SETTLE_AMT_TOTAL;
                CEP.TRC(SCCGWA, BPCSSETT.INF.CHARGE_AMT);
                WS_TMP_AMT = WS_TMP_AMT + BPCSSETT.INF.CHARGE_AMT;
                CEP.TRC(SCCGWA, WS_TMP_AMT);
                BPRPACCR.SETTLE_AMT_TOTAL = WS_TMP_AMT;
                CEP.TRC(SCCGWA, BPRPACCR.SETTLE_AMT_TOTAL);
                CEP.TRC(SCCGWA, BPRPACCR.ACCRUAL_AMT_TOTAL);
                WS_TMP_AMT = BPRPACCR.ACCRUAL_AMT_TOTAL;
                WS_TMP_AMT = WS_TMP_AMT - BPCSSETT.INF.CHARGE_AMT;
                IBS.init(SCCGWA, BPRFSCHD);
                IBS.init(SCCGWA, BPCRSCHD);
                BPRFSCHD.KEY.CTRT_NO = BPCSSETT.KEY.CTRT_NO;
                BPRFSCHD.KEY.SETTLE_DATE = BPCSSETT.KEY.SETTLE_DATE;
                BPCRSCHD.INFO.FUNC = 'Q';
                S000_CALL_BPZRSCHD();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPRFSCHD.ADJ_AMT);
                WS_TMP_AMT = WS_TMP_AMT + BPRFSCHD.ADJ_AMT;
                CEP.TRC(SCCGWA, WS_TMP_AMT);
                BPRPACCR.ACCRUAL_AMT_TOTAL = WS_TMP_AMT;
                BPRPACCR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                BPRPACCR.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
                BPRPACCR.SUP_TEL1 = SCCGWA.COMM_AREA.SUP1_ID;
                BPRPACCR.SUP_TEL2 = SCCGWA.COMM_AREA.SUP2_ID;
                BPCRACCR.INFO.FUNC = 'U';
                S000_CALL_BPZRACCR();
                if (pgmRtn) return;
            } else {
                IBS.init(SCCGWA, BPRPACCR);
                IBS.init(SCCGWA, BPCRACCR);
                BPRPACCR.KEY.CTRT_NO = BPCSSETT.KEY.CTRT_NO;
                BPRPACCR.SETTLE_DATE = BPCSSETT.KEY.SETTLE_DATE;
                BPRPACCR.SETTLE_AMT_TOTAL = BPCSSETT.INF.CHARGE_AMT;
                WS_TMP_AMT = 0;
                CEP.TRC(SCCGWA, BPCSSETT.INF.CHARGE_AMT);
                WS_TMP_AMT = WS_TMP_AMT - BPCSSETT.INF.CHARGE_AMT;
                CEP.TRC(SCCGWA, WS_TMP_AMT);
                IBS.init(SCCGWA, BPRFSCHD);
                IBS.init(SCCGWA, BPCRSCHD);
                BPRFSCHD.KEY.CTRT_NO = BPCSSETT.KEY.CTRT_NO;
                BPRFSCHD.KEY.SETTLE_DATE = BPCSSETT.KEY.SETTLE_DATE;
                BPCRSCHD.INFO.FUNC = 'Q';
                S000_CALL_BPZRSCHD();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPRFSCHD.ADJ_AMT);
                WS_TMP_AMT = WS_TMP_AMT + BPRFSCHD.ADJ_AMT;
                CEP.TRC(SCCGWA, WS_TMP_AMT);
                BPRPACCR.ACCRUAL_AMT_TOTAL = WS_TMP_AMT;
                BPRPACCR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                BPRPACCR.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
                BPRPACCR.SUP_TEL1 = SCCGWA.COMM_AREA.SUP1_ID;
                BPRPACCR.SUP_TEL2 = SCCGWA.COMM_AREA.SUP2_ID;
                BPCRACCR.INFO.FUNC = 'C';
                S000_CALL_BPZRACCR();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, BPRFSCHD);
        IBS.init(SCCGWA, BPCRSCHD);
        BPRFSCHD.KEY.CTRT_NO = BPCSSETT.KEY.CTRT_NO;
        BPRFSCHD.KEY.SETTLE_DATE = BPCSSETT.KEY.SETTLE_DATE;
        BPCRSCHD.INFO.FUNC = 'B';
        BPCRSCHD.INFO.OPT = 'B';
        S000_CALL_BPZRSCHD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRFSCHD.STS);
        if (BPCRSCHD.RETURN_INFO == 'N') {
            WS_LAST_SCHD_FLAG = 'Y';
        }
        if (WS_LAST_SCHD_FLAG == 'Y') {
            IBS.init(SCCGWA, BPRFSCH);
            IBS.init(SCCGWA, BPCRFSCH);
            BPRFSCH.KEY.CTRT_NO = BPCSSETT.KEY.CTRT_NO;
            BPCRFSCH.INFO.FUNC = 'R';
            S000_CALL_BPZRFSCH();
            if (pgmRtn) return;
            BPRFSCH.FEE_STATUS = '2';
            BPRFSCH.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCRFSCH.INFO.FUNC = 'U';
            S000_CALL_BPZRFSCH();
            if (pgmRtn) return;
        }
    }
    public void B010_SETTTL_UCT_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSSETT.INF.CHARGE_AMT);
        IBS.init(SCCGWA, BPRFSCHD);
        IBS.init(SCCGWA, BPCRSCHD);
        BPRFSCHD.KEY.CTRT_NO = BPCSSETT.KEY.CTRT_NO;
        if (SCCGWA.COMM_AREA.AC_DATE > BPRFSCH.MATURITY_DATE) {
            BPRFSCHD.KEY.SETTLE_DATE = BPRFSCH.MATURITY_DATE;
        } else {
            BPRFSCHD.KEY.SETTLE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        BPCRSCHD.INFO.FUNC = 'R';
        S000_CALL_BPZRSCHD();
        if (pgmRtn) return;
        if (BPCRSCHD.RETURN_INFO == 'N') {
            CEP.TRC(SCCGWA, "CREATE NEW FSCHD");
            IBS.init(SCCGWA, BPRFSCHD);
            IBS.init(SCCGWA, BPCRSCHD);
            BPRFSCHD.KEY.CTRT_NO = BPCSSETT.KEY.CTRT_NO;
            BPRFSCHD.KEY.SETTLE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPRFSCHD.CHARGE_CCY = BPCSSETT.INF.CHARGE_CCY;
            BPRFSCHD.CHARGE_AMT = BPCSSETT.INF.CHARGE_AMT;
            BPRFSCHD.STS = 'C';
            BPRFSCHD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPRFSCHD.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
            BPRFSCHD.SUP_TEL1 = SCCGWA.COMM_AREA.SUP1_ID;
            BPRFSCHD.SUP_TEL2 = SCCGWA.COMM_AREA.SUP2_ID;
            BPCRSCHD.INFO.FUNC = 'C';
            S000_CALL_BPZRSCHD();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "UPDATE FSCHD");
            BPRFSCHD.CHARGE_AMT = BPRFSCHD.CHARGE_AMT + BPCSSETT.INF.CHARGE_AMT;
            BPRFSCHD.STS = 'C';
            BPRFSCHD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPRFSCHD.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
            BPRFSCHD.SUP_TEL1 = SCCGWA.COMM_AREA.SUP1_ID;
            BPRFSCHD.SUP_TEL2 = SCCGWA.COMM_AREA.SUP2_ID;
            BPCRSCHD.INFO.FUNC = 'U';
            S000_CALL_BPZRSCHD();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPRPACCR);
        IBS.init(SCCGWA, BPCRACCR);
        BPRPACCR.KEY.CTRT_NO = BPCSSETT.KEY.CTRT_NO;
        BPCRACCR.INFO.FUNC = 'R';
        S000_CALL_BPZRACCR();
        if (pgmRtn) return;
        if (BPCRACCR.RETURN_INFO == 'N') {
            CEP.TRC(SCCGWA, "CREATE NEW PACCR");
            CEP.TRC(SCCGWA, BPRFSCH.CHARGE_AMT);
            CEP.TRC(SCCGWA, BPCSSETT.INF.CHARGE_AMT);
            if (BPCSSETT.INF.CHARGE_AMT > BPRFSCH.CHARGE_AMT) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SETTLE_AMT_OVERFLOW;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, BPRPACCR);
            IBS.init(SCCGWA, BPCRACCR);
            BPRPACCR.KEY.CTRT_NO = BPCSSETT.KEY.CTRT_NO;
            BPRPACCR.SETTLE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPRPACCR.SETTLE_AMT_TOTAL = BPCSSETT.INF.CHARGE_AMT;
            WS_AMO_AMT = BPCSSETT.INF.CHARGE_AMT;
            BPRPACCR.ACCRUAL_AMT_TOTAL = 0;
            BPRPACCR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPRPACCR.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
            BPRPACCR.SUP_TEL1 = SCCGWA.COMM_AREA.SUP1_ID;
            BPRPACCR.SUP_TEL2 = SCCGWA.COMM_AREA.SUP2_ID;
            BPCRACCR.INFO.FUNC = 'C';
            S000_CALL_BPZRACCR();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "UPDATE PACCR");
            CEP.TRC(SCCGWA, BPRFSCH.CHARGE_AMT);
            CEP.TRC(SCCGWA, BPCSSETT.INF.CHARGE_AMT);
            CEP.TRC(SCCGWA, BPRPACCR.SETTLE_AMT_TOTAL);
            BPRPACCR.SETTLE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPRPACCR.SETTLE_AMT_TOTAL = BPRPACCR.SETTLE_AMT_TOTAL + BPCSSETT.INF.CHARGE_AMT;
            CEP.TRC(SCCGWA, BPRPACCR.SETTLE_AMT_TOTAL);
            if (BPRPACCR.SETTLE_AMT_TOTAL != BPRFSCH.CHARGE_AMT 
                && SCCGWA.COMM_AREA.AC_DATE >= BPRFSCH.MATURITY_DATE) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_LAST_MUST_SETTLE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPRPACCR.SETTLE_AMT_TOTAL > BPRFSCH.CHARGE_AMT) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SETTLE_AMT_OVERFLOW;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCSSETT.INF.CHARGE_AMT < BPRPACCR.ACCRUAL_AMT_TOTAL) {
                BPRPACCR.ACCRUAL_AMT_TOTAL = BPRPACCR.ACCRUAL_AMT_TOTAL - BPCSSETT.INF.CHARGE_AMT;
                WS_AMO_AMT = 0;
            } else {
                WS_AMO_AMT = BPCSSETT.INF.CHARGE_AMT - BPRPACCR.ACCRUAL_AMT_TOTAL;
                BPRPACCR.ACCRUAL_AMT_TOTAL = 0;
            }
            BPRPACCR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPRPACCR.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
            BPRPACCR.SUP_TEL1 = SCCGWA.COMM_AREA.SUP1_ID;
            BPRPACCR.SUP_TEL2 = SCCGWA.COMM_AREA.SUP2_ID;
            BPCRACCR.INFO.FUNC = 'U';
            S000_CALL_BPZRACCR();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPRPACCR.SETTLE_AMT_TOTAL);
        if (BPRPACCR.SETTLE_AMT_TOTAL == BPRFSCH.CHARGE_AMT) {
            IBS.init(SCCGWA, BPRFSCHD);
            IBS.init(SCCGWA, BPCRSCHD);
            BPRFSCHD.KEY.CTRT_NO = BPCSSETT.KEY.CTRT_NO;
            BPRFSCHD.KEY.SETTLE_DATE = BPRFSCH.MATURITY_DATE;
            BPCRSCHD.INFO.FUNC = 'R';
            S000_CALL_BPZRSCHD();
            if (pgmRtn) return;
            BPRFSCHD.STS = 'C';
            BPRFSCHD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPRFSCHD.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
            BPRFSCHD.SUP_TEL1 = SCCGWA.COMM_AREA.SUP1_ID;
            BPRFSCHD.SUP_TEL2 = SCCGWA.COMM_AREA.SUP2_ID;
            BPCRSCHD.INFO.FUNC = 'U';
            S000_CALL_BPZRSCHD();
            if (pgmRtn) return;
            IBS.init(SCCGWA, BPRFSCH);
            IBS.init(SCCGWA, BPCRFSCH);
            BPRFSCH.KEY.CTRT_NO = BPCSSETT.KEY.CTRT_NO;
            BPCRFSCH.INFO.FUNC = 'R';
            S000_CALL_BPZRFSCH();
            if (pgmRtn) return;
            BPRFSCH.FEE_STATUS = '2';
            BPRFSCH.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCRFSCH.INFO.FUNC = 'U';
            S000_CALL_BPZRFSCH();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_AMO_AMT);
        if (WS_AMO_AMT > 0) {
            IBS.init(SCCGWA, BPCSFECT);
            BPCSFECT.FUNC_CODE = 'A';
            BPCSFECT.CINO = BPRFSCH.CI_NO;
            BPCSFECT.CHG_ACNO = BPRFSCH.CHARGE_AC;
            BPCSFECT.CT_TYP = "FEES";
            BPCSFECT.FEE_TYP = BPRFSCH.FEE_TYPE;
            BPCSFECT.BOOK_ACCT = BPRFSCH.BOOK_CENTRE;
            BPCSFECT.START_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPCSFECT.MATURITY_DT = BPRFSCH.MATURITY_DATE;
            BPCSFECT.HOL_OVR = 'Y';
            BPCSFECT.HOL_METH = 'N';
            BPCSFECT.CAL_CD1 = "CN";
            BPCSFECT.CAL_CD2 = "CN";
            BPCSFECT.PAY_IND = 'P';
            BPCSFECT.PAY_METH = 'P';
            BPCSFECT.INT_BASIC = "02";
            BPCSFECT.TXN_CCY = BPCSSETT.INF.CHARGE_CCY;
            BPCSFECT.TXN_AMT = BPCSSETT.INF.CHARGE_AMT;
            BPCSFECT.CHG_CCY = BPCSSETT.INF.CHARGE_CCY;
            BPCSFECT.CHG_CCY_REAL = BPCSSETT.INF.CHARGE_CCY;
            BPCSFECT.CHG_AMT = WS_AMO_AMT;
            BPCSFECT.CHG_AMT_REAL = WS_AMO_AMT;
            BPCSFECT.CT_STS = '1';
            BPCSFECT.CHG_FLG = 'Y';
            BPCSFECT.RELT_FLG = '0';
            BPCSFECT.ACU_TYP = 'F';
            BPCSFECT.FSCH_CTRT_NO = BPRFSCH.KEY.CTRT_NO;
            BPCSFECT.FSCH_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCSFECT.CHG_METH = BPRFSCH.CHARGE_METHOD;
            BPCSFECT.CCY_TYPE = BPRFSCH.CCY_TYPE;
            BPCSFECT.PRDT_TYP = BPRFSCH.PRD_TYPE;
            S000_CALL_BPZSFECT();
            if (pgmRtn) return;
            WS_ACCR_AMT = BPCSSETT.INF.CHARGE_AMT - WS_AMO_AMT;
            CEP.TRC(SCCGWA, WS_ACCR_AMT);
        }
        B010_01_SET_FFTXI_CN();
        if (pgmRtn) return;
        B010_02_SET_FFCON_CN();
        if (pgmRtn) return;
        Z_RET();
        if (pgmRtn) return;
    }
    public void B010_01_SET_FFTXI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        if (BPRFSCH.PAY_IND == 'R') {
            BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = BPCSSETT.KEY.SETTLE_DATE;
            CEP.TRC(SCCGWA, BPRFSCH.CHARGE_METHOD);
            BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = BPRFSCH.CHARGE_METHOD;
            if (BPRFSCH.CHARGE_METHOD == '0' 
                || BPRFSCH.CHARGE_METHOD == '1' 
                || BPRFSCH.CHARGE_METHOD == '2') {
                BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = BPRFSCH.CHARGE_AC;
            } else {
                if (BPRFSCH.CHARGE_METHOD == '3') {
                }
            }
            BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = BPRFSCH.CHARGE_CCY;
        } else {
            BPCFFTXI.TX_DATA.DIS_AC_INFO.DIS_VAL_DATE = BPCSSETT.KEY.SETTLE_DATE;
            BPCFFTXI.TX_DATA.DIS_AC_INFO.DIS_AC_TYP = BPRFSCH.CHARGE_METHOD;
            if (BPRFSCH.CHARGE_METHOD == '0' 
                || BPRFSCH.CHARGE_METHOD == '1' 
                || BPRFSCH.CHARGE_METHOD == '2') {
                BPCFFTXI.TX_DATA.DIS_AC_INFO.DIS_AC = BPRFSCH.CHARGE_AC;
            } else {
                if (BPRFSCH.CHARGE_METHOD == '3') {
                }
            }
            BPCFFTXI.TX_DATA.DIS_AC_INFO.DIS_CCY = BPRFSCH.CHARGE_CCY;
        }
        BPCFFTXI.TX_DATA.FEE_CTRT_NO = BPCSSETT.KEY.CTRT_NO;
        BPCFFTXI.TX_DATA.REL_CTRT_NO = BPRFSCH.REL_CTRT_NO;
        BPCFFTXI.TX_DATA.LAST_FLAG = 'Y';
        CEP.TRC(SCCGWA, BPCSSETT.INF.CI_NO);
        BPCFFTXI.TX_DATA.CI_NO = BPCSSETT.INF.CI_NO;
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CI_NO);
        IBS.init(SCCGWA, BPRFSCHD);
        IBS.init(SCCGWA, BPCRSCHD);
        BPRFSCHD.KEY.CTRT_NO = BPCSSETT.KEY.CTRT_NO;
        BPRFSCHD.KEY.SETTLE_DATE = BPCSSETT.KEY.SETTLE_DATE;
        BPCRSCHD.INFO.FUNC = 'B';
        BPCRSCHD.INFO.OPT = 'B';
        S000_CALL_BPZRSCHD();
        if (pgmRtn) return;
        if (BPCRSCHD.RETURN_INFO == 'F') {
            BPCFFTXI.TX_DATA.LAST_FLAG = 'N';
        }
        S000_CALL_BPZFFTXI();
        if (pgmRtn) return;
    }
    public void B010_01_SET_FFTXI_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        if (BPRFSCH.PAY_IND == 'R') {
            BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = BPCSSETT.KEY.SETTLE_DATE;
            BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = BPRFSCH.CHARGE_METHOD;
            BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = BPRFSCH.CHARGE_AC;
            BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = BPRFSCH.CHARGE_CCY;
            BPCFFTXI.TX_DATA.CCY_TYPE = BPRFSCH.CCY_TYPE;
        } else {
            BPCFFTXI.TX_DATA.DIS_AC_INFO.DIS_VAL_DATE = BPCSSETT.KEY.SETTLE_DATE;
            BPCFFTXI.TX_DATA.DIS_AC_INFO.DIS_AC_TYP = BPRFSCH.CHARGE_METHOD;
            BPCFFTXI.TX_DATA.DIS_AC_INFO.DIS_AC = BPRFSCH.CHARGE_AC;
            BPCFFTXI.TX_DATA.DIS_AC_INFO.DIS_CCY = BPRFSCH.CHARGE_CCY;
            BPCFFTXI.TX_DATA.CCY_TYPE = BPRFSCH.CCY_TYPE;
        }
        if (BPRFSCH.CHARGE_METHOD == '4' 
            || BPRFSCH.CHARGE_METHOD == '5') {
            BPCFFTXI.TX_DATA.CARD_PSBK_NO = BPRFSCH.CHARGE_AC;
        }
        BPCFFTXI.TX_DATA.FEE_CTRT_NO = BPCSSETT.KEY.CTRT_NO;
        BPCFFTXI.TX_DATA.BSNS_NO = BPRFSCH.REL_CTRT_NO;
        BPCFFTXI.TX_DATA.LAST_FLAG = 'Y';
        BPCFFTXI.TX_DATA.CI_NO = BPCSSETT.INF.CI_NO;
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CI_NO);
        CEP.TRC(SCCGWA, BPCSSETT.KEY.CTRT_NO);
        S000_CALL_BPZFFTXI();
        if (pgmRtn) return;
    }
    public void B010_02_SET_FFCON() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFCON);
        BPCFFCON.FEE_INFO.FEE_CNT += 1;
        WS_CNT = BPCFFCON.FEE_INFO.FEE_CNT;
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].CHG_FLG = '0';
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].FEE_CODE = BPRFSCH.FEE_TYPE;
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].FEE_CCY = BPCSSETT.INF.CHARGE_CCY;
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].FEE_BAS = BPCSSETT.INF.CHARGE_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].FEE_AMT = BPCSSETT.INF.CHARGE_AMT;
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].FEE_AMT);
        CEP.TRC(SCCGWA, BPRFSCH.CHARGE_METHOD);
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].CHG_AC_TY = BPRFSCH.CHARGE_METHOD;
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].CHG_AC_TY);
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].CHG_AC = BPRFSCH.CHARGE_AC;
        if (BPRFSCH.CHARGE_METHOD == '3') {
        }
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].CHG_CCY = BPCSSETT.INF.CHARGE_CCY;
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].CHG_BAS = BPCSSETT.INF.CHARGE_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].CHG_AMT = BPCSSETT.INF.CHARGE_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].ADJ_AMT = BPCSSETT.INF.CHARGE_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].CHG_CCY = BPCSSETT.INF.CCY_REAL;
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].CHG_AMT = BPCSSETT.INF.AMT_REAL;
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].CHG_BAS = BPCSSETT.INF.AMT_REAL;
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].ADJ_AMT = BPCSSETT.INF.AMT_REAL;
        BPCFFCON.FEE_INFO.TICKET_NO = BPCSSETT.INF.TICKET;
        BPCFFCON.FEE_INFO.EX_RATE = BPCSSETT.INF.RATE;
        BPCFFCON.FEE_INFO.REMARK = BPCSSETT.INF.REMARK;
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].CHG_CCY);
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].CHG_AMT);
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].CHG_BAS);
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].ADJ_AMT);
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.TICKET_NO);
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.EX_RATE);
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.REMARK);
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].TO_ACCT_CEN = BPRFSCH.BOOK_CENTRE;
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].GL_MASTER1 = BPRFSCH.GL_MASTER1;
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].GL_MASTER2 = BPRFSCH.GL_MASTER2;
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].GL_MASTER3 = BPRFSCH.GL_MASTER3;
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].GL_MASTER4 = BPRFSCH.GL_MASTER4;
        S000_CALL_BPZFFCON();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_CNT);
    }
    public void B010_02_SET_FFCON_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFCON);
        BPCFFCON.FEE_INFO.FEE_CNT += 1;
        WS_CNT = BPCFFCON.FEE_INFO.FEE_CNT;
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].CHG_FLG = '0';
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].FEE_CODE = BPRFSCH.FEE_TYPE;
        if (BPCSSETT.INF.PRD_TYPE.trim().length() > 0) {
            BPCFFCON.FEE_INFO.PROD_CODE1 = BPCSSETT.INF.PRD_TYPE;
        } else {
            BPCFFCON.FEE_INFO.PROD_CODE1 = BPRFSCH.PRD_TYPE;
        }
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].FEE_CCY = BPCSSETT.INF.CHARGE_CCY;
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].FEE_AMT);
        CEP.TRC(SCCGWA, BPRFSCH.CHARGE_METHOD);
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].CHG_AC_TY = BPRFSCH.CHARGE_METHOD;
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].CHG_AC_TY);
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].CHG_AC = BPRFSCH.CHARGE_AC;
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].CHG_CCY = BPCSSETT.INF.CHARGE_CCY;
        if (WS_AMO_AMT > 0) {
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].CHG_AMT = WS_ACCR_AMT;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].ADJ_AMT = WS_ACCR_AMT;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].FEE_BAS = WS_ACCR_AMT;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].FEE_AMT = WS_ACCR_AMT;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].CHG_BAS = WS_ACCR_AMT;
        } else {
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].CHG_AMT = BPCSSETT.INF.CHARGE_AMT;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].ADJ_AMT = BPCSSETT.INF.CHARGE_AMT;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].FEE_BAS = BPCSSETT.INF.CHARGE_AMT;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].FEE_AMT = BPCSSETT.INF.CHARGE_AMT;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].CHG_BAS = BPCSSETT.INF.CHARGE_AMT;
        }
        BPCFFCON.FEE_INFO.TICKET_NO = BPCSSETT.INF.TICKET;
        BPCFFCON.FEE_INFO.EX_RATE = BPCSSETT.INF.RATE;
        BPCFFCON.FEE_INFO.REMARK = BPCSSETT.INF.REMARK;
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].CHG_CCY);
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].CHG_AMT);
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].CHG_BAS);
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].ADJ_AMT);
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.TICKET_NO);
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.EX_RATE);
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.REMARK);
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].TO_ACCT_CEN = BPRFSCH.BOOK_CENTRE;
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].GL_MASTER1 = BPRFSCH.GL_MASTER1;
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].GL_MASTER2 = BPRFSCH.GL_MASTER2;
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].GL_MASTER3 = BPRFSCH.GL_MASTER3;
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].GL_MASTER4 = BPRFSCH.GL_MASTER4;
        BPCFFCON.FEE_INFO.FEE_INFO1[WS_CNT-1].REF_NO = BPRFSCH.KEY.CTRT_NO;
        S000_CALL_BPZFFCON();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_CNT);
    }
    public void B020_REVER_PROCESS_NCB() throws IOException,SQLException,Exception {
        if (GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE != SCCGWA.COMM_AREA.AC_DATE) {
            CEP.TRC(SCCGWA, "CANT RVS");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CANOT_RVS_RECORD;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPRFSCH.UCT_FLG == 'Y') {
            BPCSSETT.KEY.SETTLE_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        }
        CEP.TRC(SCCGWA, BPCSSETT.KEY.SETTLE_DATE);
        if (BPRFSCH.UCT_FLG != 'Y') {
            R000_LAST_SCHD_CHECK();
            if (pgmRtn) return;
        }
        R000_REV_FSCHD_INF();
        if (pgmRtn) return;
        R000_REV_PACCR_INF();
        if (pgmRtn) return;
        if (BPRFSCHD.ADJ_AMT != 0 
            && BPRFSCH.UCT_FLG != 'Y') {
            R000_REV_DY_AMT();
            if (pgmRtn) return;
        }
        if (BPRFSCH.FEE_STATUS == '2') {
            R000_REV_FSCH_INF();
            if (pgmRtn) return;
        }
    }
    public void R000_LAST_SCHD_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFSCHD);
        IBS.init(SCCGWA, BPCRSCHD);
        BPRFSCHD.KEY.CTRT_NO = BPCSSETT.KEY.CTRT_NO;
        BPRFSCHD.KEY.SETTLE_DATE = BPCSSETT.KEY.SETTLE_DATE;
        BPCRSCHD.INFO.FUNC = 'B';
        BPCRSCHD.INFO.OPT = 'B';
        S000_CALL_BPZRSCHD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRFSCHD.KEY.CTRT_NO);
        CEP.TRC(SCCGWA, BPRFSCHD.KEY.SETTLE_DATE);
        CEP.TRC(SCCGWA, BPRFSCHD.STS);
        CEP.TRC(SCCGWA, BPCRSCHD.RETURN_INFO);
        if (BPCRSCHD.RETURN_INFO == 'F') {
            CEP.TRC(SCCGWA, "1");
            if (BPRFSCHD.STS == 'C') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NEXT_SCHD_NOT_REV;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_REV_FSCHD_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFSCHD);
        IBS.init(SCCGWA, BPCRSCHD);
        BPRFSCHD.KEY.CTRT_NO = BPCSSETT.KEY.CTRT_NO;
        BPRFSCHD.KEY.SETTLE_DATE = BPCSSETT.KEY.SETTLE_DATE;
        BPCRSCHD.INFO.FUNC = 'R';
        S000_CALL_BPZRSCHD();
        if (pgmRtn) return;
        WS_FSCHD_ADJ_AMT = BPRFSCHD.ADJ_AMT;
        if (BPCRSCHD.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SCHD_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPRFSCHD.STS != 'C') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SCHD_NOT_SETT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPRFSCH.UCT_FLG == 'Y') {
            BPRFSCHD.CHARGE_AMT = BPRFSCHD.CHARGE_AMT - BPCSSETT.INF.CHARGE_AMT;
            CEP.TRC(SCCGWA, BPRFSCHD.CHARGE_AMT);
            if (BPRFSCHD.CHARGE_AMT <= 0) {
                WS_FSCHD_DEL_FLG = 'Y';
            }
        } else {
            BPRFSCHD.STS = 'N';
            BPRFSCHD.CHARGE_AMT = 0;
        }
        BPRFSCHD.REAL_AC_DATE = 0;
        BPRFSCHD.JNL_NO = 0;
        BPRFSCHD.VOUCHER_NO = " ";
        BPRFSCHD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRFSCHD.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
        BPRFSCHD.CHG_AMT_REAL = 0;
        BPRFSCHD.CHG_CCY_REAL = " ";
        BPRFSCHD.RATE = 0;
        if (WS_FSCHD_DEL_FLG == 'Y') {
            BPCRSCHD.INFO.FUNC = 'D';
        } else {
            BPCRSCHD.INFO.FUNC = 'U';
        }
        S000_CALL_BPZRSCHD();
        if (pgmRtn) return;
    }
    public void R000_REV_PACCR_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPACCR);
        IBS.init(SCCGWA, BPCRACCR);
        BPRPACCR.KEY.CTRT_NO = BPCSSETT.KEY.CTRT_NO;
        BPCRACCR.INFO.FUNC = 'R';
        S000_CALL_BPZRACCR();
        if (pgmRtn) return;
        if (BPRFSCHD.STS == 'N') {
            BPRPACCR.LAST_SETTLE_DATE = 0;
            BPRPACCR.SETTLE_DATE = 0;
            B020_02_GET_PRE_SETTLE_DATE();
            if (pgmRtn) return;
        }
        BPRPACCR.SETTLE_AMT_TOTAL = BPRPACCR.SETTLE_AMT_TOTAL - BPCSSETT.INF.CHARGE_AMT;
        if (BPRFSCH.UCT_FLG == 'Y') {
            IBS.init(SCCGWA, BPRFEHIS);
            BPRFEHIS.KEY.AC_DT = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            BPRFEHIS.KEY.JRNNO = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
            T000_STARTBR_BPTFEHIS();
            if (pgmRtn) return;
            T000_READNEXT_BPTFEHIS();
            if (pgmRtn) return;
            while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
                if (BPRFEHIS.REF_NO == null) BPRFEHIS.REF_NO = "";
                JIBS_tmp_int = BPRFEHIS.REF_NO.length();
                for (int i=0;i<16-JIBS_tmp_int;i++) BPRFEHIS.REF_NO += " ";
                if (BPRFEHIS.REF_NO.substring(4 - 1, 4 + 2 - 1).equalsIgnoreCase("51")) {
                    WS_AMO_AMT += BPRFEHIS.ADJ_AMT;
                }
                CEP.TRC(SCCGWA, WS_AMO_AMT);
                T000_READNEXT_BPTFEHIS();
                if (pgmRtn) return;
            }
            T000_ENDBR_BPTFEHIS();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPRPACCR.ACCRUAL_AMT_TOTAL);
            CEP.TRC(SCCGWA, BPCSSETT.INF.CHARGE_AMT);
            CEP.TRC(SCCGWA, WS_AMO_AMT);
            BPRPACCR.ACCRUAL_AMT_TOTAL = BPRPACCR.ACCRUAL_AMT_TOTAL + BPCSSETT.INF.CHARGE_AMT - WS_AMO_AMT;
            CEP.TRC(SCCGWA, BPRPACCR.ACCRUAL_AMT_TOTAL);
        } else {
            CEP.TRC(SCCGWA, BPRPACCR.ACCRUAL_AMT_TOTAL);
            CEP.TRC(SCCGWA, BPCSSETT.INF.CHARGE_AMT);
            CEP.TRC(SCCGWA, WS_FSCHD_ADJ_AMT);
            BPRPACCR.ACCRUAL_AMT_TOTAL = BPRPACCR.ACCRUAL_AMT_TOTAL + BPCSSETT.INF.CHARGE_AMT - WS_FSCHD_ADJ_AMT;
            CEP.TRC(SCCGWA, BPRPACCR.ACCRUAL_AMT_TOTAL);
        }
        BPRPACCR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRPACCR.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
        BPRPACCR.SUP_TEL1 = SCCGWA.COMM_AREA.SUP1_ID;
        BPRPACCR.SUP_TEL2 = SCCGWA.COMM_AREA.SUP2_ID;
        BPCRACCR.INFO.FUNC = 'U';
        S000_CALL_BPZRACCR();
        if (pgmRtn) return;
    }
    public void R000_REV_DY_AMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.PROD_CODE = BPRFSCH.FEE_TYPE;
        BPCPOEWA.DATA.PROD_CODE_REL = BPRFSCH.PRD_TYPE;
        BPCPOEWA.DATA.CNTR_TYPE = "FEES";
        BPCPOEWA.DATA.BR_OLD = BPRFSCH.BOOK_CENTRE;
        BPCPOEWA.DATA.BR_NEW = BPRFSCH.BOOK_CENTRE;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPRFSCHD.CHARGE_CCY;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.DESC = BPRFSCH.REMARK;
        IBS.init(SCCGWA, VTCPQTAX);
        VTCPQTAX.INPUT_DATA.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        VTCPQTAX.INPUT_DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        VTCPQTAX.INPUT_DATA.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        VTCPQTAX.INPUT_DATA.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        VTCPQTAX.INPUT_DATA.BR = BPRFSCH.BOOK_CENTRE;
        VTCPQTAX.INPUT_DATA.PROD_CD = BPRFSCH.FEE_TYPE;
        VTCPQTAX.INPUT_DATA.PROD_CD_REL = BPRFSCH.PRD_TYPE;
        VTCPQTAX.INPUT_DATA.CI_NO = BPRFSCH.CI_NO;
        VTCPQTAX.INPUT_DATA.CCY = BPRFSCHD.CHARGE_CCY;
        VTCPQTAX.INPUT_DATA.YJ_AMT = BPRFSCHD.ADJ_AMT;
        VTCPQTAX.INPUT_DATA.EC_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        VTCPQTAX.INPUT_DATA.EC_JRNNO = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
        S000_CALL_VTZPQTAX();
        if (pgmRtn) return;
        BPCPOEWA.DATA.EVENT_CODE = "DY";
        if (BPRFSCHD.ADJ_AMT > 0) {
            BPCPOEWA.DATA.AMT_INFO[05-1].AMT = BPRFSCHD.ADJ_AMT;
        } else {
            BPCPOEWA.DATA.AMT_INFO[06-1].AMT = 0 - BPRFSCHD.ADJ_AMT;
        }
        if (VTCPQTAX.OUTPUT_DATA.TAX_AMT > 0) {
            BPCPOEWA.DATA.AMT_INFO[07-1].AMT = VTCPQTAX.OUTPUT_DATA.TAX_AMT;
        } else {
            BPCPOEWA.DATA.AMT_INFO[08-1].AMT = 0 - VTCPQTAX.OUTPUT_DATA.TAX_AMT;
        }
        BPCPOEWA.DATA.CI_NO = BPRFSCH.CI_NO;
        BPCPOEWA.DATA.THEIR_AC = BPRFSCH.CHARGE_AC;
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void R000_REV_FSCH_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFSCH);
        IBS.init(SCCGWA, BPCRFSCH);
        BPRFSCH.KEY.CTRT_NO = BPCSSETT.KEY.CTRT_NO;
        BPCRFSCH.INFO.FUNC = 'R';
        S000_CALL_BPZRFSCH();
        if (pgmRtn) return;
        BPRFSCH.FEE_STATUS = '1';
        BPCRFSCH.INFO.FUNC = 'U';
        S000_CALL_BPZRFSCH();
        if (pgmRtn) return;
        if (BPRFSCH.UCT_FLG == 'Y') {
            IBS.init(SCCGWA, BPRFSCHD);
            IBS.init(SCCGWA, BPCRSCHD);
            BPRFSCHD.KEY.CTRT_NO = BPCSSETT.KEY.CTRT_NO;
            BPRFSCHD.KEY.SETTLE_DATE = BPRFSCH.MATURITY_DATE;
            BPCRSCHD.INFO.FUNC = 'R';
            S000_CALL_BPZRSCHD();
            if (pgmRtn) return;
            BPRFSCHD.STS = 'N';
            BPCRSCHD.INFO.FUNC = 'U';
            S000_CALL_BPZRSCHD();
            if (pgmRtn) return;
        }
    }
    public void B020_01_SET_FFTXI_REV() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        BPCFFTXI.TX_DATA.FEE_CTRT_NO = BPCSSETT.KEY.CTRT_NO;
        CEP.TRC(SCCGWA, BPCSSETT.INF.REL_CTRT_NO);
        BPCFFTXI.TX_DATA.BSNS_NO = BPCSSETT.INF.REL_CTRT_NO;
        if (BPCSSETT.INF.CI_NO.trim().length() > 0) {
            BPCFFTXI.TX_DATA.CI_NO = BPCSSETT.INF.CI_NO;
        }
        S000_CALL_BPZFFTXI();
        if (pgmRtn) return;
    }
    public void B020_02_GET_PRE_SETTLE_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFSCHD);
        IBS.init(SCCGWA, BPCRSCHD);
        BPRFSCHD.KEY.CTRT_NO = BPCSSETT.KEY.CTRT_NO;
        BPRFSCHD.KEY.SETTLE_DATE = BPCSSETT.KEY.SETTLE_DATE;
        BPCRSCHD.INFO.FUNC = 'B';
        BPCRSCHD.INFO.OPT = 'A';
        S000_CALL_BPZRSCHD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRFSCHD.STS);
        CEP.TRC(SCCGWA, BPCRSCHD.RETURN_INFO);
        if (BPCRSCHD.RETURN_INFO == 'F') {
            BPRPACCR.SETTLE_DATE = BPRFSCHD.KEY.SETTLE_DATE;
            CEP.TRC(SCCGWA, BPRFSCHD.KEY.SETTLE_DATE);
        }
    }
    public void B085_UPD_EVENT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSGSEQ);
        BPCSGSEQ.TYPE = "SEQ";
        BPCSGSEQ.CODE = "DYBATNO";
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSGSEQ.RUN_MODE = 'O';
        S000_CALL_BPZSGSEQ();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCUMENT);
        BPCUMENT.PROC_DATA.FUNC_CODE = "R ";
        BPCUMENT.DIARY_DATA.CONTRACT_NO = BPCSSETT.KEY.CTRT_NO;
        CEP.TRC(SCCGWA, WS_LAST_SCHD_FLAG);
        BPCUMENT.DIARY_DATA.DIARY_TYPE = "FS";
        if (WS_LAST_SCHD_FLAG == 'Y') {
            BPCUMENT.DIARY_DATA.DIARY_TYPE = "MA";
        }
        BPCUMENT.DIARY_DATA.DIARY_DATE = BPCSSETT.KEY.SETTLE_DATE;
        S000_CALL_BPZUMENT();
        if (pgmRtn) return;
        BPCUMENT.PROC_DATA.FUNC_CODE = "M2";
        BPCUMENT.DIARY_DATA.TRN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCUMENT.DIARY_DATA.TRN_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        BPCUMENT.DIARY_DATA.DIARY_BATNO = BPCSGSEQ.SEQ;
        BPCUMENT.DIARY_DATA.DIARY_STATUS = 'A';
        if (BPCSSETT.FUNC == 'R') {
            BPCUMENT.DIARY_DATA.DIARY_STATUS = 'U';
        }
        BPCUMENT.DIARY_DATA.AMOUNT1 = BPCSSETT.INF.CHARGE_AMT;
        BPCUMENT.DIARY_DATA.AMOUNT4 = BPCSSETT.INF.CHARGE_AMT;
        BPCUMENT.DIARY_DATA.AMOUNT5 = BPCSSETT.INF.CHARGE_AMT;
        if (BPRFSCH.PAY_IND == 'R') {
            BPCUMENT.DIARY_DATA.AMOUNT4 = 0;
        } else {
            BPCUMENT.DIARY_DATA.AMOUNT5 = 0;
        }
        BPCUMENT.PROC_DATA.DIARY_CNT = 1;
        S000_CALL_BPZUMENT();
        if (pgmRtn) return;
    }
    public void B090_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSSET1);
        B090_01_TRANS_DATA_OUPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_9;
        SCCFMT.DATA_PTR = BPCSSET1;
        SCCFMT.DATA_LEN = 583;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B090_01_TRANS_DATA_OUPUT() throws IOException,SQLException,Exception {
        BPCSSET1.CTRT_NO = BPCSSETT.KEY.CTRT_NO;
        BPCSSET1.REL_CTRT_NO = BPCSSETT.INF.REL_CTRT_NO;
        BPCSSET1.CI_NO = BPCSSETT.INF.CI_NO;
        BPCSSET1.ABBR_NAME = BPCSSETT.INF.ABBR_NAME;
        BPCSSET1.FEE_TYPE = BPCSSETT.INF.FEE_TYPE;
        BPCSSET1.PAY_IND = BPCSSETT.INF.PAY_IND;
        BPCSSET1.SETTLE_DATE = BPCSSETT.KEY.SETTLE_DATE;
        BPCSSET1.CHARGE_CCY = BPCSSETT.INF.CHARGE_CCY;
        BPCSSET1.CHARGE_AMT = BPCSSETT.INF.CHARGE_AMT;
        BPCSSET1.CHARGE_METHOD = BPRFSCH.CHARGE_METHOD;
        BPCSSET1.CHARGE_AC = BPRFSCH.CHARGE_AC;
        BPCSSET1.NOSTRO_AC_CD = BPRFSCH.NOSTRO_AC;
        BPCSSET1.TICKET = BPCSSETT.INF.TICKET;
        BPCSSET1.AMT_REAL = BPCSSETT.INF.AMT_REAL;
        BPCSSET1.CCY_REAL = BPCSSETT.INF.CCY_REAL;
        BPCSSET1.RATE = BPCSSETT.INF.RATE;
        BPCSSET1.EXG_DT = BPCSSETT.INF.EXG_DT;
        BPCSSET1.EXG_TM = BPCSSETT.INF.EXG_TM;
        BPCSSET1.REMARK = BPCSSETT.INF.REMARK;
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.PROD_CODE);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.PROD_CODE_REL);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.CNTR_TYPE);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.BR_OLD);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.BR_NEW);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.CCY_INFO[1-1].CCY);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.VALUE_DATE);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.EVENT_CODE);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[05-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[07-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.CI_NO);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.THEIR_AC);
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_VTZPQTAX() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, VTCPQTAX.INPUT_DATA.TR_DATE);
        CEP.TRC(SCCGWA, VTCPQTAX.INPUT_DATA.JRNNO);
        CEP.TRC(SCCGWA, VTCPQTAX.INPUT_DATA.TR_BR);
        CEP.TRC(SCCGWA, VTCPQTAX.INPUT_DATA.TX_CODE);
        CEP.TRC(SCCGWA, VTCPQTAX.INPUT_DATA.BR);
        CEP.TRC(SCCGWA, VTCPQTAX.INPUT_DATA.CNTR_TYPE);
        CEP.TRC(SCCGWA, VTCPQTAX.INPUT_DATA.PROD_CD);
        CEP.TRC(SCCGWA, VTCPQTAX.INPUT_DATA.PROD_CD_REL);
        CEP.TRC(SCCGWA, VTCPQTAX.INPUT_DATA.CI_NO);
        CEP.TRC(SCCGWA, VTCPQTAX.INPUT_DATA.CCY);
        CEP.TRC(SCCGWA, VTCPQTAX.INPUT_DATA.SH_AMT);
        CEP.TRC(SCCGWA, VTCPQTAX.INPUT_DATA.YJ_AMT);
        CEP.TRC(SCCGWA, VTCPQTAX.INPUT_DATA.INQ_TAX_FLG);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            VTCPQTAX.INPUT_DATA.EC_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            VTCPQTAX.INPUT_DATA.EC_JRNNO = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
        }
        IBS.CALLCPN(SCCGWA, "VT-P-QUERY-TAX", VTCPQTAX);
        CEP.TRC(SCCGWA, VTCPQTAX.RC);
        if (VTCPQTAX.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, VTCPQTAX.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
        CEP.TRC(SCCGWA, VTCPQTAX.OUTPUT_DATA.TAX_AMT);
    }
    public void S000_CALL_BPZSFSCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MGM-FEESCH", BPCSFSCH);
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
        IBS.CALLCPN(SCCGWA, "BP-U-MENT-MAINT", BPCUMENT);
        if (BPCUMENT.PROC_DATA.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUMENT.PROC_DATA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFFTXI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-TX-INFO", BPCFFTXI);
        if (BPCFFTXI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFTXI.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFFCON() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-CON-CHG-INFO", BPCFFCON);
        if (BPCFFCON.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFCON.RC);
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
    public void S000_CALL_BPZRACCR() throws IOException,SQLException,Exception {
        BPCRACCR.INFO.POINTER = BPRPACCR;
        BPCRACCR.INFO.REC_LEN = 192;
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-ACCR", BPCRACCR);
        CEP.TRC(SCCGWA, BPCRACCR.RC);
        if (BPCRACCR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRACCR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSFECT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-FECT-MAINT", BPCSFECT, true);
        if (BPCSFECT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSFECT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void T000_STARTBR_BPTFEHIS() throws IOException,SQLException,Exception {
        BPTFEHIS_BR.rp = new DBParm();
        BPTFEHIS_BR.rp.TableName = "BPTFEHIS";
        BPTFEHIS_BR.rp.eqWhere = "AC_DT,JRNNO";
        IBS.STARTBR(SCCGWA, BPRFEHIS, BPTFEHIS_BR);
    }
    public void T000_READNEXT_BPTFEHIS() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRFEHIS, this, BPTFEHIS_BR);
    }
    public void T000_ENDBR_BPTFEHIS() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTFEHIS_BR);
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
