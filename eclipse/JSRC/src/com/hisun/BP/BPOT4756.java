package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4756 {
    String JIBS_tmp_str[] = new String[10];
    String K_CMP_CAL_MAINTAIN = "BP-S-MAINT-CALENDER";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String K_FMT_CD = "BP452";
    BPOT4756_WS_ERR_MSG WS_ERR_MSG = new BPOT4756_WS_ERR_MSG();
    short WS_FLD_NO = 0;
    int WS_DATE = 0;
    short WS_DATE_NO = 0;
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
        CEP.TRC(SCCGWA, "BPOT4756 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4750_AWA_4750>");
        BPB4750_AWA_4750 = (BPB4750_AWA_4750) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_MAIN_PROCESS();
        B200_SET_RETURN_INFO();
    }
    public void B100_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSCALE);
        BPCSCALE.CAL_CODE = BPB4750_AWA_4750.CALCD;
        BPCSCALE.CAL_DETAIL.CNTYS_CD = BPB4750_AWA_4750.CNTYS_CD;
        BPCSCALE.CAL_DETAIL.CITY_CD = BPB4750_AWA_4750.CITY_CD;
        BPCSCALE.YEAR = BPB4750_AWA_4750.YEAR;
        BPCSCALE.EFF_DATE = BPB4750_AWA_4750.EFF_DATE;
        CEP.TRC(SCCGWA, BPB4750_AWA_4750.FUNC_CD);
        CEP.TRC(SCCGWA, BPB4750_AWA_4750.CALCD);
        CEP.TRC(SCCGWA, BPB4750_AWA_4750.CNTYS_CD);
        CEP.TRC(SCCGWA, BPB4750_AWA_4750.YEAR);
        if (BPB4750_AWA_4750.FUNC_CD == 'I') {
            BPCSCALE.FUNC = 'I';
        } else {
            BPCSCALE.FUNC = 'Q';
        }
        BPCSCALE.OUTPUT_FLG = 'N';
        S000_CALL_BPZSCALE();
    }
    public void B200_SET_RETURN_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_FMT_CD;
        SCCFMT.DATA_PTR = BPCSCALE.CAL_DETAIL;
        SCCFMT.DATA_LEN = 398;
        IBS.FMT(SCCGWA, SCCFMT);
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
