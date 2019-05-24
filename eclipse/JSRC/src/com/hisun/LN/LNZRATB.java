package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZRATB {
    String JIBS_tmp_str[] = new String[10];
    DBParm LNTRATH_RD;
    brParm LNTRATH_BR = new brParm();
    boolean pgmRtn = false;
    char K_KND_DAY = 'D';
    char K_KND_MONTH = 'M';
    char K_KND_YEAR = 'Y';
    short K_DBAS_360 = 360;
    short K_MONTHS = 12;
    char LNZRATB_FILLER1 = ' ';
    char WS_STOP_IND = ' ';
    short WS_I = 0;
    short WS_JJJ = 0;
    char WS_B_RATE_KND = ' ';
    double WS_B_RATE = 0;
    char WS_RATE_KND = ' ';
    double WS_RATE = 0;
    double WS_ACCR_RATE = 0;
    int WS_RATE_DATE = 0;
    int WS_NEXT_RATE_DATE = 0;
    int WS_BEG_DATE = 0;
    int WS_END_DATE = 0;
    int WS_GWA_AC_DATE = 0;
    int WS_EMBEZ_STR_DATE = 0;
    int WS_EMBEZ_END_DATE = 0;
    int WS_CMP_STR_DATE = 0;
    int WS_CMP_END_DATE = 0;
    int WS_TEMP_BEG_DATE = 0;
    int WS_TEMP_END_DATE = 0;
    int WS_HIS_BEG_DATE = 0;
    int WS_HIS_END_DATE = 0;
    int WS_IDX = 0;
    int WS_TOTAL = 0;
    char WS_TMP_RATE_KND = ' ';
    double WS_TMP_RATE = 0;
    double WS_TMP_OUT_RATE = 0;
    LNZRATB_WS_P_IETM_RATES[] WS_P_IETM_RATES = new LNZRATB_WS_P_IETM_RATES[20];
    int WS_ITEM_CNT = 20;
    char WS_FOUND_FLG = ' ';
    char WS_P_FOUND_FLG = ' ';
    char WS_END_FLG = ' ';
    char WS_FWD_FLAG = ' ';
    LNRRATH LNRRATH = new LNRRATH();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    LNCCONTM LNCCONTM = new LNCCONTM();
    LNCLOANM LNCLOANM = new LNCLOANM();
    LNCICTLM LNCICTLM = new LNCICTLM();
    SCCGWA SCCGWA;
    LNCRATB LNCRATB;
    SCCBATH SCCBATH;
    public LNZRATB() {
        for (int i=0;i<20;i++) WS_P_IETM_RATES[i] = new LNZRATB_WS_P_IETM_RATES();
    }
    public void MP(SCCGWA SCCGWA, LNCRATB LNCRATB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCRATB = LNCRATB;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        for (WS_JJJ = 1; WS_JJJ <= LNCRATB.COMM_DATA.ITEM_CNT 
            && LNCRATB.COMM_DATA.IETM_RATES[WS_JJJ-1].B_DATE != 0; WS_JJJ += 1) {
            CEP.TRC(SCCGWA, LNCRATB.COMM_DATA.IETM_RATES[WS_JJJ-1].B_DATE);
            CEP.TRC(SCCGWA, LNCRATB.COMM_DATA.IETM_RATES[WS_JJJ-1].E_DATE);
            CEP.TRC(SCCGWA, LNCRATB.COMM_DATA.IETM_RATES[WS_JJJ-1].RATE);
        }
        CEP.TRC(SCCGWA, "LNZRATB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNCRATB.RC.RC_APP = "LN";
        LNCRATB.RC.RC_RTNCODE = 0;
        if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
            SCCBATH = (SCCBATH) SCCGWA.COMM_AREA.BAT_AREA_PTR;
            WS_GWA_AC_DATE = SCCBATH.JPRM.NEXT_AC_DATB;
        } else {
            WS_GWA_AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK();
        if (pgmRtn) return;
        B100_GET_ICTL_INF();
        if (pgmRtn) return;
        B200_MAIN_PROC();
        if (pgmRtn) return;
        B400_ACCR_PROC();
        if (pgmRtn) return;
    }
    public void B000_CHECK() throws IOException,SQLException,Exception {
        if (LNCRATB.COMM_DATA.LN_AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CTA_NO_M_INPUT, LNCRATB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCRATB.COMM_DATA.RATE_TYPE != 'N' 
            && LNCRATB.COMM_DATA.RATE_TYPE != 'O' 
            && LNCRATB.COMM_DATA.RATE_TYPE != 'L' 
            && LNCRATB.COMM_DATA.RATE_TYPE != 'P') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_RATE_TYP, LNCRATB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCRATB.COMM_DATA.BEG_DATE > LNCRATB.COMM_DATA.END_DATE) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_BEG_GE_END_DATE, LNCRATB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B100_GET_ICTL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCAPRDM);
        LNCAPRDM.FUNC = '3';
        LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = LNCRATB.COMM_DATA.LN_AC;
        S000_CALL_LNZAPRDM();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCCONTM);
        LNCCONTM.FUNC = '3';
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCRATB.COMM_DATA.LN_AC;
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCLOANM);
        LNCLOANM.FUNC = '3';
        LNCLOANM.REC_DATA.KEY.CONTRACT_NO = LNCRATB.COMM_DATA.LN_AC;
        S000_CALL_LNZLOANM();
        if (pgmRtn) return;
        WS_EMBEZ_STR_DATE = LNCLOANM.REC_DATA.EMBEZ_DATE;
        WS_EMBEZ_END_DATE = LNCLOANM.REC_DATA.EMBEZ_CANCEL_DATE;
        if (WS_EMBEZ_STR_DATE != 0 
            && WS_EMBEZ_END_DATE == 0) {
            WS_EMBEZ_END_DATE = LNCRATB.COMM_DATA.END_DATE;
        }
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '3';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCRATB.COMM_DATA.LN_AC;
        if (LNCRATB.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCRATB.COMM_DATA.SUF_NO);
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        WS_NEXT_RATE_DATE = 99990101;
        WS_B_RATE_KND = 'Y';
        WS_RATE_KND = 'Y';
        if (LNCRATB.COMM_DATA.RATE_TYPE == 'N') {
            WS_RATE = LNCICTLM.REC_DATA.CUR_RAT;
            WS_B_RATE = LNCICTLM.REC_DATA.CUR_RAT;
            WS_RATE_DATE = LNCICTLM.REC_DATA.CUR_RAT_DT;
            WS_BEG_DATE = LNCICTLM.REC_DATA.CUR_RAT_DT;
        } else if (LNCRATB.COMM_DATA.RATE_TYPE == 'O') {
            WS_RATE = LNCICTLM.REC_DATA.CUR_PO_RAT;
            WS_B_RATE = LNCICTLM.REC_DATA.CUR_PO_RAT;
            WS_RATE_DATE = LNCICTLM.REC_DATA.CUR_PO_RAT_DT;
            WS_BEG_DATE = LNCICTLM.REC_DATA.CUR_PO_RAT_DT;
        } else if (LNCRATB.COMM_DATA.RATE_TYPE == 'L') {
            WS_RATE = LNCICTLM.REC_DATA.CUR_IO_RAT;
            WS_B_RATE = LNCICTLM.REC_DATA.CUR_IO_RAT;
            WS_RATE_DATE = LNCICTLM.REC_DATA.CUR_IO_RAT_DT;
            WS_BEG_DATE = LNCICTLM.REC_DATA.CUR_IO_RAT_DT;
        } else if (LNCRATB.COMM_DATA.RATE_TYPE == 'P') {
            WS_RATE = LNCICTLM.REC_DATA.CUR_FO_RAT;
            WS_B_RATE = LNCICTLM.REC_DATA.CUR_FO_RAT;
            WS_RATE_DATE = LNCICTLM.REC_DATA.CUR_FO_RAT_DT;
            WS_BEG_DATE = LNCICTLM.REC_DATA.CUR_FO_RAT_DT;
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_RATE_TYP, LNCRATB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B200_MAIN_PROC() throws IOException,SQLException,Exception {
        WS_I = 0;
        WS_P_FOUND_FLG = ' ';
        WS_FWD_FLAG = ' ';
        if (LNCRATB.COMM_DATA.BEG_DATE >= WS_GWA_AC_DATE) {
            WS_NEXT_RATE_DATE = LNCRATB.COMM_DATA.BEG_DATE;
            if (WS_EMBEZ_STR_DATE != 0 
                && ((LNCRATB.COMM_DATA.BEG_DATE >= WS_EMBEZ_STR_DATE 
                && LNCRATB.COMM_DATA.BEG_DATE < WS_EMBEZ_END_DATE) 
                || (LNCRATB.COMM_DATA.END_DATE >= WS_EMBEZ_STR_DATE 
                && LNCRATB.COMM_DATA.END_DATE < WS_EMBEZ_END_DATE) 
                || (LNCRATB.COMM_DATA.BEG_DATE <= WS_EMBEZ_STR_DATE 
                && LNCRATB.COMM_DATA.END_DATE >= WS_EMBEZ_END_DATE))) {
                B205_GOT_EMBEZ_INFO();
                if (pgmRtn) return;
                WS_TEMP_BEG_DATE = LNCRATB.COMM_DATA.BEG_DATE;
                WS_TEMP_END_DATE = LNCRATB.COMM_DATA.END_DATE;
                WS_I = 0;
                B280_P_MAIN_PROC();
                if (pgmRtn) return;
            }
            if ((WS_P_FOUND_FLG != 'Y')) {
                WS_FWD_FLAG = 'Y';
                WS_HIS_BEG_DATE = LNCRATB.COMM_DATA.BEG_DATE;
                WS_HIS_END_DATE = LNCRATB.COMM_DATA.END_DATE;
                R100_GET_HIS_RATE();
                if (pgmRtn) return;
            }
        } else {
            if (WS_EMBEZ_STR_DATE != 0 
                && ((LNCRATB.COMM_DATA.BEG_DATE >= WS_EMBEZ_STR_DATE 
                && LNCRATB.COMM_DATA.BEG_DATE < WS_EMBEZ_END_DATE) 
                || (LNCRATB.COMM_DATA.END_DATE >= WS_EMBEZ_STR_DATE 
                && LNCRATB.COMM_DATA.END_DATE < WS_EMBEZ_END_DATE) 
                || (LNCRATB.COMM_DATA.BEG_DATE <= WS_EMBEZ_STR_DATE 
                && LNCRATB.COMM_DATA.END_DATE >= WS_EMBEZ_END_DATE))) {
                B205_GOT_EMBEZ_INFO();
                if (pgmRtn) return;
                WS_I = 0;
                WS_TEMP_BEG_DATE = LNCRATB.COMM_DATA.BEG_DATE;
                WS_TEMP_END_DATE = LNCRATB.COMM_DATA.END_DATE;
                B280_P_MAIN_PROC();
                if (pgmRtn) return;
            }
            if ((WS_P_FOUND_FLG != 'Y')) {
                WS_FWD_FLAG = 'Y';
                WS_HIS_BEG_DATE = LNCRATB.COMM_DATA.BEG_DATE;
                WS_HIS_END_DATE = LNCRATB.COMM_DATA.END_DATE;
                R100_GET_HIS_RATE();
                if (pgmRtn) return;
            }
        }
        LNCRATB.COMM_DATA.ITEM_CNT = WS_I;
        if (LNCRATB.COMM_DATA.ITEM_CNT == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_RATH_NOTFND, LNCRATB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B205_GOT_EMBEZ_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRATH);
        WS_P_FOUND_FLG = 'Y';
        LNRRATH.KEY.RATE_TYP = 'P';
        T00_STARTBR_LNTRATH();
        if (pgmRtn) return;
        T00_READNEXT_LNTRATH();
        if (pgmRtn) return;
        if (WS_EMBEZ_STR_DATE < LNCRATB.COMM_DATA.BEG_DATE) {
            WS_CMP_STR_DATE = LNCRATB.COMM_DATA.BEG_DATE;
        } else {
            WS_CMP_STR_DATE = WS_EMBEZ_STR_DATE;
        }
        if (WS_EMBEZ_END_DATE < LNCRATB.COMM_DATA.END_DATE) {
            WS_CMP_END_DATE = WS_EMBEZ_END_DATE;
        } else {
            WS_CMP_END_DATE = LNCRATB.COMM_DATA.END_DATE;
        }
        while (WS_I < ( WS_ITEM_CNT - 1 ) 
            && WS_FOUND_FLG != 'N' 
            && (LNRRATH.KEY.RAT_CHG_DT < WS_CMP_END_DATE)) {
            if (LNRRATH.KEY.RAT_CHG_DT > WS_CMP_STR_DATE) {
                WS_I += 1;
                if (WS_I == 1) {
                    WS_BEG_DATE = WS_CMP_STR_DATE;
                }
                WS_END_DATE = LNRRATH.KEY.RAT_CHG_DT;
                WS_P_IETM_RATES[WS_I-1].WS_P_B_DATE = WS_BEG_DATE;
                WS_P_IETM_RATES[WS_I-1].WS_P_E_DATE = WS_END_DATE;
                WS_P_IETM_RATES[WS_I-1].WS_P_RATE_KND = WS_B_RATE_KND;
                WS_P_IETM_RATES[WS_I-1].WS_P_RATE = WS_B_RATE;
            }
            WS_B_RATE_KND = LNRRATH.RATE_KIND;
            WS_B_RATE = LNRRATH.INT_RAT;
            WS_BEG_DATE = LNRRATH.KEY.RAT_CHG_DT;
            T00_READNEXT_LNTRATH();
            if (pgmRtn) return;
        }
        WS_I += 1;
        if (WS_I == 1) {
            WS_BEG_DATE = WS_CMP_STR_DATE;
        }
        WS_END_DATE = WS_CMP_END_DATE;
        WS_P_IETM_RATES[WS_I-1].WS_P_B_DATE = WS_BEG_DATE;
        WS_P_IETM_RATES[WS_I-1].WS_P_E_DATE = WS_END_DATE;
        WS_P_IETM_RATES[WS_I-1].WS_P_RATE_KND = WS_B_RATE_KND;
        WS_P_IETM_RATES[WS_I-1].WS_P_RATE = WS_B_RATE;
        T00_ENDBR_LNTRATH();
        if (pgmRtn) return;
        if (WS_I == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_RATH_NOTFND, LNCRATB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (WS_I == WS_ITEM_CNT 
            && WS_P_IETM_RATES[WS_I-1].WS_P_E_DATE != LNCRATB.COMM_DATA.END_DATE) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCRATB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B220_MAIN_PROC() throws IOException,SQLException,Exception {
        WS_STOP_IND = 'N';
        WS_FOUND_FLG = 'N';
        IBS.init(SCCGWA, LNRRATH);
        LNRRATH.KEY.RATE_TYP = LNCRATB.COMM_DATA.RATE_TYPE;
        T00_STARTBR_LNTRATH();
        if (pgmRtn) return;
        T00_READNEXT_LNTRATH();
        if (pgmRtn) return;
        while (WS_I < ( WS_ITEM_CNT - 1 ) 
            && WS_FOUND_FLG != 'N' 
            && (LNRRATH.KEY.RAT_CHG_DT < LNCRATB.COMM_DATA.END_DATE) 
            && (WS_STOP_IND != 'Y')) {
            if (LNRRATH.KEY.RAT_CHG_DT > LNCRATB.COMM_DATA.BEG_DATE) {
                WS_I += 1;
                if (WS_I == 1) {
                    WS_BEG_DATE = LNCRATB.COMM_DATA.BEG_DATE;
                }
                WS_END_DATE = LNRRATH.KEY.RAT_CHG_DT;
                WS_TMP_RATE_KND = WS_B_RATE_KND;
                WS_TMP_RATE = WS_B_RATE;
                R000_GET_YEAR_RATE();
                if (pgmRtn) return;
                LNCRATB.COMM_DATA.IETM_RATES[WS_I-1].B_DATE = WS_BEG_DATE;
                LNCRATB.COMM_DATA.IETM_RATES[WS_I-1].E_DATE = WS_END_DATE;
                LNCRATB.COMM_DATA.IETM_RATES[WS_I-1].RATE = WS_TMP_OUT_RATE;
            }
            WS_B_RATE_KND = LNRRATH.RATE_KIND;
            WS_B_RATE = LNRRATH.INT_RAT;
            WS_BEG_DATE = LNRRATH.KEY.RAT_CHG_DT;
            T00_READNEXT_LNTRATH();
            if (pgmRtn) return;
        }
        WS_I += 1;
        if (WS_I == 1) {
            WS_BEG_DATE = LNCRATB.COMM_DATA.BEG_DATE;
        }
        WS_END_DATE = LNCRATB.COMM_DATA.END_DATE;
        WS_TMP_RATE_KND = WS_B_RATE_KND;
        WS_TMP_RATE = WS_B_RATE;
        R000_GET_YEAR_RATE();
        if (pgmRtn) return;
        LNCRATB.COMM_DATA.IETM_RATES[WS_I-1].B_DATE = WS_BEG_DATE;
        LNCRATB.COMM_DATA.IETM_RATES[WS_I-1].E_DATE = WS_END_DATE;
        LNCRATB.COMM_DATA.IETM_RATES[WS_I-1].RATE = WS_TMP_OUT_RATE;
        T00_ENDBR_LNTRATH();
        if (pgmRtn) return;
        LNCRATB.COMM_DATA.ITEM_CNT = WS_I;
        if (WS_I == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_RATH_NOTFND, LNCRATB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (WS_I == WS_ITEM_CNT 
            && LNCRATB.COMM_DATA.IETM_RATES[WS_I-1].E_DATE != LNCRATB.COMM_DATA.END_DATE) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCRATB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B240_MAIN_PROC() throws IOException,SQLException,Exception {
        if (LNCRATB.COMM_DATA.BEG_DATE < WS_EMBEZ_STR_DATE 
            && LNCRATB.COMM_DATA.END_DATE >= WS_EMBEZ_STR_DATE 
            && LNCRATB.COMM_DATA.END_DATE <= WS_EMBEZ_END_DATE) {
            WS_TEMP_BEG_DATE = LNCRATB.COMM_DATA.BEG_DATE;
            WS_TEMP_END_DATE = WS_EMBEZ_STR_DATE;
            B250_P_MAIN_PROC();
            if (pgmRtn) return;
            WS_FWD_FLAG = 'Y';
            B260_MOVE_P_RATE();
            if (pgmRtn) return;
        }
        if (LNCRATB.COMM_DATA.BEG_DATE < WS_EMBEZ_STR_DATE 
            && LNCRATB.COMM_DATA.END_DATE > WS_EMBEZ_END_DATE) {
            WS_TEMP_BEG_DATE = LNCRATB.COMM_DATA.BEG_DATE;
            WS_TEMP_END_DATE = WS_EMBEZ_STR_DATE;
            B250_P_MAIN_PROC();
            if (pgmRtn) return;
            B260_MOVE_P_RATE();
            if (pgmRtn) return;
            WS_TEMP_BEG_DATE = WS_EMBEZ_END_DATE;
            WS_TEMP_END_DATE = LNCRATB.COMM_DATA.END_DATE;
            B250_P_MAIN_PROC();
            if (pgmRtn) return;
        }
        if (LNCRATB.COMM_DATA.BEG_DATE >= WS_EMBEZ_STR_DATE 
            && LNCRATB.COMM_DATA.END_DATE <= WS_EMBEZ_END_DATE) {
            WS_FWD_FLAG = 'Y';
            B260_MOVE_P_RATE();
            if (pgmRtn) return;
        }
        if (LNCRATB.COMM_DATA.BEG_DATE >= WS_EMBEZ_STR_DATE 
            && LNCRATB.COMM_DATA.BEG_DATE <= WS_EMBEZ_END_DATE 
            && LNCRATB.COMM_DATA.END_DATE > WS_EMBEZ_END_DATE) {
            B260_MOVE_P_RATE();
            if (pgmRtn) return;
            WS_TEMP_BEG_DATE = WS_EMBEZ_END_DATE;
            WS_TEMP_END_DATE = LNCRATB.COMM_DATA.END_DATE;
            B250_P_MAIN_PROC();
            if (pgmRtn) return;
        }
    }
    public void B250_P_MAIN_PROC() throws IOException,SQLException,Exception {
        WS_FOUND_FLG = 'N';
        IBS.init(SCCGWA, LNRRATH);
        WS_TOTAL = 0;
        LNRRATH.KEY.RATE_TYP = LNCRATB.COMM_DATA.RATE_TYPE;
        T00_STARTBR_LNTRATH();
        if (pgmRtn) return;
        T00_READNEXT_LNTRATH();
        if (pgmRtn) return;
        while (WS_I < ( WS_ITEM_CNT - 1 ) 
            && WS_FOUND_FLG != 'N' 
            && (LNRRATH.KEY.RAT_CHG_DT < WS_TEMP_END_DATE)) {
            if (LNRRATH.KEY.RAT_CHG_DT > WS_TEMP_BEG_DATE) {
                WS_I += 1;
                if (WS_TOTAL == 1) {
                    WS_BEG_DATE = WS_TEMP_BEG_DATE;
                }
                WS_END_DATE = LNRRATH.KEY.RAT_CHG_DT;
                WS_TMP_RATE_KND = WS_B_RATE_KND;
                WS_TMP_RATE = WS_B_RATE;
                R000_GET_YEAR_RATE();
                if (pgmRtn) return;
                LNCRATB.COMM_DATA.IETM_RATES[WS_I-1].B_DATE = WS_BEG_DATE;
                LNCRATB.COMM_DATA.IETM_RATES[WS_I-1].E_DATE = WS_END_DATE;
                LNCRATB.COMM_DATA.IETM_RATES[WS_I-1].RATE = WS_TMP_OUT_RATE;
            }
            WS_B_RATE_KND = LNRRATH.RATE_KIND;
            WS_B_RATE = LNRRATH.INT_RAT;
            WS_BEG_DATE = LNRRATH.KEY.RAT_CHG_DT;
            T00_READNEXT_LNTRATH();
            if (pgmRtn) return;
        }
        WS_I += 1;
        if (WS_TOTAL == 1) {
            WS_BEG_DATE = WS_TEMP_BEG_DATE;
        }
        WS_END_DATE = WS_TEMP_END_DATE;
        WS_TMP_RATE_KND = WS_B_RATE_KND;
        WS_TMP_RATE = WS_B_RATE;
        R000_GET_YEAR_RATE();
        if (pgmRtn) return;
        LNCRATB.COMM_DATA.IETM_RATES[WS_I-1].B_DATE = WS_BEG_DATE;
        LNCRATB.COMM_DATA.IETM_RATES[WS_I-1].E_DATE = WS_END_DATE;
        LNCRATB.COMM_DATA.IETM_RATES[WS_I-1].RATE = WS_TMP_OUT_RATE;
        T00_ENDBR_LNTRATH();
        if (pgmRtn) return;
        LNCRATB.COMM_DATA.ITEM_CNT = WS_I;
        if (WS_I == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_RATH_NOTFND, LNCRATB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (WS_I == WS_ITEM_CNT 
            && LNCRATB.COMM_DATA.IETM_RATES[WS_I-1].E_DATE != LNCRATB.COMM_DATA.END_DATE) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCRATB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B280_P_MAIN_PROC() throws IOException,SQLException,Exception {
        if (WS_TEMP_BEG_DATE < WS_EMBEZ_STR_DATE 
            && WS_TEMP_END_DATE >= WS_EMBEZ_STR_DATE 
            && WS_TEMP_END_DATE <= WS_EMBEZ_END_DATE) {
            WS_HIS_BEG_DATE = WS_TEMP_BEG_DATE;
            WS_HIS_END_DATE = WS_EMBEZ_STR_DATE;
            R100_GET_HIS_RATE();
            if (pgmRtn) return;
            WS_FWD_FLAG = 'Y';
            B260_MOVE_P_RATE();
            if (pgmRtn) return;
        }
        if (WS_TEMP_BEG_DATE < WS_EMBEZ_STR_DATE 
            && WS_TEMP_END_DATE > WS_EMBEZ_END_DATE) {
            WS_HIS_BEG_DATE = WS_TEMP_BEG_DATE;
            WS_HIS_END_DATE = WS_EMBEZ_STR_DATE;
            R100_GET_HIS_RATE();
            if (pgmRtn) return;
            B260_MOVE_P_RATE();
            if (pgmRtn) return;
            WS_HIS_BEG_DATE = WS_EMBEZ_END_DATE;
            WS_HIS_END_DATE = WS_TEMP_END_DATE;
            R100_GET_HIS_RATE();
            if (pgmRtn) return;
        }
        if (WS_TEMP_BEG_DATE >= WS_EMBEZ_STR_DATE 
            && WS_TEMP_END_DATE <= WS_EMBEZ_END_DATE) {
            WS_FWD_FLAG = 'Y';
            B260_MOVE_P_RATE();
            if (pgmRtn) return;
        }
        if (WS_TEMP_BEG_DATE >= WS_EMBEZ_STR_DATE 
            && WS_TEMP_BEG_DATE <= WS_EMBEZ_END_DATE 
            && WS_TEMP_END_DATE > WS_EMBEZ_END_DATE) {
            B260_MOVE_P_RATE();
            if (pgmRtn) return;
            WS_HIS_BEG_DATE = WS_EMBEZ_END_DATE;
            WS_HIS_END_DATE = WS_TEMP_END_DATE;
            R100_GET_HIS_RATE();
            if (pgmRtn) return;
        }
    }
    public void B260_MOVE_P_RATE() throws IOException,SQLException,Exception {
        WS_END_FLG = ' ';
        WS_IDX = 1;
        while (WS_END_FLG != 'Y' 
            && WS_I <= WS_ITEM_CNT 
            && WS_IDX <= WS_ITEM_CNT) {
            if (WS_P_IETM_RATES[WS_IDX-1].WS_P_B_DATE == 0 
                && WS_P_IETM_RATES[WS_IDX-1].WS_P_E_DATE == 0 
                && WS_P_IETM_RATES[WS_IDX-1].WS_P_RATE_KND == ' ' 
                && WS_P_IETM_RATES[WS_IDX-1].WS_P_RATE == 0) {
                WS_END_FLG = 'Y';
            } else {
                WS_I += 1;
                WS_TMP_RATE_KND = WS_P_IETM_RATES[WS_IDX-1].WS_P_RATE_KND;
                WS_TMP_RATE = WS_P_IETM_RATES[WS_IDX-1].WS_P_RATE;
                R000_GET_YEAR_RATE();
                if (pgmRtn) return;
                LNCRATB.COMM_DATA.IETM_RATES[WS_I-1].B_DATE = WS_P_IETM_RATES[WS_IDX-1].WS_P_B_DATE;
                LNCRATB.COMM_DATA.IETM_RATES[WS_I-1].E_DATE = WS_P_IETM_RATES[WS_IDX-1].WS_P_E_DATE;
                LNCRATB.COMM_DATA.IETM_RATES[WS_I-1].RATE = WS_TMP_OUT_RATE;
            }
            WS_IDX += 1;
        }
    }
    public void B300_FWD_RATE_PROC() throws IOException,SQLException,Exception {
        if ((LNCRATB.COMM_DATA.ITEM_CNT < WS_ITEM_CNT) 
            && (LNCRATB.COMM_DATA.END_DATE > WS_GWA_AC_DATE)) {
            WS_I = (short) LNCRATB.COMM_DATA.ITEM_CNT;
            WS_B_RATE_KND = 'Y';
            WS_B_RATE = LNCRATB.COMM_DATA.IETM_RATES[LNCRATB.COMM_DATA.ITEM_CNT-1].RATE;
            WS_BEG_DATE = LNCRATB.COMM_DATA.IETM_RATES[LNCRATB.COMM_DATA.ITEM_CNT-1].B_DATE;
            if (LNCRATB.COMM_DATA.RATE_TYPE == 'N') {
                B310_FWD_N_RATE_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B400_ACCR_PROC() throws IOException,SQLException,Exception {
        LNCRATB.COMM_DATA.AVER_RATE = 0;
        for (WS_JJJ = 1; WS_JJJ <= LNCRATB.COMM_DATA.ITEM_CNT; WS_JJJ += 1) {
            if (WS_JJJ == LNCRATB.COMM_DATA.ITEM_CNT) {
                LNCRATB.COMM_DATA.AVER_RATE = LNCRATB.COMM_DATA.IETM_RATES[WS_JJJ-1].RATE;
            }
        }
    }
    public void B310_FWD_N_RATE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRATH);
        LNRRATH.KEY.CONTRACT_NO = LNCRATB.COMM_DATA.LN_AC;
        LNRRATH.KEY.RAT_CHG_DT = WS_BEG_DATE;
        LNRRATH.KEY.RATE_TYP = LNCRATB.COMM_DATA.RATE_TYPE;
        T00_READ_LNTRATH_FWD();
        if (pgmRtn) return;
        if (LNRRATH.KEY.RAT_CHG_DT > SCCGWA.COMM_AREA.AC_DATE 
            && LNRRATH.KEY.RAT_CHG_DT > LNCICTLM.REC_DATA.CUR_RAT_DT) {
            WS_TMP_RATE_KND = LNRRATH.RATE_KIND;
            WS_TMP_RATE = LNRRATH.INT_RAT;
            R000_GET_YEAR_RATE();
            if (pgmRtn) return;
            WS_B_RATE_KND = 'Y';
            WS_B_RATE = WS_TMP_OUT_RATE;
        }
        WS_FOUND_FLG = 'N';
        T00_STARTBR_LNTRATH_FWD();
        if (pgmRtn) return;
        T00_READNEXT_LNTRATH();
        if (pgmRtn) return;
        while ((WS_I < WS_ITEM_CNT) 
            && WS_FOUND_FLG != 'N' 
            && (LNRRATH.KEY.RAT_CHG_DT < LNCRATB.COMM_DATA.END_DATE)) {
            WS_TMP_RATE_KND = LNRRATH.RATE_KIND;
            WS_TMP_RATE = LNRRATH.INT_RAT;
            R000_GET_YEAR_RATE();
            if (pgmRtn) return;
            if (WS_TMP_OUT_RATE != WS_B_RATE) {
                WS_END_DATE = LNRRATH.KEY.RAT_CHG_DT;
                LNCRATB.COMM_DATA.IETM_RATES[WS_I-1].B_DATE = WS_BEG_DATE;
                LNCRATB.COMM_DATA.IETM_RATES[WS_I-1].E_DATE = WS_END_DATE;
                LNCRATB.COMM_DATA.IETM_RATES[WS_I-1].RATE = WS_B_RATE;
                WS_I += 1;
                WS_BEG_DATE = LNRRATH.KEY.RAT_CHG_DT;
                WS_B_RATE = WS_TMP_OUT_RATE;
            }
            T00_READNEXT_LNTRATH();
            if (pgmRtn) return;
        }
        WS_END_DATE = LNCRATB.COMM_DATA.END_DATE;
        LNCRATB.COMM_DATA.IETM_RATES[WS_I-1].B_DATE = WS_BEG_DATE;
        LNCRATB.COMM_DATA.IETM_RATES[WS_I-1].E_DATE = WS_END_DATE;
        LNCRATB.COMM_DATA.IETM_RATES[WS_I-1].RATE = WS_B_RATE;
        T00_ENDBR_LNTRATH();
        if (pgmRtn) return;
        LNCRATB.COMM_DATA.ITEM_CNT = WS_I;
    }
    public void R000_GET_YEAR_RATE() throws IOException,SQLException,Exception {
        WS_TMP_OUT_RATE = 0;
        WS_TMP_RATE_KND = 'Y';
        if (WS_TMP_RATE_KND == K_KND_DAY) {
            WS_TMP_OUT_RATE = WS_TMP_RATE * K_DBAS_360 / 100;
        } else if (WS_TMP_RATE_KND == K_KND_MONTH) {
            WS_TMP_OUT_RATE = WS_TMP_RATE * K_MONTHS / 10;
        } else if (WS_TMP_RATE_KND == K_KND_YEAR) {
            WS_TMP_OUT_RATE = WS_TMP_RATE;
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_RATE_TYP, LNCRATB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R100_GET_HIS_RATE() throws IOException,SQLException,Exception {
        if (LNCRATB.COMM_DATA.RATE_TYPE != 'N') {
            if (WS_HIS_BEG_DATE >= WS_GWA_AC_DATE) {
                WS_I += 1;
                WS_TMP_RATE_KND = WS_RATE_KND;
                WS_TMP_RATE = WS_RATE;
                R000_GET_YEAR_RATE();
                if (pgmRtn) return;
                LNCRATB.COMM_DATA.IETM_RATES[WS_I-1].B_DATE = WS_HIS_BEG_DATE;
                LNCRATB.COMM_DATA.IETM_RATES[WS_I-1].E_DATE = WS_HIS_END_DATE;
                LNCRATB.COMM_DATA.IETM_RATES[WS_I-1].RATE = WS_TMP_OUT_RATE;
            }
            if (WS_HIS_BEG_DATE < WS_GWA_AC_DATE 
                && WS_HIS_END_DATE <= WS_GWA_AC_DATE) {
                R110_GET_HIS_RATE_DETAIL();
                if (pgmRtn) return;
            }
            if (WS_HIS_BEG_DATE < WS_GWA_AC_DATE 
                && WS_HIS_END_DATE > WS_GWA_AC_DATE) {
                WS_HIS_END_DATE = WS_GWA_AC_DATE;
                R110_GET_HIS_RATE_DETAIL();
                if (pgmRtn) return;
            }
        } else {
            R110_GET_HIS_RATE_DETAIL();
            if (pgmRtn) return;
        }
    }
    public void R110_GET_HIS_RATE_DETAIL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRATH);
        WS_FOUND_FLG = 'N';
        LNRRATH.KEY.RATE_TYP = LNCRATB.COMM_DATA.RATE_TYPE;
        WS_TOTAL = 1;
        T00_STARTBR_LNTRATH();
        if (pgmRtn) return;
        T00_READNEXT_LNTRATH();
        if (pgmRtn) return;
        while (WS_I < ( WS_ITEM_CNT - 1 ) 
            && WS_FOUND_FLG != 'N' 
            && (LNRRATH.KEY.RAT_CHG_DT < WS_HIS_END_DATE)) {
            if (LNRRATH.KEY.RAT_CHG_DT > WS_HIS_BEG_DATE) {
                WS_I += 1;
                if (WS_TOTAL == 1) {
                    WS_BEG_DATE = WS_HIS_BEG_DATE;
                }
                WS_END_DATE = LNRRATH.KEY.RAT_CHG_DT;
                WS_TMP_RATE_KND = WS_B_RATE_KND;
                WS_TMP_RATE = WS_B_RATE;
                R000_GET_YEAR_RATE();
                if (pgmRtn) return;
                LNCRATB.COMM_DATA.IETM_RATES[WS_I-1].B_DATE = WS_BEG_DATE;
                LNCRATB.COMM_DATA.IETM_RATES[WS_I-1].E_DATE = WS_END_DATE;
                LNCRATB.COMM_DATA.IETM_RATES[WS_I-1].RATE = WS_TMP_OUT_RATE;
                WS_TOTAL += 1;
            }
            WS_B_RATE_KND = LNRRATH.RATE_KIND;
            WS_B_RATE = LNRRATH.INT_RAT;
            WS_BEG_DATE = LNRRATH.KEY.RAT_CHG_DT;
            T00_READNEXT_LNTRATH();
            if (pgmRtn) return;
        }
        WS_I += 1;
        if (WS_TOTAL == 1) {
            WS_BEG_DATE = WS_HIS_BEG_DATE;
        }
        WS_END_DATE = WS_HIS_END_DATE;
        WS_TMP_RATE_KND = WS_B_RATE_KND;
        WS_TMP_RATE = WS_B_RATE;
        R000_GET_YEAR_RATE();
        if (pgmRtn) return;
        LNCRATB.COMM_DATA.IETM_RATES[WS_I-1].B_DATE = WS_BEG_DATE;
        LNCRATB.COMM_DATA.IETM_RATES[WS_I-1].E_DATE = WS_END_DATE;
        LNCRATB.COMM_DATA.IETM_RATES[WS_I-1].RATE = WS_TMP_OUT_RATE;
        T00_ENDBR_LNTRATH();
        if (pgmRtn) return;
        if (WS_I == WS_ITEM_CNT 
            && LNCRATB.COMM_DATA.IETM_RATES[WS_I-1].E_DATE != WS_HIS_END_DATE) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCRATB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZAPRDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-APRD-MAINT", LNCAPRDM);
        if (LNCAPRDM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCAPRDM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCRATB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCONTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-CONT-MAINT", LNCCONTM);
        if (LNCCONTM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCONTM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCRATB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZLOANM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-LOAN-MAINT", LNCLOANM);
        if (LNCLOANM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCLOANM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCRATB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-ICTL-MAINT", LNCICTLM);
        if (LNCICTLM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCICTLM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCRATB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T00_READ_LNTRATH_FWD() throws IOException,SQLException,Exception {
        LNTRATH_RD = new DBParm();
        LNTRATH_RD.TableName = "LNTRATH";
        LNTRATH_RD.where = "CONTRACT_NO = :LNRRATH.KEY.CONTRACT_NO "
            + "AND RATE_TYP = :LNRRATH.KEY.RATE_TYP "
            + "AND RAT_CHG_DT <= :LNRRATH.KEY.RAT_CHG_DT";
        LNTRATH_RD.fst = true;
        LNTRATH_RD.order = "RAT_CHG_DT DESC";
        IBS.READ(SCCGWA, LNRRATH, this, LNTRATH_RD);
    }
    public void T00_STARTBR_LNTRATH_FWD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRATH);
        LNRRATH.KEY.CONTRACT_NO = LNCRATB.COMM_DATA.LN_AC;
        LNRRATH.KEY.RAT_CHG_DT = WS_BEG_DATE;
        LNRRATH.KEY.RATE_TYP = 'N';
        LNTRATH_BR.rp = new DBParm();
        LNTRATH_BR.rp.TableName = "LNTRATH";
        LNTRATH_BR.rp.where = "CONTRACT_NO = :LNRRATH.KEY.CONTRACT_NO "
            + "AND RATE_TYP = :LNRRATH.KEY.RATE_TYP "
            + "AND RAT_CHG_DT > :LNRRATH.KEY.RAT_CHG_DT";
        LNTRATH_BR.rp.order = "RAT_CHG_DT";
        IBS.STARTBR(SCCGWA, LNRRATH, this, LNTRATH_BR);
    }
    public void T00_STARTBR_LNTRATH() throws IOException,SQLException,Exception {
        LNRRATH.KEY.CONTRACT_NO = LNCRATB.COMM_DATA.LN_AC;
        if (LNCRATB.COMM_DATA.SUF_NO.trim().length() == 0) LNRRATH.KEY.SUB_CTA_NO = 0;
        else LNRRATH.KEY.SUB_CTA_NO = Integer.parseInt(LNCRATB.COMM_DATA.SUF_NO);
        LNRRATH.KEY.RAT_CHG_DT = 0;
        LNTRATH_BR.rp = new DBParm();
        LNTRATH_BR.rp.TableName = "LNTRATH";
        LNTRATH_BR.rp.where = "CONTRACT_NO = :LNRRATH.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRRATH.KEY.SUB_CTA_NO "
            + "AND RATE_TYP = :LNRRATH.KEY.RATE_TYP "
            + "AND RAT_CHG_DT >= :LNRRATH.KEY.RAT_CHG_DT";
        LNTRATH_BR.rp.order = "RAT_CHG_DT";
        IBS.STARTBR(SCCGWA, LNRRATH, this, LNTRATH_BR);
    }
    public void T00_ENDBR_LNTRATH() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTRATH_BR);
    }
    public void T00_READNEXT_LNTRATH() throws IOException,SQLException,Exception {
        WS_FOUND_FLG = ' ';
        IBS.READNEXT(SCCGWA, LNRRATH, this, LNTRATH_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        } else {
            WS_FOUND_FLG = 'N';
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCRATB.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, LNCRATB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
