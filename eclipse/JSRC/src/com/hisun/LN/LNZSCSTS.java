package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSCSTS {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_SCSSCKDT = "SCSSCKDT";
    String K_HIS_CPB_NM1 = "LNCPGLM";
    String K_HIS_RMKS1 = "CHANGE CONTRACT STS";
    String WS_RC_APP = " ";
    short WS_RC_RTNCODE = 0;
    String WS_ERR_MSG = " ";
    short WS_I = 0;
    char WS_CNT_LOAN = ' ';
    char WS_CNT_GMST = ' ';
    char WS_CNT_ICTL = ' ';
    String WS_CTA_NO = " ";
    LNZSCSTS_WS_LOAN_CONT_AREA WS_LOAN_CONT_AREA = new LNZSCSTS_WS_LOAN_CONT_AREA();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNRLOAN LNRLOAN = new LNRLOAN();
    LNCRLOAN LNCRLOAN = new LNCRLOAN();
    LNCPSTS LNCPSTSO = new LNCPSTS();
    LNCPSTS LNCPSTSN = new LNCPSTS();
    LNCIPART LNCIPART = new LNCIPART();
    LNCT30 LNCT30 = new LNCT30();
    LNCBALLM LNCBALLM = new LNCBALLM();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCCONTM LNCCONTM = new LNCCONTM();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCBWEVT BPCBWEVT = new BPCBWEVT();
    BPCUGMC BPCUGMC = new BPCUGMC();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPGDIN BPCPGDIN = new BPCPGDIN();
    BPCPEBAS BPCPEBAS = new BPCPEBAS();
    BPCACLDD BPCACLDD = new BPCACLDD();
    LNCPPMQ LNCPPMQ = new LNCPPMQ();
    LNCSCGGM LNCSCGGM = new LNCSCGGM();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    LNCSCSTS LNCSCSTS;
    public void MP(SCCGWA SCCGWA, LNCSCSTS LNCSCSTS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSCSTS = LNCSCSTS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        C000_OUTPUT_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSCSTS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_PROC();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
        R000_WRITE_HIS_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_PROC() throws IOException,SQLException,Exception {
        LNRLOAN.KEY.CONTRACT_NO = LNCSCSTS.CTA_NO;
        LNCRLOAN.FUNC = 'I';
        S000_CALL_LNZRLOAN();
        if (pgmRtn) return;
        if (LNCRLOAN.RETURN_INFO == 'N') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_NOT_LOAN_CTA_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCSCSTS.OLD_CLASIFD == 'Y' 
            && LNCSCSTS.OPT == 'A') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_OLDCLASSI_M_NOT_ADD;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCSCSTS.OLD_CLASIFD == 'N' 
            && LNCSCSTS.OPT == 'D') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_STATUS_NOT_DEL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCSCSTS.CTA_NO;
        LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        LNCICTLM.FUNC = '3';
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        LNCSCSTS.STS_WORD = LNCICTLM.REC_DATA.CTL_STSW;
        B030_CHECK_STS_WORD();
        if (pgmRtn) return;
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        R000_GET_PRODCD_INFO();
        if (pgmRtn) return;
        B021_ENQ_PARTI_INFO();
        if (pgmRtn) return;
        B022_GUEST_CHG_STSW();
        if (pgmRtn) return;
        if (LNCIPART.DATA.IS_SYN == 'N') {
            B021_CHG_NOR_CLASSIFD();
            if (pgmRtn) return;
        } else {
            B022_SYN_LOAN_CSTS();
            if (pgmRtn) return;
        }
    }
    public void B022_GUEST_CHG_STSW() throws IOException,SQLException,Exception {
        if (LNCSCSTS.OPT == 'A' 
            && LNCSCSTS.OLD_CLASIFD == 'N') {
            LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCSCSTS.CTA_NO;
            LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
            LNCICTLM.FUNC = '4';
            S000_CALL_LNZICTLM();
            if (pgmRtn) return;
            LNCSCSTS.STS_WORD = LNCICTLM.REC_DATA.CTL_STSW;
            if (LNCSCSTS.STS_WORD == null) LNCSCSTS.STS_WORD = "";
            JIBS_tmp_int = LNCSCSTS.STS_WORD.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCSCSTS.STS_WORD += " ";
            JIBS_tmp_str[0] = "" + 1;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            LNCSCSTS.STS_WORD = LNCSCSTS.STS_WORD.substring(0, 35 - 1) + JIBS_tmp_str[0] + LNCSCSTS.STS_WORD.substring(35 + 1 - 1);
            LNCICTLM.REC_DATA.CTL_STSW = LNCSCSTS.STS_WORD;
            LNCICTLM.FUNC = '2';
            S000_CALL_LNZICTLM();
            if (pgmRtn) return;
        }
        if (LNCSCSTS.OPT == 'D' 
            && LNCSCSTS.OLD_CLASIFD == 'Y') {
            LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCSCSTS.CTA_NO;
            LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
            LNCICTLM.FUNC = '4';
            S000_CALL_LNZICTLM();
            if (pgmRtn) return;
            LNCSCSTS.STS_WORD = LNCICTLM.REC_DATA.CTL_STSW;
            if (LNCSCSTS.STS_WORD == null) LNCSCSTS.STS_WORD = "";
            JIBS_tmp_int = LNCSCSTS.STS_WORD.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCSCSTS.STS_WORD += " ";
            JIBS_tmp_str[0] = "" + 0;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            LNCSCSTS.STS_WORD = LNCSCSTS.STS_WORD.substring(0, 35 - 1) + JIBS_tmp_str[0] + LNCSCSTS.STS_WORD.substring(35 + 1 - 1);
            LNCICTLM.REC_DATA.CTL_STSW = LNCSCSTS.STS_WORD;
            LNCICTLM.FUNC = '2';
            S000_CALL_LNZICTLM();
            if (pgmRtn) return;
        }
    }
    public void B021_ENQ_PARTI_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCIPART.DATA);
        LNCIPART.DATA.LEVEL = 'R';
        LNCIPART.DATA.CONTRACT_NO = LNCSCSTS.CTA_NO;
        LNCIPART.DATA.FUNC = 'T';
        S000_CALL_LNZIPART();
        if (pgmRtn) return;
    }
    public void B022_SYN_LOAN_CSTS() throws IOException,SQLException,Exception {
        if (LNCSCSTS.OPT == 'A' 
            && LNCSCSTS.OLD_CLASIFD == 'N') {
            for (WS_I = 1; WS_I <= LNCIPART.DATA.CNT; WS_I += 1) {
                CEP.TRC(SCCGWA, WS_I);
                CEP.TRC(SCCGWA, LNCIPART.GROUP.get(WS_I-1).SEQ_NO);
                LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCSCSTS.CTA_NO;
                LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = LNCIPART.GROUP.get(WS_I-1).SEQ_NO;
                LNCICTLM.FUNC = '4';
                S000_CALL_LNZICTLM();
                if (pgmRtn) return;
                LNCSCSTS.STS_WORD = LNCICTLM.REC_DATA.CTL_STSW;
                if (LNCSCSTS.STS_WORD == null) LNCSCSTS.STS_WORD = "";
                JIBS_tmp_int = LNCSCSTS.STS_WORD.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCSCSTS.STS_WORD += " ";
                JIBS_tmp_str[0] = "" + 1;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                LNCSCSTS.STS_WORD = LNCSCSTS.STS_WORD.substring(0, 35 - 1) + JIBS_tmp_str[0] + LNCSCSTS.STS_WORD.substring(35 + 1 - 1);
                LNCICTLM.REC_DATA.CTL_STSW = LNCSCSTS.STS_WORD;
                LNCICTLM.FUNC = '2';
                S000_CALL_LNZICTLM();
                if (pgmRtn) return;
                if (LNCIPART.GROUP.get(WS_I-1).LC_BK_FLG == 'Y' 
                    && LNCIPART.GROUP.get(WS_I-1).ADJ_BK_FLG != 'I') {
                    B021_CHG_NOR_CLASSIFD();
                    if (pgmRtn) return;
                }
                if (LNCIPART.GROUP.get(WS_I-1).ADJ_BK_FLG == 'I') {
                    B021_ADJ_CHG_STATUS();
                    if (pgmRtn) return;
                }
            }
        }
        if (LNCSCSTS.OPT == 'D' 
            && LNCSCSTS.OLD_CLASIFD == 'Y') {
            for (WS_I = 1; WS_I <= LNCIPART.DATA.CNT; WS_I += 1) {
                CEP.TRC(SCCGWA, WS_I);
                CEP.TRC(SCCGWA, LNCIPART.GROUP.get(WS_I-1).SEQ_NO);
                LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCSCSTS.CTA_NO;
                LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = LNCIPART.GROUP.get(WS_I-1).SEQ_NO;
                LNCICTLM.FUNC = '4';
                S000_CALL_LNZICTLM();
                if (pgmRtn) return;
                LNCSCSTS.STS_WORD = LNCICTLM.REC_DATA.CTL_STSW;
                if (LNCSCSTS.STS_WORD == null) LNCSCSTS.STS_WORD = "";
                JIBS_tmp_int = LNCSCSTS.STS_WORD.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCSCSTS.STS_WORD += " ";
                JIBS_tmp_str[0] = "" + 0;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                LNCSCSTS.STS_WORD = LNCSCSTS.STS_WORD.substring(0, 35 - 1) + JIBS_tmp_str[0] + LNCSCSTS.STS_WORD.substring(35 + 1 - 1);
                LNCICTLM.REC_DATA.CTL_STSW = LNCSCSTS.STS_WORD;
                LNCICTLM.FUNC = '2';
                S000_CALL_LNZICTLM();
                if (pgmRtn) return;
                if (LNCIPART.GROUP.get(WS_I-1).LC_BK_FLG == 'Y' 
                    && LNCIPART.GROUP.get(WS_I-1).ADJ_BK_FLG != 'I') {
                    B021_CHG_NOR_CLASSIFD();
                    if (pgmRtn) return;
                }
                if (LNCIPART.GROUP.get(WS_I-1).ADJ_BK_FLG == 'I') {
                    B021_ADJ_CHG_STATUS();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void R000_GET_PRODCD_INFO() throws IOException,SQLException,Exception {
        LNRLOAN.KEY.CONTRACT_NO = LNCSCSTS.CTA_NO;
        LNCRLOAN.FUNC = 'I';
        S000_CALL_LNZRLOAN();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCCONTM);
        LNCCONTM.FUNC = '3';
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCSCSTS.CTA_NO;
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
        if (LNCRLOAN.RETURN_INFO == 'F') {
            LNCSCSTS.PROD_CODE = LNCCONTM.REC_DATA.PROD_CD;
        } else {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRLOAN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_CHECK_STS_WORD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCSCSTS.STS_WORD);
        if (LNCSCSTS.STS_WORD == null) LNCSCSTS.STS_WORD = "";
        JIBS_tmp_int = LNCSCSTS.STS_WORD.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCSCSTS.STS_WORD += " ";
        if (LNCSCSTS.STS_WORD == null) LNCSCSTS.STS_WORD = "";
        JIBS_tmp_int = LNCSCSTS.STS_WORD.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCSCSTS.STS_WORD += " ";
        if (LNCSCSTS.STS_WORD == null) LNCSCSTS.STS_WORD = "";
        JIBS_tmp_int = LNCSCSTS.STS_WORD.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCSCSTS.STS_WORD += " ";
        if (LNCSCSTS.STS_WORD == null) LNCSCSTS.STS_WORD = "";
        JIBS_tmp_int = LNCSCSTS.STS_WORD.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCSCSTS.STS_WORD += " ";
        if (LNCSCSTS.STS_WORD.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
            || LNCSCSTS.STS_WORD.substring(31 - 1, 31 + 1 - 1).equalsIgnoreCase("0") 
            || LNCSCSTS.STS_WORD.substring(47 - 1, 47 + 1 - 1).equalsIgnoreCase("1") 
            || LNCSCSTS.STS_WORD.substring(57 - 1, 57 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_CTA_STS_NOT_VALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B021_CHG_NOR_CLASSIFD() throws IOException,SQLException,Exception {
        B100_GET_COMM_TO_ACMODEL();
        if (pgmRtn) return;
        if (LNCSCSTS.OPT == 'A') {
            LNCSCSTS.EVENT_CODE = "NC";
            BPCPOEWA.DATA.EVENT_CODE = LNCSCSTS.EVENT_CODE;
        } else {
            LNCSCSTS.EVENT_CODE = "CN";
            BPCPOEWA.DATA.EVENT_CODE = LNCSCSTS.EVENT_CODE;
        }
        IBS.init(SCCGWA, LNCBALLM);
        LNCBALLM.FUNC = '3';
        LNCBALLM.REC_DATA.KEY.CONTRACT_NO = LNCSCSTS.CTA_NO;
        if (LNCIPART.DATA.IS_SYN == 'N') {
            LNCBALLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        } else {
            LNCBALLM.REC_DATA.KEY.SUB_CTA_NO = LNCIPART.GROUP.get(WS_I-1).SEQ_NO;
        }
        S000_CALL_LNZBALLM();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCBALLM.REC_DATA.LOAN_CONT_AREA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_LOAN_CONT_AREA);
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = WS_LOAN_CONT_AREA.REDEFINES12.WS_LOAN_CONT[2-1].WS_LOAN_BAL;
        BPCPOEWA.DATA.AMT_INFO[5-1].AMT = WS_LOAN_CONT_AREA.REDEFINES12.WS_LOAN_CONT[5-1].WS_LOAN_BAL;
        if (WS_LOAN_CONT_AREA.REDEFINES12.WS_LOAN_CONT[15-1].WS_LOAN_BAL < 0) {
            WS_LOAN_CONT_AREA.REDEFINES12.WS_LOAN_CONT[15-1].WS_LOAN_BAL = 0;
            WS_LOAN_CONT_AREA.WS_LOAN_CONT_AREA_TEXT1 = IBS.CLS2CPY(SCCGWA, WS_LOAN_CONT_AREA.REDEFINES12);
        }
        BPCPOEWA.DATA.AMT_INFO[13-1].AMT = WS_LOAN_CONT_AREA.REDEFINES12.WS_LOAN_CONT[15-1].WS_LOAN_BAL + WS_LOAN_CONT_AREA.REDEFINES12.WS_LOAN_CONT[20-1].WS_LOAN_BAL;
        BPCPOEWA.DATA.AMT_INFO[15-1].AMT = WS_LOAN_CONT_AREA.REDEFINES12.WS_LOAN_CONT[22-1].WS_LOAN_BAL;
        BPCPOEWA.DATA.AMT_INFO[23-1].AMT = WS_LOAN_CONT_AREA.REDEFINES12.WS_LOAN_CONT[32-1].WS_LOAN_BAL;
        BPCPOEWA.DATA.AMT_INFO[27-1].AMT = WS_LOAN_CONT_AREA.REDEFINES12.WS_LOAN_CONT[42-1].WS_LOAN_BAL;
        IBS.init(SCCGWA, BPCPEBAS);
        S000_CALL_BPZPEBAS();
        if (pgmRtn) return;
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void B021_ADJ_CHG_STATUS() throws IOException,SQLException,Exception {
        B100_GET_COMM_TO_ACMODEL();
        if (pgmRtn) return;
        if (LNCSCSTS.OPT == 'A') {
            LNCSCSTS.EVENT_CODE = "NC";
            BPCPOEWA.DATA.EVENT_CODE = LNCSCSTS.EVENT_CODE;
        } else {
            LNCSCSTS.EVENT_CODE = "CN";
            BPCPOEWA.DATA.EVENT_CODE = LNCSCSTS.EVENT_CODE;
        }
        IBS.init(SCCGWA, LNCBALLM);
        LNCBALLM.FUNC = '3';
        LNCBALLM.REC_DATA.KEY.CONTRACT_NO = LNCSCSTS.CTA_NO;
        if (LNCIPART.DATA.IS_SYN == 'N') {
            LNCBALLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        } else {
            LNCBALLM.REC_DATA.KEY.SUB_CTA_NO = LNCIPART.GROUP.get(WS_I-1).SEQ_NO;
        }
        S000_CALL_LNZBALLM();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCBALLM.REC_DATA.LOAN_CONT_AREA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_LOAN_CONT_AREA);
        CEP.TRC(SCCGWA, WS_LOAN_CONT_AREA.REDEFINES12.WS_LOAN_CONT[15-1].WS_LOAN_BAL);
        CEP.TRC(SCCGWA, WS_LOAN_CONT_AREA.REDEFINES12.WS_LOAN_CONT[20-1].WS_LOAN_BAL);
        if (WS_LOAN_CONT_AREA.REDEFINES12.WS_LOAN_CONT[15-1].WS_LOAN_BAL < 0) {
            WS_LOAN_CONT_AREA.REDEFINES12.WS_LOAN_CONT[15-1].WS_LOAN_BAL = 0;
            WS_LOAN_CONT_AREA.WS_LOAN_CONT_AREA_TEXT1 = IBS.CLS2CPY(SCCGWA, WS_LOAN_CONT_AREA.REDEFINES12);
        }
        BPCPOEWA.DATA.AMT_INFO[47-1].AMT = WS_LOAN_CONT_AREA.REDEFINES12.WS_LOAN_CONT[15-1].WS_LOAN_BAL + WS_LOAN_CONT_AREA.REDEFINES12.WS_LOAN_CONT[20-1].WS_LOAN_BAL;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[47-1].AMT);
        CEP.TRC(SCCGWA, WS_LOAN_CONT_AREA.REDEFINES12.WS_LOAN_CONT[22-1].WS_LOAN_BAL);
        CEP.TRC(SCCGWA, WS_LOAN_CONT_AREA.REDEFINES12.WS_LOAN_CONT[32-1].WS_LOAN_BAL);
        CEP.TRC(SCCGWA, WS_LOAN_CONT_AREA.REDEFINES12.WS_LOAN_CONT[42-1].WS_LOAN_BAL);
        BPCPOEWA.DATA.AMT_INFO[48-1].AMT = WS_LOAN_CONT_AREA.REDEFINES12.WS_LOAN_CONT[22-1].WS_LOAN_BAL + WS_LOAN_CONT_AREA.REDEFINES12.WS_LOAN_CONT[32-1].WS_LOAN_BAL + WS_LOAN_CONT_AREA.REDEFINES12.WS_LOAN_CONT[42-1].WS_LOAN_BAL;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[48-1].AMT);
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void B022_OVE_CHG_CLASSIFD() throws IOException,SQLException,Exception {
        B100_GET_COMM_TO_ACMODEL();
        if (pgmRtn) return;
        if (LNCSCSTS.OPT == 'A') {
            LNCSCSTS.EVENT_CODE = "NC";
            BPCPOEWA.DATA.EVENT_CODE = LNCSCSTS.EVENT_CODE;
        } else {
            LNCSCSTS.EVENT_CODE = "CN";
            BPCPOEWA.DATA.EVENT_CODE = LNCSCSTS.EVENT_CODE;
        }
        IBS.init(SCCGWA, LNCBALLM);
        LNCBALLM.FUNC = '3';
        LNCBALLM.REC_DATA.KEY.CONTRACT_NO = LNCSCSTS.CTA_NO;
        LNCBALLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        S000_CALL_LNZBALLM();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCBALLM.REC_DATA.LOAN_CONT_AREA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_LOAN_CONT_AREA);
        BPCPOEWA.DATA.AMT_INFO[5-1].AMT = WS_LOAN_CONT_AREA.REDEFINES12.WS_LOAN_CONT[2-1].WS_LOAN_BAL + WS_LOAN_CONT_AREA.REDEFINES12.WS_LOAN_CONT[5-1].WS_LOAN_BAL;
        BPCPOEWA.DATA.AMT_INFO[15-1].AMT = WS_LOAN_CONT_AREA.REDEFINES12.WS_LOAN_CONT[15-1].WS_LOAN_BAL + WS_LOAN_CONT_AREA.REDEFINES12.WS_LOAN_CONT[22-1].WS_LOAN_BAL;
        BPCPOEWA.DATA.AMT_INFO[23-1].AMT = WS_LOAN_CONT_AREA.REDEFINES12.WS_LOAN_CONT[32-1].WS_LOAN_BAL;
        BPCPOEWA.DATA.AMT_INFO[27-1].AMT = WS_LOAN_CONT_AREA.REDEFINES12.WS_LOAN_CONT[42-1].WS_LOAN_BAL;
        IBS.init(SCCGWA, BPCPEBAS);
        S000_CALL_BPZPEBAS();
        if (pgmRtn) return;
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void B023_NOR_CHG_OVERDUE() throws IOException,SQLException,Exception {
        B100_GET_COMM_TO_ACMODEL();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCBALLM);
        LNCBALLM.FUNC = '3';
        LNCBALLM.REC_DATA.KEY.CONTRACT_NO = LNCSCSTS.CTA_NO;
        LNCBALLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        S000_CALL_LNZBALLM();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCBALLM.REC_DATA.LOAN_CONT_AREA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_LOAN_CONT_AREA);
        if (LNCSCSTS.OPT == 'A') {
            LNCSCSTS.EVENT_CODE = "NO";
            BPCPOEWA.DATA.EVENT_CODE = LNCSCSTS.EVENT_CODE;
            BPCPOEWA.DATA.AMT_INFO[5-1].AMT = WS_LOAN_CONT_AREA.REDEFINES12.WS_LOAN_CONT[2-1].WS_LOAN_BAL;
            BPCPOEWA.DATA.AMT_INFO[15-1].AMT = WS_LOAN_CONT_AREA.REDEFINES12.WS_LOAN_CONT[15-1].WS_LOAN_BAL;
        } else {
            LNCSCSTS.EVENT_CODE = "ON";
            BPCPOEWA.DATA.EVENT_CODE = LNCSCSTS.EVENT_CODE;
            BPCPOEWA.DATA.AMT_INFO[1-1].AMT = WS_LOAN_CONT_AREA.REDEFINES12.WS_LOAN_CONT[2-1].WS_LOAN_BAL;
            BPCPOEWA.DATA.AMT_INFO[13-1].AMT = WS_LOAN_CONT_AREA.REDEFINES12.WS_LOAN_CONT[15-1].WS_LOAN_BAL;
        }
        IBS.init(SCCGWA, BPCPEBAS);
        S000_CALL_BPZPEBAS();
        if (pgmRtn) return;
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void B100_GET_COMM_TO_ACMODEL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "CLDD";
        BPCPOEWA.DATA.PROD_CODE = LNCSCSTS.PROD_CODE;
        BPCPOEWA.DATA.AC_NO = LNCSCSTS.CTA_NO;
        BPCPOEWA.DATA.BR_OLD = LNCSCSTS.BK_CRE;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = LNCSCSTS.CCY;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.CI_NO = LNCSCSTS.CI_NO;
        BPCPOEWA.DATA.REF_NO = " ";
    }
    public void C000_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCT30);
        IBS.init(SCCGWA, SCCFMT);
        LNCT30.CTA_TYP = 'D';
        LNCT30.CTA_NO = LNCSCSTS.CTA_NO;
        LNCT30.BR = LNCSCSTS.BK_CRE;
        LNCT30.CI_NO = LNCSCSTS.CI_NO;
        LNCT30.CI_ENMS = LNCSCSTS.SHORT_NM;
        LNCT30.CITY_CD = LNCSCSTS.CITY_CD;
        LNCT30.CI_CNM = LNCSCSTS.FULL_NM;
        LNCT30.FILLER = 0X02;
        LNCT30.CCY = LNCSCSTS.CCY;
        LNCT30.O_CLASIFD = LNCSCSTS.OLD_CLASIFD;
        LNCT30.OPT = LNCSCSTS.OPT;
        LNCT30.REMARK = LNCSCSTS.REMARK;
        CEP.TRC(SCCGWA, LNCT30.CTA_NO);
        CEP.TRC(SCCGWA, LNCT30.CI_NO);
        CEP.TRC(SCCGWA, LNCT30.O_CLASIFD);
        CEP.TRC(SCCGWA, LNCT30.OPT);
        CEP.TRC(SCCGWA, LNCT30.REMARK);
        SCCFMT.FMTID = "LNT30";
        SCCFMT.DATA_PTR = LNCT30;
        SCCFMT.DATA_LEN = 465;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_WRITE_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCPSTSO);
        LNCPSTSO.BR = LNCSCSTS.BK_CRE;
        LNCPSTSO.CTA_NO = LNCSCSTS.CTA_NO;
        LNCPSTSO.PROD_CD = LNCSCSTS.PROD_CODE;
        LNCPSTSO.CCY = LNCSCSTS.CCY;
        LNCPSTSO.CLASIFD = LNCSCSTS.OLD_CLASIFD;
        LNCPSTSO.STS_WORD = LNCSCSTS.STS_WORD;
        LNCPSTSO.REMARK = LNCSCSTS.REMARK;
        IBS.init(SCCGWA, LNCPSTSN);
        LNCPSTSN.BR = LNCSCSTS.BK_CRE;
        LNCPSTSN.CTA_NO = LNCSCSTS.CTA_NO;
        LNCPSTSN.PROD_CD = LNCSCSTS.PROD_CODE;
        LNCPSTSN.CCY = LNCSCSTS.CCY;
        if (LNCSCSTS.OPT == 'A') {
            LNCPSTSN.CLASIFD = 'Y';
        } else {
            LNCPSTSN.CLASIFD = 'N';
        }
        LNCPSTSN.STS_WORD = LNCSCSTS.STS_WORD;
        LNCPSTSN.REMARK = LNCSCSTS.REMARK;
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.AC = LNCSCSTS.CTA_NO;
        BPCPNHIS.INFO.FMT_ID = K_HIS_CPB_NM1;
        BPCPNHIS.INFO.FMT_ID_LEN = 301;
        BPCPNHIS.INFO.TX_RMK = K_HIS_RMKS1;
        BPCPNHIS.INFO.CI_NO = LNCSCSTS.CI_NO;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_ID.TR_CODE);
        BPCPNHIS.INFO.TX_TOOL = "LN";
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.OLD_DAT_PT = LNCPSTSO;
        BPCPNHIS.INFO.NEW_DAT_PT = LNCPSTSN;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_LNZIPART() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-PARTI-INQ", LNCIPART);
        if (LNCIPART.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCIPART.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPEBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-BASIC-EWA", BPCPEBAS);
        if (BPCPEBAS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPEBAS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZBALLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-BALL-MAINT", LNCBALLM);
        if (LNCBALLM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCBALLM.RC);
            S000_ERR_MSG_PROC();
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
    public void S000_CALL_LNZCONTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-CONT-MAINT", LNCCONTM);
        if (LNCCONTM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCCONTM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_INQUIRE_LOAN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRLOAN);
        IBS.init(SCCGWA, LNRLOAN);
        LNCRLOAN.FUNC = 'I';
        LNRLOAN.KEY.CONTRACT_NO = WS_CTA_NO;
        S000_CALL_LNZRLOAN();
        if (pgmRtn) return;
    }
    public void S000_CALL_LNZRLOAN() throws IOException,SQLException,Exception {
        WS_CNT_LOAN = ' ';
        LNCRLOAN.REC_PTR = LNRLOAN;
        LNCRLOAN.REC_LEN = 217;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTLOAN", LNCRLOAN);
        if (LNCRLOAN.RETURN_INFO != 'F') {
            if (LNCRLOAN.RETURN_INFO == 'E' 
                || LNCRLOAN.RETURN_INFO == 'N') {
                WS_CNT_LOAN = 'Y';
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRLOAN.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
