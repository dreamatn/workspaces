package com.hisun.LN;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class LNOT8170 {
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_READ_LNTCONT_FLG = ' ';
    LNOT8170_WS_OUT_PUT WS_OUT_PUT = new LNOT8170_WS_OUT_PUT();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNCSINTA LNCSINTA = new LNCSINTA();
    LNRCONT LNRCONT = new LNRCONT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    LNB8170_AWA_8170 LNB8170_AWA_8170;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNOT8170 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB8170_AWA_8170>");
        LNB8170_AWA_8170 = (LNB8170_AWA_8170) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_BROWSE_BAS_INFO();
        if (pgmRtn) return;
        B030_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_BROWSE_BAS_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNB8170_AWA_8170.FUNC);
        CEP.TRC(SCCGWA, LNB8170_AWA_8170.CTA_NO);
        CEP.TRC(SCCGWA, LNB8170_AWA_8170.PAY_TYP);
        CEP.TRC(SCCGWA, LNB8170_AWA_8170.PAY_TERM);
        IBS.init(SCCGWA, LNCSINTA);
        LNCSINTA.FUNC = LNB8170_AWA_8170.FUNC;
        LNCSINTA.CTA_NO = LNB8170_AWA_8170.CTA_NO;
        LNCSINTA.PAY_TYP = LNB8170_AWA_8170.PAY_TYP;
        if (LNB8170_AWA_8170.PAY_TYP == 'P') {
            LNCSINTA.LC_TYP = 'O';
        } else {
            LNCSINTA.LC_TYP = 'L';
        }
        LNCSINTA.PAY_TERM = LNB8170_AWA_8170.PAY_TERM;
        LNCSINTA.CI_NO = LNB8170_AWA_8170.CI_NO;
        LNCSINTA.CI_ENMS = LNB8170_AWA_8170.CI_ENMS;
        LNCSINTA.CITY_CD = LNB8170_AWA_8170.CITY_CD;
        LNCSINTA.CI_ENM = LNB8170_AWA_8170.CI_ENM;
        LNCSINTA.PRIN = LNB8170_AWA_8170.PRIN;
        LNCSINTA.OSBAL = LNB8170_AWA_8170.OSBAL;
        LNCSINTA.CCY = LNB8170_AWA_8170.CCY;
        LNCSINTA.STS = LNB8170_AWA_8170.STS;
        S000_CALL_LNZSINTA();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNRCONT);
        LNRCONT.KEY.CONTRACT_NO = LNB8170_AWA_8170.CTA_NO;
        T000_READ_LNTCONT();
        if (pgmRtn) return;
        if (WS_READ_LNTCONT_FLG == 'N') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.CONT_NFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        WS_OUT_PUT.WS_CTA_NO = LNCSINTA.CTA_NO;
        WS_OUT_PUT.WS_CI_NO = LNCSINTA.CI_NO;
        WS_OUT_PUT.WS_CI_ENMS = LNCSINTA.CI_ENMS;
        WS_OUT_PUT.WS_PRIN = LNCSINTA.PRIN;
        WS_OUT_PUT.WS_OSBAL = LNCSINTA.OSBAL;
        WS_OUT_PUT.WS_CCY = LNCSINTA.CCY;
        WS_OUT_PUT.WS_STS = LNCSINTA.STS;
        CEP.TRC(SCCGWA, "AAAAAAAAAAAA");
        CEP.TRC(SCCGWA, LNB8170_AWA_8170.PAY_TYP);
        CEP.TRC(SCCGWA, LNB8170_AWA_8170.PAY_TERM);
        CEP.TRC(SCCGWA, LNB8170_AWA_8170.VAL_DT);
        CEP.TRC(SCCGWA, LNB8170_AWA_8170.DUE_DT);
        CEP.TRC(SCCGWA, LNB8170_AWA_8170.INT);
        CEP.TRC(SCCGWA, LNB8170_AWA_8170.AMT);
        CEP.TRC(SCCGWA, "BBBBBBBBBBBB");
        CEP.TRC(SCCGWA, LNCSINTA.PAY_TYP);
        CEP.TRC(SCCGWA, LNCSINTA.PAY_TERM);
        CEP.TRC(SCCGWA, LNCSINTA.VAL_DT);
        CEP.TRC(SCCGWA, LNCSINTA.DUE_DT);
        CEP.TRC(SCCGWA, LNCSINTA.INT);
        CEP.TRC(SCCGWA, LNCSINTA.ADJ_AMT);
