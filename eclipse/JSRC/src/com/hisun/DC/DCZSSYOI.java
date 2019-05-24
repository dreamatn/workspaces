package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.DD.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSSYOI {
    DBParm DCTCDDAT_RD;
    DBParm DCTCTCDC_RD;
    DBParm DCTCDORD_RD;
    DBParm DCTCITCD_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT_9 = "DC928";
    String K_HIS_REMARK = "                            ";
    String K_HIS_COPYBOOK = "DCRCTCDC";
    String K_TBL_CITCD = "DCTCITCD";
    String K_TBL_CDDAT = "DCTCDDAT";
    String K_TBL_CDORD = "DCTCDORD";
    String K_TBL_CTCDC = "DCTCTCDC";
    String WS_ERR_MSG = " ";
    String WS_CARD_PSW = " ";
    String WS_CARD_PROD = " ";
    String WS_CARD_CLS = " ";
    int WS_CRT_DT = 0;
    char WS_SF_FLG = ' ';
    int WS_CERT_EXP_DATE = 0;
    String WS_CI_NO = " ";
    char WS_TBL_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCRCITCD DCRCITCD = new DCRCITCD();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCRCDORD DCRCDORD = new DCRCDORD();
    DCRCTCDC DCRCTCDC = new DCRCTCDC();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    DCCPQPRD DCCDQPRD = new DCCPQPRD();
    DCCFCDGG DCCFCDGG = new DCCFCDGG();
    CICCUST CICCUST = new CICCUST();
    CICSACR CICSACR = new CICSACR();
    DDCUOPAC DDCUOPAC = new DDCUOPAC();
    BPCSOCAC BPCSOCAC = new BPCSOCAC();
    DCCF280 DCCF280 = new DCCF280();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCS9280 DCCS9280;
    public void MP(SCCGWA SCCGWA, DCCS9280 DCCS9280) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCS9280 = DCCS9280;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSSYOI return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "GO");
        B100_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B115_CARD_PROCESS();
        if (pgmRtn) return;
        B200_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCS9280.SYNC_TYP);
        CEP.TRC(SCCGWA, DCCS9280.SOCIAL_NO);
        CEP.TRC(SCCGWA, DCCS9280.SOCIAL_CARD_NO);
        CEP.TRC(SCCGWA, DCCS9280.CARD_NO);
        CEP.TRC(SCCGWA, DCCS9280.E_CARD_NO);
        CEP.TRC(SCCGWA, DCCS9280.CI_NAME);
        CEP.TRC(SCCGWA, DCCS9280.COMPANY_NAME);
        CEP.TRC(SCCGWA, DCCS9280.ORG_SEQ);
        CEP.TRC(SCCGWA, DCCS9280.SOCIAL_ID);
        CEP.TRC(SCCGWA, DCCS9280.PHY_NO);
        CEP.TRC(SCCGWA, DCCS9280.ID_NO);
        CEP.TRC(SCCGWA, DCCS9280.ID_TYP);
        if (DCCS9280.SYNC_TYP.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_SYNC_TYPE_ERR);
        }
        if (DCCS9280.SOCIAL_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_SOCIAL_NO);
        }
        if (DCCS9280.CARD_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO);
        }
        if (DCCS9280.ID_TYP.trim().length() == 0 
            || DCCS9280.ID_NO.trim().length() == 0 
            || DCCS9280.CI_NAME.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CI_INFO_MISSING);
        }
    }
    public void B115_CARD_PROCESS() throws IOException,SQLException,Exception {
        if (DCCS9280.SYNC_TYP.equalsIgnoreCase("01") 
            || DCCS9280.SYNC_TYP.equalsIgnoreCase("02")) {
            B011_CHECK_CARD();
            if (pgmRtn) return;
            B012_CHECK_CARD_DIGIT();
            if (pgmRtn) return;
            B021_GREAT_RECORD();
            if (pgmRtn) return;
            B015_REGIST_CITACR();
            if (pgmRtn) return;
            B016_OPEN_DD_AC();
            if (pgmRtn) return;
            B022_REGIST_OCAC_INF();
            if (pgmRtn) return;
        }
        if (DCCS9280.SYNC_TYP.equalsIgnoreCase("03") 
            || DCCS9280.SYNC_TYP.equalsIgnoreCase("04") 
            || DCCS9280.SYNC_TYP.equalsIgnoreCase("05")) {
            B011_CHECK_CARD();
            if (pgmRtn) return;
            B012_CHECK_CARD_DIGIT();
            if (pgmRtn) return;
            B021_GREAT_RECORD();
            if (pgmRtn) return;
            B015_REGIST_CITACR();
            if (pgmRtn) return;
            if (DCCS9280.SYNC_TYP.equalsIgnoreCase("04")) {
                B018_UPDATE_DCTCTCDC();
                if (pgmRtn) return;
            }
        }
    }
    public void B011_CHECK_CARD() throws IOException,SQLException,Exception {
        if (DCCS9280.CARD_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NO_MISSING);
        }
        IBS.init(SCCGWA, DCRCDORD);
        DCRCDORD.KEY.CARD_NO = DCCS9280.CARD_NO;
        DCRCDORD.KEY.EXC_CARD_TMS = 0;
        T000_READ_DCTCDORD();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'N') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_NOT_PREMARK_CARD);
        }
        if (DCRCDORD.CRT_STS != '1') {
            if (DCRCDORD.CRT_STS != '3') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_READY;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_RECEIPTED;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        WS_CARD_PSW = DCRCDORD.TRAN_PIN_DAT;
        WS_CARD_PROD = DCRCDORD.CARD_PROD;
        WS_CARD_CLS = DCRCDORD.CARD_CLS_CD;
        WS_CRT_DT = DCRCDORD.CRT_DT;
        WS_SF_FLG = DCRCDORD.SF_FLG;
        WS_CERT_EXP_DATE = DCRCDORD.CERT_EXP_DATE;
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = WS_CARD_PROD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
        IBS.init(SCCGWA, DCCDQPRD);
        if (BPCPQPRD.PARM_CODE.trim().length() > 0) {
            DCCDQPRD.VAL.KEY.PROD_CD = BPCPQPRD.PARM_CODE;
        } else {
            DCCDQPRD.VAL.KEY.PROD_CD = WS_CARD_PROD;
        }
        S000_CALL_DCZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCDQPRD.VAL.DATA.IC_APP_FLG);
        CEP.TRC(SCCGWA, DCCDQPRD.VAL.DATA.IC_PROD_CD);
        CEP.TRC(SCCGWA, DCCDQPRD.VAL.DATA.DD_PROD_CD);
    }
    public void B012_CHECK_CARD_DIGIT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCFCDGG);
        DCCFCDGG.VAL.FUNC = 'C';
        DCCFCDGG.VAL.CARD_NO = DCCS9280.CARD_NO;
        CEP.TRC(SCCGWA, DCCFCDGG.VAL.CARD_NO);
        S000_CALL_DCZFCDGG();
        if (pgmRtn) return;
    }
    public void B018_UPDATE_DCTCTCDC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCS9280.ID_NO);
        CEP.TRC(SCCGWA, DCCS9280.ID_TYP);
        CEP.TRC(SCCGWA, DCCS9280.CI_NAME);
        IBS.init(SCCGWA, DCRCTCDC);
        DCRCTCDC.ID_TYP = DCCS9280.ID_TYP;
        DCRCTCDC.ID_NO = DCCS9280.ID_NO;
        T000_READUPD_DCTCTCDC();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'N') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_NOTFND_CINM_REPORT);
        }
        DCRCTCDC.NEW_CARD_NO = DCCS9280.CARD_NO;
        DCRCTCDC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCTCDC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_UPDATE_DCTCTCDC();
        if (pgmRtn) return;
    }
    public void B021_GREAT_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.DATA.ID_TYPE = DCCS9280.ID_TYP;
        CICCUST.DATA.ID_NO = DCCS9280.ID_NO;
        CICCUST.DATA.CI_NM = DCCS9280.CI_NAME;
        CICCUST.FUNC = 'B';
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_NO);
        WS_CI_NO = CICCUST.O_DATA.O_CI_NO;
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCS9280.CARD_NO;
        DCRCDDAT.ACNO_TYPE = '1';
        DCRCDDAT.EXC_CARD_TMS = 0;
        DCRCDDAT.CARD_LNK_TYP = '1';
        DCRCDDAT.PROD_CD = WS_CARD_PROD;
        DCRCDDAT.CARD_CLS_PROD = WS_CARD_CLS;
        DCRCDDAT.BV_CD_NO = " ";
        DCRCDDAT.CARD_OWN_CINO = WS_CI_NO;
        DCRCDDAT.CARD_HLDR_CINO = WS_CI_NO;
        DCRCDDAT.CARD_MEDI = '2';
        DCRCDDAT.CARD_STS = '3';
        DCRCDDAT.CARD_STSW = "ZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZERO";
        DCRCDDAT.CARD_TYP = "ZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZERO";
        DCRCDDAT.TRAN_PIN_DAT = WS_CARD_PSW;
        DCRCDDAT.QURY_PIN_DAT = " ";
        DCRCDDAT.PVK_TYP = 'C';
        DCRCDDAT.HOLD_AC_FLG = 'N';
        DCRCDDAT.PROD_LMT_FLG = 'Y';
        DCRCDDAT.CVV_FLG = 'Y';
        DCRCDDAT.SAME_NAME_TFR_FLG = 'Y';
        DCRCDDAT.DIFF_NAME_TFR_FLG = 'Y';
        DCRCDDAT.DRAW_OVER_FLG = 'N';
        DCRCDDAT.SF_FLG = WS_SF_FLG;
        DCRCDDAT.DOUBLE_FREE_FLG = 'C';
        DCRCDDAT.JOIN_CUS_FLG = 'N';
        DCRCDDAT.ANNUAL_FEE_FREE = 'Y';
        DCRCDDAT.PRIM_CARD_NO = " ";
        DCRCDDAT.ISSU_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DCRCDDAT.CLT_BR = 0;
        DCRCDDAT.EMBS_DT = WS_CRT_DT;
        DCRCDDAT.ISSU_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.EXP_DT = WS_CERT_EXP_DATE;
        DCRCDDAT.ANU_FEE_NXT = 99991231;
        DCRCDDAT.ANU_FEE_PCT = 0;
        DCRCDDAT.ANU_FEE_FREE = 999;
        DCRCDDAT.ANU_FEE_ARG = 0;
        DCRCDDAT.PIN_ERR_CNT = 0;
        DCRCDDAT.PIN_LCK_DT = 0;
        DCRCDDAT.CVV_LCK_DT = 0;
        DCRCDDAT.LAST_TXN_DT = 0;
        DCRCDDAT.MOVE_FLG = 'N';
        DCRCDDAT.PSW_TYP = 'N';
        DCRCDDAT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRCDDAT.OPEN_CHNL = SCCGWA.COMM_AREA.CHNL;
        T000_WRITE_DCTCDDAT();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'D') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DUPKEY);
        }
        IBS.init(SCCGWA, DCRCITCD);
        DCRCITCD.KEY.CARD_NO = DCCS9280.CARD_NO;
        DCRCITCD.ID_TYP = DCCS9280.ID_TYP;
        DCRCITCD.ID_NO = DCCS9280.ID_NO;
        DCRCITCD.CI_NM = DCCS9280.CI_NAME;
        DCRCITCD.SOCIAL_NO = DCCS9280.SOCIAL_NO;
        DCRCITCD.SOCIAL_CARD_NO = DCCS9280.SOCIAL_CARD_NO;
        DCRCITCD.E_CARD_NO = DCCS9280.E_CARD_NO;
        DCRCITCD.ORG_SEQ = DCCS9280.ORG_SEQ;
        DCRCITCD.ORG_NM = DCCS9280.COMPANY_NAME;
        DCRCITCD.SOCIAL_ID = DCCS9280.SOCIAL_ID;
        DCRCITCD.PHY_NO = DCCS9280.PHY_NO;
        DCRCITCD.STS = '0';
        DCRCITCD.CONF_STS = '1';
        DCRCITCD.CARD_TYP = DCCS9280.SYNC_TYP;
        DCRCITCD.BR_NM = DCCS9280.OWN_BR_NAME;
        DCRCITCD.SYNC_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCRCITCD.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCITCD.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRCITCD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCITCD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DCTCITCD();
        if (pgmRtn) return;
    }
    public void B015_REGIST_CITACR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSACR);
        CICSACR.FUNC = 'A';
        CICSACR.DATA.AGR_NO = DCCS9280.CARD_NO;
        CICSACR.DATA.ENTY_TYP = '2';
        CICSACR.DATA.STSW = "11011000";
        CICSACR.DATA.CI_NO = WS_CI_NO;
        CICSACR.DATA.PROD_CD = WS_CARD_PROD;
        CICSACR.DATA.OPN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CICSACR.DATA.OPEN_DT = SCCGWA.COMM_AREA.AC_DATE;
        CICSACR.DATA.SHOW_FLG = 'Y';
        CICSACR.DATA.FRM_APP = "DC";
        CICSACR.DATA.CNTRCT_TYP = "299";
        S000_CALL_CIZSACR();
        if (pgmRtn) return;
    }
    public void B016_OPEN_DD_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUOPAC);
        DDCUOPAC.CI_NO = WS_CI_NO;
        DDCUOPAC.CARD_TYP = '1';
        DDCUOPAC.PROD_CODE = DCCDQPRD.VAL.DATA.DD_PROD_CD;
        DDCUOPAC.AC_TYP = 'A';
        DDCUOPAC.PSBK_FLG = '2';
        DDCUOPAC.CARD_FLG = '1';
        DDCUOPAC.ACNO_FLG = 'B';
        DDCUOPAC.DRAW_MTH = ' ';
        DDCUOPAC.AC = DCCS9280.CARD_NO;
        DDCUOPAC.CCY = "156";
        DDCUOPAC.CCY_TYPE = '1';
        DDCUOPAC.AC_TYPE = '1';
        DDCUOPAC.INFO_INCOMP_FLG = 'Y';
        S000_CALL_DDZUOPAC();
        if (pgmRtn) return;
    }
    public void B022_REGIST_OCAC_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSOCAC);
        BPCSOCAC.FUNC = 'C';
        BPCSOCAC.AC = DCCS9280.CARD_NO;
        BPCSOCAC.STS = 'N';
        BPCSOCAC.WORK_TYP = "21";
        BPCSOCAC.CI_TYPE = '1';
        BPCSOCAC.BV_TYP = '1';
        BPCSOCAC.ID_TYP = DCCS9280.ID_TYP;
        BPCSOCAC.ID_NO = DCCS9280.ID_NO;
        BPCSOCAC.CI_CNM = DCCS9280.CI_NAME;
        BPCSOCAC.CARD_FLG = '2';
        BPCSOCAC.CCY = "156";
        BPCSOCAC.CCY_TYPE = '1';
        BPCSOCAC.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSOCAC.OPEN_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSOCAC.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        BPCSOCAC.PROD_CD = WS_CARD_PROD;
        BPCSOCAC.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZSOCAC();
        if (pgmRtn) return;
    }
    public void B200_DATA_OUTPUT() throws IOException,SQLException,Exception {
        DCCF280.CARD_NO = DCCS9280.CARD_NO;
        DCCF280.DT = SCCGWA.COMM_AREA.AC_DATE;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_9;
        SCCFMT.DATA_PTR = DCCF280;
        SCCFMT.DATA_LEN = 27;
        CEP.TRC(SCCGWA, DCCF280);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_CDDAT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READUPD_DCTCTCDC() throws IOException,SQLException,Exception {
        DCTCTCDC_RD = new DBParm();
        DCTCTCDC_RD.TableName = "DCTCTCDC";
        DCTCTCDC_RD.where = "ID_NO = :DCRCTCDC.ID_NO "
            + "AND ID_TYP = :DCRCTCDC.ID_TYP "
            + "AND CHG_STS = '1' "
            + "AND CHG_TYP = '1' "
            + "AND NEW_CARD_NO = ' '";
        DCTCTCDC_RD.upd = true;
        IBS.READ(SCCGWA, DCRCTCDC, this, DCTCTCDC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_CTCDC;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_UPDATE_DCTCTCDC() throws IOException,SQLException,Exception {
        DCTCTCDC_RD = new DBParm();
        DCTCTCDC_RD.TableName = "DCTCTCDC";
        IBS.REWRITE(SCCGWA, DCRCTCDC, DCTCTCDC_RD);
    }
    public void T000_READ_DCTCDDAT_UPD() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.upd = true;
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_CDDAT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTCDORD() throws IOException,SQLException,Exception {
        DCTCDORD_RD = new DBParm();
        DCTCDORD_RD.TableName = "DCTCDORD";
        IBS.READ(SCCGWA, DCRCDORD, DCTCDORD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDORD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.errhdl = true;
        IBS.REWRITE(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_TBL_FLAG = 'D';
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DUPKEY);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_CDDAT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_DCTCDDAT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "WRITE-CDDAT");
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_TBL_FLAG = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_CDDAT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DCTCTCDC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "RWRITE-CTCDC");
        DCTCTCDC_RD = new DBParm();
        DCTCTCDC_RD.TableName = "DCTCTCDC";
        DCTCTCDC_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DCRCTCDC, DCTCTCDC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_TBL_FLAG = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_CTCDC;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_DCTCITCD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "WRITE-CITCD");
        DCTCITCD_RD = new DBParm();
        DCTCITCD_RD.TableName = "DCTCITCD";
        DCTCITCD_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DCRCITCD, DCTCITCD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_TBL_FLAG = 'D';
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DUPKEY);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_CITCD;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-P-QUERY-PROD", DCCDQPRD);
        if (DCCDQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCDQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZFCDGG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-F-CHK-DIGIT-GEN", DCCFCDGG);
        CEP.TRC(SCCGWA, DCCFCDGG.RC.RC_CODE);
        if (DCCFCDGG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCFCDGG.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CISOCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZSACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACR", CICSACR);
        if (CICSACR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICSACR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZUOPAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-OPEN-AC     ", DDCUOPAC);
    }
    public void S000_CALL_BPZSOCAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-OCAC-INFO", BPCSOCAC);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
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
