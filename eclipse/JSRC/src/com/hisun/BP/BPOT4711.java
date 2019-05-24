package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4711 {
    String K_CMP_AGC_MAINTAIN = "BP-MAINT-AUTOGEN-CAL";
    String K_FMT_CD = "BP451";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_I = 0;
    short WS_II = 0;
    short WS_III = 0;
    BPOT4711_WS_FMT_DATA WS_FMT_DATA = new BPOT4711_WS_FMT_DATA();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
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
        CEP.TRC(SCCGWA, "BPOT4711 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4700_AWA_4700>");
        BPB4700_AWA_4700 = (BPB4700_AWA_4700) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_MAIN_PROCESS();
        B200_SET_RETURN_INFO();
    }
    public void B100_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSMAGC);
        BPCSMAGC.CAL_CODE = BPB4700_AWA_4700.CALCD;
        BPCSMAGC.YEAR = BPB4700_AWA_4700.YEAR;
        BPCSMAGC.OUTPUT_FLG = 'N';
        BPCSMAGC.FUNC = 'C';
        S000_CALL_BPZSMAGC();
        if (BPCSMAGC.CHECK_RESULT == 'E') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CAL_AUTOGEN_EXIST;
            WS_FLD_NO = BPB4700_AWA_4700.CALCD_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_SET_RETURN_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_FMT_DATA);
        WS_III = BPB4700_AWA_4700.MONTH;
        if (BPCSMAGC.CAL_WEEK_INFO.MON_WK_NO[WS_III-1] == 7) {
            WS_II = 1;
        } else {
            WS_II = (short) (BPCSMAGC.CAL_WEEK_INFO.MON_WK_NO[WS_III-1] + 1);
        }
        for (WS_I = 1; WS_I <= 31; WS_I += 1) {
            if (WS_I <= BPCSMAGC.CAL_WEEK_INFO.MON_DAYS[WS_III-1]) {
                WS_FMT_DATA.WS_FMT_DETAIL[WS_II-1].WS_FMT_DT_NO = WS_I;
                WS_FMT_DATA.WS_FMT_DETAIL[WS_II-1].WS_FMT_DT_TP = BPCSMAGC.CAL_DETAIL.MONTH[WS_III-1].DAY_TYP[WS_I-1];
                WS_II = (short) (WS_II + 1);
            }
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_FMT_CD;
        SCCFMT.DATA_PTR = WS_FMT_DATA;
        SCCFMT.DATA_LEN = 126;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZSMAGC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = K_CMP_AGC_MAINTAIN;
        SCCCALL.COMMAREA_PTR = BPCSMAGC;
        SCCCALL.ERR_FLDNO = BPB4700_AWA_4700.FUNC_CD_NO;
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
