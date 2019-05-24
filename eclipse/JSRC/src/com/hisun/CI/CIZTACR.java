package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.BP.*;
import com.hisun.TD.*;
import com.hisun.DD.*;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZTACR {
    int JIBS_tmp_int;
    SimpleDateFormat JIBS_sdf;
    Date JIBS_date;
    DBParm CITBAS_RD;
    DBParm CITACAC_RD;
    brParm CITACR_BR = new brParm();
    brParm CITACRL_BR = new brParm();
    brParm CITACAC_BR = new brParm();
    DBParm CITACR_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 10;
    int K_MAX_COL = 0;
    int K_COL_CNT = 0;
    int WS_I = 0;
    int WS_II = 0;
    String WS_AGR_NO = " ";
    String WS_REL_AC = " ";
    String WS_REL_TYP = " ";
    String WS_TS = " ";
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICOTACR CICOTACR = new CICOTACR();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    CIRBAS CIRBAS = new CIRBAS();
    CIRACR CIRACR = new CIRACR();
    CIRACAC CIRACAC = new CIRACAC();
    CIRACRL CIRACRL = new CIRACRL();
    TDCACE TDCACE = new TDCACE();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    DDCIMMST DDCIMMST = new DDCIMMST();
    DDCUINVS DDCUINVS = new DDCUINVS();
    int WS_AC_DATE = 0;
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICTACR CICTACR;
    public void MP(SCCGWA SCCGWA, CICTACR CICTACR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICTACR = CICTACR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZTACR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICTACR.RC);
        WS_AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (CICTACR.FUNC == '1') {
            B020_BROWSE_ACR();
            if (pgmRtn) return;
        } else if (CICTACR.FUNC == '2') {
            B030_BROWSE_ACAC();
            if (pgmRtn) return;
        } else if (CICTACR.FUNC == '3') {
            B040_INQUIRE_ACR();
            if (pgmRtn) return;
        } else if (CICTACR.FUNC == '4') {
            B050_BROWSE_ACAC_BY_CI();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "FUNC INPUT ERROR");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, CICTACR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_BROWSE_ACR() throws IOException,SQLException,Exception {
        R000_TRC_TS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICTACR.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICTACR.DATA.AGR_NO);
        if (CICTACR.DATA.CI_NO.trim().length() == 0 
            && CICTACR.DATA.AGR_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CIRACR);
            CIRACR.KEY.AGR_NO = CICTACR.DATA.AGR_NO;
            T000_READ_CITACR();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.TRC(SCCGWA, "ACR INF NOT EXIST");
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND, CICTACR.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            CICTACR.DATA.CI_NO = CIRACR.CI_NO;
        }
        CEP.TRC(SCCGWA, CICTACR.DATA.CI_NO);
        if (CICTACR.DATA.CI_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CIRBAS);
            CIRBAS.KEY.CI_NO = CICTACR.DATA.CI_NO;
            T000_READ_CITBAS();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.TRC(SCCGWA, "CI INF NOT EXIST");
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND, CICTACR.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
            CEP.TRC(SCCGWA, "CI-NO OR AGR-NO MUST INPUT");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, CICTACR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRACR);
        CIRACR.CI_NO = CICTACR.DATA.CI_NO;
        T000_STARTBR_CITACR_BY_CI();
        if (pgmRtn) return;
        T000_READNEXT_CITACR();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "ACR INF NOT FOUND");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND, CICTACR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R000_01_OUT_TITLE();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && SCCMPAG.FUNC != 'E') {
            if ((CICTACR.DATA.ENTY_TYP == ' ' 
                || CICTACR.DATA.ENTY_TYP == CIRACR.ENTY_TYP) 
                && (CICTACR.DATA.FRM_APP.trim().length() == 0 
                || CICTACR.DATA.FRM_APP.equalsIgnoreCase(CIRACR.FRM_APP)) 
                && (CICTACR.DATA.CNTRCT_TYP.trim().length() == 0 
                || CICTACR.DATA.CNTRCT_TYP.equalsIgnoreCase(CIRACR.CNTRCT_TYP)) 
                && (CICTACR.DATA.PROD_CD.trim().length() == 0 
                || CICTACR.DATA.PROD_CD.equalsIgnoreCase(CIRACR.PROD_CD))) {
                IBS.init(SCCGWA, BPCPQPRD);
                BPCPQPRD.PRDT_CODE = CIRACR.PROD_CD;
                S000_CALL_BPZPQPRD();
                if (pgmRtn) return;
                R000_02_OUT_ACR_DATA();
                if (pgmRtn) return;
                if (CICTACR.DATA.REL_AREA[1-1].REL_TYP.trim().length() > 0) {
                    WS_AGR_NO = CIRACR.KEY.AGR_NO;
                    R000_INQUIRE_ACR_REL();
                    if (pgmRtn) return;
                }
            }
            T000_READNEXT_CITACR();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITACR();
        if (pgmRtn) return;
    }
    public void B030_BROWSE_ACAC() throws IOException,SQLException,Exception {
        R000_TRC_TS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICTACR.DATA.AGR_NO);
        if (CICTACR.DATA.AGR_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "AGR-NO MUST INPUT");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, CICTACR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRACR);
        CIRACR.KEY.AGR_NO = CICTACR.DATA.AGR_NO;
        T000_READ_CITACR();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "AGR-NO NOT EXIST");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND, CICTACR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRACAC);
        CIRACAC.AGR_NO = CICTACR.DATA.AGR_NO;
        T000_STARTBR_CITACAC_BY_ACR();
        if (pgmRtn) return;
        T000_READNEXT_CITACAC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "ACAC INF NOT FOUND");
        }
        R000_01_OUT_TITLE();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && SCCMPAG.FUNC != 'E') {
            if (CIRACAC.FRM_APP.equalsIgnoreCase(CICTACR.DATA.FRM_APP) 
                || CICTACR.DATA.FRM_APP.trim().length() == 0) {
                IBS.init(SCCGWA, CICOTACR);
                if (CIRACAC.FRM_APP.equalsIgnoreCase("TD")) {
                    IBS.init(SCCGWA, TDCACE);
                    TDCACE.PAGE_INF.AC_NO = CIRACR.KEY.AGR_NO;
                    TDCACE.PAGE_INF.I_BV_NO = CIRACAC.BV_NO;
                    TDCACE.PAGE_INF.I_AC_SEQ = CIRACAC.AGR_SEQ;
                    CEP.TRC(SCCGWA, TDCACE.PAGE_INF.AC_NO);
                    CEP.TRC(SCCGWA, TDCACE.PAGE_INF.I_BV_NO);
                    CEP.TRC(SCCGWA, TDCACE.PAGE_INF.I_AC_SEQ);
                    S000_CALL_TDZACE();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, TDCACE.DATA[1-1].BAL);
                    CEP.TRC(SCCGWA, TDCACE.DATA[1-1].HBAL);
                    CEP.TRC(SCCGWA, TDCACE.DATA[1-1].ACO_STSW);
                    CEP.TRC(SCCGWA, TDCACE.DATA[1-1].TERM);
                    CEP.TRC(SCCGWA, TDCACE.DATA[1-1].SDT);
                    CEP.TRC(SCCGWA, TDCACE.DATA[1-1].DDT);
                    CEP.TRC(SCCGWA, TDCACE.DATA[1-1].AC_TYP);
                    CEP.TRC(SCCGWA, TDCACE.DATA[1-1].KY_BAL);
                    CICOTACR.DATA.ACAC_BAL = TDCACE.DATA[1-1].BAL;
                    CICOTACR.DATA.HOLD_AMT = TDCACE.DATA[1-1].HBAL;
                    CICOTACR.DATA.ACAC_STSW = TDCACE.DATA[1-1].ACO_STSW;
                    CICOTACR.DATA.TERM = TDCACE.DATA[1-1].TERM;
                    CICOTACR.DATA.START_DATE = TDCACE.DATA[1-1].SDT;
                    CICOTACR.DATA.EXP_DATE = TDCACE.DATA[1-1].DDT;
                    CICOTACR.DATA.CNTRCT_TYP = TDCACE.DATA[1-1].AC_TYP;
                    CICOTACR.DATA.LAST_BAL = TDCACE.DATA[1-1].LAST_BAL;
                    CICOTACR.DATA.USABLE_BAL = TDCACE.DATA[1-1].KY_BAL;
                    CICOTACR.DATA.BV_NO = TDCACE.DATA[1-1].BV_NO;
                    CICOTACR.DATA.CCY = TDCACE.DATA[1-1].CCY;
                    CICOTACR.DATA.CR_FLG = TDCACE.DATA[1-1].CCY_TYP;
                } else if (CIRACAC.FRM_APP.equalsIgnoreCase("DD") 
                        && CIRACAC.AID.trim().length() == 0 
                        && CIRACR.ENTY_TYP != '4') {
                    IBS.init(SCCGWA, DDCIQBAL);
                    DDCIQBAL.DATA.AC = CIRACR.KEY.AGR_NO;
                    DDCIQBAL.DATA.CCY = CIRACAC.CCY;
                    DDCIQBAL.DATA.CCY_TYPE = CIRACAC.CR_FLG;
                    S000_CALL_DDZIQBAL();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, DDCIQBAL.DATA.CURR_BAL);
                    CEP.TRC(SCCGWA, DDCIQBAL.DATA.HOLD_BAL);
                    CEP.TRC(SCCGWA, DDCIQBAL.DATA.CCY_STS_WORD);
                    CEP.TRC(SCCGWA, DDCIQBAL.DATA.AVL_BAL);
                    CICOTACR.DATA.ACAC_BAL = DDCIQBAL.DATA.CURR_BAL;
                    CICOTACR.DATA.HOLD_AMT = DDCIQBAL.DATA.HOLD_BAL;
                    CICOTACR.DATA.ACAC_STSW = DDCIQBAL.DATA.CCY_STS_WORD;
                    CICOTACR.DATA.LAST_BAL = DDCIQBAL.DATA.LAST_BAL;
                    CICOTACR.DATA.USABLE_BAL = DDCIQBAL.DATA.AVL_BAL;
                    CICOTACR.DATA.CCY = CIRACAC.CCY;
                    CICOTACR.DATA.CR_FLG = CIRACAC.CR_FLG;
                    IBS.init(SCCGWA, DDCIMMST);
                    DDCIMMST.DATA.KEY.AC_NO = CIRACR.KEY.AGR_NO;
                    DDCIMMST.TX_TYPE = 'I';
                    S000_CALL_DDZIMMST();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, DDCIMMST.DATA.OPEN_DATE);
                    CEP.TRC(SCCGWA, DDCIMMST.DATA.PSBK_NO);
                    CICOTACR.DATA.START_DATE = DDCIMMST.DATA.OPEN_DATE;
                    CICOTACR.DATA.BV_NO = DDCIMMST.DATA.PSBK_NO;
                } else if (CIRACAC.FRM_APP.equalsIgnoreCase("DD") 
                        && CIRACR.ENTY_TYP == '4') {
                    IBS.init(SCCGWA, DDCUINVS);
                    DDCUINVS.I_FUNC = '4';
                    DDCUINVS.VS_AC = CIRACAC.AGR_NO;
                    DDCUINVS.CCY = CIRACAC.CCY;
                    DDCUINVS.CCY_TYP = CIRACAC.CR_FLG;
                    S000_CALL_DDZUINVS();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, DDCUINVS.O_CUR_BAL);
                    CEP.TRC(SCCGWA, DDCUINVS.O_ACO_STSW);
                    CEP.TRC(SCCGWA, DDCUINVS.O_LAST_BAL);
                    CEP.TRC(SCCGWA, DDCUINVS.O_AVA_BAL);
                    CICOTACR.DATA.ACAC_BAL = DDCUINVS.O_CUR_BAL;
                    CICOTACR.DATA.ACAC_STSW = DDCUINVS.O_ACO_STSW;
                    CICOTACR.DATA.LAST_BAL = DDCUINVS.O_LAST_BAL;
                    CICOTACR.DATA.USABLE_BAL = DDCUINVS.O_AVA_BAL;
                    CICOTACR.DATA.CCY = CIRACAC.CCY;
                    CICOTACR.DATA.CR_FLG = CIRACAC.CR_FLG;
                } else if (CIRACAC.FRM_APP.equalsIgnoreCase("LN")) {
                    CICOTACR.DATA.BV_NO = CIRACAC.BV_NO;
                } else {
                    CEP.TRC(SCCGWA, "FRM-APP ERROR");
                    CEP.TRC(SCCGWA, CIRACAC.KEY.ACAC_NO);
                }
                IBS.init(SCCGWA, BPCPQPRD);
                BPCPQPRD.PRDT_CODE = CIRACAC.PROD_CD;
                S000_CALL_BPZPQPRD();
                if (pgmRtn) return;
                R000_04_OUT_ACAC_DATA();
                if (pgmRtn) return;
            }
            T000_READNEXT_CITACAC();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITACAC();
        if (pgmRtn) return;
        if (CICTACR.DATA.REL_AREA[1-1].REL_TYP.trim().length() > 0) {
            WS_AGR_NO = CIRACR.KEY.AGR_NO;
            R000_INQUIRE_ACR_REL();
            if (pgmRtn) return;
        }
    }
    public void B040_INQUIRE_ACR() throws IOException,SQLException,Exception {
        R000_TRC_TS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICTACR.DATA.AGR_NO);
        if (CICTACR.DATA.AGR_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "AGR-NO MUST INPUT");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, CICTACR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRACR);
        CIRACR.KEY.AGR_NO = CICTACR.DATA.AGR_NO;
        T000_READ_CITACR();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "AGR-NO NOT EXIST");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_AC_NOT_EXIST, CICTACR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R000_01_OUT_TITLE();
        if (pgmRtn) return;
        R000_02_OUT_ACR_DATA();
        if (pgmRtn) return;
        if (CICTACR.DATA.REL_AREA[1-1].REL_TYP.trim().length() > 0) {
            WS_AGR_NO = CIRACR.KEY.AGR_NO;
            R000_INQUIRE_ACR_REL();
            if (pgmRtn) return;
        }
    }
    public void B050_BROWSE_ACAC_BY_CI() throws IOException,SQLException,Exception {
        R000_TRC_TS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICTACR.DATA.CI_NO);
        if (CICTACR.DATA.CI_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "CI-NO MUST INPUT");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_NO_MUST_INPUT, CICTACR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICTACR.DATA.CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "CI-NO NOT EXIST");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND, CICTACR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRACAC);
        CIRACAC.CI_NO = CICTACR.DATA.CI_NO;
        CIRACAC.PROD_CD = CICTACR.DATA.PROD_CD;
        CIRACAC.FRM_APP = CICTACR.DATA.FRM_APP;
        T000_STARTBR_CITACAC_BY_CI();
        if (pgmRtn) return;
        T000_READNEXT_CITACAC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "ACAC INF NOT FOUND");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACAC_INF_NOTFND, CICTACR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R000_01_OUT_TITLE();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && SCCMPAG.FUNC != 'E') {
            IBS.init(SCCGWA, CICOTACR);
            IBS.init(SCCGWA, CIRACR);
            CIRACR.KEY.AGR_NO = CIRACAC.AGR_NO;
            T000_READ_CITACR();
            if (pgmRtn) return;
            if (CIRACAC.FRM_APP.equalsIgnoreCase("TD")) {
                IBS.init(SCCGWA, TDCACE);
                TDCACE.PAGE_INF.AC_NO = CIRACAC.AGR_NO;
                TDCACE.PAGE_INF.I_BV_NO = CIRACAC.BV_NO;
                TDCACE.PAGE_INF.I_AC_SEQ = CIRACAC.AGR_SEQ;
                CEP.TRC(SCCGWA, TDCACE.PAGE_INF.AC_NO);
                CEP.TRC(SCCGWA, TDCACE.PAGE_INF.I_BV_NO);
                CEP.TRC(SCCGWA, TDCACE.PAGE_INF.I_AC_SEQ);
                S000_CALL_TDZACE();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, TDCACE.DATA[1-1].BAL);
                CEP.TRC(SCCGWA, TDCACE.DATA[1-1].HBAL);
                CEP.TRC(SCCGWA, TDCACE.DATA[1-1].ACO_STSW);
                CEP.TRC(SCCGWA, TDCACE.DATA[1-1].TERM);
                CEP.TRC(SCCGWA, TDCACE.DATA[1-1].SDT);
                CEP.TRC(SCCGWA, TDCACE.DATA[1-1].DDT);
                CEP.TRC(SCCGWA, TDCACE.DATA[1-1].AC_TYP);
                CEP.TRC(SCCGWA, TDCACE.DATA[1-1].KY_BAL);
                CICOTACR.DATA.ACAC_BAL = TDCACE.DATA[1-1].BAL;
                CICOTACR.DATA.HOLD_AMT = TDCACE.DATA[1-1].HBAL;
                CICOTACR.DATA.ACAC_STSW = TDCACE.DATA[1-1].ACO_STSW;
                CICOTACR.DATA.TERM = TDCACE.DATA[1-1].TERM;
                CICOTACR.DATA.START_DATE = TDCACE.DATA[1-1].SDT;
                CICOTACR.DATA.EXP_DATE = TDCACE.DATA[1-1].DDT;
                CICOTACR.DATA.CNTRCT_TYP = TDCACE.DATA[1-1].AC_TYP;
                CICOTACR.DATA.LAST_BAL = TDCACE.DATA[1-1].LAST_BAL;
                CICOTACR.DATA.USABLE_BAL = TDCACE.DATA[1-1].KY_BAL;
                CICOTACR.DATA.BV_NO = TDCACE.DATA[1-1].BV_NO;
                CICOTACR.DATA.CCY = TDCACE.DATA[1-1].CCY;
                CICOTACR.DATA.CR_FLG = TDCACE.DATA[1-1].CCY_TYP;
            } else if (CIRACAC.FRM_APP.equalsIgnoreCase("DD") 
                    && CIRACAC.AID.trim().length() == 0) {
                IBS.init(SCCGWA, DDCIQBAL);
                DDCIQBAL.DATA.AC = CIRACAC.AGR_NO;
                DDCIQBAL.DATA.CCY = CIRACAC.CCY;
                DDCIQBAL.DATA.CCY_TYPE = CIRACAC.CR_FLG;
                S000_CALL_DDZIQBAL();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, DDCIQBAL.DATA.CURR_BAL);
                CEP.TRC(SCCGWA, DDCIQBAL.DATA.HOLD_BAL);
                CEP.TRC(SCCGWA, DDCIQBAL.DATA.CCY_STS_WORD);
                CEP.TRC(SCCGWA, DDCIQBAL.DATA.AVL_BAL);
                CICOTACR.DATA.ACAC_BAL = DDCIQBAL.DATA.CURR_BAL;
                CICOTACR.DATA.HOLD_AMT = DDCIQBAL.DATA.HOLD_BAL;
                CICOTACR.DATA.ACAC_STSW = DDCIQBAL.DATA.CCY_STS_WORD;
                CICOTACR.DATA.LAST_BAL = DDCIQBAL.DATA.LAST_BAL;
                CICOTACR.DATA.USABLE_BAL = DDCIQBAL.DATA.AVL_BAL;
                CICOTACR.DATA.CCY = CIRACAC.CCY;
                CICOTACR.DATA.CR_FLG = CIRACAC.CR_FLG;
                IBS.init(SCCGWA, DDCIMMST);
                DDCIMMST.DATA.KEY.AC_NO = CIRACAC.AGR_NO;
                DDCIMMST.TX_TYPE = 'I';
                S000_CALL_DDZIMMST();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, DDCIMMST.DATA.OPEN_DATE);
                CEP.TRC(SCCGWA, DDCIMMST.DATA.PSBK_NO);
                CICOTACR.DATA.START_DATE = DDCIMMST.DATA.OPEN_DATE;
                CICOTACR.DATA.BV_NO = DDCIMMST.DATA.PSBK_NO;
            } else {
                CEP.TRC(SCCGWA, "FRM-APP ERROR");
                CEP.TRC(SCCGWA, CIRACAC.KEY.ACAC_NO);
            }
            IBS.init(SCCGWA, BPCPQPRD);
            BPCPQPRD.PRDT_CODE = CIRACAC.PROD_CD;
            S000_CALL_BPZPQPRD();
            if (pgmRtn) return;
            R000_04_OUT_ACAC_DATA();
            if (pgmRtn) return;
            T000_READNEXT_CITACAC();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITACAC();
        if (pgmRtn) return;
    }
    public void R000_INQUIRE_ACR_REL() throws IOException,SQLException,Exception {
        R000_TRC_TS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_AGR_NO);
        IBS.init(SCCGWA, CIRACRL);
        CIRACRL.KEY.AC_NO = WS_AGR_NO;
        T000_STARTBR_CITACRL_BY_AC();
        if (pgmRtn) return;
        T000_READNEXT_CITACRL();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && SCCMPAG.FUNC != 'E') {
            for (WS_I = 1; WS_I <= 30 
                && CICTACR.DATA.REL_AREA[WS_I-1].REL_TYP.trim().length() != 0; WS_I += 1) {
                CEP.TRC(SCCGWA, CIRACRL.KEY.AC_REL);
                WS_REL_TYP = CIRACRL.KEY.AC_REL;
                if (CICTACR.DATA.REL_AREA[WS_I-1].REL_TYP.equalsIgnoreCase(WS_REL_TYP)) {
                    CEP.TRC(SCCGWA, "OUT ACRL INF");
                    IBS.init(SCCGWA, CIRACR);
                    WS_REL_AC = CIRACRL.KEY.AC_NO;
                    CIRACR.KEY.AGR_NO = CIRACRL.KEY.REL_AC_NO;
                    CEP.TRC(SCCGWA, WS_REL_AC);
                    CEP.TRC(SCCGWA, CIRACR.KEY.AGR_NO);
                    T000_READ_CITACR();
                    if (pgmRtn) return;
                    IBS.init(SCCGWA, BPCPQPRD);
                    BPCPQPRD.PRDT_CODE = CIRACR.PROD_CD;
                    S000_CALL_BPZPQPRD();
                    if (pgmRtn) return;
                    R000_03_OUT_REL_DATA();
                    if (pgmRtn) return;
                }
            }
            T000_READNEXT_CITACRL();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITACRL();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRACRL);
        CIRACRL.KEY.REL_AC_NO = WS_AGR_NO;
        T000_STARTBR_CITACRL_BY_REL_AC();
        if (pgmRtn) return;
        T000_READNEXT_CITACRL();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && SCCMPAG.FUNC != 'E') {
                if (CIRACRL.KEY.AC_REL == null) CIRACRL.KEY.AC_REL = "";
                JIBS_tmp_int = CIRACRL.KEY.AC_REL.length();
                for (int i=0;i<2-JIBS_tmp_int;i++) CIRACRL.KEY.AC_REL += " ";
            if (CIRACRL.KEY.AC_REL.substring(0, 1).equalsIgnoreCase("0")) {
                if (WS_REL_TYP == null) WS_REL_TYP = "";
                JIBS_tmp_int = WS_REL_TYP.length();
                for (int i=0;i<2-JIBS_tmp_int;i++) WS_REL_TYP += " ";
                WS_REL_TYP = "8" + WS_REL_TYP.substring(1);
                if (CIRACRL.KEY.AC_REL == null) CIRACRL.KEY.AC_REL = "";
                JIBS_tmp_int = CIRACRL.KEY.AC_REL.length();
                for (int i=0;i<2-JIBS_tmp_int;i++) CIRACRL.KEY.AC_REL += " ";
                if (WS_REL_TYP == null) WS_REL_TYP = "";
                JIBS_tmp_int = WS_REL_TYP.length();
                for (int i=0;i<2-JIBS_tmp_int;i++) WS_REL_TYP += " ";
                WS_REL_TYP = WS_REL_TYP.substring(0, 2 - 1) + CIRACRL.KEY.AC_REL.substring(2 - 1, 2 + 1 - 1) + WS_REL_TYP.substring(2 + 1 - 1);
                CEP.TRC(SCCGWA, WS_REL_TYP);
                if (CIRACRL.KEY.AC_REL == null) CIRACRL.KEY.AC_REL = "";
                JIBS_tmp_int = CIRACRL.KEY.AC_REL.length();
                for (int i=0;i<2-JIBS_tmp_int;i++) CIRACRL.KEY.AC_REL += " ";
            } else if (CIRACRL.KEY.AC_REL.substring(0, 1).equalsIgnoreCase("1")) {
                if (WS_REL_TYP == null) WS_REL_TYP = "";
                JIBS_tmp_int = WS_REL_TYP.length();
                for (int i=0;i<2-JIBS_tmp_int;i++) WS_REL_TYP += " ";
                WS_REL_TYP = "9" + WS_REL_TYP.substring(1);
                if (CIRACRL.KEY.AC_REL == null) CIRACRL.KEY.AC_REL = "";
                JIBS_tmp_int = CIRACRL.KEY.AC_REL.length();
                for (int i=0;i<2-JIBS_tmp_int;i++) CIRACRL.KEY.AC_REL += " ";
                if (WS_REL_TYP == null) WS_REL_TYP = "";
                JIBS_tmp_int = WS_REL_TYP.length();
                for (int i=0;i<2-JIBS_tmp_int;i++) WS_REL_TYP += " ";
                WS_REL_TYP = WS_REL_TYP.substring(0, 2 - 1) + CIRACRL.KEY.AC_REL.substring(2 - 1, 2 + 1 - 1) + WS_REL_TYP.substring(2 + 1 - 1);
                CEP.TRC(SCCGWA, WS_REL_TYP);
            } else {
            }
            for (WS_I = 1; WS_I <= 30 
                && CICTACR.DATA.REL_AREA[WS_I-1].REL_TYP.trim().length() != 0; WS_I += 1) {
                CEP.TRC(SCCGWA, CIRACRL.KEY.AC_REL);
                if (CICTACR.DATA.REL_AREA[WS_I-1].REL_TYP.equalsIgnoreCase(WS_REL_TYP)) {
                    CEP.TRC(SCCGWA, "OUT ACRL INF");
                    IBS.init(SCCGWA, CIRACR);
                    CIRACR.KEY.AGR_NO = CIRACRL.KEY.AC_NO;
                    WS_REL_AC = CIRACRL.KEY.REL_AC_NO;
                    CEP.TRC(SCCGWA, WS_REL_AC);
                    CEP.TRC(SCCGWA, CIRACR.KEY.AGR_NO);
                    T000_READ_CITACR();
                    if (pgmRtn) return;
                    IBS.init(SCCGWA, BPCPQPRD);
                    BPCPQPRD.PRDT_CODE = CIRACR.PROD_CD;
                    S000_CALL_BPZPQPRD();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, CICTACR.DATA.REL_AREA[WS_I-1].REL_TYP);
                    R000_03_OUT_REL_DATA();
                    if (pgmRtn) return;
                }
            }
            T000_READNEXT_CITACRL();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITACRL();
        if (pgmRtn) return;
    }
    public void R000_01_OUT_TITLE() throws IOException,SQLException,Exception {
        R000_TRC_TS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_02_OUT_ACR_DATA() throws IOException,SQLException,Exception {
        R000_TRC_TS();
        if (pgmRtn) return;
        WS_II = WS_II + 1;
        CEP.TRC(SCCGWA, WS_II);
        IBS.init(SCCGWA, CICOTACR);
        CICOTACR.DATA.AC_TYP = "A1";
        CICOTACR.DATA.AC_NO = CIRACR.KEY.AGR_NO;
        CICOTACR.DATA.PROD_CD = CIRACR.PROD_CD;
        CICOTACR.DATA.CDESC = BPCPQPRD.PRDT_NAME;
        CICOTACR.DATA.CNTRCT_TYP = CIRACR.CNTRCT_TYP;
        CICOTACR.DATA.FRM_APP = CIRACR.FRM_APP;
        CICOTACR.DATA.STS = CIRACR.STS;
        CICOTACR.DATA.CCY = CIRACR.CCY;
        CICOTACR.DATA.OPN_BR = CIRACR.OPN_BR;
        CICOTACR.DATA.OPEN_DT = CIRACR.OPEN_DT;
        CICOTACR.DATA.CI_NO = CIRACR.CI_NO;
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CIRACR.CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        CICOTACR.DATA.ID_TYPE = CIRBAS.ID_TYPE;
        CICOTACR.DATA.ID_NO = CIRBAS.ID_NO;
        CICOTACR.DATA.CI_NM = CIRBAS.CI_NM;
        CICOTACR.DATA.CI_TYPE = CIRBAS.CI_TYP;
        CEP.TRC(SCCGWA, CIRBAS.CI_TYP);
        if (CIRBAS.CI_TYP == '1') {
            CICOTACR.DATA.AC_CNM = CIRBAS.CI_NM;
        } else {
            CICOTACR.DATA.AC_CNM = CIRACR.AC_CNM;
        }
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, CICOTACR);
        SCCMPAG.DATA_LEN = 976;
        CEP.TRC(SCCGWA, SCCMPAG.DATA_LEN);
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_03_OUT_REL_DATA() throws IOException,SQLException,Exception {
        R000_TRC_TS();
        if (pgmRtn) return;
        WS_II = WS_II + 1;
        CEP.TRC(SCCGWA, WS_II);
        IBS.init(SCCGWA, CICOTACR);
        CICOTACR.DATA.AC_TYP = "A1";
        CICOTACR.DATA.AC_NO = CIRACR.KEY.AGR_NO;
        CICOTACR.DATA.REL_TYP = CICTACR.DATA.REL_AREA[WS_I-1].REL_TYP;
        CICOTACR.DATA.REL_AC_NO = WS_REL_AC;
        CEP.TRC(SCCGWA, CICOTACR.DATA.AC_NO);
        CEP.TRC(SCCGWA, CICOTACR.DATA.REL_TYP);
        CEP.TRC(SCCGWA, CICOTACR.DATA.REL_AC_NO);
        CICOTACR.DATA.PROD_CD = CIRACR.PROD_CD;
        CICOTACR.DATA.CDESC = BPCPQPRD.PRDT_NAME;
        CICOTACR.DATA.CNTRCT_TYP = CIRACR.CNTRCT_TYP;
        CICOTACR.DATA.FRM_APP = CIRACR.FRM_APP;
        CICOTACR.DATA.STS = CIRACR.STS;
        CICOTACR.DATA.CCY = CIRACR.CCY;
        CICOTACR.DATA.OPN_BR = CIRACR.OPN_BR;
        CICOTACR.DATA.OPEN_DT = CIRACR.OPEN_DT;
        CICOTACR.DATA.CI_NO = CIRACR.CI_NO;
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CIRACR.CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        CICOTACR.DATA.CI_TYPE = CIRBAS.CI_TYP;
        CICOTACR.DATA.ID_TYPE = CIRBAS.ID_TYPE;
        CICOTACR.DATA.ID_NO = CIRBAS.ID_NO;
        CICOTACR.DATA.CI_NM = CIRBAS.CI_NM;
        CEP.TRC(SCCGWA, CIRBAS.CI_TYP);
        if (CIRBAS.CI_TYP == '1') {
            CICOTACR.DATA.AC_CNM = CIRBAS.CI_NM;
        } else {
            CICOTACR.DATA.AC_CNM = CIRACR.AC_CNM;
        }
        if (CIRACRL.KEY.REL_CTL == null) CIRACRL.KEY.REL_CTL = "";
        JIBS_tmp_int = CIRACRL.KEY.REL_CTL.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) CIRACRL.KEY.REL_CTL += " ";
        if (CIRACRL.KEY.REL_CTL.substring(0, 1).equalsIgnoreCase("1")) {
            CICOTACR.DATA.DEF_FLG = 'Y';
        } else {
            CICOTACR.DATA.DEF_FLG = 'N';
        }
        if (CICOTACR.DATA.REL_TYP.equalsIgnoreCase("85") 
            || CICOTACR.DATA.REL_TYP.equalsIgnoreCase("86") 
            || CICOTACR.DATA.REL_TYP.equalsIgnoreCase("87") 
            || CICOTACR.DATA.REL_TYP.equalsIgnoreCase("04") 
            || CICOTACR.DATA.REL_TYP.equalsIgnoreCase("03")) {
            IBS.init(SCCGWA, CIRACAC);
            CIRACAC.AGR_NO = CICOTACR.DATA.AC_NO;
            T000_READ_CITACAC_DEFAULT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CIRACAC.KEY.ACAC_NO);
            if (CIRACAC.FRM_APP.equalsIgnoreCase("TD")) {
                IBS.init(SCCGWA, TDCACE);
                TDCACE.PAGE_INF.AC_NO = CICOTACR.DATA.AC_NO;
                TDCACE.PAGE_INF.I_BV_NO = CIRACAC.BV_NO;
                TDCACE.PAGE_INF.I_AC_SEQ = CIRACAC.AGR_SEQ;
                CEP.TRC(SCCGWA, TDCACE.PAGE_INF.AC_NO);
                CEP.TRC(SCCGWA, TDCACE.PAGE_INF.I_BV_NO);
                CEP.TRC(SCCGWA, TDCACE.PAGE_INF.I_AC_SEQ);
                S000_CALL_TDZACE();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, TDCACE.DATA[1-1].BAL);
                CEP.TRC(SCCGWA, TDCACE.DATA[1-1].HBAL);
                CEP.TRC(SCCGWA, TDCACE.DATA[1-1].ACO_STSW);
                CEP.TRC(SCCGWA, TDCACE.DATA[1-1].TERM);
                CEP.TRC(SCCGWA, TDCACE.DATA[1-1].SDT);
                CEP.TRC(SCCGWA, TDCACE.DATA[1-1].DDT);
                CEP.TRC(SCCGWA, TDCACE.DATA[1-1].AC_TYP);
                CEP.TRC(SCCGWA, TDCACE.DATA[1-1].KY_BAL);
                CICOTACR.DATA.ACAC_BAL = TDCACE.DATA[1-1].BAL;
                CICOTACR.DATA.HOLD_AMT = TDCACE.DATA[1-1].HBAL;
                CICOTACR.DATA.ACAC_STSW = TDCACE.DATA[1-1].ACO_STSW;
                CICOTACR.DATA.TERM = TDCACE.DATA[1-1].TERM;
                CICOTACR.DATA.START_DATE = TDCACE.DATA[1-1].SDT;
                CICOTACR.DATA.EXP_DATE = TDCACE.DATA[1-1].DDT;
                CICOTACR.DATA.LAST_BAL = TDCACE.DATA[1-1].LAST_BAL;
                CICOTACR.DATA.AGR_SEQ = CIRACAC.AGR_SEQ;
                CICOTACR.DATA.USABLE_BAL = TDCACE.DATA[1-1].KY_BAL;
                CICOTACR.DATA.BV_NO = TDCACE.DATA[1-1].BV_NO;
                CICOTACR.DATA.CCY = TDCACE.DATA[1-1].CCY;
                CICOTACR.DATA.CR_FLG = TDCACE.DATA[1-1].CCY_TYP;
            } else if (CIRACAC.FRM_APP.equalsIgnoreCase("DD") 
                    && CIRACAC.AID.trim().length() == 0) {
                IBS.init(SCCGWA, DDCIQBAL);
                DDCIQBAL.DATA.AC = CICOTACR.DATA.AC_NO;
                DDCIQBAL.DATA.CCY = CIRACAC.CCY;
                DDCIQBAL.DATA.CCY_TYPE = CIRACAC.CR_FLG;
                S000_CALL_DDZIQBAL();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, DDCIQBAL.DATA.CURR_BAL);
                CEP.TRC(SCCGWA, DDCIQBAL.DATA.HOLD_BAL);
                CEP.TRC(SCCGWA, DDCIQBAL.DATA.CCY_STS_WORD);
                CEP.TRC(SCCGWA, DDCIQBAL.DATA.AVL_BAL);
                CICOTACR.DATA.CCY = CIRACAC.CCY;
                CICOTACR.DATA.CR_FLG = CIRACAC.CR_FLG;
                CICOTACR.DATA.ACAC_BAL = DDCIQBAL.DATA.CURR_BAL;
                CICOTACR.DATA.HOLD_AMT = DDCIQBAL.DATA.HOLD_BAL;
                CICOTACR.DATA.ACAC_STSW = DDCIQBAL.DATA.CCY_STS_WORD;
                CICOTACR.DATA.LAST_BAL = DDCIQBAL.DATA.LAST_BAL;
                CICOTACR.DATA.USABLE_BAL = DDCIQBAL.DATA.AVL_BAL;
                CICOTACR.DATA.AGR_SEQ = CIRACAC.AGR_SEQ;
                CICOTACR.DATA.CCY = CIRACAC.CCY;
                CICOTACR.DATA.CR_FLG = CIRACAC.CR_FLG;
            } else {
                CEP.TRC(SCCGWA, "FRM-APP ERROR");
            }
        }
        CEP.TRC(SCCGWA, CICOTACR.DATA.AC_NO);
        CEP.TRC(SCCGWA, CICOTACR.DATA.REL_AC_NO);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, CICOTACR);
        SCCMPAG.DATA_LEN = 976;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_04_OUT_ACAC_DATA() throws IOException,SQLException,Exception {
        R000_TRC_TS();
        if (pgmRtn) return;
        WS_II = WS_II + 1;
        CEP.TRC(SCCGWA, WS_II);
        CICOTACR.DATA.AC_TYP = "A2";
        CICOTACR.DATA.AC_NO = CIRACAC.KEY.ACAC_NO;
        CICOTACR.DATA.REL_AC_NO = CIRACAC.AGR_NO;
        CICOTACR.DATA.PROD_CD = CIRACAC.PROD_CD;
        CICOTACR.DATA.CDESC = BPCPQPRD.PRDT_NAME;
        CICOTACR.DATA.FRM_APP = CIRACAC.FRM_APP;
        CICOTACR.DATA.STS = CIRACAC.ACAC_STS;
        CICOTACR.DATA.OPN_BR = CIRACAC.OPN_BR;
        CICOTACR.DATA.OPEN_DT = CIRACAC.OPEN_DT;
        if (CIRACAC.ACAC_CTL == null) CIRACAC.ACAC_CTL = "";
        JIBS_tmp_int = CIRACAC.ACAC_CTL.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) CIRACAC.ACAC_CTL += " ";
        if (CIRACAC.ACAC_CTL.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            CICOTACR.DATA.DEF_FLG = 'Y';
        } else {
            CICOTACR.DATA.DEF_FLG = 'N';
        }
        CICOTACR.DATA.AGR_SEQ = CIRACAC.AGR_SEQ;
        CICOTACR.DATA.AID = CIRACAC.AID;
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CIRACAC.CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        CICOTACR.DATA.ID_TYPE = CIRBAS.ID_TYPE;
        CICOTACR.DATA.ID_NO = CIRBAS.ID_NO;
        CICOTACR.DATA.CI_NM = CIRBAS.CI_NM;
        CICOTACR.DATA.CI_TYPE = CIRBAS.CI_TYP;
        CEP.TRC(SCCGWA, CIRBAS.CI_TYP);
        if (CIRBAS.CI_TYP == '1') {
            CICOTACR.DATA.AC_CNM = CIRBAS.CI_NM;
        } else {
            CICOTACR.DATA.AC_CNM = CIRACR.AC_CNM;
        }
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, CICOTACR);
        SCCMPAG.DATA_LEN = 976;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_TRC_TS() throws IOException,SQLException,Exception {
        JIBS_sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        JIBS_date = new Date();
        WS_TS = JIBS_sdf.format(JIBS_date);
        CEP.TRC(SCCGWA, WS_TS);
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
    }
    public void S000_CALL_TDZACE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-AC-ENQ", TDCACE, true);
    }
    public void S000_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-CCY-BAL", DDCIQBAL, true);
    }
    public void S000_CALL_DDZUINVS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-VS-INFO", DDCUINVS, true);
    }
    public void S000_CALL_DDZIMMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-NFIN-M-MST", DDCIMMST, true);
    }
    public void T000_READ_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_READ_CITACAC_DEFAULT() throws IOException,SQLException,Exception {
        CITACAC_RD = new DBParm();
        CITACAC_RD.TableName = "CITACAC";
        CITACAC_RD.eqWhere = "AGR_NO";
        CITACAC_RD.where = "SUBSTR ( ACAC_CTL , 2 , 1 ) = '1'";
        IBS.READ(SCCGWA, CIRACAC, this, CITACAC_RD);
    }
    public void T000_STARTBR_CITACR_BY_CI() throws IOException,SQLException,Exception {
        CITACR_BR.rp = new DBParm();
        CITACR_BR.rp.TableName = "CITACR";
        CITACR_BR.rp.eqWhere = "CI_NO";
        CITACR_BR.rp.where = "SHOW_FLG = 'Y' "
            + "AND STS = '0'";
        IBS.STARTBR(SCCGWA, CIRACR, this, CITACR_BR);
    }
    public void T000_READNEXT_CITACR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRACR, this, CITACR_BR);
    }
    public void T000_ENDBR_CITACR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITACR_BR);
    }
    public void T000_STARTBR_CITACRL_BY_AC() throws IOException,SQLException,Exception {
        CITACRL_BR.rp = new DBParm();
        CITACRL_BR.rp.TableName = "CITACRL";
        CITACRL_BR.rp.eqWhere = "AC_NO";
        CITACRL_BR.rp.where = "EXP_DT = 0 "
            + "OR EXP_DT > :WS_AC_DATE";
        CITACRL_BR.rp.order = "AC_REL";
        IBS.STARTBR(SCCGWA, CIRACRL, this, CITACRL_BR);
    }
    public void T000_STARTBR_CITACRL_BY_REL_AC() throws IOException,SQLException,Exception {
        CITACRL_BR.rp = new DBParm();
        CITACRL_BR.rp.TableName = "CITACRL";
        CITACRL_BR.rp.eqWhere = "REL_AC_NO";
        CITACRL_BR.rp.where = "EXP_DT = 0 "
            + "OR EXP_DT > :WS_AC_DATE";
        CITACRL_BR.rp.order = "AC_REL";
        IBS.STARTBR(SCCGWA, CIRACRL, this, CITACRL_BR);
    }
    public void T000_READNEXT_CITACRL() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRACRL, this, CITACRL_BR);
    }
    public void T000_ENDBR_CITACRL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITACRL_BR);
    }
    public void T000_STARTBR_CITACAC_BY_ACR() throws IOException,SQLException,Exception {
        CITACAC_BR.rp = new DBParm();
        CITACAC_BR.rp.TableName = "CITACAC";
        CITACAC_BR.rp.eqWhere = "AGR_NO";
        CITACAC_BR.rp.where = "SUBSTR ( ACAC_CTL , 0 , 1 ) = '0' "
            + "AND ACAC_STS = '0'";
        CITACAC_BR.rp.order = "AGR_SEQ";
        IBS.STARTBR(SCCGWA, CIRACAC, this, CITACAC_BR);
    }
    public void T000_STARTBR_CITACAC_BY_CI() throws IOException,SQLException,Exception {
        CITACAC_BR.rp = new DBParm();
        CITACAC_BR.rp.TableName = "CITACAC";
        CITACAC_BR.rp.eqWhere = "CI_NO";
        CITACAC_BR.rp.where = "SUBSTR ( ACAC_CTL , 0 , 1 ) = '0' "
            + "AND ACAC_STS = '0' "
            + "AND ( FRM_APP = :CIRACAC.FRM_APP "
            + "OR :CIRACAC.FRM_APP = ' ' ) "
            + "AND ( PROD_CD = :CIRACAC.PROD_CD "
            + "OR :CIRACAC.PROD_CD = ' ' )";
        IBS.STARTBR(SCCGWA, CIRACAC, this, CITACAC_BR);
    }
    public void T000_READNEXT_CITACAC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRACAC, this, CITACAC_BR);
    }
    public void T000_ENDBR_CITACAC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITACAC_BR);
    }
    public void T000_READ_CITACR() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        IBS.READ(SCCGWA, CIRACR, CITACR_RD);
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
