package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.DC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSACHQ {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    brParm DDTCHQ_BR = new brParm();
    DBParm DDTMST_RD;
    DBParm DDTCCY_RD;
    boolean pgmRtn = false;
    String CPN_U_BPZFBVQU = "BP-F-BV-PRM-QUERY";
    String WS_ERR_MSG = " ";
    int WS_CHQ_CNT = 0;
    int WS_CNT = 0;
    int WS_P_CNT = 0;
    int WS_UNUSE_CNT = 0;
    int WS_PAID_CNT = 0;
    int WS_UNWRT_CNT = 0;
    int WS_WRITE_CNT = 0;
    int WS_DCD_CNT = 0;
    int WS_HLD_CNT = 0;
    int WS_BACK_CNT = 0;
    int WS_UNBK_CNT = 0;
    String WS_CI_CNM = " ";
    long WS_CURR_CHQ_NO = 0;
    long WS_STR_CHQ_NO = 0;
    long WS_END_CHQ_NO = 0;
    long WS_CHQ_STR_CHQ_NO = 0;
    long WS_CHQ_END_CHQ_NO = 0;
    short WS_NUMH = 0;
    short WS_NUMN = 0;
    short WS_L = 0;
    String WS_ACO_AC = " ";
    char WS_CHQ_STS = ' ';
    char WS_CHQ_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CICACCU CICACCU = new CICACCU();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DDCOACHQ DDCOACHQ = new DDCOACHQ();
    CICQACAC CICQACAC = new CICQACAC();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    DDRCHQ DDRCHQ = new DDRCHQ();
    int WS_STR_DT = 0;
    int WS_END_DT = 0;
    SCCGWA SCCGWA;
    DDCSACHQ DDCSACHQ;
    public void MP(SCCGWA SCCGWA, DDCSACHQ DDCSACHQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSACHQ = DDCSACHQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSACHQ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "********** INPUT DATA:");
        CEP.TRC(SCCGWA, DDCSACHQ.OPT);
        CEP.TRC(SCCGWA, DDCSACHQ.AC_NO);
        CEP.TRC(SCCGWA, DDCSACHQ.STR_CHQ_DT);
        CEP.TRC(SCCGWA, DDCSACHQ.END_CHQ_DT);
        CEP.TRC(SCCGWA, DDCSACHQ.CHQ_STS);
        CEP.TRC(SCCGWA, DDCSACHQ.CCY);
        CEP.TRC(SCCGWA, DDCSACHQ.CCY_TYPE);
        CEP.TRC(SCCGWA, "**********************");
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_PROC();
        if (pgmRtn) return;
        B200_INQ_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT_PROC() throws IOException,SQLException,Exception {
        B010_10_CHECK_AC_PROC();
        if (pgmRtn) return;
        if (DDCSACHQ.AC_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, DDCSACHQ.AC_NO);
            B010_20_CI_INF_PROC();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = "" + DCCPACTY.RC.RC_CODE;
            JIBS_tmp_int = WS_ERR_MSG.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_ERR_MSG = "0" + WS_ERR_MSG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_10_CHECK_AC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'C';
        CICQACAC.DATA.AGR_NO = DDCSACHQ.AC_NO;
        CICQACAC.DATA.CCY_ACAC = DDCSACHQ.CCY;
        CICQACAC.DATA.CR_FLG = DDCSACHQ.CCY_TYPE;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        WS_ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        CEP.TRC(SCCGWA, WS_ACO_AC);
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = WS_ACO_AC;
        T000_READ_DDTCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRCCY.KEY.AC);
        CEP.TRC(SCCGWA, DDRCCY.STS);
        if (DDRCCY.STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_CLEARED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_20_CI_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDCSACHQ.AC_NO;
        S000_CALL_CISOACCU();
        if (pgmRtn) return;
    }
    public void B200_INQ_MAIN_PROC() throws IOException,SQLException,Exception {
        R000_OPEN_MPAGE_OUTPUT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCSACHQ.CHQ_STS);
        WS_CHQ_STS = DDCSACHQ.CHQ_STS;
        CEP.TRC(SCCGWA, WS_CHQ_STS);
        R000_STARTBR_DDTCHQ0();
        if (pgmRtn) return;
        T000_READNEXT_DDTCHQ();
        if (pgmRtn) return;
        while (WS_CHQ_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            CEP.TRC(SCCGWA, DDRCHQ.KEY.STR_CHQ_NO);
            CEP.TRC(SCCGWA, DDRCHQ.KEY.END_CHQ_NO);
            WS_NUMN = 0;
            WS_NUMN = DDRCHQ.KEY.STR_CHQ_NO.trim().length();
            if (DDRCHQ.KEY.STR_CHQ_NO == null) DDRCHQ.KEY.STR_CHQ_NO = "";
            JIBS_tmp_int = DDRCHQ.KEY.STR_CHQ_NO.length();
            for (int i=0;i<16-JIBS_tmp_int;i++) DDRCHQ.KEY.STR_CHQ_NO += " ";
            if (DDRCHQ.KEY.STR_CHQ_NO.substring(0, WS_NUMN).trim().length() == 0) WS_CHQ_STR_CHQ_NO = 0;
            else WS_CHQ_STR_CHQ_NO = Long.parseLong(DDRCHQ.KEY.STR_CHQ_NO.substring(0, WS_NUMN));
            if (DDRCHQ.KEY.END_CHQ_NO == null) DDRCHQ.KEY.END_CHQ_NO = "";
            JIBS_tmp_int = DDRCHQ.KEY.END_CHQ_NO.length();
            for (int i=0;i<16-JIBS_tmp_int;i++) DDRCHQ.KEY.END_CHQ_NO += " ";
            if (DDRCHQ.KEY.END_CHQ_NO.substring(0, WS_NUMN).trim().length() == 0) WS_CHQ_END_CHQ_NO = 0;
            else WS_CHQ_END_CHQ_NO = Long.parseLong(DDRCHQ.KEY.END_CHQ_NO.substring(0, WS_NUMN));
            CEP.TRC(SCCGWA, WS_CHQ_STR_CHQ_NO);
            CEP.TRC(SCCGWA, WS_CHQ_END_CHQ_NO);
            WS_CHQ_CNT = (int) (WS_CHQ_END_CHQ_NO - WS_CHQ_STR_CHQ_NO + 1);
            CEP.TRC(SCCGWA, WS_CHQ_CNT);
            B020_INQ_CHEQUES_BY_STS();
            if (pgmRtn) return;
            T000_READNEXT_DDTCHQ();
            if (pgmRtn) return;
        }
        T000_ENDBR_DDTCHQ();
        if (pgmRtn) return;
    }
    public void B020_INQ_CHEQUES_BY_STS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CHQ_STS);
        WS_CNT = 0;
        if (WS_CHQ_STS == '0') {
            WS_UNUSE_CNT = 0;
            WS_PAID_CNT = 0;
            WS_UNWRT_CNT = 0;
            WS_WRITE_CNT = 0;
            WS_DCD_CNT = 0;
            WS_HLD_CNT = 0;
            B020_10_INQ_ALL();
            if (pgmRtn) return;
        } else if (WS_CHQ_STS == '7') {
            B020_20_INQ_UNBACK();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DDCSACHQ.CHQ_STS + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B020_10_INQ_ALL() throws IOException,SQLException,Exception {
        WS_CURR_CHQ_NO = WS_CHQ_STR_CHQ_NO;
        WS_STR_CHQ_NO = WS_CHQ_STR_CHQ_NO;
        WS_END_CHQ_NO = WS_CHQ_END_CHQ_NO;
        WS_P_CNT = WS_CHQ_CNT;
        CEP.TRC(SCCGWA, WS_STR_CHQ_NO);
        CEP.TRC(SCCGWA, WS_END_CHQ_NO);
        CEP.TRC(SCCGWA, WS_CURR_CHQ_NO);
        CEP.TRC(SCCGWA, WS_CNT);
        CEP.TRC(SCCGWA, WS_P_CNT);
        for (WS_CNT = 1; WS_CNT <= WS_CHQ_CNT; WS_CNT += 1) {
            CEP.TRC(SCCGWA, WS_CNT);
            CEP.TRC(SCCGWA, WS_CHQ_CNT);
            if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
            JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
            CEP.TRC(SCCGWA, DDRCHQ.CHQ_STS.substring(WS_CNT - 1, WS_CNT + 1 - 1));
            WS_CURR_CHQ_NO = WS_CHQ_STR_CHQ_NO + WS_CNT - 1;
                if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
                JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
            if (DDRCHQ.CHQ_STS.substring(WS_CNT - 1, WS_CNT + 1 - 1).equalsIgnoreCase("1")) {
                CEP.TRC(SCCGWA, "000111111");
                WS_UNUSE_CNT += 1;
                if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
                JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
            } else if (DDRCHQ.CHQ_STS.substring(WS_CNT - 1, WS_CNT + 1 - 1).equalsIgnoreCase("2")) {
                CEP.TRC(SCCGWA, "000222222");
                WS_PAID_CNT += 1;
                if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
                JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
            } else if (DDRCHQ.CHQ_STS.substring(WS_CNT - 1, WS_CNT + 1 - 1).equalsIgnoreCase("3")) {
                CEP.TRC(SCCGWA, "000333333");
                WS_UNWRT_CNT += 1;
                if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
                JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
            } else if (DDRCHQ.CHQ_STS.substring(WS_CNT - 1, WS_CNT + 1 - 1).equalsIgnoreCase("4")) {
                CEP.TRC(SCCGWA, "000444444");
                WS_WRITE_CNT += 1;
                if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
                JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
            } else if (DDRCHQ.CHQ_STS.substring(WS_CNT - 1, WS_CNT + 1 - 1).equalsIgnoreCase("5")) {
                CEP.TRC(SCCGWA, "000555555");
                WS_DCD_CNT += 1;
                if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
                JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
            } else if (DDRCHQ.CHQ_STS.substring(WS_CNT - 1, WS_CNT + 1 - 1).equalsIgnoreCase("6")) {
                CEP.TRC(SCCGWA, "000666666");
                WS_HLD_CNT += 1;
            }
        }
        IBS.init(SCCGWA, DDCOACHQ);
        R000_OUTPUT_DETAIL();
        if (pgmRtn) return;
        WS_UNUSE_CNT = 0;
        WS_UNBK_CNT = 0;
        WS_P_CNT = 0;
    }
    public void B020_20_INQ_UNBACK() throws IOException,SQLException,Exception {
        WS_CURR_CHQ_NO = WS_CHQ_STR_CHQ_NO;
        CEP.TRC(SCCGWA, WS_CNT);
        for (WS_CNT = 1; WS_CNT <= WS_CHQ_CNT; WS_CNT += 1) {
            CEP.TRC(SCCGWA, WS_CNT);
            CEP.TRC(SCCGWA, WS_CHQ_CNT);
            if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
            JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
            CEP.TRC(SCCGWA, DDRCHQ.CHQ_STS.substring(WS_CNT - 1, WS_CNT + 1 - 1));
            WS_CURR_CHQ_NO = WS_CHQ_STR_CHQ_NO + WS_CNT - 1;
            WS_STR_CHQ_NO = WS_CURR_CHQ_NO;
            WS_END_CHQ_NO = WS_CURR_CHQ_NO;
            CEP.TRC(SCCGWA, WS_CURR_CHQ_NO);
            IBS.init(SCCGWA, DDCOACHQ);
                if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
                JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
            if (DDRCHQ.CHQ_STS.substring(WS_CNT - 1, WS_CNT + 1 - 1).equalsIgnoreCase("1")) {
                CEP.TRC(SCCGWA, "777111111");
                WS_UNUSE_CNT += 1;
                if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
                JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
                DDCOACHQ.CHQ_STS = DDRCHQ.CHQ_STS.substring(WS_CNT - 1, WS_CNT + 1 - 1).charAt(0);
                if ("1".trim().length() == 0) WS_P_CNT = 0;
                else WS_P_CNT = Integer.parseInt("1");
                if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
                JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
            } else if (DDRCHQ.CHQ_STS.substring(WS_CNT - 1, WS_CNT + 1 - 1).equalsIgnoreCase("2")) {
                CEP.TRC(SCCGWA, "777222222");
                WS_PAID_CNT += 1;
                if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
                JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
                DDCOACHQ.CHQ_STS = DDRCHQ.CHQ_STS.substring(WS_CNT - 1, WS_CNT + 1 - 1).charAt(0);
                if ("1".trim().length() == 0) WS_P_CNT = 0;
                else WS_P_CNT = Integer.parseInt("1");
                if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
                JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
            } else if (DDRCHQ.CHQ_STS.substring(WS_CNT - 1, WS_CNT + 1 - 1).equalsIgnoreCase("3")) {
                CEP.TRC(SCCGWA, "777333333");
                WS_UNWRT_CNT += 1;
                if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
                JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
                DDCOACHQ.CHQ_STS = DDRCHQ.CHQ_STS.substring(WS_CNT - 1, WS_CNT + 1 - 1).charAt(0);
                if ("1".trim().length() == 0) WS_P_CNT = 0;
                else WS_P_CNT = Integer.parseInt("1");
                if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
                JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
            } else if (DDRCHQ.CHQ_STS.substring(WS_CNT - 1, WS_CNT + 1 - 1).equalsIgnoreCase("4")) {
                CEP.TRC(SCCGWA, "777444444");
                WS_WRITE_CNT += 1;
                if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
                JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
                DDCOACHQ.CHQ_STS = DDRCHQ.CHQ_STS.substring(WS_CNT - 1, WS_CNT + 1 - 1).charAt(0);
                if ("1".trim().length() == 0) WS_P_CNT = 0;
                else WS_P_CNT = Integer.parseInt("1");
                if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
                JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
            } else if (DDRCHQ.CHQ_STS.substring(WS_CNT - 1, WS_CNT + 1 - 1).equalsIgnoreCase("5")) {
                CEP.TRC(SCCGWA, "777555555");
                WS_DCD_CNT += 1;
                if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
                JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
                DDCOACHQ.CHQ_STS = DDRCHQ.CHQ_STS.substring(WS_CNT - 1, WS_CNT + 1 - 1).charAt(0);
                if ("1".trim().length() == 0) WS_P_CNT = 0;
                else WS_P_CNT = Integer.parseInt("1");
                R000_OUTPUT_DETAIL();
                if (pgmRtn) return;
                if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
                JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
            } else if (DDRCHQ.CHQ_STS.substring(WS_CNT - 1, WS_CNT + 1 - 1).equalsIgnoreCase("6")) {
                CEP.TRC(SCCGWA, "777666666");
                WS_HLD_CNT += 1;
                if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
                JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
                DDCOACHQ.CHQ_STS = DDRCHQ.CHQ_STS.substring(WS_CNT - 1, WS_CNT + 1 - 1).charAt(0);
                if ("1".trim().length() == 0) WS_P_CNT = 0;
                else WS_P_CNT = Integer.parseInt("1");
            }
        }
    }
    public void R000_OPEN_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 431;
        if ("10".trim().length() == 0) SCCMPAG.SCR_ROW_CNT = 0;
        else SCCMPAG.SCR_ROW_CNT = Short.parseShort("10");
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRCHQ.KEY.AC);
        CEP.TRC(SCCGWA, DCCPACTY.INPUT.AC);
        DDCOACHQ.AC_NO = DDCSACHQ.AC_NO;
        DDCOACHQ.AC_NAME = WS_CI_CNM;
        DDCOACHQ.CHQ_DT = DDRCHQ.CRT_DATE;
        DDCOACHQ.CCY = DDRCHQ.CCY;
        DDCOACHQ.CCY_TYPE = DDRCHQ.CCY_TYPE;
        DDCOACHQ.CHQ_TYP = DDRCHQ.KEY.CHQ_TYP;
        DDCOACHQ.STR_CHQ_NO = DDRCHQ.KEY.STR_CHQ_NO;
        DDCOACHQ.END_CHQ_NO = DDRCHQ.KEY.END_CHQ_NO;
        if (WS_CHQ_STS == '7') {
            if (WS_NUMN != 16) {
                WS_L = 0;
                WS_L = (short) (16 - WS_NUMN + 1);
            } else {
                WS_L = 0;
                WS_L = (short) (WS_L + 1);
            }
            JIBS_tmp_str[0] = "" + WS_STR_CHQ_NO;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<16-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            DDCOACHQ.STR_CHQ_NO = JIBS_tmp_str[0].substring(WS_L - 1, WS_L + WS_NUMN - 1);
            JIBS_tmp_str[0] = "" + WS_END_CHQ_NO;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<16-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            DDCOACHQ.END_CHQ_NO = JIBS_tmp_str[0].substring(WS_L - 1, WS_L + WS_NUMN - 1);
        }
        DDCOACHQ.CHQ_NO = "" + WS_CURR_CHQ_NO;
        JIBS_tmp_int = DDCOACHQ.CHQ_NO.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) DDCOACHQ.CHQ_NO = "0" + DDCOACHQ.CHQ_NO;
        DDCOACHQ.CHQ_NUM = WS_P_CNT;
        DDCOACHQ.UNUSE_CNT = WS_UNUSE_CNT;
        DDCOACHQ.PAID_CNT = WS_PAID_CNT;
        DDCOACHQ.UNWRT_CNT = WS_UNWRT_CNT;
        DDCOACHQ.WRITE_CNT = WS_WRITE_CNT;
        DDCOACHQ.DCD_CNT = WS_DCD_CNT;
        DDCOACHQ.HLD_CNT = WS_HLD_CNT;
        DDCOACHQ.BACK_CNT = WS_BACK_CNT;
        DDCOACHQ.UNBK_CNT = WS_UNBK_CNT;
        IBS.init(SCCGWA, SCCMPAG);
        CEP.TRC(SCCGWA, "LIST OUTPUT:");
        CEP.TRC(SCCGWA, DDCOACHQ.AC_NO);
        CEP.TRC(SCCGWA, DDCOACHQ.AC_NAME);
        CEP.TRC(SCCGWA, DDCOACHQ.CHQ_DT);
        CEP.TRC(SCCGWA, DDCOACHQ.CCY);
        CEP.TRC(SCCGWA, DDCOACHQ.CCY_TYPE);
        CEP.TRC(SCCGWA, DDCOACHQ.CHQ_TYP);
        CEP.TRC(SCCGWA, DDCOACHQ.STR_CHQ_NO);
        CEP.TRC(SCCGWA, DDCOACHQ.END_CHQ_NO);
        CEP.TRC(SCCGWA, DDCOACHQ.CHQ_NO);
        CEP.TRC(SCCGWA, DDCOACHQ.CHQ_STS);
        CEP.TRC(SCCGWA, DDCOACHQ.CHQ_NUM);
        CEP.TRC(SCCGWA, DDCOACHQ.PAID_CNT);
        CEP.TRC(SCCGWA, DDCOACHQ.DCD_CNT);
        CEP.TRC(SCCGWA, DDCOACHQ.BACK_CNT);
        CEP.TRC(SCCGWA, DDCOACHQ.UNUSE_CNT);
        CEP.TRC(SCCGWA, DDCOACHQ.UNWRT_CNT);
        CEP.TRC(SCCGWA, DDCOACHQ.WRITE_CNT);
        CEP.TRC(SCCGWA, DDCOACHQ.HLD_CNT);
        CEP.TRC(SCCGWA, DDCOACHQ.UNBK_CNT);
        CEP.TRC(SCCGWA, "*************");
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, DDCOACHQ);
        SCCMPAG.DATA_LEN = 431;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_CHQ_USE_ROWCNT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFBVQU);
        BPCFBVQU.TX_DATA.KEY.CODE = DDRCHQ.CHQ_BV_CD;
        S000_CALL_BPZFBVQU();
        if (pgmRtn) return;
        WS_NUMH = BPCFBVQU.TX_DATA.HEAD_LENGTH;
        WS_NUMN = BPCFBVQU.TX_DATA.NO_LENGTH;
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.HEAD_LENGTH);
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.NO_LENGTH);
    }
    public void S000_CALL_BPZFBVQU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_BPZFBVQU, BPCFBVQU);
        if (BPCFBVQU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFBVQU.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_STARTBR_DDTCHQ0() throws IOException,SQLException,Exception {
        WS_CHQ_FLG = 'N';
        CEP.TRC(SCCGWA, "000000");
        IBS.init(SCCGWA, DDRCHQ);
        CEP.TRC(SCCGWA, "777777");
        DDRCHQ.KEY.AC = WS_ACO_AC;
        WS_STR_DT = DDCSACHQ.STR_CHQ_DT;
        WS_END_DT = DDCSACHQ.END_CHQ_DT;
        CEP.TRC(SCCGWA, DDRCHQ.KEY.AC);
        CEP.TRC(SCCGWA, WS_STR_DT);
        CEP.TRC(SCCGWA, WS_END_DT);
        DDTCHQ_BR.rp = new DBParm();
        DDTCHQ_BR.rp.TableName = "DDTCHQ";
        DDTCHQ_BR.rp.where = "AC = :DDRCHQ.KEY.AC "
            + "AND CRT_DATE >= :WS_STR_DT "
            + "AND CRT_DATE <= :WS_END_DT";
        IBS.STARTBR(SCCGWA, DDRCHQ, this, DDTCHQ_BR);
        CEP.TRC(SCCGWA, "T000-STARTBR-DDTCHQ0  END");
    }
    public void T000_READNEXT_DDTCHQ() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRCHQ, this, DDTCHQ_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CHQ_FLG = 'Y';
        } else {
            WS_CHQ_FLG = 'N';
        }
    }
    public void T000_ENDBR_DDTCHQ() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTCHQ_BR);
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY2_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        CEP.TRC(SCCGWA, CICQACAC.RC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CISOACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        CEP.TRC(SCCGWA, CICACCU.RC);
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_CNM);
        CEP.TRC(SCCGWA, CICACCU.DATA.AC_CNM);
        if (CICACCU.DATA.AC_CNM.trim().length() == 0) {
            CEP.TRC(SCCGWA, CICACCU.DATA.CI_CNM);
            WS_CI_CNM = CICACCU.DATA.CI_CNM;
        } else {
            CEP.TRC(SCCGWA, CICACCU.DATA.AC_CNM);
            WS_CI_CNM = CICACCU.DATA.AC_CNM;
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
