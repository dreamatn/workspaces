package com.hisun.DD;

import com.hisun.CI.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.BP.*;
import com.hisun.DC.*;
import com.hisun.AI.*;
import com.hisun.LN.*;
import com.hisun.TD.*;
import com.hisun.GD.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSFDOR {
    int JIBS_tmp_int;
    BigDecimal bigD;
    DBParm DDTACCU_RD;
    DBParm DDTCCY_RD;
    DBParm DDTDDTD_RD;
    brParm DDTCCY_BR = new brParm();
    DBParm DDTDREG_RD;
    DBParm DDTCLDD_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DD620";
    String K_OUTPUT_FMT1 = "DD621";
    String K_APP_MMO = "DD";
    String K_CHK_STS_TBL = "0006";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String K_DDEG1 = "DDEG1";
    String K_DDEG3 = "DDEG3";
    String K_HIS_MMO = "P401";
    String K_ITM_NO = " ";
    String WS_ERR_MSG = " ";
    char WS_MST_AC_STS = ' ';
    double WK_DREG_BAL = 0;
    char WK_DREG_STS = ' ';
    char WS_AC_TYPE = ' ';
    char WS_CI_TYPE = ' ';
    double WS_TX_AMT = 0;
    char WS_CCY_STS = ' ';
    int WS_BR = 0;
    int WS_DORM_DT = 0;
    short WS_CNT = 0;
    short WS_DORM_PERIOD1 = 0;
    double WS_DORM_BAL = 0;
    double WS_DORM_BAL2 = 0;
    int WS_OPEN_DATE = 0;
    double WS_DEP_INT = 0;
    double WS_INT_TAX = 0;
    String WS_SFDOR_AC = " ";
    char WS_ACCU_FLG = ' ';
    double WS_TOT_INT1 = 0;
    DDZSFDOR_WS_DATA_OUT1 WS_DATA_OUT1 = new DDZSFDOR_WS_DATA_OUT1();
    char WS_READ_STS_FLAG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DDRCCY DDRCCY = new DDRCCY();
    DDRDDTD DDRDDTD = new DDRDDTD();
    DDRMST DDRMST = new DDRMST();
    DDRMSTR DDRMSTR = new DDRMSTR();
    DDRDREG DDRDREG = new DDRDREG();
    DDCRMST DDCRMST = new DDCRMST();
    DDCOFDOR DDCOFDOR = new DDCOFDOR();
    DDCIDREG DDCIDREG = new DDCIDREG();
    DDCIDPAM DDCIDPAM = new DDCIDPAM();
    DDCUPINT DDCUPINT = new DDCUPINT();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    DDVDPAM2 DDVDPAM2 = new DDVDPAM2();
    CICACCU CICTACCU = new CICACCU();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    SCCBINF SCCBINF = new SCCBINF();
    SCCCLDT SCCCLDT = new SCCCLDT();
    DCCPACTY DCCPACTY = new DCCPACTY();
    AICUUPIA AICUUPIA = new AICUUPIA();
    AICPQIA AICPQIA = new AICPQIA();
    DDRCLDD DDRCLDD = new DDRCLDD();
    LNCSICOT LNCSICOT = new LNCSICOT();
    TDCUCACK TDCUCACK = new TDCUCACK();
    BPCPFPDT BPCPFPDT = new BPCPFPDT();
    DDRINTB DDRINTB = new DDRINTB();
    DDRACCU DDRACCU = new DDRACCU();
    AICPQITM AICPQITM = new AICPQITM();
    BPCQCCY BPCQCCY = new BPCQCCY();
    CICQACAC CICQACAC = new CICQACAC();
    BPCPQORG BPCPQORG = new BPCPQORG();
    GDCUSTPL GDCUSTPL = new GDCUSTPL();
    LNCSETLM LNCSETLM = new LNCSETLM();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    DDRFHIS DDRFHIS = new DDRFHIS();
    SCCGWA SCCGWA;
    DDCSFDOR DDCSFDOR;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DDCSFDOR DDCSFDOR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSFDOR = DDCSFDOR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSFDOR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, DDCOFDOR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHK_INPUT_PROC();
        if (pgmRtn) return;
        B011_POST_AC_CHK();
        if (pgmRtn) return;
        B015_GET_ACAC_INFO();
        if (pgmRtn) return;
        B020_GET_AC_INF_PROC();
        if (pgmRtn) return;
        B025_CHK_DDAC_RLT_LNAC();
        if (pgmRtn) return;
        B040_INQ_CCY_BAL_PROC();
        if (pgmRtn) return;
        B025_CONTRACT_CHECK_PROC();
        if (pgmRtn) return;
        B030_GET_CI_PROC();
        if (pgmRtn) return;
        B060_CHK_STS_TBL_PROC();
        if (pgmRtn) return;
        B070_TD_CONTRACT();
        if (pgmRtn) return;
        B080_CHK_GD_RLTSHIP();
        if (pgmRtn) return;
        B085_INQ_INT_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_DEP_INT);
        B090_POST_INT_PROC();
        if (pgmRtn) return;
        B100_INQ_CCY_BAL_PROC();
        if (pgmRtn) return;
        B121_GEN_VCH_PROC();
        if (pgmRtn) return;
        B130_WRITE_DDTDREG_PROC();
        if (pgmRtn) return;
        B150_UPD_AC_STS_PROC();
        if (pgmRtn) return;
        if (WS_TX_AMT > 0) {
            B159_FIN_HISTORY_PROC();
            if (pgmRtn) return;
        }
        B160_OUTPUT_DATA_PROC();
        if (pgmRtn) return;
        B170_OUTPUT_INT_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHK_INPUT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSFDOR.DATA.AC);
        CEP.TRC(SCCGWA, DDCSFDOR.DATA.CCY);
        CEP.TRC(SCCGWA, DDCSFDOR.DATA.CCY_TYPE);
        if (DDCSFDOR.DATA.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSFDOR.DATA.CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (!DDCSFDOR.DATA.CCY.equalsIgnoreCase("156") 
            && DDCSFDOR.DATA.CCY_TYPE == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_TYP_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (!DDCSFDOR.DATA.CCY.equalsIgnoreCase("156") 
            && (DDCSFDOR.DATA.CCY_TYPE != '1' 
            && DDCSFDOR.DATA.CCY_TYPE != '2')) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_TYP_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B011_POST_AC_CHK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.POST_AC = DDCSFDOR.DATA.AC;
        T000_STARTBR_DDTCCY();
        if (pgmRtn) return;
        T000_READNEXT_DDTCCY();
        if (pgmRtn) return;
        while (WS_READ_STS_FLAG != 'Y') {
            T000_READNEXT_DDTCCY();
            if (pgmRtn) return;
        }
        T000_ENDBR_DDTCCY();
        if (pgmRtn) return;
    }
    public void B015_GET_ACAC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.AGR_NO = DDCSFDOR.DATA.AC;
        CICQACAC.DATA.CCY_ACAC = DDCSFDOR.DATA.CCY;
        CICQACAC.DATA.CR_FLG = DDCSFDOR.DATA.CCY_TYPE;
        CICQACAC.FUNC = 'C';
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
    }
    public void B020_GET_AC_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        IBS.init(SCCGWA, DDCRMST);
        DDRMST.KEY.CUS_AC = DDCSFDOR.DATA.AC;
        DDCRMST.FUNC = 'R';
        DDCRMST.REC_PTR = DDRMST;
        DDCRMST.REC_LEN = 425;
        S000_CALL_DDZRMST();
        if (pgmRtn) return;
        WS_MST_AC_STS = DDRMST.AC_STS;
        WS_AC_TYPE = DDRMST.AC_TYPE;
        WS_BR = DDRMST.OWNER_BRDP;
        WS_OPEN_DATE = DDRMST.OPEN_DATE;
        DDCOFDOR.PRD_CODE = DDRMST.PROD_CODE;
        WS_DATA_OUT1.WS_PRD_CODE = DDRMST.PROD_CODE;
        DDCOFDOR.TXN_BR = DDRMST.OPEN_DP;
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQORG.ATTR);
        CEP.TRC(SCCGWA, BPCPQORG.SUPR_BR);
        CEP.TRC(SCCGWA, DDRMST.OWNER_BRDP);
        if (BPCPQORG.ATTR == '3') {
            if ((DDRMST.OWNER_BRDP != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) 
                && (DDRMST.OWNER_BRDP != BPCPQORG.SUPR_BR)) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ERR_TXN_BRANCH;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, "AAAAAA");
            }
        } else {
            CEP.TRC(SCCGWA, "BBBBBBB");
            if (DDRMST.OWNER_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ERR_TXN_BRANCH);
            }
        }
        if (WS_MST_AC_STS == 'D') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_DORMANT_AC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRMST.LAST_FN_DATE != 0) {
            WS_DORM_DT = DDRMST.LAST_FN_DATE;
        } else {
            WS_DORM_DT = DDRMST.OPEN_DATE;
        }
        if (WS_MST_AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRMST.AC_TYPE == 'J' 
            || DDRMST.AC_TYPE == 'N' 
            || DDRMST.AC_TYPE == 'O') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_REFUSE_FAIL_AC);
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_DREG_REC_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ACCR_INT_CLOSE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(42 - 1, 42 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_LEGAL_OVERDRAFT_AC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B025_CHK_DDAC_RLT_LNAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSETLM);
        LNCSETLM.FUNC = 'I';
        LNCSETLM.DD_AC = DDCSFDOR.DATA.AC;
        LNCSETLM.CCY = DDCSFDOR.DATA.CCY;
        LNCSETLM.CCY_TYP = DDCSFDOR.DATA.CCY_TYPE;
        S000_CALL_LNZSETLM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCSETLM.CON_FLG);
        if (LNCSETLM.CON_FLG == 'N') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_DDAC_RLT_LNAC);
        }
    }
    public void B025_CONTRACT_CHECK_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPFPDT);
        BPCPFPDT.INPUT.AC = DDCSFDOR.DATA.AC;
        S000_CALL_BPZPFPDT();
        if (pgmRtn) return;
        if (BPCPFPDT.OUTPUT.PCHG_FLG == 'Y' 
            || BPCPFPDT.OUTPUT.UNCHG_FLG == 'Y') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_ARREARGE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPFPDT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-FEE-UNCHG-CHK", BPCPFPDT, true);
        if (BPCPFPDT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPFPDT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSETLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-M-SETLM", LNCSETLM);
    }
    public void B030_GET_CI_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICTACCU);
        CICTACCU.DATA.AGR_NO = DDCSFDOR.DATA.AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        WS_CI_TYPE = CICTACCU.DATA.CI_TYP;
        CEP.TRC(SCCGWA, WS_CI_TYPE);
        CEP.TRC(SCCGWA, CICTACCU.DATA.CI_NO);
        if (WS_CI_TYPE == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CI_TYPE_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_CI_TYPE == '3') {
            IBS.init(SCCGWA, DDRDDTD);
            DDRDDTD.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            DDRDDTD.EXP_DATE = SCCGWA.COMM_AREA.AC_DATE;
            T000_READ_DDTDDTD();
            if (pgmRtn) return;
        }
    }
    public void B040_INQ_CCY_BAL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = DDCSFDOR.DATA.AC;
        DDRCCY.CCY = DDCSFDOR.DATA.CCY;
        DDRCCY.CCY_TYPE = DDCSFDOR.DATA.CCY_TYPE;
        CEP.TRC(SCCGWA, DDRCCY.CUS_AC);
        CEP.TRC(SCCGWA, DDRCCY.CCY);
        CEP.TRC(SCCGWA, DDRCCY.CCY_TYPE);
        T000_READ_DDTCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRCCY.KEY.AC);
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        CEP.TRC(SCCGWA, DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1));
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        CEP.TRC(SCCGWA, DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1));
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        CEP.TRC(SCCGWA, DDRCCY.STS_WORD.substring(10 - 1, 10 + 1 - 1));
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        CEP.TRC(SCCGWA, DDRCCY.STS_WORD.substring(12 - 1, 12 + 1 - 1));
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        CEP.TRC(SCCGWA, DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1));
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        CEP.TRC(SCCGWA, DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1));
        WS_CCY_STS = DDRCCY.STS;
        if (WS_CCY_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_CLEARED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRCCY.HOLD_BAL != 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.AC_HAD_AMT_HOLD;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ACCR_INT_CLOSE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CR_HOLD;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
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
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1") 
            || DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_FORBID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B060_CHK_STS_TBL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFCSTS);
        BPCFCSTS.AP_MMO = K_APP_MMO;
        BPCFCSTS.TBL_NO = K_CHK_STS_TBL;
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        if (CICTACCU.DATA.CI_STSW == null) CICTACCU.DATA.CI_STSW = "";
        JIBS_tmp_int = CICTACCU.DATA.CI_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICTACCU.DATA.CI_STSW += " ";
        BPCFCSTS.STATUS_WORD = CICTACCU.DATA.CI_STSW + BPCFCSTS.STATUS_WORD.substring(30);
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        if (CICTACCU.DATA.STSW == null) CICTACCU.DATA.STSW = "";
        JIBS_tmp_int = CICTACCU.DATA.STSW.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) CICTACCU.DATA.STSW += " ";
        BPCFCSTS.STATUS_WORD = CICTACCU.DATA.STSW + BPCFCSTS.STATUS_WORD.substring(8);
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 101 - 1) + DDRMST.AC_STS_WORD + BPCFCSTS.STATUS_WORD.substring(101 + 99 - 1);
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD);
        S000_CALL_BPZFCSTS();
        if (pgmRtn) return;
    }
    public void B070_TD_CONTRACT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCUCACK);
        TDCUCACK.AC_NO = DDCSFDOR.DATA.AC;
        S000_CALL_TDZUCACK();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDCUCACK.PI_FLG);
        CEP.TRC(SCCGWA, TDCUCACK.GK_FLG);
        CEP.TRC(SCCGWA, TDCUCACK.ZC_FLG);
        CEP.TRC(SCCGWA, TDCUCACK.FB_FLG);
        CEP.TRC(SCCGWA, TDCUCACK.FOUND_FLG);
        if (TDCUCACK.PI_FLG == 'Y') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_IS_FX_ACCT);
        }
        if (TDCUCACK.GK_FLG == 'Y') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_IS_GK_ACCT);
        }
        if (TDCUCACK.ZC_FLG == 'Y') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_IS_ZC_ACCT);
        }
        if (TDCUCACK.FB_FLG == 'Y') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_IS_FB_ACCT);
        }
        if (TDCUCACK.FOUND_FLG == 'Y') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_HAVE_TD_CONT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B080_CHK_GD_RLTSHIP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCUSTPL);
        GDCUSTPL.INPUT.AC = DDCSFDOR.DATA.AC;
        S000_CALL_GDZUSTPL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, GDCUSTPL.OUTPUT.RLT_STS);
        if (GDCUSTPL.OUTPUT.RLT_STS == 'Y') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_GDAC_HAS_RLT);
        }
    }
    public void B085_INQ_INT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUPINT);
        DDCUPINT.AC = DDCSFDOR.DATA.AC;
        DDCUPINT.CCY = DDCSFDOR.DATA.CCY;
        DDCUPINT.CCY_TYPE = DDCSFDOR.DATA.CCY_TYPE;
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
    }
    public void B090_POST_INT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUPINT);
        DDCUPINT.AC = DDCSFDOR.DATA.AC;
        DDCUPINT.CCY = DDCSFDOR.DATA.CCY;
        DDCUPINT.CCY_TYPE = DDCSFDOR.DATA.CCY_TYPE;
        DDCUPINT.TX_MMO = K_HIS_MMO;
        DDCUPINT.REMARK = DDCSFDOR.DATA.REMARKS;
        DDCUPINT.TX_TYP = 'O';
        CEP.TRC(SCCGWA, DDCUPINT.CCY);
        CEP.TRC(SCCGWA, DDCUPINT.CCY_TYPE);
        S000_CALL_DDZUPINT();
        if (pgmRtn) return;
    }
    public void B100_INQ_CCY_BAL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = DDCSFDOR.DATA.AC;
        DDRCCY.CCY = DDCSFDOR.DATA.CCY;
        DDRCCY.CCY_TYPE = DDCSFDOR.DATA.CCY_TYPE;
        CEP.TRC(SCCGWA, DDRCCY.CUS_AC);
        CEP.TRC(SCCGWA, DDRCCY.CCY);
        CEP.TRC(SCCGWA, DDRCCY.CCY_TYPE);
        T000_READ_DDTCCY();
        if (pgmRtn) return;
        WS_TX_AMT = DDRCCY.CURR_BAL;
        DDCOFDOR.BAL = DDRCCY.CURR_BAL;
        WS_DATA_OUT1.WS_BAL = DDRCCY.CURR_BAL;
        if (WS_TX_AMT < 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_UPT1_RECORD_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B110_DEBIT_CAPITAL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICPQIA);
        AICPQIA.CD.AC_TYP = "3";
        AICPQIA.CD.BUSI_KND = K_DDEG3;
        AICPQIA.BR = DDRMST.OWNER_BRDP;
        AICPQIA.CCY = DDCSFDOR.DATA.CCY;
        AICPQIA.SIGN = 'C';
        S000_CALL_AIZPQIA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICPQIA.AC);
        DDCOFDOR.AC2 = AICPQIA.AC;
        if (AICPQIA.AC == null) AICPQIA.AC = "";
        JIBS_tmp_int = AICPQIA.AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) AICPQIA.AC += " ";
        if (K_ITM_NO == null) K_ITM_NO = "";
        JIBS_tmp_int = K_ITM_NO.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) K_ITM_NO += " ";
        K_ITM_NO = AICPQIA.AC.substring(13 - 1, 13 + 8 - 1) + K_ITM_NO.substring(8);
        CEP.TRC(SCCGWA, K_ITM_NO);
        if (WS_TX_AMT != 0) {
            IBS.init(SCCGWA, DDCUDRAC);
            DDCUDRAC.AC = DDCSFDOR.DATA.AC;
            DDCUDRAC.CCY = DDCSFDOR.DATA.CCY;
            DDCUDRAC.CCY_TYPE = DDCSFDOR.DATA.CCY_TYPE;
            DDCUDRAC.TX_AMT = WS_TX_AMT;
            DDCUDRAC.TX_MMO = K_HIS_MMO;
            DDCUDRAC.TX_TYPE = 'T';
            DDCUDRAC.OTHER_AC = AICPQIA.AC;
            S000_CALL_DDZUDRAC();
            if (pgmRtn) return;
        }
    }
    public void B120_GEN_VCH_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = AICPQIA.AC;
        AICUUPIA.DATA.RVS_SEQ = 0;
        AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.AMT = WS_TX_AMT;
        AICUUPIA.DATA.CCY = DDCSFDOR.DATA.CCY;
        AICUUPIA.DATA.EVENT_CODE = "CR";
        AICUUPIA.DATA.POST_NARR = " ";
        AICUUPIA.DATA.RVS_NO = " ";
        AICUUPIA.DATA.EVENT_CODE = "CR";
        S000_CALL_AIZUUPIA();
        if (pgmRtn) return;
        if (AICUUPIA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B121_GEN_VCH_PROC() throws IOException,SQLException,Exception {
        if (WS_TX_AMT > 0) {
            IBS.init(SCCGWA, BPCPOEWA);
            BPCPOEWA.DATA.CNTR_TYPE = "CAAC";
            BPCPOEWA.DATA.PROD_CODE = DDRCCY.PROD_CODE;
            BPCPOEWA.DATA.EVENT_CODE = "DH";
            BPCPOEWA.DATA.BR_OLD = DDRCCY.OWNER_BR;
            BPCPOEWA.DATA.BR_NEW = DDRCCY.OWNER_BR;
            BPCPOEWA.DATA.CCY_INFO[1-1].CCY = DDCSFDOR.DATA.CCY;
            BPCPOEWA.DATA.AMT_INFO[1-1].AMT = WS_TX_AMT;
            BPCPOEWA.DATA.AMT_INFO[20-1].AMT = WS_TX_AMT;
            BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCPOEWA.DATA.CI_NO = CICTACCU.DATA.CI_NO;
            BPCPOEWA.DATA.AC_NO = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            BPCPOEWA.DATA.REF_NO = " ";
            S000_CALL_BPZPOEWA();
            if (pgmRtn) return;
        }
    }
    public void B130_WRITE_DDTDREG_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRDREG);
        DDRDREG.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READ_DDTDREG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDCIDREG);
        DDCIDREG.DATA.KEY.AC = DDCSFDOR.DATA.AC;
        DDCIDREG.DATA.KEY.CCY = DDCSFDOR.DATA.CCY;
        DDCIDREG.DATA.KEY.CCY_TYPE = DDCSFDOR.DATA.CCY_TYPE;
        DDCIDREG.DATA.STS = '2';
        DDCIDREG.DATA.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDCIDREG.DATA.DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCIDREG.DATA.BAL = WS_TX_AMT;
        DDCIDREG.DATA.INT = WS_DEP_INT;
        DDCIDREG.DATA.INT_BAL = WS_INT_TAX;
        DDCIDREG.DATA.OPN_DT = WS_OPEN_DATE;
        DDCIDREG.DATA.FLG = 'O';
        DDCIDREG.DATA.RCD_STS = 'N';
        DDCIDREG.DATA.D_DT = SCCGWA.COMM_AREA.AC_DATE;
        DDCIDREG.DATA.D_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDCIDREG.DATA.D_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDCIDREG.DATA.ITM_NO = K_ITM_NO;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            DDCIDREG.OPT = 'U';
        } else {
            DDCIDREG.OPT = 'A';
        }
        CEP.TRC(SCCGWA, DDCSFDOR.DATA.AC);
        CEP.TRC(SCCGWA, DDCSFDOR.DATA.CCY);
        CEP.TRC(SCCGWA, DDCSFDOR.DATA.CCY_TYPE);
        CEP.TRC(SCCGWA, DDCIDREG.DATA.BR);
        CEP.TRC(SCCGWA, WS_TX_AMT);
        CEP.TRC(SCCGWA, WS_DEP_INT);
        CEP.TRC(SCCGWA, WS_INT_TAX);
        S000_CALL_DDZIDREG();
        if (pgmRtn) return;
    }
    public void B150_UPD_AC_STS_PROC() throws IOException,SQLException,Exception {
        DDRMST.AC_STS = 'D';
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 03 - 1) + "1" + DDRMST.AC_STS_WORD.substring(03 + 1 - 1);
        DDCRMST.FUNC = 'U';
        DDCRMST.REC_PTR = DDRMST;
        DDCRMST.REC_LEN = 425;
        S000_CALL_DDZRMST();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = DDCSFDOR.DATA.AC;
        DDRCCY.CCY = DDCSFDOR.DATA.CCY;
        DDRCCY.CCY_TYPE = DDCSFDOR.DATA.CCY_TYPE;
        CEP.TRC(SCCGWA, DDRCCY.CUS_AC);
        CEP.TRC(SCCGWA, DDRCCY.CCY);
        CEP.TRC(SCCGWA, DDRCCY.CCY_TYPE);
        T000_READUP_DDTCCY();
        if (pgmRtn) return;
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 03 - 1) + "1" + DDRCCY.STS_WORD.substring(03 + 1 - 1);
        if (SCCGWA.COMM_AREA.AC_DATE != DDRCCY.LAST_BAL_DATE) {
            if (SCCGWA.COMM_AREA.AC_DATE > DDRCCY.LAST_BAL_DATE) {
                DDRCCY.LAST_BAL = DDRCCY.CURR_BAL;
                DDRCCY.LAST_BAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            } else {
                DDRCCY.LAST_BAL -= WS_TX_AMT;
            }
        }
        if (DDRCCY.LAST_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            DDRCCY.LAST_DRW_CAMT = DDRCCY.DRW_CAMT;
            DDRCCY.LAST_DEP_CAMT = DDRCCY.DEP_CAMT;
            DDRCCY.LAST_DRW_TAMT = DDRCCY.DRW_TAMT;
            DDRCCY.LAST_DEP_TAMT = DDRCCY.DEP_TAMT;
            DDRCCY.LASDAY_TOT_DR_AMT = DDRCCY.DAY_TOT_DR_AMT;
            DDRCCY.LASDAY_TOT_CR_AMT = DDRCCY.DAY_TOT_CR_AMT;
            DDRCCY.DRW_CAMT = 0;
            DDRCCY.DEP_CAMT = 0;
            DDRCCY.DRW_TAMT = 0;
            DDRCCY.DEP_TAMT = 0;
            DDRCCY.DAY_TOT_DR_AMT = 0;
            DDRCCY.DAY_TOT_CR_AMT = 0;
        }
        DDRCCY.CURR_BAL = 0;
        DDRCCY.DRW_TAMT += WS_TX_AMT;
        DDRCCY.DAY_TOT_DR_AMT += WS_TX_AMT;
        DDRCCY.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DDTCCY();
        if (pgmRtn) return;
    }
    public void B155_ITEM_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICPQITM);
        AICPQITM.INPUT_DATA.BOOK_FLG = "BK001";
        AICPQITM.INPUT_DATA.NO = K_ITM_NO;
        S000_CALL_AIZPQITM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICPQITM.OUTPUT_DATA.CHS_NM);
        DDCOFDOR.ITM_NAME = AICPQITM.OUTPUT_DATA.CHS_NM;
    }
    public void B159_FIN_HISTORY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPFHIS);
        BPCPFHIS.DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPFHIS.DATA.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        BPCPFHIS.DATA.PRINT_IND = 'Y';
        BPCPFHIS.DATA.TX_DRCR_FLG = 'D';
        BPCPFHIS.DATA.AC = DDRCCY.CUS_AC;
        BPCPFHIS.DATA.ACO_AC = DDRCCY.KEY.AC;
        BPCPFHIS.DATA.TX_TYPE = 'T';
        BPCPFHIS.DATA.TX_VAL_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.TX_CCY = DDRCCY.CCY;
        BPCPFHIS.DATA.TX_AMT = WS_TX_AMT;
        BPCPFHIS.DATA.PROD_CD = DDRCCY.PROD_CODE;
        BPCPFHIS.DATA.TX_CCY_TYP = DDRCCY.CCY_TYPE;
        BPCPFHIS.DATA.CI_NO = CICTACCU.DATA.CI_NO;
        IBS.init(SCCGWA, DDRFHIS);
        DDRFHIS.TX_DATE = SCCGWA.COMM_AREA.TR_DATE;
        DDRFHIS.AC_NO = DDRCCY.CUS_AC;
        DDRFHIS.TX_TYPE = 'F';
        DDRFHIS.TX_AMT = WS_TX_AMT;
        DDRFHIS.DOMBR = DDRCCY.OWNER_BRDP;
        DDRFHIS.BKBR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRFHIS.CCY = DDRCCY.CCY;
        DDRFHIS.CRDR_FLG = "DR".charAt(0);
        DDRFHIS.TXTIME = SCCGWA.COMM_AREA.TR_TIME;
        DDRFHIS.TXBR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRFHIS.OPR = SCCGWA.COMM_AREA.TL_ID;
        DDRFHIS.LEDGER_BAL = 0;
        BPCPFHIS.DATA.FMT_CODE = "DD099";
        BPCPFHIS.DATA.FMT_LEN = 190;
        BPCPFHIS.DATA.FMT_DATA = IBS.CLS2CPY(SCCGWA, DDRFHIS);
        S000_CALL_BPZPFHIS();
        if (pgmRtn) return;
    }
    public void B160_OUTPUT_DATA_PROC() throws IOException,SQLException,Exception {
        DDCOFDOR.AC1 = DDCSFDOR.DATA.AC;
        DDCOFDOR.TX_MMO = K_HIS_MMO;
        DDCOFDOR.ITM_NO = K_ITM_NO;
        DDCOFDOR.TXN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCOFDOR.TXN_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDCOFDOR.REMARKS = DDCSFDOR.DATA.REMARKS;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DDCOFDOR;
        SCCFMT.DATA_LEN = 410;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B170_OUTPUT_INT_PROC() throws IOException,SQLException,Exception {
        T000_STRBR_DDTACCU();
        if (pgmRtn) return;
        WS_DATA_OUT1.WS_STR_DATE = DDRACCU.KEY.STR_DATE;
        WS_DATA_OUT1.WS_END_DATE = DDRACCU.KEY.END_DATE;
        WS_DATA_OUT1.WS_DEP_TOT = DDRACCU.TOT;
        WS_DATA_OUT1.WS_RATE = DDRACCU.RATE;
        WS_DATA_OUT1.WS_DP_INT = DDCIDREG.DATA.INT;
        WS_TOT_INT1 += DDCIDREG.DATA.INT;
        CEP.TRC(SCCGWA, WS_DATA_OUT1.WS_STR_DATE);
        CEP.TRC(SCCGWA, WS_DATA_OUT1.WS_END_DATE);
        CEP.TRC(SCCGWA, WS_DATA_OUT1.WS_DEP_TOT);
        CEP.TRC(SCCGWA, WS_DATA_OUT1.WS_RATE);
        CEP.TRC(SCCGWA, WS_DATA_OUT1.WS_DP_INT);
        WS_DATA_OUT1.WS_AC1 = DDCSFDOR.DATA.AC;
        WS_DATA_OUT1.WS_AC2 = DDCSFDOR.DATA.AC;
        WS_DATA_OUT1.WS_CCY = DDCSFDOR.DATA.CCY;
        WS_DATA_OUT1.WS_TOT_INT = WS_TOT_INT1 * 1;
        bigD = new BigDecimal(WS_DATA_OUT1.WS_TOT_INT);
        WS_DATA_OUT1.WS_TOT_INT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT1;
        SCCFMT.DATA_PTR = WS_DATA_OUT1;
        SCCFMT.DATA_LEN = 178;
        IBS.FMT(SCCGWA, SCCFMT);
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
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void R000_GET_CAL_INT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = DDCSFDOR.DATA.CCY;
        S00_CALL_BPZQCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCQCCY.DATA.STD_DAYS);
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
            if (pgmRtn) return;
        }
    }
    public void T000_READUP_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.eqWhere = "CUS_AC,CCY,CCY_TYPE";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTDDTD() throws IOException,SQLException,Exception {
        DDTDDTD_RD = new DBParm();
        DDTDDTD_RD.TableName = "DDTDDTD";
        DDTDDTD_RD.where = "AC = :DDRDDTD.KEY.AC "
            + "AND EXP_DATE > :DDRDDTD.EXP_DATE";
        DDTDDTD_RD.fst = true;
        IBS.READ(SCCGWA, DDRDDTD, this, DDTDDTD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_HAVE_POINT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DDTCCY() throws IOException,SQLException,Exception {
        DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.REWRITE(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void T000_STARTBR_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_BR.rp = new DBParm();
        DDTCCY_BR.rp.TableName = "DDTCCY";
        DDTCCY_BR.rp.eqWhere = "POST_AC";
        IBS.STARTBR(SCCGWA, DDRCCY, DDTCCY_BR);
    }
    public void T000_READNEXT_DDTCCY() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRCCY, this, DDTCCY_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_READ_STS_FLAG = 'Y';
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, DDRCCY.POST_AC);
        CEP.TRC(SCCGWA, DDRCCY.CUS_AC);
        CEP.TRC(SCCGWA, DDRCCY.CINT_FLG);
        CEP.TRC(SCCGWA, DDRCCY.STS);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0' 
            && (!DDRCCY.POST_AC.equalsIgnoreCase(DDRCCY.CUS_AC)) 
            && DDRCCY.CINT_FLG == 'Y' 
            && DDRCCY.STS != 'C') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.POST_AC_CAN_C);
        }
    }
    public void T000_ENDBR_DDTCCY() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTCCY_BR);
    }
    public void T000_READ_DDTDREG() throws IOException,SQLException,Exception {
        DDTDREG_RD = new DBParm();
        DDTDREG_RD.TableName = "DDTDREG";
        DDTDREG_RD.col = "AC";
        DDTDREG_RD.where = "AC = :DDRDREG.KEY.AC";
        IBS.READ(SCCGWA, DDRDREG, this, DDTDREG_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            if (DDRDREG.STS == '2' 
                || DDRDREG.STS == '3' 
                || DDRDREG.STS == '4') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_DREG_REC_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
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
    public void S000_CALL_AIZPQIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-IA", AICPQIA);
        if (AICPQIA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPQIA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC.RC_CODE);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIDREG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SRC-DDZIDREG", DDCIDREG);
        if (DDCIDREG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIDREG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_DDTCLDD() throws IOException,SQLException,Exception {
        DDTCLDD_RD = new DBParm();
        DDTCLDD_RD.TableName = "DDTCLDD";
        DDTCLDD_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DDRCLDD, DDTCLDD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CLDD_REC_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_GDZUSTPL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SRV-GDZUSTPL", GDCUSTPL);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICTACCU);
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
    public void S000_CALL_LNZSICOT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-I-COT", LNCSICOT);
        if (LNCSICOT.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSICOT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_TDZUCACK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-CLOSE-AC-CHK", TDCUCACK);
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZUPINT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-POST-INT", DDCUPINT);
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC);
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
        if (AICUUPIA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZPQITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-ITM", AICPQITM);
        CEP.TRC(SCCGWA, AICPQITM.RC);
        if (AICPQITM.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPQITM.RC);
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
    public void S000_CALL_BPZFCSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-STS-TBL-AUTH", BPCFCSTS);
        if (BPCFCSTS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFCSTS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "LINK SCSSCLDT,CLDT-RC=" + SCCCLDT.RC;
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PROC-FHIS", BPCPFHIS);
        if (BPCPFHIS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, BPCPFHIS);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPFHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
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
