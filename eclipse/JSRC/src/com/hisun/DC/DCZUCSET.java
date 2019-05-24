package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZUCSET {
    String JIBS_tmp_str[] = new String[10];
    DBParm DCTCDDAT_RD;
    brParm DCTPRDLT_BR = new brParm();
    DBParm DCTCDORD_RD;
    DBParm DCTCRDLT_RD;
    boolean pgmRtn = false;
    String CPN_BPZPQPRD = "BP-P-QUERY-PRDT-INFO";
    String CPN_DCZPQPRD = "DC-P-QUERY-PROD";
    String CPN_CIZMACR = "CI-MAIN-ACR";
    String CPN_REC_NHIS = "BP-REC-NHIS";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_ERR_MSG = " ";
    int WS_TMP_DATE = 0;
    int WS_TMP_DAYS = 0;
    String WS_BV_CD_NO = " ";
    char WS_FEE_FREE_FLG = ' ';
    char WS_ACNO_TYPE = ' ';
    String WS_APP_BAT_NO = " ";
    int WS_CNT = 0;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCRPRDLT DCRPRDLT = new DCRPRDLT();
    DCRCRDLT DCRCRDLT = new DCRCRDLT();
    DCRCDORD DCRCDORD = new DCRCDORD();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DCCHTMPL DCCHTMPL = new DCCHTMPL();
    DCCHTMPL DCCOTMPL = new DCCHTMPL();
    DCCHTMPL DCCNTMPL = new DCCHTMPL();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    DCCPQPRD DCCDQPRD = new DCCPQPRD();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    DCCUCSET DCCUCSET;
    public void MP(SCCGWA SCCGWA, DCCUCSET DCCUCSET) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCUCSET = DCCUCSET;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZUCSET return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B011_CHECK_SUB_INF();
        if (pgmRtn) return;
        B020_CARD_GEN();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DCCUCSET.CARD_PROD_CD.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PROD_CODE_MISSING;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_PROD_CODE_MISSING, DCCUCSET.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = DCCUCSET.CARD_PROD_CD;
        CEP.TRC(SCCGWA, DCCUCSET.CARD_PROD_CD);
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, BPCPQPRD.EFF_DATE);
        if (SCCGWA.COMM_AREA.AC_DATE < BPCPQPRD.EFF_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PRDT_NOT_EFFECT;
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PRDT_NOT_EFFECT, DCCUCSET.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.AC_DATE > BPCPQPRD.STOP_SOLD_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PRDT_STOP_SOLD;
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PRDT_STOP_SOLD, DCCUCSET.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCCDQPRD);
        if (BPCPQPRD.PARM_CODE.trim().length() > 0) {
            DCCDQPRD.VAL.KEY.PROD_CD = BPCPQPRD.PARM_CODE;
        } else {
            DCCDQPRD.VAL.KEY.PROD_CD = DCCUCSET.CARD_PROD_CD;
        }
        S000_CALL_DCZPQPRD();
        if (pgmRtn) return;
        if (DCCUCSET.CARD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NO_MISSING;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NO_MISSING, DCCUCSET.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCDQPRD.VAL.DATA.ADSC_TYPE == 'C' 
            && DCCUCSET.OWNER_CINO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CI_INFO_MISSING;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CI_INFO_MISSING, DCCUCSET.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.CARD_HLDR_CINO = DCCUCSET.HOLDER_CINO;
        DCRCDDAT.CARD_MEDI = '4';
        T000_READFIRST_DCTCDDAT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DCRCDORD);
        DCRCDORD.KEY.CARD_NO = DCCUCSET.CARD_NO;
        T000_STARTBR_DCTCDORD_FIRST();
        if (pgmRtn) return;
        WS_APP_BAT_NO = DCRCDORD.APP_BAT_NO;
    }
    public void B020_CARD_GEN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCUCSET.CARD_NO;
        CEP.TRC(SCCGWA, DCRCDDAT.KEY.CARD_NO);
        DCRCDDAT.PRIM_CARD_NO = DCCUCSET.PRIM_CD_NO;
        DCRCDDAT.HOLD_AC_FLG = DCCUCSET.HOLD_AC_FLG;
        DCRCDDAT.PROD_LMT_FLG = DCCUCSET.PROD_LMT_FLG;
        if (DCCUCSET.SNAME_TRAN_FLG == ' ') {
            DCRCDDAT.SAME_NAME_TFR_FLG = 'Y';
        } else {
            DCRCDDAT.SAME_NAME_TFR_FLG = DCCUCSET.SNAME_TRAN_FLG;
        }
        if (DCCUCSET.DNAME_TRAN_FLG == ' ') {
            DCRCDDAT.DIFF_NAME_TFR_FLG = 'Y';
        } else {
            DCRCDDAT.DIFF_NAME_TFR_FLG = DCCUCSET.DNAME_TRAN_FLG;
        }
        if (DCCUCSET.OUT_DRAW_FLG == ' ') {
            DCRCDDAT.DRAW_OVER_FLG = 'Y';
        } else {
            DCRCDDAT.DRAW_OVER_FLG = DCCUCSET.OUT_DRAW_FLG;
        }
        if (DCCUCSET.LNK_TYP == '3') {
            DCRCDDAT.CARD_LNK_TYP = '1';
        } else {
            DCRCDDAT.CARD_LNK_TYP = DCCUCSET.LNK_TYP;
        }
        CEP.TRC(SCCGWA, DCRCDDAT.CARD_LNK_TYP);
        DCRCDDAT.PROD_CD = DCCUCSET.CARD_PROD_CD;
        DCRCDDAT.CARD_OWN_CINO = DCCUCSET.OWNER_CINO;
        DCRCDDAT.CARD_HLDR_CINO = DCCUCSET.HOLDER_CINO;
        CEP.TRC(SCCGWA, DCRCDDAT.PROD_CD);
        CEP.TRC(SCCGWA, DCRCDDAT.CARD_OWN_CINO);
        CEP.TRC(SCCGWA, DCRCDDAT.CARD_HLDR_CINO);
        if (DCCUCSET.MEDIA == ' ') {
            DCRCDDAT.CARD_MEDI = '3';
            if (DCCDQPRD.VAL.DATA.SVR_RSC_CD.equalsIgnoreCase("220")) {
                DCRCDDAT.CARD_MEDI = '2';
            }
            if (DCCDQPRD.VAL.DATA.SVR_RSC_CD.equalsIgnoreCase("120")) {
                DCRCDDAT.CARD_MEDI = '1';
            }
        } else {
            DCRCDDAT.CARD_MEDI = DCCUCSET.MEDIA;
        }
        CEP.TRC(SCCGWA, DCRCDDAT.CARD_MEDI);
        if (DCCUCSET.CARD_TYPE == '0') {
            DCRCDDAT.CARD_STS = 'N';
        } else if (DCCUCSET.CARD_TYPE == '1') {
            DCRCDDAT.CARD_STS = '0';
        } else {
        }
        CEP.TRC(SCCGWA, DCRCDDAT.CARD_STS);
        DCRCDDAT.CARD_STS = DCCUCSET.CARD_STS;
        DCRCDDAT.ANU_FEE_FREE = DCCUCSET.ANU_FEE_FREE;
        if (DCCUCSET.OWN_BR == 0) {
            DCRCDDAT.ISSU_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            DCRCDDAT.CLT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        } else {
            DCRCDDAT.ISSU_BR = DCCUCSET.OWN_BR;
            DCRCDDAT.CLT_BR = DCCUCSET.OWN_BR;
        }
        DCRCDDAT.ISSU_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.EXP_DT = DCCUCSET.CERT_EXP_DATE;
        DCRCDDAT.MOVE_FLG = 'N';
        CEP.TRC(SCCGWA, WS_APP_BAT_NO);
        if (WS_APP_BAT_NO.equalsIgnoreCase("999999999999")) {
            DCRCDDAT.MOVE_FLG = 'Y';
        }
        CEP.TRC(SCCGWA, DCRCDDAT.CARD_STS);
        CEP.TRC(SCCGWA, DCRCDDAT.ISSU_BR);
        CEP.TRC(SCCGWA, DCRCDDAT.CLT_BR);
        CEP.TRC(SCCGWA, DCRCDDAT.ISSU_DT);
        CEP.TRC(SCCGWA, "KIA IS HERE1");
        DCRCDDAT.EXP_DT = DCCUCSET.CERT_EXP_DATE;
        CEP.TRC(SCCGWA, SCCCLDT.DATE1);
        CEP.TRC(SCCGWA, SCCCLDT.MTHS);
        CEP.TRC(SCCGWA, DCRCDDAT.EXP_DT);
        CEP.TRC(SCCGWA, "KIA IS HERE2");
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = SCCGWA.COMM_AREA.AC_DATE;
        SCCCLDT.MTHS = 12;
        CEP.TRC(SCCGWA, SCCCLDT);
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        CEP.TRC(SCCGWA, SCCCLDT.RC);
        if (SCCCLDT.RC != 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_INPUT_ERR;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_INPUT_ERR, DCCUCSET.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        DCRCDDAT.ANU_FEE_NXT = SCCCLDT.DATE2;
        WS_BV_CD_NO = DCCUCSET.BV_CD_NO;
        if ((WS_BV_CD_NO.equalsIgnoreCase("168") 
            || WS_BV_CD_NO.equalsIgnoreCase("426") 
            || WS_BV_CD_NO.equalsIgnoreCase("430") 
            || WS_BV_CD_NO.equalsIgnoreCase("432") 
            || WS_BV_CD_NO.equalsIgnoreCase("431") 
            || WS_BV_CD_NO.equalsIgnoreCase("429") 
            || WS_BV_CD_NO.equalsIgnoreCase("036") 
            || WS_BV_CD_NO.equalsIgnoreCase("425")) 
            || WS_FEE_FREE_FLG == 'Y') {
            DCRCDDAT.ANNUAL_FEE_FREE = 'Y';
            DCRCDDAT.ANU_FEE_FREE = 999;
        } else {
            DCRCDDAT.ANNUAL_FEE_FREE = 'N';
        }
        CEP.TRC(SCCGWA, SCCCLDT.DATE1);
        CEP.TRC(SCCGWA, SCCCLDT.MTHS);
        CEP.TRC(SCCGWA, DCRCDDAT.ANU_FEE_NXT);
        DCRCDDAT.LAST_TXN_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRCDDAT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRCDDAT.TRAN_PIN_DAT = DCCUCSET.CARD_PSW;
        CEP.TRC(SCCGWA, DCCUCSET.CARD_TYP);
        CEP.TRC(SCCGWA, DCCUCSET.AC_TYPE);
        DCRCDDAT.CARD_TYP = DCCUCSET.CARD_TYP;
        DCRCDDAT.CARD_CLS_PROD = DCCUCSET.CARD_CLS_PROD;
        DCRCDDAT.BV_CD_NO = DCCUCSET.BV_CD_NO;
        DCRCDDAT.ACNO_TYPE = DCCUCSET.AC_TYPE;
        DCRCDDAT.AC_OIC_NO = DCCUCSET.CUS_MGR;
        DCRCDDAT.AC_OIC_CODE = DCCUCSET.REG_CENT;
        DCRCDDAT.SUB_DP = DCCUCSET.SUB_BIZ;
        CEP.TRC(SCCGWA, DCCUCSET.DB_FREE);
        if (DCCUCSET.DB_FREE != ' ') {
            if (DCCUCSET.DB_FREE != 'Y' 
                && DCCUCSET.DB_FREE != 'C' 
                && DCCUCSET.DB_FREE != 'N') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DBINPUT_ERR;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_DBINPUT_ERR, DCCUCSET.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                DCRCDDAT.DOUBLE_FREE_FLG = DCCUCSET.DB_FREE;
            }
        } else {
            DCRCDDAT.DOUBLE_FREE_FLG = 'Y';
        }
        if (DCCUCSET.TRAN_WITH_CARD != ' ') {
            if (DCCUCSET.TRAN_WITH_CARD != 'Y' 
                && DCCUCSET.TRAN_WITH_CARD != 'C' 
                && DCCUCSET.TRAN_WITH_CARD != 'N') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TRAN_INPUT_ERR;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_TRAN_INPUT_ERR, DCCUCSET.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                DCRCDDAT.TRAN_WITH_CARD = DCCUCSET.TRAN_WITH_CARD;
            }
        } else {
            DCRCDDAT.TRAN_WITH_CARD = 'Y';
        }
        if (DCCUCSET.TRAN_NO_CARD != ' ') {
            if (DCCUCSET.TRAN_NO_CARD != 'Y' 
                && DCCUCSET.TRAN_NO_CARD != 'C' 
                && DCCUCSET.TRAN_NO_CARD != 'N') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NOCARD_INPUT_ERR;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_NOCARD_INPUT_ERR, DCCUCSET.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                DCRCDDAT.TRAN_NO_CARD = DCCUCSET.TRAN_NO_CARD;
            }
        } else {
            DCRCDDAT.TRAN_NO_CARD = 'Y';
        }
        if (DCCUCSET.TRAN_CRS_BOR != ' ') {
            if (DCCUCSET.TRAN_CRS_BOR != 'Y' 
                && DCCUCSET.TRAN_CRS_BOR != 'C' 
                && DCCUCSET.TRAN_CRS_BOR != 'N') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CRS_INPUT_ERR;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CRS_INPUT_ERR, DCCUCSET.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                DCRCDDAT.TRAN_CRS_BOR = DCCUCSET.TRAN_CRS_BOR;
            }
        } else {
            DCRCDDAT.TRAN_CRS_BOR = 'Y';
        }
        if (DCCUCSET.TRAN_ATM_FLG != ' ') {
            if (DCCUCSET.TRAN_ATM_FLG != 'Y' 
                && DCCUCSET.TRAN_ATM_FLG != 'C' 
                && DCCUCSET.TRAN_ATM_FLG != 'N') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ATM_INPUT_ERR;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_ATM_INPUT_ERR, DCCUCSET.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                DCRCDDAT.TRAN_ATM_FLG = DCCUCSET.TRAN_ATM_FLG;
            }
        } else {
            DCRCDDAT.TRAN_ATM_FLG = 'Y';
        }
        CEP.TRC(SCCGWA, DCCUCSET.TRAN_WITH_CARD);
        CEP.TRC(SCCGWA, DCCUCSET.TRAN_NO_CARD);
        CEP.TRC(SCCGWA, DCCUCSET.TRAN_CRS_BOR);
        CEP.TRC(SCCGWA, DCCUCSET.TRAN_CRS_BOR);
        DCRCDDAT.PSW_TYP = 'N';
        CEP.TRC(SCCGWA, DCRCDDAT.DOUBLE_FREE_FLG);
        DCRCDDAT.CARD_STSW = "ZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROESZEROES";
        DCRCDDAT.SF_FLG = DCCUCSET.SF_FLG;
        DCRCDDAT.CVV_FLG = 'Y';
        if (DCCDQPRD.VAL.DATA.PSW_FLG == 'Y') {
            DCRCDDAT.PVK_TYP = 'C';
        } else {
            DCRCDDAT.PVK_TYP = 'N';
        }
        if (DCCUCSET.OPEN_CHNL.trim().length() == 0) {
            DCRCDDAT.OPEN_CHNL = SCCGWA.COMM_AREA.CHNL;
        } else {
            DCRCDDAT.OPEN_CHNL = DCCUCSET.OPEN_CHNL;
        }
        CEP.TRC(SCCGWA, DCRCDDAT.LAST_TXN_DT);
        CEP.TRC(SCCGWA, DCRCDDAT.UPDTBL_DATE);
        CEP.TRC(SCCGWA, DCRCDDAT.UPDTBL_TLR);
        CEP.TRC(SCCGWA, DCRCDDAT);
        T000_ADD_DCTCDDAT();
        if (pgmRtn) return;
    }
    public void B011_CHECK_SUB_INF() throws IOException,SQLException,Exception {
        if (DCCUCSET.LNK_TYP != '1' 
            && DCCUCSET.PRIM_CD_NO.trim().length() > 0) {
            IBS.init(SCCGWA, DCRCDDAT);
            DCRCDDAT.KEY.CARD_NO = DCCUCSET.PRIM_CD_NO;
            T000_READ_DCTCDDAT();
            if (pgmRtn) return;
            if (DCRCDDAT.ACNO_TYPE != '1') {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_NOT_I_ERROR);
            }
            WS_ACNO_TYPE = DCRCDDAT.ACNO_TYPE;
            DCCUCSET.AC_TYPE = WS_ACNO_TYPE;
        }
    }
    public void B040_SETTING_CARD_LIMIT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRPRDLT);
        CEP.TRC(SCCGWA, "========UCSET00=======");
        CEP.TRC(SCCGWA, DCCUCSET.CARD_PROD_CD);
        DCRPRDLT.KEY.PROD_CD = DCCUCSET.CARD_PROD_CD;
        T000_STARTBR_DCTPRDLT();
        if (pgmRtn) return;
        T000_READNEXT_DCTPRDLT();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, DCRPRDLT.TXN_LMT_AMT);
            CEP.TRC(SCCGWA, DCRPRDLT.DLY_LMT_AMT);
            CEP.TRC(SCCGWA, DCRPRDLT.DLY_LMT_VOL);
            CEP.TRC(SCCGWA, DCRPRDLT.MLY_LMT_AMT);
            CEP.TRC(SCCGWA, DCRPRDLT.MLY_LMT_VOL);
            CEP.TRC(SCCGWA, DCRPRDLT.SYY_LMT_AMT);
            CEP.TRC(SCCGWA, DCRPRDLT.YLY_LMT_AMT);
            IBS.init(SCCGWA, DCRCRDLT);
            DCRCRDLT.KEY.CARD_NO = DCCUCSET.CARD_NO;
            DCRCRDLT.KEY.REGN_TYP = DCRPRDLT.KEY.REGN_TYP;
            DCRCRDLT.KEY.CHNL_NO = DCRPRDLT.KEY.CHNL_NO;
            DCRCRDLT.KEY.TXN_TYPE = DCRPRDLT.KEY.TXN_TYPE;
            DCRCRDLT.KEY.LMT_CCY = DCRPRDLT.KEY.LMT_CCY;
            DCRCRDLT.TXN_LMT_AMT = DCRPRDLT.TXN_LMT_AMT;
            DCRCRDLT.DLY_LMT_AMT = DCRPRDLT.DLY_LMT_AMT;
            DCRCRDLT.DLY_LMT_VOL = DCRPRDLT.DLY_LMT_VOL;
            DCRCRDLT.MLY_LMT_AMT = DCRPRDLT.MLY_LMT_AMT;
            DCRCRDLT.MLY_LMT_VOL = DCRPRDLT.MLY_LMT_VOL;
            DCRCRDLT.SYY_LMT_AMT = DCRPRDLT.SYY_LMT_AMT;
            DCRCRDLT.YLY_LMT_AMT = DCRPRDLT.YLY_LMT_AMT;
            DCRCRDLT.STA_DT = DCRPRDLT.STA_DT;
            DCRCRDLT.STA_TM = DCRPRDLT.STA_TM;
            DCRCRDLT.END_DT = DCRPRDLT.END_DT;
            DCRCRDLT.END_TM = DCRPRDLT.END_TM;
            DCRCRDLT.SE_LMT_AMT = DCRPRDLT.SE_LMT_AMT;
            DCRCRDLT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRCRDLT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            DCRCRDLT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRCRDLT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            CEP.TRC(SCCGWA, DCCUCSET.CARD_NO);
            CEP.TRC(SCCGWA, DCRPRDLT.KEY.REGN_TYP);
            CEP.TRC(SCCGWA, DCRPRDLT.KEY.CHNL_NO);
            CEP.TRC(SCCGWA, DCRPRDLT.KEY.TXN_TYPE);
            CEP.TRC(SCCGWA, DCRPRDLT.KEY.LMT_CCY);
            CEP.TRC(SCCGWA, DCRCRDLT.TXN_LMT_AMT);
            CEP.TRC(SCCGWA, DCRCRDLT.DLY_LMT_AMT);
            CEP.TRC(SCCGWA, DCRCRDLT.DLY_LMT_VOL);
            CEP.TRC(SCCGWA, DCRCRDLT.MLY_LMT_AMT);
            CEP.TRC(SCCGWA, DCRCRDLT.MLY_LMT_VOL);
            CEP.TRC(SCCGWA, DCRCRDLT.SYY_LMT_AMT);
            CEP.TRC(SCCGWA, DCRCRDLT.YLY_LMT_AMT);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
            T000_WRITE_DCTCRDLT();
            if (pgmRtn) return;
            T000_READNEXT_DCTPRDLT();
            if (pgmRtn) return;
        }
        T000_ENDBR_DCTPRDLT();
        if (pgmRtn) return;
    }
    public void B050_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCHTMPL);
        IBS.init(SCCGWA, DCCNTMPL);
        DCCNTMPL.CARD_NO = DCCUCSET.CARD_NO;
        DCCNTMPL.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCCNTMPL.LAST_USER = SCCGWA.COMM_AREA.TL_ID;
        DCCNTMPL.LAST_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        H000_HISTORY_RECORD();
        if (pgmRtn) return;
    }
    public void H000_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.TX_RMK = "SETTING CARD IMFORMATION   ";
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID = "DCCHTMPL";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_TOOL = DCCUCSET.CARD_NO;
        BPCPNHIS.INFO.CI_NO = DCRCDDAT.CARD_OWN_CINO;
        BPCPNHIS.INFO.FMT_ID_LEN = 770;
        BPCPNHIS.INFO.OLD_DAT_PT = DCCOTMPL;
        BPCPNHIS.INFO.NEW_DAT_PT = DCCNTMPL;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUCSET.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DCZPQPRD, DCCDQPRD);
        if (DCCDQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCDQPRD.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCDQPRD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUCSET.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BPZPQPRD, BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUCSET.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_ADD_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.WRITE(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CDDAT_EXIST;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CDDAT_EXIST, DCCUCSET.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DCTPRDLT() throws IOException,SQLException,Exception {
        DCTPRDLT_BR.rp = new DBParm();
        DCTPRDLT_BR.rp.TableName = "DCTPRDLT";
        DCTPRDLT_BR.rp.where = "PROD_CD = :DCRPRDLT.KEY.PROD_CD";
        IBS.STARTBR(SCCGWA, DCRPRDLT, this, DCTPRDLT_BR);
    }
    public void T000_READNEXT_DCTPRDLT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRPRDLT, this, DCTPRDLT_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_STARTBR_DCTCDORD_FIRST() throws IOException,SQLException,Exception {
        DCTCDORD_RD = new DBParm();
        DCTCDORD_RD.TableName = "DCTCDORD";
        DCTCDORD_RD.where = "CARD_NO = :DCRCDORD.KEY.CARD_NO";
        DCTCDORD_RD.fst = true;
        DCTCDORD_RD.order = "EXC_CARD_TMS DESC";
        IBS.READ(SCCGWA, DCRCDORD, this, DCTCDORD_RD);
    }
    public void T000_WRITE_DCTCRDLT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCRCRDLT.KEY.CARD_NO);
        CEP.TRC(SCCGWA, DCRCRDLT.KEY.CHNL_NO);
        CEP.TRC(SCCGWA, DCRCRDLT.KEY.TXN_TYPE);
        CEP.TRC(SCCGWA, DCRCRDLT.KEY.LMT_CCY);
        DCTCRDLT_RD = new DBParm();
        DCTCRDLT_RD.TableName = "DCTCRDLT";
        IBS.WRITE(SCCGWA, DCRCRDLT, DCTCRDLT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_ENDBR_DCTPRDLT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTPRDLT_BR);
    }
    public void T000_READFIRST_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.where = "CARD_HLDR_CINO = :DCRCDDAT.CARD_HLDR_CINO "
            + "AND CARD_MEDI < > :DCRCDDAT.CARD_MEDI "
            + "AND ACNO_TYPE < > :DCRCDDAT.ACNO_TYPE";
        DCTCDDAT_RD.fst = true;
        IBS.READ(SCCGWA, DCRCDDAT, this, DCTCDDAT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FEE_FREE_FLG = 'N';
        } else {
            WS_FEE_FREE_FLG = 'Y';
        }
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
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
        if (DCCUCSET.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DCCUCSET=");
            CEP.TRC(SCCGWA, DCCUCSET);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
