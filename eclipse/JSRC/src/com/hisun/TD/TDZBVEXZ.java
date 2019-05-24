package com.hisun.TD;

import com.hisun.DC.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCENPSW;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.BP.*;
import com.hisun.CI.*;
import com.hisun.DP.*;

import java.io.IOException;
import java.sql.SQLException;

public class TDZBVEXZ {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    brParm TDTPLED_BR = new brParm();
    DBParm TDTIREV_RD;
    DBParm TDTCMST_RD;
    DBParm TDTSMST_RD;
    DBParm TDTBVT_RD;
    DBParm TDTHBVT_RD;
    DBParm TDTYBTP_RD;
    DBParm TDTPBP_RD;
    DBParm TDTSTS_RD;
    String K_OUTPUT_FMT = "TD546";
    String K_SIMP_ADV_FMT = "TD520";
    String K_PLED_FMT = "TD414";
    String K_INQ_FMT = "TD414";
    String K_PRDP_TYP = "PRDPR";
    int K_REQ_BR = 999999999;
    String WS_ERR_MSG = " ";
    short WS_CNT = 0;
    String WS_BUSI_NO = " ";
    int WS_AC_SEQ = 0;
    String WS_AC = " ";
    String WS_O_CI_NO = " ";
    String WK_CI_NO_O = " ";
    String WS_OLD_AC = " ";
    char WS_CMST_CROS_CR_FLG_I = ' ';
    String WS_NEW_AC = " ";
    int WS_CHG_SEQ = 0;
    TDZBVEXZ_WS_TABLES_INFO WS_TABLES_INFO = new TDZBVEXZ_WS_TABLES_INFO();
    TDZBVEXZ_WS_CHECK_INFO WS_CHECK_INFO = new TDZBVEXZ_WS_CHECK_INFO();
    TDZBVEXZ_WS_CI_INFO WS_CI_INFO = new TDZBVEXZ_WS_CI_INFO();
    int WS_VAL_NUM = 0;
    int WS_PROC_NUM = 0;
    String WS_BV_NO = " ";
    short WS_CURR_POS = 0;
    short WS_PART_NUM = 0;
    String WS_BV_CD = " ";
    String WS_BV_CD_I = " ";
    String WS_BV_NO_I = " ";
    TDZBVEXZ_WS_PLD_NO WS_PLD_NO = new TDZBVEXZ_WS_PLD_NO();
    String WS_CCY_O = " ";
    char WS_CCY_TYPO = ' ';
    char WS_YBT_SIG = ' ';
    char WS_YBT_SIG_I = ' ';
    char WS_SMST_MDY_FLG = ' ';
    short WS_PSBK_SEQ_I = 0;
    short WS_PSBK_SEQ_O = 0;
    int WS_CUR_BVT_POS_I = 0;
    TDZBVEXZ_CP_PROD_CD CP_PROD_CD = new TDZBVEXZ_CP_PROD_CD();
    String WS_DRAW_MTH_DESC = " ";
    TDZBVEXZ_REDEFINES90 REDEFINES90 = new TDZBVEXZ_REDEFINES90();
    short WS_C = 0;
    int WS_CNT2 = 0;
    short WS_NUM1 = 0;
    int WS_L_CNT = 0;
    int WS_P_ROW = 0;
    int WS_L_ROW = 0;
    int WS_T_PAGE = 0;
    int WS_P_NUM = 0;
    int WS_STR_NUM = 0;
    int WS_END_NUM = 0;
    TDZBVEXZ_WS_FMT WS_FMT = new TDZBVEXZ_WS_FMT();
    double WS_AVL_BAL = 0;
    String WS_INQ_AC = " ";
    char WS_INQ_BV_TYP = ' ';
    String WS_INQ_BV_NO = " ";
    String WS_INQ_PLD_NO = " ";
    char WS_STS_FLG = ' ';
    char WS_MSREL_FLG = ' ';
    char WS_TDTPLED_FLAG = ' ';
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCENPSW SCCENPSW = new SCCENPSW();
    SCCHMPW SCCHMPW = new SCCHMPW();
    SCCFMT SCCFMT = new SCCFMT();
    BPCFQFBV BPCFQFBV = new BPCFQFBV();
    DCCILNKR DCCILNKR = new DCCILNKR();
    BPCUBUSE BPCUBUSE = new BPCUBUSE();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICACCU CICACCU = new CICACCU();
    TDRSMST TDRSMST = new TDRSMST();
    TDRBVT TDRBVT = new TDRBVT();
    TDRSTS TDRSTS = new TDRSTS();
    TDRYBTP TDRYBTP = new TDRYBTP();
    TDRPBP TDRPBP = new TDRPBP();
    TDCPPRTF TDCPPRTF = new TDCPPRTF();
    DPCPARMP DPCPARMP = new DPCPARMP();
    TDCOBVEZ TDCOBVEZ = new TDCOBVEZ();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    TDCRPLED TDCRPLED = new TDCRPLED();
    TDCQLDT TDCQLDT = new TDCQLDT();
    TDCORIMP TDCORIMP = new TDCORIMP();
    TDCPLDT TDCPLDT = new TDCPLDT();
    TDRIREV TDRIREV = new TDRIREV();
    DCCIMSTR DCCIMSTR = new DCCIMSTR();
    DCCIMSTT DCCIMSTT = new DCCIMSTT();
    DCCIACRB DCCIACRB = new DCCIACRB();
    DCCIACRL DCCIACRL = new DCCIACRL();
    DCCUQSAC DCCUQSAC = new DCCUQSAC();
    DCCPCDCK DCCPCDCK = new DCCPCDCK();
    DCCUOPNP DCCUOPNP = new DCCUOPNP();
    DCCUCLSP DCCUCLSP = new DCCUCLSP();
    TDCBVCD TDCBVCD = new TDCBVCD();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    TDCUPARM TDCUPARM = new TDCUPARM();
    TDRPLED TDRPLED = new TDRPLED();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    TDCPRDP TDCPRDP = new TDCPRDP();
    DCCIQBAL DCCIQBAL = new DCCIQBAL();
    TDRCMST TDRCMST = new TDRCMST();
    TDRHBVT TDRHBVT = new TDRHBVT();
    DCCUCINF DCCUCINF = new DCCUCINF();
    CICQACAC CICQACAC = new CICQACAC();
    BPCCGAC BPCCGAC = new BPCCGAC();
    CICSACR CICSACR = new CICSACR();
    CICSACAC CICSACAC = new CICSACAC();
    BPCSOCAC BPCSOCAC = new BPCSOCAC();
    BPCPOCAC BPCPOCAC = new BPCPOCAC();
    TDCACM TDCACM = new TDCACM();
    CICCUST CICCUST = new CICCUST();
    BPCPEBAS BPCPEBAS = new BPCPEBAS();
    BPCPRGST BPCPRGST = new BPCPRGST();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    CICSACRL CICSACRL = new CICSACRL();
    CICQACRL CICQACRL = new CICQACRL();
    TDCPROD TDCPROD = new TDCPROD();
    TDCQPMP TDCQPMP = new TDCQPMP();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPCPORUP_DATA_INFO BPCPORUP;
    TDCBVEXZ TDCBVEXZ;
    public void MP(SCCGWA SCCGWA, TDCBVEXZ TDCBVEXZ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCBVEXZ = TDCBVEXZ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDZBVEXZ return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCBVEXZ.BV_CD_O);
        CEP.TRC(SCCGWA, TDCBVEXZ.CERF_OPT);
        CEP.TRC(SCCGWA, TDCBVEXZ.BV_TYP_I);
        CEP.TRC(SCCGWA, TDCBVEXZ.DRAW_MTH_O);
        CEP.TRC(SCCGWA, TDCBVEXZ.PSW_O);
        CEP.TRC(SCCGWA, TDCBVEXZ.ID_TYP_O);
        CEP.TRC(SCCGWA, TDCBVEXZ.ID_NO_O);
        CEP.TRC(SCCGWA, TDCBVEXZ.BV_CD_I);
        CEP.TRC(SCCGWA, TDCBVEXZ.BV_TYP_I);
        CEP.TRC(SCCGWA, TDCBVEXZ.BV_NO_I);
        CEP.TRC(SCCGWA, TDCBVEXZ.MAIN_AC_I);
        CEP.TRC(SCCGWA, TDCBVEXZ.MAIN_AC_O);
        CEP.TRC(SCCGWA, TDCBVEXZ.DRAW_MTH_I);
        CEP.TRC(SCCGWA, TDCBVEXZ.PSW_I);
        CEP.TRC(SCCGWA, TDCBVEXZ.ID_TYP_I);
        CEP.TRC(SCCGWA, TDCBVEXZ.ID_NO_I);
        CEP.TRC(SCCGWA, "F-BUG2");
        if (TDCBVEXZ.CERF_OPT == 'C') {
            B100_CHK_INPUT_PROC();
            B210_GET_AC_BV_INFO_PROC();
            CEP.TRC(SCCGWA, TDCBVEXZ.BV_TYP_I);
            CEP.TRC(SCCGWA, TDRBVT.KEY.AC_NO);
            if (TDRBVT.STSW == null) TDRBVT.STSW = "";
            JIBS_tmp_int = TDRBVT.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
            if (TDCBVEXZ.BV_TYP_I == '3' 
                || (TDCBVEXZ.BV_TYP_I == '7' 
                && TDCBVEXZ.BV_TYP_O == '8') 
                || (TDCBVEXZ.BV_TYP_I == '7' 
                && !TDRBVT.STSW.substring(55 - 1, 55 + 1 - 1).equalsIgnoreCase("1"))) {
                B220_BV_USE();
            }
            CEP.TRC(SCCGWA, "B240-EXCHANGE-PROC");
            CEP.TRC(SCCGWA, TDRBVT.KEY.AC_NO);
            B240_EXCHANGE_PROC();
            CEP.TRC(SCCGWA, "B230-WRI-NFIN-HIS-PROC");
            CEP.TRC(SCCGWA, TDRBVT.KEY.AC_NO);
            B230_WRI_NFIN_HIS_PROC();
            B250_OUTPUT_PROC();
        } else {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_INVALID_OPT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B100_CHK_INPUT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCBVEXZ.BV_CD_O);
        CEP.TRC(SCCGWA, TDCBVEXZ.BV_NO_O);
        CEP.TRC(SCCGWA, TDCBVEXZ.BV_TYP_O);
        CEP.TRC(SCCGWA, TDCBVEXZ.BV_TYP_I);
        CEP.TRC(SCCGWA, TDCBVEXZ.MAIN_AC_O);
        CEP.TRC(SCCGWA, TDCBVEXZ.AC_SEQ_O);
        if (TDCBVEXZ.BV_TYP_O == TDCBVEXZ.BV_TYP_I) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_BV_TYP_THE_SAME);
        }
        if (TDCBVEXZ.CERF_OPT == 'C' 
            && TDCBVEXZ.BV_TYP_O == '8' 
            && !TDCBVEXZ.MAIN_AC_O.equalsIgnoreCase(TDCBVEXZ.MAIN_AC_I)) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_XN_ACNO_NEED_SAME);
        }
        CEP.TRC(SCCGWA, TDCBVEXZ.MAIN_AC_O);
        if (TDCBVEXZ.MAIN_AC_O.trim().length() > 0) {
            CEP.TRC(SCCGWA, TDCBVEXZ.AC_SEQ_O);
            if (TDCBVEXZ.AC_SEQ_O == 0 
                && (TDCBVEXZ.BV_TYP_O != '3' 
                && TDCBVEXZ.BV_TYP_O != '8') 
                && TDCBVEXZ.BV_NO_O.trim().length() == 0) {
                CEP.TRC(SCCGWA, "1111111111");
                CEP.TRC(SCCGWA, TDCBVEXZ.AC_SEQ_O);
                CEP.TRC(SCCGWA, TDCBVEXZ.BV_TYP_O);
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_FIELD_MUST_IPT);
            }
        } else {
            CEP.TRC(SCCGWA, TDCBVEXZ.AC_SEQ_O);
            if (TDCBVEXZ.MAIN_AC_O.trim().length() == 0) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_FIELD_MUST_IPT);
            }
        }
        if (TDCBVEXZ.BV_TYP_O != '1' 
            && TDCBVEXZ.BV_TYP_O != '3' 
            && TDCBVEXZ.BV_TYP_O != '4' 
            && TDCBVEXZ.BV_TYP_O != '5' 
            && TDCBVEXZ.BV_TYP_O != '6' 
            && TDCBVEXZ.BV_TYP_O != '7' 
            && TDCBVEXZ.BV_TYP_O != '8' 
            && TDCBVEXZ.BV_TYP_O != '9' 
            && TDCBVEXZ.BV_TYP_O != '4' 
            && TDCBVEXZ.BV_TYP_O != '7' 
            && TDCBVEXZ.BV_TYP_O != '8') {
            CEP.TRC(SCCGWA, "M-TD-BV-TYP-ERR1");
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_BV_TYP_ERR);
        }
        if (TDCBVEXZ.BV_TYP_O == '4' 
            && (TDCBVEXZ.BV_TYP_I != '3' 
            && (TDCBVEXZ.BV_TYP_I != '1' 
            && TDCBVEXZ.MAIN_AC_I.trim().length() == 0))) {
            CEP.TRC(SCCGWA, "M-TD-BV-TYP-ERR2");
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_BV_TYP_ERR);
        }
        if (TDCBVEXZ.BV_TYP_I == '4' 
            && (TDCBVEXZ.BV_TYP_O != '3' 
            && TDCBVEXZ.BV_TYP_O != '1')) {
            CEP.TRC(SCCGWA, "M-TD-BV-TYP-ERR3");
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_BV_TYP_ERR);
        }
        if (TDCBVEXZ.BV_TYP_O == '4') {
            IBS.init(SCCGWA, DCCPCDCK);
            DCCPCDCK.CARD_NO = TDCBVEXZ.MAIN_AC_O;
            DCCPCDCK.FUNC_CODE = 'P';
            DCCPCDCK.CARD_PSW = TDCBVEXZ.PSW_O;
        }
        CEP.TRC(SCCGWA, TDCBVEXZ.MAIN_AC_O);
        if (TDCBVEXZ.BV_TYP_I != '3') {
            if (TDCBVEXZ.MAIN_AC_I.trim().length() == 0) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_FIELD_MUST_IPT);
            }
        }
    }
    public void B210_GET_AC_BV_INFO_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B210-START");
        WS_OLD_AC = TDCBVEXZ.MAIN_AC_I;
        if (TDCBVEXZ.BV_TYP_I == '1' 
            && TDCBVEXZ.MAIN_AC_I.trim().length() > 0) {
            IBS.init(SCCGWA, TDRCMST);
            TDRCMST.KEY.AC_NO = TDCBVEXZ.MAIN_AC_I;
            T000_READ_TDTCMST();
            WS_CMST_CROS_CR_FLG_I = TDRCMST.CROS_CR_FLG;
            IBS.init(SCCGWA, TDRBVT);
            TDRBVT.KEY.AC_NO = TDCBVEXZ.MAIN_AC_I;
            CEP.TRC(SCCGWA, TDCBVEXZ.MAIN_AC_I);
            T000_READ_TDTBVT();
            R000_CHK_BVT_STS();
            CEP.TRC(SCCGWA, WS_YBT_SIG_I);
            WS_YBT_SIG_I = 'Y';
            WS_CUR_BVT_POS_I = TDRBVT.PSBK_POS;
            WS_CUR_BVT_POS_I = WS_CUR_BVT_POS_I + 2;
        } else {
            WS_CUR_BVT_POS_I = 1;
        }
        CEP.TRC(SCCGWA, "CHECK LOG END");
        if (TDCBVEXZ.BV_TYP_O == '7' 
            || TDCBVEXZ.BV_TYP_O == '1' 
            || TDCBVEXZ.BV_TYP_O == '8') {
            IBS.init(SCCGWA, TDRBVT);
            IBS.init(SCCGWA, TDRCMST);
            TDRCMST.KEY.AC_NO = TDCBVEXZ.MAIN_AC_O;
            T000_READ_TDTCMST();
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = TDCBVEXZ.MAIN_AC_O;
            CICQACAC.DATA.BV_NO = TDCBVEXZ.BV_NO_O;
            CICQACAC.DATA.AGR_SEQ = TDCBVEXZ.AC_SEQ_O;
            S000_CALL_CIZQACAC();
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            T000_READ_TDTSMST();
            if (TDCBVEXZ.BV_TYP_O == '7' 
                && TDCBVEXZ.BV_TYP_I == '3' 
                && TDRSMST.PRDAC_CD.equalsIgnoreCase("031")) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_ZTO_BV_EXZ);
            }
            CEP.TRC(SCCGWA, TDRCMST.CI_TYP);
            if (TDCBVEXZ.BV_TYP_O == '1' 
                || (TDCBVEXZ.BV_TYP_O == '8' 
                && TDRCMST.CI_TYP == '1') 
                || (TDCBVEXZ.BV_TYP_O == '7' 
                && TDRCMST.CI_TYP == '1')) {
                TDRBVT.KEY.AC_NO = TDCBVEXZ.MAIN_AC_O;
            } else {
                TDRBVT.KEY.AC_NO = TDRSMST.KEY.ACO_AC;
            }
            CEP.TRC(SCCGWA, TDRBVT.KEY.AC_NO);
            CEP.TRC(SCCGWA, TDRBVT.STSW);
            T000_READ_TDTBVT();
            if (TDRBVT.STSW == null) TDRBVT.STSW = "";
            JIBS_tmp_int = TDRBVT.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
            if (TDCBVEXZ.BV_TYP_O == '1' 
                && TDRBVT.STSW.substring(0, 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.OLD_YBT_CHG_NEW);
            }
            CEP.TRC(SCCGWA, TDRBVT.KEY.AC_NO);
        } else {
            if (TDCBVEXZ.BV_TYP_O == '3' 
                && (TDCBVEXZ.BV_TYP_I != '7' 
                && TDCBVEXZ.BV_TYP_I != '8')) {
                IBS.init(SCCGWA, TDRCMST);
                TDRCMST.KEY.AC_NO = TDCBVEXZ.MAIN_AC_O;
                CEP.TRC(SCCGWA, TDCBVEXZ.MAIN_AC_O);
                T000_READ_TDTCMST();
                IBS.init(SCCGWA, TDRSMST);
                TDRSMST.AC_NO = TDRCMST.KEY.AC_NO;
                T000_READ_TDTSMST_C();
                TDRBVT.KEY.AC_NO = TDCBVEXZ.MAIN_AC_O;
                T000_READ_TDTBVT();
            } else {
                IBS.init(SCCGWA, CICQACAC);
                CICQACAC.FUNC = 'R';
                CEP.TRC(SCCGWA, TDCBVEXZ.MAIN_AC_O);
                CEP.TRC(SCCGWA, TDCBVEXZ.AC_SEQ_O);
                CICQACAC.DATA.AGR_NO = TDCBVEXZ.MAIN_AC_O;
                CICQACAC.DATA.AGR_SEQ = TDCBVEXZ.AC_SEQ_O;
                CICQACAC.DATA.BV_NO = TDCBVEXZ.BV_NO_O;
                S000_CALL_CIZQACAC();
                if (CICQACAC.O_DATA.O_ACR_DATA.O_ENTY_TYP == '1' 
                    && TDCBVEXZ.BV_TYP_O == '3') {
                    IBS.init(SCCGWA, TDRCMST);
                    TDRCMST.KEY.AC_NO = TDCBVEXZ.MAIN_AC_O;
                    CEP.TRC(SCCGWA, TDCBVEXZ.MAIN_AC_O);
                    T000_READ_TDTCMST();
                    IBS.init(SCCGWA, TDRSMST);
                    TDRSMST.AC_NO = TDRCMST.KEY.AC_NO;
                    T000_READ_TDTSMST_C();
                    TDRBVT.KEY.AC_NO = TDCBVEXZ.MAIN_AC_O;
                    T000_READ_TDTBVT();
                } else {
                    CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
                    IBS.init(SCCGWA, TDRSMST);
                    TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
                    T000_READ_TDTSMST();
                    if (TDCBVEXZ.BV_TYP_O == '4') {
                        IBS.init(SCCGWA, DCCUCINF);
                        DCCUCINF.CARD_NO = TDCBVEXZ.MAIN_AC_O;
                        S000_CALL_DCZUCINF();
                    } else {
                        TDRBVT.KEY.AC_NO = TDRSMST.KEY.ACO_AC;
                        CEP.TRC(SCCGWA, TDRBVT.KEY.AC_NO);
                        T000_READ_TDTBVT();
                    }
                }
            }
        }
        if (TDCBVEXZ.BV_TYP_I == '4') {
            CEP.TRC(SCCGWA, "NEW-4");
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = TDCBVEXZ.MAIN_AC_I;
            S000_CALL_CIZACCU();
            WK_CI_NO_O = CICACCU.DATA.CI_NO;
            CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
        }
        if (TDCBVEXZ.BV_TYP_I == '1') {
            CEP.TRC(SCCGWA, "NEW-1");
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = TDCBVEXZ.MAIN_AC_I;
            CEP.TRC(SCCGWA, CICACCU.DATA.AGR_NO);
            CEP.TRC(SCCGWA, CICACCU.DATA.ENTY_TYP);
            S000_CALL_CIZACCU();
            CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
            WK_CI_NO_O = CICACCU.DATA.CI_NO;
        }
        if ((TDCBVEXZ.BV_TYP_I == '7' 
            || TDCBVEXZ.BV_TYP_I == '8') 
            || (TDCBVEXZ.BV_TYP_I == '1' 
            && WS_YBT_SIG_I == 'N') 
            || TDCBVEXZ.BV_TYP_I == '3') {
            CEP.TRC(SCCGWA, "NEW-1-3");
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = TDCBVEXZ.MAIN_AC_I;
            CEP.TRC(SCCGWA, CICACCU.DATA.AGR_NO);
            CEP.TRC(SCCGWA, CICACCU.DATA.ENTY_TYP);
            S000_CALL_CIZACCU();
            CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
            WK_CI_NO_O = CICACCU.DATA.CI_NO;
        }
        if (TDCBVEXZ.BV_TYP_O == '4') {
            CEP.TRC(SCCGWA, "OLD-4");
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = TDCBVEXZ.MAIN_AC_O;
            S000_CALL_CIZACCU();
            CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
            if (!CICACCU.DATA.CI_NO.equalsIgnoreCase(WK_CI_NO_O) 
                && TDCBVEXZ.BV_TYP_I != '3') {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CI_NO_NOT_SAME);
            }
        }
        if (TDCBVEXZ.BV_TYP_O == '1') {
            CEP.TRC(SCCGWA, "OLD-1");
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = TDCBVEXZ.MAIN_AC_O;
            CEP.TRC(SCCGWA, CICACCU.DATA.AGR_NO);
            CEP.TRC(SCCGWA, CICACCU.DATA.ENTY_TYP);
            S000_CALL_CIZACCU();
            CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
            if (!CICACCU.DATA.CI_NO.equalsIgnoreCase(WK_CI_NO_O) 
                && TDCBVEXZ.BV_TYP_I != '3') {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CI_NO_NOT_SAME);
            }
        }
        if (TDCBVEXZ.BV_TYP_O == '7' 
            || (TDCBVEXZ.BV_TYP_O == '1') 
            || TDCBVEXZ.BV_TYP_O == '3' 
            || TDCBVEXZ.BV_TYP_O == '8') {
            CEP.TRC(SCCGWA, "OLD-1-3");
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = TDCBVEXZ.MAIN_AC_O;
            CEP.TRC(SCCGWA, CICACCU.DATA.AGR_NO);
            CEP.TRC(SCCGWA, CICACCU.DATA.ENTY_TYP);
            S000_CALL_CIZACCU();
            CEP.TRC(SCCGWA, CICACCU.DATA.AC_CNM);
            TDCBVEXZ.AC_NM = CICACCU.DATA.AC_CNM;
            CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
            if (!CICACCU.DATA.CI_NO.equalsIgnoreCase(WK_CI_NO_O) 
                && TDCBVEXZ.BV_TYP_I != '3') {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CI_NO_NOT_SAME);
            }
        }
        if (TDCBVEXZ.BV_TYP_O == '7' 
            || TDCBVEXZ.BV_TYP_O == '3' 
            || (TDCBVEXZ.BV_TYP_O == '1') 
            || TDCBVEXZ.BV_TYP_O == '8') {
            WS_TABLES_INFO.WS_DRAW_MTH = TDRCMST.DRAW_MTH;
            WS_TABLES_INFO.WS_DRAW_INF = TDRCMST.DRAW_INF;
            WS_TABLES_INFO.WS_CROS_DR_FLG = TDRCMST.CROS_DR_FLG;
            WS_TABLES_INFO.WS_STSW = TDRBVT.STSW;
            R000_CHK_MMST_STS();
            R000_CHK_BVT_STS();
        } else {
            if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
            JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
            if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
            JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
            if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
            JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
            if (DCCUCINF.CARD_STSW.substring(0, 1).equalsIgnoreCase("1") 
                || DCCUCINF.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
                || DCCUCINF.CARD_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_BV_LOSS);
            }
            if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
            JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
            if (DCCUCINF.CARD_STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_BV_LOSS);
            }
        }
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1") 
            || TDRSMST.STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_HAS_INNER_HOLD;
                S000_ERR_MSG_PROC();
            } else {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_HAS_SIFA_HOLD;
                S000_ERR_MSG_PROC();
            }
        }
        WS_PART_NUM = TDRSMST.PART_NUM;
        DPCPARMP.AC_TYPE = TDRSMST.PRDAC_CD;
        CEP.TRC(SCCGWA, "JFTEST01");
        CEP.TRC(SCCGWA, TDRCMST.CI_TYP);
        if ((TDRSMST.PRDAC_CD.equalsIgnoreCase("037") 
            && TDRCMST.CI_TYP != '1' 
            && TDRSMST.ACO_STS != '0' 
            && TDCBVEXZ.BV_TYP_I == '3') 
            || (!TDRSMST.PRDAC_CD.equalsIgnoreCase("021") 
            && TDCBVEXZ.BV_TYP_I == '8' 
            && TDCBVEXZ.BV_TYP_O == '3') 
            || (TDRSMST.PRDAC_CD.equalsIgnoreCase("037") 
            && TDRCMST.CI_TYP == '1' 
            && TDCBVEXZ.BV_TYP_I != '3')) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INV_AC_TYP);
        }
        CEP.TRC(SCCGWA, "JFTEST02");
        if (TDRSMST.PRDAC_CD.equalsIgnoreCase("037") 
            && TDRCMST.CI_TYP == '1') {
            IBS.init(SCCGWA, CICSACRL);
            CICSACRL.FUNC = 'D';
            CICSACRL.DATA.AC_NO = TDCBVEXZ.MAIN_AC_O;
            IBS.init(SCCGWA, CICQACRL);
            CICQACRL.FUNC = 'I';
            CICQACRL.DATA.AC_NO = TDCBVEXZ.MAIN_AC_O;
            CICQACRL.DATA.AC_REL = "13";
            S000_CALL_CIZQACRL();
            CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
            CICSACRL.DATA.REL_AC_NO = CICQACRL.O_DATA.O_REL_AC_NO;
            CICSACRL.DATA.AC_REL = "13";
            S000_CALL_CIZSACRL();
        }
        if (TDRSMST.PRDAC_CD.equalsIgnoreCase("021") 
            && TDCBVEXZ.BV_TYP_I == '7' 
            && TDCBVEXZ.BV_TYP_O == '8') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INV_AC_TYP);
        }
        if (TDCBVEXZ.BV_TYP_I == '1' 
            && (!DPCPARMP.AC_TYPE.equalsIgnoreCase("020") 
            && !DPCPARMP.AC_TYPE.equalsIgnoreCase("021") 
            && !DPCPARMP.AC_TYPE.equalsIgnoreCase("033") 
            && !DPCPARMP.AC_TYPE.equalsIgnoreCase("034") 
            && !DPCPARMP.AC_TYPE.equalsIgnoreCase("035") 
            && !DPCPARMP.AC_TYPE.equalsIgnoreCase("031") 
            && !DPCPARMP.AC_TYPE.equalsIgnoreCase("037") 
            && !DPCPARMP.AC_TYPE.equalsIgnoreCase("038") 
            && !DPCPARMP.AC_TYPE.equalsIgnoreCase("032"))) {
            if ((DPCPARMP.AC_TYPE.equalsIgnoreCase("025") 
                || DPCPARMP.AC_TYPE.equalsIgnoreCase("026")) 
                || DPCPARMP.AC_TYPE.equalsIgnoreCase("026")) {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_TLZ_NOT_SUP_YBT;
                S000_ERR_MSG_PROC();
            }
            if (DPCPARMP.AC_TYPE.equalsIgnoreCase("027")) {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_CQC_NOT_SUP_YBT;
                S000_ERR_MSG_PROC();
            }
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_PRDAC_CD_YBT;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, TDCBVEXZ.BV_TYP_O);
        CEP.TRC(SCCGWA, TDCBVEXZ.BV_TYP_I);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_BANK);
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'A';
        CICCUST.DATA.AGR_NO = TDCBVEXZ.MAIN_AC_O;
        S000_CALL_CIZCUST();
        if (CICCUST.O_DATA.O_CI_TYP == '1') {
            R000_CHK_PRDP_ALL_BV();
        }
        if (TDCBVEXZ.BV_TYP_I == '1') {
            IBS.init(SCCGWA, TDRCMST);
            TDRCMST.KEY.AC_NO = TDCBVEXZ.MAIN_AC_I;
            T000_READ_TDTCMST();
            if (TDRCMST.STSW == null) TDRCMST.STSW = "";
            JIBS_tmp_int = TDRCMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRCMST.STSW += " ";
            if (TDRCMST.STSW == null) TDRCMST.STSW = "";
            JIBS_tmp_int = TDRCMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRCMST.STSW += " ";
            if (TDRCMST.STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
                || TDRCMST.STSW.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_IS_RESERVED);
            }
        } else {
            if (TDCBVEXZ.BV_TYP_I == '4') {
                IBS.init(SCCGWA, DCCUCINF);
                DCCUCINF.CARD_NO = TDCBVEXZ.MAIN_AC_I;
                S000_CALL_DCZUCINF();
                if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
                JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
                for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
                if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
                JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
                for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
                if (DCCUCINF.CARD_STSW.substring(0, 1).equalsIgnoreCase("1") 
                    || DCCUCINF.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                    CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_BV_LOSS);
                }
                if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
                JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
                for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
                if (DCCUCINF.CARD_STSW.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
                    CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_PIN_LOCK);
                }
            }
        }
        if (TDCBVEXZ.BV_TYP_O != '4') {
            IBS.init(SCCGWA, TDRCMST);
            TDRCMST.KEY.AC_NO = TDCBVEXZ.MAIN_AC_O;
            T000_READ_TDTCMST();
            if (TDRCMST.CROS_DR_FLG == '0' 
                && TDRCMST.OWNER_BRDP != BPCPORUP.DATA_INFO.BBR 
                && !SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
                S000_CALL_BPZPRGST();
                if (BPCPRGST.BRANCH_FLG == 'Y') {
                } else {
                    WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_CROS_DR_DRAW_ERR;
                    S000_ERR_MSG_PROC();
                }
            }
            if (TDRCMST.CROS_DR_FLG == '2' 
                && BPCPORUP.DATA_INFO.BBR != TDRCMST.OWNER_BRDP 
                && !SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
                if (TDRCMST.STSW == null) TDRCMST.STSW = "";
                JIBS_tmp_int = TDRCMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRCMST.STSW += " ";
                if (TDRCMST.STSW == null) TDRCMST.STSW = "";
                JIBS_tmp_int = TDRCMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRCMST.STSW += " ";
                if ((TDRCMST.OWNER_BRDP == 706610003 
                    && TDRCMST.STSW.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1") 
                    && BPCPORUP.DATA_INFO.BBR == 320505002) 
                    || (TDRCMST.OWNER_BRDP == 320505002 
                    && TDRCMST.STSW.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1") 
                    && BPCPORUP.DATA_INFO.BBR == 706610003)) {
                } else {
                    WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_CROS_DR_DRAW_ERR;
                    S000_ERR_MSG_PROC();
                }
            }
        }
        if (TDCBVEXZ.BV_TYP_I == '3' 
            || TDCBVEXZ.DRAW_MTH_O != ' ') {
            R000_CHK_PRDP_BV_TYP();
        }
        if (WS_CMST_CROS_CR_FLG_I == '0') {
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
        } else if (WS_CMST_CROS_CR_FLG_I == '1') {
        } else if (WS_CMST_CROS_CR_FLG_I == '2') {
            if (TDRSMST.OWNER_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CR_FLG_ERR);
            }
        }
        if (!TDCBVEXZ.BV_NO_O.equalsIgnoreCase(TDRBVT.BV_NO) 
            && TDCBVEXZ.BV_TYP_O != '8') {
            CEP.TRC(SCCGWA, TDCBVEXZ.BV_NO_O);
            CEP.TRC(SCCGWA, TDRBVT.BV_NO);
            CEP.TRC(SCCGWA, TDCBVEXZ.BV_TYP_O);
        }
    }
    public void R000_CHK_MMST_STS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRSMST.ACO_STS);
        CEP.TRC(SCCGWA, TDRSMST.STSW);
        CEP.TRC(SCCGWA, TDRCMST.STS);
        if (TDRSMST.ACO_STS == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED);
        }
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_LEG_FROZEN);
        }
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_SPE_FROZEN);
        }
        if (TDRBVT.STSW == null) TDRBVT.STSW = "";
        JIBS_tmp_int = TDRBVT.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
        if (TDRBVT.STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_BV_LOSS);
        }
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_IS_RESERVED);
        }
        if (TDRCMST.STSW == null) TDRCMST.STSW = "";
        JIBS_tmp_int = TDRCMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRCMST.STSW += " ";
        if (TDRCMST.STSW == null) TDRCMST.STSW = "";
        JIBS_tmp_int = TDRCMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRCMST.STSW += " ";
        if (TDRCMST.STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
            || TDRCMST.STSW.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_IS_RESERVED);
        }
        CEP.TRC(SCCGWA, "F-BUG89");
    }
    public void R000_CHK_SMST_ACO_STS() throws IOException,SQLException,Exception {
    }
    public void R000_CHK_BVT_STS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRBVT.STSW);
        CEP.TRC(SCCGWA, TDRBVT.KEY.AC_NO);
        if (TDRBVT.STSW == null) TDRBVT.STSW = "";
        JIBS_tmp_int = TDRBVT.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
        if (TDRBVT.STSW == null) TDRBVT.STSW = "";
        JIBS_tmp_int = TDRBVT.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
        if (TDRBVT.STSW == null) TDRBVT.STSW = "";
        JIBS_tmp_int = TDRBVT.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
        if ((TDRBVT.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
            || TDRBVT.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
            || TDRBVT.STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1"))) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_BV_LOSS);
        }
    }
    public void B220_BV_USE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUBUSE);
        BPCUBUSE.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUBUSE.TYPE = '0';
        BPCUBUSE.BV_CODE = TDCBVEXZ.BV_CD_I;
        BPCUBUSE.BEG_NO = TDCBVEXZ.BV_NO_I;
        BPCUBUSE.END_NO = TDCBVEXZ.BV_NO_I;
        BPCUBUSE.NUM = 1;
        S000_CALL_BPZUBUSE();
    }
    public void B730_WRT_VCH_EVENT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPEBAS);
        S000_CALL_BPZPEBAS();
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "BVF";
        if (TDCBVEXZ.BV_TYP_O == '7' 
            || TDCBVEXZ.BV_TYP_O == '8') {
            BPCPOEWA.DATA.EVENT_CODE = "DR";
        } else {
            BPCPOEWA.DATA.EVENT_CODE = "CR";
        }
        BPCPOEWA.DATA.PROD_CODE = "9710000001";
        CEP.TRC(SCCGWA, TDRSMST.CHE_BR);
        BPCPOEWA.DATA.BR_OLD = TDRSMST.CHE_BR;
        BPCPOEWA.DATA.BR_NEW = TDRSMST.CHE_BR;
        BPCPOEWA.DATA.PAY_BR = TDRSMST.CHE_BR;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = TDRSMST.CCY;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = TDRSMST.BAL;
        BPCPOEWA.DATA.VALUE_DATE = TDRSMST.VAL_DATE;
        CEP.TRC(SCCGWA, "=========");
        CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
        BPCPOEWA.DATA.AC_NO = TDRSMST.KEY.ACO_AC;
        BPCPOEWA.DATA.CI_NO = CICACCU.DATA.CI_NO;
        BPCPOEWA.DATA.AC_NO_REL = TDRSMST.OPEN_DR_AC;
        BPCPOEWA.DATA.THEIR_AC = TDRSMST.OPEN_DR_AC;
        BPCPOEWA.DATA.PORT = TDRBVT.BV_CD;
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
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-GET-SEQ", BPCSGSEQ);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_TDZRPLED() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-R-TDZRPLED", TDCRPLED);
        if (TDCRPLED.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, TDCRPLED.RC);
            S000_ERR_MSG_PROC();
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_STS_FLG = 'Y';
        } else {
            WS_STS_FLG = 'N';
        }
    }
    public void B230_WRI_NFIN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.TX_TYP_CD = "P120";
        BPCPNHIS.INFO.AC = TDCBVEXZ.MAIN_AC_O;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.CI_NO = CICACCU.DATA.CI_NO;
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.AC_SEQ = TDRHBVT.OAC_SEQ;
        BPCPNHIS.INFO.NEW_DAT_PT = TDCBVEXZ;
        BPCPNHIS.INFO.FMT_ID_LEN = 854;
        BPCPNHIS.INFO.FMT_ID = "TDCBVEXZ";
        S000_CALL_BPZPNHIS();
    }
    public void B230_WRI_NFIN_HIS_PLED() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.REF_NO = TDCPLDT.PLD_NO;
        if (TDCBVEXZ.BV_TYP_O == '1' 
            || (TDCBVEXZ.BV_TYP_O == '7' 
            || TDCBVEXZ.BV_TYP_O == '8')) {
            BPCPNHIS.INFO.TX_TOOL = TDCBVEXZ.MAIN_AC_O;
        }
        BPCPNHIS.INFO.TX_TYP_CD = "P120";
        BPCPNHIS.INFO.CI_NO = CICACCU.DATA.CI_NO;
        S000_CALL_BPZPNHIS();
    }
    public void B240_EXCHANGE_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "1747");
        CEP.TRC(SCCGWA, TDRBVT.KEY.AC_NO);
        CEP.TRC(SCCGWA, TDCBVEXZ.BV_TYP_O);
        CEP.TRC(SCCGWA, TDCBVEXZ.BV_TYP_I);
        CEP.TRC(SCCGWA, TDRCMST.CI_TYP);
        if (TDCBVEXZ.BV_TYP_O == '7' 
            && TDCBVEXZ.BV_TYP_I == '8') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_BV_B_TO_V;
            S000_ERR_MSG_PROC();
        }
        if ((TDCBVEXZ.BV_TYP_O == '7' 
            || TDCBVEXZ.BV_TYP_O == '8') 
            && TDCBVEXZ.BV_TYP_I == '3' 
            && TDRCMST.CI_TYP != '1' 
            && TDCBVEXZ.PROC_TYP.trim().length() == 0) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_PROC_TYP_IN;
            S000_ERR_MSG_PROC();
        }
        if (TDCBVEXZ.BV_TYP_O == '8' 
            && TDRSMST.PRDAC_CD.equalsIgnoreCase("021") 
            && TDCBVEXZ.BV_TYP_I != '3' 
            && TDCBVEXZ.BV_TYP_I != '7') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_BV_V_TO_B;
            S000_ERR_MSG_PROC();
        }
        if (!(TDCBVEXZ.BV_TYP_O == '3' 
            && (TDCBVEXZ.BV_TYP_I == '7' 
            || TDCBVEXZ.BV_TYP_I == '8'))) {
            R000_GET_CI_INFO();
            WS_O_CI_NO = CICACCU.DATA.CI_NO;
            R000_MOD_MMST_O();
            R000_READ_TDTBVT();
        }
        CEP.TRC(SCCGWA, "1781");
        CEP.TRC(SCCGWA, TDRBVT.KEY.AC_NO);
        if (TDCBVEXZ.BV_TYP_O == '3' 
            || TDCBVEXZ.BV_TYP_O == '1' 
            || TDCBVEXZ.BV_TYP_O == '5' 
            || TDCBVEXZ.BV_TYP_O == '7' 
            || TDCBVEXZ.BV_TYP_O == '8' 
            || TDCBVEXZ.BV_TYP_O == '4') {
            if (TDCBVEXZ.BV_TYP_O != '8') {
                R000_CUS_DEL_PROC();
                WS_O_CI_NO = CICACCU.DATA.CI_NO;
            }
        }
        CEP.TRC(SCCGWA, "1798");
        CEP.TRC(SCCGWA, TDRBVT.KEY.AC_NO);
        if (TDCBVEXZ.BV_TYP_O == '3' 
            && (TDCBVEXZ.BV_TYP_I != '7' 
            && TDCBVEXZ.BV_TYP_I != '8')) {
        } else {
            if (TDCBVEXZ.BV_TYP_O == '3' 
                || (TDCBVEXZ.BV_TYP_I == '3' 
                && (TDCBVEXZ.BV_TYP_O == '7' 
                || TDCBVEXZ.BV_TYP_O == '8')) 
                || (TDCBVEXZ.BV_TYP_I == '7' 
                && TDCBVEXZ.BV_TYP_O == '8')) {
                if (TDCBVEXZ.BV_TYP_O == '3') {
                    if (TDRBVT.STSW == null) TDRBVT.STSW = "";
                    JIBS_tmp_int = TDRBVT.STSW.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
                    if (TDRBVT.STSW.substring(55 - 1, 55 + 1 - 1).equalsIgnoreCase("1")) {
                        CEP.TRC(SCCGWA, TDCBVEXZ.MAIN_AC_O);
                        CEP.TRC(SCCGWA, TDCBVEXZ.BV_NO_O);
                        CEP.TRC(SCCGWA, TDCBVEXZ.BV_TYP_O);
                        CEP.TRC(SCCGWA, TDRBVT.BV_CD);
                        CEP.TRC(SCCGWA, TDCBVEXZ.AC_SEQ_O);
                        TDRHBVT.KEY.NAC = TDCBVEXZ.MAIN_AC_O;
                        TDRHBVT.KEY.NEW_BV_NO = TDCBVEXZ.BV_NO_O;
                        TDRHBVT.KEY.NEW_BV_TYP = TDCBVEXZ.BV_TYP_O;
                        TDRHBVT.KEY.NEW_BV_CD = TDRBVT.BV_CD;
                        TDRHBVT.KEY.NAC_SEQ = TDCBVEXZ.AC_SEQ_O;
                        T000_READ_TDTHBVT();
                        if (!TDCBVEXZ.MAIN_AC_I.equalsIgnoreCase(TDRHBVT.OAC) 
                            || !TDCBVEXZ.BV_NO_I.equalsIgnoreCase(TDRHBVT.OLD_BV_NO) 
                            || !TDCBVEXZ.BV_CD_I.equalsIgnoreCase(TDRHBVT.OLD_BV_CD)) {
                            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DATE_MISSING);
                        }
                        TDRSMST.AC_NO = TDRHBVT.OAC;
                    }
                    if (TDCBVEXZ.BV_TYP_I == '7') {
                        TDRSMST.BV_TYP = '7';
                    }
                    if (TDCBVEXZ.BV_TYP_I == '8') {
                        TDRSMST.BV_TYP = '8';
                    }
                    T000_UPDATE_TDTSMST();
                }
                if (TDCBVEXZ.BV_TYP_O == '3' 
                    || TDCBVEXZ.BV_TYP_O == '8') {
                    R000_MOD_ACR_O();
                }
                T000_READUP_TDTBVT();
                if (TDRSMST.BV_TYP == ' ' 
                    && TDCBVEXZ.MAIN_AC_I.trim().length() > 0) {
                    TDRBVT.KEY.AC_NO = TDCBVEXZ.MAIN_AC_I;
                }
                if ((TDCBVEXZ.BV_TYP_I == '7' 
                    || TDCBVEXZ.BV_TYP_I == '8') 
                    && TDCBVEXZ.BV_TYP_O == '3') {
                    if (TDRBVT.STSW == null) TDRBVT.STSW = "";
                    JIBS_tmp_int = TDRBVT.STSW.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
                    if (TDRBVT.STSW.substring(55 - 1, 55 + 1 - 1).equalsIgnoreCase("1")) {
                        if (TDRBVT.STSW == null) TDRBVT.STSW = "";
                        JIBS_tmp_int = TDRBVT.STSW.length();
                        for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
                        TDRBVT.STSW = TDRBVT.STSW.substring(0, 55 - 1) + "0" + TDRBVT.STSW.substring(55 + 1 - 1);
                    }
                }
                if (TDCBVEXZ.BV_TYP_I == '3' 
                    && TDCBVEXZ.BV_TYP_O == '7' 
                    && TDRCMST.CI_TYP != '1') {
                    if (TDRBVT.STSW == null) TDRBVT.STSW = "";
                    JIBS_tmp_int = TDRBVT.STSW.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
                    TDRBVT.STSW = TDRBVT.STSW.substring(0, 55 - 1) + "1" + TDRBVT.STSW.substring(55 + 1 - 1);
                }
                if (TDRBVT.STSW == null) TDRBVT.STSW = "";
                JIBS_tmp_int = TDRBVT.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
                CEP.TRC(SCCGWA, TDRBVT.STSW.substring(55 - 1, 55 + 1 - 1));
                CEP.TRC(SCCGWA, TDRBVT.KEY.AC_NO);
                TDRBVT.BV_NO = TDCBVEXZ.BV_NO_I;
                TDRBVT.BV_CD = TDCBVEXZ.BV_CD_I;
                T000_UPDATE_TDTBVT();
            }
            if (TDCBVEXZ.BV_TYP_I == '3' 
                && (TDCBVEXZ.BV_TYP_O != '7' 
                && TDCBVEXZ.BV_TYP_O != '8')) {
                CEP.TRC(SCCGWA, TDRSMST.AC_NO);
                TDRBVT.KEY.AC_NO = TDRSMST.AC_NO;
                TDRBVT.BV_NO = TDCBVEXZ.BV_NO_I;
                TDRBVT.BV_CD = TDCBVEXZ.BV_CD_I;
                TDRBVT.ISSU_DATE = SCCGWA.COMM_AREA.AC_DATE;
                TDRBVT.ISSU_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                TDRBVT.ISSU_TLR = SCCGWA.COMM_AREA.TL_ID;
                TDRBVT.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                TDRBVT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
                TDRBVT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
                TDRBVT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
                TDRBVT.UPD_TIME = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                TDRBVT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
                T000_WRITE_TDTBVT();
            }
            if (TDRCMST.CI_TYP == '1') {
                TDRCMST.BV_TYP = TDCBVEXZ.BV_TYP_I;
                T000_READ_TDTCMST();
                T000_UPDATE_TDTCMST();
            }
        }
        if (TDCBVEXZ.BV_TYP_I == '3' 
            || (TDCBVEXZ.BV_TYP_I == '7') 
            || TDCBVEXZ.BV_TYP_I == '1' 
            || TDCBVEXZ.BV_TYP_I == '4') {
            if (TDCBVEXZ.BV_TYP_I == '3') {
                R000_ADD_BVT_PROC();
            }
            if (TDCBVEXZ.BV_TYP_I == '1') {
                IBS.init(SCCGWA, TDRBVT);
                TDRBVT.KEY.AC_NO = TDCBVEXZ.MAIN_AC_I;
                CEP.TRC(SCCGWA, TDRBVT.KEY.AC_NO);
                CEP.TRC(SCCGWA, "UPDATE BVT-POS");
                T000_READUP_TDTBVT();
                TDRBVT.PSBK_POS = WS_CUR_BVT_POS_I;
                T000_UPDATE_TDTBVT();
            }
        }
        CEP.TRC(SCCGWA, "1111111111111");
        CEP.TRC(SCCGWA, TDCBVEXZ.BV_TYP_I);
        CEP.TRC(SCCGWA, WS_YBT_SIG_I);
        if (TDCBVEXZ.BV_TYP_I == '1') {
            CEP.TRC(SCCGWA, TDCBVEXZ.MAIN_AC_O);
            CEP.TRC(SCCGWA, TDCBVEXZ.MAIN_AC_I);
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = TDCBVEXZ.MAIN_AC_I;
            S000_CALL_CIZACCU();
            CEP.TRC(SCCGWA, WS_O_CI_NO);
            CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
            if (!WS_O_CI_NO.equalsIgnoreCase(CICACCU.DATA.CI_NO) 
                && TDCBVEXZ.BV_TYP_I != '3') {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CI_NO_NOT_SAME);
            }
        }
        R000_BV_PRT_PROC();
        CEP.TRC(SCCGWA, TDCBVEXZ.BV_TYP_O);
        CEP.TRC(SCCGWA, TDCBVEXZ.BV_TYP_I);
        if ((TDCBVEXZ.BV_TYP_O != '7' 
            && TDCBVEXZ.BV_TYP_O != '8') 
            && (TDCBVEXZ.BV_TYP_I != '7' 
            && TDCBVEXZ.BV_TYP_I != '8')) {
            B730_WRT_BPTOCAC();
        }
        if (TDCBVEXZ.BV_TYP_O == '3' 
            && (TDCBVEXZ.BV_TYP_I != '7' 
            && TDCBVEXZ.BV_TYP_I != '8')) {
            IBS.init(SCCGWA, CICSACR);
            CICSACR.FUNC = 'D';
            CICSACR.DATA.AGR_NO = TDCBVEXZ.MAIN_AC_O;
            CICSACR.DATA.ENTY_TYP = '1';
            S000_CALL_CIZSACR();
        }
    }
    public void B730_WRT_BPTOCAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSOCAC);
        BPCSOCAC.FUNC = 'U';
        if (TDCBVEXZ.BV_TYP_I == '3' 
            && WS_NEW_AC.trim().length() > 0) {
            BPCSOCAC.AC = WS_NEW_AC;
        } else {
            BPCSOCAC.AC = TDCBVEXZ.MAIN_AC_I;
        }
        if (TDCBVEXZ.BV_TYP_I == '3') {
            BPCSOCAC.NEW_BV_TYP = '5';
        } else {
            if (TDCBVEXZ.BV_TYP_I == '1') {
                BPCSOCAC.NEW_BV_TYP = '6';
            } else {
                BPCSOCAC.NEW_BV_TYP = '1';
            }
        }
        BPCSOCAC.NEW_BV_NO = TDCBVEXZ.BV_NO_I;
        BPCSOCAC.NEW_SEQ = CICSACAC.DATA.AGR_SEQ;
        BPCSOCAC.ACO_AC = TDRSMST.KEY.ACO_AC;
        BPCSOCAC.OLD_AC = TDCBVEXZ.MAIN_AC_O;
        BPCSOCAC.STS = 'D';
        BPCSOCAC.LOSS_TYP = '3';
        BPCSOCAC.REMARK = "TD-TDZBVEXZ";
        CEP.TRC(SCCGWA, BPCSOCAC.ACO_AC);
        CEP.TRC(SCCGWA, BPCSOCAC.AC);
        CEP.TRC(SCCGWA, BPCSOCAC.OLD_AC);
        CEP.TRC(SCCGWA, BPCSOCAC.STS);
        S000_CALL_BPZSOCAC();
    }
    public void B731_READ_BPCPOCAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOCAC);
        BPCPOCAC.INFO.FUNC = 'I';
        if (TDCBVEXZ.BV_TYP_I == '3') {
            BPCPOCAC.DATA_INFO.AC = BPCCGAC.DATA.CI_AC;
        } else {
            BPCPOCAC.DATA_INFO.AC = TDCBVEXZ.MAIN_AC_I;
        }
        BPCPOCAC.DATA_INFO.ACO_AC = TDRSMST.KEY.ACO_AC;
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.AC);
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.ACO_AC);
        S000_CALL_BPZPOCAC();
    }
    public void B732_UPDATE_BPTOCAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSOCAC);
        BPCSOCAC.FUNC = 'U';
        BPCSOCAC.AC = TDCBVEXZ.MAIN_AC_O;
        BPCSOCAC.ACO_AC = TDRSMST.KEY.ACO_AC;
        BPCSOCAC.STS = 'C';
        BPCSOCAC.LOSS_TYP = '3';
        BPCSOCAC.REMARK = "TD-TDZBVEXZ-C";
        CEP.TRC(SCCGWA, BPCSOCAC.ACO_AC);
        CEP.TRC(SCCGWA, BPCSOCAC.AC);
        CEP.TRC(SCCGWA, BPCSOCAC.OLD_AC);
        CEP.TRC(SCCGWA, BPCSOCAC.STS);
        S000_CALL_BPZSOCAC();
        IBS.init(SCCGWA, BPCSOCAC);
        BPCSOCAC.FUNC = 'U';
        BPCSOCAC.AC = TDCBVEXZ.MAIN_AC_I;
        BPCSOCAC.ACO_AC = TDRSMST.KEY.ACO_AC;
        BPCSOCAC.STS = 'N';
        BPCSOCAC.LOSS_TYP = '3';
        BPCSOCAC.REMARK = "TD-TDZBVEXZ-N";
        CEP.TRC(SCCGWA, BPCSOCAC.ACO_AC);
        CEP.TRC(SCCGWA, BPCSOCAC.AC);
        CEP.TRC(SCCGWA, BPCSOCAC.OLD_AC);
        CEP.TRC(SCCGWA, BPCSOCAC.STS);
        S000_CALL_BPZSOCAC();
    }
    public void S000_CALL_BPZSOCAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-OCAC-INFO", BPCSOCAC);
        if (BPCSOCAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCSOCAC.RC.RC_CODE);
        }
    }
    public void S000_CALL_BPZPOCAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-OCAC-INFO", BPCPOCAC);
        if (BPCPOCAC.RC.RC_CODE != 0 
            && BPCPOCAC.RC.RC_CODE != 1992) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPOCAC.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void B250_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCOBVEZ);
        TDCOBVEZ.BV_CD_O = TDCBVEXZ.BV_CD_O;
        TDCOBVEZ.BV_NO_O = TDCBVEXZ.BV_NO_O;
        CEP.TRC(SCCGWA, TDCBVEXZ.BV_CD_O);
        CEP.TRC(SCCGWA, TDCBVEXZ.BV_NO_O);
        TDCOBVEZ.MAIN_AC_O = TDCBVEXZ.MAIN_AC_O;
        TDCOBVEZ.AC_SEQ_O = TDCBVEXZ.AC_SEQ_O;
        TDCOBVEZ.DRAW_MTH_O = TDCBVEXZ.DRAW_MTH_O;
        if (TDCBVEXZ.BV_CD_I.trim().length() > 0) {
            TDCOBVEZ.BV_CD_I = TDCBVEXZ.BV_CD_I;
        }
        if (TDCBVEXZ.BV_NO_I.trim().length() > 0) {
            TDCOBVEZ.BV_NO_I = TDCBVEXZ.BV_NO_I;
        }
        if (TDCBVEXZ.BV_TYP_O == '9' 
            && TDCBVEXZ.BV_TYP_I == '7') {
            TDCOBVEZ.BV_CD_I = WS_BV_CD_I;
            TDCOBVEZ.BV_NO_I = WS_BV_NO_I;
        }
        if (TDCBVEXZ.BV_TYP_O == '3' 
            && (TDCBVEXZ.BV_TYP_I == '7' 
            || TDCBVEXZ.BV_TYP_I == '8')) {
            TDCOBVEZ.BV_CD_I = WS_BV_CD_I;
            TDCOBVEZ.BV_NO_I = WS_BV_NO_I;
        }
        if (TDCBVEXZ.BV_TYP_I == '3') {
            TDCOBVEZ.MAIN_AC_I = TDRSMST.AC_NO;
        } else {
            TDCOBVEZ.MAIN_AC_I = TDCBVEXZ.MAIN_AC_I;
        }
        CEP.TRC(SCCGWA, TDCOBVEZ.MAIN_AC_I);
        TDCOBVEZ.AC_SEQ_I = CICSACAC.DATA.AGR_SEQ;
        CEP.TRC(SCCGWA, "F-BUG14");
        CEP.TRC(SCCGWA, TDCBVEXZ.AC_NM);
        TDCOBVEZ.AC_NM = TDCBVEXZ.AC_NM;
        CEP.TRC(SCCGWA, TDRSMST.BAL);
        TDCOBVEZ.BAL = TDRSMST.BAL;
        CEP.TRC(SCCGWA, TDCOBVEZ.BAL);
        TDCOBVEZ.TERM = TDRSMST.TERM;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = TDCOBVEZ;
        SCCFMT.DATA_LEN = 402;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_STARTBR_TDTPLED_AC_BV_TYP() throws IOException,SQLException,Exception {
        WS_TDTPLED_FLAG = 'N';
        TDTPLED_BR.rp = new DBParm();
        TDTPLED_BR.rp.TableName = "TDTPLED";
        TDTPLED_BR.rp.where = "MAIN_AC = :WS_INQ_AC "
            + "AND BV_TYP = :WS_INQ_BV_TYP";
        TDTPLED_BR.rp.order = "CRT_DATE, TS";
        IBS.STARTBR(SCCGWA, TDRPLED, this, TDTPLED_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TDTPLED_FLAG = 'Y';
        } else {
            WS_TDTPLED_FLAG = 'N';
        }
    }
    public void T000_STARTBR_TDTPLED_BV_NO() throws IOException,SQLException,Exception {
        WS_TDTPLED_FLAG = 'N';
        TDTPLED_BR.rp = new DBParm();
        TDTPLED_BR.rp.TableName = "TDTPLED";
        TDTPLED_BR.rp.where = "MAIN_AC = :WS_INQ_AC "
            + "AND BV_NO = :WS_INQ_BV_NO";
        TDTPLED_BR.rp.order = "CRT_DATE, TS";
        IBS.STARTBR(SCCGWA, TDRPLED, this, TDTPLED_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TDTPLED_FLAG = 'Y';
        } else {
            WS_TDTPLED_FLAG = 'N';
        }
    }
    public void T000_STARTBR_TDTPLED_AC() throws IOException,SQLException,Exception {
        WS_TDTPLED_FLAG = 'N';
        TDTPLED_BR.rp = new DBParm();
        TDTPLED_BR.rp.TableName = "TDTPLED";
        TDTPLED_BR.rp.where = "MAIN_AC = :WS_INQ_AC";
        TDTPLED_BR.rp.order = "CRT_DATE, TS";
        IBS.STARTBR(SCCGWA, TDRPLED, this, TDTPLED_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TDTPLED_FLAG = 'Y';
        } else {
            WS_TDTPLED_FLAG = 'N';
        }
    }
    public void T000_STARTBR_TDTPLED_AC_BV() throws IOException,SQLException,Exception {
        WS_TDTPLED_FLAG = 'N';
        TDTPLED_BR.rp = new DBParm();
        TDTPLED_BR.rp.TableName = "TDTPLED";
        TDTPLED_BR.rp.where = "MAIN_AC = :WS_INQ_AC "
            + "AND BV_TYP = :WS_INQ_BV_TYP "
            + "AND BV_NO = :WS_INQ_BV_NO";
        TDTPLED_BR.rp.order = "CRT_DATE, TS";
        IBS.STARTBR(SCCGWA, TDRPLED, this, TDTPLED_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TDTPLED_FLAG = 'Y';
        } else {
            WS_TDTPLED_FLAG = 'N';
        }
    }
    public void T000_STARTBR_TDTPLED_PLD_NO() throws IOException,SQLException,Exception {
        WS_TDTPLED_FLAG = 'N';
        TDTPLED_BR.rp = new DBParm();
        TDTPLED_BR.rp.TableName = "TDTPLED";
        TDTPLED_BR.rp.where = "PLD_NO = :WS_INQ_PLD_NO";
        TDTPLED_BR.rp.order = "CRT_DATE, TS";
        IBS.STARTBR(SCCGWA, TDRPLED, this, TDTPLED_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "WS-PLED-FOUND");
            WS_TDTPLED_FLAG = 'Y';
        } else {
            CEP.TRC(SCCGWA, "WS-PLED-NOTFND");
            WS_TDTPLED_FLAG = 'N';
        }
    }
    public void T000_READNEXT_TDTPLED() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRPLED, this, TDTPLED_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "READ-FOUND");
            WS_TDTPLED_FLAG = 'Y';
        } else {
            CEP.TRC(SCCGWA, "READ-NOTFND");
            WS_TDTPLED_FLAG = 'N';
        }
    }
    public void T000_ENDBR_TDTPLED() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTPLED_BR);
    }
    public void R000_GET_CI_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = TDCBVEXZ.MAIN_AC_O;
        CEP.TRC(SCCGWA, CICACCU.DATA.AGR_NO);
        CEP.TRC(SCCGWA, CICACCU.DATA.ENTY_TYP);
        S000_CALL_CIZACCU();
        WS_CI_INFO.WS_CI_NO = CICACCU.DATA.CI_NO;
        WS_CI_INFO.WS_PROD_CD = CICACCU.DATA.PROD_CD;
        WS_CI_INFO.WS_CNTRCT_TYP = CICACCU.DATA.CNTRCT_TYP;
        WS_CI_INFO.WS_FRM_APP = CICACCU.DATA.FRM_APP;
        WS_CI_INFO.WS_CCY = CICACCU.DATA.CCY;
        WS_CI_INFO.WS_SHOW_FLG = CICACCU.DATA.SHOW_FLG;
        WS_CI_INFO.WS_OPN_BR = CICACCU.DATA.OPN_BR;
        WS_CI_INFO.WS_BBR = CICACCU.DATA.BBR;
        WS_CI_INFO.WS_OPEN_DT = CICACCU.DATA.OPEN_DT;
        WS_CI_INFO.WS_OIC = CICACCU.DATA.CI_OIC;
    }
    public void R000_MOD_MMST_O() throws IOException,SQLException,Exception {
        if (TDCBVEXZ.BV_TYP_I == '3' 
            && (TDCBVEXZ.BV_TYP_O != '7' 
            && TDCBVEXZ.BV_TYP_O != '8')) {
            IBS.init(SCCGWA, BPCCGAC);
            BPCCGAC.DATA.PRD_CODE = TDRSMST.PROD_CD;
            if (TDCBVEXZ.BV_TYP_O == '1' 
                || TDCBVEXZ.BV_TYP_O == '4') {
                BPCCGAC.DATA.CI_AC_FLG = '6';
            } else {
                BPCCGAC.DATA.CI_AC_FLG = '5';
            }
            BPCCGAC.DATA.CI_AC_TYPE = '2';
            BPCCGAC.DATA.AC_KIND = '1';
            CEP.TRC(SCCGWA, TDRSMST.PROD_CD);
            S000_CALL_BPZGACNO();
            TDRSMST.AC_NO = BPCCGAC.DATA.CI_AC;
            WS_NEW_AC = BPCCGAC.DATA.CI_AC;
            CEP.TRC(SCCGWA, TDRSMST.AC_NO);
            CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
            IBS.init(SCCGWA, CICSACR);
            CICSACR.DATA.STSW = "ZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROS";
            CICSACR.FUNC = 'A';
            CICSACR.DATA.AGR_NO = TDRSMST.AC_NO;
            CICSACR.DATA.ENTY_TYP = '1';
            CICSACR.DATA.CI_NO = CICACCU.DATA.CI_NO;
            if (CICSACR.DATA.STSW == null) CICSACR.DATA.STSW = "";
            JIBS_tmp_int = CICSACR.DATA.STSW.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) CICSACR.DATA.STSW += " ";
            CICSACR.DATA.STSW = "1" + CICSACR.DATA.STSW.substring(1);
            CICSACR.DATA.PROD_CD = TDRSMST.PROD_CD;
            CICSACR.DATA.CNTRCT_TYP = TDRSMST.PRDAC_CD;
            CICSACR.DATA.FRM_APP = "TD";
            CICSACR.DATA.CCY = TDRSMST.CCY;
            CICSACR.DATA.SHOW_FLG = 'Y';
            CICSACR.DATA.AC_CNM = CICACCU.DATA.AC_CNM;
            CICSACR.DATA.OPN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            CICSACR.DATA.OPEN_DT = SCCGWA.COMM_AREA.AC_DATE;
            CICSACR.DATA.OWNER_BK = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            S000_CALL_CIZSACR();
            TDRCMST.KEY.AC_NO = BPCCGAC.DATA.CI_AC;
            TDRCMST.STS = '0';
            if (TDRSMST.BV_TYP != '8' 
                && TDCBVEXZ.BV_TYP_O == '8') {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_HAVE_TRUE_BVNO);
            }
            if (TDRSMST.BV_TYP != ' ' 
                && TDRSMST.BV_TYP != '4') {
                TDRCMST.BV_TYP = '0';
                TDRSMST.BV_TYP = '3';
            } else {
                TDRCMST.BV_TYP = '3';
                TDRSMST.BV_TYP = ' ';
                TDRCMST.CI_TYP = '1';
            }
            if (TDCBVEXZ.BV_TYP_O == '4') {
                TDRSMST.STSW = "ZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROS";
                TDRCMST.CROS_DR_FLG = '1';
                TDRCMST.CROS_CR_FLG = '1';
            }
            TDRCMST.PROD_CD = " ";
            TDRCMST.OWNER_BR = TDRSMST.OWNER_BR;
            TDRCMST.OWNER_BRDP = TDRSMST.OWNER_BRDP;
            TDRCMST.OWNER_BK = TDRSMST.OWNER_BK;
            TDRCMST.CHE_BR = TDRSMST.CHE_BR;
            TDRCMST.OPEN_DATE = TDRSMST.OPEN_DATE;
            TDRCMST.OPEN_TLR = TDRSMST.CRT_TLR;
            TDRCMST.UPD_BR = TDRSMST.UPD_BR;
            TDRCMST.CHNL_NO = TDRSMST.CHNL_NO;
            TDRCMST.UPD_TLT = TDRSMST.UPD_TLT;
            TDRCMST.UPD_DATE = TDRSMST.UPD_DATE;
            TDRCMST.UPD_TIME = TDRSMST.UPD_TIME;
            T000_WRITE_TDTCMST();
            T000_READ_TDTCMST();
            if (TDCBVEXZ.BV_TYP_I == '3') {
                TDRCMST.DRAW_MTH = TDCBVEXZ.DRAW_MTH_O;
                if (TDCBVEXZ.DRAW_MTH_O == '1' 
                    || TDCBVEXZ.DRAW_MTH_O == '4') {
                    IBS.init(SCCGWA, TDCACM);
                    TDCACM.FUNC = 'R';
                    TDCACM.OLD_AC_NO = TDCBVEXZ.MAIN_AC_O;
                    TDCACM.AC_NO = WS_NEW_AC;
                    TDCACM.CARD_PSW_NEW = TDCBVEXZ.PSW_O;
                    TDCACM.CARD_PSW_OLD = TDCBVEXZ.PSW_O;
                    TDCACM.ID_TYP = CICACCU.DATA.ID_TYPE;
                    TDCACM.ID_NO = CICACCU.DATA.ID_NO;
                    TDCACM.CI_NM = CICACCU.DATA.CI_CNM;
                    S000_CALL_TDZACM();
                    T000_READ_TDTCMST();
                    TDRCMST.DRAW_MTH = TDCBVEXZ.DRAW_MTH_O;
                }
                if (TDCBVEXZ.DRAW_MTH_O == '3') {
                    if (TDRCMST.DRAW_INF == null) TDRCMST.DRAW_INF = "";
                    JIBS_tmp_int = TDRCMST.DRAW_INF.length();
                    for (int i=0;i<32-JIBS_tmp_int;i++) TDRCMST.DRAW_INF += " ";
                    if (TDCBVEXZ.ID_TYP_O == null) TDCBVEXZ.ID_TYP_O = "";
                    JIBS_tmp_int = TDCBVEXZ.ID_TYP_O.length();
                    for (int i=0;i<5-JIBS_tmp_int;i++) TDCBVEXZ.ID_TYP_O += " ";
                    TDRCMST.DRAW_INF = TDCBVEXZ.ID_TYP_O + TDRCMST.DRAW_INF.substring(5);
                    if (TDRCMST.DRAW_INF == null) TDRCMST.DRAW_INF = "";
                    JIBS_tmp_int = TDRCMST.DRAW_INF.length();
                    for (int i=0;i<32-JIBS_tmp_int;i++) TDRCMST.DRAW_INF += " ";
                    if (TDCBVEXZ.ID_NO_O == null) TDCBVEXZ.ID_NO_O = "";
                    JIBS_tmp_int = TDCBVEXZ.ID_NO_O.length();
                    for (int i=0;i<70-JIBS_tmp_int;i++) TDCBVEXZ.ID_NO_O += " ";
                    TDRCMST.DRAW_INF = TDRCMST.DRAW_INF.substring(0, 6 - 1) + TDCBVEXZ.ID_NO_O + TDRCMST.DRAW_INF.substring(6 + 25 - 1);
                }
            }
            T000_UPDATE_TDTCMST();
            TDRSMST.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
            TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            TDRSMST.PROC_NUM = (short) (TDRSMST.PROC_NUM + 1);
        } else {
            if (TDCBVEXZ.BV_TYP_O == '3') {
                TDRCMST.KEY.AC_NO = TDCBVEXZ.MAIN_AC_O;
                T000_READ_TDTCMST();
                TDRCMST.STS = '1';
                T000_UPDATE_TDTCMST();
            }
            CEP.TRC(SCCGWA, TDRCMST.CI_TYP);
            if (TDRSMST.BV_TYP != '8' 
                && TDCBVEXZ.BV_TYP_O == '8') {
                if (TDRSMST.PRDAC_CD.equalsIgnoreCase("037") 
                    || TDRSMST.PRDAC_CD.equalsIgnoreCase("021") 
                    && TDRCMST.CI_TYP == '1') {
                } else {
                    CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_HAVE_TRUE_BVNO);
                }
            }
            if (TDCBVEXZ.BV_TYP_O != '7' 
                && TDCBVEXZ.BV_TYP_O != '8') {
                TDRSMST.AC_NO = TDCBVEXZ.MAIN_AC_I;
            }
            if ((TDCBVEXZ.BV_TYP_O == '4' 
                || TDCBVEXZ.BV_TYP_O == '3') 
                && TDCBVEXZ.BV_TYP_I == '1') {
                TDRSMST.BV_TYP = ' ';
            } else {
                if (TDRCMST.CI_TYP == '1' 
                    && TDCBVEXZ.BV_TYP_I != '4') {
                    TDRCMST.KEY.AC_NO = TDCBVEXZ.MAIN_AC_I;
                    T000_READ_TDTCMST();
                    TDRCMST.PROC_SEQ += 1;
                    TDRCMST.BV_TYP = TDCBVEXZ.BV_TYP_I;
                    T000_UPDATE_TDTCMST();
                    if (TDRSMST.PRDAC_CD.equalsIgnoreCase("037")) {
                        TDRSMST.INSTR_MTH = '0';
                    }
                } else {
                    TDRSMST.BV_TYP = TDCBVEXZ.BV_TYP_I;
                }
            }
            TDRSMST.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
            TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            TDRSMST.PROC_NUM = (short) (TDRSMST.PROC_NUM + 1);
        }
        if (TDCBVEXZ.BV_TYP_O == '1') {
            R000_RWT_YBTP();
            R000_OUTPUT_YBTP();
        }
        IBS.init(SCCGWA, CICSACAC);
        CICSACAC.FUNC = 'M';
        CICSACAC.DATA.ACAC_NO = TDRSMST.KEY.ACO_AC;
        if (TDCBVEXZ.BV_TYP_I == '3') {
            CICSACAC.DATA.BV_NO = TDCBVEXZ.BV_NO_I;
            if (TDCBVEXZ.BV_TYP_O != '7' 
                && TDCBVEXZ.BV_TYP_O != '8') {
                CICSACAC.DATA.AGR_NO = TDRSMST.AC_NO;
                if (CICSACAC.DATA.ACAC_CTL == null) CICSACAC.DATA.ACAC_CTL = "";
                JIBS_tmp_int = CICSACAC.DATA.ACAC_CTL.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) CICSACAC.DATA.ACAC_CTL += " ";
                CICSACAC.DATA.ACAC_CTL = CICSACAC.DATA.ACAC_CTL.substring(0, 2 - 1) + "1" + CICSACAC.DATA.ACAC_CTL.substring(2 + 1 - 1);
                CICSACAC.DATA.NOSEQ_FLG = 'Y';
            }
        } else {
            if (TDCBVEXZ.BV_TYP_O == '8' 
                && TDCBVEXZ.BV_TYP_I == '7') {
                CICSACAC.DATA.BV_NO = TDCBVEXZ.BV_NO_I;
            }
            if (TDCBVEXZ.BV_TYP_I == '4' 
                || TDCBVEXZ.BV_TYP_I == '1') {
                if (CICSACAC.DATA.ACAC_CTL == null) CICSACAC.DATA.ACAC_CTL = "";
                JIBS_tmp_int = CICSACAC.DATA.ACAC_CTL.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) CICSACAC.DATA.ACAC_CTL += " ";
                CICSACAC.DATA.ACAC_CTL = CICSACAC.DATA.ACAC_CTL.substring(0, 2 - 1) + "0" + CICSACAC.DATA.ACAC_CTL.substring(2 + 1 - 1);
                CICSACAC.DATA.AGR_NO = TDCBVEXZ.MAIN_AC_I;
                CICSACAC.DATA.NOBV_FLG = 'Y';
            }
        }
        S000_CALL_CIZSACAC();
        T000_UPDATE_TDTSMST();
        CEP.TRC(SCCGWA, TDRSMST.AC_NO);
        CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
        CEP.TRC(SCCGWA, CICSACAC.DATA.AGR_SEQ);
    }
    public void R000_READ_TDTBVT() throws IOException,SQLException,Exception {
        WS_TABLES_INFO.WS_DRAW_MTH = TDRCMST.DRAW_MTH;
        WS_TABLES_INFO.WS_DRAW_INF = TDRCMST.DRAW_INF;
        WS_TABLES_INFO.WS_CROS_DR_FLG = TDRCMST.CROS_DR_FLG;
        WS_TABLES_INFO.WS_STSW = TDRBVT.STSW;
    }
    public void R000_MOD_SMST_O() throws IOException,SQLException,Exception {
        if (TDRSMST.BV_TYP != '8' 
            && TDCBVEXZ.BV_TYP_O == '8') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_HAVE_TRUE_BVNO);
        }
        TDRSMST.AC_NO = TDCBVEXZ.MAIN_AC_I;
        TDRSMST.BV_TYP = TDCBVEXZ.BV_TYP_I;
        TDRSMST.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRSMST.PROC_NUM = (short) (TDRSMST.PROC_NUM + 1);
        T000_UPDATE_TDTSMST();
        WS_SMST_MDY_FLG = 'Y';
    }
    public void R000_MOD_ACR_O() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSACAC);
        CICSACAC.FUNC = 'M';
        CICSACAC.DATA.ACAC_NO = TDRSMST.KEY.ACO_AC;
        if (TDRBVT.STSW == null) TDRBVT.STSW = "";
        JIBS_tmp_int = TDRBVT.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
        if (!TDRBVT.STSW.substring(55 - 1, 55 + 1 - 1).equalsIgnoreCase("1")) {
            CICSACAC.DATA.BV_NO = TDCBVEXZ.BV_NO_I;
        } else {
            CICSACAC.DATA.BV_NO = TDRHBVT.OLD_BV_NO;
        }
        S000_CALL_CIZSACAC();
    }
    public void R000_DEL_BVT_PROC() throws IOException,SQLException,Exception {
        TDRBVT.KEY.AC_NO = TDCBVEXZ.MAIN_AC_O;
        WS_TABLES_INFO.WS_CROS_DR_FLG = TDRCMST.CROS_DR_FLG;
        WS_TABLES_INFO.WS_STSW = TDRBVT.STSW;
        T000_DELETE_TDTBVT();
    }
    public void R000_CUS_DEL_PROC() throws IOException,SQLException,Exception {
        WS_CI_INFO.WS_CI_NO = CICACCU.DATA.CI_NO;
        WS_CI_INFO.WS_PROD_CD = CICACCU.DATA.PROD_CD;
        WS_CI_INFO.WS_CNTRCT_TYP = CICACCU.DATA.CNTRCT_TYP;
        WS_CI_INFO.WS_FRM_APP = CICACCU.DATA.FRM_APP;
        WS_CI_INFO.WS_CCY = CICACCU.DATA.CCY;
        WS_CI_INFO.WS_SHOW_FLG = CICACCU.DATA.SHOW_FLG;
        WS_CI_INFO.WS_OPN_BR = CICACCU.DATA.OPN_BR;
        WS_CI_INFO.WS_BBR = CICACCU.DATA.BBR;
        WS_CI_INFO.WS_OPEN_DT = CICACCU.DATA.OPEN_DT;
        WS_CI_INFO.WS_OIC = CICACCU.DATA.CI_OIC;
    }
    public void R000_MOD_MMST_I() throws IOException,SQLException,Exception {
    }
    public void R000_MOD_SMST_I() throws IOException,SQLException,Exception {
        TDRSMST.BV_TYP = TDCBVEXZ.BV_TYP_I;
        TDRSMST.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_UPDATE_TDTSMST();
    }
    public void R000_ADD_BVT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRBVT);
        TDRBVT.KEY.AC_NO = TDCBVEXZ.MAIN_AC_I;
        if (TDCBVEXZ.BV_TYP_O == '7' 
            || (TDCBVEXZ.BV_TYP_O == '1') 
            || TDCBVEXZ.BV_TYP_O == '8' 
            || TDCBVEXZ.BV_TYP_O == '4') {
            TDRBVT.STSW = "000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        } else {
            TDRBVT.STSW = WS_TABLES_INFO.WS_STSW;
        }
        if (TDCBVEXZ.BV_TYP_I == '3') {
            TDRBVT.BV_NO = TDCBVEXZ.BV_NO_I;
            WS_BV_NO_I = TDCBVEXZ.BV_NO_I;
            WS_BV_CD_I = TDCBVEXZ.BV_CD_I;
            TDRBVT.BV_CD = WS_BV_CD_I;
            CEP.TRC(SCCGWA, WS_BV_NO_I);
            CEP.TRC(SCCGWA, WS_BV_CD_I);
            if (TDRSMST.HBAL > 0) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_AMT_PART_HOLD);
            }
        }
        CEP.TRC(SCCGWA, TDRBVT.STSW);
        if (TDCBVEXZ.BV_TYP_I == '3') {
            if (TDCBVEXZ.DRAW_MTH_I == '1') {
            }
        }
        TDRBVT.PSBK_POS = WS_CUR_BVT_POS_I;
        TDRBVT.ISSU_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRBVT.ISSU_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRBVT.ISSU_TLR = SCCGWA.COMM_AREA.TL_ID;
        TDRBVT.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRBVT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRBVT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
    }
    public void R000_BV_PRT_PROC() throws IOException,SQLException,Exception {
        if (TDCBVEXZ.BV_TYP_I == '1') {
            IBS.init(SCCGWA, TDRIREV);
            TDRIREV.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
            TDRIREV.KEY.STR_DATE = SCCGWA.COMM_AREA.AC_DATE;
            TDRIREV.END_DATE = SCCGWA.COMM_AREA.AC_DATE;
            CEP.TRC(SCCGWA, TDRIREV.KEY.ACO_AC);
            T000_READ_TDTIREV();
            R000_WRT_YBTP();
            R000_OUTPUT_YBTP();
        }
        if (TDCBVEXZ.BV_TYP_I == '3' 
            || (TDCBVEXZ.BV_TYP_I == '7' 
            && TDCBVEXZ.BV_TYP_O == '8')) {
            R000_OUTPUT_BV();
        }
        R000_WRITE_TDTHBVT();
    }
    public void R000_OUTPUT_BV() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCPPRTF);
        if (TDCBVEXZ.BV_TYP_I == '3') {
            TDCPPRTF.AC = TDRSMST.AC_NO;
            TDCPPRTF.OPT = '3';
        } else {
            TDCPPRTF.AC = TDCBVEXZ.MAIN_AC_I;
            TDCPPRTF.OPT = '3';
        }
        CEP.TRC(SCCGWA, TDCPPRTF.AC);
        TDCPPRTF.ACO_AC = TDRSMST.KEY.ACO_AC;
        TDCPPRTF.PRDAC_CD = TDRSMST.PRDAC_CD;
        TDCPPRTF.AC_NAME = TDCBVEXZ.AC_NM;
        TDCPPRTF.BV_CD = TDCBVEXZ.BV_CD_I;
        TDCPPRTF.BV_TYP = TDCBVEXZ.BV_TYP_I;
        TDCPPRTF.BV_NO = TDCBVEXZ.BV_NO_I;
        TDCPPRTF.RMK = " ";
        S000_CALL_TDZPPRTF();
    }
    public void R000_WRITE_TDTHBVT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRHBVT);
        TDRHBVT.KEY.NEW_BV_CD = TDCBVEXZ.BV_CD_O;
        TDRHBVT.KEY.NEW_BV_TYP = TDCBVEXZ.BV_TYP_O;
        TDRHBVT.KEY.NEW_BV_NO = TDCBVEXZ.BV_NO_O;
        TDRHBVT.KEY.NAC = TDCBVEXZ.MAIN_AC_O;
        TDRHBVT.KEY.NAC_SEQ = TDCBVEXZ.AC_SEQ_O;
        T000_READ_A_TDTHBVT();
        WS_CHG_SEQ = TDRHBVT.KEY.CHG_SEQ;
        CEP.TRC(SCCGWA, WS_CHG_SEQ);
        WS_CHG_SEQ += 1;
        IBS.init(SCCGWA, TDRHBVT);
        if (TDCBVEXZ.BV_TYP_I == '3') {
            TDRHBVT.KEY.NAC = TDRSMST.AC_NO;
            TDRHBVT.KEY.NAC_SEQ = CICSACAC.DATA.AGR_SEQ;
        } else {
            TDRHBVT.KEY.NAC = TDCBVEXZ.MAIN_AC_I;
            TDRHBVT.KEY.NAC_SEQ = CICSACAC.DATA.AGR_SEQ;
        }
        TDRHBVT.KEY.NEW_BV_TYP = TDCBVEXZ.BV_TYP_I;
        TDRHBVT.KEY.NEW_BV_NO = TDCBVEXZ.BV_NO_I;
        TDRHBVT.KEY.NEW_BV_CD = TDCBVEXZ.BV_CD_I;
        if (TDCBVEXZ.BV_TYP_O == '7' 
            && TDRCMST.CI_TYP != '1') {
            TDRHBVT.CHA_RMK = '6';
            TDRHBVT.OLD_BV_CD = TDCBVEXZ.BV_CD_O;
        } else {
            if (TDCBVEXZ.BV_TYP_O == '8') {
                TDRHBVT.CHA_RMK = '7';
                TDRHBVT.OLD_BV_CD = TDCBVEXZ.BV_CD_O;
            } else {
                TDRHBVT.CHA_RMK = '2';
                TDRHBVT.OLD_BV_CD = TDCBVEXZ.BV_CD_O;
            }
        }
        TDRHBVT.OLD_BV_TYP = TDCBVEXZ.BV_TYP_O;
        TDRHBVT.OLD_BV_NO = TDCBVEXZ.BV_NO_O;
        TDRHBVT.OAC = TDCBVEXZ.MAIN_AC_O;
        if (TDCBVEXZ.AC_SEQ_O != 0) {
            TDRHBVT.OAC_SEQ = TDCBVEXZ.AC_SEQ_O;
        }
        TDRHBVT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRHBVT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRHBVT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        TDRHBVT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRHBVT.OWNER_BK = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRHBVT.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRHBVT.KEY.CHG_SEQ = WS_CHG_SEQ;
        TDRHBVT.PROC_TYP = TDCBVEXZ.PROC_TYP;
        T000_WRITE_TDTHBVT();
    }
    public void R000_REWRITE_TDTHBVT() throws IOException,SQLException,Exception {
        TDRHBVT.OAC_SEQ = TDCBVEXZ.AC_SEQ_O;
        TDRHBVT.CHA_RMK = '2';
        TDRHBVT.OLD_BV_CD = TDCBVEXZ.BV_CD_O;
        TDRHBVT.OLD_BV_TYP = TDCBVEXZ.BV_TYP_O;
        TDRHBVT.OLD_BV_NO = TDCBVEXZ.BV_NO_O;
        TDRHBVT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRHBVT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRHBVT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        TDRHBVT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRHBVT.OWNER_BK = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRHBVT.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_REWRITE_TDTHBVT();
    }
    public void R000_WRT_YBTP() throws IOException,SQLException,Exception {
        if (TDCBVEXZ.BV_TYP_I == '1') {
            TDRCMST.KEY.AC_NO = TDCBVEXZ.MAIN_AC_I;
            T000_READ_TDTCMST();
            TDRCMST.PROC_SEQ += 1;
            T000_UPDATE_TDTCMST();
        }
        IBS.init(SCCGWA, TDRYBTP);
        TDRYBTP.KEY.AC_NO = TDCBVEXZ.MAIN_AC_I;
        TDRYBTP.KEY.AC_SEQ = CICSACAC.DATA.AGR_SEQ;
        CEP.TRC(SCCGWA, TDRYBTP.KEY.PROC_SEQ);
        TDRYBTP.KEY.PROC_SEQ = TDRCMST.PROC_SEQ;
        TDRYBTP.PSBK_POS = (short) WS_CUR_BVT_POS_I;
        CEP.TRC(SCCGWA, WS_PROC_NUM);
        TDRYBTP.PART_NUM = TDRSMST.PART_NUM;
        TDRYBTP.PRT_STS = '1';
        TDRYBTP.VAL_DATE = TDRSMST.VAL_DATE;
        TDRYBTP.EXP_DATE = TDRSMST.EXP_DATE;
        TDRYBTP.BAL = TDRSMST.BAL;
        TDRYBTP.EXP_INT_RAT = TDRIREV.CON_RATE;
        TDRYBTP.EXP_INT = TDRSMST.EXP_INT;
        TDRYBTP.OPEN_DATE = TDRSMST.OPEN_DATE;
        TDRYBTP.OPEN_TLR = TDRSMST.CRT_TLR;
        TDRYBTP.OPEN_MMO = "P120";
        TDRYBTP.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRYBTP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRYBTP.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        TDRYBTP.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_WRITE_TDTYBTP();
    }
    public void R000_OUTPUT_YBTP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCPPRTF);
        TDCPPRTF.OPT = '1';
        if (TDCBVEXZ.BV_TYP_O == '1') {
            TDCPPRTF.AC = TDCBVEXZ.MAIN_AC_O;
            TDCPPRTF.BV_CD = TDCBVEXZ.BV_CD_O;
            TDCPPRTF.ACO_AC = TDRSMST.KEY.ACO_AC;
            TDCPPRTF.BV_NO = TDCBVEXZ.BV_NO_O;
            TDCPPRTF.BV_TYP = TDCBVEXZ.BV_TYP_O;
        } else {
            TDCPPRTF.AC = TDCBVEXZ.MAIN_AC_I;
            TDCPPRTF.ACO_AC = TDRSMST.KEY.ACO_AC;
            TDCPPRTF.BV_CD = TDCBVEXZ.BV_CD_I;
            TDCPPRTF.BV_NO = TDCBVEXZ.BV_NO_I;
            TDCPPRTF.BV_TYP = TDCBVEXZ.BV_TYP_I;
        }
        TDCPPRTF.RMK = " ";
        S000_CALL_TDZPPRTF();
    }
    public void R000_RWT_YBTP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRYBTP);
        TDRYBTP.KEY.AC_NO = TDCBVEXZ.MAIN_AC_O;
        TDRYBTP.KEY.AC_SEQ = TDCBVEXZ.AC_SEQ_O;
        TDRYBTP.PART_NUM = WS_PART_NUM;
        CEP.TRC(SCCGWA, TDRYBTP.KEY.AC_NO);
        CEP.TRC(SCCGWA, TDRYBTP.KEY.AC_SEQ);
        CEP.TRC(SCCGWA, TDRYBTP.PART_NUM);
        T000_READUP_TDTYBTP();
        if (TDRYBTP.PRT_STS == '1') {
            TDRYBTP.PRT_STS = '5';
        } else {
            TDRYBTP.PRT_STS = '3';
        }
        TDRYBTP.AMT = -1 * TDRSMST.BAL;
        TDRYBTP.INT = 0;
        TDRYBTP.TAX = 0;
        TDRYBTP.INT_RAT = TDRYBTP.EXP_INT_RAT;
        TDRYBTP.CLO_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRYBTP.CLO_TLR = SCCGWA.COMM_AREA.TL_ID;
        TDRYBTP.CLO_MMO = "P120";
        TDRYBTP.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRYBTP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_UPDATE_TDTYBTP();
    }
    public void R000_GEN_PSW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCHMPW);
        SCCHMPW.INP_DATA.SERV_ID = "E143";
        SCCHMPW.INP_DATA.AC_FLG = '0';
        if (TDCBVEXZ.MAIN_AC_O.trim().length() > 0) {
            SCCHMPW.INP_DATA.OLD_AC = TDCBVEXZ.MAIN_AC_O;
            SCCHMPW.INP_DATA.NEW_AC = TDCBVEXZ.MAIN_AC_O;
        } else {
            SCCHMPW.INP_DATA.OLD_AC = TDCBVEXZ.MAIN_AC_O;
            SCCHMPW.INP_DATA.NEW_AC = TDCBVEXZ.MAIN_AC_O;
        }
        SCCHMPW.INP_DATA.PIN_DA = TDCBVEXZ.PSW_O;
        CEP.TRC(SCCGWA, TDCBVEXZ.MAIN_AC_O);
        CEP.TRC(SCCGWA, TDCBVEXZ.PSW_O);
        CEP.TRC(SCCGWA, SCCHMPW.INP_DATA.OLD_AC);
        CEP.TRC(SCCGWA, SCCHMPW.INP_DATA.NEW_AC);
        CEP.TRC(SCCGWA, SCCHMPW.INP_DATA.PIN_DA);
        S000_CALL_SCZHMPW();
    }
    public void R000_CHK_PRDP_BV_TYP() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRSMST.PROD_CD);
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = TDRSMST.PROD_CD;
        CEP.TRC(SCCGWA, BPCPQPRD);
        S000_CALL_BPZPQPRD();
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
        if (TDCPRDP.TXN_PRM.BV_TYP == null) TDCPRDP.TXN_PRM.BV_TYP = "";
        JIBS_tmp_int = TDCPRDP.TXN_PRM.BV_TYP.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPRDP.TXN_PRM.BV_TYP += " ";
        CEP.TRC(SCCGWA, TDCPRDP.TXN_PRM.BV_TYP.substring(4 - 1, 4 + 1 - 1));
    }
    public void R000_CHK_PRDP_ALL_BV() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRSMST.PROD_CD);
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = TDRSMST.PROD_CD;
        CEP.TRC(SCCGWA, BPCPQPRD);
        S000_CALL_BPZPQPRD();
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
        CEP.TRC(SCCGWA, TDCPRDP.TXN_PRM.BV_TYP);
        if (TDCPRDP.TXN_PRM.BV_TYP == null) TDCPRDP.TXN_PRM.BV_TYP = "";
        JIBS_tmp_int = TDCPRDP.TXN_PRM.BV_TYP.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPRDP.TXN_PRM.BV_TYP += " ";
        if (!TDCPRDP.TXN_PRM.BV_TYP.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("Y") 
            && TDCBVEXZ.BV_TYP_I == '3' 
            && (TDCBVEXZ.BV_TYP_O != '7' 
            && TDCBVEXZ.BV_TYP_O != '8')) {
            CEP.TRC(SCCGWA, "F-BUG98");
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_PRD_BV_NOT_ALLOW;
            S000_ERR_MSG_PROC();
        }
        if (TDCPRDP.TXN_PRM.BV_TYP == null) TDCPRDP.TXN_PRM.BV_TYP = "";
        JIBS_tmp_int = TDCPRDP.TXN_PRM.BV_TYP.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPRDP.TXN_PRM.BV_TYP += " ";
        if (!TDCPRDP.TXN_PRM.BV_TYP.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("Y") 
            && TDCBVEXZ.BV_TYP_I == '1') {
            CEP.TRC(SCCGWA, "F-BUG98");
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_PRD_BV_NOT_YBP;
            S000_ERR_MSG_PROC();
        }
        if (TDCPRDP.TXN_PRM.BV_TYP == null) TDCPRDP.TXN_PRM.BV_TYP = "";
        JIBS_tmp_int = TDCPRDP.TXN_PRM.BV_TYP.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPRDP.TXN_PRM.BV_TYP += " ";
        if (!TDCPRDP.TXN_PRM.BV_TYP.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("Y") 
            && TDCBVEXZ.BV_TYP_I == '4') {
            CEP.TRC(SCCGWA, "F-BUG98");
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_PRD_BV_NOT_CAR;
            S000_ERR_MSG_PROC();
        }
        if (TDCPRDP.TXN_PRM.BV_TYP == null) TDCPRDP.TXN_PRM.BV_TYP = "";
        JIBS_tmp_int = TDCPRDP.TXN_PRM.BV_TYP.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPRDP.TXN_PRM.BV_TYP += " ";
        if (!TDCPRDP.TXN_PRM.BV_TYP.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("Y") 
            && TDCBVEXZ.BV_TYP_I == '7') {
            CEP.TRC(SCCGWA, "F-BUG98");
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_PRD_BV_NOT_CAR;
            S000_ERR_MSG_PROC();
        }
        if (TDCPRDP.TXN_PRM.BV_TYP == null) TDCPRDP.TXN_PRM.BV_TYP = "";
        JIBS_tmp_int = TDCPRDP.TXN_PRM.BV_TYP.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) TDCPRDP.TXN_PRM.BV_TYP += " ";
        if (!TDCPRDP.TXN_PRM.BV_TYP.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("Y") 
            && TDCBVEXZ.BV_TYP_I == '8') {
            CEP.TRC(SCCGWA, "F-BUG98");
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_PRD_BV_NOT_CAR;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_TDZACM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-AC-PSW-CHECK", TDCACM);
    }
    public void S000_CALL_TDZUPARM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-UNIT-TDPARM", TDCUPARM);
    }
    public void S000_CALL_BPZUBUSE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-TLR-BV-USE", BPCUBUSE);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DCZPCDCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-P-CARD-TRKDAT-CHK", DCCPCDCK);
    }
    public void S000_CALL_SCZHMPW() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCHMPW);
        IBS.CALLCPN(SCCGWA, "SC-HM-PASSWORD", SCCHMPW);
        CEP.TRC(SCCGWA, SCCHMPW.OUT_INFO.ERR_CODE);
        if (!SCCHMPW.OUT_INFO.ERR_CODE.equalsIgnoreCase("SC0000")) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_PWD_ERROR;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
    }
    public void S000_CALL_DCZIQBAL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "S000-CALL-DCZIQBAL");
        IBS.CALLCPN(SCCGWA, "DC-I-INQ-VIA-BAL", DCCIQBAL);
        CEP.TRC(SCCGWA, DCCIQBAL.RC);
        if (DCCIQBAL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCIQBAL.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_TDZPPRTF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-PSBK-PRTF", TDCPPRTF);
        if (TDCPPRTF.RC_MSG.RC != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, TDCPPRTF.RC_MSG);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DCCUOPNP_RTN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-OPN-VIAP", DCCUOPNP);
    }
    public void S000_CALL_DCCUCLSP_RTN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CLS-VIAP", DCCUCLSP);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_DCZIMSTR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-INQ-IAMST", DCCIMSTR);
        if (DCCIMSTR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCIMSTR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DCZUQSAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-INQ-SUB-AC", DCCUQSAC);
        if (DCCUQSAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUQSAC.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DCZIMSTT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-MST-UPD-PRT", DCCIMSTT);
        if (DCCIMSTT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCIMSTT.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DCZIACRB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-ACRB-PROC", DCCIACRB);
        if (DCCIACRB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCIACRB.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DCZIACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-ACRL-PROC", DCCIACRL);
        if (DCCIACRL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCIACRL.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_TDZQPMP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-BASE-PRD-QRY", TDCQPMP);
        CEP.TRC(SCCGWA, TDCQPMP.RC.RC_RTNCODE);
        if (TDCQPMP.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, TDCQPMP.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZSACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACR", CICSACR);
        if (CICSACR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICSACR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPEBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-BASIC-EWA", BPCPEBAS);
        if (BPCPEBAS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPEBAS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, TDRSMST.CHE_BR);
        IBS.init(SCCGWA, BPCPRGST);
        BPCPRGST.BNK = SCCGWA.COMM_AREA.TR_BANK;
        BPCPRGST.BR1 = BPCPORUP.DATA_INFO.BBR;
        BPCPRGST.BR2 = TDRSMST.OWNER_BR;
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG-STATION", BPCPRGST);
        CEP.TRC(SCCGWA, BPCPRGST.RC.RC_CODE);
        if (BPCPRGST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRGST.RC);
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPCPRGST.FLAG);
        CEP.TRC(SCCGWA, BPCPRGST.LVL_RELATION);
        CEP.TRC(SCCGWA, BPCPRGST.BRANCH_FLG);
    }
    public void S000_CALL_CIZSACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACAC", CICSACAC);
        if (CICSACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICSACAC.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZGACNO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-GENERTE-ACNO", BPCCGAC);
        if (BPCCGAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCCGAC.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF ", DCCUCINF);
        if (DCCUCINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_TDTIREV() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRIREV.KEY.ACO_AC);
        TDTIREV_RD = new DBParm();
        TDTIREV_RD.TableName = "TDTIREV";
        TDTIREV_RD.eqWhere = "ACO_AC";
        TDTIREV_RD.where = "( STR_DATE <= :TDRIREV.KEY.STR_DATE "
            + "AND END_DATE >= :TDRIREV.END_DATE ) "
            + "OR ( END_DATE < :TDRIREV.END_DATE )";
        TDTIREV_RD.fst = true;
        TDTIREV_RD.order = "END_DATE DESC";
        IBS.READ(SCCGWA, TDRIREV, this, TDTIREV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_IREV_NOFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_TDTCMST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BEGIN READ");
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        TDTCMST_RD.where = "AC_NO = :TDRCMST.KEY.AC_NO "
            + "AND STS = '0'";
        TDTCMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRCMST, this, TDTCMST_RD);
        CEP.TRC(SCCGWA, "END READ");
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_WRITE_TDTCMST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BEGIN READ");
        TDRCMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRCMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRCMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        IBS.WRITE(SCCGWA, TDRCMST, TDTCMST_RD);
        CEP.TRC(SCCGWA, "END READ");
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_UPDATE_TDTCMST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BEGIN READ");
        TDRCMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRCMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRCMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        IBS.REWRITE(SCCGWA, TDRCMST, TDTCMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READUP_TDTSMST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BEGIN READ");
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        CEP.TRC(SCCGWA, "END READ");
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_UPDATE_TDTSMST() throws IOException,SQLException,Exception {
        TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRSMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.REWRITE(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
        }
    }
    public void T000_READ_TDTBVT() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        IBS.READ(SCCGWA, TDRBVT, TDTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        } else {
            if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void T000_READ_TDTHBVT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BEGIN READ");
        TDTHBVT_RD = new DBParm();
        TDTHBVT_RD.TableName = "TDTHBVT";
        TDTHBVT_RD.where = "NEW_BV_CD = :TDRHBVT.KEY.NEW_BV_CD "
            + "AND NEW_BV_TYP = :TDRHBVT.KEY.NEW_BV_TYP "
            + "AND NEW_BV_NO = :TDRHBVT.KEY.NEW_BV_NO "
            + "AND NAC = :TDRHBVT.KEY.NAC "
            + "AND NAC_SEQ = :TDRHBVT.KEY.NAC_SEQ";
        TDTHBVT_RD.fst = true;
        TDTHBVT_RD.order = "CHG_SEQ DESC";
        IBS.READ(SCCGWA, TDRHBVT, this, TDTHBVT_RD);
        CEP.TRC(SCCGWA, "END READ");
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_A_TDTHBVT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BEGIN READ");
        TDTHBVT_RD = new DBParm();
        TDTHBVT_RD.TableName = "TDTHBVT";
        TDTHBVT_RD.where = "NEW_BV_CD = :TDRHBVT.KEY.NEW_BV_CD "
            + "AND NEW_BV_TYP = :TDRHBVT.KEY.NEW_BV_TYP "
            + "AND NEW_BV_NO = :TDRHBVT.KEY.NEW_BV_NO "
            + "AND NAC = :TDRHBVT.KEY.NAC "
            + "AND NAC_SEQ = :TDRHBVT.KEY.NAC_SEQ";
        TDTHBVT_RD.fst = true;
        TDTHBVT_RD.order = "CHG_SEQ DESC";
        IBS.READ(SCCGWA, TDRHBVT, this, TDTHBVT_RD);
        CEP.TRC(SCCGWA, "END READ");
    }
    public void T000_READ_TDTHBVT_O() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BEGIN READ");
        TDTHBVT_RD = new DBParm();
        TDTHBVT_RD.TableName = "TDTHBVT";
        TDTHBVT_RD.where = "NAC = :TDRHBVT.KEY.NAC "
            + "AND NAC_SEQ = :TDRHBVT.KEY.NAC_SEQ "
            + "AND NEW_BV_TYP = :TDRHBVT.KEY.NEW_BV_TYP "
            + "AND NEW_BV_NO = :TDRHBVT.KEY.NEW_BV_NO "
            + "AND NEW_BV_CD = :TDRHBVT.KEY.NEW_BV_CD";
        TDTHBVT_RD.upd = true;
        IBS.READ(SCCGWA, TDRHBVT, this, TDTHBVT_RD);
        CEP.TRC(SCCGWA, "END READ");
    }
    public void T000_REWRITE_TDTHBVT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BEGIN READ");
        TDRHBVT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRHBVT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRHBVT.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTHBVT_RD = new DBParm();
        TDTHBVT_RD.TableName = "TDTHBVT";
        IBS.REWRITE(SCCGWA, TDRHBVT, TDTHBVT_RD);
        CEP.TRC(SCCGWA, "END READ");
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_WRITE_TDTHBVT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BEGIN READ");
        TDRHBVT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRHBVT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRHBVT.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTHBVT_RD = new DBParm();
        TDTHBVT_RD.TableName = "TDTHBVT";
        IBS.WRITE(SCCGWA, TDRHBVT, TDTHBVT_RD);
        CEP.TRC(SCCGWA, "END READ");
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_TDTBVT_LAST() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        TDTBVT_RD.where = "AC_NO = :TDRBVT.KEY.AC_NO";
        TDTBVT_RD.fst = true;
        IBS.READ(SCCGWA, TDRBVT, this, TDTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READUP_TDTBVT() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        TDTBVT_RD.upd = true;
        IBS.READ(SCCGWA, TDRBVT, TDTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_UPDATE_TDTBVT() throws IOException,SQLException,Exception {
        TDRBVT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRBVT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRBVT.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        IBS.REWRITE(SCCGWA, TDRBVT, TDTBVT_RD);
    }
    public void T000_WRITE_TDTBVT() throws IOException,SQLException,Exception {
        TDRBVT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRBVT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRBVT.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        IBS.WRITE(SCCGWA, TDRBVT, TDTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
        }
    }
    public void T000_WRITE_TDTYBTP() throws IOException,SQLException,Exception {
        TDRYBTP.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRYBTP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRYBTP.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTYBTP_RD = new DBParm();
        TDTYBTP_RD.TableName = "TDTYBTP";
        IBS.WRITE(SCCGWA, TDRYBTP, TDTYBTP_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READ_TDTYBTP() throws IOException,SQLException,Exception {
        TDTYBTP_RD = new DBParm();
        TDTYBTP_RD.TableName = "TDTYBTP";
        TDTYBTP_RD.where = "AC_NO = :TDRYBTP.KEY.AC_NO "
            + "AND AC_SEQ = :TDRYBTP.KEY.AC_SEQ";
        TDTYBTP_RD.fst = true;
        TDTYBTP_RD.order = "PROC_SEQ DESC";
        IBS.READ(SCCGWA, TDRYBTP, this, TDTYBTP_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READUP_TDTYBTP() throws IOException,SQLException,Exception {
        TDTYBTP_RD = new DBParm();
        TDTYBTP_RD.TableName = "TDTYBTP";
        TDTYBTP_RD.where = "AC_NO = :TDRYBTP.KEY.AC_NO "
            + "AND AC_SEQ = :TDRYBTP.KEY.AC_SEQ "
            + "AND PART_NUM = :TDRYBTP.PART_NUM "
            + "AND PRT_STS <= '2'";
        TDTYBTP_RD.upd = true;
        IBS.READ(SCCGWA, TDRYBTP, this, TDTYBTP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_UPDATE_TDTYBTP() throws IOException,SQLException,Exception {
        TDRYBTP.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRYBTP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRYBTP.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTYBTP_RD = new DBParm();
        TDTYBTP_RD.TableName = "TDTYBTP";
        IBS.REWRITE(SCCGWA, TDRYBTP, TDTYBTP_RD);
    }
    public void T000_WRITE_TDTPBP() throws IOException,SQLException,Exception {
        TDRPBP.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRPBP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRPBP.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTPBP_RD = new DBParm();
        TDTPBP_RD.TableName = "TDTPBP";
        IBS.WRITE(SCCGWA, TDRPBP, TDTPBP_RD);
    }
    public void T000_READ_UPDATE_TDTPBP() throws IOException,SQLException,Exception {
        TDTPBP_RD = new DBParm();
        TDTPBP_RD.TableName = "TDTPBP";
        TDTPBP_RD.upd = true;
        IBS.READ(SCCGWA, TDRPBP, TDTPBP_RD);
    }
    public void T000_UPDATE_TDTPBP() throws IOException,SQLException,Exception {
        TDRPBP.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRPBP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRPBP.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTPBP_RD = new DBParm();
        TDTPBP_RD.TableName = "TDTPBP";
        IBS.REWRITE(SCCGWA, TDRPBP, TDTPBP_RD);
    }
    public void T000_WRITE_TDTSTS() throws IOException,SQLException,Exception {
        TDRSTS.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRSTS.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRSTS.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTSTS_RD = new DBParm();
        TDTSTS_RD.TableName = "TDTSTS";
        IBS.WRITE(SCCGWA, TDRSTS, TDTSTS_RD);
    }
    public void T000_REWRITE_TDTSTS() throws IOException,SQLException,Exception {
        TDRSTS.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRSTS.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRSTS.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTSTS_RD = new DBParm();
        TDTSTS_RD.TableName = "TDTSTS";
        IBS.REWRITE(SCCGWA, TDRSTS, TDTSTS_RD);
    }
    public void T000_READUP_TDTSTS() throws IOException,SQLException,Exception {
        TDTSTS_RD = new DBParm();
        TDTSTS_RD.TableName = "TDTSTS";
        TDTSTS_RD.upd = true;
        IBS.READ(SCCGWA, TDRSTS, TDTSTS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_STS_FLG = 'Y';
        } else {
            WS_STS_FLG = 'N';
        }
    }
    public void T000_DELETE_TDTBVT() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        IBS.DELETE(SCCGWA, TDRBVT, TDTBVT_RD);
    }
    public void S000_CALL_DCZILNKR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-INQ-ACLNK", DCCILNKR);
        if (DCCILNKR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCILNKR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZSACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACRL", CICSACRL);
        CEP.TRC(SCCGWA, CICSACRL.RC.RC_CODE);
        if (CICSACRL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICSACRL.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
        if (CICQACRL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACRL.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void T000_READ_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.where = "ACO_AC = :TDRSMST.KEY.ACO_AC "
            + "AND ACO_STS = '0'";
        TDTSMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRSMST, this, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_TDTSMST_C() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.where = "AC_NO = :TDRSMST.AC_NO";
        TDTSMST_RD.upd = true;
        TDTSMST_RD.fst = true;
        IBS.READ(SCCGWA, TDRSMST, this, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_TDTSMST_ACBR() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
