package com.hisun.DC;

import com.hisun.DD.*;
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
import com.hisun.BP.*;
import com.hisun.CI.*;
import com.hisun.DB.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZURHOL {
    int JIBS_tmp_int;
    DBParm DDTHLD_RD;
    DBParm DDTMST_RD;
    DBParm DDTCCY_RD;
    DBParm TDTSMST_RD;
    DBParm DDTHLDR_RD;
    DBParm DDTZMAC_RD;
    brParm DDTZMAC_BR = new brParm();
    brParm DDTCCZM_BR = new brParm();
    DBParm DDTCCZM_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_HIS_REMARKS = "RELEASE DEPOSIT HOLD";
    String K_HIS_REMARKS1 = "RELEASE AC STOP STATUS";
    String WS_ERR_MSG = " ";
    String WS_HLD_NO = " ";
    int WS_EFF_DATE = 0;
    int WS_EXP_DATE = 0;
    String WS_CCY = " ";
    char WS_CCY_TYP = ' ';
    char WS_HLD_STS = ' ';
    char WS_HLD_TYP = ' ';
    char WS_HLD_FLG = ' ';
    char WS_SPR_TYP = ' ';
    char WS_HLD_CLS = ' ';
    String WS_DOWN_HLD_NO = " ";
    String WS_UP_HLD_NO = " ";
    String WS_EC_HLD_NO = " ";
    String WS_AC_TYPE = " ";
    String WS_ACAC = " ";
    double WS_BEF_HLD_AMT = 0;
    double WS_REM_HLD_AMT = 0;
    double WS_CURR_BAL = 0;
    double WS_AVL_AMT = 0;
    double WS_CANCEL_AVL_AMT = 0;
    double WS_AVL_HLD_AMT = 0;
    double WS_HLD_AMT = 0;
    double WS_ACC_HLD_AMT = 0;
    String WS_ZMAC_REF_NO = " ";
    short WS_TIME = 0;
    String WS_AC_APP = " ";
    char WS_HLDNO_FLG = ' ';
    char WS_ZMAC_FLG = ' ';
    char WS_CCZM_FLG = ' ';
    char WS_CIRCLE_FLG = ' ';
    char WS_STS_CHANGE_FLG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    TDRSMST TDRSMST = new TDRSMST();
    DDRHLD DDRHLD = new DDRHLD();
    DDRHLDR DDRHLDR = new DDRHLDR();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICQACAC CICQACAC = new CICQACAC();
    DDRZMAC DDRZMAC = new DDRZMAC();
    DDRCCZM DDRCCZM = new DDRCCZM();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    int WS_HLDR_RHLD_CNT = 0;
    int WS_LAW_AC_HLD_CNT = 0;
    int WS_LAW_AC_FBID_CNT = 0;
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    DCCURHOL DCCURHOL;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DCCURHOL DCCURHOL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCURHOL = DCCURHOL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZURHOL return!");
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
        B020_CHK_AC_STS_PROC();
        if (pgmRtn) return;
        B030_UPD_HLD_REC_PROC();
        if (pgmRtn) return;
        B050_NON_FIN_HIS_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            && WS_HLD_CLS == '3') {
            B060_WRITE_VCH_PROC();
            if (pgmRtn) return;
        }
        B070_TRANS_DATA_BACK();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_PROC() throws IOException,SQLException,Exception {
        if (DCCURHOL.DATA.HLD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLDNO_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCURHOL.DATA.DEDUCT_FLG == '2') {
            DCCURHOL.DATA.RHLD_TYP = '1';
        }
        if (DCCURHOL.DATA.RHLD_TYP == ' ' 
            && DCCURHOL.DATA.RAMT == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TRF_AMT_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCURHOL.DATA.RAMT < 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AMT_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCURHOL.DATA.CHG_BR == 0) {
            DCCURHOL.DATA.CHG_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        }
        if (DCCURHOL.DATA.ABC == ' ') {
            DCCURHOL.DATA.ABC = 'N';
        }
    }
    public void B020_INQ_HLD_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHLD);
        DDRHLD.KEY.HLD_NO = DCCURHOL.DATA.HLD_NO;
        T000_READ_DDTHLD();
        if (pgmRtn) return;
        WS_ACAC = DDRHLD.AC;
        WS_HLD_NO = DDRHLD.KEY.HLD_NO;
        WS_HLD_TYP = DDRHLD.HLD_TYP;
        WS_HLD_FLG = DDRHLD.HLD_FLG;
        WS_SPR_TYP = DDRHLD.SPR_BR_TYP;
        WS_HLD_STS = DDRHLD.HLD_STS;
        WS_EFF_DATE = DDRHLD.EFF_DATE;
        WS_EXP_DATE = DDRHLD.EXP_DATE;
        WS_CCY = DDRHLD.CCY;
        WS_CCY_TYP = DDRHLD.CCY_TYPE;
        WS_HLD_CLS = DDRHLD.HLD_CLS;
        WS_BEF_HLD_AMT = DDRHLD.HLD_AMT;
        WS_DOWN_HLD_NO = DDRHLD.DOWN_HLD_NO;
        WS_UP_HLD_NO = DDRHLD.UP_HLD_NO;
        if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
        } else {
            if (DDRHLD.SPR_BR_TYP == '1' 
                && DCCURHOL.DATA.LAW_NM1.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_LAW_NM_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDRHLD.SPR_BR_TYP == '1' 
                && DCCURHOL.DATA.LAW_NO1.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_LAW_NO_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDRHLD.SPR_BR_TYP == '1' 
                && DCCURHOL.DATA.CHG_NO.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_WRIT_NO_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDRHLD.SPR_BR_TYP == '1' 
                && DCCURHOL.DATA.SPR_NM.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_SPR_NM_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            } else {
                if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                    || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                    || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")) 
                    && !DDRHLD.SPR_CHNL.equalsIgnoreCase("033100") 
                    && DCCURHOL.DATA.DEDUCT_FLG == ' ') {
                    if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != DDRHLD.SPR_BR) {
                        WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_RHLD_BR_M_HLD_BR;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
        }
        if ((SCCGWA.COMM_AREA.CANCEL_IND != 'Y') 
            || (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            && DCCURHOL.DATA.ABC == 'Y')) {
            if (DDRHLD.HLD_STS == 'C') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_AC_HAS_REL;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if ((DCCURHOL.DATA.RHLD_TYP == '2' 
                && DDRHLD.HLD_TYP == '1')) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_REL_TYP_INVALID;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DCCURHOL.DATA.RHLD_TYP == ' ' 
                && DCCURHOL.DATA.RAMT > DDRHLD.HLD_AMT) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_REL_AMT_GT_HLD_AMT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DCCURHOL.DATA.RHLD_TYP == ' ') {
                if (DCCURHOL.DATA.RAMT == DDRHLD.HLD_AMT) {
                    DCCURHOL.DATA.RHLD_TYP = '1';
                } else {
                    DCCURHOL.DATA.RHLD_TYP = '2';
                }
            }
            if (DCCURHOL.DATA.RHLD_TYP == '2') {
                if (DCCURHOL.DATA.RAMT == DDRHLD.HLD_AMT) {
                    DCCURHOL.DATA.RHLD_TYP = '1';
                }
            }
        }
        if (DCCURHOL.DATA.RHLD_TYP == '2' 
            && SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            if (DCCURHOL.DATA.RAMT > DDRHLD.HLD_AMT) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_REL_AMT_GT_HLD_AMT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCCURHOL.DATA.RHLD_TYP == '1' 
            && DDRHLD.HLD_TYP == '2' 
            && SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            DCCURHOL.DATA.RAMT = DDRHLD.HLD_AMT;
        }
    }
    public void B020_CHK_AC_STS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'A';
        CICQACAC.DATA.ACAC_NO = WS_ACAC;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        if (CICQACAC.O_DATA.O_ACR_DATA.O_STS == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
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
            WS_AC_TYPE = "" + DDRMST.AC_TYPE;
            JIBS_tmp_int = WS_AC_TYPE.length();
            for (int i=0;i<1-JIBS_tmp_int;i++) WS_AC_TYPE = "0" + WS_AC_TYPE;
        }
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDRCCY);
            DDRCCY.KEY.AC = WS_ACAC;
            T000_READ_DDTCCY();
            if (pgmRtn) return;
            WS_CANCEL_AVL_AMT = DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL + DDRCCY.CCAL_TOT_BAL - DDRCCY.MARGIN_BAL;
            if (DDRHLD.HLD_STS == 'W') {
                WS_CURR_BAL = DDRCCY.CURR_BAL;
                WS_AVL_AMT = WS_CANCEL_AVL_AMT;
            }
            WS_AC_APP = "DD";
        }
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("TD")) {
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.KEY.ACO_AC = WS_ACAC;
            T000_READ_TDTSMST();
            if (pgmRtn) return;
            if (TDRSMST.ACO_STS == '1' 
                || TDRSMST.ACO_STS == 'C') {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_CANCEL_AVL_AMT = TDRSMST.BAL - TDRSMST.HBAL - TDRSMST.GUAR_BAL;
            if (DDRHLD.HLD_STS == 'W') {
                WS_CURR_BAL = TDRSMST.BAL;
                WS_AVL_AMT = WS_CANCEL_AVL_AMT;
            }
            WS_AC_TYPE = TDRSMST.PRDAC_CD;
            WS_AC_APP = "TD";
        }
    }
    public void B030_UPD_HLD_REC_PROC() throws IOException,SQLException,Exception {
        if ((SCCGWA.COMM_AREA.CANCEL_IND != 'Y') 
            || (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            && DCCURHOL.DATA.ABC == 'Y')) {
            R000_WRITE_DDTHLDR();
            if (pgmRtn) return;
        } else {
            R000_UPD_DDTHLDR();
            if (pgmRtn) return;
        }
        R000_UPDATE_DDTHLD();
        if (pgmRtn) return;
        if ((SCCGWA.COMM_AREA.CANCEL_IND != 'Y') 
            || (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            && DCCURHOL.DATA.ABC == 'Y')) {
            B030_01_UPD_HLD_REC_PROC();
            if (pgmRtn) return;
        } else {
            B030_02_UPD_HLD_REC_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_01_UPD_HLD_REC_PROC() throws IOException,SQLException,Exception {
        if (WS_HLD_STS == 'W') {
            if (WS_HLD_TYP == '1') {
                T000_GROUP_DDTHLD_AC_HLD();
                if (pgmRtn) return;
                if (WS_HLD_FLG == '1') {
                    if (WS_SPR_TYP == '1') {
                        if (WS_LAW_AC_HLD_CNT == 0) {
                            if (WS_AC_APP.equalsIgnoreCase("DD")) {
                                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                                DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 8 - 1) + "0" + DDRCCY.STS_WORD.substring(8 + 1 - 1);
                            } else {
                                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                                JIBS_tmp_int = TDRSMST.STSW.length();
                                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                                TDRSMST.STSW = TDRSMST.STSW.substring(0, 2 - 1) + "0" + TDRSMST.STSW.substring(2 + 1 - 1);
                            }
                        }
                    } else {
                        if (WS_AC_APP.equalsIgnoreCase("DD")) {
                            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                            JIBS_tmp_int = DDRCCY.STS_WORD.length();
                            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                            DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 9 - 1) + "0" + DDRCCY.STS_WORD.substring(9 + 1 - 1);
                        } else {
                            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                            JIBS_tmp_int = TDRSMST.STSW.length();
                            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                            TDRSMST.STSW = TDRSMST.STSW.substring(0, 3 - 1) + "0" + TDRSMST.STSW.substring(3 + 1 - 1);
                        }
                    }
                } else {
                    if (WS_SPR_TYP == '1') {
                        if (WS_LAW_AC_FBID_CNT == 0) {
                            if (WS_AC_APP.equalsIgnoreCase("DD")) {
                                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                                DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 17 - 1) + "0" + DDRCCY.STS_WORD.substring(17 + 1 - 1);
                            } else {
                                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                                JIBS_tmp_int = TDRSMST.STSW.length();
                                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                                TDRSMST.STSW = TDRSMST.STSW.substring(0, 8 - 1) + "0" + TDRSMST.STSW.substring(8 + 1 - 1);
                            }
                        }
                    } else {
                        if (WS_AC_APP.equalsIgnoreCase("DD")) {
                            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                            JIBS_tmp_int = DDRCCY.STS_WORD.length();
                            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                            DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 16 - 1) + "0" + DDRCCY.STS_WORD.substring(16 + 1 - 1);
                        } else {
                            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                            JIBS_tmp_int = TDRSMST.STSW.length();
                            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                            TDRSMST.STSW = TDRSMST.STSW.substring(0, 7 - 1) + "0" + TDRSMST.STSW.substring(7 + 1 - 1);
                        }
                    }
                }
            }
            CEP.TRC(SCCGWA, WS_STS_CHANGE_FLG);
            CEP.TRC(SCCGWA, WS_UP_HLD_NO);
            CEP.TRC(SCCGWA, WS_DOWN_HLD_NO);
            if (WS_STS_CHANGE_FLG == 'Y') {
                if (WS_UP_HLD_NO.trim().length() > 0) {
                    IBS.init(SCCGWA, DDRHLD);
                    DDRHLD.KEY.HLD_NO = WS_UP_HLD_NO;
                    T000_READ_DDTHLD();
                    if (pgmRtn) return;
                    DDRHLD.DOWN_HLD_NO = WS_DOWN_HLD_NO;
                    DDRHLD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    DDRHLD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                    T000_REWRITE_DDTHLD();
                    if (pgmRtn) return;
                }
                if (WS_DOWN_HLD_NO.trim().length() > 0) {
                    IBS.init(SCCGWA, DDRHLD);
                    DDRHLD.KEY.HLD_NO = WS_DOWN_HLD_NO;
                    T000_READ_DDTHLD();
                    if (pgmRtn) return;
                    DDRHLD.UP_HLD_NO = WS_UP_HLD_NO;
                    DDRHLD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    DDRHLD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                    T000_REWRITE_DDTHLD();
                    if (pgmRtn) return;
                }
            }
            if (WS_AC_APP.equalsIgnoreCase("DD")) {
                B030_UPDATE_DDTCCY();
                if (pgmRtn) return;
                WS_CURR_BAL = DDRCCY.CURR_BAL;
                WS_AVL_AMT = DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL - DDRCCY.MARGIN_BAL + DDRCCY.CCAL_TOT_BAL;
            } else {
                B030_UPDATE_TDTSMST();
                if (pgmRtn) return;
                WS_CURR_BAL = TDRSMST.BAL;
                WS_AVL_AMT = TDRSMST.BAL - TDRSMST.HBAL - TDRSMST.GUAR_BAL;
            }
        } else {
            if (WS_AC_APP.equalsIgnoreCase("DD")) {
                B030_UPDATE_DDTCCY();
                if (pgmRtn) return;
                WS_CURR_BAL = DDRCCY.CURR_BAL;
                WS_AVL_AMT = DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL - DDRCCY.MARGIN_BAL + DDRCCY.CCAL_TOT_BAL;
            } else {
                B030_UPDATE_TDTSMST();
                if (pgmRtn) return;
                WS_CURR_BAL = TDRSMST.BAL;
                WS_AVL_AMT = TDRSMST.BAL - TDRSMST.HBAL - TDRSMST.GUAR_BAL;
            }
        }
    }
    public void B030_02_UPD_HLD_REC_PROC() throws IOException,SQLException,Exception {
        if (WS_HLD_STS == 'W') {
            if (WS_HLD_TYP == '1') {
                if (WS_HLD_FLG == '1') {
                    if (WS_SPR_TYP == '1') {
                        if (WS_AC_APP.equalsIgnoreCase("DD")) {
                            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                            JIBS_tmp_int = DDRCCY.STS_WORD.length();
                            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                            DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 8 - 1) + "1" + DDRCCY.STS_WORD.substring(8 + 1 - 1);
                        } else {
                            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                            JIBS_tmp_int = TDRSMST.STSW.length();
                            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                            TDRSMST.STSW = TDRSMST.STSW.substring(0, 2 - 1) + "1" + TDRSMST.STSW.substring(2 + 1 - 1);
                        }
                    } else {
                        if (WS_AC_APP.equalsIgnoreCase("DD")) {
                            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                            JIBS_tmp_int = DDRCCY.STS_WORD.length();
                            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                            if (DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
                                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_HLD_OR_FORBID;
                                S000_ERR_MSG_PROC();
                                if (pgmRtn) return;
                            }
                            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                            JIBS_tmp_int = DDRCCY.STS_WORD.length();
                            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                            DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 9 - 1) + "1" + DDRCCY.STS_WORD.substring(9 + 1 - 1);
                        } else {
                            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                            JIBS_tmp_int = TDRSMST.STSW.length();
                            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                            if (TDRSMST.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_HLD_OR_FORBID;
                                S000_ERR_MSG_PROC();
                                if (pgmRtn) return;
                            }
                            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                            JIBS_tmp_int = TDRSMST.STSW.length();
                            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                            TDRSMST.STSW = TDRSMST.STSW.substring(0, 3 - 1) + "1" + TDRSMST.STSW.substring(3 + 1 - 1);
                        }
                    }
                } else {
                    if (WS_SPR_TYP == '1') {
                        if (WS_AC_APP.equalsIgnoreCase("DD")) {
                            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                            JIBS_tmp_int = DDRCCY.STS_WORD.length();
                            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                            DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 17 - 1) + "1" + DDRCCY.STS_WORD.substring(17 + 1 - 1);
                        } else {
                            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                            JIBS_tmp_int = TDRSMST.STSW.length();
                            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                            TDRSMST.STSW = TDRSMST.STSW.substring(0, 8 - 1) + "1" + TDRSMST.STSW.substring(8 + 1 - 1);
                        }
                    } else {
                        if (WS_AC_APP.equalsIgnoreCase("DD")) {
                            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                            JIBS_tmp_int = DDRCCY.STS_WORD.length();
                            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                            if (DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1")) {
                                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_HLD_OR_FORBID;
                                S000_ERR_MSG_PROC();
                                if (pgmRtn) return;
                            }
                            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                            JIBS_tmp_int = DDRCCY.STS_WORD.length();
                            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                            DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 16 - 1) + "1" + DDRCCY.STS_WORD.substring(16 + 1 - 1);
                        } else {
                            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                            JIBS_tmp_int = TDRSMST.STSW.length();
                            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                            if (TDRSMST.STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1")) {
                                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_HLD_OR_FORBID;
                                S000_ERR_MSG_PROC();
                                if (pgmRtn) return;
                            }
                            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                            JIBS_tmp_int = TDRSMST.STSW.length();
                            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                            TDRSMST.STSW = TDRSMST.STSW.substring(0, 7 - 1) + "1" + TDRSMST.STSW.substring(7 + 1 - 1);
                        }
                    }
                }
            }
        } else {
            if (WS_AC_APP.equalsIgnoreCase("DD")) {
                B030_UPDATE_DDTCCY_EC();
                if (pgmRtn) return;
                WS_CURR_BAL = DDRCCY.CURR_BAL;
                WS_AVL_AMT = DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL - DDRCCY.MARGIN_BAL + DDRCCY.CCAL_TOT_BAL;
            } else {
                B030_UPDATE_TDTSMST_EC();
                if (pgmRtn) return;
                WS_CURR_BAL = TDRSMST.BAL;
                WS_AVL_AMT = TDRSMST.BAL - TDRSMST.HBAL - TDRSMST.GUAR_BAL;
            }
        }
    }
    public void B030_UPDATE_DDTCCY() throws IOException,SQLException,Exception {
        if (WS_HLD_TYP == '1') {
            T000_GROUP_DDTHLD_AC_HLD();
            if (pgmRtn) return;
            if (WS_HLD_FLG == '1') {
                if (WS_SPR_TYP == '1') {
                    if (WS_LAW_AC_HLD_CNT == 0) {
                        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                        JIBS_tmp_int = DDRCCY.STS_WORD.length();
                        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                        DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 8 - 1) + "0" + DDRCCY.STS_WORD.substring(8 + 1 - 1);
                    }
                } else {
                    if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                    JIBS_tmp_int = DDRCCY.STS_WORD.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                    DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 9 - 1) + "0" + DDRCCY.STS_WORD.substring(9 + 1 - 1);
                }
            } else {
                if (WS_SPR_TYP == '1') {
                    if (WS_LAW_AC_FBID_CNT == 0) {
                        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                        JIBS_tmp_int = DDRCCY.STS_WORD.length();
                        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                        DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 17 - 1) + "0" + DDRCCY.STS_WORD.substring(17 + 1 - 1);
                    }
                } else {
                    if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                    JIBS_tmp_int = DDRCCY.STS_WORD.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                    DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 16 - 1) + "0" + DDRCCY.STS_WORD.substring(16 + 1 - 1);
                }
            }
        }
        if (WS_HLD_TYP == '2') {
            DDRCCY.HOLD_BAL = DDRCCY.HOLD_BAL - DCCURHOL.DATA.RAMT;
        }
        R000_CCY_SMST_PROC();
        if (pgmRtn) return;
        T000_REWRITE_DDTCCY();
        if (pgmRtn) return;
    }
    public void B030_UPDATE_DDTCCY_EC() throws IOException,SQLException,Exception {
        if (WS_HLD_TYP == '1') {
            if (WS_HLD_FLG == '1') {
                if (WS_SPR_TYP == '1') {
                    if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                    JIBS_tmp_int = DDRCCY.STS_WORD.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                    DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 8 - 1) + "1" + DDRCCY.STS_WORD.substring(8 + 1 - 1);
                } else {
                    if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                    JIBS_tmp_int = DDRCCY.STS_WORD.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                    if (DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
                        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_HLD_OR_FORBID;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                    JIBS_tmp_int = DDRCCY.STS_WORD.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                    DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 9 - 1) + "1" + DDRCCY.STS_WORD.substring(9 + 1 - 1);
                }
            } else {
                if (WS_SPR_TYP == '1') {
                    if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                    JIBS_tmp_int = DDRCCY.STS_WORD.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                    DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 17 - 1) + "1" + DDRCCY.STS_WORD.substring(17 + 1 - 1);
                } else {
                    if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                    JIBS_tmp_int = DDRCCY.STS_WORD.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                    if (DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1")) {
                        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_HLD_OR_FORBID;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                    JIBS_tmp_int = DDRCCY.STS_WORD.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                    DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 16 - 1) + "1" + DDRCCY.STS_WORD.substring(16 + 1 - 1);
                }
            }
        }
        if (WS_HLD_TYP == '2') {
            DDRCCY.HOLD_BAL = DDRCCY.HOLD_BAL + DCCURHOL.DATA.RAMT;
        }
        R000_CCY_SMST_PROC_EC();
        if (pgmRtn) return;
        T000_REWRITE_DDTCCY();
        if (pgmRtn) return;
    }
    public void B030_UPDATE_TDTSMST() throws IOException,SQLException,Exception {
        if (WS_HLD_TYP == '1') {
            T000_GROUP_DDTHLD_AC_HLD();
            if (pgmRtn) return;
            if (WS_HLD_FLG == '1') {
                if (WS_SPR_TYP == '1') {
                    if (WS_LAW_AC_HLD_CNT == 0) {
                        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                        JIBS_tmp_int = TDRSMST.STSW.length();
                        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                        TDRSMST.STSW = TDRSMST.STSW.substring(0, 2 - 1) + "0" + TDRSMST.STSW.substring(2 + 1 - 1);
                    }
                } else {
                    if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                    JIBS_tmp_int = TDRSMST.STSW.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                    TDRSMST.STSW = TDRSMST.STSW.substring(0, 3 - 1) + "0" + TDRSMST.STSW.substring(3 + 1 - 1);
                }
            } else {
                if (WS_SPR_TYP == '1') {
                    if (WS_LAW_AC_FBID_CNT == 0) {
                        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                        JIBS_tmp_int = TDRSMST.STSW.length();
                        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                        TDRSMST.STSW = TDRSMST.STSW.substring(0, 8 - 1) + "0" + TDRSMST.STSW.substring(8 + 1 - 1);
                    }
                } else {
                    if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                    JIBS_tmp_int = TDRSMST.STSW.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                    TDRSMST.STSW = TDRSMST.STSW.substring(0, 7 - 1) + "0" + TDRSMST.STSW.substring(7 + 1 - 1);
                }
            }
        }
        if (WS_HLD_TYP == '2') {
            TDRSMST.HBAL = TDRSMST.HBAL - DCCURHOL.DATA.RAMT;
        }
        R000_CCY_SMST_PROC();
        if (pgmRtn) return;
        T000_REWRITE_TDTSMST();
        if (pgmRtn) return;
    }
    public void B030_UPDATE_TDTSMST_EC() throws IOException,SQLException,Exception {
        if (WS_HLD_TYP == '1') {
            if (WS_HLD_FLG == '1') {
                if (WS_SPR_TYP == '1') {
                    if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                    JIBS_tmp_int = TDRSMST.STSW.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                    TDRSMST.STSW = TDRSMST.STSW.substring(0, 2 - 1) + "1" + TDRSMST.STSW.substring(2 + 1 - 1);
                } else {
                    if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                    JIBS_tmp_int = TDRSMST.STSW.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                    if (TDRSMST.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_HLD_OR_FORBID;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                    JIBS_tmp_int = TDRSMST.STSW.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                    TDRSMST.STSW = TDRSMST.STSW.substring(0, 3 - 1) + "1" + TDRSMST.STSW.substring(3 + 1 - 1);
                }
            } else {
                if (WS_SPR_TYP == '1') {
                    if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                    JIBS_tmp_int = TDRSMST.STSW.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                    TDRSMST.STSW = TDRSMST.STSW.substring(0, 8 - 1) + "1" + TDRSMST.STSW.substring(8 + 1 - 1);
                } else {
                    if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                    JIBS_tmp_int = TDRSMST.STSW.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                    if (TDRSMST.STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1")) {
                        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_HLD_OR_FORBID;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                    JIBS_tmp_int = TDRSMST.STSW.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                    TDRSMST.STSW = TDRSMST.STSW.substring(0, 7 - 1) + "1" + TDRSMST.STSW.substring(7 + 1 - 1);
                }
            }
        }
        if (WS_HLD_TYP == '2') {
            TDRSMST.HBAL = TDRSMST.HBAL + DCCURHOL.DATA.RAMT;
        }
        R000_CCY_SMST_PROC_EC();
        if (pgmRtn) return;
        T000_REWRITE_TDTSMST();
        if (pgmRtn) return;
    }
    public void B050_NON_FIN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        if (WS_HLD_FLG == '2') {
            BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS1;
        }
        BPCPNHIS.INFO.REF_NO = WS_HLD_NO;
        if (WS_HLD_STS == 'N') {
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.FMT_ID = "DDRHLD";
        } else {
            BPCPNHIS.INFO.TX_TYP = 'D';
        }
        if (DCCURHOL.DATA.TX_TYP_CD.trim().length() > 0) {
            BPCPNHIS.INFO.TX_TYP_CD = DCCURHOL.DATA.TX_TYP_CD;
        } else {
            BPCPNHIS.INFO.TX_TYP_CD = "P130";
        }
        if (WS_HLD_FLG == '2') {
            BPCPNHIS.INFO.TX_TYP_CD = "P146";
        }
        BPCPNHIS.INFO.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        BPCPNHIS.INFO.AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
        BPCPNHIS.INFO.CCY = CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC;
        BPCPNHIS.INFO.CCY_TYPE = CICQACAC.O_DATA.O_ACAC_DATA.O_CR_FLG;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B060_WRITE_VCH_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRZMAC);
        DDRZMAC.HLD_NO = WS_HLD_NO;
        T000_READ_DDTZMAC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ZMAC_REF_NO = DDRZMAC.KEY.REF_NO;
            IBS.init(SCCGWA, DDRZMAC);
            DDRZMAC.KEY.REF_NO = WS_ZMAC_REF_NO;
            T000_STARTBR_DDTZMAC();
            if (pgmRtn) return;
            T000_READNEXT_DDTZMAC();
            if (pgmRtn) return;
            while (WS_ZMAC_FLG != 'N') {
                DDRZMAC.HLD_STS = 'C';
                DDRZMAC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DDRZMAC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                T000_REWRITE_DDTZMAC();
                if (pgmRtn) return;
                T000_READNEXT_DDTZMAC();
                if (pgmRtn) return;
            }
            T000_ENDBR_DDTZMAC();
            if (pgmRtn) return;
            IBS.init(SCCGWA, DDRCCZM);
            DDRCCZM.KEY.REF_NO = WS_ZMAC_REF_NO;
            T000_STARTBR_DDTCCZM();
            if (pgmRtn) return;
            T000_READNEXT_DDTCCZM();
            if (pgmRtn) return;
            while (WS_CCZM_FLG != 'N') {
                if (DDRCCZM.STS == 'N') {
                    WS_TIME += 1;
                    DDRCCZM.STS = 'C';
                    DDRCCZM.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    DDRCCZM.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                    T000_REWRITE_DDTCCZM();
                    if (pgmRtn) return;
                }
                T000_READNEXT_DDTCCZM();
                if (pgmRtn) return;
            }
            T000_ENDBR_DDTCCZM();
            if (pgmRtn) return;
            if (WS_TIME >= 1) {
                IBS.init(SCCGWA, BPCPOEWA);
                BPCPOEWA.DATA.CNTR_TYPE = "BVF";
                BPCPOEWA.DATA.PROD_CODE = "9710000002";
                BPCPOEWA.DATA.EVENT_CODE = "CR";
                BPCPOEWA.DATA.BR_OLD = DDRCCZM.BANK_NO;
                BPCPOEWA.DATA.CCY_INFO[1-1].CCY = CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC;
                BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
                BPCPOEWA.DATA.AMT_INFO[1-1].AMT = DDRCCZM.TOTAL_OPEN_AMT;
                BPCPOEWA.DATA.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
                S000_CALL_BPZPOEWA();
                if (pgmRtn) return;
            }
        }
    }
    public void B070_TRANS_DATA_BACK() throws IOException,SQLException,Exception {
        DCCURHOL.DATA.AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        if (DCCURHOL.DATA.RHLD_TYP == '1') {
            DCCURHOL.DATA.RAMT = WS_BEF_HLD_AMT;
        }
        DCCURHOL.DATA.HLD_TYP = WS_HLD_TYP;
        DCCURHOL.DATA.SPR_TYP = WS_SPR_TYP;
        DCCURHOL.DATA.CCY = WS_CCY;
        DCCURHOL.DATA.CCY_TYP = WS_CCY_TYP;
        DCCURHOL.DATA.BEF_AMT = WS_BEF_HLD_AMT;
        DCCURHOL.DATA.EFF_DT = WS_EFF_DATE;
        DCCURHOL.DATA.EXP_DT = WS_EXP_DATE;
        DCCURHOL.DATA.REM_AMT = WS_REM_HLD_AMT;
        DCCURHOL.DATA.AC_TYPE = WS_AC_TYPE;
        DCCURHOL.DATA.CURR_BAL = WS_CURR_BAL;
        DCCURHOL.DATA.AVL_AMT = WS_AVL_AMT;
        DCCURHOL.DATA.ACC_HLD_AMT = WS_ACC_HLD_AMT;
    }
    public void R000_WRITE_DDTHLDR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHLDR);
        DDRHLDR.KEY.HLD_NO = DDRHLD.KEY.HLD_NO;
        DDRHLDR.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRHLDR.KEY.TR_JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        DDRHLDR.AC = WS_ACAC;
        if (DCCURHOL.DATA.RHLD_TYP == '1') {
            if (WS_HLD_FLG == '2') {
                DDRHLDR.HLD_TYP = 'B';
            } else {
                DDRHLDR.HLD_TYP = '5';
            }
        } else {
            DDRHLDR.HLD_TYP = '4';
        }
        if (WS_HLD_STS == 'N') {
            CEP.TRC(SCCGWA, "DDDDDDD");
            CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
            CEP.TRC(SCCGWA, DDRCCY.HOLD_BAL);
            CEP.TRC(SCCGWA, DDRCCY.CCAL_TOT_BAL);
            CEP.TRC(SCCGWA, DDRCCY.MARGIN_BAL);
            CEP.TRC(SCCGWA, DCCURHOL.DATA.RAMT);
            WS_HLD_AMT = DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL + DDRCCY.CCAL_TOT_BAL - DDRCCY.MARGIN_BAL + DCCURHOL.DATA.RAMT;
        }
        if (DCCURHOL.DATA.DEDUCT_FLG == '1') {
            DDRHLDR.TR_AMT = DCCURHOL.DATA.RAMT;
            if (WS_HLD_AMT >= 0) {
                DDRHLDR.BEF_TR_AMT = DDRHLD.HLD_AMT - DCCURHOL.DATA.RAMT;
            } else {
                DDRHLDR.BEF_TR_AMT = DDRHLD.HLD_AMT + WS_HLD_AMT;
            }
        } else {
            if (DCCURHOL.DATA.RHLD_TYP == '2') {
                DDRHLDR.TR_AMT = DCCURHOL.DATA.RAMT;
                if (WS_HLD_STS == 'W') {
                    DDRHLDR.BEF_TR_AMT = 0;
                } else {
                    if (WS_HLD_AMT >= 0) {
                        DDRHLDR.BEF_TR_AMT = DDRHLD.HLD_AMT - DCCURHOL.DATA.RAMT;
                    } else {
                        DDRHLDR.BEF_TR_AMT = DDRHLD.HLD_AMT + WS_HLD_AMT;
                    }
                }
            } else {
                DDRHLDR.TR_AMT = WS_BEF_HLD_AMT;
                DDRHLDR.BEF_TR_AMT = 0;
            }
        }
        WS_ACC_HLD_AMT = DDRHLDR.BEF_TR_AMT;
        DDRHLDR.SPR_BR_TYP = WS_SPR_TYP;
        DDRHLDR.CHG_RSN = DCCURHOL.DATA.RSN;
        DDRHLDR.SPR_BR_NM = DCCURHOL.DATA.SPR_NM;
        DDRHLDR.CHG_WRIT_NO = DCCURHOL.DATA.CHG_NO;
        DDRHLDR.LAW_OFF_NM1 = DCCURHOL.DATA.LAW_NM1;
        DDRHLDR.LAW_ID_NO1 = DCCURHOL.DATA.LAW_NO1;
        DDRHLDR.LAW_OFF_NM2 = DCCURHOL.DATA.LAW_NM2;
        DDRHLDR.LAW_ID_NO2 = DCCURHOL.DATA.LAW_NO2;
        DDRHLDR.DOWN_HLD_NO = WS_DOWN_HLD_NO;
        DDRHLDR.UP_HLD_NO = WS_UP_HLD_NO;
        DDRHLDR.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRHLDR.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRHLDR.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRHLDR.AUTH_TL = SCCGWA.COMM_AREA.SUP1_ID;
        DDRHLDR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRHLDR.UPDTBL_TIME = SCCGWA.COMM_AREA.TR_TIME;
        DDRHLDR.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRHLDR.CRT_CHNL = SCCGWA.COMM_AREA.REQ_SYSTEM;
        if (DCCURHOL.DATA.DEDUCT_FLG == '1') {
            DDRHLDR.DEDUCT_FLG = '2';
        }
        T000_WRITE_DDTHLDR();
        if (pgmRtn) return;
    }
    public void R000_UPD_DDTHLDR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHLDR);
        DDRHLDR.KEY.HLD_NO = DCCURHOL.DATA.HLD_NO;
        DDRHLDR.KEY.TR_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        DDRHLDR.KEY.TR_JRNNO = GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
        T000_READ_DDTHLDR();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != DDRHLDR.CRT_BR) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CANCEL_BR_NO_MATCH;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCCURHOL.DATA.RAMT == 0 
            && DDRHLDR.TR_AMT != 0) {
            DCCURHOL.DATA.RAMT = DDRHLDR.TR_AMT;
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            && WS_SPR_TYP == '2') {
            if (WS_CANCEL_AVL_AMT < DCCURHOL.DATA.RAMT) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AVL_BAL_NOT_ENOUGH;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        DDRHLDR.REVERSE_FLG = 'Y';
        DDRHLDR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRHLDR.UPDTBL_TIME = SCCGWA.COMM_AREA.TR_TIME;
        DDRHLDR.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DDTHLDR();
        if (pgmRtn) return;
        if (DDRHLDR.HLD_TYP == '4') {
            DCCURHOL.DATA.RHLD_TYP = '2';
        } else {
            DCCURHOL.DATA.RHLD_TYP = '1';
        }
    }
    public void R000_UPDATE_DDTHLD() throws IOException,SQLException,Exception {
        if ((SCCGWA.COMM_AREA.CANCEL_IND != 'Y') 
            || (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            && DCCURHOL.DATA.ABC == 'Y')) {
            DDRHLD.REL_RSN = DCCURHOL.DATA.RSN;
            DDRHLD.REMARK = DCCURHOL.DATA.RMK;
            if (DCCURHOL.DATA.RHLD_TYP == '2') {
                CEP.TRC(SCCGWA, "AAA");
                CEP.TRC(SCCGWA, WS_HLD_AMT);
                CEP.TRC(SCCGWA, DCCURHOL.DATA.RAMT);
                CEP.TRC(SCCGWA, DDRHLD.HLD_AMT);
                DDRHLD.HLD_AMT = DDRHLD.HLD_AMT - DCCURHOL.DATA.RAMT;
                WS_REM_HLD_AMT = DDRHLD.HLD_AMT;
            } else {
                if (DCCURHOL.DATA.DEDUCT_FLG != '1') {
                    DDRHLD.HLD_STS = 'C';
                    WS_STS_CHANGE_FLG = 'Y';
                    CEP.TRC(SCCGWA, WS_STS_CHANGE_FLG);
                }
                DDRHLD.HLD_AMT = 0;
                WS_REM_HLD_AMT = 0;
            }
        } else {
            DDRHLD.REL_RSN = " ";
            DDRHLD.REMARK = " ";
            if (WS_HLD_TYP == '2') {
                if (DCCURHOL.DATA.RHLD_TYP == '2') {
                    CEP.TRC(SCCGWA, "BBB");
                    DDRHLD.HLD_AMT = DDRHLD.HLD_AMT + DCCURHOL.DATA.RAMT;
                } else {
                    CEP.TRC(SCCGWA, "CCC");
                    DDRHLD.HLD_AMT = DDRHLD.HLD_AMT + DDRHLDR.TR_AMT;
                }
                if (DCCURHOL.DATA.RHLD_TYP == '1') {
                    DDRHLD.HLD_STS = 'N';
                    WS_STS_CHANGE_FLG = 'Y';
                }
                if (DDRHLD.HLD_AMT != 0) {
                    DDRHLD.HLD_STS = 'N';
                    WS_STS_CHANGE_FLG = 'Y';
                }
                if (DDRHLDR.BEF_TR_AMT == 0) {
                    DDRHLD.HLD_STS = 'W';
                    WS_STS_CHANGE_FLG = 'Y';
                }
            } else {
                DDRHLD.HLD_STS = 'N';
                if (DDRHLDR.BEF_TR_AMT == 0) {
                    DDRHLD.HLD_STS = 'W';
                    WS_STS_CHANGE_FLG = 'Y';
                }
            }
            WS_HLD_STS = DDRHLD.HLD_STS;
        }
        if (DDRHLD.SPR_BR_TYP == '1' 
            && DCCURHOL.DATA.RHLD_TYP == '1') {
            if ((SCCGWA.COMM_AREA.CANCEL_IND != 'Y') 
                || (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
                && DCCURHOL.DATA.ABC == 'Y')) {
                DDRHLD.REL_WRIT_NO = DCCURHOL.DATA.CHG_NO;
            } else {
                DDRHLD.REL_WRIT_NO = " ";
            }
        }
        if ((SCCGWA.COMM_AREA.CANCEL_IND != 'Y') 
            || (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            && DCCURHOL.DATA.ABC == 'Y')) {
            DDRHLD.REL_BR = DCCURHOL.DATA.CHG_BR;
            DDRHLD.REL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        } else {
            T000_GROUP_DDTHLDR_RHLD();
            if (pgmRtn) return;
            if (WS_HLDR_RHLD_CNT == 0) {
                DDRHLD.REL_BR = 0;
                DDRHLD.REL_DATE = 0;
            } else {
                T000_GET_DDTHLDR_REC();
                if (pgmRtn) return;
                DDRHLD.REL_BR = DDRHLDR.CRT_BR;
                DDRHLD.REL_DATE = DDRHLDR.CRT_DATE;
            }
        }
        DDRHLD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRHLD.UPDTBL_TIME = SCCGWA.COMM_AREA.TR_TIME;
        DDRHLD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DDTHLD();
        if (pgmRtn) return;
    }
    public void R000_CCY_SMST_PROC() throws IOException,SQLException,Exception {
        if (WS_AC_APP.equalsIgnoreCase("DD")) {
            WS_AVL_HLD_AMT = DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL + DDRCCY.CCAL_TOT_BAL - DDRCCY.MARGIN_BAL;
        } else {
            WS_AVL_HLD_AMT = TDRSMST.BAL - TDRSMST.HBAL - TDRSMST.GUAR_BAL;
        }
        if (WS_DOWN_HLD_NO.trim().length() > 0 
            && WS_AVL_HLD_AMT > 0) {
            IBS.init(SCCGWA, DDRHLD);
            DDRHLD.KEY.HLD_NO = WS_DOWN_HLD_NO;
            T000_READ_DDTHLD();
            if (pgmRtn) return;
            while (WS_CIRCLE_FLG != 'Y') {
                if (DDRHLD.HLD_STS == 'W') {
                    if (DDRHLD.HLD_TYP == '1') {
                        WS_CIRCLE_FLG = 'Y';
                        DDRHLD.HLD_STS = 'N';
                        DDRHLD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                        DDRHLD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                        T000_REWRITE_DDTHLD();
                        if (pgmRtn) return;
                    } else {
                        WS_AVL_HLD_AMT = WS_AVL_HLD_AMT - DDRHLD.HLD_AMT;
                        if (WS_AC_APP.equalsIgnoreCase("DD")) {
                            DDRCCY.HOLD_BAL = DDRCCY.HOLD_BAL + DDRHLD.HLD_AMT;
                        } else {
                            TDRSMST.HBAL = TDRSMST.HBAL + DDRHLD.HLD_AMT;
                        }
                        DDRHLD.HLD_STS = 'N';
                        DDRHLD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                        DDRHLD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                        T000_REWRITE_DDTHLD();
                        if (pgmRtn) return;
                        if (WS_AVL_HLD_AMT <= 0) {
                            WS_CIRCLE_FLG = 'Y';
                        }
                    }
                }
                if (DDRHLD.DOWN_HLD_NO.trim().length() == 0) {
                    WS_CIRCLE_FLG = 'Y';
                } else {
                    DDRHLD.KEY.HLD_NO = DDRHLD.DOWN_HLD_NO;
                    T000_READ_DDTHLD();
                    if (pgmRtn) return;
                }
            }
        }
        CEP.TRC(SCCGWA, WS_STS_CHANGE_FLG);
        CEP.TRC(SCCGWA, WS_UP_HLD_NO);
        CEP.TRC(SCCGWA, WS_DOWN_HLD_NO);
        if (WS_STS_CHANGE_FLG == 'Y') {
            if (WS_UP_HLD_NO.trim().length() > 0) {
                IBS.init(SCCGWA, DDRHLD);
                DDRHLD.KEY.HLD_NO = WS_UP_HLD_NO;
                T000_READ_DDTHLD();
                if (pgmRtn) return;
                DDRHLD.DOWN_HLD_NO = WS_DOWN_HLD_NO;
                DDRHLD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DDRHLD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                T000_REWRITE_DDTHLD();
                if (pgmRtn) return;
            }
            if (WS_DOWN_HLD_NO.trim().length() > 0) {
                IBS.init(SCCGWA, DDRHLD);
                DDRHLD.KEY.HLD_NO = WS_DOWN_HLD_NO;
                T000_READ_DDTHLD();
                if (pgmRtn) return;
                DDRHLD.UP_HLD_NO = WS_UP_HLD_NO;
                DDRHLD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DDRHLD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                T000_REWRITE_DDTHLD();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_CCY_SMST_PROC_EC() throws IOException,SQLException,Exception {
        if (WS_AC_APP.equalsIgnoreCase("DD")) {
            WS_AVL_HLD_AMT = DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL + DDRCCY.CCAL_TOT_BAL - DDRCCY.MARGIN_BAL;
        } else {
            WS_AVL_HLD_AMT = TDRSMST.BAL - TDRSMST.HBAL - TDRSMST.GUAR_BAL;
        }
        if (WS_DOWN_HLD_NO.trim().length() == 0) {
            IBS.init(SCCGWA, DDRHLD);
            DDRHLD.KEY.HLD_NO = WS_UP_HLD_NO;
            T000_READ_DDTHLD();
            if (pgmRtn) return;
            if (!DDRHLD.DOWN_HLD_NO.equalsIgnoreCase(WS_DOWN_HLD_NO)) {
                WS_DOWN_HLD_NO = DDRHLD.DOWN_HLD_NO;
            }
        }
        if (WS_DOWN_HLD_NO.trim().length() > 0) {
            IBS.init(SCCGWA, DDRHLD);
            DDRHLD.KEY.HLD_NO = WS_DOWN_HLD_NO;
            T000_READ_DDTHLD();
            if (pgmRtn) return;
            while (WS_CIRCLE_FLG != 'Y') {
                if (DDRHLD.HLD_STS == 'N') {
                    if (DDRHLD.DOWN_HLD_NO.trim().length() > 0) {
                        DDRHLD.KEY.HLD_NO = DDRHLD.DOWN_HLD_NO;
                        T000_READ_DDTHLD();
                        if (pgmRtn) return;
                    } else {
                        WS_EC_HLD_NO = DDRHLD.KEY.HLD_NO;
                        WS_CIRCLE_FLG = 'Y';
                    }
                } else {
                    if (DDRHLD.HLD_STS == 'W') {
                        WS_EC_HLD_NO = DDRHLD.UP_HLD_NO;
                        WS_CIRCLE_FLG = 'Y';
                    }
                }
            }
        }
        if (WS_EC_HLD_NO.trim().length() > 0 
            && WS_AVL_HLD_AMT < 0) {
            IBS.init(SCCGWA, DDRHLD);
            WS_CIRCLE_FLG = ' ';
            DDRHLD.KEY.HLD_NO = WS_EC_HLD_NO;
            T000_READ_DDTHLD();
            if (pgmRtn) return;
            while (WS_CIRCLE_FLG != 'Y') {
                if (DDRHLD.HLD_TYP == '1') {
                    DDRHLD.HLD_STS = 'W';
                    DDRHLD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    DDRHLD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                    T000_REWRITE_DDTHLD();
                    if (pgmRtn) return;
                    if (WS_AVL_HLD_AMT >= 0) {
                        WS_CIRCLE_FLG = 'Y';
                    }
                } else {
                    WS_AVL_HLD_AMT = WS_AVL_HLD_AMT + DDRHLD.HLD_AMT;
                    if (WS_AC_APP.equalsIgnoreCase("DD")) {
                        DDRCCY.HOLD_BAL = DDRCCY.HOLD_BAL - DDRHLD.HLD_AMT;
                    } else {
                        TDRSMST.HBAL = TDRSMST.HBAL - DDRHLD.HLD_AMT;
                    }
                    DDRHLD.HLD_STS = 'W';
                    DDRHLD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    DDRHLD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                    T000_REWRITE_DDTHLD();
                    if (pgmRtn) return;
                    if (WS_AVL_HLD_AMT >= 0) {
                        WS_CIRCLE_FLG = 'Y';
                    }
                }
                DDRHLD.KEY.HLD_NO = DDRHLD.UP_HLD_NO;
                T000_READ_DDTHLD();
                if (pgmRtn) return;
            }
        }
        if (WS_STS_CHANGE_FLG == 'Y') {
            if (WS_UP_HLD_NO.trim().length() > 0) {
                IBS.init(SCCGWA, DDRHLD);
                DDRHLD.KEY.HLD_NO = WS_UP_HLD_NO;
                T000_READ_DDTHLD();
                if (pgmRtn) return;
                DDRHLD.DOWN_HLD_NO = WS_HLD_NO;
                DDRHLD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DDRHLD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                T000_REWRITE_DDTHLD();
                if (pgmRtn) return;
            }
            if (WS_DOWN_HLD_NO.trim().length() > 0) {
                IBS.init(SCCGWA, DDRHLD);
                DDRHLD.KEY.HLD_NO = WS_DOWN_HLD_NO;
                T000_READ_DDTHLD();
                if (pgmRtn) return;
                DDRHLD.UP_HLD_NO = WS_HLD_NO;
                DDRHLD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DDRHLD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                T000_REWRITE_DDTHLD();
                if (pgmRtn) return;
            }
        }
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
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.col = "CUS_AC,AC_STS,AC_STS_WORD,AC_TYPE, LAST_DATE,LAST_TLR,UPDTBL_DATE,UPDTBL_TLR";
        DDTMST_RD.upd = true;
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DD_AC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
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
    public void T000_READ_DDTHLDR() throws IOException,SQLException,Exception {
        DDTHLDR_RD = new DBParm();
        DDTHLDR_RD.TableName = "DDTHLDR";
        DDTHLDR_RD.upd = true;
        IBS.READ(SCCGWA, DDRHLDR, DDTHLDR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_GROUP_DDTHLDR_RHLD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHLDR);
        DDRHLDR.KEY.HLD_NO = DCCURHOL.DATA.HLD_NO;
        DDTHLDR_RD = new DBParm();
        DDTHLDR_RD.TableName = "DDTHLDR";
        DDTHLDR_RD.set = "WS-HLDR-RHLD-CNT=COUNT(*)";
        DDTHLDR_RD.where = "HLD_NO = :DDRHLDR.KEY.HLD_NO "
            + "AND HLD_TYP IN ( '4' , '5' ) "
            + "AND REVERSE_FLG < > 'Y'";
        IBS.GROUP(SCCGWA, DDRHLDR, this, DDTHLDR_RD);
    }
    public void T000_GET_DDTHLDR_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHLDR);
        DDRHLDR.KEY.HLD_NO = DCCURHOL.DATA.HLD_NO;
        DDTHLDR_RD = new DBParm();
        DDTHLDR_RD.TableName = "DDTHLDR";
        DDTHLDR_RD.where = "HLD_NO = :DDRHLDR.KEY.HLD_NO "
            + "AND HLD_TYP IN ( '4' , '5' , '6' ) "
            + "AND REVERSE_FLG < > 'Y'";
        DDTHLDR_RD.fst = true;
        DDTHLDR_RD.order = "TR_DATE DESC, TR_JRNNO DESC";
        IBS.READ(SCCGWA, DDRHLDR, this, DDTHLDR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DDTHLDR() throws IOException,SQLException,Exception {
        DDTHLDR_RD = new DBParm();
        DDTHLDR_RD.TableName = "DDTHLDR";
        IBS.REWRITE(SCCGWA, DDRHLDR, DDTHLDR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DDTHLD() throws IOException,SQLException,Exception {
        DDTHLD_RD = new DBParm();
        DDTHLD_RD.TableName = "DDTHLD";
        IBS.REWRITE(SCCGWA, DDRHLD, DDTHLD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_GROUP_DDTHLD_AC_HLD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHLD);
        DDRHLD.AC = WS_ACAC;
        DDTHLD_RD = new DBParm();
        DDTHLD_RD.TableName = "DDTHLD";
        DDTHLD_RD.set = "WS-LAW-AC-HLD-CNT=COUNT(*)";
        DDTHLD_RD.where = "AC = :DDRHLD.AC "
            + "AND SPR_BR_TYP = '1' "
            + "AND HLD_TYP = '1' "
            + "AND HLD_FLG = '1' "
            + "AND ( HLD_STS = 'N' "
            + "OR HLD_STS = 'W' )";
        IBS.GROUP(SCCGWA, DDRHLD, this, DDTHLD_RD);
        DDTHLD_RD = new DBParm();
        DDTHLD_RD.TableName = "DDTHLD";
        DDTHLD_RD.set = "WS-LAW-AC-FBID-CNT=COUNT(*)";
        DDTHLD_RD.where = "AC = :DDRHLD.AC "
            + "AND SPR_BR_TYP = '1' "
            + "AND HLD_TYP = '1' "
            + "AND HLD_FLG = '2' "
            + "AND ( HLD_STS = 'N' "
            + "OR HLD_STS = 'W' )";
        IBS.GROUP(SCCGWA, DDRHLD, this, DDTHLD_RD);
    }
    public void T000_REWRITE_DDTCCY() throws IOException,SQLException,Exception {
        DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRCCY.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.REWRITE(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void T000_REWRITE_TDTSMST() throws IOException,SQLException,Exception {
        TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRSMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.REWRITE(SCCGWA, TDRSMST, TDTSMST_RD);
    }
    public void T000_READ_DDTZMAC() throws IOException,SQLException,Exception {
        DDTZMAC_RD = new DBParm();
        DDTZMAC_RD.TableName = "DDTZMAC";
        DDTZMAC_RD.where = "HLD_NO = :DDRZMAC.HLD_NO "
            + "AND HLD_STS = 'N'";
        IBS.READ(SCCGWA, DDRZMAC, this, DDTZMAC_RD);
    }
    public void T000_STARTBR_DDTZMAC() throws IOException,SQLException,Exception {
        DDTZMAC_BR.rp = new DBParm();
        DDTZMAC_BR.rp.TableName = "DDTZMAC";
        DDTZMAC_BR.rp.where = "REF_NO = :DDRZMAC.KEY.REF_NO "
            + "AND HLD_STS = 'N'";
        DDTZMAC_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, DDRZMAC, this, DDTZMAC_BR);
    }
    public void T000_READNEXT_DDTZMAC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRZMAC, this, DDTZMAC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ZMAC_FLG = 'Y';
        } else {
            WS_ZMAC_FLG = 'N';
        }
    }
    public void T000_ENDBR_DDTZMAC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTZMAC_BR);
    }
    public void T000_REWRITE_DDTZMAC() throws IOException,SQLException,Exception {
        DDTZMAC_RD = new DBParm();
        DDTZMAC_RD.TableName = "DDTZMAC";
        IBS.REWRITE(SCCGWA, DDRZMAC, DDTZMAC_RD);
    }
    public void T000_STARTBR_DDTCCZM() throws IOException,SQLException,Exception {
        DDTCCZM_BR.rp = new DBParm();
        DDTCCZM_BR.rp.TableName = "DDTCCZM";
        DDTCCZM_BR.rp.where = "REF_NO = :DDRCCZM.KEY.REF_NO";
        DDTCCZM_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, DDRCCZM, this, DDTCCZM_BR);
    }
    public void T000_READNEXT_DDTCCZM() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRCCZM, this, DDTCCZM_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CCZM_FLG = 'Y';
        } else {
            WS_CCZM_FLG = 'N';
        }
    }
    public void T000_ENDBR_DDTCCZM() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTCCZM_BR);
    }
    public void T000_REWRITE_DDTCCZM() throws IOException,SQLException,Exception {
        DDTCCZM_RD = new DBParm();
        DDTCCZM_RD.TableName = "DDTCCZM";
        IBS.REWRITE(SCCGWA, DDRCCZM, DDTCCZM_RD);
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
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
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
