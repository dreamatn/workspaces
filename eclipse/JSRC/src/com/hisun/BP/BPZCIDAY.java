package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZCIDAY {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String PGM_SCSSCLDT = "SCSSCLDT";
    String PGM_SCSSCKDT = "SCSSCKDT";
    BPZCIDAY_WS_BEGIN_DATE WS_BEGIN_DATE = new BPZCIDAY_WS_BEGIN_DATE();
    BPZCIDAY_WS_END_DATE WS_END_DATE = new BPZCIDAY_WS_END_DATE();
    int WS_IDX = 0;
    int WS_INTER_YR = 0;
    BPZCIDAY_WS_CHECK_DATE1 WS_CHECK_DATE1 = new BPZCIDAY_WS_CHECK_DATE1();
    BPZCIDAY_WS_CHECK_DATE2 WS_CHECK_DATE2 = new BPZCIDAY_WS_CHECK_DATE2();
    String WS_ERR_MSG = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCGWA SCCGWA;
    BPCIDAY BPCIDAY;
    public void MP(SCCGWA SCCGWA, BPCIDAY BPCIDAY) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCIDAY = BPCIDAY;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZCIDAY return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCIDAY.RC.APP = SCCGWA.COMM_AREA.AP_MMO;
        BPCIDAY.RC.RC_CODE = 0;
        IBS.init(SCCGWA, BPCIDAY.OUTPUT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B200_CALC_INT_DAYS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCIDAY.I_CALR_STD.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CALC_INT_SPACE, BPCIDAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (!BPCIDAY.I_CALR_STD.equalsIgnoreCase("01") 
            && !BPCIDAY.I_CALR_STD.equalsIgnoreCase("02") 
            && !BPCIDAY.I_CALR_STD.equalsIgnoreCase("03") 
            && !BPCIDAY.I_CALR_STD.equalsIgnoreCase("04") 
            && !BPCIDAY.I_CALR_STD.equalsIgnoreCase("05")) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CALR_STD_ERR, BPCIDAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = BPCIDAY.I_BEGIN_DATE;
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CALC_BEGIN_DATE_ERR, BPCIDAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = BPCIDAY.I_END_DATE;
        SCSSCKDT SCSSCKDT2 = new SCSSCKDT();
        SCSSCKDT2.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CALC_END_DATE_ERR, BPCIDAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCIDAY.I_BEGIN_DATE > BPCIDAY.I_END_DATE) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CALC_END_DATE_ERR, BPCIDAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CPY2CLS(SCCGWA, BPCIDAY.I_BEGIN_DATE+"", WS_BEGIN_DATE);
        IBS.CPY2CLS(SCCGWA, BPCIDAY.I_END_DATE+"", WS_END_DATE);
    }
    public void B200_CALC_INT_DAYS() throws IOException,SQLException,Exception {
        if (BPCIDAY.I_CALR_STD.equalsIgnoreCase("01")) {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = BPCIDAY.I_BEGIN_DATE;
            SCCCLDT.DATE2 = BPCIDAY.I_END_DATE;
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            BPCIDAY.OUTPUT.O_ORD_DAYS = SCCCLDT.DAYS;
            BPCIDAY.OUTPUT.O_STD_DAYS = 360;
        } else if (BPCIDAY.I_CALR_STD.equalsIgnoreCase("02")) {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = BPCIDAY.I_BEGIN_DATE;
            SCCCLDT.DATE2 = BPCIDAY.I_END_DATE;
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            BPCIDAY.OUTPUT.O_ORD_DAYS = SCCCLDT.DAYS;
            BPCIDAY.OUTPUT.O_STD_DAYS = 365;
        } else if (BPCIDAY.I_CALR_STD.equalsIgnoreCase("03")) {
            WS_INTER_YR = WS_END_DATE.WS_END_YYYY - WS_BEGIN_DATE.WS_BEGIN_YYYY + 1;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_BEGIN_DATE);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_CHECK_DATE1);
            if (WS_END_DATE.WS_END_YYYY > WS_BEGIN_DATE.WS_BEGIN_YYYY) {
                WS_CHECK_DATE2.WS_CHECK_YYYY2 = WS_BEGIN_DATE.WS_BEGIN_YYYY;
                WS_CHECK_DATE2.WS_CHECK_MM2 = 12;
                WS_CHECK_DATE2.WS_CHECK_DD2 = 31;
            } else {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_END_DATE);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_CHECK_DATE2);
            }
            for (WS_IDX = 1; WS_IDX <= WS_INTER_YR; WS_IDX += 1) {
                IBS.init(SCCGWA, SCCCKDT);
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_CHECK_DATE1);
                SCCCKDT.DATE = Integer.parseInt(JIBS_tmp_str[0]);
                S000_CALL_SCSSCKDT();
                if (pgmRtn) return;
                IBS.init(SCCGWA, SCCCLDT);
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_CHECK_DATE1);
                SCCCLDT.DATE1 = Integer.parseInt(JIBS_tmp_str[0]);
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_CHECK_DATE2);
                SCCCLDT.DATE2 = Integer.parseInt(JIBS_tmp_str[0]);
                S000_CALL_SCSSCLDT();
                if (pgmRtn) return;
                if (SCCCKDT.LEAP_YEAR == 1) {
                    BPCIDAY.OUTPUT.O_LEAP_DAYS = BPCIDAY.OUTPUT.O_LEAP_DAYS + SCCCLDT.DAYS;
                    if (WS_CHECK_DATE2.WS_CHECK_YYYY2 < WS_END_DATE.WS_END_YYYY) {
                        BPCIDAY.OUTPUT.O_LEAP_DAYS += 1;
                    }
                } else {
                    BPCIDAY.OUTPUT.O_ORD_DAYS = BPCIDAY.OUTPUT.O_ORD_DAYS + SCCCLDT.DAYS;
                    if (WS_CHECK_DATE2.WS_CHECK_YYYY2 < WS_END_DATE.WS_END_YYYY) {
                        BPCIDAY.OUTPUT.O_ORD_DAYS += 1;
                    }
                }
                WS_CHECK_DATE1.WS_CHECK_YYYY1 = (short) (WS_CHECK_DATE1.WS_CHECK_YYYY1 + 1);
                WS_CHECK_DATE1.WS_CHECK_MM1 = 1;
                WS_CHECK_DATE1.WS_CHECK_DD1 = 1;
                WS_CHECK_DATE2.WS_CHECK_YYYY2 = WS_CHECK_DATE1.WS_CHECK_YYYY1;
                if (WS_CHECK_DATE1.WS_CHECK_YYYY1 < WS_END_DATE.WS_END_YYYY) {
                    WS_CHECK_DATE2.WS_CHECK_MM2 = 12;
                    WS_CHECK_DATE2.WS_CHECK_DD2 = 31;
                } else {
                    WS_CHECK_DATE2.WS_CHECK_MM2 = WS_END_DATE.WS_END_MM;
                    WS_CHECK_DATE2.WS_CHECK_DD2 = WS_END_DATE.WS_END_DD;
                }
            }
            BPCIDAY.OUTPUT.O_STD_DAYS = 366;
        } else if (BPCIDAY.I_CALR_STD.equalsIgnoreCase("04")) {
            if (WS_END_DATE.WS_END_DD == 31 
                && (WS_BEGIN_DATE.WS_BEGIN_DD == 30 
                || WS_BEGIN_DATE.WS_BEGIN_DD == 31)) {
                WS_END_DATE.WS_END_DD = 30;
            }
            if (WS_BEGIN_DATE.WS_BEGIN_DD == 31) {
                WS_BEGIN_DATE.WS_BEGIN_DD = 30;
            }
            BPCIDAY.OUTPUT.O_ORD_DAYS = ( WS_END_DATE.WS_END_YYYY - WS_BEGIN_DATE.WS_BEGIN_YYYY ) * 360 + ( WS_END_DATE.WS_END_MM - WS_BEGIN_DATE.WS_BEGIN_MM ) * 30 + ( WS_END_DATE.WS_END_DD - WS_BEGIN_DATE.WS_BEGIN_DD );
            BPCIDAY.OUTPUT.O_STD_DAYS = 360;
        } else if (BPCIDAY.I_CALR_STD.equalsIgnoreCase("05")) {
            if (WS_BEGIN_DATE.WS_BEGIN_DD == 31) {
                WS_BEGIN_DATE.WS_BEGIN_DD = 30;
            }
            if (WS_END_DATE.WS_END_DD == 31) {
                WS_END_DATE.WS_END_DD = 30;
            }
            if (WS_BEGIN_DATE.WS_BEGIN_MM == 2) {
                if (WS_BEGIN_DATE.WS_BEGIN_YYYY % 100 == 0) {
                    if (WS_BEGIN_DATE.WS_BEGIN_YYYY % 400 == 0) {
                        if (WS_BEGIN_DATE.WS_BEGIN_DD == 29) {
                            WS_BEGIN_DATE.WS_BEGIN_DD = 30;
                        }
                    } else {
                        if (WS_BEGIN_DATE.WS_BEGIN_DD == 28) {
                            WS_BEGIN_DATE.WS_BEGIN_DD = 30;
                        }
                    }
                } else {
                    if (WS_BEGIN_DATE.WS_BEGIN_YYYY % 4 == 0) {
                        if (WS_BEGIN_DATE.WS_BEGIN_DD == 29) {
                            WS_BEGIN_DATE.WS_BEGIN_DD = 30;
                        }
                    } else {
                        if (WS_BEGIN_DATE.WS_BEGIN_DD == 28) {
                            WS_BEGIN_DATE.WS_BEGIN_DD = 30;
                        }
                    }
                }
            }
            if (WS_END_DATE.WS_END_MM == 2) {
                if (WS_END_DATE.WS_END_YYYY % 100 == 0) {
                    if (WS_END_DATE.WS_END_YYYY % 400 == 0) {
                        if (WS_END_DATE.WS_END_DD == 29) {
                            WS_END_DATE.WS_END_DD = 30;
                        }
                    } else {
                        if (WS_END_DATE.WS_END_DD == 28) {
                            WS_END_DATE.WS_END_DD = 30;
                        }
                    }
                } else {
                    if (WS_END_DATE.WS_END_YYYY % 4 == 0) {
                        if (WS_END_DATE.WS_END_DD == 29) {
                            WS_END_DATE.WS_END_DD = 30;
                        }
                    } else {
                        if (WS_END_DATE.WS_END_DD == 28) {
                            WS_END_DATE.WS_END_DD = 30;
                        }
                    }
                }
            }
            BPCIDAY.OUTPUT.O_ORD_DAYS = ( WS_END_DATE.WS_END_YYYY - WS_BEGIN_DATE.WS_BEGIN_YYYY ) * 360 + ( WS_END_DATE.WS_END_MM - WS_BEGIN_DATE.WS_BEGIN_MM ) * 30 + ( WS_END_DATE.WS_END_DD - WS_BEGIN_DATE.WS_BEGIN_DD );
            BPCIDAY.OUTPUT.O_STD_DAYS = 360;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CALR_STD_ERR, BPCIDAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT3 = new SCSSCLDT();
        SCSSCLDT3.MP(SCCGWA, SCCCLDT);
        if (0 != 0 
            || SCCCLDT.RC != 0) {
            BPCIDAY.RC.RC_CODE = SCCCLDT.RC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCSSCKDT() throws IOException,SQLException,Exception {
        SCSSCKDT SCSSCKDT4 = new SCSSCKDT();
        SCSSCKDT4.MP(SCCGWA, SCCCKDT);
        if (0 != 0 
            || SCCCKDT.RC != 0) {
            BPCIDAY.RC.RC_CODE = SCCCKDT.RC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCIDAY.RC);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCIDAY.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCIDAY = ");
            CEP.TRC(SCCGWA, BPCIDAY);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
