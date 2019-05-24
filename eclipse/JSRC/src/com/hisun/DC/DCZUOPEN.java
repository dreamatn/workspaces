package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.DD.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZUOPEN {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm DCTDCSEQ_RD;
    DBParm DCTCDORD_RD;
    DBParm DCTPDBIN_RD;
    DBParm DCTDCCLS_RD;
    DBParm DCTCDDAT_RD;
    DBParm DCTBINPM_RD;
    DBParm DCTPSWMT_RD;
    DBParm DCTCDPRT_RD;
    brParm DCTCDDAT_BR = new brParm();
    boolean pgmRtn = false;
    String K_HIS_RMK = "ORDER A SPECIAL PERSONAL CARD";
    String K_OUTPUT_FMT = "DC030";
    String K_DC_IN_CI_TYPE = "DC";
    String K_DD_IN_CI_TYPE = "DD";
    String CPN_DCZPCDCD = "DC-GEN-COD-EN       ";
    String CPN_F_F_CAL_FEE = "BP-F-F-CAL-FEE      ";
    String CPN_F_F_TX_INFO = "BP-F-F-TX-INFO      ";
    String WS_ERR_MSG = " ";
    String WS_OWNER_CINO = " ";
    int WS_CNT = 0;
    long WS_CARD_SEQNO = 0;
    String WS_CARDNO0 = " ";
    long WS_SEQNO = 0;
    int WS_SEQ_LEN = 0;
    int WS_SEG1_LEN = 0;
    int WS_START = 0;
    int WS_PTR = 0;
    char WS_FACE_FLG = ' ';
    char WS_CHK_END_FLG = ' ';
    String WS_CHK_CARDNO = " ";
    char WS_END_PDBIN_FLG = ' ';
    char WS_CHECK_LATER_FLG = ' ';
    char WS_KEEP_AP_SEQ_FLG = ' ';
    char WS_OLDCARD_FLG = 'N';
    char WS_RECORD_FLG = 'N';
    char WS_CIZACCU_FLG = 'N';
    char WS_CI_GROUP = ' ';
    String WS_AC_CI_NO = " ";
    String WS_FEE_PROD = " ";
    String WS_CUST_CI_TYP = " ";
    short WS_FLD_NO = 0;
    int WS_TOT_SUB_CARD_CNT = 0;
    int WS_MAX_SUP_CNT = 0;
    String WS_LNK_AC_CCY = " ";
    String WS_UOPEN_CARD_BIN = " ";
    long WS_UOPEN_CARD_SEG1 = 0;
    String WS_CARD_SEG1_RUL = " ";
    String WS_CDORD_BV_CD_NO = " ";
    char WS_WRT_FLG = ' ';
    char WS_CARD_FLG = ' ';
    String WS_CARD_BIN = " ";
    DCZUOPEN_WS_CARD_BIN_F WS_CARD_BIN_F = new DCZUOPEN_WS_CARD_BIN_F();
    long WS_CUR_SEQ = 0;
    int WS_APP_NUM = 0;
    char WS_JUMP_FLG = ' ';
    long WS_E_SEQ = 0;
    short WS_BIN_AFTER = 0;
    int WS_I = 0;
    int WS_COUNT = 0;
    char WS_END_FLG = ' ';
    char WS_NEW_ADSC_TYPE = ' ';
    char WS_CARD_MEDI = ' ';
    String WS_APP_TYP = " ";
    String WS_IC_PROD_CD = " ";
    int WS_CEID_EXP_DT = 0;
    String WS_TRAN_PIN_DAT = " ";
    String WS_TR_PIN_DAT = " ";
    DCZUOPEN_WS_OUTPUT WS_OUTPUT = new DCZUOPEN_WS_OUTPUT();
    int WS_CNT_T = 0;
    long WS_AP_SEQ = 0;
    int WS_CNT_DCSEQ = 0;
    int WS_CNT_ORD = 0;
    int WS_CNT_BIN = 0;
    long WS_MAX_BATCHNO = 0;
    int WS_CNT_SQL = 0;
    char WS_ENTY_TYP = ' ';
    String WS_ENTY_NO = " ";
    String WS_GRPS_NO = " ";
    int WS_L_CNT = 0;
    char WS_OPEN_CARD_FLG = ' ';
    char WS_TBL_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCRCDPRT DCRCDPRT = new DCRCDPRT();
    DCRNOCRD DCRNOCRD = new DCRNOCRD();
    DCRCDORD DCRCDORD = new DCRCDORD();
    DCRBINPM DCRBINPM = new DCRBINPM();
    DCCHSPEC DCCHSPEC = new DCCHSPEC();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPRMM BPCPRMM = new BPCPRMM();
    DCCHSPEC DCCOSPEC = new DCCHSPEC();
    DCCHSPEC DCCNSPEC = new DCCHSPEC();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    DCCUCINF DCCUCINF = new DCCUCINF();
    DCCUCSET DCCUCSET = new DCCUCSET();
    DCCSGSEQ DCCSGSEQ = new DCCSGSEQ();
    DCCUGECD DCCUGECD = new DCCUGECD();
    DCCFCDGG DCCFCDGG = new DCCFCDGG();
    DDCUOPAC DDCUOPAC = new DDCUOPAC();
    CICSACRL CICSACRL = new CICSACRL();
    DCCPQPRD DCCDQPRD = new DCCPQPRD();
    DCRPDBIN DCRPDBIN = new DCRPDBIN();
    DCRDCSEQ DCRDCSEQ = new DCRDCSEQ();
    CICACCU CICACCU = new CICACCU();
    CICCUST CICCUST = new CICCUST();
    CICSACR CICSACR = new CICSACR();
    DCRDCCLS DCRDCCLS = new DCRDCCLS();
    CICMAGT CICMAGT = new CICMAGT();
    CICUAGT CICUAGT = new CICUAGT();
    BPCPRMR BPCPRMR = new BPCPRMR();
    DCRPRDPR DCRPRDPR = new DCRPRDPR();
    DCCUPRCD DCCUPRCD = new DCCUPRCD();
    DCRACLNK DCRACLNK = new DCRACLNK();
    DCCPCDCK DCCPCDCK = new DCCPCDCK();
    DCRPSWMT DCRPSWMT = new DCRPSWMT();
    DCCPCDCD DCCPCDCD = new DCCPCDCD();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPCTCALF BPCTCALF = new BPCTCALF();
    BPCPQORG BPCPQORG = new BPCPQORG();
    DCCUSPAC DCCUSPAC = new DCCUSPAC();
    DCRBRARC DCRBRARC = new DCRBRARC();
    CIRGRPM CIRGRPM = new CIRGRPM();
    BPRPDCD BPRPDCD = new BPRPDCD();
    BPCSOCAC BPCSOCAC = new BPCSOCAC();
    CICSGRS CICSGRS = new CICSGRS();
    DCCS9991 DCCS9991 = new DCCS9991();
    CICCKOC CICCKOC = new CICCKOC();
    SCCTSSC SCCTSSC = new SCCTSSC();
    CICSAGEN CICSAGEN = new CICSAGEN();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCUOPEN DCCUOPEN;
    BPCPQBNK_DATA_INFO BPCRBANK;
    CICGAGA_AGENT_AREA CICGAGA_AGENT_AREA;
    public void MP(SCCGWA SCCGWA, DCCUOPEN DCCUOPEN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCUOPEN = DCCUOPEN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZUOPEN return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        CICGAGA_AGENT_AREA = new CICGAGA_AGENT_AREA_AGENT_AREA();
        IBS.init(SCCGWA, CICGAGA_AGENT_AREA);
        IBS.CPY2CLS(SCCGWA, SCCGWA.AGENT_AREA_PTR, CICGAGA_AGENT_AREA);
        IBS.init(SCCGWA, DCRBRARC);
        WS_OPEN_CARD_FLG = 'N';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCUOPEN.AC_NO);
        CEP.TRC(SCCGWA, DCCUOPEN.EMBOSS_NAME);
        CEP.TRC(SCCGWA, DCCUOPEN.PSW);
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B016_GET_BIN_SEG1();
        if (pgmRtn) return;
        B017_GET_BV_CD_NO();
        if (pgmRtn) return;
        if (DCCUOPEN.CARD_NO.trim().length() == 0) {
            B020_GEN_CARDNO();
            if (pgmRtn) return;
            B022_ADD_DCTDORD();
            if (pgmRtn) return;
        } else {
            B021_UPDATE_DCTCDORD();
            if (pgmRtn) return;
        }
        if (DCCUOPEN.ISSUE_MTH == '1') {
            B076_GENERATE_RANDOM_PIN();
            if (pgmRtn) return;
        }
        if (DCCUOPEN.PSW.trim().length() > 0) {
            B075_GENERATE_PIN();
            if (pgmRtn) return;
        }
        if (DCCUOPEN.TRT_CTLW == null) DCCUOPEN.TRT_CTLW = "";
        JIBS_tmp_int = DCCUOPEN.TRT_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) DCCUOPEN.TRT_CTLW += " ";
        if ((!DCCUOPEN.TRT_CTLW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1"))) {
            if (!(DCCUOPEN.CARD_LNK_TYP == '2' 
                && !(DCCUOPEN.AGENT_FLG == '1' 
                || SCCGWA.COMM_AREA.AGENT_FLG == 'Y'))) {
                B018_MAX_CARD_NUM_CHECK();
                if (pgmRtn) return;
            }
        }
        B030_CARD_CREATE();
        if (pgmRtn) return;
        B043_CARD_NOTED_CI();
        if (pgmRtn) return;
        if ((DCCUOPEN.AC_NO.trim().length() == 0) 
            && (DCCUOPEN.CARD_LNK_TYP == '1')) {
            B041_OPEN_ACCOUNT();
            if (pgmRtn) return;
        }
        if (DCCDQPRD.VAL.DATA.ADSC_TYPE == 'C' 
            || DCCUOPEN.CARD_LNK_TYP != '1') {
            B042_CARD_JOIN_AC();
            if (pgmRtn) return;
        }
        if (DCCUOPEN.AGENT_FLG == '1' 
            || SCCGWA.COMM_AREA.AGENT_FLG == 'Y') {
            B065_REGIST_AGENT_INF();
            if (pgmRtn) return;
        }
        B066_REGIST_OCAC_INF();
        if (pgmRtn) return;
        B050_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
        B060_HISTORY_PROCESS();
        if (pgmRtn) return;
        B900_OUTPUT_INF();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCUOPEN.CARD_PROD_CD);
        CEP.TRC(SCCGWA, DCCUOPEN.CARD_BIN);
        CEP.TRC(SCCGWA, DCCUOPEN.CARD_SEG1);
        CEP.TRC(SCCGWA, DCCUOPEN.CARD_SEQ);
        CEP.TRC(SCCGWA, DCCUOPEN.HOLDER_IDTYP);
        CEP.TRC(SCCGWA, DCCUOPEN.HOLDER_IDNO);
        CEP.TRC(SCCGWA, DCCUOPEN.HOLDER_CINO);
        CEP.TRC(SCCGWA, DCCUOPEN.HOLDER_NAME);
        CEP.TRC(SCCGWA, DCCUOPEN.OWNER_IDTYP);
        CEP.TRC(SCCGWA, DCCUOPEN.OWNER_IDNO);
        CEP.TRC(SCCGWA, DCCUOPEN.OWNER_CINO);
        CEP.TRC(SCCGWA, DCCUOPEN.OWNER_NAME);
        CEP.TRC(SCCGWA, DCCUOPEN.AC_NO);
        CEP.TRC(SCCGWA, DCCUOPEN.AC_TYP);
        CEP.TRC(SCCGWA, DCCUOPEN.ISSUE_MTH);
        CEP.TRC(SCCGWA, DCCUOPEN.AC_TYPE);
        if (DCCUOPEN.CARD_PROD_CD.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PROD_CODE_MISSING;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_PROD_CODE_MISSING, DCCUOPEN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B010_01_GET_PROD_INF();
        if (pgmRtn) return;
        B010_02_GET_HOLD_CI_INF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCDQPRD.VAL.DATA.ADSC_TYPE);
        if (DCCUOPEN.CARD_LNK_TYP != '1') {
            B010_03_CHECK_CARD_LNK_INF();
            if (pgmRtn) return;
        }
        if (DCCUOPEN.OWNER_CINO.trim().length() == 0) {
            if (WS_OWNER_CINO.trim().length() == 0) {
                DCCUOPEN.OWNER_CINO = DCCUOPEN.HOLDER_CINO;
                WS_OWNER_CINO = DCCUOPEN.HOLDER_CINO;
            } else {
                DCCUOPEN.OWNER_CINO = WS_OWNER_CINO;
            }
        } else {
            if (WS_OWNER_CINO.trim().length() == 0) {
                WS_OWNER_CINO = DCCUOPEN.HOLDER_CINO;
            }
            if (!DCCUOPEN.OWNER_CINO.equalsIgnoreCase(WS_OWNER_CINO)) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CINO_NOT_SAME;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CINO_NOT_SAME, DCCUOPEN.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        DCCUOPEN.OWNER_CINO = WS_OWNER_CINO;
        WS_OUTPUT.WS_CARD_PROD_CD = DCCUOPEN.CARD_PROD_CD;
        WS_FACE_FLG = DCCUOPEN.FACE_FLG;
        if (DCCUOPEN.CARD_NO.trim().length() == 0) {
            WS_CARD_FLG = '1';
        } else {
            WS_CARD_FLG = '2';
        }
        if (DCCUOPEN.AC_TYPE == ' ' 
            || (DCCUOPEN.AC_TYPE != '1' 
            && DCCUOPEN.AC_TYPE != '2' 
            && DCCUOPEN.AC_TYPE != '3')) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_AC_TYPE_ERR);
        }
    }
    public void B010_01_GET_PROD_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = DCCUOPEN.CARD_PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, BPCPQPRD.EFF_DATE);
        CEP.TRC(SCCGWA, BPCPQPRD.EXP_DATE);
        CEP.TRC(SCCGWA, BPCPQPRD.STOP_SOLD_DATE);
        if (SCCGWA.COMM_AREA.AC_DATE > BPCPQPRD.EXP_DATE) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PROD_EXPIRED;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_PROD_EXPIRED, DCCUOPEN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCCDQPRD);
        CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
        if (BPCPQPRD.PARM_CODE.trim().length() > 0) {
            DCCDQPRD.VAL.KEY.PROD_CD = BPCPQPRD.PARM_CODE;
        } else {
            DCCDQPRD.VAL.KEY.PROD_CD = DCCUOPEN.CARD_PROD_CD;
        }
        CEP.TRC(SCCGWA, DCCDQPRD.VAL.KEY.PROD_CD);
        S000_CALL_DCZPQPRD();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DCCUPRCD);
        IBS.init(SCCGWA, DCRPRDPR);
        DCCUPRCD.TX_TYPE = 'I';
        DCCUPRCD.DATA.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        DCCUPRCD.DATA.VAL.PRDMO_CD = "CARD";
        if (BPCPQPRD.PARM_CODE.trim().length() > 0) {
            DCCUPRCD.DATA.KEY.CD.PARM_CODE = BPCPQPRD.PARM_CODE;
        } else {
            DCCUPRCD.DATA.KEY.CD.PARM_CODE = DCCUOPEN.CARD_PROD_CD;
        }
        S000_CALL_DCZUPRCD();
        if (pgmRtn) return;
        if (DCCUPRCD.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DCCUPRCD.RC);
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUPRCD.DATA);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCRPRDPR);
        }
        CEP.TRC(SCCGWA, DCRPRDPR);
        CEP.TRC(SCCGWA, DCRPRDPR.DATA_TXT.DD_PROD);
        CEP.TRC(SCCGWA, DCRPRDPR.DATA_TXT.FACE_FLG);
        if (WS_FACE_FLG != ' ') {
            if (DCRPRDPR.DATA_TXT.FACE_FLG != WS_FACE_FLG) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ERR_FACE_FLG;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_ERR_FACE_FLG, DCCUOPEN.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        WS_NEW_ADSC_TYPE = DCRPRDPR.DATA_TXT.ADSC_TYP;
        WS_IC_PROD_CD = DCRPRDPR.DATA_TXT.IC_PROD;
        WS_APP_TYP = DCCDQPRD.VAL.DATA.IC_APP_TYP;
        if (DCRPRDPR.DATA_TXT.SR_RC_CD.equalsIgnoreCase("220")) {
            WS_CARD_MEDI = '2';
        }
        if (DCRPRDPR.DATA_TXT.SR_RC_CD.equalsIgnoreCase("120")) {
            WS_CARD_MEDI = '1';
        }
        CEP.TRC(SCCGWA, DCRPRDPR.DATA_TXT.PHY_TYP);
        if (DCRPRDPR.DATA_TXT.PHY_TYP == 'D') {
            WS_CARD_MEDI = '4';
        }
    }
    public void B010_02_GET_HOLD_CI_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCUOPEN.HOLDER_CINO);
        if (DCCUOPEN.HOLDER_CINO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_HOLDER;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_HOLDER, DCCUOPEN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'C';
        CICCUST.DATA.CI_NO = DCCUOPEN.HOLDER_CINO;
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
    }
    public void B010_03_CHECK_CARD_LNK_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDDAT);
        CEP.TRC(SCCGWA, DCCUOPEN.OWNER_CINO);
        CEP.TRC(SCCGWA, DCCUOPEN.PRIM_CD_NO);
        DCRCDDAT.KEY.CARD_NO = DCCUOPEN.PRIM_CD_NO;
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCUOPEN.CARD_PROD_CD);
        CEP.TRC(SCCGWA, DCRCDDAT.PROD_CD);
        if (!DCCUOPEN.CARD_PROD_CD.equalsIgnoreCase(DCRCDDAT.PROD_CD)) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PROD_CD_NOT_MATCH;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_PROD_CD_NOT_MATCH, DCCUOPEN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRCDDAT.CARD_STS != 'N') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_NORMAL_STS;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_NORMAL_STS, DCCUOPEN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(0, 1).equalsIgnoreCase("1") 
            || DCRCDDAT.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ALR_CARD_LOSS_STS;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_ALR_CARD_LOSS_STS, DCCUOPEN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ALR_CARD_SWALLOW_STS;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_ALR_CARD_SWALLOW_STS, DCCUOPEN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B011_GET_PRIM_CARD_CINO();
        if (pgmRtn) return;
        if (DCCDQPRD.VAL.DATA.PSW_FLG == 'Y') {
            if (DCCUOPEN.PRIM_CD_PSW.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PSW_MUST_INPUT;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_PSW_MUST_INPUT, DCCUOPEN.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            if (DCCUOPEN.PRIM_CD_PSW.trim().length() > 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PSW_NOT_INPUT;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_PSW_NOT_INPUT, DCCUOPEN.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        B015_CHECK_PRIM_CARD_PSW();
        if (pgmRtn) return;
        if ((DCRPRDPR.DATA_TXT.SUP_CARD != 'Y')) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NOT_ALLOW_SUP_CARD;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_NOT_ALLOW_SUP_CARD, DCCUOPEN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_TYP);
        CEP.TRC(SCCGWA, DCRPRDPR.DATA_TXT.ADSC_TYP);
        CEP.TRC(SCCGWA, DCCUOPEN.OWNER_CINO);
        CEP.TRC(SCCGWA, DCCUOPEN.HOLDER_CINO);
        CEP.TRC(SCCGWA, DCRCDDAT.CARD_OWN_CINO);
        CEP.TRC(SCCGWA, DCRCDDAT.CARD_HLDR_CINO);
        if (DCRCDDAT.CARD_HLDR_CINO.trim().length() > 0) {
            if (DCCUOPEN.HOLDER_CINO.equalsIgnoreCase(DCRCDDAT.CARD_HLDR_CINO)) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CINO_SAME_ERR;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CINO_SAME_ERR, DCCUOPEN.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, DCRPRDPR.DATA_TXT.SUP_CD_N);
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.PRIM_CARD_NO = DCCUOPEN.PRIM_CD_NO;
        T000_GROUP_DCTCDDAT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_L_CNT);
        if (WS_L_CNT >= DCRPRDPR.DATA_TXT.SUP_CD_N) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_SUP_NUM_GREAT_MAX;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B011_GET_PRIM_CARD_CINO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DCCUOPEN.PRIM_CD_NO;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        WS_OWNER_CINO = CICACCU.DATA.CI_NO;
    }
    public void B018_MAX_CARD_NUM_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCKOC);
        CICCKOC.DATA.CI_NO = DCCUOPEN.HOLDER_CINO;
        CICCKOC.DATA.OPEN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CICCKOC.DATA.PROD_CD = DCCUOPEN.CARD_PROD_CD;
        if (DCCUOPEN.AGENT_FLG == '1' 
            || SCCGWA.COMM_AREA.AGENT_FLG == 'Y') {
            CICCKOC.DATA.AGENT_FLG = 'Y';
        } else {
            CICCKOC.DATA.AGENT_FLG = 'N';
        }
        if (DCCUOPEN.AGENT_FLG == '1') {
            CICCKOC.DATA.AGENT_ID_TYP = DCCUOPEN.AGENT_IDTYP;
            CICCKOC.DATA.AGENT_ID_NO = DCCUOPEN.AGENT_IDNO;
            CICCKOC.DATA.AGENT_NAME = DCCUOPEN.AGENT_NAME;
        }
        if (SCCGWA.COMM_AREA.AGENT_FLG == 'Y') {
            CICCKOC.DATA.AGENT_ID_TYP = CICGAGA_AGENT_AREA.ID_TYP;
            CICCKOC.DATA.AGENT_ID_NO = CICGAGA_AGENT_AREA.ID_NO;
            CICCKOC.DATA.AGENT_NAME = CICGAGA_AGENT_AREA.CI_NM;
        }
        CEP.TRC(SCCGWA, CICCKOC.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICCKOC.DATA.OPEN_BR);
        CEP.TRC(SCCGWA, CICCKOC.DATA.PROD_CD);
        S000_CALL_CIZCKOC();
        if (pgmRtn) return;
    }
    public void B022_ADD_DCTDORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDORD);
        DCRCDORD.KEY.CARD_NO = WS_CARDNO0;
        DCRCDORD.KEY.EXC_CARD_TMS = 0;
        DCRCDORD.CARD_PROD = DCCUOPEN.CARD_PROD_CD;
        DCRCDORD.EMBOSS_NAME = DCCUOPEN.EMBOSS_NAME;
        CEP.TRC(SCCGWA, DCCUOPEN.CARD_CLS_PROD);
        CEP.TRC(SCCGWA, WS_CDORD_BV_CD_NO);
        DCRCDORD.CARD_CLS_CD = DCCUOPEN.CARD_CLS_PROD;
        DCRCDORD.BV_CD_NO = WS_CDORD_BV_CD_NO;
        DCRCDORD.EMBS_TYP = '2';
        if (DCRPRDPR.DATA_TXT.PHY_TYP == 'D') {
            DCRCDORD.CRT_STS = '3';
        } else {
            DCRCDORD.CRT_STS = '0';
        }
        DCRCDORD.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDORD.SF_FLG = '3';
        DCRCDORD.APP_TYP = WS_APP_TYP;
        DCRCDORD.CERT_EXP_DATE = WS_CEID_EXP_DT;
        DCRCDORD.APP_TELLER = SCCGWA.COMM_AREA.TL_ID;
        DCRCDORD.APP_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDORD.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDORD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDORD.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRCDORD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        DCRCDORD.APP_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, DCRCDORD);
        CEP.TRC(SCCGWA, DCRCDORD.KEY.CARD_NO);
        CEP.TRC(SCCGWA, DCRCDORD.KEY.EXC_CARD_TMS);
        CEP.TRC(SCCGWA, DCRCDORD.CARD_PROD);
        CEP.TRC(SCCGWA, DCRCDORD.EMBS_TYP);
        CEP.TRC(SCCGWA, DCRCDORD.CRT_STS);
        CEP.TRC(SCCGWA, DCRCDORD.APP_TELLER);
        CEP.TRC(SCCGWA, DCRCDORD.APP_DT);
        CEP.TRC(SCCGWA, DCRCDORD.APP_TELLER);
        CEP.TRC(SCCGWA, DCRCDORD.APP_BR);
        CEP.TRC(SCCGWA, DCRPRDPR.DATA_TXT.PHY_TYP);
        T000_ADD_DCTCDORD();
        if (pgmRtn) return;
    }
    public void B021_UPDATE_DCTCDORD() throws IOException,SQLException,Exception {
    }
    public void B015_CHECK_PRIM_CARD_PSW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCPCDCK);
        DCCPCDCK.CARD_NO = DCCUOPEN.PRIM_CD_NO;
        if (DCCUOPEN.PRIM_CD_PSW.trim().length() == 0) {
            DCCPCDCK.FUNC_CODE = 'N';
            S000_CALL_DCZPCDCK();
            if (pgmRtn) return;
        } else {
            DCCPCDCK.FUNC_CODE = 'P';
            DCCPCDCK.CARD_PSW = DCCUOPEN.PRIM_CD_PSW;
            S000_CALL_DCZPCDCK();
            if (pgmRtn) return;
        }
    }
    public void B016_GET_BIN_SEG1() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCUOPEN.CARD_PROD_CD);
        IBS.init(SCCGWA, DCRPDBIN);
        DCRPDBIN.KEY.CARD_PROD_CD = DCCUOPEN.CARD_PROD_CD;
        T000_READ_DCTPDBIN_FIRST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCRPDBIN.KEY.CARD_BIN);
        CEP.TRC(SCCGWA, DCRPDBIN.KEY.CARD_SEG1);
        CEP.TRC(SCCGWA, DCRPDBIN.CARD_SEG1_RUL);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_UOPEN_CARD_BIN = DCRPDBIN.KEY.CARD_BIN;
            WS_CARD_BIN = DCRPDBIN.KEY.CARD_BIN;
            IBS.CPY2CLS(SCCGWA, WS_CARD_BIN, WS_CARD_BIN_F);
            WS_UOPEN_CARD_SEG1 = DCRPDBIN.KEY.CARD_SEG1;
            WS_CARD_SEG1_RUL = DCRPDBIN.CARD_SEG1_RUL;
            DCCUOPEN.CARD_BIN = DCRPDBIN.KEY.CARD_BIN;
            DCCUOPEN.CARD_SEG1 = DCRBRARC.AREA_NO;
        }
    }
    public void B017_GET_BV_CD_NO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCDQPRD.VAL.KEY.PROD_CD);
        CEP.TRC(SCCGWA, DCCUOPEN.CARD_CLS_PROD);
        IBS.init(SCCGWA, DCRDCCLS);
        DCRDCCLS.KEY.CARD_PROD_CD = DCCUOPEN.CARD_PROD_CD;
        DCRDCCLS.KEY.CARD_CLS_CD = DCCUOPEN.CARD_CLS_PROD;
        if (DCCUOPEN.CARD_CLS_PROD.trim().length() > 0) {
            T000_READ_DCTDCCLS_FIRST();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                WS_CDORD_BV_CD_NO = DCRDCCLS.BV_CD_NO;
            }
        }
    }
    public void B020_GEN_CARDNO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUGECD);
        DCCUGECD.CARD_PROD = DCCUOPEN.CARD_PROD_CD;
        DCCUGECD.CARD_CLS_CD = DCCUOPEN.CARD_CLS_PROD;
        DCCUGECD.CARD_SEQNO = DCCUOPEN.CARD_SEQ;
        S000_CALL_DCZUGECD();
        if (pgmRtn) return;
        WS_CARDNO0 = DCCUGECD.CARD_NO;
        WS_CEID_EXP_DT = DCCUGECD.CEID_EXP_DT;
    }
    public void B021_GEN_CARDNO_NOSEQ() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_AP_SEQ);
        IBS.init(SCCGWA, DCRDCSEQ);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_CARD_PROD_CD);
        CEP.TRC(SCCGWA, WS_UOPEN_CARD_BIN);
        CEP.TRC(SCCGWA, WS_UOPEN_CARD_SEG1);
        DCRDCSEQ.KEY.CARD_PROD_CD = WS_OUTPUT.WS_CARD_PROD_CD;
        DCRDCSEQ.KEY.CARD_BIN = WS_UOPEN_CARD_BIN;
        DCRDCSEQ.KEY.CARD_SEG1 = WS_UOPEN_CARD_SEG1;
        T000_READUPD_DCTDCSEQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CFX 001");
        CEP.TRC(SCCGWA, DCRDCSEQ.CUR_SEQ);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "DDTDCSEQ NOT FOUND");
            WS_CUR_SEQ = 1;
            WS_APP_NUM = 1;
            B050_DDTCDORD_PROC();
            if (pgmRtn) return;
            DCRDCSEQ.CUR_SEQ = WS_CUR_SEQ;
            DCRDCSEQ.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRDCSEQ.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            DCRDCSEQ.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRDCSEQ.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_ADD_DCTDCSEQ();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "DDTDCSEQ FOUND");
            WS_CUR_SEQ = DCRDCSEQ.CUR_SEQ;
            WS_APP_NUM = 1;
            B050_DDTCDORD_PROC();
            if (pgmRtn) return;
            DCRDCSEQ.CUR_SEQ = WS_CUR_SEQ;
            DCRDCSEQ.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRDCSEQ.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_UPDATE_DCTDCSEQ();
            if (pgmRtn) return;
        }
    }
    public void B022_GEN_CARDNO_SEQ() throws IOException,SQLException,Exception {
        WS_AP_SEQ = DCCUOPEN.CARD_SEQ;
        IBS.init(SCCGWA, DCRPDBIN);
        DCRPDBIN.KEY.CARD_PROD_CD = DCCUOPEN.CARD_PROD_CD;
        DCRPDBIN.KEY.CARD_BIN = WS_UOPEN_CARD_BIN;
        DCRPDBIN.KEY.CARD_SEG1 = WS_UOPEN_CARD_SEG1;
        CEP.TRC(SCCGWA, DCRPDBIN.KEY.CARD_PROD_CD);
        CEP.TRC(SCCGWA, DCRPDBIN.KEY.CARD_BIN);
        CEP.TRC(SCCGWA, DCRPDBIN.KEY.CARD_SEG1);
        T000_GROUP_DCTPDBIN();
        if (pgmRtn) return;
        if (WS_CNT_T <= 0) {
            CEP.TRC(SCCGWA, "SEQNO NOT PLAN ");
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_SEQNO_NO_PLAN;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_SEQNO_NO_PLAN, DCCUOPEN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_CARD_SEQNO = DCCUOPEN.CARD_SEQ;
        IBS.init(SCCGWA, DCRDCSEQ);
        DCRDCSEQ.KEY.CARD_PROD_CD = DCCUOPEN.CARD_PROD_CD;
        DCRDCSEQ.KEY.CARD_BIN = WS_UOPEN_CARD_BIN;
        DCRDCSEQ.KEY.CARD_SEG1 = WS_UOPEN_CARD_SEG1;
        T000_READUPD_DCTDCSEQ();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
        } else {
            if (WS_CARD_SEQNO < DCRDCSEQ.CUR_SEQ) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_SEQ_NO_OVERLAP;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        B041_AUTO_TRY_GEN_CARDNO();
        if (pgmRtn) return;
        B051_JUMP_4();
        if (pgmRtn) return;
        if (WS_JUMP_FLG == 'N') {
            B023_CHECK_CARD_EXIT();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "INCLUDE 4");
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NO_CARD_PROD_PLAN;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B040_CARD_ORDERFILE();
        if (pgmRtn) return;
    }
    public void B050_DDTCDORD_PROC() throws IOException,SQLException,Exception {
        WS_COUNT = WS_APP_NUM;
        WS_AP_SEQ = WS_CUR_SEQ;
        CEP.TRC(SCCGWA, WS_COUNT);
        while (WS_COUNT >= 1) {
            CEP.TRC(SCCGWA, WS_COUNT);
            WS_END_FLG = 'N';
            WS_WRT_FLG = 'N';
            if (WS_AP_SEQ > WS_E_SEQ) {
                IBS.init(SCCGWA, DCRPDBIN);
                DCRPDBIN.KEY.CARD_PROD_CD = WS_OUTPUT.WS_CARD_PROD_CD;
                DCRPDBIN.KEY.CARD_BIN = WS_UOPEN_CARD_BIN;
                DCRPDBIN.KEY.CARD_SEG1 = WS_UOPEN_CARD_SEG1;
                CEP.TRC(SCCGWA, DCRPDBIN.KEY.CARD_PROD_CD);
                CEP.TRC(SCCGWA, DCRPDBIN.KEY.CARD_BIN);
                CEP.TRC(SCCGWA, DCRPDBIN.KEY.CARD_SEG1);
                T000_READ_DCTPDBIN_1();
                if (pgmRtn) return;
                if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                    IBS.init(SCCGWA, DCRPDBIN);
                    DCRPDBIN.KEY.CARD_PROD_CD = WS_OUTPUT.WS_CARD_PROD_CD;
                    DCRPDBIN.KEY.CARD_BIN = WS_CARD_BIN;
                    DCRPDBIN.KEY.CARD_SEG1 = WS_UOPEN_CARD_SEG1;
                    T000_READ_DCTPDBIN_2();
                    if (pgmRtn) return;
                    if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                        CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NUM_NOT_ENOUGH);
                    } else {
                        WS_AP_SEQ = DCRPDBIN.S_SEQ;
                        WS_E_SEQ = DCRPDBIN.E_SEQ;
                    }
                } else {
                    WS_E_SEQ = DCRPDBIN.E_SEQ;
                }
            }
            B041_AUTO_TRY_GEN_CARDNO();
            if (pgmRtn) return;
            B051_JUMP_4();
            if (pgmRtn) return;
            if (WS_JUMP_FLG == 'N') {
                CEP.TRC(SCCGWA, "CFX222 WS-JUMP-FLG-NO ");
                B023_CHECK_CARD_EXIT();
                if (pgmRtn) return;
            }
            if (WS_WRT_FLG == 'Y') {
                CEP.TRC(SCCGWA, "CFX222 WS-WRT-TRUE ");
                B040_CARD_ORDERFILE();
                if (pgmRtn) return;
                WS_COUNT = WS_COUNT - 1;
            }
            WS_AP_SEQ = WS_AP_SEQ + 1;
        }
        if (WS_COUNT != 0) {
            CEP.TRC(SCCGWA, "NOT-MORE-USEFULL-SEQ3");
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NOT_ENOUGH_SEQNO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_CUR_SEQ = WS_AP_SEQ;
    }
    IBS.init(SCCGWA, DCRCDORD);
    DCRCDORD.KEY.CARD_NO = WS_CARDNO0;
    DCRCDORD.KEY.EXC_CARD_TMS = 0;
    T000_READ_DCTCDORD();
    if (pgmRtn) return;
    if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        if (DCCUOPEN.CARD_SEQ == 0) {
            WS_WRT_FLG = 'N';
        } else {
            CEP.TRC(SCCGWA, "DCTDORD EXIT");
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NOT_ENOUGH_SEQNO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    } else {
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = WS_CARDNO0;
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            if (DCCUOPEN.CARD_SEQ == 0) {
                WS_WRT_FLG = 'N';
            } else {
                CEP.TRC(SCCGWA, "DCTDORD EXIT");
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NOT_ENOUGH_SEQNO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
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
    public void B026_GEN_PSW_RTN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCPCDCD);
        IBS.init(SCCGWA, BPCPQORG);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        DCCPCDCD.INP_DATA.CARD_BRH = BPCPQORG.BRANCH_BR;
        CEP.TRC(SCCGWA, BPCPQORG.BRANCH_BR);
        CEP.TRC(SCCGWA, DCCDQPRD.VAL.DATA.ADSC_TYPE);
        if (DCCDQPRD.VAL.DATA.ADSC_TYPE == 'P') {
            DCCPCDCD.INP_DATA.CARD_TYPE = "P";
        } else {
            DCCPCDCD.INP_DATA.CARD_TYPE = "C";
        }
        CEP.TRC(SCCGWA, WS_CARDNO0);
        DCCPCDCD.INP_DATA.CARD_NO = WS_CARDNO0;
        S000_CALL_DCZPCDCD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCPCDCD.OUTP_DATA.SECRET_KEY);
        CEP.TRC(SCCGWA, DCCPCDCD.OUTP_DATA.PRINT_PSW);
        CEP.TRC(SCCGWA, DCCPCDCD.OUTP_DATA.DEL_PSW);
    }
    public void B027_WRITE_DCTPSWMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRPSWMT);
        WS_MAX_BATCHNO = 0;
        DCRPSWMT.KEY.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCRPSWMT.KEY.CARD_NO = WS_OUTPUT.WS_CARDNO;
        T000_GROUP_DCTPSWMT();
        if (pgmRtn) return;
        WS_MAX_BATCHNO = WS_MAX_BATCHNO + 1;
        IBS.init(SCCGWA, DCRPSWMT);
        DCRPSWMT.KEY.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCRPSWMT.KEY.CARD_NO = WS_CARDNO0;
        DCRPSWMT.PROD_CD = DCCUOPEN.CARD_PROD_CD;
        DCRPSWMT.CRT_TM = SCCGWA.COMM_AREA.TR_TIME;
        DCRPSWMT.APP_BAT_NO = WS_MAX_BATCHNO;
        DCRPSWMT.CRT_SEQ = 1;
        DCRPSWMT.APP_BR_C = BPCPQORG.BRANCH_BR;
        DCRPSWMT.PROD_NAME = BPCPQPRD.PRDT_NAME;
        DCRPSWMT.APP_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCRPSWMT.PSW_ENCRIPT = DCCPCDCD.OUTP_DATA.PRINT_PSW;
        DCRPSWMT.SER_KEY = DCCPCDCD.OUTP_DATA.SECRET_KEY;
        DCRPSWMT.CI_NAME = DCCUOPEN.HOLDER_NAME;
        DCRPSWMT.EMBOSS_NAME = DCCUOPEN.EMBOSS_NAME;
        DCRPSWMT.PRINT_FLG = 'N';
        DCRPSWMT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRPSWMT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRPSWMT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRPSWMT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_ADD_DCTPSWMT();
        if (pgmRtn) return;
    }
    public void B030_CARD_CREATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUCSET);
        DCCUCSET.CARD_PROD_CD = DCCUOPEN.CARD_PROD_CD;
        DCCUCSET.CARD_NO = WS_CARDNO0;
        DCCUCSET.PRIM_CD_NO = DCCUOPEN.PRIM_CD_NO;
        DCCUCSET.HOLDER_CINO = DCCUOPEN.HOLDER_CINO;
        DCCUCSET.OWNER_CINO = DCCUOPEN.OWNER_CINO;
        DCCUCSET.CARD_PSW = WS_TRAN_PIN_DAT;
        if (DCCUOPEN.ISSUE_MTH == '1') {
            DCCUCSET.CARD_PSW = WS_TR_PIN_DAT;
        }
        CEP.TRC(SCCGWA, DCCUOPEN.CARD_NO);
        CEP.TRC(SCCGWA, DCRPRDPR.DATA_TXT.PHY_TYP);
        CEP.TRC(SCCGWA, WS_CARDNO0);
        if (DCCUOPEN.CARD_NO.trim().length() == 0) {
            if (DCRPRDPR.DATA_TXT.PHY_TYP == 'D') {
                if (WS_FACE_FLG == 'Y') {
                    DCCUCSET.CARD_STS = 'N';
                } else {
                    DCCUCSET.CARD_STS = '2';
                }
            } else {
                DCCUCSET.CARD_STS = '0';
            }
        } else {
            DCCUCSET.CARD_STS = 'N';
        }
        DCCUCSET.MEDIA = WS_CARD_MEDI;
        DCCUCSET.LNK_TYP = DCCUOPEN.CARD_LNK_TYP;
        DCCUCSET.PROD_LMT_FLG = DCCUOPEN.PROD_LMT_FLG;
        DCCUCSET.HOLD_AC_FLG = DCCUOPEN.HOLD_AC_FLG;
        DCCUCSET.SNAME_TRAN_FLG = DCCUOPEN.SNAME_TRAN_FLG;
        DCCUCSET.DNAME_TRAN_FLG = DCCUOPEN.DNAME_TRAN_FLG;
        DCCUCSET.OUT_DRAW_FLG = DCCUOPEN.OUT_DRAW_FLG;
        DCCUCSET.CARD_TYP = "ZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROSZEROS";
        if (DCCUOPEN.TRT_CTLW == null) DCCUOPEN.TRT_CTLW = "";
        JIBS_tmp_int = DCCUOPEN.TRT_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) DCCUOPEN.TRT_CTLW += " ";
        if (DCCUOPEN.TRT_CTLW.substring(0, 1).equalsIgnoreCase("1")) {
            IBS.init(SCCGWA, DCRCDDAT);
            DCRCDDAT.CARD_HLDR_CINO = DCCUOPEN.HOLDER_CINO;
            T000_STARTBR_DCTCDDAT_1();
            if (pgmRtn) return;
            T000_READ_NEXT_DCTCDDAT();
            if (pgmRtn) return;
            while (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
                JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
                if (DCRCDDAT.CARD_TYP.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("1")) {
                    CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_GZ_CARD_EXIT_ERROR);
                }
                T000_READ_NEXT_DCTCDDAT();
                if (pgmRtn) return;
            }
            T000_ENDBR_DCTCDDAT();
            if (pgmRtn) return;
            if (DCCUCSET.CARD_TYP == null) DCCUCSET.CARD_TYP = "";
            JIBS_tmp_int = DCCUCSET.CARD_TYP.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) DCCUCSET.CARD_TYP += " ";
            DCCUCSET.CARD_TYP = DCCUCSET.CARD_TYP.substring(0, 6 - 1) + "1" + DCCUCSET.CARD_TYP.substring(6 + 1 - 1);
        }
        DCCUCSET.CARD_CLS_PROD = DCCUOPEN.CARD_CLS_PROD;
        DCCUCSET.BV_CD_NO = WS_CDORD_BV_CD_NO;
        DCCUCSET.AC_TYPE = DCCUOPEN.AC_TYPE;
        DCCUCSET.CUS_MGR = DCCUOPEN.CUS_MGR;
        DCCUCSET.REG_CENT = DCCUOPEN.REG_CENT;
        DCCUCSET.SUB_BIZ = DCCUOPEN.SUB_BIZ;
        DCCUCSET.CERT_EXP_DATE = WS_CEID_EXP_DT;
        CEP.TRC(SCCGWA, WS_CARD_MEDI);
        CEP.TRC(SCCGWA, DCCUCSET.MEDIA);
        if (DCCUOPEN.DB_FREE == ' ') {
            DCCUCSET.DB_FREE = 'Y';
        } else {
            DCCUCSET.DB_FREE = DCCUOPEN.DB_FREE;
        }
        DCCUCSET.SF_FLG = '3';
        DCCUCSET.OWN_BR = DCCUOPEN.OWN_BR;
        S000_CALL_DCZUCSET();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "=====HAHHA====");
    }
    public void B040_CARD_ORDERFILE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDORD);
        DCRCDORD.KEY.CARD_NO = WS_CARDNO0;
        DCRCDORD.KEY.EXC_CARD_TMS = 0;
        DCRCDORD.CARD_PROD = DCCUOPEN.CARD_PROD_CD;
        DCRCDORD.EMBOSS_NAME = DCCUOPEN.EMBOSS_NAME;
        CEP.TRC(SCCGWA, DCCUOPEN.CARD_CLS_PROD);
        CEP.TRC(SCCGWA, WS_CDORD_BV_CD_NO);
        DCRCDORD.CARD_CLS_CD = DCCUOPEN.CARD_CLS_PROD;
        DCRCDORD.BV_CD_NO = WS_CDORD_BV_CD_NO;
        DCRCDORD.EMBS_TYP = '2';
        DCRCDORD.CRT_STS = '0';
        DCRCDORD.APP_TELLER = SCCGWA.COMM_AREA.TL_ID;
        DCRCDORD.APP_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDORD.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDORD.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDORD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDORD.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRCDORD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRCDORD.TRAN_PIN_DAT = DCCPCDCD.OUTP_DATA.DEL_PSW;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        DCRCDORD.APP_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DCRCDORD.CERT_EXP_DATE = WS_CEID_EXP_DT;
        CEP.TRC(SCCGWA, DCRCDORD);
        CEP.TRC(SCCGWA, DCRCDORD.KEY.CARD_NO);
        CEP.TRC(SCCGWA, DCRCDORD.KEY.EXC_CARD_TMS);
        CEP.TRC(SCCGWA, DCRCDORD.CARD_PROD);
        CEP.TRC(SCCGWA, DCRCDORD.EMBS_TYP);
        CEP.TRC(SCCGWA, DCRCDORD.CRT_STS);
        CEP.TRC(SCCGWA, DCRCDORD.APP_TELLER);
        CEP.TRC(SCCGWA, DCRCDORD.APP_DT);
        CEP.TRC(SCCGWA, DCRCDORD.APP_TELLER);
        CEP.TRC(SCCGWA, DCRCDORD.APP_BR);
        CEP.TRC(SCCGWA, DCRPRDPR.DATA_TXT.PHY_TYP);
        T000_ADD_DCTCDORD();
        if (pgmRtn) return;
    }
    public void B041_OPEN_ACCOUNT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCRPRDPR.DATA_TXT.DD_PROD);
        CEP.TRC(SCCGWA, DCCDQPRD.VAL.DATA.ADSC_TYPE);
        CEP.TRC(SCCGWA, BPCRBANK.LOC_CCY1);
        IBS.init(SCCGWA, DDCUOPAC);
        if (DCCDQPRD.VAL.DATA.ADSC_TYPE == 'P') {
            DDCUOPAC.CARD_TYP = '1';
        } else {
            DDCUOPAC.CARD_TYP = '2';
        }
        DDCUOPAC.CI_NO = DCCUOPEN.HOLDER_CINO;
        DDCUOPAC.PROD_CODE = DCRPRDPR.DATA_TXT.DD_PROD;
        DDCUOPAC.ACNO_FLG = 'B';
        DDCUOPAC.PSBK_FLG = '2';
        DDCUOPAC.CARD_FLG = '1';
        DDCUOPAC.DRAW_MTH = ' ';
        DDCUOPAC.CCY = "156";
        DDCUOPAC.CCY_TYPE = '1';
        DDCUOPAC.AC = WS_CARDNO0;
        DDCUOPAC.AC_TYP = DCCUOPEN.AC_TYP;
        DDCUOPAC.AC_TYP = 'A';
        DDCUOPAC.AC_TYPE = DCCUOPEN.AC_TYPE;
        DDCUOPAC.CUS_MGR = DCCUOPEN.CUS_MGR;
        DDCUOPAC.REG_CENT = DCCUOPEN.REG_CENT;
        DDCUOPAC.SUB_BIZ = DCCUOPEN.SUB_BIZ;
        CEP.TRC(SCCGWA, DCCUOPEN.AC_TYPE);
        if (WS_FACE_FLG == 'Y') {
            if (DCCUOPEN.AC_TYPE == '2') {
                DDCUOPAC.ACO_AC_TYP = '6';
            } else {
                DDCUOPAC.ACO_AC_TYP = '7';
            }
        }
        if (WS_FACE_FLG == 'N') {
            if (DCCUOPEN.AC_TYPE == '2') {
                DDCUOPAC.ACO_AC_TYP = '3';
            } else {
                DDCUOPAC.ACO_AC_TYP = '4';
            }
        }
        CEP.TRC(SCCGWA, DDCUOPAC.ACNO_FLG);
        S000_CALL_DDZUOPAC();
        if (pgmRtn) return;
        WS_OPEN_CARD_FLG = 'Y';
        CEP.TRC(SCCGWA, DDCUOPAC.AC);
        DCCUOPEN.AC_NO = DDCUOPAC.AC;
    }
    public void B042_CARD_JOIN_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CEP.TRC(SCCGWA, DCCUOPEN.AC_NO);
        if (DCCDQPRD.VAL.DATA.ADSC_TYPE == 'C') {
            CICACCU.DATA.AGR_NO = DCCUOPEN.AC_NO;
        } else {
            CICACCU.DATA.AGR_NO = DCCUOPEN.PRIM_CD_NO;
        }
        S000_CALL_CIZACCU_A();
        if (pgmRtn) return;
        if (WS_CIZACCU_FLG == 'N') {
            IBS.init(SCCGWA, DCCUSPAC);
            if (DCCDQPRD.VAL.DATA.ADSC_TYPE == 'C') {
                DCCUSPAC.FUNC.AC = DCCUOPEN.AC_NO;
            } else {
                DCCUSPAC.FUNC.AC = DCCUOPEN.PRIM_CD_NO;
            }
            S000_CALL_DCZUSPAC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DCCUSPAC.OUTPUT.STD_AC);
            if (DCCUSPAC.OUTPUT.STD_AC.trim().length() > 0) {
                IBS.init(SCCGWA, CICACCU);
                CICACCU.DATA.AGR_NO = DCCUSPAC.OUTPUT.STD_AC;
                S000_CALL_CIZACCU();
                if (pgmRtn) return;
                DCCUOPEN.AC_NO = DCCUSPAC.OUTPUT.STD_AC;
                WS_AC_CI_NO = CICACCU.DATA.CI_NO;
            } else {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ACCOUNT_NOT_FOUND;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_ACCOUNT_NOT_FOUND, DCCUOPEN.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            WS_AC_CI_NO = CICACCU.DATA.CI_NO;
        }
        CEP.TRC(SCCGWA, WS_AC_CI_NO);
        CEP.TRC(SCCGWA, DCCUOPEN.OWNER_CINO);
        if (DCCDQPRD.VAL.DATA.ADSC_TYPE == 'C') {
            if (WS_AC_CI_NO.equalsIgnoreCase(DCCUOPEN.OWNER_CINO)) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CA_AC_NOT_DIFF_PER;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CA_AC_NOT_DIFF_PER, DCCUOPEN.RC);
            }
        } else {
            if (!WS_AC_CI_NO.equalsIgnoreCase(DCCUOPEN.OWNER_CINO)) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CA_AC_NOT_SAME_PER;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CA_AC_NOT_SAME_PER, DCCUOPEN.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, CICSACRL);
        CICSACRL.FUNC = 'A';
        CICSACRL.DATA.AC_NO = WS_CARDNO0;
        if (DCCDQPRD.VAL.DATA.ADSC_TYPE == 'C') {
            CICSACRL.DATA.AC_REL = "04";
            CICSACRL.DATA.REL_AC_NO = DCCUOPEN.AC_NO;
        } else {
            if (DCCUOPEN.CARD_LNK_TYP != '1') {
                CICSACRL.DATA.AC_REL = "03";
                CICSACRL.DATA.REL_AC_NO = DCCUOPEN.PRIM_CD_NO;
            }
        }
        CICSACRL.DATA.DEFAULT = '1';
        CICSACRL.DATA.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        S000_CALL_CIZSACRL();
        if (pgmRtn) return;
    }
    public void B043_CARD_NOTED_CI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSACR);
        CICSACR.FUNC = 'A';
        CICSACR.DATA.AGR_NO = WS_CARDNO0;
        CEP.TRC(SCCGWA, DCRPRDPR.DATA_TXT.PHY_TYP);
        if (DCRPRDPR.DATA_TXT.PHY_TYP == 'D') {
            CICSACR.DATA.ENTY_TYP = '5';
        } else {
            CICSACR.DATA.ENTY_TYP = '2';
        }
        if (DCCUOPEN.CARD_LNK_TYP == '1') {
            CICSACR.DATA.CI_NO = DCCUOPEN.OWNER_CINO;
            CICSACR.DATA.CNTRCT_TYP = "299";
        } else {
            CICSACR.DATA.CI_NO = DCCUOPEN.HOLDER_CINO;
            CICSACR.DATA.CNTRCT_TYP = "298";
        }
        CICSACR.DATA.PROD_CD = DCCUOPEN.CARD_PROD_CD;
        CICSACR.DATA.FRM_APP = "DC";
        CICSACR.DATA.CCY = "156";
        CICSACR.DATA.SHOW_FLG = 'Y';
        CICSACR.DATA.OPN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CICSACR.DATA.OPEN_DT = SCCGWA.COMM_AREA.AC_DATE;
        CICSACR.DATA.STSW = "11011000";
        S000_CALL_CIZSACR();
        if (pgmRtn) return;
    }
    public void B050_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT);
        WS_OUTPUT.WS_CARDNO = WS_CARDNO0;
        WS_OUTPUT.WS_CARD_PROD_CD = DCCUOPEN.CARD_PROD_CD;
        WS_OUTPUT.WS_CARD_CLS_PROD = DCCUOPEN.CARD_CLS_PROD;
        WS_OUTPUT.WS_BV_CD_NO = WS_CDORD_BV_CD_NO;
        WS_OUTPUT.WS_HOLDER_IDTYP = DCCUOPEN.HOLDER_IDTYP;
        WS_OUTPUT.WS_HOLDER_IDNO = DCCUOPEN.HOLDER_IDNO;
        WS_OUTPUT.WS_HOLDER_NAME = DCCUOPEN.HOLDER_NAME;
        WS_OUTPUT.WS_OWNER_IDTYP = DCCUOPEN.OWNER_IDTYP;
        WS_OUTPUT.WS_OWNER_IDNO = DCCUOPEN.OWNER_IDNO;
        WS_OUTPUT.WS_OWNER_NAME = DCCUOPEN.OWNER_NAME;
        WS_OUTPUT.WS_AC_NO = DCCUOPEN.AC_NO;
        WS_OUTPUT.WS_AC_TYP = DCCUOPEN.AC_TYP;
        WS_OUTPUT.WS_ISSUE_MTH = DCCUOPEN.ISSUE_MTH;
        WS_OUTPUT.WS_LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        WS_OUTPUT.WS_LAST_USER = SCCGWA.COMM_AREA.TL_ID;
        WS_OUTPUT.WS_LAST_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        WS_OUTPUT.WS_AGENT_IDTYP = DCCUOPEN.AGENT_IDTYP;
        WS_OUTPUT.WS_AGENT_IDNO = DCCUOPEN.AGENT_IDNO;
        WS_OUTPUT.WS_AGENT_NAME = DCCUOPEN.AGENT_NAME;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 1085;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B060_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCHSPEC);
        IBS.init(SCCGWA, DCCNSPEC);
        DCCNSPEC.HOLDER_IDTYP = DCCUOPEN.HOLDER_IDTYP;
        DCCNSPEC.HOLDER_IDNO = DCCUOPEN.HOLDER_IDNO;
        DCCNSPEC.HOLDER_NAME = DCCUOPEN.HOLDER_NAME;
        DCCNSPEC.OWNER_IDTYP = DCCUOPEN.OWNER_IDTYP;
        DCCNSPEC.OWNER_IDNO = DCCUOPEN.OWNER_IDNO;
        DCCNSPEC.OWNER_NAME = DCCUOPEN.OWNER_NAME;
        DCCNSPEC.CARD_PROD_CD = DCCUOPEN.CARD_PROD_CD;
        DCCNSPEC.CARD_BIN = DCCUOPEN.CARD_BIN;
        DCCNSPEC.AC_NO = DCCHSPEC.AC_NO;
        DCCNSPEC.AC_TYP = DCCHSPEC.AC_TYP;
        DCCNSPEC.ISSUE_MTH = DCCHSPEC.ISSUE_MTH;
        DCCNSPEC.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCCNSPEC.LAST_USER = SCCGWA.COMM_AREA.TL_ID;
        DCCNSPEC.LAST_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DCCNSPEC.SNAME_TRAN_FLG = DCCUOPEN.SNAME_TRAN_FLG;
        DCCNSPEC.DNAME_TRAN_FLG = DCCUOPEN.DNAME_TRAN_FLG;
        DCCNSPEC.OUT_DRAW_FLG = DCCUOPEN.OUT_DRAW_FLG;
        DCCNSPEC.AC_TYPE = DCCUOPEN.AC_TYPE;
        DCCNSPEC.CUS_MGR = DCCUOPEN.CUS_MGR;
        DCCNSPEC.REG_CENT = DCCUOPEN.REG_CENT;
        DCCNSPEC.SUB_BIZ = DCCUOPEN.SUB_BIZ;
        DCCNSPEC.DB_FREE = DCCUOPEN.DB_FREE;
        H000_HISTORY_RECORD();
        if (pgmRtn) return;
    }
    public void B070_FEE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '1';
        BPCFFTXI.TX_DATA.CARD_PSBK_NO = WS_CARDNO0;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = "156";
        BPCFFTXI.TX_DATA.CCY_TYPE = '1';
        BPCFFTXI.TX_DATA.SVR_CD = SCCGWA.COMM_AREA.SERV_CODE;
        BPCFFTXI.TX_DATA.CI_NO = DCCUOPEN.OWNER_CINO;
        S000_CALL_BPZFFTXI();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCTCALF);
        BPCTCALF.INPUT_AREA.FUNC_CODE = 'C';
        if (DCCUOPEN.CARD_LNK_TYP == '1') {
            BPCTCALF.INPUT_AREA.ATTR_VAL.DEF_FLG = "00";
        } else {
            BPCTCALF.INPUT_AREA.ATTR_VAL.DEF_FLG = "01";
        }
        WS_FEE_PROD = DCCUOPEN.CARD_PROD_CD;
        BPCTCALF.INPUT_AREA.PROD_CODE = DCCUOPEN.CARD_PROD_CD;
        BPCTCALF.INPUT_AREA.PROD_CODE1 = DCCUOPEN.CARD_PROD_CD;
        if ((WS_FEE_PROD.equalsIgnoreCase("CAD21000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD11000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD22000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD11100") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD12000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD10000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD28000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD51000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD55000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD16100") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD53000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD34000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD16000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD52000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD35000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD37000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD38000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD39000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD00000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD56000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD42000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD12100") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD66000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD43000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD54000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD50000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD20000"))) {
            BPCTCALF.INPUT_AREA.PROD_CODE = "CAD21000";
            BPCTCALF.INPUT_AREA.PROD_CODE1 = "CAD21000";
        }
        if ((WS_FEE_PROD.equalsIgnoreCase("CAD96000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD90000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD91000") 
            || WS_FEE_PROD.equalsIgnoreCase("CAD92000"))) {
            BPCTCALF.INPUT_AREA.PROD_CODE = "CAD91000";
            BPCTCALF.INPUT_AREA.PROD_CODE1 = "CAD91000";
        }
        BPCTCALF.INPUT_AREA.TX_AC = DCCUOPEN.AC_NO;
        BPCTCALF.INPUT_AREA.TX_CNT = 1;
        BPCTCALF.INPUT_AREA.TX_CI = DCCUOPEN.OWNER_CINO;
        BPCTCALF.INPUT_AREA.TX_CCY = "156";
        S000_CALL_BPZFCALF();
        if (pgmRtn) return;
    }
    public void B041_AUTO_TRY_GEN_CARDNO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCFCDGG);
        DCCFCDGG.VAL.FUNC = 'G';
        CEP.TRC(SCCGWA, WS_CARD_BIN);
        if (DCCFCDGG.VAL.CARD_NO == null) DCCFCDGG.VAL.CARD_NO = "";
        JIBS_tmp_int = DCCFCDGG.VAL.CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCCFCDGG.VAL.CARD_NO += " ";
        if (WS_CARD_BIN == null) WS_CARD_BIN = "";
        JIBS_tmp_int = WS_CARD_BIN.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) WS_CARD_BIN += " ";
        DCCFCDGG.VAL.CARD_NO = WS_CARD_BIN + DCCFCDGG.VAL.CARD_NO.substring(WS_CARD_BIN_F.WS_CARD_BIN_F_1);
        WS_BIN_AFTER = (short) (WS_CARD_BIN_F.WS_CARD_BIN_F_1 + 1);
        CEP.TRC(SCCGWA, WS_BIN_AFTER);
        if (DCRBINPM.SEG_NUM == 1) {
            CEP.TRC(SCCGWA, "CFX003");
            WS_START = 15 - WS_SEQ_LEN + 1;
            WS_SEQNO = WS_AP_SEQ;
            JIBS_tmp_str[0] = "" + WS_SEQNO;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            CEP.TRC(SCCGWA, JIBS_tmp_str[0].substring(WS_START - 1, WS_START + WS_SEQ_LEN - 1));
            CEP.TRC(SCCGWA, WS_SEQNO);
            CEP.TRC(SCCGWA, WS_START);
            CEP.TRC(SCCGWA, WS_SEQ_LEN);
            JIBS_tmp_str[0] = "" + WS_SEQNO;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (DCCFCDGG.VAL.CARD_NO == null) DCCFCDGG.VAL.CARD_NO = "";
            JIBS_tmp_int = DCCFCDGG.VAL.CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) DCCFCDGG.VAL.CARD_NO += " ";
            DCCFCDGG.VAL.CARD_NO = DCCFCDGG.VAL.CARD_NO.substring(0, WS_BIN_AFTER - 1) + JIBS_tmp_str[0].substring(WS_START - 1, WS_START + WS_SEQ_LEN - 1) + DCCFCDGG.VAL.CARD_NO.substring(WS_BIN_AFTER + WS_SEQ_LEN - 1);
        } else {
            CEP.TRC(SCCGWA, "CFX004");
            WS_START = 15 - DCRBINPM.SEG1_LEN + 1;
            CEP.TRC(SCCGWA, WS_START);
            CEP.TRC(SCCGWA, WS_UOPEN_CARD_SEG1);
            CEP.TRC(SCCGWA, DCRBINPM.SEG1_LEN);
            CEP.TRC(SCCGWA, WS_UOPEN_CARD_SEG1);
            CEP.TRC(SCCGWA, DCCFCDGG.VAL.CARD_NO);
            JIBS_tmp_str[0] = "" + WS_UOPEN_CARD_SEG1;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (DCCFCDGG.VAL.CARD_NO == null) DCCFCDGG.VAL.CARD_NO = "";
            JIBS_tmp_int = DCCFCDGG.VAL.CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) DCCFCDGG.VAL.CARD_NO += " ";
            DCCFCDGG.VAL.CARD_NO = DCCFCDGG.VAL.CARD_NO.substring(0, WS_BIN_AFTER - 1) + JIBS_tmp_str[0].substring(WS_START - 1, WS_START + DCRBINPM.SEG1_LEN - 1) + DCCFCDGG.VAL.CARD_NO.substring(WS_BIN_AFTER + DCRBINPM.SEG1_LEN - 1);
            CEP.TRC(SCCGWA, DCRBINPM.SEG1_LEN);
            CEP.TRC(SCCGWA, DCCFCDGG.VAL.CARD_NO);
            WS_START = 15 - WS_SEQ_LEN + 1;
            WS_SEQNO = WS_AP_SEQ;
            WS_PTR = WS_CARD_BIN_F.WS_CARD_BIN_F_1 + DCRBINPM.SEG1_LEN + 1;
            JIBS_tmp_str[0] = "" + WS_SEQNO;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            CEP.TRC(SCCGWA, JIBS_tmp_str[0].substring(WS_START - 1, WS_START + WS_SEQ_LEN - 1));
            CEP.TRC(SCCGWA, WS_SEQNO);
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
        CEP.TRC(SCCGWA, "CFX005");
        CEP.TRC(SCCGWA, DCCFCDGG.VAL.CARD_NO);
        S000_CALL_DCZFCDGG();
        if (pgmRtn) return;
        WS_OUTPUT.WS_CARDNO = DCCFCDGG.VAL.CARD_NO_GEN;
        WS_CHK_CARDNO = DCCFCDGG.VAL.CARD_NO_GEN;
        WS_CARDNO0 = DCCFCDGG.VAL.CARD_NO_GEN;
    }
    public void B051_JUMP_4() throws IOException,SQLException,Exception {
        WS_JUMP_FLG = 'N';
        for (WS_I = 1; WS_I <= DCRBINPM.CARD_LEN 
            && WS_JUMP_FLG != 'Y'; WS_I += 1) {
            if (WS_OUTPUT.WS_CARDNO == null) WS_OUTPUT.WS_CARDNO = "";
            JIBS_tmp_int = WS_OUTPUT.WS_CARDNO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) WS_OUTPUT.WS_CARDNO += " ";
            if (WS_OUTPUT.WS_CARDNO.substring(WS_I - 1, WS_I + 1 - 1).equalsIgnoreCase("4")) {
                WS_JUMP_FLG = 'Y';
            } else {
            }
        }
        if (WS_JUMP_FLG == 'N') {
            WS_WRT_FLG = 'Y';
        }
    }
    public void B065_REGIST_AGENT_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSAGEN);
        CICSAGEN.FUNC = 'A';
        CICSAGEN.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        CICSAGEN.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        CICSAGEN.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        CICSAGEN.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CICSAGEN.TLR_NO = SCCGWA.COMM_AREA.TL_ID;
        CICSAGEN.CI_NO = DCCUOPEN.HOLDER_CINO;
        CICSAGEN.OUT_AC = WS_CARDNO0;
        CEP.TRC(SCCGWA, CICGAGA_AGENT_AREA.ID_TYP);
        CEP.TRC(SCCGWA, CICGAGA_AGENT_AREA.ID_NO);
        CEP.TRC(SCCGWA, CICGAGA_AGENT_AREA.CI_NM);
        CEP.TRC(SCCGWA, CICGAGA_AGENT_AREA.PHONE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AGENT_FLG);
        CICSAGEN.AGENT_TP = "01";
        if (DCCUOPEN.AGENT_FLG == '1') {
            CICSAGEN.ID_TYP = DCCUOPEN.AGENT_IDTYP;
            CICSAGEN.ID_NO = DCCUOPEN.AGENT_IDNO;
            CICSAGEN.CI_NAME = DCCUOPEN.AGENT_NAME;
        }
        if (SCCGWA.COMM_AREA.AGENT_FLG == 'Y') {
            CICSAGEN.ID_TYP = CICGAGA_AGENT_AREA.ID_TYP;
            CICSAGEN.ID_NO = CICGAGA_AGENT_AREA.ID_NO;
            CICSAGEN.CI_NAME = CICGAGA_AGENT_AREA.CI_NM;
            CICSAGEN.PHONE = CICGAGA_AGENT_AREA.PHONE;
        }
        S000_CALL_CIZSAGEN();
        if (pgmRtn) return;
    }
    public void B066_REGIST_OCAC_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSOCAC);
        BPCSOCAC.FUNC = 'C';
        BPCSOCAC.AC = WS_CARDNO0;
        BPCSOCAC.STS = 'N';
        BPCSOCAC.WORK_TYP = "21";
        BPCSOCAC.CI_TYPE = '1';
        BPCSOCAC.BV_TYP = '1';
        if (WS_CARD_MEDI == '4') {
            BPCSOCAC.BV_TYP = '8';
        }
        if (WS_CARD_MEDI == '1') {
            BPCSOCAC.NEW_BV_TYP = '2';
        }
        if (WS_CARD_MEDI == '2') {
            BPCSOCAC.NEW_BV_TYP = '1';
        }
        if (WS_NEW_ADSC_TYPE == 'C') {
            BPCSOCAC.NEW_BV_TYP = '3';
        }
        if (WS_CARDNO0 == null) WS_CARDNO0 = "";
        JIBS_tmp_int = WS_CARDNO0.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) WS_CARDNO0 += " ";
        BPCSOCAC.BV_NO = WS_CARDNO0.substring(0, 18);
        BPCSOCAC.ID_TYP = DCCUOPEN.HOLDER_IDTYP;
        BPCSOCAC.ID_NO = DCCUOPEN.HOLDER_IDNO;
        BPCSOCAC.CI_CNM = DCCUOPEN.HOLDER_NAME;
        BPCSOCAC.CARD_FLG = WS_CARD_FLG;
        BPCSOCAC.CCY = "156";
        BPCSOCAC.CCY_TYPE = '1';
        BPCSOCAC.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSOCAC.OPEN_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSOCAC.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        BPCSOCAC.PROD_CD = DCCUOPEN.CARD_PROD_CD;
        BPCSOCAC.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-OCAC-INFO", BPCSOCAC);
    }
    public void B075_GENERATE_PIN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCS9991);
        CEP.TRC(SCCGWA, DCCUOPEN.CARD_NO);
        CEP.TRC(SCCGWA, WS_CARDNO0);
        DCCS9991.OLD_AGR_NO = "123456789012";
        DCCS9991.NEW_AGR_NO = WS_CARDNO0;
        if (WS_CARDNO0 == null) WS_CARDNO0 = "";
        JIBS_tmp_int = WS_CARDNO0.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) WS_CARDNO0 += " ";
        DCCS9991.AGR_NO_6 = WS_CARDNO0.substring(14 - 1, 14 + 6 - 1);
        DCCS9991.ID_TYPE = DCCUOPEN.HOLDER_IDTYP;
        DCCS9991.ID_NO = DCCUOPEN.HOLDER_IDNO;
        DCCS9991.CI_NM = DCCUOPEN.HOLDER_NAME;
        DCCS9991.PSWD_NO = DCCUOPEN.PSW;
        S000_CALL_DCZUPWCK();
        if (pgmRtn) return;
        WS_TRAN_PIN_DAT = DCCS9991.O_PINOFFSET;
        CEP.TRC(SCCGWA, DCCS9991.O_PINOFFSET);
        CEP.TRC(SCCGWA, WS_TRAN_PIN_DAT);
    }
    public void B076_GENERATE_RANDOM_PIN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCTSSC);
        SCCTSSC.FUNC = 'D';
        SCCTSSC.COMM_AREA_D.D_ACCNO = WS_CARDNO0;
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_D);
        SCZTSSC SCZTSSC = new SCZTSSC();
        SCZTSSC.MP(SCCGWA, SCCTSSC);
        WS_TRAN_PIN_DAT = SCCTSSC.COMM_AREA_D.D_O_PINBLOCK;
        WS_TR_PIN_DAT = SCCTSSC.COMM_AREA_D.D_O_PINOFFSET;
        CEP.TRC(SCCGWA, SCCTSSC.COMM_AREA_D.D_O_PINOFFSET);
        CEP.TRC(SCCGWA, SCCTSSC.COMM_AREA_D.D_O_PINBLOCK);
        CEP.TRC(SCCGWA, SCCTSSC.COMM_AREA_D.D_O_PINBLOCK);
        CEP.TRC(SCCGWA, SCCTSSC.COMM_AREA_D.D_FILLER);
        CEP.TRC(SCCGWA, WS_TRAN_PIN_DAT);
        CEP.TRC(SCCGWA, WS_TR_PIN_DAT);
    }
    public void B900_OUTPUT_INF() throws IOException,SQLException,Exception {
        DCCUOPEN.CARD_NO = WS_CARDNO0;
        DCCUOPEN.AC_NO = DDCUOPAC.AC;
        DCCUOPEN.PSW = WS_TRAN_PIN_DAT;
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        BPCPRMR.DAT_PTR = DCRPRDPR;
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ        ", BPCPRMR);
        CEP.TRC(SCCGWA, BPCPRMR.RC);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PROD_NOTFND;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_PROD_NOTFND, DCCUOPEN.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUOPEN.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_DCZUPRCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-MPRD", DCCUPRCD);
        CEP.TRC(SCCGWA, DCCUPRCD.RC);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CISOACCU", CICACCU);
    }
    if (CICACCU.RC.RC_CODE != 0) {
        WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUOPEN.RC);
        S000_ERR_MSG_PROC();
        if (pgmRtn) return;
    }
    public void S000_CALL_CIZACCU_A() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CISOACCU", CICACCU);
        CEP.TRC(SCCGWA, CICACCU.RC);
        if (CICACCU.RC.RC_CODE != 0) {
            WS_CIZACCU_FLG = 'N';
        } else {
            WS_CIZACCU_FLG = 'Y';
        }
        CEP.TRC(SCCGWA, WS_CIZACCU_FLG);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUOPEN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void H000_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.TX_RMK = K_HIS_RMK;
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID = "DCCHSPEC";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_TOOL = WS_CARDNO0;
        BPCPNHIS.INFO.CI_NO = DCCUOPEN.OWNER_CINO;
        BPCPNHIS.INFO.FMT_ID_LEN = 777;
        BPCPNHIS.INFO.OLD_DAT_PT = DCCOSPEC;
        BPCPNHIS.INFO.NEW_DAT_PT = DCCNSPEC;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void T000_READUPD_DCTDCSEQ() throws IOException,SQLException,Exception {
        DCTDCSEQ_RD = new DBParm();
        DCTDCSEQ_RD.TableName = "DCTDCSEQ";
        DCTDCSEQ_RD.col = "CARD_PROD_CD, CARD_BIN, CARD_SEG1, CUR_SEQ, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        DCTDCSEQ_RD.upd = true;
        IBS.READ(SCCGWA, DCRDCSEQ, DCTDCSEQ_RD);
    }
    public void T000_UPDATE_DCTDCSEQ() throws IOException,SQLException,Exception {
        DCTDCSEQ_RD = new DBParm();
        DCTDCSEQ_RD.TableName = "DCTDCSEQ";
        DCTDCSEQ_RD.col = "CUR_SEQ, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        DCTDCSEQ_RD.where = "CARD_PROD_CD = :DCRDCSEQ.KEY.CARD_PROD_CD "
            + "AND CARD_BIN = :DCRDCSEQ.KEY.CARD_BIN "
            + "AND CARD_SEG1 = :DCRDCSEQ.KEY.CARD_SEG1";
        IBS.REWRITE(SCCGWA, DCRDCSEQ, this, DCTDCSEQ_RD);
    }
    public void T000_GROUP_DCTCDORD() throws IOException,SQLException,Exception {
        null.set = "WS-CNT-ORD=COUNT(*)";
        DCTCDORD_RD = new DBParm();
        DCTCDORD_RD.TableName = "DCTCDORD";
        DCTCDORD_RD.where = "CARD_NO = :DCRCDORD.KEY.CARD_NO";
        IBS.GROUP(SCCGWA, DCRCDORD, this, DCTCDORD_RD);
    }
    public void T000_STARTBR_DCTPDBIN_FIRST() throws IOException,SQLException,Exception {
        DCTPDBIN_RD = new DBParm();
        DCTPDBIN_RD.TableName = "DCTPDBIN";
        DCTPDBIN_RD.col = "CARD_PROD_CD, CARD_BIN, CARD_SEG1, SEQ, CARD_SEG1_RUL, S_SEQ, E_SEQ, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        DCTPDBIN_RD.where = "CARD_PROD_CD = :DCRPDBIN.KEY.CARD_PROD_CD "
            + "AND CARD_BIN = :DCRPDBIN.KEY.CARD_BIN "
            + "AND CARD_SEG1 = :DCRPDBIN.KEY.CARD_SEG1";
        DCTPDBIN_RD.fst = true;
        DCTPDBIN_RD.order = "S_SEQ";
        IBS.READ(SCCGWA, DCRPDBIN, this, DCTPDBIN_RD);
    }
    public void T000_READ_DCTPDBIN_FIRST() throws IOException,SQLException,Exception {
        DCTPDBIN_RD = new DBParm();
        DCTPDBIN_RD.TableName = "DCTPDBIN";
        DCTPDBIN_RD.where = "CARD_PROD_CD = :DCRPDBIN.KEY.CARD_PROD_CD";
        DCTPDBIN_RD.fst = true;
        DCTPDBIN_RD.order = "SEQ DESC";
        IBS.READ(SCCGWA, DCRPDBIN, this, DCTPDBIN_RD);
    }
    public void T000_READ_DCTDCCLS_FIRST() throws IOException,SQLException,Exception {
        DCTDCCLS_RD = new DBParm();
        DCTDCCLS_RD.TableName = "DCTDCCLS";
        DCTDCCLS_RD.where = "CARD_PROD_CD = :DCRDCCLS.KEY.CARD_PROD_CD "
            + "AND CARD_CLS_CD = :DCRDCCLS.KEY.CARD_CLS_CD";
        DCTDCCLS_RD.fst = true;
        DCTDCCLS_RD.order = "BV_CD_NO DESC";
        IBS.READ(SCCGWA, DCRDCCLS, this, DCTDCCLS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PROD_CLS_NOTMATCH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTDCSEQ() throws IOException,SQLException,Exception {
        DCTDCSEQ_RD = new DBParm();
        DCTDCSEQ_RD.TableName = "DCTDCSEQ";
        DCTDCSEQ_RD.col = "CARD_PROD_CD, CARD_BIN, CARD_SEG1, CUR_SEQ, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        IBS.READ(SCCGWA, DCRDCSEQ, DCTDCSEQ_RD);
    }
    public void T000_WRITE_DCTDCSEQ() throws IOException,SQLException,Exception {
        DCTDCSEQ_RD = new DBParm();
        DCTDCSEQ_RD.TableName = "DCTDCSEQ";
        IBS.WRITE(SCCGWA, DCRDCSEQ, DCTDCSEQ_RD);
    }
    public void T000_READ_DCTCDORD() throws IOException,SQLException,Exception {
        DCTCDORD_RD = new DBParm();
        DCTCDORD_RD.TableName = "DCTCDORD";
        DCTCDORD_RD.col = "CARD_NO, EXC_CARD_TMS, APP_BAT_NO, CARD_PROD, CARD_CLS_CD, BV_CD_NO, EMBOSS_NAME, EMBS_TYP, CRT_STS, CRT_DT, TRAN_PIN_DAT, QURY_PIN_DAT, SF_FLG, APP_TYP, CERT_EXP_DATE, APP_TELLER, APP_DT, APP_BR, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        IBS.READ(SCCGWA, DCRCDORD, DCTCDORD_RD);
    }
    public void T000_GROUP_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.set = "WS-L-CNT=COUNT(*)";
        DCTCDDAT_RD.where = "PRIM_CARD_NO = :DCRCDDAT.PRIM_CARD_NO "
            + "AND CARD_STS < > 'C'";
        IBS.GROUP(SCCGWA, DCRCDDAT, this, DCTCDDAT_RD);
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
            + "AND CARD_BIN = :DCRPDBIN.KEY.CARD_BIN "
            + "AND CARD_SEG1 = :DCRPDBIN.KEY.CARD_SEG1";
        IBS.GROUP(SCCGWA, DCRPDBIN, this, DCTPDBIN_RD);
        CEP.TRC(SCCGWA, WS_CNT_T);
        CEP.TRC(SCCGWA, WS_AP_SEQ);
        CEP.TRC(SCCGWA, DCRPDBIN.KEY.CARD_PROD_CD);
        CEP.TRC(SCCGWA, DCRPDBIN.KEY.CARD_BIN);
        CEP.TRC(SCCGWA, DCRPDBIN.KEY.CARD_SEG1);
    }
    public void T000_GROUP_DCTPDBIN_LATER() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "SECOND-PDBIN");
        CEP.TRC(SCCGWA, WS_CNT_T);
        CEP.TRC(SCCGWA, WS_AP_SEQ);
        CEP.TRC(SCCGWA, DCRPDBIN.KEY.CARD_PROD_CD);
        CEP.TRC(SCCGWA, DCRPDBIN.KEY.CARD_BIN);
        CEP.TRC(SCCGWA, DCRPDBIN.KEY.CARD_SEG1);
        DCTPDBIN_RD = new DBParm();
        DCTPDBIN_RD.TableName = "DCTPDBIN";
        DCTPDBIN_RD.set = "WS-CNT-T=COUNT(*)";
        DCTPDBIN_RD.where = "S_SEQ >= :WS_AP_SEQ "
            + "AND CARD_PROD_CD = :DCRPDBIN.KEY.CARD_PROD_CD "
            + "AND CARD_BIN = :DCRPDBIN.KEY.CARD_BIN "
            + "AND CARD_SEG1 = :DCRPDBIN.KEY.CARD_SEG1";
        IBS.GROUP(SCCGWA, DCRPDBIN, this, DCTPDBIN_RD);
        CEP.TRC(SCCGWA, WS_CNT_T);
        CEP.TRC(SCCGWA, WS_AP_SEQ);
        CEP.TRC(SCCGWA, DCRPDBIN.KEY.CARD_PROD_CD);
        CEP.TRC(SCCGWA, DCRPDBIN.KEY.CARD_BIN);
        CEP.TRC(SCCGWA, DCRPDBIN.KEY.CARD_SEG1);
        if (WS_CNT_T <= 0) {
            WS_CHECK_LATER_FLG = 'N';
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_SEQNO_NO_PLAN;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_SEQNO_NO_PLAN, DCCUOPEN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_CHECK_LATER_FLG);
    }
    public void T000_STBR_DCTPDBIN_NEXT_USE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "THIRD-PDBIN");
        CEP.TRC(SCCGWA, WS_CNT_T);
        CEP.TRC(SCCGWA, WS_AP_SEQ);
        CEP.TRC(SCCGWA, DCRPDBIN.KEY.CARD_PROD_CD);
        CEP.TRC(SCCGWA, DCRPDBIN.KEY.CARD_BIN);
        CEP.TRC(SCCGWA, DCRPDBIN.KEY.CARD_SEG1);
        DCTPDBIN_RD = new DBParm();
        DCTPDBIN_RD.TableName = "DCTPDBIN";
        DCTPDBIN_RD.col = "CARD_PROD_CD, CARD_BIN, CARD_SEG1, SEQ, CARD_SEG1_RUL, S_SEQ, E_SEQ, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        DCTPDBIN_RD.where = "S_SEQ >= :WS_AP_SEQ "
            + "AND CARD_PROD_CD = :DCRPDBIN.KEY.CARD_PROD_CD "
            + "AND CARD_BIN = :DCRPDBIN.KEY.CARD_BIN "
            + "AND CARD_SEG1 = :DCRPDBIN.KEY.CARD_SEG1";
        DCTPDBIN_RD.fst = true;
        DCTPDBIN_RD.order = "S_SEQ";
        IBS.READ(SCCGWA, DCRPDBIN, this, DCTPDBIN_RD);
        CEP.TRC(SCCGWA, DCRPDBIN.S_SEQ);
    }
    public void T000_READ_DCTBINPM() throws IOException,SQLException,Exception {
        DCTBINPM_RD = new DBParm();
        DCTBINPM_RD.TableName = "DCTBINPM";
        DCTBINPM_RD.col = "BIN, UNION_SHORT_NAME, UNION_NAME, CHK_DIG_MTH, PIN_MTH, CVV_TYP, CARD_LEN, SEG_NUM, SEG1_LEN, SEG1_RMK, CEID_EXP, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        IBS.READ(SCCGWA, DCRBINPM, DCTBINPM_RD);
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.col = "CARD_NO, ACNO_TYPE, EXC_CARD_TMS, CARD_CLS_PROD, BV_CD_NO, CARD_LNK_TYP, PROD_CD, CARD_OWN_CINO, CARD_HLDR_CINO, CARD_MEDI, CARD_STS, CARD_STSW, CARD_TYP, TRAN_PIN_DAT, QURY_PIN_DAT, PVK_TYP, HOLD_AC_FLG, PROD_LMT_FLG, CVV_FLG, SAME_NAME_TFR_FLG, DIFF_NAME_TFR_FLG, DRAW_OVER_FLG, SF_FLG, DOUBLE_FREE_FLG, ISSU_BR, CLT_BR, EMBS_DT, ISSU_DT, EXP_DT, CLO_DT, ANU_FEE_NXT, ANU_FEE_PCT, ANU_FEE_FREE, ANU_FEE_ARG, PIN_ERR_CNT, PIN_LCK_DT, CVV_LCK_DT, LAST_TXN_DT, AC_OIC_NO, AC_OIC_CODE, SUB_DP, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR, TS";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
    }
    public void T000_ADD_DCTCDORD() throws IOException,SQLException,Exception {
        DCTCDORD_RD = new DBParm();
        DCTCDORD_RD.TableName = "DCTCDORD";
        IBS.WRITE(SCCGWA, DCRCDORD, DCTCDORD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CDORD_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDORD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_GROUP_DCTPSWMT() throws IOException,SQLException,Exception {
        DCTPSWMT_RD = new DBParm();
        DCTPSWMT_RD.TableName = "DCTPSWMT";
        DCTPSWMT_RD.set = "WS-MAX-BATCHNO=IFNULL(MAX(APP_BAT_NO),0)";
        DCTPSWMT_RD.where = "CRT_DT = :DCRPSWMT.KEY.CRT_DT";
        IBS.GROUP(SCCGWA, DCRPSWMT, this, DCTPSWMT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTPSWMT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ADD_DCTPSWMT() throws IOException,SQLException,Exception {
        DCTPSWMT_RD = new DBParm();
        DCTPSWMT_RD.TableName = "DCTPSWMT";
        IBS.WRITE(SCCGWA, DCRPSWMT, DCTPSWMT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTPSWMT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTPDBIN_1() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CUR_SEQ);
        DCTPDBIN_RD = new DBParm();
        DCTPDBIN_RD.TableName = "DCTPDBIN";
        DCTPDBIN_RD.where = "CARD_PROD_CD = :DCRPDBIN.KEY.CARD_PROD_CD "
            + "AND CARD_BIN = :DCRPDBIN.KEY.CARD_BIN "
            + "AND CARD_SEG1 = :DCRPDBIN.KEY.CARD_SEG1 "
            + "AND S_SEQ <= :WS_AP_SEQ "
            + "AND E_SEQ >= :WS_AP_SEQ";
        IBS.READ(SCCGWA, DCRPDBIN, this, DCTPDBIN_RD);
    }
    public void T000_READ_DCTPDBIN_2() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CUR_SEQ);
        DCTPDBIN_RD = new DBParm();
        DCTPDBIN_RD.TableName = "DCTPDBIN";
        DCTPDBIN_RD.where = "CARD_PROD_CD = :DCRPDBIN.KEY.CARD_PROD_CD "
            + "AND CARD_BIN = :DCRPDBIN.KEY.CARD_BIN "
            + "AND CARD_SEG1 = :DCRPDBIN.KEY.CARD_SEG1 "
            + "AND S_SEQ >= :WS_AP_SEQ";
        DCTPDBIN_RD.fst = true;
        DCTPDBIN_RD.order = "S_SEQ";
        IBS.READ(SCCGWA, DCRPDBIN, this, DCTPDBIN_RD);
    }
    public void T000_ADD_DCTDCSEQ() throws IOException,SQLException,Exception {
        DCTDCSEQ_RD = new DBParm();
        DCTDCSEQ_RD.TableName = "DCTDCSEQ";
        IBS.WRITE(SCCGWA, DCRDCSEQ, DCTDCSEQ_RD);
    }
    public void S000_CALL_CIZSACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACR", CICSACR);
        if (CICSACR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICSACR.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICSACR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUOPEN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUPWCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-SIMPLE-PSW-CHECK", DCCS9991);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CISOCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase("CI3002")) {
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUOPEN.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUOPEN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-P-QUERY-PROD", DCCDQPRD);
        if (DCCDQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCDQPRD.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCDQPRD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUOPEN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-S-GET-SEQ", DCCSGSEQ);
        if (DCCSGSEQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCSGSEQ.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCSGSEQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUOPEN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZFCDGG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-F-CHK-DIGIT-GEN", DCCFCDGG);
        if (DCCFCDGG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCFCDGG.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCFCDGG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUOPEN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZSAGEN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SVR-CIZSAGEN", CICSAGEN);
        if (CICSAGEN.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSAGEN.RC);
        }
    }
    public void S000_CALL_DCZUCSET() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-SETUP", DCCUCSET);
        if (DCCUCSET.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCSET.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUCSET.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUOPEN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUOPEN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZUOPAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-OPEN-AC     ", DDCUOPAC, true);
    }
    public void S000_CALL_DCZUGECD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-GEN-CARD-NO", DCCUGECD, true);
    }
    public void S000_CALL_CIZSACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACRL   ", CICSACRL);
        if (CICSACRL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, CICSACRL.RC);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICSACRL.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICSACRL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUOPEN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZPCDCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-P-CARD-TRKDAT-CHK", DCCPCDCK);
    }
    public void S000_CALL_DCZPCDCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DCZPCDCD, DCCPCDCD);
        if (DCCPCDCD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPCDCD.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCPCDCD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUOPEN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFFTXI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_F_TX_INFO, BPCFFTXI);
        if (BPCFFTXI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFTXI.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFFTXI.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUOPEN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFCALF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_F_CAL_FEE, BPCTCALF);
        if (BPCTCALF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTCALF.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTCALF.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUOPEN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_GROUP_DCTCDPRT() throws IOException,SQLException,Exception {
        DCTCDPRT_RD = new DBParm();
        DCTCDPRT_RD.TableName = "DCTCDPRT";
        DCTCDPRT_RD.set = "WS-CNT-SQL=COUNT(*)";
        DCTCDPRT_RD.where = "CARD_NO = :DCRCDPRT.KEY.CARD_NO "
            + "AND PRT_TYP = :DCRCDPRT.KEY.PRT_TYP";
        IBS.GROUP(SCCGWA, DCRCDPRT, this, DCTCDPRT_RD);
    }
    public void T000_WRITE_DCTCDPRT() throws IOException,SQLException,Exception {
        DCTCDPRT_RD = new DBParm();
        DCTCDPRT_RD.TableName = "DCTCDPRT";
        IBS.WRITE(SCCGWA, DCRCDPRT, DCTCDPRT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_TBL_FLAG = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDPRT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DCTCDDAT_1() throws IOException,SQLException,Exception {
        DCTCDDAT_BR.rp = new DBParm();
        DCTCDDAT_BR.rp.TableName = "DCTCDDAT";
        DCTCDDAT_BR.rp.where = "CARD_HLDR_CINO = :DCRCDDAT.CARD_HLDR_CINO";
        IBS.STARTBR(SCCGWA, DCRCDDAT, this, DCTCDDAT_BR);
    }
    public void T000_READ_NEXT_DCTCDDAT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRCDDAT, this, DCTCDDAT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "CDDAT DATA FOUND");
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "CDDAT DATA NOT FOUND");
        }
    }
    public void T000_ENDBR_DCTCDDAT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTCDDAT_BR);
    }
    public void S000_CALL_CIZMAGT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-AGT", CICMAGT);
        if (CICMAGT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICMAGT.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICMAGT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUOPEN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZUAGT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-GNL-AGT-NO", CICUAGT);
        if (CICUAGT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICUAGT.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICUAGT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUOPEN.RC);
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
    public void S000_CALL_DCZUSPAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-STD-AC", DCCUSPAC);
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (DCCUOPEN.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DCCUOPEN=");
            CEP.TRC(SCCGWA, DCCUOPEN);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
