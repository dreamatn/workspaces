package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCWA;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.CI.*;
import com.hisun.DD.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class TDZCZDE {
    int JIBS_tmp_int;
    brParm TDTSMST_BR = new brParm();
    DBParm TDTSMST_RD;
    DBParm TDTIREV_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 10;
    int K_MAX_COL = 0;
    int K_COL_CNT = 0;
    String WS_MSGID = " ";
    String WS_AC_NO = " ";
    short WS_TIME = 0;
    char WS_SMST_FLG = ' ';
    char WS_XX_FLG = ' ';
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    TDCOCZDE TDCOCZDE = new TDCOCZDE();
    TDRCMST TDRCMST = new TDRCMST();
    TDRSMST TDRSMST = new TDRSMST();
    TDRINST TDRINST = new TDRINST();
    TDRIREV TDRIREV = new TDRIREV();
    CICQACAC CICQACAC = new CICQACAC();
    CICQCIAC CICQCIAC = new CICQCIAC();
    CICSAGEN CICSAGEN = new CICSAGEN();
    DDCSCINM DDCSCINM = new DDCSCINM();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCGWA SCCGWA;
    SCCCWA SCCCWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    TDCCZDE TDCCZDE;
    public void MP(SCCGWA SCCGWA, TDCCZDE TDCCZDE) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCCZDE = TDCCZDE;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDZCZDE return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCCZDE.AC_TYP);
        CEP.TRC(SCCGWA, TDCCZDE.AC_NO);
        CEP.TRC(SCCGWA, TDCCZDE.CI_NO);
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (TDCCZDE.AC_TYP.equalsIgnoreCase("021")) {
            if (TDCCZDE.AC_NO.trim().length() == 0) {
                B021_GET_LIST();
                if (pgmRtn) return;
            } else {
                B021_AC_GET_LIST();
                if (pgmRtn) return;
            }
        } else {
            if (TDCCZDE.AC_NO.trim().length() == 0) {
                B035_GET_LIST();
                if (pgmRtn) return;
            } else {
                B035_AC_GET_LIST();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (TDCCZDE.CI_NO.trim().length() == 0 
            && TDCCZDE.AC_NO.trim().length() == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B021_GET_LIST() throws IOException,SQLException,Exception {
        R000_01_OUT_TITLE();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CICQCIAC);
        CEP.TRC(SCCGWA, TDCCZDE.CI_NO);
        CICQCIAC.DATA.CI_NO = TDCCZDE.CI_NO;
        while (CICQCIAC.DATA.LAST_FLG != 'Y') {
            CICQCIAC.FUNC = '2';
            CICQCIAC.DATA.CI_NO = TDCCZDE.CI_NO;
            S000_CALL_CIZQCIAC();
            if (pgmRtn) return;
            WS_TIME = 0;
            WS_TIME = 1;
            while (WS_TIME <= 100 
                && CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_TIME-1].ENTY_NO.trim().length() != 0) {
                CEP.TRC(SCCGWA, CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_TIME-1].ENTY_NO);
                IBS.init(SCCGWA, TDCOCZDE);
                IBS.init(SCCGWA, TDRSMST);
                TDRSMST.KEY.ACO_AC = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_TIME-1].ENTY_NO;
                T000_READ_TDTSMST();
                if (pgmRtn) return;
                if (TDRSMST.PRDAC_CD.equalsIgnoreCase("021")) {
                    TDCOCZDE.AC_NO = TDRSMST.AC_NO;
                    TDCOCZDE.OAC_NO = TDRSMST.OPEN_DR_AC;
                    TDCOCZDE.OPEN_DATE = TDRSMST.OPEN_DATE;
                    TDCOCZDE.OWNER_BR = TDRSMST.OWNER_BR;
                    TDCOCZDE.CRT_TLR = TDRSMST.CRT_TLR;
                    IBS.init(SCCGWA, CICSAGEN);
                    CICSAGEN.FUNC = 'S';
                    CICSAGEN.OUT_AC = TDRSMST.AC_NO;
                    CICSAGEN.AGENT_TP = "3";
                    S000_CALL_CIZSAGEN();
                    if (pgmRtn) return;
                    TDCOCZDE.DB_TYP = CICSAGEN.FOUND_FLG;
                    IBS.init(SCCGWA, CICQACAC);
                    CICQACAC.FUNC = 'R';
                    CICQACAC.DATA.AGR_NO = TDRSMST.OPEN_DR_AC;
                    CICQACAC.DATA.CCY_ACAC = TDRSMST.OPEN_DR_AC_CCY;
                    CICQACAC.DATA.CR_FLG = TDRSMST.OPEN_DR_AC_TYP;
                    CICQACAC.DATA.AGR_SEQ = TDRSMST.OPEN_DR_AC_SEQ;
                    S000_CALL_CIZQACAC();
                    if (pgmRtn) return;
                    if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
                        IBS.init(SCCGWA, DDCSCINM);
                        DDCSCINM.INPUT_DATA.FUNC = '2';
                        DDCSCINM.INPUT_DATA.AC_NO = TDRSMST.OPEN_DR_AC;
                        DDCSCINM.INPUT_DATA.CCY = TDRSMST.OPEN_DR_AC_CCY;
                        DDCSCINM.INPUT_DATA.CCY_TYP = TDRSMST.OPEN_DR_AC_TYP;
                        S000_CALL_DDZSCINM();
                        if (pgmRtn) return;
                        TDCOCZDE.OBV_TYP = '1';
                        TDCOCZDE.OBV_NO = DDCSCINM.OUTPUT_DATA.PSBK_NO;
                    } else {
                        TDCOCZDE.OBV_TYP = '2';
                        TDCOCZDE.OBV_NO = TDRSMST.OPEN_DR_AC;
                    }
                    IBS.init(SCCGWA, TDRIREV);
                    TDRIREV.KEY.ACO_AC = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_TIME-1].ENTY_NO;
                    TDRIREV.KEY.STR_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    T000_READ_TDTIREV();
                    if (pgmRtn) return;
                    B010_OUT_LIST();
                    if (pgmRtn) return;
                }
                WS_TIME += 1;
            }
        }
    }
    public void B035_GET_LIST() throws IOException,SQLException,Exception {
        R000_01_OUT_TITLE();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CICQCIAC);
        CEP.TRC(SCCGWA, TDCCZDE.CI_NO);
        CICQCIAC.DATA.CI_NO = TDCCZDE.CI_NO;
        CICQCIAC.DATA.PROD_CD = "1202010201";
        while (CICQCIAC.DATA.LAST_FLG != 'Y') {
            CICQCIAC.FUNC = '2';
            CICQCIAC.DATA.CI_NO = TDCCZDE.CI_NO;
            S000_CALL_CIZQCIAC();
            if (pgmRtn) return;
            WS_TIME = 0;
            WS_TIME = 1;
            while (WS_TIME <= 100 
                && CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_TIME-1].ENTY_NO.trim().length() != 0) {
                CEP.TRC(SCCGWA, CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_TIME-1].ENTY_NO);
                IBS.init(SCCGWA, TDCOCZDE);
                IBS.init(SCCGWA, TDRSMST);
                TDRSMST.KEY.ACO_AC = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_TIME-1].ENTY_NO;
                T000_READ_TDTSMST();
                if (pgmRtn) return;
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                if (TDRSMST.STSW.substring(52 - 1, 52 + 1 - 1).equalsIgnoreCase("1")) {
                    TDCOCZDE.AC_NO = TDRSMST.AC_NO;
                    TDCOCZDE.PROD_CD = TDRSMST.PROD_CD;
                    TDCOCZDE.TERM = TDRSMST.TERM;
                    TDCOCZDE.OAC_NO = TDRSMST.OPEN_DR_AC;
                    TDCOCZDE.OPEN_DATE = TDRSMST.OPEN_DATE;
                    TDCOCZDE.OWNER_BR = TDRSMST.OWNER_BR;
                    TDCOCZDE.CRT_TLR = TDRSMST.CRT_TLR;
                    TDCOCZDE.CCY = TDRSMST.CCY;
                    TDCOCZDE.CCY_TYP = TDRSMST.CCY_TYP;
                    TDCOCZDE.STSW = TDRSMST.STSW;
                    TDCOCZDE.INSTR_MTH = TDRSMST.INSTR_MTH;
                    IBS.init(SCCGWA, TDRINST);
                    TDRINST.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
                    T000_READ_TDTIREV();
                    if (pgmRtn) return;
                    TDCOCZDE.STL_AC = TDRINST.STL_AC;
                    IBS.init(SCCGWA, TDRIREV);
                    TDRIREV.KEY.ACO_AC = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_TIME-1].ENTY_NO;
                    TDRIREV.KEY.STR_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    T000_READ_TDTIREV();
                    if (pgmRtn) return;
                    TDCOCZDE.RAT_INT = TDRIREV.CON_RATE;
                    B010_OUT_LIST();
                    if (pgmRtn) return;
                }
                WS_TIME += 1;
            }
        }
        IBS.init(SCCGWA, CICQCIAC);
        CEP.TRC(SCCGWA, TDCCZDE.CI_NO);
        CICQCIAC.DATA.CI_NO = TDCCZDE.CI_NO;
        CICQCIAC.DATA.PROD_CD = "1202030201";
        while (CICQCIAC.DATA.LAST_FLG != 'Y') {
            CICQCIAC.FUNC = '2';
            CICQCIAC.DATA.CI_NO = TDCCZDE.CI_NO;
            S000_CALL_CIZQCIAC();
            if (pgmRtn) return;
            WS_TIME = 0;
            WS_TIME = 1;
            while (WS_TIME <= 100 
                && CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_TIME-1].ENTY_NO.trim().length() != 0) {
                IBS.init(SCCGWA, TDCOCZDE);
                IBS.init(SCCGWA, TDRSMST);
                TDRSMST.KEY.ACO_AC = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_TIME-1].ENTY_NO;
                T000_READ_TDTSMST();
                if (pgmRtn) return;
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                if (TDRSMST.STSW.substring(52 - 1, 52 + 1 - 1).equalsIgnoreCase("1")) {
                    TDCOCZDE.AC_NO = TDRSMST.AC_NO;
                    TDCOCZDE.PROD_CD = TDRSMST.PROD_CD;
                    TDCOCZDE.TERM = TDRSMST.TERM;
                    TDCOCZDE.OAC_NO = TDRSMST.OPEN_DR_AC;
                    TDCOCZDE.OPEN_DATE = TDRSMST.OPEN_DATE;
                    TDCOCZDE.OWNER_BR = TDRSMST.OWNER_BR;
                    TDCOCZDE.CRT_TLR = TDRSMST.CRT_TLR;
                    TDCOCZDE.CCY = TDRSMST.CCY;
                    TDCOCZDE.CCY_TYP = TDRSMST.CCY_TYP;
                    TDCOCZDE.STSW = TDRSMST.STSW;
                    TDCOCZDE.INSTR_MTH = TDRSMST.INSTR_MTH;
                    IBS.init(SCCGWA, TDRINST);
                    TDRINST.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
                    T000_READ_TDTIREV();
                    if (pgmRtn) return;
                    TDCOCZDE.STL_AC = TDRINST.STL_AC;
                    IBS.init(SCCGWA, TDRIREV);
                    TDRIREV.KEY.ACO_AC = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_TIME-1].ENTY_NO;
                    TDRIREV.KEY.STR_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    T000_READ_TDTIREV();
                    if (pgmRtn) return;
                    TDCOCZDE.RAT_INT = TDRIREV.CON_RATE;
                    B010_OUT_LIST();
                    if (pgmRtn) return;
                }
                WS_TIME += 1;
            }
        }
    }
    public void B021_AC_GET_LIST() throws IOException,SQLException,Exception {
        R000_01_OUT_TITLE();
        if (pgmRtn) return;
        IBS.init(SCCGWA, TDCOCZDE);
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.AC_NO = TDCCZDE.AC_NO;
        TDRSMST.PRDAC_CD = "021";
        T000_STARTBR_TDTSMST();
        if (pgmRtn) return;
        T000_READNEXT_TDTSMST();
        if (pgmRtn) return;
        while (WS_SMST_FLG != 'N') {
            TDCOCZDE.AC_NO = TDRSMST.AC_NO;
            TDCOCZDE.OAC_NO = TDRSMST.OPEN_DR_AC;
            TDCOCZDE.OPEN_DATE = TDRSMST.OPEN_DATE;
            TDCOCZDE.OWNER_BR = TDRSMST.OWNER_BR;
            TDCOCZDE.CRT_TLR = TDRSMST.CRT_TLR;
            IBS.init(SCCGWA, CICSAGEN);
            CICSAGEN.FUNC = 'S';
            CICSAGEN.OUT_AC = TDRSMST.AC_NO;
            CICSAGEN.AGENT_TP = "3";
            S000_CALL_CIZSAGEN();
            if (pgmRtn) return;
            TDCOCZDE.DB_TYP = CICSAGEN.FOUND_FLG;
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = TDRSMST.OPEN_DR_AC;
            CICQACAC.DATA.CCY_ACAC = TDRSMST.OPEN_DR_AC_CCY;
            CICQACAC.DATA.CR_FLG = TDRSMST.OPEN_DR_AC_TYP;
            CICQACAC.DATA.AGR_SEQ = TDRSMST.OPEN_DR_AC_SEQ;
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
                CEP.TRC(SCCGWA, TDRSMST.OPEN_DR_AC);
                CEP.TRC(SCCGWA, TDRSMST.OPEN_DR_AC_CCY);
                CEP.TRC(SCCGWA, TDRSMST.OPEN_DR_AC_TYP);
                IBS.init(SCCGWA, DDCSCINM);
                DDCSCINM.INPUT_DATA.FUNC = '2';
                DDCSCINM.INPUT_DATA.AC_NO = TDRSMST.OPEN_DR_AC;
                DDCSCINM.INPUT_DATA.CCY = TDRSMST.OPEN_DR_AC_CCY;
                DDCSCINM.INPUT_DATA.CCY_TYP = TDRSMST.OPEN_DR_AC_TYP;
                S000_CALL_DDZSCINM();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, DDCSCINM.OUTPUT_DATA.PSBK_NO);
                TDCOCZDE.OBV_TYP = '1';
                TDCOCZDE.OBV_NO = DDCSCINM.OUTPUT_DATA.PSBK_NO;
            } else {
                TDCOCZDE.OBV_TYP = '2';
                TDCOCZDE.OBV_NO = TDRSMST.OPEN_DR_AC;
            }
            IBS.init(SCCGWA, TDRIREV);
            TDRIREV.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
            TDRIREV.KEY.STR_DATE = SCCGWA.COMM_AREA.AC_DATE;
            T000_READ_TDTIREV();
            if (pgmRtn) return;
            B010_OUT_LIST();
            if (pgmRtn) return;
            WS_TIME += 1;
            T000_READNEXT_TDTSMST();
            if (pgmRtn) return;
        }
        T000_ENDBR_TDTSMST();
        if (pgmRtn) return;
    }
    public void B035_AC_GET_LIST() throws IOException,SQLException,Exception {
        R000_01_OUT_TITLE();
        if (pgmRtn) return;
        IBS.init(SCCGWA, TDCOCZDE);
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.AC_NO = TDCCZDE.AC_NO;
        TDRSMST.PRDAC_CD = "035";
        T000_XX_STARTBR_TDTSMST();
        if (pgmRtn) return;
        T000_XX_READNEXT_TDTSMST();
        if (pgmRtn) return;
        while (WS_XX_FLG != 'N') {
            TDCOCZDE.AC_NO = TDRSMST.AC_NO;
            TDCOCZDE.PROD_CD = TDRSMST.PROD_CD;
            TDCOCZDE.TERM = TDRSMST.TERM;
            TDCOCZDE.OAC_NO = TDRSMST.OPEN_DR_AC;
            TDCOCZDE.OPEN_DATE = TDRSMST.OPEN_DATE;
            TDCOCZDE.OWNER_BR = TDRSMST.OWNER_BR;
            TDCOCZDE.CRT_TLR = TDRSMST.CRT_TLR;
            TDCOCZDE.CCY = TDRSMST.CCY;
            TDCOCZDE.CCY_TYP = TDRSMST.CCY_TYP;
            TDCOCZDE.STSW = TDRSMST.STSW;
            TDCOCZDE.INSTR_MTH = TDRSMST.INSTR_MTH;
            IBS.init(SCCGWA, TDRINST);
            TDRINST.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
            T000_READ_TDTIREV();
            if (pgmRtn) return;
            TDCOCZDE.STL_AC = TDRINST.STL_AC;
            IBS.init(SCCGWA, TDRIREV);
            TDRIREV.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
            TDRIREV.KEY.STR_DATE = SCCGWA.COMM_AREA.AC_DATE;
            T000_READ_TDTIREV();
            if (pgmRtn) return;
            TDCOCZDE.RAT_INT = TDRIREV.CON_RATE;
            B010_OUT_LIST();
            if (pgmRtn) return;
            T000_XX_READNEXT_TDTSMST();
            if (pgmRtn) return;
        }
        T000_XX_ENDBR_TDTSMST();
        if (pgmRtn) return;
        IBS.init(SCCGWA, TDCOCZDE);
        IBS.init(SCCGWA, TDRSMST);
        WS_XX_FLG = ' ';
        TDRSMST.AC_NO = TDCCZDE.AC_NO;
        TDRSMST.PRDAC_CD = "036";
        T000_XX_STARTBR_TDTSMST();
        if (pgmRtn) return;
        T000_XX_READNEXT_TDTSMST();
        if (pgmRtn) return;
        while (WS_XX_FLG != 'N') {
            TDCOCZDE.AC_NO = TDRSMST.AC_NO;
            TDCOCZDE.PROD_CD = TDRSMST.PROD_CD;
            TDCOCZDE.TERM = TDRSMST.TERM;
            TDCOCZDE.OAC_NO = TDRSMST.OPEN_DR_AC;
            TDCOCZDE.OPEN_DATE = TDRSMST.OPEN_DATE;
            TDCOCZDE.OWNER_BR = TDRSMST.OWNER_BR;
            TDCOCZDE.CRT_TLR = TDRSMST.CRT_TLR;
            TDCOCZDE.CCY = TDRSMST.CCY;
            TDCOCZDE.CCY_TYP = TDRSMST.CCY_TYP;
            TDCOCZDE.STSW = TDRSMST.STSW;
            TDCOCZDE.INSTR_MTH = TDRSMST.INSTR_MTH;
            IBS.init(SCCGWA, TDRINST);
            TDRINST.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
            T000_READ_TDTIREV();
            if (pgmRtn) return;
            TDCOCZDE.STL_AC = TDRINST.STL_AC;
            IBS.init(SCCGWA, TDRIREV);
            TDRIREV.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
            TDRIREV.KEY.STR_DATE = SCCGWA.COMM_AREA.AC_DATE;
            T000_READ_TDTIREV();
            if (pgmRtn) return;
            TDCOCZDE.RAT_INT = TDRIREV.CON_RATE;
            B010_OUT_LIST();
            if (pgmRtn) return;
            T000_XX_READNEXT_TDTSMST();
            if (pgmRtn) return;
        }
        T000_XX_ENDBR_TDTSMST();
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
    public void B010_OUT_LIST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCOCZDE.PROD_CD);
        CEP.TRC(SCCGWA, TDCOCZDE.AC_NO);
        CEP.TRC(SCCGWA, TDCOCZDE.OBV_NO);
        CEP.TRC(SCCGWA, TDCOCZDE.OBV_TYP);
        CEP.TRC(SCCGWA, TDCOCZDE.OAC_NO);
        CEP.TRC(SCCGWA, TDCOCZDE.TERM);
        CEP.TRC(SCCGWA, TDCOCZDE.CCY);
        CEP.TRC(SCCGWA, TDCOCZDE.CCY_TYP);
        CEP.TRC(SCCGWA, TDCOCZDE.OPEN_DATE);
        CEP.TRC(SCCGWA, TDCOCZDE.OWNER_BR);
        CEP.TRC(SCCGWA, TDCOCZDE.CRT_TLR);
        CEP.TRC(SCCGWA, TDCOCZDE.RAT_INT);
        CEP.TRC(SCCGWA, TDCOCZDE.STSW);
        CEP.TRC(SCCGWA, TDCOCZDE.INSTR_MTH);
        CEP.TRC(SCCGWA, TDCOCZDE.STL_AC);
        CEP.TRC(SCCGWA, TDCOCZDE.DB_TYP);
        TDCOCZDE.CHNL_NO = TDRSMST.CHNL_NO;
        CEP.TRC(SCCGWA, TDCOCZDE.CHNL_NO);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, TDCOCZDE);
        SCCMPAG.DATA_LEN = 276;
        CEP.TRC(SCCGWA, SCCMPAG.DATA_LEN);
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_BR.rp = new DBParm();
        TDTSMST_BR.rp.TableName = "TDTSMST";
        TDTSMST_BR.rp.where = "AC_NO = :TDRSMST.AC_NO "
            + "AND PRDAC_CD = :TDRSMST.PRDAC_CD";
        TDTSMST_BR.rp.upd = true;
        TDTSMST_BR.rp.order = "ACO_AC";
        IBS.STARTBR(SCCGWA, TDRSMST, this, TDTSMST_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_SMST_FLG = 'Y';
        } else {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_SMST_FLG = 'N';
            } else {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
            }
        }
    }
    public void T000_READNEXT_TDTSMST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRSMST, this, TDTSMST_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_SMST_FLG = 'Y';
        } else {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_SMST_FLG = 'N';
            } else {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
            }
        }
    }
    public void T000_ENDBR_TDTSMST() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTSMST_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_SMST_FLG = 'Y';
        } else {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
        }
    }
    public void T000_READ_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_XX_READ_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.where = "AC_NO = :TDRSMST.AC_NO "
            + "AND PRDAC_CD = :TDRSMST.PRDAC_CD";
        IBS.READ(SCCGWA, TDRSMST, this, TDTSMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_XX_STARTBR_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_BR.rp = new DBParm();
        TDTSMST_BR.rp.TableName = "TDTSMST";
        TDTSMST_BR.rp.where = "AC_NO = :TDRSMST.AC_NO "
            + "AND PRDAC_CD = :TDRSMST.PRDAC_CD "
            + "AND SUBSTR ( STSW , 52 , 1 ) = '1'";
        IBS.STARTBR(SCCGWA, TDRSMST, this, TDTSMST_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_XX_FLG = 'N';
        }
    }
    public void T000_XX_READNEXT_TDTSMST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRSMST, this, TDTSMST_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_XX_FLG = 'N';
        }
    }
    public void T000_XX_ENDBR_TDTSMST() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTSMST_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_XX_FLG = 'N';
        }
    }
    public void T000_READ_TDTIREV() throws IOException,SQLException,Exception {
        TDTIREV_RD = new DBParm();
        TDTIREV_RD.TableName = "TDTIREV";
        TDTIREV_RD.eqWhere = "ACO_AC";
        TDTIREV_RD.where = "STR_DATE <= :TDRIREV.KEY.STR_DATE "
            + "AND END_DATE > :TDRIREV.KEY.STR_DATE";
        IBS.READ(SCCGWA, TDRIREV, this, TDTIREV_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_CIZSAGEN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SVR-CIZSAGEN", CICSAGEN);
        if (CICSAGEN.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICSAGEN.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_CIZQCIAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST-ACR", CICQCIAC);
        if (CICQCIAC.RC.RC_CODE != 0 
            && CICQCIAC.RC.RC_CODE != 8051) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQCIAC.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        } else {
            CICQCIAC.DATA.LAST_FLG = 'Y';
        }
    }
    public void S000_CALL_DDZSCINM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-CI-NAME", DDCSCINM);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, SCCBINF);
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
