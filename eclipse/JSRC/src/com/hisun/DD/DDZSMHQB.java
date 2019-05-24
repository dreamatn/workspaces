package com.hisun.DD;

import com.hisun.CI.*;
import com.hisun.DC.*;
import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSMHQB {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm DDTMSTR_RD;
    DBParm DDTACCU_RD;
    DBParm DDTINTB_RD;
    DBParm DDTCCY_RD;
    DBParm DDTHLD_RD;
    brParm DDTMSTR_BR = new brParm();
    DBParm DDTHQBR_RD;
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DD121";
    String K_OUTPUT_FMT2 = "DD122";
    String K_STS_TABLE_APP = "DD";
    String K_TENOR = "S000";
    String K_CHK_STS_TBL = "5122";
    String CPN_UNI_CIZACCU = "CI-INQ-ACCU";
    String K_HIS_COPYBOOK_NAME = "DDCSMHQB";
    String K_HIS_REMARKS = "ACTIVATION ACCOUNT";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_ERR_MSG = " ";
    short WS_IDX = 0;
    char WS_MSTR_STS = ' ';
    String WS_MTDF_AC = " ";
    double WS_MAX_AMT = 0;
    double WS_MAX_AMT1 = 0;
    double WS_MAX_AMT2 = 0;
    double WS_MAX_AMT3 = 0;
    double WS_MAX_AMT4 = 0;
    double WS_MAX_AMT5 = 0;
    int WS_EXP_DATE = 0;
    short WS_CNT1 = 0;
    short WS_CNT2 = 0;
    short WS_CNT3 = 0;
    String WS_CI_NO = "            ";
    short WS_CLDT_DAYS = 0;
    short WS_HQB_I = 0;
    short WS_I = 0;
    DDZSMHQB_WS_HQB_RMK WS_HQB_RMK = new DDZSMHQB_WS_HQB_RMK();
    DDZSMHQB_WS_HQB_DATA WS_HQB_DATA = new DDZSMHQB_WS_HQB_DATA();
    int WS_MHQB_STRDT = 0;
    int WS_MHQB_EXPDT = 0;
    char WS_Q_ADP_STS = ' ';
    DDZSMHQB_WS_CHEK_INF[] WS_CHEK_INF = new DDZSMHQB_WS_CHEK_INF[5];
    DDZSMHQB_WS_OUT_INF WS_OUT_INF = new DDZSMHQB_WS_OUT_INF();
    char WS_MSTR_FLG = ' ';
    char WS_INTB_FLG = ' ';
    char WS_CCY_FLG = ' ';
    char WS_MSTR_DU_KEY_FLG = ' ';
    char WS_MSTR_OPEN_FLG = ' ';
    char WS_MSTR_SAME_DAY_FLG = ' ';
    char WS_INQ_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDRFHIS DDRFHIS = new DDRFHIS();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    DDVMPRD DDVMPRD = new DDVMPRD();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    BPCPGDIN BPCPGDIN = new BPCPGDIN();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    DDCOATAC DDCOATAC = new DDCOATAC();
    DDCSCINM DDCSCINM = new DDCSCINM();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCCINTI BPCCINTI = new BPCCINTI();
    CICMACR CICMACR = new CICMACR();
    CICACCU CICACCU = new CICACCU();
    CICIAGT CICIAGT = new CICIAGT();
    DDRMSTR DDRMSTR = new DDRMSTR();
    DDRMSTR DDRMSTR1 = new DDRMSTR();
    DDCSMTDQ DDCSMTDQ = new DDCSMTDQ();
    DDRINTB DDRINTB = new DDRINTB();
    DDRACCU DDRACCU = new DDRACCU();
    DDCPHQBP DDCPHQBP = new DDCPHQBP();
    DCCPACTY DCCPACTY = new DCCPACTY();
    CICQACRL CICQACRL = new CICQACRL();
    CICQACAC CICQACAC = new CICQACAC();
    CICMAGT CICMAGT = new CICMAGT();
    DCCUCINF DCCUCINF = new DCCUCINF();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCWRMSG SCCWRMSG = new SCCWRMSG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    CICMLS2 CICMLS2 = new CICMLS2();
    DDRHQBR DDRHQBR = new DDRHQBR();
    DCCUHLD DCCUHLD = new DCCUHLD();
    DDRHLD DDRHLD = new DDRHLD();
    DCCURHLD DCCURHLD = new DCCURHLD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DDCSMHQB DDCSMHQB;
    public DDZSMHQB() {
        for (int i=0;i<5;i++) WS_CHEK_INF[i] = new DDZSMHQB_WS_CHEK_INF();
    }
    public void MP(SCCGWA SCCGWA, DDCSMHQB DDCSMHQB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSMHQB = DDCSMHQB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSMHQB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (DDCSMHQB.CUS_AC.trim().length() > 0) {
            B020_GET_CI_INF_PROC();
            if (pgmRtn) return;
            B030_GET_CCY_INF_PROC();
            if (pgmRtn) return;
        }
        if (DDCSMHQB.FUNC == '1' 
            || DDCSMHQB.FUNC == '2') {
            B040_CHK_AC_STS();
            if (pgmRtn) return;
        }
        B050_BEGIN_MPAGE_OUTPUT();
        if (pgmRtn) return;
        if (DDCSMHQB.FUNC == '1') {
            WS_HQB_RMK.WS_HQB_FUNC = "签约";
            B050_ADD_MSTR_REC();
            if (pgmRtn) return;
            B035_GEN_CI_AGT_NO();
            if (pgmRtn) return;
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 43 - 1) + "1" + DDRCCY.STS_WORD.substring(43 + 1 - 1);
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 48 - 1) + "1" + DDRCCY.STS_WORD.substring(48 + 1 - 1);
            if (DDCSMHQB.FLG.equalsIgnoreCase("B")) {
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 46 - 1) + "1" + DDRCCY.STS_WORD.substring(46 + 1 - 1);
            }
            DDRCCY.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DDTCCY();
            if (pgmRtn) return;
            if (DDCSMHQB.FLG.equalsIgnoreCase("1") 
                || DDCSMHQB.FLG.equalsIgnoreCase("2")) {
                B040_HLD_PROCESS();
                if (pgmRtn) return;
            }
            B050_OUTPUT_MPAGE_DETAIL();
            if (pgmRtn) return;
        } else if (DDCSMHQB.FUNC == '2') {
            WS_HQB_RMK.WS_HQB_FUNC = "解约";
            B050_DEL_MSTR_REC();
            if (pgmRtn) return;
            B035_CAN_CI_AGT_NO();
            if (pgmRtn) return;
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 43 - 1) + "0" + DDRCCY.STS_WORD.substring(43 + 1 - 1);
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 48 - 1) + "0" + DDRCCY.STS_WORD.substring(48 + 1 - 1);
            if (DDCSMHQB.FLG.equalsIgnoreCase("B")) {
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 46 - 1) + "0" + DDRCCY.STS_WORD.substring(46 + 1 - 1);
            }
            DDRCCY.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DDTCCY();
            if (pgmRtn) return;
            if (DDCSMHQB.FLG.equalsIgnoreCase("1") 
                || DDCSMHQB.FLG.equalsIgnoreCase("2")) {
                B040_RHLD_PROCESS();
                if (pgmRtn) return;
            }
            B050_OUTPUT_MPAGE_DETAIL();
            if (pgmRtn) return;
        } else if (DDCSMHQB.FUNC == '3') {
            if (WS_INQ_FLG == 'A') {
                if (DDCSMHQB.STS != 'Z') {
                    B050_INQ_MSTR_REC_1();
                    if (pgmRtn) return;
                } else {
                    B050_INQ_MSTR_REC_2();
                    if (pgmRtn) return;
                }
            }
            if (WS_INQ_FLG == 'B') {
                B050_INQ_GET_AC_INFO();
                if (pgmRtn) return;
            }
            if (WS_INQ_FLG == 'C') {
                B050_INQ_MSTR_REC_3();
                if (pgmRtn) return;
            }
        } else {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FUNC_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.JRN_NO != 0 
            && DDCSMHQB.FUNC != '3') {
            B170_NFIN_TX_HIS_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSMHQB.FUNC);
        CEP.TRC(SCCGWA, DDCSMHQB.CUS_AC);
        CEP.TRC(SCCGWA, DDCSMHQB.CCY);
        CEP.TRC(SCCGWA, DDCSMHQB.CCY_TYP);
        CEP.TRC(SCCGWA, DDCSMHQB.FLG);
        CEP.TRC(SCCGWA, DDCSMHQB.STRDT);
        CEP.TRC(SCCGWA, DDCSMHQB.EXPDT);
        CEP.TRC(SCCGWA, DDCSMHQB.STS);
        if (DDCSMHQB.FUNC == '1' 
            && DDCSMHQB.FLG.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_HQB_FLG_MUST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSMHQB.FUNC == '3') {
            if (DDCSMHQB.STRDT == 0 
                && DDCSMHQB.EXPDT != 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCSMHQB.STRDT != 0 
                && DDCSMHQB.EXPDT == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCSMHQB.CUS_AC.trim().length() > 0) {
                WS_INQ_FLG = 'A';
            } else {
                if (DDCSMHQB.CI_NO.trim().length() > 0) {
                    WS_INQ_FLG = 'B';
                } else {
                    WS_INQ_FLG = 'C';
                }
            }
            if (DDCSMHQB.STS == 'O'
                || DDCSMHQB.STS == 'F'
                || DDCSMHQB.STS == 'Z') {
            } else {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B170_NFIN_TX_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.AC = DDCSMHQB.CUS_AC;
        BPCPNHIS.INFO.CCY = DDRCCY.CCY;
        BPCPNHIS.INFO.CCY_TYPE = DDRCCY.CCY_TYPE;
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK_NAME;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        if (DDRMSTR.KEY.ADP_TYPE.equalsIgnoreCase("4")) {
            WS_HQB_RMK.WS_HQB_FLG = "基础";
        } else {
            if (DDRMSTR.KEY.ADP_TYPE.equalsIgnoreCase("5")) {
                WS_HQB_RMK.WS_HQB_FLG = "   A";
            } else {
                WS_HQB_RMK.WS_HQB_FLG = "   B";
            }
        }
        WS_HQB_RMK.WS_HQB_FIL = "�?  ";
        BPCPNHIS.INFO.TX_RMK = IBS.CLS2CPY(SCCGWA, WS_HQB_RMK);
        if (DDCSMHQB.FUNC == '2') {
            BPCPNHIS.INFO.TX_TYP_CD = "P603";
        } else {
            BPCPNHIS.INFO.TX_TYP_CD = "P602";
        }
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B020_GET_CI_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDCSMHQB.CUS_AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
        if (DDCSMHQB.FUNC == '1' 
            && SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BSP")) {
            CEP.TRC(SCCGWA, DDCSMHQB.CI_NO);
            if (DDCSMHQB.CI_NO.trim().length() == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CI_INFO_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (!DDCSMHQB.CI_NO.equalsIgnoreCase(CICACCU.DATA.CI_NO)) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CI_INFO_NOT_SAME;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B030_GET_CCY_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'C';
        CICQACAC.DATA.AGR_NO = DDCSMHQB.CUS_AC;
        CICQACAC.DATA.CCY_ACAC = DDCSMHQB.CCY;
        CICQACAC.DATA.CR_FLG = DDCSMHQB.CCY_TYP;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READ_UPD_DDTCCY();
        if (pgmRtn) return;
        if (DDRCCY.CURR_BAL < 8000 
            && DDCSMHQB.FUNC == '1' 
            && !DDCSMHQB.FLG.equalsIgnoreCase("J")) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AVL_BAL_NOT_ENOUGH);
        }
        if (DDCSMHQB.FUNC == '1' 
            && (DDCSMHQB.FLG.equalsIgnoreCase("1") 
            || DDCSMHQB.FLG.equalsIgnoreCase("2")) 
            && DDRCCY.CURR_BAL < 100000) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AVL_BAL_NOT_ENOUGH);
        }
    }
    public void B040_CHK_AC_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUCINF);
        DCCUCINF.CARD_NO = DDCSMHQB.CUS_AC;
        CEP.TRC(SCCGWA, DCCUCINF.CARD_NO);
        S000_CALL_DCZUCINF();
        if (pgmRtn) return;
        if (DDCSMHQB.FUNC == '1') {
            if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
            JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
            if (DCCUCINF.CARD_STSW.substring(0, 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_ALREADY_LOSE);
            }
            if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
            JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
            if (DCCUCINF.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_ALR_CARD_LOSS_STS);
            }
        }
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.AC_CLOSE_AND_REDEMP);
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (!JIBS_tmp_str[0].equalsIgnoreCase("117580")) {
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.BK_INTER_TEMP_FORBID);
            }
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.AC_OFFICE_FORBID);
            }
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
                || DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.AC_FREEZE_CNOT_TR);
            }
            if (CICACCU.DATA.CI_STSW == null) CICACCU.DATA.CI_STSW = "";
            JIBS_tmp_int = CICACCU.DATA.CI_STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CICACCU.DATA.CI_STSW += " ";
            if (CICACCU.DATA.CI_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CI_CLOSE);
            }
            if (CICACCU.DATA.CI_STSW == null) CICACCU.DATA.CI_STSW = "";
            JIBS_tmp_int = CICACCU.DATA.CI_STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CICACCU.DATA.CI_STSW += " ";
            if (!CICACCU.DATA.CI_STSW.substring(41 - 1, 41 + 3 - 1).equalsIgnoreCase("000")) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_IS_ON_BLACK_LIST_E);
            }
            if (CICACCU.DATA.CI_STSW == null) CICACCU.DATA.CI_STSW = "";
            JIBS_tmp_int = CICACCU.DATA.CI_STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CICACCU.DATA.CI_STSW += " ";
            if (CICACCU.DATA.CI_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCZM_CI_FBID);
            }
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_DORMANT_AC);
            }
        }
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDCSMHQB.FUNC == '1' 
            && DDRCCY.STS_WORD.substring(43 - 1, 43 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CI_SETON_DDCALL);
        }
        if (DDCSMHQB.FUNC == '1' 
            && (DDCSMHQB.FLG.equalsIgnoreCase("1") 
            || DDCSMHQB.FLG.equalsIgnoreCase("2"))) {
            CEP.TRC(SCCGWA, DCCUCINF.CARD_HLDR_IDTYP);
            CEP.TRC(SCCGWA, DCCUCINF.CARD_HLDR_IDNO);
            CEP.TRC(SCCGWA, DCCUCINF.CARD_HLDR_CNM);
            IBS.init(SCCGWA, CICMLS2);
            CICMLS2.DATA.LST_CD = "WHT";
            CICMLS2.DATA.LST_SEQ = 1;
            CICMLS2.DATA.ID_TYPE = DCCUCINF.CARD_HLDR_IDTYP;
            CICMLS2.DATA.ID_NO = DCCUCINF.CARD_HLDR_IDNO;
            CICMLS2.DATA.CI_CNM = DCCUCINF.CARD_HLDR_CNM;
            CICMLS2.FUNC = 'I';
            S000_LINK_CIZMLS2();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICMLS2.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICMLS2.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase("CI4101")) {
                IBS.init(SCCGWA, DDRHQBR);
                CEP.TRC(SCCGWA, DDRCCY.OWNER_BR);
                DDRHQBR.KEY.SCD_BR = DDRCCY.OWNER_BR;
                T000_READ_DDTHQBR();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, DDRHQBR.OPEN_FLG);
                if (DDRHQBR.OPEN_FLG == '0' 
                    || SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_HQB_NOT_ALLOW);
                }
            } else {
                if (CICMLS2.RC.RC_CODE != 0) {
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICMLS2.RC);
                    CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
                }
            }
        }
    }
    public void B050_ADD_MSTR_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMSTR);
        DDRMSTR.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRMSTR.ADP_STS = 'O';
        T000_READ_DDTMSTR_FIRST();
        if (pgmRtn) return;
        if (WS_MSTR_FLG == 'F') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CI_SETON_DDCALL);
        }
        if (WS_MSTR_FLG == 'N') {
            B050_ADD_DDTMSTR();
            if (pgmRtn) return;
            if (DDRMSTR.KEY.ADP_TYPE.equalsIgnoreCase("B")) {
                IBS.init(SCCGWA, DDRINTB);
                DDRINTB.KEY.AC = DDRCCY.KEY.AC;
                DDRINTB.KEY.TYPE = 'D';
                T000_READ_UPD_DDTINTB();
                if (pgmRtn) return;
                if (WS_INTB_FLG == 'F') {
                    B050_INT_DIV_PROC();
                    if (pgmRtn) return;
                }
            }
        } else {
            T000_READ_UPD_DDTMSTR_01();
            if (pgmRtn) return;
            DDRMSTR.ADP_STS = 'F';
            DDRMSTR.ADP_EXPDATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRMSTR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRMSTR.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_DELETE_DDTMSTR();
            if (pgmRtn) return;
            B035_CAN_CI_AGT_NO();
            if (pgmRtn) return;
            WS_HQB_RMK.WS_HQB_FUNC = "解约";
            B170_NFIN_TX_HIS_PROC();
            if (pgmRtn) return;
            WS_HQB_RMK.WS_HQB_FUNC = "签约";
            IBS.init(SCCGWA, DDRINTB);
            DDRINTB.KEY.AC = DDRCCY.KEY.AC;
            DDRINTB.KEY.TYPE = 'D';
            T000_READ_UPD_DDTINTB();
            if (pgmRtn) return;
            if (DDRMSTR.KEY.ADP_STRDATE > SCCGWA.COMM_AREA.AC_DATE) {
                B070_CHANGE_PROD();
                if (pgmRtn) return;
            }
            if (WS_INTB_FLG == 'F' 
                && DDRMSTR.KEY.ADP_TYPE.equalsIgnoreCase("5")) {
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = DDRMSTR.KEY.ADP_STRDATE;
                SCCCLDT.DATE2 = SCCGWA.COMM_AREA.AC_DATE;
                S000_CALL_SCSSCLDT();
                if (pgmRtn) return;
                WS_CLDT_DAYS = (short) SCCCLDT.DAYS;
                if (WS_CLDT_DAYS >= 90) {
                    R000_GET_HQBA_PARM();
                    if (pgmRtn) return;
                    R000_GET_HQBA_RATE();
                    if (pgmRtn) return;
                    R000_HQBA_INT_PROC();
                    if (pgmRtn) return;
                    B050_HQB_INT_DIV_PROC();
                    if (pgmRtn) return;
                }
            }
            B050_ADD_DDTMSTR();
            if (pgmRtn) return;
            R000_CLEAR_INTB_INF();
            if (pgmRtn) return;
        }
    }
    public void B050_INT_DIV_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRACCU);
        DDRACCU.KEY.AC = DDRINTB.KEY.AC;
        DDRACCU.KEY.END_DATE = DDRINTB.END_TOT_DATE;
        DDRACCU.KEY.CLS = 'R';
        DDRACCU.KEY.TYPE = '1';
        DDRACCU.CAL_INT_MTH = DDRINTB.CAL_INT_MTH;
        DDRACCU.INT_STS = 'N';
        DDRACCU.TIR_TYPE = DDRINTB.TIR_TYPE;
        DDRACCU.AGSP_FLG = DDRINTB.AGSP_FLG;
        DDRACCU.KEY.STR_DATE = DDRINTB.STRT_TOT_DATE;
        DDRACCU.TIR_AMT = DDRINTB.TIR_AMT1;
        DDRACCU.TOT = DDRINTB.DEP_TOT1;
        DDRACCU.RATE = DDRINTB.TIR_RATE1;
        DDRACCU.INT = DDRACCU.TOT * DDRACCU.RATE / 360 / 100;
        DDRACCU.AFT_ADJ_INT = DDRACCU.INT;
        DDRACCU.TAX_RATE = DDRINTB.TAX_RATE;
        DDRACCU.TAX = DDRACCU.INT * DDRACCU.TAX_RATE;
        T000_WRITE_DDTACCU();
        if (pgmRtn) return;
        DDRINTB.DEP_TOT1 = 0;
        DDRINTB.TIR_RATE1 = 0;
        DDRINTB.STRT_TOT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRINTB.END_TOT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRINTB.LST_TOT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_DDTINTB();
        if (pgmRtn) return;
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 47 - 1) + "1" + DDRCCY.STS_WORD.substring(47 + 1 - 1);
    }
    public void R000_GET_HQBA_PARM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCPHQBP);
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, BPRPRMT);
        DDCPHQBP.KEY.TYP = "PDD24";
        DDCPHQBP.KEY.CD = "HQBA";
        BPRPRMT.KEY.TYP = DDCPHQBP.KEY.TYP;
        BPRPRMT.KEY.CD = DDCPHQBP.KEY.CD;
        CEP.TRC(SCCGWA, DDCPHQBP.KEY);
        BPCPRMR.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCPHQBP);
        CEP.TRC(SCCGWA, DDCPHQBP.DATA_TXT.ADP_PERIOD);
        CEP.TRC(SCCGWA, DDCPHQBP.DATA_TXT.AUTO_FLG);
        CEP.TRC(SCCGWA, DDCPHQBP.DATA_TXT.CCY);
        CEP.TRC(SCCGWA, DDCPHQBP.DATA_TXT.MIN_AMT);
        CEP.TRC(SCCGWA, DDCPHQBP.DATA_TXT.CHECK_PERIOD);
        CEP.TRC(SCCGWA, DDCPHQBP.DATA_TXT.CHECK_DAYS);
        CEP.TRC(SCCGWA, DDCPHQBP.DATA_TXT.POST_PERIOD);
        CEP.TRC(SCCGWA, DDCPHQBP.DATA_TXT.POST_DATE);
        CEP.TRC(SCCGWA, DDCPHQBP.DATA_TXT.INT_CAL_COND);
        CEP.TRC(SCCGWA, DDCPHQBP.DATA_TXT.TIER_TYPE);
        CEP.TRC(SCCGWA, DDCPHQBP.DATA_TXT.TIER_IR[1-1].TIER_TOT);
        CEP.TRC(SCCGWA, DDCPHQBP.DATA_TXT.TIER_IR[1-1].TIER_RBAS);
        CEP.TRC(SCCGWA, DDCPHQBP.DATA_TXT.TIER_IR[1-1].TIER_RCD);
        CEP.TRC(SCCGWA, DDCPHQBP.DATA_TXT.TIER_IR[2-1].TIER_TOT);
        CEP.TRC(SCCGWA, DDCPHQBP.DATA_TXT.TIER_IR[2-1].TIER_RBAS);
        CEP.TRC(SCCGWA, DDCPHQBP.DATA_TXT.TIER_IR[2-1].TIER_RCD);
        CEP.TRC(SCCGWA, DDCPHQBP.DATA_TXT.TIER_IR[3-1].TIER_TOT);
        CEP.TRC(SCCGWA, DDCPHQBP.DATA_TXT.TIER_IR[3-1].TIER_RBAS);
        CEP.TRC(SCCGWA, DDCPHQBP.DATA_TXT.TIER_IR[3-1].TIER_RCD);
    }
    public void R000_GET_HQBA_RATE() throws IOException,SQLException,Exception {
        for (WS_HQB_I = 1; WS_HQB_I <= 5 
            && DDCPHQBP.DATA_TXT.TIER_IR[WS_HQB_I-1].TIER_TOT != 0; WS_HQB_I += 1) {
            if (DDCPHQBP.DATA_TXT.TIER_IR[WS_HQB_I-1].FIX_RATE != 0) {
                WS_HQB_DATA.WS_HQB_RATE_INF[WS_HQB_I-1].WS_HQB_RATE = DDCPHQBP.DATA_TXT.TIER_IR[WS_HQB_I-1].FIX_RATE;
            } else {
                IBS.init(SCCGWA, BPCCINTI);
                BPCCINTI.FUNC = 'I';
                BPCCINTI.BASE_INFO.DT = DDRMSTR.KEY.ADP_STRDATE;
                BPCCINTI.BASE_INFO.BR = DDRCCY.OWNER_BRDP;
                BPCCINTI.BASE_INFO.BASE_TYP = DDCPHQBP.DATA_TXT.TIER_IR[WS_HQB_I-1].TIER_RBAS;
                BPCCINTI.BASE_INFO.TENOR = DDCPHQBP.DATA_TXT.TIER_IR[WS_HQB_I-1].TIER_RCD;
                BPCCINTI.BASE_INFO.CCY = DDCPHQBP.DATA_TXT.CCY;
                if (BPCCINTI.BASE_INFO.TENOR.trim().length() == 0) {
                    BPCCINTI.BASE_INFO.TENOR = K_TENOR;
                }
                if (BPCCINTI.BASE_INFO.BASE_TYP.trim().length() > 0) {
                    S000_CALL_BPZCINTI();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.OWN_RATE);
                WS_HQB_DATA.WS_HQB_RATE_INF[WS_HQB_I-1].WS_HQB_RATE = BPCCINTI.BASE_INFO.OWN_RATE;
                if (DDCPHQBP.DATA_TXT.TIER_IR[WS_HQB_I-1].TIER_SPR != 0) {
                    WS_HQB_DATA.WS_HQB_RATE_INF[WS_HQB_I-1].WS_HQB_RATE = WS_HQB_DATA.WS_HQB_RATE_INF[WS_HQB_I-1].WS_HQB_RATE + DDCPHQBP.DATA_TXT.TIER_IR[WS_HQB_I-1].TIER_SPR;
                }
                if (DDCPHQBP.DATA_TXT.TIER_IR[WS_HQB_I-1].TIER_SPR == 0 
                    && DDCPHQBP.DATA_TXT.TIER_IR[WS_HQB_I-1].TIER_PCT != 0) {
                    WS_HQB_DATA.WS_HQB_RATE_INF[WS_HQB_I-1].WS_HQB_RATE = WS_HQB_DATA.WS_HQB_RATE_INF[WS_HQB_I-1].WS_HQB_RATE + WS_HQB_DATA.WS_HQB_RATE_INF[WS_HQB_I-1].WS_HQB_RATE * DDCPHQBP.DATA_TXT.TIER_IR[WS_HQB_I-1].TIER_SPR / 100;
                }
            }
        }
    }
    public void R000_HQBA_INT_PROC() throws IOException,SQLException,Exception {
        DDRINTB.DEP_TOT1 = DDRINTB.TOT_ACCU;
        if (DDRINTB.TOT_ACCU <= DDCPHQBP.DATA_TXT.TIER_IR[1-1].TIER_TOT) {
            DDRINTB.TIR_RATE1 = WS_HQB_DATA.WS_HQB_RATE_INF[1-1].WS_HQB_RATE;
        }
        if (DDRINTB.TOT_ACCU > DDCPHQBP.DATA_TXT.TIER_IR[1-1].TIER_TOT 
            && DDRINTB.TOT_ACCU <= DDCPHQBP.DATA_TXT.TIER_IR[2-1].TIER_TOT) {
            DDRINTB.TIR_RATE1 = WS_HQB_DATA.WS_HQB_RATE_INF[2-1].WS_HQB_RATE;
        }
        if (DDRINTB.TOT_ACCU > DDCPHQBP.DATA_TXT.TIER_IR[2-1].TIER_TOT) {
            DDRINTB.TIR_RATE1 = WS_HQB_DATA.WS_HQB_RATE_INF[3-1].WS_HQB_RATE;
        }
        DDRINTB.DEP_ADJ_INT = DDRINTB.DEP_ADJ_INT - DDRINTB.PAY_DEP_INT;
        DDRINTB.PAY_DEP_INT = 0;
        DDRINTB.TOT_ACCU = 0;
    }
    public void B050_HQB_INT_DIV_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRACCU);
        DDRACCU.KEY.AC = DDRINTB.KEY.AC;
        DDRACCU.KEY.END_DATE = DDRINTB.END_TOT_DATE;
        DDRACCU.KEY.CLS = 'R';
        DDRACCU.CAL_INT_MTH = '3';
        DDRACCU.INT_STS = 'N';
        DDRACCU.TIR_TYPE = DDRINTB.TIR_TYPE;
        DDRACCU.AGSP_FLG = DDRINTB.AGSP_FLG;
        if (DDRINTB.DEP_TOT1 != 0) {
            DDRACCU.KEY.TYPE = '1';
            DDRACCU.KEY.STR_DATE = DDRINTB.STRT_TOT_DATE;
            DDRACCU.TIR_AMT = DDRINTB.TIR_AMT1;
            DDRACCU.TOT = DDRINTB.DEP_TOT1;
            DDRACCU.RATE = DDRINTB.TIR_RATE1;
            DDRACCU.INT = DDRACCU.TOT * DDRACCU.RATE / 360 / 100;
            DDRACCU.AFT_ADJ_INT = DDRACCU.INT;
            T000_WRITE_DDTACCU();
            if (pgmRtn) return;
        }
        DDRINTB.DEP_TOT1 = 0;
        DDRINTB.TIR_RATE1 = 0;
        DDRINTB.STRT_TOT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRINTB.END_TOT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRINTB.LST_TOT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_DDTINTB();
        if (pgmRtn) return;
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 47 - 1) + "1" + DDRCCY.STS_WORD.substring(47 + 1 - 1);
    }
    public void B050_ADD_DDTMSTR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMSTR);
        DDRMSTR.ADP_CRE_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRMSTR.ADP_STS = 'O';
        DDRMSTR.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMSTR.ADP_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMSTR.KEY.ADP_STRDATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMSTR.ADP_EXPDATE = 20991231;
        DDRMSTR.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRMSTR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMSTR.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        if (DDCSMHQB.FLG.equalsIgnoreCase("J")) {
            DDRMSTR.KEY.ADP_TYPE = "4";
        } else if (DDCSMHQB.FLG.equalsIgnoreCase("A")) {
            DDRMSTR.KEY.ADP_TYPE = "5";
        } else if (DDCSMHQB.FLG.equalsIgnoreCase("B")) {
            DDRMSTR.KEY.ADP_TYPE = "6";
        } else if (DDCSMHQB.FLG.equalsIgnoreCase("1")) {
            DDRMSTR.KEY.ADP_TYPE = "A";
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = SCCGWA.COMM_AREA.AC_DATE;
            SCCCLDT.MTHS = 36;
            SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
            SCSSCLDT1.MP(SCCGWA, SCCCLDT);
            if (SCCCLDT.RC != 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_SYS_ERR);
            }
            CEP.TRC(SCCGWA, SCCCLDT.DATE2);
            DDRMSTR.ADP_EXPDATE = SCCCLDT.DATE2;
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = SCCGWA.COMM_AREA.AC_DATE;
            SCCCLDT.DAYS = 90;
            SCSSCLDT SCSSCLDT2 = new SCSSCLDT();
            SCSSCLDT2.MP(SCCGWA, SCCCLDT);
            if (SCCCLDT.RC != 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_SYS_ERR);
            }
            CEP.TRC(SCCGWA, SCCCLDT.DATE2);
            WS_EXP_DATE = SCCCLDT.DATE2;
        } else if (DDCSMHQB.FLG.equalsIgnoreCase("2")) {
            DDRMSTR.KEY.ADP_TYPE = "B";
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = SCCGWA.COMM_AREA.AC_DATE;
            SCCCLDT.MTHS = 36;
            SCSSCLDT SCSSCLDT3 = new SCSSCLDT();
            SCSSCLDT3.MP(SCCGWA, SCCCLDT);
            if (SCCCLDT.RC != 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_SYS_ERR);
            }
            CEP.TRC(SCCGWA, SCCCLDT.DATE2);
            DDRMSTR.ADP_EXPDATE = SCCCLDT.DATE2;
            WS_EXP_DATE = SCCCLDT.DATE2;
        }
        DDRMSTR.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRMSTR.ADP_NO = " ";
        T000_WRITE_DDTMSTR();
        if (pgmRtn) return;
    }
    public void B050_UPD_DDTMSTR() throws IOException,SQLException,Exception {
        DDRMSTR.ADP_CRE_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRMSTR.ADP_STS = 'O';
        DDRMSTR.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMSTR.ADP_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMSTR.KEY.ADP_STRDATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMSTR.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRMSTR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMSTR.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        if (DDCSMHQB.FLG.equalsIgnoreCase("A")) {
            DDRMSTR.KEY.ADP_TYPE = "5";
        } else {
            if (DDCSMHQB.FLG.equalsIgnoreCase("B")) {
                DDRMSTR.KEY.ADP_TYPE = "6";
            }
        }
        if (DDCSMHQB.FLG.equalsIgnoreCase("J")) {
            DDRMSTR.KEY.ADP_TYPE = "4";
        }
        DDRMSTR.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRMSTR.ADP_NO = " ";
        T000_REWRITE_DDTMSTR();
        if (pgmRtn) return;
    }
    public void B035_GEN_CI_AGT_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICMAGT);
        CICMAGT.FUNC = 'A';
        CICMAGT.DATA.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        WS_CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        CICMAGT.DATA.AGT_TYP = "IBS008";
        CICMAGT.DATA.AGT_STS = 'N';
        CICMAGT.DATA.ENTY_TYP = '1';
        CICMAGT.DATA.ENTY_NO = DDCSMHQB.CUS_AC;
        CICMAGT.DATA.AGT_LVL = 'A';
        CEP.TRC(SCCGWA, CICMAGT.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICMAGT.DATA.AGT_TYP);
        CEP.TRC(SCCGWA, CICMAGT.DATA.AGT_STS);
        CEP.TRC(SCCGWA, CICMAGT.DATA.ENTY_TYP);
        CEP.TRC(SCCGWA, CICMAGT.DATA.ENTY_NO);
        CEP.TRC(SCCGWA, CICMAGT.DATA.AGT_LVL);
        S000_CALL_CIZMAGT();
        if (pgmRtn) return;
    }
    public void B040_HLD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUHLD);
        DCCUHLD.DATA.AC = DDCSMHQB.CUS_AC;
        DCCUHLD.DATA.HLD_TYP = '2';
        DCCUHLD.DATA.SPR_TYP = '2';
        DCCUHLD.DATA.CCY = "156";
        DCCUHLD.DATA.CCY_TYP = '1';
        DCCUHLD.DATA.AMT = 100000;
        DCCUHLD.DATA.RSN = "活期宝签�?";
        DCCUHLD.DATA.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCCUHLD.DATA.EXP_DT = WS_EXP_DATE;
        DCCUHLD.DATA.HLD_CLS = 'B';
        S000_CALL_DCZUHLD();
        if (pgmRtn) return;
    }
    public void B040_RHLD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHLD);
        DDRHLD.CARD_NO = DDCSMHQB.CUS_AC;
        DDRHLD.HLD_STS = 'N';
        DDRHLD.HLD_CLS = 'B';
        T000_READ_DDTHLD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRHLD.KEY.HLD_NO);
        IBS.init(SCCGWA, DCCURHLD);
        DCCURHLD.DATA.HLD_NO = DDRHLD.KEY.HLD_NO;
        DCCURHLD.DATA.RHLD_TYP = '1';
        DCCURHLD.DATA.RSN = "活期宝解�?";
        S000_CALL_DCZURHLD();
        if (pgmRtn) return;
    }
    public void B035_CAN_CI_AGT_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICMAGT);
        CICMAGT.FUNC = 'D';
        CICMAGT.DATA.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        WS_CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        CICMAGT.DATA.AGT_TYP = "IBS008";
        CICMAGT.DATA.AGT_STS = 'N';
        CICMAGT.DATA.ENTY_TYP = '1';
        CICMAGT.DATA.ENTY_NO = DDCSMHQB.CUS_AC;
        CICMAGT.DATA.AGT_LVL = 'A';
        S000_CALL_CIZMAGT();
        if (pgmRtn) return;
    }
    public void B050_DEL_MSTR_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMSTR);
        DDRMSTR.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRMSTR.ADP_STS = 'O';
        if (DDCSMHQB.FLG.equalsIgnoreCase("J")) {
            DDRMSTR.KEY.ADP_TYPE = "4";
        } else if (DDCSMHQB.FLG.equalsIgnoreCase("A")) {
            DDRMSTR.KEY.ADP_TYPE = "5";
        } else if (DDCSMHQB.FLG.equalsIgnoreCase("B")) {
            DDRMSTR.KEY.ADP_TYPE = "6";
        } else if (DDCSMHQB.FLG.equalsIgnoreCase("1")) {
            DDRMSTR.KEY.ADP_TYPE = "A";
        } else if (DDCSMHQB.FLG.equalsIgnoreCase("2")) {
            DDRMSTR.KEY.ADP_TYPE = "B";
        }
        T000_READ_DDTMSTR_FIRST2();
        if (pgmRtn) return;
        if (WS_MSTR_FLG == 'N') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MSTR_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        DDRMSTR.ADP_EXPDATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMSTR.ADP_STS = 'F';
        DDRMSTR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMSTR.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DDTMSTR();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDRINTB);
        DDRINTB.KEY.AC = DDRCCY.KEY.AC;
        DDRINTB.KEY.TYPE = 'D';
        T000_READ_UPD_DDTINTB();
        if (pgmRtn) return;
        if (DDRMSTR.KEY.ADP_STRDATE > SCCGWA.COMM_AREA.AC_DATE) {
            B070_CHANGE_PROD();
            if (pgmRtn) return;
        }
        if (WS_INTB_FLG == 'F' 
            && (DDRMSTR.KEY.ADP_TYPE.equalsIgnoreCase("5") 
            || DDRMSTR.KEY.ADP_TYPE.equalsIgnoreCase("A"))) {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = DDRMSTR.KEY.ADP_STRDATE;
            SCCCLDT.DATE2 = SCCGWA.COMM_AREA.AC_DATE;
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            WS_CLDT_DAYS = (short) SCCCLDT.DAYS;
            if (WS_CLDT_DAYS >= 90) {
                R000_GET_HQBA_PARM();
                if (pgmRtn) return;
                R000_GET_HQBA_RATE();
                if (pgmRtn) return;
                R000_HQBA_INT_PROC();
                if (pgmRtn) return;
                B050_HQB_INT_DIV_PROC();
                if (pgmRtn) return;
            }
        }
        R000_CLEAR_INTB_INF();
        if (pgmRtn) return;
    }
    public void B050_INQ_MSTR_REC_1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMSTR);
        DDRMSTR.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRMSTR.ADP_STS = DDCSMHQB.STS;
        if (DDCSMHQB.STRDT == 0 
            && DDCSMHQB.EXPDT == 0) {
            T000_STARTBR_DDTMSTR_01();
            if (pgmRtn) return;
            T000_READNEXT_DDTMSTR();
            if (pgmRtn) return;
            while (WS_MSTR_FLG != 'N' 
                && SCCMPAG.FUNC != 'E') {
                B050_GET_ACAC_INFO();
                if (pgmRtn) return;
                B050_OUTPUT_MPAGE_DETAIL();
                if (pgmRtn) return;
                T000_READNEXT_DDTMSTR();
                if (pgmRtn) return;
            }
            T000_ENDBR_DDTMSTR();
            if (pgmRtn) return;
        } else {
            WS_MHQB_STRDT = DDCSMHQB.STRDT;
            WS_MHQB_EXPDT = DDCSMHQB.EXPDT;
            T000_STARTBR_DDTMSTR_02();
            if (pgmRtn) return;
            T000_READNEXT_DDTMSTR();
            if (pgmRtn) return;
            while (WS_MSTR_FLG != 'N' 
                && SCCMPAG.FUNC != 'E') {
                B050_GET_ACAC_INFO();
                if (pgmRtn) return;
                B050_OUTPUT_MPAGE_DETAIL();
                if (pgmRtn) return;
                T000_READNEXT_DDTMSTR();
                if (pgmRtn) return;
            }
            T000_ENDBR_DDTMSTR();
            if (pgmRtn) return;
        }
    }
    public void B050_INQ_MSTR_REC_2() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMSTR);
        DDRMSTR.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        if (DDCSMHQB.STRDT == 0 
            && DDCSMHQB.EXPDT == 0) {
            T000_STARTBR_DDTMSTR_03();
            if (pgmRtn) return;
            T000_READNEXT_DDTMSTR();
            if (pgmRtn) return;
            while (WS_MSTR_FLG != 'N' 
                && SCCMPAG.FUNC != 'E') {
                B050_GET_ACAC_INFO();
                if (pgmRtn) return;
                B050_OUTPUT_MPAGE_DETAIL();
                if (pgmRtn) return;
                T000_READNEXT_DDTMSTR();
                if (pgmRtn) return;
            }
            T000_ENDBR_DDTMSTR();
            if (pgmRtn) return;
        } else {
            WS_MHQB_STRDT = DDCSMHQB.STRDT;
            WS_MHQB_EXPDT = DDCSMHQB.EXPDT;
            T000_STARTBR_DDTMSTR_04();
            if (pgmRtn) return;
            T000_READNEXT_DDTMSTR();
            if (pgmRtn) return;
            while (WS_MSTR_FLG != 'N' 
                && SCCMPAG.FUNC != 'E') {
                B050_GET_ACAC_INFO();
                if (pgmRtn) return;
                B050_OUTPUT_MPAGE_DETAIL();
                if (pgmRtn) return;
                T000_READNEXT_DDTMSTR();
                if (pgmRtn) return;
            }
            T000_ENDBR_DDTMSTR();
            if (pgmRtn) return;
        }
    }
    public void B050_INQ_MSTR_REC_3() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMSTR);
        if (DDCSMHQB.STS != 'Z') {
            WS_Q_ADP_STS = DDCSMHQB.STS;
        } else {
            WS_Q_ADP_STS = ' ';
        }
        WS_MHQB_STRDT = DDCSMHQB.STRDT;
        WS_MHQB_EXPDT = DDCSMHQB.EXPDT;
        T000_STARTBR_DDTMSTR_05();
        if (pgmRtn) return;
        T000_READNEXT_DDTMSTR();
        if (pgmRtn) return;
        while (WS_MSTR_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            B050_GET_ACAC_INFO();
            if (pgmRtn) return;
            B050_OUTPUT_MPAGE_DETAIL();
            if (pgmRtn) return;
            T000_READNEXT_DDTMSTR();
            if (pgmRtn) return;
        }
        T000_ENDBR_DDTMSTR();
        if (pgmRtn) return;
    }
    public void B050_INQ_GET_AC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICIAGT);
        CICIAGT.DATA.CI_NO = DDCSMHQB.CI_NO;
        CICIAGT.DATA.AGT_TYP = "IBS008";
        CICIAGT.FUNC = 'I';
        S000_CALL_CIZIAGT();
        if (pgmRtn) return;
        for (WS_I = 1; CICIAGT.DATA.AREA.ENTY_INF[WS_I-1].ENTY_NO.trim().length() != 0 
            && WS_I < 200; WS_I += 1) {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'C';
            CICQACAC.DATA.AGR_NO = CICIAGT.DATA.AREA.ENTY_INF[WS_I-1].ENTY_NO;
            CICQACAC.DATA.CCY_ACAC = "156";
            CICQACAC.DATA.CR_FLG = '1';
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            if (DDCSMHQB.STS != 'Z') {
                B050_INQ_MSTR_REC_1();
                if (pgmRtn) return;
            } else {
                B050_INQ_MSTR_REC_2();
                if (pgmRtn) return;
            }
        }
    }
    public void B050_BEGIN_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 1660;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B050_OUTPUT_MPAGE_DETAIL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_INF);
        WS_OUT_INF.WS_FUNC = DDCSMHQB.FUNC;
        WS_OUT_INF.WS_ADP_NO = DDRMSTR.ADP_NO;
        WS_OUT_INF.WS_CUS_AC = DDCSMHQB.CUS_AC;
        WS_OUT_INF.WS_CCY = DDCSMHQB.CCY;
        WS_OUT_INF.WS_CCY_TYP = DDCSMHQB.CCY_TYP;
        WS_OUT_INF.WS_FLG = DDRMSTR.KEY.ADP_TYPE;
        WS_OUT_INF.WS_ADP_STRDATE = DDRMSTR.KEY.ADP_STRDATE;
        WS_OUT_INF.WS_ADP_EXPDATE = DDRMSTR.ADP_EXPDATE;
        WS_OUT_INF.WS_ADP_STS = DDRMSTR.ADP_STS;
        WS_OUT_INF.WS_AC_CNM = CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM;
        CEP.TRC(SCCGWA, "FARMANHAO");
        CEP.TRC(SCCGWA, WS_OUT_INF.WS_FUNC);
        CEP.TRC(SCCGWA, WS_OUT_INF.WS_ADP_NO);
        CEP.TRC(SCCGWA, WS_OUT_INF.WS_CUS_AC);
        CEP.TRC(SCCGWA, WS_OUT_INF.WS_CCY);
        CEP.TRC(SCCGWA, WS_OUT_INF.WS_FLG);
        CEP.TRC(SCCGWA, WS_OUT_INF.WS_ADP_STRDATE);
        CEP.TRC(SCCGWA, WS_OUT_INF.WS_ADP_EXPDATE);
        CEP.TRC(SCCGWA, WS_OUT_INF.WS_ADP_STS);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUT_INF);
        SCCMPAG.DATA_LEN = 1660;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B070_CHANGE_PROD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "CAAC";
        BPCPOEWA.DATA.PROD_CODE = DDRCCY.PROD_CODE;
        BPCPOEWA.DATA.EVENT_CODE = "CD1";
        BPCPOEWA.DATA.BR_OLD = DDRCCY.OWNER_BRDP;
        BPCPOEWA.DATA.BR_NEW = DDRCCY.OWNER_BRDP;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = DDRCCY.CCY;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = DDRCCY.LAST_DAYEND_BAL;
        BPCPOEWA.DATA.AMT_INFO[8-1].AMT = DDRCCY.LAST_DAYEND_BAL;
        BPCPOEWA.DATA.AMT_INFO[9-1].AMT = DDRINTB.DEP_ACCU_INT;
        BPCPOEWA.DATA.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        BPCPOEWA.DATA.AC_NO = DDRCCY.KEY.AC;
        BPCPOEWA.DATA.DESC = "活期宝解�?";
        if (BPCPOEWA.DATA.AMT_INFO[1-1].AMT != 0 
            || BPCPOEWA.DATA.AMT_INFO[8-1].AMT != 0 
            || BPCPOEWA.DATA.AMT_INFO[9-1].AMT != 0) {
            S000_CALL_BPZPOEWA();
            if (pgmRtn) return;
        }
    }
    public void R000_CLEAR_INTB_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRINTB);
        DDRINTB.KEY.AC = DDRCCY.KEY.AC;
        DDRINTB.KEY.TYPE = 'D';
        T000_READ_UPD_DDTINTB();
        if (pgmRtn) return;
        if (WS_INTB_FLG == 'F') {
            DDRINTB.PAY_DEP_INT = 0;
            DDRINTB.TOT_ACCU = 0;
            T000_REWRITE_DDTINTB();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPOEWA.RC);
        }
    }
    public void T000_WRITE_DDTMSTR() throws IOException,SQLException,Exception {
        DDTMSTR_RD = new DBParm();
        DDTMSTR_RD.TableName = "DDTMSTR";
        DDTMSTR_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DDRMSTR, DDTMSTR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MSTR_REC_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_DDTACCU() throws IOException,SQLException,Exception {
        DDTACCU_RD = new DBParm();
        DDTACCU_RD.TableName = "DDTACCU";
        IBS.WRITE(SCCGWA, DDRACCU, DDTACCU_RD);
    }
    public void T000_DELETE_DDTMSTR() throws IOException,SQLException,Exception {
        DDTMSTR_RD = new DBParm();
        DDTMSTR_RD.TableName = "DDTMSTR";
        IBS.DELETE(SCCGWA, DDRMSTR, DDTMSTR_RD);
    }
    public void T000_REWRITE_DDTMSTR() throws IOException,SQLException,Exception {
        DDTMSTR_RD = new DBParm();
        DDTMSTR_RD.TableName = "DDTMSTR";
        IBS.REWRITE(SCCGWA, DDRMSTR, DDTMSTR_RD);
    }
    public void T000_REWRITE_DDTINTB() throws IOException,SQLException,Exception {
        DDRINTB.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRINTB.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTINTB_RD = new DBParm();
        DDTINTB_RD.TableName = "DDTINTB";
        IBS.REWRITE(SCCGWA, DDRINTB, DDTINTB_RD);
    }
    public void T000_READ_UPD_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTHLD() throws IOException,SQLException,Exception {
        DDTHLD_RD = new DBParm();
        DDTHLD_RD.TableName = "DDTHLD";
        DDTHLD_RD.where = "CARD_NO = :DDRHLD.CARD_NO "
            + "AND HLD_STS = :DDRHLD.HLD_STS "
            + "AND HLD_CLS = :DDRHLD.HLD_CLS";
        IBS.READ(SCCGWA, DDRHLD, this, DDTHLD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_HLD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.REWRITE(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void T000_READ_DDTMSTR_FIRST() throws IOException,SQLException,Exception {
        DDTMSTR_RD = new DBParm();
        DDTMSTR_RD.TableName = "DDTMSTR";
        DDTMSTR_RD.where = "AC = :DDRMSTR.KEY.AC "
            + "AND ADP_STS = :DDRMSTR.ADP_STS "
            + "AND ADP_TYPE IN ( '4' , '5' , '6' , 'A' , 'B' )";
        DDTMSTR_RD.fst = true;
        DDTMSTR_RD.order = "ADP_STRDATE";
        IBS.READ(SCCGWA, DDRMSTR, this, DDTMSTR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_MSTR_FLG = 'F';
        } else {
            WS_MSTR_FLG = 'N';
        }
    }
    public void T000_READ_DDTMSTR_FIRST2() throws IOException,SQLException,Exception {
        DDTMSTR_RD = new DBParm();
        DDTMSTR_RD.TableName = "DDTMSTR";
        DDTMSTR_RD.where = "AC = :DDRMSTR.KEY.AC "
            + "AND ADP_STS = :DDRMSTR.ADP_STS "
            + "AND ADP_TYPE = :DDRMSTR.KEY.ADP_TYPE";
        DDTMSTR_RD.upd = true;
        IBS.READ(SCCGWA, DDRMSTR, this, DDTMSTR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_MSTR_FLG = 'F';
        } else {
            WS_MSTR_FLG = 'N';
        }
    }
    public void T000_READ_UPD_DDTMSTR() throws IOException,SQLException,Exception {
        DDTMSTR_RD = new DBParm();
        DDTMSTR_RD.TableName = "DDTMSTR";
        DDTMSTR_RD.where = "AC = :DDRMSTR.KEY.AC "
            + "AND ADP_STS = :DDRMSTR.ADP_STS";
        IBS.READ(SCCGWA, DDRMSTR, this, DDTMSTR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_MSTR_FLG = 'F';
        } else {
            WS_MSTR_FLG = 'N';
        }
    }
    public void T000_READ_UPD_DDTINTB() throws IOException,SQLException,Exception {
        DDTINTB_RD = new DBParm();
        DDTINTB_RD.TableName = "DDTINTB";
        DDTINTB_RD.upd = true;
        IBS.READ(SCCGWA, DDRINTB, DDTINTB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_INTB_FLG = 'F';
        } else {
            WS_INTB_FLG = 'N';
        }
    }
    public void T000_READ_UPD_DDTMSTR_01() throws IOException,SQLException,Exception {
        DDTMSTR_RD = new DBParm();
        DDTMSTR_RD.TableName = "DDTMSTR";
        DDTMSTR_RD.upd = true;
        IBS.READ(SCCGWA, DDRMSTR, DDTMSTR_RD);
    }
    public void T000_STARTBR_DDTMSTR_01() throws IOException,SQLException,Exception {
        DDTMSTR_BR.rp = new DBParm();
        DDTMSTR_BR.rp.TableName = "DDTMSTR";
        DDTMSTR_BR.rp.where = "AC = :DDRMSTR.KEY.AC "
            + "AND ADP_STS = :DDRMSTR.ADP_STS";
        DDTMSTR_BR.rp.order = "AC,ADP_STRDATE";
        IBS.STARTBR(SCCGWA, DDRMSTR, this, DDTMSTR_BR);
    }
    public void T000_STARTBR_DDTMSTR_02() throws IOException,SQLException,Exception {
        DDTMSTR_BR.rp = new DBParm();
        DDTMSTR_BR.rp.TableName = "DDTMSTR";
        DDTMSTR_BR.rp.where = "AC = :DDRMSTR.KEY.AC "
            + "AND ADP_STS = :DDRMSTR.ADP_STS "
            + "AND ADP_STRDATE BETWEEN :WS_MHQB_STRDT "
            + "AND :WS_MHQB_EXPDT";
        DDTMSTR_BR.rp.order = "AC,ADP_STRDATE";
        IBS.STARTBR(SCCGWA, DDRMSTR, this, DDTMSTR_BR);
    }
    public void T000_STARTBR_DDTMSTR_03() throws IOException,SQLException,Exception {
        DDTMSTR_BR.rp = new DBParm();
        DDTMSTR_BR.rp.TableName = "DDTMSTR";
        DDTMSTR_BR.rp.where = "AC = :DDRMSTR.KEY.AC";
        DDTMSTR_BR.rp.order = "AC,ADP_STRDATE";
        IBS.STARTBR(SCCGWA, DDRMSTR, this, DDTMSTR_BR);
    }
    public void T000_STARTBR_DDTMSTR_04() throws IOException,SQLException,Exception {
        DDTMSTR_BR.rp = new DBParm();
        DDTMSTR_BR.rp.TableName = "DDTMSTR";
        DDTMSTR_BR.rp.where = "AC = :DDRMSTR.KEY.AC "
            + "AND ADP_STRDATE BETWEEN :WS_MHQB_STRDT "
            + "AND :WS_MHQB_EXPDT";
        DDTMSTR_BR.rp.order = "AC,ADP_STRDATE";
        IBS.STARTBR(SCCGWA, DDRMSTR, this, DDTMSTR_BR);
    }
    public void T000_STARTBR_DDTMSTR_05() throws IOException,SQLException,Exception {
        DDTMSTR_BR.rp = new DBParm();
        DDTMSTR_BR.rp.TableName = "DDTMSTR";
        DDTMSTR_BR.rp.where = "( ADP_STS = :WS_Q_ADP_STS "
            + "OR :WS_Q_ADP_STS = ' ' ) "
            + "AND ( ADP_TYPE = '4' "
            + "OR ADP_TYPE = '5' "
            + "OR ADP_TYPE = '6' ) "
            + "AND ( ADP_STRDATE >= :WS_MHQB_STRDT "
            + "AND ADP_STRDATE <= :WS_MHQB_EXPDT )";
        DDTMSTR_BR.rp.order = "AC,ADP_STRDATE";
        IBS.STARTBR(SCCGWA, DDRMSTR, this, DDTMSTR_BR);
    }
    public void T000_READNEXT_DDTMSTR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRMSTR, this, DDTMSTR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_MSTR_FLG = 'F';
        } else {
            WS_MSTR_FLG = 'N';
        }
    }
    public void T000_ENDBR_DDTMSTR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTMSTR_BR);
    }
    public void T000_READ_DDTHQBR() throws IOException,SQLException,Exception {
        DDTHQBR_RD = new DBParm();
        DDTHQBR_RD.TableName = "DDTHQBR";
        IBS.READ(SCCGWA, DDRHQBR, DDTHQBR_RD);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
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
    public void S000_CALL_CIZMAGT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-AGT", CICMAGT);
        if (CICMAGT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICMAGT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUHLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-HLD", DCCUHLD, true);
    }
    public void S000_CALL_DCZURHLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-RHLD", DCCURHLD, true);
    }
    public void S000_LINK_CIZMLS2() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-LST2", CICMLS2, true);
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF, true);
    }
    public void S000_CALL_BPZFCSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-STS-TBL-AUTH", BPCFCSTS);
        if (BPCFCSTS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFCSTS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_UNI_CIZACCU, CICACCU);
        if (CICACCU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_SEND_MASSAGE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCWRMSG);
        SCCWRMSG.DATE = SCCGWA.COMM_AREA.AC_DATE;
        SCCWRMSG.JRNNO = "" + SCCGWA.COMM_AREA.JRN_NO;
        JIBS_tmp_int = SCCWRMSG.JRNNO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) SCCWRMSG.JRNNO = "0" + SCCWRMSG.JRNNO;
        S000_CALL_SCCWRMSG();
        if (pgmRtn) return;
    }
    public void S000_CALL_SCCWRMSG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-SCZWRMSG-MMSG", SCCWRMSG);
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT4 = new SCSSCLDT();
        SCSSCLDT4.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "LINK SCSSCLDT,CLDT-RC=" + SCCCLDT.RC;
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "BPCPRMR");
            CEP.TRC(SCCGWA, BPCPRMR);
        }
    }
    public void S000_CALL_BPZCINTI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-C-INTR-INQ", BPCCINTI);
        if (BPCCINTI.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, BPCCINTI.RC);
            BPCCINTI.BASE_INFO.OWN_RATE = 0;
            CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.OWN_RATE);
        }
    }
    public void S000_CALL_CIZIAGT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-AGT", CICIAGT);
        if (CICIAGT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICIAGT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B050_GET_ACAC_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRMSTR.KEY.AC);
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.ACAC_NO = DDRMSTR.KEY.AC;
        CICQACAC.FUNC = 'A';
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_CR_FLG);
        DDCSMHQB.CUS_AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        DDCSMHQB.CCY = CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC;
        DDCSMHQB.CCY_TYP = CICQACAC.O_DATA.O_ACAC_DATA.O_CR_FLG;
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
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
        CEP.TRC(SCCGWA, WS_ERR_MSG);
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
