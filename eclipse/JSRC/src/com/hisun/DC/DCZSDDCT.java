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
import com.hisun.SC.SCCMSG;
import com.hisun.AI.*;
import com.hisun.CI.*;
import com.hisun.BP.*;
import com.hisun.GD.*;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSDDCT {
    int JIBS_tmp_int;
    DBParm DDTHLD_RD;
    DBParm DDTMST_RD;
    DBParm TDTSMST_RD;
    DBParm DDTCCY_RD;
    brParm GDTPLDR_BR = new brParm();
    DBParm DDTHLDR_RD;
    String K_OUTPUT_FMT = "DC778";
    String K_STS_TABLE_APP1 = "DD";
    String K_STS_TABLE_APP2 = "TD";
    String K_CHK_STS_TBL = "7580";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    double WS_HAMT = 0;
    double WS_USED_AMT = 0;
    String WS_HLD_NO = " ";
    String WS_HLD_AC = " ";
    char WS_HLD_STS = ' ';
    char WS_HLD_TYP = ' ';
    char WS_HLD_FLG = ' ';
    double WS_HLD_AMT = 0;
    char WS_SPR_TYP = ' ';
    double WS_REM_AMT = 0;
    double WS_GD_RAMT = 0;
    String WS_RVS_NO = " ";
    double WS_CCAL_BAL = 0;
    double WS_CURR_BAL = 0;
    double WS_AVA_BAL = 0;
    char WS_LAW_FBID_FLG = ' ';
    String WS_LAW_HLD_NO = " ";
    String WS_CARD_NO = " ";
    char WS_DDTCCY_REC = ' ';
    char WS_TDTSMST_REC = ' ';
    char WS_GDTPLDR_REC = ' ';
    char WS_CIRCLE_FLG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DDCUHDRA DDCUHDRA = new DDCUHDRA();
    TDCACDRU TDCACDRU = new TDCACDRU();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    AICUUPIA AICUUPIA = new AICUUPIA();
    CICACCU CICACCU = new CICACCU();
    DCCURHLD DCCURHLD = new DCCURHLD();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    DCCUCINF DCCUCINF = new DCCUCINF();
    DDCIMPAY DDCIMPAY = new DDCIMPAY();
    DCCPCDCK DCCPCDCK = new DCCPCDCK();
    CICQACAC CICQACAC = new CICQACAC();
    CICQACRI CICQACRI = new CICQACRI();
    CICQACRL CICQACRL = new CICQACRL();
    CICCUST CICCUST = new CICCUST();
    TDCUGRP TDCUGRP = new TDCUGRP();
    DDCIBACK DDCIBACK = new DDCIBACK();
    DDRHLD DDRHLD = new DDRHLD();
    DDRHLDR DDRHLDR = new DDRHLDR();
    DDRCCY DDRCCY = new DDRCCY();
    DDRMST DDRMST = new DDRMST();
    GDRPLDR GDRPLDR = new GDRPLDR();
    TDRSMST TDRSMST = new TDRSMST();
    DCCODDCT DCCODDCT = new DCCODDCT();
    SCCGWA SCCGWA;
    DCCSDDCT DCCSDDCT;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA, DCCSDDCT DCCSDDCT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSDDCT = DCCSDDCT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCZSDDCT return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (DCCSDDCT.INPUT_DATA.FUNC == '1') {
            B020_DEDUCT_STRAIGH_PROC();
        } else if (DCCSDDCT.INPUT_DATA.FUNC == '2') {
            B030_DEDUCT_RELEASE_PROC();
        } else if (DCCSDDCT.INPUT_DATA.FUNC == '3') {
            B040_DEDUCT_RELFBID_PROC();
        } else {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_INPUT_ERR;
            S000_ERR_MSG_PROC();
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B050_OUTPUT_DEDDCT_INF();
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (DCCSDDCT.INPUT_DATA.FUNC == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DUT_TYP_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DCCSDDCT.INPUT_DATA.FUNC != '1' 
            && DCCSDDCT.INPUT_DATA.FUNC != '2' 
            && DCCSDDCT.INPUT_DATA.FUNC != '3') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DUT_TYP_INVALID;
            S000_ERR_MSG_PROC();
        }
        if (DCCSDDCT.INPUT_DATA.FUNC == '2' 
            || DCCSDDCT.INPUT_DATA.FUNC == '3') {
            CEP.TRC(SCCGWA, DCCSDDCT.INPUT_DATA.HLD_NO);
            if (DCCSDDCT.INPUT_DATA.HLD_NO.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLDNO_MUST_INPUT;
                S000_ERR_MSG_PROC();
            }
            IBS.init(SCCGWA, DDRHLD);
            DDRHLD.KEY.HLD_NO = DCCSDDCT.INPUT_DATA.HLD_NO;
            T000_READ_DDTHLD();
            WS_HLD_AC = DDRHLD.AC;
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                if (DDRHLD.HLD_STS == 'C') {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_STS_C_CNOT_TR;
                    S000_ERR_MSG_PROC();
                }
                if (DDRHLD.HLD_STS == 'W') {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_STS_W_CNOT_TR;
                    S000_ERR_MSG_PROC();
                }
            }
            if (DDRHLD.HLD_FLG == '1') {
                if (DDRHLD.HLD_CLS != '1' 
                    && DDRHLD.SPR_BR_TYP == '2') {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BANK_NOT_JDKH;
                    S000_ERR_MSG_PROC();
                }
            }
        }
        CEP.TRC(SCCGWA, DCCSDDCT.INPUT_DATA.AC);
        CEP.TRC(SCCGWA, DCCSDDCT.INPUT_DATA.CCY);
        CEP.TRC(SCCGWA, DCCSDDCT.INPUT_DATA.CCY_TYPE);
        CEP.TRC(SCCGWA, DCCSDDCT.INPUT_DATA.ST_MTH);
        CEP.TRC(SCCGWA, DCCSDDCT.INPUT_DATA.CR_AC);
        if (DCCSDDCT.INPUT_DATA.AC.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_AC_NO;
            S000_ERR_MSG_PROC();
        }
        if (DCCSDDCT.INPUT_DATA.CCY.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ACCCY_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DCCSDDCT.INPUT_DATA.CCY_TYPE == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CCY_TYP_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DCCSDDCT.INPUT_DATA.ST_MTH == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ST_MTH_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DCCSDDCT.INPUT_DATA.ST_MTH == '1') {
            if (DCCSDDCT.INPUT_DATA.CR_AC.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CR_ACNO_MUST_INPUT;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B020_DEDUCT_STRAIGH_PROC() throws IOException,SQLException,Exception {
        R_CHK_PROC();
        R_CHK_CUSAC_PROC();
        R_GET_CI_INFO();
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            R_COM_AMT_PROC();
        }
        R_DD_OR_TD_HDRA_PROC();
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
                R_BACK_VALUE_PROC();
            }
        }
        R_CR_AC_PROC();
        R_WRITE_DDTHLDR_PROC();
    }
    public void B030_DEDUCT_RELEASE_PROC() throws IOException,SQLException,Exception {
        R_GET_HOLD_INF();
        R_CHK_PROC();
        R_CHK_CUSAC_PROC();
        R_GET_CI_INFO();
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            R_COMPUTE_AMT();
        }
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("TD") 
            && SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            R_DD_OR_TD_HDRA_PROC();
            R_RELEASE_PROC();
        } else {
            R_RELEASE_PROC();
            R_DD_OR_TD_HDRA_PROC();
        }
        R_CR_AC_PROC();
    }
    public void B040_DEDUCT_RELFBID_PROC() throws IOException,SQLException,Exception {
        R_GET_HOLD_INF();
        R_CHK_PROC();
        R_CHK_CUSAC_PROC();
        R_GET_CI_INFO();
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            R_COMPUTE_AMT();
        }
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("TD") 
            && SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            R_DD_OR_TD_HDRA_PROC();
            R_RELEASE_PROC();
        } else {
            R_RELEASE_PROC();
            R_DD_OR_TD_HDRA_PROC();
        }
        R_CR_AC_PROC();
    }
    public void B050_OUTPUT_DEDDCT_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCODDCT.OUTPUT_DATA);
        DCCODDCT.OUTPUT_DATA.O_HLD_NO = DCCSDDCT.INPUT_DATA.HLD_NO;
        DCCODDCT.OUTPUT_DATA.O_AC = DCCSDDCT.INPUT_DATA.AC;
        DCCODDCT.OUTPUT_DATA.O_AC_SEQ = DCCSDDCT.INPUT_DATA.AC_SEQ;
        DCCODDCT.OUTPUT_DATA.O_CCY = DCCSDDCT.INPUT_DATA.CCY;
        DCCODDCT.OUTPUT_DATA.O_CCY_TYPE = DCCSDDCT.INPUT_DATA.CCY_TYPE;
        DCCODDCT.OUTPUT_DATA.O_HLD_TYP = WS_HLD_TYP;
        DCCODDCT.OUTPUT_DATA.O_BEF_AMT = WS_HLD_AMT;
        DCCODDCT.OUTPUT_DATA.O_TAMT = DCCSDDCT.INPUT_DATA.TAMT;
        DCCODDCT.OUTPUT_DATA.O_RHLD_TYP = DCCSDDCT.INPUT_DATA.RHLD_TYP;
        DCCODDCT.OUTPUT_DATA.O_REM_AMT = WS_REM_AMT;
        DCCODDCT.OUTPUT_DATA.O_CHG_NO = DCCSDDCT.INPUT_DATA.CHG_NO;
        DCCODDCT.OUTPUT_DATA.O_RSN = DCCSDDCT.INPUT_DATA.RSN;
        DCCODDCT.OUTPUT_DATA.O_ST_MTH = DCCSDDCT.INPUT_DATA.ST_MTH;
        if (DCCSDDCT.INPUT_DATA.ST_MTH == '1') {
            DCCODDCT.OUTPUT_DATA.O_CR_AC = DCCSDDCT.INPUT_DATA.CR_AC;
        }
        DCCODDCT.OUTPUT_DATA.O_RMK = DCCSDDCT.INPUT_DATA.RMK;
        DCCODDCT.OUTPUT_DATA.O_CHG_BR = DCCSDDCT.INPUT_DATA.CHG_BR;
        DCCODDCT.OUTPUT_DATA.O_LAW_NM1 = DCCSDDCT.INPUT_DATA.LAW_NM1;
        DCCODDCT.OUTPUT_DATA.O_LAW_NO1 = DCCSDDCT.INPUT_DATA.LAW_NO1;
        DCCODDCT.OUTPUT_DATA.O_LAW_NM2 = DCCSDDCT.INPUT_DATA.LAW_NM2;
        DCCODDCT.OUTPUT_DATA.O_LAW_NO2 = DCCSDDCT.INPUT_DATA.LAW_NO2;
        DCCODDCT.OUTPUT_DATA.O_RVS_NO = WS_RVS_NO;
        DCCSDDCT.RVS_NO = WS_RVS_NO;
        DCCODDCT.OUTPUT_DATA.O_NARRATIVE = DCCSDDCT.INPUT_DATA.NARRATIVE;
        DCCODDCT.OUTPUT_DATA.O_CURR_BAL = WS_CURR_BAL;
        DCCODDCT.OUTPUT_DATA.O_AVA_BAL = WS_AVA_BAL;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DCCODDCT.OUTPUT_DATA;
        SCCFMT.DATA_LEN = 1297;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R_CHK_PROC() throws IOException,SQLException,Exception {
        WS_CARD_NO = DCCSDDCT.INPUT_DATA.AC;
        CEP.TRC(SCCGWA, WS_CARD_NO);
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.FUNC = 'I';
        CICQACRL.DATA.AC_NO = WS_CARD_NO;
        S000_CALL_CIZQACRL();
        CEP.TRC(SCCGWA, CICQACRL.RC.RC_CODE);
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_AC_REL);
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
        if (CICQACRL.RC.RC_CODE == 8054) {
            WS_CARD_NO = CICQACRL.DATA.AC_NO;
        } else if (CICQACRL.RC.RC_CODE == 0 
                && CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("13")) {
            WS_CARD_NO = CICQACRL.O_DATA.O_REL_AC_NO;
        } else {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACRL.RC);
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = WS_CARD_NO;
        CICQACAC.DATA.AGR_SEQ = DCCSDDCT.INPUT_DATA.AC_SEQ;
        CICQACAC.DATA.CCY_ACAC = DCCSDDCT.INPUT_DATA.CCY;
        CICQACAC.DATA.CR_FLG = DCCSDDCT.INPUT_DATA.CCY_TYPE;
        S000_CALL_CIZQACAC();
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
    }
    public void R_CHK_CUSAC_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR);
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DC")) {
            IBS.init(SCCGWA, DCCUCINF);
            DCCUCINF.CARD_NO = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
            S000_CALL_DCZUCINF();
        }
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
            T000_READ_DDTMST();
            if (DDRMST.AC_STS == 'C') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
                S000_ERR_MSG_PROC();
            }
        }
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("TD")) {
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            T000_READ_TDTSMST();
            if (TDRSMST.ACO_STS == 'C' 
                || TDRSMST.ACO_STS == '1') {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED;
                S000_ERR_MSG_PROC();
            }
        }
        R_CHECK_PSW_PROC();
    }
    public void R_CHECK_PSW_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO);
        CEP.TRC(SCCGWA, DCCSDDCT.INPUT_DATA.PSWD);
        CEP.TRC(SCCGWA, DCCSDDCT.INPUT_DATA.TRK_DATA2);
        CEP.TRC(SCCGWA, DCCSDDCT.TRK_DATA3);
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DC") 
            && DCCSDDCT.INPUT_DATA.PSWD.trim().length() > 0 
            && DCCSDDCT.INPUT_DATA.TRK_DATA2.trim().length() > 0 
            && DCCSDDCT.TRK_DATA3.trim().length() > 0) {
            IBS.init(SCCGWA, DCCPCDCK);
            DCCPCDCK.FUNC_CODE = 'B';
            DCCPCDCK.CARD_NO = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
            DCCPCDCK.CARD_PSW = DCCSDDCT.INPUT_DATA.PSWD;
            DCCPCDCK.TRK2_DAT = DCCSDDCT.INPUT_DATA.TRK_DATA2;
            DCCPCDCK.TRK3_DAT = DCCSDDCT.TRK_DATA3;
            S000_CALL_DCZPCDCK();
        }
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD") 
            && DCCSDDCT.INPUT_DATA.PSWD.trim().length() > 0) {
            IBS.init(SCCGWA, DDCIMPAY);
            DDCIMPAY.AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
            DDCIMPAY.PSWD_OLD = DCCSDDCT.INPUT_DATA.PSWD;
            DDCIMPAY.FUNC = 'C';
            DDCIMPAY.PAY_MTH = '1';
            S000_CALL_DDZIMPAY();
        }
    }
    public void R_GET_CI_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'C';
        CICCUST.DATA.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        S000_CALL_CIZCUST();
    }
    public void R_COM_AMT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHLD);
        WS_HAMT = 0;
        WS_HLD_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRHLD.AC = WS_HLD_AC;
        T000_READ_DDTHLD_LAST();
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_HAMT = 0;
        } else {
            if (DDRHLD.SPR_BR_TYP == '1') {
                WS_LAW_HLD_NO = DDRHLD.KEY.HLD_NO;
            } else {
                while (WS_CIRCLE_FLG != 'Y') {
                    CEP.TRC(SCCGWA, DDRHLD.UP_HLD_NO);
                    if (DDRHLD.UP_HLD_NO.trim().length() == 0) {
                        WS_CIRCLE_FLG = 'Y';
                    } else {
                        DDRHLD.KEY.HLD_NO = DDRHLD.UP_HLD_NO;
                        T000_READ_DDTHLD();
                        if (DDRHLD.SPR_BR_TYP == '1') {
                            WS_LAW_HLD_NO = DDRHLD.KEY.HLD_NO;
                            WS_CIRCLE_FLG = 'Y';
                        }
                    }
                }
            }
            CEP.TRC(SCCGWA, WS_LAW_HLD_NO);
            if (WS_LAW_HLD_NO.trim().length() == 0) {
                WS_HAMT = 0;
            } else {
                WS_CIRCLE_FLG = ' ';
                IBS.init(SCCGWA, DDRHLD);
                DDRHLD.AC = WS_HLD_AC;
                T000_READ_DDTHLD_FIRST();
                if (DDRHLD.HLD_STS == 'N') {
                    if (DDRHLD.HLD_TYP == '1') {
                        WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_HOLD_OTH;
                        S000_ERR_MSG_PROC();
                    } else {
                        WS_HAMT = WS_HAMT + DDRHLD.HLD_AMT;
                    }
                    CEP.TRC(SCCGWA, DDRHLD.DOWN_HLD_NO);
                    if (DDRHLD.DOWN_HLD_NO.trim().length() > 0) {
                        while (WS_CIRCLE_FLG != 'Y') {
                            DDRHLD.KEY.HLD_NO = DDRHLD.DOWN_HLD_NO;
                            T000_READ_DDTHLD();
                            CEP.TRC(SCCGWA, DDRHLD.KEY.HLD_NO);
                            CEP.TRC(SCCGWA, DDRHLD.DOWN_HLD_NO);
                            CEP.TRC(SCCGWA, DDRHLD.SPR_BR_TYP);
                            CEP.TRC(SCCGWA, DDRHLD.HLD_STS);
                            CEP.TRC(SCCGWA, DDRHLD.HLD_TYP);
                            CEP.TRC(SCCGWA, DDRHLD.HLD_AMT);
                            if (DDRHLD.DOWN_HLD_NO.trim().length() == 0) {
                                WS_CIRCLE_FLG = 'Y';
                            }
                            if (!DDRHLD.KEY.HLD_NO.equalsIgnoreCase(WS_LAW_HLD_NO) 
                                && DDRHLD.SPR_BR_TYP == '1') {
                                if (DDRHLD.HLD_STS == 'W') {
                                    WS_CIRCLE_FLG = 'Y';
                                } else {
                                    if (DDRHLD.HLD_TYP == '1') {
                                        WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_HOLD_OTH;
                                        S000_ERR_MSG_PROC();
                                    } else {
                                        WS_HAMT = WS_HAMT + DDRHLD.HLD_AMT;
                                        if (DDRHLD.DOWN_HLD_NO.trim().length() == 0) {
                                            WS_CIRCLE_FLG = 'Y';
                                        }
                                    }
                                }
                            }
                            if (DDRHLD.KEY.HLD_NO.equalsIgnoreCase(WS_LAW_HLD_NO) 
                                && DDRHLD.SPR_BR_TYP == '1') {
                                WS_CIRCLE_FLG = 'Y';
                                if (DDRHLD.HLD_STS == 'N') {
                                    if (DDRHLD.HLD_TYP == '1') {
                                        CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_AC_HOLD_OTH);
                                    } else {
                                        WS_HAMT = WS_HAMT + DDRHLD.HLD_AMT;
                                    }
                                }
                            }
                            CEP.TRC(SCCGWA, WS_HAMT);
                        }
                    }
                } else {
                    WS_HAMT = 0;
                }
            }
        }
        CEP.TRC(SCCGWA, WS_HAMT);
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
            R_CHECK_DD_AMT_PROC();
        }
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("TD")) {
            R_CHECK_TD_AMT_PROC();
        }
    }
    public void R_CHECK_DD_AMT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READ_DDTCCY();
        R_CHECK_STS_TBL();
        WS_USED_AMT = DDRCCY.CURR_BAL + DDRCCY.CCAL_TOT_BAL - WS_HAMT;
        CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
        CEP.TRC(SCCGWA, WS_HAMT);
        CEP.TRC(SCCGWA, WS_USED_AMT);
        if (WS_USED_AMT < DCCSDDCT.INPUT_DATA.TAMT) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_UAMT_LESS_TAMT;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, DDRCCY.CCAL_TOT_BAL);
        if (DDRCCY.CCAL_TOT_BAL > 0) {
            WS_CCAL_BAL = DCCSDDCT.INPUT_DATA.TAMT - ( DDRCCY.CURR_BAL - WS_HAMT );
            CEP.TRC(SCCGWA, WS_CCAL_BAL);
            if (WS_CCAL_BAL >= 0) {
            } else {
                WS_CCAL_BAL = 0;
                CEP.TRC(SCCGWA, WS_CCAL_BAL);
            }
        }
    }
    public void R_CHECK_TD_AMT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READ_TDTSMST();
        R_CHECK_STS_TBL();
        CEP.TRC(SCCGWA, TDRSMST.ACO_STS);
        if (TDRSMST.ACO_STS == 'C' 
            || TDRSMST.ACO_STS == 'R' 
            || TDRSMST.ACO_STS == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED2;
            S000_ERR_MSG_PROC();
        }
        WS_USED_AMT = TDRSMST.BAL - WS_HAMT;
        CEP.TRC(SCCGWA, TDRSMST.BAL);
        CEP.TRC(SCCGWA, WS_HAMT);
        CEP.TRC(SCCGWA, WS_USED_AMT);
        CEP.TRC(SCCGWA, DCCSDDCT.INPUT_DATA.TAMT);
        if (WS_USED_AMT < DCCSDDCT.INPUT_DATA.TAMT) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_UAMT_LESS_TAMT;
            S000_ERR_MSG_PROC();
        }
    }
    public void R_CHECK_STS_TBL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFCSTS);
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
            BPCFCSTS.AP_MMO = K_STS_TABLE_APP1;
            if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
            JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
            for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 101 - 1) + DDRCCY.STS_WORD + BPCFCSTS.STATUS_WORD.substring(101 + 99 - 1);
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
                WS_LAW_FBID_FLG = '1';
            }
        }
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("TD")) {
            BPCFCSTS.AP_MMO = K_STS_TABLE_APP2;
            if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
            JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
            for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 101 - 1) + TDRSMST.STSW + BPCFCSTS.STATUS_WORD.substring(101 + 99 - 1);
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
                WS_LAW_FBID_FLG = '1';
            }
        }
        BPCFCSTS.TBL_NO = K_CHK_STS_TBL;
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        if (CICACCU.DATA.CI_STSW == null) CICACCU.DATA.CI_STSW = "";
        JIBS_tmp_int = CICACCU.DATA.CI_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICACCU.DATA.CI_STSW += " ";
        BPCFCSTS.STATUS_WORD = CICACCU.DATA.CI_STSW + BPCFCSTS.STATUS_WORD.substring(80);
        if (DCCSDDCT.INPUT_DATA.FUNC == '3' 
            || (DCCSDDCT.INPUT_DATA.FUNC == '2' 
            && WS_LAW_FBID_FLG == '1')) {
        } else {
            S000_CALL_BPZFCSTS();
        }
    }
    public void R000_GET_GD_RAMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRPLDR);
        GDRPLDR.KEY.AC = DCCSDDCT.INPUT_DATA.AC;
        GDRPLDR.KEY.AC_SEQ = DCCSDDCT.INPUT_DATA.AC_SEQ;
        T000_STARTBR_GDTPLDR();
        T000_READNEXT_GDTPLDR();
        while (WS_GDTPLDR_REC != 'N') {
            WS_GD_RAMT = WS_GD_RAMT + GDRPLDR.RELAT_AMT;
            T000_READNEXT_GDTPLDR();
        }
        T000_ENDBR_GDTPLDR();
    }
    public void R_DD_OR_TD_HDRA_PROC() throws IOException,SQLException,Exception {
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDCUHDRA);
            DDCUHDRA.AC = DCCSDDCT.INPUT_DATA.AC;
            DDCUHDRA.CCY = DCCSDDCT.INPUT_DATA.CCY;
            DDCUHDRA.CCY_TYPE = DCCSDDCT.INPUT_DATA.CCY_TYPE;
            DDCUHDRA.TX_AMT = DCCSDDCT.INPUT_DATA.TAMT;
            DDCUHDRA.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDCUHDRA.HLD_REF = DCCSDDCT.INPUT_DATA.HLD_NO;
            DDCUHDRA.REMARKS = DCCSDDCT.INPUT_DATA.RMK;
            DDCUHDRA.TX_TYPE = 'T';
            DDCUHDRA.TX_MMO = "A314";
            DDCUHDRA.OTHER_AC = DCCSDDCT.INPUT_DATA.CR_AC;
            DDCUHDRA.RLT_ACNM = DCCSDDCT.INPUT_DATA.RLT_ACNM;
            DDCUHDRA.RLT_BANK = DCCSDDCT.INPUT_DATA.RLT_BANK;
            DDCUHDRA.NARRATIVE = DCCSDDCT.INPUT_DATA.NARRATIVE;
            DDCUHDRA.CCAL_BAL = WS_CCAL_BAL;
            S000_CALL_DDZUHDRA();
            WS_CURR_BAL = DDCUHDRA.CURR_BAL;
            WS_AVA_BAL = DDCUHDRA.AVA_BAL;
            CEP.TRC(SCCGWA, WS_CURR_BAL);
            CEP.TRC(SCCGWA, WS_AVA_BAL);
        }
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("TD")) {
            IBS.init(SCCGWA, TDCACDRU);
            TDCACDRU.OPT = '1';
            TDCACDRU.MAC_CNO = DCCSDDCT.INPUT_DATA.AC;
            TDCACDRU.ACO_AC = WS_HLD_AC;
            TDCACDRU.AC_SEQ = DCCSDDCT.INPUT_DATA.AC_SEQ;
            TDCACDRU.CCY = DCCSDDCT.INPUT_DATA.CCY;
            TDCACDRU.TXN_AMT = DCCSDDCT.INPUT_DATA.TAMT;
            TDCACDRU.DRAW_MTH = '4';
            if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
            JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
            TDCACDRU.BUSI_CTLW = "1" + TDCACDRU.BUSI_CTLW.substring(1);
            TDCACDRU.TXN_MMO = "A314";
            TDCACDRU.REMARK = DCCSDDCT.INPUT_DATA.RMK;
            TDCACDRU.OPP_AC_CNO = DCCSDDCT.INPUT_DATA.CR_AC;
            if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DC")) {
                TDCACDRU.BV_TYP = '4';
            }
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            CEP.TRC(SCCGWA, TDRSMST.STSW.substring(5 - 1, 5 + 1 - 1));
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_HAS_PLEDGE;
                S000_ERR_MSG_PROC();
            }
            CEP.TRC(SCCGWA, TDRSMST.PRDAC_CD);
            if (TDRSMST.PRDAC_CD.equalsIgnoreCase("035") 
                || TDRSMST.PRDAC_CD.equalsIgnoreCase("036")) {
                R000_CALL_TDZUGRP();
            } else {
                S000_CALL_TDZACDRU();
            }
            CEP.TRC(SCCGWA, TDCACDRU.DRAW_TOT_AMT);
            CEP.TRC(SCCGWA, DCCSDDCT.INPUT_DATA.TAMT);
            if (DCCSDDCT.INPUT_DATA.TAMT >= TDCACDRU.DRAW_TOT_AMT) {
                DCCSDDCT.INPUT_DATA.TAMT = TDCACDRU.DRAW_TOT_AMT;
            }
        }
    }
    public void R000_CALL_TDZUGRP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCUGRP);
        TDCUGRP.AC_NO = DCCSDDCT.INPUT_DATA.AC;
        TDCUGRP.PROD_CD = TDRSMST.PROD_CD;
        TDCUGRP.TXN_AMT = DCCSDDCT.INPUT_DATA.TAMT;
        TDCUGRP.OP_AC = DCCSDDCT.INPUT_DATA.CR_AC;
        TDCUGRP.DRAW_TYP = '1';
        TDCUGRP.CHK_PSW = 'N';
        TDCUGRP.OPTION = "XXTKH";
        TDCUGRP.TXN_MMO = "A314";
        S000_CALL_TDZUGRP();
    }
    public void R_BACK_VALUE_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if ((GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE > 0) 
            && (GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE < SCCGWA.COMM_AREA.AC_DATE)) {
            IBS.init(SCCGWA, DDCIBACK);
            DDCIBACK.OPT = 'W';
            DDCIBACK.TX_TYPE = 'T';
            DDCIBACK.FUNC = 'T';
            DDCIBACK.AC_NO = DCCSDDCT.INPUT_DATA.AC;
            DDCIBACK.DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            DDCIBACK.CCY = DCCSDDCT.INPUT_DATA.CCY;
            DDCIBACK.CCY_TYP = DCCSDDCT.INPUT_DATA.CCY_TYPE;
            DDCIBACK.AMT = DCCSDDCT.INPUT_DATA.TAMT;
            IBS.init(SCCGWA, DDRCCY);
            DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            T000_READ_DDTCCY();
            DDCIBACK.LAST_POST_DATE = DDRCCY.LAST_POST_DATE;
            S000_CALL_DDZIBACK();
        }
    }
    public void R_CR_AC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = DCCSDDCT.INPUT_DATA.CR_AC;
        S000_CALL_CIZQACRI();
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        CEP.TRC(SCCGWA, DCCSDDCT.INPUT_DATA.ST_MTH);
        if ((CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI") 
            && DCCSDDCT.INPUT_DATA.ST_MTH != '2') 
            || (!CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI") 
            && DCCSDDCT.INPUT_DATA.ST_MTH == '2')) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ST_MTH_AC_NOT_MATCH;
            S000_ERR_MSG_PROC();
        }
        if (DCCSDDCT.INPUT_DATA.ST_MTH == '1') {
            if (!CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
                IBS.init(SCCGWA, CICACCU);
                CICACCU.DATA.AGR_NO = DCCSDDCT.INPUT_DATA.CR_AC;
                S000_CALL_CIZACCU();
            }
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("TD")) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CR_AC_NOT_TD_AC;
                S000_ERR_MSG_PROC();
            }
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_CI_TYP);
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD") 
                || CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
                if (CICQACRI.O_DATA.O_CI_TYP == '1') {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CR_AC_NOT_PER_AC;
                    S000_ERR_MSG_PROC();
                }
                IBS.init(SCCGWA, DDCUCRAC);
                DDCUCRAC.AC = DCCSDDCT.INPUT_DATA.CR_AC;
                DDCUCRAC.CCY = DCCSDDCT.INPUT_DATA.CCY;
                DDCUCRAC.CCY_TYPE = DCCSDDCT.INPUT_DATA.CCY_TYPE;
                DDCUCRAC.TX_AMT = DCCSDDCT.INPUT_DATA.TAMT;
                if (TDRSMST.PRDAC_CD.equalsIgnoreCase("035") 
                    || TDRSMST.PRDAC_CD.equalsIgnoreCase("036")) {
                    DDCUCRAC.TX_AMT = TDCUGRP.TXN_AMT_O;
                }
                DDCUCRAC.OTHER_AC = DCCSDDCT.INPUT_DATA.AC;
                DDCUCRAC.RLT_AC = DCCSDDCT.INPUT_DATA.AC;
                DDCUCRAC.OTH_TX_TOOL = DCCSDDCT.INPUT_DATA.AC;
                DDCUCRAC.TX_REF = DCCSDDCT.INPUT_DATA.HLD_NO;
                DDCUCRAC.REMARKS = DCCSDDCT.INPUT_DATA.RMK;
                DDCUCRAC.TX_MMO = "A314";
                DDCUCRAC.NARRATIVE = DCCSDDCT.INPUT_DATA.NARRATIVE;
                DDCUCRAC.TX_TYPE = 'T';
                DDCUCRAC.BANK_CR_FLG = 'Y';
                CEP.TRC(SCCGWA, DDCUCRAC.TX_AMT);
                if (DDCUCRAC.TX_AMT != 0) {
                    S000_CALL_DDZUCRAC();
                }
            }
        } else {
            IBS.init(SCCGWA, AICUUPIA);
            AICUUPIA.DATA.AC_NO = DCCSDDCT.INPUT_DATA.CR_AC;
            AICUUPIA.DATA.CCY = DCCSDDCT.INPUT_DATA.CCY;
            AICUUPIA.DATA.AMT = DCCSDDCT.INPUT_DATA.TAMT;
            if (TDRSMST.PRDAC_CD.equalsIgnoreCase("035") 
                || TDRSMST.PRDAC_CD.equalsIgnoreCase("036")) {
                AICUUPIA.DATA.AMT = TDCUGRP.TXN_AMT_O;
            }
            AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            AICUUPIA.DATA.EVENT_CODE = "CR";
            AICUUPIA.DATA.PAY_MAN = DCCSDDCT.INPUT_DATA.LAW_NM1;
            CEP.TRC(SCCGWA, AICUUPIA.DATA.AMT);
            if (AICUUPIA.DATA.AMT != 0) {
                S000_CALL_AIZUUPIA();
            }
            WS_RVS_NO = AICUUPIA.DATA.RVS_NO;
        }
    }
    public void R_WRITE_DDTHLDR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHLDR);
        DDRHLDR.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRHLDR.KEY.TR_JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        DDRHLDR.OWNER_BK = DDRCCY.OWNER_BK;
        DDRHLDR.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRHLDR.OWNER_BR = DDRCCY.OWNER_BR;
        DDRHLDR.OWNER_BRDP = DDRCCY.OWNER_BRDP;
        DDRHLDR.TR_AMT = DCCSDDCT.INPUT_DATA.TAMT;
        DDRHLDR.SPR_BR_TYP = '1';
        DDRHLDR.SPR_BR_NM = DCCSDDCT.INPUT_DATA.DUCT_NM;
        DDRHLDR.CHG_WRIT_NO = DCCSDDCT.INPUT_DATA.CHG_NO;
        DDRHLDR.CHG_RSN = DCCSDDCT.INPUT_DATA.RSN;
        DDRHLDR.DEDUCT_FLG = DCCSDDCT.INPUT_DATA.FUNC;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            DDRHLDR.REVERSE_FLG = 'Y';
        }
        DDRHLDR.LAW_OFF_NM1 = DCCSDDCT.INPUT_DATA.LAW_NM1;
        DDRHLDR.LAW_ID_NO1 = DCCSDDCT.INPUT_DATA.LAW_NO1;
        DDRHLDR.LAW_OFF_NM2 = DCCSDDCT.INPUT_DATA.LAW_NM2;
        DDRHLDR.LAW_ID_NO2 = DCCSDDCT.INPUT_DATA.LAW_NO2;
        DDRHLDR.CRT_CHNL = SCCGWA.COMM_AREA.CHNL;
        DDRHLDR.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRHLDR.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRHLDR.AUTH_TL = SCCGWA.COMM_AREA.SUP1_ID;
        DDRHLDR.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRHLDR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRHLDR.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DDTHLDR();
    }
    public void R_GET_HOLD_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHLD);
        DDRHLD.KEY.HLD_NO = DCCSDDCT.INPUT_DATA.HLD_NO;
        T000_READ_DDTHLD();
        WS_HLD_AC = DDRHLD.AC;
        WS_HLD_STS = DDRHLD.HLD_STS;
        WS_HLD_TYP = DDRHLD.HLD_TYP;
        WS_HLD_FLG = DDRHLD.HLD_FLG;
        WS_HLD_AMT = DDRHLD.HLD_AMT;
        WS_SPR_TYP = DDRHLD.SPR_BR_TYP;
        WS_HLD_NO = DDRHLD.KEY.HLD_NO;
        CEP.TRC(SCCGWA, WS_HLD_AMT);
        CEP.TRC(SCCGWA, WS_HLD_STS);
        CEP.TRC(SCCGWA, WS_HLD_TYP);
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            if (WS_HLD_STS == 'C') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_AC_HAS_REL;
                S000_ERR_MSG_PROC();
            }
            if (WS_HLD_STS == 'W') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLDAMT_LESS_TAMT;
                S000_ERR_MSG_PROC();
            }
            if (DCCSDDCT.INPUT_DATA.FUNC == '2') {
                if (WS_HLD_FLG != '1') {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DUT_TYP_INVALID;
                    S000_ERR_MSG_PROC();
                }
            }
            if (DCCSDDCT.INPUT_DATA.FUNC == '3') {
                if (WS_HLD_FLG != '2') {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DUT_TYP_INVALID;
                    S000_ERR_MSG_PROC();
                }
            }
            if (WS_HLD_TYP == '2' 
                && DCCSDDCT.INPUT_DATA.TAMT > DDRHLD.HLD_AMT) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLDAMT_LESS_TAMT;
                S000_ERR_MSG_PROC();
            }
        }
        if (DDRHLD.HLD_TYP == '2' 
            && DCCSDDCT.INPUT_DATA.RHLD_TYP == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_RHLD_TYP_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void R_COMPUTE_AMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHLD);
        WS_CIRCLE_FLG = ' ';
        WS_HAMT = 0;
        DDRHLD.AC = WS_HLD_AC;
        T000_READ_DDTHLD_FIRST();
        while (WS_CIRCLE_FLG != 'Y') {
            CEP.TRC(SCCGWA, DDRHLD.KEY.HLD_NO);
            CEP.TRC(SCCGWA, WS_HLD_NO);
            if (!DDRHLD.KEY.HLD_NO.equalsIgnoreCase(WS_HLD_NO)) {
                CEP.TRC(SCCGWA, DDRHLD.HLD_TYP);
                if (DDRHLD.HLD_TYP == '1') {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_HOLD_OTH;
                    S000_ERR_MSG_PROC();
                } else {
                    WS_HAMT = WS_HAMT + DDRHLD.HLD_AMT;
                    CEP.TRC(SCCGWA, DDRHLD.DOWN_HLD_NO);
                    if (DDRHLD.DOWN_HLD_NO.trim().length() > 0) {
                        CEP.TRC(SCCGWA, DDRHLD.KEY.HLD_NO);
                        CEP.TRC(SCCGWA, DDRHLD.DOWN_HLD_NO);
                        DDRHLD.KEY.HLD_NO = DDRHLD.DOWN_HLD_NO;
                        T000_READ_DDTHLD();
                    } else {
                        WS_CIRCLE_FLG = 'Y';
                    }
                }
            } else {
                WS_CIRCLE_FLG = 'Y';
            }
            CEP.TRC(SCCGWA, WS_HAMT);
        }
        CEP.TRC(SCCGWA, WS_HAMT);
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
            R_CHECK_DD_AMT_PROC();
        }
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("TD")) {
            R_CHECK_TD_AMT_PROC();
        }
    }
    public void R_RELEASE_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSDDCT.INPUT_DATA.TAMT);
        CEP.TRC(SCCGWA, WS_HLD_TYP);
        if (WS_HLD_TYP == '1') {
            if (DCCSDDCT.INPUT_DATA.RHLD_TYP == '0') {
                if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                    IBS.init(SCCGWA, DDRHLDR);
                    DDRHLDR.KEY.HLD_NO = DCCSDDCT.INPUT_DATA.HLD_NO;
                    DDRHLDR.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    DDRHLDR.KEY.TR_JRNNO = SCCGWA.COMM_AREA.JRN_NO;
                    DDRHLDR.HLD_TYP = '1';
                    DDRHLDR.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
                    DDRHLDR.TR_AMT = DCCSDDCT.INPUT_DATA.TAMT;
                    DDRHLDR.SPR_BR_TYP = WS_SPR_TYP;
                    DDRHLDR.SPR_BR_NM = DCCSDDCT.INPUT_DATA.DUCT_NM;
                    DDRHLDR.CHG_WRIT_NO = DCCSDDCT.INPUT_DATA.CHG_NO;
                    DDRHLDR.CHG_RSN = DCCSDDCT.INPUT_DATA.RSN;
                    DDRHLDR.DEDUCT_FLG = DCCSDDCT.INPUT_DATA.FUNC;
                    DDRHLDR.LAW_OFF_NM1 = DCCSDDCT.INPUT_DATA.LAW_NM1;
                    DDRHLDR.LAW_ID_NO1 = DCCSDDCT.INPUT_DATA.LAW_NO1;
                    DDRHLDR.LAW_OFF_NM2 = DCCSDDCT.INPUT_DATA.LAW_NM2;
                    DDRHLDR.LAW_ID_NO2 = DCCSDDCT.INPUT_DATA.LAW_NO2;
                    DDRHLDR.CRT_CHNL = SCCGWA.COMM_AREA.CHNL;
                    DDRHLDR.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                    DDRHLDR.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    DDRHLDR.AUTH_TL = SCCGWA.COMM_AREA.SUP1_ID;
                    DDRHLDR.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
                    DDRHLDR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    DDRHLDR.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                    T000_WRITE_DDTHLDR();
                } else {
                    IBS.init(SCCGWA, DDRHLDR);
                    DDRHLDR.KEY.HLD_NO = DCCSDDCT.INPUT_DATA.HLD_NO;
                    DDRHLDR.KEY.TR_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
                    DDRHLDR.KEY.TR_JRNNO = GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
                    T000_READ_DDTHLDR();
                    DDRHLDR.REVERSE_FLG = 'Y';
                    DDRHLDR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    DDRHLDR.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                    T000_REWRITE_DDTHLDR();
                }
            } else {
                IBS.init(SCCGWA, DCCURHLD);
                DCCURHLD.DATA.HLD_NO = DCCSDDCT.INPUT_DATA.HLD_NO;
                DCCURHLD.DATA.RAMT = DCCSDDCT.INPUT_DATA.TAMT;
                DCCURHLD.DATA.CHG_NO = DCCSDDCT.INPUT_DATA.CHG_NO;
                DCCURHLD.DATA.RSN = DCCSDDCT.INPUT_DATA.RSN;
                DCCURHLD.DATA.RMK = DCCSDDCT.INPUT_DATA.RMK;
                DCCURHLD.DATA.CHG_BR = DCCSDDCT.INPUT_DATA.CHG_BR;
                DCCURHLD.DATA.LAW_NM1 = DCCSDDCT.INPUT_DATA.LAW_NM1;
                DCCURHLD.DATA.LAW_NO1 = DCCSDDCT.INPUT_DATA.LAW_NO1;
                DCCURHLD.DATA.LAW_NM2 = DCCSDDCT.INPUT_DATA.LAW_NM2;
                DCCURHLD.DATA.LAW_NO2 = DCCSDDCT.INPUT_DATA.LAW_NO2;
                DCCURHLD.DATA.SPR_NM = DCCSDDCT.INPUT_DATA.DUCT_NM;
                DCCURHLD.DATA.DEDUCT_FLG = '1';
                WS_REM_AMT = 0;
                DCCURHLD.DATA.RHLD_TYP = '1';
                S000_CALL_DCZURHLD();
            }
        }
        if (WS_HLD_TYP == '2') {
            IBS.init(SCCGWA, DCCURHLD);
            DCCURHLD.DATA.HLD_NO = DCCSDDCT.INPUT_DATA.HLD_NO;
            DCCURHLD.DATA.RAMT = DCCSDDCT.INPUT_DATA.TAMT;
            DCCURHLD.DATA.CHG_NO = DCCSDDCT.INPUT_DATA.CHG_NO;
            DCCURHLD.DATA.RSN = DCCSDDCT.INPUT_DATA.RSN;
            DCCURHLD.DATA.RMK = DCCSDDCT.INPUT_DATA.RMK;
            DCCURHLD.DATA.CHG_BR = DCCSDDCT.INPUT_DATA.CHG_BR;
            DCCURHLD.DATA.LAW_NM1 = DCCSDDCT.INPUT_DATA.LAW_NM1;
            DCCURHLD.DATA.LAW_NO1 = DCCSDDCT.INPUT_DATA.LAW_NO1;
            DCCURHLD.DATA.LAW_NM2 = DCCSDDCT.INPUT_DATA.LAW_NM2;
            DCCURHLD.DATA.LAW_NO2 = DCCSDDCT.INPUT_DATA.LAW_NO2;
            DCCURHLD.DATA.SPR_NM = DCCSDDCT.INPUT_DATA.DUCT_NM;
            DCCURHLD.DATA.DEDUCT_FLG = '1';
            WS_REM_AMT = 0;
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                if (WS_HLD_AMT == DCCSDDCT.INPUT_DATA.TAMT) {
                    DCCURHLD.DATA.RHLD_TYP = '1';
                } else {
                    if (DCCSDDCT.INPUT_DATA.RHLD_TYP == '0') {
                        DCCURHLD.DATA.RHLD_TYP = '2';
                        WS_REM_AMT = WS_HLD_AMT - DCCSDDCT.INPUT_DATA.TAMT;
                    } else {
                        DCCURHLD.DATA.RHLD_TYP = '1';
                    }
                }
            }
            CEP.TRC(SCCGWA, DCCURHLD.DATA.RHLD_TYP);
            S000_CALL_DCZURHLD();
        }
    }
    public void T000_READ_DDTHLD() throws IOException,SQLException,Exception {
        DDTHLD_RD = new DBParm();
        DDTHLD_RD.TableName = "DDTHLD";
        IBS.READ(SCCGWA, DDRHLD, DDTHLD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_DDTHLD_LAST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRHLD.AC);
        DDTHLD_RD = new DBParm();
        DDTHLD_RD.TableName = "DDTHLD";
        DDTHLD_RD.where = "AC = :DDRHLD.AC "
            + "AND DOWN_HLD_NO = ' ' "
            + "AND ( HLD_STS IN ( 'N' , 'W' ) )";
        IBS.READ(SCCGWA, DDRHLD, this, DDTHLD_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, DDRHLD.UP_HLD_NO);
        CEP.TRC(SCCGWA, DDRHLD.DOWN_HLD_NO);
    }
    public void T000_READ_DDTHLD_FIRST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRHLD.AC);
        DDTHLD_RD = new DBParm();
        DDTHLD_RD.TableName = "DDTHLD";
        DDTHLD_RD.where = "AC = :DDRHLD.AC "
            + "AND UP_HLD_NO = ' ' "
            + "AND ( HLD_STS = 'N' "
            + "OR HLD_STS = 'W' )";
        IBS.READ(SCCGWA, DDRHLD, this, DDTHLD_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, DDRHLD.UP_HLD_NO);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.upd = true;
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DD_AC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TD_AC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DD_CCY_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_STARTBR_GDTPLDR() throws IOException,SQLException,Exception {
        GDTPLDR_BR.rp = new DBParm();
        GDTPLDR_BR.rp.TableName = "GDTPLDR";
        GDTPLDR_BR.rp.where = "AC = :GDRPLDR.KEY.AC "
            + "AND AC_SEQ = :GDRPLDR.KEY.AC_SEQ "
            + "AND RELAT_STS = 'N' "
            + "AND REF_TYP = '2'";
        IBS.STARTBR(SCCGWA, GDRPLDR, this, GDTPLDR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_GDTPLDR_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_GDTPLDR_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "GDTPLDR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_READNEXT_GDTPLDR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, GDRPLDR, this, GDTPLDR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_GDTPLDR_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_GDTPLDR_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "GDTPLDR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_ENDBR_GDTPLDR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, GDTPLDR_BR);
    }
    public void T000_WRITE_DDTHLDR() throws IOException,SQLException,Exception {
        DDTHLDR_RD = new DBParm();
        DDTHLDR_RD.TableName = "DDTHLDR";
        IBS.WRITE(SCCGWA, DDRHLDR, DDTHLDR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTHLDR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_READ_DDTHLDR() throws IOException,SQLException,Exception {
        DDTHLDR_RD = new DBParm();
        DDTHLDR_RD.TableName = "DDTHLDR";
        DDTHLDR_RD.col = "HLD_NO,TR_DATE,TR_JRNNO,TR_AMT,REVERSE_FLG, HLD_TYP,BEF_TR_AMT,UPDTBL_DATE,UPDTBL_TLR";
        DDTHLDR_RD.upd = true;
        IBS.READ(SCCGWA, DDRHLDR, DDTHLDR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_REWRITE_DDTHLDR() throws IOException,SQLException,Exception {
        DDTHLDR_RD = new DBParm();
        DDTHLDR_RD.TableName = "DDTHLDR";
        IBS.REWRITE(SCCGWA, DDRHLDR, DDTHLDR_RD);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF);
        if (DCCUCINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DCZPCDCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-P-CARD-TRKDAT-CHK", DCCPCDCK);
    }
    public void S000_CALL_DDZIMPAY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-MPAY-PROC", DDCIMPAY);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFCSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-STS-TBL-AUTH", BPCFCSTS);
        if (BPCFCSTS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFCSTS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZUHDRA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-HDRAW-PROC", DDCUHDRA);
    }
    public void S000_CALL_TDZUGRP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-UNIT-GROUP", TDCUGRP);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        if (CICQACRI.RC.RC_CODE != 0 
            && !CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_TDZACDRU() throws IOException,SQLException,Exception {
        if (TDCACDRU.OPP_AC_CNO == null) TDCACDRU.OPP_AC_CNO = "";
        JIBS_tmp_int = TDCACDRU.OPP_AC_CNO.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.OPP_AC_CNO += " ";
        if (TDCACDRU.OPP_AC_CNO.substring(26 - 1, 26 + 1 - 1).trim().length() > 0) {
            TDCACDRU.STL_MTH = '1';
        } else {
            TDCACDRU.STL_MTH = '2';
        }
        TDCACDRU.STL_AC = TDCACDRU.OPP_AC_CNO;
        IBS.CALLCPN(SCCGWA, "TD-ACDR-UNT", TDCACDRU);
    }
    public void S000_CALL_DDZIBACK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-BACK-PROC", DDCIBACK);
        if (DDCIBACK.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIBACK.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        if (CICACCU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC);
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
        if (AICUUPIA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DCZURHLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-RHLD", DCCURHLD);
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
