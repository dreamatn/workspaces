package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.DC.*;
import com.hisun.FS.*;
import com.hisun.CI.*;
import com.hisun.LN.*;
import com.hisun.EA.*;
import com.hisun.AI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZUDRDD {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    DBParm DDTMST_RD;
    DBParm DDTCCY_RD;
    DBParm DDTVCH_RD;
    DBParm DDTGPMST_RD;
    DBParm DDTNOSI_RD;
    DBParm DDTADMN_RD;
    DBParm DDTRSAC_RD;
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DD907";
    String K_STS_TABLE_APP = "DD";
    String K_CUS_DR_STS_TBL_P = "0002";
    String K_CUS_DR_STS_TBL_C = "0012";
    String K_CUS_DR_STS_TBL_CAN = "0010";
    String K_CUS_DR_STS_TD_TO_DD = "0013";
    String K_CUS_DR_STS_LN = "0014";
    String K_CUS_DR_STS_FEE = "0016";
    String CPN_SCSSCKDT = "SCSSCKDT         ";
    String CPN_UNI_CIZACCU = "CI-INQ-ACCU      ";
    String CPN_I_BACK_DATE = "DD-I-BACK-PROC   ";
    String CPN_PROC_FHIS = "BP-PROC-FHIS     ";
    String CPN_VWA_CPNT = "BP-P-VWA-WRITE   ";
    String CPN_I_INQ_BAL = "DD-I-INQ-CCY-BAL ";
    String CPN_I_INQ_DDPRD = "DD-I-INQ-DDPRD   ";
    String CPN_UNIT_DRAW_PROC = "DD-UNIT-DRAW-PROC";
    String CPN_F_F_CAL_FEE = "BP-F-F-CAL-FEE   ";
    String CPN_F_F_TX_INFO = "BP-F-F-TX-INFO   ";
    String CPN_BPZSEX = "BP-EX";
    String CPN_I_PSBK_PROC = "DD-I-PSBK-PROC";
    String CPN_DCZUAINQ = "DC-U-CARD-AC-INQ";
    String CPN_DCZUPSWM = "DC-U-PSW-MAINTAIN";
    String CPN_BPZPRMR = "BP-PARM-READ";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String K_AP_MMO = "DD";
    String K_CASH_CR_AMT_TBL = "0001";
    String K_CASH_DR_AMT_TBL = "0002";
    String K_NORM_TR_AMT_TBL = "0003";
    String K_PC_TR_AMT_TBL = "0004";
    String K_OBJ_SYSTEM2 = "KHMS";
    String K_SERV_CODE2 = "BAT004";
    String K_OBJ_SYSTEM3 = "QCGS";
    String K_SERV_CODE3 = "63705 ";
    String K_CHQ_INVALID_DATE = "002";
    String K_AGT_TYP = "141290001013";
    String K_AGT_FUT = "141290001018";
    String K_CON_BANK_PROD_CD = "NDP00117";
    String K_OBJ_SYSTEM4 = "ESBP";
    String K_SERV_CODE4 = "3008200000102";
    String K_SERV_CODE5 = "3002200000903";
    String WS_ERR_MSG = " ";
    double WS_UOD_AMT = 0;
    double WS_AVL_BAL = 0;
    String WS_VS_AC = " ";
    String WS_TR_AC = " ";
    char WS_VS_AC_FLG = ' ';
    char WS_ADD_UPD_REC_FLG = ' ';
    char WS_CARD_LNK_TYP = ' ';
    char WS_CD_LNK_TYP = ' ';
    char WS_CARD_TYP = ' ';
    String WS_PROD_CD = " ";
    String WS_SVS_ADC_NO = " ";
    short WS_I = 0;
    short WS_IDX = 0;
    double WS_TOT_CAMT = 0;
    double WS_TOT_TAMT = 0;
    char WS_OTHER_CITYPE = ' ';
    char WS_AC_CITYPE = ' ';
    char WS_TMP_ACATTR = ' ';
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
    String WS_UDRDD_AC = " ";
    double WS_CURR_BAL = 0;
    char WS_CROS_DR_FLG = ' ';
    String WS_GROUP_AC = " ";
    String WS_RLT_AC_NAME = " ";
    double WS_DRW_CAMT = 0;
    double WS_LIMI_AMT = 0;
    char WS_BV_TYPE = ' ';
    String WS_ID_TYPE = " ";
    int WS_LAST_FN_DATE = 0;
    double WS_PRE_AMT = 0;
    String WS_OTH_CI_NO = " ";
    String WS_AGT_TYP = " ";
    double WS_USE_BAL = 0;
    char WS_WITHD_AC_FLG = ' ';
    char WS_WITHD_TOP_AC_FLG = ' ';
    char WS_WITHDR_FLG = ' ';
    double WS_VIA_AVL_BAL = 0;
    char WS_OVD_AMT_FLG = ' ';
    char WS_SAME_CITY_FLG = ' ';
    int WS_MST_BRANCH_BR = 0;
    int WS_GWA_BRANCH_BR = 0;
    String WS_REL_AC = " ";
    int WS_BASE_DATE = 0;
    int WS_AC_DATE = 0;
    char WS_REAL_VIR_FLG = ' ';
    char WS_VIR_MAIN_FLG = 'N';
    String WS_RES_NAME = " ";
    double WS_LAST_DAYEND_BAL = 0;
    double WS_DAYEND_BAL = 0;
    double WS_DIFF_BAL = 0;
    double WS_O_CURR_BAL = 0;
    double WS_N_CURR_BAL = 0;
    double WS_TEMP_VAL = 0;
    DDZUDRDD_WS_DATA_TO_SCZTPCL WS_DATA_TO_SCZTPCL = new DDZUDRDD_WS_DATA_TO_SCZTPCL();
    DDZUDRDD_WS_UDRDD_TMP WS_UDRDD_TMP = new DDZUDRDD_WS_UDRDD_TMP();
    short WS_DEC_MTH = 0;
    long WS_TRF_AMT = 0;
    char WS_FROM_CITYP = ' ';
    double WS_AMT = 0;
    int WS_LC_NO = 0;
    int WS_SEQ = 0;
    char WS_FUNC = ' ';
    String WS_CHNL = " ";
    int WS_CCY_OWNER_BR = 0;
    char WS_SETL_FLG = ' ';
    char WS_GROUP_AC_ACTION = ' ';
    char WS_COMP_INTEL_FLG = 'N';
    char WS_DATA_OVERFLOW_FLAG = ' ';
    char WS_WHITE_FLG = ' ';
    char WS_BLACK_FLG = ' ';
    char WS_CTL_TYPE = ' ';
    char WS_NOTICE_FLG = ' ';
    char WS_CHK_AC_FLG = ' ';
    char WS_CHG_PROD_FLG = ' ';
    char WS_CHECK_SVS_ADC_FLG = ' ';
    char WS_SNAME_JSH_FLG = ' ';
    String WS_ACO_PROD_CD = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDRFHIS DDRFHIS = new DDRFHIS();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    DDCIUPT DDCIUPT = new DDCIUPT();
    DDRCCY DDRCCY = new DDRCCY();
    DDRMST DDRMST = new DDRMST();
    DDRMST DDROMST = new DDRMST();
    DDRMST DDRFMST = new DDRMST();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDVMRAT DDVMRAT = new DDVMRAT();
    BPCTCALF BPCTCALF = new BPCTCALF();
    DCCIQBAL DCCIQBAL = new DCCIQBAL();
    DCCIQHLD DCCIQHLD = new DCCIQHLD();
    DCCUCINF DCCUCINO = new DCCUCINF();
    SCCSTAR SCCSTAR = new SCCSTAR();
    DDRVCH DDRVCH = new DDRVCH();
    DDCIPSBK DDCIPSBK = new DDCIPSBK();
    DPCPARMP DPCPARMP = new DPCPARMP();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    FSCRSPAY FSCRSPAY = new FSCRSPAY();
    FSCTLEG FSCTLEG = new FSCTLEG();
    DDCODRNT DDCODRNT = new DDCODRNT();
    DDCIBACK DDCIBACK = new DDCIBACK();
    DDCIMPAY DDCIMPAY = new DDCIMPAY();
    DDCIMCHQ DDCIMCHQ = new DDCIMCHQ();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    DDCICHQD DDCICHQD = new DDCICHQD();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    CICACCU CICACCU = new CICACCU();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCCLDT SCCCLDT = new SCCCLDT();
    DCCUAINQ DCCUAINQ = new DCCUAINQ();
    DDCUADVT DDCUADVT = new DDCUADVT();
    DCCUPSWM DCCUPSWM = new DCCUPSWM();
    DDCPLIMT DDCPLIMT = new DDCPLIMT();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DCCUDACH DCCUDACH = new DCCUDACH();
    DCCURHLD DCCURHLD = new DCCURHLD();
    DCCPFTCK DCCPFTCK = new DCCPFTCK();
    CICREGLC CICREGLC = new CICREGLC();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    DDCUSIPM DDCUSIPM = new DDCUSIPM();
    DDCUZFMM DDCUZFMM = new DDCUZFMM();
    DCCUCINF DCCUCINF = new DCCUCINF();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DCCITRSR DCCITRSR = new DCCITRSR();
    BPCPQORG BPCPQORG = new BPCPQORG();
    DCCPCDCK DCCPCDCK = new DCCPCDCK();
    BPCIBUDT BPCIBUDT = new BPCIBUDT();
    LNRSETL LNRSETL = new LNRSETL();
    SCCTPCL SCCTPCL = new SCCTPCL();
    DDCNKHMS DDCNKHMS = new DDCNKHMS();
    BPCPRGST BPCPRGST = new BPCPRGST();
    DDCPCHQ DDCPCHQ = new DDCPCHQ();
    CICMAGT CICMAGT = new CICMAGT();
    DDRNOSI DDRNOSI = new DDRNOSI();
    DDRGPMST DDRGPMST = new DDRGPMST();
    DDCYERCK DDCYERCK = new DDCYERCK();
    DDCSOLCD DDCSOLCD = new DDCSOLCD();
    BPCFSCHG BPCFSCHG = new BPCFSCHG();
    DDCIDREG DDCIDREG = new DDCIDREG();
    DDRADMN DDRADMN = new DDRADMN();
    CICCUST CICCUST = new CICCUST();
    CICCUST CICOCUST = new CICCUST();
    CICCUST CICKCUST = new CICCUST();
    DDCUQBFJ DDCUQBFJ = new DDCUQBFJ();
    CICQACAC CICQACAC = new CICQACAC();
    CICQACRL CICQACRL = new CICQACRL();
    CICMLMT CICMLMT = new CICMLMT();
    CICQACRI CICQACRI = new CICQACRI();
    CICQACRI CICOACRI = new CICQACRI();
    DDCPDZZH DDCPDZZH = new DDCPDZZH();
    EACUTRAN EACUTRAN = new EACUTRAN();
    BPCFX BPCFX = new BPCFX();
    DDRRSAC DDRRSAC = new DDRRSAC();
    AICPQMIB AICPQMIB = new AICPQMIB();
    CICCKLS CICCKLS = new CICCKLS();
    CICSPVS CICSPVS = new CICSPVS();
    BPCPARMC BPCPARMC = new BPCPARMC();
    FSCIVMST FSCIVMST = new FSCIVMST();
    FSCSVTX FSCSVTX = new FSCSVTX();
    FSCBTE FSCBTE = new FSCBTE();
    CICMLRG CICMLRG = new CICMLRG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    CICPDTL CICPDTL = new CICPDTL();
    SCCGWA SCCGWA;
    DDCUDRDD DDCUDRDD;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPCPORUP_DATA_INFO BPCPORUP;
    public void MP(SCCGWA SCCGWA, DDCUDRDD DDCUDRDD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCUDRDD = DDCUDRDD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        if (WS_WITHD_AC_FLG == 'Y' 
            && (WS_WITHDR_FLG == 'Y' 
            || SCCGWA.COMM_AREA.CANCEL_IND == 'Y')) {
            R000_WITHDRAW_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "DDZUDRDD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GET_AC_INF_PROC();
        if (pgmRtn) return;
        B015_CHECK_CI_LIST();
        if (pgmRtn) return;
        B040_GET_CI_INF_PROC();
        if (pgmRtn) return;
        B030_CHK_AC_STS();
        if (pgmRtn) return;
        B060_GET_PRD_INF_PROC();
        if (pgmRtn) return;
        B070_CHK_PSBK_PROC();
        if (pgmRtn) return;
        B075_CHK_CARD_PSW_PROC();
        if (pgmRtn) return;
        B070_GET_CCY_INF_PROC();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (!JIBS_tmp_str[0].equalsIgnoreCase("111804") 
            && DDCUDRDD.SIGN_FLG != 'Y' 
            && DDCUDRDD.DELAY_FLG != 'Y') {
            B075_CARD_LIMT_BAL_CHK();
            if (pgmRtn) return;
        }
        B100_CHECK_TXN_AMT_LIMIT();
        if (pgmRtn) return;
        B050_CHECK_STS_TBL_PROC();
        if (pgmRtn) return;
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDCUDRDD.TRT_CTLW == null) DDCUDRDD.TRT_CTLW = "";
        JIBS_tmp_int = DDCUDRDD.TRT_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) DDCUDRDD.TRT_CTLW += " ";
        if (DDRCCY.STS_WORD.substring(63 - 1, 63 + 1 - 1).equalsIgnoreCase("1") 
            && !DDCUDRDD.TRT_CTLW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            WS_WITHD_AC_FLG = 'Y';
        }
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD.substring(62 - 1, 62 + 1 - 1).equalsIgnoreCase("1")) {
            WS_WITHD_TOP_AC_FLG = 'Y';
        }
        B080_CHECK_AVA_BAL_PROC();
        if (pgmRtn) return;
        B090_UPD_BAL_PROC();
        if (pgmRtn) return;
        B110_UPD_CCY_INF_PROC();
        if (pgmRtn) return;
        B120_UPD_GRP_AC_CTL_BAL();
        if (pgmRtn) return;
        B130_UPD_MST_INF_PROC();
        if (pgmRtn) return;
        if (DDCUDRDD.TRT_CTLW == null) DDCUDRDD.TRT_CTLW = "";
        JIBS_tmp_int = DDCUDRDD.TRT_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) DDCUDRDD.TRT_CTLW += " ";
        if (!DDCUDRDD.TRT_CTLW.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("1")) {
            B076_CHECK_LMT_PROC();
            if (pgmRtn) return;
        }
        if (DDCUDRDD.TX_MMO.equalsIgnoreCase("A503")) {
            B079_REGIN_LIMT_INFO();
            if (pgmRtn) return;
        }
        if (WS_CHECK_SVS_ADC_FLG == 'Y') {
            B077_REG_FRGLMT_PROC();
            if (pgmRtn) return;
        }
        if (WS_NOTICE_FLG == 'Y') {
            B078_ADP_NOTICE_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDRCCY.AC_TYPE);
        if (DDRCCY.AC_TYPE != '6') {
            B140_PRT_UNPT_ITEM();
            if (pgmRtn) return;
        }
        B150_BACK_VALUE_PROC();
        if (pgmRtn) return;
        B170_FIN_TX_HIS_PROC();
        if (pgmRtn) return;
        if (WS_REAL_VIR_FLG == 'Y' 
            || WS_VIR_MAIN_FLG == 'Y') {
            B175_VIR_VTXD_PROC();
            if (pgmRtn) return;
            B180_NOTICE_BTE_SYS_PROC();
            if (pgmRtn) return;
        }
        B180_UPD_TLR_TOT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        if ((SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != DDRCCY.OWNER_BR 
            && DDCUDRDD.BANK_DR_FLG != 'Y' 
            && ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")) 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("SDS")))) {
            CEP.TRC(SCCGWA, "TEST");
        }
        WS_CCY_OWNER_BR = DDRCCY.OWNER_BR;
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        JIBS_tmp_str[1] = "" + WS_CCY_OWNER_BR;
        JIBS_tmp_int = JIBS_tmp_str[1].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
        JIBS_tmp_str[3] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (!JIBS_tmp_str[0].substring(0, 3).equalsIgnoreCase(JIBS_tmp_str[1].substring(0, 3)) 
            && JIBS_tmp_str[3].equalsIgnoreCase("0111100")) {
            CEP.TRC(SCCGWA, "POCTEST");
            WS_SAME_CITY_FLG = 'N';
            B250_FEE_PROC();
            if (pgmRtn) return;
        }
        if (WS_CHG_PROD_FLG == 'Y') {
            B209_CHANGE_PROD_PROC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "CHG PRD");
        }
        if (!(DDCUDRDD.CLEAR_FLG == 'Y' 
            && DDCUDRDD.TX_AMT == 0) 
            && DDCUDRDD.HIS_SHOW_FLG != 'N') {
            B210_GEN_VCH_PROC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "TESTING");
        }
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_TYP);
        CEP.TRC(SCCGWA, DDCUDRDD.TX_TYPE);
        CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
        CEP.TRC(SCCGWA, DDRCCY.CCAL_TOT_BAL);
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, DDCUDRDD.AC);
        CEP.TRC(SCCGWA, DDCUDRDD.CCY);
        CEP.TRC(SCCGWA, DDCUDRDD.CCY_TYPE);
        CEP.TRC(SCCGWA, DDCUDRDD.TX_AMT);
        CEP.TRC(SCCGWA, DDCUDRDD.VAL_DATE);
        CEP.TRC(SCCGWA, DDCUDRDD.ID_TYPE);
        CEP.TRC(SCCGWA, DDCUDRDD.ID_NO);
        CEP.TRC(SCCGWA, DDCUDRDD.PSBK_NO);
        CEP.TRC(SCCGWA, DDCUDRDD.CARD_NO);
        CEP.TRC(SCCGWA, DDCUDRDD.CHQ_TYPE);
        CEP.TRC(SCCGWA, DDCUDRDD.CHQ_NO);
        CEP.TRC(SCCGWA, DDCUDRDD.PAY_PSWD);
        CEP.TRC(SCCGWA, DDCUDRDD.TX_TYPE);
        CEP.TRC(SCCGWA, DDCUDRDD.CLEAR_FLG);
        CEP.TRC(SCCGWA, DDCUDRDD.OTHER_AC);
        CEP.TRC(SCCGWA, DDCUDRDD.OTHER_CCY);
        CEP.TRC(SCCGWA, DDCUDRDD.OTHER_AMT);
        CEP.TRC(SCCGWA, DDCUDRDD.NARRATIVE);
        CEP.TRC(SCCGWA, DDCUDRDD.TX_MMO);
        CEP.TRC(SCCGWA, DDCUDRDD.TX_REF);
        CEP.TRC(SCCGWA, DDCUDRDD.REMARKS);
        CEP.TRC(SCCGWA, DDCUDRDD.CHK_DRAC_FLG);
        if (DDCUDRDD.CHK_DRAC_FLG != 'Y') {
            if (DDCUDRDD.AC.trim().length() == 0 
                && DDCUDRDD.CARD_NO.trim().length() == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCUDRDD.CCY.trim().length() == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCUDRDD.CCY_TYPE == ' ' 
                && DDCUDRDD.CCY.equalsIgnoreCase("156") 
                && DDCUDRDD.AID.trim().length() > 0) {
                DDCUDRDD.CCY_TYPE = '1';
            }
            if (DDCUDRDD.CCY_TYPE == ' ' 
                && DDCUDRDD.AID.trim().length() > 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_TYPE_M_INPUT);
            }
            if (DDCUDRDD.TX_AMT == 0 
                && DDCUDRDD.CLEAR_FLG != 'Y') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AMT_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCUDRDD.VAL_DATE != 0) {
                CEP.TRC(SCCGWA, DDCUDRDD.REMARKS);
                IBS.init(SCCGWA, SCCCKDT);
                SCCCKDT.DATE = DDCUDRDD.VAL_DATE;
                SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
                SCSSCKDT1.MP(SCCGWA, SCCCKDT);
                if (SCCCKDT.RC != 0) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BAK_DT_INVALID;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            } else {
                if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                    DDCUDRDD.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                } else {
                    DDCUDRDD.VAL_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
                }
            }
            CEP.TRC(SCCGWA, DDCUDRDD.VAL_DATE);
            if (DDCUDRDD.VAL_DATE > SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BAK_DT_INVALID;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCUDRDD.TX_TYPE != 'T' 
                && DDCUDRDD.TX_TYPE != 'C') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_TC_FLG_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, DDCUDRDD.OTH_TX_TOOL);
            CEP.TRC(SCCGWA, DDCUDRDD.CARD_NO);
            if (DDCUDRDD.CARD_NO.equalsIgnoreCase(DDCUDRDD.OTH_TX_TOOL) 
                && DDCUDRDD.CARD_NO.trim().length() > 0) {
                DDCUDRDD.BANK_DR_FLG = 'Y';
            }
            if (DDCUDRDD.BANK_DR_FLG == ' ') {
                DDCUDRDD.BANK_DR_FLG = 'N';
            } else {
                if ((DDCUDRDD.BANK_DR_FLG != 'Y') 
                    && (DDCUDRDD.BANK_DR_FLG != 'N')) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BANK_DR_FLG_INVALID;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            CEP.TRC(SCCGWA, DDCUDRDD.CHK_PSW_FLG);
            if (DDCUDRDD.CHK_PSW_FLG == ' ') {
                DDCUDRDD.CHK_PSW_FLG = 'Y';
            }
        }
        CEP.TRC(SCCGWA, DDCUDRDD.CCY);
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = DDCUDRDD.CCY;
        S000_CALL_BPZQCCY();
        if (pgmRtn) return;
        WS_DEC_MTH = BPCQCCY.DATA.DEC_MTH;
        CEP.TRC(SCCGWA, WS_DEC_MTH);
        CEP.TRC(SCCGWA, BPCQCCY.DATA.DEC_MTH);
        if (WS_DEC_MTH == 0) {
            WS_TRF_AMT = (long) DDCUDRDD.TX_AMT;
            CEP.TRC(SCCGWA, DDCUDRDD.TX_AMT);
            CEP.TRC(SCCGWA, WS_TRF_AMT);
            if (DDCUDRDD.TX_AMT != WS_TRF_AMT) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AMT_NOT_DECIMAL;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DDCUDRDD.TXN_REGION == ' ') {
            DDCUDRDD.TXN_REGION = '0';
        }
        if (DDCUDRDD.SIGN_FLG == 'Y' 
            || DDCUDRDD.DELAY_FLG == 'Y' 
            || (DDCUDRDD.FEE_FLG == 'Y' 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) 
            || DDCUDRDD.CHK_LMT_FLG == '4') {
            if (DDCUDRDD.TRT_CTLW == null) DDCUDRDD.TRT_CTLW = "";
            JIBS_tmp_int = DDCUDRDD.TRT_CTLW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) DDCUDRDD.TRT_CTLW += " ";
            DDCUDRDD.TRT_CTLW = DDCUDRDD.TRT_CTLW.substring(0, 6 - 1) + "1" + DDCUDRDD.TRT_CTLW.substring(6 + 1 - 1);
        }
        WS_CHNL = SCCGWA.COMM_AREA.CHNL;
        CEP.TRC(SCCGWA, WS_CHNL);
    }
    public void B015_CHECK_CI_LIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCKLS);
        CICCKLS.DATA.AGR_NO = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        CEP.TRC(SCCGWA, CICCKLS.DATA.AGR_NO);
        CICCKLS.DATA.AP_TYPE = "200";
        CICCKLS.DATA.EXP_MMO = DDCUDRDD.TX_MMO;
        CICCKLS.DATA.ACAC_NO = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        S000_CALL_CIZCKLS();
        if (pgmRtn) return;
    }
    public void B020_GET_AC_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.FUNC = 'I';
        if (DDCUDRDD.CARD_NO.trim().length() > 0 
            && DDCUDRDD.AC.trim().length() == 0) {
            CICQACRL.DATA.AC_NO = DDCUDRDD.CARD_NO;
        } else {
            CICQACRL.DATA.AC_NO = DDCUDRDD.AC;
        }
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = DDCUDRDD.AC;
        DDRCCY.CCY = DDCUDRDD.CCY;
        DDRCCY.CCY_TYPE = DDCUDRDD.CCY_TYPE;
        T000_READ_DDTCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCUDRDD.AC);
        CEP.TRC(SCCGWA, DDCUDRDD.CCY);
        CEP.TRC(SCCGWA, DDCUDRDD.CCY_TYPE);
        CEP.TRC(SCCGWA, DDRCCY.AC_TYPE);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            if (DDRCCY.AC_TYPE == '7') {
                WS_REAL_VIR_FLG = 'Y';
                CEP.TRC(SCCGWA, "DYQ USE VIRTUAL ACCOUNT ");
                IBS.init(SCCGWA, FSCIVMST);
                FSCIVMST.DATA.ACC_NO = DDRCCY.CUS_AC;
                S000_CALL_FSZVMST();
                if (pgmRtn) return;
                CICQACRL.DATA.AC_NO = FSCIVMST.O_DATA.O_UPACC_NO;
                CEP.TRC(SCCGWA, FSCIVMST.O_DATA.O_UPACC_NO);
            }
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD.substring(64 - 1, 64 + 1 - 1).equalsIgnoreCase("1")) {
                WS_VIR_MAIN_FLG = 'Y';
            }
        }
        CEP.TRC(SCCGWA, CICQACRL.DATA.AC_NO);
        S000_CALL_CIZQACRL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRL.RC.RC_CODE);
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_AC_REL);
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
        if (CICQACRL.RC.RC_CODE == 8054) {
            WS_REL_AC = CICQACRL.DATA.AC_NO;
            if (DDCUDRDD.CARD_NO.trim().length() > 0 
                && DDCUDRDD.AC.trim().length() > 0 
                && !DDCUDRDD.AC.equalsIgnoreCase(DDCUDRDD.CARD_NO)) {
                CICQACRL.O_DATA.O_AC_REL = "04";
            }
        } else if ((CICQACRL.RC.RC_CODE == 0 
                && (CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("03") 
                || CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("02") 
                || CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("04") 
                || CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("09") 
                || CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("13")))) {
            WS_REL_AC = CICQACRL.O_DATA.O_REL_AC_NO;
            if (CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("09") 
                && WS_REL_AC.equalsIgnoreCase(DDCUDRDD.OTHER_AC)) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_CAN_NOT_SAME);
            }
        } else if (CICQACRL.RC.RC_CODE == 0 
                && CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("01")) {
            WS_REL_AC = CICQACRL.O_DATA.O_REL_AC_NO;
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
            && !DDCUDRDD.CCY.equalsIgnoreCase("156")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_LNK_CARD_CHY_ONLY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_REL_AC);
        if (CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("04") 
            && WS_REL_AC.equalsIgnoreCase(DDCUDRDD.OTHER_AC)) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_CAN_NOT_SAME);
        }
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = WS_REL_AC;
        CICQACAC.DATA.CCY_ACAC = DDCUDRDD.CCY;
        CICQACAC.DATA.CR_FLG = DDCUDRDD.CCY_TYPE;
        CICQACAC.DATA.AID = DDCUDRDD.AID;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        WS_ACO_PROD_CD = CICQACAC.O_DATA.O_ACAC_DATA.O_PROD_CD_ACAC;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_STS);
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_STS == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_CLEARED);
        }
        IBS.init(SCCGWA, CICQACRI);
        IBS.init(SCCGWA, CICOACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = WS_REL_AC;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CICQACRI, CICOACRI);
        if (CICOACRI.O_DATA.O_SVS_ADC_NO.trim().length() > 0) {
            WS_SVS_ADC_NO = CICOACRI.O_DATA.O_SVS_ADC_NO;
            WS_CHECK_SVS_ADC_FLG = 'Y';
        }
        IBS.init(SCCGWA, CICQACRI);
        IBS.init(SCCGWA, DDRMST);
        IBS.init(SCCGWA, DDROMST);
        if (DDCUDRDD.TX_TYPE == 'T' 
            && DDCUDRDD.OTHER_AC.trim().length() > 0) {
            CEP.TRC(SCCGWA, DDCUDRDD.OTHER_AC);
            CICQACRI.FUNC = 'A';
            CICQACRI.DATA.AGR_NO = DDCUDRDD.OTHER_AC;
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_CI_NO);
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
                DDRMST.KEY.CUS_AC = DDCUDRDD.OTHER_AC;
                T000_READ_DDTMST();
                if (pgmRtn) return;
            }
            IBS.CLONE(SCCGWA, DDRMST, DDROMST);
        }
        IBS.init(SCCGWA, DCCUCINF);
        IBS.init(SCCGWA, DCCUCINO);
        if (WS_CHK_AC_FLG == '1') {
            DCCUCINF.CARD_NO = WS_TR_AC;
            S000_CALL_DCZUCINF();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, DCCUCINF, DCCUCINO);
            CEP.TRC(SCCGWA, DCCUCINO.CARD_HLDR_CINO);
            WS_PROD_CD = DCCUCINO.PROD_CD;
            IBS.init(SCCGWA, CICCUST);
            IBS.init(SCCGWA, CICKCUST);
            CICCUST.FUNC = 'C';
            CICCUST.DATA.CI_NO = DCCUCINO.CARD_HLDR_CINO;
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
        IBS.init(SCCGWA, DDRMST);
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
            DDRMST.KEY.CUS_AC = WS_REL_AC;
            T000_READ_UPDATE_DDTMST();
            if (pgmRtn) return;
            if (WS_PROD_CD.trim().length() == 0) {
                WS_PROD_CD = DDRMST.PROD_CODE;
            }
            if (DDCUDRDD.TX_TYPE == 'T' 
                && DDROMST.AC_TYPE != 'B' 
                && CICQACRI.O_DATA.O_CI_NO.equalsIgnoreCase(CICOACRI.O_DATA.O_CI_NO)) {
                WS_SNAME_JSH_FLG = 'Y';
            } else {
                WS_SNAME_JSH_FLG = 'N';
            }
            CEP.TRC(SCCGWA, WS_SNAME_JSH_FLG);
            CEP.TRC(SCCGWA, DDCUDRDD.TX_TYPE);
            CEP.TRC(SCCGWA, DDROMST.AC_TYPE);
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_CI_NO);
            CEP.TRC(SCCGWA, CICOACRI.O_DATA.O_CI_NO);
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDCUDRDD.TRT_CTLW == null) DDCUDRDD.TRT_CTLW = "";
            JIBS_tmp_int = DDCUDRDD.TRT_CTLW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) DDCUDRDD.TRT_CTLW += " ";
            if (DDRMST.AC_STS_WORD.substring(23 - 1, 23 + 1 - 1).equalsIgnoreCase("1") 
                && DDCUDRDD.CCY.equalsIgnoreCase("156") 
                && DDCUDRDD.TXN_REGION == '0' 
                && DDCUDRDD.TX_TYPE != 'C' 
                && !DDCUDRDD.TRT_CTLW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
                && ((!WS_CHNL.equalsIgnoreCase("10202") 
                && !WS_CHNL.equalsIgnoreCase("10203")) 
                || ((WS_CHNL.equalsIgnoreCase("10202") 
                || WS_CHNL.equalsIgnoreCase("10203")) 
                && WS_SNAME_JSH_FLG == 'N'))) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_HMT_AC_TRAN_OUT_FBD;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (JIBS_tmp_str[0].equalsIgnoreCase("115846") 
            && (DDRMST.AC_TYPE == 'D' 
            || DDRMST.AC_TYPE == 'E' 
            || DDRMST.AC_TYPE == 'F') 
            && DDCUDRDD.TX_TYPE == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CSH_FORBIDEN;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_ID.TR_CODE);
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(49 - 1, 49 + 1 - 1).equalsIgnoreCase("1") 
            && (DDCUDRDD.TX_MMO.equalsIgnoreCase("GTU") 
            || DDCUDRDD.TX_MMO.equalsIgnoreCase("X16"))) {
            CEP.TRC(SCCGWA, DDCUDRDD.TX_MMO);
            WS_GROUP_AC_ACTION = 'U';
            WS_GROUP_AC = DDCUDRDD.AC;
        }
        CEP.TRC(SCCGWA, DDRMST.TRF_DATE);
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            && GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE != SCCGWA.COMM_AREA.AC_DATE 
            && DDRMST.TRF_DATE != 0 
            && DDRMST.TRF_DATE >= GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_ITM_CHG);
        }
        WS_TMP_ACATTR = DDRMST.AC_TYPE;
    }
    public void B030_CHK_AC_STS() throws IOException,SQLException,Exception {
        if (DDCUDRDD.CHK_DRAC_FLG != 'Y') {
            if (DDRMST.AC_STS == 'C') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDRMST.AC_STS == 'O' 
                && !(DDCUDRDD.TX_MMO.equalsIgnoreCase("067") 
                && SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP"))) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INACTIVE_AC;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            if (DDRMST.EXP_DATE > 0 
                && DDRMST.EXP_DATE < SCCGWA.COMM_AREA.AC_DATE 
                && !DDCUDRDD.TSTS_TABL.equalsIgnoreCase("0014") 
                && !JIBS_tmp_str[1].equalsIgnoreCase("115846")) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_EXPIRED;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_TYP);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR);
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_AC_REL);
        IBS.init(SCCGWA, DCCUCINF);
        if (CICCUST.O_DATA.O_CI_TYP == '1' 
            && CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DC")) {
            DCCUCINF.CARD_NO = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        }
        if (CICCUST.O_DATA.O_CI_TYP == '2' 
            && CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD") 
            && CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("04")) {
            DCCUCINF.CARD_NO = DDCUDRDD.CARD_NO;
        }
        if (DCCUCINF.CARD_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, DCCUCINF.CARD_NO);
            S000_CALL_DCZUCINF();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DCCUCINF.CARD_STS);
            if (DCCUCINF.CARD_STS == 'C') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CARD_CLOSED);
            }
            DDCUDRDD.CARD_ADSC_TYPE = DCCUCINF.ADSC_TYPE;
            DDCUDRDD.CARD_PROD_CD = DCCUCINF.PROD_CD;
            WS_PROD_CD = DCCUCINF.PROD_CD;
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
        if (DDCUDRDD.CHK_DRAC_FLG != 'Y') {
            if (DDCUDRDD.TX_TYPE == 'C' 
                && DDRMST.FRG_IND.equalsIgnoreCase("OSA")) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.M_OSA_AC_NOT_DR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
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
            CEP.TRC(SCCGWA, DDCUDRDD.TX_TYPE);
            if ((DDRMST.CASH_FLG == '2' 
                || DDRMST.CASH_FLG == '4') 
                && DDCUDRDD.TX_TYPE == 'C') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CANT_DRW;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDRMST.AC_TYPE == 'D' 
                && DDCUDRDD.TX_TYPE == 'C') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_GEN_AC_NOT_CDRW;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(14 - 1, 14 + 1 - 1).equalsIgnoreCase("1")) {
                IBS.init(SCCGWA, BPCPRMR);
                IBS.init(SCCGWA, DDCYERCK);
                DDCYERCK.KEY.TYP = "PDD14";
                DDCYERCK.KEY.CD = DDCUDRDD.TX_MMO;
                BPCPRMR.DAT_PTR = DDCYERCK;
                CEP.TRC(SCCGWA, DDCYERCK.KEY.CD);
                S000_CALL_SCSCPARM();
                if (pgmRtn) return;
            }
        }
    }
    public void B040_GET_CI_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        IBS.init(SCCGWA, CICOCUST);
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        if (DDCUDRDD.TX_TYPE == 'T' 
            && !CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI") 
            && DDCUDRDD.OTHER_AC.trim().length() > 0) {
            CICCUST.FUNC = 'A';
            CICCUST.DATA.AGR_NO = DDCUDRDD.OTHER_AC;
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, CICCUST, CICOCUST);
            WS_RES_NAME = CICQACRI.O_DATA.O_AC_CNM;
        }
        IBS.init(SCCGWA, AICPQMIB);
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
            AICPQMIB.INPUT_DATA.AC = DDCUDRDD.OTHER_AC;
            S000_CALL_AIZPQMIB();
            if (pgmRtn) return;
            WS_RES_NAME = AICPQMIB.OUTPUT_DATA.CHS_NM;
        }
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'C';
        CICCUST.DATA.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_STSW);
        if (CICCUST.O_DATA.O_STSW == null) CICCUST.O_DATA.O_STSW = "";
        JIBS_tmp_int = CICCUST.O_DATA.O_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICCUST.O_DATA.O_STSW += " ";
        if (CICCUST.O_DATA.O_STSW.substring(22 - 1, 22 + 1 - 1).equalsIgnoreCase("1") 
            && DDCUDRDD.TX_TYPE == 'C' 
            && (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")) 
            && SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_IDEXP_AUTHOR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDCUDRDD.CLEAR_FLG);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_TYP);
        CEP.TRC(SCCGWA, DDCUDRDD.TX_TYPE);
        if (DDCUDRDD.TX_TYPE == 'C' 
            && DDCUDRDD.CCY_TYPE == '2' 
            && CICCUST.O_DATA.O_CI_TYP != '2' 
            && DDCUDRDD.CLEAR_FLG != 'Y') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_REMIT_AC_CANT_CASH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCUDRDD.TX_TYPE == 'C' 
            && DDCUDRDD.CCY_TYPE == '2' 
            && CICCUST.O_DATA.O_CI_TYP == '3') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_REMIT_AC_CANT_CASH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_ID_TYPE = DDCUDRDD.ID_TYPE;
    }
    public void B050_CHECK_STS_TBL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, BPCPARMC);
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1") 
            || DDRMST.AC_STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
            BPCPRMR.FUNC = ' ';
            BPCPRMR.TYP = "PARMC";
            if (BPCPRMR.CD == null) BPCPRMR.CD = "";
            JIBS_tmp_int = BPCPRMR.CD.length();
            for (int i=0;i<40-JIBS_tmp_int;i++) BPCPRMR.CD += " ";
            BPCPRMR.CD = "MMO" + BPCPRMR.CD.substring(3);
            if (BPCPRMR.CD == null) BPCPRMR.CD = "";
            JIBS_tmp_int = BPCPRMR.CD.length();
            for (int i=0;i<40-JIBS_tmp_int;i++) BPCPRMR.CD += " ";
            if (DDCUDRDD.TX_MMO == null) DDCUDRDD.TX_MMO = "";
            JIBS_tmp_int = DDCUDRDD.TX_MMO.length();
            for (int i=0;i<5-JIBS_tmp_int;i++) DDCUDRDD.TX_MMO += " ";
            BPCPRMR.CD = BPCPRMR.CD.substring(0, 6 - 1) + DDCUDRDD.TX_MMO + BPCPRMR.CD.substring(6 + 9 - 1);
            BPCPRMR.DAT_PTR = BPCPARMC;
            S000_CALL_BPZPRMR();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCPARMC.DATA_TXT.RBASE_TYP.RBASE_TYP3);
        if (DDCUDRDD.CHK_DRAC_FLG != 'Y') {
            IBS.init(SCCGWA, BPCFCSTS);
            if (DDCUDRDD.CARD_NO.trim().length() > 0) {
                BPCFCSTS.MSG_INFO = DDCUDRDD.CARD_NO;
            } else {
                BPCFCSTS.MSG_INFO = DDCUDRDD.AC;
            }
            CEP.TRC(SCCGWA, BPCFCSTS.MSG_INFO);
            BPCFCSTS.AP_MMO = K_STS_TABLE_APP;
            if (DDCUDRDD.TSTS_TABL.trim().length() > 0) {
                BPCFCSTS.TBL_NO = DDCUDRDD.TSTS_TABL;
            } else {
                if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                    CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_TYP);
                    if (CICCUST.O_DATA.O_CI_TYP == '1') {
                        BPCFCSTS.TBL_NO = K_CUS_DR_STS_TBL_P;
                    } else {
                        BPCFCSTS.TBL_NO = K_CUS_DR_STS_TBL_C;
                    }
                } else {
                    BPCFCSTS.TBL_NO = K_CUS_DR_STS_TBL_CAN;
                }
            }
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
            if (DDCUDRDD.FEE_FLG == 'Y' 
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
            if (DDCUDRDD.CHK_PSW_FLG == 'N') {
                if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
                JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
                for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
                BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 106 - 1) + "0" + BPCFCSTS.STATUS_WORD.substring(106 + 1 - 1);
            }
            if (DDCUDRDD.SIGN_FLG == 'Y' 
                || BPCPARMC.DATA_TXT.RBASE_TYP.RBASE_TYP3 == 'Y') {
                if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
                JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
                for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
                BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 107 - 1) + "00" + BPCFCSTS.STATUS_WORD.substring(107 + 2 - 1);
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
            if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DC")) {
                if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
                JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
                for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
                BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 101 - 1) + "000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000" + BPCFCSTS.STATUS_WORD.substring(101 + 99 - 1);
            }
            if (DDCUDRDD.FRZ_STS_CHK == 'N') {
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
            if (DDCUDRDD.TSTS_TABL.equalsIgnoreCase(K_CUS_DR_STS_LN) 
                || DDCUDRDD.TSTS_TABL.equalsIgnoreCase(K_CUS_DR_STS_FEE)) {
                IBS.init(SCCGWA, DCCIQHLD);
                DCCIQHLD.INP_DATA.AC = DDRCCY.KEY.AC;
                S000_CALL_DCZIQHLD();
                if (pgmRtn) return;
                if (DCCIQHLD.OUT_DATA.LAW_AC == 'Y') {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_HAS_LAW_HLD;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (WS_CHK_AC_FLG == '1' 
                || WS_CHK_AC_FLG == '2') {
                B090_CHECK_CARD_HLDR_CINO_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B090_CHECK_CARD_HLDR_CINO_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFCSTS);
        CEP.TRC(SCCGWA, CICKCUST.O_DATA.O_CI_TYP);
        CEP.TRC(SCCGWA, DDRFMST.CI_TYP);
        if (DDCUDRDD.CARD_NO.trim().length() > 0) {
            BPCFCSTS.MSG_INFO = DDCUDRDD.CARD_NO;
            WS_FROM_CITYP = CICKCUST.O_DATA.O_CI_TYP;
        } else {
            BPCFCSTS.MSG_INFO = DDCUDRDD.AC;
            WS_FROM_CITYP = DDRFMST.CI_TYP;
        }
        BPCFCSTS.AP_MMO = K_STS_TABLE_APP;
        if (DDCUDRDD.TSTS_TABL.trim().length() > 0) {
            BPCFCSTS.TBL_NO = DDCUDRDD.TSTS_TABL;
        } else {
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                CEP.TRC(SCCGWA, WS_FROM_CITYP);
                if (CICCUST.O_DATA.O_CI_TYP == '1') {
                    BPCFCSTS.TBL_NO = K_CUS_DR_STS_TBL_P;
                } else {
                    BPCFCSTS.TBL_NO = K_CUS_DR_STS_TBL_C;
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
    public void B060_GET_PRD_INF_PROC() throws IOException,SQLException,Exception {
        if (DDCUDRDD.CHK_DRAC_FLG != 'Y') {
            IBS.init(SCCGWA, DDCIQPRD);
            DDCIQPRD.INPUT_DATA.PROD_CODE = CICQACAC.O_DATA.O_ACAC_DATA.O_PROD_CD_ACAC;
            DDCIQPRD.INPUT_DATA.CCY = DDCUDRDD.CCY;
            DDCIQPRD.DDVMPRD_PTR = DDVMPRD;
            DDCIQPRD.DDVMRAT_PTR = DDVMRAT;
            S000_CALL_DDZIQPRD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDCIQPRD.OUTPUT_DATA.PRDT_MODEL);
            CEP.TRC(SCCGWA, DDCIQPRD.OUTPUT_DATA.AC_TYPE);
            DPCPARMP.AC_TYPE = DDCIQPRD.OUTPUT_DATA.AC_TYPE;
            CEP.TRC(SCCGWA, DDCIQPRD.OUTPUT_DATA.PRDT_MODEL);
            DDCUDRDD.PRDT_MODEL = DDCIQPRD.OUTPUT_DATA.PRDT_MODEL;
        }
    }
    public void B075_CARD_LIMT_BAL_CHK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCPFTCK);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_TYP);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR);
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_AC_REL);
        if (CICCUST.O_DATA.O_CI_TYP == '1' 
            && CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DC")) {
            CEP.TRC(SCCGWA, WS_CARD_LNK_TYP);
            if (WS_CARD_LNK_TYP == '2') {
                DCCPFTCK.VAL.CARD_NO = DDCUDRDD.CARD_NO;
            } else {
                DCCPFTCK.VAL.CARD_NO = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
            }
        }
        if (CICCUST.O_DATA.O_CI_TYP == '2' 
            && CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD") 
            && CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("04")) {
            DCCPFTCK.VAL.CARD_NO = DDCUDRDD.CARD_NO;
        }
        CEP.TRC(SCCGWA, DCCPFTCK.VAL.CARD_NO);
        if (DCCPFTCK.VAL.CARD_NO.trim().length() > 0) {
            DCCPFTCK.VAL.REGN_TYP = '0';
            CEP.TRC(SCCGWA, DDCUDRDD.FEE_FLG);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
            if ((DDCUDRDD.FEE_FLG == 'Y' 
                && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) 
                || DDCUDRDD.CHK_LMT_FLG == '4') {
                DCCPFTCK.FUNCTION_CODE = 'S';
            }
            DCCPFTCK.TO_AC_NO = DDCUDRDD.OTHER_AC;
            if (DDCUDRDD.RLT_AC.trim().length() > 0) {
                DCCPFTCK.TO_AC_NO = DDCUDRDD.RLT_AC;
            }
            DCCPFTCK.VAL.TXN_CCY = DDCUDRDD.CCY;
            DCCPFTCK.VAL.TXN_AMT = DDCUDRDD.TX_AMT;
            DCCPFTCK.TRK2_DAT = DDCUDRDD.TRK_DATE2;
            DCCPFTCK.TRK3_DAT = DDCUDRDD.TRK_DATE3;
            if (DDCUDRDD.TXN_REGION != ' ') {
                DCCPFTCK.VAL.REGN_TYP = DDCUDRDD.TXN_REGION;
            }
            if (DDCUDRDD.TXN_TYPE.trim().length() > 0) {
                DCCPFTCK.VAL.TXN_TYPE = DDCUDRDD.TXN_TYPE;
            } else {
                if (DDCUDRDD.TX_TYPE == 'T') {
                    DCCPFTCK.VAL.TXN_TYPE = "04";
                } else {
                    DCCPFTCK.VAL.TXN_TYPE = "01";
                }
            }
            CEP.TRC(SCCGWA, DCCPFTCK.VAL.TXN_TYPE);
            DCCPFTCK.TXN_CHNL = DDCUDRDD.TXN_CHNL;
            DCCPFTCK.BANK_FLG = DDCUDRDD.IN_OUT_FLG;
            DCCPFTCK.CANCEL_FLG = DDCUDRDD.CANCEL_FLG;
            DCCPFTCK.VAL.SNAME_TRF_FLG = DDCUDRDD.SNAME_FLG;
            DCCPFTCK.VAL.DNAME_TRF_FLG = DDCUDRDD.DNAME_FLG;
            DCCPFTCK.TXN_MMO = DDCUDRDD.TX_MMO;
            CEP.TRC(SCCGWA, DCCPFTCK.TXN_MMO);
            S000_CALL_DCZPFTCK();
            if (pgmRtn) return;
        }
    }
    public void B076_CHECK_LMT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICREGLC);
        CICREGLC.FUNC = '0';
        if (DDCUDRDD.CARD_NO.trim().length() > 0) {
            CICREGLC.CUS_AC = DDCUDRDD.CARD_NO;
        } else {
            CICREGLC.CUS_AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        }
        CEP.TRC(SCCGWA, CICREGLC.CUS_AC);
        CICREGLC.CARD_LNK_TYP = DCCUCINF.CARD_LNK_TYP;
        CICREGLC.ACO_AC = DDRCCY.KEY.AC;
        CICREGLC.DC_FLG = 'D';
        CICREGLC.CCY = DDRCCY.CCY;
        CICREGLC.CCY_TYP = DDRCCY.CCY_TYPE;
        CICREGLC.AMT = DDCUDRDD.TX_AMT;
        CICREGLC.BAL = DDRCCY.CURR_BAL;
        if (DDCUDRDD.TX_TYPE == 'T') {
            CICREGLC.TRS_FLG = '1';
        }
        if (DDCUDRDD.TXN_TYPE.equalsIgnoreCase("01") 
            || DDCUDRDD.TXN_TYPE.equalsIgnoreCase("02")) {
            CICREGLC.TRS_FLG = '0';
        }
        CEP.TRC(SCCGWA, DDCUDRDD.TXN_TYPE);
        if (DDCUDRDD.TX_TYPE == 'C') {
            CICREGLC.TRS_FLG = '0';
        }
        CICREGLC.OPP_CUS_AC = DDCUDRDD.OTHER_AC;
        CICREGLC.RLT_CUS_AC = DDCUDRDD.RLT_AC;
        CICREGLC.CI_NO = CICCUST.O_DATA.O_CI_NO;
        CICREGLC.CI_TYP = CICCUST.O_DATA.O_CI_TYP;
        CICREGLC.OPP_CI_NO = CICOCUST.O_DATA.O_CI_NO;
        CICREGLC.PROD_CD = WS_PROD_CD;
        CICREGLC.CITY_FLG = DDCUDRDD.TXN_REGION;
        if (CICREGLC.CITY_FLG == ' ') {
            CICREGLC.CITY_FLG = '0';
        }
        CICREGLC.AC_TYP = DCCUCINF.AC_TYP;
        CICREGLC.AC_BR = DDRCCY.OWNER_BRDP;
        if (CICCUST.O_DATA.O_STSW == null) CICCUST.O_DATA.O_STSW = "";
        JIBS_tmp_int = CICCUST.O_DATA.O_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICCUST.O_DATA.O_STSW += " ";
        CICREGLC.HS_FLG = CICCUST.O_DATA.O_STSW.substring(27 - 1, 27 + 1 - 1).charAt(0);
        CICREGLC.TX_TYP = DDCUDRDD.TXN_TYPE;
        CICREGLC.MMO = DDCUDRDD.TX_MMO;
        CICREGLC.ACO_PROD_CD = WS_ACO_PROD_CD;
        CICREGLC.OTH_BK = DDCUDRDD.RLT_BANK;
    }
    public void B079_REGIN_LIMT_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICPDTL);
        CICPDTL.FUNC = '5';
        CICPDTL.LC_OBJ = DDCUDRDD.CARD_NO;
        CICPDTL.TX_TYPE = "02";
        CICPDTL.TYPE = "16";
        WS_AMT = DDCUDRDD.TX_AMT;
        CICPDTL.LVL_TYP = "00000000000000000000000000000000000000000000000000";
        CICPDTL.LVL = 33;
        CICPDTL.LC_CCY = "156";
        CICPDTL.DAY_STA = SCCGWA.COMM_AREA.AC_DATE;
        CICPDTL.DAY_END = 20991231;
        CICPDTL.TXN_AMT = 99999999999999.99;
        CICPDTL.DLY_AMT = 99999999999999.99;
        CICPDTL.DLY_VOL = 99999;
        CICPDTL.MLY_AMT = 99999999999999.99;
        CICPDTL.MLY_VOL = 99999;
        CICPDTL.SYY_AMT = 99999999999999.99;
        CICPDTL.YLY_AMT = 99999999999999.99;
        CICPDTL.TM_AMT = 99999999999999.99;
        CICPDTL.SE_AMT = 99999999999999.99;
        CICPDTL.LMT_AMT1 = WS_AMT;
        CICPDTL.LMT_AMT2 = 99999999999999.99;
        CICPDTL.LMT_AMT3 = 99999999999999.99;
        CICPDTL.LMT_AMT4 = 99999999999999.99;
        CICPDTL.BAL_AMT = 99999999999999.99;
        CICPDTL.TIMES50[1-1].CON_TYP1 = 4;
        CICPDTL.TIMES50[1-1].VAL = "2";
        CICPDTL.TIMES50[2-1].CON_TYP1 = 4;
        CICPDTL.TIMES50[2-1].VAL = "3";
        CICPDTL.TIMES50[3-1].CON_TYP1 = 19;
        CICPDTL.TIMES50[3-1].VAL = DDCUDRDD.RLT_BANK;
        CICPDTL.TIMES50[4-1].CON_TYP1 = 23;
        CICPDTL.TIMES50[4-1].VAL = "A504";
        CICPDTL.LMT_STSW = "EEEEEEEEEEEEEEE";
        S000_CALL_CIZPDTL();
        if (pgmRtn) return;
    }
    public void B077_REG_FRGLMT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICMLRG);
        CICMLRG.DATA.CI_NO = CICCUST.O_DATA.O_CI_NO;
        CICMLRG.DATA.AGR_NO = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        CICMLRG.DATA.ACAC_NO = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            CICMLRG.DATA.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
            CICMLRG.DATA.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
            CICMLRG.DATA.TXN_TYP = '4';
        } else {
            CICMLRG.DATA.AC_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            CICMLRG.DATA.JRN_NO = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
            CICMLRG.DATA.TXN_TYP = '5';
        }
        CICMLRG.DATA.SVS_ADC_NO = WS_SVS_ADC_NO;
        CICMLRG.DATA.TXN_CCY = DDCUDRDD.CCY;
        CICMLRG.DATA.TXN_AMT = DDCUDRDD.TX_AMT;
        CICMLRG.DATA.DC_FLG = 'D';
        S000_CALL_CIZMLRG();
        if (pgmRtn) return;
    }
    public void B078_ADP_NOTICE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRADMN);
        DDRADMN.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
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
        CEP.TRC(SCCGWA, DDRADMN.KEY.ADP_NO);
        IBS.init(SCCGWA, SCCTPCL);
        SCCTPCL.SERV_AREA.OBJ_SYSTEM = K_OBJ_SYSTEM4;
        SCCTPCL.SERV_AREA.SERV_CODE = K_SERV_CODE5;
        SCCTPCL.SERV_AREA.SERV_TYPE = ' ';
        WS_DATA_TO_SCZTPCL.WS_ADP_NO = DDRADMN.KEY.ADP_NO;
        WS_DATA_TO_SCZTPCL.WS_CI_NO = CICCUST.O_DATA.O_CI_NO;
        WS_DATA_TO_SCZTPCL.WS_AGR_NO = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        WS_DATA_TO_SCZTPCL.WS_CCY = DDCUDRDD.CCY;
        WS_DATA_TO_SCZTPCL.WS_CCY_TYPE = DDCUDRDD.CCY_TYPE;
        if (WS_N_CURR_BAL < 0) {
            WS_DATA_TO_SCZTPCL.WS_ADP_BAL = DDRADMN.OD_AMT + WS_N_CURR_BAL;
        } else {
            WS_DATA_TO_SCZTPCL.WS_ADP_BAL = DDRADMN.OD_AMT;
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            WS_DATA_TO_SCZTPCL.WS_NOTI_TYP = "010";
            if (WS_O_CURR_BAL > 0) {
                WS_DATA_TO_SCZTPCL.WS_TXN_BAL = DDCUDRDD.TX_AMT - WS_O_CURR_BAL;
            } else {
                WS_DATA_TO_SCZTPCL.WS_TXN_BAL = DDCUDRDD.TX_AMT;
            }
        } else {
            WS_DATA_TO_SCZTPCL.WS_NOTI_TYP = "020";
            if (WS_N_CURR_BAL > 0) {
                WS_DATA_TO_SCZTPCL.WS_TXN_BAL = - WS_O_CURR_BAL;
            } else {
                WS_DATA_TO_SCZTPCL.WS_TXN_BAL = DDCUDRDD.TX_AMT;
            }
        }
        CEP.TRC(SCCGWA, WS_DATA_TO_SCZTPCL);
        SCCTPCL.INP_AREA.SERV_DATA_LEN = 362;
        SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, WS_DATA_TO_SCZTPCL);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_CSIF_NOTICE);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_BVMS_INQ);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_BH_FILE_SEND);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_BH_FILE_ECIF);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_AC_CHG_INF);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ACGL_GL0002);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_PASSWORD_CHK);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_SMS_INFO);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ECIF_SG0001);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_CHG_NOTICE);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ACGL_GL0001);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ECIF_AC0001);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.SN_SMS_NOTICE);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ECIF_AC0002);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.CC_CHANGE_CARD);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.SC_SCF_NOTICE);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ECIF_AC0003);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_IBIL_NOTICE);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_BROADCAST);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_CASH_CHG);
        if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
            S000_CALL_SCZTPCL();
            if (pgmRtn) return;
        }
    }
    public void B100_CHECK_TXN_AMT_LIMIT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, DDCPLIMT);
        BPCPRMR.DAT_PTR = BPRPRMT;
        DDCPLIMT.KEY.TYP = "PDD08";
        if (DDRMST.CI_TYP == '1' 
                && DDCUDRDD.TX_TYPE == 'C') {
            DDCPLIMT.KEY.CD = "005";
            B055_10_GET_TXN_AMT_LIMIT();
            if (pgmRtn) return;
        } else if ((DDRMST.CI_TYP == '1' 
                && DDCUDRDD.TX_TYPE == 'T' 
                && (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")))) {
            DDCPLIMT.KEY.CD = "006";
            B055_10_GET_TXN_AMT_LIMIT();
            if (pgmRtn) return;
        } else if (DDRMST.CI_TYP == '2' 
                && DDCUDRDD.TX_TYPE == 'C') {
            DDCPLIMT.KEY.CD = "101";
            B055_10_GET_TXN_AMT_LIMIT();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "123444");
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        CEP.TRC(SCCGWA, DDRMST.AC_STS_WORD.substring(64 - 1, 64 + 1 - 1));
        if (DDROMST.AC_STS_WORD == null) DDROMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDROMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDROMST.AC_STS_WORD += " ";
        CEP.TRC(SCCGWA, DDROMST.AC_STS_WORD.substring(64 - 1, 64 + 1 - 1));
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(64 - 1, 64 + 1 - 1).equalsIgnoreCase("1")) {
            R000_CHECK_BFJ_LMT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDCUDRDD.RLT_AC);
            CEP.TRC(SCCGWA, DDCUDRDD.OTHER_AC);
            if (DDCUDRDD.RLT_AC.trim().length() > 0) {
                WS_OTHER_AC = DDCUDRDD.RLT_AC;
            } else {
                if (DDCUDRDD.OTHER_AC.trim().length() > 0) {
                    WS_OTHER_AC = DDCUDRDD.OTHER_AC;
                }
            }
            CEP.TRC(SCCGWA, WS_OTHER_AC);
            if (WS_OTHER_AC.trim().length() > 0) {
                R000_CHECK_OTHAC_LMT();
                if (pgmRtn) return;
            }
            if (DDROMST.AC_STS_WORD == null) DDROMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDROMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDROMST.AC_STS_WORD += " ";
            if (WS_OTHER_AC.trim().length() == 0 
                || (WS_CTL_TYPE == '3' 
                && (!CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO.equalsIgnoreCase(CICQACRI.O_DATA.O_CI_NO) 
                || (CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO.equalsIgnoreCase(CICQACRI.O_DATA.O_CI_NO) 
                && !DDROMST.AC_STS_WORD.substring(64 - 1, 64 + 1 - 1).equalsIgnoreCase("1") 
                && !CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("TD"))))) {
                R000_CHECK_NON_WHITE_LMT();
                if (pgmRtn) return;
            }
        }
        if (!DDCUDRDD.CCY.equalsIgnoreCase("156") 
            && CICCUST.O_DATA.O_CI_TYP == '1' 
            && DDCUDRDD.TX_TYPE == 'C' 
            && DDCUDRDD.TX_AMT != 0) {
            IBS.init(SCCGWA, CICMLMT);
            CICMLMT.FUNC = 'A';
            CICMLMT.DATA.CI_NO = CICCUST.O_DATA.O_CI_NO;
            CICMLMT.DATA.LMT_TP = "02";
            CICMLMT.DATA.CMT = DDCUDRDD.TX_AMT;
            CICMLMT.DATA.CCY = DDCUDRDD.CCY;
            S000_CALL_CIZMLMT();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_BFJ_LMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRRSAC);
        DDRRSAC.CI_NO = CICCUST.O_DATA.O_CI_NO;
        DDRRSAC.KEY.TYPE = '0';
        DDRRSAC.KEY.CTL_TYPE = '1';
        T000_READ_UPD_DDTRSAC();
        if (pgmRtn) return;
        WS_BASE_DATE = DDRRSAC.BASE_DATE;
        WS_AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBS.init(SCCGWA, CICSPVS);
        CICSPVS.DATA.CI_NO = CICCUST.O_DATA.O_CI_NO;
        S000_CALL_CIZSPVS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICSPVS.OUT_DATA.TOTAL_BAL);
        CEP.TRC(SCCGWA, DDRRSAC.BASE_AMT);
        if (( CICSPVS.OUT_DATA.TOTAL_BAL - DDCUDRDD.TX_AMT ) < DDRRSAC.BASE_AMT) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LACK_BFJLMT);
        }
    }
    public void R000_CHECK_NON_WHITE_LMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRRSAC);
        DDRRSAC.CI_NO = CICCUST.O_DATA.O_CI_NO;
        DDRRSAC.KEY.TYPE = '0';
        DDRRSAC.KEY.CTL_TYPE = '1';
        T000_READ_UPD_DDTRSAC();
        if (pgmRtn) return;
        WS_BASE_DATE = DDRRSAC.BASE_DATE;
        WS_AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, DDRRSAC.SINGAL_LMT);
        if (DDCUDRDD.TX_AMT > DDRRSAC.SINGAL_LMT) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LMT);
        }
        CEP.TRC(SCCGWA, DDRRSAC.DAY_AMT_LMT);
        if (DDCUDRDD.TX_AMT > DDRRSAC.DAY_AMT_LMT) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_DAY_LMT);
        }
        CEP.TRC(SCCGWA, DDRRSAC.MON_AMT_LMT);
        if (DDCUDRDD.TX_AMT > DDRRSAC.MON_AMT_LMT) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_MON_LMT);
        }
        CEP.TRC(SCCGWA, WS_BASE_DATE);
        CEP.TRC(SCCGWA, WS_AC_DATE);
        CEP.TRC(SCCGWA, DDRRSAC.DAY_TOT_AMT);
        CEP.TRC(SCCGWA, DDRRSAC.DAY_TOT_CNT);
        if (WS_BASE_DATE != WS_AC_DATE) {
            CEP.TRC(SCCGWA, "NOT SAME DAY");
            DDRRSAC.DAY_TOT_AMT = DDCUDRDD.TX_AMT;
            DDRRSAC.DAY_TOT_CNT = 1;
        } else {
            CEP.TRC(SCCGWA, "SAME DAY");
            DDRRSAC.DAY_TOT_AMT += DDCUDRDD.TX_AMT;
            DDRRSAC.DAY_TOT_CNT += 1;
        }
        CEP.TRC(SCCGWA, DDRRSAC.DAY_TOT_AMT);
        CEP.TRC(SCCGWA, DDRRSAC.DAY_TOT_CNT);
        if (DDRRSAC.DAY_TOT_AMT > DDRRSAC.DAY_AMT_LMT) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_DAY_LMT);
        }
        if (DDRRSAC.DAY_TOT_CNT > DDRRSAC.DAY_CNT_LMT) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_DAY_CNTLMT);
        }
        CEP.TRC(SCCGWA, DDRRSAC.MON_TOT_AMT);
        CEP.TRC(SCCGWA, DDRRSAC.MON_TOT_CNT);
        JIBS_tmp_str[0] = "" + WS_BASE_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        JIBS_tmp_str[1] = "" + WS_AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[1].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
        if (!JIBS_tmp_str[0].substring(0, 6).equalsIgnoreCase(JIBS_tmp_str[1].substring(0, 6))) {
            CEP.TRC(SCCGWA, "NOT SAME MONTH");
            DDRRSAC.MON_TOT_AMT = DDCUDRDD.TX_AMT;
            DDRRSAC.MON_TOT_CNT = 1;
        } else {
            CEP.TRC(SCCGWA, "SAME MONTH");
            DDRRSAC.MON_TOT_AMT += DDCUDRDD.TX_AMT;
            DDRRSAC.MON_TOT_CNT += 1;
        }
        CEP.TRC(SCCGWA, DDRRSAC.MON_TOT_AMT);
        CEP.TRC(SCCGWA, DDRRSAC.MON_TOT_CNT);
        if (DDRRSAC.MON_TOT_AMT > DDRRSAC.MON_AMT_LMT) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_MON_LMT);
        }
        if (DDRRSAC.MON_TOT_CNT > DDRRSAC.MON_CNT_LMT) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_MON_CNTLMT);
        }
        DDRRSAC.BASE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRRSAC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRRSAC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DDTRSAC();
        if (pgmRtn) return;
    }
    public void R000_CHECK_OTHAC_LMT() throws IOException,SQLException,Exception {
        WS_BLACK_FLG = 'N';
        IBS.init(SCCGWA, DDRRSAC);
        DDRRSAC.KEY.TYPE = '2';
        DDRRSAC.KEY.CTL_TYPE = '3';
        DDRRSAC.KEY.OTH_AC = WS_OTHER_AC;
        T000_READ_UPD_DDTRSAC_1();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_BLACK_FLG = 'Y';
            CEP.TRC(SCCGWA, "HEI MING DAN");
            CEP.TRC(SCCGWA, DDCUDRDD.TX_AMT);
            CEP.TRC(SCCGWA, DDRRSAC.DAY_AMT_LMT);
            CEP.TRC(SCCGWA, DDRRSAC.DAY_TOT_AMT);
            if (DDCUDRDD.TX_AMT != 0 
                && DDRRSAC.DAY_AMT_LMT == 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_OVER_HMDLMT);
            }
            if (DDRRSAC.BASE_DATE != SCCGWA.COMM_AREA.AC_DATE) {
                CEP.TRC(SCCGWA, "NOT SAME DAY-HEI");
                if (DDCUDRDD.TX_AMT > DDRRSAC.DAY_AMT_LMT) {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_OVER_HMDLMT);
                }
                DDRRSAC.DAY_TOT_AMT = DDCUDRDD.TX_AMT;
            } else {
                CEP.TRC(SCCGWA, "SAME DAY-HEI");
                if (DDRRSAC.DAY_AMT_LMT != 0 
                    && (DDCUDRDD.TX_AMT > DDRRSAC.DAY_AMT_LMT 
                    || ( DDCUDRDD.TX_AMT + DDRRSAC.DAY_TOT_AMT ) > DDRRSAC.DAY_AMT_LMT)) {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_OVER_HMDLMT);
                }
                DDRRSAC.DAY_TOT_AMT += DDCUDRDD.TX_AMT;
            }
            DDRRSAC.BASE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRRSAC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRRSAC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DDTRSAC();
            if (pgmRtn) return;
        }
        WS_WHITE_FLG = 'N';
        IBS.init(SCCGWA, DDRRSAC);
        DDRRSAC.KEY.TYPE = '2';
        DDRRSAC.KEY.CTL_TYPE = '2';
        DDRRSAC.KEY.OTH_AC = WS_OTHER_AC;
        T000_READ_UPD_DDTRSAC_1();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_WHITE_FLG = 'Y';
            CEP.TRC(SCCGWA, DDCUDRDD.TX_AMT);
            CEP.TRC(SCCGWA, DDRRSAC.SINGAL_LMT);
            CEP.TRC(SCCGWA, DDRRSAC.DAY_AMT_LMT);
            CEP.TRC(SCCGWA, DDRRSAC.DAY_TOT_AMT);
            if (DDRRSAC.SINGAL_LMT != 0 
                && DDCUDRDD.TX_AMT > DDRRSAC.SINGAL_LMT) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_OVER_BMDLMT);
            }
            if (DDRRSAC.BASE_DATE != SCCGWA.COMM_AREA.AC_DATE) {
                CEP.TRC(SCCGWA, "NOT SAME DAY-BAI");
                if (DDRRSAC.DAY_AMT_LMT != 0 
                    && DDCUDRDD.TX_AMT > DDRRSAC.DAY_AMT_LMT) {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_BAI_DAYLMT);
                }
                DDRRSAC.DAY_TOT_AMT = DDCUDRDD.TX_AMT;
            } else {
                CEP.TRC(SCCGWA, "SAME DAY-BAI");
                if (DDRRSAC.DAY_AMT_LMT != 0 
                    && (DDCUDRDD.TX_AMT > DDRRSAC.DAY_AMT_LMT 
                    || ( DDCUDRDD.TX_AMT + DDRRSAC.DAY_TOT_AMT ) > DDRRSAC.DAY_AMT_LMT)) {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_BAI_DAYLMT);
                }
                DDRRSAC.DAY_TOT_AMT += DDCUDRDD.TX_AMT;
            }
            DDRRSAC.BASE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRRSAC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRRSAC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DDTRSAC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_BLACK_FLG);
        CEP.TRC(SCCGWA, WS_WHITE_FLG);
        if (WS_WHITE_FLG == 'Y' 
                && WS_BLACK_FLG == 'N') {
            WS_CTL_TYPE = '1';
        } else if (WS_WHITE_FLG == 'N' 
                && WS_BLACK_FLG == 'Y') {
            WS_CTL_TYPE = '2';
        } else if (WS_WHITE_FLG == 'Y' 
                && WS_BLACK_FLG == 'Y') {
            WS_CTL_TYPE = '4';
        } else {
            WS_CTL_TYPE = '3';
        }
        CEP.TRC(SCCGWA, WS_CTL_TYPE);
    }
    public void B055_10_GET_TXN_AMT_LIMIT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCPLIMT.KEY.CD);
        CEP.TRC(SCCGWA, DDCPLIMT.KEY.TYP);
        BPRPRMT.KEY.TYP = DDCPLIMT.KEY.TYP;
        BPRPRMT.KEY.CD = DDCPLIMT.KEY.CD;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            CEP.TRC(SCCGWA, "NO LIMIT");
        } else {
            CEP.TRC(SCCGWA, "FOUND LIMIT");
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCPLIMT.DATA_TXT);
            CEP.TRC(SCCGWA, "DDCPLIMT:");
            CEP.TRC(SCCGWA, DDCPLIMT.DATA_TXT.LOCAL_CURRENCY_LMT);
            CEP.TRC(SCCGWA, DDCPLIMT.DATA_TXT.FOREIGN_CURRENCY_LMT);
            B055_30_CHK_TXN_AMT_LIMIT();
            if (pgmRtn) return;
        }
    }
    public void B055_30_CHK_TXN_AMT_LIMIT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUDRDD.CCY);
        CEP.TRC(SCCGWA, DDCUDRDD.CCY_TYPE);
        CEP.TRC(SCCGWA, DDRMST.AC_TYPE);
        CEP.TRC(SCCGWA, DDCUDRDD.TX_AMT);
        CEP.TRC(SCCGWA, DDCUDRDD.TX_TYPE);
        CEP.TRC(SCCGWA, DDCPLIMT.KEY.TYP);
        CEP.TRC(SCCGWA, DDCPLIMT.KEY.CD);
        CEP.TRC(SCCGWA, DDCPLIMT.DATA_TXT.LOCAL_CURRENCY_LMT);
        if (DDCPLIMT.KEY.CD.equalsIgnoreCase("005") 
            || DDCPLIMT.KEY.CD.equalsIgnoreCase("006") 
            || DDCPLIMT.KEY.CD.equalsIgnoreCase("101")) {
            CEP.TRC(SCCGWA, DDRCCY.DRW_CAMT);
            WS_LIMI_AMT = DDRCCY.DRW_CAMT + DDCUDRDD.TX_AMT;
            CEP.TRC(SCCGWA, WS_LIMI_AMT);
        }
        if (DDCPLIMT.KEY.CD.equalsIgnoreCase("002") 
            || DDCPLIMT.KEY.CD.equalsIgnoreCase("003")) {
            WS_LIMI_AMT = DDCUDRDD.TX_AMT;
            CEP.TRC(SCCGWA, WS_LIMI_AMT);
        }
        CEP.TRC(SCCGWA, WS_LIMI_AMT);
        CEP.TRC(SCCGWA, DDCPLIMT.DATA_TXT.LOCAL_CURRENCY_LMT);
        CEP.TRC(SCCGWA, DDCUDRDD.CCY);
        if (DDCUDRDD.CCY.equalsIgnoreCase("156")) {
            if (WS_LIMI_AMT >= DDCPLIMT.DATA_TXT.LOCAL_CURRENCY_LMT) {
                CEP.TRC(SCCGWA, "HELLO");
                if (DDCPLIMT.KEY.CD.equalsIgnoreCase("101")) {
                    CEP.TRC(SCCGWA, "CORP DAY CASH");
                    if (WS_ID_TYPE.equalsIgnoreCase("10100")) {
                    } else {
                        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BUSS_NEED_ID_VERIFY;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                } else if (DDCPLIMT.KEY.CD.equalsIgnoreCase("005")) {
                    CEP.TRC(SCCGWA, "PER TOTAL CASH WDR");
                    if (WS_ID_TYPE.equalsIgnoreCase("10100")) {
                    } else {
                        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BUSS_NEED_ID_VERIFY;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                } else if (DDCPLIMT.KEY.CD.equalsIgnoreCase("006")) {
                    CEP.TRC(SCCGWA, "PER TOTAL AC TRF");
                    if (WS_ID_TYPE.equalsIgnoreCase("10100")) {
                    } else {
                        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BUSS_NEED_ID_VERIFY;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                } else {
                }
                CEP.TRC(SCCGWA, "156");
                CEP.TRC(SCCGWA, DDCPLIMT.KEY.CD);
                CEP.TRC(SCCGWA, WS_LIMI_AMT);
            }
        }
        if (DDCUDRDD.CCY.equalsIgnoreCase("840")) {
            CEP.TRC(SCCGWA, DDCPLIMT.DATA_TXT.FOREIGN_CURRENCY_LMT);
            if (WS_LIMI_AMT >= DDCPLIMT.DATA_TXT.FOREIGN_CURRENCY_LMT) {
                if (DDCPLIMT.KEY.CD.equalsIgnoreCase("005")) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FOREIGN_WDR_LMT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                }
                CEP.TRC(SCCGWA, "840");
                CEP.TRC(SCCGWA, DDCPLIMT.KEY.CD);
                CEP.TRC(SCCGWA, DDCUDRDD.TX_AMT);
            }
        }
        if (!DDCUDRDD.CCY.equalsIgnoreCase("156") 
            && !DDCUDRDD.CCY.equalsIgnoreCase("840")) {
            WS_BUY_CCY = DDCUDRDD.CCY;
            WS_BUY_AMT = DDCUDRDD.TX_AMT;
            WS_BUY_AMT = WS_LIMI_AMT;
            WS_SELL_CCY = "840";
            R000_AMT_EX_PROC();
            if (pgmRtn) return;
            if (WS_SELL_AMT >= DDCPLIMT.DATA_TXT.FOREIGN_CURRENCY_LMT) {
                if (DDCPLIMT.KEY.CD.equalsIgnoreCase("005")) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FOREIGN_WDR_LMT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                }
                CEP.TRC(SCCGWA, "000");
                CEP.TRC(SCCGWA, DDCPLIMT.KEY.CD);
                CEP.TRC(SCCGWA, DDCUDRDD.TX_AMT);
                CEP.TRC(SCCGWA, WS_SELL_AMT);
            }
        }
    }
    public void B070_CHK_PSBK_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y' 
            && CICCUST.O_DATA.O_CI_TYP == '1') {
            CEP.TRC(SCCGWA, DDCUDRDD.BV_TYP);
            if (DDCUDRDD.BV_TYP == ' ') {
                if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DC")) {
                    DDCUDRDD.BV_TYP = '1';
                }
                if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
                    DDCUDRDD.BV_TYP = '2';
                }
            }
            CEP.TRC(SCCGWA, DDCUDRDD.BV_TYP);
            if (DDCUDRDD.BV_TYP == '2' 
                && DDCUDRDD.CHK_PSW_FLG != 'N') {
                IBS.init(SCCGWA, DDCIMPAY);
                CEP.TRC(SCCGWA, DDCUDRDD.PSWD);
                DDCIMPAY.AC = DDCUDRDD.AC;
                DDCIMPAY.FUNC = 'C';
                DDCIMPAY.PSWD_OLD = DDCUDRDD.PSWD;
                DDCIMPAY.PAY_MTH = DDCUDRDD.PAY_TYPE;
                DDCIMPAY.ID_TYPE = DDCUDRDD.ID_TYPE;
                DDCIMPAY.ID_NO = DDCUDRDD.ID_NO;
                DDCIMPAY.AC_CNAME = CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM;
                S000_CALL_DDZIMPAY();
                if (pgmRtn) return;
            } else {
                if (DDCUDRDD.BV_TYP == '1' 
                    && DDCUDRDD.CHK_PSW_FLG != 'N') {
                    IBS.init(SCCGWA, DCCPCDCK);
                    if (DDCUDRDD.CHK_PSW == ' ') {
                        if (DDCUDRDD.PAY_TYPE == '1') {
                            DDCUDRDD.CHK_PSW = 'P';
                        } else {
                            DDCUDRDD.CHK_PSW = 'N';
                        }
                    }
                    if (DDCUDRDD.CHK_PSW == 'P') {
                        DCCPCDCK.FUNC_CODE = 'P';
                    } else if (DDCUDRDD.CHK_PSW == 'T') {
                        DCCPCDCK.FUNC_CODE = 'T';
                    } else if (DDCUDRDD.CHK_PSW == 'B') {
                        DCCPCDCK.FUNC_CODE = 'B';
                    } else if (DDCUDRDD.CHK_PSW == 'N') {
                        DCCPCDCK.FUNC_CODE = 'N';
                    } else {
                    }
                    DCCPCDCK.CARD_NO = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
                    if (DDCUDRDD.CARD_NO.trim().length() > 0) {
                        DCCPCDCK.CARD_NO = DDCUDRDD.CARD_NO;
                    }
                    CEP.TRC(SCCGWA, DCCPCDCK.CARD_NO);
                    DCCPCDCK.CARD_PSW = DDCUDRDD.PSWD;
                    DCCPCDCK.TRK2_DAT = DDCUDRDD.TRK_DATE2;
                    DCCPCDCK.TRK3_DAT = DDCUDRDD.TRK_DATE3;
                    S000_CALL_DCZPCDCK();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B075_CHK_CARD_PSW_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y' 
            && CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("04") 
            && DDCUDRDD.CHK_PSW_FLG != 'N') {
            IBS.init(SCCGWA, DCCPCDCK);
            if (DDCUDRDD.CHK_PSW == 'P') {
                DCCPCDCK.FUNC_CODE = 'P';
            } else if (DDCUDRDD.CHK_PSW == 'T') {
                DCCPCDCK.FUNC_CODE = 'T';
            } else if (DDCUDRDD.CHK_PSW == 'B') {
                DCCPCDCK.FUNC_CODE = 'B';
            } else if (DDCUDRDD.CHK_PSW == 'N') {
                DCCPCDCK.FUNC_CODE = 'N';
            } else {
            }
            if (DDCUDRDD.CARD_NO.trim().length() > 0) {
                DCCPCDCK.CARD_NO = DDCUDRDD.CARD_NO;
            } else {
                DCCPCDCK.CARD_NO = DDCUDRDD.AC;
            }
            CEP.TRC(SCCGWA, DCCPCDCK.CARD_NO);
            DCCPCDCK.CARD_PSW = DDCUDRDD.PSWD;
            DCCPCDCK.TRK2_DAT = DDCUDRDD.TRK_DATE2;
            DCCPCDCK.TRK3_DAT = DDCUDRDD.TRK_DATE3;
            if (DCCPCDCK.CARD_NO.trim().length() > 0) {
                S000_CALL_DCZPCDCK();
                if (pgmRtn) return;
            }
        }
    }
    public void B070_GET_CCY_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READ_UPDATE_DDTCCY();
        if (pgmRtn) return;
        WS_O_CURR_BAL = DDRCCY.CURR_BAL;
        CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
        CEP.TRC(SCCGWA, WS_O_CURR_BAL);
        CEP.TRC(SCCGWA, DDRCCY.CUS_AC);
        CEP.TRC(SCCGWA, DDRCCY.AC_TYPE);
        if (DDCUDRDD.CHK_DRAC_FLG != 'Y') {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                if (DDCUDRDD.CLEAR_FLG == 'Y') {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_NOT_SUPP_INPUT_CCY;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if ((DDRCCY.AC_TYPE == 'B' 
                || DDRCCY.AC_TYPE == 'C' 
                || DDRCCY.AC_TYPE == '4' 
                || DDRCCY.AC_TYPE == '5') 
                && DDCUDRDD.TX_TYPE == 'C') {
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
            if (DDCUDRDD.BANK_DR_FLG == 'N' 
                && (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")) 
                && !JIBS_tmp_str[0].equalsIgnoreCase("111804") 
                && WS_CROS_DR_FLG == '2' 
                && !DDRMST.AC_STS_WORD.substring(21 - 1, 21 + 1 - 1).equalsIgnoreCase("1") 
                && DDRMST.OWNER_BR != BPCPQORG.BBR) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_NOT_DR_AT_ALL);
            }
            CEP.TRC(SCCGWA, DDCUDRDD.FEE_FLG);
            if ((DDRCCY.AC_TYPE == '4' 
                || DDRCCY.AC_TYPE == '5') 
                && DDCUDRDD.EA_CHK_FLG != 'N' 
                && DDCUDRDD.FEE_FLG != 'Y') {
                B070_GET_DZZH_PARM_PROC();
                if (pgmRtn) return;
                B070_DZZH_CTL_PROC();
                if (pgmRtn) return;
            }
            if (DDCUDRDD.CLEAR_FLG == 'Y') {
                if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                    if (DDRCCY.STS == 'C') {
                        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_CLEARED;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    if (DDRCCY.CURR_BAL < 0) {
                        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AVL_BAL_NOT_ENOUGH;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    } else {
                        DDCUDRDD.TX_AMT = DDRCCY.CURR_BAL;
                    }
                } else {
                }
            }
        }
        if (DDCUDRDD.CLEAR_FLG == 'Y') {
            WS_LAST_DAYEND_BAL = DDRCCY.LAST_DAYEND_BAL;
            WS_DAYEND_BAL = DDRCCY.CURR_BAL;
            if (WS_LAST_DAYEND_BAL <= 0 
                && WS_DAYEND_BAL >= 0) {
                WS_DIFF_BAL = 0 - WS_LAST_DAYEND_BAL;
            }
            if (WS_LAST_DAYEND_BAL <= 0 
                && WS_DAYEND_BAL <= 0 
                && WS_LAST_DAYEND_BAL < WS_DAYEND_BAL) {
                WS_DIFF_BAL = WS_DAYEND_BAL - WS_LAST_DAYEND_BAL;
            }
            if (WS_DIFF_BAL != 0) {
                WS_CHG_PROD_FLG = 'Y';
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
        if (DDCUDRDD.OTHER_AC.trim().length() > 0) {
            EACUTRAN.OPP_AC = DDCUDRDD.OTHER_AC;
            EACUTRAN.OPP_AC_NME = CICQACRI.O_DATA.O_AC_CNM;
        } else {
            EACUTRAN.OPP_AC = DDCUDRDD.RLT_AC;
            EACUTRAN.OPP_AC_NME = DDCUDRDD.RLT_AC_NAME;
        }
        CEP.TRC(SCCGWA, EACUTRAN.OPP_AC);
        CEP.TRC(SCCGWA, EACUTRAN.OPP_AC_NME);
        EACUTRAN.MMO = DDCUDRDD.TX_MMO;
        EACUTRAN.OPP_BNK = DDCUDRDD.OTHER_BR;
        EACUTRAN.OPP_BNK_NME = DDCUDRDD.OTHER_BK_NM;
        EACUTRAN.RMK = DDCUDRDD.REMARKS;
    }
    public void B080_CHECK_AVA_BAL_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
        CEP.TRC(SCCGWA, DDRCCY.HOLD_BAL);
        CEP.TRC(SCCGWA, DDRCCY.MARGIN_BAL);
        CEP.TRC(SCCGWA, DDRCCY.CCAL_TOT_BAL);
        WS_AVL_BAL = DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL + DDRCCY.CCAL_TOT_BAL - DDRCCY.MARGIN_BAL;
        CEP.TRC(SCCGWA, WS_AVL_BAL);
        CEP.TRC(SCCGWA, DDCUDRDD.TX_AMT);
        CEP.TRC(SCCGWA, DDCUDRDD.TX_BAL_FLG);
        CEP.TRC(SCCGWA, DDCUDRDD.CLEAR_FLG);
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y' 
            && DDCUDRDD.CLEAR_FLG == 'Y' 
            && WS_AVL_BAL != DDCUDRDD.TX_AMT) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AVL_BAL_NOT_ENOUGH);
        }
        CEP.TRC(SCCGWA, DDCUDRDD.CHK_DRAC_FLG);
        CEP.TRC(SCCGWA, WS_WITHD_AC_FLG);
        if (DDCUDRDD.TRT_CTLW == null) DDCUDRDD.TRT_CTLW = "";
        JIBS_tmp_int = DDCUDRDD.TRT_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) DDCUDRDD.TRT_CTLW += " ";
        CEP.TRC(SCCGWA, DDCUDRDD.TRT_CTLW.substring(3 - 1, 3 + 1 - 1));
        CEP.TRC(SCCGWA, WS_WITHD_TOP_AC_FLG);
        if (DDCUDRDD.TRT_CTLW == null) DDCUDRDD.TRT_CTLW = "";
        JIBS_tmp_int = DDCUDRDD.TRT_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) DDCUDRDD.TRT_CTLW += " ";
        if (((DDCUDRDD.CHK_DRAC_FLG != 'Y') 
            && (WS_WITHD_AC_FLG != 'Y')) 
            || (WS_WITHD_TOP_AC_FLG == 'Y' 
            && DDCUDRDD.TRT_CTLW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1"))) {
            CEP.TRC(SCCGWA, "111");
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDCUDRDD.TRT_CTLW == null) DDCUDRDD.TRT_CTLW = "";
            JIBS_tmp_int = DDCUDRDD.TRT_CTLW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) DDCUDRDD.TRT_CTLW += " ";
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y' 
                && DDRMST.AC_STS_WORD.substring(42 - 1, 42 + 1 - 1).equalsIgnoreCase("1") 
                && !DDCUDRDD.TRT_CTLW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
                && WS_AVL_BAL < DDCUDRDD.TX_AMT) {
                if (DDCUDRDD.TX_TYPE == 'C') {
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
                if (DDCUDRDD.TX_MMO == null) DDCUDRDD.TX_MMO = "";
                JIBS_tmp_int = DDCUDRDD.TX_MMO.length();
                for (int i=0;i<5-JIBS_tmp_int;i++) DDCUDRDD.TX_MMO += " ";
                BPCPRMR.CD = BPCPRMR.CD.substring(0, 6 - 1) + DDCUDRDD.TX_MMO + BPCPRMR.CD.substring(6 + 9 - 1);
                BPCPRMR.DAT_PTR = BPCPARMC;
                S000_CALL_BPZPRMR();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCPARMC.DATA_TXT.RBASE_TYP.RBASE_TYP2);
                IBS.init(SCCGWA, DDRADMN);
                DDRADMN.KEY.AC = DDCUDRDD.ACAC;
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
                    WS_AVL_BAL = WS_AVL_BAL + DDRADMN.OD_AMT;
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
                && WS_AVL_BAL < DDCUDRDD.TX_AMT) {
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
                CEP.TRC(SCCGWA, DDCUDRDD.TX_BAL_FLG);
                if (WS_AVL_BAL == 0 
                    && DDCUDRDD.TX_BAL_FLG == 'Y') {
                    DDCUDRDD.TX_AMT = 0;
                    Z_RET();
                    if (pgmRtn) return;
                }
                if (WS_AVL_BAL < DDCUDRDD.TX_AMT 
                    && DDCUDRDD.CLEAR_FLG != 'Y' 
                    && DDCUDRDD.TX_BAL_FLG != 'Y') {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AVL_BAL_NOT_ENOUGH;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, DDCUDRDD.TX_AMT);
                CEP.TRC(SCCGWA, WS_AVL_BAL);
                if (WS_AVL_BAL < DDCUDRDD.TX_AMT 
                    && DDCUDRDD.TX_BAL_FLG == 'Y') {
                    DDCUDRDD.TX_AMT = WS_AVL_BAL;
                    CEP.TRC(SCCGWA, DDCUDRDD.TX_AMT);
                }
                if (WS_AVL_BAL != DDCUDRDD.TX_AMT 
                    && DDCUDRDD.CLEAR_FLG == 'Y') {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AVL_BAL_NOT_ENOUGH;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B090_UPD_BAL_PROC() throws IOException,SQLException,Exception {
        if (DDRCCY.STS == 'C') {
            DDRCCY.STS = 'N';
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 11 - 1) + "0" + DDRCCY.STS_WORD.substring(11 + 1 - 1);
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        JIBS_tmp_str[3] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (DDCUDRDD.CLEAR_FLG == 'Y' 
            && SCCGWA.COMM_AREA.CANCEL_IND != 'Y' 
            && !JIBS_tmp_str[1].equalsIgnoreCase("181810") 
            && !JIBS_tmp_str[3].equalsIgnoreCase("181200")) {
            DDRCCY.STS = 'C';
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 11 - 1) + "1" + DDRCCY.STS_WORD.substring(11 + 1 - 1);
        }
        if (SCCGWA.COMM_AREA.AC_DATE != DDRCCY.LAST_BAL_DATE) {
            CEP.TRC(SCCGWA, "MOVE2");
            if (SCCGWA.COMM_AREA.AC_DATE > DDRCCY.LAST_BAL_DATE) {
                DDRCCY.LAST_BAL = DDRCCY.CURR_BAL;
                DDRCCY.LAST_BAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            } else {
                if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                    DDRCCY.LAST_BAL += DDCUDRDD.TX_AMT;
                } else {
                    DDRCCY.LAST_BAL -= DDCUDRDD.TX_AMT;
                }
            }
        }
        if (DDRCCY.LAST_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            CEP.TRC(SCCGWA, "MOVE1");
            DDRCCY.LAST_DRW_CAMT = DDRCCY.DRW_CAMT;
            DDRCCY.LAST_DEP_CAMT = DDRCCY.DEP_CAMT;
            DDRCCY.LAST_DRW_TAMT = DDRCCY.DRW_TAMT;
            DDRCCY.LAST_DEP_TAMT = DDRCCY.DEP_TAMT;
            DDRCCY.LASDAY_TOT_DR_AMT = DDRCCY.DAY_TOT_DR_AMT;
            DDRCCY.LASDAY_TOT_CR_AMT = DDRCCY.DAY_TOT_CR_AMT;
            DDRCCY.DRW_CAMT = 0;
            DDRCCY.DEP_CAMT = 0;
            DDRCCY.DRW_TAMT = 0;
            DDRCCY.DEP_TAMT = 0;
            DDRCCY.DAY_TOT_DR_AMT = 0;
            DDRCCY.DAY_TOT_CR_AMT = 0;
            CEP.TRC(SCCGWA, DDRCCY.LAST_DRW_CAMT);
            CEP.TRC(SCCGWA, DDRCCY.DRW_CAMT);
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            DDRCCY.CURR_BAL = DDCUDRDD.TX_AMT + DDRCCY.CURR_BAL;
            if (WS_DATA_OVERFLOW_FLAG == 'Y') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_DATA_OVERFLOW);
            }
            if (DDCUDRDD.TX_TYPE == 'C') {
                DDRCCY.DRW_CAMT -= DDCUDRDD.TX_AMT;
            } else {
                if (DDCUDRDD.TX_TYPE == 'T') {
                    DDRCCY.DRW_TAMT -= DDCUDRDD.TX_AMT;
                }
            }
            if (SCCGWA.COMM_AREA.AC_DATE == DDRCCY.LAST_BAL_DATE) {
                DDRCCY.DAY_TOT_DR_AMT -= DDCUDRDD.TX_AMT;
            }
        } else {
            DDRCCY.CURR_BAL -= DDCUDRDD.TX_AMT;
            if (DDCUDRDD.TX_TYPE == 'C') {
                DDRCCY.DRW_CAMT += DDCUDRDD.TX_AMT;
                WS_TOT_CAMT = DDRCCY.DRW_CAMT;
            } else {
                if (DDCUDRDD.TX_TYPE == 'T') {
                    DDRCCY.DRW_TAMT += DDCUDRDD.TX_AMT;
                    WS_TOT_TAMT = DDRCCY.DRW_TAMT;
                }
            }
            CEP.TRC(SCCGWA, DDRCCY.DRW_CAMT);
            CEP.TRC(SCCGWA, DDRCCY.DRW_TAMT);
            DDRCCY.DAY_TOT_DR_AMT += DDCUDRDD.TX_AMT;
        }
        CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
        CEP.TRC(SCCGWA, DDCUDRDD.TX_AMT);
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(41 - 1, 41 + 1 - 1).equalsIgnoreCase("1")) {
            if (SCCGWA.COMM_AREA.AC_DATE < DDRCCY.LAST_DATE 
                && DDRCCY.CURR_BAL < DDRCCY.LST_DAY_YEAR_BAL) {
                DDRCCY.LST_DAY_YEAR_BAL = DDRCCY.LAST_BAL;
            }
            if (SCCGWA.COMM_AREA.AC_DATE > DDRCCY.LAST_DATE 
                && DDRCCY.CURR_BAL < DDRCCY.LAST_YEAR_BAL) {
                DDRCCY.LST_DAY_YEAR_BAL = DDRCCY.LAST_YEAR_BAL;
                DDRCCY.LAST_YEAR_BAL = DDRCCY.CURR_BAL;
            }
            if (SCCGWA.COMM_AREA.AC_DATE == DDRCCY.LAST_DATE 
                && DDRCCY.CURR_BAL < DDRCCY.LAST_YEAR_BAL) {
                DDRCCY.LAST_YEAR_BAL = DDRCCY.CURR_BAL;
            }
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (!DDRMST.AC_STS_WORD.substring(42 - 1, 42 + 1 - 1).equalsIgnoreCase("1") 
            && !DDRMST.AC_STS_WORD.substring(61 - 1, 61 + 1 - 1).equalsIgnoreCase("1") 
            && !DDRMST.AC_STS_WORD.substring(62 - 1, 62 + 1 - 1).equalsIgnoreCase("1") 
            && DDRCCY.CURR_BAL < 0) {
            if (WS_WITHD_AC_FLG == 'Y') {
                WS_WITHDR_FLG = 'Y';
            } else {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_ALREADY_OD);
            }
        }
        WS_CURR_BAL = DDRCCY.CURR_BAL;
        WS_N_CURR_BAL = DDRCCY.CURR_BAL;
        CEP.TRC(SCCGWA, WS_CURR_BAL);
        CEP.TRC(SCCGWA, DDRCCY.CCAL_TOT_BAL);
        WS_CURR_BAL = WS_CURR_BAL + DDRCCY.CCAL_TOT_BAL;
        CEP.TRC(SCCGWA, WS_CURR_BAL);
        CEP.TRC(SCCGWA, WS_TEMP_VAL);
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(42 - 1, 42 + 1 - 1).equalsIgnoreCase("1") 
            && (WS_N_CURR_BAL < 0 
            || WS_O_CURR_BAL < 0)) {
            WS_NOTICE_FLG = 'Y';
        }
    }
    public void B110_UPD_CCY_INF_PROC() throws IOException,SQLException,Exception {
        WS_LAST_FN_DATE = DDRMST.LAST_FN_DATE;
        CEP.TRC(SCCGWA, DDRMST.AC_STS_WORD);
        CEP.TRC(SCCGWA, "XXXXXXXXX");
        if (DDCUDRDD.FEE_FLG != 'Y') {
            DDRCCY.LAST_FN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        DDRCCY.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DDTCCY();
        if (pgmRtn) return;
    }
    public void B120_UPD_GRP_AC_CTL_BAL() throws IOException,SQLException,Exception {
        if (WS_GROUP_AC_ACTION == 'U') {
            CEP.TRC(SCCGWA, "GROUP NET ACCOUNT --> UPDATE CONTROL BALANCE!");
            IBS.init(SCCGWA, DDRGPMST);
            DDRGPMST.KEY.AC_NO = WS_GROUP_AC;
            DDRGPMST.KEY.CCY = "156";
            CEP.TRC(SCCGWA, DDRGPMST.KEY.AC_NO);
            T000_READ_UPDATE_DDTGPMST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDRGPMST.CTRL_BAL);
            CEP.TRC(SCCGWA, DDCUDRDD.TX_AMT);
            DDRGPMST.CTRL_BAL = DDRGPMST.CTRL_BAL + DDCUDRDD.TX_AMT;
            CEP.TRC(SCCGWA, DDRGPMST.CTRL_BAL);
            DDRGPMST.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRGPMST.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DDTGPMST();
            if (pgmRtn) return;
        }
    }
    public void B130_UPD_MST_INF_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRMST.AC_STS);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (DDRMST.AC_STS == 'W' 
            && !JIBS_tmp_str[1].equalsIgnoreCase("111620")) {
            DDRMST.AC_STS = 'N';
            IBS.init(SCCGWA, DDCIDREG);
            DDCIDREG.OPT = 'X';
            DDCIDREG.DATA.KEY.AC = DDRMST.KEY.CUS_AC;
            DDCIDREG.DATA.KEY.CCY = DDCUDRDD.CCY;
            DDCIDREG.DATA.KEY.CCY_TYPE = DDCUDRDD.CCY_TYPE;
            S000_CALL_DDZIDREG();
            if (pgmRtn) return;
        }
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
            DDRMST.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
            if (DDCUDRDD.FEE_FLG != 'Y') {
                DDRMST.LAST_FN_DATE = SCCGWA.COMM_AREA.AC_DATE;
            }
            DDRMST.LAST_TLR = SCCGWA.COMM_AREA.TL_ID;
            DDRMST.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRMST.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DDTMST();
            if (pgmRtn) return;
        }
    }
    public void B140_PRT_UNPT_ITEM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRVCH);
        DDRVCH.KEY.CUS_AC = DDCUDRDD.AC;
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DC") 
            && CICCUST.O_DATA.O_CI_TYP == '1') {
            IBS.init(SCCGWA, CICQACRL);
            CICQACRL.DATA.REL_AC_NO = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
            CICQACRL.DATA.AC_REL = "12";
            CICQACRL.FUNC = '4';
            CICQACRL.FUNC = 'I';
            IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
            CEP.TRC(SCCGWA, CICQACRL.RC.RC_CODE);
            CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_AC_NO);
            if (CICQACRL.RC.RC_CODE == 0) {
                DDRVCH.KEY.CUS_AC = CICQACRL.O_DATA.O_AC_NO;
            }
        }
        CEP.TRC(SCCGWA, DDRVCH.KEY.CUS_AC);
        T000_READ_UPDATE_DDTVCH();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRVCH.PSBK_STS);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        JIBS_tmp_str[2] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if ((DDRVCH.PSBK_STS == 'U' 
            || DDRVCH.PSBK_STS == 'W') 
            && !JIBS_tmp_str[0].equalsIgnoreCase("0111804") 
            && !JIBS_tmp_str[2].equalsIgnoreCase("0115540") 
            && BPCPARMC.DATA_TXT.RBASE_TYP.RBASE_TYP3 != 'Y') {
            if (DDRVCH.PSBK_STS == 'U') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.M_PSBK_UNWRITE_LOST);
            }
            if (DDRVCH.PSBK_STS == 'W') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.M_PSBK_WRITE_LOST);
            }
        }
        if (DDRVCH.PSBK_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_VCH_IS_CANCEL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRVCH.PSBK_SEQ != 0 
            && DDCUDRDD.PSBK_SEQ != 0 
            && DDRVCH.PSBK_SEQ != DDCUDRDD.PSBK_SEQ 
            && SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_OLD_VCH_NOT_DR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DDCIPSBK);
        if (DDRVCH.VCH_TYPE == '1' 
            || DDRVCH.VCH_TYPE == '2') {
            WS_OVD_AMT_FLG = 'N';
            DDCIPSBK.FUNC = 'T';
            DDCIPSBK.AC = DDRVCH.KEY.CUS_AC;
            DDCIPSBK.UPT_CCY = DDCUDRDD.CCY;
            DDCIPSBK.UPT_CCY_TYPE = DDCUDRDD.CCY_TYPE;
            DDCIPSBK.PSBK_NO = DDCUDRDD.PSBK_NO;
            DDCIPSBK.UPT_MMO = DDCUDRDD.TX_MMO;
            if (DDCUDRDD.TX_AMT >= 0) {
                DDCIPSBK.UPT_AMT = DDCUDRDD.TX_AMT;
            } else {
                DDCIPSBK.UPT_AMT = 0 - DDCUDRDD.TX_AMT;
                WS_OVD_AMT_FLG = 'Y';
            }
            DDCIPSBK.AC_ATTR = WS_TMP_ACATTR;
            if (WS_OVD_AMT_FLG == 'N'
                && SCCGWA.COMM_AREA.CANCEL_IND == ' '
                || WS_OVD_AMT_FLG == 'Y'
                && SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                DDCIPSBK.UPT_TXN_TYPE = 'D';
            } else if (WS_OVD_AMT_FLG == 'Y'
                && SCCGWA.COMM_AREA.CANCEL_IND == ' '
                || WS_OVD_AMT_FLG == 'N'
                && SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                DDCIPSBK.UPT_TXN_TYPE = 'C';
            } else {
            }
            S000_CALL_DDZIPSBK();
            if (pgmRtn) return;
        }
    }
    public void B110_CL_USE_LMT_PROC() throws IOException,SQLException,Exception {
    }
    public void B150_BACK_VALUE_PROC() throws IOException,SQLException,Exception {
        if ((DDCUDRDD.VAL_DATE > 0) 
            && (DDCUDRDD.VAL_DATE < SCCGWA.COMM_AREA.AC_DATE)) {
            IBS.init(SCCGWA, DDCIBACK);
            DDCIBACK.OPT = 'W';
            DDCIBACK.TX_TYPE = 'T';
            DDCIBACK.FUNC = 'T';
            DDCIBACK.AC_NO = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
            CEP.TRC(SCCGWA, DDCIBACK.AC_NO);
            DDCIBACK.DATE = DDCUDRDD.VAL_DATE;
            DDCIBACK.CCY = DDCUDRDD.CCY;
            DDCIBACK.CCY_TYP = DDCUDRDD.CCY_TYPE;
            DDCIBACK.AMT = DDCUDRDD.TX_AMT;
            DDCIBACK.LAST_POST_DATE = DDRCCY.LAST_POST_DATE;
            S000_CALL_DDZIBACK();
            if (pgmRtn) return;
        }
    }
    public void B170_FIN_TX_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        if (DDCUDRDD.TX_TYPE == 'T' 
            && DDCUDRDD.OTHER_AC.trim().length() > 0) {
            B170_01_GET_RLT_BR_INFO();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDCUDRDD.RLT_AC);
        IBS.init(SCCGWA, BPCPFHIS);
        BPCPFHIS.DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPFHIS.DATA.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        BPCPFHIS.DATA.REF_NO = DDCUDRDD.TX_REF;
        BPCPFHIS.DATA.SMS_FLG = DDCUDRDD.SMS_FLG;
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DC")) {
            BPCPFHIS.DATA.SUMUP_FLG = '1';
        } else {
            BPCPFHIS.DATA.SUMUP_FLG = '2';
        }
        if (DDCUDRDD.VS_AC_FLG == 'Y') {
            BPCPFHIS.DATA.SUMUP_FLG = '4';
        }
        if (DDCUDRDD.NOT_STD_AC_FLG == 'Y') {
            BPCPFHIS.DATA.SUMUP_FLG = '3';
        }
        BPCPFHIS.DATA.TX_DRCR_FLG = 'D';
        BPCPFHIS.DATA.PRINT_IND = 'Y';
        CEP.TRC(SCCGWA, DDCUDRDD.AC);
        BPCPFHIS.DATA.AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        if (DCCUCINO.CARD_LNK_TYP == '2') {
            BPCPFHIS.DATA.AC = DCCUCINO.CARD_NO;
        }
        BPCPFHIS.DATA.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.ACO_AC);
        CEP.TRC(SCCGWA, DDCUDRDD.CARD_NO);
        if (CICCUST.O_DATA.O_CI_TYP == '1' 
            && CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DC")) {
            CEP.TRC(SCCGWA, "PERSONAL CARD");
            if (DDCUDRDD.CARD_NO.trim().length() > 0) {
                BPCPFHIS.DATA.TX_TOOL = DDCUDRDD.CARD_NO;
            } else {
                BPCPFHIS.DATA.TX_TOOL = DDCUDRDD.AC;
            }
        } else {
            if (CICCUST.O_DATA.O_CI_TYP == '2' 
                && CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
                CEP.TRC(SCCGWA, "COMPANY CARD");
                BPCPFHIS.DATA.TX_TOOL = DDCUDRDD.CARD_NO;
            }
        }
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_TOOL);
        CEP.TRC(SCCGWA, DDCUDRDD.OTH_TX_TOOL);
        if (DDCUDRDD.OTH_TX_TOOL.trim().length() > 0) {
            BPCPFHIS.DATA.OTH_TX_TOOL = DDCUDRDD.OTH_TX_TOOL;
        }
        if (DDCUDRDD.VAL_DATE > 0) {
            BPCPFHIS.DATA.TX_VAL_DT = DDCUDRDD.VAL_DATE;
        } else {
            BPCPFHIS.DATA.TX_VAL_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        BPCPFHIS.DATA.TX_CCY = DDCUDRDD.CCY;
        BPCPFHIS.DATA.TX_CCY_TYP = DDCUDRDD.CCY_TYPE;
        BPCPFHIS.DATA.TX_AMT = DDCUDRDD.TX_AMT;
        BPCPFHIS.DATA.TX_TYPE = DDCUDRDD.TX_TYPE;
        BPCPFHIS.DATA.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        CEP.TRC(SCCGWA, DDCUDRDD.BV_TYP);
        CEP.TRC(SCCGWA, DDRCCY.AC_TYPE);
        if (DDCUDRDD.BV_TYP == '2' 
            && CICCUST.O_DATA.O_CI_TYP == '1' 
            && DDRCCY.AC_TYPE != '8') {
            BPCPFHIS.DATA.BV_CODE = "A00015";
            BPCPFHIS.DATA.BV_NO = DDCUDRDD.PSBK_NO;
        }
        if (DDCUDRDD.CHQ_NO.trim().length() > 0) {
            BPCPFHIS.DATA.BV_NO = DDCUDRDD.CHQ_NO;
            CEP.TRC(SCCGWA, DDCUDRDD.CHQ_TYPE);
            if (DDCUDRDD.CHQ_TYPE == '1') {
                BPCPFHIS.DATA.BV_CODE = "C00002";
            } else if (DDCUDRDD.CHQ_TYPE == '2') {
                BPCPFHIS.DATA.BV_CODE = "C00003";
            } else if (DDCUDRDD.CHQ_TYPE == '3') {
                BPCPFHIS.DATA.BV_CODE = "C00004";
            } else if (DDCUDRDD.CHQ_TYPE == '6') {
                BPCPFHIS.DATA.BV_CODE = "A00056";
            } else {
            }
        }
        if (BPCPFHIS.DATA.BV_NO.trim().length() == 0 
            && BPCPFHIS.DATA.BV_CODE.equalsIgnoreCase("A00015")) {
            BPCPFHIS.DATA.BV_NO = DDRVCH.PSBK_NO;
        }
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.BV_CODE);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.BV_NO);
        BPCPFHIS.DATA.NARRATIVE = DDCUDRDD.NARRATIVE;
        BPCPFHIS.DATA.REMARK = DDCUDRDD.REMARKS;
        if (WS_REAL_VIR_FLG != 'Y') {
            BPCPFHIS.DATA.REMARK = DDCUDRDD.REMARKS;
        } else {
            BPCPFHIS.DATA.REMARK = "VIR: " + DDCUDRDD.AC;
        }
        CEP.TRC(SCCGWA, DDCUDRDD.REMARKS);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.REMARK);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            && DDCUDRDD.TX_MMO.trim().length() == 0) {
            DDCUDRDD.TX_MMO = "G004";
        }
        BPCPFHIS.DATA.TX_MMO = DDCUDRDD.TX_MMO;
        CEP.TRC(SCCGWA, DDRCCY.PROD_CODE);
        BPCPFHIS.DATA.PROD_CD = DDRCCY.PROD_CODE;
        if (DDCUDRDD.TX_TYPE == 'T') {
            BPCPFHIS.DATA.OTH_AC = DDCUDRDD.OTHER_AC;
            BPCPFHIS.DATA.OTH_TX_TOOL = DDCUDRDD.OTH_TX_TOOL;
            if (DDCUDRDD.RLT_AC.trim().length() > 0) {
                BPCPFHIS.DATA.RLT_AC = DDCUDRDD.RLT_AC;
                BPCPFHIS.DATA.RLT_AC_NAME = DDCUDRDD.RLT_AC_NAME;
                CEP.TRC(SCCGWA, DDCUDRDD.RLT_AC_NAME);
                BPCPFHIS.DATA.RLT_BANK = DDCUDRDD.RLT_BANK;
                BPCPFHIS.DATA.RLT_BK_NM = DDCUDRDD.RLT_BK_NM;
                BPCPFHIS.DATA.ST_FILL.RTL_CUST_TYP = DDCUDRDD.RLT_CI_TYP;
                BPCPFHIS.DATA.ST_FIL = IBS.CLS2CPY(SCCGWA, BPCPFHIS.DATA.ST_FILL);
                BPCPFHIS.DATA.RLT_TX_TOOL = DDCUDRDD.RLT_TX_TOOL;
                BPCPFHIS.DATA.RLT_REF_NO = DDCUDRDD.RLT_REF_NO;
                BPCPFHIS.DATA.RLT_CCY = DDCUDRDD.RLT_CCY;
                BPCPFHIS.DATA.RLT_TX_TOOL = DDCUDRDD.RLT_TX_TOOL;
            } else {
                if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
                    BPCPFHIS.DATA.RLT_AC = DDCUDRDD.OTHER_AC;
                    BPCPFHIS.DATA.RLT_AC_NAME = AICPQMIB.OUTPUT_DATA.CHS_NM;
                    BPCPFHIS.DATA.RLT_BANK = "" + AICPQMIB.INPUT_DATA.BR;
                    JIBS_tmp_int = BPCPFHIS.DATA.RLT_BANK.length();
                    for (int i=0;i<6-JIBS_tmp_int;i++) BPCPFHIS.DATA.RLT_BANK = "0" + BPCPFHIS.DATA.RLT_BANK;
                    BPCPFHIS.DATA.RLT_BK_NM = BPCPQORG.CHN_NM;
                    BPCPFHIS.DATA.RLT_CCY = AICPQMIB.INPUT_DATA.CCY;
                } else {
                    BPCPFHIS.DATA.RLT_AC = DDCUDRDD.OTHER_AC;
                    CEP.TRC(SCCGWA, CICOCUST.O_DATA.O_CI_TYP);
                    if (CICOCUST.O_DATA.O_CI_TYP == '1') {
                        BPCPFHIS.DATA.RLT_AC_NAME = CICOCUST.O_DATA.O_CI_NM;
                    } else {
                        BPCPFHIS.DATA.RLT_AC_NAME = CICQACRI.O_DATA.O_AC_CNM;
                    }
                    BPCPFHIS.DATA.RLT_BANK = "" + BPCPQORG.BR;
                    JIBS_tmp_int = BPCPFHIS.DATA.RLT_BANK.length();
                    for (int i=0;i<6-JIBS_tmp_int;i++) BPCPFHIS.DATA.RLT_BANK = "0" + BPCPFHIS.DATA.RLT_BANK;
                    BPCPFHIS.DATA.RLT_BK_NM = BPCPQORG.CHN_NM;
                    BPCPFHIS.DATA.RLT_REF_NO = CICOCUST.O_DATA.O_CI_NO;
                    BPCPFHIS.DATA.RLT_CCY = CICQACRI.O_DATA.O_CCY;
                    BPCPFHIS.DATA.RLT_TX_TOOL = DDCUDRDD.OTH_TX_TOOL;
                }
            }
        }
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.RLT_AC);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.RLT_AC_NAME);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.RLT_BANK);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.ST_FILL.RTL_CUST_TYP);
        CEP.TRC(SCCGWA, WS_CTL_TYPE);
        if (WS_CTL_TYPE == '1') {
            BPCPFHIS.DATA.PV_T_TYP = '1';
        } else if (WS_CTL_TYPE == '2') {
            BPCPFHIS.DATA.PV_T_TYP = '2';
        } else if (WS_CTL_TYPE == '3') {
            BPCPFHIS.DATA.PV_T_TYP = '3';
        } else if (WS_CTL_TYPE == '4') {
            BPCPFHIS.DATA.PV_T_TYP = '4';
        } else {
        }
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.PV_T_TYP);
        BPCPFHIS.DATA.VAL_BAL_CCY = DDCUDRDD.CCY;
        BPCPFHIS.DATA.VAL_BAL = WS_CURR_BAL;
        if (DDCUDRDD.AUTO_DDTOTD_FLG == 'Y' 
            && (DDRMST.CI_TYP == '2' 
            || DDRMST.CI_TYP == '3')) {
            BPCPFHIS.DATA.PRINT_IND = 'N';
        } else {
            BPCPFHIS.DATA.PRINT_IND = 'Y';
        }
        CEP.TRC(SCCGWA, DDCUDRDD.HIS_SHOW_FLG);
        BPCPFHIS.DATA.DISPLAY_IND = DDCUDRDD.HIS_SHOW_FLG;
        CEP.TRC(SCCGWA, DDCUDRDD.TX_AMT);
        if (DDCUDRDD.TX_AMT == 0) {
            BPCPFHIS.DATA.DISPLAY_IND = 'N';
        }
        IBS.init(SCCGWA, DDRFHIS);
        if (DDCUDRDD.CHQ_NO.trim().length() > 0) {
            if (DDCUDRDD.CHQ_TYPE == '1') {
                DDRFHIS.PORT = "00002";
            } else if (DDCUDRDD.CHQ_TYPE == '2') {
                DDRFHIS.PORT = "00003";
            } else if (DDCUDRDD.CHQ_TYPE == '3') {
                DDRFHIS.PORT = "00001";
            } else if (DDCUDRDD.CHQ_TYPE == '4') {
                DDRFHIS.PORT = "00118";
            } else if (DDCUDRDD.CHQ_TYPE == '5') {
                DDRFHIS.PORT = "00004";
            } else if (DDCUDRDD.CHQ_TYPE == '6') {
                DDRFHIS.PORT = "00022";
            } else if (DDCUDRDD.CHQ_TYPE == '7') {
                DDRFHIS.PORT = "00005";
            } else if (DDCUDRDD.CHQ_TYPE == '8') {
                DDRFHIS.PORT = "00006";
            }
        }
        DDRFHIS.TX_DATE = SCCGWA.COMM_AREA.TR_DATE;
        DDRFHIS.AC_NO = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        if (DCCUCINO.CARD_LNK_TYP == '2') {
            DDRFHIS.AC_NO = DCCUCINO.CARD_NO;
        }
        DDRFHIS.TX_TYPE = 'F';
        DDRFHIS.SVR_CODE = SCCGWA.COMM_AREA.SERV_CODE;
        DDRFHIS.TX_AMT = DDCUDRDD.TX_AMT;
        DDRFHIS.DOMBR = DDRMST.OWNER_BR;
        DDRFHIS.BKBR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRFHIS.CCY = DDCUDRDD.CCY;
        BPCPFHIS.DATA.TX_DRCR_FLG = 'D';
        DDRFHIS.TXTIME = SCCGWA.COMM_AREA.TR_TIME;
        DDRFHIS.REF = DDCUDRDD.TX_REF;
        DDRFHIS.TXBR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRFHIS.OPR = SCCGWA.COMM_AREA.TL_ID;
        DDRFHIS.LEDGER_BAL = DDRCCY.CURR_BAL;
        DDRFHIS.CI_NO = CICACCU.DATA.CI_NO;
        CEP.TRC(SCCGWA, DDCUDRDD.CHQ_NO);
        BPCPFHIS.DATA.FMT_CODE = "DD099";
        BPCPFHIS.DATA.FMT_LEN = 190;
        BPCPFHIS.DATA.FMT_DATA = IBS.CLS2CPY(SCCGWA, DDRFHIS);
        S000_CALL_BPZPFHIS();
        if (pgmRtn) return;
    }
    public void B170_01_GET_RLT_BR_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AICPQMIB.INPUT_DATA.BR);
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_OPN_BR);
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
            BPCPQORG.BR = AICPQMIB.INPUT_DATA.BR;
        } else {
            BPCPQORG.BR = CICQACRI.O_DATA.O_OPN_BR;
        }
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
    }
    public void B175_VIR_VTXD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FSCSVTX);
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            FSCSVTX.TRAN_DATE = SCCGWA.COMM_AREA.AC_DATE;
            FSCSVTX.SERIAL_NO = SCCGWA.COMM_AREA.JRN_NO;
            FSCSVTX.SUB_SERIAL_NO = SCCGWA.COMM_AREA.CALL_SEQ;
            FSCSVTX.PAY_ACCT = WS_REL_AC;
            FSCSVTX.PAY_NME = CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM;
            FSCSVTX.CCY = DDCUDRDD.CCY;
            FSCSVTX.CCY_TYP = DDCUDRDD.CCY_TYPE;
            if (DDCUDRDD.OTHER_AC.trim().length() > 0) {
                FSCSVTX.RES_ACCT = DDCUDRDD.OTHER_AC;
                FSCSVTX.RES_NME = WS_RES_NAME;
            } else {
                FSCSVTX.RES_ACCT = DDCUDRDD.RLT_AC;
                FSCSVTX.RES_NME = DDCUDRDD.RLT_AC_NAME;
            }
            FSCSVTX.DR_FLG = 'D';
            FSCSVTX.TRANS_ATM = DDCUDRDD.TX_AMT;
            if (WS_REAL_VIR_FLG != 'Y') {
                FSCSVTX.VIR_ACCT = " ";
            } else {
                FSCSVTX.VIR_ACCT = DDCUDRDD.AC;
            }
            FSCSVTX.TRAN_TIME = SCCGWA.COMM_AREA.TR_TIME;
            FSCSVTX.ZY_CODE = DDCUDRDD.TX_MMO;
            FSCSVTX.REQFM_NO = CICQACRI.O_DATA.O_FRM_APP;
            FSCSVTX.EC_FLG = ' ';
            FSCSVTX.REMARK = DDCUDRDD.REMARKS;
            FSCSVTX.USE = DDCUDRDD.NARRATIVE;
        } else {
            FSCSVTX.TRAN_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            FSCSVTX.SERIAL_NO = GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
            CEP.TRC(SCCGWA, FSCSVTX.TRAN_DATE);
            CEP.TRC(SCCGWA, FSCSVTX.SERIAL_NO);
        }
        S000_CALL_FSZVTXD();
        if (pgmRtn) return;
    }
    public void B180_NOTICE_BTE_SYS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FSCBTE);
        FSCBTE.SERIAL_NO = SCCGWA.COMM_AREA.JRN_NO;
        FSCBTE.SUB_SERIAL_NO = SCCGWA.COMM_AREA.CALL_SEQ;
        FSCBTE.PAY_ACCT = WS_REL_AC;
        FSCBTE.CRYTYPE = DDCUDRDD.CCY;
        FSCBTE.PAY_NME = CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM;
        FSCBTE.TRAN_TYPE = 'D';
        FSCBTE.TRAN_ZY = DDCUDRDD.TX_MMO;
        FSCBTE.TRAN_AMT = DDCUDRDD.TX_AMT;
        FSCBTE.TRAN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        FSCBTE.TRAN_TIME = SCCGWA.COMM_AREA.TR_TIME;
        if (DDCUDRDD.OTHER_AC.trim().length() > 0) {
            FSCBTE.RES_ACCT = DDCUDRDD.OTHER_AC;
            FSCBTE.RES_NME = WS_RES_NAME;
        } else {
            FSCBTE.RES_ACCT = DDCUDRDD.RLT_AC;
            FSCBTE.RES_NME = DDCUDRDD.RLT_AC_NAME;
        }
        FSCBTE.RES_BZ = DDCUDRDD.CCY;
        FSCBTE.T_RMK = DDCUDRDD.REMARKS;
        FSCBTE.T_USE = DDCUDRDD.NARRATIVE;
        if (WS_REAL_VIR_FLG != 'Y') {
            FSCBTE.VIR_ACCT = " ";
        } else {
            FSCBTE.VIR_ACCT = DDCUDRDD.AC;
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            FSCBTE.TRANS_STATUS = '1';
        } else {
            FSCBTE.TRANS_STATUS = '0';
        }
        FSCBTE.ACC_BAR = WS_CURR_BAL;
        IBS.init(SCCGWA, SCCTPCL);
        SCCTPCL.SERV_AREA.OBJ_SYSTEM = K_OBJ_SYSTEM4;
        SCCTPCL.SERV_AREA.SERV_CODE = K_SERV_CODE4;
        SCCTPCL.SERV_AREA.SERV_TYPE = ' ';
        SCCTPCL.INP_AREA.SERV_DATA_LEN = 1083;
        SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, FSCBTE);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_CSIF_NOTICE);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_BVMS_INQ);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_BH_FILE_SEND);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_BH_FILE_ECIF);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_AC_CHG_INF);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ACGL_GL0002);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_PASSWORD_CHK);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_SMS_INFO);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ECIF_SG0001);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_CHG_NOTICE);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ACGL_GL0001);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ECIF_AC0001);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.SN_SMS_NOTICE);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ECIF_AC0002);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.CC_CHANGE_CARD);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.SC_SCF_NOTICE);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ECIF_AC0003);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_IBIL_NOTICE);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_BROADCAST);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_CASH_CHG);
        if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
            S000_CALL_SCZTPCL();
            if (pgmRtn) return;
        }
    }
    public void B180_UPD_TLR_TOT() throws IOException,SQLException,Exception {
    }
    public void B185_NOTICE_KHMS_SYS_PROC() throws IOException,SQLException,Exception {
        if (DDCUDRDD.VS_AC_FLG == 'V') {
            IBS.init(SCCGWA, CICMAGT);
            CICMAGT.DATA.AGT_TYP = WS_AGT_TYP;
            CICMAGT.DATA.ENTY_TYP = '3';
            CICMAGT.DATA.AGT_STS = 'N';
            CICMAGT.DATA.ENTY_NO = DDCUDRDD.AC;
            S000_CALL_CIZMAGT();
            if (pgmRtn) return;
            if (CICMAGT.DATA.AGT_NO.trim().length() == 0) {
                IBS.init(SCCGWA, CICMAGT);
                CICMAGT.DATA.AGT_TYP = WS_AGT_TYP;
                CICMAGT.DATA.ENTY_TYP = '1';
                CICMAGT.DATA.AGT_STS = 'N';
                CICMAGT.DATA.ENTY_NO = DDCUDRDD.AC;
                S000_CALL_CIZMAGT();
                if (pgmRtn) return;
            }
        } else {
            IBS.init(SCCGWA, CICMAGT);
            CICMAGT.DATA.AGT_TYP = WS_AGT_TYP;
            CICMAGT.DATA.ENTY_TYP = '1';
            CICMAGT.DATA.AGT_STS = 'N';
            CICMAGT.DATA.ENTY_NO = DDCUDRDD.AC;
            S000_CALL_CIZMAGT();
            if (pgmRtn) return;
        }
        if (CICMAGT.DATA.AGT_NO.trim().length() > 0) {
            IBS.init(SCCGWA, DDCNKHMS);
            if (DDCUDRDD.VS_AC_FLG == 'Y') {
                DDCNKHMS.AC_NO = DDCUDRDD.AC;
                DDCNKHMS.VA_NO = DDCUDRDD.AC;
            } else {
                if (DDCUDRDD.VS_AC_FLG == 'V') {
                    DDCNKHMS.VA_NO = DDCUDRDD.AC;
                    DDCNKHMS.AC_NO = DDCUDRDD.AC;
                } else {
                    DDCNKHMS.VA_NO = DDCUDRDD.AC;
                    DDCNKHMS.AC_NO = DDCUDRDD.AC;
                }
            }
            DDCNKHMS.CCY = DDCUDRDD.CCY;
            DDCNKHMS.CCY_TYP = DDCUDRDD.CCY_TYPE;
            DDCNKHMS.BV_TYP = DDCUDRDD.BV_TYP;
            if (DDCUDRDD.CARD_NO.trim().length() > 0) {
                DDCNKHMS.BV_NO = DDCUDRDD.CARD_NO;
            } else {
                DDCNKHMS.BV_NO = DDCUDRDD.PSBK_NO;
            }
            DDCNKHMS.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            DDCNKHMS.TX_MMO = DDCUDRDD.TX_MMO;
            if (DDCUDRDD.TX_MMO.equalsIgnoreCase("GTV")
                || DDCUDRDD.TX_MMO.equalsIgnoreCase("X15")) {
                DDCNKHMS.C_FLG = '3';
            } else if (DDCUDRDD.TX_MMO.equalsIgnoreCase("GTU")
                || DDCUDRDD.TX_MMO.equalsIgnoreCase("X16")) {
                DDCNKHMS.C_FLG = '2';
            } else {
                DDCNKHMS.C_FLG = '1';
            }
            if (BPCPFHIS.DATA.OTH_TX_TOOL.trim().length() > 0) {
                DDCNKHMS.OT_AC = BPCPFHIS.DATA.OTH_TX_TOOL;
            } else {
                DDCNKHMS.OT_AC = DDCUDRDD.OTHER_AC;
            }
            if ((DDCUDRDD.OTHER_AC.trim().length() > 0 
                || DDCUDRDD.OTH_TX_TOOL.trim().length() > 0) 
                && !DDCUDRDD.OTH_STD_APP.equalsIgnoreCase("AI")) {
                IBS.init(SCCGWA, CICACCU);
                CICACCU.DATA.AGR_NO = DDCUDRDD.OTH_STD_AC;
                S000_CALL_CIZACCU();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, CICACCU.DATA.AC_CNM);
                DDCNKHMS.OT_NAME = CICACCU.DATA.AC_CNM;
                DDCNKHMS.OT_BR = DDCUDRDD.OTHER_BR;
                CEP.TRC(SCCGWA, DDCNKHMS.OT_BR);
            } else {
                if (DDCUDRDD.RLT_AC.trim().length() > 0) {
                    DDCNKHMS.OT_AC = DDCUDRDD.RLT_AC;
                    DDCNKHMS.OT_NAME = DDCUDRDD.RLT_AC_NAME;
                    DDCNKHMS.OT_BR = DDCUDRDD.RLT_BANK;
                }
            }
            DDCNKHMS.TX_AMT = DDCUDRDD.TX_AMT;
            DDCNKHMS.AC_BAL = DDRCCY.CURR_BAL + DDRCCY.CCAL_TOT_BAL;
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                DDCNKHMS.DR_CR_FLG = 'C';
                DDCNKHMS.TX_STS = '1';
            } else {
                DDCNKHMS.DR_CR_FLG = 'D';
                DDCNKHMS.TX_STS = '0';
            }
            DDCNKHMS.TX_TYP = '1';
            DDCNKHMS.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
            DDCNKHMS.JRN_SEQ = GWA_BP_AREA.FHIS_CUR_SEQ;
            DDCNKHMS.OT_JRN_NO = GWA_SC_AREA.REQ_SYS_JRN;
            DDCNKHMS.OT_DATE = GWA_SC_AREA.REQ_SYS_DATE;
            DDCNKHMS.TX_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDCNKHMS.TX_TIME = SCCGWA.COMM_AREA.TR_TIME;
            DDCNKHMS.TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            DDCNKHMS.TX_TLR = SCCGWA.COMM_AREA.TL_ID;
            DDCNKHMS.TX_CHNL = SCCGWA.COMM_AREA.CHNL;
            DDCNKHMS.REMARK = DDCUDRDD.REMARKS;
            DDCNKHMS.NARRATIVE = DDCUDRDD.NARRATIVE;
            DDCNKHMS.USE_BAL = WS_USE_BAL;
            IBS.init(SCCGWA, SCCTPCL);
            if (WS_I == 1) {
                SCCTPCL.SERV_AREA.OBJ_SYSTEM = K_OBJ_SYSTEM2;
                SCCTPCL.SERV_AREA.SERV_CODE = K_SERV_CODE2;
            } else {
                SCCTPCL.SERV_AREA.OBJ_SYSTEM = K_OBJ_SYSTEM3;
                SCCTPCL.SERV_AREA.SERV_CODE = K_SERV_CODE2;
            }
            SCCTPCL.SERV_AREA.SERV_TYPE = ' ';
            SCCTPCL.INP_AREA.SERV_DATA_LEN = 802;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, DDCNKHMS);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_CSIF_NOTICE);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_BVMS_INQ);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_BH_FILE_SEND);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_BH_FILE_ECIF);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_AC_CHG_INF);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ACGL_GL0002);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_PASSWORD_CHK);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_SMS_INFO);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ECIF_SG0001);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_CHG_NOTICE);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ACGL_GL0001);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ECIF_AC0001);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.SN_SMS_NOTICE);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ECIF_AC0002);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.CC_CHANGE_CARD);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.SC_SCF_NOTICE);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ECIF_AC0003);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_IBIL_NOTICE);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_BROADCAST);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_CASH_CHG);
            if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
                && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                S000_CALL_SCZTPCL();
                if (pgmRtn) return;
            }
            if (WS_I == 1) {
                B185_01_WRITE_DDTNOSI_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B185_01_WRITE_DDTNOSI_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRNOSI);
        DDRNOSI.KEY.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRNOSI.VA_AC = DDCNKHMS.VA_NO;
        DDRNOSI.AC_NO = DDCNKHMS.AC_NO;
        DDRNOSI.CCY = DDCNKHMS.CCY;
        DDRNOSI.CCY_TYP = DDCNKHMS.CCY_TYP;
        DDRNOSI.BV_TYP = DDCNKHMS.BV_TYP;
        DDRNOSI.BV_NO = DDCNKHMS.BV_NO;
        DDRNOSI.TX_AMT = DDCNKHMS.TX_AMT;
        DDRNOSI.AC_BAL = DDCNKHMS.AC_BAL;
        DDRNOSI.DR_CR_FLG = DDCNKHMS.DR_CR_FLG;
        DDRNOSI.TX_CODE = DDCNKHMS.TX_CODE;
        DDRNOSI.TX_STS = DDCNKHMS.TX_STS;
        DDRNOSI.TX_TYP = DDCNKHMS.TX_TYP;
        DDRNOSI.TX_MMO = DDCNKHMS.TX_MMO;
        DDRNOSI.C_FLG = DDCNKHMS.C_FLG;
        DDRNOSI.KEY.JRN_NO = DDCNKHMS.JRN_NO;
        DDRNOSI.KEY.JRN_SEQ = DDCNKHMS.JRN_SEQ;
        DDRNOSI.OT_AC = DDCNKHMS.OT_AC;
        DDRNOSI.OT_NAME = DDCNKHMS.OT_NAME;
        DDRNOSI.OT_BR = DDCNKHMS.OT_BR;
        DDRNOSI.OT_JRN_NO = DDCNKHMS.OT_JRN_NO;
        DDRNOSI.OT_DATE = DDCNKHMS.OT_DATE;
        DDRNOSI.TX_TIME = DDCNKHMS.TX_TIME;
        DDRNOSI.TX_BR = DDCNKHMS.TX_BR;
        DDRNOSI.TX_TLR = DDCNKHMS.TX_TLR;
        DDRNOSI.TX_CHNL = DDCNKHMS.TX_CHNL;
        DDRNOSI.REMARK = DDCNKHMS.REMARK;
        DDRNOSI.NARRATIVE = DDCNKHMS.NARRATIVE;
        DDRNOSI.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRNOSI.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRNOSI.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRNOSI.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
            DDRNOSI.SEND_FLG = 'Y';
        } else {
            DDRNOSI.SEND_FLG = 'N';
        }
        T000_WRITE_DDTNOSI();
        if (pgmRtn) return;
    }
    public void B205_CHQ_DEBIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCICHQD);
        DDCICHQD.AC_NO = DDCUDRDD.AC;
        DDCICHQD.CCY = DDCUDRDD.CCY;
        DDCICHQD.CHQ_NO = DDCUDRDD.CHQ_NO;
        DDCICHQD.CHQ_AMT = DDCUDRDD.TX_AMT;
        S000_CALL_DDZICHQD();
        if (pgmRtn) return;
    }
    public void B209_CHANGE_PROD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        CEP.TRC(SCCGWA, DDCIQPRD.OUTPUT_DATA.PRDT_MODEL);
        BPCPOEWA.DATA.CNTR_TYPE = DDCUDRDD.PRDT_MODEL;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.CNTR_TYPE);
        BPCPOEWA.DATA.PROD_CODE = DDRCCY.PROD_CODE;
        BPCPOEWA.DATA.EVENT_CODE = "OD";
        BPCPOEWA.DATA.BR_OLD = DDRCCY.OWNER_BRDP;
        BPCPOEWA.DATA.BR_NEW = DDRCCY.OWNER_BRDP;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = DDCUDRDD.CCY;
        if (DDCUDRDD.VAL_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            BPCPOEWA.DATA.VALUE_DATE = DDCUDRDD.VAL_DATE;
        } else {
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            } else {
                BPCPOEWA.DATA.VALUE_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            }
        }
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = WS_DIFF_BAL;
        BPCPOEWA.DATA.CI_NO = CICCUST.O_DATA.O_CI_NO;
        BPCPOEWA.DATA.AC_NO = DDRCCY.KEY.AC;
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void B210_GEN_VCH_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        CEP.TRC(SCCGWA, DDCIQPRD.OUTPUT_DATA.PRDT_MODEL);
        BPCPOEWA.DATA.CNTR_TYPE = DDCUDRDD.PRDT_MODEL;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.CNTR_TYPE);
        BPCPOEWA.DATA.PROD_CODE = DDRCCY.PROD_CODE;
        BPCPOEWA.DATA.EVENT_CODE = "DR";
        BPCPOEWA.DATA.BR_OLD = DDRCCY.OWNER_BRDP;
        BPCPOEWA.DATA.BR_NEW = DDRCCY.OWNER_BRDP;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = DDCUDRDD.CCY;
        if (DDCUDRDD.VAL_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            BPCPOEWA.DATA.VALUE_DATE = DDCUDRDD.VAL_DATE;
        } else {
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            } else {
                BPCPOEWA.DATA.VALUE_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            }
        }
        if (DDCIPSBK.PSBK_NO.trim().length() > 0 
            && !IBS.isNumeric(DDCIPSBK.PSBK_NO)) {
            BPCPOEWA.DATA.PORT = "DDBV01";
            BPCPOEWA.DATA.REF_NO = DDCIPSBK.PSBK_NO;
        }
        if (IBS.isNumeric(DDCUDRDD.CHQ_NO) 
            && !DDCUDRDD.CHQ_NO.equalsIgnoreCase("0")) {
            if (DDCUDRDD.CHQ_TYPE == DPCPARMP.CASH_CHQ) {
                BPCPOEWA.DATA.PORT = "DDBV02";
            } else {
                if (DDCUDRDD.CHQ_TYPE == DPCPARMP.TRF_CHQ) {
                    BPCPOEWA.DATA.PORT = "DDBV03";
                } else {
                    BPCPOEWA.DATA.PORT = "RMBV01";
                }
            }
            BPCPOEWA.DATA.REF_NO = DDCUDRDD.CHQ_NO;
        }
        if (DDCUDRDD.TX_AMT < 0) {
            DDCUDRDD.TX_AMT = DDCUDRDD.TX_AMT * -1;
            CEP.TRC(SCCGWA, DDCUDRDD.TX_AMT);
            BPCPOEWA.DATA.AMT_INFO[11-1].AMT = DDCUDRDD.TX_AMT;
        } else {
            BPCPOEWA.DATA.AMT_INFO[1-1].AMT = DDCUDRDD.TX_AMT;
        }
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[11-1].AMT);
        BPCPOEWA.DATA.CI_NO = CICCUST.O_DATA.O_CI_NO;
        BPCPOEWA.DATA.AC_NO = DDRCCY.KEY.AC;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AC_NO);
        BPCPOEWA.DATA.CHQ_NO = DDCUDRDD.CHQ_NO;
        BPCPOEWA.DATA.DESC = DDCUDRDD.REMARKS;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[1-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.REF_NO);
        if (DDCUDRDD.OTHER_AC.trim().length() > 0) {
            BPCPOEWA.DATA.AC_NO_REL = DDCUDRDD.OTHER_AC;
            BPCPOEWA.DATA.THEIR_AC = DDCUDRDD.OTHER_AC;
        }
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.THEIR_AC);
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void B230_ADVICE_NOTE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCODRNT);
        DDCODRNT.AC_NO = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        DDCODRNT.CCY = DDCUDRDD.CCY;
        DDCODRNT.CASH_AMT = 0;
        DDCODRNT.TRF_AMT = DDCUDRDD.TX_AMT;
        DDCODRNT.CHQ_AMT = 0;
        DDCODRNT.CHQ_NO = "" + 0;
        JIBS_tmp_int = DDCODRNT.CHQ_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) DDCODRNT.CHQ_NO = "0" + DDCODRNT.CHQ_NO;
        DDCODRNT.RMKS = DDCUDRDD.REMARKS;
        CEP.TRC(SCCGWA, DDCODRNT.AC_NO);
        CEP.TRC(SCCGWA, DDCODRNT.ENG_NM);
        CEP.TRC(SCCGWA, DDCODRNT.ADRESS);
        CEP.TRC(SCCGWA, DDCODRNT.CCY);
        CEP.TRC(SCCGWA, DDCODRNT.CASH_AMT);
        CEP.TRC(SCCGWA, DDCODRNT.CHQ_AMT);
        CEP.TRC(SCCGWA, DDCODRNT.TRF_AMT);
        CEP.TRC(SCCGWA, DDCODRNT.CHQ_NO);
        CEP.TRC(SCCGWA, DDCODRNT.RMKS);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DDCODRNT;
        SCCFMT.DATA_LEN = 633;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B240_GET_BR_CITY_FLG() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        IBS.init(SCCGWA, BPCPRGST);
        BPCPRGST.BR1 = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPRGST.BR2 = DDRCCY.OWNER_BR;
        S000_CALL_BPZPRGST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPRGST.BRANCH_FLG);
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = DDRCCY.OWNER_BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        WS_MST_BRANCH_BR = BPCPQORG.BRANCH_BR;
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        WS_GWA_BRANCH_BR = BPCPQORG.BRANCH_BR;
        if ((WS_GWA_BRANCH_BR == 121000 
            && WS_MST_BRANCH_BR == 104000) 
            || (WS_GWA_BRANCH_BR == 104000 
            && WS_MST_BRANCH_BR == 121000)) {
            CEP.TRC(SCCGWA, "SAMECITY");
            WS_SAME_CITY_FLG = 'Y';
        } else {
            CEP.TRC(SCCGWA, "DIFFCITY");
            WS_SAME_CITY_FLG = 'N';
        }
    }
    public void B250_FEE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSOLCD);
        IBS.init(SCCGWA, BPCPRMR);
        CEP.TRC(SCCGWA, "BEGIN");
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDCUDRDD.NOTCLEAR_FLG == 'Y' 
            && DDCUDRDD.CCY.equalsIgnoreCase("156") 
            && DDRMST.AC_STS_WORD.substring(41 - 1, 41 + 1 - 1).equalsIgnoreCase("1")) {
            DDCSOLCD.KEY.TYP = "PDD17";
            DDCSOLCD.KEY.CD = "001";
            BPCPRMR.DAT_PTR = DDCSOLCD;
            S000_CALL_SCCPARM();
            if (pgmRtn) return;
            if (DDCUDRDD.TX_AMT > DDCSOLCD.DATA_TXT.PRE_AMT) {
                CEP.TRC(SCCGWA, "AMTOK");
                WS_PRE_AMT = DDCUDRDD.TX_AMT - DDCSOLCD.DATA_TXT.PRE_AMT;
                S000_SET_FEE_INFO();
                if (pgmRtn) return;
                S000_CALL_FEE();
                if (pgmRtn) return;
            }
        } else {
            CEP.TRC(SCCGWA, "NOTSOL");
            WS_PRE_AMT = 0;
            S000_SET_FEE_INFO();
            if (pgmRtn) return;
            S000_CALL_FEE();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZPCDCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-P-CARD-TRKDAT-CHK", DCCPCDCK);
    }
    public void S000_CALL_EAZUTRAN() throws IOException,SQLException,Exception {
        EAZUTRAN EAZUTRAN = new EAZUTRAN();
        EAZUTRAN.MP(SCCGWA, EACUTRAN);
    }
    public void S000_CALL_DCZUAINQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DCZUAINQ, DCCUAINQ);
        if (DCCUAINQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUAINQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZIBUDT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-COMPUTE-BUI-DT", BPCIBUDT);
    }
    public void S000_LINK_BPZSFX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-FX", BPCFX);
        if (BPCFX.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFX.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_SCZTPCL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-LINK-EXTSERV", SCCTPCL);
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF);
        if (DCCUCINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFCSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-STS-TBL-AUTH", BPCFCSTS);
        CEP.TRC(SCCGWA, BPCFCSTS.RC);
        if (BPCFCSTS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFCSTS.RC);
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
    public void S000_CALL_DDZIBACK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_I_BACK_DATE, DDCIBACK);
        if (DDCIBACK.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIBACK.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PROC_FHIS, BPCPFHIS);
        if (BPCPFHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPFHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_I_INQ_DDPRD, DDCIQPRD);
        if (DDCIQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZICHQD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-CHQ-DR-PROC", DDCICHQD);
        if (DDCICHQD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCICHQD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIPSBK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_I_PSBK_PROC, DDCIPSBK);
    }
    public void S000_SET_FEE_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, DDCUDRDD.CARD_NO);
        CEP.TRC(SCCGWA, DDCUDRDD.CARD_BV_TYPE);
        CEP.TRC(SCCGWA, DDCUDRDD.BV_TYP);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR);
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DC")) {
            BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '4';
            BPCFFTXI.TX_DATA.CARD_PSBK_NO = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        } else if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
            if (CICCUST.O_DATA.O_CI_TYP == '1') {
                BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '5';
                BPCFFTXI.TX_DATA.CARD_PSBK_NO = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
            } else {
                BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '0';
                BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
            }
        } else if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("AI")) {
            BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '3';
            BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        } else {
            BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '0';
            BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        }
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP);
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC);
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CARD_PSBK_NO);
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = DDCUDRDD.CCY;
        BPCFFTXI.TX_DATA.CCY_TYPE = DDCUDRDD.CCY_TYPE;
        BPCFFTXI.TX_DATA.SVR_CD = SCCGWA.COMM_AREA.SERV_CODE;
        if (DDCUDRDD.CHQ_NO.trim().length() > 0) {
            BPCFFTXI.TX_DATA.SVR_CD = "0117777";
        }
        BPCFFTXI.TX_DATA.CI_NO = CICCUST.O_DATA.O_CI_NO;
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY);
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP);
        S000_CALL_BPZFFTXI();
        if (pgmRtn) return;
    }
    public void S000_CALL_FEE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTCALF);
        BPCTCALF.INPUT_AREA.FUNC_CODE = 'C';
        BPCTCALF.INPUT_AREA.ATTR_VAL.BNK_FLG = '0';
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL.CITY_FLG);
        BPCTCALF.INPUT_AREA.TX_AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.TX_AC);
        BPCTCALF.INPUT_AREA.TX_CCY = DDCUDRDD.CCY;
        BPCTCALF.INPUT_AREA.TX_CNT = 1;
        if (WS_PRE_AMT != 0) {
            BPCTCALF.INPUT_AREA.TX_AMT = WS_PRE_AMT;
        } else {
            BPCTCALF.INPUT_AREA.TX_AMT = DDCUDRDD.TX_AMT;
        }
        BPCTCALF.INPUT_AREA.TX_AMT = 0;
        BPCTCALF.INPUT_AREA.OTHER_AC = DDCUDRDD.OTHER_AC;
        BPCTCALF.INPUT_AREA.TX_CI = CICCUST.O_DATA.O_CI_NO;
        if (DDCUDRDD.CARD_NO.trim().length() > 0 
            && DDCUDRDD.CARD_ADSC_TYPE == 'C') {
            BPCTCALF.INPUT_AREA.PROD_CODE = DDCUDRDD.CARD_PROD_CD;
        } else {
            BPCTCALF.INPUT_AREA.PROD_CODE = DDRMST.PROD_CODE;
            if (BPCPRGST.BRANCH_FLG == 'Y' 
                || WS_SAME_CITY_FLG == 'Y') {
                BPCTCALF.INPUT_AREA.ATTR_VAL.CITY_FLG = '0';
            } else {
                BPCTCALF.INPUT_AREA.ATTR_VAL.CITY_FLG = '1';
            }
        }
        BPCTCALF.INPUT_AREA.ATTR_VAL.DC_FLG = '0';
        BPCTCALF.INPUT_AREA.PROD_CODE1 = DDRCCY.PROD_CODE;
        S000_CALL_BPZFCALF();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZFFTXI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_F_TX_INFO, BPCFFTXI);
        if (BPCFFTXI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFTXI.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFSCHG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-CHG-FEE", BPCFSCHG);
        if (BPCFSCHG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFSCHG.RC);
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
    public void S000_CALL_DDZUSIPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-U-SIPM-PROC", DDCUSIPM);
        if (DDCUSIPM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCUSIPM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFCALF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_F_CAL_FEE, BPCTCALF);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_UNI_CIZACCU, CICACCU);
        CEP.TRC(SCCGWA, CICACCU.RC.RC_CODE);
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
        if (CICACCU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZCKLS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CHECK-LST", CICCKLS);
        if (CICCKLS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCKLS.RC);
        }
    }
    public void S000_CALL_CIZSPVS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SVR-PVS-INF", CICSPVS);
        if (CICSPVS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSPVS.RC);
        }
    }
    public void S000_CALL_CIZREGLC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-REG-LC-CTL", CICREGLC);
        if (CICREGLC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICREGLC.RC);
        }
    }
    public void S000_CALL_CIZPDTL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-PDTL", CICPDTL);
    }
    public void S000_CALL_CIZMLRG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CAL-FLRG", CICMLRG);
        if (CICMLRG.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICMLRG.RC);
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
    public void T000_READ_UPDATE_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND);
        }
    }
    public void T000_READ_UPDATE_DDTVCH() throws IOException,SQLException,Exception {
        DDTVCH_RD = new DBParm();
        DDTVCH_RD.TableName = "DDTVCH";
        DDTVCH_RD.eqWhere = "CUS_AC";
        DDTVCH_RD.fst = true;
        DDTVCH_RD.upd = true;
        IBS.READ(SCCGWA, DDRVCH, DDTVCH_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READ_UPDATE_DDTGPMST() throws IOException,SQLException,Exception {
        DDTGPMST_RD = new DBParm();
        DDTGPMST_RD.TableName = "DDTGPMST";
        DDTGPMST_RD.upd = true;
        IBS.READ(SCCGWA, DDRGPMST, DDTGPMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_GRP_AC_NOT_FND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ UPDATE TABLE DDTGPMST ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTGPMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DDTGPMST() throws IOException,SQLException,Exception {
        DDTGPMST_RD = new DBParm();
        DDTGPMST_RD.TableName = "DDTGPMST";
        IBS.REWRITE(SCCGWA, DDRGPMST, DDTGPMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "REWRITE TABLE DDTGPMST ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTGPMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_DDTNOSI() throws IOException,SQLException,Exception {
        DDTNOSI_RD = new DBParm();
        DDTNOSI_RD.TableName = "DDTNOSI";
        IBS.WRITE(SCCGWA, DDRNOSI, DDTNOSI_RD);
    }
    public void T000_REWRITE_DDTCCY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRCCY.KEY.AC);
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.REWRITE(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void T000_REWRITE_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.REWRITE(SCCGWA, DDRMST, DDTMST_RD);
    }
    public void T000_WRITE_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.WRITE(SCCGWA, DDRCCY, DDTCCY_RD);
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
    public void T000_READ_UPD_DDTRSAC_1() throws IOException,SQLException,Exception {
        DDTRSAC_RD = new DBParm();
        DDTRSAC_RD.TableName = "DDTRSAC";
        DDTRSAC_RD.eqWhere = "TYPE,CTL_TYPE,OTH_AC";
        DDTRSAC_RD.upd = true;
        IBS.READ(SCCGWA, DDRRSAC, DDTRSAC_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READ_UPD_DDTRSAC() throws IOException,SQLException,Exception {
        DDTRSAC_RD = new DBParm();
        DDTRSAC_RD.TableName = "DDTRSAC";
        DDTRSAC_RD.eqWhere = "TYPE,CTL_TYPE,CI_NO";
        DDTRSAC_RD.upd = true;
        IBS.READ(SCCGWA, DDRRSAC, DDTRSAC_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_RSA_NFOUND);
        }
    }
    public void T000_REWRITE_DDTRSAC() throws IOException,SQLException,Exception {
        DDTRSAC_RD = new DBParm();
        DDTRSAC_RD.TableName = "DDTRSAC";
        IBS.REWRITE(SCCGWA, DDRRSAC, DDTRSAC_RD);
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
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            CEP.ERR(SCCGWA, BPCPRMM.RC);
        }
    }
    public void R000_AMT_EX_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_BUY_CCY);
        CEP.TRC(SCCGWA, WS_BUY_AMT);
        CEP.TRC(SCCGWA, WS_SELL_CCY);
        IBS.init(SCCGWA, BPCFX);
        BPCFX.FUNC = '3';
        BPCFX.CHNL = SCCGWA.COMM_AREA.CHNL;
        BPCFX.EXR_TYPE = BPCRBANK.EX_RA;
        BPCFX.BUY_CCY = DDCUDRDD.CCY;
        BPCFX.BUY_AMT = WS_BUY_AMT;
        BPCFX.SELL_CCY = WS_SELL_CCY;
        S000_LINK_BPZSFX();
        if (pgmRtn) return;
        WS_SELL_AMT = BPCFX.SELL_AMT;
        CEP.TRC(SCCGWA, WS_SELL_AMT);
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
    }
    public void R000_WITHDRAW_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_UDRDD_TMP);
        CEP.TRC(SCCGWA, "aabbccddee");
        CEP.TRC(SCCGWA, DDCUDRDD.TX_AMT);
        CEP.TRC(SCCGWA, DDCUDRDD.CCY);
        CEP.TRC(SCCGWA, DDCUDRDD.CCY_TYPE);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            IBS.init(SCCGWA, FSCRSPAY);
            FSCRSPAY.CAN_JRN_NO = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
            FSCRSPAY.CAN_AC_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            IBS.CALLCPN(SCCGWA, "FS-I-CAPL-RSPAY", FSCRSPAY);
        } else {
            if (WS_AVL_BAL < DDCUDRDD.TX_AMT) {
                IBS.init(SCCGWA, FSCTLEG);
                FSCTLEG.TRANS_ATM = DDCUDRDD.TX_AMT - WS_AVL_BAL;
                if (FSCTLEG.TRANS_ATM > DDCUDRDD.TX_AMT) {
                    FSCTLEG.TRANS_ATM = DDCUDRDD.TX_AMT;
                }
                if (DDCUDRDD.TRT_CTLW == null) DDCUDRDD.TRT_CTLW = "";
                JIBS_tmp_int = DDCUDRDD.TRT_CTLW.length();
                for (int i=0;i<32-JIBS_tmp_int;i++) DDCUDRDD.TRT_CTLW += " ";
                FSCTLEG.FT_FLG = DDCUDRDD.TRT_CTLW.substring(3 - 1, 3 + 1 - 1).charAt(0);
                if (DDCUDRDD.TRT_CTLW == null) DDCUDRDD.TRT_CTLW = "";
                JIBS_tmp_int = DDCUDRDD.TRT_CTLW.length();
                for (int i=0;i<32-JIBS_tmp_int;i++) DDCUDRDD.TRT_CTLW += " ";
                FSCTLEG.SER_FLG = DDCUDRDD.TRT_CTLW.substring(5 - 1, 5 + 1 - 1).charAt(0);
                FSCTLEG.RES_ACCT = DDCUDRDD.AC;
                FSCTLEG.CCY = DDCUDRDD.CCY;
                FSCTLEG.CCY_TYP = DDCUDRDD.CCY_TYPE;
                FSCTLEG.ZY_CODE = "A609";
                FSCTLEG.TRAN_DATE = SCCGWA.COMM_AREA.AC_DATE;
                IBS.CALLCPN(SCCGWA, "FS-I-CAPL-TLEG", FSCTLEG);
            }
        }
        CEP.TRC(SCCGWA, "aaddbbddccaa");
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUDACH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CHECK-ACCOUNT", DCCUDACH);
        CEP.TRC(SCCGWA, DCCUDACH.RC);
        if (DCCUDACH.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUDACH.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZURHLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-RHLD", DCCURHLD);
    }
    public void S000_CALL_DCZPFTCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-P-FINANCE-TR-CHK", DCCPFTCK);
    }
    public void S000_CALL_DDZUZFMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DDZUZFMM", DDCUZFMM);
    }
    public void S000_CALL_DCZITRSR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-TRS-OPR", DCCITRSR);
        CEP.TRC(SCCGWA, DCCITRSR.RC);
        if (DCCITRSR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCITRSR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
    }
    public void S000_CALL_DDZUADVT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-A-DETAIL-AC", DDCUADVT);
        if (DDCUADVT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCUADVT.RC);
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
    public void S000_CALL_DDZIDREG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SRC-DDZIDREG", DDCIDREG);
        if (DDCIDREG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIDREG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZMAGT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-AGT", CICMAGT);
    }
    public void S000_CALL_DCZIQHLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-DCZIQHLD", DCCIQHLD);
        if (DCCIQHLD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCIQHLD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCCPARM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZUQBFJ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-CHK-DDZUQBFJ", DDCUQBFJ);
    }
    public void S000_CALL_CIZMLMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-LMT", CICMLMT);
        if (CICMLMT.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICMLMT.RC);
        }
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACAC.RC);
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCUST.RC);
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        if (CICQACRI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACRI.RC);
        }
    }
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB);
        if (AICPQMIB.RC.RC_CODE != 0 
            && AICPQMIB.RC.RC_CODE != 8917 
            && AICPQMIB.RC.RC_CODE != 8924) {
            CEP.ERR(SCCGWA, AICPQMIB.RC);
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
    public void S000_CALL_FSZVMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "FS-I-INQ-VMST", FSCIVMST);
        if (FSCIVMST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, FSCIVMST.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_FSZVTXD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "FS-R-WRIT-VTXD", FSCSVTX);
        if (FSCSVTX.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, FSCSVTX.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
