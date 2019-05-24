package com.hisun.TD;

import com.hisun.DD.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.BP.*;
import com.hisun.CI.*;
import com.hisun.DC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class TDZUMAW {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    DBParm TDTCMST_RD;
    DBParm TDTINST_RD;
    DBParm TDTCDI_RD;
    DBParm TDTSMST_RD;
    brParm TDTSMST_BR = new brParm();
    boolean pgmRtn = false;
    String K_OUT_FMT = "TD513";
    String WS_ERR_MSG = " ";
    TDZUMAW_CP_PROD_CD CP_PROD_CD = new TDZUMAW_CP_PROD_CD();
    char WS_TABLE_FLG = ' ';
    char WS_SMST_FLG = ' ';
    String WS_DEAW_MTH = " ";
    char WS_CR_LMT_P = ' ';
    char WS_DR_LMT_P = ' ';
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    TDRCMST TDRCMST = new TDRCMST();
    TDRSMST TDRSMST = new TDRSMST();
    TDCOMAW TDCOMAW = new TDCOMAW();
    CICACCU CICACCU = new CICACCU();
    CICSACR CICSACR = new CICSACR();
    CICQACRL CICQACRL = new CICQACRL();
    TDCPRDP TDCPRDP = new TDCPRDP();
    TDCMPR TDCMPR = new TDCMPR();
    CICSACRL CICSACRL = new CICSACRL();
    TDCACM TDCACM = new TDCACM();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    CICCUST CICCUST = new CICCUST();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    CICQACRI CICQACRI = new CICQACRI();
    TDRCDI TDRCDI = new TDRCDI();
    TDRINST TDRINST = new TDRINST();
    DCCUMATP DCCUMATP = new DCCUMATP();
    DDCIMCYY DDCIMCYY = new DDCIMCYY();
    CICQACAC CICQACAC = new CICQACAC();
    BPCPQORG BPCPQORG = new BPCPQORG();
    TDCMPRD TDCMPRD = new TDCMPRD();
    TDCPROD TDCPROD = new TDCPROD();
    TDCQPMP TDCQPMP = new TDCQPMP();
    SCCGWA SCCGWA;
    BPCPORUP_DATA_INFO BPCPORUP;
    TDCUMAW TDCUMAW;
    public void MP(SCCGWA SCCGWA, TDCUMAW TDCUMAW) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCUMAW = TDCUMAW;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDZUMAW return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_01_CI_INF_PROC();
        if (pgmRtn) return;
        B100_03_AC_INF_PROC();
        if (pgmRtn) return;
        B100_05_REL_AC_PROC();
        if (pgmRtn) return;
        B300_FMT_OUTPUT_PROC();
        if (pgmRtn) return;
        B510_UPDATE_CIZSACR_PROC();
        if (pgmRtn) return;
        B530_CHK_MPROD_INF();
        if (pgmRtn) return;
        B550_UPDATE_TDTCMST_PROC();
        if (pgmRtn) return;
        if (TDCUMAW.GL_AC_N.trim().length() > 0) {
            R000_CHK_CI_INFO();
            if (pgmRtn) return;
            R000_CHK_DD_INFO();
            if (pgmRtn) return;
            R000_CHK_SMST_GL_AC();
            if (pgmRtn) return;
            R000_CHK_CHENG_AC();
            if (pgmRtn) return;
        }
        B240_WRI_NFIN_HIS_PROC();
        if (pgmRtn) return;
        B570_UPDATE_CIZSACRL_PROC();
        if (pgmRtn) return;
    }
    public void B100_01_CI_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = TDCUMAW.MAIN_AC;
        CEP.TRC(SCCGWA, TDCUMAW.MAIN_AC);
        CEP.TRC(SCCGWA, CICACCU.DATA.AGR_NO);
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICACCU.DATA.AC_CNM);
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_TYP);
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICACCU.DATA.STS);
        CEP.TRC(SCCGWA, CICACCU.DATA.PROD_CD);
    }
    public void B100_03_AC_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRCMST);
        TDRCMST.KEY.AC_NO = TDCUMAW.MAIN_AC;
        CEP.TRC(SCCGWA, TDRCMST.KEY.AC_NO);
        T000_READ_TDTCMST();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = TDRCMST.OWNER_BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQORG.BBR);
        CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.BBR);
        if (TDRCMST.CI_TYP == '2' 
            && BPCPQORG.BBR != BPCPORUP.DATA_INFO.BBR) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_TRADE_BR_NOT_SAME);
        }
        if (WS_TABLE_FLG == 'N') {
            CEP.TRC(SCCGWA, "1111111");
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_I_NO_RECORD);
        }
        if (TDRCMST.PROD_CD.trim().length() == 0) {
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.AC_NO = TDCUMAW.MAIN_AC;
            T000_READ_TDTSMST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, TDRSMST.PROD_CD);
            CICACCU.DATA.PROD_CD = TDRSMST.PROD_CD;
        }
        CEP.TRC(SCCGWA, TDCUMAW.DW_MTH_N);
        if (TDCUMAW.DW_MTH_N == ' ') {
            TDCUMAW.DW_MTH_N = TDCUMAW.DW_MTH_O;
        }
        if (!TDRSMST.PRDAC_CD.equalsIgnoreCase("021")) {
            if ((TDCUMAW.DW_MTH_N != '1' 
                && TDCUMAW.DW_MTH_N != '4') 
                && (TDCUMAW.DR_FLG_N != '2') 
                && TDRCMST.CI_TYP == '1') {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DM_CRDR_ERR);
            }
        }
        CEP.TRC(SCCGWA, TDRCMST.STS);
        CEP.TRC(SCCGWA, TDRCMST.CI_TYP);
        CEP.TRC(SCCGWA, TDRCMST.DRAW_MTH);
        CEP.TRC(SCCGWA, TDRCMST.DRAW_INF);
        CEP.TRC(SCCGWA, TDRCMST.CROS_CR_FLG);
        CEP.TRC(SCCGWA, TDRCMST.CROS_DR_FLG);
    }
    public void B100_05_REL_AC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.FUNC = 'I';
        CEP.TRC(SCCGWA, TDCUMAW.MAIN_AC);
        CICQACRL.DATA.AC_NO = TDCUMAW.MAIN_AC;
        CICQACRL.DATA.REL_AC_NO = TDCUMAW.GL_AC_O;
        S000_CALL_CIZQACRL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_AC_REL);
    }
    public void B300_FMT_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCOMAW);
        TDCOMAW.MAIN_AC = TDCUMAW.MAIN_AC;
        TDCOMAW.AC_NM = CICACCU.DATA.AC_CNM;
        TDCOMAW.DW_MTH_O = TDRCMST.DRAW_MTH;
        TDCOMAW.CR_FLG_O = TDRCMST.CROS_CR_FLG;
        TDCOMAW.DR_FLG_O = TDRCMST.CROS_DR_FLG;
        TDCOMAW.GL_AC_O = CICQACRL.O_DATA.O_REL_AC_NO;
        TDCOMAW.DW_MTH_N = TDCUMAW.DW_MTH_N;
        TDCOMAW.DRAW_PSW = TDCUMAW.DRAW_PSW;
        TDCOMAW.CR_FLG_N = TDCUMAW.CR_FLG_N;
        TDCOMAW.DR_FLG_N = TDCUMAW.DR_FLG_N;
        TDCOMAW.GL_AC_N = TDCUMAW.GL_AC_N;
        CEP.TRC(SCCGWA, TDCOMAW.MAIN_AC);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUT_FMT;
        SCCFMT.DATA_PTR = TDCOMAW;
        SCCFMT.DATA_LEN = 387;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B510_UPDATE_CIZSACR_PROC() throws IOException,SQLException,Exception {
        if (CICACCU.DATA.CI_TYP == '2' 
            || CICACCU.DATA.CI_TYP == '3') {
            CICSACR.FUNC = 'M';
            CICSACR.DATA.AGR_NO = TDCUMAW.MAIN_AC;
            CICSACR.DATA.AC_CNM = TDCUMAW.AC_NM;
            CEP.TRC(SCCGWA, TDCUMAW.AC_NM);
            CEP.TRC(SCCGWA, CICSACR.DATA.AC_CNM);
            S000_CALL_CIZSACR();
            if (pgmRtn) return;
        }
    }
    public void B530_CHK_MPROD_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICACCU.DATA.PROD_CD);
        IBS.init(SCCGWA, BPCPQPRD);
        CEP.TRC(SCCGWA, CICACCU.DATA.PROD_CD);
        BPCPQPRD.PRDT_CODE = CICACCU.DATA.PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
        if (TDRCMST.PROD_CD.trim().length() == 0) {
            IBS.init(SCCGWA, TDCQPMP);
            IBS.init(SCCGWA, TDCPRDP);
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
            WS_DEAW_MTH = TDCPRDP.TXN_PRM.DRAW_MTH;
            WS_CR_LMT_P = TDCPRDP.TXN_PRM.CR_LMT;
            WS_DR_LMT_P = TDCPRDP.TXN_PRM.DR_LMT;
        } else {
            TDCMPRD.FUNC = 'I';
            TDCMPRD.PROD_CD = BPCPQPRD.PARM_CODE;
            S000_CALL_TDZMPRD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, TDCMPRD.DRAW_MTH_DESC);
            CEP.TRC(SCCGWA, TDCMPRD.CROS_CR_LMT);
            CEP.TRC(SCCGWA, TDCMPRD.CROS_DR_LMT);
            WS_DEAW_MTH = TDCMPRD.DRAW_MTH_DESC;
            WS_CR_LMT_P = TDCMPRD.CROS_CR_LMT;
            WS_DR_LMT_P = TDCMPRD.CROS_DR_LMT;
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, TDCUMAW.DW_MTH_N);
        CEP.TRC(SCCGWA, TDCMPR.SUM.DRAW_MTH_DESC);
        if (TDCMPR.SUM.DRAW_MTH_DESC == null) TDCMPR.SUM.DRAW_MTH_DESC = "";
        JIBS_tmp_int = TDCMPR.SUM.DRAW_MTH_DESC.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) TDCMPR.SUM.DRAW_MTH_DESC += " ";
        CEP.TRC(SCCGWA, TDCMPR.SUM.DRAW_MTH_DESC.substring(0, 4));
        if (WS_DEAW_MTH == null) WS_DEAW_MTH = "";
        JIBS_tmp_int = WS_DEAW_MTH.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) WS_DEAW_MTH += " ";
        if (WS_DEAW_MTH == null) WS_DEAW_MTH = "";
        JIBS_tmp_int = WS_DEAW_MTH.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) WS_DEAW_MTH += " ";
        if (WS_DEAW_MTH == null) WS_DEAW_MTH = "";
        JIBS_tmp_int = WS_DEAW_MTH.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) WS_DEAW_MTH += " ";
        if (WS_DEAW_MTH == null) WS_DEAW_MTH = "";
        JIBS_tmp_int = WS_DEAW_MTH.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) WS_DEAW_MTH += " ";
        if (WS_DEAW_MTH == null) WS_DEAW_MTH = "";
        JIBS_tmp_int = WS_DEAW_MTH.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) WS_DEAW_MTH += " ";
        if ((TDCUMAW.DW_MTH_N == '1' 
            && !WS_DEAW_MTH.substring(0, 1).equalsIgnoreCase("Y")) 
            || (TDCUMAW.DW_MTH_N == '2' 
            && !WS_DEAW_MTH.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("Y")) 
            || (TDCUMAW.DW_MTH_N == '3' 
            && !WS_DEAW_MTH.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("Y")) 
            || (TDCUMAW.DW_MTH_N == '4' 
            && !WS_DEAW_MTH.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("Y")) 
            || (TDCUMAW.DW_MTH_N == '5' 
            && !WS_DEAW_MTH.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("Y"))) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DRAW_MTH_NOT_MATCH);
        }
        CEP.TRC(SCCGWA, TDCUMAW.CR_FLG_N);
        CEP.TRC(SCCGWA, WS_CR_LMT_P);
        if (TDCUMAW.CR_FLG_N != '0' 
            && TDCUMAW.CR_FLG_N != '1' 
            && TDCUMAW.CR_FLG_N != '2') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CROS_FLG_OVERF);
        } else {
            if (WS_CR_LMT_P == '0' 
                && (TDCUMAW.CR_FLG_N != '0' 
                && TDCUMAW.CR_FLG_N != '2')) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CROS_CR_N_MATCH_PRD);
            }
            if (WS_CR_LMT_P == '2' 
                && TDCUMAW.CR_FLG_N != '2') {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CROS_CR_N_MATCH_PRD);
            }
        }
        CEP.TRC(SCCGWA, TDCUMAW.DR_FLG_N);
        CEP.TRC(SCCGWA, WS_DR_LMT_P);
        if (TDCUMAW.DR_FLG_N != '0' 
            && TDCUMAW.DR_FLG_N != '1' 
            && TDCUMAW.DR_FLG_N != '2') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CROS_FLG_OVERF);
        } else {
            if (WS_DR_LMT_P == '0' 
                && (TDCUMAW.DR_FLG_N != '0' 
                && TDCUMAW.DR_FLG_N != '2')) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CROS_DR_N_MATCH_PRD);
            }
            if (WS_DR_LMT_P == '2' 
                && TDCUMAW.DR_FLG_N != '2') {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CROS_DR_N_MATCH_PRD);
            }
        }
    }
    public void B550_UPDATE_TDTCMST_PROC() throws IOException,SQLException,Exception {
        if ((TDCUMAW.DW_MTH_N == '1' 
            || TDCUMAW.DW_MTH_N == '4') 
            && TDCUMAW.DW_MTH_O != TDCUMAW.DW_MTH_N) {
            CEP.TRC(SCCGWA, TDCUMAW.MAIN_AC);
            CEP.TRC(SCCGWA, TDCUMAW.DW_MTH_O);
            CEP.TRC(SCCGWA, TDCUMAW.DW_MTH_N);
            IBS.init(SCCGWA, CICCUST);
            CEP.TRC(SCCGWA, "1");
            CICCUST.FUNC = 'A';
            CEP.TRC(SCCGWA, "2");
            CICCUST.DATA.AGR_NO = TDCUMAW.MAIN_AC;
            CEP.TRC(SCCGWA, "3");
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICCUST.O_DATA.O_ID_TYPE);
            CEP.TRC(SCCGWA, CICCUST.O_DATA.O_ID_NO);
            IBS.init(SCCGWA, TDCACM);
            TDCACM.FUNC = 'R';
            TDCACM.AC_NO = TDCUMAW.MAIN_AC;
            TDCACM.OLD_AC_NO = TDCUMAW.MAIN_AC;
            TDCACM.CARD_PSW_OLD = TDCUMAW.DRAW_PSW;
            TDCACM.CARD_PSW_NEW = TDCUMAW.DRAW_PSW;
            TDCACM.ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
            TDCACM.ID_NO = CICCUST.O_DATA.O_ID_NO;
            TDCACM.CI_NM = TDCUMAW.AC_NM;
            S000_CALL_TDZACM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, TDCACM.AC_NO);
        }
        T000_READ_TDTCMST();
        if (pgmRtn) return;
        TDRCMST.DRAW_MTH = TDCUMAW.DW_MTH_N;
        TDRCMST.CROS_CR_FLG = TDCUMAW.CR_FLG_N;
        TDRCMST.CROS_DR_FLG = TDCUMAW.DR_FLG_N;
        TDRCMST.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRCMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRCMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_TDTCMST();
        if (pgmRtn) return;
    }
    public void B570_UPDATE_CIZSACRL_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
        CEP.TRC(SCCGWA, CICQACRL.RC.RC_CODE);
        if (CICQACRL.O_DATA.O_REL_AC_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
            CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_AC_REL);
            IBS.init(SCCGWA, CICSACRL);
            CICSACRL.DATA.AC_NO = TDCUMAW.MAIN_AC;
            CICSACRL.DATA.AC_REL = CICQACRL.O_DATA.O_AC_REL;
            CICSACRL.DATA.REL_AC_NO = TDCUMAW.GL_AC_O;
            CICSACRL.FUNC = 'D';
            CEP.TRC(SCCGWA, TDCUMAW.GL_AC_N);
            CEP.TRC(SCCGWA, CICSACRL.DATA.REL_AC_NO);
            S000_CALL_CIZSACRL();
            if (pgmRtn) return;
            IBS.init(SCCGWA, CICSACRL);
            CICSACRL.DATA.AC_NO = TDCUMAW.MAIN_AC;
            CICSACRL.DATA.AC_REL = CICQACRL.O_DATA.O_AC_REL;
            CICSACRL.DATA.REL_AC_NO = TDCUMAW.GL_AC_N;
            CICSACRL.FUNC = 'A';
            CEP.TRC(SCCGWA, TDCUMAW.GL_AC_N);
            CEP.TRC(SCCGWA, CICSACRL.DATA.REL_AC_NO);
            S000_CALL_CIZSACRL();
            if (pgmRtn) return;
        }
    }
    public void B240_WRI_NFIN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.TX_TYP_CD = "PB01";
        BPCPNHIS.INFO.AC = TDCUMAW.MAIN_AC;
        BPCPNHIS.INFO.TX_RMK = "CHENG ZHANG ZHANGHU HUAN GUAN LIAN KA HAO";
        BPCPNHIS.INFO.TX_CD = "0125130";
        BPCPNHIS.INFO.FMT_ID = "TDZUMAW";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.CI_NO = CICACCU.DATA.CI_NO;
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void R000_CHK_CI_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = TDCUMAW.GL_AC_N;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        if (!CICQACRI.O_DATA.O_CI_NO.equalsIgnoreCase(CICACCU.DATA.CI_NO)) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CI_NOT_SAME);
        }
        if ((CICQACRI.O_DATA.O_CI_TYP == '2' 
            || CICQACRI.O_DATA.O_CI_TYP == '3') 
            && CICACCU.DATA.CI_TYP == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_TYPE_M_ST);
        }
        if ((CICQACRI.O_DATA.O_CI_TYP == '1') 
            && (CICACCU.DATA.CI_TYP == '2' 
            || CICACCU.DATA.CI_TYP == '3')) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_MUST_UNIT_AC);
        }
    }
    public void R000_CHK_DD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIQBAL);
        DDCIQBAL.DATA.AC = TDCUMAW.GL_AC_N;
        DDCIQBAL.DATA.CCY = "156";
        DDCIQBAL.DATA.CCY_TYPE = '1';
        S000_CALL_DDZIQBAL();
        if (pgmRtn) return;
        if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
        JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
        if (DDCIQBAL.DATA.CCY_STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_SP_FROZEN);
        }
        if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
        JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
        if (DDCIQBAL.DATA.CCY_STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_LEG_FROZEN2);
        }
        if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
        JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
        if (DDCIQBAL.DATA.CCY_STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_HAS_INNER_HOLD);
        }
        if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
        JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
        if (DDCIQBAL.DATA.CCY_STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_HAS_SIFA_HOLD);
        }
        if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
        JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
        if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
        JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
        if (DDCIQBAL.DATA.CCY_STS_WORD.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1") 
            || DDCIQBAL.DATA.CCY_STS_WORD.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED);
        }
        if (TDCUMAW.GL_AC_N == null) TDCUMAW.GL_AC_N = "";
        JIBS_tmp_int = TDCUMAW.GL_AC_N.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCUMAW.GL_AC_N += " ";
        if (TDCUMAW.MAIN_AC == null) TDCUMAW.MAIN_AC = "";
        JIBS_tmp_int = TDCUMAW.MAIN_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCUMAW.MAIN_AC += " ";
        if (TDCUMAW.GL_AC_N == null) TDCUMAW.GL_AC_N = "";
        JIBS_tmp_int = TDCUMAW.GL_AC_N.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCUMAW.GL_AC_N += " ";
        if (!TDCUMAW.GL_AC_N.substring(0, 3).equalsIgnoreCase(TDCUMAW.MAIN_AC.substring(0, 3)) 
            && !IBS.isNumeric(TDCUMAW.GL_AC_N.substring(0, 1)) 
            && TDCUMAW.MAIN_AC.trim().length() > 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INV_AC_TYP);
        }
    }
    public void R000_WRITE_HIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.TX_TYP_CD = "PB01";
        BPCPNHIS.INFO.AC = TDCUMAW.MAIN_AC;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.CI_NO = CICACCU.DATA.CI_NO;
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void R000_CHK_SMST_GL_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.AC_NO = TDCUMAW.MAIN_AC;
        T000_STARTBR_TDTSMST();
        if (pgmRtn) return;
        T000_READNEXT_TDTSMST();
        if (pgmRtn) return;
        while (WS_SMST_FLG != 'N') {
            if (TDRSMST.INSTR_MTH == '1'
                || TDRSMST.INSTR_MTH == '6') {
                IBS.init(SCCGWA, TDRINST);
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                if (!TDRSMST.STSW.substring(52 - 1, 52 + 1 - 1).equalsIgnoreCase("1")) {
                    TDRINST.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
                    T000_READ_TDTINST();
                    if (pgmRtn) return;
                    if (TDRSMST.INSTR_MTH == '1') {
                        TDRINST.STL_AC = TDCUMAW.GL_AC_N;
                    } else {
                        TDRINST.STL_INT_AC = TDCUMAW.GL_AC_N;
                    }
                    TDRINST.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                    TDRINST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
                    TDRINST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    T000_UPDATE_TDTINST();
                    if (pgmRtn) return;
                } else {
                }
                TDRSMST.OPEN_DR_AC = TDCUMAW.GL_AC_N;
                TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
                TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
                T000_UPDATE_TDTSMST();
                if (pgmRtn) return;
            } else {
            }
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW.substring(22 - 1, 22 + 1 - 1).equalsIgnoreCase("1")) {
                IBS.init(SCCGWA, TDRCDI);
                TDRCDI.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
                T000_READ_TDTCDI();
                if (pgmRtn) return;
                if (TDRCDI.CD_AUTO_FLG == 'Y') {
                    TDRCDI.PAY_AC = TDCUMAW.GL_AC_N;
                    TDRCDI.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    TDRCDI.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
                    T000_UPDATE_TDTCDI();
                    if (pgmRtn) return;
                }
            }
            T000_READNEXT_TDTSMST();
            if (pgmRtn) return;
        }
    }
    public void R000_CHK_CHENG_AC() throws IOException,SQLException,Exception {
        if (TDCUMAW.GL_AC_O.trim().length() > 0) {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'R';
            CEP.TRC(SCCGWA, TDCUMAW.GL_AC_O);
            CICQACAC.DATA.AGR_NO = TDCUMAW.GL_AC_O;
            CICQACAC.DATA.CCY_ACAC = TDRSMST.CCY;
            CICQACAC.DATA.CR_FLG = TDRSMST.CCY_TYP;
            CEP.TRC(SCCGWA, TDRSMST.CCY);
            CEP.TRC(SCCGWA, TDRSMST.CCY_TYP);
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            IBS.init(SCCGWA, DDCIMCYY);
            DDCIMCYY.TX_TYPE = 'I';
            DDCIMCYY.DATA.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            CEP.TRC(SCCGWA, DDCIMCYY.DATA.KEY.AC);
            S000_CALL_DDZIMCYY();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDCIMCYY.DATA.STS_WORD);
            IBS.init(SCCGWA, DCCUMATP);
            if (DDCIMCYY.DATA.STS_WORD == null) DDCIMCYY.DATA.STS_WORD = "";
            JIBS_tmp_int = DDCIMCYY.DATA.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMCYY.DATA.STS_WORD += " ";
            if (DDCIMCYY.DATA.STS_WORD == null) DDCIMCYY.DATA.STS_WORD = "";
            JIBS_tmp_int = DDCIMCYY.DATA.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMCYY.DATA.STS_WORD += " ";
            if (DDCIMCYY.DATA.STS_WORD.substring(53 - 1, 53 + 1 - 1).equalsIgnoreCase("1") 
                || DDCIMCYY.DATA.STS_WORD.substring(54 - 1, 54 + 1 - 1).equalsIgnoreCase("1")) {
                DCCUMATP.IO_AREA.AC_NO = TDCUMAW.GL_AC_O;
                DCCUMATP.IO_AREA.AGR_NO = TDCUMAW.GL_AC_N;
                DCCUMATP.IO_AREA.FUNC_M = '5';
                if (DDCIMCYY.DATA.STS_WORD == null) DDCIMCYY.DATA.STS_WORD = "";
                JIBS_tmp_int = DDCIMCYY.DATA.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMCYY.DATA.STS_WORD += " ";
                if (DDCIMCYY.DATA.STS_WORD.substring(53 - 1, 53 + 1 - 1).equalsIgnoreCase("1")) {
                    DCCUMATP.IO_AREA.PROD_CDE = "9510000001";
                } else {
                    DCCUMATP.IO_AREA.PROD_CDE = "9510000002";
                }
                DCCUMATP.IO_AREA.PROC_TYP = "I";
                S000_CALL_DCZUMATP();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, DCCUMATP);
            if (DDCIMCYY.DATA.STS_WORD == null) DDCIMCYY.DATA.STS_WORD = "";
            JIBS_tmp_int = DDCIMCYY.DATA.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMCYY.DATA.STS_WORD += " ";
            if (DDCIMCYY.DATA.STS_WORD == null) DDCIMCYY.DATA.STS_WORD = "";
            JIBS_tmp_int = DDCIMCYY.DATA.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMCYY.DATA.STS_WORD += " ";
            if (DDCIMCYY.DATA.STS_WORD.substring(58 - 1, 58 + 1 - 1).equalsIgnoreCase("1") 
                || DDCIMCYY.DATA.STS_WORD.substring(59 - 1, 59 + 1 - 1).equalsIgnoreCase("1")) {
                DCCUMATP.IO_AREA.AC_NO = TDCUMAW.GL_AC_O;
                DCCUMATP.IO_AREA.AGR_NO = TDCUMAW.GL_AC_N;
                DCCUMATP.IO_AREA.FUNC_M = '5';
                if (DDCIMCYY.DATA.STS_WORD == null) DDCIMCYY.DATA.STS_WORD = "";
                JIBS_tmp_int = DDCIMCYY.DATA.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMCYY.DATA.STS_WORD += " ";
                if (DDCIMCYY.DATA.STS_WORD.substring(58 - 1, 58 + 1 - 1).equalsIgnoreCase("1")) {
                    DCCUMATP.IO_AREA.PROD_CDE = "9510000001";
                } else {
                    DCCUMATP.IO_AREA.PROD_CDE = "9510000002";
                }
                DCCUMATP.IO_AREA.PROC_TYP = "O";
                S000_CALL_DCZUMATP();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-CCY-BAL", DDCIQBAL, true);
    }
    public void S000_CALL_DCZUMATP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-M-AUTO-TD-PLAN", DCCUMATP, true);
    }
    public void S000_CALL_DDZIMCYY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-ZIMCYY-AC", DDCIMCYY);
        CEP.TRC(SCCGWA, DDCIMCYY.RC.RC_CODE);
        if (DDCIMCYY.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCIMCYY.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void T000_READ_TDTCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        TDTCMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRCMST, TDTCMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "-----TDTCMST-FOUND");
            WS_TABLE_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "-----TDTCMST-NOT-FOUND");
            WS_TABLE_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTLMT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TDTINST() throws IOException,SQLException,Exception {
        TDTINST_RD = new DBParm();
        TDTINST_RD.TableName = "TDTINST";
        TDTINST_RD.upd = true;
        IBS.READ(SCCGWA, TDRINST, TDTINST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "-----TDTCMST-FOUND");
            WS_TABLE_FLG = 'Y';
        } else {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DATE_LIVED);
        }
    }
    public void T000_READ_TDTCDI() throws IOException,SQLException,Exception {
        TDTCDI_RD = new DBParm();
        TDTCDI_RD.TableName = "TDTCDI";
        TDTCDI_RD.upd = true;
        IBS.READ(SCCGWA, TDRCDI, TDTCDI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "-----TDTCMST-FOUND");
            WS_TABLE_FLG = 'Y';
        } else {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DATE_LIVED);
        }
    }
    public void T000_READ_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.where = "AC_NO = :TDRSMST.AC_NO";
        TDTSMST_RD.fst = true;
        IBS.READ(SCCGWA, TDRSMST, this, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "-----TDTSMST-FOUND");
            WS_TABLE_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "-----TDTSMST-NOT-FOUND");
            WS_TABLE_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTLMT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_UPDATE_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.REWRITE(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "-----TDTSMST-FOUND");
            WS_TABLE_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "-----TDTSMST-NOT-FOUND");
            WS_TABLE_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTLMT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_TDTCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        IBS.REWRITE(SCCGWA, TDRCMST, TDTCMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "-----TDTCMST-FOUND");
            WS_TABLE_FLG = 'Y';
        } else {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DATE_LIVED);
        }
    }
    public void T000_UPDATE_TDTINST() throws IOException,SQLException,Exception {
        TDTINST_RD = new DBParm();
        TDTINST_RD.TableName = "TDTINST";
        IBS.REWRITE(SCCGWA, TDRINST, TDTINST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "-----TDTCMST-FOUND");
            WS_TABLE_FLG = 'Y';
        } else {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DATE_LIVED);
        }
    }
    public void T000_UPDATE_TDTCDI() throws IOException,SQLException,Exception {
        TDTCDI_RD = new DBParm();
        TDTCDI_RD.TableName = "TDTCDI";
        IBS.REWRITE(SCCGWA, TDRCDI, TDTCDI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "-----TDTCMST-FOUND");
            WS_TABLE_FLG = 'Y';
        } else {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DATE_LIVED);
        }
    }
    public void T000_STARTBR_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_BR.rp = new DBParm();
        TDTSMST_BR.rp.TableName = "TDTSMST";
        TDTSMST_BR.rp.where = "AC_NO = :TDRSMST.AC_NO";
        TDTSMST_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, TDRSMST, this, TDTSMST_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "-----TDTSMST-FOUND");
            WS_SMST_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "-----TDTSMST-NOT-FOUND");
            WS_SMST_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTSMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_TDTSMST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRSMST, this, TDTSMST_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "-----TDTSMST-FOUND");
            WS_SMST_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "-----TDTSMST-NOT-FOUND");
            WS_SMST_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTSMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_CIZSACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACR", CICSACR);
        if (CICSACR.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICSACR.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_TDZQPMP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-BASE-PRD-QRY", TDCQPMP);
        CEP.TRC(SCCGWA, TDCQPMP.RC.RC_RTNCODE);
        if (TDCQPMP.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, TDCQPMP.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
        if (CICQACRL.RC.RC_CODE != 0 
            && CICQACRL.RC.RC_CODE != 8054) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACRL.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICQACRI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_CIZSACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACRL", CICSACRL);
        if (CICSACRL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICSACRL.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_TDZMPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-MPRD-MAINT", TDCMPRD);
    }
    public void S000_CALL_TDZACM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-AC-PSW-CHECK", TDCACM);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
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
