package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.DD.*;
import com.hisun.IB.*;
import com.hisun.AI.*;
import com.hisun.GD.*;
import com.hisun.DC.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSERRP {
    int JIBS_tmp_int;
    DBParm LNTPLDT_RD;
    String JIBS_NumStr;
    String JIBS_tmp_str[] = new String[10];
    DBParm LNTLOAN_RD;
    DBParm LNTCTPY_RD;
    DBParm LNTPACK_RD;
    DBParm LNTCONT_RD;
    DBParm LNTICTL_RD;
    brParm LNTCONT_BR = new brParm();
    DBParm LNTRELA_RD;
    DBParm LNTSUBS_RD;
    DBParm LNTAPRD_RD;
    DBParm LNTAGRE_RD;
    brParm LNTRCVD_BR = new brParm();
    DBParm LNTEXTN_RD;
    DBParm LNTPPRP_RD;
    boolean pgmRtn = false;
    String K_DD_AC = "01";
    String K_IA_AC = "02";
    String K_IB_AC = "03";
    String K_DC_AC = "05";
    String CPN_U_PNZUCISS = "LN-SVR-EARLY-REPAY";
    char K_CKPD_INQ = '0';
    String WS_ERR_MSG = " ";
    String WS_MSG_INFO = "                                                                      ";
    String WS_UDRAC_ACAC = " ";
    LNZSERRP_WS_INFO WS_INFO = new LNZSERRP_WS_INFO();
    LNZSERRP_WS_SETL_AC_INF WS_SETL_AC_INF = new LNZSERRP_WS_SETL_AC_INF();
    LNZSERRP_WS_CHECK_INFO WS_CHECK_INFO = new LNZSERRP_WS_CHECK_INFO();
    double WS_TOT_AMT = 0;
    short WS_H = 0;
    int WS_X = 0;
    int WS_AC_NO = 0;
    LNZSERRP_WS_TX_INFO WS_TX_INFO = new LNZSERRP_WS_TX_INFO();
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
    LNZSERRP_WS_IA_AC WS_IA_AC = new LNZSERRP_WS_IA_AC();
    int WS_FEE_AC_BR = 0;
    double WS_BAL = 0;
    String WS_RELEASE_AC = " ";
    String WS_SYLOAN_AC_TYP = " ";
    String WS_SYLOAN_AC = " ";
    LNZSERRP_WS_LOAN_CONT_AREA WS_LOAN_CONT_AREA = new LNZSERRP_WS_LOAN_CONT_AREA();
    LNZSERRP_WS_BBR_INFO[] WS_BBR_INFO = new LNZSERRP_WS_BBR_INFO[20];
    int WS_IDX = 0;
    int WS_START_DATE = 0;
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
    int WS_I = 0;
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
    char WS_SERRP_FLG = ' ';
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
    GDCSDDDR GDCSDDDR = new GDCSDDDR();
    DCCSIALP DCCSIALP = new DCCSIALP();
    LNCSRPO LNCSRPO = new LNCSRPO();
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
    BPCTCALF BPCTCALF = new BPCTCALF();
    LNCRCONT LNCRCONT = new LNCRCONT();
    LNCSCKPD LNCSCKPD = new LNCSCKPD();
    CICCUST CICCUST = new CICCUST();
    LNCIPAMT LNCIPAMT = new LNCIPAMT();
    GDCSTRAC GDCSTRAC = new GDCSTRAC();
    GDCSRLSR GDCSRLSR = new GDCSRLSR();
    LNRAPRD LNRAPRD = new LNRAPRD();
    DDCUOBAL DDCUOBAL = new DDCUOBAL();
    LNRLOAN LNRLOAN = new LNRLOAN();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGAPV SCCGAPV;
    BPCPQBNK_DATA_INFO BPCRBANK;
    LNCSERRP LNCSERRP;
    SCCBATH SCCBATH;
    public LNZSERRP() {
        for (int i=0;i<20;i++) WS_BBR_INFO[i] = new LNZSERRP_WS_BBR_INFO();
    }
    public void MP(SCCGWA SCCGWA, LNCSERRP LNCSERRP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSERRP = LNCSERRP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSERRP return!");
        Z_RET();
        if (pgmRtn) return;
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
        IBS.init(SCCGWA, LNCSERRP.RC);
        LNCSERRP.TR_MMO = " ";
        LNCSERRP.TR_MMO = "D106";
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B0000_CHECK_SOMETHING();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B010_CHECK_INPUT();
            if (pgmRtn) return;
        }
        R000_CHECK_PAY_AC();
        if (pgmRtn) return;
        if (!LNCSERRP.FEE_INFO[1-1].FEE_CD.equalsIgnoreCase("0")) {
            for (WS_IDX = 1; LNCSERRP.FEE_INFO[WS_IDX-1].FEE_CD.trim().length() != 0 
                && WS_IDX <= 5; WS_IDX += 1) {
                B090_SWICH_FEE_APP();
                if (pgmRtn) return;
            }
        }
        if (LNCSERRP.FEE_AC.trim().length() > 0) {
            B203_COST_FEE_PROC();
            if (pgmRtn) return;
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
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            R000_CHECK_PROG();
            if (pgmRtn) return;
        }
        if (WS_UPD_D44_FLG == 'Y' 
            || WS_UPD_D45_FLG == 'Y') {
            IBS.init(SCCGWA, LNCICTLM);
            LNCICTLM.FUNC = '4';
            LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCSERRP.CTA;
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
        if (LNCSERRP.TOT_P_AMT > 0 
            && !SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
            B900_RLTM_INFORM_CMS();
            if (pgmRtn) return;
        }
        B101_JY_CDD();
        if (pgmRtn) return;
        C000_CALL_DDZUOBAL();
        if (pgmRtn) return;
    }
    public void B0000_CHECK_SOMETHING() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCONT);
        IBS.init(SCCGWA, LNRICTL);
        LNRCONT.KEY.CONTRACT_NO = LNCSERRP.CTA;
        T000_READ_LNTCONT();
        if (pgmRtn) return;
        WS_START_DATE = LNRCONT.CRT_DATE;
        LNRICTL.KEY.CONTRACT_NO = LNCSERRP.CTA;
        T000_READ_LNTICTL2();
        if (pgmRtn) return;
        WS_INFO.WS_CTL_STSW = LNRICTL.CTL_STSW;
        if (WS_INFO.WS_CTL_STSW == null) WS_INFO.WS_CTL_STSW = "";
        JIBS_tmp_int = WS_INFO.WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_INFO.WS_CTL_STSW += " ";
        if (WS_INFO.WS_CTL_STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1")) {
            if (LNCSERRP.TR_VAL_DTE == WS_START_DATE 
                && LNCSERRP.TOT_I_AMT != 0) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DT, LNCSERRP.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        R000_CHECK_BASIS_INFO();
        if (pgmRtn) return;
        R000_CHECK_CTA_STS();
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
        LNCUPRP.COMM_DATA.PRPY_INT_MTH = LNCSERRP.INT_MODE;
        LNCUPRP.COMM_DATA.LN_AC = LNCSERRP.CTA;
        LNCUPRP.COMM_DATA.TR_VAL_DATE = LNCSERRP.TR_VAL_DTE;
        LNCUPRP.COMM_DATA.LAST_CUT_DATE = LNCCLNQ.DATA.INT_CUT_DT;
        LNCUPRP.COMM_DATA.BALANCE = LNCSERRP.BALANCE;
        LNCUPRP.COMM_DATA.TOT_AMTS.PAY_TOT_AMT = LNCSERRP.TOT_AMT;
        LNCUPRP.COMM_DATA.TOT_AMTS.PAY_P_AMT = LNCSERRP.TOT_P_AMT;
        LNCUPRP.COMM_DATA.TOT_AMTS.PAY_I_AMT = LNCSERRP.TOT_I_AMT;
        LNCUPRP.COMM_DATA.TOT_AMTS.PAY_F_AMT1 = LNCSERRP.PC_AMT;
        LNCUPRP.COMM_DATA.RDI_FLG = LNCSERRP.RDI_FLG;
        LNCUPRP.COMM_DATA.RDI_AMT = LNCSERRP.RDI_AMT;
        LNCUPRP.COMM_DATA.HRG_AMT = LNCSERRP.HRG_AMT;
        LNCUPRP.COMM_DATA.SYN_FLG = LNCSERRP.SYN_FLG;
        LNCUPRP.COMM_DATA.TR_MMO = LNCSERRP.TR_MMO;
        LNCUPRP.B_AMTS.B_I_AMT = WS_PAY_I_AMT;
        LNCUPRP.B_AMTS.B_O_AMT = WS_PAY_O_AMT;
        LNCUPRP.B_AMTS.B_L_AMT = WS_PAY_L_AMT;
        LNCUPRP.PROJ_INT = LNCSERRP.PROJ_INT;
        CEP.TRC(SCCGWA, LNCSERRP.S_TERM);
        LNCUPRP.S_TERM = LNCSERRP.S_TERM;
        CEP.TRC(SCCGWA, LNCUPRP.S_TERM);
        LNCUPRP.N_RATE_TYP = LNCSERRP.N_RATE_TYP;
        LNCUPRP.N_RATE = LNCSERRP.N_RATE;
        LNCUPRP.O_RATE_TYP = LNCSERRP.O_RATE_TYP;
        LNCUPRP.O_RATE = LNCSERRP.O_RATE;
        LNCUPRP.L_RATE_FLG = LNCSERRP.L_RATE_FLG;
        LNCUPRP.L_RATE_TYP = LNCSERRP.L_RATE_TYP;
        LNCUPRP.L_RATE = LNCSERRP.L_RATE;
        LNCUPRP.METH = LNCSERRP.METH;
        LNCUPRP.RPSP_NO = LNCSERRP.RPSP_NO;
        LNCUPRP.RAT_MTH = LNCSERRP.RAT_MTH;
        LNCUPRP.INT_F_UN = LNCSERRP.INT_F_UN;
        LNCUPRP.INT_F_PD = LNCSERRP.INT_F_PD;
        LNCUPRP.FLT_IPER = LNCSERRP.FLT_IPER;
        LNCUPRP.FLT_ADJF = LNCSERRP.FLT_ADJF;
        LNCUPRP.FT_FLG = LNCSERRP.FT_FLG;
        LNCUPRP.FT_DAY = LNCSERRP.FT_DAY;
        LNCUPRP.FT_F_DAY = LNCSERRP.FT_F_DAY;
        LNCUPRP.FLT_MTH = LNCSERRP.FLT_MTH;
        LNCUPRP.RATE_TYP = LNCSERRP.RATE_TYP;
        LNCUPRP.RATE_PD = LNCSERRP.RATE_PD;
        LNCUPRP.RATE_VAR = LNCSERRP.RATE_VAR;
        LNCUPRP.RATE_PCT = LNCSERRP.RATE_PCT;
        LNCUPRP.RATE_INT = LNCSERRP.RATE_INT;
        LNCUPRP.INT_RATE = LNCSERRP.INT_RATE;
        LNCUPRP.PEN_RRAT = LNCSERRP.PEN_RRAT;
        LNCUPRP.PEN_TYP = LNCSERRP.PEN_TYP;
        LNCUPRP.PEN_RATT = LNCSERRP.PEN_RATT;
        LNCUPRP.PEN_RATC = LNCSERRP.PEN_RATC;
        LNCUPRP.PEN_SPR = LNCSERRP.PEN_SPR;
        LNCUPRP.PEN_PCT = LNCSERRP.PEN_PCT;
        LNCUPRP.PEN_IRAT = LNCSERRP.PEN_IRAT;
        LNCUPRP.INT_MTH = LNCSERRP.INT_MTH;
        LNCUPRP.CPND_USE = LNCSERRP.CPND_USE;
        LNCUPRP.CPND_RTY = LNCSERRP.CPND_RTY;
        LNCUPRP.CPND_TYP = LNCSERRP.CPND_TYP;
        LNCUPRP.CPNDRATT = LNCSERRP.CPNDRATT;
        LNCUPRP.CPNDRATC = LNCSERRP.CPNDRATC;
        LNCUPRP.CPND_SPR = LNCSERRP.CPND_SPR;
        LNCUPRP.CPND_PCT = LNCSERRP.CPND_PCT;
        LNCUPRP.CPNDRATE = LNCSERRP.CPNDRATE;
        LNCUPRP.ABUS_RAT = LNCSERRP.ABUS_RAT;
        LNCUPRP.ABUS_TYP = LNCSERRP.ABUS_TYP;
        LNCUPRP.ABUSRATT = LNCSERRP.ABUSRATT;
        LNCUPRP.ABUSRATC = LNCSERRP.ABUSRATC;
        LNCUPRP.ABUSSPR = LNCSERRP.ABUSSPR;
        LNCUPRP.ABUSPCT = LNCSERRP.ABUSPCT;
        LNCUPRP.ABUSIRAT = LNCSERRP.ABUSIRAT;
        LNCUPRP.ACAMT[1-1].DEP_AC2 = LNCSERRP.ACAMT[1-1].DEP_AC2;
        for (WS_X = 1; WS_X <= WS_AC_NO 
            && (LNCSERRP.ACAMT[WS_X-1].STL_MTH2.trim().length() != 0 
            || LNCSERRP.ACAMT[WS_X-1].DEP_AC2.trim().length() != 0); WS_X += 1) {
            LNCUPRP.ACAMT[WS_X-1].STL_MTH2 = LNCSERRP.ACAMT[WS_X-1].STL_MTH2;
            LNCUPRP.ACAMT[WS_X-1].CHQ_NO2 = LNCSERRP.ACAMT[WS_X-1].CHQ_NO2;
            LNCUPRP.ACAMT[WS_X-1].DEP_AC2 = LNCSERRP.ACAMT[WS_X-1].DEP_AC2;
            LNCUPRP.ACAMT[WS_X-1].AC_FLG2 = LNCSERRP.ACAMT[WS_X-1].AC_FLG2;
            LNCUPRP.ACAMT[WS_X-1].PAY_AMT2 = LNCSERRP.ACAMT[WS_X-1].PAY_AMT2;
            LNCUPRP.ACAMT[WS_X-1].AMT_FRM2 = LNCSERRP.ACAMT[WS_X-1].AMT_FRM2;
            LNCUPRP.ACAMT[WS_X-1].REC_CCY2 = LNCSERRP.ACAMT[WS_X-1].REC_CCY2;
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
        if (LNCSERRP.TOT_P_AMT > 0 
            && LNCUCMMT.REV_CMMT == 'Y' 
            && !WS_INFO.WS_CTL_STSW.substring(71 - 1, 71 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.init(SCCGWA, LNCICRCM);
            LNCICRCM.FUNC = 'P';
            LNCICRCM.DATA.AMT = LNCSERRP.TOT_P_AMT;
            if (LNCSERRP.TOT_P_AMT == LNCSERRP.BALANCE) {
                LNCICRCM.DATA.FLG = 'C';
            }
            LNCICRCM.DATA.TRAN_TYPE = 'P';
            LNCICRCM.DATA.C_T_NO = WS_INFO.WS_FATHER_CONTRACT;
            LNCICRCM.DATA.CONT_NO = LNCSERRP.CTA;
            LNCICRCM.DATA.CI_NO = LNCSERRP.CI_NO;
            LNCICRCM.DATA.CI_ATTR = LNCCLNQ.DATA.CI_ATTR;
            LNCICRCM.DATA.PROD_TYPE = LNCSERRP.PROD_CD;
            LNCICRCM.DATA.EX_RATE = LNCCLNQ.DATA.EXCHANGE_RATE;
            LNCICRCM.DATA.CCY = LNCSERRP.CCY;
            LNCICRCM.DATA.VALUE_DATE = LNCSERRP.TR_VAL_DTE;
            if (WS_INFO.WS_FATHER_CONTRACT.trim().length() > 0) {
                S000_CALL_LNZICRCM();
                if (pgmRtn) return;
            }
        }
    }
    public void B090_SWICH_FEE_APP() throws IOException,SQLException,Exception {
        if (!LNCSERRP.ACAMT[WS_IDX-1].REC_CCY2.equalsIgnoreCase(LNCSERRP.CCY)) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FEE_CCY, LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_FEE_CODE_PC = LNCSERRP.FEE_INFO[WS_IDX-1].FEE_CD;
        if (WS_FEE_CODE_PC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FEE_PC, LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        B200_SET_FEE_INFO();
        if (pgmRtn) return;
        WS_FEE_PC_AMT = LNCSERRP.FEE_INFO[WS_IDX-1].FEE_AMT;
        IBS.init(SCCGWA, BPCFFCON);
        BPCFFCON.FEE_INFO.FEE_CNT = 1;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_FLG = '0';
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_CODE = WS_FEE_CODE_PC;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_CCY = LNCSERRP.CCY;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_BAS = WS_FEE_PC_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_AMT = WS_FEE_PC_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_AC_TY = BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_AC = BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_CCY = BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_BAS = WS_FEE_PC_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_AMT = WS_FEE_PC_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].ADJ_AMT = WS_FEE_PC_AMT;
        BPCFFCON.FEE_INFO.ACCOUNT_BR = LNCSERRP.BR;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].AMO_FLG = '1';
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].AMO_STDT = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].AMO_EDDT = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.ACCOUNT_BR);
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[1-1].AMO_FLG);
        S000_CALL_BPZFFCON();
        if (pgmRtn) return;
    }
    public void B203_COST_FEE_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCSERRP.FEE_TYP);
        CEP.TRC(SCCGWA, LNCSERRP.FEE_AC);
        IBS.init(SCCGWA, BPCFFTXI);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        if (LNCSERRP.FEE_TYP.equalsIgnoreCase("07")) {
            BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '1';
            BPCFFTXI.TX_DATA.CI_NO = LNCSERRP.CI_NO;
        } else {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.DATA.AGR_NO = LNCSERRP.FEE_AC;
            CICQACRI.FUNC = 'A';
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            if (LNCSERRP.FEE_TYP.equalsIgnoreCase("05")) {
                BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '4';
                BPCFFTXI.TX_DATA.CARD_PSBK_NO = LNCSERRP.FEE_AC;
                BPCFFTXI.TX_DATA.CI_NO = CICQACRI.O_DATA.O_CI_NO;
            } else {
                if (LNCSERRP.FEE_TYP.equalsIgnoreCase("02")) {
                    BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '3';
                } else {
                    BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '0';
                }
                BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = LNCSERRP.FEE_AC;
                BPCFFTXI.TX_DATA.CI_NO = CICQACRI.O_DATA.O_CI_NO;
            }
            CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP);
        }
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = LNCSERRP.CCY;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.SERV_CODE);
        BPCFFTXI.TX_DATA.SVR_CD = SCCGWA.COMM_AREA.SERV_CODE;
        if (LNCSERRP.CCY.equalsIgnoreCase("156")) {
            BPCFFTXI.TX_DATA.CCY_TYPE = '1';
        } else {
            BPCFFTXI.TX_DATA.CCY_TYPE = LNCSERRP.FEE_TYPC;
        }
        S000_CALL_BPZFFTXI();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCTCALF);
        BPCTCALF.INPUT_AREA.FUNC_CODE = 'C';
        BPCTCALF.INPUT_AREA.TX_CCY = LNCSERRP.CCY;
        BPCTCALF.INPUT_AREA.TX_AMT = LNCSERRP.TOT_P_AMT;
        BPCTCALF.INPUT_AREA.PROD_CODE = LNCSERRP.PROD_CD;
        BPCTCALF.INPUT_AREA.PROD_CODE1 = LNCSERRP.PROD_CD;
        CEP.TRC(SCCGWA, LNCSERRP.PROD_CD);
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.PROD_CODE);
        BPCTCALF.INPUT_AREA.CPN_ID = CPN_U_PNZUCISS;
        BPCTCALF.INPUT_AREA.TX_AC = LNCSERRP.FEE_AC;
        if (!LNCSERRP.FEE_TYP.equalsIgnoreCase("07")) {
            BPCTCALF.INPUT_AREA.TX_CI = CICQACRI.O_DATA.O_CI_NO;
        } else {
            BPCTCALF.INPUT_AREA.TX_CI = LNCSERRP.CI_NO;
        }
        BPCTCALF.INPUT_AREA.FREE_FMT = "01";
        BPCTCALF.INPUT_AREA.TX_CNT = 1;
        S000_CALL_BPZFCALF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[1-1].CHG_AMT);
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[2-1].CHG_AMT);
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[1-1].ADJ_AMT);
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[2-1].ADJ_AMT);
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[1-1].DER_AMT);
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[2-1].DER_AMT);
    }
    public void B200_SET_FEE_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOFSVR);
        BPCOFSVR.FUNC = 'I';
        BPCOFSVR.KEY.SVR_NO = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        S000_CALL_BPZFSSVR();
        if (pgmRtn) return;
        for (WS_H = 1; WS_H <= 20 
            && BPCOFSVR.VAL.DATA[WS_H-1].FEE_CODE.trim().length() != 0; WS_H += 1) {
            CEP.TRC(SCCGWA, BPCOFSVR.VAL.DATA[WS_H-1].PROD_CODE);
            CEP.TRC(SCCGWA, BPCOFSVR.VAL.DATA[WS_H-1].FEE_CODE);
            CEP.TRC(SCCGWA, LNCSERRP.PROD_CD);
            if (BPCOFSVR.VAL.DATA[WS_H-1].PROD_CODE.trim().length() == 0) {
                WS_FEE_CODE_PC = BPCOFSVR.VAL.DATA[WS_H-1].FEE_CODE;
            }
            if (BPCOFSVR.VAL.DATA[WS_H-1].PROD_CODE.equalsIgnoreCase(LNCSERRP.PROD_CD)) {
                WS_FEE_CODE_HRG = BPCOFSVR.VAL.DATA[WS_H-1].FEE_CODE;
            }
        }
        CEP.TRC(SCCGWA, WS_FEE_CODE_PC);
        CEP.TRC(SCCGWA, WS_FEE_CODE_HRG);
    }
    public void B200_SET_FEE_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = LNCSERRP.FEE_TYP.charAt(0);
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = LNCSERRP.FEE_AC;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = LNCSERRP.CCY;
        if (BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY.equalsIgnoreCase("156")) {
            BPCFFTXI.TX_DATA.CCY_TYPE = '1';
        } else {
            BPCFFTXI.TX_DATA.CCY_TYPE = LNCSERRP.FEE_TYPC;
        }
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC;
        if (CICACCU.DATA.AGR_NO.trim().length() > 0 
            && (BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP == '0' 
            || BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP == '4')) {
            S000_CALL_CIZACCU();
            if (pgmRtn) return;
        }
        BPCFFTXI.TX_DATA.CI_NO = CICACCU.DATA.CI_NO;
        BPCFFTXI.TX_DATA.BSNS_NO = LNCSERRP.CTA;
        S000_CALL_BPZFFTXI();
        if (pgmRtn) return;
    }
    public void B090_SWICH_OTHER_APP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRAGRE);
        LNRAGRE.KEY.CONTRACT_NO = LNCSERRP.CTA;
        T000_READ_LNTAGRE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "KOUKUAN");
        CEP.TRC(SCCGWA, LNCSERRP.TOT_AMT);
        WS_PAY_AMT = LNCSERRP.TOT_AMT;
        WS_TR_TOT_AMT = LNCSERRP.TOT_AMT;
        WS_PAY_AMT = LNCSERRP.TOT_AMT - LNCSERRP.PC_AMT;
        if (LNCSERRP.RDI_FLG == 'Y' 
            && LNCSERRP.RDI_AMT != 0 
            && LNCCLNQ.DATA.CONT_TYPE.equalsIgnoreCase("CLDL")) {
            WS_PAY_AMT = WS_PAY_AMT - LNCSERRP.RDI_AMT;
        }
        CEP.TRC(SCCGWA, LNCSERRP.ACAMT[1-1].STL_MTH2);
        CEP.TRC(SCCGWA, LNCSERRP.ACAMT[2-1].STL_MTH2);
        CEP.TRC(SCCGWA, LNCSERRP.ACAMT[1-1].DEP_AC2);
        CEP.TRC(SCCGWA, LNCSERRP.ACAMT[2-1].DEP_AC2);
        CEP.TRC(SCCGWA, WS_AC_NO);
        for (WS_X = 1; WS_X <= WS_AC_NO 
            && (LNCSERRP.ACAMT[WS_X-1].STL_MTH2.trim().length() != 0 
            || LNCSERRP.ACAMT[WS_X-1].DEP_AC2.trim().length() != 0); WS_X += 1) {
            if (WS_PAY_AMT != 0) {
                WS_KOU_KUAN_AMT = WS_PAY_AMT;
                if (LNCSERRP.ACAMT[WS_X-1].PAY_AMT2 != 0 
                    && LNCSERRP.ACAMT[WS_X-1].PAY_AMT2 < WS_KOU_KUAN_AMT) {
                    WS_KOU_KUAN_AMT = LNCSERRP.ACAMT[WS_X-1].PAY_AMT2;
                }
                CEP.TRC(SCCGWA, WS_KOU_KUAN_AMT);
                CEP.TRC(SCCGWA, LNCSERRP.ACAMT[WS_X-1].STL_MTH2);
                if (LNCSERRP.ACAMT[WS_X-1].STL_MTH2.equalsIgnoreCase("01")
                    || LNCSERRP.ACAMT[WS_X-1].STL_MTH2.equalsIgnoreCase("07")
                    || LNCSERRP.ACAMT[WS_X-1].STL_MTH2.equalsIgnoreCase("05")
                    || LNCSERRP.ACAMT[WS_X-1].STL_MTH2.equalsIgnoreCase("06")) {
                    B090_SWICH_DEPS_PROC();
                    if (pgmRtn) return;
                } else if (LNCSERRP.ACAMT[WS_X-1].STL_MTH2.equalsIgnoreCase("02")) {
                    B090_SWICH_IAAC_PROC();
                    if (pgmRtn) return;
                } else if (LNCSERRP.ACAMT[WS_X-1].STL_MTH2.equalsIgnoreCase("03")) {
                    B090_SWICH_NOSCD_PROC();
                    if (pgmRtn) return;
                } else if (LNCSERRP.ACAMT[WS_X-1].STL_MTH2.equalsIgnoreCase("04")) {
                    B090_SWICH_GM_PROC();
                    if (pgmRtn) return;
                } else if (LNCSERRP.ACAMT[WS_X-1].STL_MTH2.equalsIgnoreCase("08")) {
                    B090_SWICH_CHAS_PROC();
                    if (pgmRtn) return;
                } else if (LNCSERRP.ACAMT[WS_X-1].STL_MTH2.equalsIgnoreCase("09")) {
                    B090_SWITCH_PARM_PROC();
                    if (pgmRtn) return;
                } else {
                }
                if (LNCSERRP.ACAMT[WS_X-1].STL_MTH2.equalsIgnoreCase("08")) {
                    DDCUDRAC.TX_TYPE = 'C';
                } else {
                    DDCUDRAC.TX_TYPE = 'T';
                }
                CEP.TRC(SCCGWA, WS_KOU_KUAN_AMT);
                if (WS_KOU_KUAN_AMT <= WS_PAY_AMT) {
                    WS_PAY_AMT -= WS_KOU_KUAN_AMT;
                }
            } else {
                WS_KOU_KUAN_AMT = 0;
            }
            LNCSERRP.ACAMT[WS_X-1].PAY_AMT2 = WS_KOU_KUAN_AMT;
            CEP.TRC(SCCGWA, LNCSERRP.ACAMT[WS_X-1].STL_MTH2);
            CEP.TRC(SCCGWA, LNCSERRP.ACAMT[WS_X-1].DEP_AC2);
            CEP.TRC(SCCGWA, LNCSERRP.ACAMT[WS_X-1].REC_CCY2);
            CEP.TRC(SCCGWA, LNCSERRP.ACAMT[WS_X-1].PAY_AMT2);
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            if (WS_PAY_AMT != 0) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_E_BAL_NOT_ENOUGH, LNCSERRP.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B101_JY_CDD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '3';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCSERRP.CTA;
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
            LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCSERRP.CTA;
            IBS.init(SCCGWA, DCCSIALP);
            DCCSIALP.FUNC = 'D';
            DCCSIALP.LN_AC = LNCSERRP.CTA;
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
        LNRLOAN.KEY.CONTRACT_NO = LNCSERRP.CTA;
        T000_READ_LNTLOAN();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNRLOAN.CTL_TYPE);
        if (LNRLOAN.CTL_TYPE == '8') {
            CEP.TRC(SCCGWA, LNCSERRP.TOT_P_AMT);
            IBS.init(SCCGWA, DDCUOBAL);
            DDCUOBAL.AC = LNCSERRP.CTA;
            DDCUOBAL.OD_AMT = LNCSERRP.TOT_P_AMT;
            S000_CALL_DDZUOBAL();
            if (pgmRtn) return;
        } else {
        }
    }
    public void S000_CALL_DCZSIALP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-AUTO-LN-PLAN", DCCSIALP);
    }
    public void B090_SWICH_DEPS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUDRAC);
        DDCUDRAC.CHK_PSW_FLG = 'N';
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.BANK_DR_FLG = 'Y';
        DDCUDRAC.TSTS_TABL = "0014";
        DDCUDRAC.TX_BAL_FLG = 'Y';
        DDCUDRAC.BV_TYP = '3';
        DDCUDRAC.TX_AMT = WS_KOU_KUAN_AMT;
        if (LNCSERRP.ACAMT[WS_X-1].STL_MTH2.equalsIgnoreCase("05") 
            || LNCSERRP.ACAMT[WS_X-1].STL_MTH2.equalsIgnoreCase("06")) {
            DDCUDRAC.CARD_NO = LNCSERRP.ACAMT[WS_X-1].DEP_AC2;
        } else {
            DDCUDRAC.AC = LNCSERRP.ACAMT[WS_X-1].DEP_AC2;
        }
        if (LNCSERRP.CCY.equalsIgnoreCase("156")) {
            DDCUDRAC.CCY_TYPE = '1';
        } else {
            DDCUDRAC.CCY_TYPE = LNCSERRP.ACAMT[1-1].CCY_TYP;
        }
        CEP.TRC(SCCGWA, DDCUDRAC.CCY_TYPE);
        DDCUDRAC.CCY = LNCSERRP.ACAMT[WS_X-1].REC_CCY2;
        DDCUDRAC.VAL_DATE = LNCSERRP.TR_VAL_DTE;
        DDCUDRAC.TX_MMO = LNCSERRP.TR_MMO;
        if (LNCSERRP.ACAMT[WS_X-1].CHQ_NO2.trim().length() > 0 
            && LNCSERRP.ACAMT[WS_X-1].STL_MTH2.equalsIgnoreCase("07")) {
            DDCUDRAC.CHQ_TYPE = LNCSERRP.ACAMT[WS_X-1].CHQ_TYPE;
            DDCUDRAC.CHQ_NO = LNCSERRP.ACAMT[WS_X-1].CHQ_NO2;
            DDCUDRAC.CHQ_ISS_DATE = LNCSERRP.ACAMT[WS_X-1].ISS_DATE;
            DDCUDRAC.PAY_PSWD = LNCSERRP.ACAMT[WS_X-1].PAY_PSWD;
        }
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.ACAC_NO = LNCSERRP.CTA;
        CICQACAC.FUNC = 'A';
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        DDCUDRAC.RLT_AC = LNCSERRP.CTA;
        if (DDCUDRAC.TX_AMT != 0) {
            S000_CALL_DDZUDRAC();
            if (pgmRtn) return;
        }
        WS_KOU_KUAN_AMT = DDCUDRAC.TX_AMT;
        WS_UDRAC_ACAC = DDCUDRAC.ACAC;
        CEP.TRC(SCCGWA, DDCUDRAC.TX_AMT);
        CEP.TRC(SCCGWA, DDCUDRAC.ACAC);
    }
    public void B090_SWICH_CHAS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUABOX);
        BPCUABOX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCUABOX.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUABOX.CCY = LNCSERRP.CCY;
        BPCUABOX.CCY_TYP = '0';
        BPCUABOX.AMT = WS_KOU_KUAN_AMT;
        BPCUABOX.OPP_AC = LNCSERRP.CTA;
        BPCUABOX.CASH_NO = "" + 117;
        JIBS_tmp_int = BPCUABOX.CASH_NO.length();
        for (int i=0;i<3-JIBS_tmp_int;i++) BPCUABOX.CASH_NO = "0" + BPCUABOX.CASH_NO;
        BPCUABOX.RMK = "LOAN PRE-REPAYMENT";
        S000_CALL_BPZUABOX();
        if (pgmRtn) return;
        WS_KOU_KUAN_AMT = BPCUABOX.AMT;
    }
    public void B090_SWICH_IAAC_PROC() throws IOException,SQLException,Exception {
        WS_AC = LNCSERRP.ACAMT[WS_X-1].DEP_AC2;
        WS_AC_CCY = LNCSERRP.ACAMT[WS_X-1].REC_CCY2;
        B042_GET_SUSP_AC_AVABAL();
        if (pgmRtn) return;
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.EVENT_CODE = "DR";
        AICUUPIA.DATA.AC_NO = LNCSERRP.ACAMT[WS_X-1].DEP_AC2;
        AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.CCY = LNCSERRP.ACAMT[WS_X-1].REC_CCY2;
        if (AICPQMIB.OUTPUT_DATA.BAL_RFLG == 'N' 
            && WS_PQMIB_CBAL < WS_KOU_KUAN_AMT) {
            AICUUPIA.DATA.AMT = WS_PQMIB_CBAL;
        } else {
            AICUUPIA.DATA.AMT = WS_KOU_KUAN_AMT;
        }
        AICUUPIA.DATA.VALUE_DATE = LNCSERRP.TR_VAL_DTE;
        AICUUPIA.DATA.RVS_NO = LNCSERRP.ACAMT[WS_X-1].RVS_NO2;
        AICUUPIA.DATA.PAY_MAN = LNCSERRP.ACAMT[WS_X-1].CI_CNM2;
        if (AICUUPIA.DATA.AMT != 0) {
            S000_CALL_AIZUUPIA();
            if (pgmRtn) return;
        }
        WS_KOU_KUAN_AMT = AICUUPIA.DATA.AMT;
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
    public void B090_SWICH_NOSCD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCPOSTA);
        IBCPOSTA.AC = LNCSERRP.ACAMT[WS_X-1].DEP_AC2;
        IBCPOSTA.CCY = LNCSERRP.ACAMT[WS_X-1].REC_CCY2;
        IBCPOSTA.AMT = WS_KOU_KUAN_AMT;
        IBCPOSTA.TXTYPE = "01";
        IBCPOSTA.VAL_DATE = LNCSERRP.TR_VAL_DTE;
        IBCPOSTA.ENTRY_FLG = '1';
        IBCPOSTA.SIGN = 'D';
        IBCPOSTA.ACT_CTR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        IBCPOSTA.ACT_CTR = LNCSERRP.BR;
        IBCPOSTA.OUR_REF = LNCSERRP.CTA;
        IBCPOSTA.THR_REF = LNCSERRP.CTA;
        IBCPOSTA.TX_MMO = LNCSERRP.TR_MMO;
        if (IBCPOSTA.AMT != 0) {
            S000_CALL_IBZDRAC();
            if (pgmRtn) return;
        }
        WS_KOU_KUAN_AMT = IBCPOSTA.AMT;
    }
    public void B090_SWICH_GM_PROC() throws IOException,SQLException,Exception {
        B044_GET_GDA_AC_AVABAL();
        if (pgmRtn) return;
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
            if (LNCSERRP.ACAMT[WS_X-1].DEP_AC2.trim().length() == 0) {
                for (WS_G = 1; WS_G <= GDCIQLDR.TCNT 
                    && WS_KOU_KUAN_AMT != 0; WS_G += 1) {
                    CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_G-1].OUT_AC);
                    CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_G-1].CCY);
                    CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_G-1].RAMT);
                    if (GDCIQLDR.INFO[WS_G-1].CCY.equalsIgnoreCase(LNCSERRP.CCY)) {
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
                for (WS_G = 1; WS_G <= GDCIQLDR.TCNT 
                    && WS_KOU_KUAN_AMT != 0; WS_G += 1) {
                    CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_G-1].OUT_AC);
                    CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_G-1].CCY);
                    CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_G-1].RAMT);
                    if (LNCSERRP.ACAMT[WS_X-1].DEP_AC2.equalsIgnoreCase(GDCIQLDR.INFO[WS_G-1].OUT_AC) 
                        && LNCSERRP.ACAMT[WS_X-1].REC_CCY2.equalsIgnoreCase(GDCIQLDR.INFO[WS_G-1].CCY) 
                        && GDCIQLDR.INFO[WS_G-1].RAMT != 0) {
                        if (WS_KOU_KUAN_AMT > GDCIQLDR.INFO[WS_G-1].RAMT) {
                            WS_KOU_GDA_AMT = 0;
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
            }
        } else {
        }
    }
    public void B090_SWITCH_PARM_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCSDDDR);
        GDCSDDDR.DRAC = LNCSERRP.ACAMT[WS_X-1].DEP_AC2;
        GDCSDDDR.AMT = WS_KOU_KUAN_AMT;
        GDCSDDDR.CCY = LNCSERRP.CCY;
        if (LNCSERRP.CCY.equalsIgnoreCase("156")) {
            GDCSDDDR.CCY_TYP = '1';
        } else {
            GDCSDDDR.CCY_TYP = '2';
        }
        GDCSDDDR.STLT = ' ';
        GDCSDDDR.CRAC = LNRAGRE.PAPER_NO;
        if (GDCSDDDR.AMT != 0) {
            WS_SEQ += 1;
            S000_CALL_GDZSDDDR();
            if (pgmRtn) return;
        }
        WS_KOU_KUAN_AMT = GDCSDDDR.AMT;
        CEP.TRC(SCCGWA, GDCSDDDR.AMT);
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
    public void B091_SWICH_CTPY() throws IOException,SQLException,Exception {
        WS_PI_PROC_FLG = ' ';
        if (WS_INFO.WS_CTL_STSW == null) WS_INFO.WS_CTL_STSW = "";
        JIBS_tmp_int = WS_INFO.WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_INFO.WS_CTL_STSW += " ";
        if (WS_INFO.WS_CTL_STSW.substring(37 - 1, 37 + 1 - 1).equalsIgnoreCase("1") 
            && LNCSERRP.TOT_P_AMT > 0) {
            WS_PI_PROC_FLG = 'P';
            B061_SOLD_OUT_P_PROC();
            if (pgmRtn) return;
        }
        WS_PI_PROC_FLG = 'I';
        if (WS_INFO.WS_CTL_STSW == null) WS_INFO.WS_CTL_STSW = "";
        JIBS_tmp_int = WS_INFO.WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_INFO.WS_CTL_STSW += " ";
        if (WS_INFO.WS_CTL_STSW.substring(38 - 1, 38 + 1 - 1).equalsIgnoreCase("1")) {
            B062_SOLD_OUT_I_PROC();
            if (pgmRtn) return;
        } else {
            B063_PURCHASE_BACK_I_PROC();
            if (pgmRtn) return;
        }
        if (WS_I_FLG == 'Y') {
            WS_REC_FLG = 'I';
            B067_WRITE_LNTTRAN();
            if (pgmRtn) return;
        }
        if (WS_O_FLG == 'Y') {
            WS_REC_FLG = 'O';
            B067_WRITE_LNTTRAN();
            if (pgmRtn) return;
        }
    }
    public void B061_SOLD_OUT_P_PROC() throws IOException,SQLException,Exception {
        B162_SOLD_REPAY_OUT_PROC();
        if (pgmRtn) return;
    }
    public void B162_SOLD_REPAY_OUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCTPY);
        LNRCTPY.KEY.CONTRACT_NO = LNCSERRP.CTA;
        LNRCTPY.KEY.TR_TYP = '1';
        T000_READ_LNTCTPY1();
        if (pgmRtn) return;
        B065_UPD_PACK_AND_CR_PROC();
        if (pgmRtn) return;
        B066_UPDATE_OR_WRITE_PLDT();
        if (pgmRtn) return;
        if (WS_PI_PROC_FLG == 'I') {
            WS_O_FLG = 'Y';
            WS_CTPY_O_AMT += WS_PAY_R_I_AMT;
            WS_O_AMT2 = WS_CTPY_O_AMT;
        }
    }
    public void B065_UPD_PACK_AND_CR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPACK);
        LNRPACK.KEY.BTH_PK = LNRCTPY.KEY.BTH_PK;
        T000_READ_UPDATE_LNTPACK();
        if (pgmRtn) return;
        IBS.init(SCCGWA, WS_TX_INFO);
        if (WS_PI_PROC_FLG == 'P') {
            WS_TX_INFO.WS_TX_AC_TYP = LNRPACK.P_AC_TYP;
            WS_TX_INFO.WS_TX_AC = LNRPACK.P_AC;
            WS_TX_INFO.WS_TX_AMT = LNCSERRP.TOT_P_AMT;
            B095_TX_AMT_PROC();
            if (pgmRtn) return;
            LNRPACK.TOT_AMT += LNCSERRP.TOT_P_AMT;
            LNRPACK.T_PRIN_AMT += LNCSERRP.TOT_P_AMT;
        }
        if (WS_PI_PROC_FLG == 'I') {
            WS_TX_INFO.WS_TX_AC_TYP = LNRPACK.I_AC_TYP;
            WS_TX_INFO.WS_TX_AC = LNRPACK.I_AC;
            WS_TX_INFO.WS_TX_AMT = WS_PAY_R_I_AMT;
            B095_TX_AMT_PROC();
            if (pgmRtn) return;
            LNRPACK.TOT_AMT += WS_PAY_R_I_AMT;
            LNRPACK.T_INT_AMT += WS_PAY_R_I_AMT;
        }
        T000_REWRITE_LNTPACK();
        if (pgmRtn) return;
    }
    public void B066_UPDATE_OR_WRITE_PLDT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPLDT);
        LNRPLDT.KEY.CONTRACT_NO = LNCSERRP.CTA;
        LNRPLDT.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRPLDT.KEY.BTH_PK = LNRPACK.KEY.BTH_PK;
        T000_READ_UPDATE_LNTPLDT();
        if (pgmRtn) return;
        if (WS_READ_LNTPLDT_FLG == 'F') {
            if (WS_PI_PROC_FLG == 'P') {
                LNRPLDT.T_PAY_AMT += LNCSERRP.TOT_P_AMT;
                LNRPLDT.PAY_PRIN += LNCSERRP.TOT_P_AMT;
            }
            if (WS_PI_PROC_FLG == 'I') {
                LNRPLDT.T_PAY_AMT += WS_PAY_R_I_AMT;
                LNRPLDT.PAY_INT += WS_PAY_R_I_AMT;
            }
            T000_REWRITE_LNTPLDT();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, LNRPLDT);
            if (WS_PI_PROC_FLG == 'P') {
                LNRPLDT.T_PAY_AMT = LNCSERRP.TOT_P_AMT;
                LNRPLDT.PAY_PRIN = LNCSERRP.TOT_P_AMT;
                LNRPLDT.PAY_INT = 0;
            }
            if (WS_PI_PROC_FLG == 'I') {
                LNRPLDT.T_PAY_AMT = WS_PAY_R_I_AMT;
                LNRPLDT.PAY_PRIN = 0;
                LNRPLDT.PAY_INT = WS_PAY_R_I_AMT;
            }
            LNRPLDT.KEY.CONTRACT_NO = LNCSERRP.CTA;
            LNRPLDT.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
            LNRPLDT.KEY.BTH_PK = LNRPACK.KEY.BTH_PK;
            LNRPLDT.CCY = LNCSERRP.CCY;
            T000_WRITE_LNTPLDT();
            if (pgmRtn) return;
        }
    }
    public void B062_SOLD_OUT_I_PROC() throws IOException,SQLException,Exception {
        WS_I_LEFT_AMT = LNCSERRP.TOT_I_AMT;
        if (WS_INFO.WS_CTL_STSW == null) WS_INFO.WS_CTL_STSW = "";
        JIBS_tmp_int = WS_INFO.WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_INFO.WS_CTL_STSW += " ";
        if (WS_INFO.WS_CTL_STSW.substring(45 - 1, 45 + 1 - 1).equalsIgnoreCase("1") 
            && WS_I_LEFT_AMT > 0) {
            B161_PURBACK_OWN_OUT_I_PROC();
            if (pgmRtn) return;
            WS_I_LEFT_AMT -= WS_PAY_R_I_AMT;
        }
        if (WS_INFO.WS_CTL_STSW == null) WS_INFO.WS_CTL_STSW = "";
        JIBS_tmp_int = WS_INFO.WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_INFO.WS_CTL_STSW += " ";
        if (WS_INFO.WS_CTL_STSW.substring(44 - 1, 44 + 1 - 1).equalsIgnoreCase("1") 
            && WS_I_LEFT_AMT > 0) {
            B160_SOLD_OWN_US_I_PROC();
            if (pgmRtn) return;
            WS_I_LEFT_AMT -= WS_PAY_R_I_AMT;
        }
        if (WS_I_LEFT_AMT > 0) {
            WS_PAY_R_I_AMT = WS_I_LEFT_AMT;
            B162_SOLD_REPAY_OUT_PROC();
            if (pgmRtn) return;
        }
    }
    public void B161_PURBACK_OWN_OUT_I_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCTPY);
        WS_CTPY_TR_TYP = '2';
        B064_UPDATE_LNTCTPY_PROC();
        if (pgmRtn) return;
        if (WS_PAY_R_I_AMT > 0) {
            B065_UPD_PACK_AND_CR_PROC();
            if (pgmRtn) return;
            B066_UPDATE_OR_WRITE_PLDT();
            if (pgmRtn) return;
            WS_O_FLG = 'Y';
            WS_CTPY_O_AMT = WS_PAY_R_I_AMT;
            WS_O_AMT1 = WS_PAY_R_I_AMT;
        }
        if (LNRCTPY.I_PAY_AMT >= LNRCTPY.I_REC_AMT) {
            WS_UPD_D45_FLG = 'Y';
        }
    }
    public void B064_UPDATE_LNTCTPY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCTPY);
        LNRCTPY.KEY.CONTRACT_NO = LNCSERRP.CTA;
        LNRCTPY.KEY.TR_TYP = WS_CTPY_TR_TYP;
        T000_READ_LNTCTPY1();
        if (pgmRtn) return;
        T000_READ_UPDATE_LNTCTPY();
        if (pgmRtn) return;
        WS_O_I_AMT = LNRCTPY.I_REC_AMT - LNRCTPY.I_PAY_AMT;
        if (WS_O_I_AMT < 0) {
            WS_O_I_AMT = 0;
        }
        if (WS_I_LEFT_AMT < 0) {
            WS_I_LEFT_AMT = 0;
        }
        if (WS_I_LEFT_AMT >= WS_O_I_AMT) {
            WS_PAY_R_I_AMT = WS_O_I_AMT;
        } else {
            WS_PAY_R_I_AMT = WS_I_LEFT_AMT;
        }
        LNRCTPY.I_PAY_AMT += WS_PAY_R_I_AMT;
        T000_REWRITE_LNTCTPY();
        if (pgmRtn) return;
    }
    public void B160_SOLD_OWN_US_I_PROC() throws IOException,SQLException,Exception {
        WS_CTPY_TR_TYP = '1';
        B064_UPDATE_LNTCTPY_PROC();
        if (pgmRtn) return;
        WS_PAY_I_AMT = WS_PAY_R_I_AMT;
        WS_I_FLG = 'Y';
        WS_CTPY_I_AMT = WS_PAY_R_I_AMT;
        WS_I_AMT1 = WS_PAY_R_I_AMT;
        if (LNRCTPY.I_PAY_AMT >= LNRCTPY.I_REC_AMT) {
            WS_UPD_D44_FLG = 'Y';
        }
    }
    public void B063_PURCHASE_BACK_I_PROC() throws IOException,SQLException,Exception {
        WS_I_LEFT_AMT = LNCSERRP.TOT_I_AMT;
        if (WS_INFO.WS_CTL_STSW == null) WS_INFO.WS_CTL_STSW = "";
        JIBS_tmp_int = WS_INFO.WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_INFO.WS_CTL_STSW += " ";
        if (WS_INFO.WS_CTL_STSW.substring(44 - 1, 44 + 1 - 1).equalsIgnoreCase("1") 
            && WS_I_LEFT_AMT > 0) {
            B160_SOLD_OWN_US_I_PROC();
            if (pgmRtn) return;
            WS_I_LEFT_AMT -= WS_PAY_R_I_AMT;
        }
        if (WS_INFO.WS_CTL_STSW == null) WS_INFO.WS_CTL_STSW = "";
        JIBS_tmp_int = WS_INFO.WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_INFO.WS_CTL_STSW += " ";
        if (WS_INFO.WS_CTL_STSW.substring(45 - 1, 45 + 1 - 1).equalsIgnoreCase("1") 
            && WS_I_LEFT_AMT > 0) {
            B161_PURBACK_OWN_OUT_I_PROC();
            if (pgmRtn) return;
            WS_I_LEFT_AMT -= WS_PAY_R_I_AMT;
        }
        if (WS_INFO.WS_CTL_STSW == null) WS_INFO.WS_CTL_STSW = "";
        JIBS_tmp_int = WS_INFO.WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_INFO.WS_CTL_STSW += " ";
        if (WS_INFO.WS_CTL_STSW == null) WS_INFO.WS_CTL_STSW = "";
        JIBS_tmp_int = WS_INFO.WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_INFO.WS_CTL_STSW += " ";
        if ((WS_INFO.WS_CTL_STSW.substring(44 - 1, 44 + 1 - 1).equalsIgnoreCase("1") 
            || WS_INFO.WS_CTL_STSW.substring(45 - 1, 45 + 1 - 1).equalsIgnoreCase("1")) 
            && WS_I_LEFT_AMT > 0) {
            WS_I_FLG = 'Y';
            WS_CTPY_I_AMT += WS_PAY_R_I_AMT;
            WS_I_AMT2 = WS_CTPY_I_AMT;
        }
    }
    public void B067_WRITE_LNTTRAN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCTRANM);
        LNCTRANM.FUNC = '0';
        LNCTRANM.REC_DATA.KEY.CONTRACT_NO = LNCSERRP.CTA;
        LNCTRANM.REC_DATA.KEY.SUB_CTA_NO = 0;
        LNCTRANM.REC_DATA.KEY.REC_FLAG = WS_REC_FLG;
        LNCTRANM.REC_DATA.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCTRANM.REC_DATA.KEY.TR_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        LNCTRANM.REC_DATA.KEY.TXN_TYP = 'I';
        LNCTRANM.REC_DATA.KEY.TXN_TERM = 0;
        LNCTRANM.REC_DATA.KEY.TRAN_FLG = 'C';
        LNCTRANM.REC_DATA.TRAN_STS = 'N';
        LNCTRANM.REC_DATA.TR_CODE = SCCGWA.COMM_AREA.TR_ID.TR_CODE;
        LNCTRANM.REC_DATA.TR_VAL_DTE = SCCGWA.COMM_AREA.AC_DATE;
        if (WS_REC_FLG == 'I') {
            LNCTRANM.REC_DATA.I_AMT = WS_CTPY_I_AMT;
            LNCTRANM.REC_DATA.PAY_AMT1 = WS_I_AMT1;
            LNCTRANM.REC_DATA.PAY_AMT2 = WS_I_AMT2;
        }
        if (WS_REC_FLG == 'O') {
            LNCTRANM.REC_DATA.I_AMT = WS_CTPY_O_AMT;
            LNCTRANM.REC_DATA.PAY_AMT1 = WS_O_AMT1;
            LNCTRANM.REC_DATA.PAY_AMT2 = WS_O_AMT2;
        }
        LNCTRANM.REC_DATA.TR_SEQ = LNCCLNQ.DATA.LAST_TX_SEQ;
        LNCTRANM.REC_DATA.TR_MMO = LNCSERRP.TR_MMO;
        S000_CALL_LNZTRANM();
        if (pgmRtn) return;
    }
    public void B460_SOLD_OUT_LOAN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRAGRE);
        LNRAGRE.KEY.CONTRACT_NO = LNCSERRP.CTA;
        T000_READ_LNTAGRE();
        if (pgmRtn) return;
        WS_INFO.WS_CTA_NO = LNRAGRE.REL_CTA_NO;
        B471_FEN_AMT();
        if (pgmRtn) return;
        if (WS_TR_TOT_AMT != 0) {
            B461_GET_SOLD_OUT_SETL_INF();
            if (pgmRtn) return;
            B462_SOLD_OUT_SETL_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void B461_GET_SOLD_OUT_SETL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRSETL);
        IBS.init(SCCGWA, LNCRSETL);
        LNCRSETL.FUNC = 'I';
        LNRSETL.KEY.CONTRACT_NO = LNCSERRP.CTA;
        LNRSETL.KEY.CCY = LNCSERRP.ACAMT[1-1].REC_CCY2;
        LNRSETL.KEY.CI_TYPE = 'T';
        LNRSETL.KEY.SETTLE_TYPE = 'D';
        LNCRSETL.REC_PTR = LNRSETL;
        LNCRSETL.REC_LEN = 266;
        S000_CALL_LNZRSETL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNRSETL.AC);
        WS_TX_INFO.WS_TX_AC_TYP = LNRSETL.AC_TYP;
        WS_SETL_AC_INF.WS_CTPY_AC = LNRSETL.AC;
    }
    public void B462_SOLD_OUT_SETL_PROCESS() throws IOException,SQLException,Exception {
        WS_TX_INFO.WS_TX_AC = WS_SETL_AC_INF.WS_CTPY_AC;
        WS_TX_INFO.WS_TX_AMT = WS_TR_TOT_AMT;
        B095_TX_AMT_PROC();
        if (pgmRtn) return;
    }
    public void B470_SOLD_OUT_LOAN_PROC() throws IOException,SQLException,Exception {
        WS_INFO.WS_CTA_NO = LNCSERRP.CTA;
        B470_FEN_AMT();
        if (pgmRtn) return;
        if (WS_LEFT_AMT != 0) {
            B473_GET_SOLD_OUT_SETL_INF();
            if (pgmRtn) return;
            B474_AMT_SETL_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void B473_GET_SOLD_OUT_SETL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRSETL);
        IBS.init(SCCGWA, LNCRSETL);
        LNCRSETL.FUNC = 'I';
        LNRSETL.KEY.CONTRACT_NO = LNCSERRP.CTA;
        LNRSETL.KEY.CCY = LNCSERRP.ACAMT[1-1].REC_CCY2;
        LNRSETL.KEY.CI_TYPE = 'E';
        LNRSETL.KEY.SETTLE_TYPE = '6';
        LNCRSETL.REC_PTR = LNRSETL;
        LNCRSETL.REC_LEN = 266;
        S000_CALL_LNZRSETL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNRSETL.AC);
        WS_TX_INFO.WS_TX_AC_TYP = LNRSETL.AC_TYP;
        WS_SETL_AC_INF.WS_CTPY_AC = LNRSETL.AC;
    }
    public void B474_AMT_SETL_PROCESS() throws IOException,SQLException,Exception {
        WS_TX_INFO.WS_TX_AC = WS_SETL_AC_INF.WS_CTPY_AC;
        WS_TX_INFO.WS_TX_AMT = WS_LEFT_AMT;
        B095_TX_AMT_PROC();
        if (pgmRtn) return;
    }
    public void B471_GET_SOLD_OUT_SETL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPLDT);
        LNRPLDT.KEY.CONTRACT_NO = LNCSERRP.CTA;
        T000_READ_UPDATE_LNTPLDT();
        if (pgmRtn) return;
        if (WS_READ_LNTPLDT_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_E_REC_NOT_F_LNRPLDT, LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        LNRPLDT.T_PAY_AMT = LNRPLDT.T_PAY_AMT + WS_LEFT_AMT;
        LNRPLDT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRPLDT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTPLDT_RD = new DBParm();
        LNTPLDT_RD.TableName = "LNTPLDT";
        IBS.REWRITE(SCCGWA, LNRPLDT, LNTPLDT_RD);
    }
    public void B472_AMT_SETL_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCIGEAI);
        LNCIGEAI.COMM_DATA.BUS_KND = "LNPMTAC";
        LNCIGEAI.COMM_DATA.CCY = LNCSERRP.CCY;
        LNCIGEAI.COMM_DATA.AC_NO = WS_SUSP_AC;
        LNCIGEAI.COMM_DATA.SIGN = 'D';
        S000_CALL_LNZIGEAI();
        if (pgmRtn) return;
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = LNCIGEAI.COMM_DATA.AI_AC;
        AICUUPIA.DATA.EVENT_CODE = "CR";
        AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.CCY = LNCSERRP.CCY;
        AICUUPIA.DATA.AMT = WS_LEFT_AMT;
        AICUUPIA.DATA.VALUE_DATE = LNCSERRP.TR_VAL_DTE;
        AICUUPIA.DATA.RVS_NO = " ";
        S000_CALL_AIZUUPIA();
        if (pgmRtn) return;
    }
    public void B470_FEN_AMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCTPY);
        LNRCTPY.KEY.CONTRACT_NO = WS_INFO.WS_CTA_NO;
        T000_READ_UPDATE_LNTCTPY();
        if (pgmRtn) return;
        WS_DIFF_I_AMT = LNRCTPY.I_REC_AMT - LNRCTPY.I_PAY_AMT;
        WS_DIFF_O_AMT = LNRCTPY.O_REC_AMT - LNRCTPY.O_PAY_AMT;
        WS_DIFF_L_AMT = LNRCTPY.L_REC_AMT - LNRCTPY.L_PAY_AMT;
        CEP.TRC(SCCGWA, WS_DIFF_I_AMT);
        CEP.TRC(SCCGWA, WS_DIFF_O_AMT);
        CEP.TRC(SCCGWA, WS_DIFF_L_AMT);
        WS_LEFT_TOT_AMT = LNCSERRP.TOT_AMT - LNCSERRP.PC_AMT;
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
    public void B471_FEN_AMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCTPY);
        LNRCTPY.KEY.CONTRACT_NO = WS_INFO.WS_CTA_NO;
        T000_READ_UPDATE_LNTCTPY();
        if (pgmRtn) return;
        WS_DIFF_I_AMT = LNRCTPY.I_REC_AMT - LNRCTPY.I_PAY_AMT;
        CEP.TRC(SCCGWA, WS_DIFF_I_AMT);
        if (WS_DIFF_I_AMT < LNCSERRP.TOT_I_AMT) {
            WS_PAY_I_AMT = WS_DIFF_I_AMT;
        } else {
            WS_PAY_I_AMT = LNCSERRP.TOT_I_AMT;
        }
        LNRCTPY.I_PAY_AMT += WS_PAY_I_AMT;
        CEP.TRC(SCCGWA, WS_PAY_I_AMT);
        T000_REWRITE_LNTCTPY();
        if (pgmRtn) return;
    }
    public void B095_SUB_PRC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRELA);
        LNRRELA.KEY.CONTRACT_NO = LNCSERRP.CTA;
        LNRRELA.KEY.TYPE = 'S';
        T000_READ_LNTRELA();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNRSUBS);
        LNRSUBS.KEY.PROJ_NO = LNRRELA.PROJ_NO;
        T000_READ_LNTSUBS();
        if (pgmRtn) return;
        if (LNCSERRP.PROJ_INT > 0) {
            if (LNRSUBS.IA_AC.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_SUBS_IA_AC_EMPTY, LNCSERRP.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.DATA.AGR_NO = LNRSUBS.IA_AC;
            CICQACRI.FUNC = 'A';
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            WS_BBR_INFO[WS_IDX-1].WS_ACR_BBR = CICQACRI.O_DATA.O_OWNER_BK;
            WS_IDX += 1;
            IBS.init(SCCGWA, AICUUPIA);
            AICUUPIA.DATA.AC_NO = LNRSUBS.IA_AC;
            AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            AICUUPIA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            AICUUPIA.DATA.CCY = LNCSERRP.CCY;
            AICUUPIA.DATA.AMT = LNCSERRP.PROJ_INT;
            AICUUPIA.DATA.VALUE_DATE = LNCSERRP.TR_VAL_DTE;
            AICUUPIA.DATA.EVENT_CODE = "DR";
            S000_CALL_AIZUUPIA();
            if (pgmRtn) return;
        }
    }
    public void B095_GROUPLOAN_PRC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCIPAMT);
        LNCIPAMT.CTA_NO = LNCSERRP.CTA;
        LNCIPAMT.P_AMT = LNCSERRP.TOT_P_AMT;
        LNCIPAMT.I_AMT = LNCSERRP.TOT_I_AMT;
        LNCIPAMT.O_AMT = 0;
        LNCIPAMT.L_AMT = 0;
        B321_CALL_LNZIPAMT();
        if (pgmRtn) return;
        for (WS_I = 1; WS_I <= 10 
            && LNCIPAMT.PART_DATA[WS_I-1].PART_CTA_NO.trim().length() != 0; WS_I += 1) {
            if (LNCIPAMT.PART_DATA[WS_I-1].PART_NO != 0) {
                if (LNCIPAMT.PART_DATA[WS_I-1].LOCAL_BANK_FLAG != 'Y') {
                    B099_GET_SYLOAN_SETL_INF();
                    if (pgmRtn) return;
                    B099_SYLOAN_SETL_PROCESS();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B321_CALL_LNZIPAMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-INQ-PAR-AMT", LNCIPAMT);
        if (LNCIPAMT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCIPAMT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B099_GET_SYLOAN_SETL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRSETL);
        IBS.init(SCCGWA, LNCRSETL);
        LNCRSETL.FUNC = 'I';
        LNCRSETL.REC_PTR = LNRSETL;
        LNCRSETL.REC_LEN = 266;
        LNRSETL.KEY.CONTRACT_NO = LNCIPAMT.CTA_NO;
        LNRSETL.KEY.PART_BK = "" + LNCIPAMT.PART_DATA[WS_I-1].PART_NO;
        JIBS_tmp_int = LNRSETL.KEY.PART_BK.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) LNRSETL.KEY.PART_BK = "0" + LNRSETL.KEY.PART_BK;
        LNRSETL.KEY.CI_TYPE = 'P';
        LNRSETL.KEY.CCY = LNCSERRP.CCY;
        LNRSETL.KEY.SETTLE_TYPE = 'C';
        S000_CALL_LNZRSETL();
        if (pgmRtn) return;
        WS_SYLOAN_AC_TYP = LNRSETL.AC_TYP;
        WS_SYLOAN_AC = LNRSETL.AC;
    }
    public void B099_SYLOAN_SETL_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_TX_INFO);
        WS_TX_INFO.WS_TX_AC_TYP = WS_SYLOAN_AC_TYP;
        WS_TX_INFO.WS_TX_AC = WS_SYLOAN_AC;
        WS_TX_INFO.WS_TX_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_P_AMT + LNCIPAMT.PART_DATA[WS_I-1].PART_I_AMT;
        B095_TX_AMT_PROC();
        if (pgmRtn) return;
    }
    public void B095_TRUS_HRGTAX_PRC() throws IOException,SQLException,Exception {
        B095_GET_TRUS_SETL_INF();
        if (pgmRtn) return;
        WS_RELEASE_AC = WS_SETL_AC_INF.WS_TRUS_W_AC;
        if (!LNCSERRP.ACAMT[WS_X-1].STL_MTH2.equalsIgnoreCase("08")) {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.DATA.AGR_NO = WS_SETL_AC_INF.WS_TRUS_W_AC;
            CICQACRI.FUNC = 'A';
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            WS_BBR_INFO[WS_IDX-1].WS_ACR_BBR = CICQACRI.O_DATA.O_OWNER_BK;
            WS_IDX += 1;
        }
        B095_TRUS_SETL_PROCESS();
        if (pgmRtn) return;
    }
    public void B095_GET_TRUS_SETL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRSETL);
        IBS.init(SCCGWA, LNCRSETL);
        LNCRSETL.FUNC = 'I';
        LNCRSETL.REC_PTR = LNRSETL;
        LNCRSETL.REC_LEN = 266;
        LNRSETL.KEY.CONTRACT_NO = LNCSERRP.CTA;
        LNRSETL.KEY.CI_TYPE = 'C';
        LNRSETL.KEY.CCY = LNCSERRP.CCY;
        LNRSETL.KEY.SETTLE_TYPE = '4';
        S000_CALL_LNZRSETL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNRSETL.AC_TYP);
        CEP.TRC(SCCGWA, LNRSETL.AC);
        WS_SETL_AC_INF.WS_TRUS_W_AC_TYP = LNRSETL.AC_TYP;
        WS_SETL_AC_INF.WS_TRUS_W_AC = LNRSETL.AC;
    }
    public void B095_TRUS_P_SETL_PROCESS() throws IOException,SQLException,Exception {
        WS_TX_INFO.WS_TX_AC_TYP = WS_SETL_AC_INF.WS_TRUS_P_AC_TYP;
        WS_TX_INFO.WS_TX_AC = WS_SETL_AC_INF.WS_TRUS_P_AC;
        WS_TX_INFO.WS_TX_AMT = LNCSERRP.TOT_P_AMT;
        B095_TX_AMT_PROC();
        if (pgmRtn) return;
    }
    public void B095_TRUS_SETL_PROCESS() throws IOException,SQLException,Exception {
        WS_TX_INFO.WS_TX_AC_TYP = WS_SETL_AC_INF.WS_TRUS_W_AC_TYP;
        WS_TX_INFO.WS_TX_AC = WS_SETL_AC_INF.WS_TRUS_W_AC;
        WS_TX_INFO.WS_TX_AMT = LNCSERRP.TOT_AMT;
        B095_TX_AMT_PROC();
        if (pgmRtn) return;
    }
    public void B095_TRUS_I_SETL_PROCESS() throws IOException,SQLException,Exception {
        if (!LNCSERRP.ACAMT[WS_X-1].STL_MTH2.equalsIgnoreCase("08")) {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.DATA.AGR_NO = WS_SETL_AC_INF.WS_TRUS_I_AC;
            CICQACRI.FUNC = 'A';
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            WS_BBR_INFO[WS_IDX-1].WS_ACR_BBR = CICQACRI.O_DATA.O_OWNER_BK;
            WS_IDX += 1;
        }
        IBS.init(SCCGWA, WS_TX_INFO);
        WS_TX_INFO.WS_TX_AC_TYP = WS_SETL_AC_INF.WS_TRUS_I_AC_TYP;
        WS_TX_INFO.WS_TX_AC = WS_SETL_AC_INF.WS_TRUS_I_AC;
        WS_TX_INFO.WS_TX_AMT = LNCSERRP.TOT_I_AMT;
        B095_TX_AMT_PROC();
        if (pgmRtn) return;
        if (LNCSERRP.HRG_AMT != 0) {
            B454_TRUS_FEE_CHARGE_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void B454_TRUS_FEE_CHARGE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        JIBS_NumStr = "" + 0;
        BPCFFTXI.TX_DATA.AUH_FLG = JIBS_NumStr.charAt(0);
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        if (WS_SETL_AC_INF.WS_TRUS_I_AC_TYP.equalsIgnoreCase("01")) {
            BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '0';
        } else if (WS_SETL_AC_INF.WS_TRUS_I_AC_TYP.equalsIgnoreCase("02")) {
            BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '3';
        } else if (WS_SETL_AC_INF.WS_TRUS_I_AC_TYP.equalsIgnoreCase("03")) {
            BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '2';
        } else if (WS_SETL_AC_INF.WS_TRUS_I_AC_TYP.equalsIgnoreCase("05")
            || WS_SETL_AC_INF.WS_TRUS_I_AC_TYP.equalsIgnoreCase("06")) {
            BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '4';
            BPCFFTXI.TX_DATA.CARD_PSBK_NO = WS_SETL_AC_INF.WS_TRUS_I_AC;
        } else {
        }
        WS_FEE_CODE_HRG = LNCSERRP.FEE_INFO[1-1].FEE_CD;
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = WS_SETL_AC_INF.WS_TRUS_I_AC;
        if (CICACCU.DATA.AGR_NO.trim().length() > 0 
            && (BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP == '0' 
            || BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP == '4')) {
            S000_CALL_CIZACCU();
            if (pgmRtn) return;
        }
        BPCFFTXI.TX_DATA.CI_NO = CICACCU.DATA.CI_NO;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = WS_SETL_AC_INF.WS_TRUS_I_AC;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = LNCSERRP.CCY;
        BPCFFTXI.TX_DATA.SVR_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCFFTXI.TX_DATA.FEE_CTRT = LNCSERRP.CTA;
        BPCFFTXI.TX_DATA.PROC_TYPE = '0';
        BPCFFTXI.TX_DATA.BSNS_NO = LNCSERRP.CTA;
        S000_CALL_BPZFFTXI();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCFFCON);
        BPCFFCON.FEE_INFO.FEE_CNT = 1;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_FLG = '0';
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_CODE = WS_FEE_CODE_HRG;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_BAS = LNCSERRP.HRG_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_AMT = LNCSERRP.HRG_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_AC_TY = BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_AC = WS_SETL_AC_INF.WS_TRUS_I_AC;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_CCY = LNCSERRP.CCY;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_CCY = LNCSERRP.CCY;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_BAS = LNCSERRP.HRG_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_AMT = LNCSERRP.HRG_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].ADJ_AMT = LNCSERRP.HRG_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].AMO_FLG = 'Y';
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].AMO_STDT = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].AMO_EDDT = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFCON.FEE_INFO.ACCOUNT_BR = LNCSERRP.BR;
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[1-1].AMO_FLG);
        CEP.TRC(SCCGWA, "SHISHI");
        S000_CALL_BPZFFCON();
        if (pgmRtn) return;
    }
    public void B091_SWICH_PROJ() throws IOException,SQLException,Exception {
        if (LNCCLNQ.DATA.PD_PROJ_NO != 0) {
            B095_FUND_LOAN_PROC();
            if (pgmRtn) return;
        } else {
            if (WS_INFO.WS_CTL_STSW == null) WS_INFO.WS_CTL_STSW = "";
            JIBS_tmp_int = WS_INFO.WS_CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) WS_INFO.WS_CTL_STSW += " ";
            if (WS_INFO.WS_CTL_STSW.substring(19 - 1, 19 + 1 - 1).equalsIgnoreCase("1")) {
                B095_TRUS_HRGTAX_PRC();
                if (pgmRtn) return;
            }
            if (WS_GROUPLOAN_FLG == 'Y') {
                B095_GROUPLOAN_PRC();
                if (pgmRtn) return;
            }
            if (WS_INFO.WS_CTL_STSW == null) WS_INFO.WS_CTL_STSW = "";
            JIBS_tmp_int = WS_INFO.WS_CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) WS_INFO.WS_CTL_STSW += " ";
            if (WS_INFO.WS_CTL_STSW.substring(41 - 1, 41 + 1 - 1).equalsIgnoreCase("1")) {
                B095_SUB_PRC();
                if (pgmRtn) return;
            }
        }
    }
    public void B095_FUND_LOAN_PROC() throws IOException,SQLException,Exception {
        B095_GET_FUND_SETL_INF();
        if (pgmRtn) return;
        WS_RELEASE_AC = LNRFUND.FUND_AC;
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.DATA.AGR_NO = LNRFUND.FUND_AC;
        CICQACRI.FUNC = 'A';
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        WS_BBR_INFO[WS_IDX-1].WS_ACR_BBR = CICQACRI.O_DATA.O_OWNER_BK;
        WS_IDX += 1;
        B095_FUND_AC_DR();
        if (pgmRtn) return;
        if (LNRFUND.PAY_FLG == 'Y') {
            B095_IA_P_SETL_PROCESS();
            if (pgmRtn) return;
            B095_IA_I_SETL_PROCESS();
            if (pgmRtn) return;
            B095_UPDATE_FUND_AMT();
            if (pgmRtn) return;
        } else {
            B095_FUND_P_SETL_PROCESS();
            if (pgmRtn) return;
            B095_FUND_I_SETL_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void B095_FUND_AC_DR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUDRAC);
        DDCUDRAC.CHK_PSW_FLG = 'N';
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.BANK_DR_FLG = 'Y';
        DDCUDRAC.TSTS_TABL = "0014";
        DDCUDRAC.TX_BAL_FLG = 'N';
        DDCUDRAC.AC = LNRFUND.FUND_AC;
        DDCUDRAC.TX_AMT = LNCSERRP.TOT_P_AMT;
        DDCUDRAC.CCY = LNCSERRP.CCY;
        if (LNCSERRP.CCY.equalsIgnoreCase("156")) {
            DDCUDRAC.CCY_TYPE = '1';
        } else {
            DDCUDRAC.CCY_TYPE = LNCSERRP.ACAMT[1-1].CCY_TYP;
        }
        DDCUDRAC.VAL_DATE = LNCSERRP.TR_VAL_DTE;
        DDCUDRAC.TX_MMO = LNCSERRP.TR_MMO;
        DDCUDRAC.OTHER_AC = LNCSERRP.CTA;
        CEP.TRC(SCCGWA, " ");
        CEP.TRC(SCCGWA, LNRFUND.FUND_AC);
        S000_CALL_DDZUDRAC();
        if (pgmRtn) return;
    }
    public void B095_IA_P_SETL_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICPQIA);
        AICPQIA.SIGN = 'C';
        AICPQIA.CD.BUSI_KND = "LN01";
        AICPQIA.BR = LNRFUND.BOOK_BR;
        AICPQIA.CCY = LNCSERRP.CCY;
        S000_CALL_AIZPQIA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICPQIA.AC);
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = AICPQIA.AC;
        AICUUPIA.DATA.EVENT_CODE = "CR";
        AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.CCY = LNCSERRP.CCY;
        AICUUPIA.DATA.AMT = LNCSERRP.TOT_P_AMT;
        AICUUPIA.DATA.VALUE_DATE = LNCSERRP.TR_VAL_DTE;
        AICUUPIA.DATA.RVS_NO = " ";
        if (AICUUPIA.DATA.AMT != 0) {
            S000_CALL_AIZUUPIA();
            if (pgmRtn) return;
        }
    }
    public void B095_IA_I_SETL_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICPQIA);
        AICPQIA.SIGN = 'C';
        AICPQIA.CD.BUSI_KND = "LN02";
        AICPQIA.BR = LNRFUND.BOOK_BR;
        AICPQIA.CCY = LNCSERRP.CCY;
        S000_CALL_AIZPQIA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICPQIA.AC);
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = AICPQIA.AC;
        AICUUPIA.DATA.EVENT_CODE = "CR";
        AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.CCY = LNCSERRP.CCY;
        AICUUPIA.DATA.AMT = LNCSERRP.TOT_I_AMT;
        AICUUPIA.DATA.VALUE_DATE = LNCSERRP.TR_VAL_DTE;
        AICUUPIA.DATA.RVS_NO = " ";
        if (AICUUPIA.DATA.AMT != 0) {
            S000_CALL_AIZUUPIA();
            if (pgmRtn) return;
        }
    }
    public void B095_UPDATE_FUND_AMT() throws IOException,SQLException,Exception {
        LNRFUND.TOT_PAY_P = LNRFUND.TOT_PAY_P + LNCSERRP.TOT_P_AMT;
        LNRFUND.TOT_PAY_I = LNRFUND.TOT_PAY_I + LNCSERRP.TOT_I_AMT;
        LNCRFUND.FUNC = 'U';
        S000_CALL_LNZRFUND();
        if (pgmRtn) return;
    }
    public void B095_GET_FUND_SETL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRFUND);
        LNCRFUND.FUNC = 'R';
        LNRFUND.KEY.PROJ_NO = LNCCLNQ.DATA.PD_PROJ_NO;
        S000_CALL_LNZRFUND();
        if (pgmRtn) return;
    }
    public void B095_FUND_P_SETL_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.DATA.AGR_NO = LNRFUND.PAY_P_AC;
        CICQACRI.FUNC = 'A';
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        WS_BBR_INFO[WS_IDX-1].WS_ACR_BBR = CICQACRI.O_DATA.O_OWNER_BK;
        WS_IDX += 1;
        B101_GET_AC_INFO();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "111");
        WS_SETL_AC_INF.WS_P_AC_TYP = WS_SETL_AC_INF.WS_FUND_AC_TYP;
        IBS.init(SCCGWA, WS_TX_INFO);
        WS_TX_INFO.WS_TX_AC = LNRFUND.PAY_P_AC;
        WS_TX_INFO.WS_TX_AMT = LNCSERRP.TOT_P_AMT;
        WS_TX_INFO.WS_TX_AC_TYP = WS_SETL_AC_INF.WS_P_AC_TYP;
        B095_TX_AMT_PROC();
        if (pgmRtn) return;
    }
    public void B095_FUND_I_SETL_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.DATA.AGR_NO = LNRFUND.PAY_I_AC;
        CICQACRI.FUNC = 'A';
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        WS_BBR_INFO[WS_IDX-1].WS_ACR_BBR = CICQACRI.O_DATA.O_OWNER_BK;
        WS_IDX += 1;
        B101_GET_AC_INFO();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "222");
        WS_SETL_AC_INF.WS_I_AC_TYP = WS_SETL_AC_INF.WS_FUND_AC_TYP;
        IBS.init(SCCGWA, WS_TX_INFO);
        WS_TX_INFO.WS_TX_AC = LNRFUND.PAY_I_AC;
        WS_TX_INFO.WS_TX_AMT = LNCSERRP.TOT_I_AMT;
        WS_TX_INFO.WS_TX_AC_TYP = WS_SETL_AC_INF.WS_I_AC_TYP;
        B095_TX_AMT_PROC();
        if (pgmRtn) return;
    }
    public void B095_TX_AMT_PROC() throws IOException,SQLException,Exception {
        if (WS_TX_INFO.WS_TX_AC_TYP.equalsIgnoreCase("02")) {
            B095_TX_IA_CR_PROC();
            if (pgmRtn) return;
        } else if (WS_TX_INFO.WS_TX_AC_TYP.equalsIgnoreCase("03")) {
            B095_TX_IB_CR_PROC();
            if (pgmRtn) return;
        } else {
            B095_TX_DD_CR_PROC();
            if (pgmRtn) return;
        }
    }
    public void B095_TX_IA_CR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'A';
        CICQACAC.DATA.ACAC_NO = LNCSERRP.CTA;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        AICUUPIA.DATA.AC_NO = WS_TX_INFO.WS_TX_AC;
        AICUUPIA.DATA.EVENT_CODE = "CR";
        AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.CCY = LNCSERRP.ACAMT[1-1].REC_CCY2;
        AICUUPIA.DATA.AMT = WS_TX_INFO.WS_TX_AMT;
        AICUUPIA.DATA.VALUE_DATE = LNCSERRP.TR_VAL_DTE;
        AICUUPIA.DATA.RVS_NO = " ";
        AICUUPIA.DATA.PAY_MAN = CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM;
        if (AICUUPIA.DATA.AMT != 0) {
            S000_CALL_AIZUUPIA();
            if (pgmRtn) return;
        }
    }
    public void B095_TX_IB_CR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCPOSTA);
        IBCPOSTA.NOSTRO_CD = WS_TX_INFO.WS_TX_AC;
        IBCPOSTA.CCY = LNCSERRP.ACAMT[1-1].REC_CCY2;
        IBCPOSTA.AMT = WS_TX_INFO.WS_TX_AMT;
        IBCPOSTA.TXTYPE = "01";
        IBCPOSTA.VAL_DATE = LNCSERRP.TR_VAL_DTE;
        IBCPOSTA.ENTRY_FLG = '1';
        IBCPOSTA.SIGN = 'C';
        IBCPOSTA.ACT_CTR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        IBCPOSTA.ACT_CTR = LNCSERRP.BR;
        IBCPOSTA.OUR_REF = LNCSERRP.CTA;
        IBCPOSTA.THR_REF = LNCSERRP.CTA;
        IBCPOSTA.TX_MMO = LNCSERRP.TR_MMO;
        if (IBCPOSTA.AMT != 0) {
            S000_CALL_IBZCRAC();
            if (pgmRtn) return;
        }
    }
    public void B095_TX_DD_CR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUCRAC);
        DDCUCRAC.BANK_CR_FLG = 'Y';
        DDCUCRAC.TX_TYPE = 'T';
        LNRICTL.KEY.CONTRACT_NO = LNCSERRP.CTA;
        T0000_READ_LNTICTL();
        if (pgmRtn) return;
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(14 - 1, 14 + 1 - 1).equalsIgnoreCase("1")) {
            if (WS_SYLOAN_AC_TYP.equalsIgnoreCase("05") 
                || WS_SYLOAN_AC_TYP.equalsIgnoreCase("06")) {
                DDCUCRAC.CARD_NO = WS_SYLOAN_AC;
            } else {
                DDCUCRAC.AC = WS_SYLOAN_AC;
            }
        }
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(19 - 1, 19 + 1 - 1).equalsIgnoreCase("1")) {
            if (WS_TX_INFO.WS_TX_AC_TYP.equalsIgnoreCase("05") 
                || WS_TX_INFO.WS_TX_AC_TYP.equalsIgnoreCase("06")) {
                DDCUCRAC.CARD_NO = WS_TX_INFO.WS_TX_AC;
            } else {
                DDCUCRAC.AC = WS_TX_INFO.WS_TX_AC;
            }
        }
        DDCUCRAC.CCY = LNCSERRP.ACAMT[1-1].REC_CCY2;
        if (LNCSERRP.CCY.equalsIgnoreCase("156")) {
            DDCUCRAC.CCY_TYPE = '1';
        } else {
            DDCUCRAC.CCY_TYPE = LNCSERRP.ACAMT[1-1].CCY_TYP;
        }
        DDCUCRAC.TX_AMT = WS_TX_INFO.WS_TX_AMT;
        DDCUCRAC.VAL_DATE = LNCSERRP.TR_VAL_DTE;
        DDCUCRAC.TX_MMO = LNCSERRP.TR_MMO;
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.ACAC_NO = LNCSERRP.CTA;
        CICQACAC.FUNC = 'A';
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        DDCUDRAC.RLT_AC = LNCSERRP.CTA;
        if (DDCUCRAC.TX_AMT != 0) {
            S000_CALL_DDZUCRAC();
            if (pgmRtn) return;
        }
    }
    public void B095_GET_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCQ65);
        IBS.init(SCCGWA, SCCFMT);
        LNCQ65.BR = LNCSERRP.BR;
        LNCQ65.TL_ID = SCCGWA.COMM_AREA.TL_ID;
        LNCQ65.SUP_ID = SCCGWA.COMM_AREA.SUP1_ID;
        LNCQ65.TR_DATE = LNCSERRP.TR_VAL_DTE;
        LNCQ65.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        LNCQ65.CAT_NO = LNCSERRP.CTA;
        LNCQ65.CI_NO = LNCSERRP.CI_NO;
        LNCQ65.CI_CNM = LNCSERRP.CI_CNM;
        LNCQ65.LN_TYPE = LNCSCKPD.PROD_CLS;
        CEP.TRC(SCCGWA, LNCQ65.LN_TYPE);
        LNCQ65.LN_BAL = LNCSERRP.BALANCE - LNCSERRP.TOT_P_AMT;
        LNCQ65.DUE_DATE = LNCCLNQ.DATA.DUE_DT;
        LNCQ65.PAY_DATE = LNCSERRP.TR_VAL_DTE;
        for (WS_X = 1; WS_X <= 5 
            && (LNCSERRP.ACAMT[WS_X-1].STL_MTH2.trim().length() != 0 
            || LNCSERRP.ACAMT[WS_X-1].DEP_AC2.trim().length() != 0); WS_X += 1) {
            LNCQ65.AC_ARRAY[WS_X-1].PAY_AC = LNCSERRP.ACAMT[WS_X-1].DEP_AC2;
        }
        LNCQ65.PAY_P_AMT = LNCSERRP.TOT_P_AMT;
        LNCQ65.PAY_I_AMT = LNCSERRP.TOT_I_AMT;
        LNCQ65.PAY_PC_AMT = LNCSERRP.PC_AMT;
        LNCQ65.PAY_H_AMT = LNCSERRP.HRG_AMT;
        LNCQ65.PROJ_INT = LNCSERRP.PROJ_INT;
        LNCQ65.PAY_T_AMT = LNCSERRP.TOT_AMT;
        LNCQ65.S_TERM = LNCSERRP.S_TERM;
        CEP.TRC(SCCGWA, LNCQ65.S_TERM);
        LNCQ65.RAT_MTH = LNCSERRP.RAT_MTH;
        LNCQ65.INT_FLPERD_UN = LNCSERRP.INT_F_UN;
        LNCQ65.INT_FLPERD = LNCSERRP.INT_F_PD;
        LNCQ65.FLT_IPER = LNCSERRP.FLT_IPER;
        LNCQ65.FLT_ADJF = LNCSERRP.FLT_ADJF;
        LNCQ65.FLOAT_FLG = LNCSERRP.FT_FLG;
        LNCQ65.FLOAT_DAY = LNCSERRP.FT_DAY;
        LNCQ65.FLT_FIX_DAYS = LNCSERRP.FT_F_DAY;
        LNCQ65.FLOAT_MTH = LNCSERRP.FLT_MTH;
        LNCQ65.RATE_TYP1 = LNCSERRP.RATE_TYP;
        LNCQ65.RATE_PERD1 = LNCSERRP.RATE_PD;
        LNCQ65.RATE_VAR1 = LNCSERRP.RATE_VAR;
        LNCQ65.RATE_PCT1 = LNCSERRP.RATE_PCT;
        LNCQ65.RATE_INT1 = LNCSERRP.RATE_INT;
        LNCQ65.IN_RATE = LNCSERRP.INT_RATE;
        LNCQ65.PEN_RRAT = LNCSERRP.PEN_RRAT;
        LNCQ65.PEN_TYP = LNCSERRP.PEN_TYP;
        LNCQ65.PEN_RATT = LNCSERRP.PEN_RATT;
        LNCQ65.PEN_RATC = LNCSERRP.PEN_RATC;
        LNCQ65.PEN_SPR = LNCSERRP.PEN_SPR;
        LNCQ65.PEN_PCT = LNCSERRP.PEN_PCT;
        LNCQ65.PEN_IRAT = LNCSERRP.PEN_IRAT;
        LNCQ65.CPND_USE = LNCSERRP.CPND_USE;
        LNCQ65.INT_MTH = LNCSERRP.INT_MTH;
        LNCQ65.CPND_RTY = LNCSERRP.CPND_RTY;
        LNCQ65.CPND_TYP = LNCSERRP.CPND_TYP;
        LNCQ65.CPNDRATT = LNCSERRP.CPNDRATT;
        LNCQ65.CPNDRATC = LNCSERRP.CPNDRATC;
        LNCQ65.CPND_SPR = LNCSERRP.CPND_SPR;
        LNCQ65.CPND_PCT = LNCSERRP.CPND_PCT;
        LNCQ65.OLC_PERU = LNCSERRP.CPNDRATE;
        LNCQ65.ABUS_RAT = LNCSERRP.ABUS_RAT;
        LNCQ65.ABUSRATT = LNCSERRP.ABUSRATT.charAt(0);
        LNCQ65.ABUS_TYP = "" + LNCSERRP.ABUS_TYP;
        JIBS_tmp_int = LNCQ65.ABUS_TYP.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) LNCQ65.ABUS_TYP = "0" + LNCQ65.ABUS_TYP;
        LNCQ65.ABUSRATC = LNCSERRP.ABUSRATC;
        LNCQ65.ABUSSPR = LNCSERRP.ABUSSPR;
        LNCQ65.ABUSPCT = LNCSERRP.ABUSPCT;
        LNCQ65.ABUSIRAT = LNCSERRP.ABUSIRAT;
        LNCQ65.CHK_NO = LNCSERRP.RPSP_NO;
        LNCQ65.METH = LNCSERRP.METH;
        for (WS_X = 1; WS_X <= 10 
            && (LNCSERRP.SYLOAN_INFO[WS_X-1].SYLOAN_SEQ.trim().length() != 0 
            || LNCSERRP.SYLOAN_INFO[WS_X-1].SYLOAN_BR != 0); WS_X += 1) {
            if (LNCSERRP.SYLOAN_INFO[WS_X-1].SYLOAN_SEQ.trim().length() == 0) LNCQ65.JOIN_INFO[WS_X-1].SYLOAN_SEQ = 0;
            else LNCQ65.JOIN_INFO[WS_X-1].SYLOAN_SEQ = Integer.parseInt(LNCSERRP.SYLOAN_INFO[WS_X-1].SYLOAN_SEQ);
            LNCQ65.JOIN_INFO[WS_X-1].SYLOAN_BR = LNCSERRP.SYLOAN_INFO[WS_X-1].SYLOAN_BR;
            LNCQ65.JOIN_INFO[WS_X-1].SYLOAN_TYP = LNCSERRP.SYLOAN_INFO[WS_X-1].SYLOAN_TYP;
            LNCQ65.JOIN_INFO[WS_X-1].SYLOAN_FLG = LNCSERRP.SYLOAN_INFO[WS_X-1].SYLOAN_FLG;
            LNCQ65.JOIN_INFO[WS_X-1].SYLOAN_P = LNCSERRP.SYLOAN_INFO[WS_X-1].SYLOAN_P;
            LNCQ65.JOIN_INFO[WS_X-1].SYLOAN_I = LNCSERRP.SYLOAN_INFO[WS_X-1].SYLOAN_I;
            LNCQ65.JOIN_INFO[WS_X-1].SYLOAN_W = LNCSERRP.SYLOAN_INFO[WS_X-1].SYLOAN_W;
        }
        CEP.TRC(SCCGWA, LNCQ65);
        SCCFMT.FMTID = "LN410";
        SCCFMT.DATA_PTR = LNCQ65;
        SCCFMT.DATA_LEN = 2766;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B096_GENERATE_HIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRAGRE);
        LNRAGRE.KEY.CONTRACT_NO = LNCSERRP.CTA;
        T000_READ_LNTAGRE();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPFHIS);
        BPCPFHIS.DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPFHIS.DATA.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        BPCPFHIS.DATA.REF_NO = LNCSERRP.CTA;
        BPCPFHIS.DATA.TX_DRCR_FLG = 'C';
        BPCPFHIS.DATA.ACO_AC = LNCSERRP.CTA;
        CEP.TRC(SCCGWA, WS_UDRAC_ACAC);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.ACO_AC);
        BPCPFHIS.DATA.AC = LNRAGRE.PAPER_NO;
        IBS.init(SCCGWA, LNCRCONT);
        IBS.init(SCCGWA, LNRCONT);
        LNCRCONT.FUNC = 'I';
        LNRCONT.KEY.CONTRACT_NO = LNCSERRP.CTA;
        S000_CALL_LNZRCONT();
        if (pgmRtn) return;
        BPCPFHIS.DATA.TX_CCY = LNRCONT.CCY;
        if (WS_INFO.WS_CTL_STSW == null) WS_INFO.WS_CTL_STSW = "";
        JIBS_tmp_int = WS_INFO.WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_INFO.WS_CTL_STSW += " ";
        if (WS_INFO.WS_CTL_STSW.substring(54 - 1, 54 + 1 - 1).equalsIgnoreCase("1") 
            && LNCSERRP.TAX_AMT > 0) {
            BPCPFHIS.DATA.TX_AMT = LNCSERRP.TOT_AMT - LNCSERRP.TAX_AMT;
        } else {
            BPCPFHIS.DATA.TX_AMT = LNCSERRP.TOT_AMT;
        }
        BPCPFHIS.DATA.CI_NO = LNCSERRP.CI_NO;
        BPCPFHIS.DATA.PROD_CD = LNCSERRP.PROD_CD;
        BPCPFHIS.DATA.OTH_AC = LNCSERRP.ACAMT[1-1].DEP_AC2;
        BPCPFHIS.DATA.RLT_AC = LNCSERRP.ACAMT[1-1].DEP_AC2;
        if (LNCSERRP.ACAMT[1-1].STL_MTH2.equalsIgnoreCase("05") 
            || LNCSERRP.ACAMT[1-1].STL_MTH2.equalsIgnoreCase("06")) {
            BPCPFHIS.DATA.TX_TOOL = LNCSERRP.ACAMT[1-1].DEP_AC2;
        }
        BPCPFHIS.DATA.TX_MMO = LNCSERRP.TR_MMO;
        BPCPFHIS.DATA.FMT_CODE = "LN410";
        BPCPFHIS.DATA.FMT_LEN = 2766;
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.FMT_LEN);
        BPCPFHIS.DATA.FMT_DATA = IBS.CLS2CPY(SCCGWA, LNCQ65);
        BPCPFHIS.DATA.TX_TYPE = DDCUDRAC.TX_TYPE;
        if (BPCPFHIS.DATA.TX_AMT != 0) {
            S000_CALL_BPZPFHIS();
            if (pgmRtn) return;
        }
    }
    public void B900_RLTM_INFORM_CMS() throws IOException,SQLException,Exception {
        if (LNCSERRP.TOT_P_AMT > 0) {
            IBS.init(SCCGWA, LNRAGRE);
            LNRAGRE.KEY.CONTRACT_NO = LNCSERRP.CTA;
            T000_READ_LNTAGRE();
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
            SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_CCY = LNCSERRP.CCY;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_AMT = LNCSERRP.TOT_P_AMT;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_CHNL = SCCGWA.COMM_AREA.REQ_SYSTEM;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_TYP = LNCSERRP.PROD_CD;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_INT = 999;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            if (LNCAPRDM.REC_DATA.PAY_MTH == '0') {
                SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_PAYTYP = "RPT-01";
                SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            } else if (LNCAPRDM.REC_DATA.PAY_MTH == '1') {
                SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_PAYTYP = "RPT-02";
                SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            } else if (LNCAPRDM.REC_DATA.PAY_MTH == '2') {
                SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_PAYTYP = "RPT-03";
                SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            } else if (LNCAPRDM.REC_DATA.PAY_MTH == '3') {
                SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_PAYTYP = "RPT-04";
                SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            } else if (LNCAPRDM.REC_DATA.PAY_MTH == '4') {
                if (LNCAPRDM.REC_DATA.INST_MTH == '1') {
                    SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_PAYTYP = "RPT-05";
                    SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
                } else {
                    SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_PAYTYP = "RPT-06";
                    SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
                }
            } else if (LNCAPRDM.REC_DATA.PAY_MTH == '5') {
                SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_PAYTYP = "RPT-07";
                SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            }
            SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_FAMT1 = 0;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_FAMT2 = 0;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            CEP.TRC(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_FAMT1);
            CEP.TRC(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_FAMT2);
            SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_YRATE = LNCCLNQ.DATA.CUR_RAT;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_PAYDT = SCCGWA.COMM_AREA.AC_DATE;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_AC = LNCSERRP.ACAMT[1-1].DEP_AC2;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.DATA.AGR_NO = LNRAGRE.PAPER_NO;
            CEP.TRC(SCCGWA, CICQACRI.DATA.AGR_NO);
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
    public void B200_DELETE_PLDR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRICTL);
        LNRICTL.KEY.CONTRACT_NO = LNCSERRP.CTA;
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
    public void R000_CHECK_DUE_AMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCNEX);
        LNCCNEX.COMM_DATA.INQ_CODE = "INQDAYI";
        LNCCNEX.COMM_DATA.LN_AC = LNCSERRP.CTA;
        LNCCNEX.COMM_DATA.SUF_NO = "" + 0;
        JIBS_tmp_int = LNCCNEX.COMM_DATA.SUF_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCCNEX.COMM_DATA.SUF_NO = "0" + LNCCNEX.COMM_DATA.SUF_NO;
        S000_CALL_LNZCNEX();
        if (pgmRtn) return;
        if (LNCCNEX.COMM_DATA.INQ_AMT != 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_HAVE_DUE_AMT, LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCCNEX);
        LNCCNEX.COMM_DATA.INQ_CODE = "INQDAYP";
        LNCCNEX.COMM_DATA.LN_AC = LNCSERRP.CTA;
        LNCCNEX.COMM_DATA.SUF_NO = "" + 0;
        JIBS_tmp_int = LNCCNEX.COMM_DATA.SUF_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCCNEX.COMM_DATA.SUF_NO = "0" + LNCCNEX.COMM_DATA.SUF_NO;
        S000_CALL_LNZCNEX();
        if (pgmRtn) return;
        if (LNCCNEX.COMM_DATA.INQ_AMT != 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_HAVE_DUE_AMT, LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_BASIS_INFO() throws IOException,SQLException,Exception {
        if (LNCSERRP.CTA.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_LN_AC_NOT_INPUT, LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            R000_CHECK_CTA_NO_VALID();
            if (pgmRtn) return;
        }
        if (LNCSERRP.TR_VAL_DTE == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_TR_VAL_DATE_EMPTY, LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            if (LNCSERRP.TR_VAL_DTE > SCCGWA.COMM_AREA.AC_DATE) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_GT_DATE, LNCSERRP.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_CHECK_CTA_NO_VALID() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCLNQ);
        LNCCLNQ.DATA.CONT_NO = LNCSERRP.CTA;
        LNCCLNQ.DATA.SUB_CONT_NO = "" + 0;
        JIBS_tmp_int = LNCCLNQ.DATA.SUB_CONT_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCCLNQ.DATA.SUB_CONT_NO = "0" + LNCCLNQ.DATA.SUB_CONT_NO;
        LNCCLNQ.FUNC = '3';
        S000_CALL_LNZICLNQ();
        if (pgmRtn) return;
        LNCSERRP.BR = LNCCLNQ.DATA.DOMI_BR;
        LNCSERRP.CI_NO = LNCCLNQ.DATA.CI_NO;
        LNCSERRP.CI_CNM = LNCCLNQ.DATA.CI_CNAME;
        LNCSERRP.PROD_CD = LNCCLNQ.DATA.PROD_CD;
        LNCSERRP.CCY = LNCCLNQ.DATA.CCY;
        LNCSERRP.BALANCE = LNCCLNQ.DATA.TOT_BAL;
        LNCSERRP.PRINCIPAL = LNCCLNQ.DATA.PRIN;
        WS_INFO.WS_CTL_STSW = LNCCLNQ.DATA.CTL_STSW;
        IBS.init(SCCGWA, LNRAGRE);
        LNRAGRE.KEY.CONTRACT_NO = LNCSERRP.CTA;
        if (LNRAGRE.KEY.CONTRACT_NO.trim().length() > 0) {
            T000_READ_LNTAGRE();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNRAGRE.VRTU_FLG);
        if (LNRAGRE.VRTU_FLG != 'Y') {
            WS_INFO.WS_FATHER_CONTRACT = LNCCLNQ.DATA.FATHER_CONTRACT;
        }
        IBS.init(SCCGWA, LNCSSTBL);
        LNCSSTBL.CON_NO = LNCSERRP.CTA;
        LNCSSTBL.TR_CODE = "" + SCCGWA.COMM_AREA.TR_ID.TR_CODE;
        JIBS_tmp_int = LNCSSTBL.TR_CODE.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) LNCSSTBL.TR_CODE = "0" + LNCSSTBL.TR_CODE;
        if (SCCGWA.COMM_AREA.TR_ID.TR_CODE == 0132230) {
            S000_CALL_LNZSSTBL();
            if (pgmRtn) return;
        }
        R000_GET_LN_INFO();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCRCONT);
        IBS.init(SCCGWA, LNRCONT);
        LNCRCONT.FUNC = 'I';
        LNRCONT.KEY.CONTRACT_NO = LNCSERRP.CTA;
        S000_CALL_LNZRCONT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCSCKPD);
        LNCSCKPD.FUNC = K_CKPD_INQ;
        LNCSCKPD.PROD_CD = LNRCONT.PROD_CD;
        S000_CALL_LNZSCKPD();
        if (pgmRtn) return;
        WS_INT_MODE_FLAG = LNCSERRP.INT_MODE;
        if ((WS_INT_MODE_FLAG != '0' 
            && WS_INT_MODE_FLAG != '1' 
            && WS_INT_MODE_FLAG != '2')) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_G_INT_MODE, LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_CTA_STS() throws IOException,SQLException,Exception {
        if (LNCCLNQ.DATA.DUE_DT <= LNCSERRP.TR_VAL_DTE) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_BIG_LOAN_DUE_DT, LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (WS_INFO.WS_CTL_STSW == null) WS_INFO.WS_CTL_STSW = "";
        JIBS_tmp_int = WS_INFO.WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_INFO.WS_CTL_STSW += " ";
        if (WS_INFO.WS_CTL_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CTA_ALREADY_OVD, LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (WS_INFO.WS_CTL_STSW == null) WS_INFO.WS_CTL_STSW = "";
        JIBS_tmp_int = WS_INFO.WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_INFO.WS_CTL_STSW += " ";
        if (WS_INFO.WS_CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CTL_STS, LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (WS_INFO.WS_CTL_STSW == null) WS_INFO.WS_CTL_STSW = "";
        JIBS_tmp_int = WS_INFO.WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_INFO.WS_CTL_STSW += " ";
        if (WS_INFO.WS_CTL_STSW.substring(51 - 1, 51 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_LOAN_STS, LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (WS_INFO.WS_CTL_STSW == null) WS_INFO.WS_CTL_STSW = "";
        JIBS_tmp_int = WS_INFO.WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_INFO.WS_CTL_STSW += " ";
        if (WS_INFO.WS_CTL_STSW.substring(53 - 1, 53 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_LOAN_STS, LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCBALLM);
        LNCBALLM.FUNC = '3';
        LNCBALLM.REC_DATA.KEY.CONTRACT_NO = LNCSERRP.CTA;
        LNCBALLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        S000_CALL_LNZBALLM();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCBALLM.REC_DATA.LOAN_CONT_AREA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_LOAN_CONT_AREA);
        WS_BAL = WS_LOAN_CONT_AREA.REDEFINES88.WS_LOAN_CONT[2-1].WS_LOAN_BAL + WS_LOAN_CONT_AREA.REDEFINES88.WS_LOAN_CONT[4-1].WS_LOAN_BAL;
        if (WS_INFO.WS_CTL_STSW == null) WS_INFO.WS_CTL_STSW = "";
        JIBS_tmp_int = WS_INFO.WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_INFO.WS_CTL_STSW += " ";
        if (WS_INFO.WS_CTL_STSW.substring(54 - 1, 54 + 1 - 1).equalsIgnoreCase("1")) {
            if (WS_INT_MODE_FLAG != '1') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_XB_INT_MODE, LNCSERRP.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, WS_BAL);
            CEP.TRC(SCCGWA, LNCSERRP.TOT_P_AMT);
            if (LNCSERRP.TOT_P_AMT != WS_BAL) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_XB_PPAY, LNCSERRP.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (WS_INFO.WS_CTL_STSW == null) WS_INFO.WS_CTL_STSW = "";
        JIBS_tmp_int = WS_INFO.WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_INFO.WS_CTL_STSW += " ";
        if (WS_INFO.WS_CTL_STSW == null) WS_INFO.WS_CTL_STSW = "";
        JIBS_tmp_int = WS_INFO.WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_INFO.WS_CTL_STSW += " ";
        if (WS_INFO.WS_CTL_STSW.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1") 
            || WS_INFO.WS_CTL_STSW.substring(13 - 1, 13 + 1 - 1).equalsIgnoreCase("1")) {
            if (LNCSERRP.PC_AMT != 0) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_GROUPLOAN_PC, LNCSERRP.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            WS_GROUPLOAN_FLG = 'Y';
            CEP.TRC(SCCGWA, "YINTUAN");
        }
        if (LNCSERRP.TR_VAL_DTE < LNCCLNQ.DATA.LAST_F_VAL_DATE) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_VALDT_GTR_LSTDT, LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCSERRP.TR_VAL_DTE < LNCCLNQ.DATA.INT_CUT_DT) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_LT_INT_CUT_DT, LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCAPRDM.REC_DATA.PAY_MTH != '0' 
            && LNCSERRP.TR_VAL_DTE < LNCCLNQ.DATA.IC_CAL_VAL_DT) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CAL_VAL_DT_INVLD, LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCSERRP.TR_VAL_DTE < SCCGWA.COMM_AREA.AC_DATE) {
            if (LNCSERRP.BALANCE == LNCSERRP.TOT_P_AMT) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CAL_DUE, LNCSERRP.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (SCCGWA.COMM_AREA.TR_ID.TR_CODE == 0134000 
            || SCCGWA.COMM_AREA.TR_ID.TR_CODE == 0134010) {
            LNRRCVD.KEY.CONTRACT_NO = LNCSERRP.CTA;
            WS_STS = 'N';
            T000_STARTBR_LNTRCVD();
            if (pgmRtn) return;
            T000_READNEXT_LNTRCVD();
            if (pgmRtn) return;
            while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
                if (LNRRCVD.TERM_STS == '1') {
                    WS_STS = 'O';
                }
                T000_READNEXT_LNTRCVD();
                if (pgmRtn) return;
            }
            T000_ENDBR_LNTRCVD();
            if (pgmRtn) return;
            if (WS_STS == 'N') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_CONT_NOTO, LNCSERRP.RC);
            }
        }
    }
    public void R000_GET_LN_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCAPRDM);
        LNCAPRDM.FUNC = '3';
        LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = LNCSERRP.CTA;
        S000_CALL_LNZAPRDM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCAPRDM.REC_DATA.GDA_APRE);
        if (LNCSERRP.SYN_FLG == '3' 
            && LNCAPRDM.REC_DATA.PAY_MTH != '4') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYN_PAY, LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_PAY_AC() throws IOException,SQLException,Exception {
        if (LNCSERRP.CCY.trim().length() == 0) {
            IBS.init(SCCGWA, LNCRCONT);
            IBS.init(SCCGWA, LNRCONT);
            LNCRCONT.FUNC = 'I';
            LNRCONT.KEY.CONTRACT_NO = LNCSERRP.CTA;
            S000_CALL_LNZRCONT();
            if (pgmRtn) return;
            LNCSERRP.CCY = LNRCONT.CCY;
        }
        WS_BBR_INFO[1-1].WS_ACR_BBR = LNCSERRP.BR;
        WS_BBR_INFO[2-1].WS_ACR_BBR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        WS_IDX = 3;
        CEP.TRC(SCCGWA, LNCSERRP.MLT_STL);
        if (LNCSERRP.MLT_STL == '1') {
            WS_AC_NO = 5;
        } else {
            WS_AC_NO = 1;
        }
        CEP.TRC(SCCGWA, LNCSERRP.FEE_AC);
        if (LNCSERRP.FEE_AC.trim().length() > 0) {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.DATA.AGR_NO = LNCSERRP.FEE_AC;
            CICQACRI.FUNC = 'A';
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            WS_BBR_INFO[WS_IDX-1].WS_ACR_BBR = CICQACRI.O_DATA.O_OWNER_BK;
            WS_IDX += 1;
        }
        for (WS_X = 1; WS_X <= WS_AC_NO 
            && (LNCSERRP.ACAMT[WS_X-1].STL_MTH2.trim().length() != 0 
            || LNCSERRP.ACAMT[WS_X-1].DEP_AC2.trim().length() != 0 
            || WS_X <= 1); WS_X += 1) {
            CEP.TRC(SCCGWA, LNCSERRP.ACAMT[WS_X-1].STL_MTH2);
            CEP.TRC(SCCGWA, LNCSERRP.ACAMT[WS_X-1].DEP_AC2);
            B103_AC_ROUTER_PROC();
            if (pgmRtn) return;
            if (LNCSERRP.ACAMT[WS_X-1].STL_MTH2.equalsIgnoreCase("01")
                || LNCSERRP.ACAMT[WS_X-1].STL_MTH2.equalsIgnoreCase("02")
                || LNCSERRP.ACAMT[WS_X-1].STL_MTH2.equalsIgnoreCase("03")
                || LNCSERRP.ACAMT[WS_X-1].STL_MTH2.equalsIgnoreCase("05")
                || LNCSERRP.ACAMT[WS_X-1].STL_MTH2.equalsIgnoreCase("04")
                || LNCSERRP.ACAMT[WS_X-1].STL_MTH2.equalsIgnoreCase("06")) {
                if (LNCSERRP.ACAMT[WS_X-1].DEP_AC2.trim().length() == 0) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ZMQ_DEP_AC, LNCSERRP.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                if (LNCSERRP.ACAMT[WS_X-1].STL_MTH2.equalsIgnoreCase("02")) {
                    B103_CHECK_IA_AC();
                    if (pgmRtn) return;
                }
            } else if (LNCSERRP.ACAMT[WS_X-1].STL_MTH2.equalsIgnoreCase("07")) {
                if (LNCSERRP.ACAMT[WS_X-1].DEP_AC2.trim().length() == 0 
                    || LNCSERRP.ACAMT[WS_X-1].CHQ_NO2.trim().length() == 0 
                    || LNCSERRP.ACAMT[WS_X-1].CHQ_TYPE == ' ' 
                    || LNCSERRP.ACAMT[WS_X-1].ISS_DATE == 0) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_SETL_CHEQUE_EMPTY, LNCSERRP.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            } else if (LNCSERRP.ACAMT[WS_X-1].STL_MTH2.equalsIgnoreCase("08")) {
            } else if (LNCSERRP.ACAMT[WS_X-1].STL_MTH2.equalsIgnoreCase("09")) {
            } else {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.SET_MET_NOT_RIGHT, LNCSERRP.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (LNCSERRP.ACAMT[WS_X-1].REC_CCY2.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_LN_CCY, LNCSERRP.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                CEP.TRC(SCCGWA, LNCSERRP.ACAMT[WS_X-1].REC_CCY2);
                CEP.TRC(SCCGWA, LNCSERRP.CCY);
                if (!LNCSERRP.ACAMT[WS_X-1].REC_CCY2.equalsIgnoreCase(LNCSERRP.CCY)) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_EX_CCY, LNCSERRP.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
            WS_CCY_CHECK = LNCSERRP.ACAMT[1-1].REC_CCY2;
            if (!WS_CCY_CHECK.equalsIgnoreCase(LNCSERRP.ACAMT[WS_X-1].REC_CCY2)) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_AC_CCY, LNCSERRP.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (LNCSERRP.ACAMT[WS_X-1].AMT_FRM2 == ' ') {
                LNCSERRP.ACAMT[WS_X-1].AMT_FRM2 = '1';
            }
        }
    }
    public void B103_AC_ROUTER_PROC() throws IOException,SQLException,Exception {
        if (LNCSERRP.ACAMT[WS_X-1].AC_FLG2 == ' ') {
            LNCSERRP.ACAMT[WS_X-1].AC_FLG2 = '0';
        }
        WS_AC_FLG = LNCSERRP.ACAMT[WS_X-1].AC_FLG2;
        if (WS_AC_FLG == '0' 
            && LNCSERRP.ACAMT[WS_X-1].STL_MTH2.trim().length() == 0 
            && LNCSERRP.ACAMT[WS_X-1].DEP_AC2.trim().length() > 0) {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.DATA.AGR_NO = LNCSERRP.ACAMT[WS_X-1].DEP_AC2;
            CICQACRI.FUNC = 'A';
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.AC_TYPE);
            B101_GET_AC_INFO();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "333");
            LNCSERRP.ACAMT[WS_X-1].STL_MTH2 = WS_SETL_AC_INF.WS_FUND_AC_TYP;
            WS_BBR_INFO[WS_IDX-1].WS_ACR_BBR = CICQACRI.O_DATA.O_OWNER_BK;
            WS_IDX += 1;
        }
    }
    public void B101_GET_AC_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("IB")) {
            WS_SETL_AC_INF.WS_FUND_AC_TYP = K_IB_AC;
        } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
            WS_SETL_AC_INF.WS_FUND_AC_TYP = K_DD_AC;
        } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
            WS_SETL_AC_INF.WS_FUND_AC_TYP = K_DC_AC;
        } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
            WS_SETL_AC_INF.WS_FUND_AC_TYP = K_IA_AC;
        } else {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_AC_TYP_NOT_MATCH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
    }
    public void B103_CHECK_IA_AC() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, LNCSERRP.ACAMT[WS_X-1].DEP_AC2, WS_IA_AC);
        IBS.init(SCCGWA, AICPQITM);
        AICPQITM.INPUT_DATA.COA_FLG = "1";
        AICPQITM.INPUT_DATA.NO = WS_IA_AC.WS_IA_AC_KEMU;
        S000_CALL_AIZPQITM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICPQITM.OUTPUT_DATA.TYPE);
        if (AICPQITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("6")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_IA_AC_OUT, LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_GET_REC_AMT() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
            if (SCCBATH.PGM_NAME.equalsIgnoreCase("BSPLN37")) {
                IBS.init(SCCGWA, DDCSQBAL);
                DDCSQBAL.AC_NO = LNCSERRP.ACAMT[1-1].DEP_AC2;
                DDCSQBAL.CCY = LNCSERRP.ACAMT[1-1].REC_CCY2;
                S000_CALL_DDZSQBAL();
                if (pgmRtn) return;
                WS_AC_BAL = DDCSQBAL.AVL_BAL;
                if (WS_AC_BAL <= 0) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_AC_AMT_ZERO, LNCSERRP.RC);
                    Z_RET();
                    if (pgmRtn) return;
                } else {
                    if (WS_AC_BAL < LNCSERRP.TOT_AMT) {
                        LNCSERRP.TOT_AMT = WS_AC_BAL;
                        CEP.TRC(SCCGWA, "1");
                        CEP.TRC(SCCGWA, LNCSERRP.TOT_AMT);
                    }
                }
            }
        }
        IBS.init(SCCGWA, LNCSPREQ);
        LNCSPREQ.CTA = LNCSERRP.CTA;
        LNCSPREQ.SUB_TRAN = 'N';
        LNCSPREQ.TR_VAL_DTE = LNCSERRP.TR_VAL_DTE;
        LNCSPREQ.INT_MODE = LNCSERRP.INT_MODE;
        LNCSPREQ.PC_RATE = LNCSERRP.PC_RATE;
        LNCSPREQ.TOT_I_AMT = LNCSERRP.TOT_I_AMT;
        CEP.TRC(SCCGWA, "SHI");
        CEP.TRC(SCCGWA, LNCSERRP.TOT_AMT);
        if (LNCSERRP.PC_RATE == 0) {
            LNCSPREQ.PC_AMT = LNCSERRP.PC_AMT;
        }
        LNCSPREQ.TOT_P_AMT = LNCSERRP.TOT_P_AMT;
        LNCSPREQ.TOT_AMT = LNCSERRP.TOT_AMT;
        LNCSPREQ.FUN_CODE = 'I';
        S000_CALL_LNZSPREQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCSPREQ.TOT_AMT);
        CEP.TRC(SCCGWA, LNCSERRP.TOT_P_AMT);
        CEP.TRC(SCCGWA, LNCSPREQ.TOT_P_AMT);
        if (LNCSERRP.TOT_P_AMT != 0 
            && LNCSERRP.TOT_P_AMT != LNCSPREQ.TOT_P_AMT) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_AMT_UNMATCH, LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, " ");
        CEP.TRC(SCCGWA, LNCSERRP.TOT_I_AMT);
        CEP.TRC(SCCGWA, LNCSPREQ.TOT_I_AMT);
        if (LNCSERRP.TOT_I_AMT != 0 
            && LNCSERRP.TOT_I_AMT != LNCSPREQ.TOT_I_AMT) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_AMT_UNMATCH, LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCAPRDM.REC_DATA.PAY_MTH == '0') {
            if (LNCSERRP.PC_AMT != 0 
                && LNCSERRP.TOT_AMT != 0) {
                LNCSERRP.TOT_AMT -= LNCSERRP.PC_AMT;
                LNCSERRP.PC_RATE = 0;
                LNCSERRP.PC_AMT = 0;
            } else {
                LNCSERRP.PC_RATE = 0;
                LNCSERRP.PC_AMT = 0;
            }
        }
        if (LNCSERRP.TOT_AMT != 0 
            && LNCSERRP.TOT_AMT != LNCSPREQ.TOT_AMT) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_AMT_UNMATCH, LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCSERRP.HRG_AMT != 0 
            && LNCSERRP.HRG_AMT != LNCSPREQ.HRG_AMT) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_AMT_UNMATCH, LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        LNCSERRP.TOT_P_AMT = LNCSPREQ.TOT_P_AMT;
        LNCSERRP.TOT_I_AMT = LNCSPREQ.TOT_I_AMT;
        LNCSERRP.PC_AMT = LNCSPREQ.PC_AMT;
        LNCSERRP.TOT_AMT = LNCSPREQ.TOT_AMT;
        LNCSERRP.HRG_AMT = LNCSPREQ.HRG_AMT;
        LNCSERRP.PROJ_INT = LNCSPREQ.PROJ_INT;
        LNCSERRP.TAX_AMT = LNCSPREQ.TAX_AMT;
        CEP.TRC(SCCGWA, LNCSERRP.TOT_P_AMT);
        CEP.TRC(SCCGWA, LNCSERRP.TOT_I_AMT);
        CEP.TRC(SCCGWA, LNCSERRP.PC_AMT);
        CEP.TRC(SCCGWA, LNCSERRP.TOT_AMT);
        CEP.TRC(SCCGWA, LNCSERRP.HRG_AMT);
        CEP.TRC(SCCGWA, LNCSERRP.PROJ_INT);
        CEP.TRC(SCCGWA, LNCSERRP.TAX_AMT);
        if (LNCSERRP.TOT_AMT == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PAY_TOT_P_ZERO, LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_TOT_AMT = LNCSERRP.TOT_P_AMT + LNCSERRP.TOT_I_AMT + LNCSERRP.PC_AMT + LNCSERRP.TAX_AMT;
        CEP.TRC(SCCGWA, WS_TOT_AMT);
        if (WS_TOT_AMT != LNCSERRP.TOT_AMT) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.AMT_NOT_EQUAL, LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_CHNL() throws IOException,SQLException,Exception {
        if (LNCSERRP.TOT_P_AMT != 0) {
            if (LNCAPRDM.REC_DATA.PAY_MTH == '0' 
                || LNCAPRDM.REC_DATA.PAY_MTH == '1' 
                || LNCAPRDM.REC_DATA.PAY_MTH == '2' 
                || LNCSERRP.TOT_P_AMT == LNCSERRP.BALANCE) {
                LNCSERRP.SYN_FLG = '1';
            } else {
                if (LNCSERRP.SYN_FLG == ' ') {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_ADJE_FLG, LNCSERRP.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void R000_CHECK_EXTN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNREXTN);
        LNREXTN.KEY.CONTRACT_NO = LNCSERRP.CTA;
        LNREXTN.KEY.VAL_DT = LNCSERRP.TR_VAL_DTE;
        LNREXTN.KEY.STATUS = '1';
        T000_READ_LNTEXTN();
        if (pgmRtn) return;
        if (WS_EXTN_FLAG == 'Y' 
            && LNREXTN.KEY.STATUS == '1') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_YUAN_QI;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
        } else {
            IBS.init(SCCGWA, LNRPPRP);
            LNRPPRP.KEY.VAL_DT = LNCSERRP.TR_VAL_DTE;
            LNRPPRP.KEY.CONTRACT_NO = LNCSERRP.CTA;
            T000_READ_LNTPPRP();
            if (pgmRtn) return;
            if (WS_PPRP_FLAG == 'Y' 
                && LNRPPRP.STATUS == '1') {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_YUAN_QI;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_CHECK_CMMT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_INFO.WS_FATHER_CONTRACT);
        if (WS_INFO.WS_FATHER_CONTRACT.trim().length() > 0 
            && !SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
            IBS.init(SCCGWA, LNRCONT);
            LNRCONT.FATHER_CONTRACT = WS_INFO.WS_FATHER_CONTRACT;
            B200_STARTBR_LNTCONT();
            if (pgmRtn) return;
            WS_CONT_FLAG = 'N';
            B200_READNEXT_LNTCONT();
            if (pgmRtn) return;
            WS_CONT_FLAG = 'N';
            WS_OVERDUE_FLAG = 'N';
            while (WS_CONT_FLAG != 'Y' 
                && WS_OVERDUE_FLAG != 'Y') {
                IBS.init(SCCGWA, LNRICTL);
                LNRICTL.KEY.CONTRACT_NO = LNRCONT.KEY.CONTRACT_NO;
                T000_READ_LNTICTL();
                if (pgmRtn) return;
                if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
                JIBS_tmp_int = LNRICTL.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
                if (LNRICTL.CTL_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_OVERDUE_FLAG = 'Y';
                    WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_CTA_OVERDUE;
                    WS_MSG_INFO = "THE OVERDUE CONTRACT IS " + LNRCONT.KEY.CONTRACT_NO;
                    CEP.ERR(SCCGWA, WS_ERR_MSG, WS_MSG_INFO);
                }
                B200_READNEXT_LNTCONT();
                if (pgmRtn) return;
            }
            B200_ENDBR_LNTCONT();
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
    public void R000_CHECK_LN_CLDL() throws IOException,SQLException,Exception {
        if (LNCCLNQ.DATA.CONT_TYPE.equalsIgnoreCase("CLDL")) {
            IBS.init(SCCGWA, LNRDISC);
            IBS.init(SCCGWA, LNCRDISC);
            LNCRDISC.FUNC = 'I';
            LNRDISC.KEY.CONTRACT_NO = LNCSERRP.CTA;
            S000_CALL_LNZRDISC();
            if (pgmRtn) return;
            if (LNRDISC.METH == '2') {
                if (LNCSERRP.TR_VAL_DTE < LNRDISC.BB_START_DT) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_VALDT_LT_CLDL_DT, LNCSERRP.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                if (LNCSERRP.TR_VAL_DTE > LNRDISC.BB_MAT_DT) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_VALDT_GT_CLDL_DT, LNCSERRP.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void R000_CHECK_PROG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCPCFTA);
        LNCPCFTA.BR_GP[1-1].BR = LNCSERRP.BR;
        LNCPCFTA.BR_GP[2-1].BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_LNZPCFTA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCPCFTA.BR_GP[1-1].BR);
        CEP.TRC(SCCGWA, LNCPCFTA.BR_GP[2-1].BR);
        CEP.TRC(SCCGWA, LNCPCFTA.BR_GP[3-1].BR);
        if (LNCPCFTA.FTA_FLG == 'Y') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_NO_TRAN_FTA_NOT_SAME;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_GET_COMMITMENT_ATTR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCUCMMT);
        LNCUCMMT.CNTR_NO = WS_INFO.WS_FATHER_CONTRACT;
        LNCUCMMT.FUNC = 'I';
        CEP.TRC(SCCGWA, WS_INFO.WS_FATHER_CONTRACT);
        if (WS_INFO.WS_FATHER_CONTRACT.trim().length() > 0) {
            S000_CALL_LNZUCMMT();
            if (pgmRtn) return;
        }
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
    public void S000_CALL_LNZPCFTA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-P-CHK-FTA-TYP", LNCPCFTA);
        if (LNCPCFTA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCPCFTA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST, true);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICCUST.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCUST.RC);
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
    public void S000_CALL_LNZUPRP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-PRE-PAY", LNCUPRP);
        if (LNCUPRP.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCUPRP.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICRCM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-I-COMMITMENT-USE", LNCICRCM);
        CEP.TRC(SCCGWA, LNCICRCM.RC.RC_CODE);
        if (LNCICRCM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCICRCM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSERRP.RC);
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
    public void S000_CALL_LNZUCMMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-U-TRANCH-MAINTAIN", LNCUCMMT);
    }
    public void S000_CALL_LNZCNEX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CONT-EXHIBIT", LNCCNEX);
        if (LNCCNEX.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCNEX.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PROC-FHIS", BPCPFHIS);
        if (BPCPFHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPFHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_IBZDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-DEBIT-AC", IBCPOSTA);
        if (IBCPOSTA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCPOSTA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_IBZCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-CREDIT-AC", IBCPOSTA);
        if (IBCPOSTA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCPOSTA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC, true);
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC, true);
    }
    public void S000_CALL_LNZRCONT() throws IOException,SQLException,Exception {
        LNCRCONT.REC_PTR = LNRCONT;
        LNCRCONT.REC_LEN = 416;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTCONT", LNCRCONT);
        if (LNCRCONT.RC.RC_CODE != 0) {
            LNCSERRP.RC.RC_APP = LNCRCONT.RC.RC_MMO;
            LNCSERRP.RC.RC_RTNCODE = LNCRCONT.RC.RC_CODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRSETL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTSETL", LNCRSETL);
        if (LNCRSETL.RETURN_INFO != 'F' 
            && LNCRSETL.RETURN_INFO != 'E') {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRSETL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCRSETL.RETURN_INFO);
        CEP.TRC(SCCGWA, LNCRSETL.RC);
    }
    public void S000_CALL_BPZUABOX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-ADD-CBOX", BPCUABOX, true);
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_GDZSDDDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SVR-GDZSDDDR", GDCSDDDR);
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
        if (AICUUPIA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRFUND() throws IOException,SQLException,Exception {
        LNCRFUND.REC_PTR = LNRFUND;
        LNCRFUND.REC_LEN = 456;
        IBS.CALLCPN(SCCGWA, "LN-R-FUND-MAIN", LNCRFUND);
        if (LNCRFUND.RETURN_INFO != 'F') {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRFUND.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRDISC() throws IOException,SQLException,Exception {
        LNCRDISC.REC_PTR = LNRDISC;
        LNCRDISC.REC_LEN = 258;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTDISC", LNCRDISC);
        CEP.TRC(SCCGWA, LNCRDISC.RC.RC_CODE);
        if (LNCRDISC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRDISC.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
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
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_READ_LNTCTPY_ERR, LNCSERRP.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCSERRP.RC);
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
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PACK_NOTFND, LNCSERRP.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCSERRP.RC);
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
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PACK_NOTFND, LNCSERRP.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCSERRP.RC);
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
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PACK_REWRITE, LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
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
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PLDT_REWRITE, LNCSERRP.RC);
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
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PLDT_WRITE, LNCSERRP.RC);
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
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_READ_LNTCTPY_ERR, LNCSERRP.RC);
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
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_UPD_LNTCTPY_ERR, LNCSERRP.RC);
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
    public void T000_READ_LNTICTL2() throws IOException,SQLException,Exception {
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        LNTICTL_RD.where = "CONTRACT_NO = :LNRICTL.KEY.CONTRACT_NO";
        IBS.READ(SCCGWA, LNRICTL, this, LNTICTL_RD);
    }
    public void B200_STARTBR_LNTCONT() throws IOException,SQLException,Exception {
        LNTCONT_BR.rp = new DBParm();
        LNTCONT_BR.rp.TableName = "LNTCONT";
        LNTCONT_BR.rp.where = "FATHER_CONTRACT = :LNRCONT.FATHER_CONTRACT";
        IBS.STARTBR(SCCGWA, LNRCONT, this, LNTCONT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTCONT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B200_READNEXT_LNTCONT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRCONT, this, LNTCONT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CONT_FLAG = 'Y';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTCONT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B200_ENDBR_LNTCONT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTCONT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTCONT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTICTL() throws IOException,SQLException,Exception {
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        IBS.READ(SCCGWA, LNRICTL, LNTICTL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ICTL_FLAG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ICTL_FLAG = 'Y';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTICTL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZAPRDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-APRD-MAINT", LNCAPRDM);
        if (LNCAPRDM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCAPRDM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSCKPD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-CHK-PROD", LNCSCKPD);
    }
    public void S000_CALL_GDZUMPLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-U-PLDR-RL", GDCUMPLD);
    }
    public void S000_CALL_GDZUMPDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-U-PLDR-DR", GDCUMPDR);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICQACRI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACRI.RC);
        }
    }
    public void S000_CALL_LNZTRANM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-TRAN-MAINT", LNCTRANM);
        if (LNCTRANM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = "" + LNCTRANM.RC.RC_RTNCODE;
            JIBS_tmp_int = WS_ERR_MSG.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_ERR_MSG = "0" + WS_ERR_MSG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCZTPCL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-LINK-EXTSERV", SCCTPCL);
    }
    public void S000_CALL_GDZSRLSR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SVR-GDZSRLSR", GDCSRLSR, true);
    }
    public void T000_READ_LNTRELA() throws IOException,SQLException,Exception {
        LNTRELA_RD = new DBParm();
        LNTRELA_RD.TableName = "LNTRELA";
        LNTRELA_RD.where = "CONTRACT_NO = :LNRRELA.KEY.CONTRACT_NO "
            + "AND TYPE = :LNRRELA.KEY.TYPE";
        IBS.READ(SCCGWA, LNRRELA, this, LNTRELA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_NOFND_RELA, LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTSUBS() throws IOException,SQLException,Exception {
        LNTSUBS_RD = new DBParm();
        LNTSUBS_RD.TableName = "LNTSUBS";
        IBS.READ(SCCGWA, LNRSUBS, LNTSUBS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_SUBS_NOTFND;
            S000_ERR_MSG_PROC();
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
    public void T000_READ_LNTAGRE() throws IOException,SQLException,Exception {
        LNTAGRE_RD = new DBParm();
        LNTAGRE_RD.TableName = "LNTAGRE";
        LNTAGRE_RD.where = "CONTRACT_NO = :LNRAGRE.KEY.CONTRACT_NO";
        IBS.READ(SCCGWA, LNRAGRE, this, LNTAGRE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_AGRE_NOTFND, LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_LNTRCVD() throws IOException,SQLException,Exception {
        LNTRCVD_BR.rp = new DBParm();
        LNTRCVD_BR.rp.TableName = "LNTRCVD";
        LNTRCVD_BR.rp.where = "CONTRACT_NO = :LNRRCVD.KEY.CONTRACT_NO";
        IBS.STARTBR(SCCGWA, LNRRCVD, this, LNTRCVD_BR);
    }
    public void T000_READNEXT_LNTRCVD() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRRCVD, this, LNTRCVD_BR);
    }
    public void T000_ENDBR_LNTRCVD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTRCVD_BR);
    }
    public void T000_READ_LNTEXTN() throws IOException,SQLException,Exception {
        LNTEXTN_RD = new DBParm();
        LNTEXTN_RD.TableName = "LNTEXTN";
        LNTEXTN_RD.where = "CONTRACT_NO = :LNREXTN.KEY.CONTRACT_NO "
            + "AND VAL_DT > :LNREXTN.KEY.VAL_DT "
            + "AND STATUS = :LNREXTN.KEY.STATUS";
        LNTEXTN_RD.fst = true;
        IBS.READ(SCCGWA, LNREXTN, this, LNTEXTN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_EXTN_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_EXTN_FLAG = 'N';
        } else {
        }
    }
    public void T000_READ_LNTPPRP() throws IOException,SQLException,Exception {
        LNTPPRP_RD = new DBParm();
        LNTPPRP_RD.TableName = "LNTPPRP";
        LNTPPRP_RD.where = "CONTRACT_NO = :LNRPPRP.KEY.CONTRACT_NO "
            + "AND VAL_DT >= :LNRPPRP.KEY.VAL_DT";
        LNTPPRP_RD.fst = true;
        IBS.READ(SCCGWA, LNRPPRP, this, LNTPPRP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_PPRP_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_PPRP_FLAG = 'N';
        } else {
        }
    }
    public void S000_CALL_BPZFFCON() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-CON-CHG-INFO", BPCFFCON);
        if (BPCFFCON.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFFCON.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFFTXI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-TX-INFO", BPCFFTXI);
        if (BPCFFTXI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFFTXI.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFSSVR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-U-MAINTAIN-FSVR", BPCOFSVR);
        if (BPCOFSVR.FOUND_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FEE_CODE, LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB);
        if (AICPQMIB.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQMIB.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_GDCIQLDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-I-QLDR-PROC", GDCIQLDR);
        if (GDCIQLDR.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, GDCIQLDR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFCALF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-CAL-FEE", BPCTCALF);
        CEP.TRC(SCCGWA, BPCTCALF.RC);
        if (BPCTCALF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTCALF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZPQIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-IA", AICPQIA);
        if (AICPQIA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQIA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSSTBL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-CHECK-CI-STS", LNCSSTBL);
        if (LNCSSTBL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSSTBL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICLNQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUNC-LN-INFO", LNCCLNQ);
        if (LNCCLNQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCLNQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSPREQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-PREPAY-CALC", LNCSPREQ, true);
        if (LNCSPREQ.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSPREQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZSQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-DDZSQBAL", DDCSQBAL);
        if (DDCSQBAL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCSQBAL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZPQITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-ITM", AICPQITM);
        if (AICPQITM.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQITM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T0000_READ_LNTICTL() throws IOException,SQLException,Exception {
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        LNTICTL_RD.where = "CONTRACT_NO = :LNRICTL.KEY.CONTRACT_NO";
        IBS.READ(SCCGWA, LNRICTL, this, LNTICTL_RD);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        if (CICACCU.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZIGEAI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUNC-AI-AC", LNCIGEAI);
        if (LNCIGEAI.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCIGEAI.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSERRP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCSERRP.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCSERRP=");
            CEP.TRC(SCCGWA, LNCSERRP);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
