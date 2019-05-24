package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;
import com.hisun.BP.*;
import com.hisun.DC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT6130 {
    int JIBS_tmp_int;
    DBParm LNTCONT_RD;
    DBParm LNTICTL_RD;
    brParm LNTSETL_BR = new brParm();
    DBParm LNTSETL_RD;
    DBParm LNTTRAN_RD;
    String JIBS_tmp_str[] = new String[10];
    String K_FMT_CD = "LNW31";
    String K_HIS_RMKS = "CHANGE BORROWER";
    String WS_ERR_MSG = " ";
    String WS_AC_TYP = " ";
    int WS_BOOK_BR = 0;
    LNOT6130_WS_PAY_AC_INF[] WS_PAY_AC_INF = new LNOT6130_WS_PAY_AC_INF[5];
    short WS_CNT = 0;
    String WS_CTL_STSW = " ";
    String WS_SV_PROD_CD = " ";
    String WS_NEW_CI_NO = " ";
    String WS_OLD_CI_NO = " ";
    String WS_SV_PAPER_NO = " ";
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICCUST CICCUST = new CICCUST();
    LNCPPMQ LNCPPMQ = new LNCPPMQ();
    CICMACR CICMACR = new CICMACR();
    CICACCU CICACCU = new CICACCU();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    LNCBALLM LNCBALLM = new LNCBALLM();
    LNCH6130 LNCHNEW = new LNCH6130();
    LNCH6130 LNCHOLD = new LNCH6130();
    DCCPACTY DCCPACTY = new DCCPACTY();
    LNCPCFTA LNCPCFTA = new LNCPCFTA();
    BPCPCKPD BPCPCKPD = new BPCPCKPD();
    CICSACR CICSACR = new CICSACR();
    CIRACR CIRACR = new CIRACR();
    CIRBAS CIRBAS = new CIRBAS();
    CICCHCI CICCHCI = new CICCHCI();
    LNCSSTBL LNCSSTBL = new LNCSSTBL();
    CICQACRI CICQACRI = new CICQACRI();
    LNRCONT LNRCONT = new LNRCONT();
    LNRSETL LNRSETL = new LNRSETL();
    LNRTRAN LNRTRAN = new LNRTRAN();
    LNRICTL LNRICTL = new LNRICTL();
    LNRAGRE LNRAGRE = new LNRAGRE();
    LNCRAGRE LNCRAGRE = new LNCRAGRE();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    SCCGMSG SCCGMSG;
    LNB6130_AWA_6130 LNB6130_AWA_6130;
    public LNOT6130() {
        for (int i=0;i<5;i++) WS_PAY_AC_INF[i] = new LNOT6130_WS_PAY_AC_INF();
    }
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNOT6130 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB6130_AWA_6130>");
        LNB6130_AWA_6130 = (LNB6130_AWA_6130) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGMSG = (SCCGMSG) GWA_SC_AREA.MSG_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_UPDATE_SETL_INF();
        B300_UPDATE_CI_AC_LINK();
        B400_WRITE_TRAN_HIS();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (LNB6130_AWA_6130.CTA_NO.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_AC_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (LNB6130_AWA_6130.CI_NO.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_CI_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (LNB6130_AWA_6130.TR_VALDT == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_VAL_DT_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, LNB6130_AWA_6130.TR_VALDT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (LNB6130_AWA_6130.TR_VALDT != SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_VAL_DT_MEQ_AC_DT;
            S000_ERR_MSG_PROC();
        }
        if (LNB6130_AWA_6130.AC_TYP.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_ACT_TYP_M_I;
            S000_ERR_MSG_PROC();
        }
        if (LNB6130_AWA_6130.DD_AC.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_AC_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.DATA.AGR_NO = LNB6130_AWA_6130.DD_AC;
        CICQACRI.FUNC = 'A';
        S000_CALL_CIZQACRI();
        WS_NEW_CI_NO = CICQACRI.O_DATA.O_CI_NO;
        if (LNB6130_AWA_6130.AC_TYP.equalsIgnoreCase("02")) {
            WS_NEW_CI_NO = LNB6130_AWA_6130.CI_NO;
        }
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("IB")) {
            WS_AC_TYP = "03";
        } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
            WS_AC_TYP = "01";
        } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
            WS_AC_TYP = "05";
        } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
            WS_AC_TYP = "02";
        } else {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_AC_TYP_NOT_MATCH;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, "000000000000");
        CEP.TRC(SCCGWA, LNB6130_AWA_6130.AC_TYP);
        CEP.TRC(SCCGWA, WS_AC_TYP);
        if (WS_AC_TYP.equalsIgnoreCase("01")) {
            IBS.init(SCCGWA, LNCSSTBL);
            LNCSSTBL.CI_NO = LNB6130_AWA_6130.CI_NO;
            LNCSSTBL.DRAW_AC = LNB6130_AWA_6130.DD_AC;
            LNCSSTBL.S_CODE = "6130";
            S000_CALL_LNZSSTBL();
        }
        IBS.init(SCCGWA, LNRCONT);
        LNRCONT.KEY.CONTRACT_NO = LNB6130_AWA_6130.CTA_NO;
        T000_READUPD_LNTCONT();
        WS_SV_PROD_CD = LNRCONT.PROD_CD;
        if (LNRCONT.CONTRACT_TYPE.equalsIgnoreCase("CLGU")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_CONTRACT_TYPE;
            S000_ERR_MSG_PROC();
        }
        LNRCONT.LAST_TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        LNRCONT.LAST_TX_SEQ += 1;
        LNRCONT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRCONT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_LNTCONT();
        IBS.init(SCCGWA, CICCUST);
        CICCUST.O_DATA.O_CI_NO = LNB6130_AWA_6130.CI_NO;
        IBS.init(SCCGWA, LNCRAGRE);
        IBS.init(SCCGWA, LNRAGRE);
        LNCRAGRE.FUNC = 'I';
        LNRAGRE.KEY.CONTRACT_NO = LNB6130_AWA_6130.CTA_NO;
        S000_CALL_LNZRAGRE();
        WS_SV_PAPER_NO = LNRAGRE.PAPER_NO;
        CEP.TRC(SCCGWA, LNB6130_AWA_6130.CI_NO);
        CEP.TRC(SCCGWA, WS_NEW_CI_NO);
        if (WS_AC_TYP.equalsIgnoreCase("02")) {
        } else {
            if (!LNB6130_AWA_6130.CI_NO.equalsIgnoreCase(WS_NEW_CI_NO)) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.ERR_AC_CI_NOTMATCH;
                S000_ERR_MSG_PROC();
            }
        }
        IBS.init(SCCGWA, CICACCU);
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.DATA.AGR_NO = WS_SV_PAPER_NO;
        CICQACRI.FUNC = 'A';
        S000_CALL_CIZQACRI();
        WS_OLD_CI_NO = CICQACRI.O_DATA.O_CI_NO;
        CEP.TRC(SCCGWA, LNB6130_AWA_6130.CI_NO);
        CEP.TRC(SCCGWA, WS_OLD_CI_NO);
        if (LNB6130_AWA_6130.CI_NO.equalsIgnoreCase(WS_OLD_CI_NO)) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_CI_NO_NOT_CHG;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, LNCSSTBL);
        S000_CALL_LNZSSTBL();
        IBS.init(SCCGWA, BPCPCKPD);
        BPCPCKPD.PRDT_CODE = WS_SV_PROD_CD;
        BPCPCKPD.CI_NO = WS_NEW_CI_NO;
        S000_CALL_BPZPCKPD();
        if (BPCPCKPD.RC.RC_CODE != 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_PRODMOD_NOTEQ_CITYPE;
            S000_ERR_MSG_PROC();
            CEP.TRC(SCCGWA, WS_ERR_MSG);
        }
        IBS.init(SCCGWA, LNRICTL);
        LNRICTL.KEY.CONTRACT_NO = LNB6130_AWA_6130.CTA_NO;
        LNRICTL.KEY.SUB_CTA_NO = 0;
        T000_READUPD_LNTICTL();
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
        if (LNRICTL.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
            || LNRICTL.CTL_STSW.substring(47 - 1, 47 + 1 - 1).equalsIgnoreCase("1") 
            || LNRICTL.CTL_STSW.substring(48 - 1, 48 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_CTL_STS_NOT_ALLOW;
            S000_ERR_MSG_PROC();
        }
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        LNRICTL.CTL_STSW = LNRICTL.CTL_STSW.substring(0, 16 - 1) + "1" + LNRICTL.CTL_STSW.substring(16 + 1 - 1);
        T000_REWRITE_LNTICTL();
        IBS.init(SCCGWA, LNCPCFTA);
        LNCPCFTA.BR_GP[1-1].BR = LNRCONT.BOOK_BR;
        LNCPCFTA.BR_GP[2-1].BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        LNCPCFTA.BR_GP[3-1].BR = CICQACRI.O_DATA.O_OWNER_BK;
        S000_CALL_LNZPCFTA();
        CEP.TRC(SCCGWA, LNCPCFTA.BR_GP[1-1].BR);
        CEP.TRC(SCCGWA, LNCPCFTA.BR_GP[2-1].BR);
        CEP.TRC(SCCGWA, LNCPCFTA.BR_GP[3-1].BR);
        if (LNCPCFTA.FTA_FLG == 'Y') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_FTA_NOT_SAME;
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_UPDATE_SETL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRSETL);
        LNRSETL.KEY.CONTRACT_NO = LNB6130_AWA_6130.CTA_NO;
        LNRSETL.KEY.CI_TYPE = 'B';
        LNRSETL.KEY.PART_BK = " ";
        LNRSETL.KEY.CCY = LNRCONT.CCY;
        LNRSETL.KEY.SETTLE_TYPE = '2';
        T000_STARTBR_LNTSETL();
        T000_READNEXT_LNTSETL();
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_SETL_NOTFND;
            S000_ERR_MSG_PROC();
        }
        WS_CNT = 1;
        if (LNRSETL.MWHD_AC_FLG == 'Y') {
            while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
                && WS_CNT <= 5) {
                WS_PAY_AC_INF[WS_CNT-1].WS_PAY_AC = LNRSETL.AC;
                WS_PAY_AC_INF[WS_CNT-1].WS_PAY_AC_TYP = LNRSETL.AC_TYP;
                WS_PAY_AC_INF[WS_CNT-1].WS_PAY_AC_FLG = LNRSETL.AC_FLG;
                if (WS_CNT == 1) {
                    LNRSETL.KEY.SEQ_NO = 0;
                    LNRSETL.MWHD_AC_FLG = 'N';
                    LNRSETL.AC_TYP = WS_AC_TYP;
                    LNRSETL.AC = LNB6130_AWA_6130.DD_AC;
                    LNRSETL.AC_FLG = '0';
                    T000_REWRITE_LNTSETL();
                } else {
                    T000_DELETE_LNTSETL();
                }
                WS_CNT += 1;
                T000_READNEXT_LNTSETL();
            }
        } else {
            WS_PAY_AC_INF[WS_CNT-1].WS_PAY_AC = LNRSETL.AC;
            WS_PAY_AC_INF[WS_CNT-1].WS_PAY_AC_TYP = LNRSETL.AC_TYP;
            WS_PAY_AC_INF[WS_CNT-1].WS_PAY_AC_FLG = LNRSETL.AC_FLG;
            LNRSETL.AC_TYP = WS_AC_TYP;
            LNRSETL.AC = LNB6130_AWA_6130.DD_AC;
            LNRSETL.AC_FLG = '0';
            T000_REWRITE_LNTSETL();
            CEP.TRC(SCCGWA, LNRSETL.AC);
            CEP.TRC(SCCGWA, LNRSETL.AC_TYP);
            CEP.TRC(SCCGWA, LNRSETL.AC_FLG);
        }
        T000_ENDBR_LNTSETL();
    }
    public void B300_UPDATE_CI_AC_LINK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCHCI);
        CICCHCI.INPUT.AC_NO = WS_SV_PAPER_NO;
        CICCHCI.INPUT.OLD_CINO = WS_OLD_CI_NO;
        CICCHCI.INPUT.NEW_CINO = LNB6130_AWA_6130.CI_NO;
        S000_CALL_CIZCHCI();
        CEP.TRC(SCCGWA, LNB6130_AWA_6130.CTA_NO);
        CEP.TRC(SCCGWA, WS_OLD_CI_NO);
        CEP.TRC(SCCGWA, LNB6130_AWA_6130.CI_NO);
    }
    public void B400_WRITE_TRAN_HIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCBALLM);
        LNCBALLM.FUNC = '3';
        LNCBALLM.REC_DATA.KEY.CONTRACT_NO = LNB6130_AWA_6130.CTA_NO;
        S000_CALL_LNZBALLM();
        IBS.init(SCCGWA, LNRTRAN);
        LNRTRAN.KEY.CONTRACT_NO = LNB6130_AWA_6130.CTA_NO;
        LNRTRAN.KEY.REC_FLAG = 'B';
        LNRTRAN.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRTRAN.KEY.TR_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        LNRTRAN.TRAN_STS = 'N';
        if ("6130".trim().length() == 0) LNRTRAN.TR_CODE = 0;
        else LNRTRAN.TR_CODE = Short.parseShort("6130");
        LNRTRAN.TR_VAL_DTE = LNB6130_AWA_6130.TR_VALDT;
        LNRTRAN.TR_SEQ = LNRCONT.LAST_TX_SEQ;
        LNRTRAN.OS_BAL = LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[1-1].LOAN_BAL - LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[8-1].LOAN_BAL;
        LNRTRAN.LAST_F_VAL_DATE = LNRCONT.LAST_F_VAL_DATE;
        LNRTRAN.LOAN_STSW = WS_CTL_STSW;
        LNRTRAN.OLD_CI_NO = WS_OLD_CI_NO;
        LNRTRAN.REQ_FRM_NO = SCCGWA.COMM_AREA.REQ_SYSTEM;
        LNRTRAN.PAY_AC1 = WS_PAY_AC_INF[1-1].WS_PAY_AC;
        LNRTRAN.AC_TYP1 = WS_PAY_AC_INF[1-1].WS_PAY_AC_TYP;
        LNRTRAN.AC_FLG1 = WS_PAY_AC_INF[1-1].WS_PAY_AC_FLG;
        LNRTRAN.PAY_AC2 = WS_PAY_AC_INF[2-1].WS_PAY_AC;
        LNRTRAN.AC_TYP2 = WS_PAY_AC_INF[2-1].WS_PAY_AC_TYP;
        LNRTRAN.AC_FLG2 = WS_PAY_AC_INF[2-1].WS_PAY_AC_FLG;
        LNRTRAN.PAY_AC3 = WS_PAY_AC_INF[3-1].WS_PAY_AC;
        LNRTRAN.AC_TYP3 = WS_PAY_AC_INF[3-1].WS_PAY_AC_TYP;
        LNRTRAN.AC_FLG3 = WS_PAY_AC_INF[3-1].WS_PAY_AC_FLG;
        LNRTRAN.PAY_AC4 = WS_PAY_AC_INF[4-1].WS_PAY_AC;
        LNRTRAN.AC_TYP4 = WS_PAY_AC_INF[4-1].WS_PAY_AC_TYP;
        LNRTRAN.AC_FLG4 = WS_PAY_AC_INF[4-1].WS_PAY_AC_FLG;
        LNRTRAN.PAY_AC5 = WS_PAY_AC_INF[5-1].WS_PAY_AC;
        LNRTRAN.AC_TYP5 = WS_PAY_AC_INF[5-1].WS_PAY_AC_TYP;
        LNRTRAN.AC_FLG5 = WS_PAY_AC_INF[5-1].WS_PAY_AC_FLG;
        LNRTRAN.TR_MMO = SCCGWA.COMM_AREA.TR_MMO;
        LNRTRAN.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        LNRTRAN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRTRAN.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRTRAN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRTRAN.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_LNTRATN();
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.FMT_ID = "LNCH6130";
        BPCPNHIS.INFO.AC = LNB6130_AWA_6130.CTA_NO;
        BPCPNHIS.INFO.FMT_ID_LEN = 219;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_RMKS;
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        IBS.init(SCCGWA, LNCHOLD);
        LNCHOLD.CTA_NO = LNB6130_AWA_6130.CTA_NO;
        LNCHOLD.CI_NO = WS_OLD_CI_NO;
        for (WS_CNT = 1; WS_CNT <= 5; WS_CNT += 1) {
            LNCHOLD.AC_INF[WS_CNT-1].AC_TYP = WS_PAY_AC_INF[WS_CNT-1].WS_PAY_AC_TYP;
            LNCHOLD.AC_INF[WS_CNT-1].DD_AC = WS_PAY_AC_INF[WS_CNT-1].WS_PAY_AC;
        }
        IBS.init(SCCGWA, LNCHNEW);
        LNCHNEW.CTA_NO = LNB6130_AWA_6130.CTA_NO;
        LNCHNEW.CI_NO = LNB6130_AWA_6130.CI_NO;
        LNCHNEW.AC_INF[1-1].AC_TYP = LNB6130_AWA_6130.AC_TYP;
        LNCHNEW.AC_INF[1-1].DD_AC = LNB6130_AWA_6130.DD_AC;
        BPCPNHIS.INFO.OLD_DAT_PT = LNCHOLD;
        BPCPNHIS.INFO.NEW_DAT_PT = LNCHNEW;
        S000_CALL_BPZPNHIS();
    }
    public void T000_READUPD_LNTCONT() throws IOException,SQLException,Exception {
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        LNTCONT_RD.upd = true;
        IBS.READ(SCCGWA, LNRCONT, LNTCONT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_CONT_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_REWRITE_LNTCONT() throws IOException,SQLException,Exception {
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        IBS.REWRITE(SCCGWA, LNRCONT, LNTCONT_RD);
    }
    public void T000_READUPD_LNTICTL() throws IOException,SQLException,Exception {
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        LNTICTL_RD.upd = true;
        IBS.READ(SCCGWA, LNRICTL, LNTICTL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_ICTL_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_REWRITE_LNTICTL() throws IOException,SQLException,Exception {
        LNRICTL.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRICTL.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        IBS.REWRITE(SCCGWA, LNRICTL, LNTICTL_RD);
    }
    public void T000_STARTBR_LNTSETL() throws IOException,SQLException,Exception {
        LNTSETL_BR.rp = new DBParm();
        LNTSETL_BR.rp.TableName = "LNTSETL";
        LNTSETL_BR.rp.eqWhere = "CONTRACT_NO,CI_TYPE, PART_BK,CCY,SETTLE_TYPE";
        LNTSETL_BR.rp.upd = true;
        LNTSETL_BR.rp.order = "SEQ_NO";
        IBS.STARTBR(SCCGWA, LNRSETL, LNTSETL_BR);
    }
    public void T000_READNEXT_LNTSETL() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRSETL, this, LNTSETL_BR);
    }
    public void T000_ENDBR_LNTSETL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTSETL_BR);
    }
    public void T000_REWRITE_LNTSETL() throws IOException,SQLException,Exception {
        LNRSETL.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRSETL.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTSETL_RD = new DBParm();
        LNTSETL_RD.TableName = "LNTSETL";
        IBS.REWRITE(SCCGWA, LNRSETL, LNTSETL_RD);
    }
    public void T000_DELETE_LNTSETL() throws IOException,SQLException,Exception {
        LNTSETL_RD = new DBParm();
        LNTSETL_RD.TableName = "LNTSETL";
        IBS.DELETE(SCCGWA, LNRSETL, LNTSETL_RD);
    }
    public void T000_WRITE_LNTRATN() throws IOException,SQLException,Exception {
        LNTTRAN_RD = new DBParm();
        LNTTRAN_RD.TableName = "LNTTRAN";
        LNTTRAN_RD.errhdl = true;
        IBS.WRITE(SCCGWA, LNRTRAN, LNTTRAN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_DUPKEY;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZCHCI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CHCI", CICCHCI);
        if (CICCHCI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICCHCI.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCHCI.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_LNZBALLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-BALL-MAINT", LNCBALLM);
        if (LNCBALLM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCBALLM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_LNZPPMQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-PRD-PRM-INQ", LNCPPMQ);
        if (LNCPPMQ.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCPPMQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_LNZRAGRE() throws IOException,SQLException,Exception {
        LNCRAGRE.REC_PTR = LNRAGRE;
        LNCRAGRE.REC_LEN = 203;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTAGRE", LNCRAGRE);
        if (LNCRAGRE.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRAGRE.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_LNZPCFTA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-P-CHK-FTA-TYP", LNCPCFTA);
        if (LNCPCFTA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCPCFTA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICQACRI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPCKPD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-PRDT-COM-CHECK", BPCPCKPD);
    }
    public void S000_CALL_LNZSSTBL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-CHECK-CI-STS", LNCSSTBL);
        if (LNCSSTBL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSSTBL.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
