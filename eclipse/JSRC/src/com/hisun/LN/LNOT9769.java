package com.hisun.LN;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.CI.CIRACR;
import com.hisun.DC.DCCPACTY;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class LNOT9769 {
    boolean pgmRtn = false;
    String K_DD_AC = "01";
    String K_IA_AC = "02";
    String K_IB_AC = "03";
    String K_DC_AC = "05";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    double WS_LOAN_BAL1 = 0;
    double WS_LOAN_AMT = 0;
    String WS_SETL_AC = " ";
    String WS_SETL_AC_TYPE = " ";
    String WS_CTA_NO = " ";
    int WS_BR = 0;
    String WS_CI_NO = " ";
    String WS_CI_CNM = " ";
    char LNOT9769_FILLER5 = 0X02;
    String WS_PROD_CD = " ";
    String WS_PROD_NM = " ";
    char LNOT9769_FILLER8 = 0X02;
    String WS_CCY = " ";
    double WS_LOAN_TOT = 0;
    double WS_LOAN_BAL = 0;
    char WS_LOAN_STS = ' ';
    int WS_TRAN_DT = 0;
    double WS_NOR_INT = 0;
    double WS_OVD_INT = 0;
    double WS_P_INT = 0;
    double WS_TRAN_AMT = 0;
    int WS_BTH_PK = 0;
    String WS_AC_TYP2 = " ";
    String WS_TR_AC = " ";
    String WS_AC_CNM2 = " ";
    char LNOT9769_FILLER22 = 0X02;
    char WS_COMPANY_FLG = ' ';
    char WS_CONSTRUT_FLG = ' ';
    char WS_SELL_OUT_FLG = ' ';
    DCCPACTY DCCPACTY = new DCCPACTY();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    LNRICTL LNRICTL = new LNRICTL();
    LNCRICTL LNCRICTL = new LNCRICTL();
    LNRSETL LNRSETL = new LNRSETL();
    LNCRSETL LNCRSETL = new LNCRSETL();
    LNRLOAN LNRLOAN = new LNRLOAN();
    LNCRLOAN LNCRLOAN = new LNCRLOAN();
    LNRCONT LNRCONT = new LNRCONT();
    LNCRCONT LNCRCONT = new LNCRCONT();
    LNRBALL LNRBALL = new LNRBALL();
    LNCRBALL LNCRBALL = new LNCRBALL();
    LNRBALH LNRBALH = new LNRBALH();
    LNCRBALH LNCRBALH = new LNCRBALH();
    CIRACR CIRACR = new CIRACR();
    LNCSNPTN LNCSNPTR = new LNCSNPTN();
    LNCSSTBL LNCSSTBL = new LNCSSTBL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCGWA SCCGWA;
    LNB9700_AWA_9700 LNB9700_AWA_9700;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNOT9769 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB9700_AWA_9700>");
        LNB9700_AWA_9700 = (LNB9700_AWA_9700) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK_PROC();
        if (pgmRtn) return;
        B010_GET_CONTRACT_INFO();
        if (pgmRtn) return;
        B020_MAIN_PLTN_PROC();
        if (pgmRtn) return;
        B030_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B000_CHECK_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNB9700_AWA_9700.CTA_NO);
        if (LNB9700_AWA_9700.BTH_PK == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN0301;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B010_GET_CONTRACT_INFO();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNB9700_AWA_9700.TRAN_DT);
        CEP.TRC(SCCGWA, LNRCONT.LAST_F_VAL_DATE);
        IBS.init(SCCGWA, LNCSSTBL);
        LNCSSTBL.CON_NO = LNB9700_AWA_9700.CTA_NO;
        LNCSSTBL.TR_CODE = "" + SCCGWA.COMM_AREA.TR_ID.TR_CODE;
        JIBS_tmp_int = LNCSSTBL.TR_CODE.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) LNCSSTBL.TR_CODE = "0" + LNCSSTBL.TR_CODE;
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        CEP.TRC(SCCGWA, LNRICTL.CTL_STSW.substring(4 - 1, 4 + 1 - 1));
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        CEP.TRC(SCCGWA, LNRICTL.CTL_STSW.substring(37 - 1, 37 + 1 - 1));
        S000_CALL_LNZSSTBL();
        if (pgmRtn) return;
        if (LNB9700_AWA_9700.TR_AC.trim().length() > 0) {
            WS_SETL_AC = LNB9700_AWA_9700.TR_AC;
            B011_CHECK_AC_INFO();
            if (pgmRtn) return;
            LNB9700_AWA_9700.AC_TYP2 = WS_SETL_AC_TYPE;
            if (LNB9700_AWA_9700.AC_TYP2.trim().length() == 0) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN0302;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B011_CHECK_AC_INFO() throws IOException,SQLException,Exception {
        if (WS_SETL_AC.trim().length() > 0) {
            IBS.init(SCCGWA, DCCPACTY);
            DCCPACTY.INPUT.AC = WS_SETL_AC;
            DCCPACTY.INPUT.FUNC = '1';
            S000_CALL_DCZPACTY();
            if (pgmRtn) return;
            if (DCCPACTY.OUTPUT.AC_TYPE == 'D' 
                    && DCCPACTY.OUTPUT.STD_APP.equalsIgnoreCase("IB")) {
                WS_SETL_AC_TYPE = K_IB_AC;
            } else if (DCCPACTY.OUTPUT.AC_TYPE == 'D' 
                    && DCCPACTY.OUTPUT.STD_APP.equalsIgnoreCase("DD")) {
                WS_SETL_AC_TYPE = K_DD_AC;
            } else if (DCCPACTY.OUTPUT.AC_TYPE == 'K') {
                WS_SETL_AC_TYPE = K_DC_AC;
            } else if (DCCPACTY.OUTPUT.AC_TYPE == 'G') {
                WS_SETL_AC_TYPE = K_IA_AC;
            } else {
                IBS.init(SCCGWA, SCCEXCP);
                SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID ACCOUNT TYPE(" + DCCPACTY.OUTPUT.AC_DETL + ")";
                CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
            }
        }
    }
    public void B010_GET_CONTRACT_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCONT);
        IBS.init(SCCGWA, LNCRCONT);
        LNRCONT.KEY.CONTRACT_NO = LNB9700_AWA_9700.CTA_NO;
        LNCRCONT.FUNC = 'I';
        S000_CALL_LNZRCONT();
        if (pgmRtn) return;
        LNB9700_AWA_9700.CCY = LNRCONT.CCY;
        LNB9700_AWA_9700.BR = LNRCONT.BOOK_BR;
        LNB9700_AWA_9700.PROD_CD = LNRCONT.PROD_CD;
        IBS.init(SCCGWA, LNRBALL);
        IBS.init(SCCGWA, LNCRBALL);
        LNRBALL.KEY.CONTRACT_NO = LNB9700_AWA_9700.CTA_NO;
        LNCRBALL.FUNC = 'I';
        S000_CALL_LNZRBALL();
        if (pgmRtn) return;
        WS_LOAN_AMT = LNRBALL.REDEFINES7.REDEFINES9.LOAN_CONT[1-1].LOAN_BAL;
        WS_LOAN_BAL1 = LNRBALL.REDEFINES7.REDEFINES9.LOAN_CONT[8-1].LOAN_BAL;
        LNB9700_AWA_9700.LOAN_TOT = WS_LOAN_AMT;
        WS_LOAN_AMT = WS_LOAN_AMT - WS_LOAN_BAL1;
        LNB9700_AWA_9700.LOAN_BAL = WS_LOAN_AMT;
        LNB9700_AWA_9700.TRAN_AMT = WS_LOAN_AMT;
        IBS.init(SCCGWA, LNRICTL);
        IBS.init(SCCGWA, LNCRICTL);
        LNRICTL.KEY.CONTRACT_NO = LNB9700_AWA_9700.CTA_NO;
        LNCRICTL.FUNC = 'I';
        S000_CALL_LNZRICTL();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRACR);
        CIRACR.KEY.AGR_NO = LNB9700_AWA_9700.CTA_NO;
        T000_READ_CITACR();
        if (pgmRtn) return;
        LNB9700_AWA_9700.CI_NO = CIRACR.CI_NO;
