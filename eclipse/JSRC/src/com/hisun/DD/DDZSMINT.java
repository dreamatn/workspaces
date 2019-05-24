package com.hisun.DD;

import com.hisun.DC.*;
import com.hisun.CI.*;
import com.hisun.SC.*;
import com.hisun.BP.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSMINT {
    int JIBS_tmp_int;
    BigDecimal bigD;
    DBParm DDTACCU_RD;
    DBParm DCTIAMST_RD;
    DBParm DDTCCY_RD;
    DBParm DDTINTB_RD;
    DBParm DDTMST_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DD645";
    String K_OUTPUT_FMT1 = "DD646";
    String K_STS_TABLE_APP = "DD";
    String K_CHK_STS_TBL = "6405";
    String K_STS_TABLE_APP1 = "DC";
    String K_CHK_STS_TBL1 = "0001";
    String K_HIS_COPYBOOK_NAME = "DDCSMINT";
    String K_HIS_TX_CD = "0116405";
    String K_HIS_REMARKS = "AC POST INT INF";
    String K_HIS_MMO = "S101";
    String WS_ERR_MSG = " ";
    short WS_IDX = 0;
    String WS_AC_NO = " ";
    String WS_STD_AC = " ";
    String WS_TR_AC = " ";
    double WS_TOT_INT1 = 0;
    short WS_CNT = 0;
    short WS_CNT1 = 0;
    String WS_PROD_CODE = " ";
    char WS_M_CINT_FLG = ' ';
    double WS_M_DEP_SPREAD = 0;
    double WS_M_DEP_SPRPCT = 0;
    char WS_OUT_INT_FLG = ' ';
    char WS_MOD_CINT_FLG = ' ';
    String WS_CI_STSW_1 = " ";
    String WS_CI_STSW_2 = " ";
    String WS_AC_STSW_1 = " ";
    String WS_AC_STSW_2 = " ";
    String WS_MINT_AC = " ";
    char WS_AC_TYPE = ' ';
    double WS_EXP_INT = 0;
    String WS_CCY_AC = " ";
    char WS_MOD_FLG = ' ';
    char WS_ACCU_FOUNT_FLG = ' ';
    DDZSMINT_WS_OUT_DATA WS_OUT_DATA = new DDZSMINT_WS_OUT_DATA();
    DDZSMINT_WS_OUT_DATA1 WS_OUT_DATA1 = new DDZSMINT_WS_OUT_DATA1();
    DDZSMINT_WS_INPUT_INF WS_INPUT_INF = new DDZSMINT_WS_INPUT_INF();
    DDZSMINT_WS_DATA_OUT1 WS_DATA_OUT1 = new DDZSMINT_WS_DATA_OUT1();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    CICACCU CICCACCU = new CICACCU();
    DDCUPINT DDCUPINT = new DDCUPINT();
    SCCMSG SCCMSG = new SCCMSG();
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    DDRINTB DDRINTB = new DDRINTB();
    DDRACCU DDRACCU = new DDRACCU();
    BPCQCCY BPCQCCY = new BPCQCCY();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    DDCSMINT DDCSMINTO = new DDCSMINT();
    DDCSMINT DDCSMINTN = new DDCSMINT();
    DCRIAMST DCRIAMST = new DCRIAMST();
    DCCPACTY DCCPACTY = new DCCPACTY();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    BPCPGDIN BPCPGDIN = new BPCPGDIN();
    DDCOATAC DDCOATAC = new DDCOATAC();
    DDCSCINM DDCSCINM = new DDCSCINM();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCCLDT SCCCLDT = new SCCCLDT();
    CICQACAC CICQACAC = new CICQACAC();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    DDCSMFLG DDCSMFLG;
    DDCSMINT DDCSMINT;
    public void MP(SCCGWA SCCGWA, DDCSMINT DDCSMINT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSMINT = DDCSMINT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSMINT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        WS_MOD_FLG = 'N';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_MAIN_INPUT_DATA();
        if (pgmRtn) return;
    }
    public void B010_MAIN_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSMINT.FUNC);
        CEP.TRC(SCCGWA, DDCSMINT.AC);
        CEP.TRC(SCCGWA, DDCSMINT.CCY);
        CEP.TRC(SCCGWA, DDCSMINT.CCY_TYP);
        CEP.TRC(SCCGWA, DDCSMINT.CINT_FLG);
        CEP.TRC(SCCGWA, DDCSMINT.AC2);
        B010_CHECK_DATA_PROC();
        if (pgmRtn) return;
        if (DDCSMINT.FUNC == '1') {
            B030_INQ_DATA_PROC();
            if (pgmRtn) return;
        } else if (DDCSMINT.FUNC == '2') {
            B020_MOD_DATA_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DDCSMINT.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        if (DDCSMINT.FUNC == '1') {
            B080_DATA_OUTPUT_PROC();
            if (pgmRtn) return;
        }
        if (DDCSMINT.FUNC == '2') {
            if (DDCSMINT.CINT_FLG == 'Y') {
                B090_DATA_OUTPUT_PROC();
                if (pgmRtn) return;
            }
            if (DDCSMINT.CINT_FLG == 'N') {
                B110_DATA_OUTPUT2();
                if (pgmRtn) return;
            }
        }
        if (DDCSMINT.FUNC == '2') {
            R000_TRANS_NFHIS_NEW();
            if (pgmRtn) return;
            R000_TRANS_NFHIS_OLD();
            if (pgmRtn) return;
            B170_NFIN_TX_HIS_PROC();
            if (pgmRtn) return;
        }
    }
    public void B045_INQ_INT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUPINT);
        DDCUPINT.AC = DDCSMINT.AC;
        DDCUPINT.CCY = DDCSMINT.CCY;
        DDCUPINT.CCY_TYPE = DDCSMINT.CCY_TYP;
        DDCUPINT.TX_TYP = 'I';
        CEP.TRC(SCCGWA, DDCSMINT.CCY);
        CEP.TRC(SCCGWA, DDCSMINT.CCY_TYP);
        S000_CALL_DDZUPINT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCUPINT.INT_TAX);
        CEP.TRC(SCCGWA, DDCUPINT.OD_INT);
        CEP.TRC(SCCGWA, DDCUPINT.DEP_INT);
        CEP.TRC(SCCGWA, DDCUPINT.UOD_INT);
        WS_EXP_INT = DDCUPINT.DEP_INT;
        WS_DATA_OUT1.WS_DP_INT = DDCUPINT.DEP_INT;
    }
    public void B045_POST_INT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUPINT);
        DDCUPINT.AC = WS_STD_AC;
        DDCUPINT.CCY = DDCSMINT.CCY;
        DDCUPINT.CCY_TYPE = DDCSMINT.CCY_TYP;
        DDCUPINT.TX_MMO = K_HIS_MMO;
        DDCUPINT.CARD_NO = DDCSMINT.AC;
        DDCUPINT.TX_TYP = 'O';
        CEP.TRC(SCCGWA, DDCUPINT.CCY);
        CEP.TRC(SCCGWA, DDCUPINT.CCY_TYPE);
        S000_CALL_DDZUPINT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCUPINT.DEP_INT);
        CEP.TRC(SCCGWA, DDCUPINT.OD_INT);
        CEP.TRC(SCCGWA, DDCUPINT.INT_TAX);
        WS_OUT_INT_FLG = 'Y';
    }
    public void B045_INQ_AC_INT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRINTB);
        DDRINTB.KEY.AC = WS_MINT_AC;
        T000_READ_DDTINTB();
        if (pgmRtn) return;
        WS_EXP_INT = DDRINTB.DEP_ACCU_INT + DDRINTB.DEP_ADJ_INT;
        CEP.TRC(SCCGWA, DDRINTB.DEP_ACCU_INT);
        CEP.TRC(SCCGWA, DDRINTB.DEP_ADJ_INT);
        CEP.TRC(SCCGWA, WS_EXP_INT);
    }
    public void B010_CHECK_DATA_PROC() throws IOException,SQLException,Exception {
        if ((DDCSMINT.FUNC != '1' 
            && DDCSMINT.FUNC != '2')) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FUNC_M_ERORR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSMINT.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSMINT.CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSMINT.CCY_TYP == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_TYP_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_AC_NO = " ";
        WS_AC_NO = DDCSMINT.AC;
        B020_GET_ACAC_INFO();
        if (pgmRtn) return;
        if (DDCSMINT.FUNC == '2') {
            WS_INPUT_INF.WS_FUNC = DDCSMINT.FUNC;
            WS_INPUT_INF.WS_AC = DDCSMINT.AC;
            WS_INPUT_INF.WS_CONT_FLG = DDCSMINT.CINT_FLG;
        }
    }
    public void B020_GET_ACAC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.AGR_NO = DDCSMINT.AC;
        CICQACAC.DATA.CCY_ACAC = DDCSMINT.CCY;
        CICQACAC.DATA.CR_FLG = DDCSMINT.CCY_TYP;
        CICQACAC.FUNC = 'C';
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
    }
    public void B030_CHK_AC_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        WS_STD_AC = DDCSMINT.AC;
        DDRMST.KEY.CUS_AC = WS_STD_AC;
        CEP.TRC(SCCGWA, WS_STD_AC);
        T000_READUP_DDTMST();
        if (pgmRtn) return;
        WS_PROD_CODE = DDRMST.PROD_CODE;
        WS_AC_STSW_1 = DDRMST.AC_STS_WORD;
        CEP.TRC(SCCGWA, WS_AC_STSW_1);
        CEP.TRC(SCCGWA, WS_PROD_CODE);
        if (DDRMST.AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRMST.AC_STS == 'M') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.AC_CLOSED_AND_GET_INT);
        }
        if (DDRMST.AC_STS == 'D') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_ALR_DREG_STS);
        }
        B045_CI_INF_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_CI_STSW_1);
        CEP.TRC(SCCGWA, CICCACCU.DATA.CI_TYP);
        if (CICCACCU.DATA.CI_TYP == '2' 
            || CICCACCU.DATA.CI_TYP == '3') {
            IBS.init(SCCGWA, BPCFCSTS);
            BPCFCSTS.AP_MMO = K_STS_TABLE_APP;
            BPCFCSTS.TBL_NO = K_CHK_STS_TBL;
            if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
            JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
            for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
            if (WS_CI_STSW_1 == null) WS_CI_STSW_1 = "";
            JIBS_tmp_int = WS_CI_STSW_1.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) WS_CI_STSW_1 += " ";
            BPCFCSTS.STATUS_WORD = WS_CI_STSW_1 + BPCFCSTS.STATUS_WORD.substring(80);
            if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
            JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
            for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
            if (WS_AC_STSW_1 == null) WS_AC_STSW_1 = "";
            JIBS_tmp_int = WS_AC_STSW_1.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) WS_AC_STSW_1 += " ";
            BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 101 - 1) + WS_AC_STSW_1 + BPCFCSTS.STATUS_WORD.substring(101 + WS_AC_STSW_1.length() - 1);
            CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD);
            CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD);
            S000_CALL_BPZFCSTS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_MUST_UNIT_AC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_DATA_OUT1.WS_PRD_CODE = DDRMST.PROD_CODE;
        B035_INQ_CCY_BAL_PROC();
        if (pgmRtn) return;
    }
    public void B035_CHK_ACZ_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        if (DDCSMINT.AC2.trim().length() > 0 
            && !DDCSMINT.AC2.equalsIgnoreCase(DDCSMINT.AC)) {
            DDRMST.KEY.CUS_AC = DDCSMINT.AC2;
            T000_READUP_DDTMST();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDRMST.KEY.CUS_AC);
        T000_READUP_DDTMST();
        if (pgmRtn) return;
        WS_AC_STSW_2 = DDRMST.AC_STS_WORD;
        WS_AC_TYPE = DDRMST.AC_TYPE;
        CEP.TRC(SCCGWA, WS_AC_STSW_2);
        if (DDRMST.AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRMST.AC_STS == 'M') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.AC_CLOSED_AND_GET_INT);
        }
        if (DDCSMINT.AC2.trim().length() > 0 
            && !DDCSMINT.AC2.equalsIgnoreCase(DDCSMINT.AC)) {
            IBS.init(SCCGWA, CICCACCU);
            CICCACCU.DATA.AGR_NO = DDRMST.KEY.CUS_AC;
            S000_CALL_CIZACCU();
            if (pgmRtn) return;
            WS_CI_STSW_2 = CICCACCU.DATA.CI_STSW;
            CEP.TRC(SCCGWA, WS_CI_STSW_2);
            CEP.TRC(SCCGWA, CICCACCU.DATA.CI_CNM);
            CEP.TRC(SCCGWA, CICCACCU.DATA.CI_TYP);
            IBS.init(SCCGWA, BPCFCSTS);
            BPCFCSTS.AP_MMO = K_STS_TABLE_APP;
            BPCFCSTS.TBL_NO = K_CHK_STS_TBL;
            if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
            JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
            for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
            if (WS_CI_STSW_2 == null) WS_CI_STSW_2 = "";
            JIBS_tmp_int = WS_CI_STSW_2.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) WS_CI_STSW_2 += " ";
            BPCFCSTS.STATUS_WORD = WS_CI_STSW_2 + BPCFCSTS.STATUS_WORD.substring(80);
            if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
            JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
            for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
            if (WS_AC_STSW_2 == null) WS_AC_STSW_2 = "";
            JIBS_tmp_int = WS_AC_STSW_2.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) WS_AC_STSW_2 += " ";
            BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 101 - 1) + WS_AC_STSW_2 + BPCFCSTS.STATUS_WORD.substring(101 + WS_AC_STSW_2.length() - 1);
            CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD);
            CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD);
            S000_CALL_BPZFCSTS();
            if (pgmRtn) return;
        }
        B035_INQ_CCY1_BAL_PROC();
        if (pgmRtn) return;
    }
    public void B035_INQ_CCY_BAL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = DDRMST.KEY.CUS_AC;
        DDRCCY.CCY = DDCSMINT.CCY;
        DDRCCY.CCY_TYPE = DDCSMINT.CCY_TYP;
        CEP.TRC(SCCGWA, DDRCCY.KEY.AC);
        CEP.TRC(SCCGWA, DDRCCY.CCY);
        CEP.TRC(SCCGWA, DDRCCY.CCY_TYPE);
        T000_READUP_DDTCCY();
        if (pgmRtn) return;
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        CEP.TRC(SCCGWA, DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1));
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        CEP.TRC(SCCGWA, DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1));
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1") 
            || DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_ALR_FBIDEN_STS);
        }
        WS_MINT_AC = DDRCCY.KEY.AC;
        CEP.TRC(SCCGWA, WS_MINT_AC);
        WS_DATA_OUT1.WS_BAL = DDRCCY.CURR_BAL;
        WS_OUT_DATA.WS_O_CONT_FLG = DDRCCY.CINT_FLG;
        WS_M_CINT_FLG = DDRCCY.CINT_FLG;
    }
    public void B035_INQ_CCY1_BAL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = DDRMST.KEY.CUS_AC;
        DDRCCY.CCY = DDCSMINT.CCY;
        DDRCCY.CCY_TYPE = DDCSMINT.CCY_TYP;
        CEP.TRC(SCCGWA, DDRCCY.KEY.AC);
        CEP.TRC(SCCGWA, DDRCCY.CCY);
        CEP.TRC(SCCGWA, DDRCCY.CCY_TYPE);
        T000_READUP_DDTCCY();
        if (pgmRtn) return;
        if (DDRCCY.STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_CLEARED;
            CEP.ERR(SCCGWA, WS_ERR_MSG, DDCSMINT.AC2);
        }
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1") 
            || DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_FORBID;
            CEP.ERR(SCCGWA, WS_ERR_MSG, DDCSMINT.AC2);
        }
    }
    public void B080_DATA_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_DATA);
        WS_OUT_DATA.WS_O_FUNC = DDCSMINT.FUNC;
        WS_OUT_DATA.WS_O_AC = WS_INPUT_INF.WS_AC;
        WS_OUT_DATA.WS_O_CCY = DDCSMINT.CCY;
        WS_OUT_DATA.WS_O_CCY_TYP = DDCSMINT.CCY_TYP;
        WS_OUT_DATA.WS_O_BAL = WS_DATA_OUT1.WS_BAL;
        WS_OUT_DATA.WS_O_CONT_FLG = WS_INPUT_INF.WS_CONT_FLG;
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_O_AC);
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_O_CCY);
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_O_CCY_TYP);
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_O_CONT_FLG);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUT_DATA;
        SCCFMT.DATA_LEN = 87;
        IBS.FMT(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, SCCFMT.DATA_LEN);
    }
    public void B090_DATA_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_DATA1);
        WS_OUT_DATA1.WS_O_FUNC1 = DDCSMINT.FUNC;
        WS_OUT_DATA1.WS_O_AC1 = WS_INPUT_INF.WS_AC;
        WS_OUT_DATA1.WS_O_CCY1 = DDCSMINT.CCY;
        WS_OUT_DATA1.WS_O_CCY_TYP1 = DDCSMINT.CCY_TYP;
        WS_OUT_DATA1.WS_O_ACCU_TOT = DDRACCU.TOT;
        WS_OUT_DATA1.WS_O_POST_AC = DDCSMINT.AC2;
        WS_OUT_DATA1.WS_O_O_CINT_FLG1 = WS_OUT_DATA.WS_O_CONT_FLG;
        WS_OUT_DATA1.WS_O_N_CINT_FLG1 = DDCSMINT.CINT_FLG;
        CEP.TRC(SCCGWA, WS_OUT_DATA1.WS_O_FUNC1);
        CEP.TRC(SCCGWA, WS_OUT_DATA1.WS_O_AC1);
        CEP.TRC(SCCGWA, WS_OUT_DATA1.WS_O_CCY1);
        CEP.TRC(SCCGWA, WS_OUT_DATA1.WS_O_CCY_TYP1);
        CEP.TRC(SCCGWA, WS_OUT_DATA1.WS_O_ACCU_TOT);
        CEP.TRC(SCCGWA, WS_OUT_DATA1.WS_O_POST_AC);
        CEP.TRC(SCCGWA, WS_OUT_DATA1.WS_O_O_CINT_FLG1);
        CEP.TRC(SCCGWA, WS_OUT_DATA1.WS_O_N_CINT_FLG1);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUT_DATA1;
        SCCFMT.DATA_LEN = 89;
        IBS.FMT(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, SCCFMT.DATA_LEN);
    }
    public void R000_TRANS_NFHIS_NEW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSMINTN);
        DDCSMINTN.FUNC = DDCSMINT.FUNC;
        DDCSMINTN.AC = DDCSMINT.AC;
        DDCSMINTN.CCY = DDCSMINT.CCY;
        DDCSMINTN.CCY_TYP = DDCSMINT.CCY_TYP;
        DDCSMINTN.AC2 = DDCSMINT.AC2;
        DDCSMINTN.CINT_FLG = DDCSMINT.CINT_FLG;
    }
    public void R000_TRANS_NFHIS_OLD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSMINTO);
        DDCSMINTO.FUNC = DDCSMINT.FUNC;
        DDCSMINTO.AC = DDCSMINT.AC;
        DDCSMINTO.CCY = DDCSMINT.CCY;
        DDCSMINTO.CCY_TYP = DDCSMINT.CCY_TYP;
        DDCSMINTO.AC2 = DDCSMINT.AC2;
        DDCSMINTO.CINT_FLG = WS_M_CINT_FLG;
    }
    public void B050_CHK_CI_TST_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFCSTS);
        BPCFCSTS.AP_MMO = K_STS_TABLE_APP;
        BPCFCSTS.TBL_NO = K_CHK_STS_TBL;
        CEP.TRC(SCCGWA, DDRMST.AC_STS_WORD);
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        if (CICCACCU.DATA.CI_STSW == null) CICCACCU.DATA.CI_STSW = "";
        JIBS_tmp_int = CICCACCU.DATA.CI_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICCACCU.DATA.CI_STSW += " ";
        BPCFCSTS.STATUS_WORD = CICCACCU.DATA.CI_STSW + BPCFCSTS.STATUS_WORD.substring(80);
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 101 - 1) + DDRMST.AC_STS_WORD + BPCFCSTS.STATUS_WORD.substring(101 + DDRMST.AC_STS_WORD.length() - 1);
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD);
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD);
        S000_CALL_BPZFCSTS();
        if (pgmRtn) return;
    }
    public void B030_INQ_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        WS_STD_AC = DDCSMINT.AC;
        DDRMST.KEY.CUS_AC = WS_STD_AC;
        CEP.TRC(SCCGWA, WS_STD_AC);
        T000_READ_DDTMST();
        if (pgmRtn) return;
        WS_INPUT_INF.WS_FUNC = DDCSMINT.FUNC;
        WS_INPUT_INF.WS_AC = DDCSMINT.AC;
        B045_CI_INF_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICCACCU.DATA.CI_TYP);
        if (CICCACCU.DATA.CI_TYP == '2' 
            || CICCACCU.DATA.CI_TYP == '3') {
        } else {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_MUST_UNIT_AC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B035_INQ_CCY_BAL_PROC();
        if (pgmRtn) return;
    }
    public void B110_DATA_OUTPUT2() throws IOException,SQLException,Exception {
        T000_STRBR_DDTACCU();
        if (pgmRtn) return;
        WS_DATA_OUT1.WS_CCY_TYP = DDCSMINT.CCY_TYP;
        WS_DATA_OUT1.WS_STR_DATE = DDRACCU.KEY.STR_DATE;
        WS_DATA_OUT1.WS_END_DATE = DDRACCU.KEY.END_DATE;
        WS_DATA_OUT1.WS_DEP_TOT = DDRACCU.TOT;
        WS_DATA_OUT1.WS_RATE = DDRACCU.RATE;
        WS_TOT_INT1 += WS_DATA_OUT1.WS_DP_INT;
        WS_DATA_OUT1.WS_FUNC2 = DDCSMINT.FUNC;
        WS_DATA_OUT1.WS_O_O_CINT_FLG = WS_OUT_DATA.WS_O_CONT_FLG;
        WS_DATA_OUT1.WS_O_N_CINT_FLG = DDCSMINT.CINT_FLG;
        WS_DATA_OUT1.WS_AC1 = DDCSMINT.AC;
        WS_DATA_OUT1.WS_AC2 = DDCSMINT.AC2;
        WS_DATA_OUT1.WS_CCY = DDCSMINT.CCY;
        WS_DATA_OUT1.WS_TOT_INT = WS_TOT_INT1 * 1;
        bigD = new BigDecimal(WS_DATA_OUT1.WS_TOT_INT);
        WS_DATA_OUT1.WS_TOT_INT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
        CEP.TRC(SCCGWA, WS_DATA_OUT1.WS_TOT_INT);
        CEP.TRC(SCCGWA, WS_DATA_OUT1.WS_DP_INT);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT1;
        SCCFMT.DATA_PTR = WS_DATA_OUT1;
        SCCFMT.DATA_LEN = 184;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_GET_CAL_INT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = DDCSMINT.CCY;
        S00_CALL_BPZQCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCQCCY.DATA.STD_DAYS);
    }
    public void T000_STRBR_DDTACCU() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRACCU);
        DDRACCU.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRACCU.POST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRACCU.POST_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        DDTACCU_RD = new DBParm();
        DDTACCU_RD.TableName = "DDTACCU";
        DDTACCU_RD.where = "AC = :DDRACCU.KEY.AC "
            + "AND POST_DATE = :DDRACCU.POST_DATE";
        DDTACCU_RD.fst = true;
        IBS.READ(SCCGWA, DDRACCU, this, DDTACCU_RD);
    }
    public void S000_CALL_DDZUPINT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-POST-INT", DDCUPINT);
    }
    public void S00_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTIAMST() throws IOException,SQLException,Exception {
        DCTIAMST_RD = new DBParm();
        DCTIAMST_RD.TableName = "DCTIAMST";
        DCTIAMST_RD.col = "VIA_AC,AC_STS,STS_WORD";
        DCTIAMST_RD.upd = true;
        IBS.READ(SCCGWA, DCRIAMST, DCTIAMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_VIA_AC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READUP_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "CUS_AC = :DDRCCY.CUS_AC "
            + "AND CCY = :DDRCCY.CCY "
            + "AND CCY_TYPE = :DDRCCY.CCY_TYPE";
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READUP_DDTCCY_01() throws IOException,SQLException,Exception {
        DDRCCY.KEY.AC = WS_CCY_AC;
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICCACCU);
        CEP.TRC(SCCGWA, CICCACCU.RC.RC_CODE);
        CEP.TRC(SCCGWA, CICCACCU.DATA.CI_NO);
    }
    public void T000_READ_DDTINTB() throws IOException,SQLException,Exception {
        DDTINTB_RD = new DBParm();
        DDTINTB_RD.TableName = "DDTINTB";
        DDTINTB_RD.where = "AC = :DDRINTB.KEY.AC";
        IBS.READ(SCCGWA, DDRINTB, this, DDTINTB_RD);
    }
    public void S000_CALL_BPZFCSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-STS-TBL-AUTH", BPCFCSTS);
        if (BPCFCSTS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFCSTS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B170_NFIN_TX_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.OLD_DAT_PT = DDCSMINTO;
        BPCPNHIS.INFO.NEW_DAT_PT = DDCSMINTN;
        BPCPNHIS.INFO.AC = DDCSMINT.AC;
        BPCPNHIS.INFO.CCY = DDRCCY.CCY;
        BPCPNHIS.INFO.CCY_TYPE = DDRCCY.CCY_TYPE;
        BPCPNHIS.INFO.TX_CD = K_HIS_TX_CD;
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK_NAME;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.TX_TYP_CD = "P148";
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.AC);
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_CD);
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.FMT_ID);
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.JRNNO);
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_RMK);
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B040_GET_PRD_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIQPRD);
        DDCIQPRD.INPUT_DATA.PROD_CODE = WS_PROD_CODE;
        DDCIQPRD.DDVMPRD_PTR = DDVMPRD;
        S000_CALL_DDZIQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDVMPRD.VAL.CAL_DINT_METH);
    }
    public void B020_MOD_DATA_PROC() throws IOException,SQLException,Exception {
        B030_CHK_AC_STS();
        if (pgmRtn) return;
        WS_CCY_AC = DDRCCY.KEY.AC;
        if (DDCSMINT.AC2.trim().length() > 0 
            && !DDCSMINT.AC2.equalsIgnoreCase(DDCSMINT.AC)) {
            B035_CHK_ACZ_STS();
            if (pgmRtn) return;
        }
        B040_GET_PRD_INF_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCSMINT.CINT_FLG);
        CEP.TRC(SCCGWA, WS_M_CINT_FLG);
        if (DDCSMINT.CINT_FLG != WS_M_CINT_FLG) {
            if (DDCSMINT.CINT_FLG == 'Y') {
                if (DDVMPRD.VAL.CAL_DINT_METH == '3') {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_PRD_NINT_ERROR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                    B200_INQ_ACCU_TOT();
                    if (pgmRtn) return;
                    if (DDRACCU.TOT != 0) {
                        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NINT_ERROR;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    } else {
                        T000_READUP_DDTCCY_01();
                        if (pgmRtn) return;
                        DDRCCY.CINT_FLG = DDCSMINT.CINT_FLG;
                        S000_REWRITE_DDTCCY();
                        if (pgmRtn) return;
                    }
                }
            }
            if (DDCSMINT.CINT_FLG == 'N') {
                B045_INQ_INT_PROC();
                if (pgmRtn) return;
                B045_POST_INT_PROC();
                if (pgmRtn) return;
                if (DDCSMINT.AC2.trim().length() > 0 
                    && !DDCSMINT.AC2.equalsIgnoreCase(DDCSMINT.AC)) {
                    if (WS_EXP_INT != 0) {
                        B055_DEP_INT_PROC();
                        if (pgmRtn) return;
                        B055_DRW_INT_PROC();
                        if (pgmRtn) return;
                    }
                }
                T000_READUP_DDTCCY_01();
                if (pgmRtn) return;
                DDRCCY.CINT_FLG = DDCSMINT.CINT_FLG;
                S000_REWRITE_DDTCCY();
                if (pgmRtn) return;
            }
        }
        T000_READUP_DDTCCY_01();
        if (pgmRtn) return;
        if (DDCSMINT.AC2.trim().length() > 0 
            && !DDCSMINT.AC2.equalsIgnoreCase(DDCSMINT.AC)) {
            DDRCCY.POST_AC = DDCSMINT.AC2;
            S000_REWRITE_DDTCCY();
            if (pgmRtn) return;
        }
    }
    public void B200_INQ_ACCU_TOT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRACCU);
        DDRACCU.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRACCU.INT_STS = 'N';
        DDRACCU.KEY.CLS = 'P';
        DDRACCU.KEY.STR_DATE = 20171215;
        DDRACCU.KEY.END_DATE = 20171215;
        DDRACCU.KEY.TYPE = '1';
        CEP.TRC(SCCGWA, DDRACCU.KEY.AC);
        CEP.TRC(SCCGWA, DDRACCU.KEY.CLS);
        CEP.TRC(SCCGWA, DDRACCU.KEY.STR_DATE);
        CEP.TRC(SCCGWA, DDRACCU.KEY.END_DATE);
        T100_READ_DDTACCU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRACCU.TOT);
    }
    public void T100_READ_DDTACCU() throws IOException,SQLException,Exception {
        DDTACCU_RD = new DBParm();
        DDTACCU_RD.TableName = "DDTACCU";
        IBS.READ(SCCGWA, DDRACCU, DDTACCU_RD);
    }
    public void B045_CI_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCACCU);
        CICCACCU.DATA.AGR_NO = WS_STD_AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        WS_CI_STSW_1 = CICCACCU.DATA.CI_STSW;
        CEP.TRC(SCCGWA, CICCACCU.DATA.CI_CNM);
        CEP.TRC(SCCGWA, CICCACCU.DATA.CI_TYP);
    }
    public void B055_DEP_INT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUCRAC);
        DDCUCRAC.TX_TYPE = 'T';
        DDCUCRAC.AC = DDCSMINT.AC2;
        DDCUCRAC.OTHER_AC = WS_STD_AC;
        DDCUCRAC.CCY = DDCSMINT.CCY;
        DDCUCRAC.CCY_TYPE = DDCSMINT.CCY_TYP;
        DDCUCRAC.TX_MMO = "S105";
        DDCUCRAC.TX_AMT = WS_EXP_INT;
        if (WS_AC_TYPE == 'M') {
            DDCUCRAC.GD_WITHDR_FLG = 'Y';
        }
        S000_CALL_DDZUCRAC();
        if (pgmRtn) return;
    }
    public void B055_DRW_INT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUDRAC);
        DDCUDRAC.AC = WS_STD_AC;
        DDCUDRAC.OTHER_AC = DDCSMINT.AC2;
        DDCUDRAC.CCY = DDCSMINT.CCY;
        DDCUDRAC.CCY_TYPE = DDCSMINT.CCY_TYP;
        DDCUDRAC.TX_MMO = "S106";
        DDCUDRAC.TX_AMT = WS_EXP_INT;
        DDCUDRAC.TX_TYPE = 'T';
        CEP.TRC(SCCGWA, DDCUDRAC.AC);
        CEP.TRC(SCCGWA, DDCUDRAC.CCY);
        CEP.TRC(SCCGWA, DDCUDRAC.CCY_TYPE);
        CEP.TRC(SCCGWA, DDCUDRAC.TX_AMT);
        S000_CALL_DDZUDRAC();
        if (pgmRtn) return;
    }
    public void T000_READUP_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.upd = true;
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_REWRITE_DDTCCY() throws IOException,SQLException,Exception {
        DDRCCY.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.REWRITE(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
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
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC);
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
