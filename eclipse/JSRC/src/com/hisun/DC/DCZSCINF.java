package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.BP.*;
import com.hisun.DD.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSCINF {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    SCZEXIT_WS_STAR_COMM WS_STAR_COMM;
    DBParm DCTCDDAT_RD;
    DBParm DCTCDORD_RD;
    DBParm DCTCITCD_RD;
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DC302";
    String K_PRDT_INF_MAINT = "BP-S-MAINT-PRDT-INFO";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    String WS_STR = " ";
    char WS_CARD_FLG = ' ';
    char WS_CARD_FLG2 = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCRCDORD DCRCDORD = new DCRCDORD();
    CICCUST CICCUST = new CICCUST();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCSMPRD BPCSMPRD = new BPCSMPRD();
    DCRPRDPR DCRPRDPR = new DCRPRDPR();
    SCCSTAR SCCSTAR = new SCCSTAR();
    DCCSCSTS DCCSCSTS = new DCCSCSTS();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    DCRCPARM DCRCPARM = new DCRCPARM();
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCPLOSS BPCPLOSS = new BPCPLOSS();
    BPRLOSS BPRLOSS = new BPRLOSS();
    CICOCUB CICOCUB = new CICOCUB();
    DCCF302 DCCF302 = new DCCF302();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    DCCPQPRD DCCDQPRD = new DCCPQPRD();
    DCRCITCD DCRCITCD = new DCRCITCD();
    DCCUPRCD DCCUPRCD = new DCCUPRCD();
    CICQCHDC CICQCHDC = new CICQCHDC();
    String WS_TABLE_CDNO = " ";
    String WS_TABLE_CCY = " ";
    int WS_TR_DT = 0;
    int WS_CNT2 = 0;
    int WS_CNT_SCAD = 0;
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCS0300 DCCS0300;
    BPRPRMT BPRPRMT = new BPRPRMT();
    public void MP(SCCGWA SCCGWA, DCCS0300 DCCS0300) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCS0300 = DCCS0300;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, "*** DCZSCINF START ***");
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "*** DCZSCINF END   ***");
        CEP.TRC(SCCGWA, "DCZSCINF return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        CEP.TRC(SCCGWA, "*** DCZSCINF AFTER SET ADDRESS ***");
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "*** DCZSCINF MAIN START ***");
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GET_CARD_INFO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DCCS0300.CARD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO, DCCS0300.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B011_CHECK_STSW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSCSTS);
        DCCSCSTS.CARD_NO = DCCS0300.CARD_NO;
        R000_UPDATE_STS_SPCDEAL();
        if (pgmRtn) return;
    }
    public void B020_GET_CARD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDDAT);
        IBS.init(SCCGWA, DCCF302);
        CEP.TRC(SCCGWA, DCCS0300.CARD_NO);
        DCRCDDAT.KEY.CARD_NO = DCCS0300.CARD_NO;
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "GO TO THERE");
        DCCF302.CARD_NO = DCRCDDAT.KEY.CARD_NO;
        WS_TABLE_CDNO = DCRCDDAT.KEY.CARD_NO;
        if (DCRCDDAT.CARD_STS == 'C') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_BE_CLOSED_W;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_BE_CLOSED_W, DCCS0300.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_TABLE_CDNO);
        DCCF302.ACNO_TYPE = DCRCDDAT.ACNO_TYPE;
        DCCF302.EXC_CARD_TMS = DCRCDDAT.EXC_CARD_TMS;
        DCCF302.CARD_LNK_TYP = DCRCDDAT.CARD_LNK_TYP;
        DCCF302.PROD_CD = DCRCDDAT.PROD_CD;
        DCCF302.CLS_PROD_CD = DCRCDDAT.CARD_CLS_PROD;
        DCCF302.BV_CD_NO = DCRCDDAT.BV_CD_NO;
        if (!DCCF302.PROD_CD.equalsIgnoreCase("1203010101")) {
            if (DCRCDDAT.MOVE_FLG == 'Y' 
                && DCCF302.ACNO_TYPE == ' ') {
            } else {
                R000_GET_NEW_CARD_STSW();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = DCCF302.PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        DCCF302.PROD_CD_NM = BPCPQPRD.PRDT_NAME;
        DCCF302.CARD_OWN_CINO = DCRCDDAT.CARD_OWN_CINO;
        if (DCRCDDAT.CARD_OWN_CINO.trim().length() > 0) {
            IBS.init(SCCGWA, CICCUST);
            CICCUST.FUNC = 'C';
            CICCUST.DATA.CI_NO = DCRCDDAT.CARD_OWN_CINO;
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
        }
        DCCF302.CARD_OWN_IDTYP = CICCUST.O_DATA.O_ID_TYPE;
        DCCF302.CARD_OWN_IDNO = CICCUST.O_DATA.O_ID_NO;
        DCCF302.CARD_OWN_ENM = CICCUST.O_DATA.O_CI_ENM;
        DCCF302.CARD_OWN_CNM = CICCUST.O_DATA.O_CI_NM;
        CEP.TRC(SCCGWA, DCCF302.CARD_OWN_ENM);
        CEP.TRC(SCCGWA, DCCF302.CARD_OWN_CNM);
        DCCF302.CARD_HLDR_CINO = DCRCDDAT.CARD_HLDR_CINO;
        IBS.init(SCCGWA, CICCUST);
        if (DCRCDDAT.CARD_HLDR_CINO.trim().length() > 0) {
            CICCUST.FUNC = 'C';
            CICCUST.DATA.CI_NO = DCRCDDAT.CARD_HLDR_CINO;
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            DCCF302.CARD_HLDR_IDTYP = CICCUST.O_DATA.O_ID_TYPE;
            DCCF302.CARD_HLDR_IDNO = CICCUST.O_DATA.O_ID_NO;
            DCCF302.CARD_HLDR_ENM = CICCUST.O_DATA.O_CI_ENM;
            DCCF302.CARD_HLDR_CNM = CICCUST.O_DATA.O_CI_NM;
            DCCF302.CARD_TEL_NO = CICCUST.O_DATA.O_TEL_NO;
            CEP.TRC(SCCGWA, DCCF302.CARD_HLDR_ENM);
            CEP.TRC(SCCGWA, DCCF302.CARD_HLDR_CNM);
        }
        CEP.TRC(SCCGWA, DCCF302.CARD_HLDR_CNM);
        DCCF302.CARD_MEDI = DCRCDDAT.CARD_MEDI;
        DCCF302.CARD_STS = DCRCDDAT.CARD_STS;
        DCCF302.CARD_STSW = DCRCDDAT.CARD_STSW;
        DCCF302.CARD_TYP = DCRCDDAT.CARD_TYP;
        if (DCRCDDAT.HOLD_AC_FLG == 'Y') {
            DCCF302.HOLD_AC_FLG = DCRCDDAT.HOLD_AC_FLG;
        } else {
            DCCF302.HOLD_AC_FLG = 'N';
        }
        DCCF302.PROD_LMT_FLG = DCRCDDAT.PROD_LMT_FLG;
        DCCF302.SAME_NAME_TFR_FLG = DCRCDDAT.SAME_NAME_TFR_FLG;
        DCCF302.DIFF_NAME_TFR_FLG = DCRCDDAT.DIFF_NAME_TFR_FLG;
        DCCF302.DRAW_OVER_FLG = DCRCDDAT.DRAW_OVER_FLG;
        DCCF302.PVK_TYP = DCRCDDAT.PVK_TYP;
        DCCF302.CVV_FLG = DCRCDDAT.CVV_FLG;
        DCCF302.DOUBLE_FREE_FLG = DCRCDDAT.DOUBLE_FREE_FLG;
        DCCF302.TRAN_WITH_CARD = DCRCDDAT.TRAN_WITH_CARD;
        DCCF302.TRAN_NO_CARD = DCRCDDAT.TRAN_NO_CARD;
        DCCF302.TRAN_CRS_BOR = DCRCDDAT.TRAN_CRS_BOR;
        DCCF302.TRAN_ATM_FLG = DCRCDDAT.TRAN_ATM_FLG;
        DCCF302.ANNUAL_FEE_FREE = DCRCDDAT.ANNUAL_FEE_FREE;
        if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
        JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
        if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
        JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
        if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
        JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
        if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
        JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
        if (DCRCDDAT.CARD_TYP.substring(0, 1).equalsIgnoreCase("1") 
            || DCRCDDAT.CARD_TYP.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
            || DCRCDDAT.CARD_TYP.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
            || DCRCDDAT.CARD_TYP.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("1")) {
            DCCF302.ANNUAL_FEE_FREE = 'Y';
        }
        DCCF302.ISSU_BR = DCRCDDAT.ISSU_BR;
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = DCRCDDAT.ISSU_BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        DCCF302.ISSU_NAME = BPCPQORG.CHN_NM;
        DCCF302.CLT_BR = DCRCDDAT.CLT_BR;
        DCCF302.EMBS_DT = DCRCDDAT.EMBS_DT;
        DCCF302.ISSU_DT = DCRCDDAT.ISSU_DT;
        DCCF302.EXP_DT = DCRCDDAT.EXP_DT;
        DCCF302.CLO_DT = DCRCDDAT.CLO_DT;
        DCCF302.ANU_FEE_NXT = DCRCDDAT.ANU_FEE_NXT;
        DCCF302.ANU_FEE_PCT = DCRCDDAT.ANU_FEE_PCT;
        DCCF302.ANU_FEE_FREE = DCRCDDAT.ANU_FEE_FREE;
        if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
        JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
        if (DCRCDDAT.CARD_TYP.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("1")) {
            DCCF302.ANU_FEE_FREE = 1;
        }
        DCCF302.ANU_FEE_ARG = DCRCDDAT.ANU_FEE_ARG;
        DCCF302.OPEN_CHNL = DCRCDDAT.OPEN_CHNL;
        CEP.TRC(SCCGWA, DCCF302.OPEN_CHNL);
        DCCF302.PIN_ERR_CNT = DCRCDDAT.PIN_ERR_CNT;
        DCCF302.PIN_LCK_DT = DCRCDDAT.PIN_LCK_DT;
        DCCF302.CVV_LCK_DT = DCRCDDAT.CVV_LCK_DT;
        DCCF302.LAST_TXN_DT = DCRCDDAT.LAST_TXN_DT;
        DCCF302.UPD_DT = DCRCDDAT.UPDTBL_DATE;
        DCCF302.UPD_TLR = DCRCDDAT.UPDTBL_TLR;
        if (BPCPQPRD.PARM_CODE.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PQPRD_PARM_CD_SPC;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_PQPRD_PARM_CD_SPC, DCCS0300.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCRPRDPR);
        IBS.init(SCCGWA, DCCUPRCD);
        DCCUPRCD.TX_TYPE = 'I';
        DCCUPRCD.DATA.KEY.CD.PARM_CODE = BPCPQPRD.PARM_CODE;
        S000_CALL_DCZUPRCD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCUPRCD.RC);
        if (DCCUPRCD.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DCCUPRCD.RC);
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUPRCD.DATA);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCRPRDPR);
        }
        CEP.TRC(SCCGWA, DCRPRDPR.DATA_TXT.ADSC_TYP);
        DCCF302.ADSC_TYPE = DCRPRDPR.DATA_TXT.ADSC_TYP;
        DCCF302.DFT_EXP = DCRPRDPR.DATA_TXT.DFT_EXP;
        DCCF302.SVR_RSC_CD = DCRPRDPR.DATA_TXT.SR_RC_CD;
        DCCF302.DD_PROD_CD = DCRPRDPR.DATA_TXT.DD_PROD;
        DCCF302.IC_PROD_CD = DCRPRDPR.DATA_TXT.IC_PROD;
        DCCF302.CARD_PHY_TYP = DCRPRDPR.DATA_TXT.PHY_TYP;
        CEP.TRC(SCCGWA, DCRCDDAT.JOIN_CUS_FLG);
        CEP.TRC(SCCGWA, DCRPRDPR.DATA_TXT.JOIN_CUS);
        if (WS_CARD_FLG == 'Y') {
            if (DCRCDDAT.JOIN_CUS_FLG == 'Y') {
                DCCF302.JOIN_CUS_FLG = 'Y';
            } else {
                DCCF302.JOIN_CUS_FLG = 'N';
            }
        } else {
            DCCF302.JOIN_CUS_FLG = DCRPRDPR.DATA_TXT.JOIN_CUS;
        }
        DCCF302.AC_HANG_FLG = DCRPRDPR.DATA_TXT.AC_HANG;
        DCCF302.MOBL_PAY_FLG = DCRPRDPR.DATA_TXT.MOBL_PAY;
        DCCF302.SUP_CARD_FLG = DCRPRDPR.DATA_TXT.SUP_CARD;
        DCCF302.PSW_FLG = DCRPRDPR.DATA_TXT.PSW_FLG;
        if (DCRPRDPR.KEY.CD.KEY1.PROD_CD.equalsIgnoreCase("CARD000013")) {
            DCCF302.CARD_PROD_FLG = 'Y';
        } else {
            if (DCRPRDPR.KEY.CD.KEY1.PROD_CD.equalsIgnoreCase("CARD000015")) {
                DCCF302.CARD_PROD_FLG = 'S';
            } else {
                if (DCRPRDPR.KEY.CD.KEY1.PROD_CD.equalsIgnoreCase("CARD000016")) {
                    DCCF302.CARD_PROD_FLG = 'G';
                } else {
                    DCCF302.CARD_PROD_FLG = 'P';
                }
            }
        }
        DCCF302.IC_APP_FLG = DCRPRDPR.DATA_TXT.APP_FLG;
        DCCF302.SAME_NAME_TFR_FLG = DCRCDDAT.SAME_NAME_TFR_FLG;
        DCCF302.DIFF_NAME_TFR_FLG = DCRCDDAT.DIFF_NAME_TFR_FLG;
        DCCF302.DRAW_OVER_FLG = DCRCDDAT.DRAW_OVER_FLG;
        DCCF302.SF_FLG = DCRCDDAT.SF_FLG;
        IBS.init(SCCGWA, DCRCITCD);
        DCRCITCD.KEY.CARD_NO = DCCS0300.CARD_NO;
        T000_READ_DCTCITCD();
        if (pgmRtn) return;
        if (WS_CARD_FLG2 == 'Y') {
            DCCF302.OWN_BR = DCRCITCD.OWN_BR;
        } else {
            DCCF302.OWN_BR = DCRCDDAT.ISSU_BR;
        }
        IBS.init(SCCGWA, CICQCHDC);
        CICQCHDC.FUNC = 'O';
        CICQCHDC.DATA.N_AGR_NO = DCCS0300.CARD_NO;
        CICQCHDC.DATA.N_ENTY_TYP = '2';
        S000_CALL_CIZQCHDC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQCHDC.DATA.O_AGR_NO);
        DCCF302.OLD_CARD = CICQCHDC.DATA.O_AGR_NO;
        if (DCRCDDAT.TRAN_PIN_DAT.trim().length() == 0) {
            DCCF302.PIN_FLG = 'N';
        } else {
            DCCF302.PIN_FLG = 'Y';
        }
        CEP.TRC(SCCGWA, DCCF302.OWN_BR);
        CEP.TRC(SCCGWA, DCCF302.SAME_NAME_TFR_FLG);
        CEP.TRC(SCCGWA, DCCF302.DIFF_NAME_TFR_FLG);
        CEP.TRC(SCCGWA, DCCF302.DRAW_OVER_FLG);
        B021_TRANSLATION_CARD_STSW();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCF302);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DCCF302;
        SCCFMT.DATA_LEN = 1785;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B021_TRANSLATION_CARD_STSW() throws IOException,SQLException,Exception {
        for (WS_CNT = 1; WS_CNT <= 64; WS_CNT += 1) {
            if (DCCF302.CARD_STSW == null) DCCF302.CARD_STSW = "";
            JIBS_tmp_int = DCCF302.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCCF302.CARD_STSW += " ";
            if (DCCF302.CARD_STSW.substring(WS_CNT - 1, WS_CNT + 1 - 1).equalsIgnoreCase("1")) {
                if (WS_STR == null) WS_STR = "";
                JIBS_tmp_int = WS_STR.length();
                for (int i=0;i<64-JIBS_tmp_int;i++) WS_STR += " ";
                WS_STR = WS_STR.substring(0, WS_CNT - 1) + "Y" + WS_STR.substring(WS_CNT + 1 - 1);
            } else {
                if (WS_STR == null) WS_STR = "";
                JIBS_tmp_int = WS_STR.length();
                for (int i=0;i<64-JIBS_tmp_int;i++) WS_STR += " ";
                WS_STR = WS_STR.substring(0, WS_CNT - 1) + "N" + WS_STR.substring(WS_CNT + 1 - 1);
            }
        }
        CEP.TRC(SCCGWA, WS_STR);
        DCCF302.CARD_STSW = WS_STR;
    }
    public void R000_GET_NEW_CARD_STSW() throws IOException,SQLException,Exception {
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(0, 1).equalsIgnoreCase("1")) {
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
            if (BPCPRMR.RC.RC_RTNCODE == 0) {
                IBS.init(SCCGWA, BPCPLOSS);
                BPCPLOSS.INFO.FUNC = 'I';
                BPCPLOSS.INFO.INDEX_FLG = "2";
                BPCPLOSS.DATA_INFO.AC = DCCS0300.CARD_NO;
                BPCPLOSS.DATA_INFO.STS = '2';
                S000_CALL_BPZPLOSS();
                if (pgmRtn) return;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPLOSS.RC);
                if (JIBS_tmp_str[0].equalsIgnoreCase("BP1993")) {
                    IBS.init(SCCGWA, DCRCDDAT);
                    DCRCDDAT.KEY.CARD_NO = DCCS0300.CARD_NO;
                    T000_READUPD_DCTCDDAT();
                    if (pgmRtn) return;
                    if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
                    JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
                    for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
                    DCRCDDAT.CARD_STSW = "0" + DCRCDDAT.CARD_STSW.substring(1);
                    DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                    T000_REWRITE_DCTCDDAT();
                    if (pgmRtn) return;
                }
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPLOSS.RC);
                if (!JIBS_tmp_str[0].equalsIgnoreCase("BP1993")) {
                    IBS.init(SCCGWA, SCCCLDT);
                    CEP.TRC(SCCGWA, BPCPLOSS.DATA_INFO.LOS_DT);
                    if (BPCPLOSS.DATA_INFO.LOS_DT == 0) {
                        SCCCLDT.DATE1 = SCCGWA.COMM_AREA.AC_DATE;
                    } else {
                        SCCCLDT.DATE1 = BPCPLOSS.DATA_INFO.LOS_DT;
                    }
                    SCCCLDT.DATE2 = SCCGWA.COMM_AREA.AC_DATE;
                    SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
                    SCSSCLDT1.MP(SCCGWA, SCCCLDT);
                    CEP.TRC(SCCGWA, SCCCLDT.RC);
                    if (SCCCLDT.RC == 0) {
                        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
                        CEP.TRC(SCCGWA, SCCCLDT.DAYS);
                        CEP.TRC(SCCGWA, DCRCPARM.DATA_TXT.LOS_TMP_DAYS);
                        if (SCCCLDT.DAYS >= DCRCPARM.DATA_TXT.LOS_TMP_DAYS) {
                            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
                            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
                            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
                            if (DCRCDDAT.CARD_STSW.substring(0, 1).equalsIgnoreCase("1")) {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
                                if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
                                JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
                                for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
                                DCRCDDAT.CARD_STSW = "0" + DCRCDDAT.CARD_STSW.substring(1);
                                B011_CHECK_STSW();
                                if (pgmRtn) return;
    } //FROM #ENDIF
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
    } //FROM #ENDIF
                            }
                        }
                        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
                        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
                        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
                        CEP.TRC(SCCGWA, DCRCDDAT.CARD_STSW.substring(0, 1));
                    }
                }
            }
        }
    }
    public void R000_UPDATE_STS_SPCDEAL() throws IOException,SQLException,Exception {
        WS_STAR_COMM = new SCZEXIT_WS_STAR_COMM();
        WS_STAR_COMM.STAR_PGM = "DCZSCSTS";
        WS_STAR_COMM.STAR_DATA = DCCSCSTS;
        IBS.START(SCCGWA, WS_STAR_COMM, false);
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCS0300.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-P-QUERY-PROD", DCCDQPRD);
        CEP.TRC(SCCGWA, DCCDQPRD.RC);
        if (DCCDQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCDQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CISOCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCS0300.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCS0300.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUPRCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-MPRD", DCCUPRCD);
        CEP.TRC(SCCGWA, DCCUPRCD.RC);
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        WS_CARD_FLG = 'N';
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CARD_FLG = 'Y';
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            if (DCRCDDAT.CARD_STSW.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1")) {
                IBS.init(SCCGWA, DDCIQBAL);
                DDCIQBAL.DATA.AC = DCCS0300.CARD_NO;
                DDCIQBAL.DATA.AID = "003";
                S000_CALL_DDZIQBAL();
                if (pgmRtn) return;
                DCCF302.NOT_INT_BAL = DDCIQBAL.DATA.NINT_BAL;
                DCCF302.ECASH_T_BAL = DDCIQBAL.DATA.CURR_BAL;
                CEP.TRC(SCCGWA, DCCF302.NOT_INT_BAL);
            }
        }
        if (WS_CARD_FLG == 'N') {
            IBS.init(SCCGWA, DCRCDORD);
            DCRCDORD.KEY.CARD_NO = DCCS0300.CARD_NO;
            if ("%".trim().length() == 0) DCRCDORD.KEY.EXC_CARD_TMS = 0;
            else DCRCDORD.KEY.EXC_CARD_TMS = Short.parseShort("%");
            DCTCDORD_RD = new DBParm();
            DCTCDORD_RD.TableName = "DCTCDORD";
            DCTCDORD_RD.where = "CARD_NO = :DCRCDORD.KEY.CARD_NO "
                + "AND EXC_CARD_TMS < 99";
            DCTCDORD_RD.fst = true;
            DCTCDORD_RD.order = "EXC_CARD_TMS DESC";
            IBS.READ(SCCGWA, DCRCDORD, this, DCTCDORD_RD);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.TRC(SCCGWA, DCRCDORD.KEY.CARD_NO);
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST, DCCS0300.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            DCRCDDAT.PROD_CD = DCRCDORD.CARD_PROD;
            DCRCDDAT.CARD_CLS_PROD = DCRCDORD.CARD_CLS_CD;
            DCRCDDAT.BV_CD_NO = DCRCDORD.BV_CD_NO;
            DCRCDDAT.EXC_CARD_TMS = DCRCDORD.KEY.EXC_CARD_TMS;
            DCRCDDAT.ISSU_BR = DCRCDORD.APP_BR;
            DCRCDDAT.CARD_STS = DCRCDORD.CRT_STS;
            DCRCDDAT.SF_FLG = DCRCDORD.SF_FLG;
        }
    }
    public void T000_READ_DCTCITCD() throws IOException,SQLException,Exception {
        WS_CARD_FLG2 = ' ';
        DCTCITCD_RD = new DBParm();
        DCTCITCD_RD.TableName = "DCTCITCD";
        DCTCITCD_RD.col = "OWN_BR";
        DCTCITCD_RD.where = "CARD_NO = :DCRCITCD.KEY.CARD_NO";
        DCTCITCD_RD.fst = true;
        IBS.READ(SCCGWA, DCRCITCD, this, DCTCITCD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CARD_FLG2 = 'Y';
        } else {
            WS_CARD_FLG2 = 'N';
        }
    }
    public void T000_READUPD_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.upd = true;
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.REWRITE(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
    }
    public void S000_CALL_BPZSMPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_PRDT_INF_MAINT, BPCSMPRD);
        CEP.TRC(SCCGWA, BPCSMPRD.RC.RC_CODE);
        if (BPCSMPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSMPRD.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSMPRD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCS0300.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-CCY-BAL", DDCIQBAL);
        if (DDCIQBAL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQBAL.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ        ", BPCPRMR);
        CEP.TRC(SCCGWA, BPCPRMR.RC);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCS0300.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPLOSS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-LOSS-INFO", BPCPLOSS);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCPLOSS.RC);
        if (BPCPLOSS.RC.RC_CODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase("BP1993")) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPLOSS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQCHDC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CHDC", CICQCHDC, true);
        CEP.TRC(SCCGWA, CICQCHDC.RC);
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
