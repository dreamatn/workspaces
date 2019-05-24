package com.hisun.GD;

import com.hisun.DD.*;
import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.AI.*;
import com.hisun.DC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class GDZSDDDR {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm GDTHIS_RD;
    brParm GDTHIS_BR = new brParm();
    DBParm DDTMST_RD;
    DBParm DDTCCY_RD;
    DBParm GDTSTAC_RD;
    String K_OUTPUT_FMT = "GD120";
    String WS_ERR_MSG = " ";
    double WS_VAVL_BAL = 0;
    GDZSDDDR_WS_FMT_OUTPUT WS_FMT_OUTPUT = new GDZSDDDR_WS_FMT_OUTPUT();
    double WS_AVA_RELAT_AMT = 0;
    double WS_CURR_BAL = 0;
    double WS_HOLD_AMT = 0;
    double WS_MARGIN_AMT = 0;
    double WS_CCAL_TOT_BAL = 0;
    double WS_MARGIN_INT = 0;
    char WS_CCY_TYPE = ' ';
    String WS_DRYW_TYP = " ";
    String WS_OTHER_AC = " ";
    char WS_PLDR_FLG = ' ';
    char WS_AC_FLG = ' ';
    char WS_HIS_FLG = ' ';
    String WS_CI_NO1 = " ";
    String WS_CI_NO2 = " ";
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    GDCRHIS GDCRHIS = new GDCRHIS();
    DDCRMST DDCRMST = new DDCRMST();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    CICACCU CICACCU = new CICACCU();
    AICUUPIA AICUUPIA = new AICUUPIA();
    GDCRPLDR GDCRPLDR = new GDCRPLDR();
    DCCPACTY DCCPACTY = new DCCPACTY();
    CICQACAC CICQACAC = new CICQACAC();
    CICQACRI CICQACRI = new CICQACRI();
    GDCSCLDD GDCSCLDD = new GDCSCLDD();
    GDCODDDR GDCODDDR = new GDCODDDR();
    DCCUTCGD DCCUTCGD = new DCCUTCGD();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDVMRAT DDVMRAT = new DDVMRAT();
    AICPQMIB AICPQMIB = new AICPQMIB();
    BPCPQORG BPCPQORG = new BPCPQORG();
    CICSOEC CICSOEC = new CICSOEC();
    GDRPLDR GDRPLDR = new GDRPLDR();
    DDRCCY DDRCCY = new DDRCCY();
    DDRMST DDRMST = new DDRMST();
    GDRHIS GDRHIS = new GDRHIS();
    GDRSTAC GDRSTAC = new GDRSTAC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    GDCSDDDR GDCSDDDR;
    public void MP(SCCGWA SCCGWA, GDCSDDDR GDCSDDDR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.GDCSDDDR = GDCSDDDR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "GDZSDDDR return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_GET_AC_INFO_PROC();
        B300_CALU_AVA_AMT_PROC();
        B040_WITHDRAW_PROC();
        if (WS_MARGIN_INT != 0) {
            B055_01_INT_AC_PROC();
        }
        B050_DEPOSIT_PROC();
        B055_02_CANCLE_AC_PROC();
        B070_OUTPUT_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (GDCSDDDR.DRAC.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (GDCSDDDR.CCY.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CCY_NOT_MATCH;
            S000_ERR_MSG_PROC();
        }
        if (GDCSDDDR.AMT == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AMT_MST_IPT;
            S000_ERR_MSG_PROC();
        }
        if (GDCSDDDR.STLT == ' ') {
        } else {
            if (GDCSDDDR.STLT != '1' 
                && GDCSDDDR.STLT != '3') {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_STLT_INVALID;
                S000_ERR_MSG_PROC();
            }
        }
        if (GDCSDDDR.CRAC.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_GET_AC_INFO_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        IBS.init(SCCGWA, DDCRMST);
        DDRMST.KEY.CUS_AC = GDCSDDDR.DRAC;
        DDCRMST.FUNC = 'I';
        DDCRMST.REC_PTR = DDRMST;
        DDCRMST.REC_LEN = 425;
        S000_CALL_DDZRMST();
        IBS.init(SCCGWA, DDRCCY);
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.AGR_NO = GDCSDDDR.DRAC;
        CICQACAC.DATA.CCY_ACAC = GDCSDDDR.CCY;
        if (GDCSDDDR.CCY_TYP != ' ') {
            DDRCCY.CCY_TYPE = GDCSDDDR.CCY_TYP;
            WS_CCY_TYPE = GDCSDDDR.CCY_TYP;
            CICQACAC.DATA.CR_FLG = GDCSDDDR.CCY_TYP;
        } else {
            if (GDCSDDDR.CCY.equalsIgnoreCase("156")) {
                DDRCCY.CCY_TYPE = '1';
                WS_CCY_TYPE = '1';
                CICQACAC.DATA.CR_FLG = '1';
            } else {
                DDRCCY.CCY_TYPE = '2';
                WS_CCY_TYPE = '2';
                CICQACAC.DATA.CR_FLG = '2';
            }
        }
        CICQACAC.FUNC = 'R';
        S000_CALL_CIZQACAC();
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        CEP.TRC(SCCGWA, "OOOOO");
        CEP.TRC(SCCGWA, WS_CCY_TYPE);
        CEP.TRC(SCCGWA, DDRCCY.KEY.AC);
        T000_READ_DDTCCY();
        if (DDCRMST.RETURN_INFO == 'N') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FR_MST_NOTFND;
            S000_ERR_MSG_PROC();
        }
        if (DDRMST.AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FR_AC_CLOSE;
            S000_ERR_MSG_PROC();
        }
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
            || DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_HOLD;
            S000_ERR_MSG_PROC();
        }
        if (DDRMST.AC_TYPE != 'N') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_NOT_GD_AC;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = GDCSDDDR.DRAC;
        S000_CALL_CIZQACAC();
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO);
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = GDCSDDDR.CRAC;
        S000_CALL_CIZQACRI();
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_CI_NO);
        if (!CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO.equalsIgnoreCase(CICQACRI.O_DATA.O_CI_NO) 
            && GDCSDDDR.STLT == '3' 
            && !WS_DRYW_TYP.equalsIgnoreCase("12")) {
            IBS.init(SCCGWA, CICSOEC);
            CICSOEC.DATA.CI_NO = CICQACRI.O_DATA.O_CI_NO;
            CICSOEC.DATA.READ_ONLY_FLG = 'Y';
            S000_CALL_CIZSOEC();
            if (!CICSOEC.DATA.SPECIAL_CI_NO.equalsIgnoreCase(CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO)) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CI_NO_NOT_SAME;
                S000_ERR_MSG_PROC();
            }
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        CEP.TRC(SCCGWA, DDRMST.YW_TYP);
        WS_DRYW_TYP = DDRMST.YW_TYP;
    }
    public void B300_CALU_AVA_AMT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
        CEP.TRC(SCCGWA, DDRCCY.MARGIN_BAL);
        CEP.TRC(SCCGWA, DDRCCY.HOLD_BAL);
        CEP.TRC(SCCGWA, DDRCCY.CCAL_TOT_BAL);
        WS_CURR_BAL = DDRCCY.CURR_BAL;
        WS_MARGIN_AMT = DDRCCY.MARGIN_BAL;
        WS_HOLD_AMT = DDRCCY.HOLD_BAL;
        WS_CCAL_TOT_BAL = DDRCCY.CCAL_TOT_BAL;
        WS_AVA_RELAT_AMT = WS_CURR_BAL - WS_HOLD_AMT - WS_MARGIN_AMT + WS_CCAL_TOT_BAL;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            CEP.TRC(SCCGWA, "NOT CANCE PROC");
            if (GDCSDDDR.AMT > WS_AVA_RELAT_AMT) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_DRAMT_GR_AVLAMT;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B040_WITHDRAW_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUDRAC);
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.GD_WITHDR_FLG = 'Y';
        DDCUDRAC.AC = GDCSDDDR.DRAC;
        DDCUDRAC.OTHER_AC = GDCSDDDR.CRAC;
        WS_OTHER_AC = DDCUDRAC.OTHER_AC;
        B170_02_GET_RLT_BR_INFO();
        DDCUDRAC.OTHER_AC_NM = CICQACRI.O_DATA.O_AC_CNM;
        DDCUDRAC.OTHER_BR = "" + CICQACRI.O_DATA.O_OPN_BR;
        JIBS_tmp_int = DDCUDRAC.OTHER_BR.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) DDCUDRAC.OTHER_BR = "0" + DDCUDRAC.OTHER_BR;
        DDCUDRAC.OTHER_BK_NM = BPCPQORG.CHN_NM;
        DDCUDRAC.CCY = GDCSDDDR.CCY;
        if (GDCSDDDR.CCY.equalsIgnoreCase("156")) {
            DDCUDRAC.CCY_TYPE = '1';
        } else {
            DDCUDRAC.CCY_TYPE = '2';
        }
        DDCUDRAC.TX_AMT = GDCSDDDR.AMT;
        DDCUDRAC.REMARKS = GDCSDDDR.REMARK;
        DDCUDRAC.TX_MMO = "A019";
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            DDCUDRAC.VAL_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        } else {
            DDCUDRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        S000_CALL_DDZUDRAC();
        CEP.TRC(SCCGWA, GDCSDDDR.AMT);
        WS_MARGIN_INT = DDCUDRAC.MARGIN_INT;
        CEP.TRC(SCCGWA, DDCUDRAC.TX_AMT);
        CEP.TRC(SCCGWA, WS_MARGIN_INT);
        CEP.TRC(SCCGWA, DDCUDRAC.MARGIN_INT);
    }
    public void B050_DEPOSIT_PROC() throws IOException,SQLException,Exception {
        if (GDCSDDDR.STLT != ' ') {
            if (GDCSDDDR.STLT == '1') {
                B050_01_DEP_AI_PROC();
            } else {
                CICACCU.DATA.AGR_NO = GDCSDDDR.DRAC;
                S000_CALL_CIZACCU();
                WS_CI_NO1 = CICACCU.DATA.CI_NO;
                if (GDCSDDDR.CRAC.trim().length() > 0) {
                    CICACCU.DATA.AGR_NO = GDCSDDDR.CRAC;
                    S000_CALL_CIZACCU();
                    WS_CI_NO2 = CICACCU.DATA.CI_NO;
                    if (!WS_CI_NO2.equalsIgnoreCase(WS_CI_NO1)) {
                        IBS.init(SCCGWA, CICSOEC);
                        CICSOEC.DATA.CI_NO = WS_CI_NO2;
                        CICSOEC.DATA.READ_ONLY_FLG = 'Y';
                        S000_CALL_CIZSOEC();
                        CEP.TRC(SCCGWA, CICSOEC.DATA.CI_NO);
                        CEP.TRC(SCCGWA, CICSOEC.DATA.SPECIAL_CI_NO);
                        CEP.TRC(SCCGWA, WS_CI_NO1);
                        if (!CICSOEC.DATA.SPECIAL_CI_NO.equalsIgnoreCase(WS_CI_NO1)) {
                            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CI_NO_NOT_SAME;
                            S000_ERR_MSG_PROC();
                        }
                    }
                }
                B050_02_DEP_DD_PROC();
            }
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            IBS.init(SCCGWA, GDRHIS);
            GDRHIS.KEY.TR_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            GDRHIS.KEY.JRNNO = GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
            GDRHIS.AC = GDCSDDDR.DRAC;
            T000_STARTBR_BY_DTJRN();
            T000_READNEXT_HIS_PROC();
            while (WS_HIS_FLG != 'N') {
                if (GDRHIS.CAN_FLG == 'N') {
                    GDRHIS.CAN_FLG = 'C';
                    T000_UPDATE_GDTHIS();
                    B060_WRITE_HIS_PROC();
                }
                T000_READNEXT_HIS_PROC();
            }
            T000_ENDBR_HIS_PROC();
        } else {
            B060_WRITE_HIS_PROC();
        }
    }
    public void B050_01_DEP_AI_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = GDCSDDDR.CRAC;
        AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.AMT = GDCSDDDR.AMT;
        AICUUPIA.DATA.CCY = GDCSDDDR.CCY;
        AICUUPIA.DATA.PAY_MAN = GDCSDDDR.AC_NM2;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            AICUUPIA.DATA.VALUE_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        } else {
            AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        AICUUPIA.DATA.EVENT_CODE = "CR";
        S000_CALL_AIZUUPIA();
    }
    public void B050_02_DEP_DD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.DATA.AGR_NO = GDCSDDDR.CRAC;
        CICQACRI.FUNC = 'A';
        S000_CALL_CIZQACRI();
        DDRMST.KEY.CUS_AC = CICQACRI.O_DATA.O_AGR_NO;
        T000_READ_DDTMST();
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.STD_AC);
        CEP.TRC(SCCGWA, DDRMST.AC_STS);
        CEP.TRC(SCCGWA, DDRMST.AC_TYPE);
        CEP.TRC(SCCGWA, DDRMST.YW_TYP);
        CEP.TRC(SCCGWA, GDCSDDDR.CRAC);
        CEP.TRC(SCCGWA, GDCSDDDR.CCY);
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = GDCSDDDR.CRAC;
        DDRCCY.CCY = GDCSDDDR.CCY;
        if (GDCSDDDR.CCY.equalsIgnoreCase("156")) {
            DDRCCY.CCY_TYPE = '1';
        } else {
            DDRCCY.CCY_TYPE = '2';
        }
        T000_READ_UPDDATE_DDTCCY();
        CEP.TRC(SCCGWA, DDRCCY.PROD_CODE);
        IBS.init(SCCGWA, DDCIQPRD);
        IBS.init(SCCGWA, DDVMPRD);
        IBS.init(SCCGWA, DDVMRAT);
        DDCIQPRD.INPUT_DATA.PROD_CODE = DDRCCY.PROD_CODE;
        CEP.TRC(SCCGWA, DDRCCY.PROD_CODE);
        DDCIQPRD.INPUT_DATA.CCY = GDCSDDDR.CCY;
        CEP.TRC(SCCGWA, DDCIQPRD.INPUT_DATA.PROD_CODE);
        CEP.TRC(SCCGWA, GDCSDDDR.CCY);
        DDCIQPRD.DDVMPRD_PTR = DDVMPRD;
        DDCIQPRD.DDVMRAT_PTR = DDVMRAT;
        S000_CALL_DDZIQPRD();
        CEP.TRC(SCCGWA, DDVMPRD.VAL.TD_FLG);
        if (DDRMST.AC_TYPE == 'N' 
            && !WS_DRYW_TYP.equalsIgnoreCase("06")) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_TYPE_INVALID;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, DDCUCRAC);
        DDCUCRAC.TX_TYPE = 'T';
        if (DDVMPRD.VAL.TD_FLG == '0') {
            DDCUCRAC.SUPPLY_FLG = 'N';
        }
        DDCUCRAC.AC = GDCSDDDR.CRAC;
        DDCUCRAC.CCY = GDCSDDDR.CCY;
        DDCUCRAC.CCY_TYPE = WS_CCY_TYPE;
        DDCUCRAC.TX_AMT = GDCSDDDR.AMT;
        DDCUCRAC.OTHER_AC = GDCSDDDR.DRAC;
        WS_OTHER_AC = DDCUCRAC.OTHER_AC;
        B170_02_GET_RLT_BR_INFO();
        DDCUCRAC.OTHER_AC_NM = CICQACRI.O_DATA.O_AC_CNM;
        DDCUCRAC.OTHER_BR = "" + CICQACRI.O_DATA.O_OPN_BR;
        JIBS_tmp_int = DDCUCRAC.OTHER_BR.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) DDCUCRAC.OTHER_BR = "0" + DDCUCRAC.OTHER_BR;
        DDCUCRAC.OTHER_BK_NM = BPCPQORG.CHN_NM;
        DDCUCRAC.REMARKS = GDCSDDDR.REMARK;
        DDCUCRAC.TX_MMO = "A001";
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            DDCUCRAC.VAL_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        } else {
            DDCUCRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        S000_CALL_DDZUCRAC();
        CEP.TRC(SCCGWA, DDCUDRAC.MARGIN_INT);
    }
    public void B055_01_INT_AC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRSTAC);
        if (GDCSDDDR.INT_AC.trim().length() == 0) {
            GDRSTAC.KEY.AC = GDCSDDDR.DRAC;
            T000_READ_GDTSTAC();
            CEP.TRC(SCCGWA, GDRSTAC.ST_AC);
            CEP.TRC(SCCGWA, GDRSTAC.INT_AC);
            if (GDRSTAC.INT_AC.trim().length() > 0) {
                GDCSDDDR.INT_AC = GDRSTAC.INT_AC;
            } else {
                GDCSDDDR.INT_AC = GDRSTAC.ST_AC;
            }
        }
        CEP.TRC(SCCGWA, GDCSDDDR.INT_AC);
        if (GDCSDDDR.INT_AC.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_INTAC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, DDRMST);
        IBS.init(SCCGWA, DDCRMST);
        DDRMST.KEY.CUS_AC = GDCSDDDR.INT_AC;
        DDCRMST.FUNC = 'I';
        DDCRMST.REC_PTR = DDRMST;
        DDCRMST.REC_LEN = 425;
        S000_CALL_DDZRMST();
        if (DDCRMST.RETURN_INFO == 'N') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FR_MST_NOTFND;
            S000_ERR_MSG_PROC();
        }
        if (DDRMST.AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FR_AC_CLOSE;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = GDCSDDDR.INT_AC;
        S000_CALL_CIZQACRI();
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        if (!CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.INPUT_NOT_DD_AC;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, DDCIQPRD);
        DDCIQPRD.INPUT_DATA.PROD_CODE = DDRCCY.PROD_CODE;
        CEP.TRC(SCCGWA, DDRCCY.PROD_CODE);
        DDCIQPRD.INPUT_DATA.CCY = GDCSDDDR.CCY;
        DDCIQPRD.DDVMPRD_PTR = DDVMPRD;
        DDCIQPRD.DDVMRAT_PTR = DDVMRAT;
        S000_CALL_DDZIQPRD();
        CEP.TRC(SCCGWA, DDRMST.PROD_CODE);
        CEP.TRC(SCCGWA, DDCIQPRD.OUTPUT_DATA.PRDT_MODEL);
        if (DDVMPRD.VAL.TD_FLG == '0' 
            && WS_MARGIN_INT != 0) {
            IBS.init(SCCGWA, DDCUCRAC);
            DDCUCRAC.TX_TYPE = 'T';
            DDCUCRAC.AC = GDCSDDDR.INT_AC;
            DDCUCRAC.CCY = GDCSDDDR.CCY;
            if (GDCSDDDR.CCY.equalsIgnoreCase("156")) {
                DDCUCRAC.CCY_TYPE = '1';
            } else {
                DDCUCRAC.CCY_TYPE = GDCSDDDR.CCY_TYP;
            }
            DDCUCRAC.CCY_TYPE = WS_CCY_TYPE;
            DDCUCRAC.TX_AMT = WS_MARGIN_INT;
            DDCUCRAC.OTHER_AC = GDCSDDDR.DRAC;
            DDCUCRAC.REMARKS = GDCSDDDR.REMARK;
            DDCUCRAC.TX_MMO = "S101";
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                DDCUCRAC.VAL_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            } else {
                DDCUCRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            }
            S000_CALL_DDZUCRAC();
        }
    }
    public void B055_02_CANCLE_AC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCSCLDD);
        if (GDCSDDDR.CANL_FLG == '1') {
            GDCSCLDD.DRAC = GDCSDDDR.DRAC;
            GDCSCLDD.CCY = GDCSDDDR.CCY;
            if (GDCSDDDR.CCY.equalsIgnoreCase("156")) {
                GDCSCLDD.CCY_TYP = '1';
            } else {
                GDCSCLDD.CCY_TYP = '2';
            }
            GDCSCLDD.STLT = GDCSDDDR.STLT;
            GDCSCLDD.CRAC = GDCSDDDR.RMN_AC;
            if (GDCSDDDR.MMO.trim().length() == 0) {
                GDCSCLDD.MMO = "A019";
            } else {
                GDCSCLDD.MMO = GDCSDDDR.MMO;
            }
            S000_CALL_GDZSCLDD();
        }
    }
    public void B060_WRITE_HIS_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GWA_SC_AREA.BA_CNT);
        GWA_SC_AREA.BA_CNT = (short) (GWA_SC_AREA.BA_CNT + 1);
        CEP.TRC(SCCGWA, GWA_SC_AREA.BA_CNT);
        IBS.init(SCCGWA, GDRHIS);
        GDRHIS.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRHIS.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        GDRHIS.KEY.SEQ = GWA_SC_AREA.BA_CNT;
        GDRHIS.AC = GDCSDDDR.DRAC;
        GDRHIS.FUNC = '2';
        GDRHIS.CHNL_CD = SCCGWA.COMM_AREA.REQ_SYSTEM;
        GDRHIS.TR_AMT = GDCSDDDR.AMT;
        GDRHIS.TR_AC = GDCSDDDR.CRAC;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            GDRHIS.CAN_FLG = 'R';
        } else {
            GDRHIS.CAN_FLG = 'N';
        }
        GDRHIS.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRHIS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        GDRHIS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRHIS.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        GDCRHIS.FUNC = 'A';
        GDCRHIS.REC_PTR = GDRHIS;
        GDCRHIS.REC_LEN = 281;
        S000_CALL_GDZRHIS();
    }
    public void B070_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = GDCSDDDR.DRAC;
        S000_CALL_CIZACCU();
        IBS.init(SCCGWA, WS_FMT_OUTPUT);
        WS_FMT_OUTPUT.WS_CI_NO = CICACCU.DATA.CI_NO;
        WS_FMT_OUTPUT.WS_CI_NM = CICACCU.DATA.CI_CNM;
        WS_FMT_OUTPUT.WS_DR_AC = GDCSDDDR.DRAC;
        WS_FMT_OUTPUT.WS_AC_NM = CICACCU.DATA.AC_CNM;
        WS_FMT_OUTPUT.WS_CCY = GDCSDDDR.CCY;
        WS_FMT_OUTPUT.WS_TXN_TYPE = WS_CCY_TYPE;
        WS_FMT_OUTPUT.WS_TXN_AMT = GDCSDDDR.AMT;
        WS_FMT_OUTPUT.WS_CR_AC = GDCSDDDR.CRAC;
        WS_FMT_OUTPUT.WS_CANL_FLG = GDCSDDDR.CANL_FLG;
        WS_FMT_OUTPUT.WS_INT_AC = GDCSDDDR.INT_AC;
        WS_FMT_OUTPUT.WS_RMN_AC = GDCSDDDR.RMN_AC;
        WS_FMT_OUTPUT.WS_REMARKS = GDCSDDDR.REMARK;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_FMT_OUTPUT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], GDCODDDR);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = GDCODDDR;
        SCCFMT.DATA_LEN = 789;
        IBS.FMT(SCCGWA, SCCFMT);
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
    public void S000_CALL_CIZSOEC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-OPEN-EXP-CI", CICSOEC);
        if (CICSOEC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSOEC.RC);
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
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC);
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
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_DDZRMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-R-DDTMST", DDCRMST);
        CEP.TRC(SCCGWA, DDCRMST.RC);
        if (DDCRMST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCRMST.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_GDZRHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SRC-GDZRHIS", GDCRHIS);
        if (GDCRHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, GDCRHIS.RC);
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
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        if (CICQACRI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_GDZSCLDD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SVR-GDZSCLDD", GDCSCLDD);
    }
    public void S000_CALL_DCZUTCGD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-TDCGDD-PROC", DCCUTCGD);
        if (DCCUTCGD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUTCGD.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZIQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-DDPRD", DDCIQPRD);
        if (DDCIQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQPRD.RC);
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
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_UPDDATE_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "CUS_AC = :DDRCCY.CUS_AC "
            + "AND CCY = :DDRCCY.CCY "
            + "AND CCY_TYPE = :DDRCCY.CCY_TYPE";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_GDTSTAC() throws IOException,SQLException,Exception {
        GDTSTAC_RD = new DBParm();
        GDTSTAC_RD.TableName = "GDTSTAC";
        IBS.READ(SCCGWA, GDRSTAC, GDTSTAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_STAC_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
