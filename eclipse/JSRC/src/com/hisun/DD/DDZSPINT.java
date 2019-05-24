package com.hisun.DD;

import com.hisun.DC.*;
import com.hisun.CI.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.BP.*;
import com.hisun.LN.*;
import com.hisun.TD.*;
import com.hisun.GD.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSPINT {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm DDTINTB_RD;
    brParm DDTACCU_BR = new brParm();
    DBParm DDTCCY_RD;
    DBParm DDTDREG_RD;
    brParm DCTIAACR_BR = new brParm();
    DBParm DCTIAACR_RD;
    brParm DDTDDTD_BR = new brParm();
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DD111";
    String K_OUTPUT_FMT1 = "DD112";
    String K_STS_TABLE_APP = "DD";
    String K_STS_TBL_NO = "1844";
    String WS_ERR_MSG = " ";
    char WS_TMP_ACATTR = ' ';
    double WS_DEP_INT = 0;
    double WS_INT_TAX = 0;
    double WS_OD_INT = 0;
    double WS_DEP_INT_END = 0;
    double WS_SPINT_PRIN = 0;
    char WS_CI_TYPE = ' ';
    String WS_CI_NO = " ";
    char WS_AC_TYPE = ' ';
    char WS_RETURN_INFO = ' ';
    double WS_FEE_AMT = 0;
    short WS_IDX = 0;
    double WS_CURR_BAL = 0;
    char WS_ACCU_FLG = ' ';
    DDZSPINT_WS_DATA_OUT1 WS_DATA_OUT1 = new DDZSPINT_WS_DATA_OUT1();
    DDZSPINT_WS_DATA_OUT2 WS_DATA_OUT2 = new DDZSPINT_WS_DATA_OUT2();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DDCUPINT DDCUPINT = new DDCUPINT();
    DDCOPINT DDCOPINT = new DDCOPINT();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDVMRAT DDVMRAT = new DDVMRAT();
    DDCIPSBK DDCIPSBK = new DDCIPSBK();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPCTCALF BPCTCALF = new BPCTCALF();
    CICACCU CICACCU1 = new CICACCU();
    DDCRMST DDCRMST = new DDCRMST();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DCCICLSA DCCICLSA = new DCCICLSA();
    DCCICLSC DCCICLSC = new DCCICLSC();
    BPCPFPDT BPCPFPDT = new BPCPFPDT();
    CICCAGT CICCAGT = new CICCAGT();
    LNCSICOT LNCSICOT = new LNCSICOT();
    TDCUCACK TDCUCACK = new TDCUCACK();
    GDCISTAC GDCISTAC = new GDCISTAC();
    DCCURCAR DCCURCAR = new DCCURCAR();
    BPCQCCY BPCQCCY = new BPCQCCY();
    CICQACAC CICQACAC = new CICQACAC();
    CICCUST CICCUST = new CICCUST();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    BPCPQORG BPCPQORG = new BPCPQORG();
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    DCRIAACR DCRIAACR = new DCRIAACR();
    DDRINTB DDRINTB = new DDRINTB();
    DDRACCU DDRACCU = new DDRACCU();
    DDRDREG DDRDREG = new DDRDREG();
    DDRDDTD DDRDDTD = new DDRDDTD();
    String WS_IAACR_VIA_AC = " ";
    SCCGWA SCCGWA;
    DDCSPINT DDCSPINT;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPCGPFEE BPCGPFEE;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCGCFEE BPCGCFEE;
    public void MP(SCCGWA SCCGWA, DDCSPINT DDCSPINT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSPINT = DDCSPINT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSPINT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCGPFEE = (BPCGPFEE) GWA_BP_AREA.FEE_AREA.FEE_PARM_PTR;
        BPCGCFEE = new BPCGCFEE();
        IBS.init(SCCGWA, BPCGCFEE);
        IBS.CPY2CLS(SCCGWA, GWA_BP_AREA.FEE_AREA.FEE_DATA_PTR, BPCGCFEE);
        BPCGCFEE = new BPCGCFEE();
        IBS.init(SCCGWA, BPCGCFEE);
        IBS.CPY2CLS(SCCGWA, GWA_BP_AREA.FEE_AREA.FEE_DATA_PTR, BPCGCFEE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSPINT.DEP_INT);
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B015_GET_CI_AC_INFO();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CANCEL_IND);
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B020_CHECK_AC_STS_PROC();
            if (pgmRtn) return;
        }
        B025_GET_CI_PROC();
        if (pgmRtn) return;
        B036_GET_AMT();
        if (pgmRtn) return;
        B037_CHECK_STS_TBL_PROC();
        if (pgmRtn) return;
        if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
            B043_CHK_AC_REL_AGRE();
            if (pgmRtn) return;
        }
        B045_POST_INT_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            if (DDRMST.AC_STS == 'W') {
                B055_UPD_DDTDREG_PROC();
                if (pgmRtn) return;
            }
        } else {
            if (DDRMST.CLOSE_AC_STS == 'W') {
                B055_UPD_DDTDREG_PROC();
                if (pgmRtn) return;
            }
        }
        if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
            B080_SET_FEE_INFO();
            if (pgmRtn) return;
            B090_CALL_FEE();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y' 
            && !SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
            B100_DATA_OUTPUT1();
            if (pgmRtn) return;
            B110_DATA_OUTPUT2();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSPINT.AC);
        CEP.TRC(SCCGWA, DDCSPINT.CCY);
        CEP.TRC(SCCGWA, DDCSPINT.CCY_TYPE);
        CEP.TRC(SCCGWA, DDCSPINT.RMK);
        CEP.TRC(SCCGWA, DDCSPINT.PAY_TYPE);
        CEP.TRC(SCCGWA, DDCSPINT.ID_TYPE);
        CEP.TRC(SCCGWA, DDCSPINT.ID_NO);
        CEP.TRC(SCCGWA, DDCSPINT.PAY_SIGN_NO);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            && GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE != SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CANCEL_NO_TERTIAN;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSPINT.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSPINT.CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSPINT.CCY_TYPE == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_TYPE_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B015_GET_CI_AC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = DDCSPINT.AC;
        CICQACAC.DATA.CCY_ACAC = DDCSPINT.CCY;
        CICQACAC.DATA.CR_FLG = DDCSPINT.CCY_TYPE;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDRMST);
            IBS.init(SCCGWA, DDCRMST);
            DDRMST.KEY.CUS_AC = CICQACAC.DATA.AGR_NO;
            DDCRMST.FUNC = 'R';
            DDCRMST.REC_PTR = DDRMST;
            DDCRMST.REC_LEN = 425;
            S000_CALL_DDZRMST();
            if (pgmRtn) return;
        }
    }
    public void B020_CHECK_AC_STS_PROC() throws IOException,SQLException,Exception {
        if (DDRMST.AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRMST.AC_STS == 'M') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_HAND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRMST.AC_STS == 'D') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_L_HANGED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_TMP_ACATTR = DDRMST.AC_TYPE;
    }
    public void B020_10_CHK_SUB_AC_STS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRIAACR);
        DCRIAACR.KEY.VIA_AC = DDCSPINT.VIA_AC;
        WS_IAACR_VIA_AC = DDCSPINT.VIA_AC;
        T000_STARTBR_DCTIAACR();
        if (pgmRtn) return;
        T000_READNEXT_DCTIAACR();
        if (pgmRtn) return;
        while (WS_RETURN_INFO != 'N') {
            if (DCRIAACR.DEFAULT_FLG == 'Y' 
                && !DCCPACTY.OUTPUT.STD_AC.equalsIgnoreCase(DCRIAACR.SUB_AC)) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_NOT_DFT_SUB_AC;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DCRIAACR.DEFAULT_FLG != 'Y' 
                && DCRIAACR.ACCR_FLG == '1') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_SUB_AC_NOT_CLS;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            T000_READNEXT_DCTIAACR();
            if (pgmRtn) return;
        }
        T000_ENDBR_DCTIAACR();
        if (pgmRtn) return;
    }
    public void B025_GET_CI_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'C';
        CICCUST.DATA.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        WS_CI_TYPE = CICCUST.O_DATA.O_CI_TYP;
        WS_CI_NO = CICCUST.O_DATA.O_CI_NO;
    }
    public void B035_GET_PRD_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIQPRD);
        DDCIQPRD.INPUT_DATA.PROD_CODE = DDRMST.PROD_CODE;
        DDCIQPRD.INPUT_DATA.CCY = DDCSPINT.CCY;
        DDCIQPRD.DDVMPRD_PTR = DDVMPRD;
        DDCIQPRD.DDVMRAT_PTR = DDVMRAT;
        S000_CALL_DDZIQPRD();
        if (pgmRtn) return;
    }
    public void B036_GET_AMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQORG.BBR);
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READ_DDTCCY();
        if (pgmRtn) return;
        WS_SPINT_PRIN = DDRCCY.CURR_BAL;
        CEP.TRC(SCCGWA, DDRCCY.OWNER_BRDP);
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD") 
            && (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")) 
            && DDRMST.CROS_DR_FLG == '2' 
            && !DDRMST.AC_STS_WORD.substring(21 - 1, 21 + 1 - 1).equalsIgnoreCase("1") 
            && DDRCCY.OWNER_BR != BPCPQORG.BBR) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_NOT_DR_AT_ALL);
        }
        if (DDRCCY.HOLD_BAL != 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.AC_HAD_AMT_HOLD;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_CURR_BAL = DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL;
        if (WS_CURR_BAL < 0 
            && SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AVL_BAL_NOT_ENOUGH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDRCCY.AC_TYPE);
    }
    public void B037_CHECK_STS_TBL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFCSTS);
        BPCFCSTS.MSG_INFO = DDCSPINT.AC;
        CEP.TRC(SCCGWA, BPCFCSTS.MSG_INFO);
        BPCFCSTS.AP_MMO = K_STS_TABLE_APP;
        BPCFCSTS.TBL_NO = K_STS_TBL_NO;
        if (CICCUST.O_DATA.O_STSW == null) CICCUST.O_DATA.O_STSW = "";
        JIBS_tmp_int = CICCUST.O_DATA.O_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICCUST.O_DATA.O_STSW += " ";
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        BPCFCSTS.STATUS_WORD = CICCUST.O_DATA.O_STSW.substring(0, 80) + BPCFCSTS.STATUS_WORD.substring(80);
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 101 - 1) + DDRMST.AC_STS_WORD + BPCFCSTS.STATUS_WORD.substring(101 + 99 - 1);
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 201 - 1) + DDRCCY.STS_WORD.substring(0, 99) + BPCFCSTS.STATUS_WORD.substring(201 + 99 - 1);
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD);
        S000_CALL_BPZFCSTS();
        if (pgmRtn) return;
    }
    public void B040_INQ_INT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUPINT);
        DDCUPINT.AC = DCCPACTY.OUTPUT.STD_AC;
        DDCUPINT.CCY = DDCSPINT.CCY;
        DDCUPINT.CCY_TYPE = DDCSPINT.CCY_TYPE;
        DDCUPINT.TX_TYP = 'I';
        CEP.TRC(SCCGWA, DDCUPINT.CCY);
        CEP.TRC(SCCGWA, DDCUPINT.CCY_TYPE);
        S000_CALL_DDZUPINT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCUPINT.INT_TAX);
        CEP.TRC(SCCGWA, DDCUPINT.OD_INT);
        CEP.TRC(SCCGWA, DDCUPINT.DEP_INT);
        CEP.TRC(SCCGWA, DDCUPINT.UOD_INT);
        WS_DEP_INT = DDCUPINT.DEP_INT;
        WS_INT_TAX = DDCUPINT.INT_TAX;
        WS_OD_INT = DDCUPINT.OD_INT + DDCUPINT.UOD_INT;
        WS_DEP_INT_END = DDCUPINT.DEP_INT - DDCUPINT.OD_INT - DDCUPINT.UOD_INT - DDCUPINT.INT_TAX;
    }
    public void B043_CHK_AC_REL_AGRE() throws IOException,SQLException,Exception {
        DDRDDTD.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_STARTBR_DDTDDTD();
        if (pgmRtn) return;
        T000_READNEXT_DDTDDTD();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'A';
            CICQACAC.DATA.ACAC_NO = DDRDDTD.KEY.CON_NO;
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            if (CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_STS == '0') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.FRIST_CEL_TRADE_TD_AGRE);
            }
            T000_READNEXT_DDTDDTD();
            if (pgmRtn) return;
        }
        T000_ENDBR_DDTDDTD();
        if (pgmRtn) return;
        IBS.init(SCCGWA, TDCUCACK);
        TDCUCACK.AC_NO = DDCSPINT.AC;
        S000_CALL_TDZUCACK();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDCUCACK.FOUND_FLG);
        if (TDCUCACK.FOUND_FLG == 'Y') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_HAVE_TD_CONT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCPFPDT);
        BPCPFPDT.INPUT.AC = DDCSPINT.AC;
        S000_CALL_BPZPFPDT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPFPDT.OUTPUT.MAIN_FLG);
        if (BPCPFPDT.OUTPUT.MAIN_FLG == 'Y') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_ARREARGE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B045_POST_INT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUPINT);
        DDCUPINT.AC = DDCSPINT.AC;
        DDCUPINT.CCY = DDCSPINT.CCY;
        DDCUPINT.CCY_TYPE = DDCSPINT.CCY_TYPE;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            DDCUPINT.DEP_INT = DDCSPINT.DEP_INT;
            CEP.TRC(SCCGWA, DDCUPINT.DEP_INT);
        }
        DDCUPINT.REMARK = DDCSPINT.RMK;
        DDCUPINT.NARRATIVE = DDCSPINT.NARRATIVE;
        if (DDRCCY.AC_TYPE == '2') {
            DDCUPINT.CARD_NO = DDCSPINT.AC;
        }
        DDCUPINT.TX_TYP = 'C';
        CEP.TRC(SCCGWA, DDCUPINT.CCY);
        CEP.TRC(SCCGWA, DDCUPINT.CCY_TYPE);
        S000_CALL_DDZUPINT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCUPINT.NRA_TAX);
        CEP.TRC(SCCGWA, DDCUPINT.INT_TAX);
        CEP.TRC(SCCGWA, DDCUPINT.OD_INT);
        CEP.TRC(SCCGWA, DDCUPINT.DEP_INT);
        WS_DEP_INT = DDCUPINT.DEP_INT + DDCUPINT.OD_INT - DDCUPINT.INT_TAX - DDCUPINT.NRA_TAX;
    }
    public void B050_PRT_UNPT_ITEM() throws IOException,SQLException,Exception {
        if (DDVMPRD.VAL.PRD_TOOL_PSB == 'Y') {
            if (DDCSPINT.PSBK_NO.trim().length() == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PBNO_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, DDCIPSBK);
            DDCIPSBK.FUNC = 'P';
            DDCIPSBK.AC = DCCPACTY.OUTPUT.STD_AC;
            DDCIPSBK.AC_ATTR = WS_TMP_ACATTR;
            S000_CALL_DDZIPSBK();
            if (pgmRtn) return;
        }
    }
    public void B055_UPD_DDTDREG_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRDREG);
        DDRDREG.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READ_UPDATE_DDTDREG();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            DDRDREG.RCD_STS = 'D';
        } else {
            DDRDREG.RCD_STS = 'N';
        }
        T000_REWRITE_DDTDREG();
        if (pgmRtn) return;
    }
    public void B070_UPD_MST_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            DDRMST.CLOSE_AC_STS = DDRMST.AC_STS;
            if (DDRMST.AC_STS == 'O') {
                if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
                JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
                DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 2 - 1) + "0" + DDRMST.AC_STS_WORD.substring(2 + 1 - 1);
            }
            if (DDRMST.AC_STS == 'V') {
                if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
                JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
                DDRMST.AC_STS_WORD = "0" + DDRMST.AC_STS_WORD.substring(1);
            }
            DDRMST.AC_STS = 'M';
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 10 - 1) + "1" + DDRMST.AC_STS_WORD.substring(10 + 1 - 1);
        } else {
            DDRMST.AC_STS = DDRMST.CLOSE_AC_STS;
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 10 - 1) + "0" + DDRMST.AC_STS_WORD.substring(10 + 1 - 1);
            if (DDRMST.CLOSE_AC_STS == 'O') {
                if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
                JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
                DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 2 - 1) + "1" + DDRMST.AC_STS_WORD.substring(2 + 1 - 1);
            }
            if (DDRMST.CLOSE_AC_STS == 'V') {
                if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
                JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
                DDRMST.AC_STS_WORD = "1" + DDRMST.AC_STS_WORD.substring(1);
            }
        }
        DDCRMST.FUNC = 'U';
        DDCRMST.REC_PTR = DDRMST;
        DDCRMST.REC_LEN = 425;
        S000_CALL_DDZRMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRMST);
    }
    public void B080_SET_FEE_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = DDCSPINT.AC;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = DDCSPINT.CCY;
        BPCFFTXI.TX_DATA.CCY_TYPE = DDCSPINT.CCY_TYPE;
        BPCFFTXI.TX_DATA.SVR_CD = SCCGWA.COMM_AREA.SERV_CODE;
        BPCFFTXI.TX_DATA.CI_NO = WS_CI_NO;
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY);
        CEP.TRC(SCCGWA, BPCFFTXI);
        S000_CALL_BPZFFTXI();
        if (pgmRtn) return;
    }
    public void B090_CALL_FEE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTCALF);
        BPCTCALF.INPUT_AREA.FUNC_CODE = 'C';
        BPCTCALF.INPUT_AREA.TX_AC = DDCSPINT.AC;
        BPCTCALF.INPUT_AREA.TX_CCY = DDCSPINT.CCY;
        if ("1".trim().length() == 0) BPCTCALF.INPUT_AREA.TX_CNT = 0;
        else BPCTCALF.INPUT_AREA.TX_CNT = Short.parseShort("1");
        BPCTCALF.INPUT_AREA.TX_CI = WS_CI_NO;
        BPCTCALF.INPUT_AREA.PROD_CODE1 = DDRMST.PROD_CODE;
        S000_CALL_BPZFCALF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.TX_AC);
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.TX_CI);
        CEP.TRC(SCCGWA, BPCTCALF);
    }
    public void B095_CLS_FREE_AC_PROC() throws IOException,SQLException,Exception {
        if (DDCSPINT.VIA_AC.trim().length() > 0) {
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                IBS.init(SCCGWA, DCCICLSA);
                DCCICLSA.INP_DATA.TYPE = "2";
                DCCICLSA.INP_DATA.AC = DCCPACTY.OUTPUT.STD_AC;
                CEP.TRC(SCCGWA, DCCICLSA);
                S000_CALL_DCZICLSA();
                if (pgmRtn) return;
                IBS.init(SCCGWA, DCCICLSA);
                DCCICLSA.INP_DATA.TYPE = "1";
                DCCICLSA.INP_DATA.AC = DDCSPINT.VIA_AC;
                CEP.TRC(SCCGWA, DCCICLSA);
                S000_CALL_DCZICLSA();
                if (pgmRtn) return;
            } else {
                IBS.init(SCCGWA, DCCICLSC);
                DCCICLSC.INP_DATA.AC = DCCPACTY.OUTPUT.STD_AC;
                CEP.TRC(SCCGWA, DCCICLSC);
                S000_CALL_DCZICLSC();
                if (pgmRtn) return;
                IBS.init(SCCGWA, DCCICLSC);
                DCCICLSC.INP_DATA.AC = DDCSPINT.VIA_AC;
                CEP.TRC(SCCGWA, DCCICLSC);
                S000_CALL_DCZICLSC();
                if (pgmRtn) return;
            }
        }
    }
    public void B100_DATA_OUTPUT1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOPINT);
        DDCOPINT.TX_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCOPINT.AC = DDCSPINT.AC;
        DDCOPINT.CCY = DDCSPINT.CCY;
        DDCOPINT.CCY_TYPE = DDCSPINT.CCY_TYPE;
        DDCOPINT.PRIN = WS_SPINT_PRIN;
        DDCOPINT.DEP_INT = DDCSPINT.DEP_INT;
        DDCOPINT.INT_TAX = WS_INT_TAX;
        DDCOPINT.OD_INT = WS_OD_INT;
        WS_IDX = 1;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_IDX-1]);
        while (WS_IDX <= 20 
            && JIBS_tmp_str[0].trim().length() != 0) {
            CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_IDX-1].CHG_AC_TY);
            CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_IDX-1].CHG_AC);
            CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_IDX-1].ADJ_AMT);
            if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_IDX-1].CHG_AC_TY == '0' 
                && BPCGCFEE.FEE_DATA.FEE_INFO[WS_IDX-1].CHG_AC.equalsIgnoreCase(DCCPACTY.OUTPUT.STD_AC)) {
                WS_FEE_AMT = WS_FEE_AMT + BPCGCFEE.FEE_DATA.FEE_INFO[WS_IDX-1].ADJ_AMT;
            }
            CEP.TRC(SCCGWA, WS_IDX);
            CEP.TRC(SCCGWA, WS_FEE_AMT);
            WS_IDX = (short) (WS_IDX + 1);
        }
        CEP.TRC(SCCGWA, WS_FEE_AMT);
        CEP.TRC(SCCGWA, BPCGCFEE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_BANK);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.FEE_DATA_IND);
        DDCOPINT.TOT_BAL = WS_SPINT_PRIN + DDCOPINT.DEP_INT - DDCOPINT.OD_INT - DDCOPINT.INT_TAX - WS_FEE_AMT;
        DDCOPINT.RMK = DDCSPINT.RMK;
        DDCOPINT.NARRATIVE = DDCSPINT.NARRATIVE;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, DDCSPINT.AC);
        CEP.TRC(SCCGWA, DDCSPINT.AC_CNM);
        CEP.TRC(SCCGWA, DDCSPINT.AC_ENM);
        CEP.TRC(SCCGWA, DDCSPINT.CCY);
        CEP.TRC(SCCGWA, DDCSPINT.CCY_TYPE);
        CEP.TRC(SCCGWA, DDCSPINT.CARD_NO);
        CEP.TRC(SCCGWA, DDCSPINT.PSBK_NO);
        CEP.TRC(SCCGWA, DDCSPINT.CHQ_TYPE);
        CEP.TRC(SCCGWA, DDCSPINT.CHQ_NO);
        CEP.TRC(SCCGWA, DDCSPINT.ID_TYPE);
        CEP.TRC(SCCGWA, DDCSPINT.ID_NO);
        CEP.TRC(SCCGWA, DDCSPINT.RMK);
        CEP.TRC(SCCGWA, DDCOPINT.TOT_BAL);
        CEP.TRC(SCCGWA, WS_SPINT_PRIN);
        CEP.TRC(SCCGWA, WS_DEP_INT);
        CEP.TRC(SCCGWA, WS_INT_TAX);
        CEP.TRC(SCCGWA, WS_OD_INT);
        CEP.TRC(SCCGWA, DDCOPINT.DEP_INT);
        CEP.TRC(SCCGWA, DDCOPINT.OD_INT);
        CEP.TRC(SCCGWA, DDCOPINT.INT_TAX);
        CEP.TRC(SCCGWA, DDCSPINT.RMK);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DDCOPINT;
        SCCFMT.DATA_LEN = 389;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B110_DATA_OUTPUT2() throws IOException,SQLException,Exception {
        R000_BEGIN_MPAGE_OUTPUT();
        if (pgmRtn) return;
        R000_GET_CAL_INT();
        if (pgmRtn) return;
        if (DDRINTB.TIR_TYPE == 'A') {
            if (DDRINTB.TIR_AMT1 != 0) {
                WS_DATA_OUT2.WS_END_DATE = DDRINTB.END_TOT_DATE;
                WS_DATA_OUT2.WS_DEP_TOT = DDRINTB.DEP_TOT1;
                WS_DATA_OUT2.WS_RATE = DDRINTB.TIR_RATE1;
                WS_DATA_OUT2.WS_DP_INT = DDRINTB.DEP_TOT1 * DDRINTB.TIR_RATE1 / BPCQCCY.DATA.STD_DAYS / 100;
                WS_DATA_OUT2.WS_END_DATE = 1111111;
                R000_INT_INF_OUTPUT();
                if (pgmRtn) return;
            }
            if (DDRINTB.TIR_AMT2 != 0) {
                WS_DATA_OUT2.WS_END_DATE = DDRINTB.END_TOT_DATE;
                WS_DATA_OUT2.WS_DEP_TOT = DDRINTB.DEP_TOT2;
                WS_DATA_OUT2.WS_RATE = DDRINTB.TIR_RATE2;
                WS_DATA_OUT2.WS_DP_INT = DDRINTB.DEP_TOT2 * DDRINTB.TIR_RATE2 / BPCQCCY.DATA.STD_DAYS / 100;
                WS_DATA_OUT2.WS_END_DATE = 2222;
                R000_INT_INF_OUTPUT();
                if (pgmRtn) return;
            }
            if (DDRINTB.TIR_AMT3 != 0) {
                WS_DATA_OUT2.WS_END_DATE = DDRINTB.END_TOT_DATE;
                WS_DATA_OUT2.WS_DEP_TOT = DDRINTB.DEP_TOT3;
                WS_DATA_OUT2.WS_RATE = DDRINTB.TIR_RATE3;
                WS_DATA_OUT2.WS_DP_INT = DDRINTB.DEP_TOT3 * DDRINTB.TIR_RATE3 / BPCQCCY.DATA.STD_DAYS / 100;
                R000_INT_INF_OUTPUT();
                if (pgmRtn) return;
            }
            if (DDRINTB.TIR_AMT4 != 0) {
                WS_DATA_OUT2.WS_END_DATE = DDRINTB.END_TOT_DATE;
                WS_DATA_OUT2.WS_DEP_TOT = DDRINTB.DEP_TOT4;
                WS_DATA_OUT2.WS_RATE = DDRINTB.TIR_RATE4;
                WS_DATA_OUT2.WS_DP_INT = DDRINTB.DEP_TOT4 * DDRINTB.TIR_RATE4 / BPCQCCY.DATA.STD_DAYS / 100;
                R000_INT_INF_OUTPUT();
                if (pgmRtn) return;
            }
            if (DDRINTB.TIR_AMT5 != 0) {
                WS_DATA_OUT2.WS_END_DATE = DDRINTB.END_TOT_DATE;
                WS_DATA_OUT2.WS_DEP_TOT = DDRINTB.DEP_TOT5;
                WS_DATA_OUT2.WS_RATE = DDRINTB.TIR_RATE5;
                WS_DATA_OUT2.WS_DP_INT = DDRINTB.DEP_TOT5 * DDRINTB.TIR_RATE5 / BPCQCCY.DATA.STD_DAYS / 100;
                R000_INT_INF_OUTPUT();
                if (pgmRtn) return;
            }
        } else {
            CEP.TRC(SCCGWA, "12000000000");
            WS_DATA_OUT2.WS_END_DATE = DDRINTB.END_TOT_DATE;
            WS_DATA_OUT2.WS_DEP_TOT = DDRINTB.DEP_TOT1;
            WS_DATA_OUT2.WS_RATE = DDRINTB.TIR_RATE1;
            WS_DATA_OUT2.WS_DP_INT = DDRINTB.DEP_TOT1 * DDRINTB.TIR_RATE1 / BPCQCCY.DATA.STD_DAYS / 100;
            R000_INT_INF_OUTPUT();
            if (pgmRtn) return;
        }
        WS_DATA_OUT1.WS_AC1 = DDCSPINT.AC;
        WS_DATA_OUT1.WS_AC2 = DDCSPINT.AC;
        WS_DATA_OUT1.WS_CCY = DDCSPINT.CCY;
        WS_DATA_OUT1.WS_TOT_INT = DDCSPINT.DEP_INT;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT1;
        SCCFMT.DATA_PTR = WS_DATA_OUT1;
        SCCFMT.DATA_LEN = 111;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_BEGIN_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.MAX_COL_NO = 67;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_INT_INF_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_DATA_OUT2);
        SCCMPAG.DATA_LEN = 67;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_GET_CAL_INT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = DDCSPINT.CCY;
        S00_CALL_BPZQCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCQCCY.DATA.STD_DAYS);
    }
    public void T000_READ_DDTINTB() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRINTB);
        DDRINTB.KEY.AC = DCCPACTY.OUTPUT.STD_AC;
        DDTINTB_RD = new DBParm();
        DDTINTB_RD.TableName = "DDTINTB";
        DDTINTB_RD.eqWhere = "AC";
        IBS.READ(SCCGWA, DDRINTB, DDTINTB_RD);
    }
    public void T000_STRBR_DDTACCU() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRACCU);
        DDRACCU.KEY.AC = DCCPACTY.OUTPUT.STD_AC;
        DDRACCU.POST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRACCU.POST_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        DDTACCU_BR.rp = new DBParm();
        DDTACCU_BR.rp.TableName = "DDTACCU";
        DDTACCU_BR.rp.where = "AC = :DDRACCU.KEY.AC";
        IBS.STARTBR(SCCGWA, DDRACCU, this, DDTACCU_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ACCU_FLG = 'N';
        }
    }
    public void T000_READNEXT_DDTACCU() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRACCU, this, DDTACCU_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ACCU_FLG = 'N';
        }
    }
    public void T000_ENDBR_DDTACCU() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTACCU_BR);
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.col = "CURR_BAL,HOLD_BAL,OWNER_BRDP,STS_WORD,OWNER_BR";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void T000_READ_UPDATE_DDTDREG() throws IOException,SQLException,Exception {
        DDTDREG_RD = new DBParm();
        DDTDREG_RD.TableName = "DDTDREG";
        DDTDREG_RD.where = "AC = :DDRDREG.KEY.AC";
        DDTDREG_RD.fst = true;
        DDTDREG_RD.upd = true;
        IBS.READ(SCCGWA, DDRDREG, this, DDTDREG_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_DREG_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DDTDREG() throws IOException,SQLException,Exception {
        DDRDREG.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        DDRDREG.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTDREG_RD = new DBParm();
        DDTDREG_RD.TableName = "DDTDREG";
        IBS.REWRITE(SCCGWA, DDRDREG, DDTDREG_RD);
    }
    public void S000_CALL_DDZIQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-DDPRD", DDCIQPRD);
        if (DDCIQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZUPINT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-POST-INT", DDCUPINT);
    }
    public void S000_CALL_DDZIPSBK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-PSBK-PROC   ", DDCIPSBK);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
    }
    public void S000_CALL_BPZPFPDT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-FEE-UNCHG-CHK", BPCPFPDT, true);
        if (BPCPFPDT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPFPDT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSICOT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-I-COT", LNCSICOT, true);
        if (LNCSICOT.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSICOT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZCAGT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CHK-AC-AGT", CICCAGT);
    }
    public void S000_CALL_TDZUCACK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-CLOSE-AC-CHK", TDCUCACK);
    }
    public void S000_CALL_GDZISTAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-I-ST-AC-PROC", GDCISTAC);
    }
    public void S000_CALL_DCZURCAR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-RTV-CAR", DCCURCAR);
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.STD_AC);
        CEP.TRC(SCCGWA, DDCRMST.RETURN_INFO);
        CEP.TRC(SCCGWA, DDCRMST.RC);
    }
    public void S000_CALL_DDZRMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-R-DDTMST", DDCRMST);
        if (DDCRMST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCRMST.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDCRMST.RETURN_INFO);
        CEP.TRC(SCCGWA, DDCRMST.RC);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU1);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
    }
    public void S000_CALL_BPZFFTXI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-TX-INFO", BPCFFTXI);
        CEP.TRC(SCCGWA, BPCFFTXI.RC.RC_CODE);
        if (BPCFFTXI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFTXI.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFCALF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-CAL-FEE", BPCTCALF);
        if (BPCTCALF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTCALF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZICLSA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-CLS-ADD", DCCICLSA);
        if (DCCICLSA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCICLSA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZICLSC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-CLS-CAN", DCCICLSC);
        if (DCCICLSC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCICLSC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S00_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
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
    public void S000_CALL_BPZFCSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-STS-TBL-AUTH", BPCFCSTS);
        CEP.TRC(SCCGWA, BPCFCSTS.RC);
        if (BPCFCSTS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFCSTS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DCTIAACR() throws IOException,SQLException,Exception {
        DCTIAACR_BR.rp = new DBParm();
        DCTIAACR_BR.rp.TableName = "DCTIAACR";
        DCTIAACR_BR.rp.where = "VIA_AC = :WS_IAACR_VIA_AC";
        IBS.STARTBR(SCCGWA, DCRIAACR, this, DCTIAACR_BR);
    }
    public void T000_READNEXT_DCTIAACR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRIAACR, this, DCTIAACR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_RETURN_INFO = 'N';
        }
    }
    public void T000_READ_DCTIAACR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRIAACR);
        DCRIAACR.SUB_AC = DDCSPINT.AC;
        DCTIAACR_RD = new DBParm();
        DCTIAACR_RD.TableName = "DCTIAACR";
        DCTIAACR_RD.col = "ACCR_FLG";
        DCTIAACR_RD.where = "SUB_AC = :DCRIAACR.SUB_AC";
        DCTIAACR_RD.fst = true;
        DCTIAACR_RD.errhdl = true;
        IBS.READ(SCCGWA, DCRIAACR, this, DCTIAACR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        }
        if (DCRIAACR.ACCR_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CLOSE_AC_FAIL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_DCTIAACR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTIAACR_BR);
    }
    public void T000_STARTBR_DDTDDTD() throws IOException,SQLException,Exception {
        DDTDDTD_BR.rp = new DBParm();
        DDTDDTD_BR.rp.TableName = "DDTDDTD";
        DDTDDTD_BR.rp.eqWhere = "AC";
        IBS.STARTBR(SCCGWA, DDRDDTD, DDTDDTD_BR);
    }
    public void T000_READNEXT_DDTDDTD() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRDDTD, this, DDTDDTD_BR);
    }
    public void T000_ENDBR_DDTDDTD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTDDTD_BR);
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
