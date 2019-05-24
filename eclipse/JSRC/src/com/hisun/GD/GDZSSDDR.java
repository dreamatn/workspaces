package com.hisun.GD;

import com.hisun.DD.*;
import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.AI.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class GDZSSDDR {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm GDTHIS_RD;
    brParm GDTHIS_BR = new brParm();
    DBParm DDTMST_RD;
    DBParm DDTCCY_RD;
    String K_OUTPUT_FMT = "GD381";
    String WS_ERR_MSG = " ";
    double WS_VAVL_BAL = 0;
    GDZSSDDR_WS_FMT_OUTPUT WS_FMT_OUTPUT = new GDZSSDDR_WS_FMT_OUTPUT();
    double WS_AVA_RELAT_AMT = 0;
    double WS_CURR_BAL = 0;
    double WS_HOLD_AMT = 0;
    double WS_MARGIN_AMT = 0;
    char WS_CCY_TYPE = ' ';
    String WS_OTHER_AC = " ";
    char WS_PLDR_FLG = ' ';
    char WS_AC_FLG = ' ';
    char WS_HIS_FLG = ' ';
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
    DDCSOCAC DDCSOCAC = new DDCSOCAC();
    GDCOSDDR GDCOSDDR = new GDCOSDDR();
    GDRSTAC GDRSTAC = new GDRSTAC();
    GDCRSTAC GDCRSTAC = new GDCRSTAC();
    CICQACRI CICQACRI = new CICQACRI();
    AICPQMIB AICPQMIB = new AICPQMIB();
    BPCPQORG BPCPQORG = new BPCPQORG();
    CICSOEC CICSOEC = new CICSOEC();
    GDRPLDR GDRPLDR = new GDRPLDR();
    DDRCCY DDRCCY = new DDRCCY();
    DDRMST DDRMST = new DDRMST();
    GDRHIS GDRHIS = new GDRHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    GDCSSDDR GDCSSDDR;
    public void MP(SCCGWA SCCGWA, GDCSSDDR GDCSSDDR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.GDCSSDDR = GDCSSDDR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "GDZSSDDR return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B150_TRANS_DATA_PROC();
        B200_GET_AC_INFO_PROC();
        B300_CALU_AVA_AMT_PROC();
        B040_WITHDRAW_PROC();
        B050_DEPOSIT_PROC();
        B070_OUTPUT_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCSSDDR.PAY_MTH);
        if (GDCSSDDR.SPDR_AC.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (GDCSSDDR.AC_CCY.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CCY_NOT_MATCH;
            S000_ERR_MSG_PROC();
        }
        if (GDCSSDDR.SP_AMT == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AMT_MST_IPT;
            S000_ERR_MSG_PROC();
        }
        if (GDCSSDDR.STLT == ' ') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_STLT_M_INPUT;
            S000_ERR_MSG_PROC();
        } else {
            if (GDCSSDDR.STLT != '1' 
                && GDCSSDDR.STLT != '3') {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_STLT_INVALID;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B150_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSOCAC);
        DDCSOCAC.CI_NO = GDCSSDDR.CI_NO;
        DDCSOCAC.PROD_CODE = GDCSSDDR.PROD_CODE;
        DDCSOCAC.POST_AC = GDCSSDDR.CUT_AC;
        DDCSOCAC.AC_CNM = GDCSSDDR.AC_CNM;
        DDCSOCAC.AC_ENM = GDCSSDDR.AC_ENM;
        DDCSOCAC.AC_CCY = GDCSSDDR.AC_CCY;
        DDCSOCAC.CCY_TYPE = GDCSSDDR.CCY_TYPE;
        DDCSOCAC.AC_TYP = GDCSSDDR.AC_TYP;
        DDCSOCAC.EXP_DATE = GDCSSDDR.EXP_DATE;
        DDCSOCAC.FRG_CODE = GDCSSDDR.FRG_CODE;
        DDCSOCAC.FR_OP_NO = GDCSSDDR.FR_OP_NO;
        DDCSOCAC.FRG_IND = GDCSSDDR.FRG_IND;
        DDCSOCAC.PAY_MTH = GDCSSDDR.PAY_MTH;
        DDCSOCAC.FEE_METH = GDCSSDDR.FEE_METH;
        DDCSOCAC.CR_CR_FL = GDCSSDDR.CR_CR_FL;
        DDCSOCAC.CR_DR_FL = GDCSSDDR.CR_DR_FL;
        DDCSOCAC.CASH_FLG = GDCSSDDR.CASH_FLG;
        DDCSOCAC.OPEN_DP = GDCSSDDR.OPEN_DP;
        DDCSOCAC.CHCK_IND = GDCSSDDR.CHCK_IND;
        DDCSOCAC.OIC_NO = GDCSSDDR.OIC_NO;
        DDCSOCAC.OIC_CODE = GDCSSDDR.RES_CENT;
        DDCSOCAC.SUB_DP = GDCSSDDR.SUB_DP;
        DDCSOCAC.YW_TYP = GDCSSDDR.YW_TYP;
        S000_CALL_DDZSOCAC();
        GDCSSDDR.CRAC = DDCSOCAC.AC;
        CEP.TRC(SCCGWA, DDCSOCAC.AC);
        CEP.TRC(SCCGWA, GDCSSDDR.CRAC);
        CEP.TRC(SCCGWA, GDCSSDDR.SPDR_AC);
        CEP.TRC(SCCGWA, GDCSSDDR.AC_TYP);
        CEP.TRC(SCCGWA, GDCSSDDR.YW_TYP);
        CEP.TRC(SCCGWA, GDCSSDDR.PAY_MTH);
        CEP.TRC(SCCGWA, DDCSOCAC.PAY_MTH);
        R000_WRITE_GDTSTAC();
    }
    public void B200_GET_AC_INFO_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        IBS.init(SCCGWA, DDCRMST);
        DDRMST.KEY.CUS_AC = GDCSSDDR.SPDR_AC;
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
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_HOLD;
            S000_ERR_MSG_PROC();
        }
    }
    public void B300_CALU_AVA_AMT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = GDCSSDDR.SPDR_AC;
        DDRCCY.CCY = GDCSSDDR.AC_CCY;
        if (GDCSSDDR.CCY_TYPE != ' ') {
            DDRCCY.CCY_TYPE = GDCSSDDR.CCY_TYPE;
            WS_CCY_TYPE = GDCSSDDR.CCY_TYPE;
        } else {
            if (GDCSSDDR.AC_CCY.equalsIgnoreCase("156")) {
                DDRCCY.CCY_TYPE = '1';
                WS_CCY_TYPE = '1';
            } else {
                DDRCCY.CCY_TYPE = '2';
                WS_CCY_TYPE = '2';
            }
        }
        CEP.TRC(SCCGWA, "OOOOO");
        CEP.TRC(SCCGWA, WS_CCY_TYPE);
        T000_READ_DDTCCY();
        CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
        CEP.TRC(SCCGWA, DDRCCY.MARGIN_BAL);
        CEP.TRC(SCCGWA, DDRCCY.HOLD_BAL);
        WS_CURR_BAL = DDRCCY.CURR_BAL;
        WS_MARGIN_AMT = DDRCCY.MARGIN_BAL;
        WS_HOLD_AMT = DDRCCY.HOLD_BAL;
        WS_AVA_RELAT_AMT = WS_CURR_BAL - WS_HOLD_AMT - WS_MARGIN_AMT;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            CEP.TRC(SCCGWA, "NOT CANCE PROC");
            if (GDCSSDDR.SP_AMT > WS_AVA_RELAT_AMT) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_DRAMT_GR_AVLAMT;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B040_WITHDRAW_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUDRAC);
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.GD_WITHDR_FLG = 'Y';
        DDCUDRAC.AC = GDCSSDDR.SPDR_AC;
        DDCUDRAC.OTHER_AC = GDCSSDDR.CRAC;
        WS_OTHER_AC = DDCUDRAC.OTHER_AC;
        B170_02_GET_RLT_BR_INFO();
        DDCUDRAC.OTHER_AC_NM = CICQACRI.O_DATA.O_AC_CNM;
        DDCUDRAC.OTHER_BR = "" + CICQACRI.O_DATA.O_OPN_BR;
        JIBS_tmp_int = DDCUDRAC.OTHER_BR.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) DDCUDRAC.OTHER_BR = "0" + DDCUDRAC.OTHER_BR;
        DDCUDRAC.OTHER_BK_NM = BPCPQORG.CHN_NM;
        DDCUDRAC.CCY = GDCSSDDR.AC_CCY;
        if (GDCSSDDR.AC_CCY.equalsIgnoreCase("156")) {
            DDCUDRAC.CCY_TYPE = '1';
        } else {
            DDCUDRAC.CCY_TYPE = '2';
        }
        DDCUDRAC.TX_AMT = GDCSSDDR.SP_AMT;
        DDCUDRAC.REMARKS = GDCSSDDR.REMARK;
        DDCUDRAC.TX_MMO = "A019";
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            DDCUDRAC.VAL_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        } else {
            DDCUDRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        S000_CALL_DDZUDRAC();
    }
    public void B050_DEPOSIT_PROC() throws IOException,SQLException,Exception {
        if (GDCSSDDR.STLT == '1') {
            B050_01_DEP_AI_PROC();
        } else {
            B050_02_DEP_DD_PROC();
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            IBS.init(SCCGWA, GDRHIS);
            GDRHIS.KEY.TR_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            GDRHIS.KEY.JRNNO = GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
            GDRHIS.AC = GDCSSDDR.SPDR_AC;
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
        AICUUPIA.DATA.AC_NO = GDCSSDDR.CRAC;
        AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.AMT = GDCSSDDR.SP_AMT;
        AICUUPIA.DATA.CCY = GDCSSDDR.AC_CCY;
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
        DDRMST.KEY.CUS_AC = GDCSSDDR.CRAC;
        T000_READ_DDTMST();
        CEP.TRC(SCCGWA, DDRMST.AC_STS);
        CEP.TRC(SCCGWA, DDRMST.AC_TYPE);
        if (DDRMST.AC_TYPE != 'N') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_TYPE_INVALID;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, DDCUCRAC);
        DDCUCRAC.TX_TYPE = 'T';
        DDCUCRAC.AC = GDCSSDDR.CRAC;
        DDCUCRAC.CCY = GDCSSDDR.AC_CCY;
        DDCUCRAC.CCY_TYPE = WS_CCY_TYPE;
        DDCUCRAC.TX_AMT = GDCSSDDR.SP_AMT;
        DDCUCRAC.OTHER_AC = GDCSSDDR.SPDR_AC;
        WS_OTHER_AC = DDCUCRAC.OTHER_AC;
        B170_02_GET_RLT_BR_INFO();
        DDCUCRAC.OTHER_AC_NM = CICQACRI.O_DATA.O_AC_CNM;
        DDCUCRAC.OTHER_BR = "" + CICQACRI.O_DATA.O_OPN_BR;
        JIBS_tmp_int = DDCUCRAC.OTHER_BR.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) DDCUCRAC.OTHER_BR = "0" + DDCUCRAC.OTHER_BR;
        DDCUCRAC.OTHER_BK_NM = BPCPQORG.CHN_NM;
        DDCUCRAC.REMARKS = GDCSSDDR.REMARK;
        DDCUCRAC.TX_MMO = "A001";
        DDCUCRAC.GD_WITHDR_FLG = 'Y';
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            DDCUCRAC.VAL_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        } else {
            DDCUCRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        S000_CALL_DDZUCRAC();
    }
    public void B060_WRITE_HIS_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GWA_SC_AREA.BA_CNT);
        GWA_SC_AREA.BA_CNT = (short) (GWA_SC_AREA.BA_CNT + 1);
        CEP.TRC(SCCGWA, GWA_SC_AREA.BA_CNT);
        IBS.init(SCCGWA, GDRHIS);
        GDRHIS.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRHIS.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        GDRHIS.KEY.SEQ = GWA_SC_AREA.BA_CNT;
        GDRHIS.AC = GDCSSDDR.SPDR_AC;
        GDRHIS.FUNC = '2';
        GDRHIS.CHNL_CD = SCCGWA.COMM_AREA.REQ_SYSTEM;
        GDRHIS.TR_AMT = GDCSSDDR.SP_AMT;
        GDRHIS.TR_AC = GDCSSDDR.CRAC;
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
        CICACCU.DATA.AGR_NO = GDCSSDDR.SPDR_AC;
        S000_CALL_CIZACCU();
        IBS.init(SCCGWA, WS_FMT_OUTPUT);
        WS_FMT_OUTPUT.WS_CI_NO = CICACCU.DATA.CI_NO;
        WS_FMT_OUTPUT.WS_CI_NM = CICACCU.DATA.CI_CNM;
        WS_FMT_OUTPUT.WS_DR_AC = GDCSSDDR.CRAC;
        WS_FMT_OUTPUT.WS_AC_NM = CICACCU.DATA.AC_CNM;
        WS_FMT_OUTPUT.WS_CCY = GDCSSDDR.AC_CCY;
        WS_FMT_OUTPUT.WS_TXN_TYPE = WS_CCY_TYPE;
        WS_FMT_OUTPUT.WS_TXN_AMT = GDCSSDDR.SP_AMT;
        WS_FMT_OUTPUT.WS_CR_AC = GDCSSDDR.SPDR_AC;
        WS_FMT_OUTPUT.WS_REMARKS = GDCSSDDR.REMARK;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_FMT_OUTPUT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], GDCOSDDR);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = GDCOSDDR;
        SCCFMT.DATA_LEN = 724;
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
    public void R000_WRITE_GDTSTAC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCSSDDR.RES_AC);
        CEP.TRC(SCCGWA, GDCSSDDR.CUT_AC);
        IBS.init(SCCGWA, GDCRSTAC);
        IBS.init(SCCGWA, GDRSTAC);
        IBS.init(SCCGWA, CICQACRI);
        IBS.init(SCCGWA, CICACCU);
        GDCRSTAC.FUNC = 'A';
        GDRSTAC.KEY.AC = DDCSOCAC.AC;
        CICACCU.DATA.AGR_NO = GDCSSDDR.RES_AC;
        S000_CALL_CIZACCU();
        CEP.TRC(SCCGWA, GDCSSDDR.CI_NO);
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
        if (!GDCSSDDR.CI_NO.equalsIgnoreCase(CICACCU.DATA.CI_NO)) {
            IBS.init(SCCGWA, CICSOEC);
            CICSOEC.DATA.CI_NO = CICACCU.DATA.CI_NO;
            CICSOEC.DATA.READ_ONLY_FLG = 'Y';
            S000_CALL_CIZSOEC();
            CEP.TRC(SCCGWA, CICSOEC.DATA.SPECIAL_CI_NO);
            CEP.TRC(SCCGWA, GDCSSDDR.CI_NO);
            if (!CICSOEC.DATA.SPECIAL_CI_NO.equalsIgnoreCase(GDCSSDDR.CI_NO)) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CI_NO_NOT_SAME;
                S000_ERR_MSG_PROC();
            }
        }
        GDRSTAC.ST_AC = CICACCU.DATA.AGR_NO;
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = GDCSSDDR.CUT_AC;
        S000_CALL_CIZACCU();
        CEP.TRC(SCCGWA, GDCSSDDR.CI_NO);
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
        if (!GDCSSDDR.CI_NO.equalsIgnoreCase(CICACCU.DATA.CI_NO)) {
            IBS.init(SCCGWA, CICSOEC);
            CICSOEC.DATA.CI_NO = CICACCU.DATA.CI_NO;
            CICSOEC.DATA.READ_ONLY_FLG = 'Y';
            S000_CALL_CIZSOEC();
            CEP.TRC(SCCGWA, CICSOEC.DATA.SPECIAL_CI_NO);
            CEP.TRC(SCCGWA, GDCSSDDR.CI_NO);
            if (!CICSOEC.DATA.SPECIAL_CI_NO.equalsIgnoreCase(GDCSSDDR.CI_NO)) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CI_NO_NOT_SAME;
                S000_ERR_MSG_PROC();
            }
        }
        GDRSTAC.INT_AC = CICACCU.DATA.AGR_NO;
        GDRSTAC.RELAT_STS = 'N';
        GDRSTAC.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRSTAC.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        GDCRSTAC.REC_PTR = GDRSTAC;
        GDCRSTAC.REC_LEN = 401;
        S000_CALL_GDZRSTAC();
    }
    public void S000_CALL_GDZRSTAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SRC-GDZRSTAC", GDCRSTAC);
        CEP.TRC(SCCGWA, GDCRSTAC.RC);
        if (GDCRSTAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, GDCRSTAC.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZSOCAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-OPEN-ENT-AC", DDCSOCAC);
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
    public void S000_CALL_CIZSOEC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-OPEN-EXP-CI", CICSOEC);
        if (CICSOEC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSOEC.RC);
        }
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
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        CEP.TRC(SCCGWA, CICQACRI.RC);
        if (CICQACRI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
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
        DDTCCY_RD.where = "CUS_AC = :DDRCCY.CUS_AC "
            + "AND CCY = :DDRCCY.CCY "
            + "AND CCY_TYPE = :DDRCCY.CCY_TYPE";
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND;
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
