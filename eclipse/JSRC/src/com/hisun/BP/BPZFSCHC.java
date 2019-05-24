package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFSCHC {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String PGM_SCSSCLDT = "SCSSCLDT";
    String K_FEE_CODE = "FII01";
    String WS_ERR_MSG = " ";
    int WS_START_DATE = 0;
    int WS_CYCLE_DATE = 0;
    int WS_FIRST_SETTLE_DATE = 0;
    int WS_SETTLE_DATE = 0;
    double WS_AMOR_AMT = 0;
    short WS_MTHS = 0;
    int WS_DAYS = 0;
    int WS_TOTAL_DAYS = 0;
    int WS_DATE1 = 0;
    BPZFSCHC_REDEFINES11 REDEFINES11 = new BPZFSCHC_REDEFINES11();
    int WS_DATE2 = 0;
    BPZFSCHC_REDEFINES16 REDEFINES16 = new BPZFSCHC_REDEFINES16();
    char WS_END_FLG = ' ';
    char WS_ADJUST_OK_FLG = ' ';
    char WS_MONTH_END_FLG = ' ';
    int WS_NEXT_WORK_DAY = 0;
    int WS_LAST_WORK_DAY = 0;
    short WS_FSCHD_CNT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    BPRFSCH BPRFSCH = new BPRFSCH();
    BPRFSCHD BPRFSCHD = new BPRFSCHD();
    BPCRFSCH BPCRFSCH = new BPCRFSCH();
    BPCRSCHD BPCRSCHD = new BPCRSCHD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCFSCHC BPCFSCHC;
    public void MP(SCCGWA SCCGWA, BPCFSCHC BPCFSCHC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFSCHC = BPCFSCHC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFSCHC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCFSCHC.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B120_INQ_FEE_SCH();
        if (pgmRtn) return;
        if (BPRFSCH.UCT_FLG == 'Y') {
            B600_ADD_UCT_FSCHD();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
        B200_CALC_TOTAL_DAYS();
        if (pgmRtn) return;
        WS_END_FLG = 'N';
        while (WS_END_FLG != 'Y') {
            B300_ADJUST_SETTLE_DATE();
            if (pgmRtn) return;
            if (WS_SETTLE_DATE > WS_START_DATE 
                || (WS_SETTLE_DATE == WS_START_DATE 
                && BPRFSCH.FEE_TYPE.equalsIgnoreCase(K_FEE_CODE))) {
                CEP.TRC(SCCGWA, "CNT1111");
                B400_ADD_ONE_CYCLE();
                if (pgmRtn) return;
                if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
                    WS_FSCHD_CNT += 1;
                    CEP.TRC(SCCGWA, WS_FSCHD_CNT);
                    if (WS_FSCHD_CNT > 500) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FSCHD_TOO_MUCH;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
            CEP.TRC(SCCGWA, WS_SETTLE_DATE);
            CEP.TRC(SCCGWA, BPCFSCHC.MATURITY_DATE);
            if (WS_SETTLE_DATE == BPCFSCHC.MATURITY_DATE) {
                WS_END_FLG = 'Y';
            } else {
                B500_ADVANCE();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, WS_END_FLG);
        }
    }
    public void B100_DELETE_SCHD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRSCHD);
        IBS.init(SCCGWA, BPRFSCHD);
        BPRFSCHD.KEY.CTRT_NO = BPCFSCHC.CTRT_NO;
        BPCRSCHD.INFO.FUNC = 'D';
        S000_CALL_BPZRSCHD();
        if (pgmRtn) return;
    }
    public void B120_INQ_FEE_SCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFSCH);
        IBS.init(SCCGWA, BPCRFSCH);
        BPRFSCH.KEY.CTRT_NO = BPCFSCHC.CTRT_NO;
        BPCRFSCH.INFO.FUNC = 'Q';
        S000_CALL_BPZRFSCH();
        if (pgmRtn) return;
        if (BPCRFSCH.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FSCH_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B200_CALC_TOTAL_DAYS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = BPCFSCHC.START_DATE;
        SCCCLDT.DATE2 = BPCFSCHC.MATURITY_DATE;
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        WS_TOTAL_DAYS = SCCCLDT.DAYS;
        CEP.TRC(SCCGWA, SCCCLDT.DAYS);
        WS_START_DATE = BPCFSCHC.START_DATE;
        WS_CYCLE_DATE = BPCFSCHC.SETTLE_DATE;
        WS_FIRST_SETTLE_DATE = BPCFSCHC.SETTLE_DATE;
        WS_MTHS = 0;
        WS_DAYS = 0;
        WS_MONTH_END_FLG = 'N';
        if (BPCFSCHC.SETTLE_FREQ == '0' 
            || BPCFSCHC.SETTLE_FREQ == '1') {
            WS_DATE1 = BPCFSCHC.SETTLE_DATE;
            IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES11);
            R000_GET_MONTH_END();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_DATE1);
            if (WS_DATE1 == BPCFSCHC.SETTLE_DATE) {
                WS_MONTH_END_FLG = 'Y';
            }
        }
    }
    public void B300_ADJUST_SETTLE_DATE() throws IOException,SQLException,Exception {
        WS_SETTLE_DATE = WS_CYCLE_DATE;
        CEP.TRC(SCCGWA, WS_SETTLE_DATE);
        if (BPCFSCHC.HOLI_METHOD != 'N') {
            IBS.init(SCCGWA, BPCOCLWD);
            BPCOCLWD.DATE1 = WS_SETTLE_DATE;
            BPCOCLWD.CAL_CODE = BPCFSCHC.CAL_CODE2;
            BPCOCLWD.DAYE_FLG = 'Y';
            BPCOCLWD.WDAYS = 1;
            S000_CALL_BPZPCLWD();
            if (pgmRtn) return;
            WS_NEXT_WORK_DAY = BPCOCLWD.DATE2;
            CEP.TRC(SCCGWA, WS_NEXT_WORK_DAY);
            IBS.init(SCCGWA, BPCOCLWD);
            BPCOCLWD.DATE1 = WS_SETTLE_DATE;
            BPCOCLWD.CAL_CODE = BPCFSCHC.CAL_CODE2;
            BPCOCLWD.DAYE_FLG = 'Y';
            BPCOCLWD.WDAYS = -1;
            S000_CALL_BPZPCLWD();
            if (pgmRtn) return;
            WS_LAST_WORK_DAY = BPCOCLWD.DATE2;
            CEP.TRC(SCCGWA, WS_LAST_WORK_DAY);
            CEP.TRC(SCCGWA, WS_NEXT_WORK_DAY);
            CEP.TRC(SCCGWA, WS_LAST_WORK_DAY);
            if (BPCOCLWD.DATE1_FLG == 'H' 
                && BPCFSCHC.HOLI_METHOD == 'F') {
                WS_SETTLE_DATE = WS_NEXT_WORK_DAY;
            }
            if (BPCOCLWD.DATE1_FLG == 'H' 
                && BPCFSCHC.HOLI_METHOD == 'M') {
                WS_DATE1 = WS_SETTLE_DATE;
                IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES11);
                WS_DATE2 = WS_NEXT_WORK_DAY;
                IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES16);
                if (REDEFINES11.WS_DATE1_MM == REDEFINES16.WS_DATE2_MM) {
                    WS_SETTLE_DATE = WS_NEXT_WORK_DAY;
                } else {
                    WS_SETTLE_DATE = WS_LAST_WORK_DAY;
                }
            }
            if (BPCOCLWD.DATE1_FLG == 'H' 
                && BPCFSCHC.HOLI_METHOD == 'P') {
                WS_SETTLE_DATE = WS_LAST_WORK_DAY;
            }
        }
        if (WS_SETTLE_DATE > BPCFSCHC.MATURITY_DATE) {
            WS_SETTLE_DATE = BPCFSCHC.MATURITY_DATE;
        }
    }
    public void B400_ADD_ONE_CYCLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFSCHD);
        IBS.init(SCCGWA, BPCRSCHD);
        BPRFSCHD.KEY.CTRT_NO = BPCFSCHC.CTRT_NO;
        BPRFSCHD.KEY.SETTLE_DATE = WS_SETTLE_DATE;
        BPRFSCHD.CHARGE_CCY = BPRFSCH.CHARGE_CCY;
        BPRFSCHD.STS = 'N';
        BPRFSCHD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRFSCHD.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
        BPRFSCHD.SUP_TEL1 = SCCGWA.COMM_AREA.TL_ID;
        BPRFSCHD.SUP_TEL2 = SCCGWA.COMM_AREA.TL_ID;
        BPCRSCHD.INFO.FUNC = 'C';
        S000_CALL_BPZRSCHD();
        if (pgmRtn) return;
        WS_START_DATE = WS_SETTLE_DATE;
    }
    public void B500_ADVANCE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        CEP.TRC(SCCGWA, "AB123");
        CEP.TRC(SCCGWA, WS_FIRST_SETTLE_DATE);
        SCCCLDT.DATE1 = WS_FIRST_SETTLE_DATE;
        CEP.TRC(SCCGWA, "CC456");
        CEP.TRC(SCCGWA, BPCFSCHC.SETTLE_FREQ);
        CEP.TRC(SCCGWA, BPCFSCHC.FREQ_COUNT);
        CEP.TRC(SCCGWA, WS_MTHS);
        if (BPCFSCHC.SETTLE_FREQ == '0') {
            if (WS_MTHS + BPCFSCHC.FREQ_COUNT * 12 > 999) {
                CEP.TRC(SCCGWA, "CHARGING BY YEAR ERROR");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FSCH_YEAR_TOO_MUCH;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_MTHS = (short) (WS_MTHS + BPCFSCHC.FREQ_COUNT * 12);
            SCCCLDT.MTHS = WS_MTHS;
        } else if (BPCFSCHC.SETTLE_FREQ == '1') {
            if (WS_MTHS + BPCFSCHC.FREQ_COUNT > 999) {
                CEP.TRC(SCCGWA, "TESTING BY MONTH ERROR");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FSCH_YEAR_TOO_MUCH;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_MTHS = (short) (WS_MTHS + BPCFSCHC.FREQ_COUNT);
            SCCCLDT.MTHS = WS_MTHS;
        } else if (BPCFSCHC.SETTLE_FREQ == '2') {
            WS_DAYS = WS_DAYS + BPCFSCHC.FREQ_COUNT * 7;
            SCCCLDT.DAYS = WS_DAYS;
        } else if (BPCFSCHC.SETTLE_FREQ == '3') {
            WS_DAYS = WS_DAYS + BPCFSCHC.FREQ_COUNT;
            SCCCLDT.DAYS = WS_DAYS;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID SETTLE-FREQ(" + BPCFSCHC.SETTLE_FREQ + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        CEP.TRC(SCCGWA, "2015102001");
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        if (WS_MONTH_END_FLG == 'Y') {
            WS_DATE1 = SCCCLDT.DATE2;
            IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES11);
            R000_GET_MONTH_END();
            if (pgmRtn) return;
            WS_CYCLE_DATE = WS_DATE1;
        } else {
            WS_CYCLE_DATE = SCCCLDT.DATE2;
        }
        if (WS_CYCLE_DATE > BPCFSCHC.MATURITY_DATE) {
            WS_CYCLE_DATE = BPCFSCHC.MATURITY_DATE;
        }
        CEP.TRC(SCCGWA, WS_LAST_WORK_DAY);
    }
    public void B600_ADD_UCT_FSCHD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFSCHD);
        IBS.init(SCCGWA, BPCRSCHD);
        BPRFSCHD.KEY.CTRT_NO = BPCFSCHC.CTRT_NO;
        BPRFSCHD.KEY.SETTLE_DATE = BPRFSCH.MATURITY_DATE;
        BPRFSCHD.CHARGE_CCY = BPRFSCH.CHARGE_CCY;
        BPRFSCHD.STS = 'N';
        BPRFSCHD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRFSCHD.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
        BPRFSCHD.SUP_TEL1 = SCCGWA.COMM_AREA.TL_ID;
        BPRFSCHD.SUP_TEL2 = SCCGWA.COMM_AREA.TL_ID;
        BPCRSCHD.INFO.FUNC = 'C';
        S000_CALL_BPZRSCHD();
        if (pgmRtn) return;
    }
    public void R000_GET_MONTH_END() throws IOException,SQLException,Exception {
        if (REDEFINES11.WS_DATE1_MM == 12) {
            REDEFINES11.WS_DATE1_MM = 1;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES11);
            WS_DATE1 = Integer.parseInt(JIBS_tmp_str[0]);
            REDEFINES11.WS_DATE1_YYYY += 1;
        } else {
            REDEFINES11.WS_DATE1_MM += 1;
        }
        REDEFINES11.WS_DATE1_DD = 1;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, REDEFINES11);
        WS_DATE1 = Integer.parseInt(JIBS_tmp_str[0]);
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = WS_DATE1;
        SCCCLDT.DAYS = -1;
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        WS_DATE1 = SCCCLDT.DATE2;
        IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES11);
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
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "CALL SCSSCLDT FAIL, RC=" + SCCCLDT.RC;
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void S000_CALL_BPZPCLWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CAL-WORK-DAY", BPCOCLWD);
        CEP.TRC(SCCGWA, BPCOCLWD.RC);
        if (BPCOCLWD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOCLWD.RC);
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
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCFSCHC.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCFSCHC = ");
            CEP.TRC(SCCGWA, BPCFSCHC);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
