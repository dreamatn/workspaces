package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZKDGAC {
    String JIBS_tmp_str[] = new String[10];
    String JIBS_NumStr;
    String JIBS_f0;
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String WS_AC_BYTE = " ";
    short WS_DGT = 0;
    short WS_RET = 0;
    short WS_RMDR = 0;
    short WS_RMDR1 = 0;
    short WS_MAX_BIT = 0;
    short WS_MAX_BIT_1 = 0;
    short I = 0;
    short WS_NUM = 0;
    BPZKDGAC_WS_SEQ WS_SEQ = new BPZKDGAC_WS_SEQ();
    BPZKDGAC_WS_AC_BYTE_R WS_AC_BYTE_R = new BPZKDGAC_WS_AC_BYTE_R();
    BPZKDGAC_WS_ACNO WS_ACNO = new BPZKDGAC_WS_ACNO();
    SCCCTLM_MSG SCCCTLM_MSG = new SCCCTLM_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGWA SCCGWA;
    BPCKDGAC BPCKDGAC;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA, BPCKDGAC BPCKDGAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCKDGAC = BPCKDGAC;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZKDGAC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        IBS.init(SCCGWA, BPCKDGAC.RC);
        WS_MAX_BIT = 32;
        WS_MAX_BIT_1 = (short) (WS_MAX_BIT - 1);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B10_CHK_INPUT();
        if (pgmRtn) return;
        if (BPCKDGAC.FUNC == 'G') {
            if (BPCKDGAC.TYPE_FLG == '2') {
                B21_GEN_DIGIT();
                if (pgmRtn) return;
            } else {
                if (BPCKDGAC.TYPE_FLG == '3') {
                    B22_GEN_DIGIT();
                    if (pgmRtn) return;
                } else {
                    B20_GEN_DIGIT();
                    if (pgmRtn) return;
                }
            }
        } else if (BPCKDGAC.FUNC == 'K') {
            if (BPCKDGAC.TYPE_FLG == '2') {
                B31_CHK_DIGIT();
                if (pgmRtn) return;
            } else {
                B30_CHK_DIGIT();
                if (pgmRtn) return;
            }
        } else {
        }
    }
    public void B10_CHK_INPUT() throws IOException,SQLException,Exception {
        if ((BPCKDGAC.FUNC != 'G' 
            && BPCKDGAC.FUNC != 'K')) {
            IBS.CPY2CLS(SCCGWA, SCCCTLM_MSG.SC_ERR_INVALID_INPUT, BPCKDGAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B20_GEN_DIGIT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_ACNO.WS_ACNO_NO_DIG);
        for (I = 1; I <= 50; I += 1) {
            WS_ACNO.WS_ACNO_NO_DIG.WS_ACNO_NO_BIT[I-1] = 0;
        }
        JIBS_tmp_str[0] = BPCKDGAC.NO;
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ACNO);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ACNO.WS_ACNO_NO_DIG);
        if (!IBS.isNumeric(JIBS_tmp_str[0])) {
            IBS.CPY2CLS(SCCGWA, SCCCTLM_MSG.SC_ERR_INVALID_INPUT, BPCKDGAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CPY2CLS(SCCGWA, BPCKDGAC.NO, WS_AC_BYTE_R);
        for (I = 1; I <= WS_MAX_BIT_1; I += 1) {
            WS_RMDR = (short) (I % 3);
            WS_RET = (short) ((I - WS_RMDR) / 3);
            if (WS_RMDR == 0) {
                WS_DGT = (short) (WS_DGT + WS_AC_BYTE_R.B[I-1] * 1);
            } else if (WS_RMDR == 1) {
                WS_DGT = (short) (WS_DGT + WS_AC_BYTE_R.B[I-1] * 3);
            } else if (WS_RMDR == 2) {
                WS_DGT = (short) (WS_DGT + WS_AC_BYTE_R.B[I-1] * 7);
            } else {
            }
        }
        if (BPCRBANK.AC_CHK == '1') {
            WS_RMDR = (short) (WS_DGT % 10);
            WS_RET = (short) ((WS_DGT - WS_RMDR) / 10);
            if (WS_RMDR != 0) {
                WS_RMDR = (short) (10 - WS_RMDR);
            }
            BPCKDGAC.DIG = WS_RMDR;
        } else {
            WS_RMDR = (short) (WS_DGT % 11);
            WS_RET = (short) ((WS_DGT - WS_RMDR) / 11);
            if (WS_RMDR == 0 
                || WS_RMDR == 1) {
                if (WS_RMDR == 0) {
                    BPCKDGAC.DIG = WS_RMDR;
                } else {
                    IBS.CPY2CLS(SCCGWA, SCCCTLM_MSG.SC_ERR_GEN_DIG, BPCKDGAC.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            } else {
                WS_RMDR = (short) (11 - WS_RMDR);
                BPCKDGAC.DIG = WS_RMDR;
            }
        }
    }
    public void B21_GEN_DIGIT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_ACNO.WS_ACNO_NO_DIG);
        for (I = 1; I <= 50; I += 1) {
            WS_ACNO.WS_ACNO_NO_DIG.WS_ACNO_NO_BIT[I-1] = 0;
        }
        JIBS_tmp_str[0] = BPCKDGAC.NO;
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ACNO);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ACNO.WS_ACNO_NO_DIG);
        if (!IBS.isNumeric(JIBS_tmp_str[0])) {
            IBS.CPY2CLS(SCCGWA, SCCCTLM_MSG.SC_ERR_INVALID_INPUT, BPCKDGAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ACNO);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_AC_BYTE_R);
        WS_DGT = (short) (WS_AC_BYTE_R.B[01-1] + WS_AC_BYTE_R.B[03-1] + WS_AC_BYTE_R.B[05-1] + WS_AC_BYTE_R.B[07-1] + WS_AC_BYTE_R.B[09-1] + WS_AC_BYTE_R.B[11-1] + WS_AC_BYTE_R.B[13-1] + WS_AC_BYTE_R.B[15-1] + WS_AC_BYTE_R.B[17-1] + WS_AC_BYTE_R.B[19-1] + WS_AC_BYTE_R.B[21-1]);
        WS_RMDR1 = (short) (WS_DGT % 10);
        WS_RET = (short) ((WS_DGT - WS_RMDR1) / 10);
        if (WS_RMDR1 == 0) {
            JIBS_tmp_str[0] = "" + BPCKDGAC.2DIG;
            JIBS_f0 = "";
            for (int i=0;i<2-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
            JIBS_NumStr = JIBS_f0 + BPCKDGAC.2DIG;
            JIBS_tmp_str[1] = "" + 0;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<0-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            JIBS_NumStr = JIBS_tmp_str[1] + JIBS_NumStr.substring(1);
            BPCKDGAC.2DIG = Short.parseShort(JIBS_NumStr);
        } else {
            WS_RMDR1 = (short) (10 - WS_RMDR1);
            JIBS_tmp_str[0] = "" + BPCKDGAC.2DIG;
            JIBS_f0 = "";
            for (int i=0;i<2-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
            JIBS_NumStr = JIBS_f0 + BPCKDGAC.2DIG;
            JIBS_tmp_str[1] = "" + WS_RMDR1;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            JIBS_NumStr = JIBS_tmp_str[1] + JIBS_NumStr.substring(1);
            BPCKDGAC.2DIG = Short.parseShort(JIBS_NumStr);
        }
        WS_DGT = 0;
        WS_NUM = (short) (WS_AC_BYTE_R.B[02-1] * 2);
        WS_DGT = (short) (WS_SEQ.WS_SEQ1 + WS_SEQ.WS_SEQ2 + WS_DGT);
        WS_NUM = (short) (WS_AC_BYTE_R.B[04-1] * 2);
        WS_DGT = (short) (WS_SEQ.WS_SEQ1 + WS_SEQ.WS_SEQ2 + WS_DGT);
        WS_NUM = (short) (WS_AC_BYTE_R.B[06-1] * 2);
        WS_DGT = (short) (WS_SEQ.WS_SEQ1 + WS_SEQ.WS_SEQ2 + WS_DGT);
        WS_NUM = (short) (WS_AC_BYTE_R.B[08-1] * 2);
        WS_DGT = (short) (WS_SEQ.WS_SEQ1 + WS_SEQ.WS_SEQ2 + WS_DGT);
        WS_NUM = (short) (WS_AC_BYTE_R.B[10-1] * 2);
        WS_DGT = (short) (WS_SEQ.WS_SEQ1 + WS_SEQ.WS_SEQ2 + WS_DGT);
        WS_NUM = (short) (WS_AC_BYTE_R.B[12-1] * 2);
        WS_DGT = (short) (WS_SEQ.WS_SEQ1 + WS_SEQ.WS_SEQ2 + WS_DGT);
        WS_NUM = (short) (WS_AC_BYTE_R.B[14-1] * 2);
        WS_DGT = (short) (WS_SEQ.WS_SEQ1 + WS_SEQ.WS_SEQ2 + WS_DGT);
        WS_NUM = (short) (WS_AC_BYTE_R.B[16-1] * 2);
        WS_DGT = (short) (WS_SEQ.WS_SEQ1 + WS_SEQ.WS_SEQ2 + WS_DGT);
        WS_NUM = (short) (WS_AC_BYTE_R.B[18-1] * 2);
        WS_DGT = (short) (WS_SEQ.WS_SEQ1 + WS_SEQ.WS_SEQ2 + WS_DGT);
        WS_NUM = (short) (WS_AC_BYTE_R.B[20-1] * 2);
        WS_DGT = (short) (WS_SEQ.WS_SEQ1 + WS_SEQ.WS_SEQ2 + WS_DGT);
        WS_RMDR1 = (short) (WS_DGT % 10);
        WS_RET = (short) ((WS_DGT - WS_RMDR1) / 10);
        if (WS_RMDR1 == 0) {
            JIBS_tmp_str[0] = "" + BPCKDGAC.2DIG;
            JIBS_f0 = "";
            for (int i=0;i<2-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
            JIBS_NumStr = JIBS_f0 + BPCKDGAC.2DIG;
            JIBS_tmp_str[1] = "" + 0;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<0-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            JIBS_NumStr = JIBS_NumStr.substring(0, 2 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(2 + 1 - 1);
            BPCKDGAC.2DIG = Short.parseShort(JIBS_NumStr);
        } else {
            WS_RMDR1 = (short) (10 - WS_RMDR1);
            JIBS_tmp_str[0] = "" + BPCKDGAC.2DIG;
            JIBS_f0 = "";
            for (int i=0;i<2-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
            JIBS_NumStr = JIBS_f0 + BPCKDGAC.2DIG;
            JIBS_tmp_str[1] = "" + WS_RMDR1;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            JIBS_NumStr = JIBS_NumStr.substring(0, 2 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(2 + 1 - 1);
            BPCKDGAC.2DIG = Short.parseShort(JIBS_NumStr);
        }
    }
    public void B22_GEN_DIGIT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_ACNO.WS_ACNO_NO_DIG);
        for (I = 1; I <= 50; I += 1) {
            WS_ACNO.WS_ACNO_NO_DIG.WS_ACNO_NO_BIT[I-1] = 0;
        }
        JIBS_tmp_str[0] = BPCKDGAC.NO;
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ACNO);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ACNO.WS_ACNO_NO_DIG);
        if (!IBS.isNumeric(JIBS_tmp_str[0])) {
            IBS.CPY2CLS(SCCGWA, SCCCTLM_MSG.SC_ERR_INVALID_INPUT, BPCKDGAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CPY2CLS(SCCGWA, BPCKDGAC.NO, WS_AC_BYTE_R);
        WS_DGT = (short) (WS_AC_BYTE_R.B[01-1] + WS_AC_BYTE_R.B[03-1] + WS_AC_BYTE_R.B[05-1] + WS_AC_BYTE_R.B[07-1] + WS_AC_BYTE_R.B[09-1] + WS_AC_BYTE_R.B[11-1]);
        WS_RMDR1 = (short) (WS_DGT % 10);
        WS_RET = (short) ((WS_DGT - WS_RMDR1) / 10);
        if (WS_RMDR1 == 0) {
            JIBS_tmp_str[0] = "" + BPCKDGAC.2DIG;
            JIBS_f0 = "";
            for (int i=0;i<2-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
            JIBS_NumStr = JIBS_f0 + BPCKDGAC.2DIG;
            JIBS_tmp_str[1] = "" + 0;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<0-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            JIBS_NumStr = JIBS_tmp_str[1] + JIBS_NumStr.substring(1);
            BPCKDGAC.2DIG = Short.parseShort(JIBS_NumStr);
        } else {
            WS_RMDR1 = (short) (10 - WS_RMDR1);
            JIBS_tmp_str[0] = "" + BPCKDGAC.2DIG;
            JIBS_f0 = "";
            for (int i=0;i<2-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
            JIBS_NumStr = JIBS_f0 + BPCKDGAC.2DIG;
            JIBS_tmp_str[1] = "" + WS_RMDR1;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            JIBS_NumStr = JIBS_tmp_str[1] + JIBS_NumStr.substring(1);
            BPCKDGAC.2DIG = Short.parseShort(JIBS_NumStr);
        }
        WS_DGT = 0;
        WS_NUM = (short) (WS_AC_BYTE_R.B[02-1] * 2);
        WS_DGT = (short) (WS_SEQ.WS_SEQ1 + WS_SEQ.WS_SEQ2 + WS_DGT);
        WS_NUM = (short) (WS_AC_BYTE_R.B[04-1] * 2);
        WS_DGT = (short) (WS_SEQ.WS_SEQ1 + WS_SEQ.WS_SEQ2 + WS_DGT);
        WS_NUM = (short) (WS_AC_BYTE_R.B[06-1] * 2);
        WS_DGT = (short) (WS_SEQ.WS_SEQ1 + WS_SEQ.WS_SEQ2 + WS_DGT);
        WS_NUM = (short) (WS_AC_BYTE_R.B[08-1] * 2);
        WS_DGT = (short) (WS_SEQ.WS_SEQ1 + WS_SEQ.WS_SEQ2 + WS_DGT);
        WS_NUM = (short) (WS_AC_BYTE_R.B[10-1] * 2);
        WS_DGT = (short) (WS_SEQ.WS_SEQ1 + WS_SEQ.WS_SEQ2 + WS_DGT);
        WS_NUM = (short) (WS_AC_BYTE_R.B[12-1] * 2);
        WS_DGT = (short) (WS_SEQ.WS_SEQ1 + WS_SEQ.WS_SEQ2 + WS_DGT);
        WS_RMDR1 = (short) (WS_DGT % 10);
        WS_RET = (short) ((WS_DGT - WS_RMDR1) / 10);
        if (WS_RMDR1 == 0) {
            JIBS_tmp_str[0] = "" + BPCKDGAC.2DIG;
            JIBS_f0 = "";
            for (int i=0;i<2-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
            JIBS_NumStr = JIBS_f0 + BPCKDGAC.2DIG;
            JIBS_tmp_str[1] = "" + 0;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<0-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            JIBS_NumStr = JIBS_NumStr.substring(0, 2 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(2 + 1 - 1);
            BPCKDGAC.2DIG = Short.parseShort(JIBS_NumStr);
        } else {
            WS_RMDR1 = (short) (10 - WS_RMDR1);
            JIBS_tmp_str[0] = "" + BPCKDGAC.2DIG;
            JIBS_f0 = "";
            for (int i=0;i<2-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
            JIBS_NumStr = JIBS_f0 + BPCKDGAC.2DIG;
            JIBS_tmp_str[1] = "" + WS_RMDR1;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            JIBS_NumStr = JIBS_NumStr.substring(0, 2 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(2 + 1 - 1);
            BPCKDGAC.2DIG = Short.parseShort(JIBS_NumStr);
        }
    }
    public void B30_CHK_DIGIT() throws IOException,SQLException,Exception {
        if (!IBS.isNumeric(BPCKDGAC.NO)) {
            IBS.CPY2CLS(SCCGWA, SCCCTLM_MSG.SC_ERR_INVALID_INPUT, BPCKDGAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CPY2CLS(SCCGWA, BPCKDGAC.NO, WS_AC_BYTE_R);
        for (I = 1; I <= WS_MAX_BIT; I += 1) {
            WS_RMDR = (short) (I % 3);
            WS_RET = (short) ((I - WS_RMDR) / 3);
            if (WS_RMDR == 0) {
                WS_DGT = (short) (WS_DGT + WS_AC_BYTE_R.B[I-1] * 1);
            } else if (WS_RMDR == 1) {
                WS_DGT = (short) (WS_DGT + WS_AC_BYTE_R.B[I-1] * 3);
            } else if (WS_RMDR == 2) {
                WS_DGT = (short) (WS_DGT + WS_AC_BYTE_R.B[I-1] * 7);
            } else {
            }
        }
        if (BPCRBANK.AC_CHK == '1') {
            WS_RMDR = (short) (WS_DGT % 10);
            WS_RET = (short) ((WS_DGT - WS_RMDR) / 10);
        } else {
            WS_RMDR = (short) (WS_DGT % 11);
            WS_RET = (short) ((WS_DGT - WS_RMDR) / 11);
        }
        if (WS_RMDR != 0) {
            IBS.CPY2CLS(SCCGWA, SCCCTLM_MSG.SC_ERR_ACNO, BPCKDGAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B31_CHK_DIGIT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_ACNO.WS_ACNO_NO_DIG);
        for (I = 1; I <= 50; I += 1) {
            WS_ACNO.WS_ACNO_NO_DIG.WS_ACNO_NO_BIT[I-1] = 0;
        }
        JIBS_tmp_str[0] = BPCKDGAC.NO;
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ACNO);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ACNO.WS_ACNO_NO_DIG);
        if (!IBS.isNumeric(JIBS_tmp_str[0])) {
            IBS.CPY2CLS(SCCGWA, SCCCTLM_MSG.SC_ERR_INVALID_INPUT, BPCKDGAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CPY2CLS(SCCGWA, BPCKDGAC.NO, WS_AC_BYTE_R);
        WS_DGT = (short) (WS_AC_BYTE_R.B[01-1] + WS_AC_BYTE_R.B[03-1] + WS_AC_BYTE_R.B[05-1] + WS_AC_BYTE_R.B[07-1] + WS_AC_BYTE_R.B[09-1] + WS_AC_BYTE_R.B[11-1] + WS_AC_BYTE_R.B[13-1] + WS_AC_BYTE_R.B[15-1] + WS_AC_BYTE_R.B[17-1] + WS_AC_BYTE_R.B[19-1]);
        WS_RMDR1 = (short) (WS_DGT % 10);
        WS_RET = (short) ((WS_DGT - WS_RMDR1) / 10);
        if (WS_RMDR1 != 0) {
            IBS.CPY2CLS(SCCGWA, SCCCTLM_MSG.SC_ERR_ACNO, BPCKDGAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_DGT = 0;
        WS_NUM = (short) (WS_AC_BYTE_R.B[02-1] * 2);
        WS_DGT = (short) (WS_SEQ.WS_SEQ1 + WS_SEQ.WS_SEQ2 + WS_DGT);
        WS_NUM = (short) (WS_AC_BYTE_R.B[04-1] * 2);
        WS_DGT = (short) (WS_SEQ.WS_SEQ1 + WS_SEQ.WS_SEQ2 + WS_DGT);
        WS_NUM = (short) (WS_AC_BYTE_R.B[06-1] * 2);
        WS_DGT = (short) (WS_SEQ.WS_SEQ1 + WS_SEQ.WS_SEQ2 + WS_DGT);
        WS_NUM = (short) (WS_AC_BYTE_R.B[08-1] * 2);
        WS_DGT = (short) (WS_SEQ.WS_SEQ1 + WS_SEQ.WS_SEQ2 + WS_DGT);
        WS_NUM = (short) (WS_AC_BYTE_R.B[10-1] * 2);
        WS_DGT = (short) (WS_SEQ.WS_SEQ1 + WS_SEQ.WS_SEQ2 + WS_DGT);
        WS_NUM = (short) (WS_AC_BYTE_R.B[12-1] * 2);
        WS_DGT = (short) (WS_SEQ.WS_SEQ1 + WS_SEQ.WS_SEQ2 + WS_DGT);
        WS_NUM = (short) (WS_AC_BYTE_R.B[14-1] * 2);
        WS_DGT = (short) (WS_SEQ.WS_SEQ1 + WS_SEQ.WS_SEQ2 + WS_DGT);
        WS_NUM = (short) (WS_AC_BYTE_R.B[16-1] * 2);
        WS_DGT = (short) (WS_SEQ.WS_SEQ1 + WS_SEQ.WS_SEQ2 + WS_DGT);
        WS_DGT = (short) (WS_AC_BYTE_R.B[19-1] + WS_DGT);
        WS_RMDR1 = (short) (WS_DGT % 10);
        WS_RET = (short) ((WS_DGT - WS_RMDR1) / 10);
        if (WS_RMDR1 != 0) {
            IBS.CPY2CLS(SCCGWA, SCCCTLM_MSG.SC_ERR_ACNO, BPCKDGAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
