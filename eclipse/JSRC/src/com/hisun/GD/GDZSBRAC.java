package com.hisun.GD;

import com.hisun.TD.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.DD.*;
import com.hisun.CI.*;
import com.hisun.BP.*;
import com.hisun.DC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class GDZSBRAC {
    int JIBS_tmp_int;
    GDZSBRAC_WS_OUTPUT_LST WS_OUTPUT_LST;
    String JIBS_tmp_str[] = new String[10];
    DBParm DCTIAMST_RD;
    DBParm TDTIREV_RD;
    DBParm GDTPLDR_RD;
    DBParm TDTSMST_RD;
    brParm TDTSMST_BR = new brParm();
    DBParm TDTCMST_RD;
    DBParm DDTMST_RD;
    boolean pgmRtn = false;
    String OUTPUT_FMT = "GD840";
    GDZSBRAC_WS_VARIABLES WS_VARIABLES = new GDZSBRAC_WS_VARIABLES();
    GDZSBRAC_WS_LIST WS_LIST = new GDZSBRAC_WS_LIST();
    GDZSBRAC_WS_COND_FLG WS_COND_FLG = new GDZSBRAC_WS_COND_FLG();
    GDCMSG_ERROR_MSG ERROR_MSG = new GDCMSG_ERROR_MSG();
    TDCMSG_ERROR_MSG ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    GDCRHIS GDCRHIS = new GDCRHIS();
    DDCRMST DDCRMST = new DDCRMST();
    CICACCU CICACCU = new CICACCU();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    CICQACAC CICQACAC = new CICQACAC();
    CICQACRI CICQACRI = new CICQACRI();
    GDCOBRAC GDCOBRAC = new GDCOBRAC();
    GDCOBRAC_WS_DB_VARS WS_DB_VARS = new GDCOBRAC_WS_DB_VARS();
    DDRMST DDRMST = new DDRMST();
    TDRSMST TDRSMST = new TDRSMST();
    DCRIAACR DCRIAACR = new DCRIAACR();
    GDRPLDR GDRPLDR = new GDRPLDR();
    GDRHIS GDRHIS = new GDRHIS();
    TDRIREV TDRIREV = new TDRIREV();
    DCRIAMST DCRIAMST = new DCRIAMST();
    TDRCMST TDRCMST = new TDRCMST();
    SCCGWA SCCGWA;
    GDCSBRAC GDCSBRAC;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    public void MP(SCCGWA SCCGWA, GDCSBRAC GDCSBRAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.GDCSBRAC = GDCSBRAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "GDZSBRAC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_COND_FLG);
        IBS.init(SCCGWA, WS_DB_VARS);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_ENQUIRY_CONDITION();
        if (pgmRtn) return;
        B020_TRANS_DATA_PRO();
        if (pgmRtn) return;
        B030_GET_PAGE_ROW_INF();
        if (pgmRtn) return;
        B040_GET_TOTAL_NUM();
        if (pgmRtn) return;
        B060_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_ENQUIRY_CONDITION() throws IOException,SQLException,Exception {
        if (GDCSBRAC.AC.trim().length() == 0) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.GD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, GDCSBRAC.AC);
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.AC_NO = GDCSBRAC.AC;
            T000_READ_FIRST_TDTSMST();
            if (pgmRtn) return;
            IBS.init(SCCGWA, BPCPQPRD);
            BPCPQPRD.PRDT_CODE = TDRSMST.PROD_CD;
            S000_CALL_BPZPQPRD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DCRIAMST.PRD_CODE);
            CEP.TRC(SCCGWA, BPCPQPRD.AC_TYPE);
            if (!BPCPQPRD.AC_TYPE.equalsIgnoreCase("033")) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.GD_MSTAC_NOT_GD_PROD;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_TRANS_DATA_PRO() throws IOException,SQLException,Exception {
        if (GDCSBRAC.PAGE_ROW == 0) {
            WS_VARIABLES.P_ROW = 20;
        } else {
            WS_VARIABLES.P_ROW = GDCSBRAC.PAGE_ROW;
        }
        if (GDCSBRAC.PAGE_NUM == 0) {
            GDCSBRAC.PAGE_NUM = 1;
        }
        WS_VARIABLES.L_CNT = 0;
        CEP.TRC(SCCGWA, WS_VARIABLES.P_ROW);
        CEP.TRC(SCCGWA, GDCSBRAC.PAGE_NUM);
    }
    public void B030_GET_PAGE_ROW_INF() throws IOException,SQLException,Exception {
        T000_STARTBR_TDTSMST();
        if (pgmRtn) return;
        T000_READNEXT_TDTSMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "1111111111");
        while (WS_COND_FLG.TDTSMST_FLG != 'N') {
            CEP.TRC(SCCGWA, TDRSMST.BAL);
            CEP.TRC(SCCGWA, TDRSMST.HBAL);
            CEP.TRC(SCCGWA, TDRSMST.GUAR_BAL);
            WS_VARIABLES.AVL_BAL = TDRSMST.BAL - TDRSMST.HBAL;
            WS_VARIABLES.AVL_RBAL = TDRSMST.BAL - TDRSMST.HBAL - TDRSMST.GUAR_BAL;
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (!TDRSMST.STSW.substring(2 - 1, 2 + 2 - 1).equalsIgnoreCase("00") 
                || !TDRSMST.STSW.substring(7 - 1, 7 + 2 - 1).equalsIgnoreCase("00")) {
                WS_VARIABLES.AVL_BAL = 0;
                WS_VARIABLES.AVL_RBAL = 0;
            }
            if (WS_VARIABLES.AVL_RBAL < 0) {
                WS_VARIABLES.AVL_RBAL = 0;
            }
            if (WS_VARIABLES.AVL_BAL < 0) {
                WS_VARIABLES.AVL_BAL = 0;
            }
            CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
            CEP.TRC(SCCGWA, TDRSMST.AC_NO);
            if (WS_COND_FLG.TDTSMST_FLG == 'Y') {
                if (GDCSBRAC.FLG == 'Y') {
                    CEP.TRC(SCCGWA, "SBRAC-FLG   Y");
                    WS_VARIABLES.L_CNT += 1;
                    CEP.TRC(SCCGWA, WS_VARIABLES.L_CNT);
                } else {
                    CEP.TRC(SCCGWA, "SBRAC-FLG   N");
                    CEP.TRC(SCCGWA, WS_VARIABLES.AVL_BAL);
                    CEP.TRC(SCCGWA, TDRSMST.ACO_STS);
                    if (TDRSMST.ACO_STS == '0') {
                        WS_VARIABLES.L_CNT += 1;
                        CEP.TRC(SCCGWA, WS_VARIABLES.L_CNT);
                    }
                }
            }
            T000_READNEXT_TDTSMST();
            if (pgmRtn) return;
        }
        T000_ENDBR_TDTSMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_VARIABLES.L_CNT);
        if (WS_VARIABLES.L_CNT != 0) {
            IBS.init(SCCGWA, WS_LIST.WS_PAGE_INF);
            WS_LIST.WS_PAGE_INF.TOTAL_NUM = WS_VARIABLES.L_CNT;
            WS_VARIABLES.L_ROW = WS_LIST.WS_PAGE_INF.TOTAL_NUM % WS_VARIABLES.P_ROW;
            WS_VARIABLES.T_PAGE = (int) ((WS_LIST.WS_PAGE_INF.TOTAL_NUM - WS_VARIABLES.L_ROW) / WS_VARIABLES.P_ROW);
            if (WS_VARIABLES.L_ROW == 0) {
                CEP.TRC(SCCGWA, "000000");
                WS_LIST.WS_PAGE_INF.TOTAL_PAGE = WS_VARIABLES.T_PAGE;
            } else {
                CEP.TRC(SCCGWA, "XXXXXX");
                WS_LIST.WS_PAGE_INF.TOTAL_PAGE = WS_VARIABLES.T_PAGE + 1;
                CEP.TRC(SCCGWA, WS_LIST.WS_PAGE_INF.TOTAL_PAGE);
                CEP.TRC(SCCGWA, WS_VARIABLES.T_PAGE);
            }
            if (GDCSBRAC.PAGE_NUM >= WS_LIST.WS_PAGE_INF.TOTAL_PAGE) {
                CEP.TRC(SCCGWA, "PAGE NUM LARGE");
                WS_LIST.WS_PAGE_INF.CURR_PAGE = WS_LIST.WS_PAGE_INF.TOTAL_PAGE;
                WS_LIST.WS_PAGE_INF.LAST_PAGE = 'Y';
                CEP.TRC(SCCGWA, WS_VARIABLES.L_ROW);
                if (WS_VARIABLES.L_ROW == 0) {
                    WS_LIST.WS_PAGE_INF.PAGE_ROW = WS_VARIABLES.P_ROW;
                    WS_OUTPUT_LST = new GDZSBRAC_WS_OUTPUT_LST();
                    WS_LIST.WS_OUTPUT_LST.add(WS_OUTPUT_LST);
                    CEP.TRC(SCCGWA, WS_VARIABLES.P_ROW);
                } else {
                    WS_LIST.WS_PAGE_INF.PAGE_ROW = WS_VARIABLES.L_ROW;
                    WS_OUTPUT_LST = new GDZSBRAC_WS_OUTPUT_LST();
                    WS_LIST.WS_OUTPUT_LST.add(WS_OUTPUT_LST);
                    CEP.TRC(SCCGWA, WS_VARIABLES.L_ROW);
                }
            } else {
                CEP.TRC(SCCGWA, "<<<<<<");
                WS_LIST.WS_PAGE_INF.CURR_PAGE = GDCSBRAC.PAGE_NUM;
                WS_LIST.WS_PAGE_INF.LAST_PAGE = 'N';
                WS_LIST.WS_PAGE_INF.PAGE_ROW = WS_VARIABLES.P_ROW;
                WS_OUTPUT_LST = new GDZSBRAC_WS_OUTPUT_LST();
                WS_LIST.WS_OUTPUT_LST.add(WS_OUTPUT_LST);
            }
            WS_VARIABLES.STR_NUM = ( WS_LIST.WS_PAGE_INF.CURR_PAGE - 1 ) * WS_VARIABLES.P_ROW;
            CEP.TRC(SCCGWA, WS_VARIABLES.STR_NUM);
            WS_VARIABLES.END_NUM = WS_LIST.WS_PAGE_INF.CURR_PAGE * WS_VARIABLES.P_ROW;
            CEP.TRC(SCCGWA, WS_VARIABLES.END_NUM);
            CEP.TRC(SCCGWA, "PAGE INFO:");
            CEP.TRC(SCCGWA, WS_LIST.WS_PAGE_INF.TOTAL_PAGE);
            CEP.TRC(SCCGWA, WS_LIST.WS_PAGE_INF.TOTAL_NUM);
            CEP.TRC(SCCGWA, WS_LIST.WS_PAGE_INF.CURR_PAGE);
            CEP.TRC(SCCGWA, WS_LIST.WS_PAGE_INF.LAST_PAGE);
            CEP.TRC(SCCGWA, WS_LIST.WS_PAGE_INF.PAGE_ROW);
        } else {
            WS_LIST.WS_PAGE_INF.TOTAL_PAGE = 0;
            WS_LIST.WS_PAGE_INF.TOTAL_NUM = 0;
            WS_LIST.WS_PAGE_INF.CURR_PAGE = 0;
            WS_LIST.WS_PAGE_INF.LAST_PAGE = 'Y';
            WS_LIST.WS_PAGE_INF.PAGE_ROW = 0;
            WS_OUTPUT_LST = new GDZSBRAC_WS_OUTPUT_LST();
            WS_LIST.WS_OUTPUT_LST.add(WS_OUTPUT_LST);
        }
    }
    public void B040_GET_TOTAL_NUM() throws IOException,SQLException,Exception {
        T000_STARTBR_TDTSMST();
        if (pgmRtn) return;
        T000_READNEXT_TDTSMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "222222222");
        while (WS_COND_FLG.TDTSMST_FLG != 'N') {
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = TDRSMST.AC_NO;
            CICACCU.DATA.ENTY_TYP = '1';
            S000_CALL_CIZACCU();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICACCU.DATA.CI_CNM);
            B030_10_GET_TDTSMST();
            if (pgmRtn) return;
            B030_20_GET_TDTIREV();
            if (pgmRtn) return;
            B030_40_GET_ACSEQ();
            if (pgmRtn) return;
            B030_30_GET_GDTPLDR();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_VARIABLES.STR_NUM);
            CEP.TRC(SCCGWA, WS_VARIABLES.END_NUM);
            if (WS_COND_FLG.TDTSMST_FLG == 'Y') {
                if (GDCSBRAC.FLG == 'Y') {
                    WS_VARIABLES.I += 1;
                    if (WS_VARIABLES.I > WS_VARIABLES.STR_NUM 
                        && WS_VARIABLES.I <= WS_VARIABLES.END_NUM) {
                        WS_VARIABLES.CNT += 1;
                        B050_OUTPUT_LIST();
                        if (pgmRtn) return;
                    }
                } else {
                    WS_VARIABLES.I += 1;
                    if (WS_VARIABLES.I > WS_VARIABLES.STR_NUM 
                        && WS_VARIABLES.I <= WS_VARIABLES.END_NUM) {
                        WS_VARIABLES.CNT += 1;
                        B050_OUTPUT_LIST();
                        if (pgmRtn) return;
                    }
                }
            }
            CEP.TRC(SCCGWA, WS_VARIABLES.I);
            CEP.TRC(SCCGWA, WS_VARIABLES.CNT);
            T000_READNEXT_TDTSMST();
            if (pgmRtn) return;
        }
        T000_ENDBR_TDTSMST();
        if (pgmRtn) return;
    }
    public void B050_OUTPUT_LIST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "LIST INT1:");
        IBS.init(SCCGWA, WS_OUTPUT_LST);
        WS_OUTPUT_LST.DD_AC = TDRSMST.AC_NO;
        CEP.TRC(SCCGWA, TDRSMST.AC_NO);
        WS_OUTPUT_LST.AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ);
        WS_OUTPUT_LST.AC = TDRSMST.KEY.ACO_AC;
        CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
        WS_OUTPUT_LST.CI_CNM = CICACCU.DATA.AC_CNM;
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_CNM);
        WS_OUTPUT_LST.CCY = TDRSMST.CCY;
        CEP.TRC(SCCGWA, TDRSMST.CCY);
        WS_OUTPUT_LST.CCY_TYP = TDRSMST.CCY_TYP;
        CEP.TRC(SCCGWA, TDRSMST.CCY_TYP);
        CEP.TRC(SCCGWA, "XXXXXXX");
        CEP.TRC(SCCGWA, TDRSMST.OPEN_DATE);
        WS_OUTPUT_LST.OPEN_DT = TDRSMST.OPEN_DATE;
        CEP.TRC(SCCGWA, TDRSMST.OPEN_DATE);
        WS_OUTPUT_LST.MT_DATE = TDRSMST.EXP_DATE;
        CEP.TRC(SCCGWA, TDRSMST.EXP_DATE);
        WS_OUTPUT_LST.BAL1 = TDRSMST.BAL;
        CEP.TRC(SCCGWA, TDRSMST.BAL);
        WS_OUTPUT_LST.BAL2 = WS_VARIABLES.AVL_BAL;
        CEP.TRC(SCCGWA, WS_VARIABLES.AVL_BAL);
        WS_OUTPUT_LST.AMT = TDRSMST.GUAR_BAL;
        CEP.TRC(SCCGWA, TDRSMST.GUAR_BAL);
        WS_OUTPUT_LST.NUM_9 = (int) WS_DB_VARS.TT_CNT;
        CEP.TRC(SCCGWA, WS_DB_VARS.TT_CNT);
        WS_OUTPUT_LST.AMT1 = WS_VARIABLES.AVL_RBAL;
        CEP.TRC(SCCGWA, WS_VARIABLES.AVL_RBAL);
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        WS_LIST.WS_OUTPUT_LST.get(WS_VARIABLES.CNT-1).SMR = "BROWSE" + TDRSMST.STSW;
        CEP.TRC(SCCGWA, "111111");
        WS_OUTPUT_LST.AC_BR = TDRSMST.OWNER_BR;
        WS_OUTPUT_LST.DOM_BR = TDRSMST.OWNER_BR;
        WS_OUTPUT_LST.BOOK_BR = TDRSMST.OWNER_BR;
        CEP.TRC(SCCGWA, TDRSMST.OWNER_BR);
        WS_OUTPUT_LST.EFF_DT = TDRSMST.VAL_DATE;
        CEP.TRC(SCCGWA, TDRSMST.VAL_DATE);
        WS_OUTPUT_LST.TERM = TDRSMST.TERM;
        CEP.TRC(SCCGWA, TDRSMST.TERM);
        CEP.TRC(SCCGWA, TDRIREV.CON_RATE);
        WS_OUTPUT_LST.RATE = TDRIREV.CON_RATE;
        CEP.TRC(SCCGWA, TDRIREV.CON_RATE);
        WS_OUTPUT_LST.RLT = TDRSMST.INSTR_MTH;
        CEP.TRC(SCCGWA, TDRSMST.INSTR_MTH);
        WS_OUTPUT_LST.INTF = '1';
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = TDRSMST.AC_NO;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("TD")) {
            IBS.init(SCCGWA, TDRCMST);
            TDRCMST.KEY.AC_NO = GDCSBRAC.AC;
            T000_READ_TDTCMST();
            if (pgmRtn) return;
            WS_OUTPUT_LST.BUS_KNB = TDRCMST.REF_TYP;
            WS_OUTPUT_LST.RAT_MTH = TDRCMST.INT_METH;
        }
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = GDCSBRAC.AC;
            T000_READ_DDTMST();
            if (pgmRtn) return;
            WS_OUTPUT_LST.BUS_KNB = DDRMST.YW_TYP;
        }
    }
    public void B060_OUTPUT_FMT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "LIST INT2:");
        IBS.init(SCCGWA, GDCOBRAC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_LIST);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], GDCOBRAC);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = OUTPUT_FMT;
        SCCFMT.DATA_PTR = GDCOBRAC;
        SCCFMT.DATA_LEN = 77720;
        CEP.TRC(SCCGWA, SCCFMT.DATA_LEN);
        CEP.TRC(SCCGWA, WS_LIST);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B030_10_GET_TDTSMST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRSMST.BAL);
        CEP.TRC(SCCGWA, TDRSMST.HBAL);
        CEP.TRC(SCCGWA, TDRSMST.GUAR_BAL);
        WS_VARIABLES.AVL_BAL = TDRSMST.BAL - TDRSMST.HBAL;
        WS_VARIABLES.AVL_RBAL = TDRSMST.BAL - TDRSMST.HBAL - TDRSMST.GUAR_BAL;
        CEP.TRC(SCCGWA, WS_VARIABLES.AVL_BAL);
        CEP.TRC(SCCGWA, WS_VARIABLES.AVL_RBAL);
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (!TDRSMST.STSW.substring(2 - 1, 2 + 2 - 1).equalsIgnoreCase("00") 
            || !TDRSMST.STSW.substring(7 - 1, 7 + 2 - 1).equalsIgnoreCase("00")) {
            WS_VARIABLES.AVL_BAL = 0;
            WS_VARIABLES.AVL_RBAL = 0;
        }
        if (WS_VARIABLES.AVL_BAL < 0) {
            WS_VARIABLES.AVL_BAL = 0;
        }
        if (WS_VARIABLES.AVL_RBAL < 0) {
            WS_VARIABLES.AVL_RBAL = 0;
        }
        CEP.TRC(SCCGWA, WS_VARIABLES.AVL_BAL);
        CEP.TRC(SCCGWA, WS_VARIABLES.AVL_RBAL);
    }
    public void B030_20_GET_TDTIREV() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRIREV);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        T000_START_FIRST_TDTIREV();
        if (pgmRtn) return;
    }
    public void B030_30_GET_GDTPLDR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRPLDR);
        GDRPLDR.KEY.AC = GDCSBRAC.AC;
        GDRPLDR.KEY.AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
        GDRPLDR.RELAT_STS = 'N';
        T000_GROUP_GDTPLDR();
        if (pgmRtn) return;
    }
    public void B030_40_GET_ACSEQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'A';
        CICQACAC.DATA.ACAC_NO = TDRSMST.KEY.ACO_AC;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ);
    }
    public void T000_READ_DCTIAMST() throws IOException,SQLException,Exception {
        DCTIAMST_RD = new DBParm();
        DCTIAMST_RD.TableName = "DCTIAMST";
        DCTIAMST_RD.where = "VIA_AC = :DCRIAMST.KEY.VIA_AC";
        IBS.READ(SCCGWA, DCRIAMST, this, DCTIAMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "TD MAIN AC NOT FOUND");
            WS_VARIABLES.ERR_MSG = ERROR_MSG.GD_AC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_START_FIRST_TDTIREV() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRIREV);
        TDRIREV.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
        TDRIREV.END_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRIREV.KEY.STR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDTIREV_RD = new DBParm();
        TDTIREV_RD.TableName = "TDTIREV";
        TDTIREV_RD.where = "ACO_AC = :TDRIREV.KEY.ACO_AC "
            + "AND STR_DATE <= :TDRIREV.KEY.STR_DATE "
            + "AND END_DATE >= :TDRIREV.END_DATE";
        TDTIREV_RD.fst = true;
        IBS.READ(SCCGWA, TDRIREV, this, TDTIREV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.TD_IREV_NOFND;
        }
    }
    public void T000_GROUP_GDTPLDR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDRPLDR.KEY.AC);
        CEP.TRC(SCCGWA, GDRPLDR.KEY.AC_SEQ);
        CEP.TRC(SCCGWA, GDRPLDR.RELAT_STS);
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.set = "WS-TT-CNT=COUNT(*)";
        GDTPLDR_RD.where = "AC = :GDRPLDR.KEY.AC "
            + "AND AC_SEQ = :GDRPLDR.KEY.AC_SEQ "
            + "AND RELAT_STS = :GDRPLDR.RELAT_STS";
        IBS.GROUP(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, WS_DB_VARS.TT_CNT);
        CEP.TRC(SCCGWA, WS_DB_VARS.TT_CNT);
    }
    public void T000_READ_TDTIREV() throws IOException,SQLException,Exception {
        TDTIREV_RD = new DBParm();
        TDTIREV_RD.TableName = "TDTIREV";
        IBS.READ(SCCGWA, TDRIREV, TDTIREV_RD);
    }
    public void T000_READ_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TDTSMST_FLG = 'N';
        } else {
            WS_COND_FLG.TDTSMST_FLG = 'Y';
        }
    }
    public void T000_READ_FIRST_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.where = "AC_NO = :TDRSMST.AC_NO "
            + "AND ACO_STS = '0'";
        TDTSMST_RD.fst = true;
        IBS.READ(SCCGWA, TDRSMST, this, TDTSMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TDTSMST_FLG = 'N';
        } else {
            WS_COND_FLG.TDTSMST_FLG = 'Y';
        }
    }
    public void T000_STARTBR_TDTSMST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.AC_NO = GDCSBRAC.AC;
        CEP.TRC(SCCGWA, TDRSMST.AC_NO);
        TDTSMST_BR.rp = new DBParm();
        TDTSMST_BR.rp.TableName = "TDTSMST";
        TDTSMST_BR.rp.where = "AC_NO = :TDRSMST.AC_NO "
            + "AND ACO_STS = '0'";
        TDTSMST_BR.rp.order = "VAL_DATE DESC";
        IBS.STARTBR(SCCGWA, TDRSMST, this, TDTSMST_BR);
    }
    public void T000_READNEXT_TDTSMST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRSMST, this, TDTSMST_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TDTSMST_FLG = 'N';
        } else {
            WS_COND_FLG.TDTSMST_FLG = 'Y';
        }
    }
    public void T000_ENDBR_TDTSMST() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTSMST_BR);
    }
    public void T000_READ_TDTCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        IBS.READ(SCCGWA, TDRCMST, TDTCMST_RD);
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
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
