package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4740 {
    String JIBS_tmp_str[] = new String[10];
    String K_CMP_AGC_MAINTAIN = "BP-S-MAINT-AUTOGEN";
    String K_CMP_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    String K_FMT_CD = "BPX01";
    String PGM_SCSSCKDT = "SCSSCKDT";
    BPOT4740_WS_ERR_MSG WS_ERR_MSG = new BPOT4740_WS_ERR_MSG();
    short WS_FLD_NO = 0;
    int WS_DATE = 0;
    short WS_DATE_NO = 0;
    int WS_AC_DATE = 0;
    BPOT4740_REDEFINES8 REDEFINES8 = new BPOT4740_REDEFINES8();
    short WS_I = 0;
    short WS_II = 0;
    short WS_III = 0;
    BPOT4740_WS_FMT_DATA WS_FMT_DATA = new BPOT4740_WS_FMT_DATA();
    char WS_FUNC_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCSAGCA BPCSAGCA = new BPCSAGCA();
    SCCGWA SCCGWA;
    BPB4730_AWA_4730 BPB4730_AWA_4730;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4740 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4730_AWA_4730>");
        BPB4730_AWA_4730 = (BPB4730_AWA_4730) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_MAIN_PROCESS();
        B300_SET_RETURN_INFO();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        WS_FUNC_FLG = BPB4730_AWA_4730.FUNC_CD;
        if ((WS_FUNC_FLG != 'B' 
            && WS_FUNC_FLG != 'A')) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FUNC_CD_INPUT_ERR, WS_ERR_MSG);
            WS_FLD_NO = BPB4730_AWA_4730.FUNC_CD_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (WS_FUNC_FLG == 'B') {
            if (BPB4730_AWA_4730.CALCD.trim().length() == 0 
                && BPB4730_AWA_4730.YEAR == 0 
                && BPB4730_AWA_4730.T_CALCD.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_INPUT_ONE, WS_ERR_MSG);
                WS_FLD_NO = BPB4730_AWA_4730.CALCD_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        if (WS_FUNC_FLG == 'A' 
            || WS_FUNC_FLG == 'U') {
            WS_AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
            IBS.CPY2CLS(SCCGWA, WS_AC_DATE+"", REDEFINES8);
            if (BPB4730_AWA_4730.YEAR < REDEFINES8.WS_AC_YYYY) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_YEAR_TOO_SMALL, WS_ERR_MSG);
                WS_FLD_NO = BPB4730_AWA_4730.YEAR_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        if (BPB4730_AWA_4730.EFF_DATE > BPB4730_AWA_4730.EXP_DATE) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_EFF_EXP_DATE_ERROR, WS_ERR_MSG);
            WS_FLD_NO = BPB4730_AWA_4730.EFF_DATE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB4730_AWA_4730.EFF_DATE != 0) {
            WS_DATE = BPB4730_AWA_4730.EFF_DATE;
            WS_DATE_NO = BPB4730_AWA_4730.EFF_DATE_NO;
            R000_CHECK_DATE();
        }
        if (BPB4730_AWA_4730.EXP_DATE != 0) {
            WS_DATE = BPB4730_AWA_4730.EXP_DATE;
            WS_DATE_NO = BPB4730_AWA_4730.EXP_DATE_NO;
            R000_CHECK_DATE();
        }
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, WS_ERR_MSG);
        WS_FLD_NO = 0;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSAGCA);
        BPCSAGCA.CAL_CODE = BPB4730_AWA_4730.CALCD;
        BPCSAGCA.CAL_NAME = BPB4730_AWA_4730.CAL_NAME;
        BPCSAGCA.B_CAL_CODE = BPB4730_AWA_4730.T_CALCD;
        BPCSAGCA.B_CAL_NAME = BPB4730_AWA_4730.T_CAL_NE;
        BPCSAGCA.YEAR = BPB4730_AWA_4730.YEAR;
        BPCSAGCA.EFF_DATE = BPB4730_AWA_4730.EFF_DATE;
        BPCSAGCA.EXP_DATE = BPB4730_AWA_4730.EXP_DATE;
        if (WS_FUNC_FLG == 'A'
            || WS_FUNC_FLG == 'Q'
            || WS_FUNC_FLG == 'U'
            || WS_FUNC_FLG == 'D') {
            BPCSAGCA.FUNC = 'C';
            BPCSAGCA.OUTPUT_FLG = 'N';
        } else if (WS_FUNC_FLG == 'B') {
            BPCSAGCA.FUNC = 'B';
            BPCSAGCA.OUTPUT_FLG = 'Y';
        } else {
        }
        CEP.TRC(SCCGWA, BPCSAGCA.FUNC);
        S000_CALL_BPZSAGCA();
        if (WS_FUNC_FLG == 'A') {
            if (BPCSAGCA.CHECK_RESULT == 'E') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CAL_AUTOGEN_EXIST, WS_ERR_MSG);
                WS_FLD_NO = BPB4730_AWA_4730.CALCD_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (WS_FUNC_FLG == 'Q' 
            || WS_FUNC_FLG == 'U' 
            || WS_FUNC_FLG == 'D') {
            if (BPCSAGCA.CHECK_RESULT == 'N') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CAL_AUTOGEN_NOTFND, WS_ERR_MSG);
                WS_FLD_NO = BPB4730_AWA_4730.CALCD_NO;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B300_SET_RETURN_INFO() throws IOException,SQLException,Exception {
        if (WS_FUNC_FLG == 'B') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 4741;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'Q') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 4742;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'U') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 4743;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'D') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 4744;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'A') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 4745;
            S000_SET_SUBS_TRN();
        } else {
        }
        if (WS_FUNC_FLG == 'U' 
            || WS_FUNC_FLG == 'D' 
            || WS_FUNC_FLG == 'Q' 
            || WS_FUNC_FLG == 'A') {
            B310_SET_RETURN_DATA();
        }
    }
    public void B310_SET_RETURN_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_FMT_DATA);
        WS_FMT_DATA.WS_FMT_CAL = BPB4730_AWA_4730.CALCD;
        WS_FMT_DATA.WS_FMT_CAL_NE = BPB4730_AWA_4730.CAL_NAME;
        WS_FMT_DATA.WS_FMT_T_CAL = BPB4730_AWA_4730.T_CALCD;
        WS_FMT_DATA.WS_FMT_T_CAL_NE = BPB4730_AWA_4730.T_CAL_NE;
        WS_FMT_DATA.WS_FMT_YEAR = BPB4730_AWA_4730.YEAR;
        WS_FMT_DATA.WS_FMT_EFF_DT = BPCSAGCA.EFF_DATE;
        WS_FMT_DATA.WS_FMT_EXP_DT = BPCSAGCA.EXP_DATE;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_FMT_CD;
        SCCFMT.DATA_PTR = WS_FMT_DATA;
        SCCFMT.DATA_LEN = 150;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_CHECK_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = WS_DATE;
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            WS_ERR_MSG.WS_ERR_AP = "SC";
            WS_ERR_MSG.WS_ERR_CODE = SCCCKDT.RC;
            WS_FLD_NO = WS_DATE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void S000_CALL_BPZSAGCA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = K_CMP_AGC_MAINTAIN;
        SCCCALL.COMMAREA_PTR = BPCSAGCA;
        SCCCALL.ERR_FLDNO = BPB4730_AWA_4730.FUNC_CD_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = K_CMP_SET_SBUS_TRN;
        SCCCALL.COMMAREA_PTR = SCCSUBS;
        SCCCALL.ERR_FLDNO = BPB4730_AWA_4730.FUNC_CD_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ERR_MSG);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ERR_MSG);
        CEP.ERRC(SCCGWA, JIBS_tmp_str[0], WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ERR_MSG);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
