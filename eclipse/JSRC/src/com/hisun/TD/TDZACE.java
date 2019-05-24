package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCSUBS;
import com.hisun.BP.*;
import com.hisun.DC.*;
import com.hisun.DP.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class TDZACE {
    DBParm TDTCMST_RD;
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm TDTSMST_RD;
    DBParm TDTCDI_RD;
    brParm TDTSMST_BR = new brParm();
    DBParm TDTBVT_RD;
    DBParm TDTAINT_RD;
    DBParm TDTOTHE_RD;
    DBParm TDTINST_RD;
    DBParm TDTIREV_RD;
    brParm TDTDOCU_BR = new brParm();
    boolean pgmRtn = false;
    String K_INQ_MACT_FMT = "TD504";
    String K_INQ_MACD_FMT = "TD505";
    String K_INQ_SACD_FMT = "TD506";
    String K_OUTPUT_FMT1 = "TD522";
    int K_OUTPUT_ROW = 10;
    String K_PRDP_TYP = "PRDPR";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    int WS_K = 0;
    int WS_T = 0;
    int WS_TT = 0;
    int WS_CNT = 0;
    char WS_CCY_FOUND = ' ';
    int WS_TOT_PAGE = 0;
    int WS_LEFT = 0;
    String WS_SUB_AC = " ";
    String WS_DDAC_CINM = " ";
    String WS_TMP_AC = " ";
    String WS_TMP_CINM = " ";
    String WS_RUL_1 = " ";
    String WS_RUL_2 = " ";
    String WS_RUL_3 = " ";
    String WS_RUL_4 = " ";
    String WS_RUL_5 = " ";
    String WS_RUL_6 = " ";
    String WS_RUL_7 = " ";
    String WS_RUL_8 = " ";
    long WS_BALT_9 = 0;
    short WS_LVLT_9 = 0;
    int WS_BRT_9 = 0;
    char WS_V_DRAW_MTH = ' ';
    short WS_TMP_PSBK_SEQ = 0;
    TDZACE_CP_PROD_CD CP_PROD_CD = new TDZACE_CP_PROD_CD();
    int WS_J = 0;
    String WS_DOCU_NO = " ";
    String WS_TMP_BVNO = " ";
    String WS_TD_AC = " ";
    int WS_FST_DATE = 0;
    String WS_TERM_T = " ";
    TDZACE_REDEFINES36 REDEFINES36 = new TDZACE_REDEFINES36();
    String WS_TERM_KD = " ";
    TDZACE_REDEFINES40 REDEFINES40 = new TDZACE_REDEFINES40();
    int WS_DATE1 = 0;
    TDZACE_REDEFINES44 REDEFINES44 = new TDZACE_REDEFINES44();
    int WS_DATE2 = 0;
    TDZACE_REDEFINES49 REDEFINES49 = new TDZACE_REDEFINES49();
    short WS_MTHS = 0;
    String WS_AC_QCD = " ";
    char WS_SMST_END_FLG = ' ';
    double WS_OUTPUT_RAT = 0;
    char WS_LM_CARD_FLG = ' ';
    String WS_LM_CARD_NO = " ";
    TDZACE_WS_DOCU_DATE[] WS_DOCU_DATE = new TDZACE_WS_DOCU_DATE[30];
    TDZACE_WS_DOCU_INFO_TMP WS_DOCU_INFO_TMP = new TDZACE_WS_DOCU_INFO_TMP();
    char WS_IAACR_FLG = ' ';
    char WS_MR_FLAG = ' ';
    char WS_DOCU_FLG = ' ';
    char WS_MMST_FLG = ' ';
    char WS_SMST_FLG = ' ';
    char WS_IAACR_READ_FLG = ' ';
    char WS_CALL_READ_FLG = ' ';
    int WS_L_CNT = 0;
    int WS_Q_CNT = 0;
    int WS_P_ROW = 0;
    int WS_P_NUM = 0;
    int WS_T_PAGE = 0;
    char WS_FSE_FLG = ' ';
    int WS_L_ROW = 0;
    String WS_BVT_AC = " ";
    int WS_NUM1 = 0;
    int WS_TIME = 0;
    int WS_STR_NUM = 0;
    int WS_END_NUM = 0;
    double WS_AVAL_BAL1 = 0;
    TDZACE_WS_LIST WS_LIST = new TDZACE_WS_LIST();
    char WS_YBT_AC_FLAG = ' ';
    char WS_BV_TYP_INFU = ' ';
    char WS_Q_ENTY_TYP = ' ';
    int WS_COUNT = 0;
    double WS_GROP_INT = 0;
    double WS_GROP_AMT = 0;
    char WS_DEFAC_FLG = ' ';
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCTPDME BPCTPDME = new BPCTPDME();
    BPRPDME BPRPDME = new BPRPDME();
    TDRIREV TDRIREV = new TDRIREV();
    TDRBVT TDRBVT = new TDRBVT();
    DCRIAMST DCRIAMST = new DCRIAMST();
    DCRIAACR DCRIAACR = new DCRIAACR();
    TDRSMST TDRSMST = new TDRSMST();
    TDRCDI TDRCDI = new TDRCDI();
    TDRINST TDRINST = new TDRINST();
    TDCOIMAT TDCOIMAT = new TDCOIMAT();
    TDCOIMAD TDCOIMAD = new TDCOIMAD();
    TDCOISAD TDCOISAD = new TDCOISAD();
    TDRDOCU TDRDOCU = new TDRDOCU();
    DPCPARMP DPCPARMP = new DPCPARMP();
    CICACCU CICACCU = new CICACCU();
    TDRAINT TDRAINT = new TDRAINT();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DCCUSPAC DCCUSPAC = new DCCUSPAC();
    TDCUYBTQ TDCUYBTQ = new TDCUYBTQ();
    TDCPRDP TDCPRDP = new TDCPRDP();
    TDCPIOD TDCPIOD = new TDCPIOD();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    TDRCALL TDRCALL = new TDRCALL();
    BPCCINTI BPCCINTI = new BPCCINTI();
    TDRCMST TDRCMST = new TDRCMST();
    CICQACRI CICQACRI = new CICQACRI();
    CICQACAC CICQACAC = new CICQACAC();
    TDROTHE TDROTHE = new TDROTHE();
    TDCOACE TDCOACE = new TDCOACE();
    SCCCLDT SCCCLDT = new SCCCLDT();
    TDCPROD TDCPROD = new TDCPROD();
    TDCQPMP TDCQPMP = new TDCQPMP();
    SCCGWA SCCGWA;
    TDCACE TDCACE;
    public TDZACE() {
        for (int i=0;i<30;i++) WS_DOCU_DATE[i] = new TDZACE_WS_DOCU_DATE();
    }
    public void MP(SCCGWA SCCGWA, TDCACE TDCACE) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCACE = TDCACE;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDZACE return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCOACE);
        IBS.init(SCCGWA, TDRBVT);
        IBS.init(SCCGWA, CICQACAC);
        IBS.init(SCCGWA, TDRCMST);
        IBS.init(SCCGWA, TDRSMST);
        IBS.init(SCCGWA, CICQACRI);
        CEP.TRC(SCCGWA, TDCACE.PAGE_INF.I_BV_TYP);
        CEP.TRC(SCCGWA, TDCACE.PAGE_INF.AC_NO);
        CEP.TRC(SCCGWA, TDCACE.PAGE_INF.I_AC_SEQ);
        CEP.TRC(SCCGWA, TDCACE.PAGE_INF.I_BV_NO);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCACE.PAGE_INF.AC_NO);
        CEP.TRC(SCCGWA, TDCACE.PAGE_INF.I_BV_NO);
        CEP.TRC(SCCGWA, TDCACE.PAGE_INF.A_ACO_AC);
        if (TDCACE.PAGE_INF.A_ACO_AC.trim().length() == 0) {
            B100_CHK_AC_BV_PROC();
            if (pgmRtn) return;
        }
        B110_FUND_MSG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.SERV_CODE);
        if (TDCACE.FMT_FLG == 'Y') {
            B120_OUT_MSG();
            if (pgmRtn) return;
        }
    }
    public void B100_CHK_AC_BV_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCACE.PAGE_INF.AC_NO);
        CEP.TRC(SCCGWA, TDCACE.PAGE_INF.I_AC_SEQ);
        CEP.TRC(SCCGWA, TDCACE.PAGE_INF.I_BV_NO);
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = TDCACE.PAGE_INF.AC_NO;
        S110_CALL_CIZQACRI();
        if (pgmRtn) return;
        WS_Q_ENTY_TYP = CICQACRI.O_DATA.O_ENTY_TYP;
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_ENTY_TYP);
        IBS.init(SCCGWA, TDRCMST);
        if (CICQACRI.O_DATA.O_ENTY_TYP != '2' 
            && CICQACRI.O_DATA.O_ENTY_TYP != '5' 
            && CICQACRI.O_DATA.O_ENTY_TYP != '6' 
            && CICQACRI.O_DATA.O_ENTY_TYP != '7') {
            CEP.TRC(SCCGWA, TDCACE.PAGE_INF.AC_NO);
            IBS.init(SCCGWA, TDRCMST);
            TDRCMST.KEY.AC_NO = TDCACE.PAGE_INF.AC_NO;
            T001_READ_TDTCMST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, TDRCMST.BV_TYP);
            CEP.TRC(SCCGWA, TDCACE.PAGE_INF.I_BV_TYP);
            TDRBVT.KEY.AC_NO = TDRCMST.KEY.AC_NO;
            if (TDRCMST.BV_TYP != '0' 
                && TDCACE.PAGE_INF.I_BV_TYP != ' ') {
                TDRBVT.KEY.AC_NO = TDRCMST.KEY.AC_NO;
                CEP.TRC(SCCGWA, TDRBVT.KEY.AC_NO);
                if (TDRCMST.BV_TYP != TDCACE.PAGE_INF.I_BV_TYP) {
                    CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_BVTYP_ERR);
                }
            }
        }
    }
    public void T001_READ_TDTCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        TDTCMST_RD.where = "AC_NO = :TDRCMST.KEY.AC_NO";
        IBS.READ(SCCGWA, TDRCMST, this, TDTCMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_IS_NOT_TD_AC);
        }
    }
    public void B110_FUND_MSG() throws IOException,SQLException,Exception {
        if (TDCACE.PAGE_INF.A_ACO_AC.trim().length() == 0) {
            B200_MAIN_MSG();
            if (pgmRtn) return;
        }
        B210_SUB_ACO_MSG();
        if (pgmRtn) return;
    }
    public void B200_MAIN_MSG() throws IOException,SQLException,Exception {
        if (CICQACRI.O_DATA.O_ENTY_TYP != '2' 
            && CICQACRI.O_DATA.O_ENTY_TYP != '5' 
            && CICQACRI.O_DATA.O_ENTY_TYP != '6' 
            && CICQACRI.O_DATA.O_ENTY_TYP != '7') {
            CEP.TRC(SCCGWA, TDRCMST.KEY.AC_NO);
            CEP.TRC(SCCGWA, TDRCMST.STS);
            CEP.TRC(SCCGWA, TDRCMST.PROD_CD);
            CEP.TRC(SCCGWA, TDRCMST.STSW);
            CEP.TRC(SCCGWA, TDRCMST.CROS_DR_FLG);
            CEP.TRC(SCCGWA, TDRCMST.CROS_CR_FLG);
            CEP.TRC(SCCGWA, TDRCMST.DRAW_MTH);
            CEP.TRC(SCCGWA, TDRCMST.FRG_IND);
            TDCOACE.PAGE_INF.AC_NO = TDRCMST.KEY.AC_NO;
            TDCOACE.PAGE_INF.AC_STS = TDRCMST.STS;
            TDCOACE.PAGE_INF.MPROD_CD = TDRCMST.PROD_CD;
            TDCOACE.PAGE_INF.STSW = TDRCMST.STSW;
            TDCOACE.PAGE_INF.CROS_DR_FLG = TDRCMST.CROS_DR_FLG;
            TDCOACE.PAGE_INF.CROS_CR_FLG = TDRCMST.CROS_CR_FLG;
            TDCOACE.PAGE_INF.FRG_IND = TDRCMST.FRG_IND;
            TDCOACE.PAGE_INF.DRAW_MTH = TDRCMST.DRAW_MTH;
            TDCOACE.PAGE_INF.CI_NAME = CICQACRI.O_DATA.O_AC_CNM;
            TDCACE.PAGE_INF.AC_NO = TDRCMST.KEY.AC_NO;
            TDCACE.PAGE_INF.AC_STS = TDRCMST.STS;
            TDCACE.PAGE_INF.MPROD_CD = TDRCMST.PROD_CD;
            TDCACE.PAGE_INF.STSW = TDRCMST.STSW;
            TDCACE.PAGE_INF.CROS_DR_FLG = TDRCMST.CROS_DR_FLG;
            TDCACE.PAGE_INF.CROS_CR_FLG = TDRCMST.CROS_CR_FLG;
            TDCACE.PAGE_INF.FRG_IND = TDRCMST.FRG_IND;
            TDCACE.PAGE_INF.DRAW_MTH = TDRCMST.DRAW_MTH;
            TDCACE.PAGE_INF.CI_NAME = CICQACRI.O_DATA.O_AC_CNM;
        }
        if (TDRCMST.STSW == null) TDRCMST.STSW = "";
        JIBS_tmp_int = TDRCMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRCMST.STSW += " ";
        if (TDRCMST.STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
            && TDRCMST.OPT_DT < SCCGWA.COMM_AREA.AC_DATE) {
            if (TDCOACE.PAGE_INF.STSW == null) TDCOACE.PAGE_INF.STSW = "";
            JIBS_tmp_int = TDCOACE.PAGE_INF.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDCOACE.PAGE_INF.STSW += " ";
            TDCOACE.PAGE_INF.STSW = TDCOACE.PAGE_INF.STSW.substring(0, 4 - 1) + "0" + TDCOACE.PAGE_INF.STSW.substring(4 + 1 - 1);
            if (TDCACE.PAGE_INF.STSW == null) TDCACE.PAGE_INF.STSW = "";
            JIBS_tmp_int = TDCACE.PAGE_INF.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.PAGE_INF.STSW += " ";
            TDCACE.PAGE_INF.STSW = TDCACE.PAGE_INF.STSW.substring(0, 4 - 1) + "0" + TDCACE.PAGE_INF.STSW.substring(4 + 1 - 1);
        }
        CEP.TRC(SCCGWA, TDCACE.PAGE_INF.I_AC_SEQ);
        CEP.TRC(SCCGWA, TDCACE.PAGE_INF.I_BV_NO);
        CEP.TRC(SCCGWA, TDCACE.PAGE_INF.AC_NO);
        if (TDRCMST.PROD_CD.trim().length() > 0) {
            IBS.init(SCCGWA, BPCPQPRD);
            BPCPQPRD.PRDT_CODE = TDRCMST.PROD_CD;
            S000_CALL_BPZPQPRD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPQPRD.AC_TYPE);
            if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("043") 
                || BPCPQPRD.AC_TYPE.equalsIgnoreCase("044")) {
                IBS.init(SCCGWA, TDRSMST);
                TDRSMST.AC_NO = TDCACE.PAGE_INF.AC_NO;
                T000_READ_TDTSMSTMR();
                if (pgmRtn) return;
                if (WS_MR_FLAG == 'F') {
                    T000_GROUP_TDTSMST_2();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, WS_GROP_AMT);
                    CEP.TRC(SCCGWA, WS_GROP_INT);
                    TDCOACE.GROP_AMT = WS_GROP_AMT;
                    TDCOACE.GROP_INT = WS_GROP_INT;
                } else {
                    TDCOACE.GROP_AMT = 0;
                    TDCOACE.GROP_INT = 0;
                }
            }
        }
        if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("043") 
            || BPCPQPRD.AC_TYPE.equalsIgnoreCase("044") 
            || BPCPQPRD.AC_TYPE.equalsIgnoreCase("035") 
            || BPCPQPRD.AC_TYPE.equalsIgnoreCase("036")) {
            WS_DEFAC_FLG = '1';
        } else {
            WS_DEFAC_FLG = '0';
        }
        IBS.init(SCCGWA, CICQACAC);
        CEP.TRC(SCCGWA, TDCACE.PAGE_INF.I_AC_SEQ);
        if ((TDCACE.PAGE_INF.I_AC_SEQ != 0 
            && (TDRCMST.BV_TYP == '1' 
            || TDRCMST.BV_TYP == ' ')) 
            || ((TDCACE.PAGE_INF.I_AC_SEQ != 0 
            || TDCACE.PAGE_INF.I_BV_NO.trim().length() > 0) 
            && TDRCMST.BV_TYP == '0')) {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = TDCACE.PAGE_INF.AC_NO;
            CICQACAC.DATA.AGR_SEQ = TDCACE.PAGE_INF.I_AC_SEQ;
            CICQACAC.DATA.BV_NO = TDCACE.PAGE_INF.I_BV_NO;
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
        }
    }
    public void B210_SUB_ACO_MSG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.KEY.ACO_AC = "" + 0X00;
        JIBS_tmp_int = TDRSMST.KEY.ACO_AC.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) TDRSMST.KEY.ACO_AC = "0" + TDRSMST.KEY.ACO_AC;
        TDRSMST.AC_NO = TDRCMST.KEY.AC_NO;
        if (CICQACRI.O_DATA.O_ENTY_TYP == '2' 
            || CICQACRI.O_DATA.O_ENTY_TYP == '5' 
            || CICQACRI.O_DATA.O_ENTY_TYP == '6' 
            || CICQACRI.O_DATA.O_ENTY_TYP == '7') {
            TDRSMST.AC_NO = TDCACE.PAGE_INF.AC_NO;
        }
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO.trim().length() > 0 
            || TDCACE.PAGE_INF.A_ACO_AC.trim().length() > 0) {
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
            TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            if (TDCACE.PAGE_INF.A_ACO_AC.trim().length() > 0) {
                TDRSMST.KEY.ACO_AC = TDCACE.PAGE_INF.A_ACO_AC;
            }
            T000_READ_TDTSMST();
            if (pgmRtn) return;
            WS_NUM1 = 1;
            B000_SMST_OTHER();
            if (pgmRtn) return;
        } else {
            if (TDCACE.DATA[1-1].ACO_STS == ' ') {
                TDRSMST.ACO_STS = '0';
            } else {
                TDRSMST.ACO_STS = TDCACE.DATA[1-1].ACO_STS;
            }
            R000_TRANS_PAGE_ROW();
            if (pgmRtn) return;
            if (TDCACE.PAGE_INF.QCD.trim().length() > 0 
                && TDCACE.PAGE_INF.CCY_I.trim().length() > 0) {
                TDRSMST.PRDAC_CD = TDCACE.PAGE_INF.QCD;
                TDRSMST.CCY = TDCACE.PAGE_INF.CCY_I;
                T000_GROUP_TDTSMST_QCD_1();
                if (pgmRtn) return;
            }
            if (TDCACE.PAGE_INF.QCD.trim().length() > 0 
                && TDCACE.PAGE_INF.CCY_I.trim().length() == 0) {
                TDRSMST.PRDAC_CD = TDCACE.PAGE_INF.QCD;
                T000_GROUP_TDTSMST_QCD_2();
                if (pgmRtn) return;
            }
            if (TDCACE.PAGE_INF.QCD.trim().length() == 0 
                && TDCACE.PAGE_INF.CCY_I.trim().length() > 0) {
                TDRSMST.CCY = TDCACE.PAGE_INF.CCY_I;
                T000_GROUP_TDTSMST_CCY_3();
                if (pgmRtn) return;
            }
            if (TDCACE.PAGE_INF.QCD.trim().length() == 0 
                && TDCACE.PAGE_INF.CCY_I.trim().length() == 0) {
                T000_GROUP_TDTSMST();
                if (pgmRtn) return;
            }
            WS_L_CNT = WS_COUNT;
            CEP.TRC(SCCGWA, WS_L_CNT);
            R000_PAGE_COM();
            if (pgmRtn) return;
            if (TDCACE.PAGE_INF.QCD.trim().length() > 0 
                && TDCACE.PAGE_INF.CCY_I.trim().length() > 0) {
                IBS.init(SCCGWA, TDRSMST);
                TDRSMST.PRDAC_CD = TDCACE.PAGE_INF.QCD;
                TDRSMST.CCY = TDCACE.PAGE_INF.CCY_I;
                T000_STARTBR_TDTSMST_QCD_1();
                if (pgmRtn) return;
            }
            if (TDCACE.PAGE_INF.QCD.trim().length() > 0 
                && TDCACE.PAGE_INF.CCY_I.trim().length() == 0) {
                TDRSMST.PRDAC_CD = TDCACE.PAGE_INF.QCD;
                T000_STARTBR_TDTSMST_QCD_2();
                if (pgmRtn) return;
            }
            if (TDCACE.PAGE_INF.QCD.trim().length() == 0 
                && TDCACE.PAGE_INF.CCY_I.trim().length() > 0) {
                TDRSMST.CCY = TDCACE.PAGE_INF.CCY_I;
                T000_STARTBR_TDTSMST_CCY_3();
                if (pgmRtn) return;
            }
            if (TDCACE.PAGE_INF.QCD.trim().length() == 0 
                && TDCACE.PAGE_INF.CCY_I.trim().length() == 0) {
                T000_STARTBR_TDTSMST();
                if (pgmRtn) return;
            }
            T000_READNEXT_TDTSMST();
            if (pgmRtn) return;
            WS_TIME = 0;
            WS_NUM1 = 0;
            while (WS_SMST_END_FLG != 'N') {
                WS_TIME = WS_TIME + 1;
                if (WS_TIME > WS_STR_NUM 
                    && WS_TIME <= WS_END_NUM) {
                    WS_NUM1 += 1;
                    B000_SMST_OTHER();
                    if (pgmRtn) return;
                }
                T000_READNEXT_TDTSMST();
                if (pgmRtn) return;
            }
            T000_ENDBR_TDTSMST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_COUNT);
            CEP.TRC(SCCGWA, TDCACE.PAGE_INF.PAGE_NUM);
            if (TDCACE.PAGE_INF.PAGE_NUM != 0) {
                CEP.TRC(SCCGWA, "AAAA");
                WS_LEFT = WS_COUNT % WS_P_ROW;
                WS_TOT_PAGE = (int) ((WS_COUNT - WS_LEFT) / WS_P_ROW);
                if (WS_LEFT != 0) {
                    WS_TOT_PAGE += 1;
                }
                CEP.TRC(SCCGWA, "BBBBBBBBBB");
                if (TDCACE.PAGE_INF.PAGE_NUM >= WS_TOT_PAGE) {
                    TDCACE.PAGE_INF.MY_FLG = 'Y';
                } else {
                    TDCACE.PAGE_INF.MY_FLG = 'N';
                }
                CEP.TRC(SCCGWA, TDCACE.PAGE_INF.MY_FLG);
            }
            if (WS_L_CNT == 0) {
                TDCOACE.TOTAL_NUM = 0;
                TDCOACE.TOTAL_PAGE = 0;
                TDCOACE.CURR_PAGE = 0;
                TDCOACE.LAST_PAGE = 'Y';
                TDCOACE.PAGE_ROW = 0;
            }
            CEP.TRC(SCCGWA, TDCOACE.TOTAL_PAGE);
            CEP.TRC(SCCGWA, TDCOACE.TOTAL_NUM);
            CEP.TRC(SCCGWA, TDCOACE.PAGE_ROW);
            CEP.TRC(SCCGWA, TDCOACE.CURR_PAGE);
            CEP.TRC(SCCGWA, TDCOACE.LAST_PAGE);
        }
    }
    public void B120_OUT_MSG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT1;
        SCCFMT.DATA_PTR = TDCOACE;
        SCCFMT.DATA_LEN = 9409;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B000_SMST_OTHER() throws IOException,SQLException,Exception {
        if (TDRSMST.KEY.ACO_AC.trim().length() > 0) {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'A';
            CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
            CICQACAC.DATA.ACAC_NO = TDRSMST.KEY.ACO_AC;
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_ENTY_TYP);
        CEP.TRC(SCCGWA, TDRSMST.BV_TYP);
        CEP.TRC(SCCGWA, TDRCMST.BV_TYP);
        if (TDRSMST.BV_TYP != '4' 
            && TDRCMST.BV_TYP != '8' 
            && TDRSMST.BV_TYP != '8') {
            if ((TDRSMST.BV_TYP != ' ' 
                && TDRCMST.BV_TYP == '0') 
                || (TDRSMST.BV_TYP == '7' 
                && TDRCMST.BV_TYP == '7')) {
                IBS.init(SCCGWA, TDRBVT);
                TDRBVT.KEY.AC_NO = TDRSMST.KEY.ACO_AC;
            }
            CEP.TRC(SCCGWA, "LUO2");
            CEP.TRC(SCCGWA, TDRBVT.KEY.AC_NO);
            CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
            CEP.TRC(SCCGWA, TDRSMST.BV_TYP);
            CEP.TRC(SCCGWA, TDRCMST.BV_TYP);
            if (((TDRSMST.BV_TYP != ' ' 
                && TDRSMST.BV_TYP != '0') 
                && TDRCMST.BV_TYP == '0') 
                || (TDRCMST.BV_TYP != '0' 
                && TDRCMST.BV_TYP != ' ')) {
                CEP.TRC(SCCGWA, TDRSMST.PRDAC_CD);
                if (!TDRSMST.PRDAC_CD.equalsIgnoreCase("035") 
                    && !TDRSMST.PRDAC_CD.equalsIgnoreCase("036")) {
                    R100_READ_TDTBVT();
                    if (pgmRtn) return;
                    if (TDCACE.PAGE_INF.I_BV_NO.trim().length() > 0 
                        && !TDCACE.PAGE_INF.I_BV_NO.equalsIgnoreCase(TDRBVT.BV_NO)) {
                        CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_BV_NO_NOT_MATCH);
                    }
                }
            }
            CEP.TRC(SCCGWA, TDCACE.PAGE_INF.I_BV_NO);
            if (!TDRBVT.KEY.AC_NO.equalsIgnoreCase(TDRSMST.KEY.ACO_AC)) {
                TDCACE.PAGE_INF.I_BV_NO = TDRBVT.BV_NO;
            }
        }
        CEP.TRC(SCCGWA, TDCACE.PAGE_INF.I_BV_NO);
        if (TDRSMST.OPEN_DR_AC.trim().length() > 0) {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.FUNC = 'A';
            CICQACRI.DATA.AGR_NO = TDRSMST.OPEN_DR_AC;
            S110_CALL_CIZQACRI();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, TDROTHE);
        TDROTHE.KEY.ACTI_NO = TDRSMST.ACTI_NO;
        T110_READ_TDTOTHE();
        if (pgmRtn) return;
        if (TDROTHE.RUL_CD.trim().length() > 0) {
            TDCOACE.DATA[WS_NUM1-1].INT_RUL_CD = TDROTHE.RUL_CD;
        }
        IBS.init(SCCGWA, TDRAINT);
        TDRAINT.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(36 - 1, 36 + 1 - 1).equalsIgnoreCase("1") 
            || TDRSMST.STSW.substring(37 - 1, 37 + 1 - 1).equalsIgnoreCase("1") 
            || TDRSMST.STSW.substring(38 - 1, 38 + 1 - 1).equalsIgnoreCase("1")) {
            T000_READ_TDTAINT();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, TDRINST);
        TDRINST.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
        T120_READ_TDTINST();
        if (pgmRtn) return;
        IBS.init(SCCGWA, TDRCDI);
        TDRCDI.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
        T130_READ_TDTCDI();
        if (pgmRtn) return;
        IBS.init(SCCGWA, TDRIREV);
        TDRIREV.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
        if (TDRSMST.EXP_DATE != 0) {
            TDRIREV.KEY.STR_DATE = TDRSMST.EXP_DATE;
            TDRIREV.KEY.STR_DATE = TDRIREV.KEY.STR_DATE - 1;
        } else {
            TDRIREV.KEY.STR_DATE = TDRSMST.VAL_DATE;
        }
        CEP.TRC(SCCGWA, TDRIREV.KEY.STR_DATE);
        if (TDRSMST.VAL_DATE > SCCGWA.COMM_AREA.AC_DATE) {
            TDRIREV.KEY.STR_DATE = TDRSMST.VAL_DATE;
        }
        T000_READ_TDTIREV_TZZ();
        if (pgmRtn) return;
        R000_MOVE_OUT_ORTHER();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_NUM1);
        if (WS_NUM1 != 0) {
            if (TDRSMST.SER_TIME == 0) {
                if (SCCGWA.COMM_AREA.AC_DATE != TDRSMST.EXP_DATE) {
                    TDCOACE.DATA[WS_NUM1-1].PREV_FLG = '1';
                } else {
                    TDCOACE.DATA[WS_NUM1-1].PREV_FLG = '0';
                }
            } else {
                TDCOACE.DATA[WS_NUM1-1].PREV_FLG = '0';
            }
            CEP.TRC(SCCGWA, "RENSHENGQI IS");
            R000_MOVE_TDTSMST();
            if (pgmRtn) return;
        }
    }
    public void R000_GET_GROPINFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        CEP.TRC(SCCGWA, TDRSMST.PROD_CD);
        CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
        BPCPQPRD.PRDT_CODE = TDRSMST.PROD_CD;
        CEP.TRC(SCCGWA, BPCPQPRD);
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
        IBS.init(SCCGWA, TDCQPMP);
        TDCQPMP.FUNC = 'I';
        TDCQPMP.PROD_CD = BPCPQPRD.PARM_CODE;
        TDCQPMP.DAT_PTR = TDCPROD;
        S000_CALL_TDZQPMP();
        if (pgmRtn) return;
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
            CEP.TRC(SCCGWA, TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].MIN_CCYC);
            if (TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].MIN_CCYC.equalsIgnoreCase(TDRSMST.CCY)) {
                WS_DOCU_NO = TDCPRDP.OTH_PRM.CCY_INF[WS_CNT-1].DOCU_NO;
                WS_CCY_FOUND = 'Y';
            }
        }
        CEP.TRC(SCCGWA, WS_DOCU_NO);
        IBS.init(SCCGWA, TDRDOCU);
        WS_I = 0;
        TDRDOCU.KEY.DOCU_NO = WS_DOCU_NO;
        T000_STARTBR_TDTDOCU();
        if (pgmRtn) return;
        T000_READNEXT_TDTDOCU();
        if (pgmRtn) return;
        while (WS_DOCU_FLG != 'N') {
            WS_I += 1;
            WS_DOCU_NO = TDRDOCU.KEY.DOCU_NO;
            CEP.TRC(SCCGWA, TDRDOCU.KEY.DOCU_TERM);
            WS_TERM_KD = TDRDOCU.KEY.DOCU_TERM;
            IBS.CPY2CLS(SCCGWA, WS_TERM_KD, REDEFINES40);
            IBS.init(SCCGWA, SCCCLDT);
            CEP.TRC(SCCGWA, WS_TERM_KD);
            CEP.TRC(SCCGWA, TDRSMST.VAL_DATE);
            SCCCLDT.DATE1 = TDRSMST.VAL_DATE;
            if (REDEFINES40.WS_TERMKD_TYP == 'D') {
                SCCCLDT.DAYS = REDEFINES40.WS_TERMKD_CNT;
            } else {
                if (REDEFINES40.WS_TERMKD_TYP == 'M') {
                    SCCCLDT.MTHS = REDEFINES40.WS_TERMKD_CNT;
                } else {
                    SCCCLDT.MTHS = (short) (REDEFINES40.WS_TERMKD_CNT * 12);
                }
            }
            CEP.TRC(SCCGWA, SCCCLDT.DAYS);
            CEP.TRC(SCCGWA, SCCCLDT.MTHS);
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            WS_DOCU_DATE[WS_I-1].WS_KD_DT = SCCCLDT.DATE2;
            WS_DOCU_DATE[WS_I-1].WS_KDTERM = TDRDOCU.KEY.DOCU_TERM;
            CEP.TRC(SCCGWA, WS_DOCU_DATE[WS_I-1].WS_KD_DT);
            T000_READNEXT_TDTDOCU();
            if (pgmRtn) return;
        }
        T000_ENDBR_TDTDOCU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "READ DOCU END");
        CEP.TRC(SCCGWA, WS_T);
        CEP.TRC(SCCGWA, WS_TT);
        for (WS_T = 1; WS_T <= 30 
            && WS_DOCU_DATE[WS_T-1].WS_KD_DT != 0; WS_T += 1) {
            CEP.TRC(SCCGWA, WS_T);
            CEP.TRC(SCCGWA, WS_DOCU_DATE[WS_T-1].WS_KD_DT);
            if (SCCGWA.COMM_AREA.AC_DATE >= WS_DOCU_DATE[WS_T-1].WS_KD_DT) {
                TDCOACE.DATA[WS_NUM1-1].INT_TERM = WS_DOCU_DATE[WS_T-1].WS_KDTERM;
                if (WS_T == 1) {
                    TDCOACE.DATA[WS_NUM1-1].NEXT_TERM = WS_DOCU_DATE[1-1].WS_KDTERM;
                    TDCOACE.DATA[WS_NUM1-1].LVL_DAY = 0;
                } else {
                    TDCOACE.DATA[WS_NUM1-1].NEXT_TERM = WS_DOCU_DATE[WS_T - 1-1].WS_KDTERM;
                    CEP.TRC(SCCGWA, TDCOACE.DATA[WS_NUM1-1].NEXT_TERM);
                    IBS.init(SCCGWA, SCCCLDT);
                    CEP.TRC(SCCGWA, WS_DOCU_DATE[WS_T-1].WS_KD_DT);
                    SCCCLDT.DATE1 = SCCGWA.COMM_AREA.AC_DATE;
                    SCCCLDT.DATE2 = WS_DOCU_DATE[WS_T - 1-1].WS_KD_DT;
                    S000_CALL_SCSSCLDT();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, SCCCLDT.DAYS);
                    TDCOACE.DATA[WS_NUM1-1].LVL_DAY = (short) SCCCLDT.DAYS;
                }
                WS_T = 31;
            }
        }
        CEP.TRC(SCCGWA, WS_T);
        CEP.TRC(SCCGWA, "AAA");
        CEP.TRC(SCCGWA, TDCOACE.DATA[WS_NUM1-1].INT_TERM);
        if (TDCOACE.DATA[WS_NUM1-1].INT_TERM.trim().length() == 0) {
            TDCOACE.DATA[WS_NUM1-1].INT_TERM = "S000";
            TDCOACE.DATA[WS_NUM1-1].NEXT_TERM = WS_DOCU_DATE[WS_T - 1-1].WS_KDTERM;
            IBS.init(SCCGWA, SCCCLDT);
            CEP.TRC(SCCGWA, TDCOACE.DATA[WS_NUM1-1].NEXT_TERM);
            SCCCLDT.DATE1 = SCCGWA.COMM_AREA.AC_DATE;
            SCCCLDT.DATE2 = WS_DOCU_DATE[WS_T - 1-1].WS_KD_DT;
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCCCLDT.DAYS);
            TDCOACE.DATA[WS_NUM1-1].LVL_DAY = (short) SCCCLDT.DAYS;
        }
        CEP.TRC(SCCGWA, WS_T);
        CEP.TRC(SCCGWA, "BBB");
        CEP.TRC(SCCGWA, TDRSMST.LBAL_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, TDRSMST.BAL);
        CEP.TRC(SCCGWA, TDRSMST.LBAL);
        CEP.TRC(SCCGWA, TDRSMST.VAL_DATE);
        if (TDRSMST.LBAL_DATE == TDRSMST.VAL_DATE) {
            if (TDRSMST.LBAL_DATE == SCCGWA.COMM_AREA.AC_DATE) {
                TDCOACE.DATA[WS_NUM1-1].TX_AMT = TDRSMST.BAL;
                WS_FSE_FLG = 'Y';
            }
        } else {
            TDCOACE.DATA[WS_NUM1-1].TX_AMT = TDRSMST.BAL - TDRSMST.LBAL;
        }
        if (TDRSMST.OPEN_DATE == SCCGWA.COMM_AREA.AC_DATE 
            && TDRSMST.BAL == 0) {
            TDCOACE.DATA[WS_NUM1-1].TX_AMT = 0;
        }
        if ((TDRSMST.LBAL_DATE != SCCGWA.COMM_AREA.AC_DATE 
            || TDRSMST.BAL == TDRSMST.LBAL) 
            && WS_FSE_FLG != 'Y') {
            TDCOACE.DATA[WS_NUM1-1].TX_AMT = 0;
        }
        CEP.TRC(SCCGWA, TDCOACE.DATA[WS_NUM1-1].TX_AMT);
        CEP.TRC(SCCGWA, TDCOACE.DATA[WS_NUM1-1].INT_TERM);
        CEP.TRC(SCCGWA, TDCOACE.DATA[WS_NUM1-1].NEXT_TERM);
        CEP.TRC(SCCGWA, TDCOACE.DATA[WS_NUM1-1].LVL_DAY);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_TDZPROD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-GET-PROD", TDCPIOD);
    }
    public void S110_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        if (CICQACRI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void T000_READ_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, TDCACE.PAGE_INF.PAGE_NUM);
            CEP.TRC(SCCGWA, TDCACE.PAGE_INF.PAGE_ROW);
        } else {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
        }
    }
    public void T000_READ_TDTSMST_QCD() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.where = "PRDAC_CD = :TDRSMST.PRDAC_CD";
        IBS.READ(SCCGWA, TDRSMST, this, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, TDCACE.PAGE_INF.PAGE_NUM);
            CEP.TRC(SCCGWA, TDCACE.PAGE_INF.PAGE_ROW);
        } else {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
        }
    }
    public void T130_READ_TDTCDI() throws IOException,SQLException,Exception {
        TDTCDI_RD = new DBParm();
        TDTCDI_RD.TableName = "TDTCDI";
        IBS.READ(SCCGWA, TDRCDI, TDTCDI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, TDCACE.PAGE_INF.PAGE_NUM);
            CEP.TRC(SCCGWA, TDCACE.PAGE_INF.PAGE_ROW);
        } else {
        }
    }
    public void T000_GROUP_TDTSMST_QCD_1() throws IOException,SQLException,Exception {
        if (TDRSMST.ACO_STS == 'A') {
            TDTSMST_RD = new DBParm();
            TDTSMST_RD.TableName = "TDTSMST";
            TDTSMST_RD.set = "WS-COUNT=COUNT(*)";
            TDTSMST_RD.where = "ACO_AC >= :TDRSMST.KEY.ACO_AC "
                + "AND AC_NO = :TDRSMST.AC_NO "
                + "AND SUBSTR ( STSW , 52 , 1 ) < > '1' "
                + "AND PRDAC_CD = :TDRSMST.PRDAC_CD "
                + "AND CCY = :TDRSMST.CCY";
            IBS.GROUP(SCCGWA, TDRSMST, this, TDTSMST_RD);
        } else {
            TDTSMST_RD = new DBParm();
            TDTSMST_RD.TableName = "TDTSMST";
            TDTSMST_RD.set = "WS-COUNT=COUNT(*)";
            TDTSMST_RD.where = "ACO_AC >= :TDRSMST.KEY.ACO_AC "
                + "AND AC_NO = :TDRSMST.AC_NO "
                + "AND SUBSTR ( STSW , 52 , 1 ) < > '1' "
                + "AND PRDAC_CD = :TDRSMST.PRDAC_CD "
                + "AND CCY = :TDRSMST.CCY "
                + "AND ACO_STS = :TDRSMST.ACO_STS";
            IBS.GROUP(SCCGWA, TDRSMST, this, TDTSMST_RD);
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, TDCACE.PAGE_INF.PAGE_NUM);
            CEP.TRC(SCCGWA, TDCACE.PAGE_INF.PAGE_ROW);
        } else {
        }
    }
    public void T000_GROUP_TDTSMST_QCD_2() throws IOException,SQLException,Exception {
        if (TDRSMST.ACO_STS == 'A') {
            TDTSMST_RD = new DBParm();
            TDTSMST_RD.TableName = "TDTSMST";
            TDTSMST_RD.set = "WS-COUNT=COUNT(*)";
            TDTSMST_RD.where = "ACO_AC >= :TDRSMST.KEY.ACO_AC "
                + "AND AC_NO = :TDRSMST.AC_NO "
                + "AND SUBSTR ( STSW , 52 , 1 ) < > '1' "
                + "AND PRDAC_CD = :TDRSMST.PRDAC_CD";
            IBS.GROUP(SCCGWA, TDRSMST, this, TDTSMST_RD);
        } else {
            TDTSMST_RD = new DBParm();
            TDTSMST_RD.TableName = "TDTSMST";
            TDTSMST_RD.set = "WS-COUNT=COUNT(*)";
            TDTSMST_RD.where = "ACO_AC >= :TDRSMST.KEY.ACO_AC "
                + "AND AC_NO = :TDRSMST.AC_NO "
                + "AND SUBSTR ( STSW , 52 , 1 ) < > '1' "
                + "AND PRDAC_CD = :TDRSMST.PRDAC_CD "
                + "AND ACO_STS = :TDRSMST.ACO_STS";
            IBS.GROUP(SCCGWA, TDRSMST, this, TDTSMST_RD);
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, TDCACE.PAGE_INF.PAGE_NUM);
            CEP.TRC(SCCGWA, TDCACE.PAGE_INF.PAGE_ROW);
        } else {
        }
    }
    public void T000_GROUP_TDTSMST_CCY_3() throws IOException,SQLException,Exception {
        if (TDRSMST.ACO_STS == 'A') {
            TDTSMST_RD = new DBParm();
            TDTSMST_RD.TableName = "TDTSMST";
            TDTSMST_RD.set = "WS-COUNT=COUNT(*)";
            TDTSMST_RD.where = "ACO_AC >= :TDRSMST.KEY.ACO_AC "
                + "AND AC_NO = :TDRSMST.AC_NO "
                + "AND SUBSTR ( STSW , 52 , 1 ) < > '1' "
                + "AND CCY = :TDRSMST.CCY";
            IBS.GROUP(SCCGWA, TDRSMST, this, TDTSMST_RD);
        } else {
            TDTSMST_RD = new DBParm();
            TDTSMST_RD.TableName = "TDTSMST";
            TDTSMST_RD.set = "WS-COUNT=COUNT(*)";
            TDTSMST_RD.where = "ACO_AC >= :TDRSMST.KEY.ACO_AC "
                + "AND AC_NO = :TDRSMST.AC_NO "
                + "AND SUBSTR ( STSW , 52 , 1 ) < > '1' "
                + "AND CCY = :TDRSMST.CCY "
                + "AND ACO_STS = :TDRSMST.ACO_STS";
            IBS.GROUP(SCCGWA, TDRSMST, this, TDTSMST_RD);
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, TDCACE.PAGE_INF.PAGE_NUM);
            CEP.TRC(SCCGWA, TDCACE.PAGE_INF.PAGE_ROW);
        } else {
        }
    }
    public void T000_GROUP_TDTSMST() throws IOException,SQLException,Exception {
        if (TDRSMST.ACO_STS == 'A') {
            TDTSMST_RD = new DBParm();
            TDTSMST_RD.TableName = "TDTSMST";
            TDTSMST_RD.set = "WS-COUNT=COUNT(*)";
            TDTSMST_RD.where = "ACO_AC >= :TDRSMST.KEY.ACO_AC "
                + "AND AC_NO = :TDRSMST.AC_NO "
                + "AND SUBSTR ( STSW , 52 , 1 ) < > '1'";
            IBS.GROUP(SCCGWA, TDRSMST, this, TDTSMST_RD);
        } else {
            TDTSMST_RD = new DBParm();
            TDTSMST_RD.TableName = "TDTSMST";
            TDTSMST_RD.set = "WS-COUNT=COUNT(*)";
            TDTSMST_RD.where = "ACO_AC >= :TDRSMST.KEY.ACO_AC "
                + "AND AC_NO = :TDRSMST.AC_NO "
                + "AND SUBSTR ( STSW , 52 , 1 ) < > '1' "
                + "AND ACO_STS = :TDRSMST.ACO_STS";
            IBS.GROUP(SCCGWA, TDRSMST, this, TDTSMST_RD);
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, TDCACE.PAGE_INF.PAGE_NUM);
            CEP.TRC(SCCGWA, TDCACE.PAGE_INF.PAGE_ROW);
        } else {
        }
    }
    public void T000_STARTBR_TDTSMST_QCD_1() throws IOException,SQLException,Exception {
        if (TDRSMST.ACO_STS == 'A') {
            TDTSMST_BR.rp = new DBParm();
            TDTSMST_BR.rp.TableName = "TDTSMST";
            TDTSMST_BR.rp.where = "ACO_AC >= :TDRSMST.KEY.ACO_AC "
                + "AND AC_NO = :TDRSMST.AC_NO "
                + "AND SUBSTR ( STSW , 52 , 1 ) < > '1' "
                + "AND CCY = :TDRSMST.CCY "
                + "AND PRDAC_CD = :TDRSMST.PRDAC_CD";
            TDTSMST_BR.rp.order = "VAL_DATE DESC";
            IBS.STARTBR(SCCGWA, TDRSMST, this, TDTSMST_BR);
        } else {
            TDTSMST_BR.rp = new DBParm();
            TDTSMST_BR.rp.TableName = "TDTSMST";
            TDTSMST_BR.rp.where = "ACO_AC >= :TDRSMST.KEY.ACO_AC "
                + "AND AC_NO = :TDRSMST.AC_NO "
                + "AND SUBSTR ( STSW , 52 , 1 ) < > '1' "
                + "AND ACO_STS = :TDRSMST.ACO_STS "
                + "AND CCY = :TDRSMST.CCY "
                + "AND PRDAC_CD = :TDRSMST.PRDAC_CD";
            TDTSMST_BR.rp.order = "VAL_DATE DESC";
            IBS.STARTBR(SCCGWA, TDRSMST, this, TDTSMST_BR);
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, TDCACE.PAGE_INF.PAGE_NUM);
            CEP.TRC(SCCGWA, TDCACE.PAGE_INF.PAGE_ROW);
        } else {
        }
    }
    public void T000_STARTBR_TDTSMST_QCD_2() throws IOException,SQLException,Exception {
        if (TDRSMST.ACO_STS == 'A') {
            TDTSMST_BR.rp = new DBParm();
            TDTSMST_BR.rp.TableName = "TDTSMST";
            TDTSMST_BR.rp.where = "ACO_AC >= :TDRSMST.KEY.ACO_AC "
                + "AND AC_NO = :TDRSMST.AC_NO "
                + "AND SUBSTR ( STSW , 52 , 1 ) < > '1' "
                + "AND PRDAC_CD = :TDRSMST.PRDAC_CD";
            TDTSMST_BR.rp.order = "VAL_DATE DESC";
            IBS.STARTBR(SCCGWA, TDRSMST, this, TDTSMST_BR);
        } else {
            TDTSMST_BR.rp = new DBParm();
            TDTSMST_BR.rp.TableName = "TDTSMST";
            TDTSMST_BR.rp.where = "ACO_AC >= :TDRSMST.KEY.ACO_AC "
                + "AND AC_NO = :TDRSMST.AC_NO "
                + "AND SUBSTR ( STSW , 52 , 1 ) < > '1' "
                + "AND ACO_STS = :TDRSMST.ACO_STS "
                + "AND PRDAC_CD = :TDRSMST.PRDAC_CD";
            TDTSMST_BR.rp.order = "VAL_DATE DESC";
            IBS.STARTBR(SCCGWA, TDRSMST, this, TDTSMST_BR);
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, TDCACE.PAGE_INF.PAGE_NUM);
            CEP.TRC(SCCGWA, TDCACE.PAGE_INF.PAGE_ROW);
        } else {
        }
    }
    public void T000_STARTBR_TDTSMST_CCY_3() throws IOException,SQLException,Exception {
        if (TDRSMST.ACO_STS == 'A') {
            TDTSMST_BR.rp = new DBParm();
            TDTSMST_BR.rp.TableName = "TDTSMST";
            TDTSMST_BR.rp.where = "ACO_AC >= :TDRSMST.KEY.ACO_AC "
                + "AND AC_NO = :TDRSMST.AC_NO "
                + "AND SUBSTR ( STSW , 52 , 1 ) < > '1' "
                + "AND CCY = :TDRSMST.CCY";
            TDTSMST_BR.rp.order = "VAL_DATE DESC";
            IBS.STARTBR(SCCGWA, TDRSMST, this, TDTSMST_BR);
        } else {
            TDTSMST_BR.rp = new DBParm();
            TDTSMST_BR.rp.TableName = "TDTSMST";
            TDTSMST_BR.rp.where = "ACO_AC >= :TDRSMST.KEY.ACO_AC "
                + "AND AC_NO = :TDRSMST.AC_NO "
                + "AND SUBSTR ( STSW , 52 , 1 ) < > '1' "
                + "AND ACO_STS = :TDRSMST.ACO_STS "
                + "AND CCY = :TDRSMST.CCY";
            TDTSMST_BR.rp.order = "VAL_DATE DESC";
            IBS.STARTBR(SCCGWA, TDRSMST, this, TDTSMST_BR);
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, TDCACE.PAGE_INF.PAGE_NUM);
            CEP.TRC(SCCGWA, TDCACE.PAGE_INF.PAGE_ROW);
        } else {
        }
    }
    public void T000_STARTBR_TDTSMST() throws IOException,SQLException,Exception {
        if (TDRSMST.ACO_STS == 'A') {
            TDTSMST_BR.rp = new DBParm();
            TDTSMST_BR.rp.TableName = "TDTSMST";
            TDTSMST_BR.rp.where = "ACO_AC >= :TDRSMST.KEY.ACO_AC "
                + "AND AC_NO = :TDRSMST.AC_NO "
                + "AND SUBSTR ( STSW , 52 , 1 ) < > '1'";
            TDTSMST_BR.rp.order = "VAL_DATE DESC";
            IBS.STARTBR(SCCGWA, TDRSMST, this, TDTSMST_BR);
        } else {
            TDTSMST_BR.rp = new DBParm();
            TDTSMST_BR.rp.TableName = "TDTSMST";
            TDTSMST_BR.rp.where = "ACO_AC >= :TDRSMST.KEY.ACO_AC "
                + "AND AC_NO = :TDRSMST.AC_NO "
                + "AND SUBSTR ( STSW , 52 , 1 ) < > '1' "
                + "AND ACO_STS = :TDRSMST.ACO_STS";
            TDTSMST_BR.rp.order = "VAL_DATE DESC";
            IBS.STARTBR(SCCGWA, TDRSMST, this, TDTSMST_BR);
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, TDCACE.PAGE_INF.PAGE_NUM);
            CEP.TRC(SCCGWA, TDCACE.PAGE_INF.PAGE_ROW);
        } else {
        }
    }
    public void T000_READNEXT_TDTSMST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRSMST, this, TDTSMST_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, TDCACE.PAGE_INF.PAGE_NUM);
            CEP.TRC(SCCGWA, TDCACE.PAGE_INF.PAGE_ROW);
        } else {
            WS_SMST_END_FLG = 'N';
        }
    }
    public void R100_READ_TDTBVT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRBVT.KEY.AC_NO);
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        IBS.READ(SCCGWA, TDRBVT, TDTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, TDRBVT.KEY.AC_NO);
        } else {
            R100_READ_TDTBVT_AC_NO();
            if (pgmRtn) return;
        }
    }
    public void R100_READ_TDTBVT_AC_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRBVT);
        TDRBVT.KEY.AC_NO = TDRSMST.AC_NO;
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        IBS.READ(SCCGWA, TDRBVT, TDTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, TDRBVT.KEY.AC_NO);
        } else {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
        }
    }
    public void T000_READ_TDTAINT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRAINT.KEY.ACO_AC);
        TDTAINT_RD = new DBParm();
        TDTAINT_RD.TableName = "TDTAINT";
        IBS.READ(SCCGWA, TDRAINT, TDTAINT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, TDRAINT.KEY.ACO_AC);
        } else {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
        }
    }
    public void T110_READ_TDTOTHE() throws IOException,SQLException,Exception {
        TDTOTHE_RD = new DBParm();
        TDTOTHE_RD.TableName = "TDTOTHE";
        IBS.READ(SCCGWA, TDROTHE, TDTOTHE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, TDRBVT.KEY.AC_NO);
        } else {
        }
    }
    public void T120_READ_TDTINST() throws IOException,SQLException,Exception {
        TDTINST_RD = new DBParm();
        TDTINST_RD.TableName = "TDTINST";
        IBS.READ(SCCGWA, TDRINST, TDTINST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, TDRBVT.KEY.AC_NO);
        } else {
        }
    }
    public void R000_MOVE_TDTSMST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ);
        CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
        CEP.TRC(SCCGWA, TDRSMST.PROD_CD);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_ENTY_TYP);
        CEP.TRC(SCCGWA, TDRSMST.ACO_STS);
        CEP.TRC(SCCGWA, TDRSMST.CCY);
        CEP.TRC(SCCGWA, TDRSMST.CCY_TYP);
        CEP.TRC(SCCGWA, TDRSMST.BAL);
        CEP.TRC(SCCGWA, TDRSMST.OPEN_DATE);
        CEP.TRC(SCCGWA, TDRSMST.VAL_DATE);
        CEP.TRC(SCCGWA, TDRSMST.EXP_DATE);
        CEP.TRC(SCCGWA, TDRSMST.DRW_INT);
        CEP.TRC(SCCGWA, TDRSMST.PART_DATE);
        CEP.TRC(SCCGWA, TDRSMST.ACTI_NO);
        CEP.TRC(SCCGWA, TDRSMST.TERM);
        CEP.TRC(SCCGWA, TDRSMST.EXP_INT);
        CEP.TRC(SCCGWA, TDRSMST.HBAL);
        CEP.TRC(SCCGWA, TDRSMST.FBAL);
        CEP.TRC(SCCGWA, TDRSMST.INSTR_MTH);
        CEP.TRC(SCCGWA, TDRSMST.BV_TYP);
        CEP.TRC(SCCGWA, TDRSMST.PART_NUM);
        CEP.TRC(SCCGWA, TDRSMST.LBAL);
        CEP.TRC(SCCGWA, TDRSMST.FC_CD);
        CEP.TRC(SCCGWA, TDRSMST.FC_NO);
        TDCOACE.DATA[WS_NUM1-1].KY_BAL = TDRSMST.BAL - TDRSMST.HBAL;
        if (TDRSMST.ACO_STS == '1' 
            || TDRSMST.ACO_STS == '3' 
            || TDRSMST.VAL_DATE > SCCGWA.COMM_AREA.AC_DATE) {
            TDCOACE.DATA[WS_NUM1-1].KY_BAL = 0;
        } else {
        }
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (!TDRSMST.STSW.substring(2 - 1, 2 + 2 - 1).equalsIgnoreCase("00")) {
            TDCOACE.DATA[WS_NUM1-1].KY_BAL = 0;
        }
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (!TDRSMST.STSW.substring(7 - 1, 7 + 2 - 1).equalsIgnoreCase("00")) {
            TDCOACE.DATA[WS_NUM1-1].KY_BAL = 0;
        }
        CEP.TRC(SCCGWA, TDCOACE.DATA[WS_NUM1-1].KY_BAL);
        CEP.TRC(SCCGWA, TDRSMST.STSW);
        CEP.TRC(SCCGWA, TDRSMST.OPEN_DR_AC);
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_AC_CNM);
        CEP.TRC(SCCGWA, TDRSMST.CHNL_NO);
        CEP.TRC(SCCGWA, TDRSMST.OIC_NO);
        CEP.TRC(SCCGWA, TDRSMST.RES_CD);
        CEP.TRC(SCCGWA, TDRSMST.SUB_DP);
        CEP.TRC(SCCGWA, TDRSMST.BUD_INT);
        CEP.TRC(SCCGWA, TDRSMST.OWNER_BRDP);
        CEP.TRC(SCCGWA, TDRSMST.CHE_BR);
        CEP.TRC(SCCGWA, TDRSMST.OWNER_BR);
        CEP.TRC(SCCGWA, TDRSMST.CRT_TLR);
        CEP.TRC(SCCGWA, TDRSMST.UPD_DATE);
        CEP.TRC(SCCGWA, TDRSMST.UPD_BR);
        CEP.TRC(SCCGWA, TDRSMST.UPD_TLT);
        CEP.TRC(SCCGWA, TDCOACE.DATA[WS_NUM1-1].PREV_FLG);
        CEP.TRC(SCCGWA, TDRBVT.BV_NO);
        CEP.TRC(SCCGWA, TDRBVT.BV_CD);
        CEP.TRC(SCCGWA, TDRSMST.MON_TYP);
        CEP.TRC(SCCGWA, TDRSMST.AC_TYP);
        TDCOACE.DATA[WS_NUM1-1].AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
        TDCOACE.DATA[WS_NUM1-1].MON_TYP = TDRSMST.MON_TYP;
        TDCOACE.DATA[WS_NUM1-1].ACO_TYP = TDRSMST.AC_TYP;
        TDCOACE.DATA[WS_NUM1-1].ACO_AC = TDRSMST.KEY.ACO_AC;
        TDCOACE.DATA[WS_NUM1-1].PROD_CD = TDRSMST.PROD_CD;
        TDCOACE.DATA[WS_NUM1-1].AC_TYP = TDRSMST.PRDAC_CD;
        TDCOACE.DATA[WS_NUM1-1].ACO_STS = TDRSMST.ACO_STS;
        TDCOACE.DATA[WS_NUM1-1].HOLD_NUM = TDRSMST.HOLD_NUM;
        TDCOACE.DATA[WS_NUM1-1].CCY = TDRSMST.CCY;
        TDCOACE.DATA[WS_NUM1-1].CCY_TYP = TDRSMST.CCY_TYP;
        TDCOACE.DATA[WS_NUM1-1].BAL = TDRSMST.BAL;
        if (TDRSMST.ACO_STS == '1' 
            || TDRSMST.ACO_STS == '2' 
            || (TDRSMST.ACO_STS == '3' 
            && TDRSMST.VAL_DATE > SCCGWA.COMM_AREA.AC_DATE)) {
            TDCOACE.DATA[WS_NUM1-1].BAL = 0;
        } else {
        }
        TDCOACE.DATA[WS_NUM1-1].OPEN_DATE = TDRSMST.OPEN_DATE;
        TDCOACE.DATA[WS_NUM1-1].SDT = TDRSMST.VAL_DATE;
        TDCOACE.DATA[WS_NUM1-1].DDT = TDRSMST.EXP_DATE;
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (!TDRSMST.STSW.substring(22 - 1, 22 + 1 - 1).equalsIgnoreCase("1")) {
            TDCOACE.DATA[WS_NUM1-1].DRW_INT = TDRSMST.DRW_INT;
        } else {
            TDCOACE.DATA[WS_NUM1-1].DRW_INT = TDRSMST.BAL_INT;
        }
        TDCOACE.DATA[WS_NUM1-1].PART_DATE = TDRSMST.PART_DATE;
        TDCOACE.DATA[WS_NUM1-1].ACTI_NO = TDRSMST.ACTI_NO;
        TDCOACE.DATA[WS_NUM1-1].TERM = TDRSMST.TERM;
        TDCOACE.DATA[WS_NUM1-1].EXP_INT = TDRSMST.EXP_INT;
        TDCOACE.DATA[WS_NUM1-1].HBAL = TDRSMST.HBAL;
        TDCOACE.DATA[WS_NUM1-1].PBAL = TDRSMST.FBAL;
        TDCOACE.DATA[WS_NUM1-1].INSTR_MTH = TDRSMST.INSTR_MTH;
        if (TDRSMST.BV_TYP != ' ' 
            && TDRCMST.BV_TYP == '0' 
            || TDRCMST.BV_TYP == ' ') {
            TDCOACE.DATA[WS_NUM1-1].BV_TYP = TDRSMST.BV_TYP;
        } else {
            TDCOACE.DATA[WS_NUM1-1].BV_TYP = TDRCMST.BV_TYP;
        }
        TDCOACE.DATA[WS_NUM1-1].BV_NO = TDRBVT.BV_NO;
        TDCOACE.DATA[WS_NUM1-1].BV_CD = TDRBVT.BV_CD;
        TDCOACE.DATA[WS_NUM1-1].PART_NUM = TDRSMST.PART_NUM;
        TDCOACE.DATA[WS_NUM1-1].LAST_BAL = TDRSMST.LBAL;
        if (TDRSMST.SER_TIME > 0) {
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            TDRSMST.STSW = TDRSMST.STSW.substring(0, 99 - 1) + "1" + TDRSMST.STSW.substring(99 + 1 - 1);
        }
        TDCOACE.DATA[WS_NUM1-1].ACO_STSW = TDRSMST.STSW;
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(34 - 1, 34 + 1 - 1).equalsIgnoreCase("0") 
            || TDRSMST.STSW.substring(34 - 1, 34 + 1 - 1).trim().length() == 0) {
            R000_GET_ERLY_TYP();
            if (pgmRtn) return;
            TDCOACE.DATA[WS_NUM1-1].ACO_STSW = "" + TDCPIOD.EXP_PRM.ERLY_TYP;
            JIBS_tmp_int = TDCOACE.DATA[WS_NUM1-1].ACO_STSW.length();
            for (int i=0;i<1-JIBS_tmp_int;i++) TDCOACE.DATA[WS_NUM1-1].ACO_STSW = "0" + TDCOACE.DATA[WS_NUM1-1].ACO_STSW;
            if (TDCOACE.DATA[WS_NUM1-1].ACO_STSW == null) TDCOACE.DATA[WS_NUM1-1].ACO_STSW = "";
            JIBS_tmp_int = TDCOACE.DATA[WS_NUM1-1].ACO_STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDCOACE.DATA[WS_NUM1-1].ACO_STSW += " ";
            JIBS_tmp_str[0] = "" + TDCPIOD.EXP_PRM.ERLY_TYP;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            TDCOACE.DATA[WS_NUM1-1].ACO_STSW = TDCOACE.DATA[WS_NUM1-1].ACO_STSW.substring(0, 34 - 1) + JIBS_tmp_str[0] + TDCOACE.DATA[WS_NUM1-1].ACO_STSW.substring(34 + 1 - 1);
            CEP.TRC(SCCGWA, TDCPIOD.EXP_PRM.ERLY_TYP);
        }
        TDCOACE.DATA[WS_NUM1-1].OPEN_DR_AC = TDRSMST.OPEN_DR_AC;
        if (TDRSMST.OPEN_DR_AC.trim().length() > 0) {
            TDCOACE.DATA[WS_NUM1-1].OPEN_DR_AC_NAME = CICQACRI.O_DATA.O_AC_CNM;
        }
        if (TDCOACE.DATA[WS_NUM1-1].C_ACTI_NO.trim().length() == 0 
            && TDRSMST.ACTI_NO.trim().length() > 0) {
            TDCOACE.DATA[WS_NUM1-1].C_ACTI_NO = TDRSMST.ACTI_NO;
            TDCACE.DATA[WS_NUM1-1].C_ACTI_NO = TDRSMST.ACTI_NO;
        }
        CEP.TRC(SCCGWA, TDCOACE.DATA[WS_NUM1-1].OPEN_DR_AC_NAME);
        TDCOACE.DATA[WS_NUM1-1].CHNL_NO = TDRSMST.CHNL_NO;
        TDCOACE.DATA[WS_NUM1-1].OIC_NO = TDRSMST.OIC_NO;
        TDCOACE.DATA[WS_NUM1-1].RES_CD = TDRSMST.RES_CD;
        TDCOACE.DATA[WS_NUM1-1].SUB_DP = TDRSMST.SUB_DP;
        TDCOACE.DATA[WS_NUM1-1].ACCR_INT = TDRSMST.BUD_INT;
        TDCOACE.DATA[WS_NUM1-1].OWN_BR = TDRSMST.OWNER_BRDP;
        TDCOACE.DATA[WS_NUM1-1].CHE_BR = TDRSMST.CHE_BR;
        TDCOACE.DATA[WS_NUM1-1].OPEN_BR = TDRSMST.OWNER_BR;
        TDCOACE.DATA[WS_NUM1-1].OPEN_TLR = TDRSMST.CRT_TLR;
        TDCOACE.DATA[WS_NUM1-1].LAST_BR = TDRSMST.UPD_BR;
        TDCOACE.DATA[WS_NUM1-1].LAST_TLR = TDRSMST.UPD_TLT;
        TDCOACE.DATA[WS_NUM1-1].FC_CD = TDRSMST.FC_CD;
        TDCOACE.DATA[WS_NUM1-1].FC_NO = TDRSMST.FC_NO;
        TDCACE.DATA[WS_NUM1-1].AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
        TDCACE.DATA[WS_NUM1-1].ACO_AC = TDRSMST.KEY.ACO_AC;
        TDCACE.DATA[WS_NUM1-1].PROD_CD = TDRSMST.PROD_CD;
        TDCACE.DATA[WS_NUM1-1].AC_TYP = TDRSMST.PRDAC_CD;
        TDCACE.DATA[WS_NUM1-1].MON_TYP = TDRSMST.MON_TYP;
        TDCACE.DATA[WS_NUM1-1].ACO_TYP = TDRSMST.AC_TYP;
        if (TDRSMST.ACO_STS == '3' 
            && TDRSMST.VAL_DATE <= SCCGWA.COMM_AREA.AC_DATE) {
            TDCACE.DATA[WS_NUM1-1].ACO_STS = '1';
        } else {
            TDCACE.DATA[WS_NUM1-1].ACO_STS = TDRSMST.ACO_STS;
        }
        TDCACE.DATA[WS_NUM1-1].HOLD_NUM = TDRSMST.HOLD_NUM;
        TDCACE.DATA[WS_NUM1-1].CCY = TDRSMST.CCY;
        TDCACE.DATA[WS_NUM1-1].CCY_TYP = TDRSMST.CCY_TYP;
        TDCACE.DATA[WS_NUM1-1].BAL = TDRSMST.BAL;
        if (TDRSMST.ACO_STS == '1' 
            || TDRSMST.ACO_STS == '2' 
            || (TDRSMST.ACO_STS == '3' 
            && TDRSMST.VAL_DATE > SCCGWA.COMM_AREA.AC_DATE)) {
            TDCACE.DATA[WS_NUM1-1].BAL = 0;
        } else {
        }
        TDCACE.DATA[WS_NUM1-1].OPEN_DATE = TDRSMST.OPEN_DATE;
        TDCACE.DATA[WS_NUM1-1].SDT = TDRSMST.VAL_DATE;
        TDCACE.DATA[WS_NUM1-1].DDT = TDRSMST.EXP_DATE;
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (!TDRSMST.STSW.substring(22 - 1, 22 + 1 - 1).equalsIgnoreCase("1")) {
            TDCACE.DATA[WS_NUM1-1].DRW_INT = TDRSMST.DRW_INT;
        } else {
            TDCACE.DATA[WS_NUM1-1].DRW_INT = TDRSMST.BAL_INT;
        }
        TDCACE.DATA[WS_NUM1-1].PART_DATE = TDRSMST.PART_DATE;
        TDCACE.DATA[WS_NUM1-1].CLO_DATE = TDRSMST.CLO_DATE;
        TDCACE.DATA[WS_NUM1-1].ACTI_NO = TDRSMST.ACTI_NO;
        TDCACE.DATA[WS_NUM1-1].TERM = TDRSMST.TERM;
        TDCACE.DATA[WS_NUM1-1].EXP_INT = TDRSMST.EXP_INT;
        TDCACE.DATA[WS_NUM1-1].HBAL = TDRSMST.HBAL;
        TDCACE.DATA[WS_NUM1-1].PBAL = TDRSMST.FBAL;
        TDCACE.DATA[WS_NUM1-1].INSTR_MTH = TDRSMST.INSTR_MTH;
        if (TDRSMST.BV_TYP != ' ' 
            && TDRCMST.BV_TYP == '0') {
            TDCACE.DATA[WS_NUM1-1].BV_TYP = TDRSMST.BV_TYP;
        } else {
            TDCACE.DATA[WS_NUM1-1].BV_TYP = TDRCMST.BV_TYP;
        }
        TDCACE.DATA[WS_NUM1-1].BV_NO = TDRBVT.BV_NO;
        TDCACE.DATA[WS_NUM1-1].BV_CD = TDRBVT.BV_CD;
        TDCACE.DATA[WS_NUM1-1].PART_NUM = TDRSMST.PART_NUM;
        TDCACE.DATA[WS_NUM1-1].LAST_BAL = TDRSMST.LBAL;
        TDCACE.DATA[WS_NUM1-1].KY_BAL = TDCOACE.DATA[WS_NUM1-1].KY_BAL;
        TDCACE.DATA[WS_NUM1-1].ACO_STSW = TDRSMST.STSW;
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(34 - 1, 34 + 1 - 1).equalsIgnoreCase("0") 
            || TDRSMST.STSW.substring(34 - 1, 34 + 1 - 1).trim().length() == 0) {
            TDCACE.DATA[WS_NUM1-1].ACO_STSW = "" + TDCPIOD.EXP_PRM.ERLY_TYP;
            JIBS_tmp_int = TDCACE.DATA[WS_NUM1-1].ACO_STSW.length();
            for (int i=0;i<1-JIBS_tmp_int;i++) TDCACE.DATA[WS_NUM1-1].ACO_STSW = "0" + TDCACE.DATA[WS_NUM1-1].ACO_STSW;
            if (TDCACE.DATA[WS_NUM1-1].ACO_STSW == null) TDCACE.DATA[WS_NUM1-1].ACO_STSW = "";
            JIBS_tmp_int = TDCACE.DATA[WS_NUM1-1].ACO_STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.DATA[WS_NUM1-1].ACO_STSW += " ";
            JIBS_tmp_str[0] = "" + TDCPIOD.EXP_PRM.ERLY_TYP;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            TDCACE.DATA[WS_NUM1-1].ACO_STSW = TDCACE.DATA[WS_NUM1-1].ACO_STSW.substring(0, 34 - 1) + JIBS_tmp_str[0] + TDCACE.DATA[WS_NUM1-1].ACO_STSW.substring(34 + 1 - 1);
        }
        TDCACE.DATA[WS_NUM1-1].OPEN_DR_AC = TDRSMST.OPEN_DR_AC;
        TDCACE.DATA[WS_NUM1-1].OPEN_DR_AC_NAME = CICQACRI.O_DATA.O_AC_CNM;
        TDCACE.DATA[WS_NUM1-1].CHNL_NO = TDRSMST.CHNL_NO;
        TDCACE.DATA[WS_NUM1-1].OIC_NO = TDRSMST.OIC_NO;
        TDCACE.DATA[WS_NUM1-1].RES_CD = TDRSMST.RES_CD;
        TDCACE.DATA[WS_NUM1-1].SUB_DP = TDRSMST.SUB_DP;
        TDCACE.DATA[WS_NUM1-1].ACCR_INT = TDRSMST.BUD_INT;
        TDCACE.DATA[WS_NUM1-1].OWN_BR = TDRSMST.OWNER_BRDP;
        TDCACE.DATA[WS_NUM1-1].CHE_BR = TDRSMST.CHE_BR;
        TDCACE.DATA[WS_NUM1-1].OPEN_BR = TDRSMST.OWNER_BR;
        TDCACE.DATA[WS_NUM1-1].OPEN_TLR = TDRSMST.CRT_TLR;
        TDCACE.DATA[WS_NUM1-1].LAST_DATE = TDRCDI.FST_INT_DATE;
        CEP.TRC(SCCGWA, TDRCDI.FST_INT_DATE);
        CEP.TRC(SCCGWA, TDRSMST.VAL_DATE);
        CEP.TRC(SCCGWA, TDRCDI.PERD_TYP);
        if (TDRCDI.PERD_TYP == '1' 
            || TDRCDI.PERD_TYP == '2') {
            if (TDRCDI.FST_INT_DATE == TDRSMST.VAL_DATE 
                || TDRCDI.FST_INT_DATE == 0) {
                R000_GET_FST_DATE();
                if (pgmRtn) return;
                TDCACE.DATA[WS_NUM1-1].LAST_DATE = WS_FST_DATE;
            }
        }
        TDCACE.DATA[WS_NUM1-1].LAST_BR = TDRSMST.UPD_BR;
        TDCACE.DATA[WS_NUM1-1].LAST_TLR = TDRSMST.UPD_TLT;
        TDCACE.DATA[WS_NUM1-1].PREV_FLG = TDCOACE.DATA[WS_NUM1-1].PREV_FLG;
        TDCACE.DATA[WS_NUM1-1].FC_CD = TDRSMST.FC_CD;
    }
    public void R000_GET_FST_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = TDRSMST.VAL_DATE;
        if (TDRCDI.PERD_TYP == '1') {
            SCCCLDT.MTHS = TDRCDI.CD_PERD;
        }
        if (TDRCDI.PERD_TYP == '2') {
            SCCCLDT.DAYS = TDRCDI.CD_PERD;
        }
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        WS_FST_DATE = SCCCLDT.DATE2;
        CEP.TRC(SCCGWA, TDCACE.DATA[WS_NUM1-1].LAST_DATE);
    }
    public void R000_GET_RAT_TTZ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRIREV);
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.KEY.ACO_AC = TDRCDI.KEY.ACO_AC;
        CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
        T000_READ_TDTSMST();
        if (pgmRtn) return;
        TDRIREV.KEY.STR_DATE = TDRSMST.VAL_DATE;
        CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
        TDRIREV.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
        T000_READ_TDTIREV_TZZ();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCCINTI);
        BPCCINTI.FUNC = 'I';
        BPCCINTI.BASE_INFO.BASE_TYP = TDRIREV.RATE_TYPE;
        if (TDRIREV.SPRD_PCT != 0 
            || TDRIREV.SPRD_PNT != 0) {
            if (BPCCINTI.BASE_INFO.BASE_TYP == null) BPCCINTI.BASE_INFO.BASE_TYP = "";
            JIBS_tmp_int = BPCCINTI.BASE_INFO.BASE_TYP.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) BPCCINTI.BASE_INFO.BASE_TYP += " ";
            if (BPCCINTI.BASE_INFO.BASE_TYP.substring(0, 1).equalsIgnoreCase("C")) {
                if (BPCCINTI.BASE_INFO.BASE_TYP == null) BPCCINTI.BASE_INFO.BASE_TYP = "";
                JIBS_tmp_int = BPCCINTI.BASE_INFO.BASE_TYP.length();
                for (int i=0;i<3-JIBS_tmp_int;i++) BPCCINTI.BASE_INFO.BASE_TYP += " ";
                BPCCINTI.BASE_INFO.BASE_TYP = "A" + BPCCINTI.BASE_INFO.BASE_TYP.substring(1);
            }
            if (BPCCINTI.BASE_INFO.BASE_TYP == null) BPCCINTI.BASE_INFO.BASE_TYP = "";
            JIBS_tmp_int = BPCCINTI.BASE_INFO.BASE_TYP.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) BPCCINTI.BASE_INFO.BASE_TYP += " ";
            if (BPCCINTI.BASE_INFO.BASE_TYP.substring(0, 1).equalsIgnoreCase("D")) {
                if (BPCCINTI.BASE_INFO.BASE_TYP == null) BPCCINTI.BASE_INFO.BASE_TYP = "";
                JIBS_tmp_int = BPCCINTI.BASE_INFO.BASE_TYP.length();
                for (int i=0;i<3-JIBS_tmp_int;i++) BPCCINTI.BASE_INFO.BASE_TYP += " ";
                BPCCINTI.BASE_INFO.BASE_TYP = "B" + BPCCINTI.BASE_INFO.BASE_TYP.substring(1);
            }
        }
        BPCCINTI.BASE_INFO.CCY = TDRSMST.CCY;
        BPCCINTI.BASE_INFO.TENOR = TDRIREV.TENOR;
        BPCCINTI.BASE_INFO.DT = SCCGWA.COMM_AREA.AC_DATE;
        if (TDRSMST.AC_BK != 0) {
            BPCCINTI.BASE_INFO.BR = TDRSMST.AC_BK;
        } else {
            BPCCINTI.BASE_INFO.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        }
        S000_CALL_BPZCINTI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDRIREV.INT_SEL);
        if (TDRIREV.INT_SEL == '0'
            || TDRIREV.INT_SEL == '3') {
            WS_OUTPUT_RAT = BPCCINTI.BASE_INFO.RATE;
            CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.RATE);
        } else if (TDRIREV.INT_SEL == '1') {
            WS_OUTPUT_RAT = BPCCINTI.BASE_INFO.RATE * ( 1 + TDRIREV.SPRD_PCT / 100 );
            CEP.TRC(SCCGWA, TDRIREV.SPRD_PCT);
        } else if (TDRIREV.INT_SEL == '2') {
            WS_OUTPUT_RAT = BPCCINTI.BASE_INFO.RATE + TDRIREV.SPRD_PNT;
            CEP.TRC(SCCGWA, TDRIREV.SPRD_PNT);
        } else if (TDRIREV.INT_SEL == '4') {
            WS_OUTPUT_RAT = TDRIREV.CON_RATE;
            CEP.TRC(SCCGWA, TDRIREV.CON_RATE);
        } else {
        }
    }
    public void R000_GET_ERLY_TYP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = TDRSMST.PROD_CD;
        CEP.TRC(SCCGWA, BPCPQPRD);
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        IBS.init(SCCGWA, TDCPIOD);
        if (TDRSMST.ACTI_NO.trim().length() > 0) {
            TDCPIOD.ACTI_NO = TDRSMST.ACTI_NO;
        }
        TDCPIOD.PROD_CD = BPCPQPRD.PARM_CODE;
        S000_CALL_TDZPROD();
        if (pgmRtn) return;
    }
    public void R000_TRANS_PAGE_ROW() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCACE.PAGE_INF.PAGE_ROW);
        if (TDCACE.PAGE_INF.PAGE_ROW == 0) {
            WS_P_ROW = 6;
        } else {
            if (TDCACE.PAGE_INF.PAGE_ROW > 6) {
                WS_P_ROW = 6;
            } else {
                WS_P_ROW = TDCACE.PAGE_INF.PAGE_ROW;
            }
        }
        CEP.TRC(SCCGWA, TDCACE.PAGE_INF.PAGE_ROW);
        CEP.TRC(SCCGWA, WS_P_NUM);
        CEP.TRC(SCCGWA, WS_P_ROW);
    }
    public void R000_PAGE_COM() throws IOException,SQLException,Exception {
        if (WS_L_CNT != 0) {
            CEP.TRC(SCCGWA, WS_L_CNT);
            TDCOACE.TOTAL_NUM = WS_L_CNT;
            WS_L_ROW = TDCOACE.TOTAL_NUM % WS_P_ROW;
            WS_T_PAGE = (int) ((TDCOACE.TOTAL_NUM - WS_L_ROW) / WS_P_ROW);
            if (WS_L_ROW == 0) {
                CEP.TRC(SCCGWA, "000000");
                TDCOACE.TOTAL_PAGE = WS_T_PAGE;
                if (WS_L_CNT != 0) {
                    WS_L_ROW = WS_P_ROW;
                }
            } else {
                CEP.TRC(SCCGWA, "XXXXXX");
                TDCOACE.TOTAL_PAGE = WS_T_PAGE + 1;
            }
            if (TDCACE.PAGE_INF.PAGE_NUM != 0) {
                if (TDCACE.PAGE_INF.PAGE_NUM >= TDCOACE.TOTAL_PAGE) {
                    CEP.TRC(SCCGWA, ">>>===");
                    TDCOACE.CURR_PAGE = TDCOACE.TOTAL_PAGE;
                    TDCOACE.LAST_PAGE = 'Y';
                    TDCOACE.PAGE_ROW = WS_L_ROW;
                } else {
                    CEP.TRC(SCCGWA, "<<<<<<");
                    TDCOACE.CURR_PAGE = TDCACE.PAGE_INF.PAGE_NUM;
                    TDCOACE.LAST_PAGE = 'N';
                    TDCOACE.PAGE_ROW = WS_P_ROW;
                }
            }
            CEP.TRC(SCCGWA, TDCACE.PAGE_INF.PAGE_NUM);
            if (TDCACE.PAGE_INF.PAGE_NUM == 0) {
                TDCOACE.CURR_PAGE = 1;
                if (TDCOACE.TOTAL_PAGE == 1) {
                    TDCOACE.LAST_PAGE = 'Y';
                    TDCOACE.PAGE_ROW = WS_L_ROW;
                } else {
                    TDCOACE.LAST_PAGE = 'N';
                    TDCOACE.PAGE_ROW = WS_P_ROW;
                }
            }
            CEP.TRC(SCCGWA, WS_P_NUM);
            CEP.TRC(SCCGWA, TDCOACE.CURR_PAGE);
            WS_P_NUM = TDCOACE.CURR_PAGE - 1;
            CEP.TRC(SCCGWA, WS_P_NUM);
            WS_STR_NUM = WS_P_NUM * WS_P_ROW;
            CEP.TRC(SCCGWA, WS_STR_NUM);
            WS_END_NUM = TDCOACE.CURR_PAGE * WS_P_ROW;
            CEP.TRC(SCCGWA, WS_END_NUM);
            CEP.TRC(SCCGWA, "PAGE1 INFO:");
            CEP.TRC(SCCGWA, WS_L_CNT);
            CEP.TRC(SCCGWA, TDCOACE.TOTAL_NUM);
            CEP.TRC(SCCGWA, WS_P_ROW);
            CEP.TRC(SCCGWA, WS_T_PAGE);
            CEP.TRC(SCCGWA, WS_L_ROW);
            CEP.TRC(SCCGWA, TDCOACE.TOTAL_PAGE);
            CEP.TRC(SCCGWA, TDCOACE.PAGE_ROW);
            CEP.TRC(SCCGWA, TDCOACE.CURR_PAGE);
            CEP.TRC(SCCGWA, TDCOACE.LAST_PAGE);
            CEP.TRC(SCCGWA, WS_P_NUM);
            CEP.TRC(SCCGWA, WS_STR_NUM);
            CEP.TRC(SCCGWA, WS_END_NUM);
        }
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCCLDT.DATE1);
        CEP.TRC(SCCGWA, SCCCLDT.DATE2);
        CEP.TRC(SCCGWA, SCCCLDT.MTHS);
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            CEP.ERR(SCCGWA, SCCCLDT.RC);
        }
        CEP.TRC(SCCGWA, SCCCLDT.DATE1);
        CEP.TRC(SCCGWA, SCCCLDT.DATE2);
    }
    public void T000_READ_TDTIREV_TZZ() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRIREV.KEY.STR_DATE);
        TDTIREV_RD = new DBParm();
        TDTIREV_RD.TableName = "TDTIREV";
        TDTIREV_RD.eqWhere = "ACO_AC";
        TDTIREV_RD.where = "STR_DATE <= :TDRIREV.KEY.STR_DATE "
            + "AND END_DATE > :TDRIREV.KEY.STR_DATE "
            + "OR END_DATE = 0";
        TDTIREV_RD.fst = true;
        TDTIREV_RD.order = "STR_DATE DESC";
        IBS.READ(SCCGWA, TDRIREV, this, TDTIREV_RD);
        CEP.TRC(SCCGWA, TDRIREV.KEY.STR_DATE);
        CEP.TRC(SCCGWA, TDRIREV.INT_SEL);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, TDRBVT.KEY.AC_NO);
        } else {
        }
    }
    public void T000_READ_TDTIREV() throws IOException,SQLException,Exception {
        TDTIREV_RD = new DBParm();
        TDTIREV_RD.TableName = "TDTIREV";
        IBS.READ(SCCGWA, TDRIREV, TDTIREV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, TDRBVT.KEY.AC_NO);
        } else {
        }
    }
    public void S000_CALL_BPZCINTI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-C-INTR-INQ", BPCCINTI);
        if (BPCCINTI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCCINTI.RC);
            BPCCINTI.BASE_INFO.RATE = 0;
        }
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_TDZQPMP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-BASE-PRD-QRY", TDCQPMP);
        CEP.TRC(SCCGWA, TDCQPMP.RC.RC_RTNCODE);
        if (TDCQPMP.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, TDCQPMP.RC);
        }
    }
    public void R000_MOVE_OUT_ORTHER() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRIREV.CON_RATE);
        CEP.TRC(SCCGWA, TDRAINT.PRV_RAT);
        CEP.TRC(SCCGWA, TDRAINT.OVE_RAT);
        CEP.TRC(SCCGWA, TDRAINT.DUE_RAT);
        CEP.TRC(SCCGWA, TDRBVT.BV_NO);
        CEP.TRC(SCCGWA, TDRBVT.STSW);
        CEP.TRC(SCCGWA, TDRINST.STL_AC);
        CEP.TRC(SCCGWA, TDRINST.STL_INT_AC);
        CEP.TRC(SCCGWA, TDRCDI.PAY_AC);
        CEP.TRC(SCCGWA, TDRCDI.PERD_TYP);
        CEP.TRC(SCCGWA, TDRCDI.CD_PERD);
        CEP.TRC(SCCGWA, TDRCDI.CD_AMT);
        CEP.TRC(SCCGWA, TDRCDI.LOS_NUM);
        CEP.TRC(SCCGWA, TDRCDI.LOS_DNUM);
        CEP.TRC(SCCGWA, TDRINST.SER_TIME);
        CEP.TRC(SCCGWA, TDRIREV.INT_SEL);
        CEP.TRC(SCCGWA, TDRINST.INSTR_TERM);
        TDCOACE.DATA[WS_NUM1-1].INT_RAT = TDRIREV.CON_RATE;
        TDCOACE.DATA[WS_NUM1-1].C_ACTI_NO = TDRIREV.ACTI_NO;
        TDCOACE.DATA[WS_NUM1-1].INT_SEL = TDRIREV.INT_SEL;
        TDCOACE.DATA[WS_NUM1-1].SPRD_PNT = TDRIREV.SPRD_PNT;
        TDCOACE.DATA[WS_NUM1-1].SPRD_PCT = TDRIREV.SPRD_PCT;
        TDCOACE.DATA[WS_NUM1-1].INT_RUL_CD = TDRIREV.INT_RUL_CD;
        TDCACE.DATA[WS_NUM1-1].INT_RAT = TDRIREV.CON_RATE;
        TDCACE.DATA[WS_NUM1-1].C_ACTI_NO = TDRIREV.ACTI_NO;
        TDCACE.DATA[WS_NUM1-1].INT_SEL = TDRIREV.INT_SEL;
        TDCACE.DATA[WS_NUM1-1].SPRD_PNT = TDRIREV.SPRD_PNT;
        TDCACE.DATA[WS_NUM1-1].SPRD_PCT = TDRIREV.SPRD_PCT;
        TDCACE.DATA[WS_NUM1-1].INT_RUL_CD = TDRIREV.INT_RUL_CD;
        WS_RUL_1 = " ";
        WS_RUL_2 = " ";
        WS_RUL_3 = " ";
        WS_RUL_4 = " ";
        WS_RUL_5 = " ";
        WS_RUL_6 = " ";
        WS_RUL_7 = " ";
        WS_RUL_8 = " ";
        WS_BALT_9 = 0;
        WS_LVLT_9 = 0;
        WS_BRT_9 = 0;
        if (TDRIREV.BAL != 0) {
            WS_BALT_9 = (long) TDRIREV.BAL;
            WS_RUL_1 = "BAL:";
        }
        if (TDRIREV.TERM.trim().length() > 0) {
            WS_RUL_2 = "TERM";
        }
        if (TDRIREV.LVL != 0) {
            WS_LVLT_9 = TDRIREV.LVL;
            WS_RUL_3 = "LVL";
        }
        if (TDRIREV.GRPS_NO.trim().length() > 0) {
            WS_RUL_4 = "GRPS_NO";
        }
        if (TDRIREV.BR != 0) {
            WS_BRT_9 = TDRIREV.BR;
            WS_RUL_5 = "BR";
        }
        if (TDRIREV.CITY_CD.trim().length() > 0) {
            WS_RUL_6 = "CITY-CD";
        }
        if (TDRIREV.CHNL_NO.trim().length() > 0) {
            WS_RUL_7 = "CHNL-NO";
        }
        if (TDRIREV.OTH_FIL.trim().length() > 0) {
            WS_RUL_8 = "OTH-FIL";
        }
        TDCOACE.DATA[WS_NUM1-1].RUL_MSG = WS_RUL_1;
        TDCACE.DATA[WS_NUM1-1].RUL_MSG = WS_RUL_1;
        CEP.TRC(SCCGWA, "HAHA");
        TDCOACE.DATA[WS_NUM1-1].PRV_RAT = TDRAINT.PRV_RAT;
        TDCOACE.DATA[WS_NUM1-1].OVE_RAT = TDRAINT.OVE_RAT;
        TDCOACE.DATA[WS_NUM1-1].DUE_RAT = TDRAINT.DUE_RAT;
        TDCOACE.DATA[WS_NUM1-1].BV_NO = TDRBVT.BV_NO;
        TDCOACE.DATA[WS_NUM1-1].DAC_STSW = TDRBVT.STSW;
        if (TDRSMST.INSTR_MTH == '6') {
            TDCOACE.DATA[WS_NUM1-1].STL_AC = TDRINST.STL_INT_AC;
        } else {
            TDCOACE.DATA[WS_NUM1-1].STL_AC = TDRINST.STL_AC;
        }
        CEP.TRC(SCCGWA, "HAHAha");
        TDCOACE.DATA[WS_NUM1-1].PAY_AC = TDRCDI.PAY_AC;
        TDCOACE.DATA[WS_NUM1-1].PERD_TYP = TDRCDI.PERD_TYP;
        TDCOACE.DATA[WS_NUM1-1].CD_PERD = TDRCDI.CD_PERD;
        TDCOACE.DATA[WS_NUM1-1].CD_AMT = TDRCDI.CD_AMT;
        TDCOACE.DATA[WS_NUM1-1].LOS_NUM = TDRCDI.LOS_NUM;
        TDCOACE.DATA[WS_NUM1-1].LAST_DATE = TDRCDI.FST_INT_DATE;
        if (TDRCDI.PERD_TYP == '1' 
            || TDRCDI.PERD_TYP == '2') {
            if (TDRCDI.FST_INT_DATE == TDRSMST.VAL_DATE 
                || TDRCDI.FST_INT_DATE == 0) {
                R000_GET_FST_DATE();
                if (pgmRtn) return;
                TDCOACE.DATA[WS_NUM1-1].LAST_DATE = WS_FST_DATE;
            }
        }
        if (!TDRSMST.PRDAC_CD.equalsIgnoreCase("025") 
            && !TDRSMST.PRDAC_CD.equalsIgnoreCase("026") 
            && !TDRSMST.PRDAC_CD.equalsIgnoreCase("024") 
            && !TDRSMST.PRDAC_CD.equalsIgnoreCase("027") 
            && !TDRSMST.PRDAC_CD.equalsIgnoreCase("028")) {
            TDCOACE.DATA[WS_NUM1-1].LAST_DATE = TDRSMST.CLO_DATE;
        }
        CEP.TRC(SCCGWA, "HAHApi");
        TDCOACE.DATA[WS_NUM1-1].LOS_DNUM = TDRCDI.LOS_DNUM;
        TDCOACE.DATA[WS_NUM1-1].INTOUT = TDRCDI.INTOUT;
        if (TDRCDI.REMMIT_BK.trim().length() == 0) TDCOACE.DATA[WS_NUM1-1].REMMIT_BK = 0;
        else TDCOACE.DATA[WS_NUM1-1].REMMIT_BK = Long.parseLong(TDRCDI.REMMIT_BK);
        TDCOACE.DATA[WS_NUM1-1].REMMIT_NM = TDRCDI.REMMIT_NM;
        CEP.TRC(SCCGWA, "bilibli");
        TDCOACE.DATA[WS_NUM1-1].NOR_NUM = TDRINST.SER_TIME;
        TDCOACE.DATA[WS_NUM1-1].RL_TERM = TDRINST.INSTR_TERM;
        TDCACE.DATA[WS_NUM1-1].PRV_RAT = TDRAINT.PRV_RAT;
        TDCACE.DATA[WS_NUM1-1].OVE_RAT = TDRAINT.OVE_RAT;
        TDCACE.DATA[WS_NUM1-1].DUE_RAT = TDRAINT.DUE_RAT;
        TDCACE.DATA[WS_NUM1-1].BV_NO = TDRBVT.BV_NO;
        TDCACE.DATA[WS_NUM1-1].DAC_STSW = TDRBVT.STSW;
        if (TDRSMST.INSTR_MTH == '6') {
            TDCACE.DATA[WS_NUM1-1].STL_AC = TDRINST.STL_INT_AC;
        } else {
            TDCACE.DATA[WS_NUM1-1].STL_AC = TDRINST.STL_AC;
        }
        CEP.TRC(SCCGWA, "HAHAha");
        TDCACE.DATA[WS_NUM1-1].PAY_AC = TDRCDI.PAY_AC;
        TDCACE.DATA[WS_NUM1-1].PERD_TYP = TDRCDI.PERD_TYP;
        TDCACE.DATA[WS_NUM1-1].CD_PERD = TDRCDI.CD_PERD;
        TDCACE.DATA[WS_NUM1-1].CD_AMT = TDRCDI.CD_AMT;
        TDCACE.DATA[WS_NUM1-1].LOS_NUM = TDRCDI.LOS_NUM;
        CEP.TRC(SCCGWA, "HAHApi");
        TDCACE.DATA[WS_NUM1-1].LOS_DNUM = TDRCDI.LOS_DNUM;
        TDCACE.DATA[WS_NUM1-1].INTOUT = TDRCDI.INTOUT;
        TDCACE.DATA[WS_NUM1-1].REMMIT_BK = TDRCDI.REMMIT_BK;
        TDCACE.DATA[WS_NUM1-1].REMMIT_NM = TDRCDI.REMMIT_NM;
        CEP.TRC(SCCGWA, "bilibli");
        TDCACE.DATA[WS_NUM1-1].NOR_NUM = TDRINST.SER_TIME;
        TDCACE.DATA[WS_NUM1-1].RL_TERM = TDRINST.INSTR_TERM;
        CEP.TRC(SCCGWA, TDRSMST.PRDAC_CD);
        CEP.TRC(SCCGWA, TDRSMST.OPEN_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, TDRSMST.BAL);
        CEP.TRC(SCCGWA, TDRSMST.LBAL_DATE);
        if (TDRSMST.PRDAC_CD.equalsIgnoreCase("035")) {
            R000_GET_GROPINFO();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_TDTSMST() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTSMST_BR);
    }
    public void T000_STARTBR_TDTDOCU() throws IOException,SQLException,Exception {
        TDTDOCU_BR.rp = new DBParm();
        TDTDOCU_BR.rp.TableName = "TDTDOCU";
        TDTDOCU_BR.rp.where = "DOCU_NO = :TDRDOCU.KEY.DOCU_NO";
        TDTDOCU_BR.rp.order = "DOCU_TERM DESC";
        IBS.STARTBR(SCCGWA, TDRDOCU, this, TDTDOCU_BR);
    }
    public void T000_READNEXT_TDTDOCU() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRDOCU, this, TDTDOCU_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DOCU_FLG = 'Y';
        } else {
            WS_DOCU_FLG = 'N';
        }
    }
    public void T000_ENDBR_TDTDOCU() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTDOCU_BR);
    }
    public void T000_READ_TDTSMSTMR() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.where = "AC_NO = :TDRSMST.AC_NO "
            + "AND SUBSTR ( STSW , 52 , 1 ) < > '1' "
            + "AND ACO_STS = '0'";
        TDTSMST_RD.fst = true;
        IBS.READ(SCCGWA, TDRSMST, this, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MR_FLAG = 'N';
        } else {
            WS_MR_FLAG = 'F';
        }
    }
    public void T000_GROUP_TDTSMST_2() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.set = "WS-GROP-AMT=SUM(BAL),WS-GROP-INT=SUM(BUD_INT)";
        TDTSMST_RD.where = "AC_NO = :TDRSMST.AC_NO "
            + "AND SUBSTR ( STSW , 52 , 1 ) < > '1' "
            + "AND ACO_STS = '0'";
        IBS.GROUP(SCCGWA, TDRSMST, this, TDTSMST_RD);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
