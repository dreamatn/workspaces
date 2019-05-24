package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.DC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import com.hisun.EA.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZUOPAC {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm DDTVCH_RD;
    DBParm DDTCCY_RD;
    DBParm DDTMST_RD;
    DBParm DDTAGENT_RD;
    boolean pgmRtn = false;
    String K_APP_MMO = "DD";
    String K_APP_MMO1 = "DC";
    String K_CI_STS_TBL = "0001";
    String K_CI_STS_ELEC_AC = "0007";
    String K_CI_STS_SOC_CARD = "0004";
    String K_HIS_CPB_NAME = "DDCHOPAC";
    String K_HIS_REMARKS = "OPEN ACCOUNT";
    String K_HIS_MMO = "P114";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    String WS_AC_NO = " ";
    String WS_AC_CCY = "156";
    short WS_RC_CODE = 0;
    short WS_CNT = 0;
    int WS_EFF_DATE = 0;
    int WS_BR = 0;
    char WS_PROD_SURP_CNY = ' ';
    short WS_CCY_N = 0;
    short WS_T_AC_NUM = 0;
    short WS_READ_CNT = 0;
    String WS_ACNO_NO = " ";
    DDZUOPAC_WS_ACNO_NO_1 WS_ACNO_NO_1 = new DDZUOPAC_WS_ACNO_NO_1();
    int WS_OPEN_BP = 0;
    int WS_OWNER_BRDP = 0;
    int WS_OWNER_BR = 0;
    int WS_OWNER_BK = 0;
    char WS_CARD_PSBK_FLG = ' ';
    char WS_BV_TYPE = ' ';
    String WS_EC_CARD = " ";
    int WS_MAX_DT = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDVMRAT DDVMRAT = new DDVMRAT();
    DDCIPSBK DDCIPSBK = new DDCIPSBK();
    DDCSOPAC DDCSOPAC = new DDCSOPAC();
    DCCSSPEC DCCSSPEC = new DCCSSPEC();
    DCCUUORD DCCUUORD = new DCCUUORD();
    DCCSCGET DCCSCGET = new DCCSCGET();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCCGAC BPCCGAC = new BPCCGAC();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCQCNGL BPCQCNGL = new BPCQCNGL();
    BPCACAAC BPCACAAC = new BPCACAAC();
    BPCPCKPD BPCPCKPD = new BPCPCKPD();
    BPCUCNGM BPCUCNGM = new BPCUCNGM();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    BPCPQORG BPCPQORG = new BPCPQORG();
    CICSACR CICSACR = new CICSACR();
    CICSAGEN CICSAGEN = new CICSAGEN();
    CICCUST CICCUST = new CICCUST();
    CICSSCH CICSSCH = new CICSSCH();
    CICOPCS CICOPCS = new CICOPCS();
    SCCBSP SCCBSP = new SCCBSP();
    SCCBSPS SCCBSPS = new SCCBSPS();
    DCCUSSTS DCCUSSTS = new DCCUSSTS();
    DDCSACCH DDCSACCH = new DDCSACCH();
    CICSACAC CICSACAC = new CICSACAC();
    BPCPFXOG BPCPFXOG = new BPCPFXOG();
    BPCSOCAC BPCSOCAC = new BPCSOCAC();
    BPROCAC BPROCAC = new BPROCAC();
    CICQCIAC CICQCIAC = new CICQCIAC();
    EACUOPEN EACUOPEN = new EACUOPEN();
    DCCUCINF DCCUCINF = new DCCUCINF();
    CICQACRI CICQACRI = new CICQACRI();
    CICQACRL CICQACRL = new CICQACRL();
    CICACCU CICACCU = new CICACCU();
    CICPDTL CICPDTL = new CICPDTL();
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    CIRADR CIRADR = new CIRADR();
    CIRCNT CIRCNT = new CIRCNT();
    CIRCDM CIRCDM = new CIRCDM();
    DDRVCH DDRVCH = new DDRVCH();
    DDRAGENT DDRAGENT = new DDRAGENT();
    SCCGWA SCCGWA;
    DDCUOPAC DDCUOPAC;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPCPORUP_DATA_INFO BPCPORUP;
    CICGAGA_AGENT_AREA CICGAGA_AGENT_AREA;
    public void MP(SCCGWA SCCGWA, DDCUOPAC DDCUOPAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCUOPAC = DDCUOPAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZUOPAC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        CICGAGA_AGENT_AREA = new CICGAGA_AGENT_AREA_AGENT_AREA();
        IBS.init(SCCGWA, CICGAGA_AGENT_AREA);
        IBS.CPY2CLS(SCCGWA, SCCGWA.AGENT_AREA_PTR, CICGAGA_AGENT_AREA);
        CEP.TRC(SCCGWA, WS_T_AC_NUM);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUOPAC.SOCIAL_CARD_FLG);
        if (DDCUOPAC.SOCIAL_CARD_FLG == '8') {
            B001_CHECK_AC_EXIST();
            if (pgmRtn) return;
        }
        B010_CHECK_DATA_VALIDITY();
        if (pgmRtn) return;
        B110_GET_PRD_INF_GK();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCUOPAC.PSBK_FLG);
        if (!(DDCUOPAC.ACNO_FLG == 'B' 
            || DDCUOPAC.ACNO_FLG == 'D')) {
            B030_GENERATE_ACNO();
            if (pgmRtn) return;
            B090_CUS_RLT_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDCUOPAC.AC);
        B060_GENERATE_ACAC_PROC();
        if (pgmRtn) return;
        if (DDCUOPAC.ACNO_FLG == 'B' 
            && !DDCUOPAC.CCY.equalsIgnoreCase("156")) {
            B066_CHECK_CUS_AC();
            if (pgmRtn) return;
        }
        if (DDCUOPAC.ACO_AC_TYP == '1' 
            && DDCUOPAC.AC.trim().length() > 0) {
            B067_CHECK_CARD_PROC();
            if (pgmRtn) return;
        }
        B070_CRT_DD_CCY_PROC();
        if (pgmRtn) return;
        B050_CRT_AC_LINK_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "FARMANHAO");
        CEP.TRC(SCCGWA, DDVMPRD.VAL.GM_FLG);
        if (DDVMPRD.VAL.GM_FLG == 'Y' 
            || DDCUOPAC.ACO_AC_TYP != '5') {
            B130_INQ_GL_MASTER();
            if (pgmRtn) return;
            B150_WRITE_GL_MASTER_PROC();
            if (pgmRtn) return;
        }
        if (DDCUOPAC.ACNO_FLG != 'B') {
            B050_CRT_DD_AC_PROC();
            if (pgmRtn) return;
        }
        if (DDCUOPAC.TRT_CTLW == null) DDCUOPAC.TRT_CTLW = "";
        JIBS_tmp_int = DDCUOPAC.TRT_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) DDCUOPAC.TRT_CTLW += " ";
        if (DDCUOPAC.TRT_CTLW.substring(0, 1).equalsIgnoreCase("1")) {
            B053_CRT_AGENT_PROC();
            if (pgmRtn) return;
            B054_REG_LMT_INFO();
            if (pgmRtn) return;
        }
        if (!(DDCUOPAC.ACNO_FLG == 'B' 
            || DDCUOPAC.ACNO_FLG == 'D')) {
            B190_PSBK_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDCUOPAC.AC_ATTR_LAB);
        CEP.TRC(SCCGWA, DDCUOPAC.CARD_FLG);
        CEP.TRC(SCCGWA, DDCUOPAC.PSBK_FLG);
        if (DDCUOPAC.CARD_FLG == '2' 
            && DDCUOPAC.PSBK_FLG == '2' 
            && (DDCUOPAC.AC_ATTR_LAB.equalsIgnoreCase("OSA") 
            || DDCUOPAC.AC_ATTR_LAB.equalsIgnoreCase("NRA") 
            || DDCUOPAC.AC_ATTR_LAB.equalsIgnoreCase("OTH"))) {
            B260_OSA_AC_PAY_MTH_REC_PROC();
            if (pgmRtn) return;
        }
        if (DDCUOPAC.ACNO_FLG != 'B') {
            B100_CRT_BP_OCAC_PROC_CUS();
            if (pgmRtn) return;
        }
        B100_CRT_BP_OCAC_PROC();
        if (pgmRtn) return;
        B170_NON_FIN_HIS_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.AGENT_FLG == 'Y' 
            && DDCUOPAC.CARD_FLG != '1') {
            B180_AGENT_INF_PORC();
            if (pgmRtn) return;
        }
    }
    public void B001_CHECK_AC_EXIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQCIAC);
        CICQCIAC.FUNC = '1';
        CEP.TRC(SCCGWA, DDCUOPAC.CI_NO);
        CICQCIAC.DATA.CI_NO = DDCUOPAC.CI_NO;
        CICQCIAC.DATA.CNTRCT_TYP = "103";
        S000_CALL_CIZQCIAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQCIAC.DATA.ACR_AREA.ENTY_INF[1-1].ENTY_NO);
        if (CICQCIAC.DATA.ACR_AREA.ENTY_INF[1-1].ENTY_NO.trim().length() > 0) {
            DDCUOPAC.AC = CICQCIAC.DATA.ACR_AREA.ENTY_INF[1-1].ENTY_NO;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_DATA_VALIDITY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUOPAC.CI_NO);
        CEP.TRC(SCCGWA, DDCUOPAC.PROD_CODE);
        CEP.TRC(SCCGWA, DDCUOPAC.AC_TYP);
        CEP.TRC(SCCGWA, DDCUOPAC.PSBK_NO);
        CEP.TRC(SCCGWA, DDCUOPAC.CARD_NO);
        CEP.TRC(SCCGWA, DDCUOPAC.CARD_TYP);
        CEP.TRC(SCCGWA, DDCUOPAC.PSWD);
        CEP.TRC(SCCGWA, DDCUOPAC.REMARK);
        CEP.TRC(SCCGWA, DDCUOPAC.CUS_MGR);
        CEP.TRC(SCCGWA, DDCUOPAC.REG_CENT);
        CEP.TRC(SCCGWA, DDCUOPAC.SUB_BIZ);
        CEP.TRC(SCCGWA, DDCUOPAC.POST_AC);
        CEP.TRC(SCCGWA, DDCUOPAC.TRT_CTLW);
        B010_10_CHK_INPUT_DATA();
        if (pgmRtn) return;
        B010_30_CHK_CI_INF();
        if (pgmRtn) return;
        B010_50_CHK_PRD_INF();
        if (pgmRtn) return;
        B010_60_CHK_TX_BR();
        if (pgmRtn) return;
    }
    public void B010_10_CHK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDCUOPAC.CI_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CI_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCUOPAC.PROD_CODE.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_NEW_PROD_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCUOPAC.PSBK_FLG == '1' 
            && DDCUOPAC.PSBK_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PSBK_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCUOPAC.PSBK_FLG == '1' 
            && DDCUOPAC.AC_TYP == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_TYP_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCUOPAC.PSBK_FLG == '1' 
            && (DDCUOPAC.AC_TYP != 'A' 
            && DDCUOPAC.AC_TYP != 'B')) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_TYP_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCUOPAC.AC_TYP == ' ') {
            DDCUOPAC.AC_TYP = 'B';
        }
        if ((DDCUOPAC.BV_TYPE == '5' 
            || DDCUOPAC.BV_TYPE == 'B') 
            && DDCUOPAC.DRAW_MTH == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_DRAW_MTH_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCUOPAC.DRAW_MTH != ' ' 
            && DDCUOPAC.DRAW_MTH != '1' 
            && DDCUOPAC.DRAW_MTH != '2' 
            && DDCUOPAC.DRAW_MTH != '3' 
            && DDCUOPAC.DRAW_MTH != '4' 
            && DDCUOPAC.DRAW_MTH != '5') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_DRAW_MTH_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCUOPAC.CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCUOPAC.CCY_TYPE == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_TYPE_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDCUOPAC.ACNO_FLG);
        if (DDCUOPAC.ACNO_FLG == ' ') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_INPUT_ERR);
        }
        if (DDCUOPAC.TRT_CTLW == null) DDCUOPAC.TRT_CTLW = "";
        JIBS_tmp_int = DDCUOPAC.TRT_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) DDCUOPAC.TRT_CTLW += " ";
        if (DDCUOPAC.TRT_CTLW.substring(0, 1).equalsIgnoreCase("1")) {
            if (DDCUOPAC.BV_TYPE != '5' 
                && DDCUOPAC.BV_TYPE != 'B') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BV_TYPE_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCUOPAC.LMT_CCY.trim().length() == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCUOPAC.LMT_BAL == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCUOPAC.AGID_CTY.trim().length() == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCUOPAC.AGID_CNO.trim().length() == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCUOPAC.AGID_CNM.trim().length() == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_30_CHK_CI_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'C';
        CICCUST.DATA.CI_NO = DDCUOPAC.CI_NO;
        S000_CALL_CISOCUST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_SID_FLG);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_BIRTH_DT);
        WS_MAX_DT = SCCGWA.COMM_AREA.AC_DATE - 180000;
        CEP.TRC(SCCGWA, WS_MAX_DT);
        if (DDCUOPAC.TRT_CTLW == null) DDCUOPAC.TRT_CTLW = "";
        JIBS_tmp_int = DDCUOPAC.TRT_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) DDCUOPAC.TRT_CTLW += " ";
        if (CICCUST.O_DATA.O_BIRTH_DT <= WS_MAX_DT 
            && DDCUOPAC.TRT_CTLW.substring(0, 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ELDER_SAVE_AC_FORBID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDCUOPAC.AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICACCU.DATA.AC_CNM);
        CEP.TRC(SCCGWA, CICACCU.DATA.AC_ENM);
        IBS.init(SCCGWA, BPCFCSTS);
        BPCFCSTS.AP_MMO = K_APP_MMO;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (JIBS_tmp_str[0].equalsIgnoreCase("0265858")) {
            BPCFCSTS.TBL_NO = K_CI_STS_ELEC_AC;
        } else if (((DDCUOPAC.SOCIAL_CARD_FLG == 'Y' 
                || DDCUOPAC.SOCIAL_CARD_FLG == 'G' 
                || DDCUOPAC.OPEN_SOC_CARD_FLG == 'Y'))) {
            BPCFCSTS.AP_MMO = K_APP_MMO1;
            BPCFCSTS.TBL_NO = K_CI_STS_SOC_CARD;
        } else {
            BPCFCSTS.TBL_NO = K_CI_STS_TBL;
        }
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
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD.substring(0, 80));
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 101 - 1) + "000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000" + BPCFCSTS.STATUS_WORD.substring(101 + 99 - 1);
        CEP.TRC(SCCGWA, DDCUOPAC.SOCIAL_CARD_FLG);
        CEP.TRC(SCCGWA, DDCUOPAC.INFO_INCOMP_FLG);
        if (DDCUOPAC.SOCIAL_CARD_FLG == '8' 
            || DDCUOPAC.INFO_INCOMP_FLG == 'Y') {
            if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
            JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
            for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
            BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 24 - 1) + "0" + BPCFCSTS.STATUS_WORD.substring(24 + 1 - 1);
        }
        S000_CALL_BPZFCSTS();
        if (pgmRtn) return;
    }
    public void B010_50_CHK_PRD_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUOPAC.PSBK_FLG);
        CEP.TRC(SCCGWA, DDCUOPAC.PROD_CODE);
        IBS.init(SCCGWA, DDCIQPRD);
        IBS.init(SCCGWA, DDVMPRD);
        DDCIQPRD.INPUT_DATA.PROD_CODE = DDCUOPAC.PROD_CODE;
        CEP.TRC(SCCGWA, DDCUOPAC.PROD_CODE);
        CEP.TRC(SCCGWA, DDCIQPRD.INPUT_DATA.PROD_CODE);
        DDCIQPRD.DDVMPRD_PTR = DDVMPRD;
        DDCIQPRD.DDVMRAT_PTR = DDVMRAT;
        S000_CALL_DDZIQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDVMPRD.VAL.CUST_TYPE);
        CEP.TRC(SCCGWA, DDCIQPRD.OUTPUT_DATA.CUS_PER_FLG);
        CEP.TRC(SCCGWA, DDVMPRD.VAL.CASH_TXN_TYPE);
        CEP.TRC(SCCGWA, DDCUOPAC.DR_FLG);
        CEP.TRC(SCCGWA, DDVMPRD.VAL.CROS_DR_LMT);
        if (DDCUOPAC.DR_FLG == '1' 
            && DDVMPRD.VAL.CROS_DR_LMT != '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DR_FLG_SURPASS_PRODUCT);
        }
        IBS.init(SCCGWA, BPCPCKPD);
        BPCPCKPD.PRDT_CODE = DDCUOPAC.PROD_CODE;
        BPCPCKPD.CI_NO = DDCUOPAC.CI_NO;
        S000_CALL_BPZPCKPD();
        if (pgmRtn) return;
        WS_PROD_SURP_CNY = 'N';
        for (WS_CCY_N = 1; WS_CCY_N <= 12 
            && WS_PROD_SURP_CNY != 'Y'; WS_CCY_N += 1) {
            CEP.TRC(SCCGWA, DDVMPRD.VAL.CCY[WS_CCY_N-1]);
            CEP.TRC(SCCGWA, DDCUOPAC.CCY);
            if (DDVMPRD.VAL.CCY[WS_CCY_N-1].equalsIgnoreCase(DDCUOPAC.CCY)) {
                WS_PROD_SURP_CNY = 'Y';
            }
        }
    }
    public void B010_60_CHK_TX_BR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQORG.TRA_TYP);
        CEP.TRC(SCCGWA, BPCPQORG.ATTR);
        if (BPCPQORG.ATTR != '2' 
            && BPCPQORG.ATTR != '3') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_BR_NOT_PQORG_ATTR);
        }
        CEP.TRC(SCCGWA, BPCPQORG.FX_BUSI);
        if (!DDCUOPAC.CCY.equalsIgnoreCase("156") 
            && BPCPQORG.FX_BUSI.equalsIgnoreCase("00")) {
            if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_TR_BR_NO_FX_AUTH);
            } else {
                IBS.init(SCCGWA, BPCPFXOG);
                BPCPFXOG.INFO.FUNC = 'B';
                BPCPFXOG.DATA_INFO.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                S000_CALL_BPZPFXOG();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCPFXOG.DATA_INFO.FX_BR);
                if (BPCPFXOG.DATA_INFO.FX_BR == 0) {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_TR_BR_NO_FX_AUTH);
                }
            }
        }
    }
    public void B030_GENERATE_ACNO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCCGAC);
        BPCCGAC.DATA.AC_KIND = '1';
        BPCCGAC.DATA.CI_AC_TYPE = '1';
        BPCCGAC.DATA.CI_AC_FLG = '6';
        S000_CALL_BPZGACNO();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCCGAC.DATA.CI_AC);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_SID_FLG);
        CEP.TRC(SCCGWA, BPCPQORG.TRA_TYP);
        if (!BPCPQORG.TRA_TYP.equalsIgnoreCase("00")) {
            if (CICCUST.O_DATA.O_SID_FLG == '1') {
                DDCUOPAC.AC_ATTR_LAB = "FTI";
            } else {
                DDCUOPAC.AC_ATTR_LAB = "FTF";
            }
        }
        if (DDCUOPAC.AC_ATTR_LAB.equalsIgnoreCase("FTI")) {
            WS_ACNO_NO_1.WS_NRA_HEAD = "FTI";
            WS_ACNO_NO = IBS.CLS2CPY(SCCGWA, WS_ACNO_NO_1);
            WS_ACNO_NO_1.WS_NRA_ACNO = BPCCGAC.DATA.CI_AC;
            WS_ACNO_NO = IBS.CLS2CPY(SCCGWA, WS_ACNO_NO_1);
        } else {
            if (DDCUOPAC.AC_ATTR_LAB.equalsIgnoreCase("FTF")) {
                WS_ACNO_NO_1.WS_NRA_HEAD = "FTF";
                WS_ACNO_NO = IBS.CLS2CPY(SCCGWA, WS_ACNO_NO_1);
                WS_ACNO_NO_1.WS_NRA_ACNO = BPCCGAC.DATA.CI_AC;
                WS_ACNO_NO = IBS.CLS2CPY(SCCGWA, WS_ACNO_NO_1);
            } else {
                WS_ACNO_NO = BPCCGAC.DATA.CI_AC;
                IBS.CPY2CLS(SCCGWA, WS_ACNO_NO, WS_ACNO_NO_1);
            }
        }
        DDCUOPAC.AC = WS_ACNO_NO;
        CEP.TRC(SCCGWA, DDCUOPAC.AC);
    }
    public void B060_GENERATE_ACAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCCGAC);
        BPCCGAC.DATA.AC_KIND = '2';
        BPCCGAC.DATA.ACO_AC_FLG = '8';
        if (DDCUOPAC.ACO_AC_TYP == '3' 
            || DDCUOPAC.ACO_AC_TYP == '4') {
            BPCCGAC.DATA.ACO_AC_FLG = '9';
        }
        BPCCGAC.DATA.ACO_AC_MMO = "11";
        BPCCGAC.DATA.ACO_AC_DEF = 234;
        S000_CALL_BPZGACNO();
        if (pgmRtn) return;
        DDCUOPAC.ACAC = BPCCGAC.DATA.ACO_AC;
        CEP.TRC(SCCGWA, DDCUOPAC.ACAC);
    }
    public void B065_CALL_EAZUOPEN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EACUOPEN);
        EACUOPEN.FUNC = '1';
        EACUOPEN.PROD_CODE = DDCUOPAC.PROD_CODE;
        EACUOPEN.REQ_SYS = SCCGWA.COMM_AREA.CHNL;
        if (DDCUOPAC.ACO_AC_TYP == '3') {
            EACUOPEN.AC_FLG = '2';
        } else {
            EACUOPEN.AC_FLG = '3';
        }
        CEP.TRC(SCCGWA, EACUOPEN.PROD_CODE);
        CEP.TRC(SCCGWA, EACUOPEN.REQ_SYS);
        CEP.TRC(SCCGWA, EACUOPEN.AC_FLG);
        S000_CALL_EAZUOPEN();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, EACUOPEN.OPEN_BR);
        CEP.TRC(SCCGWA, EACUOPEN.OWNER_BR);
    }
    public void B066_CHECK_CUS_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = DDCUOPAC.AC;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
            IBS.init(SCCGWA, CICQACRL);
            CICQACRL.DATA.REL_AC_NO = DDCUOPAC.AC;
            CICQACRL.DATA.AC_REL = "12";
            CICQACRL.FUNC = 'I';
            S000_CALL_CIZQACRL();
            if (pgmRtn) return;
            if (CICQACRL.RC.RC_CODE == 0) {
                WS_CARD_PSBK_FLG = 'Y';
            }
        }
    }
    public void B067_CHECK_CARD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUCINF);
        DCCUCINF.CARD_NO = DDCUOPAC.AC;
        S000_CALL_DCZUCINF();
        if (pgmRtn) return;
        WS_EC_CARD = DDCUOPAC.AC;
        if (DCCUCINF.CARD_LNK_TYP == '2') {
            WS_EC_CARD = DCCUCINF.PRIM_CARD_NO;
        }
    }
    public void B190_PSBK_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIPSBK);
        DDCIPSBK.FUNC = 'A';
        DDCIPSBK.AC = DDCUOPAC.AC;
        DDCIPSBK.CARD_NO = DDCUOPAC.CARD_NO;
        DDCIPSBK.PSBK_NO = DDCUOPAC.PSBK_NO;
        DDCIPSBK.ID_TYPE = CICCUST.O_DATA.O_ID_TYPE;
        DDCIPSBK.ID_NO = CICCUST.O_DATA.O_ID_NO;
        DDCIPSBK.AC_CNAME = CICCUST.O_DATA.O_CI_NM;
        DDCIPSBK.AC_ENAME = CICCUST.O_DATA.O_CI_ENM;
        DDCIPSBK.PAY_MTH = DDCUOPAC.DRAW_MTH;
        DDCIPSBK.PSWD = DDCUOPAC.PSWD;
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        DDCIPSBK.VCH_TYPE = DDRCCY.STS_WORD.substring(2 - 1, 2 + 1 - 1).charAt(0);
        CEP.TRC(SCCGWA, DDCIPSBK.VCH_TYPE);
        CEP.TRC(SCCGWA, DDCUOPAC.DRAW_MTH);
        CEP.TRC(SCCGWA, DDCUOPAC.PSWD);
        CEP.TRC(SCCGWA, DDCIPSBK.ID_TYPE);
        CEP.TRC(SCCGWA, DDCIPSBK.ID_NO);
        CEP.TRC(SCCGWA, DDCIPSBK.AC_CNAME);
        if (DDCUOPAC.DRAW_MTH == '1' 
            && DDCUOPAC.PSWD.trim().length() > 0) {
            DDCIPSBK.UCIP_FLG = 'Y';
        } else {
            DDCIPSBK.UCIP_FLG = 'N';
        }
        DDCIPSBK.BV_TYPE = DDCUOPAC.BV_TYPE;
        S000_CALL_DDZIPSBK();
        if (pgmRtn) return;
    }
    public void B050_CRT_DD_AC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCUOPAC.AC;
        DDRMST.OWNER_BK = BPCPORUP.DATA_INFO.BR;
        DDRMST.PROD_CODE = DDCUOPAC.PROD_CODE;
        DDRMST.AC_STS = 'N';
        DDRMST.AC_STS_WORD = "000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (DDVMPRD.VAL.AUFR_FLG == 'Y') {
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 63 - 1) + "1" + DDRMST.AC_STS_WORD.substring(63 + 1 - 1);
        }
        if (DDCUOPAC.ACO_AC_TYP == '8') {
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 2 - 1) + "1" + DDRMST.AC_STS_WORD.substring(2 + 1 - 1);
        }
        if ((CICCUST.O_DATA.O_REG_CNTY.equalsIgnoreCase("HKG") 
            || CICCUST.O_DATA.O_REG_CNTY.equalsIgnoreCase("MAC") 
            || CICCUST.O_DATA.O_REG_CNTY.equalsIgnoreCase("TWN")) 
            && DDCUOPAC.AC_TYP == 'B') {
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 23 - 1) + "1" + DDRMST.AC_STS_WORD.substring(23 + 1 - 1);
        }
        if (DDCUOPAC.AC_ATTR_LAB.equalsIgnoreCase("OSA") 
            || DDCUOPAC.AC_ATTR_LAB.equalsIgnoreCase("NRA") 
            || DDCUOPAC.AC_ATTR_LAB.equalsIgnoreCase("OTH") 
            || DDCUOPAC.AC_TYP == 'G') {
            DDRMST.CCY_FLG = 'S';
        } else {
            DDRMST.CCY_FLG = 'M';
        }
        DDRMST.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.OPEN_DP = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.BBR);
        CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.BR);
        DDRMST.OWNER_BR = BPCPORUP.DATA_INFO.BBR;
        DDRMST.OWNER_BRDP = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRMST.OPEN_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRMST.CARD_FLG = 'N';
        DDRMST.AC_TYPE = DDCUOPAC.AC_TYP;
        if ((DDCUOPAC.AC_ATTR_LAB.equalsIgnoreCase("OSA") 
            || DDCUOPAC.AC_ATTR_LAB.equalsIgnoreCase("NRA")) 
            && CICCUST.O_DATA.O_CI_TYP == '1') {
            DDRMST.FRG_CODE = "3300";
        }
        DDRMST.FRG_IND = DDCUOPAC.AC_ATTR_LAB;
        DDRMST.FEE_METH = 'Y';
        DDRMST.CASH_FLG = DDVMPRD.VAL.CASH_TXN_TYPE;
        DDRMST.CROS_DR_FLG = DDVMPRD.VAL.CROS_DR_LMT;
        DDRMST.CROS_DR_FLG = DDCUOPAC.DR_FLG;
        DDRMST.CROS_CR_FLG = DDVMPRD.VAL.CROS_CR_LMT;
        DDRMST.CI_TYP = CICCUST.O_DATA.O_CI_TYP;
        DDRMST.AC_OIC_NO = DDCUOPAC.CUS_MGR;
        DDRMST.AC_OIC_CODE = DDCUOPAC.REG_CENT;
        DDRMST.SUB_DP = DDCUOPAC.SUB_BIZ;
        DDRMST.RLT_PROD_CODE = DDVMPRD.VAL.TD_PROD;
        DDRMST.YW_TYP = DDCUOPAC.YW_TYP;
        if (DDCUOPAC.SOCIAL_CARD_FLG == '8') {
            DDRMST.YW_TYP = "103";
        }
        DDRMST.CLOSE_DATE = 0;
        DDRMST.LAST_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRMST.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.LAST_FN_DATE = 0;
        DDRMST.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRMST.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRMST.FRG_TYPE = DDCUOPAC.FRG_TYPE;
        DDRMST.FRG_OPEN_NO = DDCUOPAC.FR_OP_NO;
        T000_WRITE_MST_PROC();
        if (pgmRtn) return;
    }
    public void B053_CRT_AGENT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRAGENT);
        DDRAGENT.KEY.CUS_AC = DDCUOPAC.AC;
        DDRAGENT.STS = '0';
        DDRAGENT.LMT_CCY = DDCUOPAC.LMT_CCY;
        DDRAGENT.LMT_BAL = DDCUOPAC.LMT_BAL;
        DDRAGENT.AGID_TYP = DDCUOPAC.AGID_CTY;
        DDRAGENT.AGID_NO = DDCUOPAC.AGID_CNO;
        DDRAGENT.AGID_NM = DDCUOPAC.AGID_CNM;
        DDRAGENT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRAGENT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRAGENT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRAGENT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DDTAGENT();
        if (pgmRtn) return;
    }
    public void B054_REG_LMT_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICPDTL);
        CICPDTL.FUNC = '0';
        CICPDTL.TYPE = "05";
        CICPDTL.LC_OBJ = DDCUOPAC.AC;
        CICPDTL.CUS_AC = DDCUOPAC.AC;
        CICPDTL.TX_TYPE = "01";
        CICPDTL.LVL = 33;
        CICPDTL.LC_CCY = DDCUOPAC.LMT_CCY;
        CICPDTL.DAY_STA = SCCGWA.COMM_AREA.AC_DATE;
        CICPDTL.DAY_END = 99991231;
        CICPDTL.DLY_AMT = DDCUOPAC.LMT_BAL;
        CICPDTL.TXN_AMT = DDCUOPAC.LMT_BAL;
        CICPDTL.DLY_VOL = 99999;
        CICPDTL.MLY_AMT = 99999999999999.99;
        CICPDTL.MLY_VOL = 99999;
        CICPDTL.SYY_AMT = 99999999999999.99;
        CICPDTL.YLY_AMT = 99999999999999.99;
        CICPDTL.TM_AMT = 99999999999999.99;
        CICPDTL.SE_AMT = 99999999999999.99;
        CICPDTL.LMT_AMT1 = 99999999999999.99;
        CICPDTL.LMT_AMT2 = 99999999999999.99;
        CICPDTL.LMT_AMT3 = 99999999999999.99;
        CICPDTL.LMT_AMT4 = 99999999999999.99;
        CICPDTL.BAL_AMT = 99999999999999.99;
        CICPDTL.LMT_STSW = "EEEEEEEEEEEEEEE";
        CICPDTL.TIMES50[1-1].CON_TYP1 = 17;
        CICPDTL.TIMES50[1-1].VAL = "0";
        CICPDTL.TIMES50[2-1].CON_TYP1 = 17;
        CICPDTL.TIMES50[2-1].VAL = "1";
        S000_CALL_CIZPDTL();
        if (pgmRtn) return;
    }
    public void B050_CRT_AC_LINK_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUOPAC.AC_TYPE);
        IBS.init(SCCGWA, CICSACAC);
        CICSACAC.FUNC = 'A';
        CICSACAC.DATA.ACAC_NO = DDCUOPAC.ACAC;
        CICSACAC.DATA.AGR_NO = DDCUOPAC.AC;
        CICSACAC.DATA.CCY = DDCUOPAC.CCY;
        CICSACAC.DATA.PROD_CD = DDCUOPAC.PROD_CODE;
        CICSACAC.DATA.FRM_APP = "DD";
        CICSACAC.DATA.ACAC_CTL = "00000000000000000000";
        if (DDCUOPAC.CCY.equalsIgnoreCase("156") 
            && DDCUOPAC.AC_TYP == 'A') {
            if ((DDCUOPAC.AC_TYPE == '1' 
                || DDCUOPAC.AC_TYPE == ' ')) {
                if (CICSACAC.DATA.ACAC_CTL == null) CICSACAC.DATA.ACAC_CTL = "";
                JIBS_tmp_int = CICSACAC.DATA.ACAC_CTL.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) CICSACAC.DATA.ACAC_CTL += " ";
                CICSACAC.DATA.ACAC_CTL = CICSACAC.DATA.ACAC_CTL.substring(0, 5 - 1) + "1" + CICSACAC.DATA.ACAC_CTL.substring(5 + 1 - 1);
            } else {
                if (CICSACAC.DATA.ACAC_CTL == null) CICSACAC.DATA.ACAC_CTL = "";
                JIBS_tmp_int = CICSACAC.DATA.ACAC_CTL.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) CICSACAC.DATA.ACAC_CTL += " ";
                JIBS_tmp_str[0] = "" + DDCUOPAC.AC_TYPE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                CICSACAC.DATA.ACAC_CTL = CICSACAC.DATA.ACAC_CTL.substring(0, 5 - 1) + JIBS_tmp_str[0] + CICSACAC.DATA.ACAC_CTL.substring(5 + 1 - 1);
            }
        }
        CEP.TRC(SCCGWA, CICSACAC.DATA.ACAC_CTL);
        if (DDCUOPAC.ACO_AC_TYP == '1') {
            if (CICSACAC.DATA.ACAC_CTL == null) CICSACAC.DATA.ACAC_CTL = "";
            JIBS_tmp_int = CICSACAC.DATA.ACAC_CTL.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) CICSACAC.DATA.ACAC_CTL += " ";
            CICSACAC.DATA.ACAC_CTL = "10" + CICSACAC.DATA.ACAC_CTL.substring(2);
            if (CICSACAC.DATA.ACAC_CTL == null) CICSACAC.DATA.ACAC_CTL = "";
            JIBS_tmp_int = CICSACAC.DATA.ACAC_CTL.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) CICSACAC.DATA.ACAC_CTL += " ";
            CICSACAC.DATA.ACAC_CTL = CICSACAC.DATA.ACAC_CTL.substring(0, 5 - 1) + "0" + CICSACAC.DATA.ACAC_CTL.substring(5 + 1 - 1);
            CICSACAC.DATA.AID = "003";
            CICSACAC.DATA.NOSEQ_FLG = 'Y';
        } else if (DDCUOPAC.ACO_AC_TYP == '2'
            || DDCUOPAC.ACO_AC_TYP == '5') {
            CICSACAC.DATA.CR_FLG = DDCUOPAC.CCY_TYPE;
        } else {
            if (CICSACAC.DATA.ACAC_CTL == null) CICSACAC.DATA.ACAC_CTL = "";
            JIBS_tmp_int = CICSACAC.DATA.ACAC_CTL.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) CICSACAC.DATA.ACAC_CTL += " ";
            CICSACAC.DATA.ACAC_CTL = "01" + CICSACAC.DATA.ACAC_CTL.substring(2);
            CICSACAC.DATA.CR_FLG = DDCUOPAC.CCY_TYPE;
        }
        CICSACAC.DATA.OPEN_DT = SCCGWA.COMM_AREA.AC_DATE;
        CICSACAC.DATA.OPN_BR = DDRCCY.OPEN_DP;
        S000_CALL_CIZSACAC();
        if (pgmRtn) return;
    }
    public void B051_CRT_DD_MCAD_PROC() throws IOException,SQLException,Exception {
        if (DDCUOPAC.SOCIAL_CARD_FLG == 'G') {
            IBS.init(SCCGWA, DDCSACCH);
            DDCSACCH.DATA.FUNC = 'A';
            DDCSACCH.DATA.AC = DDCUOPAC.AC;
            S000_CALL_DDZSACCH();
            if (pgmRtn) return;
        }
    }
    public void B070_CRT_DD_CCY_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUOPAC.ACO_AC_TYP);
        if (DDCUOPAC.ACO_AC_TYP == '1') {
            IBS.init(SCCGWA, DDRCCY);
            DDRCCY.CUS_AC = WS_EC_CARD;
            DDRCCY.CCY = "156";
            T000_READ_DDTCCY_FIRST();
            if (pgmRtn) return;
            WS_OPEN_BP = DDRCCY.OPEN_DP;
            WS_OWNER_BRDP = DDRCCY.OWNER_BRDP;
            WS_OWNER_BR = DDRCCY.OWNER_BR;
            WS_OWNER_BK = DDRCCY.OWNER_BK;
        }
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = DDCUOPAC.ACAC;
        DDRCCY.OWNER_BK = BPCPORUP.DATA_INFO.BR;
        DDRCCY.CUS_AC = DDCUOPAC.AC;
        DDRCCY.CCY = DDCUOPAC.CCY;
        DDRCCY.CCY_TYPE = DDCUOPAC.CCY_TYPE;
        CEP.TRC(SCCGWA, DDCUOPAC.ACO_AC_TYP);
        if (DDCUOPAC.SOCIAL_CARD_FLG == '8') {
            DDCUOPAC.ACO_AC_TYP = DDCUOPAC.SOCIAL_CARD_FLG;
        }
        DDRCCY.STS_WORD = "000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        CEP.TRC(SCCGWA, DDCUOPAC.ACO_AC_TYP);
        if (DDCUOPAC.CARD_FLG == '1' 
                && DDCUOPAC.ACO_AC_TYP != '1') {
            DDRCCY.AC_TYPE = '2';
            CEP.TRC(SCCGWA, DDCUOPAC.AC_TYPE);
            if (DDCUOPAC.AC_TYPE == '2') {
                DDRCCY.AC_TYPE = 'A';
            }
            if (DDCUOPAC.ACO_AC_TYP == '6') {
                DDRCCY.AC_TYPE = 'C';
            }
            if (DDCUOPAC.ACO_AC_TYP == '7') {
                DDRCCY.AC_TYPE = 'B';
            }
            if (DDCUOPAC.ACO_AC_TYP == '3') {
                DDRCCY.AC_TYPE = '4';
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 60 - 1) + "1" + DDRCCY.STS_WORD.substring(60 + 1 - 1);
            }
            if (DDCUOPAC.ACO_AC_TYP == '4') {
                DDRCCY.AC_TYPE = '5';
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 60 - 1) + "1" + DDRCCY.STS_WORD.substring(60 + 1 - 1);
            }
        } else if (DDCUOPAC.ACO_AC_TYP == '1') {
            DDRCCY.AC_TYPE = '6';
            DDRCCY.CCY_TYPE = ' ';
        } else if (DDCUOPAC.ACO_AC_TYP == '5') {
            DDRCCY.AC_TYPE = '7';
        } else if ((DDCUOPAC.AC_TYPE == 'J' 
                || DDCUOPAC.AC_TYPE == 'N' 
                || DDCUOPAC.AC_TYPE == 'O')) {
            DDRCCY.AC_TYPE = '3';
        } else if (DDCUOPAC.ACO_AC_TYP == '8') {
            DDRCCY.AC_TYPE = '8';
        } else {
            DDRCCY.AC_TYPE = '1';
        }
        DDRCCY.STS = 'N';
        if (DDVMPRD.VAL.GM_FLG == 'N') {
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 22 - 1) + "1" + DDRCCY.STS_WORD.substring(22 + 1 - 1);
        }
        if (DDCUOPAC.PSBK_FLG == '1' 
            || (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD") 
            && !DDCUOPAC.CCY.equalsIgnoreCase("156"))) {
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 2 - 1) + "1" + DDRCCY.STS_WORD.substring(2 + 1 - 1);
        }
        if (DDCUOPAC.SOCIAL_CARD_FLG == '8') {
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 2 - 1) + "3" + DDRCCY.STS_WORD.substring(2 + 1 - 1);
        }
        CEP.TRC(SCCGWA, WS_CARD_PSBK_FLG);
        if (WS_CARD_PSBK_FLG == 'Y') {
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 2 - 1) + "2" + DDRCCY.STS_WORD.substring(2 + 1 - 1);
        }
        DDRCCY.PROD_CODE = DDCUOPAC.PROD_CODE;
        DDRCCY.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.OPEN_DP = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRCCY.OPEN_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRCCY.OWNER_BR = BPCPORUP.DATA_INFO.BBR;
        DDRCCY.OWNER_BRDP = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        if (DDCUOPAC.ACO_AC_TYP == '1') {
            DDRCCY.OPEN_DP = WS_OPEN_BP;
            DDRCCY.OWNER_BRDP = WS_OWNER_BRDP;
            DDRCCY.OWNER_BR = WS_OWNER_BR;
            DDRCCY.OWNER_BK = WS_OWNER_BK;
        }
        CEP.TRC(SCCGWA, WS_OPEN_BP);
        CEP.TRC(SCCGWA, DDRCCY.OPEN_DP);
        if (DDCUOPAC.ACO_AC_TYP == '5') {
            B071_GET_OPEN_BR();
            if (pgmRtn) return;
        }
        if ((DDVMPRD.VAL.CAL_DINT_METH == '1' 
            || DDVMPRD.VAL.CAL_DINT_METH == '2') 
            && DDCUOPAC.ACNO_FLG != 'D') {
            DDRCCY.CINT_FLG = 'Y';
        } else {
            DDRCCY.CINT_FLG = 'N';
        }
        if (DDCUOPAC.ACO_AC_TYP == '3' 
            || DDCUOPAC.ACO_AC_TYP == '4') {
            if (EACUOPEN.OPEN_BR != 0) {
                DDRCCY.OPEN_DP = EACUOPEN.OPEN_BR;
            }
            if (EACUOPEN.OWNER_BR != 0) {
                DDRCCY.OWNER_BRDP = EACUOPEN.OWNER_BR;
            }
        }
        DDRCCY.POST_AC = DDCUOPAC.POST_AC;
        DDRCCY.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRCCY.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCUOPAC.AC;
        T000_READ_DDTMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRCCY.CCY);
        CEP.TRC(SCCGWA, DDRMST.AC_TYPE);
        if (!DDRCCY.CCY.equalsIgnoreCase("156")) {
            DDRCCY.AC_FT = 'W';
        }
        CEP.TRC(SCCGWA, DDRCCY.AC_FT);
        T000_WRITE_CCY_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRCCY.OPEN_DP);
        CEP.TRC(SCCGWA, WS_OPEN_BP);
    }
    public void B071_GET_OPEN_BR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.DATA.AC_NO = DDCUOPAC.AC;
        CICQACRL.DATA.AC_REL = "01";
        CICQACRL.FUNC = 'I';
        S000_CALL_CIZQACRL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
        if (CICQACRL.O_DATA.O_REL_AC_NO.trim().length() > 0) {
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = CICQACRL.O_DATA.O_REL_AC_NO;
            T000_READ_DDTMST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                DDRCCY.OPEN_DP = DDRMST.OPEN_DP;
                DDRCCY.OWNER_BRDP = DDRMST.OWNER_BRDP;
                DDRCCY.OWNER_BR = DDRMST.OWNER_BR;
                DDRCCY.OWNER_BK = DDRMST.OWNER_BK;
            }
        }
    }
    public void B110_GET_PRD_INF_GK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = DDCUOPAC.PROD_CODE;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
    }
    public void B090_CUS_RLT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSACR);
        CICSACR.FUNC = 'A';
        CEP.TRC(SCCGWA, DDCUOPAC.AC);
        CICSACR.DATA.AGR_NO = DDCUOPAC.AC;
        CEP.TRC(SCCGWA, CICSACR.DATA.AGR_NO);
        if (DDCUOPAC.JOINT_CARD_FLG == 'Y') {
            CICSACR.DATA.ENTY_TYP = '2';
        } else {
            CICSACR.DATA.ENTY_TYP = '1';
        }
        CICSACR.DATA.CNTRCT_TYP = "199";
        if (DDCUOPAC.CARD_FLG == '1') {
            CICSACR.DATA.CNTRCT_TYP = "299";
        }
        if (DDCUOPAC.AC_TYP == 'N' 
            || DDCUOPAC.AC_TYP == 'J' 
            || DDCUOPAC.AC_TYP == 'O') {
            CICSACR.DATA.CNTRCT_TYP = "033";
        }
        if (DDCUOPAC.AC_TYP == 'N' 
            || DDCUOPAC.AC_TYP == 'J' 
            || DDCUOPAC.AC_TYP == 'O') {
            CICSACR.CTL_FLG.EXP_FLG = 'Y';
            CICSACR.CTL_STSW = IBS.CLS2CPY(SCCGWA, CICSACR.CTL_FLG);
        }
        if (DDCUOPAC.SOCIAL_CARD_FLG == '8') {
            CICSACR.DATA.CNTRCT_TYP = "103";
        }
        CICSACR.DATA.CI_NO = CICCUST.O_DATA.O_CI_NO;
        CICSACR.DATA.AC_CNM = CICCUST.O_DATA.O_CI_NM;
        CICSACR.DATA.AC_ENM = CICCUST.O_DATA.O_CI_ENM;
        CICSACR.DATA.STSW = "10010000";
        CICSACR.DATA.PROD_CD = DDCUOPAC.PROD_CODE;
        CICSACR.DATA.FRM_APP = "DD";
        CICSACR.DATA.CCY = DDCUOPAC.CCY;
        if (DDCUOPAC.JOINT_CARD_FLG == 'Y') {
            CICSACR.DATA.SHOW_FLG = 'N';
        } else {
            CICSACR.DATA.SHOW_FLG = 'Y';
        }
        CICSACR.DATA.OPEN_DT = SCCGWA.COMM_AREA.AC_DATE;
        CICSACR.DATA.OPN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_CIZSACR();
        if (pgmRtn) return;
    }
    public void B130_INQ_GL_MASTER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCNGL);
        IBS.init(SCCGWA, BPCACAAC);
        BPCQCNGL.DAT.INPUT.CNTR_TYPE = BPCPQPRD.PRDT_MODEL;
        CEP.TRC(SCCGWA, BPCPQPRD.PRDT_MODEL);
        BPCQCNGL.DAT.INPUT.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCQCNGL.DAT.INPUT.OTH_PTR_LEN = 21;
        BPCACAAC.PROD_TYPE = DDCUOPAC.PROD_CODE;
        BPCACAAC.CI_TYPE = CICCUST.O_DATA.O_CI_TYP;
        BPCACAAC.FIN_TYP = CICCUST.O_DATA.O_FIN_TYPE;
        if (DDCUOPAC.AC_TYP != ' ') {
            BPCACAAC.AC_TYP = DDCUOPAC.AC_TYP;
        } else {
            BPCACAAC.AC_TYP = 'B';
        }
        BPCQCNGL.DAT.INPUT.OTH_PTR = BPCACAAC;
        S000_CALL_BPZQCNGL();
        if (pgmRtn) return;
    }
    public void B150_WRITE_GL_MASTER_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUOPAC.PROD_CODE);
        IBS.init(SCCGWA, BPCUCNGM);
        BPCUCNGM.KEY.AC = DDCUOPAC.ACAC;
        BPCUCNGM.KEY.CNTR_TYPE = DDCIQPRD.OUTPUT_DATA.PRDT_MODEL;
        BPCUCNGM.PROD_TYPE = DDCUOPAC.PROD_CODE;
        BPCUCNGM.DATA[1-1].GLMST = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[1-1].MSTNO;
        BPCUCNGM.DATA[2-1].GLMST = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[2-1].MSTNO;
        BPCUCNGM.FUNC = 'A';
        CEP.TRC(SCCGWA, BPCUCNGM.KEY.AC);
        CEP.TRC(SCCGWA, BPCUCNGM.KEY.CNTR_TYPE);
        CEP.TRC(SCCGWA, BPCUCNGM.PROD_TYPE);
        CEP.TRC(SCCGWA, BPCUCNGM.BR);
        CEP.TRC(SCCGWA, BPCUCNGM.DATA[1-1].GLMST);
        CEP.TRC(SCCGWA, BPCUCNGM.DATA[2-1].GLMST);
        S000_CALL_BPZUCNGM();
        if (pgmRtn) return;
    }
    public void B180_AGENT_INF_PORC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSAGEN);
        CICSAGEN.FUNC = 'A';
        CICSAGEN.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        CICSAGEN.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        CICSAGEN.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        CICSAGEN.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CICSAGEN.TLR_NO = SCCGWA.COMM_AREA.TL_ID;
        CICSAGEN.CI_NO = CICCUST.O_DATA.O_CI_NO;
        CICSAGEN.OUT_AC = DDCUOPAC.AC;
        CEP.TRC(SCCGWA, CICSAGEN.OUT_AC);
        CICSAGEN.ID_TYP = CICGAGA_AGENT_AREA.ID_TYP;
        CICSAGEN.ID_NO = CICGAGA_AGENT_AREA.ID_NO;
        CICSAGEN.AGENT_TP = "03";
        CICSAGEN.CI_NAME = CICGAGA_AGENT_AREA.CI_NM;
        CICSAGEN.PHONE = CICGAGA_AGENT_AREA.PHONE;
        S000_CALL_CIZSAGEN();
        if (pgmRtn) return;
    }
    public void B170_NON_FIN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.CI_NO = DDCUOPAC.CI_NO;
        BPCPNHIS.INFO.AC = DDCUOPAC.AC;
        BPCPNHIS.INFO.CCY = DDCUOPAC.CCY;
        BPCPNHIS.INFO.CCY_TYPE = DDCUOPAC.CCY_TYPE;
        BPCPNHIS.INFO.TX_TOOL = "" + DDCUOPAC.CCY_TYPE;
        JIBS_tmp_int = BPCPNHIS.INFO.TX_TOOL.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) BPCPNHIS.INFO.TX_TOOL = "0" + BPCPNHIS.INFO.TX_TOOL;
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.FMT_ID = K_HIS_CPB_NAME;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.TX_TYP_CD = K_HIS_MMO;
        if (DDCUOPAC.ACO_AC_TYP == '7') {
            BPCPNHIS.INFO.TX_TYP_CD = "PE05";
        }
        if (DDCUOPAC.ACO_AC_TYP == '3' 
            || DDCUOPAC.ACO_AC_TYP == '4') {
            BPCPNHIS.INFO.TX_TYP_CD = "PE01";
        }
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B260_OSA_AC_PAY_MTH_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIPSBK);
        DDCIPSBK.FUNC = 'F';
        DDCIPSBK.AC = DDCUOPAC.AC;
        DDCIPSBK.PAY_MTH = DDCUOPAC.DRAW_MTH;
        DDCIPSBK.UCIP_FLG = 'N';
        DDCIPSBK.ID_TYPE = CICCUST.O_DATA.O_ID_TYPE;
        DDCIPSBK.ID_NO = CICCUST.O_DATA.O_ID_NO;
        DDCIPSBK.AC_CNAME = CICCUST.O_DATA.O_CI_NM;
        DDCIPSBK.AC_ENAME = CICCUST.O_DATA.O_CI_ENM;
        CEP.TRC(SCCGWA, DDCIPSBK.ID_TYPE);
        CEP.TRC(SCCGWA, DDCIPSBK.ID_NO);
        CEP.TRC(SCCGWA, DDCIPSBK.AC_CNAME);
        S000_CALL_DDZIPSBK();
        if (pgmRtn) return;
    }
    public void R000_CHK_BR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = WS_BR;
    }
    public void B100_CRT_BP_OCAC_PROC_CUS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSOCAC);
        BPCSOCAC.FUNC = 'C';
        BPCSOCAC.AC = DDCUOPAC.AC;
        BPCSOCAC.STS = 'N';
        BPCSOCAC.WORK_TYP = "01";
        if (DDCUOPAC.AC_TYP == 'A') {
            if (BPCSOCAC.CAL_TYP == null) BPCSOCAC.CAL_TYP = "";
            JIBS_tmp_int = BPCSOCAC.CAL_TYP.length();
            for (int i=0;i<2-JIBS_tmp_int;i++) BPCSOCAC.CAL_TYP += " ";
            JIBS_tmp_str[0] = "" + DDCUOPAC.AC_TYP;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            BPCSOCAC.CAL_TYP = JIBS_tmp_str[0] + BPCSOCAC.CAL_TYP.substring(1);
            if (BPCSOCAC.CAL_TYP == null) BPCSOCAC.CAL_TYP = "";
            JIBS_tmp_int = BPCSOCAC.CAL_TYP.length();
            for (int i=0;i<2-JIBS_tmp_int;i++) BPCSOCAC.CAL_TYP += " ";
            JIBS_tmp_str[0] = "" + DDCUOPAC.AC_TYPE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            BPCSOCAC.CAL_TYP = BPCSOCAC.CAL_TYP.substring(0, 2 - 1) + JIBS_tmp_str[0] + BPCSOCAC.CAL_TYP.substring(2 + 1 - 1);
        } else {
            BPCSOCAC.CAL_TYP = "" + DDCUOPAC.AC_TYP;
            JIBS_tmp_int = BPCSOCAC.CAL_TYP.length();
            for (int i=0;i<1-JIBS_tmp_int;i++) BPCSOCAC.CAL_TYP = "0" + BPCSOCAC.CAL_TYP;
        }
        CEP.TRC(SCCGWA, BPCSOCAC.CAL_TYP);
        CEP.TRC(SCCGWA, DDRCCY.AC_TYPE);
        if (DDRCCY.AC_TYPE == '6') {
            BPCSOCAC.WORK_TYP = "22";
        }
        if (DDCUOPAC.ACO_AC_TYP == '5') {
            BPCSOCAC.WORK_TYP = "33";
            BPCSOCAC.CAL_TYP = "Q";
        }
        BPCSOCAC.CI_TYPE = CICCUST.O_DATA.O_CI_TYP;
        if (DDCUOPAC.PSBK_FLG == '1') {
            BPCSOCAC.BV_TYP = '4';
            BPCSOCAC.BV_NO = DDCUOPAC.PSBK_NO;
        }
        if (DDCUOPAC.CARD_FLG == '1') {
            IBS.init(SCCGWA, DCCUCINF);
            DCCUCINF.CARD_NO = DDCUOPAC.AC;
            CEP.TRC(SCCGWA, DCCUCINF.CARD_NO);
            S000_CALL_DCZUCINF();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DCCUCINF.SVR_RSC_CD);
            if (DCCUCINF.SVR_RSC_CD.equalsIgnoreCase("220")) {
                BPCSOCAC.BV_TYP = '1';
            } else if (DCCUCINF.SVR_RSC_CD.equalsIgnoreCase("120")) {
                BPCSOCAC.BV_TYP = '2';
            } else if (DCCUCINF.SVR_RSC_CD.equalsIgnoreCase("505")) {
                BPCSOCAC.BV_TYP = '8';
            } else {
            }
            CEP.TRC(SCCGWA, BPCSOCAC.BV_TYP);
        }
        BPCSOCAC.ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
        BPCSOCAC.CI_CNM = CICCUST.O_DATA.O_CI_NM;
        BPCSOCAC.ID_NO = CICCUST.O_DATA.O_ID_NO;
        BPCSOCAC.CCY = DDCUOPAC.CCY;
        BPCSOCAC.CCY_TYPE = DDCUOPAC.CCY_TYPE;
        BPCSOCAC.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSOCAC.CREATE_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCSOCAC.OPEN_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSOCAC.CREATE_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSOCAC.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        BPCSOCAC.PROD_CD = DDCUOPAC.PROD_CODE;
        BPCSOCAC.BR = DDRCCY.OPEN_DP;
        BPCSOCAC.AC_CNM = CICCUST.O_DATA.O_CI_NM;
        if (DDCUOPAC.ACO_AC_TYP == '5') {
            BPCSOCAC.AC_CNM = CICACCU.DATA.AC_CNM;
        }
        if (SCCGWA.COMM_AREA.AGENT_FLG == 'Y') {
            BPCSOCAC.OTH_RPT_NM = CICGAGA_AGENT_AREA.CI_NM;
            BPCSOCAC.OTH_ID_TYP = CICGAGA_AGENT_AREA.ID_TYP;
            BPCSOCAC.OTH_ID_NO = CICGAGA_AGENT_AREA.ID_NO;
        }
        BPCSOCAC.REMARK = DDCUOPAC.REMARK;
        S000_CALL_BPZSOCAC();
        if (pgmRtn) return;
    }
    public void B100_CRT_BP_OCAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSOCAC);
        BPCSOCAC.FUNC = 'C';
        BPCSOCAC.ACO_AC = DDCUOPAC.ACAC;
        BPCSOCAC.AC = DDCUOPAC.AC;
        BPCSOCAC.STS = 'N';
        BPCSOCAC.WORK_TYP = "01";
        if (DDCUOPAC.AC_TYP == 'A') {
            if (BPCSOCAC.CAL_TYP == null) BPCSOCAC.CAL_TYP = "";
            JIBS_tmp_int = BPCSOCAC.CAL_TYP.length();
            for (int i=0;i<2-JIBS_tmp_int;i++) BPCSOCAC.CAL_TYP += " ";
            JIBS_tmp_str[0] = "" + DDCUOPAC.AC_TYP;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            BPCSOCAC.CAL_TYP = JIBS_tmp_str[0] + BPCSOCAC.CAL_TYP.substring(1);
            if (BPCSOCAC.CAL_TYP == null) BPCSOCAC.CAL_TYP = "";
            JIBS_tmp_int = BPCSOCAC.CAL_TYP.length();
            for (int i=0;i<2-JIBS_tmp_int;i++) BPCSOCAC.CAL_TYP += " ";
            JIBS_tmp_str[0] = "" + DDCUOPAC.AC_TYPE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            BPCSOCAC.CAL_TYP = BPCSOCAC.CAL_TYP.substring(0, 2 - 1) + JIBS_tmp_str[0] + BPCSOCAC.CAL_TYP.substring(2 + 1 - 1);
        } else {
            BPCSOCAC.CAL_TYP = "" + DDCUOPAC.AC_TYP;
            JIBS_tmp_int = BPCSOCAC.CAL_TYP.length();
            for (int i=0;i<1-JIBS_tmp_int;i++) BPCSOCAC.CAL_TYP = "0" + BPCSOCAC.CAL_TYP;
        }
        CEP.TRC(SCCGWA, BPCSOCAC.CAL_TYP);
        CEP.TRC(SCCGWA, DDRCCY.AC_TYPE);
        if (DDRCCY.AC_TYPE == '6') {
            BPCSOCAC.WORK_TYP = "22";
        }
        if (DDCUOPAC.ACO_AC_TYP == '5') {
            BPCSOCAC.WORK_TYP = "33";
            BPCSOCAC.CAL_TYP = "Q";
        }
        BPCSOCAC.CI_TYPE = CICCUST.O_DATA.O_CI_TYP;
        if (DDCUOPAC.PSBK_FLG == '1') {
            BPCSOCAC.BV_TYP = '4';
            BPCSOCAC.BV_NO = DDCUOPAC.PSBK_NO;
        }
        if (DDCUOPAC.CARD_FLG == '1') {
            IBS.init(SCCGWA, DCCUCINF);
            DCCUCINF.CARD_NO = DDCUOPAC.AC;
            CEP.TRC(SCCGWA, DCCUCINF.CARD_NO);
            S000_CALL_DCZUCINF();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DCCUCINF.SVR_RSC_CD);
            if (DCCUCINF.SVR_RSC_CD.equalsIgnoreCase("220")) {
                BPCSOCAC.BV_TYP = '1';
            } else if (DCCUCINF.SVR_RSC_CD.equalsIgnoreCase("120")) {
                BPCSOCAC.BV_TYP = '2';
            } else if (DCCUCINF.SVR_RSC_CD.equalsIgnoreCase("505")) {
                BPCSOCAC.BV_TYP = '8';
            } else {
            }
            CEP.TRC(SCCGWA, BPCSOCAC.BV_TYP);
        }
        BPCSOCAC.ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_NM);
        BPCSOCAC.CI_CNM = CICCUST.O_DATA.O_CI_NM;
        CEP.TRC(SCCGWA, BPCSOCAC.CI_CNM);
        BPCSOCAC.ID_NO = CICCUST.O_DATA.O_ID_NO;
        BPCSOCAC.CCY = DDCUOPAC.CCY;
        BPCSOCAC.CCY_TYPE = DDCUOPAC.CCY_TYPE;
        BPCSOCAC.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSOCAC.CREATE_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCSOCAC.OPEN_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSOCAC.CREATE_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSOCAC.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        BPCSOCAC.PROD_CD = DDCUOPAC.PROD_CODE;
        BPCSOCAC.BR = DDRCCY.OPEN_DP;
        BPCSOCAC.AC_CNM = CICCUST.O_DATA.O_CI_NM;
        if (DDCUOPAC.ACO_AC_TYP == '5') {
            BPCSOCAC.AC_CNM = CICACCU.DATA.AC_CNM;
        }
        if (SCCGWA.COMM_AREA.AGENT_FLG == 'Y') {
            BPCSOCAC.OTH_RPT_NM = CICGAGA_AGENT_AREA.CI_NM;
            BPCSOCAC.OTH_ID_TYP = CICGAGA_AGENT_AREA.ID_TYP;
            BPCSOCAC.OTH_ID_NO = CICGAGA_AGENT_AREA.ID_NO;
        }
        BPCSOCAC.REMARK = DDCUOPAC.REMARK;
        S000_CALL_BPZSOCAC();
        if (pgmRtn) return;
    }
    public void B400_WRITE_DDTVCH() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUOPAC.AC);
        IBS.init(SCCGWA, DDRVCH);
        DDRVCH.KEY.CUS_AC = DDCUOPAC.AC;
        DDRVCH.VCH_TYPE = '1';
        CEP.TRC(SCCGWA, DDCUOPAC.SOCIAL_CARD_FLG);
        if (DDCUOPAC.SOCIAL_CARD_FLG == '8') {
            DDRVCH.VCH_TYPE = '3';
        }
        CEP.TRC(SCCGWA, DDRVCH.VCH_TYPE);
        DDRVCH.PSWD_FLG = '1';
        DDRVCH.PAY_TYPE = DDCUOPAC.DRAW_MTH;
        DDRVCH.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRVCH.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDTVCH_RD = new DBParm();
        DDTVCH_RD.TableName = "DDTVCH";
        DDTVCH_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DDRVCH, DDTVCH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ACPB_REC_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_CCY_PROC() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_REC_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_MST_PROC() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_DDTAGENT() throws IOException,SQLException,Exception {
        DDTAGENT_RD = new DBParm();
        DDTAGENT_RD.TableName = "DDTAGENT";
        IBS.WRITE(SCCGWA, DDRAGENT, DDTAGENT_RD);
    }
    public void T000_READ_DDTCCY_FIRST() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.eqWhere = "CUS_AC,CCY";
        DDTCCY_RD.fst = true;
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void S000_CALL_DCZSSPEC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-S-DCZSSPEC", DCCSSPEC);
    }
    public void S000_CALL_BPZGACNO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-GENERTE-ACNO", BPCCGAC);
        CEP.TRC(SCCGWA, BPCCGAC.RC);
        if (BPCCGAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCCGAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_EAZUOPEN() throws IOException,SQLException,Exception {
        EAZUOPEN EAZUOPEN = new EAZUOPEN();
        EAZUOPEN.MP(SCCGWA, EACUOPEN);
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
        if (BPCFCSTS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFCSTS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZUCNGM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-MAINT-CNGM", BPCUCNGM);
        CEP.TRC(SCCGWA, BPCUCNGM.RC);
        if (BPCUCNGM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUCNGM.RC);
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
    public void S000_CALL_BPZPCKPD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-PRDT-COM-CHECK", BPCPCKPD);
        CEP.TRC(SCCGWA, BPCPCKPD.RC.RC_CODE);
        if (BPCPCKPD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPCKPD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZQCNGL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-CNGL", BPCQCNGL);
        CEP.TRC(SCCGWA, BPCQCNGL.RC);
        if (BPCQCNGL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCNGL.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-TLR-INF-QUERY", BPCFTLRQ);
    }
    public void S000_CALL_CIZMACR() throws IOException,SQLException,Exception {
    }
    public void S000_CALL_CIZSAGEN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SVR-CIZSAGEN", CICSAGEN);
        if (CICSAGEN.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSAGEN.RC);
        }
    }
    public void S000_CALL_CIZSACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACR", CICSACR);
        CEP.TRC(SCCGWA, CICSACR.RC.RC_CODE);
        if (CICSACR.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSACR.RC);
        }
    }
    public void S000_CALL_CIZSACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACAC", CICSACAC);
        if (CICSACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSACAC.RC);
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        if (CICQACRI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACRI.RC);
        }
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
        CEP.TRC(SCCGWA, CICQACRL.RC.RC_CODE);
    }
    public void S000_CALL_CIZPDTL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-PDTL", CICPDTL);
    }
    public void S000_CALL_BPZPFXOG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-FXOG-INFO", BPCPFXOG);
        if (BPCPFXOG.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPFXOG.RC);
        }
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
    }
    public void S000_CALL_DDZSACCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-ACCH", DDCSACCH);
    }
    public void S000_CALL_CISOCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_DDZIQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-DDPRD", DDCIQPRD);
        if (DDCIQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUUORD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-CPN-U-CARD-STS", DCCUUORD);
    }
    public void S000_CALL_DCZSCGET() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-SVR-DCZSCGET", DCCSCGET, true);
    }
    public void S000_CALL_DDZIPSBK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-PSBK-PROC", DDCIPSBK);
    }
    public void S000_CALL_BPZSOCAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-OCAC-INFO", BPCSOCAC);
        if (BPCSOCAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCSOCAC.RC);
        }
    }
    public void S000_CALL_CIZQCIAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST-ACR", CICQCIAC);
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF);
        if (DCCUCINF.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DCCUCINF.RC);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_INF() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INFO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
