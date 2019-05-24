package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZCCKWD {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_CALL_CIZCUST = "CI-INQ-CUST";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String K_CALL_BPZQCCY = "BP-INQUIRE-CCY      ";
    String K_CALL_BPZQCNCI = "BP-Q-CNTY-CITY-IFO  ";
    String K_CALL_BPZQCHEK = "BP-P-QUERY-CHEK-CODE";
    short K_CHECK_TIMES = 60;
    String WS_ERR_MSG = " ";
    short WS_CNT = 0;
    short WS_I = 0;
    String[] WS_CAL_CD = new String[10];
    String WS_CNTY_CD = " ";
    String WS_CITY_CD = " ";
    char WS_FLG = ' ';
    char WS_ALL_WDAY = ' ';
    int WS_DATE = 0;
    int WS_CHECK_DATE = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCPGDIN BPCPGDIN = new BPCPGDIN();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    BPCPQBNK BPCPQBNK = new BPCPQBNK();
    BPCPQBCH BPCPQBCH = new BPCPQBCH();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCQCNCI BPCQCNCI = new BPCQCNCI();
    BPCQCHEK BPCQCHEK = new BPCQCHEK();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    CICCUST CICCUST = new CICCUST();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    BPCCCKWD BPCCCKWD;
    public void MP(SCCGWA SCCGWA, BPCCCKWD BPCCCKWD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCCCKWD = BPCCCKWD;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAINTAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZCCKWD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQBNK);
        IBS.init(SCCGWA, BPCQCCY);
        IBS.init(SCCGWA, BPCQCNCI);
        IBS.init(SCCGWA, BPCQCHEK);
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCCCKWD.RC);
    }
    public void B00_MAINTAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_GET_HTCHK_INFO();
        if (pgmRtn) return;
        B035_GET_RESI_CAL_CODE();
        if (pgmRtn) return;
        B040_GET_CCY_CAL_CODE();
        if (pgmRtn) return;
        B045_GET_BRAN_CAL_CODE();
        if (pgmRtn) return;
        B048_GET_CNTY_CITY_CAL_CODE();
        if (pgmRtn) return;
        B050_CHECK_MULTI_CAL_HOLIDAY();
        if (pgmRtn) return;
        B060_GET_HOLIDAY_FLAG_ALL();
        if (pgmRtn) return;
        B062_GET_NX_LS_WORK_DAY_ALL();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCCCKWD.CHECK_DATE);
        CEP.TRC(SCCGWA, BPCCCKWD.HCHK_CODE);
        if (BPCCCKWD.CHECK_DATE == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CHEK_DT_MUST_INPUT, BPCCCKWD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCCCKWD.CHECK_DATE != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = BPCCCKWD.CHECK_DATE;
            SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
            SCSSCKDT1.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_DATE_ERR, BPCCCKWD.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (BPCCCKWD.HCHK_CODE.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_HCHKCD_MUST_INPUT, BPCCCKWD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_HTCHK_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCHEK);
        BPCQCHEK.CHECK_CODE = BPCCCKWD.HCHK_CODE;
        S000_CALL_BPZQCHEK();
        if (pgmRtn) return;
    }
    public void B035_GET_RESI_CAL_CODE() throws IOException,SQLException,Exception {
        if (BPCCCKWD.CI_NO.equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CI_MUST_INPUT, BPCCCKWD.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, CICCUST);
            CICCUST.DATA.CI_NO = BPCCCKWD.CI_NO;
            CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            if (CICCUST.O_DATA.O_REG_CNTY.trim().length() > 0) {
                WS_CNTY_CD = CICCUST.O_DATA.O_REG_CNTY;
                S000_CALL_BPZQCNCI();
                if (pgmRtn) return;
            }
        }
    }
    public void B040_GET_CCY_CAL_CODE() throws IOException,SQLException,Exception {
        if (BPCQCHEK.DATA.CUR_FLG == 'Y') {
            if (BPCCCKWD.TRADE_CCY.equalsIgnoreCase("*")) {
            } else {
                if (BPCCCKWD.TRADE_CCY.trim().length() == 0) {
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CCY_MUST_INPUT, BPCCCKWD.RC);
                    Z_RET();
                    if (pgmRtn) return;
                } else {
                    IBS.init(SCCGWA, BPCQCCY);
                    BPCQCCY.DATA.CCY = BPCCCKWD.TRADE_CCY;
                    S000_CALL_BPZQCCY();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, BPCQCCY.DATA.CNTY_CD);
                    CEP.TRC(SCCGWA, BPCQCCY.DATA.CITY_CD);
                    WS_CNTY_CD = BPCQCCY.DATA.CNTY_CD;
                    WS_CITY_CD = BPCQCCY.DATA.CITY_CD;
                    S000_CALL_BPZQCNCI();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B045_GET_BRAN_CAL_CODE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
    }
    public void B048_GET_CNTY_CITY_CAL_CODE() throws IOException,SQLException,Exception {
        if (BPCQCHEK.DATA.CNTY_CD1.trim().length() > 0) {
            WS_CNTY_CD = BPCQCHEK.DATA.CNTY_CD1;
            WS_CITY_CD = BPCQCHEK.DATA.CITY_CD1;
            S000_CALL_BPZQCNCI();
            if (pgmRtn) return;
        }
        if (BPCQCHEK.DATA.CNTY_CD2.trim().length() > 0) {
            WS_CNTY_CD = BPCQCHEK.DATA.CNTY_CD2;
            WS_CITY_CD = BPCQCHEK.DATA.CITY_CD2;
            S000_CALL_BPZQCNCI();
            if (pgmRtn) return;
        }
        if (BPCQCHEK.DATA.CNTY_CD3.trim().length() > 0) {
            WS_CNTY_CD = BPCQCHEK.DATA.CNTY_CD3;
            WS_CITY_CD = BPCQCHEK.DATA.CITY_CD3;
            S000_CALL_BPZQCNCI();
            if (pgmRtn) return;
        }
        if (BPCQCHEK.DATA.CNTY_CD4.trim().length() > 0) {
            WS_CNTY_CD = BPCQCHEK.DATA.CNTY_CD4;
            WS_CITY_CD = BPCQCHEK.DATA.CITY_CD4;
            S000_CALL_BPZQCNCI();
            if (pgmRtn) return;
        }
    }
    public void B050_CHECK_MULTI_CAL_HOLIDAY() throws IOException,SQLException,Exception {
        BPCCCKWD.HOLIDAY_FLG_ALL = 'N';
        for (WS_I = 1; WS_I <= 7; WS_I += 1) {
            IBS.init(SCCGWA, BPCPGDIN);
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, WS_CAL_CD[WS_I-1]);
            if (WS_CAL_CD[WS_I-1].trim().length() > 0) {
                BPCPGDIN.INPUT_DATA.CAL_CD = WS_CAL_CD[WS_I-1];
                BPCPGDIN.INPUT_DATA.FUNC = '1';
                if (BPCCCKWD.DATE_TYPE == 'H') {
                    BPCPGDIN.INPUT_DATA.DATE_TYPE = 'H';
                } else if (BPCCCKWD.DATE_TYPE == 'E') {
                    BPCPGDIN.INPUT_DATA.DATE_TYPE = 'E';
                } else {
                    BPCPGDIN.INPUT_DATA.DATE_TYPE = 'B';
                }
                BPCPGDIN.INPUT_DATA.DATE_1 = BPCCCKWD.CHECK_DATE;
                S000_CALL_BPZPGDIN();
                if (pgmRtn) return;
                if (BPCPGDIN.OUTPUT_DATA.DATE1_FLG == 'H') {
                    BPCCCKWD.HOLIDAY_FLG[WS_I-1] = 'Y';
                    WS_ALL_WDAY = 'N';
                } else {
                    BPCCCKWD.HOLIDAY_FLG[WS_I-1] = 'N';
                }
                IBS.init(SCCGWA, BPCPGDIN);
                BPCPGDIN.INPUT_DATA.DATE_1 = BPCCCKWD.CHECK_DATE;
                BPCPGDIN.INPUT_DATA.CAL_CD = WS_CAL_CD[WS_I-1];
                if (BPCCCKWD.DATE_TYPE == 'H') {
                    BPCPGDIN.INPUT_DATA.DATE_TYPE = 'H';
                } else if (BPCCCKWD.DATE_TYPE == 'E') {
                    BPCPGDIN.INPUT_DATA.DATE_TYPE = 'E';
                } else {
                    BPCPGDIN.INPUT_DATA.DATE_TYPE = 'B';
                }
                if (BPCCCKWD.HOLIDAY_FLG[WS_I-1] == 'Y') {
                    BPCPGDIN.INPUT_DATA.WDAYS = 1;
                } else {
                    BPCPGDIN.INPUT_DATA.WDAYS = 2;
                }
                BPCPGDIN.INPUT_DATA.FUNC = '2';
                S000_CALL_BPZPGDIN();
                if (pgmRtn) return;
                BPCCCKWD.NEXT_WORK_DAY[WS_I-1] = BPCPGDIN.OUTPUT_DATA.DATE_2;
                IBS.init(SCCGWA, BPCPGDIN);
                BPCPGDIN.INPUT_DATA.DATE_1 = BPCCCKWD.CHECK_DATE;
                BPCPGDIN.INPUT_DATA.CAL_CD = WS_CAL_CD[WS_I-1];
                if (BPCCCKWD.DATE_TYPE == 'H') {
                    BPCPGDIN.INPUT_DATA.DATE_TYPE = 'H';
                } else if (BPCCCKWD.DATE_TYPE == 'E') {
                    BPCPGDIN.INPUT_DATA.DATE_TYPE = 'E';
                } else {
                    BPCPGDIN.INPUT_DATA.DATE_TYPE = 'B';
                }
                if (BPCCCKWD.HOLIDAY_FLG[WS_I-1] == 'Y') {
                    BPCPGDIN.INPUT_DATA.WDAYS = -1;
                } else {
                    BPCPGDIN.INPUT_DATA.WDAYS = -2;
                }
                BPCPGDIN.INPUT_DATA.FUNC = '2';
                CEP.TRC(SCCGWA, " KIA IMPORTANT");
                CEP.TRC(SCCGWA, BPCPGDIN.INPUT_DATA.DATE_1);
                S000_CALL_BPZPGDIN();
                if (pgmRtn) return;
                BPCCCKWD.LAST_WORK_DAY[WS_I-1] = BPCPGDIN.OUTPUT_DATA.DATE_2;
                CEP.TRC(SCCGWA, " KIA IMPORTANT AGAIN");
                CEP.TRC(SCCGWA, BPCPGDIN.OUTPUT_DATA.DATE_2);
            }
        }
    }
    public void B060_GET_HOLIDAY_FLAG_ALL() throws IOException,SQLException,Exception {
        if (WS_ALL_WDAY == 'N') {
            BPCCCKWD.HOLIDAY_FLG_ALL = 'Y';
        }
    }
    public void B062_GET_NX_LS_WORK_DAY_ALL() throws IOException,SQLException,Exception {
        WS_DATE = BPCCCKWD.CHECK_DATE;
        WS_CNT = 1;
        WS_FLG = 'N';
        while (WS_FLG != 'Y' 
            && WS_CNT <= K_CHECK_TIMES) {
            IBS.init(SCCGWA, BPCPGDIN);
            if (BPCCCKWD.DATE_TYPE == 'H') {
                BPCPGDIN.INPUT_DATA.DATE_TYPE = 'H';
            } else if (BPCCCKWD.DATE_TYPE == 'E') {
                BPCPGDIN.INPUT_DATA.DATE_TYPE = 'E';
            } else {
                BPCPGDIN.INPUT_DATA.DATE_TYPE = 'B';
            }
            BPCPGDIN.INPUT_DATA.DATE_1 = WS_DATE;
            BPCPGDIN.INPUT_DATA.CAL_CD = WS_CAL_CD[1-1];
            BPCPGDIN.INPUT_DATA.WDAYS = 2;
            if (BPCCCKWD.HOLIDAY_FLG[1-1] == 'Y' 
                && WS_CNT == 1) {
                BPCPGDIN.INPUT_DATA.WDAYS = 1;
            }
            BPCPGDIN.INPUT_DATA.FUNC = '2';
            S000_CALL_BPZPGDIN();
            if (pgmRtn) return;
            WS_DATE = BPCPGDIN.OUTPUT_DATA.DATE_2;
            WS_CHECK_DATE = BPCPGDIN.OUTPUT_DATA.DATE_2;
            B070_CHECK_HOLIDAY();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_DATE);
            CEP.TRC(SCCGWA, WS_CNT);
            WS_CNT += 1;
            if (WS_ALL_WDAY == 'Y') {
                WS_FLG = 'Y';
                BPCCCKWD.NEXT_WORK_DAY_ALL = WS_DATE;
                CEP.TRC(SCCGWA, "FOUND NEXT WRKDAY");
                CEP.TRC(SCCGWA, BPCPGDIN.OUTPUT_DATA.DATE_2);
            } else {
                if (WS_CNT > K_CHECK_TIMES) {
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NEXT_WORK_DAY_ERR, BPCCCKWD.RC);
                    Z_RET();
                    if (pgmRtn) return;
                } else {
                }
            }
        }
        WS_DATE = BPCCCKWD.CHECK_DATE;
        WS_CNT = 1;
        WS_FLG = 'N';
        while (WS_FLG != 'Y' 
            && WS_CNT <= K_CHECK_TIMES) {
            IBS.init(SCCGWA, BPCPGDIN);
            if (BPCCCKWD.DATE_TYPE == 'H') {
                BPCPGDIN.INPUT_DATA.DATE_TYPE = 'H';
            } else if (BPCCCKWD.DATE_TYPE == 'E') {
                BPCPGDIN.INPUT_DATA.DATE_TYPE = 'E';
            } else {
                BPCPGDIN.INPUT_DATA.DATE_TYPE = 'B';
            }
            BPCPGDIN.INPUT_DATA.DATE_1 = WS_DATE;
            BPCPGDIN.INPUT_DATA.CAL_CD = WS_CAL_CD[1-1];
            BPCPGDIN.INPUT_DATA.WDAYS = -2;
            if (BPCCCKWD.HOLIDAY_FLG[1-1] == 'Y' 
                && WS_CNT == 1) {
                BPCPGDIN.INPUT_DATA.WDAYS = -1;
            }
            BPCPGDIN.INPUT_DATA.FUNC = '2';
            S000_CALL_BPZPGDIN();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "KIAKIAKIAKIA");
            CEP.TRC(SCCGWA, BPCPGDIN.OUTPUT_DATA.DATE_2);
            WS_DATE = BPCPGDIN.OUTPUT_DATA.DATE_2;
            WS_CHECK_DATE = BPCPGDIN.OUTPUT_DATA.DATE_2;
            B070_CHECK_HOLIDAY();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_DATE);
            CEP.TRC(SCCGWA, WS_CNT);
            WS_CNT += 1;
            if (WS_ALL_WDAY == 'Y') {
                WS_FLG = 'Y';
                BPCCCKWD.LAST_WORK_DAY_ALL = WS_DATE;
                CEP.TRC(SCCGWA, "FOUND LAST WRKDAY");
                CEP.TRC(SCCGWA, BPCPGDIN.OUTPUT_DATA.DATE_2);
            } else {
                if (WS_CNT > K_CHECK_TIMES) {
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_LAST_WORK_DAY_ERR, BPCCCKWD.RC);
                    Z_RET();
                    if (pgmRtn) return;
                } else {
                }
            }
        }
    }
    public void B070_CHECK_HOLIDAY() throws IOException,SQLException,Exception {
        WS_ALL_WDAY = 'Y';
        for (WS_I = 1; WS_I <= 7 
            && WS_ALL_WDAY != 'N'; WS_I += 1) {
            IBS.init(SCCGWA, BPCPGDIN);
            if (WS_CAL_CD[WS_I-1].trim().length() > 0) {
                BPCPGDIN.INPUT_DATA.CAL_CD = WS_CAL_CD[WS_I-1];
                BPCPGDIN.INPUT_DATA.FUNC = '1';
                if (BPCCCKWD.DATE_TYPE == 'H') {
                    BPCPGDIN.INPUT_DATA.DATE_TYPE = 'H';
                } else if (BPCCCKWD.DATE_TYPE == 'E') {
                    BPCPGDIN.INPUT_DATA.DATE_TYPE = 'E';
                } else {
                    BPCPGDIN.INPUT_DATA.DATE_TYPE = 'B';
                }
                BPCPGDIN.INPUT_DATA.DATE_1 = WS_CHECK_DATE;
                S000_CALL_BPZPGDIN();
                if (pgmRtn) return;
                if (BPCPGDIN.OUTPUT_DATA.DATE1_FLG == 'H') {
                    WS_ALL_WDAY = 'N';
                }
            }
        }
    }
    public void S000_CALL_BPZPQBCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-BCH", BPCPQBCH);
        if (BPCPQBCH.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQBCH.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCCCKWD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPGDIN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-GET-DT-INFO", BPCPGDIN);
        if (BPCPGDIN.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPGDIN.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCCCKWD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CALL_BPZQCCY, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCCCKWD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZQCNCI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCNCI);
        BPCQCNCI.INPUT_DAT.CNTY_CD = WS_CNTY_CD;
        BPCQCNCI.INPUT_DAT.CITY_CD = WS_CITY_CD;
        IBS.CALLCPN(SCCGWA, K_CALL_BPZQCNCI, BPCQCNCI);
        CEP.TRC(SCCGWA, BPCQCNCI.INPUT_DAT.CNTY_CD);
        CEP.TRC(SCCGWA, BPCQCNCI.INPUT_DAT.CITY_CD);
        if (BPCQCNCI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCNCI.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCCCKWD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_CNT += 1;
        CEP.TRC(SCCGWA, "KIA IS THINKING");
        CEP.TRC(SCCGWA, WS_CNT);
        CEP.TRC(SCCGWA, BPCQCNCI.OUTPUT_CNTY_DAT.CALR_CODE);
        CEP.TRC(SCCGWA, BPCQCNCI.OUTPUT_CAL_CODE);
        WS_CAL_CD[WS_CNT-1] = BPCQCNCI.OUTPUT_CAL_CODE;
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG ", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_CNT += 1;
        WS_CAL_CD[WS_CNT-1] = BPCPQORG.CALD_CD;
    }
    public void S000_CALL_BPZQCHEK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CALL_BPZQCHEK, BPCQCHEK);
        if (BPCQCHEK.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCHEK.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCCCKWD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CALL_CIZCUST, CICCUST);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCCCKWD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCCCKWD = ");
            CEP.TRC(SCCGWA, BPCCCKWD);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
