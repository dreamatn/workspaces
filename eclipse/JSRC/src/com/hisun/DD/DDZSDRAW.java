package com.hisun.DD;

import com.hisun.DC.*;
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
import com.hisun.BP.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSDRAW {
    int JIBS_tmp_int;
    DBParm TDTSMST_RD;
    DBParm DDTMST_RD;
    DBParm DDTCCY_RD;
    String K_OUTPUT_FMT = "DD210";
    String K_STS_TABLE_APP = "DD";
    String K_CHK_STS_TBL = "0002";
    double WS_AVA_BAL = 0;
    double WS_TX_AMT = 0;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DDRCCY DDRCCY = new DDRCCY();
    DDRMST DDRMST = new DDRMST();
    TDRSMST TDRSMST = new TDRSMST();
    TDCACDRU TDCACDRU = new TDCACDRU();
    TDCUGRP TDCUGRP = new TDCUGRP();
    AICUUPIA AICUUPIA = new AICUUPIA();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    DCCUCINF DCCUCINF = new DCCUCINF();
    CICQACAC CICQACAC = new CICQACAC();
    CICQACRI CICQACRI = new CICQACRI();
    CICCUST CICCUST = new CICCUST();
    CICSAGEN CICSAGEN = new CICSAGEN();
    DDCUHDRA DDCUHDRA = new DDCUHDRA();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    DDCIBACK DDCIBACK = new DDCIBACK();
    DDCODRAW DDCODRAW = new DDCODRAW();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    CICGAGA_AGENT_AREA CICGAGA_AGENT_AREA;
    DDCSDRAW DDCSDRAW;
    public void MP(SCCGWA SCCGWA, DDCSDRAW DDCSDRAW) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSDRAW = DDCSDRAW;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZSDRAW return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        CICGAGA_AGENT_AREA = new CICGAGA_AGENT_AREA_AGENT_AREA();
        IBS.init(SCCGWA, CICGAGA_AGENT_AREA);
        IBS.CPY2CLS(SCCGWA, SCCGWA.AGENT_AREA_PTR, CICGAGA_AGENT_AREA);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSDRAW.AC);
        CEP.TRC(SCCGWA, DDCSDRAW.AC_SEQ);
        CEP.TRC(SCCGWA, DDCSDRAW.CCY);
        CEP.TRC(SCCGWA, DDCSDRAW.CCY_TYPE);
        CEP.TRC(SCCGWA, DDCSDRAW.TX_AMT);
        CEP.TRC(SCCGWA, DDCSDRAW.TRF_FLG);
        CEP.TRC(SCCGWA, DDCSDRAW.TRF_AC);
        CEP.TRC(SCCGWA, DDCSDRAW.RMK);
        B010_CHECK_INPUT();
        B020_GET_CUSAC_INFO();
        B030_GET_CI_INFO();
        B040_CHECK_STS_TBL();
        B050_WITHDRAW_PROC();
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            && CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
            B060_BACK_VALUE_PROC();
        }
        B070_DEPOSIT_PROC();
        if (SCCGWA.COMM_AREA.AGENT_FLG == 'Y') {
            B080_AGENT_INF_PORC();
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B090_OUTPUT_DRAW_INFO();
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (DDCSDRAW.AC.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_AC_NO);
        }
        if (DDCSDRAW.CCY.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_ACCCY_MUST_INPUT);
        }
        if (DDCSDRAW.CCY_TYPE == ' ') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CCY_TYP_MUST_INPUT);
        }
        if (DDCSDRAW.TX_AMT <= 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AMT_M_INPUT);
        }
        if (DDCSDRAW.TRF_FLG == ' ') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_TRF_FLG_M_INPUT);
        }
        if (DDCSDRAW.TRF_AC.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_TRF_AMT_M_INPUT);
        }
    }
    public void B020_GET_CUSAC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = DDCSDRAW.AC;
        CICQACAC.DATA.AGR_SEQ = DDCSDRAW.AC_SEQ;
        CICQACAC.DATA.CCY_ACAC = DDCSDRAW.CCY;
        CICQACAC.DATA.CR_FLG = DDCSDRAW.CCY_TYPE;
        S000_CALL_CIZQACAC();
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DC")) {
            IBS.init(SCCGWA, DCCUCINF);
            DCCUCINF.CARD_NO = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
            S000_CALL_DCZUCINF();
            CEP.TRC(SCCGWA, DCCUCINF.CARD_STS);
            if (DCCUCINF.CARD_STS == 'C') {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_BE_CLOSED);
            }
        }
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
            T000_READ_DDTMST();
            CEP.TRC(SCCGWA, DDRMST.AC_STS);
            if (DDRMST.AC_STS == 'C') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_CLOSE);
            }
        }
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDRCCY);
            DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            T000_READ_DDTCCY();
            CEP.TRC(SCCGWA, DDRCCY.STS);
            if (DDRCCY.STS == 'C') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_CLEARED);
            }
            CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
            CEP.TRC(SCCGWA, DDRCCY.HOLD_BAL);
            WS_AVA_BAL = DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL;
        }
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("TD")) {
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            T000_READ_TDTSMST();
            CEP.TRC(SCCGWA, TDRSMST.ACO_STS);
            if (TDRSMST.ACO_STS == '1' 
                || TDRSMST.ACO_STS == '2') {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED);
            }
            CEP.TRC(SCCGWA, TDRSMST.BAL);
            CEP.TRC(SCCGWA, TDRSMST.HBAL);
            WS_AVA_BAL = TDRSMST.BAL - TDRSMST.HBAL;
        }
    }
    public void B030_GET_CI_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'C';
        CICCUST.DATA.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        S000_CALL_CIZCUST();
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_TYP);
        if (CICCUST.O_DATA.O_CI_TYP != '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_NOT_PER_CLIENT);
        }
    }
    public void B040_CHECK_STS_TBL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFCSTS);
        BPCFCSTS.AP_MMO = K_STS_TABLE_APP;
        BPCFCSTS.TBL_NO = K_CHK_STS_TBL;
        if (CICCUST.O_DATA.O_STSW == null) CICCUST.O_DATA.O_STSW = "";
        JIBS_tmp_int = CICCUST.O_DATA.O_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICCUST.O_DATA.O_STSW += " ";
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        BPCFCSTS.STATUS_WORD = CICCUST.O_DATA.O_STSW.substring(0, 80) + BPCFCSTS.STATUS_WORD.substring(80);
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
            if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
            JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
            for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 101 - 1) + DDRMST.AC_STS_WORD + BPCFCSTS.STATUS_WORD.substring(101 + 99 - 1);
            if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
            JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
            for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 201 - 1) + DDRCCY.STS_WORD + BPCFCSTS.STATUS_WORD.substring(201 + 99 - 1);
        }
        CEP.TRC(SCCGWA, BPCFCSTS.AP_MMO);
        CEP.TRC(SCCGWA, BPCFCSTS.TBL_NO);
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD);
        S000_CALL_BPZFCSTS();
    }
    public void B050_WITHDRAW_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_AVA_BAL);
        if (WS_AVA_BAL < DDCSDRAW.TX_AMT) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AVL_BAL_NOT_ENOUGH);
        }
        WS_TX_AMT = DDCSDRAW.TX_AMT;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC);
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
            B050_01_DR_DD_PROC();
        } else if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("TD")) {
            B050_02_DR_TD_PROC();
        } else {
        }
    }
    public void B050_01_DR_DD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUHDRA);
        DDCUHDRA.AC = DDCSDRAW.AC;
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DC")) {
            DDCUHDRA.CARD_NO = DDCSDRAW.AC;
        }
        DDCUHDRA.CCY = DDCSDRAW.CCY;
        DDCUHDRA.CCY_TYPE = DDCSDRAW.CCY_TYPE;
        DDCUHDRA.TX_AMT = DDCSDRAW.TX_AMT;
        DDCUHDRA.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCUHDRA.REMARKS = DDCSDRAW.RMK;
        DDCUHDRA.TX_TYPE = 'T';
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            DDCUHDRA.TX_MMO = "G004";
        } else {
            DDCUHDRA.TX_MMO = "C021";
        }
        DDCUHDRA.OTHER_AC = DDCSDRAW.TRF_AC;
        S000_CALL_DDZUHDRA();
    }
    public void B050_02_DR_TD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRSMST.PRDAC_CD);
        if (TDRSMST.PRDAC_CD.equalsIgnoreCase("035") 
            || TDRSMST.PRDAC_CD.equalsIgnoreCase("036")) {
            IBS.init(SCCGWA, TDCUGRP);
            TDCUGRP.AC_NO = DDCSDRAW.AC;
            TDCUGRP.PROD_CD = TDRSMST.PROD_CD;
            TDCUGRP.TXN_AMT = DDCSDRAW.TX_AMT;
            TDCUGRP.OP_AC = DDCSDRAW.TRF_AC;
            TDCUGRP.DRAW_TYP = '1';
            TDCUGRP.CHK_PSW = 'N';
            TDCUGRP.OPTION = "XXTKH";
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                TDCUGRP.TXN_MMO = "G004";
            } else {
                TDCUGRP.TXN_MMO = "C021";
            }
            S000_CALL_TDZUGRP();
            WS_TX_AMT = TDCUGRP.TXN_AMT_O;
            CEP.TRC(SCCGWA, WS_TX_AMT);
        } else {
            IBS.init(SCCGWA, TDCACDRU);
            TDCACDRU.MAC_CNO = DDCSDRAW.AC;
            TDCACDRU.OPT = '1';
            TDCACDRU.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            TDCACDRU.AC_SEQ = DDCSDRAW.AC_SEQ;
            TDCACDRU.CCY = DDCSDRAW.CCY;
            TDCACDRU.TXN_AMT = DDCSDRAW.TX_AMT;
            TDCACDRU.DRAW_MTH = '4';
            if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
            JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
            TDCACDRU.BUSI_CTLW = "1" + TDCACDRU.BUSI_CTLW.substring(1);
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                TDCACDRU.TXN_MMO = "G004";
            } else {
                TDCACDRU.TXN_MMO = "C021";
            }
            TDCACDRU.REMARK = DDCSDRAW.RMK;
            TDCACDRU.OPP_AC_CNO = DDCSDRAW.TRF_AC;
            if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DC")) {
                TDCACDRU.BV_TYP = '4';
            }
            S000_CALL_TDZACDRU();
        }
    }
    public void B060_BACK_VALUE_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE);
        if ((GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE > 0) 
            && (GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE < SCCGWA.COMM_AREA.AC_DATE)) {
            IBS.init(SCCGWA, DDCIBACK);
            DDCIBACK.OPT = 'W';
            DDCIBACK.TX_TYPE = 'T';
            DDCIBACK.FUNC = 'T';
            DDCIBACK.AC_NO = DDCSDRAW.AC;
            DDCIBACK.DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            DDCIBACK.CCY = DDCSDRAW.CCY;
            DDCIBACK.CCY_TYP = DDCSDRAW.CCY_TYPE;
            DDCIBACK.AMT = DDCSDRAW.TX_AMT;
            DDCIBACK.LAST_POST_DATE = DDRCCY.LAST_POST_DATE;
            S000_CALL_DDZIBACK();
        }
    }
    public void B070_DEPOSIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = DDCSDRAW.TRF_AC;
        S000_CALL_CIZQACRI();
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        if ((CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI") 
            && DDCSDRAW.TRF_FLG != '2') 
            || (!CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI") 
            && DDCSDRAW.TRF_FLG == '2')) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_ST_MTH_AC_NOT_MATCH);
        }
        if (DDCSDRAW.TRF_FLG == '2') {
            B070_01_CR_AI_PROC();
        } else {
            B070_02_CR_DD_PROC();
        }
    }
    public void B070_01_CR_AI_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.EVENT_CODE = "CR";
        AICUUPIA.DATA.AC_NO = DDCSDRAW.TRF_AC;
        AICUUPIA.DATA.CCY = DDCSDRAW.CCY;
        AICUUPIA.DATA.AMT = WS_TX_AMT;
        CEP.TRC(SCCGWA, AICUUPIA.DATA.AMT);
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.THEIR_AC = DDCSDRAW.AC;
        AICUUPIA.DATA.PAY_MAN = CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM;
        AICUUPIA.DATA.DESC = DDCSDRAW.RMK;
        S000_CALL_AIZUUPIA();
        CEP.TRC(SCCGWA, AICUUPIA.DATA.RVS_NO);
    }
    public void B070_02_CR_DD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUCRAC);
        DDCUCRAC.AC = DDCSDRAW.TRF_AC;
        DDCUCRAC.CCY = DDCSDRAW.CCY;
        DDCUCRAC.CCY_TYPE = DDCSDRAW.CCY_TYPE;
        DDCUCRAC.TX_AMT = WS_TX_AMT;
        CEP.TRC(SCCGWA, DDCUCRAC.TX_AMT);
        DDCUCRAC.OTHER_AC = DDCSDRAW.AC;
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DC")) {
            DDCUCRAC.OTH_TX_TOOL = DDCSDRAW.AC;
        }
        DDCUCRAC.REMARKS = DDCSDRAW.RMK;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            DDCUCRAC.TX_MMO = "G004";
        } else {
            DDCUCRAC.TX_MMO = "A001";
        }
        DDCUCRAC.TX_TYPE = 'T';
        DDCUCRAC.BANK_CR_FLG = 'Y';
        S000_CALL_DDZUCRAC();
    }
    public void B080_AGENT_INF_PORC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSAGEN);
        CICSAGEN.FUNC = 'A';
        CICSAGEN.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        CICSAGEN.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        CICSAGEN.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        CICSAGEN.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CICSAGEN.TLR_NO = SCCGWA.COMM_AREA.TL_ID;
        CICSAGEN.CI_NO = CICCUST.O_DATA.O_CI_NO;
        CICSAGEN.OUT_AC = DDCSDRAW.AC;
        CEP.TRC(SCCGWA, CICSAGEN.OUT_AC);
        CICSAGEN.ID_TYP = CICGAGA_AGENT_AREA.ID_TYP;
        CICSAGEN.ID_NO = CICGAGA_AGENT_AREA.ID_NO;
        CICSAGEN.AGENT_TP = "99";
        CICSAGEN.CI_NAME = CICGAGA_AGENT_AREA.CI_NM;
        CICSAGEN.PHONE = CICGAGA_AGENT_AREA.PHONE;
        S000_CALL_CIZSAGEN();
    }
    public void B090_OUTPUT_DRAW_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCODRAW);
        DDCODRAW.AC = DDCSDRAW.AC;
        DDCODRAW.AC_SEQ = DDCSDRAW.AC_SEQ;
        DDCODRAW.CCY = DDCSDRAW.CCY;
        DDCODRAW.CCY_TYPE = DDCSDRAW.CCY_TYPE;
        DDCODRAW.TX_AMT = DDCSDRAW.TX_AMT;
        DDCODRAW.TRF_FLG = DDCSDRAW.TRF_FLG;
        DDCODRAW.TRF_AC = DDCSDRAW.TRF_AC;
        DDCODRAW.RMK = DDCSDRAW.RMK;
        DDCODRAW.RVS_NO = AICUUPIA.DATA.RVS_NO;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DDCODRAW;
        SCCFMT.DATA_LEN = 240;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        if (CICQACRI.RC.RC_CODE != 0 
            && !CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
            CEP.ERR(SCCGWA, CICQACRI.RC);
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACAC.RC);
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCUST.RC);
        }
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF);
        if (DCCUCINF.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DCCUCINF.RC);
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
    public void S000_CALL_TDZUGRP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-UNIT-GROUP", TDCUGRP);
    }
    public void S000_CALL_DDZUHDRA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-HDRAW-PROC", DDCUHDRA);
    }
    public void S000_CALL_DDZIBACK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-BACK-PROC", DDCIBACK);
        if (DDCIBACK.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DDCIBACK.RC);
        }
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC);
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
        if (AICUUPIA.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, AICUUPIA.RC);
        }
    }
    public void S000_CALL_BPZFCSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-STS-TBL-AUTH", BPCFCSTS);
        if (BPCFCSTS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCFCSTS.RC);
        }
    }
    public void S000_CALL_CIZSAGEN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SVR-CIZSAGEN", CICSAGEN);
        if (CICSAGEN.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSAGEN.RC);
        }
    }
    public void T000_READ_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_TD_AC_NOTFND);
        }
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_DD_AC_NOTFND);
        }
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_DD_CCY_NOTFND);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
