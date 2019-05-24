package com.hisun.LN;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.AI.AICPQIA;
import com.hisun.AI.AICPQITM;
import com.hisun.AI.AICPQMIB;
import com.hisun.AI.AICUUPIA;
import com.hisun.BP.BPCFFCON;
import com.hisun.BP.BPCFFTXI;
import com.hisun.BP.BPCOFSVR;
import com.hisun.BP.BPCPFHIS;
import com.hisun.BP.BPCPOEWA;
import com.hisun.BP.BPCPQBNK_DATA_INFO;
import com.hisun.BP.BPCUABOX;
import com.hisun.CI.CICACCU;
import com.hisun.CI.CICQACAC;
import com.hisun.CI.CICQACRI;
import com.hisun.DC.DCCPACTY;
import com.hisun.DD.DDCSQBAL;
import com.hisun.DD.DDCUCRAC;
import com.hisun.DD.DDCUDRAC;
import com.hisun.GD.GDCIQLDR;
import com.hisun.GD.GDCUMPDR;
import com.hisun.GD.GDCUMPLD;
import com.hisun.IB.IBCPOSTA;
import com.hisun.SC.SCCBATH;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGAPV;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCTPCL;

public class LNZSPRRP {
    boolean pgmRtn = false;
    String K_DD_AC = "01";
    String K_IA_AC = "02";
    String K_IB_AC = "03";
    String K_DC_AC = "05";
    char K_CKPD_INQ = '0';
    String WS_ERR_MSG = " ";
    String WS_MSG_INFO = "                                                                      ";
    String WS_UDRAC_ACAC = " ";
    LNZSPRRP_WS_INFO WS_INFO = new LNZSPRRP_WS_INFO();
    LNZSPRRP_WS_SETL_AC_INF WS_SETL_AC_INF = new LNZSPRRP_WS_SETL_AC_INF();
    LNZSPRRP_WS_CHECK_INFO WS_CHECK_INFO = new LNZSPRRP_WS_CHECK_INFO();
    double WS_TOT_AMT = 0;
    short WS_H = 0;
    int WS_X = 0;
    int WS_AC_NO = 0;
    LNZSPRRP_WS_TX_INFO WS_TX_INFO = new LNZSPRRP_WS_TX_INFO();
    double WS_TX_B_AMT = 0;
    double WS_TX_S_AMT = 0;
    double WS_LEFT_TOT_AMT = 0;
    double WS_LEFT_AMT = 0;
    double WS_DIFF_I_AMT = 0;
    double WS_DIFF_O_AMT = 0;
    double WS_DIFF_L_AMT = 0;
    double WS_PAY_I_AMT = 0;
    double WS_PAY_O_AMT = 0;
    double WS_PAY_L_AMT = 0;
    String WS_FEE_CODE_PC = " ";
    String WS_FEE_CODE_HRG = " ";
    int WS_K = 0;
    int WS_G = 0;
    short WS_SEQ = 0;
    String WS_AC_TYP = " ";
    String WS_AC = " ";
    String WS_AC_CCY = " ";
    double WS_AC_BAL = 0;
    double WS_REC_PAY_AMT = 0;
    double WS_AC_WCCY_AMT = 0;
    double WS_PAY_AMT = 0;
    double WS_KOU_KUAN_AMT = 0;
    double WS_KOU_GDA_AMT = 0;
    double WS_KOU_GDA_TOT_AMT = 0;
    double WS_EX_PC_AMT = 0;
    double WS_TR_TOT_AMT = 0;
    double WS_FEE_PC_AMT = 0;
    String WS_CCY_CHECK = " ";
    double WS_PQMIB_CBAL = 0;
    String WS_SUSP_AC = " ";
    LNZSPRRP_WS_IA_AC WS_IA_AC = new LNZSPRRP_WS_IA_AC();
    int WS_FEE_AC_BR = 0;
    double WS_BAL = 0;
    String WS_RELEASE_AC = " ";
    String WS_SYLOAN_AC_TYP = " ";
    String WS_SYLOAN_AC = " ";
    LNZSPRRP_WS_LOAN_CONT_AREA WS_LOAN_CONT_AREA = new LNZSPRRP_WS_LOAN_CONT_AREA();
    LNZSPRRP_WS_BBR_INFO[] WS_BBR_INFO = new LNZSPRRP_WS_BBR_INFO[20];
    int WS_IDX = 0;
    char WS_PI_PROC_FLG = ' ';
    double WS_O_I_AMT = 0;
    double WS_PAY_R_I_AMT = 0;
    double WS_I_LEFT_AMT = 0;
    char WS_REC_FLG = ' ';
    char WS_CTPY_TR_TYP = ' ';
    int WS_N = 0;
    int WS_M = 0;
    double WS_I_AMT1 = 0;
    double WS_I_AMT2 = 0;
    double WS_O_AMT1 = 0;
    double WS_O_AMT2 = 0;
    double WS_CTPY_I_AMT = 0;
    double WS_CTPY_O_AMT = 0;
    char WS_STS = ' ';
    char WS_INT_MODE_FLAG = ' ';
    char WS_CONT_FLAG = ' ';
    char WS_ICTL_FLAG = ' ';
    char WS_OVERDUE_FLAG = ' ';
    char WS_READ_LNTPLDT_FLG = ' ';
    char WS_EXTN_FLAG = ' ';
    char WS_PPRP_FLAG = ' ';
    char WS_FEHIS_FLAG = ' ';
    char WS_AC_FLG = ' ';
    char WS_GROUPLOAN_FLG = ' ';
    char WS_RPSP_FLG = ' ';
    char WS_READ_LNTRELA_FLG = ' ';
    char WS_CTPY_FLG = ' ';
    char WS_UPD_ICTL_FLG = ' ';
    char WS_UPD_D44_FLG = ' ';
    char WS_UPD_D45_FLG = ' ';
    char WS_SPRRP_FLG = ' ';
    char WS_I_FLG = ' ';
    char WS_O_FLG = ' ';
    LNCQ65 LNCQ65 = new LNCQ65();
    LNRICTL LNRICTL = new LNRICTL();
    LNRSETL LNRSETL = new LNRSETL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    IBCPOSTA IBCPOSTA = new IBCPOSTA();
    LNCUPRP LNCUPRP = new LNCUPRP();
    LNCICRCM LNCICRCM = new LNCICRCM();
    LNCUCMMT LNCUCMMT = new LNCUCMMT();
    LNCCNEX LNCCNEX = new LNCCNEX();
    LNCRICTL LNCRICTL = new LNCRICTL();
    LNCRSETL LNCRSETL = new LNCRSETL();
    LNCSCKPD LNCSCKPD = new LNCSCKPD();
    BPCUABOX BPCUABOX = new BPCUABOX();
    AICUUPIA AICUUPIA = new AICUUPIA();
    LNRCONT LNRCONT = new LNRCONT();
    LNRDISC LNRDISC = new LNRDISC();
    LNCRDISC LNCRDISC = new LNCRDISC();
    LNRFUND LNRFUND = new LNRFUND();
    LNCRFUND LNCRFUND = new LNCRFUND();
    LNRCTPY LNRCTPY = new LNRCTPY();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    GDCUMPLD GDCUMPLD = new GDCUMPLD();
    GDCUMPDR GDCUMPDR = new GDCUMPDR();
    DCCPACTY DCCPACTY = new DCCPACTY();
    SCCTPCL SCCTPCL = new SCCTPCL();
    LNRPLDT LNRPLDT = new LNRPLDT();
    LNRAGRE LNRAGRE = new LNRAGRE();
    LNREXTN LNREXTN = new LNREXTN();
    LNRPPRP LNRPPRP = new LNRPPRP();
    BPCFFCON BPCFFCON = new BPCFFCON();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPCOFSVR BPCOFSVR = new BPCOFSVR();
    GDCIQLDR GDCIQLDR = new GDCIQLDR();
    AICPQMIB AICPQMIB = new AICPQMIB();
    AICPQIA AICPQIA = new AICPQIA();
    LNCSSTBL LNCSSTBL = new LNCSSTBL();
    LNCCLNQ LNCCLNQ = new LNCCLNQ();
    LNCSPREQ LNCSPREQ = new LNCSPREQ();
    AICPQITM AICPQITM = new AICPQITM();
    DDCSQBAL DDCSQBAL = new DDCSQBAL();
    LNRDSBL LNRDSBL = new LNRDSBL();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    CICACCU CICACCU = new CICACCU();
    LNCIGEAI LNCIGEAI = new LNCIGEAI();
    LNCBALLM LNCBALLM = new LNCBALLM();
    LNCPCFTA LNCPCFTA = new LNCPCFTA();
    LNRPYIF LNRPYIF = new LNRPYIF();
    LNRRELI LNRRELI = new LNRRELI();
    LNRSUBS LNRSUBS = new LNRSUBS();
    LNRRELA LNRRELA = new LNRRELA();
    LNRPACK LNRPACK = new LNRPACK();
    LNCTRANM LNCTRANM = new LNCTRANM();
    LNCICTLM LNCICTLM = new LNCICTLM();
    CICQACRI CICQACRI = new CICQACRI();
    LNRRCVD LNRRCVD = new LNRRCVD();
    CICQACAC CICQACAC = new CICQACAC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGAPV SCCGAPV;
    BPCPQBNK_DATA_INFO BPCRBANK;
    LNCSERRP LNCSPRRP;
    SCCBATH SCCBATH;
    public LNZSPRRP() {
        for (int i=0;i<20;i++) WS_BBR_INFO[i] = new LNZSPRRP_WS_BBR_INFO();
    }
    public void MP(SCCGWA SCCGWA, LNCSERRP LNCSPRRP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSPRRP = LNCSPRRP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSPRRP return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
            SCCBATH = (SCCBATH) SCCGWA.COMM_AREA.BAT_AREA_PTR;
            CEP.TRC(SCCGWA, SCCBATH.PGM_NAME);
        }
        IBS.init(SCCGWA, LNCSPRRP.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (!LNCSPRRP.FEE_INFO[1-1].FEE_CD.equalsIgnoreCase("0")) {
            for (WS_IDX = 1; LNCSPRRP.FEE_INFO[WS_IDX-1].FEE_CD.trim().length() != 0 
                && WS_IDX <= 5; WS_IDX += 1) {
                B090_SWICH_FEE_APP();
                if (pgmRtn) return;
            }
        }
        B090_SWICH_OTHER_APP();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.CONT_TYPE);
        if (!LNCCLNQ.DATA.CONT_TYPE.equalsIgnoreCase("CLDL")) {
            B091_SWICH_PROJ();
            if (pgmRtn) return;
        }
        B091_SWICH_CTPY();
        if (pgmRtn) return;
        B050_BORR_PROCESS();
        if (pgmRtn) return;
        R000_CHECK_PROG();
        if (pgmRtn) return;
        if (WS_UPD_D44_FLG == 'Y' 
            || WS_UPD_D45_FLG == 'Y') {
            IBS.init(SCCGWA, LNCICTLM);
            LNCICTLM.FUNC = '4';
            LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCSPRRP.CTA;
            LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
            S000_CALL_LNZICTLM();
            if (pgmRtn) return;
            if (WS_UPD_D44_FLG == 'Y') {
                if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 44 - 1) + "0" + LNCICTLM.REC_DATA.CTL_STSW.substring(44 + 1 - 1);
            }
            if (WS_UPD_D45_FLG == 'Y') {
                if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 45 - 1) + "0" + LNCICTLM.REC_DATA.CTL_STSW.substring(45 + 1 - 1);
            }
            LNCICTLM.FUNC = '2';
            S000_CALL_LNZICTLM();
            if (pgmRtn) return;
        }
        if (LNCAPRDM.REC_DATA.GDA_APRE.trim().length() > 0) {
            B200_DELETE_PLDR_PROC();
            if (pgmRtn) return;
        }
        B095_GET_OUTPUT();
        if (pgmRtn) return;
        B096_GENERATE_HIS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        R000_CHECK_BASIS_INFO();
        if (pgmRtn) return;
        R000_CHECK_CTA_STS();
        if (pgmRtn) return;
        R000_CHECK_PAY_AC();
        if (pgmRtn) return;
        R000_GET_REC_AMT();
        if (pgmRtn) return;
        R000_CHECK_CHNL();
        if (pgmRtn) return;
        R000_CHECK_DUE_AMT();
        if (pgmRtn) return;
        R000_CHECK_EXTN();
        if (pgmRtn) return;
        R000_CHECK_CMMT();
        if (pgmRtn) return;
        R000_CHECK_LN_CLDL();
        if (pgmRtn) return;
    }
    public void B050_BORR_PROCESS() throws IOException,SQLException,Exception {
        B050_05_BORR_EYPY();
        if (pgmRtn) return;
    }
    public void B050_05_BORR_EYPY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCUPRP);
        LNCUPRP.COMM_DATA.ACM_EVENT = "RLN";
        LNCUPRP.COMM_DATA.PRPY_INT_MTH = LNCSPRRP.INT_MODE;
        LNCUPRP.COMM_DATA.LN_AC = LNCSPRRP.CTA;
        LNCUPRP.COMM_DATA.TR_VAL_DATE = LNCSPRRP.TR_VAL_DTE;
        LNCUPRP.COMM_DATA.LAST_CUT_DATE = LNCCLNQ.DATA.INT_CUT_DT;
        LNCUPRP.COMM_DATA.BALANCE = LNCSPRRP.BALANCE;
        LNCUPRP.COMM_DATA.TOT_AMTS.PAY_TOT_AMT = LNCSPRRP.TOT_AMT;
        LNCUPRP.COMM_DATA.TOT_AMTS.PAY_P_AMT = LNCSPRRP.TOT_P_AMT;
        LNCUPRP.COMM_DATA.TOT_AMTS.PAY_I_AMT = LNCSPRRP.TOT_I_AMT;
        LNCUPRP.COMM_DATA.TOT_AMTS.PAY_F_AMT1 = LNCSPRRP.PC_AMT;
        LNCUPRP.COMM_DATA.RDI_FLG = LNCSPRRP.RDI_FLG;
        LNCUPRP.COMM_DATA.RDI_AMT = LNCSPRRP.RDI_AMT;
        LNCUPRP.COMM_DATA.HRG_AMT = LNCSPRRP.HRG_AMT;
        LNCUPRP.COMM_DATA.SYN_FLG = LNCSPRRP.SYN_FLG;
        LNCUPRP.COMM_DATA.TR_MMO = LNCSPRRP.TR_MMO;
        LNCUPRP.B_AMTS.B_I_AMT = WS_PAY_I_AMT;
        LNCUPRP.B_AMTS.B_O_AMT = WS_PAY_O_AMT;
        LNCUPRP.B_AMTS.B_L_AMT = WS_PAY_L_AMT;
        LNCUPRP.PROJ_INT = LNCSPRRP.PROJ_INT;
        CEP.TRC(SCCGWA, LNCSPRRP.S_TERM);
        LNCUPRP.S_TERM = LNCSPRRP.S_TERM;
        CEP.TRC(SCCGWA, LNCUPRP.S_TERM);
        LNCUPRP.N_RATE_TYP = LNCSPRRP.N_RATE_TYP;
        LNCUPRP.N_RATE = LNCSPRRP.N_RATE;
        LNCUPRP.O_RATE_TYP = LNCSPRRP.O_RATE_TYP;
        LNCUPRP.O_RATE = LNCSPRRP.O_RATE;
        LNCUPRP.L_RATE_FLG = LNCSPRRP.L_RATE_FLG;
        LNCUPRP.L_RATE_TYP = LNCSPRRP.L_RATE_TYP;
        LNCUPRP.L_RATE = LNCSPRRP.L_RATE;
        LNCUPRP.METH = LNCSPRRP.METH;
        LNCUPRP.RPSP_NO = LNCSPRRP.RPSP_NO;
        LNCUPRP.RAT_MTH = LNCSPRRP.RAT_MTH;
        LNCUPRP.INT_F_UN = LNCSPRRP.INT_F_UN;
        LNCUPRP.INT_F_PD = LNCSPRRP.INT_F_PD;
        LNCUPRP.FLT_IPER = LNCSPRRP.FLT_IPER;
        LNCUPRP.FLT_ADJF = LNCSPRRP.FLT_ADJF;
        LNCUPRP.FT_FLG = LNCSPRRP.FT_FLG;
        LNCUPRP.FT_DAY = LNCSPRRP.FT_DAY;
        LNCUPRP.FT_F_DAY = LNCSPRRP.FT_F_DAY;
        LNCUPRP.FLT_MTH = LNCSPRRP.FLT_MTH;
        LNCUPRP.RATE_TYP = LNCSPRRP.RATE_TYP;
        LNCUPRP.RATE_PD = LNCSPRRP.RATE_PD;
        LNCUPRP.RATE_VAR = LNCSPRRP.RATE_VAR;
        LNCUPRP.RATE_PCT = LNCSPRRP.RATE_PCT;
        LNCUPRP.RATE_INT = LNCSPRRP.RATE_INT;
        LNCUPRP.INT_RATE = LNCSPRRP.INT_RATE;
        LNCUPRP.PEN_RRAT = LNCSPRRP.PEN_RRAT;
        LNCUPRP.PEN_TYP = LNCSPRRP.PEN_TYP;
        LNCUPRP.PEN_RATT = LNCSPRRP.PEN_RATT;
        LNCUPRP.PEN_RATC = LNCSPRRP.PEN_RATC;
        LNCUPRP.PEN_SPR = LNCSPRRP.PEN_SPR;
        LNCUPRP.PEN_PCT = LNCSPRRP.PEN_PCT;
        LNCUPRP.PEN_IRAT = LNCSPRRP.PEN_IRAT;
        LNCUPRP.INT_MTH = LNCSPRRP.INT_MTH;
        LNCUPRP.CPND_USE = LNCSPRRP.CPND_USE;
        LNCUPRP.CPND_RTY = LNCSPRRP.CPND_RTY;
        LNCUPRP.CPND_TYP = LNCSPRRP.CPND_TYP;
        LNCUPRP.CPNDRATT = LNCSPRRP.CPNDRATT;
        LNCUPRP.CPNDRATC = LNCSPRRP.CPNDRATC;
        LNCUPRP.CPND_SPR = LNCSPRRP.CPND_SPR;
        LNCUPRP.CPND_PCT = LNCSPRRP.CPND_PCT;
        LNCUPRP.CPNDRATE = LNCSPRRP.CPNDRATE;
        LNCUPRP.ABUS_RAT = LNCSPRRP.ABUS_RAT;
        LNCUPRP.ABUS_TYP = LNCSPRRP.ABUS_TYP;
        LNCUPRP.ABUSRATT = LNCSPRRP.ABUSRATT;
        LNCUPRP.ABUSRATC = LNCSPRRP.ABUSRATC;
        LNCUPRP.ABUSSPR = LNCSPRRP.ABUSSPR;
        LNCUPRP.ABUSPCT = LNCSPRRP.ABUSPCT;
        LNCUPRP.ABUSIRAT = LNCSPRRP.ABUSIRAT;
        LNCUPRP.ACAMT[1-1].DEP_AC2 = LNCSPRRP.ACAMT[1-1].DEP_AC2;
        for (WS_X = 1; WS_X <= WS_AC_NO 
            && (LNCSPRRP.ACAMT[WS_X-1].STL_MTH2.trim().length() != 0 
            || LNCSPRRP.ACAMT[WS_X-1].DEP_AC2.trim().length() != 0); WS_X += 1) {
            LNCUPRP.ACAMT[WS_X-1].STL_MTH2 = LNCSPRRP.ACAMT[WS_X-1].STL_MTH2;
            LNCUPRP.ACAMT[WS_X-1].CHQ_NO2 = LNCSPRRP.ACAMT[WS_X-1].CHQ_NO2;
            LNCUPRP.ACAMT[WS_X-1].DEP_AC2 = LNCSPRRP.ACAMT[WS_X-1].DEP_AC2;
            LNCUPRP.ACAMT[WS_X-1].AC_FLG2 = LNCSPRRP.ACAMT[WS_X-1].AC_FLG2;
            LNCUPRP.ACAMT[WS_X-1].PAY_AMT2 = LNCSPRRP.ACAMT[WS_X-1].PAY_AMT2;
            LNCUPRP.ACAMT[WS_X-1].AMT_FRM2 = LNCSPRRP.ACAMT[WS_X-1].AMT_FRM2;
            LNCUPRP.ACAMT[WS_X-1].REC_CCY2 = LNCSPRRP.ACAMT[WS_X-1].REC_CCY2;
        }
        S000_CALL_LNZUPRP();
        if (pgmRtn) return;
    }
    public void B050_07_RELE_TRANCH() throws IOException,SQLException,Exception {
        R000_GET_COMMITMENT_ATTR();
        if (pgmRtn) return;
        if (WS_INFO.WS_CTL_STSW == null) WS_INFO.WS_CTL_STSW = "";
        JIBS_tmp_int = WS_INFO.WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_INFO.WS_CTL_STSW += " ";
        if (LNCSPRRP.TOT_P_AMT > 0 
            && LNCUCMMT.REV_CMMT == 'Y' 
            && !WS_INFO.WS_CTL_STSW.substring(71 - 1, 71 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.init(SCCGWA, LNCICRCM);
            LNCICRCM.FUNC = 'P';
            LNCICRCM.DATA.AMT = LNCSPRRP.TOT_P_AMT;
            if (LNCSPRRP.TOT_P_AMT == LNCSPRRP.BALANCE) {
                LNCICRCM.DATA.FLG = 'C';
            }
            LNCICRCM.DATA.TRAN_TYPE = 'P';
            LNCICRCM.DATA.C_T_NO = WS_INFO.WS_FATHER_CONTRACT;
            LNCICRCM.DATA.CONT_NO = LNCSPRRP.CTA;
            LNCICRCM.DATA.CI_NO = LNCSPRRP.CI_NO;
            LNCICRCM.DATA.CI_ATTR = LNCCLNQ.DATA.CI_ATTR;
            LNCICRCM.DATA.PROD_TYPE = LNCSPRRP.PROD_CD;
            LNCICRCM.DATA.EX_RATE = LNCCLNQ.DATA.EXCHANGE_RATE;
            LNCICRCM.DATA.CCY = LNCSPRRP.CCY;
            LNCICRCM.DATA.VALUE_DATE = LNCSPRRP.TR_VAL_DTE;
            if (WS_INFO.WS_FATHER_CONTRACT.trim().length() > 0) {
                S000_CALL_LNZICRCM();
                if (pgmRtn) return;
            }
        }
    }
    public void B090_SWICH_FEE_APP() throws IOException,SQLException,Exception {
        if (!LNCSPRRP.ACAMT[WS_IDX-1].REC_CCY2.equalsIgnoreCase(LNCSPRRP.CCY)) {
