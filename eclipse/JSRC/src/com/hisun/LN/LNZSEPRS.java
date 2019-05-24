package com.hisun.LN;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.AI.AICPQIA;
import com.hisun.AI.AICUUPIA;
import com.hisun.BP.BPCFFCON;
import com.hisun.BP.BPCFFTXI;
import com.hisun.BP.BPCOFSVR;
import com.hisun.BP.BPCPFHIS;
import com.hisun.BP.BPCPOEWA;
import com.hisun.BP.BPCPQBNK_DATA_INFO;
import com.hisun.BP.BPCUABOX;
import com.hisun.CI.CICACCU;
import com.hisun.CI.CICCUST;
import com.hisun.CI.CICQACAC;
import com.hisun.CI.CICQACRI;
import com.hisun.DC.DCCPACTY;
import com.hisun.DD.DDCUCRAC;
import com.hisun.DD.DDCUDRAC;
import com.hisun.GD.GDCIQLDR;
import com.hisun.GD.GDCUMPLD;
import com.hisun.IB.IBCPOSTA;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGAPV;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCTPCL;

public class LNZSEPRS {
    boolean pgmRtn = false;
    int WS_INT_ED = 0;
    String WS_ERR_MSG = " ";
    LNZSEPRS_WS_INFO WS_INFO = new LNZSEPRS_WS_INFO();
    LNZSEPRS_WS_SETL_AC_INF WS_SETL_AC_INF = new LNZSEPRS_WS_SETL_AC_INF();
    int WS_X = 0;
    short WS_H = 0;
    LNZSEPRS_WS_CHECK_INFO WS_CHECK_INFO = new LNZSEPRS_WS_CHECK_INFO();
    int WS_AC_NO = 0;
    double WS_TXN_B_AMT = 0;
    double WS_TXN_T_AMT = 0;
    String WS_TX_AC_TYP = " ";
    String WS_TX_AC = " ";
    double WS_TX_AMT = 0;
    double WS_TX_B_AMT = 0;
    double WS_TX_S_AMT = 0;
    double WS_FUND_OL_AMT = 0;
    String WS_FEE_CODE_PC = " ";
    String WS_FEE_CODE_HRG = " ";
    double WS_PAY_I_AMT = 0;
    double WS_PAY_O_AMT = 0;
    double WS_PAY_L_AMT = 0;
    double WS_EX_PC_AMT = 0;
    double WS_FEE_PC_AMT = 0;
    double WS_TR_TOT_AMT = 0;
    double WS_TOT_AMT = 0;
    String WS_SUSP_AC = " ";
    String WS_RELEASE_AC = " ";
    String WS_SYLOAN_AC_TYP = " ";
    String WS_SYLOAN_AC = " ";
    double WS_PAY_R_I_AMT = 0;
    char WS_CTPY_TR_TYP = ' ';
    char WS_TRAN_REC_FLG = ' ';
    double WS_I_LEFT_AMT = 0;
    int WS_IDX = 0;
    LNZSEPRS_WS_BBR_INFO[] WS_BBR_INFO = new LNZSEPRS_WS_BBR_INFO[20];
    int WS_I = 0;
    char WS_PART_IS_SYN_FLAG = ' ';
    char WS_FOUND_FLG = ' ';
    char WS_LMTU_FOUND = ' ';
    char WS_READ_LNTPLDT_FLG = ' ';
    char WS_READ_LNTICTL_FLG = ' ';
    char WS_PI_PROC_FLG = ' ';
    char WS_UPD_ICTL_FLG = ' ';
    LNCQ65 LNCQ65 = new LNCQ65();
    LNRSETL LNRSETL = new LNRSETL();
    LNRRELA LNRRELA = new LNRRELA();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    IBCPOSTA IBCPOSTA = new IBCPOSTA();
    LNCUPRP LNCUPRPR = new LNCUPRP();
    LNCICRCM LNCICRCM = new LNCICRCM();
    LNCPPMQ LNCPPMQ = new LNCPPMQ();
    LNCUCMMT LNCUCMMT = new LNCUCMMT();
    LNCRSETL LNCRSETL = new LNCRSETL();
    LNCTRANM LNCTRANM = new LNCTRANM();
    BPCUABOX BPCUABOX = new BPCUABOX();
    AICUUPIA AICUUPIA = new AICUUPIA();
    LNRFUND LNRFUND = new LNRFUND();
    LNCRFUND LNCRFUND = new LNCRFUND();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    GDCIQLDR GDCIQLDR = new GDCIQLDR();
    GDCUMPLD GDCUMPLD = new GDCUMPLD();
    DCCPACTY DCCPACTY = new DCCPACTY();
    LNRICTL LNRICTL = new LNRICTL();
    SCCTPCL SCCTPCL = new SCCTPCL();
    CICCUST CICCUST = new CICCUST();
    LNRAGRE LNRAGRE = new LNRAGRE();
    LNRPLDT LNRPLDT = new LNRPLDT();
    LNRCTPY LNRCTPY = new LNRCTPY();
    LNRTRAN LNRTRAN = new LNRTRAN();
    BPCFFCON BPCFFCON = new BPCFFCON();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    LNRDISC LNRDISC = new LNRDISC();
    LNCRDISC LNCRDISC = new LNCRDISC();
    BPCOFSVR BPCOFSVR = new BPCOFSVR();
    LNCSSTBL LNCSSTBL = new LNCSSTBL();
    CICACCU CICACCU = new CICACCU();
    LNCCLNQ LNCCLNQ = new LNCCLNQ();
    GDCUTRCL GDCUTRCL = new GDCUTRCL();
    AICPQIA AICPQIA = new AICPQIA();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    LNCIGEAI LNCIGEAI = new LNCIGEAI();
    LNRSUBS LNRSUBS = new LNRSUBS();
    LNRPACK LNRPACK = new LNRPACK();
    LNCICTLM LNCICTLM = new LNCICTLM();
    CICQACRI CICQACRI = new CICQACRI();
    CICQACAC CICQACAC = new CICQACAC();
    LNCRCONT LNCRCONT = new LNCRCONT();
    LNRCONT LNRCONT = new LNRCONT();
    LNCSPMHQ LNCSPMHQ = new LNCSPMHQ();
    LNRLOAN LNRLOAN = new LNRLOAN();
    LNCIPAMT LNCIPAMT = new LNCIPAMT();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGAPV SCCGAPV;
    BPCPQBNK_DATA_INFO BPCRBANK;
    LNCSERRP LNCSEPRS;
    public LNZSEPRS() {
        for (int i=0;i<20;i++) WS_BBR_INFO[i] = new LNZSEPRS_WS_BBR_INFO();
    }
    public void MP(SCCGWA SCCGWA, LNCSERRP LNCSEPRS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSEPRS = LNCSEPRS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSEPRS return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        IBS.init(SCCGWA, LNCSEPRS.RC);
        LNCSEPRS.TR_MMO = " ";
        LNCSEPRS.TR_MMO = "D106";
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (LNCAPRDM.REC_DATA.GDA_APRE.trim().length() > 0) {
            B100_CREATE_PLDR_PROC();
            if (pgmRtn) return;
        }
        if (LNCSEPRS.FUNC != 'W') {
            B090_SWICH_OTHER_APP();
            if (pgmRtn) return;
        }
        if (!LNCCLNQ.DATA.CONT_TYPE.equalsIgnoreCase("CLDL")) {
            B091_SWICH_PROJ();
            if (pgmRtn) return;
        }
        B091_SWICH_CTPY();
        if (pgmRtn) return;
        B050_BORR_REV_PROCESS();
        if (pgmRtn) return;
        B095_GET_OUTPUT();
        if (pgmRtn) return;
        B092_GENERATE_HIS();
        if (pgmRtn) return;
        if (LNCSEPRS.TOT_P_AMT > 0 
            && !LNCCLNQ.DATA.CONT_TYPE.equalsIgnoreCase("CLDL") 
            && !SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
            B095_UP_ZHW();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNCSEPRS.CTA.trim().length() == 0) {
