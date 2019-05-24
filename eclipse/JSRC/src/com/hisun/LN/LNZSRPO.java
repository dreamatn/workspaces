package com.hisun.LN;

import com.hisun.DC.*;
import com.hisun.SC.*;
import com.hisun.DD.*;
import com.hisun.IB.*;
import com.hisun.BP.*;
import com.hisun.AI.*;
import com.hisun.GD.*;
import com.hisun.CI.*;
import java.text.DecimalFormat;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSRPO {
    int JIBS_tmp_int;
    DBParm LNTRCVD_RD;
    DBParm LNTBALZ_RD;
    DecimalFormat df;
    String JIBS_NumStr;
    DBParm LNTLOAN_RD;
    String JIBS_tmp_str[] = new String[10];
    DBParm LNTRELA_RD;
    DBParm LNTSUBS_RD;
    DBParm LNTCTPY_RD;
    DBParm LNTPACK_RD;
    DBParm LNTPLDT_RD;
    DBParm LNTTRAN_RD;
    DBParm LNTICTL_RD;
    DBParm LNTAPRD_RD;
    brParm LNTRCVD_BR = new brParm();
    DBParm LNTAGRE_RD;
    DBParm LNTCONT_RD;
    boolean pgmRtn = false;
    String PGM_SCSSCLDT = "SCSSCLDT";
    String K_FMT_ID = "LN400";
    String K_DD_AC = "01";
    String K_INTERNAL = "02";
    String K_IB_AC = "03";
    String K_DC_AC = "05";
    String K_EB_AC = "06";
    String K_CHEQUE = "07";
    char LNZSRPO_FILLER1 = ' ';
    String WS_TRUS_W_AC_TYP = " ";
    String WS_TRUS_W_AC = " ";
    String WS_CLDL_RDI_AC = " ";
    String WS_RELEASE_AC = " ";
    int WS_IDX = 0;
    int WS_X = 0;
    String WS_UDRAC_ACAC = " ";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    int WS_I = 0;
    int WS_J = 0;
    String WS_CTA_NO = " ";
    double WS_LEFT_AMT = 0;
    double WS_DIFF_I_AMT = 0;
    double WS_DIFF_O_AMT = 0;
    double WS_DIFF_L_AMT = 0;
    double WS_LEFT_TOT_AMT = 0;
    double WS_PAY_I_AMT = 0;
    double WS_PAY_O_AMT = 0;
    double WS_PAY_L_AMT = 0;
    double WS_SUBS_TOT_AMT = 0;
    double WS_PAY_AMT = 0;
    double WS_KOU_KUAN_AMT = 0;
    double WS_KOU_GDA_AMT = 0;
    double WS_KOU_GDA_TOT_AMT = 0;
    double WS_KOU_SETL_AMT = 0;
    double WS_KOU_TOT_AMT = 0;
    short WS_SEQ1 = 0;
    short WS_SEQ2 = 0;
    int WS_G = 0;
    double WS_PQMIB_CBAL = 0;
    int WS_IC_CAL_DUE_DT = 0;
    String WS_AC_TYP = " ";
    String WS_AC = " ";
    String WS_GDA_AC = " ";
    String WS_AC_CCY = " ";
    double WS_AC_AMT = 0;
    String WS_MSG_AMT = " ";
    String WS_MSG_INFO = "                                                                      ";
    String WS_MSG_INFO_CHAR = " ";
    String WS_MSG_9 = " ";
    String WS_MSG_INFO_CHAR1 = " ";
    String WS_MSG_91 = " ";
    String WS_FEE_CODE_HRG = " ";
    double WS_TOT_D_AMT = 0;
    int WS_START_DATE = 0;
    LNZSRPO_WS_TRF_NARRATIVE WS_TRF_NARRATIVE = new LNZSRPO_WS_TRF_NARRATIVE();
    int WS_DUE_DT = 0;
    LNZSRPO_WS_INT_PERD WS_INT_PERD = new LNZSRPO_WS_INT_PERD();
    String WS_CCY_CHECK = " ";
    char WS_EX_DIR_CHECK = ' ';
    double WS_EX_TOT_AMT = 0;
    double WS_EX_PAY_AMT = 0;
    String WS_TX_AC_TYP = " ";
    String WS_TX_AC = " ";
    double WS_TX_AMT = 0;
    String WS_CTPY_AC = " ";
    double WS_AMT_ALLOWED = 0;
    LNZSRPO_WS_SETL_INFO WS_SETL_INFO = new LNZSRPO_WS_SETL_INFO();
    double WS_TR_TOT_AMT = 0;
    double WS_TOT_P_UDAMT = 0;
    String WS_FATHER_CONTRACT = " ";
    String WS_CTL_STSW = " ";
    LNZSRPO_WS_CHECK_INFO WS_CHECK_INFO = new LNZSRPO_WS_CHECK_INFO();
    String WS_SUSP_AC = "                                ";
    LNZSRPO_WS_IA_AC WS_IA_AC = new LNZSRPO_WS_IA_AC();
    LNZSRPO_WS_GDA_ACAMT[] WS_GDA_ACAMT = new LNZSRPO_WS_GDA_ACAMT[10];
    int[] WS_ACR_BBR = new int[20];
    int WS_BBR_I = 0;
    int WS_O_I = 0;
    int WS_C_I = 0;
    String WS_P_TMP_AC = " ";
    String WS_I_TMP_AC = " ";
    double WS_O_I_AMT = 0;
    double WS_O_O_AMT = 0;
    double WS_O_L_AMT = 0;
    double WS_PAY_R_I_AMT = 0;
    double WS_PAY_R_O_AMT = 0;
    double WS_PAY_R_L_AMT = 0;
    double WS_PAY_B_I_AMT = 0;
    double WS_PAY_B_O_AMT = 0;
    double WS_PAY_B_L_AMT = 0;
    char WS_CTPY_TR_TYP = ' ';
    double WS_I_LEFT_AMT = 0;
    double WS_O_LEFT_AMT = 0;
    double WS_L_LEFT_AMT = 0;
    double WS_TOT_AMT = 0;
    double WS_TRANM_I_AMT = 0;
    double WS_TRANM_O_AMT = 0;
    double WS_TRANM_L_AMT = 0;
    char WS_TRANM_TXN_TYP = ' ';
    char WS_TRANM_TRAN_FLG = ' ';
    char WS_TRANM_REC_FLAG = ' ';
    double WS_TOT_I_AMT = 0;
    double WS_B_I_TRAN_AMT = 0;
    double WS_B_O_TRAN_AMT = 0;
    double WS_B_L_TRAN_AMT = 0;
    double WS_R_I_TRAN_AMT = 0;
    double WS_R_O_TRAN_AMT = 0;
    double WS_R_L_TRAN_AMT = 0;
    LNZSRPO_WS_B_I_TEXT WS_B_I_TEXT = new LNZSRPO_WS_B_I_TEXT();
    LNZSRPO_WS_R_I_TEXT WS_R_I_TEXT = new LNZSRPO_WS_R_I_TEXT();
    int WS_B_I_LENGTH = 0;
    int WS_R_I_LENGTH = 0;
    String WS_TR_DATA = " ";
    double WS_I_AMT = 0;
    double WS_O_AMT = 0;
    double WS_L_AMT = 0;
    char WS_REC_FLAG = ' ';
    char WS_TYPE = ' ';
    double WS_TOT_CLN_AMT = 0;
    double WS_PAY_AC_BAL = 0;
    double WS_TOT_AMT2 = 0;
    int WS_CLR_DATA_LEN = 0;
    LNZSRPO_WS_CLR_DATA WS_CLR_DATA = new LNZSRPO_WS_CLR_DATA();
    char WS_READ_LNTICTL_FLG = ' ';
    char WS_READ_LNTPLDT_FLG = ' ';
    char WS_AC_FLG = ' ';
    char WS_KOU_GDA_FLAG = ' ';
    char WS_RPO_FND_FLG = ' ';
    char WS_PI_PROC_FLG = ' ';
    char WS_UPD_ICTL_FLG = ' ';
    char WS_UPD_STSW44_0_FLG = ' ';
    char WS_UPD_STSW45_0_FLG = ' ';
    char WS_SUNI_CLR_FLG = ' ';
    LNZSRPO_WS_BEF_PART_DATA WS_BEF_PART_DATA = new LNZSRPO_WS_BEF_PART_DATA();
    LNZSRPO_WS_AFT_PART_DATA WS_AFT_PART_DATA = new LNZSRPO_WS_AFT_PART_DATA();
    int WS_PART_NO = 0;
    double WS_OWE_P_AMT = 0;
    double WS_OWE_I_AMT = 0;
    double WS_OWE_O_AMT = 0;
    double WS_OWE_L_AMT = 0;
    double WS_OWE_F_AMT = 0;
    int WS_N = 0;
    LNRSETL LNRSETL = new LNRSETL();
    LNCRSETL LNCRSETL = new LNCRSETL();
    LNRRELA LNRRELA = new LNRRELA();
    LNRSUBS LNRSUBS = new LNRSUBS();
    DCCSIALP DCCSIALP = new DCCSIALP();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    LNCURPN LNCURPN = new LNCURPN();
    LNCOD10 LNCOD10 = new LNCOD10();
    LNCICRCM LNCICRCM = new LNCICRCM();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    IBCPOSTA IBCPOSTA = new IBCPOSTA();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    BPCUABOX BPCUABOX = new BPCUABOX();
    AICUUPIA AICUUPIA = new AICUUPIA();
    LNCSRPC LNCSRPC = new LNCSRPC();
    LNRFUND LNRFUND = new LNRFUND();
    LNCRFUND LNCRFUND = new LNCRFUND();
    LNRICTL LNRICTL = new LNRICTL();
    LNCICAL LNCICAL = new LNCICAL();
    LNRCTPY LNRCTPY = new LNRCTPY();
    GDCIQLDR GDCIQLDR = new GDCIQLDR();
    GDCUMPLD GDCUMPLD = new GDCUMPLD();
    BPCEX BPCEX = new BPCEX();
    GDCUMPDR GDCUMPDR = new GDCUMPDR();
    CICQACRI CICQACRI = new CICQACRI();
    LNRRCVD LNRRCVD = new LNRRCVD();
    LNCRRCVD LNCRRCVD = new LNCRRCVD();
    SCCTPCL SCCTPCL = new SCCTPCL();
    LNRAGRE LNRAGRE = new LNRAGRE();
    LNRAPRD LNRAPRD = new LNRAPRD();
    LNRPLDT LNRPLDT = new LNRPLDT();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPCFFCON BPCFFCON = new BPCFFCON();
    BPCOFSVR BPCOFSVR = new BPCOFSVR();
    GDCSDDDR GDCSDDDR = new GDCSDDDR();
    LNCCNEX LNCCNEX = new LNCCNEX();
    AICPQIA AICPQIA = new AICPQIA();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    LNCSSCHE LNCSSCHE = new LNCSSCHE();
    LNCCLNQ LNCCLNQ = new LNCCLNQ();
    AICPQITM AICPQITM = new AICPQITM();
    AICPQMIB AICPQMIB = new AICPQMIB();
    DDCSQBAL DDCSQBAL = new DDCSQBAL();
    CICACCU CICACCU = new CICACCU();
    CICCUST CICCUST = new CICCUST();
    LNCIGEAI LNCIGEAI = new LNCIGEAI();
    LNRRELI LNRRELI = new LNRRELI();
    LNCSSUNM LNCSSUNM = new LNCSSUNM();
    LNCPCFTA LNCPCFTA = new LNCPCFTA();
    LNRPACK LNRPACK = new LNRPACK();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCTRANM LNCTRANM = new LNCTRANM();
    LNCSPDQ LNCSPDQ = new LNCSPDQ();
    LNCSCLR LNCSCLR = new LNCSCLR();
    IBCQINF IBCQINF = new IBCQINF();
    SCCCLDT SCCCLDT = new SCCCLDT();
    LNCSCLRR LNCSCLRR = new LNCSCLRR();
    CICQACAC CICQACAC = new CICQACAC();
    LNRTRAN LNRTRAN = new LNRTRAN();
    LNRPARS LNRPARS = new LNRPARS();
    LNRCONT LNRCONT = new LNRCONT();
    GDCSTRAC GDCSTRAC = new GDCSTRAC();
    GDCSRLSR GDCSRLSR = new GDCSRLSR();
    DDCUOBAL DDCUOBAL = new DDCUOBAL();
    LNRLOAN LNRLOAN = new LNRLOAN();
    LNCIPAMT LNCIPAMT = new LNCIPAMT();
    LNRBALZ LNRBALZ = new LNRBALZ();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    SCCBATH SCCBATH;
    LNCSRPO LNCSRPO;
    public LNZSRPO() {
        for (int i=0;i<10;i++) WS_GDA_ACAMT[i] = new LNZSRPO_WS_GDA_ACAMT();
    }
    public void MP(SCCGWA SCCGWA, LNCSRPO LNCSRPO) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSRPO = LNCSRPO;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSRPO return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
            SCCBATH = (SCCBATH) SCCGWA.COMM_AREA.BAT_AREA_PTR;
            CEP.TRC(SCCGWA, SCCBATH.PROC_NAME);
            CEP.TRC(SCCGWA, SCCBATH.PGM_NAME);
        }
        LNCSRPO.RC.RC_APP = "LN";
        LNCSRPO.RC.RC_RTNCODE = 0;
        WS_BBR_I = 1;
        LNCSRPO.COMM_DATA.MMO = " ";
        LNCSRPO.COMM_DATA.MMO = "D106";
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_INPUT_VAILIDATION();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
    }
    public void B000_INPUT_VAILIDATION() throws IOException,SQLException,Exception {
        B0000_CHECK_SOMETHING();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B010_INPUT_CHECK();
            if (pgmRtn) return;
            B020_GET_CTA_AND_PARM_INFO();
            if (pgmRtn) return;
            if (LNCSRPO.COMM_DATA.SUBS_FLG == 'Y') {
                B040_AC_CHECK();
                if (pgmRtn) return;
            }
        } else {
            CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO);
            CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE);
            if (GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO != 0) {
                IBS.init(SCCGWA, LNRTRAN);
                LNRTRAN.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
                R000_READ_LNTTRAN();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, LNRTRAN.KEY.TR_JRN_NO);
                CEP.TRC(SCCGWA, LNRTRAN.KEY.TR_DATE);
                if (GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO != LNRTRAN.KEY.TR_JRN_NO 
                    || GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE != LNRTRAN.KEY.TR_DATE) {
                    CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.LN_E_CANCEL);
                }
            }
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B050_GET_REC_AMT_PROC();
            if (pgmRtn) return;
            B040_LOGIC_CHECK();
            if (pgmRtn) return;
        }
    }
    public void B0000_CHECK_SOMETHING() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCONT);
        LNRCONT.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        T000_READ_LNTCONT();
        if (pgmRtn) return;
        WS_START_DATE = LNRCONT.START_DATE;
        if (LNCSRPO.COMM_DATA.TR_VAL_DTE == WS_START_DATE) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DT, LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRAGRE);
        LNRAGRE.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        T000_READ_LNTAGRE();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
            B050_BATCH_AMT_DEBIT_PROC();
            if (pgmRtn) return;
        } else {
            WS_PAY_AMT = LNCSRPO.COMM_DATA.TOT_AMT;
            CEP.TRC(SCCGWA, WS_PAY_AMT);
            WS_KOU_KUAN_AMT = WS_PAY_AMT;
            if (LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase("04")) {
                B053_BAOZHENGJIN_KOUKUAN();
                if (pgmRtn) return;
            } else {
                B051_AMT_DEBIT_PROC();
                if (pgmRtn) return;
            }
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                if (WS_PAY_AMT != 0) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_E_BAL_NOT_ENOUGH, LNCSRPO.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
        }
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.PD_PROJ_NO);
        if (LNCCLNQ.DATA.PD_PROJ_NO != 0) {
            B052_FUND_LOAN_PROC();
            if (pgmRtn) return;
        }
        B060_SOLD_OUT_LOAN_PROC();
        if (pgmRtn) return;
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if (WS_CTL_STSW.substring(19 - 1, 19 + 1 - 1).equalsIgnoreCase("1")) {
            B095_TRUS_HRGTAX_PRC();
            if (pgmRtn) return;
        }
        if (!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT") 
            && !SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            && SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B061_CHK_FTA_TYP();
            if (pgmRtn) return;
        }
        B070_CUST_REPAY_PROC();
        if (pgmRtn) return;
        B090_UPDATE_WAIWEI_INFO();
        if (pgmRtn) return;
        if (WS_UPD_ICTL_FLG == 'Y') {
            B090_UPD_STSW_PROCESS();
            if (pgmRtn) return;
        }
        B110_BP_HISTORY_GEN();
        if (pgmRtn) return;
        B120_OUTPUT_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B101_JY_CDD();
            if (pgmRtn) return;
            C000_CALL_DDZUOBAL();
            if (pgmRtn) return;
        }
    }
    public void B101_JY_CDD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '3';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
            && LNCICTLM.REC_DATA.CTL_STSW.substring(20 - 1, 20 + 1 - 1).equalsIgnoreCase("1")) {
            LNCICTLM.FUNC = '4';
            LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
            IBS.init(SCCGWA, DCCSIALP);
            DCCSIALP.FUNC = 'D';
            DCCSIALP.LN_AC = LNCSRPO.COMM_DATA.CTA_NO;
            DCCSIALP.LN_TYP = '2';
            S000_CALL_DCZSIALP();
            if (pgmRtn) return;
            LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
            S000_CALL_LNZICTLM();
            if (pgmRtn) return;
            LNCICTLM.FUNC = '2';
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 20 - 1) + "0" + LNCICTLM.REC_DATA.CTL_STSW.substring(20 + 1 - 1);
            S000_CALL_LNZICTLM();
            if (pgmRtn) return;
        }
    }
    public void C000_CALL_DDZUOBAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRLOAN);
        LNRLOAN.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        T000_READ_LNTLOAN();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNRLOAN.CTL_TYPE);
        if (LNRLOAN.CTL_TYPE == '8') {
            CEP.TRC(SCCGWA, LNCSRPO.COMM_DATA.TOT_P_AMT);
            IBS.init(SCCGWA, DDCUOBAL);
            DDCUOBAL.AC = LNCSRPO.COMM_DATA.CTA_NO;
            DDCUOBAL.OD_AMT = LNCSRPO.COMM_DATA.TOT_P_AMT;
            S000_CALL_DDZUOBAL();
            if (pgmRtn) return;
        } else {
        }
    }
    public void S000_CALL_DCZSIALP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-AUTO-LN-PLAN", DCCSIALP);
    }
    public void B010_INPUT_CHECK() throws IOException,SQLException,Exception {
        if (LNCSRPO.COMM_DATA.CTA_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCSRPO.COMM_DATA.TR_VAL_DTE == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCSRPO.COMM_DATA.TR_VAL_DTE > SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FWTXN_REJECT, LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B095_TRUS_HRGTAX_PRC() throws IOException,SQLException,Exception {
        B095_GET_TRUS_SETL_INF();
        if (pgmRtn) return;
        WS_RELEASE_AC = WS_TRUS_W_AC;
        if (!LNCSRPO.COMM_DATA.ACAMT[WS_X-1].STL_MTH2.equalsIgnoreCase("08) !LNCSRPO.COMM_DATA.ACAMT[WS_X-1].STL_MTH2.equalsIgnoreCase(")) {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.DATA.AGR_NO = WS_TRUS_W_AC;
            CICQACRI.FUNC = 'A';
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            WS_IDX += 1;
        }
        B095_TRUS_SETL_PROCESS();
        if (pgmRtn) return;
    }
    public void B095_TRUS_SETL_PROCESS() throws IOException,SQLException,Exception {
        WS_TX_AC_TYP = WS_TRUS_W_AC_TYP;
        WS_TX_AC = WS_TRUS_W_AC;
        WS_TX_AMT = LNCSRPO.COMM_DATA.TOT_AMT;
        B095_TX_AMT_PROC();
        if (pgmRtn) return;
    }
    public void B095_TX_AMT_PROC() throws IOException,SQLException,Exception {
        if (WS_TX_AC_TYP.equalsIgnoreCase("02")) {
            B095_TX_IA_CR_PROC();
            if (pgmRtn) return;
        } else if (WS_TX_AC_TYP.equalsIgnoreCase("03")) {
            B095_TX_IB_CR_PROC();
            if (pgmRtn) return;
        } else {
            B095_TX_DD_CR_PROC();
            if (pgmRtn) return;
        }
    }
    public void B095_GET_TRUS_SETL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRSETL);
        IBS.init(SCCGWA, LNCRSETL);
        LNCRSETL.FUNC = 'I';
        LNCRSETL.REC_PTR = LNRSETL;
        LNCRSETL.REC_LEN = 266;
        LNRSETL.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        LNRSETL.KEY.CI_TYPE = 'C';
        LNRSETL.KEY.CCY = LNCSRPO.COMM_DATA.CCY;
        LNRSETL.KEY.SETTLE_TYPE = '4';
        S000_CALL_LNZRSETL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNRSETL.AC_TYP);
        CEP.TRC(SCCGWA, LNRSETL.AC);
        WS_TRUS_W_AC_TYP = LNRSETL.AC_TYP;
        WS_TRUS_W_AC = LNRSETL.AC;
    }
    public void B020_GET_CTA_AND_PARM_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCLNQ);
        LNCCLNQ.DATA.CONT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        LNCCLNQ.DATA.SUB_CONT_NO = "" + 0;
        JIBS_tmp_int = LNCCLNQ.DATA.SUB_CONT_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCCLNQ.DATA.SUB_CONT_NO = "0" + LNCCLNQ.DATA.SUB_CONT_NO;
        LNCCLNQ.FUNC = '3';
        S000_CALL_LNZICLNQ();
        if (pgmRtn) return;
        LNCSRPO.COMM_DATA.BR = LNCCLNQ.DATA.DOMI_BR;
        LNCSRPO.COMM_DATA.CI_NO = LNCCLNQ.DATA.CI_NO;
        LNCSRPO.COMM_DATA.CI_CNM = LNCCLNQ.DATA.CI_CNAME;
        LNCSRPO.COMM_DATA.PROD_CD = LNCCLNQ.DATA.PROD_CD;
        LNCSRPO.COMM_DATA.CCY = LNCCLNQ.DATA.CCY;
        LNCSRPO.COMM_DATA.BALANCE = LNCCLNQ.DATA.TOT_BAL;
        LNCSRPO.COMM_DATA.PRINCIPAL = LNCCLNQ.DATA.PRIN;
        WS_CTL_STSW = LNCCLNQ.DATA.CTL_STSW;
        WS_FATHER_CONTRACT = LNCCLNQ.DATA.FATHER_CONTRACT;
        WS_ACR_BBR[WS_BBR_I-1] = LNCCLNQ.DATA.BOOK_BR;
        WS_BBR_I += 1;
        WS_ACR_BBR[WS_BBR_I-1] = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        WS_BBR_I += 1;
        IBS.init(SCCGWA, LNCAPRDM);
        LNCAPRDM.FUNC = '3';
        LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        S000_CALL_LNZAPRDM();
        if (pgmRtn) return;
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if (WS_CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_LOAN_CLOSE, LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if (WS_CTL_STSW.substring(51 - 1, 51 + 1 - 1).equalsIgnoreCase("1") 
            || WS_CTL_STSW.substring(53 - 1, 53 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_NOT_SUP_DIST, LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCSRPO.COMM_DATA.TR_VAL_DTE < LNCCLNQ.DATA.LAST_F_VAL_DATE) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_VALDT_GTR_LSTDT, LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B040_AC_CHECK() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= 5 
            && (LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.trim().length() != 0 
            || LNCSRPO.COMM_DATA.ACAMT[WS_I-1].REC_AC2.trim().length() != 0 
            || WS_I <= 1); WS_I += 1) {
            if (LNCSRPO.COMM_DATA.ACAMT[WS_I-1].PAY_AMT2 > 0) {
                WS_TOT_AMT2 += LNCSRPO.COMM_DATA.ACAMT[WS_I-1].PAY_AMT2;
            }
            B103_AC_ROUTER_PROC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2);
            if (LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase("01) LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase(")
                || LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase("02) LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase(")
                || LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase("03) LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase(")
                || LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase("05) LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase(")
                || LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase("06) LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase(")) {
                if (LNCSRPO.COMM_DATA.ACAMT[WS_I-1].REC_AC2.trim().length() == 0) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCSRPO.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                if (LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase("02) LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase(")) {
                    B103_CHECK_IA_AC();
                    if (pgmRtn) return;
                }
            } else if (LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase("04) LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase(")) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_NO_GDA_REY, LNCSRPO.RC);
                Z_RET();
                if (pgmRtn) return;
            } else if (LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase("07) LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase(")) {
                if (LNCSRPO.COMM_DATA.ACAMT[WS_I-1].REC_AC2.trim().length() == 0 
                    || LNCSRPO.COMM_DATA.ACAMT[WS_I-1].CHQ_NO2.trim().length() == 0 
                    || LNCSRPO.COMM_DATA.ACAMT[WS_I-1].CHQ_TYPE2 == ' ' 
                    || LNCSRPO.COMM_DATA.ACAMT[WS_I-1].ISS_DATE2 == 0) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCSRPO.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            } else if (LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase("08) LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase(")) {
            } else {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.SET_MET_NOT_RIGHT, LNCSRPO.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (WS_TOT_AMT2 != 0 
            && WS_TOT_AMT2 != LNCSRPO.COMM_DATA.TOT_AMT 
            && !SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_TOT_AMT2, LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B103_AC_ROUTER_PROC() throws IOException,SQLException,Exception {
        if (LNCSRPO.COMM_DATA.ACAMT[WS_I-1].AC_FLG2 == ' ') {
            LNCSRPO.COMM_DATA.ACAMT[WS_I-1].AC_FLG2 = '0';
        }
        WS_AC_FLG = LNCSRPO.COMM_DATA.ACAMT[WS_I-1].AC_FLG2;
        if (WS_AC_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_NO_THEIR_FLG, LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCSRPO.COMM_DATA.ACAMT[WS_I-1].REC_AC2);
        if (LNCSRPO.COMM_DATA.ACAMT[WS_I-1].REC_AC2.trim().length() > 0) {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.DATA.AGR_NO = LNCSRPO.COMM_DATA.ACAMT[WS_I-1].REC_AC2;
            CICQACRI.FUNC = 'A';
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            WS_BBR_I += 1;
            B101_GET_AC_TYPE();
            if (pgmRtn) return;
            if (WS_SETL_INFO.WS_FUND_AC_TYP.equalsIgnoreCase(K_DD_AC) 
                && LNCSRPO.COMM_DATA.ACAMT[WS_I-1].CHQ_NO2.trim().length() > 0) {
                WS_SETL_INFO.WS_FUND_AC_TYP = K_CHEQUE;
            }
            if (LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.trim().length() > 0 
                && !LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase(WS_SETL_INFO.WS_FUND_AC_TYP)) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_REC_AC_TYP, LNCSRPO.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, WS_SETL_INFO.WS_FUND_AC_TYP);
            CEP.TRC(SCCGWA, LNCSRPO.COMM_DATA.CCY);
            LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2 = WS_SETL_INFO.WS_FUND_AC_TYP;
        }
    }
    public void B101_GET_AC_TYPE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("IB")) {
            WS_SETL_INFO.WS_FUND_AC_TYP = K_IB_AC;
        } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
            WS_SETL_INFO.WS_FUND_AC_TYP = K_DD_AC;
        } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
            WS_SETL_INFO.WS_FUND_AC_TYP = K_DC_AC;
        } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
            WS_SETL_INFO.WS_FUND_AC_TYP = K_INTERNAL;
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_AC_TYP_NOT_MATCH, LNCSRPO.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B102_GET_AC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICPQIA);
        AICPQIA.SIGN = 'D';
        AICPQIA.CD.BUSI_KND = "LNPYSUS";
        AICPQIA.BR = LNCCLNQ.DATA.BOOK_BR;
        AICPQIA.CCY = LNCSRPO.COMM_DATA.CCY;
        S000_CALL_AIZPQIA();
        if (pgmRtn) return;
        LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2 = K_INTERNAL;
        LNCSRPO.COMM_DATA.ACAMT[WS_I-1].REC_AC2 = AICPQIA.AC;
        CEP.TRC(SCCGWA, AICPQIA.AC);
    }
    public void B103_CHECK_IA_AC() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, LNCSRPO.COMM_DATA.ACAMT[WS_I-1].REC_AC2, WS_IA_AC);
        IBS.init(SCCGWA, AICPQITM);
        AICPQITM.INPUT_DATA.COA_FLG = "1";
        AICPQITM.INPUT_DATA.NO = WS_IA_AC.WS_IA_AC_KEMU;
        S000_CALL_AIZPQITM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICPQITM.OUTPUT_DATA.TYPE);
        if (AICPQITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("6")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_IA_AC_OUT, LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B040_LOGIC_CHECK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCSRPO.COMM_DATA.CUR_TRM);
        if (LNCSRPO.COMM_DATA.CUR_TRM == 'Y') {
            B041_CURRENT_TERM_CHECK();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, LNRRCVD);
            LNRRCVD.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
            LNRRCVD.KEY.SUB_CTA_NO = 0;
            LNTRCVD_RD = new DBParm();
            LNTRCVD_RD.TableName = "LNTRCVD";
            LNTRCVD_RD.eqWhere = "CONTRACT_NO,SUB_CTA_NO";
            LNTRCVD_RD.where = "REPY_STS < > '2'";
            LNTRCVD_RD.fst = true;
            IBS.READ(SCCGWA, LNRRCVD, this, LNTRCVD_RD);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_E_CUR_PAY, LNCSRPO.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B050_GET_REC_AMT_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
        } else {
            B040_RECALL_LNZSRPC();
            if (pgmRtn) return;
            B040_SUB_INFO();
            if (pgmRtn) return;
        }
    }
    public void B040_RECALL_LNZSRPC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCSRPO.COMM_DATA.TOT_AMT);
        IBS.init(SCCGWA, LNCSRPC);
        LNCSRPC.FUNC_CODE = 'U';
        LNCSRPC.COMM_DATA.CTA_NO = LNCSRPO.COMM_DATA.CTA_NO;
        LNCSRPC.COMM_DATA.TR_VAL_DTE = LNCSRPO.COMM_DATA.TR_VAL_DTE;
        LNCSRPC.COMM_DATA.SUBS_FLG = LNCSRPO.COMM_DATA.SUBS_FLG;
        LNCSRPC.COMM_DATA.TOT_AMT = LNCSRPO.COMM_DATA.TOT_AMT;
        LNCSRPC.COMM_DATA.TOT_P_AMT = LNCSRPO.COMM_DATA.TOT_P_AMT;
        LNCSRPC.COMM_DATA.TOT_I_AMT = LNCSRPO.COMM_DATA.TOT_I_AMT;
        LNCSRPC.COMM_DATA.TOT_O_AMT = LNCSRPO.COMM_DATA.TOT_O_AMT;
        LNCSRPC.COMM_DATA.TOT_L_AMT = LNCSRPO.COMM_DATA.TOT_L_AMT;
        LNCSRPC.COMM_DATA.HRG_AMT = LNCSRPO.COMM_DATA.HRG_AMT;
        LNCSRPC.COMM_DATA.CUR_TRM = LNCSRPO.COMM_DATA.CUR_TRM;
        S000_CALL_LNZSRPC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCSRPC.COMM_DATA.TOT_DTL_AMT);
        CEP.TRC(SCCGWA, LNCSRPC.COMM_DATA.TOT_AMT);
        CEP.TRC(SCCGWA, LNCSRPC.COMM_DATA.TOT_P_AMT);
        CEP.TRC(SCCGWA, LNCSRPC.COMM_DATA.TOT_I_AMT);
        CEP.TRC(SCCGWA, LNCSRPC.COMM_DATA.TOT_O_AMT);
        CEP.TRC(SCCGWA, LNCSRPC.COMM_DATA.TOT_L_AMT);
        CEP.TRC(SCCGWA, LNCSRPC.COMM_DATA.HRG_AMT);
        if (LNCSRPC.COMM_DATA.TOT_DTL_AMT < LNCSRPO.COMM_DATA.TOT_AMT) {
            LNCSRPO.COMM_DATA.TOT_AMT = LNCSRPC.COMM_DATA.TOT_DTL_AMT;
        } else {
            LNCSRPO.COMM_DATA.TOT_AMT = LNCSRPC.COMM_DATA.TOT_AMT;
        }
        WS_TOT_P_UDAMT = LNCSRPC.COMM_DATA.TOT_P_UDAMT;
        LNCSRPO.COMM_DATA.TOT_P_AMT = LNCSRPC.COMM_DATA.TOT_P_AMT;
        LNCSRPO.COMM_DATA.TOT_I_AMT = LNCSRPC.COMM_DATA.TOT_I_AMT;
        LNCSRPO.COMM_DATA.TOT_O_AMT = LNCSRPC.COMM_DATA.TOT_O_AMT;
        LNCSRPO.COMM_DATA.TOT_L_AMT = LNCSRPC.COMM_DATA.TOT_L_AMT;
        LNCSRPO.COMM_DATA.HRG_AMT = LNCSRPC.COMM_DATA.HRG_AMT;
        WS_TOT_D_AMT = LNCSRPO.COMM_DATA.TOT_P_AMT + LNCSRPO.COMM_DATA.TOT_I_AMT + LNCSRPO.COMM_DATA.TOT_O_AMT + LNCSRPO.COMM_DATA.TOT_L_AMT;
        CEP.TRC(SCCGWA, "111");
        CEP.TRC(SCCGWA, LNCSRPO.COMM_DATA.PART_DATA[1-1].SUB_C_NO);
    }
    public void B040_SUB_INFO() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= 10 
            && LNCSRPO.COMM_DATA.PART_DATA[WS_I-1].SUB_C_NO.trim().length() != 0; WS_I += 1) {
            LNCSRPO.COMM_DATA.PART_DATA[WS_I-1].SUB_C_NO = LNRPARS.SUB_LN_AC;
            LNCSRPO.COMM_DATA.PART_DATA[WS_I-1].BBR = LNRPARS.BOOK_BR;
            LNCSRPO.COMM_DATA.PART_DATA[WS_I-1].SEQ_NO = "" + LNRPARS.KEY.SEQ_NO;
            JIBS_tmp_int = LNCSRPO.COMM_DATA.PART_DATA[WS_I-1].SEQ_NO.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) LNCSRPO.COMM_DATA.PART_DATA[WS_I-1].SEQ_NO = "0" + LNCSRPO.COMM_DATA.PART_DATA[WS_I-1].SEQ_NO;
            LNCSRPO.COMM_DATA.PART_DATA[WS_I-1].REL_TYP = LNRPARS.REL_TYPE;
            LNCSRPO.COMM_DATA.PART_DATA[WS_I-1].PY_P_AMT = LNCSRPO.COMM_DATA.TOT_P_AMT * LNRPARS.PARTI_PCT / 100;
            LNCSRPO.COMM_DATA.PART_DATA[WS_I-1].PY_I_AMT = LNCSRPO.COMM_DATA.TOT_I_AMT * LNRPARS.PARTI_PCT / 100;
            LNCSRPO.COMM_DATA.PART_DATA[WS_I-1].PY_O_AMT = LNCSRPO.COMM_DATA.TOT_O_AMT * LNRPARS.PARTI_PCT / 100;
            LNCSRPO.COMM_DATA.PART_DATA[WS_I-1].PY_L_AMT = LNCSRPO.COMM_DATA.TOT_L_AMT * LNRPARS.PARTI_PCT / 100;
        }
    }
    public void B040_RECALL_LNZSRPC_END() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSRPC);
        LNCSRPC.FUNC_CODE = 'U';
        LNCSRPC.COMM_DATA.CTA_NO = LNCSRPO.COMM_DATA.CTA_NO;
        LNCSRPC.COMM_DATA.TR_VAL_DTE = LNCSRPO.COMM_DATA.TR_VAL_DTE;
        LNCSRPC.COMM_DATA.SUBS_FLG = LNCSRPO.COMM_DATA.SUBS_FLG;
        CEP.TRC(SCCGWA, WS_KOU_TOT_AMT);
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if (WS_KOU_TOT_AMT == 0 
            && WS_CTL_STSW.substring(54 - 1, 54 + 1 - 1).equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_AC_AMT_ZERO, LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (WS_KOU_TOT_AMT < LNCSRPO.COMM_DATA.TOT_AMT) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LESS_AC_AMT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        LNCSRPC.COMM_DATA.TOT_AMT = WS_KOU_TOT_AMT;
        LNCSRPC.COMM_DATA.CUR_TRM = LNCSRPO.COMM_DATA.CUR_TRM;
        if (WS_KOU_TOT_AMT > 0) {
            S000_CALL_LNZSRPC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCSRPC.COMM_DATA.TOT_DTL_AMT);
        CEP.TRC(SCCGWA, LNCSRPC.COMM_DATA.TOT_AMT);
        CEP.TRC(SCCGWA, LNCSRPC.COMM_DATA.TOT_P_AMT);
        CEP.TRC(SCCGWA, LNCSRPC.COMM_DATA.TOT_I_AMT);
        CEP.TRC(SCCGWA, LNCSRPC.COMM_DATA.TOT_O_AMT);
        CEP.TRC(SCCGWA, LNCSRPC.COMM_DATA.TOT_L_AMT);
        CEP.TRC(SCCGWA, LNCSRPC.COMM_DATA.HRG_AMT);
        CEP.TRC(SCCGWA, LNCSRPC.COMM_DATA.APT_REF);
        LNCSRPO.COMM_DATA.TOT_AMT = LNCSRPC.COMM_DATA.TOT_AMT;
        CEP.TRC(SCCGWA, LNCSRPC.COMM_DATA.TOT_AMT);
        CEP.TRC(SCCGWA, LNCSRPO.COMM_DATA.TOT_AMT);
        LNCSRPO.COMM_DATA.TOT_P_AMT = LNCSRPC.COMM_DATA.TOT_P_AMT;
        WS_TOT_P_UDAMT = LNCSRPC.COMM_DATA.TOT_P_UDAMT;
        LNCSRPO.COMM_DATA.TOT_I_AMT = LNCSRPC.COMM_DATA.TOT_I_AMT;
        LNCSRPO.COMM_DATA.TOT_O_AMT = LNCSRPC.COMM_DATA.TOT_O_AMT;
        LNCSRPO.COMM_DATA.TOT_L_AMT = LNCSRPC.COMM_DATA.TOT_L_AMT;
        LNCSRPO.COMM_DATA.HRG_AMT = LNCSRPC.COMM_DATA.HRG_AMT;
        WS_TOT_D_AMT = LNCSRPO.COMM_DATA.TOT_P_AMT + LNCSRPO.COMM_DATA.TOT_I_AMT + LNCSRPO.COMM_DATA.TOT_O_AMT + LNCSRPO.COMM_DATA.TOT_L_AMT;
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if (WS_CTL_STSW.substring(54 - 1, 54 + 1 - 1).equalsIgnoreCase("1")) {
            WS_TOT_D_AMT += LNCSRPO.COMM_DATA.HRG_AMT;
        }
        if (LNCSRPO.COMM_DATA.TOT_AMT != 0 
            && WS_TOT_D_AMT != LNCSRPO.COMM_DATA.TOT_AMT) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_AMT_UNMATCH, LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCSRPO.COMM_DATA.TOT_AMT == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_P_AMT, LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B060_SOLD_OUT_LOAN_PROC() throws IOException,SQLException,Exception {
        WS_PI_PROC_FLG = ' ';
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if (WS_CTL_STSW.substring(37 - 1, 37 + 1 - 1).equalsIgnoreCase("1") 
            && LNCSRPO.COMM_DATA.TOT_P_AMT > 0) {
            WS_PI_PROC_FLG = 'P';
            B061_SOLD_OUT_P_PROC();
            if (pgmRtn) return;
        }
        WS_PI_PROC_FLG = 'I';
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if (WS_CTL_STSW.substring(38 - 1, 38 + 1 - 1).equalsIgnoreCase("1")) {
            B062_SOLD_OUT_I_PROC();
            if (pgmRtn) return;
        } else {
            B063_PURCHASE_BACK_I_PROC();
            if (pgmRtn) return;
        }
        B700_WRITE_B_R_INTTRAN();
        if (pgmRtn) return;
    }
    public void B061_SOLD_OUT_P_PROC() throws IOException,SQLException,Exception {
        B162_SOLD_REPAY_OUT_PROC();
        if (pgmRtn) return;
    }
    public void B062_SOLD_OUT_I_PROC() throws IOException,SQLException,Exception {
        WS_I_LEFT_AMT = LNCSRPO.COMM_DATA.TOT_I_AMT;
        WS_O_LEFT_AMT = LNCSRPO.COMM_DATA.TOT_O_AMT;
        WS_L_LEFT_AMT = LNCSRPO.COMM_DATA.TOT_L_AMT;
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if (WS_CTL_STSW.substring(45 - 1, 45 + 1 - 1).equalsIgnoreCase("1") 
            && (WS_I_LEFT_AMT > 0 
            || WS_O_LEFT_AMT > 0 
            || WS_L_LEFT_AMT > 0)) {
            B161_PURBACK_OWN_OUT_I_PROC();
            if (pgmRtn) return;
            WS_I_LEFT_AMT -= WS_PAY_R_I_AMT;
            WS_O_LEFT_AMT -= WS_PAY_R_O_AMT;
            WS_L_LEFT_AMT -= WS_PAY_R_L_AMT;
        }
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if (WS_CTL_STSW.substring(44 - 1, 44 + 1 - 1).equalsIgnoreCase("1") 
            && (WS_I_LEFT_AMT > 0 
            || WS_O_LEFT_AMT > 0 
            || WS_L_LEFT_AMT > 0)) {
            B160_SOLD_OWN_US_I_PROC();
            if (pgmRtn) return;
            WS_I_LEFT_AMT -= WS_PAY_R_I_AMT;
            WS_O_LEFT_AMT -= WS_PAY_R_O_AMT;
            WS_L_LEFT_AMT -= WS_PAY_R_L_AMT;
        }
        if (WS_I_LEFT_AMT > 0 
            || WS_O_LEFT_AMT > 0 
            || WS_L_LEFT_AMT > 0) {
            WS_PAY_R_I_AMT = WS_I_LEFT_AMT;
            WS_PAY_R_O_AMT = WS_O_LEFT_AMT;
            WS_PAY_R_L_AMT = WS_L_LEFT_AMT;
            B162_SOLD_REPAY_OUT_PROC();
            if (pgmRtn) return;
        }
    }
    public void B063_PURCHASE_BACK_I_PROC() throws IOException,SQLException,Exception {
        WS_I_LEFT_AMT = LNCSRPO.COMM_DATA.TOT_I_AMT;
        WS_O_LEFT_AMT = LNCSRPO.COMM_DATA.TOT_O_AMT;
        WS_L_LEFT_AMT = LNCSRPO.COMM_DATA.TOT_L_AMT;
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if (WS_CTL_STSW.substring(44 - 1, 44 + 1 - 1).equalsIgnoreCase("1") 
            && (WS_I_LEFT_AMT > 0 
            || WS_O_LEFT_AMT > 0 
            || WS_L_LEFT_AMT > 0)) {
            B160_SOLD_OWN_US_I_PROC();
            if (pgmRtn) return;
            WS_I_LEFT_AMT -= WS_PAY_R_I_AMT;
            WS_O_LEFT_AMT -= WS_PAY_R_O_AMT;
            WS_L_LEFT_AMT -= WS_PAY_R_L_AMT;
        }
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if (WS_CTL_STSW.substring(45 - 1, 45 + 1 - 1).equalsIgnoreCase("1") 
            && (WS_I_LEFT_AMT > 0 
            || WS_O_LEFT_AMT > 0 
            || WS_L_LEFT_AMT > 0)) {
            B161_PURBACK_OWN_OUT_I_PROC();
            if (pgmRtn) return;
        }
    }
    public void B064_UPDATE_LNTCTPY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCTPY);
        LNRCTPY.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        LNRCTPY.KEY.TR_TYP = WS_CTPY_TR_TYP;
        T000_READ_LNTCTPY1();
        if (pgmRtn) return;
        T000_READ_UPDATE_LNTCTPY();
        if (pgmRtn) return;
        WS_O_I_AMT = LNRCTPY.I_REC_AMT - LNRCTPY.I_PAY_AMT;
        WS_O_O_AMT = LNRCTPY.O_REC_AMT - LNRCTPY.O_PAY_AMT;
        WS_O_L_AMT = LNRCTPY.L_REC_AMT - LNRCTPY.L_PAY_AMT;
        if (WS_O_I_AMT < 0) {
            WS_O_I_AMT = 0;
        }
        if (WS_O_O_AMT < 0) {
            WS_O_O_AMT = 0;
        }
        if (WS_O_L_AMT < 0) {
            WS_O_L_AMT = 0;
        }
        if (WS_I_LEFT_AMT < 0) {
            WS_I_LEFT_AMT = 0;
        }
        if (WS_O_LEFT_AMT < 0) {
            WS_O_LEFT_AMT = 0;
        }
        if (WS_L_LEFT_AMT < 0) {
            WS_L_LEFT_AMT = 0;
        }
        if (WS_I_LEFT_AMT >= WS_O_I_AMT) {
            WS_PAY_R_I_AMT = WS_O_I_AMT;
        } else {
            WS_PAY_R_I_AMT = WS_I_LEFT_AMT;
        }
        if (WS_O_LEFT_AMT >= WS_O_O_AMT) {
            WS_PAY_R_O_AMT = WS_O_O_AMT;
        } else {
            WS_PAY_R_O_AMT = WS_O_LEFT_AMT;
        }
        if (WS_L_LEFT_AMT >= WS_O_L_AMT) {
            WS_PAY_R_L_AMT = WS_O_L_AMT;
        } else {
            WS_PAY_R_L_AMT = WS_L_LEFT_AMT;
        }
        LNRCTPY.I_PAY_AMT += WS_PAY_R_I_AMT;
        LNRCTPY.O_PAY_AMT += WS_PAY_R_O_AMT;
        LNRCTPY.L_PAY_AMT += WS_PAY_R_L_AMT;
        T000_REWRITE_LNTCTPY();
        if (pgmRtn) return;
    }
    public void B065_UPD_PACK_AND_CR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPACK);
        LNRPACK.KEY.BTH_PK = LNRCTPY.KEY.BTH_PK;
        T000_READ_UPDATE_LNTPACK();
        if (pgmRtn) return;
        if (WS_PI_PROC_FLG == 'P') {
            WS_TX_AC_TYP = LNRPACK.P_AC_TYP;
            WS_TX_AC = LNRPACK.P_AC;
            WS_TX_AMT = LNCSRPO.COMM_DATA.TOT_P_AMT;
            B095_TX_AMT_CR_PROC();
            if (pgmRtn) return;
            LNRPACK.TOT_AMT += LNCSRPO.COMM_DATA.TOT_P_AMT;
            LNRPACK.T_PRIN_AMT += LNCSRPO.COMM_DATA.TOT_P_AMT;
        }
        if (WS_PI_PROC_FLG == 'I') {
            WS_TX_AC_TYP = LNRPACK.I_AC_TYP;
            WS_TX_AC = LNRPACK.I_AC;
            WS_TX_AMT = WS_PAY_R_I_AMT;
            WS_TX_AMT += WS_PAY_R_O_AMT;
            WS_TX_AMT += WS_PAY_R_L_AMT;
            B095_TX_AMT_CR_PROC();
            if (pgmRtn) return;
            LNRPACK.TOT_AMT += WS_PAY_R_I_AMT;
            LNRPACK.TOT_AMT += WS_PAY_R_O_AMT;
            LNRPACK.TOT_AMT += WS_PAY_R_L_AMT;
            LNRPACK.T_INT_AMT += WS_PAY_R_I_AMT;
            LNRPACK.T_O_INT += WS_PAY_R_O_AMT;
            LNRPACK.T_L_INT += WS_PAY_R_L_AMT;
        }
        T000_REWRITE_LNTPACK();
        if (pgmRtn) return;
    }
    public void B066_UPDATE_OR_WRITE_PLDT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPLDT);
        LNRPLDT.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        LNRPLDT.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRPLDT.KEY.BTH_PK = LNRPACK.KEY.BTH_PK;
        T000_READ_UPDATE_LNTPLDT();
        if (pgmRtn) return;
        if (WS_READ_LNTPLDT_FLG == 'F') {
            if (WS_PI_PROC_FLG == 'P') {
                LNRPLDT.T_PAY_AMT += LNCSRPO.COMM_DATA.TOT_P_AMT;
                LNRPLDT.PAY_PRIN += LNCSRPO.COMM_DATA.TOT_P_AMT;
            }
            if (WS_PI_PROC_FLG == 'I') {
                LNRPLDT.T_PAY_AMT += WS_PAY_R_I_AMT;
                LNRPLDT.T_PAY_AMT += WS_PAY_R_O_AMT;
                LNRPLDT.T_PAY_AMT += WS_PAY_R_L_AMT;
                LNRPLDT.PAY_INT += WS_PAY_R_I_AMT;
                LNRPLDT.PAY_O_INT += WS_PAY_R_O_AMT;
                LNRPLDT.PAY_L_INT += WS_PAY_R_L_AMT;
            }
            T000_REWRITE_LNTPLDT();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, LNRPLDT);
            LNRPLDT.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
            LNRPLDT.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
            LNRPLDT.KEY.BTH_PK = LNRPACK.KEY.BTH_PK;
            LNRPLDT.CCY = LNCSRPO.COMM_DATA.CCY;
            if (WS_PI_PROC_FLG == 'P') {
                LNRPLDT.T_PAY_AMT = LNCSRPO.COMM_DATA.TOT_P_AMT;
                LNRPLDT.PAY_PRIN = LNCSRPO.COMM_DATA.TOT_P_AMT;
                LNRPLDT.PAY_INT = 0;
                LNRPLDT.PAY_O_INT = 0;
                LNRPLDT.PAY_L_INT = 0;
            }
            if (WS_PI_PROC_FLG == 'I') {
                LNRPLDT.T_PAY_AMT = WS_PAY_R_I_AMT;
                LNRPLDT.T_PAY_AMT += WS_PAY_R_O_AMT;
                LNRPLDT.T_PAY_AMT += WS_PAY_R_L_AMT;
                LNRPLDT.PAY_PRIN = 0;
                LNRPLDT.PAY_INT = WS_PAY_R_I_AMT;
                LNRPLDT.PAY_O_INT = WS_PAY_R_O_AMT;
                LNRPLDT.PAY_L_INT = WS_PAY_R_L_AMT;
            }
            T000_WRITE_LNTPLDT();
            if (pgmRtn) return;
        }
    }
    public void B160_SOLD_OWN_US_I_PROC() throws IOException,SQLException,Exception {
        WS_CTPY_TR_TYP = '1';
        B064_UPDATE_LNTCTPY_PROC();
        if (pgmRtn) return;
        WS_B_I_TEXT.WS_B_I_AMT = WS_PAY_R_I_AMT;
        WS_B_I_TEXT.WS_B_O_AMT = WS_PAY_R_O_AMT;
        WS_B_I_TEXT.WS_B_L_AMT = WS_PAY_R_L_AMT;
        if (LNRCTPY.I_PAY_AMT >= LNRCTPY.I_REC_AMT 
            && LNRCTPY.O_PAY_AMT >= LNRCTPY.O_REC_AMT 
            && LNRCTPY.L_PAY_AMT >= LNRCTPY.L_REC_AMT) {
            WS_UPD_STSW44_0_FLG = 'Y';
            WS_UPD_ICTL_FLG = 'Y';
        }
    }
    public void B161_PURBACK_OWN_OUT_I_PROC() throws IOException,SQLException,Exception {
        WS_CTPY_TR_TYP = '2';
        B064_UPDATE_LNTCTPY_PROC();
        if (pgmRtn) return;
        WS_R_I_TEXT.WS_R_I_AMT = WS_PAY_R_I_AMT;
        WS_R_I_TEXT.WS_R_O_AMT = WS_PAY_R_O_AMT;
        WS_R_I_TEXT.WS_R_L_AMT = WS_PAY_R_L_AMT;
        if (WS_PAY_R_I_AMT > 0 
            || WS_PAY_R_O_AMT > 0 
            || WS_PAY_R_L_AMT > 0) {
            B065_UPD_PACK_AND_CR_PROC();
            if (pgmRtn) return;
            B066_UPDATE_OR_WRITE_PLDT();
            if (pgmRtn) return;
        }
        if (LNRCTPY.I_PAY_AMT >= LNRCTPY.I_REC_AMT 
            && LNRCTPY.O_PAY_AMT >= LNRCTPY.O_REC_AMT 
            && LNRCTPY.L_PAY_AMT >= LNRCTPY.L_REC_AMT) {
            WS_UPD_STSW45_0_FLG = 'Y';
            WS_UPD_ICTL_FLG = 'Y';
        }
    }
    public void B162_SOLD_REPAY_OUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCTPY);
        LNRCTPY.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        LNRCTPY.KEY.TR_TYP = '1';
        T000_READ_LNTCTPY1();
        if (pgmRtn) return;
        B065_UPD_PACK_AND_CR_PROC();
        if (pgmRtn) return;
        B066_UPDATE_OR_WRITE_PLDT();
        if (pgmRtn) return;
    }
    public void B700_WRITE_B_R_INTTRAN() throws IOException,SQLException,Exception {
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if (WS_CTL_STSW.substring(38 - 1, 38 + 1 - 1).equalsIgnoreCase("1")) {
            WS_B_I_TRAN_AMT = WS_B_I_TEXT.WS_B_I_AMT;
            WS_B_O_TRAN_AMT = WS_B_I_TEXT.WS_B_O_AMT;
            WS_B_L_TRAN_AMT = WS_B_I_TEXT.WS_B_L_AMT;
            CEP.TRC(SCCGWA, "SOLDOUT");
            WS_R_I_TRAN_AMT = LNCSRPO.COMM_DATA.TOT_I_AMT - WS_B_I_TEXT.WS_B_I_AMT;
            WS_R_O_TRAN_AMT = LNCSRPO.COMM_DATA.TOT_O_AMT - WS_B_I_TEXT.WS_B_O_AMT;
            WS_R_L_TRAN_AMT = LNCSRPO.COMM_DATA.TOT_L_AMT - WS_B_I_TEXT.WS_B_L_AMT;
        } else {
            WS_R_I_TRAN_AMT = WS_R_I_TEXT.WS_R_I_AMT;
            WS_R_O_TRAN_AMT = WS_R_I_TEXT.WS_R_O_AMT;
            WS_R_L_TRAN_AMT = WS_R_I_TEXT.WS_R_L_AMT;
            CEP.TRC(SCCGWA, "PURBACK");
            WS_B_I_TRAN_AMT = LNCSRPO.COMM_DATA.TOT_I_AMT - WS_R_I_TEXT.WS_R_I_AMT;
            WS_B_O_TRAN_AMT = LNCSRPO.COMM_DATA.TOT_O_AMT - WS_R_I_TEXT.WS_R_O_AMT;
            WS_B_L_TRAN_AMT = LNCSRPO.COMM_DATA.TOT_L_AMT - WS_R_I_TEXT.WS_R_L_AMT;
        }
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if ((WS_B_I_TRAN_AMT > 0 
            || WS_B_O_TRAN_AMT > 0 
            || WS_B_L_TRAN_AMT > 0) 
            && (WS_CTL_STSW.substring(44 - 1, 44 + 1 - 1).equalsIgnoreCase("1") 
            || WS_CTL_STSW.substring(45 - 1, 45 + 1 - 1).equalsIgnoreCase("1"))) {
            CEP.TRC(SCCGWA, "B-TRAN");
            CEP.TRC(SCCGWA, WS_B_I_TEXT.WS_B_I_AMT);
            CEP.TRC(SCCGWA, WS_B_I_TEXT.WS_B_O_AMT);
            CEP.TRC(SCCGWA, WS_B_I_TEXT.WS_B_L_AMT);
            CEP.TRC(SCCGWA, WS_B_I_TRAN_AMT);
            CEP.TRC(SCCGWA, WS_B_O_TRAN_AMT);
            CEP.TRC(SCCGWA, WS_B_L_TRAN_AMT);
            WS_TYPE = 'I';
            WS_REC_FLAG = 'I';
            WS_I_AMT = WS_B_I_TRAN_AMT;
            WS_O_AMT = WS_B_O_TRAN_AMT;
            WS_L_AMT = WS_B_L_TRAN_AMT;
            WS_B_I_LENGTH = 480;
            if (WS_TR_DATA == null) WS_TR_DATA = "";
            JIBS_tmp_int = WS_TR_DATA.length();
            for (int i=0;i<1024-JIBS_tmp_int;i++) WS_TR_DATA += " ";
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_B_I_TEXT);
            WS_TR_DATA = JIBS_tmp_str[0] + WS_TR_DATA.substring(WS_B_I_LENGTH);
            B701_TRAN_ADD_PROCESS();
            if (pgmRtn) return;
        }
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if ((WS_R_I_TRAN_AMT > 0 
            || WS_R_O_TRAN_AMT > 0 
            || WS_R_L_TRAN_AMT > 0) 
            && (WS_CTL_STSW.substring(44 - 1, 44 + 1 - 1).equalsIgnoreCase("1") 
            || WS_CTL_STSW.substring(45 - 1, 45 + 1 - 1).equalsIgnoreCase("1"))) {
            CEP.TRC(SCCGWA, "R-TRAN");
            CEP.TRC(SCCGWA, WS_R_I_TEXT.WS_R_I_AMT);
            CEP.TRC(SCCGWA, WS_R_I_TEXT.WS_R_O_AMT);
            CEP.TRC(SCCGWA, WS_R_I_TEXT.WS_R_L_AMT);
            CEP.TRC(SCCGWA, WS_R_I_TRAN_AMT);
            CEP.TRC(SCCGWA, WS_R_O_TRAN_AMT);
            CEP.TRC(SCCGWA, WS_R_L_TRAN_AMT);
            WS_TYPE = 'I';
            WS_REC_FLAG = 'O';
            WS_I_AMT = WS_R_I_TRAN_AMT;
            WS_O_AMT = WS_R_O_TRAN_AMT;
            WS_L_AMT = WS_R_L_TRAN_AMT;
            WS_R_I_LENGTH = 480;
            if (WS_TR_DATA == null) WS_TR_DATA = "";
            JIBS_tmp_int = WS_TR_DATA.length();
            for (int i=0;i<1024-JIBS_tmp_int;i++) WS_TR_DATA += " ";
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_R_I_TEXT);
            WS_TR_DATA = JIBS_tmp_str[0] + WS_TR_DATA.substring(WS_R_I_LENGTH);
            B701_TRAN_ADD_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void B701_TRAN_ADD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCTRANM);
        LNCTRANM.FUNC = '0';
        LNCTRANM.REC_DATA.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        LNCTRANM.REC_DATA.KEY.SUB_CTA_NO = 0;
        LNCTRANM.REC_DATA.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCTRANM.REC_DATA.KEY.TR_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        LNCTRANM.REC_DATA.KEY.TXN_TYP = WS_TYPE;
        LNCTRANM.REC_DATA.KEY.TRAN_FLG = 'C';
        LNCTRANM.REC_DATA.TR_CODE = SCCGWA.COMM_AREA.TR_ID.TR_CODE;
        LNCTRANM.REC_DATA.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        LNCTRANM.REC_DATA.TR_DATA = WS_TR_DATA;
        LNCTRANM.REC_DATA.I_AMT = WS_I_AMT;
        LNCTRANM.REC_DATA.O_AMT = WS_O_AMT;
        LNCTRANM.REC_DATA.L_AMT = WS_L_AMT;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            LNCTRANM.REC_DATA.TRAN_STS = 'N';
        } else {
            LNCTRANM.REC_DATA.TRAN_STS = 'R';
        }
        LNCTRANM.REC_DATA.TR_VAL_DTE = LNCSRPO.COMM_DATA.TR_VAL_DTE;
        LNCTRANM.REC_DATA.LOAN_STSW = WS_CTL_STSW;
        LNCTRANM.REC_DATA.KEY.REC_FLAG = WS_REC_FLAG;
        LNCTRANM.REC_DATA.REQ_FRM_NO = SCCGWA.COMM_AREA.REQ_SYSTEM;
        S000_CALL_LNZTRANM();
        if (pgmRtn) return;
    }
    public void B070_CUST_REPAY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCURPN);
        LNCURPN.COMM_DATA.ACM_EVENT = "RLN";
        LNCURPN.COMM_DATA.LN_AC = LNCSRPO.COMM_DATA.CTA_NO;
        LNCURPN.COMM_DATA.SUF_NO = "" + 0;
        JIBS_tmp_int = LNCURPN.COMM_DATA.SUF_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCURPN.COMM_DATA.SUF_NO = "0" + LNCURPN.COMM_DATA.SUF_NO;
        LNCURPN.COMM_DATA.SUBS_FLG = LNCSRPO.COMM_DATA.SUBS_FLG;
        LNCURPN.COMM_DATA.TR_VAL_DATE = LNCSRPO.COMM_DATA.TR_VAL_DTE;
        LNCURPN.COMM_DATA.TOT_D_AMTS.TOT_D_AMT = LNCSRPO.COMM_DATA.TOT_AMT;
        LNCURPN.COMM_DATA.TOT_D_AMTS.TOT_D_P_AMT = LNCSRPO.COMM_DATA.TOT_P_AMT;
        LNCURPN.COMM_DATA.TOT_D_AMTS.TOT_D_P_UDAMT = WS_TOT_P_UDAMT;
        LNCURPN.COMM_DATA.TOT_D_AMTS.TOT_D_I_AMT = LNCSRPO.COMM_DATA.TOT_I_AMT;
        LNCURPN.COMM_DATA.TOT_D_AMTS.TOT_D_O_AMT = LNCSRPO.COMM_DATA.TOT_O_AMT;
        LNCURPN.COMM_DATA.TOT_D_AMTS.TOT_D_L_AMT = LNCSRPO.COMM_DATA.TOT_L_AMT;
        LNCURPN.COMM_DATA.APT_REF = LNCSRPC.COMM_DATA.APT_REF;
        LNCURPN.COMM_DATA.RDI_FLG = LNCSRPO.COMM_DATA.RDI_FLG;
        LNCURPN.COMM_DATA.RDI_AMT = LNCSRPO.COMM_DATA.RDI_AMT;
        LNCURPN.COMM_DATA.ADJ_TYP = LNCSRPO.COMM_DATA.ADJ_TYP;
        LNCURPN.COMM_DATA.ADJ_AC = LNCSRPO.COMM_DATA.ADJ_AC;
        LNCURPN.COMM_DATA.HRG_AMT = LNCSRPO.COMM_DATA.HRG_AMT;
        LNCURPN.COMM_DATA.CUR_TRM = LNCSRPO.COMM_DATA.CUR_TRM;
        LNCURPN.COMM_DATA.CLN_CUT = LNCSRPO.COMM_DATA.CLN_CUT;
        LNCURPN.COMM_DATA.MMO = LNCSRPO.COMM_DATA.MMO;
        WS_G = 1;
        for (WS_I = 1; WS_I <= 5; WS_I += 1) {
            if (LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.trim().length() == 0 
                && LNCSRPO.COMM_DATA.ACAMT[WS_I-1].REC_AC2.trim().length() == 0) {
                WS_I = 6;
            } else {
                LNCURPN.COMM_DATA.ACAMT[WS_I-1].STL_MTH2 = LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2;
                LNCURPN.COMM_DATA.ACAMT[WS_I-1].CHQ_NO2 = LNCSRPO.COMM_DATA.ACAMT[WS_I-1].CHQ_NO2;
                LNCURPN.COMM_DATA.ACAMT[WS_I-1].REC_AC2 = LNCSRPO.COMM_DATA.ACAMT[WS_I-1].REC_AC2;
                LNCURPN.COMM_DATA.ACAMT[WS_I-1].AC_FLG2 = LNCSRPO.COMM_DATA.ACAMT[WS_I-1].AC_FLG2;
                LNCURPN.COMM_DATA.ACAMT[WS_I-1].PAY_AMT2 = LNCSRPO.COMM_DATA.ACAMT[WS_I-1].PAY_AMT2;
                LNCURPN.COMM_DATA.ACAMT[WS_I-1].AMT_FRM2 = LNCSRPO.COMM_DATA.ACAMT[WS_I-1].AMT_FRM2;
                LNCURPN.COMM_DATA.ACAMT[WS_I-1].REC_CCY2 = LNCSRPO.COMM_DATA.CCY;
            }
        }
        CEP.TRC(SCCGWA, LNCURPN.COMM_DATA.TOT_D_AMTS.TOT_D_P_UDAMT);
        CEP.TRC(SCCGWA, LNCURPN.COMM_DATA.TOT_D_AMTS.TOT_D_I_AMT);
        CEP.TRC(SCCGWA, LNCURPN.COMM_DATA.TOT_D_AMTS.TOT_D_O_AMT);
        CEP.TRC(SCCGWA, LNCURPN.COMM_DATA.TOT_D_AMTS.TOT_D_L_AMT);
        S000_CALL_LNZURPN();
        if (pgmRtn) return;
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if (WS_CTL_STSW.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1")) {
            WS_PART_NO = 0;
            R000_READ_LNTBALZ_PART();
            if (pgmRtn) return;
            WS_OWE_P_AMT = LNRBALZ.LOAN_BALL02 + LNRBALZ.LOAN_BALL05 + LNRBALZ.LOAN_BALL06;
            WS_OWE_I_AMT = LNRBALZ.LOAN_BALL15 + LNRBALZ.LOAN_BALL20 + LNRBALZ.LOAN_BALL22;
            WS_OWE_O_AMT = LNRBALZ.LOAN_BALL42;
            WS_OWE_L_AMT = LNRBALZ.LOAN_BALL52;
            IBS.init(SCCGWA, LNCIPAMT);
            LNCIPAMT.CTA_NO = LNCSRPO.COMM_DATA.CTA_NO;
            IBS.init(SCCGWA, WS_BEF_PART_DATA);
            LNCIPAMT.P_AMT = LNRBALZ.LOAN_BALL02 + LNRBALZ.LOAN_BALL05 + LNRBALZ.LOAN_BALL06 + LNCSRPO.COMM_DATA.TOT_P_AMT;
            LNCIPAMT.I_AMT = LNRBALZ.LOAN_BALL15 + LNRBALZ.LOAN_BALL20 + LNRBALZ.LOAN_BALL22 + LNCSRPO.COMM_DATA.TOT_I_AMT;
            LNCIPAMT.O_AMT = LNRBALZ.LOAN_BALL42 + LNCSRPO.COMM_DATA.TOT_O_AMT;
            LNCIPAMT.L_AMT = LNRBALZ.LOAN_BALL52 + LNCSRPO.COMM_DATA.TOT_L_AMT;
            B321_CALL_LNZIPAMT();
            if (pgmRtn) return;
            for (WS_N = 1; WS_N <= 10 
                && LNCIPAMT.PART_DATA[WS_N-1].PART_CTA_NO.trim().length() != 0; WS_N += 1) {
                WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_N-1].WS_BEF_PART_P_AMT = LNCIPAMT.PART_DATA[WS_N-1].PART_P_AMT;
                WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_N-1].WS_BEF_PART_I_AMT = LNCIPAMT.PART_DATA[WS_N-1].PART_I_AMT;
                WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_N-1].WS_BEF_PART_O_AMT = LNCIPAMT.PART_DATA[WS_N-1].PART_O_AMT;
                WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_N-1].WS_BEF_PART_L_AMT = LNCIPAMT.PART_DATA[WS_N-1].PART_L_AMT;
                CEP.TRC(SCCGWA, WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_N-1].WS_BEF_PART_P_AMT);
                CEP.TRC(SCCGWA, WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_N-1].WS_BEF_PART_I_AMT);
                CEP.TRC(SCCGWA, WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_N-1].WS_BEF_PART_O_AMT);
                CEP.TRC(SCCGWA, WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_N-1].WS_BEF_PART_L_AMT);
            }
            IBS.init(SCCGWA, WS_AFT_PART_DATA);
            LNCIPAMT.P_AMT = WS_OWE_P_AMT;
            LNCIPAMT.I_AMT = WS_OWE_I_AMT;
            LNCIPAMT.O_AMT = WS_OWE_O_AMT;
            LNCIPAMT.L_AMT = WS_OWE_L_AMT;
            LNCIPAMT.F_AMT = WS_OWE_F_AMT;
            CEP.TRC(SCCGWA, LNCIPAMT.P_AMT);
            CEP.TRC(SCCGWA, LNCIPAMT.I_AMT);
            CEP.TRC(SCCGWA, LNCIPAMT.O_AMT);
            CEP.TRC(SCCGWA, LNCIPAMT.L_AMT);
            B321_CALL_LNZIPAMT();
            if (pgmRtn) return;
            for (WS_N = 1; WS_N <= 10 
                && LNCIPAMT.PART_DATA[WS_N-1].PART_CTA_NO.trim().length() != 0; WS_N += 1) {
                WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_N-1].WS_AFT_PART_P_AMT = LNCIPAMT.PART_DATA[WS_N-1].PART_P_AMT;
                WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_N-1].WS_AFT_PART_I_AMT = LNCIPAMT.PART_DATA[WS_N-1].PART_I_AMT;
                WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_N-1].WS_AFT_PART_O_AMT = LNCIPAMT.PART_DATA[WS_N-1].PART_O_AMT;
                WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_N-1].WS_AFT_PART_L_AMT = LNCIPAMT.PART_DATA[WS_N-1].PART_L_AMT;
                CEP.TRC(SCCGWA, WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_N-1].WS_AFT_PART_P_AMT);
                CEP.TRC(SCCGWA, WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_N-1].WS_AFT_PART_I_AMT);
                CEP.TRC(SCCGWA, WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_N-1].WS_AFT_PART_O_AMT);
                CEP.TRC(SCCGWA, WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_N-1].WS_AFT_PART_L_AMT);
            }
            for (WS_I = 1; WS_I <= 10 
                && LNCSRPC.COMM_DATA.PART_DATA[WS_I-1].SUB_C_NO.trim().length() != 0; WS_I += 1) {
                if (LNCSRPC.COMM_DATA.PART_DATA[WS_I-1].I_O_FLG == 'O' 
                    && LNCSRPC.COMM_DATA.PART_DATA[WS_I-1].LCL_BR == 'N') {
                    IBS.init(SCCGWA, LNRSETL);
                    IBS.init(SCCGWA, LNCRSETL);
                    LNCRSETL.FUNC = 'I';
                    LNRSETL.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
                    LNRSETL.KEY.CI_TYPE = 'P';
                    LNRSETL.KEY.PART_BK = "" + LNCSRPC.COMM_DATA.PART_DATA[WS_I-1].SEQ_NO;
                    JIBS_tmp_int = LNRSETL.KEY.PART_BK.length();
                    for (int i=0;i<8-JIBS_tmp_int;i++) LNRSETL.KEY.PART_BK = "0" + LNRSETL.KEY.PART_BK;
                    LNRSETL.KEY.CCY = LNCSRPO.COMM_DATA.CCY;
                    LNRSETL.KEY.SETTLE_TYPE = 'C';
                    LNRSETL.KEY.SEQ_NO = 0;
                    CEP.TRC(SCCGWA, LNRSETL.KEY.PART_BK);
                    S000_CALL_LNZRSETL();
                    if (pgmRtn) return;
                    IBS.init(SCCGWA, CICQACRI);
                    CICQACRI.DATA.AGR_NO = LNRSETL.AC;
                    CICQACRI.FUNC = 'A';
                    S000_CALL_CIZQACRI();
                    if (pgmRtn) return;
                    B101_GET_AC_TYPE();
                    if (pgmRtn) return;
                    WS_TX_AC_TYP = WS_SETL_INFO.WS_FUND_AC_TYP;
                    WS_TX_AC = LNRSETL.AC;
                    B321_CALL_LNZIPAMT();
                    if (pgmRtn) return;
                    for (WS_N = 1; LNCIPAMT.PART_DATA[WS_N-1].PART_CTA_NO.trim().length() != 0; WS_N += 1) {
                        if (LNRSETL.KEY.PART_BK.equalsIgnoreCase(LNCIPAMT.PART_DATA[WS_N-1].PART_NO)) {
                            WS_TX_AMT = WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_N-1].WS_BEF_PART_P_AMT - WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_N-1].WS_AFT_PART_P_AMT + WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_N-1].WS_BEF_PART_I_AMT - WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_N-1].WS_AFT_PART_I_AMT + WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_N-1].WS_BEF_PART_O_AMT - WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_N-1].WS_AFT_PART_O_AMT + WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_N-1].WS_BEF_PART_L_AMT - WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_N-1].WS_AFT_PART_L_AMT;
                        }
                    }
                    B095_TX_AMT_CR_PROC();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void R000_READ_LNTBALZ_PART() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRBALZ);
        LNRBALZ.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        LNRBALZ.KEY.SUB_CTA_NO = WS_PART_NO;
        LNTBALZ_RD = new DBParm();
        LNTBALZ_RD.TableName = "LNTBALZ";
        IBS.READ(SCCGWA, LNRBALZ, LNTBALZ_RD);
    }
    public void B321_CALL_LNZIPAMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-INQ-PAR-AMT", LNCIPAMT);
        if (LNCIPAMT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCIPAMT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B080_READ_NEW_ICTL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '3';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
    }
    public void B080_DELETE_PLDR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRICTL);
        LNRICTL.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        LNRICTL.KEY.SUB_CTA_NO = 0;
        T000_READ_LNTICTL();
        if (pgmRtn) return;
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.init(SCCGWA, GDCUMPLD);
            GDCUMPLD.FUNC = 'D';
            GDCUMPLD.ERR_FUNC = 'N';
            GDCUMPLD.RSEQ = LNCAPRDM.REC_DATA.GDA_APRE;
            S000_CALL_GDZUMPLD();
            if (pgmRtn) return;
        }
    }
    public void B090_UPDATE_WAIWEI_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCSRPO.COMM_DATA.TOT_P_AMT);
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if (LNCSRPO.COMM_DATA.TOT_P_AMT > 0 
            && !SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT") 
            && (!LNCCLNQ.DATA.CONT_TYPE.equalsIgnoreCase("CLDL") 
            || (LNCCLNQ.DATA.CONT_TYPE.equalsIgnoreCase("CLDL") 
            && !WS_CTL_STSW.substring(0, 1).equalsIgnoreCase("1")))) {
            IBS.init(SCCGWA, LNRAGRE);
            LNRAGRE.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
            LNRAPRD.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
            LNRICTL.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
            T000_READ_LNTAGRE();
            if (pgmRtn) return;
            T000_READ_LNTAPRD();
            if (pgmRtn) return;
            T000_READ_LNTICTL();
            if (pgmRtn) return;
            IBS.init(SCCGWA, SCCTPCL);
            SCCTPCL.SERV_AREA.OBJ_SYSTEM = "ESBP";
            SCCTPCL.SERV_AREA.SERV_CODE = "3002200000902";
            SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_SEQ = "" + SCCGWA.COMM_AREA.JRN_NO;
            JIBS_tmp_int = SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_SEQ.length();
            for (int i=0;i<12-JIBS_tmp_int;i++) SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_SEQ = "0" + SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_SEQ;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_CTRNO = LNRAGRE.PAPER_NO;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_DBLNO = LNRAGRE.DRAW_NO;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_CCY = LNCSRPO.COMM_DATA.CCY;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_AMT = LNCSRPO.COMM_DATA.TOT_P_AMT;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_AMT = SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_AMT * ( -1 );
            }
            SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_CHNL = SCCGWA.COMM_AREA.REQ_SYSTEM;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_TYP = LNCSRPO.COMM_DATA.PROD_CD;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_INT = 999;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            if (LNRAPRD.PAY_MTH == '0') {
                SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_PAYTYP = "RPT-01";
                SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            } else if (LNRAPRD.PAY_MTH == '1') {
                SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_PAYTYP = "RPT-02";
                SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            } else if (LNRAPRD.PAY_MTH == '2') {
                SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_PAYTYP = "RPT-03";
                SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            } else if (LNRAPRD.PAY_MTH == '3') {
                SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_PAYTYP = "RPT-04";
                SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            } else if (LNRAPRD.PAY_MTH == '4') {
                if (LNRAPRD.INST_MTH == '1') {
                    SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_PAYTYP = "RPT-05";
                    SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
                } else {
                    SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_PAYTYP = "RPT-06";
                    SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
                }
            } else if (LNRAPRD.PAY_MTH == '5') {
                SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_PAYTYP = "RPT-07";
                SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            }
            if (LNCSRPO.COMM_DATA.CUR_TRM == ' ') SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_CYCLE = 0;
            else SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_CYCLE = Short.parseShort(""+LNCSRPO.COMM_DATA.CUR_TRM);
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_FAMT1 = LNCSRPO.COMM_DATA.TOT_O_AMT;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_FAMT2 = LNCSRPO.COMM_DATA.TOT_L_AMT;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_YRATE = LNRICTL.CUR_RAT;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_PAYDT = SCCGWA.COMM_AREA.AC_DATE;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_AC = LNCSRPO.COMM_DATA.ACAMT[1-1].REC_AC2;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.DATA.AGR_NO = LNRAGRE.PAPER_NO;
            CEP.TRC(SCCGWA, LNCSRPO.COMM_DATA.ACAMT[1-1].REC_AC2);
            CEP.TRC(SCCGWA, CICQACRI.DATA.AGR_NO);
            CEP.TRC(SCCGWA, WS_CHECK_INFO.WS_PAY_ACNO);
            CICQACRI.FUNC = 'A';
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_ACNM = CICQACRI.O_DATA.O_AC_CNM;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_BKNM = "" + CICQACRI.O_DATA.O_OWNER_BK;
            JIBS_tmp_int = SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_BKNM.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_BKNM = "0" + SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_BKNM;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_BK = CICQACRI.O_DATA.O_OWNER_BK;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            IBS.init(SCCGWA, CICCUST);
            CICCUST.FUNC = 'A';
            CICCUST.DATA.AGR_NO = LNRAGRE.PAPER_NO;
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            WS_CHECK_INFO.WS_CI_NM = CICCUST.O_DATA.O_CI_NM;
            WS_CHECK_INFO.WS_ID_TYPE = CICCUST.O_DATA.O_ID_TYPE;
            WS_CHECK_INFO.WS_ID_NO = CICCUST.O_DATA.O_ID_NO;
            SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_CUSTNM = CICCUST.O_DATA.O_CI_NM;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_IDTYP = CICCUST.O_DATA.O_ID_TYPE;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_ID = CICCUST.O_DATA.O_ID_NO;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            SCCTPCL.SERV_AREA.SERV_TYPE = 'N';
            SCCTPCL.INP_AREA.SERV_DATA_LEN = 1079;
            S000_CALL_SCZTPCL();
            if (pgmRtn) return;
        }
    }
    public void B090_UPD_STSW_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '4';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        LNCICTLM.FUNC = '2';
        if (WS_UPD_STSW44_0_FLG == 'Y') {
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 44 - 1) + "0" + LNCICTLM.REC_DATA.CTL_STSW.substring(44 + 1 - 1);
        }
        if (WS_UPD_STSW45_0_FLG == 'Y') {
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 45 - 1) + "0" + LNCICTLM.REC_DATA.CTL_STSW.substring(45 + 1 - 1);
        }
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
    }
    public void B110_BP_HISTORY_GEN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPFHIS);
        BPCPFHIS.DATA.TX_DRCR_FLG = 'C';
        BPCPFHIS.DATA.PRINT_IND = 'Y';
        BPCPFHIS.DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPFHIS.DATA.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        BPCPFHIS.DATA.AC = LNRAGRE.PAPER_NO;
        BPCPFHIS.DATA.ACO_AC = LNCSRPO.COMM_DATA.CTA_NO;
        BPCPFHIS.DATA.REF_NO = LNCSRPO.COMM_DATA.CTA_NO;
        BPCPFHIS.DATA.TX_CCY = LNCSRPO.COMM_DATA.CCY;
        BPCPFHIS.DATA.TX_AMT = LNCSRPO.COMM_DATA.TOT_P_AMT + LNCSRPO.COMM_DATA.TOT_I_AMT + LNCSRPO.COMM_DATA.TOT_O_AMT + LNCSRPO.COMM_DATA.TOT_L_AMT + LNCSCLR.COMM_DATA.NOR_P + LNCSCLR.COMM_DATA.NOR_I + LNCSCLR.COMM_DATA.OVR_P + LNCSCLR.COMM_DATA.OVR_I + LNCSCLR.COMM_DATA.NOR_O + LNCSCLR.COMM_DATA.NOR_L;
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_AMT);
        BPCPFHIS.DATA.TX_MMO = LNCSRPO.COMM_DATA.MMO;
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_MMO);
        BPCPFHIS.DATA.CI_NO = LNCSRPO.COMM_DATA.CI_NO;
        BPCPFHIS.DATA.PROD_CD = LNCSRPO.COMM_DATA.PROD_CD;
        BPCPFHIS.DATA.OTH_AC = LNCSRPO.COMM_DATA.ACAMT[1-1].REC_AC2;
        if (LNCSRPO.COMM_DATA.ACAMT[1-1].STL_MTH2.equalsIgnoreCase("05) LNCSRPO.COMM_DATA.ACAMT[1-1].STL_MTH2.equalsIgnoreCase(") 
            || LNCSRPO.COMM_DATA.ACAMT[1-1].STL_MTH2.equalsIgnoreCase("06) LNCSRPO.COMM_DATA.ACAMT[1-1].STL_MTH2.equalsIgnoreCase(")) {
            BPCPFHIS.DATA.TX_TOOL = LNCSRPO.COMM_DATA.ACAMT[1-1].REC_AC2;
        }
        IBS.init(SCCGWA, LNCOD10.TXN_INPUT);
        LNCOD10.TXN_INPUT.CTA_NO = LNCSRPO.COMM_DATA.CTA_NO;
        LNCOD10.TXN_INPUT.BR = LNCSRPO.COMM_DATA.BR;
        LNCOD10.TXN_INPUT.CI_NO = LNCSRPO.COMM_DATA.CI_NO;
        LNCOD10.TXN_INPUT.CI_ENMS = " ";
        LNCOD10.TXN_INPUT.CITY_CD = " ";
        LNCOD10.TXN_INPUT.PROD_CD = LNCSRPO.COMM_DATA.PROD_CD;
        LNCOD10.TXN_INPUT.CCY = LNCSRPO.COMM_DATA.CCY;
        LNCOD10.TXN_INPUT.LON_PRIN = LNCSRPO.COMM_DATA.PRINCIPAL;
        LNCOD10.TXN_INPUT.LON_BAL = LNCSRPO.COMM_DATA.BALANCE - LNCSRPO.COMM_DATA.TOT_P_AMT;
        LNCOD10.TXN_INPUT.TR_VALDT = LNCSRPO.COMM_DATA.TR_VAL_DTE;
        LNCOD10.TXN_INPUT.TOT_AMT = LNCSRPO.COMM_DATA.TOT_AMT;
        LNCOD10.TXN_INPUT.TOT_PRIN = LNCSRPO.COMM_DATA.TOT_P_AMT;
        LNCOD10.TXN_INPUT.TOT_INT = LNCSRPO.COMM_DATA.TOT_I_AMT;
        LNCOD10.TXN_INPUT.TOT_PLC = LNCSRPO.COMM_DATA.TOT_O_AMT;
        LNCOD10.TXN_INPUT.WAV_PLC = 0;
        LNCOD10.TXN_INPUT.TOT_ILC = LNCSRPO.COMM_DATA.TOT_L_AMT;
        LNCOD10.TXN_INPUT.WAV_ILC = 0;
        LNCOD10.TXN_INPUT.HRG_AMT = LNCSRPO.COMM_DATA.HRG_AMT;
        LNCOD10.TXN_INPUT.SETL_MTH = LNCSRPO.COMM_DATA.ACAMT[1-1].STL_MTH2;
        if (LNCSRPO.COMM_DATA.ACAMT[1-1].STL_MTH2.equalsIgnoreCase("01) LNCSRPO.COMM_DATA.ACAMT[1-1].STL_MTH2.equalsIgnoreCase(")) {
            LNCOD10.TXN_INPUT.DEP_AC = LNCSRPO.COMM_DATA.ACAMT[1-1].REC_AC2;
        } else if (LNCSRPO.COMM_DATA.ACAMT[1-1].STL_MTH2.equalsIgnoreCase("07) LNCSRPO.COMM_DATA.ACAMT[1-1].STL_MTH2.equalsIgnoreCase(")) {
            LNCOD10.TXN_INPUT.CHQ_NO = LNCSRPO.COMM_DATA.ACAMT[1-1].CHQ_NO2;
        } else if (LNCSRPO.COMM_DATA.ACAMT[1-1].STL_MTH2.equalsIgnoreCase("02) LNCSRPO.COMM_DATA.ACAMT[1-1].STL_MTH2.equalsIgnoreCase(")) {
            LNCOD10.TXN_INPUT.SUSP_AC = LNCSRPO.COMM_DATA.ACAMT[1-1].REC_AC2;
        } else if (LNCSRPO.COMM_DATA.ACAMT[1-1].STL_MTH2.equalsIgnoreCase("03) LNCSRPO.COMM_DATA.ACAMT[1-1].STL_MTH2.equalsIgnoreCase(")) {
            LNCOD10.TXN_INPUT.NOS_CD = LNCSRPO.COMM_DATA.ACAMT[1-1].REC_AC2;
        } else {
        }
        LNCOD10.IDX = 0;
        LNCOD10.TXN_INPUT.TAX_AMT = 0;
        BPCPFHIS.DATA.FMT_CODE = K_FMT_ID;
        BPCPFHIS.DATA.FMT_LEN = 2481;
        BPCPFHIS.DATA.FMT_DATA = IBS.CLS2CPY(SCCGWA, LNCOD10);
        if (BPCPFHIS.DATA.TX_AMT != 0) {
            S000_CALL_BPZPFHIS();
            if (pgmRtn) return;
        }
    }
    public void B120_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_FMT_ID;
        SCCFMT.DATA_PTR = LNCOD10;
        SCCFMT.DATA_LEN = 2481;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B041_CURRENT_TERM_CHECK() throws IOException,SQLException,Exception {
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if (WS_CTL_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_ALREADY_OVD;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCCLNQ.DATA.VAL_DT == SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_CUR_TRM_TRDT_ERR1;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCSRPO.COMM_DATA.TR_VAL_DTE != SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_CUR_TRM_TRDT_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCAPRDM.REC_DATA.PAY_MTH != '0') {
            IBS.init(SCCGWA, LNRRCVD);
            LNRRCVD.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
            LNRRCVD.KEY.SUB_CTA_NO = 0;
            LNRRCVD.REPY_STS = '2';
            LNRRCVD.DUE_DT = LNCSRPO.COMM_DATA.TR_VAL_DTE;
            LNTRCVD_RD = new DBParm();
            LNTRCVD_RD.TableName = "LNTRCVD";
            LNTRCVD_RD.eqWhere = "CONTRACT_NO,SUB_CTA_NO";
            LNTRCVD_RD.where = "REPY_STS = :LNRRCVD.REPY_STS "
                + "AND DUE_DT > :LNRRCVD.DUE_DT";
            LNTRCVD_RD.fst = true;
            IBS.READ(SCCGWA, LNRRCVD, this, LNTRCVD_RD);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_REPET_CUR_REPAY;
                Z_RET();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, LNRRCVD);
        LNRRCVD.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        LNRRCVD.KEY.SUB_CTA_NO = 0;
        LNRRCVD.REPY_STS = '2';
        LNRRCVD.DUE_DT = LNCSRPO.COMM_DATA.TR_VAL_DTE;
        LNTRCVD_RD = new DBParm();
        LNTRCVD_RD.TableName = "LNTRCVD";
        LNTRCVD_RD.eqWhere = "CONTRACT_NO";
        LNTRCVD_RD.where = "SUB_CTA_NO = :LNRRCVD.KEY.SUB_CTA_NO "
            + "AND REPY_STS < > :LNRRCVD.REPY_STS "
            + "AND DUE_DT <= :LNRRCVD.DUE_DT";
        LNTRCVD_RD.fst = true;
        LNTRCVD_RD.order = "DUE_DT DESC";
        IBS.READ(SCCGWA, LNRRCVD, this, LNTRCVD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_ALREADY_OVD;
            Z_RET();
            if (pgmRtn) return;
        }
        B320_GET_DANG_QI_AMT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_IC_CAL_DUE_DT);
        IBS.init(SCCGWA, LNCICAL);
        LNCICAL.COMM_DATA.FUNC_CODE = 'U';
        LNCICAL.COMM_DATA.LN_AC = LNCSRPO.COMM_DATA.CTA_NO;
        LNCICAL.COMM_DATA.SUF_NO = "" + 0;
        JIBS_tmp_int = LNCICAL.COMM_DATA.SUF_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCICAL.COMM_DATA.SUF_NO = "0" + LNCICAL.COMM_DATA.SUF_NO;
        LNCICAL.COMM_DATA.VAL_DATE = WS_IC_CAL_DUE_DT;
        S000_CALL_LNZICAL();
        if (pgmRtn) return;
        if (LNCSRPO.COMM_DATA.TOT_P_AMT != 0 
            || LNCSRPO.COMM_DATA.TOT_I_AMT != 0) {
            IBS.init(SCCGWA, LNRRCVD);
            LNRRCVD.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
            LNRRCVD.KEY.SUB_CTA_NO = 0;
            LNRRCVD.DUE_DT = WS_IC_CAL_DUE_DT;
            T000_STARTBR_RCVD_PROC();
            if (pgmRtn) return;
            T000_READNEXT_RCVD_PROC();
            if (pgmRtn) return;
            while (LNCRRCVD.RETURN_INFO != 'E') {
                CEP.TRC(SCCGWA, LNRRCVD.KEY.AMT_TYP);
                CEP.TRC(SCCGWA, LNCSRPO.COMM_DATA.TOT_P_AMT);
                CEP.TRC(SCCGWA, LNCSRPO.COMM_DATA.TOT_I_AMT);
                CEP.TRC(SCCGWA, LNRRCVD.P_REC_AMT);
                CEP.TRC(SCCGWA, LNRRCVD.I_REC_AMT);
                if (LNRRCVD.KEY.AMT_TYP == 'C') {
                    if ((LNCSRPO.COMM_DATA.TOT_P_AMT != LNRRCVD.P_REC_AMT 
                        || LNCSRPO.COMM_DATA.TOT_I_AMT != LNRRCVD.I_REC_AMT)) {
                        WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_CUR_TRM_REPY_AMT_ERR;
                        df = new DecimalFormat("################V00");
                        WS_MSG_9 = df.format(LNRRCVD.P_REC_AMT);
                        WS_MSG_INFO_CHAR = WS_MSG_9;
                        df = new DecimalFormat("################V00");
                        WS_MSG_91 = df.format(LNRRCVD.I_REC_AMT);
                        WS_MSG_INFO_CHAR1 = WS_MSG_91;
                        WS_MSG_INFO = "P SHOULD BE " + WS_MSG_INFO_CHAR + " I SHOULD BE " + WS_MSG_INFO_CHAR1;
                    }
                } else if (LNRRCVD.KEY.AMT_TYP == 'P') {
                    if (LNCSRPO.COMM_DATA.TOT_P_AMT != LNRRCVD.P_REC_AMT) {
                        WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_CUR_TRM_REPY_AMT_ERR;
                        df = new DecimalFormat("################V00");
                        WS_MSG_9 = df.format(LNRRCVD.P_REC_AMT);
                        WS_MSG_INFO_CHAR = WS_MSG_9;
                        WS_MSG_INFO = "P SHOULD BE " + WS_MSG_INFO_CHAR;
                    }
                } else if (LNRRCVD.KEY.AMT_TYP == 'I') {
                    if (LNCSRPO.COMM_DATA.TOT_I_AMT != LNRRCVD.I_REC_AMT) {
                        WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_CUR_TRM_REPY_AMT_ERR;
                        df = new DecimalFormat("################V00");
                        WS_MSG_9 = df.format(LNRRCVD.I_REC_AMT);
                        WS_MSG_INFO_CHAR = WS_MSG_9;
                        WS_MSG_INFO = "I SHOULD BE " + WS_MSG_INFO_CHAR;
                    }
                } else {
                    WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_AMT_TYPE;
                }
                if (WS_ERR_MSG.trim().length() > 0) {
                    CEP.ERR(SCCGWA, WS_ERR_MSG, WS_MSG_INFO);
                }
                T000_READNEXT_RCVD_PROC();
                if (pgmRtn) return;
            }
            T000_ENDBR_RCVD_PROC();
            if (pgmRtn) return;
        }
    }
    public void B320_GET_DANG_QI_AMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSSCHE);
        LNCSSCHE.DATA_AREA.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        LNCSSCHE.CUR_FLG = 'Y';
        S000_CALL_LNZSSCHE();
        if (pgmRtn) return;
        if (LNCSSCHE.OUT_INFO[2-1].O_DUE_DATE != 0 
            && LNCSSCHE.OUT_INFO[2-1].O_DUE_DATE < LNCSSCHE.OUT_INFO[1-1].O_DUE_DATE) {
            WS_IC_CAL_DUE_DT = LNCSSCHE.OUT_INFO[2-1].O_DUE_DATE;
        } else {
            WS_IC_CAL_DUE_DT = LNCSSCHE.OUT_INFO[1-1].O_DUE_DATE;
        }
    }
    public void B050_BATCH_AMT_DEBIT_PROC() throws IOException,SQLException,Exception {
        if (LNCSRPO.COMM_DATA.SUBS_FLG == 'Y') {
            T00_READ_LNTRELA();
            if (pgmRtn) return;
            B052_SUBS_LOAN_PROC();
            if (pgmRtn) return;
        } else {
            WS_PAY_AMT = LNCSRPO.COMM_DATA.TOT_AMT;
            B051_AMT_DEBIT_PROC();
            if (pgmRtn) return;
            if (LNCAPRDM.REC_DATA.GDA_AUTO_DB == 'Y') {
                if (LNCAPRDM.REC_DATA.GDA_APRE.trim().length() > 0 
                    && LNCAPRDM.REC_DATA.GDA_DB_SEQ == 'G') {
                    B053_BAOZHENGJIN_KOUKUAN();
                    if (pgmRtn) return;
                    if (WS_PAY_AMT > 0) {
                        B051_AMT_DEBIT_PROC();
                        if (pgmRtn) return;
                    }
                }
                if (LNCAPRDM.REC_DATA.GDA_DB_SEQ == 'S' 
                    && LNCAPRDM.REC_DATA.GDA_APRE.trim().length() > 0) {
                    B051_AMT_DEBIT_PROC();
                    if (pgmRtn) return;
                    if (WS_PAY_AMT > 0) {
                        B053_BAOZHENGJIN_KOUKUAN();
                        if (pgmRtn) return;
                    }
                }
                B051_AMT_DEBIT_PROC();
                if (pgmRtn) return;
            }
            if (WS_PAY_AMT != 0) {
                WS_KOU_TOT_AMT = WS_KOU_SETL_AMT + WS_KOU_GDA_TOT_AMT;
            } else {
                WS_KOU_TOT_AMT = LNCSRPO.COMM_DATA.TOT_AMT;
            }
        }
        B040_RECALL_LNZSRPC_END();
        if (pgmRtn) return;
    }
    public void B053_BAOZHENGJIN_KOUKUAN() throws IOException,SQLException,Exception {
        B044_GET_GDA_AC_AVABAL();
        if (pgmRtn) return;
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
            if (LNCSRPO.COMM_DATA.ACAMT[WS_I-1].REC_AC2.trim().length() == 0) {
                LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2 = " ";
                for (WS_G = 1; WS_G <= GDCIQLDR.TCNT 
                    && WS_KOU_KUAN_AMT != 0; WS_G += 1) {
                    CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_G-1].OUT_AC);
                    CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_G-1].CCY);
                    CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_G-1].RAMT);
                    if (GDCIQLDR.INFO[WS_G-1].CCY.equalsIgnoreCase(LNCSRPO.COMM_DATA.CCY)) {
                        if (WS_KOU_KUAN_AMT > GDCIQLDR.INFO[WS_G-1].RAMT) {
                            WS_KOU_GDA_AMT = GDCIQLDR.INFO[WS_G-1].RAMT;
                        } else {
                            WS_KOU_GDA_AMT = WS_KOU_KUAN_AMT;
                        }
                        IBS.init(SCCGWA, GDCSTRAC);
                        GDCSTRAC.TXFUCTYP = '1';
                        GDCSTRAC.TXRSEQ = LNCAPRDM.REC_DATA.GDA_APRE;
                        GDCSTRAC.TXAMT = WS_KOU_GDA_AMT;
                        if (GDCSTRAC.TXAMT != 0) {
                            S000_CALL_GDZSTRAC();
                            if (pgmRtn) return;
                        }
                        WS_KOU_GDA_TOT_AMT += GDCSTRAC.TXAMT;
                        WS_KOU_KUAN_AMT -= GDCSTRAC.TXAMT;
                    }
                }
                WS_KOU_KUAN_AMT = WS_KOU_GDA_TOT_AMT;
            } else {
                WS_KOU_GDA_FLAG = 'N';
                for (WS_G = 1; WS_G <= GDCIQLDR.TCNT 
                    && WS_KOU_GDA_FLAG != 'Y'; WS_G += 1) {
                    CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_G-1].OUT_AC);
                    CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_G-1].CCY);
                    CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_G-1].RAMT);
                    if (LNCSRPO.COMM_DATA.ACAMT[WS_I-1].REC_AC2.equalsIgnoreCase(GDCIQLDR.INFO[WS_G-1].OUT_AC) 
                        && LNCSRPO.COMM_DATA.CCY.equalsIgnoreCase(GDCIQLDR.INFO[WS_G-1].CCY) 
                        && GDCIQLDR.INFO[WS_G-1].RAMT != 0) {
                        WS_KOU_GDA_FLAG = 'Y';
                        if (GDCIQLDR.INFO[WS_G-1].RAMT < WS_KOU_KUAN_AMT) {
                            WS_KOU_KUAN_AMT = 0;
                        } else {
                            WS_KOU_GDA_AMT = WS_KOU_KUAN_AMT;
                        }
                        IBS.init(SCCGWA, GDCSTRAC);
                        GDCSTRAC.TXFUCTYP = '1';
                        GDCSTRAC.TXRSEQ = LNCAPRDM.REC_DATA.GDA_APRE;
                        GDCSTRAC.TXAMT = WS_KOU_GDA_AMT;
                        if (GDCSTRAC.TXAMT != 0) {
                            S000_CALL_GDZSTRAC();
                            if (pgmRtn) return;
                        }
                        WS_KOU_KUAN_AMT = GDCSTRAC.TXAMT;
                    }
                }
                if (WS_KOU_GDA_FLAG == 'N') {
                    WS_KOU_KUAN_AMT = 0;
                }
            }
        } else {
        }
    }
    public void B051_AMT_GDA_DEBIT_PROC() throws IOException,SQLException,Exception {
        WS_KOU_KUAN_AMT = WS_PAY_AMT;
        B044_GET_GDA_AC_AVABAL();
        if (pgmRtn) return;
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
            for (WS_I = 1; WS_I <= GDCIQLDR.TCNT 
                && WS_PAY_AMT != 0; WS_I += 1) {
                if (GDCIQLDR.INFO[WS_I-1].CCY.equalsIgnoreCase(LNCSRPO.COMM_DATA.CCY)) {
                    IBS.init(SCCGWA, GDCUMPDR);
                    GDCUMPDR.INPUT.FUNC = 'D';
                    GDCUMPDR.INPUT.RSEQ = LNCAPRDM.REC_DATA.GDA_APRE;
                    GDCUMPDR.INPUT.AC = GDCIQLDR.INFO[WS_I-1].OUT_AC;
                    if (GDCIQLDR.INFO[WS_I-1].RAMT < WS_KOU_KUAN_AMT) {
                        GDCUMPDR.INPUT.AMT = GDCIQLDR.INFO[WS_I-1].RAMT;
                    } else {
                        GDCUMPDR.INPUT.AMT = WS_KOU_KUAN_AMT;
                    }
                    if (GDCUMPDR.INPUT.AMT != 0) {
                        WS_SEQ2 += 1;
                        GDCUMPDR.INPUT.SEQ_NO = WS_SEQ2;
                        S000_CALL_GDZUMPDR();
                        if (pgmRtn) return;
                        IBS.init(SCCGWA, WS_GDA_ACAMT[WS_SEQ2-1]);
                        WS_GDA_ACAMT[WS_SEQ2-1].WS_GDA_AC1 = GDCIQLDR.INFO[WS_I-1].OUT_AC;
                        if (GDCIQLDR.INFO[WS_I-1].OUT_AC.trim().length() == 0) WS_GDA_ACAMT[WS_SEQ2-1].WS_GDA_AC_SEQ1 = 0;
                        else WS_GDA_ACAMT[WS_SEQ2-1].WS_GDA_AC_SEQ1 = Integer.parseInt(GDCIQLDR.INFO[WS_I-1].OUT_AC);
                        WS_GDA_ACAMT[WS_SEQ2-1].WS_GDA_CCY1 = GDCIQLDR.INFO[WS_I-1].CCY;
                        WS_GDA_ACAMT[WS_SEQ2-1].WS_GDA_PAY_AMT1 = GDCUMPDR.INPUT.AMT;
                    }
                    WS_KOU_GDA_TOT_AMT += GDCUMPDR.INPUT.AMT;
                    WS_KOU_KUAN_AMT = GDCUMPDR.INPUT.AMT;
                    if (WS_KOU_KUAN_AMT <= WS_PAY_AMT) {
                        WS_PAY_AMT -= WS_KOU_KUAN_AMT;
                        WS_KOU_KUAN_AMT = WS_PAY_AMT;
                    }
                    IBS.init(SCCGWA, CICQACRI);
                    CICQACRI.DATA.AGR_NO = GDCIQLDR.INFO[WS_I-1].OUT_AC;
                    CICQACRI.FUNC = 'A';
                    S000_CALL_CIZQACRI();
                    if (pgmRtn) return;
                    WS_ACR_BBR[WS_BBR_I-1] = CICQACRI.O_DATA.O_OWNER_BK;
                    WS_BBR_I += 1;
                }
            }
        } else {
        }
    }
    public void B051_AMT_DEBIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRAGRE);
        LNRAGRE.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        T000_READ_LNTAGRE();
        if (pgmRtn) return;
        if (LNCSRPO.COMM_DATA.SUBS_FLG == 'Y') {
            T00_READ_LNTRELA();
            if (pgmRtn) return;
            B052_SUBS_LOAN_PROC();
            if (pgmRtn) return;
        } else {
            for (WS_I = 1; WS_I <= 5 
                && (LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.trim().length() != 0 
                || LNCSRPO.COMM_DATA.ACAMT[WS_I-1].REC_AC2.trim().length() != 0 
                || WS_I <= 1); WS_I += 1) {
                CEP.TRC(SCCGWA, "111S");
                if (WS_PAY_AMT != 0 
                    || WS_EX_TOT_AMT != 0) {
                    WS_KOU_KUAN_AMT = WS_PAY_AMT;
                    if (LNCSRPO.COMM_DATA.ACAMT[WS_I-1].PAY_AMT2 != 0 
                        && LNCSRPO.COMM_DATA.ACAMT[WS_I-1].PAY_AMT2 < WS_KOU_KUAN_AMT) {
                        WS_KOU_KUAN_AMT = LNCSRPO.COMM_DATA.ACAMT[WS_I-1].PAY_AMT2;
                    }
                    CEP.TRC(SCCGWA, LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2);
                    if (LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase("01) LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase(")
                        || LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase("07) LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase(")
                        || LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase("05) LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase(")
                        || LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase("06) LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase(")) {
                        B052_DD_DEBIT_PROC();
                        if (pgmRtn) return;
                    } else if (LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase("02) LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase(")) {
                        B052_GLSUS_DEBIT_PROC();
                        if (pgmRtn) return;
                    } else if (LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase("03) LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase(")) {
                        B052_NOSCD_DEBIT_PROC();
                        if (pgmRtn) return;
                    } else if (LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase("04) LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase(")) {
                    } else if (LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase("08) LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase(")) {
                        B052_CASH_DEBIT_PROC();
                        if (pgmRtn) return;
                    } else if (LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase("09) LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase(")) {
                        B052_PARM_DEBIT_PROC();
                        if (pgmRtn) return;
                    } else {
                    }
                    CEP.TRC(SCCGWA, WS_KOU_KUAN_AMT);
                    if (WS_KOU_KUAN_AMT <= WS_PAY_AMT) {
                        WS_PAY_AMT -= WS_KOU_KUAN_AMT;
                    }
                } else {
                    WS_KOU_KUAN_AMT = 0;
                }
                LNCSRPO.COMM_DATA.ACAMT[WS_I-1].PAY_AMT2 = WS_KOU_KUAN_AMT;
                WS_KOU_SETL_AMT += WS_KOU_KUAN_AMT;
            }
        }
    }
    public void B052_DD_DEBIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUDRAC);
        DDCUDRAC.CHK_PSW_FLG = 'N';
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.BANK_DR_FLG = 'Y';
        DDCUDRAC.TX_BAL_FLG = 'Y';
        DDCUDRAC.TSTS_TABL = "0014";
        DDCUDRAC.BV_TYP = '3';
        if (LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase("05) LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase(") 
            || LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase("06) LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase(")) {
            DDCUDRAC.CARD_NO = LNCSRPO.COMM_DATA.ACAMT[WS_I-1].REC_AC2;
        } else {
            DDCUDRAC.AC = LNCSRPO.COMM_DATA.ACAMT[WS_I-1].REC_AC2;
        }
        if (LNCSRPO.COMM_DATA.ACAMT[WS_I-1].CHQ_NO2.trim().length() > 0 
            && LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase("07) LNCSRPO.COMM_DATA.ACAMT[WS_I-1].STL_MTH2.equalsIgnoreCase(")) {
            DDCUDRAC.CHQ_NO = LNCSRPO.COMM_DATA.ACAMT[WS_I-1].CHQ_NO2;
            DDCUDRAC.CHQ_TYPE = LNCSRPO.COMM_DATA.ACAMT[WS_I-1].CHQ_TYPE2;
            DDCUDRAC.CHQ_ISS_DATE = LNCSRPO.COMM_DATA.ACAMT[WS_I-1].ISS_DATE2;
            DDCUDRAC.PAY_PSWD = LNCSRPO.COMM_DATA.ACAMT[WS_I-1].PAY_PSWD;
        }
        DDCUDRAC.CCY = LNCSRPO.COMM_DATA.CCY;
        DDCUDRAC.CCY_TYPE = LNCSRPO.COMM_DATA.ACAMT[1-1].CCY_TYP;
        CEP.TRC(SCCGWA, DDCUDRAC.CCY_TYPE);
        DDCUDRAC.TX_AMT = WS_KOU_KUAN_AMT;
        DDCUDRAC.VAL_DATE = LNCSRPO.COMM_DATA.TR_VAL_DTE;
        DDCUDRAC.TX_MMO = LNCSRPO.COMM_DATA.MMO;
        DDCUDRAC.OTHER_AC = LNRAGRE.PAPER_NO;
        DDCUDRAC.OTHER_AMT = LNCSRPO.COMM_DATA.ACAMT[WS_I-1].PAY_AMT2;
        DDCUDRAC.NARRATIVE = IBS.CLS2CPY(SCCGWA, WS_TRF_NARRATIVE);
        if (DDCUDRAC.TX_AMT != 0) {
            S000_CALL_DDZUDRAC();
            if (pgmRtn) return;
        }
        WS_KOU_KUAN_AMT = DDCUDRAC.TX_AMT;
    }
    public void B090_GET_DD_AC_BAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSQBAL);
        DDCSQBAL.AC_NO = WS_AC;
        DDCSQBAL.CCY = WS_AC_CCY;
        S000_CALL_DDZSQBAL();
        if (pgmRtn) return;
        WS_AC_AMT = DDCSQBAL.AVL_BAL;
    }
    public void B052_GLSUS_DEBIT_PROC() throws IOException,SQLException,Exception {
        WS_AC = LNCSRPO.COMM_DATA.ACAMT[WS_I-1].REC_AC2;
        WS_AC_CCY = LNCSRPO.COMM_DATA.CCY;
        B042_GET_SUSP_AC_AVABAL();
        if (pgmRtn) return;
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.EVENT_CODE = "DR";
        AICUUPIA.DATA.AC_NO = LNCSRPO.COMM_DATA.ACAMT[WS_I-1].REC_AC2;
        AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.CCY = LNCSRPO.COMM_DATA.CCY;
        if (AICPQMIB.OUTPUT_DATA.BAL_RFLG == 'N' 
            && WS_PQMIB_CBAL < WS_KOU_KUAN_AMT) {
            AICUUPIA.DATA.AMT = WS_PQMIB_CBAL;
        } else {
            AICUUPIA.DATA.AMT = WS_KOU_KUAN_AMT;
        }
        AICUUPIA.DATA.VALUE_DATE = LNCSRPO.COMM_DATA.TR_VAL_DTE;
        AICUUPIA.DATA.PAY_MAN = LNCCLNQ.DATA.CI_CNAME;
        AICUUPIA.DATA.RVS_NO = LNCSRPO.COMM_DATA.ACAMT[WS_I-1].RVS_NO2;
        if (AICUUPIA.DATA.AMT != 0) {
            S000_CALL_AIZUUPIA();
            if (pgmRtn) return;
        }
        WS_KOU_KUAN_AMT = AICUUPIA.DATA.AMT;
    }
    public void B052_NOSCD_DEBIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCPOSTA);
        IBCPOSTA.NOSTRO_CD = LNCSRPO.COMM_DATA.ACAMT[WS_I-1].REC_AC2;
        IBCPOSTA.CCY = LNCSRPO.COMM_DATA.CCY;
        IBCPOSTA.AMT = WS_KOU_KUAN_AMT;
        IBCPOSTA.TXTYPE = "01";
        IBCPOSTA.VAL_DATE = LNCSRPO.COMM_DATA.TR_VAL_DTE;
        IBCPOSTA.ENTRY_FLG = '1';
        IBCPOSTA.SIGN = 'D';
        IBCPOSTA.ACT_CTR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        IBCPOSTA.ACT_CTR = LNCSRPO.COMM_DATA.BR;
        IBCPOSTA.OUR_REF = LNCSRPO.COMM_DATA.CTA_NO;
        IBCPOSTA.THR_REF = LNCSRPO.COMM_DATA.CTA_NO;
        IBCPOSTA.NARR = IBS.CLS2CPY(SCCGWA, WS_TRF_NARRATIVE);
        IBCPOSTA.TX_MMO = LNCSRPO.COMM_DATA.MMO;
        if (IBCPOSTA.AMT != 0) {
            S000_CALL_IBZDRAC();
            if (pgmRtn) return;
        }
        WS_KOU_KUAN_AMT = IBCPOSTA.AMT;
    }
    public void B052_GM_DEBIT_PROC() throws IOException,SQLException,Exception {
        B044_GET_GDA_AC_AVABAL();
        if (pgmRtn) return;
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
            CEP.TRC(SCCGWA, GDCIQLDR.TCNT);
            CEP.TRC(SCCGWA, WS_KOU_KUAN_AMT);
            for (WS_G = 1; WS_G <= GDCIQLDR.TCNT 
                && WS_KOU_KUAN_AMT != 0; WS_G += 1) {
                CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_G-1].OUT_AC);
                CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_G-1].CCY);
                CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_G-1].RAMT);
                if (GDCIQLDR.INFO[WS_G-1].CCY.equalsIgnoreCase(LNCSRPO.COMM_DATA.CCY)) {
                    if (WS_KOU_KUAN_AMT > GDCIQLDR.INFO[WS_G-1].RAMT) {
                        WS_KOU_GDA_AMT = GDCIQLDR.INFO[WS_G-1].RAMT;
                    } else {
                        WS_KOU_GDA_AMT = WS_KOU_KUAN_AMT;
                    }
                    IBS.init(SCCGWA, GDCSDDDR);
                    GDCSDDDR.DRAC = GDCIQLDR.INFO[WS_G-1].OUT_AC;
                    GDCSDDDR.AMT = WS_KOU_GDA_AMT;
                    GDCSDDDR.CCY = LNCSRPO.COMM_DATA.CCY;
                    GDCSDDDR.STLT = ' ';
                    CEP.TRC(SCCGWA, "222");
                    CEP.TRC(SCCGWA, LNRAGRE.PAPER_NO);
                    GDCSDDDR.CRAC = LNRAGRE.PAPER_NO;
                    if (GDCSDDDR.AMT != 0) {
                        WS_SEQ1 += 1;
                        S000_CALL_GDZSDDDR();
                        if (pgmRtn) return;
                        IBS.init(SCCGWA, WS_GDA_ACAMT[WS_SEQ1-1]);
                        WS_GDA_ACAMT[WS_SEQ1-1].WS_GDA_AC1 = GDCIQLDR.INFO[WS_G-1].OUT_AC;
                        WS_GDA_ACAMT[WS_SEQ1-1].WS_GDA_CCY1 = GDCIQLDR.INFO[WS_G-1].CCY;
                        WS_GDA_ACAMT[WS_SEQ1-1].WS_GDA_PAY_AMT1 = GDCSDDDR.AMT;
                    }
                    WS_KOU_GDA_TOT_AMT += GDCSDDDR.AMT;
                    WS_KOU_KUAN_AMT -= GDCSDDDR.AMT;
                }
            }
            WS_KOU_KUAN_AMT = WS_KOU_GDA_TOT_AMT;
            WS_PAY_AMT -= WS_KOU_KUAN_AMT;
            CEP.TRC(SCCGWA, WS_KOU_KUAN_AMT);
            CEP.TRC(SCCGWA, WS_PAY_AMT);
        } else {
        }
    }
    public void B044_GET_GDA_AC_AVABAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCIQLDR);
        GDCIQLDR.RSEQ = LNCAPRDM.REC_DATA.GDA_APRE;
        S000_CALL_GDCIQLDR();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = GDCIQLDR.INFO[1-1].OUT_AC;
        CICQACAC.DATA.AGR_SEQ = GDCIQLDR.INFO[1-1].OUT_SEQ;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
    }
    public void B052_CASH_DEBIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUABOX);
        BPCUABOX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCUABOX.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUABOX.CCY = LNCSRPO.COMM_DATA.CCY;
        BPCUABOX.CCY_TYP = '0';
        BPCUABOX.AMT = WS_KOU_KUAN_AMT;
        BPCUABOX.OPP_AC = LNCSRPO.COMM_DATA.CTA_NO;
        BPCUABOX.CASH_NO = "117";
        BPCUABOX.RMK = "LOAN PRE-REPAYMENT";
        if (BPCUABOX.AMT != 0) {
            S000_CALL_BPZUABOX();
            if (pgmRtn) return;
        }
        WS_KOU_KUAN_AMT = BPCUABOX.AMT;
    }
    public void B052_PARM_DEBIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCSDDDR);
        GDCSDDDR.DRAC = LNCSRPO.COMM_DATA.ACAMT[WS_I-1].REC_AC2;
        GDCSDDDR.AMT = WS_KOU_KUAN_AMT;
        GDCSDDDR.CCY = LNCSRPO.COMM_DATA.CCY;
        DDCUDRAC.CCY_TYPE = LNCSRPO.COMM_DATA.ACAMT[1-1].CCY_TYP;
        CEP.TRC(SCCGWA, LNCSRPO.COMM_DATA.ACAMT[1-1].CCY_TYP);
        GDCSDDDR.STLT = ' ';
        GDCSDDDR.CRAC = LNRAGRE.PAPER_NO;
        if (GDCSDDDR.AMT != 0) {
            WS_SEQ1 += 1;
            S000_CALL_GDZSDDDR();
            if (pgmRtn) return;
        }
        WS_KOU_KUAN_AMT = GDCSDDDR.AMT;
    }
    public void B052_SUBS_LOAN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRSUBS);
        LNRSUBS.KEY.PROJ_NO = LNRRELA.PROJ_NO;
        T000_READ_LNTSUBS();
        if (pgmRtn) return;
        if (LNCSRPO.COMM_DATA.TOT_P_AMT > 0) {
            if (LNRSUBS.PAY_AC.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_SUBS_PAY_AC_EMPTY, LNCSRPO.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.DATA.AGR_NO = LNRSUBS.PAY_AC;
            CICQACRI.FUNC = 'A';
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            WS_ACR_BBR[WS_BBR_I-1] = CICQACRI.O_DATA.O_OWNER_BK;
            WS_BBR_I += 1;
            WS_TX_AC = LNRSUBS.PAY_AC;
            WS_TX_AMT = LNCSRPO.COMM_DATA.TOT_P_AMT;
            B096_TX_IA_DR_PROC();
            if (pgmRtn) return;
        }
        LNCSRPO.COMM_DATA.ACAMT[1-1].REC_AC2 = LNRSUBS.PAY_AC;
        LNCSRPO.COMM_DATA.ACAMT[1-1].STL_MTH2 = K_INTERNAL;
        LNCSRPO.COMM_DATA.ACAMT[1-1].PAY_AMT2 = LNCSRPO.COMM_DATA.TOT_P_AMT;
        WS_TOT_I_AMT = LNCSRPO.COMM_DATA.TOT_I_AMT + LNCSRPO.COMM_DATA.TOT_O_AMT + LNCSRPO.COMM_DATA.TOT_L_AMT;
        if (WS_TOT_I_AMT > 0) {
            if (LNRSUBS.IA_AC.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_SUBS_IA_AC_EMPTY, LNCSRPO.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.DATA.AGR_NO = LNRSUBS.IA_AC;
            CICQACRI.FUNC = 'A';
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            WS_ACR_BBR[WS_BBR_I-1] = CICQACRI.O_DATA.O_OWNER_BK;
            WS_BBR_I += 1;
            WS_TX_AC = LNRSUBS.IA_AC;
            WS_TX_AMT = WS_TOT_I_AMT;
            B096_TX_IA_DR_PROC();
            if (pgmRtn) return;
        }
        LNCSRPO.COMM_DATA.ACAMT[2-1].REC_AC2 = LNRSUBS.IA_AC;
        LNCSRPO.COMM_DATA.ACAMT[2-1].STL_MTH2 = K_INTERNAL;
        LNCSRPO.COMM_DATA.ACAMT[2-1].PAY_AMT2 = WS_TOT_I_AMT;
        WS_KOU_KUAN_AMT = LNCSRPO.COMM_DATA.TOT_AMT;
        WS_KOU_TOT_AMT = LNCSRPO.COMM_DATA.TOT_AMT;
        WS_PAY_AMT = 0;
    }
    public void B052_SUBS_DEBIT_PROC_AI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICPQIA);
        AICPQIA.SIGN = 'D';
        AICPQIA.CD.BUSI_KND = "LNSUBAC";
        AICPQIA.BR = LNCCLNQ.DATA.BOOK_BR;
        AICPQIA.CCY = LNCSRPO.COMM_DATA.CCY;
        S000_CALL_AIZPQIA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICPQIA.AC);
        CEP.TRC(SCCGWA, LNRSUBS.SUS_AC_SEQ);
        IBS.CPY2CLS(SCCGWA, AICPQIA.AC, WS_IA_AC);
        if (LNRSUBS.SUS_AC_SEQ != 0) {
            WS_IA_AC.WS_IA_AC_NO = LNRSUBS.SUS_AC_SEQ;
        }
        CEP.TRC(SCCGWA, WS_IA_AC);
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = IBS.CLS2CPY(SCCGWA, WS_IA_AC);
        CEP.TRC(SCCGWA, LNRSUBS.POST_CREV_NO);
        if (LNRSUBS.POST_CREV_NO.trim().length() > 0) {
            AICUUPIA.DATA.RVS_NO = LNRSUBS.POST_CREV_NO;
        }
        AICUUPIA.DATA.CCY = LNCSRPO.COMM_DATA.CCY;
        AICUUPIA.DATA.AMT = WS_SUBS_TOT_AMT;
        AICUUPIA.DATA.VALUE_DATE = LNCSRPO.COMM_DATA.TR_VAL_DTE;
        AICUUPIA.DATA.EVENT_CODE = "DR";
        if (AICUUPIA.DATA.AMT != 0) {
            S000_CALL_AIZUUPIA();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, AICUUPIA.DATA.RVS_NO);
        CEP.TRC(SCCGWA, LNRSUBS.POST_CREV_NO);
        if (LNRSUBS.POST_CREV_NO.trim().length() == 0 
            || !AICUUPIA.DATA.RVS_NO.equalsIgnoreCase(LNRSUBS.POST_CREV_NO)) {
            LNRSUBS.POST_CREV_NO = AICUUPIA.DATA.RVS_NO;
        }
        LNCSRPO.COMM_DATA.ACAMT[2-1].STL_MTH2 = K_INTERNAL;
        LNCSRPO.COMM_DATA.ACAMT[2-1].REC_AC2 = IBS.CLS2CPY(SCCGWA, WS_IA_AC);
        LNCSRPO.COMM_DATA.ACAMT[2-1].PAY_AMT2 = WS_SUBS_TOT_AMT;
    }
    public void B042_GET_SUSP_AC_AVABAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICPQMIB);
        AICPQMIB.INPUT_DATA.AC = WS_AC;
        AICPQMIB.INPUT_DATA.CCY = WS_AC_CCY;
        S000_CALL_AIZPQMIB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICPQMIB.OUTPUT_DATA.CBAL);
        CEP.TRC(SCCGWA, AICPQMIB.OUTPUT_DATA.BAL_RFLG);
        if (AICPQMIB.OUTPUT_DATA.CBAL < 0) {
            WS_PQMIB_CBAL = AICPQMIB.OUTPUT_DATA.CBAL * ( -1 );
        } else {
            WS_PQMIB_CBAL = AICPQMIB.OUTPUT_DATA.CBAL;
        }
    }
    public void B102_GET_AC_BAL() throws IOException,SQLException,Exception {
        if (WS_AC_TYP.equalsIgnoreCase(K_DD_AC)
            || WS_AC_TYP.equalsIgnoreCase(K_DC_AC)
            || WS_AC_TYP.equalsIgnoreCase(K_EB_AC)) {
            B090_GET_DD_AC_BAL();
            if (pgmRtn) return;
        } else if (WS_AC_TYP.equalsIgnoreCase(K_INTERNAL)) {
            B920_GET_SUSP_AC_AVABAL();
            if (pgmRtn) return;
        } else if (WS_AC_TYP.equalsIgnoreCase(K_IB_AC)) {
            B105_GET_NOS_AC_AVABAL();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B920_GET_SUSP_AC_AVABAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICPQMIB);
        AICPQMIB.INPUT_DATA.AC = WS_AC;
        AICPQMIB.INPUT_DATA.CCY = WS_AC_CCY;
        S000_CALL_AIZPQMIB();
        if (pgmRtn) return;
        WS_AC_AMT = AICPQMIB.OUTPUT_DATA.CBAL;
    }
    public void B105_GET_NOS_AC_AVABAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCQINF);
        IBCQINF.INPUT_DATA.NOSTRO_CD = WS_AC;
        IBCQINF.INPUT_DATA.CCY = WS_AC_CCY;
        S000_CALL_IBZQINF();
        if (pgmRtn) return;
        WS_AC_AMT = IBCQINF.OUTPUT_DATA.VALUE_BAL;
    }
    public void B052_TRUS_HRDTAX_PROC() throws IOException,SQLException,Exception {
        B451_GET_TRUS_SETL_INF();
        if (pgmRtn) return;
        B453_TRUS_I_SETL_PROCESS();
        if (pgmRtn) return;
    }
    public void B451_GET_TRUS_SETL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRSETL);
        IBS.init(SCCGWA, LNCRSETL);
        LNCRSETL.FUNC = 'I';
        LNRSETL.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        LNRSETL.KEY.CI_TYPE = 'C';
        LNRSETL.KEY.CCY = LNCSRPO.COMM_DATA.CCY;
        LNRSETL.KEY.SETTLE_TYPE = '4';
        LNRSETL.KEY.SEQ_NO = 0;
        S000_CALL_LNZRSETL();
        if (pgmRtn) return;
        WS_SETL_INFO.WS_TRUS_P_AC_TYP = LNRSETL.AC_TYP;
        WS_SETL_INFO.WS_TRUS_P_AC = LNRSETL.AC;
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.DATA.AGR_NO = WS_SETL_INFO.WS_TRUS_P_AC;
        CICQACRI.FUNC = 'A';
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        WS_ACR_BBR[WS_BBR_I-1] = CICQACRI.O_DATA.O_OWNER_BK;
        WS_BBR_I += 1;
        IBS.init(SCCGWA, LNRSETL);
        IBS.init(SCCGWA, LNCRSETL);
        LNCRSETL.FUNC = 'I';
        LNRSETL.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        LNRSETL.KEY.CI_TYPE = 'C';
        LNRSETL.KEY.CCY = LNCSRPO.COMM_DATA.CCY;
        LNRSETL.KEY.SETTLE_TYPE = 'B';
        LNRSETL.KEY.SEQ_NO = 0;
        S000_CALL_LNZRSETL();
        if (pgmRtn) return;
        WS_SETL_INFO.WS_TRUS_I_AC_TYP = LNRSETL.AC_TYP;
        WS_SETL_INFO.WS_TRUS_I_AC = LNRSETL.AC;
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.DATA.AGR_NO = WS_SETL_INFO.WS_TRUS_I_AC;
        CICQACRI.FUNC = 'A';
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        WS_ACR_BBR[WS_BBR_I-1] = CICQACRI.O_DATA.O_OWNER_BK;
        WS_BBR_I += 1;
    }
    public void B453_TRUS_I_SETL_PROCESS() throws IOException,SQLException,Exception {
        WS_SETL_INFO.WS_TRUS_I_AMT = LNCSRPO.COMM_DATA.TOT_I_AMT + LNCSRPO.COMM_DATA.TOT_O_AMT + LNCSRPO.COMM_DATA.TOT_L_AMT;
        WS_TX_AC_TYP = WS_SETL_INFO.WS_TRUS_I_AC_TYP;
        WS_TX_AC = WS_SETL_INFO.WS_TRUS_I_AC;
        WS_TX_AMT = WS_SETL_INFO.WS_TRUS_I_AMT;
        if (WS_SETL_INFO.WS_TRUS_I_AMT > 0) {
            B095_TX_AMT_CR_PROC();
            if (pgmRtn) return;
        }
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if (!WS_CTL_STSW.substring(54 - 1, 54 + 1 - 1).equalsIgnoreCase("1")) {
            WS_AC_TYP = WS_SETL_INFO.WS_TRUS_I_AC_TYP;
            WS_AC = WS_SETL_INFO.WS_TRUS_I_AC;
            WS_AC_CCY = LNCSRPO.COMM_DATA.CCY;
            B102_GET_AC_BAL();
            if (pgmRtn) return;
            if (WS_AC_AMT >= LNCSRPO.COMM_DATA.HRG_AMT) {
                WS_SETL_INFO.WS_TRUS_F_AMT = LNCSRPO.COMM_DATA.HRG_AMT;
            } else {
                LNCSRPO.COMM_DATA.HRG_AMT = WS_AC_AMT;
                WS_SETL_INFO.WS_TRUS_F_AMT = LNCSRPO.COMM_DATA.HRG_AMT;
            }
            CEP.TRC(SCCGWA, WS_AC_AMT);
            CEP.TRC(SCCGWA, WS_SETL_INFO.WS_TRUS_F_AMT);
            if (WS_SETL_INFO.WS_TRUS_F_AMT != 0) {
                B454_TRUS_FEE_CHARGE_PROCESS();
                if (pgmRtn) return;
            }
        }
    }
    public void B454_TRUS_FEE_CHARGE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOFSVR);
        BPCOFSVR.FUNC = 'I';
        BPCOFSVR.KEY.SVR_NO = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        S000_CALL_BPZFSSVR();
        if (pgmRtn) return;
        for (WS_I = 1; WS_I <= 20 
            && BPCOFSVR.VAL.DATA[WS_I-1].FEE_CODE.trim().length() != 0; WS_I += 1) {
            CEP.TRC(SCCGWA, BPCOFSVR.VAL.DATA[WS_I-1].PROD_CODE);
            CEP.TRC(SCCGWA, BPCOFSVR.VAL.DATA[WS_I-1].FEE_CODE);
            CEP.TRC(SCCGWA, LNCSRPO.COMM_DATA.PROD_CD);
            if (BPCOFSVR.VAL.DATA[WS_I-1].PROD_CODE.equalsIgnoreCase(LNCSRPO.COMM_DATA.PROD_CD)) {
                WS_FEE_CODE_HRG = BPCOFSVR.VAL.DATA[WS_I-1].FEE_CODE;
            }
        }
        IBS.init(SCCGWA, BPCFFTXI);
        JIBS_NumStr = "" + 0;
        BPCFFTXI.TX_DATA.AUH_FLG = JIBS_NumStr.charAt(0);
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        if (WS_SETL_INFO.WS_TRUS_I_AC_TYP.equalsIgnoreCase(K_DD_AC)) {
            BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '0';
        } else if (WS_SETL_INFO.WS_TRUS_I_AC_TYP.equalsIgnoreCase(K_INTERNAL)) {
            BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '3';
        } else if (WS_SETL_INFO.WS_TRUS_I_AC_TYP.equalsIgnoreCase(K_IB_AC)) {
            BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '2';
        } else if (WS_SETL_INFO.WS_TRUS_I_AC_TYP.equalsIgnoreCase(K_DC_AC)
            || WS_SETL_INFO.WS_TRUS_I_AC_TYP.equalsIgnoreCase(K_EB_AC)) {
            BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '4';
            BPCFFTXI.TX_DATA.CARD_PSBK_NO = WS_SETL_INFO.WS_TRUS_I_AC;
        } else {
        }
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = WS_SETL_INFO.WS_TRUS_I_AC;
        if (CICACCU.DATA.AGR_NO.trim().length() > 0 
            && (BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP == '0' 
            || BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP == '4')) {
            S000_CALL_CIZACCU();
            if (pgmRtn) return;
        }
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = WS_SETL_INFO.WS_TRUS_I_AC;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = LNCSRPO.COMM_DATA.CCY;
        BPCFFTXI.TX_DATA.SVR_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCFFTXI.TX_DATA.CI_NO = CICACCU.DATA.CI_NO;
        BPCFFTXI.TX_DATA.FEE_CTRT = " ";
        BPCFFTXI.TX_DATA.BSNS_NO = LNCSRPO.COMM_DATA.CTA_NO;
        BPCFFTXI.TX_DATA.PROC_TYPE = '0';
        S000_CALL_BPZFFTXI();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCFFCON);
        BPCFFCON.FEE_INFO.FEE_CNT = 1;
        CEP.TRC(SCCGWA, WS_FEE_CODE_HRG);
        CEP.TRC(SCCGWA, WS_SETL_INFO.WS_TRUS_F_AMT);
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_CODE = WS_FEE_CODE_HRG;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_BAS = WS_SETL_INFO.WS_TRUS_F_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_AMT = WS_SETL_INFO.WS_TRUS_F_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_FLG = '0';
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_AC_TY = '0';
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_AC = WS_SETL_INFO.WS_TRUS_I_AC;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_CCY = LNCSRPO.COMM_DATA.CCY;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_CCY = LNCSRPO.COMM_DATA.CCY;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_BAS = WS_SETL_INFO.WS_TRUS_F_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_AMT = WS_SETL_INFO.WS_TRUS_F_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].ADJ_AMT = WS_SETL_INFO.WS_TRUS_F_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].AMO_FLG = 'Y';
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].AMO_STDT = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].AMO_EDDT = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFCON.FEE_INFO.ACCOUNT_BR = LNCSRPO.COMM_DATA.BR;
        S000_CALL_BPZFFCON();
        if (pgmRtn) return;
    }
    public void B052_FUND_LOAN_PROC() throws IOException,SQLException,Exception {
        B451_GET_FUND_SETL_INF();
        if (pgmRtn) return;
        if (LNCSRPO.COMM_DATA.TOT_P_AMT > 0) {
            B452_FUND_P_SETL_PROCESS();
            if (pgmRtn) return;
        }
        if (LNCSRPO.COMM_DATA.TOT_I_AMT > 0 
            || LNCSRPO.COMM_DATA.TOT_O_AMT > 0 
            || LNCSRPO.COMM_DATA.TOT_L_AMT > 0) {
            B453_FUND_IOL_SETL_PROCESS();
            if (pgmRtn) return;
        }
        B454_FUND_AMT_PROCESS();
        if (pgmRtn) return;
    }
    public void B451_GET_FUND_SETL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRFUND);
        IBS.init(SCCGWA, LNCRFUND);
        LNCRFUND.FUNC = 'I';
        LNRFUND.KEY.PROJ_NO = LNCCLNQ.DATA.PD_PROJ_NO;
        S000_CALL_LNZRFUND();
        if (pgmRtn) return;
        if (LNRFUND.PAY_FLG == 'Y') {
            IBS.init(SCCGWA, AICPQIA);
            AICPQIA.SIGN = 'C';
            AICPQIA.CD.BUSI_KND = "LN01";
            AICPQIA.BR = LNRFUND.BOOK_BR;
            AICPQIA.CCY = LNCSRPO.COMM_DATA.CCY;
            S000_CALL_AIZPQIA();
            if (pgmRtn) return;
            WS_P_TMP_AC = AICPQIA.AC;
            CEP.TRC(SCCGWA, WS_P_TMP_AC);
            IBS.init(SCCGWA, AICPQIA);
            AICPQIA.SIGN = 'C';
            AICPQIA.CD.BUSI_KND = "LN02";
            AICPQIA.BR = LNRFUND.BOOK_BR;
            AICPQIA.CCY = LNCSRPO.COMM_DATA.CCY;
            S000_CALL_AIZPQIA();
            if (pgmRtn) return;
            WS_I_TMP_AC = AICPQIA.AC;
            CEP.TRC(SCCGWA, WS_I_TMP_AC);
            WS_ACR_BBR[WS_BBR_I-1] = LNRFUND.BOOK_BR;
            WS_BBR_I += 1;
        }
    }
    public void B452_FUND_P_SETL_PROCESS() throws IOException,SQLException,Exception {
        if (LNRFUND.PAY_FLG == 'Y') {
            WS_TX_AC_TYP = K_INTERNAL;
            WS_TX_AC = WS_P_TMP_AC;
        } else {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.DATA.AGR_NO = LNRFUND.PAY_P_AC;
            CICQACRI.FUNC = 'A';
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            WS_ACR_BBR[WS_BBR_I-1] = CICQACRI.O_DATA.O_OWNER_BK;
            WS_BBR_I += 1;
            B101_GET_AC_TYPE();
            if (pgmRtn) return;
            WS_TX_AC_TYP = WS_SETL_INFO.WS_FUND_AC_TYP;
            WS_TX_AC = LNRFUND.PAY_P_AC;
        }
        WS_TX_AMT = LNCSRPO.COMM_DATA.TOT_P_AMT;
        B095_TX_AMT_CR_PROC();
        if (pgmRtn) return;
    }
    public void B453_FUND_IOL_SETL_PROCESS() throws IOException,SQLException,Exception {
        if (LNRFUND.PAY_FLG == 'Y') {
            WS_TX_AC_TYP = K_INTERNAL;
            WS_TX_AC = WS_I_TMP_AC;
        } else {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.DATA.AGR_NO = LNRFUND.PAY_I_AC;
            CICQACRI.FUNC = 'A';
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            WS_ACR_BBR[WS_BBR_I-1] = CICQACRI.O_DATA.O_OWNER_BK;
            WS_BBR_I += 1;
            B101_GET_AC_TYPE();
            if (pgmRtn) return;
            WS_TX_AC_TYP = WS_SETL_INFO.WS_FUND_AC_TYP;
            WS_TX_AC = LNRFUND.PAY_I_AC;
        }
        WS_TX_AMT = LNCSRPO.COMM_DATA.TOT_I_AMT + LNCSRPO.COMM_DATA.TOT_O_AMT + LNCSRPO.COMM_DATA.TOT_L_AMT;
        B095_TX_AMT_CR_PROC();
        if (pgmRtn) return;
    }
    public void B454_FUND_AMT_PROCESS() throws IOException,SQLException,Exception {
        if (LNCSRPO.COMM_DATA.TOT_P_AMT > 0) {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.DATA.AGR_NO = LNRFUND.FUND_AC;
            CICQACRI.FUNC = 'A';
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            WS_ACR_BBR[WS_BBR_I-1] = CICQACRI.O_DATA.O_OWNER_BK;
            WS_BBR_I += 1;
            B101_GET_AC_TYPE();
            if (pgmRtn) return;
            WS_TX_AC_TYP = WS_SETL_INFO.WS_FUND_AC_TYP;
            WS_TX_AC = LNRFUND.FUND_AC;
            WS_TX_AMT = LNCSRPO.COMM_DATA.TOT_P_AMT;
            B096_TX_AMT_PROC();
            if (pgmRtn) return;
        }
        if (LNRFUND.PAY_FLG == 'Y') {
            IBS.init(SCCGWA, LNRFUND);
            LNCRFUND.FUNC = 'R';
            LNRFUND.KEY.PROJ_NO = LNCCLNQ.DATA.PD_PROJ_NO;
            S000_CALL_LNZRFUND();
            if (pgmRtn) return;
            LNRFUND.TOT_PAY_P += LNCSRPO.COMM_DATA.TOT_P_AMT;
            LNRFUND.TOT_PAY_I += LNCSRPO.COMM_DATA.TOT_I_AMT;
            LNRFUND.TOT_PAY_I += LNCSRPO.COMM_DATA.TOT_O_AMT;
            LNRFUND.TOT_PAY_I += LNCSRPO.COMM_DATA.TOT_L_AMT;
            LNCRFUND.FUNC = 'U';
            S000_CALL_LNZRFUND();
            if (pgmRtn) return;
        }
    }
    public void B061_SOLD_OUT_LOAN_PROC() throws IOException,SQLException,Exception {
        WS_TR_TOT_AMT = LNCSRPO.COMM_DATA.TOT_AMT;
        IBS.init(SCCGWA, LNRAGRE);
        LNRAGRE.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        T000_READ_LNTAGRE();
        if (pgmRtn) return;
        WS_CTA_NO = LNRAGRE.REL_CTA_NO;
        B072_SOLD_OUT_SETL_PROCESS();
        if (pgmRtn) return;
        if (WS_TR_TOT_AMT != 0) {
            B461_GET_SOLD_OUT_SETL_INF();
            if (pgmRtn) return;
            B462_SOLD_OUT_SETL_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void B061_CHK_FTA_TYP() throws IOException,SQLException,Exception {
        if (WS_BBR_I > 21) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_FTA_BR_GREATER_20, LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCPCFTA);
        for (WS_I = 1; WS_I < WS_BBR_I 
            && WS_I <= 20; WS_I += 1) {
            LNCPCFTA.BR_GP[WS_I-1].BR = WS_ACR_BBR[WS_I-1];
        }
        S000_CALL_LNZPCFTA();
        if (pgmRtn) return;
        if (LNCPCFTA.FTA_FLG == 'Y') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_NO_TRAN_FTA_NOT_SAME, LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B461_GET_SOLD_OUT_SETL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRSETL);
        IBS.init(SCCGWA, LNCRSETL);
        LNCRSETL.FUNC = 'I';
        LNRSETL.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        LNRSETL.KEY.CCY = LNCSRPO.COMM_DATA.CCY;
        LNRSETL.KEY.CI_TYPE = 'T';
        LNRSETL.KEY.SETTLE_TYPE = 'D';
        S000_CALL_LNZRSETL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNRSETL.AC);
        WS_TX_AC_TYP = LNRSETL.AC_TYP;
        WS_CTPY_AC = LNRSETL.AC;
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.DATA.AGR_NO = LNRSETL.AC;
        CICQACRI.FUNC = 'A';
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        WS_ACR_BBR[WS_BBR_I-1] = CICQACRI.O_DATA.O_OWNER_BK;
        WS_BBR_I += 1;
    }
    public void B462_SOLD_OUT_SETL_PROCESS() throws IOException,SQLException,Exception {
        WS_TX_AC = WS_CTPY_AC;
        WS_TX_AMT = WS_TR_TOT_AMT;
        B095_TX_AMT_CR_PROC();
        if (pgmRtn) return;
    }
    public void B473_GET_SOLD_OUT_SETL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRSETL);
        IBS.init(SCCGWA, LNCRSETL);
        LNCRSETL.FUNC = 'I';
        LNRSETL.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        LNRSETL.KEY.CCY = LNCSRPO.COMM_DATA.CCY;
        LNRSETL.KEY.SETTLE_TYPE = '6';
        LNRSETL.KEY.CI_TYPE = 'E';
        S000_CALL_LNZRSETL();
        if (pgmRtn) return;
        WS_TX_AC_TYP = LNRSETL.AC_TYP;
        WS_CTPY_AC = LNRSETL.AC;
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.DATA.AGR_NO = LNRSETL.AC;
        CICQACRI.FUNC = 'A';
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        WS_ACR_BBR[WS_BBR_I-1] = CICQACRI.O_DATA.O_OWNER_BK;
        WS_BBR_I += 1;
    }
    public void B474_AMT_SETL_PROCESS() throws IOException,SQLException,Exception {
        WS_TX_AC = WS_CTPY_AC;
        WS_TX_AMT = WS_LEFT_AMT;
        B095_TX_AMT_CR_PROC();
        if (pgmRtn) return;
    }
    public void B472_AMT_SETL_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCIGEAI);
        LNCIGEAI.COMM_DATA.BUS_KND = "LNPMTAC";
        LNCIGEAI.COMM_DATA.CCY = LNCSRPO.COMM_DATA.CCY;
        LNCIGEAI.COMM_DATA.AC_NO = WS_SUSP_AC;
        LNCIGEAI.COMM_DATA.SIGN = 'D';
        S000_CALL_LNZIGEAI();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.DATA.AGR_NO = LNCIGEAI.COMM_DATA.AI_AC;
        CICQACRI.FUNC = 'A';
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        WS_ACR_BBR[WS_BBR_I-1] = CICQACRI.O_DATA.O_OWNER_BK;
        WS_BBR_I += 1;
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = LNCIGEAI.COMM_DATA.AI_AC;
        AICUUPIA.DATA.EVENT_CODE = "CR";
        AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.CCY = LNCSRPO.COMM_DATA.CCY;
        AICUUPIA.DATA.AMT = WS_LEFT_AMT;
        AICUUPIA.DATA.VALUE_DATE = LNCSRPO.COMM_DATA.TR_VAL_DTE;
        AICUUPIA.DATA.RVS_NO = " ";
        S000_CALL_AIZUUPIA();
        if (pgmRtn) return;
    }
    public void B071_SOLD_OUT_SETL_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCTPY);
        LNRCTPY.KEY.CONTRACT_NO = WS_CTA_NO;
        T000_READ_UPDATE_LNTCTPY();
        if (pgmRtn) return;
        WS_DIFF_I_AMT = LNRCTPY.I_REC_AMT - LNRCTPY.I_PAY_AMT;
        WS_DIFF_O_AMT = LNRCTPY.O_REC_AMT - LNRCTPY.O_PAY_AMT;
        WS_DIFF_L_AMT = LNRCTPY.L_REC_AMT - LNRCTPY.L_PAY_AMT;
        CEP.TRC(SCCGWA, WS_DIFF_I_AMT);
        CEP.TRC(SCCGWA, WS_DIFF_O_AMT);
        CEP.TRC(SCCGWA, WS_DIFF_L_AMT);
        WS_LEFT_TOT_AMT = LNCSRPO.COMM_DATA.TOT_AMT;
        WS_LEFT_AMT = WS_LEFT_TOT_AMT;
        if (WS_DIFF_I_AMT > 0) {
            if (WS_LEFT_TOT_AMT > WS_DIFF_I_AMT) {
                WS_PAY_I_AMT = WS_DIFF_I_AMT;
                LNRCTPY.I_PAY_AMT = LNRCTPY.I_REC_AMT;
                WS_LEFT_AMT = WS_LEFT_TOT_AMT - WS_DIFF_I_AMT;
            } else {
                WS_PAY_I_AMT = WS_LEFT_TOT_AMT;
                LNRCTPY.I_PAY_AMT = LNRCTPY.I_PAY_AMT + WS_LEFT_TOT_AMT;
                WS_LEFT_AMT = 0;
            }
            WS_LEFT_TOT_AMT = WS_LEFT_AMT;
        }
        if (WS_DIFF_O_AMT > 0) {
            if (WS_LEFT_TOT_AMT > WS_DIFF_O_AMT) {
                WS_PAY_O_AMT = WS_DIFF_O_AMT;
                LNRCTPY.O_PAY_AMT = LNRCTPY.O_REC_AMT;
                WS_LEFT_AMT = WS_LEFT_TOT_AMT - WS_DIFF_O_AMT;
            } else {
                WS_PAY_O_AMT = WS_LEFT_TOT_AMT;
                LNRCTPY.O_PAY_AMT = LNRCTPY.O_PAY_AMT + WS_LEFT_TOT_AMT;
                WS_LEFT_AMT = 0;
            }
            WS_LEFT_TOT_AMT = WS_LEFT_AMT;
        }
        if (WS_DIFF_L_AMT > 0) {
            if (WS_LEFT_TOT_AMT > WS_DIFF_L_AMT) {
                WS_PAY_L_AMT = WS_DIFF_L_AMT;
                LNRCTPY.L_PAY_AMT = LNRCTPY.L_REC_AMT;
                WS_LEFT_AMT = WS_LEFT_TOT_AMT - WS_DIFF_L_AMT;
            } else {
                WS_PAY_L_AMT = WS_LEFT_TOT_AMT;
                LNRCTPY.L_PAY_AMT = LNRCTPY.L_PAY_AMT + WS_LEFT_TOT_AMT;
                WS_LEFT_AMT = 0;
            }
            WS_LEFT_TOT_AMT = WS_LEFT_AMT;
        }
        CEP.TRC(SCCGWA, WS_LEFT_AMT);
        CEP.TRC(SCCGWA, WS_PAY_I_AMT);
        CEP.TRC(SCCGWA, WS_PAY_O_AMT);
        CEP.TRC(SCCGWA, WS_PAY_L_AMT);
        T000_REWRITE_LNTCTPY();
        if (pgmRtn) return;
    }
    public void B072_SOLD_OUT_SETL_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCTPY);
        LNRCTPY.KEY.CONTRACT_NO = WS_CTA_NO;
        T000_READ_UPDATE_LNTCTPY();
        if (pgmRtn) return;
        WS_DIFF_I_AMT = LNRCTPY.I_REC_AMT - LNRCTPY.I_PAY_AMT;
        WS_DIFF_O_AMT = LNRCTPY.O_REC_AMT - LNRCTPY.O_PAY_AMT;
        WS_DIFF_L_AMT = LNRCTPY.L_REC_AMT - LNRCTPY.L_PAY_AMT;
        CEP.TRC(SCCGWA, WS_DIFF_I_AMT);
        CEP.TRC(SCCGWA, WS_DIFF_O_AMT);
        CEP.TRC(SCCGWA, WS_DIFF_L_AMT);
        if (WS_DIFF_I_AMT < LNCSRPO.COMM_DATA.TOT_I_AMT) {
            WS_PAY_I_AMT = WS_DIFF_I_AMT;
        } else {
            WS_PAY_I_AMT = LNCSRPO.COMM_DATA.TOT_I_AMT;
        }
        if (WS_DIFF_O_AMT < LNCSRPO.COMM_DATA.TOT_O_AMT) {
            WS_PAY_O_AMT = WS_DIFF_O_AMT;
        } else {
            WS_PAY_O_AMT = LNCSRPO.COMM_DATA.TOT_O_AMT;
        }
        if (WS_DIFF_L_AMT < LNCSRPO.COMM_DATA.TOT_L_AMT) {
            WS_PAY_L_AMT = WS_DIFF_L_AMT;
        } else {
            WS_PAY_L_AMT = LNCSRPO.COMM_DATA.TOT_L_AMT;
        }
        LNRCTPY.I_PAY_AMT += WS_PAY_I_AMT;
        LNRCTPY.O_PAY_AMT += WS_PAY_O_AMT;
        LNRCTPY.L_PAY_AMT += WS_PAY_L_AMT;
        CEP.TRC(SCCGWA, WS_PAY_I_AMT);
        CEP.TRC(SCCGWA, WS_PAY_O_AMT);
        CEP.TRC(SCCGWA, WS_PAY_L_AMT);
        T000_REWRITE_LNTCTPY();
        if (pgmRtn) return;
    }
    public void B095_TX_AMT_CR_PROC() throws IOException,SQLException,Exception {
        if (WS_TX_AC_TYP.equalsIgnoreCase(K_INTERNAL)) {
            B095_TX_IA_CR_PROC();
            if (pgmRtn) return;
        } else if (WS_TX_AC_TYP.equalsIgnoreCase(K_IB_AC)) {
            B095_TX_IB_CR_PROC();
            if (pgmRtn) return;
        } else {
            B095_TX_DD_CR_PROC();
            if (pgmRtn) return;
        }
    }
    public void B095_TX_IA_CR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = WS_TX_AC;
        AICUUPIA.DATA.EVENT_CODE = "CR";
        AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.CCY = LNCSRPO.COMM_DATA.CCY;
        AICUUPIA.DATA.AMT = WS_TX_AMT;
        AICUUPIA.DATA.VALUE_DATE = LNCSRPO.COMM_DATA.TR_VAL_DTE;
        AICUUPIA.DATA.RVS_NO = " ";
        AICUUPIA.DATA.PAY_MAN = LNCCLNQ.DATA.CI_CNAME;
        if (AICUUPIA.DATA.AMT != 0) {
            S000_CALL_AIZUUPIA();
            if (pgmRtn) return;
        }
    }
    public void B095_TX_IB_CR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCPOSTA);
        IBCPOSTA.NOSTRO_CD = WS_TX_AC;
        IBCPOSTA.CCY = LNCSRPO.COMM_DATA.CCY;
        IBCPOSTA.AMT = WS_TX_AMT;
        IBCPOSTA.TXTYPE = "01";
        IBCPOSTA.VAL_DATE = LNCSRPO.COMM_DATA.TR_VAL_DTE;
        IBCPOSTA.ENTRY_FLG = '1';
        IBCPOSTA.SIGN = 'C';
        IBCPOSTA.ACT_CTR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        IBCPOSTA.ACT_CTR = LNCSRPO.COMM_DATA.BR;
        IBCPOSTA.OUR_REF = LNCSRPO.COMM_DATA.CTA_NO;
        IBCPOSTA.THR_REF = LNCSRPO.COMM_DATA.CTA_NO;
        IBCPOSTA.TX_MMO = LNCSRPO.COMM_DATA.MMO;
        if (IBCPOSTA.AMT != 0) {
            S000_CALL_IBZCRAC();
            if (pgmRtn) return;
        }
    }
    public void B095_TX_DD_CR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUCRAC);
        DDCUCRAC.BANK_CR_FLG = 'Y';
        DDCUCRAC.TX_TYPE = 'T';
        if (WS_TX_AC_TYP.equalsIgnoreCase("05") 
            || WS_TX_AC_TYP.equalsIgnoreCase("06")) {
            DDCUCRAC.CARD_NO = WS_TX_AC;
        } else {
            DDCUCRAC.AC = WS_TX_AC;
        }
        DDCUCRAC.CCY = LNCSRPO.COMM_DATA.CCY;
        DDCUCRAC.CCY_TYPE = LNCSRPO.COMM_DATA.ACAMT[1-1].CCY_TYP;
        CEP.TRC(SCCGWA, LNCSRPO.COMM_DATA.ACAMT[1-1].CCY_TYP);
        DDCUCRAC.TX_AMT = WS_TX_AMT;
        DDCUCRAC.VAL_DATE = LNCSRPO.COMM_DATA.TR_VAL_DTE;
        DDCUCRAC.TX_MMO = LNCSRPO.COMM_DATA.MMO;
        DDCUCRAC.OTHER_AC = LNRAGRE.PAPER_NO;
        DDCUCRAC.OTHER_CCY = LNCSRPO.COMM_DATA.CCY;
        DDCUCRAC.OTHER_AMT = WS_TX_AMT;
        if (DDCUCRAC.TX_AMT != 0) {
            S000_CALL_DDZUCRAC();
            if (pgmRtn) return;
        }
    }
    public void B096_TX_AMT_PROC() throws IOException,SQLException,Exception {
        if (WS_TX_AC_TYP.equalsIgnoreCase(K_INTERNAL)) {
            B096_TX_IA_DR_PROC();
            if (pgmRtn) return;
        } else if (WS_TX_AC_TYP.equalsIgnoreCase(K_IB_AC)) {
            B096_TX_IB_DR_PROC();
            if (pgmRtn) return;
        } else {
            B096_TX_DD_DR_PROC();
            if (pgmRtn) return;
        }
    }
    public void B096_TX_IA_DR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = WS_TX_AC;
        AICUUPIA.DATA.EVENT_CODE = "DR";
        AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.CCY = LNCSRPO.COMM_DATA.CCY;
        AICUUPIA.DATA.AMT = WS_TX_AMT;
        AICUUPIA.DATA.VALUE_DATE = LNCSRPO.COMM_DATA.TR_VAL_DTE;
        AICUUPIA.DATA.RVS_NO = " ";
        if (AICUUPIA.DATA.AMT != 0) {
            S000_CALL_AIZUUPIA();
            if (pgmRtn) return;
        }
    }
    public void B096_TX_IB_DR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCPOSTA);
        IBCPOSTA.NOSTRO_CD = WS_TX_AC;
        IBCPOSTA.CCY = LNCSRPO.COMM_DATA.CCY;
        IBCPOSTA.AMT = WS_TX_AMT;
        IBCPOSTA.TXTYPE = "01";
        IBCPOSTA.VAL_DATE = LNCSRPO.COMM_DATA.TR_VAL_DTE;
        IBCPOSTA.ENTRY_FLG = '1';
        IBCPOSTA.SIGN = 'D';
        IBCPOSTA.ACT_CTR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        IBCPOSTA.ACT_CTR = LNCSRPO.COMM_DATA.BR;
        IBCPOSTA.OUR_REF = LNCSRPO.COMM_DATA.CTA_NO;
        IBCPOSTA.THR_REF = LNCSRPO.COMM_DATA.CTA_NO;
        IBCPOSTA.TX_MMO = LNCSRPO.COMM_DATA.MMO;
        if (IBCPOSTA.AMT != 0) {
            S000_CALL_IBZDRAC();
            if (pgmRtn) return;
        }
    }
    public void B096_TX_DD_DR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUDRAC);
        DDCUDRAC.CHK_PSW_FLG = 'N';
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.BANK_DR_FLG = 'Y';
        DDCUDRAC.TX_BAL_FLG = 'N';
        DDCUDRAC.TSTS_TABL = "0014";
        if (WS_TX_AC_TYP.equalsIgnoreCase("05") 
            || WS_TX_AC_TYP.equalsIgnoreCase("06")) {
            DDCUDRAC.CARD_NO = WS_TX_AC;
        } else {
            DDCUDRAC.AC = WS_TX_AC;
        }
        DDCUDRAC.CCY = LNCSRPO.COMM_DATA.CCY;
        DDCUDRAC.CCY_TYPE = LNCSRPO.COMM_DATA.ACAMT[1-1].CCY_TYP;
        CEP.TRC(SCCGWA, LNCSRPO.COMM_DATA.ACAMT[1-1].CCY_TYP);
        DDCUDRAC.TX_AMT = WS_TX_AMT;
        DDCUDRAC.VAL_DATE = LNCSRPO.COMM_DATA.TR_VAL_DTE;
        DDCUDRAC.TX_MMO = LNCSRPO.COMM_DATA.MMO;
        DDCUDRAC.OTHER_AC = LNRAGRE.PAPER_NO;
        DDCUDRAC.OTHER_CCY = LNCSRPO.COMM_DATA.CCY;
        DDCUDRAC.OTHER_AMT = WS_TX_AMT;
        if (DDCUDRAC.TX_AMT != 0) {
            S000_CALL_DDZUDRAC();
            if (pgmRtn) return;
        }
    }
    public void B801_RECORD_CLRR_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSCLRR);
        IBS.init(SCCGWA, LNCTRANM);
        LNCTRANM.FUNC = '4';
        LNCTRANM.REC_DATA.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        LNCTRANM.REC_DATA.KEY.SUB_CTA_NO = 0;
        LNCTRANM.REC_DATA.KEY.REC_FLAG = 'B';
        LNCTRANM.REC_DATA.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCTRANM.REC_DATA.KEY.TR_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        LNCTRANM.REC_DATA.KEY.TXN_TYP = 'T';
        LNCTRANM.REC_DATA.KEY.TXN_TERM = 0;
        LNCTRANM.REC_DATA.KEY.TRAN_FLG = 'C';
        S000_CALL_LNZTRANM();
        if (pgmRtn) return;
        WS_CLR_DATA.WS_CLR_CTA_NO = LNCSCLR.COMM_DATA.CTA_NO;
        WS_CLR_DATA.WS_CLR_TR_VAL_DT = LNCSCLR.COMM_DATA.TR_VAL_DT;
        WS_CLR_DATA.WS_CLR_REC_AC2 = LNCSCLR.COMM_DATA.PAY_DATA[1-1].REC_AC2;
        WS_CLR_DATA.WS_CLR_NOR_P = LNCSCLR.COMM_DATA.NOR_P;
        WS_CLR_DATA.WS_CLR_OVR_P = LNCSCLR.COMM_DATA.OVR_P;
        WS_CLR_DATA.WS_CLR_NOR_I = LNCSCLR.COMM_DATA.NOR_I;
        WS_CLR_DATA.WS_CLR_OVR_I = LNCSCLR.COMM_DATA.OVR_I;
        WS_CLR_DATA.WS_CLR_NOR_O = LNCSCLR.COMM_DATA.NOR_O;
        WS_CLR_DATA.WS_CLR_NOR_L = LNCSCLR.COMM_DATA.NOR_L;
        WS_CLR_DATA.WS_CLR_PC_CLR_FLG = LNCSCLR.COMM_DATA.PC_CLR_FLG;
        WS_CLR_DATA.WS_CLR_P_CLR_FLG = LNCSCLR.COMM_DATA.P_CLR_FLG;
        WS_CLR_DATA.WS_CLR_I_CLR_FLG = LNCSCLR.COMM_DATA.I_CLR_FLG;
        LNCTRANM.FUNC = '2';
        WS_CLR_DATA_LEN = 1710;
        if (LNCTRANM.REC_DATA.TR_DATA == null) LNCTRANM.REC_DATA.TR_DATA = "";
        JIBS_tmp_int = LNCTRANM.REC_DATA.TR_DATA.length();
        for (int i=0;i<1024-JIBS_tmp_int;i++) LNCTRANM.REC_DATA.TR_DATA += " ";
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_CLR_DATA);
        LNCTRANM.REC_DATA.TR_DATA = JIBS_tmp_str[0] + LNCTRANM.REC_DATA.TR_DATA.substring(WS_CLR_DATA_LEN);
        S000_CALL_LNZTRANM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCSCLR.COMM_DATA.NOR_P);
        CEP.TRC(SCCGWA, LNCSCLR.COMM_DATA.OVR_P);
        CEP.TRC(SCCGWA, LNCSCLR.COMM_DATA.NOR_I);
        CEP.TRC(SCCGWA, LNCSCLR.COMM_DATA.OVR_I);
        CEP.TRC(SCCGWA, LNCSCLR.COMM_DATA.NOR_O);
        CEP.TRC(SCCGWA, LNCSCLR.COMM_DATA.NOR_L);
    }
    public void T000_READ_LNTLOAN() throws IOException,SQLException,Exception {
        LNTLOAN_RD = new DBParm();
        LNTLOAN_RD.TableName = "LNTLOAN";
        LNTLOAN_RD.where = "CONTRACT_NO = :LNRLOAN.KEY.CONTRACT_NO";
        IBS.READ(SCCGWA, LNRLOAN, this, LNTLOAN_RD);
    }
    public void S000_CALL_DDZUOBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DDZUOBAL", DDCUOBAL);
        if (DDCUOBAL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCUOBAL.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSCLR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-CLEAR", LNCSCLR);
        if (LNCSCLR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSCLR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSPDQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRV-PSTDUE-ENQ", LNCSPDQ, true);
        if (LNCSPDQ.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSPDQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZURPN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-REPAY-NORMAL", LNCURPN);
        CEP.TRC(SCCGWA, LNCURPN.RC);
        if (LNCURPN.RC.RC_RTNCODE != 0) {
            LNCSRPO.RC.RC_APP = LNCURPN.RC.RC_APP;
            LNCSRPO.RC.RC_RTNCODE = LNCURPN.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICRCM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-I-COMMITMENT-USE", LNCICRCM);
        if (LNCICRCM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCICRCM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC, true);
        CEP.TRC(SCCGWA, "DDZUDRAC SUCC");
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC, true);
    }
    public void S000_CALL_IBZDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-DEBIT-AC", IBCPOSTA);
        if (IBCPOSTA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCPOSTA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_IBZCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-CREDIT-AC", IBCPOSTA);
        if (IBCPOSTA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCPOSTA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PROC-FHIS", BPCPFHIS);
        if (BPCPFHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPFHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZSQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-DDZSQBAL", DDCSQBAL);
        if (DDCSQBAL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCSQBAL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRSETL() throws IOException,SQLException,Exception {
        LNCRSETL.REC_PTR = LNRSETL;
        LNCRSETL.REC_LEN = 266;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTSETL", LNCRSETL);
        if (LNCRSETL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRSETL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZTRANM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-TRAN-MAINT", LNCTRANM);
        CEP.TRC(SCCGWA, LNCTRANM.RC.RC_APP);
        if (LNCTRANM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCTRANM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZPQIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-IA", AICPQIA);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, AICPQIA.RC);
        if (AICPQIA.RC.RC_CODE != 0 
            || JIBS_tmp_str[1].trim().length() == 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQIA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSSUNM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRV-SUN-MAINT", LNCSSUNM);
        if (LNCSSUNM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSSUNM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZPCFTA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-P-CHK-FTA-TYP", LNCPCFTA);
        if (LNCPCFTA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCPCFTA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-ICTL-MAINT", LNCICTLM);
        if (LNCICTLM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCICTLM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T00_READ_LNTRELA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRELA);
        LNRRELA.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        LNRRELA.KEY.TYPE = 'S';
        LNTRELA_RD = new DBParm();
        LNTRELA_RD.TableName = "LNTRELA";
        LNTRELA_RD.where = "CONTRACT_NO = :LNRRELA.KEY.CONTRACT_NO "
            + "AND TYPE = :LNRRELA.KEY.TYPE";
        IBS.READ(SCCGWA, LNRRELA, this, LNTRELA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_NOFND_RELA, LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_LNTSUBS() throws IOException,SQLException,Exception {
        LNTSUBS_RD = new DBParm();
        LNTSUBS_RD.TableName = "LNTSUBS";
        LNTSUBS_RD.upd = true;
        IBS.READ(SCCGWA, LNRSUBS, LNTSUBS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SUBS_NOTFND, LNCSRPO.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCSRPO.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_READ_LNTSUBS() throws IOException,SQLException,Exception {
        LNTSUBS_RD = new DBParm();
        LNTSUBS_RD.TableName = "LNTSUBS";
        IBS.READ(SCCGWA, LNRSUBS, LNTSUBS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SUBS_NOTFND, LNCSRPO.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCSRPO.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_REWRITE_LNTSUBS() throws IOException,SQLException,Exception {
        LNRSUBS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRSUBS.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTSUBS_RD = new DBParm();
        LNTSUBS_RD.TableName = "LNTSUBS";
        IBS.REWRITE(SCCGWA, LNRSUBS, LNTSUBS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SUBS_NOTFND, LNCSRPO.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCSRPO.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_READ_LNTCTPY1() throws IOException,SQLException,Exception {
        LNTCTPY_RD = new DBParm();
        LNTCTPY_RD.TableName = "LNTCTPY";
        LNTCTPY_RD.where = "CONTRACT_NO = :LNRCTPY.KEY.CONTRACT_NO "
            + "AND TR_TYP = :LNRCTPY.KEY.TR_TYP";
        LNTCTPY_RD.fst = true;
        LNTCTPY_RD.order = "TRAN_DATE DESC";
        IBS.READ(SCCGWA, LNRCTPY, this, LNTCTPY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_READ_LNTCTPY_ERR, LNCSRPO.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCSRPO.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_READ_LNTPACK() throws IOException,SQLException,Exception {
        LNTPACK_RD = new DBParm();
        LNTPACK_RD.TableName = "LNTPACK";
        IBS.READ(SCCGWA, LNRPACK, LNTPACK_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PACK_NOTFND, LNCSRPO.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCSRPO.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_READ_UPDATE_LNTPACK() throws IOException,SQLException,Exception {
        LNTPACK_RD = new DBParm();
        LNTPACK_RD.TableName = "LNTPACK";
        LNTPACK_RD.upd = true;
        IBS.READ(SCCGWA, LNRPACK, LNTPACK_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PACK_NOTFND, LNCSRPO.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCSRPO.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_REWRITE_LNTPACK() throws IOException,SQLException,Exception {
        LNRPACK.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRPACK.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTPACK_RD = new DBParm();
        LNTPACK_RD.TableName = "LNTPACK";
        IBS.REWRITE(SCCGWA, LNRPACK, LNTPACK_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PACK_REWRITE, LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC, true);
        CEP.TRC(SCCGWA, CICQACAC.RC.RC_CODE);
        if (CICQACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACAC.RC);
        }
    }
    public void T000_READ_UPDATE_LNTPLDT() throws IOException,SQLException,Exception {
        LNTPLDT_RD = new DBParm();
        LNTPLDT_RD.TableName = "LNTPLDT";
        LNTPLDT_RD.upd = true;
        IBS.READ(SCCGWA, LNRPLDT, LNTPLDT_RD);
        WS_READ_LNTPLDT_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_READ_LNTPLDT_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_READ_LNTPLDT_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTPLDT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_LNTPLDT() throws IOException,SQLException,Exception {
        LNRPLDT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRPLDT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTPLDT_RD = new DBParm();
        LNTPLDT_RD.TableName = "LNTPLDT";
        IBS.REWRITE(SCCGWA, LNRPLDT, LNTPLDT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PLDT_REWRITE, LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_LNTPLDT() throws IOException,SQLException,Exception {
        LNRPLDT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRPLDT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRPLDT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRPLDT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTPLDT_RD = new DBParm();
        LNTPLDT_RD.TableName = "LNTPLDT";
        LNTPLDT_RD.errhdl = true;
        IBS.WRITE(SCCGWA, LNRPLDT, LNTPLDT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PLDT_WRITE, LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_READ_LNTTRAN() throws IOException,SQLException,Exception {
        LNTTRAN_RD = new DBParm();
        LNTTRAN_RD.TableName = "LNTTRAN";
        LNTTRAN_RD.where = "REC_FLAG = 'B' "
            + "AND CONTRACT_NO = :LNRTRAN.KEY.CONTRACT_NO "
            + "AND TR_CODE = 4000";
        LNTTRAN_RD.fst = true;
        LNTTRAN_RD.order = "TS DESC";
        IBS.READ(SCCGWA, LNRTRAN, this, LNTTRAN_RD);
    }
    public void S000_CALL_BPZUABOX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-ADD-CBOX", BPCUABOX, true);
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
        if (AICUUPIA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_IBZQINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZQINF", IBCQINF);
        if (IBCQINF.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCQINF.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSRPC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRV-REPAY-CALC", LNCSRPC);
        if (LNCSRPC.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSRPC.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRFUND() throws IOException,SQLException,Exception {
        LNCRFUND.REC_PTR = LNRFUND;
        LNCRFUND.REC_LEN = 456;
        IBS.CALLCPN(SCCGWA, "LN-R-FUND-MAIN", LNCRFUND);
        if (LNCRFUND.RETURN_INFO != 'F') {
            IBS.CPY2CLS(SCCGWA, LNCRFUND.RC.RC_CODE+"", LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTICTL() throws IOException,SQLException,Exception {
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        IBS.READ(SCCGWA, LNRICTL, LNTICTL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_READ_LNTICTL_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_READ_LNTICTL_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTICTL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-INT-CAL", LNCICAL);
        if (LNCICAL.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCICAL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_LNTCTPY() throws IOException,SQLException,Exception {
        LNTCTPY_RD = new DBParm();
        LNTCTPY_RD.TableName = "LNTCTPY";
        LNTCTPY_RD.upd = true;
        IBS.READ(SCCGWA, LNRCTPY, LNTCTPY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_READ_LNTCTPY_ERR, LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_LNTCTPY() throws IOException,SQLException,Exception {
        LNRCTPY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRCTPY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTCTPY_RD = new DBParm();
        LNTCTPY_RD.TableName = "LNTCTPY";
        IBS.REWRITE(SCCGWA, LNRCTPY, LNTCTPY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_UPD_LNTCTPY_ERR, LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_GDCIQLDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-I-QLDR-PROC", GDCIQLDR);
        if (GDCIQLDR.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, GDCIQLDR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_GDZSBAAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SRV-GDZSBAAC", GDCSTRAC);
    }
    public void S000_CALL_GDZSTRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-S-TRAC-PROC", GDCSTRAC);
    }
    public void S000_CALL_GDZUMPLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-U-PLDR-RL", GDCUMPLD);
    }
    public void S000_CALL_GDZUMPDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-U-PLDR-DR", GDCUMPDR);
    }
    public void S000_CALL_GDZSDDDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SVR-GDZSDDDR", GDCSDDDR);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICQACRI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACRI.RC);
        }
    }
    public void S000_LINK_BPZSEX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-EX", BPCEX);
        if (BPCEX.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCEX.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_GDZSRLSR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SVR-GDZSRLSR", GDCSRLSR, true);
    }
    public void S000_CALL_SCCCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            LNCSRPO.RC.RC_APP = "SC";
            LNCSRPO.RC.RC_RTNCODE = SCCCLDT.RC;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B0000_READ_LNTICTL() throws IOException,SQLException,Exception {
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        LNTICTL_RD.where = "CONTRACT_NO = :LNRICTL.KEY.CONTRACT_NO";
        IBS.READ(SCCGWA, LNRICTL, this, LNTICTL_RD);
    }
    public void B0000_READ_LNTAPRD() throws IOException,SQLException,Exception {
        LNTAPRD_RD = new DBParm();
        LNTAPRD_RD.TableName = "LNTAPRD";
        LNTAPRD_RD.where = "CONTRACT_NO = :LNRAPRD.KEY.CONTRACT_NO";
        IBS.READ(SCCGWA, LNRAPRD, this, LNTAPRD_RD);
    }
    public void T000_STARTBR_RCVD_PROC() throws IOException,SQLException,Exception {
        LNTRCVD_BR.rp = new DBParm();
        LNTRCVD_BR.rp.TableName = "LNTRCVD";
        LNTRCVD_BR.rp.eqWhere = "CONTRACT_NO";
        LNTRCVD_BR.rp.where = "SUB_CTA_NO = :LNRRCVD.KEY.SUB_CTA_NO "
            + "AND DUE_DT = :LNRRCVD.DUE_DT "
            + "AND REPY_STS < > '2'";
        LNTRCVD_BR.rp.order = "AMT_TYP";
        IBS.STARTBR(SCCGWA, LNRRCVD, this, LNTRCVD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_RCVD_NOTFND, LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_RCVD_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRRCVD, this, LNTRCVD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            LNCRRCVD.RETURN_INFO = 'E';
        }
    }
    public void T000_ENDBR_RCVD_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTRCVD_BR);
    }
    public void S000_CALL_SCZTPCL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-LINK-EXTSERV", SCCTPCL);
    }
    public void S000_CALL_LNZCNEX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CONT-EXHIBIT", LNCCNEX);
        if (LNCCNEX.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCNEX.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTAGRE() throws IOException,SQLException,Exception {
        LNTAGRE_RD = new DBParm();
        LNTAGRE_RD.TableName = "LNTAGRE";
        LNTAGRE_RD.where = "CONTRACT_NO = :LNRAGRE.KEY.CONTRACT_NO";
        IBS.READ(SCCGWA, LNRAGRE, this, LNTAGRE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_AGRE_NOTFND, LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTAPRD() throws IOException,SQLException,Exception {
        LNTAPRD_RD = new DBParm();
        LNTAPRD_RD.TableName = "LNTAPRD";
        LNTAPRD_RD.where = "CONTRACT_NO = :LNRAPRD.KEY.CONTRACT_NO";
        IBS.READ(SCCGWA, LNRAPRD, this, LNTAPRD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_APRD_NOTFND, LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSSCHE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-INQ-SSCHE", LNCSSCHE);
    }
    public void S000_CALL_BPZFFTXI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-TX-INFO", BPCFFTXI);
        if (BPCFFTXI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFFTXI.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFFCON() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-CON-CHG-INFO", BPCFFCON);
        if (BPCFFCON.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFFCON.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFSSVR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-U-MAINTAIN-FSVR", BPCOFSVR);
        if (BPCOFSVR.FOUND_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FEE_CODE, LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICLNQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUNC-LN-INFO", LNCCLNQ);
        if (LNCCLNQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCLNQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZPQITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-ITM", AICPQITM);
        if (AICPQITM.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQITM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB);
        if (AICPQMIB.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQMIB.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST, true);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        if (CICACCU.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZIGEAI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUNC-AI-AC", LNCIGEAI);
        if (LNCIGEAI.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCIGEAI.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTCONT() throws IOException,SQLException,Exception {
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        LNTCONT_RD.where = "CONTRACT_NO = :LNRCONT.KEY.CONTRACT_NO";
        IBS.READ(SCCGWA, LNRCONT, this, LNTCONT_RD);
    }
    public void S000_CALL_LNZAPRDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-APRD-MAINT", LNCAPRDM);
        if (LNCAPRDM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCAPRDM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_ERR_INFO);
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
