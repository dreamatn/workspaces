package com.hisun.GD;

import com.hisun.DD.*;
import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.AI.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class GDZSDTRE {
    int JIBS_tmp_int;
    DBParm GDTPLDR_RD;
    brParm GDTTRAN_BR = new brParm();
    DBParm GDTTRAN_RD;
    DBParm DDTMST_RD;
    DBParm DDTCCY_RD;
    String K_OUTPUT_FMT = "GD141";
    String WS_ERR_MSG = " ";
    double WS_VAVL_BAL = 0;
    double WS_AVA_RELAT_AMT = 0;
    double WS_CURR_BAL = 0;
    double WS_HOLD_AMT = 0;
    double WS_MARGIN_AMT = 0;
    double WS_LACK_AMT = 0;
    char WS_CCY_TYPE = ' ';
    String WS_CR_AC = " ";
    double WS_CRDK_AMT = 0;
    double WS_ADV_AMT = 0;
    String WS_OTHER_AC = " ";
    char WS_PLDR_FLG = ' ';
    char WS_AC_FLG = ' ';
    char WS_HIS_FLG = ' ';
    char WS_TRAN_FLG = ' ';
    char WS_EXIT_FLG = ' ';
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    GDCRHIS GDCRHIS = new GDCRHIS();
    DDCRMST DDCRMST = new DDCRMST();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    CICACCU CICACCU = new CICACCU();
    CICCUST CICCUST = new CICCUST();
    AICUUPIA AICUUPIA = new AICUUPIA();
    GDCRPLDR GDCRPLDR = new GDCRPLDR();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    CICQACAC CICQACAC = new CICQACAC();
    CICQACRI CICQACRI = new CICQACRI();
    GDCODTRE GDCODTRE = new GDCODTRE();
    AICPQMIB AICPQMIB = new AICPQMIB();
    BPCPQORG BPCPQORG = new BPCPQORG();
    CICSOEC CICSOEC = new CICSOEC();
    double WS_ALL_ADV_AMT = 0;
    GDRPLDR GDRPLDR = new GDRPLDR();
    GDRTRAN GDRTRAN = new GDRTRAN();
    DDRCCY DDRCCY = new DDRCCY();
    DDRMST DDRMST = new DDRMST();
    GDRHIS GDRHIS = new GDRHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    GDCSDTRE GDCSDTRE;
    public void MP(SCCGWA SCCGWA, GDCSDTRE GDCSDTRE) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.GDCSDTRE = GDCSDTRE;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "GDZSDTRE return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (GDCSDTRE.STL_MTH == '3') {
            B020_GET_AC_INFO_PROC();
        }
        B030_CALU_AVA_AMT_PROC();
        if (GDCSDTRE.STL_MTH == '3') {
            B040_WITHDRAW_PROC();
        }
        if (GDCSDTRE.STL_MTH == '1') {
            B041_CALL_AI_DR_UNT();
        }
        B050_DEPOSIT_PROC();
        B070_OUTPUT_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (GDCSDTRE.DRAC.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (GDCSDTRE.CCY.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CCY_NOT_MATCH;
            S000_ERR_MSG_PROC();
        }
        if (GDCSDTRE.AMT == 0 
            && GDCSDTRE.REF_NO.trim().length() > 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AMT_MST_IPT;
            S000_ERR_MSG_PROC();
        }
        if (GDCSDTRE.STLT == ' ') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_STLT_M_INPUT;
            S000_ERR_MSG_PROC();
        } else {
            if (GDCSDTRE.STLT != '1' 
                && GDCSDTRE.STLT != '3') {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_STLT_INVALID;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B020_GET_AC_INFO_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        IBS.init(SCCGWA, DDCRMST);
        CEP.TRC(SCCGWA, GDCSDTRE.DRAC);
        DDRMST.KEY.CUS_AC = GDCSDTRE.DRAC;
        DDCRMST.FUNC = 'I';
        DDCRMST.REC_PTR = DDRMST;
        DDCRMST.REC_LEN = 425;
        S000_CALL_DDZRMST();
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = GDCSDTRE.DRAC;
        DDRCCY.CCY = GDCSDTRE.CCY;
        DDRCCY.CCY_TYPE = GDCSDTRE.CCY_TYP;
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
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1") 
            || DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.AC_OFFICE_FORBID;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, GDRPLDR);
        GDRPLDR.DEAL_CD = GDCSDTRE.CTA_NO;
        GDRPLDR.BSREF = GDCSDTRE.REF_NO;
        T000_READ_GDTPLDR();
        CEP.TRC(SCCGWA, GDRPLDR.KEY.AC);
        if (WS_PLDR_FLG != 'N') {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = GDRPLDR.KEY.AC;
            CICQACAC.DATA.AGR_SEQ = GDRPLDR.KEY.AC_SEQ;
            S000_CALL_CIZQACAC();
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO);
            IBS.init(SCCGWA, CICCUST);
            CICCUST.FUNC = 'A';
            CICCUST.DATA.AGR_NO = GDCSDTRE.DRAC;
            S000_CALL_CIZCUST();
            CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_NO);
            if (!CICCUST.O_DATA.O_CI_NO.equalsIgnoreCase(CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO)) {
                IBS.init(SCCGWA, CICSOEC);
                CICSOEC.DATA.CI_NO = CICCUST.O_DATA.O_CI_NO;
                CICSOEC.DATA.READ_ONLY_FLG = 'Y';
                S000_CALL_CIZSOEC();
                CEP.TRC(SCCGWA, CICSOEC.DATA.CI_NO);
                CEP.TRC(SCCGWA, CICSOEC.DATA.SPECIAL_CI_NO);
                CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO);
                if (!CICSOEC.DATA.SPECIAL_CI_NO.equalsIgnoreCase(CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO)) {
                    WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CI_NO_NOT_SAME;
                    S000_ERR_MSG_PROC();
                }
            }
        }
    }
    public void B030_CALU_AVA_AMT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRTRAN);
        GDRTRAN.KEY.DEAL_CD = GDCSDTRE.CTA_NO;
        GDRTRAN.KEY.BSREF = GDCSDTRE.REF_NO;
        GDRTRAN.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        if (GDCSDTRE.REF_NO.trim().length() == 0) {
            if (GDCSDTRE.STL_MTH == '3') {
                IBS.init(SCCGWA, DDCIQBAL);
                DDCIQBAL.DATA.AC = GDCSDTRE.DRAC;
                DDCIQBAL.DATA.CCY = GDCSDTRE.CCY;
                S000_CALL_DDZIQBAL();
                CEP.TRC(SCCGWA, DDCIQBAL.DATA.AVL_BAL);
                if (DDCIQBAL.DATA.AVL_BAL == 0) {
                    CEP.ERR(SCCGWA, GDCMSG_ERROR_MSG.GD_AC_BAL_ZERO);
                }
                WS_LACK_AMT = DDCIQBAL.DATA.AVL_BAL;
            }
            CEP.TRC(SCCGWA, GDCSDTRE.AMT);
            if (GDCSDTRE.STL_MTH == '1') {
                WS_LACK_AMT = GDCSDTRE.AMT;
            }
            T000_STARTBR_GDTTRAN();
            T000_READNEXT_GDTTRAN();
            while (WS_TRAN_FLG != 'N' 
                && WS_EXIT_FLG != 'Y') {
                CEP.TRC(SCCGWA, GDRTRAN.ADV_AMT);
                T000_READUP_GDTTRAN();
                if (WS_LACK_AMT < GDRTRAN.ADV_AMT) {
                    WS_EXIT_FLG = 'Y';
                } else {
                    WS_CRDK_AMT = GDRTRAN.ADV_AMT;
                    CEP.TRC(SCCGWA, WS_CRDK_AMT);
                    GDRTRAN.ADV_AMT = 0;
                    GDRTRAN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    GDRTRAN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                    T000_REWRITE_GDTTRAN();
                    WS_ADV_AMT = WS_ADV_AMT + WS_CRDK_AMT;
                    CEP.TRC(SCCGWA, WS_ADV_AMT);
                    WS_LACK_AMT = WS_LACK_AMT - WS_CRDK_AMT;
                    CEP.TRC(SCCGWA, WS_LACK_AMT);
                }
                T000_READNEXT_GDTTRAN();
            }
            T000_ENDBR_GDTTRAN();
        } else {
            CEP.TRC(SCCGWA, GDCSDTRE.AMT);
            T000_READUP_GDTTRAN();
            if (GDRTRAN.ADV_AMT > GDCSDTRE.AMT) {
                WS_ADV_AMT = GDCSDTRE.AMT;
            } else {
                WS_ADV_AMT = GDRTRAN.ADV_AMT;
            }
            GDRTRAN.ADV_AMT = GDRTRAN.ADV_AMT - WS_ADV_AMT;
            GDRTRAN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            GDRTRAN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_GDTTRAN();
        }
        WS_CR_AC = GDRTRAN.ADV_AC;
        if (WS_ADV_AMT == 0) {
            CEP.ERR(SCCGWA, GDCMSG_ERROR_MSG.GD_ADV_AMT_ZERO);
        }
    }
    public void B040_WITHDRAW_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUDRAC);
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.GD_WITHDR_FLG = 'Y';
        DDCUDRAC.AC = GDCSDTRE.DRAC;
        DDCUDRAC.OTHER_AC = WS_CR_AC;
        DDCUDRAC.CCY = GDCSDTRE.CCY;
        if (GDCSDTRE.CCY.equalsIgnoreCase("156")) {
            DDCUDRAC.CCY_TYPE = '1';
        } else {
            DDCUDRAC.CCY_TYPE = '2';
        }
        DDCUDRAC.TX_AMT = WS_ADV_AMT;
        DDCUDRAC.REMARKS = GDCSDTRE.REMARK;
        DDCUDRAC.TX_MMO = "A019";
        DDCUDRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        S000_CALL_DDZUDRAC();
    }
    public void B041_CALL_AI_DR_UNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = GDCSDTRE.DRAC;
        CEP.TRC(SCCGWA, GDCSDTRE.DRAC);
        CEP.TRC(SCCGWA, AICUUPIA.DATA.AC_NO);
        AICUUPIA.DATA.RVS_SEQ = 0;
        AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            AICUUPIA.DATA.VALUE_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        } else {
            AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        AICUUPIA.DATA.AMT = WS_ADV_AMT;
        AICUUPIA.DATA.CCY = GDCSDTRE.CCY;
        AICUUPIA.DATA.EVENT_CODE = "DR";
        AICUUPIA.DATA.POST_NARR = " ";
        AICUUPIA.DATA.RVS_NO = GDCSDTRE.REV_NO;
        AICUUPIA.DATA.RVS_SEQ = GDCSDTRE.REV_CD;
        AICUUPIA.DATA.PAY_MAN = GDCSDTRE.AC_NM;
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
        if (GDCSDTRE.STLT == '1') {
            B050_01_DEP_AI_PROC();
        }
    }
    public void B050_01_DEP_AI_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCSDTRE.REV_NO);
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = GDRTRAN.ADV_AC;
        AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.AMT = WS_ADV_AMT;
        AICUUPIA.DATA.CCY = GDCSDTRE.CCY;
        AICUUPIA.DATA.EVENT_CODE = "CR";
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        S000_CALL_AIZUUPIA();
    }
    public void B070_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = GDCSDTRE.DRAC;
        S000_CALL_CIZACCU();
        IBS.init(SCCGWA, GDCODTRE);
        GDCODTRE.DRAC = GDCSDTRE.DRAC;
        GDCODTRE.AC_NM = CICACCU.DATA.AC_CNM;
        GDCODTRE.CCY = GDCSDTRE.CCY;
        GDCODTRE.CCY_TYP = GDCSDTRE.CCY_TYP;
        GDCODTRE.CTA_NO = GDCSDTRE.CTA_NO;
        GDCODTRE.REF_NO = GDCSDTRE.REF_NO;
        GDCODTRE.PN_AMT = GDCSDTRE.PN_AMT;
        GDCODTRE.AMT = WS_ADV_AMT;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = GDCODTRE;
        SCCFMT.DATA_LEN = 397;
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
    public void S000_CALL_CIZSOEC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-OPEN-EXP-CI", CICSOEC);
        if (CICSOEC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSOEC.RC);
        }
    }
    public void S000_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-CCY-BAL", DDCIQBAL);
        if (DDCIQBAL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQBAL.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC);
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
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_GDTPLDR() throws IOException,SQLException,Exception {
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.where = "DEAL_CD = :GDRPLDR.DEAL_CD";
        GDTPLDR_RD.fst = true;
        IBS.READ(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_PLDR_FLG = 'N';
        }
    }
    public void T000_STARTBR_GDTTRAN() throws IOException,SQLException,Exception {
        GDTTRAN_BR.rp = new DBParm();
        GDTTRAN_BR.rp.TableName = "GDTTRAN";
        GDTTRAN_BR.rp.where = "DEAL_CD = :GDRTRAN.KEY.DEAL_CD "
            + "AND TR_DATE = :GDRTRAN.KEY.TR_DATE";
        IBS.STARTBR(SCCGWA, GDRTRAN, this, GDTTRAN_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_TRAN_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READNEXT_GDTTRAN() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, GDRTRAN, this, GDTTRAN_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TRAN_FLG = 'N';
        }
    }
    public void T000_ENDBR_GDTTRAN() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, GDTTRAN_BR);
    }
    public void T000_GROUP_GDTTRAN() throws IOException,SQLException,Exception {
        GDRTRAN.KEY.DEAL_CD = GDCSDTRE.CTA_NO;
        GDRTRAN.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, GDRTRAN.KEY.DEAL_CD);
        CEP.TRC(SCCGWA, GDRTRAN.KEY.TR_DATE);
        GDTTRAN_RD = new DBParm();
        GDTTRAN_RD.TableName = "GDTTRAN";
        GDTTRAN_RD.where = "DEAL_CD = :GDRTRAN.KEY.DEAL_CD "
            + "AND TR_DATE = :GDRTRAN.KEY.TR_DATE";
        GDTTRAN_RD.set = "WS-ALL-ADV-AMT=IFNULL(SUM(ADV_AMT),0)";
        IBS.GROUP(SCCGWA, GDRTRAN, this, GDTTRAN_RD);
    }
    public void T000_READUP_GDTTRAN() throws IOException,SQLException,Exception {
        GDTTRAN_RD = new DBParm();
        GDTTRAN_RD.TableName = "GDTTRAN";
        GDTTRAN_RD.upd = true;
        IBS.READ(SCCGWA, GDRTRAN, GDTTRAN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_TRAN_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_REWRITE_GDTTRAN() throws IOException,SQLException,Exception {
        GDTTRAN_RD = new DBParm();
        GDTTRAN_RD.TableName = "GDTTRAN";
        IBS.REWRITE(SCCGWA, GDRTRAN, GDTTRAN_RD);
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
