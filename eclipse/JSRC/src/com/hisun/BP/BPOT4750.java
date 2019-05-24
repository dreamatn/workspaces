package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCSUBS;

public class BPOT4750 {
    boolean pgmRtn = false;
    String K_CMP_CAL_MAINTAIN = "BP-S-MAINT-CALENDER";
    String K_CMP_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    String PGM_SCSSCKDT = "SCSSCKDT";
    BPOT4750_WS_ERR_MSG WS_ERR_MSG = new BPOT4750_WS_ERR_MSG();
    short WS_FLD_NO = 0;
    int WS_DATE = 0;
    short WS_DATE_NO = 0;
    int WS_AC_DATE = 0;
    BPOT4750_REDEFINES8 REDEFINES8 = new BPOT4750_REDEFINES8();
    short WS_I = 0;
    short WS_II = 0;
    short WS_III = 0;
    BPOT4750_WS_FMT_DATA WS_FMT_DATA = new BPOT4750_WS_FMT_DATA();
    char WS_FUNC_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCSCALE BPCSCALE = new BPCSCALE();
    SCCGWA SCCGWA;
    BPB4750_AWA_4750 BPB4750_AWA_4750;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT4750 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4750_AWA_4750>");
        BPB4750_AWA_4750 = (BPB4750_AWA_4750) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        IBS.init(SCCGWA, BPCSCALE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_MAIN_PROCESS();
        if (pgmRtn) return;
        B300_SET_RETURN_INFO();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB4750_AWA_4750.CNTYS_CD.trim().length() == 0 
            && BPB4750_AWA_4750.CITY_CD.trim().length() > 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_INPUT_CNTY_CD, WS_ERR_MSG);
            WS_FLD_NO = BPB4750_AWA_4750.EFF_DATE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSCALE);
        BPCSCALE.CAL_CODE = BPB4750_AWA_4750.CALCD;
        BPCSCALE.CAL_NAME = BPB4750_AWA_4750.CAL_NAME;
        BPCSCALE.YEAR = BPB4750_AWA_4750.YEAR;
        BPCSCALE.CAL_DETAIL.CNTYS_CD = BPB4750_AWA_4750.CNTYS_CD;
        BPCSCALE.CAL_DETAIL.CITY_CD = BPB4750_AWA_4750.CITY_CD;
        CEP.TRC(SCCGWA, BPB4750_AWA_4750.CALCD);
        CEP.TRC(SCCGWA, BPB4750_AWA_4750.CAL_NAME);
        CEP.TRC(SCCGWA, BPB4750_AWA_4750.YEAR);
        CEP.TRC(SCCGWA, BPB4750_AWA_4750.CNTYS_CD);
        CEP.TRC(SCCGWA, BPB4750_AWA_4750.CITY_CD);
        BPCSCALE.FUNC = 'B';
