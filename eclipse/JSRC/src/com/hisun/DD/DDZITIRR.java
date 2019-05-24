package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.SC.SCCBATH;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.BP.*;
import com.hisun.TD.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZITIRR {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    char WS_FOUND_FLAG = ' ';
    DDZITIRR_WS_TEMP_RATE_INFO[] WS_TEMP_RATE_INFO = new DDZITIRR_WS_TEMP_RATE_INFO[5];
    double WS_OUT_RATE = 0;
    double WS_BAS_RATE = 0;
    short WS_I = 0;
    char WS_HC_FLAG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    BPCCINTI BPCCINTI = new BPCCINTI();
    TDCCRACD TDCCRACD = new TDCCRACD();
    SCCGWA SCCGWA;
    SCCBKPO SCCBKPO;
    SCCBATH SCCBATH;
    DDCITIRR DDCITIRR;
    public DDZITIRR() {
        for (int i=0;i<5;i++) WS_TEMP_RATE_INFO[i] = new DDZITIRR_WS_TEMP_RATE_INFO();
    }
    public void MP(SCCGWA SCCGWA, DDCITIRR DDCITIRR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCITIRR = DDCITIRR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZITIRR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCITIRR.CCY);
        CEP.TRC(SCCGWA, DDCITIRR.RBASE_INFO[1-1].BASE);
        CEP.TRC(SCCGWA, DDCITIRR.RBASE_INFO[1-1].TENOR);
        CEP.TRC(SCCGWA, DDCITIRR.RBASE_INFO[1-1].SPREAD);
        CEP.TRC(SCCGWA, DDCITIRR.RBASE_INFO[2-1].BASE);
        CEP.TRC(SCCGWA, DDCITIRR.RBASE_INFO[2-1].TENOR);
        CEP.TRC(SCCGWA, DDCITIRR.RBASE_INFO[2-1].SPREAD);
        CEP.TRC(SCCGWA, DDCITIRR.RBASE_INFO[3-1].BASE);
        CEP.TRC(SCCGWA, DDCITIRR.RBASE_INFO[3-1].TENOR);
        CEP.TRC(SCCGWA, DDCITIRR.RBASE_INFO[3-1].SPREAD);
        CEP.TRC(SCCGWA, DDCITIRR.RBASE_INFO[4-1].BASE);
        CEP.TRC(SCCGWA, DDCITIRR.RBASE_INFO[4-1].TENOR);
        CEP.TRC(SCCGWA, DDCITIRR.RBASE_INFO[4-1].SPREAD);
        CEP.TRC(SCCGWA, DDCITIRR.RBASE_INFO[5-1].BASE);
        CEP.TRC(SCCGWA, DDCITIRR.RBASE_INFO[5-1].TENOR);
        CEP.TRC(SCCGWA, DDCITIRR.RBASE_INFO[5-1].SPREAD);
        CEP.TRC(SCCGWA, DDCITIRR.HL_FLAG);
        CEP.TRC(SCCGWA, DDCITIRR.FIX_RATE);
        CEP.TRC(SCCGWA, DDCITIRR.MAX_RATE);
        CEP.TRC(SCCGWA, DDCITIRR.MIN_RATE);
        CEP.TRC(SCCGWA, DDCITIRR.AC_DATE);
        CEP.TRC(SCCGWA, DDCITIRR.RUL_CD);
        CEP.TRC(SCCGWA, DDCITIRR.TYPE);
        CEP.TRC(SCCGWA, DDCITIRR.CI_NO);
        SCCBATH = (SCCBATH) SCCGWA.COMM_AREA.BAT_AREA_PTR;
        DDCITIRR.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        DDCITIRR.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_PROC();
        if (pgmRtn) return;
        B200_GET_TIRR_RATE();
        if (pgmRtn) return;
        B300_HIGH_LOW_RATE_PROC();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT_PROC() throws IOException,SQLException,Exception {
        if ((DDCITIRR.RBASE_INFO[1-1].BASE.trim().length() == 0) 
            && (DDCITIRR.RBASE_INFO[2-1].BASE.trim().length() == 0) 
            && (DDCITIRR.RBASE_INFO[3-1].BASE.trim().length() == 0) 
            && (DDCITIRR.RBASE_INFO[4-1].BASE.trim().length() == 0) 
            && (DDCITIRR.RBASE_INFO[5-1].BASE.trim().length() == 0) 
            && (DDCITIRR.FIX_RATE == 0)) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_BASERATE_INVALID, DDCITIRR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DDCITIRR.HL_FLAG == ' ') {
            DDCITIRR.HL_FLAG = 'H';
        }
        if (DDCITIRR.CCY.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_M_INPUT, DDCITIRR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B200_GET_TIRR_RATE() throws IOException,SQLException,Exception {
        if (DDCITIRR.FIX_RATE > 0) {
            DDCITIRR.COMPUTED_RATE = DDCITIRR.FIX_RATE;
            Z_RET();
            if (pgmRtn) return;
        } else {
            for (WS_I = 1; WS_I <= 5; WS_I += 1) {
                if (DDCITIRR.RBASE_INFO[WS_I-1].BASE.trim().length() > 0) {
                    IBS.init(SCCGWA, BPCCINTI);
                    BPCCINTI.FUNC = 'I';
                    if (DDCITIRR.AC_DATE != 0) {
                        BPCCINTI.BASE_INFO.DT = DDCITIRR.AC_DATE;
                    } else {
                        BPCCINTI.BASE_INFO.DT = SCCGWA.COMM_AREA.AC_DATE;
                    }
                    BPCCINTI.BASE_INFO.BR = DDCITIRR.BR;
                    BPCCINTI.BASE_INFO.BASE_TYP = DDCITIRR.RBASE_INFO[WS_I-1].BASE;
                    BPCCINTI.BASE_INFO.TENOR = DDCITIRR.RBASE_INFO[WS_I-1].TENOR;
                    BPCCINTI.BASE_INFO.CCY = DDCITIRR.CCY;
                    S00_CALL_INQUIRE_RATE();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.OWN_RATE);
                    WS_TEMP_RATE_INFO[WS_I-1].WS_TMP_RATE = BPCCINTI.BASE_INFO.OWN_RATE;
                    WS_BAS_RATE = BPCCINTI.BASE_INFO.OWN_RATE;
                    DDCITIRR.RATE_INFO[WS_I-1].RATE = BPCCINTI.BASE_INFO.OWN_RATE;
                    DDCITIRR.RATE_INFO[WS_I-1].N_DT = BPCCINTI.BASE_INFO.N_DT;
                    if (DDCITIRR.TYPE == '1' 
                        && DDCITIRR.RBASE_INFO[WS_I-1].SPREAD != 0) {
                        WS_TEMP_RATE_INFO[WS_I-1].WS_TMP_RATE = WS_TEMP_RATE_INFO[WS_I-1].WS_TMP_RATE + DDCITIRR.RBASE_INFO[WS_I-1].SPREAD;
                    } else {
                        if (DDCITIRR.TYPE == '2' 
                            && DDCITIRR.RBASE_INFO[WS_I-1].SPREAD_PCT != 0) {
                            WS_TEMP_RATE_INFO[WS_I-1].WS_TMP_RATE = WS_TEMP_RATE_INFO[WS_I-1].WS_TMP_RATE + WS_TEMP_RATE_INFO[WS_I-1].WS_TMP_RATE * DDCITIRR.RBASE_INFO[WS_I-1].SPREAD_PCT / 100;
                        }
                    }
                    if (DDCITIRR.TYPE == '3' 
                        && DDCITIRR.RUL_CD.trim().length() > 0) {
                        B201_QUERY_RULE_RATE();
                        if (pgmRtn) return;
                        WS_TEMP_RATE_INFO[WS_I-1].WS_TMP_RATE = TDCCRACD.OUT_INF.RATE;
                    }
                }
            }
        }
    }
    public void B300_HIGH_LOW_RATE_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCITIRR.TYPE);
        CEP.TRC(SCCGWA, WS_TEMP_RATE_INFO[1-1].WS_TMP_RATE);
        CEP.TRC(SCCGWA, WS_TEMP_RATE_INFO[2-1].WS_TMP_RATE);
        CEP.TRC(SCCGWA, WS_TEMP_RATE_INFO[3-1].WS_TMP_RATE);
        CEP.TRC(SCCGWA, WS_TEMP_RATE_INFO[4-1].WS_TMP_RATE);
        CEP.TRC(SCCGWA, WS_TEMP_RATE_INFO[5-1].WS_TMP_RATE);
        WS_OUT_RATE = 0;
        for (WS_I = 1; WS_I <= 5 
            && WS_TEMP_RATE_INFO[WS_I-1].WS_TMP_RATE != 0; WS_I += 1) {
            if (WS_OUT_RATE == 0) {
                if (WS_TEMP_RATE_INFO[WS_I-1].WS_TMP_RATE != 0) {
                    WS_OUT_RATE = WS_TEMP_RATE_INFO[WS_I-1].WS_TMP_RATE;
                }
            } else {
                if (WS_TEMP_RATE_INFO[WS_I-1].WS_TMP_RATE != 0) {
                    if (DDCITIRR.HL_FLAG == 'H') {
                        if (WS_TEMP_RATE_INFO[WS_I-1].WS_TMP_RATE > WS_OUT_RATE) {
                            WS_OUT_RATE = WS_TEMP_RATE_INFO[WS_I-1].WS_TMP_RATE;
                        }
                    } else {
                        if (WS_TEMP_RATE_INFO[WS_I-1].WS_TMP_RATE < WS_OUT_RATE) {
                            WS_OUT_RATE = WS_TEMP_RATE_INFO[WS_I-1].WS_TMP_RATE;
                        }
                    }
                }
            }
        }
        DDCITIRR.COMPUTED_RATE = WS_OUT_RATE;
    }
    public void B201_QUERY_RULE_RATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCCRACD);
        TDCCRACD.ACO_AC = DDCITIRR.AC_NO;
        TDCCRACD.CI_NO = DDCITIRR.CI_NO;
        TDCCRACD.FRM_APP = "DD";
        TDCCRACD.RAT_BAS = WS_BAS_RATE;
        TDCCRACD.RUL_TYP = DDCITIRR.HL_FLAG;
        TDCCRACD.RUL_CD_ALL[1-1].RUL_CD = DDCITIRR.RUL_CD;
        S000_CALL_TDZCRACD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDCCRACD.OUT_INF.RATE);
    }
    public void S000_CALL_TDZCRACD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-CAL-IRAT-CD", TDCCRACD);
    }
    public void S00_CALL_INQUIRE_RATE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-C-INTR-INQ", BPCCINTI);
        CEP.TRC(SCCGWA, BPCCINTI.RC);
        if (BPCCINTI.RC.RC_CODE != 0) {
            BPCCINTI.BASE_INFO.OWN_RATE = 0;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCCINTI.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCITIRR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCITIRR.COMPUTED_RATE);
        for (WS_I = 1; WS_I <= 5 
            && DDCITIRR.RBASE_INFO[WS_I-1].BASE.trim().length() != 0; WS_I += 1) {
            CEP.TRC(SCCGWA, DDCITIRR.RATE_INFO[WS_I-1].RATE);
        }
        CEP.TRC(SCCGWA, DDCITIRR.RC);
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (DDCITIRR.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DDCITIRR=");
            CEP.TRC(SCCGWA, DDCITIRR);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
