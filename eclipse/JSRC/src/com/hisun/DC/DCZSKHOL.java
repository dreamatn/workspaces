package com.hisun.DC;

import com.hisun.DD.*;
import com.hisun.TD.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.BP.*;
import com.hisun.CI.*;
import com.hisun.DB.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSKHOL {
    int JIBS_tmp_int;
    DBParm DDTHLD_RD;
    DBParm DDTMST_RD;
    DBParm TDTSMST_RD;
    DBParm DDTCCY_RD;
    DBParm DUPKEY_RD;
    DBParm DDTHLDR_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DC774";
    String K_HIS_REMARKS = "KEEP DEPOSIT HOLD";
    String CPN_SCSSCKDT = "SCSSCKDT";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_ERR_MSG = " ";
    String WS_ACAC = " ";
    String WS_HLD_NO = " ";
    String WS_DOWN_HLD_NO = " ";
    String WS_UP_HLD_NO = " ";
    int WS_EFF_DATE = 0;
    int WS_EXP_DATE = 0;
    double WS_HLD_AMT = 0;
    char WS_HLD_STS = ' ';
    char WS_HLD_TYP = ' ';
    char WS_SPR_TYP = ' ';
    double WS_HLD_AMT_NEW = 0;
    double WS_CURR_BAL = 0;
    double WS_AVL_AMT = 0;
    String WS_CCY = " ";
    char WS_CCY_TYP = ' ';
    double WS_ACC_AMT = 0;
    double WS_ACC_HLD_AMT = 0;
    double WS_CURR_AVL_AMT = 0;
    double WS_CURR_HLD_AMT = 0;
    char WS_CIRCLE_FLG = ' ';
    char WS_AC_HOLD_FLG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DDRHLD DDRHLD = new DDRHLD();
    DDRHLDR DDRHLDR = new DDRHLDR();
    DCCOKHOL DCCOKHOL = new DCCOKHOL();
    SCCBINF SCCBINF = new SCCBINF();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCCKDT SCCCKDT = new SCCCKDT();
    DDRMST DDRMST = new DDRMST();
    TDRSMST TDRSMST = new TDRSMST();
    DCRIAMST DCRIAMST = new DCRIAMST();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DDRCCY DDRCCY = new DDRCCY();
    DCRIACCY DCRIACCY = new DCRIACCY();
    DCCUIQMC DCCUIQMC = new DCCUIQMC();
    DCRACLNK DCRACLNK = new DCRACLNK();
    CICQACAC CICQACAC = new CICQACAC();
    SCCCLDT SCCCLDT = new SCCCLDT();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    DCCSKHOL DCCSKHOL;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DCCSKHOL DCCSKHOL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSKHOL = DCCSKHOL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSKHOL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_PROC();
        if (pgmRtn) return;
        B020_INQ_HLD_INF_PROC();
        if (pgmRtn) return;
        B021_INQ_ACAC_PROC();
        if (pgmRtn) return;
        B030_UPDATE_DDTHLD();
        if (pgmRtn) return;
        B035_UPDATE_DDTCCY_PROC();
        if (pgmRtn) return;
        B035_UPDATE_TDTSMST_PROC();
        if (pgmRtn) return;
        B040_WRITE_DDTHLDR();
        if (pgmRtn) return;
        B045_NON_FIN_HIS_PROC();
        if (pgmRtn) return;
        B050_OUTPUT_DATA_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSKHOL.DATA.HLD_NO);
        if (DCCSKHOL.DATA.HLD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLDNO_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_INQ_HLD_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHLD);
        DDRHLD.KEY.HLD_NO = DCCSKHOL.DATA.HLD_NO;
        T000_READ_DDTHLD();
        if (pgmRtn) return;
        WS_ACAC = DDRHLD.AC;
        WS_HLD_NO = DDRHLD.KEY.HLD_NO;
        WS_EFF_DATE = DDRHLD.EFF_DATE;
        WS_EXP_DATE = DDRHLD.EXP_DATE;
        WS_HLD_AMT = DDRHLD.HLD_AMT;
        WS_HLD_STS = DDRHLD.HLD_STS;
        WS_HLD_TYP = DDRHLD.HLD_TYP;
        WS_SPR_TYP = DDRHLD.SPR_BR_TYP;
        WS_CCY = DDRHLD.CCY;
        WS_CCY_TYP = DDRHLD.CCY_TYPE;
        WS_DOWN_HLD_NO = DDRHLD.DOWN_HLD_NO;
        WS_UP_HLD_NO = DDRHLD.UP_HLD_NO;
        CEP.TRC(SCCGWA, WS_EXP_DATE);
        CEP.TRC(SCCGWA, WS_HLD_AMT);
        CEP.TRC(SCCGWA, DCCSKHOL.DATA.NEW_DT);
        CEP.TRC(SCCGWA, DCCSKHOL.DATA.NEW_AMT);
        if (DDRHLD.HLD_STS == 'C') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_AC_HAS_REL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRHLD.SPR_BR_TYP == '1' 
            && DCCSKHOL.DATA.CHG_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_WRIT_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRHLD.SPR_BR_TYP == '1' 
            && DCCSKHOL.DATA.SPR_NM.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_SPR_NM_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRHLD.SPR_BR_TYP == '1' 
            && DCCSKHOL.DATA.LAW_NM1.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_LAW_NM_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRHLD.SPR_BR_TYP == '1' 
            && DCCSKHOL.DATA.LAW_NO1.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_LAW_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSKHOL.DATA.NEW_DT == 0 
            && DCCSKHOL.DATA.NEW_AMT == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AMT_DT_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSKHOL.DATA.NEW_DT != 0 
            && DCCSKHOL.DATA.NEW_AMT != 0 
            && DCCSKHOL.DATA.NEW_DT == WS_EXP_DATE 
            && DCCSKHOL.DATA.NEW_AMT == WS_HLD_AMT) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AMT_DT_NOT_SAME;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSKHOL.DATA.NEW_DT != 0 
            && WS_EXP_DATE > DCCSKHOL.DATA.NEW_DT) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NEW_EXP_DT_VALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSKHOL.DATA.NEW_DT != 0 
            && WS_EXP_DATE == DCCSKHOL.DATA.NEW_DT 
            && DDRHLD.HLD_TYP == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_DATA_NO_CHANGE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSKHOL.DATA.NEW_DT != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = DCCSKHOL.DATA.NEW_DT;
            SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
            SCSSCKDT1.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_EXP_DT_INVALID;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (SCCGWA.COMM_AREA.AC_DATE > WS_EXP_DATE) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_NEW_NOT_LESS_EXPDATE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSKHOL.DATA.NEW_DT != 0 
            && DDRHLD.SPR_BR_TYP == '1') {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = WS_EXP_DATE;
            SCCCLDT.MTHS = 12;
            SCSSCLDT SCSSCLDT2 = new SCSSCLDT();
            SCSSCLDT2.MP(SCCGWA, SCCCLDT);
            CEP.TRC(SCCGWA, SCCCLDT.DATE2);
            if (DCCSKHOL.DATA.NEW_DT > SCCCLDT.DATE2) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_TERM_OVER_ONE_YEAR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCCSKHOL.DATA.NEW_AMT != 0 
            && DCCSKHOL.DATA.NEW_AMT > WS_HLD_AMT) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_NEW_AMT_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DCCSKHOL.DATA.CHG_BR);
        if (DCCSKHOL.DATA.CHG_BR == 0) {
            DCCSKHOL.DATA.CHG_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        }
        CEP.TRC(SCCGWA, DDRHLD.SPR_BR);
        CEP.TRC(SCCGWA, DCCSKHOL.DATA.CHG_BR);
        CEP.TRC(SCCGWA, DDRHLD.SPR_BR);
        if (!DDRHLD.SPR_CHNL.equalsIgnoreCase("033100")) {
            if (DDRHLD.SPR_BR != 0 
                && DCCSKHOL.DATA.CHG_BR != DDRHLD.SPR_BR) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_KHLD_BR_M_HLD_BR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DDRHLD.HLD_TYP == '1' 
            && DCCSKHOL.DATA.NEW_AMT != 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_TOT_BAL_HLD;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B021_INQ_ACAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'A';
        CICQACAC.DATA.ACAC_NO = DDRHLD.AC;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_STS);
        if (CICQACAC.O_DATA.O_ACR_DATA.O_STS == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC);
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
            T000_READ_DDTMST();
            if (pgmRtn) return;
            if (DDRMST.AC_STS == 'C') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("TD")) {
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.KEY.ACO_AC = DDRHLD.AC;
            T000_READ_TDTSMST();
            if (pgmRtn) return;
            if (TDRSMST.ACO_STS == 'C' 
                || TDRSMST.ACO_STS == 'R' 
                || TDRSMST.ACO_STS == '1' 
                || TDRSMST.ACO_STS == '2') {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDRCCY);
            DDRCCY.KEY.AC = DDRHLD.AC;
            T000_READ_DDTCCY_UPDATE();
            if (pgmRtn) return;
        }
    }
    public void B030_UPDATE_DDTHLD() throws IOException,SQLException,Exception {
        DDRHLD.LST_HLD_DT = WS_EXP_DATE;
        if (DCCSKHOL.DATA.NEW_AMT != 0) {
            DDRHLD.HLD_AMT = DCCSKHOL.DATA.NEW_AMT;
        }
        if (DCCSKHOL.DATA.NEW_DT != 0) {
            DDRHLD.EXP_DATE = DCCSKHOL.DATA.NEW_DT;
        }
        DDRHLD.SPR_CHNL = SCCGWA.COMM_AREA.REQ_SYSTEM;
        DDRHLD.SPR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        if (DDRHLD.SPR_BR_TYP == '1') {
            DDRHLD.HLD_BR_NM = DCCSKHOL.DATA.SPR_NM;
            DDRHLD.HLD_WRIT_NO = DCCSKHOL.DATA.CHG_NO;
            DDRHLD.LAW_OFF_NM1 = DCCSKHOL.DATA.LAW_NM1;
            DDRHLD.LAW_ID_NO1 = DCCSKHOL.DATA.LAW_NO1;
            DDRHLD.LAW_OFF_NM2 = DCCSKHOL.DATA.LAW_NM2;
            DDRHLD.LAW_ID_NO2 = DCCSKHOL.DATA.LAW_NO2;
        }
        DDRHLD.HLD_RSN = DCCSKHOL.DATA.RSN;
        DDRHLD.REMARK = DCCSKHOL.DATA.RMK;
        DDRHLD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRHLD.UPDTBL_TIME = SCCGWA.COMM_AREA.TR_TIME;
        DDRHLD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DDTHLD();
        if (pgmRtn) return;
        WS_HLD_AMT_NEW = DDRHLD.HLD_AMT;
    }
    public void B035_UPDATE_DDTCCY_PROC() throws IOException,SQLException,Exception {
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
            if (WS_HLD_STS != 'W') {
                DDRCCY.HOLD_BAL = DDRCCY.HOLD_BAL - WS_HLD_AMT + DCCSKHOL.DATA.NEW_AMT;
                DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                T000_REWRITE_DDTCCY();
                if (pgmRtn) return;
            }
            WS_CURR_BAL = DDRCCY.CURR_BAL;
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            CEP.TRC(SCCGWA, DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1));
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
                || DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1") 
                || DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1") 
                || DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
                WS_AVL_AMT = 0;
            } else {
                WS_AVL_AMT = DDRCCY.CURR_BAL + DDRCCY.CCAL_TOT_BAL - DDRCCY.HOLD_BAL - DDRCCY.MARGIN_BAL;
            }
            CEP.TRC(SCCGWA, WS_AVL_AMT);
            WS_CURR_AVL_AMT = DDRCCY.CURR_BAL + DDRCCY.CCAL_TOT_BAL - DDRCCY.MARGIN_BAL;
            if (WS_HLD_STS == 'W') {
                WS_ACC_HLD_AMT = 0;
            } else {
                IBS.init(SCCGWA, DDRHLD);
                WS_CIRCLE_FLG = ' ';
                DDRHLD.AC = WS_ACAC;
                T000_READ_DDTHLD_FIRST();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, DDRHLD.KEY.HLD_NO);
                while (WS_CIRCLE_FLG != 'Y') {
                    if (DDRHLD.HLD_TYP == '1') {
                        WS_CIRCLE_FLG = 'Y';
                        WS_AC_HOLD_FLG = 'Y';
                    } else {
                        if (DDRHLD.KEY.HLD_NO.equalsIgnoreCase(WS_HLD_NO)) {
                            WS_CIRCLE_FLG = 'Y';
                        } else {
                            if (!DDRHLD.DOWN_HLD_NO.equalsIgnoreCase(WS_HLD_NO)) {
                                WS_CURR_HLD_AMT = WS_CURR_HLD_AMT + DDRHLD.HLD_AMT;
                            } else {
                                WS_CIRCLE_FLG = 'Y';
                                WS_CURR_HLD_AMT = WS_CURR_HLD_AMT + DDRHLD.HLD_AMT;
                            }
                            DDRHLD.KEY.HLD_NO = DDRHLD.DOWN_HLD_NO;
                            CEP.TRC(SCCGWA, DDRHLD.KEY.HLD_NO);
                            T000_READ_DDTHLD();
                            if (pgmRtn) return;
                        }
                    }
                }
                if (WS_AC_HOLD_FLG == 'Y') {
                    WS_ACC_HLD_AMT = 0;
                } else {
                    WS_ACC_HLD_AMT = WS_CURR_AVL_AMT - WS_CURR_HLD_AMT;
                    CEP.TRC(SCCGWA, WS_ACC_HLD_AMT);
                    if (WS_ACC_HLD_AMT < 0) {
                        WS_ACC_HLD_AMT = 0;
                    }
                    CEP.TRC(SCCGWA, WS_ACC_HLD_AMT);
                    CEP.TRC(SCCGWA, WS_HLD_AMT_NEW);
                    if (WS_ACC_HLD_AMT > WS_HLD_AMT_NEW) {
                        WS_ACC_HLD_AMT = WS_HLD_AMT_NEW;
                    }
                }
            }
            CEP.TRC(SCCGWA, WS_ACC_HLD_AMT);
        }
    }
    public void B035_UPDATE_TDTSMST_PROC() throws IOException,SQLException,Exception {
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("TD")) {
            if (WS_HLD_STS != 'W') {
                TDRSMST.HBAL = TDRSMST.HBAL - WS_HLD_AMT + DCCSKHOL.DATA.NEW_AMT;
                CEP.TRC(SCCGWA, TDRSMST.HBAL);
                TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
                TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
                TDRSMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
                T000_REWRITE_TDTSMST();
                if (pgmRtn) return;
            }
            WS_CURR_BAL = TDRSMST.BAL;
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
                || TDRSMST.STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
                WS_AVL_AMT = 0;
            } else {
                WS_AVL_AMT = TDRSMST.BAL - TDRSMST.HBAL - TDRSMST.GUAR_BAL;
            }
            CEP.TRC(SCCGWA, WS_AVL_AMT);
            WS_CURR_AVL_AMT = TDRSMST.BAL - TDRSMST.GUAR_BAL;
            CEP.TRC(SCCGWA, TDRSMST.BAL);
            CEP.TRC(SCCGWA, TDRSMST.GUAR_BAL);
            CEP.TRC(SCCGWA, WS_CURR_AVL_AMT);
            if (WS_HLD_STS == 'W') {
                WS_ACC_HLD_AMT = 0;
            } else {
                IBS.init(SCCGWA, DDRHLD);
                WS_CIRCLE_FLG = ' ';
                DDRHLD.AC = WS_ACAC;
                T000_READ_DDTHLD_FIRST();
                if (pgmRtn) return;
                while (WS_CIRCLE_FLG != 'Y') {
                    if (DDRHLD.HLD_TYP == '1') {
                        WS_CIRCLE_FLG = 'Y';
                        WS_AC_HOLD_FLG = 'Y';
                    } else {
                        CEP.TRC(SCCGWA, DDRHLD.KEY.HLD_NO);
                        if (DDRHLD.KEY.HLD_NO.equalsIgnoreCase(WS_HLD_NO)) {
                            WS_CIRCLE_FLG = 'Y';
                        } else {
                            CEP.TRC(SCCGWA, DDRHLD.DOWN_HLD_NO);
                            if (!DDRHLD.DOWN_HLD_NO.equalsIgnoreCase(WS_HLD_NO)) {
                                WS_CURR_HLD_AMT = WS_CURR_HLD_AMT + DDRHLD.HLD_AMT;
                            } else {
                                WS_CIRCLE_FLG = 'Y';
                                WS_CURR_HLD_AMT = WS_CURR_HLD_AMT + DDRHLD.HLD_AMT;
                            }
                            DDRHLD.KEY.HLD_NO = DDRHLD.DOWN_HLD_NO;
                            CEP.TRC(SCCGWA, DDRHLD.KEY.HLD_NO);
                            T000_READ_DDTHLD();
                            if (pgmRtn) return;
                            CEP.TRC(SCCGWA, WS_CURR_HLD_AMT);
                        }
                    }
                }
                CEP.TRC(SCCGWA, WS_CURR_HLD_AMT);
                if (WS_AC_HOLD_FLG == 'Y') {
                    WS_ACC_HLD_AMT = 0;
                } else {
                    WS_ACC_HLD_AMT = WS_CURR_AVL_AMT - WS_CURR_HLD_AMT;
                    CEP.TRC(SCCGWA, WS_ACC_HLD_AMT);
                    if (WS_ACC_HLD_AMT < 0) {
                        WS_ACC_HLD_AMT = 0;
                    }
                    CEP.TRC(SCCGWA, WS_ACC_HLD_AMT);
                    CEP.TRC(SCCGWA, WS_HLD_AMT_NEW);
                    if (WS_ACC_HLD_AMT > WS_HLD_AMT_NEW) {
                        WS_ACC_HLD_AMT = WS_HLD_AMT_NEW;
                    }
                }
            }
        }
    }
    public void B040_WRITE_DDTHLDR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHLDR);
        DDRHLDR.KEY.HLD_NO = DCCSKHOL.DATA.HLD_NO;
        DDRHLDR.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRHLDR.KEY.TR_JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        DDRHLDR.AC = DDRHLD.AC;
        DDRHLDR.HLD_TYP = '7';
        DDRHLDR.BEF_TR_AMT = WS_ACC_HLD_AMT;
        DDRHLDR.TR_AMT = DCCSKHOL.DATA.NEW_AMT;
        DDRHLDR.CHG_RSN = DCCSKHOL.DATA.RSN;
        DDRHLDR.SPR_BR_TYP = DDRHLD.SPR_BR_TYP;
        DDRHLDR.SPR_BR_NM = DCCSKHOL.DATA.SPR_NM;
        DDRHLDR.CHG_WRIT_NO = DCCSKHOL.DATA.CHG_NO;
        DDRHLDR.LAW_OFF_NM1 = DCCSKHOL.DATA.LAW_NM1;
        DDRHLDR.LAW_ID_NO1 = DCCSKHOL.DATA.LAW_NO1;
        DDRHLDR.LAW_OFF_NM2 = DCCSKHOL.DATA.LAW_NM2;
        DDRHLDR.LAW_ID_NO2 = DCCSKHOL.DATA.LAW_NO2;
        DDRHLDR.DOWN_HLD_NO = WS_DOWN_HLD_NO;
        DDRHLDR.UP_HLD_NO = WS_UP_HLD_NO;
        DDRHLDR.CRT_CHNL = SCCGWA.COMM_AREA.REQ_SYSTEM;
        DDRHLDR.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRHLDR.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRHLDR.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRHLDR.AUTH_TL = SCCGWA.COMM_AREA.SUP1_ID;
        DDRHLDR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRHLDR.UPDTBL_TIME = SCCGWA.COMM_AREA.TR_TIME;
        DDRHLDR.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DDTHLDR();
        if (pgmRtn) return;
    }
    public void B045_NON_FIN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.REF_NO = DCCSKHOL.DATA.HLD_NO;
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.FMT_ID = "DDRHLD";
        BPCPNHIS.INFO.AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
        BPCPNHIS.INFO.CCY = CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC;
        BPCPNHIS.INFO.CCY_TYPE = CICQACAC.O_DATA.O_ACAC_DATA.O_CR_FLG;
        BPCPNHIS.INFO.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B050_OUTPUT_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCOKHOL);
        DCCOKHOL.DATA.HLD_NO = DCCSKHOL.DATA.HLD_NO;
        DCCOKHOL.DATA.AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        DCCOKHOL.DATA.HLD_TYP = WS_HLD_TYP;
        DCCOKHOL.DATA.SPR_TYP = WS_SPR_TYP;
        DCCOKHOL.DATA.CCY = WS_CCY;
        DCCOKHOL.DATA.CCY_TYP = WS_CCY_TYP;
        DCCOKHOL.DATA.AMT = WS_HLD_AMT_NEW;
        DCCOKHOL.DATA.EFF_DT = WS_EFF_DATE;
        DCCOKHOL.DATA.EXP_DT = WS_EXP_DATE;
        DCCOKHOL.DATA.NEW_DT = DCCSKHOL.DATA.NEW_DT;
        DCCOKHOL.DATA.CHG_NO = DCCSKHOL.DATA.CHG_NO;
        DCCOKHOL.DATA.SPR_NM = DCCSKHOL.DATA.SPR_NM;
        DCCOKHOL.DATA.RSN = DCCSKHOL.DATA.RSN;
        DCCOKHOL.DATA.RMK = DCCSKHOL.DATA.RMK;
        DCCOKHOL.DATA.CHG_BR = DCCSKHOL.DATA.CHG_BR;
        DCCOKHOL.DATA.LAW_NM1 = DCCSKHOL.DATA.LAW_NM1;
        DCCOKHOL.DATA.LAW_NO1 = DCCSKHOL.DATA.LAW_NO1;
        DCCOKHOL.DATA.LAW_NM2 = DCCSKHOL.DATA.LAW_NM2;
        DCCOKHOL.DATA.LAW_NO2 = DCCSKHOL.DATA.LAW_NO2;
        DCCOKHOL.DATA.CURR_BAL = WS_CURR_BAL;
        DCCOKHOL.DATA.AVL_AMT = WS_AVL_AMT;
        DCCOKHOL.DATA.ACC_HLD_AMT = WS_ACC_HLD_AMT;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DCCOKHOL;
        SCCFMT.DATA_LEN = 1363;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_DDTHLD() throws IOException,SQLException,Exception {
        DDTHLD_RD = new DBParm();
        DDTHLD_RD.TableName = "DDTHLD";
        DDTHLD_RD.upd = true;
        IBS.READ(SCCGWA, DDRHLD, DDTHLD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTHLD_FIRST() throws IOException,SQLException,Exception {
        DDTHLD_RD = new DBParm();
        DDTHLD_RD.TableName = "DDTHLD";
        DDTHLD_RD.where = "AC = :DDRHLD.AC "
            + "AND UP_HLD_NO = ' ' "
            + "AND HLD_STS = 'N'";
        DDTHLD_RD.upd = true;
        IBS.READ(SCCGWA, DDRHLD, this, DDTHLD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.col = "AC_STS_WORD,AC_TYPE";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DD_AC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TD_AC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTCCY_UPDATE() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DD_CCY_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DDTHLD() throws IOException,SQLException,Exception {
        DDTHLD_RD = new DBParm();
        DDTHLD_RD.TableName = "DDTHLD";
        DDTHLD_RD.errhdl = true;
        DUPKEY_RD = new DBParm();
        DUPKEY_RD.TableName = "DUPKEY";
        IBS.REWRITE(SCCGWA, DURKEY, DUPKEY_RD);
    }
    public void T000_WRITE_DDTHLDR() throws IOException,SQLException,Exception {
        DDTHLDR_RD = new DBParm();
        DDTHLDR_RD.TableName = "DDTHLDR";
        DDTHLDR_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DDRHLDR, DDTHLDR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLDR_REC_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.REWRITE(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void T000_REWRITE_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.REWRITE(SCCGWA, TDRSMST, TDTSMST_RD);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
