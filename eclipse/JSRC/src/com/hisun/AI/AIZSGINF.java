package com.hisun.AI;

import java.util.ArrayList;
import java.util.List;
import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class AIZSGINF {
    AIZSGINF_WS_BR_DATA WS_BR_DATA;
    AICOGINF_DATA DATA;
    brParm AITGINF_BR = new brParm();
    DBParm AITGINF_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_P_INQ_ORG_LOW = "BP-P-INQ-ORG-LOW";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_ERR_MSG = " ";
    int WS_RCD_SEQ = 0;
    int WS_RD_NUM = 0;
    short WS_RECORD_NUM = 0;
    int WS_A = 0;
    int WS_J = 0;
    int WS_CNT = 0;
    int WS_CNT1 = 0;
    int WS_OUT_CNT = 0;
    int WS_LAST_BR = 0;
    short WS_DAYS = 0;
    int WS_SUS_TERM = 0;
    String WS_MIB_AC = " ";
    AIZSGINF_REDEFINES14 REDEFINES14 = new AIZSGINF_REDEFINES14();
    String WS_RVS_NO_X = " ";
    AIZSGINF_REDEFINES20 REDEFINES20 = new AIZSGINF_REDEFINES20();
    AIZSGINF_WS_GINF_INFO WS_GINF_INFO = new AIZSGINF_WS_GINF_INFO();
    AIZSGINF_WS_RVS_INFO WS_RVS_INFO = new AIZSGINF_WS_RVS_INFO();
    int WS_SUS_DT = 0;
    char WS_SUS_FLG = ' ';
    int WS_SUS_DATE = 0;
    List<AIZSGINF_WS_BR_DATA> WS_BR_DATA = new ArrayList<AIZSGINF_WS_BR_DATA>();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AIRGINF AIRGINF = new AIRGINF();
    AICRGINF AICRGINF = new AICRGINF();
    AIRGRVS AIRGRVS = new AIRGRVS();
    AICRGRVS AICRGRVS = new AICRGRVS();
    AIRCRVS AIRCRVS = new AIRCRVS();
    AICRCRVS AICRCRVS = new AICRCRVS();
    AICOGINF AICOGINF = new AICOGINF();
    AICPQMIB AICPQMIB = new AICPQMIB();
    CICCUST CICCUST = new CICCUST();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCPORLO BPCPORLO = new BPCPORLO();
    BPCPQVCH BPCPQVCH = new BPCPQVCH();
    BPCPRGST BPCPRGST = new BPCPRGST();
    AICPCMIB AICPCMIB = new AICPCMIB();
    AIRCMIB AIRCMIB = new AIRCMIB();
    BPCFTLCM BPCFTLCM = new BPCFTLCM();
    int WS_DB_EXP_STDT = 0;
    int WS_DB_EXP_ENDT = 0;
    int WS_DB_SUS_STDT = 0;
    int WS_DB_SUS_ENDT = 0;
    int WS_COUNT_NO = 0;
    char WS_OTH_STS = ' ';
    char WS_GINF_FLG = ' ';
    char WS_END_FLG = ' ';
    SCCGWA SCCGWA;
    AICSGINF AICSGINF;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPORUP_DATA_INFO BPCPORUP;
    public void MP(SCCGWA SCCGWA, AICSGINF AICSGINF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICSGINF = AICSGINF;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZSGINF return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        IBS.init(SCCGWA, AIRGINF);
        IBS.init(SCCGWA, AIRGRVS);
        IBS.init(SCCGWA, AIRCRVS);
        IBS.init(SCCGWA, AICRGINF);
        IBS.init(SCCGWA, AICRGRVS);
        IBS.init(SCCGWA, AICRGRVS);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (AICSGINF.FUNC == 'Q') {
            B020_BROWSE_GINF_PROCESS();
            if (pgmRtn) return;
        } else if (AICSGINF.FUNC == 'B') {
            B060_BROWSE_RVS_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_GET_SUB_BR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.LVL);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_BANK);
        CEP.TRC(SCCGWA, AICSGINF.BR);
        CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.BR);
        IBS.init(SCCGWA, BPCPORLO);
        BPCPORLO.BNK = SCCGWA.COMM_AREA.TR_BANK;
        if (AICSGINF.BR != 0) {
            BPCPORLO.BR = AICSGINF.BR;
            S000_CALL_BPZPORLO();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPORLO.SUB_NUM);
            CEP.TRC(SCCGWA, WS_J);
            WS_J = WS_J + 1;
            WS_BR_DATA = WS_BR_DATA.get(WS_J-1);
            WS_BR_DATA.WS_TMP_BR = AICSGINF.BR;
            WS_BR_DATA.set(WS_J-1, WS_BR_DATA);
            CEP.TRC(SCCGWA, WS_J);
            CEP.TRC(SCCGWA, WS_BR_DATA.get(WS_J-1).WS_TMP_BR);
            if (BPCPORLO.SUB_NUM != 0) {
                for (WS_CNT = 1; WS_CNT <= BPCPORLO.SUB_NUM; WS_CNT += 1) {
                    WS_J = WS_J + 1;
                    CEP.TRC(SCCGWA, WS_CNT);
                    CEP.TRC(SCCGWA, WS_J);
                    WS_BR_DATA = WS_BR_DATA.get(WS_J-1);
                    WS_BR_DATA.WS_TMP_BR = BPCPORLO.SUB_BR_DATA[WS_CNT-1].SUB_BR;
                    WS_BR_DATA.set(WS_J-1, WS_BR_DATA);
                    CEP.TRC(SCCGWA, BPCPORLO.SUB_BR_DATA[WS_CNT-1].SUB_BR);
                    CEP.TRC(SCCGWA, WS_BR_DATA.get(WS_J-1).WS_TMP_BR);
                }
            }
            CEP.TRC(SCCGWA, BPCPORLO.NEXT_CALL_FLG);
            CEP.TRC(SCCGWA, BPCPORLO.LAST_BR);
            if (BPCPORLO.NEXT_CALL_FLG == 'Y') {
                WS_LAST_BR = BPCPORLO.LAST_BR;
                IBS.init(SCCGWA, BPCPORLO);
                BPCPORLO.BNK = SCCGWA.COMM_AREA.TR_BANK;
                BPCPORLO.BR = AICSGINF.BR;
                BPCPORLO.LAST_BR = WS_LAST_BR;
                S000_CALL_BPZPORLO();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCPORLO.SUB_NUM);
                if (BPCPORLO.SUB_NUM != 0) {
                    for (WS_CNT1 = 1; WS_CNT1 <= BPCPORLO.SUB_NUM; WS_CNT1 += 1) {
                        WS_J = WS_J + 1;
                        WS_BR_DATA = WS_BR_DATA.get(WS_J-1);
                        WS_BR_DATA.WS_TMP_BR = BPCPORLO.SUB_BR_DATA[WS_CNT1-1].SUB_BR;
                        WS_BR_DATA.set(WS_J-1, WS_BR_DATA);
                        CEP.TRC(SCCGWA, WS_CNT1);
                        CEP.TRC(SCCGWA, WS_J);
                        CEP.TRC(SCCGWA, BPCPORLO.SUB_BR_DATA[WS_CNT1-1].SUB_BR);
                        CEP.TRC(SCCGWA, WS_BR_DATA.get(WS_J-1).WS_TMP_BR);
                    }
                }
            }
        }
    }
    public void B020_BROWSE_GINF_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        CEP.TRC(SCCGWA, AICSGINF.STS);
        CEP.TRC(SCCGWA, WS_J);
        if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
            IBS.init(SCCGWA, SCCMPAG);
            SCCMPAG.FUNC = 'S';
            SCCMPAG.TITL = " ";
            SCCMPAG.SUBT_ROW_CNT = 0;
            SCCMPAG.MAX_COL_NO = 179;
            SCCMPAG.SCR_ROW_CNT = 13;
            SCCMPAG.SCR_COL_CNT = 10;
            B_MPAG();
            if (pgmRtn) return;
            if (AICSGINF.SUS_TERM != 1 
                && AICSGINF.SUS_TERM != 0) {
                WS_SUS_TERM = 1 - AICSGINF.SUS_TERM;
                CEP.TRC(SCCGWA, WS_SUS_TERM);
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DAYS = WS_SUS_TERM;
                SCCCLDT.DATE1 = SCCGWA.COMM_AREA.AC_DATE;
                S000_CALL_SCSSCLDT();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, SCCCLDT.DATE2);
                WS_SUS_DATE = SCCCLDT.DATE2;
            } else {
                if (AICSGINF.SUS_TERM == 1) {
                    WS_SUS_DATE = SCCGWA.COMM_AREA.AC_DATE;
                } else {
                    WS_SUS_DATE = 0;
                }
            }
            if (AICSGINF.RVS_NO.trim().length() == 0) {
                IBS.init(SCCGWA, AIRGINF);
                AIRGINF.BR = AICSGINF.BR;
                AIRGINF.ITM = AICSGINF.ITM;
                AIRGINF.SEQ = AICSGINF.SEQ;
                AIRGINF.CCY = AICSGINF.CCY;
                WS_DB_EXP_STDT = AICSGINF.EXP_STDT;
                WS_DB_EXP_ENDT = AICSGINF.EXP_ENDT;
                WS_DB_SUS_STDT = AICSGINF.SUS_STDT;
                WS_DB_SUS_ENDT = AICSGINF.SUS_ENDT;
                if (AICSGINF.STS == 'N') {
                    AIRGINF.STS = AICSGINF.STS;
                    WS_OTH_STS = 'P';
                } else {
                    AIRGINF.STS = AICSGINF.STS;
                }
                if (AIRGINF.ITM.trim().length() > 0 
                    && AIRGINF.SEQ == 0 
                    && AIRGINF.CCY.trim().length() == 0) {
                    if (AICSGINF.STS == 'N') {
                        T000_STARTBR_GINF_BY_ITM_1();
                        if (pgmRtn) return;
                    } else {
                        T000_STARTBR_GINF_BY_ITM_2();
                        if (pgmRtn) return;
                    }
                }
                if (AIRGINF.ITM.trim().length() > 0 
                    && AIRGINF.SEQ != 0 
                    && AIRGINF.CCY.trim().length() == 0) {
                    if (AICSGINF.STS == 'N') {
                        T000_STARTBR_GINF_BY_ITM_S_1();
                        if (pgmRtn) return;
                    } else {
                        T000_STARTBR_GINF_BY_ITM_S_2();
                        if (pgmRtn) return;
                    }
                }
                if (AIRGINF.CCY.trim().length() > 0 
                    && AIRGINF.SEQ == 0 
                    && AIRGINF.ITM.trim().length() == 0) {
                    if (AICSGINF.STS == 'N') {
                        T000_STARTBR_GINF_BY_CCY_1();
                        if (pgmRtn) return;
                    } else {
                        T000_STARTBR_GINF_BY_CCY_2();
                        if (pgmRtn) return;
                    }
                }
                if (AIRGINF.CCY.trim().length() > 0 
                    && AIRGINF.SEQ == 0 
                    && AIRGINF.ITM.trim().length() > 0) {
                    if (AICSGINF.STS == 'N') {
                        T000_STARTBR_AITGINF_NO_RVS_1();
                        if (pgmRtn) return;
                    } else {
                        T000_STARTBR_AITGINF_NO_RVS_2();
                        if (pgmRtn) return;
                    }
                }
                if (AIRGINF.CCY.trim().length() > 0 
                    && AIRGINF.SEQ != 0 
                    && AIRGINF.ITM.trim().length() > 0) {
                    if (AICSGINF.STS == 'N') {
                        T000_STARTBR_AITGINF_SEQ_1();
                        if (pgmRtn) return;
                    } else {
                        T000_STARTBR_AITGINF_SEQ_2();
                        if (pgmRtn) return;
                    }
                }
                if (AIRGINF.CCY.trim().length() == 0 
                    && AIRGINF.SEQ == 0 
                    && AIRGINF.ITM.trim().length() == 0) {
                    if (AICSGINF.STS == 'N') {
                        T000_STARTBR_AITGINF_1();
                        if (pgmRtn) return;
                    } else {
                        T000_STARTBR_AITGINF_2();
                        if (pgmRtn) return;
                    }
                }
                T000_READNEXT_AITGINF();
                if (pgmRtn) return;
                while (WS_GINF_FLG != 'N' 
                    && SCCMPAG.FUNC != 'E' 
                    && WS_END_FLG != 'Y') {
                    CEP.TRC(SCCGWA, "CHECK RVS EXP");
                    CEP.TRC(SCCGWA, AIRGINF.RVS_EXP);
                    S000_CHE_INPUT_FOR_OUTPUT();
                    if (pgmRtn) return;
                    if (WS_SUS_FLG == 'Y') {
                        B020_02_OUT_BRW_DATA();
                        if (pgmRtn) return;
                    }
                    T000_READNEXT_AITGINF();
                    if (pgmRtn) return;
                }
                T000_ENDBR_AITGINF();
                if (pgmRtn) return;
            } else {
                IBS.init(SCCGWA, AIRGINF);
                IBS.init(SCCGWA, AICRGINF);
                AICRGINF.INFO.FUNC = 'Q';
                AIRGINF.KEY.RVS_NO = AICSGINF.RVS_NO;
                CEP.TRC(SCCGWA, AIRGINF.KEY.RVS_NO);
                S000_CALL_AIZRGINF();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, AICRGINF.RETURN_INFO);
                if (AICRGINF.RETURN_INFO == 'N') {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_GINF_NOT_EXIST;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, AICRGINF.RETURN_INFO);
                CEP.TRC(SCCGWA, WS_SUS_FLG);
                R000_CHECK_BR();
                if (pgmRtn) return;
                B020_02_OUT_BRW_DATA();
                if (pgmRtn) return;
            }
        } else {
            IBS.init(SCCGWA, AIRGINF);
            AIRGINF.BR = AICSGINF.BR;
            REDEFINES14.WS_MIB_BR = AICSGINF.BR;
            WS_MIB_AC = IBS.CLS2CPY(SCCGWA, REDEFINES14);
            AIRGINF.ITM = AICSGINF.ITM;
            REDEFINES14.WS_ITM = AICSGINF.ITM;
            WS_MIB_AC = IBS.CLS2CPY(SCCGWA, REDEFINES14);
            AIRGINF.SEQ = AICSGINF.SEQ;
            REDEFINES14.WS_SEQ = AICSGINF.SEQ;
            WS_MIB_AC = IBS.CLS2CPY(SCCGWA, REDEFINES14);
            AIRGINF.CCY = AICSGINF.CCY;
            REDEFINES14.WS_CCY = AICSGINF.CCY;
            WS_MIB_AC = IBS.CLS2CPY(SCCGWA, REDEFINES14);
            AIRGINF.AC = WS_MIB_AC;
            AIRGINF.KEY.RVS_NO = AICSGINF.RVS_NO;
            AIRGINF.STS = AICSGINF.STS;
            AICRGINF.INFO.EXP_STDT = AICSGINF.EXP_STDT;
            AICRGINF.INFO.EXP_ENDT = AICSGINF.EXP_ENDT;
            AICRGINF.INFO.SUS_STDT = AICSGINF.SUS_STDT;
            AICRGINF.INFO.SUS_ENDT = AICSGINF.SUS_ENDT;
            CEP.TRC(SCCGWA, REDEFINES14.WS_MIB_BR);
            CEP.TRC(SCCGWA, REDEFINES14.WS_ITM);
            CEP.TRC(SCCGWA, REDEFINES14.WS_SEQ);
            CEP.TRC(SCCGWA, REDEFINES14.WS_CCY);
            CEP.TRC(SCCGWA, WS_MIB_AC);
            CEP.TRC(SCCGWA, AIRGINF.BR);
            CEP.TRC(SCCGWA, AIRGINF.ITM);
            CEP.TRC(SCCGWA, AIRGINF.SEQ);
            CEP.TRC(SCCGWA, AIRGINF.CCY);
            CEP.TRC(SCCGWA, AIRGINF.KEY.RVS_NO);
            CEP.TRC(SCCGWA, AIRGINF.STS);
            CEP.TRC(SCCGWA, AICRGINF.INFO.EXP_STDT);
            CEP.TRC(SCCGWA, AICRGINF.INFO.EXP_ENDT);
            CEP.TRC(SCCGWA, AICRGINF.INFO.SUS_STDT);
            CEP.TRC(SCCGWA, AICRGINF.INFO.SUS_ENDT);
            CEP.TRC(SCCGWA, "HELLO");
            if (AICSGINF.RVS_NO.trim().length() > 0) {
                T000_STARTBR_AITGINF_OUT_1();
                if (pgmRtn) return;
            } else {
                if (AICSGINF.BR == 0 
                    || AICSGINF.ITM.trim().length() == 0 
                    || AICSGINF.SEQ == 0 
                    || AICSGINF.CCY.trim().length() == 0) {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_IA_NO_MUST_IPT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                T000_STARTBR_AITGINF_OUT();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
            B020_03_OUT_PAGE_TITLE();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, AIRGINF);
            WS_RD_NUM = 0;
            CEP.TRC(SCCGWA, WS_RD_NUM);
            CEP.TRC(SCCGWA, AICSGINF.PAGE_ROW);
            while (WS_RD_NUM < AICSGINF.PAGE_ROW 
                && WS_GINF_FLG != 'N') {
                WS_RD_NUM = WS_RD_NUM + 1;
                CEP.TRC(SCCGWA, WS_RD_NUM);
                S000_CHE_INPUT_FOR_OUTPUT();
                if (pgmRtn) return;
                if (WS_SUS_FLG == 'Y') {
                    B020_04_OUT_PAGE_DATA();
                    if (pgmRtn) return;
                }
                T000_READNEXT_AITGINF();
                if (pgmRtn) return;
            }
            T000_ENDBR_AITGINF();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.OUTP_LEN);
            AICOGINF.OUTPUT_TITLE.CURR_PAGE_ROW = WS_RD_NUM;
            DATA = new AICOGINF_DATA();
            AICOGINF.DATA.add(DATA);
            if (AICSGINF.PAGE_NUM == 0) {
                AICOGINF.OUTPUT_TITLE.CURR_PAGE = 1;
            } else {
                AICOGINF.OUTPUT_TITLE.CURR_PAGE = AICSGINF.PAGE_NUM;
            }
            SCCFMT.FMTID = "AI520";
            SCCFMT.DATA_PTR = AICOGINF;
            SCCFMT.DATA_LEN = 8972;
            IBS.FMT(SCCGWA, SCCFMT);
        }
    }
    public void S000_CALL_BPZPORLO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG_LOW, BPCPORLO);
        if (BPCPORLO.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPORLO.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CHE_INPUT_FOR_OUTPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "HELLO");
        WS_SUS_FLG = 'N';
        CEP.TRC(SCCGWA, AIRGINF.LST_G_DT);
        CEP.TRC(SCCGWA, AIRGINF.LST_G_DT);
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = AIRGINF.LST_G_DT;
        SCCCLDT.DATE2 = SCCGWA.COMM_AREA.AC_DATE;
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        WS_DAYS = (short) SCCCLDT.DAYS;
        CEP.TRC(SCCGWA, SCCCLDT.DAYS);
        CEP.TRC(SCCGWA, WS_DAYS);
        WS_SUS_DT = WS_DAYS + 1;
        CEP.TRC(SCCGWA, WS_SUS_DT);
        CEP.TRC(SCCGWA, AICSGINF.SUS_TERM);
        CEP.TRC(SCCGWA, AICSGINF.SUS_STDT);
        CEP.TRC(SCCGWA, AICSGINF.SUS_ENDT);
        CEP.TRC(SCCGWA, AICSGINF.EXP_STDT);
        CEP.TRC(SCCGWA, AICSGINF.EXP_ENDT);
        CEP.TRC(SCCGWA, WS_SUS_DATE);
        CEP.TRC(SCCGWA, AICSGINF.SUS_TERM);
        if (WS_SUS_DT > AICSGINF.SUS_TERM 
            || WS_SUS_DT == AICSGINF.SUS_TERM) {
            WS_SUS_FLG = 'Y';
        }
        CEP.TRC(SCCGWA, WS_SUS_FLG);
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (AICSGINF.FUNC == 'Q') {
            if (AICSGINF.BR != 0) {
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BR = AICSGINF.BR;
                S000_CALL_BPZPQORG();
                if (pgmRtn) return;
            }
            if (AICSGINF.CCY.equalsIgnoreCase("999")) {
                AICSGINF.CCY = "   ";
            }
            CEP.TRC(SCCGWA, AICSGINF.SUS_STDT);
            CEP.TRC(SCCGWA, AICSGINF.SUS_ENDT);
            if (AICSGINF.SUS_STDT == 0) {
                AICSGINF.SUS_STDT = 0;
                if (AICSGINF.SUS_ENDT == 0) {
                    AICSGINF.SUS_ENDT = 99991231;
                }
            } else {
                if (AICSGINF.SUS_ENDT == 0) {
                    AICSGINF.SUS_ENDT = 99991231;
                }
            }
            CEP.TRC(SCCGWA, "AFTER SUS DATE CHECK");
            CEP.TRC(SCCGWA, AICSGINF.SUS_STDT);
            CEP.TRC(SCCGWA, AICSGINF.SUS_ENDT);
            CEP.TRC(SCCGWA, AICSGINF.EXP_STDT);
            CEP.TRC(SCCGWA, AICSGINF.EXP_ENDT);
            if (AICSGINF.EXP_STDT == 0) {
                AICSGINF.EXP_STDT = 0;
                if (AICSGINF.EXP_ENDT == 0) {
                    AICSGINF.EXP_ENDT = 99991231;
                }
            } else {
                if (AICSGINF.EXP_ENDT == 0) {
                    AICSGINF.EXP_ENDT = 99991231;
                }
            }
            CEP.TRC(SCCGWA, "AFTER EXP DATE CHECK");
            CEP.TRC(SCCGWA, AICSGINF.EXP_STDT);
            CEP.TRC(SCCGWA, AICSGINF.EXP_ENDT);
            CEP.TRC(SCCGWA, "11");
            CEP.TRC(SCCGWA, "22");
            if (AICSGINF.PAGE_ROW == 0) {
                AICSGINF.PAGE_ROW = 25;
            }
        }
        if (AICSGINF.FUNC == 'B') {
            if (AICSGINF.RVS_NO.trim().length() == 0) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_INPUT_ERROR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (AICSGINF.EXP_STDT == 0) {
            AICSGINF.EXP_STDT = 0;
            if (AICSGINF.EXP_ENDT == 0) {
                AICSGINF.EXP_ENDT = 99991231;
            }
        } else {
            if (AICSGINF.EXP_ENDT == 0) {
                AICSGINF.EXP_ENDT = AICSGINF.EXP_STDT;
            }
        }
        CEP.TRC(SCCGWA, "44");
    }
    public void B060_BROWSE_RVS_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 852;
        SCCMPAG.SCR_ROW_CNT = 13;
        SCCMPAG.SCR_COL_CNT = 10;
        B_MPAG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, AIRGRVS);
        IBS.init(SCCGWA, AICRGRVS);
        AICRGRVS.INFO.FUNC = 'B';
        AICRGRVS.INFO.OPT = '6';
        AIRGRVS.KEY.RVS_NO = AICSGINF.RVS_NO;
        S000_CALL_AIZRGRVS();
        if (pgmRtn) return;
        AICRGRVS.INFO.OPT = 'N';
        S000_CALL_AIZRGRVS();
        if (pgmRtn) return;
        while (AICRGRVS.RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E' 
            && WS_END_FLG != 'Y') {
            IBS.init(SCCGWA, WS_RVS_INFO);
            WS_RVS_INFO.WS_O_FLG = '1';
            WS_RVS_INFO.WS_O_TX_DT = AIRGRVS.TX_DT;
            WS_RVS_INFO.WS_O_BR = AIRGRVS.BR;
            WS_RVS_INFO.WS_O_RVS_NO = AIRGRVS.KEY.RVS_NO;
            WS_RVS_INFO.WS_O_RVS_SEQ = AIRGRVS.KEY.RVS_SEQ;
            WS_RVS_INFO.WS_O_AC = AIRGRVS.AC;
            WS_RVS_INFO.WS_O_CCY = AIRGRVS.CCY;
            WS_RVS_INFO.WS_O_SIGN = AIRGRVS.SIGN;
            WS_RVS_INFO.WS_O_AMT = AIRGRVS.AMT;
            WS_RVS_INFO.WS_O_STS = AIRGRVS.STS;
            WS_RVS_INFO.WS_O_PAY_MAN = AIRGRVS.PAY_MAN;
            WS_RVS_INFO.WS_O_PAY_BR = AIRGRVS.PAY_BR;
            WS_RVS_INFO.WS_O_THEIR_AC = AIRGRVS.THEIR_AC;
            WS_RVS_INFO.WS_O_CI_NO = AIRGRVS.CI_NO;
            WS_RVS_INFO.WS_O_TR_BR = AIRGRVS.TR_BR;
            WS_RVS_INFO.WS_O_TR_TELLER = AIRGRVS.TR_TELLER;
            WS_RVS_INFO.WS_O_SET_NO = AIRGRVS.SET_NO;
            WS_RVS_INFO.WS_O_SET_SEQ = AIRGRVS.SET_SEQ;
            WS_RVS_INFO.WS_O_REF_NO = AIRGRVS.REF_NO;
            WS_RVS_INFO.WS_O_PART = AIRGRVS.PART;
            WS_RVS_INFO.WS_O_BILL_NO = " ";
            CEP.TRC(SCCGWA, AIRGRVS.KEY.RVS_NO);
            CEP.TRC(SCCGWA, AIRGRVS.TX_DT);
            CEP.TRC(SCCGWA, AIRGRVS.BR);
            CEP.TRC(SCCGWA, AIRGRVS.KEY.RVS_SEQ);
            CEP.TRC(SCCGWA, AIRGRVS.AC);
            CEP.TRC(SCCGWA, AIRGRVS.CCY);
            CEP.TRC(SCCGWA, AIRGRVS.AMT);
            CEP.TRC(SCCGWA, AIRGRVS.TR_BR);
            CEP.TRC(SCCGWA, AIRGRVS.TR_TELLER);
            CEP.TRC(SCCGWA, AIRGRVS.CI_NO);
            CEP.TRC(SCCGWA, AIRGRVS.SET_NO);
            CEP.TRC(SCCGWA, AIRGRVS.SET_SEQ);
            CEP.TRC(SCCGWA, AIRGRVS.REF_NO);
            CEP.TRC(SCCGWA, AIRGRVS.PART);
            if ((AIRGRVS.CI_NO.trim().length() == 0)) {
                WS_RVS_INFO.WS_O_CI_ENMS = " ";
            } else {
                IBS.init(SCCGWA, CICCUST);
                CICCUST.DATA.CI_NO = AIRGRVS.CI_NO;
                CICCUST.FUNC = 'C';
                S000_CALL_CIZCUST();
                if (pgmRtn) return;
                WS_RVS_INFO.WS_O_CI_ENMS = CICCUST.O_DATA.O_CI_NM;
            }
            CEP.TRC(SCCGWA, WS_RVS_INFO.WS_O_CI_NO);
            CEP.TRC(SCCGWA, WS_RVS_INFO.WS_O_CI_ENMS);
            CEP.TRC(SCCGWA, WS_RVS_INFO.WS_O_TR_BR);
            CEP.TRC(SCCGWA, WS_RVS_INFO.WS_O_TR_TELLER);
            CEP.TRC(SCCGWA, WS_RVS_INFO.WS_O_SET_NO);
            R000_OUTPUT_RVS_INFO();
            if (pgmRtn) return;
            AICRGRVS.INFO.OPT = 'N';
            S000_CALL_AIZRGRVS();
            if (pgmRtn) return;
        }
        AICRGRVS.INFO.OPT = 'E';
        S000_CALL_AIZRGRVS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, AIRCRVS);
        IBS.init(SCCGWA, AICRCRVS);
        AICRCRVS.INFO.FUNC = 'B';
        AICRCRVS.INFO.OPT = '5';
        AIRCRVS.KEY.RVS_NO = AICSGINF.RVS_NO;
        CEP.TRC(SCCGWA, AICSGINF.RVS_NO);
        CEP.TRC(SCCGWA, AIRCRVS.KEY.RVS_NO);
        S000_CALL_AIZRCRVS();
        if (pgmRtn) return;
        AICRCRVS.INFO.OPT = 'N';
        S000_CALL_AIZRCRVS();
        if (pgmRtn) return;
        while (AICRCRVS.RETURN_INFO != 'N') {
            R00_QUERY_CMIB();
            if (pgmRtn) return;
            IBS.init(SCCGWA, WS_RVS_INFO);
            WS_RVS_INFO.WS_O_FLG = '2';
            CEP.TRC(SCCGWA, BPCPQVCH.DATA.SIGN);
            if (AIRCMIB.RVS_TYP == 'C') {
                WS_RVS_INFO.WS_O_SIGN = 'D';
            } else {
                WS_RVS_INFO.WS_O_SIGN = 'C';
            }
            WS_RVS_INFO.WS_O_TX_DT = AIRCRVS.TX_DT;
            WS_RVS_INFO.WS_O_BR = AIRCRVS.BR;
            WS_RVS_INFO.WS_O_RVS_NO = AIRCRVS.KEY.RVS_NO;
            WS_RVS_INFO.WS_O_RVS_SEQ = AIRCRVS.KEY.RVS_SEQ;
            WS_RVS_INFO.WS_O_AC = AIRCRVS.AC;
            WS_RVS_INFO.WS_O_CCY = AIRCRVS.CCY;
            WS_RVS_INFO.WS_O_AMT = AIRCRVS.AMT;
            WS_RVS_INFO.WS_O_STS = AIRCRVS.STS;
            WS_RVS_INFO.WS_O_PAY_MAN = AIRCRVS.PAY_MAN;
            WS_RVS_INFO.WS_O_PAY_BR = AIRCRVS.PAY_BR;
            WS_RVS_INFO.WS_O_THEIR_AC = AIRCRVS.THEIR_AC;
            WS_RVS_INFO.WS_O_CI_NO = AIRCRVS.CI_NO;
            WS_RVS_INFO.WS_O_TR_BR = AIRCRVS.TR_BR;
            WS_RVS_INFO.WS_O_TR_TELLER = AIRCRVS.TR_TELLER;
            WS_RVS_INFO.WS_O_SET_NO = AIRCRVS.SET_NO;
            WS_RVS_INFO.WS_O_SET_SEQ = AIRCRVS.SET_SEQ;
            WS_RVS_INFO.WS_O_PART = AIRCRVS.PART;
            WS_RVS_INFO.WS_O_BILL_NO = " ";
            CEP.TRC(SCCGWA, AIRCRVS.TX_DT);
            CEP.TRC(SCCGWA, AIRCRVS.BR);
            CEP.TRC(SCCGWA, AIRCRVS.AC);
            CEP.TRC(SCCGWA, AIRCRVS.CCY);
            CEP.TRC(SCCGWA, AIRCRVS.AMT);
            CEP.TRC(SCCGWA, WS_RVS_INFO.WS_O_SIGN);
            if ((AIRCRVS.CI_NO.trim().length() == 0)) {
                WS_RVS_INFO.WS_O_CI_ENMS = " ";
            } else {
                IBS.init(SCCGWA, CICCUST);
                CICCUST.DATA.CI_NO = AIRCRVS.CI_NO;
                CICCUST.FUNC = 'C';
                S000_CALL_CIZCUST();
                if (pgmRtn) return;
                WS_RVS_INFO.WS_O_CI_ENMS = CICCUST.O_DATA.O_CI_NM;
            }
            R000_OUTPUT_RVS_INFO();
            if (pgmRtn) return;
            AICRCRVS.INFO.OPT = 'N';
            S000_CALL_AIZRCRVS();
            if (pgmRtn) return;
        }
        AICRCRVS.INFO.OPT = 'E';
        S000_CALL_AIZRCRVS();
        if (pgmRtn) return;
    }
    public void B020_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 179;
        SCCMPAG.SCR_ROW_CNT = 13;
        SCCMPAG.SCR_COL_CNT = 10;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B020_03_OUT_PAGE_TITLE() throws IOException,SQLException,Exception {
        if (AICSGINF.PAGE_NUM == 0) {
            if (AICSGINF.RVS_NO.trim().length() > 0) {
                T000_COUNT_AITGINF_RVS();
                if (pgmRtn) return;
                if (WS_COUNT_NO == 0) {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_RVS_NOT_EXIST;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            } else {
                T000_COUNT_AITGINF();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, WS_COUNT_NO);
            AICOGINF.TOTAL_DATA.TOTAL_NUM = WS_COUNT_NO;
            CEP.TRC(SCCGWA, "WLL11");
            WS_RECORD_NUM = (short) (AICOGINF.TOTAL_DATA.TOTAL_NUM % AICSGINF.PAGE_ROW);
            AICOGINF.TOTAL_DATA.TOTAL_PAGE = (int) ((AICOGINF.TOTAL_DATA.TOTAL_NUM - WS_RECORD_NUM) / AICSGINF.PAGE_ROW);
            CEP.TRC(SCCGWA, "WLL66");
            CEP.TRC(SCCGWA, AICSGINF.PAGE_ROW);
            CEP.TRC(SCCGWA, "WLL88");
            CEP.TRC(SCCGWA, AICOGINF.TOTAL_DATA.TOTAL_NUM);
            CEP.TRC(SCCGWA, AICOGINF.TOTAL_DATA.TOTAL_PAGE);
            if (WS_RECORD_NUM > 0) {
                AICOGINF.TOTAL_DATA.TOTAL_PAGE = AICOGINF.TOTAL_DATA.TOTAL_PAGE + 1;
            }
        }
        IBS.init(SCCGWA, AICOGINF.OUTPUT_TITLE);
        AICOGINF.OUTPUT_TITLE.LAST_FLG = 'N';
        if (AICOGINF.TOTAL_DATA.TOTAL_PAGE == AICSGINF.PAGE_NUM) {
            AICOGINF.OUTPUT_TITLE.LAST_FLG = 'Y';
        }
        if (AICSGINF.PAGE_NUM > 0) {
            AICSGINF.RCD_SEQ = ( AICSGINF.PAGE_NUM - 1 ) * AICSGINF.PAGE_ROW + 1;
        } else {
            AICSGINF.RCD_SEQ = 1;
        }
        CEP.TRC(SCCGWA, AICSGINF.RCD_SEQ);
        AICRGINF.RCD_SEQ = AICSGINF.RCD_SEQ;
        T000_READNEXT_AITGINF_FIRST();
        if (pgmRtn) return;
    }
    public void B020_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "HELLO1");
        WS_GINF_INFO.WS_RVS_NO = AIRGINF.KEY.RVS_NO;
        if (!AIRGINF.AC.equalsIgnoreCase(WS_GINF_INFO.WS_AC)) {
            WS_GINF_INFO.WS_AC = AIRGINF.AC;
            IBS.init(SCCGWA, AICPQMIB);
            AICPQMIB.INPUT_DATA.AC = AIRGINF.AC;
            S000_CALL_AIZPQMIB();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, AIRGINF.AC);
        WS_GINF_INFO.WS_RVS_TYP = AICPQMIB.OUTPUT_DATA.RVS_TYP;
        WS_GINF_INFO.WS_RVS_KND = AIRGINF.RVS_KND;
        CEP.TRC(SCCGWA, AIRGINF.RVS_KND);
        CEP.TRC(SCCGWA, AIRGINF.RVS_EXP);
        if (AIRGINF.RVS_EXP == 0 
            || AIRGINF.RVS_EXP == 0) {
            WS_GINF_INFO.WS_RVS_EXP = 99991231;
        } else {
            WS_GINF_INFO.WS_RVS_EXP = AIRGINF.RVS_EXP;
        }
        WS_GINF_INFO.WS_BR = AIRGINF.BR;
        WS_GINF_INFO.WS_G_MAX_NO = AIRGINF.G_MAX_NO;
        WS_GINF_INFO.WS_C_MAX_NO = AIRGINF.C_MAX_NO;
        WS_GINF_INFO.WS_G_AMT = AIRGINF.G_AMT;
        WS_GINF_INFO.WS_C_AMT = AIRGINF.C_AMT;
        WS_GINF_INFO.WS_G_BAL = AIRGINF.G_BAL;
        WS_GINF_INFO.WS_FST_G_DT = AIRGINF.FST_G_DT;
        WS_GINF_INFO.WS_LST_G_DT = AIRGINF.LST_G_DT;
        WS_GINF_INFO.WS_FST_C_DT = AIRGINF.FST_C_DT;
        WS_GINF_INFO.WS_LST_C_DT = AIRGINF.LST_C_DT;
        WS_GINF_INFO.WS_LST_TR_DT = AIRGINF.LST_TR_DT;
        WS_GINF_INFO.WS_STS = AIRGINF.STS;
        CEP.TRC(SCCGWA, WS_GINF_INFO.WS_RVS_TYP);
        CEP.TRC(SCCGWA, WS_GINF_INFO.WS_RVS_KND);
        CEP.TRC(SCCGWA, AIRGINF.G_MAX_NO);
        CEP.TRC(SCCGWA, WS_GINF_INFO.WS_G_MAX_NO);
        CEP.TRC(SCCGWA, AIRGINF.C_MAX_NO);
        CEP.TRC(SCCGWA, WS_GINF_INFO.WS_C_MAX_NO);
        WS_OUT_CNT += 1;
        CEP.TRC(SCCGWA, WS_END_FLG);
        CEP.TRC(SCCGWA, WS_OUT_CNT);
        if (WS_OUT_CNT > 500) {
            WS_END_FLG = 'Y';
        }
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_GINF_INFO);
        SCCMPAG.DATA_LEN = 179;
        B_MPAG();
        if (pgmRtn) return;
        WS_SUS_FLG = ' ';
    }
    public void B020_04_OUT_PAGE_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_GINF_INFO);
        IBS.init(SCCGWA, AICPQMIB);
        AICPQMIB.INPUT_DATA.AC = AIRGINF.AC;
        S000_CALL_AIZPQMIB();
        if (pgmRtn) return;
        DATA.RVS_TYP = AICPQMIB.OUTPUT_DATA.RVS_TYP;
        DATA.RVS_KND = AIRGINF.RVS_KND;
        DATA.RVS_EXP = AIRGINF.RVS_EXP;
        DATA.BR = AIRGINF.BR;
        DATA.G_MAX_NO = AIRGINF.G_MAX_NO;
        DATA.C_MAX_NO = AIRGINF.C_MAX_NO;
        DATA.G_AMT = AIRGINF.G_AMT;
        DATA.C_AMT = AIRGINF.C_AMT;
        DATA.G_BAL = AIRGINF.G_BAL;
        DATA.FST_G_DT = AIRGINF.FST_G_DT;
        DATA.LST_G_DT = AIRGINF.LST_G_DT;
        DATA.FST_C_DT = AIRGINF.FST_C_DT;
        DATA.LST_C_DT = AIRGINF.LST_C_DT;
        DATA.LST_TR_DT = AIRGINF.LST_TR_DT;
        DATA.STS = AIRGINF.STS;
    }
    public void R000_CHECK_BR() throws IOException,SQLException,Exception {
        REDEFINES20.WS_RVS_BR = AIRGINF.BR;
        WS_RVS_NO_X = IBS.CLS2CPY(SCCGWA, REDEFINES20);
        IBS.init(SCCGWA, BPCFTLCM);
        BPCFTLCM.BR = REDEFINES20.WS_RVS_BR;
        BPCFTLCM.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLCM();
        if (pgmRtn) return;
        if (BPCFTLCM.AUTH_FLG == 'N') {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_BR_NO_GRANT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R00_QUERY_CMIB() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRCMIB);
        IBS.init(SCCGWA, AICPCMIB);
        AIRCMIB.KEY.GL_BOOK = AIRCRVS.BOOK_FLG;
        AIRCMIB.KEY.BR = AIRCRVS.BR;
        AIRCMIB.KEY.ITM = AIRCRVS.ITM;
        AIRCMIB.KEY.SEQ = AIRCRVS.SEQ;
        AICPCMIB.POINTER = AIRCMIB;
        AICPCMIB.REC_LEN = 407;
        S000_CALL_AIZPCMIB();
        if (pgmRtn) return;
        if (AICPCMIB.RETURN_INFO == 'N') {
            IBS.init(SCCGWA, AIRCMIB);
            IBS.init(SCCGWA, AICPCMIB);
            AIRCMIB.KEY.GL_BOOK = AIRCRVS.BOOK_FLG;
            AIRCMIB.KEY.BR = AIRCRVS.BR;
            AIRCMIB.KEY.ITM = AIRCRVS.ITM;
            AIRCMIB.KEY.SEQ = 999999;
            AICPCMIB.POINTER = AIRCMIB;
            AICPCMIB.REC_LEN = 407;
            S000_CALL_AIZPCMIB();
            if (pgmRtn) return;
            if (AICPCMIB.RETURN_INFO == 'N') {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_READ_AITCMIB_NOTFND);
            }
        }
    }
    public void S000_CALL_AIZPCMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-GET-CMIB", AICPCMIB);
        CEP.TRC(SCCGWA, AICPCMIB.RC);
        if (AICPCMIB.RETURN_INFO != 'N') {
            if (AICPCMIB.RC.RC_CODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPCMIB.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_STARTBR_AITGINF_NO_RVS_1() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BROWER");
        CEP.TRC(SCCGWA, AIRGINF.STS);
        CEP.TRC(SCCGWA, AIRGINF.KEY.RVS_NO);
        CEP.TRC(SCCGWA, AIRGINF.BR);
        CEP.TRC(SCCGWA, AIRGINF.ITM);
        CEP.TRC(SCCGWA, AIRGINF.SEQ);
        CEP.TRC(SCCGWA, AIRGINF.STS);
        CEP.TRC(SCCGWA, WS_OTH_STS);
        AITGINF_BR.rp = new DBParm();
        AITGINF_BR.rp.TableName = "AITGINF";
        AITGINF_BR.rp.where = "BR = :AIRGINF.BR "
            + "AND ITM = :AIRGINF.ITM "
            + "AND CCY = :AIRGINF.CCY "
            + "AND STS IN ( :AIRGINF.STS , :WS_OTH_STS ) "
            + "AND ( RVS_EXP BETWEEN :WS_DB_EXP_STDT "
            + "AND :WS_DB_EXP_ENDT ) "
            + "AND ( FST_G_DT BETWEEN :WS_DB_SUS_STDT "
            + "AND :WS_DB_SUS_ENDT )";
        AITGINF_BR.rp.order = "RVS_NO";
        IBS.STARTBR(SCCGWA, AIRGINF, this, AITGINF_BR);
        CEP.TRC(SCCGWA, AIRGINF.C_AMT);
        CEP.TRC(SCCGWA, AIRGINF.G_BAL);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "NORMAL");
            WS_GINF_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "NOTFND");
            WS_GINF_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITGINF";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_AITGINF_NO_RVS_2() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BROWER");
        CEP.TRC(SCCGWA, AIRGINF.STS);
        CEP.TRC(SCCGWA, AIRGINF.KEY.RVS_NO);
        CEP.TRC(SCCGWA, AIRGINF.BR);
        CEP.TRC(SCCGWA, AIRGINF.ITM);
        CEP.TRC(SCCGWA, AIRGINF.SEQ);
        CEP.TRC(SCCGWA, AIRGINF.STS);
        CEP.TRC(SCCGWA, WS_OTH_STS);
        AITGINF_BR.rp = new DBParm();
        AITGINF_BR.rp.TableName = "AITGINF";
        AITGINF_BR.rp.where = "BR = :AIRGINF.BR "
            + "AND ITM = :AIRGINF.ITM "
            + "AND CCY = :AIRGINF.CCY "
            + "AND STS = :AIRGINF.STS "
            + "AND ( RVS_EXP BETWEEN :WS_DB_EXP_STDT "
            + "AND :WS_DB_EXP_ENDT ) "
            + "AND ( FST_G_DT BETWEEN :WS_DB_SUS_STDT "
            + "AND :WS_DB_SUS_ENDT )";
        AITGINF_BR.rp.order = "RVS_NO";
        IBS.STARTBR(SCCGWA, AIRGINF, this, AITGINF_BR);
        CEP.TRC(SCCGWA, AIRGINF.C_AMT);
        CEP.TRC(SCCGWA, AIRGINF.G_BAL);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "NORMAL");
            WS_GINF_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "NOTFND");
            WS_GINF_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITGINF";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_AITGINF_SEQ_1() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BROWER");
        CEP.TRC(SCCGWA, AIRGINF.STS);
        CEP.TRC(SCCGWA, AIRGINF.KEY.RVS_NO);
        CEP.TRC(SCCGWA, AIRGINF.BR);
        CEP.TRC(SCCGWA, AIRGINF.ITM);
        CEP.TRC(SCCGWA, AIRGINF.SEQ);
        CEP.TRC(SCCGWA, AIRGINF.STS);
        CEP.TRC(SCCGWA, WS_OTH_STS);
        AITGINF_BR.rp = new DBParm();
        AITGINF_BR.rp.TableName = "AITGINF";
        AITGINF_BR.rp.where = "BR = :AIRGINF.BR "
            + "AND ITM = :AIRGINF.ITM "
            + "AND SEQ = :AIRGINF.SEQ "
            + "AND CCY = :AIRGINF.CCY "
            + "AND STS IN ( :AIRGINF.STS , :WS_OTH_STS ) "
            + "AND ( RVS_EXP BETWEEN :WS_DB_EXP_STDT "
            + "AND :WS_DB_EXP_ENDT ) "
            + "AND ( FST_G_DT BETWEEN :WS_DB_SUS_STDT "
            + "AND :WS_DB_SUS_ENDT )";
        AITGINF_BR.rp.order = "RVS_NO";
        IBS.STARTBR(SCCGWA, AIRGINF, this, AITGINF_BR);
        CEP.TRC(SCCGWA, AIRGINF.C_AMT);
        CEP.TRC(SCCGWA, AIRGINF.G_BAL);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "NORMAL");
            WS_GINF_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "NOTFND");
            WS_GINF_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITGINF";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_AITGINF_SEQ_2() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BROWER");
        CEP.TRC(SCCGWA, AIRGINF.STS);
        CEP.TRC(SCCGWA, AIRGINF.KEY.RVS_NO);
        CEP.TRC(SCCGWA, AIRGINF.BR);
        CEP.TRC(SCCGWA, AIRGINF.ITM);
        CEP.TRC(SCCGWA, AIRGINF.SEQ);
        CEP.TRC(SCCGWA, AIRGINF.STS);
        CEP.TRC(SCCGWA, WS_OTH_STS);
        AITGINF_BR.rp = new DBParm();
        AITGINF_BR.rp.TableName = "AITGINF";
        AITGINF_BR.rp.where = "BR = :AIRGINF.BR "
            + "AND ITM = :AIRGINF.ITM "
            + "AND SEQ = :AIRGINF.SEQ "
            + "AND CCY = :AIRGINF.CCY "
            + "AND STS = :AIRGINF.STS "
            + "AND ( RVS_EXP BETWEEN :WS_DB_EXP_STDT "
            + "AND :WS_DB_EXP_ENDT ) "
            + "AND ( FST_G_DT BETWEEN :WS_DB_SUS_STDT "
            + "AND :WS_DB_SUS_ENDT )";
        AITGINF_BR.rp.order = "RVS_NO";
        IBS.STARTBR(SCCGWA, AIRGINF, this, AITGINF_BR);
        CEP.TRC(SCCGWA, AIRGINF.C_AMT);
        CEP.TRC(SCCGWA, AIRGINF.G_BAL);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "NORMAL");
            WS_GINF_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "NOTFND");
            WS_GINF_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITGINF";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_GINF_BY_ITM_S_1() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BROWER");
        CEP.TRC(SCCGWA, AIRGINF.STS);
        CEP.TRC(SCCGWA, AIRGINF.KEY.RVS_NO);
        CEP.TRC(SCCGWA, AIRGINF.BR);
        CEP.TRC(SCCGWA, AIRGINF.ITM);
        CEP.TRC(SCCGWA, AIRGINF.SEQ);
        CEP.TRC(SCCGWA, AIRGINF.STS);
        CEP.TRC(SCCGWA, WS_OTH_STS);
        AITGINF_BR.rp = new DBParm();
        AITGINF_BR.rp.TableName = "AITGINF";
        AITGINF_BR.rp.where = "BR = :AIRGINF.BR "
            + "AND ITM = :AIRGINF.ITM "
            + "AND SEQ = :AIRGINF.SEQ "
            + "AND STS IN ( :AIRGINF.STS , :WS_OTH_STS ) "
            + "AND ( RVS_EXP BETWEEN :WS_DB_EXP_STDT "
            + "AND :WS_DB_EXP_ENDT ) "
            + "AND ( FST_G_DT BETWEEN :WS_DB_SUS_STDT "
            + "AND :WS_DB_SUS_ENDT )";
        AITGINF_BR.rp.order = "RVS_NO";
        IBS.STARTBR(SCCGWA, AIRGINF, this, AITGINF_BR);
        CEP.TRC(SCCGWA, AIRGINF.C_AMT);
        CEP.TRC(SCCGWA, AIRGINF.G_BAL);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "NORMAL");
            WS_GINF_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "NOTFND");
            WS_GINF_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITGINF";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_GINF_BY_ITM_S_2() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BROWER");
        CEP.TRC(SCCGWA, AIRGINF.STS);
        CEP.TRC(SCCGWA, AIRGINF.KEY.RVS_NO);
        CEP.TRC(SCCGWA, AIRGINF.BR);
        CEP.TRC(SCCGWA, AIRGINF.ITM);
        CEP.TRC(SCCGWA, AIRGINF.SEQ);
        CEP.TRC(SCCGWA, AIRGINF.STS);
        CEP.TRC(SCCGWA, WS_OTH_STS);
        AITGINF_BR.rp = new DBParm();
        AITGINF_BR.rp.TableName = "AITGINF";
        AITGINF_BR.rp.where = "BR = :AIRGINF.BR "
            + "AND ITM = :AIRGINF.ITM "
            + "AND SEQ = :AIRGINF.SEQ "
            + "AND STS = :AIRGINF.STS "
            + "AND ( RVS_EXP BETWEEN :WS_DB_EXP_STDT "
            + "AND :WS_DB_EXP_ENDT ) "
            + "AND ( FST_G_DT BETWEEN :WS_DB_SUS_STDT "
            + "AND :WS_DB_SUS_ENDT )";
        AITGINF_BR.rp.order = "RVS_NO";
        IBS.STARTBR(SCCGWA, AIRGINF, this, AITGINF_BR);
        CEP.TRC(SCCGWA, AIRGINF.C_AMT);
        CEP.TRC(SCCGWA, AIRGINF.G_BAL);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "NORMAL");
            WS_GINF_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "NOTFND");
            WS_GINF_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITGINF";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_GINF_BY_ITM_1() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BROWER");
        CEP.TRC(SCCGWA, AIRGINF.STS);
        CEP.TRC(SCCGWA, AIRGINF.KEY.RVS_NO);
        CEP.TRC(SCCGWA, AIRGINF.BR);
        CEP.TRC(SCCGWA, AIRGINF.ITM);
        CEP.TRC(SCCGWA, AIRGINF.SEQ);
        CEP.TRC(SCCGWA, AIRGINF.STS);
        CEP.TRC(SCCGWA, WS_OTH_STS);
        AITGINF_BR.rp = new DBParm();
        AITGINF_BR.rp.TableName = "AITGINF";
        AITGINF_BR.rp.where = "BR = :AIRGINF.BR "
            + "AND ITM = :AIRGINF.ITM "
            + "AND STS IN ( :AIRGINF.STS , :WS_OTH_STS ) "
            + "AND ( RVS_EXP BETWEEN :WS_DB_EXP_STDT "
            + "AND :WS_DB_EXP_ENDT ) "
            + "AND ( FST_G_DT BETWEEN :WS_DB_SUS_STDT "
            + "AND :WS_DB_SUS_ENDT )";
        AITGINF_BR.rp.order = "RVS_NO";
        IBS.STARTBR(SCCGWA, AIRGINF, this, AITGINF_BR);
        CEP.TRC(SCCGWA, AIRGINF.C_AMT);
        CEP.TRC(SCCGWA, AIRGINF.G_BAL);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "NORMAL");
            WS_GINF_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "NOTFND");
            WS_GINF_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITGINF";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_GINF_BY_ITM_2() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BROWER");
        CEP.TRC(SCCGWA, AIRGINF.STS);
        CEP.TRC(SCCGWA, AIRGINF.KEY.RVS_NO);
        CEP.TRC(SCCGWA, AIRGINF.BR);
        CEP.TRC(SCCGWA, AIRGINF.ITM);
        CEP.TRC(SCCGWA, AIRGINF.SEQ);
        CEP.TRC(SCCGWA, AIRGINF.STS);
        CEP.TRC(SCCGWA, WS_OTH_STS);
        AITGINF_BR.rp = new DBParm();
        AITGINF_BR.rp.TableName = "AITGINF";
        AITGINF_BR.rp.where = "BR = :AIRGINF.BR "
            + "AND ITM = :AIRGINF.ITM "
            + "AND STS = :AIRGINF.STS "
            + "AND ( RVS_EXP BETWEEN :WS_DB_EXP_STDT "
            + "AND :WS_DB_EXP_ENDT ) "
            + "AND ( FST_G_DT BETWEEN :WS_DB_SUS_STDT "
            + "AND :WS_DB_SUS_ENDT )";
        AITGINF_BR.rp.order = "RVS_NO";
        IBS.STARTBR(SCCGWA, AIRGINF, this, AITGINF_BR);
        CEP.TRC(SCCGWA, AIRGINF.C_AMT);
        CEP.TRC(SCCGWA, AIRGINF.G_BAL);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "NORMAL");
            WS_GINF_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "NOTFND");
            WS_GINF_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITGINF";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_GINF_BY_CCY_1() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BROWER");
        CEP.TRC(SCCGWA, AIRGINF.STS);
        CEP.TRC(SCCGWA, AIRGINF.KEY.RVS_NO);
        CEP.TRC(SCCGWA, AIRGINF.BR);
        CEP.TRC(SCCGWA, AIRGINF.ITM);
        CEP.TRC(SCCGWA, AIRGINF.SEQ);
        CEP.TRC(SCCGWA, AIRGINF.STS);
        CEP.TRC(SCCGWA, WS_OTH_STS);
        AITGINF_BR.rp = new DBParm();
        AITGINF_BR.rp.TableName = "AITGINF";
        AITGINF_BR.rp.where = "BR = :AIRGINF.BR "
            + "AND CCY = :AIRGINF.CCY "
            + "AND STS IN ( :AIRGINF.STS , :WS_OTH_STS ) "
            + "AND ( RVS_EXP BETWEEN :WS_DB_EXP_STDT "
            + "AND :WS_DB_EXP_ENDT ) "
            + "AND ( FST_G_DT BETWEEN :WS_DB_SUS_STDT "
            + "AND :WS_DB_SUS_ENDT )";
        AITGINF_BR.rp.order = "RVS_NO";
        IBS.STARTBR(SCCGWA, AIRGINF, this, AITGINF_BR);
        CEP.TRC(SCCGWA, AIRGINF.C_AMT);
        CEP.TRC(SCCGWA, AIRGINF.G_BAL);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "NORMAL");
            WS_GINF_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "NOTFND");
            WS_GINF_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITGINF";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_GINF_BY_CCY_2() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BROWER");
        CEP.TRC(SCCGWA, AIRGINF.STS);
        CEP.TRC(SCCGWA, AIRGINF.KEY.RVS_NO);
        CEP.TRC(SCCGWA, AIRGINF.BR);
        CEP.TRC(SCCGWA, AIRGINF.ITM);
        CEP.TRC(SCCGWA, AIRGINF.SEQ);
        CEP.TRC(SCCGWA, AIRGINF.STS);
        CEP.TRC(SCCGWA, WS_OTH_STS);
        AITGINF_BR.rp = new DBParm();
        AITGINF_BR.rp.TableName = "AITGINF";
        AITGINF_BR.rp.where = "BR = :AIRGINF.BR "
            + "AND CCY = :AIRGINF.CCY "
            + "AND STS = :AIRGINF.STS "
            + "AND ( RVS_EXP BETWEEN :WS_DB_EXP_STDT "
            + "AND :WS_DB_EXP_ENDT ) "
            + "AND ( FST_G_DT BETWEEN :WS_DB_SUS_STDT "
            + "AND :WS_DB_SUS_ENDT )";
        AITGINF_BR.rp.order = "RVS_NO";
        IBS.STARTBR(SCCGWA, AIRGINF, this, AITGINF_BR);
        CEP.TRC(SCCGWA, AIRGINF.C_AMT);
        CEP.TRC(SCCGWA, AIRGINF.G_BAL);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "NORMAL");
            WS_GINF_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "NOTFND");
            WS_GINF_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITGINF";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_AITGINF_1() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BROWER");
        CEP.TRC(SCCGWA, AIRGINF.STS);
        CEP.TRC(SCCGWA, AIRGINF.KEY.RVS_NO);
        CEP.TRC(SCCGWA, AIRGINF.BR);
        CEP.TRC(SCCGWA, AIRGINF.ITM);
        CEP.TRC(SCCGWA, AIRGINF.SEQ);
        CEP.TRC(SCCGWA, AIRGINF.STS);
        CEP.TRC(SCCGWA, WS_OTH_STS);
        CEP.TRC(SCCGWA, WS_DB_EXP_STDT);
        CEP.TRC(SCCGWA, WS_DB_EXP_ENDT);
        CEP.TRC(SCCGWA, WS_DB_SUS_STDT);
        CEP.TRC(SCCGWA, WS_DB_SUS_ENDT);
        AITGINF_BR.rp = new DBParm();
        AITGINF_BR.rp.TableName = "AITGINF";
        AITGINF_BR.rp.where = "BR = :AIRGINF.BR "
            + "AND STS IN ( :AIRGINF.STS , :WS_OTH_STS ) "
            + "AND ( RVS_EXP BETWEEN :WS_DB_EXP_STDT "
            + "AND :WS_DB_EXP_ENDT ) "
            + "AND ( FST_G_DT BETWEEN :WS_DB_SUS_STDT "
            + "AND :WS_DB_SUS_ENDT )";
        AITGINF_BR.rp.order = "RVS_NO";
        IBS.STARTBR(SCCGWA, AIRGINF, this, AITGINF_BR);
        CEP.TRC(SCCGWA, AIRGINF.C_AMT);
        CEP.TRC(SCCGWA, AIRGINF.G_BAL);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "NORMAL");
            WS_GINF_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "NOTFND");
            WS_GINF_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITGINF";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_AITGINF_2() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BROWER");
        CEP.TRC(SCCGWA, AIRGINF.STS);
        CEP.TRC(SCCGWA, AIRGINF.KEY.RVS_NO);
        CEP.TRC(SCCGWA, AIRGINF.BR);
        CEP.TRC(SCCGWA, AIRGINF.ITM);
        CEP.TRC(SCCGWA, AIRGINF.SEQ);
        CEP.TRC(SCCGWA, AIRGINF.STS);
        CEP.TRC(SCCGWA, WS_OTH_STS);
        AITGINF_BR.rp = new DBParm();
        AITGINF_BR.rp.TableName = "AITGINF";
        AITGINF_BR.rp.where = "BR = :AIRGINF.BR "
            + "AND STS = :AIRGINF.STS "
            + "AND ( RVS_EXP BETWEEN :WS_DB_EXP_STDT "
            + "AND :WS_DB_EXP_ENDT ) "
            + "AND ( FST_G_DT BETWEEN :WS_DB_SUS_STDT "
            + "AND :WS_DB_SUS_ENDT )";
        AITGINF_BR.rp.order = "RVS_NO";
        IBS.STARTBR(SCCGWA, AIRGINF, this, AITGINF_BR);
        CEP.TRC(SCCGWA, AIRGINF.C_AMT);
        CEP.TRC(SCCGWA, AIRGINF.G_BAL);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "NORMAL");
            WS_GINF_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "NOTFND");
            WS_GINF_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITGINF";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_AITGINF() throws IOException,SQLException,Exception {
        AITGINF_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, AIRGINF, this, AITGINF_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_GINF_FLG = 'Y';
            CEP.TRC(SCCGWA, "NEXT NOMAL");
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_GINF_FLG = 'N';
            CEP.TRC(SCCGWA, "NEXT NOTFND");
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITGINF";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_AITGINF() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, AITGINF_BR);
    }
    public void T000_STARTBR_AITGINF_OUT() throws IOException,SQLException,Exception {
        AITGINF_BR.rp = new DBParm();
        AITGINF_BR.rp.TableName = "AITGINF";
        AITGINF_BR.rp.where = "AC = :AIRGINF.AC";
        IBS.STARTBR(SCCGWA, AIRGINF, this, AITGINF_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_GINF_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_GINF_FLG = 'N';
        } else {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
            CEP.TRC(SCCGWA, "AICRGINF = ");
            CEP.TRC(SCCGWA, AICRGINF);
    } //FROM #ENDIF
        }
    }
    public void T000_STARTBR_AITGINF_OUT_1() throws IOException,SQLException,Exception {
        AITGINF_BR.rp = new DBParm();
        AITGINF_BR.rp.TableName = "AITGINF";
        AITGINF_BR.rp.where = "RVS_NO = :AIRGINF.KEY.RVS_NO";
        IBS.STARTBR(SCCGWA, AIRGINF, this, AITGINF_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_GINF_FLG = 'Y';
            R000_CHECK_BR();
            if (pgmRtn) return;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_GINF_FLG = 'N';
        } else {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
            CEP.TRC(SCCGWA, "AICRGINF = ");
            CEP.TRC(SCCGWA, AICRGINF);
    } //FROM #ENDIF
        }
    }
    public void T000_COUNT_AITGINF_RVS() throws IOException,SQLException,Exception {
        AITGINF_RD = new DBParm();
        AITGINF_RD.TableName = "AITGINF";
        AITGINF_RD.set = "WS-COUNT-NO=COUNT(1)";
        AITGINF_RD.where = "RVS_NO = :AIRGINF.KEY.RVS_NO";
        IBS.GROUP(SCCGWA, AIRGINF, this, AITGINF_RD);
    }
    public void T000_COUNT_AITGINF() throws IOException,SQLException,Exception {
        AITGINF_RD = new DBParm();
        AITGINF_RD.TableName = "AITGINF";
        AITGINF_RD.set = "WS-COUNT-NO=COUNT(1)";
        AITGINF_RD.where = "AC = :AIRGINF.AC";
        IBS.GROUP(SCCGWA, AIRGINF, this, AITGINF_RD);
    }
    public void T000_READ_AITGINF() throws IOException,SQLException,Exception {
        AITGINF_RD = new DBParm();
        AITGINF_RD.TableName = "AITGINF";
        AITGINF_RD.errhdl = true;
        IBS.READ(SCCGWA, AIRGINF, AITGINF_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_GINF_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_GINF_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
            CEP.TRC(SCCGWA, "AICRGINF = ");
            CEP.TRC(SCCGWA, AICRGINF);
    } //FROM #ENDIF
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITGINF";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_AITGINF_FIRST() throws IOException,SQLException,Exception {
        WS_RCD_SEQ = AICRGINF.RCD_SEQ;
        CEP.TRC(SCCGWA, WS_RCD_SEQ);
        IBS.READNEXT(SCCGWA, AIRGINF, this, AITGINF_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_GINF_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_GINF_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITGINF";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFTLCM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-TLR-QRY-BR-CHK", BPCFTLCM);
        if (BPCFTLCM.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCFTLCM.RC.RC_CODE);
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC.RC_CODE);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_OUTPUT_RVS_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_RVS_INFO);
        SCCMPAG.DATA_LEN = 852;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_AIZRGINF() throws IOException,SQLException,Exception {
        AICRGINF.INFO.POINTER = AIRGINF;
        AICRGINF.INFO.LEN = 242;
        IBS.CALLCPN(SCCGWA, "AI-R-MAIN-GINF", AICRGINF);
        if (AICRGINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICRGINF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AICPQMIB.INPUT_DATA.AC);
        CEP.TRC(SCCGWA, AICPQMIB.INPUT_DATA.CCY);
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB);
    }
    public void S000_CALL_AIZRGRVS() throws IOException,SQLException,Exception {
        AICRGRVS.INFO.POINTER = AIRGRVS;
        AICRGRVS.INFO.REC_LEN = 1113;
        IBS.CALLCPN(SCCGWA, "AI-R-MAIN-GRVS", AICRGRVS);
        if (AICRGRVS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICRGRVS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZRCRVS() throws IOException,SQLException,Exception {
        AICRCRVS.INFO.POINTER = AIRCRVS;
        AICRCRVS.INFO.REC_LEN = 893;
        IBS.CALLCPN(SCCGWA, "AI-R-MAIN-CRVS", AICRCRVS);
        if (AICRCRVS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICRCRVS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQVCH() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPQVCH.DATA.KEY.AC_DATE);
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-VCH", BPCPQVCH);
        CEP.TRC(SCCGWA, BPCPQVCH.RC);
        if (BPCPQVCH.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQVCH.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG-STATION", BPCPRGST);
        if (BPCPRGST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRGST.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        CEP.TRC(SCCGWA, SCCCLDT.RC);
        if (SCCCLDT.RC != 0) {
            CEP.ERR(SCCGWA, SCCCLDT.RC);
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
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
