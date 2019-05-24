package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSRATE {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    DBParm LNTRATH_RD;
    DBParm LNTRATN_RD;
    boolean pgmRtn = false;
    char K_TYP_YEAR = 'Y';
    char K_TYP_MONTH = 'M';
    char K_TYP_DAY = 'D';
    char K_USE_CPND = 'Y';
    char K_RATE_N = 'N';
    char K_RATE_O = 'O';
    char K_RATE_L = 'L';
    char K_RATE_P = 'P';
    String K_HIS_RMKS = "RATE ADJUST";
    char K_FUNC_QUERY = 'I';
    char K_FUNC_ADD = 'A';
    char K_FUNC_UPD = 'M';
    char K_FUNC_DEL = 'D';
    LNZSRATE_WS_COND_FLG WS_COND_FLG = new LNZSRATE_WS_COND_FLG();
    String WS_CURR_DATE = " ";
    int WS_I = 0;
    String WS_ERR_MSG = " ";
    char WS_RATE_TYP = ' ';
    double WS_INT_RATE = 0;
    double WS_INT_RATE_Y = 0;
    double WS_YEAR_RATE_N = 0;
    double WS_YEAR_RATE_O = 0;
    double WS_YEAR_RATE_L = 0;
    double WS_YEAR_RATE_P = 0;
    char[] WS_DEL_TYP = new char[4];
    char WS_ICAL_FLG = ' ';
    int WS_CONT_BOOK_BR = 0;
    String WS_LOAN_CCY = " ";
    LNZSRATE_WS_OUT WS_OUT = new LNZSRATE_WS_OUT();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    LNRRATH LNRRATH = new LNRRATH();
    LNCICTLM LNCICTLM = new LNCICTLM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    LNCSRATE LNCSRATEO = new LNCSRATE();
    LNCSRATE LNCSRATEN = new LNCSRATE();
    LNCCONTM LNCCONTM = new LNCCONTM();
    LNCCLNQ LNCCLNQ = new LNCCLNQ();
    LNCRCAL LNCRCAL = new LNCRCAL();
    LNCICAL LNCICAL = new LNCICAL();
    LNCPLAJ LNCPLAJ = new LNCPLAJ();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    LNCFCPL LNCFCPL = new LNCFCPL();
    LNCFRATE LNCFRATE = new LNCFRATE();
    LNCPCFTA LNCPCFTA = new LNCPCFTA();
    LNCRATNM LNCRATNM = new LNCRATNM();
    LNCRATLM LNCRATLM = new LNCRATLM();
    LNCSIQIF LNCSIQIF = new LNCSIQIF();
    BPCCINTI BPCCINTI = new BPCCINTI();
    LNRRATN LNRRATN = new LNRRATN();
    CICQACAC CICQACAC = new CICQACAC();
    SCCWRMSG SCCWRMSG = new SCCWRMSG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGAPV SCCGAPV;
    SCCBATH SCCBATH;
    LNCSRATE LNCSRATE;
    public void MP(SCCGWA SCCGWA, LNCSRATE LNCSRATE) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSRATE = LNCSRATE;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSRATE return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSRATEO);
        IBS.init(SCCGWA, LNCSRATEN);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK_INPUT();
        if (pgmRtn) return;
        if (LNCSRATE.FUNC_TYP != 'I' 
            && LNCSRATE.DATA.RAT_CHG_DT < SCCGWA.COMM_AREA.AC_DATE 
            && LNCSRATE.SPE_DEAL_FLG != '1') {
            B101_RCAL_PROC();
            if (pgmRtn) return;
        }
        if (LNCSRATE.FUNC_TYP == 'A' 
            || LNCSRATE.FUNC_TYP == 'M') {
            B102_CAL_LOAN_RATE();
            if (pgmRtn) return;
        }
        if (LNCSRATE.FUNC_TYP == 'I') {
            B001_FUNC_QUERY();
            if (pgmRtn) return;
        } else if (LNCSRATE.FUNC_TYP == 'A') {
            B002_FUNC_ADD();
            if (pgmRtn) return;
            B003_UPD_ICTL_RATE();
            if (pgmRtn) return;
        } else if (LNCSRATE.FUNC_TYP == 'M') {
            B004_FUNC_UPD();
            if (pgmRtn) return;
            B003_UPD_ICTL_RATE();
            if (pgmRtn) return;
        } else if (LNCSRATE.FUNC_TYP == 'D') {
            B008_FUNC_DEL();
            if (pgmRtn) return;
            B003_UPD_ICTL_RATE1();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE, LNCSRATE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (JIBS_tmp_str[0].equalsIgnoreCase("0136000") 
            && (LNCSRATE.FUNC_TYP == 'A' 
            || LNCSRATE.FUNC_TYP == 'M' 
            || LNCSRATE.FUNC_TYP == 'D')) {
            B006_WRITE_NFIA_HIS_PROC();
            if (pgmRtn) return;
        }
        if (WS_ICAL_FLG == 'Y') {
            if (LNCAPRDM.REC_DATA.PAY_MTH == '4') {
                IBS.init(SCCGWA, LNCPLAJ);
                LNCPLAJ.COMM_DATA.ADJ_IND = 'R';
                LNCPLAJ.COMM_DATA.LN_AC = LNCSRATE.DATA.CONTRACT_NO;
                LNCPLAJ.COMM_DATA.SUF_NO = "" + LNCSRATE.DATA.SUB_CTA_NO;
                JIBS_tmp_int = LNCPLAJ.COMM_DATA.SUF_NO.length();
                for (int i=0;i<8-JIBS_tmp_int;i++) LNCPLAJ.COMM_DATA.SUF_NO = "0" + LNCPLAJ.COMM_DATA.SUF_NO;
                LNCPLAJ.COMM_DATA.TR_VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                S000_CALL_LNZPLAJ();
                if (pgmRtn) return;
                IBS.init(SCCGWA, LNCFCPL);
                LNCFCPL.COMM_DATA.PAY_MTH = '4';
                LNCFCPL.COMM_DATA.CTA_NO = LNCSRATE.DATA.CONTRACT_NO;
                S000_CALL_LNZFCPL();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, LNCICAL);
            LNCICAL.COMM_DATA.FUNC_CODE = 'U';
            LNCICAL.COMM_DATA.FUNC_TYPE = ' ';
            LNCICAL.COMM_DATA.LN_AC = LNCSRATE.DATA.CONTRACT_NO;
            LNCICAL.COMM_DATA.SUF_NO = "" + LNCSRATE.DATA.SUB_CTA_NO;
            JIBS_tmp_int = LNCICAL.COMM_DATA.SUF_NO.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) LNCICAL.COMM_DATA.SUF_NO = "0" + LNCICAL.COMM_DATA.SUF_NO;
            LNCICAL.COMM_DATA.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            S000_CALL_LNZICAL();
            if (pgmRtn) return;
        }
        if (LNCSRATE.FUNC_TYP == 'I') {
            B007_PUTOUT_DATA();
            if (pgmRtn) return;
        }
    }
    public void B000_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCAPRDM);
        LNCAPRDM.FUNC = '3';
        LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = LNCSRATE.DATA.CONTRACT_NO;
        S000_CALL_LNZAPRDM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCSRATE.DATA.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNCSRATE.DATA.RAT_CHG_DT);
        CEP.TRC(SCCGWA, LNCSRATE.DATA.INT_RAT_N);
        CEP.TRC(SCCGWA, LNCAPRDM.REC_DATA.PAY_MTH);
        if ((LNCSRATE.FUNC_TYP != 'I' 
            && LNCSRATE.FUNC_TYP != 'A' 
            && LNCSRATE.FUNC_TYP != 'M' 
            && LNCSRATE.FUNC_TYP != 'D')) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE, LNCSRATE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCSRATE.FUNC_TYP != 'I' 
            && LNCAPRDM.REC_DATA.PAY_MTH == '0' 
            && LNCSRATE.SPE_DEAL_FLG != '1' 
            && LNCSRATE.DATA.INT_RAT_N != 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PINT_N_MAIN_RATE, LNCSRATE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '3';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCSRATE.DATA.CONTRACT_NO;
        LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = LNCSRATE.DATA.SUB_CTA_NO;
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
            && LNCSRATE.FUNC_TYP != 'I') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_LOAN_CLOSE, LNCSRATE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(47 - 1, 47 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_HAS_DELETION, LNCSRATE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCSRATE.DATA.RAT_CHG_DT == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_VAL_DT_M_INPUT, LNCSRATE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCCONTM);
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCSRATE.DATA.CONTRACT_NO;
        LNCCONTM.FUNC = '3';
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
        WS_CONT_BOOK_BR = LNCCONTM.REC_DATA.BOOK_BR;
        WS_LOAN_CCY = LNCCONTM.REC_DATA.CCY;
        IBS.init(SCCGWA, LNCPCFTA);
        LNCPCFTA.BR_GP[1-1].BR = WS_CONT_BOOK_BR;
        LNCPCFTA.BR_GP[2-1].BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_LNZPCFTA();
        if (pgmRtn) return;
        if (LNCPCFTA.FTA_FLG == 'Y') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_NO_TRAN_FTA_NOT_SAME, LNCSRATE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if ((LNCSRATE.FUNC_TYP == K_FUNC_ADD 
            || LNCSRATE.FUNC_TYP == K_FUNC_UPD 
            || LNCSRATE.FUNC_TYP == K_FUNC_DEL) 
            && LNCSRATE.DATA.RAT_CHG_DT < LNCCONTM.REC_DATA.LAST_F_VAL_DATE) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_VAL_DT_LESS_F_VAL, LNCSRATE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCSRATE.FUNC_TYP == 'D' 
            && LNCSRATE.DATA.RAT_CHG_DT == LNCCONTM.REC_DATA.START_DATE 
            && LNCSRATE.SPE_DEAL_FLG != '2') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_CANT_DEL_STA_RAT, LNCSRATE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if ((LNCSRATE.DATA.RAT_CHG_DT < LNCCONTM.REC_DATA.START_DATE 
            || LNCSRATE.DATA.RAT_CHG_DT > LNCCONTM.REC_DATA.MAT_DATE) 
            && (LNCSRATE.FUNC_TYP == 'A' 
            || LNCSRATE.FUNC_TYP == 'M') 
            && LNCSRATE.DATA.INT_RAT_N != 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_DT_BETW_STA_END, LNCSRATE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCSRATE.DATA.MTH != '0') {
            IBS.init(SCCGWA, BPCCINTI);
            BPCCINTI.BASE_INFO.BASE_TYP = LNCSRATE.DATA.RATE_TYP_N;
            BPCCINTI.BASE_INFO.TENOR = LNCSRATE.DATA.RATEPERD_N;
            BPCCINTI.BASE_INFO.CCY = WS_LOAN_CCY;
            BPCCINTI.BASE_INFO.DT = SCCGWA.COMM_AREA.AC_DATE;
            BPCCINTI.FUNC = 'I';
            S000_CALL_BPZCINTI();
            if (pgmRtn) return;
            if (BPCCINTI.RC.RC_CODE != 0) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCCINTI.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRATE.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (LNCSRATE.DATA.CPND_USE == 'Y') {
                if (!LNCSRATE.DATA.PEN_RATT_O.equalsIgnoreCase(LNCSRATE.DATA.RATE_TYP_N)) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_RATE_TYP_ERR, LNCSRATE.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                if (!LNCSRATE.DATA.PEN_RATC_O.equalsIgnoreCase(LNCSRATE.DATA.RATEPERD_N)) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_RATE_TYP_ERR, LNCSRATE.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            } else {
                if ((!LNCSRATE.DATA.PEN_RATT_O.equalsIgnoreCase(LNCSRATE.DATA.RATE_TYP_N)) 
                    || (!LNCSRATE.DATA.CPNDRATT_L.equalsIgnoreCase(LNCSRATE.DATA.RATE_TYP_N))) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_RATE_TYP_ERR, LNCSRATE.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                if ((!LNCSRATE.DATA.PEN_RATC_O.equalsIgnoreCase(LNCSRATE.DATA.RATEPERD_N)) 
                    || (!LNCSRATE.DATA.CPNDRATC_L.equalsIgnoreCase(LNCSRATE.DATA.RATEPERD_N))) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_RATE_TYP_ERR, LNCSRATE.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
            if (LNCSRATE.DATA.FLT_MTH_N == ' ') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_FLT_ERR, LNCSRATE.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (LNCSRATE.DATA.FLT_MTH_N != '0') {
                if (LNCSRATE.DATA.FLT_MTH_N != '2' 
                    && LNCSRATE.DATA.RATE_VAR_N == ' ') {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_FLT_ERR, LNCSRATE.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                if (LNCSRATE.DATA.FLT_MTH_N != '1' 
                    && LNCSRATE.DATA.RATE_PCT_N == ' ') {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_FLT_ERR, LNCSRATE.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B001_FUNC_QUERY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSRATE.QUERY_DATA);
        IBS.init(SCCGWA, LNCSIQIF);
        for (WS_I = 1; WS_I <= 4; WS_I += 1) {
            if (WS_I == 1) {
                WS_RATE_TYP = K_RATE_N;
            } else if (WS_I == 2) {
                WS_RATE_TYP = K_RATE_O;
            } else if (WS_I == 3) {
                WS_RATE_TYP = K_RATE_L;
            } else if (WS_I == 4) {
                WS_RATE_TYP = K_RATE_P;
            }
            B100_READ_LNTRATH1();
            if (pgmRtn) return;
            R000_RECA_COMA();
            if (pgmRtn) return;
        }
        LNCSIQIF.CONTRACT_NO = LNCSRATE.DATA.CONTRACT_NO;
        S000_CALL_LNZSIQIF();
        if (pgmRtn) return;
        LNCSRATE.DATA.INT_RATT_N = 'Y';
        LNCSRATE.DATA.FLT_MTH_N = LNCSIQIF.FLOAT_MTH;
        LNCSRATE.DATA.RATE_TYP_N = LNCSIQIF.RATE_TYP;
        LNCSRATE.DATA.RATEPERD_N = LNCSIQIF.RATE_PERD;
        LNCSRATE.DATA.RATE_VAR_N = LNCSIQIF.RATE_VAR;
        LNCSRATE.DATA.RATE_PCT_N = LNCSIQIF.RATE_PCT;
        LNCSRATE.DATA.RATE_INT_N = LNCSIQIF.RATE_INT;
        LNCSRATE.DATA.INT_RAT_N = LNCSIQIF.IN_RATE;
        LNCSRATE.DATA.PEN_RRAT_O = LNCSIQIF.PEN_RRAT;
        LNCSRATE.DATA.PEN_TYP_O = LNCSIQIF.PEN_TYP;
        LNCSRATE.DATA.PEN_RATT_O = LNCSIQIF.PEN_RATT;
        LNCSRATE.DATA.PEN_RATC_O = LNCSIQIF.PEN_RATC;
        LNCSRATE.DATA.PEN_SPR_O = LNCSIQIF.PEN_SPR;
        LNCSRATE.DATA.PEN_PCT_O = LNCSIQIF.PEN_PCT;
        LNCSRATE.DATA.INT_RAT_O = LNCSIQIF.PEN_IRAT;
        LNCSRATE.DATA.INT_MTH = LNCSIQIF.INT_MTH;
        LNCSRATE.DATA.CPND_USE = LNCSIQIF.CPND_USE;
        CEP.TRC(SCCGWA, "B001-FUNC-QUERY");
        CEP.TRC(SCCGWA, LNCSRATE.DATA.CPND_USE);
        LNCSRATE.DATA.CPND_RTY_L = LNCSIQIF.CPND_RTY;
        LNCSRATE.DATA.CPND_TYP_L = LNCSIQIF.CPND_TYP;
        LNCSRATE.DATA.CPNDRATT_L = LNCSIQIF.CPNDRATT;
        LNCSRATE.DATA.CPNDRATC_L = LNCSIQIF.CPNDRATC;
        LNCSRATE.DATA.CPND_SPR_L = LNCSIQIF.CPND_SPR;
        LNCSRATE.DATA.CPND_PCT_L = LNCSIQIF.CPND_PCT;
        LNCSRATE.DATA.INT_RAT_L = LNCSIQIF.OLC_PERU;
        LNCSRATE.DATA.ABUS_RAT_P = LNCSIQIF.ABUS_RAT;
        LNCSRATE.DATA.ABUS_TYP_P = LNCSIQIF.ABUSRATT;
        LNCSRATE.DATA.ABUSRATT_P = LNCSIQIF.ABUS_TYP;
        LNCSRATE.DATA.ABUSRATC_P = LNCSIQIF.ABUSRATC;
        LNCSRATE.DATA.ABUSSPR_P = LNCSIQIF.ABUSSPR;
        LNCSRATE.DATA.ABUSPCT_P = LNCSIQIF.ABUSPCT;
        LNCSRATE.DATA.INT_RAT_P = LNCSIQIF.ABUSIRAT;
    }
    public void B002_FUNC_ADD() throws IOException,SQLException,Exception {
        if (LNCSRATE.DATA.RATE_KIND_N != ' ') {
            IBS.init(SCCGWA, LNRRATH);
            LNRRATH.KEY.CONTRACT_NO = LNCSRATE.DATA.CONTRACT_NO;
            LNRRATH.KEY.SUB_CTA_NO = LNCSRATE.DATA.SUB_CTA_NO;
            LNRRATH.KEY.RATE_TYP = K_RATE_N;
            LNRRATH.KEY.RAT_CHG_DT = LNCSRATE.DATA.RAT_CHG_DT;
            CEP.TRC(SCCGWA, "NORMAL RATE HISTORY");
            CEP.TRC(SCCGWA, LNRRATH.KEY.CONTRACT_NO);
            CEP.TRC(SCCGWA, LNRRATH.KEY.SUB_CTA_NO);
            CEP.TRC(SCCGWA, LNRRATH.KEY.RATE_TYP);
            CEP.TRC(SCCGWA, LNRRATH.KEY.RAT_CHG_DT);
            T00_READUPDATE_LNTRATH();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_COND_FLG.WS_FOUND_FLG);
            if (WS_COND_FLG.WS_FOUND_FLG == 'Y') {
                LNRRATH.RATE_KIND = LNCSRATE.DATA.RATE_KIND_N;
                LNRRATH.INT_RAT = LNCSRATE.DATA.INT_RAT_N;
                T00_REWRITE_LNTRATH();
                if (pgmRtn) return;
            } else {
                IBS.init(SCCGWA, LNRRATH);
                LNRRATH.KEY.CONTRACT_NO = LNCSRATE.DATA.CONTRACT_NO;
                LNRRATH.KEY.SUB_CTA_NO = LNCSRATE.DATA.SUB_CTA_NO;
                LNRRATH.KEY.RATE_TYP = K_RATE_N;
                LNRRATH.KEY.RAT_CHG_DT = LNCSRATE.DATA.RAT_CHG_DT;
                LNRRATH.RATE_KIND = LNCSRATE.DATA.RATE_KIND_N;
                LNRRATH.INT_RAT = LNCFRATE.COMM_DATA.IN_RATE;
                T00_WRITE_LNTRATH();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, "NORMAL AA");
                CEP.TRC(SCCGWA, LNRRATH.KEY.RATE_TYP);
                CEP.TRC(SCCGWA, LNRRATH.KEY.RAT_CHG_DT);
            }
            CEP.TRC(SCCGWA, LNCSRATE.DATA.CONTRACT_NO);
            CEP.TRC(SCCGWA, LNCSRATE.DATA.RAT_CHG_DT);
            CEP.TRC(SCCGWA, LNCSRATE.DATA.MTH);
            CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.FLT_MTH);
            CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.RAT_TYP);
            CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.RAT_PD);
            CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.RAT_VAR);
            CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.RAT_PCT);
            CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.IN_RATE);
            IBS.init(SCCGWA, LNRRATN);
            LNCRATNM.REC_DATA.KEY.CONTRACT_NO = LNCSRATE.DATA.CONTRACT_NO;
            LNCRATNM.REC_DATA.KEY.ACTV_DT = LNCSRATE.DATA.RAT_CHG_DT;
            T000_READ_LNTRATN();
            if (pgmRtn) return;
            LNCRATNM.FUNC = '0';
            LNCRATNM.REC_DATA.KEY.CONTRACT_NO = LNCSRATE.DATA.CONTRACT_NO;
            LNCRATNM.REC_DATA.KEY.ACTV_DT = LNCSRATE.DATA.RAT_CHG_DT;
            LNCRATNM.REC_DATA.RATE_FLG = LNRRATN.RATE_FLG;
            if (LNCRATNM.REC_DATA.RATE_FLG == ' ') {
                LNCRATNM.REC_DATA.RATE_FLG = LNCSRATE.DATA.MTH;
            }
            LNCRATNM.REC_DATA.VARIATION_METHOD = LNCFRATE.COMM_DATA.FLT_MTH;
            LNCRATNM.REC_DATA.INT_RATE_TYPE1 = LNCFRATE.COMM_DATA.RAT_TYP;
            LNCRATNM.REC_DATA.INT_RATE_CLAS1 = LNCFRATE.COMM_DATA.RAT_PD;
            LNCRATNM.REC_DATA.RATE_VARIATION1 = LNCFRATE.COMM_DATA.RAT_VAR;
            LNCRATNM.REC_DATA.RATE_PECT1 = LNCFRATE.COMM_DATA.RAT_PCT;
            LNCRATNM.REC_DATA.ALL_IN_RATE = LNCFRATE.COMM_DATA.IN_RATE;
            LNCRATNM.REC_DATA.BASE_RATE1 = LNCSRATE.DATA.RATE_INT_N;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                LNCRATNM.REC_DATA.FLT_PERD = LNRRATN.FLT_PERD;
                LNCRATNM.REC_DATA.FLT_PERD_UNIT = LNRRATN.FLT_PERD_UNIT;
                LNCRATNM.REC_DATA.FIRST_FLT_FLG = LNRRATN.FIRST_FLT_FLG;
                LNCRATNM.REC_DATA.FLT_DAY = LNRRATN.FLT_DAY;
                LNCRATNM.REC_DATA.FLT_GAP_PERD = LNRRATN.FLT_GAP_PERD;
                LNCRATNM.REC_DATA.FLT_DTADJ_FLG = LNRRATN.FLT_DTADJ_FLG;
                LNCRATNM.REC_DATA.FST_FLT_DT = LNRRATN.FST_FLT_DT;
                LNCRATNM.REC_DATA.NEXT_FLT_DT = LNRRATN.NEXT_FLT_DT;
            } else {
                LNCRATNM.REC_DATA.FLT_PERD = LNCSRATE.DATA.FLT_PERD;
                LNCRATNM.REC_DATA.FLT_PERD_UNIT = LNCSRATE.DATA.FLT_PERD_UNIT;
                LNCRATNM.REC_DATA.FIRST_FLT_FLG = LNCSRATE.DATA.FIRST_FLT_FLG;
                LNCRATNM.REC_DATA.FLT_DAY = LNCSRATE.DATA.FLT_DAY;
                LNCRATNM.REC_DATA.FLT_GAP_PERD = LNCSRATE.DATA.FLT_GAP_PERD;
                LNCRATNM.REC_DATA.FLT_DTADJ_FLG = LNCSRATE.DATA.FLT_DTADJ_FLG;
                LNCRATNM.REC_DATA.FST_FLT_DT = LNCSRATE.DATA.FST_FLT_DT;
                LNCRATNM.REC_DATA.NEXT_FLT_DT = LNCSRATE.DATA.NEXT_FLT_DT;
            }
            S000_CALL_LNZRATNM();
            if (pgmRtn) return;
        }
        if (LNCSRATE.DATA.RATE_KIND_O != ' ') {
            IBS.init(SCCGWA, LNRRATH);
            LNRRATH.KEY.CONTRACT_NO = LNCSRATE.DATA.CONTRACT_NO;
            LNRRATH.KEY.SUB_CTA_NO = LNCSRATE.DATA.SUB_CTA_NO;
            LNRRATH.KEY.RATE_TYP = K_RATE_O;
            LNRRATH.KEY.RAT_CHG_DT = LNCSRATE.DATA.RAT_CHG_DT;
            T00_READUPDATE_LNTRATH();
            if (pgmRtn) return;
            if (WS_COND_FLG.WS_FOUND_FLG == 'Y') {
                LNRRATH.RATE_KIND = LNCSRATE.DATA.RATE_KIND_O;
                LNRRATH.INT_RAT = LNCSRATE.DATA.INT_RAT_O;
                T00_REWRITE_LNTRATH();
                if (pgmRtn) return;
            } else {
                IBS.init(SCCGWA, LNRRATH);
                LNRRATH.KEY.CONTRACT_NO = LNCSRATE.DATA.CONTRACT_NO;
                LNRRATH.KEY.SUB_CTA_NO = LNCSRATE.DATA.SUB_CTA_NO;
                LNRRATH.KEY.RATE_TYP = K_RATE_O;
                LNRRATH.KEY.RAT_CHG_DT = LNCSRATE.DATA.RAT_CHG_DT;
                LNRRATH.RATE_KIND = LNCSRATE.DATA.RATE_KIND_O;
                LNRRATH.INT_RAT = LNCFRATE.COMM_DATA.PEN_IRAT;
                T00_WRITE_LNTRATH();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, "FAXI AA");
                CEP.TRC(SCCGWA, LNRRATH.KEY.RATE_TYP);
                CEP.TRC(SCCGWA, LNRRATH.KEY.RAT_CHG_DT);
            }
            LNCRATLM.FUNC = '0';
            LNCRATLM.REC_DATA.KEY.CONTRACT_NO = LNCSRATE.DATA.CONTRACT_NO;
            LNCRATLM.REC_DATA.KEY.ACTV_DT = LNCSRATE.DATA.RAT_CHG_DT;
            LNCRATLM.REC_DATA.KEY.OVD_KND = 'O';
            LNCRATLM.REC_DATA.INT_CHRG_MTH = LNCFRATE.COMM_DATA.PEN_RRAT;
            LNCRATLM.REC_DATA.EFF_RAT = LNCFRATE.COMM_DATA.PEN_IRAT;
            LNCRATLM.REC_DATA.RATE_TYPE = LNCFRATE.COMM_DATA.PEN_RATT;
            LNCRATLM.REC_DATA.RATE_CLAS = LNCFRATE.COMM_DATA.PEN_RATC;
            LNCRATLM.REC_DATA.DIF_IRAT_PER = LNCFRATE.COMM_DATA.PEN_PCT;
            LNCRATLM.REC_DATA.DIF_IRAT_PNT = LNCFRATE.COMM_DATA.PEN_SPR;
            LNCRATLM.REC_DATA.VARIATION_METHOD = LNCFRATE.COMM_DATA.PEN_TYP;
            S000_CALL_LNZRATLM();
            if (pgmRtn) return;
        }
        if (LNCSRATE.DATA.RATE_KIND_P != ' ') {
            IBS.init(SCCGWA, LNRRATH);
            LNRRATH.KEY.CONTRACT_NO = LNCSRATE.DATA.CONTRACT_NO;
            LNRRATH.KEY.SUB_CTA_NO = LNCSRATE.DATA.SUB_CTA_NO;
            LNRRATH.KEY.RATE_TYP = K_RATE_P;
            LNRRATH.KEY.RAT_CHG_DT = LNCSRATE.DATA.RAT_CHG_DT;
            T00_READUPDATE_LNTRATH();
            if (pgmRtn) return;
            if (WS_COND_FLG.WS_FOUND_FLG == 'Y') {
                LNRRATH.RATE_KIND = LNCSRATE.DATA.RATE_KIND_P;
                LNRRATH.INT_RAT = LNCSRATE.DATA.INT_RAT_P;
                T00_REWRITE_LNTRATH();
                if (pgmRtn) return;
            } else {
                IBS.init(SCCGWA, LNRRATH);
                LNRRATH.KEY.CONTRACT_NO = LNCSRATE.DATA.CONTRACT_NO;
                LNRRATH.KEY.SUB_CTA_NO = LNCSRATE.DATA.SUB_CTA_NO;
                LNRRATH.KEY.RATE_TYP = K_RATE_P;
                LNRRATH.KEY.RAT_CHG_DT = LNCSRATE.DATA.RAT_CHG_DT;
                LNRRATH.RATE_KIND = LNCSRATE.DATA.RATE_KIND_P;
                LNRRATH.INT_RAT = LNCFRATE.COMM_DATA.ABUSIRAT;
                T00_WRITE_LNTRATH();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, "NUO YONG  AA");
                CEP.TRC(SCCGWA, LNRRATH.KEY.RATE_TYP);
                CEP.TRC(SCCGWA, LNRRATH.KEY.RAT_CHG_DT);
            }
            LNCRATLM.FUNC = '0';
            LNCRATLM.REC_DATA.KEY.CONTRACT_NO = LNCSRATE.DATA.CONTRACT_NO;
            LNCRATLM.REC_DATA.KEY.ACTV_DT = LNCSRATE.DATA.RAT_CHG_DT;
            LNCRATLM.REC_DATA.KEY.OVD_KND = 'P';
            LNCRATLM.REC_DATA.INT_CHRG_MTH = LNCFRATE.COMM_DATA.ABUS_RAT;
            LNCRATLM.REC_DATA.EFF_RAT = LNCFRATE.COMM_DATA.ABUSIRAT;
            LNCRATLM.REC_DATA.RATE_TYPE = LNCFRATE.COMM_DATA.ABUSRATT;
            LNCRATLM.REC_DATA.RATE_CLAS = LNCFRATE.COMM_DATA.ABUSRATC;
            LNCRATLM.REC_DATA.DIF_IRAT_PER = LNCFRATE.COMM_DATA.ABUSPCT;
            LNCRATLM.REC_DATA.DIF_IRAT_PNT = LNCFRATE.COMM_DATA.ABUSSPR;
            LNCRATLM.REC_DATA.VARIATION_METHOD = LNCFRATE.COMM_DATA.ABUS_TYP;
            S000_CALL_LNZRATLM();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCSRATE.DATA.CPND_USE);
        if (LNCSRATE.DATA.CPND_USE == K_USE_CPND) {
            IBS.init(SCCGWA, LNRRATH);
            LNRRATH.KEY.CONTRACT_NO = LNCSRATE.DATA.CONTRACT_NO;
            LNRRATH.KEY.SUB_CTA_NO = LNCSRATE.DATA.SUB_CTA_NO;
            LNRRATH.KEY.RATE_TYP = K_RATE_L;
            LNRRATH.KEY.RAT_CHG_DT = LNCSRATE.DATA.RAT_CHG_DT;
            T00_READUPDATE_LNTRATH();
            if (pgmRtn) return;
            if (WS_COND_FLG.WS_FOUND_FLG == 'Y') {
                LNRRATH.RATE_KIND = LNCSRATE.DATA.RATE_KIND_O;
                LNRRATH.INT_RAT = LNCSRATE.DATA.INT_RAT_O;
                T00_REWRITE_LNTRATH();
                if (pgmRtn) return;
            } else {
                IBS.init(SCCGWA, LNRRATH);
                LNRRATH.KEY.CONTRACT_NO = LNCSRATE.DATA.CONTRACT_NO;
                LNRRATH.KEY.SUB_CTA_NO = LNCSRATE.DATA.SUB_CTA_NO;
                LNRRATH.KEY.RATE_TYP = K_RATE_L;
                LNRRATH.KEY.RAT_CHG_DT = LNCSRATE.DATA.RAT_CHG_DT;
                LNRRATH.RATE_KIND = LNCSRATE.DATA.RATE_KIND_O;
                LNRRATH.INT_RAT = LNCFRATE.COMM_DATA.PEN_IRAT;
                T00_WRITE_LNTRATH();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, "FULI AA");
                CEP.TRC(SCCGWA, LNRRATH.KEY.RATE_TYP);
                CEP.TRC(SCCGWA, LNRRATH.KEY.RAT_CHG_DT);
            }
            LNCRATLM.FUNC = '0';
            LNCRATLM.REC_DATA.KEY.CONTRACT_NO = LNCSRATE.DATA.CONTRACT_NO;
            LNCRATLM.REC_DATA.KEY.ACTV_DT = LNCSRATE.DATA.RAT_CHG_DT;
            LNCRATLM.REC_DATA.KEY.OVD_KND = 'L';
            LNCRATLM.REC_DATA.INT_CHRG_MTH = LNCFRATE.COMM_DATA.PEN_RRAT;
            LNCRATLM.REC_DATA.EFF_RAT = LNCFRATE.COMM_DATA.PEN_IRAT;
            LNCRATLM.REC_DATA.RATE_TYPE = LNCFRATE.COMM_DATA.PEN_RATT;
            LNCRATLM.REC_DATA.RATE_CLAS = LNCFRATE.COMM_DATA.PEN_RATC;
            LNCRATLM.REC_DATA.DIF_IRAT_PER = LNCFRATE.COMM_DATA.PEN_PCT;
            LNCRATLM.REC_DATA.DIF_IRAT_PNT = LNCFRATE.COMM_DATA.PEN_SPR;
            LNCRATLM.REC_DATA.VARIATION_METHOD = LNCFRATE.COMM_DATA.PEN_TYP;
            S000_CALL_LNZRATLM();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, LNCSRATE.DATA.CPND_USE);
            CEP.TRC(SCCGWA, "55555");
            CEP.TRC(SCCGWA, LNCSRATE.DATA.RATE_KIND_L);
            CEP.TRC(SCCGWA, LNRRATH.KEY.CONTRACT_NO);
            CEP.TRC(SCCGWA, LNRRATH.KEY.SUB_CTA_NO);
            CEP.TRC(SCCGWA, LNRRATH.KEY.RATE_TYP);
            CEP.TRC(SCCGWA, LNRRATH.KEY.RAT_CHG_DT);
            if (LNCSRATE.DATA.RATE_KIND_L != ' ') {
                IBS.init(SCCGWA, LNRRATH);
                LNRRATH.KEY.CONTRACT_NO = LNCSRATE.DATA.CONTRACT_NO;
                LNRRATH.KEY.SUB_CTA_NO = LNCSRATE.DATA.SUB_CTA_NO;
                LNRRATH.KEY.RATE_TYP = K_RATE_L;
                LNRRATH.KEY.RAT_CHG_DT = LNCSRATE.DATA.RAT_CHG_DT;
                T00_READUPDATE_LNTRATH();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, "FULI BB");
                CEP.TRC(SCCGWA, LNRRATH.KEY.RATE_TYP);
                CEP.TRC(SCCGWA, LNRRATH.KEY.RAT_CHG_DT);
                if (WS_COND_FLG.WS_FOUND_FLG == 'Y') {
                    LNRRATH.RATE_KIND = LNCSRATE.DATA.RATE_KIND_L;
                    LNRRATH.INT_RAT = LNCSRATE.DATA.INT_RAT_L;
                    T00_REWRITE_LNTRATH();
                    if (pgmRtn) return;
                } else {
                    LNRRATH.KEY.CONTRACT_NO = LNCSRATE.DATA.CONTRACT_NO;
                    LNRRATH.KEY.SUB_CTA_NO = LNCSRATE.DATA.SUB_CTA_NO;
                    LNRRATH.KEY.RATE_TYP = K_RATE_L;
                    LNRRATH.KEY.RAT_CHG_DT = LNCSRATE.DATA.RAT_CHG_DT;
                    LNRRATH.RATE_KIND = LNCSRATE.DATA.RATE_KIND_L;
                    LNRRATH.INT_RAT = LNCFRATE.COMM_DATA.CPNDRATE;
                    T00_WRITE_LNTRATH();
                    if (pgmRtn) return;
                }
                LNCRATLM.FUNC = '0';
                LNCRATLM.REC_DATA.KEY.CONTRACT_NO = LNCSRATE.DATA.CONTRACT_NO;
                LNCRATLM.REC_DATA.KEY.ACTV_DT = LNCSRATE.DATA.RAT_CHG_DT;
                LNCRATLM.REC_DATA.KEY.OVD_KND = 'L';
                LNCRATLM.REC_DATA.INT_CHRG_MTH = LNCFRATE.COMM_DATA.CPND_RTY;
                LNCRATLM.REC_DATA.EFF_RAT = LNCFRATE.COMM_DATA.CPNDRATE;
                LNCRATLM.REC_DATA.RATE_TYPE = LNCFRATE.COMM_DATA.CPNDRATT;
                LNCRATLM.REC_DATA.RATE_CLAS = LNCFRATE.COMM_DATA.CPNDRATC;
                LNCRATLM.REC_DATA.DIF_IRAT_PER = LNCFRATE.COMM_DATA.CPND_PCT;
                LNCRATLM.REC_DATA.DIF_IRAT_PNT = LNCFRATE.COMM_DATA.CPND_SPR;
                LNCRATLM.REC_DATA.VARIATION_METHOD = LNCFRATE.COMM_DATA.CPND_TYP;
                CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.CPND_RTY);
                CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.CPNDRATE);
                CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.CPNDRATT);
                CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.CPNDRATC);
                CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.CPND_PCT);
                CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.CPND_SPR);
                CEP.TRC(SCCGWA, LNCFRATE.COMM_DATA.CPND_TYP);
                S000_CALL_LNZRATLM();
                if (pgmRtn) return;
            }
        }
    }
    public void B003_UPD_ICTL_RATE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "44444444");
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.CUR_RAT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, LNCSRATE.DATA.RAT_CHG_DT);
        if (LNCSRATE.DATA.RAT_CHG_DT <= SCCGWA.COMM_AREA.AC_DATE) {
            IBS.init(SCCGWA, LNCICTLM);
            LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCSRATE.DATA.CONTRACT_NO;
            LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = LNCSRATE.DATA.SUB_CTA_NO;
            LNCICTLM.FUNC = '4';
            S000_CALL_LNZICTLM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "55555555");
            CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.CUR_RAT);
            LNCSRATE.DATA.INT_RAT_N_Y = LNCICTLM.REC_DATA.CUR_RAT;
            LNCSRATE.DATA.INT_RAT_O_Y = LNCICTLM.REC_DATA.CUR_PO_RAT;
            LNCSRATE.DATA.INT_RAT_L_Y = LNCICTLM.REC_DATA.CUR_IO_RAT;
            LNCSRATE.DATA.INT_RAT_P_Y = LNCICTLM.REC_DATA.CUR_FO_RAT;
            B005_RATE_TO_YEAR();
            if (pgmRtn) return;
            if (LNCSRATE.DATA.INT_RAT_N != 0 
                && LNCICTLM.REC_DATA.CUR_RAT_DT <= LNCSRATE.DATA.RAT_CHG_DT) {
                CEP.TRC(SCCGWA, "N");
                LNCICTLM.REC_DATA.CUR_RAT = WS_YEAR_RATE_N;
                LNCICTLM.REC_DATA.CUR_RAT_DT = LNCSRATE.DATA.RAT_CHG_DT;
                CEP.TRC(SCCGWA, "7777777");
                CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.CUR_RAT);
                LNCSRATE.DATA.INT_RAT_N_Y = WS_YEAR_RATE_N;
            }
            if (LNCSRATE.DATA.INT_RAT_O != 0 
                && LNCICTLM.REC_DATA.CUR_PO_RAT_DT <= LNCSRATE.DATA.RAT_CHG_DT) {
                CEP.TRC(SCCGWA, "O");
                LNCICTLM.REC_DATA.CUR_PO_RAT = WS_YEAR_RATE_O;
                LNCICTLM.REC_DATA.CUR_PO_RAT_DT = LNCSRATE.DATA.RAT_CHG_DT;
                LNCSRATE.DATA.INT_RAT_O_Y = WS_YEAR_RATE_O;
            }
            if (LNCSRATE.DATA.INT_RAT_L != 0 
                && LNCSRATE.DATA.CPND_USE == 'N' 
                && LNCICTLM.REC_DATA.CUR_IO_RAT_DT <= LNCSRATE.DATA.RAT_CHG_DT) {
                CEP.TRC(SCCGWA, "L1");
                LNCICTLM.REC_DATA.CUR_IO_RAT = WS_YEAR_RATE_L;
                LNCICTLM.REC_DATA.CUR_IO_RAT_DT = LNCSRATE.DATA.RAT_CHG_DT;
                LNCSRATE.DATA.INT_RAT_L_Y = WS_YEAR_RATE_L;
            }
            if (LNCSRATE.DATA.INT_RAT_O != 0 
                && LNCSRATE.DATA.CPND_USE == K_USE_CPND 
                && LNCICTLM.REC_DATA.CUR_IO_RAT_DT <= LNCSRATE.DATA.RAT_CHG_DT) {
                CEP.TRC(SCCGWA, "L2");
                LNCICTLM.REC_DATA.CUR_IO_RAT = WS_YEAR_RATE_O;
                LNCICTLM.REC_DATA.CUR_IO_RAT_DT = LNCSRATE.DATA.RAT_CHG_DT;
                LNCSRATE.DATA.INT_RAT_L_Y = WS_YEAR_RATE_O;
            }
            if (LNCSRATE.DATA.INT_RAT_P != 0 
                && LNCICTLM.REC_DATA.CUR_FO_RAT_DT <= LNCSRATE.DATA.RAT_CHG_DT) {
                CEP.TRC(SCCGWA, "P");
                LNCICTLM.REC_DATA.CUR_FO_RAT = WS_YEAR_RATE_P;
                LNCICTLM.REC_DATA.CUR_FO_RAT_DT = LNCSRATE.DATA.RAT_CHG_DT;
                LNCSRATE.DATA.INT_RAT_P_Y = WS_YEAR_RATE_P;
            }
            LNCICTLM.FUNC = '2';
            CEP.TRC(SCCGWA, "8888888");
            CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.CUR_RAT);
            S000_CALL_LNZICTLM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            if (!JIBS_tmp_str[0].equalsIgnoreCase("0132000")) {
                R000_SEND_MESSAGE();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, "6666666");
            CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.CUR_RAT);
        }
    }
    public void B003_UPD_ICTL_RATE1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCSRATE.DATA.CONTRACT_NO;
        LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = LNCSRATE.DATA.SUB_CTA_NO;
        LNCICTLM.FUNC = '4';
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        if (LNCICTLM.REC_DATA.CUR_RAT_DT == LNCSRATE.DATA.RAT_CHG_DT 
            && (WS_DEL_TYP[1-1] == 'N' 
            || LNCSRATE.OPE_RATE == 'N')) {
            IBS.init(SCCGWA, LNRRATH);
            LNRRATH.KEY.CONTRACT_NO = LNCSRATE.DATA.CONTRACT_NO;
            LNRRATH.KEY.SUB_CTA_NO = LNCSRATE.DATA.SUB_CTA_NO;
            LNRRATH.KEY.RATE_TYP = K_RATE_N;
            LNRRATH.KEY.RAT_CHG_DT = LNCSRATE.DATA.RAT_CHG_DT;
            T00_READ_RATH1();
            if (pgmRtn) return;
            WS_INT_RATE = 0;
            WS_INT_RATE_Y = 0;
            WS_RATE_TYP = ' ';
            WS_INT_RATE = LNRRATH.INT_RAT;
            WS_RATE_TYP = LNRRATH.RATE_KIND;
            B009_RATE_TO_YEAR1();
            if (pgmRtn) return;
            LNCICTLM.REC_DATA.CUR_RAT = WS_INT_RATE_Y;
            LNCICTLM.REC_DATA.CUR_RAT_DT = LNRRATH.KEY.RAT_CHG_DT;
        }
        if (LNCICTLM.REC_DATA.CUR_PO_RAT_DT == LNCSRATE.DATA.RAT_CHG_DT 
            && (WS_DEL_TYP[2-1] == 'O' 
            || LNCSRATE.OPE_RATE == 'O')) {
            IBS.init(SCCGWA, LNRRATH);
            LNRRATH.KEY.CONTRACT_NO = LNCSRATE.DATA.CONTRACT_NO;
            LNRRATH.KEY.SUB_CTA_NO = LNCSRATE.DATA.SUB_CTA_NO;
            LNRRATH.KEY.RATE_TYP = K_RATE_O;
            LNRRATH.KEY.RAT_CHG_DT = LNCSRATE.DATA.RAT_CHG_DT;
            T00_READ_RATH1();
            if (pgmRtn) return;
            WS_INT_RATE = 0;
            WS_INT_RATE_Y = 0;
            WS_RATE_TYP = ' ';
            WS_INT_RATE = LNRRATH.INT_RAT;
            WS_RATE_TYP = LNRRATH.RATE_KIND;
            B009_RATE_TO_YEAR1();
            if (pgmRtn) return;
            LNCICTLM.REC_DATA.CUR_PO_RAT = WS_INT_RATE_Y;
            LNCICTLM.REC_DATA.CUR_PO_RAT_DT = LNRRATH.KEY.RAT_CHG_DT;
        }
        if (LNCICTLM.REC_DATA.CUR_IO_RAT_DT == LNCSRATE.DATA.RAT_CHG_DT 
            && (WS_DEL_TYP[3-1] == 'L' 
            || LNCSRATE.OPE_RATE == 'L')) {
            IBS.init(SCCGWA, LNRRATH);
            LNRRATH.KEY.CONTRACT_NO = LNCSRATE.DATA.CONTRACT_NO;
            LNRRATH.KEY.SUB_CTA_NO = LNCSRATE.DATA.SUB_CTA_NO;
            LNRRATH.KEY.RATE_TYP = K_RATE_L;
            LNRRATH.KEY.RAT_CHG_DT = LNCSRATE.DATA.RAT_CHG_DT;
            T00_READ_RATH1();
            if (pgmRtn) return;
            WS_INT_RATE = 0;
            WS_INT_RATE_Y = 0;
            WS_RATE_TYP = ' ';
            WS_INT_RATE = LNRRATH.INT_RAT;
            WS_RATE_TYP = LNRRATH.RATE_KIND;
            B009_RATE_TO_YEAR1();
            if (pgmRtn) return;
            LNCICTLM.REC_DATA.CUR_IO_RAT = WS_INT_RATE_Y;
            LNCICTLM.REC_DATA.CUR_IO_RAT_DT = LNRRATH.KEY.RAT_CHG_DT;
        }
        if (LNCICTLM.REC_DATA.CUR_FO_RAT_DT == LNCSRATE.DATA.RAT_CHG_DT 
            && (WS_DEL_TYP[4-1] == 'P' 
            || LNCSRATE.OPE_RATE == 'P')) {
            IBS.init(SCCGWA, LNRRATH);
            LNRRATH.KEY.CONTRACT_NO = LNCSRATE.DATA.CONTRACT_NO;
            LNRRATH.KEY.SUB_CTA_NO = LNCSRATE.DATA.SUB_CTA_NO;
            LNRRATH.KEY.RATE_TYP = K_RATE_P;
            LNRRATH.KEY.RAT_CHG_DT = LNCSRATE.DATA.RAT_CHG_DT;
            T00_READ_RATH1();
            if (pgmRtn) return;
            WS_INT_RATE = 0;
            WS_INT_RATE_Y = 0;
            WS_RATE_TYP = ' ';
            WS_INT_RATE = LNRRATH.INT_RAT;
            WS_RATE_TYP = LNRRATH.RATE_KIND;
            B009_RATE_TO_YEAR1();
            if (pgmRtn) return;
            LNCICTLM.REC_DATA.CUR_FO_RAT = WS_INT_RATE_Y;
            LNCICTLM.REC_DATA.CUR_FO_RAT_DT = LNRRATH.KEY.RAT_CHG_DT;
        }
        LNCICTLM.FUNC = '2';
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
    }
    public void B004_FUNC_UPD() throws IOException,SQLException,Exception {
        if (LNCSRATE.DATA.RATE_KIND_N != ' ') {
            IBS.init(SCCGWA, LNRRATH);
            LNRRATH.KEY.CONTRACT_NO = LNCSRATE.DATA.CONTRACT_NO;
            LNRRATH.KEY.SUB_CTA_NO = LNCSRATE.DATA.SUB_CTA_NO;
            LNRRATH.KEY.RATE_TYP = K_RATE_N;
            LNRRATH.KEY.RAT_CHG_DT = LNCSRATE.DATA.RAT_CHG_DT;
            T00_READUPDATE_LNTRATH();
            if (pgmRtn) return;
            if (WS_COND_FLG.WS_FOUND_FLG == 'Y') {
                LNCSRATEO.DATA.CONTRACT_NO = LNCSRATE.DATA.CONTRACT_NO;
                LNCSRATEO.DATA.SUB_CTA_NO = LNCSRATE.DATA.SUB_CTA_NO;
                LNCSRATEO.DATA.RAT_CHG_DT = LNRRATH.KEY.RAT_CHG_DT;
                LNCSRATEO.DATA.RATE_KIND_N = LNRRATH.RATE_KIND;
                LNCSRATEO.DATA.INT_RAT_N = LNRRATH.INT_RAT;
                CEP.TRC(SCCGWA, "N");
                LNRRATH.RATE_KIND = LNCSRATE.DATA.RATE_KIND_N;
                LNRRATH.INT_RAT = LNCSRATE.DATA.INT_RAT_N;
                T00_REWRITE_LNTRATH();
                if (pgmRtn) return;
            } else {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_RATE_N_RATH_NOTFND, LNCSRATE.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            LNCRATNM.FUNC = '4';
            LNCRATNM.REC_DATA.KEY.CONTRACT_NO = LNCSRATE.DATA.CONTRACT_NO;
            LNCRATNM.REC_DATA.KEY.ACTV_DT = LNCSRATE.DATA.RAT_CHG_DT;
            S000_CALL_LNZRATNM();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                LNCRATNM.FUNC = '2';
                LNCRATNM.REC_DATA.VARIATION_METHOD = LNCSRATE.DATA.FLT_MTH_N;
                LNCRATNM.REC_DATA.INT_RATE_TYPE1 = LNCSRATE.DATA.RATE_TYP_N;
                LNCRATNM.REC_DATA.INT_RATE_CLAS1 = LNCSRATE.DATA.RATEPERD_N;
                LNCRATNM.REC_DATA.RATE_VARIATION1 = LNCSRATE.DATA.RATE_VAR_N;
                LNCRATNM.REC_DATA.RATE_PECT1 = LNCSRATE.DATA.RATE_PCT_N;
                LNCRATNM.REC_DATA.ALL_IN_RATE = LNCSRATE.DATA.RATE_INT_N;
                S000_CALL_LNZRATNM();
                if (pgmRtn) return;
            } else {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_RATE_N_RATH_NOTFND, LNCSRATE.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (LNCSRATE.DATA.RATE_KIND_O != ' ') {
            IBS.init(SCCGWA, LNRRATH);
            LNRRATH.KEY.CONTRACT_NO = LNCSRATE.DATA.CONTRACT_NO;
            LNRRATH.KEY.SUB_CTA_NO = LNCSRATE.DATA.SUB_CTA_NO;
            LNRRATH.KEY.RATE_TYP = K_RATE_O;
            LNRRATH.KEY.RAT_CHG_DT = LNCSRATE.DATA.RAT_CHG_DT;
            T00_READUPDATE_LNTRATH();
            if (pgmRtn) return;
            if (WS_COND_FLG.WS_FOUND_FLG == 'Y') {
                LNCSRATEO.DATA.CONTRACT_NO = LNCSRATE.DATA.CONTRACT_NO;
                LNCSRATEO.DATA.SUB_CTA_NO = LNCSRATE.DATA.SUB_CTA_NO;
                LNCSRATEO.DATA.RAT_CHG_DT = LNRRATH.KEY.RAT_CHG_DT;
                LNCSRATEO.DATA.RATE_KIND_O = LNRRATH.RATE_KIND;
                LNCSRATEO.DATA.INT_RAT_O = LNRRATH.INT_RAT;
                CEP.TRC(SCCGWA, "O");
                LNRRATH.RATE_KIND = LNCSRATE.DATA.RATE_KIND_O;
                LNRRATH.INT_RAT = LNCSRATE.DATA.INT_RAT_O;
                T00_REWRITE_LNTRATH();
                if (pgmRtn) return;
            } else {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_RATE_O_RATH_NOTFND, LNCSRATE.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            LNCRATLM.FUNC = '4';
            LNCRATLM.REC_DATA.KEY.CONTRACT_NO = LNCSRATE.DATA.CONTRACT_NO;
            LNCRATLM.REC_DATA.KEY.ACTV_DT = LNCSRATE.DATA.RAT_CHG_DT;
            LNCRATLM.REC_DATA.KEY.OVD_KND = 'O';
            S000_CALL_LNZRATLM();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                LNCRATLM.FUNC = '2';
                LNCRATLM.REC_DATA.INT_CHRG_MTH = LNCSRATE.DATA.PEN_RRAT_O;
                LNCRATLM.REC_DATA.EFF_RAT = LNCSRATE.DATA.INT_RAT_O;
                LNCRATLM.REC_DATA.RATE_TYPE = LNCSRATE.DATA.PEN_RATT_O;
                LNCRATLM.REC_DATA.RATE_CLAS = LNCSRATE.DATA.PEN_RATC_O;
                LNCRATLM.REC_DATA.DIF_IRAT_PER = LNCSRATE.DATA.PEN_PCT_O;
                LNCRATLM.REC_DATA.DIF_IRAT_PNT = LNCSRATE.DATA.PEN_SPR_O;
                LNCRATLM.REC_DATA.VARIATION_METHOD = LNCSRATE.DATA.PEN_TYP_O;
                S000_CALL_LNZRATLM();
                if (pgmRtn) return;
            } else {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_RATE_O_RATH_NOTFND, LNCSRATE.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (LNCSRATE.DATA.RATE_KIND_P != ' ') {
            IBS.init(SCCGWA, LNRRATH);
            LNRRATH.KEY.CONTRACT_NO = LNCSRATE.DATA.CONTRACT_NO;
            LNRRATH.KEY.SUB_CTA_NO = LNCSRATE.DATA.SUB_CTA_NO;
            LNRRATH.KEY.RATE_TYP = K_RATE_P;
            LNRRATH.KEY.RAT_CHG_DT = LNCSRATE.DATA.RAT_CHG_DT;
            T00_READUPDATE_LNTRATH();
            if (pgmRtn) return;
            if (WS_COND_FLG.WS_FOUND_FLG == 'Y') {
                LNCSRATEO.DATA.CONTRACT_NO = LNCSRATE.DATA.CONTRACT_NO;
                LNCSRATEO.DATA.SUB_CTA_NO = LNCSRATE.DATA.SUB_CTA_NO;
                LNCSRATEO.DATA.RAT_CHG_DT = LNRRATH.KEY.RAT_CHG_DT;
                LNCSRATEO.DATA.RATE_KIND_P = LNRRATH.RATE_KIND;
                LNCSRATEO.DATA.INT_RAT_P = LNRRATH.INT_RAT;
                CEP.TRC(SCCGWA, "P");
                LNRRATH.RATE_KIND = LNCSRATE.DATA.RATE_KIND_P;
                LNRRATH.INT_RAT = LNCSRATE.DATA.INT_RAT_P;
                T00_REWRITE_LNTRATH();
                if (pgmRtn) return;
            } else {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_RATE_P_RATH_NOTFND, LNCSRATE.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            LNCRATLM.FUNC = '4';
            LNCRATLM.REC_DATA.KEY.CONTRACT_NO = LNCSRATE.DATA.CONTRACT_NO;
            LNCRATLM.REC_DATA.KEY.ACTV_DT = LNCSRATE.DATA.RAT_CHG_DT;
            LNCRATLM.REC_DATA.KEY.OVD_KND = 'P';
            LNCRATLM.REC_DATA.INT_CHRG_MTH = LNCSRATE.DATA.ABUS_RAT_P;
            LNCRATLM.REC_DATA.EFF_RAT = LNCSRATE.DATA.INT_RAT_P;
            LNCRATLM.REC_DATA.RATE_TYPE = LNCSRATE.DATA.ABUSRATT_P;
            LNCRATLM.REC_DATA.RATE_CLAS = LNCSRATE.DATA.ABUSRATC_P;
            LNCRATLM.REC_DATA.DIF_IRAT_PER = LNCSRATE.DATA.ABUSPCT_P;
            LNCRATLM.REC_DATA.DIF_IRAT_PNT = LNCSRATE.DATA.ABUSSPR_P;
            LNCRATLM.REC_DATA.VARIATION_METHOD = LNCSRATE.DATA.ABUS_TYP_P;
            S000_CALL_LNZRATLM();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                LNCRATLM.FUNC = '2';
                S000_CALL_LNZRATLM();
                if (pgmRtn) return;
            } else {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_RATE_O_RATH_NOTFND, LNCSRATE.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (LNCSRATE.DATA.CPND_USE == K_USE_CPND) {
            IBS.init(SCCGWA, LNRRATH);
            LNRRATH.KEY.CONTRACT_NO = LNCSRATE.DATA.CONTRACT_NO;
            LNRRATH.KEY.SUB_CTA_NO = LNCSRATE.DATA.SUB_CTA_NO;
            LNRRATH.KEY.RATE_TYP = K_RATE_L;
            LNRRATH.KEY.RAT_CHG_DT = LNCSRATE.DATA.RAT_CHG_DT;
            T00_READUPDATE_LNTRATH();
            if (pgmRtn) return;
            if (WS_COND_FLG.WS_FOUND_FLG == 'Y') {
                LNCSRATEO.DATA.CONTRACT_NO = LNCSRATE.DATA.CONTRACT_NO;
                LNCSRATEO.DATA.SUB_CTA_NO = LNCSRATE.DATA.SUB_CTA_NO;
                LNCSRATEO.DATA.RAT_CHG_DT = LNRRATH.KEY.RAT_CHG_DT;
                LNCSRATEO.DATA.RATE_KIND_L = LNRRATH.RATE_KIND;
                LNCSRATEO.DATA.INT_RAT_L = LNRRATH.INT_RAT;
                CEP.TRC(SCCGWA, "L1");
                LNRRATH.RATE_KIND = LNCSRATE.DATA.RATE_KIND_O;
                LNRRATH.INT_RAT = LNCSRATE.DATA.INT_RAT_O;
                T00_REWRITE_LNTRATH();
                if (pgmRtn) return;
            } else {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_RATE_L_RATH_NOTFND, LNCSRATE.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            LNCRATLM.FUNC = '4';
            LNCRATLM.REC_DATA.KEY.CONTRACT_NO = LNCSRATE.DATA.CONTRACT_NO;
            LNCRATLM.REC_DATA.KEY.ACTV_DT = LNCSRATE.DATA.RAT_CHG_DT;
            LNCRATLM.REC_DATA.KEY.OVD_KND = 'L';
            S000_CALL_LNZRATLM();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                LNCRATLM.FUNC = '2';
                LNCRATLM.REC_DATA.INT_CHRG_MTH = LNCSRATE.DATA.PEN_RRAT_O;
                LNCRATLM.REC_DATA.EFF_RAT = LNCSRATE.DATA.INT_RAT_O;
                LNCRATLM.REC_DATA.RATE_TYPE = LNCSRATE.DATA.PEN_RATT_O;
                LNCRATLM.REC_DATA.RATE_CLAS = LNCSRATE.DATA.PEN_RATC_O;
                LNCRATLM.REC_DATA.DIF_IRAT_PER = LNCSRATE.DATA.PEN_PCT_O;
                LNCRATLM.REC_DATA.DIF_IRAT_PNT = LNCSRATE.DATA.PEN_SPR_O;
                LNCRATLM.REC_DATA.VARIATION_METHOD = LNCSRATE.DATA.PEN_TYP_O;
                S000_CALL_LNZRATLM();
                if (pgmRtn) return;
            } else {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_RATE_L_RATH_NOTFND, LNCSRATE.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
            if (LNCSRATE.DATA.RATE_KIND_L != ' ') {
                IBS.init(SCCGWA, LNRRATH);
                LNRRATH.KEY.CONTRACT_NO = LNCSRATE.DATA.CONTRACT_NO;
                LNRRATH.KEY.SUB_CTA_NO = LNCSRATE.DATA.SUB_CTA_NO;
                LNRRATH.KEY.RATE_TYP = K_RATE_L;
                LNRRATH.KEY.RAT_CHG_DT = LNCSRATE.DATA.RAT_CHG_DT;
                T00_READUPDATE_LNTRATH();
                if (pgmRtn) return;
                if (WS_COND_FLG.WS_FOUND_FLG == 'Y') {
                    LNCSRATEO.DATA.CONTRACT_NO = LNCSRATE.DATA.CONTRACT_NO;
                    LNCSRATEO.DATA.SUB_CTA_NO = LNCSRATE.DATA.SUB_CTA_NO;
                    LNCSRATEO.DATA.RAT_CHG_DT = LNRRATH.KEY.RAT_CHG_DT;
                    LNCSRATEO.DATA.RATE_KIND_L = LNRRATH.RATE_KIND;
                    LNCSRATEO.DATA.INT_RAT_L = LNRRATH.INT_RAT;
                    CEP.TRC(SCCGWA, "L2");
                    LNRRATH.RATE_KIND = LNCSRATE.DATA.RATE_KIND_L;
                    LNRRATH.INT_RAT = LNCSRATE.DATA.INT_RAT_L;
                    T00_REWRITE_LNTRATH();
                    if (pgmRtn) return;
                } else {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_RATE_L_RATH_NOTFND, LNCSRATE.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                LNCRATLM.FUNC = '4';
                LNCRATLM.REC_DATA.KEY.CONTRACT_NO = LNCSRATE.DATA.CONTRACT_NO;
                LNCRATLM.REC_DATA.KEY.ACTV_DT = LNCSRATE.DATA.RAT_CHG_DT;
                LNCRATLM.REC_DATA.KEY.OVD_KND = 'L';
                S000_CALL_LNZRATLM();
                if (pgmRtn) return;
                if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                    LNCRATLM.FUNC = '2';
                    LNCRATLM.REC_DATA.INT_CHRG_MTH = LNCSRATE.DATA.CPND_RTY_L;
                    LNCRATLM.REC_DATA.EFF_RAT = LNCSRATE.DATA.INT_RAT_L;
                    LNCRATLM.REC_DATA.RATE_TYPE = LNCSRATE.DATA.CPNDRATT_L;
                    LNCRATLM.REC_DATA.RATE_CLAS = LNCSRATE.DATA.CPNDRATC_L;
                    LNCRATLM.REC_DATA.DIF_IRAT_PER = LNCSRATE.DATA.CPND_PCT_L;
                    LNCRATLM.REC_DATA.DIF_IRAT_PNT = LNCSRATE.DATA.CPND_SPR_L;
                    LNCRATLM.REC_DATA.VARIATION_METHOD = LNCSRATE.DATA.CPND_TYP_L;
                    S000_CALL_LNZRATLM();
                    if (pgmRtn) return;
                } else {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_RATE_L_RATH_NOTFND, LNCSRATE.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B005_RATE_TO_YEAR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCSRATE.DATA.INT_RAT_N);
        if (LNCSRATE.DATA.INT_RAT_N != 0) {
            WS_YEAR_RATE_N = LNCSRATE.DATA.INT_RAT_N;
            CEP.TRC(SCCGWA, LNCSRATE.DATA.RATE_KIND_N);
            if (LNCSRATE.DATA.RATE_KIND_N == K_TYP_MONTH) {
                WS_YEAR_RATE_N = LNCSRATE.DATA.INT_RAT_N * 12 / 10;
            }
            if (LNCSRATE.DATA.RATE_KIND_N == K_TYP_DAY) {
                WS_YEAR_RATE_N = LNCSRATE.DATA.INT_RAT_N * 360 / 100;
            }
        }
        if (LNCSRATE.DATA.INT_RAT_O != 0) {
            WS_YEAR_RATE_O = LNCSRATE.DATA.INT_RAT_O;
            if (LNCSRATE.DATA.RATE_KIND_O == K_TYP_MONTH) {
                WS_YEAR_RATE_O = LNCSRATE.DATA.INT_RAT_O * 12 / 10;
            }
            if (LNCSRATE.DATA.RATE_KIND_O == K_TYP_DAY) {
                WS_YEAR_RATE_O = LNCSRATE.DATA.INT_RAT_O * 360 / 100;
            }
        }
        if (LNCSRATE.DATA.INT_RAT_L != 0) {
            WS_YEAR_RATE_L = LNCSRATE.DATA.INT_RAT_L;
            if (LNCSRATE.DATA.RATE_KIND_L == K_TYP_MONTH) {
                WS_YEAR_RATE_L = LNCSRATE.DATA.INT_RAT_L * 12 / 10;
            }
            if (LNCSRATE.DATA.RATE_KIND_L == K_TYP_DAY) {
                WS_YEAR_RATE_L = LNCSRATE.DATA.INT_RAT_L * 360 / 100;
            }
        }
        if (LNCSRATE.DATA.INT_RAT_P != 0) {
            WS_YEAR_RATE_P = LNCSRATE.DATA.INT_RAT_P;
            if (LNCSRATE.DATA.RATE_KIND_P == K_TYP_MONTH) {
                WS_YEAR_RATE_P = LNCSRATE.DATA.INT_RAT_P * 12 / 10;
            }
            if (LNCSRATE.DATA.RATE_KIND_P == K_TYP_DAY) {
                WS_YEAR_RATE_P = LNCSRATE.DATA.INT_RAT_P * 360 / 100;
            }
        }
    }
    public void B006_WRITE_NFIA_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        if (LNCSRATE.FUNC_TYP == 'A' 
            || LNCSRATE.FUNC_TYP == 'M') {
            IBS.CLONE(SCCGWA, LNCSRATE, LNCSRATEN);
        }
        if (LNCSRATE.FUNC_TYP == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
        }
        if (LNCSRATE.FUNC_TYP == 'M') {
            BPCPNHIS.INFO.TX_TYP = 'M';
        }
        if (LNCSRATE.FUNC_TYP == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
        }
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        BPCPNHIS.INFO.AC = LNCSRATE.DATA.CONTRACT_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_RMKS;
        BPCPNHIS.INFO.FMT_ID = "LNCSRATE";
        BPCPNHIS.INFO.FMT_ID_LEN = 387;
        BPCPNHIS.INFO.OLD_DAT_PT = LNCSRATEO;
        BPCPNHIS.INFO.NEW_DAT_PT = LNCSRATEN;
        BPCPNHIS.INFO.TX_CD = "0136000";
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        CEP.TRC(SCCGWA, "START WRITE HIS");
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B007_PUTOUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT);
        WS_OUT.WS_CONT_NO = LNCSRATE.DATA.CONTRACT_NO;
        WS_OUT.WS_VAL_DT = LNCSRATE.DATA.RAT_CHG_DT;
        WS_OUT.WS_INT_RATT_N = LNCSRATE.DATA.INT_RATT_N;
        WS_OUT.WS_FLT_MTH_N = LNCSRATE.DATA.FLT_MTH_N;
        WS_OUT.WS_RATE_TYP_N = LNCSRATE.DATA.RATE_TYP_N;
        WS_OUT.WS_RATEPERD_N = LNCSRATE.DATA.RATEPERD_N;
        WS_OUT.WS_RATE_VAR_N = LNCSRATE.DATA.RATE_VAR_N;
        WS_OUT.WS_RATE_PCT_N = LNCSRATE.DATA.RATE_PCT_N;
        WS_OUT.WS_RATE_INT_N = LNCSRATE.DATA.RATE_INT_N;
        WS_OUT.WS_INT_RAT_N = LNCSRATE.DATA.INT_RAT_N;
        WS_OUT.WS_PEN_RRAT_O = LNCSRATE.DATA.PEN_RRAT_O;
        WS_OUT.WS_PEN_TYP_O = LNCSRATE.DATA.PEN_TYP_O;
        WS_OUT.WS_PEN_RATT_O = LNCSRATE.DATA.PEN_RATT_O;
        WS_OUT.WS_PEN_RATC_O = LNCSRATE.DATA.PEN_RATC_O;
        WS_OUT.WS_PEN_SPR_O = LNCSRATE.DATA.PEN_SPR_O;
        WS_OUT.WS_PEN_PCT_O = LNCSRATE.DATA.PEN_PCT_O;
        WS_OUT.WS_INT_RAT_O = LNCSRATE.DATA.INT_RAT_O;
        WS_OUT.WS_INT_MTH = LNCSRATE.DATA.INT_MTH;
        WS_OUT.WS_CPND_USE = LNCSRATE.DATA.CPND_USE;
        WS_OUT.WS_CPND_RTY_L = LNCSRATE.DATA.CPND_RTY_L;
        WS_OUT.WS_CPND_TYP_L = LNCSRATE.DATA.CPND_TYP_L;
        WS_OUT.WS_CPNDRATT_L = LNCSRATE.DATA.CPNDRATT_L;
        WS_OUT.WS_CPNDRATC_L = LNCSRATE.DATA.CPNDRATC_L;
        WS_OUT.WS_CPND_SPR_L = LNCSRATE.DATA.CPND_SPR_L;
        WS_OUT.WS_CPND_PCT_L = LNCSRATE.DATA.CPND_PCT_L;
        WS_OUT.WS_INT_RAT_L = LNCSRATE.DATA.INT_RAT_L;
        WS_OUT.WS_ABUS_RAT_P = LNCSRATE.DATA.ABUS_RAT_P;
        WS_OUT.WS_ABUS_TYP_P = LNCSRATE.DATA.ABUS_TYP_P;
        WS_OUT.WS_ABUSRATT_P = LNCSRATE.DATA.ABUSRATT_P;
        WS_OUT.WS_ABUSRATC_P = LNCSRATE.DATA.ABUSRATC_P;
        WS_OUT.WS_ABUSSPR_P = LNCSRATE.DATA.ABUSSPR_P;
        WS_OUT.WS_ABUSPCT_P = LNCSRATE.DATA.ABUSPCT_P;
        WS_OUT.WS_INT_RAT_P = LNCSRATE.DATA.INT_RAT_P;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "LN600";
        SCCFMT.DATA_PTR = WS_OUT;
        SCCFMT.DATA_LEN = 219;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B008_FUNC_DEL() throws IOException,SQLException,Exception {
        if (LNCSRATE.OPE_RATE == ' ') {
            for (WS_I = 1; WS_I <= 4; WS_I += 1) {
                IBS.init(SCCGWA, LNRRATH);
                if (WS_I == 1) {
                    WS_RATE_TYP = K_RATE_N;
                } else if (WS_I == 2) {
                    WS_RATE_TYP = K_RATE_O;
                } else if (WS_I == 3) {
                    WS_RATE_TYP = K_RATE_L;
                } else if (WS_I == 4) {
                    WS_RATE_TYP = K_RATE_P;
                }
                LNRRATH.KEY.CONTRACT_NO = LNCSRATE.DATA.CONTRACT_NO;
                LNRRATH.KEY.SUB_CTA_NO = LNCSRATE.DATA.SUB_CTA_NO;
                LNRRATH.KEY.RATE_TYP = WS_RATE_TYP;
                LNRRATH.KEY.RAT_CHG_DT = LNCSRATE.DATA.RAT_CHG_DT;
                T00_READUPDATE_LNTRATH();
                if (pgmRtn) return;
                if (WS_COND_FLG.WS_FOUND_FLG == 'Y') {
                    if (WS_I == 1) {
                        WS_DEL_TYP[1-1] = 'N';
                        LNCSRATEO.DATA.INT_RAT_N = LNRRATH.INT_RAT;
                        LNCSRATEO.DATA.RATE_KIND_N = LNRRATH.RATE_KIND;
                    } else if (WS_I == 2) {
                        WS_DEL_TYP[2-1] = 'O';
                        LNCSRATEO.DATA.INT_RAT_O = LNRRATH.INT_RAT;
                        LNCSRATEO.DATA.RATE_KIND_O = LNRRATH.RATE_KIND;
                    } else if (WS_I == 3) {
                        WS_DEL_TYP[3-1] = 'L';
                        LNCSRATEO.DATA.INT_RAT_L = LNRRATH.INT_RAT;
                        LNCSRATEO.DATA.RATE_KIND_L = LNRRATH.RATE_KIND;
                    } else if (WS_I == 4) {
                        WS_DEL_TYP[4-1] = 'P';
                        LNCSRATEO.DATA.INT_RAT_P = LNRRATH.INT_RAT;
                        LNCSRATEO.DATA.RATE_KIND_P = LNRRATH.RATE_KIND;
                    }
                    LNCSRATEO.DATA.CONTRACT_NO = LNCSRATE.DATA.CONTRACT_NO;
                    LNCSRATEO.DATA.SUB_CTA_NO = LNCSRATE.DATA.SUB_CTA_NO;
                    LNCSRATEO.DATA.RAT_CHG_DT = LNRRATH.KEY.RAT_CHG_DT;
                    T00_DELETE_LNTRATH();
                    if (pgmRtn) return;
                }
            }
        } else {
            IBS.init(SCCGWA, LNRRATH);
            LNRRATH.KEY.CONTRACT_NO = LNCSRATE.DATA.CONTRACT_NO;
            LNRRATH.KEY.SUB_CTA_NO = LNCSRATE.DATA.SUB_CTA_NO;
            LNRRATH.KEY.RATE_TYP = LNCSRATE.OPE_RATE;
            LNRRATH.KEY.RAT_CHG_DT = LNCSRATE.DATA.RAT_CHG_DT;
            T00_READUPDATE_LNTRATH();
            if (pgmRtn) return;
            if (WS_COND_FLG.WS_FOUND_FLG != 'Y') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_REC_NOTFND, LNCSRATE.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (LNCSRATE.OPE_RATE == 'N') {
                LNCSRATEO.DATA.INT_RAT_N = LNRRATH.INT_RAT;
                LNCSRATEO.DATA.RATE_KIND_N = LNRRATH.RATE_KIND;
            } else if (LNCSRATE.OPE_RATE == 'O') {
                LNCSRATEO.DATA.INT_RAT_O = LNRRATH.INT_RAT;
                LNCSRATEO.DATA.RATE_KIND_O = LNRRATH.RATE_KIND;
            } else if (LNCSRATE.OPE_RATE == 'L') {
                LNCSRATEO.DATA.INT_RAT_L = LNRRATH.INT_RAT;
                LNCSRATEO.DATA.RATE_KIND_L = LNRRATH.RATE_KIND;
            } else if (LNCSRATE.OPE_RATE == 'P') {
                LNCSRATEO.DATA.INT_RAT_P = LNRRATH.INT_RAT;
                LNCSRATEO.DATA.RATE_KIND_P = LNRRATH.RATE_KIND;
            }
            LNCSRATEO.DATA.CONTRACT_NO = LNCSRATE.DATA.CONTRACT_NO;
            LNCSRATEO.DATA.SUB_CTA_NO = LNCSRATE.DATA.SUB_CTA_NO;
            LNCSRATEO.DATA.RAT_CHG_DT = LNRRATH.KEY.RAT_CHG_DT;
            T00_DELETE_LNTRATH();
            if (pgmRtn) return;
        }
    }
    public void B009_RATE_TO_YEAR1() throws IOException,SQLException,Exception {
        WS_INT_RATE_Y = WS_INT_RATE;
        if (WS_RATE_TYP == K_TYP_MONTH) {
            WS_INT_RATE_Y = WS_INT_RATE * 12 / 10;
        }
        if (WS_RATE_TYP == K_TYP_DAY) {
            WS_INT_RATE_Y = WS_INT_RATE * 360 / 100;
        }
    }
    public void B100_READ_LNTRATH1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRATH);
        LNRRATH.KEY.CONTRACT_NO = LNCSRATE.DATA.CONTRACT_NO;
        LNRRATH.KEY.SUB_CTA_NO = LNCSRATE.DATA.SUB_CTA_NO;
        LNRRATH.KEY.RATE_TYP = WS_RATE_TYP;
        LNRRATH.KEY.RAT_CHG_DT = LNCSRATE.DATA.RAT_CHG_DT;
        T00_READ_LNTRATH1();
        if (pgmRtn) return;
    }
    public void B101_RCAL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRCAL);
        LNCRCAL.COMM_DATA.FUNC_CODE = 'U';
        LNCRCAL.COMM_DATA.FUNC_TYPE = ' ';
        LNCRCAL.COMM_DATA.LN_AC = LNCSRATE.DATA.CONTRACT_NO;
        LNCRCAL.COMM_DATA.SUF_NO = "" + LNCSRATE.DATA.SUB_CTA_NO;
        JIBS_tmp_int = LNCRCAL.COMM_DATA.SUF_NO.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) LNCRCAL.COMM_DATA.SUF_NO = "0" + LNCRCAL.COMM_DATA.SUF_NO;
        LNCRCAL.COMM_DATA.VAL_DATE = LNCSRATE.DATA.RAT_CHG_DT;
        S000_CALL_LNZRCAL();
        if (pgmRtn) return;
        WS_ICAL_FLG = 'Y';
    }
    public void B102_CAL_LOAN_RATE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CONT_BOOK_BR);
        CEP.TRC(SCCGWA, WS_LOAN_CCY);
        CEP.TRC(SCCGWA, LNCSRATE.DATA.INT_MTH);
        CEP.TRC(SCCGWA, LNCSRATE.DATA.RATE_TYP_N);
        CEP.TRC(SCCGWA, K_RATE_N);
        CEP.TRC(SCCGWA, LNCSRATE.DATA.FLT_MTH_N);
        CEP.TRC(SCCGWA, LNCSRATE.DATA.RATE_TYP_N);
        CEP.TRC(SCCGWA, LNCSRATE.DATA.RATEPERD_N);
        CEP.TRC(SCCGWA, LNCSRATE.DATA.RATE_VAR_N);
        CEP.TRC(SCCGWA, LNCSRATE.DATA.RATE_PCT_N);
        CEP.TRC(SCCGWA, LNCSRATE.DATA.RATE_INT_N);
        CEP.TRC(SCCGWA, LNCSRATE.DATA.INT_RAT_N);
        IBS.init(SCCGWA, LNCFRATE);
        LNCFRATE.COMM_DATA.BOOK_BR = WS_CONT_BOOK_BR;
        LNCFRATE.COMM_DATA.BOOK_CCY = WS_LOAN_CCY;
        LNCFRATE.COMM_DATA.INT_MTH = LNCSRATE.DATA.INT_MTH;
        if (LNCSRATE.DATA.RATE_TYP_N.trim().length() > 0) {
            if (LNCFRATE.COMM_DATA.CALF_CD == null) LNCFRATE.COMM_DATA.CALF_CD = "";
            JIBS_tmp_int = LNCFRATE.COMM_DATA.CALF_CD.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) LNCFRATE.COMM_DATA.CALF_CD += " ";
            JIBS_tmp_str[0] = "" + K_RATE_N;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            LNCFRATE.COMM_DATA.CALF_CD = JIBS_tmp_str[0] + LNCFRATE.COMM_DATA.CALF_CD.substring(1);
            LNCFRATE.COMM_DATA.FLT_MTH = LNCSRATE.DATA.FLT_MTH_N;
            LNCFRATE.COMM_DATA.RAT_TYP = LNCSRATE.DATA.RATE_TYP_N;
            LNCFRATE.COMM_DATA.RAT_PD = LNCSRATE.DATA.RATEPERD_N;
            LNCFRATE.COMM_DATA.RAT_VAR = LNCSRATE.DATA.RATE_VAR_N;
            LNCFRATE.COMM_DATA.RAT_PCT = LNCSRATE.DATA.RATE_PCT_N;
            LNCFRATE.COMM_DATA.RAT_INT = LNCSRATE.DATA.RATE_INT_N;
            LNCFRATE.COMM_DATA.IN_RATE = LNCSRATE.DATA.INT_RAT_N;
        }
        if (LNCSRATE.DATA.PEN_RRAT_O != ' ') {
            if (LNCFRATE.COMM_DATA.CALF_CD == null) LNCFRATE.COMM_DATA.CALF_CD = "";
            JIBS_tmp_int = LNCFRATE.COMM_DATA.CALF_CD.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) LNCFRATE.COMM_DATA.CALF_CD += " ";
            JIBS_tmp_str[0] = "" + K_RATE_O;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            LNCFRATE.COMM_DATA.CALF_CD = LNCFRATE.COMM_DATA.CALF_CD.substring(0, 2 - 1) + JIBS_tmp_str[0] + LNCFRATE.COMM_DATA.CALF_CD.substring(2 + 1 - 1);
            LNCFRATE.COMM_DATA.PEN_RRAT = LNCSRATE.DATA.PEN_RRAT_O;
            LNCFRATE.COMM_DATA.PEN_TYP = LNCSRATE.DATA.PEN_TYP_O;
            LNCFRATE.COMM_DATA.PEN_RATT = LNCSRATE.DATA.PEN_RATT_O;
            LNCFRATE.COMM_DATA.PEN_RATC = LNCSRATE.DATA.PEN_RATC_O;
            LNCFRATE.COMM_DATA.PEN_SPR = LNCSRATE.DATA.PEN_SPR_O;
            LNCFRATE.COMM_DATA.PEN_PCT = LNCSRATE.DATA.PEN_PCT_O;
            LNCFRATE.COMM_DATA.PEN_IRAT = LNCSRATE.DATA.INT_RAT_O;
        }
        LNCFRATE.COMM_DATA.INT_MTH = LNCSRATE.DATA.INT_MTH;
        if (LNCSRATE.DATA.CPND_RTY_L != ' ') {
            if (LNCFRATE.COMM_DATA.CALF_CD == null) LNCFRATE.COMM_DATA.CALF_CD = "";
            JIBS_tmp_int = LNCFRATE.COMM_DATA.CALF_CD.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) LNCFRATE.COMM_DATA.CALF_CD += " ";
            JIBS_tmp_str[0] = "" + K_RATE_L;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            LNCFRATE.COMM_DATA.CALF_CD = LNCFRATE.COMM_DATA.CALF_CD.substring(0, 3 - 1) + JIBS_tmp_str[0] + LNCFRATE.COMM_DATA.CALF_CD.substring(3 + 1 - 1);
            LNCFRATE.COMM_DATA.CPND_USE = LNCSRATE.DATA.CPND_USE;
            LNCFRATE.COMM_DATA.CPND_RTY = LNCSRATE.DATA.CPND_RTY_L;
            LNCFRATE.COMM_DATA.CPND_TYP = LNCSRATE.DATA.CPND_TYP_L;
            LNCFRATE.COMM_DATA.CPNDRATT = LNCSRATE.DATA.CPNDRATT_L;
            LNCFRATE.COMM_DATA.CPNDRATC = LNCSRATE.DATA.CPNDRATC_L;
            LNCFRATE.COMM_DATA.CPND_SPR = LNCSRATE.DATA.CPND_SPR_L;
            LNCFRATE.COMM_DATA.CPND_PCT = LNCSRATE.DATA.CPND_PCT_L;
            LNCFRATE.COMM_DATA.CPNDRATE = LNCSRATE.DATA.INT_RAT_L;
        }
        if (LNCSRATE.DATA.ABUS_RAT_P != ' ') {
            if (LNCFRATE.COMM_DATA.CALF_CD == null) LNCFRATE.COMM_DATA.CALF_CD = "";
            JIBS_tmp_int = LNCFRATE.COMM_DATA.CALF_CD.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) LNCFRATE.COMM_DATA.CALF_CD += " ";
            JIBS_tmp_str[0] = "" + K_RATE_P;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            LNCFRATE.COMM_DATA.CALF_CD = LNCFRATE.COMM_DATA.CALF_CD.substring(0, 4 - 1) + JIBS_tmp_str[0] + LNCFRATE.COMM_DATA.CALF_CD.substring(4 + 1 - 1);
            LNCFRATE.COMM_DATA.ABUS_RAT = LNCSRATE.DATA.ABUS_RAT_P;
            LNCFRATE.COMM_DATA.ABUSRATT = LNCSRATE.DATA.ABUSRATT_P;
            LNCFRATE.COMM_DATA.ABUS_TYP = LNCSRATE.DATA.ABUS_TYP_P;
            LNCFRATE.COMM_DATA.ABUSRATC = LNCSRATE.DATA.ABUSRATC_P;
            LNCFRATE.COMM_DATA.ABUSSPR = LNCSRATE.DATA.ABUSSPR_P;
            LNCFRATE.COMM_DATA.ABUSPCT = LNCSRATE.DATA.ABUSPCT_P;
            LNCFRATE.COMM_DATA.ABUSIRAT = LNCSRATE.DATA.INT_RAT_P;
        }
        if (!SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("300600")) {
            S000_CALL_LNZFRATE();
            if (pgmRtn) return;
        } else {
            LNCFRATE.COMM_DATA.IN_RATE = LNCSRATE.DATA.INT_RAT_N;
        }
        LNCSRATE.DATA.INT_RAT_N = LNCFRATE.COMM_DATA.IN_RATE;
        LNCSRATE.DATA.INT_RAT_O = LNCFRATE.COMM_DATA.PEN_IRAT;
        LNCSRATE.DATA.INT_RAT_L = LNCFRATE.COMM_DATA.CPNDRATE;
        LNCSRATE.DATA.INT_RAT_P = LNCFRATE.COMM_DATA.ABUSIRAT;
        CEP.TRC(SCCGWA, LNCSRATE.DATA.INT_RAT_N);
        CEP.TRC(SCCGWA, LNCSRATE.DATA.INT_RAT_O);
        CEP.TRC(SCCGWA, LNCSRATE.DATA.INT_RAT_L);
        CEP.TRC(SCCGWA, LNCSRATE.DATA.INT_RAT_P);
    }
    public void R000_RECA_COMA() throws IOException,SQLException,Exception {
        if (LNRRATH.KEY.RATE_TYP == K_RATE_N) {
            CEP.TRC(SCCGWA, "N");
            LNCSRATE.QUERY_DATA.Q_DT_N = LNRRATH.KEY.RAT_CHG_DT;
            WS_OUT.WS_VAL_DT = LNRRATH.KEY.RAT_CHG_DT;
            LNCSRATE.QUERY_DATA.Q_KIND_N = LNRRATH.RATE_KIND;
            LNCSRATE.QUERY_DATA.Q_RAT_N = LNRRATH.INT_RAT;
        } else if (LNRRATH.KEY.RATE_TYP == K_RATE_O) {
            CEP.TRC(SCCGWA, "O");
            LNCSRATE.QUERY_DATA.Q_DT_O = LNRRATH.KEY.RAT_CHG_DT;
            LNCSRATE.QUERY_DATA.Q_KIND_O = LNRRATH.RATE_KIND;
            LNCSRATE.QUERY_DATA.Q_RAT_O = LNRRATH.INT_RAT;
            if (LNCSRATE.QUERY_DATA.Q_DT_O > WS_OUT.WS_VAL_DT) {
                WS_OUT.WS_VAL_DT = LNCSRATE.QUERY_DATA.Q_DT_O;
            }
        } else if (LNRRATH.KEY.RATE_TYP == K_RATE_L) {
            CEP.TRC(SCCGWA, "L");
            LNCSRATE.QUERY_DATA.Q_DT_L = LNRRATH.KEY.RAT_CHG_DT;
            LNCSRATE.QUERY_DATA.Q_KIND_L = LNRRATH.RATE_KIND;
            LNCSRATE.QUERY_DATA.Q_RAT_L = LNRRATH.INT_RAT;
            if (LNCSRATE.QUERY_DATA.Q_DT_L > WS_OUT.WS_VAL_DT) {
                WS_OUT.WS_VAL_DT = LNCSRATE.QUERY_DATA.Q_DT_L;
            }
        } else if (LNRRATH.KEY.RATE_TYP == K_RATE_P) {
            CEP.TRC(SCCGWA, "P");
            LNCSRATE.QUERY_DATA.Q_DT_P = LNRRATH.KEY.RAT_CHG_DT;
            LNCSRATE.QUERY_DATA.Q_KIND_P = LNRRATH.RATE_KIND;
            LNCSRATE.QUERY_DATA.Q_RAT_P = LNRRATH.INT_RAT;
            if (LNCSRATE.QUERY_DATA.Q_DT_P > WS_OUT.WS_VAL_DT) {
                WS_OUT.WS_VAL_DT = LNCSRATE.QUERY_DATA.Q_DT_P;
            }
        }
    }
    public void T00_READ_LNTRATH1() throws IOException,SQLException,Exception {
        LNTRATH_RD = new DBParm();
        LNTRATH_RD.TableName = "LNTRATH";
        LNTRATH_RD.where = "CONTRACT_NO = :LNRRATH.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRRATH.KEY.SUB_CTA_NO "
            + "AND RATE_TYP = :LNRRATH.KEY.RATE_TYP "
            + "AND RAT_CHG_DT <= :LNRRATH.KEY.RAT_CHG_DT";
        LNTRATH_RD.fst = true;
        LNTRATH_RD.order = "RAT_CHG_DT DESC";
        IBS.READ(SCCGWA, LNRRATH, this, LNTRATH_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        WS_COND_FLG.WS_FOUND_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.WS_FOUND_FLG = 'Y';
        }
    }
    public void S000_CALL_LNZSIQIF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-INQ-INF", LNCSIQIF, true);
        if (LNCSIQIF.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, LNCSIQIF.RC);
        }
    }
    public void T00_READUPDATE_LNTRATH() throws IOException,SQLException,Exception {
        LNTRATH_RD = new DBParm();
        LNTRATH_RD.TableName = "LNTRATH";
        LNTRATH_RD.upd = true;
        IBS.READ(SCCGWA, LNRRATH, LNTRATH_RD);
        WS_COND_FLG.WS_FOUND_FLG = ' ';
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.WS_FOUND_FLG = 'Y';
        } else {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_COND_FLG.WS_FOUND_FLG = 'N';
            } else {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DBIO, LNCSRATE.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void T00_REWRITE_LNTRATH() throws IOException,SQLException,Exception {
        LNRRATH.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRRATH.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTRATH_RD = new DBParm();
        LNTRATH_RD.TableName = "LNTRATH";
        IBS.REWRITE(SCCGWA, LNRRATH, LNTRATH_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T00_WRITE_LNTRATH() throws IOException,SQLException,Exception {
        LNRRATH.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRRATH.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRRATH.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRRATH.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTRATH_RD = new DBParm();
        LNTRATH_RD.TableName = "LNTRATH";
        LNTRATH_RD.errhdl = true;
        IBS.WRITE(SCCGWA, LNRRATH, LNTRATH_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_RATE_EXISTED, LNCSRATE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T00_DELETE_LNTRATH() throws IOException,SQLException,Exception {
        LNTRATH_RD = new DBParm();
        LNTRATH_RD.TableName = "LNTRATH";
        IBS.DELETE(SCCGWA, LNRRATH, LNTRATH_RD);
    }
    public void T00_READ_RATH1() throws IOException,SQLException,Exception {
        LNTRATH_RD = new DBParm();
        LNTRATH_RD.TableName = "LNTRATH";
        LNTRATH_RD.where = "CONTRACT_NO = :LNRRATH.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRRATH.KEY.SUB_CTA_NO "
            + "AND RATE_TYP = :LNRRATH.KEY.RATE_TYP "
            + "AND RAT_CHG_DT < :LNRRATH.KEY.RAT_CHG_DT";
        LNTRATH_RD.fst = true;
        LNTRATH_RD.order = "RAT_CHG_DT DESC";
        IBS.READ(SCCGWA, LNRRATH, this, LNTRATH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_REC_NOTFND, LNCSRATE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTRATN() throws IOException,SQLException,Exception {
        LNTRATN_RD = new DBParm();
        LNTRATN_RD.TableName = "LNTRATN";
        LNTRATN_RD.where = "CONTRACT_NO = :LNCRATNM.REC_DATA.KEY.CONTRACT_NO "
            + "AND ACTV_DT < :LNCRATNM.REC_DATA.KEY.ACTV_DT";
        LNTRATN_RD.fst = true;
        IBS.READ(SCCGWA, LNRRATN, this, LNTRATN_RD);
    }
    public void S000_CALL_LNZICTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-ICTL-MAINT", LNCICTLM);
        if (LNCICTLM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCICTLM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRATE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZPCFTA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-P-CHK-FTA-TYP", LNCPCFTA);
        if (LNCPCFTA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCPCFTA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRATE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZFRATE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-F-RATE-CAL", LNCFRATE);
        if (LNCFRATE.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCFRATE.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRATE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRATE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCONTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-CONT-MAINT", LNCCONTM);
        if (LNCCONTM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCONTM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRATE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRCAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-REV-CAL-BASE", LNCRCAL);
        if (LNCRCAL.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRCAL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRATE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-INT-CAL", LNCICAL);
        if (LNCICAL.RC.RC_RTNCODE != 0) {
            IBS.CPY2CLS(SCCGWA, LNCICAL.RC.RC_APP, LNCSRATE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZPLAJ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-PLPI-AUTADJ", LNCPLAJ);
        if (LNCPLAJ.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCPLAJ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRATE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZAPRDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-APRD-MAINT", LNCAPRDM);
        CEP.TRC(SCCGWA, LNCAPRDM.RC.RC_RTNCODE);
        if (LNCAPRDM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCAPRDM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRATE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZCINTI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-C-INTR-INQ", BPCCINTI);
        CEP.TRC(SCCGWA, BPCCINTI.RC);
    }
    public void S000_CALL_LNZFCPL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CAL-PLPI", LNCFCPL);
        if (LNCFCPL.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCFCPL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRATE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRATNM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-RATN-MAINT", LNCRATNM);
        if (LNCRATNM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRATNM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRATE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRATLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-RATL-MAINT", LNCRATLM);
        if (LNCRATLM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRATLM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRATE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_SEND_MESSAGE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCWRMSG);
        SCCWRMSG.DATE = SCCGWA.COMM_AREA.AC_DATE;
        SCCWRMSG.JRNNO = "" + SCCGWA.COMM_AREA.JRN_NO;
        JIBS_tmp_int = SCCWRMSG.JRNNO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) SCCWRMSG.JRNNO = "0" + SCCWRMSG.JRNNO;
        SCCWRMSG.AP_CODE = "25";
        IBS.init(SCCGWA, CICQACAC);
        CEP.TRC(SCCGWA, LNCSRATE.DATA.CONTRACT_NO);
        CICQACAC.DATA.ACAC_NO = LNCSRATE.DATA.CONTRACT_NO;
        CICQACAC.FUNC = 'A';
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO);
        if (CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO.trim().length() > 0) {
            SCCWRMSG.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        }
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.CUR_RAT_DT);
        JIBS_tmp_str[0] = "" + LNCICTLM.REC_DATA.CUR_RAT_DT;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        CEP.TRC(SCCGWA, JIBS_tmp_str[0].substring(0, 2));
        JIBS_tmp_str[0] = "" + LNCICTLM.REC_DATA.CUR_RAT_DT;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        CEP.TRC(SCCGWA, JIBS_tmp_str[0].substring(3 - 1, 3 + 1 - 1));
        JIBS_tmp_str[0] = "" + LNCICTLM.REC_DATA.CUR_RAT_DT;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        CEP.TRC(SCCGWA, JIBS_tmp_str[0].substring(4 - 1, 4 + 1 - 1));
        WS_CURR_DATE = "" + LNCICTLM.REC_DATA.CUR_RAT_DT;
        JIBS_tmp_int = WS_CURR_DATE.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) WS_CURR_DATE = "0" + WS_CURR_DATE;
        CEP.TRC(SCCGWA, WS_CURR_DATE);
        SCCWRMSG.AC = LNCSRATE.DATA.CONTRACT_NO;
        if (WS_CURR_DATE == null) WS_CURR_DATE = "";
        JIBS_tmp_int = WS_CURR_DATE.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) WS_CURR_DATE += " ";
        if (WS_CURR_DATE.substring(0, 4).trim().length() == 0) SCCWRMSG.YEAR = 0;
        else SCCWRMSG.YEAR = Short.parseShort(WS_CURR_DATE.substring(0, 4));
        if (WS_CURR_DATE == null) WS_CURR_DATE = "";
        JIBS_tmp_int = WS_CURR_DATE.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) WS_CURR_DATE += " ";
        if (WS_CURR_DATE.substring(5 - 1, 5 + 2 - 1).trim().length() == 0) SCCWRMSG.MONTH = 0;
        else SCCWRMSG.MONTH = Short.parseShort(WS_CURR_DATE.substring(5 - 1, 5 + 2 - 1));
        if (WS_CURR_DATE == null) WS_CURR_DATE = "";
        JIBS_tmp_int = WS_CURR_DATE.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) WS_CURR_DATE += " ";
        if (WS_CURR_DATE.substring(7 - 1, 7 + 2 - 1).trim().length() == 0) SCCWRMSG.DAY = 0;
        else SCCWRMSG.DAY = Short.parseShort(WS_CURR_DATE.substring(7 - 1, 7 + 2 - 1));
        SCCWRMSG.RATE = LNCICTLM.REC_DATA.CUR_RAT;
        R000_CALL_SCZWRMSG();
        if (pgmRtn) return;
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
    }
    public void R000_CALL_SCZWRMSG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-SCZWRMSG-MMSG", SCCWRMSG, true);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
