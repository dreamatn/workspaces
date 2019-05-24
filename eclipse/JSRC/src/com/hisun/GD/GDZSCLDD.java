package com.hisun.GD;

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
import com.hisun.CI.*;
import com.hisun.BP.*;
import com.hisun.AI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class GDZSCLDD {
    int JIBS_tmp_int;
    DBParm GDTSTAC_RD;
    DBParm DDTCCY_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "GD181";
    String WS_ERR_MSG = " ";
    GDZSCLDD_WS_TXCTA_NO WS_TXCTA_NO = new GDZSCLDD_WS_TXCTA_NO();
    double WS_BAL = 0;
    double WS_CR_BAL = 0;
    double WS_PLDR_AMT_TOT = 0;
    char WS_CI_TYP_T = ' ';
    double WS_HOLD_AMT_BY_LAW = 0;
    double WS_INT = 0;
    double WS_MARGIN_INT = 0;
    double WS_AMT = 0;
    String WS_OTHER_AC = " ";
    String WS_TXINT_AC = " ";
    double WS_TOT_BAL = 0;
    double WS_SUM_AMT = 0;
    char WS_PLDR_FLG = ' ';
    char WS_CCY_FLG = ' ';
    char WS_REL_GD_AC_FLG = ' ';
    char WS_STAC_FLG = ' ';
    char WS_BAL_ZERO_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    GDRPLDR GDRPLDR = new GDRPLDR();
    GDRHIS GDRHIS = new GDRHIS();
    CICACCU CICACCU = new CICACCU();
    GDCOCLDD GDCOCLDD = new GDCOCLDD();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    DDCSCLAC DDCSCLAC = new DDCSCLAC();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    AICUUPIA AICUUPIA = new AICUUPIA();
    CICQACAC CICQACAC = new CICQACAC();
    CICQACRI CICQACRI = new CICQACRI();
    GDCRPLDR GDCRPLDR = new GDCRPLDR();
    GDCRHIS GDCRHIS = new GDCRHIS();
    DDCRMST DDCRMST = new DDCRMST();
    GDRSTAC GDRSTAC = new GDRSTAC();
    DDCSCPLC DDCSCPLC = new DDCSCPLC();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDVMRAT DDVMRAT = new DDVMRAT();
    AICPQMIB AICPQMIB = new AICPQMIB();
    BPCPQORG BPCPQORG = new BPCPQORG();
    CICSOEC CICSOEC = new CICSOEC();
    DDRMST DDRMST = new DDRMST();
    TDRSMST TDRSMST = new TDRSMST();
    DDRCCY DDRCCY = new DDRCCY();
    SCCGWA SCCGWA;
    GDCSCLDD GDCSCLDD;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA, GDCSCLDD GDCSCLDD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.GDCSCLDD = GDCSCLDD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "GDZSCLDD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_PROC();
        if (pgmRtn) return;
        B020_CHECK_AC_VALIDITY();
        if (pgmRtn) return;
        B030_CHECK_PLDR_REC();
        if (pgmRtn) return;
        B300_GET_TD_FLG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDVMPRD.VAL.TD_FLG);
        if (DDVMPRD.VAL.TD_FLG == '0') {
            B040_WITHDRAW_PROC();
            if (pgmRtn) return;
            if (WS_AMT != 0) {
                if (GDCSCLDD.STLT == '1') {
                    B055_01_DEP_AI_PROC();
                    if (pgmRtn) return;
                } else {
                    B055_DR_STAC_INT_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        B045_CHECK_ACRL_PROC();
        if (pgmRtn) return;
        B050_CLOSE_DRAC_PROC();
        if (pgmRtn) return;
        B060_UPDATE_STAC_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_MARGIN_INT);
        B080_OUTPUT_FMT_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCSCLDD.DRAC);
        CEP.TRC(SCCGWA, GDCSCLDD.CCY);
        CEP.TRC(SCCGWA, GDCSCLDD.STLT);
        CEP.TRC(SCCGWA, GDCSCLDD.CRAC);
        if (GDCSCLDD.DRAC.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_DR_AC_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (GDCSCLDD.CCY.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CCY_NOT_MATCH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (GDCSCLDD.STLT == ' ') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_STLT_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (GDCSCLDD.CRAC.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CR_AC_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_CHECK_AC_VALIDITY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCRMST);
        IBS.init(SCCGWA, DDRMST);
        DDCRMST.FUNC = 'I';
        DDRMST.KEY.CUS_AC = GDCSCLDD.DRAC;
        DDCRMST.REC_PTR = DDRMST;
        DDCRMST.REC_LEN = 425;
        S000_CALL_DDZRMST();
        if (pgmRtn) return;
        if (DDCRMST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCRMST.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRMST.AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRMST.AC_STS == 'O') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INACTIVE_AC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = GDCSCLDD.DRAC;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO);
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = GDCSCLDD.DRAC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        WS_CI_TYP_T = CICACCU.DATA.CI_TYP;
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = GDCSCLDD.CRAC;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_CI_NO);
        if (!CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO.equalsIgnoreCase(CICQACRI.O_DATA.O_CI_NO) 
            && GDCSCLDD.STLT == '3') {
            IBS.init(SCCGWA, CICSOEC);
            CICSOEC.DATA.CI_NO = CICQACRI.O_DATA.O_CI_NO;
            CICSOEC.DATA.READ_ONLY_FLG = 'Y';
            S000_CALL_CIZSOEC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICSOEC.DATA.CI_NO);
            CEP.TRC(SCCGWA, CICSOEC.DATA.SPECIAL_CI_NO);
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO);
            if (!CICSOEC.DATA.SPECIAL_CI_NO.equalsIgnoreCase(CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO)) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CI_NO_NOT_SAME;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (GDCSCLDD.CRAC == null) GDCSCLDD.CRAC = "";
        JIBS_tmp_int = GDCSCLDD.CRAC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) GDCSCLDD.CRAC += " ";
        CEP.TRC(SCCGWA, GDCSCLDD.CRAC.substring(6 - 1, 6 + 3 - 1));
        CEP.TRC(SCCGWA, GDCSCLDD.CCY);
        if (GDCSCLDD.CRAC == null) GDCSCLDD.CRAC = "";
        JIBS_tmp_int = GDCSCLDD.CRAC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) GDCSCLDD.CRAC += " ";
        if (!GDCSCLDD.CCY.equalsIgnoreCase(GDCSCLDD.CRAC.substring(7 - 1, 7 + 3 - 1)) 
            && GDCSCLDD.STLT == '1') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CCY_NOT_MATCH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_CHECK_PLDR_REC() throws IOException,SQLException,Exception {
        B030_10_STARTBR_PLDR_PROC();
        if (pgmRtn) return;
        B030_20_READNEXT_PLDR_PROC();
        if (pgmRtn) return;
        while (WS_PLDR_FLG != 'N') {
            CEP.TRC(SCCGWA, GDRPLDR.RELAT_STS);
            if (GDRPLDR.RELAT_STS == 'N') {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_PLDR_REC_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            B030_20_READNEXT_PLDR_PROC();
            if (pgmRtn) return;
        }
        B030_30_ENDBR_PLDR_PROC();
        if (pgmRtn) return;
    }
    public void B030_10_STARTBR_PLDR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCRPLDR);
        IBS.init(SCCGWA, GDRPLDR);
        CEP.TRC(SCCGWA, "AAAAAAA");
        GDCRPLDR.FUNC = 'B';
        GDCRPLDR.OPT = 'C';
        GDRPLDR.KEY.AC = GDCSCLDD.DRAC;
        GDCRPLDR.REC_PTR = GDRPLDR;
        GDCRPLDR.REC_LEN = 311;
        S000_CALL_GDZRPLDR();
        if (pgmRtn) return;
    }
    public void B030_20_READNEXT_PLDR_PROC() throws IOException,SQLException,Exception {
        GDCRPLDR.FUNC = 'B';
        GDCRPLDR.OPT = 'R';
        GDCRPLDR.REC_PTR = GDRPLDR;
        GDCRPLDR.REC_LEN = 311;
        S000_CALL_GDZRPLDR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "111111111111111111111111111");
        CEP.TRC(SCCGWA, GDCRPLDR.RC);
        if (GDCRPLDR.RC.RC_CODE != 0) {
            WS_PLDR_FLG = 'N';
        } else {
            WS_PLDR_FLG = 'Y';
        }
    }
    public void B030_30_ENDBR_PLDR_PROC() throws IOException,SQLException,Exception {
        GDCRPLDR.FUNC = 'B';
        GDCRPLDR.OPT = 'E';
        GDCRPLDR.REC_PTR = GDRPLDR;
        GDCRPLDR.REC_LEN = 311;
        S000_CALL_GDZRPLDR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BBBBBBBBB");
    }
    public void B040_WITHDRAW_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUDRAC);
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.GD_WITHDR_FLG = 'Y';
        DDCUDRAC.AC = GDCSCLDD.DRAC;
        DDCUDRAC.OTHER_AC = GDCSCLDD.CRAC;
        WS_OTHER_AC = DDCUDRAC.OTHER_AC;
        B170_02_GET_RLT_BR_INFO();
        if (pgmRtn) return;
        DDCUDRAC.OTHER_AC_NM = CICQACRI.O_DATA.O_AC_CNM;
        DDCUDRAC.OTHER_BR = "" + CICQACRI.O_DATA.O_OPN_BR;
        JIBS_tmp_int = DDCUDRAC.OTHER_BR.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) DDCUDRAC.OTHER_BR = "0" + DDCUDRAC.OTHER_BR;
        DDCUDRAC.OTHER_BK_NM = BPCPQORG.CHN_NM;
        DDCUDRAC.CCY = GDCSCLDD.CCY;
        DDCUDRAC.CCY_TYPE = GDCSCLDD.CCY_TYP;
        if (GDCSCLDD.CCY.equalsIgnoreCase("156")) {
            DDCUDRAC.CCY_TYPE = '1';
        }
        DDCUDRAC.TX_AMT = 0;
        DDCUDRAC.CLEAR_FLG = 'Y';
        DDCUDRAC.REMARKS = " ";
        DDCUDRAC.TX_MMO = "A019";
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            DDCUDRAC.VAL_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        } else {
            DDCUDRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        S000_CALL_DDZUDRAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCUDRAC.MARGIN_INT);
        CEP.TRC(SCCGWA, DDCUDRAC.TX_AMT);
        WS_MARGIN_INT = DDCUDRAC.MARGIN_INT;
        WS_AMT = DDCUDRAC.TX_AMT;
        CEP.TRC(SCCGWA, DDCUDRAC.MARGIN_INT);
        if (WS_MARGIN_INT > 0) {
            B055_03_GET_INT_AC();
            if (pgmRtn) return;
            B055_02_DEP_DD_PROC();
            if (pgmRtn) return;
        }
    }
    public void B045_CHECK_ACRL_PROC() throws IOException,SQLException,Exception {
    }
    public void B050_CLOSE_DRAC_PROC() throws IOException,SQLException,Exception {
        if (WS_CI_TYP_T == '1') {
            R000_PERSON_CLS_PROC();
            if (pgmRtn) return;
        } else {
            R000_COMPANY_CLS_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_PERSON_CLS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSCLAC);
        DDCSCLAC.GD_WITHDR_FLG = 'Y';
        DDCSCLAC.AC_NO = GDCSCLDD.DRAC;
        DDCSCLAC.CCY = GDCSCLDD.CCY;
        if (GDCSCLDD.CCY.equalsIgnoreCase("156")) {
            DDCSCLAC.CCY_TYPE = '1';
        } else {
            DDCSCLAC.CCY_TYPE = '2';
        }
        DDCSCLAC.TRF_FLG = 'T';
        DDCSCLAC.TRF_AC = GDCSCLDD.CRAC;
        DDCSCLAC.AC_BAL = GDCSCLDD.BAL;
        if (GDCSCLDD.STLT == '1') {
            DDCSCLAC.TO_BVTYP = '3';
        } else {
            if (GDCSCLDD.STLT == '3') {
                DDCSCLAC.TO_BVTYP = '4';
            }
        }
        DDCSCLAC.REMARK = GDCSCLDD.RMK;
        if (GDCSCLDD.MMO.trim().length() == 0) {
            DDCSCLAC.MMO = "A019";
        } else {
            DDCSCLAC.MMO = GDCSCLDD.MMO;
        }
        CEP.TRC(SCCGWA, DDCSCLAC.CCY);
        CEP.TRC(SCCGWA, DDCSCLAC.CCY_TYPE);
        S000_CALL_DDZSCLAC();
        if (pgmRtn) return;
        if (GDCSCLDD.BAL != 0) {
            B070_WRITE_HIS_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_COMPANY_CLS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSCPLC);
        if (GDCSCLDD.CCY.equalsIgnoreCase("156")) {
            GDCSCLDD.CCY_TYP = '1';
        }
        if (GDCSCLDD.STLT == '1') {
            DDCSCPLC.TO_BV_TYPE = '3';
        } else {
            DDCSCPLC.TO_BV_TYPE = '4';
        }
        DDCSCPLC.FR_AC = GDCSCLDD.DRAC;
        DDCSCPLC.FR_AC_CNAME = GDCSCLDD.DRAC_NM;
        DDCSCPLC.TO_AC = GDCSCLDD.CRAC;
        DDCSCPLC.TO_AC_CNAME = GDCSCLDD.CRAC_NM;
        DDCSCPLC.FR_CCY = GDCSCLDD.CCY;
        DDCSCPLC.FR_CCY_TYPE = GDCSCLDD.CCY_TYP;
        DDCSCPLC.TO_CCY = GDCSCLDD.CCY;
        DDCSCPLC.TO_CCY_TYPE = GDCSCLDD.CCY_TYP;
        if (GDCSCLDD.MMO.trim().length() == 0) {
            DDCSCPLC.TX_RMK = "A019";
        } else {
            DDCSCPLC.TX_RMK = GDCSCLDD.MMO;
        }
        DDCSCPLC.REMARKS = GDCSCLDD.RMK;
        DDCSCPLC.TO_BV_AM = '3';
        S000_CALL_DDZSCPLC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCSCPLC.TO_AMT);
        if (DDCSCPLC.TO_AMT != 0) {
            B070_WRITE_HIS_PROC();
            if (pgmRtn) return;
        }
    }
    public void B055_DR_STAC_INT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUCRAC);
        DDCUCRAC.TX_TYPE = 'T';
        DDCUCRAC.AC = GDCSCLDD.CRAC;
        DDCUCRAC.CCY = GDCSCLDD.CCY;
        DDCUCRAC.CCY_TYPE = GDCSCLDD.CCY_TYP;
        DDCUCRAC.TX_AMT = WS_AMT;
        DDCUCRAC.OTHER_AC = GDCSCLDD.DRAC;
        WS_OTHER_AC = DDCUCRAC.OTHER_AC;
        B170_02_GET_RLT_BR_INFO();
        if (pgmRtn) return;
        DDCUCRAC.OTHER_AC_NM = CICQACRI.O_DATA.O_AC_CNM;
        DDCUCRAC.OTHER_BR = "" + CICQACRI.O_DATA.O_OPN_BR;
        JIBS_tmp_int = DDCUCRAC.OTHER_BR.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) DDCUCRAC.OTHER_BR = "0" + DDCUCRAC.OTHER_BR;
        DDCUCRAC.OTHER_BK_NM = BPCPQORG.CHN_NM;
        DDCUCRAC.TX_MMO = GDCSCLDD.MMO;
        DDCUCRAC.TX_MMO = "S101";
        S000_CALL_DDZUCRAC();
        if (pgmRtn) return;
    }
    public void B055_01_DEP_AI_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = GDCSCLDD.CRAC;
        AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.AMT = WS_AMT;
        AICUUPIA.DATA.CCY = GDCSCLDD.CCY;
        AICUUPIA.DATA.PAY_MAN = GDCSCLDD.DRAC_NM;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            AICUUPIA.DATA.VALUE_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        } else {
            AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        AICUUPIA.DATA.EVENT_CODE = "CR";
        S000_CALL_AIZUUPIA();
        if (pgmRtn) return;
    }
    public void B055_02_DEP_DD_PROC() throws IOException,SQLException,Exception {
        if (WS_TXINT_AC.trim().length() > 0) {
            IBS.init(SCCGWA, DDCUCRAC);
            DDCUCRAC.TX_TYPE = 'T';
            DDCUCRAC.AC = WS_TXINT_AC;
            DDCUCRAC.CCY = GDCSCLDD.CCY;
            DDCUCRAC.CCY_TYPE = GDCSCLDD.CCY_TYP;
            DDCUCRAC.TX_AMT = WS_MARGIN_INT;
            DDCUCRAC.OTHER_AC = GDCSCLDD.DRAC;
            WS_OTHER_AC = DDCUCRAC.OTHER_AC;
            B170_02_GET_RLT_BR_INFO();
            if (pgmRtn) return;
            DDCUCRAC.OTHER_AC_NM = CICQACRI.O_DATA.O_AC_CNM;
            DDCUCRAC.OTHER_BR = "" + CICQACRI.O_DATA.O_OPN_BR;
            JIBS_tmp_int = DDCUCRAC.OTHER_BR.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) DDCUCRAC.OTHER_BR = "0" + DDCUCRAC.OTHER_BR;
            DDCUCRAC.OTHER_BK_NM = BPCPQORG.CHN_NM;
            DDCUCRAC.REMARKS = GDCSCLDD.RMK;
            DDCUCRAC.TX_MMO = "S101";
            S000_CALL_DDZUCRAC();
            if (pgmRtn) return;
        }
    }
    public void B055_03_GET_INT_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRSTAC);
        GDRSTAC.KEY.AC = GDCSCLDD.DRAC;
        T000_READ_GDTSTAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, GDRSTAC.KEY.AC);
        CEP.TRC(SCCGWA, GDRSTAC.KEY.AC_SEQ);
        CEP.TRC(SCCGWA, GDRSTAC.ST_AC);
        CEP.TRC(SCCGWA, GDRSTAC.INT_AC);
        if (GDRSTAC.INT_AC.trim().length() > 0) {
            WS_TXINT_AC = GDRSTAC.INT_AC;
        } else {
            WS_TXINT_AC = GDRSTAC.ST_AC;
        }
        CEP.TRC(SCCGWA, WS_TXINT_AC);
        if (WS_TXINT_AC.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_INTAC_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B060_UPDATE_STAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRSTAC);
        GDRSTAC.KEY.AC = GDCSCLDD.DRAC;
        T000_READ_UPDATE_PROC();
        if (pgmRtn) return;
        if (WS_STAC_FLG == 'Y') {
            GDRSTAC.RELAT_STS = 'R';
            GDRSTAC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            GDRSTAC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_REC_PROC();
            if (pgmRtn) return;
        }
    }
    public void B070_WRITE_HIS_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA.BA_CNT = (short) (GWA_SC_AREA.BA_CNT + 1);
        CEP.TRC(SCCGWA, GWA_SC_AREA.BA_CNT);
        IBS.init(SCCGWA, GDRHIS);
        GDCRHIS.FUNC = 'A';
        GDRHIS.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRHIS.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        GDRHIS.KEY.SEQ = GWA_SC_AREA.BA_CNT;
        GDRHIS.RSEQ = GDRPLDR.KEY.RSEQ;
        GDRHIS.AC = GDCSCLDD.DRAC;
        GDRHIS.FUNC = '2';
        GDRHIS.DEAL_CD = GDRPLDR.DEAL_CD;
        GDRHIS.BSREF = GDRPLDR.BSREF;
        GDRHIS.CHNL_CD = SCCGWA.COMM_AREA.REQ_SYSTEM;
        GDRHIS.TR_AMT = DDCSCLAC.AC_BAL;
        GDRHIS.TR_AC = GDCSCLDD.CRAC;
        GDRHIS.CAN_FLG = 'N';
        GDRHIS.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRHIS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        GDRHIS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRHIS.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        GDCRHIS.REC_PTR = GDRHIS;
        GDCRHIS.REC_LEN = 281;
        S000_CALL_GDZRHIS();
        if (pgmRtn) return;
    }
    public void B300_GET_TD_FLG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.AGR_NO = GDCSCLDD.DRAC;
        CICQACAC.DATA.CCY_ACAC = GDCSCLDD.CCY;
        if (GDCSCLDD.CCY_TYP != ' ') {
            DDRCCY.CCY_TYPE = GDCSCLDD.CCY_TYP;
            CICQACAC.DATA.CR_FLG = GDCSCLDD.CCY_TYP;
        } else {
            if (GDCSCLDD.CCY.equalsIgnoreCase("156")) {
                DDRCCY.CCY_TYPE = '1';
                CICQACAC.DATA.CR_FLG = '1';
            }
        }
        CICQACAC.FUNC = 'R';
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        CEP.TRC(SCCGWA, DDRCCY.KEY.AC);
        T000_READ_DDTCCY();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDCIQPRD);
        IBS.init(SCCGWA, DDVMPRD);
        IBS.init(SCCGWA, DDVMRAT);
        DDCIQPRD.INPUT_DATA.PROD_CODE = DDRCCY.PROD_CODE;
        CEP.TRC(SCCGWA, DDRCCY.PROD_CODE);
        DDCIQPRD.INPUT_DATA.CCY = GDCSCLDD.CCY;
        DDCIQPRD.DDVMPRD_PTR = DDVMPRD;
        DDCIQPRD.DDVMRAT_PTR = DDVMRAT;
        S000_CALL_DDZIQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRMST.PROD_CODE);
        if (DDRCCY.HOLD_BAL != 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.AC_HAD_AMT_HOLD;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B080_OUTPUT_FMT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = GDCSCLDD.DRAC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        GDCSCLDD.CI_NM = CICACCU.DATA.AC_CNM;
        GDCSCLDD.CI_NO = CICACCU.DATA.CI_NO;
        IBS.init(SCCGWA, GDCOCLDD);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        GDCOCLDD.VAL.CI_NO = GDCSCLDD.CI_NO;
        GDCOCLDD.VAL.CI_NM = GDCSCLDD.CI_NM;
        GDCOCLDD.VAL.DR_AC = GDCSCLDD.DRAC;
        GDCOCLDD.VAL.AC_NM = GDCSCLDD.DRAC_NM;
        GDCOCLDD.VAL.CCY = GDCSCLDD.CCY;
        if (GDCSCLDD.CCY.equalsIgnoreCase("156")) {
            GDCOCLDD.VAL.CCY_TYP = '1';
        } else {
            GDCOCLDD.VAL.CCY_TYP = '2';
        }
        WS_SUM_AMT = WS_MARGIN_INT + WS_AMT;
        CEP.TRC(SCCGWA, WS_SUM_AMT);
        GDCOCLDD.VAL.BAL = WS_SUM_AMT;
        CEP.TRC(SCCGWA, GDCOCLDD.VAL.BAL);
        GDCOCLDD.VAL.INT = DDCSCLAC.AC_INT;
        GDCOCLDD.VAL.CR_AC = GDCSCLDD.CRAC;
        GDCOCLDD.VAL.RMK = GDCSCLDD.RMK;
        SCCFMT.DATA_PTR = GDCOCLDD;
        SCCFMT.DATA_LEN = 739;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B170_02_GET_RLT_BR_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = WS_OTHER_AC;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_OPN_BR);
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        IBS.init(SCCGWA, BPCPQORG);
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
            IBS.init(SCCGWA, AICPQMIB);
            AICPQMIB.INPUT_DATA.AC = WS_OTHER_AC;
            S000_CALL_AIZPQMIB();
            if (pgmRtn) return;
            BPCPQORG.BR = AICPQMIB.INPUT_DATA.BR;
        } else {
            BPCPQORG.BR = CICQACRI.O_DATA.O_OPN_BR;
        }
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
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
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZSCPLC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-S-CP-COLSE", DDCSCPLC);
    }
    public void S000_CALL_DDZRMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-R-DDTMST", DDCRMST);
    }
    public void S000_CALL_DDZSCLAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-S-CLS-AC", DDCSCLAC, true);
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC);
    }
    public void S000_CALL_CIZSOEC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-OPEN-EXP-CI", CICSOEC);
        if (CICSOEC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSOEC.RC);
        }
    }
    public void S000_CALL_GDZRPLDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SRC-GDZRPLDR", GDCRPLDR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_PLDR_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_GDZRHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SRC-GDZRHIS", GDCRHIS);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_HIS_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        if (CICQACRI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC);
    }
    public void S000_CALL_DDZIQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-DDPRD", DDCIQPRD);
        if (DDCIQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void T000_READ_GDTSTAC() throws IOException,SQLException,Exception {
        GDTSTAC_RD = new DBParm();
        GDTSTAC_RD.TableName = "GDTSTAC";
        GDTSTAC_RD.where = "AC = :GDRSTAC.KEY.AC "
            + "AND AC_SEQ = :GDRSTAC.KEY.AC_SEQ";
        IBS.READ(SCCGWA, GDRSTAC, this, GDTSTAC_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_STAC_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        CEP.TRC(SCCGWA, DDRCCY.KEY.AC);
        CEP.TRC(SCCGWA, DDRCCY.CCY);
        CEP.TRC(SCCGWA, DDRCCY.CCY_TYPE);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        GDTSTAC_RD = new DBParm();
        GDTSTAC_RD.TableName = "GDTSTAC";
        GDTSTAC_RD.upd = true;
        IBS.READ(SCCGWA, GDRSTAC, GDTSTAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_STAC_FLG = 'Y';
        } else {
            WS_STAC_FLG = 'N';
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        GDTSTAC_RD = new DBParm();
        GDTSTAC_RD.TableName = "GDTSTAC";
        IBS.REWRITE(SCCGWA, GDRSTAC, GDTSTAC_RD);
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
