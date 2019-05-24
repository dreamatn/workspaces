package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4010 {
    int JIBS_tmp_int;
    String K_CMP_CAL_MAINTAIN = "BP-S-MAINT-ACCT     ";
    String K_CMP_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    String K_FMT_CD = "BPX01";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPOT4010_WS_FMT_DATA WS_FMT_DATA = new BPOT4010_WS_FMT_DATA();
    int WS_RESP = 0;
    String WS_WORD = " ";
    char WS_FUNC_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCSMACT BPCSMACT = new BPCSMACT();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCGWA SCCGWA;
    BPB4000_AWA_4000 BPB4000_AWA_4000;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4010 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4000_AWA_4000>");
        BPB4000_AWA_4000 = (BPB4000_AWA_4000) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_MAIN_PROCESS();
        B300_SET_RETURN_INFO();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB4000_AWA_4000.ACCT_CD);
        CEP.TRC(SCCGWA, BPB4000_AWA_4000.ACCT_NM);
        CEP.TRC(SCCGWA, BPB4000_AWA_4000.ACCT_OFF);
        CEP.TRC(SCCGWA, BPB4000_AWA_4000.ITEM_NO);
        CEP.TRC(SCCGWA, BPB4000_AWA_4000.ITEM_NM);
        CEP.TRC(SCCGWA, BPB4000_AWA_4000.EFF_DATE);
        CEP.TRC(SCCGWA, BPB4000_AWA_4000.EXP_DATE);
        CEP.TRC(SCCGWA, BPB4000_AWA_4000.RMK);
        WS_FUNC_FLG = BPB4000_AWA_4000.FUNC_CD;
        if ((WS_FUNC_FLG != 'B' 
            && WS_FUNC_FLG != 'A')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ACCT_FUNC_CD_ERR;
            WS_FLD_NO = BPB4000_AWA_4000.FUNC_CD_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (WS_FUNC_FLG == 'A' 
            || WS_FUNC_FLG == 'U' 
            || WS_FUNC_FLG == 'D') {
            if (BPB4000_AWA_4000.ACCT_CD == 0 
                || BPB4000_AWA_4000.ITEM_NO == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ACCT_MUST_INPUT_2;
                WS_FLD_NO = BPB4000_AWA_4000.ITEM_NO_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (BPB4000_AWA_4000.ACCT_OFF != 'Y' 
                && BPB4000_AWA_4000.ACCT_OFF != 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.ACCT_INPUT_ERR_1;
                WS_FLD_NO = BPB4000_AWA_4000.ACCT_OFF_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (WS_FUNC_FLG == 'Q') {
            if (BPB4000_AWA_4000.ACCT_CD == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ACCT_MUST_INPUT_2;
                WS_FLD_NO = BPB4000_AWA_4000.ACCT_CD_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (BPB4000_AWA_4000.ITEM_NO != 0) {
            }
        }
        if (WS_FUNC_FLG == 'A') {
            if (BPB4000_AWA_4000.EFF_DATE > BPB4000_AWA_4000.EXP_DATE) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.ACCT_VAL_DATE_ERR;
                WS_FLD_NO = BPB4000_AWA_4000.ACCT_OFF_NO;
                S000_ERR_MSG_PROC();
            }
            if (BPB4000_AWA_4000.EFF_DATE != 0) {
                IBS.init(SCCGWA, SCCCKDT);
                SCCCKDT.DATE = BPB4000_AWA_4000.EFF_DATE;
                SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
                SCSSCKDT1.MP(SCCGWA, SCCCKDT);
                if (SCCCKDT.RC != 0) {
                    WS_ERR_MSG = "" + SCCCKDT.RC;
                    JIBS_tmp_int = WS_ERR_MSG.length();
                    for (int i=0;i<4-JIBS_tmp_int;i++) WS_ERR_MSG = "0" + WS_ERR_MSG;
                    WS_FLD_NO = BPB4000_AWA_4000.EFF_DATE_NO;
                    S000_ERR_MSG_PROC();
                }
            }
            if (BPB4000_AWA_4000.EXP_DATE != 0) {
                IBS.init(SCCGWA, SCCCKDT);
                SCCCKDT.DATE = BPB4000_AWA_4000.EXP_DATE;
                SCSSCKDT SCSSCKDT2 = new SCSSCKDT();
                SCSSCKDT2.MP(SCCGWA, SCCCKDT);
                if (SCCCKDT.RC != 0) {
                    WS_ERR_MSG = "" + SCCCKDT.RC;
                    JIBS_tmp_int = WS_ERR_MSG.length();
                    for (int i=0;i<4-JIBS_tmp_int;i++) WS_ERR_MSG = "0" + WS_ERR_MSG;
                    WS_FLD_NO = BPB4000_AWA_4000.EXP_DATE_NO;
                    S000_ERR_MSG_PROC();
                }
            }
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.ACCT_INPUT_ERR_2;
        WS_FLD_NO = 0;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSMACT);
        BPCSMACT.ACCT_CD = BPB4000_AWA_4000.ACCT_CD;
        BPCSMACT.ACCT_NM = BPB4000_AWA_4000.ACCT_NM;
        BPCSMACT.INFO.ACCT_OFF = BPB4000_AWA_4000.ACCT_OFF;
        BPCSMACT.INFO.ITEM_NO = BPB4000_AWA_4000.ITEM_NO;
        BPCSMACT.INFO.ITEM_NM = BPB4000_AWA_4000.ITEM_NM;
        BPCSMACT.VAL_DATE = BPB4000_AWA_4000.EFF_DATE;
        BPCSMACT.EXP_DATE = BPB4000_AWA_4000.EXP_DATE;
        BPCSMACT.INFO.REMARK = BPB4000_AWA_4000.RMK;
        if (WS_FUNC_FLG == 'A'
            || WS_FUNC_FLG == 'Q'
            || WS_FUNC_FLG == 'U'
            || WS_FUNC_FLG == 'D') {
            BPCSMACT.FUNC = 'C';
            BPCSMACT.OUTPUT_FLG = 'N';
        } else if (WS_FUNC_FLG == 'B') {
            BPCSMACT.FUNC = 'B';
            BPCSMACT.OUTPUT_FLG = 'Y';
        } else {
        }
        S000_CALL_BPZSMACT();
        CEP.TRC(SCCGWA, BPCSMACT.OUTPUT_FLG);
    }
    public void B300_SET_RETURN_INFO() throws IOException,SQLException,Exception {
        if (WS_FUNC_FLG == 'B') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 4011;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'Q') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 4012;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'U') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 4013;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'D') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 4014;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'A') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 4015;
            S000_SET_SUBS_TRN();
        } else {
        }
        if (WS_FUNC_FLG == 'U' 
            || WS_FUNC_FLG == 'D' 
            || WS_FUNC_FLG == 'Q') {
            B310_SET_RETURN_DATA();
        }
        if (WS_FUNC_FLG == 'A') {
            B311_SET_RETURN_DATA();
        }
    }
    public void B310_SET_RETURN_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_FMT_DATA);
        WS_FMT_DATA.WS_FMT_ACCT_CD = BPB4000_AWA_4000.ACCT_CD;
        WS_FMT_DATA.WS_FMT_ACCT_NM = BPB4000_AWA_4000.ACCT_NM;
        WS_FMT_DATA.WS_FMT_ITEM_NO = "" + BPB4000_AWA_4000.ITEM_NO;
        JIBS_tmp_int = WS_FMT_DATA.WS_FMT_ITEM_NO.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) WS_FMT_DATA.WS_FMT_ITEM_NO = "0" + WS_FMT_DATA.WS_FMT_ITEM_NO;
        WS_FMT_DATA.WS_FMT_ITEM_NM = BPB4000_AWA_4000.ITEM_NM;
        WS_FMT_DATA.WS_FMT_ACCT_OFF = BPCSMACT.INFO.ACCT_OFF;
        WS_FMT_DATA.WS_FMT_VAL_DATE = BPCSMACT.VAL_DATE;
        WS_FMT_DATA.WS_FMT_EXP_DATE = BPCSMACT.EXP_DATE;
        WS_FMT_DATA.WS_FMT_REMARK = BPCSMACT.INFO.REMARK;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_FMT_CD;
        SCCFMT.DATA_PTR = WS_FMT_DATA;
        SCCFMT.DATA_LEN = 282;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B311_SET_RETURN_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_FMT_DATA);
        WS_FMT_DATA.WS_FMT_ACCT_CD = BPB4000_AWA_4000.ACCT_CD;
        WS_FMT_DATA.WS_FMT_ACCT_NM = BPB4000_AWA_4000.ACCT_NM;
        WS_FMT_DATA.WS_FMT_ACCT_OFF = BPB4000_AWA_4000.ACCT_OFF;
        WS_FMT_DATA.WS_FMT_ITEM_NO = "" + BPB4000_AWA_4000.ITEM_NO;
        JIBS_tmp_int = WS_FMT_DATA.WS_FMT_ITEM_NO.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) WS_FMT_DATA.WS_FMT_ITEM_NO = "0" + WS_FMT_DATA.WS_FMT_ITEM_NO;
        WS_FMT_DATA.WS_FMT_ITEM_NM = BPB4000_AWA_4000.ITEM_NM;
        WS_FMT_DATA.WS_FMT_VAL_DATE = BPB4000_AWA_4000.EFF_DATE;
        WS_FMT_DATA.WS_FMT_EXP_DATE = BPB4000_AWA_4000.EXP_DATE;
        WS_FMT_DATA.WS_FMT_REMARK = BPB4000_AWA_4000.RMK;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_FMT_CD;
        SCCFMT.DATA_PTR = WS_FMT_DATA;
        SCCFMT.DATA_LEN = 282;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZSMACT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = K_CMP_CAL_MAINTAIN;
        SCCCALL.COMMAREA_PTR = BPCSMACT;
        SCCCALL.ERR_FLDNO = BPB4000_AWA_4000.FUNC_CD_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = K_CMP_SET_SBUS_TRN;
        SCCCALL.COMMAREA_PTR = SCCSUBS;
        SCCCALL.ERR_FLDNO = BPB4000_AWA_4000.FUNC_CD_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
