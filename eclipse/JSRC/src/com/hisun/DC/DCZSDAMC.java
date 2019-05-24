package com.hisun.DC;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCFFTXI;
import com.hisun.BP.BPCPFPDT;
import com.hisun.BP.BPCPLOSS;
import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPORUP_DATA_INFO;
import com.hisun.BP.BPCPQORG;
import com.hisun.BP.BPCPQPRD;
import com.hisun.BP.BPCPRMM;
import com.hisun.BP.BPCPRMR;
import com.hisun.BP.BPCSLOSS;
import com.hisun.BP.BPCSOCAC;
import com.hisun.BP.BPCTCALF;
import com.hisun.BP.BPCUBUSE;
import com.hisun.BP.BPRLOSS;
import com.hisun.BP.BPROCAC;
import com.hisun.CI.CICGAGA_AGENT_AREA_AGENT_AREA;
import com.hisun.CI.CICQACRL;
import com.hisun.CI.CICSAGEN;
import com.hisun.DD.DDRCCY;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCWRMSG;
import com.hisun.TD.TDCCHDC;

public class DCZSDAMC {
    boolean pgmRtn = false;
    String CPN_F_F_TX_INFO = "BP-F-F-TX-INFO";
    String CPN_F_F_CAL_FEE = "BP-F-F-CAL-FEE";
    String CPN_BPZPQPRD = "BP-P-QUERY-PRDT-INFO";
    String CPN_DCZUCGET = "DC-U-CARD-GET";
    String CPN_DCZUCCHA = "DC-U-CARD-CHA";
    String CPN_REC_NHIS = "BP-REC-NHIS";
    String CPN_DCZUPSWM = "DC-U-PSW-MAINTAIN";
    String CPN_DCZUCHCD = "DC-U-CHANG-CARD";
    double K_MAX_BL = 50000;
    String PGM_SCSSCLDT = "SCSSCLDT";
    String K_OUTPUT_FMT = "DC043";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    long WS_CARD_SEQNO = 0;
    String WS_CARDNO0 = " ";
    short WS_CARD_SEQ = 0;
    int WS_CARD_BR = 0;
    char WS_ADSC_TYPE = ' ';
    String WS_CIFNO = " ";
    String WS_PROD_CD = " ";
    char WS_OLD_ADSC_TYPE = ' ';
    char WS_NEW_ADSC_TYPE = ' ';
    String WS_NEW_CARD_MD = " ";
    String WS_HOLDER_CINO = " ";
    char WS_OLD_CARD_APP_FLG = ' ';
    char WS_NEW_CARD_APP_FLG = ' ';
    String WS_IC_PROD_CD = " ";
    String WS_BAL_CARD_NO = " ";
    char WS_PSW_FLG = ' ';
    char WS_OPEN_IC_FLG = ' ';
    String WS_CARD_OWN_CINO = " ";
    String WS_CARD_HLDR_CINO = " ";
    char WS_AC_TYPE = ' ';
    char WS_PSW_LOST_FLG = ' ';
    char WS_ACTIVATION_FLG = ' ';
    String WS_FEE_PROD = " ";
    DCZSDAMC_WS_OLD_CARD_INFO WS_OLD_CARD_INFO = new DCZSDAMC_WS_OLD_CARD_INFO();
    DCZSDAMC_WS_OLD_CARD_CRDLT_INFO WS_OLD_CARD_CRDLT_INFO = new DCZSDAMC_WS_OLD_CARD_CRDLT_INFO();
    DCZSDAMC_WS_OLD_CARD_TXTOT_INFO WS_OLD_CARD_TXTOT_INFO = new DCZSDAMC_WS_OLD_CARD_TXTOT_INFO();
    DCZSDAMC_WS_OUTPUT WS_OUTPUT = new DCZSDAMC_WS_OUTPUT();
    String WS_OLD_BV_CD_NO = " ";
    int WS_LOS_DT = 0;
    int WS_LOS_DT1 = 0;
    short WS_LOS_DAYS = 0;
    char WS_TBL_FLAG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCWRMSG SCCWRMSG = new SCCWRMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCRCDORD DCRCDORD = new DCRCDORD();
    DCRBINPM DCRBINPM = new DCRBINPM();
    DCCHDAMC DCCHDAMC = new DCCHDAMC();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DCCHDAMC DCCODAMC = new DCCHDAMC();
    DCCHDAMC DCCNDAMC = new DCCHDAMC();
    DCCUCGET DCCUCGET = new DCCUCGET();
    DCCUCCHA DCCUCCHA = new DCCUCCHA();
    BPCUBUSE BPCUBUSE = new BPCUBUSE();
    DCCUPSWM DCCUPSWM = new DCCUPSWM();
    DCCUCHCD DCCUCHCD = new DCCUCHCD();
    DCCSCCAG DCCSCCAG = new DCCSCCAG();
    DCCPACTY DCCPACTY = new DCCPACTY();
    BPCPRMR BPCPRMR = new BPCPRMR();
    DCRPRDPR DCRPRDPR = new DCRPRDPR();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCPFPDT BPCPFPDT = new BPCPFPDT();
    DCRACLNK DCRACLNK = new DCRACLNK();
    DCRIAMST DCRIAMST = new DCRIAMST();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    DCRINRCD DCRINRCD = new DCRINRCD();
    DCRNOCRD DCRNOCRD = new DCRNOCRD();
    DCCUTBAL DCCUTBAL = new DCCUTBAL();
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPCTCALF BPCTCALF = new BPCTCALF();
    BPRLOSS BPRLOSS = new BPRLOSS();
    BPCSLOSS BPCSLOSS = new BPCSLOSS();
    BPCSOCAC BPCSOCAC = new BPCSOCAC();
    BPROCAC BPROCAC = new BPROCAC();
    CICCHDC CICCHDC = new CICCHDC();
    CICSACR CICSACR = new CICSACR();
    DCRCRDLT DCRCRDLT = new DCRCRDLT();
    DCRDIRAC DCRDIRAC = new DCRDIRAC();
    DCCFCDGG DCCFCDGG = new DCCFCDGG();
    CICCKOC CICCKOC = new CICCKOC();
    DDCIMCYY DDCIMCYY = new DDCIMCYY();
    TDCCHDC TDCCHDC = new TDCCHDC();
    CICSAGEN CICSAGEN = new CICSAGEN();
    DDCUOPAC DDCUOPAC = new DDCUOPAC();
    BPCPLOSS BPCPLOSS = new BPCPLOSS();
    DCRAPPLC DCRAPPLC = new DCRAPPLC();
    CICQACRL CICQACRL = new CICQACRL();
    DCCUCSET DCCUCSET = new DCCUCSET();
    DCCUMATP DCCUMATP = new DCCUMATP();
    BPCPQORG BPCPQORG = new BPCPQORG();
    DCCUBIND DCCUBIND = new DCCUBIND();
    DCRTXTOT DCRTXTOT = new DCRTXTOT();
    DCCUPRCD DCCUPRCD = new DCCUPRCD();
    DDRCCY DDRCCY = new DDRCCY();
    DCRCPARM DCRCPARM = new DCRCPARM();
    DCRBRCLC DCRBRCLC = new DCRBRCLC();
    DDRSTSW DDRSTSW = new DDRSTSW();
    DDRSTSW DDRSTSN = new DDRSTSW();
    SCCGWA SCCGWA;
    BPCPORUP_DATA_INFO BPCPORUP;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCSDAMC DCCSDAMC;
    CICGAGA_AGENT_AREA CICGAGA_AGENT_AREA;
    public void MP(SCCGWA SCCGWA, DCCSDAMC DCCSDAMC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSDAMC = DCCSDAMC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSDAMC return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        CICGAGA_AGENT_AREA = new CICGAGA_AGENT_AREA_AGENT_AREA();
        IBS.init(SCCGWA, CICGAGA_AGENT_AREA);
        IBS.CPY2CLS(SCCGWA, SCCGWA.AGENT_AREA_PTR, CICGAGA_AGENT_AREA);
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B012_CHECK_NEW_CARD_DIGIT();
        if (pgmRtn) return;
        B011_CHECK_CARD_ARREARAGE();
        if (pgmRtn) return;
        B040_CARD_VOUCHER();
        if (pgmRtn) return;
        B030_CARD_ORDER();
        if (pgmRtn) return;
        B020_CHANGE_CARD_ATTACHMENT();
        if (pgmRtn) return;
        if (WS_OLD_CARD_INFO.WS_CARD_LNK_TYP == '1') {
            B222_PRIM_CARD_CHANGE();
            if (pgmRtn) return;
        }
        if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
            B050_FEE_PROCESS();
            if (pgmRtn) return;
        }
        B080_NEW_CARD_RELN();
        if (pgmRtn) return;
        B082_CHANGE_CARD_PROCESS();
        if (pgmRtn) return;
        B086_CHANGE_ZHCP_PROCESS();
        if (pgmRtn) return;
        if (WS_ADSC_TYPE == 'P') {
            B200_CARRY_BIND_RELATION();
            if (pgmRtn) return;
        }
        B083_CHANGE_CARD_LIMIT();
        if (pgmRtn) return;
        B091_INHERIT_ACUM_LIMIT();
        if (pgmRtn) return;
        B085_CHANGE_APPLEPAY();
        if (pgmRtn) return;
        if (WS_ADSC_TYPE == 'C') {
            B084_CHANGE_POINT_AC();
            if (pgmRtn) return;
        }
        B100_OLD_CARD_CANCEL();
        if (pgmRtn) return;
        B110_UPDATE_DDTCCY();
        if (pgmRtn) return;
        if (DCCSDAMC.CHANG_TYP == '3') {
            B111_UPDATE_CCYTYP();
            if (pgmRtn) return;
        }
        B120_UPDATE_TDTSMST();
        if (pgmRtn) return;
        B130_UPDATE_DCTINRCD();
        if (pgmRtn) return;
        B025_OPEN_IC_AC();
        if (pgmRtn) return;
        if (DCCSDAMC.AGENT_FLG == '1') {
            B140_REGIST_AGENT_INF();
            if (pgmRtn) return;
        }
        if (WS_OLD_CARD_INFO.WS_OLD_CARD_STSW == null) WS_OLD_CARD_INFO.WS_OLD_CARD_STSW = "";
        JIBS_tmp_int = WS_OLD_CARD_INFO.WS_OLD_CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) WS_OLD_CARD_INFO.WS_OLD_CARD_STSW += " ";
        CEP.TRC(SCCGWA, WS_OLD_CARD_INFO.WS_OLD_CARD_STSW.substring(17 - 1, 17 + 1 - 1));
        if (WS_OLD_CARD_INFO.WS_OLD_CARD_STSW == null) WS_OLD_CARD_INFO.WS_OLD_CARD_STSW = "";
        JIBS_tmp_int = WS_OLD_CARD_INFO.WS_OLD_CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) WS_OLD_CARD_INFO.WS_OLD_CARD_STSW += " ";
        if (WS_OLD_CARD_INFO.WS_OLD_CARD_STSW.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
            B210_UPDATE_DDTSTSW();
            if (pgmRtn) return;
        }
        B070_OUTPUT_PROCESS();
        if (pgmRtn) return;
        B060_HISTORY_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSDAMC.LOST_NO);
        CEP.TRC(SCCGWA, DCCSDAMC.AGENT_FLG);
        CEP.TRC(SCCGWA, DCCSDAMC.OLD_CARD_NO);
        CEP.TRC(SCCGWA, DCCSDAMC.HOLDER_IDTYP);
        CEP.TRC(SCCGWA, DCCSDAMC.HOLDER_IDNO);
        CEP.TRC(SCCGWA, DCCSDAMC.HOLDER_NAME);
        CEP.TRC(SCCGWA, DCCSDAMC.CI_NO);
        CEP.TRC(SCCGWA, DCCSDAMC.CARD_PASSWD);
        CEP.TRC(SCCGWA, DCCSDAMC.NEW_CARD_NO);
        CEP.TRC(SCCGWA, DCCSDAMC.NEW_BV_CD);
        CEP.TRC(SCCGWA, DCCSDAMC.RMK);
        CEP.TRC(SCCGWA, DCCSDAMC.RC);
        if (DCCSDAMC.OLD_CARD_NO.trim().length() == 0 
            || DCCSDAMC.NEW_CARD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO, DCCSDAMC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSDAMC.HOLDER_IDTYP.trim().length() == 0 
            && DCCSDAMC.HOLDER_IDNO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CI_INFO_MISSING;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CI_INFO_MISSING, DCCSDAMC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSDAMC.OLD_CARD_NO.equalsIgnoreCase(DCCSDAMC.NEW_CARD_NO)) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NEW_OLD_CARD_SAME;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_NEW_OLD_CARD_SAME, DCCSDAMC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCSDAMC.OLD_CARD_NO;
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        WS_OLD_CARD_INFO.WS_OLD_CARD_TYP = DCRCDDAT.CARD_TYP;
        WS_OLD_CARD_INFO.WS_OLD_PVK_TYP = DCRCDDAT.PVK_TYP;
        WS_OLD_CARD_INFO.WS_OLD_HOLD_AC_FLG = DCRCDDAT.HOLD_AC_FLG;
        WS_OLD_CARD_INFO.WS_OLD_PROD_LMT_FLG = DCRCDDAT.PROD_LMT_FLG;
        WS_OLD_CARD_INFO.WS_OLD_CVV_FLG = DCRCDDAT.CVV_FLG;
        WS_OLD_CARD_INFO.WS_OLD_SAME_TFR_FLG = DCRCDDAT.SAME_NAME_TFR_FLG;
        WS_OLD_CARD_INFO.WS_OLD_DIFF_TFR_FLG = DCRCDDAT.DIFF_NAME_TFR_FLG;
        WS_OLD_CARD_INFO.WS_OLD_DRAW_OVER_FLG = DCRCDDAT.DRAW_OVER_FLG;
        WS_OLD_CARD_INFO.WS_OLD_DB_FREE_FLG = DCRCDDAT.DOUBLE_FREE_FLG;
        WS_OLD_CARD_INFO.WS_CARD_LNK_TYP = DCRCDDAT.CARD_LNK_TYP;
        WS_OLD_CARD_INFO.WS_OLD_TRAN_WITH_CARD = DCRCDDAT.TRAN_WITH_CARD;
        WS_OLD_CARD_INFO.WS_OLD_TRAN_NO_CARD = DCRCDDAT.TRAN_NO_CARD;
        WS_OLD_CARD_INFO.WS_OLD_TRAN_CRS_BOR = DCRCDDAT.TRAN_CRS_BOR;
        WS_OLD_CARD_INFO.WS_OLD_TRAN_ATM_FLG = DCRCDDAT.TRAN_ATM_FLG;
        WS_OLD_CARD_INFO.WS_OLD_JOIN_CUS_FLG = DCRCDDAT.JOIN_CUS_FLG;
        WS_OLD_CARD_INFO.WS_OLD_ANNU_FEE_FREE = DCRCDDAT.ANNUAL_FEE_FREE;
        WS_OLD_CARD_INFO.WS_OLD_PRIM_CARD_NO = DCRCDDAT.PRIM_CARD_NO;
        WS_OLD_CARD_INFO.WS_OLD_ANU_FEE_NXT = DCRCDDAT.ANU_FEE_NXT;
        WS_OLD_CARD_INFO.WS_OLD_ANU_FEE_PCT = DCRCDDAT.ANU_FEE_PCT;
        WS_OLD_CARD_INFO.WS_OLD_ANU_FEE_FREE = DCRCDDAT.ANU_FEE_FREE;
        WS_OLD_CARD_INFO.WS_OLD_ANU_FEE_ARG = DCRCDDAT.ANU_FEE_ARG;
        WS_OLD_CARD_INFO.WS_OLD_AC_OIC_NO = DCRCDDAT.AC_OIC_NO;
        WS_OLD_CARD_INFO.WS_OLD_AC_OIC_CODE = DCRCDDAT.AC_OIC_CODE;
        WS_OLD_CARD_INFO.WS_OLD_SUB_DP = DCRCDDAT.SUB_DP;
        WS_OLD_CARD_INFO.WS_OLD_PSW_TYP = DCRCDDAT.PSW_TYP;
        WS_CARD_OWN_CINO = DCRCDDAT.CARD_OWN_CINO;
        WS_CARD_HLDR_CINO = DCRCDDAT.CARD_HLDR_CINO;
        WS_AC_TYPE = DCRCDDAT.ACNO_TYPE;
        WS_OLD_BV_CD_NO = DCRCDDAT.BV_CD_NO;
        WS_OLD_CARD_INFO.WS_OLD_CARD_STSW = DCRCDDAT.CARD_STSW;
        if (DCCSDAMC.CHANG_TYP == '3') {
            if (DCRCDDAT.CARD_MEDI != '4') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CDNOT_VIRTUAL;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CDNOT_VIRTUAL, DCCSDAMC.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            if (DCRCDDAT.CARD_MEDI == '4') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CDIS_VIRTUAL;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CDIS_VIRTUAL, DCCSDAMC.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCRCDDAT.ACNO_TYPE == '3') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_THIRD_CN_CHANGE;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_THIRD_CN_CHANGE, DCCSDAMC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1")) {
            WS_OPEN_IC_FLG = 'Y';
        }
        if (DCRCDDAT.CARD_STS == 'C') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_BE_CLOSED;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_BE_CLOSED, DCCSDAMC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRCDDAT.CARD_STS == '0') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_READY;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_READY, DCCSDAMC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_CARD_BR = DCRCDDAT.ISSU_BR;
        WS_PROD_CD = DCRCDDAT.PROD_CD;
        WS_CIFNO = DCRCDDAT.CARD_OWN_CINO;
        CEP.TRC(SCCGWA, DCRCDDAT.CARD_STS);
        CEP.TRC(SCCGWA, DCRCDDAT.PROD_CD);
        if (DCRCDDAT.PROD_CD.equalsIgnoreCase("1203021701") 
            || DCRCDDAT.PROD_CD.equalsIgnoreCase("1203021801")) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_II_III_NOT_CHANGE);
        }
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = DCRCDDAT.PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
        if (BPCPQPRD.PARM_CODE.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PQPRD_PARM_CD_SPC;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_PQPRD_PARM_CD_SPC, DCCSDAMC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCCUPRCD);
        IBS.init(SCCGWA, DCRPRDPR);
        DCCUPRCD.TX_TYPE = 'I';
        DCCUPRCD.DATA.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        DCCUPRCD.DATA.VAL.PRDMO_CD = "CARD";
        DCCUPRCD.DATA.KEY.CD.PARM_CODE = BPCPQPRD.PARM_CODE;
        DCCUPRCD.DATE.EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
        S000_CALL_DCZUPRCD();
        if (pgmRtn) return;
        if (DCCUPRCD.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DCCUPRCD.RC);
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUPRCD.DATA);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCRPRDPR);
        }
        CEP.TRC(SCCGWA, DCRPRDPR);
        WS_ADSC_TYPE = DCRPRDPR.DATA_TXT.ADSC_TYP;
        WS_OLD_ADSC_TYPE = DCRPRDPR.DATA_TXT.ADSC_TYP;
        WS_PSW_FLG = DCRPRDPR.DATA_TXT.PSW_FLG;
        CEP.TRC(SCCGWA, DCRPRDPR.DATA_TXT.APP_FLG);
        WS_OLD_CARD_APP_FLG = DCRPRDPR.DATA_TXT.APP_FLG;
        if (DCCSDAMC.CHANG_TYP == '3') {
            if (DCRPRDPR.DATA_TXT.PHY_TYP != 'D') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PDNOT_VIRTUAL;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_PDNOT_VIRTUAL, DCCSDAMC.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            if (DCRPRDPR.DATA_TXT.PHY_TYP == 'D') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PDIS_VIRTUAL;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_PDIS_VIRTUAL, DCCSDAMC.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        WS_HOLDER_CINO = DCCSDAMC.CI_NO;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_ID.TR_CODE);
        if (SCCGWA.COMM_AREA.TR_ID.TR_CODE == 0053) {
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            if (!DCRCDDAT.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_LOSE_STS;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_LOSE_STS, DCCSDAMC.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DCCSDAMC.LOST_NO.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_LOST_MUST_INPUT;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_LOST_MUST_INPUT, DCCSDAMC.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                IBS.init(SCCGWA, BPCPLOSS);
                BPCPLOSS.DATA_INFO.LOS_NO = DCCSDAMC.LOST_NO;
                BPCPLOSS.INFO.FUNC = 'I';
                BPCPLOSS.INFO.INDEX_FLG = "1";
                S000_CALL_BPZPLOSS();
                if (pgmRtn) return;
                if ((!BPCPLOSS.DATA_INFO.AC.equalsIgnoreCase(DCCSDAMC.OLD_CARD_NO)) 
                    || (BPCPLOSS.DATA_INFO.STS != '1')) {
                    CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_LOST_NO_NOT_NORMAL);
                }
                WS_LOS_DT = BPCPLOSS.DATA_INFO.LOS_DT;
                IBS.init(SCCGWA, DCRCPARM);
                IBS.init(SCCGWA, BPCPRMR);
                DCRCPARM.KEY.TYP = "DCPRM";
                BPCPRMR.FUNC = ' ';
                DCRCPARM.KEY.CD = SCCGWA.COMM_AREA.TR_BANK;
                IBS.CPY2CLS(SCCGWA, DCRCPARM.KEY.CD, DCRCPARM.KEY.KEY1);
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_BANK);
                BPCPRMR.DAT_PTR = DCRCPARM;
                S000_CALL_BPZPRMR();
                if (pgmRtn) return;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
                if (JIBS_tmp_str[0].equalsIgnoreCase(DCCMSG_ERROR_MSG.NOT_FOUND)) {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.NOT_FOUND;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                DCRCPARM.DATA_TXT.LOS_DAYS = 7;
                CEP.TRC(SCCGWA, DCRCPARM.DATA_TXT.LOS_DAYS);
                WS_LOS_DAYS = DCRCPARM.DATA_TXT.LOS_DAYS;
                CEP.TRC(SCCGWA, WS_LOS_DAYS);
                CEP.TRC(SCCGWA, WS_LOS_DT);
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DAYS = WS_LOS_DAYS;
                SCCCLDT.DATE1 = WS_LOS_DT;
                S000_CALL_SCSSCLDT();
                if (pgmRtn) return;
                WS_LOS_DT1 = SCCCLDT.DATE2;
                CEP.TRC(SCCGWA, WS_LOS_DT1);
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
                if (SCCGWA.COMM_AREA.AC_DATE < WS_LOS_DT1) {
                    CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_LOST_DAY_LESS);
                }
                CEP.TRC(SCCGWA, "PR0067");
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BR = BPCPLOSS.DATA_INFO.LOS_ORG;
                S000_CALL_BPZPQORG();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCPLOSS.DATA_INFO.LOS_ORG);
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
                CEP.TRC(SCCGWA, BPCPQORG.BBR);
                CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.BBR);
                if (BPCPQORG.BBR != BPCPORUP.DATA_INFO.BBR) {
                    CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_LOST_BR_NOT_MATCH);
                }
                IBS.init(SCCGWA, BPCSLOSS);
                BPCSLOSS.LOS_NO = DCCSDAMC.LOST_NO;
                BPCSLOSS.FUNC = 'U';
                BPCSLOSS.STS = '5';
                BPCSLOSS.RLOS_ORG = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPCSLOSS.RLOS_DT = SCCGWA.COMM_AREA.AC_DATE;
                BPCSLOSS.RLOS_TIME = SCCGWA.COMM_AREA.TR_TIME;
                BPCSLOSS.RLOS_TLR = SCCGWA.COMM_AREA.TL_ID;
                BPCSLOSS.NEW_BV_NO = DCCSDAMC.NEW_CARD_NO;
                S000_CALL_BPZSLOSS();
                if (pgmRtn) return;
            }
        }
        if (SCCGWA.COMM_AREA.TR_ID.TR_CODE == 0043) {
            if (DCRCDDAT.CARD_STS != 'N') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_NORMAL_STS;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_NORMAL_STS, DCCSDAMC.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            if (DCRCDDAT.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ALR_CARD_LOSS_STS;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_ALR_CARD_LOSS_STS, DCCSDAMC.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STS == 'N' 
            && (DCRCDDAT.CARD_STSW.substring(0, 1).equalsIgnoreCase("1"))) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_ALREADY_LOSE;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_ALREADY_LOSE, DCCSDAMC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STS == 'N' 
            && (DCRCDDAT.CARD_STSW.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1"))) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ALR_CARD_SWALLOW_STS;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_ALR_CARD_SWALLOW_STS, DCCSDAMC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.TR_ID.TR_CODE == 0053 
            && DCRCDDAT.CARD_STS != 'N') {
            WS_ACTIVATION_FLG = 'N';
            if (DCCSDAMC.CARD_PASSWD.trim().length() > 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NOT_ATT_NOT_INP_PSW;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_NOT_ATT_NOT_INP_PSW, DCCSDAMC.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (WS_PSW_FLG == 'Y') {
            if (DCCSDAMC.CARD_PASSWD.trim().length() == 0 
                && (WS_PSW_LOST_FLG != 'Y') 
                && (WS_ACTIVATION_FLG != 'N')) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PSW_MUST_INPUT;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_PSW_MUST_INPUT, DCCSDAMC.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else if (WS_PSW_FLG == 'N') {
            if (DCCSDAMC.CARD_PASSWD.trim().length() > 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_PSW;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_PSW, DCCSDAMC.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B011_CHECK_CARD_ARREARAGE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPFPDT);
        BPCPFPDT.INPUT.CARD_PSBK_NO = DCCSDAMC.OLD_CARD_NO;
        S000_CALL_BPZPFPDT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPFPDT.OUTPUT.MAIN_FLG);
        if (BPCPFPDT.OUTPUT.MAIN_FLG == 'Y') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PAY_ARREARAGE;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_PAY_ARREARAGE, DCCSDAMC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B012_CHECK_NEW_CARD_DIGIT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCFCDGG);
        DCCFCDGG.VAL.FUNC = 'C';
        DCCFCDGG.VAL.CARD_NO = DCCSDAMC.NEW_CARD_NO;
        CEP.TRC(SCCGWA, DCCFCDGG.VAL.CARD_NO);
        S000_CALL_DCZFCDGG();
        if (pgmRtn) return;
    }
    public void B020_CHANGE_CARD_ATTACHMENT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUPSWM);
        DCCUPSWM.FUNC = 'R';
        DCCUPSWM.OLD_AGR_NO = DCCSDAMC.OLD_CARD_NO;
        DCCUPSWM.AGR_NO = DCCSDAMC.NEW_CARD_NO;
        if (DCCSDAMC.OLD_CARD_NO == null) DCCSDAMC.OLD_CARD_NO = "";
        JIBS_tmp_int = DCCSDAMC.OLD_CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCCSDAMC.OLD_CARD_NO += " ";
        DCCUPSWM.AGR_NO_6 = DCCSDAMC.OLD_CARD_NO.substring(14 - 1, 14 + 6 - 1);
        DCCUPSWM.PSW_TYP = 'T';
        DCCUPSWM.CARD_PSW_NEW = DCCSDAMC.CARD_PASSWD;
        DCCUPSWM.ID_TYP = DCCSDAMC.HOLDER_IDTYP;
        DCCUPSWM.ID_NO = DCCSDAMC.HOLDER_IDNO;
        DCCUPSWM.CI_NM = DCCSDAMC.HOLDER_NAME;
        S000_CALL_DCZUPSWM();
        if (pgmRtn) return;
        WS_OLD_CARD_INFO.WS_OLD_CARD_PSW = DCCUPSWM.O_NEW_PSW;
        IBS.init(SCCGWA, DCCUCSET);
        DCCUCSET.CARD_PROD_CD = WS_OLD_CARD_INFO.WS_NEW_PROD_CODE;
        DCCUCSET.CARD_NO = DCCSDAMC.NEW_CARD_NO;
        DCCUCSET.HOLDER_CINO = DCCSDAMC.CI_NO;
        DCCUCSET.PRIM_CD_NO = WS_OLD_CARD_INFO.WS_OLD_PRIM_CARD_NO;
        DCCUCSET.OWNER_CINO = WS_CARD_OWN_CINO;
        DCCUCSET.CARD_TYPE = '0';
        DCCUCSET.CARD_PSW = WS_OLD_CARD_INFO.WS_OLD_CARD_PSW;
        DCCUCSET.SNAME_TRAN_FLG = WS_OLD_CARD_INFO.WS_OLD_SAME_TFR_FLG;
        DCCUCSET.DNAME_TRAN_FLG = WS_OLD_CARD_INFO.WS_OLD_DIFF_TFR_FLG;
        DCCUCSET.OUT_DRAW_FLG = WS_OLD_CARD_INFO.WS_OLD_DRAW_OVER_FLG;
        DCCUCSET.LNK_TYP = WS_OLD_CARD_INFO.WS_CARD_LNK_TYP;
        DCCUCSET.PROD_LMT_FLG = WS_OLD_CARD_INFO.WS_OLD_PROD_LMT_FLG;
        DCCUCSET.CARD_STS = 'N';
        DCCUCSET.HOLD_AC_FLG = WS_OLD_CARD_INFO.WS_OLD_HOLD_AC_FLG;
        DCCUCSET.CARD_TYP = WS_OLD_CARD_INFO.WS_OLD_CARD_TYP;
        DCCUCSET.CARD_CLS_PROD = WS_OLD_CARD_INFO.WS_CARD_CLS_CD;
        DCCUCSET.BV_CD_NO = WS_OLD_CARD_INFO.WS_NEW_BV_CD;
        DCCUCSET.SF_FLG = WS_OLD_CARD_INFO.WS_NEW_SF_FLG;
        DCCUCSET.CERT_EXP_DATE = WS_OLD_CARD_INFO.WS_NEW_CERT_EXP_DATE;
        DCCUCSET.AC_TYPE = WS_AC_TYPE;
        if (WS_OLD_CARD_INFO.WS_OLD_DB_FREE_FLG == ' ') {
            DCCUCSET.DB_FREE = 'Y';
        } else {
            DCCUCSET.DB_FREE = WS_OLD_CARD_INFO.WS_OLD_DB_FREE_FLG;
        }
        if (WS_OLD_CARD_INFO.WS_OLD_TRAN_WITH_CARD == ' ') {
            DCCUCSET.TRAN_WITH_CARD = 'Y';
        } else {
            DCCUCSET.TRAN_WITH_CARD = WS_OLD_CARD_INFO.WS_OLD_TRAN_WITH_CARD;
        }
        if (WS_OLD_CARD_INFO.WS_OLD_TRAN_NO_CARD == ' ') {
            DCCUCSET.TRAN_NO_CARD = 'Y';
        } else {
            DCCUCSET.TRAN_NO_CARD = WS_OLD_CARD_INFO.WS_OLD_TRAN_NO_CARD;
        }
        if (WS_OLD_CARD_INFO.WS_OLD_TRAN_CRS_BOR == ' ') {
            DCCUCSET.TRAN_CRS_BOR = 'Y';
        } else {
            DCCUCSET.TRAN_CRS_BOR = WS_OLD_CARD_INFO.WS_OLD_TRAN_CRS_BOR;
        }
        if (WS_OLD_CARD_INFO.WS_OLD_TRAN_ATM_FLG == ' ') {
            DCCUCSET.TRAN_ATM_FLG = 'Y';
        } else {
            DCCUCSET.TRAN_ATM_FLG = WS_OLD_CARD_INFO.WS_OLD_TRAN_ATM_FLG;
        }
        S000_CALL_DCZUCSET();
        if (pgmRtn) return;
        DCRCDDAT.KEY.CARD_NO = DCCSDAMC.NEW_CARD_NO;
        T000_READUPD_DCTCDDAT();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "KIA TESTING");
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        DCRCDDAT.ANNUAL_FEE_FREE = WS_OLD_CARD_INFO.WS_OLD_ANNU_FEE_FREE;
        DCRCDDAT.ANU_FEE_FREE = WS_OLD_CARD_INFO.WS_OLD_ANU_FEE_FREE;
        if (DCCSDAMC.CHANG_TYP == '3') {
            DCRCDDAT.CARD_MEDI = WS_OLD_CARD_INFO.WS_CARD_MEDI;
        }
        DCRCDDAT.EMBS_DT = DCRCDORD.CRT_DT;
        DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRCDDAT.EXC_CARD_TMS = DCRCDORD.KEY.EXC_CARD_TMS;
        if (WS_OPEN_IC_FLG == 'Y') {
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            DCRCDDAT.CARD_STSW = DCRCDDAT.CARD_STSW.substring(0, 16 - 1) + "1" + DCRCDDAT.CARD_STSW.substring(16 + 1 - 1);
        }
        DCRCDDAT.ACT_DT = SCCGWA.COMM_AREA.AC_DATE;
        T000_UPDATE_DCTCDDAT();
        if (pgmRtn) return;
        B060_OLD_CARD_STATUS_SETTING();
        if (pgmRtn) return;
    }
    public void B222_PRIM_CARD_CHANGE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.PRIM_CARD_NO = DCCSDAMC.OLD_CARD_NO;
        T000_STARTBR_DCTCDDAT();
        if (pgmRtn) return;
        T000_READNEXT_DCTCDDAT();
        if (pgmRtn) return;
        while (WS_TBL_FLAG != 'N') {
            DCRCDDAT.PRIM_CARD_NO = DCCSDAMC.NEW_CARD_NO;
            DCRCRDLT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRCRDLT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            DCRCRDLT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRCRDLT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_UPDATE_DCTCDDAT();
            if (pgmRtn) return;
            T000_READNEXT_DCTCDDAT();
            if (pgmRtn) return;
        }
        T000_ENDBR_DCTCDDAT();
        if (pgmRtn) return;
    }
    public void B030_CARD_ORDER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDORD);
        DCRCDORD.KEY.CARD_NO = DCCSDAMC.NEW_CARD_NO;
        DCRCDORD.KEY.EXC_CARD_TMS = 0;
        DCRCDORD.CRT_STS = '1';
        T000_READUPD_DCTCDORD();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_IN_ORDER;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_IN_ORDER, DCCSDAMC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_OLD_CARD_INFO.WS_CARD_CLS_CD = DCRCDORD.CARD_CLS_CD;
        WS_OLD_CARD_INFO.WS_NEW_BV_CD = DCRCDORD.BV_CD_NO;
        WS_OLD_CARD_INFO.WS_NEW_SF_FLG = DCRCDORD.SF_FLG;
        WS_OLD_CARD_INFO.WS_NEW_CERT_EXP_DATE = DCRCDORD.CERT_EXP_DATE;
        WS_OLD_CARD_INFO.WS_EMBS_DT = DCRCDORD.CRT_DT;
        if (WS_OLD_CARD_INFO.WS_CARD_CLS_CD.trim().length() > 0) {
            IBS.init(SCCGWA, DCRBRCLC);
            DCRBRCLC.KEY.BR_NO = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            DCRBRCLC.KEY.CARD_CLS_CD = WS_OLD_CARD_INFO.WS_CARD_CLS_CD;
            T000_READ_DCTBRCLC();
            if (pgmRtn) return;
            if (WS_TBL_FLAG == 'N') {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_BR_CLS_NOT_MAT);
            }
        }
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = DCRCDORD.CARD_PROD;
        WS_OLD_CARD_INFO.WS_NEW_PROD_CODE = DCRCDORD.CARD_PROD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
        if (BPCPQPRD.PARM_CODE.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PQPRD_PARM_CD_SPC;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_PQPRD_PARM_CD_SPC, DCCSDAMC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCCUPRCD);
        IBS.init(SCCGWA, DCRPRDPR);
        DCCUPRCD.TX_TYPE = 'I';
        DCCUPRCD.DATA.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        DCCUPRCD.DATA.VAL.PRDMO_CD = "CARD";
        DCCUPRCD.DATA.KEY.CD.PARM_CODE = BPCPQPRD.PARM_CODE;
        DCCUPRCD.DATE.EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
        S000_CALL_DCZUPRCD();
        if (pgmRtn) return;
        if (DCCUPRCD.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DCCUPRCD.RC);
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUPRCD.DATA);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCRPRDPR);
        }
        WS_NEW_ADSC_TYPE = DCRPRDPR.DATA_TXT.ADSC_TYP;
        CEP.TRC(SCCGWA, WS_OLD_ADSC_TYPE);
        CEP.TRC(SCCGWA, WS_NEW_ADSC_TYPE);
        CEP.TRC(SCCGWA, DCRPRDPR.DATA_TXT.APP_FLG);
        CEP.TRC(SCCGWA, DCRPRDPR.DATA_TXT.IC_PROD);
        WS_NEW_CARD_APP_FLG = DCRPRDPR.DATA_TXT.APP_FLG;
        WS_IC_PROD_CD = DCRPRDPR.DATA_TXT.IC_PROD;
        if (DCRPRDPR.DATA_TXT.SR_RC_CD.equalsIgnoreCase("220")) {
            WS_OLD_CARD_INFO.WS_CARD_MEDI = '2';
        }
        if (DCRPRDPR.DATA_TXT.SR_RC_CD.equalsIgnoreCase("120")) {
            WS_OLD_CARD_INFO.WS_CARD_MEDI = '1';
        }
        if (WS_OLD_ADSC_TYPE != WS_NEW_ADSC_TYPE) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_TYPE_NOTMATCH;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_TYPE_NOTMATCH, DCCSDAMC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        DCRCDORD.CRT_STS = '3';
        DCRCDORD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDORD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_UPDATE_DCTCDORD();
        if (pgmRtn) return;
    }
    public void B040_CARD_VOUCHER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRBINPM);
        if (DCCSDAMC.NEW_CARD_NO == null) DCCSDAMC.NEW_CARD_NO = "";
        JIBS_tmp_int = DCCSDAMC.NEW_CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCCSDAMC.NEW_CARD_NO += " ";
        DCRBINPM.KEY.BIN = DCCSDAMC.NEW_CARD_NO.substring(0, 6);
        if (DCCSDAMC.NEW_CARD_NO == null) DCCSDAMC.NEW_CARD_NO = "";
        JIBS_tmp_int = DCCSDAMC.NEW_CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCCSDAMC.NEW_CARD_NO += " ";
        if (DCCSDAMC.NEW_CARD_NO.substring(0, 8).equalsIgnoreCase("62326594")) {
            DCRBINPM.KEY.BIN = "62326594";
        }
        T000_READ_DCTBINPM();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NOT_PREMARK_CARD;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_NOT_PREMARK_CARD, DCCSDAMC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCUBUSE);
        BPCUBUSE.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUBUSE.TYPE = '0';
        BPCUBUSE.VB_FLG = '0';
        BPCUBUSE.COUNT_MTH = '1';
        if (DCCSDAMC.NEW_CARD_NO == null) DCCSDAMC.NEW_CARD_NO = "";
        JIBS_tmp_int = DCCSDAMC.NEW_CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCCSDAMC.NEW_CARD_NO += " ";
        BPCUBUSE.BEG_NO = DCCSDAMC.NEW_CARD_NO.substring(0, 18);
        BPCUBUSE.END_NO = DCCSDAMC.NEW_CARD_NO.substring(0, 18);
        BPCUBUSE.BV_CODE = DCCSDAMC.NEW_BV_CD;
        if (DCCSDAMC.NEW_BV_CD.trim().length() == 0) {
            IBS.init(SCCGWA, DCRCDORD);
            DCRCDORD.KEY.CARD_NO = DCCSDAMC.NEW_CARD_NO;
            DCRCDORD.KEY.EXC_CARD_TMS = 0;
            DCRCDORD.CRT_STS = '1';
            T000_READ_DCTCDORD();
            if (pgmRtn) return;
            BPCUBUSE.BV_CODE = DCRCDORD.BV_CD_NO;
        }
        BPCUBUSE.NUM = 1;
        CEP.TRC(SCCGWA, BPCUBUSE.TLR);
        CEP.TRC(SCCGWA, BPCUBUSE.BEG_NO);
        CEP.TRC(SCCGWA, BPCUBUSE.END_NO);
        CEP.TRC(SCCGWA, BPCUBUSE.BV_CODE);
        CEP.TRC(SCCGWA, BPCUBUSE.NUM);
        S000_CALL_BPZUBUSE();
        if (pgmRtn) return;
    }
    public void B082_CHANGE_CARD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCHDC);
        CICCHDC.FUNC = 'C';
        CICCHDC.DATA_FOR_CHANGE.ENTY_NO_OLD = DCCSDAMC.OLD_CARD_NO;
        CICCHDC.DATA_FOR_CHANGE.ENTY_TYP_OLD = '2';
        CICCHDC.DATA_FOR_CHANGE.ENTY_NO_NEW = DCCSDAMC.NEW_CARD_NO;
        CICCHDC.DATA_FOR_CHANGE.ENTY_TYP_NEW = '2';
        if (SCCGWA.COMM_AREA.TR_ID.TR_CODE == 0053) {
            CICCHDC.DATA_FOR_CHANGE.IC_AID_FLG = 'N';
        }
        S000_CALL_CIZCHDC();
        if (pgmRtn) return;
    }
    public void B083_CHANGE_CARD_LIMIT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCRDLT);
        DCRCRDLT.KEY.CARD_NO = DCCSDAMC.OLD_CARD_NO;
        T000_STARTBR_DCTCRDLT();
        if (pgmRtn) return;
        T000_READNEXT_DCTCRDLT();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            B150_MOVE_OLD_DCTCRDLT();
            if (pgmRtn) return;
            B160_INSERT_NEW_DCTCRDLT();
            if (pgmRtn) return;
            DCRCRDLT.KEY.CARD_NO = DCCSDAMC.NEW_CARD_NO;
            DCRCRDLT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRCRDLT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            DCRCRDLT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRCRDLT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_WRITE_DCTCRDLT();
            if (pgmRtn) return;
            T000_READNEXT_DCTCRDLT();
            if (pgmRtn) return;
        }
        T000_ENDBR_DCTCRDLT();
        if (pgmRtn) return;
    }
    public void B091_INHERIT_ACUM_LIMIT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRTXTOT);
        DCRTXTOT.KEY.CARD_NO = DCCSDAMC.OLD_CARD_NO;
        T000_STARTBR_DCTTXTOT();
        if (pgmRtn) return;
        T000_READNEXT_DCTTXTOT();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            B151_MOVE_OLD_DCTTXTOT();
            if (pgmRtn) return;
            B161_INSERT_NEW_DCTTXTOT();
            if (pgmRtn) return;
            DCRTXTOT.KEY.CARD_NO = DCCSDAMC.NEW_CARD_NO;
            DCRTXTOT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRTXTOT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_WRITE_DCTTXTOT();
            if (pgmRtn) return;
            T000_READNEXT_DCTTXTOT();
            if (pgmRtn) return;
        }
        T000_ENDBR_DCTTXTOT();
        if (pgmRtn) return;
    }
    public void B085_CHANGE_APPLEPAY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRAPPLC);
        DCRAPPLC.SPAN = DCCSDAMC.OLD_CARD_NO;
        T000_STARTBR_DCTAPPLC();
        if (pgmRtn) return;
        T000_READNEXT_DCTAPPLC();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            DCRAPPLC.SPAN = DCCSDAMC.NEW_CARD_NO;
            DCRAPPLC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRAPPLC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_UPDATE_DCTAPPLC();
            if (pgmRtn) return;
            T000_READNEXT_DCTAPPLC();
            if (pgmRtn) return;
        }
        T000_ENDBR_DCTAPPLC();
        if (pgmRtn) return;
    }
    public void B084_CHANGE_POINT_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRDIRAC);
        DCRDIRAC.KEY.CARD_NO = DCCSDAMC.OLD_CARD_NO;
        T000_STARTBR_DCTDIRAC();
        if (pgmRtn) return;
        T000_READNEXT_DCTDIRAC();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            DCRDIRAC.KEY.CARD_NO = DCCSDAMC.NEW_CARD_NO;
            DCRDIRAC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRDIRAC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_UPDATE_DCTDIRAC();
            if (pgmRtn) return;
            T000_READNEXT_DCTDIRAC();
            if (pgmRtn) return;
        }
        T000_ENDBR_DCTDIRAC();
        if (pgmRtn) return;
    }
    public void B086_CHANGE_ZHCP_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUMATP);
        DCCUMATP.IO_AREA.FUNC_M = '6';
        DCCUMATP.IO_AREA.AC_NO = DCCSDAMC.NEW_CARD_NO;
        DCCUMATP.IO_AREA.AGR_NO = DCCSDAMC.OLD_CARD_NO;
        S000_CALL_DCZUMATP();
        if (pgmRtn) return;
    }
    public void B050_FEE_PROCESS() throws IOException,SQLException,Exception {
        if (WS_ADSC_TYPE == 'C') {
            IBS.init(SCCGWA, CICQACRL);
            CICQACRL.FUNC = 'I';
            CICQACRL.DATA.AC_NO = DCCSDAMC.OLD_CARD_NO;
            CICQACRL.DATA.AC_REL = "04";
            S000_CALL_CIZQACRL();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
            WS_BAL_CARD_NO = CICQACRL.O_DATA.O_REL_AC_NO;
        } else {
            WS_BAL_CARD_NO = DCCSDAMC.OLD_CARD_NO;
        }
        IBS.init(SCCGWA, BPCFFTXI);
        JIBS_NumStr = "" + 0;
        BPCFFTXI.TX_DATA.AUH_FLG = JIBS_NumStr.charAt(0);
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '1';
        if (WS_ADSC_TYPE == 'C') {
            BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '0';
            BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = WS_BAL_CARD_NO;
        }
        BPCFFTXI.TX_DATA.CARD_PSBK_NO = DCCSDAMC.NEW_CARD_NO;
        BPCFFTXI.TX_DATA.CCY_TYPE = '1';
        BPCFFTXI.TX_DATA.SVR_CD = "0260020";
        BPCFFTXI.TX_DATA.CI_NO = WS_CIFNO;
        S000_CALL_BPZFFTXI();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCTCALF);
        BPCTCALF.INPUT_AREA.FUNC_CODE = 'C';
        BPCTCALF.INPUT_AREA.TX_CNT = 1;
        BPCTCALF.INPUT_AREA.TX_CI = WS_CIFNO;
        WS_FEE_PROD = WS_OLD_CARD_INFO.WS_NEW_PROD_CODE;
        BPCTCALF.INPUT_AREA.PROD_CODE = WS_OLD_CARD_INFO.WS_NEW_PROD_CODE;
        BPCTCALF.INPUT_AREA.PROD_CODE1 = WS_OLD_CARD_INFO.WS_NEW_PROD_CODE;
        CEP.TRC(SCCGWA, DCRPRDPR.DATA_TXT.SR_RC_CD);
        if (DCRPRDPR.DATA_TXT.SR_RC_CD.equalsIgnoreCase("220")) {
            BPCTCALF.INPUT_AREA.ATTR_VAL.DEF_FLG = "05";
            BPCTCALF.INPUT_AREA.ATTR_VAL.DEF_FLG = "05";
            BPCTCALF.INPUT_AREA.FEE_ATTR = IBS.CLS2CPY(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL);
        }
        if (DCRPRDPR.DATA_TXT.SR_RC_CD.equalsIgnoreCase("120")) {
            BPCTCALF.INPUT_AREA.ATTR_VAL.DEF_FLG = "04";
            BPCTCALF.INPUT_AREA.ATTR_VAL.DEF_FLG = "04";
            BPCTCALF.INPUT_AREA.FEE_ATTR = IBS.CLS2CPY(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL);
        }
        BPCTCALF.INPUT_AREA.TX_AC = DCCSDAMC.NEW_CARD_NO;
        S000_CALL_BPZFCALF();
        if (pgmRtn) return;
    }
    public void B061_CHECK_CARD_LOST_DAYS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRINRCD);
        DCRINRCD.KEY.CARD_NO = DCRCDDAT.KEY.CARD_NO;
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = DCRINRCD.CRT_DATE;
        SCCCLDT.DATE2 = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, DCRINRCD.CRT_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_INPUT_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, DCRINRCD.CRT_DATE);
        CEP.TRC(SCCGWA, SCCCLDT.DAYS);
        if (SCCCLDT.DAYS < 3) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_BYD_THR_DAYS_CAN_CHG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B062_CHECK_CARD_BALANCE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUTBAL);
        DCCUTBAL.INPUT_DATA.AC = DCCSDAMC.OLD_CARD_NO;
        S000_CALL_DCZUTBAL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCUTBAL.OUTPUT_DATA.TOT_BAL);
        if (K_MAX_BL <= DCCUTBAL.OUTPUT_DATA.TOT_BAL) {
            B061_CHECK_CARD_LOST_DAYS();
            if (pgmRtn) return;
        }
    }
    public void B080_NEW_OLD_CARD_FILE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUCHCD);
        DCCUCHCD.FUNC_CODE = 'A';
        DCCUCHCD.INPUT_DATA.CARD_NO = DCCSDAMC.NEW_CARD_NO;
        DCCUCHCD.INPUT_DATA.OLD_CARD_NO = DCCSDAMC.OLD_CARD_NO;
        DCCUCHCD.INPUT_DATA.OLD_CARD_MEDI = DCRCDDAT.CARD_MEDI;
        CEP.TRC(SCCGWA, DCRCDDAT.CARD_MEDI);
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCSDAMC.NEW_CARD_NO;
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        DCCUCHCD.INPUT_DATA.NEW_CARD_MEDI = DCRCDDAT.CARD_MEDI;
        CEP.TRC(SCCGWA, DCRCDDAT.CARD_MEDI);
        DCCUCHCD.INPUT_DATA.NEW_CARD_ACT_FG = 'N';
        DCCUCHCD.INPUT_DATA.NEW_CARD_ACT_DT = 0;
        S000_CALL_DCZUCHCD();
        if (pgmRtn) return;
    }
    public void B090_CARD_AGREEMENT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSCCAG);
        DCCSCCAG.NEW_CARD = DCCSDAMC.NEW_CARD_NO;
        DCCSCCAG.OLD_CARD = DCCSDAMC.OLD_CARD_NO;
        S000_CALL_DCZSCCAG();
        if (pgmRtn) return;
    }
    public void B025_OPEN_IC_AC() throws IOException,SQLException,Exception {
        if (WS_OPEN_IC_FLG == 'Y' 
            && SCCGWA.COMM_AREA.TR_ID.TR_CODE == 0053) {
            IBS.init(SCCGWA, DDCUOPAC);
            DDCUOPAC.ACO_AC_TYP = '1';
            DDCUOPAC.CI_NO = DCCSDAMC.CI_NO;
            DDCUOPAC.CARD_TYP = '1';
            DDCUOPAC.PROD_CODE = WS_IC_PROD_CD;
            DDCUOPAC.AC_TYP = 'A';
            DDCUOPAC.PSBK_FLG = '2';
            DDCUOPAC.CARD_FLG = '1';
            DDCUOPAC.ACNO_FLG = 'B';
            DDCUOPAC.DRAW_MTH = ' ';
            DDCUOPAC.AC = DCCSDAMC.NEW_CARD_NO;
            DDCUOPAC.CCY = "156";
            DDCUOPAC.CCY_TYPE = '1';
            DDCUOPAC.AC_TYPE = '2';
            S000_CALL_DDZUOPAC();
            if (pgmRtn) return;
        }
    }
    public void B080_NEW_CARD_RELN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSACR);
        CICSACR.FUNC = 'A';
        CICSACR.DATA.AGR_NO = DCCSDAMC.NEW_CARD_NO;
        CICSACR.DATA.ENTY_TYP = '2';
        CICSACR.DATA.STSW = "11011000";
        if (WS_OLD_CARD_INFO.WS_CARD_LNK_TYP != '1') {
            CICSACR.DATA.CNTRCT_TYP = "298";
            CICSACR.DATA.CI_NO = WS_CARD_HLDR_CINO;
        } else {
            CICSACR.DATA.CNTRCT_TYP = "299";
            CICSACR.DATA.CI_NO = WS_CARD_OWN_CINO;
        }
        CICSACR.DATA.PROD_CD = WS_OLD_CARD_INFO.WS_NEW_PROD_CODE;
        CICSACR.DATA.FRM_APP = "DC";
        CICSACR.DATA.SHOW_FLG = 'Y';
        CICSACR.DATA.OPN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CICSACR.DATA.OPEN_DT = SCCGWA.COMM_AREA.AC_DATE;
        S000_CALL_CIZSACR();
        if (pgmRtn) return;
    }
    public void B210_UPDATE_DDTSTSW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRSTSW);
        IBS.init(SCCGWA, BPCPQORG);
        DDRSTSW.KEY.CUS_AC = DCCSDAMC.OLD_CARD_NO;
        DDRSTSW.STS = 'N';
        CEP.TRC(SCCGWA, DDRSTSW.KEY.CUS_AC);
        T000_STARTBR_DDTSTSW();
        if (pgmRtn) return;
        T000_READNEXT_DDTSTSW();
        if (pgmRtn) return;
        while (WS_TBL_FLAG != 'N') {
            BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPQORG.BBR);
            CEP.TRC(SCCGWA, BPCPQORG.BRANCH_BR);
            DDRSTSW.KEY.CUS_AC = DCCSDAMC.NEW_CARD_NO;
            DDRSTSW.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRSTSW.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            DDRSTSW.UPDTBL_BRDP = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            DDRSTSW.UPDTBL_BR = BPCPQORG.BRANCH_BR;
            DDRSTSW.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRSTSW.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            DDRSTSW.CRT_BRDP = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            DDRSTSW.CRT_BR = BPCPQORG.BRANCH_BR;
            T000_WRITE_DDTSTSW();
            if (pgmRtn) return;
            T000_READNEXT_DDTSTSW();
            if (pgmRtn) return;
        }
        T000_ENDBR_DDTSTSW();
        if (pgmRtn) return;
    }
    public void B060_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSDAMC.RMK);
        IBS.init(SCCGWA, DCCHDAMC);
        IBS.init(SCCGWA, DCCNDAMC);
        DCCNDAMC.OLD_CARD_NO = DCCSDAMC.OLD_CARD_NO;
        DCCNDAMC.HOLDER_IDTYP = DCCSDAMC.HOLDER_IDTYP;
        DCCNDAMC.HOLDER_IDNO = DCCSDAMC.HOLDER_IDNO;
        DCCNDAMC.HOLDER_NAME = DCCSDAMC.HOLDER_NAME;
        DCCNDAMC.NEW_CARD_NO = DCCSDAMC.NEW_CARD_NO;
        DCCNDAMC.RMK = DCCSDAMC.RMK;
        DCCNDAMC.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCCNDAMC.LAST_USER = SCCGWA.COMM_AREA.TL_ID;
        R000_HISTORY_RECORD();
        if (pgmRtn) return;
    }
    public void R000_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.TX_RMK = "DAMAGE/OUT CARD DIRECT CHANGE";
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID = "DCCHDAMC";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_TOOL = DCCSDAMC.OLD_CARD_NO;
        BPCPNHIS.INFO.CI_NO = DCRCDDAT.CARD_OWN_CINO;
        BPCPNHIS.INFO.AC = DCCSDAMC.OLD_CARD_NO;
        BPCPNHIS.INFO.FMT_ID_LEN = 416;
        if (SCCGWA.COMM_AREA.TR_ID.TR_CODE == 0053) {
            BPCPNHIS.INFO.TX_TYP_CD = "P105";
        } else {
            BPCPNHIS.INFO.TX_TYP_CD = "P120";
        }
        BPCPNHIS.INFO.OLD_DAT_PT = DCCODAMC;
        BPCPNHIS.INFO.NEW_DAT_PT = DCCNDAMC;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.TX_RMK = "DAMAGE/OUT CARD DIRECT CHANGE";
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID = "DCCHDAMC";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_TOOL = DCCSDAMC.OLD_CARD_NO;
        BPCPNHIS.INFO.CI_NO = DCRCDDAT.CARD_OWN_CINO;
        BPCPNHIS.INFO.AC = DCCSDAMC.NEW_CARD_NO;
        BPCPNHIS.INFO.FMT_ID_LEN = 416;
        if (SCCGWA.COMM_AREA.TR_ID.TR_CODE == 0053) {
            BPCPNHIS.INFO.TX_TYP_CD = "P105";
        } else {
            BPCPNHIS.INFO.TX_TYP_CD = "P120";
        }
        BPCPNHIS.INFO.OLD_DAT_PT = DCCODAMC;
        BPCPNHIS.INFO.NEW_DAT_PT = DCCNDAMC;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B070_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT);
        WS_OUTPUT.WS_OLD_CARD_NO = DCCSDAMC.OLD_CARD_NO;
        WS_OUTPUT.WS_HOLDER_IDTYP = DCCSDAMC.HOLDER_IDTYP;
        WS_OUTPUT.WS_HOLDER_IDNO = DCCSDAMC.HOLDER_IDNO;
        WS_OUTPUT.WS_HOLDER_NAME = DCCSDAMC.HOLDER_NAME;
        WS_OUTPUT.WS_NEW_CARD_NO = DCCSDAMC.NEW_CARD_NO;
        WS_OUTPUT.WS_LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        WS_OUTPUT.WS_LAST_USER = SCCGWA.COMM_AREA.TL_ID;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 383;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B100_CANCEL_VCH_LOSE_FLG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRIAMST);
        DCRIAMST.KEY.VIA_AC = DCRACLNK.VIA_AC;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            if (DCRIAMST.STS_WORD == null) DCRIAMST.STS_WORD = "";
            JIBS_tmp_int = DCRIAMST.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DCRIAMST.STS_WORD += " ";
            DCRIAMST.STS_WORD = DCRIAMST.STS_WORD.substring(0, 7 - 1) + "0" + DCRIAMST.STS_WORD.substring(7 + 1 - 1);
            DCRIAMST.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRIAMST.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        }
    }
    public void B060_OLD_CARD_STATUS_SETTING() throws IOException,SQLException,Exception {
        if (DCCSDAMC.OLD_CARD_NO.trim().length() > 0) {
            IBS.init(SCCGWA, DCRCDDAT);
            DCRCDDAT.KEY.CARD_NO = DCCSDAMC.OLD_CARD_NO;
            T000_READUPD_DCTCDDAT();
            if (pgmRtn) return;
            DCRCDDAT.CARD_STS = 'C';
            DCRCDDAT.CLO_DT = SCCGWA.COMM_AREA.AC_DATE;
            DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_UPDATE_DCTCDDAT();
            if (pgmRtn) return;
        }
    }
    public void B100_OLD_CARD_CANCEL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSOCAC);
        BPCSOCAC.FUNC = 'U';
        BPCSOCAC.OLD_AC = DCCSDAMC.OLD_CARD_NO;
        BPCSOCAC.AC = DCCSDAMC.NEW_CARD_NO;
        BPCSOCAC.STS = 'K';
        if (WS_OLD_CARD_INFO.WS_CARD_MEDI == '1') {
            BPCSOCAC.NEW_BV_TYP = '2';
        }
        if (WS_OLD_CARD_INFO.WS_CARD_MEDI == '2') {
            BPCSOCAC.NEW_BV_TYP = '1';
        }
        if (WS_NEW_ADSC_TYPE == 'C') {
            BPCSOCAC.NEW_BV_TYP = '3';
        }
        BPCSOCAC.NEW_PROD_CD = WS_OLD_CARD_INFO.WS_NEW_PROD_CODE;
        if (SCCGWA.COMM_AREA.TR_ID.TR_CODE == 0053) {
            BPCSOCAC.IC_AID_FLG = 'N';
        }
        S000_CALL_BPZSOCAC();
        if (pgmRtn) return;
    }
    public void B110_UPDATE_DDTCCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIMCYY);
        DDCIMCYY.TX_TYPE = 'U';
        DDCIMCYY.DATA.AC_OLD = DCCSDAMC.OLD_CARD_NO;
        DDCIMCYY.DATA.AC_NEW = DCCSDAMC.NEW_CARD_NO;
        if (SCCGWA.COMM_AREA.TR_ID.TR_CODE == 0053) {
            DDCIMCYY.IC_FLG = 'N';
        }
        if (SCCGWA.COMM_AREA.TR_ID.TR_CODE == 0043) {
            DDCIMCYY.IC_FLG = 'Y';
        }
        S000_CALL_DDZIMCYY();
        if (pgmRtn) return;
    }
    public void B111_UPDATE_CCYTYP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = DCCSDAMC.NEW_CARD_NO;
        DDRCCY.CCY = "156";
        DDRCCY.CCY_TYPE = '1';
        T000_READUPD_DDTCCY();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'Y') {
            DDRCCY.AC_TYPE = 'A';
        }
        DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_UPDATE_DDTCCY();
        if (pgmRtn) return;
    }
    public void B120_UPDATE_TDTSMST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCCHDC);
        TDCCHDC.OLD_AC = DCCSDAMC.OLD_CARD_NO;
        TDCCHDC.NEW_AC = DCCSDAMC.NEW_CARD_NO;
        S000_CALL_TDZCHDC();
        if (pgmRtn) return;
    }
    public void B130_UPDATE_DCTINRCD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRINRCD);
        DCRINRCD.KEY.CARD_NO = DCCSDAMC.OLD_CARD_NO;
        T000_READUPD_DCTINRCD();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'Y') {
            DCRINRCD.NEW_CARD_NO = DCCSDAMC.NEW_CARD_NO;
            DCRINRCD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRINRCD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_UPDATE_DCTINRCD();
            if (pgmRtn) return;
        }
    }
    public void B140_REGIST_AGENT_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSAGEN);
        CICSAGEN.FUNC = 'A';
        CICSAGEN.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        CICSAGEN.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        CICSAGEN.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        CICSAGEN.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CICSAGEN.TLR_NO = SCCGWA.COMM_AREA.TL_ID;
        CICSAGEN.CI_NO = DCCSDAMC.CI_NO;
        CICSAGEN.OUT_AC = DCCSDAMC.NEW_CARD_NO;
        CICSAGEN.ID_TYP = CICGAGA_AGENT_AREA.ID_TYP;
        CICSAGEN.ID_NO = CICGAGA_AGENT_AREA.ID_NO;
        CICSAGEN.AGENT_TP = "01";
        CICSAGEN.CI_NAME = CICGAGA_AGENT_AREA.CI_NM;
        CICSAGEN.PHONE = CICGAGA_AGENT_AREA.PHONE;
        S000_CALL_CIZSAGEN();
        if (pgmRtn) return;
    }
    public void B150_MOVE_OLD_DCTCRDLT() throws IOException,SQLException,Exception {
        WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_REGN_TYP = DCRCRDLT.KEY.REGN_TYP;
        WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_CHNL_NO = DCRCRDLT.KEY.CHNL_NO;
        WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_TXN_TYPE = DCRCRDLT.KEY.TXN_TYPE;
        WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_LMT_CCY = DCRCRDLT.KEY.LMT_CCY;
        WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_STA_DT = DCRCRDLT.STA_DT;
        WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_STA_TM = DCRCRDLT.STA_TM;
        WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_END_DT = DCRCRDLT.END_DT;
        WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_END_TM = DCRCRDLT.END_TM;
        WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_TXN_LMT_AMT = DCRCRDLT.TXN_LMT_AMT;
        WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_DLY_LMT_AMT = DCRCRDLT.DLY_LMT_AMT;
        WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_DLY_LMT_VOL = DCRCRDLT.DLY_LMT_VOL;
        WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_MLY_LMT_AMT = DCRCRDLT.MLY_LMT_AMT;
        WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_MLY_LMT_VOL = DCRCRDLT.MLY_LMT_VOL;
        WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_SYY_LMT_AMT = DCRCRDLT.SYY_LMT_AMT;
        WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_YLY_LMT_AMT = DCRCRDLT.YLY_LMT_AMT;
        WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_SE_LMT_AMT = DCRCRDLT.SE_LMT_AMT;
    }
    public void B160_INSERT_NEW_DCTCRDLT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCRDLT);
        DCRCRDLT.KEY.REGN_TYP = WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_REGN_TYP;
        DCRCRDLT.KEY.CHNL_NO = WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_CHNL_NO;
        DCRCRDLT.KEY.TXN_TYPE = WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_TXN_TYPE;
        DCRCRDLT.KEY.LMT_CCY = WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_LMT_CCY;
        DCRCRDLT.STA_DT = WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_STA_DT;
        DCRCRDLT.STA_TM = WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_STA_TM;
        DCRCRDLT.END_DT = WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_END_DT;
        DCRCRDLT.END_TM = WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_END_TM;
        DCRCRDLT.TXN_LMT_AMT = WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_TXN_LMT_AMT;
        DCRCRDLT.DLY_LMT_AMT = WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_DLY_LMT_AMT;
        DCRCRDLT.DLY_LMT_VOL = WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_DLY_LMT_VOL;
        DCRCRDLT.MLY_LMT_AMT = WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_MLY_LMT_AMT;
        DCRCRDLT.MLY_LMT_VOL = WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_MLY_LMT_VOL;
        DCRCRDLT.SYY_LMT_AMT = WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_SYY_LMT_AMT;
        DCRCRDLT.YLY_LMT_AMT = WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_YLY_LMT_AMT;
        DCRCRDLT.SE_LMT_AMT = WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_SE_LMT_AMT;
    }
    public void B200_CARRY_BIND_RELATION() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_AC_TYPE);
        IBS.init(SCCGWA, DCCUBIND);
        DCCUBIND.IO_AREA.FUNC = 'H';
        DCCUBIND.IO_AREA.CARD_NEW = DCCSDAMC.NEW_CARD_NO;
        DCCUBIND.IO_AREA.CARD_OLD = DCCSDAMC.OLD_CARD_NO;
        DCCUBIND.IO_AREA.AC_TYP = WS_AC_TYPE;
        S000_CALL_DCZUBIND();
        if (pgmRtn) return;
    }
    public void S000_CALL_DCZUBIND() throws IOException,SQLException,Exception {
        DCZUBIND DCZUBIND = new DCZUBIND();
        DCZUBIND.MP(SCCGWA, DCCUBIND);
        CEP.TRC(SCCGWA, DCCUBIND.RC.RC_CODE);
        if (DCCUBIND.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DCCUBIND.RC.RC_CODE);
        }
    }
    public void S000_CALL_BPZSOCAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-OCAC-INFO", BPCSOCAC);
        CEP.TRC(SCCGWA, BPCSOCAC.RC.RC_CODE);
        if (BPCSOCAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSOCAC.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSOCAC.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCSDAMC.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_CIZSAGEN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SVR-CIZSAGEN", CICSAGEN);
        if (CICSAGEN.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSAGEN.RC);
        }
    }
    public void S000_CALL_BPZFFTXI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_F_TX_INFO, BPCFFTXI);
        CEP.TRC(SCCGWA, BPCFFTXI.RC);
        if (BPCFFTXI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFTXI.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFFTXI.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCSDAMC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFCALF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_F_CAL_FEE, BPCTCALF);
        CEP.TRC(SCCGWA, BPCTCALF.RC);
        if (BPCTCALF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTCALF.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTCALF.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCSDAMC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUCHCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DCZUCHCD, DCCUCHCD);
        CEP.TRC(SCCGWA, DCCUCHCD.RC.RC_CODE);
        if (DCCUCHCD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCHCD.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUCHCD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCSDAMC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZSCCAG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-S-DCZSCCAG", DCCSCCAG);
    }
    public void S000_CALL_BPZPFPDT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-FEE-UNCHG-CHK", BPCPFPDT);
        CEP.TRC(SCCGWA, BPCPFPDT.RC);
        if (BPCPFPDT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPFPDT.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPFPDT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCSDAMC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCSDAMC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUMATP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-M-AUTO-TD-PLAN", DCCUMATP);
        if (DCCUMATP.O_AREA.RC_CODE != 0) {
            WS_ERR_MSG = DCCUMATP.O_AREA.MSG_ID;
            IBS.CPY2CLS(SCCGWA, DCCUMATP.O_AREA.MSG_ID, DCCSDAMC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUCCHA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DCZUCCHA, DCCUCCHA);
        if (DCCUCCHA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCCHA.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUCCHA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCSDAMC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZUBUSE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-TLR-BV-USE", BPCUBUSE);
    }
    public void S000_CALL_DCZUPSWM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DCZUPSWM, DCCUPSWM);
        if (DCCUPSWM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUPSWM.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUPSWM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCSDAMC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZCKOC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CHECK-OPEN-CARD", CICCKOC);
        CEP.TRC(SCCGWA, CICCKOC.RC);
        if (CICCKOC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCKOC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READUPD_DCTCDORD() throws IOException,SQLException,Exception {
        DCTCDORD_RD = new DBParm();
        DCTCDORD_RD.TableName = "DCTCDORD";
        DCTCDORD_RD.where = "CARD_NO = :DCRCDORD.KEY.CARD_NO "
            + "AND EXC_CARD_TMS = :DCRCDORD.KEY.EXC_CARD_TMS "
            + "AND CRT_STS = :DCRCDORD.CRT_STS";
        DCTCDORD_RD.upd = true;
        IBS.READ(SCCGWA, DCRCDORD, this, DCTCDORD_RD);
    }
    public void T000_READ_DCTCDORD() throws IOException,SQLException,Exception {
        DCTCDORD_RD = new DBParm();
        DCTCDORD_RD.TableName = "DCTCDORD";
        DCTCDORD_RD.where = "CARD_NO = :DCRCDORD.KEY.CARD_NO "
            + "AND EXC_CARD_TMS = :DCRCDORD.KEY.EXC_CARD_TMS "
            + "AND CRT_STS = :DCRCDORD.CRT_STS";
        IBS.READ(SCCGWA, DCRCDORD, this, DCTCDORD_RD);
    }
    public void T000_UPDATE_DCTCDORD() throws IOException,SQLException,Exception {
        DCTCDORD_RD = new DBParm();
        DCTCDORD_RD.TableName = "DCTCDORD";
        IBS.REWRITE(SCCGWA, DCRCDORD, DCTCDORD_RD);
    }
    public void T000_READ_DCTBRCLC() throws IOException,SQLException,Exception {
        DCTBRCLC_RD = new DBParm();
        DCTBRCLC_RD.TableName = "DCTBRCLC";
        IBS.READ(SCCGWA, DCRBRCLC, DCTBRCLC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTBRCLC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_BR.rp = new DBParm();
        DCTCDDAT_BR.rp.TableName = "DCTCDDAT";
        DCTCDDAT_BR.rp.where = "PRIM_CARD_NO = :DCRCDDAT.PRIM_CARD_NO";
        DCTCDDAT_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, DCRCDDAT, this, DCTCDDAT_BR);
    }
    public void T000_READNEXT_DCTCDDAT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRCDDAT, this, DCTCDDAT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDDAT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_DCTCDDAT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTCDDAT_BR);
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST, DCCSDAMC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDDAT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READUPD_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.upd = true;
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST, DCCSDAMC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDDAT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_UPDATE_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.REWRITE(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
    }
    public void T000_WRITE_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.WRITE(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
    }
    public void T000_READUPD_DCTINRCD() throws IOException,SQLException,Exception {
        DCTINRCD_RD = new DBParm();
        DCTINRCD_RD.TableName = "DCTINRCD";
        DCTINRCD_RD.where = "CARD_NO = :DCRINRCD.KEY.CARD_NO";
        DCTINRCD_RD.upd = true;
        IBS.READ(SCCGWA, DCRINRCD, this, DCTINRCD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTINRCD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_UPDATE_DCTINRCD() throws IOException,SQLException,Exception {
        DCTINRCD_RD = new DBParm();
        DCTINRCD_RD.TableName = "DCTINRCD";
        IBS.REWRITE(SCCGWA, DCRINRCD, DCTINRCD_RD);
    }
    public void T000_STARTBR_DDTSTSW() throws IOException,SQLException,Exception {
        DDTSTSW_BR.rp = new DBParm();
        DDTSTSW_BR.rp.TableName = "DDTSTSW";
        DDTSTSW_BR.rp.where = "CUS_AC = :DDRSTSW.KEY.CUS_AC "
            + "AND STS = :DDRSTSW.STS";
        IBS.STARTBR(SCCGWA, DDRSTSW, this, DDTSTSW_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTSTSW";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_DDTSTSW() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRSTSW, this, DDTSTSW_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTSTSW";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_TBL_FLAG);
    }
    public void T000_WRITE_DDTSTSW() throws IOException,SQLException,Exception {
        DDTSTSW_RD = new DBParm();
        DDTSTSW_RD.TableName = "DDTSTSW";
        IBS.WRITE(SCCGWA, DDRSTSW, DDTSTSW_RD);
    }
    public void T000_ENDBR_DDTSTSW() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTSTSW_BR);
    }
    public void T000_READUPD_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "CUS_AC = :DDRCCY.CUS_AC "
            + "AND CCY = :DDRCCY.CCY "
            + "AND CCY_TYPE = :DDRCCY.CCY_TYPE";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTCCY";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_UPDATE_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.REWRITE(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void T000_STARTBR_DCTCRDLT() throws IOException,SQLException,Exception {
