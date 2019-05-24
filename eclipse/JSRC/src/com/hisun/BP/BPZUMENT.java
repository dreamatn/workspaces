package com.hisun.BP;

import com.hisun.SC.*;
import java.text.DecimalFormat;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZUMENT {
    String JIBS_tmp_str[] = new String[10];
    DecimalFormat df;
    int JIBS_tmp_int;
    String JIBS_NumStr;
    boolean pgmRtn = false;
    String BP_SEQ_TYPE = "SEQ";
    String BP_SEQ_CODE = "EVENT";
    String BP_DIARY_BATNO = "DYBATNO";
    String BP_HIS_REMARKS = "EVENT  DIARY";
    int BP_SPEC_DATE = 19570101;
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_ERR_MSG = " ";
    int WS_DEL_CNT = 0;
    int WS_TMP_CNT = 0;
    String WS_RATE_STR = " ";
    char WS_END_BRW = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRDIARY BPRDIARY = new BPRDIARY();
    BPRDIARY BPRBDIAR = new BPRDIARY();
    BPRDYCNT BPRDYCNT = new BPRDYCNT();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCRMENT BPCRMENT = new BPCRMENT();
    BPCRDYCN BPCRDYCN = new BPCRDYCN();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCGWA SCCGWA;
    BPCUMENT BPCUMENT;
    public void MP(SCCGWA SCCGWA, BPCUMENT BPCUMENT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCUMENT = BPCUMENT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZUMENT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCUMENT.PROC_DATA.UPDATA_FLG);
        if (BPCUMENT.PROC_DATA.UPDATA_FLG == 'F' 
            || BPCUMENT.PROC_DATA.UPDATA_FLG == 'D') {
            IBS.init(SCCGWA, BPCSGSEQ);
            BPCSGSEQ.TYPE = BP_SEQ_TYPE;
            BPCSGSEQ.CODE = BP_DIARY_BATNO;
            BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCSGSEQ.RUN_MODE = 'O';
            S000_CALL_BPZSGSEQ();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCSGSEQ.SEQ);
            CEP.TRC(SCCGWA, BPCUMENT.DIARY_DATA.DIARY_BATNO);
            BPCUMENT.DIARY_DATA.DIARY_BATNO = BPCSGSEQ.SEQ;
            CEP.TRC(SCCGWA, BPCUMENT.DIARY_DATA.DIARY_BATNO);
        }
        CEP.TRC(SCCGWA, BPCUMENT.PROC_DATA.FUNC_CODE);
        if (BPCUMENT.PROC_DATA.FUNC_CODE.equalsIgnoreCase("A) BPCUMENT.PROC_DATA.FUNC_CODE.equalsIgnoreCase(")) {
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCUMENT.PROC_DATA.FUNC_CODE.equalsIgnoreCase("M2")
            || BPCUMENT.PROC_DATA.FUNC_CODE.equalsIgnoreCase("M1")) {
            B040_MODIFY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCUMENT.PROC_DATA.FUNC_CODE.equalsIgnoreCase("M3")) {
            B041_MODIFY_ONLY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCUMENT.PROC_DATA.FUNC_CODE.equalsIgnoreCase("D1")) {
            B050_DELETE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCUMENT.PROC_DATA.FUNC_CODE.equalsIgnoreCase("D2")) {
            B060_DELETE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCUMENT.PROC_DATA.FUNC_CODE.equalsIgnoreCase("D3")) {
            B300_DEL_SINGLE_PROC();
            if (pgmRtn) return;
        } else if (BPCUMENT.PROC_DATA.FUNC_CODE.equalsIgnoreCase("D4")) {
            B400_DEL_ONLY_PROC();
            if (pgmRtn) return;
        } else if (BPCUMENT.PROC_DATA.FUNC_CODE.equalsIgnoreCase("Q) BPCUMENT.PROC_DATA.FUNC_CODE.equalsIgnoreCase(")) {
            B070_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCUMENT.PROC_DATA.FUNC_CODE.equalsIgnoreCase("B) BPCUMENT.PROC_DATA.FUNC_CODE.equalsIgnoreCase(")) {
            B080_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCUMENT.PROC_DATA.FUNC_CODE.equalsIgnoreCase("R) BPCUMENT.PROC_DATA.FUNC_CODE.equalsIgnoreCase(")) {
            B090_READUPD_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FUNC_ERROR, BPCUMENT.PROC_DATA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCUMENT.DIARY_DATA.CONTRACT_NO.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CTRT_NO_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCUMENT.PROC_DATA.FUNC_CODE.equalsIgnoreCase("A) BPCUMENT.PROC_DATA.FUNC_CODE.equalsIgnoreCase(")) {
            if (BPCUMENT.DIARY_DATA.ACCT_CENTRE == 0 
                || BPCUMENT.DIARY_DATA.DIARY_STATUS == ' ' 
                || BPCUMENT.DIARY_DATA.CONTRACT_TYPE.trim().length() == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_DATE_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCUMENT.PROC_DATA.FUNC_CODE.equalsIgnoreCase("A) BPCUMENT.PROC_DATA.FUNC_CODE.equalsIgnoreCase(") 
            || BPCUMENT.PROC_DATA.FUNC_CODE.equalsIgnoreCase("M1") 
            || BPCUMENT.PROC_DATA.FUNC_CODE.equalsIgnoreCase("R) BPCUMENT.PROC_DATA.FUNC_CODE.equalsIgnoreCase(") 
            || BPCUMENT.PROC_DATA.FUNC_CODE.equalsIgnoreCase("Q) BPCUMENT.PROC_DATA.FUNC_CODE.equalsIgnoreCase(")) {
            if (BPCUMENT.DIARY_DATA.DIARY_TYPE.trim().length() == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_D_TYPE_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCUMENT.DIARY_DATA.DIARY_DATE == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ENT_DATE_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
    }
    public void B020_CREATE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRDIARY);
        IBS.init(SCCGWA, BPCRMENT);
        IBS.init(SCCGWA, BPCSGSEQ);
        BPCSGSEQ.TYPE = BP_SEQ_TYPE;
        BPCSGSEQ.CODE = BP_SEQ_CODE;
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSGSEQ.RUN_MODE = 'O';
        S000_CALL_BPZSGSEQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSGSEQ.SEQ);
        CEP.TRC(SCCGWA, BPCUMENT.DIARY_DATA.KEY.SEQ);
        BPCUMENT.DIARY_DATA.KEY.SEQ = BPCSGSEQ.SEQ;
        CEP.TRC(SCCGWA, BPCUMENT.DIARY_DATA.KEY.SEQ);
        BPCRMENT.INFO.FUNC = 'A';
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = BP_SPEC_DATE;
        SCCCLDT.DATE2 = BPCUMENT.DIARY_DATA.DIARY_DATE;
        CEP.TRC(SCCGWA, SCCCLDT.DATE1);
        CEP.TRC(SCCGWA, SCCCLDT.DATE2);
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC == 0) {
            BPCUMENT.DIARY_DATA.REL_DAY = SCCCLDT.DAYS;
        }
        if (BPCUMENT.DIARY_DATA.REL_DAY < 0) {
            BPCUMENT.DIARY_DATA.REL_DAY = 0 - BPCUMENT.DIARY_DATA.REL_DAY;
        }
        CEP.TRC(SCCGWA, BPCUMENT.DIARY_DATA.REL_DAY);
        BPCUMENT.DIARY_DATA.ACTL_DATE = SCCGWA.COMM_AREA.TR_DATE;
        BPCUMENT.DIARY_DATA.ACTL_TIME = SCCGWA.COMM_AREA.TR_TIME;
        BPCUMENT.DIARY_DATA.TRN_DATE = SCCGWA.COMM_AREA.TR_DATE;
        BPCUMENT.DIARY_DATA.AS_OF_DATE = SCCGWA.COMM_AREA.TR_DATE;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUMENT.DIARY_DATA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRDIARY);
        df = new DecimalFormat("0.00000000");
        WS_RATE_STR = df.format(0);
        df = new DecimalFormat("0.00000000");
        WS_RATE_STR = df.format(BPCUMENT.DIARY_DATA.RATEX1);
        BPRDIARY.RATE_1 = " ";
        BPRDIARY.RATE_1 = "" + WS_RATE_STR;
        JIBS_tmp_int = BPRDIARY.RATE_1.length();
        for (int i=0;i<14-JIBS_tmp_int;i++) BPRDIARY.RATE_1 = "0" + BPRDIARY.RATE_1;
        df = new DecimalFormat("0.00000000");
        WS_RATE_STR = df.format(0);
        df = new DecimalFormat("0.00000000");
        WS_RATE_STR = df.format(BPCUMENT.DIARY_DATA.RATEX2);
        BPRDIARY.RATE_2 = " ";
        BPRDIARY.RATE_2 = "" + WS_RATE_STR;
        JIBS_tmp_int = BPRDIARY.RATE_2.length();
        for (int i=0;i<14-JIBS_tmp_int;i++) BPRDIARY.RATE_2 = "0" + BPRDIARY.RATE_2;
        df = new DecimalFormat("0.00000000");
        WS_RATE_STR = df.format(0);
        df = new DecimalFormat("0.00000000");
        WS_RATE_STR = df.format(BPCUMENT.DIARY_DATA.RATEX3);
        BPRDIARY.RATE_3 = " ";
        BPRDIARY.RATE_3 = "" + WS_RATE_STR;
        JIBS_tmp_int = BPRDIARY.RATE_3.length();
        for (int i=0;i<14-JIBS_tmp_int;i++) BPRDIARY.RATE_3 = "0" + BPRDIARY.RATE_3;
        df = new DecimalFormat("0.00000000");
        WS_RATE_STR = df.format(0);
        df = new DecimalFormat("0.00000000");
        WS_RATE_STR = df.format(BPCUMENT.DIARY_DATA.RATEX4);
        BPRDIARY.RATE_4 = " ";
        BPRDIARY.RATE_4 = "" + WS_RATE_STR;
        JIBS_tmp_int = BPRDIARY.RATE_4.length();
        for (int i=0;i<14-JIBS_tmp_int;i++) BPRDIARY.RATE_4 = "0" + BPRDIARY.RATE_4;
        df = new DecimalFormat("0.00000000");
        WS_RATE_STR = df.format(0);
        df = new DecimalFormat("0.00000000");
        WS_RATE_STR = df.format(BPCUMENT.DIARY_DATA.RATEX5);
        BPRDIARY.RATE_5 = " ";
        BPRDIARY.RATE_5 = "" + WS_RATE_STR;
        JIBS_tmp_int = BPRDIARY.RATE_5.length();
        for (int i=0;i<14-JIBS_tmp_int;i++) BPRDIARY.RATE_5 = "0" + BPRDIARY.RATE_5;
        df = new DecimalFormat("0.00000000");
        WS_RATE_STR = df.format(0);
        df = new DecimalFormat("0.00000000");
        WS_RATE_STR = df.format(BPCUMENT.DIARY_DATA.RATEX6);
        BPRDIARY.RATE_6 = " ";
        BPRDIARY.RATE_6 = "" + WS_RATE_STR;
        JIBS_tmp_int = BPRDIARY.RATE_6.length();
        for (int i=0;i<14-JIBS_tmp_int;i++) BPRDIARY.RATE_6 = "0" + BPRDIARY.RATE_6;
        df = new DecimalFormat("0.00000000");
        WS_RATE_STR = df.format(0);
        df = new DecimalFormat("0.00000000");
        WS_RATE_STR = df.format(BPCUMENT.DIARY_DATA.RATEX7);
        BPRDIARY.RATE_7 = " ";
        BPRDIARY.RATE_7 = "" + WS_RATE_STR;
        JIBS_tmp_int = BPRDIARY.RATE_7.length();
        for (int i=0;i<14-JIBS_tmp_int;i++) BPRDIARY.RATE_7 = "0" + BPRDIARY.RATE_7;
        df = new DecimalFormat("0.00000000");
        WS_RATE_STR = df.format(0);
        df = new DecimalFormat("0.00000000");
        WS_RATE_STR = df.format(BPCUMENT.DIARY_DATA.RATEX8);
        BPRDIARY.RATE_8 = " ";
        BPRDIARY.RATE_8 = "" + WS_RATE_STR;
        JIBS_tmp_int = BPRDIARY.RATE_8.length();
        for (int i=0;i<14-JIBS_tmp_int;i++) BPRDIARY.RATE_8 = "0" + BPRDIARY.RATE_8;
        df = new DecimalFormat("0.00000000");
        WS_RATE_STR = df.format(0);
        df = new DecimalFormat("0.00000000");
        WS_RATE_STR = df.format(BPCUMENT.DIARY_DATA.RATEX9);
        BPRDIARY.RATE_9 = ' ';
        JIBS_NumStr = "" + WS_RATE_STR;
        BPRDIARY.RATE_9 = JIBS_NumStr.charAt(0);
        df = new DecimalFormat("0.00000000");
        WS_RATE_STR = df.format(0);
        df = new DecimalFormat("0.00000000");
        WS_RATE_STR = df.format(BPCUMENT.DIARY_DATA.RATEX10);
        BPRDIARY.RATE_10 = " ";
        BPRDIARY.RATE_10 = "" + WS_RATE_STR;
        JIBS_tmp_int = BPRDIARY.RATE_10.length();
        for (int i=0;i<14-JIBS_tmp_int;i++) BPRDIARY.RATE_10 = "0" + BPRDIARY.RATE_10;
        BPCRMENT.INFO.POINTER = BPRDIARY;
        BPCRMENT.LEN = 1468;
        S000_CALL_BPZRMENT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCUMENT.PROC_DATA.DIARY_CNT);
        if (BPCUMENT.DIARY_DATA.DIARY_BATNO == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BATNO_CANNOT_BE_ZERO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            B100_RD_UPD_BPTDYCNT_PROC();
            if (pgmRtn) return;
            WS_TMP_CNT = 1;
            if (BPCRDYCN.RETURN_INFO == 'F') {
                B110_UPDATE_BPTDYCNT_PROC();
                if (pgmRtn) return;
            } else {
                B120_WRITE_BPTDYCNT_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B030_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRDIARY);
        IBS.init(SCCGWA, BPRBDIAR);
        IBS.init(SCCGWA, BPCRMENT);
        BPCRMENT.INFO.FUNC = 'B';
        BPCRMENT.INFO.BROWSE_OPTION = '1';
        BPCRMENT.INFO.OPT = 'S';
        BPRDIARY.CONTRACTNO = BPCUMENT.DIARY_DATA.CONTRACT_NO;
        BPRDIARY.D_TYPE = BPCUMENT.DIARY_DATA.DIARY_TYPE;
        BPCRMENT.INFO.START_DT = BPCUMENT.DIARY_DATA.DIARY_DATE;
        BPCRMENT.INFO.END_DT = BPCUMENT.DIARY_DATA.DIARY_DATE;
        BPCRMENT.LEN = 1468;
        BPCRMENT.INFO.POINTER = BPRDIARY;
        S000_CALL_BPZRMENT();
        if (pgmRtn) return;
        if (BPCRMENT.RETURN_INFO != 'F') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_ENT_RECORD_NOT_EXIST, BPCUMENT.PROC_DATA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_END_BRW = 'N';
        while (BPCRMENT.RETURN_INFO != 'N' 
            && WS_END_BRW != 'Y') {
            BPCRMENT.INFO.SEQ = BPRDIARY.KEY.SEQ;
            BPCRMENT.INFO.FUNC = 'B';
            BPCRMENT.INFO.OPT = 'N';
            S000_CALL_BPZRMENT();
            if (pgmRtn) return;
            if (BPCRMENT.RETURN_INFO == 'F' 
                && BPRDIARY.D_STATUS != 'D') {
                IBS.CLONE(SCCGWA, BPRDIARY, BPRBDIAR);
                BPCRMENT.INFO.FUNC = 'U';
                B200_TRN_DATA_TO_DIARY_PROC();
                if (pgmRtn) return;
                BPCRMENT.LEN = 1468;
                BPCRMENT.INFO.POINTER = BPRDIARY;
                S000_CALL_BPZRMENT();
                if (pgmRtn) return;
                WS_END_BRW = 'Y';
            }
        }
        BPCRMENT.INFO.FUNC = 'B';
        BPCRMENT.INFO.OPT = 'E';
        S000_CALL_BPZRMENT();
        if (pgmRtn) return;
        if (BPCUMENT.DIARY_DATA.DIARY_BATNO == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BATNO_CANNOT_BE_ZERO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            B100_RD_UPD_BPTDYCNT_PROC();
            if (pgmRtn) return;
            WS_TMP_CNT = 1;
            if (BPCRDYCN.RETURN_INFO == 'F') {
                B110_UPDATE_BPTDYCNT_PROC();
                if (pgmRtn) return;
            } else {
                B120_WRITE_BPTDYCNT_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B040_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRDIARY);
        IBS.init(SCCGWA, BPRBDIAR);
        IBS.init(SCCGWA, BPCRMENT);
        BPCRMENT.INFO.FUNC = 'R';
        CEP.TRC(SCCGWA, BPCUMENT.DIARY_DATA.KEY.SEQ);
        BPRDIARY.KEY.SEQ = BPCUMENT.DIARY_DATA.KEY.SEQ;
        BPCRMENT.LEN = 1468;
        BPCRMENT.INFO.POINTER = BPRDIARY;
        S000_CALL_BPZRMENT();
        if (pgmRtn) return;
        if (BPCRMENT.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ENT_RECORD_NOT_EXIST;
            S000_ERR_MSG_PROC_END();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRDIARY, BPRBDIAR);
        BPCRMENT.INFO.FUNC = 'U';
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = BP_SPEC_DATE;
        SCCCLDT.DATE2 = BPCUMENT.DIARY_DATA.DIARY_DATE;
        CEP.TRC(SCCGWA, SCCCLDT.DATE1);
        CEP.TRC(SCCGWA, SCCCLDT.DATE2);
        SCSSCLDT SCSSCLDT2 = new SCSSCLDT();
        SCSSCLDT2.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC == 0) {
            BPCUMENT.DIARY_DATA.REL_DAY = SCCCLDT.DAYS;
        }
        if (BPCUMENT.DIARY_DATA.REL_DAY < 0) {
            BPCUMENT.DIARY_DATA.REL_DAY = 0 - BPCUMENT.DIARY_DATA.REL_DAY;
        }
        CEP.TRC(SCCGWA, BPCUMENT.DIARY_DATA.REL_DAY);
        B200_TRN_DATA_TO_DIARY_PROC();
        if (pgmRtn) return;
        BPCRMENT.LEN = 1468;
        BPCRMENT.INFO.POINTER = BPRDIARY;
        S000_CALL_BPZRMENT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        CEP.TRC(SCCGWA, BPCUMENT.DIARY_DATA.CONTRACT_NO);
        CEP.TRC(SCCGWA, BPCUMENT.DIARY_DATA.DIARY_BATNO);
        CEP.TRC(SCCGWA, BPCUMENT.PROC_DATA.DIARY_CNT);
        if (BPCUMENT.DIARY_DATA.DIARY_BATNO == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BATNO_CANNOT_BE_ZERO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            B100_RD_UPD_BPTDYCNT_PROC();
            if (pgmRtn) return;
            WS_TMP_CNT = 1;
            if (BPCRDYCN.RETURN_INFO == 'F') {
                B110_UPDATE_BPTDYCNT_PROC();
                if (pgmRtn) return;
            } else {
                B120_WRITE_BPTDYCNT_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B041_MODIFY_ONLY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRDIARY);
        IBS.init(SCCGWA, BPCRMENT);
        BPCRMENT.INFO.FUNC = 'U';
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = BP_SPEC_DATE;
        SCCCLDT.DATE2 = BPCUMENT.DIARY_DATA.DIARY_DATE;
        CEP.TRC(SCCGWA, SCCCLDT.DATE1);
        CEP.TRC(SCCGWA, SCCCLDT.DATE2);
        SCSSCLDT SCSSCLDT3 = new SCSSCLDT();
        SCSSCLDT3.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC == 0) {
            BPCUMENT.DIARY_DATA.REL_DAY = SCCCLDT.DAYS;
        }
        if (BPCUMENT.DIARY_DATA.REL_DAY < 0) {
            BPCUMENT.DIARY_DATA.REL_DAY = 0 - BPCUMENT.DIARY_DATA.REL_DAY;
        }
        CEP.TRC(SCCGWA, BPCUMENT.DIARY_DATA.REL_DAY);
        B200_TRN_DATA_TO_DIARY_PROC();
        if (pgmRtn) return;
        BPCRMENT.LEN = 1468;
        BPCRMENT.INFO.POINTER = BPRDIARY;
        S000_CALL_BPZRMENT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        CEP.TRC(SCCGWA, BPCUMENT.DIARY_DATA.CONTRACT_NO);
        CEP.TRC(SCCGWA, BPCUMENT.DIARY_DATA.DIARY_BATNO);
        CEP.TRC(SCCGWA, BPCUMENT.PROC_DATA.DIARY_CNT);
        if (BPCUMENT.DIARY_DATA.DIARY_BATNO == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BATNO_CANNOT_BE_ZERO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            B100_RD_UPD_BPTDYCNT_PROC();
            if (pgmRtn) return;
            WS_TMP_CNT = 1;
            if (BPCRDYCN.RETURN_INFO == 'F') {
                B110_UPDATE_BPTDYCNT_PROC();
                if (pgmRtn) return;
            } else {
                B120_WRITE_BPTDYCNT_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B050_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRDIARY);
        IBS.init(SCCGWA, BPCRMENT);
        WS_DEL_CNT = 0;
        BPCRMENT.INFO.FUNC = 'B';
        BPCRMENT.INFO.OPT = 'S';
        BPCRMENT.INFO.BROWSE_OPTION = '1';
        BPCRMENT.INFO.UPD_FLAG = 'Y';
        CEP.TRC(SCCGWA, BPCUMENT.DIARY_DATA.CONTRACT_NO);
        CEP.TRC(SCCGWA, BPCUMENT.DIARY_DATA.DIARY_TYPE);
        CEP.TRC(SCCGWA, BPCUMENT.PROC_DATA.START_DATE);
        CEP.TRC(SCCGWA, BPCUMENT.PROC_DATA.END_DATE);
        CEP.TRC(SCCGWA, BPCUMENT.PROC_DATA.DT_EQ_FLG);
        BPRDIARY.CONTRACTNO = BPCUMENT.DIARY_DATA.CONTRACT_NO;
        BPRDIARY.D_TYPE = BPCUMENT.DIARY_DATA.DIARY_TYPE;
        BPCRMENT.INFO.START_DT = BPCUMENT.PROC_DATA.START_DATE;
        BPCRMENT.INFO.END_DT = BPCUMENT.PROC_DATA.END_DATE;
        BPCRMENT.INFO.DT_EQ_FLG = BPCUMENT.PROC_DATA.DT_EQ_FLG;
        BPCRMENT.LEN = 1468;
        BPCRMENT.INFO.POINTER = BPRDIARY;
        S000_CALL_BPZRMENT();
        if (pgmRtn) return;
        if (BPCRMENT.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ENT_RECORD_NOT_EXIST;
            S000_ERR_MSG_PROC_END();
            if (pgmRtn) return;
        }
        while (BPCRMENT.RETURN_INFO != 'N') {
            BPCRMENT.INFO.SEQ = BPRDIARY.KEY.SEQ;
            BPCRMENT.INFO.FUNC = 'B';
            BPCRMENT.INFO.OPT = 'N';
            S000_CALL_BPZRMENT();
            if (pgmRtn) return;
            if (BPCRMENT.RETURN_INFO == 'F' 
                && BPRDIARY.D_STATUS != 'D') {
                BPCRMENT.INFO.FUNC = 'U';
                BPCUMENT.DIARY_DATA.DIARY_STATUS = 'D';
                BPRDIARY.D_STATUS = BPCUMENT.DIARY_DATA.DIARY_STATUS;
                BPRDIARY.DIARY_BATNO = BPCUMENT.DIARY_DATA.DIARY_BATNO;
                WS_DEL_CNT = WS_DEL_CNT + 1;
                BPCRMENT.LEN = 1468;
                BPCRMENT.INFO.POINTER = BPRDIARY;
                S000_CALL_BPZRMENT();
                if (pgmRtn) return;
            }
        }
        BPCUMENT.PROC_DATA.DELETE_CNT = WS_DEL_CNT;
        BPCRMENT.INFO.FUNC = 'B';
        BPCRMENT.INFO.OPT = 'E';
        S000_CALL_BPZRMENT();
        if (pgmRtn) return;
        if (WS_DEL_CNT > 0) {
            BPCUMENT.PROC_DATA.RETURN_INFO = 'F';
        }
        if (BPCUMENT.PROC_DATA.UPDATA_FLG == 'D') {
            BPCUMENT.PROC_DATA.DIARY_CNT = BPCUMENT.PROC_DATA.DELETE_CNT;
            WS_TMP_CNT = BPCUMENT.PROC_DATA.DIARY_CNT;
            B120_WRITE_BPTDYCNT_PROC();
            if (pgmRtn) return;
        } else {
            if (BPCUMENT.DIARY_DATA.DIARY_BATNO == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BATNO_CANNOT_BE_ZERO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                B100_RD_UPD_BPTDYCNT_PROC();
                if (pgmRtn) return;
                WS_TMP_CNT = BPCUMENT.PROC_DATA.DELETE_CNT;
                if (BPCRDYCN.RETURN_INFO == 'F') {
                    B110_UPDATE_BPTDYCNT_PROC();
                    if (pgmRtn) return;
                } else {
                    B120_WRITE_BPTDYCNT_PROC();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B060_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRDIARY);
        IBS.init(SCCGWA, BPCRMENT);
        WS_DEL_CNT = 0;
        BPCRMENT.INFO.FUNC = 'B';
        BPCRMENT.INFO.OPT = 'S';
        BPCRMENT.INFO.BROWSE_OPTION = '1';
        BPCRMENT.INFO.UPD_FLAG = 'Y';
        BPRDIARY.CONTRACTNO = BPCUMENT.DIARY_DATA.CONTRACT_NO;
        BPRDIARY.D_TYPE = BPCUMENT.DIARY_DATA.DIARY_TYPE;
        BPCRMENT.INFO.START_DT = BPCUMENT.PROC_DATA.START_DATE;
        BPCRMENT.INFO.END_DT = BPCUMENT.PROC_DATA.END_DATE;
        BPCRMENT.INFO.DT_EQ_FLG = BPCUMENT.PROC_DATA.DT_EQ_FLG;
        BPCRMENT.LEN = 1468;
        BPCRMENT.INFO.POINTER = BPRDIARY;
        S000_CALL_BPZRMENT();
        if (pgmRtn) return;
        if (BPCRMENT.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ENT_RECORD_NOT_EXIST;
            S000_ERR_MSG_PROC_END();
            if (pgmRtn) return;
        }
        while (BPCRMENT.RETURN_INFO != 'N') {
            BPCRMENT.INFO.SEQ = BPRDIARY.KEY.SEQ;
            BPCRMENT.INFO.FUNC = 'B';
            BPCRMENT.INFO.OPT = 'N';
            S000_CALL_BPZRMENT();
            if (pgmRtn) return;
            if (BPCRMENT.RETURN_INFO == 'F') {
                BPCRMENT.INFO.FUNC = 'D';
                WS_DEL_CNT = WS_DEL_CNT + 1;
                BPCRMENT.LEN = 1468;
                BPCRMENT.INFO.POINTER = BPRDIARY;
                S000_CALL_BPZRMENT();
                if (pgmRtn) return;
            }
        }
        BPCUMENT.PROC_DATA.DELETE_CNT = WS_DEL_CNT;
        BPCRMENT.INFO.FUNC = 'B';
        BPCRMENT.INFO.OPT = 'E';
        S000_CALL_BPZRMENT();
        if (pgmRtn) return;
        if (WS_DEL_CNT > 0) {
            BPCUMENT.PROC_DATA.RETURN_INFO = 'F';
        }
        if (BPCUMENT.PROC_DATA.UPDATA_FLG == 'D') {
            BPCUMENT.PROC_DATA.DIARY_CNT = BPCUMENT.PROC_DATA.DELETE_CNT;
            WS_TMP_CNT = BPCUMENT.PROC_DATA.DIARY_CNT;
            B120_WRITE_BPTDYCNT_PROC();
            if (pgmRtn) return;
        }
    }
    public void B070_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRDIARY);
        IBS.init(SCCGWA, BPCRMENT);
        BPCRMENT.INFO.FUNC = 'B';
        BPCRMENT.INFO.BROWSE_OPTION = '1';
        BPCRMENT.INFO.OPT = 'S';
        BPRDIARY.CONTRACTNO = BPCUMENT.DIARY_DATA.CONTRACT_NO;
        BPRDIARY.D_TYPE = BPCUMENT.DIARY_DATA.DIARY_TYPE;
        BPCRMENT.INFO.START_DT = BPCUMENT.DIARY_DATA.DIARY_DATE;
        BPCRMENT.INFO.END_DT = BPCUMENT.DIARY_DATA.DIARY_DATE;
        BPCRMENT.INFO.DT_EQ_FLG = BPCUMENT.PROC_DATA.DT_EQ_FLG;
        BPCRMENT.LEN = 1468;
        BPCRMENT.INFO.POINTER = BPRDIARY;
        S000_CALL_BPZRMENT();
        if (pgmRtn) return;
        if (BPCRMENT.RETURN_INFO != 'F') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_ENT_RECORD_NOT_EXIST, BPCUMENT.PROC_DATA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_END_BRW = 'N';
        while (BPCRMENT.RETURN_INFO != 'N' 
            && WS_END_BRW != 'Y') {
            BPCRMENT.INFO.FUNC = 'B';
            BPCRMENT.INFO.OPT = 'N';
            S000_CALL_BPZRMENT();
            if (pgmRtn) return;
            if (BPCRMENT.RETURN_INFO == 'F') {
                if (BPCUMENT.PROC_DATA.INCLUDE_D_STS == 'Y') {
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRDIARY);
                    IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUMENT.DIARY_DATA);
                    WS_END_BRW = 'Y';
                } else {
                    if (BPRDIARY.D_STATUS != 'D') {
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRDIARY);
                        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUMENT.DIARY_DATA);
                        WS_END_BRW = 'Y';
                    }
                }
            }
        }
        BPCRMENT.INFO.FUNC = 'B';
        BPCRMENT.INFO.OPT = 'E';
        S000_CALL_BPZRMENT();
        if (pgmRtn) return;
    }
    public void B080_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        if (BPCUMENT.PROC_DATA.BRW_FIRST == 'Y') {
            B080_04_START_BRW_FIRST_PROC();
            if (pgmRtn) return;
        } else {
            if (BPCUMENT.PROC_DATA.BRW_OPT == 'S') {
                B080_01_START_BRW_PROC();
                if (pgmRtn) return;
            } else if (BPCUMENT.PROC_DATA.BRW_OPT == 'N') {
                B080_02_READ_NEXT_PROC();
                if (pgmRtn) return;
            } else if (BPCUMENT.PROC_DATA.BRW_OPT == 'E') {
                B080_03_END_BRW_PROC();
                if (pgmRtn) return;
            } else {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_INPUT_ERROR, BPCUMENT.PROC_DATA.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B080_01_START_BRW_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRDIARY);
        IBS.init(SCCGWA, BPCRMENT);
        BPCRMENT.INFO.FUNC = 'B';
        BPCRMENT.INFO.BROWSE_OPTION = '1';
        BPCRMENT.INFO.OPT = 'S';
        if (BPCUMENT.PROC_DATA.BRW_UPD_FLG == 'Y') {
            BPCRMENT.INFO.UPD_FLAG = 'Y';
        }
        if (BPCUMENT.PROC_DATA.BRW_ORDERBY == 'Y') {
            BPCRMENT.INFO.BRW_ORDERBY = 'Y';
        }
        BPRDIARY.CONTRACTNO = BPCUMENT.DIARY_DATA.CONTRACT_NO;
        BPRDIARY.D_TYPE = BPCUMENT.DIARY_DATA.DIARY_TYPE;
        BPCRMENT.INFO.START_DT = BPCUMENT.PROC_DATA.START_DATE;
        BPCRMENT.INFO.END_DT = BPCUMENT.PROC_DATA.END_DATE;
        BPCRMENT.INFO.DT_EQ_FLG = BPCUMENT.PROC_DATA.DT_EQ_FLG;
        BPCRMENT.LEN = 1468;
        BPCRMENT.INFO.POINTER = BPRDIARY;
        S000_CALL_BPZRMENT();
        if (pgmRtn) return;
    }
    public void B080_02_READ_NEXT_PROC() throws IOException,SQLException,Exception {
        BPCRMENT.INFO.SEQ = BPCUMENT.DIARY_DATA.KEY.SEQ;
        if (BPCUMENT.PROC_DATA.BRW_ORDERBY == 'Y') {
            BPCRMENT.INFO.BRW_ORDERBY = 'Y';
        }
        BPCRMENT.INFO.FUNC = 'B';
        BPCRMENT.INFO.OPT = 'N';
        S000_CALL_BPZRMENT();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRDIARY);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUMENT.DIARY_DATA);
    }
    public void B080_03_END_BRW_PROC() throws IOException,SQLException,Exception {
        BPCRMENT.INFO.FUNC = 'B';
        BPCRMENT.INFO.OPT = 'E';
        S000_CALL_BPZRMENT();
        if (pgmRtn) return;
    }
    public void B080_04_START_BRW_FIRST_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRDIARY);
        IBS.init(SCCGWA, BPCRMENT);
        BPCRMENT.INFO.FUNC = 'B';
        BPCRMENT.INFO.BROWSE_OPTION = '5';
        BPCRMENT.INFO.OPT = 'S';
        BPRDIARY.CONTRACTNO = BPCUMENT.DIARY_DATA.CONTRACT_NO;
        BPRDIARY.D_TYPE = BPCUMENT.DIARY_DATA.DIARY_TYPE;
        BPCRMENT.INFO.END_DT = BPCUMENT.DIARY_DATA.DIARY_DATE;
        BPCRMENT.LEN = 1468;
        BPCRMENT.INFO.POINTER = BPRDIARY;
        S000_CALL_BPZRMENT();
        if (pgmRtn) return;
        if (BPCRMENT.RETURN_INFO == 'F') {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRDIARY);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUMENT.DIARY_DATA);
        }
    }
    public void B090_READUPD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRDIARY);
        IBS.init(SCCGWA, BPCRMENT);
        BPCRMENT.INFO.UPD_FLAG = 'Y';
        BPCRMENT.INFO.FUNC = 'B';
        BPCRMENT.INFO.BROWSE_OPTION = '1';
        BPCRMENT.INFO.OPT = 'S';
        CEP.TRC(SCCGWA, BPCUMENT.DIARY_DATA.CONTRACT_NO);
        CEP.TRC(SCCGWA, BPCUMENT.DIARY_DATA.DIARY_TYPE);
        CEP.TRC(SCCGWA, BPCUMENT.DIARY_DATA.DIARY_DATE);
        BPRDIARY.CONTRACTNO = BPCUMENT.DIARY_DATA.CONTRACT_NO;
        BPRDIARY.D_TYPE = BPCUMENT.DIARY_DATA.DIARY_TYPE;
        BPCRMENT.INFO.START_DT = BPCUMENT.DIARY_DATA.DIARY_DATE;
        BPCRMENT.INFO.END_DT = BPCUMENT.DIARY_DATA.DIARY_DATE;
        BPCRMENT.LEN = 1468;
        BPCRMENT.INFO.POINTER = BPRDIARY;
        S000_CALL_BPZRMENT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BROWSE START1");
        CEP.TRC(SCCGWA, BPRDIARY.CONTRACTNO);
        CEP.TRC(SCCGWA, BPRDIARY.D_TYPE);
        CEP.TRC(SCCGWA, BPCRMENT.INFO.START_DT);
        CEP.TRC(SCCGWA, BPCRMENT.INFO.END_DT);
        CEP.TRC(SCCGWA, BPCUMENT.PROC_DATA.INCLUDE_D_STS);
        CEP.TRC(SCCGWA, BPRDIARY.D_STATUS);
        CEP.TRC(SCCGWA, BPRDIARY.KEY.SEQ);
        CEP.TRC(SCCGWA, "BROWSE END1");
        if (BPCRMENT.RETURN_INFO != 'F') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_ENT_RECORD_NOT_EXIST, BPCUMENT.PROC_DATA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_END_BRW = 'N';
        while (BPCRMENT.RETURN_INFO != 'N' 
            && WS_END_BRW != 'Y') {
            BPCRMENT.INFO.SEQ = BPRDIARY.KEY.SEQ;
            BPCRMENT.INFO.FUNC = 'B';
            BPCRMENT.INFO.OPT = 'N';
            S000_CALL_BPZRMENT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPRDIARY.CONTRACTNO);
            CEP.TRC(SCCGWA, BPRDIARY.D_TYPE);
            CEP.TRC(SCCGWA, BPCRMENT.INFO.START_DT);
            CEP.TRC(SCCGWA, BPCRMENT.INFO.END_DT);
            CEP.TRC(SCCGWA, BPCUMENT.PROC_DATA.INCLUDE_D_STS);
            CEP.TRC(SCCGWA, BPRDIARY.D_STATUS);
            CEP.TRC(SCCGWA, BPRDIARY.KEY.SEQ);
            if (BPCRMENT.RETURN_INFO == 'F') {
                if (BPCUMENT.PROC_DATA.INCLUDE_D_STS == 'Y') {
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRDIARY);
                    IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUMENT.DIARY_DATA);
                    WS_END_BRW = 'Y';
                } else {
                    if (BPRDIARY.D_STATUS != 'D') {
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRDIARY);
                        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUMENT.DIARY_DATA);
                        WS_END_BRW = 'Y';
                    }
                }
            }
            CEP.TRC(SCCGWA, WS_END_BRW);
        }
        BPCRMENT.INFO.FUNC = 'B';
        BPCRMENT.INFO.OPT = 'E';
        S000_CALL_BPZRMENT();
        if (pgmRtn) return;
    }
    public void B300_DEL_SINGLE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRDIARY);
        IBS.init(SCCGWA, BPRBDIAR);
        IBS.init(SCCGWA, BPCRMENT);
        BPCRMENT.INFO.FUNC = 'R';
        CEP.TRC(SCCGWA, BPCUMENT.DIARY_DATA.KEY.SEQ);
        BPRDIARY.KEY.SEQ = BPCUMENT.DIARY_DATA.KEY.SEQ;
        BPCRMENT.LEN = 1468;
        BPCRMENT.INFO.POINTER = BPRDIARY;
        S000_CALL_BPZRMENT();
        if (pgmRtn) return;
        if (BPCRMENT.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ENT_RECORD_NOT_EXIST;
            S000_ERR_MSG_PROC_END();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRDIARY, BPRBDIAR);
        BPCRMENT.INFO.FUNC = 'D';
        BPCRMENT.LEN = 1468;
        BPCRMENT.INFO.POINTER = BPRDIARY;
        S000_CALL_BPZRMENT();
        if (pgmRtn) return;
    }
    public void B400_DEL_ONLY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRDIARY);
        IBS.init(SCCGWA, BPCRMENT);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUMENT.DIARY_DATA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRDIARY);
        BPCRMENT.INFO.FUNC = 'D';
        BPCRMENT.LEN = 1468;
        BPCRMENT.INFO.POINTER = BPRDIARY;
        S000_CALL_BPZRMENT();
        if (pgmRtn) return;
    }
    public void B200_TRN_DATA_TO_DIARY_PROC() throws IOException,SQLException,Exception {
        BPRDIARY.CONTRACTNO = BPCUMENT.DIARY_DATA.CONTRACT_NO;
        BPRDIARY.D_TYPE = BPCUMENT.DIARY_DATA.DIARY_TYPE;
        BPRDIARY.EVENTDATE = BPCUMENT.DIARY_DATA.DIARY_DATE;
        BPRDIARY.ACTL_DATE = BPCUMENT.DIARY_DATA.ACTL_DATE;
        BPRDIARY.ACTL_TIME = BPCUMENT.DIARY_DATA.ACTL_TIME;
        BPRDIARY.ACCT_CENTRE = BPCUMENT.DIARY_DATA.ACCT_CENTRE;
        BPRDIARY.CONTRACT_TYPE = BPCUMENT.DIARY_DATA.CONTRACT_TYPE;
        BPRDIARY.PRODUCT_TYPE = BPCUMENT.DIARY_DATA.PRODUCT_TYPE;
        BPRDIARY.TRN_DATE = BPCUMENT.DIARY_DATA.TRN_DATE;
        BPRDIARY.TRN_JRN_NO = BPCUMENT.DIARY_DATA.TRN_JRN_NO;
        BPRDIARY.DIARY_BATNO = BPCUMENT.DIARY_DATA.DIARY_BATNO;
        BPRDIARY.D_STATUS = BPCUMENT.DIARY_DATA.DIARY_STATUS;
        BPRDIARY.AMOUNT1 = BPCUMENT.DIARY_DATA.AMOUNT1;
        BPRDIARY.AMOUNT2 = BPCUMENT.DIARY_DATA.AMOUNT2;
        BPRDIARY.AMOUNT3 = BPCUMENT.DIARY_DATA.AMOUNT3;
        BPRDIARY.AMOUNT4 = BPCUMENT.DIARY_DATA.AMOUNT4;
        BPRDIARY.AMOUNT5 = BPCUMENT.DIARY_DATA.AMOUNT5;
        BPRDIARY.AMOUNT6 = BPCUMENT.DIARY_DATA.AMOUNT6;
        BPRDIARY.AMOUNT7 = BPCUMENT.DIARY_DATA.AMOUNT7;
        BPRDIARY.AMOUNT8 = BPCUMENT.DIARY_DATA.AMOUNT8;
        BPRDIARY.AMOUNT9 = BPCUMENT.DIARY_DATA.AMOUNT9;
        BPRDIARY.AMOUNT10 = BPCUMENT.DIARY_DATA.AMOUNT10;
        BPRDIARY.AMOUNT11 = BPCUMENT.DIARY_DATA.AMOUNT11;
        BPRDIARY.AMOUNT12 = BPCUMENT.DIARY_DATA.AMOUNT12;
        BPRDIARY.AMOUNT13 = BPCUMENT.DIARY_DATA.AMOUNT13;
        BPRDIARY.AMOUNT14 = BPCUMENT.DIARY_DATA.AMOUNT14;
        BPRDIARY.AMOUNT15 = BPCUMENT.DIARY_DATA.AMOUNT15;
        BPRDIARY.AMOUNT16 = BPCUMENT.DIARY_DATA.AMOUNT16;
        BPRDIARY.AMOUNT17 = BPCUMENT.DIARY_DATA.AMOUNT17;
        BPRDIARY.AMOUNT18 = BPCUMENT.DIARY_DATA.AMOUNT18;
        BPRDIARY.AMOUNT19 = BPCUMENT.DIARY_DATA.AMOUNT19;
        BPRDIARY.AS_OF_DATE = BPCUMENT.DIARY_DATA.AS_OF_DATE;
        BPRDIARY.AUTO_CNFM = BPCUMENT.DIARY_DATA.AUTO_CNFM;
        BPRDIARY.AUTO_STLMT = BPCUMENT.DIARY_DATA.AUTO_STLMT;
        BPRDIARY.BASE_RT_NO = BPCUMENT.DIARY_DATA.BASE_RT_NO;
        BPRDIARY.BGHT_SOLD = BPCUMENT.DIARY_DATA.BGHT_SOLD;
        BPRDIARY.BR_CHG = BPCUMENT.DIARY_DATA.BR_CHG;
        BPRDIARY.CALC_PRD1 = BPCUMENT.DIARY_DATA.CALC_PRD1;
        BPRDIARY.CALC_PRD2 = BPCUMENT.DIARY_DATA.CALC_PRD2;
        BPRDIARY.CALC_RDN = BPCUMENT.DIARY_DATA.CALC_RDN;
        BPRDIARY.CCY_1 = BPCUMENT.DIARY_DATA.CCY_1;
        BPRDIARY.CCY_2 = BPCUMENT.DIARY_DATA.CCY_2;
        BPRDIARY.CCY_3 = BPCUMENT.DIARY_DATA.CCY_3;
        BPRDIARY.CCY_4 = BPCUMENT.DIARY_DATA.CCY_4;
        BPRDIARY.CLIENT_NO = BPCUMENT.DIARY_DATA.CLIENT_NO;
        BPRDIARY.CMB_SPL_ID = BPCUMENT.DIARY_DATA.CMB_SPL_ID;
        BPRDIARY.CNFM_FLG = BPCUMENT.DIARY_DATA.CNFM_FLG;
        BPRDIARY.CNT_PERIOD = BPCUMENT.DIARY_DATA.CNT_PERIOD;
        BPRDIARY.CRT_CODE = BPCUMENT.DIARY_DATA.CRT_CODE;
        BPRDIARY.DAYSNOTICE = BPCUMENT.DIARY_DATA.DAYSNOTICE;
        BPRDIARY.D_DAYTYPE = BPCUMENT.DIARY_DATA.D_DAYTYPE;
        BPRDIARY.DEALER = BPCUMENT.DIARY_DATA.DEALER;
        BPRDIARY.D_REFNO = BPCUMENT.DIARY_DATA.D_REFNO;
        BPRDIARY.EMUCCYEXCH = BPCUMENT.DIARY_DATA.EMUCCYEXCH;
        BPRDIARY.EMUCONV = BPCUMENT.DIARY_DATA.EMUCONV;
        BPRDIARY.EMUINTSTL = BPCUMENT.DIARY_DATA.EMUINTSTL;
        BPRDIARY.EX_STATUS = BPCUMENT.DIARY_DATA.EX_STATUS;
        BPRDIARY.FEE_MIN = BPCUMENT.DIARY_DATA.FEE_MIN;
        BPRDIARY.GEN_IND1 = BPCUMENT.DIARY_DATA.GEN_IND1;
        BPRDIARY.GEN_IND2 = BPCUMENT.DIARY_DATA.GEN_IND2;
        BPRDIARY.HOLIDAY = BPCUMENT.DIARY_DATA.HOLIDAY;
        BPRDIARY.HOLOVR_CHK = BPCUMENT.DIARY_DATA.HOLOVR_CHK;
        BPRDIARY.INT_TO_SUS = BPCUMENT.DIARY_DATA.INT_TO_SUS;
        BPRDIARY.LOAN_COUNT = BPCUMENT.DIARY_DATA.LOAN_COUNT;
        BPRDIARY.MAX_RATE = BPCUMENT.DIARY_DATA.MAX_RATE;
        BPRDIARY.MIN_FEE = BPCUMENT.DIARY_DATA.MIN_FEE;
        BPRDIARY.MIN_RATE = BPCUMENT.DIARY_DATA.MIN_RATE;
        BPRDIARY.NOTIF_EVT = BPCUMENT.DIARY_DATA.NOTIF_EVT;
        BPRDIARY.ORIGIN = BPCUMENT.DIARY_DATA.ORIGIN;
        BPRDIARY.OTH_CN_NO = BPCUMENT.DIARY_DATA.OTH_CN_NO;
        BPRDIARY.PAY_NOSTRO = BPCUMENT.DIARY_DATA.PAY_NOSTRO;
        BPRDIARY.PAYAGT_INT = BPCUMENT.DIARY_DATA.PAYAGT_INT;
        BPRDIARY.PAYNST_INT = BPCUMENT.DIARY_DATA.PAYNST_INT;
        BPRDIARY.PAYRCV_DT = BPCUMENT.DIARY_DATA.PAYRCV_DT;
        BPRDIARY.PAYRCV_RDN = BPCUMENT.DIARY_DATA.PAYRCV_RDN;
        BPRDIARY.PAYRCV_YN = BPCUMENT.DIARY_DATA.PAYRCV_YN;
        BPRDIARY.PAYRCVST1 = BPCUMENT.DIARY_DATA.PAYRCVST1;
        BPRDIARY.PAYRCVST2 = BPCUMENT.DIARY_DATA.PAYRCVST2;
        BPRDIARY.PROJ_FLAG = BPCUMENT.DIARY_DATA.PROJ_FLAG;
        BPRDIARY.PTC_SEQ = BPCUMENT.DIARY_DATA.PTC_SEQ;
        BPRDIARY.PTC_TYPE = BPCUMENT.DIARY_DATA.PTC_TYPE;
        BPRDIARY.PYMT_FLG = BPCUMENT.DIARY_DATA.PYMT_FLG;
        df = new DecimalFormat("0.00000000");
        WS_RATE_STR = df.format(0);
        df = new DecimalFormat("0.00000000");
        WS_RATE_STR = df.format(BPCUMENT.DIARY_DATA.RATEX1);
        BPRDIARY.RATE_1 = "" + WS_RATE_STR;
        JIBS_tmp_int = BPRDIARY.RATE_1.length();
        for (int i=0;i<14-JIBS_tmp_int;i++) BPRDIARY.RATE_1 = "0" + BPRDIARY.RATE_1;
        df = new DecimalFormat("0.00000000");
        WS_RATE_STR = df.format(0);
        df = new DecimalFormat("0.00000000");
        WS_RATE_STR = df.format(BPCUMENT.DIARY_DATA.RATEX2);
        BPRDIARY.RATE_2 = "" + WS_RATE_STR;
        JIBS_tmp_int = BPRDIARY.RATE_2.length();
        for (int i=0;i<14-JIBS_tmp_int;i++) BPRDIARY.RATE_2 = "0" + BPRDIARY.RATE_2;
        df = new DecimalFormat("0.00000000");
        WS_RATE_STR = df.format(0);
        df = new DecimalFormat("0.00000000");
        WS_RATE_STR = df.format(BPCUMENT.DIARY_DATA.RATEX3);
        BPRDIARY.RATE_3 = "" + WS_RATE_STR;
        JIBS_tmp_int = BPRDIARY.RATE_3.length();
        for (int i=0;i<14-JIBS_tmp_int;i++) BPRDIARY.RATE_3 = "0" + BPRDIARY.RATE_3;
        df = new DecimalFormat("0.00000000");
        WS_RATE_STR = df.format(0);
        df = new DecimalFormat("0.00000000");
        WS_RATE_STR = df.format(BPCUMENT.DIARY_DATA.RATEX4);
        BPRDIARY.RATE_4 = "" + WS_RATE_STR;
        JIBS_tmp_int = BPRDIARY.RATE_4.length();
        for (int i=0;i<14-JIBS_tmp_int;i++) BPRDIARY.RATE_4 = "0" + BPRDIARY.RATE_4;
        df = new DecimalFormat("0.00000000");
        WS_RATE_STR = df.format(0);
        df = new DecimalFormat("0.00000000");
        WS_RATE_STR = df.format(BPCUMENT.DIARY_DATA.RATEX5);
        BPRDIARY.RATE_5 = "" + WS_RATE_STR;
        JIBS_tmp_int = BPRDIARY.RATE_5.length();
        for (int i=0;i<14-JIBS_tmp_int;i++) BPRDIARY.RATE_5 = "0" + BPRDIARY.RATE_5;
        df = new DecimalFormat("0.00000000");
        WS_RATE_STR = df.format(0);
        df = new DecimalFormat("0.00000000");
        WS_RATE_STR = df.format(BPCUMENT.DIARY_DATA.RATEX6);
        BPRDIARY.RATE_6 = "" + WS_RATE_STR;
        JIBS_tmp_int = BPRDIARY.RATE_6.length();
        for (int i=0;i<14-JIBS_tmp_int;i++) BPRDIARY.RATE_6 = "0" + BPRDIARY.RATE_6;
        df = new DecimalFormat("0.00000000");
        WS_RATE_STR = df.format(0);
        df = new DecimalFormat("0.00000000");
        WS_RATE_STR = df.format(BPCUMENT.DIARY_DATA.RATEX7);
        BPRDIARY.RATE_7 = "" + WS_RATE_STR;
        JIBS_tmp_int = BPRDIARY.RATE_7.length();
        for (int i=0;i<14-JIBS_tmp_int;i++) BPRDIARY.RATE_7 = "0" + BPRDIARY.RATE_7;
        df = new DecimalFormat("0.00000000");
        WS_RATE_STR = df.format(0);
        df = new DecimalFormat("0.00000000");
        WS_RATE_STR = df.format(BPCUMENT.DIARY_DATA.RATEX8);
        BPRDIARY.RATE_8 = "" + WS_RATE_STR;
        JIBS_tmp_int = BPRDIARY.RATE_8.length();
        for (int i=0;i<14-JIBS_tmp_int;i++) BPRDIARY.RATE_8 = "0" + BPRDIARY.RATE_8;
        df = new DecimalFormat("0.00000000");
        WS_RATE_STR = df.format(0);
        df = new DecimalFormat("0.00000000");
        WS_RATE_STR = df.format(BPCUMENT.DIARY_DATA.RATEX9);
        JIBS_NumStr = "" + WS_RATE_STR;
        BPRDIARY.RATE_9 = JIBS_NumStr.charAt(0);
        df = new DecimalFormat("0.00000000");
        WS_RATE_STR = df.format(0);
        df = new DecimalFormat("0.00000000");
        WS_RATE_STR = df.format(BPCUMENT.DIARY_DATA.RATEX10);
        BPRDIARY.RATE_10 = "" + WS_RATE_STR;
        JIBS_tmp_int = BPRDIARY.RATE_10.length();
        for (int i=0;i<14-JIBS_tmp_int;i++) BPRDIARY.RATE_10 = "0" + BPRDIARY.RATE_10;
        BPRDIARY.RATE_ID = BPCUMENT.DIARY_DATA.RATE_ID;
        BPRDIARY.RATE_WIDTH = BPCUMENT.DIARY_DATA.RATE_WIDTH;
        BPRDIARY.RATEX1 = BPCUMENT.DIARY_DATA.RATEX1;
        BPRDIARY.RATEX10 = BPCUMENT.DIARY_DATA.RATEX10;
        BPRDIARY.RATEX1MD = BPCUMENT.DIARY_DATA.RATEX1MD;
        BPRDIARY.RATEX2 = BPCUMENT.DIARY_DATA.RATEX2;
        BPRDIARY.RATEX2MD = BPCUMENT.DIARY_DATA.RATEX2MD;
        BPRDIARY.RATEX3 = BPCUMENT.DIARY_DATA.RATEX3;
        BPRDIARY.RATEX3MD = BPCUMENT.DIARY_DATA.RATEX3MD;
        BPRDIARY.RATEX4 = BPCUMENT.DIARY_DATA.RATEX4;
        BPRDIARY.RATEX4MD = BPCUMENT.DIARY_DATA.RATEX4MD;
        BPRDIARY.RATEX5 = BPCUMENT.DIARY_DATA.RATEX5;
        BPRDIARY.RATEX6 = BPCUMENT.DIARY_DATA.RATEX6;
        BPRDIARY.RATEX7 = BPCUMENT.DIARY_DATA.RATEX7;
        BPRDIARY.RATEX8 = BPCUMENT.DIARY_DATA.RATEX8;
        BPRDIARY.RATEX9 = BPCUMENT.DIARY_DATA.RATEX9;
        BPRDIARY.PAY_NOSTRO_AC = BPCUMENT.DIARY_DATA.PAY_NOSTRO_AC;
        BPRDIARY.RCV_DD_NO1 = BPCUMENT.DIARY_DATA.RCV_DD_NO1;
        BPRDIARY.RCV_DD_NO2 = BPCUMENT.DIARY_DATA.RCV_DD_NO2;
        BPRDIARY.RCV_DD_NO3 = BPCUMENT.DIARY_DATA.RCV_DD_NO3;
        BPRDIARY.RCV_NOSTRO_AC = BPCUMENT.DIARY_DATA.RCV_NOSTRO_AC;
        BPRDIARY.PAY_DD_NO1 = BPCUMENT.DIARY_DATA.PAY_DD_NO1;
        BPRDIARY.PAY_DD_NO2 = BPCUMENT.DIARY_DATA.PAY_DD_NO2;
        BPRDIARY.PAY_DD_NO3 = BPCUMENT.DIARY_DATA.PAY_DD_NO3;
        BPRDIARY.RCV_NOSTRO = BPCUMENT.DIARY_DATA.RCV_NOSTRO;
        BPRDIARY.RCVAGT_INT = BPCUMENT.DIARY_DATA.RCVAGT_INT;
        BPRDIARY.RCVNST_INT = BPCUMENT.DIARY_DATA.RCVNST_INT;
        BPRDIARY.REL_DAY = BPCUMENT.DIARY_DATA.REL_DAY;
        BPRDIARY.HOLOVR_CHK_CNTY2 = BPCUMENT.DIARY_DATA.HOLOVR_CHK_CNTY2;
        BPRDIARY.HOLOVR_CHK_CNTY3 = BPCUMENT.DIARY_DATA.HOLOVR_CHK_CNTY3;
        BPRDIARY.HOLOVR_CHK_CNTY4 = BPCUMENT.DIARY_DATA.HOLOVR_CHK_CNTY4;
        BPRDIARY.HOLOVR_CHK_CNTY5 = BPCUMENT.DIARY_DATA.HOLOVR_CHK_CNTY5;
        BPRDIARY.DD_CHQ_NO1 = BPCUMENT.DIARY_DATA.DD_CHQ_NO1;
        BPRDIARY.DD_CHQ_NO2 = BPCUMENT.DIARY_DATA.DD_CHQ_NO2;
        BPRDIARY.DD_CHQ_NO3 = BPCUMENT.DIARY_DATA.DD_CHQ_NO3;
    }
    public void B200_TRN_DATA_TO_UMENT_PROC() throws IOException,SQLException,Exception {
        BPCUMENT.DIARY_DATA.KEY.SEQ = BPRDIARY.KEY.SEQ;
        BPCUMENT.DIARY_DATA.CONTRACT_NO = BPRDIARY.CONTRACTNO;
        BPCUMENT.DIARY_DATA.DIARY_TYPE = BPRDIARY.D_TYPE;
        BPCUMENT.DIARY_DATA.DIARY_DATE = BPRDIARY.EVENTDATE;
        BPCUMENT.DIARY_DATA.ACTL_DATE = BPRDIARY.ACTL_DATE;
        BPCUMENT.DIARY_DATA.ACTL_TIME = BPRDIARY.ACTL_TIME;
        BPCUMENT.DIARY_DATA.ACCT_CENTRE = BPRDIARY.ACCT_CENTRE;
        BPCUMENT.DIARY_DATA.CONTRACT_TYPE = BPRDIARY.CONTRACT_TYPE;
        BPCUMENT.DIARY_DATA.TRN_DATE = BPRDIARY.TRN_DATE;
        BPCUMENT.DIARY_DATA.TRN_JRN_NO = BPRDIARY.TRN_JRN_NO;
        BPCUMENT.DIARY_DATA.DIARY_STATUS = BPRDIARY.D_STATUS;
        BPCUMENT.DIARY_DATA.AMOUNT1 = BPRDIARY.AMOUNT1;
        BPCUMENT.DIARY_DATA.AMOUNT2 = BPRDIARY.AMOUNT2;
        BPCUMENT.DIARY_DATA.AMOUNT3 = BPRDIARY.AMOUNT3;
        BPCUMENT.DIARY_DATA.AMOUNT4 = BPRDIARY.AMOUNT4;
        BPCUMENT.DIARY_DATA.AMOUNT5 = BPRDIARY.AMOUNT5;
        BPCUMENT.DIARY_DATA.AMOUNT6 = BPRDIARY.AMOUNT6;
        BPCUMENT.DIARY_DATA.AMOUNT7 = BPRDIARY.AMOUNT7;
        BPCUMENT.DIARY_DATA.AMOUNT8 = BPRDIARY.AMOUNT8;
        BPCUMENT.DIARY_DATA.AMOUNT9 = BPRDIARY.AMOUNT9;
        BPCUMENT.DIARY_DATA.AMOUNT10 = BPRDIARY.AMOUNT10;
        BPCUMENT.DIARY_DATA.AMOUNT11 = BPRDIARY.AMOUNT11;
        BPCUMENT.DIARY_DATA.AMOUNT12 = BPRDIARY.AMOUNT12;
        BPCUMENT.DIARY_DATA.AMOUNT13 = BPRDIARY.AMOUNT13;
        BPCUMENT.DIARY_DATA.AMOUNT14 = BPRDIARY.AMOUNT14;
        BPCUMENT.DIARY_DATA.AMOUNT15 = BPRDIARY.AMOUNT15;
        BPCUMENT.DIARY_DATA.AMOUNT16 = BPRDIARY.AMOUNT16;
        BPCUMENT.DIARY_DATA.AMOUNT17 = BPRDIARY.AMOUNT17;
        BPCUMENT.DIARY_DATA.AMOUNT18 = BPRDIARY.AMOUNT18;
        BPCUMENT.DIARY_DATA.AMOUNT19 = BPRDIARY.AMOUNT19;
        BPCUMENT.DIARY_DATA.AS_OF_DATE = BPRDIARY.AS_OF_DATE;
        BPCUMENT.DIARY_DATA.AUTO_CNFM = BPRDIARY.AUTO_CNFM;
        BPCUMENT.DIARY_DATA.AUTO_STLMT = BPRDIARY.AUTO_STLMT;
        BPCUMENT.DIARY_DATA.BASE_RT_NO = BPRDIARY.BASE_RT_NO;
        BPCUMENT.DIARY_DATA.BGHT_SOLD = BPRDIARY.BGHT_SOLD;
        BPCUMENT.DIARY_DATA.BR_CHG = BPRDIARY.BR_CHG;
        BPCUMENT.DIARY_DATA.CALC_PRD1 = BPRDIARY.CALC_PRD1;
        BPCUMENT.DIARY_DATA.CALC_PRD2 = BPRDIARY.CALC_PRD2;
        BPCUMENT.DIARY_DATA.CALC_RDN = BPRDIARY.CALC_RDN;
        BPCUMENT.DIARY_DATA.CCY_1 = BPRDIARY.CCY_1;
        BPCUMENT.DIARY_DATA.CCY_2 = BPRDIARY.CCY_2;
        BPCUMENT.DIARY_DATA.CCY_3 = BPRDIARY.CCY_3;
        BPCUMENT.DIARY_DATA.CCY_4 = BPRDIARY.CCY_4;
        BPCUMENT.DIARY_DATA.CLIENT_NO = BPRDIARY.CLIENT_NO;
        BPCUMENT.DIARY_DATA.CMB_SPL_ID = BPRDIARY.CMB_SPL_ID;
        BPCUMENT.DIARY_DATA.CNFM_FLG = BPRDIARY.CNFM_FLG;
        BPCUMENT.DIARY_DATA.CNT_PERIOD = BPRDIARY.CNT_PERIOD;
        BPCUMENT.DIARY_DATA.CRT_CODE = BPRDIARY.CRT_CODE;
        BPCUMENT.DIARY_DATA.DAYSNOTICE = BPRDIARY.DAYSNOTICE;
        BPCUMENT.DIARY_DATA.D_DAYTYPE = BPRDIARY.D_DAYTYPE;
        BPCUMENT.DIARY_DATA.DEALER = BPRDIARY.DEALER;
        BPCUMENT.DIARY_DATA.D_REFNO = BPRDIARY.D_REFNO;
        BPCUMENT.DIARY_DATA.EMUCCYEXCH = BPRDIARY.EMUCCYEXCH;
        BPCUMENT.DIARY_DATA.EMUCONV = BPRDIARY.EMUCONV;
        BPCUMENT.DIARY_DATA.EMUINTSTL = BPRDIARY.EMUINTSTL;
        BPCUMENT.DIARY_DATA.EX_STATUS = BPRDIARY.EX_STATUS;
        BPCUMENT.DIARY_DATA.FEE_MIN = BPRDIARY.FEE_MIN;
        BPCUMENT.DIARY_DATA.GEN_IND1 = BPRDIARY.GEN_IND1;
        BPCUMENT.DIARY_DATA.GEN_IND2 = BPRDIARY.GEN_IND2;
        BPCUMENT.DIARY_DATA.HOLIDAY = BPRDIARY.HOLIDAY;
        BPCUMENT.DIARY_DATA.HOLOVR_CHK = BPRDIARY.HOLOVR_CHK;
        BPCUMENT.DIARY_DATA.INT_TO_SUS = BPRDIARY.INT_TO_SUS;
        BPCUMENT.DIARY_DATA.LOAN_COUNT = BPRDIARY.LOAN_COUNT;
        BPCUMENT.DIARY_DATA.MAX_RATE = BPRDIARY.MAX_RATE;
        BPCUMENT.DIARY_DATA.MIN_FEE = BPRDIARY.MIN_FEE;
        BPCUMENT.DIARY_DATA.MIN_RATE = BPRDIARY.MIN_RATE;
        BPCUMENT.DIARY_DATA.NOTIF_EVT = BPRDIARY.NOTIF_EVT;
        BPCUMENT.DIARY_DATA.ORIGIN = BPRDIARY.ORIGIN;
        BPCUMENT.DIARY_DATA.OTH_CN_NO = BPRDIARY.OTH_CN_NO;
        BPCUMENT.DIARY_DATA.PAY_NOSTRO = BPRDIARY.PAY_NOSTRO;
        BPCUMENT.DIARY_DATA.PAYAGT_INT = BPRDIARY.PAYAGT_INT;
        BPCUMENT.DIARY_DATA.PAYNST_INT = BPRDIARY.PAYNST_INT;
        BPCUMENT.DIARY_DATA.PAYRCV_DT = BPRDIARY.PAYRCV_DT;
        BPCUMENT.DIARY_DATA.PAYRCV_RDN = BPRDIARY.PAYRCV_RDN;
        BPCUMENT.DIARY_DATA.PAYRCV_YN = BPRDIARY.PAYRCV_YN;
        BPCUMENT.DIARY_DATA.PAYRCVST1 = BPRDIARY.PAYRCVST1;
        BPCUMENT.DIARY_DATA.PAYRCVST2 = BPRDIARY.PAYRCVST2;
        BPCUMENT.DIARY_DATA.PROJ_FLAG = BPRDIARY.PROJ_FLAG;
        BPCUMENT.DIARY_DATA.PTC_SEQ = BPRDIARY.PTC_SEQ;
        BPCUMENT.DIARY_DATA.PTC_TYPE = BPRDIARY.PTC_TYPE;
        BPCUMENT.DIARY_DATA.PYMT_FLG = BPRDIARY.PYMT_FLG;
        BPCUMENT.DIARY_DATA.RATE_1 = BPRDIARY.RATE_1;
        BPCUMENT.DIARY_DATA.RATE_2 = BPRDIARY.RATE_2;
        BPCUMENT.DIARY_DATA.RATE_3 = BPRDIARY.RATE_3;
        BPCUMENT.DIARY_DATA.RATE_4 = BPRDIARY.RATE_4;
        BPCUMENT.DIARY_DATA.RATE_5 = BPRDIARY.RATE_5;
        BPCUMENT.DIARY_DATA.RATE_6 = BPRDIARY.RATE_6;
        BPCUMENT.DIARY_DATA.RATE_7 = BPRDIARY.RATE_7;
        BPCUMENT.DIARY_DATA.RATE_8 = BPRDIARY.RATE_8;
        BPCUMENT.DIARY_DATA.RATE_9 = BPRDIARY.RATE_9;
        BPCUMENT.DIARY_DATA.RATE_10 = BPRDIARY.RATE_10;
        BPCUMENT.DIARY_DATA.RATE_ID = BPRDIARY.RATE_ID;
        BPCUMENT.DIARY_DATA.RATE_WIDTH = BPRDIARY.RATE_WIDTH;
        BPCUMENT.DIARY_DATA.RATEX1 = BPRDIARY.RATEX1;
        BPCUMENT.DIARY_DATA.RATEX10 = BPRDIARY.RATEX10;
        BPCUMENT.DIARY_DATA.RATEX1MD = BPRDIARY.RATEX1MD;
        BPCUMENT.DIARY_DATA.RATEX2 = BPRDIARY.RATEX2;
        BPCUMENT.DIARY_DATA.RATEX2MD = BPRDIARY.RATEX2MD;
        BPCUMENT.DIARY_DATA.RATEX3 = BPRDIARY.RATEX3;
        BPCUMENT.DIARY_DATA.RATEX3MD = BPRDIARY.RATEX3MD;
        BPCUMENT.DIARY_DATA.RATEX4 = BPRDIARY.RATEX4;
        BPCUMENT.DIARY_DATA.RATEX4MD = BPRDIARY.RATEX4MD;
        BPCUMENT.DIARY_DATA.RATEX5 = BPRDIARY.RATEX5;
        BPCUMENT.DIARY_DATA.RATEX6 = BPRDIARY.RATEX6;
        BPCUMENT.DIARY_DATA.RATEX7 = BPRDIARY.RATEX7;
        BPCUMENT.DIARY_DATA.RATEX8 = BPRDIARY.RATEX8;
        BPCUMENT.DIARY_DATA.RATEX9 = BPRDIARY.RATEX9;
        BPCUMENT.DIARY_DATA.PAY_NOSTRO_AC = BPRDIARY.PAY_NOSTRO_AC;
        BPCUMENT.DIARY_DATA.RCV_DD_NO1 = BPRDIARY.RCV_DD_NO1;
        BPCUMENT.DIARY_DATA.RCV_DD_NO2 = BPRDIARY.RCV_DD_NO2;
        BPCUMENT.DIARY_DATA.RCV_DD_NO3 = BPRDIARY.RCV_DD_NO3;
        BPCUMENT.DIARY_DATA.RCV_NOSTRO_AC = BPRDIARY.RCV_NOSTRO_AC;
        BPCUMENT.DIARY_DATA.PAY_DD_NO1 = BPRDIARY.PAY_DD_NO1;
        BPCUMENT.DIARY_DATA.PAY_DD_NO2 = BPRDIARY.PAY_DD_NO2;
        BPCUMENT.DIARY_DATA.PAY_DD_NO3 = BPRDIARY.PAY_DD_NO3;
        BPCUMENT.DIARY_DATA.RCV_NOSTRO = BPRDIARY.RCV_NOSTRO;
        BPCUMENT.DIARY_DATA.RCVAGT_INT = BPRDIARY.RCVAGT_INT;
        BPCUMENT.DIARY_DATA.RCVNST_INT = BPRDIARY.RCVNST_INT;
        BPCUMENT.DIARY_DATA.REL_DAY = BPRDIARY.REL_DAY;
        BPCUMENT.DIARY_DATA.HOLOVR_CHK_CNTY2 = BPRDIARY.HOLOVR_CHK_CNTY2;
        BPCUMENT.DIARY_DATA.HOLOVR_CHK_CNTY3 = BPRDIARY.HOLOVR_CHK_CNTY3;
        BPCUMENT.DIARY_DATA.HOLOVR_CHK_CNTY4 = BPRDIARY.HOLOVR_CHK_CNTY4;
        BPCUMENT.DIARY_DATA.HOLOVR_CHK_CNTY5 = BPRDIARY.HOLOVR_CHK_CNTY5;
        BPCUMENT.DIARY_DATA.DD_CHQ_NO1 = BPRDIARY.DD_CHQ_NO1;
        BPCUMENT.DIARY_DATA.DD_CHQ_NO2 = BPRDIARY.DD_CHQ_NO2;
        BPCUMENT.DIARY_DATA.DD_CHQ_NO3 = BPRDIARY.DD_CHQ_NO3;
    }
    public void B100_RD_UPD_BPTDYCNT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRDYCNT);
        IBS.init(SCCGWA, BPCRDYCN);
        BPCRDYCN.INFO.FUNC = 'R';
        BPRDYCNT.KEY.CONTRACT_NO = BPCUMENT.DIARY_DATA.CONTRACT_NO;
        BPRDYCNT.KEY.DIARY_BATNO = BPCUMENT.DIARY_DATA.DIARY_BATNO;
        BPCRDYCN.INFO.POINTER = BPRDYCNT;
        BPCRDYCN.LEN = 85;
        S000_CALL_BPZRDYCN();
        if (pgmRtn) return;
    }
    public void B110_UPDATE_BPTDYCNT_PROC() throws IOException,SQLException,Exception {
        BPCRDYCN.INFO.FUNC = 'U';
        BPRDYCNT.DIARY_CNT = BPRDYCNT.DIARY_CNT + WS_TMP_CNT;
        BPRDYCNT.CONTRACT_TYPE = BPCUMENT.DIARY_DATA.CONTRACT_TYPE;
        CEP.TRC(SCCGWA, BPCUMENT.PROC_DATA.LN_FLAG);
        if (BPCUMENT.PROC_DATA.LN_FLAG != ' ') {
            BPRDYCNT.PRODUCT_TYPE = "" + BPCUMENT.PROC_DATA.LN_FLAG;
            JIBS_tmp_int = BPRDYCNT.PRODUCT_TYPE.length();
            for (int i=0;i<1-JIBS_tmp_int;i++) BPRDYCNT.PRODUCT_TYPE = "0" + BPRDYCNT.PRODUCT_TYPE;
        }
        BPCRDYCN.INFO.POINTER = BPRDYCNT;
        BPCRDYCN.LEN = 85;
        S000_CALL_BPZRDYCN();
        if (pgmRtn) return;
    }
    public void B120_WRITE_BPTDYCNT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRDYCNT);
        IBS.init(SCCGWA, BPCRDYCN);
        BPCRDYCN.INFO.FUNC = 'A';
        BPRDYCNT.KEY.CONTRACT_NO = BPCUMENT.DIARY_DATA.CONTRACT_NO;
        BPRDYCNT.KEY.DIARY_BATNO = BPCUMENT.DIARY_DATA.DIARY_BATNO;
        BPRDYCNT.DIARY_CNT = WS_TMP_CNT;
        BPRDYCNT.CONTRACT_TYPE = BPCUMENT.DIARY_DATA.CONTRACT_TYPE;
        CEP.TRC(SCCGWA, BPCUMENT.PROC_DATA.LN_FLAG);
        if (BPCUMENT.PROC_DATA.LN_FLAG != ' ') {
            BPRDYCNT.PRODUCT_TYPE = "" + BPCUMENT.PROC_DATA.LN_FLAG;
            JIBS_tmp_int = BPRDYCNT.PRODUCT_TYPE.length();
            for (int i=0;i<1-JIBS_tmp_int;i++) BPRDYCNT.PRODUCT_TYPE = "0" + BPRDYCNT.PRODUCT_TYPE;
        }
        BPCRDYCN.INFO.POINTER = BPRDYCNT;
        BPCRDYCN.LEN = 85;
        S000_CALL_BPZRDYCN();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZRMENT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BPZRMENT1");
        CEP.TRC(SCCGWA, BPCUMENT.PROC_DATA.DT_EQ_FLG);
        CEP.TRC(SCCGWA, BPCRMENT.INFO.DT_EQ_FLG);
        IBS.CALLCPN(SCCGWA, "BP-R-MENT-MAINT", BPCRMENT);
        CEP.TRC(SCCGWA, BPCRMENT.RC.RC_CODE);
        CEP.TRC(SCCGWA, BPCRMENT.RETURN_INFO);
        BPCUMENT.PROC_DATA.RETURN_INFO = BPCRMENT.RETURN_INFO;
        if (BPCRMENT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRMENT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUMENT.PROC_DATA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRDYCN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-DYCNT-MAINT", BPCRDYCN);
        CEP.TRC(SCCGWA, BPCRDYCN.RC.RC_CODE);
        if (BPCRDYCN.RC.RC_CODE != 0) {
            WS_ERR_MSG = "" + BPCRDYCN.RC.RC_CODE;
            JIBS_tmp_int = WS_ERR_MSG.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_ERR_MSG = "0" + WS_ERR_MSG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_ERR_MSG);
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_END() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_ERR_MSG);
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        if (BPCUMENT.PROC_DATA.FUNC_CODE.equalsIgnoreCase("A) BPCUMENT.PROC_DATA.FUNC_CODE.equalsIgnoreCase(") 
            || BPCUMENT.PROC_DATA.FUNC_CODE.equalsIgnoreCase("D1") 
            || BPCUMENT.PROC_DATA.FUNC_CODE.equalsIgnoreCase("D2")) {
            S000_01_CALL_HIS_PROC();
            if (pgmRtn) return;
        }
        if (BPCUMENT.PROC_DATA.FUNC_CODE.equalsIgnoreCase("M1")) {
            S000_02_CALL_HIS_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void S000_01_CALL_HIS_PROC() throws IOException,SQLException,Exception {
        if (BPCUMENT.PROC_DATA.FUNC_CODE.equalsIgnoreCase("A) BPCUMENT.PROC_DATA.FUNC_CODE.equalsIgnoreCase(")) {
            BPCPNHIS.INFO.TX_TYP = 'A';
        } else {
            BPCPNHIS.INFO.TX_TYP = 'D';
        }
        BPCPNHIS.INFO.FMT_ID = "BPRDIARY";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        BPCPNHIS.INFO.REF_NO = "" + BPCUMENT.DIARY_DATA.KEY.SEQ;
        JIBS_tmp_int = BPCPNHIS.INFO.REF_NO.length();
        for (int i=0;i<15-JIBS_tmp_int;i++) BPCPNHIS.INFO.REF_NO = "0" + BPCPNHIS.INFO.REF_NO;
        BPCPNHIS.INFO.TX_TYP_CD = "BPUMENT";
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_TYP_CD);
        BPCPNHIS.INFO.TX_RMK = BP_HIS_REMARKS;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_02_CALL_HIS_PROCESS() throws IOException,SQLException,Exception {
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.FMT_ID = "BPRDIARY";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        BPCPNHIS.INFO.REF_NO = "" + BPCUMENT.DIARY_DATA.KEY.SEQ;
        JIBS_tmp_int = BPCPNHIS.INFO.REF_NO.length();
        for (int i=0;i<15-JIBS_tmp_int;i++) BPCPNHIS.INFO.REF_NO = "0" + BPCPNHIS.INFO.REF_NO;
        BPCPNHIS.INFO.TX_TYP_CD = "BPUMENT";
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_TYP_CD);
        BPCPNHIS.INFO.TX_RMK = BP_HIS_REMARKS;
        BPCPNHIS.INFO.OLD_DAT_PT = BPRBDIAR;
        BPCPNHIS.INFO.NEW_DAT_PT = BPRDIARY;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUMENT.PROC_DATA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-GET-SEQ", BPCSGSEQ);
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCUMENT.PROC_DATA.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "BPCUMENT = ");
            CEP.TRC(SCCGWA, BPCUMENT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
