package com.hisun.AI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.text.DecimalFormat;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class AIZRSBAL {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    DecimalFormat df;
    brParm AITMSTT_BR = new brParm();
    boolean pgmRtn = false;
    int K_MAX_ROW = 20;
    int K_COL_CNT = 20;
    String CPN_P_INQ_ORG_LOW = "BP-P-INQ-ORG-LOW";
    String WS_ERR_MSG = " ";
    char WS_FLG = ' ';
    char WS_FLAG = ' ';
    char WS_FLG1 = ' ';
    char WS_FLG2 = ' ';
    int WS_LAST_BR = 0;
    int WS_PORLO_BR = 0;
    char WS_INQ_FLG = ' ';
    int[] WS_OCCURS_BR = new int[1000];
    long WS_CNT3 = 0;
    AIZRSBAL_WS_OUT_LIST WS_OUT_LIST = new AIZRSBAL_WS_OUT_LIST();
    char WS_REC_HAVE = ' ';
    String WK_ITM_NAME = " ";
    int WS_CNTA = 0;
    char WS_FUNC = ' ';
    int WS_I = 0;
    int WS_K = 0;
    char WS_LVL = ' ';
    char WS_LVL1 = ' ';
    AIZRSBAL_WS_SUPR_GRP WS_SUPR_GRP = new AIZRSBAL_WS_SUPR_GRP();
    double WS_TMP_LDDBAL = 0;
    double WS_TMP_LDCBAL = 0;
    double WS_TMP_CDDBAL = 0;
    double WS_TMP_CDCBAL = 0;
    double WS_TMP_CDDRAMT = 0;
    double WS_TMP_CDCRAMT = 0;
    int WS_TMP_CDNODR = 0;
    int WS_TMP_CDNOCR = 0;
    String WS_LAST_CCY = " ";
    int WS_LAST_MSTBR = 0;
    char WS_FIR_LEV_FLG = ' ';
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AIRMSTT AIRMSTT = new AIRMSTT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    AICPQITM AICQITM = new AICPQITM();
    BPCPORLO BPCPORLO = new BPCPORLO();
    BPCPQORG BPCPQORG = new BPCPQORG();
    int WS_BR_START = 0;
    int WS_BR_END = 0;
    String WS_DB_ITM = " ";
    SCCGWA SCCGWA;
    AICRSBAL AICRSBAL;
    BPCPORUP_DATA_INFO BPCPORUP;
    AIRMSTT AIRLMSTT;
    public void MP(SCCGWA SCCGWA, AICRSBAL AICRSBAL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICRSBAL = AICRSBAL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZRSBAL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AICRSBAL.COMM_DATA.BOOK_FLG);
        CEP.TRC(SCCGWA, AICRSBAL.COMM_DATA.ITM_NO);
        CEP.TRC(SCCGWA, AICRSBAL.COMM_DATA.CCY);
        CEP.TRC(SCCGWA, AICRSBAL.COMM_DATA.BR_START);
        CEP.TRC(SCCGWA, AICRSBAL.COMM_DATA.BR_END);
        AIRLMSTT = (AIRMSTT) AICRSBAL.REC_PTR;
        AICRSBAL.RETURN_INFO = 'F';
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        IBS.init(SCCGWA, AIRMSTT);
        IBS.CLONE(SCCGWA, AIRLMSTT, AIRMSTT);
        AICRSBAL.RC.RC_MMO = "AI";
        AICRSBAL.RC.RC_CODE = 0;
        WS_REC_HAVE = 'N';
        IBS.init(SCCGWA, AICQITM);
        AICQITM.INPUT_DATA.BOOK_FLG = AICRSBAL.COMM_DATA.BOOK_FLG;
        AICQITM.INPUT_DATA.NO = AICRSBAL.COMM_DATA.ITM_NO;
        S000_CALL_AIZPQITM();
        if (pgmRtn) return;
        if (AICQITM.RC.RTNCODE == 0) {
            WK_ITM_NAME = AICQITM.OUTPUT_DATA.CHS_NM;
        }
    }
    public void S000_CALL_AIZPQITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-ITM", AICQITM);
    }
    public void S000_CALL_BPZPORLO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG_LOW, BPCPORLO);
        if (BPCPORLO.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPORLO.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], AICRSBAL.RC);
            AICRSBAL.RETURN_INFO = 'F';
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
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_GET_INPUT_BR_LEVEL();
        if (pgmRtn) return;
        if (AICRSBAL.FUNC == 'I') {
            B030_GET_INFO_HMST();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + AICRSBAL.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, AIRMSTT, AIRLMSTT);
    }
    public void B010_GET_INPUT_BR_LEVEL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = AICRSBAL.COMM_DATA.BR_START;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQORG.LVL);
        if (BPCPQORG.LVL == '7' 
            || BPCPQORG.LVL == '8' 
            || BPCPQORG.LVL == '9') {
            WS_INQ_FLG = '0';
        }
        if (BPCPQORG.LVL == '4' 
            || BPCPQORG.LVL == '5' 
            || BPCPQORG.LVL == '6') {
            WS_INQ_FLG = '1';
        }
        if (BPCPQORG.LVL == '2' 
            || BPCPQORG.LVL == '3') {
            WS_INQ_FLG = '2';
        }
    }
    public void B030_GET_INFO_HMST() throws IOException,SQLException,Exception {
        C100_MPAG_START();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_INQ_FLG);
        if (AICRSBAL.COMM_DATA.ITM_NO == null) AICRSBAL.COMM_DATA.ITM_NO = "";
        JIBS_tmp_int = AICRSBAL.COMM_DATA.ITM_NO.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) AICRSBAL.COMM_DATA.ITM_NO += " ";
        if (AICRSBAL.COMM_DATA.ITM_NO.substring(9 - 1, 9 + 2 - 1).trim().length() > 0) {
            if (WS_INQ_FLG == '0' 
                || WS_INQ_FLG == '1') {
                T000_STARTBR_NOT_BR();
                if (pgmRtn) return;
            } else {
                T000_STARTBR_BR_PROC();
                if (pgmRtn) return;
            }
        } else {
            WS_FIR_LEV_FLG = 'Y';
            if (WS_INQ_FLG == '0' 
                || WS_INQ_FLG == '1') {
                T000_STARTBR_NOT_BR1();
                if (pgmRtn) return;
            } else {
                T000_STARTBR_BR_PROC1();
                if (pgmRtn) return;
            }
        }
        B040_INCLUDE_BR_RELATION();
        if (pgmRtn) return;
        B040_INCLUDE_BR_RELATION_NEXT();
        if (pgmRtn) return;
        if (WS_FIR_LEV_FLG == 'Y') {
            T000_OUTPUT_CYCLE1();
            if (pgmRtn) return;
        } else {
            T000_OUTPUT_CYCLE();
            if (pgmRtn) return;
        }
    }
    public void B040_INCLUDE_BR_RELATION() throws IOException,SQLException,Exception {
        if (BPCPQORG.LVL == '4' 
            || BPCPQORG.LVL == '5' 
            || BPCPQORG.LVL == '6') {
            CEP.TRC(SCCGWA, "PQORG-LVL NOT = 2 OR  9");
            IBS.init(SCCGWA, BPCPORLO);
            CEP.TRC(SCCGWA, AICRSBAL.COMM_DATA.BR_START);
            BPCPORLO.BNK = SCCGWA.COMM_AREA.TR_BANK;
            BPCPORLO.BR = AICRSBAL.COMM_DATA.BR_START;
            CEP.TRC(SCCGWA, BPCPORLO.BR);
            S000_CALL_BPZPORLO();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPORLO.SUB_NUM);
            if (BPCPORLO.SUB_NUM > 0) {
                WS_FUNC = '7';
                for (WS_CNTA = 1; WS_CNTA <= BPCPORLO.SUB_NUM; WS_CNTA += 1) {
                    WS_OCCURS_BR[WS_CNTA-1] = BPCPORLO.SUB_BR_DATA[WS_CNTA-1].SUB_BR;
                    CEP.TRC(SCCGWA, WS_OCCURS_BR[WS_CNTA-1]);
                }
            } else {
                CEP.TRC(SCCGWA, BPCPQORG.SUPR_BR);
                BPCPORLO.BR = BPCPQORG.SUPR_BR;
                CEP.TRC(SCCGWA, BPCPORLO.BR);
                S000_CALL_BPZPORLO();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCPORLO.SUB_NUM);
                WS_FUNC = '7';
                for (WS_CNTA = 1; WS_CNTA <= BPCPORLO.SUB_NUM; WS_CNTA += 1) {
                    WS_OCCURS_BR[WS_CNTA-1] = BPCPORLO.SUB_BR_DATA[WS_CNTA-1].SUB_BR;
                    CEP.TRC(SCCGWA, WS_OCCURS_BR[WS_CNTA-1]);
                }
            }
        } else {
            WS_FUNC = '9';
        }
    }
    public void B040_INCLUDE_BR_RELATION_NEXT() throws IOException,SQLException,Exception {
        if ((BPCPQORG.LVL == '4' 
            || BPCPQORG.LVL == '5' 
            || BPCPQORG.LVL == '6') 
            && BPCPORLO.NEXT_CALL_FLG == 'Y') {
            CEP.TRC(SCCGWA, "BR more than  100  AND again 2222222222 ");
            WS_FLG1 = 'Y';
            WS_LAST_BR = BPCPORLO.LAST_BR;
            WS_PORLO_BR = BPCPORLO.BR;
            IBS.init(SCCGWA, BPCPORLO);
            BPCPORLO.BNK = SCCGWA.COMM_AREA.TR_BANK;
            BPCPORLO.BR = WS_PORLO_BR;
            BPCPORLO.LAST_BR = WS_LAST_BR;
            S000_CALL_BPZPORLO();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPORLO.SUB_NUM);
            for (WS_CNTA = 1; WS_CNTA <= BPCPORLO.SUB_NUM; WS_CNTA += 1) {
                WS_CNT3 = WS_CNTA + 100;
                CEP.TRC(SCCGWA, WS_CNT3);
                WS_OCCURS_BR[WS_CNT3-1] = BPCPORLO.SUB_BR_DATA[WS_CNTA-1].SUB_BR;
                CEP.TRC(SCCGWA, WS_OCCURS_BR[WS_CNT3-1]);
            }
            BPCPORLO.SUB_NUM = (short) (BPCPORLO.SUB_NUM + 100);
            CEP.TRC(SCCGWA, BPCPORLO.SUB_NUM);
        }
    }
    public void C100_MPAG_START() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 317;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_OUTPUT_CYCLE() throws IOException,SQLException,Exception {
        T000_READNEXT_AITMSTT();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            CEP.TRC(SCCGWA, WS_FUNC);
            if (WS_FUNC == '9') {
                D100_DATA_OUTPUT();
                if (pgmRtn) return;
                D100_DATA_PAGE_OUT();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, AICRSBAL.COMM_DATA.BR_START);
            CEP.TRC(SCCGWA, AIRMSTT.KEY.BR);
            if (WS_FUNC == '7') {
                CEP.TRC(SCCGWA, WS_FLAG);
                WS_CNTA = 1;
                CEP.TRC(SCCGWA, WS_CNTA);
                for (WS_CNTA = 1; WS_CNTA <= BPCPORLO.SUB_NUM 
                    && WS_FLAG != 'Y'; WS_CNTA += 1) {
                    CEP.TRC(SCCGWA, WS_CNTA);
                    CEP.TRC(SCCGWA, WS_OCCURS_BR[WS_CNTA-1]);
                    CEP.TRC(SCCGWA, AIRMSTT.KEY.BR);
                    if (WS_OCCURS_BR[WS_CNTA-1] == AIRMSTT.KEY.BR) {
                        CEP.TRC(SCCGWA, "DDDDDDDDDDDDD");
                        WS_FLAG = 'Y';
                        D100_DATA_OUTPUT();
                        if (pgmRtn) return;
                        D100_DATA_PAGE_OUT();
                        if (pgmRtn) return;
                    }
                }
                if (AICRSBAL.COMM_DATA.BR_START == AIRMSTT.KEY.BR 
                    && WS_FLAG != 'Y') {
                    CEP.TRC(SCCGWA, "FFFFFFFFFFFFFF");
                    WS_FLAG = 'Y';
                    D100_DATA_OUTPUT();
                    if (pgmRtn) return;
                    D100_DATA_PAGE_OUT();
                    if (pgmRtn) return;
                }
                WS_FLAG = ' ';
            }
            T000_READNEXT_AITMSTT();
            if (pgmRtn) return;
        }
        T000_ENDBR_AITMSTT();
        if (pgmRtn) return;
    }
    public void T000_OUTPUT_CYCLE1() throws IOException,SQLException,Exception {
        WS_LAST_CCY = " ";
        WS_LAST_MSTBR = 0;
        WS_FLAG = ' ';
        T000_READNEXT_AITMSTT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_FUNC);
        if (WS_FUNC == '9') {
            while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
                && SCCMPAG.FUNC != 'E') {
                if (AIRMSTT.KEY.ITM_NO == null) AIRMSTT.KEY.ITM_NO = "";
                JIBS_tmp_int = AIRMSTT.KEY.ITM_NO.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) AIRMSTT.KEY.ITM_NO += " ";
                if (AIRMSTT.KEY.ITM_NO.substring(7 - 1, 7 + 2 - 1).trim().length() > 0) {
                    if (((!AIRMSTT.CCY.equalsIgnoreCase(WS_LAST_CCY) 
                        && WS_LAST_CCY.trim().length() > 0) 
                        || (AIRMSTT.KEY.BR != WS_LAST_MSTBR 
                        && WS_LAST_MSTBR != 0))) {
                        D100_DATA_OUTPUT1();
                        if (pgmRtn) return;
                        D100_DATA_PAGE_OUT();
                        if (pgmRtn) return;
                    }
                    WS_TMP_LDDBAL += AIRMSTT.LDDBAL;
                    WS_TMP_LDCBAL += AIRMSTT.LDCBAL;
                    WS_TMP_CDDBAL += AIRMSTT.CDDBAL;
                    WS_TMP_CDCBAL += AIRMSTT.CDCBAL;
                    WS_TMP_CDDRAMT += AIRMSTT.CDDRAMT;
                    WS_TMP_CDCRAMT += AIRMSTT.CDCRAMT;
                    WS_TMP_CDNODR += AIRMSTT.CDNODR;
                    WS_TMP_CDNOCR += AIRMSTT.CDNOCR;
                    CEP.TRC(SCCGWA, "fc1");
                    CEP.TRC(SCCGWA, WS_LAST_CCY);
                    CEP.TRC(SCCGWA, WS_LAST_MSTBR);
                    CEP.TRC(SCCGWA, AIRMSTT.CCY);
                    CEP.TRC(SCCGWA, AIRMSTT.KEY.ITM_NO);
                    WS_LAST_CCY = AIRMSTT.CCY;
                    WS_LAST_MSTBR = AIRMSTT.KEY.BR;
                }
                T000_READNEXT_AITMSTT();
                if (pgmRtn) return;
            }
            T000_ENDBR_AITMSTT();
            if (pgmRtn) return;
            if (WS_LAST_CCY.trim().length() > 0) {
                D100_DATA_OUTPUT1();
                if (pgmRtn) return;
                D100_DATA_PAGE_OUT();
                if (pgmRtn) return;
            }
        }
        if (WS_FUNC == '7') {
            while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
                && SCCMPAG.FUNC != 'E') {
                if (AIRMSTT.KEY.ITM_NO == null) AIRMSTT.KEY.ITM_NO = "";
                JIBS_tmp_int = AIRMSTT.KEY.ITM_NO.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) AIRMSTT.KEY.ITM_NO += " ";
                if (AIRMSTT.KEY.ITM_NO.substring(7 - 1, 7 + 2 - 1).trim().length() > 0) {
                    WS_CNTA = 1;
                    for (WS_CNTA = 1; WS_CNTA <= BPCPORLO.SUB_NUM 
                        && WS_FLAG != 'Y'; WS_CNTA += 1) {
                        CEP.TRC(SCCGWA, WS_CNTA);
                        CEP.TRC(SCCGWA, WS_OCCURS_BR[WS_CNTA-1]);
                        CEP.TRC(SCCGWA, AIRMSTT.KEY.BR);
                        if (WS_OCCURS_BR[WS_CNTA-1] == AIRMSTT.KEY.BR) {
                            CEP.TRC(SCCGWA, "GGGGGGGG");
                            WS_FLAG = 'Y';
                            if (((!AIRMSTT.CCY.equalsIgnoreCase(WS_LAST_CCY) 
                                && WS_LAST_CCY.trim().length() > 0) 
                                || (AIRMSTT.KEY.BR != WS_LAST_MSTBR 
                                && WS_LAST_MSTBR != 0))) {
                                D100_DATA_OUTPUT1();
                                if (pgmRtn) return;
                                D100_DATA_PAGE_OUT();
                                if (pgmRtn) return;
                            }
                            WS_TMP_LDDBAL += AIRMSTT.LDDBAL;
                            WS_TMP_LDCBAL += AIRMSTT.LDCBAL;
                            WS_TMP_CDDBAL += AIRMSTT.CDDBAL;
                            WS_TMP_CDCBAL += AIRMSTT.CDCBAL;
                            WS_TMP_CDDRAMT += AIRMSTT.CDDRAMT;
                            WS_TMP_CDCRAMT += AIRMSTT.CDCRAMT;
                            WS_TMP_CDNODR += AIRMSTT.CDNODR;
                            WS_TMP_CDNOCR += AIRMSTT.CDNOCR;
                            WS_LAST_CCY = AIRMSTT.CCY;
                            WS_LAST_MSTBR = AIRMSTT.KEY.BR;
                        }
                    }
                    if (AICRSBAL.COMM_DATA.BR_START == AIRMSTT.KEY.BR 
                        && WS_FLAG != 'Y') {
                        CEP.TRC(SCCGWA, "HHHHHHHHHHH");
                        if (((!AIRMSTT.CCY.equalsIgnoreCase(WS_LAST_CCY) 
                            && WS_LAST_CCY.trim().length() > 0) 
                            || (AIRMSTT.KEY.BR != WS_LAST_MSTBR 
                            && WS_LAST_MSTBR != 0))) {
                            D100_DATA_OUTPUT1();
                            if (pgmRtn) return;
                            D100_DATA_PAGE_OUT();
                            if (pgmRtn) return;
                        }
                        WS_FLAG = 'Y';
                        WS_TMP_LDDBAL += AIRMSTT.LDDBAL;
                        WS_TMP_LDCBAL += AIRMSTT.LDCBAL;
                        WS_TMP_CDDBAL += AIRMSTT.CDDBAL;
                        WS_TMP_CDCBAL += AIRMSTT.CDCBAL;
                        WS_TMP_CDDRAMT += AIRMSTT.CDDRAMT;
                        WS_TMP_CDCRAMT += AIRMSTT.CDCRAMT;
                        WS_TMP_CDNODR += AIRMSTT.CDNODR;
                        WS_TMP_CDNOCR += AIRMSTT.CDNOCR;
                        WS_LAST_CCY = AIRMSTT.CCY;
                        WS_LAST_MSTBR = AIRMSTT.KEY.BR;
                    }
                }
                WS_FLAG = ' ';
                T000_READNEXT_AITMSTT();
                if (pgmRtn) return;
            }
            if (WS_LAST_CCY.trim().length() > 0) {
                D100_DATA_OUTPUT1();
                if (pgmRtn) return;
                D100_DATA_PAGE_OUT();
                if (pgmRtn) return;
            }
            T000_ENDBR_AITMSTT();
            if (pgmRtn) return;
        }
    }
    public void D100_DATA_OUTPUT1() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "5170-----------------------");
        IBS.init(SCCGWA, WS_OUT_LIST);
        WS_OUT_LIST.WS_BOOK_FLG = AIRMSTT.KEY.GL_BOOK_FLG;
        WS_OUT_LIST.WS_BR = WS_LAST_MSTBR;
        WS_OUT_LIST.WS_ITM_NO = AICRSBAL.COMM_DATA.ITM_NO;
        WS_OUT_LIST.WS_CCY = WS_LAST_CCY;
        WS_OUT_LIST.WS_AC_CNAME = WK_ITM_NAME;
        WS_OUT_LIST.WS_LDDBAL = WS_TMP_LDDBAL;
        WS_OUT_LIST.WS_LDCBAL = WS_TMP_LDCBAL;
        WS_OUT_LIST.WS_CDDBAL = WS_TMP_CDDBAL;
        WS_OUT_LIST.WS_CDCBAL = WS_TMP_CDCBAL;
        WS_OUT_LIST.WS_CDDRAMT = WS_TMP_CDDRAMT;
        WS_OUT_LIST.WS_CDCRAMT = WS_TMP_CDCRAMT;
        df = new DecimalFormat("########0");
        WS_OUT_LIST.WS_CDNODR = df.format(WS_TMP_CDNODR);
        df = new DecimalFormat("########0");
        WS_OUT_LIST.WS_CDNOCR = df.format(WS_TMP_CDNOCR);
        WS_TMP_LDDBAL = 0;
        WS_TMP_LDCBAL = 0;
        WS_TMP_CDDBAL = 0;
        WS_TMP_CDCBAL = 0;
        WS_TMP_CDDRAMT = 0;
        WS_TMP_CDCRAMT = 0;
        WS_TMP_CDNODR = 0;
        WS_TMP_CDNOCR = 0;
        CEP.TRC(SCCGWA, "ZR05--------------------22");
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_BOOK_FLG);
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_BR);
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_ITM_NO);
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_CCY);
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_AC_CNAME);
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_LDDBAL);
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_LDCBAL);
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_CDDBAL);
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_CDCBAL);
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_CDDRAMT);
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_CDCRAMT);
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_CDNODR);
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_CDNOCR);
    }
    public void D100_DATA_OUTPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "5170-----------------------");
        IBS.init(SCCGWA, WS_OUT_LIST);
        WS_OUT_LIST.WS_BOOK_FLG = AIRMSTT.KEY.GL_BOOK_FLG;
        WS_OUT_LIST.WS_BR = AIRMSTT.KEY.BR;
        WS_OUT_LIST.WS_ITM_NO = AIRMSTT.KEY.ITM_NO;
        WS_OUT_LIST.WS_CCY = AIRMSTT.CCY;
        WS_OUT_LIST.WS_AC_CNAME = WK_ITM_NAME;
        WS_OUT_LIST.WS_LDDBAL = AIRMSTT.LDDBAL;
        WS_OUT_LIST.WS_LDCBAL = AIRMSTT.LDCBAL;
        WS_OUT_LIST.WS_CDDBAL = AIRMSTT.CDDBAL;
        WS_OUT_LIST.WS_CDCBAL = AIRMSTT.CDCBAL;
        WS_OUT_LIST.WS_CDDRAMT = AIRMSTT.CDDRAMT;
        WS_OUT_LIST.WS_CDCRAMT = AIRMSTT.CDCRAMT;
        df = new DecimalFormat("########0");
        WS_OUT_LIST.WS_CDNODR = df.format(AIRMSTT.CDNODR);
        df = new DecimalFormat("########0");
        WS_OUT_LIST.WS_CDNOCR = df.format(AIRMSTT.CDNOCR);
        CEP.TRC(SCCGWA, "ZR05--------------------22");
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_BOOK_FLG);
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_BR);
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_ITM_NO);
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_CCY);
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_AC_CNAME);
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_LDDBAL);
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_LDCBAL);
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_CDDBAL);
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_CDCBAL);
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_CDDRAMT);
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_CDCRAMT);
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_CDNODR);
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_CDNOCR);
    }
    public void D100_DATA_PAGE_OUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "FCTST");
        CEP.TRC(SCCGWA, WS_OUT_LIST);
        WS_FLG2 = 'Y';
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUT_LIST);
        SCCMPAG.DATA_LEN = 317;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_ENDBR_AITMSTT() throws IOException,SQLException,Exception {
        AITMSTT_BR.rp.Reqid = 1;
        IBS.ENDBR(SCCGWA, AITMSTT_BR);
    }
    public void T000_STARTBR_NOT_BR() throws IOException,SQLException,Exception {
        AIRMSTT.KEY.GL_BOOK_FLG = AICRSBAL.COMM_DATA.BOOK_FLG;
        AIRMSTT.KEY.ITM_NO = AICRSBAL.COMM_DATA.ITM_NO;
        AIRMSTT.CCY = AICRSBAL.COMM_DATA.CCY;
        AITMSTT_BR.rp = new DBParm();
        AITMSTT_BR.rp.TableName = "AITMSTT";
        AITMSTT_BR.rp.where = "GL_BOOK_FLG = :AIRMSTT.KEY.GL_BOOK_FLG "
            + "AND ITM_NO = :AIRMSTT.KEY.ITM_NO "
            + "AND ( CCY = :AIRMSTT.CCY "
            + "OR ' ' = :AIRMSTT.CCY )";
        AITMSTT_BR.rp.Reqid = 1;
        AITMSTT_BR.rp.order = "BR,CCY";
        IBS.STARTBR(SCCGWA, AIRMSTT, this, AITMSTT_BR);
        CEP.TRC(SCCGWA, "111111111111111111111111");
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRSBAL.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRSBAL.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_READ_AITMSTT_NOTFND, AICRSBAL.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITMSTT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BR_PROC() throws IOException,SQLException,Exception {
        AIRMSTT.KEY.GL_BOOK_FLG = AICRSBAL.COMM_DATA.BOOK_FLG;
        AIRMSTT.KEY.ITM_NO = AICRSBAL.COMM_DATA.ITM_NO;
        AIRMSTT.CCY = AICRSBAL.COMM_DATA.CCY;
        WS_BR_START = AICRSBAL.COMM_DATA.BR_START;
        CEP.TRC(SCCGWA, WS_BR_START);
        AITMSTT_BR.rp = new DBParm();
        AITMSTT_BR.rp.TableName = "AITMSTT";
        AITMSTT_BR.rp.where = "GL_BOOK_FLG = :AIRMSTT.KEY.GL_BOOK_FLG "
            + "AND ITM_NO = :AIRMSTT.KEY.ITM_NO "
            + "AND BR = :WS_BR_START "
            + "AND ( CCY = :AIRMSTT.CCY "
            + "OR ' ' = :AIRMSTT.CCY )";
        AITMSTT_BR.rp.Reqid = 1;
        AITMSTT_BR.rp.order = "BR,CCY";
        IBS.STARTBR(SCCGWA, AIRMSTT, this, AITMSTT_BR);
        CEP.TRC(SCCGWA, "222222222222222222222");
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRSBAL.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRSBAL.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_READ_AITMSTT_NOTFND, AICRSBAL.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITMSTT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_NOT_BR1() throws IOException,SQLException,Exception {
        AIRMSTT.KEY.GL_BOOK_FLG = AICRSBAL.COMM_DATA.BOOK_FLG;
        if (AICRSBAL.COMM_DATA.ITM_NO == null) AICRSBAL.COMM_DATA.ITM_NO = "";
        JIBS_tmp_int = AICRSBAL.COMM_DATA.ITM_NO.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) AICRSBAL.COMM_DATA.ITM_NO += " ";
        if (AICRSBAL.COMM_DATA.ITM_NO.substring(5 - 1, 5 + 4 - 1).trim().length() == 0) {
            if (AICRSBAL.COMM_DATA.ITM_NO == null) AICRSBAL.COMM_DATA.ITM_NO = "";
            JIBS_tmp_int = AICRSBAL.COMM_DATA.ITM_NO.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) AICRSBAL.COMM_DATA.ITM_NO += " ";
            if (AIRMSTT.KEY.ITM_NO == null) AIRMSTT.KEY.ITM_NO = "";
            JIBS_tmp_int = AIRMSTT.KEY.ITM_NO.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) AIRMSTT.KEY.ITM_NO += " ";
            AIRMSTT.KEY.ITM_NO = AICRSBAL.COMM_DATA.ITM_NO.substring(0, 4) + AIRMSTT.KEY.ITM_NO.substring(4);
            if (AIRMSTT.KEY.ITM_NO == null) AIRMSTT.KEY.ITM_NO = "";
            JIBS_tmp_int = AIRMSTT.KEY.ITM_NO.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) AIRMSTT.KEY.ITM_NO += " ";
            AIRMSTT.KEY.ITM_NO = AIRMSTT.KEY.ITM_NO.substring(0, 5 - 1) + "______" + AIRMSTT.KEY.ITM_NO.substring(5 + 6 - 1);
        } else {
            if (AICRSBAL.COMM_DATA.ITM_NO == null) AICRSBAL.COMM_DATA.ITM_NO = "";
            JIBS_tmp_int = AICRSBAL.COMM_DATA.ITM_NO.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) AICRSBAL.COMM_DATA.ITM_NO += " ";
            if (AIRMSTT.KEY.ITM_NO == null) AIRMSTT.KEY.ITM_NO = "";
            JIBS_tmp_int = AIRMSTT.KEY.ITM_NO.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) AIRMSTT.KEY.ITM_NO += " ";
            AIRMSTT.KEY.ITM_NO = AICRSBAL.COMM_DATA.ITM_NO.substring(0, 8) + AIRMSTT.KEY.ITM_NO.substring(8);
            if (AIRMSTT.KEY.ITM_NO == null) AIRMSTT.KEY.ITM_NO = "";
            JIBS_tmp_int = AIRMSTT.KEY.ITM_NO.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) AIRMSTT.KEY.ITM_NO += " ";
            AIRMSTT.KEY.ITM_NO = AIRMSTT.KEY.ITM_NO.substring(0, 9 - 1) + "__" + AIRMSTT.KEY.ITM_NO.substring(9 + 2 - 1);
        }
        AIRMSTT.CCY = AICRSBAL.COMM_DATA.CCY;
        AITMSTT_BR.rp = new DBParm();
        AITMSTT_BR.rp.TableName = "AITMSTT";
        AITMSTT_BR.rp.where = "GL_BOOK_FLG = :AIRMSTT.KEY.GL_BOOK_FLG "
            + "AND ITM_NO LIKE :AIRMSTT.KEY.ITM_NO "
            + "AND ( CCY = :AIRMSTT.CCY "
            + "OR ' ' = :AIRMSTT.CCY )";
        AITMSTT_BR.rp.Reqid = 1;
        AITMSTT_BR.rp.order = "BR,CCY";
        IBS.STARTBR(SCCGWA, AIRMSTT, this, AITMSTT_BR);
        CEP.TRC(SCCGWA, "111111111111111111111111");
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRSBAL.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRSBAL.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_READ_AITMSTT_NOTFND, AICRSBAL.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITMSTT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BR_PROC1() throws IOException,SQLException,Exception {
        AIRMSTT.KEY.GL_BOOK_FLG = AICRSBAL.COMM_DATA.BOOK_FLG;
        if (AICRSBAL.COMM_DATA.ITM_NO == null) AICRSBAL.COMM_DATA.ITM_NO = "";
        JIBS_tmp_int = AICRSBAL.COMM_DATA.ITM_NO.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) AICRSBAL.COMM_DATA.ITM_NO += " ";
        if (AICRSBAL.COMM_DATA.ITM_NO.substring(5 - 1, 5 + 4 - 1).trim().length() == 0) {
            if (AICRSBAL.COMM_DATA.ITM_NO == null) AICRSBAL.COMM_DATA.ITM_NO = "";
            JIBS_tmp_int = AICRSBAL.COMM_DATA.ITM_NO.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) AICRSBAL.COMM_DATA.ITM_NO += " ";
            if (AIRMSTT.KEY.ITM_NO == null) AIRMSTT.KEY.ITM_NO = "";
            JIBS_tmp_int = AIRMSTT.KEY.ITM_NO.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) AIRMSTT.KEY.ITM_NO += " ";
            AIRMSTT.KEY.ITM_NO = AICRSBAL.COMM_DATA.ITM_NO.substring(0, 4) + AIRMSTT.KEY.ITM_NO.substring(4);
            if (AIRMSTT.KEY.ITM_NO == null) AIRMSTT.KEY.ITM_NO = "";
            JIBS_tmp_int = AIRMSTT.KEY.ITM_NO.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) AIRMSTT.KEY.ITM_NO += " ";
            AIRMSTT.KEY.ITM_NO = AIRMSTT.KEY.ITM_NO.substring(0, 5 - 1) + "______" + AIRMSTT.KEY.ITM_NO.substring(5 + 6 - 1);
        } else {
            if (AICRSBAL.COMM_DATA.ITM_NO == null) AICRSBAL.COMM_DATA.ITM_NO = "";
            JIBS_tmp_int = AICRSBAL.COMM_DATA.ITM_NO.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) AICRSBAL.COMM_DATA.ITM_NO += " ";
            if (AIRMSTT.KEY.ITM_NO == null) AIRMSTT.KEY.ITM_NO = "";
            JIBS_tmp_int = AIRMSTT.KEY.ITM_NO.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) AIRMSTT.KEY.ITM_NO += " ";
            AIRMSTT.KEY.ITM_NO = AICRSBAL.COMM_DATA.ITM_NO.substring(0, 8) + AIRMSTT.KEY.ITM_NO.substring(8);
            if (AIRMSTT.KEY.ITM_NO == null) AIRMSTT.KEY.ITM_NO = "";
            JIBS_tmp_int = AIRMSTT.KEY.ITM_NO.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) AIRMSTT.KEY.ITM_NO += " ";
            AIRMSTT.KEY.ITM_NO = AIRMSTT.KEY.ITM_NO.substring(0, 9 - 1) + "__" + AIRMSTT.KEY.ITM_NO.substring(9 + 2 - 1);
        }
        AIRMSTT.CCY = AICRSBAL.COMM_DATA.CCY;
        WS_BR_START = AICRSBAL.COMM_DATA.BR_START;
        AITMSTT_BR.rp = new DBParm();
        AITMSTT_BR.rp.TableName = "AITMSTT";
        AITMSTT_BR.rp.where = "GL_BOOK_FLG = :AIRMSTT.KEY.GL_BOOK_FLG "
            + "AND ITM_NO LIKE :AIRMSTT.KEY.ITM_NO "
            + "AND BR = :WS_BR_START "
            + "AND ( CCY = :AIRMSTT.CCY "
            + "OR ' ' = :AIRMSTT.CCY )";
        AITMSTT_BR.rp.Reqid = 1;
        AITMSTT_BR.rp.order = "BR,CCY";
        IBS.STARTBR(SCCGWA, AIRMSTT, this, AITMSTT_BR);
        CEP.TRC(SCCGWA, "222222222222222222222");
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRSBAL.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICRSBAL.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_READ_AITMSTT_NOTFND, AICRSBAL.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITMSTT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_AITMSTT() throws IOException,SQLException,Exception {
        AITMSTT_BR.rp.Reqid = 1;
        IBS.READNEXT(SCCGWA, AIRMSTT, this, AITMSTT_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICRSBAL.RETURN_INFO = 'F';
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_NORMAL, AICRSBAL.RC);
            WS_REC_HAVE = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            if (WS_REC_HAVE == 'N') {
                AICRSBAL.RETURN_INFO = 'N';
                IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_READ_AITMSTT_NOTFND, AICRSBAL.RC);
            }
        } else {
            AICRSBAL.RETURN_INFO = 'O';
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_READ_FILE_ERR, AICRSBAL.RC);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITMSTT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (AICRSBAL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "AICRSBAL = ");
            CEP.TRC(SCCGWA, AICRSBAL);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
