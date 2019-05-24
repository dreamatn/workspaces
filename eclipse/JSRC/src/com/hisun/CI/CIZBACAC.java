package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.TD.*;
import com.hisun.DC.*;
import com.hisun.DD.*;
import com.hisun.IB.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZBACAC {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm CITBAS_RD;
    DBParm CITACR_RD;
    DBParm CITCNT_RD;
    brParm CITACR_BR = new brParm();
    brParm CITACAC_BR = new brParm();
    DBParm CITACRL_RD;
    DBParm CITACAC_RD;
    brParm CITACRL_BR = new brParm();
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "CIA02";
    int K_MAX_ROW = 10;
    int WS_I = 0;
    int WS_PAGE_ROW = 0;
    int WS_RECORD_NUM = 0;
    int WS_RECORD_NUM1 = 0;
    int WS_CX = 0;
    String WS_CI_NO = " ";
    String WS_STA_NO = " ";
    char WS_ACR_FLG = ' ';
    char WS_ACAC_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    CIRACR CIRACR = new CIRACR();
    CIRACRL CIRACRL = new CIRACRL();
    CIRACAC CIRACAC = new CIRACAC();
    CIRBAS CIRBAS = new CIRBAS();
    CIRCNT CIRCNT = new CIRCNT();
    TDCACE TDCACE = new TDCACE();
    DCCUCINF DCCUCINF = new DCCUCINF();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    DDCIMMST DDCIMMST = new DDCIMMST();
    IBCQINFS IBCQINFS = new IBCQINFS();
    CICFA02 CICFA02 = new CICFA02();
    DCCIQHLD DCCIQHLD = new DCCIQHLD();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    int WS_AC_DATE = 0;
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICBACAC CICBACAC;
    public void MP(SCCGWA SCCGWA, CICBACAC CICBACAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICBACAC = CICBACAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZBACAC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        WS_AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICBACAC.DATA.AGR_NO);
        if (CICBACAC.DATA.AGR_NO.trim().length() == 0) {
            B010_GET_BAS_INF();
            if (pgmRtn) return;
            if (CICBACAC.DATA.FRM_APP.equalsIgnoreCase("TD") 
                && CICBACAC.DATA.END_DAYS != 0) {
                B020_BROWSE_PROC_TD();
                if (pgmRtn) return;
            } else {
                B020_BROWSE_PROC();
                if (pgmRtn) return;
            }
        } else {
            if (CICBACAC.DATA.FRM_APP.equalsIgnoreCase("TD") 
                && CICBACAC.DATA.END_DAYS != 0) {
                B030_BROWSE_BY_TD();
                if (pgmRtn) return;
            } else {
                B030_BROWSE_BY_AC();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_GET_BAS_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICBACAC.DATA.CI_NO);
        WS_CI_NO = CICBACAC.DATA.CI_NO;
        if (CICBACAC.DATA.CI_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "CI NO MUST INPUT");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_NO_MUST_INPUT, CICBACAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICBACAC.DATA.CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRCNT);
        CIRCNT.KEY.CI_NO = CICBACAC.DATA.CI_NO;
        T000_READ_CITCNT();
        if (pgmRtn) return;
    }
    public void B020_BROWSE_PROC_TD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOCLWD);
        IBS.init(SCCGWA, CIRACR);
        IBS.init(SCCGWA, CICFA02);
        BPCOCLWD.CAL_CODE = "SY";
        BPCOCLWD.DATE1 = SCCGWA.COMM_AREA.AC_DATE;
        BPCOCLWD.WDAYS = CICBACAC.DATA.END_DAYS;
        S000_CALL_BPZPCLWD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCOCLWD.DATE2);
        CIRACR.CI_NO = CICBACAC.DATA.CI_NO;
        T000_STARTBR_CITACR();
        if (pgmRtn) return;
        T000_READNEXT_CITACR();
        if (pgmRtn) return;
        if (CICBACAC.DATA.PAGE_ROW > K_MAX_ROW 
            || CICBACAC.DATA.PAGE_ROW == 0) {
            WS_PAGE_ROW = K_MAX_ROW;
        } else {
            WS_PAGE_ROW = CICBACAC.DATA.PAGE_ROW;
        }
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            if ((CICBACAC.DATA.ENTY_TYP == ' ' 
                || CICBACAC.DATA.ENTY_TYP == CIRACR.ENTY_TYP) 
                && (CICBACAC.DATA.FRM_APP.trim().length() == 0 
                || (CIRACR.FRM_APP.equalsIgnoreCase(CICBACAC.DATA.FRM_APP) 
                || CIRACR.FRM_APP.equalsIgnoreCase("DC"))) 
                && (CICBACAC.DATA.STS == ' ' 
                || CICBACAC.DATA.STS == CIRACR.STS) 
                && (CICBACAC.DATA.TRCT_TYP.trim().length() == 0 
                || CICBACAC.DATA.TRCT_TYP.equalsIgnoreCase(CIRACR.CNTRCT_TYP))) {
                WS_STA_NO = " ";
                if (CIRACR.FRM_APP.equalsIgnoreCase("DC")) {
                    B040_READ_CITACRL_CHECK();
                    if (pgmRtn) return;
                }
                IBS.init(SCCGWA, CIRACAC);
                CIRACAC.AGR_NO = CIRACR.KEY.AGR_NO;
                T000_STARTBR_CITACAC();
                if (pgmRtn) return;
                T000_READNEXT_CITACAC();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
                while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
                    if ((CICBACAC.DATA.CCY.trim().length() == 0 
                        || CICBACAC.DATA.CCY.equalsIgnoreCase(CIRACAC.CCY) 
                        || (CICBACAC.DATA.CCY.equalsIgnoreCase("NCH") 
                        && !CIRACAC.CCY.equalsIgnoreCase("156"))) 
                        && (CICBACAC.DATA.FRM_APP.trim().length() == 0 
                        || CICBACAC.DATA.FRM_APP.equalsIgnoreCase("DC") 
                        || CICBACAC.DATA.FRM_APP.equalsIgnoreCase(CIRACAC.FRM_APP)) 
                        && (CICBACAC.DATA.PROD_CD.trim().length() == 0 
                        || CICBACAC.DATA.PROD_CD.equalsIgnoreCase(CIRACR.PROD_CD) 
                        || CICBACAC.DATA.PROD_CD.equalsIgnoreCase(CIRACAC.PROD_CD)) 
                        && (CICBACAC.DATA.STS == ' ' 
                        || CICBACAC.DATA.STS == CIRACAC.ACAC_STS)) {
                        IBS.init(SCCGWA, TDCACE);
                        TDCACE.PAGE_INF.AC_NO = CIRACAC.AGR_NO;
                        TDCACE.PAGE_INF.I_AC_SEQ = CIRACAC.AGR_SEQ;
                        S000_CALL_TDZACE();
                        if (pgmRtn) return;
                        CEP.TRC(SCCGWA, TDCACE.DATA[1-1].DDT);
                        if (TDCACE.DATA[1-1].DDT > BPCOCLWD.DATE2) {
                            CEP.TRC(SCCGWA, WS_RECORD_NUM);
                            WS_RECORD_NUM = WS_RECORD_NUM + 1;
                            CEP.TRC(SCCGWA, WS_CX);
                            CEP.TRC(SCCGWA, CICBACAC.DATA.PAGE_NUM);
                            CEP.TRC(SCCGWA, WS_PAGE_ROW);
                            WS_CX = ( CICBACAC.DATA.PAGE_NUM - 1 ) * WS_PAGE_ROW;
                            CEP.TRC(SCCGWA, WS_RECORD_NUM);
                            CEP.TRC(SCCGWA, WS_CX);
                            CEP.TRC(SCCGWA, WS_I);
                            if ((WS_RECORD_NUM > WS_CX) 
                                && (WS_I < WS_PAGE_ROW)) {
                                WS_I = WS_I + 1;
                                B021_DATA_TRANS_TO_FMT_TD();
                                if (pgmRtn) return;
                            }
                            CEP.TRC(SCCGWA, WS_I);
                        }
                    }
                    T000_READNEXT_CITACAC();
                    if (pgmRtn) return;
                }
                T000_ENDBR_CITACAC();
                if (pgmRtn) return;
            }
            T000_READNEXT_CITACR();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITACR();
        if (pgmRtn) return;
        B022_OUT_PAGE_TITLE();
        if (pgmRtn) return;
        B023_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B020_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACR);
        IBS.init(SCCGWA, CICFA02);
        CIRACR.CI_NO = CICBACAC.DATA.CI_NO;
        T000_STARTBR_CITACR();
        if (pgmRtn) return;
        T000_READNEXT_CITACR();
        if (pgmRtn) return;
        if (CICBACAC.DATA.PAGE_ROW > K_MAX_ROW 
            || CICBACAC.DATA.PAGE_ROW == 0) {
            WS_PAGE_ROW = K_MAX_ROW;
        } else {
            WS_PAGE_ROW = CICBACAC.DATA.PAGE_ROW;
        }
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            if ((CICBACAC.DATA.ENTY_TYP == ' ' 
                || CICBACAC.DATA.ENTY_TYP == CIRACR.ENTY_TYP) 
                && (CICBACAC.DATA.FRM_APP.trim().length() == 0 
                || (CIRACR.FRM_APP.equalsIgnoreCase(CICBACAC.DATA.FRM_APP) 
                || CIRACR.FRM_APP.equalsIgnoreCase("DC"))) 
                && (CICBACAC.DATA.STS == ' ' 
                || CICBACAC.DATA.STS == CIRACR.STS) 
                && (CICBACAC.DATA.TRCT_TYP.trim().length() == 0 
                || CICBACAC.DATA.TRCT_TYP.equalsIgnoreCase(CIRACR.CNTRCT_TYP))) {
                WS_STA_NO = " ";
                if (CIRACR.FRM_APP.equalsIgnoreCase("DC")) {
                    B040_READ_CITACRL_CHECK();
                    if (pgmRtn) return;
                }
                IBS.init(SCCGWA, CIRACAC);
                CIRACAC.AGR_NO = CIRACR.KEY.AGR_NO;
                T000_STARTBR_CITACAC();
                if (pgmRtn) return;
                T000_READNEXT_CITACAC();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
                while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
                    if ((CICBACAC.DATA.CCY.trim().length() == 0 
                        || CICBACAC.DATA.CCY.equalsIgnoreCase(CIRACAC.CCY) 
                        || (CICBACAC.DATA.CCY.equalsIgnoreCase("NCH") 
                        && !CIRACAC.CCY.equalsIgnoreCase("156"))) 
                        && (CICBACAC.DATA.FRM_APP.trim().length() == 0 
                        || CICBACAC.DATA.FRM_APP.equalsIgnoreCase("DC") 
                        || CICBACAC.DATA.FRM_APP.equalsIgnoreCase(CIRACAC.FRM_APP)) 
                        && (CICBACAC.DATA.PROD_CD.trim().length() == 0 
                        || CICBACAC.DATA.PROD_CD.equalsIgnoreCase(CIRACR.PROD_CD) 
                        || CICBACAC.DATA.PROD_CD.equalsIgnoreCase(CIRACAC.PROD_CD)) 
                        && (CICBACAC.DATA.STS == ' ' 
                        || CICBACAC.DATA.STS == CIRACAC.ACAC_STS)) {
                        CEP.TRC(SCCGWA, WS_RECORD_NUM);
                        WS_RECORD_NUM = WS_RECORD_NUM + 1;
                        CEP.TRC(SCCGWA, WS_CX);
                        CEP.TRC(SCCGWA, CICBACAC.DATA.PAGE_NUM);
                        CEP.TRC(SCCGWA, WS_PAGE_ROW);
                        WS_CX = ( CICBACAC.DATA.PAGE_NUM - 1 ) * WS_PAGE_ROW;
                        CEP.TRC(SCCGWA, WS_RECORD_NUM);
                        CEP.TRC(SCCGWA, WS_CX);
                        CEP.TRC(SCCGWA, WS_I);
                        if ((WS_RECORD_NUM > WS_CX) 
                            && (WS_I < WS_PAGE_ROW)) {
                            WS_I = WS_I + 1;
                            B021_DATA_TRANS_TO_FMT();
                            if (pgmRtn) return;
                        }
                        CEP.TRC(SCCGWA, WS_I);
                    }
                    T000_READNEXT_CITACAC();
                    if (pgmRtn) return;
                }
                T000_ENDBR_CITACAC();
                if (pgmRtn) return;
            }
            T000_READNEXT_CITACR();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITACR();
        if (pgmRtn) return;
        B022_OUT_PAGE_TITLE();
        if (pgmRtn) return;
        B023_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B021_DATA_TRANS_TO_FMT() throws IOException,SQLException,Exception {
        CICFA02.DATA[WS_I-1].ACAC_NO = CIRACAC.KEY.ACAC_NO;
        CICFA02.DATA[WS_I-1].AGR_NO = CIRACAC.AGR_NO;
        CICFA02.DATA[WS_I-1].STA_NO = WS_STA_NO;
        CICFA02.DATA[WS_I-1].AGR_SEQ = CIRACAC.AGR_SEQ;
        CICFA02.DATA[WS_I-1].AID = CIRACAC.AID;
        CICFA02.DATA[WS_I-1].STS = CIRACAC.ACAC_STS;
        CICFA02.DATA[WS_I-1].STSW = " ";
        CICFA02.DATA[WS_I-1].FRM_APP = CIRACAC.FRM_APP;
        CICFA02.DATA[WS_I-1].CNTRCT_TYP = CIRACR.CNTRCT_TYP;
        CICFA02.DATA[WS_I-1].PROD_CD = CIRACAC.PROD_CD;
        CICFA02.DATA[WS_I-1].CCY = CIRACAC.CCY;
        CICFA02.DATA[WS_I-1].CR_FLG = CIRACAC.CR_FLG;
        CICFA02.DATA[WS_I-1].BV_NO = CIRACAC.BV_NO;
        CICFA02.DATA[WS_I-1].OPEN_DT = CIRACAC.OPEN_DT;
        CICFA02.DATA[WS_I-1].OPEN_BR = CIRACAC.OPN_BR;
        CICFA02.DATA[WS_I-1].CI_TYP = CIRBAS.CI_TYP;
        if (CIRBAS.CI_TYP == '1') {
            CICFA02.DATA[WS_I-1].AC_CNM = CIRBAS.CI_NM;
        } else if (CIRBAS.CI_TYP == '2' 
                && CIRACR.FRM_APP.equalsIgnoreCase("DC")) {
            IBS.init(SCCGWA, CIRACRL);
            CIRACRL.KEY.AC_NO = CIRACR.KEY.AGR_NO;
            CIRACRL.KEY.AC_REL = "04";
            T000_READ_CITACRL_BY_AC_R();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CIRACRL.KEY.REL_AC_NO);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                IBS.init(SCCGWA, CIRACR);
                CIRACR.KEY.AGR_NO = CIRACRL.KEY.REL_AC_NO;
                T000_READ_CITACR();
                if (pgmRtn) return;
                CICFA02.DATA[WS_I-1].AC_CNM = CIRACR.AC_CNM;
            }
        } else {
            CICFA02.DATA[WS_I-1].AC_CNM = CIRACR.AC_CNM;
        }
        if (CIRACAC.FRM_APP.equalsIgnoreCase("DD") 
                && CIRACR.STS == '0') {
            IBS.init(SCCGWA, DDCIQBAL);
            DDCIQBAL.DATA.AC = CIRACAC.AGR_NO;
            DDCIQBAL.DATA.CCY = CIRACAC.CCY;
            DDCIQBAL.DATA.CCY_TYPE = CIRACAC.CR_FLG;
            S000_CALL_DDZIQBAL();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "账面余额 = ");
            CEP.TRC(SCCGWA, DDCIQBAL.DATA.CURR_BAL);
            CEP.TRC(SCCGWA, "可用余额 = ");
            CEP.TRC(SCCGWA, DDCIQBAL.DATA.AVL_BAL);
            CICFA02.DATA[WS_I-1].BAL = DDCIQBAL.DATA.CURR_BAL;
            CICFA02.DATA[WS_I-1].AVA_BAL = DDCIQBAL.DATA.AVL_BAL;
            CICFA02.DATA[WS_I-1].LAST_BAL = DDCIQBAL.DATA.LAST_BAL;
            CICFA02.DATA[WS_I-1].DAC_STSW = DDCIQBAL.DATA.CCY_STS_WORD;
            if (CIRACR.FRM_APP.equalsIgnoreCase("DD")) {
                IBS.init(SCCGWA, DDCIMMST);
                DDCIMMST.TX_TYPE = 'I';
                DDCIMMST.DATA.KEY.AC_NO = CIRACAC.AGR_NO;
                S000_CALL_DDZIMMST();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, DDCIMMST.DATA.PAY_TYPE);
                CICFA02.DATA[WS_I-1].DRAW_MTH = DDCIMMST.DATA.PAY_TYPE;
                if (DDCIMMST.DATA.FRG_CODE == null) DDCIMMST.DATA.FRG_CODE = "";
                JIBS_tmp_int = DDCIMMST.DATA.FRG_CODE.length();
                for (int i=0;i<25-JIBS_tmp_int;i++) DDCIMMST.DATA.FRG_CODE += " ";
                CICFA02.DATA[WS_I-1].FRG_CODE = DDCIMMST.DATA.FRG_CODE.substring(0, 4);
                CICFA02.DATA[WS_I-1].BV_TYP = DDCIMMST.DATA.BV_TYPE;
            } else {
                CICFA02.DATA[WS_I-1].DRAW_MTH = '1';
                CICFA02.DATA[WS_I-1].BV_TYP = '4';
            }
            CEP.TRC(SCCGWA, CIRACR.ENTY_TYP);
            if (CIRACR.ENTY_TYP == '2') {
                IBS.init(SCCGWA, DCCUCINF);
                DCCUCINF.CARD_NO = CIRACAC.AGR_NO;
                S000_CALL_DCZUCINF();
                if (pgmRtn) return;
                CICFA02.DATA[WS_I-1].EXP_DT = DCCUCINF.EXP_DT;
            }
        } else if (CIRACAC.FRM_APP.equalsIgnoreCase("TD")) {
            IBS.init(SCCGWA, TDCACE);
            TDCACE.PAGE_INF.AC_NO = CIRACAC.AGR_NO;
            TDCACE.PAGE_INF.I_AC_SEQ = CIRACAC.AGR_SEQ;
            S000_CALL_TDZACE();
            if (pgmRtn) return;
            CICFA02.DATA[WS_I-1].BAL = TDCACE.DATA[1-1].BAL;
            CICFA02.DATA[WS_I-1].AVA_BAL = TDCACE.DATA[1-1].KY_BAL;
            CICFA02.DATA[WS_I-1].LAST_BAL = TDCACE.DATA[1-1].LAST_BAL;
            CICFA02.DATA[WS_I-1].TERMS = TDCACE.DATA[1-1].TERM;
            CICFA02.DATA[WS_I-1].END_DT = TDCACE.DATA[1-1].DDT;
            CICFA02.DATA[WS_I-1].DAC_STSW = TDCACE.DATA[1-1].ACO_STSW;
            if (!CIRACR.FRM_APP.equalsIgnoreCase("DC")) {
                CICFA02.DATA[WS_I-1].BV_TYP = TDCACE.DATA[1-1].BV_TYP;
            } else {
                CICFA02.DATA[WS_I-1].BV_TYP = '4';
            }
            CICFA02.DATA[WS_I-1].DRAW_MTH = TDCACE.PAGE_INF.DRAW_MTH;
            CICFA02.DATA[WS_I-1].FRG_CODE = "" + TDCACE.DATA[1-1].FC_CD;
            JIBS_tmp_int = CICFA02.DATA[WS_I-1].FRG_CODE.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) CICFA02.DATA[WS_I-1].FRG_CODE = "0" + CICFA02.DATA[WS_I-1].FRG_CODE;
            CICFA02.DATA[WS_I-1].STS = TDCACE.DATA[1-1].ACO_STS;
            CICFA02.DATA[WS_I-1].INT_RAT = TDCACE.DATA[1-1].INT_RAT;
            CICFA02.DATA[WS_I-1].EXP_INT = TDCACE.DATA[1-1].EXP_INT;
            if (CIRACR.ENTY_TYP == '2') {
                IBS.init(SCCGWA, DCCUCINF);
                DCCUCINF.CARD_NO = CIRACAC.AGR_NO;
                S000_CALL_DCZUCINF();
                if (pgmRtn) return;
                CICFA02.DATA[WS_I-1].EXP_DT = DCCUCINF.EXP_DT;
            }
        } else if (CIRACAC.FRM_APP.equalsIgnoreCase("IB")) {
            if (CIRACR.CNTRCT_TYP.equalsIgnoreCase("IBTD")) {
                IBS.init(SCCGWA, IBCQINFS);
                IBCQINFS.PRIM_AC_NO = CIRACAC.AGR_NO;
                IBCQINFS.SEQ_NO = CIRACAC.AGR_SEQ;
                S000_CALL_IBZQINFS();
                if (pgmRtn) return;
                CICFA02.DATA[WS_I-1].BAL = IBCQINFS.CURR_BAL;
                CICFA02.DATA[WS_I-1].END_DT = IBCQINFS.EXP_DATE;
                CICFA02.DATA[WS_I-1].LAST_BAL = IBCQINFS.LBAL;
            }
        } else {
        }
        IBS.init(SCCGWA, DCCIQHLD);
        DCCIQHLD.INP_DATA.AC = CIRACAC.KEY.ACAC_NO;
        S000_CALL_DCZIQHLD();
        if (pgmRtn) return;
        if (DCCIQHLD.OUT_DATA.LAW_AMT == 'Y' 
            || DCCIQHLD.OUT_DATA.LAW_AC == 'Y') {
            CICFA02.DATA[WS_I-1].JF_FLG = '1';
        } else {
            CICFA02.DATA[WS_I-1].JF_FLG = '0';
        }
    }
    public void B021_DATA_TRANS_TO_FMT_TD() throws IOException,SQLException,Exception {
        CICFA02.DATA[WS_I-1].ACAC_NO = CIRACAC.KEY.ACAC_NO;
        CICFA02.DATA[WS_I-1].AGR_NO = CIRACAC.AGR_NO;
        CICFA02.DATA[WS_I-1].STA_NO = WS_STA_NO;
        CICFA02.DATA[WS_I-1].AGR_SEQ = CIRACAC.AGR_SEQ;
        CICFA02.DATA[WS_I-1].AID = CIRACAC.AID;
        CICFA02.DATA[WS_I-1].STS = CIRACAC.ACAC_STS;
        CICFA02.DATA[WS_I-1].STSW = " ";
        CICFA02.DATA[WS_I-1].FRM_APP = CIRACAC.FRM_APP;
        CICFA02.DATA[WS_I-1].CNTRCT_TYP = CIRACR.CNTRCT_TYP;
        CICFA02.DATA[WS_I-1].PROD_CD = CIRACAC.PROD_CD;
        CICFA02.DATA[WS_I-1].CCY = CIRACAC.CCY;
        CICFA02.DATA[WS_I-1].CR_FLG = CIRACAC.CR_FLG;
        CICFA02.DATA[WS_I-1].BV_NO = CIRACAC.BV_NO;
        CICFA02.DATA[WS_I-1].OPEN_DT = CIRACAC.OPEN_DT;
        CICFA02.DATA[WS_I-1].OPEN_BR = CIRACAC.OPN_BR;
        CICFA02.DATA[WS_I-1].CI_TYP = CIRBAS.CI_TYP;
        if (CIRBAS.CI_TYP == '1') {
            CICFA02.DATA[WS_I-1].AC_CNM = CIRBAS.CI_NM;
        } else {
            CICFA02.DATA[WS_I-1].AC_CNM = CIRACR.AC_CNM;
        }
        CICFA02.DATA[WS_I-1].BAL = TDCACE.DATA[1-1].BAL;
        CICFA02.DATA[WS_I-1].AVA_BAL = TDCACE.DATA[1-1].KY_BAL;
        CICFA02.DATA[WS_I-1].LAST_BAL = TDCACE.DATA[1-1].LAST_BAL;
        CICFA02.DATA[WS_I-1].TERMS = TDCACE.DATA[1-1].TERM;
        CICFA02.DATA[WS_I-1].END_DT = TDCACE.DATA[1-1].DDT;
        CICFA02.DATA[WS_I-1].DAC_STSW = TDCACE.DATA[1-1].ACO_STSW;
        if (!CIRACR.FRM_APP.equalsIgnoreCase("DC")) {
            CICFA02.DATA[WS_I-1].BV_TYP = TDCACE.DATA[1-1].BV_TYP;
        } else {
            CICFA02.DATA[WS_I-1].BV_TYP = '4';
        }
        CICFA02.DATA[WS_I-1].DRAW_MTH = TDCACE.PAGE_INF.DRAW_MTH;
        CICFA02.DATA[WS_I-1].FRG_CODE = "" + TDCACE.DATA[1-1].FC_CD;
        JIBS_tmp_int = CICFA02.DATA[WS_I-1].FRG_CODE.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) CICFA02.DATA[WS_I-1].FRG_CODE = "0" + CICFA02.DATA[WS_I-1].FRG_CODE;
        CICFA02.DATA[WS_I-1].STS = TDCACE.DATA[1-1].ACO_STS;
        CICFA02.DATA[WS_I-1].INT_RAT = TDCACE.DATA[1-1].INT_RAT;
        CICFA02.DATA[WS_I-1].EXP_INT = TDCACE.DATA[1-1].EXP_INT;
        if (CIRACR.ENTY_TYP == '2') {
            IBS.init(SCCGWA, DCCUCINF);
            DCCUCINF.CARD_NO = CIRACAC.AGR_NO;
            S000_CALL_DCZUCINF();
            if (pgmRtn) return;
            CICFA02.DATA[WS_I-1].EXP_DT = DCCUCINF.EXP_DT;
        }
        IBS.init(SCCGWA, DCCIQHLD);
        DCCIQHLD.INP_DATA.AC = CIRACAC.KEY.ACAC_NO;
        S000_CALL_DCZIQHLD();
        if (pgmRtn) return;
        if (DCCIQHLD.OUT_DATA.LAW_AMT == 'Y' 
            || DCCIQHLD.OUT_DATA.LAW_AC == 'Y') {
            CICFA02.DATA[WS_I-1].JF_FLG = '1';
        } else {
            CICFA02.DATA[WS_I-1].JF_FLG = '0';
        }
    }
    public void B022_OUT_PAGE_TITLE() throws IOException,SQLException,Exception {
        CICFA02.OUTPUT_TITLE.CI_NO = WS_CI_NO;
        CICFA02.OUTPUT_TITLE.ID_TYPE = CIRBAS.ID_TYPE;
        CICFA02.OUTPUT_TITLE.ID_NO = CIRBAS.ID_NO;
        CICFA02.OUTPUT_TITLE.CI_NM = CIRBAS.CI_NM;
        CICFA02.OUTPUT_TITLE.TEL_NO = CIRCNT.TEL_NO;
        WS_RECORD_NUM1 = WS_RECORD_NUM % WS_PAGE_ROW;
        CICFA02.OUTPUT_TITLE.TOTAL_PAGE = (int) ((WS_RECORD_NUM - WS_RECORD_NUM1) / WS_PAGE_ROW);
        if (WS_RECORD_NUM1 > 0) {
            CICFA02.OUTPUT_TITLE.TOTAL_PAGE = CICFA02.OUTPUT_TITLE.TOTAL_PAGE + 1;
        }
        CICFA02.OUTPUT_TITLE.TOTAL_NUM = WS_RECORD_NUM;
        CICFA02.OUTPUT_TITLE.CURR_PAGE = CICBACAC.DATA.PAGE_NUM;
        CICFA02.OUTPUT_TITLE.PAGE_ROW = WS_I;
        CEP.TRC(SCCGWA, CICFA02.OUTPUT_TITLE.TOTAL_NUM);
        CEP.TRC(SCCGWA, CICFA02.OUTPUT_TITLE.CURR_PAGE);
        CEP.TRC(SCCGWA, CICFA02.OUTPUT_TITLE.PAGE_ROW);
        if (CICFA02.OUTPUT_TITLE.CURR_PAGE >= CICFA02.OUTPUT_TITLE.TOTAL_PAGE 
            || CICFA02.OUTPUT_TITLE.TOTAL_PAGE == 0) {
            CICFA02.OUTPUT_TITLE.LAST_PAGE = 'Y';
        } else {
            CICFA02.OUTPUT_TITLE.LAST_PAGE = 'N';
        }
    }
    public void B023_DATA_OUTPUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = CICFA02;
        SCCFMT.DATA_LEN = 6745;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B030_BROWSE_BY_TD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICBACAC.DATA.AGR_NO);
        IBS.init(SCCGWA, CIRACR);
        IBS.init(SCCGWA, CICFA02);
        IBS.init(SCCGWA, BPCOCLWD);
        BPCOCLWD.CAL_CODE = "SY";
        BPCOCLWD.DATE1 = SCCGWA.COMM_AREA.AC_DATE;
        BPCOCLWD.WDAYS = CICBACAC.DATA.END_DAYS;
        S000_CALL_BPZPCLWD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCOCLWD.DATE2);
        CIRACR.KEY.AGR_NO = CICBACAC.DATA.AGR_NO;
        T000_READ_CITACR();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "AGR-NO NOT EXIST");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND, CICBACAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_CI_NO = CIRACR.CI_NO;
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CIRACR.CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRCNT);
        CIRCNT.KEY.CI_NO = CIRACR.CI_NO;
        T000_READ_CITCNT();
        if (pgmRtn) return;
        WS_STA_NO = " ";
        if (CIRACR.FRM_APP.equalsIgnoreCase("DC")) {
            B040_READ_CITACRL_CHECK();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRACAC);
        CIRACAC.AGR_NO = CIRACR.KEY.AGR_NO;
        T000_STARTBR_CITACAC();
        if (pgmRtn) return;
        T000_READNEXT_CITACAC();
        if (pgmRtn) return;
        if (CICBACAC.DATA.PAGE_ROW > K_MAX_ROW 
            || CICBACAC.DATA.PAGE_ROW == 0) {
            WS_PAGE_ROW = K_MAX_ROW;
        } else {
            WS_PAGE_ROW = CICBACAC.DATA.PAGE_ROW;
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            if ((CICBACAC.DATA.CCY.trim().length() == 0 
                || CICBACAC.DATA.CCY.equalsIgnoreCase(CIRACAC.CCY) 
                || (CICBACAC.DATA.CCY.equalsIgnoreCase("NCH") 
                && !CIRACAC.CCY.equalsIgnoreCase("156"))) 
                && (CICBACAC.DATA.FRM_APP.trim().length() == 0 
                || CICBACAC.DATA.FRM_APP.equalsIgnoreCase(CIRACAC.FRM_APP)) 
                && (CICBACAC.DATA.STS == ' ' 
                || CICBACAC.DATA.STS == CIRACAC.ACAC_STS) 
                && (CICBACAC.DATA.TRCT_TYP.trim().length() == 0 
                || CICBACAC.DATA.TRCT_TYP.equalsIgnoreCase(CIRACR.CNTRCT_TYP))) {
                IBS.init(SCCGWA, TDCACE);
                TDCACE.PAGE_INF.AC_NO = CIRACAC.AGR_NO;
                TDCACE.PAGE_INF.I_AC_SEQ = CIRACAC.AGR_SEQ;
                S000_CALL_TDZACE();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, TDCACE.DATA[1-1].DDT);
                if (TDCACE.DATA[1-1].DDT > BPCOCLWD.DATE2) {
                    CEP.TRC(SCCGWA, WS_RECORD_NUM);
                    WS_RECORD_NUM = WS_RECORD_NUM + 1;
                    CEP.TRC(SCCGWA, WS_CX);
                    CEP.TRC(SCCGWA, CICBACAC.DATA.PAGE_NUM);
                    CEP.TRC(SCCGWA, WS_PAGE_ROW);
                    WS_CX = ( CICBACAC.DATA.PAGE_NUM - 1 ) * WS_PAGE_ROW;
                    CEP.TRC(SCCGWA, WS_RECORD_NUM);
                    CEP.TRC(SCCGWA, WS_CX);
                    CEP.TRC(SCCGWA, WS_I);
                    if ((WS_RECORD_NUM > WS_CX) 
                        && (WS_I < WS_PAGE_ROW)) {
                        WS_I = WS_I + 1;
                        B021_DATA_TRANS_TO_FMT_TD();
                        if (pgmRtn) return;
                    }
                    CEP.TRC(SCCGWA, WS_I);
                }
            }
            T000_READNEXT_CITACAC();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITACAC();
        if (pgmRtn) return;
        B022_OUT_PAGE_TITLE();
        if (pgmRtn) return;
        B023_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B030_BROWSE_BY_AC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICBACAC.DATA.AGR_NO);
        IBS.init(SCCGWA, CIRACR);
        IBS.init(SCCGWA, CICFA02);
        CIRACR.KEY.AGR_NO = CICBACAC.DATA.AGR_NO;
        T000_READ_CITACR();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "AGR-NO NOT EXIST");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND, CICBACAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_CI_NO = CIRACR.CI_NO;
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CIRACR.CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRCNT);
        CIRCNT.KEY.CI_NO = CIRACR.CI_NO;
        T000_READ_CITCNT();
        if (pgmRtn) return;
        WS_STA_NO = " ";
        if (CIRACR.FRM_APP.equalsIgnoreCase("DC")) {
            B040_READ_CITACRL_CHECK();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRACAC);
        CIRACAC.AGR_NO = CIRACR.KEY.AGR_NO;
        T000_STARTBR_CITACAC();
        if (pgmRtn) return;
        T000_READNEXT_CITACAC();
        if (pgmRtn) return;
        if (CICBACAC.DATA.PAGE_ROW > K_MAX_ROW 
            || CICBACAC.DATA.PAGE_ROW == 0) {
            WS_PAGE_ROW = K_MAX_ROW;
        } else {
            WS_PAGE_ROW = CICBACAC.DATA.PAGE_ROW;
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            if ((CICBACAC.DATA.CCY.trim().length() == 0 
                || CICBACAC.DATA.CCY.equalsIgnoreCase(CIRACAC.CCY) 
                || (CICBACAC.DATA.CCY.equalsIgnoreCase("NCH") 
                && !CIRACAC.CCY.equalsIgnoreCase("156"))) 
                && (CICBACAC.DATA.FRM_APP.trim().length() == 0 
                || CICBACAC.DATA.FRM_APP.equalsIgnoreCase(CIRACAC.FRM_APP)) 
                && (CICBACAC.DATA.STS == ' ' 
                || CICBACAC.DATA.STS == CIRACAC.ACAC_STS) 
                && (CICBACAC.DATA.TRCT_TYP.trim().length() == 0 
                || CICBACAC.DATA.TRCT_TYP.equalsIgnoreCase(CIRACR.CNTRCT_TYP))) {
                CEP.TRC(SCCGWA, WS_RECORD_NUM);
                WS_RECORD_NUM = WS_RECORD_NUM + 1;
                CEP.TRC(SCCGWA, WS_CX);
                CEP.TRC(SCCGWA, CICBACAC.DATA.PAGE_NUM);
                CEP.TRC(SCCGWA, WS_PAGE_ROW);
                WS_CX = ( CICBACAC.DATA.PAGE_NUM - 1 ) * WS_PAGE_ROW;
                CEP.TRC(SCCGWA, WS_RECORD_NUM);
                CEP.TRC(SCCGWA, WS_CX);
                CEP.TRC(SCCGWA, WS_I);
                if ((WS_RECORD_NUM > WS_CX) 
                    && (WS_I < WS_PAGE_ROW)) {
                    WS_I = WS_I + 1;
                    B021_DATA_TRANS_TO_FMT();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, WS_I);
            }
            T000_READNEXT_CITACAC();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITACAC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRACRL);
        CIRACRL.KEY.REL_AC_NO = CICBACAC.DATA.AGR_NO;
        T000_STARTBR_CITACRL_BY_RAC();
        if (pgmRtn) return;
        T000_READNEXT_CITACRL();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            CEP.TRC(SCCGWA, CIRACRL.KEY.AC_NO);
            IBS.init(SCCGWA, CIRACR);
            CIRACR.KEY.AGR_NO = CIRACRL.KEY.AC_NO;
            T000_READ_CITACR();
            if (pgmRtn) return;
            if (CIRACR.CNTRCT_TYP.equalsIgnoreCase(CICBACAC.DATA.TRCT_TYP) 
                || CICBACAC.DATA.TRCT_TYP.trim().length() == 0) {
                IBS.init(SCCGWA, CIRACAC);
                CIRACAC.AGR_NO = CIRACRL.KEY.AC_NO;
                T000_READ_CITACAC_DEFAULT();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
                if ((CICBACAC.DATA.CCY.trim().length() == 0 
                    || CICBACAC.DATA.CCY.equalsIgnoreCase(CIRACAC.CCY) 
                    || (CICBACAC.DATA.CCY.equalsIgnoreCase("NCH") 
                    && !CIRACAC.CCY.equalsIgnoreCase("156"))) 
                    && (CICBACAC.DATA.STS == ' ' 
                    || CICBACAC.DATA.STS == CIRACAC.ACAC_STS) 
                    && (CICBACAC.DATA.FRM_APP.trim().length() == 0 
                    || CICBACAC.DATA.FRM_APP.equalsIgnoreCase(CIRACAC.FRM_APP)) 
                    && SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                    CEP.TRC(SCCGWA, WS_RECORD_NUM);
                    WS_RECORD_NUM = WS_RECORD_NUM + 1;
                    CEP.TRC(SCCGWA, WS_CX);
                    CEP.TRC(SCCGWA, CICBACAC.DATA.PAGE_NUM);
                    CEP.TRC(SCCGWA, WS_PAGE_ROW);
                    WS_CX = ( CICBACAC.DATA.PAGE_NUM - 1 ) * WS_PAGE_ROW;
                    CEP.TRC(SCCGWA, WS_RECORD_NUM);
                    CEP.TRC(SCCGWA, WS_CX);
                    CEP.TRC(SCCGWA, WS_I);
                    if ((WS_RECORD_NUM > WS_CX) 
                        && (WS_I < WS_PAGE_ROW)) {
                        WS_I = WS_I + 1;
                        B021_DATA_TRANS_TO_FMT();
                        if (pgmRtn) return;
                    }
                    CEP.TRC(SCCGWA, WS_I);
                }
            }
            T000_READNEXT_CITACRL();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITACRL();
        if (pgmRtn) return;
        B022_OUT_PAGE_TITLE();
        if (pgmRtn) return;
        B023_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B040_READ_CITACRL_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACRL);
        CIRACRL.KEY.AC_REL = "13";
        CIRACRL.KEY.REL_AC_NO = CIRACR.KEY.AGR_NO;
        T000_READ_CITACRL_BY_REL_AC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_STA_NO = CIRACRL.KEY.AC_NO;
        }
    }
    public void S000_CALL_BPZPCLWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CAL-WORK-DAY", BPCOCLWD);
        if (BPCOCLWD.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCOCLWD.RC);
        }
    }
    public void S000_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-CCY-BAL", DDCIQBAL, true);
        CEP.TRC(SCCGWA, DDCIQBAL.RC.RC_CODE);
        if (DDCIQBAL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCIQBAL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CICBACAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIMMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-NFIN-M-MST", DDCIMMST, true);
        CEP.TRC(SCCGWA, DDCIMMST.RC.RC_CODE);
        if (DDCIMMST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCIMMST.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CICBACAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF, true);
        if (DCCUCINF.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CICBACAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_TDZACE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-AC-ENQ", TDCACE, true);
    }
    public void S000_CALL_IBZQINFS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZQINFS", IBCQINFS, true);
        if (IBCQINFS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCQINFS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CICBACAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZIQHLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-DCZIQHLD", DCCIQHLD, true);
    }
    public void T000_READ_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND, CICBACAC.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITBAS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITACR() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        IBS.READ(SCCGWA, CIRACR, CITACR_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READ_CITCNT() throws IOException,SQLException,Exception {
        CITCNT_RD = new DBParm();
        CITCNT_RD.TableName = "CITCNT";
        CITCNT_RD.eqWhere = "CI_NO";
        CITCNT_RD.where = "CNT_TYPE = '21'";
        IBS.READ(SCCGWA, CIRCNT, this, CITCNT_RD);
    }
    public void T000_STARTBR_CITACR() throws IOException,SQLException,Exception {
        CITACR_BR.rp = new DBParm();
        CITACR_BR.rp.TableName = "CITACR";
        CITACR_BR.rp.eqWhere = "CI_NO";
        CITACR_BR.rp.where = "SHOW_FLG = 'Y' "
            + "AND FRM_APP < > 'LN'";
        CITACR_BR.rp.order = "OPEN_DT,AGR_NO";
        IBS.STARTBR(SCCGWA, CIRACR, this, CITACR_BR);
    }
    public void T000_READNEXT_CITACR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRACR, this, CITACR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ACR_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ACR_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "BROWSE";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_CITACR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITACR_BR);
    }
    public void T000_STARTBR_CITACAC() throws IOException,SQLException,Exception {
        CITACAC_BR.rp = new DBParm();
        CITACAC_BR.rp.TableName = "CITACAC";
        CITACAC_BR.rp.eqWhere = "AGR_NO";
        CITACAC_BR.rp.where = "SUBSTR ( ACAC_CTL , 0 , 1 ) = '0' "
            + "AND FRM_APP < > 'LN'";
        CITACAC_BR.rp.order = "CCY,CR_FLG,OPEN_DT,ACAC_NO";
        IBS.STARTBR(SCCGWA, CIRACAC, this, CITACAC_BR);
    }
    public void T000_READNEXT_CITACAC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRACAC, this, CITACAC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ACAC_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ACAC_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITACAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "BROWSE";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITACRL_BY_AC_R() throws IOException,SQLException,Exception {
        CITACRL_RD = new DBParm();
        CITACRL_RD.TableName = "CITACRL";
        CITACRL_RD.eqWhere = "AC_NO , AC_REL";
        CITACRL_RD.where = "( EXP_DT = 0 "
            + "OR EXP_DT > :WS_AC_DATE ) "
            + "AND SUBSTR ( REL_CTL , 1 , 1 ) = '1'";
        IBS.READ(SCCGWA, CIRACRL, this, CITACRL_RD);
    }
    public void T000_READ_CITACRL_BY_REL_AC() throws IOException,SQLException,Exception {
        CITACRL_RD = new DBParm();
        CITACRL_RD.TableName = "CITACRL";
        CITACRL_RD.eqWhere = "AC_REL , REL_AC_NO";
        CITACRL_RD.where = "REL_STS = '0'";
        IBS.READ(SCCGWA, CIRACRL, this, CITACRL_RD);
    }
    public void T000_READ_CITACAC_DEFAULT() throws IOException,SQLException,Exception {
        CITACAC_RD = new DBParm();
        CITACAC_RD.TableName = "CITACAC";
        CITACAC_RD.eqWhere = "AGR_NO";
        CITACAC_RD.where = "SUBSTR ( ACAC_CTL , 2 , 1 ) = '1'";
        IBS.READ(SCCGWA, CIRACAC, this, CITACAC_RD);
    }
    public void T000_ENDBR_CITACAC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITACAC_BR);
    }
    public void T000_STARTBR_CITACRL_BY_RAC() throws IOException,SQLException,Exception {
        CITACRL_BR.rp = new DBParm();
        CITACRL_BR.rp.TableName = "CITACRL";
        CITACRL_BR.rp.eqWhere = "REL_AC_NO";
        CITACRL_BR.rp.where = "( EXP_DT = 0 "
            + "OR EXP_DT > :WS_AC_DATE ) "
            + "AND AC_REL IN ( '05' , '06' , '07' )";
        IBS.STARTBR(SCCGWA, CIRACRL, this, CITACRL_BR);
    }
    public void T000_READNEXT_CITACRL() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRACRL, this, CITACRL_BR);
    }
    public void T000_ENDBR_CITACRL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITACRL_BR);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_C);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_D);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_Q);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_M);
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
        CEP.TRC(SCCGWA, CICBACAC.RC);
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
