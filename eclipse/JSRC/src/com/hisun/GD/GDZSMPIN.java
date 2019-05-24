package com.hisun.GD;

import com.hisun.DD.*;
import com.hisun.DC.*;
import com.hisun.SC.*;
import com.hisun.AI.*;
import com.hisun.BP.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class GDZSMPIN {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm GDTHIS_RD;
    brParm GDTHIS_BR = new brParm();
    DBParm DDTCCY_RD;
    String K_OUTPUT_FMT = "GD110";
    String CPN_U_ADD_CBOX = "BP-U-ADD-CBOX      ";
    String K_CASH_NO = "120";
    String K_OBJ_SYSTEM = "ESBP";
    String WS_ERR_MSG = " ";
    GDZSMPIN_WS_TXCTA_NO WS_TXCTA_NO = new GDZSMPIN_WS_TXCTA_NO();
    String WS_DRYW_TYP = " ";
    String WS_OTHER_AC = " ";
    String WS_CI_NO2 = " ";
    GDZSMPIN_WS_GYL_NOTI WS_GYL_NOTI = new GDZSMPIN_WS_GYL_NOTI();
    char WS_REC_FLG = ' ';
    char WS_REC_PLDR_FLG = ' ';
    char WS_HIS_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    GDCOMPIN GDCOMPIN = new GDCOMPIN();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    AICPIAEV AICPIAEV = new AICPIAEV();
    AICUUPIA AICUUPIA = new AICUUPIA();
    GDRPLDR GDRPLDR = new GDRPLDR();
    BPCUABOX BPCUABOX = new BPCUABOX();
    BPCUSBOX BPCUSBOX = new BPCUSBOX();
    GDCSMPRL GDCSMPRL = new GDCSMPRL();
    DDCRMST DDCRMST = new DDCRMST();
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA = new SCCGBPA_BP_AREA();
    CICACCU CICACCU = new CICACCU();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    GDCUMPLD GDCUMPLD = new GDCUMPLD();
    GDCRHIS GDCRHIS = new GDCRHIS();
    GDRHIS GDRHIS = new GDRHIS();
    DDRCCY DDRCCY = new DDRCCY();
    BPCPARMC BPCPARMC = new BPCPARMC();
    BPCPRMR BPCPRMR = new BPCPRMR();
    CICQACRI CICQACRI = new CICQACRI();
    AICPQMIB AICPQMIB = new AICPQMIB();
    BPCPQORG BPCPQORG = new BPCPQORG();
    CICSOEC CICSOEC = new CICSOEC();
    SCCTPCL SCCTPCL = new SCCTPCL();
    DCCUCINF DCCUCINF = new DCCUCINF();
    DDRMST DDRMST = new DDRMST();
    SCCGWA SCCGWA;
    GDCSMPIN GDCSMPIN;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA, GDCSMPIN GDCSMPIN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.GDCSMPIN = GDCSMPIN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, WS_DRYW_TYP);
        if (WS_DRYW_TYP.equalsIgnoreCase("12")) {
            B030_NOTI_GYL();
        }
        CEP.TRC(SCCGWA, "GDZSMPIN return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHK_INPUT_DATA();
        B020_GET_CI_DATA();
        B025_POST_TXN_AMT_PROC();
        if (GDCSMPIN.VAL.RL_FLG == '0' 
            && SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B080_CALL_RELAT_PROC();
        }
        B090_OUTPUT_PROCESS();
    }
    public void B010_CHK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCSMPIN.VAL.CI_NO);
        CEP.TRC(SCCGWA, GDCSMPIN.VAL.CI_NM);
        CEP.TRC(SCCGWA, GDCSMPIN.VAL.DD_AC1);
        CEP.TRC(SCCGWA, GDCSMPIN.VAL.DR_CARD1);
        CEP.TRC(SCCGWA, GDCSMPIN.VAL.CI_NM1);
        CEP.TRC(SCCGWA, GDCSMPIN.VAL.PROD_CD);
        CEP.TRC(SCCGWA, GDCSMPIN.VAL.CCY);
        CEP.TRC(SCCGWA, GDCSMPIN.VAL.CCY_TYP);
        CEP.TRC(SCCGWA, GDCSMPIN.VAL.AMT);
        CEP.TRC(SCCGWA, GDCSMPIN.VAL.STL_MTH);
        CEP.TRC(SCCGWA, GDCSMPIN.VAL.REV_NO);
        CEP.TRC(SCCGWA, GDCSMPIN.VAL.DD_AC2);
        CEP.TRC(SCCGWA, GDCSMPIN.VAL.DR_CARD2);
        CEP.TRC(SCCGWA, GDCSMPIN.VAL.CI_NM2);
        CEP.TRC(SCCGWA, GDCSMPIN.VAL.CHQ_NO);
        CEP.TRC(SCCGWA, GDCSMPIN.VAL.ISSUE_DT);
        CEP.TRC(SCCGWA, GDCSMPIN.VAL.PAY_AC);
        CEP.TRC(SCCGWA, GDCSMPIN.VAL.BRANCH);
        CEP.TRC(SCCGWA, GDCSMPIN.VAL.PAY_CI_NM);
        CEP.TRC(SCCGWA, GDCSMPIN.VAL.RL_FLG);
        CEP.TRC(SCCGWA, GDCSMPIN.VAL.CTA_NO);
        CEP.TRC(SCCGWA, GDCSMPIN.VAL.REF_NO);
        CEP.TRC(SCCGWA, GDCSMPIN.VAL.BSTYP);
        CEP.TRC(SCCGWA, GDCSMPIN.VAL.RL_AMT);
        CEP.TRC(SCCGWA, GDCSMPIN.VAL.EXP_DT);
        CEP.TRC(SCCGWA, GDCSMPIN.VAL.RL_SEQ);
        if (GDCSMPIN.VAL.STL_MTH == '1' 
            || GDCSMPIN.VAL.STL_MTH == '3') {
            if (GDCSMPIN.VAL.DD_AC2.trim().length() == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
                S000_ERR_MSG_PROC();
            }
        }
        if (GDCSMPIN.VAL.STL_MTH == '0' 
            || GDCSMPIN.VAL.STL_MTH == '1' 
            || GDCSMPIN.VAL.STL_MTH == '3' 
            || GDCSMPIN.VAL.STL_MTH == ' ') {
            CEP.TRC(SCCGWA, "NEXT SENTENCE");
        } else {
            CEP.TRC(SCCGWA, GDCSMPIN.VAL.STL_MTH);
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_STL_MTH_INVAILD;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, DDCRMST);
        IBS.init(SCCGWA, DDRMST);
        DDCRMST.FUNC = 'I';
        DDRMST.KEY.CUS_AC = GDCSMPIN.VAL.DD_AC1;
        DDCRMST.REC_PTR = DDRMST;
        DDCRMST.REC_LEN = 425;
        S000_CALL_DDZRMST();
        CEP.TRC(SCCGWA, "AAAAAA");
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (DDCRMST.RETURN_INFO == 'N') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_TO_MST_NOTFND;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, DDRMST.AC_STS);
        if (DDRMST.AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_TO_AC_CLOSE;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, DDRMST.AC_TYPE);
        if (DDRMST.AC_TYPE != 'N') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_NOT_GD_AC;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, DDRMST.YW_TYP);
        WS_DRYW_TYP = DDRMST.YW_TYP;
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = GDCSMPIN.VAL.DD_AC2;
        S000_CALL_CIZQACRI();
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
            if (GDCSMPIN.VAL.STL_MTH == '3') {
                DDCRMST.FUNC = 'I';
                DDRMST.KEY.CUS_AC = GDCSMPIN.VAL.DD_AC2;
                DDCRMST.REC_PTR = DDRMST;
                DDCRMST.REC_LEN = 425;
                S000_CALL_DDZRMST();
                CEP.TRC(SCCGWA, "BBBBBB");
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
                if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                    if (DDCRMST.RETURN_INFO == 'N') {
                        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FR_MST_NOTFND;
                        S000_ERR_MSG_PROC();
                    }
                }
            }
            CEP.TRC(SCCGWA, WS_DRYW_TYP);
            CEP.TRC(SCCGWA, DDRMST.AC_TYPE);
            if (GDCSMPIN.VAL.STL_MTH == '3') {
                IBS.init(SCCGWA, DDRCCY);
                DDRCCY.CUS_AC = GDCSMPIN.VAL.DD_AC2;
                DDRCCY.CCY = GDCSMPIN.VAL.CCY;
                DDRCCY.CCY_TYPE = GDCSMPIN.VAL.CCY_TYP;
                T000_READ_DDTCCY();
                if (DDRMST.AC_TYPE == 'N' 
                    && !WS_DRYW_TYP.equalsIgnoreCase("06")) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_TYP_ERR;
                    CEP.ERR(SCCGWA, WS_ERR_MSG);
                }
                if (DDRMST.AC_STS == 'C') {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FR_AC_CLOSE;
                    CEP.ERR(SCCGWA, WS_ERR_MSG);
                }
                if (DDRMST.AC_STS == 'M') {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.AC_CLOSED_AND_GET_INT;
                    CEP.ERR(SCCGWA, WS_ERR_MSG);
                }
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                if (DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1") 
                    || DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.AC_OFFICE_FORBID;
                    CEP.ERR(SCCGWA, WS_ERR_MSG);
                }
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                if (DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1") 
                    || DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.AC_FREEZE_CNOT_TR;
                    CEP.ERR(SCCGWA, WS_ERR_MSG);
                }
                if (DDRMST.AC_STS == 'D') {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_IS_LONG_HOVER;
                    CEP.ERR(SCCGWA, WS_ERR_MSG);
                }
                if (DDRMST.AC_STS == 'V') {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NOT_CHECKED;
                    CEP.ERR(SCCGWA, WS_ERR_MSG);
                }
                if (DDRMST.AC_STS == 'O') {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NOT_ACTIVE;
                    CEP.ERR(SCCGWA, WS_ERR_MSG);
                }
            }
        } else {
            if (GDCSMPIN.VAL.STL_MTH == '3') {
                IBS.init(SCCGWA, DDRCCY);
                DDRCCY.CUS_AC = GDCSMPIN.VAL.DD_AC2;
                DDRCCY.CCY = GDCSMPIN.VAL.CCY;
                DDRCCY.CCY_TYPE = GDCSMPIN.VAL.CCY_TYP;
                T000_READ_DDTCCY();
                IBS.init(SCCGWA, DCCUCINF);
                DCCUCINF.CARD_NO = GDCSMPIN.VAL.DD_AC2;
                S000_CALL_DCZUCINF();
                CEP.TRC(SCCGWA, DCCUCINF.CARD_STS);
                CEP.TRC(SCCGWA, DCCUCINF.CARD_STSW);
                if (DCCUCINF.CARD_STS == 'C') {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_CANCEL_STS;
                    CEP.ERR(SCCGWA, WS_ERR_MSG);
                }
                if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
                JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
                for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
                if (DCCUCINF.CARD_STSW.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ALR_CARD_FORB_STS;
                    CEP.ERR(SCCGWA, WS_ERR_MSG);
                }
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                if (DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1") 
                    || DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.AC_OFFICE_FORBID;
                    CEP.ERR(SCCGWA, WS_ERR_MSG);
                }
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                if (DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1") 
                    || DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.AC_FREEZE_CNOT_TR;
                    CEP.ERR(SCCGWA, WS_ERR_MSG);
                }
            }
        }
        if (!IBS.isNumeric(GWA_SC_AREA.BA_CNT+"")) {
            GWA_SC_AREA.BA_CNT = 0;
        }
    }
    public void B020_GET_CI_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = GDCSMPIN.VAL.DD_AC1;
        S000_CALL_CIZACCU();
        GDCSMPIN.VAL.CI_NM = CICACCU.DATA.AC_CNM;
        GDCSMPIN.VAL.CI_NO = CICACCU.DATA.CI_NO;
    }
    public void B030_NOTI_GYL() throws IOException,SQLException,Exception {
        WS_GYL_NOTI.WS_TRCN_SEQ_NO = SCCGWA.COMM_AREA.JRN_NO;
        WS_GYL_NOTI.WS_PAY_ACC_NO = GDCSMPIN.VAL.DD_AC2;
        WS_GYL_NOTI.WS_PAY_ACC_NME = GDCSMPIN.VAL.CI_NM2;
        WS_GYL_NOTI.WS_CCY = GDCSMPIN.VAL.CCY;
        WS_GYL_NOTI.WS_CLCTN_ACC_NO = GDCSMPIN.VAL.DD_AC1;
        WS_GYL_NOTI.WS_CLCTN_ACC_NME = GDCSMPIN.VAL.CI_NM1;
        WS_GYL_NOTI.WS_BSN_TYP = '1';
        WS_GYL_NOTI.WS_TXN_AMT = GDCSMPIN.VAL.AMT;
        WS_GYL_NOTI.WS_TXN_DTE = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = WS_GYL_NOTI.WS_TXN_DTE.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) WS_GYL_NOTI.WS_TXN_DTE = "0" + WS_GYL_NOTI.WS_TXN_DTE;
        WS_GYL_NOTI.WS_TXN_TIM = " ";
        WS_GYL_NOTI.WS_SMY_CD = " ";
        WS_GYL_NOTI.WS_RMK = " ";
        IBS.init(SCCGWA, SCCTPCL);
        SCCTPCL.SERV_AREA.OBJ_SYSTEM = K_OBJ_SYSTEM;
        SCCTPCL.SERV_AREA.SERV_CODE = "5001200001501";
        CEP.TRC(SCCGWA, SCCTPCL.SERV_AREA.SERV_CODE);
        SCCTPCL.SERV_AREA.SERV_TYPE = ' ';
        SCCTPCL.INP_AREA.SERV_DATA_LEN = 875;
        SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, WS_GYL_NOTI);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_CSIF_NOTICE);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_BVMS_INQ);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_BH_FILE_SEND);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_BH_FILE_ECIF);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_AC_CHG_INF);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ACGL_GL0002);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_PASSWORD_CHK);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_SMS_INFO);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ECIF_SG0001);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_CHG_NOTICE);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ACGL_GL0001);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ECIF_AC0001);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.SN_SMS_NOTICE);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ECIF_AC0002);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.CC_CHANGE_CARD);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.SC_SCF_NOTICE);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ECIF_AC0003);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_IBIL_NOTICE);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_BROADCAST);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_CASH_CHG);
        CEP.TRC(SCCGWA, GDCSMPIN.VAL.BSTYP);
        S000_CALL_SCZTPCL();
    }
    public void B025_POST_TXN_AMT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCSMPIN.VAL.STL_MTH);
        CEP.TRC(SCCGWA, GDCSMPIN.VAL.REV_NO);
        if (GDCSMPIN.VAL.STL_MTH == '1') {
            B041_CALL_AI_DR_UNT();
        }
        if (GDCSMPIN.VAL.STL_MTH == '3') {
            if (GDCSMPIN.VAL.DD_AC2.trim().length() > 0) {
                CICACCU.DATA.AGR_NO = GDCSMPIN.VAL.DD_AC2;
                S000_CALL_CIZACCU();
                WS_CI_NO2 = CICACCU.DATA.CI_NO;
                CEP.TRC(SCCGWA, WS_CI_NO2);
                CEP.TRC(SCCGWA, GDCSMPIN.VAL.CI_NO);
                if (!WS_CI_NO2.equalsIgnoreCase(GDCSMPIN.VAL.CI_NO)) {
                    IBS.init(SCCGWA, CICSOEC);
                    CICSOEC.DATA.CI_NO = WS_CI_NO2;
                    CICSOEC.DATA.READ_ONLY_FLG = 'Y';
                    S000_CALL_CIZSOEC();
                    CEP.TRC(SCCGWA, CICSOEC.DATA.CI_NO);
                    CEP.TRC(SCCGWA, CICSOEC.DATA.SPECIAL_CI_NO);
                    if (!CICSOEC.DATA.SPECIAL_CI_NO.equalsIgnoreCase(GDCSMPIN.VAL.CI_NO)) {
                        WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CI_NO_NOT_SAME;
                        S000_ERR_MSG_PROC();
                    }
                }
            }
            B040_WITHDRAW_PROC();
        }
        if (GDCSMPIN.VAL.STL_MTH == '0') {
            B020_CASH_PROC();
        }
        B050_DEPOSIT_PROC();
    }
    public void B020_CASH_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUABOX);
        BPCUABOX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCUABOX.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUABOX.CCY = GDCSMPIN.VAL.CCY;
        BPCUABOX.AMT = GDCSMPIN.VAL.AMT;
        CEP.TRC(SCCGWA, BPCUABOX.CCY);
        CEP.TRC(SCCGWA, BPCUABOX.AMT);
        BPCUABOX.OPP_AC = GDCSMPIN.VAL.DD_AC1;
        BPCUABOX.CASH_NO = K_CASH_NO;
        CEP.TRC(SCCGWA, BPCUABOX.CASH_NO);
        S000_CALL_BPZUABOX();
    }
    public void B040_WITHDRAW_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GWA_BP_AREA.VCH_AREA.VCH_MAX_CNT);
        IBS.init(SCCGWA, DDCUDRAC);
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.CHK_PSW_FLG = 'N';
        DDCUDRAC.GD_WITHDR_FLG = 'Y';
        DDCUDRAC.AC = GDCSMPIN.VAL.DD_AC2;
        DDCUDRAC.CCY = GDCSMPIN.VAL.CCY;
        DDCUDRAC.CCY_TYPE = GDCSMPIN.VAL.CCY_TYP;
        DDCUDRAC.TX_AMT = GDCSMPIN.VAL.AMT;
        DDCUDRAC.OTHER_AC = GDCSMPIN.VAL.DD_AC1;
        WS_OTHER_AC = DDCUDRAC.OTHER_AC;
        B170_02_GET_RLT_BR_INFO();
        DDCUDRAC.OTHER_AC_NM = CICQACRI.O_DATA.O_AC_CNM;
        DDCUDRAC.OTHER_BR = "" + CICQACRI.O_DATA.O_OPN_BR;
        JIBS_tmp_int = DDCUDRAC.OTHER_BR.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) DDCUDRAC.OTHER_BR = "0" + DDCUDRAC.OTHER_BR;
        DDCUDRAC.OTHER_BK_NM = BPCPQORG.CHN_NM;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            DDCUDRAC.VAL_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        } else {
            DDCUDRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        DDCUDRAC.CHQ_ISS_DATE = GDCSMPIN.VAL.ISSUE_DT;
        DDCUDRAC.NARRATIVE = " ";
        DDCUDRAC.REMARKS = " ";
        if (GDCSMPIN.VAL.MMO.trim().length() == 0) {
            DDCUDRAC.TX_MMO = "A019";
        } else {
            CEP.TRC(SCCGWA, GDCSMPIN.VAL.MMO);
            B051_SMPIN_MMO_CON();
            DDCUDRAC.TX_MMO = GDCSMPIN.VAL.MMO;
        }
        DDCUDRAC.CHQ_TYPE = GDCSMPIN.VAL.PN_TYP;
        DDCUDRAC.CHQ_NO = GDCSMPIN.VAL.CHQ_NO;
        DDCUDRAC.PAY_PSWD = GDCSMPIN.VAL.TXPSW;
        DDCUDRAC.PSWD = GDCSMPIN.VAL.TXPSW2;
        S000_CALL_DDZUDRAC();
    }
    public void B041_CALL_AI_DR_UNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = GDCSMPIN.VAL.DD_AC2;
        CEP.TRC(SCCGWA, GDCSMPIN.VAL.DD_AC2);
        CEP.TRC(SCCGWA, AICUUPIA.DATA.AC_NO);
        AICUUPIA.DATA.RVS_SEQ = 0;
        AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            AICUUPIA.DATA.VALUE_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        } else {
            AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        AICUUPIA.DATA.AMT = GDCSMPIN.VAL.AMT;
        AICUUPIA.DATA.CCY = GDCSMPIN.VAL.CCY;
        AICUUPIA.DATA.EVENT_CODE = "DR";
        AICUUPIA.DATA.POST_NARR = " ";
        AICUUPIA.DATA.RVS_NO = GDCSMPIN.VAL.REV_NO;
        AICUUPIA.DATA.PAY_MAN = GDCSMPIN.VAL.CI_NM2;
        AICUUPIA.DATA.EVENT_CODE = "DR";
        S000_CALL_AIZUUPIA();
        CEP.TRC(SCCGWA, AICUUPIA.RC.RC_CODE);
        if (AICUUPIA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            CEP.TRC(SCCGWA, "000000000000000000000000");
            S000_ERR_MSG_PROC();
            CEP.TRC(SCCGWA, "111111111111111111111111");
        }
    }
    public void B050_DEPOSIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUCRAC);
        if (GDCSMPIN.VAL.STL_MTH == '0') {
            DDCUCRAC.TX_TYPE = 'C';
        } else {
            DDCUCRAC.TX_TYPE = 'T';
        }
        DDCUCRAC.GD_WITHDR_FLG = 'Y';
        DDCUCRAC.AC = GDCSMPIN.VAL.DD_AC1;
        DDCUCRAC.CCY = GDCSMPIN.VAL.CCY;
        DDCUCRAC.CCY_TYPE = GDCSMPIN.VAL.CCY_TYP;
        DDCUCRAC.TX_AMT = GDCSMPIN.VAL.AMT;
        DDCUCRAC.CARD_NO = GDCSMPIN.VAL.DR_CARD1;
        DDCUCRAC.OTHER_AC = GDCSMPIN.VAL.DD_AC2;
        if (GDCSMPIN.VAL.STL_MTH != '0') {
            WS_OTHER_AC = DDCUCRAC.OTHER_AC;
            B170_02_GET_RLT_BR_INFO();
            DDCUCRAC.OTHER_AC_NM = CICQACRI.O_DATA.O_AC_CNM;
            DDCUCRAC.OTHER_BR = "" + CICQACRI.O_DATA.O_OPN_BR;
            JIBS_tmp_int = DDCUCRAC.OTHER_BR.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) DDCUCRAC.OTHER_BR = "0" + DDCUCRAC.OTHER_BR;
            DDCUCRAC.OTHER_BK_NM = BPCPQORG.CHN_NM;
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            DDCUCRAC.VAL_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        } else {
            DDCUCRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        DDCUCRAC.NARRATIVE = " ";
        DDCUCRAC.REMARKS = " ";
        CEP.TRC(SCCGWA, DDCUCRAC.AC_NAME);
        if (GDCSMPIN.VAL.MMO.trim().length() == 0) {
            CEP.TRC(SCCGWA, GDCSMPIN.VAL.MMO);
            DDCUCRAC.TX_MMO = "A001";
        } else {
            B051_SMPIN_MMO_CON();
            DDCUCRAC.TX_MMO = GDCSMPIN.VAL.MMO;
        }
        S000_CALL_DDZUCRAC();
        CEP.TRC(SCCGWA, GDCSMPIN.VAL.DD_AC1);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            IBS.init(SCCGWA, GDRHIS);
            GDRHIS.KEY.TR_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            GDRHIS.KEY.JRNNO = GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
            GDRHIS.AC = GDCSMPIN.VAL.DD_AC1;
            T000_STARTBR_BY_DTJRN();
            T000_READNEXT_HIS_PROC();
            while (WS_HIS_FLG != 'N') {
                if (GDRHIS.CAN_FLG == 'N') {
                    GDRHIS.CAN_FLG = 'C';
                    T000_UPDATE_GDTHIS();
                    R000_WRITE_HIS_PROC();
                }
                T000_READNEXT_HIS_PROC();
            }
            T000_ENDBR_HIS_PROC();
        } else {
            R000_WRITE_HIS_PROC();
        }
    }
    public void B051_SMPIN_MMO_CON() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, BPCPARMC);
        BPCPRMR.FUNC = ' ';
        BPCPARMC.KEY.TYP = "PARMC";
        if (BPCPARMC.KEY.CD == null) BPCPARMC.KEY.CD = "";
        JIBS_tmp_int = BPCPARMC.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPCPARMC.KEY.CD += " ";
        BPCPARMC.KEY.CD = "MMO" + BPCPARMC.KEY.CD.substring(3);
        if (BPCPARMC.KEY.CD == null) BPCPARMC.KEY.CD = "";
        JIBS_tmp_int = BPCPARMC.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPCPARMC.KEY.CD += " ";
        if (GDCSMPIN.VAL.MMO == null) GDCSMPIN.VAL.MMO = "";
        JIBS_tmp_int = GDCSMPIN.VAL.MMO.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) GDCSMPIN.VAL.MMO += " ";
        BPCPARMC.KEY.CD = BPCPARMC.KEY.CD.substring(0, 6 - 1) + GDCSMPIN.VAL.MMO + BPCPARMC.KEY.CD.substring(6 + 9 - 1);
        BPCPRMR.DAT_PTR = BPCPARMC;
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        CEP.TRC(SCCGWA, BPCPRMR.RC.RC_RTNCODE);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase("BP0180")) {
                CEP.ERR(SCCGWA, GDCMSG_ERROR_MSG.GD_MMO_NOT_EXIST);
            } else {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
                CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
            }
        }
    }
    public void B080_CALL_RELAT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GWA_SC_AREA.BA_CNT);
        IBS.init(SCCGWA, GDCUMPLD);
        GDCUMPLD.FUNC = 'C';
        GDCUMPLD.AC = GDCSMPIN.VAL.DD_AC1;
        GDCUMPLD.AC_TYPE = '0';
        GDCUMPLD.CCY = GDCSMPIN.VAL.CCY;
        if (GDCSMPIN.VAL.CCY.equalsIgnoreCase("156")) {
            GDCUMPLD.CCY_TYPE = '1';
        } else {
            GDCUMPLD.CCY_TYPE = '2';
        }
        GDCUMPLD.RSEQ = GDCSMPIN.VAL.RL_SEQ;
        GDCUMPLD.TX_AMT = GDCSMPIN.VAL.RL_AMT;
        GDCUMPLD.EXP_DATE = GDCSMPIN.VAL.EXP_DT;
        GDCUMPLD.CTA_NO = GDCSMPIN.VAL.CTA_NO;
        GDCUMPLD.REF_NO = GDCSMPIN.VAL.REF_NO;
        GDCUMPLD.BUSI_TYPE = GDCSMPIN.VAL.BSTYP;
        CEP.TRC(SCCGWA, "AAAAAA");
        CEP.TRC(SCCGWA, GDCSMPRL.KEY.RSEQ);
        S000_CALL_GDZUMPLD();
    }
    public void B090_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCOMPIN);
        GDCOMPIN.VAL.CI_NO = GDCSMPIN.VAL.CI_NO;
        GDCOMPIN.VAL.CI_NM = GDCSMPIN.VAL.CI_NM;
        GDCOMPIN.VAL.DD_AC1 = GDCSMPIN.VAL.DD_AC1;
        GDCOMPIN.VAL.DR_CARD1 = GDCSMPIN.VAL.DR_CARD1;
        GDCOMPIN.VAL.CI_NM1 = GDCSMPIN.VAL.CI_NM1;
        GDCOMPIN.VAL.PROD_CD = GDCSMPIN.VAL.PROD_CD;
        GDCOMPIN.VAL.CCY = GDCSMPIN.VAL.CCY;
        GDCOMPIN.VAL.CCY_TYP = GDCSMPIN.VAL.CCY_TYP;
        GDCOMPIN.VAL.AMT = GDCSMPIN.VAL.AMT;
        GDCOMPIN.VAL.STL_MTH = GDCSMPIN.VAL.STL_MTH;
        GDCOMPIN.VAL.REV_NO = GDCSMPIN.VAL.REV_NO;
        GDCOMPIN.VAL.DD_AC2 = GDCSMPIN.VAL.DD_AC2;
        GDCOMPIN.VAL.DR_CARD2 = GDCSMPIN.VAL.DR_CARD2;
        GDCOMPIN.VAL.CI_NM2 = GDCSMPIN.VAL.CI_NM2;
        GDCOMPIN.VAL.CHQ_NO = GDCSMPIN.VAL.CHQ_NO;
        GDCOMPIN.VAL.ISSUE_DT = GDCSMPIN.VAL.ISSUE_DT;
        GDCOMPIN.VAL.PAY_AC = GDCSMPIN.VAL.PAY_AC;
        GDCOMPIN.VAL.BRANCH = GDCSMPIN.VAL.BRANCH;
        GDCOMPIN.VAL.PAY_CI_NM = GDCSMPIN.VAL.PAY_CI_NM;
        GDCOMPIN.VAL.RL_FLG = GDCSMPIN.VAL.RL_FLG;
        if (GDCSMPIN.VAL.RL_FLG == '0') {
            GDCOMPIN.VAL.CTA_NO = GDCSMPIN.VAL.CTA_NO;
            GDCOMPIN.VAL.REF_NO = GDCSMPIN.VAL.REF_NO;
            GDCOMPIN.VAL.BSTYP = GDCSMPIN.VAL.BSTYP;
            GDCOMPIN.VAL.RL_AMT = GDCSMPIN.VAL.RL_AMT;
            GDCOMPIN.VAL.EXP_DT = GDCSMPIN.VAL.EXP_DT;
            GDCOMPIN.VAL.RL_SEQ = GDCUMPLD.RSEQ;
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = GDCOMPIN;
        SCCFMT.DATA_LEN = 1350;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_WRITE_HIS_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA.BA_CNT = (short) (GWA_SC_AREA.BA_CNT + 1);
        CEP.TRC(SCCGWA, GWA_SC_AREA.BA_CNT);
        IBS.init(SCCGWA, GDRHIS);
        GDCRHIS.FUNC = 'A';
        GDRHIS.AC = GDCSMPIN.VAL.DD_AC1;
        GDRHIS.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRHIS.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        GDRHIS.KEY.SEQ = GWA_SC_AREA.BA_CNT;
        GDRHIS.FUNC = '1';
        GDRHIS.TR_AMT = GDCSMPIN.VAL.AMT;
        GDRHIS.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRHIS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        GDRHIS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRHIS.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        GDRHIS.CHNL_CD = SCCGWA.COMM_AREA.REQ_SYSTEM;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            GDRHIS.CAN_FLG = 'R';
        } else {
            GDRHIS.CAN_FLG = 'N';
        }
        if (GDCSMPIN.VAL.PAY_AC.trim().length() > 0) {
            GDRHIS.TR_AC = GDCSMPIN.VAL.PAY_AC;
        } else {
            GDRHIS.TR_AC = GDCSMPIN.VAL.DD_AC2;
        }
        GDCRHIS.REC_PTR = GDRHIS;
        GDCRHIS.REC_LEN = 281;
        S000_CALL_GDZRHIS();
        CEP.TRC(SCCGWA, GDRHIS.KEY.SEQ);
    }
    public void B170_02_GET_RLT_BR_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = WS_OTHER_AC;
        S000_CALL_CIZQACRI();
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_OPN_BR);
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        IBS.init(SCCGWA, BPCPQORG);
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
            IBS.init(SCCGWA, AICPQMIB);
            AICPQMIB.INPUT_DATA.AC = WS_OTHER_AC;
            S000_CALL_AIZPQMIB();
            BPCPQORG.BR = AICPQMIB.INPUT_DATA.BR;
        } else {
            BPCPQORG.BR = CICQACRI.O_DATA.O_OPN_BR;
        }
        S000_CALL_BPZPQORG();
        CEP.TRC(SCCGWA, BPCPQORG.CHN_NM);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        if (CICQACRI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACRI.RC);
        }
    }
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB);
        if (AICPQMIB.RC.RC_CODE != 0 
            && AICPQMIB.RC.RC_CODE != 8917 
            && AICPQMIB.RC.RC_CODE != 8924) {
            CEP.ERR(SCCGWA, AICPQMIB.RC);
        }
    }
    public void S000_CALL_CIZSOEC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-OPEN-EXP-CI", CICSOEC);
        if (CICSOEC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSOEC.RC);
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_GDZRHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SRC-GDZRHIS", GDCRHIS);
        CEP.TRC(SCCGWA, GDCRHIS.RC.RC_CODE);
        if (GDCRHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, GDCRHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_UPDATE_GDTHIS() throws IOException,SQLException,Exception {
        GDTHIS_RD = new DBParm();
        GDTHIS_RD.TableName = "GDTHIS";
        IBS.REWRITE(SCCGWA, GDRHIS, GDTHIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_HIS_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_STARTBR_BY_DTJRN() throws IOException,SQLException,Exception {
        GDTHIS_BR.rp = new DBParm();
        GDTHIS_BR.rp.TableName = "GDTHIS";
        GDTHIS_BR.rp.where = "TR_DATE = :GDRHIS.KEY.TR_DATE "
            + "AND JRNNO = :GDRHIS.KEY.JRNNO "
            + "AND AC = :GDRHIS.AC";
        GDTHIS_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, GDRHIS, this, GDTHIS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_HIS_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READNEXT_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, GDRHIS, this, GDTHIS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_HIS_FLG = 'N';
        }
    }
    public void T000_ENDBR_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, GDTHIS_BR);
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "CUS_AC = :DDRCCY.CUS_AC "
            + "AND CCY = :DDRCCY.CCY "
            + "AND CCY_TYPE = :DDRCCY.CCY_TYPE";
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-GET-SEQ", BPCSGSEQ);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
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
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC);
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC);
    }
    public void S000_CALL_GDZUMPLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-U-PLDR-RL", GDCUMPLD);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_AIZPIAEV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-IA-WRT-EWA", AICPIAEV);
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
    }
    public void S000_CALL_DDZRMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-R-DDTMST", DDCRMST);
        if (DDCRMST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCRMST.RC);
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, DDCRMST.RETURN_INFO);
        CEP.TRC(SCCGWA, DDCRMST.RC);
    }
    public void S000_CALL_BPZUABOX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_ADD_CBOX, BPCUABOX);
    }
    public void S000_CALL_SCZTPCL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-LINK-EXTSERV", SCCTPCL);
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
