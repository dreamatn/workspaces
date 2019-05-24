package com.hisun.GD;

import com.hisun.DD.*;
import com.hisun.TD.*;
import com.hisun.DC.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCTPCL;
import com.hisun.BP.*;
import com.hisun.CI.*;
import com.hisun.AI.*;

import java.io.IOException;
import java.sql.SQLException;

public class GDZUMPLD {
    int JIBS_tmp_int;
    DBParm DDTMST_RD;
    DBParm TDTSMST_RD;
    DBParm TDTCMST_RD;
    DBParm DDTCCY_RD;
    DBParm GDTPLDR_RD;
    DBParm GDTHIS_RD;
    brParm GDTPLDR_BR = new brParm();
    brParm GDTHIS_BR = new brParm();
    DBParm GDTSTAC_RD;
    char K_RL_STS_RELAT = 'R';
    char K_RL_STS_CANCEL = 'C';
    String K_SERV_CODE = "189200";
    String K_APP_MMO = "GD";
    String K_CHK_STS_TBL = "0001";
    String K_GUAOUT = "GUAOUT";
    String K_CHK_STS_TBL1 = "0002";
    String K_CHK_STS_TBL2 = "0003";
    String WS_ERR_MSG = " ";
    double WS_AVA_RELAT_AMT = 0;
    double WS_CURR_BAL = 0;
    double WS_HOLD_AMT = 0;
    double WS_MARGIN_AMT = 0;
    double WS_CCAL_TOT_BAL = 0;
    String WS_AC = " ";
    int WS_AC_SEQ = 0;
    double WS_VAL_RBAL = 0;
    double WS_CANCEL_AMT = 0;
    char WS_CANCEL_AC_TYP = ' ';
    String WS_CANCEL_AC = " ";
    int WS_CANCEL_AC_SEQ = 0;
    String WS_CANCEL_ACO_AC = " ";
    char WS_FUNC = ' ';
    String WS_AIMIB_AC = " ";
    GDZUMPLD_WS_CRT_RSEQ WS_CRT_RSEQ = new GDZUMPLD_WS_CRT_RSEQ();
    GDZUMPLD_WS_CMS WS_CMS = new GDZUMPLD_WS_CMS();
    String WS_ACO_AC = " ";
    char WS_HIS_FLG = ' ';
    char WS_PLDR_FLG = ' ';
    char WS_PLDR_CREATE_FLG = ' ';
    char WS_RSEQ_FLG = ' ';
    char WS_UQSAC_FLG = ' ';
    char WS_TD_SEQ_FLG = ' ';
    char WS_STAC_FLG = ' ';
    char WS_CSTS_FLG = ' ';
    char WS_AICR_FLG = ' ';
    char WS_CR_FLG = ' ';
    char WS_INTAC_FLG = ' ';
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDVMRAT DDVMRAT = new DDVMRAT();
    GDCRPLDR GDCRPLDR = new GDCRPLDR();
    GDCRHIS GDCRHIS = new GDCRHIS();
    CICQACAC CICQACAC = new CICQACAC();
    DDCICHAC DDCICHAC = new DDCICHAC();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCTPCL SCCTPCL = new SCCTPCL();
    GDRSTAC GDRSTAC = new GDRSTAC();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    AICPQIA AICPQIA = new AICPQIA();
    AICUUPIA AICUUPIA = new AICUUPIA();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    TDCACDRU TDCACDRU = new TDCACDRU();
    CICACCU CICACCU = new CICACCU();
    DCRIAACR DCRIAACR = new DCRIAACR();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    CICQACRI CICQACRI = new CICQACRI();
    double WS_ALL_RELAT_AMT = 0;
    DDRMST DDRMST = new DDRMST();
    TDRSMST TDRSMST = new TDRSMST();
    TDRCMST TDRCMST = new TDRCMST();
    DDRCCY DDRCCY = new DDRCCY();
    GDRPLDR GDRPLDR = new GDRPLDR();
    GDRHIS GDRHIS = new GDRHIS();
    SCCGWA SCCGWA;
    GDCUMPLD GDCUMPLD;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA, GDCUMPLD GDCUMPLD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.GDCUMPLD = GDCUMPLD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "GDZUMPLD return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCUMPLD.FUNC);
        CEP.TRC(SCCGWA, GDCUMPLD.AC);
        CEP.TRC(SCCGWA, GDCUMPLD.AC_TYPE);
        CEP.TRC(SCCGWA, GDCUMPLD.CCY);
        CEP.TRC(SCCGWA, GDCUMPLD.RSEQ);
        CEP.TRC(SCCGWA, GDCUMPLD.BUSI_TYPE);
        if (GDCUMPLD.AC.trim().length() > 0) {
            B010_CHECK_DATA_VALIDITY();
        }
        if (GDCUMPLD.FUNC == 'C') {
            B020_GET_AC_INFO();
            B040_CALU_AVA_AMT_PROC();
            B050_CRT_PLDR_PROC();
        } else if (GDCUMPLD.FUNC == 'A'
            || GDCUMPLD.FUNC == 'R') {
            B020_GET_AC_INFO();
            B040_CALU_AVA_AMT_PROC();
            B060_MOD_PLDR_PROC();
        } else if (GDCUMPLD.FUNC == 'D') {
            B070_DEL_PLDR_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + GDCUMPLD.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
            B080_NOTICE_CMS();
        }
    }
    public void B010_CHECK_DATA_VALIDITY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCUMPLD.RSEQ);
        CEP.TRC(SCCGWA, GDCUMPLD.AC);
        CEP.TRC(SCCGWA, GDCUMPLD.AC_SEQ);
        CEP.TRC(SCCGWA, GDCUMPLD.BUSI_TYPE);
        WS_AC = GDCUMPLD.AC;
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = GDCUMPLD.AC;
        CICQACAC.DATA.AGR_SEQ = GDCUMPLD.AC_SEQ;
        S000_CALL_CIZQACAC();
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        WS_ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        if (GDCUMPLD.AC.trim().length() == 0 
            && GDCUMPLD.FUNC != 'D') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (GDCUMPLD.AC_TYPE == ' ' 
            && GDCUMPLD.FUNC != 'D') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_TYPE_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (GDCUMPLD.CCY.trim().length() == 0 
            && GDCUMPLD.FUNC != 'D') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CCY_NOT_MATCH;
            S000_ERR_MSG_PROC();
        }
        if (GDCUMPLD.CTA_NO.trim().length() == 0 
            && GDCUMPLD.FUNC == 'C') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CTA_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (GDCUMPLD.BUSI_TYPE.trim().length() == 0 
            && GDCUMPLD.FUNC == 'C') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_BUSI_TYPE_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, GDCUMPLD.TX_AMT);
        if (GDCUMPLD.TX_AMT < 0 
            && GDCUMPLD.FUNC != 'D') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AMT_INVALID;
            S000_ERR_MSG_PROC();
        }
        if (!IBS.isNumeric(GWA_SC_AREA.BA_CNT+"")) {
            GWA_SC_AREA.BA_CNT = 0;
        }
    }
    public void B020_GET_AC_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCUMPLD.AC_TYPE);
        if (GDCUMPLD.AC_TYPE == '0') {
            B030_CHK_PROD_INFO_PROC();
        }
        CEP.TRC(SCCGWA, DDVMPRD.VAL.TD_FLG);
        if (GDCUMPLD.AC_TYPE == '0' 
            && (DDVMPRD.VAL.TD_FLG != '0' 
            || GDCUMPLD.AC_SEQ == 0)) {
            B020_01_CHK_DDAC_PROC();
        } else {
            B020_02_CHK_TDAC_PROC();
        }
    }
    public void B020_01_CHK_DDAC_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCUMPLD.AC_SEQ);
        if (GDCUMPLD.AC_SEQ != 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_DD_SEQ_NOTNEED;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = GDCUMPLD.AC;
        T000_READ_DDTMST();
        if (GDCUMPLD.FUNC == 'A' 
            || GDCUMPLD.FUNC == 'C') {
            IBS.init(SCCGWA, BPCFCSTS);
            BPCFCSTS.AP_MMO = K_APP_MMO;
            BPCFCSTS.TBL_NO = K_CHK_STS_TBL1;
            if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
            JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
            for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 101 - 1) + DDRMST.AC_STS_WORD + BPCFCSTS.STATUS_WORD.substring(101 + 99 - 1);
            S000_CALL_BPZFCSTS1();
        }
        if (!GDCUMPLD.BUSI_TYPE.equalsIgnoreCase(DDRMST.YW_TYP) 
            && GDCUMPLD.BUSI_TYPE.trim().length() > 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_BUSI_TYP_NOT_MTH;
            S000_ERR_MSG_PROC();
        }
        if (DDRMST.AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
        }
        if (DDRMST.AC_STS == 'O') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INACTIVE_AC;
            S000_ERR_MSG_PROC();
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        CEP.TRC(SCCGWA, DDRMST.AC_STS_WORD.substring(9 - 1, 9 + 1 - 1));
        CEP.TRC(SCCGWA, GDCUMPLD.FUNC);
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (GDCUMPLD.FUNC != 'R' 
            && DDRMST.AC_STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.TRC(SCCGWA, "AC HOLD");
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_HLD;
            S000_ERR_MSG_PROC();
        }
        if (DDRMST.AC_TYPE != 'N') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_NOT_GD_AC;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = GDCUMPLD.AC;
        S000_CALL_CIZQACAC();
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRCCY.CCY = GDCUMPLD.CCY;
        DDRCCY.CCY_TYPE = GDCUMPLD.CCY_TYPE;
        CEP.TRC(SCCGWA, "OOOOO");
        CEP.TRC(SCCGWA, GDCUMPLD.CCY_TYPE);
        CEP.TRC(SCCGWA, DDRCCY.CCY_TYPE);
        T000_READ_DDTCCY();
        CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
        CEP.TRC(SCCGWA, DDRCCY.MARGIN_BAL);
        CEP.TRC(SCCGWA, DDRCCY.HOLD_BAL);
        CEP.TRC(SCCGWA, DDRCCY.CCAL_TOT_BAL);
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (GDCUMPLD.FUNC != 'R' 
            && (!DDRCCY.STS_WORD.substring(8 - 1, 8 + 2 - 1).equalsIgnoreCase("00") 
            || !DDRCCY.STS_WORD.substring(16 - 1, 16 + 2 - 1).equalsIgnoreCase("00"))) {
            CEP.TRC(SCCGWA, "AC HOLD");
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_HLD;
            S000_ERR_MSG_PROC();
        }
        WS_CURR_BAL = DDRCCY.CURR_BAL;
        WS_MARGIN_AMT = DDRCCY.MARGIN_BAL;
        WS_HOLD_AMT = DDRCCY.HOLD_BAL;
        WS_CCAL_TOT_BAL = DDRCCY.CCAL_TOT_BAL;
        GDCUMPLD.PROD_CD = DDRMST.PROD_CODE;
    }
    public void B020_02_CHK_TDAC_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCUMPLD.AC);
        CEP.TRC(SCCGWA, GDCUMPLD.AC_SEQ);
        if (GDCUMPLD.AC_SEQ != 0) {
            IBS.init(SCCGWA, TDRCMST);
            TDRCMST.KEY.AC_NO = GDCUMPLD.AC;
            T000_READ_TDTCMST();
            if (!GDCUMPLD.BUSI_TYPE.equalsIgnoreCase(TDRCMST.REF_TYP) 
                && GDCUMPLD.BUSI_TYPE.trim().length() > 0) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_BUSI_TYP_NOT_MTH;
                S000_ERR_MSG_PROC();
            }
            IBS.init(SCCGWA, TDRSMST);
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = GDCUMPLD.AC;
            CICQACAC.DATA.AGR_SEQ = GDCUMPLD.AC_SEQ;
            S000_CALL_CIZQACAC();
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
            TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            T000_READ_TDTSMST();
            if (GDCUMPLD.FUNC == 'A' 
                || GDCUMPLD.FUNC == 'C') {
                CEP.TRC(SCCGWA, TDRSMST.STSW);
                IBS.init(SCCGWA, BPCFCSTS);
                BPCFCSTS.AP_MMO = K_APP_MMO;
                BPCFCSTS.TBL_NO = K_CHK_STS_TBL2;
                if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
                JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
                for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 101 - 1) + TDRSMST.STSW + BPCFCSTS.STATUS_WORD.substring(101 + 99 - 1);
                CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD);
                S000_CALL_BPZFCSTS1();
            }
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            CEP.TRC(SCCGWA, TDRSMST.STSW.substring(2 - 1, 2 + 2 - 1));
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
                || TDRSMST.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_HLD;
                S000_ERR_MSG_PROC();
            }
        }
        CEP.TRC(SCCGWA, GDCUMPLD.AC);
        if (!GDCUMPLD.CCY.equalsIgnoreCase(TDRSMST.CCY)) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_CCY_ERR_MATCH;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, GDCUMPLD.CCY_TYPE);
        if (GDCUMPLD.CCY_TYPE != TDRSMST.CCY_TYP) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_CCY_TYP_NOT_SAME;
            S000_ERR_MSG_PROC();
        }
        if (TDRSMST.ACO_STS == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED;
            S000_ERR_MSG_PROC();
        }
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (GDCUMPLD.FUNC != 'R' 
            && (TDRSMST.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
            || TDRSMST.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1"))) {
            CEP.TRC(SCCGWA, "AC HOLD");
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_HLD;
            S000_ERR_MSG_PROC();
        }
        if (TDRSMST.ACO_STS == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_INVALID;
            S000_ERR_MSG_PROC();
        }
        if (!TDRSMST.PRDAC_CD.equalsIgnoreCase("033")) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_NOT_GD_AC;
            S000_ERR_MSG_PROC();
        }
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (GDCUMPLD.FUNC != 'R' 
            && (!TDRSMST.STSW.substring(2 - 1, 2 + 2 - 1).equalsIgnoreCase("00") 
            || !TDRSMST.STSW.substring(7 - 1, 7 + 2 - 1).equalsIgnoreCase("00"))) {
            CEP.TRC(SCCGWA, "AC HOLD");
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_HLD;
            S000_ERR_MSG_PROC();
        }
        WS_CURR_BAL = TDRSMST.BAL;
        WS_HOLD_AMT = TDRSMST.HBAL;
        WS_MARGIN_AMT = TDRSMST.GUAR_BAL;
        GDCUMPLD.PROD_CD = TDRSMST.PROD_CD;
    }
    public void B030_CHK_PROD_INFO_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = GDCUMPLD.AC;
        T000_READ_DDTMST();
        CEP.TRC(SCCGWA, DDRMST.PROD_CODE);
        if (GDCUMPLD.CCY.trim().length() > 0) {
            IBS.init(SCCGWA, DDCIQPRD);
            IBS.init(SCCGWA, DDVMPRD);
            IBS.init(SCCGWA, DDVMRAT);
            DDCIQPRD.INPUT_DATA.PROD_CODE = DDRMST.PROD_CODE;
            DDCIQPRD.INPUT_DATA.CCY = GDCUMPLD.CCY;
            DDCIQPRD.DDVMPRD_PTR = DDVMPRD;
            DDCIQPRD.DDVMRAT_PTR = DDVMRAT;
            S000_CALL_DDZIQPRD();
        }
        GDCUMPLD.PROD_CD = DDCIQPRD.INPUT_DATA.PROD_CODE;
        CEP.TRC(SCCGWA, DDVMPRD.VAL.TD_FLG);
    }
    public void B040_CALU_AVA_AMT_PROC() throws IOException,SQLException,Exception {
        WS_AVA_RELAT_AMT = WS_CURR_BAL - WS_HOLD_AMT - WS_MARGIN_AMT + WS_CCAL_TOT_BAL;
        CEP.TRC(SCCGWA, GDCUMPLD.TX_AMT);
        CEP.TRC(SCCGWA, WS_AVA_RELAT_AMT);
        if (GDCUMPLD.TX_AMT > WS_AVA_RELAT_AMT 
            && (GDCUMPLD.FUNC == 'C' 
            || GDCUMPLD.FUNC == 'A')) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_RL_AMT_GT_LMT;
            S000_ERR_MSG_PROC();
        }
        GDCUMPLD.BAL = WS_CURR_BAL;
        GDCUMPLD.AVA_RAMT = WS_AVA_RELAT_AMT;
    }
    public void B050_CRT_PLDR_PROC() throws IOException,SQLException,Exception {
        if (GDCUMPLD.CPRL_FLG == 'Y') {
            R000_CRT_PLDR_PROC();
        } else {
            B050_01_CRT_DATA_VAL();
            B050_02_CRT_PLDR_REC();
        }
        CEP.TRC(SCCGWA, GDRPLDR.KEY.AC);
        CEP.TRC(SCCGWA, GDRPLDR.KEY.AC_SEQ);
        WS_AC = GDRPLDR.KEY.AC;
        WS_AC_SEQ = GDRPLDR.KEY.AC_SEQ;
        R000_UPD_MARG_BAL_PROC();
        R000_WRITE_HIS_PROC();
        R000_WRITE_NON_FIN_HIS_PROC();
    }
    public void B050_01_CRT_DATA_VAL() throws IOException,SQLException,Exception {
        WS_PLDR_CREATE_FLG = 'C';
        if (GDCUMPLD.RSEQ.trim().length() == 0) {
            IBS.init(SCCGWA, GDRPLDR);
            GDRPLDR.DEAL_CD = GDCUMPLD.CTA_NO;
            GDRPLDR.BSREF = GDCUMPLD.REF_NO;
            CEP.TRC(SCCGWA, GDRPLDR.DEAL_CD);
            CEP.TRC(SCCGWA, GDRPLDR.BSREF);
            T000_READ_FIRST_GDTPLDR();
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                IBS.init(SCCGWA, BPCSGSEQ);
                BPCSGSEQ.TYPE = "SEQ";
                BPCSGSEQ.CODE = "MAPM";
                BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
                BPCSGSEQ.RUN_MODE = 'O';
                S000_CALL_BPZSGSEQ();
                WS_CRT_RSEQ.WS_AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
                WS_CRT_RSEQ.WS_CTA_SEQ = (int) BPCSGSEQ.SEQ;
                GDCUMPLD.RSEQ = IBS.CLS2CPY(SCCGWA, WS_CRT_RSEQ);
                GDCUMPLD.O_RSEQ = IBS.CLS2CPY(SCCGWA, WS_CRT_RSEQ);
                CEP.TRC(SCCGWA, GDCUMPLD.O_RSEQ);
                CEP.TRC(SCCGWA, GDCUMPLD.RSEQ);
                CEP.TRC(SCCGWA, "AAAAAA");
                WS_PLDR_CREATE_FLG = 'C';
            } else if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_NOT_FST_REL_MUST_REQ;
                S000_ERR_MSG_PROC();
            } else {
                SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "GDTPLDR";
                SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
                B_DB_EXCP();
            }
        } else {
            IBS.init(SCCGWA, GDRPLDR);
            WS_PLDR_FLG = 'N';
            WS_RSEQ_FLG = 'N';
            GDRPLDR.KEY.RSEQ = GDCUMPLD.RSEQ;
            T000_STBR_BY_RSEQ_PROC1();
            T000_READNEXT_PROC();
            while (WS_PLDR_FLG != 'N') {
                WS_RSEQ_FLG = 'Y';
                CEP.TRC(SCCGWA, GDCUMPLD.AC);
                CEP.TRC(SCCGWA, GDCUMPLD.RSEQ);
                CEP.TRC(SCCGWA, GDRPLDR);
                if (GDRPLDR.KEY.AC.equalsIgnoreCase(GDCUMPLD.AC) 
                    && GDRPLDR.KEY.AC_SEQ == GDCUMPLD.AC_SEQ) {
                    WS_PLDR_CREATE_FLG = 'A';
                    if (!GDCUMPLD.CCY.equalsIgnoreCase(GDRPLDR.CCY)) {
                        WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CCY_NOT_MATCH;
                        S000_ERR_MSG_PROC();
                    }
                }
                if (!GDCUMPLD.CTA_NO.equalsIgnoreCase(GDRPLDR.DEAL_CD)) {
                    WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CTA_NO_NOT_MATCH;
                    S000_ERR_MSG_PROC();
                }
                CEP.TRC(SCCGWA, GDCUMPLD.REF_NO);
                CEP.TRC(SCCGWA, GDRPLDR.BSREF);
                if (!GDCUMPLD.REF_NO.equalsIgnoreCase(GDRPLDR.BSREF)) {
                    WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_REF_NO_NOT_MATCH;
                    S000_ERR_MSG_PROC();
                }
                CEP.TRC(SCCGWA, GDCUMPLD.BUSI_TYPE);
                CEP.TRC(SCCGWA, GDRPLDR.REF_TYP);
                CEP.TRC(SCCGWA, GDRPLDR);
                if (!GDCUMPLD.BUSI_TYPE.equalsIgnoreCase(GDRPLDR.REF_TYP)) {
                    WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_BUSI_TYP_NOT_MTH;
                    S000_ERR_MSG_PROC();
                }
                T000_READNEXT_PROC();
            }
            T000_ENDBR_PROC();
            if (WS_RSEQ_FLG == 'N') {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_RSEQ_NOTFND;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B050_02_CRT_PLDR_REC() throws IOException,SQLException,Exception {
        if (WS_PLDR_CREATE_FLG == 'C') {
            R000_CRT_PLDR_PROC();
        } else {
            R000_READUPD_PLDR_PROC2();
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
            CEP.TRC(SCCGWA, GDCRPLDR.RC);
            if (GDCRPLDR.RETURN_INFO == 'N') {
                R000_CRT_PLDR_PROC();
            } else {
                R000_UPDATE_PLDR_PROC();
            }
        }
    }
    public void B060_MOD_PLDR_PROC() throws IOException,SQLException,Exception {
        R000_READUPD_PLDR_PROC();
        CEP.TRC(SCCGWA, GDRPLDR.KEY.AC);
        CEP.TRC(SCCGWA, GDRPLDR.KEY.AC_SEQ);
        WS_AC = GDRPLDR.KEY.AC;
        WS_AC_SEQ = GDRPLDR.KEY.AC_SEQ;
        if (GDCUMPLD.TX_AMT > GDRPLDR.RELAT_AMT 
            && GDCUMPLD.FUNC == 'R') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CUT_AMT_GT_LMT;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, GDRPLDR.DEAL_CD);
        CEP.TRC(SCCGWA, GDRPLDR.BSREF);
        CEP.TRC(SCCGWA, GDRPLDR.SYS_NO);
        GDCUMPLD.CTA_NO = GDRPLDR.DEAL_CD;
        GDCUMPLD.REF_NO = GDRPLDR.BSREF;
        GDCUMPLD.SYS_NO = GDRPLDR.SYS_NO;
        R000_UPDATE_PLDR_PROC();
        R000_UPD_MARG_BAL_PROC();
        R000_WRITE_HIS_PROC();
        R000_WRITE_NON_FIN_HIS_PROC();
    }
    public void B070_DEL_PLDR_PROC() throws IOException,SQLException,Exception {
        WS_AC = GDCUMPLD.AC;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            if (GDCUMPLD.AC.trim().length() == 0) {
                B070_01_DEL_ALL_PLDR_PROC();
            } else {
                B070_02_DEL_ONE_PLDR_PROC();
            }
        } else {
            CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE);
            CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO);
            IBS.init(SCCGWA, GDRHIS);
            GDRHIS.KEY.TR_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            GDRHIS.KEY.JRNNO = GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
            if (GDCUMPLD.AC.trim().length() > 0) {
                GDRHIS.AC = GDCUMPLD.AC;
                GDRHIS.AC_SEQ = GDCUMPLD.AC_SEQ;
            }
            T000_STARTBR_BY_DTJRNTYP();
            T000_READNEXT_HIS_PROC();
            CEP.TRC(SCCGWA, GDRHIS.CAN_FLG);
            CEP.TRC(SCCGWA, GDRHIS.AC);
            CEP.TRC(SCCGWA, GDRHIS.KEY.SEQ);
            CEP.TRC(SCCGWA, GDRHIS.FUNC);
            while (WS_HIS_FLG != 'N') {
                if (GDRHIS.CAN_FLG == 'N') {
                    CEP.TRC(SCCGWA, GDRHIS.FUNC);
                    CEP.TRC(SCCGWA, GDRHIS.TR_AMT);
                    WS_FUNC = GDRHIS.FUNC;
                    if (GDRHIS.FUNC == '6') {
                        B070_03_CANCEL_PROC1();
                        R000_WRITE_CANCEL_HIS_PROC();
                    } else if (GDRHIS.FUNC == '8') {
                        B070_03_CANCEL_PROC2();
                        R000_WRITE_CANCEL_HIS_PROC();
                    } else if (GDRHIS.FUNC == '9') {
                        B070_03_CANCEL_PROC3();
                        R000_WRITE_CANCEL_HIS_PROC();
                    } else if (GDRHIS.FUNC == '3') {
                        B070_02_DEL_ONE_PLDR_PROC();
                    }
                }
                T000_READNEXT_HIS_PROC();
            }
            T000_ENDBR_HIS_PROC();
        }
    }
    public void B070_01_DEL_ALL_PLDR_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCUMPLD.RSEQ);
        R000_READ_PLDR_REC_FIRST();
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        IBS.init(SCCGWA, GDRPLDR);
        WS_PLDR_FLG = 'N';
        GDRPLDR.KEY.RSEQ = GDCUMPLD.RSEQ;
        GDRPLDR.RELAT_STS = 'N';
        CEP.TRC(SCCGWA, GDRPLDR.KEY.RSEQ);
        CEP.TRC(SCCGWA, GDRPLDR.RELAT_STS);
        T000_STBR_BY_RSEQ_PROC();
        T000_READNEXT_PROC();
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (WS_PLDR_FLG == 'N') {
            if (GDCUMPLD.ERR_FUNC == 'N') {
                CEP.TRC(SCCGWA, "NO ERR MSG");
            } else {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_PLDR_RELEASED;
                S000_ERR_MSG_PROC();
            }
        }
        while (WS_PLDR_FLG != 'N') {
            CEP.TRC(SCCGWA, GDRPLDR.KEY.AC);
            CEP.TRC(SCCGWA, GDRPLDR.KEY.AC_SEQ);
            WS_AC = GDRPLDR.KEY.AC;
            WS_AC_SEQ = GDRPLDR.KEY.AC_SEQ;
            CEP.TRC(SCCGWA, WS_AC_SEQ);
            CEP.TRC(SCCGWA, WS_AC);
            GDCUMPLD.AC = GDRPLDR.KEY.AC;
            GDCUMPLD.AC_SEQ = GDRPLDR.KEY.AC_SEQ;
            GDCUMPLD.CCY = GDRPLDR.CCY;
            if (GDRPLDR.CCY.equalsIgnoreCase("156")) {
                GDCUMPLD.CCY_TYPE = '1';
            } else {
                GDCUMPLD.CCY_TYPE = '2';
            }
            GDCUMPLD.AC_TYPE = GDRPLDR.AC_TYP;
            GDCUMPLD.TX_AMT = GDRPLDR.RELAT_AMT;
            GDCUMPLD.CTA_NO = GDRPLDR.DEAL_CD;
            GDCUMPLD.REF_NO = GDRPLDR.BSREF;
            CEP.TRC(SCCGWA, GDCUMPLD.TOT_RAMT);
            CEP.TRC(SCCGWA, GDRPLDR.RELAT_AMT);
            GDCUMPLD.TOT_RAMT = GDCUMPLD.TOT_RAMT + GDRPLDR.RELAT_AMT;
            CEP.TRC(SCCGWA, GDCUMPLD.TOT_RAMT);
            if (GDCUMPLD.TX_AMT != 0) {
                B020_GET_AC_INFO();
                B040_CALU_AVA_AMT_PROC();
            }
            R000_READUPD_PLDR_PROC1();
            R000_UPDATE_PLDR_PROC();
            if (GDCUMPLD.TX_AMT != 0) {
                R000_UPD_MARG_BAL_PROC();
            }
            R000_WRITE_HIS_PROC();
            R000_WRITE_NON_FIN_HIS_PROC();
            CEP.TRC(SCCGWA, GWA_SC_AREA.BA_CNT);
            if (GDCUMPLD.TX_AMT != 0) {
                if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
                    R000_STAC_PROC();
                    R100_WRITE_HIS_PROC();
                }
            }
            T000_READNEXT_PROC();
        }
        T000_ENDBR_PROC();
    }
    public void B070_02_DEL_ONE_PLDR_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            R000_READUPD_PLDR_PROC();
        } else {
            R000_READUPD_PLDR_PROC1();
        }
        GDCUMPLD.AC = GDRPLDR.KEY.AC;
        WS_AC = GDRPLDR.KEY.AC;
        GDCUMPLD.AC_SEQ = GDRPLDR.KEY.AC_SEQ;
        WS_AC_SEQ = GDRPLDR.KEY.AC_SEQ;
        GDCUMPLD.CCY = GDRPLDR.CCY;
        CEP.TRC(SCCGWA, WS_AC_SEQ);
        CEP.TRC(SCCGWA, WS_AC);
        if (GDRPLDR.CCY.equalsIgnoreCase("156")) {
            GDCUMPLD.CCY_TYPE = '1';
        } else {
            GDCUMPLD.CCY_TYPE = '2';
        }
        GDCUMPLD.AC_TYPE = GDRPLDR.AC_TYP;
        GDCUMPLD.TX_AMT = GDRPLDR.RELAT_AMT;
        GDCUMPLD.TOT_RAMT = GDRPLDR.RELAT_AMT;
        GDCUMPLD.CTA_NO = GDRPLDR.DEAL_CD;
        GDCUMPLD.REF_NO = GDRPLDR.BSREF;
        if (GDCUMPLD.TX_AMT != 0) {
            B020_GET_AC_INFO();
            B040_CALU_AVA_AMT_PROC();
        }
        R000_UPDATE_PLDR_PROC();
        if (GDCUMPLD.TX_AMT != 0) {
            R000_UPD_MARG_BAL_PROC();
        }
        R000_WRITE_HIS_PROC();
        R000_WRITE_NON_FIN_HIS_PROC();
    }
    public void B070_03_CANCEL_PROC1() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDRHIS.RSEQ);
        CEP.TRC(SCCGWA, GDRHIS.AC);
        CEP.TRC(SCCGWA, GDRHIS.AC_SEQ);
        CEP.TRC(SCCGWA, GDRHIS.TR_AMT);
        CEP.TRC(SCCGWA, GDRPLDR.RELAT_STS);
        WS_CANCEL_AC = GDRHIS.AC;
        WS_CANCEL_AMT = GDRHIS.TR_AMT;
        WS_CANCEL_AC_SEQ = GDRHIS.AC_SEQ;
        IBS.init(SCCGWA, GDRPLDR);
        if (GDRHIS.RSEQ.trim().length() > 0) {
            GDRPLDR.KEY.RSEQ = GDRHIS.RSEQ;
            GDRPLDR.KEY.AC = GDRHIS.AC;
            GDRPLDR.KEY.AC_SEQ = GDRHIS.AC_SEQ;
            CEP.TRC(SCCGWA, GDRPLDR.KEY.RSEQ);
            CEP.TRC(SCCGWA, GDRPLDR.KEY.AC);
            CEP.TRC(SCCGWA, GDRPLDR.KEY.AC_SEQ);
            T000_READ_GDTPLDR1();
        } else {
            GDRPLDR.DEAL_CD = GDRHIS.DEAL_CD;
            GDRPLDR.BSREF = GDRHIS.BSREF;
            GDRPLDR.SYS_NO = GDCUMPLD.SYS_NO;
            GDRPLDR.KEY.AC = GDRHIS.AC;
            GDRPLDR.KEY.AC_SEQ = GDRHIS.AC_SEQ;
            CEP.TRC(SCCGWA, GDRPLDR.DEAL_CD);
            CEP.TRC(SCCGWA, GDRPLDR.BSREF);
            CEP.TRC(SCCGWA, GDRPLDR.SYS_NO);
            T000_READ_GDTPLDR2();
        }
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = GDRHIS.AC;
        CICQACAC.DATA.AGR_SEQ = GDRHIS.AC_SEQ;
        CICQACAC.DATA.CCY_ACAC = GDRPLDR.CCY;
        CICQACAC.DATA.CR_FLG = GDRPLDR.CCY_TYP;
        S000_CALL_CIZQACAC();
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        WS_CANCEL_ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        CEP.TRC(SCCGWA, GDRPLDR.RELAT_STS);
        CEP.TRC(SCCGWA, GDRPLDR.AC_TYP);
        WS_CANCEL_AC_TYP = GDRPLDR.AC_TYP;
        if (GDRPLDR.RELAT_STS != 'R') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_PLDR_NOT_RELEASED;
            S000_ERR_MSG_PROC();
        } else {
            CEP.TRC(SCCGWA, WS_CANCEL_AMT);
            if (WS_CANCEL_AMT != 0) {
                GDRPLDR.RELAT_AMT = GDRPLDR.RELAT_AMT + WS_CANCEL_AMT;
            }
            GDRPLDR.RELAT_STS = 'N';
            GDRPLDR.RELS_DATE = 0;
            GDRPLDR.RELS_TIME = 0;
            GDRPLDR.RELS_BR = 0;
            GDRPLDR.RELS_CHNL_CD = " ";
            GDRPLDR.RELS_USR = " ";
            T000_REWRITE_GDTPLDR();
            CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE);
            CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO);
            GDRHIS.CAN_FLG = 'C';
            GDRHIS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            GDRHIS.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_GDTHIS();
            CEP.TRC(SCCGWA, GDCUMPLD.AC_TYPE);
            CEP.TRC(SCCGWA, GDRPLDR.AC_TYP);
            if (GDRPLDR.AC_TYP == '0') {
                IBS.init(SCCGWA, DDRCCY);
                CEP.TRC(SCCGWA, GDRPLDR.CCY);
                if (GDRPLDR.CCY.equalsIgnoreCase("156")) {
                    DDRCCY.CCY_TYPE = '1';
                } else {
                    DDRCCY.CCY_TYPE = '2';
                }
                DDRCCY.CCY = GDRPLDR.CCY;
                IBS.init(SCCGWA, CICQACAC);
                CICQACAC.FUNC = 'R';
                CICQACAC.DATA.AGR_NO = WS_CANCEL_AC;
                CICQACAC.DATA.AGR_SEQ = WS_CANCEL_AC_SEQ;
                S000_CALL_CIZQACAC();
                CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
                DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
                T000_READUPD_DDTCCY();
                WS_AVA_RELAT_AMT = DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL - DDRCCY.MARGIN_BAL;
                CEP.TRC(SCCGWA, WS_AVA_RELAT_AMT);
                if (WS_CANCEL_AMT > WS_AVA_RELAT_AMT) {
                    WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_RL_AMT_GT_LMT;
                    S000_ERR_MSG_PROC();
                }
                CEP.TRC(SCCGWA, DDRCCY.MARGIN_BAL);
                CEP.TRC(SCCGWA, WS_CANCEL_AMT);
                DDRCCY.MARGIN_BAL = DDRCCY.MARGIN_BAL + WS_CANCEL_AMT;
                T000_UPDATE_DDTCCY();
            } else {
                CEP.TRC(SCCGWA, WS_CANCEL_AC);
                IBS.init(SCCGWA, TDRSMST);
                if (GDRHIS.AC_SEQ != 0) {
                    IBS.init(SCCGWA, CICQACAC);
                    CICQACAC.FUNC = 'R';
                    CICQACAC.DATA.AGR_NO = GDCUMPLD.AC;
                    CICQACAC.DATA.AGR_SEQ = GDCUMPLD.AC_SEQ;
                    S000_CALL_CIZQACAC();
                    CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
                    TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
                } else {
                    TDRSMST.KEY.ACO_AC = WS_CANCEL_ACO_AC;
                }
                T000_READ_TDTSMST();
                WS_AVA_RELAT_AMT = TDRSMST.BAL - TDRSMST.HBAL - TDRSMST.GUAR_BAL;
                CEP.TRC(SCCGWA, WS_AVA_RELAT_AMT);
                if (WS_CANCEL_AMT > WS_AVA_RELAT_AMT) {
                    WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_RL_AMT_GT_LMT;
                    S000_ERR_MSG_PROC();
                }
                CEP.TRC(SCCGWA, TDRSMST.GUAR_BAL);
                CEP.TRC(SCCGWA, WS_CANCEL_AMT);
                TDRSMST.GUAR_BAL = TDRSMST.GUAR_BAL + WS_CANCEL_AMT;
                TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
                TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
                T000_UPDATE_TDTSMST();
            }
        }
    }
    public void B070_03_CANCEL_PROC2() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDRHIS.RSEQ);
        CEP.TRC(SCCGWA, GDRHIS.AC);
        CEP.TRC(SCCGWA, GDRHIS.AC_SEQ);
        CEP.TRC(SCCGWA, GDRHIS.TR_AMT);
        IBS.init(SCCGWA, GDRPLDR);
        if (GDRHIS.RSEQ.trim().length() > 0) {
            GDRPLDR.KEY.RSEQ = GDRHIS.RSEQ;
            GDRPLDR.KEY.AC = GDRHIS.AC;
            GDRPLDR.KEY.AC_SEQ = GDRHIS.AC_SEQ;
            CEP.TRC(SCCGWA, GDRPLDR.KEY.RSEQ);
            CEP.TRC(SCCGWA, GDRPLDR.KEY.AC);
            CEP.TRC(SCCGWA, GDRPLDR.KEY.AC_SEQ);
            T000_READ_GDTPLDR1();
        } else {
            GDRPLDR.DEAL_CD = GDRHIS.DEAL_CD;
            GDRPLDR.BSREF = GDRHIS.BSREF;
            GDRPLDR.SYS_NO = GDCUMPLD.SYS_NO;
            GDRPLDR.KEY.AC = GDRHIS.AC;
            GDRPLDR.KEY.AC_SEQ = GDRHIS.AC_SEQ;
            CEP.TRC(SCCGWA, GDRPLDR.DEAL_CD);
            CEP.TRC(SCCGWA, GDRPLDR.BSREF);
            CEP.TRC(SCCGWA, GDRPLDR.SYS_NO);
            T000_READ_GDTPLDR2();
        }
        CEP.TRC(SCCGWA, GDRPLDR.AC_TYP);
        CEP.TRC(SCCGWA, GDRHIS.TR_AC);
        if (GDRPLDR.AC_TYP == '0') {
            IBS.init(SCCGWA, DDCUDRAC);
            DDCUDRAC.TX_TYPE = 'T';
            DDCUDRAC.GD_WITHDR_FLG = 'Y';
            DDCUDRAC.AC = GDRPLDR.KEY.AC;
            DDCUDRAC.CCY = GDRPLDR.CCY;
            if (GDRPLDR.CCY.equalsIgnoreCase("156")) {
                DDCUDRAC.CCY_TYPE = '1';
            } else {
                DDCUDRAC.CCY_TYPE = '2';
            }
            DDCUDRAC.TX_AMT = GDRHIS.TR_AMT;
            DDCUDRAC.TX_MMO = "A019";
            DDCUDRAC.OTHER_AC = GDRHIS.TR_AC;
            S000_CALL_DDZUDRAC();
        } else {
            IBS.init(SCCGWA, TDCACDRU);
            if (GDRPLDR.KEY.AC_SEQ != 0) {
                IBS.init(SCCGWA, CICQACAC);
                CICQACAC.FUNC = 'R';
                CICQACAC.DATA.AGR_NO = GDRPLDR.KEY.AC;
                CICQACAC.DATA.AGR_SEQ = GDRPLDR.KEY.AC_SEQ;
                S000_CALL_CIZQACAC();
                CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO);
                TDCACDRU.AC_SEQ = GDRPLDR.KEY.AC_SEQ;
                TDCACDRU.MAC_CNO = GDRPLDR.KEY.AC;
                IBS.init(SCCGWA, TDRSMST);
                TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
                T000_READ_TDTSMST();
                CEP.TRC(SCCGWA, TDRSMST.BAL);
            } else {
                TDCACDRU.MAC_CNO = GDRPLDR.KEY.AC;
                IBS.init(SCCGWA, TDRSMST);
                TDRSMST.KEY.ACO_AC = WS_ACO_AC;
                T000_READ_TDTSMST();
                CEP.TRC(SCCGWA, TDRSMST.BAL);
            }
            TDCACDRU.CCY = GDRPLDR.CCY;
            if (GDRPLDR.CCY.equalsIgnoreCase("156")) {
                TDCACDRU.CCY_TYP = '1';
            } else {
                TDCACDRU.CCY_TYP = '2';
            }
            CEP.TRC(SCCGWA, GDRHIS.TR_AMT);
            CEP.TRC(SCCGWA, GDRPLDR.RELAT_AMT);
            TDCACDRU.TXN_AMT = GDRHIS.TR_AMT;
            TDCACDRU.PRDMO_CD = "MMDP";
            TDCACDRU.DRAW_MTH = '4';
            if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
            JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
            TDCACDRU.BUSI_CTLW = TDCACDRU.BUSI_CTLW.substring(0, 2 - 1) + "1" + TDCACDRU.BUSI_CTLW.substring(2 + 1 - 1);
            TDCACDRU.TXN_MMO = "A019";
            TDCACDRU.OPP_AC_CNO = GDRHIS.TR_AC;
            if (TDRSMST.BAL > GDRHIS.TR_AMT) {
                TDCACDRU.OPT = '8';
            } else {
                TDCACDRU.OPT = '9';
            }
            S000_CALL_TDZACDRU();
            CEP.TRC(SCCGWA, TDCACDRU.INT_AC);
            CEP.TRC(SCCGWA, TDCACDRU.PAYING_INT);
            if ((TDCACDRU.PAYING_INT != 0) 
                && (TDCACDRU.INT_AC.trim().length() > 0)) {
                IBS.init(SCCGWA, DDCUCRAC);
                DDCUCRAC.TX_TYPE = 'T';
                DDCUCRAC.CARD_NO = TDCACDRU.INT_AC;
                DDCUCRAC.CCY = GDRPLDR.CCY;
                if (GDRPLDR.CCY.equalsIgnoreCase("156")) {
                    DDCUCRAC.CCY_TYPE = '1';
                } else {
                    DDCUCRAC.CCY_TYPE = '2';
                }
                DDCUCRAC.TX_AMT = TDCACDRU.PAYING_INT;
                DDCUCRAC.TX_MMO = "A001";
                if (GDRPLDR.KEY.AC_SEQ != 0) {
                    DDCUCRAC.OTHER_AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
                } else {
                    DDCUCRAC.OTHER_AC = GDRPLDR.KEY.AC;
                }
                S000_CALL_DDZUCRAC();
            } else {
                if ((TDCACDRU.PAYING_INT != 0) 
                    && (TDCACDRU.INT_AC.trim().length() == 0)) {
                    IBS.init(SCCGWA, AICPQIA);
                    AICPQIA.CD.AC_TYP = "2";
                    AICPQIA.CD.BUSI_KND = K_GUAOUT;
                    AICPQIA.BR = TDRSMST.OWNER_BR;
                    AICPQIA.CCY = GDRPLDR.CCY;
                    AICPQIA.SIGN = 'C';
                    S000_CALL_AIZPQIA();
                    CEP.TRC(SCCGWA, AICPQIA.AC);
                    IBS.init(SCCGWA, AICUUPIA);
                    AICUUPIA.DATA.AC_NO = AICPQIA.AC;
                    AICUUPIA.DATA.RVS_SEQ = 0;
                    AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                    AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    AICUUPIA.DATA.AMT = TDCACDRU.PAYING_INT;
                    AICUUPIA.DATA.CCY = GDRPLDR.CCY;
                    AICUUPIA.DATA.EVENT_CODE = "CR";
                    AICUUPIA.DATA.POST_NARR = " ";
                    AICUUPIA.DATA.RVS_NO = " ";
                    AICUUPIA.DATA.EVENT_CODE = "CR";
                    S000_CALL_AIZUUPIA();
                }
            }
        }
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = GDRHIS.TR_AC;
        S000_CALL_CIZQACRI();
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_AGR_NO);
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_ENTY_TYP);
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
            IBS.init(SCCGWA, AICUUPIA);
            AICUUPIA.DATA.AC_NO = GDRHIS.TR_AC;
            AICUUPIA.DATA.RVS_SEQ = 0;
            AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            AICUUPIA.DATA.AMT = GDRHIS.TR_AMT;
            AICUUPIA.DATA.CCY = GDRPLDR.CCY;
            AICUUPIA.DATA.EVENT_CODE = "CR";
            AICUUPIA.DATA.POST_NARR = " ";
            AICUUPIA.DATA.RVS_NO = " ";
            AICUUPIA.DATA.EVENT_CODE = "CR";
            S000_CALL_AIZUUPIA();
        } else {
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
                IBS.init(SCCGWA, DDCUCRAC);
                DDCUCRAC.TX_TYPE = 'T';
                DDCUCRAC.AC = GDRHIS.TR_AC;
                DDCUCRAC.CCY = GDRPLDR.CCY;
                if (GDRPLDR.CCY.equalsIgnoreCase("156")) {
                    DDCUCRAC.CCY_TYPE = '1';
                } else {
                    DDCUCRAC.CCY_TYPE = '2';
                }
                DDCUCRAC.TX_AMT = GDRHIS.TR_AMT;
                DDCUCRAC.TX_MMO = "A001";
                if (GDRPLDR.KEY.AC_SEQ != 0) {
                    DDCUCRAC.OTHER_AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
                } else {
                    DDCUCRAC.OTHER_AC = GDRPLDR.KEY.AC;
                }
                S000_CALL_DDZUCRAC();
            }
        }
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE);
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO);
        GDRHIS.CAN_FLG = 'C';
        GDRHIS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRHIS.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_GDTHIS();
    }
    public void B070_03_CANCEL_PROC3() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDRHIS.RSEQ);
        CEP.TRC(SCCGWA, GDRHIS.AC);
        CEP.TRC(SCCGWA, GDRHIS.AC_SEQ);
        CEP.TRC(SCCGWA, GDRHIS.TR_AMT);
        IBS.init(SCCGWA, GDRPLDR);
        if (GDRHIS.RSEQ.trim().length() > 0) {
            GDRPLDR.KEY.RSEQ = GDRHIS.RSEQ;
            GDRPLDR.KEY.AC = GDRHIS.AC;
            GDRPLDR.KEY.AC_SEQ = GDRHIS.AC_SEQ;
            CEP.TRC(SCCGWA, GDRPLDR.KEY.RSEQ);
            CEP.TRC(SCCGWA, GDRPLDR.KEY.AC);
            CEP.TRC(SCCGWA, GDRPLDR.KEY.AC_SEQ);
            T000_READ_GDTPLDR1();
        } else {
            GDRPLDR.DEAL_CD = GDRHIS.DEAL_CD;
            GDRPLDR.BSREF = GDRHIS.BSREF;
            GDRPLDR.SYS_NO = GDCUMPLD.SYS_NO;
            GDRPLDR.KEY.AC = GDRHIS.AC;
            GDRPLDR.KEY.AC_SEQ = GDRHIS.AC_SEQ;
            CEP.TRC(SCCGWA, GDRPLDR.DEAL_CD);
            CEP.TRC(SCCGWA, GDRPLDR.BSREF);
            CEP.TRC(SCCGWA, GDRPLDR.SYS_NO);
            T000_READ_GDTPLDR2();
        }
        CEP.TRC(SCCGWA, GDRPLDR.AC_TYP);
        CEP.TRC(SCCGWA, GDRHIS.TR_AC);
        if (GDRPLDR.AC_TYP == '0') {
            IBS.init(SCCGWA, DDCUDRAC);
            DDCUDRAC.TX_TYPE = 'T';
            DDCUDRAC.GD_WITHDR_FLG = 'Y';
            DDCUDRAC.AC = GDRPLDR.KEY.AC;
            DDCUDRAC.CCY = GDRPLDR.CCY;
            if (GDRPLDR.CCY.equalsIgnoreCase("156")) {
                DDCUDRAC.CCY_TYPE = '1';
            } else {
                DDCUDRAC.CCY_TYPE = '2';
            }
            DDCUDRAC.TX_AMT = GDRHIS.TR_AMT;
            DDCUDRAC.TX_MMO = "A019";
            DDCUDRAC.OTHER_AC = GDRHIS.TR_AC;
            S000_CALL_DDZUDRAC();
        } else {
            IBS.init(SCCGWA, TDCACDRU);
            if (GDRPLDR.KEY.AC_SEQ != 0) {
                IBS.init(SCCGWA, CICQACAC);
                CICQACAC.FUNC = 'R';
                CICQACAC.DATA.AGR_NO = GDRPLDR.KEY.AC;
                CICQACAC.DATA.AGR_SEQ = GDRPLDR.KEY.AC_SEQ;
                S000_CALL_CIZQACAC();
                CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO);
                TDCACDRU.AC_SEQ = GDRPLDR.KEY.AC_SEQ;
                TDCACDRU.MAC_CNO = GDRPLDR.KEY.AC;
                IBS.init(SCCGWA, TDRSMST);
                TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
                T000_READ_TDTSMST();
                CEP.TRC(SCCGWA, TDRSMST.BAL);
            } else {
                TDCACDRU.MAC_CNO = GDRPLDR.KEY.AC;
                IBS.init(SCCGWA, TDRSMST);
                TDRSMST.KEY.ACO_AC = WS_ACO_AC;
                T000_READ_TDTSMST();
                CEP.TRC(SCCGWA, TDRSMST.BAL);
            }
            TDCACDRU.CCY = GDRPLDR.CCY;
            if (GDRPLDR.CCY.equalsIgnoreCase("156")) {
                TDCACDRU.CCY_TYP = '1';
            } else {
                TDCACDRU.CCY_TYP = '2';
            }
            CEP.TRC(SCCGWA, GDRHIS.TR_AMT);
            CEP.TRC(SCCGWA, GDRPLDR.RELAT_AMT);
            TDCACDRU.TXN_AMT = GDRHIS.TR_AMT;
            TDCACDRU.PRDMO_CD = "MMDP";
            TDCACDRU.DRAW_MTH = '4';
            if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
            JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
            TDCACDRU.BUSI_CTLW = TDCACDRU.BUSI_CTLW.substring(0, 2 - 1) + "1" + TDCACDRU.BUSI_CTLW.substring(2 + 1 - 1);
            DDCUDRAC.TX_MMO = "A019";
            TDCACDRU.OPP_AC_CNO = GDRHIS.TR_AC;
            if (TDRSMST.BAL > GDRHIS.TR_AMT) {
                TDCACDRU.OPT = '8';
            } else {
                TDCACDRU.OPT = '9';
            }
            S000_CALL_TDZACDRU();
            CEP.TRC(SCCGWA, TDCACDRU.INT_AC);
            CEP.TRC(SCCGWA, TDCACDRU.PAYING_INT);
            if ((TDCACDRU.PAYING_INT != 0) 
                && (TDCACDRU.INT_AC.trim().length() > 0)) {
                IBS.init(SCCGWA, DDCUCRAC);
                DDCUCRAC.TX_TYPE = 'T';
                DDCUCRAC.CARD_NO = TDCACDRU.INT_AC;
                DDCUCRAC.CCY = GDRPLDR.CCY;
                if (GDRPLDR.CCY.equalsIgnoreCase("156")) {
                    DDCUCRAC.CCY_TYPE = '1';
                } else {
                    DDCUCRAC.CCY_TYPE = '2';
                }
                DDCUCRAC.TX_AMT = TDCACDRU.PAYING_INT;
                DDCUCRAC.TX_MMO = "A001";
                if (GDRPLDR.KEY.AC_SEQ != 0) {
                    DDCUCRAC.OTHER_AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
                } else {
                    DDCUCRAC.OTHER_AC = GDRPLDR.KEY.AC;
                }
                S000_CALL_DDZUCRAC();
            } else {
                if ((TDCACDRU.PAYING_INT != 0) 
                    && (TDCACDRU.INT_AC.trim().length() == 0)) {
                    IBS.init(SCCGWA, AICPQIA);
                    AICPQIA.CD.AC_TYP = "2";
                    AICPQIA.CD.BUSI_KND = K_GUAOUT;
                    AICPQIA.BR = TDRSMST.OWNER_BR;
                    AICPQIA.CCY = GDRPLDR.CCY;
                    AICPQIA.SIGN = 'C';
                    S000_CALL_AIZPQIA();
                    CEP.TRC(SCCGWA, AICPQIA.AC);
                    IBS.init(SCCGWA, AICUUPIA);
                    AICUUPIA.DATA.AC_NO = AICPQIA.AC;
                    AICUUPIA.DATA.RVS_SEQ = 0;
                    AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                    AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    AICUUPIA.DATA.AMT = TDCACDRU.PAYING_INT;
                    AICUUPIA.DATA.CCY = GDRPLDR.CCY;
                    AICUUPIA.DATA.EVENT_CODE = "CR";
                    AICUUPIA.DATA.POST_NARR = " ";
                    AICUUPIA.DATA.RVS_NO = " ";
                    AICUUPIA.DATA.EVENT_CODE = "CR";
                    S000_CALL_AIZUUPIA();
                }
            }
        }
        CEP.TRC(SCCGWA, GDRHIS.TR_AC);
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = GDRHIS.TR_AC;
        AICUUPIA.DATA.RVS_SEQ = 0;
        AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.AMT = GDRHIS.TR_AMT;
        AICUUPIA.DATA.CCY = GDRPLDR.CCY;
        AICUUPIA.DATA.EVENT_CODE = "CR";
        AICUUPIA.DATA.POST_NARR = " ";
        AICUUPIA.DATA.RVS_NO = " ";
        AICUUPIA.DATA.EVENT_CODE = "CR";
        S000_CALL_AIZUUPIA();
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE);
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO);
        GDRHIS.CAN_FLG = 'C';
        GDRHIS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRHIS.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_GDTHIS();
    }
    public void B080_NOTICE_CMS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDRPLDR.RELAT_CHNL_CD);
        if (GDRPLDR.RELAT_CHNL_CD.equalsIgnoreCase("CMS")) {
            WS_CMS.WS_RSEQ = GDRPLDR.KEY.RSEQ;
            WS_CMS.WS_CTA = GDRPLDR.DEAL_CD;
            WS_CMS.WS_REF_NO = GDRPLDR.BSREF;
            CEP.TRC(SCCGWA, "ALL RELAT AMT");
            IBS.init(SCCGWA, GDRPLDR);
            GDRPLDR.KEY.RSEQ = GDCUMPLD.RSEQ;
            GDRPLDR.RELAT_STS = 'N';
            T000_GROUP_GDTPLDR();
            CEP.TRC(SCCGWA, WS_ALL_RELAT_AMT);
            WS_CMS.WS_RAMT = WS_ALL_RELAT_AMT;
            IBS.init(SCCGWA, SCCTPCL);
            SCCTPCL.SERV_AREA.SERV_CODE = K_SERV_CODE;
            SCCTPCL.SERV_AREA.SERV_TYPE = ' ';
            SCCTPCL.INP_AREA.SERV_DATA_LEN = 89;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, WS_CMS);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_CSIF_NOTICE);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_BVMS_INQ);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_BH_FILE_SEND);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_BH_FILE_ECIF);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_AC_CHG_INF);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ACGL_GL0002);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_PASSWORD_CHK);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_SMS_INFO);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ECIF_SG0001);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_CHG_NOTICE);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ACGL_GL0001);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ECIF_AC0001);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.SN_SMS_NOTICE);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ECIF_AC0002);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.CC_CHANGE_CARD);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.SC_SCF_NOTICE);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ECIF_AC0003);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_IBIL_NOTICE);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_BROADCAST);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_CASH_CHG);
            S000_CALL_SCZTPCL();
        }
    }
    public void R000_STAC_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_AC);
        CEP.TRC(SCCGWA, WS_AC_SEQ);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        CEP.TRC(SCCGWA, GDCUMPLD.TX_AMT);
        IBS.init(SCCGWA, GDRSTAC);
        WS_CR_FLG = ' ';
        WS_INTAC_FLG = ' ';
        WS_CSTS_FLG = ' ';
        WS_AIMIB_AC = " ";
        IBS.init(SCCGWA, TDCACDRU);
        GDRSTAC.KEY.AC = WS_AC;
        GDRSTAC.KEY.AC_SEQ = WS_AC_SEQ;
        T000_READ_GDTSTAC();
        CEP.TRC(SCCGWA, GDRSTAC.ST_AC);
        if (GDRSTAC.ST_AC.trim().length() > 0) {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.FUNC = 'A';
            CICQACRI.DATA.AGR_NO = GDRSTAC.ST_AC;
            S000_CALL_CIZQACRI();
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_AGR_NO);
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_ENTY_TYP);
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
                R000_CHECK_STS_TBL_PROC();
            }
        }
        CEP.TRC(SCCGWA, WS_CSTS_FLG);
        CEP.TRC(SCCGWA, WS_STAC_FLG);
        if (WS_STAC_FLG == 'N' 
            || WS_CSTS_FLG == 'N') {
            IBS.init(SCCGWA, AICPQIA);
            AICPQIA.CD.AC_TYP = "2";
            AICPQIA.CD.BUSI_KND = K_GUAOUT;
            if (GDRPLDR.AC_TYP == '0') {
                AICPQIA.BR = DDRMST.OWNER_BR;
            } else {
                AICPQIA.BR = TDRSMST.OWNER_BR;
            }
            AICPQIA.CCY = GDCUMPLD.CCY;
            AICPQIA.SIGN = 'C';
            WS_CR_FLG = 'N';
            S000_CALL_AIZPQIA();
            CEP.TRC(SCCGWA, AICPQIA.AC);
            WS_AIMIB_AC = AICPQIA.AC;
        }
        if (GDCUMPLD.TX_AMT != 0) {
            if (GDRPLDR.AC_TYP == '0') {
                IBS.init(SCCGWA, DDCUDRAC);
                DDCUDRAC.TX_TYPE = 'T';
                DDCUDRAC.GD_WITHDR_FLG = 'Y';
                DDCUDRAC.AC = WS_AC;
                DDCUDRAC.CCY = GDCUMPLD.CCY;
                DDCUDRAC.CCY_TYPE = GDCUMPLD.CCY_TYPE;
                DDCUDRAC.TX_AMT = GDCUMPLD.TX_AMT;
                DDCUDRAC.TX_MMO = "A019";
                if (WS_AIMIB_AC.trim().length() > 0) {
                    DDCUDRAC.OTHER_AC = WS_AIMIB_AC;
                } else {
                    DDCUDRAC.OTHER_AC = CICQACRI.O_DATA.O_AGR_NO;
                }
                S000_CALL_DDZUDRAC();
            } else {
                CEP.TRC(SCCGWA, GDCUMPLD.AC);
                IBS.init(SCCGWA, TDCACDRU);
                if (WS_AC_SEQ != 0) {
                    TDCACDRU.AC_SEQ = WS_AC_SEQ;
                    TDCACDRU.MAC_CNO = WS_AC;
                } else {
                    TDCACDRU.MAC_CNO = WS_AC;
                }
                TDCACDRU.CCY = GDCUMPLD.CCY;
                TDCACDRU.CCY_TYP = GDCUMPLD.CCY_TYPE;
                CEP.TRC(SCCGWA, GDCUMPLD.TX_AMT);
                CEP.TRC(SCCGWA, GDRPLDR.RELAT_AMT);
                TDCACDRU.TXN_AMT = GDCUMPLD.TX_AMT;
                TDCACDRU.PRDMO_CD = "MMDP";
                TDCACDRU.DRAW_MTH = '4';
                if (TDCACDRU.BUSI_CTLW == null) TDCACDRU.BUSI_CTLW = "";
                JIBS_tmp_int = TDCACDRU.BUSI_CTLW.length();
                for (int i=0;i<32-JIBS_tmp_int;i++) TDCACDRU.BUSI_CTLW += " ";
                TDCACDRU.BUSI_CTLW = TDCACDRU.BUSI_CTLW.substring(0, 2 - 1) + "1" + TDCACDRU.BUSI_CTLW.substring(2 + 1 - 1);
                TDCACDRU.TXN_MMO = "A019";
                if (WS_AIMIB_AC.trim().length() > 0) {
                    TDCACDRU.OPP_AC_CNO = WS_AIMIB_AC;
                } else {
                    TDCACDRU.OPP_AC_CNO = CICQACRI.O_DATA.O_AGR_NO;
                }
                CEP.TRC(SCCGWA, TDRSMST.BAL);
                if (TDRSMST.BAL > GDCUMPLD.TX_AMT) {
                    TDCACDRU.OPT = '8';
                } else {
                    TDCACDRU.OPT = '9';
                }
                S000_CALL_TDZACDRU();
                CEP.TRC(SCCGWA, TDCACDRU.PAYING_INT);
                CEP.TRC(SCCGWA, TDCACDRU.INT_AC);
                if ((TDCACDRU.PAYING_INT != 0) 
                    && (TDCACDRU.INT_AC.trim().length() == 0)) {
                    if (WS_AIMIB_AC.trim().length() == 0) {
                        IBS.init(SCCGWA, AICPQIA);
                        AICPQIA.CD.AC_TYP = "2";
                        AICPQIA.CD.BUSI_KND = K_GUAOUT;
                        if (GDRPLDR.AC_TYP == '0') {
                            AICPQIA.BR = DDRMST.OWNER_BR;
                        } else {
                            AICPQIA.BR = TDRSMST.OWNER_BR;
                        }
                        AICPQIA.CCY = GDCUMPLD.CCY;
                        AICPQIA.SIGN = 'C';
                        S000_CALL_AIZPQIA();
                        CEP.TRC(SCCGWA, AICPQIA.AC);
                        WS_AIMIB_AC = AICPQIA.AC;
                    }
                    WS_INTAC_FLG = 'N';
                }
            }
        }
        CEP.TRC(SCCGWA, WS_CR_FLG);
        if (WS_CR_FLG == 'N') {
            CEP.TRC(SCCGWA, WS_AIMIB_AC);
            IBS.init(SCCGWA, AICUUPIA);
            AICUUPIA.DATA.AC_NO = WS_AIMIB_AC;
            AICUUPIA.DATA.RVS_SEQ = 0;
            AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            AICUUPIA.DATA.AMT = GDCUMPLD.TX_AMT;
            AICUUPIA.DATA.CCY = GDCUMPLD.CCY;
            AICUUPIA.DATA.EVENT_CODE = "CR";
            AICUUPIA.DATA.POST_NARR = " ";
            AICUUPIA.DATA.RVS_NO = " ";
            AICUUPIA.DATA.EVENT_CODE = "CR";
            S000_CALL_AIZUUPIA();
        } else {
            CEP.TRC(SCCGWA, "NOT HAND");
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
                IBS.init(SCCGWA, AICUUPIA);
                AICUUPIA.DATA.AC_NO = GDRSTAC.ST_AC;
                AICUUPIA.DATA.RVS_SEQ = 0;
                AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
                AICUUPIA.DATA.AMT = GDCUMPLD.TX_AMT;
                AICUUPIA.DATA.CCY = GDCUMPLD.CCY;
                AICUUPIA.DATA.EVENT_CODE = "CR";
                AICUUPIA.DATA.POST_NARR = " ";
                AICUUPIA.DATA.RVS_NO = " ";
                AICUUPIA.DATA.EVENT_CODE = "CR";
                S000_CALL_AIZUUPIA();
            } else {
                if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
                    IBS.init(SCCGWA, DDCUCRAC);
                    DDCUCRAC.TX_TYPE = 'T';
                    DDCUCRAC.AC = GDRSTAC.ST_AC;
                    DDCUCRAC.CCY = GDCUMPLD.CCY;
                    DDCUCRAC.CCY_TYPE = GDCUMPLD.CCY_TYPE;
                    DDCUCRAC.TX_AMT = GDCUMPLD.TX_AMT;
                    DDCUCRAC.TX_MMO = "A001";
                    if (WS_AC_SEQ != 0) {
                        DDCUCRAC.OTHER_AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
                    } else {
                        DDCUCRAC.OTHER_AC = WS_AC;
                    }
                    S000_CALL_DDZUCRAC();
                }
            }
        }
        CEP.TRC(SCCGWA, WS_CR_FLG);
        CEP.TRC(SCCGWA, WS_INTAC_FLG);
        if (GDRPLDR.AC_TYP == '1') {
            if (TDCACDRU.PAYING_INT != 0) {
                if (WS_INTAC_FLG == 'N') {
                    CEP.TRC(SCCGWA, WS_AIMIB_AC);
                    CEP.TRC(SCCGWA, "INT");
                    IBS.init(SCCGWA, AICUUPIA);
                    AICUUPIA.DATA.AC_NO = WS_AIMIB_AC;
                    AICUUPIA.DATA.RVS_SEQ = 0;
                    AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                    AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    AICUUPIA.DATA.AMT = TDCACDRU.PAYING_INT;
                    AICUUPIA.DATA.CCY = GDCUMPLD.CCY;
                    AICUUPIA.DATA.EVENT_CODE = "CR";
                    AICUUPIA.DATA.POST_NARR = " ";
                    AICUUPIA.DATA.RVS_NO = " ";
                    AICUUPIA.DATA.EVENT_CODE = "CR";
                    S000_CALL_AIZUUPIA();
                } else {
                    IBS.init(SCCGWA, DDCUCRAC);
                    DDCUCRAC.TX_TYPE = 'T';
                    DDCUCRAC.AC = TDCACDRU.INT_AC;
                    DDCUCRAC.CCY = GDCUMPLD.CCY;
                    DDCUCRAC.CCY_TYPE = GDCUMPLD.CCY_TYPE;
                    DDCUCRAC.TX_AMT = TDCACDRU.PAYING_INT;
                    DDCUCRAC.TX_MMO = "A001";
                    DDCUCRAC.OTHER_AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
                    S000_CALL_DDZUCRAC();
                }
            }
        }
    }
    public void R100_WRITE_HIS_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA.BA_CNT = (short) (GWA_SC_AREA.BA_CNT + 1);
        CEP.TRC(SCCGWA, GWA_SC_AREA.BA_CNT);
        IBS.init(SCCGWA, GDRHIS);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        CEP.TRC(SCCGWA, AICPQIA.AC);
        CEP.TRC(SCCGWA, GDRSTAC.ST_AC);
        GDCRHIS.FUNC = 'A';
        GDRHIS.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRHIS.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        GDRHIS.KEY.SEQ = GWA_SC_AREA.BA_CNT;
        GDRHIS.RSEQ = GDCUMPLD.RSEQ;
        GDRHIS.AC = WS_AC;
        GDRHIS.AC_SEQ = WS_AC_SEQ;
        if (GDCUMPLD.FUNC == 'D') {
            if (WS_CR_FLG == 'N') {
                GDRHIS.FUNC = '9';
                GDRHIS.TR_AC = AICPQIA.AC;
            } else {
                GDRHIS.FUNC = '8';
                GDRHIS.TR_AC = GDRSTAC.ST_AC;
            }
        }
        GDRHIS.DEAL_CD = GDCUMPLD.CTA_NO;
        GDRHIS.BSREF = GDCUMPLD.REF_NO;
        GDRHIS.CHNL_CD = SCCGWA.COMM_AREA.REQ_SYSTEM;
        GDRHIS.TR_AMT = GDCUMPLD.TX_AMT;
        GDRHIS.CAN_FLG = 'N';
        GDRHIS.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRHIS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        GDRHIS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRHIS.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        GDCRHIS.REC_PTR = GDRHIS;
        GDCRHIS.REC_LEN = 281;
        S000_CALL_GDZRHIS();
    }
    public void R000_CHECK_STS_TBL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        T000_READ_DDTMST();
        IBS.init(SCCGWA, BPCFCSTS);
        BPCFCSTS.AP_MMO = K_APP_MMO;
        BPCFCSTS.TBL_NO = K_CHK_STS_TBL;
        BPCFCSTS.STATUS_WORD = "ZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZERO";
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 101 - 1) + DDRMST.AC_STS_WORD + BPCFCSTS.STATUS_WORD.substring(101 + 99 - 1);
        S000_CALL_BPZFCSTS();
        if (WS_CSTS_FLG != 'N') {
            IBS.init(SCCGWA, DDCICHAC);
            DDCICHAC.TX_TYPE = 'T';
            DDCICHAC.AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
            DDCICHAC.CCY = GDCUMPLD.CCY;
            DDCICHAC.CCY_TYPE = GDCUMPLD.CCY_TYPE;
            DDCICHAC.TX_AMT = GDCUMPLD.TX_AMT;
            DDCICHAC.TX_MMO = "A001";
            S000_CALL_DDZICHAC();
        }
    }
    public void R000_UPD_MARG_BAL_PROC() throws IOException,SQLException,Exception {
        if (GDCUMPLD.AC_TYPE == '0' 
            && (DDVMPRD.VAL.TD_FLG != '0' 
            || GDCUMPLD.AC_SEQ == 0)) {
            R000_UPD_DDTCCY_PROC();
        } else {
            R000_UPD_TDTSMST_PROC();
        }
    }
    public void R000_UPD_DDTCCY_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCUMPLD.AC);
        CEP.TRC(SCCGWA, GDCUMPLD.CCY);
        CEP.TRC(SCCGWA, GDCUMPLD.CCY_TYPE);
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = GDCUMPLD.AC;
        S000_CALL_CIZQACAC();
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRCCY.CCY = GDCUMPLD.CCY;
        DDRCCY.CCY_TYPE = GDCUMPLD.CCY_TYPE;
        T000_READUPD_DDTCCY();
        if (GDCUMPLD.FUNC == 'C'
            || GDCUMPLD.FUNC == 'A') {
            DDRCCY.MARGIN_BAL = DDRCCY.MARGIN_BAL + GDCUMPLD.TX_AMT;
        } else if (GDCUMPLD.FUNC == 'R'
            || GDCUMPLD.FUNC == 'D') {
            CEP.TRC(SCCGWA, DDRCCY.HOLD_BAL);
            CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
            WS_VAL_RBAL = DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL;
            if (WS_VAL_RBAL < GDCUMPLD.TX_AMT) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AMT_INVALID;
                S000_ERR_MSG_PROC();
            }
            DDRCCY.MARGIN_BAL = DDRCCY.MARGIN_BAL - GDCUMPLD.TX_AMT;
        }
        DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_UPDATE_DDTCCY();
    }
    public void R000_UPD_TDTSMST_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSMST);
        if (GDCUMPLD.AC_SEQ != 0) {
            TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        } else {
            TDRSMST.KEY.ACO_AC = WS_ACO_AC;
        }
        T000_READUPD_TDTSMST();
        if (GDCUMPLD.FUNC == 'C'
            || GDCUMPLD.FUNC == 'A') {
            TDRSMST.GUAR_BAL = TDRSMST.GUAR_BAL + GDCUMPLD.TX_AMT;
        } else if (GDCUMPLD.FUNC == 'R'
            || GDCUMPLD.FUNC == 'D') {
            CEP.TRC(SCCGWA, TDRSMST.HBAL);
            CEP.TRC(SCCGWA, TDRSMST.BAL);
            WS_VAL_RBAL = TDRSMST.BAL - TDRSMST.HBAL;
            if (WS_VAL_RBAL < GDCUMPLD.TX_AMT) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AMT_INVALID;
                S000_ERR_MSG_PROC();
            }
            TDRSMST.GUAR_BAL = TDRSMST.GUAR_BAL - GDCUMPLD.TX_AMT;
        }
        TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        T000_UPDATE_TDTSMST();
    }
    public void R000_WRITE_NON_FIN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.AC = GDCUMPLD.AC;
        BPCPNHIS.INFO.AC_SEQ = GDCUMPLD.AC_SEQ;
        BPCPNHIS.INFO.CCY = GDCUMPLD.CCY;
        BPCPNHIS.INFO.CCY_TYPE = GDCUMPLD.CCY_TYPE;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.REF_NO = GDCUMPLD.RSEQ;
        BPCPNHIS.INFO.CI_NO = GDCUMPLD.CI_NO;
        BPCPNHIS.INFO.TX_RMK = GDCUMPLD.REMARK;
        BPCPNHIS.INFO.TX_TYP_CD = GDCUMPLD.MMO;
        if (GDCUMPLD.FUNC == 'C') {
            if (WS_PLDR_CREATE_FLG == 'C') {
                CEP.TRC(SCCGWA, "ADD");
                BPCPNHIS.INFO.TX_TYP = 'A';
                BPCPNHIS.INFO.NEW_DAT_PT = GDCUMPLD;
            } else {
                CEP.TRC(SCCGWA, "MOD");
                BPCPNHIS.INFO.FMT_ID = "GDCOACBD";
                BPCPNHIS.INFO.TX_TYP = 'M';
                BPCPNHIS.INFO.OLD_DAT_PT = GDCUMPLD;
                BPCPNHIS.INFO.NEW_DAT_PT = GDCUMPLD;
            }
        } else if (GDCUMPLD.FUNC == 'A') {
            CEP.TRC(SCCGWA, "MOD");
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.FMT_ID = "GDCOACBD";
            BPCPNHIS.INFO.OLD_DAT_PT = GDCUMPLD;
            BPCPNHIS.INFO.NEW_DAT_PT = GDCUMPLD;
        } else if (GDCUMPLD.FUNC == 'R') {
            CEP.TRC(SCCGWA, "MOD");
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.FMT_ID = "GDCOACBD";
            BPCPNHIS.INFO.OLD_DAT_PT = GDCUMPLD;
            BPCPNHIS.INFO.NEW_DAT_PT = GDCUMPLD;
        } else if (GDCUMPLD.FUNC == 'D') {
            CEP.TRC(SCCGWA, "DEL");
            BPCPNHIS.INFO.TX_TYP = 'D';
            BPCPNHIS.INFO.OLD_DAT_PT = GDCUMPLD;
        } else {
        }
        S000_CALL_BPZPNHIS();
    }
    public void R000_WRITE_HIS_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA.BA_CNT = (short) (GWA_SC_AREA.BA_CNT + 1);
        CEP.TRC(SCCGWA, GWA_SC_AREA.BA_CNT);
        IBS.init(SCCGWA, GDRHIS);
        GDCRHIS.FUNC = 'A';
        GDRHIS.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRHIS.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        GDRHIS.KEY.SEQ = GWA_SC_AREA.BA_CNT;
        GDRHIS.RSEQ = GDCUMPLD.RSEQ;
        GDRHIS.AC = WS_AC;
        GDRHIS.AC_SEQ = WS_AC_SEQ;
        CEP.TRC(SCCGWA, WS_AC_SEQ);
        CEP.TRC(SCCGWA, GDCUMPLD.AC_SEQ);
        CEP.TRC(SCCGWA, GDRHIS.KEY.SEQ);
        CEP.TRC(SCCGWA, GDRHIS.KEY.TR_DATE);
        CEP.TRC(SCCGWA, GDRHIS.KEY.JRNNO);
        if (GDCUMPLD.FUNC == 'C') {
            if (WS_PLDR_CREATE_FLG == 'C') {
                GDRHIS.FUNC = '3';
            } else {
                GDRHIS.FUNC = '4';
            }
            if (GDCUMPLD.KDGD_FLG == 'Y') {
                GDRHIS.FUNC = '4';
            }
        } else if (GDCUMPLD.FUNC == 'A') {
            GDRHIS.FUNC = '4';
        } else if (GDCUMPLD.FUNC == 'R') {
            GDRHIS.FUNC = '5';
        } else if (GDCUMPLD.FUNC == 'D') {
            GDRHIS.FUNC = '6';
        }
        GDRHIS.DEAL_CD = GDCUMPLD.CTA_NO;
        GDRHIS.BSREF = GDCUMPLD.REF_NO;
        GDRHIS.CHNL_CD = SCCGWA.COMM_AREA.REQ_SYSTEM;
        GDRHIS.TR_AMT = GDCUMPLD.TX_AMT;
        GDRHIS.CAN_FLG = 'N';
        GDRHIS.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRHIS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        GDRHIS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRHIS.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        GDCRHIS.REC_PTR = GDRHIS;
        GDCRHIS.REC_LEN = 281;
        S000_CALL_GDZRHIS();
    }
    public void R000_WRITE_CANCEL_HIS_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA.BA_CNT = (short) (GWA_SC_AREA.BA_CNT + 1);
        CEP.TRC(SCCGWA, GWA_SC_AREA.BA_CNT);
        GDRHIS.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRHIS.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        GDRHIS.KEY.SEQ = GWA_SC_AREA.BA_CNT;
        GDRHIS.CAN_FLG = 'R';
        GDRHIS.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRHIS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        GDRHIS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRHIS.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        GDCRHIS.FUNC = 'A';
        GDCRHIS.REC_PTR = GDRHIS;
        GDCRHIS.REC_LEN = 281;
        S000_CALL_GDZRHIS();
        CEP.TRC(SCCGWA, GDRHIS);
    }
    public void R000_CRT_PLDR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRPLDR);
        GDCRPLDR.FUNC = 'A';
        GDRPLDR.KEY.RSEQ = GDCUMPLD.RSEQ;
        GDCUMPLD.O_RSEQ = GDRPLDR.KEY.RSEQ;
        GDRPLDR.KEY.AC = WS_AC;
        GDRPLDR.KEY.AC_SEQ = GDCUMPLD.AC_SEQ;
        GDRPLDR.AC_TYP = GDCUMPLD.AC_TYPE;
        GDRPLDR.CCY = GDCUMPLD.CCY;
        GDRPLDR.CCY_TYP = GDCUMPLD.CCY_TYPE;
        GDRPLDR.RELAT_WAY = GDCUMPLD.RELAT_WAY;
        GDRPLDR.SYS_NO = GDCUMPLD.SYS_NO;
        GDRPLDR.DEAL_CD = GDCUMPLD.CTA_NO;
        GDRPLDR.BSREF = GDCUMPLD.REF_NO;
        GDRPLDR.RELAT_STS = 'N';
        GDRPLDR.REF_TYP = GDCUMPLD.BUSI_TYPE;
        GDRPLDR.RELAT_AMT = GDCUMPLD.TX_AMT;
        GDCUMPLD.TOT_RAMT = GDCUMPLD.TX_AMT;
        GDRPLDR.PALT_AMT = GDCUMPLD.PN_AMT;
        GDRPLDR.MFG_DATE = GDCUMPLD.EXP_DATE;
        GDRPLDR.RELAT_TIME = SCCGWA.COMM_AREA.TR_TIME;
        GDRPLDR.RELAT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        GDRPLDR.RELAT_CHNL_CD = SCCGWA.COMM_AREA.REQ_SYSTEM;
        GDRPLDR.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRPLDR.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        GDRPLDR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRPLDR.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        WS_AC_SEQ = GDCUMPLD.AC_SEQ;
        CEP.TRC(SCCGWA, GDRPLDR.KEY.RSEQ);
        CEP.TRC(SCCGWA, GDRPLDR.KEY.AC);
        CEP.TRC(SCCGWA, GDRPLDR.KEY.AC_SEQ);
        CEP.TRC(SCCGWA, GDRPLDR.AC_TYP);
        CEP.TRC(SCCGWA, GDRPLDR.CCY);
        CEP.TRC(SCCGWA, GDRPLDR.DD_AC);
        CEP.TRC(SCCGWA, GDRPLDR.RELAT_WAY);
        CEP.TRC(SCCGWA, GDRPLDR.SYS_NO);
        CEP.TRC(SCCGWA, GDRPLDR.DEAL_CD);
        CEP.TRC(SCCGWA, GDRPLDR.BSREF);
        CEP.TRC(SCCGWA, GDRPLDR.RELAT_STS);
        CEP.TRC(SCCGWA, GDRPLDR.REF_TYP);
        CEP.TRC(SCCGWA, GDRPLDR.RELAT_AMT);
        CEP.TRC(SCCGWA, GDRPLDR.PALT_AMT);
        CEP.TRC(SCCGWA, GDRPLDR.MFG_DATE);
        CEP.TRC(SCCGWA, GDRPLDR.RELAT_TIME);
        CEP.TRC(SCCGWA, GDRPLDR.RELAT_BR);
        CEP.TRC(SCCGWA, GDRPLDR.RELAT_CHNL_CD);
        CEP.TRC(SCCGWA, GDRPLDR.CRT_DATE);
        CEP.TRC(SCCGWA, GDRPLDR.CRT_TLR);
        CEP.TRC(SCCGWA, GDRPLDR.UPDTBL_DATE);
        CEP.TRC(SCCGWA, GDRPLDR.UPDTBL_TLR);
        GDCRPLDR.REC_PTR = GDRPLDR;
        GDCRPLDR.REC_LEN = 311;
        S000_CALL_GDZRPLDR();
        if (GDCRPLDR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, GDCRPLDR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void R000_READ_PLDR_REC_FIRST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRPLDR);
        WS_PLDR_FLG = 'N';
        WS_RSEQ_FLG = 'N';
        GDRPLDR.KEY.RSEQ = GDCUMPLD.RSEQ;
        T000_READ_GDTPLDR();
    }
    public void R000_READUPD_PLDR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRPLDR);
        IBS.init(SCCGWA, GDCRPLDR);
        GDCRPLDR.FUNC = 'R';
        GDRPLDR.KEY.RSEQ = GDCUMPLD.RSEQ;
        if (GDCUMPLD.AC_TYPE == '0' 
            && DDVMPRD.VAL.TD_FLG != '0') {
            GDRPLDR.KEY.AC = GDCUMPLD.AC;
        } else {
            GDRPLDR.KEY.AC = WS_AC;
        }
        GDRPLDR.KEY.AC_SEQ = GDCUMPLD.AC_SEQ;
        GDRPLDR.DEAL_CD = GDCUMPLD.CTA_NO;
        GDRPLDR.BSREF = GDCUMPLD.REF_NO;
        GDCRPLDR.REC_PTR = GDRPLDR;
        GDCRPLDR.REC_LEN = 311;
        S000_CALL_GDZRPLDR();
        CEP.TRC(SCCGWA, "1111");
        if (GDCUMPLD.FUNC != 'C') {
            if (GDCRPLDR.RC.RC_CODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, GDCRPLDR.RC);
                S000_ERR_MSG_PROC();
            }
            if (GDRPLDR.RELAT_STS == 'R') {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_PLDR_RELEASED;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void R000_READUPD_PLDR_PROC1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRPLDR);
        GDCRPLDR.FUNC = 'R';
        GDRPLDR.KEY.RSEQ = GDCUMPLD.RSEQ;
        GDRPLDR.KEY.AC = GDCUMPLD.AC;
        GDRPLDR.KEY.AC_SEQ = GDCUMPLD.AC_SEQ;
        GDRPLDR.DEAL_CD = GDCUMPLD.CTA_NO;
        GDRPLDR.BSREF = GDCUMPLD.REF_NO;
        CEP.TRC(SCCGWA, GDCUMPLD.RSEQ);
        CEP.TRC(SCCGWA, GDCUMPLD.AC);
        CEP.TRC(SCCGWA, WS_AC);
        CEP.TRC(SCCGWA, GDCUMPLD.AC_SEQ);
        GDCRPLDR.REC_PTR = GDRPLDR;
        GDCRPLDR.REC_LEN = 311;
        S000_CALL_GDZRPLDR();
        if (GDCRPLDR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, GDCRPLDR.RC);
            S000_ERR_MSG_PROC();
        }
        if (GDRPLDR.RELAT_STS == 'R') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_PLDR_RELEASED;
            S000_ERR_MSG_PROC();
        }
    }
    public void R000_READUPD_PLDR_PROC2() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRPLDR);
        IBS.init(SCCGWA, GDCRPLDR);
        GDCRPLDR.FUNC = 'E';
        GDRPLDR.KEY.RSEQ = GDCUMPLD.RSEQ;
        if (GDCUMPLD.AC_TYPE == '0' 
            && DDVMPRD.VAL.TD_FLG != '0') {
            GDRPLDR.KEY.AC = GDCUMPLD.AC;
        } else {
            GDRPLDR.KEY.AC = WS_AC;
        }
        GDRPLDR.KEY.AC_SEQ = GDCUMPLD.AC_SEQ;
        GDRPLDR.DEAL_CD = GDCUMPLD.CTA_NO;
        GDRPLDR.BSREF = GDCUMPLD.REF_NO;
        GDCRPLDR.REC_PTR = GDRPLDR;
        GDCRPLDR.REC_LEN = 311;
        S000_CALL_GDZRPLDR();
        CEP.TRC(SCCGWA, "PROC2");
        if (GDCUMPLD.FUNC != 'C') {
            if (GDCRPLDR.RC.RC_CODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, GDCRPLDR.RC);
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void R000_READCTA_PLDR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRPLDR);
        GDCRPLDR.FUNC = 'C';
        GDRPLDR.DEAL_CD = GDCUMPLD.CTA_NO;
        GDRPLDR.BSREF = GDCUMPLD.REF_NO;
        GDRPLDR.KEY.AC = GDCUMPLD.AC;
        GDRPLDR.KEY.AC_SEQ = GDCUMPLD.AC_SEQ;
        CEP.TRC(SCCGWA, GDCUMPLD.CTA_NO);
        CEP.TRC(SCCGWA, GDCUMPLD.REF_NO);
        CEP.TRC(SCCGWA, GDCUMPLD.AC);
        CEP.TRC(SCCGWA, WS_AC);
        CEP.TRC(SCCGWA, GDCUMPLD.AC_SEQ);
        GDCRPLDR.REC_PTR = GDRPLDR;
        GDCRPLDR.REC_LEN = 311;
        S000_CALL_GDZRPLDR();
        if (GDCRPLDR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, GDCRPLDR.RC);
            S000_ERR_MSG_PROC();
        }
        if (GDRPLDR.RELAT_CHNL_CD.equalsIgnoreCase("EBIL") 
            && !GDRPLDR.RELAT_CHNL_CD.equalsIgnoreCase(SCCGWA.COMM_AREA.REQ_SYSTEM)) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CHANNEL_ERR;
            S000_ERR_MSG_PROC();
        }
        if (GDRPLDR.RELAT_STS == 'R') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_PLDR_RELEASED;
            S000_ERR_MSG_PROC();
        }
    }
    public void R000_UPDATE_PLDR_PROC() throws IOException,SQLException,Exception {
        if (GDCUMPLD.FUNC == 'C' 
                && WS_PLDR_CREATE_FLG == 'A'
            || GDCUMPLD.FUNC == 'A') {
            GDRPLDR.RELAT_AMT = GDRPLDR.RELAT_AMT + GDCUMPLD.TX_AMT;
            if (GDRPLDR.RELAT_STS == 'R') {
                GDRPLDR.RELAT_STS = 'N';
            }
        } else if (GDCUMPLD.FUNC == 'R') {
            GDRPLDR.RELAT_AMT = GDRPLDR.RELAT_AMT - GDCUMPLD.TX_AMT;
        } else if (GDCUMPLD.FUNC == 'D') {
            GDRPLDR.RELAT_AMT = 0;
            GDRPLDR.RELAT_STS = 'R';
            GDRPLDR.RELS_DATE = SCCGWA.COMM_AREA.AC_DATE;
            GDRPLDR.RELS_TIME = SCCGWA.COMM_AREA.TR_TIME;
            GDRPLDR.RELS_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            GDRPLDR.RELS_CHNL_CD = SCCGWA.COMM_AREA.REQ_SYSTEM;
            GDRPLDR.RELS_USR = SCCGWA.COMM_AREA.TL_ID;
        } else {
        }
        GDRPLDR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRPLDR.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        GDCRPLDR.FUNC = 'U';
        GDCRPLDR.REC_PTR = GDRPLDR;
        GDCRPLDR.REC_LEN = 311;
        S000_CALL_GDZRPLDR();
        if (GDCUMPLD.FUNC != 'D') {
            GDCUMPLD.TOT_RAMT = GDRPLDR.RELAT_AMT;
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICQACRI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-GET-SEQ", BPCSGSEQ);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
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
    public void S000_CALL_DDZIQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-DDPRD", DDCIQPRD);
        if (DDCIQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQPRD.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_GDZRPLDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SRC-GDZRPLDR", GDCRPLDR);
        if (GDCUMPLD.FUNC != 'C') {
            if (GDCRPLDR.RC.RC_CODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, GDCRPLDR.RC);
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void S000_CALL_GDZRHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SRC-GDZRHIS", GDCRHIS);
        if (GDCRHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, GDCRHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_SCZTPCL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-LINK-EXTSERV", SCCTPCL);
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC);
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC);
    }
    public void S000_CALL_TDZACDRU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-ACDR-UNT", TDCACDRU);
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
    }
    public void S000_CALL_AIZPQIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-IA", AICPQIA);
        if (AICPQIA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPQIA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "AC =");
            CEP.TRC(SCCGWA, DDRMST.KEY.CUS_AC);
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "ACO-AC =");
            CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_TDTCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        TDTCMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRCMST, TDTCMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "AC-NO =");
            CEP.TRC(SCCGWA, TDRCMST.KEY.AC_NO);
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READUPD_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "ACO-AC =");
            CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_UPDATE_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.REWRITE(SCCGWA, TDRSMST, TDTSMST_RD);
    }
    public void T000_READUPD_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "AC =");
            CEP.TRC(SCCGWA, DDRCCY.KEY.AC);
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_UPDATE_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.REWRITE(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "AC =");
            CEP.TRC(SCCGWA, DDRCCY.KEY.AC);
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_GDTPLDR() throws IOException,SQLException,Exception {
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.where = "RSEQ = :GDRPLDR.KEY.RSEQ";
        GDTPLDR_RD.fst = true;
        IBS.READ(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_PLDR_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_GDTPLDR1() throws IOException,SQLException,Exception {
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.where = "RSEQ = :GDRPLDR.KEY.RSEQ "
            + "AND AC = :GDRPLDR.KEY.AC "
            + "AND AC_SEQ = :GDRPLDR.KEY.AC_SEQ";
        GDTPLDR_RD.upd = true;
        IBS.READ(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_PLDR_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_GDTPLDR2() throws IOException,SQLException,Exception {
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.where = "SYS_NO = :GDRPLDR.SYS_NO "
            + "AND DEAL_CD = :GDRPLDR.DEAL_CD "
            + "AND BSREF = :GDRPLDR.BSREF "
            + "AND AC = :GDRPLDR.KEY.AC "
            + "AND AC_SEQ = :GDRPLDR.KEY.AC_SEQ";
        GDTPLDR_RD.upd = true;
        IBS.READ(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_PLDR_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_UPDATE_GDTPLDR() throws IOException,SQLException,Exception {
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.upd = true;
        IBS.READ(SCCGWA, GDRPLDR, GDTPLDR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_PLDR_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_REWRITE_GDTPLDR() throws IOException,SQLException,Exception {
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        IBS.REWRITE(SCCGWA, GDRPLDR, GDTPLDR_RD);
    }
    public void T000_UPDATE_GDTHIS() throws IOException,SQLException,Exception {
        GDTHIS_RD = new DBParm();
        GDTHIS_RD.TableName = "GDTHIS";
        GDTHIS_RD.where = "TR_DATE = :GDRHIS.KEY.TR_DATE "
            + "AND JRNNO = :GDRHIS.KEY.JRNNO "
            + "AND FUNC = :GDRHIS.FUNC";
        GDTHIS_RD.upd = true;
        IBS.READ(SCCGWA, GDRHIS, this, GDTHIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_HIS_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_REWRITE_GDTHIS() throws IOException,SQLException,Exception {
        GDTHIS_RD = new DBParm();
        GDTHIS_RD.TableName = "GDTHIS";
        IBS.REWRITE(SCCGWA, GDRHIS, GDTHIS_RD);
    }
    public void T000_GROUP_GDTPLDR() throws IOException,SQLException,Exception {
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.where = "RSEQ = :GDRPLDR.KEY.RSEQ "
            + "AND RELAT_STS = :GDRPLDR.RELAT_STS";
        GDTPLDR_RD.set = "WS-ALL-RELAT-AMT=IFNULL(SUM(RELAT_AMT),0)";
        IBS.GROUP(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
    }
    public void T000_STBR_BY_RSEQ_PROC() throws IOException,SQLException,Exception {
        GDTPLDR_BR.rp = new DBParm();
        GDTPLDR_BR.rp.TableName = "GDTPLDR";
        GDTPLDR_BR.rp.where = "RSEQ = :GDRPLDR.KEY.RSEQ "
            + "AND RELAT_STS = :GDRPLDR.RELAT_STS";
        GDTPLDR_BR.rp.order = "AC,AC_SEQ";
        IBS.STARTBR(SCCGWA, GDRPLDR, this, GDTPLDR_BR);
    }
    public void T000_STBR_BY_RSEQ_PROC1() throws IOException,SQLException,Exception {
        GDTPLDR_BR.rp = new DBParm();
        GDTPLDR_BR.rp.TableName = "GDTPLDR";
        GDTPLDR_BR.rp.where = "RSEQ = :GDRPLDR.KEY.RSEQ";
        GDTPLDR_BR.rp.order = "AC,AC_SEQ";
        IBS.STARTBR(SCCGWA, GDRPLDR, this, GDTPLDR_BR);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, GDRPLDR, this, GDTPLDR_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_PLDR_FLG = 'Y';
        } else {
            WS_PLDR_FLG = 'N';
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, GDTPLDR_BR);
    }
    public void T000_STARTBR_BY_DTJRNTYP1() throws IOException,SQLException,Exception {
        GDTHIS_BR.rp = new DBParm();
        GDTHIS_BR.rp.TableName = "GDTHIS";
        GDTHIS_BR.rp.where = "TR_DATE = :GDRHIS.KEY.TR_DATE "
            + "AND JRNNO = :GDRHIS.KEY.JRNNO "
            + "AND FUNC = :GDRHIS.FUNC";
        IBS.STARTBR(SCCGWA, GDRHIS, this, GDTHIS_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_STARTBR_BY_DTJRNTYPAC() throws IOException,SQLException,Exception {
        GDTHIS_BR.rp = new DBParm();
        GDTHIS_BR.rp.TableName = "GDTHIS";
        GDTHIS_BR.rp.where = "TR_DATE = :GDRHIS.KEY.TR_DATE "
            + "AND JRNNO = :GDRHIS.KEY.JRNNO "
            + "AND FUNC = :GDRHIS.FUNC "
            + "AND AC = :GDRHIS.AC "
            + "AND AC_SEQ = :GDRHIS.AC_SEQ";
        IBS.STARTBR(SCCGWA, GDRHIS, this, GDTHIS_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_STARTBR_BY_DTJRNTYP() throws IOException,SQLException,Exception {
        GDTHIS_BR.rp = new DBParm();
        GDTHIS_BR.rp.TableName = "GDTHIS";
        GDTHIS_BR.rp.where = "TR_DATE = :GDRHIS.KEY.TR_DATE "
            + "AND JRNNO = :GDRHIS.KEY.JRNNO "
            + "AND AC_SEQ = :GDRHIS.AC_SEQ";
        GDTHIS_BR.rp.upd = true;
        GDTHIS_BR.rp.order = "SEQ DESC";
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
    public void T000_READ_FIRST_GDTPLDR() throws IOException,SQLException,Exception {
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.where = "RELAT_WAY = '0' "
            + "AND DEAL_CD = :GDRPLDR.DEAL_CD "
            + "AND BSREF = :GDRPLDR.BSREF "
            + "AND RELAT_STS = 'N'";
        GDTPLDR_RD.fst = true;
        GDTPLDR_RD.upd = true;
        IBS.READ(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READ_GDTSTAC() throws IOException,SQLException,Exception {
        GDTSTAC_RD = new DBParm();
        GDTSTAC_RD.TableName = "GDTSTAC";
        IBS.READ(SCCGWA, GDRSTAC, GDTSTAC_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_STAC_FLG = 'N';
        } else {
            WS_STAC_FLG = 'Y';
        }
    }
    public void S000_CALL_BPZPQPCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-PC", BPCOQPCD);
        if (BPCOQPCD.RC.RC_CODE != 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_BUSI_TYPE_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFCSTS1() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-STS-TBL-AUTH", BPCFCSTS);
        CEP.TRC(SCCGWA, BPCFCSTS.RC);
        CEP.TRC(SCCGWA, BPCFCSTS.RC.RC_CODE);
        if (BPCFCSTS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFCSTS.RC);
        }
    }
    public void S000_CALL_DDZICHAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "DD-I-CHECK-PROC";
        SCCCALL.COMMAREA_PTR = DDCICHAC;
        SCCCALL.ERRHDL_FLG = 'Y';
        IBS.CALL(SCCGWA, SCCCALL);
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
            WS_CSTS_FLG = 'N';
        }
    }
    public void S000_CALL_BPZFCSTS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "BP-F-STS-TBL-AUTH";
        SCCCALL.COMMAREA_PTR = BPCFCSTS;
        SCCCALL.ERRHDL_FLG = 'Y';
        IBS.CALL(SCCGWA, SCCCALL);
        CEP.TRC(SCCGWA, BPCFCSTS.RC);
        CEP.TRC(SCCGWA, BPCFCSTS.RC.RC_CODE);
        if (BPCFCSTS.RC.RC_CODE != 0) {
            WS_CSTS_FLG = 'N';
        }
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
            WS_CSTS_FLG = 'N';
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
