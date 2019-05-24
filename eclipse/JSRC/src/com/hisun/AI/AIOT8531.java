package com.hisun.AI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class AIOT8531 {
    int JIBS_tmp_int;
    brParm AITMSTT_BR = new brParm();
    brParm AITITM_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 18;
    int K_MAX_COL = 500;
    String K_CCY_CNY = "156";
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    double WS_TMP_AMT = 0;
    int WS_TMP_BR = 0;
    double WS_TMP_CNYAMT = 0;
    double WS_TMP_FORAMT = 0;
    double WS_TMP_ALLAMT = 0;
    double WS_TOTAL_CNYAMT = 0;
    double WS_TOTAL_FORAMT = 0;
    double WS_TOTAL_ALLAMT = 0;
    double WS_BUY_AMT = 0;
    double WS_SELL_AMT = 0;
    String WS_BUY_CCY = " ";
    String WS_SELL_CCY = " ";
    int WS_LAST_BR = 0;
    int WS_PORLO_BR = 0;
    int WS_CNTA = 0;
    long WS_CNT3 = 0;
    char WS_INQ_FLG = ' ';
    int[] WS_OCCURS_BR = new int[1000];
    AIOT8531_WS_OUT_LIST WS_OUT_LIST = new AIOT8531_WS_OUT_LIST();
    char WS_MSTT_FLG = ' ';
    char WS_ITM_FLG = ' ';
    char WS_OUT_TOTAL = ' ';
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCSUBS SCCSUBS = new SCCSUBS();
    AIRITM AIRITM = new AIRITM();
    AIRMSTT AIRMSTT = new AIRMSTT();
    BPCPORLO BPCPORLO = new BPCPORLO();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCFX BPCFX = new BPCFX();
    String WS_TMP_ITM = " ";
    String WS_TMP_ITM_10 = " ";
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    AIB8530_AWA_8530 AIB8530_AWA_8530;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIOT8531 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB8530_AWA_8530>");
        AIB8530_AWA_8530 = (AIB8530_AWA_8530) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_DATA();
        if (pgmRtn) return;
        B200_TRANS_DATA();
        if (pgmRtn) return;
    }
    public void B100_CHECK_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIB8530_AWA_8530.BR);
        CEP.TRC(SCCGWA, AIB8530_AWA_8530.DT);
        if (AIB8530_AWA_8530.BR == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_INPUT_ERROR;
            WS_FLD_NO = AIB8530_AWA_8530.BR_START_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = AIB8530_AWA_8530.BR;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPQORG.LVL);
            if (BPCPQORG.LVL == '7' 
                || BPCPQORG.LVL == '8' 
                || BPCPQORG.LVL == '9') {
                WS_INQ_FLG = '0';
            } else {
                if (BPCPQORG.LVL == '2' 
                    || BPCPQORG.LVL == '3') {
                    WS_INQ_FLG = '2';
                } else {
                    if (BPCPQORG.LVL == '4' 
                        || BPCPQORG.LVL == '5' 
                        || BPCPQORG.LVL == '6') {
                        WS_INQ_FLG = '1';
                    }
                }
            }
        }
        if (AIB8530_AWA_8530.DT != SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_INPUT_ERROR;
            WS_FLD_NO = AIB8530_AWA_8530.DT_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B200_TRANS_DATA() throws IOException,SQLException,Exception {
        WS_OUT_TOTAL = ' ';
        WS_TOTAL_CNYAMT = 0;
        WS_TOTAL_FORAMT = 0;
        WS_TOTAL_ALLAMT = 0;
        IBS.init(SCCGWA, AIRITM);
        WS_ITM_FLG = ' ';
        AIRITM.KEY.COA_FLG = "1";
        AIRITM.TYPE = "5";
        AIRITM.ITM_LVL = '1';
        T000_STARTBR_AITITM();
        if (pgmRtn) return;
        T000_READNEXT_AITITM();
        if (pgmRtn) return;
        if (WS_ITM_FLG == 'Y') {
            B210_OUT_TITLE();
            if (pgmRtn) return;
        }
        while (WS_ITM_FLG != 'N') {
            WS_TMP_CNYAMT = 0;
            WS_TMP_FORAMT = 0;
            WS_TMP_ALLAMT = 0;
            CEP.TRC(SCCGWA, AIRITM.KEY.NO);
            CEP.TRC(SCCGWA, WS_INQ_FLG);
            B220_INCLUDE_BR_RELATION();
            if (pgmRtn) return;
            if (WS_INQ_FLG == '1') {
                CEP.TRC(SCCGWA, BPCPORLO.SUB_NUM);
                for (WS_CNTA = 1; WS_CNTA <= BPCPORLO.SUB_NUM; WS_CNTA += 1) {
                    CEP.TRC(SCCGWA, WS_CNTA);
                    WS_TMP_BR = WS_OCCURS_BR[WS_CNTA-1];
                    CEP.TRC(SCCGWA, WS_TMP_BR);
                    IBS.init(SCCGWA, AIRMSTT);
                    WS_MSTT_FLG = ' ';
                    T000_START_BR_PROC();
                    if (pgmRtn) return;
                    T000_READNEXT_AITMSTT();
                    if (pgmRtn) return;
                    while (WS_MSTT_FLG != 'N' 
                        && SCCMPAG.FUNC != 'E') {
                        if (AIRMSTT.KEY.ITM_NO == null) AIRMSTT.KEY.ITM_NO = "";
                        JIBS_tmp_int = AIRMSTT.KEY.ITM_NO.length();
                        for (int i=0;i<10-JIBS_tmp_int;i++) AIRMSTT.KEY.ITM_NO += " ";
                        if (AIRMSTT.KEY.ITM_NO.substring(9 - 1, 9 + 2 - 1).trim().length() > 0) {
                            B020_02_GET_DATA();
                            if (pgmRtn) return;
                        }
                        T000_READNEXT_AITMSTT();
                        if (pgmRtn) return;
                    }
                    T000_ENDBR_AITMSTT();
                    if (pgmRtn) return;
                }
            } else {
                IBS.init(SCCGWA, AIRMSTT);
                WS_MSTT_FLG = ' ';
                if (WS_INQ_FLG == '0') {
                    T000_START_NOT_BR();
                    if (pgmRtn) return;
                } else {
                    WS_TMP_BR = AIB8530_AWA_8530.BR;
                    T000_START_BR_PROC();
                    if (pgmRtn) return;
                }
                T000_READNEXT_AITMSTT();
                if (pgmRtn) return;
                while (WS_MSTT_FLG != 'N' 
                    && SCCMPAG.FUNC != 'E') {
                    if (AIRMSTT.KEY.ITM_NO == null) AIRMSTT.KEY.ITM_NO = "";
                    JIBS_tmp_int = AIRMSTT.KEY.ITM_NO.length();
                    for (int i=0;i<10-JIBS_tmp_int;i++) AIRMSTT.KEY.ITM_NO += " ";
                    if (AIRMSTT.KEY.ITM_NO.substring(9 - 1, 9 + 2 - 1).trim().length() > 0) {
                        B020_02_GET_DATA();
                        if (pgmRtn) return;
                    }
                    T000_READNEXT_AITMSTT();
                    if (pgmRtn) return;
                }
                T000_ENDBR_AITMSTT();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, WS_TMP_CNYAMT);
            CEP.TRC(SCCGWA, WS_TMP_FORAMT);
            CEP.TRC(SCCGWA, WS_TMP_ALLAMT);
            if (AIRITM.BAL_SIGN_FLG == 'D') {
                WS_TOTAL_CNYAMT = WS_TOTAL_CNYAMT - WS_TMP_CNYAMT;
                WS_TOTAL_FORAMT = WS_TOTAL_FORAMT - WS_TMP_FORAMT;
                WS_TOTAL_ALLAMT = WS_TOTAL_ALLAMT - WS_TMP_ALLAMT;
            } else {
                WS_TOTAL_CNYAMT = WS_TOTAL_CNYAMT + WS_TMP_CNYAMT;
                WS_TOTAL_FORAMT = WS_TOTAL_FORAMT + WS_TMP_FORAMT;
                WS_TOTAL_ALLAMT = WS_TOTAL_ALLAMT + WS_TMP_ALLAMT;
            }
            CEP.TRC(SCCGWA, WS_TOTAL_CNYAMT);
            CEP.TRC(SCCGWA, WS_TOTAL_FORAMT);
            CEP.TRC(SCCGWA, WS_TOTAL_ALLAMT);
            B020_03_OUT_DATA();
            if (pgmRtn) return;
            T000_READNEXT_AITITM();
            if (pgmRtn) return;
        }
        if (WS_ITM_FLG == 'N') {
            WS_OUT_TOTAL = 'Y';
            B020_03_OUT_DATA();
            if (pgmRtn) return;
        }
        T000_ENDBR_AITITM();
        if (pgmRtn) return;
    }
    public void B210_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 242;
        CEP.TRC(SCCGWA, SCCMPAG.MAX_COL_NO);
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_MAX_COL;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B220_INCLUDE_BR_RELATION() throws IOException,SQLException,Exception {
        if (WS_INQ_FLG == '1') {
            IBS.init(SCCGWA, BPCPORLO);
            BPCPORLO.BNK = SCCGWA.COMM_AREA.TR_BANK;
            BPCPORLO.BR = AIB8530_AWA_8530.BR;
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
    public void B020_02_GET_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIRMSTT.KEY.ITM_NO);
        CEP.TRC(SCCGWA, WS_TMP_ALLAMT);
        if (AIRITM.BAL_SIGN_FLG == 'D') {
            WS_TMP_AMT = AIRMSTT.CDDRAMT - AIRMSTT.CDCRAMT;
        } else {
            WS_TMP_AMT = AIRMSTT.CDCRAMT - AIRMSTT.CDDRAMT;
        }
        if (AIRMSTT.CCY.equalsIgnoreCase(K_CCY_CNY)) {
            WS_TMP_CNYAMT += WS_TMP_AMT;
        } else {
            if (WS_TMP_AMT != 0) {
                WS_BUY_AMT = WS_TMP_AMT;
                WS_BUY_CCY = AIRMSTT.CCY;
                WS_SELL_CCY = K_CCY_CNY;
                R000_TRAN_CNYAMT();
                if (pgmRtn) return;
                WS_TMP_FORAMT += WS_SELL_AMT;
            }
        }
        CEP.TRC(SCCGWA, WS_TMP_CNYAMT);
        CEP.TRC(SCCGWA, WS_TMP_FORAMT);
        WS_TMP_ALLAMT = WS_TMP_CNYAMT + WS_TMP_FORAMT;
        CEP.TRC(SCCGWA, WS_TMP_ALLAMT);
    }
    public void B020_03_OUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_LIST);
        if (WS_OUT_TOTAL != 'Y') {
            WS_OUT_LIST.WS_BR = AIB8530_AWA_8530.BR;
            WS_OUT_LIST.WS_ITM_NO = AIRITM.KEY.NO;
            WS_OUT_LIST.WS_ITM_CHS_NM = AIRITM.CHS_NM;
            WS_OUT_LIST.WS_CNY_AMT = WS_TMP_CNYAMT;
            WS_OUT_LIST.WS_FOR_AMT = WS_TMP_FORAMT;
            WS_OUT_LIST.WS_ALL_AMT = WS_TMP_ALLAMT;
            WS_OUT_LIST.WS_DT = AIB8530_AWA_8530.DT;
        } else {
            WS_OUT_LIST.WS_BR = AIB8530_AWA_8530.BR;
            WS_OUT_LIST.WS_DT = AIB8530_AWA_8530.DT;
            WS_OUT_LIST.WS_CNY_AMT = WS_TOTAL_CNYAMT;
            WS_OUT_LIST.WS_FOR_AMT = WS_TOTAL_FORAMT;
            WS_OUT_LIST.WS_ALL_AMT = WS_TOTAL_ALLAMT;
        }
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUT_LIST);
        SCCMPAG.DATA_LEN = 242;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_TRAN_CNYAMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFX);
        BPCFX.FUNC = '3';
        BPCFX.CHNL = SCCGWA.COMM_AREA.CHNL;
        BPCFX.EXR_TYPE = "MDR";
        BPCFX.BR = AIB8530_AWA_8530.BR;
        BPCFX.BUY_CCY = WS_BUY_CCY;
        BPCFX.BUY_AMT = WS_BUY_AMT;
        BPCFX.SELL_CCY = WS_SELL_CCY;
        S000_LINK_BPZSFX();
        if (pgmRtn) return;
        WS_SELL_AMT = BPCFX.SELL_AMT;
    }
    public void T000_START_BR_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_TMP_BR);
        AIRMSTT.KEY.GL_BOOK_FLG = "BK001";
        if (AIRITM.KEY.NO == null) AIRITM.KEY.NO = "";
        JIBS_tmp_int = AIRITM.KEY.NO.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) AIRITM.KEY.NO += " ";
        if (WS_TMP_ITM_10 == null) WS_TMP_ITM_10 = "";
        JIBS_tmp_int = WS_TMP_ITM_10.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) WS_TMP_ITM_10 += " ";
        WS_TMP_ITM_10 = AIRITM.KEY.NO.substring(0, 4) + WS_TMP_ITM_10.substring(4);
        if (WS_TMP_ITM_10 == null) WS_TMP_ITM_10 = "";
        JIBS_tmp_int = WS_TMP_ITM_10.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) WS_TMP_ITM_10 += " ";
        WS_TMP_ITM_10 = WS_TMP_ITM_10.substring(0, 5 - 1) + "______" + WS_TMP_ITM_10.substring(5 + 6 - 1);
        CEP.TRC(SCCGWA, WS_TMP_ITM_10);
        AIRMSTT.KEY.BR = WS_TMP_BR;
        AITMSTT_BR.rp = new DBParm();
        AITMSTT_BR.rp.TableName = "AITMSTT";
        AITMSTT_BR.rp.where = "GL_BOOK_FLG = :AIRMSTT.KEY.GL_BOOK_FLG "
            + "AND BR = :AIRMSTT.KEY.BR "
            + "AND ITM_NO LIKE :WS_TMP_ITM_10";
        AITMSTT_BR.rp.order = "BR,ITM_NO";
        IBS.STARTBR(SCCGWA, AIRMSTT, this, AITMSTT_BR);
    }
    public void T000_START_NOT_BR() throws IOException,SQLException,Exception {
        AIRMSTT.KEY.GL_BOOK_FLG = "BK001";
        if (AIRITM.KEY.NO == null) AIRITM.KEY.NO = "";
        JIBS_tmp_int = AIRITM.KEY.NO.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) AIRITM.KEY.NO += " ";
        if (WS_TMP_ITM_10 == null) WS_TMP_ITM_10 = "";
        JIBS_tmp_int = WS_TMP_ITM_10.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) WS_TMP_ITM_10 += " ";
        WS_TMP_ITM_10 = AIRITM.KEY.NO.substring(0, 4) + WS_TMP_ITM_10.substring(4);
        if (WS_TMP_ITM_10 == null) WS_TMP_ITM_10 = "";
        JIBS_tmp_int = WS_TMP_ITM_10.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) WS_TMP_ITM_10 += " ";
        WS_TMP_ITM_10 = WS_TMP_ITM_10.substring(0, 5 - 1) + "______" + WS_TMP_ITM_10.substring(5 + 6 - 1);
        CEP.TRC(SCCGWA, WS_TMP_ITM_10);
        AITMSTT_BR.rp = new DBParm();
        AITMSTT_BR.rp.TableName = "AITMSTT";
        AITMSTT_BR.rp.where = "GL_BOOK_FLG = :AIRMSTT.KEY.GL_BOOK_FLG "
            + "AND ITM_NO LIKE :WS_TMP_ITM_10";
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
    public void T000_STARTBR_AITITM() throws IOException,SQLException,Exception {
        AITITM_BR.rp = new DBParm();
        AITITM_BR.rp.TableName = "AITITM";
        AITITM_BR.rp.where = "COA_FLG = :AIRITM.KEY.COA_FLG "
            + "AND TYPE = :AIRITM.TYPE "
            + "AND ITM_LVL = :AIRITM.ITM_LVL";
        AITITM_BR.rp.order = "COA_FLG,TYPE,NO";
        IBS.STARTBR(SCCGWA, AIRITM, this, AITITM_BR);
    }
    public void T000_READNEXT_AITITM() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, AIRITM, this, AITITM_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ITM_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ITM_FLG = 'N';
        } else {
        }
    }
    public void T000_ENDBR_AITITM() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, AITITM_BR);
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
    public void S000_CALL_BPZPORLO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG-LOW", BPCPORLO);
        if (BPCPORLO.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPORLO.RC);
        }
    }
    public void S000_LINK_BPZSFX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-FX", BPCFX);
        if (BPCFX.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFX.RC);
            Z_RET();
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
