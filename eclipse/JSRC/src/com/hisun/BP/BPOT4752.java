package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4752 {
    String JIBS_tmp_str[] = new String[10];
    String K_CMP_CAL_MAINTAIN = "BP-S-MAINT-CALENDER";
    String PGM_SCSSCKDT = "SCSSCKDT";
    BPOT4752_WS_ERR_MSG WS_ERR_MSG = new BPOT4752_WS_ERR_MSG();
    short WS_FLD_NO = 0;
    int WS_DATE = 0;
    short WS_DATE_NO = 0;
    int WS_AC_DATE = 0;
    BPOT4752_REDEFINES8 REDEFINES8 = new BPOT4752_REDEFINES8();
    short WS_I = 0;
    short WS_II = 0;
    short WS_III = 0;
    BPOT4752_WS_MON WS_MON = new BPOT4752_WS_MON();
    char WS_DT_TYP_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCSCALE BPCSCALE = new BPCSCALE();
    SCCGWA SCCGWA;
    BPB4750_AWA_4750 BPB4750_AWA_4750;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4752 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4750_AWA_4750>");
        BPB4750_AWA_4750 = (BPB4750_AWA_4750) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_MAIN_PROCESS();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        WS_AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBS.CPY2CLS(SCCGWA, WS_AC_DATE+"", REDEFINES8);
        CEP.TRC(SCCGWA, BPB4750_AWA_4750.YEAR);
        CEP.TRC(SCCGWA, REDEFINES8.WS_AC_YYYY);
        CEP.TRC(SCCGWA, "NIAN1");
        if (BPB4750_AWA_4750.YEAR < REDEFINES8.WS_AC_YYYY) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_YEAR_TOO_SMALL, WS_ERR_MSG);
            WS_FLD_NO = BPB4750_AWA_4750.YEAR_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        CEP.TRC(SCCGWA, BPB4750_AWA_4750.END_YEAR);
        CEP.TRC(SCCGWA, BPB4750_AWA_4750.YEAR);
        CEP.TRC(SCCGWA, "NIAN2");
        if (BPB4750_AWA_4750.END_YEAR < BPB4750_AWA_4750.YEAR) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_YEAR_TOO_SMALL, WS_ERR_MSG);
            WS_FLD_NO = BPB4750_AWA_4750.END_YEAR_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        CEP.TRC(SCCGWA, BPB4750_AWA_4750.EXP_DATE);
        if (BPB4750_AWA_4750.EXP_DATE <= SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_EXP_DATE_ERROR, WS_ERR_MSG);
            WS_FLD_NO = BPB4750_AWA_4750.EXP_DATE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        CEP.TRC(SCCGWA, BPB4750_AWA_4750.CALCD);
        CEP.TRC(SCCGWA, BPB4750_AWA_4750.CNTYS_CD);
        if (BPB4750_AWA_4750.CALCD.trim().length() == 0 
            && BPB4750_AWA_4750.CNTYS_CD.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_INPUT_ONE, WS_ERR_MSG);
            WS_FLD_NO = BPB4750_AWA_4750.CALCD_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_INPUT_ONE, WS_ERR_MSG);
            WS_FLD_NO = BPB4750_AWA_4750.CNTYS_CD_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        CEP.TRC(SCCGWA, BPB4750_AWA_4750.CNTYS_CD);
        CEP.TRC(SCCGWA, BPB4750_AWA_4750.CITY_CD);
        CEP.TRC(SCCGWA, BPB4750_AWA_4750.EFF_DATE);
        if (BPB4750_AWA_4750.EFF_DATE > BPB4750_AWA_4750.EXP_DATE) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_EFF_EXP_DATE_ERROR, WS_ERR_MSG);
            WS_FLD_NO = BPB4750_AWA_4750.EFF_DATE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB4750_AWA_4750.EFF_DATE != 0) {
            WS_DATE = BPB4750_AWA_4750.EFF_DATE;
            WS_DATE_NO = BPB4750_AWA_4750.EFF_DATE_NO;
            R000_CHECK_DATE();
        }
        if (BPB4750_AWA_4750.EXP_DATE != 0) {
            WS_DATE = BPB4750_AWA_4750.EXP_DATE;
            WS_DATE_NO = BPB4750_AWA_4750.EXP_DATE_NO;
            R000_CHECK_DATE();
        }
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, WS_ERR_MSG);
        WS_FLD_NO = 0;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSCALE);
        BPCSCALE.CAL_CODE = BPB4750_AWA_4750.CALCD;
        BPCSCALE.CAL_NAME = BPB4750_AWA_4750.CAL_NAME;
        BPCSCALE.CAL_DETAIL.CNTYS_CD = BPB4750_AWA_4750.CNTYS_CD;
        BPCSCALE.CAL_DETAIL.CITY_CD = BPB4750_AWA_4750.CITY_CD;
        BPCSCALE.YEAR = BPB4750_AWA_4750.YEAR;
        BPCSCALE.END_YEAR = BPB4750_AWA_4750.END_YEAR;
        BPCSCALE.EFF_DATE = BPB4750_AWA_4750.EFF_DATE;
        BPCSCALE.EXP_DATE = BPB4750_AWA_4750.EXP_DATE;
        BPCSCALE.FUNC = 'A';
        BPCSCALE.OUTPUT_FLG = 'N';
        S000_CALL_BPZSCALE();
    }
    public void S000_CALL_BPZSCALE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = K_CMP_CAL_MAINTAIN;
        SCCCALL.COMMAREA_PTR = BPCSCALE;
        SCCCALL.ERR_FLDNO = BPB4750_AWA_4750.FUNC_CD_NO;
        IBS.CALL(SCCGWA, SCCCALL);
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
