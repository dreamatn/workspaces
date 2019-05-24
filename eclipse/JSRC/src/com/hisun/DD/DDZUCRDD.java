package com.hisun.DD;

import com.hisun.DC.*;
import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.FS.*;
import com.hisun.CI.*;
import com.hisun.EA.*;
import com.hisun.AI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZUCRDD {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    SCZEXIT_WS_STAR_COMM WS_STAR_COMM;
    DBParm DDTMST_RD;
    DBParm DDTVCH_RD;
    DBParm DDTCCY_RD;
    brParm DDTCCY_BR = new brParm();
    DBParm DDTGPMST_RD;
    DBParm DDTNOSI_RD;
    DBParm DDTINTB_RD;
    DBParm DDTMSTR_RD;
    DBParm DDTADMN_RD;
    String K_STS_TABLE_APP = "DD";
    String K_CUS_CR_STS_TBL_CAN = "0011";
    String K_CUS_CR_STS_TBL_P = "0041";
    String K_CUS_CR_STS_TBL_C = "0003";
    String K_CUS_CR_STS_TBL_LN = "0014";
    String K_CUS_CR_STS_TBL_LAW = "7580";
    String CPN_UNIT_DEP_PROC = "DD-UNIT-DEP-PROC ";
    String CPN_SCSSCKDT = "SCSSCKDT";
    String CPN_UNI_CIZACCU = "CI-INQ-ACCU";
    String CPN_I_INQ_BAL = "DD-I-INQ-CCY-BAL";
    String CPN_I_BACK_PROC = "DD-I-BACK-PROC   ";
    String CPN_I_PSBK_PROC = "DD-I-PSBK-PROC    ";
    String CPN_PROC_FHIS = "BP-PROC-FHIS";
    String CPN_VWA_CPNT = "BP-P-VWA-WRITE";
    String CPN_I_INQ_DDPRD = "DD-I-INQ-DDPRD";
    String CPN_FS_FCOST = "FS-I-CAPL-COST";
    String CPN_FS_FRESL = "FS-I-CAPL-RESL";
    String K_GJ_TX = "0581100";
    String CPN_I_CNY_TX_PROC = "DD-I-CNY-TX-PROC";
    String CPN_F_F_CAL_FEE = "BP-F-F-CAL-FEE      ";
    String CPN_F_F_TX_INFO = "BP-F-F-TX-INFO      ";
    String CPN_DCZUAINQ = "DC-U-CARD-AC-INQ";
    String CPN_BPZPRMR = "BP-PARM-READ";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String K_AP_MMO = "DD";
    String K_CASH_CR_AMT_TBL = "0001";
    String K_NORM_TR_AMT_TBL = "0003";
    String K_PC_TR_AMT_TBL = "0004";
    String K_OBJ_SYSTEM1 = "NFMS";
    String K_SERV_CODE1 = "CMA001";
    String K_OBJ_SYSTEM2 = "KHMS";
    String K_OBJ_SYSTEM3 = "QCGS";
    String K_SERV_CODE2 = "BAT004";
    String K_AGT_TYP = "141290001013";
    String K_AGT_FUT = "141290001018";
    String K_PROD_CODE = "NDP00117";
    String K_OBJ_SYSTEM4 = "ESBP";
    String K_SERV_CODE4 = "3008200000102";
    String K_SERV_CODE5 = "3002200000903";
    String WS_ERR_MSG = " ";
    double WS_AVL_BAL = 0;
    char WS_ADD_UPD_REC_FLG = ' ';
    String WS_VS_AC = " ";
    char WS_VS_AC_FLG = ' ';
    String WS_NOSTD_AC = " ";
    String WS_TR_AC = " ";
    char WS_NOSTD_AC_FLG = ' ';
    String WS_OTH_NOSTD_AC = " ";
    char WS_OTH_NOSTD_AC_FLG = ' ';
    char WS_INTB_REC_FLG = ' ';
    char WS_MST_AC_FLG = ' ';
    short WS_I = 0;
    short WS_IDX = 0;
    double WS_TOT_CAMT = 0;
    double WS_TOT_TAMT = 0;
    char WS_OTHER_CITYPE = ' ';
    char WS_AC_CITYPE = ' ';
    short WS_LENGTH = 0;
    char WS_TMP_ACATTR = ' ';
    int WS_TXN_BDATE_NO = 0;
    int WS_EXP_DATE = 0;
    String WS_UCRDD_AC = " ";
    String WS_UCRDD_OTHER_AC = " ";
    String WS_CARD_NO = " ";
    String WS_FEE_UCRDD_NO = " ";
    String WS_FEE_UCRDD_AC = " ";
    DDZUCRDD_WS_DATA_TO_NFMX WS_DATA_TO_NFMX = new DDZUCRDD_WS_DATA_TO_NFMX();
    double WS_CURR_BAL = 0;
    String WS_GROUP_AC = " ";
    char WS_GROUP_AC_ACTION = ' ';
    char WS_DATA_OVERFLOW_FLAG = ' ';
    String WS_RLT_AC_NAME = " ";
    char WS_BV_TYPE = ' ';
    double WS_DEP_CAMT = 0;
    double WS_LIMI_AMT = 0;
    String WS_BUY_CCY = " ";
    char WS_BUY_CCY_TYPE = ' ';
    double WS_BUY_AMT = 0;
    String WS_SELL_CCY = " ";
    char WS_SELL_CCY_TYPE = ' ';
    double WS_SELL_AMT = 0;
    String WS_ID_TYPE = " ";
    char WS_OT_BV_TYPE = ' ';
    String WS_AGT_TYP = " ";
    String WS_N_CARD_NO = " ";
    String WS_N_CARD_OTH = " ";
    String ACTY_OT_STD_AC = " ";
    DDZUCRDD_ACTY_OUTPUT ACTY_OUTPUT = new DDZUCRDD_ACTY_OUTPUT();
    int WS_BRANCH_BR = 0;
    String WS_REL_AC = " ";
    String WS_PROD_CD = " ";
    char WS_CROS_CR_FLG = ' ';
    char WS_REAL_VIR_FLG = ' ';
    char WS_VIR_MAIN_FLG = 'N';
    String WS_PAY_NAME = " ";
    double WS_O_CURR_BAL = 0;
    double WS_N_CURR_BAL = 0;
    double WS_TEMP_VAL = 0;
    DDZUCRDD_WS_DATA_TO_SCZTPCL WS_DATA_TO_SCZTPCL = new DDZUCRDD_WS_DATA_TO_SCZTPCL();
    short WS_DEC_MTH = 0;
    long WS_TRF_AMT = 0;
    String WS_SVS_ADC_NO = " ";
    String WS_AC_STS_WORD = " ";
    char WS_FROM_CITYP = ' ';
    char WS_NOTICE_FLG = ' ';
    char WS_CHECK_SVS_ADC_FLG = ' ';
    char WS_CHK_AC_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCSTAR SCCSTAR = new SCCSTAR();
    DDRFHIS DDRFHIS = new DDRFHIS();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDVMRAT DDVMRAT = new DDVMRAT();
    DDRMST DDRMST = new DDRMST();
    DDRMST DDRMSTOT = new DDRMST();
    DDRMST DDRFMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    DDRCCY DDRCCYM = new DDRCCY();
    BPCTCALF BPCTCALF = new BPCTCALF();
    DCCIQHLD DCCIQHLD = new DCCIQHLD();
    DDCUCRDD DDCUCRD1 = new DDCUCRDD();
    DCCUCINF DCCUCINO = new DCCUCINF();
    DDRVCH DDRVCH = new DDRVCH();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    DDCIPSBK DDCIPSBK = new DDCIPSBK();
    DDCIBACK DDCIBACK = new DDCIBACK();
    FSCFCOST FSCFCOST = new FSCFCOST();
    FSCFRESL FSCFRESL = new FSCFRESL();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    DPCPARMP DPCPARMP = new DPCPARMP();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    BPCCPRL BPCCPRL = new BPCCPRL();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    BPCOVAWR BPCOVAWR = new BPCOVAWR();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    CICACCU CICACCU = new CICACCU();
    CICACCU CICACCUOT = new CICACCU();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCCLDT SCCCLDT = new SCCCLDT();
    DCCPFTCK DCCPFTCK = new DCCPFTCK();
    DCCITRSR DCCITRSR = new DCCITRSR();
    DCCPACTY DCCPACTY = new DCCPACTY();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCPRMM BPCPRMM = new BPCPRMM();
    DDCPLIMT DDCPLIMT = new DDCPLIMT();
    DDCUADVT DDCUADVT = new DDCUADVT();
    SCCTPCL SCCTPCL = new SCCTPCL();
    DDCNKHMS DDCNKHMS = new DDCNKHMS();
    BPCPRGST BPCPRGST = new BPCPRGST();
    CICMAGT CICMAGT = new CICMAGT();
    DDRNOSI DDRNOSI = new DDRNOSI();
    DDRGPMST DDRGPMST = new DDRGPMST();
    DDRINTB DDRINTB = new DDRINTB();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    DCCUCINF DCCUCINF = new DCCUCINF();
    DDCIDREG DDCIDREG = new DDCIDREG();
    DDCUCBAL DDCUCBAL = new DDCUCBAL();
    BPCCGAC BPCCGAC = new BPCCGAC();
    DDRMSTR DDRMSTR = new DDRMSTR();
    CICMLMT CICMLMT = new CICMLMT();
    CICQACAC CICQACAC = new CICQACAC();
    CICCUST CICCUST = new CICCUST();
    CICCUST CICOCUST = new CICCUST();
    CICCUST CICKCUST = new CICCUST();
    CICQACRL CICQACRL = new CICQACRL();
    DDCUOPAC DDCUOPAC = new DDCUOPAC();
    DDCUOCAC DDCUOCAC = new DDCUOCAC();
    CICQACRI CICQACRI = new CICQACRI();
    CICQACRI CICOACRI = new CICQACRI();
    DDCPDZZH DDCPDZZH = new DDCPDZZH();
    EACUTRAN EACUTRAN = new EACUTRAN();
    BPCFX BPCFX = new BPCFX();
    AICPQMIB AICPQMIB = new AICPQMIB();
    DDCUHTOD DDCUHTOD = new DDCUHTOD();
    DDRADMN DDRADMN = new DDRADMN();
    CICCKLS CICCKLS = new CICCKLS();
    FSCIVMST FSCIVMST = new FSCIVMST();
    FSCSVTX FSCSVTX = new FSCSVTX();
    FSCBTE FSCBTE = new FSCBTE();
    CICREGLC CICREGLC = new CICREGLC();
    FSCUVSAT FSCUVSAT = new FSCUVSAT();
    CICMLRG CICMLRG = new CICMLRG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    SCCGWA SCCGWA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPCPORUP_DATA_INFO BPCPORUP;
    DDCUCRDD DDCUCRDD;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, DDCUCRDD DDCUCRDD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCUCRDD = DDCUCRDD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = DDCUCRDD.AC;
        DDRCCY.CCY = DDCUCRDD.CCY;
        DDRCCY.CCY_TYPE = DDCUCRDD.CCY_TYPE;
        T000_READ_DDTCCY();
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDCUCRDD.TRT_CTLW == null) DDCUCRDD.TRT_CTLW = "";
        JIBS_tmp_int = DDCUCRDD.TRT_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) DDCUCRDD.TRT_CTLW += " ";
        if (DDRCCY.STS_WORD.substring(63 - 1, 63 + 1 - 1).equalsIgnoreCase("1") 
            && !DDCUCRDD.TRT_CTLW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            R000_ACCUMULATE_PROC_CAN();
        }
        B000_MAIN_PROC();
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDCUCRDD.TRT_CTLW == null) DDCUCRDD.TRT_CTLW = "";
        JIBS_tmp_int = DDCUCRDD.TRT_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) DDCUCRDD.TRT_CTLW += " ";
        if (DDRCCY.STS_WORD.substring(63 - 1, 63 + 1 - 1).equalsIgnoreCase("1") 
            && !DDCUCRDD.TRT_CTLW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            R000_ACCUMULATE_PROC();
        }
        CEP.TRC(SCCGWA, "DDZUCRDD return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B015_CHECK_INPUT_DATA();
        B020_CHECK_CI_INF_PROC();
        B015_CHECK_CI_LIST();
        B040_CI_INF_PROC();
        B030_GET_AC_INF_PROC();
        B035_CHK_AC_STS();
        B075_GET_CI_CCY_INF_PROC();
        if (!(DDVMPRD.VAL.TD_FLG == '0' 
            && DDCUCRDD.SUPPLY_FLG == 'N')) {
            B050_CHECK_STS_TBL_PROC();
        }
        CEP.TRC(SCCGWA, DDRCCY.PROD_CODE);
        B070_GET_PRD_INF_PROC();
        if (DDCUCRDD.SIGN_FLG != 'Y') {
            B076_CARD_LIMT_BAL_CHK();
        }
        B093_CHECK_TXN_AMT_LIMIT();
        B080_CHECK_AVA_BAL_PROC();
        B085_UPD_BAL_PROC();
        B095_UPD_CCY_INF_PROC();
        B096_UPD_GRP_AC_CTL_BAL();
        B098_UPD_MST_INF_PROC();
        if (DDCUCRDD.SIGN_FLG != 'Y') {
            B076_CHECK_LMT_PROC();
        }
        if (WS_CHECK_SVS_ADC_FLG == 'Y') {
            B077_REG_FRGLMT_PROC();
        }
        if (WS_NOTICE_FLG == 'Y') {
            B078_ADP_NOTICE_PROC();
        }
        CEP.TRC(SCCGWA, DDRCCY.AC_TYPE);
        if (DDRCCY.AC_TYPE != '6') {
            B100_PRT_UNPT_ITEM();
        }
        B130_BACK_VALUE_PROC();
        B170_FIN_TXN_HIS_PROC();
        if (WS_REAL_VIR_FLG == 'Y') {
            B175_VIR_CURBAL_PROC();
        }
        if (WS_REAL_VIR_FLG == 'Y' 
            || WS_VIR_MAIN_FLG == 'Y') {
            B175_VIR_VTXD_PROC();
            B180_NOTICE_BTE_SYS_PROC();
        }
        if (DDCUCRDD.HIS_SHOW_FLG != 'N') {
            B190_GEN_VCH_PROC();
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, DDCUCRDD.BANK_CR_FLG);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            CEP.TRC(SCCGWA, DDVMPRD.VAL.TD_FLG);
            CEP.TRC(SCCGWA, DDCUCRDD.SUPPLY_FLG);
            if (DDVMPRD.VAL.TD_FLG == '0' 
                && DDCUCRDD.SUPPLY_FLG != 'N') {
                B210_DEP_TD_DRW_DD();
            }
        }
        CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
        CEP.TRC(SCCGWA, DDRCCY.CCAL_TOT_BAL);
    }
    public void B015_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUCRDD.AC);
        CEP.TRC(SCCGWA, DDCUCRDD.CCY);
        CEP.TRC(SCCGWA, DDCUCRDD.CCY_TYPE);
        CEP.TRC(SCCGWA, DDCUCRDD.CARD_NO);
        CEP.TRC(SCCGWA, DDCUCRDD.PSBK_NO);
        CEP.TRC(SCCGWA, DDCUCRDD.TX_AMT);
        CEP.TRC(SCCGWA, DDCUCRDD.VAL_DATE);
        CEP.TRC(SCCGWA, DDCUCRDD.OTHER_AC);
        CEP.TRC(SCCGWA, DDCUCRDD.OTHER_CCY);
        CEP.TRC(SCCGWA, DDCUCRDD.OTHER_AMT);
        CEP.TRC(SCCGWA, DDCUCRDD.NARRATIVE);
        CEP.TRC(SCCGWA, DDCUCRDD.TX_MMO);
        CEP.TRC(SCCGWA, DDCUCRDD.TX_TYPE);
        CEP.TRC(SCCGWA, DDCUCRDD.TX_REF);
        CEP.TRC(SCCGWA, DDCUCRDD.REMARKS);
        CEP.TRC(SCCGWA, DDCUCRDD.BANK_CR_FLG);
        CEP.TRC(SCCGWA, DDCUCRDD.TXN_REGION);
        if (DDCUCRDD.AC.trim().length() == 0 
            && DDCUCRDD.CARD_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT);
        }
        if (DDCUCRDD.CCY.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_M_INPUT);
        }
        if (DDCUCRDD.CCY.equalsIgnoreCase("156") 
            && DDCUCRDD.CCY_TYPE == ' ' 
            && DDCUCRDD.AID.trim().length() == 0) {
            DDCUCRDD.CCY_TYPE = '1';
        }
        if (DDCUCRDD.CCY_TYPE == ' ' 
            && DDCUCRDD.AID.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_TYPE_M_INPUT);
        }
        if (DDCUCRDD.TX_AMT == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AMT_M_INPUT);
        } else {
        }
        CEP.TRC(SCCGWA, DDCUCRDD.CCY);
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = DDCUCRDD.CCY;
        S000_CALL_BPZQCCY();
        WS_DEC_MTH = BPCQCCY.DATA.DEC_MTH;
        CEP.TRC(SCCGWA, BPCQCCY.DATA.DEC_MTH);
        CEP.TRC(SCCGWA, WS_DEC_MTH);
        if (WS_DEC_MTH == 0) {
            WS_TRF_AMT = (long) DDCUCRDD.TX_AMT;
            CEP.TRC(SCCGWA, DDCUCRDD.TX_AMT);
            CEP.TRC(SCCGWA, WS_TRF_AMT);
            if (DDCUCRDD.TX_AMT != WS_TRF_AMT) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AMT_NOT_DECIMAL;
                S000_ERR_MSG_PROC();
            }
        }
        if (DDCUCRDD.VAL_DATE != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = DDCUCRDD.VAL_DATE;
            SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
            SCSSCKDT1.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BAK_DT_INVALID;
                S000_ERR_MSG_PROC();
            }
        } else {
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                DDCUCRDD.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            } else {
                DDCUCRDD.VAL_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            }
        }
        CEP.TRC(SCCGWA, DDCUCRDD.VAL_DATE);
        if (DDCUCRDD.VAL_DATE > SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BAK_DT_INVALID;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, DDCUCRDD.OTH_TX_TOOL);
        CEP.TRC(SCCGWA, DDCUCRDD.CARD_NO);
        if (DDCUCRDD.CARD_NO.equalsIgnoreCase(DDCUCRDD.OTH_TX_TOOL) 
            && DDCUCRDD.CARD_NO.trim().length() > 0) {
            DDCUCRDD.BANK_CR_FLG = 'Y';
        }
        if (DDCUCRDD.BANK_CR_FLG == ' ') {
            DDCUCRDD.BANK_CR_FLG = 'N';
        } else {
            if ((DDCUCRDD.BANK_CR_FLG != 'Y') 
                && (DDCUCRDD.BANK_CR_FLG != 'N')) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BANK_CR_FLG_INVALID;
                S000_ERR_MSG_PROC();
            }
        }
        if (DDCUCRDD.TXN_REGION == ' ') {
            DDCUCRDD.TXN_REGION = '0';
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        CEP.TRC(SCCGWA, BPCPQORG.FX_BUSI);
        if (!DDCUCRDD.CCY.equalsIgnoreCase("156") 
            && (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")) 
            && BPCPQORG.FX_BUSI.equalsIgnoreCase("00")) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_TR_BR_NO_FX_AUTH);
        }
        CEP.TRC(SCCGWA, BPCPQORG.TRA_TYP);
        if ((!BPCPQORG.TRA_TYP.equalsIgnoreCase("00")) 
            && DDCUCRDD.TX_TYPE == 'C') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_FTA_BR_NO_CASH_TXN);
        }
        if (DDCUCRDD.TX_TYPE == 'C' 
            && DDCUCRDD.CCY_TYPE == '2') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_REMIT_AC_CANT_CASH;
            S000_ERR_MSG_PROC();
        }
    }
    public void B015_CHECK_CI_LIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCKLS);
        CICCKLS.DATA.AGR_NO = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        CEP.TRC(SCCGWA, CICCKLS.DATA.AGR_NO);
        CICCKLS.DATA.AP_TYPE = "100";
        CICCKLS.DATA.EXP_MMO = DDCUCRDD.TX_MMO;
        CICCKLS.DATA.ACAC_NO = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        S000_CALL_CIZCKLS();
    }
    public void B020_CHECK_CI_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.FUNC = 'I';
        if (DDCUCRDD.CARD_NO.trim().length() > 0 
            && DDCUCRDD.AC.trim().length() == 0) {
            CICQACRL.DATA.AC_NO = DDCUCRDD.CARD_NO;
        } else {
            CICQACRL.DATA.AC_NO = DDCUCRDD.AC;
        }
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = DDCUCRDD.AC;
        DDRCCY.CCY = DDCUCRDD.CCY;
        DDRCCY.CCY_TYPE = DDCUCRDD.CCY_TYPE;
        T000_READ_DDTCCY();
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            if (DDRCCY.AC_TYPE == '7') {
                WS_REAL_VIR_FLG = 'Y';
                CEP.TRC(SCCGWA, "DYQ USE VIRTUAL ACCOUNT ");
                IBS.init(SCCGWA, FSCIVMST);
                FSCIVMST.DATA.ACC_NO = DDRCCY.CUS_AC;
                S000_CALL_FSZVMST();
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
        CEP.TRC(SCCGWA, CICQACRL.RC.RC_CODE);
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_AC_REL);
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
        if (CICQACRL.RC.RC_CODE == 8054) {
            CEP.TRC(SCCGWA, "11111");
            WS_REL_AC = CICQACRL.DATA.AC_NO;
            if (DDCUCRDD.CARD_NO.trim().length() > 0 
                && DDCUCRDD.AC.trim().length() > 0 
                && !DDCUCRDD.AC.equalsIgnoreCase(DDCUCRDD.CARD_NO)) {
                CICQACRL.O_DATA.O_AC_REL = "04";
            }
        } else if ((CICQACRL.RC.RC_CODE == 0 
                && (CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("03") 
                || CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("02") 
                || CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("09") 
                || CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("13") 
                || CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("04")))) {
            CEP.TRC(SCCGWA, "22222");
            WS_REL_AC = CICQACRL.O_DATA.O_REL_AC_NO;
            if (CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("09") 
                && WS_REL_AC.equalsIgnoreCase(DDCUCRDD.OTHER_AC)) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_CAN_NOT_SAME);
            }
        } else if (CICQACRL.RC.RC_CODE == 0 
                && CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("01")) {
            CEP.TRC(SCCGWA, "33333");
            WS_REL_AC = CICQACRL.O_DATA.O_REL_AC_NO;
            R000_VSAC_CHECK_PROC();
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
            && !DDCUCRDD.CCY.equalsIgnoreCase("156")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_LNK_CARD_CHY_ONLY;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, WS_REL_AC);
        if (CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("04") 
            && WS_REL_AC.equalsIgnoreCase(DDCUCRDD.OTHER_AC)) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_CAN_NOT_SAME);
        }
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.CCY_ACAC = DDCUCRDD.CCY;
        CICQACAC.DATA.CR_FLG = DDCUCRDD.CCY_TYPE;
        if (DDCUCRDD.AID.equalsIgnoreCase("003")) {
            CICQACAC.DATA.AGR_NO = DDCUCRDD.AC;
        } else {
            CICQACAC.DATA.AGR_NO = WS_REL_AC;
        }
        CICQACAC.DATA.AID = DDCUCRDD.AID;
        S000_CALL_CIZQACAC();
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        IBS.init(SCCGWA, CICQACRI);
        IBS.init(SCCGWA, CICOACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = WS_REL_AC;
        S000_CALL_CIZQACRI();
        IBS.CLONE(SCCGWA, CICQACRI, CICOACRI);
        if (CICOACRI.O_DATA.O_SVS_ADC_NO.trim().length() > 0) {
            WS_SVS_ADC_NO = CICOACRI.O_DATA.O_SVS_ADC_NO;
            WS_CHECK_SVS_ADC_FLG = 'Y';
        }
        IBS.init(SCCGWA, CICQACRI);
        if (DDCUCRDD.TX_TYPE == 'T' 
            && DDCUCRDD.OTHER_AC.trim().length() > 0) {
            CEP.TRC(SCCGWA, DDCUCRDD.OTHER_AC);
            CICQACRI.FUNC = 'A';
            CICQACRI.DATA.AGR_NO = DDCUCRDD.OTHER_AC;
            S000_CALL_CIZQACRI();
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_CI_NO);
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        }
        IBS.init(SCCGWA, DCCUCINF);
        IBS.init(SCCGWA, DCCUCINO);
        if (WS_CHK_AC_FLG == '1') {
            DCCUCINF.CARD_NO = WS_TR_AC;
            S000_CALL_DCZUCINF();
            IBS.CLONE(SCCGWA, DCCUCINF, DCCUCINO);
            WS_PROD_CD = DCCUCINF.PROD_CD;
            CEP.TRC(SCCGWA, DCCUCINO.CARD_HLDR_CINO);
            IBS.init(SCCGWA, CICCUST);
            IBS.init(SCCGWA, CICKCUST);
            CICCUST.FUNC = 'C';
            CICCUST.DATA.CI_NO = DCCUCINO.CARD_HLDR_CINO;
            CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
            S000_CALL_CIZCUST();
            IBS.CLONE(SCCGWA, CICCUST, CICKCUST);
        }
        IBS.init(SCCGWA, DDRMST);
        IBS.init(SCCGWA, DDRFMST);
        if (WS_CHK_AC_FLG == '2') {
            DDRMST.KEY.CUS_AC = WS_TR_AC;
            CEP.TRC(SCCGWA, DDRMST.KEY.CUS_AC);
            T000_READ_DDTMST();
            IBS.CLONE(SCCGWA, DDRMST, DDRFMST);
        }
    }
    public void B030_GET_AC_INF_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR);
        IBS.init(SCCGWA, DDRMST);
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO);
            DDRMST.KEY.CUS_AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
            T000_READ_UPDATE_DDTMST();
            WS_TMP_ACATTR = DDRMST.AC_TYPE;
            WS_PROD_CD = DDRMST.PROD_CODE;
        }
        WS_AC_STS_WORD = DDRMST.AC_STS_WORD;
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDCUCRDD.TRT_CTLW == null) DDCUCRDD.TRT_CTLW = "";
        JIBS_tmp_int = DDCUCRDD.TRT_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) DDCUCRDD.TRT_CTLW += " ";
        if (DDRMST.AC_STS_WORD.substring(23 - 1, 23 + 1 - 1).equalsIgnoreCase("1") 
            && DDCUCRDD.CCY.equalsIgnoreCase("156") 
            && (DDCUCRDD.TXN_REGION == '0' 
            || DDCUCRDD.TX_TYPE == 'C') 
            && !DDCUCRDD.TRT_CTLW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_HMT_AC_FOR_IN);
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(49 - 1, 49 + 1 - 1).equalsIgnoreCase("1") 
            && (DDCUCRDD.TX_MMO.equalsIgnoreCase("GTV") 
            || DDCUCRDD.TX_MMO.equalsIgnoreCase("X15"))) {
            CEP.TRC(SCCGWA, DDCUCRDD.TX_MMO);
            WS_GROUP_AC_ACTION = 'D';
            WS_GROUP_AC = DDCUCRDD.AC;
        }
        CEP.TRC(SCCGWA, "XIANJIAN");
        CEP.TRC(SCCGWA, CICOCUST.O_DATA.O_CI_NO);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_NO);
        CEP.TRC(SCCGWA, DDRMST.AC_TYPE);
        CEP.TRC(SCCGWA, DDRMSTOT.AC_TYPE);
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDCUCRDD.TX_TYPE == 'T' 
            && DDRMST.AC_TYPE == 'B' 
            && !DDRMST.AC_STS_WORD.substring(23 - 1, 23 + 1 - 1).equalsIgnoreCase("1") 
            && DDCUCRDD.CCY.equalsIgnoreCase("156") 
            && (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI") 
            || (!CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI") 
            && CICOCUST.O_DATA.O_CI_NO.trim().length() > 0 
            && !CICCUST.O_DATA.O_CI_NO.equalsIgnoreCase(CICOCUST.O_DATA.O_CI_NO))) 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("MID")) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.SVAC_AC_ONL_TO_SVAC);
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
    public void B035_CHK_AC_STS() throws IOException,SQLException,Exception {
        if (DDRMST.FRG_IND.equalsIgnoreCase("OSA") 
            && DDCUCRDD.CCY_TYPE == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.OSA_AC_NOT_CASH);
        }
        if (DDRMST.AC_STS == 'C') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_CLOSE);
        }
        if (DDRMST.AC_STS == 'V') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_NOT_CHECKED);
        }
        if (DDRMST.AC_STS == 'D' 
            && SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_L_HANGED);
        }
        if (DDRMST.AC_STS == 'M') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.AC_CLOSED_AND_GET_INT);
        }
        CEP.TRC(SCCGWA, DDRMST.EXP_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (DDRMST.EXP_DATE > 0 
            && DDRMST.EXP_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_EXPIRED);
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, DDRMST.CROS_CR_FLG);
        if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10301")) {
            DDCUCRDD.BANK_CR_FLG = 'Y';
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (DDRMST.AC_TYPE == 'F' 
            && DDRMST.CI_TYP == '2' 
            && DDRMST.EXP_DATE > 0) {
            IBS.init(SCCGWA, SCCCLDT);
            if (SCCGWA.COMM_AREA.AC_DATE > DDRMST.EXP_DATE) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_TEMP_AC_IS_EXPIRED);
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
                }
                WS_EXP_DATE = SCCCLDT.DATE2;
                CEP.TRC(SCCGWA, WS_EXP_DATE);
                if (WS_EXP_DATE > DDRMST.EXP_DATE 
                    || WS_EXP_DATE == DDRMST.EXP_DATE) {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_TEMP_AC_EXPIRE_WARN, DDRMST.EXP_DATE);
                }
            }
        }
        CEP.TRC(SCCGWA, DDRMST.CASH_FLG);
        CEP.TRC(SCCGWA, DDCUCRDD.TX_TYPE);
        if ((DDRMST.CASH_FLG == '3' 
            || DDRMST.CASH_FLG == '4') 
            && DDCUCRDD.TX_TYPE == 'C') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CANT_CRD);
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(23 - 1, 23 + 1 - 1).equalsIgnoreCase("1") 
            && DDCUCRDD.CCY.equalsIgnoreCase("156") 
            && DDCUCRDD.TX_TYPE == 'C') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_HMT_AC_NO_CASH_CR);
        }
        if (DDRMST.FRG_IND == null) DDRMST.FRG_IND = "";
        JIBS_tmp_int = DDRMST.FRG_IND.length();
        for (int i=0;i<3-JIBS_tmp_int;i++) DDRMST.FRG_IND += " ";
        if (DDRMST.FRG_IND.substring(0, 2).equalsIgnoreCase("FT") 
            && DDCUCRDD.TX_TYPE == 'C') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_FTA_AC_NO_CASH_TXN);
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
            DCCUCINF.CARD_NO = DDCUCRDD.CARD_NO;
        }
        if (DCCUCINF.CARD_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, DCCUCINF.CARD_NO);
            S000_CALL_DCZUCINF();
            if (WS_CHK_AC_FLG != '1') {
                WS_PROD_CD = DCCUCINF.PROD_CD;
            }
            CEP.TRC(SCCGWA, DCCUCINF.CARD_STS);
            if (DCCUCINF.CARD_STS == 'C') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CARD_CLOSED);
            }
        } else {
            CEP.TRC(SCCGWA, DDRMST.CROS_CR_FLG);
            WS_CROS_CR_FLG = DDRMST.CROS_CR_FLG;
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
                WS_CROS_CR_FLG = '1';
            }
        }
    }
    public void B040_CI_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        IBS.init(SCCGWA, CICOCUST);
        CEP.TRC(SCCGWA, "XIANOTHERACCU");
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        if (DDCUCRDD.TX_TYPE == 'T' 
            && !CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI") 
            && DDCUCRDD.OTHER_AC.trim().length() > 0) {
            CEP.TRC(SCCGWA, "OTHER ACCU");
            CICCUST.FUNC = 'A';
            CICCUST.DATA.AGR_NO = DDCUCRDD.OTHER_AC;
            S000_CALL_CIZCUST();
            IBS.CLONE(SCCGWA, CICCUST, CICOCUST);
            WS_PAY_NAME = CICQACRI.O_DATA.O_AC_CNM;
        }
        IBS.init(SCCGWA, AICPQMIB);
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
            AICPQMIB.INPUT_DATA.AC = DDCUCRDD.OTHER_AC;
            S000_CALL_AIZPQMIB();
            WS_PAY_NAME = AICPQMIB.OUTPUT_DATA.CHS_NM;
        }
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'C';
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO);
        CICCUST.DATA.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        S000_CALL_CIZCUST();
        if (CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM.trim().length() == 0) {
            DDCUCRDD.CHI_NAME = CICCUST.O_DATA.O_CI_NM;
        } else {
            DDCUCRDD.CHI_NAME = CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM;
        }
        if (CICQACAC.O_DATA.O_ACR_DATA.O_AC_ENM.trim().length() == 0) {
            DDCUCRDD.ENG_NAME = CICCUST.O_DATA.O_CI_ENM;
        } else {
            DDCUCRDD.ENG_NAME = CICQACAC.O_DATA.O_ACR_DATA.O_AC_ENM;
        }
    }
    public void B050_CHECK_STS_TBL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFCSTS);
        if (DDCUCRDD.CARD_NO.trim().length() > 0) {
            BPCFCSTS.MSG_INFO = DDCUCRDD.CARD_NO;
        } else {
            BPCFCSTS.MSG_INFO = DDCUCRDD.AC;
        }
        CEP.TRC(SCCGWA, BPCFCSTS.MSG_INFO);
        BPCFCSTS.AP_MMO = K_STS_TABLE_APP;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_TYP);
            if (CICCUST.O_DATA.O_CI_TYP == '1') {
                BPCFCSTS.TBL_NO = K_CUS_CR_STS_TBL_P;
            } else {
                BPCFCSTS.TBL_NO = K_CUS_CR_STS_TBL_C;
            }
        } else {
            BPCFCSTS.TBL_NO = K_CUS_CR_STS_TBL_CAN;
        }
        if (DDCUCRDD.LAW_DUCT_FLG == 'Y') {
            BPCFCSTS.TBL_NO = K_CUS_CR_STS_TBL_LAW;
        }
        if (CICCUST.O_DATA.O_STSW == null) CICCUST.O_DATA.O_STSW = "";
        JIBS_tmp_int = CICCUST.O_DATA.O_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICCUST.O_DATA.O_STSW += " ";
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        BPCFCSTS.STATUS_WORD = CICCUST.O_DATA.O_STSW.substring(0, 80) + BPCFCSTS.STATUS_WORD.substring(99);
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 101 - 1) + DDRMST.AC_STS_WORD + BPCFCSTS.STATUS_WORD.substring(101 + 99 - 1);
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 201 - 1) + DDRCCY.STS_WORD + BPCFCSTS.STATUS_WORD.substring(201 + 99 - 1);
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DC")) {
            if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
            JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
            for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
            BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 101 - 1) + "000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000" + BPCFCSTS.STATUS_WORD.substring(101 + 99 - 1);
        }
        if (DDCUCRDD.DR_ONL_FLG == 'N') {
            if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
            JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
            for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
            BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 212 - 1) + "0" + BPCFCSTS.STATUS_WORD.substring(212 + 1 - 1);
        }
        CEP.TRC(SCCGWA, DDRMST.AC_STS_WORD);
        CEP.TRC(SCCGWA, BPCFCSTS.AP_MMO);
        CEP.TRC(SCCGWA, BPCFCSTS.TBL_NO);
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD);
        S000_CALL_BPZFCSTS();
        if (WS_CHK_AC_FLG == '1' 
            || WS_CHK_AC_FLG == '2') {
            B090_CHECK_CARD_HLDR_CINO_PROC();
        }
    }
    public void B090_CHECK_CARD_HLDR_CINO_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFCSTS);
        CEP.TRC(SCCGWA, CICKCUST.O_DATA.O_CI_TYP);
        CEP.TRC(SCCGWA, DDRFMST.CI_TYP);
        if (DDCUCRDD.CARD_NO.trim().length() > 0) {
            BPCFCSTS.MSG_INFO = DDCUCRDD.CARD_NO;
            WS_FROM_CITYP = CICKCUST.O_DATA.O_CI_TYP;
        } else {
            BPCFCSTS.MSG_INFO = DDCUCRDD.AC;
            WS_FROM_CITYP = DDRFMST.CI_TYP;
        }
        BPCFCSTS.AP_MMO = K_STS_TABLE_APP;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            CEP.TRC(SCCGWA, WS_FROM_CITYP);
            if (CICCUST.O_DATA.O_CI_TYP == '1') {
                BPCFCSTS.TBL_NO = K_CUS_CR_STS_TBL_P;
            } else {
                BPCFCSTS.TBL_NO = K_CUS_CR_STS_TBL_C;
            }
        } else {
            BPCFCSTS.TBL_NO = K_CUS_CR_STS_TBL_CAN;
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
    }
    public void B060_CHK_NAME() throws IOException,SQLException,Exception {
        if (DDCUCRDD.AC_NAME.trim().length() > 0) {
            if (!DDCUCRDD.AC_NAME.equalsIgnoreCase(DDCUCRDD.CHI_NAME) 
                && !DDCUCRDD.AC_NAME.equalsIgnoreCase(DDCUCRDD.ENG_NAME)) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_NAME_NOT_MATCH;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B070_GET_PRD_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIQPRD);
        DDCIQPRD.INPUT_DATA.PROD_CODE = DDRCCY.PROD_CODE;
        CEP.TRC(SCCGWA, DDRCCY.PROD_CODE);
        DDCIQPRD.INPUT_DATA.CCY = DDCUCRDD.CCY;
        CEP.TRC(SCCGWA, DDCIQPRD.INPUT_DATA.PROD_CODE);
        CEP.TRC(SCCGWA, DDCUCRDD.CCY);
        DDCIQPRD.DDVMPRD_PTR = DDVMPRD;
        DDCIQPRD.DDVMRAT_PTR = DDVMRAT;
        S000_CALL_DDZIQPRD();
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AP_MMO);
        CEP.TRC(SCCGWA, DDCIQPRD.OUTPUT_DATA.AC_TYPE);
        DPCPARMP.AC_TYPE = DDCIQPRD.OUTPUT_DATA.AC_TYPE;
    }
    public void B075_GET_CI_CCY_INF_PROC() throws IOException,SQLException,Exception {
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("TD")) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ALREADY_CR_HLD, CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO);
        }
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_STS);
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO.trim().length() == 0 
            || (CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO.trim().length() > 0 
            && CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_STS == '1')) {
            WS_ADD_UPD_REC_FLG = 'A';
            if (DDRMST.CCY_FLG == 'S') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_NOT_SUPP_INPUT_CCY);
            }
            if (DDRMST.CI_TYP != '2' 
                && DDRMST.CI_TYP != '3') {
                IBS.init(SCCGWA, DDRCCY);
                DDRCCY.CUS_AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
                T000_READ_DDTCCY_FIRST();
                if (DDCUCRDD.CCY.equalsIgnoreCase("156") 
                    && DDCUCRDD.CCY_TYPE == '2') {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CNY_NOT_ALLOW_REMIT;
                    S000_ERR_MSG_PROC();
                }
                B020_CRT_CCY_OPAC_PROC();
                CEP.TRC(SCCGWA, DDCUOPAC.ACAC);
                CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO = DDCUOPAC.ACAC;
            } else {
                CEP.TRC(SCCGWA, DDCUCRDD.CCY);
                CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO);
                if (CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO == null) CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO = "";
                JIBS_tmp_int = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO.length();
                for (int i=0;i<32-JIBS_tmp_int;i++) CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO += " ";
                if (!CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO.substring(0, 2).equalsIgnoreCase("FT") 
                    && DDCUCRDD.CCY.equalsIgnoreCase("156")) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CNNNOT_OPEN_CNY_AC;
                    S000_ERR_MSG_PROC();
                }
                B020_CRT_CCY_OCAC_PROC();
                CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO = DDCUOCAC.ACAC;
            }
        }
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READ_UPDATE_DDTCCY();
        WS_O_CURR_BAL = DDRCCY.CURR_BAL;
        CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
        CEP.TRC(SCCGWA, WS_O_CURR_BAL);
        CEP.TRC(SCCGWA, DDRCCY.AC_TYPE);
        if ((DDRCCY.AC_TYPE == 'B' 
            || DDRCCY.AC_TYPE == 'C' 
            || DDRCCY.AC_TYPE == '4' 
            || DDRCCY.AC_TYPE == '5') 
            && DDCUCRDD.TX_TYPE == 'C') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CANT_CRD);
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        CEP.TRC(SCCGWA, BPCPQORG.ATTR);
        CEP.TRC(SCCGWA, WS_CROS_CR_FLG);
        CEP.TRC(SCCGWA, DDRMST.OWNER_BR);
        CEP.TRC(SCCGWA, BPCPQORG.BBR);
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDCUCRDD.BANK_CR_FLG == 'N' 
            && (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")) 
            && WS_CROS_CR_FLG == '2' 
            && !DDRMST.AC_STS_WORD.substring(21 - 1, 21 + 1 - 1).equalsIgnoreCase("1") 
            && DDRMST.OWNER_BR != BPCPQORG.BBR) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_NOT_CR_AT_ALL);
        }
        if ((DDRCCY.AC_TYPE == '4' 
            || DDRCCY.AC_TYPE == '5') 
            && DDCUCRDD.EA_CHK_FLG != 'N') {
            B075_GET_DZZH_PARM_PROC();
            B075_DZZH_CTL_PROC();
        }
    }
    public void B075_GET_DZZH_PARM_PROC() throws IOException,SQLException,Exception {
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
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCPDZZH.DATA_TXT);
        CEP.TRC(SCCGWA, DDCPDZZH.DATA_TXT.II_BAL);
        CEP.TRC(SCCGWA, DDCPDZZH.DATA_TXT.II_LMT_AMT);
        CEP.TRC(SCCGWA, DDCPDZZH.DATA_TXT.III_BAL);
        CEP.TRC(SCCGWA, DDCPDZZH.DATA_TXT.III_LMT_AMT);
    }
    public void B075_DZZH_CTL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EACUTRAN);
        EACUTRAN.FUNC = 'C';
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
        if (DDCUCRDD.OTHER_AC.trim().length() > 0) {
            EACUTRAN.OPP_AC = DDCUCRDD.OTHER_AC;
            EACUTRAN.OPP_AC_NME = CICQACRI.O_DATA.O_AC_CNM;
        } else {
            EACUTRAN.OPP_AC = DDCUCRDD.RLT_AC;
            EACUTRAN.OPP_AC_NME = DDCUCRDD.RLT_AC_NAME;
        }
        CEP.TRC(SCCGWA, EACUTRAN.OPP_AC);
        CEP.TRC(SCCGWA, EACUTRAN.OPP_AC_NME);
        EACUTRAN.MMO = DDCUCRDD.TX_MMO;
        EACUTRAN.OPP_BNK = DDCUCRDD.OTHER_BR;
        EACUTRAN.OPP_BNK_NME = DDCUCRDD.OTHER_BK_NM;
        EACUTRAN.RMK = DDCUCRDD.REMARKS;
    }
    public void B076_CARD_LIMT_BAL_CHK() throws IOException,SQLException,Exception {
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
            DCCPFTCK.VAL.CARD_NO = DDCUCRDD.CARD_NO;
        }
        CEP.TRC(SCCGWA, DCCPFTCK.VAL.CARD_NO);
        CEP.TRC(SCCGWA, DDCUCRDD.CHK_LMT_FLG);
        if (DCCPFTCK.VAL.CARD_NO.trim().length() > 0) {
            if ((DDRCCY.AC_TYPE == 'A' 
                || DDRCCY.AC_TYPE == 'B' 
                || DDRCCY.AC_TYPE == '4' 
                || DDRCCY.AC_TYPE == '5' 
                || DDRCCY.AC_TYPE == 'C' 
                || DDRCCY.AC_TYPE == '2' 
                || DDRCCY.AC_TYPE == '1') 
                && DDCUCRDD.CHK_LMT_FLG != '4') {
                DCCPFTCK.FUNCTION_CODE = 'B';
            } else {
                DCCPFTCK.FUNCTION_CODE = 'S';
            }
            DCCPFTCK.FR_AC_NO = DDCUCRDD.OTHER_AC;
            if (DDCUCRDD.RLT_AC.trim().length() > 0) {
                DCCPFTCK.TO_AC_NO = DDCUCRDD.RLT_AC;
            }
            DCCPFTCK.VAL.REGN_TYP = '0';
            DCCPFTCK.VAL.TXN_TYPE = "02";
            DCCPFTCK.VAL.TXN_CCY = DDCUCRDD.CCY;
            DCCPFTCK.VAL.TXN_AMT = DDCUCRDD.TX_AMT;
            DCCPFTCK.TXN_MMO = DDCUCRDD.TX_MMO;
            CEP.TRC(SCCGWA, DCCPFTCK.TXN_MMO);
            S000_CALL_DCZPFTCK();
        }
    }
    public void B076_CHECK_LMT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICREGLC);
        CICREGLC.FUNC = '0';
        if (DDCUCRDD.CARD_NO.trim().length() > 0) {
            CICREGLC.CUS_AC = DDCUCRDD.CARD_NO;
        } else {
            CICREGLC.CUS_AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        }
        CICREGLC.CARD_LNK_TYP = DCCUCINF.CARD_LNK_TYP;
        CICREGLC.MAIN_CARD_NO = DCCUCINO.PRIM_CARD_NO;
        CICREGLC.ACO_AC = DDRCCY.KEY.AC;
        CICREGLC.DC_FLG = 'C';
        CICREGLC.CCY = DDRCCY.CCY;
        CICREGLC.CCY_TYP = DDRCCY.CCY_TYPE;
        CICREGLC.AMT = DDCUCRDD.TX_AMT;
        CICREGLC.BAL = DDRCCY.CURR_BAL;
        if (DDCUCRDD.TX_TYPE == 'T') {
            CICREGLC.TRS_FLG = '1';
        }
        if (DDCUCRDD.TX_TYPE == 'C') {
            CICREGLC.TRS_FLG = '0';
        }
        CICREGLC.OPP_CUS_AC = DDCUCRDD.OTHER_AC;
        CICREGLC.RLT_CUS_AC = DDCUCRDD.RLT_AC;
        CICREGLC.CI_NO = CICCUST.O_DATA.O_CI_NO;
        CICREGLC.CI_TYP = CICCUST.O_DATA.O_CI_TYP;
        CICREGLC.OPP_CI_NO = CICOCUST.O_DATA.O_CI_NO;
        CICREGLC.PROD_CD = WS_PROD_CD;
        CICREGLC.CITY_FLG = DDCUCRDD.TXN_REGION;
        if (CICREGLC.CITY_FLG == ' ') {
            CICREGLC.CITY_FLG = '0';
        }
        CICREGLC.AC_TYP = DCCUCINF.AC_TYP;
        CICREGLC.AC_BR = DDRCCY.OWNER_BRDP;
        if (CICCUST.O_DATA.O_STSW == null) CICCUST.O_DATA.O_STSW = "";
        JIBS_tmp_int = CICCUST.O_DATA.O_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICCUST.O_DATA.O_STSW += " ";
        CICREGLC.HS_FLG = CICCUST.O_DATA.O_STSW.substring(27 - 1, 27 + 1 - 1).charAt(0);
        CICREGLC.TX_TYP = "" + DDCUCRDD.TX_TYPE;
        JIBS_tmp_int = CICREGLC.TX_TYP.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) CICREGLC.TX_TYP = "0" + CICREGLC.TX_TYP;
        CICREGLC.MMO = DDCUCRDD.TX_MMO;
        CICREGLC.TYPE = "02";
        CICREGLC.OTH_BK = DDCUCRDD.RLT_BANK;
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
        CICMLRG.DATA.TXN_CCY = DDCUCRDD.CCY;
        CICMLRG.DATA.TXN_AMT = DDCUCRDD.TX_AMT;
        CICMLRG.DATA.DC_FLG = 'C';
        S000_CALL_CIZMLRG();
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
        CEP.TRC(SCCGWA, DDRADMN.KEY.ADP_NO);
        IBS.init(SCCGWA, SCCTPCL);
        SCCTPCL.SERV_AREA.OBJ_SYSTEM = K_OBJ_SYSTEM4;
        SCCTPCL.SERV_AREA.SERV_CODE = K_SERV_CODE5;
        SCCTPCL.SERV_AREA.SERV_TYPE = ' ';
        WS_DATA_TO_SCZTPCL.WS_ADP_NO = DDRADMN.KEY.ADP_NO;
        WS_DATA_TO_SCZTPCL.WS_CI_NO = CICCUST.O_DATA.O_CI_NO;
        WS_DATA_TO_SCZTPCL.WS_AGR_NO = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        WS_DATA_TO_SCZTPCL.WS_CCY = DDCUCRDD.CCY;
        WS_DATA_TO_SCZTPCL.WS_CCY_TYPE = DDCUCRDD.CCY_TYPE;
        if (WS_N_CURR_BAL < 0) {
            WS_DATA_TO_SCZTPCL.WS_ADP_BAL = DDRADMN.OD_AMT + WS_N_CURR_BAL;
        } else {
            WS_DATA_TO_SCZTPCL.WS_ADP_BAL = DDRADMN.OD_AMT;
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            WS_DATA_TO_SCZTPCL.WS_NOTI_TYP = "020";
            if (WS_N_CURR_BAL > 0) {
                WS_DATA_TO_SCZTPCL.WS_TXN_BAL = - WS_O_CURR_BAL;
            } else {
                WS_DATA_TO_SCZTPCL.WS_TXN_BAL = DDCUCRDD.TX_AMT;
            }
        } else {
            WS_DATA_TO_SCZTPCL.WS_NOTI_TYP = "010";
            if (WS_O_CURR_BAL > 0) {
                WS_DATA_TO_SCZTPCL.WS_TXN_BAL = - WS_N_CURR_BAL;
            } else {
                WS_DATA_TO_SCZTPCL.WS_TXN_BAL = DDCUCRDD.TX_AMT;
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
        }
    }
    public void B093_CHECK_TXN_AMT_LIMIT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, DDCPLIMT);
        BPCPRMR.DAT_PTR = BPRPRMT;
        DDCPLIMT.KEY.TYP = "PDD08";
        if (DDRMST.CI_TYP == '1' 
            && DDCUCRDD.TX_TYPE == 'C') {
            DDCPLIMT.KEY.CD = "004";
            B055_10_GET_TXN_AMT_LIMIT();
        }
        if (!DDCUCRDD.CCY.equalsIgnoreCase("156") 
            && CICCUST.O_DATA.O_CI_TYP == '1' 
            && DDCUCRDD.TX_TYPE == 'C' 
            && DDCUCRDD.TX_AMT != 0) {
            IBS.init(SCCGWA, CICMLMT);
            CICMLMT.FUNC = 'A';
            CICMLMT.DATA.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
            CICMLMT.DATA.LMT_TP = "01";
            CICMLMT.DATA.CMT = DDCUCRDD.TX_AMT;
            CICMLMT.DATA.CCY = DDCUCRDD.CCY;
            S000_CALL_CIZMLMT();
        }
    }
    public void B055_10_GET_TXN_AMT_LIMIT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCPLIMT.KEY.CD);
        CEP.TRC(SCCGWA, DDCPLIMT.KEY.TYP);
        BPRPRMT.KEY.TYP = DDCPLIMT.KEY.TYP;
        BPRPRMT.KEY.CD = DDCPLIMT.KEY.CD;
        S000_CALL_BPZPRMR();
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
        }
    }
    public void B055_30_CHK_TXN_AMT_LIMIT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUCRDD.TX_AMT);
        CEP.TRC(SCCGWA, DDCPLIMT.KEY.TYP);
        CEP.TRC(SCCGWA, DDCPLIMT.KEY.CD);
        CEP.TRC(SCCGWA, DDCPLIMT.DATA_TXT);
        CEP.TRC(SCCGWA, DDCPLIMT.DATA_TXT.LOCAL_CURRENCY_LMT);
        CEP.TRC(SCCGWA, WS_ID_TYPE);
        if (DDCPLIMT.KEY.CD.equalsIgnoreCase("004")) {
            WS_LIMI_AMT = DDRCCY.DEP_CAMT + DDCUCRDD.TX_AMT;
            CEP.TRC(SCCGWA, WS_LIMI_AMT);
        }
        CEP.TRC(SCCGWA, WS_LIMI_AMT);
        CEP.TRC(SCCGWA, DDCPLIMT.DATA_TXT.LOCAL_CURRENCY_LMT);
        CEP.TRC(SCCGWA, DDCUCRDD.CCY);
        if (DDCUCRDD.CCY.equalsIgnoreCase("840")) {
            if (WS_LIMI_AMT >= DDCPLIMT.DATA_TXT.FOREIGN_CURRENCY_LMT 
                && DDCPLIMT.KEY.CD.equalsIgnoreCase("004")) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.FOREIGN_CUY_LMT;
                S000_ERR_MSG_PROC();
            }
        }
        if (!DDCUCRDD.CCY.equalsIgnoreCase("156") 
            && !DDCUCRDD.CCY.equalsIgnoreCase("840")) {
            WS_BUY_CCY = DDCUCRDD.CCY;
            WS_BUY_AMT = WS_LIMI_AMT;
            WS_SELL_CCY = "840";
            B560_AMT_EX_PROC();
            if (WS_SELL_AMT >= DDCPLIMT.DATA_TXT.FOREIGN_CURRENCY_LMT 
                && DDCPLIMT.KEY.CD.equalsIgnoreCase("004")) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.FOREIGN_CUY_LMT;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B560_AMT_EX_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_BUY_CCY);
        CEP.TRC(SCCGWA, WS_BUY_AMT);
        CEP.TRC(SCCGWA, WS_SELL_CCY);
        IBS.init(SCCGWA, BPCFX);
        BPCFX.FUNC = '3';
        BPCFX.CHNL = SCCGWA.COMM_AREA.CHNL;
        BPCFX.EXR_TYPE = BPCRBANK.EX_RA;
        BPCFX.BUY_CCY = DDCUCRDD.CCY;
        BPCFX.BUY_AMT = WS_BUY_AMT;
        BPCFX.SELL_CCY = WS_SELL_CCY;
        S000_LINK_BPZSFX();
        WS_SELL_AMT = BPCFX.SELL_AMT;
        CEP.TRC(SCCGWA, WS_SELL_AMT);
    }
    public void B080_CHECK_AVA_BAL_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
            CEP.TRC(SCCGWA, DDRCCY.HOLD_BAL);
            CEP.TRC(SCCGWA, DDRCCY.CCAL_TOT_BAL);
            CEP.TRC(SCCGWA, DDRCCY.MARGIN_BAL);
            WS_AVL_BAL = DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL + DDRCCY.CCAL_TOT_BAL - DDRCCY.MARGIN_BAL;
            CEP.TRC(SCCGWA, WS_AVL_BAL);
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(42 - 1, 42 + 1 - 1).equalsIgnoreCase("1") 
                && WS_AVL_BAL < DDCUCRDD.TX_AMT) {
                IBS.init(SCCGWA, DDRADMN);
                DDRADMN.KEY.AC = DDCUCRDD.ACAC;
                DDRADMN.ADP_STRDATE = SCCGWA.COMM_AREA.AC_DATE;
                if (CICCUST.O_DATA.O_CI_TYP == '2') {
                    DDRADMN.KEY.ADP_TYPE = "08";
                }
                if (CICCUST.O_DATA.O_CI_TYP == '3') {
                    DDRADMN.KEY.ADP_TYPE = "10";
                }
                DDRADMN.ADP_STS = 'O';
                T000_READ_DDTADMN();
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
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (WS_AVL_BAL < DDCUCRDD.TX_AMT 
                && !DDRMST.AC_STS_WORD.substring(61 - 1, 61 + 1 - 1).equalsIgnoreCase("1") 
                && !DDRMST.AC_STS_WORD.substring(62 - 1, 62 + 1 - 1).equalsIgnoreCase("1")) {
                if (WS_AVL_BAL < DDCUCRDD.TX_AMT) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AVL_BAL_NOT_ENOUGH;
                    S000_ERR_MSG_PROC();
                }
            }
            CEP.TRC(SCCGWA, DDVMPRD.VAL.TD_FLG);
            CEP.TRC(SCCGWA, DDCUCRDD.SUPPLY_FLG);
            if (DDVMPRD.VAL.TD_FLG == '0' 
                && DDCUCRDD.SUPPLY_FLG != 'N') {
                B210_DEP_TD_DRW_DD();
                T000_READ_UPDATE_DDTCCY();
            }
        }
    }
    public void B090_CHECK_FRG_LOAN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            CEP.TRC(SCCGWA, DDRMST.PROD_CODE);
            if (DDRMST.PROD_CODE.equalsIgnoreCase(K_PROD_CODE)) {
                IBS.init(SCCGWA, DDCUCBAL);
                DDCUCBAL.AC = DDCUCRDD.AC;
                DDCUCBAL.PROD_CODE = K_PROD_CODE;
                DDCUCBAL.AC_AMT = DDRCCY.CURR_BAL;
                S000_CALL_DDZUCBAL();
            }
        }
    }
    public void B085_UPD_BAL_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRCCY.STS);
        if (DDRCCY.STS == 'C') {
            DDRCCY.STS = 'N';
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 11 - 1) + "0" + DDRCCY.STS_WORD.substring(11 + 1 - 1);
            DDRCCY.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRCCY.LAST_BAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            IBS.init(SCCGWA, DDRINTB);
            WS_INTB_REC_FLG = 'N';
            DDRINTB.KEY.AC = DDRCCY.KEY.AC;
            T000_READ_UPDATE_DDTINTB();
            if (WS_INTB_REC_FLG == 'Y') {
                DDRINTB.STRT_TOT_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DDRINTB.END_TOT_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DDRINTB.LST_TOT_DATE = SCCGWA.COMM_AREA.AC_DATE;
                T000_REWRITE_DDTINTB();
            }
        }
        if (SCCGWA.COMM_AREA.AC_DATE != DDRCCY.LAST_BAL_DATE) {
            if (SCCGWA.COMM_AREA.AC_DATE > DDRCCY.LAST_BAL_DATE) {
                DDRCCY.LAST_BAL = DDRCCY.CURR_BAL;
                DDRCCY.LAST_BAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            } else {
                if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                    DDRCCY.LAST_BAL += DDCUCRDD.TX_AMT;
                } else {
                    DDRCCY.LAST_BAL -= DDCUCRDD.TX_AMT;
                }
            }
        }
        CEP.TRC(SCCGWA, "111");
        CEP.TRC(SCCGWA, DDRCCY.LAST_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (DDRCCY.LAST_DATE < SCCGWA.COMM_AREA.AC_DATE) {
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
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            DDRCCY.CURR_BAL = DDCUCRDD.TX_AMT + DDRCCY.CURR_BAL;
            if (WS_DATA_OVERFLOW_FLAG == 'Y') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_DATA_OVERFLOW);
            }
            CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
            if (DDCUCRDD.TX_TYPE == 'C') {
                DDRCCY.DEP_CAMT += DDCUCRDD.TX_AMT;
                WS_TOT_CAMT = DDRCCY.DEP_CAMT;
            } else {
                if (DDCUCRDD.TX_TYPE == 'T') {
                    DDRCCY.DEP_TAMT += DDCUCRDD.TX_AMT;
                    WS_TOT_TAMT = DDRCCY.DEP_TAMT;
                }
            }
            DDRCCY.DAY_TOT_CR_AMT += DDCUCRDD.TX_AMT;
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (WS_AC_STS_WORD == null) WS_AC_STS_WORD = "";
            JIBS_tmp_int = WS_AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) WS_AC_STS_WORD += " ";
            JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            if (DDRCCY.STS_WORD.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
                && !WS_AC_STS_WORD.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1") 
                && !JIBS_tmp_str[1].equalsIgnoreCase("117580")) {
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 4 - 1) + "0" + DDRCCY.STS_WORD.substring(4 + 1 - 1);
                IBS.init(SCCGWA, DDCIDREG);
                DDCIDREG.OPT = 'T';
                DDCIDREG.DATA.KEY.AC = DDRCCY.CUS_AC;
                CEP.TRC(SCCGWA, DDCIDREG.DATA.KEY.AC);
                DDCIDREG.DATA.KEY.CCY = DDCUCRDD.CCY;
                DDCIDREG.DATA.KEY.CCY_TYPE = DDCUCRDD.CCY_TYPE;
                S000_CALL_DDZIDREG();
            }
        } else {
            DDRCCY.CURR_BAL -= DDCUCRDD.TX_AMT;
            CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
            if (DDCUCRDD.TX_TYPE == 'C') {
                DDRCCY.DEP_CAMT -= DDCUCRDD.TX_AMT;
            } else {
                if (DDCUCRDD.TX_TYPE == 'T') {
                    DDRCCY.DEP_TAMT -= DDCUCRDD.TX_AMT;
                }
            }
            if (SCCGWA.COMM_AREA.AC_DATE == DDCUCRDD.VAL_DATE) {
                DDRCCY.DAY_TOT_CR_AMT -= DDCUCRDD.TX_AMT;
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
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_ALREADY_OD);
        }
        if (DDVMPRD.VAL.TD_FLG == '0') {
            if (DDCUCRDD.SUPPLY_FLG != 'N') {
                if (SCCGWA.COMM_AREA.CANCEL_IND == ' ') {
                    CEP.TRC(SCCGWA, "ADD");
                    DDRCCY.CCAL_TOT_BAL = DDRCCY.CCAL_TOT_BAL + DDCUCRDD.TX_AMT;
                } else {
                    CEP.TRC(SCCGWA, "REVERSE ADD");
                    DDRCCY.CCAL_TOT_BAL = DDRCCY.CCAL_TOT_BAL - DDCUCRDD.TX_AMT;
                }
            } else {
                if (SCCGWA.COMM_AREA.CANCEL_IND == ' ') {
                    CEP.TRC(SCCGWA, "SUPPLY");
                    DDRCCY.CCAL_TOT_BAL = DDRCCY.CCAL_TOT_BAL - DDCUCRDD.TX_AMT;
                } else {
                    CEP.TRC(SCCGWA, "REVERSE SUPPLY");
                    DDRCCY.CCAL_TOT_BAL = DDRCCY.CCAL_TOT_BAL + DDCUCRDD.TX_AMT;
                }
            }
        }
        CEP.TRC(SCCGWA, DDRCCY.CCAL_TOT_BAL);
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
    public void R000_VSAC_CHECK_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUADVT);
        DDCUADVT.VS_AC = CICQACRL.DATA.AC_NO;
        CEP.TRC(SCCGWA, DDCUADVT.VS_AC);
        DDCUADVT.CCY = DDCUCRDD.CCY;
        DDCUADVT.CCY_TYP = DDCUCRDD.CCY_TYPE;
        DDCUADVT.VALUE_DT = SCCGWA.COMM_AREA.AC_DATE;
        DDCUADVT.TNJNL = SCCGWA.COMM_AREA.JRN_NO;
        DDCUADVT.TRAN_AMT = DDCUCRDD.TX_AMT;
        DDCUADVT.DD_AC = WS_REL_AC;
        DDCUADVT.DR_CR_F = 'C';
        DDCUADVT.UPD_FHIS = 'Y';
        DDCUADVT.OTH_AC = DDCUCRDD.OTHER_AC;
        CEP.TRC(SCCGWA, DDCUADVT.OTH_AC);
        DDCUADVT.RLT_AC = DDCUCRDD.RLT_AC;
        DDCUADVT.RLT_AC_NAME = DDCUCRDD.RLT_AC_NAME;
        if (DDCUCRDD.RLT_BANK.trim().length() == 0) DDCUADVT.RLT_BANK = 0;
        else DDCUADVT.RLT_BANK = Long.parseLong(DDCUCRDD.RLT_BANK);
        DDCUADVT.RLT_BA_NM = DDCUCRDD.RLT_BK_NM;
        CEP.TRC(SCCGWA, DDCUADVT.RLT_AC);
        DDCUADVT.TX_TYPE = DDCUCRDD.TX_TYPE;
        DDCUADVT.TX_MMO = DDCUCRDD.TX_MMO;
        CEP.TRC(SCCGWA, DDCUADVT.TX_MMO);
        S000_CALL_DDZUADVT();
    }
    public void B095_UPD_CCY_INF_PROC() throws IOException,SQLException,Exception {
        DDRCCY.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.LAST_FN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DDTCCY();
    }
    public void B096_UPD_GRP_AC_CTL_BAL() throws IOException,SQLException,Exception {
        if (WS_GROUP_AC_ACTION == 'D') {
            CEP.TRC(SCCGWA, "GROUP NET ACCOUNT --> UPDATE CONTROL BALANCE!");
            IBS.init(SCCGWA, DDRGPMST);
            DDRGPMST.KEY.AC_NO = WS_GROUP_AC;
            DDRGPMST.KEY.CCY = "156";
            CEP.TRC(SCCGWA, DDRGPMST.KEY.AC_NO);
            T000_READ_UPDATE_DDTGPMST();
            CEP.TRC(SCCGWA, DDRGPMST.CTRL_BAL);
            CEP.TRC(SCCGWA, DDCUCRDD.TX_AMT);
            DDRGPMST.CTRL_BAL = DDRGPMST.CTRL_BAL - DDCUCRDD.TX_AMT;
            CEP.TRC(SCCGWA, DDRGPMST.CTRL_BAL);
            T000_REWRITE_DDTGPMST();
        }
    }
    public void B098_UPD_MST_INF_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRMST.AC_STS);
        if (DDRMST.AC_STS == 'W') {
            DDRMST.AC_STS = 'N';
            IBS.init(SCCGWA, DDCIDREG);
            DDCIDREG.OPT = 'X';
            DDCIDREG.DATA.KEY.AC = DDRMST.KEY.CUS_AC;
            DDCIDREG.DATA.KEY.CCY = DDCUCRDD.CCY;
            DDCIDREG.DATA.KEY.CCY_TYPE = DDCUCRDD.CCY_TYPE;
            S000_CALL_DDZIDREG();
        }
        if (WS_AC_STS_WORD == null) WS_AC_STS_WORD = "";
        JIBS_tmp_int = WS_AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) WS_AC_STS_WORD += " ";
        if (WS_AC_STS_WORD.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1")) {
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 5 - 1) + "0" + DDRMST.AC_STS_WORD.substring(5 + 1 - 1);
            C000_DORM_AC_PROC();
        }
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
            DDRMST.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRMST.LAST_FN_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRMST.LAST_TLR = SCCGWA.COMM_AREA.TL_ID;
            DDRMST.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRMST.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DDTMST();
        }
    }
    public void C000_DORM_AC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIDREG);
        DDCIDREG.OPT = 'T';
        DDCIDREG.DATA.KEY.AC = DDRMST.KEY.CUS_AC;
        CEP.TRC(SCCGWA, DDCIDREG.DATA.KEY.AC);
        S000_CALL_DDZIDREG();
        IBS.init(SCCGWA, DDRCCYM);
        DDRCCYM.CUS_AC = DDRMST.KEY.CUS_AC;
        T000_STARTBR_DDTCCY_D();
        T000_READNXT_DDTCCY_D();
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            if (DDRCCYM.STS_WORD == null) DDRCCYM.STS_WORD = "";
            JIBS_tmp_int = DDRCCYM.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCYM.STS_WORD += " ";
            if (DDRCCYM.STS_WORD.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                if (DDRCCYM.STS_WORD == null) DDRCCYM.STS_WORD = "";
                JIBS_tmp_int = DDRCCYM.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCYM.STS_WORD += " ";
                DDRCCYM.STS_WORD = DDRCCYM.STS_WORD.substring(0, 4 - 1) + "0" + DDRCCYM.STS_WORD.substring(4 + 1 - 1);
                T000_REWRITE_DDTCCY_D();
            }
            T000_READNXT_DDTCCY_D();
        }
        T000_ENDBR_DDTCCY_D();
    }
    public void B100_PRT_UNPT_ITEM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRVCH);
        DDRVCH.KEY.CUS_AC = DDCUCRDD.AC;
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
        CEP.TRC(SCCGWA, DDRVCH.PSBK_NO);
        CEP.TRC(SCCGWA, DDRVCH.PSBK_STS);
        if (DDRVCH.PSBK_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_VCH_IS_CANCEL;
            S000_ERR_MSG_PROC();
        }
        if (DDRVCH.PSBK_SEQ != 0 
            && DDCUCRDD.PSBK_SEQ != 0 
            && DDRVCH.PSBK_SEQ != DDCUCRDD.PSBK_SEQ) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_OLD_VCH_NOT_DR;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, DDCIPSBK);
        if (DDRVCH.VCH_TYPE == '1' 
            || DDRVCH.VCH_TYPE == '2') {
            DDCIPSBK.FUNC = 'T';
            DDCIPSBK.AC = DDRVCH.KEY.CUS_AC;
            DDCIPSBK.UPT_CCY = DDCUCRDD.CCY;
            DDCIPSBK.UPT_CCY_TYPE = DDCUCRDD.CCY_TYPE;
            DDCIPSBK.PSBK_NO = DDCUCRDD.PSBK_NO;
            DDCIPSBK.UPT_MMO = DDCUCRDD.TX_MMO;
            CEP.TRC(SCCGWA, DDCIPSBK.UPT_MMO);
            DDCIPSBK.UPT_AMT = DDCUCRDD.TX_AMT;
            DDCIPSBK.AC_ATTR = WS_TMP_ACATTR;
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                DDCIPSBK.UPT_TXN_TYPE = 'D';
            } else {
                DDCIPSBK.UPT_TXN_TYPE = 'C';
            }
            S000_CALL_DDZIPSBK();
        }
    }
    public void B130_BACK_VALUE_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUCRDD.VAL_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if ((DDCUCRDD.VAL_DATE > 0) 
            && (DDCUCRDD.VAL_DATE < SCCGWA.COMM_AREA.AC_DATE)) {
            IBS.init(SCCGWA, DDCIBACK);
            DDCIBACK.AC_NO = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
            CEP.TRC(SCCGWA, DDCIBACK.AC_NO);
            DDCIBACK.DATE = DDCUCRDD.VAL_DATE;
            DDCIBACK.CCY = DDCUCRDD.CCY;
            DDCIBACK.CCY_TYP = DDCUCRDD.CCY_TYPE;
            DDCIBACK.AMT = DDCUCRDD.TX_AMT;
            DDCIBACK.OPT = 'D';
            DDCIBACK.TX_TYPE = 'T';
            DDCIBACK.FUNC = 'T';
            DDCIBACK.LAST_POST_DATE = DDRCCY.LAST_POST_DATE;
            S000_CALL_DDZIBACK();
        }
    }
    public void B165_UPD_RTV_CCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCITRSR);
        DCCITRSR.INP_DATA.OPR = 'T';
        CEP.TRC(SCCGWA, WS_UCRDD_AC);
        DCCITRSR.INP_DATA.AC = WS_UCRDD_AC;
        DCCITRSR.INP_DATA.CCY = DDCUCRDD.CCY;
        DCCITRSR.INP_DATA.CCY_TYPE = DDCUCRDD.CCY_TYPE;
        DCCITRSR.INP_DATA.DRCR_FLG = 'C';
        DCCITRSR.INP_DATA.TRS_AMT = DDCUCRDD.TX_AMT;
        DCCITRSR.INP_DATA.APP = "DD";
        if (DDCUCRDD.LAW_DUCT_FLG == 'Y') {
            DCCITRSR.INP_DATA.LAW_FLG = '1';
        }
        CEP.TRC(SCCGWA, DCCITRSR.INP_DATA.OPR);
        CEP.TRC(SCCGWA, DCCITRSR.INP_DATA.AC);
        CEP.TRC(SCCGWA, DCCITRSR.INP_DATA.CCY);
        CEP.TRC(SCCGWA, DCCITRSR.INP_DATA.CCY_TYPE);
        CEP.TRC(SCCGWA, DCCITRSR.INP_DATA.DRCR_FLG);
        CEP.TRC(SCCGWA, DCCITRSR.INP_DATA.TRS_AMT);
        CEP.TRC(SCCGWA, DCCITRSR.INP_DATA.APP);
        S000_CALL_DCZITRSR();
    }
    public void B170_FIN_TXN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        if (DDCUCRDD.TX_TYPE == 'T' 
            && DDCUCRDD.OTHER_AC.trim().length() > 0) {
            B170_01_GET_RLT_BR_INFO();
        }
        IBS.init(SCCGWA, BPCPFHIS);
        BPCPFHIS.DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPFHIS.DATA.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        BPCPFHIS.DATA.TX_DRCR_FLG = 'C';
        BPCPFHIS.DATA.PRINT_IND = 'Y';
        BPCPFHIS.DATA.SMS_FLG = DDCUCRDD.SMS_FLG;
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DC")) {
            BPCPFHIS.DATA.SUMUP_FLG = '1';
        } else {
            BPCPFHIS.DATA.SUMUP_FLG = '2';
        }
        BPCPFHIS.DATA.AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        if (DCCUCINO.CARD_LNK_TYP == '2') {
            BPCPFHIS.DATA.AC = DCCUCINO.CARD_NO;
        }
        BPCPFHIS.DATA.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.ACO_AC);
        CEP.TRC(SCCGWA, WS_CARD_NO);
        if (CICCUST.O_DATA.O_CI_TYP == '1' 
            && CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DC")) {
            CEP.TRC(SCCGWA, "PERSONAL CARD");
            if (DDCUCRDD.CARD_NO.trim().length() > 0) {
                BPCPFHIS.DATA.TX_TOOL = DDCUCRDD.CARD_NO;
            } else {
                BPCPFHIS.DATA.TX_TOOL = DDCUCRDD.AC;
            }
        } else {
            if (CICCUST.O_DATA.O_CI_TYP == '2' 
                && CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
                CEP.TRC(SCCGWA, "COMPANY CARD");
                BPCPFHIS.DATA.TX_TOOL = DDCUCRDD.CARD_NO;
            }
        }
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_TOOL);
        if (DDCUCRDD.VAL_DATE != 0) {
            BPCPFHIS.DATA.TX_VAL_DT = DDCUCRDD.VAL_DATE;
        } else {
            BPCPFHIS.DATA.TX_VAL_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        BPCPFHIS.DATA.TX_CCY = DDCUCRDD.CCY;
        BPCPFHIS.DATA.TX_CCY_TYP = DDCUCRDD.CCY_TYPE;
        CEP.TRC(SCCGWA, DDCUCRDD.CCY_TYPE);
        BPCPFHIS.DATA.TX_AMT = DDCUCRDD.TX_AMT;
        BPCPFHIS.DATA.TX_TYPE = DDCUCRDD.TX_TYPE;
        BPCPFHIS.DATA.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        CEP.TRC(SCCGWA, DDCUCRDD.BV_VTYP);
        CEP.TRC(SCCGWA, DDCUCRDD.BV_VNO);
        if (DDCUCRDD.BV_VTYP != 0) {
            BPCPFHIS.DATA.BV_CODE = "" + DDCUCRDD.BV_VTYP;
            JIBS_tmp_int = BPCPFHIS.DATA.BV_CODE.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) BPCPFHIS.DATA.BV_CODE = "0" + BPCPFHIS.DATA.BV_CODE;
        }
        if (DDCUCRDD.BV_VNO.trim().length() > 0) {
            BPCPFHIS.DATA.BV_NO = DDCUCRDD.BV_VNO;
        }
        CEP.TRC(SCCGWA, DDRCCY.AC_TYPE);
        if (CICCUST.O_DATA.O_CI_TYP == '1' 
            && CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD") 
            && DDRCCY.AC_TYPE != '8') {
            BPCPFHIS.DATA.BV_CODE = "A00015";
            BPCPFHIS.DATA.BV_NO = DDCUCRDD.PSBK_NO;
        }
        if (BPCPFHIS.DATA.BV_NO.trim().length() == 0 
            && BPCPFHIS.DATA.BV_CODE.equalsIgnoreCase("A00015")) {
            BPCPFHIS.DATA.BV_NO = DDRVCH.PSBK_NO;
        }
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.BV_CODE);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.BV_NO);
        BPCPFHIS.DATA.NARRATIVE = DDCUCRDD.NARRATIVE;
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.NARRATIVE);
        if (WS_REAL_VIR_FLG != 'Y') {
            BPCPFHIS.DATA.REMARK = DDCUCRDD.REMARKS;
        } else {
            BPCPFHIS.DATA.REMARK = "VIR:" + DDCUCRDD.AC;
        }
        CEP.TRC(SCCGWA, DDCUCRDD.REMARKS);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.REMARK);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            if (DDCUCRDD.TX_MMO.trim().length() == 0) {
                DDCUCRDD.TX_MMO = "G004";
            }
        }
        BPCPFHIS.DATA.TX_MMO = DDCUCRDD.TX_MMO;
        CEP.TRC(SCCGWA, DDRCCY.PROD_CODE);
        BPCPFHIS.DATA.PROD_CD = DDRCCY.PROD_CODE;
        if (DDCUCRDD.TX_TYPE == 'T') {
            BPCPFHIS.DATA.OTH_AC = DDCUCRDD.OTHER_AC;
            BPCPFHIS.DATA.OTH_TX_TOOL = DDCUCRDD.OTH_TX_TOOL;
            CEP.TRC(SCCGWA, DDCUCRDD.RLT_AC);
            if (DDCUCRDD.RLT_AC.trim().length() > 0) {
                BPCPFHIS.DATA.RLT_AC = DDCUCRDD.RLT_AC;
                BPCPFHIS.DATA.RLT_AC_NAME = DDCUCRDD.RLT_AC_NAME;
                BPCPFHIS.DATA.RLT_BANK = DDCUCRDD.RLT_BANK;
                BPCPFHIS.DATA.RLT_BK_NM = DDCUCRDD.RLT_BK_NM;
                BPCPFHIS.DATA.RLT_REF_NO = DDCUCRDD.RLT_REF_NO;
                BPCPFHIS.DATA.RLT_CCY = DDCUCRDD.RLT_CCY;
                BPCPFHIS.DATA.RLT_TX_TOOL = DDCUCRDD.RLT_TX_TOOL;
                BPCPFHIS.DATA.ST_FILL.RTL_CUST_TYP = DDCUCRDD.RLT_CI_TYP;
                BPCPFHIS.DATA.ST_FIL = IBS.CLS2CPY(SCCGWA, BPCPFHIS.DATA.ST_FILL);
            } else {
                if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
                    BPCPFHIS.DATA.RLT_AC = DDCUCRDD.OTHER_AC;
                    BPCPFHIS.DATA.RLT_AC_NAME = AICPQMIB.OUTPUT_DATA.CHS_NM;
                    BPCPFHIS.DATA.RLT_BANK = "" + AICPQMIB.INPUT_DATA.BR;
                    JIBS_tmp_int = BPCPFHIS.DATA.RLT_BANK.length();
                    for (int i=0;i<6-JIBS_tmp_int;i++) BPCPFHIS.DATA.RLT_BANK = "0" + BPCPFHIS.DATA.RLT_BANK;
                    BPCPFHIS.DATA.RLT_BK_NM = BPCPQORG.CHN_NM;
                    BPCPFHIS.DATA.RLT_CCY = AICPQMIB.INPUT_DATA.CCY;
                } else {
                    BPCPFHIS.DATA.RLT_AC = DDCUCRDD.OTHER_AC;
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
                    BPCPFHIS.DATA.RLT_TX_TOOL = DDCUCRDD.OTH_TX_TOOL;
                }
            }
        }
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.SUMUP_FLG);
        CEP.TRC(SCCGWA, DDCUCRDD.OTH_TX_TOOL);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.RLT_AC);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.RLT_AC_NAME);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.RLT_BANK);
        BPCPFHIS.DATA.VAL_BAL = WS_CURR_BAL;
        BPCPFHIS.DATA.VAL_BAL_CCY = DDCUCRDD.CCY;
        if (DDCUCRDD.AUTO_TDTODD_FLG == 'Y') {
            BPCPFHIS.DATA.PRINT_IND = 'N';
        } else {
            BPCPFHIS.DATA.PRINT_IND = 'Y';
        }
        CEP.TRC(SCCGWA, DDCUCRDD.HIS_SHOW_FLG);
        BPCPFHIS.DATA.DISPLAY_IND = DDCUCRDD.HIS_SHOW_FLG;
        IBS.init(SCCGWA, DDRFHIS);
        if (DDCIPSBK.PSBK_NO.trim().length() > 0 
            && !IBS.isNumeric(DDCIPSBK.PSBK_NO)) {
            DDRFHIS.PORT = "DDBV01";
            BPCPFHIS.DATA.REF_NO = DDCIPSBK.PSBK_NO;
        } else {
            DDRFHIS.PORT = "" + DDCUCRDD.BV_VTYP;
            JIBS_tmp_int = DDRFHIS.PORT.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) DDRFHIS.PORT = "0" + DDRFHIS.PORT;
            BPCPFHIS.DATA.REF_NO = DDCUCRDD.BV_VNO;
        }
        CEP.TRC(SCCGWA, DDCIPSBK.PSBK_NO);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.REF_NO);
        DDRFHIS.TX_DATE = SCCGWA.COMM_AREA.TR_DATE;
        DDRFHIS.AC_NO = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        if (DCCUCINO.CARD_LNK_TYP == '2') {
            DDRFHIS.AC_NO = DCCUCINO.CARD_NO;
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            DDRFHIS.TX_TYPE = 'N';
        }
        DDRFHIS.SVR_CODE = SCCGWA.COMM_AREA.SERV_CODE;
        DDRFHIS.TX_AMT = DDCUCRDD.TX_AMT;
        DDRFHIS.CCY = DDCUCRDD.CCY;
        BPCPFHIS.DATA.TX_DRCR_FLG = 'C';
        DDRFHIS.TXTIME = SCCGWA.COMM_AREA.TR_TIME;
        DDRFHIS.REF = DDCUCRDD.REMARKS;
        DDRFHIS.TXBR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRFHIS.OPR = SCCGWA.COMM_AREA.TL_ID;
        DDRFHIS.LEDGER_BAL = DDRCCY.CURR_BAL;
        DDRFHIS.CI_NO = CICCUST.O_DATA.O_CI_NO;
        BPCPFHIS.DATA.FMT_CODE = "DD099";
        BPCPFHIS.DATA.FMT_LEN = 190;
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.FMT_LEN);
        BPCPFHIS.DATA.FMT_DATA = IBS.CLS2CPY(SCCGWA, DDRFHIS);
        S000_CALL_BPZPFHIS();
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
    }
    public void B175_VIR_VTXD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FSCSVTX);
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            FSCSVTX.TRAN_DATE = SCCGWA.COMM_AREA.AC_DATE;
            FSCSVTX.SERIAL_NO = SCCGWA.COMM_AREA.JRN_NO;
            FSCSVTX.SUB_SERIAL_NO = SCCGWA.COMM_AREA.CALL_SEQ;
            if (DDCUCRDD.OTHER_AC.trim().length() > 0) {
                FSCSVTX.PAY_ACCT = DDCUCRDD.OTHER_AC;
                FSCSVTX.PAY_NME = WS_PAY_NAME;
            } else {
                FSCSVTX.PAY_ACCT = DDCUCRDD.RLT_AC;
                FSCSVTX.PAY_NME = DDCUCRDD.RLT_AC_NAME;
            }
            FSCSVTX.CCY = DDCUCRDD.CCY;
            FSCSVTX.CCY_TYP = DDCUCRDD.CCY_TYPE;
            FSCSVTX.RES_ACCT = WS_REL_AC;
            FSCSVTX.RES_NME = DDCUCRDD.CHI_NAME;
            FSCSVTX.DR_FLG = 'C';
            FSCSVTX.TRANS_ATM = DDCUCRDD.TX_AMT;
            if (WS_REAL_VIR_FLG != 'Y') {
                FSCSVTX.VIR_ACCT = " ";
            } else {
                FSCSVTX.VIR_ACCT = DDCUCRDD.AC;
            }
            FSCSVTX.TRAN_TIME = SCCGWA.COMM_AREA.TR_TIME;
            FSCSVTX.ZY_CODE = DDCUCRDD.TX_MMO;
            FSCSVTX.REQFM_NO = CICQACRI.O_DATA.O_FRM_APP;
            FSCSVTX.EC_FLG = ' ';
            FSCSVTX.REMARK = DDCUCRDD.REMARKS;
            FSCSVTX.USE = DDCUCRDD.NARRATIVE;
        } else {
            FSCSVTX.TRAN_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            FSCSVTX.SERIAL_NO = GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
            CEP.TRC(SCCGWA, FSCSVTX.TRAN_DATE);
            CEP.TRC(SCCGWA, FSCSVTX.SERIAL_NO);
        }
        S000_CALL_FSZVTXD();
    }
    public void B175_VIR_CURBAL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FSCUVSAT);
        FSCUVSAT.VS_AC = DDCUCRDD.AC;
        FSCUVSAT.CCY = DDCUCRDD.CCY;
        FSCUVSAT.CCY_TYP = DDCUCRDD.CCY_TYPE;
        FSCUVSAT.VALUE_DT = SCCGWA.COMM_AREA.AC_DATE;
        FSCUVSAT.TNJNL = SCCGWA.COMM_AREA.JRN_NO;
        FSCUVSAT.TRAN_AMT = DDCUCRDD.TX_AMT;
        FSCUVSAT.DR_CR_F = 'C';
        FSCUVSAT.OTH_AC = DDCUCRDD.OTHER_AC;
        FSCUVSAT.TX_TYPE = DDCUCRDD.TX_TYPE;
        FSCUVSAT.TX_MMO = DDCUCRDD.TX_MMO;
        CEP.TRC(SCCGWA, FSCUVSAT.VS_AC);
        CEP.TRC(SCCGWA, FSCUVSAT.OTH_AC);
        CEP.TRC(SCCGWA, FSCUVSAT.TX_MMO);
        S000_CALL_FSZUVSAT();
    }
    public void B180_NOTICE_BTE_SYS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FSCBTE);
        FSCBTE.SERIAL_NO = SCCGWA.COMM_AREA.JRN_NO;
        FSCBTE.SUB_SERIAL_NO = SCCGWA.COMM_AREA.CALL_SEQ;
        if (DDCUCRDD.OTHER_AC.trim().length() > 0) {
            FSCBTE.PAY_ACCT = DDCUCRDD.OTHER_AC;
            FSCBTE.PAY_NME = WS_PAY_NAME;
        } else {
            FSCBTE.PAY_ACCT = DDCUCRDD.RLT_AC;
            FSCBTE.PAY_NME = DDCUCRDD.RLT_AC_NAME;
        }
        FSCBTE.CRYTYPE = DDCUCRDD.CCY;
        FSCBTE.TRAN_TYPE = 'C';
        FSCBTE.TRAN_ZY = DDCUCRDD.TX_MMO;
        FSCBTE.TRAN_AMT = DDCUCRDD.TX_AMT;
        FSCBTE.TRAN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        FSCBTE.TRAN_TIME = SCCGWA.COMM_AREA.TR_TIME;
        FSCBTE.RES_ACCT = WS_REL_AC;
        FSCBTE.RES_BZ = DDCUCRDD.CCY;
        FSCBTE.RES_NME = DDCUCRDD.CHI_NAME;
        FSCBTE.T_RMK = DDCUCRDD.REMARKS;
        FSCBTE.T_USE = DDCUCRDD.NARRATIVE;
        if (WS_REAL_VIR_FLG != 'Y') {
            FSCBTE.VIR_ACCT = " ";
        } else {
            FSCBTE.VIR_ACCT = DDCUCRDD.AC;
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
        }
    }
    public void B180_NOTICE_NFMX_SYS_PROC() throws IOException,SQLException,Exception {
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        CEP.TRC(SCCGWA, DDRMST.AC_STS_WORD.substring(61 - 1, 61 + 1 - 1));
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(61 - 1, 61 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.init(SCCGWA, SCCTPCL);
            WS_DATA_TO_NFMX.WS_AC_NO = DDCUCRDD.AC;
            WS_DATA_TO_NFMX.WS_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
            if (DDCUCRDD.AUTO_TDTODD_FLG == 'Y') {
                WS_DATA_TO_NFMX.WS_TX_AMT = DDCUCRDD.TD_INT_AMT;
            } else {
                WS_DATA_TO_NFMX.WS_TX_AMT = DDCUCRDD.TX_AMT;
            }
            WS_DATA_TO_NFMX.WS_TX_DT = SCCGWA.COMM_AREA.AC_DATE;
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                WS_DATA_TO_NFMX.WS_TX_STS = '1';
            } else {
                WS_DATA_TO_NFMX.WS_TX_STS = '0';
            }
            if (BPCPFHIS.DATA.OTH_TX_TOOL.trim().length() > 0) {
                WS_DATA_TO_NFMX.WS_O_AC = BPCPFHIS.DATA.OTH_TX_TOOL;
            } else {
                WS_DATA_TO_NFMX.WS_O_AC = DDCUCRDD.OTHER_AC;
            }
            CEP.TRC(SCCGWA, CICACCUOT.DATA.AC_CNM);
            WS_DATA_TO_NFMX.WS_O_NAME = CICACCUOT.DATA.AC_CNM;
            if (DDCUCRDD.OTHER_AC.trim().length() > 0 
                && !ACTY_OUTPUT.STD_APP.equalsIgnoreCase("AI")) {
            } else {
                if (DDCUCRDD.RLT_AC.trim().length() > 0) {
                    WS_DATA_TO_NFMX.WS_O_AC = DDCUCRDD.RLT_AC;
                    WS_DATA_TO_NFMX.WS_O_NAME = DDCUCRDD.RLT_AC_NAME;
                }
            }
            CEP.TRC(SCCGWA, WS_DATA_TO_NFMX.WS_O_AC);
            CEP.TRC(SCCGWA, WS_DATA_TO_NFMX.WS_O_NAME);
            WS_DATA_TO_NFMX.WS_REMARK = DDCUCRDD.REMARKS;
            WS_DATA_TO_NFMX.WS_NARRATIVE = DDCUCRDD.NARRATIVE;
            SCCTPCL.SERV_AREA.OBJ_SYSTEM = K_OBJ_SYSTEM1;
            SCCTPCL.SERV_AREA.SERV_CODE = K_SERV_CODE1;
            SCCTPCL.SERV_AREA.SERV_TYPE = ' ';
            CEP.TRC(SCCGWA, WS_DATA_TO_NFMX.WS_AC_NO);
            CEP.TRC(SCCGWA, WS_DATA_TO_NFMX.WS_JRN_NO);
            CEP.TRC(SCCGWA, WS_DATA_TO_NFMX.WS_TX_AMT);
            CEP.TRC(SCCGWA, WS_DATA_TO_NFMX.WS_TX_DT);
            CEP.TRC(SCCGWA, WS_DATA_TO_NFMX.WS_TX_STS);
            CEP.TRC(SCCGWA, WS_DATA_TO_NFMX.WS_O_AC);
            CEP.TRC(SCCGWA, WS_DATA_TO_NFMX.WS_O_NAME);
            CEP.TRC(SCCGWA, WS_DATA_TO_NFMX.WS_REMARK);
            CEP.TRC(SCCGWA, WS_DATA_TO_NFMX.WS_NARRATIVE);
            SCCTPCL.INP_AREA.SERV_DATA_LEN = 613;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, WS_DATA_TO_NFMX);
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
            }
        }
    }
    public void B185_NOTICE_KHMS_SYS_PROC() throws IOException,SQLException,Exception {
        if (WS_MST_AC_FLG == 'Y') {
            IBS.init(SCCGWA, CICMAGT);
            CICMAGT.DATA.AGT_TYP = WS_AGT_TYP;
            CICMAGT.DATA.ENTY_TYP = '3';
            CICMAGT.DATA.AGT_STS = 'N';
            CICMAGT.DATA.ENTY_NO = WS_VS_AC;
            S000_CALL_CIZMAGT();
            if (CICMAGT.DATA.AGT_NO.trim().length() == 0) {
                IBS.init(SCCGWA, CICMAGT);
                CICMAGT.DATA.AGT_TYP = WS_AGT_TYP;
                CICMAGT.DATA.ENTY_TYP = '1';
                CICMAGT.DATA.AGT_STS = 'N';
                CICMAGT.DATA.ENTY_NO = DDCUCRDD.AC;
                S000_CALL_CIZMAGT();
            }
        } else {
            IBS.init(SCCGWA, CICMAGT);
            CICMAGT.DATA.AGT_TYP = WS_AGT_TYP;
            CICMAGT.DATA.ENTY_TYP = '1';
            CICMAGT.DATA.AGT_STS = 'N';
            CICMAGT.DATA.ENTY_NO = DDCUCRDD.AC;
            CEP.TRC(SCCGWA, CICMAGT.DATA.AGT_TYP);
            CEP.TRC(SCCGWA, CICMAGT.DATA.ENTY_TYP);
            CEP.TRC(SCCGWA, CICMAGT.DATA.AGT_STS);
            CEP.TRC(SCCGWA, CICMAGT.DATA.ENTY_NO);
            S000_CALL_CIZMAGT();
        }
        CEP.TRC(SCCGWA, CICMAGT.DATA.AGT_NO);
        if (CICMAGT.DATA.AGT_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, "NOTICE KHMS");
            IBS.init(SCCGWA, DDCNKHMS);
            if (WS_VS_AC_FLG == 'Y') {
                DDCNKHMS.VA_NO = DDCUCRDD.AC;
                DDCNKHMS.AC_NO = WS_VS_AC;
            } else {
                if (WS_MST_AC_FLG == 'Y') {
                    DDCNKHMS.VA_NO = WS_VS_AC;
                    DDCNKHMS.AC_NO = WS_VS_AC;
                } else {
                    DDCNKHMS.VA_NO = DDCUCRDD.AC;
                    DDCNKHMS.AC_NO = DDCUCRDD.AC;
                }
            }
            CEP.TRC(SCCGWA, DDCNKHMS.VA_NO);
            CEP.TRC(SCCGWA, DDCNKHMS.AC_NO);
            DDCNKHMS.CCY = DDCUCRDD.CCY;
            DDCNKHMS.CCY_TYP = DDCUCRDD.CCY_TYPE;
            DDCNKHMS.BV_TYP = DDCUCRDD.BV_TYP;
            if (DDCUCRDD.CARD_NO.trim().length() > 0) {
                DDCNKHMS.BV_NO = DDCUCRDD.CARD_NO;
            } else {
                DDCNKHMS.BV_NO = DDCUCRDD.PSBK_NO;
            }
            DDCNKHMS.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            DDCNKHMS.TX_MMO = DDCUCRDD.TX_MMO;
            if (DDCUCRDD.TX_MMO.equalsIgnoreCase("GTV")
                || DDCUCRDD.TX_MMO.equalsIgnoreCase("X15")) {
                DDCNKHMS.C_FLG = '3';
            } else if (DDCUCRDD.TX_MMO.equalsIgnoreCase("GTU")
                || DDCUCRDD.TX_MMO.equalsIgnoreCase("X16")) {
                DDCNKHMS.C_FLG = '2';
            } else {
                DDCNKHMS.C_FLG = '1';
            }
            if (BPCPFHIS.DATA.OTH_TX_TOOL.trim().length() > 0) {
                DDCNKHMS.OT_AC = BPCPFHIS.DATA.OTH_TX_TOOL;
            } else {
                DDCNKHMS.OT_AC = DDCUCRDD.OTHER_AC;
            }
            CEP.TRC(SCCGWA, CICACCU.DATA.AC_CNM);
            DDCNKHMS.OT_NAME = CICACCUOT.DATA.AC_CNM;
            if (DDCUCRDD.AUTO_TDTODD_FLG == 'Y') {
                DDCNKHMS.TX_AMT = DDCUCRDD.TD_INT_AMT;
            } else {
                DDCNKHMS.TX_AMT = DDCUCRDD.TX_AMT;
            }
            DDCNKHMS.AC_BAL = DDRCCY.CURR_BAL + DDRCCY.CCAL_TOT_BAL;
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                DDCNKHMS.DR_CR_FLG = 'D';
                DDCNKHMS.TX_STS = '1';
            } else {
                DDCNKHMS.DR_CR_FLG = 'C';
                DDCNKHMS.TX_STS = '0';
            }
            DDCNKHMS.TX_TYP = '1';
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
            DDCNKHMS.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
            DDCNKHMS.JRN_SEQ = GWA_BP_AREA.FHIS_CUR_SEQ;
            DDCNKHMS.OT_JRN_NO = GWA_SC_AREA.REQ_SYS_JRN;
            DDCNKHMS.OT_DATE = GWA_SC_AREA.REQ_SYS_DATE;
            DDCNKHMS.OT_BR = DDCUCRDD.OTHER_BR;
            CEP.TRC(SCCGWA, DDCNKHMS.OT_BR);
            CEP.TRC(SCCGWA, DDCNKHMS.OT_JRN_NO);
            DDCNKHMS.TX_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDCNKHMS.TX_TIME = SCCGWA.COMM_AREA.TR_TIME;
            DDCNKHMS.TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            DDCNKHMS.TX_TLR = SCCGWA.COMM_AREA.TL_ID;
            DDCNKHMS.TX_CHNL = SCCGWA.COMM_AREA.CHNL;
            DDCNKHMS.REMARK = DDCUCRDD.REMARKS;
            DDCNKHMS.NARRATIVE = DDCUCRDD.NARRATIVE;
            CEP.TRC(SCCGWA, ACTY_OUTPUT.STD_APP);
            CEP.TRC(SCCGWA, DDCUCRDD.RLT_AC);
            if (DDCUCRDD.OTHER_AC.trim().length() > 0 
                && !ACTY_OUTPUT.STD_APP.equalsIgnoreCase("AI")) {
            } else {
                if (DDCUCRDD.RLT_AC.trim().length() > 0) {
                    DDCNKHMS.OT_AC = DDCUCRDD.RLT_AC;
                    DDCNKHMS.OT_NAME = DDCUCRDD.RLT_AC_NAME;
                    DDCNKHMS.OT_BR = DDCUCRDD.RLT_BANK;
                }
            }
            DDCNKHMS.USE_BAL = DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL + DDRCCY.CCAL_TOT_BAL;
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
            }
            if (WS_I == 1) {
                B185_01_WRITE_DDTNOSI_PROC();
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
    }
    public void B190_GEN_VCH_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = DDCIQPRD.OUTPUT_DATA.PRDT_MODEL;
        BPCPOEWA.DATA.PROD_CODE = DDRCCY.PROD_CODE;
        BPCPOEWA.DATA.EVENT_CODE = "CR";
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.CNTR_TYPE);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.PROD_CODE);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.EVENT_CODE);
        BPCPOEWA.DATA.BR_OLD = DDRCCY.OWNER_BRDP;
        BPCPOEWA.DATA.BR_NEW = DDRCCY.OWNER_BRDP;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = DDCUCRDD.CCY;
        if (DDCUCRDD.VAL_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            BPCPOEWA.DATA.VALUE_DATE = DDCUCRDD.VAL_DATE;
        } else {
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            } else {
                BPCPOEWA.DATA.VALUE_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            }
        }
        if (DDCUCRDD.TX_AMT < 0) {
            DDCUCRDD.TX_AMT = DDCUCRDD.TX_AMT * -1;
            CEP.TRC(SCCGWA, DDCUCRDD.TX_AMT);
            BPCPOEWA.DATA.AMT_INFO[11-1].AMT = DDCUCRDD.TX_AMT;
        } else {
            BPCPOEWA.DATA.AMT_INFO[1-1].AMT = DDCUCRDD.TX_AMT;
        }
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[11-1].AMT);
        BPCPOEWA.DATA.CI_NO = CICCUST.O_DATA.O_CI_NO;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.CI_NO);
        BPCPOEWA.DATA.AC_NO = DDRCCY.KEY.AC;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AC_NO);
        if (DDCIPSBK.PSBK_NO.trim().length() > 0 
            && !IBS.isNumeric(DDCIPSBK.PSBK_NO)) {
            BPCPOEWA.DATA.PORT = "DDBV01";
            BPCPOEWA.DATA.REF_NO = DDCIPSBK.PSBK_NO;
        } else {
            BPCPOEWA.DATA.PORT = "" + DDCUCRDD.BV_VTYP;
            JIBS_tmp_int = BPCPOEWA.DATA.PORT.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) BPCPOEWA.DATA.PORT = "0" + BPCPOEWA.DATA.PORT;
            BPCPOEWA.DATA.REF_NO = DDCUCRDD.BV_VNO;
        }
        BPCPOEWA.DATA.PORT = "" + DDCUCRDD.BV_VTYP;
        JIBS_tmp_int = BPCPOEWA.DATA.PORT.length();
        for (int i=0;i<3-JIBS_tmp_int;i++) BPCPOEWA.DATA.PORT = "0" + BPCPOEWA.DATA.PORT;
        if (DDCUCRDD.OTHER_AC.trim().length() > 0) {
            BPCPOEWA.DATA.AC_NO_REL = DDCUCRDD.OTHER_AC;
            BPCPOEWA.DATA.THEIR_AC = DDCUCRDD.OTHER_AC;
        }
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.THEIR_AC);
        BPCPOEWA.DATA.DESC = DDCUCRDD.REMARKS;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.DESC);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[1-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.PORT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.REF_NO);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AC_NO_REL);
        S000_CALL_BPZPOEWA();
    }
    public void B020_CRT_CCY_OPAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUOPAC);
        DDCUOPAC.PROD_CODE = DDRCCY.PROD_CODE;
        DDCUOPAC.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        DDCUOPAC.CCY = DDCUCRDD.CCY;
        DDCUOPAC.CCY_TYPE = DDCUCRDD.CCY_TYPE;
        DDCUOPAC.PSBK_FLG = '2';
        DDCUOPAC.CARD_FLG = '2';
        DDCUOPAC.AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        DDCUOPAC.ACNO_FLG = 'B';
        DDCUOPAC.ACO_AC_TYP = '2';
        S000_CALL_DDZUOPAC();
    }
    public void B020_CRT_CCY_OCAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUOCAC);
        DDCUOCAC.FUNC = '1';
        DDCUOCAC.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        DDCUOCAC.AC_CCY = DDCUCRDD.CCY;
        DDCUOCAC.CCY_TYPE = DDCUCRDD.CCY_TYPE;
        DDCUOCAC.AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        DDCUOCAC.PROD_CODE = DDRMST.PROD_CODE;
        S000_CALL_DDZUOCAC();
    }
    public void B240_GET_BR_CITY_FLG() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        IBS.init(SCCGWA, BPCPRGST);
        BPCPRGST.BR1 = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPRGST.BR2 = DDRCCY.OWNER_BR;
        CEP.TRC(SCCGWA, BPCPRGST.BR1);
        CEP.TRC(SCCGWA, BPCPRGST.BR2);
        S000_CALL_BPZPRGST();
        CEP.TRC(SCCGWA, BPCPRGST.BRANCH_FLG);
    }
    public void B210_DEP_TD_DRW_DD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUHTOD);
        DDCUHTOD.AC = DDCUCRDD.AC;
        DDCUHTOD.CCY = DDCUCRDD.CCY;
        DDCUHTOD.CCY_TYP = DDCUCRDD.CCY_TYPE;
        DDCUHTOD.AMT = DDCUCRDD.TX_AMT;
        CEP.TRC(SCCGWA, DDVMPRD.VAL.TD_PROD);
        DDCUHTOD.PROD_CD = DDVMPRD.VAL.TD_PROD;
        DDCUHTOD.TERM = "S000";
        IBS.init(SCCGWA, DDRMSTR);
        DDRMSTR.KEY.AC = DDRCCY.KEY.AC;
        CEP.TRC(SCCGWA, DDRMSTR.KEY.AC);
        DDRMSTR.KEY.ADP_TYPE = "1";
        DDRMSTR.ADP_STS = 'O';
        T000_READ_DDTMSTR();
        DDCUHTOD.SPRD_PNT = DDRMSTR.TIR_SPR11;
        DDCUHTOD.SPRD_PCT = DDRMSTR.TIR_SPR_PCT11;
        DDCUHTOD.TX_MMO = DDCUCRDD.TX_MMO;
        S000_CALL_DDZUHTOD();
    }
    public void R000_INQ_CCY_AVL_BAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIQBAL);
        DDCIQBAL.DATA.AC = CICQACAC.DATA.AGR_NO;
        DDCIQBAL.DATA.CCY = DDCUCRDD.CCY;
        S00_CALL_DDZIQBAL();
    }
    public void S000_CALL_DDZUHTOD() throws IOException,SQLException,Exception {
        DDZUHTOD DDZUHTOD = new DDZUHTOD();
        DDZUHTOD.MP(SCCGWA, DDCUHTOD);
    }
    public void S000_CALL_CIZMAGT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-AGT", CICMAGT);
    }
    public void R000_SET_FEE_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
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
        CEP.TRC(SCCGWA, ACTY_OUTPUT.AC_DETL);
        CEP.TRC(SCCGWA, DDCUCRDD.TX_TYPE);
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CARD_PSBK_NO);
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if ((JIBS_tmp_str[0].equalsIgnoreCase("0111700") 
            || JIBS_tmp_str[0].equalsIgnoreCase("0111709")) 
            && DDCUCRDD.TX_TYPE == 'C') {
            BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '1';
        }
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = DDCUCRDD.CCY;
        if (CICCUST.O_DATA.O_CI_TYP == '2' 
            || CICCUST.O_DATA.O_CI_TYP == '3') {
            BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = "156";
        }
        BPCFFTXI.TX_DATA.CCY_TYPE = DDCUCRDD.CCY_TYPE;
        BPCFFTXI.TX_DATA.SVR_CD = SCCGWA.COMM_AREA.SERV_CODE;
        BPCFFTXI.TX_DATA.CI_NO = CICCUST.O_DATA.O_CI_NO;
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY);
        S000_CALL_BPZFFTXI();
    }
    public void R000_CALL_FEE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTCALF);
        BPCTCALF.INPUT_AREA.FUNC_CODE = 'C';
        BPCTCALF.INPUT_AREA.ATTR_VAL.BNK_FLG = '0';
        BPCTCALF.INPUT_AREA.TX_AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.TX_AC);
        BPCTCALF.INPUT_AREA.TX_CCY = DDCUCRDD.CCY;
        BPCTCALF.INPUT_AREA.TX_AMT = DDCUCRDD.TX_AMT;
        BPCTCALF.INPUT_AREA.TX_CNT = 1;
        BPCTCALF.INPUT_AREA.OTHER_AC = DDCUCRDD.OTHER_AC;
        BPCTCALF.INPUT_AREA.TX_CI = CICCUST.O_DATA.O_CI_NO;
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.OTHER_AC);
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.TX_CI);
        BPCTCALF.INPUT_AREA.ATTR_VAL.DC_FLG = '1';
        if (DDCUCRDD.BV_TYP == '1') {
            CEP.TRC(SCCGWA, WS_N_CARD_NO);
            IBS.init(SCCGWA, DCCUCINF);
            if (DDCUCRDD.CARD_NO.trim().length() > 0) {
                DCCUCINF.CARD_NO = DDCUCRDD.CARD_NO;
            } else {
                DCCUCINF.CARD_NO = DDCUCRDD.AC;
            }
            CEP.TRC(SCCGWA, DCCUCINF.CARD_NO);
            S000_CALL_DCZUCINF();
            if (DCCUCINF.ADSC_TYPE == 'C') {
                BPCTCALF.INPUT_AREA.PROD_CODE = DCCUCINF.PROD_CD;
            } else {
                BPCTCALF.INPUT_AREA.PROD_CODE = DDRMST.PROD_CODE;
                if (BPCPRGST.BRANCH_FLG == 'Y') {
                    BPCTCALF.INPUT_AREA.ATTR_VAL.CITY_FLG = '0';
                } else {
                    BPCTCALF.INPUT_AREA.ATTR_VAL.CITY_FLG = '1';
                }
            }
        } else {
            if (BPCPRGST.BRANCH_FLG == 'Y') {
                BPCTCALF.INPUT_AREA.ATTR_VAL.CITY_FLG = '0';
            } else {
                BPCTCALF.INPUT_AREA.ATTR_VAL.CITY_FLG = '1';
            }
        }
        CEP.TRC(SCCGWA, BPCTCALF.RC);
        S000_CALL_BPZFCALF();
        CEP.TRC(SCCGWA, BPCTCALF.RC);
    }
    public void R000_ACCUMULATE_PROC_CAN() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            IBS.init(SCCGWA, DDCUCRD1);
            IBS.CLONE(SCCGWA, DDCUCRDD, DDCUCRD1);
            IBS.init(SCCGWA, FSCFRESL);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CANCEL_JRN_NO);
            CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.SERV_CODE);
            FSCFRESL.CAN_JRN_NO = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
            FSCFRESL.CAN_AC_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            CEP.TRC(SCCGWA, FSCFRESL);
            IBS.CALLCPN(SCCGWA, CPN_FS_FRESL, FSCFRESL);
            IBS.init(SCCGWA, DDCUCRDD);
            IBS.CLONE(SCCGWA, DDCUCRD1, DDCUCRDD);
        }
    }
    public void R000_ACCUMULATE_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            IBS.init(SCCGWA, FSCFCOST);
            FSCFCOST.PAY_ACCT = DDCUCRDD.AC;
            FSCFCOST.CCY = DDCUCRDD.CCY;
            FSCFCOST.CCY_TYP = DDCUCRDD.CCY_TYPE;
            FSCFCOST.TRANS_ATM = DDCUCRDD.TX_AMT;
            FSCFCOST.ZY_CODE = "A608";
            FSCFCOST.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
            FSCFCOST.TRAN_DATE = SCCGWA.COMM_AREA.AC_DATE;
            CEP.TRC(SCCGWA, FSCFCOST.PAY_ACCT);
            CEP.TRC(SCCGWA, FSCFCOST);
            WS_STAR_COMM = new SCZEXIT_WS_STAR_COMM();
            WS_STAR_COMM.STAR_TR_ID = K_GJ_TX;
            WS_STAR_COMM.STAR_DATA = FSCFCOST;
            IBS.START(SCCGWA, WS_STAR_COMM, true);
        }
    }
    public void S000_CALL_BPZFFTXI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_F_TX_INFO, BPCFFTXI);
        if (BPCFFTXI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFTXI.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_SCZTPCL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-LINK-EXTSERV", SCCTPCL);
    }
    public void S000_LINK_BPZSFX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-FX", BPCFX);
        if (BPCFX.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFX.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZFCALF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_F_CAL_FEE, BPCTCALF);
    }
    public void S00_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_I_INQ_BAL, DDCIQBAL);
        if (DDCIQBAL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQBAL.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZIQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_I_INQ_DDPRD, DDCIQPRD);
        if (DDCIQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQPRD.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZIPSBK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_I_PSBK_PROC, DDCIPSBK);
    }
    public void S000_CALL_DDZIBACK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_I_BACK_PROC, DDCIBACK);
        if (DDCIBACK.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIBACK.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFCSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-STS-TBL-AUTH", BPCFCSTS);
        if (BPCFCSTS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFCSTS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZUCBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-U-DDZUCBAL", DDCUCBAL);
    }
    public void S000_CALL_BPZCCPRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-Z-CPRL-MAINTAIN", BPCCPRL);
        if (BPCCPRL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCCPRL.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_UNI_CIZACCU, CICACCU);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        if (CICQACRI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACRI.RC);
        }
    }
    public void S000_CALL_CIZMLMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-LMT", CICMLMT);
        if (CICMLMT.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICMLMT.RC);
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
    }
    public void S000_CALL_DDZUOPAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-OPEN-AC", DDCUOPAC);
    }
    public void S000_CALL_DDZUOCAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-OPEN-COM-AC", DDCUOCAC);
    }
    public void S000_CALL_EAZUTRAN() throws IOException,SQLException,Exception {
        EAZUTRAN EAZUTRAN = new EAZUTRAN();
        EAZUTRAN.MP(SCCGWA, EACUTRAN);
    }
    public void S000_CALL_CIZCKLS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CHECK-LST", CICCKLS);
        if (CICCKLS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCKLS.RC);
        }
    }
    public void S000_CALL_FSZVMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "FS-I-INQ-VMST", FSCIVMST);
        if (FSCIVMST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, FSCIVMST.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_FSZVTXD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "FS-R-WRIT-VTXD", FSCSVTX);
        if (FSCSVTX.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, FSCSVTX.RC);
            S000_ERR_MSG_PROC();
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
        }
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
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
        }
    }
    public void T000_READ_UPDATE_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY2_REC_NOTFND);
        }
    }
    public void T000_REWRITE_DDTCCY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRCCY.KEY.AC);
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.REWRITE(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void T000_READ_DDTCCY_FIRST() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.eqWhere = "CUS_AC";
        DDTCCY_RD.where = "AC_TYPE < > '6'";
        DDTCCY_RD.fst = true;
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (DDRCCY.PROD_CODE.trim().length() == 0) {
            DDRCCY.PROD_CODE = DDRMST.PROD_CODE;
        }
    }
    public void T000_STARTBR_DDTCCY_D() throws IOException,SQLException,Exception {
        DDTCCY_BR.rp = new DBParm();
        DDTCCY_BR.rp.TableName = "DDTCCY";
        DDTCCY_BR.rp.where = "CUS_AC = :DDRCCY.CUS_AC";
        DDTCCY_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, DDRCCYM, this, DDTCCY_BR);
    }
    public void T000_READNXT_DDTCCY_D() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRCCYM, this, DDTCCY_BR);
    }
    public void T000_ENDBR_DDTCCY_D() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTCCY_BR);
    }
    public void T000_REWRITE_DDTCCY_D() throws IOException,SQLException,Exception {
        DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.REWRITE(SCCGWA, DDRCCYM, DDTCCY_RD);
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
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ UPDATE TABLE DDTGPMST ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTGPMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_REWRITE_DDTGPMST() throws IOException,SQLException,Exception {
        DDRGPMST.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRGPMST.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
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
        }
    }
    public void T000_WRITE_DDTNOSI() throws IOException,SQLException,Exception {
        DDTNOSI_RD = new DBParm();
        DDTNOSI_RD.TableName = "DDTNOSI";
        IBS.WRITE(SCCGWA, DDRNOSI, DDTNOSI_RD);
    }
    public void T000_READ_UPDATE_DDTINTB() throws IOException,SQLException,Exception {
        DDTINTB_RD = new DBParm();
        DDTINTB_RD.TableName = "DDTINTB";
        DDTINTB_RD.upd = true;
        IBS.READ(SCCGWA, DDRINTB, DDTINTB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_INTB_REC_FLG = 'Y';
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ UPDATE TABLE DDTINTB ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTINTB";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_REWRITE_DDTINTB() throws IOException,SQLException,Exception {
        DDRINTB.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRINTB.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTINTB_RD = new DBParm();
        DDTINTB_RD.TableName = "DDTINTB";
        IBS.REWRITE(SCCGWA, DDRINTB, DDTINTB_RD);
    }
    public void T000_READ_DDTMSTR() throws IOException,SQLException,Exception {
        DDTMSTR_RD = new DBParm();
        DDTMSTR_RD.TableName = "DDTMSTR";
        DDTMSTR_RD.eqWhere = "AC,ADP_TYPE,ADP_STS";
        DDTMSTR_RD.where = "ADP_STRDATE <= :SCCGWA.COMM_AREA.AC_DATE";
        IBS.READ(SCCGWA, DDRMSTR, this, DDTMSTR_RD);
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
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
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
    public void S000_CALL_BPZPFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PROC_FHIS, BPCPFHIS);
        if (BPCPFHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPFHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_VCH_CPNT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_VWA_CPNT, BPCOVAWR);
        if (BPCOVAWR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOVAWR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        CEP.TRC(SCCGWA, BPCPRMM.RC);
    }
    public void S000_CALL_DCZPFTCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-P-FINANCE-TR-CHK", DCCPFTCK);
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DCZITRSR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-TRS-OPR", DCCITRSR);
        CEP.TRC(SCCGWA, DCCITRSR.RC);
        if (DCCITRSR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCITRSR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCUST.RC);
        }
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF);
        if (DCCUCINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZUADVT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-A-DETAIL-AC", DDCUADVT);
        if (DDCUADVT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCUADVT.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG-STATION", BPCPRGST);
        if (BPCPRGST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRGST.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZIDREG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SRC-DDZIDREG", DDCIDREG);
        if (DDCIDREG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIDREG.RC);
            S000_ERR_MSG_PROC();
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
    public void S000_CALL_CIZREGLC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-REG-LC-CTL", CICREGLC);
        if (CICREGLC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICREGLC.RC);
        }
    }
    public void S000_CALL_CIZMLRG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CAL-FLRG", CICMLRG);
        if (CICMLRG.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICMLRG.RC);
        }
    }
    public void S000_CALL_FSZUVSAT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "FS-M-VIRAC-BAL", FSCUVSAT);
        if (FSCUVSAT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, FSCUVSAT.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC1() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_EXP_DATE);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
