package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.DD.*;
import com.hisun.TD.*;
import com.hisun.DC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZSVCH {
    int BAS_CI_NM_LEN;
    int JIBS_tmp_int;
    DBParm CITBAS_RD;
    DBParm CITACR_RD;
    brParm CITACR_BR = new brParm();
    brParm CITACAC_BR = new brParm();
    DBParm CITACAC_RD;
    DBParm CITACRL_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 10;
    int K_MAX_COL = 0;
    int K_COL_CNT = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CIRBAS CIRBAS = new CIRBAS();
    CIRACR CIRACR = new CIRACR();
    CIRACAC CIRACAC = new CIRACAC();
    CIRACRL CIRACRL = new CIRACRL();
    CICOVCH CICOVCH = new CICOVCH();
    DDCSCINM DDCSCINM = new DDCSCINM();
    TDCACE TDCACE = new TDCACE();
    TDCMACE TDCMACE = new TDCMACE();
    DCCUCINF DCCUCINF = new DCCUCINF();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSVCH CICSVCH;
    public void MP(SCCGWA SCCGWA, CICSVCH CICSVCH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSVCH = CICSVCH;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSVCH return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSVCH.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (CICSVCH.DATA.AGR_NO.trim().length() == 0) {
            B020_INQ_CUST_VCH();
            if (pgmRtn) return;
        } else {
            B030_INQ_AC_VCH();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_INQ_CUST_VCH() throws IOException,SQLException,Exception {
        if (CICSVCH.DATA.CI_NO.trim().length() == 0) {
            IBS.init(SCCGWA, CIRBAS);
            CIRBAS.ID_TYPE = CICSVCH.DATA.ID_TYPE;
            CIRBAS.ID_NO = CICSVCH.DATA.ID_NO;
            CIRBAS.CI_NM = CICSVCH.DATA.CI_NM;
            BAS_CI_NM_LEN = CIRBAS.CI_NM.length();
            T000_READ_CITBAS_GET_CINO();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, CIRBAS);
            CIRBAS.KEY.CI_NO = CICSVCH.DATA.CI_NO;
            T000_READ_CITBAS();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "CI INF NOT EXIST");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND, CICSVCH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRACR);
        CIRACR.CI_NO = CIRBAS.KEY.CI_NO;
        T000_STARTBR_CITACR_BY_CI();
        if (pgmRtn) return;
        T000_READNEXT_CITACR();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "ACR INF NOT EXIST");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND, CICSVCH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R000_01_OUT_TITLE();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && SCCMPAG.FUNC != 'E') {
            if ((CICSVCH.DATA.ENTY_TYP == ' ' 
                || CICSVCH.DATA.ENTY_TYP == CIRACR.ENTY_TYP) 
                && (CIRACR.ENTY_TYP != '4') 
                && (CICSVCH.DATA.FRM_APP.trim().length() == 0 
                || CICSVCH.DATA.FRM_APP.equalsIgnoreCase(CIRACR.FRM_APP)) 
                && CIRACR.STS == '0') {
                if (CIRACR.FRM_APP.equalsIgnoreCase("DD")) {
                    R000_INQ_DD_VCH();
                    if (pgmRtn) return;
                } else if (CIRACR.FRM_APP.equalsIgnoreCase("TD")) {
                    IBS.init(SCCGWA, CIRACAC);
                    CIRACAC.AGR_NO = CIRACR.KEY.AGR_NO;
                    T000_READ_CITACAC_FIRST();
                    if (pgmRtn) return;
                    if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                        R000_INQ_TD_VCH();
                        if (pgmRtn) return;
                    } else {
                        R000_INQ_TD_VCH_2();
                        if (pgmRtn) return;
                    }
                } else if (CIRACR.FRM_APP.equalsIgnoreCase("DC")) {
                    R000_INQ_DC_VCH();
                    if (pgmRtn) return;
                } else {
                }
            }
            T000_READNEXT_CITACR();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITACR();
        if (pgmRtn) return;
    }
    public void B030_INQ_AC_VCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACR);
        CIRACR.KEY.AGR_NO = CICSVCH.DATA.AGR_NO;
        T000_READ_CITACR();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "AC INF NOT EXIST");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND, CICSVCH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (CIRACR.STS != '0') {
            CEP.TRC(SCCGWA, "ACR IS CLOSE");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_IS_CLOSED, CICSVCH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CIRACR.CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        R000_01_OUT_TITLE();
        if (pgmRtn) return;
        if (CIRACR.ENTY_TYP != '4') {
            if (CIRACR.FRM_APP.equalsIgnoreCase("DD")) {
                R000_INQ_DD_VCH();
                if (pgmRtn) return;
            } else if (CIRACR.FRM_APP.equalsIgnoreCase("TD")) {
                IBS.init(SCCGWA, CIRACAC);
                CIRACAC.AGR_NO = CIRACR.KEY.AGR_NO;
                T000_READ_CITACAC_FIRST();
                if (pgmRtn) return;
                if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                    R000_INQ_TD_VCH();
                    if (pgmRtn) return;
                } else {
                    R000_INQ_TD_VCH_2();
                    if (pgmRtn) return;
                }
            } else if (CIRACR.FRM_APP.equalsIgnoreCase("DC")) {
                R000_INQ_DC_VCH();
                if (pgmRtn) return;
            } else {
            }
        }
    }
    public void R000_INQ_DD_VCH() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "DD");
        CEP.TRC(SCCGWA, CIRACR.KEY.AGR_NO);
        IBS.init(SCCGWA, DDCSCINM);
        DDCSCINM.INPUT_DATA.AC_NO = CIRACR.KEY.AGR_NO;
        DDCSCINM.INPUT_DATA.FUNC = '2';
        S000_CALL_DDZSCINM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCSCINM.OUTPUT_DATA.BV_TYP);
        if (DDCSCINM.OUTPUT_DATA.BV_TYP == 'J' 
            || DDCSCINM.OUTPUT_DATA.BV_TYP == '9' 
            || DDCSCINM.OUTPUT_DATA.BV_TYP == 'X') {
            R000_02_OUT_DD_DATA();
            if (pgmRtn) return;
        }
    }
    public void R000_INQ_TD_VCH() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "TD");
        CEP.TRC(SCCGWA, CIRACR.KEY.AGR_NO);
        IBS.init(SCCGWA, TDCACE);
        TDCACE.PAGE_INF.AC_NO = CIRACR.KEY.AGR_NO;
        S000_CALL_TDZACE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDCACE.PAGE_INF.I_BV_NO);
        if (TDCACE.PAGE_INF.I_BV_NO.trim().length() > 0) {
            R000_03_OUT_TD_DATA_ACR();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, CIRACAC);
            CIRACAC.AGR_NO = CIRACR.KEY.AGR_NO;
            T000_STARTBR_CITACAC_BY_AGR();
            if (pgmRtn) return;
            T000_READNEXT_CITACAC();
            if (pgmRtn) return;
            while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
                && SCCMPAG.FUNC != 'E') {
                IBS.init(SCCGWA, TDCACE);
                TDCACE.PAGE_INF.AC_NO = CIRACR.KEY.AGR_NO;
                TDCACE.PAGE_INF.I_AC_SEQ = CIRACAC.AGR_SEQ;
                TDCACE.PAGE_INF.I_BV_NO = CIRACAC.BV_NO;
                CEP.TRC(SCCGWA, TDCACE.PAGE_INF.AC_NO);
                CEP.TRC(SCCGWA, TDCACE.PAGE_INF.I_AC_SEQ);
                CEP.TRC(SCCGWA, TDCACE.PAGE_INF.I_BV_NO);
                S000_CALL_TDZACE();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, TDCACE.DATA[1-1].BV_NO);
                if (TDCACE.DATA[1-1].BV_NO.trim().length() > 0) {
                    R000_03_OUT_TD_DATA_ACAC();
                    if (pgmRtn) return;
                }
                T000_READNEXT_CITACAC();
                if (pgmRtn) return;
            }
            T000_ENDBR_CITACAC();
            if (pgmRtn) return;
        }
    }
    public void R000_INQ_TD_VCH_2() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "TD-2");
        CEP.TRC(SCCGWA, CIRACR.KEY.AGR_NO);
        IBS.init(SCCGWA, TDCMACE);
        TDCMACE.INPUT_DATA.AC_NO = CIRACR.KEY.AGR_NO;
        S000_CALL_TDZMACE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDCMACE.OUTPUT_DATA.OIMAE_BV_NO);
        if (TDCMACE.OUTPUT_DATA.OIMAE_BV_NO.trim().length() > 0) {
            R000_03_OUT_TD_DATA_ACR_2();
            if (pgmRtn) return;
        }
    }
    public void R000_INQ_DC_VCH() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "DC");
        CEP.TRC(SCCGWA, CIRACR.KEY.AGR_NO);
        IBS.init(SCCGWA, DCCUCINF);
        DCCUCINF.CARD_NO = CIRACR.KEY.AGR_NO;
        S000_CALL_DCZUCINF();
        if (pgmRtn) return;
        R000_04_OUT_DC_DATA();
        if (pgmRtn) return;
    }
    public void R000_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_02_OUT_DD_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "OUT DD VCH");
        IBS.init(SCCGWA, CICOVCH);
        CICOVCH.DATA.AC_TYP = "A1";
        CICOVCH.DATA.ENTY_TYP = CIRACR.ENTY_TYP;
        CICOVCH.DATA.AGR_NO = CIRACR.KEY.AGR_NO;
        CICOVCH.DATA.OPEN_DT = CIRACR.OPEN_DT;
        CICOVCH.DATA.OPEN_BR = CIRACR.OPN_BR;
        CICOVCH.DATA.FRM_APP = CIRACR.FRM_APP;
        if (CIRBAS.CI_TYP == '1') {
            if (CIRBAS.CI_NM == null) CIRBAS.CI_NM = "";
            JIBS_tmp_int = CIRBAS.CI_NM.length();
            for (int i=0;i<252-JIBS_tmp_int;i++) CIRBAS.CI_NM += " ";
            CICOVCH.DATA.AC_CNM = CIRBAS.CI_NM.substring(0, BAS_CI_NM_LEN);
        } else {
            CICOVCH.DATA.AC_CNM = CIRACR.AC_CNM;
            CICOVCH.DATA.AC_ENM = CIRACR.AC_ENM;
        }
        CICOVCH.DATA.VCH_TYPE = DDCSCINM.OUTPUT_DATA.BV_TYP;
        if (DDCSCINM.OUTPUT_DATA.VCH_STS == 'N') {
            CICOVCH.DATA.VCH_STS = '1';
        } else if (DDCSCINM.OUTPUT_DATA.VCH_STS == 'U') {
            CICOVCH.DATA.VCH_STS = '2';
        } else if (DDCSCINM.OUTPUT_DATA.VCH_STS == 'W') {
            CICOVCH.DATA.VCH_STS = '3';
        } else {
        }
            if (DDCSCINM.OUTPUT_DATA.AC_STS_WORD == null) DDCSCINM.OUTPUT_DATA.AC_STS_WORD = "";
            JIBS_tmp_int = DDCSCINM.OUTPUT_DATA.AC_STS_WORD.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DDCSCINM.OUTPUT_DATA.AC_STS_WORD += " ";
        if (DDCSCINM.OUTPUT_DATA.AC_STS_WORD.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1")) {
            CICOVCH.DATA.PSW_STS = '2';
            if (DDCSCINM.OUTPUT_DATA.AC_STS_WORD == null) DDCSCINM.OUTPUT_DATA.AC_STS_WORD = "";
            JIBS_tmp_int = DDCSCINM.OUTPUT_DATA.AC_STS_WORD.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DDCSCINM.OUTPUT_DATA.AC_STS_WORD += " ";
        } else if (DDCSCINM.OUTPUT_DATA.AC_STS_WORD.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("1")) {
            CICOVCH.DATA.PSW_STS = '3';
        } else {
            CICOVCH.DATA.PSW_STS = '1';
        }
        CICOVCH.DATA.VCH_BVID = DDCSCINM.OUTPUT_DATA.BV_CD;
        CICOVCH.DATA.VCH_BVNO = DDCSCINM.OUTPUT_DATA.PSBK_NO;
        CICOVCH.DATA.VCH_SEQ = DDCSCINM.OUTPUT_DATA.PSBK_SEQ;
        CICOVCH.DATA.PROD_CD = CIRACR.PROD_CD;
        CEP.TRC(SCCGWA, CICOVCH.DATA.AC_TYP);
        CEP.TRC(SCCGWA, CICOVCH.DATA.ENTY_TYP);
        CEP.TRC(SCCGWA, CICOVCH.DATA.AGR_NO);
        CEP.TRC(SCCGWA, CICOVCH.DATA.OPEN_DT);
        CEP.TRC(SCCGWA, CICOVCH.DATA.OPEN_BR);
        CEP.TRC(SCCGWA, CICOVCH.DATA.AC_CNM);
        CEP.TRC(SCCGWA, CICOVCH.DATA.AC_ENM);
        CEP.TRC(SCCGWA, CICOVCH.DATA.VCH_TYPE);
        CEP.TRC(SCCGWA, CICOVCH.DATA.VCH_STS);
        CEP.TRC(SCCGWA, CICOVCH.DATA.PSW_STS);
        CEP.TRC(SCCGWA, CICOVCH.DATA.VCH_BVID);
        CEP.TRC(SCCGWA, CICOVCH.DATA.VCH_BVNO);
        CEP.TRC(SCCGWA, CICOVCH.DATA.VCH_SEQ);
        CEP.TRC(SCCGWA, CICOVCH.DATA.PROD_CD);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, CICOVCH);
        SCCMPAG.DATA_LEN = 644;
        CEP.TRC(SCCGWA, SCCMPAG.DATA_LEN);
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_03_OUT_TD_DATA_ACR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "OUT TD ACR VCH");
        IBS.init(SCCGWA, CICOVCH);
        CICOVCH.DATA.AC_TYP = "A1";
        CICOVCH.DATA.ENTY_TYP = CIRACR.ENTY_TYP;
        CICOVCH.DATA.AGR_NO = CIRACR.KEY.AGR_NO;
        CICOVCH.DATA.OPEN_DT = CIRACR.OPEN_DT;
        CICOVCH.DATA.OPEN_BR = CIRACR.OPN_BR;
        CICOVCH.DATA.FRM_APP = CIRACR.FRM_APP;
        if (CIRBAS.CI_TYP == '1') {
            if (CIRBAS.CI_NM == null) CIRBAS.CI_NM = "";
            JIBS_tmp_int = CIRBAS.CI_NM.length();
            for (int i=0;i<252-JIBS_tmp_int;i++) CIRBAS.CI_NM += " ";
            CICOVCH.DATA.AC_CNM = CIRBAS.CI_NM.substring(0, BAS_CI_NM_LEN);
        } else {
            CICOVCH.DATA.AC_CNM = CIRACR.AC_CNM;
            CICOVCH.DATA.AC_ENM = CIRACR.AC_ENM;
        }
        CEP.TRC(SCCGWA, TDCACE.DATA[1-1].BV_TYP);
        CICOVCH.DATA.VCH_TYPE = TDCACE.DATA[1-1].BV_TYP;
        CEP.TRC(SCCGWA, TDCACE.DATA[1-1].DAC_STSW);
            if (TDCACE.DATA[1-1].DAC_STSW == null) TDCACE.DATA[1-1].DAC_STSW = "";
            JIBS_tmp_int = TDCACE.DATA[1-1].DAC_STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.DATA[1-1].DAC_STSW += " ";
        if (TDCACE.DATA[1-1].DAC_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            CICOVCH.DATA.VCH_STS = '2';
            if (TDCACE.DATA[1-1].DAC_STSW == null) TDCACE.DATA[1-1].DAC_STSW = "";
            JIBS_tmp_int = TDCACE.DATA[1-1].DAC_STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.DATA[1-1].DAC_STSW += " ";
        } else if (TDCACE.DATA[1-1].DAC_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            CICOVCH.DATA.VCH_STS = '3';
            if (TDCACE.DATA[1-1].DAC_STSW == null) TDCACE.DATA[1-1].DAC_STSW = "";
            JIBS_tmp_int = TDCACE.DATA[1-1].DAC_STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.DATA[1-1].DAC_STSW += " ";
        } else if (TDCACE.DATA[1-1].DAC_STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1")) {
            CICOVCH.DATA.VCH_STS = '5';
        } else {
            CICOVCH.DATA.VCH_STS = '1';
        }
        CEP.TRC(SCCGWA, CICOVCH.DATA.VCH_STS);
        CEP.TRC(SCCGWA, TDCACE.PAGE_INF.STSW);
            if (TDCACE.PAGE_INF.STSW == null) TDCACE.PAGE_INF.STSW = "";
            JIBS_tmp_int = TDCACE.PAGE_INF.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.PAGE_INF.STSW += " ";
        if (TDCACE.PAGE_INF.STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            CICOVCH.DATA.PSW_STS = '3';
            if (TDCACE.PAGE_INF.STSW == null) TDCACE.PAGE_INF.STSW = "";
            JIBS_tmp_int = TDCACE.PAGE_INF.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.PAGE_INF.STSW += " ";
        } else if (TDCACE.PAGE_INF.STSW.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("1")) {
            CICOVCH.DATA.PSW_STS = '2';
        } else {
            CICOVCH.DATA.PSW_STS = '1';
        }
        CICOVCH.DATA.VCH_BVID = TDCACE.DATA[1-1].BV_CD;
        CICOVCH.DATA.VCH_BVNO = TDCACE.PAGE_INF.I_BV_NO;
        CICOVCH.DATA.PROD_CD = CIRACR.PROD_CD;
        CICOVCH.DATA.TERM = TDCACE.DATA[1-1].TERM;
        CICOVCH.DATA.EXP_DT = TDCACE.DATA[1-1].DDT;
        CEP.TRC(SCCGWA, CICOVCH.DATA.AC_TYP);
        CEP.TRC(SCCGWA, CICOVCH.DATA.ENTY_TYP);
        CEP.TRC(SCCGWA, CICOVCH.DATA.AGR_NO);
        CEP.TRC(SCCGWA, CICOVCH.DATA.OPEN_DT);
        CEP.TRC(SCCGWA, CICOVCH.DATA.OPEN_BR);
        CEP.TRC(SCCGWA, CICOVCH.DATA.AC_CNM);
        CEP.TRC(SCCGWA, CICOVCH.DATA.AC_ENM);
        CEP.TRC(SCCGWA, CICOVCH.DATA.VCH_TYPE);
        CEP.TRC(SCCGWA, CICOVCH.DATA.VCH_STS);
        CEP.TRC(SCCGWA, CICOVCH.DATA.PSW_STS);
        CEP.TRC(SCCGWA, CICOVCH.DATA.VCH_BVID);
        CEP.TRC(SCCGWA, CICOVCH.DATA.VCH_BVNO);
        CEP.TRC(SCCGWA, CICOVCH.DATA.PROD_CD);
        CEP.TRC(SCCGWA, CICOVCH.DATA.TERM);
        CEP.TRC(SCCGWA, CICOVCH.DATA.EXP_DT);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, CICOVCH);
        SCCMPAG.DATA_LEN = 644;
        CEP.TRC(SCCGWA, SCCMPAG.DATA_LEN);
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_03_OUT_TD_DATA_ACAC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "OUT TD ACAC VCH");
        IBS.init(SCCGWA, CICOVCH);
        CICOVCH.DATA.AC_TYP = "A2";
        CICOVCH.DATA.ENTY_TYP = CIRACR.ENTY_TYP;
        CICOVCH.DATA.AGR_NO = CIRACR.KEY.AGR_NO;
        CICOVCH.DATA.ACAC_NO = CIRACAC.KEY.ACAC_NO;
        CICOVCH.DATA.OPEN_DT = CIRACAC.OPEN_DT;
        CICOVCH.DATA.OPEN_BR = CIRACAC.OPN_BR;
        CICOVCH.DATA.FRM_APP = CIRACAC.FRM_APP;
        if (CIRBAS.CI_TYP == '1') {
            if (CIRBAS.CI_NM == null) CIRBAS.CI_NM = "";
            JIBS_tmp_int = CIRBAS.CI_NM.length();
            for (int i=0;i<252-JIBS_tmp_int;i++) CIRBAS.CI_NM += " ";
            CICOVCH.DATA.AC_CNM = CIRBAS.CI_NM.substring(0, BAS_CI_NM_LEN);
        } else {
            CICOVCH.DATA.AC_CNM = CIRACR.AC_CNM;
            CICOVCH.DATA.AC_ENM = CIRACR.AC_ENM;
        }
        CICOVCH.DATA.VCH_TYPE = TDCACE.DATA[1-1].BV_TYP;
        CEP.TRC(SCCGWA, TDCACE.DATA[1-1].DAC_STSW);
            if (TDCACE.DATA[1-1].DAC_STSW == null) TDCACE.DATA[1-1].DAC_STSW = "";
            JIBS_tmp_int = TDCACE.DATA[1-1].DAC_STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.DATA[1-1].DAC_STSW += " ";
        if (TDCACE.DATA[1-1].DAC_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            CICOVCH.DATA.VCH_STS = '2';
            if (TDCACE.DATA[1-1].DAC_STSW == null) TDCACE.DATA[1-1].DAC_STSW = "";
            JIBS_tmp_int = TDCACE.DATA[1-1].DAC_STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.DATA[1-1].DAC_STSW += " ";
        } else if (TDCACE.DATA[1-1].DAC_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            CICOVCH.DATA.VCH_STS = '3';
            if (TDCACE.DATA[1-1].DAC_STSW == null) TDCACE.DATA[1-1].DAC_STSW = "";
            JIBS_tmp_int = TDCACE.DATA[1-1].DAC_STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.DATA[1-1].DAC_STSW += " ";
        } else if (TDCACE.DATA[1-1].DAC_STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1")) {
            CICOVCH.DATA.VCH_STS = '5';
        } else {
            CICOVCH.DATA.VCH_STS = '1';
        }
        CEP.TRC(SCCGWA, CICOVCH.DATA.VCH_STS);
            if (TDCACE.PAGE_INF.STSW == null) TDCACE.PAGE_INF.STSW = "";
            JIBS_tmp_int = TDCACE.PAGE_INF.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.PAGE_INF.STSW += " ";
        if (TDCACE.PAGE_INF.STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            CICOVCH.DATA.PSW_STS = '3';
            if (TDCACE.PAGE_INF.STSW == null) TDCACE.PAGE_INF.STSW = "";
            JIBS_tmp_int = TDCACE.PAGE_INF.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.PAGE_INF.STSW += " ";
        } else if (TDCACE.PAGE_INF.STSW.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("1")) {
            CICOVCH.DATA.PSW_STS = '2';
        } else {
            CICOVCH.DATA.PSW_STS = '1';
        }
        CICOVCH.DATA.VCH_BVID = TDCACE.DATA[1-1].BV_CD;
        CICOVCH.DATA.VCH_BVNO = TDCACE.DATA[1-1].BV_NO;
        CICOVCH.DATA.TERM = TDCACE.DATA[1-1].TERM;
        CICOVCH.DATA.PROD_CD = CIRACAC.PROD_CD;
        CICOVCH.DATA.EXP_DT = TDCACE.DATA[1-1].DDT;
        CEP.TRC(SCCGWA, CICOVCH.DATA.AC_TYP);
        CEP.TRC(SCCGWA, CICOVCH.DATA.ENTY_TYP);
        CEP.TRC(SCCGWA, CICOVCH.DATA.AGR_NO);
        CEP.TRC(SCCGWA, CICOVCH.DATA.ACAC_NO);
        CEP.TRC(SCCGWA, CICOVCH.DATA.OPEN_DT);
        CEP.TRC(SCCGWA, CICOVCH.DATA.OPEN_BR);
        CEP.TRC(SCCGWA, CICOVCH.DATA.AC_CNM);
        CEP.TRC(SCCGWA, CICOVCH.DATA.AC_ENM);
        CEP.TRC(SCCGWA, CICOVCH.DATA.VCH_TYPE);
        CEP.TRC(SCCGWA, CICOVCH.DATA.VCH_STS);
        CEP.TRC(SCCGWA, CICOVCH.DATA.PSW_STS);
        CEP.TRC(SCCGWA, CICOVCH.DATA.VCH_BVID);
        CEP.TRC(SCCGWA, CICOVCH.DATA.VCH_BVNO);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, CICOVCH);
        SCCMPAG.DATA_LEN = 644;
        CEP.TRC(SCCGWA, SCCMPAG.DATA_LEN);
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_03_OUT_TD_DATA_ACR_2() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "OUT TD ACR VCH");
        IBS.init(SCCGWA, CICOVCH);
        CICOVCH.DATA.AC_TYP = "A1";
        CICOVCH.DATA.ENTY_TYP = CIRACR.ENTY_TYP;
        CICOVCH.DATA.AGR_NO = CIRACR.KEY.AGR_NO;
        CICOVCH.DATA.OPEN_DT = CIRACR.OPEN_DT;
        CICOVCH.DATA.OPEN_BR = CIRACR.OPN_BR;
        CICOVCH.DATA.FRM_APP = CIRACR.FRM_APP;
        if (CIRBAS.CI_TYP == '1') {
            if (CIRBAS.CI_NM == null) CIRBAS.CI_NM = "";
            JIBS_tmp_int = CIRBAS.CI_NM.length();
            for (int i=0;i<252-JIBS_tmp_int;i++) CIRBAS.CI_NM += " ";
            CICOVCH.DATA.AC_CNM = CIRBAS.CI_NM.substring(0, BAS_CI_NM_LEN);
        } else {
            CICOVCH.DATA.AC_CNM = CIRACR.AC_CNM;
            CICOVCH.DATA.AC_ENM = CIRACR.AC_ENM;
        }
        CEP.TRC(SCCGWA, TDCMACE.O_BV_TYP);
        CICOVCH.DATA.VCH_TYPE = TDCMACE.O_BV_TYP;
        CEP.TRC(SCCGWA, TDCMACE.OUTPUT_DATA.OIMAE_BV_STSW);
            if (TDCMACE.OUTPUT_DATA.OIMAE_BV_STSW == null) TDCMACE.OUTPUT_DATA.OIMAE_BV_STSW = "";
            JIBS_tmp_int = TDCMACE.OUTPUT_DATA.OIMAE_BV_STSW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) TDCMACE.OUTPUT_DATA.OIMAE_BV_STSW += " ";
        if (TDCMACE.OUTPUT_DATA.OIMAE_BV_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            CICOVCH.DATA.VCH_STS = '2';
            if (TDCMACE.OUTPUT_DATA.OIMAE_BV_STSW == null) TDCMACE.OUTPUT_DATA.OIMAE_BV_STSW = "";
            JIBS_tmp_int = TDCMACE.OUTPUT_DATA.OIMAE_BV_STSW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) TDCMACE.OUTPUT_DATA.OIMAE_BV_STSW += " ";
        } else if (TDCMACE.OUTPUT_DATA.OIMAE_BV_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            CICOVCH.DATA.VCH_STS = '3';
            if (TDCMACE.OUTPUT_DATA.OIMAE_BV_STSW == null) TDCMACE.OUTPUT_DATA.OIMAE_BV_STSW = "";
            JIBS_tmp_int = TDCMACE.OUTPUT_DATA.OIMAE_BV_STSW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) TDCMACE.OUTPUT_DATA.OIMAE_BV_STSW += " ";
        } else if (TDCMACE.OUTPUT_DATA.OIMAE_BV_STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1")) {
            CICOVCH.DATA.VCH_STS = '5';
        } else {
            CICOVCH.DATA.VCH_STS = '1';
        }
        CEP.TRC(SCCGWA, CICOVCH.DATA.VCH_STS);
        CEP.TRC(SCCGWA, TDCMACE.OUTPUT_DATA.OIMAE_AC_STSW);
            if (TDCMACE.OUTPUT_DATA.OIMAE_AC_STSW == null) TDCMACE.OUTPUT_DATA.OIMAE_AC_STSW = "";
            JIBS_tmp_int = TDCMACE.OUTPUT_DATA.OIMAE_AC_STSW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) TDCMACE.OUTPUT_DATA.OIMAE_AC_STSW += " ";
        if (TDCMACE.OUTPUT_DATA.OIMAE_AC_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            CICOVCH.DATA.PSW_STS = '3';
            if (TDCMACE.OUTPUT_DATA.OIMAE_AC_STSW == null) TDCMACE.OUTPUT_DATA.OIMAE_AC_STSW = "";
            JIBS_tmp_int = TDCMACE.OUTPUT_DATA.OIMAE_AC_STSW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) TDCMACE.OUTPUT_DATA.OIMAE_AC_STSW += " ";
        } else if (TDCMACE.OUTPUT_DATA.OIMAE_AC_STSW.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("1")) {
            CICOVCH.DATA.PSW_STS = '2';
        } else {
            CICOVCH.DATA.PSW_STS = '1';
        }
        CICOVCH.DATA.VCH_BVID = TDCMACE.OUTPUT_DATA.OIMAE_BV_CD;
        CICOVCH.DATA.VCH_BVNO = TDCMACE.OUTPUT_DATA.OIMAE_BV_NO;
        CICOVCH.DATA.PROD_CD = CIRACR.PROD_CD;
        CEP.TRC(SCCGWA, CICOVCH.DATA.AC_TYP);
        CEP.TRC(SCCGWA, CICOVCH.DATA.ENTY_TYP);
        CEP.TRC(SCCGWA, CICOVCH.DATA.AGR_NO);
        CEP.TRC(SCCGWA, CICOVCH.DATA.OPEN_DT);
        CEP.TRC(SCCGWA, CICOVCH.DATA.OPEN_BR);
        CEP.TRC(SCCGWA, CICOVCH.DATA.AC_CNM);
        CEP.TRC(SCCGWA, CICOVCH.DATA.AC_ENM);
        CEP.TRC(SCCGWA, CICOVCH.DATA.VCH_TYPE);
        CEP.TRC(SCCGWA, CICOVCH.DATA.VCH_STS);
        CEP.TRC(SCCGWA, CICOVCH.DATA.PSW_STS);
        CEP.TRC(SCCGWA, CICOVCH.DATA.VCH_BVID);
        CEP.TRC(SCCGWA, CICOVCH.DATA.VCH_BVNO);
        CEP.TRC(SCCGWA, CICOVCH.DATA.PROD_CD);
        CEP.TRC(SCCGWA, CICOVCH.DATA.TERM);
        CEP.TRC(SCCGWA, CICOVCH.DATA.EXP_DT);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, CICOVCH);
        SCCMPAG.DATA_LEN = 644;
        CEP.TRC(SCCGWA, SCCMPAG.DATA_LEN);
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_04_OUT_DC_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "OUTPUT DC VCH");
        IBS.init(SCCGWA, CICOVCH);
        CICOVCH.DATA.AC_TYP = "A1";
        CICOVCH.DATA.ENTY_TYP = CIRACR.ENTY_TYP;
        CICOVCH.DATA.AGR_NO = CIRACR.KEY.AGR_NO;
        CICOVCH.DATA.OPEN_DT = CIRACR.OPEN_DT;
        CICOVCH.DATA.OPEN_BR = CIRACR.OPN_BR;
        CICOVCH.DATA.FRM_APP = CIRACR.FRM_APP;
        CEP.TRC(SCCGWA, CICOVCH.DATA.FRM_APP);
        if (CIRBAS.CI_TYP == '1') {
            if (CIRBAS.CI_NM == null) CIRBAS.CI_NM = "";
            JIBS_tmp_int = CIRBAS.CI_NM.length();
            for (int i=0;i<252-JIBS_tmp_int;i++) CIRBAS.CI_NM += " ";
            CICOVCH.DATA.AC_CNM = CIRBAS.CI_NM.substring(0, BAS_CI_NM_LEN);
        } else {
            IBS.init(SCCGWA, CIRACRL);
            CIRACRL.KEY.AC_NO = CIRACR.KEY.AGR_NO;
            CIRACRL.KEY.AC_REL = "04";
            T000_READ_CITACRL_BY_ACNO();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CIRACRL.KEY.REL_AC_NO);
            IBS.init(SCCGWA, CIRACR);
            CIRACR.KEY.AGR_NO = CIRACRL.KEY.REL_AC_NO;
            T000_READ_CITACR();
            if (pgmRtn) return;
            CICOVCH.DATA.AC_CNM = CIRACR.AC_CNM;
            CICOVCH.DATA.AC_ENM = CIRACR.AC_ENM;
        }
        CEP.TRC(SCCGWA, DCCUCINF.CARD_MEDI);
        if (DCCUCINF.CARD_MEDI == '1') {
            CICOVCH.DATA.VCH_TYPE = 'A';
        } else if (DCCUCINF.CARD_MEDI == '2') {
            CICOVCH.DATA.VCH_TYPE = 'B';
        } else if (DCCUCINF.CARD_MEDI == '3') {
            CICOVCH.DATA.VCH_TYPE = 'C';
        } else if (DCCUCINF.CARD_MEDI == '4') {
            CICOVCH.DATA.VCH_TYPE = 'D';
        } else {
        }
            if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
            JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(0, 1).equalsIgnoreCase("1")) {
            CICOVCH.DATA.VCH_STS = '2';
            if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
            JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        } else if (DCCUCINF.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            CICOVCH.DATA.VCH_STS = '3';
            if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
            JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        } else if (DCCUCINF.CARD_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
            CICOVCH.DATA.VCH_STS = '6';
            if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
            JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        } else if (DCCUCINF.CARD_STSW.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
            CICOVCH.DATA.VCH_STS = '7';
            if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
            JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        } else if (DCCUCINF.CARD_STSW.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1")) {
            CICOVCH.DATA.VCH_STS = '8';
            if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
            JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        } else if (DCCUCINF.CARD_STSW.substring(14 - 1, 14 + 1 - 1).equalsIgnoreCase("1")) {
            CICOVCH.DATA.VCH_STS = '9';
        } else {
            CICOVCH.DATA.VCH_STS = '1';
        }
            if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
            JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
            CICOVCH.DATA.PSW_STS = '3';
        } else {
            CICOVCH.DATA.PSW_STS = '1';
        }
        CICOVCH.DATA.VCH_BVID = DCCUCINF.BV_CD_NO;
        CICOVCH.DATA.PROD_CD = CIRACR.PROD_CD;
        CEP.TRC(SCCGWA, CICOVCH.DATA.AC_TYP);
        CEP.TRC(SCCGWA, CICOVCH.DATA.ENTY_TYP);
        CEP.TRC(SCCGWA, CICOVCH.DATA.AGR_NO);
        CEP.TRC(SCCGWA, CICOVCH.DATA.OPEN_DT);
        CEP.TRC(SCCGWA, CICOVCH.DATA.OPEN_BR);
        CEP.TRC(SCCGWA, CICOVCH.DATA.AC_CNM);
        CEP.TRC(SCCGWA, CICOVCH.DATA.AC_ENM);
        CEP.TRC(SCCGWA, CICOVCH.DATA.VCH_TYPE);
        CEP.TRC(SCCGWA, CICOVCH.DATA.VCH_STS);
        CEP.TRC(SCCGWA, CICOVCH.DATA.PSW_STS);
        CEP.TRC(SCCGWA, CICOVCH.DATA.VCH_BVID);
        CEP.TRC(SCCGWA, CICOVCH.DATA.VCH_BVNO);
        CEP.TRC(SCCGWA, CICOVCH.DATA.PROD_CD);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, CICOVCH);
        SCCMPAG.DATA_LEN = 644;
        CEP.TRC(SCCGWA, SCCMPAG.DATA_LEN);
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_TDZACE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-AC-ENQ", TDCACE, true);
    }
    public void S000_CALL_TDZMACE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-MAC-ENQ", TDCMACE, true);
    }
    public void S000_CALL_DDZSCINM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-CI-NAME", DDCSCINM, true);
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF, true);
        if (DCCUCINF.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DCCUCINF.RC);
        }
    }
    public void T000_READ_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_READ_CITBAS_GET_CINO() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        CITBAS_RD.eqWhere = "ID_TYPE,ID_NO,CI_NM";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_READ_CITACR() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        IBS.READ(SCCGWA, CIRACR, CITACR_RD);
    }
    public void T000_STARTBR_CITACR_BY_CI() throws IOException,SQLException,Exception {
        CITACR_BR.rp = new DBParm();
        CITACR_BR.rp.TableName = "CITACR";
        CITACR_BR.rp.eqWhere = "CI_NO";
        IBS.STARTBR(SCCGWA, CIRACR, CITACR_BR);
    }
    public void T000_READNEXT_CITACR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRACR, this, CITACR_BR);
    }
    public void T000_ENDBR_CITACR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITACR_BR);
    }
    public void T000_STARTBR_CITACAC_BY_AGR() throws IOException,SQLException,Exception {
        CITACAC_BR.rp = new DBParm();
        CITACAC_BR.rp.TableName = "CITACAC";
        CITACAC_BR.rp.eqWhere = "AGR_NO";
        CITACAC_BR.rp.where = "ACAC_STS = '0'";
        IBS.STARTBR(SCCGWA, CIRACAC, this, CITACAC_BR);
    }
    public void T000_READNEXT_CITACAC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRACAC, this, CITACAC_BR);
    }
    public void T000_ENDBR_CITACAC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITACAC_BR);
    }
    public void T000_READ_CITACAC_FIRST() throws IOException,SQLException,Exception {
        CITACAC_RD = new DBParm();
        CITACAC_RD.TableName = "CITACAC";
        CITACAC_RD.eqWhere = "AGR_NO";
        CITACAC_RD.where = "ACAC_STS = '0'";
        CITACAC_RD.fst = true;
        IBS.READ(SCCGWA, CIRACAC, this, CITACAC_RD);
    }
    public void T000_READ_CITACRL_BY_ACNO() throws IOException,SQLException,Exception {
        CITACRL_RD = new DBParm();
        CITACRL_RD.TableName = "CITACRL";
        CITACRL_RD.eqWhere = "AC_NO , AC_REL";
        CITACRL_RD.where = "( EXP_DT = 0 "
            + "OR EXP_DT > :SCCGWA.COMM_AREA.AC_DATE ) "
            + "AND SUBSTR ( REL_CTL , 1 , 1 ) = '1'";
        IBS.READ(SCCGWA, CIRACRL, this, CITACRL_RD);
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
