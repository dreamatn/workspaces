package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT2240 {
    String JIBS_tmp_str[] = new String[10];
    LNOT2240_WS_ERR_MSG WS_ERR_MSG = new LNOT2240_WS_ERR_MSG();
    short WS_FLD_NO = 0;
    LNOT2240_WS_OUT_PUT WS_OUT_PUT = new LNOT2240_WS_OUT_PUT();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNCSICGB LNCSICGB = new LNCSICGB();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    LNB2240_AWA_2240 LNB2240_AWA_2240;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNOT2240 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB2240_AWA_2240>");
        LNB2240_AWA_2240 = (LNB2240_AWA_2240) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_CALL_LNZSICGB();
        B030_OUTPUT_PROCESS();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNB2240_AWA_2240.CTA_NO);
        CEP.TRC(SCCGWA, LNB2240_AWA_2240.BR);
        CEP.TRC(SCCGWA, LNB2240_AWA_2240.CI_NO);
        CEP.TRC(SCCGWA, LNB2240_AWA_2240.CI_CNM);
        CEP.TRC(SCCGWA, LNB2240_AWA_2240.CCY);
        CEP.TRC(SCCGWA, LNB2240_AWA_2240.PRINCIPA);
        CEP.TRC(SCCGWA, LNB2240_AWA_2240.OSBAL);
        CEP.TRC(SCCGWA, LNB2240_AWA_2240.TRAN_VDT);
        CEP.TRC(SCCGWA, LNB2240_AWA_2240.IC_AMT);
        CEP.TRC(SCCGWA, LNB2240_AWA_2240.AC_TYP);
        CEP.TRC(SCCGWA, LNB2240_AWA_2240.DD_AC);
        CEP.TRC(SCCGWA, LNB2240_AWA_2240.DD_CNM);
        CEP.TRC(SCCGWA, LNB2240_AWA_2240.RMK);
        CEP.TRC(SCCGWA, "AAAAAA");
        if (LNB2240_AWA_2240.CTA_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_CONT_NO_MUST_INPUT, WS_ERR_MSG);
            WS_FLD_NO = LNB2240_AWA_2240.CTA_NO_NO;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, "BAAAAA");
        if (LNB2240_AWA_2240.IC_AMT == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_IC_AMT, WS_ERR_MSG);
            WS_FLD_NO = LNB2240_AWA_2240.IC_AMT_NO;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, "CAAAAA");
        if ((LNB2240_AWA_2240.AC_TYP != '1' 
            && LNB2240_AWA_2240.AC_TYP != '5')) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_AC_TYP, WS_ERR_MSG);
            WS_FLD_NO = LNB2240_AWA_2240.AC_TYP_NO;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, "DAAAAA");
        if (LNB2240_AWA_2240.DD_AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DD_AC, WS_ERR_MSG);
            WS_FLD_NO = LNB2240_AWA_2240.DD_AC_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_CALL_LNZSICGB() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSICGB);
        LNCSICGB.CTA_NO = LNB2240_AWA_2240.CTA_NO;
        LNCSICGB.BR = LNB2240_AWA_2240.BR;
        LNCSICGB.CI_NO = LNB2240_AWA_2240.CI_NO;
        LNCSICGB.CI_CNM = LNB2240_AWA_2240.CI_CNM;
        LNCSICGB.CCY = LNB2240_AWA_2240.CCY;
        LNCSICGB.PRINCIPA = LNB2240_AWA_2240.PRINCIPA;
        LNCSICGB.OSBAL = LNB2240_AWA_2240.OSBAL;
        LNCSICGB.TRAN_VDT = LNB2240_AWA_2240.TRAN_VDT;
        LNCSICGB.IC_AMT = LNB2240_AWA_2240.IC_AMT;
        LNCSICGB.AC_TYP = LNB2240_AWA_2240.AC_TYP;
        LNCSICGB.DD_AC = LNB2240_AWA_2240.DD_AC;
        LNCSICGB.DD_CNM = LNB2240_AWA_2240.DD_CNM;
        LNCSICGB.RMK = LNB2240_AWA_2240.RMK;
        S000_CALL_LNZSICGB();
    }
    public void B030_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        WS_OUT_PUT.WS_CTA_NO = LNCSICGB.CTA_NO;
        WS_OUT_PUT.WS_BR = LNCSICGB.BR;
        WS_OUT_PUT.WS_CI_NO = LNCSICGB.CI_NO;
        WS_OUT_PUT.WS_CI_CNM = LNCSICGB.CI_CNM;
        WS_OUT_PUT.WS_CCY = LNCSICGB.CCY;
        WS_OUT_PUT.WS_PRINCIPA = LNCSICGB.PRINCIPA;
        WS_OUT_PUT.WS_OSBAL = LNCSICGB.OSBAL;
        WS_OUT_PUT.WS_TRAN_VDT = LNCSICGB.TRAN_VDT;
        WS_OUT_PUT.WS_IC_AMT = LNCSICGB.IC_AMT;
        WS_OUT_PUT.WS_AC_TYP = LNCSICGB.AC_TYP;
        WS_OUT_PUT.WS_DD_AC = LNCSICGB.DD_AC;
        WS_OUT_PUT.WS_DD_CNM = LNCSICGB.DD_CNM;
        WS_OUT_PUT.WS_RMK = LNCSICGB.RMK;
        CEP.TRC(SCCGWA, WS_OUT_PUT.WS_CTA_NO);
        CEP.TRC(SCCGWA, WS_OUT_PUT.WS_BR);
        CEP.TRC(SCCGWA, WS_OUT_PUT.WS_CI_NO);
        CEP.TRC(SCCGWA, WS_OUT_PUT.WS_CI_CNM);
        CEP.TRC(SCCGWA, WS_OUT_PUT.WS_CCY);
        CEP.TRC(SCCGWA, WS_OUT_PUT.WS_PRINCIPA);
        CEP.TRC(SCCGWA, WS_OUT_PUT.WS_OSBAL);
        CEP.TRC(SCCGWA, WS_OUT_PUT.WS_TRAN_VDT);
        CEP.TRC(SCCGWA, WS_OUT_PUT.WS_IC_AMT);
        CEP.TRC(SCCGWA, WS_OUT_PUT.WS_AC_TYP);
        CEP.TRC(SCCGWA, WS_OUT_PUT.WS_DD_AC);
        CEP.TRC(SCCGWA, WS_OUT_PUT.WS_DD_CNM);
        CEP.TRC(SCCGWA, WS_OUT_PUT.WS_RMK);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "LNP40";
        SCCFMT.DATA_PTR = WS_OUT_PUT;
        SCCFMT.DATA_LEN = 763;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_LNZSICGB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-CIGB", LNCSICGB);
        if (LNCSICGB.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSICGB.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ERR_MSG);
            S000_ERR_MSG_PROC();
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
