package com.hisun.LN;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPNHIS;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class LNOT6150 {
    boolean pgmRtn = false;
    char K_CTA_STS_NORMAL = '0';
    String K_CONT_TYP_CLDD = "CLDD";
    String K_CPN_BPZPCFTA = "BP-P-CHK-FTA-TYP";
    String K_CPN_BPZPNHIS = "BP-REC-NHIS";
    char K_PAY_MTH_BLH = '4';
    char K_CAL_PERU_MONTH = 'M';
    char K_STS_NORMAL = '1';
    char K_REV_FLAG_Y = 'Y';
    char K_REV_FLAG_N = 'N';
    String K_HIS_RMKS = "DELAY REPAY PRIN";
    String K_MMO = "12019999";
    String PGM_SCSSCLDT = "SCSSCLDT";
    LNOT6150_WS_ERR_MSG WS_ERR_MSG = new LNOT6150_WS_ERR_MSG();
    short WS_I = 0;
    short WS_J = 0;
    short WS_K = 0;
    char WS_CIRCUL_FLG = ' ';
    int WS_MIN_DATE = 0;
    LNOT6150_REDEFINES12 REDEFINES12 = new LNOT6150_REDEFINES12();
    int WS_MAX_DATE = 0;
    LNOT6150_REDEFINES17 REDEFINES17 = new LNOT6150_REDEFINES17();
    int WS_INPUT_DATE = 0;
    LNOT6150_REDEFINES22 REDEFINES22 = new LNOT6150_REDEFINES22();
    short WS_END_YEAR = 0;
    short WS_START_YEAR = 0;
    String WS_CTL_STSW = " ";
    short WS_MONTH_REC_NUM = 0;
    char WS_FUNC_CD = ' ';
    LNOT6150_WS_TR_DATA WS_TR_DATA = new LNOT6150_WS_TR_DATA();
    LNOT6150_WS_PHS_END_DATE[] WS_PHS_END_DATE = new LNOT6150_WS_PHS_END_DATE[5];
    short WS_PHS_NUM = 0;
    short WS_MTHS = 0;
    char WS_PHS_LAST_MONTH_FLG = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    LNCPCFTA LNCPCFTA = new LNCPCFTA();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCCLDT SCCCLDT = new SCCCLDT();
    LNRCONT LNRCONT = new LNRCONT();
    LNRICTL LNRICTL = new LNRICTL();
    LNRAPRD LNRAPRD = new LNRAPRD();
    LNRSEAJ LNRSEAJ = new LNRSEAJ();
    LNRPAIP LNRPAIP = new LNRPAIP();
    LNRPLPI LNRPLPI = new LNRPLPI();
    LNRBALZ LNRBALZ = new LNRBALZ();
    LNRTRAN LNRTRAN = new LNRTRAN();
    LNRLOAN LNRLOAN = new LNRLOAN();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    LNB6150_AWA_6150 LNB6150_AWA_6150;
    public LNOT6150() {
        for (int i=0;i<5;i++) WS_PHS_END_DATE[i] = new LNOT6150_WS_PHS_END_DATE();
    }
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNOT6150 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB6150_AWA_6150>");
        LNB6150_AWA_6150 = (LNB6150_AWA_6150) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_DATA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_FUNC_CD);
        if (WS_FUNC_CD == '0') {
            B200_ADD_SEAJ_INF();
            if (pgmRtn) return;
        } else if (WS_FUNC_CD == '1') {
            B300_DEL_ALL_SEAJ_INF();
            if (pgmRtn) return;
        } else if (WS_FUNC_CD == '2') {
            B400_DEL_PART_SEAJ_INF();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_FUNC_CD_VAL_ERR, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B500_DEL_UNPAID_PLPI_INF();
        if (pgmRtn) return;
        B600_ADD_TRAN_INF();
        if (pgmRtn) return;
        B700_ADD_HIS_INF();
        if (pgmRtn) return;
    }
    public void B100_CHECK_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNB6150_AWA_6150.FUNC_CD);
        CEP.TRC(SCCGWA, LNB6150_AWA_6150.CONT_NO);
        CEP.TRC(SCCGWA, LNB6150_AWA_6150.FLG);
        CEP.TRC(SCCGWA, LNB6150_AWA_6150.DATE_ARY[1-1].YEAR);
        CEP.TRC(SCCGWA, LNB6150_AWA_6150.DATE_ARY[1-1].PERD);
        if (LNB6150_AWA_6150.CONT_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CTA_NO_M_INPUT, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNB6150_AWA_6150.FUNC_CD == ' ') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_FUNC_CD_M_INPUT, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_FUNC_CD = LNB6150_AWA_6150.FUNC_CD;
        if (WS_FUNC_CD == '0' 
            || WS_FUNC_CD == '2') {
            if (LNB6150_AWA_6150.FLG == ' ') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_CIRCUL_FLG_M_INPUT, WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_CIRCUL_FLG = LNB6150_AWA_6150.FLG;
            if ((WS_CIRCUL_FLG != 'Y' 
                && WS_CIRCUL_FLG != 'N')) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_CIRCUL_FLG_VAL_ERR, WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (LNB6150_AWA_6150.DATE_ARY[1-1].PERD == 0) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_DELAY_MONTH_M_INPUT, WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            for (WS_I = 1; WS_I <= 12 
                && LNB6150_AWA_6150.DATE_ARY[WS_I-1].PERD != 0; WS_I += 1) {
                if (WS_CIRCUL_FLG == 'Y' 
                    && LNB6150_AWA_6150.DATE_ARY[WS_I-1].YEAR != 0) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_CANNOT_IPT_YEAR, WS_ERR_MSG);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (WS_CIRCUL_FLG == 'N' 
                    && LNB6150_AWA_6150.DATE_ARY[WS_I-1].YEAR == 0) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_MUST_IPT_YEAR, WS_ERR_MSG);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        IBS.init(SCCGWA, LNRCONT);
        LNRCONT.KEY.CONTRACT_NO = LNB6150_AWA_6150.CONT_NO;
        T000_READ_LNTCONT();
        if (pgmRtn) return;
        if (LNRCONT.CTA_STS != K_CTA_STS_NORMAL) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_CTA_STS_MUST_NORMAL, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (!LNRCONT.CONTRACT_TYPE.equalsIgnoreCase(K_CONT_TYP_CLDD)) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_CONTRACT_TYPE_MCLDD, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNRCONT.MAT_DATE <= SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MAT_DT_GE_AC_DT, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNRICTL);
        LNRICTL.KEY.CONTRACT_NO = LNB6150_AWA_6150.CONT_NO;
        CEP.TRC(SCCGWA, LNRICTL.KEY.CONTRACT_NO);
        LNRICTL.KEY.SUB_CTA_NO = 0;
        T000_READUPD_LNTICTL();
        if (pgmRtn) return;
        WS_CTL_STSW = LNRICTL.CTL_STSW;
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
            || LNRICTL.CTL_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
            || LNRICTL.CTL_STSW.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1") 
            || LNRICTL.CTL_STSW.substring(47 - 1, 47 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_STS_CONNOT_DELAY, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNB6150_AWA_6150.FLG);
        CEP.TRC(SCCGWA, WS_FUNC_CD);
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        CEP.TRC(SCCGWA, LNRICTL.CTL_STSW.substring(29 - 1, 29 + 1 - 1));
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if ((WS_FUNC_CD == '1' 
            || WS_FUNC_CD == '2') 
            && LNRICTL.CTL_STSW.substring(29 - 1, 29 + 1 - 1).equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_STS_NOT_DELAY, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCPCFTA);
        LNCPCFTA.BR_GP[1-1].BR = LNRCONT.BOOK_BR;
        LNCPCFTA.BR_GP[2-1].BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_LNZPCFTA();
        if (pgmRtn) return;
        if (LNCPCFTA.FTA_FLG == 'Y') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_FTA_MUST_SAME, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNRAPRD);
        LNRAPRD.KEY.CONTRACT_NO = LNB6150_AWA_6150.CONT_NO;
        CEP.TRC(SCCGWA, LNRAPRD.KEY.CONTRACT_NO);
        T000_READ_LNTAPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNRAPRD.PAY_MTH);
        if (LNRAPRD.PAY_MTH != K_PAY_MTH_BLH) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PAY_MTH_M_BLH, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNRLOAN);
        LNRLOAN.KEY.CONTRACT_NO = LNB6150_AWA_6150.CONT_NO;
        T000_READ_LNTLOAN();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNRPAIP);
        LNRPAIP.KEY.CONTRACT_NO = LNB6150_AWA_6150.CONT_NO;
        LNRPAIP.KEY.SUB_CTA_NO = 0;
        T100_STARTBR_LNTPAIP();
        if (pgmRtn) return;
        T000_READNEXT_LNTPAIP();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PAIP_NOTFND, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        for (WS_I = 1; SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && WS_I <= 5; WS_I += 1) {
            if (LNRPAIP.PERD_UNIT != K_CAL_PERU_MONTH) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_CAL_PERU_M_MONTH, WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_PHS_NUM += 1;
            if (WS_PHS_NUM == 1) {
                LNRPAIP.PHS_TOT_TERM -= 1;
            }
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = LNRLOAN.FST_CAL_DT;
            WS_MTHS = (short) (LNRPAIP.PERD * LNRPAIP.PHS_TOT_TERM + WS_MTHS);
            SCCCLDT.MTHS = WS_MTHS;
            CEP.TRC(SCCGWA, SCCCLDT.DATE1);
            CEP.TRC(SCCGWA, SCCCLDT.MTHS);
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            WS_PHS_END_DATE[WS_I-1].WS_PHS_END_DT = SCCCLDT.DATE2;
