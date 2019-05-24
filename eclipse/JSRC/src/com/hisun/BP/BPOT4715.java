package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4715 {
    String JIBS_tmp_str[] = new String[10];
    String K_CMP_AGC_MAINTAIN = "BP-MAINT-AUTOGEN-CAL";
    String PGM_SCSSCKDT = "SCSSCKDT";
    BPOT4715_WS_ERR_MSG WS_ERR_MSG = new BPOT4715_WS_ERR_MSG();
    short WS_FLD_NO = 0;
    int WS_DATE = 0;
    short WS_DATE_NO = 0;
    int WS_AC_DATE = 0;
    BPOT4715_REDEFINES8 REDEFINES8 = new BPOT4715_REDEFINES8();
    short WS_I = 0;
    short WS_II = 0;
    short WS_III = 0;
    char WS_DT_TYP_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCSMAGC BPCSMAGC = new BPCSMAGC();
    SCCGWA SCCGWA;
    BPB4700_AWA_4700 BPB4700_AWA_4700;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4715 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4700_AWA_4700>");
        BPB4700_AWA_4700 = (BPB4700_AWA_4700) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_MAIN_PROCESS();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        WS_AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBS.CPY2CLS(SCCGWA, WS_AC_DATE+"", REDEFINES8);
        if (BPB4700_AWA_4700.YEAR < REDEFINES8.WS_AC_YYYY) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_YEAR_TOO_SMALL, WS_ERR_MSG);
            WS_FLD_NO = BPB4700_AWA_4700.YEAR_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB4700_AWA_4700.EFF_DATE > BPB4700_AWA_4700.EXP_DATE) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_EFF_EXP_DATE_ERROR, WS_ERR_MSG);
            WS_FLD_NO = BPB4700_AWA_4700.EFF_DATE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB4700_AWA_4700.EFF_DATE != 0) {
            WS_DATE = BPB4700_AWA_4700.EFF_DATE;
            WS_DATE_NO = BPB4700_AWA_4700.EFF_DATE_NO;
            R000_CHECK_DATE();
        }
        if (BPB4700_AWA_4700.EXP_DATE != 0) {
            WS_DATE = BPB4700_AWA_4700.EXP_DATE;
            WS_DATE_NO = BPB4700_AWA_4700.EXP_DATE_NO;
            R000_CHECK_DATE();
        }
        for (WS_I = 1; WS_I <= 42; WS_I += 1) {
            if (BPB4700_AWA_4700.MONTH_1[WS_I-1].DT_NO_1 == 0 
                && BPB4700_AWA_4700.MONTH_1[WS_I-1].DT_TP_1 != ' ') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_DAY_TYP_ERROR, WS_ERR_MSG);
                WS_FLD_NO = BPB4700_AWA_4700.MONTH_1[WS_I-1].DT_TP_1_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            WS_DT_TYP_FLG = BPB4700_AWA_4700.MONTH_1[WS_I-1].DT_TP_1;
            if ((WS_DT_TYP_FLG != 'S' 
                && WS_DT_TYP_FLG != 'H' 
                && WS_DT_TYP_FLG != 'W')) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_DAY_TYP_ERROR, WS_ERR_MSG);
                WS_FLD_NO = BPB4700_AWA_4700.MONTH_1[WS_I-1].DT_TP_1_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, WS_ERR_MSG);
        WS_FLD_NO = 0;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSMAGC);
        BPCSMAGC.FUNC = 'A';
        BPCSMAGC.OUTPUT_FLG = 'Y';
        BPCSMAGC.CAL_CODE = BPB4700_AWA_4700.CALCD;
        BPCSMAGC.CAL_NAME = BPB4700_AWA_4700.CAL_NAME;
        BPCSMAGC.B_CAL_CODE = BPB4700_AWA_4700.T_CALCD;
        BPCSMAGC.B_CAL_NAME = BPB4700_AWA_4700.T_CAL_NE;
        BPCSMAGC.YEAR = BPB4700_AWA_4700.YEAR;
        BPCSMAGC.EFF_DATE = BPB4700_AWA_4700.EFF_DATE;
        BPCSMAGC.EXP_DATE = BPB4700_AWA_4700.EXP_DATE;
        BPCSMAGC.MON_NO = BPB4700_AWA_4700.MONTH;
        WS_III = BPB4700_AWA_4700.MONTH;
        WS_II = 1;
        for (WS_I = 1; WS_I <= 42; WS_I += 1) {
            if (BPB4700_AWA_4700.MONTH_1[WS_I-1].DT_NO_1 != 0) {
                BPCSMAGC.CAL_DETAIL.MONTH[WS_III-1].DAY_TYP[WS_II-1] = BPB4700_AWA_4700.MONTH_1[WS_I-1].DT_TP_1;
                WS_II += 1;
            }
        }
        S000_CALL_BPZSMAGC();
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
    public void S000_CALL_BPZSMAGC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = K_CMP_AGC_MAINTAIN;
        SCCCALL.COMMAREA_PTR = BPCSMAGC;
        SCCCALL.ERR_FLDNO = BPB4700_AWA_4700.FUNC_CD_NO;
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
