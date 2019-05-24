package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.BP.*;
import com.hisun.TD.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSDDTD {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    brParm DDTDDTD_BR = new brParm();
    DBParm DDTDDTD_RD;
    DBParm DDTINF_RD;
    DBParm DDTMST_RD;
    DBParm DDTCCY_RD;
    boolean pgmRtn = false;
    String K_OUTPUT_FMT2 = "DD132";
    String K_OUTPUT_FMT3 = "DD133";
    String K_OUTPUT_FMT4 = "DD134";
    String K_OUTPUT_FMT5 = "DD135";
    String CPN_I_INQ_DDPRD = "DD-I-INQ-DDPRD";
    String K_STS_TABLE_APP = "DD";
    String K_CHK_STS_TBL = "5133";
    String K_HIS_COPYBOOK_NAME = "DDCSDDTD";
    String K_HIS_REMARKS = "ACTIVATION ACCOUNT";
    String WS_ERR_MSG = " ";
    char WS_FUNC = ' ';
    DDZSDDTD_WS_REF_NO WS_REF_NO = new DDZSDDTD_WS_REF_NO();
    char WS_STOP_FLG = ' ';
    DDZSDDTD_WS_LIST WS_LIST = new DDZSDDTD_WS_LIST();
    DDZSDDTD_WS_SHOW2 WS_SHOW2 = new DDZSDDTD_WS_SHOW2();
    DDZSDDTD_WS_SHOW3 WS_SHOW3 = new DDZSDDTD_WS_SHOW3();
    DDZSDDTD_WS_SHOW4 WS_SHOW4 = new DDZSDDTD_WS_SHOW4();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DDCSCINM DDCSCINM = new DDCSCINM();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    BPCPGDIN BPCPGDIN = new BPCPGDIN();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    DDVMPRD DDVMPRD = new DDVMPRD();
    TDCTZZC TDCTZZC = new TDCTZZC();
    TDCKHCR TDCKHCR = new TDCKHCR();
    TDCTDDR TDCTDDR = new TDCTDDR();
    TDCTZZD TDCTZZD = new TDCTZZD();
    TDCACE TDCACE = new TDCACE();
    CICACCU CICACCU = new CICACCU();
    DDCSRATE DDCSRATE = new DDCSRATE();
    CICQACAC CICQACAC = new CICQACAC();
    CICMAGT CICMAGT = new CICMAGT();
    DDRDDTD DDRDDTD = new DDRDDTD();
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    DDRINF DDRINF = new DDRINF();
    SCCGWA SCCGWA;
    DDCSDDTD DDCSDDTD;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA, DDCSDDTD DDCSDDTD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSDDTD = DDCSDDTD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSDDTD return!");
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
        WS_FUNC = DDCSDDTD.FUNC;
        CEP.TRC(SCCGWA, DDCSDDTD.AC);
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDCSDDTD.AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCSDDTD.AC;
        CEP.TRC(SCCGWA, DDRMST.KEY.CUS_AC);
        T000_READ_DDTMST();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = DDCSDDTD.AC;
        CICQACAC.DATA.CCY_ACAC = DDCSDDTD.CCY;
        CICQACAC.DATA.CR_FLG = DDCSDDTD.CCY_TYPE;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDRCCY);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READ_DDTCCY();
        if (pgmRtn) return;
        if (WS_FUNC == 'A' 
            || WS_FUNC == 'C' 
            || WS_FUNC == 'D') {
            B040_CHECK_STS_TBL_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDRMST.OWNER_BRDP);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        if (DDRCCY.OWNER_BRDP != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH 
            && (WS_FUNC == 'A' 
            || WS_FUNC == 'C' 
            || WS_FUNC == 'D')) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BRANCH_NOT_SAME;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_FUNC == 'I') {
            B021_INIT_OUTPUT_FOR_INQ();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDCSDDTD.PROL_NO);
            if (DDCSDDTD.PROL_NO.trim().length() > 0) {
                R000_BEGIN_MPAGE_OUTPUT();
                if (pgmRtn) return;
                IBS.init(SCCGWA, DDRDDTD);
                DDRDDTD.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
                DDRDDTD.REF_NO = DDCSDDTD.PROL_NO;
                T000_READ_DDTDDTD_TBL_INQ();
                if (pgmRtn) return;
                B020_FORMAT_OUTPUT_DATA_INQ();
                if (pgmRtn) return;
                R000_TRANS_DATA_MPAGE_OUTPUT();
                if (pgmRtn) return;
            } else {
                IBS.init(SCCGWA, DDRDDTD);
                DDRDDTD.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
                T000_STBR_DDTDDTD();
                if (pgmRtn) return;
                T000_READNEXT_DDTDDTD();
                if (pgmRtn) return;
                R000_BEGIN_MPAGE_OUTPUT();
                if (pgmRtn) return;
                while (WS_STOP_FLG != 'Y') {
                    B020_FORMAT_OUTPUT_DATA_INQ();
                    if (pgmRtn) return;
                    R000_TRANS_DATA_MPAGE_OUTPUT();
                    if (pgmRtn) return;
                    T000_READNEXT_DDTDDTD();
                    if (pgmRtn) return;
                    B021_INIT_OUTPUT_FOR_INQ();
                    if (pgmRtn) return;
                }
                T000_ENDBR_DDTDDTD();
                if (pgmRtn) return;
            }
        } else if (WS_FUNC == 'B') {
        } else if (WS_FUNC == 'A') {
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                CEP.TRC(SCCGWA, "GWA-CANCEL-YES");
                IBS.init(SCCGWA, DDRDDTD);
                DDRDDTD.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
                CEP.TRC(SCCGWA, DDRDDTD.KEY.AC);
                DDRDDTD.REF_NO = DDCSDDTD.PROL_NO;
                CEP.TRC(SCCGWA, DDRDDTD.REF_NO);
                T000_READ_UPDATE_DDTDDTD_TBL2();
                if (pgmRtn) return;
                IBS.init(SCCGWA, TDCACE);
                TDCACE.PAGE_INF.A_ACO_AC = DDRDDTD.KEY.CON_NO;
                CEP.TRC(SCCGWA, TDCACE.PAGE_INF.A_ACO_AC);
                S000_CALL_TDZACE();
                if (pgmRtn) return;
                B040_ADD_NEW_CONT();
                if (pgmRtn) return;
                T000_DELETE_DDTDDTD();
                if (pgmRtn) return;
            } else {
                CEP.TRC(SCCGWA, "GWA-CANCEL-NO");
                B031_CHECK_AC_TYPE();
                if (pgmRtn) return;
                B040_ADD_NEW_CONT();
                if (pgmRtn) return;
                B050_ADD_NEW_PROL();
                if (pgmRtn) return;
                B060_FORMAT_OUTPUT_DATA_ADD();
                if (pgmRtn) return;
            }
        } else if (WS_FUNC == 'C') {
            B065_CHECK_TD_STATUS();
            if (pgmRtn) return;
            IBS.init(SCCGWA, DDRDDTD);
            DDRDDTD.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            DDRDDTD.KEY.CON_NO = DDCSDDTD.CON_NO;
            DDRDDTD.REF_NO = DDCSDDTD.PROL_NO;
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
            CEP.TRC(SCCGWA, DDCSDDTD.CON_NO);
            CEP.TRC(SCCGWA, DDCSDDTD.PROL_NO);
            T000_READ_UPDATE_DDTDDTD_TBL();
            if (pgmRtn) return;
            DDRDDTD.PAND_RATE_TYPE = DDCSDDTD.B_RAT_TP;
            DDRDDTD.PAND_RATE_TERM = DDCSDDTD.B_RAT_TM;
            DDRDDTD.PAND_SPRD_TYPE = DDCSDDTD.BF_TYPE;
            DDRDDTD.PAND_SPRD = DDCSDDTD.BF_SPRD;
            DDRDDTD.PAND_SPRD_PCT = DDCSDDTD.BF_PCNT;
            DDRDDTD.PAND_FIX_RATE = DDCSDDTD.BP_RATE;
            DDRDDTD.PAND_FIN_RATE = DDCSDDTD.BRK_RATE;
            DDRDDTD.REMARKS = DDCSDDTD.REMARKS;
            T000_REWRITE_DDTDDTD_TBL_CHG();
            if (pgmRtn) return;
            B070_FORMAT_OUTPUT_DATA_CHG();
            if (pgmRtn) return;
        } else if (WS_FUNC == 'D') {
            IBS.init(SCCGWA, DDRDDTD);
            DDRDDTD.KEY.AC = DDRCCY.KEY.AC;
            DDRDDTD.REF_NO = DDCSDDTD.PROL_NO;
            CEP.TRC(SCCGWA, DDRDDTD.KEY.AC);
            CEP.TRC(SCCGWA, DDRDDTD.REF_NO);
            T000_READ_DDTDDTD_TBL_INQ();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDRDDTD.KEY.CON_NO);
            IBS.init(SCCGWA, TDCACE);
            TDCACE.PAGE_INF.A_ACO_AC = DDRDDTD.KEY.CON_NO;
            S000_CALL_TDZACE();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, TDCACE.DATA[1-1].ACO_STS);
            if (TDCACE.DATA[1-1].ACO_STS == '1') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.TD_CON_IS_REDEMPTION);
            }
            CEP.TRC(SCCGWA, DDCSDDTD.W_AMT);
            CEP.TRC(SCCGWA, TDCACE.DATA[1-1].BAL);
            JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            if (DDCSDDTD.W_AMT > TDCACE.DATA[1-1].BAL 
                && !JIBS_tmp_str[1].equalsIgnoreCase("115139")) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AVL_BAL_NOT_ENOUGH);
            }
            B080_WITHDRAW_DD_CONT_SUZ();
            if (pgmRtn) return;
            B090_FORMAT_OUTPUT_DATA_DEL();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DDCSDDTD.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        if (SCCGWA.COMM_AREA.JRN_NO != 0 
            && (WS_FUNC != 'D')) {
            B170_NFIN_TX_HIS_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDCSDDTD.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B065_CHECK_TD_STATUS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCACE);
        TDCACE.PAGE_INF.A_ACO_AC = DDCSDDTD.CON_NO;
        CEP.TRC(SCCGWA, DDCSDDTD.CON_NO);
        S000_CALL_TDZACE();
        if (pgmRtn) return;
        if (TDCACE.DATA[1-1].ACO_STS == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.TD_CON_IS_REDEMPTION;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_FORMAT_OUTPUT_DATA_INQ() throws IOException,SQLException,Exception {
        WS_LIST.WS_LIST_AC = DDCSDDTD.AC;
        WS_LIST.WS_LIST_AC_CNM = DDCSDDTD.AC_CNM;
        WS_LIST.WS_LIST_CCY = DDRDDTD.CCY;
        WS_LIST.WS_LIST_CCY_TYPE = DDRDDTD.CCY_TYPE;
        WS_LIST.WS_LIST_PROL_DT = DDRDDTD.CRT_DATE;
        WS_LIST.WS_LIST_PROL_NO = DDRDDTD.REF_NO;
        CEP.TRC(SCCGWA, DDRDDTD.REF_NO);
        WS_LIST.WS_LIST_CON_NO = DDRDDTD.KEY.CON_NO;
        WS_LIST.WS_LIST_REMARKS = "                                                                                                                                                                                                                                                ";
        WS_LIST.WS_LIST_RATE = DDRDDTD.CON_FIN_RATE;
        WS_LIST.WS_LIST_BRK_RATE = DDRDDTD.PAND_FIN_RATE;
        WS_LIST.WS_LIST_RATE_TYP = DDRDDTD.CON_RATE_TYPE;
        WS_LIST.WS_LIST_RAT_TERM = DDRDDTD.CON_RATE_TERM;
        WS_LIST.WS_LIST_FLOAT_TP = DDRDDTD.CON_SPRD_TYPE;
        WS_LIST.WS_LIST_F_SPRD = DDRDDTD.CON_SPRD;
        WS_LIST.WS_LIST_F_PCNT = DDRDDTD.CON_SPRD_PCT;
        WS_LIST.WS_LIST_PMT_RATE = DDRDDTD.CON_FIX_RATE;
        WS_LIST.WS_LIST_B_RAT_TP = DDRDDTD.PAND_RATE_TYPE;
        WS_LIST.WS_LIST_B_RAT_TM = DDRDDTD.PAND_RATE_TERM;
        WS_LIST.WS_LIST_BF_TYPE = DDRDDTD.PAND_SPRD_TYPE;
        WS_LIST.WS_LIST_BF_SPRD = DDRDDTD.PAND_SPRD;
        WS_LIST.WS_LIST_BF_PCNT = DDRDDTD.PAND_SPRD_PCT;
        WS_LIST.WS_LIST_BP_RATE = DDRDDTD.PAND_FIX_RATE;
        WS_LIST.WS_LIST_REMARKS = DDRDDTD.REMARKS;
        if (DDRDDTD.PAND_FIX_RATE != 0) {
            WS_LIST.WS_LIST_BRK_RATE = DDRDDTD.PAND_FIX_RATE;
        }
        if (DDRDDTD.PAND_RATE_TYPE.trim().length() > 0 
            && DDRDDTD.PAND_RATE_TERM.trim().length() > 0) {
            IBS.init(SCCGWA, DDCSRATE);
            DDCSRATE.RATE_TYP = DDRDDTD.PAND_RATE_TYPE;
            DDCSRATE.RAT_TERM = DDRDDTD.PAND_RATE_TERM;
            DDCSRATE.FLOAT_TP = DDRDDTD.PAND_SPRD_TYPE;
            DDCSRATE.F_SPRD = DDRDDTD.PAND_SPRD;
            DDCSRATE.F_PCNT = DDRDDTD.PAND_SPRD_PCT;
            DDCSRATE.CCY = DDRDDTD.CCY;
            CEP.TRC(SCCGWA, DDRDDTD.PAND_RATE_TYPE);
            CEP.TRC(SCCGWA, DDRDDTD.PAND_RATE_TERM);
            CEP.TRC(SCCGWA, DDRDDTD.PAND_SPRD_TYPE);
            CEP.TRC(SCCGWA, DDRDDTD.PAND_SPRD);
            CEP.TRC(SCCGWA, DDRDDTD.PAND_SPRD_PCT);
            CEP.TRC(SCCGWA, DDCSRATE.RATE_TYP);
            CEP.TRC(SCCGWA, DDCSRATE.RAT_TERM);
            CEP.TRC(SCCGWA, DDCSRATE.FLOAT_TP);
            CEP.TRC(SCCGWA, DDCSRATE.F_SPRD);
            CEP.TRC(SCCGWA, DDCSRATE.F_PCNT);
            CEP.TRC(SCCGWA, DDCSRATE.CCY);
            S000_CALL_DDZSRATE();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDCSRATE.CON_RATE);
            WS_LIST.WS_LIST_BRK_RATE = DDCSRATE.CON_RATE;
        }
        IBS.init(SCCGWA, TDCACE);
        TDCACE.PAGE_INF.A_ACO_AC = DDRDDTD.KEY.CON_NO;
        S000_CALL_TDZACE();
        if (pgmRtn) return;
        WS_LIST.WS_LIST_PROL_AMT = TDCACE.DATA[1-1].PBAL;
        CEP.TRC(SCCGWA, WS_LIST.WS_LIST_PROL_AMT);
        WS_LIST.WS_LIST_PROL_BAL = TDCACE.DATA[1-1].BAL;
        CEP.TRC(SCCGWA, WS_LIST.WS_LIST_PROL_BAL);
        WS_LIST.WS_LIST_TENOR = TDCACE.DATA[1-1].TERM;
        WS_LIST.WS_LIST_VAL_DATE = TDCACE.DATA[1-1].SDT;
        WS_LIST.WS_LIST_MAT_DATE = TDCACE.DATA[1-1].DDT;
        WS_LIST.WS_LIST_PROL_STS = TDCACE.DATA[1-1].ACO_STS;
        WS_LIST.WS_LIST_PART_DATE = TDCACE.DATA[1-1].PART_DATE;
        WS_LIST.WS_LIST_CLO_DATE = TDCACE.DATA[1-1].CLO_DATE;
        CEP.TRC(SCCGWA, WS_LIST.WS_LIST_PART_DATE);
        CEP.TRC(SCCGWA, WS_LIST.WS_LIST_CLO_DATE);
        if (WS_LIST.WS_LIST_PROL_STS == '1') {
            WS_LIST.WS_LIST_PROL_BAL = 0;
        }
        WS_LIST.WS_LIST_JR_NNO = DDRDDTD.JR_NNO;
    }
    public void B040_CHECK_STS_TBL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFCSTS);
        BPCFCSTS.AP_MMO = K_STS_TABLE_APP;
        BPCFCSTS.TBL_NO = K_CHK_STS_TBL;
        if (CICACCU.DATA.CI_STSW == null) CICACCU.DATA.CI_STSW = "";
        JIBS_tmp_int = CICACCU.DATA.CI_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICACCU.DATA.CI_STSW += " ";
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        BPCFCSTS.STATUS_WORD = CICACCU.DATA.CI_STSW.substring(0, 80) + BPCFCSTS.STATUS_WORD.substring(80);
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 101 - 1) + DDRMST.AC_STS_WORD + BPCFCSTS.STATUS_WORD.substring(101 + 99 - 1);
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 201 - 1) + DDRCCY.STS_WORD + BPCFCSTS.STATUS_WORD.substring(201 + 99 - 1);
        CEP.TRC(SCCGWA, BPCFCSTS.AP_MMO);
        CEP.TRC(SCCGWA, BPCFCSTS.TBL_NO);
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_STSW);
        CEP.TRC(SCCGWA, DDRMST.AC_STS_WORD);
        CEP.TRC(SCCGWA, DDRCCY.STS_WORD);
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD);
        S000_CALL_BPZFCSTS();
        if (pgmRtn) return;
    }
    public void B021_INIT_OUTPUT_FOR_INQ() throws IOException,SQLException,Exception {
        WS_LIST.WS_LIST_AC = "                                ";
        WS_LIST.WS_LIST_AC_CNM = "                                                                                                                                                                                                                                                            ";
        WS_LIST.WS_LIST_CCY = "   ";
        WS_LIST.WS_LIST_CCY_TYPE = ' ';
        WS_LIST.WS_LIST_PROL_DT = 0;
        WS_LIST.WS_LIST_PROL_NO = "                    ";
        WS_LIST.WS_LIST_CON_NO = "                                ";
        WS_LIST.WS_LIST_REMARKS = "                                                                                                                                                                                                                                                ";
        WS_LIST.WS_LIST_RATE = 0;
        WS_LIST.WS_LIST_BRK_RATE = 0;
        WS_LIST.WS_LIST_RATE_TYP = "   ";
        WS_LIST.WS_LIST_RAT_TERM = "    ";
        WS_LIST.WS_LIST_FLOAT_TP = ' ';
        WS_LIST.WS_LIST_F_SPRD = 0;
        WS_LIST.WS_LIST_F_PCNT = 0;
        WS_LIST.WS_LIST_PMT_RATE = 0;
        WS_LIST.WS_LIST_B_RAT_TP = "   ";
        WS_LIST.WS_LIST_B_RAT_TM = "    ";
        WS_LIST.WS_LIST_BF_TYPE = ' ';
        WS_LIST.WS_LIST_BF_SPRD = 0;
        WS_LIST.WS_LIST_BF_PCNT = 0;
        WS_LIST.WS_LIST_BP_RATE = 0;
        WS_LIST.WS_LIST_PROL_AMT = 0;
        WS_LIST.WS_LIST_PROL_BAL = 0;
        WS_LIST.WS_LIST_TENOR = "    ";
        WS_LIST.WS_LIST_VAL_DATE = 0;
        WS_LIST.WS_LIST_MAT_DATE = 0;
        WS_LIST.WS_LIST_PROL_STS = ' ';
    }
    public void B031_CHECK_AC_TYPE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCSDDTD.AC;
        T000_READ_DDTMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCSDDTD.VAL_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (DDCSDDTD.VAL_DATE != SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_VAL_DT_MUST_BE_ACDT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B035_GEN_CI_AGT_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICMAGT);
        CICMAGT.FUNC = 'A';
        CICMAGT.DATA.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        CICMAGT.DATA.AGT_TYP = "IBS034";
        CICMAGT.DATA.AGT_STS = 'N';
        CICMAGT.DATA.ENTY_TYP = '1';
        CICMAGT.DATA.ENTY_NO = DDCSDDTD.AC;
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
    public void B035_CAN_CI_AGT_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICMAGT);
        CICMAGT.FUNC = 'D';
        CICMAGT.DATA.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        CICMAGT.DATA.AGT_TYP = "IBS034";
        CICMAGT.DATA.AGT_STS = 'C';
        CICMAGT.DATA.ENTY_TYP = '1';
        CICMAGT.DATA.ENTY_NO = DDCSDDTD.AC;
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
    public void B040_ADD_NEW_CONT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRINF);
        DDRINF.KEY.CUS_AC = DDCSDDTD.AC;
        T000_READ_DDTINF();
        if (pgmRtn) return;
        IBS.init(SCCGWA, TDCKHCR);
        if (DDCSDDTD.TENOR.equalsIgnoreCase("S000")) {
            TDCKHCR.PROD_CD = "9530000017";
            TDCKHCR.PRV_RAT = DDCSDDTD.BRK_RATE;
            CEP.TRC(SCCGWA, TDCKHCR.PRV_RAT);
        } else {
            TDCKHCR.PROD_CD = "9530000015";
        }
        TDCKHCR.CCY = DDCSDDTD.CCY;
        TDCKHCR.CCY_TYP = DDCSDDTD.CCY_TYPE;
        if (DDRMST.FRG_CODE.trim().length() == 0) TDCKHCR.FC_CD = 0;
        else TDCKHCR.FC_CD = Short.parseShort(DDRMST.FRG_CODE);
        TDCKHCR.TXN_AMT = DDCSDDTD.PROL_AMT;
        TDCKHCR.TERM = DDCSDDTD.TENOR;
        TDCKHCR.INSTR_MTH = '1';
        TDCKHCR.VAL_DT = DDCSDDTD.VAL_DATE;
        TDCKHCR.INT_RAT = DDCSDDTD.RATE;
        TDCKHCR.OPP_AC = DDCSDDTD.AC;
        TDCKHCR.AC = DDCSDDTD.AC;
        TDCKHCR.STL_INT_AC = DDCSDDTD.AC;
        CEP.TRC(SCCGWA, TDCKHCR.STL_INT_AC);
        TDCKHCR.BV_TYP = '0';
        TDCKHCR.ID_TYP = CICACCU.DATA.ID_TYPE;
        TDCKHCR.ID_NO = CICACCU.DATA.ID_NO;
        TDCKHCR.NAME = CICACCU.DATA.CI_CNM;
        TDCKHCR.OPT = '1';
        TDCKHCR.CT_FLG = '3';
        TDCKHCR.CALR_STD = "1";
        TDCKHCR.INT_SEL = '4';
        TDCKHCR.SHOW = '1';
        TDCKHCR.DH_FLG = DDCSDDTD.DH_FLG;
        TDCKHCR.EXP_DT = DDCSDDTD.EXP_DT;
        TDCKHCR.CALR_STD = DDCSDDTD.CALR_STD;
        TDCKHCR.REMARK = DDCSDDTD.REMARKS;
        TDCKHCR.DRAW_MTH = '4';
        TDCKHCR.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        TDCKHCR.AC_NAME = CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM;
        TDCKHCR.CROS_DR_FLG = '0';
        TDCKHCR.CROS_CR_FLG = '0';
        TDCKHCR.MON_TYP = DDRINF.AMT_TYPE;
        TDCKHCR.ACO_TYP = DDRINF.TXN_TYPE;
        TDCKHCR.MMO = "P408";
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CANCEL_IND);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            TDCKHCR.GACO_AC = TDCACE.PAGE_INF.A_ACO_AC;
            TDCKHCR.GAC_NO = TDCACE.PAGE_INF.AC_NO;
        }
        CEP.TRC(SCCGWA, TDCKHCR.GACO_AC);
        CEP.TRC(SCCGWA, TDCKHCR.GAC_NO);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO);
        IBS.init(SCCGWA, DDCSCINM);
        DDCSCINM.INPUT_DATA.CARD_NO = " ";
        DDCSCINM.INPUT_DATA.AC_NO = DDCSDDTD.AC;
        DDCSCINM.INPUT_DATA.FUNC = '2';
        S000_CALL_DDZSCINM();
        if (pgmRtn) return;
        S000_CALL_TDZKHCR();
        if (pgmRtn) return;
    }
    public void B050_ADD_NEW_PROL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRDDTD);
        DDRDDTD.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRDDTD.KEY.CON_NO = TDCKHCR.GACO_AC;
        WS_REF_NO.WS_AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        WS_REF_NO.WS_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        DDRDDTD.REF_NO = IBS.CLS2CPY(SCCGWA, WS_REF_NO);
        DDCSDDTD.PROL_NO = IBS.CLS2CPY(SCCGWA, WS_REF_NO);
        DDRDDTD.JR_NNO = SCCGWA.COMM_AREA.JRN_NO;
        DDRDDTD.CCY = DDCSDDTD.CCY;
        DDRDDTD.CCY_TYPE = DDCSDDTD.CCY_TYPE;
        DDRDDTD.EFF_DATE = DDCSDDTD.VAL_DATE;
        DDRDDTD.EXP_DATE = DDCSDDTD.EXP_DT;
        DDRDDTD.CON_RATE_TYPE = DDCSDDTD.RATE_TYP;
        DDRDDTD.CON_RATE_TERM = DDCSDDTD.RAT_TERM;
        DDRDDTD.CON_SPRD_TYPE = DDCSDDTD.FLOAT_TP;
        DDRDDTD.CON_SPRD = DDCSDDTD.F_SPRD;
        DDRDDTD.CON_SPRD_PCT = DDCSDDTD.F_PCNT;
        DDRDDTD.CON_FIX_RATE = DDCSDDTD.PMT_RATE;
        DDRDDTD.CON_FIN_RATE = DDCSDDTD.RATE;
        DDRDDTD.PAND_RATE_TYPE = DDCSDDTD.B_RAT_TP;
        DDRDDTD.PAND_RATE_TERM = DDCSDDTD.B_RAT_TM;
        DDRDDTD.PAND_SPRD_TYPE = DDCSDDTD.BF_TYPE;
        DDRDDTD.PAND_SPRD = DDCSDDTD.BF_SPRD;
        DDRDDTD.PAND_SPRD_PCT = DDCSDDTD.BF_PCNT;
        DDRDDTD.PAND_FIX_RATE = DDCSDDTD.BP_RATE;
        DDRDDTD.PAND_FIN_RATE = DDCSDDTD.BRK_RATE;
        DDRDDTD.CREATE_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRDDTD.CERATE_SUP_ID = SCCGWA.COMM_AREA.TL_ID;
        DDRDDTD.DELETE_TLR = " ";
        DDRDDTD.DELETE_BR = 0;
        DDRDDTD.DELETE_DATE = 0;
        DDRDDTD.DELETE_SUP_ID = " ";
        DDRDDTD.REMARKS = DDCSDDTD.REMARKS;
        DDRDDTD.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRDDTD.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRDDTD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRDDTD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DDTDDTD_TBL_ADD();
        if (pgmRtn) return;
    }
    public void B060_FORMAT_OUTPUT_DATA_ADD() throws IOException,SQLException,Exception {
        WS_SHOW2.WS_SHOW2_AC = DDCSDDTD.AC;
        WS_SHOW2.WS_SHOW2_REF_NO = IBS.CLS2CPY(SCCGWA, WS_REF_NO);
        WS_SHOW2.WS_SHOW2_AC_CNM = DDCSDDTD.AC_CNM;
        WS_SHOW2.WS_SHOW2_AC_ENM = DDCSDDTD.AC_ENM;
        WS_SHOW2.WS_SHOW2_CCY = DDCSDDTD.CCY;
        WS_SHOW2.WS_SHOW2_CCY_TYPE = DDCSDDTD.CCY_TYPE;
        WS_SHOW2.WS_SHOW2_PROL_AMT = DDCSDDTD.PROL_AMT;
        WS_SHOW2.WS_SHOW2_TENOR = DDCSDDTD.TENOR;
        WS_SHOW2.WS_SHOW2_VAL_DATE = DDCSDDTD.VAL_DATE;
        WS_SHOW2.WS_SHOW2_MAT_DATE = TDCTZZC.EXP_DT;
        WS_SHOW2.WS_SHOW2_RATE_TYP = DDCSDDTD.RATE_TYP;
        WS_SHOW2.WS_SHOW2_RAT_TERM = DDCSDDTD.RAT_TERM;
        WS_SHOW2.WS_SHOW2_FLOAT_TP = DDCSDDTD.FLOAT_TP;
        WS_SHOW2.WS_SHOW2_F_SPRD = DDCSDDTD.F_SPRD;
        WS_SHOW2.WS_SHOW2_F_PCNT = DDCSDDTD.F_PCNT;
        WS_SHOW2.WS_SHOW2_PMT_RATE = DDCSDDTD.PMT_RATE;
        WS_SHOW2.WS_SHOW2_RATE = DDCSDDTD.RATE;
        WS_SHOW2.WS_SHOW2_B_RAT_TP = DDCSDDTD.B_RAT_TP;
        WS_SHOW2.WS_SHOW2_B_RAT_TM = DDCSDDTD.B_RAT_TM;
        WS_SHOW2.WS_SHOW2_BF_TYPE = DDCSDDTD.BF_TYPE;
        WS_SHOW2.WS_SHOW2_BF_SPRD = DDCSDDTD.BF_SPRD;
        WS_SHOW2.WS_SHOW2_BF_PCNT = DDCSDDTD.BF_PCNT;
        WS_SHOW2.WS_SHOW2_BP_RATE = DDCSDDTD.BP_RATE;
        WS_SHOW2.WS_SHOW2_BRK_RATE = DDCSDDTD.BRK_RATE;
        WS_SHOW2.WS_SHOW2_DH_FLG = DDCSDDTD.DH_FLG;
        WS_SHOW2.WS_SHOW2_CALR_STD = DDCSDDTD.CALR_STD;
        WS_SHOW2.WS_SHOW2_REMARKS = DDCSDDTD.REMARKS;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT3;
        SCCFMT.DATA_PTR = WS_SHOW2;
        SCCFMT.DATA_LEN = 941;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B070_FORMAT_OUTPUT_DATA_CHG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRDDTD);
        DDRDDTD.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRDDTD.KEY.CON_NO = TDCACE.DATA[1-1].ACO_AC;
        DDRDDTD.REF_NO = DDCSDDTD.PROL_NO;
        T000_READ_DDTDDTD_TBL_BRO();
        if (pgmRtn) return;
        WS_SHOW3.WS_SHOW3_AC = DDCSDDTD.AC;
        WS_SHOW3.WS_SHOW3_REF_NO = DDCSDDTD.PROL_NO;
        WS_SHOW3.WS_SHOW3_AC_CNM = DDCSDDTD.AC_CNM;
        WS_SHOW3.WS_SHOW3_AC_ENM = DDCSDDTD.AC_ENM;
        WS_SHOW3.WS_SHOW3_CCY = DDRDDTD.CCY;
        WS_SHOW3.WS_SHOW3_CCY_TYPE = DDRDDTD.CCY_TYPE;
        WS_SHOW3.WS_SHOW3_PROL_DT = DDRDDTD.CRT_DATE;
        WS_SHOW3.WS_SHOW3_PROL_AMT = DDCSDDTD.PROL_AMT;
        WS_SHOW3.WS_SHOW3_PROL_BAL = DDCSDDTD.PROL_BAL;
        WS_SHOW3.WS_SHOW3_TENOR = DDCSDDTD.TENOR;
        WS_SHOW3.WS_SHOW3_VAL_DATE = DDCSDDTD.VAL_DATE;
        WS_SHOW3.WS_SHOW3_MAT_DATE = DDCSDDTD.MAT_DATE;
        WS_SHOW3.WS_SHOW3_B_RAT_TP = DDCSDDTD.B_RAT_TP;
        WS_SHOW3.WS_SHOW3_B_RAT_TM = DDCSDDTD.B_RAT_TM;
        WS_SHOW3.WS_SHOW3_BF_TYPE = DDCSDDTD.BF_TYPE;
        WS_SHOW3.WS_SHOW3_BF_SPRD = DDCSDDTD.BF_SPRD;
        WS_SHOW3.WS_SHOW3_BF_PCNT = DDCSDDTD.BF_PCNT;
        WS_SHOW3.WS_SHOW3_BP_RATE = DDCSDDTD.BP_RATE;
        WS_SHOW3.WS_SHOW3_BRK_RATE = DDCSDDTD.BRK_RATE;
        WS_SHOW3.WS_SHOW3_REMARKS = DDCSDDTD.REMARKS;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT4;
        SCCFMT.DATA_PTR = WS_SHOW3;
        SCCFMT.DATA_LEN = 913;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B080_WITHDRAW_DD_CONT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCTZZD);
        CEP.TRC(SCCGWA, DDCSDDTD.W_AMT);
        CEP.TRC(SCCGWA, DDCSDDTD.PROL_BAL);
        if (DDCSDDTD.W_AMT < DDCSDDTD.PROL_BAL) {
            TDCTZZD.OPT = '7';
        } else {
            if (DDCSDDTD.W_AMT == DDCSDDTD.PROL_BAL) {
                TDCTZZD.OPT = 'A';
            }
        }
        TDCTZZD.PBAL = DDCSDDTD.PROL_BAL;
        CEP.TRC(SCCGWA, DDCSDDTD.W_AMT);
        CEP.TRC(SCCGWA, DDCSDDTD.PROL_BAL);
        CEP.TRC(SCCGWA, TDCTZZD.OPT);
        TDCTZZD.AC = DDCSDDTD.CON_NO;
        TDCTZZD.CCY = DDCSDDTD.CCY;
        TDCTZZD.CCY_TYP = DDCSDDTD.CCY_TYPE;
        TDCTZZD.TXN_AMT = DDCSDDTD.W_AMT;
        TDCTZZD.DRAW_MTH = '4';
        TDCTZZD.PVAL_DT = DDCSDDTD.VAL_DATE;
        TDCTZZD.CT_FLG = '3';
        CEP.TRC(SCCGWA, "XIANLIANGCHI");
        if (DDCSDDTD.BRK_RATE == 0) {
            TDCTZZD.PAYING_INT = DDCSDDTD.B_INT;
            CEP.TRC(SCCGWA, TDCTZZD.PAYING_INT);
        } else {
            TDCTZZD.INT_RAT = DDCSDDTD.BRK_RATE;
            CEP.TRC(SCCGWA, "XIANGUAN");
        }
        CEP.TRC(SCCGWA, TDCTZZD.INT_RAT);
        TDCTZZD.OPP_AC = DDCSDDTD.AC;
        TDCTZZD.REMARK = DDCSDDTD.REMARKS;
        CEP.TRC(SCCGWA, TDCTZZD.RL_EXP_DT);
        CEP.TRC(SCCGWA, TDCTZZD.RL_RAT);
        S000_CALL_TDZTZZD();
        if (pgmRtn) return;
    }
    public void B080_WITHDRAW_DD_CONT_SUZ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCTDDR);
        if (DDCSDDTD.W_AMT < DDCSDDTD.PROL_BAL) {
            TDCTDDR.PROC_FLG = '7';
        } else {
            if (DDCSDDTD.W_AMT == DDCSDDTD.PROL_BAL) {
                TDCTDDR.PROC_FLG = 'A';
            }
        }
        TDCTDDR.BV_TYP = '0';
        CEP.TRC(SCCGWA, DDRDDTD.KEY.CON_NO);
        TDCTDDR.ACO_AC = DDRDDTD.KEY.CON_NO;
        TDCTDDR.OPP_AC = DDCSDDTD.AC;
        TDCTDDR.CCY = DDCSDDTD.CCY;
        TDCTDDR.CCY_TYP = DDCSDDTD.CCY_TYPE;
        TDCTDDR.BAL = DDCSDDTD.PROL_BAL;
        TDCTDDR.PVAL_DT = DDCSDDTD.VAL_DATE;
        TDCTDDR.VAL_DT = DDCSDDTD.VAL_DATE;
        TDCTDDR.EXP_DT = DDCSDDTD.MAT_DATE;
        if (DDCSDDTD.BRK_RATE == 0) {
            TDCTDDR.PAYING_INT = DDCSDDTD.B_INT;
            CEP.TRC(SCCGWA, TDCTDDR.PAYING_INT);
        } else {
            TDCTDDR.INT_RAT = DDCSDDTD.BRK_RATE;
            CEP.TRC(SCCGWA, TDCTDDR.INT_RAT);
        }
        TDCTDDR.TXN_AMT = DDCSDDTD.W_AMT;
        TDCTDDR.DRAW_MTH = '4';
        TDCTDDR.ID_TYP = CICACCU.DATA.ID_TYPE;
        TDCTDDR.ID_NO = CICACCU.DATA.ID_NO;
        TDCTDDR.REMARK = DDCSDDTD.REMARKS;
        TDCTDDR.CT_FLG = '2';
        TDCTDDR.MMO = "P409";
        if (TDCTDDR.STSW == null) TDCTDDR.STSW = "";
        JIBS_tmp_int = TDCTDDR.STSW.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) TDCTDDR.STSW += " ";
        TDCTDDR.STSW = "1" + TDCTDDR.STSW.substring(1);
        S000_CALL_TDZTDDR();
        if (pgmRtn) return;
    }
    public void B090_FORMAT_OUTPUT_DATA_DEL() throws IOException,SQLException,Exception {
        WS_SHOW4.WS_SHOW4_AC = DDCSDDTD.AC;
        WS_SHOW4.WS_SHOW4_REF_NO = DDCSDDTD.PROL_NO;
        WS_SHOW4.WS_SHOW4_AC_CNM = DDCSDDTD.AC_CNM;
        WS_SHOW4.WS_SHOW4_AC_ENM = DDCSDDTD.AC_ENM;
        WS_SHOW4.WS_SHOW4_CCY = DDCSDDTD.CCY;
        WS_SHOW4.WS_SHOW4_CCY_TYPE = DDCSDDTD.CCY_TYPE;
        WS_SHOW4.WS_SHOW4_PROL_AMT = DDCSDDTD.PROL_AMT;
        WS_SHOW4.WS_SHOW4_PROL_BAL = DDCSDDTD.PROL_BAL;
        WS_SHOW4.WS_SHOW4_TENOR = DDCSDDTD.TENOR;
        WS_SHOW4.WS_SHOW4_VAL_DATE = DDCSDDTD.VAL_DATE;
        WS_SHOW4.WS_SHOW4_MAT_DATE = DDCSDDTD.MAT_DATE;
        WS_SHOW4.WS_SHOW4_RATE = DDCSDDTD.RATE;
        WS_SHOW4.WS_SHOW4_BRK_RATE = DDCSDDTD.BRK_RATE;
        if (DDCSDDTD.B_INT == 0) {
            WS_SHOW4.WS_SHOW4_B_INT = TDCTDDR.PAYING_INT;
        } else {
            WS_SHOW4.WS_SHOW4_B_INT = DDCSDDTD.B_INT;
        }
        WS_SHOW4.WS_SHOW4_W_AMT = DDCSDDTD.W_AMT;
        WS_SHOW4.WS_SHOW4_REMARKS = DDCSDDTD.REMARKS;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT5;
        SCCFMT.DATA_PTR = WS_SHOW4;
        SCCFMT.DATA_LEN = 917;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B170_NFIN_TX_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        if (WS_FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
            BPCPNHIS.INFO.TX_TYP_CD = "P605";
        }
        if (WS_FUNC == 'C') {
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.TX_TYP_CD = "P606";
        }
        BPCPNHIS.INFO.AC = DDCSDDTD.AC;
        BPCPNHIS.INFO.CCY = DDCSDDTD.CCY;
        BPCPNHIS.INFO.CCY_TYPE = DDCSDDTD.CCY_TYPE;
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.AC);
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.CCY);
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.CCY_TYPE);
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK_NAME;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
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
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void R000_BEGIN_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 771;
        CEP.TRC(SCCGWA, SCCMPAG.MAX_COL_NO);
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_LIST);
        SCCMPAG.DATA_LEN = 771;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_STBR_DDTDDTD() throws IOException,SQLException,Exception {
        DDTDDTD_BR.rp = new DBParm();
        DDTDDTD_BR.rp.TableName = "DDTDDTD";
        DDTDDTD_BR.rp.where = "AC = :DDRDDTD.KEY.AC";
        DDTDDTD_BR.rp.order = "AC,CON_NO,REF_NO";
        IBS.STARTBR(SCCGWA, DDRDDTD, this, DDTDDTD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_STOP_FLG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_STOP_FLG = 'Y';
        } else {
            SCCEXCP.TYPE = 'D';
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTDDTD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STRTBR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
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
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_DDTDDTD() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRDDTD, this, DDTDDTD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_STOP_FLG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_STOP_FLG = 'Y';
        } else {
            SCCEXCP.TYPE = 'D';
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTDDTD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_DDTDDTD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTDDTD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            SCCEXCP.TYPE = 'D';
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTDDTD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "ENDBR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTDDTD_TBL_INQ() throws IOException,SQLException,Exception {
        DDTDDTD_RD = new DBParm();
        DDTDDTD_RD.TableName = "DDTDDTD";
        DDTDDTD_RD.where = "AC = :DDRDDTD.KEY.AC "
            + "AND REF_NO = :DDRDDTD.REF_NO";
        IBS.READ(SCCGWA, DDRDDTD, this, DDTDDTD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_4030;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTDDTD_TBL_BRO() throws IOException,SQLException,Exception {
        DDTDDTD_RD = new DBParm();
        DDTDDTD_RD.TableName = "DDTDDTD";
        DDTDDTD_RD.where = "AC = :DDRDDTD.KEY.AC "
            + "AND CON_NO = :DDRDDTD.KEY.CON_NO "
            + "AND REF_NO = :DDRDDTD.REF_NO";
        IBS.READ(SCCGWA, DDRDDTD, this, DDTDDTD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_4030;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_DDTDDTD_TBL_ADD() throws IOException,SQLException,Exception {
        DDTDDTD_RD = new DBParm();
        DDTDDTD_RD.TableName = "DDTDDTD";
        IBS.WRITE(SCCGWA, DDRDDTD, DDTDDTD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_4029;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTINF() throws IOException,SQLException,Exception {
        DDTINF_RD = new DBParm();
        DDTINF_RD.TableName = "DDTINF";
        IBS.READ(SCCGWA, DDRINF, DDTINF_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_DDTDDTD_TBL() throws IOException,SQLException,Exception {
        DDTDDTD_RD = new DBParm();
        DDTDDTD_RD.TableName = "DDTDDTD";
        DDTDDTD_RD.upd = true;
        DDTDDTD_RD.where = "AC = :DDRDDTD.KEY.AC "
            + "AND CON_NO = :DDRDDTD.KEY.CON_NO "
            + "AND REF_NO = :DDRDDTD.REF_NO";
        IBS.READ(SCCGWA, DDRDDTD, this, DDTDDTD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_4030;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_DDTDDTD_TBL2() throws IOException,SQLException,Exception {
        DDTDDTD_RD = new DBParm();
        DDTDDTD_RD.TableName = "DDTDDTD";
        DDTDDTD_RD.upd = true;
        DDTDDTD_RD.where = "AC = :DDRDDTD.KEY.AC "
            + "AND REF_NO = :DDRDDTD.REF_NO";
        IBS.READ(SCCGWA, DDRDDTD, this, DDTDDTD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_4030;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DDTDDTD_TBL_CHG() throws IOException,SQLException,Exception {
        DDTDDTD_RD = new DBParm();
        DDTDDTD_RD.TableName = "DDTDDTD";
        IBS.REWRITE(SCCGWA, DDRDDTD, DDTDDTD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_4030;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
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
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_DELETE_DDTDDTD() throws IOException,SQLException,Exception {
        DDTDDTD_RD = new DBParm();
        DDTDDTD_RD.TableName = "DDTDDTD";
        IBS.DELETE(SCCGWA, DDRDDTD, DDTDDTD_RD);
    }
    public void S000_CALL_DDZSCINM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-CI-NAME", DDCSCINM, true);
    }
    public void S000_CALL_TDZTZZC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-TZZ-CR", TDCTZZC, true);
    }
    public void S000_CALL_TDZKHCR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-KH-CR", TDCKHCR, true);
    }
    public void S000_CALL_TDZTDDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-TD-DR", TDCTDDR);
    }
    public void S000_CALL_CIZMAGT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-AGT", CICMAGT);
        if (CICMAGT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICMAGT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZSRATE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-RATE", DDCSRATE);
    }
    public void S000_CALL_TDZTZZD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-TZZ-DR", TDCTZZD, true);
    }
    public void S000_CALL_TDZACE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-AC-ENQ", TDCACE, true);
    }
    public void S000_CALL_DDZIQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_I_INQ_DDPRD, DDCIQPRD);
        if (DDCIQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQPRD.RC);
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
        CEP.TRC(SCCGWA, WS_ERR_MSG);
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
