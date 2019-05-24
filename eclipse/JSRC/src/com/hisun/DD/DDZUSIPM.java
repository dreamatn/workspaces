package com.hisun.DD;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class DDZUSIPM {
    boolean pgmRtn = false;
    String K_LOC_CCY = "156";
    String CPN_DD_S_SIGN_PROC = "DD-S-SIGN-PROC";
    String K_OUTPUT_FMT = "DD135";
    String WS_ERR_MSG = " ";
    short WS_CHQ_CNT = 0;
    short WS_CNT = 0;
    short WS_L_TCNT = 0;
    String WS_CHQ_STS = " ";
    long WS_USIPM_STR_CHQ_NO = 0;
    long WS_CHQ_STR_CHQ_NO = 0;
    long WS_STR_CHQ_NO1 = 0;
    long WS_END_CHQ_NO1 = 0;
    long WS_HLD_CHQ_NO = 0;
    long WS_CHQ_NO = 0;
    long WS_STR_NO = 0;
    long WS_END_NO = 0;
    long WS_STR_CHQ_NO = 0;
    long WS_END_CHQ_NO = 0;
    long WS_LOS_CHQ_NO = 0;
    int WS_LEH = 0;
    int WS_LEN = 0;
    short WS_STR_POS = 0;
    short WS_CUR_POS = 0;
    int WS_LEE = 0;
    char WS_CHQ_FLG = ' ';
    char WS_TOP_FLG = ' ';
    char WS_STOP_FLG = ' ';
    char WS_PGM_FLG = ' ';
    char WS_READ_STOP_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DDRCHQ DDRCHQ = new DDRCHQ();
    DDRSTOP DDRSTOP = new DDRSTOP();
    long WS_USIPM_STR_NO = 0;
    long WS_USIPM_END_NO = 0;
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    DDCUSIPM DDCUSIPM;
    public void MP(SCCGWA SCCGWA, DDCUSIPM DDCUSIPM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCUSIPM = DDCUSIPM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZUSIPM return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (DDCUSIPM.FUNC == '1') {
            B020_FREEZE_CHQB_PROC();
            if (pgmRtn) return;
        } else if (DDCUSIPM.FUNC == '2') {
            B030_QUERY_DDRSTOP_PROC();
            if (pgmRtn) return;
        } else if (DDCUSIPM.FUNC == '3') {
            B040_RLEHLD_STOP_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DDCUSIPM.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUSIPM.INPUT.AC_NO);
        CEP.TRC(SCCGWA, DDCUSIPM.INPUT.CCY);
        CEP.TRC(SCCGWA, DDCUSIPM.INPUT.CCY_TYPE);
        if (DDCUSIPM.INPUT.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDCUSIPM.FUNC);
        if (DDCUSIPM.FUNC != '3') {
            if (DDCUSIPM.INPUT.STR_CHQ_NO.trim().length() == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_STR_CHQ_NO_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCUSIPM.INPUT.END_CHQ_NO.trim().length() == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_END_CHQ_NO_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_FREEZE_CHQB_PROC() throws IOException,SQLException,Exception {
        WS_LEN = 0;
        WS_LEN = DDCUSIPM.INPUT.STR_CHQ_NO.trim().length();
        CEP.TRC(SCCGWA, WS_LEN);
        if (DDCUSIPM.INPUT.STR_CHQ_NO.trim().length() > 0) {
            if (WS_LEN != 8 
                && WS_LEN != 16) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQ_LEN_NOT_EQUAL_E;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCUSIPM.INPUT.STR_CHQ_NO == null) DDCUSIPM.INPUT.STR_CHQ_NO = "";
            JIBS_tmp_int = DDCUSIPM.INPUT.STR_CHQ_NO.length();
            for (int i=0;i<16-JIBS_tmp_int;i++) DDCUSIPM.INPUT.STR_CHQ_NO += " ";
            if (!IBS.isNumeric(DDCUSIPM.INPUT.STR_CHQ_NO.substring(0, WS_LEN))) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DDCUSIPM.INPUT.END_CHQ_NO.trim().length() > 0) {
            WS_LEE = 0;
            WS_LEE = DDCUSIPM.INPUT.END_CHQ_NO.trim().length();
            if (WS_LEE != 8 
                && WS_LEE != 16) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQ_LEN_NOT_EQUAL_E;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCUSIPM.INPUT.END_CHQ_NO == null) DDCUSIPM.INPUT.END_CHQ_NO = "";
            JIBS_tmp_int = DDCUSIPM.INPUT.END_CHQ_NO.length();
            for (int i=0;i<16-JIBS_tmp_int;i++) DDCUSIPM.INPUT.END_CHQ_NO += " ";
            if (!IBS.isNumeric(DDCUSIPM.INPUT.END_CHQ_NO.substring(0, WS_LEE))) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, DDRCHQ);
        DDRCHQ.KEY.AC = DDCUSIPM.INPUT.AC_NO;
        DDRCHQ.KEY.STR_CHQ_NO = DDCUSIPM.INPUT.STR_CHQ_NO;
        DDRCHQ.KEY.END_CHQ_NO = DDCUSIPM.INPUT.END_CHQ_NO;
        if (DDCUSIPM.INPUT.STR_CHQ_NO == null) DDCUSIPM.INPUT.STR_CHQ_NO = "";
        JIBS_tmp_int = DDCUSIPM.INPUT.STR_CHQ_NO.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) DDCUSIPM.INPUT.STR_CHQ_NO += " ";
        if (DDCUSIPM.INPUT.STR_CHQ_NO.substring(0, WS_LEN).trim().length() == 0) WS_STR_NO = 0;
        else WS_STR_NO = Long.parseLong(DDCUSIPM.INPUT.STR_CHQ_NO.substring(0, WS_LEN));
        if (DDCUSIPM.INPUT.END_CHQ_NO == null) DDCUSIPM.INPUT.END_CHQ_NO = "";
        JIBS_tmp_int = DDCUSIPM.INPUT.END_CHQ_NO.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) DDCUSIPM.INPUT.END_CHQ_NO += " ";
        if (DDCUSIPM.INPUT.END_CHQ_NO.substring(0, WS_LEN).trim().length() == 0) WS_END_NO = 0;
        else WS_END_NO = Long.parseLong(DDCUSIPM.INPUT.END_CHQ_NO.substring(0, WS_LEN));
        CEP.TRC(SCCGWA, WS_STR_NO);
        CEP.TRC(SCCGWA, WS_END_NO);
        CEP.TRC(SCCGWA, DDRCHQ.KEY.END_CHQ_NO);
        CEP.TRC(SCCGWA, DDRCHQ.KEY.STR_CHQ_NO);
        T000_STARTBR_DDTCHQ();
        if (pgmRtn) return;
        T000_READNEXT_DDTCHQ();
        if (pgmRtn) return;
        if (WS_CHQ_FLG == 'N') {
            if ((!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")) 
                && WS_LEN == 8) {
                T000_STARTBR_DDTCHQ1();
                if (pgmRtn) return;
                T000_READNEXT_DDTCHQ();
                if (pgmRtn) return;
                if (WS_CHQ_FLG == 'N') {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQB_REC_NOTFND;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            } else {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQB_REC_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        WS_LEH = 0;
        WS_LEH = DDRCHQ.KEY.STR_CHQ_NO.trim().length();
        CEP.TRC(SCCGWA, WS_LEH);
        WS_PGM_FLG = 'N';
        while (WS_CHQ_FLG != 'N' 
            && WS_CHQ_NO <= WS_END_NO 
            && WS_PGM_FLG != 'Y') {
            CEP.TRC(SCCGWA, WS_USIPM_STR_CHQ_NO);
            CEP.TRC(SCCGWA, WS_CHQ_STR_CHQ_NO);
            WS_LEH = 0;
            WS_LEH = DDRCHQ.KEY.STR_CHQ_NO.trim().length();
            CEP.TRC(SCCGWA, WS_LEH);
            if (WS_LEH == 8 
                && WS_LEN == 16) {
                T000_READNEXT_DDTCHQ();
                if (pgmRtn) return;
            } else {
                WS_PGM_FLG = 'Y';
                if (DDCUSIPM.INPUT.STR_CHQ_NO == null) DDCUSIPM.INPUT.STR_CHQ_NO = "";
                JIBS_tmp_int = DDCUSIPM.INPUT.STR_CHQ_NO.length();
                for (int i=0;i<16-JIBS_tmp_int;i++) DDCUSIPM.INPUT.STR_CHQ_NO += " ";
                if (DDCUSIPM.INPUT.STR_CHQ_NO.substring(0, WS_LEN).trim().length() == 0) WS_CHQ_NO = 0;
                else WS_CHQ_NO = Long.parseLong(DDCUSIPM.INPUT.STR_CHQ_NO.substring(0, WS_LEN));
                if (DDRCHQ.KEY.STR_CHQ_NO == null) DDRCHQ.KEY.STR_CHQ_NO = "";
                JIBS_tmp_int = DDRCHQ.KEY.STR_CHQ_NO.length();
                for (int i=0;i<16-JIBS_tmp_int;i++) DDRCHQ.KEY.STR_CHQ_NO += " ";
                if (DDRCHQ.KEY.STR_CHQ_NO.substring(0, WS_LEN).trim().length() == 0) WS_STR_CHQ_NO = 0;
                else WS_STR_CHQ_NO = Long.parseLong(DDRCHQ.KEY.STR_CHQ_NO.substring(0, WS_LEN));
                if (DDRCHQ.KEY.END_CHQ_NO == null) DDRCHQ.KEY.END_CHQ_NO = "";
                JIBS_tmp_int = DDRCHQ.KEY.END_CHQ_NO.length();
                for (int i=0;i<16-JIBS_tmp_int;i++) DDRCHQ.KEY.END_CHQ_NO += " ";
                if (DDRCHQ.KEY.END_CHQ_NO.substring(0, WS_LEN).trim().length() == 0) WS_END_CHQ_NO = 0;
                else WS_END_CHQ_NO = Long.parseLong(DDRCHQ.KEY.END_CHQ_NO.substring(0, WS_LEN));
                if (WS_LEH == 16 
                    && WS_LEN == 8) {
                    if (DDRCHQ.KEY.STR_CHQ_NO == null) DDRCHQ.KEY.STR_CHQ_NO = "";
                    JIBS_tmp_int = DDRCHQ.KEY.STR_CHQ_NO.length();
                    for (int i=0;i<16-JIBS_tmp_int;i++) DDRCHQ.KEY.STR_CHQ_NO += " ";
                    if (DDRCHQ.KEY.STR_CHQ_NO.substring(9 - 1, 9 + WS_LEN - 1).trim().length() == 0) WS_STR_CHQ_NO = 0;
                    else WS_STR_CHQ_NO = Long.parseLong(DDRCHQ.KEY.STR_CHQ_NO.substring(9 - 1, 9 + WS_LEN - 1));
                    if (DDRCHQ.KEY.END_CHQ_NO == null) DDRCHQ.KEY.END_CHQ_NO = "";
                    JIBS_tmp_int = DDRCHQ.KEY.END_CHQ_NO.length();
                    for (int i=0;i<16-JIBS_tmp_int;i++) DDRCHQ.KEY.END_CHQ_NO += " ";
                    if (DDRCHQ.KEY.END_CHQ_NO.substring(9 - 1, 9 + WS_LEN - 1).trim().length() == 0) WS_END_CHQ_NO = 0;
                    else WS_END_CHQ_NO = Long.parseLong(DDRCHQ.KEY.END_CHQ_NO.substring(9 - 1, 9 + WS_LEN - 1));
                }
                CEP.TRC(SCCGWA, WS_CHQ_NO);
                CEP.TRC(SCCGWA, WS_STR_CHQ_NO);
                CEP.TRC(SCCGWA, WS_END_CHQ_NO);
