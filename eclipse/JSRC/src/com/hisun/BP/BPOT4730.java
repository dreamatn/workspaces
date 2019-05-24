package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4730 {
    String JIBS_tmp_str[] = new String[10];
    String K_CMP_CAL_MAINTAIN = "BP-S-MAINT-CITY-CAL";
    String K_CMP_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    String K_FMT_CD = "BPX01";
    String PGM_SCSSCKDT = "SCSSCKDT";
    BPOT4730_WS_ERR_MSG WS_ERR_MSG = new BPOT4730_WS_ERR_MSG();
    short WS_FLD_NO = 0;
    int WS_DATE = 0;
    short WS_DATE_NO = 0;
    int WS_AC_DATE = 0;
    BPOT4730_REDEFINES8 REDEFINES8 = new BPOT4730_REDEFINES8();
    short WS_I = 0;
    short WS_II = 0;
    short WS_III = 0;
    BPOT4730_WS_FMT_DATA WS_FMT_DATA = new BPOT4730_WS_FMT_DATA();
    char WS_FUNC_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCSCCAL BPCSCCAL = new BPCSCCAL();
    SCCGWA SCCGWA;
    BPB4750_AWA_4750 BPB4750_AWA_4750;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4730 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4750_AWA_4750>");
        BPB4750_AWA_4750 = (BPB4750_AWA_4750) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        IBS.init(SCCGWA, BPCSCCAL);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_MAIN_PROCESS();
        B300_SET_RETURN_INFO();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB4750_AWA_4750.CALCD.trim().length() == 0 
            && BPB4750_AWA_4750.YEAR == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_INPUT_ONE, WS_ERR_MSG);
            WS_FLD_NO = BPB4750_AWA_4750.CALCD_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB4750_AWA_4750.CNTYS_CD.trim().length() == 0 
            && BPB4750_AWA_4750.CITY_CD.trim().length() > 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_INPUT_CNTY_CD, WS_ERR_MSG);
            WS_FLD_NO = BPB4750_AWA_4750.EFF_DATE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSCCAL);
        BPCSCCAL.CAL_CODE = BPB4750_AWA_4750.CALCD;
        BPCSCCAL.CAL_NAME = BPB4750_AWA_4750.CAL_NAME;
        BPCSCCAL.YEAR = BPB4750_AWA_4750.YEAR;
        BPCSCCAL.CNTYS_CD = BPB4750_AWA_4750.CNTYS_CD;
        BPCSCCAL.CITY_CD = BPB4750_AWA_4750.CITY_CD;
        BPCSCCAL.FUNC = 'B';
        BPCSCCAL.OUTPUT_FLG = 'Y';
        S000_CALL_BPZSCCAL();
    }
    public void B300_SET_RETURN_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCSUBS);
        SCCSUBS.AP_CODE = 999;
        SCCSUBS.TR_CODE = 4751;
        S000_SET_SUBS_TRN();
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
    public void S000_CALL_BPZSCCAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = K_CMP_CAL_MAINTAIN;
        SCCCALL.COMMAREA_PTR = BPCSCCAL;
        SCCCALL.ERR_FLDNO = BPB4750_AWA_4750.FUNC_CD_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = K_CMP_SET_SBUS_TRN;
        SCCCALL.COMMAREA_PTR = SCCSUBS;
        SCCCALL.ERR_FLDNO = BPB4750_AWA_4750.FUNC_CD_NO;
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
