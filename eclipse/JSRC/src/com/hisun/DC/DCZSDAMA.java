package com.hisun.DC;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCFFTXI;
import com.hisun.BP.BPCGCFEE;
import com.hisun.BP.BPCGPFEE;
import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPCPFPDT;
import com.hisun.BP.BPCPLOSS;
import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPORUP_DATA_INFO;
import com.hisun.BP.BPCPQBNK_DATA_INFO;
import com.hisun.BP.BPCPQORG;
import com.hisun.BP.BPCPQPRD;
import com.hisun.BP.BPCPRMM;
import com.hisun.BP.BPCPRMR;
import com.hisun.BP.BPCSLOSS;
import com.hisun.BP.BPCSOCAC;
import com.hisun.BP.BPCTCALF;
import com.hisun.BP.BPRPDCD;
import com.hisun.CI.CICCUST;
import com.hisun.CI.CICGAGA_AGENT_AREA_AGENT_AREA;
import com.hisun.CI.CICKIDD;
import com.hisun.CI.CICMSG_ERROR_MSG;
import com.hisun.CI.CICSAGEN;
import com.hisun.CI.CICSSCH;
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

public class DCZSDAMA {
    boolean pgmRtn = false;
    String CPN_BPZPQPRD = "BP-P-QUERY-PRDT-INFO";
    String CPN_DCZSGSEQ = "DC-S-GET-SEQ";
    String CPN_DCZFCDGG = "DC-F-CHK-DIGIT-GEN";
    String CPN_DCZUPSWM = "DC-U-PSW-MAINTAIN";
    String CPN_F_F_TX_INFO = "BP-F-F-TX-INFO";
    String CPN_F_F_CAL_FEE = "BP-F-F-CAL-FEE";
    String CPN_REC_NHIS = "BP-REC-NHIS";
    String CPN_DCZUCHCD = "DC-U-CHANG-CARD";
    String CPN_DCZUCCHA = "DC-U-CARD-CHA";
    String K_OUTPUT_FMT = "DC142";
    double K_MAX_BL = 50000;
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    String WS_CARDNO = " ";
    short WS_CARD_SEQNO = 0;
    long WS_CARD_SEQ = 0;
    long WS_SEQNO = 0;
    int WS_SEQ_LEN = 0;
    int WS_SEG1_LEN = 0;
    int WS_START = 0;
    int WS_PTR = 0;
    String WS_CIFNO = " ";
    String WS_OWNER_CINO = " ";
    String WS_EMBOSS_NAME = " ";
    String WS_PROD_CD = " ";
    String WS_N_PROD_CD = " ";
    String WS_OLD_PROD_CD = " ";
    int WS_CARD_BR = 0;
    String WS_CINO = " ";
    char WS_ADSC_TYPE = ' ';
    short WS_RMK = 0;
    char WS_PSW_FLG = ' ';
    char WS_PSW_LOST_FLG = ' ';
    char WS_ACTIVATION_FLG = ' ';
    char WS_CHK_END_FLG = ' ';
    char WS_SOCIAL_FLG = 'N';
    String WS_FIN_CARD_NO = " ";
    char WS_CI_GROUP = ' ';
    String WS_FEE_PROD = " ";
    char WS_CARD_PHY_TYP = ' ';
    String WS_CHK_CARDNO = " ";
    char WS_OLD_CARD_MEDI = ' ';
    long WS_CARD_SEG1 = 0;
    DCZSDAMA_WS_CARD_SEG1_R WS_CARD_SEG1_R = new DCZSDAMA_WS_CARD_SEG1_R();
    long WS_CARD_SEQNO1 = 0;
    String WS_LOST_NO = " ";
    String WS_OLD_CARD_PSW = " ";
    char WS_OLD_SAME_TFR_FLG = ' ';
    char WS_OLD_DIFF_TFR_FLG = ' ';
    char WS_OLD_DRAW_OVER_FLG = ' ';
    char WS_OLD_HOLD_AC_FLG = ' ';
    char WS_OLD_DB_FREE_FLG = ' ';
    char WS_OLD_TRAN_WITH_CARD = ' ';
    char WS_OLD_TRAN_NO_CARD = ' ';
    char WS_OLD_TRAN_CRS_BOR = ' ';
    char WS_OLD_TRAN_ATM_FLG = ' ';
    char WS_OLD_PROD_LMT_FLG = ' ';
    String WS_OLD_CARD_TYP = " ";
    char WS_AC_TYPE = ' ';
    String WS_CARD_OWN_CINO = " ";
    String WS_CARD_HLD_CINO = " ";
    char WS_OPEN_IC_FLG = ' ';
    char WS_NEW_ADSC_TYPE = ' ';
    char WS_OLD_ADSC_TYPE = ' ';
    char WS_CARD_MEDI = ' ';
    char WS_OLD_ANNU_FEE_FREE = ' ';
    short WS_OLD_ANU_FEE_FREE = 0;
    String WS_IC_PROD_CD = " ";
    String WS_NEW_BV_CD = " ";
    int WS_CEID_EXP_DT = 0;
    String WS_APP_TYP = " ";
    char WS_CARD_LNK_TYP = ' ';
    String WS_OLD_PRIM_CARD_NO = " ";
    DCZSDAMA_WS_OLD_CARD_CRDLT_INFO WS_OLD_CARD_CRDLT_INFO = new DCZSDAMA_WS_OLD_CARD_CRDLT_INFO();
    DCZSDAMA_WS_OLD_CARD_TXTOT_INFO WS_OLD_CARD_TXTOT_INFO = new DCZSDAMA_WS_OLD_CARD_TXTOT_INFO();
    String WS_OLD_OPEN_CHNL = " ";
    int WS_LOS_DT = 0;
    int WS_LOS_DT1 = 0;
    short WS_LOS_DAYS = 0;
    String WS_OLD_CARD_STSW = " ";
    short WS_I = 0;
    DCZSDAMA_WS_OUTPUT WS_OUTPUT = new DCZSDAMA_WS_OUTPUT();
    int WS_CNT_T = 0;
    long WS_AP_SEQ = 0;
    int WS_CNT_DCSEQ = 0;
    int WS_CNT_ORD = 0;
    int WS_CNT_BIN = 0;
    long WS_MAX_BATCHNO = 0;
    String WS_NEW_PROD_CD = " ";
    char WS_CI_TYP = ' ';
    String WS_ID_TYPE = " ";
    String WS_CI_NM = " ";
    String WS_ID_NO = " ";
    char WS_ENTY_TYP = ' ';
    String WS_ENTY_NO = " ";
    String WS_GRPS_NO = " ";
    char WS_TBL_FLAG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCWRMSG SCCWRMSG = new SCCWRMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCRCDORD DCRCDORD = new DCRCDORD();
    DCRBINPM DCRBINPM = new DCRBINPM();
    DCRPDBIN DCRPDBIN = new DCRPDBIN();
    DCRDCSEQ DCRDCSEQ = new DCRDCSEQ();
    DCRBRARC DCRBRARC = new DCRBRARC();
    DCCHDAMA DCCHDAMA = new DCCHDAMA();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DCCHDAMA DCCNDAMA = new DCCHDAMA();
    DCCPQPRD DCCDQPRD = new DCCPQPRD();
    DCCFCDGG DCCFCDGG = new DCCFCDGG();
    CICSSCH CICSSCH = new CICSSCH();
    DCCSGSEQ DCCSGSEQ = new DCCSGSEQ();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPCTCALF BPCTCALF = new BPCTCALF();
    DCCUPSWM DCCUPSWM = new DCCUPSWM();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    CICCUST CICCUST = new CICCUST();
    DCCUPSWM DCCPSWM = new DCCUPSWM();
    DCCUCHCD DCCUCHCD = new DCCUCHCD();
    DCCUCCHA DCCUCCHA = new DCCUCCHA();
    DCCSCCAG DCCSCCAG = new DCCSCCAG();
    BPCPFPDT BPCPFPDT = new BPCPFPDT();
    DCRINRCD DCRINRCD = new DCRINRCD();
    DCRIAMST DCRIAMST = new DCRIAMST();
    DCRNOCRD DCRNOCRD = new DCRNOCRD();
    CICKIDD CICKIDD = new CICKIDD();
    DCCPACTY DCCPACTY = new DCCPACTY();
    BPCPRMR BPCPRMR = new BPCPRMR();
    DCRPRDPR DCRPRDPR = new DCRPRDPR();
    BPCPRMM BPCPRMM = new BPCPRMM();
    DCCUTBAL DCCUTBAL = new DCCUTBAL();
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCPLOSS BPCPLOSS = new BPCPLOSS();
    CIRBAS CIRBAS = new CIRBAS();
    CIRGRPM CIRGRPM = new CIRGRPM();
    BPRPDCD BPRPDCD = new BPRPDCD();
    DCCS0132 DCCS0132 = new DCCS0132();
    CICSACR CICSACR = new CICSACR();
    CICCHDC CICCHDC = new CICCHDC();
    DCCUMATP DCCUMATP = new DCCUMATP();
    DCRDIRAC DCRDIRAC = new DCRDIRAC();
    DCCUBIND DCCUBIND = new DCCUBIND();
    DCRCRDLT DCRCRDLT = new DCRCRDLT();
    DCRTXTOT DCRTXTOT = new DCRTXTOT();
    DCRAPPLC DCRAPPLC = new DCRAPPLC();
    BPCSOCAC BPCSOCAC = new BPCSOCAC();
    DDCIMCYY DDCIMCYY = new DDCIMCYY();
    TDCCHDC TDCCHDC = new TDCCHDC();
    DDCUOPAC DDCUOPAC = new DDCUOPAC();
    DCCUCSET DCCUCSET = new DCCUCSET();
    CICSAGEN CICSAGEN = new CICSAGEN();
    DCRDCCLS DCRDCCLS = new DCRDCCLS();
    DCCUGECD DCCUGECD = new DCCUGECD();
    DCCUPRCD DCCUPRCD = new DCCUPRCD();
    BPCSLOSS BPCSLOSS = new BPCSLOSS();
    BPCPQORG BPCPQORG = new BPCPQORG();
    DDRCCY DDRCCY = new DDRCCY();
    EARACLNK EARACLNK = new EARACLNK();
    EARACLNK EARACLNO = new EARACLNK();
    DCRCPARM DCRCPARM = new DCRCPARM();
    DDRSTSW DDRSTSW = new DDRSTSW();
    DDRSTSW DDRSTSN = new DDRSTSW();
    DCRCDDAT DCRODDAT = new DCRCDDAT();
    SCCGWA SCCGWA;
    BPCPORUP_DATA_INFO BPCPORUP;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPCGCFEE BPCGCFEE;
    BPCGPFEE BPCGPFEE;
    DCCSDAMA DCCSDAMA;
    CICGAGA_AGENT_AREA CICGAGA_AGENT_AREA;
    public void MP(SCCGWA SCCGWA, DCCSDAMA DCCSDAMA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSDAMA = DCCSDAMA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSDAMA return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        CICGAGA_AGENT_AREA = new CICGAGA_AGENT_AREA_AGENT_AREA();
        IBS.init(SCCGWA, CICGAGA_AGENT_AREA);
        IBS.CPY2CLS(SCCGWA, SCCGWA.AGENT_AREA_PTR, CICGAGA_AGENT_AREA);
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        IBS.init(SCCGWA, DCRDCCLS);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSDAMA.OLD_CARD_NO);
        CEP.TRC(SCCGWA, DCCSDAMA.HOLDER_IDTYP);
        CEP.TRC(SCCGWA, DCCSDAMA.HOLDER_IDNO);
        CEP.TRC(SCCGWA, DCCSDAMA.HOLDER_NAME);
        CEP.TRC(SCCGWA, DCCSDAMA.CARD_PASSWD);
        CEP.TRC(SCCGWA, DCCSDAMA.ISSUE_MTH);
        CEP.TRC(SCCGWA, DCCSDAMA.ENV_FLG);
        CEP.TRC(SCCGWA, DCCSDAMA.PICK_MTH);
        CEP.TRC(SCCGWA, DCCSDAMA.CARD_SEG1);
        CEP.TRC(SCCGWA, DCCSDAMA.CARD_SEQ);
        CEP.TRC(SCCGWA, DCCSDAMA.CARD_CLS_CD);
        CEP.TRC(SCCGWA, DCCSDAMA.CARD_BIN);
        CEP.TRC(SCCGWA, DCCSDAMA.N_CARD_PR_CD);
        CEP.TRC(SCCGWA, DCCSDAMA.NEW_CARD_NO);
        CEP.TRC(SCCGWA, DCCSDAMA.RC);
        CEP.TRC(SCCGWA, DCCSDAMA.RMK);
        CEP.TRC(SCCGWA, DCCSDAMA.LOST_NO);
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_CARD_CHECK();
        if (pgmRtn) return;
        B024_GET_PTOD_INF();
        if (pgmRtn) return;
        B025_GET_NEW_BV_CODE();
        if (pgmRtn) return;
        if (DCCSDAMA.PICK_MTH == '1') {
            B030_SELF_CARDNO_PROCESS();
            if (pgmRtn) return;
        } else {
            B031_NEW_CARDNO_GEN();
            if (pgmRtn) return;
        }
        B040_CARD_ORDER_FILE();
        if (pgmRtn) return;
        if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
            B050_FEE_PROCESS();
            if (pgmRtn) return;
        }
        if (DCCSDAMA.PICK_MTH == '2' 
            || DCCSDAMA.PICK_MTH == '3') {
            B050_CHANGE_CARD_ATTACHMENT();
            if (pgmRtn) return;
            if (WS_CARD_LNK_TYP == '1') {
                B222_PRIM_CARD_CHANGE();
                if (pgmRtn) return;
            }
            B081_NEW_CARD_RELN();
            if (pgmRtn) return;
            B082_CHANGE_CARD_PROCESS();
            if (pgmRtn) return;
            B083_CHANGE_ZHCP_PROCESS();
            if (pgmRtn) return;
            if (WS_ADSC_TYPE == 'P') {
                B084_CARRY_BIND_RELATION();
                if (pgmRtn) return;
            }
            B085_CHANGE_CARD_LIMIT();
            if (pgmRtn) return;
            B086_INHERIT_ACUM_LIMIT();
            if (pgmRtn) return;
            B087_CHANGE_APPLEPAY();
            if (pgmRtn) return;
            if (WS_ADSC_TYPE == 'C') {
                B088_CHANGE_POINT_AC();
                if (pgmRtn) return;
            }
            B089_OLD_CARD_CANCEL();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.TR_ID.TR_CODE == 52 
                || SCCGWA.COMM_AREA.TR_ID.TR_CODE == 0052) {
                B099_LOSS_REGIN_PRC();
                if (pgmRtn) return;
            }
            B100_UPDATE_DDTCCY();
            if (pgmRtn) return;
            if (DCCSDAMA.CHANG_TYP == '3') {
                B111_UPDATE_CCYTYP();
                if (pgmRtn) return;
            }
            B110_UPDATE_TDTSMST();
            if (pgmRtn) return;
            B112_UPDATE_EATACLNK();
            if (pgmRtn) return;
            if (DCCSDAMA.PICK_MTH != '1') {
                B120_UPDATE_DCTINRCD();
                if (pgmRtn) return;
            }
            B130_OPEN_IC_AC();
            if (pgmRtn) return;
            if (WS_OLD_CARD_STSW == null) WS_OLD_CARD_STSW = "";
            JIBS_tmp_int = WS_OLD_CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) WS_OLD_CARD_STSW += " ";
            CEP.TRC(SCCGWA, WS_OLD_CARD_STSW.substring(17 - 1, 17 + 1 - 1));
            if (WS_OLD_CARD_STSW == null) WS_OLD_CARD_STSW = "";
            JIBS_tmp_int = WS_OLD_CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) WS_OLD_CARD_STSW += " ";
            if (WS_OLD_CARD_STSW.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
                B200_UPDATE_DDTSTSW();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, "CFX002 END");
        if (DCCSDAMA.AGENT_FLG == '1') {
            B140_REGIST_AGENT_INF();
            if (pgmRtn) return;
        }
        B080_NEW_OLD_CARD_FILE();
        if (pgmRtn) return;
        B060_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
        B070_HISTORY_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSDAMA.OLD_CARD_NO);
        CEP.TRC(SCCGWA, DCCSDAMA.CARD_PASSWD);
        CEP.TRC(SCCGWA, DCCSDAMA.HOLDER_IDTYP);
        CEP.TRC(SCCGWA, DCCSDAMA.HOLDER_IDNO);
        CEP.TRC(SCCGWA, DCCSDAMA.HOLDER_NAME);
        CEP.TRC(SCCGWA, DCCSDAMA.ISSUE_MTH);
        CEP.TRC(SCCGWA, DCCSDAMA.ENV_FLG);
        CEP.TRC(SCCGWA, DCCSDAMA);
        CEP.TRC(SCCGWA, DCCSDAMA.PICK_MTH);
        CEP.TRC(SCCGWA, DCCSDAMA.NEW_CARD_NO);
        CEP.TRC(SCCGWA, DCCSDAMA.RMK);
        WS_RMK = DCCSDAMA.RMK;
        if (DCCSDAMA.CARD_BIN.trim().length() == 0) {
            if (DCCSDAMA.OLD_CARD_NO == null) DCCSDAMA.OLD_CARD_NO = "";
            JIBS_tmp_int = DCCSDAMA.OLD_CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) DCCSDAMA.OLD_CARD_NO += " ";
            DCCSDAMA.CARD_BIN = DCCSDAMA.OLD_CARD_NO.substring(0, 6);
            if (DCCSDAMA.OLD_CARD_NO == null) DCCSDAMA.OLD_CARD_NO = "";
            JIBS_tmp_int = DCCSDAMA.OLD_CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) DCCSDAMA.OLD_CARD_NO += " ";
            if (DCCSDAMA.OLD_CARD_NO.substring(0, 4).equalsIgnoreCase("9111")) {
                DCCSDAMA.CARD_BIN = "621462";
            }
        }
        if (DCCSDAMA.OLD_CARD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO, DCCSDAMA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSDAMA.HOLDER_IDTYP.trim().length() == 0 
            && DCCSDAMA.HOLDER_IDNO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CI_INFO_MISSING;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CI_INFO_MISSING, DCCSDAMA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSDAMA.PICK_MTH == '1') {
            if (DCCSDAMA.CARD_SEQ != 0 
                && DCCSDAMA.CARD_SEG1 != ' ') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_INPUT_ERR;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_INPUT_ERR, DCCSDAMA.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DCCSDAMA.CARD_BIN == null) DCCSDAMA.CARD_BIN = "";
            JIBS_tmp_int = DCCSDAMA.CARD_BIN.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) DCCSDAMA.CARD_BIN += " ";
            if (DCCSDAMA.OLD_CARD_NO == null) DCCSDAMA.OLD_CARD_NO = "";
            JIBS_tmp_int = DCCSDAMA.OLD_CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) DCCSDAMA.OLD_CARD_NO += " ";
            if (DCCSDAMA.CARD_BIN.substring(0, 4).equalsIgnoreCase("9111") 
                || DCCSDAMA.OLD_CARD_NO.substring(0, 4).equalsIgnoreCase("9111")) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NOT_SUPT_911_CARD;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_NOT_SUPT_911_CARD, DCCSDAMA.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DCCSDAMA.CARD_BIN == null) DCCSDAMA.CARD_BIN = "";
            JIBS_tmp_int = DCCSDAMA.CARD_BIN.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) DCCSDAMA.CARD_BIN += " ";
            if (DCCSDAMA.OLD_CARD_NO == null) DCCSDAMA.OLD_CARD_NO = "";
            JIBS_tmp_int = DCCSDAMA.OLD_CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) DCCSDAMA.OLD_CARD_NO += " ";
            if (DCCSDAMA.CARD_BIN.substring(0, 6).equalsIgnoreCase("685800") 
                || DCCSDAMA.OLD_CARD_NO.substring(0, 6).equalsIgnoreCase("685800")) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NOT_SUPT_685800_CARD;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_NOT_SUPT_685800_CARD, DCCSDAMA.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCCSDAMA.PICK_MTH == '1' 
            || DCCSDAMA.PICK_MTH == '2') {
            if (DCCSDAMA.CARD_SEQ != 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NO_NEED_CARD_SEQNO;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_NO_NEED_CARD_SEQNO, DCCSDAMA.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCCSDAMA.PICK_MTH == '3') {
            if (DCCSDAMA.CARD_SEQ == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO, DCCSDAMA.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCCSDAMA.CARD_CLS_CD.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_MD;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_MD, DCCSDAMA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSDAMA.HOLDER_IDTYP.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_ID_TYPE;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_ID_TYPE, DCCSDAMA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSDAMA.HOLDER_IDNO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_ID_NO;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_ID_NO, DCCSDAMA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSDAMA.HOLDER_NAME.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CI_NAME;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_CI_NAME, DCCSDAMA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_CARD_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDDAT);
        IBS.init(SCCGWA, DCRNOCRD);
        DCRCDDAT.KEY.CARD_NO = DCCSDAMA.OLD_CARD_NO;
        CEP.TRC(SCCGWA, DCRCDDAT.KEY.CARD_NO);
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        if (DCCSDAMA.CHANG_TYP == '3') {
            if (DCRCDDAT.CARD_MEDI != '4') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CDNOT_VIRTUAL;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CDNOT_VIRTUAL, DCCSDAMA.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            if (DCRCDDAT.CARD_MEDI == '4') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CDIS_VIRTUAL;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CDIS_VIRTUAL, DCCSDAMA.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, DCCSDAMA.GM_FLG);
        if (DCRCDDAT.ACNO_TYPE == '3' 
            && DCCSDAMA.GM_FLG != '3') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_THIRD_CN_CHANGE;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_THIRD_CN_CHANGE, DCCSDAMA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DCRCDDAT.PROD_CD);
        if (DCRCDDAT.PROD_CD.equalsIgnoreCase("6606097100") 
            && DCCSDAMA.GM_FLG == '3') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_FACE_CANT_DO;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_FACE_CANT_DO, DCCSDAMA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRCDDAT.CARD_STS == 'C') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_BE_CLOSED;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_BE_CLOSED, DCCSDAMA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRCDDAT.CARD_STS == '0') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_READY;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_READY, DCCSDAMA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        DCRNOCRD.OLD_CARD_NO = DCCSDAMA.OLD_CARD_NO;
        WS_OLD_CARD_MEDI = DCRCDDAT.CARD_MEDI;
        WS_PROD_CD = DCRCDDAT.PROD_CD;
        WS_OLD_PROD_CD = DCRCDDAT.PROD_CD;
        if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            && DCCSDAMA.PICK_MTH == '2') {
            if (DCRCDDAT.PROD_CD.equalsIgnoreCase("CAD11000") 
                || DCRCDDAT.PROD_CD.equalsIgnoreCase("CAD11100") 
                || DCRCDDAT.PROD_CD.equalsIgnoreCase("CAD10000")) {
                WS_PROD_CD = "CAD34000";
                DCCSDAMA.CARD_BIN = "621462";
            }
            if (DCRCDDAT.PROD_CD.equalsIgnoreCase("CAD12100") 
                || DCRCDDAT.PROD_CD.equalsIgnoreCase("CAD12000")) {
                WS_PROD_CD = "CAD16100";
                DCCSDAMA.CARD_BIN = "622568";
            }
            WS_CARD_SEG1 = DCCSDAMA.CARD_SEG1;
            IBS.CPY2CLS(SCCGWA, WS_CARD_SEG1+"", WS_CARD_SEG1_R);
            if (DCRCDDAT.PROD_CD.equalsIgnoreCase("CAD12000") 
                && (!WS_CARD_SEG1_R.WS_CARD_SEQ1_BR.equalsIgnoreCase("29") 
                && !WS_CARD_SEG1_R.WS_CARD_SEQ1_BR.equalsIgnoreCase("21"))) {
                WS_PROD_CD = "CAD34000";
                DCCSDAMA.CARD_BIN = "621462";
            }
        }
        if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            && DCCSDAMA.PICK_MTH == '2' 
            && (DCRCDDAT.PROD_CD.equalsIgnoreCase("CAD11000") 
            || DCRCDDAT.PROD_CD.equalsIgnoreCase("CAD11100"))) {
            if (DCCSDAMA.RMK == 999) {
                WS_PROD_CD = DCRCDDAT.PROD_CD;
                DCCSDAMA.CARD_BIN = "622568";
            }
        }
        if (DCRCDDAT.PROD_CD.equalsIgnoreCase("CAD12100") 
            && DCCSDAMA.PICK_MTH == '2' 
            && (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
            WS_PROD_CD = "CAD12100";
            DCCSDAMA.CARD_BIN = "622568";
        }
        WS_CARD_BR = DCRCDDAT.ISSU_BR;
        WS_OWNER_CINO = DCRCDDAT.CARD_OWN_CINO;
        WS_OLD_SAME_TFR_FLG = DCRCDDAT.SAME_NAME_TFR_FLG;
        WS_OLD_DIFF_TFR_FLG = DCRCDDAT.DIFF_NAME_TFR_FLG;
        WS_OLD_DRAW_OVER_FLG = DCRCDDAT.DRAW_OVER_FLG;
        WS_OLD_HOLD_AC_FLG = DCRCDDAT.HOLD_AC_FLG;
        WS_OLD_PROD_LMT_FLG = DCRCDDAT.PROD_LMT_FLG;
        WS_OLD_CARD_TYP = DCRCDDAT.CARD_TYP;
        WS_AC_TYPE = DCRCDDAT.ACNO_TYPE;
        WS_OLD_DB_FREE_FLG = DCRCDDAT.DOUBLE_FREE_FLG;
        WS_OLD_TRAN_WITH_CARD = DCRCDDAT.TRAN_WITH_CARD;
        WS_OLD_TRAN_NO_CARD = DCRCDDAT.TRAN_NO_CARD;
        WS_OLD_TRAN_CRS_BOR = DCRCDDAT.TRAN_CRS_BOR;
        WS_OLD_TRAN_ATM_FLG = DCRCDDAT.TRAN_ATM_FLG;
        WS_CARD_OWN_CINO = DCRCDDAT.CARD_OWN_CINO;
        CEP.TRC(SCCGWA, DCRCDDAT.CARD_HLDR_CINO);
        WS_CARD_HLD_CINO = DCRCDDAT.CARD_HLDR_CINO;
        WS_CINO = DCRCDDAT.CARD_HLDR_CINO;
        WS_OLD_ANNU_FEE_FREE = DCRCDDAT.ANNUAL_FEE_FREE;
        WS_OLD_ANU_FEE_FREE = DCRCDDAT.ANU_FEE_FREE;
        WS_CARD_LNK_TYP = DCRCDDAT.CARD_LNK_TYP;
        WS_OLD_PRIM_CARD_NO = DCRCDDAT.PRIM_CARD_NO;
        CEP.TRC(SCCGWA, DCRCDDAT.OPEN_CHNL);
        WS_OLD_OPEN_CHNL = DCRCDDAT.OPEN_CHNL;
        WS_OLD_CARD_STSW = DCRCDDAT.CARD_STSW;
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1")) {
            WS_OPEN_IC_FLG = 'Y';
        }
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = WS_PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, BPCPQPRD.EFF_DATE);
        CEP.TRC(SCCGWA, BPCPQPRD.STOP_SOLD_DATE);
        CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
        if (BPCPQPRD.PARM_CODE.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PQPRD_PARM_CD_SPC;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_PQPRD_PARM_CD_SPC, DCCSDAMA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.AC_DATE < BPCPQPRD.EFF_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PRDT_NOT_EFFECT;
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PRDT_NOT_EFFECT, DCCSDAMA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCRPRDPR);
        IBS.init(SCCGWA, DCCUPRCD);
        DCCUPRCD.TX_TYPE = 'I';
        DCCUPRCD.DATA.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        DCCUPRCD.DATA.VAL.PRDMO_CD = "CARD";
        DCCUPRCD.DATA.KEY.CD.PARM_CODE = BPCPQPRD.PARM_CODE;
        S000_CALL_DCZUPRCD();
        if (pgmRtn) return;
        if (DCCUPRCD.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DCCUPRCD.RC);
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUPRCD.DATA);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCRPRDPR);
        }
        WS_OLD_ADSC_TYPE = DCRPRDPR.DATA_TXT.ADSC_TYP;
        CEP.TRC(SCCGWA, DCRPRDPR.DATA_TXT.ADSC_TYP);
        CEP.TRC(SCCGWA, DCRPRDPR.DATA_TXT.PSW_FLG);
        CEP.TRC(SCCGWA, DCRPRDPR.DATA_TXT.PHY_TYP);
        if (DCCSDAMA.CHANG_TYP == '3') {
            if (DCRPRDPR.DATA_TXT.PHY_TYP != 'D') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PDNOT_VIRTUAL;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_PDNOT_VIRTUAL, DCCSDAMA.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            if (DCRPRDPR.DATA_TXT.PHY_TYP == 'D') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PDIS_VIRTUAL;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_PDIS_VIRTUAL, DCCSDAMA.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        WS_ADSC_TYPE = DCRPRDPR.DATA_TXT.ADSC_TYP;
        WS_PSW_FLG = DCRPRDPR.DATA_TXT.PSW_FLG;
        WS_CARD_PHY_TYP = DCRPRDPR.DATA_TXT.PHY_TYP;
        CEP.TRC(SCCGWA, DCRPRDPR.DATA_TXT.SR_RC_CD);
        if (DCRPRDPR.DATA_TXT.ADSC_TYP != 'P') {
            CEP.TRC(SCCGWA, WS_CARD_BR);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
            if (WS_CARD_BR == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_BR_NOT_EXIST;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_BR_NOT_EXIST, DCCSDAMA.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (WS_CARD_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CHG_C_CARD_M_OPEN_BR;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CHG_C_CARD_M_OPEN_BR, DCCSDAMA.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, DCRCDDAT.CARD_STS);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_ID.TR_CODE);
        if (SCCGWA.COMM_AREA.TR_ID.TR_CODE == 52 
            || SCCGWA.COMM_AREA.TR_ID.TR_CODE == 0052) {
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            if (!DCRCDDAT.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_LOSE_STS;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_LOSE_STS, DCCSDAMA.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DCCSDAMA.LOST_NO.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_LOST_MUST_INPUT;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_LOST_MUST_INPUT, DCCSDAMA.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                IBS.init(SCCGWA, BPCPLOSS);
                CEP.TRC(SCCGWA, DCCSDAMA.LOST_NO);
                BPCPLOSS.DATA_INFO.LOS_NO = DCCSDAMA.LOST_NO;
                CEP.TRC(SCCGWA, BPCPLOSS.DATA_INFO.LOS_NO);
                BPCPLOSS.INFO.FUNC = 'I';
                BPCPLOSS.INFO.INDEX_FLG = "1";
                S000_CALL_BPZPLOSS();
                if (pgmRtn) return;
                if ((!BPCPLOSS.DATA_INFO.AC.equalsIgnoreCase(DCCSDAMA.OLD_CARD_NO)) 
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
            }
            if (DCCSDAMA.PICK_MTH == '1') {
                IBS.init(SCCGWA, DCRINRCD);
                DCRINRCD.KEY.CARD_NO = DCRCDDAT.KEY.CARD_NO;
                DCRINRCD.KEY.CARD_SEQ = DCRCDDAT.EXC_CARD_TMS;
                B999_QUERY_BP_REC_PROC();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCPLOSS.DATA_INFO.LOS_ORG);
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
                if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != BPCPLOSS.DATA_INFO.LOS_ORG) {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_M_CHG_CD_IN_LOST_BR;
                    IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_M_CHG_CD_IN_LOST_BR, DCCSDAMA.RC);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                WS_LOST_NO = BPCPLOSS.DATA_INFO.LOS_NO;
            }
            if (DCCSDAMA.PICK_MTH == '1') {
                IBS.init(SCCGWA, DCCS0132);
                DCCS0132.FUNC_CODE = 'C';
                DCCS0132.CARD_NO = DCCSDAMA.OLD_CARD_NO;
                DCCS0132.ID_TYPE = DCCSDAMA.HOLDER_IDTYP;
                DCCS0132.ID_NO = DCCSDAMA.HOLDER_IDNO;
                DCCS0132.CI_NAME = DCCSDAMA.HOLDER_NAME;
                DCCS0132.LOST_NO = WS_LOST_NO;
                CEP.TRC(SCCGWA, DCCS0132.FUNC_CODE);
                S000_CALL_DCZSLOST();
                if (pgmRtn) return;
            }
            if (DCCSDAMA.PICK_MTH != '1') {
                if (DCRPRDPR.DATA_TXT.ADSC_TYP == 'P') {
                    if (!DCCSDAMA.HOLDER_IDTYP.equalsIgnoreCase("10100") 
                        && !DCCSDAMA.HOLDER_IDTYP.equalsIgnoreCase("10200")) {
                        B061_CHECK_CARD_LOST_DAYS();
                        if (pgmRtn) return;
                    }
                }
            }
        }
        if (SCCGWA.COMM_AREA.TR_ID.TR_CODE == 42 
            || SCCGWA.COMM_AREA.TR_ID.TR_CODE == 0042) {
            if (DCRCDDAT.CARD_STS != 'N') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_NORMAL_STS;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_NORMAL_STS, DCCSDAMA.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            if (DCRCDDAT.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ALR_CARD_LOSS_STS;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_ALR_CARD_LOSS_STS, DCCSDAMA.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(0, 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_ALREADY_LOSE;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_ALREADY_LOSE, DCCSDAMA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
            || DCRCDDAT.CARD_STSW.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
            WS_PSW_LOST_FLG = 'Y';
            if (DCCSDAMA.CARD_PASSWD.trim().length() > 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PSW_LOST_NOT_INPUT;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_PSW_LOST_NOT_INPUT, DCCSDAMA.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ALR_CARD_SWALLOW_STS;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_ALR_CARD_SWALLOW_STS, DCCSDAMA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
            if ((SCCGWA.COMM_AREA.TR_ID.TR_CODE == 52 
                || SCCGWA.COMM_AREA.TR_ID.TR_CODE == 0052) 
                && DCRCDDAT.CARD_STS != 'N') {
                WS_ACTIVATION_FLG = 'N';
                if (DCCSDAMA.CARD_PASSWD.trim().length() > 0) {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NOT_ATT_NOT_INP_PSW;
                    IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_NOT_ATT_NOT_INP_PSW, DCCSDAMA.RC);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            } else {
                if (WS_PSW_FLG == 'Y') {
                    if (DCCSDAMA.CARD_PASSWD.trim().length() == 0 
                        && (WS_PSW_LOST_FLG != 'Y') 
                        && (WS_ACTIVATION_FLG != 'N')) {
                        WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PSW_MUST_INPUT;
                        IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_PSW_MUST_INPUT, DCCSDAMA.RC);
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                } else if (WS_PSW_FLG == 'N') {
                    if (DCCSDAMA.CARD_PASSWD.trim().length() > 0) {
                        WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_PSW;
                        IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_PSW, DCCSDAMA.RC);
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
        }
        if (DCRCDDAT.CARD_HLDR_CINO.trim().length() > 0) {
            IBS.init(SCCGWA, CICCUST);
            CICCUST.DATA.CI_NO = DCRCDDAT.CARD_HLDR_CINO;
            CICCUST.FUNC = 'C';
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            if (DCCSDAMA.HOLDER_IDTYP.equalsIgnoreCase(CICCUST.O_DATA.O_ID_TYPE) 
                && DCCSDAMA.HOLDER_IDNO.equalsIgnoreCase(CICCUST.O_DATA.O_ID_NO) 
                && DCCSDAMA.HOLDER_NAME.equalsIgnoreCase(CICCUST.O_DATA.O_CI_NM)) {
            } else {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_CIF_NOMATCH;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_CIF_NOMATCH, DCCSDAMA.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCCSDAMA.PICK_MTH == '2' 
            || DCCSDAMA.PICK_MTH == '3') {
            B021_CHECK_CARD_ARREARAGE();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCRODDAT);
        IBS.CLONE(SCCGWA, DCRCDDAT, DCRODDAT);
        if (WS_CARD_LNK_TYP != '1' 
            && WS_OLD_PRIM_CARD_NO.trim().length() > 0) {
            IBS.init(SCCGWA, DCRCDDAT);
            DCRCDDAT.KEY.CARD_NO = WS_OLD_PRIM_CARD_NO;
            T000_READ_DCTCDDAT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DCRCDDAT.PROD_CD);
            CEP.TRC(SCCGWA, DCCSDAMA.N_CARD_PR_CD);
            if (!DCRCDDAT.PROD_CD.equalsIgnoreCase(DCCSDAMA.N_CARD_PR_CD)) {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_PROD_CD_NOT_MATCH);
            }
        }
    }
    public void B021_CHECK_CARD_ARREARAGE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPFPDT);
        CEP.TRC(SCCGWA, DCCSDAMA.OLD_CARD_NO);
        BPCPFPDT.INPUT.CARD_PSBK_NO = DCCSDAMA.OLD_CARD_NO;
        S000_CALL_BPZPFPDT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPFPDT.OUTPUT.MAIN_FLG);
        if (BPCPFPDT.OUTPUT.MAIN_FLG == 'Y') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PAY_ARREARAGE;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_PAY_ARREARAGE, DCCSDAMA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_SELF_CARDNO_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRBINPM);
        DCRBINPM.KEY.BIN = DCCSDAMA.CARD_BIN;
        T000_READ_DCTBINPM();
        if (pgmRtn) return;
        R000_CHECK_CARDNO();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DCRPDBIN);
        DCRPDBIN.KEY.CARD_PROD_CD = DCCSDAMA.N_CARD_PR_CD;
        T000_READ_DCTPDBIN_FIRST();
        if (pgmRtn) return;
        if (!DCRPDBIN.KEY.CARD_BIN.equalsIgnoreCase(DCCSDAMA.CARD_BIN)) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_PROD_CNT_EXCHAN);
        }
        WS_CEID_EXP_DT = DCRBINPM.CEID_EXP;
        IBS.init(SCCGWA, DCRCDDAT);
        IBS.CLONE(SCCGWA, DCRODDAT, DCRCDDAT);
        WS_CARDNO = DCRCDDAT.KEY.CARD_NO;
        WS_CARD_SEQNO = DCRCDDAT.EXC_CARD_TMS;
        WS_CINO = DCRODDAT.CARD_HLDR_CINO;
        if (DCRCDDAT.EXC_CARD_TMS == 9) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_SEQNO_NOTOK;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_SEQNO_NOTOK, DCCSDAMA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_CARD_SEQNO = (short) (WS_CARD_SEQNO + 1);
        T000_READUPD_DCTCDDAT();
        if (pgmRtn) return;
        DCRCDORD.KEY.EXC_CARD_TMS = WS_CARD_SEQNO;
        DCRCDDAT.EXC_CARD_TMS = WS_CARD_SEQNO;
        DCRCDDAT.CARD_CLS_PROD = DCCSDAMA.CARD_CLS_CD;
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        DCRCDDAT.CARD_STSW = DCRCDDAT.CARD_STSW.substring(0, 2 - 1) + "0" + DCRCDDAT.CARD_STSW.substring(2 + 1 - 1);
        DCRCDDAT.CARD_STS = '0';
        DCRCDDAT.BV_CD_NO = WS_NEW_BV_CD;
        DCRCDDAT.PROD_CD = DCCSDAMA.N_CARD_PR_CD;
        if (DCCSDAMA.CHANG_TYP == '3') {
            DCRCDDAT.CARD_MEDI = WS_CARD_MEDI;
        }
        if (WS_CARD_PHY_TYP == 'D') {
            DCRCDDAT.CARD_STS = '2';
        }
        DCRCDDAT.MOVE_FLG = 'N';
        DCRCDDAT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        DCRCDDAT.CARD_STSW = DCRCDDAT.CARD_STSW.substring(0, 29 - 1) + "1" + DCRCDDAT.CARD_STSW.substring(29 + 1 - 1);
        IBS.init(SCCGWA, DCCUPSWM);
        DCCUPSWM.FUNC = 'R';
        DCCUPSWM.OLD_AGR_NO = DCCSDAMA.OLD_CARD_NO;
        DCCUPSWM.AGR_NO = WS_CARDNO;
        if (DCCSDAMA.OLD_CARD_NO == null) DCCSDAMA.OLD_CARD_NO = "";
        JIBS_tmp_int = DCCSDAMA.OLD_CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCCSDAMA.OLD_CARD_NO += " ";
        DCCUPSWM.AGR_NO_6 = DCCSDAMA.OLD_CARD_NO.substring(14 - 1, 14 + 6 - 1);
        DCCUPSWM.PSW_TYP = 'T';
        DCCUPSWM.CARD_PSW_NEW = DCCSDAMA.CARD_PASSWD;
        DCCUPSWM.ID_TYP = DCCSDAMA.HOLDER_IDTYP;
        DCCUPSWM.ID_NO = DCCSDAMA.HOLDER_IDNO;
        DCCUPSWM.CI_NM = DCCSDAMA.HOLDER_NAME;
        S000_CALL_DCZUPSWM();
        if (pgmRtn) return;
        WS_OLD_CARD_PSW = DCCUPSWM.O_NEW_PSW;
        DCRCDDAT.TRAN_PIN_DAT = WS_OLD_CARD_PSW;
        T000_UPDATE_DCTCDDAT();
        if (pgmRtn) return;
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.init(SCCGWA, DCRINRCD);
            WS_CARD_SEQNO1 = DCRCDDAT.EXC_CARD_TMS - 1;
            DCRINRCD.KEY.CARD_NO = DCRCDDAT.KEY.CARD_NO;
            DCRINRCD.KEY.CARD_SEQ = (short) WS_CARD_SEQNO1;
            CEP.TRC(SCCGWA, WS_CARD_SEQNO1);
            CEP.TRC(SCCGWA, DCRCDDAT.CARD_STSW);
            T000_READ_DCTINRCD_F();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                DCRINRCD.KEY.CARD_SEQ = DCRCDDAT.EXC_CARD_TMS;
                DCRINRCD.KEY.TR_JRNNO = SCCGWA.COMM_AREA.JRN_NO;
                T000_WRITE_DCTINRCD();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_CHECK_CARDNO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSDAMA.NEW_CARD_NO);
        CEP.TRC(SCCGWA, DCCSDAMA.OLD_CARD_NO);
        CEP.TRC(SCCGWA, DCRBINPM.CARD_LEN);
        for (WS_I = 1; WS_I <= DCRBINPM.CARD_LEN; WS_I += 1) {
            if (DCCSDAMA.OLD_CARD_NO == null) DCCSDAMA.OLD_CARD_NO = "";
            JIBS_tmp_int = DCCSDAMA.OLD_CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) DCCSDAMA.OLD_CARD_NO += " ";
            if (DCCSDAMA.OLD_CARD_NO.substring(WS_I - 1, WS_I + 1 - 1).equalsIgnoreCase("4")) {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_BADNO_INCLUDE);
            }
        }
    }
    public void B024_GET_PTOD_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = DCCSDAMA.N_CARD_PR_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, BPCPQPRD.EFF_DATE);
        CEP.TRC(SCCGWA, BPCPQPRD.EXP_DATE);
        CEP.TRC(SCCGWA, BPCPQPRD.STOP_SOLD_DATE);
        if (SCCGWA.COMM_AREA.AC_DATE > BPCPQPRD.EXP_DATE) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PROD_EXPIRED;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_PROD_EXPIRED, DCCSDAMA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCCDQPRD);
        CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
        if (BPCPQPRD.PARM_CODE.trim().length() > 0) {
            DCCDQPRD.VAL.KEY.PROD_CD = BPCPQPRD.PARM_CODE;
        } else {
            DCCDQPRD.VAL.KEY.PROD_CD = DCCSDAMA.N_CARD_PR_CD;
        }
        CEP.TRC(SCCGWA, DCCDQPRD.VAL.KEY.PROD_CD);
        S000_CALL_DCZPQPRD();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DCRPRDPR);
        IBS.init(SCCGWA, DCCUPRCD);
        DCCUPRCD.TX_TYPE = 'I';
        DCCUPRCD.DATA.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        DCCUPRCD.DATA.VAL.PRDMO_CD = "CARD";
        if (BPCPQPRD.PARM_CODE.trim().length() > 0) {
            DCCUPRCD.DATA.KEY.CD.PARM_CODE = BPCPQPRD.PARM_CODE;
        } else {
            DCCUPRCD.DATA.KEY.CD.PARM_CODE = DCCSDAMA.N_CARD_PR_CD;
        }
        S000_CALL_DCZUPRCD();
        if (pgmRtn) return;
        if (DCCUPRCD.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DCCUPRCD.RC);
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUPRCD.DATA);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCRPRDPR);
        }
        CEP.TRC(SCCGWA, DCRPRDPR.DATA_TXT.DD_PROD);
        CEP.TRC(SCCGWA, DCRPRDPR.DATA_TXT.FACE_FLG);
        WS_APP_TYP = DCRPRDPR.DATA_TXT.APP_TYP;
        WS_NEW_ADSC_TYPE = DCRPRDPR.DATA_TXT.ADSC_TYP;
        WS_IC_PROD_CD = DCRPRDPR.DATA_TXT.IC_PROD;
        CEP.TRC(SCCGWA, WS_OLD_ADSC_TYPE);
        CEP.TRC(SCCGWA, WS_NEW_ADSC_TYPE);
        if (WS_OLD_ADSC_TYPE != WS_NEW_ADSC_TYPE) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_TYPE_NOTMATCH;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_TYPE_NOTMATCH, DCCSDAMA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRPRDPR.DATA_TXT.SR_RC_CD.equalsIgnoreCase("220")) {
            WS_CARD_MEDI = '2';
        }
        if (DCRPRDPR.DATA_TXT.SR_RC_CD.equalsIgnoreCase("120")) {
            WS_CARD_MEDI = '1';
        }
        if (DCCSDAMA.GM_FLG == '3') {
            WS_CARD_MEDI = '4';
        }
    }
    public void B031_NEW_CARDNO_GEN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUGECD);
        DCCUGECD.CARD_PROD = DCCSDAMA.N_CARD_PR_CD;
        DCCUGECD.CARD_CLS_CD = DCCSDAMA.CARD_CLS_CD;
        DCCUGECD.CARD_SEQNO = DCCSDAMA.CARD_SEQ;
        S000_CALL_DCZUGECD();
        if (pgmRtn) return;
        WS_CARDNO = DCCUGECD.CARD_NO;
        DCCSDAMA.CARD_SEQ = DCCUGECD.CARD_SEQNO;
        WS_CEID_EXP_DT = DCCUGECD.CEID_EXP_DT;
    }
    public void B030_CARDNO_GEN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_PROD_CD);
        if (DCCSDAMA.PICK_MTH == '1') {
            WS_CARDNO = DCRCDDAT.KEY.CARD_NO;
            WS_CARD_SEQNO = DCRCDDAT.EXC_CARD_TMS;
            IBS.init(SCCGWA, DCRCDORD);
            DCRCDORD.KEY.CARD_NO = WS_CARDNO;
            DCRCDORD.KEY.EXC_CARD_TMS = WS_CARD_SEQNO;
            T000_READ_DCTCDORD();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                CEP.TRC(SCCGWA, "KIA TESTING1");
            } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.TRC(SCCGWA, "KIA TESTING");
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NOT_PREMARK_CARD;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_NOT_PREMARK_CARD, DCCSDAMA.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                IBS.init(SCCGWA, SCCEXCP);
                SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDORD";
                SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
                SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
                B_DB_EXCP();
                if (pgmRtn) return;
                Z_RET();
                if (pgmRtn) return;
            }
            WS_CINO = DCRCDDAT.CARD_HLDR_CINO;
            if (DCRCDORD.CRT_STS != '3' 
                && (SCCGWA.COMM_AREA.TR_ID.TR_CODE == 42 
                || SCCGWA.COMM_AREA.TR_ID.TR_CODE == 0042)) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PEV_CD_NOT_RECEIPTED;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_PEV_CD_NOT_RECEIPTED, DCCSDAMA.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DCRCDORD.CRT_STS != '3') {
                T000_READUP_DCTCDORD();
                if (pgmRtn) return;
                if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NOT_PREMARK_CARD;
                    IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_NOT_PREMARK_CARD, DCCSDAMA.RC);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                    IBS.init(SCCGWA, SCCEXCP);
                    SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDORD";
                    SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
                    SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
                    B_DB_EXCP();
                    if (pgmRtn) return;
                    Z_RET();
                    if (pgmRtn) return;
                }
                DCRCDORD.CRT_STS = '3';
                DCRCDORD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DCRCDORD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                T000_REWRITE_DCTCDORD();
                if (pgmRtn) return;
            }
            if (DCRCDDAT.EXC_CARD_TMS == 9) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_SEQNO_NOTOK;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_SEQNO_NOTOK, DCCSDAMA.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_CARD_SEQNO = (short) (WS_CARD_SEQNO + 1);
            T000_READUPD_DCTCDDAT();
            if (pgmRtn) return;
            DCRCDORD.KEY.EXC_CARD_TMS = WS_CARD_SEQNO;
            DCRCDDAT.EXC_CARD_TMS = WS_CARD_SEQNO;
            DCRCDDAT.CARD_CLS_PROD = DCCSDAMA.CARD_CLS_CD;
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            DCRCDDAT.CARD_STSW = DCRCDDAT.CARD_STSW.substring(0, 2 - 1) + "0" + DCRCDDAT.CARD_STSW.substring(2 + 1 - 1);
            DCRCDDAT.CARD_STS = '0';
            if (WS_CARD_PHY_TYP == 'D') {
                DCRCDDAT.CARD_STS = '2';
            }
            DCRCDDAT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRCDDAT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_UPDATE_DCTCDDAT();
            if (pgmRtn) return;
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            if (DCRCDDAT.CARD_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                IBS.init(SCCGWA, DCRINRCD);
                WS_CARD_SEQNO1 = DCRCDDAT.EXC_CARD_TMS - 1;
                DCRINRCD.KEY.CARD_NO = DCRCDDAT.KEY.CARD_NO;
                DCRINRCD.KEY.CARD_SEQ = (short) WS_CARD_SEQNO1;
                CEP.TRC(SCCGWA, WS_CARD_SEQNO1);
                CEP.TRC(SCCGWA, DCRCDDAT.CARD_STSW);
                T000_READ_DCTINRCD_F();
                if (pgmRtn) return;
                if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                    DCRINRCD.KEY.CARD_SEQ = DCRCDDAT.EXC_CARD_TMS;
                    DCRINRCD.KEY.TR_JRNNO = SCCGWA.COMM_AREA.JRN_NO;
                    T000_WRITE_DCTINRCD();
                    if (pgmRtn) return;
                }
            }
        }
        if (DCCSDAMA.PICK_MTH == '2' 
            || DCCSDAMA.PICK_MTH == '3') {
            IBS.init(SCCGWA, DCRCDORD);
            DCRCDORD.KEY.CARD_NO = DCCSDAMA.OLD_CARD_NO;
            DCRCDORD.KEY.EXC_CARD_TMS = DCRCDDAT.EXC_CARD_TMS;
            T000_READ_DCTCDORD();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                CEP.TRC(SCCGWA, "KIA TESTING1");
            } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.TRC(SCCGWA, "KIA TESTING");
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NOT_PREMARK_CARD;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_NOT_PREMARK_CARD, DCCSDAMA.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                IBS.init(SCCGWA, SCCEXCP);
                SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDORD";
                SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
                SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
                B_DB_EXCP();
                if (pgmRtn) return;
                Z_RET();
                if (pgmRtn) return;
            }
            WS_EMBOSS_NAME = DCCSDAMA.EMBOSS_NAME;
            WS_CINO = DCRCDDAT.CARD_HLDR_CINO;
            if (DCCSDAMA.PICK_MTH == '2') {
                IBS.init(SCCGWA, DCRPDBIN);
                WS_NEW_PROD_CD = DCCSDAMA.N_CARD_PR_CD;
                WS_N_PROD_CD = DCCSDAMA.N_CARD_PR_CD;
                T000_READ_DCTPDBIN_FR();
                if (pgmRtn) return;
                DCCSDAMA.CARD_BIN = DCRPDBIN.KEY.CARD_BIN;
                IBS.init(SCCGWA, DCRBRARC);
                DCRBRARC.KEY.BR_NO = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                T000_READ_DCTBRARC();
                if (pgmRtn) return;
                DCCSDAMA.CARD_SEG1 = DCRBRARC.AREA_NO;
                IBS.init(SCCGWA, DCCSGSEQ);
                DCCSGSEQ.INP_DATA.CARD_PROD_CD = WS_NEW_PROD_CD;
                if (DCRPDBIN.CARD_SEG1_RUL.equalsIgnoreCase("00")) {
                    DCCSGSEQ.INP_DATA.CARD_SEG1 = DCRPDBIN.KEY.CARD_SEG1;
                } else {
                    DCCSGSEQ.INP_DATA.CARD_SEG1 = DCRBRARC.AREA_NO;
                }
                DCCSGSEQ.INP_DATA.CARD_BIN = DCCSDAMA.CARD_BIN;
                DCCSGSEQ.INP_DATA.APP_NUM = 1;
                S000_CALL_DCZSGSEQ();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, DCCSGSEQ.OUT_DATA[1-1].CUR_SEQ);
                WS_CARD_SEQ = DCCSGSEQ.OUT_DATA[1-1].CUR_SEQ;
            } else {
                WS_AP_SEQ = DCCSDAMA.CARD_SEQ;
                WS_CARD_SEQ = DCCSDAMA.CARD_SEQ;
                IBS.init(SCCGWA, DCRPDBIN);
                WS_NEW_PROD_CD = DCCSDAMA.N_CARD_PR_CD;
                WS_N_PROD_CD = DCCSDAMA.N_CARD_PR_CD;
                T000_READ_DCTPDBIN_FR();
                if (pgmRtn) return;
                DCCSDAMA.CARD_BIN = DCRPDBIN.KEY.CARD_BIN;
                IBS.init(SCCGWA, DCRBRARC);
                DCRBRARC.KEY.BR_NO = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                T000_READ_DCTBRARC();
                if (pgmRtn) return;
                if (DCRPDBIN.CARD_SEG1_RUL.equalsIgnoreCase("00")) {
                    DCCSDAMA.CARD_SEG1 = DCRPDBIN.KEY.CARD_SEG1;
                } else {
                    DCCSDAMA.CARD_SEG1 = DCRBRARC.AREA_NO;
                }
                IBS.init(SCCGWA, DCRPDBIN);
                DCRPDBIN.KEY.CARD_PROD_CD = WS_N_PROD_CD;
                DCRPDBIN.KEY.CARD_BIN = DCCSDAMA.CARD_BIN;
                DCRPDBIN.KEY.CARD_SEG1 = DCCSDAMA.CARD_SEG1;
                CEP.TRC(SCCGWA, DCRPDBIN.KEY.CARD_PROD_CD);
                CEP.TRC(SCCGWA, DCRPDBIN.KEY.CARD_BIN);
                CEP.TRC(SCCGWA, DCRPDBIN.KEY.CARD_SEG1);
                T000_GROUP_DCTPDBIN();
                if (pgmRtn) return;
                if (WS_CNT_T <= 0) {
                    CEP.TRC(SCCGWA, "SEQNO NOT PLAN ");
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_SEQNO_NO_PLAN;
                    IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_SEQNO_NO_PLAN, DCCSDAMA.RC);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                IBS.init(SCCGWA, DCRDCSEQ);
                DCRDCSEQ.KEY.CARD_PROD_CD = WS_N_PROD_CD;
                DCRDCSEQ.KEY.CARD_BIN = DCCSDAMA.CARD_BIN;
                DCRDCSEQ.KEY.CARD_SEG1 = DCCSDAMA.CARD_SEG1;
                T000_READ_DCTDCSEQ();
                if (pgmRtn) return;
                if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
                    T000_STARTBR_DCTPDBIN_FIRST();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, DCRPDBIN.S_SEQ);
                    CEP.TRC(SCCGWA, DCCSDAMA.CARD_SEQ);
                    if (DCCSDAMA.CARD_SEQ == DCRPDBIN.S_SEQ) {
                        DCRPDBIN.S_SEQ = DCRPDBIN.S_SEQ + 1;
                    }
                    IBS.init(SCCGWA, DCRDCSEQ);
                    DCRDCSEQ.KEY.CARD_PROD_CD = DCRPDBIN.KEY.CARD_PROD_CD;
                    DCRDCSEQ.KEY.CARD_BIN = DCRPDBIN.KEY.CARD_BIN;
                    DCRDCSEQ.KEY.CARD_SEG1 = DCRPDBIN.KEY.CARD_SEG1;
                    DCRDCSEQ.CUR_SEQ = DCRPDBIN.S_SEQ;
                    DCRDCSEQ.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    DCRDCSEQ.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
                    DCRDCSEQ.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    DCRDCSEQ.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                    T000_WRITE_DCTDCSEQ();
                    if (pgmRtn) return;
                } else {
                    if (DCRDCSEQ.CUR_SEQ == DCCSDAMA.CARD_SEQ) {
                        CEP.TRC(SCCGWA, "GET NEXT USEFULL SEQ");
                        WS_AP_SEQ = DCRDCSEQ.CUR_SEQ;
                        WS_AP_SEQ = WS_AP_SEQ + 1;
                        B041_AUTO_CHECK_CUR_SEQ();
                        if (pgmRtn) return;
                        IBS.init(SCCGWA, DCRDCSEQ);
                        CEP.TRC(SCCGWA, DCRPDBIN.KEY.CARD_PROD_CD);
                        CEP.TRC(SCCGWA, DCRPDBIN.KEY.CARD_BIN);
                        CEP.TRC(SCCGWA, DCRPDBIN.KEY.CARD_SEG1);
                        DCRDCSEQ.KEY.CARD_PROD_CD = DCRPDBIN.KEY.CARD_PROD_CD;
                        DCRDCSEQ.KEY.CARD_BIN = DCRPDBIN.KEY.CARD_BIN;
                        DCRDCSEQ.KEY.CARD_SEG1 = DCRPDBIN.KEY.CARD_SEG1;
                        T000_READUPD_DCTDCSEQ();
                        if (pgmRtn) return;
                        DCRDCSEQ.CUR_SEQ = WS_AP_SEQ;
                        DCRDCSEQ.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                        DCRDCSEQ.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                        T000_UPDATE_DCTDCSEQ();
                        if (pgmRtn) return;
                    }
                }
                WS_CARD_SEQ = DCCSDAMA.CARD_SEQ;
            }
            CEP.TRC(SCCGWA, "123456");
            CEP.TRC(SCCGWA, WS_CARD_SEQNO);
            CEP.TRC(SCCGWA, WS_CARD_SEQ);
            IBS.init(SCCGWA, DCCFCDGG);
            DCCFCDGG.VAL.FUNC = 'G';
            if (DCCFCDGG.VAL.CARD_NO == null) DCCFCDGG.VAL.CARD_NO = "";
            JIBS_tmp_int = DCCFCDGG.VAL.CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) DCCFCDGG.VAL.CARD_NO += " ";
            if (DCCSDAMA.CARD_BIN == null) DCCSDAMA.CARD_BIN = "";
            JIBS_tmp_int = DCCSDAMA.CARD_BIN.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) DCCSDAMA.CARD_BIN += " ";
            DCCFCDGG.VAL.CARD_NO = DCCSDAMA.CARD_BIN + DCCFCDGG.VAL.CARD_NO.substring(6);
            if (DCRCDDAT.KEY.CARD_NO == null) DCRCDDAT.KEY.CARD_NO = "";
            JIBS_tmp_int = DCRCDDAT.KEY.CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) DCRCDDAT.KEY.CARD_NO += " ";
            CEP.TRC(SCCGWA, DCRCDDAT.KEY.CARD_NO.substring(0, 6));
            if (DCCFCDGG.VAL.CARD_NO == null) DCCFCDGG.VAL.CARD_NO = "";
            JIBS_tmp_int = DCCFCDGG.VAL.CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) DCCFCDGG.VAL.CARD_NO += " ";
            CEP.TRC(SCCGWA, DCCFCDGG.VAL.CARD_NO.substring(0, 6));
            IBS.init(SCCGWA, DCRBINPM);
            DCRBINPM.KEY.BIN = DCCSDAMA.CARD_BIN;
            T000_READ_DCTBINPM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DCRBINPM.SEG_NUM);
            if (DCRBINPM.SEG_NUM == 1) {
                CEP.TRC(SCCGWA, "KIA = SEG1");
                WS_SEQ_LEN = DCRBINPM.CARD_LEN - 6 - 1;
                WS_START = 15 - WS_SEQ_LEN + 1;
                WS_SEQNO = WS_CARD_SEQ;
                JIBS_tmp_str[0] = "" + WS_SEQNO;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                if (DCCFCDGG.VAL.CARD_NO == null) DCCFCDGG.VAL.CARD_NO = "";
                JIBS_tmp_int = DCCFCDGG.VAL.CARD_NO.length();
                for (int i=0;i<19-JIBS_tmp_int;i++) DCCFCDGG.VAL.CARD_NO += " ";
                DCCFCDGG.VAL.CARD_NO = DCCFCDGG.VAL.CARD_NO.substring(0, 7 - 1) + JIBS_tmp_str[0].substring(WS_START - 1, WS_START + WS_SEQ_LEN - 1) + DCCFCDGG.VAL.CARD_NO.substring(7 + WS_SEQ_LEN - 1);
            } else {
                CEP.TRC(SCCGWA, "KIA = SEG2");
                WS_SEG1_LEN = DCRBINPM.SEG1_LEN;
                WS_SEQ_LEN = DCRBINPM.CARD_LEN - 6 - DCRBINPM.SEG1_LEN - 1;
                WS_START = 15 - DCRBINPM.SEG1_LEN + 1;
                CEP.TRC(SCCGWA, DCCSDAMA.CARD_SEG1);
                JIBS_tmp_str[0] = "" + DCCSDAMA.CARD_SEG1;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                if (DCCFCDGG.VAL.CARD_NO == null) DCCFCDGG.VAL.CARD_NO = "";
                JIBS_tmp_int = DCCFCDGG.VAL.CARD_NO.length();
                for (int i=0;i<19-JIBS_tmp_int;i++) DCCFCDGG.VAL.CARD_NO += " ";
                DCCFCDGG.VAL.CARD_NO = DCCFCDGG.VAL.CARD_NO.substring(0, 7 - 1) + JIBS_tmp_str[0].substring(WS_START - 1, WS_START + DCRBINPM.SEG1_LEN - 1) + DCCFCDGG.VAL.CARD_NO.substring(7 + DCRBINPM.SEG1_LEN - 1);
                CEP.TRC(SCCGWA, DCRBINPM.SEG1_LEN);
                CEP.TRC(SCCGWA, DCCFCDGG.VAL.CARD_NO);
                WS_START = 15 - WS_SEQ_LEN + 1;
                WS_SEQNO = WS_CARD_SEQ;
                WS_PTR = 6 + DCRBINPM.SEG1_LEN + 1;
                CEP.TRC(SCCGWA, WS_START);
                CEP.TRC(SCCGWA, WS_SEQ_LEN);
                JIBS_tmp_str[0] = "" + WS_SEQNO;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                if (DCCFCDGG.VAL.CARD_NO == null) DCCFCDGG.VAL.CARD_NO = "";
                JIBS_tmp_int = DCCFCDGG.VAL.CARD_NO.length();
                for (int i=0;i<19-JIBS_tmp_int;i++) DCCFCDGG.VAL.CARD_NO += " ";
                DCCFCDGG.VAL.CARD_NO = DCCFCDGG.VAL.CARD_NO.substring(0, WS_PTR - 1) + JIBS_tmp_str[0].substring(WS_START - 1, WS_START + WS_SEQ_LEN - 1) + DCCFCDGG.VAL.CARD_NO.substring(WS_PTR + WS_SEQ_LEN - 1);
            }
            CEP.TRC(SCCGWA, "SC6001 4");
            CEP.TRC(SCCGWA, DCCFCDGG.VAL.CARD_NO);
            S000_CALL_DCZFCDGG();
            if (pgmRtn) return;
            WS_CARDNO = DCCFCDGG.VAL.CARD_NO_GEN;
            WS_CARD_SEQNO = 1;
            IBS.init(SCCGWA, DCRCDORD);
            DCRCDORD.KEY.CARD_NO = WS_CARDNO;
            DCRCDORD.KEY.EXC_CARD_TMS = 1;
            T000_READ_DCTCDORD();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_USED_CARD;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_USED_CARD, DCCSDAMA.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B040_CARD_ORDER_FILE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDORD);
        DCRCDORD.KEY.CARD_NO = WS_CARDNO;
        DCRCDORD.KEY.EXC_CARD_TMS = WS_CARD_SEQNO;
        if (DCCSDAMA.PICK_MTH == '2' 
            || DCCSDAMA.PICK_MTH == '3') {
            DCRCDORD.KEY.EXC_CARD_TMS = 0;
        }
        CEP.TRC(SCCGWA, DCRCDORD.KEY.CARD_NO);
        CEP.TRC(SCCGWA, DCRCDORD.KEY.EXC_CARD_TMS);
        DCRCDORD.APP_BAT_NO = " ";
        DCRCDORD.CARD_PROD = DCCSDAMA.N_CARD_PR_CD;
        DCRCDORD.CARD_CLS_CD = DCCSDAMA.CARD_CLS_CD;
        DCRCDORD.BV_CD_NO = WS_NEW_BV_CD;
        CEP.TRC(SCCGWA, WS_CINO);
        DCRCDORD.EMBOSS_NAME = DCCSDAMA.EMBOSS_NAME;
        DCRCDORD.EMBS_TYP = '2';
        DCRCDORD.CRT_STS = '0';
        DCRCDORD.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDORD.SF_FLG = '3';
        DCRCDORD.APP_TYP = WS_APP_TYP;
        DCRCDORD.CERT_EXP_DATE = WS_CEID_EXP_DT;
        DCRCDORD.APP_TELLER = SCCGWA.COMM_AREA.TL_ID;
        DCRCDORD.APP_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDORD.APP_TELLER = SCCGWA.COMM_AREA.TL_ID;
        DCRCDORD.APP_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DCRCDORD.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDORD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDORD.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRCDORD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        if (DCCSDAMA.GM_FLG == '3') {
            DCRCDORD.CRT_STS = '3';
        }
        T000_ADD_DCTCDORD();
        if (pgmRtn) return;
    }
    public void B070_GET_CARD_AC_NO() throws IOException,SQLException,Exception {
    }
    public void B050_FEE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        JIBS_NumStr = "" + 0;
        BPCFFTXI.TX_DATA.AUH_FLG = JIBS_NumStr.charAt(0);
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '1';
        BPCFFTXI.TX_DATA.CARD_PSBK_NO = WS_CARDNO;
        BPCFFTXI.TX_DATA.CCY_TYPE = '1';
        BPCFFTXI.TX_DATA.SVR_CD = SCCGWA.COMM_AREA.SERV_CODE;
        BPCFFTXI.TX_DATA.CI_NO = WS_OWNER_CINO;
        S000_CALL_BPZFFTXI();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCTCALF);
        BPCTCALF.INPUT_AREA.FUNC_CODE = 'C';
        BPCTCALF.INPUT_AREA.TX_CNT = 1;
        BPCTCALF.INPUT_AREA.TX_CI = WS_OWNER_CINO;
        CEP.TRC(SCCGWA, DCRPRDPR.DATA_TXT.SR_RC_CD);
        CEP.TRC(SCCGWA, WS_N_PROD_CD);
        WS_FEE_PROD = DCCSDAMA.N_CARD_PR_CD;
        BPCTCALF.INPUT_AREA.PROD_CODE = DCCSDAMA.N_CARD_PR_CD;
        BPCTCALF.INPUT_AREA.PROD_CODE1 = DCCSDAMA.N_CARD_PR_CD;
        if ((WS_FEE_PROD.equalsIgnoreCase("CAD21000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD11000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD58000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD50000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD11100") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD12000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD22000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD41000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD51000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD55000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD10000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD28000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD34000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD16000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD16100") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD53000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD37000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD38000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD52000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD35000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD56000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD42000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD39000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD00000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD43000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD54000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD12100") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD66000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD20000") 
            || WS_FEE_PROD.equalsIgnoreCase("1203020301"))) {
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
        }
        BPCTCALF.INPUT_AREA.TX_AC = WS_CARDNO;
        S000_CALL_BPZFCALF();
        if (pgmRtn) return;
    }
    public void B050_CHANGE_CARD_ATTACHMENT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUPSWM);
        DCCUPSWM.FUNC = 'R';
        DCCUPSWM.OLD_AGR_NO = DCCSDAMA.OLD_CARD_NO;
        DCCUPSWM.AGR_NO = WS_CARDNO;
        if (DCCSDAMA.OLD_CARD_NO == null) DCCSDAMA.OLD_CARD_NO = "";
        JIBS_tmp_int = DCCSDAMA.OLD_CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCCSDAMA.OLD_CARD_NO += " ";
        DCCUPSWM.AGR_NO_6 = DCCSDAMA.OLD_CARD_NO.substring(14 - 1, 14 + 6 - 1);
        DCCUPSWM.PSW_TYP = 'T';
        DCCUPSWM.CARD_PSW_NEW = DCCSDAMA.CARD_PASSWD;
        DCCUPSWM.ID_TYP = DCCSDAMA.HOLDER_IDTYP;
        DCCUPSWM.ID_NO = DCCSDAMA.HOLDER_IDNO;
        DCCUPSWM.CI_NM = DCCSDAMA.HOLDER_NAME;
        S000_CALL_DCZUPSWM();
        if (pgmRtn) return;
        WS_OLD_CARD_PSW = DCCUPSWM.O_NEW_PSW;
        IBS.init(SCCGWA, DCCUCSET);
        DCCUCSET.CARD_PROD_CD = DCCSDAMA.N_CARD_PR_CD;
        DCCUCSET.CARD_NO = WS_CARDNO;
        CEP.TRC(SCCGWA, WS_CINO);
        DCCUCSET.HOLDER_CINO = WS_CINO;
        CEP.TRC(SCCGWA, DCCUCSET.HOLDER_CINO);
        DCCUCSET.OWNER_CINO = WS_OWNER_CINO;
        DCCUCSET.CARD_TYPE = '0';
        DCCUCSET.CARD_PSW = WS_OLD_CARD_PSW;
        DCCUCSET.SNAME_TRAN_FLG = WS_OLD_SAME_TFR_FLG;
        DCCUCSET.DNAME_TRAN_FLG = WS_OLD_DIFF_TFR_FLG;
        DCCUCSET.OUT_DRAW_FLG = WS_OLD_DRAW_OVER_FLG;
        DCCUCSET.LNK_TYP = '1';
        DCCUCSET.PROD_LMT_FLG = WS_OLD_PROD_LMT_FLG;
        DCCUCSET.CARD_STS = '0';
        DCCUCSET.HOLD_AC_FLG = WS_OLD_HOLD_AC_FLG;
        DCCUCSET.CARD_TYP = WS_OLD_CARD_TYP;
        DCCUCSET.CARD_CLS_PROD = DCCSDAMA.CARD_CLS_CD;
        DCCUCSET.BV_CD_NO = WS_NEW_BV_CD;
        DCCUCSET.SF_FLG = '3';
        DCCUCSET.CERT_EXP_DATE = WS_CEID_EXP_DT;
        DCCUCSET.AC_TYPE = WS_AC_TYPE;
        DCCUCSET.LNK_TYP = WS_CARD_LNK_TYP;
        DCCUCSET.OWNER_CINO = WS_CARD_OWN_CINO;
        DCCUCSET.PRIM_CD_NO = WS_OLD_PRIM_CARD_NO;
        if (WS_OLD_DB_FREE_FLG == ' ') {
            DCCUCSET.DB_FREE = 'Y';
        } else {
            DCCUCSET.DB_FREE = WS_OLD_DB_FREE_FLG;
        }
        if (WS_OLD_TRAN_WITH_CARD == ' ') {
            DCCUCSET.TRAN_WITH_CARD = 'Y';
        } else {
            DCCUCSET.TRAN_WITH_CARD = WS_OLD_TRAN_WITH_CARD;
        }
        if (WS_OLD_TRAN_NO_CARD == ' ') {
            DCCUCSET.TRAN_NO_CARD = 'Y';
        } else {
            DCCUCSET.TRAN_NO_CARD = WS_OLD_TRAN_NO_CARD;
        }
        if (WS_OLD_TRAN_CRS_BOR == ' ') {
            DCCUCSET.TRAN_CRS_BOR = 'Y';
        } else {
            DCCUCSET.TRAN_CRS_BOR = WS_OLD_TRAN_CRS_BOR;
        }
        if (WS_OLD_TRAN_ATM_FLG == ' ') {
            DCCUCSET.TRAN_ATM_FLG = 'Y';
        } else {
            DCCUCSET.TRAN_ATM_FLG = WS_OLD_TRAN_ATM_FLG;
        }
        DCCUCSET.OPEN_CHNL = WS_OLD_OPEN_CHNL;
        CEP.TRC(SCCGWA, "GYA000000");
        S000_CALL_DCZUCSET();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "GYA111111");
        DCRCDDAT.KEY.CARD_NO = WS_CARDNO;
        T000_READUPD_DCTCDDAT();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "KIA TESTING");
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        DCRCDDAT.ANNUAL_FEE_FREE = WS_OLD_ANNU_FEE_FREE;
        DCRCDDAT.ANU_FEE_FREE = WS_OLD_ANU_FEE_FREE;
        DCRCDDAT.EMBS_DT = DCRCDORD.CRT_DT;
        DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRCDDAT.EXC_CARD_TMS = DCRCDORD.KEY.EXC_CARD_TMS;
        if (DCCSDAMA.CHANG_TYP == '3') {
            DCRCDDAT.BV_CD_NO = WS_NEW_BV_CD;
            DCRCDDAT.PROD_CD = DCCSDAMA.N_CARD_PR_CD;
            DCRCDDAT.CARD_MEDI = WS_CARD_MEDI;
        }
        if (WS_OPEN_IC_FLG == 'Y') {
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            DCRCDDAT.CARD_STSW = DCRCDDAT.CARD_STSW.substring(0, 16 - 1) + "1" + DCRCDDAT.CARD_STSW.substring(16 + 1 - 1);
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        DCRCDDAT.CARD_STSW = DCRCDDAT.CARD_STSW.substring(0, 30 - 1) + "1" + DCRCDDAT.CARD_STSW.substring(30 + 1 - 1);
        DCRCDDAT.ACT_DT = SCCGWA.COMM_AREA.AC_DATE;
        if (DCCSDAMA.GM_FLG == '3') {
            DCRCDDAT.CARD_STS = 'N';
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            DCRCDDAT.CARD_STSW = DCRCDDAT.CARD_STSW.substring(0, 18 - 1) + "0" + DCRCDDAT.CARD_STSW.substring(18 + 1 - 1);
        }
        if (WS_OLD_CARD_STSW == null) WS_OLD_CARD_STSW = "";
        JIBS_tmp_int = WS_OLD_CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) WS_OLD_CARD_STSW += " ";
        if (WS_OLD_CARD_STSW.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            DCRCDDAT.CARD_STSW = DCRCDDAT.CARD_STSW.substring(0, 17 - 1) + "1" + DCRCDDAT.CARD_STSW.substring(17 + 1 - 1);
        }
        T000_UPDATE_DCTCDDAT();
        if (pgmRtn) return;
        B060_OLD_CARD_STATUS_SETTING();
        if (pgmRtn) return;
    }
    public void B222_PRIM_CARD_CHANGE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.PRIM_CARD_NO = DCCSDAMA.OLD_CARD_NO;
        T000_STARTBR_DCTCDDAT();
        if (pgmRtn) return;
        T000_READNEXT_DCTCDDAT();
        if (pgmRtn) return;
        while (WS_TBL_FLAG != 'N') {
            DCRCDDAT.PRIM_CARD_NO = WS_CARDNO;
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
    public void B025_GET_NEW_BV_CODE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRDCCLS);
        DCRDCCLS.KEY.CARD_PROD_CD = DCCSDAMA.N_CARD_PR_CD;
        DCRDCCLS.KEY.CARD_CLS_CD = DCCSDAMA.CARD_CLS_CD;
        T000_READ_DCTDCCLS();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'N') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.NOT_FOUND);
        }
        WS_NEW_BV_CD = DCRDCCLS.BV_CD_NO;
    }
    public void B060_OLD_CARD_STATUS_SETTING() throws IOException,SQLException,Exception {
        if (DCCSDAMA.OLD_CARD_NO.trim().length() > 0) {
            IBS.init(SCCGWA, DCRCDDAT);
            DCRCDDAT.KEY.CARD_NO = DCCSDAMA.OLD_CARD_NO;
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
    public void B081_NEW_CARD_RELN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSACR);
        CICSACR.FUNC = 'A';
        CICSACR.DATA.AGR_NO = WS_CARDNO;
        CICSACR.DATA.ENTY_TYP = '2';
        if (DCCSDAMA.CHANG_TYP == '3' 
            && DCCSDAMA.GM_FLG == '3') {
            CICSACR.DATA.ENTY_TYP = '5';
        }
        CICSACR.DATA.STSW = "11011000";
        if (WS_CARD_LNK_TYP != '1') {
            CICSACR.DATA.CNTRCT_TYP = "298";
            CICSACR.DATA.CI_NO = WS_CARD_HLD_CINO;
        } else {
            CICSACR.DATA.CNTRCT_TYP = "299";
            CICSACR.DATA.CI_NO = WS_CARD_OWN_CINO;
        }
        CICSACR.DATA.PROD_CD = DCCSDAMA.N_CARD_PR_CD;
        CICSACR.DATA.FRM_APP = "DC";
        CICSACR.DATA.SHOW_FLG = 'Y';
        CICSACR.DATA.OPN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CICSACR.DATA.OPEN_DT = SCCGWA.COMM_AREA.AC_DATE;
        S000_CALL_CIZSACR();
        if (pgmRtn) return;
    }
    public void B082_CHANGE_CARD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCHDC);
        CICCHDC.FUNC = 'C';
        CICCHDC.DATA_FOR_CHANGE.ENTY_NO_OLD = DCCSDAMA.OLD_CARD_NO;
        CICCHDC.DATA_FOR_CHANGE.ENTY_TYP_OLD = '2';
        CICCHDC.DATA_FOR_CHANGE.ENTY_NO_NEW = WS_CARDNO;
        CICCHDC.DATA_FOR_CHANGE.ENTY_TYP_NEW = '2';
        if (SCCGWA.COMM_AREA.TR_ID.TR_CODE == 0052 
            || SCCGWA.COMM_AREA.TR_ID.TR_CODE == 52) {
            CICCHDC.DATA_FOR_CHANGE.IC_AID_FLG = 'N';
        }
        S000_CALL_CIZCHDC();
        if (pgmRtn) return;
    }
    public void B083_CHANGE_ZHCP_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUMATP);
        DCCUMATP.IO_AREA.FUNC_M = '6';
        DCCUMATP.IO_AREA.AC_NO = WS_CARDNO;
        DCCUMATP.IO_AREA.AGR_NO = DCCSDAMA.OLD_CARD_NO;
        S000_CALL_DCZUMATP();
        if (pgmRtn) return;
    }
    public void B084_CARRY_BIND_RELATION() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_AC_TYPE);
        IBS.init(SCCGWA, DCCUBIND);
        DCCUBIND.IO_AREA.FUNC = 'H';
        DCCUBIND.IO_AREA.CARD_NEW = WS_CARDNO;
        DCCUBIND.IO_AREA.CARD_OLD = DCCSDAMA.OLD_CARD_NO;
        DCCUBIND.IO_AREA.AC_TYP = WS_AC_TYPE;
        S000_CALL_DCZUBIND();
        if (pgmRtn) return;
    }
    public void B085_CHANGE_CARD_LIMIT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCRDLT);
        DCRCRDLT.KEY.CARD_NO = DCCSDAMA.OLD_CARD_NO;
        T000_STARTBR_DCTCRDLT();
        if (pgmRtn) return;
        T000_READNEXT_DCTCRDLT();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            B150_MOVE_OLD_DCTCRDLT();
            if (pgmRtn) return;
            B160_INSERT_NEW_DCTCRDLT();
            if (pgmRtn) return;
            DCRCRDLT.KEY.CARD_NO = WS_CARDNO;
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
    public void B086_INHERIT_ACUM_LIMIT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRTXTOT);
        DCRTXTOT.KEY.CARD_NO = DCCSDAMA.OLD_CARD_NO;
        T000_STARTBR_DCTTXTOT();
        if (pgmRtn) return;
        T000_READNEXT_DCTTXTOT();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            B151_MOVE_OLD_DCTTXTOT();
            if (pgmRtn) return;
            B161_INSERT_NEW_DCTTXTOT();
            if (pgmRtn) return;
            DCRTXTOT.KEY.CARD_NO = WS_CARDNO;
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
    public void B087_CHANGE_APPLEPAY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRAPPLC);
        DCRAPPLC.SPAN = DCCSDAMA.OLD_CARD_NO;
        T000_STARTBR_DCTAPPLC();
        if (pgmRtn) return;
        T000_READNEXT_DCTAPPLC();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            DCRAPPLC.SPAN = WS_CARDNO;
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
    public void B151_MOVE_OLD_DCTTXTOT() throws IOException,SQLException,Exception {
        WS_OLD_CARD_TXTOT_INFO.WS_TXTOT_REGN_TYP = DCRTXTOT.KEY.REGN_TYP;
        WS_OLD_CARD_TXTOT_INFO.WS_TXTOT_CHNL_NO = DCRTXTOT.KEY.CHNL_NO;
        WS_OLD_CARD_TXTOT_INFO.WS_TXTOT_TXN_TYPE = DCRTXTOT.KEY.TXN_TYPE;
        WS_OLD_CARD_TXTOT_INFO.WS_TXTOT_LMT_CCY = DCRTXTOT.KEY.LMT_CCY;
        WS_OLD_CARD_TXTOT_INFO.WS_TXTOT_STA_DT = DCRTXTOT.STA_DT;
        WS_OLD_CARD_TXTOT_INFO.WS_TXTOT_STA_TM = DCRTXTOT.STA_TM;
        WS_OLD_CARD_TXTOT_INFO.WS_TXTOT_END_DT = DCRTXTOT.END_DT;
        WS_OLD_CARD_TXTOT_INFO.WS_TXTOT_END_TM = DCRTXTOT.END_TM;
        WS_OLD_CARD_TXTOT_INFO.WS_TXTOT_DLY_TOT_AMT = DCRTXTOT.DLY_TOT_AMT;
        WS_OLD_CARD_TXTOT_INFO.WS_TXTOT_LAST_DLY_TOT_AMT = DCRTXTOT.LAST_DLY_TOT_AMT;
        WS_OLD_CARD_TXTOT_INFO.WS_TXTOT_DLY_TOT_VOL = DCRTXTOT.DLY_TOT_VOL;
        WS_OLD_CARD_TXTOT_INFO.WS_TXTOT_LAST_DLY_TOT_VOL = DCRTXTOT.LAST_DLY_TOT_VOL;
        WS_OLD_CARD_TXTOT_INFO.WS_TXTOT_MLY_TOT_AMT = DCRTXTOT.MLY_TOT_AMT;
        WS_OLD_CARD_TXTOT_INFO.WS_TXTOT_LAST_MLY_TOT_AMT = DCRTXTOT.LAST_MLY_TOT_AMT;
        WS_OLD_CARD_TXTOT_INFO.WS_TXTOT_MLY_TOT_VOL = DCRTXTOT.MLY_TOT_VOL;
        WS_OLD_CARD_TXTOT_INFO.WS_TXTOT_LAST_MLY_TOT_VOL = DCRTXTOT.LAST_MLY_TOT_VOL;
        WS_OLD_CARD_TXTOT_INFO.WS_TXTOT_SYY_TOT_AMT = DCRTXTOT.SYY_TOT_AMT;
        WS_OLD_CARD_TXTOT_INFO.WS_TXTOT_LAST_SYY_TOT_AMT = DCRTXTOT.LAST_SYY_TOT_AMT;
        WS_OLD_CARD_TXTOT_INFO.WS_TXTOT_YLY_TOT_AMT = DCRTXTOT.YLY_TOT_AMT;
        WS_OLD_CARD_TXTOT_INFO.WS_TXTOT_SE_LMT_AMT = DCRTXTOT.SE_LMT_AMT;
        WS_OLD_CARD_TXTOT_INFO.WS_TXTOT_UPDTBL_DATE = DCRTXTOT.UPDTBL_DATE;
        WS_OLD_CARD_TXTOT_INFO.WS_TXTOT_UPDTBL_TLR = DCRTXTOT.UPDTBL_TLR;
    }
    public void B161_INSERT_NEW_DCTTXTOT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRTXTOT);
        DCRTXTOT.KEY.REGN_TYP = WS_OLD_CARD_TXTOT_INFO.WS_TXTOT_REGN_TYP;
        DCRTXTOT.KEY.CHNL_NO = WS_OLD_CARD_TXTOT_INFO.WS_TXTOT_CHNL_NO;
        DCRTXTOT.KEY.TXN_TYPE = WS_OLD_CARD_TXTOT_INFO.WS_TXTOT_TXN_TYPE;
        DCRTXTOT.KEY.LMT_CCY = WS_OLD_CARD_TXTOT_INFO.WS_TXTOT_LMT_CCY;
        DCRTXTOT.STA_DT = WS_OLD_CARD_TXTOT_INFO.WS_TXTOT_STA_DT;
        DCRTXTOT.STA_TM = WS_OLD_CARD_TXTOT_INFO.WS_TXTOT_STA_TM;
        DCRTXTOT.END_DT = WS_OLD_CARD_TXTOT_INFO.WS_TXTOT_END_DT;
        DCRTXTOT.END_TM = WS_OLD_CARD_TXTOT_INFO.WS_TXTOT_END_TM;
        DCRTXTOT.DLY_TOT_AMT = WS_OLD_CARD_TXTOT_INFO.WS_TXTOT_DLY_TOT_AMT;
        DCRTXTOT.LAST_DLY_TOT_AMT = WS_OLD_CARD_TXTOT_INFO.WS_TXTOT_LAST_DLY_TOT_AMT;
        DCRTXTOT.DLY_TOT_VOL = WS_OLD_CARD_TXTOT_INFO.WS_TXTOT_DLY_TOT_VOL;
        DCRTXTOT.LAST_DLY_TOT_VOL = WS_OLD_CARD_TXTOT_INFO.WS_TXTOT_LAST_DLY_TOT_VOL;
        DCRTXTOT.MLY_TOT_AMT = WS_OLD_CARD_TXTOT_INFO.WS_TXTOT_MLY_TOT_AMT;
        DCRTXTOT.LAST_MLY_TOT_AMT = WS_OLD_CARD_TXTOT_INFO.WS_TXTOT_LAST_MLY_TOT_AMT;
        DCRTXTOT.MLY_TOT_VOL = WS_OLD_CARD_TXTOT_INFO.WS_TXTOT_MLY_TOT_VOL;
        DCRTXTOT.LAST_MLY_TOT_VOL = WS_OLD_CARD_TXTOT_INFO.WS_TXTOT_LAST_MLY_TOT_VOL;
        DCRTXTOT.SYY_TOT_AMT = WS_OLD_CARD_TXTOT_INFO.WS_TXTOT_SYY_TOT_AMT;
        DCRTXTOT.LAST_SYY_TOT_AMT = WS_OLD_CARD_TXTOT_INFO.WS_TXTOT_LAST_SYY_TOT_AMT;
        DCRTXTOT.YLY_TOT_AMT = WS_OLD_CARD_TXTOT_INFO.WS_TXTOT_YLY_TOT_AMT;
        DCRTXTOT.SE_LMT_AMT = WS_OLD_CARD_TXTOT_INFO.WS_TXTOT_SE_LMT_AMT;
        DCRTXTOT.UPDTBL_DATE = WS_OLD_CARD_TXTOT_INFO.WS_TXTOT_UPDTBL_DATE;
        DCRTXTOT.UPDTBL_TLR = WS_OLD_CARD_TXTOT_INFO.WS_TXTOT_UPDTBL_TLR;
    }
    public void B088_CHANGE_POINT_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRDIRAC);
        DCRDIRAC.KEY.CARD_NO = DCCSDAMA.OLD_CARD_NO;
        T000_STARTBR_DCTDIRAC();
        if (pgmRtn) return;
        T000_READNEXT_DCTDIRAC();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            DCRDIRAC.KEY.CARD_NO = WS_CARDNO;
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
    public void B089_OLD_CARD_CANCEL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSOCAC);
        BPCSOCAC.FUNC = 'U';
        BPCSOCAC.OLD_AC = DCCSDAMA.OLD_CARD_NO;
        BPCSOCAC.AC = WS_CARDNO;
        BPCSOCAC.STS = 'K';
        if (WS_CARD_MEDI == '1') {
            BPCSOCAC.NEW_BV_TYP = '2';
        }
        if (WS_CARD_MEDI == '2') {
            BPCSOCAC.NEW_BV_TYP = '1';
        }
        if (WS_NEW_ADSC_TYPE == 'C') {
            BPCSOCAC.NEW_BV_TYP = '3';
        }
        BPCSOCAC.NEW_PROD_CD = DCCSDAMA.N_CARD_PR_CD;
        if (SCCGWA.COMM_AREA.TR_ID.TR_CODE == 0052 
            || SCCGWA.COMM_AREA.TR_ID.TR_CODE == 52) {
            BPCSOCAC.IC_AID_FLG = 'N';
        }
        S000_CALL_BPZSOCAC();
        if (pgmRtn) return;
    }
    public void B100_UPDATE_DDTCCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIMCYY);
        DDCIMCYY.TX_TYPE = 'U';
        DDCIMCYY.DATA.AC_OLD = DCCSDAMA.OLD_CARD_NO;
        DDCIMCYY.DATA.AC_NEW = WS_CARDNO;
        if (SCCGWA.COMM_AREA.TR_ID.TR_CODE == 0052 
            || SCCGWA.COMM_AREA.TR_ID.TR_CODE == 52) {
            DDCIMCYY.IC_FLG = 'N';
        }
        if (SCCGWA.COMM_AREA.TR_ID.TR_CODE == 0042 
            || SCCGWA.COMM_AREA.TR_ID.TR_CODE == 42) {
            DDCIMCYY.IC_FLG = 'Y';
        }
        S000_CALL_DDZIMCYY();
        if (pgmRtn) return;
    }
    public void B111_UPDATE_CCYTYP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = WS_CARDNO;
        DDRCCY.CCY = "156";
        DDRCCY.CCY_TYPE = '1';
        T000_READUPD_DDTCCY();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'Y') {
            if (DCCSDAMA.GM_FLG != '3') {
                DDRCCY.AC_TYPE = 'A';
            } else {
                if (WS_AC_TYPE == '2') {
                    DDRCCY.AC_TYPE = 'C';
                }
                if (WS_AC_TYPE == '3') {
                    DDRCCY.AC_TYPE = 'B';
                }
            }
        }
        DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_UPDATE_DDTCCY();
        if (pgmRtn) return;
    }
    public void B110_UPDATE_TDTSMST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCCHDC);
        TDCCHDC.OLD_AC = DCCSDAMA.OLD_CARD_NO;
        TDCCHDC.NEW_AC = WS_CARDNO;
        S000_CALL_TDZCHDC();
        if (pgmRtn) return;
    }
    public void B120_UPDATE_DCTINRCD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRINRCD);
        DCRINRCD.KEY.CARD_NO = DCCSDAMA.OLD_CARD_NO;
        T000_READUPD_DCTINRCD();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'Y') {
            DCRINRCD.NEW_CARD_NO = WS_CARDNO;
            DCRINRCD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRINRCD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_UPDATE_DCTINRCD();
            if (pgmRtn) return;
        }
    }
    public void B130_OPEN_IC_AC() throws IOException,SQLException,Exception {
        if (WS_OPEN_IC_FLG == 'Y' 
            && (SCCGWA.COMM_AREA.TR_ID.TR_CODE == 0052 
            || SCCGWA.COMM_AREA.TR_ID.TR_CODE == 52)) {
            IBS.init(SCCGWA, DDCUOPAC);
            DDCUOPAC.ACO_AC_TYP = '1';
            DDCUOPAC.CI_NO = WS_CARD_HLD_CINO;
            DDCUOPAC.CARD_TYP = '1';
            DDCUOPAC.PROD_CODE = WS_IC_PROD_CD;
            DDCUOPAC.AC_TYP = 'A';
            DDCUOPAC.PSBK_FLG = '2';
            DDCUOPAC.CARD_FLG = '1';
            DDCUOPAC.ACNO_FLG = 'B';
            DDCUOPAC.DRAW_MTH = ' ';
            DDCUOPAC.AC = WS_CARDNO;
            DDCUOPAC.CCY = "156";
            DDCUOPAC.CCY_TYPE = '1';
            DDCUOPAC.AC_TYPE = '2';
            S000_CALL_DDZUOPAC();
            if (pgmRtn) return;
        }
    }
    public void B200_UPDATE_DDTSTSW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRSTSW);
        IBS.init(SCCGWA, BPCPQORG);
        DDRSTSW.KEY.CUS_AC = DCCSDAMA.OLD_CARD_NO;
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
            DDRSTSW.KEY.CUS_AC = WS_CARDNO;
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
    public void B140_REGIST_AGENT_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSAGEN);
        CICSAGEN.FUNC = 'A';
        CICSAGEN.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        CICSAGEN.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        CICSAGEN.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        CICSAGEN.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CICSAGEN.TLR_NO = SCCGWA.COMM_AREA.TL_ID;
        CICSAGEN.CI_NO = WS_CARD_HLD_CINO;
        CICSAGEN.OUT_AC = WS_CARDNO;
        CICSAGEN.ID_TYP = CICGAGA_AGENT_AREA.ID_TYP;
        CICSAGEN.ID_NO = CICGAGA_AGENT_AREA.ID_NO;
        CICSAGEN.AGENT_TP = "01";
        CICSAGEN.CI_NAME = CICGAGA_AGENT_AREA.CI_NM;
        CICSAGEN.PHONE = CICGAGA_AGENT_AREA.PHONE;
        S000_CALL_CIZSAGEN();
        if (pgmRtn) return;
    }
    public void B029_CHECK_NEW_CARD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = DCCSDAMA.N_CARD_PR_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        if (BPCPQPRD.PARM_CODE.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PQPRD_PARM_CD_SPC;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_PQPRD_PARM_CD_SPC, DCCSDAMA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCCUPRCD);
        IBS.init(SCCGWA, DCRPRDPR);
        DCCUPRCD.TX_TYPE = 'I';
        DCCUPRCD.DATA.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        DCCUPRCD.DATA.VAL.PRDMO_CD = "CARD";
        DCCUPRCD.DATA.KEY.CD.PARM_CODE = BPCPQPRD.PARM_CODE;
        S000_CALL_DCZUPRCD();
        if (pgmRtn) return;
        if (DCCUPRCD.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DCCUPRCD.RC);
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUPRCD.DATA);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCRPRDPR);
        }
        WS_NEW_ADSC_TYPE = DCRPRDPR.DATA_TXT.ADSC_TYP;
        WS_IC_PROD_CD = DCRPRDPR.DATA_TXT.IC_PROD;
        if (DCRPRDPR.DATA_TXT.SR_RC_CD.equalsIgnoreCase("220")) {
            WS_CARD_MEDI = '2';
        }
        if (DCRPRDPR.DATA_TXT.SR_RC_CD.equalsIgnoreCase("120")) {
            WS_CARD_MEDI = '1';
        }
    }
    public void B112_UPDATE_EATACLNK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSDAMA.OLD_CARD_NO);
        IBS.init(SCCGWA, EARACLNK);
        EARACLNK.KEY.CARD_NO = DCCSDAMA.OLD_CARD_NO;
        T000_READUPD_EATACLNK();
        if (pgmRtn) return;
        if (EARACLNK.CON_STS == 'B') {
            IBS.CLONE(SCCGWA, EARACLNK, EARACLNO);
            CEP.TRC(SCCGWA, EARACLNK.KEY.CON_AC);
            T000_UPDATE_EATACLNK();
            if (pgmRtn) return;
            R000_ADD_EATACLNK();
            if (pgmRtn) return;
        }
    }
    public void T000_READUPD_EATACLNK() throws IOException,SQLException,Exception {
        EATACLNK_RD = new DBParm();
        EATACLNK_RD.TableName = "EATACLNK";
        EATACLNK_RD.where = "CARD_NO = :EARACLNK.KEY.CARD_NO";
        EATACLNK_RD.upd = true;
        IBS.READ(SCCGWA, EARACLNK, this, EATACLNK_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_UPDATE_EATACLNK() throws IOException,SQLException,Exception {
        EARACLNK.CON_STS = 'C';
        EARACLNK.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EARACLNK.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        EATACLNK_RD = new DBParm();
        EATACLNK_RD.TableName = "EATACLNK";
        IBS.REWRITE(SCCGWA, EARACLNK, EATACLNK_RD);
    }
    public void R000_ADD_EATACLNK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EARACLNK);
        IBS.CLONE(SCCGWA, EARACLNO, EARACLNK);
        EARACLNK.KEY.CARD_NO = WS_CARDNO;
        EARACLNK.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EARACLNK.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        EARACLNK.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EARACLNK.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_EATACLNK();
        if (pgmRtn) return;
    }
    public void T000_WRITE_EATACLNK() throws IOException,SQLException,Exception {
        EATACLNK_RD = new DBParm();
        EATACLNK_RD.TableName = "EATACLNK";
        EATACLNK_RD.errhdl = true;
        IBS.WRITE(SCCGWA, EARACLNK, EATACLNK_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DUPKEY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B080_NEW_OLD_CARD_FILE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CARDNO);
        CEP.TRC(SCCGWA, DCCSDAMA.OLD_CARD_NO);
        if (!WS_CARDNO.equalsIgnoreCase(DCCSDAMA.OLD_CARD_NO)) {
            IBS.init(SCCGWA, DCCUCHCD);
            DCCUCHCD.FUNC_CODE = 'A';
            DCCUCHCD.INPUT_DATA.CARD_NO = WS_CARDNO;
            DCCUCHCD.INPUT_DATA.OLD_CARD_NO = DCCSDAMA.OLD_CARD_NO;
            DCCUCHCD.INPUT_DATA.NEW_CARD_MEDI = '2';
            DCCUCHCD.INPUT_DATA.OLD_CARD_MEDI = WS_OLD_CARD_MEDI;
            DCCUCHCD.INPUT_DATA.NEW_CARD_ACT_FG = 'N';
            DCCUCHCD.INPUT_DATA.NEW_CARD_ACT_DT = 0;
            S000_CALL_DCZUCHCD();
            if (pgmRtn) return;
        }
    }
    public void B090_CARD_AGREEMENT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSCCAG);
        DCCSCCAG.NEW_CARD = WS_CARDNO;
        DCCSCCAG.OLD_CARD = DCCSDAMA.OLD_CARD_NO;
        S000_CALL_DCZSCCAG();
        if (pgmRtn) return;
    }
    public void B060_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT);
        WS_OUTPUT.WS_OLD_CARD_NO = DCCSDAMA.OLD_CARD_NO;
        WS_OUTPUT.WS_HOLDER_IDTYP = DCCSDAMA.HOLDER_IDTYP;
        WS_OUTPUT.WS_HOLDER_IDNO = DCCSDAMA.HOLDER_IDNO;
        WS_OUTPUT.WS_HOLDER_NAME = DCCSDAMA.HOLDER_NAME;
        WS_OUTPUT.WS_ISSUE_MTH = DCCSDAMA.ISSUE_MTH;
        WS_OUTPUT.WS_ENV_FLG = DCCSDAMA.ENV_FLG;
        WS_OUTPUT.WS_NEW_CARD_NO = WS_CARDNO;
        DCCSDAMA.NEW_CARD_NO = WS_CARDNO;
        WS_OUTPUT.WS_LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        WS_OUTPUT.WS_LAST_USER = SCCGWA.COMM_AREA.TL_ID;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 385;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B070_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCHDAMA);
        IBS.init(SCCGWA, DCCNDAMA);
        DCCNDAMA.OLD_CARD_NO = DCCSDAMA.OLD_CARD_NO;
        DCCNDAMA.HOLDER_IDTYP = DCCSDAMA.HOLDER_IDTYP;
        DCCNDAMA.HOLDER_IDNO = DCCSDAMA.HOLDER_IDNO;
        DCCNDAMA.HOLDER_NAME = DCCSDAMA.HOLDER_NAME;
        DCCNDAMA.NEW_CARD_NO = WS_CARDNO;
        DCCNDAMA.ISSUE_MTH = DCCSDAMA.ISSUE_MTH;
        DCCNDAMA.ENV_FLG = DCCSDAMA.ENV_FLG;
        DCCNDAMA.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCCNDAMA.LAST_USER = SCCGWA.COMM_AREA.TL_ID;
        DCCNDAMA.RMK = WS_RMK;
        R000_HISTORY_RECORD();
        if (pgmRtn) return;
    }
    public void B999_QUERY_BP_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPLOSS);
        BPCPLOSS.DATA_INFO.AC = DCRCDDAT.KEY.CARD_NO;
        BPCPLOSS.DATA_INFO.STS = '1';
        BPCPLOSS.INFO.FUNC = 'I';
        BPCPLOSS.INFO.INDEX_FLG = "2";
        S000_CALL_BPZPLOSS();
        if (pgmRtn) return;
    }
    public void R000_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        if (WS_RMK == 1) {
            BPCPNHIS.INFO.TX_RMK = "卡损换卡";
        } else {
            if (WS_RMK == 2) {
                BPCPNHIS.INFO.TX_RMK = "旧卡换新�?";
            } else {
                BPCPNHIS.INFO.TX_RMK = "换实名卡";
            }
        }
        CEP.TRC(SCCGWA, WS_RMK);
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_RMK);
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID = "DCCHDAMA";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_TOOL = DCCSDAMA.OLD_CARD_NO;
        BPCPNHIS.INFO.CI_NO = WS_OWNER_CINO;
        BPCPNHIS.INFO.AC = DCCSDAMA.OLD_CARD_NO;
        BPCPNHIS.INFO.FMT_ID_LEN = 418;
        if (SCCGWA.COMM_AREA.TR_ID.TR_CODE == 0043) {
            BPCPNHIS.INFO.TX_TYP_CD = "P105";
        } else {
            BPCPNHIS.INFO.TX_TYP_CD = "P120";
        }
        CEP.TRC(SCCGWA, DCCNDAMA);
        BPCPNHIS.INFO.NEW_DAT_PT = DCCNDAMA;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCNDAMA);
        CEP.TRC(SCCGWA, DCCSDAMA.PICK_MTH);
        if (DCCSDAMA.PICK_MTH != '1') {
            CEP.TRC(SCCGWA, "NEW CARD");
            CEP.TRC(SCCGWA, WS_CARDNO);
            IBS.init(SCCGWA, BPCPNHIS);
            BPCPNHIS.INFO.TX_TYP = 'A';
            if (WS_RMK == 1) {
                BPCPNHIS.INFO.TX_RMK = "卡损换卡";
            } else {
                if (WS_RMK == 2) {
                    BPCPNHIS.INFO.TX_RMK = "旧卡换新�?";
                } else {
                    BPCPNHIS.INFO.TX_RMK = "换实名卡";
                }
            }
            CEP.TRC(SCCGWA, WS_RMK);
            CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_RMK);
            BPCPNHIS.INFO.DATA_FLG = 'Y';
            BPCPNHIS.INFO.FMT_ID = "DCCHDAMA";
            BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
            BPCPNHIS.INFO.TX_TOOL = DCCSDAMA.OLD_CARD_NO;
            BPCPNHIS.INFO.CI_NO = WS_OWNER_CINO;
            BPCPNHIS.INFO.AC = WS_CARDNO;
            BPCPNHIS.INFO.FMT_ID_LEN = 418;
            if (SCCGWA.COMM_AREA.TR_ID.TR_CODE == 0043) {
                BPCPNHIS.INFO.TX_TYP_CD = "P105";
            } else {
                BPCPNHIS.INFO.TX_TYP_CD = "P120";
            }
            CEP.TRC(SCCGWA, DCCNDAMA);
            BPCPNHIS.INFO.NEW_DAT_PT = DCCNDAMA;
            S000_CALL_BPZPNHIS();
            if (pgmRtn) return;
        }
    }
    public void B090_SOCIAL_CARD() throws IOException,SQLException,Exception {
    }
    public void B041_AUTO_CHECK_CUR_SEQ() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_AP_SEQ);
        WS_CHK_END_FLG = 'N';
        for (WS_AP_SEQ = WS_AP_SEQ; WS_CHK_END_FLG != 'Y'; WS_AP_SEQ += 1) {
            CEP.TRC(SCCGWA, WS_AP_SEQ);
            B041_AUTO_TRY_GEN_CARDNO();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_CHK_CARDNO);
            IBS.init(SCCGWA, DCRCDORD);
            DCRCDORD.KEY.CARD_NO = WS_CHK_CARDNO;
            T000_GROUP_DCTCDORD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "ORD-FLG-FLG ");
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
            CEP.TRC(SCCGWA, WS_CNT_ORD);
            if (WS_CNT_ORD > 0) {
                CEP.TRC(SCCGWA, "ORD-WRT-FALSE1");
            } else {
                IBS.init(SCCGWA, DCRCDDAT);
                DCRCDDAT.KEY.CARD_NO = WS_CHK_CARDNO;
                T000_READ_DCTCDDAT_C();
                if (pgmRtn) return;
                if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
                    CEP.TRC(SCCGWA, "DAT-FLG-FLG ");
                    CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
                    WS_CHK_END_FLG = 'Y';
                    WS_AP_SEQ = WS_AP_SEQ - 1;
                }
            }
        }
    }
    public void B041_AUTO_TRY_GEN_CARDNO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCFCDGG);
        DCCFCDGG.VAL.FUNC = 'G';
        if (DCCFCDGG.VAL.CARD_NO == null) DCCFCDGG.VAL.CARD_NO = "";
        JIBS_tmp_int = DCCFCDGG.VAL.CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCCFCDGG.VAL.CARD_NO += " ";
        if (DCCSDAMA.CARD_BIN == null) DCCSDAMA.CARD_BIN = "";
        JIBS_tmp_int = DCCSDAMA.CARD_BIN.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) DCCSDAMA.CARD_BIN += " ";
        DCCFCDGG.VAL.CARD_NO = DCCSDAMA.CARD_BIN + DCCFCDGG.VAL.CARD_NO.substring(6);
        IBS.init(SCCGWA, DCRBINPM);
        CEP.TRC(SCCGWA, DCCSDAMA.CARD_BIN);
        DCRBINPM.KEY.BIN = DCCSDAMA.CARD_BIN;
        T000_READ_DCTBINPM();
        if (pgmRtn) return;
        WS_CEID_EXP_DT = DCRBINPM.CEID_EXP;
        if (DCRBINPM.SEG_NUM == 1) {
            CEP.TRC(SCCGWA, "KIA = SEG1");
            WS_SEQ_LEN = DCRBINPM.CARD_LEN - 6 - 1;
            WS_START = 15 - WS_SEQ_LEN + 1;
            WS_SEQNO = WS_AP_SEQ;
            JIBS_tmp_str[0] = "" + WS_SEQNO;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (DCCFCDGG.VAL.CARD_NO == null) DCCFCDGG.VAL.CARD_NO = "";
            JIBS_tmp_int = DCCFCDGG.VAL.CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) DCCFCDGG.VAL.CARD_NO += " ";
            DCCFCDGG.VAL.CARD_NO = DCCFCDGG.VAL.CARD_NO.substring(0, 7 - 1) + JIBS_tmp_str[0].substring(WS_START - 1, WS_START + WS_SEQ_LEN - 1) + DCCFCDGG.VAL.CARD_NO.substring(7 + WS_SEQ_LEN - 1);
        } else {
            CEP.TRC(SCCGWA, "KIA = SEG2");
            WS_SEG1_LEN = DCRBINPM.SEG1_LEN;
            WS_SEQ_LEN = DCRBINPM.CARD_LEN - 6 - DCRBINPM.SEG1_LEN - 1;
            WS_START = 15 - DCRBINPM.SEG1_LEN + 1;
            CEP.TRC(SCCGWA, DCCSDAMA.CARD_SEG1);
            CEP.TRC(SCCGWA, WS_START);
            CEP.TRC(SCCGWA, DCRBINPM.SEG1_LEN);
            JIBS_tmp_str[0] = "" + DCCSDAMA.CARD_SEG1;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (DCCFCDGG.VAL.CARD_NO == null) DCCFCDGG.VAL.CARD_NO = "";
            JIBS_tmp_int = DCCFCDGG.VAL.CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) DCCFCDGG.VAL.CARD_NO += " ";
            DCCFCDGG.VAL.CARD_NO = DCCFCDGG.VAL.CARD_NO.substring(0, 7 - 1) + JIBS_tmp_str[0].substring(WS_START - 1, WS_START + DCRBINPM.SEG1_LEN - 1) + DCCFCDGG.VAL.CARD_NO.substring(7 + DCRBINPM.SEG1_LEN - 1);
            CEP.TRC(SCCGWA, DCRBINPM.SEG1_LEN);
            CEP.TRC(SCCGWA, DCCFCDGG.VAL.CARD_NO);
            WS_START = 15 - WS_SEQ_LEN + 1;
            WS_SEQNO = WS_AP_SEQ;
            WS_PTR = 6 + DCRBINPM.SEG1_LEN + 1;
            JIBS_tmp_str[0] = "" + WS_SEQNO;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (DCCFCDGG.VAL.CARD_NO == null) DCCFCDGG.VAL.CARD_NO = "";
            JIBS_tmp_int = DCCFCDGG.VAL.CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) DCCFCDGG.VAL.CARD_NO += " ";
            DCCFCDGG.VAL.CARD_NO = DCCFCDGG.VAL.CARD_NO.substring(0, WS_PTR - 1) + JIBS_tmp_str[0].substring(WS_START - 1, WS_START + WS_SEQ_LEN - 1) + DCCFCDGG.VAL.CARD_NO.substring(WS_PTR + WS_SEQ_LEN - 1);
        }
        CEP.TRC(SCCGWA, DCCFCDGG.VAL.CARD_NO);
        S000_CALL_DCZFCDGG();
        if (pgmRtn) return;
        WS_CHK_CARDNO = DCCFCDGG.VAL.CARD_NO_GEN;
    }
    public void B099_LOSS_REGIN_PRC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSLOSS);
        CEP.TRC(SCCGWA, DCCSDAMA.LOST_NO);
        BPCSLOSS.LOS_NO = DCCSDAMA.LOST_NO;
        CEP.TRC(SCCGWA, BPCSLOSS.LOS_NO);
        BPCSLOSS.FUNC = 'U';
        BPCSLOSS.STS = '5';
        BPCSLOSS.RLOS_ORG = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCSLOSS.RLOS_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCSLOSS.RLOS_TIME = SCCGWA.COMM_AREA.TR_TIME;
        BPCSLOSS.RLOS_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSLOSS.NEW_BV_NO = WS_CARDNO;
        S000_CALL_BPZSLOSS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCSDAMA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZSSCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SEARCH-CI", CICSSCH);
        if (CICSSCH.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICSSCH.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICSSCH.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCSDAMA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DCZSGSEQ, DCCSGSEQ);
        if (DCCSGSEQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCSGSEQ.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCSGSEQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCSDAMA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFFTXI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_F_TX_INFO, BPCFFTXI);
        CEP.TRC(SCCGWA, BPCFFTXI.RC);
        if (BPCFFTXI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFTXI.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFFTXI.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCSDAMA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUGECD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-GEN-CARD-NO", DCCUGECD, true);
    }
    public void S000_CALL_BPZFCALF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_F_CAL_FEE, BPCTCALF);
        CEP.TRC(SCCGWA, BPCTCALF.RC);
        if (BPCTCALF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTCALF.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTCALF.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCSDAMA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZSACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACR ", CICSACR);
        CEP.TRC(SCCGWA, CICSACR.RC);
        if (CICSACR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICSACR.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICSACR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCSDAMA.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_CIZCHDC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CHANGE-DC ", CICCHDC);
        CEP.TRC(SCCGWA, CICCHDC.RC);
        if (CICCHDC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCHDC.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICCHDC.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCSDAMA.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_DCZUMATP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-M-AUTO-TD-PLAN", DCCUMATP);
        if (DCCUMATP.O_AREA.RC_CODE != 0) {
            WS_ERR_MSG = DCCUMATP.O_AREA.MSG_ID;
            IBS.CPY2CLS(SCCGWA, DCCUMATP.O_AREA.MSG_ID, DCCSDAMA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
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
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCSDAMA.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_CIZSAGEN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SVR-CIZSAGEN", CICSAGEN);
        if (CICSAGEN.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSAGEN.RC);
        }
    }
    public void S000_CALL_DDZIMCYY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-ZIMCYY-AC", DDCIMCYY);
        CEP.TRC(SCCGWA, DDCIMCYY.RC);
        if (DDCIMCYY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIMCYY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_TDZCHDC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-Z-CHAG-DC", TDCCHDC);
        CEP.TRC(SCCGWA, CICCHDC.RC);
        if (CICCHDC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCHDC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZUOPAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-OPEN-AC     ", DDCUOPAC);
    }
    public void S000_CALL_DCZUCHCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DCZUCHCD, DCCUCHCD);
        CEP.TRC(SCCGWA, DCCUCHCD.RC.RC_CODE);
        if (DCCUCHCD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCHCD.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUCHCD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCSDAMA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUCCHA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DCZUCCHA, DCCUCCHA);
        if (DCCUCCHA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCCHA.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUCCHA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCSDAMA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZSCCAG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-S-DCZSCCAG", DCCSCCAG);
    }
    public void S000_CALL_DCZUPSWM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DCZUPSWM, DCCUPSWM);
        if (DCCUPSWM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUPSWM.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUPSWM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCSDAMA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPFPDT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-FEE-UNCHG-CHK", BPCPFPDT);
        CEP.TRC(SCCGWA, BPCPFPDT.RC);
        if (BPCPFPDT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPFPDT.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPFPDT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCSDAMA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZFCDGG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DCZFCDGG, DCCFCDGG);
        if (DCCFCDGG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCFCDGG.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCFCDGG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCSDAMA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BPZPQPRD, BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCSDAMA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUCSET() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-SETUP", DCCUCSET);
        if (DCCUCSET.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCSET.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "KIA TESTING");
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST, DCCSDAMA.RC);
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
    public void T000_READ_DCTCDDAT_C() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
    }
    public void T000_GROUP_DCTCDORD() throws IOException,SQLException,Exception {
        null.set = "WS-CNT-ORD=COUNT(*)";
        DCTCDORD_RD = new DBParm();
        DCTCDORD_RD.TableName = "DCTCDORD";
        DCTCDORD_RD.where = "CARD_NO = :DCRCDORD.KEY.CARD_NO";
        IBS.GROUP(SCCGWA, DCRCDORD, this, DCTCDORD_RD);
    }
    public void T000_READ_DCTCDORD() throws IOException,SQLException,Exception {
        DCTCDORD_RD = new DBParm();
        DCTCDORD_RD.TableName = "DCTCDORD";
        IBS.READ(SCCGWA, DCRCDORD, DCTCDORD_RD);
    }
    public void T000_READ_DCTPDBIN_FR() throws IOException,SQLException,Exception {
        DCTPDBIN_RD = new DBParm();
        DCTPDBIN_RD.TableName = "DCTPDBIN";
        DCTPDBIN_RD.where = "CARD_PROD_CD = :WS_NEW_PROD_CD";
        DCTPDBIN_RD.fst = true;
        IBS.READ(SCCGWA, DCRPDBIN, this, DCTPDBIN_RD);
    }
    public void T000_READ_DCTBRARC() throws IOException,SQLException,Exception {
        DCTBRARC_RD = new DBParm();
        DCTBRARC_RD.TableName = "DCTBRARC";
        IBS.READ(SCCGWA, DCRBRARC, DCTBRARC_RD);
    }
    public void T000_READUP_DCTCDORD() throws IOException,SQLException,Exception {
        DCTCDORD_RD = new DBParm();
        DCTCDORD_RD.TableName = "DCTCDORD";
        DCTCDORD_RD.upd = true;
        IBS.READ(SCCGWA, DCRCDORD, DCTCDORD_RD);
    }
    public void T000_REWRITE_DCTCDORD() throws IOException,SQLException,Exception {
        DCTCDORD_RD = new DBParm();
        DCTCDORD_RD.TableName = "DCTCDORD";
        IBS.REWRITE(SCCGWA, DCRCDORD, DCTCDORD_RD);
    }
    public void T000_ADD_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.WRITE(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
    }
    public void T000_READUPD_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.upd = true;
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST, DCCSDAMA.RC);
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
    public void T000_READ_DCTDCCLS() throws IOException,SQLException,Exception {
        DCTDCCLS_RD = new DBParm();
        DCTDCCLS_RD.TableName = "DCTDCCLS";
        IBS.READ(SCCGWA, DCRDCCLS, DCTDCCLS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTDCCLS";
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
    public void T000_UPDATE_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.REWRITE(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
    }
    public void T000_ADD_DCTCDORD() throws IOException,SQLException,Exception {
        DCTCDORD_RD = new DBParm();
        DCTCDORD_RD.TableName = "DCTCDORD";
        DCTCDORD_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DCRCDORD, DCTCDORD_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "KIA");
            CEP.TRC(SCCGWA, "12345");
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDORD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTBINPM() throws IOException,SQLException,Exception {
        DCTBINPM_RD = new DBParm();
        DCTBINPM_RD.TableName = "DCTBINPM";
        IBS.READ(SCCGWA, DCRBINPM, DCTBINPM_RD);
    }
    public void T000_READ_DCTINRCD() throws IOException,SQLException,Exception {
        DCTINRCD_RD = new DBParm();
        DCTINRCD_RD.TableName = "DCTINRCD";
        DCTINRCD_RD.where = "CARD_NO = :DCRINRCD.KEY.CARD_NO "
            + "AND CARD_SEQ = :DCRINRCD.KEY.CARD_SEQ "
            + "AND INCD_TYPE = '02'";
        DCTINRCD_RD.fst = true;
        DCTINRCD_RD.order = "CRT_DATE DESC, TS DESC";
        IBS.READ(SCCGWA, DCRINRCD, this, DCTINRCD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_LOSE_STS;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_LOSE_STS, DCCSDAMA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTINRCD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTINRCD_F() throws IOException,SQLException,Exception {
        DCTINRCD_RD = new DBParm();
        DCTINRCD_RD.TableName = "DCTINRCD";
        DCTINRCD_RD.where = "CARD_NO = :DCRINRCD.KEY.CARD_NO "
            + "AND CARD_SEQ = :DCRINRCD.KEY.CARD_SEQ "
            + "AND INCD_TYPE = '04'";
        DCTINRCD_RD.fst = true;
        DCTINRCD_RD.order = "CRT_DATE DESC, CRT_TM DESC, TS DESC";
        IBS.READ(SCCGWA, DCRINRCD, this, DCTINRCD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0'
            || SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTINRCD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_GROUP_DCTPDBIN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "FIRST-PDBIN");
        CEP.TRC(SCCGWA, WS_CNT_T);
        CEP.TRC(SCCGWA, WS_AP_SEQ);
        CEP.TRC(SCCGWA, DCRPDBIN.KEY.CARD_PROD_CD);
        CEP.TRC(SCCGWA, DCRPDBIN.KEY.CARD_BIN);
        CEP.TRC(SCCGWA, DCRPDBIN.KEY.CARD_SEG1);
        DCTPDBIN_RD = new DBParm();
        DCTPDBIN_RD.TableName = "DCTPDBIN";
        DCTPDBIN_RD.set = "WS-CNT-T=COUNT(*)";
        DCTPDBIN_RD.where = "S_SEQ <= :WS_AP_SEQ "
            + "AND E_SEQ >= :WS_AP_SEQ "
            + "AND CARD_PROD_CD = :DCRPDBIN.KEY.CARD_PROD_CD "
            + "AND CARD_BIN = :DCRPDBIN.KEY.CARD_BIN";
        IBS.GROUP(SCCGWA, DCRPDBIN, this, DCTPDBIN_RD);
        CEP.TRC(SCCGWA, WS_CNT_T);
        CEP.TRC(SCCGWA, WS_AP_SEQ);
        CEP.TRC(SCCGWA, DCRPDBIN.KEY.CARD_PROD_CD);
        CEP.TRC(SCCGWA, DCRPDBIN.KEY.CARD_BIN);
        CEP.TRC(SCCGWA, DCRPDBIN.KEY.CARD_SEG1);
    }
    public void T000_READ_DCTDCSEQ() throws IOException,SQLException,Exception {
        DCTDCSEQ_RD = new DBParm();
        DCTDCSEQ_RD.TableName = "DCTDCSEQ";
        IBS.READ(SCCGWA, DCRDCSEQ, DCTDCSEQ_RD);
    }
    public void T000_STARTBR_DCTPDBIN_FIRST() throws IOException,SQLException,Exception {
        DCTPDBIN_RD = new DBParm();
        DCTPDBIN_RD.TableName = "DCTPDBIN";
        DCTPDBIN_RD.where = "CARD_PROD_CD = :DCRPDBIN.KEY.CARD_PROD_CD "
            + "AND CARD_BIN = :DCRPDBIN.KEY.CARD_BIN "
            + "AND CARD_SEG1 = :DCRPDBIN.KEY.CARD_SEG1";
        DCTPDBIN_RD.fst = true;
        DCTPDBIN_RD.order = "S_SEQ";
        IBS.READ(SCCGWA, DCRPDBIN, this, DCTPDBIN_RD);
    }
    public void T000_WRITE_DCTDCSEQ() throws IOException,SQLException,Exception {
        DCTDCSEQ_RD = new DBParm();
        DCTDCSEQ_RD.TableName = "DCTDCSEQ";
        IBS.WRITE(SCCGWA, DCRDCSEQ, DCTDCSEQ_RD);
    }
    public void T000_READUPD_DCTDCSEQ() throws IOException,SQLException,Exception {
        DCTDCSEQ_RD = new DBParm();
        DCTDCSEQ_RD.TableName = "DCTDCSEQ";
        DCTDCSEQ_RD.upd = true;
        IBS.READ(SCCGWA, DCRDCSEQ, DCTDCSEQ_RD);
    }
    public void T000_UPDATE_DCTDCSEQ() throws IOException,SQLException,Exception {
        DCTDCSEQ_RD = new DBParm();
        DCTDCSEQ_RD.TableName = "DCTDCSEQ";
        IBS.REWRITE(SCCGWA, DCRDCSEQ, DCTDCSEQ_RD);
    }
    public void T000_WRITE_DCTINRCD() throws IOException,SQLException,Exception {
        DCTINRCD_RD = new DBParm();
        DCTINRCD_RD.TableName = "DCTINRCD";
        IBS.WRITE(SCCGWA, DCRINRCD, DCTINRCD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DUPKEY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DCTCRDLT() throws IOException,SQLException,Exception {
