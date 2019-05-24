package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZUCPDT {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_ERR_MSG = " ";
    short WS_POST_MOTHS = 0;
    int WS_NEXT_DATE = 0;
    int WS_LAST_DATE = 0;
    DDZUCPDT_WS_POST_DATE WS_POST_DATE = new DDZUCPDT_WS_POST_DATE();
    DDZUCPDT_WS_CURR_DATE WS_CURR_DATE = new DDZUCPDT_WS_CURR_DATE();
    short WS_QUOTIENT = 0;
    short WS_REMAINDER = 0;
    short WS_MTHS = 0;
    short WS_DAYS = 0;
    short WS_CNT = 0;
    int WS_DATE1 = 0;
    int WS_DATE2 = 0;
    short WS_POST_FEQ = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    DDCUCPDT DDCUCPDT;
    public void MP(SCCGWA SCCGWA, DDCUCPDT DDCUCPDT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCUCPDT = DDCUCPDT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZUCPDT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (DDCUCPDT.CURR_DATE > DDCUCPDT.POST_DATE) {
            B020_GET_POST_DATE_PROC();
            if (pgmRtn) return;
        } else {
            if ((DDCUCPDT.POST_DATE > DDCUCPDT.CURR_DATE 
                || DDCUCPDT.POST_DATE == DDCUCPDT.CURR_DATE) 
                && DDCUCPDT.POST_DATE < DDCUCPDT.NEXT_DATE) {
                DDCUCPDT.CURR_POST_FLG = 'Y';
                DDCUCPDT.NEXT_POST_DATE = DDCUCPDT.POST_DATE;
                CEP.TRC(SCCGWA, DDCUCPDT.NEXT_POST_DATE);
            } else {
                DDCUCPDT.CURR_POST_FLG = 'N';
            }
        }
        CEP.TRC(SCCGWA, DDCUCPDT.POST_DATE);
        CEP.TRC(SCCGWA, DDCUCPDT.CURR_DATE);
        CEP.TRC(SCCGWA, DDCUCPDT.POST_PERIOD);
        CEP.TRC(SCCGWA, DDCUCPDT.POST_FEQ);
        CEP.TRC(SCCGWA, DDCUCPDT.CURR_POST_FLG);
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUCPDT.POST_DATE);
        CEP.TRC(SCCGWA, DDCUCPDT.CURR_DATE);
        CEP.TRC(SCCGWA, DDCUCPDT.POST_PERIOD);
        CEP.TRC(SCCGWA, DDCUCPDT.POST_FEQ);
        if (DDCUCPDT.POST_DATE == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_POST_DT_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCUCPDT.CURR_DATE == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CURR_DT_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCUCPDT.POST_PERIOD == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_POST_PERIOD_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCUCPDT.POST_FEQ == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_POST_FEQ_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_POST_DATE_PROC() throws IOException,SQLException,Exception {
        if (DDCUCPDT.POST_PERIOD == 'M') {
            B021_GET_POST_MM_PROC();
            if (pgmRtn) return;
        } else if (DDCUCPDT.POST_PERIOD == 'D') {
            B023_GET_POST_DD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DDCUCPDT.POST_PERIOD + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B021_GET_POST_MM_PROC() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, DDCUCPDT.POST_DATE+"", WS_POST_DATE);
        IBS.CPY2CLS(SCCGWA, DDCUCPDT.CURR_DATE+"", WS_CURR_DATE);
        CEP.TRC(SCCGWA, WS_POST_DATE);
        CEP.TRC(SCCGWA, WS_CURR_DATE);
        if (WS_CURR_DATE.WS_CURR_YYYY > WS_POST_DATE.WS_POST_YYYY) {
            WS_MTHS = (short) (( WS_CURR_DATE.WS_CURR_YYYY - WS_POST_DATE.WS_POST_YYYY ) * 12 + WS_CURR_DATE.WS_CURR_MM - WS_POST_DATE.WS_POST_MM);
            CEP.TRC(SCCGWA, WS_MTHS);
        }
        if (WS_CURR_DATE.WS_CURR_YYYY == WS_POST_DATE.WS_POST_YYYY) {
            WS_MTHS = (short) (WS_CURR_DATE.WS_CURR_MM - WS_POST_DATE.WS_POST_MM);
            CEP.TRC(SCCGWA, WS_MTHS);
        }
        WS_REMAINDER = (short) (WS_MTHS % DDCUCPDT.POST_FEQ);
        WS_QUOTIENT = (short) ((WS_MTHS - WS_REMAINDER) / DDCUCPDT.POST_FEQ);
        CEP.TRC(SCCGWA, WS_QUOTIENT);
        CEP.TRC(SCCGWA, WS_REMAINDER);
        if (WS_REMAINDER == 0) {
            if (WS_MTHS != 0) {
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = DDCUCPDT.POST_DATE;
                SCCCLDT.MTHS = WS_MTHS;
                S000_CALL_SCSSCLDT();
                if (pgmRtn) return;
                WS_LAST_DATE = SCCCLDT.DATE2;
                CEP.TRC(SCCGWA, WS_LAST_DATE);
            }
            CEP.TRC(SCCGWA, "11111");
            if (WS_QUOTIENT != 0) {
                if (WS_POST_DATE.WS_POST_DD == WS_CURR_DATE.WS_CURR_DD) {
                    CEP.TRC(SCCGWA, "22222");
                    IBS.init(SCCGWA, SCCCLDT);
                    SCCCLDT.DATE1 = DDCUCPDT.POST_DATE;
                    SCCCLDT.MTHS = WS_MTHS;
                    S000_CALL_SCSSCLDT();
                    if (pgmRtn) return;
                    DDCUCPDT.NEXT_POST_DATE = SCCCLDT.DATE2;
                    DDCUCPDT.CURR_POST_FLG = 'Y';
                    CEP.TRC(SCCGWA, DDCUCPDT.NEXT_POST_DATE);
                } else {
                    if ((WS_LAST_DATE > DDCUCPDT.CURR_DATE 
                        || WS_LAST_DATE == DDCUCPDT.CURR_DATE) 
                        && WS_LAST_DATE < DDCUCPDT.NEXT_DATE) {
                        CEP.TRC(SCCGWA, "AAAAA");
                        DDCUCPDT.CURR_POST_FLG = 'Y';
                        DDCUCPDT.NEXT_POST_DATE = WS_LAST_DATE;
                        CEP.TRC(SCCGWA, DDCUCPDT.NEXT_POST_DATE);
                    } else {
                        CEP.TRC(SCCGWA, "BBBBB");
                        WS_CNT = 1;
                        while (WS_DATE1 < DDCUCPDT.CURR_DATE 
                            && WS_CNT <= 1000) {
                            WS_POST_FEQ = (short) (DDCUCPDT.POST_FEQ * WS_CNT);
                            CEP.TRC(SCCGWA, WS_POST_FEQ);
                            IBS.init(SCCGWA, SCCCLDT);
                            SCCCLDT.DATE1 = DDCUCPDT.POST_DATE;
                            SCCCLDT.MTHS = WS_POST_FEQ;
                            S000_CALL_SCSSCLDT();
                            if (pgmRtn) return;
                            WS_DATE1 = SCCCLDT.DATE2;
                            WS_CNT += 1;
                        }
                        if (WS_DATE1 >= DDCUCPDT.CURR_DATE 
                            && WS_DATE1 < DDCUCPDT.NEXT_DATE) {
                            DDCUCPDT.CURR_POST_FLG = 'Y';
                            DDCUCPDT.NEXT_POST_DATE = WS_DATE1;
                        } else {
                            DDCUCPDT.CURR_POST_FLG = 'N';
                        }
                    }
                }
            } else {
                CEP.TRC(SCCGWA, "66666");
                DDCUCPDT.CURR_POST_FLG = 'Y';
                DDCUCPDT.NEXT_POST_DATE = DDCUCPDT.POST_DATE;
            }
        } else {
            CEP.TRC(SCCGWA, "77777");
            WS_CNT = 1;
            while (WS_DATE1 < DDCUCPDT.CURR_DATE 
                && WS_CNT <= 1000) {
                WS_POST_FEQ = (short) (DDCUCPDT.POST_FEQ * WS_CNT);
                CEP.TRC(SCCGWA, WS_POST_FEQ);
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = DDCUCPDT.POST_DATE;
                SCCCLDT.MTHS = WS_POST_FEQ;
                S000_CALL_SCSSCLDT();
                if (pgmRtn) return;
                WS_DATE1 = SCCCLDT.DATE2;
                WS_CNT += 1;
            }
            if (WS_DATE1 >= DDCUCPDT.CURR_DATE 
                && WS_DATE1 < DDCUCPDT.NEXT_DATE) {
                DDCUCPDT.CURR_POST_FLG = 'Y';
                DDCUCPDT.NEXT_POST_DATE = WS_DATE1;
            } else {
                DDCUCPDT.CURR_POST_FLG = 'N';
            }
        }
    }
    public void B023_GET_POST_DD_PROC() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, DDCUCPDT.POST_DATE+"", WS_POST_DATE);
        IBS.CPY2CLS(SCCGWA, DDCUCPDT.CURR_DATE+"", WS_CURR_DATE);
        IBS.init(SCCGWA, SCCCLDT);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_POST_DATE);
        SCCCLDT.DATE1 = Integer.parseInt(JIBS_tmp_str[0]);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_CURR_DATE);
        SCCCLDT.DATE2 = Integer.parseInt(JIBS_tmp_str[0]);
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        WS_DAYS = (short) SCCCLDT.DAYS;
        CEP.TRC(SCCGWA, SCCCLDT.DAYS);
        CEP.TRC(SCCGWA, WS_DAYS);
        WS_REMAINDER = (short) (WS_DAYS % DDCUCPDT.POST_FEQ);
        WS_QUOTIENT = (short) ((WS_DAYS - WS_REMAINDER) / DDCUCPDT.POST_FEQ);
        CEP.TRC(SCCGWA, WS_QUOTIENT);
        CEP.TRC(SCCGWA, WS_REMAINDER);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, WS_CURR_DATE);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_POST_DATE);
        if (JIBS_tmp_str[1].equalsIgnoreCase(JIBS_tmp_str[0])) {
            CEP.TRC(SCCGWA, "AAAA");
            DDCUCPDT.CURR_POST_FLG = 'Y';
            DDCUCPDT.NEXT_POST_DATE = DDCUCPDT.POST_DATE;
        }
        if (WS_QUOTIENT != 0 
            && WS_REMAINDER == 0) {
            CEP.TRC(SCCGWA, "BBBB");
            DDCUCPDT.CURR_POST_FLG = 'Y';
            DDCUCPDT.NEXT_POST_DATE = DDCUCPDT.CURR_DATE;
            CEP.TRC(SCCGWA, DDCUCPDT.NEXT_POST_DATE);
        } else {
            if (WS_REMAINDER != 0) {
                CEP.TRC(SCCGWA, "CCCC");
                DDCUCPDT.CURR_POST_FLG = 'N';
            }
        }
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            CEP.TRC(SCCGWA, SCCCLDT);
            if (WS_ERR_MSG == null) WS_ERR_MSG = "";
            JIBS_tmp_int = WS_ERR_MSG.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) WS_ERR_MSG += " ";
            WS_ERR_MSG = "SC" + WS_ERR_MSG.substring(2);
            if (WS_ERR_MSG == null) WS_ERR_MSG = "";
            JIBS_tmp_int = WS_ERR_MSG.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) WS_ERR_MSG += " ";
            JIBS_tmp_str[0] = "" + SCCCLDT.RC;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            WS_ERR_MSG = WS_ERR_MSG.substring(0, 3 - 1) + JIBS_tmp_str[0] + WS_ERR_MSG.substring(3 + 4 - 1);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
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
