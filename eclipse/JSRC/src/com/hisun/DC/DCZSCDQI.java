package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.DD.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSCDQI {
    int JIBS_tmp_int;
    DBParm DCTCITCD_RD;
    DBParm DCTDCSCC_RD;
    DBParm DCTCDDAT_RD;
    DBParm DCTCTCDC_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DC404";
    short K_MAX_COLS = 20;
    String WS_ERR_MSG = " ";
    String WS_ID_TYP = " ";
    String WS_ID_NO = " ";
    String WS_CI_NAME = " ";
    String WS_CARD_NO = " ";
    String WS_CI_NO = " ";
    String WS_OLD_CARD_NO = " ";
    String WS_OLD_SOCIAL_CARD = " ";
    String WS_OLD_E_CARD = " ";
    char WS_CNT_FLG = ' ';
    char WS_TBL_FLAG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRCITCD DCRCITCD = new DCRCITCD();
    DCRCTCDC DCRCTCDC = new DCRCTCDC();
    DCRDCSCC DCRDCSCC = new DCRDCSCC();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    CICSSCH CICSSCH = new CICSSCH();
    CICACCU CICACCU = new CICACCU();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    DCCF404 DCCF404 = new DCCF404();
    CICCUST CICCUST = new CICCUST();
    CICQCHDC CICQCHDC = new CICQCHDC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCSCDQI DCCSCDQI;
    public void MP(SCCGWA SCCGWA, DCCSCDQI DCCSCDQI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSCDQI = DCCSCDQI;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSCDQI return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        WS_TBL_FLAG = ' ';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GET_CARD_INFO();
        if (pgmRtn) return;
        C020_OUT_LIST();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSCDQI.ID_TYP);
        CEP.TRC(SCCGWA, DCCSCDQI.ID_NO);
        CEP.TRC(SCCGWA, DCCSCDQI.CI_NM);
        CEP.TRC(SCCGWA, DCCSCDQI.CARD_NO);
        CEP.TRC(SCCGWA, DCCSCDQI.CI_NO);
        CEP.TRC(SCCGWA, DCCSCDQI.SOCIAL_NO);
        if (DCCSCDQI.ID_TYP.trim().length() == 0 
            && DCCSCDQI.ID_NO.trim().length() == 0 
            && DCCSCDQI.CI_NM.trim().length() == 0 
            && DCCSCDQI.CARD_NO.trim().length() == 0 
            && DCCSCDQI.CI_NO.trim().length() == 0 
            && DCCSCDQI.SOCIAL_NO.trim().length() == 0 
            && DCCSCDQI.SOCIAL_CARD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_QUERY_INF_MISSING;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSCDQI.CARD_NO == null) DCCSCDQI.CARD_NO = "";
        JIBS_tmp_int = DCCSCDQI.CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCCSCDQI.CARD_NO += " ";
        if (DCCSCDQI.CARD_NO.trim().length() > 0 
            && !DCCSCDQI.CARD_NO.substring(0, 10).equalsIgnoreCase("6214619999")) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NOT_CITIZEN_CARD;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_CARD_INFO() throws IOException,SQLException,Exception {
        if (DCCSCDQI.ID_TYP.trim().length() > 0 
            && DCCSCDQI.ID_NO.trim().length() > 0 
            && DCCSCDQI.CI_NM.trim().length() > 0) {
            WS_ID_TYP = DCCSCDQI.ID_TYP;
            WS_ID_NO = DCCSCDQI.ID_NO;
            WS_CI_NAME = DCCSCDQI.CI_NM;
            IBS.init(SCCGWA, CICCUST);
            CICCUST.FUNC = 'B';
            CICCUST.DATA.ID_TYPE = DCCSCDQI.ID_TYP;
            CICCUST.DATA.ID_NO = DCCSCDQI.ID_NO;
            CICCUST.DATA.CI_NM = DCCSCDQI.CI_NM;
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            WS_CI_NO = CICCUST.O_DATA.O_CI_NO;
            CEP.TRC(SCCGWA, WS_CI_NO);
            IBS.init(SCCGWA, DCRCDDAT);
            DCRCDDAT.CARD_HLDR_CINO = WS_CI_NO;
            T000_READ_DCTCDDAT1();
            if (pgmRtn) return;
            WS_CARD_NO = DCRCDDAT.KEY.CARD_NO;
            CEP.TRC(SCCGWA, WS_CARD_NO);
            IBS.init(SCCGWA, CICQCHDC);
            CICQCHDC.FUNC = 'O';
            CICQCHDC.DATA.N_AGR_NO = WS_CARD_NO;
            CICQCHDC.DATA.N_ENTY_TYP = '2';
            S000_CALL_CIZQCHDC();
            if (pgmRtn) return;
            WS_OLD_CARD_NO = CICQCHDC.DATA.O_AGR_NO;
            CEP.TRC(SCCGWA, WS_OLD_CARD_NO);
            if (WS_OLD_CARD_NO.trim().length() > 0) {
                IBS.init(SCCGWA, DCRCITCD);
                DCRCITCD.KEY.CARD_NO = WS_OLD_CARD_NO;
                T000_READ_DCTCITCD_FIRST();
                if (pgmRtn) return;
                WS_OLD_SOCIAL_CARD = DCRCITCD.SOCIAL_CARD_NO;
                WS_OLD_E_CARD = DCRCITCD.E_CARD_NO;
            }
            IBS.init(SCCGWA, DCRCTCDC);
            DCRCTCDC.NEW_CARD_NO = WS_CARD_NO;
            T000_READ_DCTCTCDC_FIRST();
            if (pgmRtn) return;
            IBS.init(SCCGWA, DCRCITCD);
            DCRCITCD.KEY.CARD_NO = WS_CARD_NO;
            T000_READ_DCTCITCD_FIRST();
            if (pgmRtn) return;
        }
        if (DCCSCDQI.CARD_NO.trim().length() > 0) {
            WS_CARD_NO = DCCSCDQI.CARD_NO;
            IBS.init(SCCGWA, DCRCDDAT);
            DCRCDDAT.KEY.CARD_NO = DCCSCDQI.CARD_NO;
            T000_READ_DCTCDDAT2();
            if (pgmRtn) return;
            WS_CI_NO = DCRCDDAT.CARD_HLDR_CINO;
            CEP.TRC(SCCGWA, WS_CI_NO);
            IBS.init(SCCGWA, CICCUST);
            CICCUST.FUNC = 'C';
            CICCUST.DATA.CI_NO = WS_CI_NO;
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            WS_ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
            WS_ID_NO = CICCUST.O_DATA.O_ID_NO;
            WS_CI_NAME = CICCUST.O_DATA.O_CI_NM;
            CEP.TRC(SCCGWA, WS_ID_TYP);
            CEP.TRC(SCCGWA, WS_ID_NO);
            CEP.TRC(SCCGWA, WS_CI_NAME);
            IBS.init(SCCGWA, CICQCHDC);
            CICQCHDC.FUNC = 'O';
            CICQCHDC.DATA.N_AGR_NO = WS_CARD_NO;
            CICQCHDC.DATA.N_ENTY_TYP = '2';
            S000_CALL_CIZQCHDC();
            if (pgmRtn) return;
            WS_OLD_CARD_NO = CICQCHDC.DATA.O_AGR_NO;
            CEP.TRC(SCCGWA, WS_OLD_CARD_NO);
            if (WS_OLD_CARD_NO.trim().length() > 0) {
                IBS.init(SCCGWA, DCRCITCD);
                DCRCITCD.KEY.CARD_NO = WS_OLD_CARD_NO;
                T000_READ_DCTCITCD_FIRST();
                if (pgmRtn) return;
                WS_OLD_SOCIAL_CARD = DCRCITCD.SOCIAL_CARD_NO;
                WS_OLD_E_CARD = DCRCITCD.E_CARD_NO;
            }
            IBS.init(SCCGWA, DCRCTCDC);
            DCRCTCDC.NEW_CARD_NO = WS_CARD_NO;
            T000_READ_DCTCTCDC_FIRST();
            if (pgmRtn) return;
            IBS.init(SCCGWA, DCRCITCD);
            DCRCITCD.KEY.CARD_NO = WS_CARD_NO;
            T000_READ_DCTCITCD_FIRST();
            if (pgmRtn) return;
        }
        if (DCCSCDQI.CI_NO.trim().length() > 0) {
            WS_CI_NO = DCCSCDQI.CI_NO;
            IBS.init(SCCGWA, CICCUST);
            CICCUST.FUNC = 'C';
            CICCUST.DATA.CI_NO = WS_CI_NO;
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            WS_ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
            WS_ID_NO = CICCUST.O_DATA.O_ID_NO;
            WS_CI_NAME = CICCUST.O_DATA.O_CI_NM;
            CEP.TRC(SCCGWA, WS_ID_TYP);
            CEP.TRC(SCCGWA, WS_ID_NO);
            CEP.TRC(SCCGWA, WS_CI_NAME);
            IBS.init(SCCGWA, DCRCDDAT);
            DCRCDDAT.CARD_HLDR_CINO = WS_CI_NO;
            T000_READ_DCTCDDAT1();
            if (pgmRtn) return;
            WS_CARD_NO = DCRCDDAT.KEY.CARD_NO;
            CEP.TRC(SCCGWA, WS_CARD_NO);
            IBS.init(SCCGWA, CICQCHDC);
            CICQCHDC.FUNC = 'O';
            CICQCHDC.DATA.N_AGR_NO = WS_CARD_NO;
            CICQCHDC.DATA.N_ENTY_TYP = '2';
            S000_CALL_CIZQCHDC();
            if (pgmRtn) return;
            WS_OLD_CARD_NO = CICQCHDC.DATA.O_AGR_NO;
            CEP.TRC(SCCGWA, WS_OLD_CARD_NO);
            if (WS_OLD_CARD_NO.trim().length() > 0) {
                IBS.init(SCCGWA, DCRCITCD);
                DCRCITCD.KEY.CARD_NO = WS_OLD_CARD_NO;
                T000_READ_DCTCITCD_FIRST();
                if (pgmRtn) return;
                WS_OLD_SOCIAL_CARD = DCRCITCD.SOCIAL_CARD_NO;
                WS_OLD_E_CARD = DCRCITCD.E_CARD_NO;
            }
            IBS.init(SCCGWA, DCRCTCDC);
            DCRCTCDC.NEW_CARD_NO = WS_CARD_NO;
            T000_READ_DCTCTCDC_FIRST();
            if (pgmRtn) return;
            IBS.init(SCCGWA, DCRCITCD);
            DCRCITCD.KEY.CARD_NO = WS_CARD_NO;
            T000_READ_DCTCITCD_FIRST();
            if (pgmRtn) return;
        }
        if (DCCSCDQI.SOCIAL_NO.trim().length() > 0) {
            IBS.init(SCCGWA, DCRCITCD);
            DCRCITCD.SOCIAL_NO = DCCSCDQI.SOCIAL_NO;
            T000_READ_DCTCITCD_FIRST2();
            if (pgmRtn) return;
            WS_CARD_NO = DCRCITCD.KEY.CARD_NO;
            CEP.TRC(SCCGWA, WS_CARD_NO);
            IBS.init(SCCGWA, DCRCDDAT);
            DCRCDDAT.KEY.CARD_NO = WS_CARD_NO;
            T000_READ_DCTCDDAT2();
            if (pgmRtn) return;
            WS_CI_NO = DCRCDDAT.CARD_HLDR_CINO;
            CEP.TRC(SCCGWA, WS_CI_NO);
            IBS.init(SCCGWA, CICCUST);
            CICCUST.FUNC = 'C';
            CICCUST.DATA.CI_NO = WS_CI_NO;
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            WS_ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
            WS_ID_NO = CICCUST.O_DATA.O_ID_NO;
            WS_CI_NAME = CICCUST.O_DATA.O_CI_NM;
            CEP.TRC(SCCGWA, WS_ID_TYP);
            CEP.TRC(SCCGWA, WS_ID_NO);
            CEP.TRC(SCCGWA, WS_CI_NAME);
            IBS.init(SCCGWA, CICQCHDC);
            CICQCHDC.FUNC = 'O';
            CICQCHDC.DATA.N_AGR_NO = WS_CARD_NO;
            CICQCHDC.DATA.N_ENTY_TYP = '2';
            S000_CALL_CIZQCHDC();
            if (pgmRtn) return;
            WS_OLD_CARD_NO = CICQCHDC.DATA.O_AGR_NO;
            CEP.TRC(SCCGWA, WS_OLD_CARD_NO);
            if (WS_OLD_CARD_NO.trim().length() > 0) {
                IBS.init(SCCGWA, DCRCITCD);
                DCRCITCD.KEY.CARD_NO = WS_OLD_CARD_NO;
                T000_READ_DCTCITCD_FIRST();
                if (pgmRtn) return;
                WS_OLD_SOCIAL_CARD = DCRCITCD.SOCIAL_CARD_NO;
                WS_OLD_E_CARD = DCRCITCD.E_CARD_NO;
            }
            IBS.init(SCCGWA, DCRCTCDC);
            DCRCTCDC.NEW_CARD_NO = WS_CARD_NO;
            T000_READ_DCTCTCDC_FIRST();
            if (pgmRtn) return;
            IBS.init(SCCGWA, DCRCITCD);
            DCRCITCD.SOCIAL_NO = DCCSCDQI.SOCIAL_NO;
            T000_READ_DCTCITCD_FIRST2();
            if (pgmRtn) return;
        }
        if (DCCSCDQI.SOCIAL_CARD_NO.trim().length() > 0) {
            IBS.init(SCCGWA, DCRCITCD);
            DCRCITCD.SOCIAL_CARD_NO = DCCSCDQI.SOCIAL_CARD_NO;
            T000_READ_DCTCITCD_FIRST3();
            if (pgmRtn) return;
            WS_CARD_NO = DCRCITCD.KEY.CARD_NO;
            CEP.TRC(SCCGWA, WS_CARD_NO);
            IBS.init(SCCGWA, DCRCDDAT);
            DCRCDDAT.KEY.CARD_NO = WS_CARD_NO;
            T000_READ_DCTCDDAT2();
            if (pgmRtn) return;
            WS_CI_NO = DCRCDDAT.CARD_HLDR_CINO;
            CEP.TRC(SCCGWA, WS_CI_NO);
            IBS.init(SCCGWA, CICCUST);
            CICCUST.FUNC = 'C';
            CICCUST.DATA.CI_NO = WS_CI_NO;
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            WS_ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
            WS_ID_NO = CICCUST.O_DATA.O_ID_NO;
            WS_CI_NAME = CICCUST.O_DATA.O_CI_NM;
            CEP.TRC(SCCGWA, WS_ID_TYP);
            CEP.TRC(SCCGWA, WS_ID_NO);
            CEP.TRC(SCCGWA, WS_CI_NAME);
            IBS.init(SCCGWA, CICQCHDC);
            CICQCHDC.FUNC = 'O';
            CICQCHDC.DATA.N_AGR_NO = WS_CARD_NO;
            CICQCHDC.DATA.N_ENTY_TYP = '2';
            S000_CALL_CIZQCHDC();
            if (pgmRtn) return;
            WS_OLD_CARD_NO = CICQCHDC.DATA.O_AGR_NO;
            CEP.TRC(SCCGWA, WS_OLD_CARD_NO);
            if (WS_OLD_CARD_NO.trim().length() > 0) {
                IBS.init(SCCGWA, DCRCITCD);
                DCRCITCD.KEY.CARD_NO = WS_OLD_CARD_NO;
                T000_READ_DCTCITCD_FIRST();
                if (pgmRtn) return;
                WS_OLD_SOCIAL_CARD = DCRCITCD.SOCIAL_CARD_NO;
                WS_OLD_E_CARD = DCRCITCD.E_CARD_NO;
            }
            IBS.init(SCCGWA, DCRCTCDC);
            DCRCTCDC.NEW_CARD_NO = WS_CARD_NO;
            T000_READ_DCTCTCDC_FIRST();
            if (pgmRtn) return;
            IBS.init(SCCGWA, DCRCITCD);
            DCRCITCD.SOCIAL_CARD_NO = DCCSCDQI.SOCIAL_CARD_NO;
            T000_READ_DCTCITCD_FIRST3();
            if (pgmRtn) return;
        }
    }
    public void B025_GET_CCY_BAL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIQBAL);
        DDCIQBAL.DATA.AC = WS_CARD_NO;
        DDCIQBAL.DATA.CCY = "156";
        DDCIQBAL.DATA.CCY_TYPE = '1';
        S000_CALL_DDZIQBAL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.CURR_BAL_TOT);
        DCCF404.O_AC_AMT = DDCIQBAL.DATA.CURR_BAL_TOT;
    }
    public void B030_GET_OLD_CI_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRDCSCC);
        DCRDCSCC.CARD_NO = WS_CARD_NO;
        T000_READ_DCTDCSCC();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'Y') {
            DCCF404.O_OLD_CI_NAME = DCRDCSCC.CI_NM_OLD;
            DCCF404.O_OLD_ID_TYP = DCRDCSCC.ID_TYP_OLD;
            DCCF404.O_OLD_ID_NO = DCRDCSCC.ID_NO_OLD;
            DCCF404.O_SEX = DCRDCSCC.SEX;
            DCCF404.O_OLD_SEX = DCRDCSCC.SEX_OLD;
            DCCF404.O_CI_NM = DCRDCSCC.CI_NM;
            DCCF404.O_ID_TYP = DCRDCSCC.ID_TYP;
            DCCF404.O_ID_NO = DCRDCSCC.ID_NO;
        }
    }
    public void C020_OUT_LIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCF404);
        if (DCRCDDAT.CARD_STS == 'C' 
            || DCRCDDAT.CARD_STS == '3') {
        } else {
            B025_GET_CCY_BAL_PROC();
            if (pgmRtn) return;
        }
        DCCF404.O_CARD_NO = WS_CARD_NO;
        DCCF404.O_ID_TYP = WS_ID_TYP;
        DCCF404.O_ID_NO = WS_ID_NO;
        DCCF404.O_CI_NM = WS_CI_NAME;
        DCCF404.O_CI_NO = WS_CI_NO;
        DCCF404.O_SOCIAL_NO = DCRCITCD.SOCIAL_NO;
        DCCF404.O_SOCIAL_CARD_NO = DCRCITCD.SOCIAL_CARD_NO;
        DCCF404.O_E_CARD_NO = DCRCITCD.E_CARD_NO;
        DCCF404.O_ORG_SEQ = DCRCITCD.ORG_SEQ;
        DCCF404.O_ORG_NM = DCRCITCD.ORG_NM;
        DCCF404.O_SOCIAL_ID = DCRCITCD.SOCIAL_ID;
        DCCF404.O_CARD_STS = DCRCDDAT.CARD_STS;
        DCCF404.O_CARD_STSW = DCRCDDAT.CARD_STSW;
        DCCF404.O_OLD_CARD_NO = WS_OLD_CARD_NO;
        DCCF404.O_OLD_SOCIAL_NO = WS_OLD_SOCIAL_CARD;
        DCCF404.O_OLD_E_CARD_NO = WS_OLD_E_CARD;
        DCCF404.O_CHG_SEQ = DCRCTCDC.CHG_SEQ;
        DCCF404.O_CHG_APP_NO = DCRCTCDC.CHG_APP_NO;
        DCCF404.O_CHG_APP_DT = DCRCTCDC.TXN_DT;
        DCCF404.O_CHG_APP_BR = DCRCTCDC.APP_BR;
        DCCF404.O_CHG_APP_TELLER = DCRCTCDC.APP_TLR;
        if (DCRCTCDC.CHG_STS == '2') {
            DCCF404.O_DELIVER_FLG = '1';
        }
        if (DCRCTCDC.CHG_STS == '1') {
            DCCF404.O_DELIVER_FLG = '0';
        }
        DCCF404.O_DELIVER_DT = DCRCTCDC.HDOV_DT;
        DCCF404.O_DELIVER_BR = DCRCTCDC.HDOV_BR;
        DCCF404.O_DELIVER_TELLER = DCRCTCDC.HDOV_TLR;
        DCCF404.O_FETCH_DT = DCRCTCDC.FET_DT;
        DCCF404.O_FETCH_BR = DCRCTCDC.FET_BR;
        DCCF404.O_FETCH_TELLER = DCRCTCDC.FET_TLR;
        DCCF404.O_OPEN_DT = DCRCDDAT.ISSU_DT;
        DCCF404.O_OPEN_BR = DCRCDDAT.ISSU_BR;
        DCCF404.O_OWN_STS = DCRCITCD.STS;
        DCCF404.O_OWN_DT = DCRCITCD.OWN_DT;
        DCCF404.O_OWN_BR = DCRCITCD.OWN_BR;
        DCCF404.O_OWN_REASON = DCRCITCD.RMK;
        if (DCRCDDAT.CARD_TYP == null) DCRCDDAT.CARD_TYP = "";
        JIBS_tmp_int = DCRCDDAT.CARD_TYP.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) DCRCDDAT.CARD_TYP += " ";
        if (DCRCDDAT.CARD_TYP.substring(0, 1).equalsIgnoreCase("1")) {
            DCCF404.O_EMPLOY_FLG = 'Y';
        } else {
            DCCF404.O_EMPLOY_FLG = 'N';
        }
        SCCFMT.FMTID = "DC404";
        SCCFMT.DATA_PTR = DCCF404;
        SCCFMT.DATA_LEN = 1263;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_DCTCITCD_FIRST() throws IOException,SQLException,Exception {
        DCTCITCD_RD = new DBParm();
        DCTCITCD_RD.TableName = "DCTCITCD";
        DCTCITCD_RD.where = "CARD_NO = :DCRCITCD.KEY.CARD_NO";
        DCTCITCD_RD.fst = true;
        IBS.READ(SCCGWA, DCRCITCD, this, DCTCITCD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_INFO_NOT_FOUND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTDCSCC() throws IOException,SQLException,Exception {
        DCTDCSCC_RD = new DBParm();
        DCTDCSCC_RD.TableName = "DCTDCSCC";
        DCTDCSCC_RD.where = "CARD_NO = :DCRDCSCC.CARD_NO";
        DCTDCSCC_RD.fst = true;
        DCTDCSCC_RD.order = "TXN_DT DESC";
        IBS.READ(SCCGWA, DCRDCSCC, this, DCTDCSCC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTDCSCC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTCITCD_FIRST2() throws IOException,SQLException,Exception {
        DCTCITCD_RD = new DBParm();
        DCTCITCD_RD.TableName = "DCTCITCD";
        DCTCITCD_RD.where = "SOCIAL_NO = :DCRCITCD.SOCIAL_NO";
        DCTCITCD_RD.fst = true;
        DCTCITCD_RD.order = "SYNC_DT DESC";
        IBS.READ(SCCGWA, DCRCITCD, this, DCTCITCD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_INFO_NOT_FOUND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTCITCD_FIRST3() throws IOException,SQLException,Exception {
        DCTCITCD_RD = new DBParm();
        DCTCITCD_RD.TableName = "DCTCITCD";
        DCTCITCD_RD.where = "SOCIAL_CARD_NO = :DCRCITCD.SOCIAL_CARD_NO";
        DCTCITCD_RD.fst = true;
        IBS.READ(SCCGWA, DCRCITCD, this, DCTCITCD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_INFO_NOT_FOUND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTCDDAT1() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.where = "CARD_HLDR_CINO = :DCRCDDAT.CARD_HLDR_CINO "
            + "AND PROD_CD = '1203010101'";
        DCTCDDAT_RD.fst = true;
        DCTCDDAT_RD.order = "ISSU_DT DESC";
        IBS.READ(SCCGWA, DCRCDDAT, this, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_INFO_NOT_FOUND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTCDDAT2() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.where = "CARD_NO = :DCRCDDAT.KEY.CARD_NO";
        DCTCDDAT_RD.fst = true;
        IBS.READ(SCCGWA, DCRCDDAT, this, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_INFO_NOT_FOUND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTCTCDC_FIRST() throws IOException,SQLException,Exception {
        DCTCTCDC_RD = new DBParm();
        DCTCTCDC_RD.TableName = "DCTCTCDC";
        DCTCTCDC_RD.where = "NEW_CARD_NO = :DCRCTCDC.NEW_CARD_NO";
        DCTCTCDC_RD.fst = true;
        IBS.READ(SCCGWA, DCRCTCDC, this, DCTCTCDC_RD);
    }
    public void S000_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-CCY-BAL", DDCIQBAL);
        if (DDCIQBAL.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DDCIQBAL.RC);
        }
    }
    public void S001_CALL_CIZSSCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SEARCH-CI", CICSSCH);
        if (CICSSCH.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSSCH.RC);
        }
    }
    public void S002_CALL_CICACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        if (CICACCU.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICACCU.RC);
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CISOCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase("CI3002")) {
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_CIZQCHDC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CHDC", CICQCHDC);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, CICQCHDC.RC);
        if (CICQCHDC.RC.RC_CODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase("CI8071")) {
            CEP.ERR(SCCGWA, CICQCHDC.RC);
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
