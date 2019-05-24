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
import com.hisun.GD.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSDUCT {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    DBParm DDTHLD_RD;
    DBParm DDTHLDR_RD;
    DBParm DDTFBID_RD;
    brParm DDTHLD_BR = new brParm();
    DBParm DDTCCY_RD;
    DBParm TDTCDI_RD;
    DBParm TDTSMST_RD;
    DBParm TDTCMST_RD;
    DBParm TDTBVT_RD;
    DBParm DDTMST_RD;
    brParm GDTPLDR_BR = new brParm();
    DBParm DCTIAMST_RD;
    double WS_AMT = 0;
    String K_CNT_CCY = "156";
    String K_OUTPUT_FMT = "DC758";
    String K_STS_TABLE_APP1 = "DD";
    String K_STS_TABLE_APP2 = "TD";
    String K_CHK_STS_TBL = "7580";
    String K_DC_DUCT1 = "DDKH";
    String K_DC_DUCT2 = "DDKH";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    double WS_HAMT = 0;
    double WS_SAMT = 0;
    double WS_USED_AMT = 0;
    double WS_AMT_USE = 0;
    String WS_CI_NO1 = " ";
    String WS_CI_NO2 = " ";
    String WS_AC = " ";
    String WS_VIA_AC = " ";
    String WS_STD_APP = " ";
    int WS_HLD_SEQ = 0;
    char WS_HLD_TYPE = ' ';
    double WS_HLD_AMT = 0;
    double WS_REM_AMT = 0;
    double WS_INT_AMT = 0;
    String WS_HLD_BR_NM = " ";
    char WS_AC_TYP = ' ';
    String WS_STD_AC = " ";
    double WS_GD_RAMT = 0;
    String WS_HLD_AC = " ";
    int WS_AC_BR = 0;
    int WS_HLD_BR = 0;
    String WS_SPR_CHNL = " ";
    String WS_RVS_NO = " ";
    char WS_SPR_BR_TYP = ' ';
    double WS_CCAL_BAL = 0;
    double WS_CURR_BAL = 0;
    double WS_AVA_BAL = 0;
    char WS_LAW_AMT_N_FLG = ' ';
    char WS_LAW_FBID_FLG = ' ';
    String WS_PROD_CD = " ";
    DCZSDUCT_CP_PROD_CD CP_PROD_CD = new DCZSDUCT_CP_PROD_CD();
    String WS_ACAC_TYP = " ";
    String WS_PAY_MAN = " ";
    String WS_ACO_AC = " ";
    char WS_DCTHLD_REC = ' ';
    char WS_DCTIACCY_REC = ' ';
    char WS_DDTCCY_REC = ' ';
    char WS_TDTSMST_REC = ' ';
    char WS_TDTCMST_REC = ' ';
    char WS_TDTBVT_REC = ' ';
    char WS_GDTPLDR_REC = ' ';
    char WS_GD_AC_FLG = ' ';
    char WS_HLDNO_FLG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DDCUHDRA DDCUHDRA = new DDCUHDRA();
    TDCACDRU TDCACDRU = new TDCACDRU();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    TDRCDI TDRCDI = new TDRCDI();
    TDCTDDR TDCTDDR = new TDCTDDR();
    AICUUPIA AICUUPIA = new AICUUPIA();
    AICPQIA AICPQIA = new AICPQIA();
    CICACCU CICACCU = new CICACCU();
    DCCURHLD DCCURHLD = new DCCURHLD();
    DDRHLD DDRHLD = new DDRHLD();
    DDRHLDR DDRHLDR = new DDRHLDR();
    DCRIACCY DCRIACCY = new DCRIACCY();
    DCRIAMST DCRIAMST = new DCRIAMST();
    DCCIACCT DCCIACCT = new DCCIACCT();
    DDRCCY DDRCCY = new DDRCCY();
    DDRMST DDRMST = new DDRMST();
    GDRPLDR GDRPLDR = new GDRPLDR();
    TDRSMST TDRSMST = new TDRSMST();
    TDRCMST TDRCMST = new TDRCMST();
    TDRBVT TDRBVT = new TDRBVT();
    DDCUPARM DDCUPARM = new DDCUPARM();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    DCCUCINF DCCUCINF = new DCCUCINF();
    DDCIMPAY DDCIMPAY = new DDCIMPAY();
    DCCPCDCK DCCPCDCK = new DCCPCDCK();
    CICQACAC CICQACAC = new CICQACAC();
    CICQACRI CICQACRI = new CICQACRI();
    CICCUST CICCUST = new CICCUST();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    DCCODUCT DCCODUCT = new DCCODUCT();
    DDRFBID DDRFBID = new DDRFBID();
    TDCUGRP TDCUGRP = new TDCUGRP();
    DDCIBACK DDCIBACK = new DDCIBACK();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    DDVMPRD DDVMPRD = new DDVMPRD();
    TDCPRDP TDCPRDP = new TDCPRDP();
    CICQACRL CICQACRL = new CICQACRL();
    TDCQPMP TDCQPMP = new TDCQPMP();
    TDCPROD TDCPROD = new TDCPROD();
    SCCGWA SCCGWA;
    DCCSDUCT DCCSDUCT;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA, DCCSDUCT DCCSDUCT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSDUCT = DCCSDUCT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCZSDUCT return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (DCCSDUCT.INPUT_DATA.FUNC == '1') {
            B020_DEDUCT_STRAIGH_PROC();
        } else if (DCCSDUCT.INPUT_DATA.FUNC == '2') {
            B030_DEDUCT_RELEASE_PROC();
        } else if (DCCSDUCT.INPUT_DATA.FUNC == '3') {
            B050_DEDUCT_RELFBID_PROC();
        } else {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_INPUT_ERR;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, "PR0215");
        CEP.TRC(SCCGWA, TDRSMST.PRDAC_CD);
        CEP.TRC(SCCGWA, TDRSMST.STSW);
        CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B040_OUTPUT_DEDUCT_INF();
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (DCCSDUCT.INPUT_DATA.FUNC == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DUT_TYP_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DCCSDUCT.INPUT_DATA.AC.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_AC_NO;
            S000_ERR_MSG_PROC();
        }
        if (DCCSDUCT.INPUT_DATA.ST_MTH == '1') {
            if (DCCSDUCT.INPUT_DATA.CR_AC.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CR_ACNO_MUST_INPUT;
                S000_ERR_MSG_PROC();
            }
        }
        if (DCCSDUCT.INPUT_DATA.CCY.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ACCCY_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DCCSDUCT.INPUT_DATA.CCY_TYPE == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CCY_TYP_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DCCSDUCT.INPUT_DATA.TAMT <= 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_OLD_LINE_M_INPUT);
        }
        if (DCCSDUCT.INPUT_DATA.ST_MTH == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ST_MTH_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DCCSDUCT.INPUT_DATA.FUNC != '1' 
            && DCCSDUCT.INPUT_DATA.FUNC != '2' 
            && DCCSDUCT.INPUT_DATA.FUNC != '3') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DUT_TYP_INVALID;
            S000_ERR_MSG_PROC();
        }
        if (DCCSDUCT.INPUT_DATA.FUNC == '2') {
            if (DCCSDUCT.INPUT_DATA.HLD_NO.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLDNO_MUST_INPUT;
                S000_ERR_MSG_PROC();
            }
            IBS.init(SCCGWA, DDRHLD);
            CEP.TRC(SCCGWA, DCCSDUCT.INPUT_DATA.HLD_NO);
            DDRHLD.KEY.HLD_NO = DCCSDUCT.INPUT_DATA.HLD_NO;
            T000_READ_DDTHLD();
            CEP.TRC(SCCGWA, DDRHLD.HLD_STS);
            CEP.TRC(SCCGWA, DDRHLD.SPR_BR_TYP);
            CEP.TRC(SCCGWA, DDRHLD.HLD_CLS);
            CEP.TRC(SCCGWA, DDRHLD.AC);
            WS_AC = DDRHLD.AC;
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                if (DDRHLD.HLD_STS == 'C') {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_STS_C_CNOT_TR);
                }
                if (DDRHLD.HLD_STS == 'W') {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_STS_W_CNOT_TR);
                }
            }
            if (DDRHLD.HLD_CLS != '1' 
                && DDRHLD.SPR_BR_TYP == '2') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_BANK_NOT_JDKH);
            }
        }
        if (DCCSDUCT.INPUT_DATA.FUNC == '3') {
            if (DCCSDUCT.INPUT_DATA.HLD_NO.trim().length() == 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_REF_NO_M_INPUT);
            }
            IBS.init(SCCGWA, DDRFBID);
            CEP.TRC(SCCGWA, DCCSDUCT.INPUT_DATA.HLD_NO);
            DDRFBID.KEY.REF_NO = DCCSDUCT.INPUT_DATA.HLD_NO;
            T000_READ_DDTFBID();
            CEP.TRC(SCCGWA, DDRFBID.STS);
            CEP.TRC(SCCGWA, DDRFBID.AC);
            WS_AC = DDRFBID.AC;
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                if (DDRFBID.STS != '1') {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_FBD_NOT_STS_N);
                }
                CEP.TRC(SCCGWA, DDRFBID.ORG_TYP);
                if (DDRFBID.ORG_TYP != '2') {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.BK_INTER_TEMP_FORBID);
                }
            }
        }
    }
    public void B020_DEDUCT_STRAIGH_PROC() throws IOException,SQLException,Exception {
        B020_01_CHK_PROC();
        B020_01_CHK_CUSAC_PROC();
        B020_02_CHK_PROD();
        R000_GET_CI_INFO();
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B020_03_COM_AMT_PROC();
        }
        B020_05_DD_OR_TD_HDRA_PROC();
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            CEP.TRC(SCCGWA, "--DAOQIXI--");
            if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
                CEP.TRC(SCCGWA, "--DD--");
                B020_06_BACK_VALUE_PROC();
            }
        }
        B020_07_CR_AC_PROC();
        B020_08_WRITE_DDTHLDR_PROC();
        B020_09_CR_TD_INT_AC_PROC();
    }
    public void B030_DEDUCT_RELEASE_PROC() throws IOException,SQLException,Exception {
        B030_01_GET_HOLD_INF();
        B020_01_CHK_CUSAC_PROC();
        R000_GET_CI_INFO();
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B030_LAW_HLD_AMT();
            CEP.TRC(SCCGWA, WS_AMT);
            B030_05_COMPUTE_AMT();
        }
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("TD") 
            && SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            B020_05_DD_OR_TD_HDRA_PROC();
            B030_07_RELEASE_PROC();
        } else {
            B030_07_RELEASE_PROC();
            B020_05_DD_OR_TD_HDRA_PROC();
        }
        B020_07_CR_AC_PROC();
        B020_09_CR_TD_INT_AC_PROC();
    }
    public void B050_DEDUCT_RELFBID_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'A';
        CEP.TRC(SCCGWA, DDRFBID.AC);
        CICQACAC.DATA.ACAC_NO = DDRFBID.AC;
        S000_CALL_CIZQACAC();
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC);
        WS_ACAC_TYP = CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC;
        WS_PAY_MAN = CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM;
        B020_01_CHK_CUSAC_PROC();
        R000_GET_CI_INFO();
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B030_LAW_HLD_AMT();
            CEP.TRC(SCCGWA, WS_AMT);
            B030_05_01_CHK_AMT();
        }
        B020_05_DD_OR_TD_HDRA_PROC();
        B020_07_CR_AC_PROC();
        B020_08_WRITE_DDTHLDR_PROC();
        B020_09_CR_TD_INT_AC_PROC();
    }
    public void B020_01_CHK_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = DCCSDUCT.INPUT_DATA.AC;
        CICQACAC.DATA.AGR_SEQ = DCCSDUCT.INPUT_DATA.AC_SEQ;
        CICQACAC.DATA.CCY_ACAC = DCCSDUCT.INPUT_DATA.CCY;
        CICQACAC.DATA.CR_FLG = DCCSDUCT.INPUT_DATA.CCY_TYPE;
        S000_CALL_CIZQACAC();
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC);
        WS_ACAC_TYP = CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC;
        WS_PAY_MAN = CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM;
    }
    public void B020_01_CHK_CUSAC_PROC() throws IOException,SQLException,Exception {
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DC")) {
            DCCUCINF.CARD_NO = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
            S000_CALL_DCZUCINF();
        }
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
            DDRMST.KEY.CUS_AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
            T000_READ_DDTMST();
            if (DDRMST.AC_STS == 'C') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_CLOSE);
            }
        }
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("TD")) {
            TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            T000_READ_TDTSMST();
            if (TDRSMST.ACO_STS == 'C' 
                || TDRSMST.ACO_STS == '1') {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED);
            }
            WS_PROD_CD = TDRSMST.PROD_CD;
            CEP.TRC(SCCGWA, WS_PROD_CD);
        }
        R000_CHECK_PSW_PROC();
    }
    public void B020_02_CHK_PROD() throws IOException,SQLException,Exception {
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDRCCY);
            DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            T000_READ_DDTCCY();
            WS_PROD_CD = DDRCCY.PROD_CODE;
        }
        CEP.TRC(SCCGWA, WS_PROD_CD);
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = WS_PROD_CD;
        S000_CALL_BPZPQPRD();
        CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDVMPRD);
            IBS.init(SCCGWA, DDCUPARM);
            DDCUPARM.TX_TYPE = 'I';
            DDCUPARM.DATA.KEY.PARM_CODE = BPCPQPRD.PARM_CODE;
            DDCUPARM.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
            DDCUPARM.DATA.KEY.PRDMO_CD = "CAAC";
            DDCUPARM.DATE.EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
            S000_CALL_DDZUPARM();
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCUPARM.DATA);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDVMPRD);
            CEP.TRC(SCCGWA, DDVMPRD);
            CEP.TRC(SCCGWA, DDVMPRD.VAL.AUFR_FLG);
            if (DDVMPRD.VAL.AUFR_FLG == '1' 
                || DDVMPRD.VAL.AUFR_FLG == 'N') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_PROD_NO_DUCT);
            }
        }
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("TD")) {
            IBS.init(SCCGWA, TDCPROD);
            IBS.init(SCCGWA, TDCQPMP);
            IBS.init(SCCGWA, TDCPRDP);
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
            CEP.TRC(SCCGWA, TDCPRDP);
            CEP.TRC(SCCGWA, TDCPRDP);
            CEP.TRC(SCCGWA, TDCPRDP.OTH_PRM.FRZ_FLG);
            if (TDCPRDP.OTH_PRM.FRZ_FLG == '1' 
                || TDCPRDP.OTH_PRM.FRZ_FLG == 'N') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_PROD_NO_DUCT);
            }
        }
    }
    public void R000_CHECK_PSW_PROC() throws IOException,SQLException,Exception {
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DC") 
            && DCCSDUCT.INPUT_DATA.PSWD.trim().length() > 0 
            && DCCSDUCT.INPUT_DATA.TRK_DATA2.trim().length() > 0 
            && DCCSDUCT.TRK_DATA3.trim().length() > 0) {
            CEP.TRC(SCCGWA, "DC");
            IBS.init(SCCGWA, DCCPCDCK);
            DCCPCDCK.FUNC_CODE = 'B';
            DCCPCDCK.CARD_NO = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
            DCCPCDCK.CARD_PSW = DCCSDUCT.INPUT_DATA.PSWD;
            DCCPCDCK.TRK2_DAT = DCCSDUCT.INPUT_DATA.TRK_DATA2;
            DCCPCDCK.TRK3_DAT = DCCSDUCT.TRK_DATA3;
            S000_CALL_DCZPCDCK();
        }
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD") 
            && DCCSDUCT.INPUT_DATA.PSWD.trim().length() > 0) {
            IBS.init(SCCGWA, DDCIMPAY);
            DDCIMPAY.AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
            DDCIMPAY.PSWD_OLD = DCCSDUCT.INPUT_DATA.PSWD;
            DDCIMPAY.FUNC = 'C';
            DDCIMPAY.PAY_MTH = '1';
            S000_CALL_DDZIMPAY();
        }
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("TD")) {
            CEP.TRC(SCCGWA, "TD");
        }
    }
    public void B020_03_COM_AMT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHLD);
        WS_HAMT = 0;
        WS_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRHLD.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        CEP.TRC(SCCGWA, DDRHLD.AC);
        DDRHLD.HLD_STS = 'N';
        DDRHLD.SPR_BR_TYP = '1';
        T000_STARTBR_DCTHLD();
        T000_READNEXT_DCTHLD();
        CEP.TRC(SCCGWA, WS_DCTHLD_REC);
        while (WS_DCTHLD_REC != 'N') {
            CEP.TRC(SCCGWA, DDRHLD.HLD_TYP);
            if (DDRHLD.HLD_TYP == '1') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_HOLD_OTH;
                S000_ERR_MSG_PROC();
            } else {
                CEP.TRC(SCCGWA, WS_HAMT);
                CEP.TRC(SCCGWA, DDRHLD.HLD_AMT);
                WS_HAMT = WS_HAMT + DDRHLD.HLD_AMT;
            }
            CEP.TRC(SCCGWA, "COMPUTED");
            T000_READNEXT_DCTHLD();
        }
        T000_ENDBR_DCTHLD();
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
            B011_CHECK_DD_AMT_PROC();
        }
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("TD")) {
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            T000_READ_TDTSMST();
            R000_CHECK_STS_TBL();
            if (TDRSMST.ACO_STS == 'C' 
                || TDRSMST.ACO_STS == 'R' 
                || TDRSMST.ACO_STS == '1') {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED2;
                S000_ERR_MSG_PROC();
            }
            if (WS_TDTSMST_REC == 'Y') {
                CEP.TRC(SCCGWA, "WS-TDTSMST-FOUND");
                CEP.TRC(SCCGWA, TDRSMST.BAL);
                WS_USED_AMT = TDRSMST.BAL - WS_HAMT;
                CEP.TRC(SCCGWA, WS_USED_AMT);
            }
            if (DDRHLD.SPR_BR_TYP == '1' 
                && DDRHLD.HLD_TYP == '2') {
                if (WS_USED_AMT < DCCSDUCT.INPUT_DATA.TAMT) {
                    CEP.TRC(SCCGWA, "------AMT-----");
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_UAMT_LESS_TAMT;
                    S000_ERR_MSG_PROC();
                }
            }
            CEP.TRC(SCCGWA, TDRSMST.PRDAC_CD);
            CEP.TRC(SCCGWA, TDRSMST.GUAR_BAL);
            CEP.TRC(SCCGWA, WS_USED_AMT);
            if (TDRSMST.PRDAC_CD.equalsIgnoreCase("033") 
                && TDRSMST.GUAR_BAL != 0) {
                WS_USED_AMT = WS_USED_AMT - TDRSMST.GUAR_BAL;
                if (WS_USED_AMT < DCCSDUCT.INPUT_DATA.TAMT) {
                    R000_GET_GD_RAMT();
                    WS_USED_AMT = WS_USED_AMT + TDRSMST.GUAR_BAL - WS_GD_RAMT;
                    CEP.TRC(SCCGWA, WS_USED_AMT);
                    if (WS_USED_AMT < DCCSDUCT.INPUT_DATA.TAMT) {
                        WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_GD_UAMT_LESS;
                        S000_ERR_MSG_PROC();
                    }
                }
            }
        }
    }
    public void B020_05_DD_OR_TD_HDRA_PROC() throws IOException,SQLException,Exception {
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDCUHDRA);
            DDCUHDRA.AC = DCCSDUCT.INPUT_DATA.AC;
            DDCUHDRA.CCY = DCCSDUCT.INPUT_DATA.CCY;
            DDCUHDRA.CCY_TYPE = DCCSDUCT.INPUT_DATA.CCY_TYPE;
            CEP.TRC(SCCGWA, DDCUHDRA.CCY_TYPE);
            DDCUHDRA.TX_AMT = DCCSDUCT.INPUT_DATA.TAMT;
            DDCUHDRA.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDCUHDRA.HLD_REF = DCCSDUCT.INPUT_DATA.HLD_NO;
            DDCUHDRA.REMARKS = DCCSDUCT.INPUT_DATA.RMK;
            DDCUHDRA.TX_TYPE = 'T';
            DDCUHDRA.TX_MMO = "A314";
            DDCUHDRA.OTHER_AC = DCCSDUCT.INPUT_DATA.CR_AC;
            DDCUHDRA.RLT_ACNM = DCCSDUCT.INPUT_DATA.RLT_ACNM;
            DDCUHDRA.RLT_BANK = DCCSDUCT.INPUT_DATA.RLT_BANK;
            DDCUHDRA.NARRATIVE = DCCSDUCT.INPUT_DATA.NARRATIVE;
            DDCUHDRA.CCAL_BAL = WS_CCAL_BAL;
            CEP.TRC(SCCGWA, DDCUHDRA.CCAL_BAL);
            S000_CALL_DDZUHDRA();
            CEP.TRC(SCCGWA, DDCUHDRA.CURR_BAL);
            CEP.TRC(SCCGWA, DDCUHDRA.AVA_BAL);
            WS_CURR_BAL = DDCUHDRA.CURR_BAL;
            WS_AVA_BAL = DDCUHDRA.AVA_BAL;
        }
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("TD")) {
            IBS.init(SCCGWA, TDCACDRU);
            IBS.init(SCCGWA, TDRBVT);
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                T000_READ_TDTBVT();
                if (WS_TDTBVT_REC == 'Y') {
                    if (TDRBVT.STSW == null) TDRBVT.STSW = "";
                    JIBS_tmp_int = TDRBVT.STSW.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
                    if (TDRBVT.STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1")) {
                        WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_BVT_NOT_DUCT;
                        S000_ERR_MSG_PROC();
                    }
                }
            }
            TDCACDRU.MAC_CNO = DCCSDUCT.INPUT_DATA.AC;
            TDCACDRU.OPT = '1';
            CEP.TRC(SCCGWA, TDCACDRU.MAC_CNO);
            CEP.TRC(SCCGWA, TDCACDRU.AC_SEQ);
            TDCACDRU.ACO_AC = WS_AC;
            TDCACDRU.AC_SEQ = DCCSDUCT.INPUT_DATA.AC_SEQ;
            CEP.TRC(SCCGWA, TDCACDRU.ACO_AC);
            TDCACDRU.CCY = DCCSDUCT.INPUT_DATA.CCY;
            CEP.TRC(SCCGWA, TDCACDRU.CCY);
            TDCACDRU.TXN_AMT = DCCSDUCT.INPUT_DATA.TAMT;
            CEP.TRC(SCCGWA, TDCACDRU.TXN_AMT);
            TDCACDRU.DRAW_MTH = '4';
            if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
            JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
            TDCACDRU.BUSI_CTLW = "1" + TDCACDRU.BUSI_CTLW.substring(1);
            TDCACDRU.TXN_MMO = "A314";
            TDCACDRU.REMARK = DCCSDUCT.INPUT_DATA.RMK;
            TDCACDRU.OPP_AC_CNO = DCCSDUCT.INPUT_DATA.CR_AC;
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR);
            if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DC")) {
                TDCACDRU.BV_TYP = '4';
            }
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_HAS_PLEDGE);
            }
            if (TDRSMST.PRDAC_CD.equalsIgnoreCase("035") 
                || TDRSMST.PRDAC_CD.equalsIgnoreCase("036")) {
                R000_CALL_TDZUGRP();
                if (DCCSDUCT.INPUT_DATA.CR_AC == null) DCCSDUCT.INPUT_DATA.CR_AC = "";
                JIBS_tmp_int = DCCSDUCT.INPUT_DATA.CR_AC.length();
                for (int i=0;i<32-JIBS_tmp_int;i++) DCCSDUCT.INPUT_DATA.CR_AC += " ";
                if (DCCSDUCT.INPUT_DATA.CR_AC.substring(26 - 1, 26 + 1 - 1).trim().length() == 0) {
                    B230_CALL_DD_CR_UNT();
                } else {
                    B210_CALL_AI_CR_UNT();
                }
            } else {
                S000_CALL_TDZACDRU();
                if (DCCSDUCT.INPUT_DATA.TAMT >= TDCACDRU.DRAW_TOT_AMT) {
                    DCCSDUCT.INPUT_DATA.TAMT = TDCACDRU.DRAW_TOT_AMT;
                    WS_INT_AMT = 0;
                } else {
                    WS_INT_AMT = TDCACDRU.DRAW_TOT_AMT - DCCSDUCT.INPUT_DATA.TAMT;
                }
            }
            CEP.TRC(SCCGWA, TDCACDRU.DRAW_TOT_AMT);
            CEP.TRC(SCCGWA, WS_INT_AMT);
        }
    }
    public void R000_CALL_TDZUGRP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCUGRP);
        TDCUGRP.AC_NO = DCCSDUCT.INPUT_DATA.AC;
        TDCUGRP.PROD_CD = TDRSMST.PROD_CD;
        TDCUGRP.TXN_AMT = DCCSDUCT.INPUT_DATA.TAMT;
        TDCUGRP.OP_AC = DCCSDUCT.INPUT_DATA.CR_AC;
        TDCUGRP.DRAW_TYP = '1';
        TDCUGRP.CHK_PSW = 'N';
        TDCUGRP.OPTION = "XXTKH";
        TDCUGRP.TXN_MMO = "A314";
        S000_CALL_TDZUGRP();
    }
    public void B210_CALL_AI_CR_UNT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSDUCT.INPUT_DATA.CR_AC);
        CEP.TRC(SCCGWA, TDCUGRP.TXN_AMT_O);
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = DCCSDUCT.INPUT_DATA.CR_AC;
        AICUUPIA.DATA.RVS_SEQ = 0;
        AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.AMT = TDCUGRP.TXN_AMT_O;
        AICUUPIA.DATA.CCY = "156";
        AICUUPIA.DATA.EVENT_CODE = "CR";
        AICUUPIA.DATA.POST_NARR = " ";
        AICUUPIA.DATA.EVENT_CODE = "CR";
        AICUUPIA.DATA.PAY_MAN = DCCSDUCT.INPUT_DATA.RLT_ACNM;
        if (DCCSDUCT.INPUT_DATA.RLT_ACNM.trim().length() == 0) {
            AICUUPIA.DATA.PAY_MAN = DCCSDUCT.INPUT_DATA.LAW_NM1;
        }
        AICUUPIA.DATA.PAY_BR = (int) DCCSDUCT.INPUT_DATA.RLT_BANK;
        CEP.TRC(SCCGWA, AICUUPIA.DATA.RVS_NO);
        CEP.TRC(SCCGWA, AICUUPIA.DATA.RVS_NO);
    }
    public void B230_CALL_DD_CR_UNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUCRAC);
        CEP.TRC(SCCGWA, TDCUGRP.TXN_AMT_O);
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = DCCSDUCT.INPUT_DATA.CR_AC;
        CEP.TRC(SCCGWA, CICQACRI.DATA.AGR_NO);
        S000_CALL_CIZQACRI();
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
            DDCUCRAC.AC = DCCSDUCT.INPUT_DATA.CR_AC;
        } else {
            DDCUCRAC.CARD_NO = DCCSDUCT.INPUT_DATA.CR_AC;
            DDCUCRAC.BV_TYP = '1';
        }
        DDCUCRAC.CCY = "156";
        DDCUCRAC.CCY_TYPE = '1';
        DDCUCRAC.TX_AMT = TDCUGRP.TXN_AMT_O;
        DDCUCRAC.OTHER_AC = DCCSDUCT.INPUT_DATA.AC;
        DDCUCRAC.RLT_AC = DCCSDUCT.INPUT_DATA.AC;
        DDCUCRAC.OTHER_CCY = "156";
        DDCUCRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCUCRAC.NARRATIVE = " ";
        DDCUCRAC.TX_MMO = "TD";
        DDCUCRAC.OTH_TX_TOOL = DCCSDUCT.INPUT_DATA.AC;
        DDCUCRAC.OTHER_CCY = "156";
        DDCUCRAC.TX_TYPE = 'T';
        DDCUCRAC.BANK_CR_FLG = 'N';
    }
    public void B020_06_BACK_VALUE_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        if ((GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE > 0) 
            && (GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE < SCCGWA.COMM_AREA.AC_DATE)) {
            IBS.init(SCCGWA, DDCIBACK);
            DDCIBACK.OPT = 'W';
            DDCIBACK.TX_TYPE = 'T';
            DDCIBACK.FUNC = 'T';
            DDCIBACK.AC_NO = DCCSDUCT.INPUT_DATA.AC;
            DDCIBACK.DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            DDCIBACK.CCY = DCCSDUCT.INPUT_DATA.CCY;
            DDCIBACK.CCY_TYP = DCCSDUCT.INPUT_DATA.CCY_TYPE;
            DDCIBACK.AMT = DCCSDUCT.INPUT_DATA.TAMT;
            IBS.init(SCCGWA, DDRCCY);
            DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            CEP.TRC(SCCGWA, DDRCCY.KEY.AC);
            T000_READ_DDTCCY();
            DDCIBACK.LAST_POST_DATE = DDRCCY.LAST_POST_DATE;
            CEP.TRC(SCCGWA, DDCIBACK.AC_NO);
            CEP.TRC(SCCGWA, DDCIBACK.DATE);
            CEP.TRC(SCCGWA, DDCIBACK.CCY);
            CEP.TRC(SCCGWA, DDCIBACK.CCY_TYP);
            CEP.TRC(SCCGWA, DDCIBACK.AMT);
            CEP.TRC(SCCGWA, DDCIBACK.LAST_POST_DATE);
            S000_CALL_DDZIBACK();
        }
    }
    public void B020_07_CR_AC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = DCCSDUCT.INPUT_DATA.CR_AC;
        CEP.TRC(SCCGWA, CICQACRI.DATA.AGR_NO);
        S000_CALL_CIZQACRI();
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        if ((CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI") 
            && DCCSDUCT.INPUT_DATA.ST_MTH != '2') 
            || (!CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI") 
            && DCCSDUCT.INPUT_DATA.ST_MTH == '2')) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ST_MTH_AC_NOT_MATCH;
            S000_ERR_MSG_PROC();
        }
        if (DCCSDUCT.INPUT_DATA.ST_MTH == '1') {
            if (!CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
                IBS.init(SCCGWA, CICACCU);
                CICACCU.DATA.AGR_NO = DCCSDUCT.INPUT_DATA.CR_AC;
                S000_CALL_CIZACCU();
            }
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("TD")) {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CR_AC_NOT_TD_AC);
            }
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD") 
                || CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
                CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_CI_TYP);
                if (CICQACRI.O_DATA.O_CI_TYP == '1') {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CR_AC_NOT_PER_AC;
                    S000_ERR_MSG_PROC();
                }
                IBS.init(SCCGWA, DDCUCRAC);
                DDCUCRAC.AC = DCCSDUCT.INPUT_DATA.CR_AC;
                DDCUCRAC.CCY = DCCSDUCT.INPUT_DATA.CCY;
                DDCUCRAC.CCY_TYPE = DCCSDUCT.INPUT_DATA.CCY_TYPE;
                DDCUCRAC.TX_AMT = DCCSDUCT.INPUT_DATA.TAMT;
                CEP.TRC(SCCGWA, WS_ACAC_TYP);
                if (WS_ACAC_TYP.equalsIgnoreCase("TD")) {
                    CEP.TRC(SCCGWA, TDRSMST.PRDAC_CD);
                    if (TDRSMST.PRDAC_CD.equalsIgnoreCase("035") 
                        || TDRSMST.PRDAC_CD.equalsIgnoreCase("036")) {
                        DDCUCRAC.TX_AMT = TDCUGRP.TXN_AMT_O;
                    }
                }
                CEP.TRC(SCCGWA, DDCUCRAC.TX_AMT);
                DDCUCRAC.OTHER_AC = DCCSDUCT.INPUT_DATA.AC;
                DDCUCRAC.RLT_AC = DCCSDUCT.INPUT_DATA.AC;
                DDCUCRAC.OTH_TX_TOOL = DCCSDUCT.INPUT_DATA.AC;
                DDCUCRAC.TX_REF = DCCSDUCT.INPUT_DATA.HLD_NO;
                DDCUCRAC.REMARKS = DCCSDUCT.INPUT_DATA.RMK;
                DDCUCRAC.TX_MMO = "A001";
                DDCUCRAC.NARRATIVE = DCCSDUCT.INPUT_DATA.NARRATIVE;
                DDCUCRAC.TX_TYPE = 'T';
                DDCUCRAC.BANK_CR_FLG = 'Y';
                if (DDCUCRAC.TX_AMT != 0) {
                    S000_CALL_DDZUCRAC();
                }
            }
        } else {
            IBS.init(SCCGWA, AICUUPIA);
            CEP.TRC(SCCGWA, TDCUGRP.TXN_AMT_O);
            CEP.TRC(SCCGWA, DCCSDUCT.INPUT_DATA.TAMT);
            CEP.TRC(SCCGWA, DCCSDUCT.INPUT_DATA.CR_AC);
            AICUUPIA.DATA.AC_NO = DCCSDUCT.INPUT_DATA.CR_AC;
            AICUUPIA.DATA.CCY = DCCSDUCT.INPUT_DATA.CCY;
            AICUUPIA.DATA.AMT = DCCSDUCT.INPUT_DATA.TAMT;
            CEP.TRC(SCCGWA, WS_ACAC_TYP);
            if (WS_ACAC_TYP.equalsIgnoreCase("TD")) {
                CEP.TRC(SCCGWA, TDRSMST.PRDAC_CD);
                if (TDRSMST.PRDAC_CD.equalsIgnoreCase("035") 
                    || TDRSMST.PRDAC_CD.equalsIgnoreCase("036")) {
                    AICUUPIA.DATA.AMT = TDCUGRP.TXN_AMT_O;
                }
            }
            CEP.TRC(SCCGWA, AICUUPIA.DATA.AMT);
            AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            AICUUPIA.DATA.EVENT_CODE = "CR";
            AICUUPIA.DATA.THEIR_AC = DCCSDUCT.INPUT_DATA.AC;
            AICUUPIA.DATA.PAY_MAN = WS_PAY_MAN;
            if (AICUUPIA.DATA.AMT != 0) {
                S000_CALL_AIZUUPIA();
            }
            WS_RVS_NO = AICUUPIA.DATA.RVS_NO;
            CEP.TRC(SCCGWA, WS_RVS_NO);
        }
    }
    public void B020_08_WRITE_DDTHLDR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHLDR);
        if (DCCSDUCT.INPUT_DATA.FUNC == '3') {
            DDRHLDR.KEY.HLD_NO = DCCSDUCT.INPUT_DATA.HLD_NO;
            DDRHLDR.HLD_TYP = 'A';
        }
        DDRHLDR.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRHLDR.KEY.TR_JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        DDRHLDR.OWNER_BK = DDRCCY.OWNER_BK;
        DDRHLDR.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRHLDR.OWNER_BR = DDRCCY.OWNER_BR;
        DDRHLDR.OWNER_BRDP = DDRCCY.OWNER_BRDP;
        DDRHLDR.TR_AMT = DCCSDUCT.INPUT_DATA.TAMT;
        DDRHLDR.SPR_BR_TYP = '1';
        DDRHLDR.SPR_BR_NM = DCCSDUCT.INPUT_DATA.DUCT_NM;
        DDRHLDR.CHG_WRIT_NO = DCCSDUCT.INPUT_DATA.CHG_NO;
        DDRHLDR.CHG_RSN = DCCSDUCT.INPUT_DATA.RSN;
        DDRHLDR.DEDUCT_FLG = DCCSDUCT.INPUT_DATA.FUNC;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            DDRHLDR.REVERSE_FLG = 'Y';
        }
        DDRHLDR.LAW_OFF_NM1 = DCCSDUCT.INPUT_DATA.LAW_NM1;
        DDRHLDR.LAW_ID_NO1 = DCCSDUCT.INPUT_DATA.LAW_NO1;
        DDRHLDR.LAW_OFF_NM2 = DCCSDUCT.INPUT_DATA.LAW_NM2;
        DDRHLDR.LAW_ID_NO2 = DCCSDUCT.INPUT_DATA.LAW_NO2;
        DDRHLDR.CRT_CHNL = SCCGWA.COMM_AREA.CHNL;
        DDRHLDR.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRHLDR.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRHLDR.AUTH_TL = SCCGWA.COMM_AREA.SUP1_ID;
        DDRHLDR.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRHLDR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRHLDR.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DCTHLDR();
    }
    public void B020_09_CR_TD_INT_AC_PROC() throws IOException,SQLException,Exception {
        if (WS_STD_APP.equalsIgnoreCase("TD") 
            && WS_INT_AMT > 0) {
            if (DCCSDUCT.INPUT_DATA.INT_AC.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TD_INT_AC_MUST_INPUT;
                S000_ERR_MSG_PROC();
            }
            IBS.init(SCCGWA, DCCPACTY);
            DCCPACTY.INPUT.AC = DCCSDUCT.INPUT_DATA.INT_AC;
            DCCPACTY.INPUT.CCY = DCCSDUCT.INPUT_DATA.CCY;
            S000_CALL_DCZPACTY();
            CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.AC_TYPE);
            if (DCCPACTY.OUTPUT.AC_TYPE == 'K' 
                || DCCPACTY.OUTPUT.AC_TYPE == 'G') {
            } else {
                if (DCCPACTY.OUTPUT.AC_TYPE == 'D' 
                    && DCCPACTY.OUTPUT.STD_APP.equalsIgnoreCase("DD")) {
                } else {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_INT_AC_NOT_VALID;
                    S000_ERR_MSG_PROC();
                }
            }
            if (DCCPACTY.OUTPUT.AC_TYPE == 'K' 
                || DCCPACTY.OUTPUT.AC_TYPE == 'D') {
                IBS.init(SCCGWA, DDCUCRAC);
                if (DCCPACTY.OUTPUT.AC_TYPE == 'K') {
                    DDCUCRAC.CARD_NO = DCCSDUCT.INPUT_DATA.INT_AC;
                }
                DDCUCRAC.AC = DCCPACTY.OUTPUT.STD_AC;
                DDCUCRAC.CCY = DCCSDUCT.INPUT_DATA.CCY;
                DDCUCRAC.CCY_TYPE = DCCSDUCT.INPUT_DATA.CCY_TYPE;
                DDCUCRAC.TX_AMT = WS_INT_AMT;
                DDCUCRAC.RLT_AC = DCCSDUCT.INPUT_DATA.AC;
                DDCUCRAC.OTHER_AC = DCCSDUCT.INPUT_DATA.AC;
                DDCUCRAC.OTH_TX_TOOL = DCCSDUCT.INPUT_DATA.AC;
                DDCUCRAC.TX_REF = DCCSDUCT.INPUT_DATA.HLD_NO;
                DDCUCRAC.REMARKS = DCCSDUCT.INPUT_DATA.RMK;
                DDCUCRAC.TX_MMO = "A001";
                DDCUCRAC.NARRATIVE = DCCSDUCT.INPUT_DATA.NARRATIVE;
                DDCUCRAC.TX_TYPE = 'T';
                DDCUCRAC.BANK_CR_FLG = 'Y';
                S000_CALL_DDZUCRAC();
            }
            if (DCCPACTY.OUTPUT.AC_TYPE == 'G') {
                IBS.init(SCCGWA, AICUUPIA);
                AICUUPIA.DATA.AC_NO = DCCSDUCT.INPUT_DATA.INT_AC;
                AICUUPIA.DATA.CCY = DCCSDUCT.INPUT_DATA.CCY;
                AICUUPIA.DATA.AMT = WS_INT_AMT;
                AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
                AICUUPIA.DATA.EVENT_CODE = "CR";
                AICUUPIA.DATA.PAY_MAN = DCCSDUCT.INPUT_DATA.LAW_NM1;
                S000_CALL_AIZUUPIA();
            }
        }
    }
    public void B030_01_GET_HOLD_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHLD);
        DDRHLD.KEY.HLD_NO = DCCSDUCT.INPUT_DATA.HLD_NO;
        T000_READ_DCTHLD();
        if (WS_DCTHLD_REC == 'N') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
        WS_HLD_AC = DDRHLD.AC;
        WS_HLD_SEQ = DDRHLD.HLD_SEQ;
        WS_HLD_TYPE = DDRHLD.HLD_TYP;
        WS_HLD_AMT = DDRHLD.HLD_AMT;
        WS_HLD_BR_NM = DDRHLD.HLD_BR_NM;
        WS_HLD_BR = DDRHLD.CRT_BR;
        WS_SPR_CHNL = DDRHLD.SPR_CHNL;
        WS_SPR_BR_TYP = DDRHLD.SPR_BR_TYP;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            if (DDRHLD.HLD_STS == 'C') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_AC_HAS_REL;
                S000_ERR_MSG_PROC();
            }
            if (DDRHLD.HLD_TYP == '2' 
                && DCCSDUCT.INPUT_DATA.TAMT > DDRHLD.HLD_AMT) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLDAMT_LESS_TAMT;
                S000_ERR_MSG_PROC();
            }
            CEP.TRC(SCCGWA, WS_LAW_AMT_N_FLG);
            if (DDRHLD.SPR_BR_TYP == '1' 
                && DDRHLD.HLD_TYP == '2' 
                && DDRHLD.HLD_STS == 'N') {
                CEP.TRC(SCCGWA, "===*********===");
                WS_LAW_AMT_N_FLG = '1';
            }
            CEP.TRC(SCCGWA, WS_LAW_AMT_N_FLG);
        }
        if (DDRHLD.HLD_TYP == '2' 
            && DCCSDUCT.INPUT_DATA.RHLD_TYP == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_RHLD_TYP_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'A';
        CICQACAC.DATA.ACAC_NO = DDRHLD.AC;
        S000_CALL_CIZQACAC();
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC);
        WS_ACAC_TYP = CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC;
        WS_PAY_MAN = CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM;
    }
    public void B030_05_COMPUTE_AMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHLD);
        WS_HAMT = 0;
        DDRHLD.AC = WS_HLD_AC;
        DDRHLD.HLD_STS = 'N';
        DDRHLD.SPR_BR_TYP = '1';
        DDRHLD.HLD_SEQ = WS_HLD_SEQ;
        CEP.TRC(SCCGWA, DDRHLD.AC);
        CEP.TRC(SCCGWA, WS_HLD_SEQ);
        T000_STARTBR_DCTHLD_BY_HLDSEQ();
        T000_READNEXT_DCTHLD();
        while (WS_DCTHLD_REC != 'N') {
            if (DDRHLD.HLD_TYP == '1') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_HOLD_OTH;
                S000_ERR_MSG_PROC();
            } else {
                WS_HAMT = WS_HAMT + DDRHLD.HLD_AMT;
                CEP.TRC(SCCGWA, WS_HAMT);
            }
            T000_READNEXT_DCTHLD();
        }
        T000_ENDBR_DCTHLD();
        if (WS_SPR_BR_TYP == '2') {
            DDRHLD.SPR_BR_TYP = '2';
            DDRHLD.HLD_SEQ = WS_HLD_SEQ;
            T000_STARTBR_DCTHLD_BY_BNKSEQ();
            T000_READNEXT_DCTHLD();
            while (WS_DCTHLD_REC != 'N') {
                if (DDRHLD.HLD_TYP == '1') {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_HOLD_OTH;
                    S000_ERR_MSG_PROC();
                } else {
                    WS_HAMT = WS_HAMT + DDRHLD.HLD_AMT;
                    CEP.TRC(SCCGWA, WS_HAMT);
                }
                T000_READNEXT_DCTHLD();
            }
            T000_ENDBR_DCTHLD();
        }
        B030_05_01_CHK_AMT();
    }
    public void B030_05_01_CHK_AMT() throws IOException,SQLException,Exception {
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
            B011_CHECK_DD_AMT_PROC();
        }
        CEP.TRC(SCCGWA, "1111");
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("TD")) {
            CEP.TRC(SCCGWA, "22222");
            IBS.init(SCCGWA, TDRSMST);
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
            TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            T000_READ_TDTSMST();
            R000_CHECK_STS_TBL();
            if (TDRSMST.ACO_STS == 'C' 
                || TDRSMST.ACO_STS == 'R' 
                || TDRSMST.ACO_STS == '1') {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED2;
                S000_ERR_MSG_PROC();
            }
            if (WS_TDTSMST_REC == 'Y') {
                CEP.TRC(SCCGWA, TDRSMST.BAL);
                CEP.TRC(SCCGWA, WS_SAMT);
                if (DCCSDUCT.INPUT_DATA.FUNC == '3' 
                    || DCCSDUCT.INPUT_DATA.FUNC == '2') {
                    CEP.TRC(SCCGWA, "==CURR BALL - SUM LAW HLD BAL==");
                    WS_USED_AMT = TDRSMST.BAL - WS_AMT;
                    CEP.TRC(SCCGWA, WS_USED_AMT);
                } else {
                    WS_HAMT = TDRSMST.HBAL;
                    CEP.TRC(SCCGWA, WS_HAMT);
                    WS_USED_AMT = TDRSMST.BAL - WS_HAMT;
                }
            }
            CEP.TRC(SCCGWA, WS_USED_AMT);
            if (TDRSMST.PRDAC_CD.equalsIgnoreCase("033") 
                && TDRSMST.GUAR_BAL != 0) {
                WS_GD_AC_FLG = 'Y';
                WS_USED_AMT = WS_USED_AMT - TDRSMST.GUAR_BAL;
                CEP.TRC(SCCGWA, WS_USED_AMT);
                if (WS_USED_AMT < DCCSDUCT.INPUT_DATA.TAMT) {
                    R000_GET_GD_RAMT();
                    WS_USED_AMT = WS_USED_AMT + TDRSMST.GUAR_BAL - WS_GD_RAMT;
                    CEP.TRC(SCCGWA, WS_USED_AMT);
                    if ((DCCSDUCT.INPUT_DATA.FUNC != '2') 
                        && (WS_USED_AMT < DCCSDUCT.INPUT_DATA.TAMT)) {
                        WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_GD_UAMT_LESS;
                        S000_ERR_MSG_PROC();
                    }
                }
            }
        }
        CEP.TRC(SCCGWA, WS_GD_AC_FLG);
        if (WS_GD_AC_FLG != 'Y') {
            if (DCCSDUCT.INPUT_DATA.TAMT > WS_USED_AMT) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_UAMT_LESS_TAMT;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B030_LAW_HLD_AMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHLD);
        CEP.TRC(SCCGWA, DDRFBID.AC);
        CEP.TRC(SCCGWA, DCCSDUCT.INPUT_DATA.HLD_NO);
        if (DCCSDUCT.INPUT_DATA.FUNC == '3') {
            DDRHLD.AC = DDRFBID.AC;
        }
        if (DCCSDUCT.INPUT_DATA.FUNC == '2') {
            DDRHLD.AC = WS_HLD_AC;
        }
        CEP.TRC(SCCGWA, WS_HLD_AC);
        DDRHLD.KEY.HLD_NO = DCCSDUCT.INPUT_DATA.HLD_NO;
        DDTHLD_RD = new DBParm();
        DDTHLD_RD.TableName = "DDTHLD";
        DDTHLD_RD.set = "WS-AMT=IFNULL(SUM(HLD_AMT),0)";
        DDTHLD_RD.where = "AC = :DDRHLD.AC "
            + "AND SPR_BR_TYP = '1' "
            + "AND HLD_TYP = '2' "
            + "AND ( HLD_STS = 'N' "
            + "OR HLD_STS = 'W' ) "
            + "AND HLD_NO < :DDRHLD.KEY.HLD_NO";
        IBS.GROUP(SCCGWA, DDRHLD, this, DDTHLD_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "==DDTHLD FOUND==");
            WS_HLDNO_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "==DDTHLD NOT FOUND==");
            WS_HLDNO_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTHLD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void B011_CHECK_DD_AMT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        CEP.TRC(SCCGWA, DDRCCY.KEY.AC);
        T000_READ_DDTCCY();
        R000_CHECK_STS_TBL();
        CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
        CEP.TRC(SCCGWA, DDRCCY.CCAL_TOT_BAL);
        CEP.TRC(SCCGWA, WS_HAMT);
        if (DCCSDUCT.INPUT_DATA.FUNC == '3' 
            || DCCSDUCT.INPUT_DATA.FUNC == '2') {
            CEP.TRC(SCCGWA, "==CURR BALL - SUM LAW HLD BAL==");
            WS_USED_AMT = DDRCCY.CURR_BAL - WS_AMT + DDRCCY.CCAL_TOT_BAL;
        } else {
            WS_USED_AMT = DDRCCY.CURR_BAL + DDRCCY.CCAL_TOT_BAL - WS_HAMT;
        }
        CEP.TRC(SCCGWA, WS_USED_AMT);
        CEP.TRC(SCCGWA, DCCSDUCT.INPUT_DATA.TAMT);
        if (WS_USED_AMT < DCCSDUCT.INPUT_DATA.TAMT) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_UAMT_LESS_TAMT);
        }
        if (DDRCCY.CCAL_TOT_BAL > 0) {
            CEP.TRC(SCCGWA, "CALL ");
            WS_CCAL_BAL = DCCSDUCT.INPUT_DATA.TAMT - ( DDRCCY.CURR_BAL - WS_HAMT );
            if (WS_CCAL_BAL >= 0) {
            } else {
                WS_CCAL_BAL = 0;
            }
        }
        if (DDRCCY.AC_TYPE == '3' 
            && DDRCCY.MARGIN_BAL != 0) {
            WS_USED_AMT = WS_USED_AMT - DDRCCY.MARGIN_BAL;
            WS_AMT_USE = WS_USED_AMT - DDRCCY.CCAL_TOT_BAL;
            if (WS_AMT_USE < DCCSDUCT.INPUT_DATA.TAMT) {
                R000_GET_GD_RAMT();
                WS_USED_AMT = WS_USED_AMT + DDRCCY.MARGIN_BAL - WS_GD_RAMT;
                CEP.TRC(SCCGWA, WS_USED_AMT);
                if ((DCCSDUCT.INPUT_DATA.FUNC != '2') 
                    && (WS_USED_AMT < DCCSDUCT.INPUT_DATA.TAMT)) {
                    CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_GD_UAMT_LESS);
                }
            }
        }
        CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
    }
    public void B030_07_RELEASE_PROC() throws IOException,SQLException,Exception {
        if (WS_HLD_TYPE == '1') {
            if (DCCSDUCT.INPUT_DATA.RHLD_TYP == '0') {
                if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                    IBS.init(SCCGWA, DDRHLDR);
                    DDRHLDR.KEY.HLD_NO = DCCSDUCT.INPUT_DATA.HLD_NO;
                    DDRHLDR.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    DDRHLDR.KEY.TR_JRNNO = SCCGWA.COMM_AREA.JRN_NO;
                    DDRHLDR.HLD_TYP = '1';
                    DDRHLDR.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
                    DDRHLDR.TR_AMT = DCCSDUCT.INPUT_DATA.TAMT;
                    DDRHLDR.SPR_BR_TYP = '1';
                    DDRHLDR.SPR_BR_NM = DCCSDUCT.INPUT_DATA.DUCT_NM;
                    DDRHLDR.CHG_WRIT_NO = DCCSDUCT.INPUT_DATA.CHG_NO;
                    DDRHLDR.CHG_RSN = DCCSDUCT.INPUT_DATA.RSN;
                    DDRHLDR.DEDUCT_FLG = DCCSDUCT.INPUT_DATA.FUNC;
                    DDRHLDR.LAW_OFF_NM1 = DCCSDUCT.INPUT_DATA.LAW_NM1;
                    DDRHLDR.LAW_ID_NO1 = DCCSDUCT.INPUT_DATA.LAW_NO1;
                    DDRHLDR.LAW_OFF_NM2 = DCCSDUCT.INPUT_DATA.LAW_NM2;
                    DDRHLDR.LAW_ID_NO2 = DCCSDUCT.INPUT_DATA.LAW_NO2;
                    DDRHLDR.CRT_CHNL = SCCGWA.COMM_AREA.CHNL;
                    DDRHLDR.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                    DDRHLDR.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    DDRHLDR.AUTH_TL = SCCGWA.COMM_AREA.SUP1_ID;
                    DDRHLDR.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
                    DDRHLDR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    DDRHLDR.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                    T000_WRITE_DCTHLDR();
                    CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
                    CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
                    CEP.TRC(SCCGWA, DCCSDUCT.INPUT_DATA.HLD_NO);
                } else {
                    IBS.init(SCCGWA, DDRHLDR);
                    DDRHLDR.KEY.HLD_NO = DCCSDUCT.INPUT_DATA.HLD_NO;
                    DDRHLDR.KEY.TR_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
                    DDRHLDR.KEY.TR_JRNNO = GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
                    T000_READ_DCTHLDR();
                    DDRHLDR.REVERSE_FLG = 'Y';
                    DDRHLDR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    DDRHLDR.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                    T000_REWRITE_DCTHLDR();
                    CEP.TRC(SCCGWA, DCCSDUCT.INPUT_DATA.HLD_NO);
                    CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO);
                    CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE);
                }
            } else {
                CEP.TRC(SCCGWA, "-----------------");
                IBS.init(SCCGWA, DCCURHLD);
                DCCURHLD.DATA.HLD_NO = DCCSDUCT.INPUT_DATA.HLD_NO;
                DCCURHLD.DATA.RAMT = DCCSDUCT.INPUT_DATA.TAMT;
                DCCURHLD.DATA.CHG_NO = DCCSDUCT.INPUT_DATA.CHG_NO;
                DCCURHLD.DATA.SPR_NM = WS_HLD_BR_NM;
                DCCURHLD.DATA.RSN = DCCSDUCT.INPUT_DATA.RSN;
                DCCURHLD.DATA.RMK = DCCSDUCT.INPUT_DATA.RMK;
                DCCURHLD.DATA.CHG_BR = DCCSDUCT.INPUT_DATA.CHG_BR;
                DCCURHLD.DATA.LAW_NM1 = DCCSDUCT.INPUT_DATA.LAW_NM1;
                DCCURHLD.DATA.LAW_NO1 = DCCSDUCT.INPUT_DATA.LAW_NO1;
                DCCURHLD.DATA.LAW_NM2 = DCCSDUCT.INPUT_DATA.LAW_NM2;
                DCCURHLD.DATA.LAW_NO2 = DCCSDUCT.INPUT_DATA.LAW_NO2;
                DCCURHLD.DATA.SPR_NM = DCCSDUCT.INPUT_DATA.SPR_NM;
                DCCURHLD.DATA.DEDUCT_FLG = '1';
                WS_REM_AMT = 0;
                DCCURHLD.DATA.RHLD_TYP = '1';
                S000_CALL_DCZURHLD();
            }
        }
        if (WS_HLD_TYPE == '2') {
            CEP.TRC(SCCGWA, DCCSDUCT.INPUT_DATA.CHG_NO);
            IBS.init(SCCGWA, DCCURHLD);
            DCCURHLD.DATA.HLD_NO = DCCSDUCT.INPUT_DATA.HLD_NO;
            DCCURHLD.DATA.RAMT = DCCSDUCT.INPUT_DATA.TAMT;
            DCCURHLD.DATA.CHG_NO = DCCSDUCT.INPUT_DATA.CHG_NO;
            DCCURHLD.DATA.SPR_NM = WS_HLD_BR_NM;
            DCCURHLD.DATA.RSN = DCCSDUCT.INPUT_DATA.RSN;
            DCCURHLD.DATA.RMK = DCCSDUCT.INPUT_DATA.RMK;
            DCCURHLD.DATA.CHG_BR = DCCSDUCT.INPUT_DATA.CHG_BR;
            DCCURHLD.DATA.LAW_NM1 = DCCSDUCT.INPUT_DATA.LAW_NM1;
            DCCURHLD.DATA.LAW_NO1 = DCCSDUCT.INPUT_DATA.LAW_NO1;
            DCCURHLD.DATA.LAW_NM2 = DCCSDUCT.INPUT_DATA.LAW_NM2;
            DCCURHLD.DATA.LAW_NO2 = DCCSDUCT.INPUT_DATA.LAW_NO2;
            DCCURHLD.DATA.SPR_NM = DCCSDUCT.INPUT_DATA.DUCT_NM;
            DCCURHLD.DATA.DEDUCT_FLG = '1';
            WS_REM_AMT = 0;
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                if (WS_HLD_AMT == DCCSDUCT.INPUT_DATA.TAMT) {
                    DCCURHLD.DATA.RHLD_TYP = '1';
                } else {
                    if (DCCSDUCT.INPUT_DATA.RHLD_TYP == '0') {
                        DCCURHLD.DATA.RHLD_TYP = '2';
                        WS_REM_AMT = WS_HLD_AMT - DCCSDUCT.INPUT_DATA.TAMT;
                    } else {
                        DCCURHLD.DATA.RHLD_TYP = '1';
                    }
                }
            }
            S000_CALL_DCZURHLD();
        }
    }
    public void B030_09_DEDUCT_AC_PROC() throws IOException,SQLException,Exception {
        if (DCCPACTY.OUTPUT.STD_APP.equalsIgnoreCase("DD")) {
            CEP.TRC(SCCGWA, DCCSDUCT.INPUT_DATA.CCY);
            IBS.init(SCCGWA, DDCUHDRA);
            DDCUHDRA.AC = WS_STD_AC;
            if (DCCPACTY.OUTPUT.AC_TYPE == 'K' 
                || DCCPACTY.OUTPUT.AC_FREE_TYPE.equalsIgnoreCase("004")) {
                DDCUHDRA.CARD_NO = DCCSDUCT.INPUT_DATA.AC;
                if (DCCPACTY.OUTPUT.AC_FREE_TYPE.equalsIgnoreCase("004")) {
                    DDCUHDRA.VS_AC_FLG = 'Y';
                }
                if (DCCPACTY.OUTPUT.AC_TYPE == 'K') {
                    DDCUHDRA.CARD_NO = DCCPACTY.OUTPUT.N_CARD_NO;
                }
            }
            DDCUHDRA.CCY = DCCSDUCT.INPUT_DATA.CCY;
            DDCUHDRA.CCY_TYPE = DCCSDUCT.INPUT_DATA.CCY_TYPE;
            DDCUHDRA.TX_AMT = DCCSDUCT.INPUT_DATA.TAMT;
            DDCUHDRA.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDCUHDRA.HLD_REF = DCCSDUCT.INPUT_DATA.HLD_NO;
            DDCUHDRA.REMARKS = DCCSDUCT.INPUT_DATA.RMK;
            DDCUHDRA.TX_TYPE = 'T';
            if (WS_SPR_BR_TYP == '1') {
                DDCUHDRA.TX_MMO = "A314";
            } else {
                DDCUHDRA.TX_MMO = "X9F";
            }
            DDCUHDRA.OTHER_AC = DCCSDUCT.INPUT_DATA.CR_AC;
            DDCUHDRA.RLT_ACNM = DCCSDUCT.INPUT_DATA.RLT_ACNM;
            DDCUHDRA.RLT_BANK = DCCSDUCT.INPUT_DATA.RLT_BANK;
            DDCUHDRA.NARRATIVE = DCCSDUCT.INPUT_DATA.NARRATIVE;
            DDCUHDRA.CCAL_BAL = WS_CCAL_BAL;
            CEP.TRC(SCCGWA, DDCUHDRA.CCAL_BAL);
            S000_CALL_DDZUHDRA();
        }
        if (DCCPACTY.OUTPUT.STD_APP.equalsIgnoreCase("TD")) {
            IBS.init(SCCGWA, TDCACDRU);
            IBS.init(SCCGWA, TDRBVT);
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                T000_READ_TDTBVT();
                if (WS_TDTBVT_REC == 'Y') {
                    if (TDRBVT.STSW == null) TDRBVT.STSW = "";
                    JIBS_tmp_int = TDRBVT.STSW.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
                    if (TDRBVT.STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1")) {
                        WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_BVT_NOT_DUCT;
                        S000_ERR_MSG_PROC();
                    }
                }
            }
            CEP.TRC(SCCGWA, DCCSDUCT.INPUT_DATA.AC);
            TDCACDRU.MAC_CNO = DCCSDUCT.INPUT_DATA.AC;
            CEP.TRC(SCCGWA, TDCACDRU.MAC_CNO);
            TDCACDRU.AC_SEQ = DCCPACTY.INPUT.SEQ;
            CEP.TRC(SCCGWA, TDCACDRU.CCY);
            TDCACDRU.CCY = DCCSDUCT.INPUT_DATA.CCY;
            TDCACDRU.TXN_AMT = DCCSDUCT.INPUT_DATA.TAMT;
            if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
            JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
            TDCACDRU.BUSI_CTLW = "1" + TDCACDRU.BUSI_CTLW.substring(1);
            if (WS_SPR_BR_TYP == '1') {
                TDCACDRU.TXN_MMO = "A314";
            } else {
                TDCACDRU.TXN_MMO = "X9F";
            }
            TDCACDRU.REMARK = DCCSDUCT.INPUT_DATA.RMK;
            TDCACDRU.OPP_AC_CNO = DCCSDUCT.INPUT_DATA.CR_AC;
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_HAS_PLEDGE);
            }
            S000_CALL_TDZACDRU();
            if (DCCSDUCT.INPUT_DATA.TAMT >= TDCACDRU.DRAW_TOT_AMT) {
                DCCSDUCT.INPUT_DATA.TAMT = TDCACDRU.DRAW_TOT_AMT;
                WS_INT_AMT = 0;
            } else {
                WS_INT_AMT = TDCACDRU.DRAW_TOT_AMT - DCCSDUCT.INPUT_DATA.TAMT;
            }
            CEP.TRC(SCCGWA, TDCACDRU.DRAW_TOT_AMT);
            CEP.TRC(SCCGWA, WS_INT_AMT);
        }
    }
    public void B030_13_CR_TD_INT_AC_PROC() throws IOException,SQLException,Exception {
        if (WS_STD_APP.equalsIgnoreCase("TD") 
            && WS_INT_AMT > 0) {
            if (DCCSDUCT.INPUT_DATA.INT_AC.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TD_INT_AC_MUST_INPUT;
                S000_ERR_MSG_PROC();
            }
            IBS.init(SCCGWA, DCCPACTY);
            DCCPACTY.INPUT.AC = DCCSDUCT.INPUT_DATA.INT_AC;
            DCCPACTY.INPUT.CCY = DCCSDUCT.INPUT_DATA.CCY;
            S000_CALL_DCZPACTY();
            if (DCCPACTY.OUTPUT.AC_TYPE == 'K' 
                || DCCPACTY.OUTPUT.AC_TYPE == 'G') {
            } else {
                if (DCCPACTY.OUTPUT.AC_TYPE == 'D' 
                    && DCCPACTY.OUTPUT.STD_APP.equalsIgnoreCase("DD")) {
                } else {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_INT_AC_NOT_VALID;
                    S000_ERR_MSG_PROC();
                }
            }
            if (DCCPACTY.OUTPUT.AC_TYPE == 'K' 
                || DCCPACTY.OUTPUT.AC_TYPE == 'D') {
                IBS.init(SCCGWA, DDCUCRAC);
                if (DCCPACTY.OUTPUT.AC_TYPE == 'K') {
                    DDCUCRAC.CARD_NO = DCCSDUCT.INPUT_DATA.INT_AC;
                }
                DDCUCRAC.AC = DCCPACTY.OUTPUT.STD_AC;
                DDCUCRAC.CCY = DCCSDUCT.INPUT_DATA.CCY;
                DDCUCRAC.CCY_TYPE = DCCSDUCT.INPUT_DATA.CCY_TYPE;
                DDCUCRAC.TX_AMT = WS_INT_AMT;
                DDCUCRAC.OTHER_AC = DCCSDUCT.INPUT_DATA.AC;
                DDCUCRAC.RLT_AC = DCCSDUCT.INPUT_DATA.AC;
                DDCUCRAC.OTH_TX_TOOL = DCCSDUCT.INPUT_DATA.AC;
                DDCUCRAC.TX_REF = DCCSDUCT.INPUT_DATA.HLD_NO;
                DDCUCRAC.REMARKS = DCCSDUCT.INPUT_DATA.RMK;
                if (WS_SPR_BR_TYP == '1') {
                    DDCUCRAC.TX_MMO = "A314";
                } else {
                    DDCUCRAC.TX_MMO = "X9F";
                }
                DDCUCRAC.NARRATIVE = DCCSDUCT.INPUT_DATA.NARRATIVE;
                DDCUCRAC.TX_TYPE = 'T';
                DDCUCRAC.BANK_CR_FLG = 'Y';
                S000_CALL_DDZUCRAC();
            }
            if (DCCPACTY.OUTPUT.AC_TYPE == 'G') {
                IBS.init(SCCGWA, AICUUPIA);
                AICUUPIA.DATA.AC_NO = DCCSDUCT.INPUT_DATA.INT_AC;
                AICUUPIA.DATA.CCY = DCCSDUCT.INPUT_DATA.CCY;
                AICUUPIA.DATA.AMT = WS_INT_AMT;
                AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
                AICUUPIA.DATA.EVENT_CODE = "CR";
                AICUUPIA.DATA.PAY_MAN = DCCSDUCT.INPUT_DATA.LAW_NM1;
                S000_CALL_AIZUUPIA();
            }
        }
    }
    public void B040_OUTPUT_DEDUCT_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCODUCT.OUTPUT_DATA);
        DCCODUCT.OUTPUT_DATA.O_HLD_NO = DCCSDUCT.INPUT_DATA.HLD_NO;
        DCCODUCT.OUTPUT_DATA.O_AC = DCCSDUCT.INPUT_DATA.AC;
        DCCODUCT.OUTPUT_DATA.O_AC_SEQ = DCCSDUCT.INPUT_DATA.AC_SEQ;
        DCCODUCT.OUTPUT_DATA.O_CCY = DCCSDUCT.INPUT_DATA.CCY;
        DCCODUCT.OUTPUT_DATA.O_CCY_TYPE = DCCSDUCT.INPUT_DATA.CCY_TYPE;
        DCCODUCT.OUTPUT_DATA.O_HLD_TYP = WS_HLD_TYPE;
        DCCODUCT.OUTPUT_DATA.O_BEF_AMT = WS_HLD_AMT;
        DCCODUCT.OUTPUT_DATA.O_TAMT = DCCSDUCT.INPUT_DATA.TAMT;
        DCCODUCT.OUTPUT_DATA.O_RHLD_TYP = DCCSDUCT.INPUT_DATA.RHLD_TYP;
        DCCODUCT.OUTPUT_DATA.O_REM_AMT = WS_REM_AMT;
        DCCODUCT.OUTPUT_DATA.O_CHG_NO = DCCSDUCT.INPUT_DATA.CHG_NO;
        DCCODUCT.OUTPUT_DATA.O_RSN = DCCSDUCT.INPUT_DATA.RSN;
        DCCODUCT.OUTPUT_DATA.O_ST_MTH = DCCSDUCT.INPUT_DATA.ST_MTH;
        if (DCCSDUCT.INPUT_DATA.ST_MTH == '1') {
            DCCODUCT.OUTPUT_DATA.O_CR_AC = DCCSDUCT.INPUT_DATA.CR_AC;
        } else {
            DCCODUCT.OUTPUT_DATA.O_CR_AC = AICPQIA.AC;
        }
        DCCODUCT.OUTPUT_DATA.O_INT_AC = DCCSDUCT.INPUT_DATA.INT_AC;
        DCCODUCT.OUTPUT_DATA.O_RMK = DCCSDUCT.INPUT_DATA.RMK;
        DCCODUCT.OUTPUT_DATA.O_CHG_BR = DCCSDUCT.INPUT_DATA.CHG_BR;
        DCCODUCT.OUTPUT_DATA.O_LAW_NM1 = DCCSDUCT.INPUT_DATA.LAW_NM1;
        DCCODUCT.OUTPUT_DATA.O_LAW_NO1 = DCCSDUCT.INPUT_DATA.LAW_NO1;
        DCCODUCT.OUTPUT_DATA.O_LAW_NM2 = DCCSDUCT.INPUT_DATA.LAW_NM2;
        DCCODUCT.OUTPUT_DATA.O_LAW_NO2 = DCCSDUCT.INPUT_DATA.LAW_NO2;
        DCCODUCT.OUTPUT_DATA.O_RVS_NO = WS_RVS_NO;
        CEP.TRC(SCCGWA, WS_RVS_NO);
        DCCSDUCT.RVS_NO = WS_RVS_NO;
        DCCODUCT.OUTPUT_DATA.O_NARRATIVE = DCCSDUCT.INPUT_DATA.NARRATIVE;
        DCCODUCT.OUTPUT_DATA.O_CURR_BAL = WS_CURR_BAL;
        DCCODUCT.OUTPUT_DATA.O_AVA_BAL = WS_AVA_BAL;
        CEP.TRC(SCCGWA, DCCODUCT.OUTPUT_DATA.O_TAMT);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DCCODUCT.OUTPUT_DATA;
        SCCFMT.DATA_LEN = 1329;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_GET_GD_RAMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRPLDR);
        GDRPLDR.KEY.AC = DCCSDUCT.INPUT_DATA.AC;
        GDRPLDR.KEY.AC_SEQ = DCCSDUCT.INPUT_DATA.AC_SEQ;
        T000_STARTBR_GDTPLDR();
        T000_READNEXT_GDTPLDR();
        while (WS_GDTPLDR_REC != 'N') {
            CEP.TRC(SCCGWA, WS_GD_RAMT);
            CEP.TRC(SCCGWA, GDRPLDR.RELAT_AMT);
            WS_GD_RAMT = WS_GD_RAMT + GDRPLDR.RELAT_AMT;
            T000_READNEXT_GDTPLDR();
        }
        T000_ENDBR_GDTPLDR();
        CEP.TRC(SCCGWA, WS_GD_RAMT);
    }
    public void R000_GET_CI_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'C';
        CICCUST.DATA.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        S000_CALL_CIZCUST();
    }
    public void R000_CHECK_STS_TBL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFCSTS);
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.STD_APP);
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
                CEP.TRC(SCCGWA, "====DD1717=====");
                WS_LAW_FBID_FLG = '1';
            }
            CEP.TRC(SCCGWA, DDRMST.AC_STS_WORD);
            if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
            JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
            for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
            CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD.substring(101 - 1, 101 + 99 - 1));
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
                CEP.TRC(SCCGWA, "====TD8888=====");
                WS_LAW_FBID_FLG = '1';
            }
            CEP.TRC(SCCGWA, TDRSMST.STSW);
        }
        BPCFCSTS.TBL_NO = K_CHK_STS_TBL;
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        if (CICACCU.DATA.CI_STSW == null) CICACCU.DATA.CI_STSW = "";
        JIBS_tmp_int = CICACCU.DATA.CI_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICACCU.DATA.CI_STSW += " ";
        BPCFCSTS.STATUS_WORD = CICACCU.DATA.CI_STSW + BPCFCSTS.STATUS_WORD.substring(80);
        CEP.TRC(SCCGWA, BPCFCSTS.AP_MMO);
        CEP.TRC(SCCGWA, BPCFCSTS.TBL_NO);
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD);
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD.substring(0, 80));
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD.substring(101 - 1, 101 + 99 - 1));
        CEP.TRC(SCCGWA, WS_LAW_AMT_N_FLG);
        CEP.TRC(SCCGWA, WS_LAW_FBID_FLG);
        if (DCCSDUCT.INPUT_DATA.FUNC == '3' 
            || (DCCSDUCT.INPUT_DATA.FUNC == '2' 
            && WS_LAW_AMT_N_FLG == '1' 
            && WS_LAW_FBID_FLG == '1')) {
        } else {
            S000_CALL_BPZFCSTS();
        }
    }
    public void R000_AC_PURP_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCIACCT);
        DCCIACCT.DATA.AC_PURP = DDRMST.AC_PURP;
        DCCIACCT.DATA.OPT = 'D';
        S000_CALL_DCZIACCT();
        CEP.TRC(SCCGWA, DDRMST.AC_PURP);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        if (CICQACRI.RC.RC_CODE != 0 
            && !CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
            CEP.ERR(SCCGWA, CICQACRI.RC);
        }
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF);
        if (DCCUCINF.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DCCUCINF.RC);
        }
    }
    public void S000_CALL_DCZPCDCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-P-CARD-TRKDAT-CHK", DCCPCDCK);
    }
    public void S000_CALL_DDZIMPAY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-MPAY-PROC", DDCIMPAY);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACAC.RC);
        }
    }
    if (CICQACAC.RC.RC_CODE != 0) {
        CEP.ERR(SCCGWA, CICQACAC.RC);
    }
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-GET-SEQ", BPCSGSEQ);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_WRITE_DCTHLDR() throws IOException,SQLException,Exception {
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
    public void T000_READ_DCTHLD() throws IOException,SQLException,Exception {
        DDTHLD_RD = new DBParm();
        DDTHLD_RD.TableName = "DDTHLD";
        IBS.READ(SCCGWA, DDRHLD, DDTHLD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DCTHLD_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_REC_NOTFND;
            S000_ERR_MSG_PROC();
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTHLD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_READ_DDTHLD() throws IOException,SQLException,Exception {
        DDTHLD_RD = new DBParm();
        DDTHLD_RD.TableName = "DDTHLD";
        DDTHLD_RD.where = "HLD_NO = :DDRHLD.KEY.HLD_NO";
        IBS.READ(SCCGWA, DDRHLD, this, DDTHLD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_DDTFBID() throws IOException,SQLException,Exception {
        DDTFBID_RD = new DBParm();
        DDTFBID_RD.TableName = "DDTFBID";
        DDTFBID_RD.where = "REF_NO = :DDRFBID.KEY.REF_NO "
            + "AND TYPE = '1'";
        IBS.READ(SCCGWA, DDRFBID, this, DDTFBID_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FBID_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_STARTBR_DCTHLD() throws IOException,SQLException,Exception {
        DDTHLD_BR.rp = new DBParm();
        DDTHLD_BR.rp.TableName = "DDTHLD";
        DDTHLD_BR.rp.where = "AC = :DDRHLD.AC "
            + "AND HLD_STS = :DDRHLD.HLD_STS "
            + "AND SPR_BR_TYP = :DDRHLD.SPR_BR_TYP";
        IBS.STARTBR(SCCGWA, DDRHLD, this, DDTHLD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DCTHLD_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_DCTHLD_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTHLD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_STARTBR_DCTHLD_BY_HLDSEQ() throws IOException,SQLException,Exception {
        if (WS_SPR_BR_TYP == '1') {
            DDTHLD_BR.rp = new DBParm();
            DDTHLD_BR.rp.TableName = "DDTHLD";
            DDTHLD_BR.rp.where = "AC = :DDRHLD.AC "
                + "AND HLD_STS = :DDRHLD.HLD_STS "
                + "AND SPR_BR_TYP = :DDRHLD.SPR_BR_TYP "
                + "AND HLD_SEQ < :DDRHLD.HLD_SEQ";
            IBS.STARTBR(SCCGWA, DDRHLD, this, DDTHLD_BR);
        } else {
            DDTHLD_BR.rp = new DBParm();
            DDTHLD_BR.rp.TableName = "DDTHLD";
            DDTHLD_BR.rp.where = "AC = :DDRHLD.AC "
                + "AND HLD_STS = :DDRHLD.HLD_STS "
                + "AND SPR_BR_TYP = :DDRHLD.SPR_BR_TYP";
            IBS.STARTBR(SCCGWA, DDRHLD, this, DDTHLD_BR);
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DCTHLD_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_DCTHLD_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTHLD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_STARTBR_DCTHLD_BY_BNKSEQ() throws IOException,SQLException,Exception {
        DDTHLD_BR.rp = new DBParm();
        DDTHLD_BR.rp.TableName = "DDTHLD";
        DDTHLD_BR.rp.where = "AC = :DDRHLD.AC "
            + "AND HLD_STS = :DDRHLD.HLD_STS "
            + "AND SPR_BR_TYP = :DDRHLD.SPR_BR_TYP "
            + "AND HLD_SEQ < :DDRHLD.HLD_SEQ";
        IBS.STARTBR(SCCGWA, DDRHLD, this, DDTHLD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DCTHLD_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_DCTHLD_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTHLD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_READNEXT_DCTHLD() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRHLD, this, DDTHLD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DCTHLD_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_DCTHLD_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTHLD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_ENDBR_DCTHLD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTHLD_BR);
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTCCY_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DD_CCY_NOTFND;
            S000_ERR_MSG_PROC();
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTCCY";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_READ_TDTCDI() throws IOException,SQLException,Exception {
        TDTCDI_RD = new DBParm();
        TDTCDI_RD.TableName = "TDTCDI";
        IBS.READ(SCCGWA, TDRCDI, TDTCDI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TD_AC_NOTFND;
            S000_ERR_MSG_PROC();
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTCDI";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_READ_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TDTSMST_REC = 'Y';
            WS_ACO_AC = TDRSMST.KEY.ACO_AC;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TD_AC_NOTFND;
            S000_ERR_MSG_PROC();
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TCTSMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_READ_TDTCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        IBS.READ(SCCGWA, TDRCMST, TDTCMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TDTCMST_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TD_AC_NOTFND;
            S000_ERR_MSG_PROC();
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTCMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_READ_TDTBVT() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        IBS.READ(SCCGWA, TDRBVT, TDTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TDTBVT_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TDTBVT_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TCTBVT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_READ_DCTHLDR() throws IOException,SQLException,Exception {
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
    public void T000_REWRITE_DCTHLDR() throws IOException,SQLException,Exception {
        DDTHLDR_RD = new DBParm();
        DDTHLDR_RD.TableName = "DDTHLDR";
        IBS.REWRITE(SCCGWA, DDRHLDR, DDTHLDR_RD);
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
    public void T000_STARTBR_GDTPLDR() throws IOException,SQLException,Exception {
        GDTPLDR_BR.rp = new DBParm();
        GDTPLDR_BR.rp.TableName = "GDTPLDR";
        GDTPLDR_BR.rp.where = "AC = :GDRPLDR.KEY.AC "
            + "AND AC_SEQ = :GDRPLDR.KEY.AC_SEQ "
            + "AND RELAT_STS = 'N' "
            + "AND REF_TYP IN ( '10' , '12' )";
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
    public void T000_READ_DCTIAMST() throws IOException,SQLException,Exception {
        DCTIAMST_RD = new DBParm();
        DCTIAMST_RD.TableName = "DCTIAMST";
        DCTIAMST_RD.col = "AC_STS,STS_WORD";
        DCTIAMST_RD.upd = true;
        IBS.READ(SCCGWA, DCRIAMST, DCTIAMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_VIA_AC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
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
    public void S000_CALL_TDZUGRP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-UNIT-GROUP", TDCUGRP);
    }
    public void S000_CALL_AICUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
        if (AICUUPIA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_DDZUHDRA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-HDRAW-PROC", DDCUHDRA);
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
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC);
    }
    public void S000_CALL_DDZIBACK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-BACK-PROC", DDCIBACK);
        if (DDCIBACK.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIBACK.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DCZURHLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-RHLD", DCCURHLD);
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
        if (AICUUPIA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        if (CICACCU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_TDZTDDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-TD-DR", TDCTDDR);
    }
    public void S000_CALL_BPZFCSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-STS-TBL-AUTH", BPCFCSTS);
        if (BPCFCSTS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFCSTS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DCZIACCT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-AC-PURP-CHK", DCCIACCT);
        if (DCCIACCT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCIACCT.RC);
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, DCCIACCT.RC);
    }
    public void S000_CALL_AIZPQIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-IA", AICPQIA);
        if (AICPQIA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQIA.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_AIZPPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
        if (AICUUPIA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCUST.RC);
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
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
    public void S000_CALL_DDZUPARM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-MPRD", DDCUPARM);
        CEP.TRC(SCCGWA, DDCUPARM.RC);
    }
    if (DDCUPARM.RC.RC_CODE != 0) {
        CEP.ERR(SCCGWA, DDCUPARM.RC);
    }
    public void S000_CALL_TDZQPMP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-BASE-PRD-QRY", TDCQPMP);
        CEP.TRC(SCCGWA, TDCQPMP.RC);
        if (TDCQPMP.RC.RC_RTNCODE != 0) {
            CEP.ERR(SCCGWA, TDCQPMP.RC);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
