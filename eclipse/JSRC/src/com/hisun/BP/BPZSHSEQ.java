package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSHSEQ {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String CPN_R_HSEQ_MAINT = "BP-R-HSEQ-MAINT";
    String WS_ERR_MSG = " ";
    short WS_BRANCH = 0;
    BPZSHSEQ_WS_HSEQ_INFO WS_HSEQ_INFO = new BPZSHSEQ_WS_HSEQ_INFO();
    BPZSHSEQ_WS_HSEQ_HEAD_DATA WS_HSEQ_HEAD_DATA = new BPZSHSEQ_WS_HSEQ_HEAD_DATA();
    BPZSHSEQ_WS_INQ_CINO_DATA WS_INQ_CINO_DATA = new BPZSHSEQ_WS_INQ_CINO_DATA();
    BPZSHSEQ_WS_INQ_ACNO_DATA WS_INQ_ACNO_DATA = new BPZSHSEQ_WS_INQ_ACNO_DATA();
    BPZSHSEQ_WS_INQ_CNTR_DATA WS_INQ_CNTR_DATA = new BPZSHSEQ_WS_INQ_CNTR_DATA();
    BPZSHSEQ_WS_COND_FLG WS_COND_FLG = new BPZSHSEQ_WS_COND_FLG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRHSEQ BPRHSEQ = new BPRHSEQ();
    BPCRHSEQ BPCRHSEQ = new BPCRHSEQ();
    BPCSMPTY BPCSMPTY = new BPCSMPTY();
    BPCSMPCD BPCSMPCD = new BPCSMPCD();
    BPCUQSEQ BPCUQSEQ = new BPCUQSEQ();
    BPCCKAC BPCCKAC = new BPCCKAC();
    CICCUST CICCUST = new CICCUST();
    SCCGWA SCCGWA;
    BPCSHSEQ BPCSHSEQ;
    public void MP(SCCGWA SCCGWA, BPCSHSEQ BPCSHSEQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSHSEQ = BPCSHSEQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSHSEQ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "INPUT DATA:");
        CEP.TRC(SCCGWA, BPCSHSEQ.FUNC_CODE);
        CEP.TRC(SCCGWA, BPCSHSEQ.TYPE);
        CEP.TRC(SCCGWA, BPCSHSEQ.CODE);
        CEP.TRC(SCCGWA, BPCSHSEQ.SEQ_NO);
        CEP.TRC(SCCGWA, BPCSHSEQ.CI_TYPE);
        CEP.TRC(SCCGWA, BPCSHSEQ.CI_NO);
        CEP.TRC(SCCGWA, BPCSHSEQ.AC_NO);
        CEP.TRC(SCCGWA, BPCSHSEQ.CT_NO);
        CEP.TRC(SCCGWA, BPCSHSEQ.USED_FLAG);
        CEP.TRC(SCCGWA, BPCSHSEQ.USED_DATE);
        CEP.TRC(SCCGWA, BPCSHSEQ.CI_NAME);
        CEP.TRC(SCCGWA, BPCSHSEQ.AC_OFFICER);
        CEP.TRC(SCCGWA, BPCSHSEQ.AC_ACCT);
        CEP.TRC(SCCGWA, BPCSHSEQ.AC_DIGIT);
        CEP.TRC(SCCGWA, BPCSHSEQ.REMARK);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (BPCSHSEQ.FUNC_CODE == 'I') {
            B020_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSHSEQ.FUNC_CODE == 'A') {
            B030_CREATE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSHSEQ.FUNC_CODE == 'U') {
            B040_MODIFY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSHSEQ.FUNC_CODE == 'D') {
            B050_DELETE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSHSEQ.FUNC_CODE == 'B') {
            B060_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSHSEQ.FUNC_CODE == 'U' 
            || BPCSHSEQ.FUNC_CODE == 'D') {
            B070_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCSHSEQ.FUNC_CODE != 'B' 
            && BPCSHSEQ.TYPE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_HSEQ_TYPE_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSHSEQ.FUNC_CODE != 'B' 
            && BPCSHSEQ.CODE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_HSEQ_CODE_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSHSEQ.FUNC_CODE != 'B' 
            && BPCSHSEQ.SEQ_NO == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_HSEQ_SEQ_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSHSEQ.FUNC_CODE == 'A') {
            if (BPCSHSEQ.TYPE.equalsIgnoreCase("CINO")) {
                IBS.init(SCCGWA, CICCUST);
                CICCUST.DATA.CI_NO = BPCSHSEQ.CI_NO;
                CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
                S000_CALL_CIZCUST();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, CICCUST.RC);
                if (CICCUST.RC.RC_CODE == 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CINO_IS_USED;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            } else if (BPCSHSEQ.TYPE.equalsIgnoreCase("ACNO")) {
                IBS.init(SCCGWA, BPCCKAC);
                BPCCKAC.ACNO = BPCSHSEQ.AC_NO;
                S000_CALL_BPZQACNO();
                if (pgmRtn) return;
                if (BPCCKAC.RC.RC_CODE == 0) {
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            } else if (BPCSHSEQ.TYPE.equalsIgnoreCase("CTNO")) {
            } else {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_QUERY_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSHSEQ.TYPE);
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (BPCSHSEQ.TYPE.equalsIgnoreCase("CINO")) {
            B020_01_QUERY_CINO_PROC();
            if (pgmRtn) return;
        } else if (BPCSHSEQ.TYPE.equalsIgnoreCase("ACNO")) {
            B020_02_QUERY_ACNO_PROC();
            if (pgmRtn) return;
        } else if (BPCSHSEQ.TYPE.equalsIgnoreCase("CTNO")) {
            B020_03_QUERY_CTNO_PROC();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_01_QUERY_CINO_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSMPTY);
        BPCSMPTY.TYPE = BPCSHSEQ.TYPE;
        BPCSMPTY.FUNC = 'Q';
        BPCSMPTY.OUTPUT_FLG = 'N';
        S000_CALL_BPZSMPTY();
        if (pgmRtn) return;
        WS_INQ_CINO_DATA.WS_INQ_CINO_TYPE = BPCSMPTY.TYPE;
        IBS.init(SCCGWA, BPCSMPCD);
        BPCSMPCD.TYPE = BPCSHSEQ.TYPE;
        BPCSMPCD.CODE = BPCSHSEQ.CODE;
        BPCSMPCD.FUNC = 'Q';
        BPCSMPCD.OUTPUT_FLG = 'N';
        S000_CALL_BPZSMPCD();
        if (pgmRtn) return;
        WS_INQ_CINO_DATA.WS_INQ_CINO_CODE = BPCSMPCD.CODE;
        IBS.init(SCCGWA, BPCUQSEQ);
        BPCUQSEQ.SEQ_TYPE = BPCSHSEQ.TYPE;
        BPCUQSEQ.SEQ_CODE = BPCSHSEQ.CODE;
        S000_CALL_BPZUQSEQ();
        if (pgmRtn) return;
        WS_INQ_CINO_DATA.WS_INQ_CINO_SEQ_DESC = BPCUQSEQ.SEQ_DESC;
        IBS.init(SCCGWA, BPRHSEQ);
        IBS.init(SCCGWA, BPCRHSEQ);
        BPCRHSEQ.INFO.FUNC = 'I';
        BPRHSEQ.KEY.TYPE = BPCSHSEQ.TYPE;
        BPRHSEQ.KEY.CODE = BPCSHSEQ.CODE;
        BPRHSEQ.KEY.SEQ_NO = BPCSHSEQ.SEQ_NO;
        BPCRHSEQ.INFO.POINTER = BPRHSEQ;
        BPCRHSEQ.LEN = 558;
        S000_CALL_BPZRHSEQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRHSEQ.KEY.TYPE);
        CEP.TRC(SCCGWA, BPRHSEQ.KEY.CODE);
        CEP.TRC(SCCGWA, BPRHSEQ.KEY.SEQ_NO);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRHSEQ.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase("0")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_HSEQ_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_INQ_CINO_DATA.WS_INQ_CINO_SEQ_NO = BPRHSEQ.KEY.SEQ_NO;
        WS_INQ_CINO_DATA.WS_INQ_CINO_CI_NO = BPRHSEQ.CI_NO;
        WS_INQ_CINO_DATA.WS_INQ_CINO_CI_NM = BPRHSEQ.CI_NAME;
        if (BPRHSEQ.CI_TYPE == 'P') {
            WS_INQ_CINO_DATA.WS_INQ_CINO_CI_TYPE = "91";
        }
        if (BPRHSEQ.CI_TYPE == 'C') {
            WS_INQ_CINO_DATA.WS_INQ_CINO_CI_TYPE = "96";
        }
        WS_INQ_CINO_DATA.WS_INQ_CINO_AC_OFC = BPRHSEQ.AC_OFFICER;
        WS_INQ_CINO_DATA.WS_INQ_CINO_AC_ACCT = BPRHSEQ.ACCT;
        WS_INQ_CINO_DATA.WS_INQ_CINO_REMARK = BPRHSEQ.REMARK;
        CEP.TRC(SCCGWA, "==============");
        CEP.TRC(SCCGWA, WS_INQ_CINO_DATA.WS_INQ_CINO_TYPE);
        CEP.TRC(SCCGWA, WS_INQ_CINO_DATA.WS_INQ_CINO_CODE);
        CEP.TRC(SCCGWA, WS_INQ_CINO_DATA.WS_INQ_CINO_SEQ_DESC);
        CEP.TRC(SCCGWA, WS_INQ_CINO_DATA.WS_INQ_CINO_SEQ_NO);
        CEP.TRC(SCCGWA, WS_INQ_CINO_DATA.WS_INQ_CINO_CI_NO);
        CEP.TRC(SCCGWA, WS_INQ_CINO_DATA.WS_INQ_CINO_CI_NM);
        CEP.TRC(SCCGWA, WS_INQ_CINO_DATA.WS_INQ_CINO_CI_TYPE);
        CEP.TRC(SCCGWA, WS_INQ_CINO_DATA.WS_INQ_CINO_AC_OFC);
        CEP.TRC(SCCGWA, WS_INQ_CINO_DATA.WS_INQ_CINO_AC_ACCT);
        CEP.TRC(SCCGWA, WS_INQ_CINO_DATA.WS_INQ_CINO_REMARK);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "BP571";
        SCCFMT.DATA_PTR = WS_INQ_CINO_DATA;
        SCCFMT.DATA_LEN = 499;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B020_02_QUERY_ACNO_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSMPTY);
        BPCSMPTY.TYPE = BPCSHSEQ.TYPE;
        BPCSMPTY.FUNC = 'Q';
        BPCSMPTY.OUTPUT_FLG = 'N';
        S000_CALL_BPZSMPTY();
        if (pgmRtn) return;
        WS_INQ_ACNO_DATA.WS_INQ_ACNO_TYPE = BPCSMPTY.TYPE;
        WS_INQ_ACNO_DATA.WS_INQ_ACNO_TYPE_DESC = BPCSMPTY.INFO.NAME;
        IBS.init(SCCGWA, BPCSMPCD);
        BPCSMPCD.TYPE = BPCSHSEQ.TYPE;
        BPCSMPCD.CODE = BPCSHSEQ.CODE;
        BPCSMPCD.FUNC = 'Q';
        BPCSMPCD.OUTPUT_FLG = 'N';
        S000_CALL_BPZSMPCD();
        if (pgmRtn) return;
        WS_INQ_ACNO_DATA.WS_INQ_ACNO_CODE = BPCSMPCD.CODE;
        WS_INQ_ACNO_DATA.WS_INQ_ACNO_CODE_DESC = BPCSMPCD.INFO.CODE_NAME;
        IBS.init(SCCGWA, BPCUQSEQ);
        BPCUQSEQ.SEQ_TYPE = BPCSHSEQ.TYPE;
        BPCUQSEQ.SEQ_CODE = BPCSHSEQ.CODE;
        S000_CALL_BPZUQSEQ();
        if (pgmRtn) return;
        WS_INQ_ACNO_DATA.WS_INQ_ACNO_SEQ_DESC = BPCUQSEQ.SEQ_DESC;
        IBS.init(SCCGWA, BPRHSEQ);
        IBS.init(SCCGWA, BPCRHSEQ);
        BPCRHSEQ.INFO.FUNC = 'I';
        BPRHSEQ.KEY.TYPE = BPCSHSEQ.TYPE;
        BPRHSEQ.KEY.CODE = BPCSHSEQ.CODE;
        BPRHSEQ.KEY.SEQ_NO = BPCSHSEQ.SEQ_NO;
        BPCRHSEQ.INFO.POINTER = BPRHSEQ;
        BPCRHSEQ.LEN = 558;
        S000_CALL_BPZRHSEQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRHSEQ.KEY.TYPE);
        CEP.TRC(SCCGWA, BPRHSEQ.KEY.CODE);
        CEP.TRC(SCCGWA, BPRHSEQ.KEY.SEQ_NO);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRHSEQ.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase("0")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_HSEQ_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_INQ_ACNO_DATA.WS_INQ_ACNO_SEQ_NO = BPRHSEQ.KEY.SEQ_NO;
        WS_INQ_ACNO_DATA.WS_INQ_ACNO_AC_NO = BPRHSEQ.AC_NO;
        WS_INQ_ACNO_DATA.WS_INQ_ACNO_AC_OFC = BPRHSEQ.AC_OFFICER;
        WS_INQ_ACNO_DATA.WS_INQ_ACNO_REMARK = BPRHSEQ.REMARK;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 551;
        SCCMPAG.SCR_ROW_CNT = 50;
        SCCMPAG.SCR_COL_CNT = 100;
        B_MPAG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_INQ_ACNO_DATA);
        SCCMPAG.DATA_LEN = 551;
        CEP.TRC(SCCGWA, "==============");
        CEP.TRC(SCCGWA, WS_INQ_ACNO_DATA.WS_INQ_ACNO_TYPE);
        CEP.TRC(SCCGWA, WS_INQ_ACNO_DATA.WS_INQ_ACNO_TYPE_DESC);
        CEP.TRC(SCCGWA, WS_INQ_ACNO_DATA.WS_INQ_ACNO_CODE);
        CEP.TRC(SCCGWA, WS_INQ_ACNO_DATA.WS_INQ_ACNO_CODE_DESC);
        CEP.TRC(SCCGWA, WS_INQ_ACNO_DATA.WS_INQ_ACNO_SEQ_DESC);
        CEP.TRC(SCCGWA, WS_INQ_ACNO_DATA.WS_INQ_ACNO_SEQ_NO);
        CEP.TRC(SCCGWA, WS_INQ_ACNO_DATA.WS_INQ_ACNO_AC_NO);
        CEP.TRC(SCCGWA, WS_INQ_ACNO_DATA.WS_INQ_ACNO_AC_OFC);
        CEP.TRC(SCCGWA, WS_INQ_ACNO_DATA.WS_INQ_ACNO_REMARK);
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B020_03_QUERY_CTNO_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSMPTY);
        BPCSMPTY.TYPE = BPCSHSEQ.TYPE;
        BPCSMPTY.FUNC = 'Q';
        BPCSMPTY.OUTPUT_FLG = 'N';
        S000_CALL_BPZSMPTY();
        if (pgmRtn) return;
        WS_INQ_CNTR_DATA.WS_INQ_CNTR_TYPE = BPCSMPTY.TYPE;
        WS_INQ_CNTR_DATA.WS_INQ_CNTR_TYPE_DESC = BPCSMPTY.INFO.NAME;
        IBS.init(SCCGWA, BPCSMPCD);
        BPCSMPCD.TYPE = BPCSHSEQ.TYPE;
        BPCSMPCD.CODE = BPCSHSEQ.CODE;
        BPCSMPCD.FUNC = 'Q';
        BPCSMPCD.OUTPUT_FLG = 'N';
        S000_CALL_BPZSMPCD();
        if (pgmRtn) return;
        WS_INQ_CNTR_DATA.WS_INQ_CNTR_CODE = BPCSMPCD.CODE;
        WS_INQ_CNTR_DATA.WS_INQ_CNTR_CODE_DESC = BPCSMPCD.INFO.CODE_NAME;
        IBS.init(SCCGWA, BPCUQSEQ);
        BPCUQSEQ.SEQ_TYPE = BPCSHSEQ.TYPE;
        BPCUQSEQ.SEQ_CODE = BPCSHSEQ.CODE;
        S000_CALL_BPZUQSEQ();
        if (pgmRtn) return;
        WS_INQ_CNTR_DATA.WS_INQ_CNTR_SEQ_DESC = BPCUQSEQ.SEQ_DESC;
        IBS.init(SCCGWA, BPRHSEQ);
        IBS.init(SCCGWA, BPCRHSEQ);
        BPCRHSEQ.INFO.FUNC = 'I';
        BPRHSEQ.KEY.TYPE = BPCSHSEQ.TYPE;
        BPRHSEQ.KEY.CODE = BPCSHSEQ.CODE;
        BPRHSEQ.KEY.SEQ_NO = BPCSHSEQ.SEQ_NO;
        BPCRHSEQ.INFO.POINTER = BPRHSEQ;
        BPCRHSEQ.LEN = 558;
        S000_CALL_BPZRHSEQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRHSEQ.KEY.TYPE);
        CEP.TRC(SCCGWA, BPRHSEQ.KEY.CODE);
        CEP.TRC(SCCGWA, BPRHSEQ.KEY.SEQ_NO);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRHSEQ.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase("0")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_HSEQ_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_INQ_CNTR_DATA.WS_INQ_CNTR_SEQ_NO = BPRHSEQ.KEY.SEQ_NO;
        WS_INQ_CNTR_DATA.WS_INQ_CNTR_CT_NO = BPRHSEQ.CT_NO;
        WS_INQ_CNTR_DATA.WS_INQ_CNTR_AC_OFC = BPRHSEQ.AC_OFFICER;
        WS_INQ_CNTR_DATA.WS_INQ_CNTR_REMARK = BPRHSEQ.REMARK;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 544;
        SCCMPAG.SCR_ROW_CNT = 50;
        SCCMPAG.SCR_COL_CNT = 100;
        B_MPAG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_INQ_CNTR_DATA);
        SCCMPAG.DATA_LEN = 544;
        CEP.TRC(SCCGWA, "==============");
        CEP.TRC(SCCGWA, WS_INQ_CNTR_DATA.WS_INQ_CNTR_TYPE);
        CEP.TRC(SCCGWA, WS_INQ_CNTR_DATA.WS_INQ_CNTR_TYPE_DESC);
        CEP.TRC(SCCGWA, WS_INQ_CNTR_DATA.WS_INQ_CNTR_CODE);
        CEP.TRC(SCCGWA, WS_INQ_CNTR_DATA.WS_INQ_CNTR_CODE_DESC);
        CEP.TRC(SCCGWA, WS_INQ_CNTR_DATA.WS_INQ_CNTR_SEQ_DESC);
        CEP.TRC(SCCGWA, WS_INQ_CNTR_DATA.WS_INQ_CNTR_SEQ_NO);
        CEP.TRC(SCCGWA, WS_INQ_CNTR_DATA.WS_INQ_CNTR_CT_NO);
        CEP.TRC(SCCGWA, WS_INQ_CNTR_DATA.WS_INQ_CNTR_AC_OFC);
        CEP.TRC(SCCGWA, WS_INQ_CNTR_DATA.WS_INQ_CNTR_REMARK);
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B030_CREATE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRHSEQ);
        IBS.init(SCCGWA, BPCRHSEQ);
        BPCRHSEQ.INFO.FUNC = 'A';
        R000_TRANS_DATA_ADD();
        if (pgmRtn) return;
        BPCRHSEQ.LEN = 558;
        BPCRHSEQ.INFO.POINTER = BPRHSEQ;
        S000_CALL_BPZRHSEQ();
        if (pgmRtn) return;
    }
    public void B040_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRHSEQ);
        IBS.init(SCCGWA, BPCRHSEQ);
        BPCRHSEQ.INFO.FUNC = 'R';
        BPRHSEQ.KEY.TYPE = BPCSHSEQ.TYPE;
        BPRHSEQ.KEY.CODE = BPCSHSEQ.CODE;
        BPRHSEQ.KEY.SEQ_NO = BPCSHSEQ.SEQ_NO;
        BPCRHSEQ.LEN = 558;
        BPCRHSEQ.INFO.POINTER = BPRHSEQ;
        S000_CALL_BPZRHSEQ();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCRHSEQ);
        R000_TRANS_DATA_MOD();
        if (pgmRtn) return;
        BPCRHSEQ.INFO.FUNC = 'U';
        BPCRHSEQ.LEN = 558;
        BPCRHSEQ.INFO.POINTER = BPRHSEQ;
        S000_CALL_BPZRHSEQ();
        if (pgmRtn) return;
    }
    public void B050_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRHSEQ);
        IBS.init(SCCGWA, BPCRHSEQ);
        BPCRHSEQ.INFO.FUNC = 'I';
        BPRHSEQ.KEY.TYPE = BPCSHSEQ.TYPE;
        BPRHSEQ.KEY.CODE = BPCSHSEQ.CODE;
        BPRHSEQ.KEY.SEQ_NO = BPCSHSEQ.SEQ_NO;
        BPCRHSEQ.LEN = 558;
        BPCRHSEQ.INFO.POINTER = BPRHSEQ;
        S000_CALL_BPZRHSEQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRHSEQ.USED_FLAG);
        if (BPRHSEQ.USED_FLAG == 'Y') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CINO_IS_USED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPCRHSEQ.INFO.FUNC = 'R';
        BPRHSEQ.KEY.TYPE = BPCSHSEQ.TYPE;
        BPRHSEQ.KEY.CODE = BPCSHSEQ.CODE;
        BPRHSEQ.KEY.SEQ_NO = BPCSHSEQ.SEQ_NO;
        BPCRHSEQ.LEN = 558;
        BPCRHSEQ.INFO.POINTER = BPRHSEQ;
        S000_CALL_BPZRHSEQ();
        if (pgmRtn) return;
        BPCRHSEQ.INFO.FUNC = 'D';
        BPRHSEQ.KEY.TYPE = BPCSHSEQ.TYPE;
        BPRHSEQ.KEY.CODE = BPCSHSEQ.CODE;
        BPRHSEQ.KEY.SEQ_NO = BPCSHSEQ.SEQ_NO;
        BPCRHSEQ.LEN = 558;
        BPCRHSEQ.INFO.POINTER = BPRHSEQ;
        S000_CALL_BPZRHSEQ();
        if (pgmRtn) return;
    }
    public void B060_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 444;
        SCCMPAG.SCR_ROW_CNT = 35;
        SCCMPAG.SCR_COL_CNT = 100;
        B_MPAG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCSMPTY);
        BPCSMPTY.TYPE = BPCSHSEQ.TYPE;
        BPCSMPTY.FUNC = 'Q';
        BPCSMPTY.OUTPUT_FLG = 'N';
        CEP.TRC(SCCGWA, BPCSMPTY.TYPE);
        S000_CALL_BPZSMPTY();
        if (pgmRtn) return;
        WS_HSEQ_HEAD_DATA.WS_H_HSEQ_TYPE = BPCSMPTY.TYPE;
        WS_HSEQ_HEAD_DATA.WS_H_HSEQ_TYPE_DESC = BPCSMPTY.INFO.NAME;
        CEP.TRC(SCCGWA, BPCSMPTY.INFO.NAME);
        CEP.TRC(SCCGWA, BPCSMPTY.INFO.CHG_NAME);
        CEP.TRC(SCCGWA, BPCSMPTY.INFO.REMARKS);
        IBS.init(SCCGWA, BPCSMPCD);
        BPCSMPCD.TYPE = BPCSHSEQ.TYPE;
        BPCSMPCD.CODE = BPCSHSEQ.CODE;
        BPCSMPCD.FUNC = 'Q';
        BPCSMPCD.OUTPUT_FLG = 'N';
        S000_CALL_BPZSMPCD();
        if (pgmRtn) return;
        WS_HSEQ_HEAD_DATA.WS_H_HSEQ_CODE = BPCSMPCD.CODE;
        WS_HSEQ_HEAD_DATA.WS_H_HSEQ_CODE_DESC = BPCSMPCD.INFO.CODE_NAME;
        CEP.TRC(SCCGWA, BPCSMPCD.TYPE_NAME);
        CEP.TRC(SCCGWA, BPCSMPCD.INFO.CODE_NAME);
        CEP.TRC(SCCGWA, BPCSMPCD.INFO.CODE_NAME_S);
        IBS.init(SCCGWA, BPCUQSEQ);
        BPCUQSEQ.SEQ_TYPE = BPCSHSEQ.TYPE;
        BPCUQSEQ.SEQ_CODE = BPCSHSEQ.CODE;
        S000_CALL_BPZUQSEQ();
        if (pgmRtn) return;
        WS_HSEQ_HEAD_DATA.WS_H_HSEQ_DESC = BPCUQSEQ.SEQ_DESC;
        CEP.TRC(SCCGWA, BPCUQSEQ.SEQ_DESC);
        CEP.TRC(SCCGWA, BPCUQSEQ.SEQ_CODE);
        CEP.TRC(SCCGWA, BPCUQSEQ.SEQ_NO);
        if (BPCUQSEQ.SEQ_CODE == null) BPCUQSEQ.SEQ_CODE = "";
        JIBS_tmp_int = BPCUQSEQ.SEQ_CODE.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) BPCUQSEQ.SEQ_CODE += " ";
        if (WS_HSEQ_HEAD_DATA.WS_H_PRE_CINO == null) WS_HSEQ_HEAD_DATA.WS_H_PRE_CINO = "";
        JIBS_tmp_int = WS_HSEQ_HEAD_DATA.WS_H_PRE_CINO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) WS_HSEQ_HEAD_DATA.WS_H_PRE_CINO += " ";
        WS_HSEQ_HEAD_DATA.WS_H_PRE_CINO = BPCUQSEQ.SEQ_CODE.substring(0, 3) + WS_HSEQ_HEAD_DATA.WS_H_PRE_CINO.substring(3);
        JIBS_tmp_str[0] = "" + BPCUQSEQ.SEQ_NO;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (WS_HSEQ_HEAD_DATA.WS_H_PRE_CINO == null) WS_HSEQ_HEAD_DATA.WS_H_PRE_CINO = "";
        JIBS_tmp_int = WS_HSEQ_HEAD_DATA.WS_H_PRE_CINO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) WS_HSEQ_HEAD_DATA.WS_H_PRE_CINO += " ";
        WS_HSEQ_HEAD_DATA.WS_H_PRE_CINO = WS_HSEQ_HEAD_DATA.WS_H_PRE_CINO.substring(0, 4 - 1) + JIBS_tmp_str[0].substring(11 - 1, 11 + 5 - 1) + WS_HSEQ_HEAD_DATA.WS_H_PRE_CINO.substring(4 + 5 - 1);
        CEP.TRC(SCCGWA, WS_HSEQ_HEAD_DATA.WS_H_PRE_CINO);
        IBS.init(SCCGWA, BPRHSEQ);
        IBS.init(SCCGWA, BPCRHSEQ);
        BPCRHSEQ.INFO.FUNC = 'B';
        BPCRHSEQ.INFO.OPT = 'S';
        BPRHSEQ.KEY.TYPE = BPCSHSEQ.TYPE;
        BPRHSEQ.KEY.CODE = BPCSHSEQ.CODE;
        CEP.TRC(SCCGWA, BPCSHSEQ.AC_OFFICER);
        if (BPCSHSEQ.AC_OFFICER.trim().length() == 0) {
            BPRHSEQ.AC_OFFICER = "%%%%%%%%";
        } else {
            BPRHSEQ.AC_OFFICER = BPCSHSEQ.AC_OFFICER;
        }
        CEP.TRC(SCCGWA, BPRHSEQ.AC_OFFICER);
        CEP.TRC(SCCGWA, BPCSHSEQ.AC_ACCT);
        if (BPCSHSEQ.AC_ACCT == 0) {
            BPRHSEQ.ACCT = 0;
        } else {
            BPRHSEQ.ACCT = BPCSHSEQ.AC_ACCT;
        }
        CEP.TRC(SCCGWA, BPRHSEQ.ACCT);
        BPCRHSEQ.INFO.POINTER = BPRHSEQ;
        BPCRHSEQ.LEN = 558;
        S000_CALL_BPZRHSEQ();
        if (pgmRtn) return;
        while (BPCRHSEQ.RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E') {
            BPCRHSEQ.INFO.OPT = 'N';
            S000_CALL_BPZRHSEQ();
            if (pgmRtn) return;
            if (BPCRHSEQ.RETURN_INFO != 'N') {
                WS_HSEQ_INFO.WS_HSEQ_TYPE = BPRHSEQ.KEY.TYPE;
                WS_HSEQ_INFO.WS_HSEQ_CODE = BPRHSEQ.KEY.CODE;
                WS_HSEQ_INFO.WS_HSEQ_SEQ_NO = BPRHSEQ.KEY.SEQ_NO;
                WS_HSEQ_INFO.WS_HSEQ_CI_TYPE = BPRHSEQ.CI_TYPE;
                WS_HSEQ_INFO.WS_HSEQ_CI_NO = BPRHSEQ.CI_NO;
                WS_HSEQ_INFO.WS_HSEQ_AC_NO = BPRHSEQ.AC_NO;
                WS_HSEQ_INFO.WS_HSEQ_CT_NO = BPRHSEQ.CT_NO;
                WS_HSEQ_INFO.WS_HSEQ_USED_FLAG = BPRHSEQ.USED_FLAG;
                WS_HSEQ_INFO.WS_HSEQ_USED_DATE = BPRHSEQ.USED_DATE;
                WS_HSEQ_INFO.WS_HSEQ_CI_NAME = BPRHSEQ.CI_NAME;
                WS_HSEQ_INFO.WS_HSEQ_AC_OFFICER = BPRHSEQ.AC_OFFICER;
                WS_HSEQ_INFO.WS_HSEQ_AC_DIGIT = BPRHSEQ.AC_DIGIT;
                WS_HSEQ_INFO.WS_HSEQ_REMARK = BPRHSEQ.REMARK;
                WS_HSEQ_INFO.WS_HSEQ_AC_ACCT = BPRHSEQ.ACCT;
                CEP.TRC(SCCGWA, "XXXXXXXXXXX");
                CEP.TRC(SCCGWA, WS_HSEQ_INFO.WS_HSEQ_TYPE);
                CEP.TRC(SCCGWA, WS_HSEQ_INFO.WS_HSEQ_CODE);
                CEP.TRC(SCCGWA, WS_HSEQ_INFO.WS_HSEQ_SEQ_NO);
                CEP.TRC(SCCGWA, WS_HSEQ_INFO.WS_HSEQ_CI_TYPE);
                CEP.TRC(SCCGWA, WS_HSEQ_INFO.WS_HSEQ_CI_NO);
                CEP.TRC(SCCGWA, WS_HSEQ_INFO.WS_HSEQ_AC_NO);
                CEP.TRC(SCCGWA, WS_HSEQ_INFO.WS_HSEQ_CT_NO);
                CEP.TRC(SCCGWA, WS_HSEQ_INFO.WS_HSEQ_USED_FLAG);
                CEP.TRC(SCCGWA, WS_HSEQ_INFO.WS_HSEQ_USED_DATE);
                CEP.TRC(SCCGWA, WS_HSEQ_INFO.WS_HSEQ_CI_NAME);
                CEP.TRC(SCCGWA, WS_HSEQ_INFO.WS_HSEQ_AC_OFFICER);
                CEP.TRC(SCCGWA, WS_HSEQ_INFO.WS_HSEQ_AC_ACCT);
                CEP.TRC(SCCGWA, WS_HSEQ_INFO.WS_HSEQ_AC_DIGIT);
                CEP.TRC(SCCGWA, WS_HSEQ_INFO.WS_HSEQ_REMARK);
                if (BPRHSEQ.KEY.TYPE.equalsIgnoreCase(BPCSHSEQ.TYPE) 
                    && BPRHSEQ.KEY.CODE.equalsIgnoreCase(BPCSHSEQ.CODE)) {
                    B060_02_OUT_HSEQ_INFO();
                    if (pgmRtn) return;
                }
            }
        }
        BPCRHSEQ.INFO.OPT = 'E';
        S000_CALL_BPZRHSEQ();
        if (pgmRtn) return;
    }
    public void B070_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        WS_HSEQ_INFO.WS_HSEQ_TYPE = BPRHSEQ.KEY.TYPE;
        WS_HSEQ_INFO.WS_HSEQ_CODE = BPRHSEQ.KEY.CODE;
        WS_HSEQ_INFO.WS_HSEQ_SEQ_NO = BPRHSEQ.KEY.SEQ_NO;
        if (BPRHSEQ.CI_TYPE == 'P') {
            WS_HSEQ_INFO.WS_HSEQ_CI_TYPE = "91".charAt(0);
        }
        if (BPRHSEQ.CI_TYPE == 'C') {
            WS_HSEQ_INFO.WS_HSEQ_CI_TYPE = "96".charAt(0);
        }
        WS_HSEQ_INFO.WS_HSEQ_CI_NO = BPRHSEQ.CI_NO;
        WS_HSEQ_INFO.WS_HSEQ_AC_NO = BPRHSEQ.AC_NO;
        WS_HSEQ_INFO.WS_HSEQ_CT_NO = BPRHSEQ.CT_NO;
        WS_HSEQ_INFO.WS_HSEQ_USED_FLAG = BPRHSEQ.USED_FLAG;
        WS_HSEQ_INFO.WS_HSEQ_USED_DATE = BPRHSEQ.USED_DATE;
        WS_HSEQ_INFO.WS_HSEQ_CI_NAME = BPRHSEQ.CI_NAME;
        WS_HSEQ_INFO.WS_HSEQ_AC_OFFICER = BPRHSEQ.AC_OFFICER;
        WS_HSEQ_INFO.WS_HSEQ_AC_ACCT = BPRHSEQ.ACCT;
        WS_HSEQ_INFO.WS_HSEQ_AC_DIGIT = BPRHSEQ.AC_DIGIT;
        WS_HSEQ_INFO.WS_HSEQ_REMARK = BPRHSEQ.REMARK;
        IBS.init(SCCGWA, SCCFMT);
        if (BPCSHSEQ.TYPE.equalsIgnoreCase("CINO")) {
            SCCFMT.FMTID = "BP565";
        } else if (BPCSHSEQ.TYPE.equalsIgnoreCase("ACNO")) {
            SCCFMT.FMTID = "BP566";
        } else if (BPCSHSEQ.TYPE.equalsIgnoreCase("CTNO")) {
            SCCFMT.FMTID = "BP567";
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        SCCFMT.DATA_PTR = WS_HSEQ_INFO;
        SCCFMT.DATA_LEN = 444;
        CEP.TRC(SCCGWA, WS_HSEQ_INFO.WS_HSEQ_TYPE);
        CEP.TRC(SCCGWA, WS_HSEQ_INFO.WS_HSEQ_CODE);
        CEP.TRC(SCCGWA, WS_HSEQ_INFO.WS_HSEQ_SEQ_NO);
        CEP.TRC(SCCGWA, WS_HSEQ_INFO.WS_HSEQ_CI_TYPE);
        CEP.TRC(SCCGWA, WS_HSEQ_INFO.WS_HSEQ_CI_NO);
        CEP.TRC(SCCGWA, WS_HSEQ_INFO.WS_HSEQ_AC_NO);
        CEP.TRC(SCCGWA, WS_HSEQ_INFO.WS_HSEQ_CT_NO);
        CEP.TRC(SCCGWA, WS_HSEQ_INFO.WS_HSEQ_USED_FLAG);
        CEP.TRC(SCCGWA, WS_HSEQ_INFO.WS_HSEQ_USED_DATE);
        CEP.TRC(SCCGWA, WS_HSEQ_INFO.WS_HSEQ_CI_NAME);
        CEP.TRC(SCCGWA, WS_HSEQ_INFO.WS_HSEQ_AC_OFFICER);
        CEP.TRC(SCCGWA, WS_HSEQ_INFO.WS_HSEQ_AC_ACCT);
        CEP.TRC(SCCGWA, WS_HSEQ_INFO.WS_HSEQ_AC_DIGIT);
        CEP.TRC(SCCGWA, WS_HSEQ_INFO.WS_HSEQ_REMARK);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B060_01_OUT_BRW_HEAD_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_LEN = 391;
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_HSEQ_HEAD_DATA);
        B_MPAG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_HSEQ_HEAD_DATA.WS_H_HSEQ_TYPE);
        CEP.TRC(SCCGWA, WS_HSEQ_HEAD_DATA.WS_H_HSEQ_TYPE_DESC);
        CEP.TRC(SCCGWA, WS_HSEQ_HEAD_DATA.WS_H_HSEQ_CODE);
        CEP.TRC(SCCGWA, WS_HSEQ_HEAD_DATA.WS_H_HSEQ_CODE_DESC);
        CEP.TRC(SCCGWA, WS_HSEQ_HEAD_DATA.WS_H_HSEQ_DESC);
        CEP.TRC(SCCGWA, WS_HSEQ_HEAD_DATA.WS_H_PRE_CINO);
    }
    public void R000_TRANS_DATA_ADD() throws IOException,SQLException,Exception {
        BPRHSEQ.KEY.TYPE = BPCSHSEQ.TYPE;
        BPRHSEQ.KEY.CODE = BPCSHSEQ.CODE;
        BPRHSEQ.KEY.SEQ_NO = BPCSHSEQ.SEQ_NO;
        BPRHSEQ.CI_TYPE = BPCSHSEQ.CI_TYPE;
        BPRHSEQ.CI_NO = BPCSHSEQ.CI_NO;
        BPRHSEQ.AC_NO = BPCSHSEQ.AC_NO;
        BPRHSEQ.CT_NO = BPCSHSEQ.CT_NO;
        BPRHSEQ.USED_FLAG = BPCSHSEQ.USED_FLAG;
        BPRHSEQ.CI_NAME = BPCSHSEQ.CI_NAME;
        BPRHSEQ.AC_OFFICER = BPCSHSEQ.AC_OFFICER;
        BPRHSEQ.ACCT = BPCSHSEQ.AC_ACCT;
        BPRHSEQ.AC_DIGIT = BPCSHSEQ.AC_DIGIT;
        BPRHSEQ.REMARK = BPCSHSEQ.REMARK;
    }
    public void R000_TRANS_DATA_MOD() throws IOException,SQLException,Exception {
        BPRHSEQ.KEY.TYPE = BPCSHSEQ.TYPE;
        BPRHSEQ.KEY.CODE = BPCSHSEQ.CODE;
        BPRHSEQ.KEY.SEQ_NO = BPCSHSEQ.SEQ_NO;
        BPRHSEQ.CI_TYPE = BPCSHSEQ.CI_TYPE;
        BPRHSEQ.CI_NO = BPCSHSEQ.CI_NO;
        BPRHSEQ.AC_NO = BPCSHSEQ.AC_NO;
        BPRHSEQ.CT_NO = BPCSHSEQ.CT_NO;
        BPRHSEQ.CI_NAME = BPCSHSEQ.CI_NAME;
        BPRHSEQ.AC_OFFICER = BPCSHSEQ.AC_OFFICER;
        BPRHSEQ.ACCT = BPCSHSEQ.AC_ACCT;
        BPRHSEQ.AC_DIGIT = BPCSHSEQ.AC_DIGIT;
        BPRHSEQ.REMARK = BPCSHSEQ.REMARK;
    }
    public void B060_02_OUT_HSEQ_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_HSEQ_INFO);
        SCCMPAG.DATA_LEN = 444;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZSMPTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-MAINT-PARM-TYPE", BPCSMPTY);
    }
    public void S000_CALL_BPZSMPCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-MAINT-PARM-CODE", BPCSMPCD);
    }
    public void S000_CALL_BPZUQSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-INQ-SEQ", BPCUQSEQ);
        if (BPCUQSEQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUQSEQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZQACNO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-CHECK-ACNO", BPCCKAC);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
    }
    public void S000_CALL_BPZRHSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-HSEQ-MAINT", BPCRHSEQ);
        if (BPCRHSEQ.RETURN_INFO == 'D') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCRHSEQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRHSEQ.RC);
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
