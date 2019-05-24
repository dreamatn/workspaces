package com.hisun.LN;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.AI.AICPQIA;
import com.hisun.AI.AICUUPIA;
import com.hisun.BP.BPCEX;
import com.hisun.BP.BPCFFCON;
import com.hisun.BP.BPCFFTXI;
import com.hisun.BP.BPCOFSVR;
import com.hisun.BP.BPCPFHIS;
import com.hisun.BP.BPCUABOX;
import com.hisun.CI.CICACCU;
import com.hisun.CI.CICCUST;
import com.hisun.CI.CICQACRI;
import com.hisun.DD.DDCUCRAC;
import com.hisun.DD.DDCUDRAC;
import com.hisun.GD.GDCSDDDR;
import com.hisun.GD.GDCUMPLD;
import com.hisun.IB.IBCPOSTA;
import com.hisun.SC.SCCBATH;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGAPV;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCTPCL;

public class LNZSRPOR {
    boolean pgmRtn = false;
    String K_DD_AC = "01";
    String K_INTERNAL = "02";
    String K_IB_AC = "03";
    String K_DC_AC = "05";
    String K_EB_AC = "06";
    char LNZSRPOR_FILLER1 = ' ';
    String WS_TRUS_W_AC_TYP = " ";
    String WS_TRUS_W_AC = " ";
    String WS_CLDL_RDI_AC = " ";
    String WS_RELEASE_AC = " ";
    int WS_IDX = 0;
    int WS_X = 0;
    String WS_UDRAC_ACAC = " ";
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    String WS_ERR_INFO = " ";
    String WS_CTA_NO = " ";
    double WS_LEFT_AMT = 0;
    String WS_FEE_CODE_HRG = " ";
    String WS_FATHER_CONTRACT = " ";
    double WS_EXCHANGE_RATE = 0;
    String WS_CONTRACT_TYPE = " ";
    String WS_CTL_STSW = " ";
    char WS_CI_ATTR = ' ';
    LNZSRPOR_WS_TRF_NARRATIVE WS_TRF_NARRATIVE = new LNZSRPOR_WS_TRF_NARRATIVE();
    String WS_EVENT_CODE = " ";
    int WS_LOCAL_SEQ_PRIN = 0;
    int WS_LOCAL_SEQ_INT = 0;
    double WS_WOF_O_INT_ALOW = 0;
    LNZSRPOR_WS_SETL_INFO WS_SETL_INFO = new LNZSRPOR_WS_SETL_INFO();
    double WS_TXN_B_AMT = 0;
    double WS_TXN_T_AMT = 0;
    String WS_TX_AC_TYP = " ";
    String WS_CTPY_AC = " ";
    String WS_TX_AC = " ";
    double WS_TX_AMT = 0;
    double WS_TR_TOT_AMT = 0;
    String WS_SUSP_AC = "                                ";
    double WS_PAY_I_AMT = 0;
    double WS_PAY_O_AMT = 0;
    double WS_PAY_L_AMT = 0;
    LNZSRPOR_WS_CHECK_INFO WS_CHECK_INFO = new LNZSRPOR_WS_CHECK_INFO();
    String WS_P_TMP_AC = " ";
    String WS_I_TMP_AC = " ";
    double WS_PAY_R_I_AMT = 0;
    double WS_PAY_R_O_AMT = 0;
    double WS_PAY_R_L_AMT = 0;
    double WS_B_I_AMT = 0;
    double WS_B_O_AMT = 0;
    double WS_B_L_AMT = 0;
    double WS_R_I_AMT = 0;
    double WS_R_O_AMT = 0;
    double WS_R_L_AMT = 0;
    char WS_CTPY_TR_TYP = ' ';
    double WS_I_LEFT_AMT = 0;
    double WS_O_LEFT_AMT = 0;
    double WS_L_LEFT_AMT = 0;
    char WS_TRAN_REC_FLG = ' ';
    LNZSRPOR_WS_TR_TEXT WS_TR_TEXT = new LNZSRPOR_WS_TR_TEXT();
    int WS_TR_LENGTH = 0;
    int WS_CLR_DATA_LEN = 0;
    LNZSRPOR_WS_CLR_DATA WS_CLR_DATA = new LNZSRPOR_WS_CLR_DATA();
    int WS_J = 0;
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    char WS_EOF_FLG = ' ';
    char WS_SYNL_FLAG = ' ';
    char WS_REC_FOUND_FLG = ' ';
    char WS_LIMIT_FLAG = ' ';
    char WS_READ_LNTICTL_FLG = ' ';
    char WS_READ_LNTPLDT_FLG = ' ';
    char WS_GDA_KO_FLG = ' ';
    char WS_PI_PROC_FLG = ' ';
    char WS_UPD_ICTL_FLG = ' ';
    LNRSETL LNRSETL = new LNRSETL();
    LNCRSETL LNCRSETL = new LNCRSETL();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    LNCURPNR LNCURPNR = new LNCURPNR();
    LNCOD10 LNCOD10 = new LNCOD10();
    LNCICRCM LNCICRCM = new LNCICRCM();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    IBCPOSTA IBCPOSTA = new IBCPOSTA();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    LNCPPMQ LNCPPMQ = new LNCPPMQ();
    BPCUABOX BPCUABOX = new BPCUABOX();
    AICUUPIA AICUUPIA = new AICUUPIA();
    LNRFUND LNRFUND = new LNRFUND();
    LNCRFUND LNCRFUND = new LNCRFUND();
    LNRCTPY LNRCTPY = new LNRCTPY();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    GDCUMPLD GDCUMPLD = new GDCUMPLD();
    BPCEX BPCEX = new BPCEX();
    SCCTPCL SCCTPCL = new SCCTPCL();
    LNRAGRE LNRAGRE = new LNRAGRE();
    LNRPLDT LNRPLDT = new LNRPLDT();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPCFFCON BPCFFCON = new BPCFFCON();
    LNRTRAN LNRTRAN = new LNRTRAN();
    LNCTRANM LNCTRANM = new LNCTRANM();
    BPCOFSVR BPCOFSVR = new BPCOFSVR();
    AICPQIA AICPQIA = new AICPQIA();
    GDCUTRCL GDCUTRCL = new GDCUTRCL();
    LNCCLNQ LNCCLNQ = new LNCCLNQ();
    LNRRELA LNRRELA = new LNRRELA();
    LNRSUBS LNRSUBS = new LNRSUBS();
    CICACCU CICACCU = new CICACCU();
    LNCIGEAI LNCIGEAI = new LNCIGEAI();
    LNRRELI LNRRELI = new LNRRELI();
    LNCSSUNM LNCSSUNM = new LNCSSUNM();
    LNRPACK LNRPACK = new LNRPACK();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCSCLRR LNCSCLRR = new LNCSCLRR();
    CICQACRI CICQACRI = new CICQACRI();
    GDCSDDDR GDCSDDDR = new GDCSDDDR();
    CICCUST CICCUST = new CICCUST();
    LNCIPAMT LNCIPAMT = new LNCIPAMT();
    LNRBALZ LNRBALZ = new LNRBALZ();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    SCCBATH SCCBATH;
    LNCSRPOR LNCSRPOR;
    public void MP(SCCGWA SCCGWA, LNCSRPOR LNCSRPOR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSRPOR = LNCSRPOR;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSRPOR return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
            SCCBATH = (SCCBATH) SCCGWA.COMM_AREA.BAT_AREA_PTR;
        }
        LNCSRPOR.RC.RC_APP = "LN";
        LNCSRPOR.RC.RC_RTNCODE = 0;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_INPUT_VAILIDATION();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
    }
    public void B000_INPUT_VAILIDATION() throws IOException,SQLException,Exception {
        B010_BASIC_CHECK();
        if (pgmRtn) return;
        B020_GET_CTA_AND_PARM_INFO();
        if (pgmRtn) return;
        if (LNCAPRDM.REC_DATA.GDA_APRE.trim().length() > 0) {
            B080_DELETE_PLDR_PROC();
            if (pgmRtn) return;
        }
        B030_LOGIC_CHECK();
        if (pgmRtn) return;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if (WS_CTL_STSW.substring(19 - 1, 19 + 1 - 1).equalsIgnoreCase("1")) {
            B095_TRUS_HRGTAX_PRC();
            if (pgmRtn) return;
        }
        B051_AMT_DEBIT_PROC();
        if (pgmRtn) return;
        if (LNCCLNQ.DATA.PD_PROJ_NO != 0) {
            B052_FUND_LOAN_PROC();
            if (pgmRtn) return;
        }
        B060_SOLD_OUT_LOAN_PROC();
        if (pgmRtn) return;
        B070_CUST_REPAY_REV_PROC();
        if (pgmRtn) return;
        B095_UP_ZHW();
        if (pgmRtn) return;
        B110_BP_HISTORY_GEN();
        if (pgmRtn) return;
        if (WS_CLR_DATA.WS_CLR_BAL_24 > 0 
            || WS_CLR_DATA.WS_CLR_BAL_48 > 0 
            || WS_CLR_DATA.WS_CLR_BAL_58 > 0) {
            R000_READ_LNTBALZ_UPD();
            if (pgmRtn) return;
            LNRBALZ.LOAN_BALL24 += WS_CLR_DATA.WS_CLR_BAL_24;
            LNRBALZ.LOAN_BALL48 += WS_CLR_DATA.WS_CLR_BAL_48;
            LNRBALZ.LOAN_BALL58 += WS_CLR_DATA.WS_CLR_BAL_58;
            R000_REWRITE_LNTBALZ();
            if (pgmRtn) return;
        }
    }
    public void R000_READ_LNTBALZ_UPD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRBALZ);
        LNRBALZ.KEY.CONTRACT_NO = LNCSRPOR.COMM_DATA.CTA_NO;
        LNRBALZ.KEY.SUB_CTA_NO = 0;
        LNTBALZ_RD = new DBParm();
        LNTBALZ_RD.TableName = "LNTBALZ";
        LNTBALZ_RD.upd = true;
        IBS.READ(SCCGWA, LNRBALZ, LNTBALZ_RD);
    }
    public void R000_REWRITE_LNTBALZ() throws IOException,SQLException,Exception {
        LNRBALZ.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRBALZ.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTBALZ_RD = new DBParm();
        LNTBALZ_RD.TableName = "LNTBALZ";
        IBS.REWRITE(SCCGWA, LNRBALZ, LNTBALZ_RD);
    }
    public void B095_TRUS_HRGTAX_PRC() throws IOException,SQLException,Exception {
        B095_GET_TRUS_SETL_INF();
        if (pgmRtn) return;
        WS_RELEASE_AC = WS_TRUS_W_AC;
        if (!LNCSRPOR.COMM_DATA.ACAMT[WS_X-1].STL_MTH2.equalsIgnoreCase("08) !LNCSRPOR.COMM_DATA.ACAMT[WS_X-1].STL_MTH2.equalsIgnoreCase(")) {
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
        WS_TX_AMT = LNCSRPOR.COMM_DATA.TOT_AMT;
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
        LNRSETL.KEY.CONTRACT_NO = LNCSRPOR.COMM_DATA.CTA_NO;
        LNRSETL.KEY.CI_TYPE = 'C';
        LNRSETL.KEY.CCY = LNCSRPOR.COMM_DATA.CCY;
        LNRSETL.KEY.SETTLE_TYPE = '4';
        S000_CALL_LNZRSETL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNRSETL.AC_TYP);
        CEP.TRC(SCCGWA, LNRSETL.AC);
        WS_TRUS_W_AC_TYP = LNRSETL.AC_TYP;
        WS_TRUS_W_AC = LNRSETL.AC;
    }
    public void B010_BASIC_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRAGRE);
        LNRAGRE.KEY.CONTRACT_NO = LNCSRPOR.COMM_DATA.CTA_NO;
        T000_READ_LNTAGRE();
        if (pgmRtn) return;
        if (LNCSRPOR.COMM_DATA.CTA_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCSRPOR.RC);
            WS_ERR_INFO = "SRPOR-CTA-NO";
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCSRPOR.COMM_DATA.TR_VAL_DTE == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCSRPOR.RC);
            WS_ERR_INFO = "SRPOR-TR-VAL-DTE";
            Z_RET();
            if (pgmRtn) return;
        }
        WS_TRAN_REC_FLG = 'B';
        B067_READ_TRAN_C();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '3';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCSRPOR.COMM_DATA.CTA_NO;
        LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCTRANM.REC_DATA.LOAN_STSW == null) LNCTRANM.REC_DATA.LOAN_STSW = "";
        JIBS_tmp_int = LNCTRANM.REC_DATA.LOAN_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCTRANM.REC_DATA.LOAN_STSW += " ";
        if (!LNCICTLM.REC_DATA.CTL_STSW.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase(LNCTRANM.REC_DATA.LOAN_STSW.substring(11 - 1, 11 + 1 - 1))) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_REVERSE_FAILED, LNCSRPOR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_CTA_AND_PARM_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCTRANM);
        LNCTRANM.FUNC = '3';
        LNCTRANM.REC_DATA.KEY.CONTRACT_NO = LNCSRPOR.COMM_DATA.CTA_NO;
        LNCTRANM.REC_DATA.KEY.SUB_CTA_NO = 0;
        LNCTRANM.REC_DATA.KEY.REC_FLAG = 'B';
        LNCTRANM.REC_DATA.KEY.TR_DATE = LNCSRPOR.TXN_INFO.TXN_DT;
        LNCTRANM.REC_DATA.KEY.TR_JRN_NO = LNCSRPOR.TXN_INFO.JRNNO;
        LNCTRANM.REC_DATA.KEY.TXN_TERM = 0;
        LNCTRANM.REC_DATA.KEY.TXN_TYP = 'T';
        LNCTRANM.REC_DATA.KEY.TRAN_FLG = 'C';
        S000_CALL_LNZTRANM();
        if (pgmRtn) return;
        if (LNCTRANM.RC.RC_RTNCODE == 1519) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_TRAN_NOTFND, LNCSRPOR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        LNCSRPOR.COMM_DATA.TOT_P_AMT = LNCTRANM.REC_DATA.P_AMT;
        LNCSRPOR.COMM_DATA.TOT_I_AMT = LNCTRANM.REC_DATA.I_AMT;
        LNCSRPOR.COMM_DATA.TOT_O_AMT = LNCTRANM.REC_DATA.O_AMT;
        LNCSRPOR.COMM_DATA.TOT_L_AMT = LNCTRANM.REC_DATA.L_AMT;
        LNCSRPOR.COMM_DATA.RDI_AMT = LNCTRANM.REC_DATA.I_ADJ_AMT;
        LNCSRPOR.COMM_DATA.HRG_AMT = LNCTRANM.REC_DATA.F_AMT;
        LNCSRPOR.COMM_DATA.ACAMT[1-1].AC_FLG2 = LNCTRANM.REC_DATA.AC_FLG1;
        LNCSRPOR.COMM_DATA.ACAMT[1-1].STL_MTH2 = LNCTRANM.REC_DATA.AC_TYP1;
        LNCSRPOR.COMM_DATA.ACAMT[1-1].REC_AC2 = LNCTRANM.REC_DATA.PAY_AC1;
        LNCSRPOR.COMM_DATA.ACAMT[1-1].PAY_AMT2 = LNCTRANM.REC_DATA.PAY_AMT1;
        LNCSRPOR.COMM_DATA.ACAMT[2-1].AC_FLG2 = LNCTRANM.REC_DATA.AC_FLG2;
        LNCSRPOR.COMM_DATA.ACAMT[2-1].STL_MTH2 = LNCTRANM.REC_DATA.AC_TYP2;
        LNCSRPOR.COMM_DATA.ACAMT[2-1].REC_AC2 = LNCTRANM.REC_DATA.PAY_AC2;
        LNCSRPOR.COMM_DATA.ACAMT[2-1].PAY_AMT2 = LNCTRANM.REC_DATA.PAY_AMT2;
        LNCSRPOR.COMM_DATA.ACAMT[3-1].AC_FLG2 = LNCTRANM.REC_DATA.AC_FLG3;
        LNCSRPOR.COMM_DATA.ACAMT[3-1].STL_MTH2 = LNCTRANM.REC_DATA.AC_TYP3;
        LNCSRPOR.COMM_DATA.ACAMT[3-1].REC_AC2 = LNCTRANM.REC_DATA.PAY_AC3;
        LNCSRPOR.COMM_DATA.ACAMT[3-1].PAY_AMT2 = LNCTRANM.REC_DATA.PAY_AMT3;
        LNCSRPOR.COMM_DATA.ACAMT[4-1].AC_FLG2 = LNCTRANM.REC_DATA.AC_FLG4;
        LNCSRPOR.COMM_DATA.ACAMT[4-1].STL_MTH2 = LNCTRANM.REC_DATA.AC_TYP4;
        LNCSRPOR.COMM_DATA.ACAMT[4-1].REC_AC2 = LNCTRANM.REC_DATA.PAY_AC4;
        LNCSRPOR.COMM_DATA.ACAMT[4-1].PAY_AMT2 = LNCTRANM.REC_DATA.PAY_AMT4;
        LNCSRPOR.COMM_DATA.ACAMT[5-1].AC_FLG2 = LNCTRANM.REC_DATA.AC_FLG5;
        LNCSRPOR.COMM_DATA.ACAMT[5-1].STL_MTH2 = LNCTRANM.REC_DATA.AC_TYP5;
        LNCSRPOR.COMM_DATA.ACAMT[5-1].REC_AC2 = LNCTRANM.REC_DATA.PAY_AC5;
        LNCSRPOR.COMM_DATA.ACAMT[5-1].PAY_AMT2 = LNCTRANM.REC_DATA.PAY_AMT5;
        IBS.init(SCCGWA, LNCSCLRR);
        WS_CLR_DATA_LEN = 324;
        if (LNCTRANM.REC_DATA.TR_DATA == null) LNCTRANM.REC_DATA.TR_DATA = "";
        JIBS_tmp_int = LNCTRANM.REC_DATA.TR_DATA.length();
        for (int i=0;i<1024-JIBS_tmp_int;i++) LNCTRANM.REC_DATA.TR_DATA += " ";
        IBS.CPY2CLS(SCCGWA, LNCTRANM.REC_DATA.TR_DATA.substring(0, WS_CLR_DATA_LEN), WS_CLR_DATA);
        LNCSCLRR.COMM_DATA.CTA_NO = WS_CLR_DATA.WS_CLR_CTA_NO;
        LNCSCLRR.COMM_DATA.TR_VAL_DT = WS_CLR_DATA.WS_CLR_TR_VAL_DT;
        LNCSCLRR.COMM_DATA.PAY_DATA[1-1].REC_AC2 = WS_CLR_DATA.WS_CLR_REC_AC2;
        LNCSCLRR.COMM_DATA.NOR_P = WS_CLR_DATA.WS_CLR_NOR_P;
        LNCSCLRR.COMM_DATA.OVR_P = WS_CLR_DATA.WS_CLR_OVR_P;
        LNCSCLRR.COMM_DATA.NOR_I = WS_CLR_DATA.WS_CLR_NOR_I;
        LNCSCLRR.COMM_DATA.OVR_I = WS_CLR_DATA.WS_CLR_OVR_I;
        LNCSCLRR.COMM_DATA.NOR_O = WS_CLR_DATA.WS_CLR_NOR_O;
        LNCSCLRR.COMM_DATA.NOR_L = WS_CLR_DATA.WS_CLR_NOR_L;
        LNCSCLRR.COMM_DATA.PC_CLR_FLG = WS_CLR_DATA.WS_CLR_PC_CLR_FLG;
        LNCSCLRR.COMM_DATA.P_CLR_FLG = WS_CLR_DATA.WS_CLR_P_CLR_FLG;
        LNCSCLRR.COMM_DATA.I_CLR_FLG = WS_CLR_DATA.WS_CLR_I_CLR_FLG;
