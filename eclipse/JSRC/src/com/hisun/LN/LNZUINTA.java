package com.hisun.LN;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPGDIN;
import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPQBNK_DATA_INFO;
import com.hisun.CI.CICACCU;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class LNZUINTA {
    boolean pgmRtn = false;
    String K_HIS_RMKS = "ADJUST INTEREST";
    String K_HIS_RMKS_1 = "LATE CHARGE AMOUNT ADJUST";
    String WS_ERR_MSG = " ";
    char WS_CNT_ICTL = ' ';
    char WS_CNT_PLPI = ' ';
    char WS_CNT_INTA = ' ';
    char WS_CNT_RCVD = ' ';
    char WS_CNT_PYIF = ' ';
    char WS_CNT_RATN = ' ';
    char WS_REWRITE_CNT = ' ';
    int WS_LAST_BAT_AC_DATE = 0;
    int WS_VAL_DATE = 0;
    LNZUINTA_WS_OUTPUT_LIST WS_OUTPUT_LIST = new LNZUINTA_WS_OUTPUT_LIST();
    int WS_MAT_DATE = 0;
    int WS_ORI_MAT_DT = 0;
    char WS_PREC_FLG = ' ';
    char WS_PAY_MTH = ' ';
    short WS_CAL_TERM = 0;
    double WS_AMT_CNT = 0;
    double WS_AMT_CNT1 = 0;
    double WS_AMT_CNT2 = 0;
    int WS_OVD_DT = 0;
    short WS_P_CUR_TERM = 0;
    short WS_IC_CUR_TERM = 0;
    double WS_TMP_AMT = 0;
    double WS_TMP_I_INT = 0;
    double WS_TMP_O_INT = 0;
    double WS_TMP_L_INT = 0;
    char WS_INTA_FOUND_FLG = ' ';
    double WS_YIJIE_AMT = 0;
    double WS_YIJIEFULI_AMT = 0;
    double WS_JIEXILIXI_AMT = 0;
    double WS_JIEXIFULI_AMT = 0;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    LNCLCCM LNCLCCM = new LNCLCCM();
    LNCRCAL LNCRCAL = new LNCRCAL();
    LNCICAL LNCICAL = new LNCICAL();
    LNCICUT LNCICUT = new LNCICUT();
    LNCICIQ LNCICIQ = new LNCICIQ();
    LNRPLPI LNRPLPI = new LNRPLPI();
    LNCRPLPI LNCRPLPI = new LNCRPLPI();
    LNRRATN LNRRATN = new LNRRATN();
    LNCRRATN LNCRRATN = new LNCRRATN();
    LNRRCVD LNRRCVD = new LNRRCVD();
    LNCRRCVD LNCRRCVD = new LNCRRCVD();
    LNRPYIF LNRPYIF = new LNRPYIF();
    LNCRPYIF LNCRPYIF = new LNCRPYIF();
    LNRINTA LNRINTA = new LNRINTA();
    LNCRINTA LNCRINTA = new LNCRINTA();
    LNRICTL LNRICTL = new LNRICTL();
    LNCRICTL LNCRICTL = new LNCRICTL();
    LNCHINTA LNCHINTA = new LNCHINTA();
    LNCHINTA LNCHINTO = new LNCHINTA();
    LNCHINAA LNCHINAA = new LNCHINAA();
    LNCHINAA LNCHINAO = new LNCHINAA();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    LNCCONTM LNCCONTM = new LNCCONTM();
    BPCPGDIN BPCPGDIN = new BPCPGDIN();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    LNCPAIPM LNCPAIPM = new LNCPAIPM();
    LNCSSCHE LNCSSCHE = new LNCSSCHE();
    LNRPAIP LNRPAIP = new LNRPAIP();
    LNCRPAIP LNCRPAIP = new LNCRPAIP();
    LNCFCPL LNCFCPL = new LNCFCPL();
    LNRACRU LNRACRU = new LNRACRU();
    LNCRACRU LNCRACRU = new LNCRACRU();
    LNCSPDQ LNCSPDQ = new LNCSPDQ();
    CICACCU CICACCU = new CICACCU();
    LNCBALLM LNCBALLM = new LNCBALLM();
    LNCIGVCY LNCIGVCY = new LNCIGVCY();
    SCCGWA SCCGWA;
    LNCSINTA LNCUINTA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA, LNCSINTA LNCUINTA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCUINTA = LNCUINTA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZUINTA return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCUINTA.FUNC);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        CEP.TRC(SCCGWA, LNCUINTA.CTA_NO);
        IBS.init(SCCGWA, LNRICTL);
        IBS.init(SCCGWA, LNCRICTL);
        LNRICTL.KEY.CONTRACT_NO = LNCUINTA.CTA_NO;
        LNRICTL.KEY.SUB_CTA_NO = LNCUINTA.SUB_CTA_NO;
        LNCRICTL.FUNC = 'I';
        S000_CALL_LNZRICTL();
        if (pgmRtn) return;
        if (WS_CNT_ICTL == 'Y') {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRICTL.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(47 - 1, 47 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.ERR_LOAN_CANCELLED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
            || LNRICTL.CTL_STSW.substring(57 - 1, 57 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LOAN_STS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
            || LNRICTL.CTL_STSW.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1") 
            || LNRICTL.CTL_STSW.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1") 
            || LNRICTL.CTL_STSW.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1") 
            || LNRICTL.CTL_STSW.substring(13 - 1, 13 + 1 - 1).equalsIgnoreCase("1") 
            || LNRICTL.CTL_STSW.substring(14 - 1, 14 + 1 - 1).equalsIgnoreCase("1") 
            || LNRICTL.CTL_STSW.substring(51 - 1, 51 + 1 - 1).equalsIgnoreCase("1") 
            || LNRICTL.CTL_STSW.substring(53 - 1, 53 + 1 - 1).equalsIgnoreCase("1") 
            || LNRICTL.CTL_STSW.substring(54 - 1, 54 + 1 - 1).equalsIgnoreCase("1") 
            || LNRICTL.CTL_STSW.substring(55 - 1, 55 + 1 - 1).equalsIgnoreCase("1") 
            || LNRICTL.CTL_STSW.substring(71 - 1, 71 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_0433;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCUINTA.PAY_TYP == 'C' 
            && LNCUINTA.ADJ_AMT != 0) {
            if (LNCUINTA.PAY_TERM == 0) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_PERD_INVALID;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (LNCUINTA.FUNC == 'I') {
            B010_CHECK_INPUT();
            if (pgmRtn) return;
            B010_MAIN_PROC();
            if (pgmRtn) return;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            if (JIBS_tmp_str[0].equalsIgnoreCase("136040")) {
                C000_GET_OVE_AMT();
                if (pgmRtn) return;
            }
        } else if (LNCUINTA.FUNC == 'Q') {
            B020_CHECK_INPUT();
            if (pgmRtn) return;
            B021_MAIN_PROC();
            if (pgmRtn) return;
        } else if (LNCUINTA.FUNC == 'M'
            || LNCUINTA.FUNC == 'P') {
            B030_CHECK_INPUT();
            if (pgmRtn) return;
            B030_MAIN_PROC();
            if (pgmRtn) return;
        } else if (LNCUINTA.FUNC == 'O') {
            B030_CHECK_INPUT();
            if (pgmRtn) return;
            B040_MAIN_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCUINTA.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        CEP.TRC(SCCGWA, LNCUINTA.FUNC);
        CEP.TRC(SCCGWA, LNCUINTA.CTA_NO);
        CEP.TRC(SCCGWA, LNCUINTA.CI_NO);
        CEP.TRC(SCCGWA, LNCUINTA.CI_ENMS);
        CEP.TRC(SCCGWA, LNCUINTA.CI_CNM);
        CEP.TRC(SCCGWA, LNCUINTA.PRIN);
        CEP.TRC(SCCGWA, LNCUINTA.OSBAL);
        CEP.TRC(SCCGWA, LNCUINTA.CCY);
        CEP.TRC(SCCGWA, LNCUINTA.STS);
        CEP.TRC(SCCGWA, LNCUINTA.PAY_TYP);
        CEP.TRC(SCCGWA, LNCUINTA.PAY_TERM);
        CEP.TRC(SCCGWA, LNCUINTA.VAL_DT);
        CEP.TRC(SCCGWA, LNCUINTA.DUE_DT);
        CEP.TRC(SCCGWA, LNCUINTA.INT);
        CEP.TRC(SCCGWA, LNCUINTA.OVE_AMTS);
        CEP.TRC(SCCGWA, LNCUINTA.LVE_AMTS);
        CEP.TRC(SCCGWA, LNCUINTA.ADJ_AMT);
        CEP.TRC(SCCGWA, LNCUINTA.TOT_INT);
        CEP.TRC(SCCGWA, LNCUINTA.ADJ_AMTS);
        CEP.TRC(SCCGWA, LNCUINTA.TOT_L_INT);
        CEP.TRC(SCCGWA, LNCUINTA.RSN);
        CEP.TRC(SCCGWA, LNCUINTA.SEQ);
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNCUINTA.CTA_NO.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_CONT_NO_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCAPRDM);
        LNCAPRDM.FUNC = '3';
        LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = LNCUINTA.CTA_NO;
        S000_CALL_LNZAPRDM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCAPRDM.REC_DATA.PAY_MTH);
        if (LNCAPRDM.REC_DATA.PAY_MTH == '4') {
            LNCUINTA.PAY_TYP = 'C';
        }
        if (LNCAPRDM.REC_DATA.PAY_MTH == '4') {
            IBS.init(SCCGWA, LNRPAIP);
            IBS.init(SCCGWA, LNCRPAIP);
            LNRPAIP.KEY.CONTRACT_NO = LNCUINTA.CTA_NO;
            LNRPAIP.KEY.SUB_CTA_NO = LNCUINTA.SUB_CTA_NO;
            LNRPAIP.KEY.PHS_NO = LNRICTL.IC_CAL_PHS_NO;
            LNCRPAIP.FUNC = 'I';
            LNCRPAIP.REC_PTR = LNRPAIP;
            LNCRPAIP.REC_LEN = 200;
            S000_CALL_LNZRPAIP();
            if (pgmRtn) return;
        }
        if ((LNCUINTA.PAY_TERM > LNRICTL.IC_CAL_TERM) 
            || (LNCUINTA.PAY_TYP == 'C' 
            && LNCUINTA.PAY_TERM > LNRPAIP.PHS_TOT_TERM)) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN5720;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCUINTA.PAY_TYP);
    }
    public void B020_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNCUINTA.CTA_NO.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_CONT_NO_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCCONTM);
        LNCCONTM.FUNC = '3';
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCUINTA.CTA_NO;
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
    }
    public void B030_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNCUINTA.CTA_NO.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_CONT_NO_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCUINTA.INT);
        if (LNCUINTA.PAY_TYP == ' ') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_INPUT_PAY_TYPE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCCONTM);
        LNCCONTM.FUNC = '3';
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCUINTA.CTA_NO;
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
        WS_MAT_DATE = LNCCONTM.REC_DATA.MAT_DATE;
        WS_ORI_MAT_DT = LNCCONTM.REC_DATA.ORI_MAT_DATE;
        IBS.init(SCCGWA, LNCAPRDM);
        LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = LNCUINTA.CTA_NO;
        LNCAPRDM.FUNC = '3';
        S000_CALL_LNZAPRDM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "000000 PAY-MTH");
        CEP.TRC(SCCGWA, LNCAPRDM.REC_DATA.PAY_MTH);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (LNCAPRDM.REC_DATA.PAY_MTH == '4' 
            && LNCUINTA.PAY_TYP != 'C' 
            && LNCUINTA.FUNC == 'O' 
            && JIBS_tmp_str[1].equalsIgnoreCase("136040")) {
            LNCUINTA.PAY_TYP = 'C';
        }
        CEP.TRC(SCCGWA, LNCUINTA.PAY_TERM);
        CEP.TRC(SCCGWA, LNRICTL.IC_CAL_TERM);
        CEP.TRC(SCCGWA, LNRICTL.IC_CUR_TERM);
        if (LNCUINTA.PAY_TERM == 0) {
            CEP.TRC(SCCGWA, LNCUINTA.PAY_TERM);
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCUINTA.FUNC == 'O' 
            && LNCUINTA.PAY_TERM >= LNRICTL.IC_CAL_TERM) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_PAY_TERM;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCUINTA.PAY_TERM);
        if (LNCUINTA.FUNC == 'P' 
            && LNCUINTA.SUB_CTA_NO == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCUINTA.FUNC == 'M') {
            if (LNCUINTA.PAY_TYP != ' ') {
                if (LNCAPRDM.REC_DATA.PAY_MTH != '4') {
                    if (SCCGWA.COMM_AREA.TR_ID.TR_CODE != 6040) {
                        LNCUINTA.PAY_TYP = 'I';
                    }
                } else {
                    LNCUINTA.PAY_TYP = 'C';
                }
            }
            IBS.init(SCCGWA, LNRRCVD);
            IBS.init(SCCGWA, LNCRRCVD);
            LNRRCVD.KEY.CONTRACT_NO = LNCUINTA.CTA_NO;
            LNRRCVD.KEY.SUB_CTA_NO = LNCUINTA.SUB_CTA_NO;
            LNRRCVD.KEY.AMT_TYP = LNCUINTA.PAY_TYP;
            LNRRCVD.KEY.TERM = LNCUINTA.PAY_TERM;
            LNCRRCVD.FUNC = 'I';
            S000_CALL_LNZRRCVD();
            if (pgmRtn) return;
            WS_YIJIE_AMT = LNRRCVD.I_REC_AMT;
            if (WS_CNT_RCVD == 'Y') {
                IBS.init(SCCGWA, LNRPLPI);
                LNRPLPI.KEY.CONTRACT_NO = LNCUINTA.CTA_NO;
                LNRPLPI.KEY.SUB_CTA_NO = LNCUINTA.SUB_CTA_NO;
                LNRPLPI.KEY.REPY_MTH = LNCUINTA.PAY_TYP;
                LNRPLPI.KEY.TERM = LNCUINTA.PAY_TERM;
                T000_READ_LNTPLPI();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, LNRPLPI.KEY.TERM);
                IBS.init(SCCGWA, LNCICUT);
                LNCICUT.COMM_DATA.FUNC_CODE = 'I';
                LNCICUT.COMM_DATA.LN_AC = LNRPLPI.KEY.CONTRACT_NO;
                LNCICUT.COMM_DATA.SUF_NO = "" + 0;
                JIBS_tmp_int = LNCICUT.COMM_DATA.SUF_NO.length();
                for (int i=0;i<0-JIBS_tmp_int;i++) LNCICUT.COMM_DATA.SUF_NO = "0" + LNCICUT.COMM_DATA.SUF_NO;
                LNCICUT.COMM_DATA.TYPE = 'I';
                LNCICUT.COMM_DATA.BEG_DATE = LNRPLPI.VAL_DT;
                LNCICUT.COMM_DATA.TERM = LNCUINTA.PAY_TERM;
                CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.TERM);
                if (LNRPLPI.DUE_DT < SCCGWA.COMM_AREA.AC_DATE) {
                    LNCICUT.COMM_DATA.END_DATE = LNRPLPI.DUE_DT;
                } else {
                    LNCICUT.COMM_DATA.END_DATE = SCCGWA.COMM_AREA.AC_DATE;
                }
                S000_CALL_LNZICUT();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.INT_AMT);
                LNCUINTA.INT = LNCICUT.COMM_DATA.INT_AMT;
                WS_AMT_CNT = LNCUINTA.INT + LNCUINTA.ADJ_AMT;
                CEP.TRC(SCCGWA, WS_AMT_CNT);
                CEP.TRC(SCCGWA, LNCUINTA.ADJ_AMT);
                if (WS_AMT_CNT < 0) {
                    WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_NOT_LESS_ZERO;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (LNCAPRDM.REC_DATA.PAY_MTH == '0') {
                WS_PREC_FLG = 'Y';
                if (LNCCONTM.REC_DATA.MAT_DATE == LNCCONTM.REC_DATA.ORI_MAT_DATE) {
                    WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_YSX_LOAN;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (LNCUINTA.PAY_TYP == 'C') {
                if (SCCGWA.COMM_AREA.TR_ID.TR_CODE != 6040) {
                    LNCUINTA.LC_TYP = 'I';
                }
            }
        }
        if (LNCUINTA.FUNC == 'O') {
            CEP.TRC(SCCGWA, LNCCONTM.REC_DATA.CONTRACT_TYPE);
            CEP.TRC(SCCGWA, LNCUINTA.ADJ_L_AMT);
            CEP.TRC(SCCGWA, LNCUINTA.PAY_TYP);
            CEP.TRC(SCCGWA, LNCUINTA.ADJ_AMT);
            if (LNCAPRDM.REC_DATA.PAY_MTH == '0') {
                if (LNCUINTA.ADJ_L_AMT != 0 
                    && LNCAPRDM.REC_DATA.OCAL_PERD == 0) {
                    WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_CLDL_NOT_RATE;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (LNCUINTA.PAY_TYP != 'I' 
                && LNCUINTA.PAY_TYP != 'P' 
                && LNCUINTA.PAY_TYP != 'C') {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_PAY_TYPE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, LNRRCVD);
            IBS.init(SCCGWA, LNCRRCVD);
            LNRRCVD.KEY.CONTRACT_NO = LNCUINTA.CTA_NO;
            LNRRCVD.KEY.SUB_CTA_NO = LNCUINTA.SUB_CTA_NO;
            LNRRCVD.KEY.AMT_TYP = LNCUINTA.PAY_TYP;
            LNRRCVD.KEY.TERM = LNCUINTA.PAY_TERM;
            CEP.TRC(SCCGWA, LNCUINTA.PAY_TERM);
            CEP.TRC(SCCGWA, LNRRCVD.KEY.TERM);
            LNCRRCVD.FUNC = 'I';
            S000_CALL_LNZRRCVD();
            if (pgmRtn) return;
            WS_YIJIEFULI_AMT = LNRRCVD.L_LST_CAL_AMT;
            if (WS_CNT_RCVD != 'Y' 
                && LNRRCVD.REPY_STS == '2') {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_TA_TERM;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                if (WS_CNT_RCVD == 'Y') {
                    WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRRCVD.RC);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (SCCGWA.COMM_AREA.AC_DATE <= LNRRCVD.DUE_DT) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MISS_MATURED;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (LNCUINTA.PAY_TYP == 'C') {
                if (LNCUINTA.ADJ_AMT == 0 
                    && LNCUINTA.ADJ_L_AMT == 0) {
                    WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_SPACE_AMT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                    if (LNCUINTA.ADJ_AMT != 0) {
                        R000_O_ADJ_AMT_CHK();
                        if (pgmRtn) return;
                    }
                    if (LNCUINTA.ADJ_L_AMT == 0) {
                        R000_L_ADJ_AMT_CHK();
                        if (pgmRtn) return;
                    }
                    if (LNCUINTA.ADJ_AMT != 0 
                        && LNCUINTA.ADJ_L_AMT != 0) {
                        R000_O_ADJ_AMT_CHK();
                        if (pgmRtn) return;
                        R000_L_ADJ_AMT_CHK();
                        if (pgmRtn) return;
                    }
                }
            }
            if (LNCUINTA.PAY_TYP == 'P') {
                if (LNCUINTA.ADJ_AMT == 0) {
                    WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_AMT_INPUT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (LNCUINTA.ADJ_L_AMT != 0) {
                    WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_TYP_RATE;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                R000_O_ADJ_AMT_CHK();
                if (pgmRtn) return;
            }
            if (LNCUINTA.PAY_TYP == 'I') {
                if (LNCUINTA.ADJ_AMT != 0) {
                    WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_TYP_RATE;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (LNCUINTA.ADJ_L_AMT == 0) {
                    WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_AMT_INPUT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                R000_L_ADJ_AMT_CHK();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPLPI);
        IBS.init(SCCGWA, LNCRPLPI);
        LNRPLPI.KEY.CONTRACT_NO = LNCUINTA.CTA_NO;
        LNRPLPI.KEY.SUB_CTA_NO = LNCUINTA.SUB_CTA_NO;
        LNRPLPI.KEY.REPY_MTH = LNCUINTA.PAY_TYP;
        LNRPLPI.KEY.TERM = LNCUINTA.PAY_TERM;
        CEP.TRC(SCCGWA, LNCUINTA.CTA_NO);
        CEP.TRC(SCCGWA, LNCUINTA.PAY_TYP);
        CEP.TRC(SCCGWA, LNRPLPI.KEY.REPY_MTH);
        CEP.TRC(SCCGWA, LNCUINTA.PAY_TERM);
        LNCRPLPI.FUNC = 'I';
        S000_CALL_LNZRPLPI();
        if (pgmRtn) return;
        if (WS_CNT_PLPI == 'Y') {
            IBS.init(SCCGWA, LNCFCPL);
            if (LNCUINTA.PAY_TYP == 'C') {
                LNCFCPL.COMM_DATA.PAY_MTH = '4';
            } else {
                LNCFCPL.COMM_DATA.PAY_MTH = '2';
            }
            LNCFCPL.COMM_DATA.CTA_NO = LNCUINTA.CTA_NO;
            S000_CALL_LNZFCPL();
            if (pgmRtn) return;
            IBS.init(SCCGWA, LNRPLPI);
            IBS.init(SCCGWA, LNCRPLPI);
            LNRPLPI.KEY.CONTRACT_NO = LNCUINTA.CTA_NO;
            LNRPLPI.KEY.SUB_CTA_NO = LNCUINTA.SUB_CTA_NO;
            LNRPLPI.KEY.REPY_MTH = LNCUINTA.PAY_TYP;
            LNRPLPI.KEY.TERM = LNCUINTA.PAY_TERM;
            LNCRPLPI.FUNC = 'I';
            S000_CALL_LNZRPLPI();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_CNT_PLPI);
            if (WS_CNT_PLPI == 'Y') {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRPLPI.RC);
                CEP.TRC(SCCGWA, WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        LNCUINTA.VAL_DT = LNRPLPI.VAL_DT;
        LNCUINTA.DUE_DT = LNRPLPI.DUE_DT;
        CEP.TRC(SCCGWA, LNCUINTA.VAL_DT);
        CEP.TRC(SCCGWA, LNCUINTA.DUE_DT);
        if (LNRPLPI.REC_STS == '1') {
            C000_READ_LNTRCVD_I();
            if (pgmRtn) return;
            C000_READ_LNTRCVD_P();
            if (pgmRtn) return;
            C000_READ_LNTRCVD_C();
            if (pgmRtn) return;
        } else {
            C000_CALL_LNZICUT();
            if (pgmRtn) return;
        }
        if (WS_OUTPUT_LIST.WS_CNT_PAY1 != 'Y') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_WRD_PLS_CNG_PLPI;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        C000_READ_LNTINTA();
        if (pgmRtn) return;
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        WS_PAY_MTH = LNCICIQ.DATA.PAY_MTH;
        CEP.TRC(SCCGWA, WS_PAY_MTH);
        if (LNCUINTA.FUNC == 'Q') {
            IBS.init(SCCGWA, LNRPLPI);
            LNRPLPI.KEY.CONTRACT_NO = LNCUINTA.CTA_NO;
            LNRPLPI.KEY.SUB_CTA_NO = LNCUINTA.SUB_CTA_NO;
            if (WS_PAY_MTH == '4') {
                LNRPLPI.KEY.REPY_MTH = 'C';
            } else {
                LNRPLPI.KEY.REPY_MTH = 'I';
            }
            LNRPLPI.KEY.TERM = LNRICTL.IC_CAL_TERM;
            T000_READ_LNTPLPI();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, LNRPLPI.KEY.TERM);
            IBS.init(SCCGWA, LNCICUT);
            LNCICUT.COMM_DATA.FUNC_CODE = 'I';
            LNCICUT.COMM_DATA.LN_AC = LNRICTL.KEY.CONTRACT_NO;
            LNCICUT.COMM_DATA.SUF_NO = "" + 0;
            JIBS_tmp_int = LNCICUT.COMM_DATA.SUF_NO.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) LNCICUT.COMM_DATA.SUF_NO = "0" + LNCICUT.COMM_DATA.SUF_NO;
            LNCICUT.COMM_DATA.TYPE = 'I';
            LNCICUT.COMM_DATA.BEG_DATE = LNRPLPI.VAL_DT;
            LNCUINTA.VAL_DT = LNRPLPI.VAL_DT;
            LNCICUT.COMM_DATA.END_DATE = LNRPLPI.DUE_DT;
            LNCUINTA.DUE_DT = LNRPLPI.DUE_DT;
            S000_CALL_LNZICUT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, LNCICUT.COMM_DATA.INT_AMT);
            if (WS_PAY_MTH == '4') {
                IBS.init(SCCGWA, LNCPAIPM);
                LNCPAIPM.REC_DATA.KEY.CONTRACT_NO = LNCUINTA.CTA_NO;
                LNCPAIPM.REC_DATA.KEY.SUB_CTA_NO = 0;
                LNCPAIPM.REC_DATA.KEY.PHS_NO = LNRICTL.IC_CAL_PHS_NO;
                CEP.TRC(SCCGWA, LNCPAIPM.REC_DATA.KEY.CONTRACT_NO);
                CEP.TRC(SCCGWA, LNCPAIPM.REC_DATA.KEY.SUB_CTA_NO);
                CEP.TRC(SCCGWA, LNCPAIPM.REC_DATA.KEY.PHS_NO);
                LNCPAIPM.FUNC = '3';
                S000_CALL_LNZPAIPM();
                if (pgmRtn) return;
                LNCUINTA.D_PRIN = LNCPAIPM.REC_DATA.CUR_INST_AMT - LNCICUT.COMM_DATA.INT_AMT;
            } else {
                LNCUINTA.D_PRIN = 0;
            }
            LNCUINTA.D_INT = LNCUINTA.D_PRIN + LNCICUT.COMM_DATA.INT_AMT;
            LNCUINTA.INT = LNCICUT.COMM_DATA.INT_AMT;
            CEP.TRC(SCCGWA, LNCUINTA.D_INT);
            CEP.TRC(SCCGWA, LNCUINTA.D_PRIN);
        }
    }
    public void B021_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCAPRDM);
        LNCUINTA.OVE_AMTS = 0;
        LNCUINTA.LVE_AMTS = 0;
        LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = LNCUINTA.CTA_NO;
        LNCAPRDM.FUNC = '3';
        S000_CALL_LNZAPRDM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "111111 PAY-MTH");
        CEP.TRC(SCCGWA, LNCAPRDM.REC_DATA.PAY_MTH);
        IBS.init(SCCGWA, LNRICTL);
        IBS.init(SCCGWA, LNCRICTL);
        LNRICTL.KEY.CONTRACT_NO = LNCUINTA.CTA_NO;
        LNRICTL.KEY.SUB_CTA_NO = LNCUINTA.SUB_CTA_NO;
        CEP.TRC(SCCGWA, LNCUINTA.CTA_NO);
        CEP.TRC(SCCGWA, LNCUINTA.SUB_CTA_NO);
        LNCRICTL.FUNC = 'I';
        S000_CALL_LNZRICTL();
        if (pgmRtn) return;
        if (LNCAPRDM.REC_DATA.PAY_MTH == '4') {
            LNCUINTA.PAY_TYP = 'C';
            C020_GET_LCCM_AMT();
            if (pgmRtn) return;
            LNCUINTA.OVE_AMTS = LNCLCCM.COMM_DATA.O_AMT;
            LNCUINTA.LVE_AMTS = LNCLCCM.COMM_DATA.L_AMT;
        } else {
            LNCUINTA.PAY_TYP = 'P';
            C020_GET_LCCM_AMT();
            if (pgmRtn) return;
            LNCUINTA.OVE_AMTS = LNCLCCM.COMM_DATA.O_AMT;
            LNCUINTA.PAY_TYP = 'I';
            C020_GET_LCCM_AMT();
            if (pgmRtn) return;
            LNCUINTA.LVE_AMTS = LNCLCCM.COMM_DATA.L_AMT;
        }
    }
    public void C020_GET_LCCM_AMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCLCCM);
        LNCLCCM.COMM_DATA.FUNC_CODE = 'I';
        LNCLCCM.COMM_DATA.LN_AC = LNCUINTA.CTA_NO;
        LNCLCCM.COMM_DATA.SUF_NO = "" + LNCUINTA.SUB_CTA_NO;
        JIBS_tmp_int = LNCLCCM.COMM_DATA.SUF_NO.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) LNCLCCM.COMM_DATA.SUF_NO = "0" + LNCLCCM.COMM_DATA.SUF_NO;
        LNCLCCM.COMM_DATA.TYPE = LNCUINTA.PAY_TYP;
        LNCLCCM.COMM_DATA.TERM = LNCUINTA.PAY_TERM;
        CEP.TRC(SCCGWA, " ");
        CEP.TRC(SCCGWA, LNCUINTA.PAY_TERM);
        LNCLCCM.COMM_DATA.END_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBS.init(SCCGWA, LNRRCVD);
        IBS.init(SCCGWA, LNCRRCVD);
        LNRRCVD.KEY.CONTRACT_NO = LNCUINTA.CTA_NO;
        LNRRCVD.KEY.SUB_CTA_NO = LNCUINTA.SUB_CTA_NO;
        LNRRCVD.KEY.AMT_TYP = LNCUINTA.PAY_TYP;
        LNRRCVD.KEY.TERM = LNCLCCM.COMM_DATA.TERM;
        LNCRRCVD.FUNC = 'I';
        S000_CALL_LNZRRCVD();
        if (pgmRtn) return;
        if ((LNRRCVD.REPY_STS != '0' 
            && LNCUINTA.FUNC != 'O') 
            || (LNRRCVD.REPY_STS == '2' 
            && LNCUINTA.FUNC == 'O')) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_RCVD_STS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            S000_CALL_LNZLCCM();
            if (pgmRtn) return;
        }
    }
    public void B030_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPLPI);
        IBS.init(SCCGWA, LNCRPLPI);
        LNRPLPI.KEY.CONTRACT_NO = LNCUINTA.CTA_NO;
        LNRPLPI.KEY.SUB_CTA_NO = LNCUINTA.SUB_CTA_NO;
        LNRPLPI.KEY.REPY_MTH = LNCUINTA.PAY_TYP;
        LNRPLPI.KEY.TERM = LNCUINTA.PAY_TERM;
        CEP.TRC(SCCGWA, LNRPLPI.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRPLPI.KEY.SUB_CTA_NO);
        CEP.TRC(SCCGWA, LNRPLPI.KEY.REPY_MTH);
        CEP.TRC(SCCGWA, LNRPLPI.KEY.TERM);
        LNCRPLPI.FUNC = 'R';
        S000_CALL_LNZRPLPI();
        if (pgmRtn) return;
        LNCUINTA.VAL_DT = LNRPLPI.VAL_DT;
        LNCUINTA.DUE_DT = LNRPLPI.DUE_DT;
        if (LNRPLPI.VAL_DT > SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_PLPI_VAL_DT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        C000_QUERY_RCVD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNRPLPI.REC_STS);
        if (LNRPLPI.REC_STS == '1') {
            C000_QUERY_RCVD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, LNRRCVD.OVD_DT);
            WS_OVD_DT = LNRRCVD.OVD_DT;
            CEP.TRC(SCCGWA, WS_MAT_DATE);
            CEP.TRC(SCCGWA, WS_ORI_MAT_DT);
            CEP.TRC(SCCGWA, LNCUINTA.PAY_TYP);
            CEP.TRC(SCCGWA, LNCUINTA.PAY_TERM);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
            WS_VAL_DATE = LNCUINTA.VAL_DT;
            C000_CHECK_EPY();
            if (pgmRtn) return;
            C000_REV_CAL_PROC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "B030-MAIN PROC C000-WRITE-DATA START");
            C000_WRITE_DATA();
            if (pgmRtn) return;
            C000_UPDATE_PLPI();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "B030-MAIN PROC C000-UPDATE-PLPI OVER");
            C000_INT_CAL_PROC();
            if (pgmRtn) return;
            C000_UPDATE_RCVD_DATA();
            if (pgmRtn) return;
            C000_UPDATE_RCVD_STS();
            if (pgmRtn) return;
            IBS.init(SCCGWA, LNCBALLM);
            LNCBALLM.FUNC = '4';
            LNCBALLM.REC_DATA.KEY.CONTRACT_NO = LNCUINTA.CTA_NO;
            LNCBALLM.REC_DATA.KEY.SUB_CTA_NO = LNCUINTA.SUB_CTA_NO;
            S000_CALL_LNZBALLM();
            if (pgmRtn) return;
            LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[20-1].LOAN_BAL = LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[20-1].LOAN_BAL + LNCUINTA.ADJ_AMT;
            LNCBALLM.FUNC = '2';
            S000_CALL_LNZBALLM();
            if (pgmRtn) return;
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.ENTY_TYP = '1';
            CICACCU.DATA.AGR_NO = LNCUINTA.CTA_NO;
            S000_CALL_CIZACCU();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
            IBS.init(SCCGWA, LNCIGVCY);
            LNCIGVCY.DATA.EVENT_CODE = "IA";
            CEP.TRC(SCCGWA, LNRICTL.CTL_STSW);
            if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
            JIBS_tmp_int = LNRICTL.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
            if (LNRICTL.CTL_STSW.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("0")) {
                if (LNCUINTA.ADJ_AMT > 0) {
                    LNCIGVCY.DATA.AMT_INFO[25-1].AMT = LNCUINTA.ADJ_AMT;
                } else {
                    LNCIGVCY.DATA.AMT_INFO[26-1].AMT = LNCUINTA.ADJ_AMT;
                }
            } else {
                if (LNCUINTA.ADJ_AMT > 0) {
                    LNCIGVCY.DATA.AMT_INFO[29-1].AMT = LNCUINTA.ADJ_AMT;
                } else {
                    LNCIGVCY.DATA.AMT_INFO[30-1].AMT = LNCUINTA.ADJ_AMT;
                }
            }
            LNCIGVCY.DATA.CNTR_TYPE = LNCCONTM.REC_DATA.CONTRACT_TYPE;
            LNCIGVCY.DATA.PROD_CODE_OLD = LNCCONTM.REC_DATA.PROD_CD;
            LNCIGVCY.DATA.CTA_NO = LNCUINTA.CTA_NO;
            LNCIGVCY.DATA.SUB_CTA_NO = LNCUINTA.SUB_CTA_NO;
            LNCIGVCY.DATA.BR_OLD = LNCCONTM.REC_DATA.BOOK_BR;
            LNCIGVCY.DATA.CCY_INFO[1-1].CCY = LNCCONTM.REC_DATA.CCY;
            LNCIGVCY.DATA.VALUE_DATE = LNRPLPI.DUE_DT;
            LNCIGVCY.DATA.CI_NO = CICACCU.DATA.CI_NO;
            LNCIGVCY.DATA.STATUS = LNRICTL.CTL_STSW;
            S000_CALL_LNZIGVCY();
            if (pgmRtn) return;
        } else {
            C000_WRITE_DATA();
            if (pgmRtn) return;
        }
        if (LNCUINTA.FUNC != 'P') {
            if (LNCUINTA.PAY_TYP == 'I') {
                R000_WRITE_HIS_PROC();
                if (pgmRtn) return;
            } else {
                R000_WRITE_HIS_PROC_1();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_LNZBALLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-BALL-MAINT", LNCBALLM);
        if (LNCBALLM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCBALLM.RC);
