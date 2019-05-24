package com.hisun.LN;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.AI.AICPIAEV;
import com.hisun.AI.AICPQIA;
import com.hisun.AI.AICPQMIB;
import com.hisun.AI.AICUUPIA;
import com.hisun.BP.BPCFFCON;
import com.hisun.BP.BPCFFTXI;
import com.hisun.BP.BPCOFBAS;
import com.hisun.BP.BPCUABOX;
import com.hisun.BP.BPCUSBOX;
import com.hisun.CI.CICACCU;
import com.hisun.CI.CICCUST;
import com.hisun.CI.CICQACRI;
import com.hisun.DC.DCCPACTY;
import com.hisun.DC.DCCUCINF;
import com.hisun.DC.DCCURHLD;
import com.hisun.DD.DDCIMCCY;
import com.hisun.DD.DDCIMMST;
import com.hisun.DD.DDCUCRAC;
import com.hisun.DD.DDCUDRAC;
import com.hisun.DD.DDCUUPAY;
import com.hisun.GD.GDCSRLSR;
import com.hisun.IB.IBCPOSTA;
import com.hisun.IB.IBCQINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGAPV;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.VT.VTCPQTAX;

public class LNZSLDRW {
    boolean pgmRtn = false;
    char K_INQUERY = 'I';
    char K_NORMAL_INT = 'I';
    String K_DD_AC = "01";
    String K_INTERNAL = "02";
    String K_IB_AC = "03";
    String K_DC_AC = "05";
    String K_EB_AC = "06";
    String K_CNY = "156";
    String K_GUAR_ADV = "1";
    String K_FACTR_ADV = "2";
    String K_BIL_A_ADV = "3";
    String K_LC_ADV = "4";
    String K_LN_P_ADV = "5";
    String K_LN_I_ADV = "6";
    String K_DISC_ADV = "7";
    String K_REDISC_ADV = "8";
    String K_OTH_ADV = "9";
    String CPN_U_MAINTAIN_FBAS = "BP-F-U-MAINTAIN-FBAS";
    char K_CKPD_INQ = '0';
    char LNZSLDRW_FILLER1 = ' ';
    String WS_PAY_AC = " ";
    double WS_PRE_INT = 0;
    double WS_TOT_PRIN = 0;
    double WS_DRAW_AMT = 0;
    double WS_YHS_AMT = 0;
    String WS_YHS_AC = " ";
    short WS_CNT = 0;
    String WS_RVS_NO = " ";
    String WS_QUERY_AC = " ";
    String WS_ACCOUNT_AC = " ";
    char WS_ACCOUNT_TYPE = ' ';
    String WS_AC_TYPE = " ";
    double WS_ACCOUNT_AMT = 0;
    int WS_AC_BBR = 0;
    int WS_FUND_BBR = 0;
    int WS_YHS_BBR = 0;
    int WS_DRAW_BBR = 0;
    int WS_PAYI_BBR = 0;
    int WS_CONS_INT_BBR = 0;
    int WS_ATO_BBR = 0;
    int WS_PRO_BBR = 0;
    int WS_JSDRAW_BBR = 0;
    int WS_FEE_BBR = 0;
    int WS_PAY_BBR = 0;
    int WS_SETTLE_BBR = 0;
    char WS_GD_AC_FLG = ' ';
    LNZSLDRW_WS_BBR_DATA[] WS_BBR_DATA = new LNZSLDRW_WS_BBR_DATA[10];
    short WS_IDX = 0;
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    LNZSLDRW_WS_MSGID WS_MSGID = new LNZSLDRW_WS_MSGID();
    short WS_FLD_NO = 0;
    LNZSLDRW_WS_DD_NARRATIVE WS_DD_NARRATIVE = new LNZSLDRW_WS_DD_NARRATIVE();
    double WS_PARTI_AMT = 0;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    LNCUDRW LNCUDRW = new LNCUDRW();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    DDCIMCCY DDCIMCCY = new DDCIMCCY();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    DDCIMMST DDCIMMST = new DDCIMMST();
    LNCICIQ LNCICIQ = new LNCICIQ();
    LNRCONT LNRCONT = new LNRCONT();
    LNCRCONT LNCRCONT = new LNCRCONT();
    BPCUSBOX BPCUSBOX = new BPCUSBOX();
    AICPIAEV AICPIAEV = new AICPIAEV();
    LNCUDRWR LNCUDRWR = new LNCUDRWR();
    BPCUABOX BPCUABOX = new BPCUABOX();
    LNCICUT LNCICUT = new LNCICUT();
    IBCQINF IBCQINF = new IBCQINF();
    IBCPOSTA IBCPOSTA = new IBCPOSTA();
    AICPQMIB AICPQMIB = new AICPQMIB();
    DCCUCINF DCCUCINF = new DCCUCINF();
    AICUUPIA AICUUPIA = new AICUUPIA();
    LNRSETL LNRSETL = new LNRSETL();
    DCCUHLD DCCUHLD = new DCCUHLD();
    DCCURHLD DCCURHLD = new DCCURHLD();
    DDCUUPAY DDCUUPAY = new DDCUUPAY();
    BPCFFCON BPCFFCON = new BPCFFCON();
    BPCOFBAS BPCOFBAS = new BPCOFBAS();
    DCCPACTY DCCPACTY = new DCCPACTY();
    GDCSMPIN GDCSMPIN = new GDCSMPIN();
    AICUIANO AICUIANO = new AICUIANO();
    CICACCU CICACCU = new CICACCU();
    DDCSIBAL DDCSIBAL = new DDCSIBAL();
    LNCTRPRM LNCTRPRM = new LNCTRPRM();
    LNRICTL LNRICTL = new LNRICTL();
    AICPQIA AICPQIA = new AICPQIA();
    GDCSRLSR GDCSRLSR = new GDCSRLSR();
    LNRFUND LNRFUND = new LNRFUND();
    GDCSMPRL GDCSMPRL = new GDCSMPRL();
    LNRPARS LNRPARS = new LNRPARS();
    LNCSCKPD LNCSCKPD = new LNCSCKPD();
    CICQACRI CICQACRI = new CICQACRI();
    CICCUST CICCUST = new CICCUST();
    VTCPQTAX VTCPQTAX = new VTCPQTAX();
    SCCGWA SCCGWA;
    LNCSWLAD LNCSLDRW;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    public LNZSLDRW() {
        for (int i=0;i<10;i++) WS_BBR_DATA[i] = new LNZSLDRW_WS_BBR_DATA();
    }
    public void MP(SCCGWA SCCGWA, LNCSWLAD LNCSLDRW) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSLDRW = LNCSLDRW;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSLDRW return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
        LNCSLDRW.RC.RC_APP = "LN";
        LNCSLDRW.RC.RC_RTNCODE = 0;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B000_CHECK() throws IOException,SQLException,Exception {
        B010_INPUT_DATA_CHECK();
        if (pgmRtn) return;
        B020_GET_PROD_INF();
        if (pgmRtn) return;
        B030_TRAN_DATA_CHECK();
        if (pgmRtn) return;
        B031_COMP_PREC_AMT();
        if (pgmRtn) return;
    }
    public void B010_INPUT_DATA_CHECK() throws IOException,SQLException,Exception {
        if (LNCSLDRW.COMM_DATA.CTA_NO.trim().length() == 0) {