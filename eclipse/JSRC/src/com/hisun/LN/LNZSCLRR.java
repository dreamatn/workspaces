package com.hisun.LN;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.AI.AICPQIA;
import com.hisun.AI.AICPQITM;
import com.hisun.AI.AICPQMIB;
import com.hisun.AI.AICUUPIA;
import com.hisun.BP.BPCFFCON;
import com.hisun.BP.BPCFFTXI;
import com.hisun.BP.BPCIDAY;
import com.hisun.BP.BPCOFBAS;
import com.hisun.BP.BPCOFSVR;
import com.hisun.BP.BPCUABOX;
import com.hisun.CI.CICACCU;
import com.hisun.DC.DCCPACTY;
import com.hisun.DD.DDCUCRAC;
import com.hisun.DD.DDCUDRAC;
import com.hisun.GD.GDCIQLDR;
import com.hisun.GD.GDCUMPDR;
import com.hisun.GD.GDCUMPLD;
import com.hisun.IB.IBCPOSTA;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCSUBS;

public class LNZSCLRR {
    boolean pgmRtn = false;
    String K_SETL_DEPAC = "01";
    String K_SETL_GLSUS = "02";
    String K_SETL_NOSCD = "03";
    String K_SETL_GM = "04";
    String K_SETL_DEBIT = "05";
    String K_SETL_ECS = "06";
    String K_SETL_CHEQUE = "07";
    String K_SETL_CASH = "08";
    short K_AC_NO = 5;
    String K_DD_AC = "01";
    String K_INTERNAL = "02";
    String K_IB_AC = "03";
    String K_DC_AC = "05";
    String K_EB_AC = "06";
    LNZSCLRR_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new LNZSCLRR_WS_TEMP_VARIABLE();
    LNZSCLRR_WS_LOAN_CONT_AREA WS_LOAN_CONT_AREA = new LNZSCLRR_WS_LOAN_CONT_AREA();
    LNZSCLRR_WS_OUT_LNW32 WS_OUT_LNW32 = new LNZSCLRR_WS_OUT_LNW32();
    char WS_READ_LNTPLDT_FLG = ' ';
    char WS_READ_LNTICTL_FLG = ' ';
    char WS_PI_PROC_FLG = ' ';
    char WS_UPD_ICTL_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    SCCSUBS SCCSUBS = new SCCSUBS();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    AICPQIA AICPQIA = new AICPQIA();
    AICUUPIA AICUUPIA = new AICUUPIA();
    AICPQITM AICPQITM = new AICPQITM();
    AICPQMIB AICPQMIB = new AICPQMIB();
    BPCFFCON BPCFFCON = new BPCFFCON();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPCIDAY BPCIDAY = new BPCIDAY();
    BPCUABOX BPCUABOX = new BPCUABOX();
    BPCOFSVR BPCOFSVR = new BPCOFSVR();
    BPCOFBAS BPCOFBAS = new BPCOFBAS();
    CICACCU CICACCU = new CICACCU();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    GDCIQLDR GDCIQLDR = new GDCIQLDR();
    GDCUMPDR GDCUMPDR = new GDCUMPDR();
    GDCUMPLD GDCUMPLD = new GDCUMPLD();
    GDCUTRCL GDCUTRCL = new GDCUTRCL();
    LNRICTL LNRICTL = new LNRICTL();
    LNRCONT LNRCONT = new LNRCONT();
    LNRAPRD LNRAPRD = new LNRAPRD();
    LNREXTN LNREXTN = new LNREXTN();
    LNRPPRP LNRPPRP = new LNRPPRP();
    LNRRELA LNRRELA = new LNRRELA();
    LNRRCVD LNRRCVD = new LNRRCVD();
    LNRPAIP LNRPAIP = new LNRPAIP();
    LNRPACK LNRPACK = new LNRPACK();
    LNCSRPC LNCSRPC = new LNCSRPC();
    LNCICUT LNCICUT = new LNCICUT();
    LNCCNEX LNCCNEX = new LNCCNEX();
    LNCCONTM LNCCONTM = new LNCCONTM();
    LNCBALLM LNCBALLM = new LNCBALLM();
    LNCSSTBL LNCSSTBL = new LNCSSTBL();
    LNCPPMQ LNCPPMQ = new LNCPPMQ();
    LNRSUBS LNRSUBS = new LNRSUBS();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    LNRFUND LNRFUND = new LNRFUND();
    LNRLOAN LNRLOAN = new LNRLOAN();
    LNRTRAN LNRTRAN = new LNRTRAN();
    LNRCTPY LNRCTPY = new LNRCTPY();
    LNRSETL LNRSETL = new LNRSETL();
    LNCRSETL LNCRSETL = new LNCRSETL();
    LNRWOFF LNRWOFF = new LNRWOFF();
    LNCRWOFF LNCRWOFF = new LNCRWOFF();
    LNRPLDT LNRPLDT = new LNRPLDT();
    LNCIGEAI LNCIGEAI = new LNCIGEAI();
    LNCURPNR LNCURPNR = new LNCURPNR();
    LNCPYIFM LNCPYIFM = new LNCPYIFM();
    LNCINTDM LNCINTDM = new LNCINTDM();
    LNRINTD LNRINTD = new LNRINTD();
    LNCCNEV LNCCNEV = new LNCCNEV();
    LNCUPRP LNCUPRP = new LNCUPRP();
    LNCCNBU LNCCNBU = new LNCCNBU();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCPLAJ LNCPLAJ = new LNCPLAJ();
    LNCCONTM LNCCONTM = new LNCCONTM();
    LNCTRANM LNCTRANM = new LNCTRANM();
    LNCRCAL LNCRCAL = new LNCRCAL();
    LNRPYIF LNRPYIF = new LNRPYIF();
    LNCICAL LNCICAL = new LNCICAL();
    IBCPOSTA IBCPOSTA = new IBCPOSTA();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    LNRAGRE LNRAGRE = new LNRAGRE();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    LNCSCLRR LNCSCLRR;
    public void MP(SCCGWA SCCGWA, LNCSCLRR LNCSCLRR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSCLRR = LNCSCLRR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        A100_CHECK_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSCLRR return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        LNCSCLRR.RC.RC_APP = "LN";
        LNCSCLRR.RC.RC_RTNCODE = 0;
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
    }
    public void A100_CHECK_PROCESS() throws IOException,SQLException,Exception {
        if (LNCSCLRR.COMM_DATA.CTA_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CTA_NO_M_INPUT, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_VALIDATION();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNRAPRD);
        LNRAPRD.KEY.CONTRACT_NO = LNCSCLRR.COMM_DATA.CTA_NO;
        T000_READ_LNTAPRD();
        if (pgmRtn) return;
        if (WS_TEMP_VARIABLE.WS_CTL_STSW == null) WS_TEMP_VARIABLE.WS_CTL_STSW = "";
        JIBS_tmp_int = WS_TEMP_VARIABLE.WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_TEMP_VARIABLE.WS_CTL_STSW += " ";
        if (WS_TEMP_VARIABLE.WS_CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
            && LNRAPRD.GDA_APRE.trim().length() > 0) {
            B200_GUAR_ADD_PROC();
            if (pgmRtn) return;
        }
        B300_REPAY_AC_REV_PROC();
        if (pgmRtn) return;
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(19 - 1, 19 + 1 - 1).equalsIgnoreCase("1") 
            && (LNCSCLRR.COMM_DATA.P_CLR_FLG == 'Y' 
            || LNCSCLRR.COMM_DATA.I_CLR_FLG == 'Y')) {
            B400_MAD_LOAN_REV_PROC();
            if (pgmRtn) return;
        }
        B500_SOLD_OUT_REV_PROC();
        if (pgmRtn) return;
        WS_TEMP_VARIABLE.WS_REPAY_NOR = LNCSCLRR.COMM_DATA.NOR_P + LNCSCLRR.COMM_DATA.NOR_I;
        WS_TEMP_VARIABLE.WS_REPAY_OVR = LNCSCLRR.COMM_DATA.OVR_P + LNCSCLRR.COMM_DATA.OVR_I + LNCSCLRR.COMM_DATA.NOR_O + LNCSCLRR.COMM_DATA.NOR_L;
        if (WS_TEMP_VARIABLE.WS_REPAY_NOR != 0 
            && (LNCSCLRR.COMM_DATA.I_CLR_FLG == 'Y' 
            || LNCSCLRR.COMM_DATA.P_CLR_FLG == 'Y')) {
            B600_PRP_REV_PROC();
            if (pgmRtn) return;
        }
        if (WS_TEMP_VARIABLE.WS_REPAY_OVR != 0) {
            B700_RPN_REV_PROC();
            if (pgmRtn) return;
        }
    }
    public void B100_CHECK_VALIDATION() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRICTL);
        LNRICTL.KEY.CONTRACT_NO = LNCSCLRR.COMM_DATA.CTA_NO;
