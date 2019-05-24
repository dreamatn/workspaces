package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.DC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSQCHQ {
    int JIBS_tmp_int;
    brParm DDTCHQ_BR = new brParm();
    DBParm DDTSTOP_RD;
    String JIBS_tmp_str[] = new String[10];
    DBParm DDTMST_RD;
    boolean pgmRtn = false;
    String CPN_U_BPZFBVQU = "BP-F-BV-PRM-QUERY";
    String WS_ERR_MSG = " ";
    int WS_CHQ_CNT = 0;
    int WS_OUT_CNT = 0;
    int WS_CNT = 0;
    int WS_NXT_CNT = 0;
    int WS_P_CNT = 0;
    int WS_UNUSE_CNT = 0;
    int WS_PAID_CNT = 0;
    int WS_UNWRT_CNT = 0;
    int WS_WRITE_CNT = 0;
    int WS_DCD_CNT = 0;
    int WS_HLD_CNT = 0;
    String WS_CI_CNM = " ";
    char WS_SQCHQ_STS = ' ';
    long WS_STR_CHQ_NO = 0;
    long WS_END_CHQ_NO = 0;
    long WS_SQCHQ_STR_CHQ_NO = 0;
    long WS_SQCHQ_END_CHQ_NO = 0;
    long WS_CHQ_STR_CHQ_NO = 0;
    long WS_CHQ_END_CHQ_NO = 0;
    long WS_CURR_CHQ_NO = 0;
    long WS_OQCHQ_STR_CHQ_NO = 0;
    long WS_OQCHQ_END_CHQ_NO = 0;
    short WS_NUMH = 0;
    short WS_NUMN = 0;
    short WS_NUML = 0;
    short WS_NUMI = 0;
    short WS_NUME = 0;
    short WS_T = 0;
    char WS_INQ_FLG = ' ';
    char WS_CHQ_STS = ' ';
    char WS_CHQ_FLG = ' ';
    char WS_STOP_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CICACCU CICACCU = new CICACCU();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DDCOQCHQ DDCOQCHQ = new DDCOQCHQ();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    CICQACAC CICQACAC = new CICQACAC();
    DDRSTOP DDRSTOP = new DDRSTOP();
    DDRMST DDRMST = new DDRMST();
    DDRCHQ DDRCHQ = new DDRCHQ();
    SCCGWA SCCGWA;
    DDCSQCHQ DDCSQCHQ;
    public void MP(SCCGWA SCCGWA, DDCSQCHQ DDCSQCHQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSQCHQ = DDCSQCHQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSQCHQ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "********** INPUT DATA:");
        CEP.TRC(SCCGWA, DDCSQCHQ.AC_NO);
        CEP.TRC(SCCGWA, DDCSQCHQ.CHQ_TYP);
        CEP.TRC(SCCGWA, DDCSQCHQ.STR_CHQ_NO);
        CEP.TRC(SCCGWA, DDCSQCHQ.END_CHQ_NO);
        CEP.TRC(SCCGWA, DDCSQCHQ.CHQ_STS);
        CEP.TRC(SCCGWA, DDCSQCHQ.STR_CRT_DT);
        CEP.TRC(SCCGWA, DDCSQCHQ.END_CRT_DT);
        CEP.TRC(SCCGWA, "**********************");
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_PROC();
        if (pgmRtn) return;
        B150_GET_ACAC_INFO();
        if (pgmRtn) return;
        B200_INQ_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT_PROC() throws IOException,SQLException,Exception {
        if (DDCSQCHQ.STR_CHQ_NO.trim().length() > 0 
            && DDCSQCHQ.END_CHQ_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQ_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSQCHQ.STR_CHQ_NO.trim().length() == 0 
            && DDCSQCHQ.END_CHQ_NO.trim().length() > 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQ_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSQCHQ.STR_CHQ_NO.trim().length() > 0) {
            WS_NUMI = 0;
            WS_NUMI = DDCSQCHQ.STR_CHQ_NO.trim().length();
            CEP.TRC(SCCGWA, WS_NUMI);
            CEP.TRC(SCCGWA, WS_T);
            if (DDCSQCHQ.STR_CHQ_NO == null) DDCSQCHQ.STR_CHQ_NO = "";
            JIBS_tmp_int = DDCSQCHQ.STR_CHQ_NO.length();
            for (int i=0;i<16-JIBS_tmp_int;i++) DDCSQCHQ.STR_CHQ_NO += " ";
            if (!IBS.isNumeric(DDCSQCHQ.STR_CHQ_NO.substring(0, WS_NUMI))) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, WS_T);
        }
        if (DDCSQCHQ.END_CHQ_NO.trim().length() > 0) {
            WS_NUME = 0;
            WS_NUME = DDCSQCHQ.END_CHQ_NO.trim().length();
            CEP.TRC(SCCGWA, WS_NUME);
            if (DDCSQCHQ.END_CHQ_NO == null) DDCSQCHQ.END_CHQ_NO = "";
            JIBS_tmp_int = DDCSQCHQ.END_CHQ_NO.length();
            for (int i=0;i<16-JIBS_tmp_int;i++) DDCSQCHQ.END_CHQ_NO += " ";
            if (!IBS.isNumeric(DDCSQCHQ.END_CHQ_NO.substring(0, WS_NUME))) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DDCSQCHQ.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSQCHQ.AC_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, DDCSQCHQ.AC_NO);
            B010_10_CHECK_AC_PROC();
            if (pgmRtn) return;
            B010_20_CI_INF_PROC();
            if (pgmRtn) return;
            if (DDCSQCHQ.CHQ_TYP == ' ') {
                CEP.TRC(SCCGWA, "TYPE SPACE");
                CEP.TRC(SCCGWA, DDCSQCHQ.STR_CHQ_NO);
                CEP.TRC(SCCGWA, DDCSQCHQ.END_CHQ_NO);
                if (DDCSQCHQ.STR_CHQ_NO.trim().length() > 0 
                    && DDCSQCHQ.END_CHQ_NO.trim().length() > 0) {
                    WS_INQ_FLG = '3';
                    CEP.TRC(SCCGWA, "3333");
                } else {
                    WS_INQ_FLG = '1';
                    CEP.TRC(SCCGWA, "1111");
                }
            } else {
                CEP.TRC(SCCGWA, DDCSQCHQ.CHQ_TYP);
                CEP.TRC(SCCGWA, DDCSQCHQ.STR_CHQ_NO);
                CEP.TRC(SCCGWA, DDCSQCHQ.END_CHQ_NO);
                if (DDCSQCHQ.STR_CHQ_NO.trim().length() > 0 
                    && DDCSQCHQ.END_CHQ_NO.trim().length() > 0) {
                    WS_INQ_FLG = '0';
                    CEP.TRC(SCCGWA, "0000");
                } else {
                    WS_INQ_FLG = '2';
                    CEP.TRC(SCCGWA, "2222");
                }
            }
        } else {
            CEP.TRC(SCCGWA, "AC SPACE");
            CEP.TRC(SCCGWA, DDCSQCHQ.STR_CHQ_NO);
            CEP.TRC(SCCGWA, DDCSQCHQ.END_CHQ_NO);
            if (DDCSQCHQ.STR_CHQ_NO.trim().length() > 0 
                && DDCSQCHQ.END_CHQ_NO.trim().length() > 0) {
                if (DDCSQCHQ.CHQ_TYP == ' ') {
                    CEP.TRC(SCCGWA, "TYPE SAPCE");
                    WS_INQ_FLG = '4';
                    CEP.TRC(SCCGWA, "4444");
                } else {
                    CEP.TRC(SCCGWA, DDCSQCHQ.CHQ_TYP);
                    WS_INQ_FLG = '5';
                    CEP.TRC(SCCGWA, "5555");
                }
            } else {
                CEP.TRC(SCCGWA, "AC AND STR OR/AND END SPACE NOT SUPPORT");
            }
        }
    }
    public void B010_10_CHECK_AC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCSQCHQ.AC_NO;
        T000_READ_DDTMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRMST.AC_STS);
        if (DDRMST.AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_20_CI_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDCSQCHQ.AC_NO;
        S000_CALL_CISOACCU();
        if (pgmRtn) return;
    }
    public void B150_GET_ACAC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.AGR_NO = DDCSQCHQ.AC_NO;
        CICQACAC.DATA.CCY_ACAC = "156";
        CICQACAC.DATA.CR_FLG = '1';
        CICQACAC.FUNC = 'C';
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
    }
    public void B200_INQ_MAIN_PROC() throws IOException,SQLException,Exception {
        R000_OPEN_MPAGE_OUTPUT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_INQ_FLG);
        WS_SQCHQ_STS = DDCSQCHQ.CHQ_STS;
        if (WS_INQ_FLG == '0') {
            R000_STARTBR_DDTCHQ0();
            if (pgmRtn) return;
        } else if (WS_INQ_FLG == '1') {
            R000_STARTBR_DDTCHQ1();
            if (pgmRtn) return;
        } else if (WS_INQ_FLG == '2') {
            R000_STARTBR_DDTCHQ2();
            if (pgmRtn) return;
        } else if (WS_INQ_FLG == '3') {
            R000_STARTBR_DDTCHQ3();
            if (pgmRtn) return;
        } else if (WS_INQ_FLG == '4') {
            R000_STARTBR_DDTCHQ4();
            if (pgmRtn) return;
        } else if (WS_INQ_FLG == '5') {
            R000_STARTBR_DDTCHQ5();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DDCSQCHQ.CHQ_STS + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        T000_READNEXT_DDTCHQ();
        if (pgmRtn) return;
        while (WS_CHQ_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            WS_NUMN = 0;
            WS_NUMN = DDRCHQ.KEY.STR_CHQ_NO.trim().length();
            CEP.TRC(SCCGWA, WS_NUMN);
            CEP.TRC(SCCGWA, DDRCHQ.KEY.STR_CHQ_NO);
            CEP.TRC(SCCGWA, DDRCHQ.KEY.END_CHQ_NO);
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
            if (DDCSQCHQ.STR_CHQ_NO == null) DDCSQCHQ.STR_CHQ_NO = "";
            JIBS_tmp_int = DDCSQCHQ.STR_CHQ_NO.length();
            for (int i=0;i<16-JIBS_tmp_int;i++) DDCSQCHQ.STR_CHQ_NO += " ";
            if (DDCSQCHQ.STR_CHQ_NO.substring(0, WS_NUMN).trim().length() == 0) WS_SQCHQ_STR_CHQ_NO = 0;
            else WS_SQCHQ_STR_CHQ_NO = Long.parseLong(DDCSQCHQ.STR_CHQ_NO.substring(0, WS_NUMN));
            if (DDCSQCHQ.END_CHQ_NO == null) DDCSQCHQ.END_CHQ_NO = "";
            JIBS_tmp_int = DDCSQCHQ.END_CHQ_NO.length();
            for (int i=0;i<16-JIBS_tmp_int;i++) DDCSQCHQ.END_CHQ_NO += " ";
            if (DDCSQCHQ.END_CHQ_NO.substring(0, WS_NUMN).trim().length() == 0) WS_SQCHQ_END_CHQ_NO = 0;
            else WS_SQCHQ_END_CHQ_NO = Long.parseLong(DDCSQCHQ.END_CHQ_NO.substring(0, WS_NUMN));
            CEP.TRC(SCCGWA, WS_CHQ_STR_CHQ_NO);
            CEP.TRC(SCCGWA, WS_CHQ_END_CHQ_NO);
            CEP.TRC(SCCGWA, WS_SQCHQ_STR_CHQ_NO);
            CEP.TRC(SCCGWA, WS_SQCHQ_END_CHQ_NO);
            if (WS_INQ_FLG == '1' 
                || WS_INQ_FLG == '2') {
                if (WS_SQCHQ_STR_CHQ_NO > WS_CHQ_END_CHQ_NO) {
                    CEP.TRC(SCCGWA, "111 GT 222");
                    T000_READNEXT_DDTCHQ();
                    if (pgmRtn) return;
                }
                if (DDCSQCHQ.END_CHQ_NO.trim().length() > 0 
                    && WS_SQCHQ_END_CHQ_NO < WS_CHQ_STR_CHQ_NO) {
                    CEP.TRC(SCCGWA, "222 LT 111");
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
            if (WS_CHQ_STR_CHQ_NO >= WS_SQCHQ_STR_CHQ_NO) {
                CEP.TRC(SCCGWA, "CHQ STR GT= SQCHQ STR");
                WS_STR_CHQ_NO = WS_CHQ_STR_CHQ_NO;
            } else {
                CEP.TRC(SCCGWA, "CHQ STR LT SQCHQ STR");
                WS_STR_CHQ_NO = WS_SQCHQ_STR_CHQ_NO;
            }
            CEP.TRC(SCCGWA, WS_STR_CHQ_NO);
            WS_OUT_CNT = (int) (WS_STR_CHQ_NO - WS_CHQ_STR_CHQ_NO + 1);
            CEP.TRC(SCCGWA, WS_OUT_CNT);
            if (DDCSQCHQ.END_CHQ_NO.trim().length() == 0) {
                CEP.TRC(SCCGWA, "SQCHQ END SPACE");
                CEP.TRC(SCCGWA, DDCSQCHQ.END_CHQ_NO);
                WS_END_CHQ_NO = WS_CHQ_END_CHQ_NO;
            } else {
                CEP.TRC(SCCGWA, "SQCHQ END NOT SPACE");
                CEP.TRC(SCCGWA, DDCSQCHQ.END_CHQ_NO);
                if (WS_CHQ_END_CHQ_NO >= WS_SQCHQ_END_CHQ_NO) {
                    CEP.TRC(SCCGWA, "CHQ END GT= SQCHQ END");
                    WS_END_CHQ_NO = WS_SQCHQ_END_CHQ_NO;
                } else {
                    CEP.TRC(SCCGWA, "CHQ END LT SQCHQ END");
                    WS_END_CHQ_NO = WS_CHQ_END_CHQ_NO;
                }
            }
            CEP.TRC(SCCGWA, WS_END_CHQ_NO);
            WS_CHQ_CNT = (int) (WS_END_CHQ_NO - WS_CHQ_STR_CHQ_NO + 1);
            CEP.TRC(SCCGWA, WS_CHQ_CNT);
            WS_CHQ_STS = WS_SQCHQ_STS;
            CEP.TRC(SCCGWA, WS_CHQ_STS);
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
        if (WS_CHQ_STS == ' ') {
            B020_10_INQ_STS_ALL();
            if (pgmRtn) return;
        } else if (WS_CHQ_STS == '1'
            || WS_CHQ_STS == '2'
            || WS_CHQ_STS == '3'
            || WS_CHQ_STS == '4'
            || WS_CHQ_STS == '5'
            || WS_CHQ_STS == '6') {
            B020_20_INQ_STS_ONE();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DDCSQCHQ.CHQ_STS + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B020_10_INQ_STS_ALL() throws IOException,SQLException,Exception {
        WS_CNT = 0;
        WS_CURR_CHQ_NO = WS_CHQ_STR_CHQ_NO;
        CEP.TRC(SCCGWA, WS_CNT);
        CEP.TRC(SCCGWA, WS_OUT_CNT);
        for (WS_CNT = 1; WS_CNT <= WS_CHQ_CNT; WS_CNT += 1) {
            CEP.TRC(SCCGWA, WS_CNT);
            CEP.TRC(SCCGWA, WS_CHQ_CNT);
            if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
            JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
            CEP.TRC(SCCGWA, DDRCHQ.CHQ_STS.substring(WS_CNT - 1, WS_CNT + 1 - 1));
            WS_CURR_CHQ_NO = WS_CHQ_STR_CHQ_NO + WS_CNT - 1;
            WS_OQCHQ_END_CHQ_NO = WS_CURR_CHQ_NO;
            WS_OQCHQ_STR_CHQ_NO = WS_STR_CHQ_NO;
            CEP.TRC(SCCGWA, WS_CURR_CHQ_NO);
            if (WS_CNT >= WS_OUT_CNT) {
                if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
                JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
                CEP.TRC(SCCGWA, DDRCHQ.CHQ_STS.substring(WS_CNT - 1, WS_CNT + 1 - 1));
                WS_NXT_CNT = WS_CNT + 1;
                if (WS_NXT_CNT > WS_CHQ_CNT) {
                    CEP.TRC(SCCGWA, "WS-CHQ-END");
                    WS_NXT_CNT = WS_CHQ_CNT;
                }
                CEP.TRC(SCCGWA, WS_NXT_CNT);
                if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
                JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
                CEP.TRC(SCCGWA, DDRCHQ.CHQ_STS.substring(WS_NXT_CNT - 1, WS_NXT_CNT + 1 - 1));
                if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
                JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
                WS_CHQ_STS = DDRCHQ.CHQ_STS.substring(WS_CNT - 1, WS_CNT + 1 - 1).charAt(0);
                DDCSQCHQ.CHQ_TYP = DDRCHQ.KEY.CHQ_TYP;
                if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
                JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
                DDCSQCHQ.CHQ_STS = DDRCHQ.CHQ_STS.substring(WS_CNT - 1, WS_CNT + 1 - 1).charAt(0);
                CEP.TRC(SCCGWA, DDCSQCHQ.CHQ_TYP);
                CEP.TRC(SCCGWA, DDCSQCHQ.CHQ_STS);
                if (WS_CHQ_STS == '1') {
                    R000_OUT_ALL_STS_ONE();
                    if (pgmRtn) return;
                } else if (WS_CHQ_STS == '2') {
                    R000_OUT_ALL_STS_ONE();
                    if (pgmRtn) return;
                } else if (WS_CHQ_STS == '3') {
                    R000_OUT_ALL_STS_ONE();
                    if (pgmRtn) return;
                } else if (WS_CHQ_STS == '4') {
                    R000_OUT_ALL_STS_ONE();
                    if (pgmRtn) return;
                } else if (WS_CHQ_STS == '5') {
                    R000_OUT_ALL_STS_ONE();
                    if (pgmRtn) return;
                } else if (WS_CHQ_STS == '6') {
                    R000_OUT_ALL_STS_ONE();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B020_20_INQ_STS_ONE() throws IOException,SQLException,Exception {
        WS_CNT = 0;
        WS_CURR_CHQ_NO = WS_CHQ_STR_CHQ_NO;
        CEP.TRC(SCCGWA, WS_CNT);
        CEP.TRC(SCCGWA, WS_OUT_CNT);
        for (WS_CNT = 1; WS_CNT <= WS_CHQ_CNT; WS_CNT += 1) {
            CEP.TRC(SCCGWA, WS_CNT);
            CEP.TRC(SCCGWA, WS_CHQ_CNT);
            if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
            JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
            CEP.TRC(SCCGWA, DDRCHQ.CHQ_STS.substring(WS_CNT - 1, WS_CNT + 1 - 1));
            WS_CURR_CHQ_NO = WS_CHQ_STR_CHQ_NO + WS_CNT - 1;
            WS_OQCHQ_END_CHQ_NO = WS_CURR_CHQ_NO;
            CEP.TRC(SCCGWA, WS_CURR_CHQ_NO);
            if (WS_CNT >= WS_OUT_CNT) {
                DDCSQCHQ.CHQ_TYP = DDRCHQ.KEY.CHQ_TYP;
                if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
                JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
                DDCSQCHQ.CHQ_STS = DDRCHQ.CHQ_STS.substring(WS_CNT - 1, WS_CNT + 1 - 1).charAt(0);
                CEP.TRC(SCCGWA, DDCSQCHQ.CHQ_TYP);
                CEP.TRC(SCCGWA, DDCSQCHQ.CHQ_STS);
                if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
                JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
                CEP.TRC(SCCGWA, DDRCHQ.CHQ_STS.substring(WS_CNT - 1, WS_CNT + 1 - 1));
                R000_OUT_STS_ONE();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_OUT_ALL_STS_ONE() throws IOException,SQLException,Exception {
        if (WS_NXT_CNT > WS_CNT) {
            if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
            JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
            if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
            JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
            if (DDRCHQ.CHQ_STS.substring(WS_NXT_CNT - 1, WS_NXT_CNT + 1 - 1).equalsIgnoreCase(DDRCHQ.CHQ_STS.substring(WS_CNT - 1, WS_CNT + 1 - 1))) {
                WS_P_CNT += 1;
                CEP.TRC(SCCGWA, WS_OQCHQ_STR_CHQ_NO);
            } else {
                WS_P_CNT = WS_P_CNT + 1;
                CEP.TRC(SCCGWA, WS_P_CNT);
                WS_OQCHQ_STR_CHQ_NO = WS_OQCHQ_END_CHQ_NO - WS_P_CNT + 1;
                CEP.TRC(SCCGWA, WS_OQCHQ_STR_CHQ_NO);
                R000_OUTPUT_DETAIL();
                if (pgmRtn) return;
            }
        } else {
            WS_P_CNT = WS_P_CNT + 1;
            WS_OQCHQ_STR_CHQ_NO = WS_OQCHQ_END_CHQ_NO - WS_P_CNT + 1;
            CEP.TRC(SCCGWA, WS_OQCHQ_STR_CHQ_NO);
            CEP.TRC(SCCGWA, WS_P_CNT);
            R000_OUTPUT_DETAIL();
            if (pgmRtn) return;
        }
    }
    public void R000_OUT_STS_ONE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CHQ_STS);
        if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
        JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
        for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
        CEP.TRC(SCCGWA, DDRCHQ.CHQ_STS.substring(WS_CNT - 1, WS_CNT + 1 - 1));
        if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
        JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
        for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
        if (DDRCHQ.CHQ_STS.substring(WS_CNT - 1, WS_CNT + 1 - 1).equalsIgnoreCase(WS_CHQ_STS+"")) {
            CEP.TRC(SCCGWA, WS_CNT);
            WS_P_CNT += 1;
            WS_OQCHQ_STR_CHQ_NO = WS_STR_CHQ_NO;
            if (WS_CNT == WS_CHQ_CNT) {
                WS_OQCHQ_END_CHQ_NO = WS_END_CHQ_NO;
                CEP.TRC(SCCGWA, "IF IF OUTPUT-DETAIL");
                R000_OUTPUT_DETAIL();
                if (pgmRtn) return;
            }
        } else {
            WS_OQCHQ_END_CHQ_NO = WS_CURR_CHQ_NO - 1;
            CEP.TRC(SCCGWA, WS_OQCHQ_END_CHQ_NO);
            if (WS_OQCHQ_END_CHQ_NO >= WS_STR_CHQ_NO) {
                CEP.TRC(SCCGWA, "ELSE OUTPUT-DETAIL");
                R000_OUTPUT_DETAIL();
                if (pgmRtn) return;
            }
            WS_STR_CHQ_NO = WS_CHQ_STR_CHQ_NO + WS_CNT;
            CEP.TRC(SCCGWA, WS_STR_CHQ_NO);
        }
    }
    public void R000_CHQ_USE_ROWCNT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRCHQ.CHQ_BV_CD);
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
    public void R000_OPEN_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 416;
        if ("10".trim().length() == 0) SCCMPAG.SCR_ROW_CNT = 0;
        else SCCMPAG.SCR_ROW_CNT = Short.parseShort("10");
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_STARTBR_DDTCHQ0() throws IOException,SQLException,Exception {
        WS_CHQ_FLG = 'N';
        CEP.TRC(SCCGWA, "000000");
        IBS.init(SCCGWA, DDRCHQ);
        CEP.TRC(SCCGWA, "999999");
        DDRCHQ.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRCHQ.KEY.CHQ_TYP = DDCSQCHQ.CHQ_TYP;
        DDRCHQ.KEY.STR_CHQ_NO = DDCSQCHQ.STR_CHQ_NO;
        DDRCHQ.KEY.END_CHQ_NO = DDCSQCHQ.END_CHQ_NO;
        DDTCHQ_BR.rp = new DBParm();
        DDTCHQ_BR.rp.TableName = "DDTCHQ";
        DDTCHQ_BR.rp.where = "AC = :DDRCHQ.KEY.AC "
            + "AND CHQ_TYP = :DDRCHQ.KEY.CHQ_TYP "
            + "AND END_CHQ_NO >= :DDRCHQ.KEY.STR_CHQ_NO "
            + "AND STR_CHQ_NO <= :DDRCHQ.KEY.END_CHQ_NO";
        IBS.STARTBR(SCCGWA, DDRCHQ, this, DDTCHQ_BR);
        CEP.TRC(SCCGWA, "T000-STARTBR-DDTCHQ0  END");
    }
    public void R000_STARTBR_DDTCHQ1() throws IOException,SQLException,Exception {
        WS_CHQ_FLG = 'N';
        CEP.TRC(SCCGWA, "111111");
        IBS.init(SCCGWA, DDRCHQ);
        CEP.TRC(SCCGWA, "888888");
        DDRCHQ.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDTCHQ_BR.rp = new DBParm();
        DDTCHQ_BR.rp.TableName = "DDTCHQ";
        DDTCHQ_BR.rp.where = "AC = :DDRCHQ.KEY.AC";
        IBS.STARTBR(SCCGWA, DDRCHQ, this, DDTCHQ_BR);
        CEP.TRC(SCCGWA, "T000-STARTBR-DDTCHQ1  END");
    }
    public void R000_STARTBR_DDTCHQ2() throws IOException,SQLException,Exception {
        WS_CHQ_FLG = 'N';
        CEP.TRC(SCCGWA, "222222");
        IBS.init(SCCGWA, DDRCHQ);
        CEP.TRC(SCCGWA, "777777");
        DDRCHQ.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRCHQ.KEY.CHQ_TYP = DDCSQCHQ.CHQ_TYP;
        DDTCHQ_BR.rp = new DBParm();
        DDTCHQ_BR.rp.TableName = "DDTCHQ";
        DDTCHQ_BR.rp.where = "AC = :DDRCHQ.KEY.AC "
            + "AND CHQ_TYP = :DDRCHQ.KEY.CHQ_TYP";
        IBS.STARTBR(SCCGWA, DDRCHQ, this, DDTCHQ_BR);
        CEP.TRC(SCCGWA, "T000-STARTBR-DDTCHQ2  END");
    }
    public void R000_STARTBR_DDTCHQ3() throws IOException,SQLException,Exception {
        WS_CHQ_FLG = 'N';
        CEP.TRC(SCCGWA, "333333");
        IBS.init(SCCGWA, DDRCHQ);
        CEP.TRC(SCCGWA, "666666");
        DDRCHQ.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRCHQ.KEY.STR_CHQ_NO = DDCSQCHQ.STR_CHQ_NO;
        DDRCHQ.KEY.END_CHQ_NO = DDCSQCHQ.END_CHQ_NO;
        DDTCHQ_BR.rp = new DBParm();
        DDTCHQ_BR.rp.TableName = "DDTCHQ";
        DDTCHQ_BR.rp.where = "AC = :DDRCHQ.KEY.AC "
            + "AND END_CHQ_NO >= :DDRCHQ.KEY.STR_CHQ_NO "
            + "AND STR_CHQ_NO <= :DDRCHQ.KEY.END_CHQ_NO";
        IBS.STARTBR(SCCGWA, DDRCHQ, this, DDTCHQ_BR);
        CEP.TRC(SCCGWA, "T000-STARTBR-DDTCHQ4  END");
    }
    public void R000_STARTBR_DDTCHQ4() throws IOException,SQLException,Exception {
        WS_CHQ_FLG = 'N';
        CEP.TRC(SCCGWA, "444444");
        IBS.init(SCCGWA, DDRCHQ);
        CEP.TRC(SCCGWA, "222222222");
        DDRCHQ.KEY.STR_CHQ_NO = DDCSQCHQ.STR_CHQ_NO;
        DDRCHQ.KEY.END_CHQ_NO = DDCSQCHQ.END_CHQ_NO;
        DDTCHQ_BR.rp = new DBParm();
        DDTCHQ_BR.rp.TableName = "DDTCHQ";
        DDTCHQ_BR.rp.where = "END_CHQ_NO >= :DDRCHQ.KEY.STR_CHQ_NO "
            + "AND STR_CHQ_NO <= :DDRCHQ.KEY.END_CHQ_NO";
        IBS.STARTBR(SCCGWA, DDRCHQ, this, DDTCHQ_BR);
        CEP.TRC(SCCGWA, "T000-STARTBR-DDTCHQ4  END");
    }
    public void R000_STARTBR_DDTCHQ5() throws IOException,SQLException,Exception {
        WS_CHQ_FLG = 'N';
        CEP.TRC(SCCGWA, "555555");
        IBS.init(SCCGWA, DDRCHQ);
        CEP.TRC(SCCGWA, "222222222");
        DDRCHQ.KEY.CHQ_TYP = DDCSQCHQ.CHQ_TYP;
        DDRCHQ.KEY.STR_CHQ_NO = DDCSQCHQ.STR_CHQ_NO;
        DDRCHQ.KEY.END_CHQ_NO = DDCSQCHQ.END_CHQ_NO;
        CEP.TRC(SCCGWA, DDRCHQ.KEY.STR_CHQ_NO);
        CEP.TRC(SCCGWA, DDRCHQ.KEY.END_CHQ_NO);
        DDTCHQ_BR.rp = new DBParm();
        DDTCHQ_BR.rp.TableName = "DDTCHQ";
        DDTCHQ_BR.rp.where = "CHQ_TYP = :DDRCHQ.KEY.CHQ_TYP "
            + "AND END_CHQ_NO >= :DDRCHQ.KEY.STR_CHQ_NO "
            + "AND STR_CHQ_NO <= :DDRCHQ.KEY.END_CHQ_NO";
        IBS.STARTBR(SCCGWA, DDRCHQ, this, DDTCHQ_BR);
        CEP.TRC(SCCGWA, "T000-STARTBR-DDTCHQ5  END");
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
    public void T000_STARTBR_DDTSTOP_FIRST() throws IOException,SQLException,Exception {
        WS_STOP_FLG = 'N';
        CEP.TRC(SCCGWA, "STOP FIRST");
        IBS.init(SCCGWA, DDRSTOP);
        DDRSTOP.KEY.AC = DDRCHQ.KEY.AC;
        DDRSTOP.KEY.CHQ_NO = DDCOQCHQ.STR_CHQ_NO;
        CEP.TRC(SCCGWA, "############");
        DDTSTOP_RD = new DBParm();
        DDTSTOP_RD.TableName = "DDTSTOP";
        DDTSTOP_RD.where = "AC = :DDRSTOP.KEY.AC "
            + "AND CHQ_NO = :DDRSTOP.KEY.CHQ_NO";
        DDTSTOP_RD.col = "AC,CHQ_NO,JR_NNO,VCA_STS";
        DDTSTOP_RD.fst = true;
        IBS.READ(SCCGWA, DDRSTOP, this, DDTSTOP_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_STOP_FLG = 'Y';
            CEP.TRC(SCCGWA, DDRSTOP.KEY.JR_NNO);
        } else {
            WS_STOP_FLG = 'N';
            CEP.TRC(SCCGWA, "NOT FOUND");
        }
        CEP.TRC(SCCGWA, "T000-STARTBR-DDTSTOP-FIRST END");
    }
    public void R000_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        if (!DDRCHQ.KEY.AC.equalsIgnoreCase(DDCSQCHQ.AC_NO)) {
            CEP.TRC(SCCGWA, DDCSQCHQ.AC_NO);
            CEP.TRC(SCCGWA, DDRCHQ.KEY.AC);
            WS_UNUSE_CNT = 0;
            WS_PAID_CNT = 0;
            WS_UNWRT_CNT = 0;
            WS_WRITE_CNT = 0;
            WS_DCD_CNT = 0;
            WS_HLD_CNT = 0;
            CEP.TRC(SCCGWA, "000 000 000 000 000 000");
        }
        IBS.init(SCCGWA, DDCOQCHQ);
        CEP.TRC(SCCGWA, DDRCHQ.KEY.AC);
        DDCOQCHQ.AC_NO = DDCSQCHQ.AC_NO;
        DDCOQCHQ.CCY = DDRCHQ.CCY;
        DDCOQCHQ.CCY_TYPE = DDRCHQ.CCY_TYPE;
        DDCOQCHQ.CHQ_TYP = DDRCHQ.KEY.CHQ_TYP;
        if (WS_NUMN != 16) {
            WS_NUML = (short) (16 - WS_NUMN + 1);
            CEP.TRC(SCCGWA, WS_NUML);
            JIBS_tmp_str[0] = "" + WS_OQCHQ_STR_CHQ_NO;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<16-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            DDCOQCHQ.STR_CHQ_NO = JIBS_tmp_str[0].substring(WS_NUML - 1, WS_NUML + WS_NUMN - 1);
            JIBS_tmp_str[0] = "" + WS_OQCHQ_END_CHQ_NO;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<16-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            DDCOQCHQ.END_CHQ_NO = JIBS_tmp_str[0].substring(WS_NUML - 1, WS_NUML + WS_NUMN - 1);
        } else {
            DDCOQCHQ.STR_CHQ_NO = "" + WS_OQCHQ_STR_CHQ_NO;
            JIBS_tmp_int = DDCOQCHQ.STR_CHQ_NO.length();
            for (int i=0;i<16-JIBS_tmp_int;i++) DDCOQCHQ.STR_CHQ_NO = "0" + DDCOQCHQ.STR_CHQ_NO;
            DDCOQCHQ.END_CHQ_NO = "" + WS_OQCHQ_END_CHQ_NO;
            JIBS_tmp_int = DDCOQCHQ.END_CHQ_NO.length();
            for (int i=0;i<16-JIBS_tmp_int;i++) DDCOQCHQ.END_CHQ_NO = "0" + DDCOQCHQ.END_CHQ_NO;
        }
        CEP.TRC(SCCGWA, "CHQ-CHQ");
        CEP.TRC(SCCGWA, DDCOQCHQ.STR_CHQ_NO);
        CEP.TRC(SCCGWA, DDCOQCHQ.END_CHQ_NO);
        DDCOQCHQ.CHQ_STS = WS_CHQ_STS;
        DDCOQCHQ.CHQ_NUM = WS_P_CNT;
        DDCOQCHQ.CI_NAME = WS_CI_CNM;
        CEP.TRC(SCCGWA, WS_CHQ_STS);
        if (WS_CHQ_STS == '1') {
            CEP.TRC(SCCGWA, "1111");
            WS_UNUSE_CNT = WS_UNUSE_CNT + WS_P_CNT;
            CEP.TRC(SCCGWA, WS_UNUSE_CNT);
        } else if (WS_CHQ_STS == '2') {
            CEP.TRC(SCCGWA, "2222");
            WS_PAID_CNT = WS_PAID_CNT + WS_P_CNT;
            CEP.TRC(SCCGWA, WS_PAID_CNT);
        } else if (WS_CHQ_STS == '3') {
            CEP.TRC(SCCGWA, "3333");
            WS_UNWRT_CNT = WS_UNWRT_CNT + WS_P_CNT;
            CEP.TRC(SCCGWA, WS_UNWRT_CNT);
        } else if (WS_CHQ_STS == '4') {
            CEP.TRC(SCCGWA, "4444");
            WS_WRITE_CNT = WS_WRITE_CNT + WS_P_CNT;
            CEP.TRC(SCCGWA, WS_WRITE_CNT);
        } else if (WS_CHQ_STS == '5') {
            CEP.TRC(SCCGWA, "5555");
            WS_DCD_CNT = WS_DCD_CNT + WS_P_CNT;
            CEP.TRC(SCCGWA, WS_DCD_CNT);
        } else if (WS_CHQ_STS == '6') {
            CEP.TRC(SCCGWA, "6666");
            WS_HLD_CNT = WS_HLD_CNT + WS_P_CNT;
            CEP.TRC(SCCGWA, WS_HLD_CNT);
        }
        DDCOQCHQ.ISSU_DATE = DDRCHQ.CRT_DATE;
        DDCOQCHQ.ISSU_TLR = DDRCHQ.CRT_TLR;
        DDCOQCHQ.CRT_DATE = DDRCHQ.CRT_DATE;
        DDCOQCHQ.UNUSE_CNT = WS_UNUSE_CNT;
        DDCOQCHQ.PAID_CNT = WS_PAID_CNT;
        DDCOQCHQ.UNWRT_CNT = WS_UNWRT_CNT;
        DDCOQCHQ.WRITE_CNT = WS_WRITE_CNT;
        DDCOQCHQ.DCD_CNT = WS_DCD_CNT;
        DDCOQCHQ.HLD_CNT = WS_HLD_CNT;
        T000_STARTBR_DDTSTOP_FIRST();
        if (pgmRtn) return;
        if (WS_STOP_FLG == 'Y') {
            if (DDRSTOP.VCA_STS == '6') {
                DDCOQCHQ.JR_NNO = DDRSTOP.KEY.JR_NNO;
                DDCOQCHQ.WRT_FLG = 'N';
            } else {
                CEP.TRC(SCCGWA, "YYYYYY");
                DDCOQCHQ.JR_NNO = DDRSTOP.KEY.JR_NNO;
                DDCOQCHQ.WRT_FLG = 'Y';
            }
        } else {
            CEP.TRC(SCCGWA, "NNNNNN");
            DDCOQCHQ.JR_NNO = 0;
            DDCOQCHQ.WRT_FLG = 'N';
        }
        CEP.TRC(SCCGWA, DDCOQCHQ.JR_NNO);
        CEP.TRC(SCCGWA, DDCOQCHQ.WRT_FLG);
        WS_P_CNT = 0;
        CEP.TRC(SCCGWA, WS_P_CNT);
        IBS.init(SCCGWA, SCCMPAG);
        CEP.TRC(SCCGWA, "LIST OUTPUT:");
        CEP.TRC(SCCGWA, DDCOQCHQ.AC_NO);
        CEP.TRC(SCCGWA, DDCOQCHQ.CCY);
        CEP.TRC(SCCGWA, DDCOQCHQ.CCY_TYPE);
        CEP.TRC(SCCGWA, DDCOQCHQ.CHQ_TYP);
        CEP.TRC(SCCGWA, DDCOQCHQ.STR_CHQ_NO);
        CEP.TRC(SCCGWA, DDCOQCHQ.END_CHQ_NO);
        CEP.TRC(SCCGWA, DDCOQCHQ.CHQ_STS);
        CEP.TRC(SCCGWA, DDCOQCHQ.CHQ_NUM);
        CEP.TRC(SCCGWA, DDCOQCHQ.ISSU_DATE);
        CEP.TRC(SCCGWA, DDCOQCHQ.ISSU_TLR);
        CEP.TRC(SCCGWA, DDCOQCHQ.CRT_DATE);
        CEP.TRC(SCCGWA, DDCOQCHQ.UNUSE_CNT);
        CEP.TRC(SCCGWA, DDCOQCHQ.PAID_CNT);
        CEP.TRC(SCCGWA, DDCOQCHQ.UNWRT_CNT);
        CEP.TRC(SCCGWA, DDCOQCHQ.WRITE_CNT);
        CEP.TRC(SCCGWA, DDCOQCHQ.DCD_CNT);
        CEP.TRC(SCCGWA, DDCOQCHQ.HLD_CNT);
        CEP.TRC(SCCGWA, DDCOQCHQ.JR_NNO);
        CEP.TRC(SCCGWA, DDCOQCHQ.WRT_FLG);
        CEP.TRC(SCCGWA, DDCOQCHQ.CI_NAME);
        CEP.TRC(SCCGWA, "*************");
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, DDCOQCHQ);
        SCCMPAG.DATA_LEN = 416;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "@@@@@@@@@");
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
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        CEP.TRC(SCCGWA, DCCPACTY.RC);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
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
