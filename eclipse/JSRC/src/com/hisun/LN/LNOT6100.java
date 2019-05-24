package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT6100 {
    int JIBS_tmp_int;
    DBParm LNTCONT_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_HIS_RMKS = "SET NON-ACCRUAL STATUS";
    String K_HIS_CPB_NM = "LNCHSARU";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_READ_LNTCONT_FLG = ' ';
    char WS_LSTS_FLG = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNCSSCI LNCSSCI = new LNCSSCI();
    LNCSSCO LNCSSCO = new LNCSSCO();
    LNCSSCO LNCSSCN = new LNCSSCO();
    LNCICIQ LNCICIQ = new LNCICIQ();
    LNCF16 LNCF16 = new LNCF16();
    LNCLOANM LNCLOANM = new LNCLOANM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    LNCPPMQ LNCPPMQ = new LNCPPMQ();
    LNRCONT LNRCONT = new LNRCONT();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNRICTL LNRICTL = new LNRICTL();
    LNCRICTL LNCRICTL = new LNCRICTL();
    LNCHSARU LNCHSARU = new LNCHSARU();
    LNCHSARU LNCSARUO = new LNCHSARU();
    LNCHSARU LNCSARUN = new LNCHSARU();
    LNCPCFTA LNCPCFTA = new LNCPCFTA();
    LNCSCQIF LNCSCQIF = new LNCSCQIF();
    LNCIGVCY LNCIGVCY = new LNCIGVCY();
    SCCGWA SCCGWA;
    LNB6100_AWA_6100 LNB6100_AWA_6100;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNOT6100 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB6100_AWA_6100>");
        LNB6100_AWA_6100 = (LNB6100_AWA_6100) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNB6100_AWA_6100.CTA_NO);
        if (LNB6100_AWA_6100.CTA_NO.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            if (LNB6100_AWA_6100.CTA_NO.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(LNB6100_AWA_6100.CTA_NO);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNRCONT);
        CEP.TRC(SCCGWA, LNB6100_AWA_6100.CTA_NO);
        LNRCONT.KEY.CONTRACT_NO = LNB6100_AWA_6100.CTA_NO;
        T000_READ_CONT();
        if (pgmRtn) return;
        if (WS_READ_LNTCONT_FLG == 'N') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_CONT_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (!LNRCONT.CONTRACT_TYPE.equalsIgnoreCase("CLDD")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_NON_CLDD_PROD;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '4';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNB6100_AWA_6100.CTA_NO;
        LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.CTL_STSW.substring(11 - 1, 11 + 1 - 1));
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.CTL_STSW.substring(48 - 1, 48 + 1 - 1));
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.CTL_STSW.substring(4 - 1, 4 + 1 - 1));
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.CTL_STSW.substring(8 - 1, 8 + 1 - 1));
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.CTL_STSW.substring(71 - 1, 71 + 1 - 1));
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.CTL_STSW.substring(19 - 1, 19 + 1 - 1));
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.CTL_STSW.substring(52 - 1, 52 + 1 - 1));
        B010_01_CHK_LOAN_STSW();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_LSTS_FLG);
        if (WS_LSTS_FLG == 'Y') {
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW.substring(48 - 1, 48 + 1 - 1).equalsIgnoreCase("1") 
                || LNCICTLM.REC_DATA.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
                || LNCICTLM.REC_DATA.CTL_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
                || LNCICTLM.REC_DATA.CTL_STSW.substring(71 - 1, 71 + 1 - 1).equalsIgnoreCase("1") 
                || LNCICTLM.REC_DATA.CTL_STSW.substring(19 - 1, 19 + 1 - 1).equalsIgnoreCase("1") 
                || LNCICTLM.REC_DATA.CTL_STSW.substring(52 - 1, 52 + 1 - 1).equalsIgnoreCase("1") 
                || LNCICTLM.REC_DATA.CTL_STSW.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1") 
                || LNCICTLM.REC_DATA.CTL_STSW.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("2") 
                || LNCICTLM.REC_DATA.CTL_STSW.substring(37 - 1, 37 + 1 - 1).equalsIgnoreCase("1") 
                || LNCICTLM.REC_DATA.CTL_STSW.substring(38 - 1, 38 + 1 - 1).equalsIgnoreCase("1") 
                || LNCICTLM.REC_DATA.CTL_STSW.substring(39 - 1, 39 + 1 - 1).equalsIgnoreCase("1") 
                || LNCICTLM.REC_DATA.CTL_STSW.substring(44 - 1, 44 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN1690;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            B001_CHK_FTA_TYP();
            if (pgmRtn) return;
        }
    }
    public void B001_CHK_FTA_TYP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCPCFTA);
        LNCPCFTA.BR_GP[1-1].BR = LNRCONT.BOOK_BR;
        LNCPCFTA.BR_GP[2-1].BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, LNCPCFTA.BR_GP[1-1].BR);
        CEP.TRC(SCCGWA, LNCPCFTA.BR_GP[2-1].BR);
        S000_CALL_LNZPCFTA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCPCFTA.BR_GP[1-1].BR);
        CEP.TRC(SCCGWA, LNCPCFTA.BR_GP[2-1].BR);
        if (LNCPCFTA.FTA_FLG == 'Y') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_FTA_NOT_SAME;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_01_CHK_LOAN_STSW() throws IOException,SQLException,Exception {
        WS_LSTS_FLG = 'Y';
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
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(31 - 1, 31 + 1 - 1).equalsIgnoreCase("0") 
            || LNRICTL.CTL_STSW.substring(47 - 1, 47 + 1 - 1).equalsIgnoreCase("1") 
            || LNRICTL.CTL_STSW.substring(57 - 1, 57 + 1 - 1).equalsIgnoreCase("1") 
            || LNRICTL.CTL_STSW.substring(62 - 1, 62 + 1 - 1).equalsIgnoreCase("1") 
            || LNRICTL.CTL_STSW.substring(63 - 1, 63 + 1 - 1).equalsIgnoreCase("1") 
            || LNRICTL.CTL_STSW.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1") 
            || LNRICTL.CTL_STSW.substring(70 - 1, 70 + 1 - 1).equalsIgnoreCase("1")) {
            WS_LSTS_FLG = 'N';
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNRCONT.CONTRACT_TYPE);
        CEP.TRC(SCCGWA, LNRCONT.CTA_STS);
        if (LNRCONT.CONTRACT_TYPE.equalsIgnoreCase("CLDD")) {
            LNCICTLM.FUNC = '2';
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 11 - 1) + "1" + LNCICTLM.REC_DATA.CTL_STSW.substring(11 + 1 - 1);
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 16 - 1) + "2" + LNCICTLM.REC_DATA.CTL_STSW.substring(16 + 1 - 1);
            S000_CALL_LNZICTLM();
            if (pgmRtn) return;
            IBS.init(SCCGWA, LNCLOANM);
            LNCLOANM.FUNC = '4';
            LNCLOANM.REC_DATA.KEY.CONTRACT_NO = LNB6100_AWA_6100.CTA_NO;
            S000_CALL_LNZLOANM();
            if (pgmRtn) return;
            LNCLOANM.FUNC = '2';
            LNCLOANM.REC_DATA.TRANS_NACCR_DATE = SCCGWA.COMM_AREA.AC_DATE;
            LNCLOANM.REC_DATA.KEY.CONTRACT_NO = LNB6100_AWA_6100.CTA_NO;
            S000_CALL_LNZLOANM();
            if (pgmRtn) return;
            R000_CTNR_PROCESS();
            if (pgmRtn) return;
            B100_WRITE_HIS_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_CTNR_PROCESS() throws IOException,SQLException,Exception {
        B150_GET_CQ_INF();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCIGVCY);
        LNCIGVCY.DATA.CNTR_TYPE = LNRCONT.CONTRACT_TYPE;
        LNCIGVCY.DATA.PROD_CODE_OLD = LNRCONT.PROD_CD;
        LNCIGVCY.DATA.CTA_NO = LNB6100_AWA_6100.CTA_NO;
        LNCIGVCY.DATA.SUB_CTA_NO = 0;
        LNCIGVCY.DATA.EVENT_CODE = "PN";
        LNCIGVCY.DATA.BR_OLD = LNRCONT.BOOK_BR;
        LNCIGVCY.DATA.CCY_INFO[1-1].CCY = LNRCONT.CCY;
        LNCIGVCY.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCIGVCY.DATA.CI_NO = LNCSCQIF.CI_NO;
        LNCIGVCY.DATA.STATUS = LNCICTLM.REC_DATA.CTL_STSW;
        LNCIGVCY.DATA.DEP_AC = LNB6100_AWA_6100.CTA_NO;
        S000_CALL_LNZIGVCY();
        if (pgmRtn) return;
    }
    public void S000_CALL_LNZIGVCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-I-GEN-VCY", LNCIGVCY);
        if (LNCIGVCY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCIGVCY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B100_WRITE_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSARUO);
        IBS.init(SCCGWA, LNCSARUN);
        LNCSARUN.CTA_NO = LNB6100_AWA_6100.CTA_NO;
        LNCSARUN.TR_VAL_DTE = SCCGWA.COMM_AREA.AC_DATE;
        LNCSARUN.ACRU_STS = 'Y';
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.REF_NO = LNB6100_AWA_6100.CTA_NO;
        BPCPNHIS.INFO.AC = LNB6100_AWA_6100.CTA_NO;
        B150_GET_CQ_INF();
        if (pgmRtn) return;
        BPCPNHIS.INFO.CI_NO = LNCSCQIF.CI_NO;
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.FMT_ID = K_HIS_CPB_NM;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_RMKS;
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID_LEN = 41;
        BPCPNHIS.INFO.OLD_DAT_PT = LNCSARUO;
        BPCPNHIS.INFO.NEW_DAT_PT = LNCSARUN;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B150_GET_CQ_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSCQIF);
        LNCSCQIF.CTA_NO = LNB6100_AWA_6100.CTA_NO;
        S000_CALL_LNZSCQIF();
        if (pgmRtn) return;
    }
    public void T000_READ_CONT() throws IOException,SQLException,Exception {
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        IBS.READ(SCCGWA, LNRCONT, LNTCONT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_READ_LNTCONT_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_READ_LNTCONT_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTCONT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-ICTL-MAINT", LNCICTLM);
        if (LNCICTLM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCICTLM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZPCFTA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-P-CHK-FTA-TYP", LNCPCFTA);
        if (LNCPCFTA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCPCFTA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZLOANM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-LOAN-MAINT", LNCLOANM);
        if (LNCLOANM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCLOANM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "999999999");
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSCQIF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-CONT-QUAL-INF", LNCSCQIF, true);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
