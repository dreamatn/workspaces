package com.hisun.LN;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.CI.CICQACRI;
import com.hisun.CI.CIRACR;
import com.hisun.DC.DCCPACTY;
import com.hisun.DD.DDCIMCCY;
import com.hisun.DD.DDCMSG_ERROR_MSG;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class LNOT6090 {
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
    short WS_J = 0;
    LNOT6090_WS_IA_AC WS_IA_AC = new LNOT6090_WS_IA_AC();
    String WS_CTA_NO = " ";
    int WS_BR = 0;
    String WS_CI_NO = " ";
    String WS_CI_CNM = " ";
    char LNOT6090_FILLER5 = 0X02;
    String WS_PROD_CD = " ";
    String WS_PROD_NM = " ";
    char LNOT6090_FILLER8 = 0X02;
    String WS_DRWD_MTH = " ";
    String WS_CCY = " ";
    char WS_LOAN_STS = ' ';
    int WS_TRAN_DT = 0;
    double WS_LOAN_BAL = 0;
    int WS_PACK_DT = 0;
    double WS_P_BAL = 0;
    double WS_NOR_INT = 0;
    double WS_OVD_INT = 0;
    double WS_P_INT = 0;
    int WS_BTH_PK = 0;
    String WS_PK_NM = " ";
    char LNOT6090_FILLER21 = 0X02;
    char WS_PRIN_MOD = ' ';
    char WS_INT_MOD = ' ';
    String WS_P_AC_TYP = " ";
    String WS_P_AC = " ";
    String WS_I_AC_TYP = " ";
    String WS_I_AC = " ";
    DCCPACTY DCCPACTY = new DCCPACTY();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    LNRICTL LNRICTL = new LNRICTL();
    LNCRICTL LNCRICTL = new LNCRICTL();
    LNRLOAN LNRLOAN = new LNRLOAN();
    LNCRLOAN LNCRLOAN = new LNCRLOAN();
    LNRCONT LNRCONT = new LNRCONT();
    LNCRCONT LNCRCONT = new LNCRCONT();
    LNRBALL LNRBALL = new LNRBALL();
    LNCRBALL LNCRBALL = new LNCRBALL();
    LNCSPLTN LNCSPLTN = new LNCSPLTN();
    CIRACR CIRACR = new CIRACR();
    LNCSSTBL LNCSSTBL = new LNCSSTBL();
    DDCIMCCY DDCIMCCY = new DDCIMCCY();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    LNCPCFTA LNCPCFTA = new LNCPCFTA();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    LNRAGRE LNRAGRE = new LNRAGRE();
    LNCRAGRE LNCRAGRE = new LNCRAGRE();
    CICQACRI CICQACRI = new CICQACRI();
    LNCCONTM LNCCONTM = new LNCCONTM();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    LNB6090_AWA_6090 LNB6090_AWA_6090;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNOT6090 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB6090_AWA_6090>");
        LNB6090_AWA_6090 = (LNB6090_AWA_6090) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK_PROC();
        if (pgmRtn) return;
        B020_MAIN_PLTN_PROC();
        if (pgmRtn) return;
        B030_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B000_CHECK_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNB6090_AWA_6090.CTA_NO);
        CEP.TRC(SCCGWA, LNB6090_AWA_6090.TRAN_DT);
        CEP.TRC(SCCGWA, LNB6090_AWA_6090.PACK_DT);
        CEP.TRC(SCCGWA, LNB6090_AWA_6090.P_BAL);
        CEP.TRC(SCCGWA, LNB6090_AWA_6090.P_N_INT);
        CEP.TRC(SCCGWA, LNB6090_AWA_6090.PRIN_MOD);
        CEP.TRC(SCCGWA, LNB6090_AWA_6090.INT_MOD);
        CEP.TRC(SCCGWA, LNB6090_AWA_6090.P_AC);
        CEP.TRC(SCCGWA, LNB6090_AWA_6090.I_AC);
        if (LNB6090_AWA_6090.CTA_NO.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_AC_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B010_GET_CONTRACT_INFO();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNB6090_AWA_6090.TRAN_DT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (LNB6090_AWA_6090.TRAN_DT == 0) {
            LNB6090_AWA_6090.TRAN_DT = SCCGWA.COMM_AREA.AC_DATE;
        } else {
            if (LNB6090_AWA_6090.TRAN_DT != SCCGWA.COMM_AREA.AC_DATE 
                && SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN0320;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, LNB6090_AWA_6090.PACK_DT);
        if (LNB6090_AWA_6090.PACK_DT == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN0307;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNB6090_AWA_6090.PACK_DT > SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN0315;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNB6090_AWA_6090.PRIN_MOD != '0' 
            && LNB6090_AWA_6090.PRIN_MOD != '1') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN0300;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNB6090_AWA_6090.INT_MOD != '0' 
            && LNB6090_AWA_6090.INT_MOD != '1' 
            && LNB6090_AWA_6090.INT_MOD != '2') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN0300;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNB6090_AWA_6090.BTH_PK == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN0301;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNB6090_AWA_6090.PRIN_MOD != '0') {
            if (LNB6090_AWA_6090.P_AC_TYP.trim().length() == 0) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN0302;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (LNB6090_AWA_6090.P_AC.trim().length() == 0) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN0303;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (LNB6090_AWA_6090.INT_MOD != '0') {
            if (LNB6090_AWA_6090.I_AC_TYP.trim().length() == 0) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN0302;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (LNB6090_AWA_6090.I_AC.trim().length() == 0) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN0303;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (!LNRCONT.CONTRACT_TYPE.equalsIgnoreCase("CLDD")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_NONE_CLDD;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
            || LNRICTL.CTL_STSW.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN0381;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(19 - 1, 19 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN0316;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(41 - 1, 41 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN0317;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(52 - 1, 52 + 1 - 1).equalsIgnoreCase("1") 
            || LNRICTL.CTL_STSW.substring(53 - 1, 53 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN0380;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(51 - 1, 51 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN0308;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(54 - 1, 54 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN0310;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(55 - 1, 55 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN0311;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1") 
            || LNRICTL.CTL_STSW.substring(13 - 1, 13 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN0312;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(47 - 1, 47 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN0382;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCPCFTA);
        WS_J = 1;
        if (LNB6090_AWA_6090.P_AC.trim().length() > 0) {
            WS_SETL_AC = LNB6090_AWA_6090.P_AC;
            B011_CHECK_AC_TYP();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, " ");
            CEP.TRC(SCCGWA, LNB6090_AWA_6090.P_AC_TYP);
            CEP.TRC(SCCGWA, WS_SETL_AC_TYPE);
            if (!LNB6090_AWA_6090.P_AC_TYP.equalsIgnoreCase(WS_SETL_AC_TYPE)) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN0353;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            B013_CHECK_AC_CCY();
            if (pgmRtn) return;
        }
        if (LNB6090_AWA_6090.I_AC.trim().length() > 0) {
            WS_SETL_AC = LNB6090_AWA_6090.I_AC;
            B011_CHECK_AC_TYP();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, " ");
            CEP.TRC(SCCGWA, LNB6090_AWA_6090.I_AC_TYP);
            CEP.TRC(SCCGWA, WS_SETL_AC_TYPE);
            if (!LNB6090_AWA_6090.I_AC_TYP.equalsIgnoreCase(WS_SETL_AC_TYPE)) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN0353;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            B013_CHECK_AC_CCY();
            if (pgmRtn) return;
        }
        LNCPCFTA.BR_GP[WS_J-1].BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        LNCPCFTA.BR_GP[WS_J + 1-1].BR = LNRCONT.BOOK_BR;
        if (LNCPCFTA.FTA_FLG == 'Y') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_FTA_MUST_SAME;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (!LNB6090_AWA_6090.P_AC_TYP.equalsIgnoreCase("02") 
            || !LNB6090_AWA_6090.I_AC_TYP.equalsIgnoreCase("02")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_AC_TYP_AI;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNB6090_AWA_6090.P_AC == null) LNB6090_AWA_6090.P_AC = "";
        JIBS_tmp_int = LNB6090_AWA_6090.P_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) LNB6090_AWA_6090.P_AC += " ";
        if (LNB6090_AWA_6090.I_AC == null) LNB6090_AWA_6090.I_AC = "";
        JIBS_tmp_int = LNB6090_AWA_6090.I_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) LNB6090_AWA_6090.I_AC += " ";
        if (!LNB6090_AWA_6090.P_AC.substring(10 - 1, 10 + 10 - 1).equalsIgnoreCase("2181009000") 
            || !LNB6090_AWA_6090.I_AC.substring(10 - 1, 10 + 10 - 1).equalsIgnoreCase("2181009000")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_AC_ITM_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCCONTM);
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNB6090_AWA_6090.CTA_NO;
        LNCCONTM.FUNC = '3';
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
        if (LNB6090_AWA_6090.P_AC == null) LNB6090_AWA_6090.P_AC = "";
        JIBS_tmp_int = LNB6090_AWA_6090.P_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) LNB6090_AWA_6090.P_AC += " ";
        if (LNB6090_AWA_6090.I_AC == null) LNB6090_AWA_6090.I_AC = "";
        JIBS_tmp_int = LNB6090_AWA_6090.I_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) LNB6090_AWA_6090.I_AC += " ";
        if (!LNB6090_AWA_6090.P_AC.substring(7 - 1, 7 + 3 - 1).equalsIgnoreCase(LNCCONTM.REC_DATA.CCY) 
            || !LNB6090_AWA_6090.I_AC.substring(7 - 1, 7 + 3 - 1).equalsIgnoreCase(LNCCONTM.REC_DATA.CCY)) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_AC_CCY_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B011_CHECK_AC_TYP() throws IOException,SQLException,Exception {
        if (WS_SETL_AC.trim().length() > 0) {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.DATA.AGR_NO = WS_SETL_AC;
            CICQACRI.FUNC = 'A';
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("IB")) {
                WS_SETL_AC_TYPE = K_IB_AC;
            } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
                WS_SETL_AC_TYPE = K_DD_AC;
            } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
                WS_SETL_AC_TYPE = K_DC_AC;
            } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
                WS_SETL_AC_TYPE = K_IA_AC;
            } else {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN0391;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B013_CHECK_AC_CCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIMCCY);
        if (WS_SETL_AC_TYPE.equalsIgnoreCase(K_DD_AC)) {
            DDCIMCCY.DATA[1-1].AC = WS_SETL_AC;
            DDCIMCCY.DATA[1-1].CCY = LNB6090_AWA_6090.CCY;
            if (LNB6090_AWA_6090.CCY.equalsIgnoreCase("156")) {
                DDCIMCCY.DATA[1-1].CCY_TYPE = '1';
            } else {
                DDCIMCCY.DATA[1-1].CCY_TYPE = '2';
            }
            S000_CALL_DDZIMCCY();
            if (pgmRtn) return;
            if (!DDCIMCCY.DATA[1-1].CCY.equalsIgnoreCase(LNRCONT.CCY)) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN0394;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (WS_SETL_AC_TYPE.equalsIgnoreCase(K_IA_AC)) {
            IBS.CPY2CLS(SCCGWA, WS_SETL_AC, WS_IA_AC);
            if (!WS_IA_AC.WS_AC_CCY.equalsIgnoreCase(LNRCONT.CCY)) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN0394;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_GET_CONTRACT_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCONT);
        IBS.init(SCCGWA, LNCRCONT);
        LNRCONT.KEY.CONTRACT_NO = LNB6090_AWA_6090.CTA_NO;
        LNCRCONT.FUNC = 'I';
        S000_CALL_LNZRCONT();
        if (pgmRtn) return;
        LNB6090_AWA_6090.CCY = LNRCONT.CCY;
        LNB6090_AWA_6090.BR = LNRCONT.BOOK_BR;
        LNB6090_AWA_6090.PROD_CD = LNRCONT.PROD_CD;
        IBS.init(SCCGWA, LNRBALL);
        IBS.init(SCCGWA, LNCRBALL);
        LNRBALL.KEY.CONTRACT_NO = LNB6090_AWA_6090.CTA_NO;
        LNCRBALL.FUNC = 'I';
        S000_CALL_LNZRBALL();
        if (pgmRtn) return;
        WS_LOAN_AMT = LNRBALL.REDEFINES7.REDEFINES9.LOAN_CONT[1-1].LOAN_BAL;
        WS_LOAN_BAL1 = LNRBALL.REDEFINES7.REDEFINES9.LOAN_CONT[8-1].LOAN_BAL;
        WS_LOAN_AMT = WS_LOAN_AMT - WS_LOAN_BAL1;
        LNB6090_AWA_6090.LOAN_BAL = WS_LOAN_AMT;
        IBS.init(SCCGWA, LNRICTL);
        IBS.init(SCCGWA, LNCRICTL);
        LNRICTL.KEY.CONTRACT_NO = LNB6090_AWA_6090.CTA_NO;
        CEP.TRC(SCCGWA, LNRICTL.KEY.CONTRACT_NO);
        LNCRICTL.FUNC = 'I';
        S000_CALL_LNZRICTL();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCRAGRE);
        IBS.init(SCCGWA, LNRAGRE);
        LNCRAGRE.FUNC = 'I';
        LNRAGRE.KEY.CONTRACT_NO = LNB6090_AWA_6090.CTA_NO;
        S000_CALL_LNZRAGRE();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRACR);
        CIRACR.KEY.AGR_NO = LNRAGRE.PAPER_NO;
        T000_READ_CITACR();
        if (pgmRtn) return;
        LNB6090_AWA_6090.CI_NO = CIRACR.CI_NO;
