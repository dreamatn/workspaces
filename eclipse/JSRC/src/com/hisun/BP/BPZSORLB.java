package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSORLB {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_BRW_ORG_RGL = "BP-R-BRW-ORG-RGL    ";
    String CPN_R_BRW_ORG_RGI = "BP-R-BRW-ORG-RGI    ";
    String CPN_R_BRW_ORG_RGE = "BP-R-BRW-ORG-RGE    ";
    int K_MAX_CNT = 500;
    int K_MAX_COL = 99;
    int K_MAX_ROW = 10;
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    String WS_ACO_AC = " ";
    BPZSORLB_WS_SORLB_BRO2 WS_SORLB_BRO2 = new BPZSORLB_WS_SORLB_BRO2();
    BPZSORLB_WS_SORLB_BRO WS_SORLB_BRO = new BPZSORLB_WS_SORLB_BRO();
    BPZSORLB_WS_SORLB_BRO3 WS_SORLB_BRO3 = new BPZSORLB_WS_SORLB_BRO3();
    char WS_OUT = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPRORGL BPRORGL = new BPRORGL();
    BPRORGI BPRORGI = new BPRORGI();
    BPRORGE BPRORGE = new BPRORGE();
    BPCRORLB BPCRORLB = new BPCRORLB();
    BPCRORGI BPCRORGI = new BPCRORGI();
    BPCRORGL BPCRORGL = new BPCRORGL();
    CICQACAC CICQACAC = new CICQACAC();
    BPCRORIB BPCRORIB = new BPCRORIB();
    BPCROREB BPCROREB = new BPCROREB();
    SCCGWA SCCGWA;
    BPCSORLB BPCSORLB;
    public void MP(SCCGWA SCCGWA, BPCSORLB BPCSORLB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSORLB = BPCSORLB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSORLB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSORLB.AC_DT);
        if (BPCSORLB.FUNC == '1') {
            B011_CHECK_INPUT_DATA();
            if (pgmRtn) return;
            B021_BRAWSE_ORGI_RECORD();
            if (pgmRtn) return;
        }
        if (BPCSORLB.FUNC == '2') {
            B012_CHECK_INPUT_DATA();
            if (pgmRtn) return;
            B022_BRAWSE_ORGl_RECORD();
            if (pgmRtn) return;
        }
        if (BPCSORLB.FUNC == '3') {
            B023_BRAWSE_ORGE_RECORD();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCSORLB.AC_DT == 0 
            && BPCSORLB.MN_JRN == 0 
            && BPCSORLB.AC_TYPE.trim().length() == 0 
            && BPCSORLB.SUB_AC.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_ONE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((BPCSORLB.AC_DT != 0 
            || BPCSORLB.MN_JRN != 0) 
            && (BPCSORLB.AC_TYPE.trim().length() > 0 
            || BPCSORLB.SUB_AC.trim().length() > 0)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B011_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCSORLB.AC_DT == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B012_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSORLB.AC_DT);
        CEP.TRC(SCCGWA, BPCSORLB.TX_JRN);
        if (BPCSORLB.AC_DT == 0 
            || BPCSORLB.TX_JRN == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_BRAWSE_ORGL_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRORLB);
        IBS.init(SCCGWA, BPRORGL);
        BPRORGL.AC_DT = BPCSORLB.AC_DT;
        BPRORGL.TX_TOOL = BPCSORLB.TX_TOOL;
        BPRORGL.INCO_OLD_BR = BPCSORLB.OLD_BR;
        BPRORGL.INCO_NEW_BR = BPCSORLB.NEW_BR;
        BPRORGL.TX_FLG = BPCSORLB.TX_FLG;
        BPCRORLB.INFO.POINTER = BPRORGL;
        BPCRORLB.RETURN_INFO = ' ';
        IBS.init(SCCGWA, BPCRORLB.RC);
        BPCRORLB.FUNC = 'S';
        S000_CALL_BPZRORLB();
        if (pgmRtn) return;
        B021_OUTPUT_TITLE();
        if (pgmRtn) return;
        for (WS_CNT = 1; BPCRORLB.RC.RC_CODE == 0 
            && WS_CNT < K_MAX_CNT 
            && SCCMPAG.FUNC != 'E'; WS_CNT += 1) {
            BPCRORLB.RETURN_INFO = ' ';
            IBS.init(SCCGWA, BPCRORLB.RC);
            BPCRORLB.FUNC = 'R';
            S000_CALL_BPZRORLB();
            if (pgmRtn) return;
            if (BPCRORLB.RC.RC_CODE == 0) {
                BPRORGI.KEY.AC_DT = BPRORGL.AC_DT;
                BPRORGI.KEY.MN_JRN = BPRORGL.KEY.TX_JRN;
                BPCRORGI.INFO.FUNC = 'Q';
                S000_CALL_BPZRORGI();
                if (pgmRtn) return;
                B025_OUTPUT_DATA();
                if (pgmRtn) return;
            }
        }
        BPCRORLB.RETURN_INFO = ' ';
        IBS.init(SCCGWA, BPCRORLB.RC);
        BPCRORLB.FUNC = 'E';
        S000_CALL_BPZRORLB();
        if (pgmRtn) return;
    }
    public void B021_BRAWSE_ORGI_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRORIB);
        IBS.init(SCCGWA, BPRORGI);
        BPRORGI.KEY.AC_DT = BPCSORLB.AC_DT;
        BPCRORIB.INFO.POINTER = BPRORGI;
        BPCRORIB.RETURN_INFO = ' ';
        IBS.init(SCCGWA, BPCRORIB.RC);
        BPCRORIB.FUNC = 'S';
        S000_CALL_BPZRORIB();
        if (pgmRtn) return;
        B021_OUTPUT_TITLE();
        if (pgmRtn) return;
        for (WS_CNT = 1; BPCRORIB.RC.RC_CODE == 0 
            && WS_CNT < K_MAX_CNT 
            && SCCMPAG.FUNC != 'E'; WS_CNT += 1) {
            BPCRORIB.RETURN_INFO = ' ';
            IBS.init(SCCGWA, BPCRORIB.RC);
            BPCRORIB.FUNC = 'R';
            S000_CALL_BPZRORIB();
            if (pgmRtn) return;
            if (BPCRORIB.RC.RC_CODE == 0) {
                CEP.TRC(SCCGWA, BPRORGI.AC_ORGI_TYP);
                if (BPCSORLB.MN_JRN != 0) {
                    if (BPCSORLB.MN_JRN == BPRORGI.KEY.MN_JRN) {
                        WS_OUT = 'Y';
                    } else {
                        WS_OUT = 'N';
                    }
                } else {
                    WS_OUT = 'Y';
                }
                CEP.TRC(SCCGWA, BPCSORLB.TX_TOOL);
                CEP.TRC(SCCGWA, BPRORGI.TX_TOOL);
                if (WS_OUT == 'Y') {
                    CEP.TRC(SCCGWA, "SORLB-TX-TOOL");
                    if (BPCSORLB.TX_TOOL.trim().length() > 0) {
                        if (!BPCSORLB.TX_TOOL.equalsIgnoreCase(BPRORGI.TX_TOOL)) {
                            WS_OUT = 'N';
                        }
                    }
                }
                if (WS_OUT == 'Y') {
                    if (BPCSORLB.OLD_BR != 0) {
                        if (BPCSORLB.OLD_BR != BPRORGI.INCO_OLD_BR) {
                            WS_OUT = 'N';
                        }
                    }
                }
                if (WS_OUT == 'Y') {
                    if (BPCSORLB.TX_FLG != ' ') {
                        if (BPCSORLB.TX_FLG != BPRORGI.ORGI_FLG) {
                            WS_OUT = 'N';
                        }
                    }
                }
                if (WS_OUT == 'Y') {
                    if (BPCSORLB.NEW_BR != 0) {
                        if (BPCSORLB.NEW_BR != BPRORGI.INCO_NEW_BR) {
                            WS_OUT = 'N';
                        }
                    }
                }
                CEP.TRC(SCCGWA, WS_OUT);
                if (WS_OUT == 'Y') {
                    B025_OUTPUT_DATA();
                    if (pgmRtn) return;
                }
            }
        }
        BPCRORIB.RETURN_INFO = ' ';
        IBS.init(SCCGWA, BPCRORIB.RC);
        BPCRORIB.FUNC = 'E';
        S000_CALL_BPZRORIB();
        if (pgmRtn) return;
    }
    public void B022_BRAWSE_ORGL_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRORLB);
        IBS.init(SCCGWA, BPRORGL);
        BPRORGL.AC_DT = BPCSORLB.AC_DT;
        BPRORGL.KEY.TX_DATE = BPCSORLB.AC_DT;
        BPRORGL.KEY.TX_JRN = BPCSORLB.TX_JRN;
        BPCRORLB.INFO.POINTER = BPRORGL;
        BPCRORLB.RETURN_INFO = ' ';
        IBS.init(SCCGWA, BPCRORLB.RC);
        BPCRORLB.FUNC = 'S';
        S000_CALL_BPZRORLB();
        if (pgmRtn) return;
        B022_OUTPUT_TITLE();
        if (pgmRtn) return;
        for (WS_CNT = 1; BPCRORLB.RC.RC_CODE == 0 
            && WS_CNT < K_MAX_CNT 
            && SCCMPAG.FUNC != 'E'; WS_CNT += 1) {
            BPCRORLB.RETURN_INFO = ' ';
            IBS.init(SCCGWA, BPCRORLB.RC);
            BPCRORLB.FUNC = 'R';
            S000_CALL_BPZRORLB();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCRORLB.RC.RC_CODE);
            if (BPCRORLB.RC.RC_CODE == 0) {
                B026_OUTPUT_DATA();
                if (pgmRtn) return;
            }
        }
        BPCRORLB.RETURN_INFO = ' ';
        IBS.init(SCCGWA, BPCRORLB.RC);
        BPCRORLB.FUNC = 'E';
        S000_CALL_BPZRORLB();
        if (pgmRtn) return;
    }
    public void B023_BRAWSE_ORGE_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCROREB);
        IBS.init(SCCGWA, BPRORGE);
        CEP.TRC(SCCGWA, BPCSORLB.AC_DT);
        BPRORGE.KEY.TX_DATE = BPCSORLB.AC_DT;
        BPRORGE.KEY.TX_JRN = BPCSORLB.TX_JRN;
        BPRORGE.KEY.TX_SEQ = BPCSORLB.TX_SEQ;
        if (BPCSORLB.AC_TYPE.equalsIgnoreCase("1")) {
            BPRORGE.KEY.INCO_AC = BPCSORLB.TX_TOOL;
        } else {
            BPRORGE.KEY.INCO_AC = BPCSORLB.TX_TOOL;
            BPRORGE.KEY.INCO_ACO_AC = BPCSORLB.ACO_AC;
        }
        BPCROREB.INFO.POINTER = BPRORGE;
        BPCROREB.RETURN_INFO = ' ';
        IBS.init(SCCGWA, BPCROREB.RC);
        BPCROREB.FUNC = 'S';
        S000_CALL_BPZROREB();
        if (pgmRtn) return;
        B023_OUTPUT_TITLE();
        if (pgmRtn) return;
        for (WS_CNT = 1; BPCROREB.RC.RC_CODE == 0 
            && WS_CNT < K_MAX_CNT 
            && SCCMPAG.FUNC != 'E'; WS_CNT += 1) {
            BPCROREB.RETURN_INFO = ' ';
            IBS.init(SCCGWA, BPCROREB.RC);
            BPCROREB.FUNC = 'R';
            S000_CALL_BPZROREB();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCROREB.RC.RC_CODE);
            if (BPCROREB.RC.RC_CODE == 0) {
                B027_OUTPUT_DATA();
                if (pgmRtn) return;
            }
        }
        BPCROREB.RETURN_INFO = ' ';
        IBS.init(SCCGWA, BPCROREB.RC);
        BPCROREB.FUNC = 'E';
        S000_CALL_BPZROREB();
        if (pgmRtn) return;
    }
    public void B021_OUTPUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 96;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B022_OUTPUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 171;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B023_OUTPUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 100;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B025_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_SORLB_BRO);
        WS_SORLB_BRO.WS_BRO_AC_DT = BPRORGI.KEY.AC_DT;
        WS_SORLB_BRO.WS_BRO_ORGI_TYP = BPRORGI.ORGI_TYP;
        WS_SORLB_BRO.WS_BRO_TX_JRN = BPRORGI.KEY.MN_JRN;
        WS_SORLB_BRO.WS_BRO_TX_TOOL = BPRORGI.TX_TOOL;
        WS_SORLB_BRO.WS_BRO_INCO_OLD_BR = BPRORGI.INCO_OLD_BR;
        WS_SORLB_BRO.WS_BRO_INCO_NEW_BR = BPRORGI.INCO_NEW_BR;
        WS_SORLB_BRO.WS_BRO_AC_ORGI_TYP = BPRORGI.AC_ORGI_TYP;
        WS_SORLB_BRO.WS_BRO_INCO_DATE = BPRORGI.INCO_DATE;
        WS_SORLB_BRO.WS_BRO_TX_FLG = BPRORGI.ORGI_FLG;
        WS_SORLB_BRO.WS_BRO_ACO_AC = BPRORGI.ACO_AC;
        if (WS_CNT == 1) {
            CEP.TRC(SCCGWA, WS_SORLB_BRO.WS_BRO_AC_DT);
            CEP.TRC(SCCGWA, WS_SORLB_BRO.WS_BRO_ORGI_TYP);
            CEP.TRC(SCCGWA, WS_SORLB_BRO.WS_BRO_TX_JRN);
            CEP.TRC(SCCGWA, WS_SORLB_BRO.WS_BRO_TX_TOOL);
            CEP.TRC(SCCGWA, WS_SORLB_BRO.WS_BRO_INCO_OLD_BR);
            CEP.TRC(SCCGWA, WS_SORLB_BRO.WS_BRO_INCO_NEW_BR);
            CEP.TRC(SCCGWA, WS_SORLB_BRO.WS_BRO_AC_ORGI_TYP);
            CEP.TRC(SCCGWA, WS_SORLB_BRO.WS_BRO_INCO_DATE);
            CEP.TRC(SCCGWA, WS_SORLB_BRO.WS_BRO_TX_FLG);
        }
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_SORLB_BRO);
        SCCMPAG.DATA_LEN = 96;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B026_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_SORLB_BRO2);
        WS_SORLB_BRO2.WS_SORLB_INCO_DATE = BPRORGL.AC_DT;
        WS_SORLB_BRO2.WS_SORLB_TX_JRN = BPRORGL.KEY.TX_JRN;
        WS_SORLB_BRO2.WS_SORLB_TX_SEQ = BPRORGL.KEY.TX_SEQ;
        WS_SORLB_BRO2.WS_SORLB_BSP_JRN = BPRORGL.MN_JRN;
        WS_SORLB_BRO2.WS_SORLB_AC_ORGI_TYP = BPRORGL.AC_ORGI_TYP;
        WS_SORLB_BRO2.WS_SORLB_TX_TOOL = BPRORGL.TX_TOOL;
        WS_SORLB_BRO2.WS_SORLB_CCY = BPRORGL.CCY;
        WS_SORLB_BRO2.WS_SORLB_CCY_TYPE = BPRORGL.CCY_TYPE;
        WS_SORLB_BRO2.WS_SORLB_SEQ = BPRORGL.SEQ;
        WS_SORLB_BRO2.WS_SORLB_ACO_AC = BPRORGL.ACO_AC;
        WS_SORLB_BRO2.WS_SORLB_INCO_OLD_BR = BPRORGL.INCO_OLD_BR;
        WS_SORLB_BRO2.WS_SORLB_INCO_NEW_BR = BPRORGL.INCO_NEW_BR;
        WS_SORLB_BRO2.WS_SORLB_TX_FLG = BPRORGL.TX_FLG;
        WS_SORLB_BRO2.WS_SORLB_ERROR_CODE = BPRORGL.ERROR_CODE;
        WS_SORLB_BRO2.WS_SORLB_TX_TM = BPRORGL.TX_TM;
        WS_SORLB_BRO2.WS_SORLB_UPT_DT = BPRORGL.UPT_DT;
        WS_SORLB_BRO2.WS_SORLB_UPT_TLR = BPRORGL.UPT_TLR;
        WS_SORLB_BRO2.WS_SORLB_CRT_DATE = BPRORGL.CRT_DATE;
        WS_SORLB_BRO2.WS_SORLB_CRT_TLR = BPRORGL.CRT_TLR;
        WS_SORLB_BRO2.WS_SORLB_CI_NO = BPRORGL.CI_NO;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_SORLB_BRO2);
        SCCMPAG.DATA_LEN = 171;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B027_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_SORLB_BRO3);
        WS_SORLB_BRO3.WS_BRO3_AC_DATE = BPRORGE.KEY.TX_DATE;
        WS_SORLB_BRO3.WS_BRO3_TX_JRN = BPRORGE.KEY.TX_JRN;
        WS_SORLB_BRO3.WS_BRO3_TX_SEQ = BPRORGE.KEY.TX_SEQ;
        WS_SORLB_BRO3.WS_BRO3_TX_DATE = BPRORGE.AC_DATE;
        WS_SORLB_BRO3.WS_BRO3_MN_JRN = BPRORGE.MN_JRN;
        WS_SORLB_BRO3.WS_BRO3_TX_TOOL = BPRORGE.KEY.INCO_AC;
        WS_SORLB_BRO3.WS_BRO3_ACO_AC = BPRORGE.KEY.INCO_ACO_AC;
        WS_SORLB_BRO3.WS_BRO3_BBR_FLG = BPRORGE.BBR_FLG;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_SORLB_BRO3);
        SCCMPAG.DATA_LEN = 100;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B020_GET_ACO_NO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "************************");
        CEP.TRC(SCCGWA, BPRORGI.TX_TOOL);
        CEP.TRC(SCCGWA, BPRORGI.SEQ);
        CEP.TRC(SCCGWA, BPRORGI.CCY);
        CEP.TRC(SCCGWA, BPRORGI.CCY_TYPE);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = BPRORGI.TX_TOOL;
        CICQACAC.DATA.AGR_SEQ = BPRORGI.SEQ;
        CICQACAC.DATA.CCY_ACAC = BPRORGI.CCY;
        CICQACAC.DATA.CR_FLG = BPRORGI.CCY_TYPE;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.RC);
        if (CICQACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CHEB_ERROR2);
        }
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
    }
    public void S000_CALL_BPZRORLB() throws IOException,SQLException,Exception {
        BPCRORLB.INFO.LEN = 245;
        IBS.CALLCPN(SCCGWA, CPN_R_BRW_ORG_RGL, BPCRORLB);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCRORLB.RC);
        if (BPCRORLB.RC.RC_CODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRORLB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRORIB() throws IOException,SQLException,Exception {
        BPCRORIB.INFO.LEN = 190;
        IBS.CALLCPN(SCCGWA, CPN_R_BRW_ORG_RGI, BPCRORIB);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCRORIB.RC);
        if (BPCRORIB.RC.RC_CODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRORIB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZROREB() throws IOException,SQLException,Exception {
        BPCROREB.INFO.LEN = 112;
        IBS.CALLCPN(SCCGWA, CPN_R_BRW_ORG_RGE, BPCROREB);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCROREB.RC);
        if (BPCROREB.RC.RC_CODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCROREB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRORGI() throws IOException,SQLException,Exception {
        BPCRORGI.INFO.POINTER = BPRORGI;
        BPCRORGI.INFO.LEN = 190;
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-ORGI", BPCRORGI);
        if (BPCRORGI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRORGI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRORGL() throws IOException,SQLException,Exception {
        BPCRORGL.INFO.POINTER = BPRORGL;
        BPCRORGL.INFO.LEN = 245;
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-ORGL", BPCRORGL);
        if (BPCRORGL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRORGL.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
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
