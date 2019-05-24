package com.hisun.FX;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.VT.WFCCAPV;
import com.hisun.WF.*;
import com.hisun.CI.*;
import com.hisun.DD.*;

import java.text.DecimalFormat;

import java.io.IOException;
import java.sql.SQLException;

public class FXOT1151 {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    String JIBS_NumStr;
    String JIBS_f0;
    DBParm CITBAS_RD;
    DecimalFormat df;
    DBParm DDTMST_RD;
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG      ";
    String CPN_INQ_EXR_CODE = "BP-INQ-EXR-CODE   ";
    String CPN_I_INQ_BAL = "DD-I-INQ-CCY-BAL  ";
    String CPN_P_QUERY_FXOG_INFO = "BP-P-QUERY-FXOG-INFO";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String K_PROD_CD1 = "1303020403";
    String K_PROD_CD2 = "1303021101";
    String K_CNY = "156";
    String K_USD = "840";
    char K_AC_FLG_CASH = '0';
    char K_AC_FLG_DDAC = '1';
    char K_AC_FLG_SUPAC = '2';
    char K_AC_FLG_SUPAC_1 = '3';
    char K_CASH_FLG_NOTE = '0';
    char K_CASH_FLG_REMIT = '1';
    char K_CI_TYP_PERSONAL = '1';
    char K_AC_STS = 'C';
    char K_USE_IND = 'Y';
    String K_EXTM_CD = "999";
    char K_FXT_STS_UNUSED = 'U';
    String K_OUTPUT_FMT = "BP815";
    String WK_MOBILE_CHNL = "10203";
    int WK_MOBILE_TM_DIF = 35;
    int WK_MONTHS = 3;
    char WS_CHK_AMT_FLG = ' ';
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    double WS_TOTAL_AMT = 0;
    char WS_SW_OFF = '0';
    String WS_SELL_CCY_CNTYCD = " ";
    String WS_BUY_CCY_CNTYCD = " ";
    int WS_TEMP_DT = 0;
    int WS_SELT_DATE = 0;
    double AMT_NS_TOL = 0;
    int WS_EX_STR_TIME = 0;
    int WS_EX_END_TIME = 0;
    int WS_EX_HOUR = 0;
    int WS_EX_MINU = 0;
    int WS_EX_SECO = 0;
    int WS_TR_HOUR = 0;
    int WS_TR_MINU = 0;
    int WS_TR_SECO = 0;
    int WS_EX_SEC = 0;
    int WS_TR_SEC = 0;
    int WS_TM_DIF = 0;
    int WS_TMDIF_TM_DIF = 0;
    double WS_B_US_AMT = 0;
    double WS_S_US_AMT = 0;
    char WS_LVL1 = ' ';
    char WS_LVL2 = ' ';
    String WS_INFO = " ";
    String WS_EX_PIPS = " ";
    char WS_MSG_TYPE = ' ';
    String WS_MSGID = " ";
    int WS_DATE = 0;
    FXCMSG_ERROR_MSG FXCMSG_ERROR_MSG = new FXCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    FXCSDRFX FXCSDRFX = new FXCSDRFX();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCOIEC BPCOIEC = new BPCOIEC();
    WFCCAPV WFCCAPV = new WFCCAPV();
    CICCUST CICCUST = new CICCUST();
    CICQACAC CICQACAC = new CICQACAC();
    CICOCUI CICOCUI = new CICOCUI();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCPCMWD BPCPCMWD = new BPCPCMWD();
    BPCQCCY BPCQCCY = new BPCQCCY();
    DDRMST DDRMST = new DDRMST();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    DDRVCH DDRVCH = new DDRVCH();
    FXCSLLMT FXCSLLMT = new FXCSLLMT();
    BPCPRMR BPCPRMR = new BPCPRMR();
    FXRTMDIF FXRTMDIF = new FXRTMDIF();
    CIRBAS CIRBAS = new CIRBAS();
    BPCFX BPCFX = new BPCFX();
    CICACCU CICACCU = new CICACCU();
    CICSAGEN CICSAGEN = new CICSAGEN();
    BPCUCHBR BPCUCHBR = new BPCUCHBR();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    FXCIBFXT FXCIBFXT = new FXCIBFXT();
    CICQACRI CICQACRI = new CICQACRI();
    BPCPFXOG BPCPFXOG = new BPCPFXOG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    FXCF151 FXCF151 = new FXCF151();
    SCCGWA SCCGWA;
    SCCGAPV SCCGAPV;
    FXB1151_AWA_1151 FXB1151_AWA_1151;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRTLT BPRTLT;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPCPORUP_DATA_INFO BPCPORUP;
    CICGAGA_AGENT_AREA CICGAGA_AGENT_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "FXOT1151 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "FXB1151_AWA_1151>");
        FXB1151_AWA_1151 = (FXB1151_AWA_1151) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
        BPRTLT = (BPRTLT) SCCGWA.COMM_AREA.TLT_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        CICGAGA_AGENT_AREA = new CICGAGA_AGENT_AREA_AGENT_AREA();
        IBS.init(SCCGWA, CICGAGA_AGENT_AREA);
        IBS.CPY2CLS(SCCGWA, SCCGWA.AGENT_AREA_PTR, CICGAGA_AGENT_AREA);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_INPUT_CHECK();
        B020_ADD_REC_PROC();
        if (!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase(WK_MOBILE_CHNL)) {
            B030_SEND_OUTPUT();
        }
    }
    public void B010_INPUT_CHECK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.CI_NO);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.CI_TYP);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.CI_ENM);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.CI_CNM);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.CI_TELNO);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.ID_TYP);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.ID_NO);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.PROD_CD);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.PROD_NM);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.VALUE_DT);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.O_END_DT);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.TIK_NO);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.TRA_AC);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.REG_CD);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.B_CS_FLG);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.PAC_FLG);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.DD_AC1);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.B_SUPAC);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.BUY_CCY);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.BUY_AMT);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.S_CS_FLG);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.CAC_FLG);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.DD_AC2);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.S_SUPAC);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.SELL_CCY);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.SELL_AMT);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.EXR_TYPE);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.RATE_TM);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.EX_TIME);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_TIME);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.EX_CODE);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.SYS_RATE);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.PRE_RATE);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.EX_RATE);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.SPREAD);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.AUTH_LVL);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.EXST_CD1);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.EXST_CD2);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.EXST_CD3);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.AMT_NS1);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.AMT_NS2);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.AMT_NS3);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.DRAW_MTH);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.BU_CHNL);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.CTA_NO);
        CEP.TRC(SCCGWA, K_CNY);
        CEP.TRC(SCCGWA, GWA_SC_AREA.REQ_TYPE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CANCEL_IND);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.ACI_NM);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.AID_TYP);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.AID_NO);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.USE_YRMO);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.SUSE_TYP);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.USE_INF1);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.USE_INF2);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.USE_INF3);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.USE_INF4);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.USE_INF5);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.USE_INF6);
        B010_01_CHK_EX_TM();
        B010_02_CHK_DT();
        B010_03_CHK_CI_TYP();
        B010_04_CHK_CCY();
        B010_05_CHK_AMT();
        B010_06_CHK_PRD_TYP();
        B010_07_CHK_AC();
        B010_09_CHK_BR();
        if (FXB1151_AWA_1151.RATE_TM == 0 
            || FXB1151_AWA_1151.RATE_TM == ' ') {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_RATE_TM_ERR);
        }
        if (FXB1151_AWA_1151.TIK_NO.equalsIgnoreCase("0") 
            || FXB1151_AWA_1151.TIK_NO.trim().length() == 0) {
            B010_10_CHK_RATE_TM();
        }
        if (FXB1151_AWA_1151.TIK_NO.trim().length() > 0 
            && !FXB1151_AWA_1151.TIK_NO.equalsIgnoreCase("0")) {
            B010_13_CHK_BOOK_RATE();
        }
        B010_15_CHK_REG();
        B010_16_CHK_SELL_FX();
        B010_17_CHK_BR_CLOSE();
    }
    public void B010_01_CHK_EX_TM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, FXRTMDIF);
        FXRTMDIF.KEY.TYP = "EXTM";
        FXRTMDIF.KEY.CD = K_EXTM_CD;
        BPCPRMR.DAT_PTR = FXRTMDIF;
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        CEP.TRC(SCCGWA, FXRTMDIF.DATA_TXT.EX_STR_TIME);
        CEP.TRC(SCCGWA, FXRTMDIF.DATA_TXT.EX_END_TIME);
        CEP.TRC(SCCGWA, FXRTMDIF.DATA_TXT.FX_RATE_MIN);
        CEP.TRC(SCCGWA, FXRTMDIF.DATA_TXT.FX_TIK_MIN);
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        CEP.TRC(SCCGWA, JIBS_tmp_str[0].substring(0, 4));
        JIBS_tmp_str[0] = "" + WS_EX_STR_TIME;
        JIBS_f0 = "";
        for (int i=0;i<6-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + WS_EX_STR_TIME;
        JIBS_tmp_str[1] = "" + FXRTMDIF.DATA_TXT.EX_STR_TIME;
        JIBS_tmp_int = JIBS_tmp_str[1].length();
        for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
        JIBS_NumStr = JIBS_tmp_str[1] + JIBS_NumStr.substring(4);
        WS_EX_STR_TIME = Integer.parseInt(JIBS_NumStr);
        JIBS_tmp_str[0] = "" + WS_EX_STR_TIME;
        JIBS_f0 = "";
        for (int i=0;i<6-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + WS_EX_STR_TIME;
        JIBS_NumStr = JIBS_NumStr.substring(0, 5 - 1) + "00" + JIBS_NumStr.substring(5 + 2 - 1);
        WS_EX_STR_TIME = Integer.parseInt(JIBS_NumStr);
        JIBS_tmp_str[0] = "" + WS_EX_END_TIME;
        JIBS_f0 = "";
        for (int i=0;i<6-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + WS_EX_END_TIME;
        JIBS_tmp_str[1] = "" + FXRTMDIF.DATA_TXT.EX_END_TIME;
        JIBS_tmp_int = JIBS_tmp_str[1].length();
        for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
        JIBS_NumStr = JIBS_tmp_str[1] + JIBS_NumStr.substring(4);
        WS_EX_END_TIME = Integer.parseInt(JIBS_NumStr);
        JIBS_tmp_str[0] = "" + WS_EX_END_TIME;
        JIBS_f0 = "";
        for (int i=0;i<6-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + WS_EX_END_TIME;
        JIBS_NumStr = JIBS_NumStr.substring(0, 5 - 1) + "00" + JIBS_NumStr.substring(5 + 2 - 1);
        WS_EX_END_TIME = Integer.parseInt(JIBS_NumStr);
        if (FXB1151_AWA_1151.TIK_NO.equalsIgnoreCase("0") 
            || FXB1151_AWA_1151.TIK_NO.trim().length() == 0) {
            if (SCCGWA.COMM_AREA.TR_TIME > WS_EX_END_TIME 
                || SCCGWA.COMM_AREA.TR_TIME < WS_EX_STR_TIME) {
                CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_NOT_PROCESSING_TM);
            }
        }
    }
    public void B010_02_CHK_DT() throws IOException,SQLException,Exception {
        if (FXB1151_AWA_1151.VALUE_DT == 0 
            || FXB1151_AWA_1151.VALUE_DT > SCCGWA.COMM_AREA.AC_DATE) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_VALUE_DATE_INVALID);
        }
        if (FXB1151_AWA_1151.O_END_DT == 0 
            || FXB1151_AWA_1151.O_END_DT < FXB1151_AWA_1151.VALUE_DT) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_SETDT_LESS_ERROR);
        }
        IBS.init(SCCGWA, BPCPCMWD);
        BPCPCMWD.DATE_TYPE = 'B';
        BPCPCMWD.CHECK_DATE = FXB1151_AWA_1151.VALUE_DT;
        BPCPCMWD.CAL_CODE[1-1].CNTY_CD = WS_BUY_CCY_CNTYCD;
        BPCPCMWD.CAL_CODE[2-1].CNTY_CD = WS_SELL_CCY_CNTYCD;
        BPCPCMWD.CAL_CODE[3-1].CNTY_CD = BPCRBANK.COUN_CD;
        BPCPCMWD.CALCD[1-1] = "CN  ";
        S000_CALL_BPZPCMWD();
        WS_TEMP_DT = BPCPCMWD.NEXT_WORK_DAY_ALL;
        CEP.TRC(SCCGWA, WS_TEMP_DT);
        IBS.init(SCCGWA, BPCPCMWD);
        BPCPCMWD.DATE_TYPE = 'B';
        BPCPCMWD.CHECK_DATE = WS_TEMP_DT;
        BPCPCMWD.CAL_CODE[1-1].CNTY_CD = WS_BUY_CCY_CNTYCD;
        BPCPCMWD.CAL_CODE[2-1].CNTY_CD = WS_SELL_CCY_CNTYCD;
        BPCPCMWD.CAL_CODE[3-1].CNTY_CD = BPCRBANK.COUN_CD;
        BPCPCMWD.CALCD[1-1] = "CN  ";
        S000_CALL_BPZPCMWD();
        WS_SELT_DATE = BPCPCMWD.NEXT_WORK_DAY_ALL;
        CEP.TRC(SCCGWA, WS_SELT_DATE);
        if (FXB1151_AWA_1151.VALUE_DT != FXB1151_AWA_1151.O_END_DT) {
            if (FXB1151_AWA_1151.O_END_DT < WS_TEMP_DT) {
                CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_SETDT_LESS_ERROR);
            }
            if (FXB1151_AWA_1151.O_END_DT > WS_SELT_DATE) {
                CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_OVER_T_2);
            }
        }
        if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase(WK_MOBILE_CHNL)) {
            if (FXB1151_AWA_1151.VALUE_DT != SCCGWA.COMM_AREA.AC_DATE 
                && FXB1151_AWA_1151.O_END_DT != SCCGWA.COMM_AREA.AC_DATE) {
                CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_VAL_END_DT_ERR);
            }
        }
        if (FXB1151_AWA_1151.VALUE_DT < SCCGWA.COMM_AREA.AC_DATE) {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = FXB1151_AWA_1151.VALUE_DT;
            SCCCLDT.MTHS = (short) WK_MONTHS;
            S000_CALL_SCSSCLDT();
            WS_DATE = SCCCLDT.DATE2;
            CEP.TRC(SCCGWA, WS_DATE);
            if (WS_DATE < SCCGWA.COMM_AREA.AC_DATE) {
                CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_BKDT_ERR);
            }
        }
    }
    public void B010_03_CHK_CI_TYP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = FXB1151_AWA_1151.CI_NO;
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        CITBAS_RD.where = "CI_NO = :CIRBAS.KEY.CI_NO";
        IBS.READ(SCCGWA, CIRBAS, this, CITBAS_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, CIRBAS.STSW);
            if (CIRBAS.STSW == null) CIRBAS.STSW = "";
            JIBS_tmp_int = CIRBAS.STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
            if (CIRBAS.STSW.substring(23 - 1, 23 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_CI_MERGE);
            }
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        }
        if ((!FXB1151_AWA_1151.DD_AC1.equalsIgnoreCase("0") 
            && FXB1151_AWA_1151.DD_AC1.trim().length() > 0) 
            || (!FXB1151_AWA_1151.DD_AC2.equalsIgnoreCase("0") 
            && FXB1151_AWA_1151.DD_AC2.trim().length() > 0)) {
            SO00_CALL_CIZCUST();
            if (CICCUST.RC.RC_CODE != 0) {
                CEP.ERR(SCCGWA, CICCUST.RC);
            }
            if (CICCUST.O_DATA.O_CI_TYP != K_CI_TYP_PERSONAL 
                && FXB1151_AWA_1151.AC_TYP1 != 'V' 
                && FXB1151_AWA_1151.AC_TYP2 != 'V') {
                CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_CI_TYPE_ERR);
            }
            if ((!FXB1151_AWA_1151.DD_AC2.equalsIgnoreCase("0") 
                && FXB1151_AWA_1151.DD_AC2.trim().length() > 0) 
                && !FXB1151_AWA_1151.SELL_CCY.equalsIgnoreCase("156")) {
                IBS.init(SCCGWA, CICQACAC);
                CICQACAC.FUNC = 'C';
                CICQACAC.DATA.AGR_NO = FXB1151_AWA_1151.DD_AC2;
                CICQACAC.DATA.CCY_ACAC = FXB1151_AWA_1151.SELL_CCY;
                if (FXB1151_AWA_1151.S_CS_FLG == '0') {
                    CICQACAC.DATA.CR_FLG = '1';
                } else {
                    CICQACAC.DATA.CR_FLG = '2';
                }
                S000_CALL_CIZQACAC();
                CEP.TRC(SCCGWA, CICQACAC.RC.RC_CODE);
                CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
                if (CICQACAC.RC.RC_CODE != 0) {
                    CEP.TRC(SCCGWA, CICCUST.O_DATA.O_EXP_DT);
                    if (CICCUST.O_DATA.O_EXP_DT < FXB1151_AWA_1151.VALUE_DT 
                        || CICCUST.O_DATA.O_EXP_DT < FXB1151_AWA_1151.O_END_DT) {
                        CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_ID_EXP);
                    }
                }
            }
        }
    }
    public void B010_04_CHK_CCY() throws IOException,SQLException,Exception {
        if (FXB1151_AWA_1151.BUY_CCY.trim().length() == 0) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_CCY_MISSED);
        } else {
            IBS.init(SCCGWA, BPCQCCY);
            BPCQCCY.DATA.CCY = FXB1151_AWA_1151.BUY_CCY;
            S000_CALL_BPZQCCY();
            WS_BUY_CCY_CNTYCD = BPCQCCY.DATA.CNTY_CD;
            CEP.TRC(SCCGWA, WS_BUY_CCY_CNTYCD);
        }
        if (FXB1151_AWA_1151.SELL_CCY.trim().length() == 0) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_CCY_MISSED);
        } else {
            IBS.init(SCCGWA, BPCQCCY);
            BPCQCCY.DATA.CCY = FXB1151_AWA_1151.SELL_CCY;
            S000_CALL_BPZQCCY();
            WS_SELL_CCY_CNTYCD = BPCQCCY.DATA.CNTY_CD;
            CEP.TRC(SCCGWA, WS_SELL_CCY_CNTYCD);
        }
        if (FXB1151_AWA_1151.S_CS_FLG == FXB1151_AWA_1151.B_CS_FLG 
            && FXB1151_AWA_1151.BUY_CCY.equalsIgnoreCase(FXB1151_AWA_1151.SELL_CCY)) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_CCY_CSFLG_ERR);
        }
        if (FXB1151_AWA_1151.PROD_CD.equalsIgnoreCase(K_PROD_CD1)) {
            if (!FXB1151_AWA_1151.BUY_CCY.equalsIgnoreCase(K_CNY) 
                && !FXB1151_AWA_1151.SELL_CCY.equalsIgnoreCase(K_CNY)) {
                CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_PROC_CNY);
            }
        } else {
            if (FXB1151_AWA_1151.BUY_CCY.equalsIgnoreCase(K_CNY) 
                || FXB1151_AWA_1151.SELL_CCY.equalsIgnoreCase(K_CNY)) {
                CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_PROC_NOT_CNY);
            }
            if (FXB1151_AWA_1151.BUY_CCY.equalsIgnoreCase(FXB1151_AWA_1151.SELL_CCY) 
                && FXB1151_AWA_1151.B_CS_FLG == K_CASH_FLG_REMIT 
                && FXB1151_AWA_1151.S_CS_FLG == K_CASH_FLG_NOTE) {
                CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_CSFLG_CCY_ERR);
            }
        }
    }
    public void B010_05_CHK_AMT() throws IOException,SQLException,Exception {
        if (FXB1151_AWA_1151.BUY_AMT == 0 
            || FXB1151_AWA_1151.SELL_AMT == 0) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_MUST_INPUT_AMT);
        }
        if (FXB1151_AWA_1151.BUY_CCY.equalsIgnoreCase(K_CNY) 
            || FXB1151_AWA_1151.SELL_CCY.equalsIgnoreCase(K_CNY)) {
            if ((FXB1151_AWA_1151.EXST_CD1.trim().length() > 0 
                && FXB1151_AWA_1151.AMT_NS1 == 0) 
                || (FXB1151_AWA_1151.EXST_CD1.trim().length() == 0 
                && FXB1151_AWA_1151.AMT_NS1 != 0) 
                || (FXB1151_AWA_1151.EXST_CD2.trim().length() > 0 
                && FXB1151_AWA_1151.AMT_NS2 == 0) 
                || (FXB1151_AWA_1151.EXST_CD2.trim().length() == 0 
                && FXB1151_AWA_1151.AMT_NS2 != 0) 
                || (FXB1151_AWA_1151.EXST_CD3.trim().length() > 0 
                && FXB1151_AWA_1151.AMT_NS3 == 0) 
                || (FXB1151_AWA_1151.EXST_CD3.trim().length() == 0 
                && FXB1151_AWA_1151.AMT_NS3 != 0)) {
                CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_EXSTCD_AMT_MUST);
            }
            if (FXB1151_AWA_1151.EXST_CD1.trim().length() == 0 
                && FXB1151_AWA_1151.EXST_CD2.trim().length() == 0 
                && FXB1151_AWA_1151.EXST_CD3.trim().length() == 0) {
                CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_EXSTCD_AMT_MUST);
            } else {
                AMT_NS_TOL = FXB1151_AWA_1151.AMT_NS1 + FXB1151_AWA_1151.AMT_NS2 + FXB1151_AWA_1151.AMT_NS3;
                CEP.TRC(SCCGWA, AMT_NS_TOL);
                if (FXB1151_AWA_1151.BUY_CCY.equalsIgnoreCase(K_CNY)) {
                    CEP.TRC(SCCGWA, "BUY-CCY");
                    if (AMT_NS_TOL != FXB1151_AWA_1151.SELL_AMT) {
                        CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_EXST_AMT_ERR);
                    }
                } else {
                    CEP.TRC(SCCGWA, "SELL-CCY");
                    if (AMT_NS_TOL != FXB1151_AWA_1151.BUY_AMT) {
                        CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_EXST_AMT_ERR);
                    }
                }
            }
        }
        if (FXB1151_AWA_1151.TIK_NO.equalsIgnoreCase("0") 
            || FXB1151_AWA_1151.TIK_NO.trim().length() == 0) {
            B010_05_01_CHK_LIMIT_AMT();
        }
        B010_05_03_CHK_AMT();
    }
    public void B010_05_01_CHK_LIMIT_AMT() throws IOException,SQLException,Exception {
        if (!FXB1151_AWA_1151.BUY_CCY.equalsIgnoreCase(K_CNY)) {
            CEP.TRC(SCCGWA, FXB1151_AWA_1151.BUY_CCY);
            IBS.init(SCCGWA, FXCSLLMT);
            FXCSLLMT.KEY.TRCHNL = SCCGWA.COMM_AREA.CHNL;
            FXCSLLMT.FUNC = 'Q';
            FXCSLLMT.KEY.CCY = FXB1151_AWA_1151.BUY_CCY;
            S000_CALL_FXZSLLMT();
            CEP.TRC(SCCGWA, FXCSLLMT.B_AMT);
            CEP.TRC(SCCGWA, FXCSLLMT.RC.RC_CODE);
            if (FXCSLLMT.RC.RC_CODE == 0) {
                if (FXB1151_AWA_1151.BUY_AMT > FXCSLLMT.B_AMT) {
                    CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_BUY_AMT_OVRLMT);
                }
            } else {
                if (FXB1151_AWA_1151.BUY_CCY.equalsIgnoreCase(K_USD)) {
                    CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_USDLMT_MISS);
                }
                IBS.init(SCCGWA, FXCSLLMT);
                FXCSLLMT.KEY.TRCHNL = SCCGWA.COMM_AREA.CHNL;
                FXCSLLMT.FUNC = 'Q';
                FXCSLLMT.KEY.CCY = K_USD;
                S000_CALL_FXZSLLMT();
                IBS.init(SCCGWA, BPCFX);
                BPCFX.BUY_CCY = FXB1151_AWA_1151.BUY_CCY;
                BPCFX.BUY_AMT = FXB1151_AWA_1151.BUY_AMT;
                BPCFX.SELL_CCY = K_USD;
                BPCFX.B_CASH_FLG = FXB1151_AWA_1151.B_CS_FLG;
                BPCFX.S_CASH_FLG = FXB1151_AWA_1151.B_CS_FLG;
                B010_05_02_CAL_US_AMT();
                WS_B_US_AMT = BPCFX.SELL_AMT;
                if (BPCFX.SELL_AMT > FXCSLLMT.B_AMT) {
                    CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_BUY_AMT_OVRLMT);
                }
            }
        }
        if (!FXB1151_AWA_1151.SELL_CCY.equalsIgnoreCase(K_CNY)) {
            CEP.TRC(SCCGWA, FXB1151_AWA_1151.SELL_CCY);
            IBS.init(SCCGWA, FXCSLLMT);
            FXCSLLMT.KEY.TRCHNL = SCCGWA.COMM_AREA.CHNL;
            FXCSLLMT.KEY.CCY = FXB1151_AWA_1151.SELL_CCY;
            FXCSLLMT.FUNC = 'Q';
            S000_CALL_FXZSLLMT();
            CEP.TRC(SCCGWA, FXCSLLMT.S_AMT);
            CEP.TRC(SCCGWA, FXCSLLMT.RC.RC_CODE);
            if (FXCSLLMT.RC.RC_CODE == 0) {
                if (FXB1151_AWA_1151.SELL_AMT > FXCSLLMT.S_AMT) {
                    CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_SELL_AMT_OVRLMT);
                }
            } else {
                if (FXB1151_AWA_1151.SELL_CCY.equalsIgnoreCase(K_USD)) {
                    CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_USDLMT_MISS);
                }
                FXCSLLMT.KEY.CCY = K_USD;
                S000_CALL_FXZSLLMT();
                IBS.init(SCCGWA, BPCFX);
                BPCFX.SELL_CCY = FXB1151_AWA_1151.SELL_CCY;
                BPCFX.SELL_AMT = FXB1151_AWA_1151.SELL_AMT;
                BPCFX.BUY_CCY = K_USD;
                BPCFX.S_CASH_FLG = FXB1151_AWA_1151.S_CS_FLG;
                BPCFX.B_CASH_FLG = FXB1151_AWA_1151.S_CS_FLG;
                B010_05_02_CAL_US_AMT();
                WS_S_US_AMT = BPCFX.BUY_AMT;
                if (BPCFX.BUY_AMT > FXCSLLMT.S_AMT) {
                    CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_SELL_AMT_OVRLMT);
                }
            }
        }
    }
    public void B010_05_02_CAL_US_AMT() throws IOException,SQLException,Exception {
        BPCFX.FUNC = '3';
        BPCFX.CHNL = SCCGWA.COMM_AREA.CHNL;
        BPCFX.EXR_TYPE = FXB1151_AWA_1151.EXR_TYPE;
        BPCFX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZSFX();
    }
    public void B010_05_03_CHK_AMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFX);
        BPCFX.FUNC = '5';
        BPCFX.CHNL = SCCGWA.COMM_AREA.CHNL;
        BPCFX.EXR_TYPE = FXB1151_AWA_1151.EXR_TYPE;
        BPCFX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCFX.BUY_CCY = FXB1151_AWA_1151.BUY_CCY;
        BPCFX.BUY_AMT = FXB1151_AWA_1151.BUY_AMT;
        BPCFX.B_CASH_FLG = FXB1151_AWA_1151.B_CS_FLG;
        BPCFX.SELL_CCY = FXB1151_AWA_1151.SELL_CCY;
        BPCFX.S_CASH_FLG = FXB1151_AWA_1151.S_CS_FLG;
        BPCFX.TRN_RATE = FXB1151_AWA_1151.EX_RATE;
        S000_CALL_BPZSFX();
        CEP.TRC(SCCGWA, "CHK AMT 1");
        CEP.TRC(SCCGWA, BPCFX.SELL_AMT);
        if (BPCFX.SELL_AMT == FXB1151_AWA_1151.SELL_AMT) {
            WS_CHK_AMT_FLG = 'Y';
        } else {
            WS_CHK_AMT_FLG = 'N';
        }
        if (WS_CHK_AMT_FLG == 'N') {
            IBS.init(SCCGWA, BPCFX);
            BPCFX.FUNC = '5';
            BPCFX.CHNL = SCCGWA.COMM_AREA.CHNL;
            BPCFX.EXR_TYPE = FXB1151_AWA_1151.EXR_TYPE;
            BPCFX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCFX.SELL_AMT = FXB1151_AWA_1151.SELL_AMT;
            BPCFX.SELL_CCY = FXB1151_AWA_1151.SELL_CCY;
            BPCFX.S_CASH_FLG = FXB1151_AWA_1151.S_CS_FLG;
            BPCFX.BUY_CCY = FXB1151_AWA_1151.BUY_CCY;
            BPCFX.B_CASH_FLG = FXB1151_AWA_1151.B_CS_FLG;
            BPCFX.TRN_RATE = FXB1151_AWA_1151.EX_RATE;
            S000_CALL_BPZSFX();
            CEP.TRC(SCCGWA, "CHK AMT 2");
            CEP.TRC(SCCGWA, BPCFX.BUY_AMT);
            if (BPCFX.BUY_AMT == FXB1151_AWA_1151.BUY_AMT) {
                WS_CHK_AMT_FLG = 'Y';
            }
        }
        if (WS_CHK_AMT_FLG == 'N') {
            IBS.init(SCCGWA, BPCFX);
            BPCFX.FUNC = '5';
            BPCFX.CHNL = "CASH1";
            BPCFX.EXR_TYPE = FXB1151_AWA_1151.EXR_TYPE;
            BPCFX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCFX.BUY_CCY = FXB1151_AWA_1151.BUY_CCY;
            BPCFX.BUY_AMT = FXB1151_AWA_1151.BUY_AMT;
            BPCFX.B_CASH_FLG = FXB1151_AWA_1151.B_CS_FLG;
            BPCFX.SELL_CCY = FXB1151_AWA_1151.SELL_CCY;
            BPCFX.S_CASH_FLG = FXB1151_AWA_1151.S_CS_FLG;
            BPCFX.TRN_RATE = FXB1151_AWA_1151.EX_RATE;
            S000_CALL_BPZSFX();
            CEP.TRC(SCCGWA, "CHK AMT 3");
            CEP.TRC(SCCGWA, BPCFX.SELL_AMT);
            if (BPCFX.SELL_AMT == FXB1151_AWA_1151.SELL_AMT) {
                WS_CHK_AMT_FLG = 'Y';
            }
        }
        if (WS_CHK_AMT_FLG == 'N') {
            IBS.init(SCCGWA, BPCFX);
            BPCFX.FUNC = '5';
            BPCFX.CHNL = "CASH1";
            BPCFX.EXR_TYPE = FXB1151_AWA_1151.EXR_TYPE;
            BPCFX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCFX.SELL_AMT = FXB1151_AWA_1151.SELL_AMT;
            BPCFX.SELL_CCY = FXB1151_AWA_1151.SELL_CCY;
            BPCFX.S_CASH_FLG = FXB1151_AWA_1151.S_CS_FLG;
            BPCFX.BUY_CCY = FXB1151_AWA_1151.BUY_CCY;
            BPCFX.B_CASH_FLG = FXB1151_AWA_1151.B_CS_FLG;
            BPCFX.TRN_RATE = FXB1151_AWA_1151.EX_RATE;
            S000_CALL_BPZSFX();
            CEP.TRC(SCCGWA, "CHK AMT 4");
            CEP.TRC(SCCGWA, BPCFX.BUY_AMT);
            if (BPCFX.BUY_AMT == FXB1151_AWA_1151.BUY_AMT) {
                WS_CHK_AMT_FLG = 'Y';
            }
        }
        if (WS_CHK_AMT_FLG == 'N') {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_AMT_ERR);
        }
    }
    public void B010_06_CHK_PRD_TYP() throws IOException,SQLException,Exception {
        if (!FXB1151_AWA_1151.PROD_CD.equalsIgnoreCase(K_PROD_CD1) 
            && !FXB1151_AWA_1151.PROD_CD.equalsIgnoreCase(K_PROD_CD2)) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_PROD_CD_ERR);
        }
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = FXB1151_AWA_1151.PROD_CD;
        S000_CALL_BPZPQPRD();
        CEP.TRC(SCCGWA, BPCPQPRD.CUS_PER_FLG);
        if (BPCPQPRD.CUS_PER_FLG != '0') {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_PRD_NOT_PER);
        }
        if (FXB1151_AWA_1151.PROD_CD.equalsIgnoreCase(K_PROD_CD1)) {
            SCCGWA.COMM_AREA.TR_MMO = "P624";
        }
        if (FXB1151_AWA_1151.PROD_CD.equalsIgnoreCase(K_PROD_CD2)) {
            SCCGWA.COMM_AREA.TR_MMO = "P625";
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_MMO);
    }
    public void B010_07_CHK_AC() throws IOException,SQLException,Exception {
        if (FXB1151_AWA_1151.BUY_CCY.equalsIgnoreCase("156")) {
            if (FXB1151_AWA_1151.B_CS_FLG != ' ') {
                CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_CASH_FLG_INVALID);
            }
        } else {
            if (FXB1151_AWA_1151.B_CS_FLG != K_CASH_FLG_NOTE 
                && FXB1151_AWA_1151.B_CS_FLG != K_CASH_FLG_REMIT) {
                CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_CASH_FLG_INVALID);
            }
        }
        if (FXB1151_AWA_1151.SELL_CCY.equalsIgnoreCase("156")) {
            if (FXB1151_AWA_1151.S_CS_FLG != ' ') {
                CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_CASH_FLG_INVALID);
            }
        } else {
            if (FXB1151_AWA_1151.S_CS_FLG != K_CASH_FLG_NOTE 
                && FXB1151_AWA_1151.S_CS_FLG != K_CASH_FLG_REMIT) {
                CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_CASH_FLG_INVALID);
            }
        }
        if (!FXB1151_AWA_1151.BUY_CCY.equalsIgnoreCase("156")) {
            if ((FXB1151_AWA_1151.PAC_FLG == K_AC_FLG_CASH 
                && FXB1151_AWA_1151.B_CS_FLG != K_CASH_FLG_NOTE)) {
                CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_CASH_FLG_INVALID);
            }
        }
        if (!FXB1151_AWA_1151.SELL_CCY.equalsIgnoreCase("156")) {
            if ((FXB1151_AWA_1151.CAC_FLG == K_AC_FLG_CASH 
                && FXB1151_AWA_1151.S_CS_FLG != K_CASH_FLG_NOTE)) {
                CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_CASH_FLG_INVALID);
            }
        }
        if (FXB1151_AWA_1151.PAC_FLG == K_AC_FLG_DDAC) {
            if (FXB1151_AWA_1151.DD_AC1.trim().length() == 0) {
                CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_DDAC1_MUST);
            } else {
                IBS.init(SCCGWA, CICACCU);
                CICACCU.DATA.AGR_NO = FXB1151_AWA_1151.DD_AC1;
                S000_CALL_CISOACCU();
                if (!CICACCU.DATA.CI_NO.equalsIgnoreCase(FXB1151_AWA_1151.CI_NO)) {
                    CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_CUS_AC_ERR);
                }
                if (CICACCU.DATA.CI_TYP != K_CI_TYP_PERSONAL 
                    && FXB1151_AWA_1151.AC_TYP1 != 'V') {
                    CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_AC_ERR);
                }
                IBS.init(SCCGWA, DDCIQBAL);
                DDCIQBAL.DATA.AC = FXB1151_AWA_1151.DD_AC1;
                if (!FXB1151_AWA_1151.BUY_CCY.equalsIgnoreCase("156")) {
                    if (FXB1151_AWA_1151.B_CS_FLG == '0') {
                        DDCIQBAL.DATA.CCY_TYPE = '1';
                    } else {
                        DDCIQBAL.DATA.CCY_TYPE = '2';
                    }
                }
                DDCIQBAL.DATA.CCY = FXB1151_AWA_1151.BUY_CCY;
                R000_CALL_DDZIQBAL();
                CEP.TRC(SCCGWA, DDCIQBAL.DATA.AVL_BAL);
                if (DDCIQBAL.DATA.AVL_BAL < FXB1151_AWA_1151.BUY_AMT) {
                    CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_BAL_INV);
                }
            }
        }
        if (FXB1151_AWA_1151.CAC_FLG == K_AC_FLG_DDAC) {
            if (FXB1151_AWA_1151.DD_AC2.trim().length() == 0) {
                CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_DDAC2_MUST);
            } else {
                IBS.init(SCCGWA, CICACCU);
                CICACCU.DATA.AGR_NO = FXB1151_AWA_1151.DD_AC2;
                S000_CALL_CISOACCU();
                if (!CICACCU.DATA.CI_NO.equalsIgnoreCase(FXB1151_AWA_1151.CI_NO)) {
                    CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_CUS_AC_ERR);
                }
                if (CICACCU.DATA.CI_TYP != K_CI_TYP_PERSONAL 
                    && FXB1151_AWA_1151.AC_TYP2 != 'V') {
                    CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_AC_ERR);
                }
            }
        }
        if ((FXB1151_AWA_1151.PAC_FLG == K_AC_FLG_SUPAC 
            || FXB1151_AWA_1151.PAC_FLG == K_AC_FLG_SUPAC_1) 
            && FXB1151_AWA_1151.B_SUPAC.trim().length() == 0) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_B_SUPAC_MIS);
        }
        if ((FXB1151_AWA_1151.CAC_FLG == K_AC_FLG_SUPAC 
            || FXB1151_AWA_1151.CAC_FLG == K_AC_FLG_SUPAC_1) 
            && FXB1151_AWA_1151.S_SUPAC.trim().length() == 0) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_S_SUPAC_ERR);
        }
        if (FXB1151_AWA_1151.PAC_FLG != K_AC_FLG_CASH 
            && FXB1151_AWA_1151.PAC_FLG != K_AC_FLG_DDAC 
            && FXB1151_AWA_1151.PAC_FLG != K_AC_FLG_SUPAC 
            && FXB1151_AWA_1151.PAC_FLG != K_AC_FLG_SUPAC_1) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_PAC_ERR);
        }
        if (FXB1151_AWA_1151.CAC_FLG != K_AC_FLG_CASH 
            && FXB1151_AWA_1151.CAC_FLG != K_AC_FLG_DDAC 
            && FXB1151_AWA_1151.CAC_FLG != K_AC_FLG_SUPAC 
            && FXB1151_AWA_1151.CAC_FLG != K_AC_FLG_SUPAC_1) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_CAC_ERR);
        }
        if (FXB1151_AWA_1151.VALUE_DT < FXB1151_AWA_1151.O_END_DT) {
            if (FXB1151_AWA_1151.PAC_FLG != K_AC_FLG_DDAC) {
                CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_PAC_ERR);
            }
            if (FXB1151_AWA_1151.CAC_FLG == K_AC_FLG_CASH) {
                CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_CAC_ERR);
            }
        }
        if (FXB1151_AWA_1151.BS_TYPE.equalsIgnoreCase("02") 
            && FXB1151_AWA_1151.PROD_CD.equalsIgnoreCase(K_PROD_CD1) 
            && ((!FXB1151_AWA_1151.BUY_CCY.equalsIgnoreCase("156") 
            && FXB1151_AWA_1151.AC_TYP1 != 'V') 
            || (!FXB1151_AWA_1151.SELL_CCY.equalsIgnoreCase("156") 
            && FXB1151_AWA_1151.AC_TYP2 != 'V'))) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_AC_TYP_ERR);
        }
        if ((FXB1151_AWA_1151.AC_TYP1 != ' ' 
            && FXB1151_AWA_1151.AC_TYP1 != 'V' 
            && FXB1151_AWA_1151.AC_TYP1 != 'W') 
            || (FXB1151_AWA_1151.AC_TYP2 != ' ' 
            && FXB1151_AWA_1151.AC_TYP2 != 'V' 
            && FXB1151_AWA_1151.AC_TYP2 != 'W')) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_AC_TYP_ERR);
        }
        if ((FXB1151_AWA_1151.AC_TYP1 == 'V' 
            && FXB1151_AWA_1151.B_CS_FLG != K_CASH_FLG_REMIT) 
            || (FXB1151_AWA_1151.AC_TYP2 == 'V' 
            && FXB1151_AWA_1151.S_CS_FLG != K_CASH_FLG_REMIT)) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_CASH_FLG_FCY_ERR);
        }
    }
    public void B010_09_CHK_BR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        CEP.TRC(SCCGWA, BPCPQORG.FX_BUSI);
        if (!(SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase(WK_MOBILE_CHNL))) {
            if (BPCPQORG.FX_BUSI.equalsIgnoreCase("00") 
                || BPCPQORG.FX_BUSI.equalsIgnoreCase("02")) {
                CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_BR_FX_INV);
            }
        }
    }
    public void B010_10_CHK_RATE_TM() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = "" + FXB1151_AWA_1151.RATE_TM;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 2).trim().length() == 0) WS_EX_HOUR = 0;
        else WS_EX_HOUR = Integer.parseInt(JIBS_tmp_str[0].substring(0, 2));
        JIBS_tmp_str[0] = "" + FXB1151_AWA_1151.RATE_TM;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1).trim().length() == 0) WS_EX_MINU = 0;
        else WS_EX_MINU = Integer.parseInt(JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1));
        JIBS_tmp_str[0] = "" + FXB1151_AWA_1151.RATE_TM;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_EX_SECO = 0;
        else WS_EX_SECO = Integer.parseInt(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 2).trim().length() == 0) WS_TR_HOUR = 0;
        else WS_TR_HOUR = Integer.parseInt(JIBS_tmp_str[0].substring(0, 2));
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1).trim().length() == 0) WS_TR_MINU = 0;
        else WS_TR_MINU = Integer.parseInt(JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1));
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_TR_SECO = 0;
        else WS_TR_SECO = Integer.parseInt(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
        CEP.TRC(SCCGWA, WS_EX_HOUR);
        CEP.TRC(SCCGWA, WS_EX_MINU);
        CEP.TRC(SCCGWA, WS_EX_SECO);
        CEP.TRC(SCCGWA, WS_TR_HOUR);
        CEP.TRC(SCCGWA, WS_TR_MINU);
        CEP.TRC(SCCGWA, WS_TR_SECO);
        WS_EX_SEC = WS_EX_HOUR * 3600 + WS_EX_MINU * 60 + WS_EX_SECO;
        WS_TR_SEC = WS_TR_HOUR * 3600 + WS_TR_MINU * 60 + WS_TR_SECO;
        if (WS_EX_SEC > WS_TR_SEC) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_RT_TM_ERR);
        } else {
            WS_TM_DIF = WS_TR_SEC - WS_EX_SEC;
        }
        if (GWA_SC_AREA.REQ_TYPE == 'F' 
            || GWA_SC_AREA.REQ_TYPE == 'A') {
            WS_TMDIF_TM_DIF = FXRTMDIF.DATA_TXT.FX_RATE_MIN * 60 - 30;
        } else {
            WS_TMDIF_TM_DIF = FXRTMDIF.DATA_TXT.FX_RATE_MIN * 60;
        }
        CEP.TRC(SCCGWA, WS_TM_DIF);
        CEP.TRC(SCCGWA, WS_TMDIF_TM_DIF);
        if (WS_TM_DIF > WS_TMDIF_TM_DIF) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_RATE_TIMEOUT);
        }
    }
    public void B010_13_CHK_BOOK_RATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FXCIBFXT);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.TIK_NO);
        FXCIBFXT.KEY.TIK_NO = FXB1151_AWA_1151.TIK_NO;
        FXCIBFXT.FUNC = 'I';
        S000_CALL_FXZIBFXT();
        CEP.TRC(SCCGWA, FXCIBFXT.PROD_CD);
        if (FXCIBFXT.STATUS != K_FXT_STS_UNUSED) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_TIK_STS_INVALID);
        }
        if (SCCGWA.COMM_AREA.AC_DATE < FXCIBFXT.VALUE_DT) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_TIK_VALDT_ERR);
        }
        if (SCCGWA.COMM_AREA.AC_DATE > FXCIBFXT.OPT_END_DT) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_TIK_ENDDT_ERR);
        }
        B010_13_01_CHK_TIK_TM();
        if (!FXB1151_AWA_1151.CI_NO.equalsIgnoreCase(FXCIBFXT.CI_NO)) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_TIK_CINO_ERR);
        }
        CEP.TRC(SCCGWA, FXCIBFXT.TRA_AC);
        if (!FXB1151_AWA_1151.TRA_AC.equalsIgnoreCase(FXCIBFXT.TRA_AC)) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_TIK_TRAC_ERR);
        }
        CEP.TRC(SCCGWA, FXCIBFXT.B_CS_FLG);
        CEP.TRC(SCCGWA, FXCIBFXT.S_CS_FLG);
        if (FXB1151_AWA_1151.B_CS_FLG != FXCIBFXT.B_CS_FLG 
            || FXB1151_AWA_1151.S_CS_FLG != FXCIBFXT.S_CS_FLG) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_TIK_CSFLG_ERR);
        }
        if (!FXB1151_AWA_1151.BUY_CCY.equalsIgnoreCase(FXCIBFXT.BUY_CCY) 
            || !FXB1151_AWA_1151.SELL_CCY.equalsIgnoreCase(FXCIBFXT.SELL_CCY)) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_TIK_CCY_ERR);
        }
        if (FXB1151_AWA_1151.EX_RATE != FXCIBFXT.EX_RATE) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_TIK_RATE_ERR);
        }
        CEP.TRC(SCCGWA, "UPDATE-STS-START");
        B010_13_02_TIK_UPD();
        CEP.TRC(SCCGWA, "UPDATE-STS-END");
    }
    public void B010_13_01_CHK_TIK_TM() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = "" + FXCIBFXT.TRA_TM;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 2).trim().length() == 0) WS_EX_HOUR = 0;
        else WS_EX_HOUR = Integer.parseInt(JIBS_tmp_str[0].substring(0, 2));
        JIBS_tmp_str[0] = "" + FXCIBFXT.TRA_TM;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1).trim().length() == 0) WS_EX_MINU = 0;
        else WS_EX_MINU = Integer.parseInt(JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1));
        JIBS_tmp_str[0] = "" + FXCIBFXT.TRA_TM;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_EX_SECO = 0;
        else WS_EX_SECO = Integer.parseInt(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 2).trim().length() == 0) WS_TR_HOUR = 0;
        else WS_TR_HOUR = Integer.parseInt(JIBS_tmp_str[0].substring(0, 2));
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1).trim().length() == 0) WS_TR_MINU = 0;
        else WS_TR_MINU = Integer.parseInt(JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1));
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_TR_SECO = 0;
        else WS_TR_SECO = Integer.parseInt(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
        CEP.TRC(SCCGWA, WS_EX_HOUR);
        CEP.TRC(SCCGWA, WS_EX_MINU);
        CEP.TRC(SCCGWA, WS_EX_SECO);
        CEP.TRC(SCCGWA, WS_TR_HOUR);
        CEP.TRC(SCCGWA, WS_TR_MINU);
        CEP.TRC(SCCGWA, WS_TR_SECO);
        WS_EX_SEC = WS_EX_HOUR * 3600 + WS_EX_MINU * 60 + WS_EX_SECO;
        WS_TR_SEC = WS_TR_HOUR * 3600 + WS_TR_MINU * 60 + WS_TR_SECO;
        if (WS_EX_SEC > WS_TR_SEC) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_RT_TM_ERR);
        } else {
            WS_TM_DIF = WS_TR_SEC - WS_EX_SEC;
        }
        WS_TMDIF_TM_DIF = FXRTMDIF.DATA_TXT.FX_TIK_MIN * 60;
        CEP.TRC(SCCGWA, WS_TM_DIF);
        CEP.TRC(SCCGWA, WS_TMDIF_TM_DIF);
        if (WS_TM_DIF > WS_TMDIF_TM_DIF) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_TIK_TIMEOUT);
        }
    }
    public void B010_13_02_TIK_UPD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FXCIBFXT);
        FXCIBFXT.KEY.TIK_NO = FXB1151_AWA_1151.TIK_NO;
        FXCIBFXT.FUNC = 'U';
        S000_CALL_FXZIBFXT();
    }
    public void B010_15_CHK_REG() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.REG_CD);
        if (FXB1151_AWA_1151.PROD_CD.equalsIgnoreCase(K_PROD_CD1)) {
            if (FXB1151_AWA_1151.BS_TYPE.trim().length() == 0) {
                CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_BS_TYP_ERR);
            }
            if (FXB1151_AWA_1151.SELL_CCY.equalsIgnoreCase("156")) {
                if (FXB1151_AWA_1151.STL_CODE.trim().length() == 0) {
                    CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_STL_CD_ERR);
                }
            }
            if (FXB1151_AWA_1151.AC_TYP1 == 'G' 
                || FXB1151_AWA_1151.AC_TYP2 == 'G') {
                if (FXB1151_AWA_1151.TX_CD.trim().length() == 0) {
                    CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_TX_CD_ERR);
                }
                if (FXB1151_AWA_1151.CAP_CD.trim().length() == 0) {
                    CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_CA_CD_ERR);
                }
                if (FXB1151_AWA_1151.SELL_CCY.equalsIgnoreCase("156")) {
                    if (FXB1151_AWA_1151.USE_TYPE.trim().length() == 0) {
                        CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_USE_TYP_ERR);
                    }
                    if (FXB1151_AWA_1151.USE_DTL.trim().length() == 0) {
                        CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_USE_DTL_ERR);
                    }
                }
                if (FXB1151_AWA_1151.CI_TELNO.trim().length() == 0) {
                    CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_CI_TELNO_ERR);
                }
            }
        }
    }
    public void B010_16_CHK_SELL_FX() throws IOException,SQLException,Exception {
        if (FXB1151_AWA_1151.PROD_CD.equalsIgnoreCase(K_PROD_CD1) 
            && !FXB1151_AWA_1151.SELL_CCY.equalsIgnoreCase("156")) {
            CEP.TRC(SCCGWA, FXB1151_AWA_1151.USE_YRMO);
            if (FXB1151_AWA_1151.USE_YRMO == 0) {
                CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_USE_YRMO_ERR);
            }
            CEP.TRC(SCCGWA, FXB1151_AWA_1151.SUSE_TYP);
            if (FXB1151_AWA_1151.SUSE_TYP.trim().length() == 0) {
                CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_USE_TYPE_ERR);
            }
        }
    }
    public void B010_17_CHK_BR_CLOSE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUCHBR);
        BPCUCHBR.FUNC = 'F';
        BPCUCHBR.OLD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCUCHBR.ORGI_FLG = '0';
        S000_CALL_BPZUCHBR();
        CEP.TRC(SCCGWA, BPCUCHBR.INCO_DATE);
        if (BPCUCHBR.RC.RC_CODE == 0) {
            if (BPCUCHBR.INCO_DATE <= FXB1151_AWA_1151.O_END_DT) {
                CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_BR_CLS_MGR);
            }
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUCHBR.RC);
            if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
                CEP.ERR(SCCGWA, BPCUCHBR.RC);
            }
        }
    }
    public void B010_20_CHK_DEV_AUTH() throws IOException,SQLException,Exception {
        if (FXB1151_AWA_1151.AUTH_LVL.trim().length() > 0) {
            if (FXB1151_AWA_1151.AUTH_LVL == null) FXB1151_AWA_1151.AUTH_LVL = "";
            JIBS_tmp_int = FXB1151_AWA_1151.AUTH_LVL.length();
            for (int i=0;i<2-JIBS_tmp_int;i++) FXB1151_AWA_1151.AUTH_LVL += " ";
            WS_LVL1 = FXB1151_AWA_1151.AUTH_LVL.substring(0, 1).charAt(0);
            WS_LVL2 = '0';
            if (IBS.isNumeric(WS_LVL1+"")) {
                WS_INFO = " ";
                if (WS_INFO == null) WS_INFO = "";
                JIBS_tmp_int = WS_INFO.length();
                for (int i=0;i<120-JIBS_tmp_int;i++) WS_INFO += " ";
                WS_INFO = "该汇率偏差需集中授权�?" + WS_INFO.substring(33);
                if (WS_INFO == null) WS_INFO = "";
                JIBS_tmp_int = WS_INFO.length();
                for (int i=0;i<120-JIBS_tmp_int;i++) WS_INFO += " ";
                WS_INFO = WS_INFO.substring(0, 34 - 1) + "偏差= " + WS_INFO.substring(34 + 9 - 1);
                df = new DecimalFormat("#####0.0000");
                WS_EX_PIPS = df.format(FXB1151_AWA_1151.SPREAD);
                if (WS_INFO == null) WS_INFO = "";
                JIBS_tmp_int = WS_INFO.length();
                for (int i=0;i<120-JIBS_tmp_int;i++) WS_INFO += " ";
                if (WS_EX_PIPS == null) WS_EX_PIPS = "";
                JIBS_tmp_int = WS_EX_PIPS.length();
                for (int i=0;i<12-JIBS_tmp_int;i++) WS_EX_PIPS += " ";
                WS_INFO = WS_INFO.substring(0, 43 - 1) + WS_EX_PIPS + WS_INFO.substring(43 + 12 - 1);
                WS_MSG_TYPE = 'A';
                SCCMSG SCCMSG = new SCCMSG();
                IBS.init(SCCGWA, SCCMSG);
                SCCMSG.MSGID = FXCMSG_ERROR_MSG.FX_RATE_DEV_AUTH;
                SCCMSG.TYPE = WS_MSG_TYPE;
                SCCMSG.LVL.LVL1 = WS_LVL1;
                SCCMSG.LVL.LVL2 = WS_LVL2;
                SCCMSG.INFO = WS_INFO;
                CEP.ERR(SCCGWA, SCCMSG);
                CEP.TRC(SCCGWA, SCCMSG.MSGID);
                CEP.TRC(SCCGWA, SCCMSG.LVL.LVL1);
                CEP.TRC(SCCGWA, SCCMSG.LVL.LVL2);
                CEP.TRC(SCCGWA, WS_INFO);
            }
        }
    }
    public void B020_ADD_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FXCSDRFX);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.EXST_CD1);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.EXST_CD2);
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.EXST_CD3);
        FXCSDRFX.FUNC = 'A';
        FXCSDRFX.CI_NO = FXB1151_AWA_1151.CI_NO;
        FXCSDRFX.CI_TYP = FXB1151_AWA_1151.CI_TYP;
        FXCSDRFX.ID_TYP = FXB1151_AWA_1151.ID_TYP;
        FXCSDRFX.ID_NO = FXB1151_AWA_1151.ID_NO;
        FXCSDRFX.CI_ENM = FXB1151_AWA_1151.CI_ENM;
        FXCSDRFX.TRA_AC = FXB1151_AWA_1151.TRA_AC;
        FXCSDRFX.CI_CNM = FXB1151_AWA_1151.CI_CNM;
        FXCSDRFX.CI_TELNO = FXB1151_AWA_1151.CI_TELNO;
        FXCSDRFX.PROD_CD = FXB1151_AWA_1151.PROD_CD;
        FXCSDRFX.PROD_TYP = "FX";
        FXCSDRFX.PROD_NM = FXB1151_AWA_1151.PROD_NM;
        FXCSDRFX.VALUE_DT = FXB1151_AWA_1151.VALUE_DT;
        FXCSDRFX.O_END_DT = FXB1151_AWA_1151.O_END_DT;
        if (GWA_SC_AREA.REQ_TYPE == 'F') {
            FXCSDRFX.O_END_DT = FXB1151_AWA_1151.VALUE_DT;
        }
        CEP.TRC(SCCGWA, FXCSDRFX.O_END_DT);
        FXCSDRFX.PAC_FLG = FXB1151_AWA_1151.PAC_FLG;
        FXCSDRFX.DD_AC1 = FXB1151_AWA_1151.DD_AC1;
        FXCSDRFX.AC_TYP1 = FXB1151_AWA_1151.AC_TYP1;
        FXCSDRFX.DRAW_MTH = FXB1151_AWA_1151.DRAW_MTH;
        FXCSDRFX.PAY_PSW = FXB1151_AWA_1151.PAY_PSW;
        FXCSDRFX.BUY_CCY = FXB1151_AWA_1151.BUY_CCY;
        FXCSDRFX.BUY_AMT = FXB1151_AWA_1151.BUY_AMT;
        FXCSDRFX.B_CS_FLG = FXB1151_AWA_1151.B_CS_FLG;
        FXCSDRFX.B_SUPAC = FXB1151_AWA_1151.B_SUPAC;
        FXCSDRFX.CAC_FLG = FXB1151_AWA_1151.CAC_FLG;
        FXCSDRFX.DD_AC2 = FXB1151_AWA_1151.DD_AC2;
        FXCSDRFX.AC_TYP2 = FXB1151_AWA_1151.AC_TYP2;
        FXCSDRFX.SELL_CCY = FXB1151_AWA_1151.SELL_CCY;
        FXCSDRFX.SELL_AMT = FXB1151_AWA_1151.SELL_AMT;
        FXCSDRFX.S_CS_FLG = FXB1151_AWA_1151.S_CS_FLG;
        FXCSDRFX.S_SUPAC = FXB1151_AWA_1151.S_SUPAC;
        FXCSDRFX.REV_NO = FXB1151_AWA_1151.REV_NO;
        FXCSDRFX.EXR_TYPE = FXB1151_AWA_1151.EXR_TYPE;
        FXCSDRFX.RATE_TM = FXB1151_AWA_1151.RATE_TM;
        FXCSDRFX.EX_TIME = SCCGWA.COMM_AREA.TR_TIME;
        FXCSDRFX.TIK_NO = FXB1151_AWA_1151.TIK_NO;
        FXCSDRFX.SYS_RATE = FXB1151_AWA_1151.SYS_RATE;
        FXCSDRFX.PRE_RATE = FXB1151_AWA_1151.PRE_RATE;
        FXCSDRFX.SPREAD = FXB1151_AWA_1151.SPREAD;
        FXCSDRFX.AUTH_LVL = FXB1151_AWA_1151.AUTH_LVL;
        FXCSDRFX.EX_RATE = FXB1151_AWA_1151.EX_RATE;
        FXCSDRFX.REF_NO = FXB1151_AWA_1151.REF_NO;
        FXCSDRFX.EXST_CD1 = FXB1151_AWA_1151.EXST_CD1;
        FXCSDRFX.AMT_NS1 = FXB1151_AWA_1151.AMT_NS1;
        FXCSDRFX.EXST_CD2 = FXB1151_AWA_1151.EXST_CD2;
        FXCSDRFX.AMT_NS2 = FXB1151_AWA_1151.AMT_NS2;
        FXCSDRFX.EXST_CD3 = FXB1151_AWA_1151.EXST_CD3;
        FXCSDRFX.AMT_NS3 = FXB1151_AWA_1151.AMT_NS3;
        FXCSDRFX.BS_TYPE = FXB1151_AWA_1151.BS_TYPE;
        FXCSDRFX.STL_CODE = FXB1151_AWA_1151.STL_CODE;
        FXCSDRFX.REG_CD = FXB1151_AWA_1151.REG_CD;
        FXCSDRFX.TX_CD = FXB1151_AWA_1151.TX_CD;
        FXCSDRFX.CAP_CD = FXB1151_AWA_1151.CAP_CD;
        FXCSDRFX.USE_TYPE = FXB1151_AWA_1151.USE_TYPE;
        FXCSDRFX.USE_DTL = FXB1151_AWA_1151.USE_DTL;
        FXCSDRFX.REG_NAME = FXB1151_AWA_1151.REG_NAME;
        FXCSDRFX.RMK = FXB1151_AWA_1151.RMK;
        FXCSDRFX.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        FXCSDRFX.TRN_DT = SCCGWA.COMM_AREA.AC_DATE;
        FXCSDRFX.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        if (FXB1151_AWA_1151.BUY_CCY.equalsIgnoreCase(FXB1151_AWA_1151.SELL_CCY)) {
            if (FXB1151_AWA_1151.EX_CODE == null) FXB1151_AWA_1151.EX_CODE = "";
            JIBS_tmp_int = FXB1151_AWA_1151.EX_CODE.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) FXB1151_AWA_1151.EX_CODE += " ";
            if (FXB1151_AWA_1151.BUY_CCY == null) FXB1151_AWA_1151.BUY_CCY = "";
            JIBS_tmp_int = FXB1151_AWA_1151.BUY_CCY.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) FXB1151_AWA_1151.BUY_CCY += " ";
            FXB1151_AWA_1151.EX_CODE = FXB1151_AWA_1151.BUY_CCY + FXB1151_AWA_1151.EX_CODE.substring(3);
            if (FXB1151_AWA_1151.EX_CODE == null) FXB1151_AWA_1151.EX_CODE = "";
            JIBS_tmp_int = FXB1151_AWA_1151.EX_CODE.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) FXB1151_AWA_1151.EX_CODE += " ";
            if (FXB1151_AWA_1151.BUY_CCY == null) FXB1151_AWA_1151.BUY_CCY = "";
            JIBS_tmp_int = FXB1151_AWA_1151.BUY_CCY.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) FXB1151_AWA_1151.BUY_CCY += " ";
            FXB1151_AWA_1151.EX_CODE = FXB1151_AWA_1151.EX_CODE.substring(0, 4 - 1) + FXB1151_AWA_1151.BUY_CCY + FXB1151_AWA_1151.EX_CODE.substring(4 + 3 - 1);
        } else {
            IBS.init(SCCGWA, BPCOIEC);
            BPCOIEC.CCY1 = FXB1151_AWA_1151.BUY_CCY;
            BPCOIEC.CCY2 = FXB1151_AWA_1151.SELL_CCY;
            BPCOIEC.EXR_TYP = FXB1151_AWA_1151.EXR_TYPE;
            S000_CALL_BPZSIEC();
            FXB1151_AWA_1151.EX_CODE = BPCOIEC.EXR_CODE;
        }
        CEP.TRC(SCCGWA, FXB1151_AWA_1151.EX_CODE);
        FXCSDRFX.EX_CODE = FXB1151_AWA_1151.EX_CODE;
        FXCSDRFX.TYP_SER = '2';
        FXCSDRFX.CTA_TYPE = '0';
        FXCSDRFX.B_US_AMT = WS_B_US_AMT;
        FXCSDRFX.S_US_AMT = WS_S_US_AMT;
        if (FXB1151_AWA_1151.PROD_CD.equalsIgnoreCase(K_PROD_CD1)) {
            if (FXB1151_AWA_1151.BUY_CCY.equalsIgnoreCase(K_CNY)) {
                FXCSDRFX.B_S_FLG = '2';
            }
            if (FXB1151_AWA_1151.SELL_CCY.equalsIgnoreCase(K_CNY)) {
                FXCSDRFX.B_S_FLG = '1';
            }
        }
        if (FXB1151_AWA_1151.PROD_CD.equalsIgnoreCase(K_PROD_CD2)) {
            if (FXB1151_AWA_1151.EX_CODE == null) FXB1151_AWA_1151.EX_CODE = "";
            JIBS_tmp_int = FXB1151_AWA_1151.EX_CODE.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) FXB1151_AWA_1151.EX_CODE += " ";
            if (FXB1151_AWA_1151.BUY_CCY.equalsIgnoreCase(FXB1151_AWA_1151.EX_CODE.substring(0, 3))) {
                FXCSDRFX.B_S_FLG = '3';
            }
            if (FXB1151_AWA_1151.EX_CODE == null) FXB1151_AWA_1151.EX_CODE = "";
            JIBS_tmp_int = FXB1151_AWA_1151.EX_CODE.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) FXB1151_AWA_1151.EX_CODE += " ";
            if (FXB1151_AWA_1151.SELL_CCY.equalsIgnoreCase(FXB1151_AWA_1151.EX_CODE.substring(0, 3))) {
                FXCSDRFX.B_S_FLG = '4';
            }
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.BBR);
        FXCSDRFX.ST_CHNL = SCCGWA.COMM_AREA.CHNL;
        if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase(WK_MOBILE_CHNL)) {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.FUNC = 'A';
            CICQACRI.DATA.AGR_NO = FXB1151_AWA_1151.DD_AC1;
            S000_CALL_CIZQACRI();
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_OPN_BR);
            IBS.init(SCCGWA, BPCPFXOG);
            BPCPFXOG.DATA_INFO.BR = CICQACRI.O_DATA.O_OPN_BR;
            BPCPFXOG.OUTPUT_FMT = K_OUTPUT_FMT;
            BPCPFXOG.INFO.FUNC = 'B';
            S000_CALL_BPZPFXOG();
            CEP.TRC(SCCGWA, BPCPFXOG.DATA_INFO.FX_BR);
            FXCSDRFX.AC_BR = BPCPFXOG.DATA_INFO.FX_BR;
        } else {
            FXCSDRFX.AC_BR = BPCPORUP.DATA_INFO.BBR;
        }
        if (FXB1151_AWA_1151.VALUE_DT < SCCGWA.COMM_AREA.AC_DATE) {
            FXCSDRFX.BKDT_FLG = 'Y';
            FXCSDRFX.TRAN_RAT = (double)SCCGWA.COMM_AREA.AC_DATE;
        }
        if (FXB1151_AWA_1151.O_END_DT <= SCCGWA.COMM_AREA.AC_DATE) {
            FXCSDRFX.STATUS = 'C';
        } else {
            FXCSDRFX.STATUS = 'U';
        }
        FXCSDRFX.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        FXCSDRFX.ACI_NM = FXB1151_AWA_1151.ACI_NM;
        FXCSDRFX.AID_TYP = FXB1151_AWA_1151.AID_TYP;
        FXCSDRFX.AID_NO = FXB1151_AWA_1151.AID_NO;
        FXCSDRFX.USE_YRMO = FXB1151_AWA_1151.USE_YRMO;
        FXCSDRFX.SUSE_TYP = FXB1151_AWA_1151.SUSE_TYP;
        FXCSDRFX.USE_INF1 = FXB1151_AWA_1151.USE_INF1;
        FXCSDRFX.USE_INF2 = FXB1151_AWA_1151.USE_INF2;
        FXCSDRFX.USE_INF3 = FXB1151_AWA_1151.USE_INF3;
        FXCSDRFX.USE_INF4 = FXB1151_AWA_1151.USE_INF4;
        FXCSDRFX.USE_INF5 = FXB1151_AWA_1151.USE_INF5;
        FXCSDRFX.USE_INF6 = FXB1151_AWA_1151.USE_INF6;
        FXCSDRFX.IMG_NO = FXB1151_AWA_1151.IMG_NO;
        S000_CALL_FXZSDRFX();
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AGENT_FLG);
        if (SCCGWA.COMM_AREA.AGENT_FLG == 'Y') {
            B240_AGENT_INF_PORC();
        }
    }
    public void B030_SEND_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FXCF151);
        FXCF151.UPD_BR = FXCSDRFX.UPD_BR;
        FXCF151.TRN_DT = FXCSDRFX.TRN_DT;
        FXCF151.SEQ = FXCSDRFX.SEQ;
        FXCF151.JRN_NO = FXCSDRFX.JRN_NO;
        FXCF151.CTA_NO = FXCSDRFX.CTA_NO;
        FXCF151.STATUS = FXCSDRFX.STATUS;
        FXCF151.TIK_NO = FXCSDRFX.TIK_NO;
        FXCF151.PROD_CD = FXCSDRFX.PROD_CD;
        FXCF151.PROD_NM = FXCSDRFX.PROD_NM;
        FXCF151.CI_NO = FXCSDRFX.CI_NO;
        FXCF151.CI_TYP = FXCSDRFX.CI_TYP;
        FXCF151.CI_CNM = FXCSDRFX.CI_CNM;
        FXCF151.CI_ENM = FXCSDRFX.CI_ENM;
        FXCF151.TRA_AC = FXCSDRFX.TRA_AC;
        FXCF151.VALUE_DT = FXCSDRFX.VALUE_DT;
        FXCF151.O_END_DT = FXCSDRFX.O_END_DT;
        FXCF151.PAC_FLG = FXCSDRFX.PAC_FLG;
        FXCF151.DD_AC1 = FXCSDRFX.DD_AC1;
        FXCF151.AC_TYP1 = FXCSDRFX.AC_TYP1;
        FXCF151.DRAW_MTH = FXCSDRFX.DRAW_MTH;
        FXCF151.PAY_PSW = FXCSDRFX.PAY_PSW;
        FXCF151.B_CS_FLG = FXCSDRFX.B_CS_FLG;
        FXCF151.BUY_CCY = FXCSDRFX.BUY_CCY;
        FXCF151.BUY_AMT = FXCSDRFX.BUY_AMT;
        FXCF151.B_SUPAC = FXCSDRFX.B_SUPAC;
        FXCF151.CAC_FLG = FXCSDRFX.CAC_FLG;
        FXCF151.DD_AC2 = FXCSDRFX.DD_AC2;
        FXCF151.AC_TYP2 = FXCSDRFX.AC_TYP2;
        FXCF151.REV_NO = FXCSDRFX.REV_NO;
        FXCF151.S_CS_FLG = FXCSDRFX.S_CS_FLG;
        FXCF151.SELL_CCY = FXCSDRFX.SELL_CCY;
        FXCF151.SELL_AMT = FXCSDRFX.SELL_AMT;
        FXCF151.S_SUPAC = FXCSDRFX.S_SUPAC;
        FXCF151.EX_CODE = FXCSDRFX.EX_CODE;
        FXCF151.EX_TIME = FXCSDRFX.EX_TIME;
        FXCF151.EXR_TYPE = FXCSDRFX.EXR_TYPE;
        FXCF151.RATE_TM = FXCSDRFX.RATE_TM;
        FXCF151.SYS_RATE = FXCSDRFX.SYS_RATE;
        FXCF151.PRE_RATE = FXCSDRFX.PRE_RATE;
        FXCF151.SPREAD = FXCSDRFX.SPREAD;
        FXCF151.AUTH_LVL = FXCSDRFX.AUTH_LVL;
        FXCF151.EX_RATE = FXCSDRFX.EX_RATE;
        FXCF151.REF_NO = FXCSDRFX.REF_NO;
        FXCF151.EXST_CD1 = FXCSDRFX.EXST_CD1;
        FXCF151.AMT_NS1 = FXCSDRFX.AMT_NS1;
        FXCF151.EXST_CD2 = FXCSDRFX.EXST_CD2;
        FXCF151.AMT_NS2 = FXCSDRFX.AMT_NS2;
        FXCF151.EXST_CD3 = FXCSDRFX.EXST_CD3;
        FXCF151.AMT_NS3 = FXCSDRFX.AMT_NS3;
        FXCF151.ST_CHNL = FXCSDRFX.ST_CHNL;
        FXCF151.AC_BR = FXCSDRFX.AC_BR;
        FXCF151.BS_TYPE = FXCSDRFX.BS_TYPE;
        FXCF151.STL_CODE = FXCSDRFX.STL_CODE;
        FXCF151.REG_CD = FXCSDRFX.REG_CD;
        FXCF151.TX_CD = FXCSDRFX.TX_CD;
        FXCF151.CAP_CD = FXCSDRFX.CAP_CD;
        FXCF151.USE_TYPE = FXCSDRFX.USE_TYPE;
        FXCF151.USE_DTL = FXCSDRFX.USE_DTL;
        FXCF151.REG_NAME = FXCSDRFX.REG_NAME;
        FXCF151.BKDT_FLG = FXCSDRFX.BKDT_FLG;
        FXCF151.CRT_TLR = FXCSDRFX.CRT_TLR;
        FXCF151.RMK = FXCSDRFX.RMK;
        FXCF151.ACI_NM = FXCSDRFX.ACI_NM;
        FXCF151.AID_TYP = FXCSDRFX.AID_TYP;
        FXCF151.AID_NO = FXCSDRFX.AID_NO;
        FXCF151.USE_YRMO = FXCSDRFX.USE_YRMO;
        FXCF151.SUSE_TYP = FXCSDRFX.SUSE_TYP;
        FXCF151.USE_INF1 = FXCSDRFX.USE_INF1;
        FXCF151.USE_INF2 = FXCSDRFX.USE_INF2;
        FXCF151.USE_INF3 = FXCSDRFX.USE_INF3;
        FXCF151.USE_INF4 = FXCSDRFX.USE_INF4;
        FXCF151.USE_INF5 = FXCSDRFX.USE_INF5;
        FXCF151.USE_INF6 = FXCSDRFX.USE_INF6;
        CEP.TRC(SCCGWA, FXCF151.SEQ);
        CEP.TRC(SCCGWA, FXCF151.JRN_NO);
        CEP.TRC(SCCGWA, FXCF151.STATUS);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "FX151";
        SCCFMT.DATA_PTR = FXCF151;
        SCCFMT.DATA_LEN = 3458;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B240_AGENT_INF_PORC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSAGEN);
        CICSAGEN.FUNC = 'A';
        CICSAGEN.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        CICSAGEN.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        CICSAGEN.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        CICSAGEN.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CICSAGEN.TLR_NO = SCCGWA.COMM_AREA.TL_ID;
        CICSAGEN.CI_NO = FXB1151_AWA_1151.CI_NO;
        CICSAGEN.ID_TYP = CICGAGA_AGENT_AREA.ID_TYP;
        CICSAGEN.ID_NO = CICGAGA_AGENT_AREA.ID_NO;
        CICSAGEN.AGENT_TP = "04";
        CICSAGEN.CI_NAME = CICGAGA_AGENT_AREA.CI_NM;
        CICSAGEN.PHONE = CICGAGA_AGENT_AREA.PHONE;
        S000_CALL_CIZSAGEN();
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPQPRD.RC);
        }
    }
    public void SO00_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.DATA.CI_NO = FXB1151_AWA_1151.CI_NO;
        CICCUST.FUNC = 'C';
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND);
        }
    }
    public void S000_CALL_FXZSDRFX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "FX-S-MAIN-DRFX", FXCSDRFX);
    }
    public void R000_GET_UPPER_BRANCE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG);
    }
    public void S000_CALL_BPZSIEC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_INQ_EXR_CODE, BPCOIEC);
        if (BPCOIEC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCOIEC.RC);
        }
    }
    public void S000_CALL_BPZPCMWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-MUL-WORK-DAY", BPCPCMWD);
        CEP.TRC(SCCGWA, BPCPCMWD.RC.RC_CODE);
        if (BPCPCMWD.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPCMWD.RC);
        }
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICQACRI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACRI.RC.RC_CODE);
        }
    }
    public void S000_CALL_BPZPFXOG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_QUERY_FXOG_INFO, BPCPFXOG);
        if (BPCPFXOG.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPFXOG.RC);
        }
    }
    public void S000_CALL_CISOACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_FXZSLLMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "FX-S-MAIN-LLMT", FXCSLLMT);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
    }
    public void S000_CALL_BPZSFX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-FX", BPCFX);
        if (BPCFX.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCFX.RC);
        }
    }
    public void R000_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_I_INQ_BAL, DDCIQBAL);
        if (DDCIQBAL.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DDCIQBAL.RC);
        }
    }
    public void S000_CALL_FXZIBFXT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "FX-INQUIRE-UP-BFXT", FXCIBFXT);
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCCLDT.DATE1);
        CEP.TRC(SCCGWA, SCCCLDT.DATE2);
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            CEP.TRC(SCCGWA, "SCSSCLDT");
            if (WS_MSGID == null) WS_MSGID = "";
            JIBS_tmp_int = WS_MSGID.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) WS_MSGID += " ";
            WS_MSGID = "SC" + WS_MSGID.substring(2);
            if (WS_MSGID == null) WS_MSGID = "";
            JIBS_tmp_int = WS_MSGID.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) WS_MSGID += " ";
            JIBS_tmp_str[0] = "" + SCCCLDT.RC;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            WS_MSGID = WS_MSGID.substring(0, 3 - 1) + JIBS_tmp_str[0] + WS_MSGID.substring(3 + 4 - 1);
            CEP.ERR(SCCGWA, WS_MSGID);
        }
    }
    public void S000_CALL_CIZSAGEN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SVR-CIZSAGEN", CICSAGEN);
        if (CICSAGEN.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSAGEN.RC);
        }
    }
    public void S000_CALL_BPZUCHBR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-BPZUCHBR", BPCUCHBR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
