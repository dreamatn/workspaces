package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCENPSW;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.DD.*;
import com.hisun.BP.*;
import com.hisun.AI.*;
import com.hisun.DP.*;
import com.hisun.CI.*;
import com.hisun.DC.*;

import java.io.IOException;
import java.sql.SQLException;

public class TDZACCRU {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm TDTGROP_RD;
    DBParm TDTSMST_RD;
    DBParm DDTMST_RD;
    DBParm TDTDWHH_RD;
    DBParm TDTCMST_RD;
    DBParm TDTOTHE_RD;
    DBParm TDTHMST_RD;
    DBParm TDTIREV_RD;
    DBParm TDTIRH_RD;
    DBParm TDTPMPD_RD;
    DBParm TDTAINT_RD;
    DBParm TDTBVT_RD;
    DBParm TDTINST_RD;
    DBParm TDTINTC_RD;
    DBParm TDTCDI_RD;
    DBParm TDTGGRP_RD;
    DBParm TDTYBTP_RD;
    DBParm TDTPBP_RD;
    DBParm TDTOCAC_RD;
    brParm TDTGROP_BR = new brParm();
    brParm TDTGGRP_BR = new brParm();
    brParm TDTSMST_BR = new brParm();
    DBParm DDTCCY_RD;
    String K_PRDP_TYP = "PRDPR";
    String K_IRUL_TYP = "TIRUL";
    String K_OUTPUT_FMT = "TD001";
    String K_OUTPUT_FMT2 = "TD016";
    String K_OUTPUT_FMT3 = "TD017";
    String K_OUTPUT_FMT4 = "TD018";
    String K_AP_MMO = "TD";
    String K_DD_ACCO = "DD";
    String K_DC_ACCO = "DC";
    String K_NORM_CR_STS_TBL = "0001";
    String K_NORM_CR_STS_TBL_P = "0011";
    String K_NORM_CR_STS_TBL_C = "0021";
    String K_NORM_CR_STS_TBL_C_P = "0022";
    String K_CTGM_CR_STS_TBL_C_P = "0025";
    String K_NORM_CR_AMT_TBL = "0001";
    String K_NORM_TR_AMT_TBL = "0003";
    String K_SYS_ERR = "SC6001";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String K_ZJ_TYPE = "01";
    String K_JLT_TYPE = "03";
    String K_DZ_TYPE = "05";
    String K_ZNT_TYPE = "09";
    String K_0G_TYPE = "02";
    String K_LA_TYPE = "08";
    String K_PDE_TYPE = "17";
    String K_CDE_TYPE = "18";
    String K_CHNL_DZ = "EBS";
    String K_CHNL_ZJ = "FIM";
    String K_AC_BK = "043";
    int K_HEAD_BR = 043400;
    String K_CHNL_TLR = "100100";
    String WS_MSGID = " ";
    String WS_MMO = " ";
    char WS_EC = ' ';
    String WS_PQORG_TRA_TYP = " ";
    char WS_PROC_TYP = ' ';
    short WS_I = 0;
    short WS_J = 0;
    short WS_IDX = 0;
    short WS_INST = 0;
    short WS_X = 0;
    short WS_C = 0;
    short WS_D = 0;
    short WS_MTHS = 0;
    short WS_MTHS_1 = 0;
    short WS_NUM = 0;
    short WS_HAP_NUM = 0;
    short WS_SHD_NUM = 0;
    short WS_RC = 0;
    short WS_RC_DISP = 0;
    double WS_BAL = 0;
    short WS_SEQ = 0;
    short WS_SEQ2 = 0;
    String WS_VIA_AC = " ";
    String WS_AC = " ";
    char WS_STL_MTH = ' ';
    String WS_MAC_CNO = " ";
    String WS_AC_TYPE = " ";
    String WS_AC_TYPE_M = " ";
    String WS_PROD_CD = " ";
    String WS_PROD_CD_PMPD = " ";
    int WS_NEXT_DEAL_DATE = 0;
    int WS_VA_OPEN_DATE = 0;
    char WS_CI_TYP = ' ';
    int WS_AC_SEQ = 0;
    String WS_GROP = " ";
    int WS_POST_BR = 0;
    char WS_WRT_OCAC = ' ';
    int WS_DEAL_DATE = 0;
    String WS_TERM_X = " ";
    String WS_ACO_BK = " ";
    String WS_ACO_GRP = " ";
    String WS_ACO_SEQ = " ";
    int WS_LBAL_DATE = 0;
    String WS_FRG_IND = " ";
    double WS_AMT_BK = 0;
    double WS_CR_AMT = 0;
    char WS_HIS_FLG = ' ';
    int WS_CHE_BR = 0;
    int WS_OWNER_BR = 0;
    int WS_OWNER_BK = 0;
    int WS_OWNER_BRDP = 0;
    char WS_CHECK_OPP = ' ';
    char WS_GRP_FLG = ' ';
    char WS_ZS_FLG = ' ';
    String WS_TERM = " ";
    TDZACCRU_REDEFINES56 REDEFINES56 = new TDZACCRU_REDEFINES56();
    int WS_DATE1 = 0;
    TDZACCRU_REDEFINES60 REDEFINES60 = new TDZACCRU_REDEFINES60();
    TDZACCRU_REDEFINES64 REDEFINES64 = new TDZACCRU_REDEFINES64();
    int WS_DATE2 = 0;
    TDZACCRU_REDEFINES68 REDEFINES68 = new TDZACCRU_REDEFINES68();
    TDZACCRU_REDEFINES72 REDEFINES72 = new TDZACCRU_REDEFINES72();
    String WS_BV_TYP_DESC = " ";
    TDZACCRU_REDEFINES76 REDEFINES76 = new TDZACCRU_REDEFINES76();
    String WS_DRAW_MTH_DESC = " ";
    TDZACCRU_REDEFINES87 REDEFINES87 = new TDZACCRU_REDEFINES87();
    String WS_TERM_DESC = " ";
    TDZACCRU_REDEFINES93 REDEFINES93 = new TDZACCRU_REDEFINES93();
    String WS_INSTR_MTH_DESC = " ";
    TDZACCRU_REDEFINES106 REDEFINES106 = new TDZACCRU_REDEFINES106();
    double WS_SELL_AMT = 0;
    double WS_BAL_DZ = 0;
    long WS_BAL_XY = 0;
    double WS_BAL_YE = 0;
    double WS_ADD_AMTC = 0;
    TDZACCRU_CP_PROD_CD CP_PROD_CD = new TDZACCRU_CP_PROD_CD();
    char WS_MATCH = ' ';
    short WS_TERM_MTHS2 = 0;
    double WS_ADJ_CR_INT = 0;
    double WS_ADJ_DR_INT = 0;
    double WS_INST_AMT = 0;
    int WS_VAL_DATE = 0;
    TDZACCRU_REDEFINES128 REDEFINES128 = new TDZACCRU_REDEFINES128();
    int WS_EXP_DATE = 0;
    TDZACCRU_REDEFINES133 REDEFINES133 = new TDZACCRU_REDEFINES133();
    short WS_CDI_NUM = 0;
    int WS_MAC_SEQ = 0;
    int WS_NEXT_DT = 0;
    int WS_CAL_DAYS = 0;
    int WS_STR_DT = 0;
    String WS_DOCU_NO = " ";
    int WS_PERD_DAYS = 0;
    int WS_TERM_DT = 0;
    TDZACCRU_REDEFINES145 REDEFINES145 = new TDZACCRU_REDEFINES145();
    String WS_MIN_CCYC = " ";
    double WS_MIN_AMTC = 0;
    double WS_MIN_DRAW_AMT = 0;
    double WS_MIN_LEFT_AMTC = 0;
    double WS_MAX_AMTC = 0;
    double WS_MIN_AMTC_REF = 0;
    double WS_MAX_AMTC_REF = 0;
    double WS_ADD_AMTC_REF = 0;
    double WS_BUY_AMT = 0;
    String WS_RUL_CDC = " ";
    short WS_CNT = 0;
    String WS_REF_CCY = " ";
    short WS_W = 0;
    char WS_CCY_FOUND = ' ';
    String WS_FORMAL_TERM = " ";
    short WS_ONE_MONS = 0;
    short WS_THREE_MONS = 0;
    short WS_SIX_MONS = 0;
    String WS_VA_CARD = " ";
    int WS_CUR_LINE = 0;
    short WS_PAGES = 0;
    TDZACCRU_WS_CHECK_DATE WS_CHECK_DATE = new TDZACCRU_WS_CHECK_DATE();
    char WS_CALD_FLG = ' ';
    String WS_IRAT_CD = " ";
    char WS_NON_STD_FLG = ' ';
    int WS_SUPR_BR1 = 0;
    int WS_SUPR_BR2 = 0;
    String WS_DD_CNM = " ";
    int WS_OPEN_BK = 0;
    int WS_AC_BR = 0;
    String WS_EX_TERM = " ";
    TDZACCRU_REDEFINES188 REDEFINES188 = new TDZACCRU_REDEFINES188();
    int WS_ACCRU_TERM = 0;
    int WS_PRDP_TERM = 0;
    TDZACCRU_WS_EX_TERM_NUM WS_EX_TERM_NUM = new TDZACCRU_WS_EX_TERM_NUM();
    String WS_TXN_FUNC = " ";
    short WS_COUNT1 = 0;
    TDZACCRU_WS_TDRFHIS WS_TDRFHIS = new TDZACCRU_WS_TDRFHIS();
    int WS_DATE3 = 0;
    char WS_ERROR = ' ';
    String WS_OPP_AC_CNO = " ";
    double WS_GROP_AMT = 0;
    double WS_GROP_AMT_X = 0;
    TDZACCRU_WS_SPARM_DATA WS_SPARM_DATA = new TDZACCRU_WS_SPARM_DATA();
    String WS_PR_IRAT_CD = " ";
    int WS_BRANCH_BR1 = 0;
    int WS_BRANCH_BR2 = 0;
    String WS_ACO_AC = " ";
    String WS_ACO_XXT = " ";
    String WS_ACO_AC_1 = " ";
    String WS_ACO_AC_2 = " ";
    short WS_LEN = 0;
    String WS_OP_APP = " ";
    char WS_ONTIM_FLG = ' ';
    char WS_LIMIT_FLG = ' ';
    String WS_PERD = " ";
    short WS_CD_PERD = 0;
    char WS_MAIN = ' ';
    String WS_GRPS_NO = " ";
    char WS_DE_FLG = ' ';
    String WS_GRP_ACO = " ";
    char WS_FULL = ' ';
    double WS_BAL_EDU = 0;
    double WS_BAL_I = 0;
    short WS_TS_SEQ = 0;
    char WS_CHK_PSW = ' ';
    char WS_CCY_DEF_FLG = ' ';
    String WS_ID_TYP = " ";
    char WS_ERLY_TYP = ' ';
    char WS_SMST_FLG = ' ';
    char WS_GGRP_FLG = ' ';
    char WS_CGROP_FLG = ' ';
    char WS_GROP_FLG = ' ';
    char WS_NOT_STANDARD_FLG = ' ';
    char WS_HMST_FLAG = ' ';
    char WS_YBT_AC_FLAG = ' ';
    char WS_SPE_FLG = ' ';
    char WS_OCAC_FLAG = ' ';
    char WS_OPP_AC_OSA_FLG = ' ';
    char WS_INT_AC_OSA_FLG = ' ';
    char WS_STL_AC_OSA_FLG = ' ';
    char WS_BUKUAN_FLG = ' ';
    int WS_GROP_CNT = 0;
    double WS_REP_AMT = 0;
    double WS_HAND_AMT = 0;
    double WS_SMST_AMT = 0;
    short WS_YBT_LINE = 0;
    int WS_CCY_CNT = 0;
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCENPSW SCCENPSW = new SCCENPSW();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCBINF SCCBINF = new SCCBINF();
    BPCPLDPD BPCPLDPD = new BPCPLDPD();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCPQPDT BPCPQPDT = new BPCPQPDT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCFAMTA BPCFAMTA = new BPCFAMTA();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    BPCUBUSE BPCUBUSE = new BPCUBUSE();
    BPCCGAC BPCCGAC = new BPCCGAC();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCPEBAS BPCPEBAS = new BPCPEBAS();
    BPCQCNGL BPCQCNGL = new BPCQCNGL();
    BPCAMMDP BPCAMMDP = new BPCAMMDP();
    BPCUCNGM BPCUCNGM = new BPCUCNGM();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    BPCFTLAM BPCFTLAM = new BPCFTLAM();
    AICPQMIB AICPQMIB = new AICPQMIB();
    TDRGROP TDRGROP = new TDRGROP();
    BPRPRMT BPRPRMT = new BPRPRMT();
    TDRFHIS TDRFHIS = new TDRFHIS();
    TDRGGRP TDRGGRP = new TDRGGRP();
    TDRIRH TDRIRH = new TDRIRH();
    TDRSMST TDRSMST = new TDRSMST();
    TDRCDI TDRCDI = new TDRCDI();
    TDRYBTP TDRYBTP = new TDRYBTP();
    TDRPBP TDRPBP = new TDRPBP();
    TDRBVT TDRBVT = new TDRBVT();
    TDRINST TDRINST = new TDRINST();
    TDRINTC TDRINTC = new TDRINTC();
    TDCCEINT TDCCEINT = new TDCCEINT();
    TDCPPRTF TDCPPRTF = new TDCPPRTF();
    TDCPRDP TDCPRDP = new TDCPRDP();
    TDCIRULP TDCIRULP = new TDCIRULP();
    DPCPARMP DPCPARMP = new DPCPARMP();
    CICCUST CICCUST = new CICCUST();
    CICQACRL CICQACRL = new CICQACRL();
    CICACCU CICACCU = new CICACCU();
    CICACCU CICACDD = new CICACCU();
    CICMACR CICMACR = new CICMACR();
    DCCUCACJ DCCUCACJ = new DCCUCACJ();
    DDRMST DDRMST = new DDRMST();
    TDCOCCRU TDCOCCRU = new TDCOCCRU();
    TDCOCCLZ TDCOCCLZ = new TDCOCCLZ();
    BPCPORDN BPCPORDN = new BPCPORDN();
    BPCFX BPCFX = new BPCFX();
    DCCUHLD DCCUHLD = new DCCUHLD();
    TDRIREV TDRIREV = new TDRIREV();
    BPCCINTI BPCCINTI = new BPCCINTI();
    TDRDWHH TDRDWHH = new TDRDWHH();
    TDRHMST TDRHMST = new TDRHMST();
    TDRAINT TDRAINT = new TDRAINT();
    TDCCDS TDCCDS = new TDCCDS();
    DCCITRSR DCCITRSR = new DCCITRSR();
    BPCQCCY BPCQCCY = new BPCQCCY();
    DDCSCINM DDCSCINM = new DDCSCINM();
    BPCPRGST BPCPRGST = new BPCPRGST();
    CICQACRI CICQACRI = new CICQACRI();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    SCCHMPW SCCHMPW = new SCCHMPW();
    DDCSFBID DDCSFBID = new DDCSFBID();
    TDCUPARM TDCUPARM = new TDCUPARM();
    TDCBVCD TDCBVCD = new TDCBVCD();
    BPCITAXG BPCITAXG = new BPCITAXG();
    DDCIMMST DDCIMMST = new DDCIMMST();
    BPCTCALF BPCTCALF = new BPCTCALF();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPCPQORG BPCPQORG = new BPCPQORG();
    DCCUSSTS DCCUSSTS = new DCCUSSTS();
    DCCPFTCK DCCPFTCK = new DCCPFTCK();
    TDROCAC TDROCAC = new TDROCAC();
    DCCUCINF DCCUCINF = new DCCUCINF();
    BPCPCKPD BPCPCKPD = new BPCPCKPD();
    DCCILNKR DCCILNKR = new DCCILNKR();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    DCCUIQMC DCCUIQMC = new DCCUIQMC();
    BPCUNARR BPCUNARR = new BPCUNARR();
    TDCODECR TDCODECR = new TDCODECR();
    TDRCMST TDRCMST = new TDRCMST();
    TDCPIOD TDCPIOD = new TDCPIOD();
    CICQACAC CICQACAC = new CICQACAC();
    CICSACAC CICSACAC = new CICSACAC();
    CICSACR CICSACR = new CICSACR();
    CICSACRL CICSACRL = new CICSACRL();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    TDROTHE TDROTHE = new TDROTHE();
    CICMGRPM CICMGRPM = new CICMGRPM();
    BPCSOCAC BPCSOCAC = new BPCSOCAC();
    TDCLML TDCLML = new TDCLML();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    TDCACM TDCACM = new TDCACM();
    DDCSQIFA DDCSQIFA = new DDCSQIFA();
    DCCURHLD DCCURHLD = new DCCURHLD();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICSAGEN CICSAGEN = new CICSAGEN();
    DDRCCY DDRCCY = new DDRCCY();
    CICMLMT CICMLMT = new CICMLMT();
    CICCKLS CICCKLS = new CICCKLS();
    TDCPROD TDCPROD = new TDCPROD();
    TDCQPMP TDCQPMP = new TDCQPMP();
    TDRPMPD TDRPMPD = new TDRPMPD();
    SCCGWA SCCGWA;
    BPCPORUP_DATA_INFO BPCPORUP;
    TDCACCRU TDCACCRU;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    CICGAGA_AGENT_AREA CICGAGA_AGENT_AREA;
    public void MP(SCCGWA SCCGWA, TDCACCRU TDCACCRU) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCACCRU = TDCACCRU;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.REQ_SYSTEM);
        if (SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("CNTA")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_ERR_SYSTEM);
        }
        if (TDCACCRU.VAL_DT == 0) {
            TDCACCRU.VAL_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (TDCACCRU.VAL_DT < SCCGWA.COMM_AREA.AC_DATE) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DQX_AUTH);
        }
        if (TDCACCRU.TERM.equalsIgnoreCase("S000") 
            && TDCACCRU.INT_SEL != '4') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_TERM_SEL_ERR);
        }
        if (TDCACCRU.PROD_CD.equalsIgnoreCase("5201010101") 
            || TDCACCRU.PROD_CD.equalsIgnoreCase("5201010301")) {
            if (!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10210")) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CHNL_NOT_SUPPORT);
            }
        }
        if (TDCACCRU.TXN_MMO.equalsIgnoreCase("0072")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_ERR_MMO);
        }
        CEP.TRC(SCCGWA, TDCACCRU.BV_CD);
        if (TDCACCRU.BV_CD.trim().length() == 0) {
            TDCACCRU.BV_CD = "088";
        }
        CEP.TRC(SCCGWA, TDCACCRU);
        CEP.TRC(SCCGWA, "JFTEST1");
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        CEP.TRC(SCCGWA, TDCACCRU.ACO_AC);
        CEP.TRC(SCCGWA, TDCACCRU.AC_NO);
        CEP.TRC(SCCGWA, TDCACCRU.FST_FLG);
        A000_INIT_PROC();
        if (TDCACCRU.GRPAUTO_FLG == 'Y') {
            WS_GRP_ACO = TDCACCRU.ACO_AC;
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            IBS.init(SCCGWA, CICQACAC);
            if (TDCACCRU.ACO_AC.trim().length() > 0) {
                CICQACAC.FUNC = 'A';
                CICQACAC.DATA.ACAC_NO = TDCACCRU.ACO_AC;
            } else {
                CICQACAC.FUNC = 'R';
                CICQACAC.DATA.AGR_NO = TDCACCRU.AC_NO;
                CICQACAC.DATA.AGR_SEQ = TDCACCRU.AC_SEQ;
                CEP.TRC(SCCGWA, CICQACAC.DATA.AGR_SEQ);
                CICQACAC.DATA.BV_NO = TDCACCRU.BV_NO;
            }
            S000_CALL_CIZQACAC();
            TDCACCRU.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
            if (TDCACCRU.AC_NO.trim().length() == 0) {
                TDCACCRU.AC_NO = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
            }
            if (TDCACCRU.AC_SEQ == 0) {
                TDCACCRU.AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
            }
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.KEY.ACO_AC = TDCACCRU.ACO_AC;
            T000_READ_SMST();
            CEP.TRC(SCCGWA, TDRSMST.ACO_STS);
        }
        CEP.TRC(SCCGWA, TDCACCRU.AC_NO);
        if (TDCACCRU.BV_TYP == '4') {
            CEP.TRC(SCCGWA, "---1S");
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.FUNC = 'A';
            CICQACRI.DATA.AGR_NO = TDCACCRU.AC_NO;
            S000_CALL_CIZQACRI();
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_AGR_NO);
            if (CICQACRI.O_DATA.O_AGR_NO.trim().length() > 0) {
                TDCACCRU.AC_NO = CICQACRI.O_DATA.O_AGR_NO;
            }
            CEP.TRC(SCCGWA, TDCACCRU.AC_NO);
            IBS.init(SCCGWA, DCCUCINF);
            DCCUCINF.CARD_NO = TDCACCRU.AC_NO;
            S000_CALL_DCZUCINF();
            CEP.TRC(SCCGWA, DCCUCINF.CARD_LNK_TYP);
            if (DCCUCINF.CARD_LNK_TYP != '1') {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_FSCARD_NOT_OPEN_TD;
                S000_ERR_MSG_PROC();
            }
            CEP.TRC(SCCGWA, DCCUCINF.FACE_FLG);
            CEP.TRC(SCCGWA, TDCACCRU.CT_FLG);
            if (DCCUCINF.FACE_FLG == 'N') {
                if (TDCACCRU.CT_FLG == '0') {
                    WS_MSGID = TDCMSG_ERROR_MSG.TD_II_CARD_NOT_OPEN_TD;
                    S000_ERR_MSG_PROC();
                }
            }
        }
        CEP.TRC(SCCGWA, TDCACCRU.OPP_AC_CNO);
        if (TDCACCRU.OPP_AC_CNO.trim().length() > 0) {
            CEP.TRC(SCCGWA, "---2S");
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.FUNC = 'A';
            CICQACRI.DATA.AGR_NO = TDCACCRU.OPP_AC_CNO;
            S000_CALL_CIZQACRI();
            CEP.TRC(SCCGWA, "---2E");
            if (CICQACRI.O_DATA.O_ENTY_TYP == '2' 
                && CICQACRI.O_DATA.O_AGR_NO.trim().length() > 0) {
                TDCACCRU.OPP_AC_CNO = CICQACRI.O_DATA.O_AGR_NO;
            }
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
            WS_OP_APP = CICQACRI.O_DATA.O_FRM_APP;
        }
        if (TDCACCRU.INT_RUL_CD.equalsIgnoreCase("ZZYH") 
            && (!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_N_EXSIT_IRUL;
            S000_ERR_MSG_PROC();
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            WS_EC = '1';
            if ((TDCACCRU.OPT == '0' 
                    || TDCACCRU.OPT == '2' 
                    || TDCACCRU.OPT == '3' 
                    || TDCACCRU.OPT == '4')) {
                WS_TXN_FUNC = "XHC";
                if (WS_MMO.trim().length() == 0) {
                    B000_GET_MMO();
                }
                WS_PROC_TYP = '1';
                IBS.init(SCCGWA, TDRSMST);
                TDRSMST.KEY.ACO_AC = TDCACCRU.ACO_AC;
                T000_READU_SMST();
                CEP.TRC(SCCGWA, TDRSMST.PRDAC_CD);
                CEP.TRC(SCCGWA, TDCACCRU.TXN_AMT);
                CEP.TRC(SCCGWA, TDCACCRU.ACO_AC);
                CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO);
                CEP.TRC(SCCGWA, TDRSMST.ACO_STS);
                if (TDRSMST.PRDAC_CD.equalsIgnoreCase("035") 
                    && TDCACCRU.FST_FLG != '1') {
                    CEP.TRC(SCCGWA, "ACCRU1214-A");
                    WS_GROP_AMT = TDCACCRU.TXN_AMT;
                    IBS.init(SCCGWA, TDRGGRP);
                    TDRGGRP.AC_NO = TDCACCRU.AC_NO;
                    TDRGGRP.KEY.JRNNO = GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
                    TDRGGRP.KEY.TR_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
                    CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE);
                    T000_STARTBR_TDTGGRP();
                    T000_READNEXT_TDTGGRP();
                    while (WS_GGRP_FLG != 'N') {
                        CEP.TRC(SCCGWA, "ACCRU1214-B");
                        TDCACCRU.ACO_AC = TDRGGRP.ACO_AC;
                        TDCACCRU.TXN_AMT = TDRGGRP.TX_AMT;
                        if (TDRGGRP.NEW_FLG == 'Y') {
                            B000_MAIN_PROC_KH_CANCEL();
                        } else {
                            B000_MAIN_PROC_CR_CANCEL();
                        }
                        TDRGGRP.CAN_FLG = '1';
                        T000_REWRITE_TDTGGRP();
                        T000_READNEXT_TDTGGRP();
                    }
                    T000_ENDBR_TDTGGRP();
                    TDCACCRU.TXN_AMT = WS_GROP_AMT;
                    IBS.init(SCCGWA, TDRSMST);
                    TDRSMST.AC_NO = TDCACCRU.AC_NO;
                    T000_GROUP_TDTSMST();
                    IBS.init(SCCGWA, TDRSMST);
                    TDRSMST.AC_NO = TDCACCRU.AC_NO;
                    T000_READ_TDTSMST_MR();
                    TDRSMST.BAL = WS_SMST_AMT;
                    TDCACCRU.ACO_AC = TDRSMST.KEY.ACO_AC;
                    T000_REWRITE_SMST();
                    B270_WRT_FHIS();
                } else {
                    CEP.TRC(SCCGWA, "ACCRU1214-C");
                    B000_MAIN_PROC_KH_CANCEL();
                }
                B300_DRAW_FEE_PROC();
                B800_OUTPUT_PROC();
            } else if (TDCACCRU.OPT == '1') {
                WS_TXN_FUNC = "BTC";
                if (WS_MMO.trim().length() == 0) {
                    B000_GET_MMO();
                }
                WS_PROC_TYP = '5';
                B000_MAIN_PROC_GK_CANCEL();
                B300_DRAW_FEE_PROC();
            } else {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_OPT);
            }
        } else {
            WS_EC = ' ';
            if ((TDCACCRU.OPT == '0' 
                    || TDCACCRU.OPT == '2' 
                    || TDCACCRU.OPT == '3')) {
                WS_TXN_FUNC = "XH";
                if (WS_MMO.trim().length() == 0) {
                    B000_GET_MMO();
                }
                WS_PROC_TYP = '1';
                B000_MAIN_PROC_KH();
                B300_DRAW_FEE_PROC();
                if (WS_ZS_FLG == 'Y') {
                    if (TDCACCRU.AC_STSW == null) TDCACCRU.AC_STSW = "";
                    JIBS_tmp_int = TDCACCRU.AC_STSW.length();
                    for (int i=0;i<8-JIBS_tmp_int;i++) TDCACCRU.AC_STSW += " ";
                    TDCACCRU.AC_STSW = "1" + TDCACCRU.AC_STSW.substring(1);
                }
                B800_OUTPUT_PROC();
            } else if (TDCACCRU.OPT == '1') {
                WS_TXN_FUNC = "BT";
                if (WS_MMO.trim().length() == 0) {
                    B000_GET_MMO();
                }
                WS_PROC_TYP = '5';
                B000_MAIN_PROC_GK();
                B300_DRAW_FEE_PROC();
                B900_OUTPUT_PROC();
            } else {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_OPT);
            }
        }
        CEP.TRC(SCCGWA, "TDZACCRU return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        CICGAGA_AGENT_AREA = new CICGAGA_AGENT_AREA_AGENT_AREA();
        IBS.init(SCCGWA, CICGAGA_AGENT_AREA);
        IBS.CPY2CLS(SCCGWA, SCCGWA.AGENT_AREA_PTR, CICGAGA_AGENT_AREA);
        IBS.init(SCCGWA, TDCCEINT);
        IBS.init(SCCGWA, CICCUST);
        WS_MMO = TDCACCRU.TXN_MMO;
        if (TDCACCRU.JRNNO != 0) {
            GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO = TDCACCRU.JRNNO;
            SCCGWA.COMM_AREA.CANCEL_IND = 'Y';
        }
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, TDCACCRU.GRPAUTO_FLG);
        CEP.TRC(SCCGWA, TDCACCRU.CDS_DT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        WS_PQORG_TRA_TYP = BPCPQORG.TRA_TYP;
        if (TDCACCRU.AC_NO.trim().length() > 0) {
            IBS.init(SCCGWA, TDRCMST);
            TDRCMST.KEY.AC_NO = TDCACCRU.AC_NO;
            T000_READ_CMST();
            if (!WS_PQORG_TRA_TYP.equalsIgnoreCase("00") 
                && (TDRCMST.FRG_IND.trim().length() == 0 
                || TDRCMST.FRG_IND.equalsIgnoreCase("NRA"))) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_FTA);
            }
            if (WS_PQORG_TRA_TYP.equalsIgnoreCase("00") 
                && (TDRCMST.FRG_IND.trim().length() > 0 
                && !TDRCMST.FRG_IND.equalsIgnoreCase("NRA"))) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_NOT_FTA);
            }
        }
    }
    public void B000_GET_MMO() throws IOException,SQLException,Exception {
        if (WS_TXN_FUNC.equalsIgnoreCase("XHC")) {
            WS_MMO = "G004";
        } else if (WS_TXN_FUNC.equalsIgnoreCase("BTC")) {
            WS_MMO = "G004";
        } else if (WS_TXN_FUNC.equalsIgnoreCase("XH")
            || WS_TXN_FUNC.equalsIgnoreCase("BT")) {
            if (TDCACCRU.OPT == '3') {
                WS_MMO = "A001";
            } else {
                if (TDCACCRU.CT_FLG == '0') {
                    if (TDCACCRU.AC_NO.trim().length() == 0) {
                        WS_MMO = "C001";
                    } else {
                        WS_MMO = "C002";
                    }
                } else {
                    WS_MMO = "A001";
                    if (TDCACCRU.CT_FLG == '2') {
                        WS_MMO = "A005";
                    }
                }
            }
        }
    }
    public void B000_MAIN_PROC_KH() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCACCRU.VAL_DT);
        CEP.TRC(SCCGWA, TDCACCRU.AC_NO);
        IBS.init(SCCGWA, TDRCMST);
        if (TDCACCRU.AC_NO.trim().length() > 0 
            && TDCACCRU.OPT != '2' 
            && TDCACCRU.BV_TYP != '4') {
            TDRCMST.KEY.AC_NO = TDCACCRU.AC_NO;
            T000_READU_CMST();
            WS_PROD_CD = TDRCMST.PROD_CD;
            R000_GET_SALE_PROD_INFO();
            WS_AC_TYPE_M = BPCPQPRD.AC_TYPE;
            if (TDRCMST.CCY.trim().length() > 0 
                && TDCACCRU.CCY.trim().length() > 0 
                && (BPCPQPRD.AC_TYPE.equalsIgnoreCase("033") 
                || BPCPQPRD.AC_TYPE.equalsIgnoreCase("048")) 
                && !TDRCMST.CCY.equalsIgnoreCase(TDCACCRU.CCY)) {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_CCY_I_ERR;
                S000_ERR_MSG_PROC();
            }
        }
        B020_GET_CI_INF_KH();
        if (TDCACCRU.AC_NO.trim().length() == 0) {
            WS_MAIN = 'Y';
        }
        CEP.TRC(SCCGWA, TDCACCRU.PROD_CD);
        WS_PROD_CD = TDCACCRU.PROD_CD;
        R000_GET_SALE_PROD_INFO();
        CEP.TRC(SCCGWA, BPCPQPRD.AC_TYPE);
        WS_AC_TYPE = BPCPQPRD.AC_TYPE;
        if (TDCACCRU.BV_TYP != '4') {
            if (!BPCPQPRD.PRDT_MODEL.equalsIgnoreCase("MMDP")) {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_MAIN_AC_NOTTD;
                S000_ERR_MSG_PROC();
            }
        }
        R000_GET_BRINFO();
        CEP.TRC(SCCGWA, WS_PROD_CD);
        CEP.TRC(SCCGWA, TDCACCRU.BV_TYP);
        CEP.TRC(SCCGWA, TDCACCRU.AC_NO);
        if (TDCACCRU.MAIN_FLG == 'N' 
            && TDCACCRU.BV_TYP != '4') {
            B230_GEN_TD_AC();
        }
        CEP.TRC(SCCGWA, TDCACCRU.AC_NO);
        B480_GET_PRODINF();
        CEP.TRC(SCCGWA, BPCPQPRD.AC_TYPE);
        B490_CHECK_PROD();
        B495_CHECK_PROD_CI_INFO();
        B021_CHK_AC_INFO();
        CEP.TRC(SCCGWA, TDCACCRU.AC_NO);
        if (TDCACCRU.BV_TYP == '1') {
            R000_CHECK_YBT();
        }
        CEP.TRC(SCCGWA, TDCPIOD.EXP_PRM.LMT_FLG);
        CEP.TRC(SCCGWA, WS_AC_TYPE);
        CEP.TRC(SCCGWA, TDCCEINT.RACD_FLG);
        B030_GET_PRD_INF_KH();
        CEP.TRC(SCCGWA, TDCACCRU.AC_NO);
        B100_CHK_PRD_CODE();
        WS_GROP_AMT = TDCACCRU.TXN_AMT;
        if (WS_AC_TYPE.equalsIgnoreCase("035")) {
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.AC_NO = TDCACCRU.AC_NO;
            TDRSMST.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
            T000_READUP_SMST_GRP();
            CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
            if (TDCACCRU.FST_FLG != '1') {
                WS_MMO = "C002";
                if (TDCACCRU.GRPAUTO_FLG != 'Y') {
                    R000_INST_GROPAMT();
                } else {
                    R000_AUTO_GROPAMT();
                }
            } else {
                WS_MMO = "C001";
                B500_GEN_ACOAC();
            }
        } else {
            if (WS_AC_TYPE.equalsIgnoreCase("036")) {
                if (TDCACCRU.TXN_MMO.trim().length() == 0) {
                    WS_MMO = "C002";
                } else {
                    WS_MMO = TDCACCRU.TXN_MMO;
                }
                CEP.TRC(SCCGWA, "1130");
                IBS.init(SCCGWA, TDRSMST);
                TDRSMST.AC_NO = TDCACCRU.AC_NO;
                TDRSMST.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
                T000_READUP_SMST_GRP();
                if (WS_GRP_FLG == 'Y') {
                    B500_GEN_ACOAC();
                    IBS.init(SCCGWA, TDRGGRP);
                    CEP.TRC(SCCGWA, "AAWW");
                    R000_GET_GROPSEQ();
                    CEP.TRC(SCCGWA, WS_TS_SEQ);
                    TDRGGRP.KEY.SEQ = WS_TS_SEQ;
                    TDRGGRP.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
                    TDRGGRP.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    TDRGGRP.ACO_AC = TDCACCRU.ACO_AC;
                    TDRGGRP.CAN_FLG = '0';
                    TDRGGRP.NEW_FLG = 'Y';
                    TDRGGRP.CDR_FLG = 'C';
                    TDRGGRP.AC_NO = TDCACCRU.AC_NO;
                    TDRGGRP.TX_AMT = TDCACCRU.TXN_AMT;
                    TDRGGRP.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
                    TDRGGRP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    T000_WRITE_TDTGGRP();
                } else {
                    TDRSMST.BAL += TDCACCRU.TXN_AMT;
                    T000_REWRITE_SMST();
                    CEP.TRC(SCCGWA, WS_GRP_FLG);
                    IBS.init(SCCGWA, TDRGGRP);
                    R000_GET_GROPSEQ();
                    TDRGGRP.KEY.SEQ = WS_TS_SEQ;
                    TDRGGRP.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
                    TDRGGRP.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    TDRGGRP.ACO_AC = TDRSMST.KEY.ACO_AC;
                    TDRGGRP.CAN_FLG = '0';
                    TDRGGRP.NEW_FLG = 'N';
                    TDRGGRP.CDR_FLG = 'C';
                    TDRGGRP.AC_NO = TDCACCRU.AC_NO;
                    TDRGGRP.TX_AMT = TDCACCRU.TXN_AMT;
                    TDRGGRP.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
                    TDRGGRP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    TDRGGRP.RMK = "XXTCR";
                    T000_WRITE_TDTGGRP();
                }
            } else {
                B500_GEN_ACOAC();
            }
        }
        CEP.TRC(SCCGWA, WS_GRP_FLG);
        WS_ACO_AC_1 = WS_ACO_AC;
        if ((WS_AC_TYPE.equalsIgnoreCase("035") 
            || WS_AC_TYPE.equalsIgnoreCase("036")) 
            && TDCACCRU.FST_FLG == '1') {
            B500_GEN_ACOAC();
            WS_ACO_AC_2 = WS_ACO_AC;
        }
        CEP.TRC(SCCGWA, WS_ACO_AC_1);
        CEP.TRC(SCCGWA, WS_ACO_AC_2);
        CEP.TRC(SCCGWA, TDCACCRU.AC_NO);
        if (TDCACCRU.BV_TYP == '2' 
            || TDCACCRU.BV_TYP == '3' 
            || TDCACCRU.BV_TYP == '7') {
            if (TDCACCRU.BVPRT_FLG != 'N') {
                B200_BV_USE();
            }
        }
        if (TDCACCRU.BV_TYP == '4') {
            B017_GET_CARD_INF();
            R000_CHECK_CARD_PROC();
        }
        if (TDCACCRU.BV_TYP == '1') {
            B017_READ_BVT();
        }
        CEP.TRC(SCCGWA, "222222222");
        CEP.TRC(SCCGWA, TDCACCRU.CI_NO);
        CEP.TRC(SCCGWA, TDCACCRU.CI_NO);
        if (TDCACCRU.INSTR_MTH == '4' 
            || TDCACCRU.INSTR_MTH == '5') {
            if (TDCACCRU.CD_AMT <= 0) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_OTHER_ERR);
            }
        }
        CEP.TRC(SCCGWA, "444444444444444444");
        WS_TERM = TDCACCRU.TERM;
        IBS.CPY2CLS(SCCGWA, WS_TERM, REDEFINES56);
        if ((!DPCPARMP.AC_TYPE.equalsIgnoreCase("029") 
            && !DPCPARMP.AC_TYPE.equalsIgnoreCase("030")) 
            && !WS_AC_TYPE.equalsIgnoreCase("022") 
            && TDCACCRU.VAL_DT < SCCGWA.COMM_AREA.AC_DATE) {
            B710_CHECK_VAL_DT();
        }
        CEP.TRC(SCCGWA, "55555555555555");
        CEP.TRC(SCCGWA, TDCACCRU.VAL_DT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (TDCACCRU.VAL_DT > SCCGWA.COMM_AREA.AC_DATE) {
            if (!WS_AC_TYPE.equalsIgnoreCase("037")) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_VAL_DT);
            }
        } else if (TDCACCRU.VAL_DT <= SCCGWA.COMM_AREA.AC_DATE) {
            if (WS_AC_TYPE.equalsIgnoreCase("037")) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_VAL_DT);
            }
        } else {
        }
        CEP.TRC(SCCGWA, "66666666666");
        B210_CAL_EXP_DT();
        CEP.TRC(SCCGWA, TDCACCRU.EXP_DT);
        if (TDCACCRU.TERM.equalsIgnoreCase("S000")) {
            if (TDCACCRU.EXP_DT <= SCCGWA.COMM_AREA.AC_DATE) {
                if (TDCACCRU.OPT != '2' 
                    && (TDCPIOD.OTH_PRM.DOCU_FLG == '0' 
                    || TDCPIOD.OTH_PRM.DOCU_FLG == 'Y')) {
                    CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_EXP_DT);
                }
            }
        }
        CEP.TRC(SCCGWA, REDEFINES56.WS_TERM_TYP);
        CEP.TRC(SCCGWA, TDCACCRU.CI_NO);
        CEP.TRC(SCCGWA, TDCACCRU.PSW);
        CEP.TRC(SCCGWA, WS_DE_FLG);
        if (TDCACCRU.AC_NAME.trim().length() == 0) {
            IBS.init(SCCGWA, CICCUST);
            CICCUST.FUNC = 'C';
            CICCUST.DATA.CI_NO = TDCACCRU.CI_NO;
            S000_CALL_CIZCUST();
            TDCACCRU.ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
            TDCACCRU.ID_NO = CICCUST.O_DATA.O_ID_NO;
            TDCACCRU.AC_NAME = CICCUST.O_DATA.O_CI_NM;
        }
        CEP.TRC(SCCGWA, WS_AC_TYPE);
        CEP.TRC(SCCGWA, WS_CI_TYP);
        CEP.TRC(SCCGWA, TDCACCRU.PSW);
        if (WS_AC_TYPE.equalsIgnoreCase("037") 
            && WS_CI_TYP == '1' 
            && TDCACCRU.PSW.trim().length() == 0) {
            WS_CHK_PSW = 'N';
        }
        if (TDCACCRU.MAIN_FLG == 'N' 
            && TDCACCRU.BV_TYP != '4') {
            B500_WRT_TDTCMST();
            if (TDCACCRU.DRAW_MTH == '1' 
                || TDCACCRU.DRAW_MTH == '4') {
                if (WS_DE_FLG == 'Y' 
                    || WS_CHK_PSW == 'N') {
                } else {
                    if (TDCACCRU.OPSW_FLG == 'Y') {
                        IBS.init(SCCGWA, TDCACM);
                        TDCACM.OPSW_FLG = TDCACCRU.OPSW_FLG;
                        TDCACM.FUNC = 'R';
                        TDCACM.AC_NO = TDCACCRU.AC_NO;
                        TDCACM.OLD_AC_NO = "123456789012";
                        TDCACM.CARD_PSW_OLD = TDCACCRU.PSW;
                        TDCACM.CARD_PSW_NEW = TDCACCRU.PSW;
                        TDCACM.ID_TYP = TDCACCRU.ID_TYP;
                        TDCACM.ID_NO = TDCACCRU.ID_NO;
                        TDCACM.CI_NM = TDCACCRU.AC_NAME;
                        S000_CALL_TDZACM();
                    } else {
                        IBS.init(SCCGWA, TDCACM);
                        TDCACM.FUNC = 'R';
                        TDCACM.AC_NO = TDCACCRU.AC_NO;
                        TDCACM.OLD_AC_NO = "123456789012";
                        TDCACM.CARD_PSW_OLD = TDCACCRU.PSW;
                        TDCACM.CARD_PSW_NEW = TDCACCRU.PSW;
                        TDCACM.ID_TYP = TDCACCRU.ID_TYP;
                        TDCACM.ID_NO = TDCACCRU.ID_NO;
                        TDCACM.CI_NM = TDCACCRU.AC_NAME;
                        S000_CALL_TDZACM();
                    }
                }
            }
        }
        if (TDCACCRU.AC_NO.trim().length() > 0 
            && TDCACCRU.MAIN_FLG == 'Y' 
            && TDCACCRU.OPT != '2' 
            && TDCACCRU.BV_TYP != '4') {
            B500_REWRT_TDTCMST();
        }
        CEP.TRC(SCCGWA, TDCACCRU.PSW);
        if (WS_GRP_FLG != 'N' 
            && WS_FULL != 'Y') {
            B500_WRT_TDTSMST();
        }
        if ((WS_AC_TYPE.equalsIgnoreCase("035") 
            || WS_AC_TYPE.equalsIgnoreCase("036")) 
            && TDCACCRU.FST_FLG == '1') {
            WS_ACO_AC = WS_ACO_AC_1;
            B500_WRT_TDTSMST();
            WS_ACO_AC = WS_ACO_AC_2;
            TDRSMST.KEY.ACO_AC = WS_ACO_AC;
        }
        if (((WS_AC_TYPE.equalsIgnoreCase("037") 
            || WS_AC_TYPE.equalsIgnoreCase("021")) 
            && (TDCPIOD.EXP_PRM.LMT_FLG != '0' 
            && TDCPIOD.EXP_PRM.LMT_FLG != 'N')) 
            || (WS_AC_TYPE.equalsIgnoreCase("020") 
            && TDCCEINT.RACD_FLG == 'Y')) {
            if (TDCACCRU.CDS_DT == 0) {
                B111_CHANG_TDZLML();
            }
        }
        if ((TDRSMST.PRDAC_CD.equalsIgnoreCase("035") 
            || TDRSMST.PRDAC_CD.equalsIgnoreCase("036")) 
            || TDCACCRU.OPT == '2') {
            if (TDCACCRU.OPT == '2') {
                IBS.init(SCCGWA, DDRCCY);
                DDRCCY.CUS_AC = TDCACCRU.OPP_AC_CNO;
                DDRCCY.CCY = TDRSMST.CCY;
                DDRCCY.CCY_TYPE = TDRSMST.CCY_TYP;
                T000_READ_DDTCCY();
                TDRSMST.OWNER_BK = DDRCCY.OWNER_BK;
                TDRSMST.OWNER_BR = DDRCCY.OPEN_DP;
                TDRSMST.OWNER_BRDP = DDRCCY.OWNER_BR;
                TDRSMST.CHE_BR = DDRCCY.OWNER_BRDP;
            } else {
                TDRSMST.OWNER_BK = TDRCMST.OWNER_BK;
                TDRSMST.OWNER_BR = TDRCMST.OWNER_BR;
                TDRSMST.OWNER_BRDP = TDRCMST.OWNER_BRDP;
                TDRSMST.CHE_BR = TDRCMST.CHE_BR;
            }
        }
        CEP.TRC(SCCGWA, TDRCMST.OWNER_BK);
        CEP.TRC(SCCGWA, TDRCMST.OWNER_BR);
        CEP.TRC(SCCGWA, TDRCMST.OWNER_BRDP);
        CEP.TRC(SCCGWA, TDRCMST.CHE_BR);
        CEP.TRC(SCCGWA, TDRSMST.CHE_BR);
        WS_CHE_BR = TDRSMST.CHE_BR;
        CEP.TRC(SCCGWA, WS_CHE_BR);
        if (WS_GRP_FLG != 'N' 
            && WS_FULL != 'Y') {
            B500_WRT_IREV();
        }
        if ((WS_AC_TYPE.equalsIgnoreCase("035") 
            || WS_AC_TYPE.equalsIgnoreCase("036")) 
            && TDCACCRU.FST_FLG == '1') {
            WS_ACO_AC = WS_ACO_AC_1;
            B500_WRT_IREV();
            WS_ACO_AC = WS_ACO_AC_2;
        }
        if ((TDCACCRU.INSTR_MTH != '0' 
            && TDCACCRU.INSTR_MTH != ' ')) {
            CEP.TRC(SCCGWA, TDCACCRU.INSTR_MTH);
            if ((WS_GRP_FLG != 'N') 
                && (WS_FULL != 'Y')) {
                B510_WRT_INST_INF();
            }
            if ((WS_AC_TYPE.equalsIgnoreCase("035") 
                || WS_AC_TYPE.equalsIgnoreCase("036")) 
                && TDCACCRU.FST_FLG == '1') {
                WS_ACO_AC = WS_ACO_AC_1;
                B510_WRT_INST_INF();
                WS_ACO_AC = WS_ACO_AC_2;
            }
        }
        if (TDCPIOD.OTH_PRM.PLAN_FLG == '1' 
            || (TDCACCRU.PERD_TYP == '1' 
            || TDCACCRU.PERD_TYP == '2')) {
            B520_WRT_CDI();
            CEP.TRC(SCCGWA, "EDU MAX CHECK");
            CEP.TRC(SCCGWA, DPCPARMP.AC_TYPE);
            if (DPCPARMP.AC_TYPE.equalsIgnoreCase("026")) {
                WS_BAL_EDU = TDRCDI.PLAN_NUM * TDCACCRU.CD_AMT;
                CEP.TRC(SCCGWA, TDRCDI.PLAN_NUM);
                CEP.TRC(SCCGWA, TDCACCRU.CD_AMT);
                CEP.TRC(SCCGWA, WS_BAL_EDU);
                CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.CCY_INF[1-1].MAX_AMT);
                if (WS_BAL_EDU > TDCPIOD.OTH_PRM.CCY_INF[1-1].MAX_AMT 
                    && TDCPIOD.OTH_PRM.CCY_INF[1-1].MAX_AMT != 0) {
                    CEP.TRC(SCCGWA, "EDU BAL OVER MAX AMT");
                    CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_EDU_OVER_MAX_BAL);
                }
            }
            if (TDCPIOD.OTH_PRM.PLAN_FLG == '1') {
                B300_WRT_TDTDWHH();
            }
        }
        CEP.TRC(SCCGWA, WS_SPE_FLG);
        CEP.TRC(SCCGWA, TDCACCRU.BV_NO);
        if (TDCACCRU.BV_TYP == '1' 
            || TDCACCRU.BV_TYP == '2' 
            || TDCACCRU.BV_TYP == '3' 
            || TDCACCRU.BV_TYP == '5' 
            || TDCACCRU.BV_TYP == '6' 
            || TDCACCRU.BV_TYP == '7' 
            || TDCACCRU.BV_TYP == '8' 
            || TDCACCRU.OPT == '3' 
            || TDCACCRU.OPT == '2') {
            if (TDCACCRU.BV_TYP == '2' 
                || TDCACCRU.BV_TYP == '3' 
                || TDCACCRU.BV_TYP == '5' 
                || TDCACCRU.BV_TYP == '6' 
                || TDCACCRU.BV_TYP == '7' 
                || TDCACCRU.BV_TYP == '8') {
                B580_WRT_BVT_INF();
            }
        }
        CEP.TRC(SCCGWA, TDCACCRU.BV_TYP);
        if (TDCACCRU.BV_TYP == '1') {
            B560_RWT_BVT_YBT();
            B560_WRT_YBTP_INF();
            if (TDCACCRU.PRT_FLG == '0') {
                B600_OUTPUT_YBTP_INF();
            }
        }
        if (TDCACCRU.BV_TYP == '2') {
            B570_WRT_PBP_INF();
            B610_OUTPUT_PBP_INF();
        }
        CEP.TRC(SCCGWA, TDRSMST.PRDAC_CD);
        if (TDCPIOD.EXP_PRM.ERLY_TYP == '5' 
            || TDCPIOD.EXP_PRM.LATE_TYP == '5' 
            || TDCPIOD.EXP_PRM.RES_TYP == '5') {
            B740_WRT_TDTAINT();
        }
        B130_CHK_STS_TBL();
        CEP.TRC(SCCGWA, "QQQQQQ");
        CEP.TRC(SCCGWA, WS_GROP);
        CEP.TRC(SCCGWA, WS_HIS_FLG);
        if (TDCACCRU.VAL_DT <= SCCGWA.COMM_AREA.AC_DATE) {
            if (WS_GROP.equalsIgnoreCase("FRST")) {
            } else {
                if (!WS_GROP.equalsIgnoreCase("NFST")) {
                    B270_WRT_FHIS();
                } else {
                    if (WS_HIS_FLG == 'Y') {
                        B270_WRT_FHIS();
                    }
                }
            }
        } else {
            B200_17_WRI_NFIN_HIS_PROC();
        }
        if (!WS_AC_TYPE.equalsIgnoreCase("035") 
            && !WS_AC_TYPE.equalsIgnoreCase("036")) {
            B730_WRT_BPTOCAC();
        }
        if ((WS_AC_TYPE.equalsIgnoreCase("035") 
            || WS_AC_TYPE.equalsIgnoreCase("036")) 
            && TDCACCRU.FST_FLG != '1') {
            IBS.init(SCCGWA, CICQACAC);
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.AC_NO = TDCACCRU.AC_NO;
            T000_GROUP_TDTSMST();
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = TDCACCRU.AC_NO;
            S000_CALL_CIZQACAC();
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            T000_READU_SMST();
            if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                if (TDRSMST.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
                    || TDRSMST.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
                    || TDRSMST.BAL < TDRSMST.HBAL) {
                    CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_ALREADY_HLD);
                }
            }
            CEP.TRC(SCCGWA, "UPDATE BAL");
            CEP.TRC(SCCGWA, TDCACCRU.TXN_AMT);
            CEP.TRC(SCCGWA, WS_SMST_AMT);
            CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
            TDRSMST.BAL = WS_SMST_AMT;
            TDRSMST.RMK_100 = "XXTJIEXI";
            CEP.TRC(SCCGWA, TDRSMST.BAL);
            CEP.TRC(SCCGWA, TDRSMST.RMK_100);
            T000_REWRITE_SMST();
            CEP.TRC(SCCGWA, TDRSMST.BAL);
        }
        if ((WS_GRP_FLG != 'N') 
            && (WS_FULL != 'Y')) {
            B700_INQ_GL_MASTER();
            B750_WRT_GL_MASTER();
        }
        if (TDCACCRU.VAL_DT <= SCCGWA.COMM_AREA.AC_DATE) {
            if (WS_AC_TYPE.equalsIgnoreCase("035") 
                && WS_FULL != 'N' 
                && WS_GRP_FLG == 'Y') {
                CEP.TRC(SCCGWA, "GET ACO-AC");
                IBS.init(SCCGWA, TDRSMST);
                TDRSMST.AC_NO = TDCACCRU.AC_NO;
                T000_READ_SMST_FST();
                TDCACCRU.ACO_AC = TDRSMST.KEY.ACO_AC;
                CEP.TRC(SCCGWA, TDCACCRU.ACO_AC);
            }
            B260_WRT_VCH_EVENT();
        } else {
            R000_CALL_DC_HLD();
        }
        CEP.TRC(SCCGWA, TDCACCRU.ACO_AC);
        CEP.TRC(SCCGWA, DCCUHLD.DATA.HLD_NO);
        if (TDCACCRU.VAL_DT > SCCGWA.COMM_AREA.AC_DATE) {
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.KEY.ACO_AC = TDCACCRU.ACO_AC;
            T000_READU_SMST();
            TDRSMST.BUSI_NO = DCCUHLD.DATA.HLD_NO;
            TDRSMST.ACO_STS = '3';
            T000_REWRITE_SMST();
            CEP.TRC(SCCGWA, WS_CI_TYP);
            if (WS_CI_TYP == '1') {
                IBS.init(SCCGWA, TDRSMST);
                TDRCMST.KEY.AC_NO = TDCACCRU.AC_NO;
                T000_READU_CMST();
                TDRCMST.STS = '3';
                if (TDRCMST.RMK_100 == null) TDRCMST.RMK_100 = "";
                JIBS_tmp_int = TDRCMST.RMK_100.length();
                for (int i=0;i<200-JIBS_tmp_int;i++) TDRCMST.RMK_100 += " ";
                JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.JRN_NO;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<12-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                TDRCMST.RMK_100 = TDRCMST.RMK_100.substring(0, 50 - 1) + JIBS_tmp_str[0] + TDRCMST.RMK_100.substring(50 + 12 - 1);
                T000_REWRITE_CMST();
            }
        }
        CEP.TRC(SCCGWA, WS_FULL);
        if (SCCGWA.COMM_AREA.AGENT_FLG == 'Y') {
            CEP.TRC(SCCGWA, TDCACCRU.ANY_FLG);
            if (TDCACCRU.ANY_FLG != 'S') {
                B180_AGENT_INF_PORC();
            }
        }
    }
    public void R000_GET_BRINFO() throws IOException,SQLException,Exception {
        if ((WS_AC_TYPE.equalsIgnoreCase("035") 
            || WS_AC_TYPE.equalsIgnoreCase("036")) 
            || TDCACCRU.OPT == '2') {
            if (TDCACCRU.OPT == '2') {
                CEP.TRC(SCCGWA, "661");
                IBS.init(SCCGWA, DDRCCY);
                DDRCCY.CUS_AC = TDCACCRU.OPP_AC_CNO;
                DDRCCY.CCY = TDCACCRU.CCY;
                DDRCCY.CCY_TYPE = TDCACCRU.CCY_TYP;
                T000_READ_DDTCCY();
                WS_OWNER_BK = DDRCCY.OWNER_BK;
                WS_OWNER_BR = DDRCCY.OPEN_DP;
                WS_OWNER_BRDP = DDRCCY.OWNER_BR;
                WS_CHE_BR = DDRCCY.OWNER_BRDP;
            } else {
                CEP.TRC(SCCGWA, TDRCMST.OWNER_BK);
                CEP.TRC(SCCGWA, TDRCMST.OWNER_BR);
                CEP.TRC(SCCGWA, TDRCMST.OWNER_BRDP);
                CEP.TRC(SCCGWA, TDRCMST.CHE_BR);
                CEP.TRC(SCCGWA, "662");
                WS_OWNER_BK = TDRCMST.OWNER_BK;
                WS_OWNER_BR = TDRCMST.OWNER_BR;
                WS_OWNER_BRDP = TDRCMST.OWNER_BRDP;
                WS_CHE_BR = TDRCMST.CHE_BR;
            }
        } else {
            CEP.TRC(SCCGWA, "663");
            WS_OWNER_BR = BPCPORUP.DATA_INFO.BBR;
            WS_OWNER_BRDP = BPCPORUP.DATA_INFO.BBR;
            WS_CHE_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            WS_OWNER_BK = BPCPORUP.DATA_INFO.BR;
        }
    }
    public void B180_AGENT_INF_PORC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSAGEN);
        CICSAGEN.FUNC = 'A';
        CICSAGEN.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        CICSAGEN.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        CICSAGEN.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        CICSAGEN.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CICSAGEN.TLR_NO = SCCGWA.COMM_AREA.TL_ID;
        CICSAGEN.CI_NO = CICCUST.O_DATA.O_CI_NO;
        CICSAGEN.OUT_AC = TDCACCRU.AC_NO;
        CEP.TRC(SCCGWA, CICSAGEN.OUT_AC);
        CICSAGEN.ID_TYP = CICGAGA_AGENT_AREA.ID_TYP;
        CICSAGEN.ID_NO = CICGAGA_AGENT_AREA.ID_NO;
        CICSAGEN.AGENT_TP = "3";
        CICSAGEN.CI_NAME = CICGAGA_AGENT_AREA.CI_NM;
        CICSAGEN.PHONE = CICGAGA_AGENT_AREA.PHONE;
        S000_CALL_CIZSAGEN();
    }
    public void R000_CHECK_YBT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCUPARM);
        TDCUPARM.FUNC = 'I';
        S000_CALL_TDZUPARM();
        CEP.TRC(SCCGWA, TDCUPARM.PARM_DATA.YBT_LINE_LMT);
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.AC_NO = TDCACCRU.AC_NO;
        T000_GROP_SMSTYBT();
        WS_SPARM_DATA.WS_SYBT_LINE_LMT = (short) (TDCUPARM.PARM_DATA.YBT_LINE_LMT / 2 - 1);
        CEP.TRC(SCCGWA, WS_SPARM_DATA.WS_SYBT_LINE_LMT);
        CEP.TRC(SCCGWA, WS_YBT_LINE);
        if (WS_YBT_LINE > WS_SPARM_DATA.WS_SYBT_LINE_LMT) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_YBTLINE_FULL);
        }
    }
    public void R000_CHECK_TSSTS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
        CEP.TRC(SCCGWA, TDRSMST.STSW);
        CEP.TRC(SCCGWA, TDRSMST.BAL);
        CEP.TRC(SCCGWA, TDRSMST.HBAL);
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1") 
            || TDRSMST.STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_TMP_HLD);
        }
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
            || TDRSMST.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_ALREADY_HLD);
        }
        if (TDRSMST.BAL - TDRSMST.HBAL < TDCACCRU.TXN_AMT) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_ALREADY_HLD);
        }
    }
    public void B000_MAIN_PROC_CR_CANCEL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.KEY.ACO_AC = TDCACCRU.ACO_AC;
        T000_READU_SMST();
        B130_CHK_STS_TBL();
        CEP.TRC(SCCGWA, TDRSMST.BAL);
        CEP.TRC(SCCGWA, TDCACCRU.TXN_AMT);
        TDRSMST.BAL = TDRSMST.BAL - TDCACCRU.TXN_AMT;
        TDRSMST.PROC_NUM = (short) (TDRSMST.PROC_NUM + 1);
        TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_SMST();
        IBS.init(SCCGWA, TDRGROP);
        TDRGROP.KEY.DRAW_DT = SCCGWA.COMM_AREA.AC_DATE;
        TDRGROP.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
        T000_READU_GROP_CANCEL();
        if (WS_CGROP_FLG == 'Y') {
            TDRGROP.REP_BAL = TDRGROP.REP_BAL - TDCACCRU.TXN_AMT;
            T000_REWRITE_GROP_CANCEL();
        }
    }
    public void B000_MAIN_PROC_KH_CANCEL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRCMST);
        if (TDCACCRU.AC_NO.trim().length() > 0 
            && TDCACCRU.OPT != '2' 
            && TDCACCRU.BV_TYP != '4') {
            TDRCMST.KEY.AC_NO = TDCACCRU.AC_NO;
            T000_READU_CMST();
        }
        B015_READU_SMST_KH();
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (!TDRSMST.STSW.substring(2 - 1, 2 + 2 - 1).equalsIgnoreCase("00")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_ALREADY_HLD);
        }
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (!TDRSMST.STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("0")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_IS_IMPAWNED);
        }
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (!TDRSMST.STSW.substring(7 - 1, 7 + 2 - 1).equalsIgnoreCase("00")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_HAS_INNER_HOLD);
        }
        CEP.TRC(SCCGWA, TDCACCRU.PROD_CD);
        CEP.TRC(SCCGWA, TDRSMST.PROD_CD);
        WS_PROD_CD = TDCACCRU.PROD_CD;
        if (TDCACCRU.PROD_CD.trim().length() == 0) {
            WS_PROD_CD = TDRSMST.PROD_CD;
        }
        R000_GET_SALE_PROD_INFO();
        WS_AC_TYPE = BPCPQPRD.AC_TYPE;
        CEP.TRC(SCCGWA, TDRSMST.GUAR_BAL);
        CEP.TRC(SCCGWA, TDRSMST.BAL);
        CEP.TRC(SCCGWA, TDCACCRU.TXN_AMT);
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_IS_IMPAWNED);
        }
        if (TDCACCRU.OPT == '3') {
            if (TDCACCRU.TXN_AMT > TDRSMST.BAL - TDRSMST.GUAR_BAL) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CANOT_GUALCANEL);
            }
        }
        CEP.TRC(SCCGWA, TDCACCRU.BV_TYP);
        if (TDCACCRU.BV_TYP == '2' 
            || TDCACCRU.BV_TYP == '3' 
            || TDCACCRU.BV_TYP == '5' 
            || TDCACCRU.BV_TYP == '6' 
            || TDCACCRU.BV_TYP == '7' 
            || TDCACCRU.BV_TYP == '8') {
            B017_READ_BVT();
            CEP.TRC(SCCGWA, TDRBVT.STSW);
            if (TDRBVT.STSW == null) TDRBVT.STSW = "";
            JIBS_tmp_int = TDRBVT.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
            if (TDRBVT.STSW == null) TDRBVT.STSW = "";
            JIBS_tmp_int = TDRBVT.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
            if (TDRBVT.STSW == null) TDRBVT.STSW = "";
            JIBS_tmp_int = TDRBVT.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
            if (TDRBVT.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
                || TDRBVT.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
                || TDRBVT.STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NORMAL_REG_LOS);
            }
        }
        B020_GET_CI_INF_KH();
        B030_GET_PRD_INF_KH();
        B100_CHK_PRD_CODE();
        B130_CHK_STS_TBL();
        if (WS_SPE_FLG == 'D' 
            || WS_SPE_FLG == 'Z') {
            B260_WRT_VCH_DAIFU();
        }
        CEP.TRC(SCCGWA, WS_AC_TYPE);
        if (!WS_AC_TYPE.equalsIgnoreCase("037")) {
            if (!WS_AC_TYPE.equalsIgnoreCase("035") 
                && !WS_AC_TYPE.equalsIgnoreCase("036")) {
                B270_WRT_FHIS();
            }
        } else {
            CEP.TRC(SCCGWA, "PR021701");
            CEP.TRC(SCCGWA, WS_CI_TYP);
            if (WS_CI_TYP != '1') {
                R000_CALL_DC_HLD();
            } else {
                B200_17_WRI_NFIN_HIS_PROC();
            }
        }
        if (TDCACCRU.TXN_AMT > DPCPARMP.LARGE_AMT) {
            B290_REG_LARGE_AMT_CANCEL();
        }
        if (TDCACCRU.BV_TYP == '2' 
            || TDCACCRU.BV_TYP == '3' 
            || TDCACCRU.BV_TYP == '6' 
            || TDCACCRU.BV_TYP == '7') {
            B200_BV_USE();
        }
        B500_REWRT_SMST_KH();
        if (TDCACCRU.BV_TYP == '4') {
            B017_GET_CARD_INF();
            R000_CHECK_CARD_PROC();
        }
        if (TDCACCRU.BV_TYP == '1') {
            B560_REWRT_YBTP_INF();
        }
        if (TDCACCRU.BV_TYP == '2') {
            B560_RWT_BVT_PBP();
            B570_WRT_PBP_INF();
        }
        if ((WS_AC_TYPE.equalsIgnoreCase("037") 
            || WS_AC_TYPE.equalsIgnoreCase("021") 
            || WS_AC_TYPE.equalsIgnoreCase("020"))) {
            B480_GET_PRODINF();
            CEP.TRC(SCCGWA, TDCPIOD.EXP_PRM.LMT_FLG);
        }
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        CEP.TRC(SCCGWA, TDRSMST.STSW.substring(66 - 1, 66 + 1 - 1));
        CEP.TRC(SCCGWA, WS_AC_TYPE);
        CEP.TRC(SCCGWA, TDCPIOD.ACTI_NO);
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (((WS_AC_TYPE.equalsIgnoreCase("037") 
            || WS_AC_TYPE.equalsIgnoreCase("021")) 
            && (TDCPIOD.EXP_PRM.LMT_FLG != '0' 
            && TDCPIOD.EXP_PRM.LMT_FLG != 'N')) 
            || (WS_AC_TYPE.equalsIgnoreCase("020") 
            && TDRSMST.STSW.substring(66 - 1, 66 + 1 - 1).equalsIgnoreCase("1"))) {
            if (TDCACCRU.CDS_DT == 0) {
                B111_CHANG_TDZLML();
            }
        }
        if (TDRCMST.PROD_CD.trim().length() == 0 
            && TDCACCRU.BV_TYP != '4' 
            && TDCACCRU.OPT != '2') {
            B230_REWRITE_CMST_CANCEL();
        }
        IBS.init(SCCGWA, CICSACAC);
        CICSACAC.FUNC = 'A';
        CICSACAC.DATA.PROD_CD = TDCACCRU.PROD_CD;
        CICSACAC.DATA.ACAC_NO = TDCACCRU.ACO_AC;
        CICSACAC.DATA.AGR_NO = TDCACCRU.AC_NO;
        CICSACAC.DATA.AGR_SEQ = TDCACCRU.AC_SEQ;
        CICSACAC.DATA.BV_NO = TDCACCRU.BV_NO;
        CICSACAC.DATA.CCY = TDCACCRU.CCY;
        CICSACAC.DATA.CR_FLG = TDCACCRU.CCY_TYP;
        S000_CALL_CIZSACAC();
        CEP.TRC(SCCGWA, TDRSMST.PRDAC_CD);
        CEP.TRC(SCCGWA, WS_CI_TYP);
        CEP.TRC(SCCGWA, TDRSMST.OPEN_DR_AC);
        if (TDRSMST.PRDAC_CD.equalsIgnoreCase("021") 
            && WS_CI_TYP == '1' 
            && TDRSMST.BV_TYP != '4') {
            IBS.init(SCCGWA, CICSACRL);
            CICSACRL.FUNC = 'D';
            CICSACRL.DATA.AC_NO = TDCACCRU.AC_NO;
            CICSACRL.DATA.REL_AC_NO = TDRSMST.OPEN_DR_AC;
            if (TDCACCRU.CD_AC.trim().length() > 0) {
                CICSACRL.DATA.REL_AC_NO = TDCACCRU.CD_AC;
            }
            CICSACRL.DATA.AC_REL = "07";
            S000_CALL_CIZSACRL();
        }
        if (TDRSMST.PRDAC_CD.equalsIgnoreCase("037") 
            && WS_CI_TYP == '1') {
            IBS.init(SCCGWA, CICSACRL);
            CICSACRL.FUNC = 'D';
            CICSACRL.DATA.AC_NO = TDCACCRU.AC_NO;
            CICSACRL.DATA.REL_AC_NO = TDRSMST.OPEN_DR_AC;
            if (TDCACCRU.CD_AC.trim().length() > 0) {
                CICSACRL.DATA.REL_AC_NO = TDCACCRU.CD_AC;
            }
            CICSACRL.DATA.AC_REL = "13";
            CICSACRL.DATA.DEFAULT = '1';
            S000_CALL_CIZSACRL();
        }
        if (!WS_AC_TYPE.equalsIgnoreCase("035") 
            && !WS_AC_TYPE.equalsIgnoreCase("036")) {
            B730_WRT_BPTOCAC_CANCEL();
        }
        CEP.TRC(SCCGWA, TDCACCRU.TXN_AMT);
        if ((WS_AC_TYPE.equalsIgnoreCase("035") 
            || WS_AC_TYPE.equalsIgnoreCase("036")) 
            && TDCACCRU.FST_FLG != '1') {
            CEP.TRC(SCCGWA, "BAL-CANCEL");
            IBS.init(SCCGWA, CICQACAC);
            IBS.init(SCCGWA, TDRSMST);
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = TDCACCRU.AC_NO;
            S000_CALL_CIZQACAC();
            TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            T000_READU_SMST();
            TDRSMST.BAL = TDRSMST.BAL - TDCACCRU.TXN_AMT;
            T000_REWRITE_SMST();
        }
        if (!TDCACCRU.CCY.equalsIgnoreCase("156") 
            && WS_CI_TYP == '1' 
            && TDCACCRU.CT_FLG == '0' 
            && TDCACCRU.TXN_AMT != 0) {
            IBS.init(SCCGWA, CICMLMT);
            CICMLMT.FUNC = 'A';
            CICMLMT.DATA.CI_NO = TDCACCRU.CI_NO;
            CICMLMT.DATA.LMT_TP = "01";
            CICMLMT.DATA.CMT = TDCACCRU.TXN_AMT;
            CICMLMT.DATA.CCY = TDCACCRU.CCY;
            S000_CALL_CIZMLMT();
        }
        if (TDCACCRU.FST_FLG == '1') {
            CEP.TRC(SCCGWA, "FIRST CR");
            WS_ACO_BK = TDCACCRU.ACO_AC;
            WS_AMT_BK = TDCACCRU.TXN_AMT;
            IBS.init(SCCGWA, CICQACAC);
            IBS.init(SCCGWA, TDRSMST);
            CICQACAC.FUNC = 'S';
            CICQACAC.DATA.AGR_NO = TDCACCRU.AC_NO;
            CICQACAC.DATA.AGR_SEQ = 1;
            S000_CALL_CIZQACAC();
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
            TDCACCRU.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            TDRSMST.KEY.ACO_AC = TDCACCRU.ACO_AC;
            B015_READU_SMST_KH();
            B500_REWRT_SMST_KH();
            IBS.init(SCCGWA, CICSACAC);
            CICSACAC.FUNC = 'A';
            CICSACAC.DATA.PROD_CD = TDCACCRU.PROD_CD;
            CICSACAC.DATA.ACAC_NO = TDCACCRU.ACO_AC;
            CICSACAC.DATA.AGR_NO = TDCACCRU.AC_NO;
            CICSACAC.DATA.AGR_SEQ = 1;
            CICSACAC.DATA.BV_NO = TDCACCRU.BV_NO;
            CICSACAC.DATA.CCY = TDCACCRU.CCY;
            CICSACAC.DATA.CR_FLG = TDCACCRU.CCY_TYP;
            S000_CALL_CIZSACAC();
            TDCACCRU.TXN_AMT = TDRSMST.BAL;
            B270_WRT_FHIS();
            B730_WRT_BPTOCAC_CANCEL();
            TDCACCRU.ACO_AC = WS_ACO_BK;
            TDCACCRU.TXN_AMT = WS_AMT_BK;
        }
    }
    public void B000_MAIN_PROC_GK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = TDCACCRU.AC_NO;
        CICQACAC.DATA.AGR_SEQ = TDCACCRU.AC_SEQ;
        S000_CALL_CIZQACAC();
        TDCACCRU.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        if (TDCACCRU.BV_TYP == '4' 
            && TDCACCRU.AC_NO.trim().length() == 0) {
            B017_GET_CARD_INF();
            TDCACCRU.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            B015_READU_SMST_GK();
        } else {
            B015_READU_SMST_GK();
        }
        if ((TDCACCRU.CCY.trim().length() > 0) 
            && (!TDCACCRU.CCY.equalsIgnoreCase(TDRSMST.CCY))) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CCY_I_ERR);
        }
        if ((TDCACCRU.CCY_TYP != ' ') 
            && (TDCACCRU.CCY_TYP != TDRSMST.CCY_TYP)) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CCY_TYP_ERROR);
        }
        if (TDRSMST.EXP_DATE <= SCCGWA.COMM_AREA.AC_DATE) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_EXP_ALRDY;
            S000_ERR_MSG_PROC();
        }
        T000_READ_IREV();
        WS_YBT_AC_FLAG = 'N';
        B016_READU_CDI();
        if (TDRSMST.BV_TYP != '4') {
            B017_READ_BVT();
        }
        WS_BAL_I = TDRCDI.CD_AMT * ( TDRCDI.NOR_NUM + TDRCDI.LOS_DNUM );
        CEP.TRC(SCCGWA, WS_BAL_I);
        CEP.TRC(SCCGWA, TDRSMST.BAL);
        if (WS_BAL_I > TDRSMST.BAL) {
            CEP.TRC(SCCGWA, "AC WAS DEDUCTED");
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_DEDUCTED_GK);
        }
        B020_GET_CI_INF_GK();
        B030_GET_PRD_INF_GK();
        B100_CHK_PRD_CODE();
        TDCACCRU.TERM = TDRSMST.TERM;
        B480_GET_PRODINF();
        B490_CHECK_PROD();
        if ((!DPCPARMP.AC_TYPE.equalsIgnoreCase("025") 
            && !DPCPARMP.AC_TYPE.equalsIgnoreCase("026"))) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_PRD_NOT_MATCH;
            S000_ERR_MSG_PROC();
        }
        B020_GET_CI_INF_KH();
        B021_CHK_AC_INFO();
        B130_CHK_STS_TBL();
        if (TDRCDI.LAST_DEAL_DATE != SCCGWA.COMM_AREA.AC_DATE) {
            B220_CAL_ACCU();
        }
        WS_CHE_BR = TDRSMST.CHE_BR;
        CEP.TRC(SCCGWA, WS_CHE_BR);
        B260_WRT_VCH_EVENT();
        B270_WRT_FHIS();
        if (TDCACCRU.TXN_AMT > DPCPARMP.LARGE_AMT) {
            B290_REG_LARGE_AMT();
        }
        if ((!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")) 
            && TDCACCRU.CT_FLG == '4') {
            B320_REG_ETAB();
        }
        B500_REWRT_SMST_GK();
        B500_UPD_HMST();
        B300_WRT_TDTDWHH();
        B520_REWRT_CDI();
        if (TDCACCRU.BV_TYP == '2') {
            B560_RWT_BVT_PBP();
            B570_WRT_PBP_INF();
            if (TDCACCRU.PRT_FLG == '0') {
                B610_OUTPUT_PBP_INF();
            }
        }
    }
    public void B000_MAIN_PROC_GK_CANCEL() throws IOException,SQLException,Exception {
        B015_READU_SMST_GK();
        T000_READ_IREV();
        B016_READU_CDI_CANCEL();
        if (TDRSMST.BV_TYP != '4') {
            B017_READ_BVT();
        }
        B020_GET_CI_INF_GK();
        B030_GET_PRD_INF_GK();
        B060_READU_WHH_CANCEL();
        B100_CHK_PRD_CODE();
        TDCACCRU.TERM = TDRSMST.TERM;
        B480_GET_PRODINF();
        B130_CHK_STS_TBL();
        B270_WRT_FHIS();
        if (TDCACCRU.TXN_AMT > DPCPARMP.LARGE_AMT) {
            B290_REG_LARGE_AMT_CANCEL();
        }
        if ((!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")) 
            && TDCACCRU.CT_FLG == '4') {
            B320_REG_ETAB_CANCEL();
        }
        B500_REWRT_SMST_GK_CANCEL();
        B500_UPD_HMST();
        B520_REWRT_CDI_CANCEL();
        B070_REWRITE_WHH_CANCEL();
        if (TDCACCRU.BV_TYP == '2') {
            B560_RWT_BVT_PBP();
            B570_WRT_PBP_INF();
        }
    }
    public void B230_REWRITE_CMST_CANCEL() throws IOException,SQLException,Exception {
        TDRCMST.STS = '2';
        TDRCMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRCMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDRCMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_CMST();
        IBS.init(SCCGWA, CICSACR);
        CICSACR.DATA.STSW = "ZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROS";
        CICSACR.FUNC = 'A';
        CICSACR.DATA.AGR_NO = TDCACCRU.AC_NO;
        CICSACR.DATA.ENTY_TYP = '1';
        CICSACR.DATA.CI_NO = TDCACCRU.CI_NO;
        if (CICSACR.DATA.STSW == null) CICSACR.DATA.STSW = "";
        JIBS_tmp_int = CICSACR.DATA.STSW.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) CICSACR.DATA.STSW += " ";
        CICSACR.DATA.STSW = "1" + CICSACR.DATA.STSW.substring(1);
        CICSACR.DATA.PROD_CD = TDCACCRU.PROD_CD;
        CICSACR.DATA.CNTRCT_TYP = WS_AC_TYPE;
        CICSACR.DATA.FRM_APP = "TD";
        CICSACR.DATA.CCY = TDCACCRU.CCY;
        CICSACR.DATA.SHOW_FLG = 'Y';
        CICSACR.DATA.AC_CNM = TDCACCRU.AC_NAME;
        CICSACR.DATA.OPEN_DT = SCCGWA.COMM_AREA.AC_DATE;
        S000_CALL_CIZSACR();
    }
    public void B010_READU_MMST() throws IOException,SQLException,Exception {
    }
    public void B010_READU_MMST() throws IOException,SQLException,Exception {
    }
    public void B111_CHANG_TDZLML() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCACCRU.ACTI_NO);
        CEP.TRC(SCCGWA, TDCACCRU.PROD_CD);
        IBS.init(SCCGWA, TDCLML);
        TDCLML.FUNC = 'B';
        TDCLML.ACTI_NO = TDCACCRU.ACTI_NO;
        TDCLML.PROD_CD = TDCACCRU.PROD_CD;
        TDCLML.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
            TDCLML.CHNL_NO = SCCGWA.COMM_AREA.BSP_FLG;
        } else {
            TDCLML.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        }
        CEP.TRC(SCCGWA, TDCLML.CHNL_NO);
        TDCLML.BAL = TDCACCRU.TXN_AMT;
        S000_CALL_TDZLML();
    }
    public void B015_READU_SMST_KH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.KEY.ACO_AC = TDCACCRU.ACO_AC;
        T000_READU_SMST();
        TDCACCRU.CCY_TYP = TDRSMST.CCY_TYP;
        T000_READ_IREV();
        CEP.TRC(SCCGWA, TDRSMST.ACO_STS);
        CEP.TRC(SCCGWA, TDCACCRU.ACO_AC);
        if (TDRSMST.ACO_STS != '0' 
            && TDRSMST.ACO_STS != '3') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED);
        }
        CEP.TRC(SCCGWA, TDCACCRU.TXN_AMT);
        CEP.TRC(SCCGWA, TDRSMST.BAL);
        if (TDCACCRU.TXN_AMT != TDRSMST.BAL) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_BAL_ERR);
        }
        if (TDRSMST.DRW_INT != 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_TCQ_CANNOT_CANCEL);
        }
        if (TDCACCRU.TXN_AMT > TDRSMST.BAL - TDRSMST.HBAL - TDRSMST.WBAL) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AMT_HOLD;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, TDCACCRU.BUSI_CTLW);
        CEP.TRC(SCCGWA, TDRSMST.PRDAC_CD);
        if (TDRSMST.PART_NUM != 0) {
            if (TDCACCRU.BUSI_CTLW == null) TDCACCRU.BUSI_CTLW = "";
            JIBS_tmp_int = TDCACCRU.BUSI_CTLW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) TDCACCRU.BUSI_CTLW += " ";
            if (TDCACCRU.BUSI_CTLW.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1") 
                && TDRSMST.PRDAC_CD.equalsIgnoreCase("036")) {
            } else {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_PART_IS_NOT_CANCEL);
            }
        }
        if (WS_MMO.trim().length() == 0) {
            B000_GET_MMO();
        }
    }
    public void B015_READU_SMST_GK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.KEY.ACO_AC = TDCACCRU.ACO_AC;
        T000_READU_SMST();
        if (TDRSMST.ACO_STS != '0') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED);
        }
        if (!TDCACCRU.CCY.equalsIgnoreCase(TDRSMST.CCY)) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CCY_NOT_MATCH);
        }
        WS_BAL = TDCACCRU.BAL;
        TDCACCRU.BAL = TDRSMST.BAL;
        IBS.init(SCCGWA, TDRCMST);
        TDRCMST.KEY.AC_NO = TDRSMST.AC_NO;
        T000_READ_CMST();
        if (TDRCMST.CROS_CR_FLG == '0') {
            if (TDRSMST.OWNER_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                IBS.init(SCCGWA, BPCPRGST);
                BPCPRGST.BNK = SCCGWA.COMM_AREA.TR_BANK;
                BPCPRGST.BR1 = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPCPRGST.BR2 = TDRSMST.OWNER_BR;
                CEP.TRC(SCCGWA, BPCPRGST.BR1);
                CEP.TRC(SCCGWA, BPCPRGST.BR2);
                S000_CALL_BPZPRGST();
                CEP.TRC(SCCGWA, BPCPRGST.FLAG);
                CEP.TRC(SCCGWA, BPCPRGST.LVL_RELATION);
                CEP.TRC(SCCGWA, BPCPRGST.BRANCH_FLG);
                if (BPCPRGST.BRANCH_FLG == 'Y') {
                } else {
                    CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CROS_CR_FLG_ERR);
                }
            }
        } else if (TDRCMST.CROS_CR_FLG == '1') {
        } else if (TDRCMST.CROS_CR_FLG == '2') {
            if (TDRSMST.OWNER_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CR_FLG_ERR);
            }
        }
        if (WS_MMO.trim().length() == 0) {
            B000_GET_MMO();
        }
    }
    public void B016_READU_CDI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRCDI);
        TDRCDI.KEY.ACO_AC = TDCACCRU.ACO_AC;
        T000_READU_CDI();
        WS_NEXT_DEAL_DATE = TDRCDI.NEXT_DEAL_DATE;
        TDCACCRU.NOR_NUM = TDRCDI.NOR_NUM;
        TDCACCRU.LOS_DNUM = TDRCDI.LOS_DNUM;
        TDCACCRU.LOS_NUM = TDRCDI.LOS_NUM;
        TDCACCRU.LAST_DT = TDRCDI.NEXT_DEAL_DATE;
        WS_CD_PERD = TDRCDI.CD_PERD;
        WS_PERD = "" + WS_CD_PERD;
        JIBS_tmp_int = WS_PERD.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) WS_PERD = "0" + WS_PERD;
    }
    public void B016_READU_CDI_CANCEL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRCDI);
        TDRCDI.KEY.ACO_AC = TDCACCRU.ACO_AC;
        T000_READU_CDI();
    }
    public void B017_GET_CARD_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = TDCACCRU.AC_NO;
        S000_CALL_CIZQACRI();
    }
    public void B017_READ_BVT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRBVT);
        CEP.TRC(SCCGWA, TDCACCRU.MAIN_FLG);
        if ((TDCACCRU.BV_TYP == '7' 
            && TDCACCRU.MAIN_FLG == 'Y') 
            || (TDCACCRU.BV_TYP == '8' 
            && TDCACCRU.MAIN_FLG == 'Y') 
            || (TDCACCRU.BV_TYP == '3' 
            && TDCACCRU.MAIN_FLG == 'Y')) {
            TDRBVT.KEY.AC_NO = TDCACCRU.ACO_AC;
        } else {
            TDRBVT.KEY.AC_NO = TDCACCRU.AC_NO;
        }
        T000_READU_BVT();
        TDCACCRU.BV_NO = TDRBVT.BV_NO;
        if (TDCACCRU.BV_TYP == '7' 
            || TDCACCRU.BV_TYP == '8' 
            || (TDCACCRU.BV_TYP == '1' 
            && WS_YBT_AC_FLAG != 'N')) {
            CEP.TRC(SCCGWA, "BVT");
            TDCACCRU.BV_CD = TDRBVT.BV_CD;
            TDCACCRU.BV_NO = TDRBVT.BV_NO;
        }
        CEP.TRC(SCCGWA, TDCACCRU.BV_NO);
        CEP.TRC(SCCGWA, TDRBVT.BV_NO);
        if (!TDCACCRU.BV_NO.equalsIgnoreCase(TDRBVT.BV_NO)) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_BV_NO_NOT_MATCH;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_GET_CI_INF_KH() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCACCRU.CI_NO);
        CEP.TRC(SCCGWA, TDCACCRU.CHNL_FLG);
        if (((SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            || TDCACCRU.CANCEL_FLG == 'Y') 
            && TDCACCRU.CHNL_FLG == 'Y' 
            && TDCACCRU.CI_NO.trim().length() == 0) 
            || (TDCACCRU.CI_NO.trim().length() == 0 
            && TDCACCRU.AC_NO.trim().length() > 0)) {
            CEP.TRC(SCCGWA, "CHNL-Y-YES");
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = TDCACCRU.AC_NO;
            S000_CALL_CIZACCU();
            WS_CI_TYP = CICACCU.DATA.CI_TYP;
            TDCACCRU.CI_NO = CICACCU.DATA.CI_NO;
        }
        CEP.TRC(SCCGWA, TDCACCRU.CI_NO);
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'C';
        CICCUST.DATA.CI_NO = TDCACCRU.CI_NO;
        S000_CALL_CIZCUST();
        WS_CI_TYP = CICCUST.O_DATA.O_CI_TYP;
        CEP.TRC(SCCGWA, WS_CI_TYP);
        if (CICCUST.O_DATA.O_SID_FLG == '2') {
            WS_FRG_IND = "NRA";
        }
    }
    public void B021_CHK_AC_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCACCRU.OPP_AC_CNO);
        CEP.TRC(SCCGWA, TDCACCRU.CT_FLG);
        CEP.TRC(SCCGWA, TDCACCRU.STL_AC);
        if (TDCACCRU.STL_AC.trim().length() > 0) {
            if (TDCACCRU.STL_AC == null) TDCACCRU.STL_AC = "";
            JIBS_tmp_int = TDCACCRU.STL_AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) TDCACCRU.STL_AC += " ";
            if (TDCACCRU.STL_AC.substring(26 - 1, 26 + 1 - 1).trim().length() > 0) {
                WS_AC = TDCACCRU.STL_AC;
                R000_CHK_AI_INFO();
            } else {
                WS_AC = TDCACCRU.STL_AC;
                R000_CHK_DD_INFO();
                R000_CHK_CI_INFO();
            }
        }
        CEP.TRC(SCCGWA, TDCACCRU.INT_AC);
        CEP.TRC(SCCGWA, TDCACCRU.INOUT_FLG);
        if (TDCACCRU.INT_AC.trim().length() > 0 
            && TDCACCRU.INOUT_FLG != '2') {
            if (TDCACCRU.INT_AC == null) TDCACCRU.INT_AC = "";
            JIBS_tmp_int = TDCACCRU.INT_AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) TDCACCRU.INT_AC += " ";
            if (TDCACCRU.INT_AC.substring(26 - 1, 26 + 1 - 1).trim().length() > 0) {
                WS_AC = TDCACCRU.INT_AC;
                R000_CHK_AI_INFO();
            } else {
                WS_AC = TDCACCRU.INT_AC;
                R000_CHK_DD_INFO();
                R000_CHK_CI_INFO();
            }
        }
        CEP.TRC(SCCGWA, TDCACCRU.OPP_AC_CNO);
        CEP.TRC(SCCGWA, TDCACCRU.CT_FLG);
        if (TDCACCRU.OPP_AC_CNO.trim().length() > 0 
            && (TDCACCRU.CT_FLG == '3' 
            || TDCACCRU.CT_FLG == '2' 
            || TDCACCRU.CT_FLG == '5' 
            || TDCACCRU.CT_FLG == '4')) {
            WS_AC = TDCACCRU.OPP_AC_CNO;
            WS_CHECK_OPP = 'Y';
            R000_CHK_DD_INFO();
            R000_CHK_CI_SAME();
        }
    }
    public void R000_CHK_CI_SAME() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = WS_AC;
        S000_CALL_CIZQACRI();
        CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
        CEP.TRC(SCCGWA, TDCACCRU.OPT);
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_CI_NO);
        if (!CICCUST.DATA.CI_NO.equalsIgnoreCase(CICQACRI.O_DATA.O_CI_NO) 
            && TDCACCRU.CD_AC.trim().length() == 0) {
            if (TDCPIOD.TXN_PRM.CUST_CTL == '0') {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_CI_NOT_SAME;
                S000_ERR_MSG_PROC();
            } else if (TDCPIOD.TXN_PRM.CUST_CTL == '1') {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_CI_NOT_SAME_AUTH;
                S000_ERR_MSG_PROC();
            } else if (TDCPIOD.TXN_PRM.CUST_CTL == '2') {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_CI_NOT_SAME_WARM;
                S000_ERR_MSG_PROC();
            } else {
            }
        }
    }
    public void B020_GET_CI_INF_GK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        if (TDRSMST.BV_TYP == '1') {
            CEP.TRC(SCCGWA, "DAN");
            CICACCU.DATA.AGR_NO = TDCACCRU.AC_NO;
        } else if (TDRSMST.BV_TYP == '4') {
            CICACCU.DATA.AGR_NO = TDCACCRU.AC_NO;
        } else {
            CICACCU.DATA.AGR_NO = TDCACCRU.AC_NO;
        }
        if (TDCACCRU.OPT == '2' 
            || TDCACCRU.OPT == '3') {
            if (TDCACCRU.AC_NO.trim().length() > 0) {
                CICACCU.DATA.AGR_NO = TDCACCRU.AC_NO;
            } else {
                CICACCU.DATA.AGR_NO = TDCACCRU.AC_NO;
            }
        }
        CEP.TRC(SCCGWA, CICACCU.DATA.AGR_NO);
        S000_CALL_CIZACCU();
        WS_CI_TYP = CICACCU.DATA.CI_TYP;
        TDCACCRU.CI_NO = CICACCU.DATA.CI_NO;
        CICCUST.O_DATA.O_SVR_LVL = CICACCU.DATA.SVRLVL;
    }
    public void B020_GET_CI_INF_WWKH() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCACCRU.CHNL_FLG);
        if (TDCACCRU.CHNL_FLG == 'Y' 
            && TDCACCRU.AC_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, "WWKH");
            IBS.init(SCCGWA, CICACCU);
            CEP.TRC(SCCGWA, TDCACCRU.BV_TYP);
            CICACCU.DATA.AGR_NO = TDCACCRU.AC_NO;
            S000_CALL_CIZACCU();
            CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
            if (CICACCU.DATA.CI_NO.trim().length() > 0 
                && !CICACCU.DATA.CI_NO.equalsIgnoreCase(TDCACCRU.CI_NO)) {
                TDCACCRU.CI_NO = CICACCU.DATA.CI_NO;
            }
        }
        CEP.TRC(SCCGWA, TDCACCRU.CI_NO);
    }
    public void B030_GET_PRD_INF_KH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPCKPD);
        BPCPCKPD.PRDT_CODE = TDCACCRU.PROD_CD;
        BPCPCKPD.CI_NO = TDCACCRU.CI_NO;
        S000_CALL_BPZPCKPD();
        IBS.init(SCCGWA, BPCPQPRD);
        CEP.TRC(SCCGWA, TDCACCRU.PROD_CD);
        BPCPQPRD.PRDT_CODE = TDCACCRU.PROD_CD;
        S000_CALL_BPZPQPRD();
        CEP.TRC(SCCGWA, BPCPQPRD.CUS_TYPE);
        CEP.TRC(SCCGWA, BPCPQPRD.CUS_PER_FLG);
        CEP.TRC(SCCGWA, "TEMP1 1031");
        CEP.TRC(SCCGWA, BPCPQPRD.PRDT_MODEL);
        CEP.TRC(SCCGWA, TDCACCRU.PRDMO_CD);
        CEP.TRC(SCCGWA, BPCPQPRD.AC_TYPE);
        if (BPCPQPRD.AC_TYPE.trim().length() == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_PRD_NOT_MATCH;
            S000_ERR_MSG_PROC();
        }
        if (WS_AC_TYPE.equalsIgnoreCase("033") 
            && !BPCPQPRD.AC_TYPE.equalsIgnoreCase("033") 
            && SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_BDP_VAL_AC;
            S000_ERR_MSG_PROC();
        }
        if (BPCPQPRD.STOP_SOLD_DATE > 0 
            && BPCPQPRD.STOP_SOLD_DATE <= SCCGWA.COMM_AREA.AC_DATE) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_PRD_STOP_SOLD;
            S000_ERR_MSG_PROC();
        }
        if (!BPCPQPRD.PRDT_MODEL.equalsIgnoreCase(TDCACCRU.PRDMO_CD)) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_PRD_NOT_MATCH;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPCPQPRD.AC_TYPE);
        DPCPARMP.AC_TYPE = BPCPQPRD.AC_TYPE;
    }
    public void B030_GET_PRD_INF_GK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = TDRSMST.PROD_CD;
        S000_CALL_BPZPQPRD();
        CEP.TRC(SCCGWA, WS_MAX_AMTC);
        if (!BPCPQPRD.PRDT_MODEL.equalsIgnoreCase(TDCACCRU.PRDMO_CD)) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_PRD_NOT_MATCH;
            S000_ERR_MSG_PROC();
        }
        DPCPARMP.AC_TYPE = BPCPQPRD.AC_TYPE;
        WS_PROD_CD = BPCPQPRD.PARM_CODE;
    }
    public void B040_GET_PRD_TYP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPDT);
        CEP.TRC(SCCGWA, BPCPQPRD.BUSI_TYPE);
        BPCPQPDT.PRDT_TYPE = BPCPQPRD.BUSI_TYPE;
        S000_CALL_BPZPQPDT();
    }
    public void B100_CHK_PRD_CODE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPLDPD);
        BPCPLDPD.KIND = '1';
        if (TDCACCRU.OPT == '0' 
            || TDCACCRU.OPT == '2' 
            || TDCACCRU.OPT == '3' 
            || TDCACCRU.OPT == '4') {
            BPCPLDPD.PRDT_CODE = TDCACCRU.PROD_CD;
        } else {
            BPCPLDPD.PRDT_CODE = TDRSMST.PROD_CD;
        }
        CEP.TRC(SCCGWA, BPCPLDPD);
        S000_CALL_BPZPLDPD();
    }
    public void B110_CHK_PRD_PARM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCQPMP);
        TDCQPMP.FUNC = 'I';
        TDCQPMP.PROD_CD = BPCPQPRD.PARM_CODE;
        WS_PROD_CD_PMPD = BPCPQPRD.PARM_CODE;
        TDCQPMP.DAT_PTR = TDCPROD;
        S000_CALL_TDZQPMP();
        TDCPRDP.PRDMO_CD = TDCPROD.PRDO_CDM;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.TXN_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.TXN_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.INT_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.INT_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.EXP_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.EXP_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.OTH_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.OTH_PRM);
        WS_REF_CCY = TDCPROD.PROD_DATA.OTH_PRM.REF_CCY;
        WS_CCY_FOUND = 'N';
        for (WS_CNT = 1; WS_CNT <= 24 
            && WS_CCY_FOUND != 'Y'; WS_CNT += 1) {
            if (TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].MIN_CCYC.equalsIgnoreCase(TDCACCRU.CCY)) {
                WS_CALD_FLG = TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].INT_RUL;
                CEP.TRC(SCCGWA, TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].RAT_CD);
                WS_IRAT_CD = TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].RAT_CD;
                CEP.TRC(SCCGWA, "B110-CHK");
                CEP.TRC(SCCGWA, WS_IRAT_CD);
                WS_PR_IRAT_CD = TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].PRRAT_CD;
                WS_CCY_FOUND = 'Y';
            }
        }
        if (TDCPRDP.TXN_PRM.OTH_FLG.equalsIgnoreCase("08") 
            && WS_OPP_AC_OSA_FLG == 'N' 
            && TDCACCRU.OPP_AC_CNO.trim().length() > 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_OPP_AC_NOT_OSA;
            S000_ERR_MSG_PROC();
        }
        if (TDCPRDP.TXN_PRM.OTH_FLG.equalsIgnoreCase("08") 
            && WS_INT_AC_OSA_FLG == 'N' 
            && TDCACCRU.INT_AC.trim().length() > 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_INT_AC_NOT_OSA;
            S000_ERR_MSG_PROC();
        }
        if (TDCPRDP.TXN_PRM.OTH_FLG.equalsIgnoreCase("08") 
            && WS_STL_AC_OSA_FLG == 'N' 
            && TDCACCRU.STL_AC.trim().length() > 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_STL_AC_NOT_OSA;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, "YSY");
        CEP.TRC(SCCGWA, TDCPRDP.TXN_PRM.OTH_FLG);
        if (TDCPRDP.TXN_PRM.OTH_FLG.equalsIgnoreCase(K_ZJ_TYPE) 
            || TDCPRDP.TXN_PRM.OTH_FLG.equalsIgnoreCase(K_DZ_TYPE)) {
            if (TDCPRDP.TXN_PRM.OTH_FLG.equalsIgnoreCase(K_ZJ_TYPE) 
                && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase(K_CHNL_ZJ)) {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_CHNL_IS_NOT_EBSX;
                S000_ERR_MSG_PROC();
            }
            if (TDCPRDP.TXN_PRM.OTH_FLG.equalsIgnoreCase(K_ZJ_TYPE) 
                && SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase(K_CHNL_ZJ)) {
                WS_SPE_FLG = 'Z';
            }
            if (TDCPRDP.TXN_PRM.OTH_FLG.equalsIgnoreCase(K_DZ_TYPE) 
                && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase(K_CHNL_DZ)) {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_CHNL_IS_NOT_EBSX;
                S000_ERR_MSG_PROC();
            }
            if (TDCPRDP.TXN_PRM.OTH_FLG.equalsIgnoreCase(K_DZ_TYPE) 
                && SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase(K_CHNL_DZ)) {
                WS_SPE_FLG = 'D';
            }
        }
        if (TDCPRDP.TXN_PRM.OTH_FLG.equalsIgnoreCase(K_PDE_TYPE)) {
            WS_SPE_FLG = 'P';
        }
        if (TDCPRDP.TXN_PRM.OTH_FLG.equalsIgnoreCase(K_CDE_TYPE)) {
            WS_SPE_FLG = 'C';
        }
        if (TDCPRDP.TXN_PRM.OTH_FLG.equalsIgnoreCase(K_JLT_TYPE)) {
            WS_SPE_FLG = 'J';
        }
        if (TDCPRDP.TXN_PRM.OTH_FLG.equalsIgnoreCase(K_LA_TYPE)) {
            WS_OCAC_FLAG = 'L';
        }
        CEP.TRC(SCCGWA, "CQJ3");
        WS_CCY_FOUND = 'N';
        for (WS_CNT = 1; WS_CNT <= 24; WS_CNT += 1) {
            if (TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].MIN_CCYC.equalsIgnoreCase(TDCACCRU.CCY)) {
                WS_MIN_AMTC = TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].MIN_AMTC;
                WS_MAX_AMTC = TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].MAX_AMT;
                WS_RUL_CDC = IBS.CLS2CPY(SCCGWA, TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].RUL_CD);
                WS_FORMAL_TERM = TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].TERM;
                WS_MIN_CCYC = TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].MIN_CCYC;
                WS_CALD_FLG = TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].INT_RUL;
                WS_ADD_AMTC = TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].ADD_AMTC;
                WS_W = WS_CNT;
                WS_CCY_FOUND = 'Y';
                WS_CNT = 99;
            }
        }
        if (WS_CCY_FOUND == 'N') {
            CEP.TRC(SCCGWA, TDCPRDP.OTH_PRM.CCY_TYP);
            CEP.TRC(SCCGWA, "TEST11");
            WS_MSGID = TDCMSG_ERROR_MSG.TD_CCY_NOT_MATCH;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, TDCACCRU.CCY);
        CEP.TRC(SCCGWA, WS_MIN_CCYC);
        R000_CHK_IF_DEF_PRD();
        if (WS_CCY_DEF_FLG == 'Y') {
            for (WS_CNT = 1; WS_CNT <= 24; WS_CNT += 1) {
                if (TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].MIN_CCYC.equalsIgnoreCase(TDCACCRU.CCY)) {
                    WS_MIN_AMTC = TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].MIN_AMTC;
                    WS_MAX_AMTC = TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].MAX_AMT;
                    WS_ADD_AMTC = TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].ADD_AMTC;
                    WS_CNT = 99;
                }
            }
        }
        if (WS_CCY_DEF_FLG == 'N') {
            for (WS_CNT = 1; WS_CNT <= 24; WS_CNT += 1) {
                if (TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].MIN_CCYC.equalsIgnoreCase(WS_REF_CCY)) {
                    WS_MIN_AMTC_REF = TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].MIN_AMTC;
                    WS_MAX_AMTC_REF = TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].MAX_AMT;
                    WS_ADD_AMTC_REF = TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].ADD_AMTC;
                    WS_CNT = 99;
                }
            }
            if (WS_MIN_AMTC_REF != 0) {
                CEP.TRC(SCCGWA, "COM-MIN-AMTC");
                WS_BUY_AMT = WS_MIN_AMTC_REF;
                R000_AMT_EX_PROC();
                CEP.TRC(SCCGWA, WS_SELL_AMT);
                WS_MIN_AMTC = WS_SELL_AMT;
            }
            CEP.TRC(SCCGWA, WS_MIN_AMTC);
            if (WS_MAX_AMTC_REF != 0) {
                CEP.TRC(SCCGWA, "COM-MAX-AMTC");
                WS_BUY_AMT = WS_MAX_AMTC_REF;
                R000_AMT_EX_PROC();
                WS_MAX_AMTC = WS_SELL_AMT;
            }
            if (WS_ADD_AMTC_REF != 0) {
                CEP.TRC(SCCGWA, "COM-MAX-AMTC");
                WS_BUY_AMT = WS_ADD_AMTC_REF;
                R000_AMT_EX_PROC();
                WS_ADD_AMTC = WS_SELL_AMT;
            }
        }
        if (WS_MIN_AMTC > 0) {
            CEP.TRC(SCCGWA, TDCACCRU.TXN_AMT);
            CEP.TRC(SCCGWA, WS_MIN_AMTC);
            if (TDCACCRU.TXN_AMT < WS_MIN_AMTC) {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_AMT_TOO_LITTLE;
                S000_ERR_MSG_PROC();
            }
        }
        WS_BAL_DZ = TDCACCRU.TXN_AMT - WS_MIN_AMTC;
        if (WS_ADD_AMTC > 0) {
            CEP.TRC(SCCGWA, WS_BAL_DZ);
            WS_BAL_YE = WS_BAL_DZ % WS_ADD_AMTC;
            WS_BAL_XY = (long) ((WS_BAL_DZ - WS_BAL_YE) / WS_ADD_AMTC);
            CEP.TRC(SCCGWA, WS_BAL_XY);
            CEP.TRC(SCCGWA, WS_BAL_YE);
            if (WS_BAL_YE != 0) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_NO_START_BAL_ERR);
            }
        }
        if (TDCACCRU.CT_FLG == '0' 
            && TDCPRDP.TXN_PRM.CASH_FLG != 'Y') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_CASH_NOT_ALLOWED;
            S000_ERR_MSG_PROC();
        }
        if (TDCACCRU.CT_FLG == '0' 
            && TDCACCRU.CCY_TYP == '2') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_CASH_NOT_ALLOWED;
            S000_ERR_MSG_PROC();
        }
        WS_D = 1;
        WS_X = 0;
        while (WS_D <= 9) {
            if (TDCPRDP.TXN_PRM.BV_TYP == null) TDCPRDP.TXN_PRM.BV_TYP = "";
            JIBS_tmp_int = TDCPRDP.TXN_PRM.BV_TYP.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) TDCPRDP.TXN_PRM.BV_TYP += " ";
            if (TDCPRDP.TXN_PRM.BV_TYP.substring(WS_D - 1, WS_D + 1 - 1).equalsIgnoreCase("Y")) {
                CEP.TRC(SCCGWA, WS_X);
                CEP.TRC(SCCGWA, WS_D);
                if (WS_BV_TYP_DESC == null) WS_BV_TYP_DESC = "";
                JIBS_tmp_int = WS_BV_TYP_DESC.length();
                for (int i=0;i<9-JIBS_tmp_int;i++) WS_BV_TYP_DESC += " ";
                JIBS_tmp_str[0] = "" + WS_X;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                WS_BV_TYP_DESC = WS_BV_TYP_DESC.substring(0, WS_D - 1) + JIBS_tmp_str[0] + WS_BV_TYP_DESC.substring(WS_D + 1 - 1);
                IBS.CPY2CLS(SCCGWA, WS_BV_TYP_DESC, REDEFINES76);
            }
            WS_X = (short) (WS_X + 1);
            WS_D = (short) (WS_D + 1);
        }
        CEP.TRC(SCCGWA, TDCACCRU.BV_TYP);
        CEP.TRC(SCCGWA, REDEFINES76.WS_BV_TYP_DESC1);
        CEP.TRC(SCCGWA, REDEFINES76.WS_BV_TYP_DESC2);
        CEP.TRC(SCCGWA, REDEFINES76.WS_BV_TYP_DESC3);
        CEP.TRC(SCCGWA, REDEFINES76.WS_BV_TYP_DESC4);
        CEP.TRC(SCCGWA, REDEFINES76.WS_BV_TYP_DESC5);
        CEP.TRC(SCCGWA, REDEFINES76.WS_BV_TYP_DESC6);
        CEP.TRC(SCCGWA, REDEFINES76.WS_BV_TYP_DESC7);
        CEP.TRC(SCCGWA, REDEFINES76.WS_BV_TYP_DESC8);
        CEP.TRC(SCCGWA, REDEFINES76.WS_BV_TYP_DESC9);
        if (TDCACCRU.BV_TYP != REDEFINES76.WS_BV_TYP_DESC1 
            && TDCACCRU.BV_TYP != REDEFINES76.WS_BV_TYP_DESC2 
            && TDCACCRU.BV_TYP != REDEFINES76.WS_BV_TYP_DESC3 
            && TDCACCRU.BV_TYP != REDEFINES76.WS_BV_TYP_DESC4 
            && TDCACCRU.BV_TYP != REDEFINES76.WS_BV_TYP_DESC5 
            && TDCACCRU.BV_TYP != REDEFINES76.WS_BV_TYP_DESC6 
            && TDCACCRU.BV_TYP != REDEFINES76.WS_BV_TYP_DESC7 
            && TDCACCRU.BV_TYP != REDEFINES76.WS_BV_TYP_DESC8 
            && TDCACCRU.BV_TYP != REDEFINES76.WS_BV_TYP_DESC9) {
            CEP.TRC(SCCGWA, "TEST3");
            WS_MSGID = TDCMSG_ERROR_MSG.TD_BV_TYP_NOT_ALLOW;
            S000_ERR_MSG_PROC();
        }
        WS_C = 1;
        while (WS_C <= 4) {
            if (TDCPRDP.TXN_PRM.DRAW_MTH == null) TDCPRDP.TXN_PRM.DRAW_MTH = "";
            JIBS_tmp_int = TDCPRDP.TXN_PRM.DRAW_MTH.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) TDCPRDP.TXN_PRM.DRAW_MTH += " ";
            CEP.TRC(SCCGWA, TDCPRDP.TXN_PRM.DRAW_MTH.substring(WS_C - 1, WS_C + 1 - 1));
            if (TDCPRDP.TXN_PRM.DRAW_MTH == null) TDCPRDP.TXN_PRM.DRAW_MTH = "";
            JIBS_tmp_int = TDCPRDP.TXN_PRM.DRAW_MTH.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) TDCPRDP.TXN_PRM.DRAW_MTH += " ";
            if (TDCPRDP.TXN_PRM.DRAW_MTH.substring(WS_C - 1, WS_C + 1 - 1).equalsIgnoreCase("Y")) {
                if (WS_DRAW_MTH_DESC == null) WS_DRAW_MTH_DESC = "";
                JIBS_tmp_int = WS_DRAW_MTH_DESC.length();
                for (int i=0;i<4-JIBS_tmp_int;i++) WS_DRAW_MTH_DESC += " ";
                JIBS_tmp_str[0] = "" + WS_C;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                WS_DRAW_MTH_DESC = WS_DRAW_MTH_DESC.substring(0, WS_C - 1) + JIBS_tmp_str[0] + WS_DRAW_MTH_DESC.substring(WS_C + 1 - 1);
                IBS.CPY2CLS(SCCGWA, WS_DRAW_MTH_DESC, REDEFINES87);
            }
            WS_C = (short) (WS_C + 1);
            CEP.TRC(SCCGWA, WS_C);
        }
        CEP.TRC(SCCGWA, REDEFINES87.WS_DRAW_MTH_PSW);
        CEP.TRC(SCCGWA, REDEFINES87.WS_DRAW_MTH_SIGN);
        CEP.TRC(SCCGWA, REDEFINES87.WS_DRAW_MTH_CERTI);
        CEP.TRC(SCCGWA, REDEFINES87.WS_DRAW_MTH_ANYWAY);
        CEP.TRC(SCCGWA, TDCACCRU.DRAW_MTH);
        if (TDCACCRU.CHK_PSW_FLG == 'Y' 
            && TDCACCRU.CHNL_FLG == 'Y') {
        } else {
            if (TDCACCRU.DRAW_MTH != ' ') {
                if (TDCACCRU.DRAW_MTH != REDEFINES87.WS_DRAW_MTH_PSW 
                    && TDCACCRU.DRAW_MTH != REDEFINES87.WS_DRAW_MTH_SIGN 
                    && TDCACCRU.DRAW_MTH != REDEFINES87.WS_DRAW_MTH_CERTI 
                    && TDCACCRU.DRAW_MTH != REDEFINES87.WS_DRAW_MTH_ANYWAY) {
                    CEP.TRC(SCCGWA, "TEST4");
                    CEP.TRC(SCCGWA, WS_VA_OPEN_DATE);
                    CEP.TRC(SCCGWA, DPCPARMP.AC_TYPE);
                    if (WS_VA_OPEN_DATE != SCCGWA.COMM_AREA.AC_DATE 
                        && (TDCACCRU.DRAW_MTH == '4' 
                        || TDCACCRU.DRAW_MTH == '2') 
                        && TDCACCRU.BV_TYP == '1' 
                        && (!DPCPARMP.AC_TYPE.equalsIgnoreCase("025") 
                        && !DPCPARMP.AC_TYPE.equalsIgnoreCase("026")) 
                        && !DPCPARMP.AC_TYPE.equalsIgnoreCase("027")) {
                    } else {
                        WS_MSGID = TDCMSG_ERROR_MSG.TD_DRAW_MTH_NOT_ALLOW;
                        S000_ERR_MSG_PROC();
                    }
                }
            }
        }
        if (TDCACCRU.DEPOSIT_TYP == 'Z' 
            || TDCACCRU.DEPOSIT_TYP == 'T' 
            || TDCACCRU.DEPOSIT_TYP == 'C' 
            || TDCACCRU.DEPOSIT_TYP == 'L') {
            if (WS_FORMAL_TERM == null) WS_FORMAL_TERM = "";
            JIBS_tmp_int = WS_FORMAL_TERM.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_FORMAL_TERM += " ";
            if (WS_FORMAL_TERM.substring(0, 1).equalsIgnoreCase("Y")) {
                REDEFINES93.WS_TERM_ONE_DAY = "D001";
                WS_TERM_DESC = IBS.CLS2CPY(SCCGWA, REDEFINES93);
            }
            if (WS_FORMAL_TERM == null) WS_FORMAL_TERM = "";
            JIBS_tmp_int = WS_FORMAL_TERM.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_FORMAL_TERM += " ";
            if (WS_FORMAL_TERM.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("Y")) {
                REDEFINES93.WS_TERM_SEVEN_DAY = "D007";
                WS_TERM_DESC = IBS.CLS2CPY(SCCGWA, REDEFINES93);
            }
            if (WS_FORMAL_TERM == null) WS_FORMAL_TERM = "";
            JIBS_tmp_int = WS_FORMAL_TERM.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_FORMAL_TERM += " ";
            if (WS_FORMAL_TERM.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("Y")) {
                REDEFINES93.WS_TERM_ONE_MONTH = "M001";
                WS_TERM_DESC = IBS.CLS2CPY(SCCGWA, REDEFINES93);
            }
            if (WS_FORMAL_TERM == null) WS_FORMAL_TERM = "";
            JIBS_tmp_int = WS_FORMAL_TERM.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_FORMAL_TERM += " ";
            if (WS_FORMAL_TERM.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("Y")) {
                REDEFINES93.WS_TERM_THREE_MONTH = "M003";
                WS_TERM_DESC = IBS.CLS2CPY(SCCGWA, REDEFINES93);
            }
            if (WS_FORMAL_TERM == null) WS_FORMAL_TERM = "";
            JIBS_tmp_int = WS_FORMAL_TERM.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_FORMAL_TERM += " ";
            if (WS_FORMAL_TERM.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("Y")) {
                REDEFINES93.WS_TERM_SIX_MONTH = "M006";
                WS_TERM_DESC = IBS.CLS2CPY(SCCGWA, REDEFINES93);
            }
            if (WS_FORMAL_TERM == null) WS_FORMAL_TERM = "";
            JIBS_tmp_int = WS_FORMAL_TERM.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_FORMAL_TERM += " ";
            if (WS_FORMAL_TERM.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("Y")) {
                REDEFINES93.WS_TERM_ONE_YEAR = "Y001";
                WS_TERM_DESC = IBS.CLS2CPY(SCCGWA, REDEFINES93);
            }
            if (WS_FORMAL_TERM == null) WS_FORMAL_TERM = "";
            JIBS_tmp_int = WS_FORMAL_TERM.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_FORMAL_TERM += " ";
            if (WS_FORMAL_TERM.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("Y")) {
                REDEFINES93.WS_TERM_TWO_YEAR = "Y002";
                WS_TERM_DESC = IBS.CLS2CPY(SCCGWA, REDEFINES93);
            }
            if (WS_FORMAL_TERM == null) WS_FORMAL_TERM = "";
            JIBS_tmp_int = WS_FORMAL_TERM.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_FORMAL_TERM += " ";
            if (WS_FORMAL_TERM.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("Y")) {
                REDEFINES93.WS_TERM_THREE_YEAR = "Y003";
                WS_TERM_DESC = IBS.CLS2CPY(SCCGWA, REDEFINES93);
            }
            if (WS_FORMAL_TERM == null) WS_FORMAL_TERM = "";
            JIBS_tmp_int = WS_FORMAL_TERM.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_FORMAL_TERM += " ";
            if (WS_FORMAL_TERM.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("Y")) {
                REDEFINES93.WS_TERM_FIVE_YEAR = "Y005";
                WS_TERM_DESC = IBS.CLS2CPY(SCCGWA, REDEFINES93);
            }
            if (WS_FORMAL_TERM == null) WS_FORMAL_TERM = "";
            JIBS_tmp_int = WS_FORMAL_TERM.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_FORMAL_TERM += " ";
            if (WS_FORMAL_TERM.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("Y")) {
                REDEFINES93.WS_TERM_SIX_YEAR = "Y006";
                WS_TERM_DESC = IBS.CLS2CPY(SCCGWA, REDEFINES93);
            }
            if (WS_FORMAL_TERM == null) WS_FORMAL_TERM = "";
            JIBS_tmp_int = WS_FORMAL_TERM.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_FORMAL_TERM += " ";
            if (WS_FORMAL_TERM.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("Y")) {
                REDEFINES93.WS_TERM_NOT_STANDARD = "S000";
                WS_TERM_DESC = IBS.CLS2CPY(SCCGWA, REDEFINES93);
            }
            CEP.TRC(SCCGWA, REDEFINES93.WS_TERM_ONE_DAY);
            CEP.TRC(SCCGWA, REDEFINES93.WS_TERM_SEVEN_DAY);
            CEP.TRC(SCCGWA, REDEFINES93.WS_TERM_ONE_MONTH);
            CEP.TRC(SCCGWA, REDEFINES93.WS_TERM_THREE_MONTH);
            CEP.TRC(SCCGWA, REDEFINES93.WS_TERM_SIX_MONTH);
            CEP.TRC(SCCGWA, REDEFINES93.WS_TERM_ONE_YEAR);
            CEP.TRC(SCCGWA, REDEFINES93.WS_TERM_TWO_YEAR);
            CEP.TRC(SCCGWA, REDEFINES93.WS_TERM_THREE_YEAR);
            CEP.TRC(SCCGWA, REDEFINES93.WS_TERM_FIVE_YEAR);
            CEP.TRC(SCCGWA, REDEFINES93.WS_TERM_SIX_YEAR);
            CEP.TRC(SCCGWA, REDEFINES93.WS_TERM_NOT_STANDARD);
            CEP.TRC(SCCGWA, TDCACCRU.TERM);
            if (!TDCACCRU.TERM.equalsIgnoreCase(REDEFINES93.WS_TERM_ONE_DAY) 
                && !TDCACCRU.TERM.equalsIgnoreCase(REDEFINES93.WS_TERM_SEVEN_DAY) 
                && !TDCACCRU.TERM.equalsIgnoreCase(REDEFINES93.WS_TERM_ONE_MONTH) 
                && !TDCACCRU.TERM.equalsIgnoreCase(REDEFINES93.WS_TERM_THREE_MONTH) 
                && !TDCACCRU.TERM.equalsIgnoreCase(REDEFINES93.WS_TERM_SIX_MONTH) 
                && !TDCACCRU.TERM.equalsIgnoreCase(REDEFINES93.WS_TERM_ONE_YEAR) 
                && !TDCACCRU.TERM.equalsIgnoreCase(REDEFINES93.WS_TERM_TWO_YEAR) 
                && !TDCACCRU.TERM.equalsIgnoreCase(REDEFINES93.WS_TERM_THREE_YEAR) 
                && !TDCACCRU.TERM.equalsIgnoreCase(REDEFINES93.WS_TERM_FIVE_YEAR) 
                && !TDCACCRU.TERM.equalsIgnoreCase(REDEFINES93.WS_TERM_SIX_YEAR) 
                && !TDCACCRU.TERM.equalsIgnoreCase(REDEFINES93.WS_TERM_NOT_STANDARD)) {
                if (REDEFINES93.WS_TERM_NOT_STANDARD.equalsIgnoreCase("S000")) {
                    CEP.TRC(SCCGWA, TDCPRDP.OTH_PRM.CCY_INF[WS_W-1].TERM1);
                    if (!TDCACCRU.TERM.equalsIgnoreCase(TDCPRDP.OTH_PRM.CCY_INF[WS_W-1].TERM1) 
                        && !TDCACCRU.TERM.equalsIgnoreCase(TDCPRDP.OTH_PRM.CCY_INF[WS_W-1].TERM2) 
                        && !TDCACCRU.TERM.equalsIgnoreCase(TDCPRDP.OTH_PRM.CCY_INF[WS_W-1].TERM3) 
                        && !TDCACCRU.TERM.equalsIgnoreCase(TDCPRDP.OTH_PRM.CCY_INF[WS_W-1].TERM4) 
                        && !TDCACCRU.TERM.equalsIgnoreCase(TDCPRDP.OTH_PRM.CCY_INF[WS_W-1].TERM5) 
                        && !TDCACCRU.TERM.equalsIgnoreCase(TDCPRDP.OTH_PRM.CCY_INF[WS_W-1].TERM6)) {
                        if (TDCACCRU.TERM.equalsIgnoreCase("S000") 
                            && TDCACCRU.EXP_DT > 0) {
                            WS_NOT_STANDARD_FLG = 'Y';
                        } else {
                            CEP.TRC(SCCGWA, TDCACCRU.EXP_DT);
                            WS_MSGID = TDCMSG_ERROR_MSG.TD_TERM_NOT_ALLOW;
                            S000_ERR_MSG_PROC();
                        }
                    } else {
                        WS_NOT_STANDARD_FLG = 'Y';
                    }
                } else {
                    WS_MSGID = TDCMSG_ERROR_MSG.TD_TERM_NOT_ALLOW;
                    S000_ERR_MSG_PROC();
                }
            }
        }
        CEP.TRC(SCCGWA, TDCPRDP.OTH_PRM.MAX_TERM);
        CEP.TRC(SCCGWA, TDCACCRU.TERM);
        if (!TDCACCRU.TERM.equalsIgnoreCase("S000")) {
            if (!REDEFINES93.WS_TERM_NOT_STANDARD.equalsIgnoreCase("S000")) {
                if (TDCACCRU.TERM.compareTo(TDCPRDP.OTH_PRM.MAX_TERM) > 0 
                    && TDCPRDP.OTH_PRM.MAX_TERM.trim().length() > 0) {
                    WS_MSGID = TDCMSG_ERROR_MSG.TD_NOT_GREAT_MAX_TERM;
                    S000_ERR_MSG_PROC();
                }
                if (TDCACCRU.TERM.compareTo(TDCPRDP.OTH_PRM.MIN_TERM) < 0 
                    && TDCPRDP.OTH_PRM.MIN_TERM.trim().length() > 0) {
                    WS_MSGID = TDCMSG_ERROR_MSG.TD_NOT_LESS_MIN_TERM;
                    S000_ERR_MSG_PROC();
                }
            } else {
                CEP.TRC(SCCGWA, TDCPRDP.OTH_PRM.MAX_TERM);
                CEP.TRC(SCCGWA, TDCPRDP.OTH_PRM.MIN_TERM);
                WS_EX_TERM = TDCACCRU.TERM;
                IBS.CPY2CLS(SCCGWA, WS_EX_TERM, REDEFINES188);
                R000_EXCHANGE_TERM();
                WS_ACCRU_TERM = WS_EX_TERM_NUM.WS_EX_TERM_DAY;
                CEP.TRC(SCCGWA, "INPUT-TERM");
                CEP.TRC(SCCGWA, WS_ACCRU_TERM);
                if (TDCPRDP.OTH_PRM.MAX_TERM.trim().length() > 0) {
                    WS_EX_TERM = TDCPRDP.OTH_PRM.MAX_TERM;
                    IBS.CPY2CLS(SCCGWA, WS_EX_TERM, REDEFINES188);
                    R000_EXCHANGE_TERM();
                    WS_PRDP_TERM = WS_EX_TERM_NUM.WS_EX_TERM_DAY;
                    CEP.TRC(SCCGWA, "MAX-TERM");
                    CEP.TRC(SCCGWA, WS_PRDP_TERM);
                    if (WS_ACCRU_TERM > WS_PRDP_TERM) {
                        WS_MSGID = TDCMSG_ERROR_MSG.TD_NOT_GREAT_MAX_TERM;
                        S000_ERR_MSG_PROC();
                    }
                }
                if (TDCPRDP.OTH_PRM.MIN_TERM.trim().length() > 0) {
                    WS_EX_TERM = TDCPRDP.OTH_PRM.MIN_TERM;
                    IBS.CPY2CLS(SCCGWA, WS_EX_TERM, REDEFINES188);
                    R000_EXCHANGE_TERM();
                    WS_PRDP_TERM = WS_EX_TERM_NUM.WS_EX_TERM_DAY;
                    CEP.TRC(SCCGWA, "MIN-TERM");
                    CEP.TRC(SCCGWA, WS_PRDP_TERM);
                    if (WS_ACCRU_TERM < WS_PRDP_TERM) {
                        WS_MSGID = TDCMSG_ERROR_MSG.TD_NOT_LESS_MIN_TERM;
                        S000_ERR_MSG_PROC();
                    }
                }
            }
        }
        CEP.TRC(SCCGWA, TDCPRDP.TXN_PRM.RSID_LMT);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_RESIDENT);
        if (TDCPRDP.TXN_PRM.RSID_LMT != '0' 
            && TDCPRDP.TXN_PRM.RSID_LMT != ' ') {
            if (TDCPRDP.TXN_PRM.RSID_LMT == '1' 
                && CICCUST.O_DATA.O_RESIDENT != '1') {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_CI_NOT_RESIDENT;
                S000_ERR_MSG_PROC();
            }
            if (TDCPRDP.TXN_PRM.RSID_LMT == '2' 
                && CICCUST.O_DATA.O_RESIDENT != '2') {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_CI_NOT_NON_RESIDENT;
                S000_ERR_MSG_PROC();
            }
        }
        if (TDCACCRU.DEPOSIT_TYP == 'Z' 
            || TDCACCRU.DEPOSIT_TYP == 'X' 
            || TDCACCRU.DEPOSIT_TYP == 'C' 
            || TDCACCRU.DEPOSIT_TYP == 'L') {
            WS_C = 1;
            WS_X = 0;
            while (WS_C <= 7) {
                if (TDCPRDP.EXP_PRM.INR_MTH == null) TDCPRDP.EXP_PRM.INR_MTH = "";
                JIBS_tmp_int = TDCPRDP.EXP_PRM.INR_MTH.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) TDCPRDP.EXP_PRM.INR_MTH += " ";
                if (TDCPRDP.EXP_PRM.INR_MTH.substring(WS_C - 1, WS_C + 1 - 1).equalsIgnoreCase("Y")) {
                    if (WS_INSTR_MTH_DESC == null) WS_INSTR_MTH_DESC = "";
                    JIBS_tmp_int = WS_INSTR_MTH_DESC.length();
                    for (int i=0;i<7-JIBS_tmp_int;i++) WS_INSTR_MTH_DESC += " ";
                    JIBS_tmp_str[0] = "" + WS_X;
                    JIBS_tmp_int = JIBS_tmp_str[0].length();
                    for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                    WS_INSTR_MTH_DESC = WS_INSTR_MTH_DESC.substring(0, WS_C - 1) + JIBS_tmp_str[0] + WS_INSTR_MTH_DESC.substring(WS_C + 1 - 1);
                    IBS.CPY2CLS(SCCGWA, WS_INSTR_MTH_DESC, REDEFINES106);
                }
                WS_X = (short) (WS_X + 1);
                WS_C = (short) (WS_C + 1);
            }
            CEP.TRC(SCCGWA, REDEFINES106.WS_PENDING_INDICA);
            CEP.TRC(SCCGWA, REDEFINES106.WS_TURN_DEMAND_DEP);
            CEP.TRC(SCCGWA, REDEFINES106.WS_TURN_TIME_DEP);
            CEP.TRC(SCCGWA, REDEFINES106.WS_PRIN_INT_ROLL);
            CEP.TRC(SCCGWA, REDEFINES106.WS_ADD_PRIN_ROLL);
            CEP.TRC(SCCGWA, REDEFINES106.WS_SUBTR_PRIN_ROLL);
            CEP.TRC(SCCGWA, REDEFINES106.WS_PRIN_ROLL);
            if (TDCACCRU.INSTR_MTH != REDEFINES106.WS_PENDING_INDICA 
                && TDCACCRU.INSTR_MTH != REDEFINES106.WS_TURN_DEMAND_DEP 
                && TDCACCRU.INSTR_MTH != REDEFINES106.WS_TURN_TIME_DEP 
                && TDCACCRU.INSTR_MTH != REDEFINES106.WS_PRIN_INT_ROLL 
                && TDCACCRU.INSTR_MTH != REDEFINES106.WS_ADD_PRIN_ROLL 
                && TDCACCRU.INSTR_MTH != REDEFINES106.WS_SUBTR_PRIN_ROLL 
                && TDCACCRU.INSTR_MTH != REDEFINES106.WS_PRIN_ROLL) {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_INSTR_MTH_NOT_ALLOW;
                S000_ERR_MSG_PROC();
            }
        }
        if (TDCACCRU.DEPOSIT_TYP == 'L') {
            if (TDCPRDP.OTH_PRM.PAY_PERD.trim().length() > 0) {
                if (TDCPRDP.OTH_PRM.PAY_PERD == null) TDCPRDP.OTH_PRM.PAY_PERD = "";
                JIBS_tmp_int = TDCPRDP.OTH_PRM.PAY_PERD.length();
                for (int i=0;i<4-JIBS_tmp_int;i++) TDCPRDP.OTH_PRM.PAY_PERD += " ";
                if (TDCPRDP.OTH_PRM.PAY_PERD.substring(0, 1).equalsIgnoreCase("Y")) {
                    WS_ONE_MONS = 1;
                }
                if (TDCPRDP.OTH_PRM.PAY_PERD == null) TDCPRDP.OTH_PRM.PAY_PERD = "";
                JIBS_tmp_int = TDCPRDP.OTH_PRM.PAY_PERD.length();
                for (int i=0;i<4-JIBS_tmp_int;i++) TDCPRDP.OTH_PRM.PAY_PERD += " ";
                if (TDCPRDP.OTH_PRM.PAY_PERD.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("Y")) {
                    WS_THREE_MONS = 3;
                }
                if (TDCPRDP.OTH_PRM.PAY_PERD == null) TDCPRDP.OTH_PRM.PAY_PERD = "";
                JIBS_tmp_int = TDCPRDP.OTH_PRM.PAY_PERD.length();
                for (int i=0;i<4-JIBS_tmp_int;i++) TDCPRDP.OTH_PRM.PAY_PERD += " ";
                if (TDCPRDP.OTH_PRM.PAY_PERD.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("Y")) {
                    WS_SIX_MONS = 6;
                }
                if (TDCACCRU.CD_PERD != WS_ONE_MONS 
                    && TDCACCRU.CD_PERD != WS_THREE_MONS 
                    && TDCACCRU.CD_PERD != WS_SIX_MONS) {
                    WS_MSGID = TDCMSG_ERROR_MSG.TD_PAY_PERD_NOT_ALLOW;
                    S000_ERR_MSG_PROC();
                }
            }
        }
        if (TDCACCRU.DEPOSIT_TYP == 'C' 
            || TDCACCRU.DEPOSIT_TYP == 'X') {
            if (TDCPRDP.OTH_PRM.INT_PERD.trim().length() > 0) {
                if (TDCPRDP.OTH_PRM.INT_PERD == null) TDCPRDP.OTH_PRM.INT_PERD = "";
                JIBS_tmp_int = TDCPRDP.OTH_PRM.INT_PERD.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) TDCPRDP.OTH_PRM.INT_PERD += " ";
                if (TDCPRDP.OTH_PRM.INT_PERD.substring(0, 1).equalsIgnoreCase("Y")) {
                    WS_ONE_MONS = 1;
                }
                if (TDCPRDP.OTH_PRM.INT_PERD == null) TDCPRDP.OTH_PRM.INT_PERD = "";
                JIBS_tmp_int = TDCPRDP.OTH_PRM.INT_PERD.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) TDCPRDP.OTH_PRM.INT_PERD += " ";
                if (TDCPRDP.OTH_PRM.INT_PERD.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("Y")) {
                    WS_THREE_MONS = 3;
                }
                if (TDCPRDP.OTH_PRM.INT_PERD == null) TDCPRDP.OTH_PRM.INT_PERD = "";
                JIBS_tmp_int = TDCPRDP.OTH_PRM.INT_PERD.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) TDCPRDP.OTH_PRM.INT_PERD += " ";
                if (TDCPRDP.OTH_PRM.INT_PERD.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("Y")) {
                    WS_SIX_MONS = 6;
                }
                if (TDCACCRU.CD_PERD != WS_ONE_MONS 
                    && TDCACCRU.CD_PERD != WS_THREE_MONS 
                    && TDCACCRU.CD_PERD != WS_SIX_MONS) {
                    CEP.TRC(SCCGWA, "CZF");
                    CEP.TRC(SCCGWA, TDCPRDP.OTH_PRM.INT_PERD);
                    WS_MSGID = TDCMSG_ERROR_MSG.TD_INT_PERD_NOT_ALLOW;
                    S000_ERR_MSG_PROC();
                }
            }
        }
        CEP.TRC(SCCGWA, "CQJ1");
        CEP.TRC(SCCGWA, TDCACCRU.INSTR_MTH);
        if (TDCACCRU.DEPOSIT_TYP == 'T') {
            if (TDCACCRU.INSTR_MTH != '0' 
                && TDCACCRU.INSTR_MTH != '3') {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_INSTR_MTH_NOT_ALLOW;
            }
        }
        IBS.init(SCCGWA, TDCIRULP);
        CEP.TRC(SCCGWA, TDCACCRU.SPRD_PCT);
        CEP.TRC(SCCGWA, TDCACCRU.SPRD_PNT);
        CEP.TRC(SCCGWA, TDCACCRU.INT_RAT);
        CEP.TRC(SCCGWA, TDCACCRU.INT_RUL_CD);
        CEP.TRC(SCCGWA, WS_RUL_CDC);
        if ((TDCACCRU.INT_RUL_CD.equalsIgnoreCase("ZZYH") 
            || WS_RUL_CDC.equalsIgnoreCase("ZZYH")) 
            && (!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
            TDCACCRU.INT_RUL_CD = " ";
            WS_RUL_CDC = " ";
        }
        if (TDCACCRU.SPRD_PCT == 0 
            && TDCACCRU.SPRD_PNT == 0 
            && TDCACCRU.INT_RAT == 0 
            && (TDCACCRU.INT_RUL_CD.trim().length() > 0 
            || WS_RUL_CDC.trim().length() > 0)) {
            IBS.init(SCCGWA, BPCPRMM);
            BPCPRMM.FUNC = '3';
            BPRPRMT.KEY.TYP = K_IRUL_TYP;
            if (TDCACCRU.INT_RUL_CD.trim().length() > 0) {
                BPRPRMT.KEY.CD = TDCACCRU.INT_RUL_CD;
            } else {
                BPRPRMT.KEY.CD = WS_RUL_CDC;
            }
            CEP.TRC(SCCGWA, BPRPRMT.KEY.CD);
            CEP.TRC(SCCGWA, WS_I);
            BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPCPRMM.DAT_PTR = BPRPRMT;
            S000_CALL_BPZPRMM();
            CEP.TRC(SCCGWA, BPCPRMM.EFF_DT);
            if (TDCPRDP.INT_PRM.RAT_INX == '0') {
                if (TDCACCRU.VAL_DT >= BPCPRMM.EFF_DT 
                    && TDCACCRU.VAL_DT < BPCPRMM.EXP_DT) {
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
                    IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCIRULP);
                }
            } else if (TDCPRDP.INT_PRM.RAT_INX == '1') {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCIRULP);
            } else {
            }
            CEP.TRC(SCCGWA, TDCIRULP);
            if (TDCIRULP.SPRD_OPT != 'A' 
                && TDCIRULP.SPRD_OPT != ' ') {
                WS_MATCH = 'N';
                for (WS_I = 1; WS_I <= 10; WS_I += 1) {
                    CEP.TRC(SCCGWA, TDCIRULP.CCY.CCYS[WS_I-1].SPT_CCY);
                    if (TDCIRULP.CCY.CCYS[WS_I-1].SPT_CCY.equalsIgnoreCase(TDCACCRU.CCY)) {
                        WS_I = 99;
                        WS_MATCH = 'Y';
                    }
                }
                if (WS_MATCH != 'Y') {
                    IBS.init(SCCGWA, TDCIRULP);
                }
            }
        }
        if (TDCPRDP.TXN_PRM.OTH_FLG.equalsIgnoreCase("08") 
            && TDCACCRU.PRV_RAT == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_LIAN_PRV_RAT_NEED;
            S000_ERR_MSG_PROC();
        }
    }
    public void B110_CHK_PRD_PARM_GK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
        IBS.init(SCCGWA, TDCQPMP);
        TDCQPMP.FUNC = 'I';
        TDCQPMP.PROD_CD = BPCPQPRD.PARM_CODE;
        TDCQPMP.DAT_PTR = TDCPROD;
        S000_CALL_TDZQPMP();
        TDCPRDP.PRDMO_CD = TDCPROD.PRDO_CDM;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.TXN_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.TXN_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.INT_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.INT_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.EXP_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.EXP_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.OTH_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.OTH_PRM);
        WS_CCY_FOUND = 'N';
        for (WS_CNT = 1; WS_CNT <= 24; WS_CNT += 1) {
            if (TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].MIN_CCYC.equalsIgnoreCase(TDCACCRU.CCY)) {
                WS_MIN_AMTC = TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].MIN_AMTC;
                WS_MAX_AMTC = TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].MAX_AMT;
                WS_RUL_CDC = IBS.CLS2CPY(SCCGWA, TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].RUL_CD);
                WS_FORMAL_TERM = TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].TERM;
                WS_MIN_CCYC = TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].MIN_CCYC;
                WS_CALD_FLG = TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].INT_RUL;
                WS_W = WS_CNT;
                WS_CCY_FOUND = 'Y';
                WS_CNT = 99;
            }
        }
        CEP.TRC(SCCGWA, TDCACCRU.TXN_AMT);
        CEP.TRC(SCCGWA, WS_MAX_AMTC);
        CEP.TRC(SCCGWA, TDCPRDP.OTH_PRM.CCY_TYP);
        CEP.TRC(SCCGWA, TDCACCRU.CCY);
        CEP.TRC(SCCGWA, TDCPRDP.OTH_PRM.CCY_INF[1-1].MIN_CCYC);
        if ((TDCPRDP.OTH_PRM.CCY_TYP == 'L' 
            || TDCPRDP.OTH_PRM.CCY_TYP == 'F') 
            && !TDCACCRU.CCY.equalsIgnoreCase(TDCPRDP.OTH_PRM.CCY_INF[1-1].MIN_CCYC)) {
            CEP.TRC(SCCGWA, "TEST2");
            WS_MSGID = TDCMSG_ERROR_MSG.TD_CCY_NOT_MATCH;
            S000_ERR_MSG_PROC();
        }
        if (WS_CCY_FOUND == 'N') {
            CEP.TRC(SCCGWA, TDCPRDP.OTH_PRM.CCY_TYP);
            CEP.TRC(SCCGWA, "TEST12");
            WS_MSGID = TDCMSG_ERROR_MSG.TD_CCY_NOT_MATCH;
            S000_ERR_MSG_PROC();
        }
        if (TDCACCRU.CT_FLG == '0' 
            && TDCPRDP.TXN_PRM.CASH_FLG != 'Y') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_CASH_NOT_ALLOWED;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, TDCIRULP);
        CEP.TRC(SCCGWA, TDCACCRU.SPRD_PCT);
        CEP.TRC(SCCGWA, TDCACCRU.SPRD_PNT);
        CEP.TRC(SCCGWA, TDCACCRU.INT_RAT);
        CEP.TRC(SCCGWA, TDCACCRU.INT_RUL_CD);
        CEP.TRC(SCCGWA, WS_RUL_CDC);
        if ((TDCACCRU.INT_RUL_CD.equalsIgnoreCase("ZZYH") 
            || WS_RUL_CDC.equalsIgnoreCase("ZZYH")) 
            && (!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
            TDCACCRU.INT_RUL_CD = " ";
            WS_RUL_CDC = " ";
        }
        if (TDCACCRU.SPRD_PCT == 0 
            && TDCACCRU.SPRD_PNT == 0 
            && TDCACCRU.INT_RAT == 0 
            && (TDCACCRU.INT_RUL_CD.trim().length() > 0 
            || WS_RUL_CDC.trim().length() > 0)) {
            IBS.init(SCCGWA, BPCPRMM);
            BPCPRMM.FUNC = '3';
            BPRPRMT.KEY.TYP = K_IRUL_TYP;
            if (TDCACCRU.INT_RUL_CD.trim().length() > 0) {
                BPRPRMT.KEY.CD = TDCACCRU.INT_RUL_CD;
            } else {
                BPRPRMT.KEY.CD = WS_RUL_CDC;
            }
            CEP.TRC(SCCGWA, BPRPRMT.KEY.CD);
            CEP.TRC(SCCGWA, WS_I);
            BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPCPRMM.DAT_PTR = BPRPRMT;
            S000_CALL_BPZPRMM();
            CEP.TRC(SCCGWA, BPCPRMM.EFF_DT);
            if (TDCPRDP.INT_PRM.RAT_INX == '0') {
                if (TDCACCRU.VAL_DT >= BPCPRMM.EFF_DT 
                    && TDCACCRU.VAL_DT < BPCPRMM.EXP_DT) {
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
                    IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCIRULP);
                }
            } else if (TDCPRDP.INT_PRM.RAT_INX == '1') {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCIRULP);
            } else {
            }
            CEP.TRC(SCCGWA, TDCIRULP);
            if (TDCIRULP.SPRD_OPT != 'A') {
                WS_MATCH = 'N';
                for (WS_I = 1; WS_I <= 12; WS_I += 1) {
                    if (TDCIRULP.CCY.CCYS[WS_I-1].SPT_CCY.equalsIgnoreCase(TDCACCRU.CCY)) {
                        WS_I = 0;
                        WS_MATCH = 'Y';
                    }
                }
                if (WS_MATCH != 'Y') {
                    WS_MSGID = TDCMSG_ERROR_MSG.TD_CCY_NOT_MATCH;
                    S000_ERR_MSG_PROC();
                }
            }
        }
    }
    public void B110_GET_PARMDAT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCQPMP);
        TDCQPMP.FUNC = 'I';
        TDCQPMP.PROD_CD = TDCPIOD.PROD_CD;
        TDCQPMP.DAT_PTR = TDCPROD;
        S000_CALL_TDZQPMP();
        TDCPRDP.PRDMO_CD = TDCPROD.PRDO_CDM;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.TXN_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.TXN_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.INT_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.INT_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.EXP_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.EXP_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.OTH_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.OTH_PRM);
    }
    public void B120_CHK_AMT_AUTH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFAMTA);
        BPCFAMTA.FUNC = ' ';
        BPCFAMTA.AP_MMO = K_AP_MMO;
        if (TDCACCRU.CT_FLG == '0') {
            BPCFAMTA.TBL_NO = K_NORM_CR_AMT_TBL;
        } else {
            BPCFAMTA.TBL_NO = K_NORM_TR_AMT_TBL;
        }
        BPCFAMTA.CCY = TDCACCRU.CCY;
        BPCFAMTA.AMT = TDCACCRU.TXN_AMT;
        S000_CALL_BPZFAMTA();
    }
    public void B130_CHK_STS_TBL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFCSTS);
        BPCFCSTS.STATUS_WORD = "000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        BPCFCSTS.AP_MMO = K_AP_MMO;
        if (WS_EC == ' ') {
            CEP.TRC(SCCGWA, WS_CI_TYP);
            if (WS_CI_TYP == '1') {
                BPCFCSTS.TBL_NO = K_NORM_CR_STS_TBL_P;
            } else {
                BPCFCSTS.TBL_NO = K_NORM_CR_STS_TBL;
            }
        } else {
            if (WS_CI_TYP == '1') {
                BPCFCSTS.TBL_NO = K_NORM_CR_STS_TBL_C_P;
                CEP.TRC(SCCGWA, TDCPRDP.TXN_PRM.OTH_FLG);
                if (TDCPRDP.TXN_PRM.OTH_FLG.equalsIgnoreCase("07")) {
                    BPCFCSTS.TBL_NO = K_CTGM_CR_STS_TBL_C_P;
                }
            } else {
                BPCFCSTS.TBL_NO = K_NORM_CR_STS_TBL_C;
            }
        }
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 201 - 1) + TDRSMST.STSW + BPCFCSTS.STATUS_WORD.substring(201 + 32 - 1);
        if (TDRSMST.BV_TYP != '4' 
            && !DPCPARMP.AC_TYPE.equalsIgnoreCase("033")) {
            if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
            JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
            for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
            if (TDRBVT.STSW == null) TDRBVT.STSW = "";
            JIBS_tmp_int = TDRBVT.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
            BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 151 - 1) + TDRBVT.STSW + BPCFCSTS.STATUS_WORD.substring(151 + 32 - 1);
        }
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_STSW);
        if (CICCUST.O_DATA.O_STSW == null) CICCUST.O_DATA.O_STSW = "";
        JIBS_tmp_int = CICCUST.O_DATA.O_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICCUST.O_DATA.O_STSW += " ";
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 01 - 1) + CICCUST.O_DATA.O_STSW.substring(0, 80) + BPCFCSTS.STATUS_WORD.substring(01 + 80 - 1);
        if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
            if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
            JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
            for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
            BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 24 - 1) + "0" + BPCFCSTS.STATUS_WORD.substring(24 + 1 - 1);
        }
        if (TDRSMST.BV_TYP == '7' 
            || TDRSMST.BV_TYP == '8' 
            || TDRSMST.BV_TYP == '4' 
            || TDRSMST.BV_TYP == '2') {
            if (TDRSMST.BV_TYP != '4') {
                if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
                JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
                for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
                if (TDRBVT.STSW == null) TDRBVT.STSW = "";
                JIBS_tmp_int = TDRBVT.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
                BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 151 - 1) + TDRBVT.STSW + BPCFCSTS.STATUS_WORD.substring(151 + 32 - 1);
            }
        }
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD);
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD.substring(151 - 1, 151 + 32 - 1));
        S000_CALL_BPZFCSTS();
    }
    public void B200_BV_USE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCACCRU.BV_NO);
        IBS.init(SCCGWA, BPCUBUSE);
        BPCUBUSE.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUBUSE.TYPE = '0';
        BPCUBUSE.BV_CODE = TDCACCRU.BV_CD;
        BPCUBUSE.BEG_NO = TDCACCRU.BV_NO;
        BPCUBUSE.END_NO = TDCACCRU.BV_NO;
        BPCUBUSE.NUM = 1;
        CEP.TRC(SCCGWA, BPCUBUSE.TLR);
        CEP.TRC(SCCGWA, BPCUBUSE.BV_CODE);
        CEP.TRC(SCCGWA, BPCUBUSE.BEG_NO);
        CEP.TRC(SCCGWA, BPCUBUSE.END_NO);
        CEP.TRC(SCCGWA, BPCUBUSE.NUM);
        S000_CALL_BPZUBUSE();
    }
    public void B210_CAL_EXP_DT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCACCRU.EXP_DT);
        if (!IBS.isNumeric(REDEFINES56.WS_TERM_MTHS+"")) {
            REDEFINES56.WS_TERM_MTHS = 0;
            WS_TERM = IBS.CLS2CPY(SCCGWA, REDEFINES56);
        }
        CEP.TRC(SCCGWA, REDEFINES56.WS_TERM_TYP);
        if ((!DPCPARMP.AC_TYPE.equalsIgnoreCase("029") 
            && !DPCPARMP.AC_TYPE.equalsIgnoreCase("030")) 
            && (!DPCPARMP.AC_TYPE.equalsIgnoreCase("022") 
            && !DPCPARMP.AC_TYPE.equalsIgnoreCase("023") 
            && !DPCPARMP.AC_TYPE.equalsIgnoreCase("036")) 
            && REDEFINES56.WS_TERM_MTHS != 0) {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = TDCACCRU.VAL_DT;
            if (REDEFINES56.WS_TERM_TYP == 'Y' 
                || REDEFINES56.WS_TERM_TYP == 'M') {
                if (REDEFINES56.WS_TERM_TYP == 'Y') {
                    SCCCLDT.MTHS = (short) (REDEFINES56.WS_TERM_MTHS * 12);
                } else {
                    SCCCLDT.MTHS = REDEFINES56.WS_TERM_MTHS;
                }
            } else {
                SCCCLDT.DAYS = REDEFINES56.WS_TERM_MTHS;
            }
            S000_CALL_SCSSCLDT();
            CEP.TRC(SCCGWA, SCCCLDT.DATE2);
            CEP.TRC(SCCGWA, TDCACCRU.EXP_DT);
            if (TDCACCRU.EXP_DT > 0 
                && SCCCLDT.DATE2 != TDCACCRU.EXP_DT) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_EXP_DT);
            }
            TDCACCRU.EXP_DT = SCCCLDT.DATE2;
        }
    }
    public void B210_CHK_EXP_DT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_TERM);
        if (!IBS.isNumeric(REDEFINES56.WS_TERM_MTHS+"")) {
            REDEFINES56.WS_TERM_MTHS = 0;
            WS_TERM = IBS.CLS2CPY(SCCGWA, REDEFINES56);
        }
        CEP.TRC(SCCGWA, REDEFINES56.WS_TERM_TYP);
        if ((!DPCPARMP.AC_TYPE.equalsIgnoreCase("029") 
            && !DPCPARMP.AC_TYPE.equalsIgnoreCase("030")) 
            && (!DPCPARMP.AC_TYPE.equalsIgnoreCase("022") 
            && !DPCPARMP.AC_TYPE.equalsIgnoreCase("023") 
            && !DPCPARMP.AC_TYPE.equalsIgnoreCase("036")) 
            && REDEFINES56.WS_TERM_MTHS != 0) {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = TDCACCRU.VAL_DT;
            if (REDEFINES56.WS_TERM_TYP == 'Y' 
                || REDEFINES56.WS_TERM_TYP == 'M') {
                if (REDEFINES56.WS_TERM_TYP == 'Y') {
                    SCCCLDT.MTHS = (short) (REDEFINES56.WS_TERM_MTHS * 12);
                } else {
                    SCCCLDT.MTHS = REDEFINES56.WS_TERM_MTHS;
                }
            } else {
                SCCCLDT.DAYS = REDEFINES56.WS_TERM_MTHS;
            }
            S000_CALL_SCSSCLDT();
            CEP.TRC(SCCGWA, SCCCLDT.DATE2);
            CEP.TRC(SCCGWA, TDCACCRU.EXP_DT);
            if (SCCCLDT.DATE2 != TDCACCRU.EXP_DT) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_EXP_DT);
            }
        }
    }
    public void B220_CAL_EXP_INT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCCEINT);
        TDCCEINT.IRUL_PTR = TDCPIOD;
        TDCCEINT.OPT = '0';
        TDCCEINT.TXN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDCCEINT.OPEN_BK = SCCGWA.COMM_AREA.TR_BANK;
        TDCCEINT.PRDAC_CD = BPCPQPRD.AC_TYPE;
        CEP.TRC(SCCGWA, TDCACCRU.PROD_CD);
        TDCCEINT.PROD_CD = TDCACCRU.PROD_CD;
        CEP.TRC(SCCGWA, TDCCEINT.PROD_CD);
        TDCCEINT.IRAT_CD = WS_IRAT_CD;
        CEP.TRC(SCCGWA, "B220-");
        CEP.TRC(SCCGWA, WS_IRAT_CD);
        if (DPCPARMP.AC_TYPE.equalsIgnoreCase("026") 
            && WS_PR_IRAT_CD.trim().length() > 0) {
            TDCCEINT.IRAT_CD = WS_PR_IRAT_CD;
        }
        TDCCEINT.CI_TYP = CICCUST.O_DATA.O_CI_TYP;
        TDCCEINT.SRV_LVL = CICCUST.O_DATA.O_SVR_LVL;
        TDCCEINT.CCY = TDCACCRU.CCY;
        if ((DPCPARMP.AC_TYPE.equalsIgnoreCase("025") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("026")) 
            && TDCACCRU.TERM.equalsIgnoreCase("Y006")) {
            TDCCEINT.TERM = "Y005";
        } else {
            TDCCEINT.TERM = TDCACCRU.TERM;
        }
        if (TDCACCRU.TENOR.trim().length() > 0) {
            TDCCEINT.TERM = TDCACCRU.TENOR;
        }
        TDCCEINT.TXN_AMT = TDCACCRU.TXN_AMT;
        TDCCEINT.VAL_DATE = TDCACCRU.VAL_DT;
        TDCCEINT.EXP_DATE = TDCACCRU.EXP_DT;
        TDCCEINT.CD_PERD = TDCACCRU.CD_PERD;
        TDCCEINT.CD_AMT = TDCACCRU.CD_AMT;
        TDCCEINT.SPRD_PNT = TDCACCRU.SPRD_PNT;
        TDCCEINT.SPRD_PCT = TDCACCRU.SPRD_PCT;
        TDCCEINT.AC_RUL_CD = TDCACCRU.INT_RUL_CD;
        CEP.TRC(SCCGWA, "JFAAA");
        CEP.TRC(SCCGWA, TDCPIOD.ACTI.HY_RAT);
        CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.CPRA_TYP);
        if (TDCPIOD.OTH_PRM.CPRA_TYP == '4') {
            TDCACCRU.INT_RAT = TDCPIOD.ACTI.HY_RAT;
        }
        CEP.TRC(SCCGWA, TDCACCRU.INT_RAT);
        if (TDCACCRU.INT_SEL == '4') {
            TDCCEINT.RAT = TDCACCRU.INT_RAT;
            TDCCEINT.RAT_1 = TDCACCRU.INT_RAT;
        }
        TDCCEINT.CALR_STD = TDCACCRU.CALR_STD;
        TDCCEINT.CUS_ISTRY = CICACCU.DATA.INDUS1;
        TDCCEINT.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        TDCCEINT.RGN_NO = CICMACR.DATA.BASE_RGN;
        TDCCEINT.CI_NO = TDCACCRU.CI_NO;
        TDCCEINT.CALD_FLG = WS_CALD_FLG;
        CEP.TRC(SCCGWA, TDCACCRU.RULE);
        CEP.TRC(SCCGWA, TDCCEINT.CALD_FLG);
        if (TDCACCRU.RULE != ' ') {
            TDCCEINT.CALD_FLG = TDCACCRU.RULE;
        }
        TDCCEINT.AC_BR = WS_AC_BR;
        CEP.TRC(SCCGWA, TDCCEINT.IRAT_CD);
        CEP.TRC(SCCGWA, "B220-CAL-EXP-INT");
        S000_CALL_TDZCEINT();
        CEP.TRC(SCCGWA, TDCCEINT.RAT);
        TDCACCRU.INT_RAT = TDCCEINT.RAT;
        CEP.TRC(SCCGWA, TDCACCRU.INT_RAT);
        TDCACCRU.SPRD_PNT = TDCCEINT.SPRD_PNT;
        TDCACCRU.SPRD_PCT = TDCCEINT.SPRD_PCT;
        if ((DPCPARMP.AC_TYPE.equalsIgnoreCase("022") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("023") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("036"))) {
            TDCCEINT.INT = 0;
        }
    }
    public void B220_CAL_ACCU() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCCEINT);
        TDCCEINT.IRUL_PTR = TDCIRULP;
        TDCCEINT.OPT = '1';
        TDCCEINT.TXN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDCCEINT.OPEN_BK = SCCGWA.COMM_AREA.TR_BANK;
        TDCCEINT.PRDAC_CD = TDRSMST.PRDAC_CD;
        TDCCEINT.PROD_CD = TDCACCRU.PROD_CD;
        TDCCEINT.IRAT_CD = WS_IRAT_CD;
        TDCCEINT.SRV_LVL = CICCUST.O_DATA.O_SVR_LVL;
        TDCCEINT.CCY = TDRSMST.CCY;
        TDCCEINT.TERM = TDRSMST.TERM;
        TDCCEINT.TXN_AMT = TDRSMST.BAL;
        TDCCEINT.VAL_DATE = TDRCDI.LAST_DEAL_DATE;
        TDCCEINT.EXP_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDCCEINT.CD_PERD = WS_CD_PERD;
        TDCCEINT.CD_AMT = TDRCDI.CD_AMT;
        TDCCEINT.LOS_DNUM = TDRCDI.LOS_DNUM;
        TDCCEINT.SPRD_PNT = TDRIREV.SPRD_PNT;
        TDCCEINT.SPRD_PCT = TDRIREV.SPRD_PCT;
        TDCCEINT.AC_RUL_CD = TDRIREV.INT_RUL_CD;
        TDCCEINT.RAT = TDRIREV.CON_RATE;
        TDCCEINT.CALR_STD = TDCACCRU.CALR_STD;
        TDCCEINT.CUS_ISTRY = CICACCU.DATA.INDUS1;
        TDCCEINT.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        TDCCEINT.RGN_NO = CICMACR.DATA.BASE_RGN;
        TDCCEINT.CALD_FLG = WS_CALD_FLG;
        CEP.TRC(SCCGWA, TDCACCRU.RULE);
        CEP.TRC(SCCGWA, TDCCEINT.CALD_FLG);
        if (TDCACCRU.RULE != ' ') {
            TDCCEINT.CALD_FLG = TDCACCRU.RULE;
        }
        CEP.TRC(SCCGWA, "B220-CAL-ACCU");
        S000_CALL_TDZCEINT();
        IBS.init(SCCGWA, TDRINTC);
        TDRINTC.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
        TDRINTC.KEY.CLS = '4';
        TDRINTC.KEY.BDT = TDRCDI.LAST_DEAL_DATE;
        TDRINTC.EDT = SCCGWA.COMM_AREA.AC_DATE;
        TDRINTC.AMT = TDRSMST.BAL - TDRCDI.CD_AMT * TDRCDI.LOS_DNUM;
        TDRINTC.INT_STS = '3';
        TDRINTC.INT_RAT = TDRIREV.CON_RATE;
        TDRINTC.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRINTC.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        TDRINTC.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRINTC.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRINTC.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        R000_GET_TAX_INF();
        if (BPCITAXG.RETURN_INFO == 'N' 
            || BPCITAXG.OUTPUT.TAXR_GROUP[1-1].EFF_DT == 0) {
            TDRINTC.TAX_RAT = 0;
        } else {
            TDRINTC.TAX_RAT = BPCITAXG.OUTPUT.TAXR_GROUP[1-1].TAX_VAL;
        }
        T000_WRITE_INTC();
        if (TDCCEINT.LACCU != 0) {
            TDRINTC.KEY.CLS = '5';
            TDRINTC.AMT = TDRCDI.CD_AMT * TDRCDI.LOS_DNUM;
            TDRINTC.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            TDRINTC.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            TDRINTC.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
            TDRINTC.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            T000_WRITE_INTC();
        }
    }
    public void B015_CHECK_CI_LIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCKLS);
        if (TDCACCRU.AC_NO.trim().length() == 0) {
            CICCKLS.DATA.AP_TYPE = "001";
            CICCKLS.DATA.CI_NO = TDCACCRU.CI_NO;
        } else {
            CICCKLS.DATA.AP_TYPE = "002";
            CICCKLS.DATA.AGR_NO = TDCACCRU.AC_NO;
        }
        CEP.TRC(SCCGWA, CICCKLS.DATA.AGR_NO);
        CEP.TRC(SCCGWA, CICCKLS.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICCKLS.DATA.AP_TYPE);
        CEP.TRC(SCCGWA, TDCACCRU.ACO_AC);
        CICCKLS.DATA.EXP_MMO = WS_MMO;
        CICCKLS.DATA.ACAC_NO = TDCACCRU.ACO_AC;
        S000_CALL_CIZCKLS();
    }
    public void B230_GEN_TD_AC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CI_TYP);
        B015_CHECK_CI_LIST();
        IBS.init(SCCGWA, BPCCGAC);
        BPCCGAC.DATA.PRD_CODE = TDCACCRU.PROD_CD;
        if (WS_CI_TYP == '1') {
            BPCCGAC.DATA.CI_AC_FLG = '6';
        } else {
            BPCCGAC.DATA.CI_AC_FLG = '5';
        }
        BPCCGAC.DATA.CI_AC_TYPE = '2';
        BPCCGAC.DATA.AC_KIND = '1';
        CEP.TRC(SCCGWA, TDCACCRU.PROD_CD);
        CEP.TRC(SCCGWA, BPCCGAC.DATA.AC_KIND);
        S000_CALL_BPZGACNO();
        CICCUST.FUNC = 'C';
        CICCUST.DATA.CI_NO = TDCACCRU.CI_NO;
        S000_CALL_CIZCUST();
        CEP.TRC(SCCGWA, BPCPQORG.TRA_TYP);
        CEP.TRC(SCCGWA, WS_CI_TYP);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_SID_FLG);
        if (!WS_PQORG_TRA_TYP.equalsIgnoreCase("00")) {
            if (WS_CI_TYP == '1') {
                if (CICCUST.O_DATA.O_SID_FLG == '1') {
                    WS_FRG_IND = "FTI";
                }
                if (CICCUST.O_DATA.O_SID_FLG == '2') {
                    WS_FRG_IND = "FTF";
                }
            }
            if (WS_CI_TYP == '2') {
                if (CICCUST.O_DATA.O_SID_FLG == '1') {
                    WS_FRG_IND = "FTE";
                }
                if (CICCUST.O_DATA.O_SID_FLG == '2') {
                    WS_FRG_IND = "FTN";
                }
            }
            if (WS_CI_TYP == '3') {
                WS_FRG_IND = "FTU";
            }
        }
        TDCACCRU.AC_NO = BPCCGAC.DATA.CI_AC;
        CEP.TRC(SCCGWA, TDCACCRU.AC_NO);
        B230_GEN_TD_CI();
    }
    public void B230_GEN_TD_CI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSACR);
        CICSACR.DATA.STSW = "ZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROS";
        CICSACR.FUNC = 'A';
        CICSACR.DATA.AGR_NO = TDCACCRU.AC_NO;
        CICSACR.DATA.ENTY_TYP = '1';
        CICSACR.DATA.CI_NO = TDCACCRU.CI_NO;
        if (CICSACR.DATA.STSW == null) CICSACR.DATA.STSW = "";
        JIBS_tmp_int = CICSACR.DATA.STSW.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) CICSACR.DATA.STSW += " ";
        CICSACR.DATA.STSW = "1" + CICSACR.DATA.STSW.substring(1);
        CICSACR.DATA.PROD_CD = TDCACCRU.PROD_CD;
        CICSACR.DATA.CNTRCT_TYP = WS_AC_TYPE;
        CICSACR.DATA.FRM_APP = "TD";
        CICSACR.DATA.CCY = TDCACCRU.CCY;
        CICSACR.DATA.SHOW_FLG = 'Y';
        CICSACR.DATA.AC_CNM = TDCACCRU.AC_NAME;
        CICSACR.DATA.OPN_BR = WS_CHE_BR;
        CICSACR.DATA.OPEN_DT = SCCGWA.COMM_AREA.AC_DATE;
        CICSACR.DATA.OWNER_BK = WS_OWNER_BK;
        if (TDCACCRU.ANY_FLG == 'S') {
            CICSACR.CTL_FLG.MUL_FLG = '1';
            CICSACR.CTL_STSW = IBS.CLS2CPY(SCCGWA, CICSACR.CTL_FLG);
        }
        if (TDCACCRU.ANY_FLG == 'E') {
            CICSACR.CTL_FLG.MUL_FLG = '2';
            CICSACR.CTL_STSW = IBS.CLS2CPY(SCCGWA, CICSACR.CTL_FLG);
        }
        S000_CALL_CIZSACR();
        CEP.TRC(SCCGWA, TDCACCRU.OPP_AC_CNO);
        if ((WS_AC_TYPE.equalsIgnoreCase("021") 
            && WS_CI_TYP == '1') 
            || WS_AC_TYPE.equalsIgnoreCase("035") 
            || WS_AC_TYPE.equalsIgnoreCase("036") 
            || WS_AC_TYPE.equalsIgnoreCase("037")) {
            CEP.TRC(SCCGWA, "LUO");
            CEP.TRC(SCCGWA, WS_AC_TYPE);
            CEP.TRC(SCCGWA, WS_CI_TYP);
            IBS.init(SCCGWA, CICSACRL);
            CICSACRL.FUNC = 'A';
            CICSACRL.DATA.AC_NO = TDCACCRU.AC_NO;
            CICSACRL.DATA.REL_AC_NO = TDCACCRU.OPP_AC_CNO;
            CEP.TRC(SCCGWA, TDCACCRU.CD_AC);
            if (TDCACCRU.CD_AC.trim().length() > 0) {
                CICSACRL.DATA.REL_AC_NO = TDCACCRU.CD_AC;
            }
            if (WS_AC_TYPE.equalsIgnoreCase("021") 
                && WS_CI_TYP == '1') {
                CICSACRL.DATA.AC_REL = "07";
                CICSACRL.DATA.REL_AC_NO = TDCACCRU.INT_AC;
            }
            if (WS_AC_TYPE.equalsIgnoreCase("035")) {
                CICSACRL.DATA.AC_REL = "05";
            }
            if (WS_AC_TYPE.equalsIgnoreCase("036")) {
                CICSACRL.DATA.AC_REL = "06";
            }
            if (WS_AC_TYPE.equalsIgnoreCase("037") 
                && WS_CI_TYP == '1') {
                CICSACRL.DATA.AC_REL = "13";
                CICSACRL.DATA.DEFAULT = '1';
            }
            S000_CALL_CIZSACRL();
        }
    }
    public void B240_GEN_CI_INFO() throws IOException,SQLException,Exception {
    }
    public void B240_GEN_PSW() throws IOException,SQLException,Exception {
    }
    public void B250_GEN_CI_AC_INF() throws IOException,SQLException,Exception {
    }
    public void B260_WRT_VCH_EVENT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPEBAS);
        S000_CALL_BPZPEBAS();
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = BPCPQPRD.PRDT_MODEL;
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(51 - 1, 51 + 1 - 1).equalsIgnoreCase("1") 
            && (TDRSMST.PRDAC_CD.equalsIgnoreCase("033") 
            || TDRSMST.PRDAC_CD.equalsIgnoreCase("020"))) {
            BPCPOEWA.DATA.CNTR_TYPE = "CAAC";
        }
        if (TDCACCRU.OPT == '0' 
            || TDCACCRU.OPT == '2' 
            || TDCACCRU.OPT == '3' 
            || TDCACCRU.OPT == '4') {
            BPCPOEWA.DATA.PROD_CODE = TDCACCRU.PROD_CD;
        } else {
            BPCPOEWA.DATA.PROD_CODE = TDRSMST.PROD_CD;
        }
        BPCPOEWA.DATA.EVENT_CODE = "CR";
        BPCPOEWA.DATA.DESC = TDCACCRU.REMARK;
        if (TDCPRDP.TXN_PRM.OTH_FLG.equalsIgnoreCase("03")) {
            BPCPOEWA.DATA.BR_OLD = WS_POST_BR;
        } else {
            if (TDCACCRU.OPT == '1') {
                BPCPOEWA.DATA.BR_OLD = TDRSMST.OWNER_BR;
            } else {
                BPCPOEWA.DATA.BR_OLD = WS_AC_BR;
            }
        }
        CEP.TRC(SCCGWA, TDRSMST.CHE_BR);
        CEP.TRC(SCCGWA, WS_CHE_BR);
        BPCPOEWA.DATA.BR_OLD = WS_CHE_BR;
        BPCPOEWA.DATA.BR_NEW = WS_CHE_BR;
        BPCPOEWA.DATA.PAY_BR = WS_CHE_BR;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = TDCACCRU.CCY;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = TDCACCRU.TXN_AMT;
        BPCPOEWA.DATA.VALUE_DATE = TDRSMST.VAL_DATE;
        CEP.TRC(SCCGWA, "=========");
        CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
        CEP.TRC(SCCGWA, TDCACCRU.ACO_AC);
        BPCPOEWA.DATA.AC_NO = TDRSMST.KEY.ACO_AC;
        if (TDRSMST.KEY.ACO_AC.trim().length() == 0) {
            BPCPOEWA.DATA.AC_NO = TDCACCRU.ACO_AC;
        }
        CEP.TRC(SCCGWA, WS_ACO_XXT);
        CEP.TRC(SCCGWA, WS_GRP_FLG);
        if ((WS_AC_TYPE.equalsIgnoreCase("036") 
            || WS_AC_TYPE.equalsIgnoreCase("035")) 
            && WS_GRP_FLG == 'N') {
            BPCPOEWA.DATA.AC_NO = WS_ACO_XXT;
            if (WS_ACO_XXT.trim().length() == 0) {
                BPCPOEWA.DATA.AC_NO = TDCACCRU.ACO_AC;
            }
        }
        if ((WS_AC_TYPE.equalsIgnoreCase("036") 
            || WS_AC_TYPE.equalsIgnoreCase("035")) 
            && WS_GRP_FLG == 'Y') {
            BPCPOEWA.DATA.AC_NO = TDCACCRU.ACO_AC;
        }
        BPCPOEWA.DATA.CI_NO = TDCACCRU.CI_NO;
        BPCPOEWA.DATA.AC_NO_REL = TDCACCRU.OPP_AC_CNO;
        BPCPOEWA.DATA.THEIR_AC = TDCACCRU.OPP_AC_CNO;
        BPCPOEWA.DATA.PAY_MAN = TDCACCRU.OPP_NAME;
        BPCPOEWA.DATA.PORT = TDCACCRU.BV_CD;
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(51 - 1, 51 + 1 - 1).equalsIgnoreCase("1") 
            && (TDRSMST.PRDAC_CD.equalsIgnoreCase("033") 
            || TDRSMST.PRDAC_CD.equalsIgnoreCase("020"))) {
            BPCPOEWA.DATA.AMT_INFO[1-1].AMT = 0;
        }
        if (BPCPOEWA.DATA.AMT_INFO[1-1].AMT == 0 
            && BPCPOEWA.DATA.AMT_INFO[2-1].AMT == 0 
            && BPCPOEWA.DATA.AMT_INFO[3-1].AMT == 0 
            && BPCPOEWA.DATA.AMT_INFO[4-1].AMT == 0 
            && BPCPOEWA.DATA.AMT_INFO[5-1].AMT == 0 
            && BPCPOEWA.DATA.AMT_INFO[6-1].AMT == 0 
            && BPCPOEWA.DATA.AMT_INFO[7-1].AMT == 0) {
        } else {
            S000_CALL_BPZPOEWA();
        }
    }
    public void B260_WRT_VCH_DAIFU() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPEBAS);
        S000_CALL_BPZPEBAS();
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = BPCPQPRD.PRDT_MODEL;
        BPCPOEWA.DATA.PROD_CODE = TDRSMST.PROD_CD;
        BPCPOEWA.DATA.EVENT_CODE = "REVSALCR";
        BPCPOEWA.DATA.DESC = TDCACCRU.REMARK;
        BPCPOEWA.DATA.BR_OLD = TDRSMST.OWNER_BR;
        BPCPOEWA.DATA.BR_NEW = TDRSMST.OWNER_BR;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = TDCACCRU.CCY;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = TDRSMST.BAL;
        if (WS_SPE_FLG == 'D') {
            BPCPOEWA.DATA.AMT_INFO[4-1].AMT = TDRSMST.BUD_INT;
        }
        BPCPOEWA.DATA.VALUE_DATE = TDCACCRU.VAL_DT;
        BPCPOEWA.DATA.AC_NO = TDCACCRU.AC_NO;
        BPCPOEWA.DATA.CI_NO = TDCACCRU.CI_NO;
        BPCPOEWA.DATA.AC_NO_REL = TDCACCRU.OPP_AC_CNO;
        BPCPOEWA.DATA.PORT = TDCACCRU.BV_CD;
        S000_CALL_BPZPOEWA();
    }
    public void B200_17_WRI_NFIN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.AC = TDCACCRU.AC_NO;
        BPCPNHIS.INFO.AC_SEQ = CICSACAC.DATA.AGR_SEQ;
        BPCPNHIS.INFO.TX_RMK = "FOR HLD DDAC";
        BPCPNHIS.INFO.FMT_ID = "TDZACCRU";
        BPCPNHIS.INFO.CI_NO = TDCACCRU.CI_NO;
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        if (WS_CI_TYP == '1') {
            BPCPNHIS.INFO.TX_CD = "0125108";
        } else {
            BPCPNHIS.INFO.TX_CD = "0125103";
        }
        BPCPNHIS.INFO.TX_TYP_CD = WS_MMO;
        BPCPNHIS.INFO.FMT_ID_LEN = 3579;
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.NEW_DAT_PT = TDCACCRU;
        S000_CALL_BPZPNHIS();
    }
    public void B270_WRT_FHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPFHIS);
        BPCPFHIS.DATA.TX_DRCR_FLG = 'C';
        BPCPFHIS.DATA.PRINT_IND = 'Y';
        BPCPFHIS.DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPFHIS.DATA.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        BPCPFHIS.DATA.TX_REV_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.CI_NO = TDCACCRU.CI_NO;
        if (TDCACCRU.CT_FLG == '0') {
            BPCPFHIS.DATA.TX_TYPE = 'C';
        } else {
            BPCPFHIS.DATA.TX_TYPE = 'T';
        }
        CEP.TRC(SCCGWA, TDCACCRU.ACO_AC);
        CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
        CEP.TRC(SCCGWA, WS_ACO_AC_1);
        WS_ACO_SEQ = TDRSMST.KEY.ACO_AC;
        BPCPFHIS.DATA.AC = TDCACCRU.AC_NO;
        BPCPFHIS.DATA.ACO_AC = TDCACCRU.ACO_AC;
        if ((WS_AC_TYPE.equalsIgnoreCase("035") 
            || WS_AC_TYPE.equalsIgnoreCase("036"))) {
            if (TDCACCRU.FST_FLG == '1' 
                && WS_SEQ2 > 1) {
                BPCPFHIS.DATA.ACO_AC = WS_ACO_AC_1;
                if (WS_AC_TYPE.equalsIgnoreCase("035")) {
                    BPCPFHIS.DATA.REMARK = "000002";
                }
            } else {
                IBS.init(SCCGWA, TDRSMST);
                TDRSMST.AC_NO = TDCACCRU.AC_NO;
                T000_READ_TDTSMST_MR();
                BPCPFHIS.DATA.ACO_AC = TDRSMST.KEY.ACO_AC;
                if (WS_AC_TYPE.equalsIgnoreCase("035")) {
                    IBS.init(SCCGWA, CICQACAC);
                    CICQACAC.FUNC = 'A';
                    CICQACAC.DATA.ACAC_NO = WS_ACO_SEQ;
                    S000_CALL_CIZQACAC();
                    JIBS_tmp_str[0] = "" + CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
                    JIBS_tmp_int = JIBS_tmp_str[0].length();
                    for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                    BPCPFHIS.DATA.REMARK = JIBS_tmp_str[0].substring(3 - 1, 3 + 6 - 1);
                }
            }
        }
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.ACO_AC);
        if (TDCACCRU.BV_TYP == '4') {
            BPCPFHIS.DATA.TX_TOOL = TDCACCRU.AC_NO;
        }
        BPCPFHIS.DATA.OTH_AC = TDCACCRU.OPP_AC_CNO;
        if (TDCACCRU.OPP_BV_TYP == '1') {
            BPCPFHIS.DATA.OTH_TX_TOOL = TDCACCRU.OPP_AC_CNO;
            BPCPFHIS.DATA.RLT_TX_TOOL = TDCACCRU.OPP_AC_CNO;
        } else {
            BPCPFHIS.DATA.RLT_AC = TDCACCRU.OPP_AC_CNO;
        }
        BPCPFHIS.DATA.BV_NO = TDCACCRU.BV_NO;
        BPCPFHIS.DATA.BV_CODE = TDCACCRU.BV_CD;
        if (TDCACCRU.BV_CD.equalsIgnoreCase("TDBV4")) {
            BPCPFHIS.DATA.BV_CODE = " ";
        }
        BPCPFHIS.DATA.TX_DRCR_FLG = 'C';
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_DRCR_FLG);
        CEP.TRC(SCCGWA, TDCACCRU.CCY_TYP);
        BPCPFHIS.DATA.TX_VAL_DT = TDCACCRU.VAL_DT;
        BPCPFHIS.DATA.TX_CCY = TDCACCRU.CCY;
        BPCPFHIS.DATA.TX_CCY_TYP = TDCACCRU.CCY_TYP;
        CEP.TRC(SCCGWA, "PHIS");
        CEP.TRC(SCCGWA, TDCACCRU.TXN_AMT);
        CEP.TRC(SCCGWA, TDCACCRU.BAL);
        CEP.TRC(SCCGWA, TDCACCRU.BUSI_CTLW);
        BPCPFHIS.DATA.TX_AMT = TDCACCRU.TXN_AMT;
        BPCPFHIS.DATA.TX_MMO = WS_MMO;
        if (TDCACCRU.OPT == '0' 
            || TDCACCRU.OPT == '2' 
            || TDCACCRU.OPT == '3' 
            || TDCACCRU.OPT == '4') {
            BPCPFHIS.DATA.PROD_CD = TDCACCRU.PROD_CD;
        } else {
            BPCPFHIS.DATA.PROD_CD = TDRSMST.PROD_CD;
        }
        WS_TDRFHIS.WS_FHIS_POST_CD = TDCACCRU.BV_CD;
        WS_TDRFHIS.WS_FHIS_SVR_CODE = SCCGWA.COMM_AREA.SERV_CODE;
        WS_TDRFHIS.WS_FHIS_TX_TIME = SCCGWA.COMM_AREA.TR_TIME;
        WS_TDRFHIS.WS_FHIS_DEP_TYPE = BPCPQPRD.BUSI_TYPE;
        WS_TDRFHIS.WS_FHIS_AC_NAME = TDCACCRU.NAME;
        WS_TDRFHIS.WS_FHIS_INSTR_MTH = TDCACCRU.INSTR_MTH;
        WS_COUNT1 = 0;
        WS_COUNT1 = 276;
        CEP.TRC(SCCGWA, WS_COUNT1);
        IBS.init(SCCGWA, TDRFHIS);
        TDRFHIS.POST_CD = TDCACCRU.BV_CD;
        TDRFHIS.SVR_CODE = SCCGWA.COMM_AREA.SERV_CODE;
        TDRFHIS.TX_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDRFHIS.DEP_TYPE = BPCPQPRD.BUSI_TYPE;
        TDRFHIS.AC_NAME = TDCACCRU.NAME;
        TDRFHIS.INSTR_MTH = TDCACCRU.INSTR_MTH;
        BPCPFHIS.DATA.FMT_CODE = "TD099";
        CEP.TRC(SCCGWA, TDRFHIS.DATA_FIELD_TEXT);
        if (TDRFHIS.DATA_FIELD_TEXT.trim().length() == 0) {
            BPCPFHIS.DATA.FMT_LEN = WS_COUNT1;
        } else {
            BPCPFHIS.DATA.FMT_LEN = 1276;
        }
        BPCPFHIS.DATA.FMT_DATA = IBS.CLS2CPY(SCCGWA, TDRFHIS);
        if (TDCACCRU.PROD_CD.equalsIgnoreCase("CDP00580")) {
            BPCPFHIS.DATA.PRINT_IND = 'N';
        } else {
            BPCPFHIS.DATA.PRINT_IND = 'Y';
        }
        BPCPFHIS.DATA.DISPLAY_IND = 'Y';
        CEP.TRC(SCCGWA, "WWWWW");
        CEP.TRC(SCCGWA, TDRSMST.STSW);
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(51 - 1, 51 + 1 - 1).equalsIgnoreCase("0")) {
            BPCPFHIS.DATA.DISPLAY_IND = 'Y';
        } else {
            BPCPFHIS.DATA.DISPLAY_IND = 'N';
        }
        BPCPFHIS.DATA.RLT_AC_NAME = TDCACCRU.OPP_NAME;
        if (TDCACCRU.CT_FLG == '1' 
            && TDCACCRU.OPP_AC_CNO.trim().length() > 0) {
            IBS.init(SCCGWA, AICPQMIB);
            AICPQMIB.INPUT_DATA.AC = TDCACCRU.OPP_AC_CNO;
            S000_CALL_AIZPQMIB();
            BPCPFHIS.DATA.RLT_AC_NAME = AICPQMIB.OUTPUT_DATA.CHS_NM;
            BPCPFHIS.DATA.RLT_BANK = "" + AICPQMIB.INPUT_DATA.BR;
            JIBS_tmp_int = BPCPFHIS.DATA.RLT_BANK.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPCPFHIS.DATA.RLT_BANK = "0" + BPCPFHIS.DATA.RLT_BANK;
            BPCPFHIS.DATA.RLT_BK_NM = BPCPQORG.CHN_NM;
            BPCPFHIS.DATA.RLT_CCY = AICPQMIB.INPUT_DATA.CCY;
            CEP.TRC(SCCGWA, AICPQMIB.INPUT_DATA.BR);
            BPCPQORG.BR = AICPQMIB.INPUT_DATA.BR;
            S000_CALL_BPZPQORG();
            CEP.TRC(SCCGWA, BPCPQORG.CHN_NM);
            BPCPFHIS.DATA.RLT_BK_NM = BPCPQORG.CHN_NM;
        }
        if (TDCACCRU.CT_FLG == '2' 
            && TDCACCRU.OPP_AC_CNO.trim().length() > 0) {
            BPCPFHIS.DATA.RLT_AC = TDCACCRU.OPP_AC_CNO;
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = TDCACCRU.OPP_AC_CNO;
            S000_CALL_CIZACCU();
            BPCPFHIS.DATA.RLT_AC_NAME = CICACCU.DATA.AC_CNM;
            if (CICACCU.DATA.AC_CNM.trim().length() == 0) {
                BPCPFHIS.DATA.RLT_AC_NAME = CICACCU.DATA.AC_ENM;
            }
            BPCPFHIS.DATA.RLT_BANK = "" + CICACCU.DATA.OPN_BR;
            JIBS_tmp_int = BPCPFHIS.DATA.RLT_BANK.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPCPFHIS.DATA.RLT_BANK = "0" + BPCPFHIS.DATA.RLT_BANK;
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            S000_CALL_BPZPQORG();
            BPCPFHIS.DATA.RLT_BK_NM = BPCPQORG.CHN_NM;
        }
        if (TDCACCRU.HIS_RMK.equalsIgnoreCase("N")) {
            BPCPFHIS.DATA.DISPLAY_IND = 'N';
        }
        CEP.TRC(SCCGWA, "123123123");
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.DISPLAY_IND);
        if (TDCACCRU.BV_TYP == '4') {
            if (!TDRSMST.PRDAC_CD.equalsIgnoreCase("035") 
                && !TDRSMST.PRDAC_CD.equalsIgnoreCase("036")) {
                BPCPFHIS.DATA.SMS_FLG = 'N';
            }
        }
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.SMS_FLG);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_AMT);
        CEP.TRC(SCCGWA, TDRSMST.BAL);
        BPCPFHIS.DATA.VAL_BAL = TDRSMST.BAL;
        if (TDCACCRU.HIS_RMK.equalsIgnoreCase("N") 
            && TDRSMST.PRDAC_CD.equalsIgnoreCase("036")) {
        } else {
            S000_CALL_BPZPFHIS();
        }
    }
    public void B270_UPDATE_FINANCE_HIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUNARR);
        BPCUNARR.FUNC = 'S';
        BPCUNARR.INP_DATA.AC = TDCACCRU.AC_NO;
        BPCUNARR.INP_DATA.AC_DT = TDCACCRU.CAN_DATE;
        BPCUNARR.INP_DATA.JRNNO = TDCACCRU.CAN_JRNNO;
        BPCUNARR.INP_DATA.TX_STS = 'C';
        S000_CALL_BPZUNARR();
        IBS.init(SCCGWA, BPCUNARR);
        BPCUNARR.FUNC = 'S';
        BPCUNARR.INP_DATA.AC = TDCACCRU.AC_NO;
        BPCUNARR.INP_DATA.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCUNARR.INP_DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCUNARR.INP_DATA.TX_STS = 'R';
        S000_CALL_BPZUNARR();
    }
    public void B300_WRT_TDTDWHH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRDWHH);
        TDRDWHH.KEY.ACO_AC = TDCACCRU.ACO_AC;
        T000_READ_TDRDWHH();
        IBS.init(SCCGWA, TDRDWHH);
        TDRDWHH.KEY.ACO_AC = TDCACCRU.ACO_AC;
        if (TDCACCRU.OPT == '0') {
            TDRDWHH.KEY.PAY_SEQ = TDRCDI.NOR_NUM;
        } else {
            TDRDWHH.KEY.PAY_SEQ = WS_CDI_NUM;
        }
        TDRDWHH.TERM_NO = TDRCDI.PLAN_NUM;
        TDRDWHH.TXND_PROC_TYPE = 'P';
        if (TDCACCRU.OPT == '1') {
            TDRDWHH.NOR_SETT_DATE = WS_NEXT_DEAL_DATE;
        } else {
            TDRDWHH.NOR_SETT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            TDRDWHH.SETT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        TDRDWHH.SETT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRDWHH.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        TDRDWHH.SETT_CCY = TDCACCRU.CCY;
        TDRDWHH.SETT_AMT = TDCACCRU.TXN_AMT;
        TDRDWHH.INOUT = 'I';
        TDRDWHH.PAY_MTH = TDCACCRU.CT_FLG;
        TDRDWHH.SETT_AC = TDCACCRU.OPP_AC_CNO;
        TDRDWHH.STATUS = 'N';
        TDRDWHH.RBAK = " ";
        TDRDWHH.OWNER_BK = BPCPORUP.DATA_INFO.BR;
        TDRDWHH.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        TDRDWHH.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRDWHH.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRDWHH.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_WRITE_TDTDWHH();
    }
    public void B280_REG_OPEN() throws IOException,SQLException,Exception {
    }
    public void B280_REG_OPEN_CANCEL() throws IOException,SQLException,Exception {
    }
    public void B290_REG_LARGE_AMT() throws IOException,SQLException,Exception {
    }
    public void B290_REG_LARGE_AMT_CANCEL() throws IOException,SQLException,Exception {
    }
    public void B320_REG_ETAB() throws IOException,SQLException,Exception {
    }
    public void B320_REG_ETAB_CANCEL() throws IOException,SQLException,Exception {
    }
    public void B300_DRAW_FEE_PROC() throws IOException,SQLException,Exception {
    }
    public void B310_CALL_TLR_AM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLAM);
        BPCFTLAM.OP_CODE = 'A';
        BPCFTLAM.ACCU_TYP = "13";
        BPCFTLAM.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCFTLAM.CCY = TDCACCRU.CCY;
        BPCFTLAM.TX_COUNT = 1;
        BPCFTLAM.AMT = TDCACCRU.TXN_AMT;
        S000_CALL_BPZFTLAM();
    }
    public void B480_GET_PRODINF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCPIOD);
        TDCPIOD.PROD_CD = WS_PROD_CD;
        WS_PROD_CD_PMPD = WS_PROD_CD;
        TDCPIOD.C_PROD_CD = TDCACCRU.PROD_CD;
        TDCPIOD.ACTI_NO = TDCACCRU.ACTI_NO;
        TDCPIOD.PROD_TYPE = WS_AC_TYPE;
        CEP.TRC(SCCGWA, WS_PROD_CD);
        CEP.TRC(SCCGWA, TDCACCRU.ACTI_NO);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, TDCACCRU.TERM);
        if (TDCACCRU.ACTI_NO.trim().length() == 0) {
            TDCPIOD.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            TDCPIOD.INTERM = TDCACCRU.TERM;
            TDCPIOD.CCY = TDCACCRU.CCY;
        }
        B110_GET_PARMDAT();
        if (WS_AC_TYPE.equalsIgnoreCase("029") 
            || WS_AC_TYPE.equalsIgnoreCase("033")) {
            TDCPIOD.GET_FLG = 'N';
            if (TDCACCRU.TERM.trim().length() > 0 
                && (TDCPRDP.EXP_PRM.DOCU_TYP == '0' 
                || TDCPRDP.EXP_PRM.DOCU_TYP == 'Y') 
                && WS_AC_TYPE.equalsIgnoreCase("029")) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_TERM_I_ERR);
            }
        }
        TDCPIOD.BAL_ACTI_FLG = TDCACCRU.BAL_ACTI_FLG;
        TDCPIOD.TLM_FLG = 'O';
        TDCPIOD.BAL = TDCACCRU.TXN_AMT;
        TDCPIOD.ACCRU_FLG = 'Y';
        TDCPIOD.CDS_DT = TDCACCRU.CDS_DT;
        S000_CALL_TDZPROD();
        WS_ONTIM_FLG = TDCPIOD.EXP_PRM.ONTM_FLG;
        WS_LIMIT_FLG = TDCPIOD.EXP_PRM.LMT_FLG;
        CEP.TRC(SCCGWA, TDCPIOD.EXP_PRM.ERLY_TYP);
        WS_ERLY_TYP = TDCPIOD.EXP_PRM.ERLY_TYP;
        CEP.TRC(SCCGWA, TDCPIOD.ACTI_NO);
        CEP.TRC(SCCGWA, "JF0811");
        CEP.TRC(SCCGWA, TDCPIOD.ACTI.ZR_TYP);
        TDCACCRU.ACTI_NO = TDCPIOD.ACTI_NO;
        if (TDCACCRU.CDS_DT != 0 
            && WS_AC_TYPE.equalsIgnoreCase("021") 
            && TDCPIOD.ACTI.ZR_TYP == '2') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CDS_ERR);
        }
    }
    public void B490_CHECK_PROD() throws IOException,SQLException,Exception {
        if (TDCACCRU.INT_SEL == ' ') {
            TDCACCRU.INT_SEL = '0';
        }
        if (TDCACCRU.VAL_DT == TDCACCRU.EXP_DT 
            && TDCACCRU.VAL_DT > 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_EXP_DT);
        }
        if (TDCACCRU.VAL_DT == 0) {
            TDCACCRU.VAL_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        CEP.TRC(SCCGWA, TDCACCRU.CCY_TYP);
        if (TDCACCRU.CCY_TYP == ' ') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CCY_TYP_MUST_INUT);
        }
        CEP.TRC(SCCGWA, TDCACCRU.BV_TYP);
        if (TDCACCRU.BV_TYP == '4') {
            IBS.init(SCCGWA, DCCUCINF);
            DCCUCINF.CARD_NO = TDCACCRU.AC_NO;
            S000_CALL_DCZUCINF();
            CEP.TRC(SCCGWA, DCCUCINF.CARD_STSW);
        }
        CEP.TRC(SCCGWA, TDCACCRU.OPP_AC_CNO);
        CEP.TRC(SCCGWA, WS_AC_TYPE);
        CEP.TRC(SCCGWA, WS_OP_APP);
        if (WS_AC_TYPE.equalsIgnoreCase("021") 
            && WS_CI_TYP == '1' 
            && WS_OP_APP.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = TDCACCRU.OPP_AC_CNO;
            T000_READ_DDTMST();
            if (DDRMST.AC_TYPE != 'A') {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_ERR_OPAC2);
            }
        }
        if (!TDCACCRU.CCY.equalsIgnoreCase("156") 
            && WS_CI_TYP == '1' 
            && TDCACCRU.CT_FLG == '0' 
            && TDCACCRU.TXN_AMT != 0) {
            IBS.init(SCCGWA, CICMLMT);
            CICMLMT.FUNC = 'A';
            CICMLMT.DATA.CI_NO = TDCACCRU.CI_NO;
            CICMLMT.DATA.LMT_TP = "01";
            CICMLMT.DATA.CMT = TDCACCRU.TXN_AMT;
            CICMLMT.DATA.CCY = TDCACCRU.CCY;
            S000_CALL_CIZMLMT();
        }
        if (TDRCMST.PROD_CD.trim().length() > 0) {
            CEP.TRC(SCCGWA, WS_AC_TYPE_M);
            CEP.TRC(SCCGWA, WS_AC_TYPE);
            if ((WS_AC_TYPE_M.equalsIgnoreCase("043") 
                && !WS_AC_TYPE.equalsIgnoreCase("035")) 
                || (WS_AC_TYPE_M.equalsIgnoreCase("044") 
                && !WS_AC_TYPE.equalsIgnoreCase("036")) 
                || (WS_AC_TYPE_M.equalsIgnoreCase("048") 
                && !WS_AC_TYPE.equalsIgnoreCase("033")) 
                || (WS_AC_TYPE_M.equalsIgnoreCase("047") 
                && !WS_AC_TYPE.equalsIgnoreCase("038")) 
                || (WS_AC_TYPE_M.equalsIgnoreCase("045") 
                && !WS_AC_TYPE.equalsIgnoreCase("031"))) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_PRD_NOT_MATCH);
            }
        }
        if (TDCACCRU.CT_FLG == '0' 
            && TDCACCRU.CCY_TYP == '2') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.2_CANOT_CASH);
        }
        CEP.TRC(SCCGWA, "JF10121");
        if ((WS_AC_TYPE.equalsIgnoreCase("037") 
            || WS_AC_TYPE.equalsIgnoreCase("021"))) {
            IBS.init(SCCGWA, TDROTHE);
            TDROTHE.KEY.ACTI_NO = TDCACCRU.ACTI_NO;
            T00_READ_TDTOTHE();
            if (TDROTHE.STR_DATE > SCCGWA.COMM_AREA.AC_DATE) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_TXN_DT_LESS_VAL_DT);
            }
            CEP.TRC(SCCGWA, TDROTHE.GRPS_NO);
            CEP.TRC(SCCGWA, WS_CI_TYP);
            if (TDROTHE.GRPS_NO.trim().length() > 0 
                && WS_CI_TYP != '1') {
                IBS.init(SCCGWA, CICMGRPM);
                CICMGRPM.FUNC = 'Q';
                CICMGRPM.INPUT_DATA.GRPS_NO = TDROTHE.GRPS_NO;
                CICMGRPM.INPUT_DATA.CI_NO = TDCACCRU.CI_NO;
                S000_LINK_CIZMGRPM();
            }
        }
        CEP.TRC(SCCGWA, "ASDF");
        WS_CCY_FOUND = 'N';
        for (WS_CNT = 1; WS_CNT <= 24 
            && WS_CCY_FOUND != 'Y'; WS_CNT += 1) {
            CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].MIN_CCYC);
            CEP.TRC(SCCGWA, TDCACCRU.CCY);
            if (TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].MIN_CCYC.equalsIgnoreCase(TDCACCRU.CCY)) {
                WS_CALD_FLG = TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].INT_RUL;
                CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].RAT_CD);
                WS_IRAT_CD = TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].RAT_CD;
                WS_PR_IRAT_CD = TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].PRRAT_CD;
                WS_DOCU_NO = TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].DOCU_NO;
                CEP.TRC(SCCGWA, TDCACCRU.XXTINT_FLG);
                CEP.TRC(SCCGWA, TDCACCRU.TXN_AMT);
                CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].MIN_AMTC);
                WS_CCY_FOUND = 'Y';
            }
        }
        if (TDCACCRU.RATE_TYP.trim().length() > 0) {
            WS_IRAT_CD = TDCACCRU.RATE_TYP;
        }
        CEP.TRC(SCCGWA, TDCACCRU.RATE_TYP);
        CEP.TRC(SCCGWA, WS_IRAT_CD);
        if (TDCPIOD.TXN_PRM.CASH_FLG == 'N' 
            && TDCACCRU.CT_FLG == '0') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CASHFG_ERR);
        }
        CEP.TRC(SCCGWA, "CHECK-PROD1");
        if (TDRCMST.PROD_CD.trim().length() == 0 
            && WS_AC_TYPE.equalsIgnoreCase("021")) {
            WS_DE_FLG = 'Y';
        } else {
            WS_DE_FLG = 'N';
        }
        CEP.TRC(SCCGWA, WS_DE_FLG);
        CEP.TRC(SCCGWA, TDCPIOD.TXN_PRM.BV_TYP);
        if (TDCPIOD.TXN_PRM.BV_TYP == null) TDCPIOD.TXN_PRM.BV_TYP = "";
        JIBS_tmp_int = TDCPIOD.TXN_PRM.BV_TYP.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.TXN_PRM.BV_TYP += " ";
        CEP.TRC(SCCGWA, TDCPIOD.TXN_PRM.BV_TYP.substring(9 - 1, 9 + 1 - 1));
        if (TDCACCRU.MAIN_FLG == 'N') {
                if (TDCPIOD.TXN_PRM.BV_TYP == null) TDCPIOD.TXN_PRM.BV_TYP = "";
                JIBS_tmp_int = TDCPIOD.TXN_PRM.BV_TYP.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.TXN_PRM.BV_TYP += " ";
            if (TDCACCRU.BV_TYP == '0' 
                    && !TDCPIOD.TXN_PRM.BV_TYP.substring(0, 1).equalsIgnoreCase("Y")                if (TDCPIOD.TXN_PRM.BV_TYP == null) TDCPIOD.TXN_PRM.BV_TYP = "";
                JIBS_tmp_int = TDCPIOD.TXN_PRM.BV_TYP.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.TXN_PRM.BV_TYP += " ";

                || TDCACCRU.BV_TYP == '1' 
                    && !TDCPIOD.TXN_PRM.BV_TYP.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("Y")                if (TDCPIOD.TXN_PRM.BV_TYP == null) TDCPIOD.TXN_PRM.BV_TYP = "";
                JIBS_tmp_int = TDCPIOD.TXN_PRM.BV_TYP.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.TXN_PRM.BV_TYP += " ";

                || TDCACCRU.BV_TYP == '2' 
                    && !TDCPIOD.TXN_PRM.BV_TYP.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("Y")                if (TDCPIOD.TXN_PRM.BV_TYP == null) TDCPIOD.TXN_PRM.BV_TYP = "";
                JIBS_tmp_int = TDCPIOD.TXN_PRM.BV_TYP.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.TXN_PRM.BV_TYP += " ";

                || TDCACCRU.BV_TYP == '3' 
                    && !TDCPIOD.TXN_PRM.BV_TYP.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("Y")                if (TDCPIOD.TXN_PRM.BV_TYP == null) TDCPIOD.TXN_PRM.BV_TYP = "";
                JIBS_tmp_int = TDCPIOD.TXN_PRM.BV_TYP.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.TXN_PRM.BV_TYP += " ";

                || TDCACCRU.BV_TYP == '4' 
                    && !TDCPIOD.TXN_PRM.BV_TYP.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("Y")                if (TDCPIOD.TXN_PRM.BV_TYP == null) TDCPIOD.TXN_PRM.BV_TYP = "";
                JIBS_tmp_int = TDCPIOD.TXN_PRM.BV_TYP.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.TXN_PRM.BV_TYP += " ";

                || TDCACCRU.BV_TYP == '7' 
                    && !TDCPIOD.TXN_PRM.BV_TYP.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("Y")                if (TDCPIOD.TXN_PRM.BV_TYP == null) TDCPIOD.TXN_PRM.BV_TYP = "";
                JIBS_tmp_int = TDCPIOD.TXN_PRM.BV_TYP.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.TXN_PRM.BV_TYP += " ";

                || TDCACCRU.BV_TYP == '8' 
                    && !TDCPIOD.TXN_PRM.BV_TYP.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("Y")) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_BVTYP_ERR);
            }
        }
        CEP.TRC(SCCGWA, "CHECK-PROD2");
        CEP.TRC(SCCGWA, TDCPIOD.TXN_PRM.DRAW_MTH);
        CEP.TRC(SCCGWA, TDCACCRU.DRAW_MTH);
        CEP.TRC(SCCGWA, TDCACCRU.AC_SEQ);
        CEP.TRC(SCCGWA, TDCACCRU.MAIN_FLG);
        if (TDCACCRU.MAIN_FLG == 'N' 
            && TDCACCRU.OPT != '2') {
            if (WS_DE_FLG == 'Y') {
            } else {
                    if (TDCPIOD.TXN_PRM.DRAW_MTH == null) TDCPIOD.TXN_PRM.DRAW_MTH = "";
                    JIBS_tmp_int = TDCPIOD.TXN_PRM.DRAW_MTH.length();
                    for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.TXN_PRM.DRAW_MTH += " ";
                if (TDCACCRU.DRAW_MTH == '1' 
                        && !TDCPIOD.TXN_PRM.DRAW_MTH.substring(0, 1).equalsIgnoreCase("Y")                    if (TDCPIOD.TXN_PRM.DRAW_MTH == null) TDCPIOD.TXN_PRM.DRAW_MTH = "";
                    JIBS_tmp_int = TDCPIOD.TXN_PRM.DRAW_MTH.length();
                    for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.TXN_PRM.DRAW_MTH += " ";

                    || TDCACCRU.DRAW_MTH == '2' 
                        && !TDCPIOD.TXN_PRM.DRAW_MTH.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("Y")                    if (TDCPIOD.TXN_PRM.DRAW_MTH == null) TDCPIOD.TXN_PRM.DRAW_MTH = "";
                    JIBS_tmp_int = TDCPIOD.TXN_PRM.DRAW_MTH.length();
                    for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.TXN_PRM.DRAW_MTH += " ";

                    || TDCACCRU.DRAW_MTH == '3' 
                        && !TDCPIOD.TXN_PRM.DRAW_MTH.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("Y")                    if (TDCPIOD.TXN_PRM.DRAW_MTH == null) TDCPIOD.TXN_PRM.DRAW_MTH = "";
                    JIBS_tmp_int = TDCPIOD.TXN_PRM.DRAW_MTH.length();
                    for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.TXN_PRM.DRAW_MTH += " ";

                    || TDCACCRU.DRAW_MTH == '5' 
                        && !TDCPIOD.TXN_PRM.DRAW_MTH.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("Y")                    if (TDCPIOD.TXN_PRM.DRAW_MTH == null) TDCPIOD.TXN_PRM.DRAW_MTH = "";
                    JIBS_tmp_int = TDCPIOD.TXN_PRM.DRAW_MTH.length();
                    for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.TXN_PRM.DRAW_MTH += " ";

                    || TDCACCRU.DRAW_MTH == '4' 
                        && !TDCPIOD.TXN_PRM.DRAW_MTH.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("Y")) {
                    CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DRAW_ERR);
                }
            }
            CEP.TRC(SCCGWA, "CHECK-PROD3");
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, TDCPIOD.TXN_PRM.CR_LMT);
            CEP.TRC(SCCGWA, TDCPIOD.TXN_PRM.DR_LMT);
            CEP.TRC(SCCGWA, TDCACCRU.CROS_CR_FLG);
            CEP.TRC(SCCGWA, TDCACCRU.CROS_DR_FLG);
            if (TDCACCRU.CROS_CR_FLG == ' ') {
                CEP.TRC(SCCGWA, "3331");
                TDCACCRU.CROS_CR_FLG = TDCPIOD.TXN_PRM.CR_LMT;
            }
            CEP.TRC(SCCGWA, TDCACCRU.CROS_CR_FLG);
            if (TDCACCRU.CROS_CR_FLG == '0') {
                TDCACCRU.CROS_CR_FLG = '1';
            }
            if (TDCACCRU.CROS_CR_FLG != '0' 
                && TDCACCRU.CROS_CR_FLG != '1' 
                && TDCACCRU.CROS_CR_FLG != '2') {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CROS_FLG_OVERF);
            } else {
                if (TDCPIOD.TXN_PRM.CR_LMT == '0' 
                    && (TDCACCRU.CROS_CR_FLG != '0' 
                    && TDCACCRU.CROS_CR_FLG != '2')) {
                    CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CROS_CR_N_MATCH_PRD);
                }
                if (TDCPIOD.TXN_PRM.CR_LMT == '2' 
                    && TDCACCRU.CROS_CR_FLG != '2') {
                    CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CROS_CR_N_MATCH_PRD);
                }
            }
            if (TDCACCRU.CROS_DR_FLG == ' ') {
                CEP.TRC(SCCGWA, "3332");
                TDCACCRU.CROS_DR_FLG = TDCPIOD.TXN_PRM.DR_LMT;
            }
            CEP.TRC(SCCGWA, TDCACCRU.CROS_DR_FLG);
            if (TDCACCRU.CROS_DR_FLG == '0') {
                TDCACCRU.CROS_DR_FLG = '1';
            }
            if (TDCACCRU.CROS_DR_FLG != '0' 
                && TDCACCRU.CROS_DR_FLG != '1' 
                && TDCACCRU.CROS_DR_FLG != '2') {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CROS_FLG_OVERF);
            } else {
                if (TDCPIOD.TXN_PRM.DR_LMT == '0' 
                    && (TDCACCRU.CROS_DR_FLG != '0' 
                    && TDCACCRU.CROS_DR_FLG != '2')) {
                    CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CROS_DR_N_MATCH_PRD);
                }
                if (TDCPIOD.TXN_PRM.DR_LMT == '2' 
                    && TDCACCRU.CROS_DR_FLG != '2') {
                    CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CROS_DR_N_MATCH_PRD);
                }
            }
            CEP.TRC(SCCGWA, "CHECK-PROD41");
        }
        if (TDCACCRU.MAIN_FLG == 'Y' 
            || TDCACCRU.OPT == '1') {
            IBS.init(SCCGWA, TDRCMST);
            TDRCMST.KEY.AC_NO = TDCACCRU.AC_NO;
            T000_READ_CMST();
            if (TDRCMST.CHNL_NO.equalsIgnoreCase("10202") 
                && TDRCMST.CI_TYP == '2' 
                && SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                && SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase(K_CHNL_TLR)) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CR_NOT_ALLOW);
            }
            WS_AC_BR = TDRCMST.OWNER_BRDP;
            if (TDRCMST.OWNER_BRDP == 0) {
                WS_AC_BR = TDRCMST.CHE_BR;
            }
            CEP.TRC(SCCGWA, WS_AC_BR);
            CEP.TRC(SCCGWA, TDRCMST.CROS_CR_FLG);
            CEP.TRC(SCCGWA, TDRCMST.CROS_DR_FLG);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
            CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.BBR);
            if (TDRCMST.CROS_CR_FLG == '0') {
                if (WS_AC_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                    IBS.init(SCCGWA, BPCPRGST);
                    BPCPRGST.BNK = SCCGWA.COMM_AREA.TR_BANK;
                    BPCPRGST.BR1 = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                    BPCPRGST.BR2 = WS_AC_BR;
                    CEP.TRC(SCCGWA, BPCPRGST.BR1);
                    CEP.TRC(SCCGWA, BPCPRGST.BR2);
                    S000_CALL_BPZPRGST();
                    CEP.TRC(SCCGWA, BPCPRGST.FLAG);
                    CEP.TRC(SCCGWA, BPCPRGST.LVL_RELATION);
                    CEP.TRC(SCCGWA, BPCPRGST.BRANCH_FLG);
                    if (BPCPRGST.BRANCH_FLG == 'Y') {
                    } else {
                        WS_MSGID = TDCMSG_ERROR_MSG.TD_CROS_CR_FLG_ERR;
                        S000_ERR_MSG_PROC();
                    }
                }
            } else if (TDRCMST.CROS_CR_FLG == '1') {
            } else if (TDRCMST.CROS_CR_FLG == '2') {
                if (WS_AC_BR != BPCPORUP.DATA_INFO.BBR) {
                    WS_MSGID = TDCMSG_ERROR_MSG.TD_CR_FLG_ERR;
                    S000_ERR_MSG_PROC();
                }
            }
            if ((TDCACCRU.OPT != '2') 
                && (TDCACCRU.BV_TYP != '4')) {
                if (TDRCMST.STS != '0') {
                    CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_STS_NOT_NORMAL);
                }
            }
        }
        CEP.TRC(SCCGWA, "CHECK-PROD6");
        if (TDCACCRU.SPRD_PNT != 0 
            && TDCACCRU.SPRD_PCT != 0 
            && TDCACCRU.INT_RAT != 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INPT_ERR);
        }
        CEP.TRC(SCCGWA, "CHECK-PROD7");
        if (TDCACCRU.INSTR_MTH == ' ') {
            TDCACCRU.INSTR_MTH = '0';
        }
        WS_J = 1;
        CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.CCY_INF[1-1].MIN_CCYC);
        CEP.TRC(SCCGWA, TDCACCRU.CCY);
        while (WS_J <= 24 
            && !TDCACCRU.CCY.equalsIgnoreCase(TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].MIN_CCYC)) {
            CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].MIN_CCYC);
            CEP.TRC(SCCGWA, WS_J);
            WS_J += 1;
        }
        if (TDCACCRU.CCY.equalsIgnoreCase(TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].MIN_CCYC)) {
            if (TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].MIN_CCYC.equalsIgnoreCase("CNY")) {
                TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].MIN_CCYC = "156";
            }
        }
        if (WS_J > 24) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CCY_ERR);
        }
        WS_W = WS_J;
        CEP.TRC(SCCGWA, "CHECK-PROD71");
        WS_I = 1;
        CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM);
        CEP.TRC(SCCGWA, "111");
        if (TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM == null) TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM = "";
        JIBS_tmp_int = TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM += " ";
        if (TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM.substring(0, 1).equalsIgnoreCase("Y")) {
            REDEFINES93.WS_TERM_ONE_DAY = "D001";
            WS_TERM_DESC = IBS.CLS2CPY(SCCGWA, REDEFINES93);
        }
        CEP.TRC(SCCGWA, "222");
        if (TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM == null) TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM = "";
        JIBS_tmp_int = TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM += " ";
        if (TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("Y")) {
            REDEFINES93.WS_TERM_SEVEN_DAY = "D007";
            WS_TERM_DESC = IBS.CLS2CPY(SCCGWA, REDEFINES93);
        }
        CEP.TRC(SCCGWA, "333");
        if (TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM == null) TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM = "";
        JIBS_tmp_int = TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM += " ";
        if (TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("Y")) {
            REDEFINES93.WS_TERM_ONE_MONTH = "M001";
            WS_TERM_DESC = IBS.CLS2CPY(SCCGWA, REDEFINES93);
        }
        CEP.TRC(SCCGWA, "444");
        if (TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM == null) TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM = "";
        JIBS_tmp_int = TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM += " ";
        if (TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("Y")) {
            REDEFINES93.WS_TERM_THREE_MONTH = "M003";
            WS_TERM_DESC = IBS.CLS2CPY(SCCGWA, REDEFINES93);
        }
        if (TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM == null) TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM = "";
        JIBS_tmp_int = TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM += " ";
        if (TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("Y")) {
            REDEFINES93.WS_TERM_SIX_MONTH = "M006";
            WS_TERM_DESC = IBS.CLS2CPY(SCCGWA, REDEFINES93);
        }
        CEP.TRC(SCCGWA, "555");
        if (TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM == null) TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM = "";
        JIBS_tmp_int = TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM += " ";
        if (TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("Y")) {
            REDEFINES93.WS_TERM_ONE_YEAR = "Y001";
            WS_TERM_DESC = IBS.CLS2CPY(SCCGWA, REDEFINES93);
        }
        CEP.TRC(SCCGWA, "666");
        if (TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM == null) TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM = "";
        JIBS_tmp_int = TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM += " ";
        if (TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("Y")) {
            REDEFINES93.WS_TERM_TWO_YEAR = "Y002";
            WS_TERM_DESC = IBS.CLS2CPY(SCCGWA, REDEFINES93);
        }
        CEP.TRC(SCCGWA, "777");
        if (TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM == null) TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM = "";
        JIBS_tmp_int = TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM += " ";
        if (TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("Y")) {
            REDEFINES93.WS_TERM_THREE_YEAR = "Y003";
            WS_TERM_DESC = IBS.CLS2CPY(SCCGWA, REDEFINES93);
        }
        CEP.TRC(SCCGWA, "888");
        if (TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM == null) TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM = "";
        JIBS_tmp_int = TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM += " ";
        if (TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("Y")) {
            REDEFINES93.WS_TERM_FIVE_YEAR = "Y005";
            WS_TERM_DESC = IBS.CLS2CPY(SCCGWA, REDEFINES93);
        }
        CEP.TRC(SCCGWA, "999");
        if (TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM == null) TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM = "";
        JIBS_tmp_int = TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM += " ";
        if (TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("Y")) {
            REDEFINES93.WS_TERM_SIX_YEAR = "Y006";
            WS_TERM_DESC = IBS.CLS2CPY(SCCGWA, REDEFINES93);
        }
        CEP.TRC(SCCGWA, "000");
        if (TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM == null) TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM = "";
        JIBS_tmp_int = TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM += " ";
        if (TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("Y")) {
            REDEFINES93.WS_TERM_NOT_STANDARD = "S000";
            WS_TERM_DESC = IBS.CLS2CPY(SCCGWA, REDEFINES93);
        }
        CEP.TRC(SCCGWA, "AAA");
        CEP.TRC(SCCGWA, REDEFINES93.WS_TERM_ONE_DAY);
        CEP.TRC(SCCGWA, REDEFINES93.WS_TERM_SEVEN_DAY);
        CEP.TRC(SCCGWA, REDEFINES93.WS_TERM_ONE_MONTH);
        CEP.TRC(SCCGWA, REDEFINES93.WS_TERM_THREE_MONTH);
        CEP.TRC(SCCGWA, REDEFINES93.WS_TERM_SIX_MONTH);
        CEP.TRC(SCCGWA, REDEFINES93.WS_TERM_ONE_YEAR);
        CEP.TRC(SCCGWA, REDEFINES93.WS_TERM_TWO_YEAR);
        CEP.TRC(SCCGWA, REDEFINES93.WS_TERM_THREE_YEAR);
        CEP.TRC(SCCGWA, REDEFINES93.WS_TERM_FIVE_YEAR);
        CEP.TRC(SCCGWA, REDEFINES93.WS_TERM_SIX_YEAR);
        CEP.TRC(SCCGWA, REDEFINES93.WS_TERM_NOT_STANDARD);
        CEP.TRC(SCCGWA, TDCACCRU.TERM);
        if (!TDCACCRU.TERM.equalsIgnoreCase(REDEFINES93.WS_TERM_ONE_DAY) 
            && !TDCACCRU.TERM.equalsIgnoreCase(REDEFINES93.WS_TERM_SEVEN_DAY) 
            && !TDCACCRU.TERM.equalsIgnoreCase(REDEFINES93.WS_TERM_ONE_MONTH) 
            && !TDCACCRU.TERM.equalsIgnoreCase(REDEFINES93.WS_TERM_THREE_MONTH) 
            && !TDCACCRU.TERM.equalsIgnoreCase(REDEFINES93.WS_TERM_SIX_MONTH) 
            && !TDCACCRU.TERM.equalsIgnoreCase(REDEFINES93.WS_TERM_ONE_YEAR) 
            && !TDCACCRU.TERM.equalsIgnoreCase(REDEFINES93.WS_TERM_TWO_YEAR) 
            && !TDCACCRU.TERM.equalsIgnoreCase(REDEFINES93.WS_TERM_THREE_YEAR) 
            && !TDCACCRU.TERM.equalsIgnoreCase(REDEFINES93.WS_TERM_FIVE_YEAR) 
            && !TDCACCRU.TERM.equalsIgnoreCase(REDEFINES93.WS_TERM_SIX_YEAR) 
            && !TDCACCRU.TERM.equalsIgnoreCase(REDEFINES93.WS_TERM_NOT_STANDARD) 
            && !TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM1.equalsIgnoreCase(TDCACCRU.TERM) 
            && !TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM2.equalsIgnoreCase(TDCACCRU.TERM) 
            && !TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM3.equalsIgnoreCase(TDCACCRU.TERM) 
            && !TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM4.equalsIgnoreCase(TDCACCRU.TERM) 
            && !TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM5.equalsIgnoreCase(TDCACCRU.TERM) 
            && !TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].TERM6.equalsIgnoreCase(TDCACCRU.TERM) 
            && ((!TDCACCRU.TERM.equalsIgnoreCase("S000") 
            && TDCACCRU.TERM.trim().length() > 0) 
            && (TDCPIOD.EXP_PRM.DOCU_TYP != '0' 
            && TDCPIOD.EXP_PRM.DOCU_TYP != 'Y'))) {
            CEP.TRC(SCCGWA, "BBB");
            R000_GET_TERM();
            if (WS_ERROR == 'Y') {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_TERM_ERR);
            }
        }
        CEP.TRC(SCCGWA, "CHECK-PROD81");
        if (TDCACCRU.TERM.equalsIgnoreCase("S000") 
            && TDCACCRU.EXP_DT == 0) {
            if (TDCACCRU.OPT != '2' 
                && (TDCPIOD.OTH_PRM.DOCU_FLG == '0' 
                || TDCPIOD.OTH_PRM.DOCU_FLG == 'Y')) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_EXPDT_ERR);
            }
        }
        CEP.TRC(SCCGWA, "CHECK-PROD91");
        WS_I = 1;
        if (WS_I > 12) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INRMTH_ERR);
        }
        CEP.TRC(SCCGWA, "CHECK-PROD8");
        CEP.TRC(SCCGWA, TDCACCRU.TXN_AMT);
        WS_ADD_AMTC = TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].ADD_AMTC;
        WS_MIN_CCYC = TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].MIN_CCYC;
        R000_CHK_IF_DEF_PRD();
        WS_REF_CCY = TDCPIOD.OTH_PRM.REF_CCY;
        if (WS_CCY_DEF_FLG == 'Y') {
            for (WS_CNT = 1; WS_CNT <= 24; WS_CNT += 1) {
                if (TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].MIN_CCYC.equalsIgnoreCase(TDCACCRU.CCY)) {
                    WS_MIN_AMTC = TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].MIN_AMTC;
                    WS_MAX_AMTC = TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].MAX_AMT;
                    WS_ADD_AMTC = TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].ADD_AMTC;
                    WS_CNT = 99;
                }
            }
        }
        if (WS_CCY_DEF_FLG == 'N') {
            for (WS_CNT = 1; WS_CNT <= 24; WS_CNT += 1) {
                CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.REF_CCY);
                if (TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].MIN_CCYC.equalsIgnoreCase(TDCPIOD.OTH_PRM.REF_CCY)) {
                    WS_MIN_AMTC_REF = TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].MIN_AMTC;
                    WS_MAX_AMTC_REF = TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].MAX_AMT;
                    WS_ADD_AMTC_REF = TDCPIOD.OTH_PRM.CCY_INF[WS_CNT-1].ADD_AMTC;
                    WS_CNT = 99;
                }
            }
            CEP.TRC(SCCGWA, WS_MIN_AMTC_REF);
            if (WS_MIN_AMTC_REF != 0) {
                CEP.TRC(SCCGWA, "COM-MIN-AMTC");
                WS_BUY_AMT = WS_MIN_AMTC_REF;
                R000_AMT_EX_PROC();
                CEP.TRC(SCCGWA, WS_SELL_AMT);
                WS_MIN_AMTC = WS_SELL_AMT;
            }
            CEP.TRC(SCCGWA, WS_MIN_AMTC);
            if (WS_MAX_AMTC_REF != 0) {
                CEP.TRC(SCCGWA, "COM-MAX-AMTC");
                WS_BUY_AMT = WS_MAX_AMTC_REF;
                R000_AMT_EX_PROC();
                WS_MAX_AMTC = WS_SELL_AMT;
            }
            if (WS_ADD_AMTC_REF != 0) {
                CEP.TRC(SCCGWA, "COM-MAX-AMTC");
                WS_BUY_AMT = WS_ADD_AMTC_REF;
                R000_AMT_EX_PROC();
                WS_ADD_AMTC = WS_SELL_AMT;
            }
        }
        if (WS_MIN_AMTC > 0) {
            CEP.TRC(SCCGWA, TDCACCRU.TXN_AMT);
            CEP.TRC(SCCGWA, WS_MIN_AMTC);
            if (TDCACCRU.TXN_AMT < WS_MIN_AMTC 
                && TDCACCRU.XXTINT_FLG != 'Y' 
                && !(WS_AC_TYPE.equalsIgnoreCase("035") 
                && TDCACCRU.GRPAUTO_FLG == 'Y')) {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_AMT_TOO_LITTLE;
                S000_ERR_MSG_PROC();
            }
        }
        WS_BAL_DZ = TDCACCRU.TXN_AMT - WS_MIN_AMTC;
        if (WS_ADD_AMTC > 0) {
            CEP.TRC(SCCGWA, WS_BAL_DZ);
            WS_BAL_YE = WS_BAL_DZ % WS_ADD_AMTC;
            WS_BAL_XY = (long) ((WS_BAL_DZ - WS_BAL_YE) / WS_ADD_AMTC);
            CEP.TRC(SCCGWA, WS_BAL_XY);
            CEP.TRC(SCCGWA, WS_BAL_YE);
            if (WS_BAL_YE != 0) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_NO_START_BAL_ERR);
            }
        }
        CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.PLAN_FLG);
        CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.PAY_PERD);
        CEP.TRC(SCCGWA, TDCACCRU.GK_TERM);
        CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.PLAN_FLG);
        CEP.TRC(SCCGWA, TDCACCRU.PERD_TYP);
        CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.INT_PRD2);
        CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.INT_PRD3);
        CEP.TRC(SCCGWA, TDCACCRU.CD_AUTO_FLG);
        if (TDCPIOD.OTH_PRM.PLAN_FLG != '1') {
            if ((TDCACCRU.PERD_TYP == '1' 
                || TDCACCRU.PERD_TYP == '2') 
                && TDCPIOD.OTH_PRM.INT_PRD2 != '1' 
                && TDCPIOD.OTH_PRM.INT_PRD3 != '2') {
                CEP.TRC(SCCGWA, "111");
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_PERD_ERR);
            }
            CEP.TRC(SCCGWA, TDCACCRU.PERD_TYP);
            CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.INT_PRD1);
            if (TDCACCRU.PERD_TYP == '0' 
                && TDCPIOD.OTH_PRM.INT_PRD1 != '0') {
                CEP.TRC(SCCGWA, "222");
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_PERD_ERR);
            }
        }
        if (TDCPIOD.OTH_PRM.INT_PERD == null) TDCPIOD.OTH_PRM.INT_PERD = "";
        JIBS_tmp_int = TDCPIOD.OTH_PRM.INT_PERD.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPIOD.OTH_PRM.INT_PERD += " ";
        if (TDCPIOD.OTH_PRM.INT_PERD.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("Y") 
            && TDCACCRU.FRAT_DT != 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_OTHER_ERR);
        }
        if (TDCACCRU.TERM.equalsIgnoreCase("S000") 
            && (TDCACCRU.INSTR_MTH == '3' 
            || TDCACCRU.INSTR_MTH == '6')) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INSTR_MTH_NOT_ALLOW);
        }
        CEP.TRC(SCCGWA, TDCACCRU.FST_FLG);
        CEP.TRC(SCCGWA, WS_AC_TYPE);
        CEP.TRC(SCCGWA, TDCACCRU.AC_NO);
        if ((WS_AC_TYPE.equalsIgnoreCase("035") 
            || WS_AC_TYPE.equalsIgnoreCase("036")) 
            && TDCACCRU.FST_FLG != '1') {
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.AC_NO = TDCACCRU.AC_NO;
            T000_CHECK_SMST_SW();
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1") 
                || TDRSMST.STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.TRC(SCCGWA, TDCACCRU.XXTLM_FLG);
                if (TDCACCRU.XXTLM_FLG != '1') {
                    CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_MAIN_AC_HOLD);
                }
            }
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, TDCACCRU.TXN_PNT);
        S000_CALL_BPZPQORG();
        CEP.TRC(SCCGWA, BPCPQORG.ATTR);
        if (!TDCACCRU.CCY.equalsIgnoreCase("156")) {
            CEP.TRC(SCCGWA, BPCPQORG.FX_BUSI);
            if (BPCPQORG.FX_BUSI.equalsIgnoreCase("00")) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_TR_BR_NO_FX_AUTH);
            }
        }
    }
    public void B495_CHECK_PROD_CI_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICACCU.DATA.EP_FLG);
        CEP.TRC(SCCGWA, CICACCU.DATA.RESIDENT);
        CEP.TRC(SCCGWA, TDCPIOD.TXN_PRM.STA_LMT);
        CEP.TRC(SCCGWA, TDCPIOD.TXN_PRM.RSID_LMT);
        if ((TDCPIOD.TXN_PRM.STA_LMT == '1' 
            && CICCUST.O_DATA.O_EP_FLG != 'Y') 
            || (TDCPIOD.TXN_PRM.RSID_LMT == '2' 
            && CICCUST.O_DATA.O_RESIDENT == 'Y')) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_STA_ERR);
        }
        CEP.TRC(SCCGWA, "CHECK-PROD4");
        CEP.TRC(SCCGWA, TDCPIOD.TXN_PRM.RSID_LMT);
        CEP.TRC(SCCGWA, CICACCU.DATA.RESIDENT);
        if (TDCPIOD.TXN_PRM.RSID_LMT != CICACCU.DATA.RESIDENT 
            && TDCPIOD.TXN_PRM.RSID_LMT != '0') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_RSID_ERR);
        }
    }
    public void B720_WRT_DCTSSTS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        IBS.init(SCCGWA, DCCUSSTS);
        DCCUSSTS.OPT = 'A';
        if (TDCACCRU.AC_NO.trim().length() > 0) {
            DCCUSSTS.DATA.KEY.AC = TDCACCRU.AC_NO;
            DCCUSSTS.DATA.KEY.SEQ = WS_AC_SEQ;
        } else {
            DCCUSSTS.DATA.KEY.AC = TDCACCRU.AC_NO;
        }
        DCCUSSTS.DATA.KEY.CCY = TDCACCRU.CCY;
        DCCUSSTS.DATA.KEY.CCY_TYPE = TDCACCRU.CCY_TYP;
        DCCUSSTS.DATA.STS = '1';
        DCCUSSTS.DATA.OPEN_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCCUSSTS.DATA.OPEN_DP = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DCCUSSTS.DATA.OPEN_BR = BPCPQORG.BRANCH_BR;
        DCCUSSTS.DATA.FRM_APP = "TD";
        DCCUSSTS.DATA.CROS_DR_FLG = TDCACCRU.CROS_DR_FLG;
        S000_CALL_DCZUSSTS();
    }
    public void B730_WRT_TDTOCAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDROCAC);
    }
    public void B730_WRT_BPTOCAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSOCAC);
        BPCSOCAC.FUNC = 'C';
        BPCSOCAC.AC = TDCACCRU.AC_NO;
        BPCSOCAC.ACO_AC = TDCACCRU.ACO_AC;
        if ((WS_AC_TYPE.equalsIgnoreCase("035") 
            || WS_AC_TYPE.equalsIgnoreCase("036"))) {
            if (TDCACCRU.FST_FLG == '1' 
                && WS_SEQ2 > 1) {
                BPCSOCAC.ACO_AC = TDRSMST.KEY.ACO_AC;
            }
        }
        BPCSOCAC.STS = 'N';
        CEP.TRC(SCCGWA, WS_AC_TYPE);
        CEP.TRC(SCCGWA, WS_CI_TYP);
        if (WS_AC_TYPE.equalsIgnoreCase("029")) {
            BPCSOCAC.WORK_TYP = "02";
        } else if (WS_AC_TYPE.equalsIgnoreCase("020")) {
            if (WS_CI_TYP == '1') {
                BPCSOCAC.WORK_TYP = "03";
            } else {
                BPCSOCAC.WORK_TYP = "15";
            }
        } else if (WS_AC_TYPE.equalsIgnoreCase("025")) {
            BPCSOCAC.WORK_TYP = "05";
        } else if (WS_AC_TYPE.equalsIgnoreCase("024")) {
            BPCSOCAC.WORK_TYP = "06";
        } else if (WS_AC_TYPE.equalsIgnoreCase("027")) {
            BPCSOCAC.WORK_TYP = "07";
        } else if (WS_AC_TYPE.equalsIgnoreCase("022")) {
            if (WS_CI_TYP == '1') {
                BPCSOCAC.WORK_TYP = "08";
            } else {
                BPCSOCAC.WORK_TYP = "19";
            }
        } else if (WS_AC_TYPE.equalsIgnoreCase("026")) {
            BPCSOCAC.WORK_TYP = "09";
        } else if (WS_AC_TYPE.equalsIgnoreCase("021")) {
            if (WS_CI_TYP == '1') {
                BPCSOCAC.WORK_TYP = "10";
            } else {
                BPCSOCAC.WORK_TYP = "16";
            }
        } else if (WS_AC_TYPE.equalsIgnoreCase("032")) {
            BPCSOCAC.WORK_TYP = "11";
        } else if (WS_AC_TYPE.equalsIgnoreCase("035")) {
            BPCSOCAC.WORK_TYP = "12";
        } else if (WS_AC_TYPE.equalsIgnoreCase("036")) {
            BPCSOCAC.WORK_TYP = "13";
        } else if (WS_AC_TYPE.equalsIgnoreCase("028")) {
            BPCSOCAC.WORK_TYP = "17";
        } else if (WS_AC_TYPE.equalsIgnoreCase("037")) {
            BPCSOCAC.WORK_TYP = "18";
        } else if (WS_AC_TYPE.equalsIgnoreCase("031")) {
            BPCSOCAC.WORK_TYP = "20";
        } else if (WS_AC_TYPE.equalsIgnoreCase("038")) {
            BPCSOCAC.WORK_TYP = "28";
        } else if (WS_AC_TYPE.equalsIgnoreCase("033")) {
            BPCSOCAC.WORK_TYP = "29";
        }
        if (WS_AC_TYPE_M.equalsIgnoreCase("046")) {
            BPCSOCAC.WORK_TYP = "31";
        }
        if (WS_AC_TYPE_M.equalsIgnoreCase("047")) {
            BPCSOCAC.WORK_TYP = "28";
        }
        BPCSOCAC.CI_TYPE = WS_CI_TYP;
        if (TDCACCRU.BV_TYP == '0') {
            BPCSOCAC.BV_TYP = '8';
        } else if (TDCACCRU.BV_TYP == '1') {
            BPCSOCAC.BV_TYP = '6';
        } else if (TDCACCRU.BV_TYP == '2') {
            BPCSOCAC.BV_TYP = '4';
        } else if (TDCACCRU.BV_TYP == '3') {
            BPCSOCAC.BV_TYP = '5';
        } else if (TDCACCRU.BV_TYP == '7') {
            BPCSOCAC.BV_TYP = '7';
        } else if (TDCACCRU.BV_TYP == '8') {
            BPCSOCAC.BV_TYP = '8';
        } else if (TDCACCRU.BV_TYP == '4') {
            IBS.init(SCCGWA, DCCUCINF);
            DCCUCINF.CARD_NO = TDCACCRU.AC_NO;
            S000_CALL_DCZUCINF();
            CEP.TRC(SCCGWA, DCCUCINF.SVR_RSC_CD);
            if (DCCUCINF.SVR_RSC_CD.equalsIgnoreCase("220")) {
                BPCSOCAC.BV_TYP = '1';
            } else {
                BPCSOCAC.BV_TYP = '2';
            }
        }
        BPCSOCAC.BV_NO = TDCACCRU.BV_NO;
        if (BPCSOCAC.BV_NO.equalsIgnoreCase("000000000")) {
            BPCSOCAC.BV_NO = " ";
        }
        if (TDCACCRU.ID_TYP.trim().length() == 0 
            || TDCACCRU.ID_NO.trim().length() == 0) {
            IBS.init(SCCGWA, CICCUST);
            CICCUST.FUNC = 'C';
            CICCUST.DATA.CI_NO = TDCACCRU.CI_NO;
            S000_CALL_CIZCUST();
            BPCSOCAC.ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
            BPCSOCAC.ID_NO = CICCUST.O_DATA.O_ID_NO;
        } else {
            BPCSOCAC.ID_TYP = TDCACCRU.ID_TYP;
            BPCSOCAC.ID_NO = TDCACCRU.ID_NO;
        }
        CEP.TRC(SCCGWA, "JF0331-1");
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_AC_CNM);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_NM);
        CEP.TRC(SCCGWA, TDCACCRU.AC_NAME);
        BPCSOCAC.CI_CNM = TDCACCRU.AC_NAME;
        BPCSOCAC.AC_CNM = TDCACCRU.AC_NAME;
        BPCSOCAC.CI_CNM = CICCUST.O_DATA.O_CI_NM;
        BPCSOCAC.SEQ = TDCACCRU.AC_SEQ;
        if (WS_AC_TYPE.equalsIgnoreCase("035") 
            || WS_AC_TYPE.equalsIgnoreCase("036")) {
            BPCSOCAC.SEQ = 1;
        }
        CEP.TRC(SCCGWA, TDRIREV.CON_RATE);
        BPCSOCAC.OPEN_RAT = TDRIREV.CON_RATE;
        BPCSOCAC.CCY = TDCACCRU.CCY;
        BPCSOCAC.CCY_TYPE = TDCACCRU.CCY_TYP;
        BPCSOCAC.AUT_TLR = " ";
        BPCSOCAC.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSOCAC.OPEN_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSOCAC.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        BPCSOCAC.OPEN_AMT = TDCACCRU.TXN_AMT;
        BPCSOCAC.PROD_CD = TDCACCRU.PROD_CD;
        BPCSOCAC.BR = WS_CHE_BR;
        if (SCCGWA.COMM_AREA.AGENT_FLG == 'Y') {
            CEP.TRC(SCCGWA, "OCAC");
            BPCSOCAC.OTH_RPT_NM = CICGAGA_AGENT_AREA.CI_NM;
            BPCSOCAC.OTH_ID_TYP = CICGAGA_AGENT_AREA.ID_TYP;
            BPCSOCAC.OTH_ID_NO = CICGAGA_AGENT_AREA.ID_NO;
        }
        S000_CALL_BPZSOCAC();
    }
    public void B730_WRT_BPTOCAC_CANCEL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSOCAC);
        BPCSOCAC.AC = TDCACCRU.AC_NO;
        BPCSOCAC.ACO_AC = TDCACCRU.ACO_AC;
        BPCSOCAC.STS = 'N';
        S000_CALL_BPZSOCAC();
    }
    public void B730_WRT_TDTOCAC_CANCEL() throws IOException,SQLException,Exception {
    }
    public void B500_GEN_ACOAC() throws IOException,SQLException,Exception {
        WS_SEQ += 1;
        IBS.init(SCCGWA, BPCCGAC);
        BPCCGAC.DATA.PRD_CODE = TDCACCRU.PROD_CD;
        BPCCGAC.DATA.AC_KIND = '2';
        BPCCGAC.DATA.ACO_AC_FLG = '8';
        BPCCGAC.DATA.ACO_AC_MMO = "12";
        BPCCGAC.DATA.ACO_AC_DEF = 12;
        S000_CALL_BPZGACNO();
        WS_ACO_AC = BPCCGAC.DATA.ACO_AC;
        TDCACCRU.ACO_AC = BPCCGAC.DATA.ACO_AC;
        CEP.TRC(SCCGWA, BPCCGAC.DATA.ACO_AC);
        IBS.init(SCCGWA, CICSACAC);
        if (TDCACCRU.ANY_FLG == 'S') {
            CICSACAC.DATA.MUL_FLG = '1';
        }
        if (TDCACCRU.ANY_FLG == 'E') {
            CICSACAC.DATA.MUL_FLG = '2';
        }
        CICSACAC.FUNC = 'A';
        CICSACAC.DATA.PROD_CD = TDCACCRU.PROD_CD;
        CICSACAC.DATA.ACAC_NO = WS_ACO_AC;
        CICSACAC.DATA.AGR_NO = TDCACCRU.AC_NO;
        CICSACAC.DATA.AGR_SEQ = TDCACCRU.AC_SEQ;
        if (!WS_AC_TYPE_M.equalsIgnoreCase("041")) {
            CICSACAC.DATA.BV_NO = TDCACCRU.BV_NO;
        }
        CICSACAC.DATA.CCY = TDCACCRU.CCY;
        CICSACAC.DATA.CR_FLG = TDCACCRU.CCY_TYP;
        CEP.TRC(SCCGWA, TDCACCRU.SHOW);
        CICSACAC.DATA.ACAC_CTL = "ZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROS";
        if (CICSACAC.DATA.ACAC_CTL == null) CICSACAC.DATA.ACAC_CTL = "";
        JIBS_tmp_int = CICSACAC.DATA.ACAC_CTL.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) CICSACAC.DATA.ACAC_CTL += " ";
        JIBS_tmp_str[0] = "" + TDCACCRU.SHOW;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        CICSACAC.DATA.ACAC_CTL = JIBS_tmp_str[0] + CICSACAC.DATA.ACAC_CTL.substring(1);
        if (TDCACCRU.SHOW == ' ') {
            if (CICSACAC.DATA.ACAC_CTL == null) CICSACAC.DATA.ACAC_CTL = "";
            JIBS_tmp_int = CICSACAC.DATA.ACAC_CTL.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) CICSACAC.DATA.ACAC_CTL += " ";
            CICSACAC.DATA.ACAC_CTL = "0" + CICSACAC.DATA.ACAC_CTL.substring(1);
        }
        CEP.TRC(SCCGWA, TDCACCRU.FST_FLG);
        CEP.TRC(SCCGWA, WS_SEQ);
        CEP.TRC(SCCGWA, TDCACCRU.MAIN_FLG);
        CEP.TRC(SCCGWA, TDCACCRU.BV_TYP);
        if ((TDCACCRU.FST_FLG == '1' 
            && WS_SEQ == 1) 
            || TDCACCRU.MAIN_FLG == 'N') {
            if (TDCACCRU.BV_TYP != '4') {
                if (CICSACAC.DATA.ACAC_CTL == null) CICSACAC.DATA.ACAC_CTL = "";
                JIBS_tmp_int = CICSACAC.DATA.ACAC_CTL.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) CICSACAC.DATA.ACAC_CTL += " ";
                CICSACAC.DATA.ACAC_CTL = CICSACAC.DATA.ACAC_CTL.substring(0, 2 - 1) + "1" + CICSACAC.DATA.ACAC_CTL.substring(2 + 1 - 1);
            }
        }
        if ((WS_AC_TYPE.equalsIgnoreCase("035") 
            || WS_AC_TYPE.equalsIgnoreCase("036")) 
            && ((TDCACCRU.FST_FLG == '1' 
            && WS_SEQ != 1) 
            || TDCACCRU.FST_FLG != '1')) {
            if (CICSACAC.DATA.ACAC_CTL == null) CICSACAC.DATA.ACAC_CTL = "";
            JIBS_tmp_int = CICSACAC.DATA.ACAC_CTL.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) CICSACAC.DATA.ACAC_CTL += " ";
            CICSACAC.DATA.ACAC_CTL = "1" + CICSACAC.DATA.ACAC_CTL.substring(1);
        }
        CICSACAC.DATA.FRM_APP = "TD";
        CICSACAC.DATA.OPN_BR = WS_CHE_BR;
        CICSACAC.DATA.OWNER_BK = WS_OWNER_BK;
        CICSACAC.DATA.OPEN_DT = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, TDCACCRU.MAIN_FLG);
        CEP.TRC(SCCGWA, TDCACCRU.BV_TYP);
        CEP.TRC(SCCGWA, CICSACAC.DATA.NOSEQ_FLG);
        if (TDCACCRU.MAIN_FLG != 'Y') {
            CICSACAC.DATA.NOSEQ_FLG = 'Y';
        }
        if (TDCACCRU.BV_TYP == '4' 
            || TDCACCRU.OPT == '2') {
            CICSACAC.DATA.NOSEQ_FLG = 'N';
        }
        if (WS_AC_TYPE.equalsIgnoreCase("035") 
            || WS_AC_TYPE.equalsIgnoreCase("036")) {
            CICSACAC.DATA.NOSEQ_FLG = 'N';
        }
        S000_CALL_CIZSACAC();
        CEP.TRC(SCCGWA, "JFTEST111");
        CEP.TRC(SCCGWA, CICSACAC.DATA.AGR_SEQ);
        TDCACCRU.AC_SEQ = CICSACAC.DATA.AGR_SEQ;
        WS_AC_SEQ = CICSACAC.DATA.AGR_SEQ;
        if (TDCACCRU.BV_NO.trim().length() == 0 
            && TDCACCRU.BV_TYP == '8') {
            CEP.TRC(SCCGWA, "UP CITACAC");
            IBS.init(SCCGWA, CICSACAC);
            CICSACAC.FUNC = 'M';
            CICSACAC.DATA.ACAC_NO = WS_ACO_AC;
            if (CICSACAC.DATA.BV_NO == null) CICSACAC.DATA.BV_NO = "";
            JIBS_tmp_int = CICSACAC.DATA.BV_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) CICSACAC.DATA.BV_NO += " ";
            CICSACAC.DATA.BV_NO = "000" + CICSACAC.DATA.BV_NO.substring(3);
            if (CICSACAC.DATA.BV_NO == null) CICSACAC.DATA.BV_NO = "";
            JIBS_tmp_int = CICSACAC.DATA.BV_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) CICSACAC.DATA.BV_NO += " ";
            JIBS_tmp_str[0] = "" + WS_AC_SEQ;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            CICSACAC.DATA.BV_NO = CICSACAC.DATA.BV_NO.substring(0, 4 - 1) + JIBS_tmp_str[0] + CICSACAC.DATA.BV_NO.substring(4 + 6 - 1);
            TDCACCRU.BV_NO = CICSACAC.DATA.BV_NO;
            if (TDCACCRU.MAIN_FLG != 'Y') {
                CICSACAC.DATA.NOSEQ_FLG = 'N';
            } else {
                CICSACAC.DATA.NOSEQ_FLG = 'Y';
                S000_CALL_CIZSACAC();
            }
        }
        B015_CHECK_CI_LIST();
        CEP.TRC(SCCGWA, "GEN-ACOAC-END");
    }
    public void B500_WRT_TDTCMST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRCMST);
        TDRCMST.KEY.AC_NO = TDCACCRU.AC_NO;
        TDRCMST.STS = '0';
        if (TDCACCRU.BV_TYP == '0' 
            || TDCACCRU.BV_TYP == '1' 
            || TDCACCRU.BV_TYP == '2' 
            || TDCACCRU.BV_TYP == '3' 
            || (TDCACCRU.MAIN_FLG != 'Y' 
            && TDCACCRU.BV_TYP == '8')) {
            TDRCMST.BV_TYP = TDCACCRU.BV_TYP;
        }
        TDRCMST.PROC_SEQ += 1;
        CEP.TRC(SCCGWA, TDRCMST.PROC_SEQ);
        TDRCMST.BV_TYP = TDCACCRU.BV_TYP;
        TDRCMST.CCY = TDCACCRU.CCY;
        TDRCMST.CI_TYP = WS_CI_TYP;
        TDRCMST.CUS_PSW_FLG = 'N';
        TDRCMST.AC_PSW_FLG = 'N';
        if (!WS_AC_TYPE.equalsIgnoreCase("035") 
            && !WS_AC_TYPE.equalsIgnoreCase("036")) {
            TDRCMST.DRAW_MTH = TDCACCRU.DRAW_MTH;
        }
        TDRCMST.ERR_NUM = 0;
        TDRCMST.OPT_DT = 0;
        TDRCMST.STSW = "ZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROS";
        TDRCMST.CROS_DR_FLG = TDCACCRU.CROS_DR_FLG;
        TDRCMST.CROS_CR_FLG = TDCACCRU.CROS_CR_FLG;
        TDRCMST.OWNER_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRCMST.OWNER_BRDP = BPCPORUP.DATA_INFO.BBR;
        TDRCMST.OWNER_BK = BPCPORUP.DATA_INFO.BR;
        TDRCMST.CHE_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRCMST.REF_TYP = " ";
        TDRCMST.INT_METH = '2';
        TDRCMST.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRCMST.OPEN_TLR = SCCGWA.COMM_AREA.TL_ID;
        TDRCMST.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRCMST.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        TDRCMST.FRG_IND = WS_FRG_IND;
        TDRCMST.OIC_NO = TDCACCRU.OIC_NO;
        TDRCMST.RESP_CD = TDCACCRU.RESP_CD;
        TDRCMST.SUB_DP = TDCACCRU.SUB_DP;
        TDRCMST.RMK_100 = "TDZACCRU";
        TDRCMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRCMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDRCMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_CMST();
    }
    public void B500_REWRT_TDTCMST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRCMST);
        TDRCMST.KEY.AC_NO = TDCACCRU.AC_NO;
        T000_READUP_CMST();
        TDRCMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRCMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDRCMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRCMST.PROC_SEQ += 1;
        T000_REWRITE_CMST();
    }
    public void B500_WRT_TDTSMST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSMST);
        WS_SEQ2 += 1;
        CEP.TRC(SCCGWA, WS_ACO_AC);
        CEP.TRC(SCCGWA, TDCACCRU.AC_NO);
        TDRSMST.STSW = "ZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROS";
        TDRSMST.AC_NO = TDCACCRU.AC_NO;
        TDRSMST.KEY.ACO_AC = WS_ACO_AC;
        TDRSMST.ACO_STS = '0';
        if ((TDCACCRU.MAIN_FLG != 'N' 
            && TDCACCRU.BV_TYP == '7') 
            || (TDCACCRU.MAIN_FLG != 'N' 
            && TDCACCRU.BV_TYP == '8') 
            || (TDCACCRU.MAIN_FLG != 'N' 
            && TDCACCRU.BV_TYP == '3') 
            || TDCACCRU.BV_TYP == '4') {
            TDRSMST.BV_TYP = TDCACCRU.BV_TYP;
        }
        if (TDCACCRU.CALR_STD.trim().length() == 0) {
            IBS.init(SCCGWA, BPCQCCY);
            BPCQCCY.DATA.CCY = TDCACCRU.CCY;
            S000_CALL_BPZQCCY();
            CEP.TRC(SCCGWA, BPCQCCY.DATA.STD_DAYS);
            if (BPCQCCY.DATA.STD_DAYS == 360) {
                TDCACCRU.CALR_STD = "01";
            } else if (BPCQCCY.DATA.STD_DAYS == 365) {
                TDCACCRU.CALR_STD = "02";
            } else if (BPCQCCY.DATA.STD_DAYS == 366) {
                TDCACCRU.CALR_STD = "03";
            }
        }
        TDRSMST.HOLD_NUM = 0;
        TDRSMST.PROD_CD = TDCACCRU.PROD_CD;
        TDRSMST.PRDAC_CD = WS_AC_TYPE;
        if (TDCACCRU.OPT == '3') {
            TDRSMST.PRDAC_CD = "033";
        }
        TDRSMST.ACTI_NO = TDCACCRU.ACTI_NO;
        TDRSMST.CCY = TDCACCRU.CCY;
        TDRSMST.CCY_TYP = TDCACCRU.CCY_TYP;
        TDRSMST.TERM = TDCACCRU.TERM;
        TDRSMST.CALR_STD = TDCACCRU.CALR_STD;
        TDRSMST.VAL_DATE = TDCACCRU.VAL_DT;
        CEP.TRC(SCCGWA, TDCACCRU.EXP_DT);
        if ((!TDRSMST.PRDAC_CD.equalsIgnoreCase("036")) 
            && !(TDRSMST.PRDAC_CD.equalsIgnoreCase("022") 
            && TDCACCRU.INSTR_MTH != '3')) {
            TDRSMST.EXP_DATE = TDCACCRU.EXP_DT;
        }
        CEP.TRC(SCCGWA, TDRSMST.EXP_DATE);
        TDRSMST.INSTR_MTH = TDCACCRU.INSTR_MTH;
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        JIBS_tmp_str[0] = "" + TDCACCRU.SHOW;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        TDRSMST.STSW = TDRSMST.STSW.substring(0, 51 - 1) + JIBS_tmp_str[0] + TDRSMST.STSW.substring(51 + 1 - 1);
        if (TDCACCRU.SHOW == ' ') {
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            TDRSMST.STSW = TDRSMST.STSW.substring(0, 51 - 1) + "0" + TDRSMST.STSW.substring(51 + 1 - 1);
        }
        if (SCCGWA.COMM_AREA.AGENT_FLG == 'Y') {
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            TDRSMST.STSW = TDRSMST.STSW.substring(0, 55 - 1) + "1" + TDRSMST.STSW.substring(55 + 1 - 1);
        }
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        JIBS_tmp_str[0] = "" + WS_CI_TYP;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        TDRSMST.STSW = TDRSMST.STSW.substring(0, 65 - 1) + JIBS_tmp_str[0] + TDRSMST.STSW.substring(65 + 1 - 1);
        CEP.TRC(SCCGWA, TDCACCRU.FST_FLG);
        CEP.TRC(SCCGWA, WS_SEQ2);
        CEP.TRC(SCCGWA, WS_AC_TYPE);
        if ((WS_AC_TYPE.equalsIgnoreCase("035") 
            || WS_AC_TYPE.equalsIgnoreCase("036"))) {
            if (TDCACCRU.FST_FLG == '1' 
                && WS_SEQ2 > 1) {
                CEP.TRC(SCCGWA, "FIRSTOPEN");
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                TDRSMST.STSW = TDRSMST.STSW.substring(0, 52 - 1) + "1" + TDRSMST.STSW.substring(52 + 1 - 1);
                WS_WRT_OCAC = 'Y';
                WS_GROP = "FRST";
                B270_WRT_FHIS();
                B730_WRT_BPTOCAC();
            } else {
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                TDRSMST.STSW = TDRSMST.STSW.substring(0, 51 - 1) + "1" + TDRSMST.STSW.substring(51 + 1 - 1);
                WS_GROP = "NFST";
                if (TDCACCRU.FST_FLG != '1') {
                    WS_HIS_FLG = 'Y';
                }
            }
        }
        CEP.TRC(SCCGWA, WS_GROP);
        CEP.TRC(SCCGWA, WS_HIS_FLG);
        CEP.TRC(SCCGWA, TDCACCRU.RULE);
        if (TDCACCRU.RULE != ' ') {
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            JIBS_tmp_str[0] = "" + TDCACCRU.RULE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            TDRSMST.STSW = TDRSMST.STSW.substring(0, 31 - 1) + JIBS_tmp_str[0] + TDRSMST.STSW.substring(31 + 1 - 1);
        } else {
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            JIBS_tmp_str[0] = "" + TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].INT_RUL;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            TDRSMST.STSW = TDRSMST.STSW.substring(0, 31 - 1) + JIBS_tmp_str[0] + TDRSMST.STSW.substring(31 + 1 - 1);
        }
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        JIBS_tmp_str[0] = "" + TDCPIOD.OTH_PRM.CCY_INF[WS_J-1].FLOW_FLG;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        TDRSMST.STSW = TDRSMST.STSW.substring(0, 32 - 1) + JIBS_tmp_str[0] + TDRSMST.STSW.substring(32 + 1 - 1);
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        JIBS_tmp_str[0] = "" + TDCACCRU.XC_UP_FLG;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        TDRSMST.STSW = TDRSMST.STSW.substring(0, 33 - 1) + JIBS_tmp_str[0] + TDRSMST.STSW.substring(33 + 1 - 1);
        if (TDCACCRU.XC_UP_FLG == ' ') {
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            TDRSMST.STSW = TDRSMST.STSW.substring(0, 33 - 1) + "0" + TDRSMST.STSW.substring(33 + 1 - 1);
        }
        if (TDCPIOD.EXP_PRM.ERLY_TYP == '5' 
            && TDCACCRU.PRV_RAT > 0) {
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            TDRSMST.STSW = TDRSMST.STSW.substring(0, 36 - 1) + "1" + TDRSMST.STSW.substring(36 + 1 - 1);
        }
        if (TDCPIOD.EXP_PRM.ERLY_TYP == '5' 
            && TDCACCRU.PRV_RAT <= 0) {
            IBS.init(SCCGWA, CICCUST);
            CICCUST.FUNC = 'A';
            CICCUST.DATA.AGR_NO = TDRSMST.AC_NO;
            CEP.TRC(SCCGWA, TDRSMST.AC_NO);
            S000_CALL_CIZCUST();
            CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_TYP);
            if (CICCUST.O_DATA.O_CI_TYP != '3') {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.PRVRAT_MUSTIN);
            }
        }
        if (TDCPIOD.EXP_PRM.LATE_TYP == '5' 
            && TDCACCRU.OVE_RAT > 0) {
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            TDRSMST.STSW = TDRSMST.STSW.substring(0, 37 - 1) + "1" + TDRSMST.STSW.substring(37 + 1 - 1);
        }
        if (TDCPIOD.EXP_PRM.RES_TYP == '5' 
            && TDCACCRU.DUE_RAT > 0) {
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            TDRSMST.STSW = TDRSMST.STSW.substring(0, 38 - 1) + "1" + TDRSMST.STSW.substring(38 + 1 - 1);
        }
        CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.PLAN_FLG);
        CEP.TRC(SCCGWA, TDCACCRU.PERD_TYP);
        CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.INT_PRD2);
        CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.INT_PRD3);
        if (TDCPIOD.OTH_PRM.PLAN_FLG == '1') {
            CEP.TRC(SCCGWA, "1");
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            TDRSMST.STSW = TDRSMST.STSW.substring(0, 21 - 1) + "1" + TDRSMST.STSW.substring(21 + 1 - 1);
        } else {
            CEP.TRC(SCCGWA, "11");
            if (TDCACCRU.PERD_TYP == '1' 
                || TDCACCRU.PERD_TYP == '2') {
                CEP.TRC(SCCGWA, "2");
                if (TDCPIOD.OTH_PRM.INT_PRD2 == '1') {
                    CEP.TRC(SCCGWA, "3");
                    if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                    JIBS_tmp_int = TDRSMST.STSW.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                    TDRSMST.STSW = TDRSMST.STSW.substring(0, 22 - 1) + "1" + TDRSMST.STSW.substring(22 + 1 - 1);
                }
                if (TDCPIOD.OTH_PRM.INT_PRD3 == '2') {
                    CEP.TRC(SCCGWA, "4");
                    if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                    JIBS_tmp_int = TDRSMST.STSW.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                    TDRSMST.STSW = TDRSMST.STSW.substring(0, 23 - 1) + "1" + TDRSMST.STSW.substring(23 + 1 - 1);
                }
            }
        }
        CEP.TRC(SCCGWA, TDCACCRU.CT_FLG);
        CEP.TRC(SCCGWA, WS_OP_APP);
        if (TDCACCRU.CT_FLG == '2' 
            && !WS_OP_APP.equalsIgnoreCase("DC")) {
            IBS.init(SCCGWA, DDCSQIFA);
            DDCSQIFA.INPUT_DATA.AC_NO = TDCACCRU.OPP_AC_CNO;
            DDCSQIFA.INPUT_DATA.CCY = TDCACCRU.CCY;
            DDCSQIFA.INPUT_DATA.CCY_TYPE = TDCACCRU.CCY_TYP;
            DDCSQIFA.INPUT_DATA.AC_OLD = TDCACCRU.OPP_AC_CNO;
            IBS.init(SCCGWA, CICQACRL);
            CICQACRL.DATA.AC_NO = TDCACCRU.OPP_AC_CNO;
            S000_CALL_CIZQACRL();
            if (CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("02") 
                || CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("03") 
                || CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("04") 
                || CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("09") 
                || CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("13")) {
                DDCSQIFA.INPUT_DATA.AC_NO = CICQACRL.O_DATA.O_REL_AC_NO;
                DDCSQIFA.INPUT_DATA.AC_OLD = CICQACRL.O_DATA.O_REL_AC_NO;
            }
            CEP.TRC(SCCGWA, DDCSQIFA.OUTPUT_DATA.STS_WORD);
            if (DDCSQIFA.OUTPUT_DATA.STS_WORD == null) DDCSQIFA.OUTPUT_DATA.STS_WORD = "";
            JIBS_tmp_int = DDCSQIFA.OUTPUT_DATA.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDCSQIFA.OUTPUT_DATA.STS_WORD += " ";
            if (DDCSQIFA.OUTPUT_DATA.STS_WORD.substring(64 - 1, 64 + 1 - 1).equalsIgnoreCase("1")) {
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                TDRSMST.STSW = TDRSMST.STSW.substring(0, 64 - 1) + "1" + TDRSMST.STSW.substring(64 + 1 - 1);
            }
            IBS.init(SCCGWA, DDCIMMST);
            DDCIMMST.TX_TYPE = 'I';
            DDCIMMST.DATA.KEY.AC_NO = TDCACCRU.OPP_AC_CNO;
            CEP.TRC(SCCGWA, TDCACCRU.OPP_AC_CNO);
            S000_CALL_DDZIMMST();
            if (DDCIMMST.DATA.AC_STS_WORD == null) DDCIMMST.DATA.AC_STS_WORD = "";
            JIBS_tmp_int = DDCIMMST.DATA.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMMST.DATA.AC_STS_WORD += " ";
            if (DDCIMMST.DATA.AC_STS_WORD.substring(23 - 1, 23 + 1 - 1).equalsIgnoreCase("1")) {
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                TDRSMST.STSW = TDRSMST.STSW.substring(0, 64 - 1) + "1" + TDRSMST.STSW.substring(64 + 1 - 1);
            }
        }
        TDRSMST.BAL = TDCACCRU.TXN_AMT;
        TDRSMST.FBAL = TDCACCRU.TXN_AMT;
        TDRSMST.PBAL = TDCACCRU.TXN_AMT;
        if (WS_AC_TYPE.equalsIgnoreCase("035") 
            || WS_AC_TYPE.equalsIgnoreCase("036")) {
            TDRSMST.BAL = WS_GROP_AMT;
            TDRSMST.PBAL = WS_GROP_AMT;
        }
        TDRSMST.LBAL = TDRSMST.BAL;
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        JIBS_tmp_str[0] = "" + TDCPIOD.EXP_PRM.ERLY_TYP;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        TDRSMST.STSW = TDRSMST.STSW.substring(0, 34 - 1) + JIBS_tmp_str[0] + TDRSMST.STSW.substring(34 + 1 - 1);
        TDRSMST.CCY_TYP = TDCACCRU.CCY_TYP;
        TDRSMST.LBAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRSMST.OPEN_PAY_MTH = TDCACCRU.CT_FLG;
        TDRSMST.OPEN_DR_AC = TDCACCRU.OPP_AC_CNO;
        if (WS_AC_TYPE.equalsIgnoreCase("021")) {
            TDRSMST.OPEN_DR_AC = TDCACCRU.INT_AC;
        }
        TDRSMST.OPEN_DR_AC_CCY = TDCACCRU.CCY;
        TDRSMST.OPEN_DR_AC_TYP = TDCACCRU.CCY_TYP;
        TDRSMST.PVAL_DATE = TDCACCRU.VAL_DT;
        if (TDCACCRU.FRAT_DT != 0) {
            TDRSMST.PVAL_DATE = TDCACCRU.FRAT_DT;
        }
        TDRSMST.PROC_NUM = 1;
        if ((REDEFINES56.WS_TERM_TYP == 'Y' 
            || REDEFINES56.WS_TERM_TYP == 'M') 
            || (REDEFINES56.WS_TERM_TYP == 'D') 
            || (DPCPARMP.AC_TYPE.equalsIgnoreCase("022") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("023") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("036")) 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("028") 
            || (TDCACCRU.OPT == '2' 
            && TDCACCRU.INT_RAT != 0)) {
            if (!(TDCACCRU.INT_SEL == '4' 
                && TDCACCRU.INT_RAT == 0)) {
                B220_CAL_EXP_INT();
            } else {
                TDCCEINT.INT = 0;
            }
        }
        CEP.TRC(SCCGWA, "JF CEINT-INT ");
        CEP.TRC(SCCGWA, TDCCEINT.INT);
        if (WS_AC_TYPE.equalsIgnoreCase("020") 
            && TDCCEINT.RACD_FLG != 'Y') {
            TDRSMST.ACTI_NO = " ";
            TDCACCRU.ACTI_NO = " ";
        } else {
            if (WS_AC_TYPE.equalsIgnoreCase("020") 
                && TDCCEINT.RACD_FLG == 'Y') {
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                TDRSMST.STSW = TDRSMST.STSW.substring(0, 66 - 1) + "1" + TDRSMST.STSW.substring(66 + 1 - 1);
            }
        }
        if (TDCCEINT.XZ_FLG != '3') {
            TDCCEINT.AC_RUL_CD = " ";
        }
        TDRSMST.EXP_INT = TDCCEINT.INT;
        TDRSMST.FC_CD = TDCACCRU.FC_CD;
        TDRSMST.FC_NO = TDCACCRU.FC_NO;
        TDRSMST.OIC_NO = TDCACCRU.OIC_NO;
        TDRSMST.RES_CD = TDCACCRU.RES_CD;
        TDRSMST.SUB_DP = TDCACCRU.SUB_DP;
        TDRSMST.MON_TYP = TDCACCRU.MON_TYP;
        TDRSMST.AC_TYP = TDCACCRU.ACO_TYP;
        if (TDCACCRU.CT_FLG == '0') {
            TDRSMST.CASH_BAL = TDCACCRU.TXN_AMT;
        }
        if (TDCACCRU.CT_FLG == '2') {
            TDRSMST.TRDEP_BAL = TDCACCRU.TXN_AMT;
        }
        TDRSMST.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        TDRSMST.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        TDRSMST.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRSMST.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRSMST.OWNER_BR = WS_OWNER_BR;
        TDRSMST.OWNER_BRDP = WS_OWNER_BRDP;
        CEP.TRC(SCCGWA, TDCACCRU.ACBR_FLG);
        CEP.TRC(SCCGWA, WS_CI_TYP);
        if (TDCACCRU.ACBR_FLG == '1' 
            && WS_CI_TYP == '3') {
            TDRSMST.CHE_BR = K_HEAD_BR;
        } else {
            TDRSMST.CHE_BR = WS_CHE_BR;
        }
        TDRSMST.OWNER_BK = WS_OWNER_BK;
        CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.BBR);
        CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.BR);
        TDRSMST.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRSMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDRSMST.CALR_STD = TDCACCRU.CALR_STD;
        if (TDRSMST.EXP_DATE < TDRSMST.VAL_DATE 
            && TDRSMST.EXP_DATE > 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_EXPDT_LT_VALDATE);
        }
        if (TDRSMST.CCY_TYP == ' ') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CCY_TYP_MUST_INUT);
        }
        R000_7_X_24H_BAL_UPD();
        CEP.TRC(SCCGWA, WS_OWNER_BK);
        CEP.TRC(SCCGWA, WS_OWNER_BR);
        CEP.TRC(SCCGWA, WS_OWNER_BRDP);
        CEP.TRC(SCCGWA, WS_CHE_BR);
        CEP.TRC(SCCGWA, TDRSMST.CHE_BR);
        CEP.TRC(SCCGWA, "ZS SMST");
        CEP.TRC(SCCGWA, WS_CI_TYP);
        CEP.TRC(SCCGWA, TDRSMST.PRDAC_CD);
        TDRSMST.RECOM_TYP = TDCACCRU.RECOM_TYP;
        TDRSMST.RECOM_ID_TYPE = TDCACCRU.RECOM_ID_TYPE;
        TDRSMST.RECOM_ID_NO = TDCACCRU.RECOM_ID_NO;
        CEP.TRC(SCCGWA, TDCACCRU.NOTI_FLG);
        CEP.TRC(SCCGWA, TDRSMST.PRDAC_CD);
        if (TDCACCRU.NOTI_FLG == 'Y') {
            if (TDRSMST.PRDAC_CD.equalsIgnoreCase("022")) {
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                TDRSMST.STSW = TDRSMST.STSW.substring(0, 25 - 1) + "1" + TDRSMST.STSW.substring(25 + 1 - 1);
            } else {
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                TDRSMST.STSW = TDRSMST.STSW.substring(0, 60 - 1) + "1" + TDRSMST.STSW.substring(60 + 1 - 1);
            }
        }
        CEP.TRC(SCCGWA, TDRSMST.EXP_DATE);
        CEP.TRC(SCCGWA, "SMST-NCB");
        if (TDRSMST.KEY.ACO_AC.trim().length() > 0) {
            T000_WRITE_SMST();
        }
    }
    public void B500_WRT_SMST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSMST);
    }
    public void B500_UPD_HMST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRHMST);
    }
    public void B500_WRT_IREV() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRIREV);
        TDRIREV.KEY.ACO_AC = WS_ACO_AC;
        TDRIREV.ACTI_NO = TDCACCRU.ACTI_NO;
        CEP.TRC(SCCGWA, TDRIREV.ACTI_NO);
        if (TDCACCRU.ACTI_NO.trim().length() > 0) {
            IBS.init(SCCGWA, TDROTHE);
            TDROTHE.KEY.ACTI_NO = TDCACCRU.ACTI_NO;
            T00_READ_TDTOTHE();
            CEP.TRC(SCCGWA, TDROTHE.RUL_CD);
            TDRIREV.INT_RUL_CD = TDROTHE.RUL_CD;
        }
        if (TDCACCRU.INT_RUL_CD.trim().length() > 0) {
            TDRIREV.INT_RUL_CD = TDCACCRU.INT_RUL_CD;
        }
        TDRIREV.KEY.STR_DATE = TDRSMST.VAL_DATE;
        CEP.TRC(SCCGWA, TDCACCRU.EXP_DT);
        CEP.TRC(SCCGWA, TDRSMST.EXP_DATE);
        if (TDRSMST.EXP_DATE != 0 
            && (!DPCPARMP.AC_TYPE.equalsIgnoreCase("022") 
            && !DPCPARMP.AC_TYPE.equalsIgnoreCase("023") 
            && !DPCPARMP.AC_TYPE.equalsIgnoreCase("036"))) {
            TDRIREV.END_DATE = TDRSMST.EXP_DATE;
        } else {
            TDRIREV.END_DATE = 99991231;
        }
        CEP.TRC(SCCGWA, TDCACCRU.INT_SEL);
        if (TDCACCRU.INT_SEL == ' ') {
            TDRIREV.RATE_SEL = '0';
        } else {
            TDRIREV.RATE_SEL = TDCACCRU.INT_SEL;
        }
        CEP.TRC(SCCGWA, WS_SPE_FLG);
        if (TDCPIOD.EXP_PRM.NORM_TYP == '1' 
            && TDCACCRU.TENOR.compareTo(SPACE) > 0) {
            TDRIREV.TENOR = TDCACCRU.TENOR;
        } else {
            TDRIREV.TENOR = TDRSMST.TERM;
        }
        IBS.init(SCCGWA, BPCCINTI);
        if (!DPCPARMP.AC_TYPE.equalsIgnoreCase("028") 
            && (!DPCPARMP.AC_TYPE.equalsIgnoreCase("029") 
            && !DPCPARMP.AC_TYPE.equalsIgnoreCase("030")) 
            && WS_SPE_FLG != 'D' 
            && WS_SPE_FLG != 'Z' 
            && WS_SPE_FLG != 'J' 
            && WS_OCAC_FLAG != 'L' 
            && !(TDCACCRU.INT_SEL == '4' 
            || (TDCACCRU.INT_SEL == '0' 
            && TDCPIOD.OTH_PRM.CPRA_TYP == '4')) 
            && !(TDCACCRU.OPT == '2' 
            && (TDCPIOD.OTH_PRM.DOCU_FLG == '0' 
            || TDCPIOD.OTH_PRM.DOCU_FLG == 'Y'))) {
            BPCCINTI.FUNC = 'I';
            CEP.TRC(SCCGWA, "IREV-PRD-RAT1");
            BPCCINTI.BASE_INFO.BASE_TYP = WS_IRAT_CD;
            if ((DPCPARMP.AC_TYPE.equalsIgnoreCase("025") 
                || DPCPARMP.AC_TYPE.equalsIgnoreCase("026")) 
                && TDCACCRU.TERM.equalsIgnoreCase("Y006")) {
                BPCCINTI.BASE_INFO.TENOR = "Y005";
            } else {
                BPCCINTI.BASE_INFO.TENOR = TDRSMST.TERM;
            }
            if (DPCPARMP.AC_TYPE.equalsIgnoreCase("026") 
                && WS_PR_IRAT_CD.trim().length() > 0) {
                BPCCINTI.BASE_INFO.BASE_TYP = WS_PR_IRAT_CD;
            }
            CEP.TRC(SCCGWA, TDCPRDP.TXN_PRM.OTH_FLG);
            if (TDCPRDP.TXN_PRM.OTH_FLG.equalsIgnoreCase("06")) {
                BPCCINTI.BASE_INFO.TENOR = "S000";
            }
            CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.TENOR);
            BPCCINTI.BASE_INFO.CCY = TDRSMST.CCY;
            BPCCINTI.BASE_INFO.DT = SCCGWA.COMM_AREA.AC_DATE;
            BPCCINTI.BASE_INFO.BR = TDRSMST.OWNER_BR;
            CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.CCY);
            CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.TENOR);
            S000_CALL_BPZCINTI();
            CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.OWN_RATE);
            CEP.TRC(SCCGWA, "JF0724-1");
            TDRIREV.PRD_RATE = BPCCINTI.BASE_INFO.OWN_RATE;
        } else {
            CEP.TRC(SCCGWA, "IREV-PRD-RATE2");
            CEP.TRC(SCCGWA, WS_SPE_FLG);
            if (WS_SPE_FLG == 'D' 
                || WS_SPE_FLG == 'Z' 
                || TDCACCRU.INT_SEL == '4' 
                || WS_OCAC_FLAG == 'L') {
                CEP.TRC(SCCGWA, TDCACCRU.INT_RAT);
                CEP.TRC(SCCGWA, "JF0724-2");
                TDRIREV.PRD_RATE = TDCACCRU.INT_RAT;
            } else {
                CEP.TRC(SCCGWA, "JF0724-3");
                TDRIREV.PRD_RATE = 0;
            }
        }
        CEP.TRC(SCCGWA, WS_ERLY_TYP);
        CEP.TRC(SCCGWA, TDRIREV.PRD_RATE);
        if (DPCPARMP.AC_TYPE.equalsIgnoreCase("028")) {
            TDRIREV.ERLY_TYP = WS_ERLY_TYP;
        }
        if (DPCPARMP.AC_TYPE.equalsIgnoreCase("028") 
            && TDCACCRU.RATE_TYP.trim().length() > 0) {
            TDRIREV.RATE_TYPE = TDCACCRU.RATE_TYP;
        } else {
            TDRIREV.RATE_TYPE = WS_IRAT_CD;
        }
        if (DPCPARMP.AC_TYPE.equalsIgnoreCase("026") 
            && WS_PR_IRAT_CD.trim().length() > 0) {
            TDRIREV.RATE_TYPE = WS_PR_IRAT_CD;
        }
        TDRIREV.SPRD_PNT = TDCACCRU.SPRD_PNT;
        TDRIREV.SPRD_PCT = TDCACCRU.SPRD_PCT;
        if (TDRIREV.INT_RUL_CD.trim().length() == 0 
            && WS_RUL_CDC.trim().length() > 0) {
            TDRIREV.INT_RUL_CD = WS_RUL_CDC;
        }
        CEP.TRC(SCCGWA, TDCACCRU.INT_RAT);
        TDRIREV.CON_RATE = TDCACCRU.INT_RAT;
        TDRIREV.INT_SEL = TDRIREV.RATE_SEL;
        CEP.TRC(SCCGWA, TDCACCRU.INT_RAT);
        TDRIREV.FEE_RUL_CD = TDCACCRU.FEE_RUL_CD;
        TDRIREV.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        TDRIREV.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRIREV.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRIREV.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRIREV.BAL = TDCCEINT.JU_RACD.RACD_BAL;
        TDRIREV.CCY = TDCCEINT.CCY;
        TDRIREV.TERM = TDCCEINT.JU_RACD.RACD_TERM;
        if (TDCCEINT.JU_RACD.RACD_SVR_LVL.trim().length() == 0) TDRIREV.LVL = 0;
        else TDRIREV.LVL = Short.parseShort(TDCCEINT.JU_RACD.RACD_SVR_LVL);
        TDRIREV.GRPS_NO = TDCCEINT.JU_RACD.RACD_GRPS_NO;
        TDRIREV.BR = TDCCEINT.JU_RACD.RACD_BR;
        TDRIREV.CHNL_NO = TDCCEINT.JU_RACD.RACD_CHNL_NO;
        TDRIREV.OWNER_BR = TDRSMST.OWNER_BR;
        TDRIREV.OWNER_BRDP = TDRSMST.OWNER_BRDP;
        TDRIREV.OWNER_BK = TDRSMST.OWNER_BK;
        CEP.TRC(SCCGWA, WS_ACO_AC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCCEINT.INT_STSW);
        CEP.TRC(SCCGWA, JIBS_tmp_str[0].substring(4 - 1, 4 + 1 - 1));
        TDRIREV.OTH_FIL = TDCCEINT.JU_RACD.OTH_FIL;
        CEP.TRC(SCCGWA, WS_DOCU_NO);
        CEP.TRC(SCCGWA, TDCCEINT.XZ_FLG);
        TDRIREV.DOCU_NO = WS_DOCU_NO;
        if (TDRIREV.RATE_SEL == '0') {
            TDRIREV.RATE_SEL = TDCCEINT.XZ_FLG;
            if (TDCCEINT.XZ_FLG == ' ') {
                TDRIREV.RATE_SEL = '0';
            }
        }
        CEP.TRC(SCCGWA, TDCCEINT.AC_RUL_CD);
        if (TDCCEINT.AC_RUL_CD.trim().length() > 0) {
            TDRIREV.INT_RUL_CD = TDCCEINT.AC_RUL_CD;
        }
        T000_WRITE_IREV();
    }
    public void R000_GET_OWNRAT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCCINTI);
        BPCCINTI.FUNC = 'I';
        CEP.TRC(SCCGWA, WS_IRAT_CD);
        CEP.TRC(SCCGWA, TDRIREV.RATE_TYPE);
        BPCCINTI.BASE_INFO.TENOR = TDRSMST.TERM;
        if (DPCPARMP.AC_TYPE.equalsIgnoreCase("028") 
            && TDCACCRU.RATE_TYP.trim().length() > 0) {
            BPCCINTI.BASE_INFO.BASE_TYP = TDCACCRU.RATE_TYP;
        } else {
            BPCCINTI.BASE_INFO.BASE_TYP = WS_IRAT_CD;
        }
        CEP.TRC(SCCGWA, TDCPRDP.TXN_PRM.OTH_FLG);
        if (TDCPRDP.TXN_PRM.OTH_FLG.equalsIgnoreCase("06")) {
            BPCCINTI.BASE_INFO.TENOR = "S000";
        }
        CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.TENOR);
        BPCCINTI.BASE_INFO.CCY = TDRSMST.CCY;
        BPCCINTI.BASE_INFO.DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCCINTI.BASE_INFO.BR = TDRSMST.OWNER_BR;
        CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.CCY);
        CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.TENOR);
        S000_CALL_BPZCINTI();
    }
    public void B500_WRT_IRH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRIRH);
    }
    public void B500_REWRT_SMST_KH() throws IOException,SQLException,Exception {
        TDRSMST.ACO_STS = '2';
        TDRSMST.PROC_NUM = (short) (TDRSMST.PROC_NUM + 1);
        if (WS_SPE_FLG == 'D' 
            || WS_SPE_FLG == 'Z') {
            TDRSMST.BUD_INT = 0;
        }
        TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_SMST();
    }
    public void B500_REWRT_SMST_GK() throws IOException,SQLException,Exception {
        TDRSMST.BAL = TDRSMST.BAL + TDCACCRU.TXN_AMT;
        TDRSMST.PROC_NUM = (short) (TDRSMST.PROC_NUM + 1);
        CEP.TRC(SCCGWA, TDRSMST.BAL);
        CEP.TRC(SCCGWA, WS_MAX_AMTC);
        if (TDRSMST.BAL > WS_MAX_AMTC 
            && WS_MAX_AMTC != 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AMT_SO_LARGE;
            S000_ERR_MSG_PROC();
        }
        R000_7_X_24H_BAL_UPD();
        TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_SMST();
    }
    public void B500_REWRT_SMST_GK_CANCEL() throws IOException,SQLException,Exception {
        if (TDCACCRU.TXN_AMT > TDRSMST.BAL - TDRSMST.HBAL - TDRSMST.WBAL) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AMT_HOLD;
            S000_ERR_MSG_PROC();
        }
        TDRSMST.BAL = TDRSMST.BAL - TDCACCRU.TXN_AMT;
        TDRSMST.PROC_NUM = (short) (TDRSMST.PROC_NUM + 1);
        R000_7_X_24H_BAL_UPD();
        if (TDRSMST.BAL != WS_BAL) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_NOT_LAST_PART;
            S000_ERR_MSG_PROC();
        }
        TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_SMST();
    }
    public void B510_WRT_INST_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRINST);
        TDRINST.KEY.ACO_AC = WS_ACO_AC;
        TDRINST.PROD_CD = TDCACCRU.PROD_CD;
        if ((TDCACCRU.INSTR_MTH == '1' 
            || TDCACCRU.INSTR_MTH == '6') 
            && TDCACCRU.STL_AC.trim().length() > 0) {
            WS_AC = TDCACCRU.STL_AC;
            R000_GET_CARD_FLG();
            TDRINST.STL_MTH = WS_STL_MTH;
            if (TDCACCRU.INSTR_MTH == '1') {
                TDRINST.STL_AC = TDCACCRU.STL_AC;
            } else {
                if (TDCACCRU.INSTR_MTH == '6') {
                    TDRINST.STL_INT_AC = TDCACCRU.STL_AC;
                }
            }
            CEP.TRC(SCCGWA, TDCACCRU.OPP_AC_CNO);
            CEP.TRC(SCCGWA, TDCACCRU.STL_AC);
            if (WS_AC_TYPE.equalsIgnoreCase("037") 
                && !TDCACCRU.OPP_AC_CNO.equalsIgnoreCase(TDCACCRU.STL_AC)) {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_ERR_RETURN;
                S000_ERR_MSG_PROC();
            }
        }
        CEP.TRC(SCCGWA, TDCACCRU.INSTR_MTH);
        CEP.TRC(SCCGWA, TDCACCRU.INT_AC);
        CEP.TRC(SCCGWA, TDCACCRU.INOUT_FLG);
        if ((TDCACCRU.INSTR_MTH == '4' 
            || TDCACCRU.INT_AC.trim().length() > 0) 
            && !WS_AC_TYPE.equalsIgnoreCase("021") 
            && TDCACCRU.INOUT_FLG != '2') {
            WS_AC = TDCACCRU.INT_AC;
            R000_GET_CARD_FLG();
            TDRINST.STL_INT_MTH = WS_STL_MTH;
            TDRINST.STL_INT_AC = TDCACCRU.INT_AC;
        }
        TDRINST.INSTR_AMT = TDCACCRU.CD_AMT;
        TDRINST.SER_TIME = TDCACCRU.ZC_NUM;
        if (TDCACCRU.INSTR_TERM.trim().length() == 0 
            && TDCACCRU.INSTR_MTH != '1') {
            TDRINST.INSTR_TERM = TDCACCRU.TERM;
        } else {
            if (TDCACCRU.INSTR_TERM.trim().length() > 0 
                && (TDCACCRU.INSTR_MTH == '3' 
                || TDCACCRU.INSTR_MTH == '4' 
                || TDCACCRU.INSTR_MTH == '5' 
                || TDCACCRU.INSTR_MTH == '6')) {
                B510_CHECK_INSTR_TERM();
            }
            TDRINST.INSTR_TERM = TDCACCRU.INSTR_TERM;
        }
        TDRINST.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRINST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRINST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        if (TDCACCRU.INSTR_MTH == '6' 
            && TDRINST.STL_INT_AC.trim().length() == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_STL_INT_AC;
            S000_ERR_MSG_PROC();
        }
        if (TDCACCRU.INSTR_MTH == '1' 
            && TDRINST.STL_AC.trim().length() == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_STL_AC_ERR;
            S000_ERR_MSG_PROC();
        }
        T000_WRITE_INST();
    }
    public void B520_WRT_CDI() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCACCRU.PERD_TYP);
        CEP.TRC(SCCGWA, TDCACCRU.CD_PERD);
        if (TDCACCRU.PERD_TYP == ' ') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_INT_PERD_ERROR;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, WS_MAX_AMTC);
        IBS.init(SCCGWA, TDRCDI);
        TDRCDI.KEY.ACO_AC = TDCACCRU.ACO_AC;
        WS_CD_PERD = TDCACCRU.CD_PERD;
        WS_PERD = "" + WS_CD_PERD;
        JIBS_tmp_int = WS_PERD.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) WS_PERD = "0" + WS_PERD;
        CEP.TRC(SCCGWA, "jf111111111");
        TDRCDI.PERD_TYP = TDCACCRU.PERD_TYP;
        CEP.TRC(SCCGWA, "jf1111111222");
        if (DPCPARMP.AC_TYPE.equalsIgnoreCase("028") 
            && TDCACCRU.PERD_TYP == '3') {
            TDRCDI.CD_AUTO_FLG = 'Y';
        } else {
            TDRCDI.CD_AUTO_FLG = TDCACCRU.CD_AUTO_FLG;
        }
        if (REDEFINES56.WS_TERM_TYP == 'Y') {
            WS_TERM_MTHS2 = (short) (REDEFINES56.WS_TERM_MTHS * 12);
        }
        CEP.TRC(SCCGWA, "jf11111112223");
        if (REDEFINES56.WS_TERM_TYP == 'M') {
            WS_TERM_MTHS2 = REDEFINES56.WS_TERM_MTHS;
        }
        CEP.TRC(SCCGWA, "jf11111112221");
        CEP.TRC(SCCGWA, TDCACCRU.VAL_DT);
        CEP.TRC(SCCGWA, TDCACCRU.EXP_DT);
        if (TDRCDI.PERD_TYP == '1') {
            WS_VAL_DATE = TDCACCRU.VAL_DT;
            IBS.CPY2CLS(SCCGWA, WS_VAL_DATE+"", REDEFINES128);
            WS_EXP_DATE = TDCACCRU.EXP_DT;
            IBS.CPY2CLS(SCCGWA, WS_EXP_DATE+"", REDEFINES133);
            CEP.TRC(SCCGWA, REDEFINES133.WS_EXP_DATE_YY);
            CEP.TRC(SCCGWA, REDEFINES128.WS_VAL_DATE_YY);
            CEP.TRC(SCCGWA, REDEFINES133.WS_EXP_DATE_MM);
            CEP.TRC(SCCGWA, REDEFINES128.WS_VAL_DATE_MM);
            WS_TERM_MTHS2 = (short) (( REDEFINES133.WS_EXP_DATE_YY - REDEFINES128.WS_VAL_DATE_YY ) * 12 + REDEFINES133.WS_EXP_DATE_MM - REDEFINES128.WS_VAL_DATE_MM);
        }
        CEP.TRC(SCCGWA, "jf11111112444");
        if (TDRCDI.PERD_TYP == '2') {
            WS_VAL_DATE = TDCACCRU.VAL_DT;
            IBS.CPY2CLS(SCCGWA, WS_VAL_DATE+"", REDEFINES128);
            WS_EXP_DATE = TDCACCRU.EXP_DT;
            IBS.CPY2CLS(SCCGWA, WS_EXP_DATE+"", REDEFINES133);
            WS_TERM_MTHS2 = (short) (( REDEFINES133.WS_EXP_DATE_YY - REDEFINES128.WS_VAL_DATE_YY ) * 12 + REDEFINES133.WS_EXP_DATE_MM - REDEFINES128.WS_VAL_DATE_MM);
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = TDCACCRU.VAL_DT;
            SCCCLDT.DATE2 = TDCACCRU.EXP_DT;
            S000_CALL_SCSSCLDT();
            WS_CAL_DAYS = SCCCLDT.DAYS;
        }
        if (TDRCDI.PERD_TYP == '1') {
            TDRCDI.PLAN_NUM = (short) (WS_TERM_MTHS2 / TDCACCRU.CD_PERD);
        }
        if (TDRCDI.PERD_TYP == '2') {
            TDRCDI.PLAN_NUM = (short) (WS_CAL_DAYS / TDCACCRU.CD_PERD);
        }
        CEP.TRC(SCCGWA, "jf1111111666");
        TDRCDI.INTOUT = TDCACCRU.INOUT_FLG;
        TDRCDI.REMMIT_BK = TDCACCRU.REMMIT_BK;
        TDRCDI.REMMIT_NM = TDCACCRU.REMMIT_NM;
        if ((DPCPARMP.AC_TYPE.equalsIgnoreCase("025") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("026")) 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("024")) {
            CEP.TRC(SCCGWA, TDCACCRU.CD_AMT);
            CEP.TRC(SCCGWA, TDCACCRU.TXN_AMT);
            TDRCDI.CD_AMT = TDCACCRU.CD_AMT;
            if (DPCPARMP.AC_TYPE.equalsIgnoreCase("024")) {
                TDRCDI.CD_AMT = TDCACCRU.TXN_AMT / TDRCDI.PLAN_NUM;
            }
            WS_BAL = TDCACCRU.TXN_AMT % TDCACCRU.CD_AMT;
            TDRCDI.NOR_NUM = (short) ((TDCACCRU.TXN_AMT - WS_BAL) / TDCACCRU.CD_AMT);
            CEP.TRC(SCCGWA, TDRCDI.NOR_NUM);
            CEP.TRC(SCCGWA, TDRCDI.PLAN_NUM);
            if (TDRCDI.NOR_NUM > TDRCDI.PLAN_NUM 
                || WS_BAL != 0) {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_AMT_NOT_MATCH;
                S000_ERR_MSG_PROC();
            }
            if (TDRCDI.NOR_NUM * TDCACCRU.CD_AMT > WS_MAX_AMTC 
                && WS_MAX_AMTC != 0) {
                CEP.TRC(SCCGWA, TDRSMST.BAL);
                WS_MSGID = TDCMSG_ERROR_MSG.TD_AMT_SO_LARGE;
                S000_ERR_MSG_PROC();
            }
        } else {
            if (!(DPCPARMP.AC_TYPE.equalsIgnoreCase("028") 
                && TDRCDI.PERD_TYP == '3') 
                && TDRCDI.PLAN_NUM != 0) {
                TDRCDI.CD_AMT = TDRSMST.EXP_INT / TDRCDI.PLAN_NUM;
            }
        }
        CEP.TRC(SCCGWA, WS_MAX_AMTC);
        CEP.TRC(SCCGWA, TDCACCRU.TXN_AMT);
        CEP.TRC(SCCGWA, TDRCDI.PLAN_NUM);
        CEP.TRC(SCCGWA, TDRCDI.CD_AMT);
        if (TDRCDI.PLAN_NUM * TDRCDI.CD_AMT > WS_MAX_AMTC 
            && WS_MAX_AMTC != 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AMT_SO_LARGE;
            S000_ERR_MSG_PROC();
        }
        if (TDCACCRU.INT_AC == null) TDCACCRU.INT_AC = "";
        JIBS_tmp_int = TDCACCRU.INT_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCACCRU.INT_AC += " ";
        if (TDCACCRU.INT_AC.trim().length() > 0 
            && TDCACCRU.INOUT_FLG != '2' 
            && TDCACCRU.INT_AC.substring(26 - 1, 26 + 1 - 1).trim().length() == 0) {
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = TDCACCRU.INT_AC;
            S000_CALL_CIZACCU();
            CEP.TRC(SCCGWA, CICACCU.DATA.ENTY_TYP);
            CEP.TRC(SCCGWA, CICACCU.DATA.FRM_APP);
            if (CICACCU.DATA.ENTY_TYP == '2'
                || CICACCU.DATA.ENTY_TYP == '5'
                || CICACCU.DATA.ENTY_TYP == '6') {
                TDRCDI.STL_MTH = '1';
            } else if (CICACCU.DATA.ENTY_TYP == '1') {
                if (CICACCU.DATA.FRM_APP.equalsIgnoreCase(K_DD_ACCO)) {
                    TDRCDI.STL_MTH = '2';
                } else {
                    WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
                    S000_ERR_MSG_PROC();
                }
            } else if (CICACCU.DATA.ENTY_TYP == '3') {
                if (CICACCU.DATA.FRM_APP.equalsIgnoreCase(K_DC_ACCO)) {
                    TDRCDI.STL_MTH = '2';
                } else {
                    WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
                    S000_ERR_MSG_PROC();
                }
            } else {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
                S000_ERR_MSG_PROC();
            }
        }
        if (TDCACCRU.INOUT_FLG == '2') {
            TDRCDI.STL_MTH = '2';
        }
        TDRCDI.LAST_DEAL_DATE = TDCACCRU.VAL_DT;
        if (TDCACCRU.FRAT_DT != 0) {
            TDRCDI.FST_INT_DATE = TDCACCRU.FRAT_DT;
            TDRCDI.NEXT_DEAL_DATE = TDCACCRU.FRAT_DT;
            if (TDRCDI.PERD_TYP == '1') {
                REDEFINES56.WS_TERM_MTHS = WS_CD_PERD;
                WS_TERM = IBS.CLS2CPY(SCCGWA, REDEFINES56);
                WS_STR_DT = TDCACCRU.FRAT_DT;
                B521_CAL_NEXT_DT();
                WS_DEAL_DATE = WS_NEXT_DT;
            }
            if (TDRCDI.PERD_TYP == '2') {
                WS_PERD_DAYS = WS_CD_PERD;
                WS_STR_DT = TDCACCRU.FRAT_DT;
                B522_CAL_NEXT_DT();
                WS_DEAL_DATE = WS_NEXT_DT;
            }
        } else {
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW.substring(21 - 1, 21 + 1 - 1).equalsIgnoreCase("1")) {
                REDEFINES56.WS_TERM_MTHS = (short) (TDRCDI.NOR_NUM + 1);
                REDEFINES56.WS_TERM_MTHS = (short) (TDRCDI.NOR_NUM * WS_CD_PERD);
                WS_STR_DT = TDRSMST.VAL_DATE;
                B521_CAL_NEXT_DT();
                TDRCDI.NEXT_DEAL_DATE = WS_NEXT_DT;
                WS_DEAL_DATE = WS_NEXT_DT;
            }
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW.substring(22 - 1, 22 + 1 - 1).equalsIgnoreCase("1")) {
                if (TDRCDI.PERD_TYP == '1') {
                    REDEFINES56.WS_TERM_MTHS = WS_CD_PERD;
                    WS_TERM = IBS.CLS2CPY(SCCGWA, REDEFINES56);
                    WS_STR_DT = TDCACCRU.VAL_DT;
                    B521_CAL_NEXT_DT();
                    TDRCDI.NEXT_DEAL_DATE = WS_NEXT_DT;
                    WS_DEAL_DATE = WS_NEXT_DT;
                }
                if (TDRCDI.PERD_TYP == '2') {
                    WS_PERD_DAYS = WS_CD_PERD;
                    WS_STR_DT = TDCACCRU.VAL_DT;
                    B522_CAL_NEXT_DT();
                    TDRCDI.NEXT_DEAL_DATE = WS_NEXT_DT;
                    WS_DEAL_DATE = WS_NEXT_DT;
                }
            }
        }
        if (WS_DEAL_DATE > TDRSMST.EXP_DATE) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_FST_DT);
        }
        if (TDCACCRU.CD_AUTO_FLG == 'Y') {
            TDRCDI.PAY_AC = TDCACCRU.INT_AC;
            if (TDCACCRU.INT_AC.trim().length() == 0) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INPT_ERR);
            }
        }
        TDRCDI.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRCDI.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRCDI.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRCDI.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        TDRCDI.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRCDI.CD_PERD = TDCACCRU.CD_PERD;
        if (TDCACCRU.INT_AC.trim().length() > 0) {
            if (TDRCDI.PAY_AC == null) TDRCDI.PAY_AC = "";
            JIBS_tmp_int = TDRCDI.PAY_AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) TDRCDI.PAY_AC += " ";
            if (TDRCDI.PAY_AC.substring(26 - 1, 26 + 1 - 1).trim().length() > 0) {
                TDRCDI.STL_MTH = '1';
            }
            if (TDRCDI.PAY_AC == null) TDRCDI.PAY_AC = "";
            JIBS_tmp_int = TDRCDI.PAY_AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) TDRCDI.PAY_AC += " ";
            if (TDRCDI.PAY_AC.substring(26 - 1, 26 + 1 - 1).trim().length() == 0) {
                TDRCDI.STL_MTH = '2';
            }
        }
        T000_WRITE_CDI();
        WS_PERD = "" + TDRCDI.CD_PERD;
        JIBS_tmp_int = WS_PERD.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) WS_PERD = "0" + WS_PERD;
    }
    public void B520_REWRT_CDI() throws IOException,SQLException,Exception {
        TDRCDI.LAST_DEAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        WS_BAL = TDCACCRU.TXN_AMT % TDRCDI.CD_AMT;
        WS_NUM = (short) ((TDCACCRU.TXN_AMT - WS_BAL) / TDRCDI.CD_AMT);
        CEP.TRC(SCCGWA, WS_NUM);
        if (WS_BAL != 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AMT_NOT_MATCH;
            S000_ERR_MSG_PROC();
        }
        WS_DATE1 = TDRSMST.VAL_DATE;
        IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES60);
        IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES64);
        WS_DATE2 = SCCGWA.COMM_AREA.AC_DATE;
        IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES68);
        IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES72);
        CEP.TRC(SCCGWA, WS_DATE1);
        CEP.TRC(SCCGWA, WS_DATE2);
        WS_MTHS = (short) (( REDEFINES68.WS_DATE2_YY - REDEFINES60.WS_DATE1_YY ) * 12 + REDEFINES68.WS_DATE2_MM - REDEFINES60.WS_DATE1_MM);
        CEP.TRC(SCCGWA, WS_MTHS);
        CEP.TRC(SCCGWA, WS_CD_PERD);
        WS_MTHS_1 = (short) (WS_MTHS % WS_CD_PERD);
        WS_MTHS = (short) ((WS_MTHS - WS_MTHS_1) / WS_CD_PERD);
        WS_SHD_NUM = WS_MTHS;
        CEP.TRC(SCCGWA, WS_SHD_NUM);
        CEP.TRC(SCCGWA, WS_MTHS_1);
        CEP.TRC(SCCGWA, TDRCDI.NOR_NUM);
        CEP.TRC(SCCGWA, TDRCDI.NEXT_DEAL_DATE);
        CEP.TRC(SCCGWA, WS_MTHS);
        CEP.TRC(SCCGWA, TDRCDI.NOR_NUM);
        CEP.TRC(SCCGWA, TDRCDI.LOS_DNUM);
        CEP.TRC(SCCGWA, TDRCDI.LOS_NUM);
        WS_MTHS = (short) (WS_MTHS + 1 - TDRCDI.NOR_NUM - TDRCDI.LOS_DNUM - TDRCDI.LOS_NUM);
        if (WS_MTHS <= 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_NOT_GK;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, WS_MTHS);
        CEP.TRC(SCCGWA, TDCPIOD.OTH_PRM.PAY_GRCE);
        TDCPIOD.OTH_PRM.PAY_GRCE += 1;
        if (TDRCDI.LOS_NUM >= TDCPIOD.OTH_PRM.PAY_GRCE
            || WS_MTHS > TDCPIOD.OTH_PRM.PAY_GRCE) {
            CEP.TRC(SCCGWA, WS_NUM);
            if (WS_NUM != 1) {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_GK_PRV_ERR;
                S000_ERR_MSG_PROC();
            }
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_IS_LOSS);
            if (WS_MTHS > TDCPIOD.OTH_PRM.PAY_GRCE) {
                TDRCDI.LOS_NUM = (short) (TDRCDI.LOS_NUM + WS_MTHS - 1);
            }
            TDRCDI.LOS_DNUM = (short) (TDRCDI.LOS_DNUM + WS_NUM);
        } else if (WS_MTHS == TDCPIOD.OTH_PRM.PAY_GRCE) {
            WS_SHD_NUM = (short) (TDRCDI.PLAN_NUM - WS_SHD_NUM);
            CEP.TRC(SCCGWA, WS_SHD_NUM);
            CEP.TRC(SCCGWA, WS_NUM);
            if (WS_SHD_NUM >= 2 
                && WS_NUM != 3 
                && !SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_LOS_AMT_ERR;
                S000_ERR_MSG_PROC();
            }
            if (WS_SHD_NUM == 1 
                && WS_NUM != 2) {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_LOS_AMT_ERR;
                S000_ERR_MSG_PROC();
            }
            TDRCDI.NOR_NUM = (short) (TDRCDI.NOR_NUM + WS_NUM);
        } else if (WS_MTHS < TDCPIOD.OTH_PRM.PAY_GRCE) {
            if (WS_NUM != 1) {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_GK_PRV_ERR;
                S000_ERR_MSG_PROC();
            }
            TDRCDI.NOR_NUM = (short) (TDRCDI.NOR_NUM + WS_NUM);
        }
        WS_HAP_NUM = (short) (TDRCDI.NOR_NUM + TDRCDI.LOS_DNUM + TDRCDI.LOS_NUM);
        CEP.TRC(SCCGWA, WS_HAP_NUM);
        if (WS_HAP_NUM > TDRCDI.PLAN_NUM) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_PERD_M_PLAN_PERD;
            S000_ERR_MSG_PROC();
        }
        REDEFINES56.WS_TERM_MTHS = (short) (WS_HAP_NUM * WS_CD_PERD);
        WS_STR_DT = TDRSMST.VAL_DATE;
        B521_CAL_NEXT_DT();
        TDRCDI.NEXT_DEAL_DATE = WS_NEXT_DT;
        TDRCDI.LAST_DEAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRCDI.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRCDI.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_CDI();
    }
    public void B521_CAL_NEXT_DT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, REDEFINES56.WS_TERM_MTHS);
        CEP.TRC(SCCGWA, WS_STR_DT);
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = WS_STR_DT;
        SCCCLDT.MTHS = REDEFINES56.WS_TERM_MTHS;
        S000_CALL_SCSSCLDT();
        WS_NEXT_DT = SCCCLDT.DATE2;
        CEP.TRC(SCCGWA, WS_NEXT_DT);
    }
    public void B522_CAL_NEXT_DT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_STR_DT);
        CEP.TRC(SCCGWA, WS_PERD_DAYS);
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = WS_STR_DT;
        SCCCLDT.DAYS = WS_PERD_DAYS;
        S000_CALL_SCSSCLDT();
        WS_NEXT_DT = SCCCLDT.DATE2;
    }
    public void B520_REWRT_CDI_CANCEL() throws IOException,SQLException,Exception {
        TDRCDI.LOS_DNUM = TDCACCRU.LOS_DNUM;
        TDRCDI.LOS_NUM = 0;
        WS_BAL = TDCACCRU.TXN_AMT % TDRCDI.CD_AMT;
        WS_NUM = (short) ((TDCACCRU.TXN_AMT - WS_BAL) / TDRCDI.CD_AMT);
        CEP.TRC(SCCGWA, WS_NUM);
        if (WS_BAL != 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AMT_NOT_MATCH;
            S000_ERR_MSG_PROC();
        }
        TDRCDI.NOR_NUM = (short) (TDRCDI.NOR_NUM - WS_NUM);
        if (TDRCDI.NOR_NUM < 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AMT_NOT_MATCH;
            S000_ERR_MSG_PROC();
        }
        TDRCDI.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRCDI.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRCDI.NEXT_DEAL_DATE = TDCACCRU.LAST_DT;
        CEP.TRC(SCCGWA, TDCACCRU.LAST_DT);
        T000_REWRITE_CDI();
    }
    public void B530_REWRT_MMST() throws IOException,SQLException,Exception {
    }
    public void B590_CCYU_MMST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCACCRU.AC_NO);
        CEP.TRC(SCCGWA, TDCACCRU.CCY);
        CEP.TRC(SCCGWA, TDCACCRU.CCY_TYP);
        CEP.TRC(SCCGWA, TDCACCRU.TXN_AMT);
        IBS.init(SCCGWA, DCCITRSR);
        DCCITRSR.INP_DATA.OPR = 'T';
        DCCITRSR.INP_DATA.AC = TDCACCRU.AC_NO;
        DCCITRSR.INP_DATA.CCY = TDCACCRU.CCY;
        DCCITRSR.INP_DATA.CCY_TYPE = TDCACCRU.CCY_TYP;
        DCCITRSR.INP_DATA.DRCR_FLG = 'C';
        if (TDCACCRU.CANCEL_FLG == 'Y') {
            DCCITRSR.INP_DATA.DRCR_FLG = 'D';
        }
        DCCITRSR.INP_DATA.TRS_AMT = TDCACCRU.TXN_AMT;
        DCCITRSR.INP_DATA.APP = "TD";
        S000_CALL_DCZITRSR();
    }
    public void B530_REWRT_MMST_CANCEL() throws IOException,SQLException,Exception {
    }
    public void B540_WRT_MSREL() throws IOException,SQLException,Exception {
    }
    public void B550_WRT_CARD_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUCACJ);
        DCCUCACJ.FUNC_CODE = 'U';
        DCCUCACJ.AC_TYP = '2';
        DCCUCACJ.OUT_FLG = 'N';
        DCCUCACJ.CLR_FLG = 'N';
        DCCUCACJ.CARD_NO = TDCACCRU.AC_NO;
        DCCUCACJ.AC_NO = TDCACCRU.AC_NO;
        CEP.TRC(SCCGWA, TDCACCRU.AC_NO);
        DCCUCACJ.AC_CCY = TDCACCRU.CCY;
        DCCUCACJ.AC_PROD_CD = TDCACCRU.PROD_CD;
        DCCUCACJ.CARD_LNK_TYP = '1';
        S000_CALL_DCZUCACJ();
        WS_AC_SEQ = DCCUCACJ.LNK_SEQ;
        CEP.TRC(SCCGWA, DCCUCACJ.LNK_SEQ);
    }
    public void B560_RWT_BVT_YBT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRBVT.PSBK_POS);
        if (TDRBVT.PSBK_POS == 0) {
            TDRBVT.PSBK_POS += 1;
        } else {
            TDRBVT.PSBK_POS += 2;
        }
        TDRBVT.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRBVT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRBVT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRBVT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_BVT();
    }
    public void B560_RWT_BVT_PBP() throws IOException,SQLException,Exception {
        TDRBVT.PSBK_POS += 1;
        TDRBVT.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRBVT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRBVT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRBVT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_BVT();
    }
    public void B560_WRT_YBTP_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRYBTP);
        TDRYBTP.KEY.AC_NO = TDCACCRU.AC_NO;
        TDRYBTP.KEY.PROC_SEQ = TDRCMST.PROC_SEQ;
        CEP.TRC(SCCGWA, WS_AC_SEQ);
        CEP.TRC(SCCGWA, TDCACCRU.AC_SEQ);
        CEP.TRC(SCCGWA, TDRYBTP.KEY.PROC_SEQ);
        TDRYBTP.KEY.AC_SEQ = TDCACCRU.AC_SEQ;
        TDRYBTP.PART_NUM = TDRSMST.PART_NUM;
        TDRYBTP.PRT_STS = '1';
        TDRYBTP.PSBK_POS = (short) TDRBVT.PSBK_POS;
        TDRYBTP.VAL_DATE = TDRSMST.VAL_DATE;
        TDRYBTP.EXP_DATE = TDRSMST.EXP_DATE;
        TDRYBTP.BAL = TDRSMST.BAL;
        TDRYBTP.AMT = TDRSMST.BAL;
        TDRYBTP.EXP_INT_RAT = TDRIREV.CON_RATE;
        TDRYBTP.EXP_INT = TDRSMST.EXP_INT;
        TDRYBTP.OPEN_DATE = TDRSMST.OPEN_DATE;
        TDRYBTP.OPEN_TLR = TDRSMST.CRT_TLR;
        TDRYBTP.OPEN_MMO = WS_MMO;
        TDRYBTP.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRYBTP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRYBTP.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        TDRYBTP.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, WS_ZS_FLG);
        if (WS_ZS_FLG == 'Y' 
            && TDRSMST.PRDAC_CD.equalsIgnoreCase("020")) {
            if (TDRYBTP.FLR == null) TDRYBTP.FLR = "";
            JIBS_tmp_int = TDRYBTP.FLR.length();
            for (int i=0;i<120-JIBS_tmp_int;i++) TDRYBTP.FLR += " ";
            TDRYBTP.FLR = "ZS" + TDRYBTP.FLR.substring(2);
        }
        T000_WRITE_YBTP();
    }
    public void B560_REWRT_YBTP_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'A';
        CICQACAC.DATA.ACAC_NO = TDCACCRU.ACO_AC;
        S000_CALL_CIZQACAC();
        TDRYBTP.KEY.AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
        TDRYBTP.KEY.AC_NO = TDCACCRU.AC_NO;
        TDRYBTP.PART_NUM = TDRSMST.PART_NUM;
        T000_READU_YBTP();
        if (TDRYBTP.PRT_STS == '1') {
            TDRYBTP.PRT_STS = '5';
        } else {
            if (TDRYBTP.PRT_STS == '2') {
                TDRYBTP.PRT_STS = '3';
            }
        }
        TDRYBTP.AMT = -1 * TDCACCRU.TXN_AMT;
        TDRYBTP.INT = 0;
        TDRYBTP.TAX = 0;
        TDRYBTP.INT_RAT = 0;
        TDRYBTP.CLO_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRYBTP.CLO_TLR = TDRSMST.UPD_TLT;
        TDRYBTP.CLO_MMO = WS_MMO;
        TDRYBTP.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRYBTP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_YBTP();
    }
    public void B570_WRT_PBP_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRPBP);
        TDRPBP.KEY.AC_NO = TDRSMST.AC_NO;
        TDRPBP.KEY.PROC_SEQ = TDRSMST.PROC_NUM;
        TDRPBP.PSBK_POS = (short) TDRBVT.PSBK_POS;
        TDRPBP.RAT_INT = TDRIREV.CON_RATE;
        TDRPBP.BAL = TDRSMST.BAL;
        CEP.TRC(SCCGWA, WS_PROC_TYP);
        TDRPBP.PROC_TYP = WS_PROC_TYP;
        TDRPBP.PRT_STS = '0';
        if (WS_EC == ' ') {
            TDRPBP.AMT = TDCACCRU.TXN_AMT;
        } else {
            TDRPBP.AMT = -1 * TDCACCRU.TXN_AMT;
        }
        TDRPBP.PROC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRPBP.PROC_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRPBP.PROC_TLR = SCCGWA.COMM_AREA.TL_ID;
        TDRPBP.PROC_MMO = WS_MMO;
        CEP.TRC(SCCGWA, WS_MMO);
        CEP.TRC(SCCGWA, TDRPBP.PROC_MMO);
        TDRPBP.EC_TRS_TYP = WS_EC;
        TDRPBP.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        TDRPBP.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRPBP.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRPBP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_WRITE_PBP();
    }
    public void B580_WRT_BVT_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRBVT);
        CEP.TRC(SCCGWA, TDCACCRU.BV_TYP);
        if ((TDRCMST.BV_TYP == '2' 
            || TDRCMST.BV_TYP == '3' 
            || TDRCMST.BV_TYP == '8' 
            || TDRCMST.BV_TYP == '7')) {
            TDRBVT.KEY.AC_NO = TDRSMST.AC_NO;
        } else {
            TDRBVT.KEY.AC_NO = TDRSMST.KEY.ACO_AC;
        }
        CEP.TRC(SCCGWA, "LUO2018");
        CEP.TRC(SCCGWA, TDRBVT.KEY.AC_NO);
        TDRBVT.BV_NO = TDCACCRU.BV_NO;
        TDRBVT.BV_CD = TDCACCRU.BV_CD;
        CEP.TRC(SCCGWA, TDRBVT.BV_NO);
        CEP.TRC(SCCGWA, TDRBVT.BV_CD);
        CEP.TRC(SCCGWA, TDCACCRU.DRAW_MTH);
        TDRBVT.STSW = "000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        TDRBVT.ISSU_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRBVT.ISSU_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRBVT.ISSU_TLR = SCCGWA.COMM_AREA.TL_ID;
        TDRBVT.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRBVT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRBVT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRBVT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRBVT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        TDRBVT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRBVT.OWNER_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRBVT.OWNER_BRDP = BPCPORUP.DATA_INFO.BBR;
        TDRBVT.OWNER_BK = BPCPORUP.DATA_INFO.BR;
        T000_WRITE_BVT();
    }
    public void B580_READ_TDTOCAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDROCAC);
        TDROCAC.KEY.AC = TDCACCRU.AC_NO;
        TDROCAC.KEY.STS = 'N';
        T000_READU_TDROCAC();
    }
    public void B600_OUTPUT_YBTP_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCPPRTF);
        TDCPPRTF.OPT = '1';
        TDCPPRTF.AC = TDCACCRU.AC_NO;
        TDCPPRTF.BV_CD = TDCACCRU.BV_CD;
        TDCPPRTF.BV_TYP = TDCACCRU.BV_TYP;
        TDCPPRTF.BV_NO = TDCACCRU.BV_NO;
        TDCPPRTF.RMK = " ";
        TDCPPRTF.ACO_AC = TDCACCRU.ACO_AC;
        S000_CALL_TDZPPRTF();
    }
    public void B610_OUTPUT_PBP_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCPPRTF);
        if (TDCACCRU.OPT == '0') {
            TDCPPRTF.OPT = '2';
        } else {
            TDCPPRTF.OPT = '1';
        }
        TDCPPRTF.AC = TDRSMST.AC_NO;
        TDCPPRTF.PRDAC_CD = TDRSMST.PRDAC_CD;
        TDCPPRTF.AC_NAME = TDCACCRU.NAME;
        TDCPPRTF.BV_CD = TDCACCRU.BV_CD;
        TDCPPRTF.BV_TYP = TDCACCRU.BV_TYP;
        TDCPPRTF.BV_NO = TDCACCRU.BV_NO;
        TDCPPRTF.RMK = " ";
        TDCPPRTF.ACO_AC = TDCACCRU.ACO_AC;
        S000_CALL_TDZPPRTF();
    }
    public void B620_OUTPUT_BV_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCPPRTF);
        TDCPPRTF.OPT = '3';
        TDCPPRTF.AC = TDRSMST.KEY.ACO_AC;
        TDCPPRTF.PRDAC_CD = TDRSMST.PRDAC_CD;
        TDCPPRTF.AC_NAME = TDCACCRU.NAME;
        TDCPPRTF.BV_CD = TDCACCRU.BV_CD;
        TDCPPRTF.BV_TYP = TDCACCRU.BV_TYP;
        TDCPPRTF.BV_NO = TDCACCRU.BV_NO;
        TDCPPRTF.RAT = TDCACCRU.INT_RAT;
        TDCPPRTF.RMK = " ";
        TDCPPRTF.ACO_AC = TDCACCRU.ACO_AC;
        S000_CALL_TDZPPRTF();
    }
    public void B700_INQ_GL_MASTER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCNGL);
        IBS.init(SCCGWA, BPCAMMDP);
        if (TDCACCRU.OPT == '2') {
            CEP.TRC(SCCGWA, TDRSMST.CCY);
            CEP.TRC(SCCGWA, TDRSMST.CCY_TYP);
            CEP.TRC(SCCGWA, TDCACCRU.OPP_AC_CNO);
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.DATA.AGR_NO = TDCACCRU.OPP_AC_CNO;
            CICQACAC.DATA.CCY_ACAC = TDRSMST.CCY;
            CICQACAC.DATA.CR_FLG = TDRSMST.CCY_TYP;
            CICQACAC.FUNC = 'R';
            S000_CALL_CIZQACAC();
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
            IBS.init(SCCGWA, BPCUCNGM);
            BPCUCNGM.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            BPCUCNGM.KEY.CNTR_TYPE = "CAAC";
            BPCUCNGM.FUNC = 'Q';
            S000_CALL_BPZUCNGM();
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUCNGM.DATA[1-1]);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[1-1]);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUCNGM.DATA[2-1]);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[2-1]);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUCNGM.DATA[3-1]);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[3-1]);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUCNGM.DATA[4-1]);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[4-1]);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUCNGM.DATA[5-1]);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[5-1]);
        } else {
            BPCQCNGL.DAT.INPUT.CNTR_TYPE = BPCPQPRD.PRDT_MODEL;
            BPCQCNGL.DAT.INPUT.BR = TDRSMST.OWNER_BR;
            BPCQCNGL.DAT.INPUT.OTH_PTR_LEN = 21;
            BPCAMMDP.PROD_TYPE = TDCACCRU.PROD_CD;
            BPCAMMDP.CI_TYPE = CICCUST.O_DATA.O_CI_TYP;
            BPCAMMDP.FIN_TYP = CICCUST.O_DATA.O_FIN_TYPE;
            BPCAMMDP.AC_TYP = TDCACCRU.ACO_TYP;
            BPCAMMDP.PROP_TYP = TDCACCRU.MON_TYP;
            BPCQCNGL.DAT.INPUT.OTH_PTR = BPCAMMDP;
            S000_CALL_BPZQCNGL();
        }
    }
    public void B710_CHECK_VAL_DT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        BPCPRMM.FUNC = '3';
        BPRPRMT.KEY.TYP = "TDPRM";
        BPRPRMT.KEY.CD = K_AC_BK;
        BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, BPRPRMT.KEY.TYP);
        CEP.TRC(SCCGWA, BPRPRMT.KEY.CD);
        CEP.TRC(SCCGWA, BPCPRMM.EFF_DT);
        BPCPRMM.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMM();
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_CHECK_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (REDEFINES56.WS_TERM_MTHS != 0) {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = TDCACCRU.VAL_DT;
            if (REDEFINES56.WS_TERM_TYP == 'Y' 
                || REDEFINES56.WS_TERM_TYP == 'M') {
                if (REDEFINES56.WS_TERM_TYP == 'Y') {
                    CEP.TRC(SCCGWA, REDEFINES56.WS_TERM_TYP);
                    SCCCLDT.MTHS = (short) (REDEFINES56.WS_TERM_MTHS * 12);
                } else {
                    CEP.TRC(SCCGWA, REDEFINES56.WS_TERM_TYP);
                    SCCCLDT.MTHS = REDEFINES56.WS_TERM_MTHS;
                }
            } else {
                CEP.TRC(SCCGWA, REDEFINES56.WS_TERM_TYP);
                SCCCLDT.DAYS = REDEFINES56.WS_TERM_MTHS;
            }
            CEP.TRC(SCCGWA, REDEFINES56.WS_TERM_MTHS);
            CEP.TRC(SCCGWA, SCCCLDT.DATE1);
            CEP.TRC(SCCGWA, SCCCLDT.MTHS);
            CEP.TRC(SCCGWA, SCCCLDT.DAYS);
            S000_CALL_SCSSCLDT();
            if (SCCCLDT.DATE2 <= SCCGWA.COMM_AREA.AC_DATE) {
                CEP.TRC(SCCGWA, SCCCLDT.DATE2);
                WS_MSGID = TDCMSG_ERROR_MSG.TD_INVALID_VAL_DT;
                S000_ERR_MSG_PROC();
            }
        }
        if ((DPCPARMP.AC_TYPE.equalsIgnoreCase("025") 
            || DPCPARMP.AC_TYPE.equalsIgnoreCase("026"))) {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = TDCACCRU.VAL_DT;
            CEP.TRC(SCCGWA, TDCACCRU.VAL_DT);
            if (TDCACCRU.CD_PERD == 0001) {
                SCCCLDT.MTHS = 1;
            }
            if (TDCACCRU.CD_PERD == 0003) {
                SCCCLDT.MTHS = 3;
            }
            if (TDCACCRU.CD_PERD == 0006) {
                SCCCLDT.MTHS = 6;
            }
            if (TDCACCRU.CD_PERD == 0012) {
                SCCCLDT.MTHS = 12;
            }
            CEP.TRC(SCCGWA, TDCACCRU.CD_PERD);
            CEP.TRC(SCCGWA, SCCCLDT.MTHS);
            S000_CALL_SCSSCLDT();
            if (SCCCLDT.DATE2 <= SCCGWA.COMM_AREA.AC_DATE) {
                CEP.TRC(SCCGWA, SCCCLDT.DATE2);
                WS_MSGID = TDCMSG_ERROR_MSG.TD_INVALID_VAL_DT;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B800_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCOCCRU);
        IBS.init(SCCGWA, TDCODECR);
        TDCOCCRU.PRDAC_CD = TDRSMST.PRDAC_CD;
        TDCOCCRU.BV_TYP = TDCACCRU.BV_TYP;
        TDCOCCRU.BV_CD = TDCACCRU.BV_CD;
        TDCOCCRU.BV_NO = TDCACCRU.BV_NO;
        TDCOCCRU.TX_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDCOCCRU.MAIN_AC = TDCACCRU.AC_NO;
        CEP.TRC(SCCGWA, WS_MAC_SEQ);
        TDCOCCRU.MAIN_SEQ = WS_AC_SEQ;
        CEP.TRC(SCCGWA, TDCOCCRU.MAIN_SEQ);
        TDCOCCRU.AC = TDCACCRU.AC_NO;
        TDCOCCRU.CCY = TDCACCRU.CCY;
        TDCOCCRU.NAME = TDCACCRU.NAME;
        TDCOCCRU.TXN_AMT = TDCACCRU.TXN_AMT;
        TDCOCCRU.OPEN_DATE = TDRSMST.OPEN_DATE;
        TDCOCCRU.VAL_DATE = TDCACCRU.VAL_DT;
        TDCOCCRU.TERMS = TDCACCRU.TERM;
        TDCOCCRU.EXP_DATE = TDCACCRU.EXP_DT;
        if ((SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            || TDCACCRU.CANCEL_FLG == 'Y') 
            && TDCOCCRU.EXP_DATE == 0) {
            TDCOCCRU.EXP_DATE = TDRSMST.EXP_DATE;
        }
        if ((SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            || TDCACCRU.CANCEL_FLG == 'Y') 
            && TDCOCCRU.MAIN_SEQ == 0) {
            TDCOCCRU.MAIN_SEQ = TDCACCRU.AC_SEQ;
        }
        CEP.TRC(SCCGWA, TDCACCRU.INT_RAT);
        TDCOCCRU.INT_RAT = TDCACCRU.INT_RAT;
        if ((SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            || TDCACCRU.CANCEL_FLG == 'Y') 
            && TDCOCCRU.INT_RAT == 0) {
            TDCOCCRU.INT_RAT = TDRIREV.CON_RATE;
        }
        TDCOCCRU.EXP_INT = TDRSMST.EXP_INT;
        CEP.TRC(SCCGWA, TDCACCRU.DRAW_MTH);
        if (TDCACCRU.BV_TYP == '4') {
            TDCOCCRU.DRAW_MTH = TDCACCRU.DRAW_MTH;
        } else {
            TDCOCCRU.DRAW_MTH = TDCACCRU.DRAW_MTH;
        }
        TDCOCCRU.INT_AC = TDCACCRU.INT_AC;
        TDCOCCRU.INOUT = TDCACCRU.INOUT_FLG;
        TDCOCCRU.REMMIT_BK = TDCACCRU.REMMIT_BK;
        TDCOCCRU.REMMIT_NM = TDCACCRU.REMMIT_NM;
        TDCOCCRU.INT_SEL = TDCACCRU.INT_SEL;
        TDCOCCRU.AC_RUL_CD = TDCACCRU.INT_RUL_CD;
        TDCOCCRU.ACTI_NO = TDCPIOD.ACTI_NO;
        CEP.TRC(SCCGWA, TDCOCCRU.MAIN_SEQ);
        TDCACCRU.CHNL_OUTPUT_INFO.O_MAIN_SEQ = TDCOCCRU.MAIN_SEQ;
        WS_DATE1 = TDCACCRU.VAL_DT;
        IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES60);
        IBS.CPY2CLS(SCCGWA, WS_DATE1+"", REDEFINES64);
        WS_DATE2 = TDCACCRU.EXP_DT;
        IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES68);
        IBS.CPY2CLS(SCCGWA, WS_DATE2+"", REDEFINES72);
        CEP.TRC(SCCGWA, "ZS");
        CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.OWN_RATE);
        CEP.TRC(SCCGWA, TDCOCCRU.INT_RAT);
        CEP.TRC(SCCGWA, WS_CI_TYP);
        CEP.TRC(SCCGWA, TDCACCRU.BV_TYP);
        CEP.TRC(SCCGWA, WS_ZS_FLG);
        if (WS_ZS_FLG == 'Y' 
            && WS_CI_TYP == '1' 
            && TDCACCRU.BV_TYP == '3') {
            if (TDCOCCRU.CNTR_NO == null) TDCOCCRU.CNTR_NO = "";
            JIBS_tmp_int = TDCOCCRU.CNTR_NO.length();
            for (int i=0;i<120-JIBS_tmp_int;i++) TDCOCCRU.CNTR_NO += " ";
            TDCOCCRU.CNTR_NO = "Y" + TDCOCCRU.CNTR_NO.substring(1);
        }
        if (TDCOCCRU.CNTR_NO == null) TDCOCCRU.CNTR_NO = "";
        JIBS_tmp_int = TDCOCCRU.CNTR_NO.length();
        for (int i=0;i<120-JIBS_tmp_int;i++) TDCOCCRU.CNTR_NO += " ";
        CEP.TRC(SCCGWA, TDCOCCRU.CNTR_NO.substring(0, 1));
        CEP.TRC(SCCGWA, "TESTING");
        CEP.TRC(SCCGWA, WS_DATE1);
        CEP.TRC(SCCGWA, WS_DATE2);
        CEP.TRC(SCCGWA, TDCACCRU.VAL_DT);
        CEP.TRC(SCCGWA, TDCACCRU.EXP_DT);
        CEP.TRC(SCCGWA, TDCACCRU.FRAT_DT);
        CEP.TRC(SCCGWA, "-----------");
        WS_LEN = 1491;
        CEP.TRC(SCCGWA, WS_LEN);
        CEP.TRC(SCCGWA, "************");
        CEP.TRC(SCCGWA, "************");
        WS_LEN = 0;
        CEP.TRC(SCCGWA, WS_LEN);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT2;
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        CEP.TRC(SCCGWA, TDRSMST.STSW.substring(41 - 1, 41 + 1 - 1));
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(41 - 1, 41 + 1 - 1).equalsIgnoreCase("7") 
            || TDRSMST.STSW.substring(41 - 1, 41 + 1 - 1).equalsIgnoreCase("8")) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            if (JIBS_tmp_str[0].equalsIgnoreCase("0125103")) {
                SCCFMT.FMTID = K_OUTPUT_FMT4;
            }
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCOCCRU);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCODECR);
            SCCFMT.DATA_PTR = TDCODECR;
            SCCFMT.DATA_LEN = 1491;
        } else {
            CEP.TRC(SCCGWA, SCCFMT.FMTID);
            SCCFMT.DATA_PTR = TDCOCCRU;
            SCCFMT.DATA_LEN = 0;
            CEP.TRC(SCCGWA, "LUO");
            CEP.TRC(SCCGWA, TDCOCCRU.MAIN_SEQ);
        }
        IBS.FMT(SCCGWA, SCCFMT);
        TDCACCRU.EXP_INT = TDRSMST.EXP_INT;
    }
    public void B750_WRT_GL_MASTER() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPQPRD.PRDT_MODEL);
        IBS.init(SCCGWA, BPCUCNGM);
        BPCUCNGM.FUNC = 'A';
        if (TDCACCRU.OPT == '2') {
            BPCUCNGM.KEY.CNTR_TYPE = "CAAC";
            BPCUCNGM.PROD_TYPE = DDCIMMST.DATA.PROD_CODE;
        } else {
            BPCUCNGM.KEY.CNTR_TYPE = BPCPQPRD.PRDT_MODEL;
            BPCUCNGM.PROD_TYPE = TDCACCRU.PROD_CD;
        }
        CEP.TRC(SCCGWA, TDCACCRU.ACO_AC);
        CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
        BPCUCNGM.KEY.AC = TDCACCRU.ACO_AC;
        if (TDCACCRU.ACO_AC.trim().length() == 0) {
            BPCUCNGM.KEY.AC = TDRSMST.KEY.ACO_AC;
        }
        BPCUCNGM.KEY.REL_SEQ = " ";
        BPCUCNGM.BR = TDRSMST.OWNER_BR;
        BPCUCNGM.DATA[1-1].GLMST = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[1-1].MSTNO;
        BPCUCNGM.DATA[2-1].GLMST = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[2-1].MSTNO;
        BPCUCNGM.DATA[3-1].GLMST = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[3-1].MSTNO;
        BPCUCNGM.DATA[4-1].GLMST = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[4-1].MSTNO;
        BPCUCNGM.DATA[5-1].GLMST = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[5-1].MSTNO;
        S000_CALL_BPZUCNGM();
    }
    public void B900_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCOCCLZ);
        TDCOCCLZ.BV_CD = TDCACCRU.BV_CD;
        TDCOCCLZ.BV_NO = TDCACCRU.BV_NO;
        TDCOCCLZ.TX_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDCOCCLZ.AC = TDCACCRU.AC_NO;
        TDCOCCLZ.CCY = TDCACCRU.CCY;
        TDCOCCLZ.NAME = TDCACCRU.NAME;
        TDCOCCLZ.TXN_AMT = TDCACCRU.TXN_AMT;
        CEP.TRC(SCCGWA, TDRSMST.PBAL);
        CEP.TRC(SCCGWA, TDRSMST.BAL);
        TDCOCCLZ.BAL = TDRSMST.BAL;
        TDCOCCLZ.VAL_DATE = TDCACCRU.VAL_DT;
        TDCOCCLZ.BR = TDROCAC.BR;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT3;
        SCCFMT.DATA_PTR = TDCOCCLZ;
        SCCFMT.DATA_LEN = 368;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_CHECK_CARD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCPFTCK);
        DCCPFTCK.VAL.CARD_NO = CICQACRI.O_DATA.O_AGR_NO;
        DCCPFTCK.VAL.REGN_TYP = '0';
        DCCPFTCK.VAL.TXN_TYPE = "02";
        DCCPFTCK.VAL.TXN_CCY = TDCACCRU.CCY;
        DCCPFTCK.VAL.TXN_AMT = TDCACCRU.TXN_AMT;
        S000_CALL_DCZPFTCK();
    }
    public void R000_CALL_DC_HLD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUHLD);
        DCCUHLD.DATA.AC = TDCACCRU.OPP_AC_CNO;
        DCCUHLD.DATA.HLD_TYP = '2';
        DCCUHLD.DATA.SPR_TYP = '2';
        DCCUHLD.DATA.HLD_CLS = '4';
        DCCUHLD.DATA.CCY = TDRSMST.CCY;
        DCCUHLD.DATA.CCY_TYP = TDRSMST.CCY_TYP;
        DCCUHLD.DATA.AMT = TDRSMST.BAL;
        DCCUHLD.DATA.RMK = TDRSMST.KEY.ACO_AC;
        if (WS_CI_TYP == '1') {
            if (DCCUHLD.DATA.RMK == null) DCCUHLD.DATA.RMK = "";
            JIBS_tmp_int = DCCUHLD.DATA.RMK.length();
            for (int i=0;i<120-JIBS_tmp_int;i++) DCCUHLD.DATA.RMK += " ";
            DCCUHLD.DATA.RMK = DCCUHLD.DATA.RMK.substring(0, 49 - 1) + "Y" + DCCUHLD.DATA.RMK.substring(49 + 1 - 1);
        }
        if (DCCUHLD.DATA.RMK == null) DCCUHLD.DATA.RMK = "";
        JIBS_tmp_int = DCCUHLD.DATA.RMK.length();
        for (int i=0;i<120-JIBS_tmp_int;i++) DCCUHLD.DATA.RMK += " ";
        JIBS_tmp_str[0] = "" + TDCACCRU.JRNNO;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<12-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        DCCUHLD.DATA.RMK = DCCUHLD.DATA.RMK.substring(0, 50 - 1) + JIBS_tmp_str[0] + DCCUHLD.DATA.RMK.substring(50 + 12 - 1);
        DCCUHLD.DATA.HLD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_DCZUHLD();
    }
    public void R000_GET_TAX_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCITAXG);
        BPCITAXG.TAX_TYP = TDCPRDP.EXP_PRM.TAX_CD;
        BPCITAXG.VAL_TYP = '0';
        BPCITAXG.BR = TDRSMST.OWNER_BR;
        BPCITAXG.CCY = TDRSMST.CCY;
        BPCITAXG.ST_DT = TDRCDI.LAST_DEAL_DATE;
        BPCITAXG.EN_DT = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, BPCITAXG.TAX_TYP);
        CEP.TRC(SCCGWA, BPCITAXG.BR);
        CEP.TRC(SCCGWA, BPCITAXG.CCY);
        CEP.TRC(SCCGWA, BPCITAXG.ST_DT);
        CEP.TRC(SCCGWA, BPCITAXG.EN_DT);
        S000_CALL_BPZITAXG();
    }
    public void R000_AMT_EX_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFX);
        BPCFX.FUNC = '3';
        BPCFX.CHNL = SCCGWA.COMM_AREA.CHNL;
        BPCFX.EXR_TYPE = "MDR";
        CEP.TRC(SCCGWA, WS_REF_CCY);
        BPCFX.BUY_CCY = WS_REF_CCY;
        BPCFX.BUY_AMT = WS_BUY_AMT;
        BPCFX.SELL_CCY = TDCACCRU.CCY;
        S000_CALL_BPZSFX();
        WS_SELL_AMT = BPCFX.SELL_AMT;
    }
    public void R000_CHK_DD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIQBAL);
        DDCIQBAL.DATA.AC = WS_AC;
        DDCIQBAL.DATA.CCY = TDCACCRU.CCY;
        DDCIQBAL.DATA.CCY_TYPE = TDCACCRU.CCY_TYP;
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.AC);
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.CCY);
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.CCY_TYPE);
        S000_CALL_DDZIQBAL();
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.CCY_STS_WORD);
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.CCY_STS);
        if (DDCIQBAL.DATA.CCY_STS == ' ') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_ERR_CCY);
        }
        if (DDCIQBAL.DATA.CCY_STS == 'C') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.AC_CLOSE_AND_REDEMP);
        }
        if (DDCIQBAL.DATA.CCY_STS != 'N') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DD_ERR);
        }
        CEP.TRC(SCCGWA, WS_AC_TYPE);
        CEP.TRC(SCCGWA, WS_CHECK_OPP);
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.CURR_BAL);
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.HOLD_BAL);
        CEP.TRC(SCCGWA, TDCACCRU.TXN_AMT);
        if (WS_AC_TYPE.equalsIgnoreCase("037") 
            && WS_CHECK_OPP == 'Y') {
            if (DDCIQBAL.DATA.CURR_BAL - DDCIQBAL.DATA.HOLD_BAL < TDCACCRU.TXN_AMT) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AVL_BAL_NOT_ENOUGH);
            }
        }
    }
    public void R000_CHK_AI_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = WS_AC;
        S000_CALL_CIZQACRI();
        if (!CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INV_AC_TYP);
        }
    }
    public void R000_CHK_CI_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = WS_AC;
        S000_CALL_CIZQACRI();
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_CI_NO);
        CEP.TRC(SCCGWA, TDCACCRU.CI_NO);
        if (!CICQACRI.O_DATA.O_CI_NO.equalsIgnoreCase(TDCACCRU.CI_NO)) {
            if (TDCPIOD.TXN_PRM.CUST_CTL == '0') {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_CI_NOT_SAME;
                S000_ERR_MSG_PROC();
            } else if (TDCPIOD.TXN_PRM.CUST_CTL == '1') {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_CI_NOT_SAME_AUTH;
                S000_ERR_MSG_PROC();
            } else if (TDCPIOD.TXN_PRM.CUST_CTL == '2') {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_CI_NOT_SAME_WARM;
                S000_ERR_MSG_PROC();
            } else {
            }
        }
        if ((CICQACRI.O_DATA.O_CI_TYP == '2' 
            || CICQACRI.O_DATA.O_CI_TYP == '3') 
            && TDCACCRU.OPT != '3') {
            if (CICCUST.O_DATA.O_CI_TYP == '1') {
                WS_MSGID = DDCMSG_ERROR_MSG.DD_AC_TYPE_M_ST;
                S000_ERR_MSG_PROC();
            }
        }
        if ((CICQACRI.O_DATA.O_CI_TYP == '1') 
            && TDCACCRU.OPT != '3') {
            if (CICCUST.O_DATA.O_CI_TYP == '2' 
                || CICCUST.O_DATA.O_CI_TYP == '3') {
                WS_MSGID = DDCMSG_ERROR_MSG.DD_AC_MUST_UNIT_AC;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void R000_GET_DDAC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        CEP.TRC(SCCGWA, TDCACCRU.OPP_AC_CNO);
        DDRMST.KEY.CUS_AC = TDCACCRU.OPP_AC_CNO;
        T000_READ_DDTMST();
        IBS.init(SCCGWA, CICACDD);
        CICACDD.DATA.AGR_NO = TDCACCRU.OPP_AC_CNO;
        S000_CALL_CIZACDD();
    }
    public void R00_CHECK_FROMDATE_HOLIDAY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOCLWD);
        BPCOCLWD.CAL_CODE = "CN";
        BPCOCLWD.DATE1 = TDCACCRU.EXP_DT;
        BPCOCLWD.DAYS = 1;
        S00_LINK_BPZPCLWD();
        if (BPCOCLWD.DATE2_FLG == 'H') {
            IBS.init(SCCGWA, BPCOCLWD);
            BPCOCLWD.CAL_CODE = "CN";
            BPCOCLWD.DATE1 = TDCACCRU.EXP_DT;
            BPCOCLWD.WDAYS = 1;
            CEP.TRC(SCCGWA, BPCOCLWD.CAL_CODE);
            CEP.TRC(SCCGWA, BPCOCLWD.DATE1);
            CEP.TRC(SCCGWA, BPCOCLWD.WDAYS);
            S00_LINK_BPZPCLWD();
            TDCACCRU.EXP_DT = BPCOCLWD.DATE2;
            CEP.TRC(SCCGWA, TDCACCRU.EXP_DT);
        }
    }
    public void R000_7_X_24H_BAL_UPD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, TDRSMST.LBAL_DATE);
        CEP.TRC(SCCGWA, WS_LBAL_DATE);
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y' 
            && TDCACCRU.CANCEL_FLG != 'Y') {
            if (SCCGWA.COMM_AREA.AC_DATE > TDRSMST.LBAL_DATE) {
                TDRSMST.LBAL = TDRSMST.BAL;
                TDRSMST.LBAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            } else {
                if (SCCGWA.COMM_AREA.AC_DATE == TDRSMST.LBAL_DATE) {
                } else {
                    TDRSMST.LBAL = TDRSMST.LBAL + TDCACCRU.TXN_AMT;
                }
            }
            CEP.TRC(SCCGWA, TDCACCRU.TXN_AMT);
        } else {
            if (SCCGWA.COMM_AREA.AC_DATE < TDRSMST.LBAL_DATE) {
                TDRSMST.LBAL = TDRSMST.LBAL - TDCACCRU.TXN_AMT;
            }
        }
    }
    public void R000_GET_CARD_FLG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = WS_AC;
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = WS_AC;
        S000_CALL_CIZQACRI();
        if (CICQACRI.DATA.AGR_NO.trim().length() > 0) {
            CICACCU.DATA.AGR_NO = CICQACRI.DATA.AGR_NO;
        }
        S000_CALL_CIZACCU();
        if (CICACCU.DATA.ENTY_TYP == '2'
            || CICACCU.DATA.ENTY_TYP == '5'
            || CICACCU.DATA.ENTY_TYP == '6') {
            WS_STL_MTH = '1';
        } else if (CICACCU.DATA.ENTY_TYP == '1') {
            if (CICACCU.DATA.FRM_APP.equalsIgnoreCase(K_DD_ACCO)) {
                WS_STL_MTH = '2';
            } else {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
                S000_ERR_MSG_PROC();
            }
        } else if (CICACCU.DATA.ENTY_TYP == '3') {
            if (CICACCU.DATA.FRM_APP.equalsIgnoreCase(K_DC_ACCO)) {
                WS_STL_MTH = '2';
            } else {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
                S000_ERR_MSG_PROC();
            }
        } else {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void R000_GET_BR_CITY_FLG() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        IBS.init(SCCGWA, BPCPRGST);
        BPCPRGST.BR1 = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPRGST.BR2 = TDRSMST.OWNER_BR;
        S000_CALL_BPZPRGST();
        CEP.TRC(SCCGWA, BPCPRGST.BRANCH_FLG);
        if (BPCPRGST.BRANCH_FLG == 'N') {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
            BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            S000_CALL_BPZPQORG();
            WS_BRANCH_BR1 = BPCPQORG.BRANCH_BR;
            CEP.TRC(SCCGWA, WS_BRANCH_BR1);
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
            BPCPQORG.BR = TDRSMST.OWNER_BR;
            S000_CALL_BPZPQORG();
            WS_BRANCH_BR2 = BPCPQORG.BRANCH_BR;
            CEP.TRC(SCCGWA, WS_BRANCH_BR1);
            CEP.TRC(SCCGWA, WS_BRANCH_BR2);
            if (WS_BRANCH_BR1 == WS_BRANCH_BR2) {
                BPCPRGST.BRANCH_FLG = 'Y';
            } else {
                if ((WS_BRANCH_BR1 == 104000 
                    || WS_BRANCH_BR1 == 121000) 
                    && (WS_BRANCH_BR2 == 104000 
                    || WS_BRANCH_BR2 == 121000)) {
                    BPCPRGST.BRANCH_FLG = 'Y';
                }
            }
        }
        CEP.TRC(SCCGWA, BPCPRGST.BRANCH_FLG);
    }
    public void R000_SET_FEE_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        if (TDCACCRU.CT_FLG == '0'
            || TDCACCRU.CT_FLG == '1') {
            BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '1';
        } else if (TDCACCRU.CT_FLG == '2'
            || TDCACCRU.CT_FLG == '3'
            || TDCACCRU.CT_FLG == '4'
            || TDCACCRU.CT_FLG == '5') {
            BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '1';
        } else {
        }
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = TDRSMST.CCY;
        BPCFFTXI.TX_DATA.CCY_TYPE = TDRSMST.CCY_TYP;
        BPCFFTXI.TX_DATA.SVR_CD = SCCGWA.COMM_AREA.SERV_CODE;
        BPCFFTXI.TX_DATA.CI_NO = TDCACCRU.CI_NO;
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY);
        S000_CALL_BPZFFTXI();
    }
    public void R000_CALL_FEE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTCALF);
        BPCTCALF.INPUT_AREA.FUNC_CODE = 'C';
        BPCTCALF.INPUT_AREA.ATTR_VAL.BNK_FLG = '0';
        BPCTCALF.INPUT_AREA.ATTR_VAL.DC_FLG = '1';
        if (BPCPRGST.BRANCH_FLG == 'Y') {
            BPCTCALF.INPUT_AREA.ATTR_VAL.CITY_FLG = '0';
        } else {
            BPCTCALF.INPUT_AREA.ATTR_VAL.CITY_FLG = '1';
        }
        if (TDCACCRU.AC_NO.trim().length() > 0) {
            BPCTCALF.INPUT_AREA.TX_AC = TDCACCRU.AC_NO;
        } else {
            BPCTCALF.INPUT_AREA.TX_AC = TDCACCRU.AC_NO;
        }
        BPCTCALF.INPUT_AREA.TX_CCY = TDRSMST.CCY;
        BPCTCALF.INPUT_AREA.TX_AMT = TDCACCRU.TXN_AMT;
        BPCTCALF.INPUT_AREA.TX_CNT = 1;
        BPCTCALF.INPUT_AREA.OTHER_AC = TDRSMST.KEY.ACO_AC;
        BPCTCALF.INPUT_AREA.TX_CI = TDCACCRU.CI_NO;
        BPCTCALF.INPUT_AREA.PROD_CODE = TDRSMST.PROD_CD;
        BPCTCALF.INPUT_AREA.PROD_CODE1 = TDRSMST.PROD_CD;
        CEP.TRC(SCCGWA, BPCTCALF.RC);
        S000_CALL_BPZFCALF();
        CEP.TRC(SCCGWA, BPCTCALF.RC);
    }
    public void R000_GET_SALE_PROD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = WS_PROD_CD;
        S000_CALL_BPZPQPRD();
        WS_PROD_CD = BPCPQPRD.PARM_CODE;
    }
    public void R000_LINK_DDZSFBID() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSFBID);
        if (TDCACCRU.AC_NO.trim().length() > 0) {
            DDCSFBID.KEY.AC_NO = TDCACCRU.AC_NO;
        } else {
            DDCSFBID.KEY.AC_NO = TDCACCRU.AC_NO;
        }
        DDCSFBID.KEY.REF_NO = TDCACCRU.REF_NO;
        DDCSFBID.KEY.TYPE = '1';
        DDCSFBID.ORG_TYP = '1';
        DDCSFBID.EXP_DATE = TDCACCRU.EXP_DT;
        DDCSFBID.CRT_TL = SCCGWA.COMM_AREA.TL_ID;
        DDCSFBID.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDCSFBID.CRT_AUTH_TL = SCCGWA.COMM_AREA.SUP1_ID;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            || TDCACCRU.CANCEL_FLG == 'Y') {
            DDCSFBID.FUNC = 'A';
        } else {
            DDCSFBID.FUNC = 'F';
        }
        S000_CALL_DDZSFBID();
    }
    public void S000_LINK_CIZMGRPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SVR-CIZMGRPM", CICMGRPM);
        if (CICMGRPM.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICMGRPM.RC);
        }
    }
    public void R000_CHECK_MIAN_AC() throws IOException,SQLException,Exception {
    }
    public void R000_INST_GROPAMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.AC_NO = TDCACCRU.AC_NO;
        CEP.TRC(SCCGWA, TDCACCRU.AC_NO);
        TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRSMST.LBAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_STARTBR_GROPSMST();
        T000_READNEXT_GROPSMST();
        CEP.TRC(SCCGWA, WS_SMST_FLG);
        WS_INST = 1;
        while (WS_SMST_FLG != 'N') {
            CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
            CEP.TRC(SCCGWA, TDRSMST.LBAL);
            CEP.TRC(SCCGWA, TDRSMST.BAL);
            WS_BUKUAN_FLG = 'N';
            WS_ACO_GRP = TDRSMST.KEY.ACO_AC;
            if (TDRSMST.LBAL > TDRSMST.BAL 
                && TDCACCRU.GRPAUTO_FLG != 'Y') {
                WS_BUKUAN_FLG = 'Y';
            }
            if (TDCACCRU.GRPAUTO_FLG == 'Y') {
                WS_BUKUAN_FLG = 'Y';
            }
            if (TDRSMST.LBAL <= TDRSMST.BAL 
                && TDCACCRU.GRPAUTO_FLG != 'Y') {
                WS_SMST_FLG = 'N';
            } else {
                WS_INST_AMT = TDRSMST.LBAL - TDRSMST.BAL;
                CEP.TRC(SCCGWA, WS_INST_AMT);
                CEP.TRC(SCCGWA, WS_GROP_AMT);
                if (WS_INST_AMT > 0) {
                    CEP.TRC(SCCGWA, "A");
                    if (WS_GROP_AMT <= WS_INST_AMT) {
                        CEP.TRC(SCCGWA, "AA");
                        TDRSMST.BAL += WS_GROP_AMT;
                        WS_SMST_FLG = 'N';
                        IBS.init(SCCGWA, TDRGROP);
                        TDRGROP.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
                        TDRGROP.KEY.DRAW_DT = SCCGWA.COMM_AREA.AC_DATE;
                        T000_READUP_TDTGROP();
                        TDRGROP.REP_BAL += WS_GROP_AMT;
                        IBS.init(SCCGWA, TDRGGRP);
                        R000_GET_GROPSEQ();
                        TDRGGRP.KEY.SEQ = WS_TS_SEQ;
                        TDRGGRP.CDR_FLG = 'C';
                        TDRGGRP.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
                        TDRGGRP.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
                        TDRGGRP.ACO_AC = TDRSMST.KEY.ACO_AC;
                        TDRGGRP.AC_NO = TDCACCRU.AC_NO;
                        TDRGGRP.TX_AMT = WS_GROP_AMT;
                        TDRGGRP.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
                        TDRGGRP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
                        TDRGGRP.NEW_FLG = 'N';
                        TDRGGRP.CAN_FLG = '0';
                        if (TDRGGRP.TX_AMT > 0) {
                            T000_WRITE_TDTGGRP();
                        }
                        WS_GROP_AMT = 0;
                    } else {
                        CEP.TRC(SCCGWA, "AAA");
                        TDRSMST.BAL += WS_INST_AMT;
                        WS_GROP_AMT = WS_GROP_AMT - WS_INST_AMT;
                        IBS.init(SCCGWA, TDRGROP);
                        TDRGROP.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
                        TDRGROP.KEY.DRAW_DT = SCCGWA.COMM_AREA.AC_DATE;
                        T000_READUP_TDTGROP();
                        TDRGROP.REP_BAL += WS_INST_AMT;
                        IBS.init(SCCGWA, TDRGGRP);
                        R000_GET_GROPSEQ();
                        TDRGGRP.KEY.SEQ = WS_TS_SEQ;
                        TDRGGRP.CDR_FLG = 'C';
                        TDRGGRP.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
                        TDRGGRP.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
                        TDRGGRP.ACO_AC = TDRSMST.KEY.ACO_AC;
                        TDRGGRP.AC_NO = TDCACCRU.AC_NO;
                        TDRGGRP.TX_AMT = WS_INST_AMT;
                        TDRGGRP.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
                        TDRGGRP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
                        TDRGGRP.NEW_FLG = 'N';
                        TDRGGRP.CAN_FLG = '0';
                        T000_WRITE_TDTGGRP();
                    }
                    CEP.TRC(SCCGWA, WS_INST_AMT);
                    CEP.TRC(SCCGWA, WS_GROP_AMT);
                } else {
                    IBS.init(SCCGWA, TDRGROP);
                    TDRGROP.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
                    TDRGROP.KEY.DRAW_DT = SCCGWA.COMM_AREA.AC_DATE;
                    T000_READUP_TDTGROP();
                }
                if (TDCACCRU.GRPAUTO_FLG == 'Y') {
                    TDRGROP.REP_BAL = TDRGROP.AUTO_BAL;
                }
                if (TDRGROP.REP_BAL >= TDRGROP.AUTO_BAL + TDRGROP.HAND_BAL) {
                    TDRGROP.TYPE = "N";
                } else {
                    TDRGROP.TYPE = "Y";
                }
                T000_REWRITE_SMST();
                T000_REWRITE_TDTGROP();
            }
            T000_READNEXT_GROPSMST();
            if (WS_INST == 1) {
                WS_INST += 1;
            }
        }
        T000_ENDBR_GROPSMST();
        CEP.TRC(SCCGWA, WS_ACO_GRP);
        CEP.TRC(SCCGWA, WS_GROP_AMT);
        CEP.TRC(SCCGWA, WS_SMST_FLG);
        CEP.TRC(SCCGWA, WS_GRP_FLG);
        if (WS_GROP_AMT <= 0) {
            WS_FULL = 'Y';
        } else {
            WS_FULL = 'N';
        }
        if (WS_SMST_FLG == 'N' 
            && WS_GROP_AMT > 0) {
            if (WS_GRP_FLG != 'Y') {
                IBS.init(SCCGWA, TDRSMST);
                TDRSMST.AC_NO = TDCACCRU.AC_NO;
                T000_READU_SMST_FST();
                CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
                CEP.TRC(SCCGWA, TDRSMST.BAL);
                TDRSMST.AC_NO = TDRSMST.KEY.ACO_AC;
                T000_READU_SMST();
                TDRSMST.BAL += WS_GROP_AMT;
                T000_REWRITE_SMST();
                IBS.init(SCCGWA, TDRGGRP);
                R000_GET_GROPSEQ();
                TDRGGRP.KEY.SEQ = WS_TS_SEQ;
                TDRGGRP.CDR_FLG = 'C';
                TDRGGRP.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
                TDRGGRP.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
                TDRGGRP.ACO_AC = TDRSMST.KEY.ACO_AC;
                TDRGGRP.AC_NO = TDRSMST.AC_NO;
                TDRGGRP.TX_AMT = WS_GROP_AMT;
                TDRGGRP.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
                TDRGGRP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
                TDRGGRP.NEW_FLG = 'N';
                TDRGGRP.CAN_FLG = '0';
                T000_WRITE_TDTGGRP();
            } else {
                B500_GEN_ACOAC();
                IBS.init(SCCGWA, TDRGGRP);
                R000_GET_GROPSEQ();
                TDRGGRP.KEY.SEQ = WS_TS_SEQ;
                TDRGGRP.CDR_FLG = 'C';
                TDRGGRP.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
                TDRGGRP.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
                TDRGGRP.ACO_AC = TDCACCRU.ACO_AC;
                TDRGGRP.AC_NO = TDCACCRU.AC_NO;
                TDRGGRP.TX_AMT = WS_GROP_AMT;
                TDRGGRP.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
                TDRGGRP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
                TDRGGRP.NEW_FLG = 'Y';
                TDRGGRP.CAN_FLG = '0';
                T000_WRITE_TDTGGRP();
            }
        }
    }
    public void R000_AUTO_GROPAMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRGROP);
        TDRGROP.AC_NO = TDCACCRU.AC_NO;
        IBS.init(SCCGWA, TDRGROP);
        TDRGROP.AC_NO = TDCACCRU.AC_NO;
        TDRGROP.KEY.DRAW_DT = SCCGWA.COMM_AREA.AC_DATE;
        T000_STARTBR_TDTGROP_AUTO();
        T000_READNEXT_TDTGROP();
        while (WS_GROP_FLG != 'N') {
            CEP.TRC(SCCGWA, TDRGROP.HAND_BAL);
            CEP.TRC(SCCGWA, TDRGROP.AUTO_BAL);
            CEP.TRC(SCCGWA, TDRGROP.REP_BAL);
            CEP.TRC(SCCGWA, TDRGROP.KEY.ACO_AC);
            TDRGROP.REP_BAL += TDRGROP.AUTO_BAL;
            if (TDRGROP.REP_BAL >= TDRGROP.AUTO_BAL + TDRGROP.HAND_BAL) {
                TDRGROP.TYPE = "N";
            } else {
                TDRGROP.TYPE = "Y";
            }
            T000_REWRITE_TDTGROP();
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.KEY.ACO_AC = TDRGROP.KEY.ACO_AC;
            T000_READU_SMST();
            CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
            TDRSMST.BAL = TDRSMST.BAL + TDRGROP.AUTO_BAL;
            CEP.TRC(SCCGWA, TDRSMST.BAL);
            T000_REWRITE_SMST();
            T000_READNEXT_TDTGROP();
        }
    }
    public void R000_HAND_GROPAMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRGROP);
        TDRGROP.AC_NO = TDCACCRU.AC_NO;
        T000_COUNT_TDTGROP();
        CEP.TRC(SCCGWA, WS_GROP_CNT);
        IBS.init(SCCGWA, TDRGROP);
        TDRGROP.AC_NO = TDCACCRU.AC_NO;
        TDRGROP.KEY.DRAW_DT = SCCGWA.COMM_AREA.AC_DATE;
        T000_STARTBR_TDTGROP();
        T000_READNEXT_TDTGROP();
        if (WS_GROP_FLG == 'N') {
            CEP.TRC(SCCGWA, "GROP-NOTFND");
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.KEY.ACO_AC = TDCACCRU.ACO_AC;
            T000_READU_SMST();
            TDRSMST.BAL = TDRSMST.BAL + WS_GROP_AMT;
            T000_REWRITE_SMST();
        }
        while (WS_GROP_FLG != 'N' 
            && WS_GROP_AMT > 0 
            && WS_GROP_CNT != 0) {
            CEP.TRC(SCCGWA, WS_GROP_CNT);
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.KEY.ACO_AC = TDRGROP.KEY.ACO_AC;
            T000_READU_SMST();
            CEP.TRC(SCCGWA, "GROP-SMST");
            CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
            CEP.TRC(SCCGWA, WS_GROP_AMT);
            CEP.TRC(SCCGWA, TDRGROP.AUTO_BAL);
            CEP.TRC(SCCGWA, TDRGROP.HAND_BAL);
            CEP.TRC(SCCGWA, TDRGROP.REP_BAL);
            WS_GROP_AMT_X = WS_GROP_AMT;
            WS_GROP_AMT = WS_GROP_AMT - TDRGROP.AUTO_BAL - TDRGROP.HAND_BAL + TDRGROP.REP_BAL;
            CEP.TRC(SCCGWA, WS_GROP_AMT);
            if (WS_GROP_AMT > 0) {
                if (WS_GROP_CNT > 1) {
                    CEP.TRC(SCCGWA, "AAAAAAAAAAAAAAA");
                    TDRSMST.BAL = TDRSMST.BAL + TDRGROP.AUTO_BAL + TDRGROP.HAND_BAL - TDRGROP.REP_BAL;
                    CEP.TRC(SCCGWA, TDRSMST.BAL);
                    CEP.TRC(SCCGWA, TDRGROP.REP_BAL);
                    CEP.TRC(SCCGWA, TDRGROP.AUTO_BAL);
                    TDRGROP.REP_BAL = TDRGROP.AUTO_BAL + TDRGROP.HAND_BAL;
                } else {
                    CEP.TRC(SCCGWA, "AAAAAAAAAAAAAAA2");
                    TDRSMST.BAL = TDRSMST.BAL + WS_GROP_AMT_X;
                    CEP.TRC(SCCGWA, WS_GROP_AMT);
                    CEP.TRC(SCCGWA, TDRGROP.REP_BAL);
                    TDRGROP.REP_BAL = WS_GROP_AMT_X + TDRGROP.REP_BAL - WS_GROP_AMT;
                    CEP.TRC(SCCGWA, TDRGROP.REP_BAL);
                }
            } else {
                CEP.TRC(SCCGWA, "BBBBBBBBBB");
                CEP.TRC(SCCGWA, WS_GROP_AMT);
                CEP.TRC(SCCGWA, WS_GROP_AMT_X);
                if (WS_GROP_CNT == 1) {
                    CEP.TRC(SCCGWA, "BBBBBBBBBB1");
                    TDRGROP.REP_BAL = WS_GROP_AMT_X + TDRGROP.REP_BAL;
                    CEP.TRC(SCCGWA, TDRGROP.REP_BAL);
                    TDRSMST.BAL = TDRSMST.BAL + WS_GROP_AMT_X;
                } else {
                    CEP.TRC(SCCGWA, "BBBBBBBBBB2");
                    CEP.TRC(SCCGWA, WS_GROP_AMT_X);
                    TDRSMST.BAL = TDRSMST.BAL + WS_GROP_AMT_X;
                    TDRGROP.REP_BAL = TDRGROP.REP_BAL + WS_GROP_AMT_X;
                    CEP.TRC(SCCGWA, TDRSMST.BAL);
                }
                WS_GROP_AMT = 0;
            }
            T000_REWRITE_SMST();
            CEP.TRC(SCCGWA, "SSSSSS2");
            CEP.TRC(SCCGWA, TDRGROP.REP_BAL);
            CEP.TRC(SCCGWA, TDRGROP.AUTO_BAL);
            CEP.TRC(SCCGWA, TDRGROP.HAND_BAL);
            if (TDRGROP.REP_BAL == TDRGROP.AUTO_BAL + TDRGROP.HAND_BAL) {
                TDRGROP.TYPE = "N";
            } else {
                if (TDRGROP.REP_BAL < TDRGROP.AUTO_BAL + TDRGROP.HAND_BAL) {
                    TDRGROP.TYPE = "Y";
                }
            }
            T000_REWRITE_TDTGROP();
            CEP.TRC(SCCGWA, TDRGROP.TYPE);
            if (TDRGROP.TYPE.equalsIgnoreCase("N")) {
                WS_GROP_CNT = WS_GROP_CNT - 1;
            }
            T000_READNEXT_TDTGROP();
        }
        CEP.TRC(SCCGWA, WS_GROP_CNT);
        T000_ENDBR_TDTGROP();
    }
    public void R000_EXCHANGE_TERM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, REDEFINES188.WS_EX_TERM_TYP);
        CEP.TRC(SCCGWA, REDEFINES188.WS_EX_TERM_MTHS);
        if (REDEFINES188.WS_EX_TERM_TYP == 'D') {
            WS_EX_TERM_NUM.WS_EX_TERM_DAY = REDEFINES188.WS_EX_TERM_MTHS;
        } else if (REDEFINES188.WS_EX_TERM_TYP == 'M') {
            WS_EX_TERM_NUM.WS_EX_TERM_MTH = REDEFINES188.WS_EX_TERM_MTHS;
            WS_EX_TERM_NUM.WS_EX_TERM_DAY = WS_EX_TERM_NUM.WS_EX_TERM_MTH * 30;
        } else if (REDEFINES188.WS_EX_TERM_TYP == 'Y') {
            WS_EX_TERM_NUM.WS_EX_TERM_YEAR = REDEFINES188.WS_EX_TERM_MTHS;
            WS_EX_TERM_NUM.WS_EX_TERM_DAY = WS_EX_TERM_NUM.WS_EX_TERM_YEAR * 360;
        }
    }
    public void R000_GET_VIA_PROC() throws IOException,SQLException,Exception {
    }
    public void T000_COUNT_TDTGROP() throws IOException,SQLException,Exception {
        TDTGROP_RD = new DBParm();
        TDTGROP_RD.TableName = "TDTGROP";
        TDTGROP_RD.set = "WS-GROP-CNT=COUNT(*)";
        TDTGROP_RD.where = "AC_NO = :TDRGROP.AC_NO "
            + "AND TYPE < > 'N'";
        IBS.GROUP(SCCGWA, TDRGROP, this, TDTGROP_RD);
    }
    public void T000_READUP_TDTGROP() throws IOException,SQLException,Exception {
        TDTGROP_RD = new DBParm();
        TDTGROP_RD.TableName = "TDTGROP";
        TDTGROP_RD.where = "ACO_AC = :TDRGROP.KEY.ACO_AC "
            + "AND DRAW_DT = :TDRGROP.KEY.DRAW_DT";
        TDTGROP_RD.upd = true;
        IBS.READ(SCCGWA, TDRGROP, this, TDTGROP_RD);
    }
    public void T000_GROUP_TDTGROP() throws IOException,SQLException,Exception {
        TDTGROP_RD = new DBParm();
        TDTGROP_RD.TableName = "TDTGROP";
        TDTGROP_RD.set = "WS-REP-AMT=SUM(REP_BAL),WS-HAND-AMT=SUM(HAND_BAL)";
        TDTGROP_RD.where = "AC_NO = :TDRGROP.AC_NO";
        IBS.GROUP(SCCGWA, TDRGROP, this, TDTGROP_RD);
    }
    public void T000_GROUP_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.set = "WS-SMST-AMT=SUM(BAL)";
        TDTSMST_RD.where = "AC_NO = :TDRSMST.AC_NO "
            + "AND SUBSTR ( STSW , 52 , 1 ) < > '1' "
            + "AND ACO_STS = '0'";
        IBS.GROUP(SCCGWA, TDRSMST, this, TDTSMST_RD);
    }
    public void T000_GROP_SMSTYBT() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.set = "WS-YBT-LINE=COUNT(*)";
        TDTSMST_RD.where = "AC_NO = :TDRSMST.AC_NO "
            + "AND ACO_STS = '0'";
        IBS.GROUP(SCCGWA, TDRSMST, this, TDTSMST_RD);
    }
    public void T000_REWRITE_TDTGROP() throws IOException,SQLException,Exception {
        TDRGROP.OWNER_BK = BPCPORUP.DATA_INFO.BR;
        TDRGROP.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRGROP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRGROP.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTGROP_RD = new DBParm();
        TDTGROP_RD.TableName = "TDTGROP";
        IBS.REWRITE(SCCGWA, TDRGROP, TDTGROP_RD);
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READUP_SMST_GRP() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.where = "AC_NO = :TDRSMST.AC_NO "
            + "AND VAL_DATE = :TDRSMST.OPEN_DATE "
            + "AND ACO_STS = '0' "
            + "AND SUBSTR ( STSW , 52 , 1 ) < > '1'";
        TDTSMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRSMST, this, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_GRP_FLG = 'N';
            WS_ACO_XXT = TDRSMST.KEY.ACO_AC;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_GRP_FLG = 'Y';
        } else {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_OTHER_ERR);
        }
        if (TDCACCRU.GRPAUTO_FLG == 'Y') {
            WS_GRP_FLG = 'N';
        }
    }
    public void T000_READU_SMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READU_SMST_FST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.where = "AC_NO = :TDRSMST.AC_NO "
            + "AND SUBSTR ( STSW , 52 , 1 ) < > '1'";
        TDTSMST_RD.fst = true;
        TDTSMST_RD.order = "VAL_DATE DESC";
        IBS.READ(SCCGWA, TDRSMST, this, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_TDRDWHH() throws IOException,SQLException,Exception {
        TDTDWHH_RD = new DBParm();
        TDTDWHH_RD.TableName = "TDTDWHH";
        TDTDWHH_RD.where = "ACO_AC = :TDRDWHH.KEY.ACO_AC";
        TDTDWHH_RD.fst = true;
        TDTDWHH_RD.order = "PAY_SEQ DESC";
        IBS.READ(SCCGWA, TDRDWHH, this, TDTDWHH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CDI_NUM = (short) (TDRDWHH.KEY.PAY_SEQ + 1);
        } else {
            WS_CDI_NUM = 1;
        }
    }
    public void T000_READ_TDTSMST_MR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCACCRU.AC_NO);
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.where = "AC_NO = :TDRSMST.AC_NO "
            + "AND SUBSTR ( STSW , 52 , 1 ) = '1'";
        TDTSMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRSMST, this, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_CHECK_SMST_SW() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCACCRU.AC_NO);
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.where = "AC_NO = :TDRSMST.AC_NO "
            + "AND SUBSTR ( STSW , 52 , 1 ) = '1'";
        IBS.READ(SCCGWA, TDRSMST, this, TDTSMST_RD);
    }
    public void T000_READ_SMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_SMST_GK() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.where = "AC_NO = :TDRSMST.AC_NO";
        IBS.READ(SCCGWA, TDRSMST, this, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READU_CMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        TDTCMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRCMST, TDTCMST_RD);
        CEP.TRC(SCCGWA, "AAAAAAAAAA");
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_REWRITE_SMST() throws IOException,SQLException,Exception {
        TDRSMST.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRSMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            TDRSMST.CLO_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(52 - 1, 52 + 1 - 1).equalsIgnoreCase("1")) {
            R000_7_X_24H_BAL_UPD();
        }
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.REWRITE(SCCGWA, TDRSMST, TDTSMST_RD);
    }
    public void T000_WRITE_CMST() throws IOException,SQLException,Exception {
        TDRCMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRCMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRCMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        IBS.WRITE(SCCGWA, TDRCMST, TDTCMST_RD);
    }
    public void T000_READ_CMST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRCMST.KEY.AC_NO);
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        IBS.READ(SCCGWA, TDRCMST, TDTCMST_RD);
    }
    public void T000_READU_GROP_CANCEL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRGROP.KEY.ACO_AC);
        TDTGROP_RD = new DBParm();
        TDTGROP_RD.TableName = "TDTGROP";
        TDTGROP_RD.upd = true;
        IBS.READ(SCCGWA, TDRGROP, TDTGROP_RD);
    }
    public void T000_REWRITE_GROP_CANCEL() throws IOException,SQLException,Exception {
        TDRGROP.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRGROP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRGROP.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTGROP_RD = new DBParm();
        TDTGROP_RD.TableName = "TDTGROP";
        IBS.REWRITE(SCCGWA, TDRGROP, TDTGROP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_CGROP_FLG = 'N';
        } else {
            WS_CGROP_FLG = 'Y';
        }
    }
    public void T000_READUP_CMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        TDTCMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRCMST, TDTCMST_RD);
    }
    public void T000_REWRITE_CMST() throws IOException,SQLException,Exception {
        TDRCMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRCMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRCMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        IBS.REWRITE(SCCGWA, TDRCMST, TDTCMST_RD);
    }
    public void T000_WRITE_SMST() throws IOException,SQLException,Exception {
        TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRSMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.WRITE(SCCGWA, TDRSMST, TDTSMST_RD);
    }
    public void T00_READ_TDTOTHE() throws IOException,SQLException,Exception {
        TDTOTHE_RD = new DBParm();
        TDTOTHE_RD.TableName = "TDTOTHE";
        TDTOTHE_RD.where = "ACTI_NO = :TDROTHE.KEY.ACTI_NO "
            + "AND SUC_FLG < > '1'";
        IBS.READ(SCCGWA, TDROTHE, this, TDTOTHE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
        }
    }
    public void T000_READUP_HMST() throws IOException,SQLException,Exception {
        TDTHMST_RD = new DBParm();
        TDTHMST_RD.TableName = "TDTHMST";
        TDTHMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRHMST, TDTHMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_HMST_FLAG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_HMST_FLAG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTHMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_REWRITE_HMST() throws IOException,SQLException,Exception {
        TDTHMST_RD = new DBParm();
        TDTHMST_RD.TableName = "TDTHMST";
        IBS.REWRITE(SCCGWA, TDRHMST, TDTHMST_RD);
    }
    public void T000_WRITE_HMST() throws IOException,SQLException,Exception {
        TDTHMST_RD = new DBParm();
        TDTHMST_RD.TableName = "TDTHMST";
        IBS.WRITE(SCCGWA, TDRHMST, TDTHMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_HMST_FLAG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_HMST_FLAG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_HMST_DUPKEY;
            S000_ERR_MSG_PROC();
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTHMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_WRITE_IREV() throws IOException,SQLException,Exception {
        TDRIREV.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRIREV.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRIREV.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTIREV_RD = new DBParm();
        TDTIREV_RD.TableName = "TDTIREV";
        IBS.WRITE(SCCGWA, TDRIREV, TDTIREV_RD);
    }
    public void T000_WRITE_IRH() throws IOException,SQLException,Exception {
        TDTIRH_RD = new DBParm();
        TDTIRH_RD.TableName = "TDTIRH";
        IBS.WRITE(SCCGWA, TDRIRH, TDTIRH_RD);
    }
    public void T000_WRITE_TDTDWHH() throws IOException,SQLException,Exception {
        TDRDWHH.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRDWHH.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRDWHH.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTDWHH_RD = new DBParm();
        TDTDWHH_RD.TableName = "TDTDWHH";
        IBS.WRITE(SCCGWA, TDRDWHH, TDTDWHH_RD);
    }
    public void B060_READU_WHH_CANCEL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRDWHH);
        TDRDWHH.JRN_NO = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
        TDRDWHH.KEY.ACO_AC = TDCACCRU.ACO_AC;
        T000_READU_TDTDWHH();
    }
    public void R010_CHECK_STS_TBL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = TDCACCRU.INT_AC;
        CEP.TRC(SCCGWA, CICQACRI.DATA.AGR_NO);
        S000_CALL_CIZQACRI();
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = TDCACCRU.INT_AC;
        S000_CALL_CIZACCU();
        if (CICQACRI.O_DATA.O_CI_TYP == '2') {
            IBS.init(SCCGWA, DCCPFTCK);
            DCCPFTCK.VAL.CARD_NO = TDCACCRU.INT_AC;
            DCCPFTCK.VAL.TXN_TYPE = "04";
            DCCPFTCK.VAL.TXN_CCY = TDCACCRU.CCY;
            DCCPFTCK.FUNCTION_CODE = 'S';
            DCCPFTCK.TRK2_DAT = TDCACCRU.INT_TRK2;
            DCCPFTCK.TRK3_DAT = TDCACCRU.INT_TRK3;
            CEP.TRC(SCCGWA, TDCACCRU.CI_NO);
            CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
            if (TDCACCRU.CI_NO.equalsIgnoreCase(CICACCU.DATA.CI_NO)) {
                DCCPFTCK.VAL.SNAME_TRF_FLG = 'Y';
            } else {
                DCCPFTCK.VAL.DNAME_TRF_FLG = 'Y';
            }
            S000_CALL_DCZPFTCK();
        } else {
            IBS.init(SCCGWA, BPCFCSTS);
            BPCFCSTS.AP_MMO = "DD";
            BPCFCSTS.TBL_NO = "0002";
            if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
            JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
            for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
            if (DDCSCINM.OUTPUT_DATA.AC_STS_WORD == null) DDCSCINM.OUTPUT_DATA.AC_STS_WORD = "";
            JIBS_tmp_int = DDCSCINM.OUTPUT_DATA.AC_STS_WORD.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DDCSCINM.OUTPUT_DATA.AC_STS_WORD += " ";
            BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 101 - 1) + DDCSCINM.OUTPUT_DATA.AC_STS_WORD + BPCFCSTS.STATUS_WORD.substring(101 + 99 - 1);
            CEP.TRC(SCCGWA, BPCFCSTS.AP_MMO);
            CEP.TRC(SCCGWA, BPCFCSTS.TBL_NO);
            CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD);
            S000_CALL_BPZFCSTS();
        }
    }
    public void R000_GET_TERM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCQPMP);
        TDCQPMP.FUNC = 'I';
        TDCQPMP.PROD_CD = BPCPQPRD.PARM_CODE;
        TDCQPMP.DAT_PTR = TDCPROD;
        S000_CALL_TDZQPMP();
        TDCPRDP.PRDMO_CD = TDCPROD.PRDO_CDM;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.TXN_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.TXN_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.INT_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.INT_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.EXP_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.EXP_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.OTH_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.OTH_PRM);
        WS_CCY_FOUND = ' ';
        CEP.TRC(SCCGWA, WS_CCY_FOUND);
        for (WS_CNT = 1; WS_CNT <= 24 
            && WS_CCY_FOUND != 'Y'; WS_CNT += 1) {
            if (TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].MIN_CCYC.equalsIgnoreCase(TDCACCRU.CCY)) {
                WS_TERM_X = TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].TERM;
                WS_CCY_FOUND = 'Y';
            }
        }
        CEP.TRC(SCCGWA, WS_TERM_X);
        if (WS_TERM_X == null) WS_TERM_X = "";
        JIBS_tmp_int = WS_TERM_X.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) WS_TERM_X += " ";
        if (WS_TERM_X.substring(0, 1).equalsIgnoreCase("Y")) {
            REDEFINES93.WS_TERM_ONE_DAY = "D001";
            WS_TERM_DESC = IBS.CLS2CPY(SCCGWA, REDEFINES93);
        }
        CEP.TRC(SCCGWA, "222X");
        if (WS_TERM_X == null) WS_TERM_X = "";
        JIBS_tmp_int = WS_TERM_X.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) WS_TERM_X += " ";
        if (WS_TERM_X.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("Y")) {
            REDEFINES93.WS_TERM_SEVEN_DAY = "D007";
            WS_TERM_DESC = IBS.CLS2CPY(SCCGWA, REDEFINES93);
        }
        CEP.TRC(SCCGWA, "333X");
        if (WS_TERM_X == null) WS_TERM_X = "";
        JIBS_tmp_int = WS_TERM_X.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) WS_TERM_X += " ";
        if (WS_TERM_X.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("Y")) {
            REDEFINES93.WS_TERM_ONE_MONTH = "M001";
            WS_TERM_DESC = IBS.CLS2CPY(SCCGWA, REDEFINES93);
        }
        CEP.TRC(SCCGWA, "444X");
        if (WS_TERM_X == null) WS_TERM_X = "";
        JIBS_tmp_int = WS_TERM_X.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) WS_TERM_X += " ";
        if (WS_TERM_X.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("Y")) {
            REDEFINES93.WS_TERM_THREE_MONTH = "M003";
            WS_TERM_DESC = IBS.CLS2CPY(SCCGWA, REDEFINES93);
        }
        if (WS_TERM_X == null) WS_TERM_X = "";
        JIBS_tmp_int = WS_TERM_X.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) WS_TERM_X += " ";
        if (WS_TERM_X.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("Y")) {
            REDEFINES93.WS_TERM_SIX_MONTH = "M006";
            WS_TERM_DESC = IBS.CLS2CPY(SCCGWA, REDEFINES93);
        }
        CEP.TRC(SCCGWA, "555X");
        if (WS_TERM_X == null) WS_TERM_X = "";
        JIBS_tmp_int = WS_TERM_X.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) WS_TERM_X += " ";
        if (WS_TERM_X.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("Y")) {
            REDEFINES93.WS_TERM_ONE_YEAR = "Y001";
            WS_TERM_DESC = IBS.CLS2CPY(SCCGWA, REDEFINES93);
        }
        CEP.TRC(SCCGWA, "666X");
        if (WS_TERM_X == null) WS_TERM_X = "";
        JIBS_tmp_int = WS_TERM_X.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) WS_TERM_X += " ";
        if (WS_TERM_X.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("Y")) {
            REDEFINES93.WS_TERM_TWO_YEAR = "Y002";
            WS_TERM_DESC = IBS.CLS2CPY(SCCGWA, REDEFINES93);
        }
        CEP.TRC(SCCGWA, "777X");
        if (WS_TERM_X == null) WS_TERM_X = "";
        JIBS_tmp_int = WS_TERM_X.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) WS_TERM_X += " ";
        if (WS_TERM_X.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("Y")) {
            REDEFINES93.WS_TERM_THREE_YEAR = "Y003";
            WS_TERM_DESC = IBS.CLS2CPY(SCCGWA, REDEFINES93);
        }
        CEP.TRC(SCCGWA, "888X");
        if (WS_TERM_X == null) WS_TERM_X = "";
        JIBS_tmp_int = WS_TERM_X.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) WS_TERM_X += " ";
        if (WS_TERM_X.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("Y")) {
            REDEFINES93.WS_TERM_FIVE_YEAR = "Y005";
            WS_TERM_DESC = IBS.CLS2CPY(SCCGWA, REDEFINES93);
        }
        CEP.TRC(SCCGWA, "999X");
        if (WS_TERM_X == null) WS_TERM_X = "";
        JIBS_tmp_int = WS_TERM_X.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) WS_TERM_X += " ";
        if (WS_TERM_X.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("Y")) {
            REDEFINES93.WS_TERM_SIX_YEAR = "Y006";
            WS_TERM_DESC = IBS.CLS2CPY(SCCGWA, REDEFINES93);
        }
        CEP.TRC(SCCGWA, "000X");
        if (WS_TERM_X == null) WS_TERM_X = "";
        JIBS_tmp_int = WS_TERM_X.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) WS_TERM_X += " ";
        if (WS_TERM_X.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("Y")) {
            REDEFINES93.WS_TERM_NOT_STANDARD = "S000";
            WS_TERM_DESC = IBS.CLS2CPY(SCCGWA, REDEFINES93);
        }
        CEP.TRC(SCCGWA, "AAAX");
        CEP.TRC(SCCGWA, REDEFINES93.WS_TERM_ONE_DAY);
        CEP.TRC(SCCGWA, REDEFINES93.WS_TERM_SEVEN_DAY);
        CEP.TRC(SCCGWA, REDEFINES93.WS_TERM_ONE_MONTH);
        CEP.TRC(SCCGWA, REDEFINES93.WS_TERM_THREE_MONTH);
        CEP.TRC(SCCGWA, REDEFINES93.WS_TERM_SIX_MONTH);
        CEP.TRC(SCCGWA, REDEFINES93.WS_TERM_ONE_YEAR);
        CEP.TRC(SCCGWA, REDEFINES93.WS_TERM_TWO_YEAR);
        CEP.TRC(SCCGWA, REDEFINES93.WS_TERM_THREE_YEAR);
        CEP.TRC(SCCGWA, REDEFINES93.WS_TERM_FIVE_YEAR);
        CEP.TRC(SCCGWA, REDEFINES93.WS_TERM_SIX_YEAR);
        CEP.TRC(SCCGWA, REDEFINES93.WS_TERM_NOT_STANDARD);
        CEP.TRC(SCCGWA, TDCACCRU.TERM);
        if (!TDCACCRU.TERM.equalsIgnoreCase(REDEFINES93.WS_TERM_ONE_DAY) 
            && !TDCACCRU.TERM.equalsIgnoreCase(REDEFINES93.WS_TERM_SEVEN_DAY) 
            && !TDCACCRU.TERM.equalsIgnoreCase(REDEFINES93.WS_TERM_ONE_MONTH) 
            && !TDCACCRU.TERM.equalsIgnoreCase(REDEFINES93.WS_TERM_THREE_MONTH) 
            && !TDCACCRU.TERM.equalsIgnoreCase(REDEFINES93.WS_TERM_SIX_MONTH) 
            && !TDCACCRU.TERM.equalsIgnoreCase(REDEFINES93.WS_TERM_ONE_YEAR) 
            && !TDCACCRU.TERM.equalsIgnoreCase(REDEFINES93.WS_TERM_TWO_YEAR) 
            && !TDCACCRU.TERM.equalsIgnoreCase(REDEFINES93.WS_TERM_THREE_YEAR) 
            && !TDCACCRU.TERM.equalsIgnoreCase(REDEFINES93.WS_TERM_FIVE_YEAR) 
            && !TDCACCRU.TERM.equalsIgnoreCase(REDEFINES93.WS_TERM_SIX_YEAR) 
            && !TDCACCRU.TERM.equalsIgnoreCase(REDEFINES93.WS_TERM_NOT_STANDARD) 
            && !TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].TERM1.equalsIgnoreCase(TDCACCRU.TERM) 
            && !TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].TERM2.equalsIgnoreCase(TDCACCRU.TERM) 
            && !TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].TERM3.equalsIgnoreCase(TDCACCRU.TERM) 
            && !TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].TERM4.equalsIgnoreCase(TDCACCRU.TERM) 
            && !TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].TERM5.equalsIgnoreCase(TDCACCRU.TERM) 
            && !TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].TERM6.equalsIgnoreCase(TDCACCRU.TERM) 
            && ((!TDCACCRU.TERM.equalsIgnoreCase("S000") 
            && TDCACCRU.TERM.trim().length() > 0) 
            && (TDCPIOD.EXP_PRM.DOCU_TYP != '0' 
            && TDCPIOD.EXP_PRM.DOCU_TYP != 'Y'))) {
            CEP.TRC(SCCGWA, "BBBX");
            WS_ERROR = 'Y';
        }
    }
    public void T000_READU_TDTDWHH() throws IOException,SQLException,Exception {
        TDTDWHH_RD = new DBParm();
        TDTDWHH_RD.TableName = "TDTDWHH";
        TDTDWHH_RD.where = "JRN_NO = :TDRDWHH.JRN_NO "
            + "AND ACO_AC = :TDRDWHH.KEY.ACO_AC";
        TDTDWHH_RD.upd = true;
        IBS.READ(SCCGWA, TDRDWHH, this, TDTDWHH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void B070_REWRITE_WHH_CANCEL() throws IOException,SQLException,Exception {
        TDRDWHH.STATUS = 'R';
        TDRDWHH.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRDWHH.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_TDTDWHH();
    }
    public void B510_CHECK_INSTR_TERM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, REDEFINES93.WS_TERM_ONE_DAY);
        CEP.TRC(SCCGWA, REDEFINES93.WS_TERM_SEVEN_DAY);
        CEP.TRC(SCCGWA, REDEFINES93.WS_TERM_ONE_MONTH);
        CEP.TRC(SCCGWA, REDEFINES93.WS_TERM_THREE_MONTH);
        CEP.TRC(SCCGWA, REDEFINES93.WS_TERM_SIX_MONTH);
        CEP.TRC(SCCGWA, REDEFINES93.WS_TERM_ONE_YEAR);
        CEP.TRC(SCCGWA, REDEFINES93.WS_TERM_TWO_YEAR);
        CEP.TRC(SCCGWA, REDEFINES93.WS_TERM_THREE_YEAR);
        CEP.TRC(SCCGWA, REDEFINES93.WS_TERM_FIVE_YEAR);
        CEP.TRC(SCCGWA, REDEFINES93.WS_TERM_SIX_YEAR);
        CEP.TRC(SCCGWA, REDEFINES93.WS_TERM_NOT_STANDARD);
        CEP.TRC(SCCGWA, TDCACCRU.INSTR_TERM);
        if (!TDCACCRU.INSTR_TERM.equalsIgnoreCase(REDEFINES93.WS_TERM_ONE_DAY) 
            && !TDCACCRU.INSTR_TERM.equalsIgnoreCase(REDEFINES93.WS_TERM_SEVEN_DAY) 
            && !TDCACCRU.INSTR_TERM.equalsIgnoreCase(REDEFINES93.WS_TERM_ONE_MONTH) 
            && !TDCACCRU.INSTR_TERM.equalsIgnoreCase(REDEFINES93.WS_TERM_THREE_MONTH) 
            && !TDCACCRU.INSTR_TERM.equalsIgnoreCase(REDEFINES93.WS_TERM_SIX_MONTH) 
            && !TDCACCRU.INSTR_TERM.equalsIgnoreCase(REDEFINES93.WS_TERM_ONE_YEAR) 
            && !TDCACCRU.INSTR_TERM.equalsIgnoreCase(REDEFINES93.WS_TERM_TWO_YEAR) 
            && !TDCACCRU.INSTR_TERM.equalsIgnoreCase(REDEFINES93.WS_TERM_THREE_YEAR) 
            && !TDCACCRU.INSTR_TERM.equalsIgnoreCase(REDEFINES93.WS_TERM_FIVE_YEAR) 
            && !TDCACCRU.INSTR_TERM.equalsIgnoreCase(REDEFINES93.WS_TERM_SIX_YEAR) 
            && !TDCACCRU.INSTR_TERM.equalsIgnoreCase(REDEFINES93.WS_TERM_NOT_STANDARD)) {
            if (REDEFINES93.WS_TERM_NOT_STANDARD.equalsIgnoreCase("S000")) {
                CEP.TRC(SCCGWA, TDCPRDP.OTH_PRM.CCY_INF[WS_W-1].TERM1);
                if (!TDCACCRU.INSTR_TERM.equalsIgnoreCase(TDCPRDP.OTH_PRM.CCY_INF[WS_W-1].TERM1) 
                    && !TDCACCRU.INSTR_TERM.equalsIgnoreCase(TDCPRDP.OTH_PRM.CCY_INF[WS_W-1].TERM2) 
                    && !TDCACCRU.INSTR_TERM.equalsIgnoreCase(TDCPRDP.OTH_PRM.CCY_INF[WS_W-1].TERM3) 
                    && !TDCACCRU.INSTR_TERM.equalsIgnoreCase(TDCPRDP.OTH_PRM.CCY_INF[WS_W-1].TERM4) 
                    && !TDCACCRU.INSTR_TERM.equalsIgnoreCase(TDCPRDP.OTH_PRM.CCY_INF[WS_W-1].TERM5) 
                    && !TDCACCRU.INSTR_TERM.equalsIgnoreCase(TDCPRDP.OTH_PRM.CCY_INF[WS_W-1].TERM6)) {
                    CEP.TRC(SCCGWA, "TEST5");
                    WS_MSGID = TDCMSG_ERROR_MSG.TD_TERM_NOT_ALLOW;
                    S000_ERR_MSG_PROC();
                } else {
                    WS_NOT_STANDARD_FLG = 'Y';
                }
            } else {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_TERM_NOT_ALLOW;
                S000_ERR_MSG_PROC();
            }
        }
        CEP.TRC(SCCGWA, TDCPRDP.OTH_PRM.MAX_TERM);
        CEP.TRC(SCCGWA, TDCACCRU.INSTR_TERM);
        if (!TDCACCRU.INSTR_TERM.equalsIgnoreCase("S000")) {
            if (!REDEFINES93.WS_TERM_NOT_STANDARD.equalsIgnoreCase("S000")) {
                if (TDCACCRU.INSTR_TERM.compareTo(TDCPRDP.OTH_PRM.MAX_TERM) > 0 
                    && TDCPRDP.OTH_PRM.MAX_TERM.trim().length() > 0) {
                    WS_MSGID = TDCMSG_ERROR_MSG.TD_NOT_GREAT_MAX_TERM;
                    S000_ERR_MSG_PROC();
                }
                if (TDCACCRU.INSTR_TERM.compareTo(TDCPRDP.OTH_PRM.MIN_TERM) < 0 
                    && TDCPRDP.OTH_PRM.MIN_TERM.trim().length() > 0) {
                    WS_MSGID = TDCMSG_ERROR_MSG.TD_NOT_LESS_MIN_TERM;
                    S000_ERR_MSG_PROC();
                }
            } else {
                CEP.TRC(SCCGWA, TDCPRDP.OTH_PRM.MAX_TERM);
                CEP.TRC(SCCGWA, TDCPRDP.OTH_PRM.MIN_TERM);
                WS_EX_TERM = TDCACCRU.INSTR_TERM;
                IBS.CPY2CLS(SCCGWA, WS_EX_TERM, REDEFINES188);
                R000_EXCHANGE_TERM();
                WS_ACCRU_TERM = WS_EX_TERM_NUM.WS_EX_TERM_DAY;
                CEP.TRC(SCCGWA, "INPUT-TERM");
                CEP.TRC(SCCGWA, WS_ACCRU_TERM);
                if (TDCPRDP.OTH_PRM.MAX_TERM.trim().length() > 0) {
                    WS_EX_TERM = TDCPRDP.OTH_PRM.MAX_TERM;
                    IBS.CPY2CLS(SCCGWA, WS_EX_TERM, REDEFINES188);
                    R000_EXCHANGE_TERM();
                    WS_PRDP_TERM = WS_EX_TERM_NUM.WS_EX_TERM_DAY;
                    CEP.TRC(SCCGWA, "MAX-TERM");
                    CEP.TRC(SCCGWA, WS_PRDP_TERM);
                    if (WS_ACCRU_TERM > WS_PRDP_TERM) {
                        WS_MSGID = TDCMSG_ERROR_MSG.TD_NOT_GREAT_MAX_TERM;
                        S000_ERR_MSG_PROC();
                    }
                }
                if (TDCPRDP.OTH_PRM.MIN_TERM.trim().length() > 0) {
                    WS_EX_TERM = TDCPRDP.OTH_PRM.MIN_TERM;
                    IBS.CPY2CLS(SCCGWA, WS_EX_TERM, REDEFINES188);
                    R000_EXCHANGE_TERM();
                    WS_PRDP_TERM = WS_EX_TERM_NUM.WS_EX_TERM_DAY;
                    CEP.TRC(SCCGWA, "MIN-TERM");
                    CEP.TRC(SCCGWA, WS_PRDP_TERM);
                    if (WS_ACCRU_TERM < WS_PRDP_TERM) {
                        WS_MSGID = TDCMSG_ERROR_MSG.TD_NOT_LESS_MIN_TERM;
                        S000_ERR_MSG_PROC();
                    }
                }
            }
        }
    }
    public void B740_WRT_TDTAINT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRAINT);
        TDRAINT.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
        TDRAINT.PRV_RAT = TDCACCRU.PRV_RAT;
        TDRAINT.OVE_RAT = TDCACCRU.OVE_RAT;
        TDRAINT.DUE_RAT = TDCACCRU.DUE_RAT;
        TDRAINT.OWNER_BK = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRAINT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRAINT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRAINT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        TDRAINT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_TDTAINT();
    }
    public void R000_GET_GROPSEQ() throws IOException,SQLException,Exception {
        TDRGGRP.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        TDRGGRP.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, TDRGGRP.KEY.JRNNO);
        CEP.TRC(SCCGWA, TDRGGRP.KEY.TR_DATE);
        T000_READ_TDTGGRP_MAX();
        WS_TS_SEQ += 1;
    }
    public void R000_CHK_IF_DEF_PRD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRPMPD);
        CEP.TRC(SCCGWA, WS_PROD_CD_PMPD);
        CEP.TRC(SCCGWA, TDCACCRU.CCY);
        TDRPMPD.KEY.IBS_AC_BK = K_AC_BK;
        TDRPMPD.KEY.PRD_CD = WS_PROD_CD_PMPD;
        TDRPMPD.MIN_CCYC = TDCACCRU.CCY;
        T000_GROP_TDTPMPD();
    }
    public void T000_GROP_TDTPMPD() throws IOException,SQLException,Exception {
        WS_CCY_DEF_FLG = ' ';
        TDTPMPD_RD = new DBParm();
        TDTPMPD_RD.TableName = "TDTPMPD";
        TDTPMPD_RD.set = "WS-CCY-CNT=COUNT(*)";
        TDTPMPD_RD.where = "IBS_AC_BK = :TDRPMPD.KEY.IBS_AC_BK "
            + "AND PRD_CD = :TDRPMPD.KEY.PRD_CD "
            + "AND PRM_TYP = 'M' "
            + "AND MIN_CCYC = :TDRPMPD.MIN_CCYC";
        IBS.GROUP(SCCGWA, TDRPMPD, this, TDTPMPD_RD);
        CEP.TRC(SCCGWA, WS_CCY_CNT);
        if (WS_CCY_CNT > 0) {
            WS_CCY_DEF_FLG = 'Y';
        } else {
            WS_CCY_DEF_FLG = 'N';
        }
    }
    public void T000_WRITE_TDTAINT() throws IOException,SQLException,Exception {
        TDRAINT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRAINT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRAINT.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTAINT_RD = new DBParm();
        TDTAINT_RD.TableName = "TDTAINT";
        IBS.WRITE(SCCGWA, TDRAINT, TDTAINT_RD);
    }
    public void T000_REWRITE_TDTDWHH() throws IOException,SQLException,Exception {
        TDTDWHH_RD = new DBParm();
        TDTDWHH_RD.TableName = "TDTDWHH";
        IBS.REWRITE(SCCGWA, TDRDWHH, TDTDWHH_RD);
    }
    public void T000_REWRITE_BVT() throws IOException,SQLException,Exception {
        TDRBVT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRBVT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRBVT.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        IBS.REWRITE(SCCGWA, TDRBVT, TDTBVT_RD);
    }
    public void T000_WRITE_INST() throws IOException,SQLException,Exception {
        TDRINST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRINST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRINST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTINST_RD = new DBParm();
        TDTINST_RD.TableName = "TDTINST";
        IBS.WRITE(SCCGWA, TDRINST, TDTINST_RD);
    }
    public void T000_WRITE_INTC() throws IOException,SQLException,Exception {
        TDRINTC.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRINTC.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRINTC.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTINTC_RD = new DBParm();
        TDTINTC_RD.TableName = "TDTINTC";
        IBS.WRITE(SCCGWA, TDRINTC, TDTINTC_RD);
    }
    public void T000_WRITE_CDI() throws IOException,SQLException,Exception {
        TDRCDI.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRCDI.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRCDI.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTCDI_RD = new DBParm();
        TDTCDI_RD.TableName = "TDTCDI";
        IBS.WRITE(SCCGWA, TDRCDI, TDTCDI_RD);
    }
    public void T000_READU_CDI() throws IOException,SQLException,Exception {
        TDTCDI_RD = new DBParm();
        TDTCDI_RD.TableName = "TDTCDI";
        TDTCDI_RD.upd = true;
        IBS.READ(SCCGWA, TDRCDI, TDTCDI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_TDTGGRP_MAX() throws IOException,SQLException,Exception {
        TDTGGRP_RD = new DBParm();
        TDTGGRP_RD.TableName = "TDTGGRP";
        TDTGGRP_RD.where = "TR_DATE = :TDRGGRP.KEY.TR_DATE "
            + "AND JRNNO = :TDRGGRP.KEY.JRNNO";
        TDTGGRP_RD.fst = true;
        TDTGGRP_RD.order = "SEQ DESC";
        IBS.READ(SCCGWA, TDRGGRP, this, TDTGGRP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TS_SEQ = 0;
        } else {
            WS_TS_SEQ = TDRGGRP.KEY.SEQ;
        }
    }
    public void T000_REWRITE_CDI() throws IOException,SQLException,Exception {
        TDRCDI.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRCDI.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRCDI.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRCDI.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTCDI_RD = new DBParm();
        TDTCDI_RD.TableName = "TDTCDI";
        IBS.REWRITE(SCCGWA, TDRCDI, TDTCDI_RD);
    }
    public void T000_READ_MSREL() throws IOException,SQLException,Exception {
    }
    public void T000_READU_YBTP() throws IOException,SQLException,Exception {
        TDTYBTP_RD = new DBParm();
        TDTYBTP_RD.TableName = "TDTYBTP";
        TDTYBTP_RD.where = "AC_NO = :TDRYBTP.KEY.AC_NO "
            + "AND AC_SEQ = :TDRYBTP.KEY.AC_SEQ "
            + "AND PART_NUM = :TDRYBTP.PART_NUM "
            + "AND PRT_STS <= '2'";
        TDTYBTP_RD.upd = true;
        IBS.READ(SCCGWA, TDRYBTP, this, TDTYBTP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_REWRITE_YBTP() throws IOException,SQLException,Exception {
        TDRYBTP.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRYBTP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRYBTP.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTYBTP_RD = new DBParm();
        TDTYBTP_RD.TableName = "TDTYBTP";
        IBS.REWRITE(SCCGWA, TDRYBTP, TDTYBTP_RD);
    }
    public void T000_WRITE_YBTP() throws IOException,SQLException,Exception {
        TDRYBTP.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRYBTP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRYBTP.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTYBTP_RD = new DBParm();
        TDTYBTP_RD.TableName = "TDTYBTP";
        IBS.WRITE(SCCGWA, TDRYBTP, TDTYBTP_RD);
    }
    public void T000_WRITE_PBP() throws IOException,SQLException,Exception {
        TDRPBP.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRPBP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRPBP.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTPBP_RD = new DBParm();
        TDTPBP_RD.TableName = "TDTPBP";
        IBS.WRITE(SCCGWA, TDRPBP, TDTPBP_RD);
    }
    public void T000_WRITE_BVT() throws IOException,SQLException,Exception {
        TDRBVT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRBVT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRBVT.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        IBS.WRITE(SCCGWA, TDRBVT, TDTBVT_RD);
    }
    public void T000_WRITE_TDTGGRP() throws IOException,SQLException,Exception {
        if (TDCACCRU.XHZC_FLG == 'Y') {
            TDRGGRP.RMK = "XXTXHZC";
        }
        TDRGGRP.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRGGRP.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRGGRP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDTGGRP_RD = new DBParm();
        TDTGGRP_RD.TableName = "TDTGGRP";
        IBS.WRITE(SCCGWA, TDRGGRP, TDTGGRP_RD);
    }
    public void T000_REWRITE_TDTGGRP() throws IOException,SQLException,Exception {
        TDRGGRP.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRGGRP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDTGGRP_RD = new DBParm();
        TDTGGRP_RD.TableName = "TDTGGRP";
        IBS.REWRITE(SCCGWA, TDRGGRP, TDTGGRP_RD);
    }
    public void T000_WRT_TDROCAC() throws IOException,SQLException,Exception {
        TDTOCAC_RD = new DBParm();
        TDTOCAC_RD.TableName = "TDTOCAC";
        IBS.WRITE(SCCGWA, TDROCAC, TDTOCAC_RD);
    }
    public void T000_READU_TDROCAC() throws IOException,SQLException,Exception {
        TDTOCAC_RD = new DBParm();
        TDTOCAC_RD.TableName = "TDTOCAC";
        TDTOCAC_RD.upd = true;
        IBS.READ(SCCGWA, TDROCAC, TDTOCAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        } else {
            WS_OCAC_FLAG = 'Y';
        }
    }
    public void T000_DEL_TDROCAC() throws IOException,SQLException,Exception {
        TDTOCAC_RD = new DBParm();
        TDTOCAC_RD.TableName = "TDTOCAC";
        IBS.DELETE(SCCGWA, TDROCAC, TDTOCAC_RD);
    }
    public void T000_READ_BVT() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        IBS.READ(SCCGWA, TDRBVT, TDTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZSQIFA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-INQ-IFA", DDCSQIFA);
    }
    public void S000_CALL_DCZUHLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-HLD", DCCUHLD);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_DCZURHLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-RHLD", DCCURHLD);
    }
    public void T000_READU_BVT() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        TDTBVT_RD.upd = true;
        IBS.READ(SCCGWA, TDRBVT, TDTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READU_BVT_LAST() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        TDTBVT_RD.where = "AC_NO = :TDRBVT.KEY.AC_NO";
        TDTBVT_RD.fst = true;
        TDTBVT_RD.order = "AC_NO DESC";
        IBS.READ(SCCGWA, TDRBVT, this, TDTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        TDTBVT_RD.where = "AC_NO = :TDRBVT.KEY.AC_NO";
        TDTBVT_RD.upd = true;
        IBS.READ(SCCGWA, TDRBVT, this, TDTBVT_RD);
    }
    public void T000_STARTBR_TDTGROP() throws IOException,SQLException,Exception {
        WS_GROP_FLG = 'N';
        CEP.TRC(SCCGWA, TDRGROP.AC_NO);
        CEP.TRC(SCCGWA, TDRGROP.KEY.DRAW_DT);
        TDTGROP_BR.rp = new DBParm();
        TDTGROP_BR.rp.TableName = "TDTGROP";
        TDTGROP_BR.rp.where = "AC_NO = :TDRGROP.AC_NO "
            + "AND DRAW_DT = :TDRGROP.KEY.DRAW_DT "
            + "AND TYPE < > 'N'";
        TDTGROP_BR.rp.upd = true;
        TDTGROP_BR.rp.order = "ACO_AC DESC";
        IBS.STARTBR(SCCGWA, TDRGROP, this, TDTGROP_BR);
    }
    public void T000_STARTBR_TDTGROP_AUTO() throws IOException,SQLException,Exception {
        WS_GROP_FLG = 'N';
        CEP.TRC(SCCGWA, TDRGROP.AC_NO);
        CEP.TRC(SCCGWA, TDRGROP.KEY.DRAW_DT);
        TDTGROP_BR.rp = new DBParm();
        TDTGROP_BR.rp.TableName = "TDTGROP";
        TDTGROP_BR.rp.where = "AC_NO = :TDRGROP.AC_NO "
            + "AND DRAW_DT = :TDRGROP.KEY.DRAW_DT "
            + "AND AUTO_BAL > 0";
        TDTGROP_BR.rp.upd = true;
        TDTGROP_BR.rp.order = "ACO_AC DESC";
        IBS.STARTBR(SCCGWA, TDRGROP, this, TDTGROP_BR);
    }
    public void T000_STARTBR_TDTGGRP() throws IOException,SQLException,Exception {
        WS_GGRP_FLG = 'N';
        TDTGGRP_BR.rp = new DBParm();
        TDTGGRP_BR.rp.TableName = "TDTGGRP";
        TDTGGRP_BR.rp.where = "AC_NO = :TDRGGRP.AC_NO "
            + "AND JRNNO = :TDRGGRP.KEY.JRNNO "
            + "AND CAN_FLG = '0' "
            + "AND TR_DATE = :TDRGGRP.KEY.TR_DATE";
        TDTGGRP_BR.rp.upd = true;
        TDTGGRP_BR.rp.order = "TR_DATE";
        IBS.STARTBR(SCCGWA, TDRGGRP, this, TDTGGRP_BR);
    }
    public void T000_STARTBR_GROPSMST() throws IOException,SQLException,Exception {
        WS_SMST_FLG = 'N';
        CEP.TRC(SCCGWA, TDRSMST.AC_NO);
        TDTSMST_BR.rp = new DBParm();
        TDTSMST_BR.rp.TableName = "TDTSMST";
        TDTSMST_BR.rp.where = "AC_NO = :TDRSMST.AC_NO "
            + "AND ACO_STS = '0' "
            + "AND SUBSTR ( STSW , 52 , 1 ) < > '1' "
            + "AND LBAL_DATE = :TDRSMST.LBAL_DATE";
        TDTSMST_BR.rp.upd = true;
        TDTSMST_BR.rp.order = "VAL_DATE";
        IBS.STARTBR(SCCGWA, TDRSMST, this, TDTSMST_BR);
    }
    public void T000_READNEXT_GROPSMST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRSMST, this, TDTSMST_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_SMST_FLG = 'Y';
        } else {
            WS_SMST_FLG = 'N';
        }
    }
    public void T000_READNEXT_TDTSMST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRSMST, this, TDTSMST_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_SMST_FLG = 'Y';
        } else {
            WS_SMST_FLG = 'N';
        }
    }
    public void T000_READNEXT_TDTGGRP() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRGGRP, this, TDTGGRP_BR);
        CEP.TRC(SCCGWA, TDRGGRP.ACO_AC);
        CEP.TRC(SCCGWA, TDRGGRP.KEY.TR_DATE);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_GGRP_FLG = 'Y';
        } else {
            WS_GGRP_FLG = 'N';
        }
    }
    public void T000_READNEXT_TDTGROP() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRGROP, this, TDTGROP_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_GROP_FLG = 'Y';
        } else {
            WS_GROP_FLG = 'N';
        }
    }
    public void T000_ENDBR_TDTGROP() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTSMST_BR);
    }
    public void T000_ENDBR_TDTGGRP() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTGGRP_BR);
    }
    public void T000_ENDBR_TDTSMST() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTSMST_BR);
    }
    public void T000_ENDBR_GROPSMST() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTSMST_BR);
    }
    public void T000_READ_SMST_FST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.where = "AC_NO = :TDRSMST.AC_NO "
            + "AND ACO_STS = '0' "
            + "AND SUBSTR ( STSW , 52 , 1 ) < > '1'";
        TDTSMST_RD.fst = true;
        TDTSMST_RD.order = "VAL_DATE DESC";
        IBS.READ(SCCGWA, TDRSMST, this, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
        }
    }
    public void T000_READ_SMST_MR() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.where = "AC_NO = :TDRSMST.AC_NO "
            + "AND ACO_STS = '0' "
            + "AND SUBSTR ( STSW , 52 , 1 ) = '1'";
        IBS.READ(SCCGWA, TDRSMST, this, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
        }
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "CUS_AC = :DDRCCY.CUS_AC "
            + "AND CCY = :DDRCCY.CCY "
            + "AND CCY_TYPE = :DDRCCY.CCY_TYPE";
        DDTCCY_RD.fst = true;
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
        }
    }
    public void T000_READ_IREV() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRIREV);
        TDRIREV.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
        TDRIREV.KEY.STR_DATE = TDCACCRU.VAL_DT;
        CEP.TRC(SCCGWA, TDRIREV.KEY.ACO_AC);
        CEP.TRC(SCCGWA, TDRIREV.KEY.STR_DATE);
        TDTIREV_RD = new DBParm();
        TDTIREV_RD.TableName = "TDTIREV";
        TDTIREV_RD.eqWhere = "ACO_AC";
        TDTIREV_RD.where = "STR_DATE <= :TDRIREV.KEY.STR_DATE "
            + "AND END_DATE >= :TDRIREV.KEY.STR_DATE";
        IBS.READ(SCCGWA, TDRIREV, this, TDTIREV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_IREV_NOFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZITAXG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-TAXR-GROUP-INQ", BPCITAXG);
        CEP.TRC(SCCGWA, BPCITAXG.RETURN_INFO);
    }
    public void S000_CALL_TDZUPARM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-UNIT-TDPARM", TDCUPARM);
    }
    public void S000_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-CCY-BAL", DDCIQBAL, true);
    }
    public void S000_CALL_DCZILNKR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-INQ-ACLNK", DCCILNKR);
        if (DCCILNKR.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, DCCILNKR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZMLMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-LMT", CICMLMT);
        if (CICMLMT.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICMLMT.RC);
        }
    }
    public void S000_CALL_TDZCEINT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-CAL-EXP-INT", TDCCEINT);
        if (TDCCEINT.RC_MSG.RC != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, TDCCEINT.RC_MSG);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC.RC_CODE);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_TDZPPRTF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-PSBK-PRTF", TDCPPRTF);
        if (TDCPPRTF.RC_MSG.RC != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, TDCPPRTF.RC_MSG);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
    }
    public void S000_CALL_CIZCKLS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CHECK-LST", CICCKLS);
        if (CICCKLS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCKLS.RC);
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        CEP.TRC(SCCGWA, CICACCU.RC);
        if (CICACCU.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
    }
    public void S000_CALL_CIZACDD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACDD);
    }
    public void S000_CALL_CIZSACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACAC", CICSACAC);
        if (CICSACAC.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, CICSACAC.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZMACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-ACR", CICMACR);
        if (CICMACR.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, CICMACR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DCZUCACJ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-AC-JOIN", DCCUCACJ);
        CEP.TRC(SCCGWA, DCCUCACJ);
        if (DCCUCACJ.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, DCCUCACJ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZIMMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-NFIN-M-MST", DDCIMMST);
        CEP.TRC(SCCGWA, DDCIMMST.RC.RC_CODE);
        if (DDCIMMST.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, DDCIMMST.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF);
        if (DCCUCINF.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZUNARR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-UPD-HIS-NARR", BPCUNARR);
        CEP.TRC(SCCGWA, BPCUNARR.RC);
        if (BPCUNARR.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCUNARR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        WS_RC = 0;
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (WS_RC != 0) {
            SCCCLDT.RC = WS_RC;
        }
        CEP.TRC(SCCGWA, SCCCLDT.RC);
        if (SCCCLDT.RC != 0) {
            IBS.init(SCCGWA, SCCBINF);
            SCCBINF.ERR_TYPE = 'P';
            SCCBINF.ERR_ACTION = 'E';
            WS_RC_DISP = SCCCLDT.RC;
            SCCBINF.ERR_NAME = PGM_SCSSCLDT;
            WS_MSGID = K_SYS_ERR;
            SCCBINF.OTHER_INFO = "CALL-SCSSCLDT ERROR  " + WS_RC_DISP;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_SCZHMPW() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCHMPW);
        IBS.CALLCPN(SCCGWA, "SC-HM-PASSWORD", SCCHMPW);
        CEP.TRC(SCCGWA, SCCHMPW.OUT_INFO.ERR_CODE);
        if (!SCCHMPW.OUT_INFO.ERR_CODE.equalsIgnoreCase("SC0000")) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_PWD_ERROR;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPLDPD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-LOAD-PROD", BPCPLDPD);
        if (BPCPLDPD.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPLDPD.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPCKPD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "TYJ713");
        CEP.TRC(SCCGWA, BPCPCKPD.PRDT_CODE);
        IBS.CALLCPN(SCCGWA, "BP-P-PRDT-COM-CHECK", BPCPCKPD);
        CEP.TRC(SCCGWA, BPCPCKPD.RC.RC_CODE);
        if (BPCPCKPD.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPCKPD.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICQACRI.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZSACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACRL", CICSACRL);
        CEP.TRC(SCCGWA, CICSACRL.RC.RC_CODE);
        if (CICSACRL.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, CICSACRL.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZSACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACR", CICSACR);
        CEP.TRC(SCCGWA, CICSACR.RC.RC_CODE);
        if (CICSACR.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, CICSACR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZSCINM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-CI-NAME", DDCSCINM, true);
    }
    public void S000_CALL_DCZUSSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-CPN-M-SSTS", DCCUSSTS);
        if (DCCUSSTS.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, DCCUSSTS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DCZPFTCK() throws IOException,SQLException,Exception {
        DCCPFTCK.FUNCTION_CODE = 'S';
        IBS.CALLCPN(SCCGWA, "DC-P-FINANCE-TR-CHK", DCCPFTCK);
    }
    public void S000_CALL_BPZPQPDT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-PRD-TYPE", BPCPQPDT);
        if (BPCPQPDT.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPQPDT.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_TDZLML() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-Z-LML", TDCLML);
        if (TDCLML.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCLML.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG-STATION", BPCPRGST);
        if (BPCPRGST.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPRGST.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFFTXI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-TX-INFO", BPCFFTXI);
        if (BPCFFTXI.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCFFTXI.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFCALF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-CAL-FEE", BPCTCALF);
        if (BPCTCALF.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCTCALF.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFAMTA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-AMT-TBL-AUTH", BPCFAMTA);
        if (BPCFAMTA.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCFAMTA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZUBUSE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-TLR-BV-USE", BPCUBUSE);
    }
    public void S00_LINK_BPZPCLWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CAL-WORK-DAY", BPCOCLWD);
        if (BPCOCLWD.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCFAMTA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZSAGEN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SVR-CIZSAGEN", CICSAGEN);
        if (CICSAGEN.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSAGEN.RC);
        }
    }
    public void S000_CALL_BPZGACNO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-GENERTE-ACNO", BPCCGAC);
        if (BPCCGAC.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCCGAC.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFCSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-STS-TBL-AUTH", BPCFCSTS);
        if (BPCFCSTS.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCFCSTS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPEBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-BASIC-EWA", BPCPEBAS);
        if (BPCPEBAS.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPEBAS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZSFX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-FX", BPCFX);
        if (BPCFX.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCFX.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZSOCAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-OCAC-INFO", BPCSOCAC);
        if (BPCSOCAC.RC.RC_CODE != 0) {
            WS_MSGID = "" + BPCSOCAC.RC.RC_CODE;
            JIBS_tmp_int = WS_MSGID.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_MSGID = "0" + WS_MSGID;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DCZIMSTR() throws IOException,SQLException,Exception {
    }
    public void S000_CALL_DCZIACRA() throws IOException,SQLException,Exception {
    }
    public void S000_CALL_DCZIACRL() throws IOException,SQLException,Exception {
    }
    public void S000_CALL_DCZITRSR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-TRS-OPR", DCCITRSR);
        if (DCCITRSR.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, DCCITRSR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZSFBID() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-DDZSFBID", DDCSFBID);
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZQCNGL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-CNGL", BPCQCNGL);
        CEP.TRC(SCCGWA, BPCQCNGL.RC);
        if (BPCQCNGL.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCQCNGL.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZUCNGM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-MAINT-CNGM", BPCUCNGM);
        CEP.TRC(SCCGWA, BPCUCNGM.RC);
        if (BPCUCNGM.RC.RC_RTNCODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCUCNGM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PROC-FHIS", BPCPFHIS);
        CEP.TRC(SCCGWA, BPCPFHIS.RC.RC_CODE);
        if (BPCPFHIS.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPFHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_SCSSCKDT() throws IOException,SQLException,Exception {
        SCSSCKDT SCSSCKDT2 = new SCSSCKDT();
        SCSSCKDT2.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            if (WS_MSGID == null) WS_MSGID = "";
            JIBS_tmp_int = WS_MSGID.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) WS_MSGID += " ";
            WS_MSGID = "SC" + WS_MSGID.substring(2);
            if (WS_MSGID == null) WS_MSGID = "";
            JIBS_tmp_int = WS_MSGID.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) WS_MSGID += " ";
            JIBS_tmp_str[0] = "" + SCCCKDT.RC;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            WS_MSGID = WS_MSGID.substring(0, 3 - 1) + JIBS_tmp_str[0] + WS_MSGID.substring(3 + 4 - 1);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFTLAM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-TLR-ACCU-MGM", BPCFTLAM);
        if (BPCFTLAM.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCFTLAM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_TDZPROD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-GET-PROD", TDCPIOD);
        if (TDCPIOD.RC.RC_RTNCODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, TDCPIOD.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZCINTI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-C-INTR-INQ", BPCCINTI);
        if (BPCCINTI.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCCINTI.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_TDZACM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-AC-PSW-CHECK", TDCACM);
    }
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB);
        if (AICPQMIB.RC.RC_CODE != 0 
            && AICPQMIB.RC.RC_CODE != 8917 
            && AICPQMIB.RC.RC_CODE != 8924) {
            CEP.ERR(SCCGWA, AICPQMIB.RC);
        }
    }
    public void S000_CALL_TDZQPMP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-BASE-PRD-QRY", TDCQPMP);
        CEP.TRC(SCCGWA, TDCQPMP.RC.RC_RTNCODE);
        if (TDCQPMP.RC.RC_RTNCODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, TDCQPMP.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, SCCBINF);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
