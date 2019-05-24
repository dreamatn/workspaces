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
import com.hisun.CI.CICMACR;
import com.hisun.CI.CICQACAC;
import com.hisun.CI.CICQACRI;
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

public class LNZSCLR {
    boolean pgmRtn = false;
    String WS_SETL_DEPAC = "01";
    String WS_SETL_GLSUS = "02";
    String WS_SETL_NOSCD = "03";
    String WS_SETL_GM = "04";
    String WS_SETL_DEBIT = "05";
    String WS_SETL_ECS = "06";
    String WS_SETL_CHEQUE = "07";
    String WS_SETL_CASH = "08";
    String K_DD_AC = "01";
    String K_IA_AC = "02";
    String K_IB_AC = "03";
    String K_DC_AC = "05";
    String K_EB_AC = "06";
    String WS_PAPER_NO = " ";
    LNZSCLR_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new LNZSCLR_WS_TEMP_VARIABLE();
    LNZSCLR_WS_LOAN_CONT_AREA WS_LOAN_CONT_AREA = new LNZSCLR_WS_LOAN_CONT_AREA();
    char WS_SUBS_FLG = ' ';
    char WS_READ_LNTCONT_FLG = ' ';
    char WS_READ_LNTICTL_FLG = ' ';
    char WS_READ_LNTAPRD_FLG = ' ';
    char WS_READ_LNTEXTN_FLG = ' ';
    char WS_READ_LNTPPRP_FLG = ' ';
    char WS_READ_LNTRCVD_FLG = ' ';
    char WS_READ_LNTPLDT_FLG = ' ';
    char WS_READ_LNTDISB_FLG = ' ';
    char WS_AC_FLG = ' ';
    char WS_PI_PROC_FLG = ' ';
    char WS_UPD_ICTL_FLG = ' ';
    char WS_UPD_WOFF_FLG = ' ';
    char WS_UPD_STSW44_0_FLG = ' ';
    char WS_UPD_STSW45_0_FLG = ' ';
    char WS_JQ_EXIST_FLG = ' ';
    char WS_AGRE_FOUND_FLG = ' ';
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
    CICMACR CICMACR = new CICMACR();
    DCCPACTY DCCPACTY = new DCCPACTY();
    CICQACRI CICQACRI = new CICQACRI();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    LNRAGRE LNRAGRE = new LNRAGRE();
    GDCIQLDR GDCIQLDR = new GDCIQLDR();
    GDCUMPDR GDCUMPDR = new GDCUMPDR();
    GDCUMPLD GDCUMPLD = new GDCUMPLD();
    LNCAMDB LNCAMDB = new LNCAMDB();
    LNCSPDQ LNCSPDQ = new LNCSPDQ();
    LNRICTL LNRICTL = new LNRICTL();
    LNRCONT LNRCONT = new LNRCONT();
    LNRAPRD LNRAPRD = new LNRAPRD();
    LNREXTN LNREXTN = new LNREXTN();
    LNRPPRP LNRPPRP = new LNRPPRP();
    LNRRELA LNRRELA = new LNRRELA();
    LNRRCVD LNRRCVD = new LNRRCVD();
    LNRDISB LNRDISB = new LNRDISB();
    LNRPAIP LNRPAIP = new LNRPAIP();
    LNRPACK LNRPACK = new LNRPACK();
    LNCSRPC LNCSRPC = new LNCSRPC();
    LNCICUT LNCICUT = new LNCICUT();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCCNEX LNCCNEX = new LNCCNEX();
    LNCBALLM LNCBALLM = new LNCBALLM();
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
    LNCURPN LNCURPN = new LNCURPN();
    LNCPYIFM LNCPYIFM = new LNCPYIFM();
    LNCINTDM LNCINTDM = new LNCINTDM();
    LNRINTD LNRINTD = new LNRINTD();
    LNCCNEV LNCCNEV = new LNCCNEV();
    LNCUPRP LNCUPRP = new LNCUPRP();
    LNCCNBU LNCCNBU = new LNCCNBU();
    LNCPLAJ LNCPLAJ = new LNCPLAJ();
    LNCCONTM LNCCONTM = new LNCCONTM();
    LNCTRANM LNCTRANM = new LNCTRANM();
    IBCPOSTA IBCPOSTA = new IBCPOSTA();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    CICSACR CICSACR = new CICSACR();
    CICQACAC CICQACAC = new CICQACAC();
    CICSACAC CICSACAC = new CICSACAC();
    int WS_CNT = 0;
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    LNCSCLR LNCSCLR;
    public void MP(SCCGWA SCCGWA, LNCSCLR LNCSCLR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSCLR = LNCSCLR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        A100_CHECK_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSCLR return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        LNCSCLR.RC.RC_APP = "LN";
        LNCSCLR.RC.RC_RTNCODE = 0;
        WS_TEMP_VARIABLE.WS_BBR_I = 1;
    }
    public void A100_CHECK_PROCESS() throws IOException,SQLException,Exception {
        if (LNCSCLR.COMM_DATA.CTA_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CTA_NO_M_INPUT, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCSCLR.COMM_DATA.TR_VAL_DT == 0) {
            LNCSCLR.COMM_DATA.TR_VAL_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (LNCSCLR.COMM_DATA.P_CLR_FLG == ' ') {
            LNCSCLR.COMM_DATA.P_CLR_FLG = 'Y';
        }
        if (LNCSCLR.COMM_DATA.I_CLR_FLG == ' ') {
            LNCSCLR.COMM_DATA.I_CLR_FLG = 'Y';
        }
        if (LNCSCLR.COMM_DATA.PC_CLR_FLG == ' ') {
            LNCSCLR.COMM_DATA.PC_CLR_FLG = 'Y';
        }
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_VALIDATION();
        if (pgmRtn) return;
        if (WS_READ_LNTRCVD_FLG == 'F') {
            IBS.init(SCCGWA, LNRDISB);
            LNRDISB.KEY.CTA_NO = LNCSCLR.COMM_DATA.CTA_NO;
            T000_READUPD_LNTDISB();
            if (pgmRtn) return;
            if (WS_READ_LNTDISB_FLG == 'F') {
                T000_CLEAR_LNTDISB();
                if (pgmRtn) return;
            }
            B200_RPC_QUERY_PROC();
            if (pgmRtn) return;
        }
        B300_PRP_QUERY_PROC();
        if (pgmRtn) return;
        B400_CLR_ACCOUNT_PROC();
        if (pgmRtn) return;
    }
    public void B100_CHECK_VALIDATION() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCONT);
        LNRCONT.KEY.CONTRACT_NO = LNCSCLR.COMM_DATA.CTA_NO;
        T000_READ_CONT();
        if (pgmRtn) return;
        if (WS_READ_LNTCONT_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CONT_NOTFND, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNRICTL);
        LNRICTL.KEY.CONTRACT_NO = LNCSCLR.COMM_DATA.CTA_NO;
        T000_READ_ICTL();
        if (pgmRtn) return;
        if (WS_READ_LNTICTL_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_ICTL_NOTFND, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_TEMP_VARIABLE.WS_CTL_STSW = LNRICTL.CTL_STSW;
        IBS.init(SCCGWA, LNRAPRD);
        LNRAPRD.KEY.CONTRACT_NO = LNCSCLR.COMM_DATA.CTA_NO;
        T000_READ_APRD();
        if (pgmRtn) return;
        if (WS_READ_LNTAPRD_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_APRD_NOTFND, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
            || LNRICTL.CTL_STSW.substring(47 - 1, 47 + 1 - 1).equalsIgnoreCase("1") 
            || LNRICTL.CTL_STSW.substring(51 - 1, 51 + 1 - 1).equalsIgnoreCase("1") 
            || LNRICTL.CTL_STSW.substring(53 - 1, 53 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_ELD_AC_STS, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCSCLR.COMM_DATA.TR_VAL_DT < LNRCONT.LAST_F_VAL_DATE) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.ERR_CONT_VALDT_LESS_GT, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNREXTN);
        LNREXTN.KEY.CONTRACT_NO = LNCSCLR.COMM_DATA.CTA_NO;
        LNREXTN.KEY.STATUS = '1';
        LNREXTN.KEY.VAL_DT = LNCSCLR.COMM_DATA.TR_VAL_DT;
        T000_READ_EXTN_FIRST();
        if (pgmRtn) return;
        if (WS_READ_LNTEXTN_FLG == 'F') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_EXTN_RFUSE_CLR, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNRPPRP);
        LNRPPRP.KEY.CONTRACT_NO = LNCSCLR.COMM_DATA.CTA_NO;
        LNRPPRP.STATUS = '1';
        LNRPPRP.KEY.VAL_DT = LNCSCLR.COMM_DATA.TR_VAL_DT;
        T000_READ_PPRP_FIRST();
        if (pgmRtn) return;
        if (WS_READ_LNTPPRP_FLG == 'F') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PPRP_RFUSE_CLR, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNRRCVD);
        LNRRCVD.KEY.CONTRACT_NO = LNCSCLR.COMM_DATA.CTA_NO;
