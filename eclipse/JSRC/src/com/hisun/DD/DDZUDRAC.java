package com.hisun.DD;

import com.hisun.BP.*;
import com.hisun.CI.*;
import com.hisun.SC.*;
import com.hisun.DC.*;
import com.hisun.FS.*;
import com.hisun.EA.*;

import java.text.DecimalFormat;

import java.io.IOException;
import java.sql.SQLException;

public class DDZUDRAC {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    DecimalFormat df;
    DBParm DDTMST_RD;
    DBParm DDTCCY_RD;
    DBParm DDTVCH_RD;
    DBParm DDTMCAD_RD;
    DBParm DDTADMN_RD;
    boolean pgmRtn = false;
    String K_STS_TABLE_APP = "DD";
    String K_CUS_DR_STS_TBL_P = "0002";
    String K_CUS_DR_STS_TBL_C = "0012";
    String K_CUS_DR_STS_TBL_CAN = "0010";
    String K_CUS_DR_STS_TD_TO_DD = "0013";
    String K_CUS_DR_STS_LN = "0014";
    String K_CUS_DR_STS_CLOSE = "0015";
    String CPN_SCSSCKDT = "SCSSCKDT";
    String CPN_UNI_CIZACCU = "CI-INQ-ACCU";
    String CPN_I_INQ_DDPRD = "DD-I-INQ-DDPRD";
    String CPN_BPZSEX = "BP-EX";
    String CPN_DCZUPSWM = "DC-U-PSW-MAINTAIN";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String K_AP_MMO = "DD";
    String K_LOAN_REPAY_MMO = "791";
    String K_PAY_CR_CARD_MMO = "GPX";
    String K_AGENT_PAY_FEE_MMO = "109";
    String K_CHQ_INVALID_DATE = "001";
    String K_CON_BANK_PROD_CD = "NDP00117";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    double WS_UOD_AMT = 0;
    double WS_AVL_BAL = 0;
    double WS_AVL_BAL2 = 0;
    double WS_HLD_PRI_HLD_AMT = 0;
    double WS_HLD_RAMT = 0;
    double WS_GPDR_AMT = 0;
    String WS_VS_AC = " ";
    char WS_VS_AC_FLG = ' ';
    short WS_I = 0;
    short WS_IDX = 0;
    char WS_OTHER_CITYPE = ' ';
    char WS_AC_CITYPE = ' ';
    String WS_BUY_CCY = " ";
    char WS_BUY_CCY_TYPE = ' ';
    double WS_BUY_AMT = 0;
    String WS_SELL_CCY = " ";
    char WS_SELL_CCY_TYPE = ' ';
    double WS_SELL_AMT = 0;
    int WS_OPEN_BR = 0;
    int WS_OPEN_DP = 0;
    String WS_FRG_IND = " ";
    String WS_STD_APP = " ";
    int WS_EXP_DATE = 0;
    int WS_TMP_DT = 0;
    String WS_SUB_AC = " ";
    String WS_OTHER_AC = " ";
    String WS_TEMP_STD_AC = " ";
    String WS_TEMP_SUB_AC = " ";
    String WS_TEMP_OTH_STD_AC = " ";
    String WS_OTH_VS_AC = " ";
    String WS_OTH_CI_NO = " ";
    char WS_CROS_DR_FLG = ' ';
    char WS_BV_TYPE = ' ';
    char WS_NOTCLEAR_FLG = ' ';
    String WS_N_CARD_NO = " ";
    char WS_CARD_ADSC_TYPE = ' ';
    String WS_CARD_PROD_CD = " ";
    String WS_SASB_CARD_NO = " ";
    String WS_VA_AC1 = " ";
    String WS_VA_AC2 = " ";
    double WS_MASTER_VAL_BAL = 0;
    double WS_DISPLAY_AMT = 0;
    String WS_DISPLAY_AMT_1 = " ";
    double WS_CURR_BAL = 0;
    double WS_COM_AUTO_AC_BAL = 0;
    double WS_AUTO_AC_TX_AMT = 0;
    int WS_BRANCH_1 = 0;
    int WS_BRANCH_2 = 0;
    int WS_BRANCH_3 = 0;
    double WS_NEED_AMT = 0;
    String WS_REL_AC = " ";
    String WS_PQORG_TRA_TYP = " ";
    char WS_FROM_CITYP = ' ';
    String WS_TR_AC = " ";
    char WS_PERS_FIN_FLG = 'N';
    char WS_VA_SAME_FLG = 'N';
    char WS_COMP_INTEL_FLG = 'N';
    char WS_GROUP_AC_FLG = 'N';
    char WS_REAL_TIME_FLG = 'N';
    char WS_NEED_HLD_AMT_FLG = 'N';
    char WS_CHK_DRAC_FLG = ' ';
    char WS_CHK_FHIS_FLG = ' ';
    char WS_IS_NOTCLEAR_FLG = ' ';
    char WS_NOT_STD_AC_FLG = ' ';
    char WS_CHK_AC_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    DDRCCY DDRCCY = new DDRCCY();
    DDRMST DDRMST = new DDRMST();
    DDRMST DDROMST = new DDRMST();
    DDRMST DDRFMST = new DDRMST();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDVMRAT DDVMRAT = new DDVMRAT();
    DDRMCAD DDRMCAD = new DDRMCAD();
    DCCIQHLD DCCIQHLD = new DCCIQHLD();
    DCCIQBAL DCCIQBAL = new DCCIQBAL();
    DDRVCH DDRVCH = new DDRVCH();
    DPCPARMP DPCPARMP = new DPCPARMP();
    DDCIMPAY DDCIMPAY = new DDCIMPAY();
    DDCIMCHQ DDCIMCHQ = new DDCIMCHQ();
    BPCIFHIS BPCIFHIS = new BPCIFHIS();
    BPRFHIST BPRFHIST = new BPRFHIST();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    BPCEX BPCEX = new BPCEX();
    FSCTLEG FSCTLEG = new FSCTLEG();
    CICACCU CICACCU = new CICACCU();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCCLDT SCCCLDT = new SCCCLDT();
    DCCUPSWM DCCUPSWM = new DCCUPSWM();
    DDCUZFMM DDCUZFMM = new DDCUZFMM();
    DDCPLIMT DDCPLIMT = new DDCPLIMT();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCPRMR BPCPRMR = new BPCPRMR();
    DCCUDACH DCCUDACH = new DCCUDACH();
    DCCURHLD DCCURHLD = new DCCURHLD();
    DCCPFTCK DCCPFTCK = new DCCPFTCK();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    DDCUSIPM DDCUSIPM = new DDCUSIPM();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DCCUCINF DCCUCINF = new DCCUCINF();
    BPCPQORG BPCPQORG = new BPCPQORG();
    DCCPCDCK DCCPCDCK = new DCCPCDCK();
    BPCIBUDT BPCIBUDT = new BPCIBUDT();
    DDCPCHQ DDCPCHQ = new DDCPCHQ();
    DCCUTCGD DCCUTCGD = new DCCUTCGD();
    DDCUGPDR DDCUGPDR = new DDCUGPDR();
    DDCUDRDD DDCUDRDD = new DDCUDRDD();
    DDCYERCK DDCYERCK = new DDCYERCK();
    DCCUIQVA DCCUIQVA = new DCCUIQVA();
    DCCUSBEI DCCUSBEI = new DCCUSBEI();
    DDCSACCH DDCSACCH = new DDCSACCH();
    BPCPRGST BPCPRGST = new BPCPRGST();
    CICCUST CICCUST = new CICCUST();
    CICCUST CICOCUST = new CICCUST();
    CICCUST CICKCUST = new CICCUST();
    DDCUADVT DDCUADVT = new DDCUADVT();
    CICQACAC CICQACAC = new CICQACAC();
    CICQACRI CICQACRI = new CICQACRI();
    CICQACRI CICFACRI = new CICQACRI();
    DDCPDZZH DDCPDZZH = new DDCPDZZH();
    EACUTRAN EACUTRAN = new EACUTRAN();
    DDRADMN DDRADMN = new DDRADMN();
    DDRRSAC DDRRSAC = new DDRRSAC();
    CICQACRL CICQACRL = new CICQACRL();
    CICCKLS CICCKLS = new CICCKLS();
    BPCPARMC BPCPARMC = new BPCPARMC();
    FSCIVMST FSCIVMST = new FSCIVMST();
    FSCUVSAT FSCUVSAT = new FSCUVSAT();
    SCCGWA SCCGWA;
    DDCUDRAC DDCUDRAC;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPCPORUP_DATA_INFO BPCPORUP;
    public void MP(SCCGWA SCCGWA, DDCUDRAC DDCUDRAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCUDRAC = DDCUDRAC;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, DDCUDRAC.HLD_NO);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZUDRAC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        CEP.TRC(SCCGWA, DDCUDRAC.TRT_CTLW);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B030_GET_CI_INF_PROC_N();
        if (pgmRtn) return;
        B015_CHECK_CI_LIST();
        if (pgmRtn) return;
        B080_GET_CI_INF_PROC();
        if (pgmRtn) return;
        B040_GET_OTHER_CARD_PROC();
        if (pgmRtn) return;
        B050_GET_AC_INF_PROC();
        if (pgmRtn) return;
        B070_CHK_AC_STS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCUDRAC.SIGN_FLG);
        CEP.TRC(SCCGWA, DDCUDRAC.DELAY_FLG);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (!JIBS_tmp_str[0].equalsIgnoreCase("111804") 
            && DDCUDRAC.SIGN_FLG != 'Y' 
            && DDCUDRAC.DELAY_FLG != 'Y') {
            B075_CHECK_CARD_STS();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDCUDRAC.CHK_PSW_FLG);
        B055_CHK_CANCEL_PROC();
        if (pgmRtn) return;
        B060_CHK_OTH_CARD_PROC();
        if (pgmRtn) return;
        B120_GET_CCY_INF_PROC();
        if (pgmRtn) return;
        B100_GET_PRD_INF_PROC();
        if (pgmRtn) return;
        B110_CHK_CHQ_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDVMPRD.VAL.TD_FLG);
        CEP.TRC(SCCGWA, DDCUDRAC.SUPPLY_FLG);
        if (!(DDVMPRD.VAL.TD_FLG == '0' 
            && DDCUDRAC.SUPPLY_FLG == 'N')) {
            B090_CHECK_STS_TBL_PROC();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B130_CHECK_AVA_BAL_PROC();
            if (pgmRtn) return;
        }
        B140_DR_ACCOUNT_PROC();
        if (pgmRtn) return;
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        CEP.TRC(SCCGWA, DDRCCY.STS_WORD.substring(50 - 1, 50 + 5 - 1));
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            && (DDVMPRD.VAL.TD_FLG == '0' 
            || DDRCCY.STS_WORD.substring(50 - 1, 50 + 1 - 1).equalsIgnoreCase("1") 
            || DDRCCY.STS_WORD.substring(51 - 1, 51 + 1 - 1).equalsIgnoreCase("1") 
            || DDRCCY.STS_WORD.substring(52 - 1, 52 + 1 - 1).equalsIgnoreCase("1") 
            || DDRCCY.STS_WORD.substring(53 - 1, 53 + 1 - 1).equalsIgnoreCase("1") 
            || DDRCCY.STS_WORD.substring(54 - 1, 54 + 1 - 1).equalsIgnoreCase("1")) 
            && DDCUDRAC.SUPPLY_FLG != 'N') {
            B130_01_MARGIN_SUPP_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
        CEP.TRC(SCCGWA, DDRCCY.CCAL_TOT_BAL);
        DDCUDRAC.ACAC = DDCUDRDD.ACAC;
        DDCUDRAC.TX_AMT = DDCUDRDD.TX_AMT;
        CEP.TRC(SCCGWA, DDCUDRAC.TX_AMT);
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, DDCUDRAC.AC);
        CEP.TRC(SCCGWA, DDCUDRAC.CCY);
        CEP.TRC(SCCGWA, DDCUDRAC.CCY_TYPE);
        CEP.TRC(SCCGWA, DDCUDRAC.TX_AMT);
        CEP.TRC(SCCGWA, DDCUDRAC.VAL_DATE);
        CEP.TRC(SCCGWA, DDCUDRAC.ID_TYPE);
        CEP.TRC(SCCGWA, DDCUDRAC.ID_NO);
        CEP.TRC(SCCGWA, DDCUDRAC.PSBK_NO);
        CEP.TRC(SCCGWA, DDCUDRAC.CARD_NO);
        CEP.TRC(SCCGWA, DDCUDRAC.CHQ_TYPE);
        CEP.TRC(SCCGWA, DDCUDRAC.CHQ_NO);
        CEP.TRC(SCCGWA, DDCUDRAC.PAY_PSWD);
        CEP.TRC(SCCGWA, DDCUDRAC.TX_TYPE);
        CEP.TRC(SCCGWA, DDCUDRAC.CLEAR_FLG);
        CEP.TRC(SCCGWA, DDCUDRAC.OTHER_AC);
        CEP.TRC(SCCGWA, DDCUDRAC.OTHER_CCY);
        CEP.TRC(SCCGWA, DDCUDRAC.OTHER_AMT);
        CEP.TRC(SCCGWA, DDCUDRAC.NARRATIVE);
        CEP.TRC(SCCGWA, DDCUDRAC.TX_MMO);
        CEP.TRC(SCCGWA, DDCUDRAC.TX_REF);
        CEP.TRC(SCCGWA, DDCUDRAC.REMARKS);
        CEP.TRC(SCCGWA, DDCUDRAC.TX_BAL_FLG);
        if (DDCUDRAC.AC.trim().length() == 0 
            && DDCUDRAC.CARD_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT);
        }
        if (DDCUDRAC.CCY.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_M_INPUT);
        }
        if (DDCUDRAC.CCY_TYPE == ' ' 
            && DDCUDRAC.CCY.equalsIgnoreCase("156") 
            && DDCUDRAC.AID.trim().length() == 0) {
            DDCUDRAC.CCY_TYPE = '1';
        }
        if (DDCUDRAC.CCY_TYPE == ' ' 
            && DDCUDRAC.AID.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_TYPE_M_INPUT);
        }
        if (DDCUDRAC.TX_AMT == 0 
            && DDCUDRAC.CLEAR_FLG != 'Y') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AMT_M_INPUT);
        }
        if (DDCUDRAC.VAL_DATE != 0) {
            CEP.TRC(SCCGWA, DDCUDRAC.REMARKS);
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = DDCUDRAC.VAL_DATE;
            SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
            SCSSCKDT1.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BAK_DT_INVALID;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            DDCUDRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (DDCUDRAC.VAL_DATE > SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BAK_DT_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCUDRAC.TX_TYPE != 'T' 
            && DDCUDRAC.TX_TYPE != 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_TC_FLG_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDCUDRAC.OTH_TX_TOOL);
        CEP.TRC(SCCGWA, DDCUDRAC.CARD_NO);
        if (DDCUDRAC.CARD_NO.equalsIgnoreCase(DDCUDRAC.OTH_TX_TOOL) 
            && DDCUDRAC.CARD_NO.trim().length() > 0) {
            DDCUDRAC.BANK_DR_FLG = 'Y';
        }
        if (DDCUDRAC.BANK_DR_FLG == ' ') {
            DDCUDRAC.BANK_DR_FLG = 'N';
        } else {
            if ((DDCUDRAC.BANK_DR_FLG != 'Y') 
                && (DDCUDRAC.BANK_DR_FLG != 'N')) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BANK_DR_FLG_INVALID;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, DDCUDRAC.CHK_PSW_FLG);
        if (DDCUDRAC.CHK_PSW_FLG == ' ') {
            DDCUDRAC.CHK_PSW_FLG = 'Y';
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQORG.FX_BUSI);
        if (!DDCUDRAC.CCY.equalsIgnoreCase("156") 
            && (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")) 
            && BPCPQORG.FX_BUSI.equalsIgnoreCase("00")) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_TR_BR_NO_FX_AUTH);
        }
        WS_PQORG_TRA_TYP = BPCPQORG.TRA_TYP;
    }
    public void B015_CHECK_CI_LIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCKLS);
        CICCKLS.DATA.AGR_NO = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        CEP.TRC(SCCGWA, CICCKLS.DATA.AGR_NO);
        CICCKLS.DATA.AP_TYPE = "200";
        CICCKLS.DATA.EXP_MMO = DDCUDRAC.TX_MMO;
        CICCKLS.DATA.ACAC_NO = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        CEP.TRC(SCCGWA, CICCKLS.DATA.ACAC_NO);
        S000_CALL_CIZCKLS();
        if (pgmRtn) return;
    }
    public void B030_CHECK_PASSWORD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUDRAC.BV_TYP);
        if (DDCUDRAC.BV_TYP == '1' 
            || ((DDCUDRAC.TRK_DATE2.trim().length() > 0 
            || DDCUDRAC.TRK_DATE3.trim().length() > 0) 
            && DDCUDRAC.BV_TYP != '2') 
            || (WS_BV_TYPE == '1' 
            && DDCUDRAC.BV_TYP == '3')) {
            IBS.init(SCCGWA, DCCPCDCK);
            if (DDCUDRAC.CHK_PSW == 'P') {
                DCCPCDCK.FUNC_CODE = 'P';
            } else if (DDCUDRAC.CHK_PSW == 'T') {
                DCCPCDCK.FUNC_CODE = 'T';
            } else if (DDCUDRAC.CHK_PSW == 'B') {
                DCCPCDCK.FUNC_CODE = 'B';
            } else if (DDCUDRAC.CHK_PSW == 'N') {
                DCCPCDCK.FUNC_CODE = 'N';
            } else {
            }
            DCCPCDCK.OLD_CARD_NO = DDCUDRAC.CARD_NO;
            DCCPCDCK.CARD_NO = WS_N_CARD_NO;
            DCCPCDCK.CARD_PSW = DDCUDRAC.PSWD;
            DCCPCDCK.TRK2_DAT = DDCUDRAC.TRK_DATE2;
            DCCPCDCK.TRK3_DAT = DDCUDRAC.TRK_DATE3;
        }
        if (DDCUDRAC.BV_TYP == '2' 
            || (DDCUDRAC.BV_TYP == '3' 
            && WS_BV_TYPE == '2')) {
            IBS.init(SCCGWA, DDCIMPAY);
            DDCIMPAY.PSWD_OLD = DDCUDRAC.PSWD;
            DDCIMPAY.CARD_NO = DDCUDRAC.CARD_NO;
            DDCIMPAY.NEW_CARD_NO = WS_SASB_CARD_NO;
            DDCIMPAY.AC = WS_TEMP_STD_AC;
            DDCIMPAY.FUNC = 'C';
            DDCIMPAY.PAY_MTH = '1';
        }
    }
    public void B040_GET_OTHER_CARD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUDRAC.OTHER_AC);
        if (DDCUDRAC.TX_TYPE == 'T' 
            && DDCUDRAC.OTHER_AC.trim().length() > 0 
            && CICFACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = CICFACRI.O_DATA.O_AGR_NO;
            CEP.TRC(SCCGWA, DDRMST.KEY.CUS_AC);
            T000_READ_DDTMST();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, DDRMST, DDROMST);
            WS_FRG_IND = DDRMST.FRG_IND;
        }
    }
    public void B050_GET_AC_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
            DDRMST.KEY.CUS_AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
            CEP.TRC(SCCGWA, DDRMST.KEY.CUS_AC);
            T000_READ_UPDATE_DDTMST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDRMST.AC_TYPE);
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            CEP.TRC(SCCGWA, DDRMST.AC_STS_WORD.substring(7 - 1, 7 + 1 - 1));
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            CEP.TRC(SCCGWA, DDRMST.AC_STS_WORD.substring(8 - 1, 8 + 1 - 1));
        }
        CEP.TRC(SCCGWA, DDRMST.AC_TYPE);
        CEP.TRC(SCCGWA, CICOCUST.O_DATA.O_CI_NO);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_NO);
        CEP.TRC(SCCGWA, CICFACRI.O_DATA.O_FRM_APP);
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (DDCUDRAC.TX_TYPE == 'T' 
            && DDRMST.AC_TYPE == 'B' 
            && !DDRMST.AC_STS_WORD.substring(23 - 1, 23 + 1 - 1).equalsIgnoreCase("1") 
            && DDCUDRAC.CCY.equalsIgnoreCase("156") 
            && (CICFACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI") 
            || (!CICFACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI") 
            && CICOCUST.O_DATA.O_CI_NO.trim().length() > 0 
            && !CICCUST.O_DATA.O_CI_NO.equalsIgnoreCase(CICOCUST.O_DATA.O_CI_NO))) 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("MID") 
            && !JIBS_tmp_str[1].equalsIgnoreCase("351700")) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.SVAC_AC_ONL_TO_SVAC);
        }
        CEP.TRC(SCCGWA, DDCUDRAC.CCY);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_TYP);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (DDCUDRAC.TX_TYPE == 'T' 
            && CICCUST.O_DATA.O_CI_TYP == '1' 
            && !DDCUDRAC.CCY.equalsIgnoreCase("156") 
            && (CICFACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI") 
            || (!CICFACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI") 
            && CICOCUST.O_DATA.O_CI_NO.trim().length() > 0 
            && !CICCUST.O_DATA.O_CI_NO.equalsIgnoreCase(CICOCUST.O_DATA.O_CI_NO))) 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("MID") 
            && !JIBS_tmp_str[1].equalsIgnoreCase("351700") 
            && SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.NO_SAME_CI_W);
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(49 - 1, 49 + 1 - 1).equalsIgnoreCase("1")) {
            WS_GROUP_AC_FLG = 'Y';
        }
        CEP.TRC(SCCGWA, DDRMST.TRF_DATE);
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            && GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE != SCCGWA.COMM_AREA.AC_DATE 
            && DDRMST.TRF_DATE != 0 
            && DDRMST.TRF_DATE >= GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_ITM_CHG);
        }
    }
    public void B055_CHK_CANCEL_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            R000_STARTBR_BPTFHIST();
            if (pgmRtn) return;
            R000_READNEXT_BPTFHIST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPRFHIST.DRCRFLG);
            CEP.TRC(SCCGWA, BPRFHIST.TX_MMO);
            CEP.TRC(SCCGWA, BPRFHIST.TX_STS);
            while (WS_CHK_FHIS_FLG != 'N') {
                R000_READNEXT_BPTFHIST();
                if (pgmRtn) return;
            }
            R000_ENDBR_BPTFHIST();
            if (pgmRtn) return;
        }
    }
    public void B060_CHK_OTH_CARD_PROC() throws IOException,SQLException,Exception {
        if (DDCUDRAC.TX_TYPE == 'T' 
            && DDRMST.CI_TYP != '1' 
            && DDCUDRAC.CARD_NO.trim().length() > 0 
            && DDCUDRAC.OTHER_AC.trim().length() > 0 
            && CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DCCUDACH);
            DCCUDACH.DATA.CARD_NO = DDCUDRAC.CARD_NO;
            CEP.TRC(SCCGWA, DCCUDACH.DATA.CARD_NO);
            if (DDCUDRAC.OTH_TX_TOOL.trim().length() > 0) {
                DCCUDACH.DATA.AC_NO = DDCUDRAC.OTH_TX_TOOL;
            } else {
                DCCUDACH.DATA.AC_NO = DDCUDRAC.OTHER_AC;
            }
            DCCUDACH.DATA.BANK_FLG = '1';
            DCCUDACH.DATA.BR = WS_OPEN_DP;
            CEP.TRC(SCCGWA, DCCUDACH.DATA.CARD_NO);
            CEP.TRC(SCCGWA, DCCUDACH.DATA.AC_NO);
            CEP.TRC(SCCGWA, DCCUDACH.DATA.BANK_FLG);
            CEP.TRC(SCCGWA, DCCUDACH.DATA.BR);
            S000_CALL_DCZUDACH();
            if (pgmRtn) return;
            if (DCCUDACH.DATA.FLG != 'Y') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.M_TO_TR_NOT_DIRE_AC;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B070_CHK_AC_STS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRMST.AC_STS);
        if (DDRMST.AC_STS == 'C') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_CLOSE);
        }
        if (DDRMST.AC_STS == 'O') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_NOT_ACTIVE);
        }
        if (DDRMST.AC_STS == 'V') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_NOT_CHECKED);
        }
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (DDRMST.EXP_DATE > 0 
            && DDRMST.EXP_DATE < SCCGWA.COMM_AREA.AC_DATE 
            && !JIBS_tmp_str[1].equalsIgnoreCase("115846")) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_EXPIRED);
        }
        IBS.init(SCCGWA, DCCUCINF);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_TYP);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR);
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_AC_REL);
        if (CICCUST.O_DATA.O_CI_TYP == '1' 
            && CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DC")) {
            DCCUCINF.CARD_NO = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        }
        if (CICCUST.O_DATA.O_CI_TYP == '2' 
            && CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD") 
            && CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("04")) {
            DCCUCINF.CARD_NO = DDCUDRAC.CARD_NO;
        }
        if (DCCUCINF.CARD_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, DCCUCINF.CARD_NO);
            S000_CALL_DCZUCINF();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DCCUCINF.CARD_STS);
            if (DCCUCINF.CARD_STS == 'C') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CARD_CLOSED);
            }
            WS_CARD_ADSC_TYPE = DCCUCINF.ADSC_TYPE;
            WS_CARD_PROD_CD = DCCUCINF.PROD_CD;
        } else {
            CEP.TRC(SCCGWA, DDRMST.CROS_DR_FLG);
            WS_CROS_DR_FLG = DDRMST.CROS_DR_FLG;
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            CEP.TRC(SCCGWA, DDRMST.AC_STS_WORD.substring(67 - 1, 67 + 1 - 1));
            CEP.TRC(SCCGWA, DDRMST.OWNER_BRDP);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(67 - 1, 67 + 1 - 1).equalsIgnoreCase("1") 
                && ((DDRMST.OWNER_BRDP == 320505002 
                && SCCGWA.COMM_AREA.BR_DP.TR_BRANCH == 706610003) 
                || (DDRMST.OWNER_BRDP == 706610003 
                && SCCGWA.COMM_AREA.BR_DP.TR_BRANCH == 320505002))) {
                WS_CROS_DR_FLG = '1';
            }
        }
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_STS);
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_STS == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_CLEARED);
        }
        if (DDCUDRAC.TX_TYPE == 'C' 
            && DDRMST.FRG_IND.equalsIgnoreCase("OSA")) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.M_OSA_AC_NOT_DR);
        }
        if (DDCUDRAC.TX_TYPE == 'T' 
            && (!DDRMST.FRG_IND.equalsIgnoreCase(WS_FRG_IND)) 
            && CICFACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD") 
            && (DDRMST.FRG_IND.equalsIgnoreCase("OSA") 
            || WS_FRG_IND.equalsIgnoreCase("OSA"))) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.M_OSA_AC_TO_NOT_OSA);
        }
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (DDRMST.AC_TYPE == 'F' 
            && DDRMST.CI_TYP == '2' 
            && !JIBS_tmp_str[1].equalsIgnoreCase("115846") 
            && DDRMST.EXP_DATE > 0) {
            IBS.init(SCCGWA, SCCCLDT);
            if (SCCGWA.COMM_AREA.AC_DATE > DDRMST.EXP_DATE) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_TEMP_AC_IS_EXPIRED;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                SCCCLDT.DATE1 = SCCGWA.COMM_AREA.AC_DATE;
                SCCCLDT.MTHS = 3;
                CEP.TRC(SCCGWA, SCCCLDT.DATE1);
                CEP.TRC(SCCGWA, SCCCLDT.MTHS);
                SCSSCLDT SCSSCLDT2 = new SCSSCLDT();
                SCSSCLDT2.MP(SCCGWA, SCCCLDT);
                if (SCCCLDT.RC != 0) {
                    WS_ERR_MSG = "LINK SCSSCLDT,CLDT-RC=" + SCCCLDT.RC;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                WS_EXP_DATE = SCCCLDT.DATE2;
                CEP.TRC(SCCGWA, WS_EXP_DATE);
                if ((WS_EXP_DATE > DDRMST.EXP_DATE 
                    || WS_EXP_DATE == DDRMST.EXP_DATE)) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_TEMP_AC_EXPIRE_WARN;
                    S000_ERR_MSG_PROC1();
                    if (pgmRtn) return;
                }
            }
        }
        CEP.TRC(SCCGWA, DDRMST.CASH_FLG);
        CEP.TRC(SCCGWA, DDCUDRAC.TX_TYPE);
        if ((DDRMST.CASH_FLG == '2' 
            || DDRMST.CASH_FLG == '4') 
            && DDCUDRAC.TX_TYPE == 'C') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CANT_DRW);
        }
        if (DDRMST.AC_TYPE == 'D' 
            && DDCUDRAC.TX_TYPE == 'C') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_GEN_AC_NOT_CDRW);
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(14 - 1, 14 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.init(SCCGWA, BPCPRMR);
            IBS.init(SCCGWA, DDCYERCK);
            DDCYERCK.KEY.TYP = "PDD14";
            DDCYERCK.KEY.CD = DDCUDRAC.TX_MMO;
            BPCPRMR.DAT_PTR = DDCYERCK;
            CEP.TRC(SCCGWA, DDCYERCK.KEY.CD);
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NOT_YEAR_CHK;
            S000_CALL_SCSCPARM();
            if (pgmRtn) return;
        }
        if (DDRMST.FRG_IND == null) DDRMST.FRG_IND = "";
        JIBS_tmp_int = DDRMST.FRG_IND.length();
        for (int i=0;i<3-JIBS_tmp_int;i++) DDRMST.FRG_IND += " ";
        if (DDRMST.FRG_IND.substring(0, 2).equalsIgnoreCase("FT") 
            && DDCUDRAC.TX_TYPE == 'C') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_FTA_AC_NO_CASH_TXN);
        }
        if ((!BPCPQORG.TRA_TYP.equalsIgnoreCase("00")) 
            && DDCUDRAC.TX_TYPE == 'C') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_FTA_BR_NO_CASH_TXN);
        }
        CEP.TRC(SCCGWA, WS_PQORG_TRA_TYP);
        CEP.TRC(SCCGWA, DDRMST.FRG_IND);
        if (DDRMST.FRG_IND == null) DDRMST.FRG_IND = "";
        JIBS_tmp_int = DDRMST.FRG_IND.length();
        for (int i=0;i<3-JIBS_tmp_int;i++) DDRMST.FRG_IND += " ";
        if (WS_PQORG_TRA_TYP.equalsIgnoreCase("00") 
            && DDRMST.FRG_IND.substring(0, 2).equalsIgnoreCase("FT")) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_FTA_AC_ONLY_TXN_FTA);
        }
        if (DDRMST.FRG_IND == null) DDRMST.FRG_IND = "";
        JIBS_tmp_int = DDRMST.FRG_IND.length();
        for (int i=0;i<3-JIBS_tmp_int;i++) DDRMST.FRG_IND += " ";
        if ((!WS_PQORG_TRA_TYP.equalsIgnoreCase("00")) 
            && (!DDRMST.FRG_IND.substring(0, 2).equalsIgnoreCase("FT"))) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ONLY_FTA_AC_TXN_FTA);
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(22 - 1, 22 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_REG_AC_MUST_LEGAL);
        }
    }
    public void B075_CHECK_CARD_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCPFTCK);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_TYP);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR);
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_AC_REL);
        if (CICCUST.O_DATA.O_CI_TYP == '1' 
            && CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DC")) {
            DCCPFTCK.VAL.CARD_NO = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        }
        if (CICCUST.O_DATA.O_CI_TYP == '2' 
            && CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD") 
            && CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("04")) {
            DCCPFTCK.VAL.CARD_NO = DDCUDRAC.CARD_NO;
        }
        CEP.TRC(SCCGWA, DCCPFTCK.VAL.CARD_NO);
        if (DCCPFTCK.VAL.CARD_NO.trim().length() > 0) {
            DCCPFTCK.VAL.REGN_TYP = '0';
            DCCPFTCK.FUNCTION_CODE = 'S';
            if (DDCUDRAC.TXN_TYPE.trim().length() > 0) {
                DCCPFTCK.VAL.TXN_TYPE = DDCUDRAC.TXN_TYPE;
            } else {
                DCCPFTCK.VAL.TXN_TYPE = "01";
            }
            CEP.TRC(SCCGWA, DCCPFTCK.VAL.TXN_TYPE);
            DCCPFTCK.TRK2_DAT = DDCUDRAC.TRK_DATE2;
            DCCPFTCK.TRK3_DAT = DDCUDRAC.TRK_DATE3;
            DCCPFTCK.VAL.TXN_CCY = DDCUDRAC.CCY;
            DCCPFTCK.VAL.TXN_AMT = DDCUDRAC.TX_AMT;
            DCCPFTCK.TXN_MMO = DDCUDRAC.TX_MMO;
            CEP.TRC(SCCGWA, DCCPFTCK.TXN_MMO);
            S000_CALL_DCZPFTCK();
            if (pgmRtn) return;
        }
    }
    public void B080_GET_CI_INF_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICFACRI.O_DATA.O_CI_NO);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO);
        CEP.TRC(SCCGWA, CICFACRI.O_DATA.O_FRM_APP);
        IBS.init(SCCGWA, CICCUST);
        IBS.init(SCCGWA, CICOCUST);
        if (DDCUDRAC.TX_TYPE == 'T' 
            && DDCUDRAC.OTHER_AC.trim().length() > 0 
            && !CICFACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
            CICCUST.FUNC = 'C';
            CICCUST.DATA.CI_NO = CICFACRI.O_DATA.O_CI_NO;
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, CICCUST, CICOCUST);
        }
        CEP.TRC(SCCGWA, CICOCUST.O_DATA.O_CI_NO);
        IBS.init(SCCGWA, CICCUST);
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        if (!CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
            CICCUST.FUNC = 'C';
            CICCUST.DATA.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            if (CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM.trim().length() == 0) {
                DDCUDRAC.CHI_NAME = CICCUST.O_DATA.O_CI_NM;
            } else {
                DDCUDRAC.CHI_NAME = CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM;
            }
            if (CICQACAC.O_DATA.O_ACR_DATA.O_AC_ENM.trim().length() == 0) {
                DDCUDRAC.ENG_NAME = CICCUST.O_DATA.O_CI_ENM;
            } else {
                DDCUDRAC.ENG_NAME = CICQACAC.O_DATA.O_ACR_DATA.O_AC_ENM;
            }
        }
    }
    public void B030_GET_CI_INF_PROC_N() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        IBS.init(SCCGWA, CICFACRI);
        if (DDCUDRAC.TX_TYPE == 'T' 
            && DDCUDRAC.OTHER_AC.trim().length() > 0) {
            CEP.TRC(SCCGWA, "OTHER AC INFO");
            CICQACRI.FUNC = 'A';
            CICQACRI.DATA.AGR_NO = DDCUDRAC.OTHER_AC;
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        } else {
            if (DDCUDRAC.RLT_TX_TOOL.trim().length() > 0) {
                CEP.TRC(SCCGWA, "OTHER TX TOOL INFO");
                IBS.init(SCCGWA, CICQACRI);
                CICQACRI.FUNC = 'A';
                CICQACRI.DATA.AGR_NO = DDCUDRAC.RLT_TX_TOOL;
                S000_CALL_CIZQACRI();
                if (pgmRtn) return;
            }
        }
        IBS.CLONE(SCCGWA, CICQACRI, CICFACRI);
        CEP.TRC(SCCGWA, CICFACRI.O_DATA.O_AGR_NO);
        CEP.TRC(SCCGWA, CICFACRI.O_DATA.O_CI_NO);
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.FUNC = 'I';
        if (DDCUDRAC.CARD_NO.trim().length() > 0 
            && DDCUDRAC.AC.trim().length() == 0) {
            CICQACRL.DATA.AC_NO = DDCUDRAC.CARD_NO;
        } else {
            CICQACRL.DATA.AC_NO = DDCUDRAC.AC;
        }
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = DDCUDRAC.AC;
        DDRCCY.CCY = DDCUDRAC.CCY;
        DDRCCY.CCY_TYPE = DDCUDRAC.CCY_TYPE;
        T000_READ_DDTCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCUDRAC.AC);
        CEP.TRC(SCCGWA, DDCUDRAC.CCY);
        CEP.TRC(SCCGWA, DDCUDRAC.CCY_TYPE);
        CEP.TRC(SCCGWA, DDRCCY.AC_TYPE);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            if (DDRCCY.AC_TYPE == '7') {
                CEP.TRC(SCCGWA, "DYQ USE VIRTUAL ACCOUNT ");
                IBS.init(SCCGWA, FSCIVMST);
                FSCIVMST.DATA.ACC_NO = DDRCCY.CUS_AC;
                S000_CALL_FSZVMST();
                if (pgmRtn) return;
                CICQACRL.DATA.AC_NO = FSCIVMST.O_DATA.O_UPACC_NO;
                CEP.TRC(SCCGWA, FSCIVMST.O_DATA.O_UPACC_NO);
                R000_VIR_CURBAL_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, CICQACRL.DATA.AC_NO);
        S000_CALL_CIZQACRL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRL.RC.RC_CODE);
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_AC_REL);
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
        if (CICQACRL.RC.RC_CODE == 8054) {
            CEP.TRC(SCCGWA, "1111111111");
            WS_REL_AC = CICQACRL.DATA.AC_NO;
            if (DDCUDRAC.CARD_NO.trim().length() > 0 
                && DDCUDRAC.AC.trim().length() > 0 
                && !DDCUDRAC.AC.equalsIgnoreCase(DDCUDRAC.CARD_NO)) {
                CICQACRL.O_DATA.O_AC_REL = "04";
            }
        } else if ((CICQACRL.RC.RC_CODE == 0 
                && (CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("03") 
                || CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("02") 
                || CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("04") 
                || CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("09") 
                || CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("13")))) {
            CEP.TRC(SCCGWA, "2222222222");
            WS_REL_AC = CICQACRL.O_DATA.O_REL_AC_NO;
            if (CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("09") 
                && WS_REL_AC.equalsIgnoreCase(DDCUDRAC.OTHER_AC)) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_CAN_NOT_SAME);
            }
        } else if (CICQACRL.RC.RC_CODE == 0 
                && CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("01")) {
            WS_REL_AC = CICQACRL.O_DATA.O_REL_AC_NO;
            R000_CHECK_VSAC_PROC();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, CICQACRL.RC);
        }
        WS_TR_AC = CICQACRL.DATA.AC_NO;
        if (CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("03")) {
            WS_CHK_AC_FLG = '1';
        }
        if (CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("13")) {
            WS_CHK_AC_FLG = '2';
        }
        if (CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("03") 
            && !DDCUDRAC.CCY.equalsIgnoreCase("156")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_LNK_CARD_CHY_ONLY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CICQACRI);
        CEP.TRC(SCCGWA, DDCUDRAC.AC);
        CEP.TRC(SCCGWA, DDCUDRAC.CARD_NO);
        if (DDCUDRAC.AC.trim().length() > 0) {
            CICQACRI.DATA.AGR_NO = DDCUDRAC.AC;
        } else {
            CICQACRI.DATA.AGR_NO = DDCUDRAC.CARD_NO;
        }
        CICQACRI.FUNC = 'A';
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
            WS_BV_TYPE = '1';
        }
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
            WS_BV_TYPE = '2';
        }
        CEP.TRC(SCCGWA, WS_REL_AC);
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.CCY_ACAC = DDCUDRAC.CCY;
        CICQACAC.DATA.CR_FLG = DDCUDRAC.CCY_TYPE;
        CICQACAC.DATA.AID = DDCUDRAC.AID;
        CICQACAC.DATA.AGR_NO = WS_REL_AC;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR);
        IBS.init(SCCGWA, DCCUCINF);
        if (WS_CHK_AC_FLG == '1') {
            DCCUCINF.CARD_NO = WS_TR_AC;
            S000_CALL_DCZUCINF();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DCCUCINF.CARD_HLDR_CINO);
            IBS.init(SCCGWA, CICCUST);
            IBS.init(SCCGWA, CICKCUST);
            CICCUST.FUNC = 'C';
            CICCUST.DATA.CI_NO = DCCUCINF.CARD_HLDR_CINO;
            CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, CICCUST, CICKCUST);
        }
        IBS.init(SCCGWA, DDRMST);
        IBS.init(SCCGWA, DDRFMST);
        if (WS_CHK_AC_FLG == '2') {
            DDRMST.KEY.CUS_AC = WS_TR_AC;
            CEP.TRC(SCCGWA, DDRMST.KEY.CUS_AC);
            T000_READ_DDTMST();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, DDRMST, DDRFMST);
        }
    }
    public void R000_CHECK_VSAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUADVT);
        DDCUADVT.VS_AC = CICQACRL.DATA.AC_NO;
        DDCUADVT.CCY = DDCUDRAC.CCY;
        DDCUADVT.CCY_TYP = DDCUDRAC.CCY_TYPE;
        DDCUADVT.VALUE_DT = SCCGWA.COMM_AREA.AC_DATE;
        DDCUADVT.TNJNL = SCCGWA.COMM_AREA.JRN_NO;
        DDCUADVT.TRAN_AMT = DDCUDRAC.TX_AMT;
        DDCUADVT.DD_AC = WS_REL_AC;
        DDCUADVT.DR_CR_F = 'D';
        DDCUADVT.UPD_FHIS = 'Y';
        DDCUADVT.OTH_AC = DDCUDRAC.OTHER_AC;
        CEP.TRC(SCCGWA, DDCUADVT.OTH_AC);
        DDCUADVT.RLT_AC = DDCUDRAC.RLT_AC;
        DDCUADVT.RLT_AC_NAME = DDCUDRAC.RLT_AC_NAME;
        if (DDCUDRAC.RLT_BANK.trim().length() == 0) DDCUADVT.RLT_BANK = 0;
        else DDCUADVT.RLT_BANK = Long.parseLong(DDCUDRAC.RLT_BANK);
        DDCUADVT.RLT_BA_NM = DDCUDRAC.RLT_BK_NM;
        CEP.TRC(SCCGWA, DDCUADVT.RLT_AC);
        DDCUADVT.TX_TYPE = DDCUDRAC.TX_TYPE;
        DDCUADVT.TX_MMO = DDCUDRAC.TX_MMO;
        CEP.TRC(SCCGWA, DDCUADVT.TX_MMO);
        S000_CALL_DDZUADVT();
        if (pgmRtn) return;
    }
    public void R000_VIR_CURBAL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FSCUVSAT);
        FSCUVSAT.VS_AC = DDCUDRAC.AC;
        FSCUVSAT.CCY = DDCUDRAC.CCY;
        FSCUVSAT.CCY_TYP = DDCUDRAC.CCY_TYPE;
        FSCUVSAT.VALUE_DT = SCCGWA.COMM_AREA.AC_DATE;
        FSCUVSAT.TNJNL = SCCGWA.COMM_AREA.JRN_NO;
        FSCUVSAT.TRAN_AMT = DDCUDRAC.TX_AMT;
        FSCUVSAT.DR_CR_F = 'D';
        FSCUVSAT.OTH_AC = DDCUDRAC.OTHER_AC;
        FSCUVSAT.TX_TYPE = DDCUDRAC.TX_TYPE;
        FSCUVSAT.TX_MMO = DDCUDRAC.TX_MMO;
        CEP.TRC(SCCGWA, FSCUVSAT.VS_AC);
        CEP.TRC(SCCGWA, FSCUVSAT.OTH_AC);
        CEP.TRC(SCCGWA, FSCUVSAT.TX_MMO);
        S000_CALL_FSZUVSAT();
        if (pgmRtn) return;
    }
    public void B090_CHECK_STS_TBL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFCSTS);
        if (DDCUDRAC.CARD_NO.trim().length() > 0) {
            BPCFCSTS.MSG_INFO = DDCUDRAC.CARD_NO;
        } else {
            BPCFCSTS.MSG_INFO = DDCUDRAC.AC;
        }
        CEP.TRC(SCCGWA, BPCFCSTS.MSG_INFO);
        BPCFCSTS.AP_MMO = K_STS_TABLE_APP;
        if (DDCUDRAC.TSTS_TABL.trim().length() > 0) {
            BPCFCSTS.TBL_NO = DDCUDRAC.TSTS_TABL;
        } else {
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_TYP);
                if (CICCUST.O_DATA.O_CI_TYP == '1') {
                    BPCFCSTS.TBL_NO = K_CUS_DR_STS_TBL_P;
                } else {
                    if (DDCUDRAC.TX_MMO.equalsIgnoreCase("A005") 
                        && SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
                        BPCFCSTS.TBL_NO = K_CUS_DR_STS_TD_TO_DD;
                    } else {
                        BPCFCSTS.TBL_NO = K_CUS_DR_STS_TBL_C;
                    }
                }
            } else {
                BPCFCSTS.TBL_NO = K_CUS_DR_STS_TBL_CAN;
            }
        }
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_STSW);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_NO);
        if (CICCUST.O_DATA.O_STSW == null) CICCUST.O_DATA.O_STSW = "";
        JIBS_tmp_int = CICCUST.O_DATA.O_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICCUST.O_DATA.O_STSW += " ";
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        BPCFCSTS.STATUS_WORD = CICCUST.O_DATA.O_STSW.substring(0, 80) + BPCFCSTS.STATUS_WORD.substring(80);
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 101 - 1) + DDRMST.AC_STS_WORD + BPCFCSTS.STATUS_WORD.substring(101 + 99 - 1);
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 201 - 1) + DDRCCY.STS_WORD.substring(0, 99) + BPCFCSTS.STATUS_WORD.substring(201 + 99 - 1);
        if (DDCUDRAC.FEE_FLG == 'Y' 
            && SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
            if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
            JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
            for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
            BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 105 - 1) + "0" + BPCFCSTS.STATUS_WORD.substring(105 + 1 - 1);
            if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
            JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
            for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
            BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 204 - 1) + "0" + BPCFCSTS.STATUS_WORD.substring(204 + 1 - 1);
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if ((JIBS_tmp_str[0].equalsIgnoreCase("0111803") 
            && (DDRMST.AC_STS_WORD.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
            || DDRMST.AC_STS_WORD.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1"))) 
            || (JIBS_tmp_str[1].equalsIgnoreCase("0111620") 
            && DDRMST.AC_STS_WORD.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1"))) {
            if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
            JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
            for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
            BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 102 - 1) + "0" + BPCFCSTS.STATUS_WORD.substring(102 + 1 - 1);
        }
        if (DDCUDRAC.CHK_PSW_FLG == 'N') {
            if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
            JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
            for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
            BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 106 - 1) + "0" + BPCFCSTS.STATUS_WORD.substring(106 + 1 - 1);
        }
        if (DDCUDRAC.SIGN_FLG == 'Y') {
            if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
            JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
            for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
            BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 107 - 1) + "00" + BPCFCSTS.STATUS_WORD.substring(107 + 2 - 1);
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1") 
            || DDRMST.AC_STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.init(SCCGWA, BPCPRMR);
            IBS.init(SCCGWA, BPCPARMC);
            BPCPRMR.FUNC = ' ';
            BPCPRMR.TYP = "PARMC";
            if (BPCPRMR.CD == null) BPCPRMR.CD = "";
            JIBS_tmp_int = BPCPRMR.CD.length();
            for (int i=0;i<40-JIBS_tmp_int;i++) BPCPRMR.CD += " ";
            BPCPRMR.CD = "MMO" + BPCPRMR.CD.substring(3);
            if (BPCPRMR.CD == null) BPCPRMR.CD = "";
            JIBS_tmp_int = BPCPRMR.CD.length();
            for (int i=0;i<40-JIBS_tmp_int;i++) BPCPRMR.CD += " ";
            if (DDCUDRAC.TX_MMO == null) DDCUDRAC.TX_MMO = "";
            JIBS_tmp_int = DDCUDRAC.TX_MMO.length();
            for (int i=0;i<5-JIBS_tmp_int;i++) DDCUDRAC.TX_MMO += " ";
            BPCPRMR.CD = BPCPRMR.CD.substring(0, 6 - 1) + DDCUDRAC.TX_MMO + BPCPRMR.CD.substring(6 + 9 - 1);
            BPCPRMR.DAT_PTR = BPCPARMC;
            S000_CALL_BPZPRMR();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPARMC.DATA_TXT.RBASE_TYP.RBASE_TYP3);
            if (BPCPARMC.DATA_TXT.RBASE_TYP.RBASE_TYP3 == 'Y') {
                if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
                JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
                for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
                BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 107 - 1) + "00" + BPCFCSTS.STATUS_WORD.substring(107 + 2 - 1);
            }
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (JIBS_tmp_str[0].equalsIgnoreCase("0111804") 
            && DDRMST.AC_STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
            if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
            JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
            for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
            BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 108 - 1) + "0" + BPCFCSTS.STATUS_WORD.substring(108 + 1 - 1);
        }
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DC") 
            && CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
            if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
            JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
            for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
            BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 101 - 1) + "000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000" + BPCFCSTS.STATUS_WORD.substring(101 + 99 - 1);
        }
        if (DDCUDRAC.FRZ_STS_CHK == 'N') {
            if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
            JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
            for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
            BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 209 - 1) + "0" + BPCFCSTS.STATUS_WORD.substring(209 + 1 - 1);
        }
        CEP.TRC(SCCGWA, BPCFCSTS.AP_MMO);
        CEP.TRC(SCCGWA, BPCFCSTS.TBL_NO);
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD);
        S000_CALL_BPZFCSTS();
        if (pgmRtn) return;
        if (DDCUDRAC.TSTS_TABL.equalsIgnoreCase("0014") 
            || DDCUDRAC.TSTS_TABL.equalsIgnoreCase("0016")) {
            IBS.init(SCCGWA, DCCIQHLD);
            DCCIQHLD.INP_DATA.AC = DDRCCY.KEY.AC;
            S000_CALL_DCZIQHLD();
            if (pgmRtn) return;
            if (DCCIQHLD.OUT_DATA.LAW_AC == 'Y') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_HAS_LAW_HLD);
            }
        }
        if (WS_CHK_AC_FLG == '1' 
            || WS_CHK_AC_FLG == '2') {
            B090_CHECK_CARD_HLDR_CINO_PROC();
            if (pgmRtn) return;
        }
    }
    public void B090_CHECK_CARD_HLDR_CINO_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFCSTS);
        CEP.TRC(SCCGWA, CICKCUST.O_DATA.O_CI_TYP);
        CEP.TRC(SCCGWA, DDRFMST.CI_TYP);
        if (DDCUDRAC.CARD_NO.trim().length() > 0) {
            BPCFCSTS.MSG_INFO = DDCUDRAC.CARD_NO;
            WS_FROM_CITYP = CICKCUST.O_DATA.O_CI_TYP;
        } else {
            BPCFCSTS.MSG_INFO = DDCUDRAC.AC;
            WS_FROM_CITYP = DDRFMST.CI_TYP;
        }
        BPCFCSTS.AP_MMO = K_STS_TABLE_APP;
        if (DDCUDRAC.TSTS_TABL.trim().length() > 0) {
            BPCFCSTS.TBL_NO = DDCUDRAC.TSTS_TABL;
        } else {
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                CEP.TRC(SCCGWA, WS_FROM_CITYP);
                if (WS_FROM_CITYP == '1') {
                    BPCFCSTS.TBL_NO = K_CUS_DR_STS_TBL_P;
                } else {
                    if (DDCUDRAC.TX_MMO.equalsIgnoreCase("A005") 
                        && SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
                        BPCFCSTS.TBL_NO = K_CUS_DR_STS_TD_TO_DD;
                    } else {
                        BPCFCSTS.TBL_NO = K_CUS_DR_STS_TBL_C;
                    }
                }
            } else {
                BPCFCSTS.TBL_NO = K_CUS_DR_STS_TBL_CAN;
            }
        }
        CEP.TRC(SCCGWA, CICKCUST.O_DATA.O_STSW);
        CEP.TRC(SCCGWA, CICKCUST.O_DATA.O_CI_NO);
        if (CICKCUST.O_DATA.O_STSW == null) CICKCUST.O_DATA.O_STSW = "";
        JIBS_tmp_int = CICKCUST.O_DATA.O_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICKCUST.O_DATA.O_STSW += " ";
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        BPCFCSTS.STATUS_WORD = CICKCUST.O_DATA.O_STSW.substring(0, 80) + BPCFCSTS.STATUS_WORD.substring(80);
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        if (DDRFMST.AC_STS_WORD == null) DDRFMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRFMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRFMST.AC_STS_WORD += " ";
        BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 101 - 1) + DDRFMST.AC_STS_WORD + BPCFCSTS.STATUS_WORD.substring(101 + 99 - 1);
        CEP.TRC(SCCGWA, BPCFCSTS.AP_MMO);
        CEP.TRC(SCCGWA, BPCFCSTS.TBL_NO);
        CEP.TRC(SCCGWA, BPCFCSTS.MSG_INFO);
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD);
        S000_CALL_BPZFCSTS();
        if (pgmRtn) return;
    }
    public void B100_GET_PRD_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIQPRD);
        DDCIQPRD.INPUT_DATA.PROD_CODE = DDRCCY.PROD_CODE;
        CEP.TRC(SCCGWA, DDRCCY.PROD_CODE);
        DDCIQPRD.INPUT_DATA.CCY = DDCUDRAC.CCY;
        DDCIQPRD.DDVMPRD_PTR = DDVMPRD;
        DDCIQPRD.DDVMRAT_PTR = DDVMRAT;
        S000_CALL_DDZIQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRMST.PROD_CODE);
        CEP.TRC(SCCGWA, DDCIQPRD.OUTPUT_DATA.PRDT_MODEL);
        CEP.TRC(SCCGWA, DDCIQPRD.OUTPUT_DATA.AC_TYPE);
        DPCPARMP.AC_TYPE = DDCIQPRD.OUTPUT_DATA.AC_TYPE;
        CEP.TRC(SCCGWA, DDCIQPRD.OUTPUT_DATA.PRDT_MODEL);
        CEP.TRC(SCCGWA, DDVMPRD.VAL.TD_FLG);
        CEP.TRC(SCCGWA, DDRMST.AC_TYPE);
        CEP.TRC(SCCGWA, DDCUDRAC.GD_WITHDR_FLG);
        CEP.TRC(SCCGWA, DDCUDRAC.SUPPLY_FLG);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if ((DDRMST.AC_TYPE == 'N' 
            || DDRMST.AC_TYPE == 'J' 
            || DDRMST.AC_TYPE == 'O') 
            && !(SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            && JIBS_tmp_str[1].equalsIgnoreCase("115846")) 
            && DDCUDRAC.GD_WITHDR_FLG != 'Y' 
            && !(DDVMPRD.VAL.TD_FLG == '0' 
            && DDCUDRAC.SUPPLY_FLG == 'N')) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.M_GDWR_CNEL_NOT_MATCH);
        }
    }
    public void B110_CHK_CHQ_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUDRAC.CHQ_NO);
        if (DDCUDRAC.CHQ_NO.trim().length() > 0 
            && DDCUDRAC.CHQ_TYPE != '7') {
            if (DDCUDRAC.CHQ_ISS_DATE == 0 
                && (DDCUDRAC.CHQ_TYPE != '4' 
                && DDCUDRAC.CHQ_TYPE != '6') 
                && DDCUDRAC.CHQ_DATE_FLG != 'N') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQ_ISSU_DT_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if ((SCCGWA.COMM_AREA.CANCEL_IND != 'Y') 
                && DDCUDRAC.CHQ_ISS_DATE != 0 
                && DDCUDRAC.CHQ_DATE_FLG != 'N') {
                B111_CHK_CHQ_DATE_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, DDCIMCHQ);
            DDCIMCHQ.DATA.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            DDCIMCHQ.DATA.CCY = DDCUDRAC.CCY;
            DDCIMCHQ.DATA.CCY_TYPE = DDCUDRAC.CCY_TYPE;
            DDCIMCHQ.FUNC = 'P';
            DDCIMCHQ.DATA.PAID_AMT = DDCUDRAC.TX_AMT;
            DDCIMCHQ.DATA.CHQ_NO = DDCUDRAC.CHQ_NO;
            DDCIMCHQ.DATA.STR_CHQ_NO = DDCUDRAC.CHQ_NO;
            DDCIMCHQ.DATA.END_CHQ_NO = DDCUDRAC.CHQ_NO;
            DDCIMCHQ.DATA.CHQ_TYP = DDCUDRAC.CHQ_TYPE;
            CEP.TRC(SCCGWA, DDCIMCHQ.DATA.CHQ_TYP);
            CEP.TRC(SCCGWA, DDCUDRAC.CHQ_ISS_DATE);
            DDCIMCHQ.DATA.ISSU_DATE = DDCUDRAC.CHQ_ISS_DATE;
            S000_CALL_DDZIMCHQ();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDCUDRAC.CHQ_TYPE);
            CEP.TRC(SCCGWA, DDCIMCHQ.DATA.CHQ_TYP);
            if (DDCUDRAC.CHQ_TYPE != DDCIMCHQ.DATA.CHQ_TYP 
                && DDCUDRAC.CHQ_TYPE != ' ') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ERR_CHQ_TYPE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if ((DDCUDRAC.TX_TYPE == 'C' 
                && (DDCUDRAC.CHQ_TYPE == '2' 
                || DDCUDRAC.CHQ_TYPE == '4' 
                || DDCUDRAC.CHQ_TYPE == '5' 
                || DDCUDRAC.CHQ_TYPE == '6')) 
                || (DDCUDRAC.TX_TYPE == 'T' 
                && DDCUDRAC.CHQ_TYPE == '1')) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ERR_CHQ_TYPE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, DDCUSIPM);
            DDCUSIPM.INPUT.AC_NO = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            DDCUSIPM.INPUT.CHQ_TYP = DDCUDRAC.CHQ_TYPE;
            DDCUSIPM.INPUT.STR_CHQ_NO = DDCUDRAC.CHQ_NO;
            DDCUSIPM.INPUT.END_CHQ_NO = DDCUDRAC.CHQ_NO;
            DDCUSIPM.FUNC = '2';
            S000_CALL_DDZUSIPM();
            if (pgmRtn) return;
            if (DDCUSIPM.RC.RC_CODE == 0) {
                if (DDCUDRAC.HLD_NO.trim().length() == 0) {
                    DDCUDRAC.HLD_NO = DDCUSIPM.OUTPUT.O_FRZE_NO;
                } else {
                    if (!DDCUSIPM.OUTPUT.O_FRZE_NO.equalsIgnoreCase(DDCUDRAC.HLD_NO) 
                        && DDCUSIPM.OUTPUT.O_FRZE_NO.trim().length() > 0) {
                        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQ_HLD_NOT_INP_SAME;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
            if (CICCUST.O_DATA.O_CI_TYP != '1' 
                && DDCUDRAC.PCHQ_FLG != 'Y' 
                && SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                IBS.init(SCCGWA, DDCUZFMM);
                DDCUZFMM.STD_AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
                CEP.TRC(SCCGWA, DDCUZFMM.STD_AC);
                CEP.TRC(SCCGWA, DDCIMCHQ.DATA.CHQ_TYP);
                if (DDCIMCHQ.DATA.CHQ_TYP == '4'
                    || DDCIMCHQ.DATA.CHQ_TYP == '6') {
                    DDCUZFMM.INPUT.CHQ_TYP = '4';
                } else if (DDCIMCHQ.DATA.CHQ_TYP == '5') {
                    DDCUZFMM.INPUT.CHQ_TYP = '5';
                } else {
                    DDCUZFMM.INPUT.CHQ_TYP = '1';
                }
                DDCUZFMM.INPUT.CHQ_ISSU_DATE = DDCUDRAC.CHQ_ISS_DATE;
                DDCUZFMM.INPUT.CHQ_NO = DDCUDRAC.CHQ_NO;
                DDCUZFMM.INPUT.AMT = DDCUDRAC.TX_AMT;
                DDCUZFMM.INPUT.CHQ_PSW = DDCUDRAC.PAY_PSWD;
                CEP.TRC(SCCGWA, DDCUZFMM.INPUT.AC_NO);
                CEP.TRC(SCCGWA, DDCUZFMM.INPUT.CHQ_ISSU_DATE);
                CEP.TRC(SCCGWA, DDCUZFMM.INPUT.CHQ_NO);
                CEP.TRC(SCCGWA, DDCUZFMM.INPUT.AMT);
                CEP.TRC(SCCGWA, DDCUDRAC.PAY_PSWD);
                S000_CALL_DDZUZFMM();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, DDCUZFMM.OUTPUT_DATE.CHK_RESULT);
                if (DDCUZFMM.OUTPUT_DATE.CHK_RESULT == '2') {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQ_PAY_PSW_ERR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (DDCUZFMM.OUTPUT_DATE.CHK_RESULT == '4') {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQ_PAY_PSW_INPUT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B111_CHK_CHQ_DATE_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUDRAC.CHQ_TYPE);
        CEP.TRC(SCCGWA, DDCUDRAC.CHQ_NO);
        if (DDCUDRAC.CHQ_TYPE != ' ' 
            && DDCUDRAC.CHQ_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQ_MUST_INP;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDCUDRAC.CHQ_ISS_DATE);
        if (DDCUDRAC.CHQ_ISS_DATE > SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ISSDT_GT_ACDATE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        R000_GET_CHQ_VALID_DT_PARM();
        if (pgmRtn) return;
        if (DDCUDRAC.CHQ_ISS_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = DDCUDRAC.CHQ_ISS_DATE;
            SCCCLDT.DAYS = DDCPCHQ.DATA_TXT.DAYS;
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCCCLDT.DATE2);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
            if (SCCCLDT.DATE2 < SCCGWA.COMM_AREA.AC_DATE) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CHQ_OVER_TEN_DAY);
            }
        }
    }
    public void B120_GET_CCY_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        CEP.TRC(SCCGWA, DDRCCY.KEY.AC);
        T000_READ_UPDATE_DDTCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCUDRAC.CLEAR_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            if (DDCUDRAC.CLEAR_FLG == 'Y') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND);
            }
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_NOT_SUPP_INPUT_CCY);
        }
        if ((DDRCCY.AC_TYPE == 'B' 
            || DDRCCY.AC_TYPE == 'C' 
            || DDRCCY.AC_TYPE == '4' 
            || DDRCCY.AC_TYPE == '5') 
            && DDCUDRAC.TX_TYPE == 'C') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ERR_DR_CSH);
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQORG.ATTR);
        CEP.TRC(SCCGWA, WS_CROS_DR_FLG);
        CEP.TRC(SCCGWA, DDRMST.OWNER_BR);
        CEP.TRC(SCCGWA, BPCPQORG.BBR);
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (DDCUDRAC.BANK_DR_FLG == 'N' 
            && (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")) 
            && !JIBS_tmp_str[0].equalsIgnoreCase("111804") 
            && WS_CROS_DR_FLG == '2' 
            && !DDRMST.AC_STS_WORD.substring(21 - 1, 21 + 1 - 1).equalsIgnoreCase("1") 
            && DDRMST.OWNER_BR != BPCPQORG.BBR) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_NOT_DR_AT_ALL);
        }
        CEP.TRC(SCCGWA, DDCUDRAC.FEE_FLG);
        if ((DDRCCY.AC_TYPE == '4' 
            || DDRCCY.AC_TYPE == '5') 
            && DDCUDRAC.EA_CHK_FLG != 'N' 
            && DDCUDRAC.FEE_FLG != 'Y') {
            B070_GET_DZZH_PARM_PROC();
            if (pgmRtn) return;
            B070_DZZH_CTL_PROC();
            if (pgmRtn) return;
        }
        if (DDCUDRAC.CLEAR_FLG == 'Y') {
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                if (DDRCCY.STS == 'C') {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_CLEARED);
                }
                CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
                if (DDRCCY.CURR_BAL < 0) {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AVL_BAL_NOT_ENOUGH);
                } else {
                    DDCUDRAC.TX_AMT = DDRCCY.CURR_BAL + DDRCCY.CCAL_TOT_BAL;
                    CEP.TRC(SCCGWA, DDCUDRAC.TX_AMT);
                }
            }
        }
    }
    public void B070_GET_DZZH_PARM_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, DDCPDZZH);
        BPRPRMT.KEY.TYP = "PDD26";
        BPRPRMT.KEY.CD = "DZZH";
        BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCPDZZH);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT.DAT_TXT);
        BPCPRMM.FUNC = '3';
        BPCPRMM.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCPDZZH.DATA_TXT);
        CEP.TRC(SCCGWA, DDCPDZZH.DATA_TXT.II_BAL);
        CEP.TRC(SCCGWA, DDCPDZZH.DATA_TXT.II_LMT_AMT);
        CEP.TRC(SCCGWA, DDCPDZZH.DATA_TXT.III_BAL);
        CEP.TRC(SCCGWA, DDCPDZZH.DATA_TXT.III_LMT_AMT);
    }
    public void B070_DZZH_CTL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EACUTRAN);
        EACUTRAN.FUNC = 'D';
        EACUTRAN.CARD_NO = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        CEP.TRC(SCCGWA, EACUTRAN.CARD_NO);
        if (DDRCCY.AC_TYPE == '4') {
            EACUTRAN.AC_FLG = '2';
        } else {
            EACUTRAN.AC_FLG = '3';
        }
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_PROD_CD_ACR);
        EACUTRAN.PROD_CODE = CICQACAC.O_DATA.O_ACR_DATA.O_PROD_CD_ACR;
        EACUTRAN.AC_NME = CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM;
        if (DDCUDRAC.OTHER_AC.trim().length() > 0) {
            EACUTRAN.OPP_AC = DDCUDRAC.OTHER_AC;
            EACUTRAN.OPP_AC_NME = CICFACRI.O_DATA.O_AC_CNM;
        } else {
            EACUTRAN.OPP_AC = DDCUDRAC.RLT_AC;
            EACUTRAN.OPP_AC_NME = DDCUDRAC.RLT_AC_NAME;
        }
        CEP.TRC(SCCGWA, EACUTRAN.OPP_AC);
        CEP.TRC(SCCGWA, EACUTRAN.OPP_AC_NME);
        EACUTRAN.MMO = DDCUDRAC.TX_MMO;
        EACUTRAN.OPP_BNK = DDCUDRAC.OTHER_BR;
        EACUTRAN.OPP_BNK_NME = DDCUDRAC.OTHER_BK_NM;
        EACUTRAN.RMK = DDCUDRAC.REMARKS;
    }
    public void B130_CHECK_AVA_BAL_PROC() throws IOException,SQLException,Exception {
        WS_AVL_BAL = DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL - DDRCCY.MARGIN_BAL;
        WS_COM_AUTO_AC_BAL = DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL + DDRCCY.CCAL_TOT_BAL - DDRCCY.MARGIN_BAL;
        WS_CURR_BAL = DDRCCY.CURR_BAL;
        CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
        CEP.TRC(SCCGWA, DDRCCY.HOLD_BAL);
        CEP.TRC(SCCGWA, DDRCCY.MARGIN_BAL);
        CEP.TRC(SCCGWA, WS_COM_AUTO_AC_BAL);
        CEP.TRC(SCCGWA, DDCUDRAC.TX_AMT);
        CEP.TRC(SCCGWA, DDCUDRAC.TX_BAL_FLG);
        CEP.TRC(SCCGWA, DDCUDRAC.CLEAR_FLG);
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        CEP.TRC(SCCGWA, DDRMST.AC_STS_WORD.substring(42 - 1, 42 + 1 - 1));
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y' 
            && DDRMST.AC_STS_WORD.substring(42 - 1, 42 + 1 - 1).equalsIgnoreCase("1") 
            && WS_AVL_BAL < DDCUDRAC.TX_AMT) {
            if (DDCUDRAC.TX_TYPE == 'C') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CANNOT_USE_FT_MSTR);
            }
            if (DDRCCY.HOLD_BAL > 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AVL_BAL_NOT_ENOUGH);
            }
            IBS.init(SCCGWA, BPCPRMR);
            IBS.init(SCCGWA, BPCPARMC);
            BPCPRMR.FUNC = ' ';
            BPCPRMR.TYP = "PARMC";
            if (BPCPRMR.CD == null) BPCPRMR.CD = "";
            JIBS_tmp_int = BPCPRMR.CD.length();
            for (int i=0;i<40-JIBS_tmp_int;i++) BPCPRMR.CD += " ";
            BPCPRMR.CD = "MMO" + BPCPRMR.CD.substring(3);
            if (BPCPRMR.CD == null) BPCPRMR.CD = "";
            JIBS_tmp_int = BPCPRMR.CD.length();
            for (int i=0;i<40-JIBS_tmp_int;i++) BPCPRMR.CD += " ";
            if (DDCUDRAC.TX_MMO == null) DDCUDRAC.TX_MMO = "";
            JIBS_tmp_int = DDCUDRAC.TX_MMO.length();
            for (int i=0;i<5-JIBS_tmp_int;i++) DDCUDRAC.TX_MMO += " ";
            BPCPRMR.CD = BPCPRMR.CD.substring(0, 6 - 1) + DDCUDRAC.TX_MMO + BPCPRMR.CD.substring(6 + 9 - 1);
            BPCPRMR.DAT_PTR = BPCPARMC;
            S000_CALL_BPZPRMR();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPARMC.DATA_TXT.RBASE_TYP.RBASE_TYP2);
            IBS.init(SCCGWA, DDRADMN);
            DDRADMN.KEY.AC = DDRCCY.KEY.AC;
            DDRADMN.ADP_STRDATE = SCCGWA.COMM_AREA.AC_DATE;
            if (CICCUST.O_DATA.O_CI_TYP == '2') {
                DDRADMN.KEY.ADP_TYPE = "08";
            }
            if (CICCUST.O_DATA.O_CI_TYP == '3') {
                DDRADMN.KEY.ADP_TYPE = "10";
            }
            DDRADMN.ADP_STS = 'O';
            T000_READ_DDTADMN();
            if (pgmRtn) return;
            if (DDRADMN.ADP_STSW == null) DDRADMN.ADP_STSW = "";
            JIBS_tmp_int = DDRADMN.ADP_STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRADMN.ADP_STSW += " ";
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0' 
                && !DDRADMN.ADP_STSW.substring(0, 1).equalsIgnoreCase("1")) {
                CEP.TRC(SCCGWA, WS_AVL_BAL);
                CEP.TRC(SCCGWA, DDRADMN.OD_AMT);
                WS_AVL_BAL = WS_AVL_BAL + DDRADMN.OD_AMT;
                CEP.TRC(SCCGWA, WS_AVL_BAL);
            }
        }
        CEP.TRC(SCCGWA, WS_AVL_BAL);
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        CEP.TRC(SCCGWA, DDRMST.AC_STS_WORD.substring(62 - 1, 62 + 1 - 1));
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y' 
            && DDRMST.AC_STS_WORD.substring(62 - 1, 62 + 1 - 1).equalsIgnoreCase("1") 
            && WS_AVL_BAL < DDCUDRAC.TX_AMT) {
            IBS.init(SCCGWA, DDRADMN);
            DDRADMN.KEY.AC = DDRCCY.KEY.AC;
            DDRADMN.ADP_STRDATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRADMN.KEY.ADP_TYPE = "09";
            DDRADMN.ADP_STS = 'O';
            T000_READ_DDTADMN();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                WS_AVL_BAL = WS_AVL_BAL + DDRADMN.OD_AMT;
            }
        }
        CEP.TRC(SCCGWA, WS_AVL_BAL);
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        CEP.TRC(SCCGWA, DDRMST.AC_STS_WORD.substring(61 - 1, 61 + 1 - 1));
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y' 
            && (!DDRMST.AC_STS_WORD.substring(61 - 1, 61 + 1 - 1).equalsIgnoreCase("1") 
            && !DDRMST.AC_STS_WORD.substring(62 - 1, 62 + 1 - 1).equalsIgnoreCase("1"))) {
            CEP.TRC(SCCGWA, WS_AVL_BAL);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            CEP.TRC(SCCGWA, DDCUDRAC.TX_BAL_FLG);
            if (DDCUDRAC.CLEAR_FLG != 'Y' 
                && DDCUDRAC.TX_BAL_FLG == 'Y' 
                && WS_AVL_BAL < DDCUDRAC.TX_AMT) {
                if (WS_AVL_BAL <= 0) {
                    DDCUDRAC.TX_AMT = 0;
                    Z_RET();
                    if (pgmRtn) return;
                } else {
                    DDCUDRAC.TX_AMT = WS_AVL_BAL;
                    CEP.TRC(SCCGWA, DDCUDRAC.TX_AMT);
                }
            }
            CEP.TRC(SCCGWA, DDVMPRD.VAL.TD_FLG);
            if (DDVMPRD.VAL.TD_FLG == '0') {
                if (DDCUDRAC.CLEAR_FLG != 'Y') {
                    if (WS_COM_AUTO_AC_BAL >= DDCUDRAC.TX_AMT) {
                        if (DDCUDRAC.SUPPLY_FLG != 'N') {
                            B130_01_MARGIN_SUPP_PROC();
                            if (pgmRtn) return;
                        }
                    } else {
                        CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AVL_BAL_NOT_ENOUGH);
                    }
                } else {
                    CEP.TRC(SCCGWA, "TTTT");
                    B130_01_MARGIN_SUPP_PROC();
                    if (pgmRtn) return;
                }
            } else {
                CEP.TRC(SCCGWA, WS_AVL_BAL);
                CEP.TRC(SCCGWA, DDCUDRAC.TX_AMT);
                CEP.TRC(SCCGWA, DDCUDRAC.CLEAR_FLG);
                CEP.TRC(SCCGWA, DDCUDRAC.TX_BAL_FLG);
                if (WS_AVL_BAL < DDCUDRAC.TX_AMT 
                    && DDCUDRAC.CLEAR_FLG != 'Y' 
                    && DDCUDRAC.TX_BAL_FLG != 'Y') {
                    if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                    JIBS_tmp_int = DDRCCY.STS_WORD.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                    CEP.TRC(SCCGWA, DDRCCY.STS_WORD.substring(50 - 1, 50 + 5 - 1));
                    if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                    JIBS_tmp_int = DDRCCY.STS_WORD.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                    if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                    JIBS_tmp_int = DDRCCY.STS_WORD.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                    if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                    JIBS_tmp_int = DDRCCY.STS_WORD.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                    if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                    JIBS_tmp_int = DDRCCY.STS_WORD.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                    if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                    JIBS_tmp_int = DDRCCY.STS_WORD.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                    if (DDRCCY.STS_WORD.substring(50 - 1, 50 + 1 - 1).equalsIgnoreCase("1") 
                        || DDRCCY.STS_WORD.substring(51 - 1, 51 + 1 - 1).equalsIgnoreCase("1") 
                        || DDRCCY.STS_WORD.substring(52 - 1, 52 + 1 - 1).equalsIgnoreCase("1") 
                        || DDRCCY.STS_WORD.substring(53 - 1, 53 + 1 - 1).equalsIgnoreCase("1") 
                        || DDRCCY.STS_WORD.substring(54 - 1, 54 + 1 - 1).equalsIgnoreCase("1")) {
                        if (DDCUDRAC.SUPPLY_FLG != 'N') {
                            WS_NEED_AMT = DDCUDRAC.TX_AMT - WS_AVL_BAL;
                            CEP.TRC(SCCGWA, WS_NEED_AMT);
                            if (DDRCCY.HOLD_BAL <= DDRCCY.CURR_BAL) {
                                B130_01_MARGIN_SUPP_PROC();
                                if (pgmRtn) return;
                            } else {
                                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AVL_BAL_NOT_ENOUGH);
                            }
                        } else {
                            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AVL_BAL_NOT_ENOUGH);
                        }
                    } else {
                        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                        JIBS_tmp_int = DDRCCY.STS_WORD.length();
                        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                        CEP.TRC(SCCGWA, DDRCCY.STS_WORD.substring(63 - 1, 63 + 1 - 1));
                        if (DDCUDRAC.TRT_CTLW == null) DDCUDRAC.TRT_CTLW = "";
                        JIBS_tmp_int = DDCUDRAC.TRT_CTLW.length();
                        for (int i=0;i<32-JIBS_tmp_int;i++) DDCUDRAC.TRT_CTLW += " ";
                        CEP.TRC(SCCGWA, DDCUDRAC.TRT_CTLW.substring(2 - 1, 2 + 1 - 1));
                        if (DDCUDRAC.TRT_CTLW == null) DDCUDRAC.TRT_CTLW = "";
                        JIBS_tmp_int = DDCUDRAC.TRT_CTLW.length();
                        for (int i=0;i<32-JIBS_tmp_int;i++) DDCUDRAC.TRT_CTLW += " ";
                        CEP.TRC(SCCGWA, DDCUDRAC.TRT_CTLW.substring(3 - 1, 3 + 1 - 1));
                        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                        JIBS_tmp_int = DDRCCY.STS_WORD.length();
                        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                        JIBS_tmp_int = DDRCCY.STS_WORD.length();
                        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                        JIBS_tmp_int = DDRCCY.STS_WORD.length();
                        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                        if (DDCUDRAC.TRT_CTLW == null) DDCUDRAC.TRT_CTLW = "";
                        JIBS_tmp_int = DDCUDRAC.TRT_CTLW.length();
                        for (int i=0;i<32-JIBS_tmp_int;i++) DDCUDRAC.TRT_CTLW += " ";
                        if (DDRCCY.STS_WORD.substring(63 - 1, 63 + 1 - 1).equalsIgnoreCase("1") 
                            && !DDRCCY.STS_WORD.substring(62 - 1, 62 + 1 - 1).equalsIgnoreCase("1") 
                            && (!DDRCCY.STS_WORD.substring(62 - 1, 62 + 1 - 1).equalsIgnoreCase("1")) 
                            && (!DDCUDRAC.TRT_CTLW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1"))) {
                        } else {
                            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AVL_BAL_NOT_ENOUGH);
                        }
                    }
                }
            }
        }
    }
    public void B130_01_MARGIN_SUPP_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUTCGD);
        if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
            DCCUTCGD.CHNL_FLG = 'B';
        } else {
            DCCUTCGD.CHNL_FLG = 'O';
        }
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_TYP);
        if (CICCUST.O_DATA.O_CI_TYP == '1') {
            DCCUTCGD.FUNC = 'P';
        } else {
            DCCUTCGD.FUNC = 'C';
        }
        DCCUTCGD.AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        CEP.TRC(SCCGWA, DCCUTCGD.AC);
        DCCUTCGD.CCY = DDRCCY.CCY;
        DCCUTCGD.CCY_TYP = DDRCCY.CCY_TYPE;
        DCCUTCGD.AMT = DDCUDRAC.TX_AMT;
        if (WS_NEED_AMT != 0) {
            DCCUTCGD.AMT = WS_NEED_AMT;
        }
        CEP.TRC(SCCGWA, DCCUTCGD.AMT);
        DCCUTCGD.MMO = DDCUDRAC.TX_MMO;
        DCCUTCGD.CANC_FLG = 'N';
        if (DDCUDRAC.CLEAR_FLG == 'Y') {
            DCCUTCGD.CANC_FLG = 'Y';
        }
        DCCUTCGD.FLG = 'O';
        DCCUTCGD.SIGN_FLG = 'Y';
        DCCUTCGD.PROD_CD = DDRCCY.PROD_CODE;
        CEP.TRC(SCCGWA, DDVMPRD.VAL.TD_FLG);
        if (DDVMPRD.VAL.TD_FLG == '0') {
            DCCUTCGD.FLG = 'M';
            DCCUTCGD.SIGN_FLG = 'N';
            DCCUTCGD.PROD_CD = DDVMPRD.VAL.TD_PROD;
        }
        CEP.TRC(SCCGWA, DCCUTCGD.PROD_CD);
        S000_CALL_DCZUTCGD();
        if (pgmRtn) return;
        DDCUDRAC.MARGIN_INT = DCCUTCGD.INTEREST_AMT;
        CEP.TRC(SCCGWA, DDCUDRAC.MARGIN_INT);
        CEP.TRC(SCCGWA, DCCUTCGD.BAL_FLG);
        if (DCCUTCGD.BAL_FLG == '1' 
            || (SCCGWA.COMM_AREA.CANCEL_IND != 'Y' 
            && DCCUTCGD.RC.RC_CODE != 0)) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AVL_BAL_NOT_ENOUGH);
        }
    }
    public void B135_CHECK_AVA_BAL_PROC_CON() throws IOException,SQLException,Exception {
        if (DDCUDRAC.HLD_NO.trim().length() > 0 
            || DDCUDRAC.HLD_ENT_PAY == 'Y') {
            CEP.TRC(SCCGWA, DDCUDRAC.HLD_NO);
            CEP.TRC(SCCGWA, DDCUDRAC.HLD_ENT_PAY);
            IBS.init(SCCGWA, DCCURHLD);
            if (DDCUDRAC.HLD_NO.trim().length() > 0) {
                CEP.TRC(SCCGWA, DDCUDRAC.HLD_NO);
                DCCURHLD.DATA.HLD_NO = DDCUDRAC.HLD_NO;
                DCCURHLD.DATA.DEDUCT_FLG = '2';
            } else {
                DCCURHLD.DATA.ENT_PAY_FLG = 'Y';
                DCCURHLD.DATA.HLD_TYP = '4';
                DCCURHLD.DATA.AC = WS_TEMP_STD_AC;
                DCCURHLD.DATA.CCY = DDCUDRAC.CCY;
                DCCURHLD.DATA.CCY_TYP = DDCUDRAC.CCY_TYPE;
                DCCURHLD.DATA.RAMT = DDCUDRAC.TX_AMT;
            }
            S000_CALL_DCZURHLD();
            if (pgmRtn) return;
            if (DCCURHLD.DATA.PRI_AC_FLG == 'Y' 
                && SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.M_PRI_ACHLD_NOT_AMTHLD;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_AVL_BAL = DDRCCY.CURR_BAL - DCCURHLD.DATA.PRI_HLD_AMT;
            DDRCCY.HOLD_BAL = DDRCCY.HOLD_BAL - DCCURHLD.DATA.RAMT;
        } else {
            WS_AVL_BAL = DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL;
        }
        CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
        CEP.TRC(SCCGWA, DDRCCY.HOLD_BAL);
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            if (WS_AVL_BAL == 0 
                && DDCUDRAC.CLEAR_FLG != 'Y' 
                && DDCUDRAC.HLD_ENT_PAY != 'Y' 
                && WS_AVL_BAL < DDCUDRAC.TX_AMT 
                && DDCUDRAC.TX_BAL_FLG == 'Y') {
                DDCUDRAC.TX_AMT = 0;
                Z_RET();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, WS_AVL_BAL);
            CEP.TRC(SCCGWA, DDCUDRAC.TX_AMT);
            CEP.TRC(SCCGWA, WS_AVL_BAL);
            CEP.TRC(SCCGWA, DDCUDRAC.TX_AMT);
            CEP.TRC(SCCGWA, DDCUDRAC.CLEAR_FLG);
            if (WS_AVL_BAL != DDCUDRAC.TX_AMT 
                && DDCUDRAC.CLEAR_FLG == 'Y') {
                CEP.TRC(SCCGWA, WS_AVL_BAL);
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AVL_BAL_NOT_ENOUGH;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B140_DR_ACCOUNT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_PERS_FIN_FLG);
        CEP.TRC(SCCGWA, WS_COMP_INTEL_FLG);
        CEP.TRC(SCCGWA, WS_GROUP_AC_FLG);
        CEP.TRC(SCCGWA, WS_REAL_TIME_FLG);
        CEP.TRC(SCCGWA, WS_NEED_HLD_AMT_FLG);
        if (WS_REAL_TIME_FLG == 'Y') {
            CEP.TRC(SCCGWA, "*** GO INTO REAL-TIME-WDRAW-Y ***");
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if ((WS_COMP_INTEL_FLG == 'Y' 
                || WS_PERS_FIN_FLG == 'Y') 
                && !DDRMST.AC_STS_WORD.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.TRC(SCCGWA, "*** GO INTO COMBINATION PRODUCT ***");
                IBS.init(SCCGWA, DCCUTCGD);
                DCCUTCGD.AC = WS_TEMP_STD_AC;
                if (WS_PERS_FIN_FLG == 'Y') {
                    DCCUTCGD.FUNC = 'P';
                } else {
                    DCCUTCGD.FUNC = 'C';
                }
                if ((SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
                    || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT"))) {
                    DCCUTCGD.CHNL_FLG = 'B';
                } else {
                    DCCUTCGD.CHNL_FLG = 'O';
                }
                CEP.TRC(SCCGWA, DDCUDRAC.TX_AMT);
                CEP.TRC(SCCGWA, WS_AVL_BAL);
                if (WS_PERS_FIN_FLG == 'Y') {
                    WS_GPDR_AMT = WS_AUTO_AC_TX_AMT - WS_AVL_BAL;
                } else {
                    WS_GPDR_AMT = WS_AUTO_AC_TX_AMT - WS_CURR_BAL;
                }
                CEP.TRC(SCCGWA, WS_GPDR_AMT);
                DCCUTCGD.AMT = WS_GPDR_AMT;
                DCCUTCGD.MMO = DDCUDRAC.TX_MMO;
                DCCUTCGD.ZERO_FLG = DDCUDRAC.TX_BAL_FLG;
                CEP.TRC(SCCGWA, DCCUTCGD.AC);
                CEP.TRC(SCCGWA, DCCUTCGD.AMT);
                CEP.TRC(SCCGWA, DCCUTCGD.MMO);
                S000_CALL_DCZUTCGD();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, DCCUTCGD.RC);
                if (DCCUTCGD.RC.RC_CODE != 0) {
                    WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUTCGD.RC);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                    CEP.TRC(SCCGWA, DCCUTCGD.BAL_FLG);
                    CEP.TRC(SCCGWA, DCCUTCGD.CNT);
                    CEP.TRC(SCCGWA, DCCUTCGD.AVA_AMT);
                    if (DCCUTCGD.BAL_FLG == '1') {
                        if (WS_COMP_INTEL_FLG == 'Y' 
                            && DCCUTCGD.CNT >= 30) {
                            WS_DISPLAY_AMT = DCCUTCGD.AVA_AMT;
                            WS_DISPLAY_AMT = WS_CURR_BAL + WS_DISPLAY_AMT;
                            df = new DecimalFormat("#############0.00");
                            WS_DISPLAY_AMT_1 = df.format(WS_DISPLAY_AMT);
                            WS_ERR_INFO = "AVA-AMT = " + WS_DISPLAY_AMT_1;
                            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MORE_TX_AMT_THAN_TOT;
                            S000_ERR_MSG_PROC();
                            if (pgmRtn) return;
                        } else {
                            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AVL_BAL_NOT_ENOUGH;
                            S000_ERR_MSG_PROC();
                            if (pgmRtn) return;
                        }
                    }
                    IBS.init(SCCGWA, DDRCCY);
                    DDRCCY.KEY.AC = WS_TEMP_STD_AC;
                    T000_READ_UPDATE_DDTCCY();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
                    CEP.TRC(SCCGWA, DDRCCY.HOLD_BAL);
                    CEP.TRC(SCCGWA, WS_HLD_PRI_HLD_AMT);
                    if (WS_NEED_HLD_AMT_FLG == 'Y') {
                        WS_AVL_BAL2 = DDRCCY.CURR_BAL - WS_HLD_PRI_HLD_AMT;
                    } else {
                        WS_AVL_BAL2 = DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL;
                    }
                    CEP.TRC(SCCGWA, WS_AVL_BAL2);
                }
            } else {
                WS_AVL_BAL2 = WS_AVL_BAL;
                CEP.TRC(SCCGWA, WS_AVL_BAL2);
            }
            if ((WS_AVL_BAL2 < 0 
                && WS_GROUP_AC_FLG == 'Y') 
                || (WS_AVL_BAL2 < DDCUDRAC.TX_AMT 
                && WS_GROUP_AC_FLG == 'Y')) {
                IBS.init(SCCGWA, DDCUGPDR);
                DDCUGPDR.AC = WS_TEMP_STD_AC;
                DDCUGPDR.CCY = DDCUDRAC.CCY;
                DDCUGPDR.CCY_TYPE = DDCUDRAC.CCY_TYPE;
                CEP.TRC(SCCGWA, DDCUDRAC.TX_AMT);
                CEP.TRC(SCCGWA, WS_AVL_BAL2);
                WS_GPDR_AMT = DDCUDRAC.TX_AMT - WS_AVL_BAL2;
                CEP.TRC(SCCGWA, WS_GPDR_AMT);
                DDCUGPDR.RT_DR_AMT = WS_GPDR_AMT;
                DDCUGPDR.REAL_TIME_WDR = ' ';
                DDCUGPDR.TX_MMO = DDCUDRAC.TX_MMO;
                DDCUGPDR.TX_REF = DDCUDRAC.TX_REF;
                DDCUGPDR.NARRATIVE = DDCUDRAC.NARRATIVE;
                DDCUGPDR.REMARKS = DDCUDRAC.REMARKS;
                CEP.TRC(SCCGWA, DDCUGPDR.AC);
                CEP.TRC(SCCGWA, DDCUGPDR.CCY);
                CEP.TRC(SCCGWA, DDCUGPDR.CCY_TYPE);
                CEP.TRC(SCCGWA, DDCUGPDR.RT_DR_AMT);
                CEP.TRC(SCCGWA, DDCUGPDR.REAL_TIME_WDR);
                CEP.TRC(SCCGWA, DDCUGPDR.TX_MMO);
                CEP.TRC(SCCGWA, DDCUGPDR.TX_REF);
                CEP.TRC(SCCGWA, DDCUGPDR.NARRATIVE);
                CEP.TRC(SCCGWA, DDCUGPDR.REMARKS);
                S000_CALL_DDZUGPDR();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, DDCUGPDR.RC);
                if (DDCUGPDR.RC.RC_CODE != 0) {
                    WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCUGPDR.RC);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (DDCUGPDR.REAL_TIME_WDR != 'Y' 
                    && DDCUDRAC.TX_BAL_FLG != 'Y') {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AVL_BAL_NOT_ENOUGH;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            WS_CHK_DRAC_FLG = 'Y';
            B088_DR_DD_ACCOUNT_PROC();
            if (pgmRtn) return;
        } else {
            WS_CHK_DRAC_FLG = 'Y';
            B088_DR_DD_ACCOUNT_PROC();
            if (pgmRtn) return;
        }
    }
    public void B088_DR_DD_ACCOUNT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUDRAC.AC);
        CEP.TRC(SCCGWA, DDCUDRAC.CCY);
        CEP.TRC(SCCGWA, DDCUDRAC.CCY_TYPE);
        IBS.init(SCCGWA, DDCUDRDD);
        CEP.TRC(SCCGWA, DDCUDRAC.AC);
        DDCUDRDD.AC = DDCUDRAC.AC;
        DDCUDRDD.ACAC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        CEP.TRC(SCCGWA, DDCUDRAC.ACAC);
        DDCUDRDD.CCY = DDCUDRAC.CCY;
        DDCUDRDD.CCY_TYPE = DDCUDRAC.CCY_TYPE;
        DDCUDRDD.TX_AMT = DDCUDRAC.TX_AMT;
        DDCUDRDD.CLEAR_FLG = DDCUDRAC.CLEAR_FLG;
        DDCUDRDD.VAL_DATE = DDCUDRAC.VAL_DATE;
        DDCUDRDD.PAY_PSWD = DDCUDRAC.PAY_PSWD;
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_ID_TYPE);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_ID_NO);
        DDCUDRDD.ID_TYPE = CICCUST.O_DATA.O_ID_TYPE;
        DDCUDRDD.ID_NO = CICCUST.O_DATA.O_ID_NO;
        DDCUDRDD.PSBK_NO = DDCUDRAC.PSBK_NO;
        DDCUDRDD.CARD_NO = DDCUDRAC.CARD_NO;
        DDCUDRDD.CHQ_TYPE = DDCUDRAC.CHQ_TYPE;
        DDCUDRDD.CHQ_NO = DDCUDRAC.CHQ_NO;
        DDCUDRDD.CHQ_ISS_DATE = DDCUDRAC.CHQ_ISS_DATE;
        DDCUDRDD.OTHER_AC = DDCUDRAC.OTHER_AC;
        DDCUDRDD.OTHER_CCY = DDCUDRAC.OTHER_CCY;
        DDCUDRDD.OTHER_AMT = DDCUDRAC.OTHER_AMT;
        DDCUDRDD.TX_TYPE = DDCUDRAC.TX_TYPE;
        DDCUDRDD.NARRATIVE = DDCUDRAC.NARRATIVE;
        DDCUDRDD.TX_MMO = DDCUDRAC.TX_MMO;
        DDCUDRDD.TX_REF = DDCUDRAC.TX_REF;
        DDCUDRDD.REMARKS = DDCUDRAC.REMARKS;
        DDCUDRDD.CHK_PSW_FLG = DDCUDRAC.CHK_PSW_FLG;
        DDCUDRDD.BANK_DR_FLG = DDCUDRAC.BANK_DR_FLG;
        DDCUDRDD.GD_WITHDR_FLG = DDCUDRAC.GD_WITHDR_FLG;
        DDCUDRDD.TX_BAL_FLG = DDCUDRAC.TX_BAL_FLG;
        DDCUDRDD.HLD_NO = DDCUDRAC.HLD_NO;
        DDCUDRDD.PAY_TYPE = DDCUDRAC.PAY_TYPE;
        DDCUDRDD.PAY_SIGN_NO = DDCUDRAC.PAY_SIGN_NO;
        DDCUDRDD.BV_TYP = DDCUDRAC.BV_TYP;
        CEP.TRC(SCCGWA, DDCUDRAC.BV_TYP);
        if (DDCUDRAC.BV_TYP == '4') {
            DDCUDRDD.BV_TYP = '3';
        }
        DDCUDRDD.CHK_PSW = DDCUDRAC.CHK_PSW;
        DDCUDRDD.TRK_DATE2 = DDCUDRAC.TRK_DATE2;
        DDCUDRDD.TRK_DATE3 = DDCUDRAC.TRK_DATE3;
        DDCUDRDD.PSWD = DDCUDRAC.PSWD;
        DDCUDRDD.OTHER_BR = DDCUDRAC.OTHER_BR;
        DDCUDRDD.OTHER_BK_NM = DDCUDRAC.OTHER_BK_NM;
        DDCUDRDD.OTHER_AC_NM = DDCUDRAC.OTHER_AC_NM;
        DDCUDRDD.OTH_TX_TOOL = DDCUDRAC.OTH_TX_TOOL;
        DDCUDRDD.RLT_AC = DDCUDRAC.RLT_AC;
        DDCUDRDD.HLD_ENT_PAY = DDCUDRAC.HLD_ENT_PAY;
        DDCUDRDD.RLT_AC_NAME = DDCUDRAC.RLT_AC_NAME;
        DDCUDRDD.RLT_BANK = DDCUDRAC.RLT_BANK;
        DDCUDRDD.RLT_BK_NM = DDCUDRAC.RLT_BK_NM;
        CEP.TRC(SCCGWA, DDCUDRDD.RLT_AC);
        CEP.TRC(SCCGWA, DDCUDRDD.RLT_AC_NAME);
        CEP.TRC(SCCGWA, DDCUDRDD.RLT_BANK);
        CEP.TRC(SCCGWA, DDCUDRDD.RLT_BK_NM);
        DDCUDRDD.RLT_REF_NO = DDCUDRAC.RLT_REF_NO;
        DDCUDRDD.RLT_CCY = DDCUDRAC.RLT_CCY;
        DDCUDRDD.RLT_TX_TOOL = DDCUDRAC.RLT_TX_TOOL;
        CEP.TRC(SCCGWA, DDCUDRDD.RLT_TX_TOOL);
        DDCUDRDD.PSBK_SEQ = DDCUDRAC.PSBK_SEQ;
        DDCUDRDD.CHK_DRAC_FLG = WS_CHK_DRAC_FLG;
        DDCUDRDD.STD_AC = DDCUDRAC.AC;
        CEP.TRC(SCCGWA, DDCUDRDD.STD_AC);
        DDCUDRDD.VS_AC_FLG = WS_VS_AC_FLG;
        CEP.TRC(SCCGWA, WS_TEMP_OTH_STD_AC);
        DDCUDRDD.OTH_STD_AC = WS_TEMP_OTH_STD_AC;
        CEP.TRC(SCCGWA, DDCUDRDD.OTH_STD_AC);
        DDCUDRDD.OTH_STD_APP = WS_STD_APP;
        DDCUDRDD.CARD_BV_TYPE = WS_BV_TYPE;
        DDCUDRDD.PRDT_MODEL = DDCIQPRD.OUTPUT_DATA.PRDT_MODEL;
        DDCUDRDD.NOTCLEAR_FLG = WS_NOTCLEAR_FLG;
        DDCUDRDD.NOT_STD_AC_FLG = WS_NOT_STD_AC_FLG;
        if (DDCUDRAC.BV_TYP == '1' 
            && CICCUST.O_DATA.O_CI_TYP == '1') {
            DDCUDRDD.N_CARD_NO = CICQACRI.O_DATA.O_AGR_NO;
        }
        CEP.TRC(SCCGWA, DDCUDRDD.N_CARD_NO);
        DDCUDRDD.CARD_ADSC_TYPE = WS_CARD_ADSC_TYPE;
        DDCUDRDD.CARD_PROD_CD = WS_CARD_PROD_CD;
        DDCUDRDD.AUTO_DDTOTD_FLG = DDCUDRAC.AUTO_DDTOTD_FLG;
        DDCUDRDD.AID = DDCUDRAC.AID;
        DDCUDRDD.FRZ_STS_CHK = DDCUDRAC.FRZ_STS_CHK;
        DDCUDRDD.SIGN_FLG = DDCUDRAC.SIGN_FLG;
        DDCUDRDD.TXN_REGION = DDCUDRAC.TXN_REGION;
        DDCUDRDD.TXN_CHNL = DDCUDRAC.TXN_CHNL;
        DDCUDRDD.TXN_TYPE = DDCUDRAC.TXN_TYPE;
        DDCUDRDD.IN_OUT_FLG = DDCUDRAC.IN_OUT_FLG;
        DDCUDRDD.SNAME_FLG = DDCUDRAC.SNAME_FLG;
        DDCUDRDD.DNAME_FLG = DDCUDRAC.DNAME_FLG;
        DDCUDRDD.CANCEL_FLG = DDCUDRAC.CANCEL_FLG;
        DDCUDRDD.FEE_FLG = DDCUDRAC.FEE_FLG;
        DDCUDRDD.HIS_SHOW_FLG = DDCUDRAC.HIS_SHOW_FLG;
        DDCUDRDD.DELAY_FLG = DDCUDRAC.DELAY_FLG;
        DDCUDRDD.CHK_LMT_FLG = DDCUDRAC.CHK_LMT_FLG;
        DDCUDRDD.SMS_FLG = DDCUDRAC.SMS_FLG;
        DDCUDRDD.EA_CHK_FLG = DDCUDRAC.EA_CHK_FLG;
        DDCUDRDD.TRT_CTLW = DDCUDRAC.TRT_CTLW;
        if (DDCUDRAC.TSTS_TABL.equalsIgnoreCase("0014")) {
            if (DDCUDRDD.TRT_CTLW == null) DDCUDRDD.TRT_CTLW = "";
            JIBS_tmp_int = DDCUDRDD.TRT_CTLW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) DDCUDRDD.TRT_CTLW += " ";
            DDCUDRDD.TRT_CTLW = DDCUDRDD.TRT_CTLW.substring(0, 4 - 1) + "1" + DDCUDRDD.TRT_CTLW.substring(4 + 1 - 1);
        }
        if (DDCUDRDD.TRT_CTLW == null) DDCUDRDD.TRT_CTLW = "";
        JIBS_tmp_int = DDCUDRDD.TRT_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) DDCUDRDD.TRT_CTLW += " ";
        CEP.TRC(SCCGWA, DDCUDRDD.TRT_CTLW.substring(4 - 1, 4 + 1 - 1));
        DDCUDRDD.RLT_CI_TYP = DDCUDRAC.RLT_CI_TYP;
        CEP.TRC(SCCGWA, DDCUDRDD.RLT_CI_TYP);
        S000_CALL_DDZUDRDD();
        if (pgmRtn) return;
        DDCUDRAC.AC = DDCUDRDD.AC;
        DDCUDRAC.TX_AMT = DDCUDRDD.TX_AMT;
        CEP.TRC(SCCGWA, DDCUDRAC.TX_AMT);
    }
    public void B099_SURE_AUTO_TRS() throws IOException,SQLException,Exception {
        if (WS_PERS_FIN_FLG == 'Y') {
            IBS.init(SCCGWA, DCCUIQVA);
            DCCUIQVA.INP_DATA.SUB_AC = WS_TEMP_STD_AC;
            S000_CALL_DCZUIQVA();
            if (pgmRtn) return;
            WS_VA_AC1 = DCCUIQVA.OUTP_DATA.VIA_AC;
            if (DDCUDRAC.OTHER_AC.trim().length() > 0) {
                IBS.init(SCCGWA, DCCUIQVA);
                DCCUIQVA.INP_DATA.SUB_AC = DDCUDRAC.OTHER_AC;
                S000_CALL_DCZUIQVA();
                if (pgmRtn) return;
                WS_VA_AC2 = DCCUIQVA.OUTP_DATA.VIA_AC;
            }
            if (WS_VA_AC1.equalsIgnoreCase(WS_VA_AC2)) {
                WS_VA_SAME_FLG = 'Y';
            }
        }
        if (WS_COMP_INTEL_FLG == 'Y') {
            IBS.init(SCCGWA, DCCUSBEI);
            DCCUSBEI.IO_AREA.DD_AC = WS_TEMP_STD_AC;
            S000_CALL_DCZUSBEI();
            if (pgmRtn) return;
            WS_VA_AC1 = DCCUSBEI.OUTPUT.TD_AC;
            if (DDCUDRAC.OTHER_AC.trim().length() > 0) {
                IBS.init(SCCGWA, DCCUIQVA);
                DCCUIQVA.INP_DATA.SUB_AC = DDCUDRAC.OTHER_AC;
                S000_CALL_DCZUIQVA();
                if (pgmRtn) return;
                WS_VA_AC2 = DCCUIQVA.OUTP_DATA.VIA_AC;
            }
            if (WS_VA_AC1.equalsIgnoreCase(WS_VA_AC2)) {
                WS_VA_SAME_FLG = 'Y';
            }
        }
    }
    public void S000_CALL_DCZUIQVA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-INQ-VA-AC2", DCCUIQVA);
    }
    public void S000_CALL_DCZUSBEI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-ANDY-SERVICE", DCCUSBEI);
        if (DCCUSBEI.MSG_ID.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUSBEI.MSG_ID.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZPCDCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-P-CARD-TRKDAT-CHK", DCCPCDCK);
    }
    public void S000_LINK_BPZSEX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BPZSEX, BPCEX);
        CEP.TRC(SCCGWA, BPCEX.SELL_CCY);
        CEP.TRC(SCCGWA, BPCEX.SELL_AMT);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFCSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-STS-TBL-AUTH", BPCFCSTS);
        CEP.TRC(SCCGWA, BPCFCSTS.RC);
        if (BPCFCSTS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFCSTS.RC);
            WS_ERR_INFO = "AP-MMO = " + BPCFCSTS.AP_MMO + ",TBL-NO = " + BPCFCSTS.TBL_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUPSWM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DCZUPSWM, DCCUPSWM);
        if (DCCUPSWM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUPSWM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIMPAY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-MPAY-PROC", DDCIMPAY);
    }
    public void S000_CALL_DDZIMCHQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-CHQ-PROC", DDCIMCHQ);
    }
    public void S000_CALL_DDZIQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_I_INQ_DDPRD, DDCIQPRD);
        if (DDCIQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPCLWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CAL-WORK-DAY", BPCOCLWD);
        if (BPCOCLWD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOCLWD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT3 = new SCSSCLDT();
        SCSSCLDT3.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "LINK SCSSCLDT,CLDT-RC=" + SCCCLDT.RC;
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void S000_CALL_DDZUSIPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-U-SIPM-PROC", DDCUSIPM);
        if (DDCUSIPM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCUSIPM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZUZFMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DDZUZFMM", DDCUZFMM);
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF);
        if (DCCUCINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            WS_ERR_INFO = "UCINF-CARD-NO = " + DCCUCINF.CARD_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_UNI_CIZACCU, CICACCU);
        if (CICACCU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            WS_ERR_INFO = "ACCU-AGR-NO = " + CICACCU.DATA.AGR_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_FSZVMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "FS-I-INQ-VMST", FSCIVMST);
        if (FSCIVMST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, FSCIVMST.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.upd = true;
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTCCY_FIRST() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "AC = :DDRCCY.KEY.AC "
            + "AND CCY = :DDRCCY.CCY";
        DDTCCY_RD.fst = true;
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
    }
    public void T000_READ_UPDATE_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND);
        }
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "CUS_AC = :DDRCCY.CUS_AC "
            + "AND CCY = :DDRCCY.CCY "
            + "AND CCY_TYPE = :DDRCCY.CCY_TYPE";
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTCCY";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_DDTVCH() throws IOException,SQLException,Exception {
        DDTVCH_RD = new DBParm();
        DDTVCH_RD.TableName = "DDTVCH";
        DDTVCH_RD.upd = true;
        IBS.READ(SCCGWA, DDRVCH, DDTVCH_RD);
    }
    public void T000_READ_DDTMCAD() throws IOException,SQLException,Exception {
        DDTMCAD_RD = new DBParm();
        DDTMCAD_RD.TableName = "DDTMCAD";
        IBS.READ(SCCGWA, DDRMCAD, DDTMCAD_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READ_DDTADMN() throws IOException,SQLException,Exception {
        DDTADMN_RD = new DBParm();
        DDTADMN_RD.TableName = "DDTADMN";
        DDTADMN_RD.eqWhere = "AC,ADP_TYPE,ADP_STS";
        DDTADMN_RD.where = "ADP_STRDATE <= :DDRADMN.ADP_STRDATE";
        IBS.READ(SCCGWA, DDRADMN, this, DDTADMN_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        CEP.TRC(SCCGWA, BPCPRMM.RC);
    }
    public void R000_GET_CHQ_VALID_DT_PARM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, DDCPCHQ);
        DDCPCHQ.KEY.TYP = "PDD04";
        DDCPCHQ.KEY.CD = K_CHQ_INVALID_DATE;
        BPCPRMR.DAT_PTR = DDCPCHQ;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPRMR);
        CEP.TRC(SCCGWA, DDCPCHQ);
        CEP.TRC(SCCGWA, DDCPCHQ.DATA_TXT.DAYS);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.REQ_SYSTEM);
        if (SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("031600")) {
            DDCPCHQ.DATA_TXT.DAYS = (short) (DDCPCHQ.DATA_TXT.DAYS + 10);
        }
    }
    public void R000_STARTBR_BPTFHIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCIFHIS);
        IBS.init(SCCGWA, BPRFHIST);
        BPCIFHIS.INPUT.FUNC = 'A';
        BPCIFHIS.INPUT.JRNNO = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
        BPCIFHIS.INPUT.STR_AC_DT = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        BPCIFHIS.INPUT.END_AC_DT = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        BPCIFHIS.INPUT.REC_PT = BPRFHIST;
        BPCIFHIS.INPUT.REC_LEN = 690;
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.REC_LEN);
        S000_CALL_BPZIFHIS();
        if (pgmRtn) return;
    }
    public void R000_READNEXT_BPTFHIST() throws IOException,SQLException,Exception {
        BPCIFHIS.INPUT.FUNC = '2';
        BPCIFHIS.INPUT.REC_PT = BPRFHIST;
        BPCIFHIS.INPUT.REC_LEN = 690;
        S000_CALL_BPZIFHIS();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCIFHIS.OUTPUT.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_END_OF_TABLE)) {
            WS_CHK_FHIS_FLG = 'N';
        } else {
            WS_CHK_FHIS_FLG = 'Y';
        }
    }
    public void R000_WITHDRAW_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            IBS.init(SCCGWA, FSCTLEG);
            FSCTLEG.JRN_NO = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
            FSCTLEG.TRAN_DATE = SCCGWA.COMM_AREA.TR_DATE;
            IBS.CALLCPN(SCCGWA, "FS-I-CAPL-TLEG", FSCTLEG);
        } else {
            if (WS_AVL_BAL < DDCUDRAC.TX_AMT) {
                IBS.init(SCCGWA, FSCTLEG);
                FSCTLEG.TRANS_ATM = DDCUDRAC.TX_AMT - WS_AVL_BAL;
                if (FSCTLEG.TRANS_ATM > DDCUDRAC.TX_AMT) {
                    FSCTLEG.TRANS_ATM = DDCUDRAC.TX_AMT;
                }
                FSCTLEG.RES_ACCT = DDCUDRAC.AC;
                FSCTLEG.CCY = DDCUDRAC.CCY;
                FSCTLEG.CCY_TYP = DDCUDRAC.CCY_TYPE;
                FSCTLEG.ZY_CODE = DDCUDRAC.TX_MMO;
                FSCTLEG.TRAN_DATE = SCCGWA.COMM_AREA.AC_DATE;
                IBS.CALLCPN(SCCGWA, "FS-I-CAPL-TLEG", FSCTLEG);
            }
        }
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READ_UPDATE_DDTCCY();
        if (pgmRtn) return;
    }
    public void R000_ENDBR_BPTFHIST() throws IOException,SQLException,Exception {
        BPCIFHIS.INPUT.FUNC = '3';
        BPCIFHIS.INPUT.REC_PT = BPRFHIST;
        BPCIFHIS.INPUT.REC_LEN = 690;
        S000_CALL_BPZIFHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZIFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-INQ-FHIST", BPCIFHIS);
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            WS_ERR_INFO = "TYPE = " + DDCPCHQ.KEY.TYP + ",CODE = " + DDCPCHQ.KEY.CD;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCSCPARM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        CEP.TRC(SCCGWA, BPCPRMR.RC.RC_RTNCODE);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            if (BPCPRMR.RC.RC_RTNCODE == 180) {
                if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                    || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                    || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NOT_YEAR_CHK;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_DCZUDACH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CHECK-ACCOUNT", DCCUDACH);
        CEP.TRC(SCCGWA, DCCUDACH.RC);
        if (DCCUDACH.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUDACH.RC);
            WS_ERR_INFO = "UDACH-CARD-NO = " + DCCUDACH.DATA.CARD_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZURHLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-RHLD", DCCURHLD);
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        CEP.TRC(SCCGWA, DCCPACTY.INPUT.AC);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            WS_ERR_INFO = "ACTY-AC = " + DCCPACTY.INPUT.AC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUTCGD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-TDCGDD-PROC", DCCUTCGD);
    }
    public void S000_CALL_DDZUGPDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-GROUP-DRAW", DDCUGPDR);
    }
    public void S000_CALL_DDZSACCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-ACCH", DDCSACCH);
    }
    public void S000_CALL_DCZIQHLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-DCZIQHLD", DCCIQHLD);
        if (DCCIQHLD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCIQHLD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG-STATION", BPCPRGST);
        if (BPCPRGST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRGST.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-INQ-VIA-BAL", DCCIQBAL);
        CEP.TRC(SCCGWA, DCCIQBAL.RC);
        if (DCCIQBAL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCIQBAL.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_EAZUTRAN() throws IOException,SQLException,Exception {
        EAZUTRAN EAZUTRAN = new EAZUTRAN();
        EAZUTRAN.MP(SCCGWA, EACUTRAN);
    }
    public void S000_CALL_DCZPFTCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-P-FINANCE-TR-CHK", DCCPFTCK);
    }
    public void S000_CALL_CIZCKLS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CHECK-LST", CICCKLS);
        if (CICCKLS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCKLS.RC);
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
    }
    public void S000_CALL_DDZUADVT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-A-DETAIL-AC", DDCUADVT);
        if (DDCUADVT.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DDCUADVT.RC);
        }
    }
    public void S000_CALL_FSZUVSAT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "FS-M-VIRAC-BAL", FSCUVSAT);
        if (FSCUVSAT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, FSCUVSAT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        if (CICQACRI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACRI.RC);
        }
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
    }
    public void S000_CALL_DDZUDRDD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC-DD", DDCUDRDD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INFO);
    }
    public void S000_ERR_MSG_PROC1() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, DDRMST.EXP_DATE);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_ERR_MSG);
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
