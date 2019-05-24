package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT8212 {
    BigDecimal bigD;
    DBParm LNTSUBS_RD;
    String K_FMT_ID = "LN821";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    LNOT8212_WS_FMT_OUT WS_FMT_OUT = new LNOT8212_WS_FMT_OUT();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNRSUBS LNRSUBS = new LNRSUBS();
    SCCGWA SCCGWA;
    LNB8212_AWA_8212 LNB8212_AWA_8212;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNOT8212 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB8212_AWA_8212>");
        LNB8212_AWA_8212 = (LNB8212_AWA_8212) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_GET_SUB_INF();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNB8212_AWA_8212.PROJ_NO);
        CEP.TRC(SCCGWA, LNB8212_AWA_8212.CTA_NO);
        if (LNB8212_AWA_8212.CTA_NO.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            if (LNB8212_AWA_8212.CTA_NO.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(LNB8212_AWA_8212.CTA_NO);
            S000_ERR_MSG_PROC();
        }
        if (LNB8212_AWA_8212.PROJ_NO == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            WS_FLD_NO = (short) LNB8212_AWA_8212.PROJ_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_GET_SUB_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRSUBS);
        CEP.TRC(SCCGWA, LNB8212_AWA_8212.PROJ_NO);
        LNRSUBS.KEY.PROJ_NO = LNB8212_AWA_8212.PROJ_NO;
        T000_READ_LNTSUBS();
        CEP.TRC(SCCGWA, LNRSUBS.TYP);
        CEP.TRC(SCCGWA, LNRSUBS.SUBS_MTH);
        CEP.TRC(SCCGWA, LNRSUBS.AMT_TYP);
        CEP.TRC(SCCGWA, LNB8212_AWA_8212.PRIN);
        CEP.TRC(SCCGWA, LNB8212_AWA_8212.INT);
        IBS.init(SCCGWA, WS_FMT_OUT);
        WS_FMT_OUT.WS_FMT_OUT_PROJ_NO = LNB8212_AWA_8212.PROJ_NO;
        WS_FMT_OUT.WS_FMT_OUT_CTA_NO = LNB8212_AWA_8212.CTA_NO;
        if (LNRSUBS.TYP == '3') {
            if (LNRSUBS.SUBS_MTH == '3') {
                if (LNRSUBS.AMT_TYP == '1') {
                    WS_FMT_OUT.WS_FMT_OUT_SUBS_INT = LNB8212_AWA_8212.INT * LNRSUBS.FIXED_AMT / 100;
                    bigD = new BigDecimal(WS_FMT_OUT.WS_FMT_OUT_SUBS_INT);
                    WS_FMT_OUT.WS_FMT_OUT_SUBS_INT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                } else {
                    WS_FMT_OUT.WS_FMT_OUT_SUBS_INT = LNRSUBS.FIXED_AMT;
                }
                WS_FMT_OUT.WS_FMT_OUT_OWN_INT = LNB8212_AWA_8212.INT - WS_FMT_OUT.WS_FMT_OUT_SUBS_INT;
                bigD = new BigDecimal(WS_FMT_OUT.WS_FMT_OUT_OWN_INT);
                WS_FMT_OUT.WS_FMT_OUT_OWN_INT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                WS_FMT_OUT.WS_FMT_OUT_SUBS_PRIN = 0;
                WS_FMT_OUT.WS_FMT_OUT_OWN_PRIN = LNB8212_AWA_8212.PRIN;
            }
            if (LNRSUBS.SUBS_MTH == '5') {
                if (LNRSUBS.AMT_TYP == '1') {
                    WS_FMT_OUT.WS_FMT_OUT_SUBS_PRIN = LNB8212_AWA_8212.PRIN * LNRSUBS.FIXED_AMT / 100;
                    bigD = new BigDecimal(WS_FMT_OUT.WS_FMT_OUT_SUBS_PRIN);
                    WS_FMT_OUT.WS_FMT_OUT_SUBS_PRIN = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                } else {
                    WS_FMT_OUT.WS_FMT_OUT_SUBS_PRIN = LNRSUBS.FIXED_AMT;
                }
                WS_FMT_OUT.WS_FMT_OUT_OWN_PRIN = LNB8212_AWA_8212.PRIN - WS_FMT_OUT.WS_FMT_OUT_SUBS_PRIN;
                bigD = new BigDecimal(WS_FMT_OUT.WS_FMT_OUT_OWN_PRIN);
                WS_FMT_OUT.WS_FMT_OUT_OWN_PRIN = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                WS_FMT_OUT.WS_FMT_OUT_SUBS_INT = 0;
                WS_FMT_OUT.WS_FMT_OUT_OWN_INT = LNB8212_AWA_8212.INT;
            }
        }
        WS_FMT_OUT.WS_FMT_OUT_TERM_PRIN = WS_FMT_OUT.WS_FMT_OUT_SUBS_PRIN + WS_FMT_OUT.WS_FMT_OUT_OWN_PRIN;
        bigD = new BigDecimal(WS_FMT_OUT.WS_FMT_OUT_TERM_PRIN);
        WS_FMT_OUT.WS_FMT_OUT_TERM_PRIN = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
        WS_FMT_OUT.WS_FMT_OUT_TERM_INT = WS_FMT_OUT.WS_FMT_OUT_SUBS_INT + WS_FMT_OUT.WS_FMT_OUT_OWN_INT;
        bigD = new BigDecimal(WS_FMT_OUT.WS_FMT_OUT_TERM_INT);
        WS_FMT_OUT.WS_FMT_OUT_TERM_INT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_FMT_ID;
        SCCFMT.DATA_PTR = WS_FMT_OUT;
        SCCFMT.DATA_LEN = 127;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_LNTSUBS() throws IOException,SQLException,Exception {
        LNTSUBS_RD = new DBParm();
        LNTSUBS_RD.TableName = "LNTSUBS";
        IBS.READ(SCCGWA, LNRSUBS, LNTSUBS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_SUBS_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
