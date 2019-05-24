package com.hisun.TD;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.AI.AICPQIA;
import com.hisun.AI.AICUUPIA;
import com.hisun.BP.BPCCINTI;
import com.hisun.BP.BPCFAMTA;
import com.hisun.BP.BPCFCSTS;
import com.hisun.BP.BPCFFTXI;
import com.hisun.BP.BPCFTLAM;
import com.hisun.BP.BPCFX;
import com.hisun.BP.BPCPEBAS;
import com.hisun.BP.BPCPFHIS;
import com.hisun.BP.BPCPLDPD;
import com.hisun.BP.BPCPLOSS;
import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPOEWA;
import com.hisun.BP.BPCPORUP_DATA_INFO;
import com.hisun.BP.BPCPQORG;
import com.hisun.BP.BPCPQPRD;
import com.hisun.BP.BPCPRGST;
import com.hisun.BP.BPCPRMM;
import com.hisun.BP.BPCPRMR;
import com.hisun.BP.BPCRDAMT;
import com.hisun.BP.BPCSLOSS;
import com.hisun.BP.BPCSOCAC;
import com.hisun.BP.BPCTCALF;
import com.hisun.BP.BPCUBUSE;
import com.hisun.BP.BPCUNARR;
import com.hisun.BP.BPRPRMT;
import com.hisun.CI.CICACCU;
import com.hisun.CI.CICCUST;
import com.hisun.CI.CICGAGA_AGENT_AREA_AGENT_AREA;
import com.hisun.CI.CICMACR;
import com.hisun.CI.CICQACAC;
import com.hisun.CI.CICQACRI;
import com.hisun.CI.CICQACRL;
import com.hisun.CI.CICSAGEN;
import com.hisun.DC.DCCMSG_ERROR_MSG;
import com.hisun.DC.DCCUCINF;
import com.hisun.DC.DCRSPAC;
import com.hisun.DD.DDCIMCCY;
import com.hisun.DD.DDCIMMST;
import com.hisun.DD.DDCMSG_ERROR_MSG;
import com.hisun.DD.DDCSCINM;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCTPCL;

public class TDZACDRU {
    boolean pgmRtn = false;
    String K_PAY_FMT = "TD007";
    String K_INT_FMT = "TD008";
    String K_OUTPUT_FMT = "TD018";
    String K_AP_MMO = "TD";
    String K_NORM_DR_STS_TBL = "0005";
    String K_NORM_DR_STS_TBL_P = "0015";
    String K_NORM_DR_STS_TBL_C = "0006";
    String K_NORM_DR_STS_TBL_C_P = "0016";
    String K_AUTO_DR_STS_TBL = "0010";
    String K_NORM_DR_AMT_TBL = "0002";
    String K_NORM_TR_AMT_TBL = "0003";
    String K_NORM_LN_DR_TBL = "0030";
    String K_NORM_LOS_STS_TBL_P = "0035";
    String K_NORM_LOS_STS_TBL_C = "0045";
    String K_SYS_ERR = "SC6001";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String PGM_SCSSCKDT = "SCSSCKDT";
    char K_FINAL_DEP = '3';
    String K_TD_CHQ_CODE = "TDOCS";
    short K_MAX_DD = 40;
    String K_AC_BK = "043";
    String WS_MSGID = " ";
    String WS_MMO = " ";
    String WS_CI_CNM = " ";
    String WS_CI_ENM = " ";
    String WS_AC_CNM = " ";
    String WS_AC_ENM = " ";
    String WS_TD_CI_NO = " ";
    String WS_OPP_CI_NO = " ";
    double WS_HIS_AMT = 0;
    double WS_BUD_INT = 0;
    double WS_0YG_AMT_WY = 0;
    double WS_TOT_PAY_AMT = 0;
    double WS_HOLD_INT_AMT = 0;
    double WS_HOLD_TAX_AMT = 0;
    int WS_LAST_DEAL_DT = 0;
    int WS_NEXT_DEAL_DT = 0;
    char WS_CCY_DEF_FLG = ' ';
    double WS_DR_INT_AMT = 0;
    short WS_NUM = 0;
    TDZACDRU_WS_RC_MSG WS_RC_MSG = new TDZACDRU_WS_RC_MSG();
    short WS_RC_DISP = 0;
    char WS_DR_FLG = ' ';
    String WS_TERM = " ";
    TDZACDRU_REDEFINES30 REDEFINES30 = new TDZACDRU_REDEFINES30();
    double WS_DD_RAT = 0;
    short WS_I = 0;
    int WS_EXP_DATE = 0;
    int WS_DATE1 = 0;
    TDZACDRU_REDEFINES37 REDEFINES37 = new TDZACDRU_REDEFINES37();
    int WS_DATE2 = 0;
    TDZACDRU_REDEFINES42 REDEFINES42 = new TDZACDRU_REDEFINES42();
    TDZACDRU_WS_DATE3 WS_DATE3 = new TDZACDRU_WS_DATE3();
    short WS_MM = 0;
    int WS_DD = 0;
    short WS_COUNT_NUM = 0;
    short WS_CD_PERD = 0;
    short WS_COUNT_MM = 0;
    short WS_COUNT_DD = 0;
    TDZACDRU_WS_PRDCODE WS_PRDCODE = new TDZACDRU_WS_PRDCODE();
    char WS_AINT_FLG = ' ';
    double WS_CD_AMT = 0;
    short WS_SER_NO = 0;
    short WS_PWH_SEQ = 0;
    double WS_HMST_TX_AMT = 0;
    double WS_TOT_AMT = 0;
    double WS_BAL = 0;
    String WS_NRA_TAX_TYP = " ";
    char WS_SID_FLG = ' ';
    short WS_SER_TIME = 0;
    double WS_TXN_AMT_TMP = 0;
    char WS_OPT = ' ';
    double WS_CR_INT_AMT = 0;
    double WS_CR_INT_TAX = 0;
    double WS_PNLT_FEE = 0;
    double WS_PNLT_TAX = 0;
    char WS_LIT_FLG = ' ';
    char WS_DD_FLG = ' ';
    int WS_HDRW_DT = 0;
    String WS_CI_STSW = " ";
    String WS_MIN_CCYC = " ";
    double WS_MIN_AMTC = 0;
    double WS_MIN_DRAW_AMT = 0;
    double WS_MIN_LEFT_AMTC = 0;
    double WS_MAX_AMTC = 0;
    double WS_MIN_AMTC_USD = 0;
    double WS_MAX_AMTC_USD = 0;
    double WS_MIN_DRAW_AMT_USD = 0;
    double WS_MIN_LEFT_AMTC_USD = 0;
    double WS_BUY_AMT = 0;
    double WS_SELL_AMT = 0;
    String WS_RUL_CDC = " ";
    short WS_CNT = 0;
    String WS_VIA_AC = " ";
    String WS_MAC_CNO = " ";
    char WS_CI_TYP = ' ';
    int WS_CI_BBR = 0;
    String WS_BV_NO = " ";
    short WS_PSBK_POS = 0;
    int WS_CUR_LINE = 0;
    short WS_PAGES = 0;
    char WS_CALD_FLG = ' ';
    double WS_TEMP_RATE = 0;
    String WS_IRAT_CD = " ";
    char WS_CCY_FOUND = ' ';
    char WS_RATE_SEL = ' ';
    String WS_DOCU_NO = " ";
    TDZACDRU_WS_SAVRT_CODE WS_SAVRT_CODE = new TDZACDRU_WS_SAVRT_CODE();
    String WS_ACCU_INDUS1 = " ";
    String WS_ID_RGN = " ";
    String WS_ACCU_SRVLVL = " ";
    int WS_OPEN_BR = 0;
    char WS_OPP_CI_TYP = ' ';
    String WS_TXN_FUNC = " ";
    double WS_YBTP_INT_RAT = 0;
    double WS_YBTP_INT = 0;
    double WS_YBTP_INT_TX = 0;
    short WS_COUNT1 = 0;
    TDZACDRU_WS_TDRFHIS WS_TDRFHIS = new TDZACDRU_WS_TDRFHIS();
    double WS_INT_AMT_BK = 0;
    int WS_BRANCH_BR1 = 0;
    int WS_BRANCH_BR2 = 0;
    double WS_TEMP_NEW_RATE = 0;
    String WS_NE_IRAT_CD = " ";
    String WS_PR_IRAT_CD = " ";
    double WS_TRANS_INT = 0;
    short WS_ZS_NUM = 0;
    char WS_ZS_FLG = ' ';
    char WS_HIS_FLG = ' ';
    short WS_TEST_1 = 0;
    String WS_TEST_2 = " ";
    short WS_TEST_3 = 0;
    String WS_TEST_4 = " ";
    TDZACDRU_WS_REMARK WS_REMARK = new TDZACDRU_WS_REMARK();
    int WS_HMST_STR_DT = 0;
    int WS_HMST_END_DT = 0;
    double WS_SMST_AMT = 0;
    short WS_CCY_CNT = 0;
    char WS_ROLL_FLAG = ' ';
    char WS_MR_FLAG = ' ';
    char WS_PWH_FLAG = ' ';
    char WS_HMST_FLAG = ' ';
    char WS_SPE_FLG = ' ';
    String WS_CHNL_NO = " ";
    char WS_READ_IAACR_FLAG = ' ';
    char WS_READ_SPAC_FLAG = ' ';
    char WS_TDTDINT_FLG = ' ';
    char WS_TDTINST_FLG = ' ';
    char WS_SGXC_FLG = ' ';
    char WS_SMST_FLAG = ' ';
    char WS_OCAC_FLAG = ' ';
    char WS_DD_FLAG = ' ';
    char WS_TDTCALL_FLG = ' ';
    char PS_BOPWK = ' ';
    int CUSBRH = 0;
    int PS_DAT = 0;
    long BSY_FLW = 0;
    double PS_AMT = 0;
    String PS_OID = " ";
    String PS_PRA = " ";
    String PS_PRN = " ";
    char TDZACDRU_FILLER9 = 0X02;
    String PS_IID = " ";
    String PS_PEA = " ";
    String PS_PEN = " ";
    char TDZACDRU_FILLER13 = 0X02;
    String PS_PTL = " ";
    char IC_FLG = ' ';
    String PS_STA = " ";
    char PS_STU = ' ';
    String TRXOPR = " ";
    String BSYOSN = " ";
    String PS_OAD = " ";
    String PS_IAD = " ";
    char PS_EME = ' ';
    String PS_PRD = " ";
    char TDZACDRU_FILLER24 = 0X02;
    String PS_PED = " ";
    char TDZACDRU_FILLER26 = 0X02;
    String PS_RMK = " ";
    char TDZACDRU_FILLER28 = 0X02;
    String PS_ICD = " ";
    long ATDDAC = 0;
    String ATDDDA = " ";
    long ATDCAC = 0;
    String ATDCDA = " ";
    char PS_GXZ_FLG = ' ';
    String PS_RVS_NO = " ";
    String PS_RVS_AC = " ";
    String PS_REMARK = " ";
    char TDZACDRU_FILLER38 = 0X02;
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCHMPW SCCHMPW = new SCCHMPW();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCBINF SCCBINF = new SCCBINF();
    BPCPLDPD BPCPLDPD = new BPCPLDPD();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCFAMTA BPCFAMTA = new BPCFAMTA();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    BPCSLOSS BPCSLOSS = new BPCSLOSS();
    BPCUBUSE BPCUBUSE = new BPCUBUSE();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCPEBAS BPCPEBAS = new BPCPEBAS();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    BPCFTLAM BPCFTLAM = new BPCFTLAM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    TDRFHIS TDRFHIS = new TDRFHIS();
    TDRCMST TDRCMST = new TDRCMST();
    TDRDWHH TDRDWHH = new TDRDWHH();
    TDRSMST TDRSMST = new TDRSMST();
    CIRACRL CIRACRL = new CIRACRL();
    TDRPMPD TDRPMPD = new TDRPMPD();
    TDRGRGP TDRGRGP = new TDRGRGP();
    TDRIREV TDRIREV = new TDRIREV();
    TDRCDI TDRCDI = new TDRCDI();
    TDRYBTP TDRYBTP = new TDRYBTP();
    TDRSTS TDRSTS = new TDRSTS();
    TDRPBP TDRPBP = new TDRPBP();
    TDRBVT TDRBVT = new TDRBVT();
    TDRCALL TDRCALL = new TDRCALL();
    TDCCDINT TDCCDINT = new TDCCDINT();
    TDCCEINT TDCCEINT = new TDCCEINT();
    TDCPPRTF TDCPPRTF = new TDCPPRTF();
    TDCPRDP TDCPRDP = new TDCPRDP();
    TDCQINT TDCQINT = new TDCQINT();
    TDCQPAY TDCQPAY = new TDCQPAY();
    TDCIRULP TDCIRULP = new TDCIRULP();
    CICCUST CICCUST = new CICCUST();
    CICACCU CICACCU = new CICACCU();
    CICACCU CICACDD = new CICACCU();
    CICQACRI CICQACRI = new CICQACRI();
    CICQACAC CICQACAC = new CICQACAC();
    CICSACAC CICSACAC = new CICSACAC();
    CICMACR CICMACR = new CICMACR();
    DCCPCDCK DCCPCDCK = new DCCPCDCK();
    DPCPARMP DPCPARMP = new DPCPARMP();
    TDCOCDRU TDCOCDRU = new TDCOCDRU();
    BPCCINTI BPCCINTI = new BPCCINTI();
    TDRINST TDRINST = new TDRINST();
    TDCPWHT TDCPWHT = new TDCPWHT();
    TDRAINT TDRAINT = new TDRAINT();
    TDRROLL TDRROLL = new TDRROLL();
    TDRPWH TDRPWH = new TDRPWH();
    DCCIMSTR DCCIMSTR = new DCCIMSTR();
    DCCIMSTT DCCIMSTT = new DCCIMSTT();
    DCCUQSAC DCCUQSAC = new DCCUQSAC();
    DCCIACRD DCCIACRD = new DCCIACRD();
    DCCIACRA DCCIACRA = new DCCIACRA();
    DCCITRSR DCCITRSR = new DCCITRSR();
    TDCSAVRT TDCSAVRT = new TDCSAVRT();
    BPCPRMR BPCPRMR = new BPCPRMR();
    TDRPHIS TDRPHIS = new TDRPHIS();
    SCCTPCL SCCTPCL = new SCCTPCL();
    DDCSCINM DDCSCINM = new DDCSCINM();
    AICPQIA AICPQIA = new AICPQIA();
    AICUUPIA AICUUPIA = new AICUUPIA();
    DCCUCINF DCCUCINF = new DCCUCINF();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    BPCPRGST BPCPRGST = new BPCPRGST();
    TDCPRINT TDCPRINT = new TDCPRINT();
    TDCUPARM TDCUPARM = new TDCUPARM();
    DCRSPAC DCRSPAC = new DCRSPAC();
    TDRDINT TDRDINT = new TDRDINT();
    BPCTCALF BPCTCALF = new BPCTCALF();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    DCCPFTCK DCCPFTCK = new DCCPFTCK();
    TDROCAC TDROCAC = new TDROCAC();
    DCCUCACJ DCCUCACJ = new DCCUCACJ();
    BPCRDAMT BPCRDAMT = new BPCRDAMT();
    BPCFX BPCFX = new BPCFX();
    DDCIMCCY DDCIMCCY = new DDCIMCCY();
    BPCUNARR BPCUNARR = new BPCUNARR();
    DDCUCCAL DDCUCCAL = new DDCUCCAL();
    BPCPQORG BPCPQORG = new BPCPQORG();
    DCCIQHLD DCCIQHLD = new DCCIQHLD();
    TDCPIOD TDCPIOD = new TDCPIOD();
    BPCSOCAC BPCSOCAC = new BPCSOCAC();
    TDCACM TDCACM = new TDCACM();
    TDCTLML TDCTLML = new TDCTLML();
    DDCIMPAY DDCIMPAY = new DDCIMPAY();
    CICQACRL CICQACRL = new CICQACRL();
    CICSACRL CICSACRL = new CICSACRL();
    CICSACR CICSACR = new CICSACR();
    CICMLMT CICMLMT = new CICMLMT();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPLOSS BPCPLOSS = new BPCPLOSS();
    CICSAGEN CICSAGEN = new CICSAGEN();
    CICCKLS CICCKLS = new CICCKLS();
    CICSOEC CICSOEC = new CICSOEC();
    TDCACE TDCACE = new TDCACE();
    DDCIMMST DDCIMMST = new DDCIMMST();
    SCCGWA SCCGWA;
    BPCPORUP_DATA_INFO BPCPORUP;
    TDCACDRU TDCACDRU;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    CICGAGA_AGENT_AREA CICGAGA_AGENT_AREA;
    public void MP(SCCGWA SCCGWA, TDCACDRU TDCACDRU) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCACDRU = TDCACDRU;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, TDCACDRU.OPT);
        CEP.TRC(SCCGWA, TDCACDRU.CCY_TYP);
        CEP.TRC(SCCGWA, TDCACDRU.BUSI_CTLW);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, TDCACDRU.INT_RAT);
        CEP.TRC(SCCGWA, TDCACDRU.TXN_CHNL);
        CEP.TRC(SCCGWA, TDCACDRU.TXN_AMT);
        A000_INIT_PROC();
        if (pgmRtn) return;
        WS_MAC_CNO = TDCACDRU.MAC_CNO;
        IBS.init(SCCGWA, CICQACAC);
        if (TDCACDRU.ACO_AC.trim().length() > 0) {
            CICQACAC.FUNC = 'A';
            CICQACAC.DATA.ACAC_NO = TDCACDRU.ACO_AC;
        } else {
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = TDCACDRU.MAC_CNO;
            CICQACAC.DATA.AGR_SEQ = TDCACDRU.AC_SEQ;
            CEP.TRC(SCCGWA, CICQACAC.DATA.AGR_SEQ);
            CICQACAC.DATA.BV_NO = TDCACDRU.BV_NO;
        }
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        TDCACDRU.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        if (TDCACDRU.MAC_CNO.trim().length() == 0) {
            TDCACDRU.MAC_CNO = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        }
        if (TDCACDRU.AC_SEQ == 0) {
            TDCACDRU.AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
        }
        R000_GET_SMST_INFO();
        if (pgmRtn) return;
        if ((TDCACDRU.CCY.trim().length() > 0) 
            && (!TDCACDRU.CCY.equalsIgnoreCase(TDRSMST.CCY))) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CCY_I_ERR);
        }
        if ((TDCACDRU.CCY_TYP != ' ') 
            && (TDCACDRU.CCY_TYP != TDRSMST.CCY_TYP)) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CCY_TYP_ERROR);
        }
        WS_HOLD_INT_AMT = TDRSMST.HOLD_INT_AMT;
        WS_HOLD_TAX_AMT = TDRSMST.HOLD_TAX_AMT;
        if (TDRSMST.CCY_TYP == '2' 
            && TDCACDRU.STL_MTH == '0') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_REM_AC_CASH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDRSMST.CCY_TYP != TDCACDRU.CCY_TYP 
            && TDCACDRU.CCY_TYP != ' ') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_CCY_TYP_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
        JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (!TDCACDRU.BUSI_CTLW.substring(0, 1).equalsIgnoreCase("1") 
            && TDRSMST.STSW.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_UNCHK;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(51 - 1, 51 + 1 - 1).equalsIgnoreCase("1") 
            && (!TDRSMST.PRDAC_CD.equalsIgnoreCase("035") 
            && !TDRSMST.PRDAC_CD.equalsIgnoreCase("036"))) {
            if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
            JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
            TDCACDRU.BUSI_CTLW = TDCACDRU.BUSI_CTLW.substring(0, 4 - 1) + "1" + TDCACDRU.BUSI_CTLW.substring(4 + 1 - 1);
        }
        if ((TDRSMST.PRDAC_CD.equalsIgnoreCase("033")) 
            && (TDCACDRU.OPT != '8' 
            && TDCACDRU.OPT != '9' 
            && TDCACDRU.OPT != '5' 
            && TDCACDRU.OPT != 'A')) {
            if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
            JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
            if (!TDCACDRU.BUSI_CTLW.substring(0, 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_ERR_OPT);
            } else {
                if (TDCACDRU.OPT == '1') {
                    TDCACDRU.OPT = '8';
                }
            }
        }
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        CEP.TRC(SCCGWA, TDRSMST.STSW.substring(51 - 1, 51 + 1 - 1));
        CEP.TRC(SCCGWA, TDCACDRU.BV_TYP);
        if (TDCACDRU.BV_TYP == '4' 
            || TDRSMST.BV_TYP == '4') {
            if (TDRSMST.BV_TYP == '4') {
                TDCACDRU.BV_TYP = TDRSMST.BV_TYP;
            }
            IBS.init(SCCGWA, TDRCMST);
            if (TDCACDRU.AC_TRK2.trim().length() == 0 
                && TDCACDRU.AC_TRK3.trim().length() == 0) {
                TDCACDRU.MAC_CNO = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
            }
        } else {
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (!TDRSMST.STSW.substring(51 - 1, 51 + 1 - 1).equalsIgnoreCase("1")) {
                R000_GET_CMST_INFO();
                if (pgmRtn) return;
            } else {
                IBS.init(SCCGWA, TDRCMST);
                if (TDRSMST.PRDAC_CD.equalsIgnoreCase("035") 
                    || TDRSMST.PRDAC_CD.equalsIgnoreCase("036")) {
                    R000_GET_CMST_INFO();
                    if (pgmRtn) return;
                }
            }
        }
        CEP.TRC(SCCGWA, TDCACDRU.OPP_AC_CNO);
        if ((TDCACDRU.OPP_AC_CNO.trim().length() > 0) 
            && (TDCACDRU.INOUT != '2') 
            && (TDCACDRU.CT_FLG == '2' 
            || TDCACDRU.CT_FLG == '3' 
            || TDCACDRU.CT_FLG == '4')) {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.FUNC = 'A';
            CICQACRI.DATA.AGR_NO = TDCACDRU.OPP_AC_CNO;
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            TDCACDRU.OPP_AC_CNO = CICQACRI.O_DATA.O_AGR_NO;
            WS_OPP_CI_NO = CICQACRI.O_DATA.O_CI_NO;
            WS_OPP_CI_TYP = CICQACRI.O_DATA.O_CI_TYP;
        }
        R000_CHK_FRG_IND();
        if (pgmRtn) return;
        if (TDRSMST.PRDAC_CD.equalsIgnoreCase("033") 
            && TDCACDRU.STL_MTH == '3') {
            TDCACDRU.STL_MTH = '2';
        }
        if (TDCACDRU.STL_AC.trim().length() == 0) {
            TDCACDRU.STL_AC = TDCACDRU.OPP_AC_CNO;
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            R000_READ_TDTPWH_FOR_GD();
            if (pgmRtn) return;
        }
        if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
        JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
        if (TDCACDRU.BUSI_CTLW.substring(0, 1).equalsIgnoreCase("1")) {
            B000_READ_AC_BV();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        if (!BPCPQORG.TRA_TYP.equalsIgnoreCase("00") 
            && (TDCACE.PAGE_INF.FRG_IND.trim().length() == 0 
            || TDCACE.PAGE_INF.FRG_IND.equalsIgnoreCase("NRA"))) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_FTA);
        }
        if (BPCPQORG.TRA_TYP.equalsIgnoreCase("00") 
            && (TDCACE.PAGE_INF.FRG_IND.trim().length() > 0 
            && !TDCACE.PAGE_INF.FRG_IND.equalsIgnoreCase("NRA"))) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_NOT_FTA);
        }
        CEP.TRC(SCCGWA, BPCPQORG.ATTR);
        if (!TDCACDRU.CCY.equalsIgnoreCase("156")) {
            CEP.TRC(SCCGWA, BPCPQORG.FX_BUSI);
            if (BPCPQORG.FX_BUSI.equalsIgnoreCase("00")) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_TR_BR_NO_FX_AUTH);
            }
        }
        CEP.TRC(SCCGWA, "TSTMMO");
        CEP.TRC(SCCGWA, TDCACDRU.TXN_MMO);
        B015_CHECK_CI_LIST();
        if (pgmRtn) return;
        if (TDRSMST.INSTR_MTH != '0') {
            B011_GET_MAT_INST_INF();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            if (TDCACDRU.TXN_CHNL.equalsIgnoreCase("BSP")) {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_BSP_NO_CANCLE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if ((TDCACDRU.OPT == '0' 
                    || TDCACDRU.OPT == '6' 
                    || TDCACDRU.OPT == '9')) {
                WS_TXN_FUNC = "XHC";
                if (TDCACDRU.TXN_MMO.trim().length() == 0) {
                    B000_GET_MMO();
                    if (pgmRtn) return;
                }
                B000_MAIN_XH_PROC_CANCEL();
                if (pgmRtn) return;
                B300_DRAW_FEE_PROC();
                if (pgmRtn) return;
            } else if ((TDCACDRU.OPT == '1' 
                    || TDCACDRU.OPT == '7' 
                    || TDCACDRU.OPT == '8')) {
                WS_TXN_FUNC = "BTC";
                if (TDCACDRU.TXN_MMO.trim().length() == 0) {
                    B000_GET_MMO();
                    if (pgmRtn) return;
                }
                B000_MAIN_BT_PROC_CANCEL();
                if (pgmRtn) return;
                B300_DRAW_FEE_PROC();
                if (pgmRtn) return;
            } else if (TDCACDRU.OPT == '3') {
                WS_TXN_FUNC = "PIC";
                if (TDCACDRU.TXN_MMO.trim().length() == 0) {
                    B000_GET_MMO();
                    if (pgmRtn) return;
                }
                B000_MAIN_PI_PROC_CANCEL();
                if (pgmRtn) return;
                B300_DRAW_FEE_PROC();
                if (pgmRtn) return;
            } else if (TDCACDRU.OPT == '5'
                || TDCACDRU.OPT == 'A') {
                WS_TXN_FUNC = "XCC";
                if (TDCACDRU.TXN_MMO.trim().length() == 0) {
                    B000_GET_MMO();
                    if (pgmRtn) return;
                }
                B000_MAIN_XC_PROC_CANCEL();
                if (pgmRtn) return;
                B300_DRAW_FEE_PROC();
                if (pgmRtn) return;
            } else if (TDCACDRU.OPT == 'B') {
                WS_TXN_FUNC = "FBC";
                if (TDCACDRU.TXN_MMO.trim().length() == 0) {
                    B000_GET_MMO();
                    if (pgmRtn) return;
                }
                B000_MAIN_FB_PROC_CANCEL();
                if (pgmRtn) return;
                B300_DRAW_FEE_PROC();
                if (pgmRtn) return;
            } else {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_INVALID_OPT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            CEP.TRC(SCCGWA, TDCACDRU.OPT);
            if ((TDCACDRU.OPT == '0' 
                    || TDCACDRU.OPT == '6' 
                    || TDCACDRU.OPT == '9')) {
                WS_TXN_FUNC = "XH";
                if (TDCACDRU.TXN_MMO.trim().length() == 0) {
                    B000_GET_MMO();
                    if (pgmRtn) return;
                }
                B000_MAIN_XH_PROC();
                if (pgmRtn) return;
                B300_DRAW_FEE_PROC();
                if (pgmRtn) return;
            } else if ((TDCACDRU.OPT == '1' 
                    || TDCACDRU.OPT == '7' 
                    || TDCACDRU.OPT == '8')) {
                WS_TXN_FUNC = "BT";
                if (TDCACDRU.TXN_MMO.trim().length() == 0) {
                    B000_GET_MMO();
                    if (pgmRtn) return;
                }
                B000_MAIN_BT_PROC();
                if (pgmRtn) return;
                B300_DRAW_FEE_PROC();
                if (pgmRtn) return;
            } else if (TDCACDRU.OPT == '3') {
                WS_TXN_FUNC = "PI";
                if (TDCACDRU.TXN_MMO.trim().length() == 0) {
                    B000_GET_MMO();
                    if (pgmRtn) return;
                }
                B000_MAIN_PI_PROC();
                if (pgmRtn) return;
                B300_DRAW_FEE_PROC();
                if (pgmRtn) return;
            } else if (TDCACDRU.OPT == '5'
                || TDCACDRU.OPT == 'A') {
                WS_TXN_FUNC = "XC";
                if (TDCACDRU.TXN_MMO.trim().length() == 0) {
                    B000_GET_MMO();
                    if (pgmRtn) return;
                }
                B000_MAIN_XC_PROC();
                if (pgmRtn) return;
                B300_DRAW_FEE_PROC();
                if (pgmRtn) return;
            } else if (TDCACDRU.OPT == 'B') {
                WS_TXN_FUNC = "FB";
                if (TDCACDRU.TXN_MMO.trim().length() == 0) {
                    B000_GET_MMO();
                    if (pgmRtn) return;
                }
                B000_MAIN_FB_PROC();
                if (pgmRtn) return;
                B300_DRAW_FEE_PROC();
                if (pgmRtn) return;
            } else {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_INVALID_OPT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, TDCACDRU.DRAW_TOT_AMT);
        if (SCCGWA.COMM_AREA.AGENT_FLG == 'Y') {
            B180_AGENT_INF_PORC();
            if (pgmRtn) return;
        }
        TDCACDRU.PAYING_INT = TDCACDRU.PAYING_INT - TDCACDRU.PAYING_TAX;
        CEP.TRC(SCCGWA, TDCACDRU.PAYING_INT);
        CEP.TRC(SCCGWA, "TDZACDRU return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRGRGP);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        CICGAGA_AGENT_AREA = new CICGAGA_AGENT_AREA_AGENT_AREA();
        IBS.init(SCCGWA, CICGAGA_AGENT_AREA);
        IBS.CPY2CLS(SCCGWA, SCCGWA.AGENT_AREA_PTR, CICGAGA_AGENT_AREA);
        WS_DR_FLG = 'D';
        WS_HMST_TX_AMT = TDCACDRU.TXN_AMT;
        WS_MMO = TDCACDRU.TXN_MMO;
        WS_OPT = TDCACDRU.OPT;
    }
    public void B000_GET_MMO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_TXN_FUNC);
        if (WS_TXN_FUNC.equalsIgnoreCase("XH")) {
            WS_MMO = "C004";
        } else if (WS_TXN_FUNC.equalsIgnoreCase("XC")) {
            WS_MMO = "A017";
        } else if (WS_TXN_FUNC.equalsIgnoreCase("BT")) {
            WS_MMO = "C007";
            if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
            JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
            if (TDCACDRU.BUSI_CTLW.substring(0, 1).equalsIgnoreCase("1")) {
                WS_MMO = "A314";
            }
        } else if (WS_TXN_FUNC.equalsIgnoreCase("PI")) {
            WS_MMO = "S106";
        } else if (WS_TXN_FUNC.equalsIgnoreCase("XHC")) {
            WS_MMO = "G004";
        } else if (WS_TXN_FUNC.equalsIgnoreCase("XCC")) {
            WS_MMO = "G004";
        } else if (WS_TXN_FUNC.equalsIgnoreCase("BTC")) {
            WS_MMO = "G004";
        } else if (WS_TXN_FUNC.equalsIgnoreCase("PIC")) {
            WS_MMO = "G004";
        } else if (WS_TXN_FUNC.equalsIgnoreCase("FB")) {
            WS_MMO = "C003";
        } else if (WS_TXN_FUNC.equalsIgnoreCase("FBC")) {
            WS_MMO = "G004";
        }
        CEP.TRC(SCCGWA, WS_MMO);
    }
    public void B180_AGENT_INF_PORC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSAGEN);
        CICSAGEN.FUNC = 'A';
        CICSAGEN.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        CICSAGEN.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        CICSAGEN.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        CICSAGEN.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CICSAGEN.TLR_NO = SCCGWA.COMM_AREA.TL_ID;
        CICSAGEN.CI_NO = CICACCU.DATA.CI_NO;
        CICSAGEN.OUT_AC = TDCACDRU.MAC_CNO;
        CEP.TRC(SCCGWA, CICSAGEN.OUT_AC);
        CICSAGEN.ID_TYP = CICGAGA_AGENT_AREA.ID_TYP;
        CICSAGEN.ID_NO = CICGAGA_AGENT_AREA.ID_NO;
        CICSAGEN.AGENT_TP = "3";
        CICSAGEN.CI_NAME = CICGAGA_AGENT_AREA.CI_NM;
        CICSAGEN.PHONE = CICGAGA_AGENT_AREA.PHONE;
        S000_CALL_CIZSAGEN();
        if (pgmRtn) return;
    }
    public void B000_MAIN_XH_PROC() throws IOException,SQLException,Exception {
        if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
        JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
        if (!TDCACDRU.BUSI_CTLW.substring(0, 1).equalsIgnoreCase("1")) {
            B015_CHK_SMST_INFO();
            if (pgmRtn) return;
            if ((!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")) 
                && (TDRCMST.BV_TYP == '1' 
                || (TDRSMST.BV_TYP == '3' 
                || TDRSMST.BV_TYP == '7')) 
                && !SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_CHNL_IS_NOT_TLR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        B015_GET_CON_RATE();
        if (pgmRtn) return;
        B030_GET_PRD_INF();
        if (pgmRtn) return;
        B100_CHK_PRD_CODE();
        if (pgmRtn) return;
        B020_GET_CI_INF();
        if (pgmRtn) return;
        B110_CHK_PRD_PARM();
        if (pgmRtn) return;
        if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
        JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
        if (!TDCACDRU.BUSI_CTLW.substring(0, 1).equalsIgnoreCase("1")) {
            R000_CHK_SAME_CI();
            if (pgmRtn) return;
        }
        B110_CHK_PRD_PARM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.MID_FLG);
        CEP.TRC(SCCGWA, TDCACDRU.BUSI_CTLW);
        if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
        JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
        if (TDCPIOD.OTH_PRM.MID_FLG == 'N' 
            && SCCGWA.COMM_AREA.AC_DATE < TDRSMST.EXP_DATE 
            && !TDCACDRU.BUSI_CTLW.substring(0, 1).equalsIgnoreCase("1")) {
            if ((TDRSMST.FBAL != TDRSMST.BAL 
                && TDRSMST.PRDAC_CD.equalsIgnoreCase("037"))) {
            } else {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_NOT_EARLY);
            }
        }
        if (TDRSMST.ACO_STS != '0') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED);
        }
        if ((TDCACDRU.OPT != '6')) {
            if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
            JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
            if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
            JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
            if (DPCPARMP.AC_TYPE.equalsIgnoreCase("033") 
                && !TDCACDRU.BUSI_CTLW.substring(0, 1).equalsIgnoreCase("1") 
                && !TDCACDRU.BUSI_CTLW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
                && !SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_TGM_DRAW_AUTH;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW.substring(21 - 1, 21 + 1 - 1).equalsIgnoreCase("1") 
                || TDRSMST.STSW.substring(22 - 1, 22 + 1 - 1).equalsIgnoreCase("1") 
                || TDRSMST.STSW.substring(23 - 1, 23 + 1 - 1).equalsIgnoreCase("1")) {
                B016_READ_CDI();
                if (pgmRtn) return;
            }
            if ((TDRSMST.BV_TYP == '1' 
                || TDRSMST.BV_TYP == '2' 
                || TDRSMST.BV_TYP == '3' 
                || TDRSMST.BV_TYP == '7') 
                || (TDRCMST.BV_TYP == '1' 
                || TDRCMST.BV_TYP == '2' 
                || TDRCMST.BV_TYP == '3' 
                || TDRCMST.BV_TYP == '7')) {
                B017_READ_BVT();
                if (pgmRtn) return;
            }
            if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
            JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
            if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
            JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
            if (TDRSMST.BV_TYP == '3' 
                && !TDCACDRU.BUSI_CTLW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("3") 
                && !TDCACDRU.BUSI_CTLW.substring(0, 1).equalsIgnoreCase("1")) {
                if (TDRBVT.STSW == null) TDRBVT.STSW = "";
                JIBS_tmp_int = TDRBVT.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
                if (TDRBVT.STSW.substring(55 - 1, 55 + 1 - 1).equalsIgnoreCase("1")) {
                    CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CUNDAN_NOT_DRAW);
                }
            }
            if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
            JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
            if (!TDCACDRU.BUSI_CTLW.substring(0, 1).equalsIgnoreCase("1")) {
                B020_GET_CI_INF();
                if (pgmRtn) return;
            }
            B050_CHK_CI_INF();
            if (pgmRtn) return;
        }
        R000_GEN_TLMT_PROC();
        if (pgmRtn) return;
        if (TDRSMST.BAL != TDCACDRU.TXN_AMT) {
            CEP.TRC(SCCGWA, "19001");
            CEP.TRC(SCCGWA, TDRSMST.BAL);
            CEP.TRC(SCCGWA, TDCACDRU.TXN_AMT);
            WS_MSGID = TDCMSG_ERROR_MSG.TD_BAL_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDCACDRU.TXN_AMT < WS_MIN_LEFT_AMTC) {
            WS_DD_FLG = 'Y';
        }
        R000_GEN_HDRW_PROC();
        if (pgmRtn) return;
        if (!(TDRIREV.INT_SEL == '4' 
            && TDRIREV.CON_RATE == 0)) {
            B220_CAL_DRW_INT();
            if (pgmRtn) return;
        } else {
            TDCACDRU.DRAW_TOT_AMT = TDCACDRU.TXN_AMT;
        }
        if (TDCACDRU.STSW == null) TDCACDRU.STSW = "";
        JIBS_tmp_int = TDCACDRU.STSW.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) TDCACDRU.STSW += " ";
        if (TDCACDRU.STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
            TDCACDRU.DRAW_TOT_AMT = TDCACDRU.TXN_AMT;
        }
        WS_INT_AMT_BK = TDCCDINT.TRANS_INT;
        CEP.TRC(SCCGWA, TDCCDINT.RAT);
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(36 - 1, 36 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.init(SCCGWA, TDRAINT);
            TDRAINT.KEY.ACO_AC = TDCACDRU.ACO_AC;
            T000_READ_TDTAINT();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.AC_DATE < TDRSMST.EXP_DATE) {
                if (TDRAINT.PRV_RAT == 0) {
                    WS_MSGID = TDCMSG_ERROR_MSG.TD_NEED_PRV_RAT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
        JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
        if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            && !TDCACDRU.BUSI_CTLW.substring(0, 1).equalsIgnoreCase("1")) {
            if (TDRSMST.BV_TYP == '4') {
                B140_CHK_CARD_INF();
                if (pgmRtn) return;
            } else {
                if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
                JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
                for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
                if (!TDCACDRU.BUSI_CTLW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1")) {
                    B150_CHK_DRAW_MTH();
                    if (pgmRtn) return;
                }
            }
        }
        if (TDRSMST.BV_TYP == '4') {
            R000_CHECK_CARD_PROC();
            if (pgmRtn) return;
            IBS.init(SCCGWA, DCCUCINF);
            DCCUCINF.CARD_NO = TDCACDRU.MAC_CNO;
            S000_CALL_DCZUCINF();
            if (pgmRtn) return;
            if (DCCUCINF.CARD_STS == 'C') {
                WS_MSGID = DCCMSG_ERROR_MSG.DC_CARD_NOT_NORMAL_STS;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        B130_CHK_STS_TBL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        B260_WRT_VCH_EVENT();
        if (pgmRtn) return;
        B270_WRT_FHIS();
        if (pgmRtn) return;
        B280_REG_CLOSE();
        if (pgmRtn) return;
        B320_REG_ETAB();
        if (pgmRtn) return;
        if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
        JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
        if (!TDCACDRU.BUSI_CTLW.substring(0, 1).equalsIgnoreCase("1") 
            && (TDCACDRU.DRAW_TOT_AMT > DPCPARMP.LARGE_AMT)) {
            B290_REG_LARGE_AMT();
            if (pgmRtn) return;
        }
        B500_WRITE_TDTPWH();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        B500_REWRT_SMST_XH();
        if (pgmRtn) return;
        if (TDCACDRU.STSW == null) TDCACDRU.STSW = "";
        JIBS_tmp_int = TDCACDRU.STSW.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) TDCACDRU.STSW += " ";
        CEP.TRC(SCCGWA, TDCACDRU.STSW.substring(8 - 1, 8 + 1 - 1));
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDCACDRU.STSW == null) TDCACDRU.STSW = "";
        JIBS_tmp_int = TDCACDRU.STSW.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) TDCACDRU.STSW += " ";
        if (TDRSMST.STSW.substring(22 - 1, 22 + 1 - 1).equalsIgnoreCase("1") 
            && !TDCACDRU.STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("2")) {
            B501_WRT_TDTPHIS();
            if (pgmRtn) return;
        }
        if ((TDRCMST.BV_TYP == '0' 
            || TDRCMST.BV_TYP == '1' 
            || TDRCMST.BV_TYP == '2' 
            || TDRCMST.BV_TYP == '3' 
            || TDRCMST.BV_TYP == '7' 
            || TDRCMST.BV_TYP == '8')) {
            B530_UPDATE_MAIN_AC_INFO_XH();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        }
        if (TDRCMST.BV_TYP == '1') {
            B550_REWRT_YBTP();
            if (pgmRtn) return;
            if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
            JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
            if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")) 
                && !TDCACDRU.BUSI_CTLW.substring(0, 1).equalsIgnoreCase("1") 
                && TDCACDRU.PRT_FLG == '0') {
                B600_OUTPUT_YBTP();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        if (TDRCMST.BV_TYP == '2') {
            TDRBVT.PSBK_POS += 1;
            T000_REWRITE_BVT();
            if (pgmRtn) return;
            B570_WRT_PBP_XH();
            if (pgmRtn) return;
            if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
            JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
            if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")) 
                && !TDCACDRU.BUSI_CTLW.substring(0, 1).equalsIgnoreCase("1") 
                && TDCACDRU.PRT_FLG == '0') {
                B610_OUTPUT_PBP();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, TDRSMST.PRDAC_CD);
        CEP.TRC(SCCGWA, WS_CI_TYP);
        CEP.TRC(SCCGWA, TDRSMST.OPEN_DR_AC);
        CEP.TRC(SCCGWA, "PR0189");
        if (TDRSMST.PRDAC_CD.equalsIgnoreCase("021") 
            && WS_CI_TYP == '1' 
            && TDRSMST.BV_TYP != '4') {
            IBS.init(SCCGWA, CICSACRL);
            CICSACRL.FUNC = 'D';
            CICSACRL.DATA.AC_NO = TDCACDRU.MAC_CNO;
            CICSACRL.DATA.AC_REL = "07";
            IBS.init(SCCGWA, CICQACRL);
            CICQACRL.FUNC = 'I';
            CICQACRL.DATA.AC_NO = TDCACDRU.MAC_CNO;
            CICQACRL.DATA.AC_REL = "07";
            S000_CALL_CIZQACRL();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
            if (CICQACRL.O_DATA.O_REL_AC_NO.trim().length() > 0) {
                CICSACRL.DATA.REL_AC_NO = CICQACRL.O_DATA.O_REL_AC_NO;
                S000_CALL_CIZSACRL();
                if (pgmRtn) return;
            }
        }
        B250_GEN_CI_AC_INF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
            if (TDRSMST.BV_TYP == '3' 
                || TDRSMST.BV_TYP == '6' 
                || TDRSMST.BV_TYP == '7' 
                || TDRSMST.BV_TYP == '8') {
                B620_OUTPUT_PAY_INF();
                if (pgmRtn) return;
            }
            B650_OUTPUT_PROC();
            if (pgmRtn) return;
            B630_OUTPUT_INT_INF();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        CEP.TRC(SCCGWA, TDRSMST.PRDAC_CD);
        CEP.TRC(SCCGWA, WS_CI_TYP);
        CEP.TRC(SCCGWA, TDRSMST.OPEN_DR_AC);
        if (!TDRSMST.PRDAC_CD.equalsIgnoreCase("035") 
            && !TDRSMST.PRDAC_CD.equalsIgnoreCase("036")) {
            B630_WRT_BPTOCAC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
        JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
        if (TDCACDRU.BUSI_CTLW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("2")) {
            B160_GET_LOSS_NO();
            if (pgmRtn) return;
            B161_CHECK_LOS_BR();
            if (pgmRtn) return;
            B165_UPD_LOSS_INFO();
            if (pgmRtn) return;
            B230_WRI_NFIN_HIS_PROC();
            if (pgmRtn) return;
        }
        if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
        JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
        if (!TDCACDRU.BUSI_CTLW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("9") 
            && TDRSMST.PRDAC_CD.equalsIgnoreCase("035")) {
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.AC_NO = TDCACDRU.MAC_CNO;
            T000_READ_SMST_MR();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                if (TDRSMST.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
                    || TDRSMST.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
                    || TDRSMST.STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1") 
                    || TDRSMST.STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
                    || TDRSMST.HBAL > 0) {
                    CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_STS_NOT_NORMAL);
                }
            }
            IBS.init(SCCGWA, CICQACAC);
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.AC_NO = TDCACDRU.MAC_CNO;
            T000_READ_TDTSMSTMR();
            if (pgmRtn) return;
            if (WS_MR_FLAG == 'F') {
                T000_GROUP_TDTSMST();
                if (pgmRtn) return;
                CICQACAC.FUNC = 'R';
                CICQACAC.DATA.AGR_NO = TDCACDRU.MAC_CNO;
                S000_CALL_CIZQACAC();
                if (pgmRtn) return;
                IBS.init(SCCGWA, TDRSMST);
                TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
                T000_READU_SMST();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, "UPDATE BAL");
                CEP.TRC(SCCGWA, TDRSMST.BAL);
                TDRSMST.BAL = WS_SMST_AMT;
                T000_REWRITE_SMST();
                if (pgmRtn) return;
            } else {
                CICQACAC.FUNC = 'R';
                CICQACAC.DATA.AGR_NO = TDCACDRU.MAC_CNO;
                S000_CALL_CIZQACAC();
                if (pgmRtn) return;
                IBS.init(SCCGWA, TDRSMST);
                TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
                T000_READU_SMST();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, "UPDATE BAL");
                CEP.TRC(SCCGWA, TDRSMST.BAL);
                TDRSMST.BAL = 0;
                T000_REWRITE_SMST();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
    }
    public void B160_GET_LOSS_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSTS);
        TDRSTS.KEY.AC_NO = TDRSMST.AC_NO;
        TDRSTS.KEY.AC_SEQ = TDCACDRU.AC_SEQ;
        TDRSTS.KEY.TYPE = '2';
        TDRSTS.KEY.POS = 3;
        TDRSTS.STS = '1';
        T000_READ_TDTSTS();
        if (pgmRtn) return;
    }
    public void B161_CHECK_LOS_BR() throws IOException,SQLException,Exception {
        if (TDRSTS.REQ_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH 
            && TDRSTS.REQ_BR != BPCPORUP.DATA_INFO.BBR 
            && TDRSTS.REQ_BR != 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_BR_INPUT_ERR);
        }
    }
    public void B165_UPD_LOSS_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSLOSS);
        BPCSLOSS.FUNC = 'U';
        if (TDRSTS.REMARK == null) TDRSTS.REMARK = "";
        JIBS_tmp_int = TDRSTS.REMARK.length();
        for (int i=0;i<120-JIBS_tmp_int;i++) TDRSTS.REMARK += " ";
        BPCSLOSS.LOS_NO = TDRSTS.REMARK.substring(0, 20);
        BPCSLOSS.STS = '6';
        BPCSLOSS.RLOS_ORG = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCSLOSS.RLOS_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSLOSS.RLOS_DT = SCCGWA.COMM_AREA.TR_DATE;
        BPCSLOSS.RLOS_TIME = SCCGWA.COMM_AREA.TR_TIME;
        S000_CALL_BPZSLOSS();
        if (pgmRtn) return;
    }
    public void B000_MAIN_XH_PROC_CANCEL() throws IOException,SQLException,Exception {
        B015_PREPARE_INFO_XH_CANCEL();
        if (pgmRtn) return;
        B030_GET_PRD_INF();
        if (pgmRtn) return;
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(21 - 1, 21 + 1 - 1).equalsIgnoreCase("1") 
            || TDRSMST.STSW.substring(22 - 1, 22 + 1 - 1).equalsIgnoreCase("1") 
            || TDRSMST.STSW.substring(23 - 1, 23 + 1 - 1).equalsIgnoreCase("1")) {
            B016_READ_CDI();
            if (pgmRtn) return;
        }
        if ((TDRSMST.BV_TYP == '1' 
            || TDRSMST.BV_TYP == '2' 
            || TDRSMST.BV_TYP == '3' 
            || TDRSMST.BV_TYP == '7') 
            || (TDRCMST.BV_TYP == '1' 
            || TDRCMST.BV_TYP == '2' 
            || TDRCMST.BV_TYP == '3' 
            || TDRCMST.BV_TYP == '7')) {
            B017_READ_BVT();
            if (pgmRtn) return;
        }
        B020_GET_CI_INF();
        if (pgmRtn) return;
        B100_CHK_PRD_CODE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TYJTEST2");
        B110_CHK_PRD_PARM();
        if (pgmRtn) return;
        B050_CHK_CI_INF();
        if (pgmRtn) return;
        if (TDRSMST.BV_TYP == '4') {
            R000_CHECK_CARD_PROC();
            if (pgmRtn) return;
            IBS.init(SCCGWA, DCCUCINF);
            DCCUCINF.CARD_NO = TDCACDRU.MAC_CNO;
            S000_CALL_DCZUCINF();
            if (pgmRtn) return;
            if (DCCUCINF.CARD_STS == 'C') {
                WS_MSGID = DCCMSG_ERROR_MSG.DC_CARD_NOT_NORMAL_STS;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        B130_CHK_STS_TBL();
        if (pgmRtn) return;
        R000_GEN_TLMT_PROC();
        if (pgmRtn) return;
        if (DPCPARMP.AC_TYPE.equalsIgnoreCase("022")) {
            if (!(TDRIREV.INT_SEL == '4' 
                && TDRIREV.CON_RATE == 0)) {
                B220_CAL_DRW_INT();
                if (pgmRtn) return;
            }
        }
        B500_UPDATE_TDTPWH();
        if (pgmRtn) return;
        B270_WRT_FHIS();
        if (pgmRtn) return;
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(22 - 1, 22 + 1 - 1).equalsIgnoreCase("1")) {
            B501_UPDATE_TDTPHIS_CAN();
            if (pgmRtn) return;
        }
        B280_REG_CLOSE_CANCEL();
        if (pgmRtn) return;
        if ((!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")) 
            && TDCACDRU.CT_FLG == '4') {
            B320_REG_ETAB_CANCEL();
            if (pgmRtn) return;
        }
        if (TDCACDRU.DRAW_TOT_AMT > DPCPARMP.LARGE_AMT) {
            B290_REG_LARGE_AMT_CANCEL();
            if (pgmRtn) return;
        }
        B500_REWRT_SMST_XH_CANCEL();
        if (pgmRtn) return;
        if ((TDRCMST.BV_TYP == '0' 
            || TDRCMST.BV_TYP == '1' 
            || TDRCMST.BV_TYP == '2' 
            || TDRCMST.BV_TYP == '3' 
            || TDRCMST.BV_TYP == '8' 
            || TDRCMST.BV_TYP == '7')) {
            B530_UPD_MAIN_AC_INF_XH_CAN();
            if (pgmRtn) return;
        }
        if (TDRCMST.BV_TYP == '1') {
            TDRBVT.PSBK_POS += 2;
            T000_REWRITE_BVT();
            if (pgmRtn) return;
            B560_WRT_YBTP();
            if (pgmRtn) return;
        }
        if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
        JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
        if (TDCACDRU.BUSI_CTLW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("2")) {
            B160_GET_LOSS_NO();
            if (pgmRtn) return;
            B161_CHECK_LOS_BR();
            if (pgmRtn) return;
            B165_UPD_LOSS_INFO();
            if (pgmRtn) return;
            B230_WRI_NFIN_HIS_PROC();
            if (pgmRtn) return;
        }
        if (TDRCMST.BV_TYP == '2') {
            TDRBVT.PSBK_POS += 1;
            T000_REWRITE_BVT();
            if (pgmRtn) return;
            B570_WRT_PBP_XH_CANCEL();
            if (pgmRtn) return;
        }
        B250_GEN_CI_AC_INF();
        if (pgmRtn) return;
        TDCACDRU.DRAW_TOT_AMT = TDCACDRU.TXN_AMT + TDRPWH.INT_AMT_PY - TDRPWH.INT_AMT_TX + TDRSMST.CR_INT_AMT - TDRSMST.DR_INT_AMT + TDRPWH.PNLT_FEE - TDRPWH.PNLT_TAX;
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(22 - 1, 22 + 1 - 1).equalsIgnoreCase("1") 
            || TDRSMST.STSW.substring(23 - 1, 23 + 1 - 1).equalsIgnoreCase("1")) {
            TDCACDRU.DRAW_TOT_AMT = TDCACDRU.DRAW_TOT_AMT - TDRPWH.INT_AMT_BK;
        }
        TDCACDRU.PAYING_INT = TDRPWH.INT_AMT_PY + TDRSMST.CR_INT_AMT - TDRSMST.DR_INT_AMT + TDRPWH.PNLT_FEE;
        TDCACDRU.PAYING_TAX = TDRPWH.INT_AMT_TX + TDRPWH.PNLT_TAX;
        R000_UPDATE_TDTDINT_CANCEL();
        if (pgmRtn) return;
        if ((DPCPARMP.AC_TYPE.equalsIgnoreCase("022") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("023") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("036"))) {
            R000_UPDATE_TDTCALL_CANCEL();
            if (pgmRtn) return;
        }
        B631_OUTPUT_PERD_INT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDRSMST.PRDAC_CD);
        CEP.TRC(SCCGWA, WS_CI_TYP);
        CEP.TRC(SCCGWA, TDRSMST.OPEN_DR_AC);
        if (TDRSMST.PRDAC_CD.equalsIgnoreCase("021") 
            && WS_CI_TYP == '1') {
            IBS.init(SCCGWA, CIRACRL);
            CIRACRL.KEY.AC_NO = TDRSMST.AC_NO;
            CIRACRL.KEY.AC_REL = "07";
            T000_STARTBR_CITACRL();
            if (pgmRtn) return;
            T000_READNEXT_CITACRL();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                IBS.init(SCCGWA, CICSACRL);
                CICSACRL.FUNC = 'A';
                CICSACRL.DATA.AC_NO = TDCACDRU.MAC_CNO;
                CICSACRL.DATA.REL_AC_NO = TDRSMST.OPEN_DR_AC;
                CICSACRL.DATA.REL_AC_NO = CIRACRL.KEY.REL_AC_NO;
                CICSACRL.DATA.AC_REL = "07";
                S000_CALL_CIZSACRL();
                if (pgmRtn) return;
                T000_ENDBR_CITACRL();
                if (pgmRtn) return;
            }
        }
        if (!TDRSMST.PRDAC_CD.equalsIgnoreCase("035") 
            && !TDRSMST.PRDAC_CD.equalsIgnoreCase("036")) {
            B630_WRT_BPTOCAC_CANCEL();
            if (pgmRtn) return;
        }
    }
    public void B000_MAIN_BT_PROC() throws IOException,SQLException,Exception {
        if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
        JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
        if (!TDCACDRU.BUSI_CTLW.substring(0, 1).equalsIgnoreCase("1")) {
            B015_CHK_SMST_INFO();
            if (pgmRtn) return;
            if ((!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")) 
                && (TDRCMST.BV_TYP == '1' 
                || (TDRSMST.BV_TYP == '3' 
                || TDRSMST.BV_TYP == '7'))) {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_CHNL_IS_NOT_TLR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        B015_GET_CON_RATE();
        if (pgmRtn) return;
        B030_GET_PRD_INF();
        if (pgmRtn) return;
        B100_CHK_PRD_CODE();
        if (pgmRtn) return;
        B020_GET_CI_INF();
        if (pgmRtn) return;
        B110_CHK_PRD_PARM();
        if (pgmRtn) return;
        if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
        JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
        if (!TDCACDRU.BUSI_CTLW.substring(0, 1).equalsIgnoreCase("1")) {
            R000_CHK_SAME_CI();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, TDRSMST.BAL);
        CEP.TRC(SCCGWA, TDCACDRU.TXN_AMT);
        if (TDRSMST.PRDAC_CD.equalsIgnoreCase("037") 
            && TDRSMST.ACO_STS != '0') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_STS_NOT_NORMAL);
        }
        if (TDRSMST.BAL < TDCACDRU.TXN_AMT) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_BTA_TOO_LARGE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
        JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
        if (TDRSMST.BAL == TDCACDRU.TXN_AMT 
            && !TDCACDRU.BUSI_CTLW.substring(0, 1).equalsIgnoreCase("1")) {
            if (TDRSMST.PRDAC_CD.equalsIgnoreCase("035") 
                || TDRSMST.PRDAC_CD.equalsIgnoreCase("036")) {
            } else {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_BTA_TOO_LARGE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, TDRSMST.EXP_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
        JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
        if (!TDCACDRU.BUSI_CTLW.substring(0, 1).equalsIgnoreCase("1") 
            && TDRSMST.EXP_DATE <= SCCGWA.COMM_AREA.AC_DATE 
            && TDRSMST.EXP_DATE != 0 
            && !DPCPARMP.AC_TYPE.equalsIgnoreCase("033") 
            && !(DPCPARMP.AC_TYPE.equalsIgnoreCase("021") 
            && WS_CI_TYP == '1' 
            && TDRSMST.HBAL > 0) 
            && !(DPCPARMP.AC_TYPE.equalsIgnoreCase("037") 
            && (WS_CI_TYP == '2' 
            || WS_CI_TYP == '3') 
            && TDRSMST.HBAL > 0 
            && TDCACDRU.TXN_AMT == TDRSMST.BAL - TDRSMST.HBAL )) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_EXP_N_BT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDCACDRU.OPT != '7') {
            if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
            JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
            if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
            JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
            if (DPCPARMP.AC_TYPE.equalsIgnoreCase("033") 
                && !TDCACDRU.BUSI_CTLW.substring(0, 1).equalsIgnoreCase("1") 
                && !TDCACDRU.BUSI_CTLW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
                && !SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_TGM_DRAW_AUTH;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW.substring(21 - 1, 21 + 1 - 1).equalsIgnoreCase("1") 
                || TDRSMST.STSW.substring(22 - 1, 22 + 1 - 1).equalsIgnoreCase("1") 
                || TDRSMST.STSW.substring(23 - 1, 23 + 1 - 1).equalsIgnoreCase("1")) {
                B016_READ_CDI();
                if (pgmRtn) return;
            }
            if ((TDRSMST.BV_TYP == '1' 
                || TDRSMST.BV_TYP == '2' 
                || TDRSMST.BV_TYP == '3' 
                || TDRSMST.BV_TYP == '7') 
                || (TDRCMST.BV_TYP == '1' 
                || TDRCMST.BV_TYP == '2' 
                || TDRCMST.BV_TYP == '3' 
                || TDRCMST.BV_TYP == '7')) {
                B017_READ_BVT();
                if (pgmRtn) return;
            }
            B050_CHK_CI_INF();
            if (pgmRtn) return;
            if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
            JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
            if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
            JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
            if (TDRSMST.BV_TYP == '3' 
                && !TDCACDRU.BUSI_CTLW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("3") 
                && !TDCACDRU.BUSI_CTLW.substring(0, 1).equalsIgnoreCase("1")) {
                if (TDRBVT.STSW == null) TDRBVT.STSW = "";
                JIBS_tmp_int = TDRBVT.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
                if (TDRBVT.STSW.substring(55 - 1, 55 + 1 - 1).equalsIgnoreCase("1")) {
                    CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CUNDAN_NOT_DRAW);
                }
            }
        }
        R000_GEN_TLMT_PROC();
        if (pgmRtn) return;
        if (!(TDRIREV.INT_SEL == '4' 
            && TDRIREV.CON_RATE == 0)) {
            R000_GET_BUD_INT_BT();
            if (pgmRtn) return;
        }
        R000_GEN_HDRW_PROC();
        if (pgmRtn) return;
        if (!(TDRIREV.INT_SEL == '4' 
            && TDRIREV.CON_RATE == 0)) {
            B220_CAL_DRW_INT();
            if (pgmRtn) return;
        } else {
            TDCACDRU.DRAW_TOT_AMT = TDCACDRU.TXN_AMT;
        }
        if (TDRSMST.PRDAC_CD.equalsIgnoreCase("035")) {
            TDCACDRU.DRAW_TOT_AMT = TDCACDRU.TXN_AMT;
        }
        CEP.TRC(SCCGWA, TDCCDINT.PAYING_INT);
        CEP.TRC(SCCGWA, TDCCDINT.TRANS_INT);
        WS_INT_AMT_BK = TDCCDINT.TRANS_INT;
        if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
        JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
        if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            && !TDCACDRU.BUSI_CTLW.substring(0, 1).equalsIgnoreCase("1")) {
            if (TDRSMST.BV_TYP == '4') {
                B140_CHK_CARD_INF();
                if (pgmRtn) return;
            } else {
                if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
                JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
                for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
                if (!TDCACDRU.BUSI_CTLW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1")) {
                    B150_CHK_DRAW_MTH();
                    if (pgmRtn) return;
                }
            }
        }
        if (TDRSMST.BV_TYP == '4') {
            R000_CHECK_CARD_PROC();
            if (pgmRtn) return;
            IBS.init(SCCGWA, DCCUCINF);
            DCCUCINF.CARD_NO = TDCACDRU.MAC_CNO;
            S000_CALL_DCZUCINF();
            if (pgmRtn) return;
            if (DCCUCINF.CARD_STS == 'C') {
                WS_MSGID = DCCMSG_ERROR_MSG.DC_CARD_NOT_NORMAL_STS;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        B130_CHK_STS_TBL();
        if (pgmRtn) return;
        if ((!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")) 
            && TDCACDRU.CT_FLG == '4') {
            B320_REG_ETAB();
            if (pgmRtn) return;
        }
        B260_WRT_VCH_EVENT();
        if (pgmRtn) return;
        B270_WRT_FHIS();
        if (pgmRtn) return;
        if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
        JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
        if (!TDCACDRU.BUSI_CTLW.substring(0, 1).equalsIgnoreCase("1") 
            && (TDCACDRU.DRAW_TOT_AMT > DPCPARMP.LARGE_AMT)) {
            B290_REG_LARGE_AMT();
            if (pgmRtn) return;
        }
        if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
        JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
        if (!TDCACDRU.BUSI_CTLW.substring(0, 1).equalsIgnoreCase("1") 
            && ((TDRSMST.BV_TYP == '3' 
            || TDRSMST.BV_TYP == '7') 
            || (TDRCMST.BV_TYP == '3' 
            || TDRCMST.BV_TYP == '7')) 
            && !SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            && !(!(!(!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) 
            && TDCACDRU.NEW_BV_NO.trim().length() == 0)) {
            B200_BV_USE();
            if (pgmRtn) return;
        }
        if (TDRCMST.BV_TYP == '1') {
            B550_REWRT_YBTP();
            if (pgmRtn) return;
        }
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(40 - 1, 40 + 1 - 1).equalsIgnoreCase("1") 
            || TDRSMST.STSW.substring(41 - 1, 41 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.init(SCCGWA, TDRAINT);
            TDRAINT.KEY.ACO_AC = TDCACDRU.ACO_AC;
            T000_READ_TDTAINT();
            if (pgmRtn) return;
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (SCCGWA.COMM_AREA.AC_DATE < TDRSMST.EXP_DATE 
                && TDRSMST.STSW.substring(36 - 1, 36 + 1 - 1).equalsIgnoreCase("1")) {
                if (TDRAINT.PRV_RAT == 0) {
                    WS_MSGID = TDCMSG_ERROR_MSG.TD_NEED_PRV_RAT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (SCCGWA.COMM_AREA.AC_DATE > TDRSMST.EXP_DATE 
                && TDRSMST.STSW.substring(37 - 1, 37 + 1 - 1).equalsIgnoreCase("1")) {
                if (TDRAINT.OVE_RAT == 0) {
                    WS_MSGID = TDCMSG_ERROR_MSG.TD_NEED_OVR_RAT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        CEP.TRC(SCCGWA, TDCCDINT.OPT);
        if (TDCACDRU.STSW == null) TDCACDRU.STSW = "";
        JIBS_tmp_int = TDCACDRU.STSW.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) TDCACDRU.STSW += " ";
        CEP.TRC(SCCGWA, TDCACDRU.STSW.substring(8 - 1, 8 + 1 - 1));
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDCACDRU.STSW == null) TDCACDRU.STSW = "";
        JIBS_tmp_int = TDCACDRU.STSW.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) TDCACDRU.STSW += " ";
        if (TDRSMST.STSW.substring(22 - 1, 22 + 1 - 1).equalsIgnoreCase("1") 
            && TDCCDINT.OPT == '0' 
            && !TDCACDRU.STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("2")) {
            B501_WRT_TDTPHIS();
            if (pgmRtn) return;
        }
        B500_WRITE_TDTPWH();
        if (pgmRtn) return;
        B500_REWRT_SMST_BT();
        if (pgmRtn) return;
        if (TDRCMST.PROD_CD.trim().length() > 0) {
            B530_UPDATE_MAIN_AC_INFO_BT();
            if (pgmRtn) return;
        }
        if (TDRCMST.BV_TYP == '1') {
            TDRBVT.PSBK_POS += 2;
            T000_REWRITE_BVT();
            if (pgmRtn) return;
            B560_WRT_YBTP();
            if (pgmRtn) return;
            if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
            JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
            if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")) 
                && !TDCACDRU.BUSI_CTLW.substring(0, 1).equalsIgnoreCase("1") 
                && TDCACDRU.PRT_FLG == '0') {
                B600_OUTPUT_YBTP();
                if (pgmRtn) return;
            }
        }
        if (TDRCMST.BV_TYP == '2') {
            TDRBVT.PSBK_POS += 1;
            T000_REWRITE_BVT();
            if (pgmRtn) return;
            B570_WRT_PBP_BT();
            if (pgmRtn) return;
            if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
            JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
            if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")) 
                && !TDCACDRU.BUSI_CTLW.substring(0, 1).equalsIgnoreCase("1") 
                && TDCACDRU.PRT_FLG == '0') {
                B610_OUTPUT_PBP();
                if (pgmRtn) return;
            }
        }
        if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
            if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
            JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
            if (((TDRSMST.BV_TYP == '3' 
                || TDRSMST.BV_TYP == '7') 
                || (TDRCMST.BV_TYP == '3' 
                || TDRCMST.BV_TYP == '7')) 
                && !TDCACDRU.BUSI_CTLW.substring(0, 1).equalsIgnoreCase("1")) {
                B580_REWRT_NEW_BV();
                if (pgmRtn) return;
                B620_OUTPUT_PAY_INF();
                if (pgmRtn) return;
                B640_OUTPUT_NEW_BV();
                if (pgmRtn) return;
            }
            B650_OUTPUT_PROC();
            if (pgmRtn) return;
            B630_OUTPUT_INT_INF();
            if (pgmRtn) return;
        }
    }
    public void B000_MAIN_BT_PROC_CANCEL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCACDRU.BAL);
        B015_READU_SMST_OTH_CANCEL();
        if (pgmRtn) return;
        B030_GET_PRD_INF();
        if (pgmRtn) return;
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(21 - 1, 21 + 1 - 1).equalsIgnoreCase("1") 
            || TDRSMST.STSW.substring(22 - 1, 22 + 1 - 1).equalsIgnoreCase("1") 
            || TDRSMST.STSW.substring(23 - 1, 23 + 1 - 1).equalsIgnoreCase("1")) {
            B016_READU_CDI();
            if (pgmRtn) return;
        }
        if ((TDRSMST.BV_TYP == '1' 
            || TDRSMST.BV_TYP == '2' 
            || TDRSMST.BV_TYP == '3' 
            || TDRSMST.BV_TYP == '7') 
            || (TDRCMST.BV_TYP == '1' 
            || TDRCMST.BV_TYP == '2' 
            || TDRCMST.BV_TYP == '3' 
            || TDRCMST.BV_TYP == '7')) {
            B017_READ_BVT();
            if (pgmRtn) return;
            if ((TDRSMST.BV_TYP == '3' 
                || TDRSMST.BV_TYP == '7') 
                || (TDRCMST.BV_TYP == '2' 
                || TDRCMST.BV_TYP == '3' 
                || TDRCMST.BV_TYP == '7')) {
                if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
                JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
                for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
                if (!TDCACDRU.NEW_BV_NO.equalsIgnoreCase(TDRBVT.BV_NO) 
                    && !TDCACDRU.BUSI_CTLW.substring(0, 1).equalsIgnoreCase("1")) {
                    CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_BV_NO_NOT_MATCH);
                }
            }
        }
        B020_GET_CI_INF();
        if (pgmRtn) return;
        B100_CHK_PRD_CODE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TYJTEST4");
        B110_CHK_PRD_PARM();
        if (pgmRtn) return;
        B050_CHK_CI_INF();
        if (pgmRtn) return;
        if (TDRSMST.BV_TYP == '4') {
            R000_CHECK_CARD_PROC();
            if (pgmRtn) return;
            IBS.init(SCCGWA, DCCUCINF);
            DCCUCINF.CARD_NO = TDCACDRU.MAC_CNO;
            S000_CALL_DCZUCINF();
            if (pgmRtn) return;
            if (DCCUCINF.CARD_STS == 'C') {
                WS_MSGID = DCCMSG_ERROR_MSG.DC_CARD_NOT_NORMAL_STS;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        B130_CHK_STS_TBL();
        if (pgmRtn) return;
        if (DPCPARMP.AC_TYPE.equalsIgnoreCase("022")) {
            if (!(TDRIREV.INT_SEL == '4' 
                && TDRIREV.CON_RATE == 0)) {
                B220_CAL_DRW_INT();
                if (pgmRtn) return;
            }
        }
        R000_GEN_TLMT_PROC();
        if (pgmRtn) return;
        B270_WRT_FHIS();
        if (pgmRtn) return;
        B500_UPDATE_TDTPWH();
        if (pgmRtn) return;
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(22 - 1, 22 + 1 - 1).equalsIgnoreCase("1")) {
            B501_UPDATE_TDTPHIS_CAN();
            if (pgmRtn) return;
        }
        if (!(TDRIREV.INT_SEL == '4' 
            && TDRIREV.CON_RATE == 0)) {
            R000_GET_BUD_INT_BT();
            if (pgmRtn) return;
        }
        if (TDCACDRU.DRAW_TOT_AMT > DPCPARMP.LARGE_AMT) {
            B290_REG_LARGE_AMT_CANCEL();
            if (pgmRtn) return;
        }
        if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
        JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
        if (((TDRSMST.BV_TYP == '3' 
            || TDRSMST.BV_TYP == '7') 
            || (TDRCMST.BV_TYP == '3' 
            || TDRCMST.BV_TYP == '7')) 
            && !TDCACDRU.BUSI_CTLW.substring(0, 1).equalsIgnoreCase("1") 
            && !SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            && !(!(!(!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) 
            && TDCACDRU.NEW_BV_NO.trim().length() == 0)) {
            B200_BV_USE();
            if (pgmRtn) return;
        }
        if ((!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")) 
            && TDCACDRU.CT_FLG == '4') {
            B320_REG_ETAB_CANCEL();
            if (pgmRtn) return;
        }
        if (TDRCMST.BV_TYP == '1') {
            B550_REWRT_YBTP_CANCEL();
            if (pgmRtn) return;
        }
        TDCACDRU.DRAW_TOT_AMT = TDCACDRU.TXN_AMT + TDRPWH.INT_AMT_PY - TDRPWH.INT_AMT_TX + TDRPWH.PNLT_FEE - TDRPWH.PNLT_TAX;
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(22 - 1, 22 + 1 - 1).equalsIgnoreCase("1")) {
            TDCACDRU.DRAW_TOT_AMT = TDCACDRU.DRAW_TOT_AMT - TDRPWH.INT_AMT_BK;
        }
        TDCACDRU.PAYING_INT = TDRPWH.INT_AMT_PY;
        TDCACDRU.PAYING_TAX = TDRPWH.INT_AMT_TX;
        TDCACDRU.PAYING_INT = TDRPWH.INT_AMT_PY + TDRPWH.PNLT_FEE;
        TDCACDRU.PAYING_TAX = TDRPWH.INT_AMT_TX + TDRPWH.PNLT_TAX;
        B500_REWRT_SMST_BT_CANCEL();
        if (pgmRtn) return;
        if (TDRCMST.PROD_CD.trim().length() > 0) {
            B530_UPDATE_MAIN_AC_INFO_BT();
            if (pgmRtn) return;
        }
        if (TDRCMST.BV_TYP == '1') {
            TDRBVT.PSBK_POS += 2;
            T000_REWRITE_BVT();
            if (pgmRtn) return;
            B560_WRT_YBTP();
            if (pgmRtn) return;
        }
        if (TDRCMST.BV_TYP == '2') {
            TDRBVT.PSBK_POS += 1;
            T000_REWRITE_BVT();
            if (pgmRtn) return;
            B570_WRT_PBP_BT_CANCEL();
            if (pgmRtn) return;
        }
        if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
        JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
        if (((TDRSMST.BV_TYP == '3' 
            || TDRSMST.BV_TYP == '7') 
            || (TDRCMST.BV_TYP == '3' 
            || TDRCMST.BV_TYP == '7')) 
            && !TDCACDRU.BUSI_CTLW.substring(0, 1).equalsIgnoreCase("1")) {
            B580_REWRT_OLD_BV();
            if (pgmRtn) return;
        }
        R000_UPDATE_TDTDINT_CANCEL();
        if (pgmRtn) return;
        if ((DPCPARMP.AC_TYPE.equalsIgnoreCase("022") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("023") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("036"))) {
            R000_UPDATE_TDTCALL_CANCEL();
            if (pgmRtn) return;
        }
        B631_OUTPUT_PERD_INT();
        if (pgmRtn) return;
    }
    public void B000_MAIN_PI_PROC() throws IOException,SQLException,Exception {
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(39 - 1, 39 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_PAY_INNORMAL);
        }
        B015_CHK_SMST_INFO();
        if (pgmRtn) return;
        B015_GET_CON_RATE();
        if (pgmRtn) return;
        B030_GET_PRD_INF();
        if (pgmRtn) return;
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(22 - 1, 22 + 1 - 1).equalsIgnoreCase("1")) {
            B016_READU_CDI();
            if (pgmRtn) return;
            B017_REWRITE_CDI();
            if (pgmRtn) return;
        }
        if ((TDRSMST.BV_TYP == '1' 
            || TDRSMST.BV_TYP == '2' 
            || TDRSMST.BV_TYP == '3' 
            || TDRSMST.BV_TYP == '7') 
            || (TDRCMST.BV_TYP == '1' 
            || TDRCMST.BV_TYP == '2' 
            || TDRCMST.BV_TYP == '3' 
            || TDRCMST.BV_TYP == '7')) {
            B017_READ_BVT();
            if (pgmRtn) return;
        }
        B020_GET_CI_INF();
        if (pgmRtn) return;
        if (TDCACDRU.NAME.trim().length() == 0) {
            TDCACDRU.NAME = CICACCU.DATA.AC_CNM;
            if (CICACCU.DATA.AC_CNM.trim().length() == 0) {
                TDCACDRU.NAME = CICACCU.DATA.AC_ENM;
            }
        }
        CEP.TRC(SCCGWA, TDCACDRU.NAME);
        B100_CHK_PRD_CODE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TYJTEST5");
        B110_CHK_PRD_PARM();
        if (pgmRtn) return;
        R000_CHK_SAME_CI();
        if (pgmRtn) return;
        B050_CHK_CI_INF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AA");
        CEP.TRC(SCCGWA, TDCACDRU.PAYING_INT);
        if (!(TDRIREV.INT_SEL == '4' 
            && TDRIREV.CON_RATE == 0)) {
            B220_CAL_DRW_INT();
            if (pgmRtn) return;
        }
        if (TDCCDINT.PAYING_INT <= 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_NO_PAYING_INT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
        JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
        if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            && !TDCACDRU.BUSI_CTLW.substring(0, 1).equalsIgnoreCase("1")) {
            if (TDRSMST.BV_TYP == '4') {
                B140_CHK_CARD_INF();
                if (pgmRtn) return;
            } else {
                if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
                JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
                for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
                if (!TDCACDRU.BUSI_CTLW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1")) {
                    B150_CHK_DRAW_MTH();
                    if (pgmRtn) return;
                }
            }
        }
        if (TDRSMST.BV_TYP == '4') {
            R000_CHECK_CARD_PROC();
            if (pgmRtn) return;
            IBS.init(SCCGWA, DCCUCINF);
            DCCUCINF.CARD_NO = TDCACDRU.MAC_CNO;
            S000_CALL_DCZUCINF();
            if (pgmRtn) return;
            if (DCCUCINF.CARD_STS == 'C') {
                WS_MSGID = DCCMSG_ERROR_MSG.DC_CARD_NOT_NORMAL_STS;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        B130_CHK_STS_TBL();
        if (pgmRtn) return;
        if (TDCACDRU.INOUT == '2') {
            B030_CHK_INFORM_ZFMM();
            if (pgmRtn) return;
        }
        if ((!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")) 
            && TDCACDRU.CT_FLG == '4') {
            B320_REG_ETAB();
            if (pgmRtn) return;
        }
        R000_GET_BUD_INT_PI();
        if (pgmRtn) return;
        B260_WRT_VCH_EVENT();
        if (pgmRtn) return;
        if (TDCACDRU.DRAW_TOT_AMT > DPCPARMP.LARGE_AMT) {
            B290_REG_LARGE_AMT();
            if (pgmRtn) return;
        }
        B500_REWRT_SMST_PI();
        if (pgmRtn) return;
        B501_WRT_TDTPHIS();
        if (pgmRtn) return;
        if (TDRCMST.BV_TYP == '2') {
            TDRBVT.PSBK_POS += 1;
            T000_REWRITE_BVT();
            if (pgmRtn) return;
            B570_WRT_PBP_PI();
            if (pgmRtn) return;
            if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
            JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
            if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")) 
                && !TDCACDRU.BUSI_CTLW.substring(0, 1).equalsIgnoreCase("1") 
                && TDCACDRU.PRT_FLG == '0') {
                B610_OUTPUT_PBP();
                if (pgmRtn) return;
            }
        }
        if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
            B630_OUTPUT_INT_INF();
            if (pgmRtn) return;
        }
    }
    public void B000_MAIN_FB_PROC() throws IOException,SQLException,Exception {
        B030_GET_PRD_INF();
        if (pgmRtn) return;
        if ((TDRSMST.BV_TYP == '1' 
            || TDRSMST.BV_TYP == '2' 
            || TDRSMST.BV_TYP == '3' 
            || TDRSMST.BV_TYP == '7') 
            || (TDRCMST.BV_TYP == '1' 
            || TDRCMST.BV_TYP == '2' 
            || TDRCMST.BV_TYP == '3' 
            || TDRCMST.BV_TYP == '7')) {
            B017_READ_BVT();
            if (pgmRtn) return;
        }
        B020_GET_CI_INF();
        if (pgmRtn) return;
        B100_CHK_PRD_CODE();
        if (pgmRtn) return;
        B110_CHK_PRD_PARM();
        if (pgmRtn) return;
        B050_CHK_CI_INF();
        if (pgmRtn) return;
        R000_CHK_SAME_CI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDCACDRU.PAYING_INT);
        B016_READU_CDI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDRSMST.BAL);
        CEP.TRC(SCCGWA, TDRCDI.PLAN_NUM);
        CEP.TRC(SCCGWA, TDRCDI.NOR_NUM);
        CEP.TRC(SCCGWA, TDRCDI.CD_AMT);
        if (TDRSMST.BAL < ( TDRCDI.PLAN_NUM - TDRCDI.NOR_NUM ) * TDRCDI.CD_AMT - 1) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_ERR_HOLD_DRAW);
        }
        if (!(TDRIREV.INT_SEL == '4' 
            && TDRIREV.CON_RATE == 0)) {
            B220_CAL_DRW_INT();
            if (pgmRtn) return;
        }
        TDCCDINT.RAT = 0;
        B017_REWRITE_CDI();
        if (pgmRtn) return;
        if (TDCCDINT.PAYING_INT <= 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_NO_PAYING_INT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDCACDRU.OPT == 'B' 
            && DPCPARMP.AC_TYPE.equalsIgnoreCase("024")) {
            TDCACDRU.TXN_AMT = TDCCDINT.PAYING_INT;
            TDCCDINT.PAYING_INT = 0;
        }
        if (TDCACDRU.TXN_AMT > TDRSMST.BAL - TDRSMST.HBAL - TDRSMST.WBAL) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AMT_HOLD;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, TDCCDINT.PAYING_INT);
        if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
        JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
        if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            && !TDCACDRU.BUSI_CTLW.substring(0, 1).equalsIgnoreCase("1")) {
            if (TDRSMST.BV_TYP == '4') {
                B140_CHK_CARD_INF();
                if (pgmRtn) return;
            } else {
                if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
                JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
                for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
                if (!TDCACDRU.BUSI_CTLW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1")) {
                    B150_CHK_DRAW_MTH();
                    if (pgmRtn) return;
                }
            }
        }
        if (TDRSMST.BV_TYP == '4') {
            R000_CHECK_CARD_PROC();
            if (pgmRtn) return;
            IBS.init(SCCGWA, DCCUCINF);
            DCCUCINF.CARD_NO = TDCACDRU.MAC_CNO;
            S000_CALL_DCZUCINF();
            if (pgmRtn) return;
            if (DCCUCINF.CARD_STS == 'C') {
                WS_MSGID = DCCMSG_ERROR_MSG.DC_CARD_NOT_NORMAL_STS;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        B130_CHK_STS_TBL();
        if (pgmRtn) return;
        if ((!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")) 
            && TDCACDRU.CT_FLG == '4') {
            B320_REG_ETAB();
            if (pgmRtn) return;
        }
        B260_WRT_VCH_EVENT();
        if (pgmRtn) return;
        if (TDCACDRU.DRAW_TOT_AMT > DPCPARMP.LARGE_AMT) {
            B290_REG_LARGE_AMT();
            if (pgmRtn) return;
        }
        WS_DR_FLG = 'D';
        WS_HIS_AMT = TDCACDRU.TXN_AMT;
        B270_WRT_FHIS();
        if (pgmRtn) return;
        B500_REWRT_SMST_FB();
        if (pgmRtn) return;
        B500_WRITE_TDTPWH();
        if (pgmRtn) return;
        if (TDRCMST.BV_TYP == '2') {
            TDRBVT.PSBK_POS += 1;
            T000_REWRITE_BVT();
            if (pgmRtn) return;
            B570_WRT_PBP_PI();
            if (pgmRtn) return;
            if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
            JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
            if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")) 
                && !TDCACDRU.BUSI_CTLW.substring(0, 1).equalsIgnoreCase("1") 
                && TDCACDRU.PRT_FLG == '0') {
                B610_OUTPUT_PBP();
                if (pgmRtn) return;
            }
        }
        if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
            B630_OUTPUT_INT_INF();
            if (pgmRtn) return;
        }
    }
    public void B000_MAIN_FB_PROC_CANCEL() throws IOException,SQLException,Exception {
        B015_READU_SMST_OTH_CANCEL();
        if (pgmRtn) return;
        B030_GET_PRD_INF();
        if (pgmRtn) return;
        B016_READ_CDI();
        if (pgmRtn) return;
        if ((TDRSMST.BV_TYP == '1' 
            || TDRSMST.BV_TYP == '2' 
            || TDRSMST.BV_TYP == '3' 
            || TDRSMST.BV_TYP == '7') 
            || (TDRCMST.BV_TYP == '1' 
            || TDRCMST.BV_TYP == '2' 
            || TDRCMST.BV_TYP == '3' 
            || TDRCMST.BV_TYP == '7')) {
            B017_READ_BVT();
            if (pgmRtn) return;
        }
        B020_GET_CI_INF();
        if (pgmRtn) return;
        B100_CHK_PRD_CODE();
        if (pgmRtn) return;
        B110_CHK_PRD_PARM();
        if (pgmRtn) return;
        B050_CHK_CI_INF();
        if (pgmRtn) return;
        if (TDRSMST.BV_TYP == '4') {
            R000_CHECK_CARD_PROC();
            if (pgmRtn) return;
            IBS.init(SCCGWA, DCCUCINF);
            DCCUCINF.CARD_NO = TDCACDRU.MAC_CNO;
            S000_CALL_DCZUCINF();
            if (pgmRtn) return;
            if (DCCUCINF.CARD_STS == 'C') {
                WS_MSGID = DCCMSG_ERROR_MSG.DC_CARD_NOT_NORMAL_STS;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        B130_CHK_STS_TBL();
        if (pgmRtn) return;
        if ((!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")) 
            && TDCACDRU.CT_FLG == '4') {
            B320_REG_ETAB_CANCEL();
            if (pgmRtn) return;
        }
        if (TDCACDRU.DRAW_TOT_AMT > DPCPARMP.LARGE_AMT) {
            B290_REG_LARGE_AMT_CANCEL();
            if (pgmRtn) return;
        }
        T000_READU_CDI();
        if (pgmRtn) return;
        WS_BAL = TDCACDRU.TXN_AMT % TDRCDI.CD_AMT;
        WS_NUM = (short) ((TDCACDRU.TXN_AMT - WS_BAL) / TDRCDI.CD_AMT);
        TDRCDI.NOR_NUM = (short) (TDRCDI.NOR_NUM - WS_NUM);
        T000_REWRITE_CDI();
        if (pgmRtn) return;
        B500_REWRT_SMST_FB_CANCEL();
        if (pgmRtn) return;
        B500_WRITE_TDTPWH();
        if (pgmRtn) return;
        if (TDRCMST.BV_TYP == '2') {
            TDRBVT.PSBK_POS += 1;
            T000_REWRITE_BVT();
            if (pgmRtn) return;
            B570_WRT_PBP_FB_CANCEL();
            if (pgmRtn) return;
        }
        B270_WRT_FHIS();
        if (pgmRtn) return;
    }
    public void B501_WRT_TDTPHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRPHIS);
        TDRPHIS.KEY.ACO_AC = TDCACDRU.ACO_AC;
        TDRPHIS.KEY.SETT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRPHIS.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        TDRPHIS.TXN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRPHIS.BOOK_BR = TDRSMST.CHE_BR;
        TDRPHIS.SETT_CCY = TDCACDRU.CCY;
        CEP.TRC(SCCGWA, TDCACDRU.BAL);
        TDRPHIS.SETT_BAL = TDCACDRU.BAL;
        TDRPHIS.RAT_INT = TDCCDINT.RAT;
        TDRPHIS.TAX_RAT = TDCCDINT.TAX_RAT;
        TDRPHIS.STATUS = 'N';
        TDRPHIS.STR_DATE = WS_LAST_DEAL_DT;
        TDRPHIS.END_DATE = WS_NEXT_DEAL_DT;
        if (TDCACDRU.INOUT == '2') {
            TDRPHIS.SETT_STATE = '2';
            TDRPHIS.AI_ACNO = PS_RVS_AC;
            TDRPHIS.REV_NO = PS_RVS_NO;
        } else {
            TDRPHIS.SETT_STATE = '1';
            TDRPHIS.AI_ACNO = TDCACDRU.OPP_AC_CNO;
        }
        CEP.TRC(SCCGWA, TDCCDINT.OPT);
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(22 - 1, 22 + 1 - 1).equalsIgnoreCase("1") 
            && TDCCDINT.OPT == '0') {
            TDRPHIS.INT_AMT = TDCACDRU.PAYING_INT - TDCCDINT.TRANS_INT;
            TDRPHIS.SETT_BAL = TDCACDRU.TXN_AMT;
            CEP.TRC(SCCGWA, TDCCDINT.BACK_INT);
            CEP.TRC(SCCGWA, TDRPHIS.INT_AMT);
            TDRPHIS.SETT_AMT = TDCACDRU.PAYING_INT - TDCCDINT.TRANS_INT;
            CEP.TRC(SCCGWA, TDRPHIS.SETT_AMT);
            TDRPHIS.SETT_STATE = '1';
            TDRPHIS.END_DATE = SCCGWA.COMM_AREA.AC_DATE;
        } else {
            TDRPHIS.INT_AMT = TDCACDRU.PAYING_INT;
            TDRPHIS.TAX_AMT = TDCACDRU.PAYING_TAX;
            TDRPHIS.SETT_AMT = TDCACDRU.PAYING_INT - TDCACDRU.PAYING_TAX;
        }
        if (TDCACDRU.OPT == '1' 
            || TDCACDRU.OPT == '7' 
            || TDCACDRU.OPT == '8') {
            TDRPHIS.SETT_BAL = TDCACDRU.TXN_AMT;
        }
        if (TDCACDRU.OPT == '1' 
            || TDCACDRU.OPT == '7' 
            || TDCACDRU.OPT == '8') {
            TDRPHIS.RAT_INT = 0;
            TDRPHIS.TAX_RAT = 0;
        }
        if (TDRPHIS.SETT_STATE == '1') {
            TDRPHIS.REC_INT_DT = SCCGWA.COMM_AREA.AC_DATE;
        } else {
            TDRPHIS.REC_INT_DT = 0;
        }
        TDRPHIS.DRW_BUD_INT = WS_BUD_INT;
        if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
        JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
        if (TDCACDRU.BUSI_CTLW.substring(0, 1).equalsIgnoreCase("1") 
            && TDRSMST.EXP_DATE != 0 
            && SCCGWA.COMM_AREA.AC_DATE > TDRSMST.EXP_DATE) {
            TDRPHIS.DRW_BUD_INT = 0;
        }
        TDRPHIS.COM_BAL = 0;
        TDRPHIS.COM_ACCU = 0;
        TDRPHIS.INOUT = TDCACDRU.INOUT;
        TDRPHIS.STL_MTH = TDCACDRU.CT_FLG;
        TDRPHIS.PAY_AC = TDCACDRU.INT_STL_AC;
        TDRPHIS.PAY_AC_SEQ = TDCACDRU.INT_STL_AC_SEQ;
        TDRPHIS.REMMIT_BK = TDCACDRU.INT_REMMIT_BK;
        TDRPHIS.REMMIT_NM = TDCACDRU.INT_REMMIT_NM;
        TDRPHIS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        TDRPHIS.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRPHIS.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRPHIS.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_WRITE_PHIS();
        if (pgmRtn) return;
    }
    public void B501_WRT_TDTDWHH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRDWHH);
        TDRDWHH.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
        TDRDWHH.KEY.PAY_SEQ = TDRCDI.NOR_NUM;
        TDRDWHH.TERM_NO = TDRCDI.PLAN_NUM;
        TDRDWHH.TXND_PROC_TYPE = 'P';
        TDRDWHH.NOR_SETT_DATE = WS_NEXT_DEAL_DT;
        TDRDWHH.SETT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRDWHH.SETT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRDWHH.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        TDRDWHH.SETT_CCY = TDRSMST.CCY;
        TDRDWHH.SETT_AMT = TDCACDRU.TXN_AMT;
        TDRDWHH.PAY_MTH = TDCACDRU.CT_FLG;
        TDRDWHH.SETT_AC = TDCACDRU.OPP_AC_CNO;
        TDRDWHH.STATUS = 'N';
        TDRDWHH.RBAK = " ";
        TDRDWHH.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        TDRDWHH.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRDWHH.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRDWHH.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_WRITE_TDTDWHH();
        if (pgmRtn) return;
    }
    public void B501_UPDATE_TDTPHIS_CAN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRPHIS);
        TDRPHIS.KEY.ACO_AC = TDCACDRU.ACO_AC;
        TDRPHIS.KEY.SETT_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        TDRPHIS.KEY.JRNNO = GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
        T000_READUP_PHIS_C();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            T000_REWRITE_PHIS_CANCEL();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_PHIS() throws IOException,SQLException,Exception {
        TDRPHIS.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRPHIS.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRPHIS.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTPHIS_RD = new DBParm();
        TDTPHIS_RD.TableName = "TDTPHIS";
        IBS.WRITE(SCCGWA, TDRPHIS, TDTPHIS_RD);
    }
    public void T000_WRITE_TDTDWHH() throws IOException,SQLException,Exception {
        TDRDWHH.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRDWHH.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRDWHH.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTDWHH_RD = new DBParm();
        TDTDWHH_RD.TableName = "TDTDWHH";
        IBS.WRITE(SCCGWA, TDRDWHH, TDTDWHH_RD);
    }
    public void T000_REWRITE_PHIS_CANCEL() throws IOException,SQLException,Exception {
        TDRPHIS.STATUS = 'R';
        TDRPHIS.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRPHIS.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRPHIS.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTPHIS_RD = new DBParm();
        TDTPHIS_RD.TableName = "TDTPHIS";
        IBS.REWRITE(SCCGWA, TDRPHIS, TDTPHIS_RD);
    }
    public void T000_READU_PHIS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCACDRU.ACO_AC);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE);
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO);
        TDRPHIS.KEY.ACO_AC = TDCACDRU.ACO_AC;
        TDRPHIS.KEY.SETT_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        TDRPHIS.KEY.JRNNO = GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
        TDTPHIS_RD = new DBParm();
        TDTPHIS_RD.TableName = "TDTPHIS";
        TDTPHIS_RD.where = "ACO_AC = :TDRPHIS.KEY.ACO_AC "
            + "AND SETT_DATE = :TDRPHIS.KEY.SETT_DATE "
            + "AND JRNNO = :TDRPHIS.KEY.JRNNO";
        TDTPHIS_RD.upd = true;
        IBS.READ(SCCGWA, TDRPHIS, this, TDTPHIS_RD);
        TDCACDRU.PAYING_INT = TDRPHIS.INT_AMT;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READUP_PHIS_C() throws IOException,SQLException,Exception {
        TDTPHIS_RD = new DBParm();
        TDTPHIS_RD.TableName = "TDTPHIS";
        TDTPHIS_RD.where = "ACO_AC = :TDRPHIS.KEY.ACO_AC "
            + "AND SETT_DATE = :TDRPHIS.KEY.SETT_DATE "
            + "AND JRNNO = :TDRPHIS.KEY.JRNNO";
        TDTPHIS_RD.upd = true;
        IBS.READ(SCCGWA, TDRPHIS, this, TDTPHIS_RD);
    }
    public void T000_READ_TDTPHIS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE);
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO);
        TDRPHIS.KEY.ACO_AC = TDCACDRU.ACO_AC;
        TDRPHIS.KEY.SETT_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        TDRPHIS.KEY.JRNNO = GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
        CEP.TRC(SCCGWA, TDRPHIS.KEY.ACO_AC);
        CEP.TRC(SCCGWA, TDRPHIS.KEY.SETT_DATE);
        CEP.TRC(SCCGWA, TDRPHIS.KEY.JRNNO);
        TDTPHIS_RD = new DBParm();
        TDTPHIS_RD.TableName = "TDTPHIS";
        TDTPHIS_RD.col = "ACO_AC,SETT_DATE,JRNNO,SETT_AMT";
        TDTPHIS_RD.where = "ACO_AC = :TDRPHIS.KEY.ACO_AC "
            + "AND SETT_DATE = :TDRPHIS.KEY.SETT_DATE "
            + "AND JRNNO = :TDRPHIS.KEY.JRNNO";
        IBS.READ(SCCGWA, TDRPHIS, this, TDTPHIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, TDRPHIS.KEY.SETT_DATE);
    }
    public void B000_MAIN_PI_PROC_CANCEL() throws IOException,SQLException,Exception {
        B015_READU_SMST_OTH_CANCEL();
        if (pgmRtn) return;
        B030_GET_PRD_INF();
        if (pgmRtn) return;
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(22 - 1, 22 + 1 - 1).equalsIgnoreCase("1")) {
            B016_READ_CDI();
            if (pgmRtn) return;
        }
        if ((TDRSMST.BV_TYP == '1' 
            || TDRSMST.BV_TYP == '2' 
            || TDRSMST.BV_TYP == '3' 
            || TDRSMST.BV_TYP == '7' 
            || TDRSMST.BV_TYP == '8') 
            || (TDRCMST.BV_TYP == '1' 
            || TDRCMST.BV_TYP == '2' 
            || TDRCMST.BV_TYP == '3' 
            || TDRCMST.BV_TYP == '7')) {
            b017_READ_BVT();
            if (pgmRtn) return;
        }
        B020_GET_CI_INF();
        if (pgmRtn) return;
        B100_CHK_PRD_CODE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TYJTEST6");
        B110_CHK_PRD_PARM();
        if (pgmRtn) return;
        B050_CHK_CI_INF();
        if (pgmRtn) return;
        if (TDRSMST.BV_TYP == '4') {
            R000_CHECK_CARD_PROC();
            if (pgmRtn) return;
            IBS.init(SCCGWA, DCCUCINF);
            DCCUCINF.CARD_NO = TDCACDRU.MAC_CNO;
            S000_CALL_DCZUCINF();
            if (pgmRtn) return;
            if (DCCUCINF.CARD_STS == 'C') {
                WS_MSGID = DCCMSG_ERROR_MSG.DC_CARD_NOT_NORMAL_STS;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        B130_CHK_STS_TBL();
        if (pgmRtn) return;
        if ((!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")) 
            && TDCACDRU.CT_FLG == '4') {
            B320_REG_ETAB_CANCEL();
            if (pgmRtn) return;
        }
        if (TDCACDRU.DRAW_TOT_AMT > DPCPARMP.LARGE_AMT) {
            B290_REG_LARGE_AMT_CANCEL();
            if (pgmRtn) return;
        }
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(22 - 1, 22 + 1 - 1).equalsIgnoreCase("1")) {
            T000_READU_PHIS();
            if (pgmRtn) return;
            T000_READU_CDI();
            if (pgmRtn) return;
            TDRCDI.LAST_DEAL_DATE = TDRPHIS.STR_DATE;
            TDRCDI.NEXT_DEAL_DATE = TDRPHIS.END_DATE;
            T000_REWRITE_CDI();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "XIANGUANYAO");
            T000_REWRITE_PHIS_CANCEL();
            if (pgmRtn) return;
        }
        R000_GET_BUD_INT_PI();
        if (pgmRtn) return;
        B500_REWRT_SMST_PI_CANCEL();
        if (pgmRtn) return;
        if (TDRCMST.BV_TYP == '2') {
            TDRBVT.PSBK_POS += 1;
            T000_REWRITE_BVT();
            if (pgmRtn) return;
            B570_WRT_PBP_PI_CANCEL();
            if (pgmRtn) return;
        }
        R000_UPDATE_TDTDINT_CANCEL();
        if (pgmRtn) return;
    }
    public void B000_MAIN_XC_PROC() throws IOException,SQLException,Exception {
        B015_CHK_SMST_INFO();
        if (pgmRtn) return;
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (!TDRSMST.STSW.substring(2 - 1, 2 + 2 - 1).equalsIgnoreCase("00") 
            && TDRSMST.INSTR_MTH == '6') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_HOLD_NOT_BAL_XC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B015_GET_CON_RATE();
        if (pgmRtn) return;
        B500_WRT_ROLL();
        if (pgmRtn) return;
        B030_GET_PRD_INF();
        if (pgmRtn) return;
        if (TDCACDRU.TXN_AMT == 0) {
            TDCACDRU.TXN_AMT = TDRSMST.BAL;
        }
        if ((TDRSMST.BV_TYP == '1' 
            || TDRSMST.BV_TYP == '2' 
            || TDRSMST.BV_TYP == '3' 
            || TDRSMST.BV_TYP == '7') 
            || (TDRCMST.BV_TYP == '1' 
            || TDRCMST.BV_TYP == '2' 
            || TDRCMST.BV_TYP == '3' 
            || TDRCMST.BV_TYP == '7')) {
            B017_READ_BVT();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, TDRBVT.BV_NO);
        B020_GET_CI_INF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDCACDRU.BV_NO);
        B100_CHK_PRD_CODE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TYJTEST7");
        B110_CHK_PRD_PARM();
        if (pgmRtn) return;
        B050_CHK_CI_INF();
        if (pgmRtn) return;
        if (!(TDRIREV.INT_SEL == '4' 
            && TDRIREV.CON_RATE == 0)) {
            B220_CAL_DRW_INT();
            if (pgmRtn) return;
        }
        TDRROLL.EXP_INT = TDCCDINT.PAYING_INT;
        TDRROLL.DRW_TAX = TDCCDINT.PAYING_TAX;
        T000_WRITE_ROLL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDCACDRU.BV_NO);
        if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
        JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
        if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            && !TDCACDRU.BUSI_CTLW.substring(0, 1).equalsIgnoreCase("1")) {
            if (TDRSMST.BV_TYP == '4') {
                B140_CHK_CARD_INF();
                if (pgmRtn) return;
            } else {
                if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
                JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
                for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
                if (!TDCACDRU.BUSI_CTLW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1")) {
                    B150_CHK_DRAW_MTH();
                    if (pgmRtn) return;
                }
            }
        }
        if (TDRSMST.BV_TYP == '4') {
            R000_CHECK_CARD_PROC();
            if (pgmRtn) return;
            IBS.init(SCCGWA, DCCUCINF);
            DCCUCINF.CARD_NO = TDCACDRU.MAC_CNO;
            S000_CALL_DCZUCINF();
            if (pgmRtn) return;
            if (DCCUCINF.CARD_STS == 'C') {
                WS_MSGID = DCCMSG_ERROR_MSG.DC_CARD_NOT_NORMAL_STS;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        B130_CHK_STS_TBL();
        if (pgmRtn) return;
        if ((!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")) 
            && TDCACDRU.CT_FLG == '4') {
            B320_REG_ETAB();
            if (pgmRtn) return;
        }
        B260_WRT_VCH_EVENT();
        if (pgmRtn) return;
        WS_HIS_AMT = BPCPOEWA.DATA.AMT_INFO[5-1].AMT;
        if (WS_HIS_AMT != 0 
            && TDRSMST.INSTR_MTH != '6') {
            CEP.TRC(SCCGWA, TDCACDRU.BV_NO);
            B270_WRT_FHIS();
            if (pgmRtn) return;
        }
        WS_EXP_DATE = TDRSMST.EXP_DATE;
        B500_REWRT_SMST_XC();
        if (pgmRtn) return;
        if (TDRCMST.PROD_CD.trim().length() > 0) {
            B530_UPDATE_MAIN_AC_INFO_BT();
            if (pgmRtn) return;
        }
        WS_DR_FLG = 'C';
        WS_HIS_AMT = BPCPOEWA.DATA.AMT_INFO[1-1].AMT + BPCPOEWA.DATA.AMT_INFO[4-1].AMT - BPCPOEWA.DATA.AMT_INFO[3-1].AMT;
        if (TDRSMST.INSTR_MTH != '6') {
            B270_WRT_FHIS();
            if (pgmRtn) return;
        }
        if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
            B630_OUTPUT_INT_INF();
            if (pgmRtn) return;
        }
    }
    public void B000_MAIN_XC_PROC_CANCEL() throws IOException,SQLException,Exception {
        B015_READU_SMST_OTH_CANCEL();
        if (pgmRtn) return;
        B030_GET_PRD_INF();
        if (pgmRtn) return;
        if ((TDRSMST.BV_TYP == '1' 
            || TDRSMST.BV_TYP == '2' 
            || TDRSMST.BV_TYP == '3' 
            || TDRSMST.BV_TYP == '7') 
            || (TDRCMST.BV_TYP == '1' 
            || TDRCMST.BV_TYP == '2' 
            || TDRCMST.BV_TYP == '3' 
            || TDRCMST.BV_TYP == '7')) {
            B017_READ_BVT();
            if (pgmRtn) return;
        }
        B020_GET_CI_INF();
        if (pgmRtn) return;
        B100_CHK_PRD_CODE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TYJTEST8");
        B110_CHK_PRD_PARM();
        if (pgmRtn) return;
        B050_CHK_CI_INF();
        if (pgmRtn) return;
        if (TDRSMST.BV_TYP == '4') {
            R000_CHECK_CARD_PROC();
            if (pgmRtn) return;
            IBS.init(SCCGWA, DCCUCINF);
            DCCUCINF.CARD_NO = TDCACDRU.MAC_CNO;
            S000_CALL_DCZUCINF();
            if (pgmRtn) return;
            if (DCCUCINF.CARD_STS == 'C') {
                WS_MSGID = DCCMSG_ERROR_MSG.DC_CARD_NOT_NORMAL_STS;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        B130_CHK_STS_TBL();
        if (pgmRtn) return;
        if ((!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")) 
            && TDCACDRU.CT_FLG == '4') {
            B320_REG_ETAB_CANCEL();
            if (pgmRtn) return;
        }
        B500_REWRT_ROLL_CANCEL();
        if (pgmRtn) return;
        B500_REWRT_SMST_XC_CANCEL();
        if (pgmRtn) return;
        WS_DR_FLG = 'C';
        B270_WRT_FHIS();
        if (pgmRtn) return;
        R000_UPDATE_TDTDINT_CANCEL();
        if (pgmRtn) return;
        if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
            B631_OUTPUT_PERD_INT();
            if (pgmRtn) return;
        }
    }
    public void B000_READ_AC_BV() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            if (TDRSMST.ACO_STS == '0') {
                if (TDRSMST.PRDAC_CD.equalsIgnoreCase("033")) {
                    TDCACDRU.OPT = '8';
                } else {
                    TDCACDRU.OPT = '1';
                }
            }
            if (TDRSMST.ACO_STS == '1') {
                if (TDRSMST.PRDAC_CD.equalsIgnoreCase("033")) {
                    TDCACDRU.OPT = '9';
                } else {
                    TDCACDRU.OPT = '0';
                }
                IBS.init(SCCGWA, TDRPWH);
                TDRPWH.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
                TDRPWH.TRADE_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
                TDRPWH.JRNNO = GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
                T000_READ_TDTPWH();
                if (pgmRtn) return;
                if (TDRPWH.PRIN_AMT != TDRPWH.BEF_AMT) {
                    WS_MSGID = TDCMSG_ERROR_MSG.TD_NOT_LAST_PART;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        } else {
            if (TDCACDRU.TXN_AMT < TDRSMST.BAL) {
                if (TDRSMST.PRDAC_CD.equalsIgnoreCase("033")) {
                    TDCACDRU.OPT = '8';
                } else {
                    TDCACDRU.OPT = '1';
                }
            } else {
                if (TDRSMST.PRDAC_CD.equalsIgnoreCase("033")) {
                    TDCACDRU.OPT = '8';
                } else {
                    TDCACDRU.OPT = '1';
                }
                if (TDCACDRU.TXN_AMT > TDRSMST.BAL) {
                    WS_MSGID = TDCMSG_ERROR_MSG.TD_TOT_AMT_GT_BAL;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        if (TDRSMST.BV_TYP == '1' 
            || TDRSMST.BV_TYP == '3' 
            || TDRSMST.BV_TYP == '5' 
            || TDRSMST.BV_TYP == '6' 
            || TDRSMST.BV_TYP == '7' 
            || TDRSMST.BV_TYP == '8') {
            IBS.init(SCCGWA, TDRBVT);
            if (TDRSMST.BV_TYP == '1' 
                && TDCACDRU.MAC_CNO.trim().length() > 0) {
                TDRBVT.KEY.AC_NO = TDCACDRU.MAC_CNO;
            } else {
                TDRBVT.KEY.AC_NO = TDCACDRU.ACO_AC;
            }
            T000_READ_BVT();
            if (pgmRtn) return;
            TDCACDRU.BV_CD = TDRBVT.BV_CD;
            TDCACDRU.BV_NO = TDRBVT.BV_NO;
            TDCACDRU.DRAW_MTH = TDRCMST.DRAW_MTH;
            TDCACDRU.DRAW_INF = TDRCMST.DRAW_INF;
        }
        TDCACDRU.PRDMO_CD = TDRSMST.PROD_CD;
        CEP.TRC(SCCGWA, TDCACDRU.OPT);
        CEP.TRC(SCCGWA, TDCACDRU.MAC_CNO);
        CEP.TRC(SCCGWA, TDRSMST.BV_TYP);
        B020_GET_CI_INF();
        if (pgmRtn) return;
        TDCACDRU.BV_TYP = TDRSMST.BV_TYP;
        if (WS_CI_TYP == '1') {
            if (CICACCU.DATA.CI_CNM.trim().length() > 0) {
                TDCACDRU.NAME = CICACCU.DATA.CI_CNM;
            } else {
                TDCACDRU.NAME = CICACCU.DATA.CI_ENM;
            }
        } else {
            TDCACDRU.NAME = CICACCU.DATA.AC_CNM;
        }
        TDCACDRU.CCY = TDRSMST.CCY;
        TDCACDRU.CCY_TYP = TDRSMST.CCY_TYP;
        TDCACDRU.PVAL_DATE = TDRSMST.PVAL_DATE;
        TDCACDRU.PBAL = TDRSMST.PBAL;
        TDCACDRU.ID_TYP = CICACCU.DATA.ID_TYPE;
        TDCACDRU.ID_NO = CICACCU.DATA.ID_NO;
        if (!(SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            || TDCACDRU.CANCEL_FLG == 'Y')) {
            TDCACDRU.BAL = TDRSMST.BAL;
        }
        TDCACDRU.VAL_DT = TDRSMST.VAL_DATE;
        TDCACDRU.EXP_DT = TDRSMST.EXP_DATE;
        TDCACDRU.INRT_MTH = TDRSMST.INSTR_MTH;
    }
    public void B015_CHK_SMST_INFO() throws IOException,SQLException,Exception {
        if (TDCACDRU.OPT == '3' 
            && TDRSMST.EXP_DATE <= SCCGWA.COMM_AREA.AC_DATE 
            && !(SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            || TDCACDRU.CANCEL_FLG == 'Y') 
            && !SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_EXP_P_CLO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
        JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
        if (TDRSMST.ACO_STS != '0' 
            && !(SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            || TDCACDRU.CANCEL_FLG == 'Y') 
            && !TDCACDRU.BUSI_CTLW.substring(0, 1).equalsIgnoreCase("1")) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (!TDCACDRU.CCY.equalsIgnoreCase(TDRSMST.CCY)) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_CCY_NOT_MATCH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDCACDRU.OPT != '3') {
            if (TDCACDRU.PVAL_DATE == 0) {
                TDCACDRU.PVAL_DATE = TDRSMST.PVAL_DATE;
            }
            if (TDRSMST.VAL_DATE != TDCACDRU.PVAL_DATE 
                && TDRSMST.VAL_DATE != TDCACDRU.VAL_DT 
                && TDRSMST.PVAL_DATE != TDCACDRU.VAL_DT 
                && TDRSMST.PVAL_DATE != TDCACDRU.PVAL_DATE) {
                CEP.TRC(SCCGWA, TDCACDRU.PVAL_DATE);
                CEP.TRC(SCCGWA, TDRSMST.PVAL_DATE);
                WS_MSGID = TDCMSG_ERROR_MSG.TD_VAL_DATE_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
            if (TDCACDRU.CHNL_FLG != 'Y') {
                if (((DPCPARMP.AC_TYPE.equalsIgnoreCase("025") 
                    || DPCPARMP.AC_TYPE.equalsIgnoreCase("026")) 
                    || DPCPARMP.AC_TYPE.equalsIgnoreCase("027")) 
                    && TDRSMST.BAL != TDCACDRU.TXN_AMT) {
                    CEP.TRC(SCCGWA, "19002");
                    CEP.TRC(SCCGWA, TDRSMST.BAL);
                    CEP.TRC(SCCGWA, TDCACDRU.TXN_AMT);
                    WS_MSGID = TDCMSG_ERROR_MSG.TD_BAL_ERR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
            JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
            if (!TDCACDRU.BUSI_CTLW.substring(0, 1).equalsIgnoreCase("1") 
                && (TDCACDRU.OPT != '5' 
                && TDCACDRU.OPT != 'A')) {
                CEP.TRC(SCCGWA, TDRSMST.PVAL_DATE);
                CEP.TRC(SCCGWA, TDCACDRU.TXN_AMT);
                CEP.TRC(SCCGWA, TDRSMST.BAL);
                CEP.TRC(SCCGWA, TDRSMST.HBAL);
                CEP.TRC(SCCGWA, TDRSMST.WBAL);
                if (TDCACDRU.TXN_AMT > TDRSMST.BAL) {
                    WS_MSGID = TDCMSG_ERROR_MSG.TD_BTA_TOO_LARGE;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
