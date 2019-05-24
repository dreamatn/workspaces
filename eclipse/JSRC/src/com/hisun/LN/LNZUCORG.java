package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZUCORG {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm LNTCONT_RD;
    DBParm LNTAGRE_RD;
    DBParm LNTICTL_RD;
    DBParm LNTPARS_RD;
    boolean pgmRtn = false;
    char K_PRODMOD_C = 'C';
    char K_PRODMOD_R = 'R';
    char K_PRODMOD_G = 'G';
    int WS_OWNER_BR = 0;
    int WS_BOOK_BR = 0;
    String WS_CTL_STSW = " ";
    char WS_PROD_MOD = ' ';
    LNZUCORG_WS_LOAN_CONT_AREA WS_LOAN_CONT_AREA = new LNZUCORG_WS_LOAN_CONT_AREA();
    char WS_AGRE_REC_FLG = ' ';
    char WS_CONT_REC_FLG = ' ';
    char WS_ICTL_REC_FLG = ' ';
    char WS_PARS_REC_FLG = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNRAGRE LNRAGRE = new LNRAGRE();
    LNRCONT LNRCONT = new LNRCONT();
    LNRICTL LNRICTL = new LNRICTL();
    LNRPARS LNRPARS = new LNRPARS();
    CICCUST CICCUST = new CICCUST();
    CICSACR CICSACR = new CICSACR();
    CICSACAC CICSACAC = new CICSACAC();
    LNCSCKPD LNCSCKPD = new LNCSCKPD();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCPQORG BPCPQORG = new BPCPQORG();
    LNCBALLM LNCBALLM = new LNCBALLM();
    SCCGWA SCCGWA;
    LNCUCORG LNCUCORG;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, LNCUCORG LNCUCORG) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCUCORG = LNCUCORG;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZUCORG return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (LNCUCORG.MIG_TYP == '2') {
            CEP.TRC(SCCGWA, LNCUCORG.VS_AC_FLG);
            B020_GET_AGRE_INFO();
            if (pgmRtn) return;
            B020_GET_CONT_INFO();
            if (pgmRtn) return;
            B020_GET_ICTL_INFO();
            if (pgmRtn) return;
            B020_GET_PROD_INFO();
            if (pgmRtn) return;
            if (WS_CONT_REC_FLG == 'Y' 
                && WS_ICTL_REC_FLG == 'Y') {
                B040_MIG_CONT_BR();
                if (pgmRtn) return;
                B050_MIG_ACR_BR();
                if (pgmRtn) return;
                B060_MIG_ACO_BR();
                if (pgmRtn) return;
                if (WS_CTL_STSW == null) WS_CTL_STSW = "";
                JIBS_tmp_int = WS_CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
                if (WS_CTL_STSW == null) WS_CTL_STSW = "";
                JIBS_tmp_int = WS_CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
                if (WS_CTL_STSW == null) WS_CTL_STSW = "";
                JIBS_tmp_int = WS_CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
                if (WS_CTL_STSW.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1") 
                    || WS_CTL_STSW.substring(13 - 1, 13 + 1 - 1).equalsIgnoreCase("1") 
                    || WS_CTL_STSW.substring(14 - 1, 14 + 1 - 1).equalsIgnoreCase("1")) {
                    B020_GET_PARS_INFO();
                    if (pgmRtn) return;
                    B070_MIG_PARS_BR();
                    if (pgmRtn) return;
                }
                if ((BPCPQORG.BBR != WS_OWNER_BR 
                    && WS_PROD_MOD != 'G') 
                    || (WS_PROD_MOD == 'G' 
                    && BPCPQORG.BBR != WS_OWNER_BR 
                    && WS_BOOK_BR != 43400)) {
                    B080_GEN_VCH_PROC();
                    if (pgmRtn) return;
                }
            }
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID MIG TYPE(" + LNCUCORG.MIG_TYP + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCUCORG.MIG_TYP);
        CEP.TRC(SCCGWA, LNCUCORG.LN_AC);
        CEP.TRC(SCCGWA, LNCUCORG.BR_OLD);
        CEP.TRC(SCCGWA, LNCUCORG.BR_NEW);
        if (LNCUCORG.MIG_TYP == ' ') {
            CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.LN_MIG_TYP_M_INPUT);
        }
        if (LNCUCORG.MIG_TYP == '1' 
            && LNCUCORG.CONTRACT_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.LN_CONT_NO_MUST_INPUT);
        }
        if (LNCUCORG.MIG_TYP == '2' 
            && LNCUCORG.LN_AC.trim().length() == 0) {
            CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_AC_MUST_INPUT);
        }
        if (LNCUCORG.BR_OLD == 0) {
            CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.LN_OLD_BR_M_INPUT);
        }
        if (LNCUCORG.BR_NEW == 0) {
            CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.LN_NEW_BR_M_INPUT);
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = LNCUCORG.BR_NEW;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQORG.BBR);
        CEP.TRC(SCCGWA, BPCPQORG.BRANCH_BR);
    }
    public void B020_GET_AGRE_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRAGRE);
        LNRAGRE.KEY.CONTRACT_NO = LNCUCORG.LN_AC;
        T000_READ_LNTAGRE();
        if (pgmRtn) return;
        LNCUCORG.CONTRACT_NO = LNRAGRE.PAPER_NO;
    }
    public void B020_GET_CONT_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCONT);
        LNRCONT.KEY.CONTRACT_NO = LNCUCORG.LN_AC;
        LNRCONT.DOMI_BR = LNCUCORG.BR_OLD;
        CEP.TRC(SCCGWA, LNRCONT.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRCONT.DOMI_BR);
        CEP.TRC(SCCGWA, LNRCONT.BOOK_BR);
        T000_READ_LNTCONT_UPD();
        if (pgmRtn) return;
        WS_OWNER_BR = LNRCONT.DOMI_BR;
        WS_BOOK_BR = LNRCONT.BOOK_BR;
        CEP.TRC(SCCGWA, LNRCONT.DOMI_BR);
        CEP.TRC(SCCGWA, LNRCONT.BOOK_BR);
    }
    public void B020_GET_ICTL_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRICTL);
        LNRICTL.KEY.CONTRACT_NO = LNCUCORG.LN_AC;
        CEP.TRC(SCCGWA, LNRICTL.KEY.CONTRACT_NO);
        T000_READ_LNTICTL();
        if (pgmRtn) return;
        WS_CTL_STSW = LNRICTL.CTL_STSW;
        CEP.TRC(SCCGWA, WS_CTL_STSW);
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if (WS_CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
            || WS_CTL_STSW.substring(48 - 1, 48 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CLOSED);
        }
    }
    public void B020_GET_PROD_INFO() throws IOException,SQLException,Exception {
        R000_INQ_PRD_INFO();
        if (pgmRtn) return;
        WS_PROD_MOD = LNCSCKPD.PROD_MOD;
        CEP.TRC(SCCGWA, LNCSCKPD.PROD_MOD);
        CEP.TRC(SCCGWA, WS_PROD_MOD);
    }
    public void B020_GET_PARS_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPARS);
        LNRPARS.KEY.CONTRACT_NO = LNCUCORG.CONTRACT_NO;
        LNRPARS.BOOK_BR = LNCUCORG.BR_OLD;
        CEP.TRC(SCCGWA, LNRPARS.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRPARS.BOOK_BR);
        T000_READ_LNTPARS_UPD();
        if (pgmRtn) return;
    }
    public void B040_MIG_CONT_BR() throws IOException,SQLException,Exception {
        if (WS_PROD_MOD == K_PRODMOD_G 
            && BPCPQORG.BBR != WS_OWNER_BR 
            && WS_BOOK_BR != 43400) {
            LNRCONT.DOMI_BR = LNCUCORG.BR_NEW;
        } else {
            LNRCONT.DOMI_BR = LNCUCORG.BR_NEW;
            LNRCONT.BOOK_BR = LNCUCORG.BR_NEW;
        }
        LNRCONT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRCONT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, LNRCONT.DOMI_BR);
        CEP.TRC(SCCGWA, LNRCONT.BOOK_BR);
        CEP.TRC(SCCGWA, LNRCONT.UPDTBL_DATE);
        CEP.TRC(SCCGWA, LNRCONT.UPDTBL_TLR);
        T000_REWRITE_LNTCONT();
        if (pgmRtn) return;
    }
    public void B050_MIG_ACR_BR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSACR);
        CICSACR.FUNC = 'M';
        CICSACR.DATA.AGR_NO = LNCUCORG.CONTRACT_NO;
        CICSACR.DATA.ENTY_TYP = '1';
        CICSACR.DATA.OPN_BR = LNCUCORG.BR_NEW;
        CICSACR.DATA.OWNER_BK = BPCPQORG.BRANCH_BR;
        CEP.TRC(SCCGWA, CICSACR.DATA.OPN_BR);
        CEP.TRC(SCCGWA, CICSACR.DATA.OWNER_BK);
        S000_CALL_CIZSACR();
        if (pgmRtn) return;
    }
    public void B060_MIG_ACO_BR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSACAC);
        CICSACAC.FUNC = 'M';
        CICSACAC.DATA.ACAC_NO = LNCUCORG.LN_AC;
        CEP.TRC(SCCGWA, LNCUCORG.LN_AC);
        CICSACAC.DATA.OPN_BR = LNCUCORG.BR_NEW;
        CICSACAC.DATA.OWNER_BK = BPCPQORG.BRANCH_BR;
        CEP.TRC(SCCGWA, CICSACAC.DATA.OPN_BR);
        CEP.TRC(SCCGWA, CICSACAC.DATA.OWNER_BK);
        S000_CALL_CIZSACAC();
        if (pgmRtn) return;
    }
    public void B070_MIG_PARS_BR() throws IOException,SQLException,Exception {
        LNRPARS.BOOK_BR = LNCUCORG.BR_NEW;
        LNRPARS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRPARS.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, LNRPARS.BOOK_BR);
        CEP.TRC(SCCGWA, LNRPARS.UPDTBL_DATE);
        CEP.TRC(SCCGWA, LNRPARS.UPDTBL_TLR);
        T000_REWRITE_LNTPARS();
        if (pgmRtn) return;
    }
    public void B080_GEN_VCH_PROC() throws IOException,SQLException,Exception {
        R000_INQ_CI_INFO();
        if (pgmRtn) return;
        R000_INQ_BALL_INFO();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = LNRCONT.CONTRACT_TYPE;
        BPCPOEWA.DATA.PROD_CODE = LNRCONT.PROD_CD;
        BPCPOEWA.DATA.EVENT_CODE = "BC";
        BPCPOEWA.DATA.BR_OLD = LNCUCORG.BR_OLD;
        BPCPOEWA.DATA.BR_NEW = LNCUCORG.BR_NEW;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.AC_NO = LNRCONT.KEY.CONTRACT_NO;
        BPCPOEWA.DATA.CI_NO = CICCUST.O_DATA.O_CI_NO;
        BPCPOEWA.DATA.CCY_INFO[01-1].CCY = LNRCONT.CCY;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AC_NO);
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if (!WS_CTL_STSW.substring(19 - 1, 19 + 1 - 1).equalsIgnoreCase("1") 
            && !WS_CTL_STSW.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1")) {
            if (WS_CTL_STSW == null) WS_CTL_STSW = "";
            JIBS_tmp_int = WS_CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
            if (!WS_CTL_STSW.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
                BPCPOEWA.DATA.AMT_INFO[01-1].AMT = WS_LOAN_CONT_AREA.REDEFINES8.WS_LOAN_CONT[02-1].WS_LOAN_BAL + WS_LOAN_CONT_AREA.REDEFINES8.WS_LOAN_CONT[05-1].WS_LOAN_BAL;
                BPCPOEWA.DATA.AMT_INFO[05-1].AMT = WS_LOAN_CONT_AREA.REDEFINES8.WS_LOAN_CONT[06-1].WS_LOAN_BAL;
                BPCPOEWA.DATA.AMT_INFO[13-1].AMT = WS_LOAN_CONT_AREA.REDEFINES8.WS_LOAN_CONT[15-1].WS_LOAN_BAL + WS_LOAN_CONT_AREA.REDEFINES8.WS_LOAN_CONT[20-1].WS_LOAN_BAL + WS_LOAN_CONT_AREA.REDEFINES8.WS_LOAN_CONT[22-1].WS_LOAN_BAL;
                BPCPOEWA.DATA.AMT_INFO[15-1].AMT = WS_LOAN_CONT_AREA.REDEFINES8.WS_LOAN_CONT[42-1].WS_LOAN_BAL;
                BPCPOEWA.DATA.AMT_INFO[21-1].AMT = WS_LOAN_CONT_AREA.REDEFINES8.WS_LOAN_CONT[52-1].WS_LOAN_BAL;
            }
            if (WS_CTL_STSW == null) WS_CTL_STSW = "";
            JIBS_tmp_int = WS_CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
            if (WS_CTL_STSW.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
                BPCPOEWA.DATA.AMT_INFO[01-1].AMT = WS_LOAN_CONT_AREA.REDEFINES8.WS_LOAN_CONT[02-1].WS_LOAN_BAL + WS_LOAN_CONT_AREA.REDEFINES8.WS_LOAN_CONT[06-1].WS_LOAN_BAL + WS_LOAN_CONT_AREA.REDEFINES8.WS_LOAN_CONT[05-1].WS_LOAN_BAL;
                BPCPOEWA.DATA.AMT_INFO[29-1].AMT = WS_LOAN_CONT_AREA.REDEFINES8.WS_LOAN_CONT[65-1].WS_LOAN_BAL + WS_LOAN_CONT_AREA.REDEFINES8.WS_LOAN_CONT[66-1].WS_LOAN_BAL;
                BPCPOEWA.DATA.AMT_INFO[70-1].AMT = WS_LOAN_CONT_AREA.REDEFINES8.WS_LOAN_CONT[66-1].WS_LOAN_BAL;
                BPCPOEWA.DATA.AMT_INFO[17-1].AMT = WS_LOAN_CONT_AREA.REDEFINES8.WS_LOAN_CONT[24-1].WS_LOAN_BAL + WS_LOAN_CONT_AREA.REDEFINES8.WS_LOAN_CONT[48-1].WS_LOAN_BAL + WS_LOAN_CONT_AREA.REDEFINES8.WS_LOAN_CONT[58-1].WS_LOAN_BAL;
            }
        }
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if (WS_CTL_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
            BPCPOEWA.DATA.AMT_INFO[09-1].AMT = WS_LOAN_CONT_AREA.REDEFINES8.WS_LOAN_CONT[09-1].WS_LOAN_BAL + WS_LOAN_CONT_AREA.REDEFINES8.WS_LOAN_CONT[10-1].WS_LOAN_BAL;
        }
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if (WS_CTL_STSW.substring(19 - 1, 19 + 1 - 1).equalsIgnoreCase("1")) {
            BPCPOEWA.DATA.AMT_INFO[02-1].AMT = WS_LOAN_CONT_AREA.REDEFINES8.WS_LOAN_CONT[02-1].WS_LOAN_BAL + WS_LOAN_CONT_AREA.REDEFINES8.WS_LOAN_CONT[06-1].WS_LOAN_BAL;
            BPCPOEWA.DATA.AMT_INFO[23-1].AMT = WS_LOAN_CONT_AREA.REDEFINES8.WS_LOAN_CONT[15-1].WS_LOAN_BAL + WS_LOAN_CONT_AREA.REDEFINES8.WS_LOAN_CONT[20-1].WS_LOAN_BAL + WS_LOAN_CONT_AREA.REDEFINES8.WS_LOAN_CONT[22-1].WS_LOAN_BAL + WS_LOAN_CONT_AREA.REDEFINES8.WS_LOAN_CONT[42-1].WS_LOAN_BAL + WS_LOAN_CONT_AREA.REDEFINES8.WS_LOAN_CONT[52-1].WS_LOAN_BAL;
        }
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if (WS_CTL_STSW.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1")) {
            BPCPOEWA.DATA.AMT_INFO[12-1].AMT = WS_LOAN_CONT_AREA.REDEFINES8.WS_LOAN_CONT[02-1].WS_LOAN_BAL + WS_LOAN_CONT_AREA.REDEFINES8.WS_LOAN_CONT[06-1].WS_LOAN_BAL;
            BPCPOEWA.DATA.AMT_INFO[39-1].AMT = WS_LOAN_CONT_AREA.REDEFINES8.WS_LOAN_CONT[15-1].WS_LOAN_BAL + WS_LOAN_CONT_AREA.REDEFINES8.WS_LOAN_CONT[20-1].WS_LOAN_BAL + WS_LOAN_CONT_AREA.REDEFINES8.WS_LOAN_CONT[22-1].WS_LOAN_BAL + WS_LOAN_CONT_AREA.REDEFINES8.WS_LOAN_CONT[42-1].WS_LOAN_BAL + WS_LOAN_CONT_AREA.REDEFINES8.WS_LOAN_CONT[52-1].WS_LOAN_BAL;
        }
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void R000_INQ_CI_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'A';
        CICCUST.DATA.AGR_NO = LNCUCORG.CONTRACT_NO;
        CEP.TRC(SCCGWA, CICCUST.DATA.AGR_NO);
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
    }
    public void R000_INQ_PRD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSCKPD);
        LNCSCKPD.FUNC = '0';
        LNCSCKPD.PROD_CD = LNRCONT.PROD_CD;
        CEP.TRC(SCCGWA, LNCSCKPD.PROD_CD);
        S000_CALL_LNZSCKPD();
        if (pgmRtn) return;
    }
    public void R000_INQ_BALL_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCBALLM);
        IBS.init(SCCGWA, WS_LOAN_CONT_AREA);
        LNCBALLM.FUNC = '3';
        LNCBALLM.REC_DATA.KEY.CONTRACT_NO = LNCUCORG.LN_AC;
        CEP.TRC(SCCGWA, LNCUCORG.LN_AC);
        S000_CALL_LNZBALLM();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCBALLM.REC_DATA.LOAN_CONT_AREA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_LOAN_CONT_AREA);
    }
    public void S000_CALL_LNZBALLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-BALL-MAINT", LNCBALLM);
        if (LNCBALLM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCBALLM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCUCORG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZSACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACR", CICSACR);
        if (CICSACR.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICSACR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCUCORG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZSACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACAC", CICSACAC);
        if (CICSACAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICSACAC.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCUCORG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCUCORG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCUCORG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSCKPD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-CHK-PROD", LNCSCKPD);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCUCORG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTCONT_UPD() throws IOException,SQLException,Exception {
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        LNTCONT_RD.eqWhere = "CONTRACT_NO, DOMI_BR";
        LNTCONT_RD.upd = true;
        IBS.READ(SCCGWA, LNRCONT, LNTCONT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CONT_REC_FLG = 'Y';
        }
    }
    public void T000_REWRITE_LNTCONT() throws IOException,SQLException,Exception {
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        IBS.REWRITE(SCCGWA, LNRCONT, LNTCONT_RD);
    }
    public void T000_READ_LNTAGRE() throws IOException,SQLException,Exception {
        LNTAGRE_RD = new DBParm();
        LNTAGRE_RD.TableName = "LNTAGRE";
        LNTAGRE_RD.where = "CONTRACT_NO = :LNRAGRE.KEY.CONTRACT_NO";
        LNTAGRE_RD.fst = true;
        IBS.READ(SCCGWA, LNRAGRE, this, LNTAGRE_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_AGRE_REC_FLG = 'Y';
        }
    }
    public void T000_READ_LNTICTL() throws IOException,SQLException,Exception {
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        LNTICTL_RD.where = "CONTRACT_NO = :LNRICTL.KEY.CONTRACT_NO";
        IBS.READ(SCCGWA, LNRICTL, this, LNTICTL_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ICTL_REC_FLG = 'Y';
        }
    }
    public void T000_READ_LNTPARS_UPD() throws IOException,SQLException,Exception {
        LNTPARS_RD = new DBParm();
        LNTPARS_RD.TableName = "LNTPARS";
        LNTPARS_RD.eqWhere = "CONTRACT_NO, BOOK_BR";
        LNTPARS_RD.upd = true;
        IBS.READ(SCCGWA, LNRPARS, LNTPARS_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_PARS_REC_FLG = 'Y';
        }
    }
    public void T000_REWRITE_LNTPARS() throws IOException,SQLException,Exception {
        LNTPARS_RD = new DBParm();
        LNTPARS_RD.TableName = "LNTPARS";
        IBS.REWRITE(SCCGWA, LNRPARS, LNTPARS_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
