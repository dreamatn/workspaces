package com.hisun.AI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class AIOT8530 {
    String JIBS_tmp_str[] = new String[10];
    brParm AITMSTT_BR = new brParm();
    brParm BPTPARM_BR = new brParm();
    boolean pgmRtn = false;
    int K_MAX_ROW = 18;
    int K_MAX_COL = 500;
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    char WS_FUNC_FLG = ' ';
    String WS_ITM_NAME = " ";
    int WS_LAST_BR = 0;
    int WS_PORLO_BR = 0;
    int WS_CNTA = 0;
    int WS_CNTB = 0;
    long WS_CNT3 = 0;
    int WS_TMP_BR = 0;
    double WS_TMP_BAL = 0;
    double WS_ALL_BAL = 0;
    String WS_CCY = " ";
    short WS_I = 0;
    char WS_INQ_FLG = ' ';
    int[] WS_OCCURS_BR = new int[1000];
    AIOT8530_WS_ITM_LIST WS_ITM_LIST = new AIOT8530_WS_ITM_LIST();
    AIOT8530_WS_OUT_LIST WS_OUT_LIST = new AIOT8530_WS_OUT_LIST();
    AIOT8530_WS_DATA[] WS_DATA = new AIOT8530_WS_DATA[50];
    char WS_MSTT_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    char WS_READ_FLG = ' ';
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCSUBS SCCSUBS = new SCCSUBS();
    AICSSBAL AICSSBAL = new AICSSBAL();
    AICPQITM AICQITM = new AICPQITM();
    AIRMSTT AIRMSTT = new AIRMSTT();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCPORLO BPCPORLO = new BPCPORLO();
    AIRPAI15 AIRPAI15 = new AIRPAI15();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRPARM BPRPARM = new BPRPARM();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    SCCGWA SCCGWA;
    AIB8530_AWA_8530 AIB8530_AWA_8530;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public AIOT8530() {
        for (int i=0;i<50;i++) WS_DATA[i] = new AIOT8530_WS_DATA();
    }
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIOT8530 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB8530_AWA_8530>");
        AIB8530_AWA_8530 = (AIB8530_AWA_8530) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        CEP.TRC(SCCGWA, "B----------------------------------------");
        IBS.init(SCCGWA, BPCRMBPM);
        BPCRMBPM.RC.RC_MMO = "BP";
        BPCRMBPM.PTR = BPRPARM;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_DATA();
        if (pgmRtn) return;
        B100_CHECK_STA_CD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "C--------------------------------------");
    }
    public void B100_CHECK_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "D-----------------------------");
        CEP.TRC(SCCGWA, AIB8530_AWA_8530.BR_START);
        CEP.TRC(SCCGWA, AIB8530_AWA_8530.STA_CD);
        CEP.TRC(SCCGWA, AIB8530_AWA_8530.DT);
        CEP.TRC(SCCGWA, "D1-----------------------------");
        if (AIB8530_AWA_8530.BR_START == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_INPUT_ERROR;
            WS_FLD_NO = AIB8530_AWA_8530.BR_START_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = AIB8530_AWA_8530.BR_START;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPQORG.LVL);
            if (BPCPQORG.LVL == '9') {
                WS_INQ_FLG = '0';
            } else {
                if (BPCPQORG.LVL == '2') {
                    WS_INQ_FLG = '2';
                } else {
                    WS_INQ_FLG = '1';
                }
            }
        }
        if (AIB8530_AWA_8530.DT != SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_INPUT_ERROR;
            WS_FLD_NO = AIB8530_AWA_8530.DT_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (AIB8530_AWA_8530.STA_CD.trim().length() == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_INPUT_ERROR;
            WS_FLD_NO = AIB8530_AWA_8530.STA_CD_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B100_CHECK_STA_CD() throws IOException,SQLException,Exception {
        if (AIB8530_AWA_8530.STA_CD.equalsIgnoreCase("ALL")) {
            B010_STARTBR_BPTPARM();
            if (pgmRtn) return;
            B011_READNEXT_BPTPARM();
            if (pgmRtn) return;
            B210_OUT_TITLE();
            if (pgmRtn) return;
            while (BPCRMBPM.RETURN_INFO != 'L') {
                AIB8530_AWA_8530.STA_CD = BPRPARM.KEY.CODE;
                CEP.TRC(SCCGWA, AIB8530_AWA_8530.STA_CD);
                B020_QUERY_RECORD_PROC();
                if (pgmRtn) return;
                B200_TRANS_DATA();
                if (pgmRtn) return;
                B230_OUT_BRW_DATA();
                if (pgmRtn) return;
                B011_READNEXT_BPTPARM();
                if (pgmRtn) return;
            }
            B012_ENDBR_BPTPARM();
            if (pgmRtn) return;
        } else {
            B020_QUERY_RECORD_PROC();
            if (pgmRtn) return;
            B210_OUT_TITLE();
            if (pgmRtn) return;
            B200_TRANS_DATA();
            if (pgmRtn) return;
            B230_OUT_BRW_DATA();
            if (pgmRtn) return;
        }
    }
    public void B010_STARTBR_BPTPARM() throws IOException,SQLException,Exception {
        BPCRMBPM.FUNC = 'S';
        BPRPARM.KEY.TYPE = "PAI15";
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
    }
    public void B011_READNEXT_BPTPARM() throws IOException,SQLException,Exception {
        BPCRMBPM.FUNC = 'R';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
    }
    public void B012_ENDBR_BPTPARM() throws IOException,SQLException,Exception {
        BPCRMBPM.FUNC = 'E';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
    }
    public void B020_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, AIRPAI15);
        AIRPAI15.KEY.TYP = "PAI15";
        AIRPAI15.KEY.CD = AIB8530_AWA_8530.STA_CD;
        IBS.CPY2CLS(SCCGWA, AIRPAI15.KEY.CD, AIRPAI15.KEY.REDEFINES6);
        BPCPRMM.FUNC = '3';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        AIRPAI15.VAL_LEN = 222;
        BPCPRMM.DAT_PTR = AIRPAI15;
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AIRPAI15.DATA_TXT);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ITM_LIST);
            WS_ITM_NAME = AIRPAI15.CDESC;
            CEP.TRC(SCCGWA, WS_ITM_LIST);
            CEP.TRC(SCCGWA, WS_ITM_LIST.WS_OCCURS_ITM[1-1]);
            CEP.TRC(SCCGWA, WS_ITM_LIST.WS_OCCURS_ITM[20-1]);
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
    public void S000_CALL_BPZRMBPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MBRW-PARM", BPCRMBPM);
        if (BPCRMBPM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRMBPM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B200_TRANS_DATA() throws IOException,SQLException,Exception {
        WS_TMP_BR = 0;
        WS_TMP_BAL = 0;
        WS_ALL_BAL = 0;
        for (WS_I = 1; WS_I <= 50; WS_I += 1) {
            WS_DATA[WS_I-1].WS_DATA_CCY = " ";
            WS_DATA[WS_I-1].WS_DATA_AMT = 0;
        }
        B220_INCLUDE_BR_RELATION();
        if (pgmRtn) return;
        if (WS_INQ_FLG == '1') {
            for (WS_CNTA = 1; WS_CNTA <= BPCPORLO.SUB_NUM; WS_CNTA += 1) {
                CEP.TRC(SCCGWA, WS_CNTA);
                WS_TMP_BR = WS_OCCURS_BR[WS_CNTA-1];
                CEP.TRC(SCCGWA, WS_TMP_BR);
                for (WS_CNTB = 1; WS_CNTB <= 20; WS_CNTB += 1) {
                    IBS.init(SCCGWA, AIRMSTT);
                    WS_MSTT_FLG = ' ';
                    AIRMSTT.KEY.ITM_NO = WS_ITM_LIST.WS_OCCURS_ITM[WS_CNTB-1];
                    T000_START_BR_PROC();
                    if (pgmRtn) return;
                    T000_READNEXT_AITMSTT();
                    if (pgmRtn) return;
                    while (WS_MSTT_FLG != 'N' 
                        && SCCMPAG.FUNC != 'E') {
                        WS_FOUND_FLG = 'N';
                        WS_TMP_BAL = AIRMSTT.CDDBAL + AIRMSTT.CDCBAL;
                        CEP.TRC(SCCGWA, WS_TMP_BAL);
                        for (WS_I = 1; WS_I <= 50 
                            && WS_FOUND_FLG != 'Y'; WS_I += 1) {
                            if (AIRMSTT.CCY.equalsIgnoreCase(WS_DATA[WS_I-1].WS_DATA_CCY) 
                                || WS_DATA[WS_I-1].WS_DATA_CCY.trim().length() == 0) {
                                WS_DATA[WS_I-1].WS_DATA_CCY = AIRMSTT.CCY;
                                WS_DATA[WS_I-1].WS_DATA_AMT += WS_TMP_BAL;
                                WS_FOUND_FLG = 'Y';
                            }
                        }
                        T000_READNEXT_AITMSTT();
                        if (pgmRtn) return;
                    }
                    T000_ENDBR_AITMSTT();
                    if (pgmRtn) return;
                }
            }
        } else {
            for (WS_CNTB = 1; WS_CNTB <= 20; WS_CNTB += 1) {
                IBS.init(SCCGWA, AIRMSTT);
                WS_MSTT_FLG = ' ';
                AIRMSTT.KEY.ITM_NO = WS_ITM_LIST.WS_OCCURS_ITM[WS_CNTB-1];
                if (WS_INQ_FLG == '0') {
                    T000_START_NOT_BR();
                    if (pgmRtn) return;
                } else {
                    WS_TMP_BR = AIB8530_AWA_8530.BR_START;
                    T000_START_BR_PROC();
                    if (pgmRtn) return;
                }
                T000_READNEXT_AITMSTT();
                if (pgmRtn) return;
                while (WS_MSTT_FLG != 'N' 
                    && SCCMPAG.FUNC != 'E') {
                    if (WS_INQ_FLG == '0') {
                        CEP.TRC(SCCGWA, AIRMSTT.KEY.BR);
                        IBS.init(SCCGWA, BPCPQORG);
                        BPCPQORG.BR = AIRMSTT.KEY.BR;
                        S000_CALL_BPZPQORG();
                        if (pgmRtn) return;
                        if (BPCPQORG.VIL_TYP.equalsIgnoreCase("00")) {
                            WS_FOUND_FLG = 'N';
                            WS_TMP_BAL = AIRMSTT.CDDBAL + AIRMSTT.CDCBAL;
                            CEP.TRC(SCCGWA, WS_TMP_BAL);
                            for (WS_I = 1; WS_I <= 50 
                                && WS_FOUND_FLG != 'Y'; WS_I += 1) {
                                if (AIRMSTT.CCY.equalsIgnoreCase(WS_DATA[WS_I-1].WS_DATA_CCY) 
                                    || WS_DATA[WS_I-1].WS_DATA_CCY.trim().length() == 0) {
                                    WS_DATA[WS_I-1].WS_DATA_CCY = AIRMSTT.CCY;
                                    WS_DATA[WS_I-1].WS_DATA_AMT += WS_TMP_BAL;
                                    WS_FOUND_FLG = 'Y';
                                }
                            }
                        }
                    } else {
                        WS_FOUND_FLG = 'N';
                        WS_TMP_BAL = AIRMSTT.CDDBAL + AIRMSTT.CDCBAL;
                        CEP.TRC(SCCGWA, WS_TMP_BAL);
                        for (WS_I = 1; WS_I <= 50 
                            && WS_FOUND_FLG != 'Y'; WS_I += 1) {
                            if (AIRMSTT.CCY.equalsIgnoreCase(WS_DATA[WS_I-1].WS_DATA_CCY) 
                                || WS_DATA[WS_I-1].WS_DATA_CCY.trim().length() == 0) {
                                WS_DATA[WS_I-1].WS_DATA_CCY = AIRMSTT.CCY;
                                WS_DATA[WS_I-1].WS_DATA_AMT += WS_TMP_BAL;
                                WS_FOUND_FLG = 'Y';
                            }
                        }
                    }
                    T000_READNEXT_AITMSTT();
                    if (pgmRtn) return;
                }
                T000_ENDBR_AITMSTT();
                if (pgmRtn) return;
            }
        }
    }
    public void B210_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 213;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_MAX_COL;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B220_INCLUDE_BR_RELATION() throws IOException,SQLException,Exception {
        if (WS_INQ_FLG == '1') {
            IBS.init(SCCGWA, BPCPORLO);
            BPCPORLO.BNK = SCCGWA.COMM_AREA.TR_BANK;
            BPCPORLO.BR = AIB8530_AWA_8530.BR_START;
            S000_CALL_BPZPORLO();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPORLO.SUB_NUM);
            if (BPCPORLO.SUB_NUM > 0) {
                for (WS_CNTA = 1; WS_CNTA <= BPCPORLO.SUB_NUM; WS_CNTA += 1) {
                    WS_OCCURS_BR[WS_CNTA-1] = BPCPORLO.SUB_BR_DATA[WS_CNTA-1].SUB_BR;
                    CEP.TRC(SCCGWA, WS_OCCURS_BR[WS_CNTA-1]);
                }
            } else {
                CEP.TRC(SCCGWA, BPCPQORG.SUPR_BR);
                BPCPORLO.BR = BPCPQORG.SUPR_BR;
                S000_CALL_BPZPORLO();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCPORLO.SUB_NUM);
                for (WS_CNTA = 1; WS_CNTA <= BPCPORLO.SUB_NUM; WS_CNTA += 1) {
                    WS_OCCURS_BR[WS_CNTA-1] = BPCPORLO.SUB_BR_DATA[WS_CNTA-1].SUB_BR;
                    CEP.TRC(SCCGWA, WS_OCCURS_BR[WS_CNTA-1]);
                }
            }
            if (BPCPORLO.NEXT_CALL_FLG == 'Y') {
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
    }
    public void B230_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= 50 
            && SCCMPAG.FUNC != 'E'; WS_I += 1) {
            if (WS_DATA[WS_I-1].WS_DATA_CCY.trim().length() > 0) {
                IBS.init(SCCGWA, WS_OUT_LIST);
                WS_OUT_LIST.WS_BR = AIB8530_AWA_8530.BR_START;
                WS_OUT_LIST.WS_STA_CD = AIB8530_AWA_8530.STA_CD;
                WS_OUT_LIST.WS_AC_CNAME = WS_ITM_NAME;
                WS_OUT_LIST.WS_DT = AIB8530_AWA_8530.DT;
                WS_OUT_LIST.WS_DDBAL = WS_DATA[WS_I-1].WS_DATA_AMT;
                WS_OUT_LIST.WS_OUT_CCY = WS_DATA[WS_I-1].WS_DATA_CCY;
                CEP.TRC(SCCGWA, WS_OUT_LIST.WS_BR);
                CEP.TRC(SCCGWA, WS_OUT_LIST.WS_STA_CD);
                CEP.TRC(SCCGWA, WS_OUT_LIST.WS_AC_CNAME);
                CEP.TRC(SCCGWA, WS_OUT_LIST.WS_DT);
                CEP.TRC(SCCGWA, WS_OUT_LIST.WS_DDBAL);
                CEP.TRC(SCCGWA, WS_OUT_LIST.WS_OUT_CCY);
                IBS.init(SCCGWA, SCCMPAG);
                SCCMPAG.FUNC = 'D';
                SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUT_LIST);
                SCCMPAG.DATA_LEN = 213;
                B_MPAG();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_START_BR_PROC() throws IOException,SQLException,Exception {
        AIRMSTT.KEY.GL_BOOK_FLG = "BK001";
        AIRMSTT.KEY.BR = WS_TMP_BR;
        AITMSTT_BR.rp = new DBParm();
        AITMSTT_BR.rp.TableName = "AITMSTT";
        AITMSTT_BR.rp.where = "GL_BOOK_FLG = :AIRMSTT.KEY.GL_BOOK_FLG "
            + "AND ITM_NO = :AIRMSTT.KEY.ITM_NO "
            + "AND BR = :AIRMSTT.KEY.BR";
        AITMSTT_BR.rp.order = "BR,ITM_NO";
        IBS.STARTBR(SCCGWA, AIRMSTT, this, AITMSTT_BR);
    }
    public void T000_START_NOT_BR() throws IOException,SQLException,Exception {
        AIRMSTT.KEY.GL_BOOK_FLG = "BK001";
        AITMSTT_BR.rp = new DBParm();
        AITMSTT_BR.rp.TableName = "AITMSTT";
        AITMSTT_BR.rp.where = "GL_BOOK_FLG = :AIRMSTT.KEY.GL_BOOK_FLG "
            + "AND ITM_NO = :AIRMSTT.KEY.ITM_NO";
        AITMSTT_BR.rp.order = "BR,ITM_NO";
        IBS.STARTBR(SCCGWA, AIRMSTT, this, AITMSTT_BR);
    }
    public void T000_READNEXT_AITMSTT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, AIRMSTT, this, AITMSTT_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_MSTT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSTT_FLG = 'N';
        } else {
        }
    }
    public void T000_ENDBR_AITMSTT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, AITMSTT_BR);
    }
    public void T000_STARTBR_BPTPARM() throws IOException,SQLException,Exception {
        BPTPARM_BR.rp = new DBParm();
        BPTPARM_BR.rp.TableName = "BPTPARM";
        BPTPARM_BR.rp.where = "TYPE = 'PAI15'";
        IBS.STARTBR(SCCGWA, BPRPARM, this, BPTPARM_BR);
    }
    public void T000_READNEXT_BPTPARM() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRPARM, this, BPTPARM_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_READ_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_READ_FLG = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTPARM() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTPARM_BR);
    }
    public void S000_CALL_BPZPORLO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG-LOW", BPCPORLO);
        if (BPCPORLO.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPORLO.RC);
        }
    }
    public void S000_CALL_AIZSSBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-S-AIZS8530", AICSSBAL);
        CEP.TRC(SCCGWA, AICSSBAL.RC);
        if (AICSSBAL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICSSBAL.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INFO, WS_FLD_NO);
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
