package com.hisun.AI;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.DD.*;
import com.hisun.IB.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class AIZEODEW {
    OVWA_VCH_AREA VCH_AREA;
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm AITGRP_RD;
    DBParm AITODE_RD;
    boolean pgmRtn = false;
    String K_CMP_INQ_CAL_CODE = "BP-P-INQ-CAL-CODE";
    String K_CMP_MUL_WORK_DAY = "BP-P-MUL-WORK-DAY";
    String K_CMP_UPDATE_CALE = "BP-P-UPDATE-CALE ";
    String K_CMP_MAIN_BPTEXUPF = "BP-R-MAINT-UPDATA";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG";
    int K_MAX_COL = 99;
    int K_MAX_ROW = 99;
    int K_MAX_ENTRY = 400;
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    int WS_REC_CNT = 0;
    int WS_GL_CNT = 0;
    int WS_DD_CNT = 0;
    int WS_IB_CNT = 0;
    int WS_IA_CNT = 0;
    int WS_TOT_CNT = 0;
    short WS_VWA_CNT = 0;
    String WS_CAL_CODE = " ";
    int WS_EFF_DATE = 0;
    int WS_EXP_DATE = 0;
    String WS_BOOK_FLG = " ";
    String WS_ITM = " ";
    char WS_GRP_AUTH_LVL = ' ';
    char WS_ENT_AUTH_LVL = ' ';
    double WS_ODE_TX_AMT = 0;
    double WS_T_AMT = 0;
    int WS_ODE_CNT = 0;
    AIZEODEW_WS_EX_ODE_DATA[] WS_EX_ODE_DATA = new AIZEODEW_WS_EX_ODE_DATA[500];
    AIZEODEW_WS_OUT_ODE_291 WS_OUT_ODE_291 = new AIZEODEW_WS_OUT_ODE_291();
    AIZEODEW_WS_OUT_ODE_300 WS_OUT_ODE_300 = new AIZEODEW_WS_OUT_ODE_300();
    AIZEODEW_WS_OUT_ODE_301 WS_OUT_ODE_301 = new AIZEODEW_WS_OUT_ODE_301();
    AIZEODEW_WS_OUT_ODE_293 WS_OUT_ODE_293 = new AIZEODEW_WS_OUT_ODE_293();
    AIZEODEW_WS_OUT_ODE_295 WS_OUT_ODE_295 = new AIZEODEW_WS_OUT_ODE_295();
    AIZEODEW_WS_IA_AC[] WS_IA_AC = new AIZEODEW_WS_IA_AC[500];
    char WS_STOP = ' ';
    char WS_CHK_HOL = ' ';
    char WS_CHK_CALE_TBL = ' ';
    char WS_HOLIDAY_FLG = ' ';
    char WS_DIFF_FLG = ' ';
    char WS_DD_BACK_FLG = ' ';
    char WS_IB_BACK_FLG = ' ';
    String WS_PROD_AP = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    AICX660 AICX660 = new AICX660();
    BPCUCALE BPCUCALE = new BPCUCALE();
    BPCOQCAL BPCOQCAL = new BPCOQCAL();
    BPCEXCHK BPCEXCHK = new BPCEXCHK();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCRMUPD BPCRMUPD = new BPCRMUPD();
    BPREXUPF BPREXUPF = new BPREXUPF();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    AICPQITM AICPQITM = new AICPQITM();
    AIRPAI2 AIRPAI2 = new AIRPAI2();
    BPCOVAWR BPCOVAWR = new BPCOVAWR();
    BPCPEBAS BPCPEBAS = new BPCPEBAS();
    BPCOVABS BPCOVABS = new BPCOVABS();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    IBCPOSTA IBCPOSTA = new IBCPOSTA();
    AICUUPIA AICUUPIA = new AICUUPIA();
    AICUIANO AICUIANO = new AICUIANO();
    AICPQMIB AICPQMIB = new AICPQMIB();
    AICOCKVH AICOCKVH = new AICOCKVH();
    BPRVWA BPROVWA = new BPRVWA();
    IBCQINF IBCQINF = new IBCQINF();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCMSG SCCMSG = new SCCMSG();
    AIRGRP AIRGRP = new AIRGRP();
    AIRODE AIRODE = new AIRODE();
    SCCGWA SCCGWA;
    BPCEXAPV BPCEXAPV;
    public AIZEODEW() {
        for (int i=0;i<500;i++) WS_EX_ODE_DATA[i] = new AIZEODEW_WS_EX_ODE_DATA();
        for (int i=0;i<500;i++) WS_IA_AC[i] = new AIZEODEW_WS_IA_AC();
    }
    public void MP(SCCGWA SCCGWA, BPCEXAPV BPCEXAPV) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCEXAPV = BPCEXAPV;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZEODEW return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCEXAPV.RC);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHK_INPUT_DATA();
        if (pgmRtn) return;
        B200_BRW_EXUPF_FOR_CHK();
        if (pgmRtn) return;
        if (WS_CHK_HOL == 'F') {
            IBS.CPY2CLS(SCCGWA, BPCEXCHK.RC.RC_CODE+"", BPCEXAPV.RC);
            BPCEXAPV.RC.ERR_INF = BPCEXCHK.RC.ERR_INF;
            Z_RET();
            if (pgmRtn) return;
        }
        if (WS_ODE_CNT > 0 
            && BPCEXAPV.EXCEL_TYPE.equalsIgnoreCase("03")) {
            B300_UPDATE_CALE_TABLE();
            if (pgmRtn) return;
        }
    }
    public void B100_CHK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "AAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        CEP.TRC(SCCGWA, BPCEXAPV.RC);
        if (!BPCEXAPV.EXCEL_TYPE.equalsIgnoreCase("03") 
            && !BPCEXAPV.EXCEL_TYPE.equalsIgnoreCase("X3")) {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_UP_NOT_ODE_ENT, BPCEXAPV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCEXAPV.BATCH_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BATCH_NO_EMPTY, BPCEXAPV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
    }
    public void B200_BRW_EXUPF_FOR_CHK() throws IOException,SQLException,Exception {
        WS_CHK_HOL = 'P';
        IBS.init(SCCGWA, BPREXUPF);
        IBS.init(SCCGWA, BPCRMUPD);
        BPREXUPF.KEY.BATCH_NO = BPCEXAPV.BATCH_NO;
        BPCRMUPD.INFO.POINTER = BPREXUPF;
        BPCRMUPD.INFO.LEN = 183;
        BPCRMUPD.INFO.FUNC = 'B';
        BPCRMUPD.INFO.OPT = 'S';
        BPREXUPF.KEY.BATCH_NO = BPCEXAPV.BATCH_NO;
        CEP.TRC(SCCGWA, BPCEXAPV.BATCH_NO);
        S000_CALL_BPZRMUPD();
        if (pgmRtn) return;
        WS_STOP = 'N';
        WS_REC_CNT = 0;
        WS_ODE_CNT = 0;
        IBS.init(SCCGWA, BPROVWA.BASIC_AREA);
        BPROVWA.BASIC_AREA.CNT = 0;
        VCH_AREA = new OVWA_VCH_AREA();
        BPROVWA.VCH_AREA.add(VCH_AREA);
        while (WS_STOP != 'Y') {
            BPCRMUPD.INFO.FUNC = 'B';
            BPCRMUPD.INFO.OPT = 'N';
            S000_CALL_BPZRMUPD();
            if (pgmRtn) return;
            if (BPCRMUPD.RETURN_INFO == 'N') {
                WS_STOP = 'Y';
            } else {
                if (BPCRMUPD.RETURN_INFO == 'F') {
                    if (BPREXUPF.KEY.BATCH_NO.equalsIgnoreCase(BPCEXAPV.BATCH_NO)) {
                        WS_REC_CNT = WS_REC_CNT + 1;
                        if (BPREXUPF.APPV_FLAG == 'A') {
                            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_ACT_DUP, BPCEXAPV.RC);
                            Z_RET();
                            if (pgmRtn) return;
                        } else {
                            B200_01_CHK_REC_VALID();
                            if (pgmRtn) return;
                        }
                        if (WS_CHK_HOL == 'F') {
                            WS_STOP = 'Y';
                        }
                    } else {
                        WS_STOP = 'Y';
                    }
                } else {
                    WS_STOP = 'Y';
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_READ_BPTEXUPF_ERR, BPCEXAPV.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
        }
        if (WS_REC_CNT == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_UP_DATA_EMPTY, BPCEXAPV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPROVWA.BASIC_AREA.CNT > 0) {
            IBS.init(SCCGWA, AICOCKVH);
            BPROVWA.BASIC_AREA.ODE_FLG = 'Y';
            AICOCKVH.FUNC_CD = 'B';
            AICOCKVH.VCH_PTR = BPROVWA;
            S000_CALL_AIZPCKVH();
            if (pgmRtn) return;
            if (BPROVWA.BASIC_AREA.CNT > K_MAX_ENTRY) {
                IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_EX_MAX_ENTRY, BPCEXAPV.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        BPCRMUPD.INFO.FUNC = 'B';
        BPCRMUPD.INFO.OPT = 'E';
        S000_CALL_BPZRMUPD();
        if (pgmRtn) return;
    }
    public void B200_01_CHK_REC_VALID() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCEXCHK);
        BPCEXCHK.EXCEL_TYPE = BPCEXAPV.EXCEL_TYPE;
        BPCEXCHK.EXCEL_DATA = BPREXUPF.RECORD;
        BPCEXCHK.EXCEL_BATNO = BPREXUPF.KEY.BATCH_NO;
        if (BPREXUPF.RECORD == null) BPREXUPF.RECORD = "";
        JIBS_tmp_int = BPREXUPF.RECORD.length();
        for (int i=0;i<1000-JIBS_tmp_int;i++) BPREXUPF.RECORD += " ";
        CEP.TRC(SCCGWA, BPREXUPF.RECORD.substring(153 - 1, 153 + 12 - 1));
        S000_CALL_AIZEODEL();
        if (pgmRtn) return;
        if (BPCEXCHK.DATA_FLG == '0') {
            WS_CHK_HOL = 'F';
        } else {
            WS_ODE_CNT = WS_ODE_CNT + 1;
            IBS.CPY2CLS(SCCGWA, BPREXUPF.RECORD, WS_EX_ODE_DATA[WS_ODE_CNT-1]);
            CEP.TRC(SCCGWA, WS_EX_ODE_DATA[WS_ODE_CNT-1]);
            CEP.TRC(SCCGWA, WS_EX_ODE_DATA[WS_ODE_CNT-1].WS_GL_BOOK);
            CEP.TRC(SCCGWA, WS_EX_ODE_DATA[WS_ODE_CNT-1].WS_VALUE_DATE);
            CEP.TRC(SCCGWA, WS_EX_ODE_DATA[WS_ODE_CNT-1].WS_ENTRY_TYPE);
            CEP.TRC(SCCGWA, WS_EX_ODE_DATA[WS_ODE_CNT-1].WS_BCH);
            CEP.TRC(SCCGWA, WS_EX_ODE_DATA[WS_ODE_CNT-1].WS_CUR);
            CEP.TRC(SCCGWA, WS_EX_ODE_DATA[WS_ODE_CNT-1].WS_ITM_NO);
            CEP.TRC(SCCGWA, WS_EX_ODE_DATA[WS_ODE_CNT-1].WS_AC);
            CEP.TRC(SCCGWA, WS_EX_ODE_DATA[WS_ODE_CNT-1].WS_ACTION_CODE);
            CEP.TRC(SCCGWA, WS_EX_ODE_DATA[WS_ODE_CNT-1].WS_RED_FLG);
            CEP.TRC(SCCGWA, WS_EX_ODE_DATA[WS_ODE_CNT-1].WS_AMOUNT);
            CEP.TRC(SCCGWA, WS_EX_ODE_DATA[WS_ODE_CNT-1].WS_TLT_ID);
            CEP.TRC(SCCGWA, WS_EX_ODE_DATA[WS_ODE_CNT-1].WS_REV_NO);
            CEP.TRC(SCCGWA, WS_EX_ODE_DATA[WS_ODE_CNT-1].WS_NARR_CD);
            CEP.TRC(SCCGWA, WS_EX_ODE_DATA[WS_ODE_CNT-1].WS_TRN_DESC);
            CEP.TRC(SCCGWA, WS_EX_ODE_DATA[WS_ODE_CNT-1].WS_TRN_DESC);
            CEP.TRC(SCCGWA, WS_EX_ODE_DATA[WS_ODE_CNT-1].WS_REF_NO);
            CEP.TRC(SCCGWA, WS_EX_ODE_DATA[WS_ODE_CNT-1].WS_PROD_TYPE);
            CEP.TRC(SCCGWA, WS_EX_ODE_DATA[WS_ODE_CNT-1].WS_ANS_CD1);
            CEP.TRC(SCCGWA, WS_EX_ODE_DATA[WS_ODE_CNT-1].WS_ANS_CD2);
            CEP.TRC(SCCGWA, WS_EX_ODE_DATA[WS_ODE_CNT-1].WS_ANS_CD3);
            CEP.TRC(SCCGWA, WS_EX_ODE_DATA[WS_ODE_CNT-1].WS_ANS_CD4);
            CEP.TRC(SCCGWA, WS_EX_ODE_DATA[WS_ODE_CNT-1].WS_ANS_CD5);
            CEP.TRC(SCCGWA, WS_EX_ODE_DATA[WS_ODE_CNT-1].WS_ANS_CD6);
            CEP.TRC(SCCGWA, WS_EX_ODE_DATA[WS_ODE_CNT-1].WS_ANS_CD7);
            CEP.TRC(SCCGWA, WS_EX_ODE_DATA[WS_ODE_CNT-1].WS_ANS_CD8);
            CEP.TRC(SCCGWA, WS_EX_ODE_DATA[WS_ODE_CNT-1].WS_ANS_CD9);
            CEP.TRC(SCCGWA, WS_EX_ODE_DATA[WS_ODE_CNT-1].WS_ANS_CD10);
            if (WS_EX_ODE_DATA[WS_ODE_CNT-1].WS_ENTRY_TYPE == 'I') {
                if (WS_EX_ODE_DATA[WS_ODE_CNT-1].WS_AC.trim().length() == 0) {
                    if (WS_EX_ODE_DATA[WS_ODE_CNT-1].WS_BCH == ' ' 
                        || WS_EX_ODE_DATA[WS_ODE_CNT-1].WS_CUR.trim().length() == 0 
                        || WS_EX_ODE_DATA[WS_ODE_CNT-1].WS_ITM_NO.trim().length() == 0 
                        || WS_EX_ODE_DATA[WS_ODE_CNT-1].WS_BR_SEQ == 0) {
                        IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_AC_MUST_INPUT, BPCEXAPV.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    }
                    IBS.init(SCCGWA, WS_IA_AC[WS_ODE_CNT-1]);
                    WS_IA_AC[WS_ODE_CNT-1].WS_IA_BR = WS_EX_ODE_DATA[WS_ODE_CNT-1].WS_BCH;
                    WS_IA_AC[WS_ODE_CNT-1].WS_IA_CCY = WS_EX_ODE_DATA[WS_ODE_CNT-1].WS_CUR;
                    WS_IA_AC[WS_ODE_CNT-1].WS_IA_ITM = WS_EX_ODE_DATA[WS_ODE_CNT-1].WS_ITM_NO;
                    WS_IA_AC[WS_ODE_CNT-1].WS_IA_SEQ = WS_EX_ODE_DATA[WS_ODE_CNT-1].WS_BR_SEQ;
                    WS_EX_ODE_DATA[WS_ODE_CNT-1].WS_AC = IBS.CLS2CPY(SCCGWA, WS_IA_AC[WS_ODE_CNT-1]);
                } else {
                    IBS.CPY2CLS(SCCGWA, WS_EX_ODE_DATA[WS_ODE_CNT-1].WS_AC, WS_IA_AC[WS_ODE_CNT-1]);
                }
                WS_EX_ODE_DATA[WS_ODE_CNT-1].WS_BCH = WS_IA_AC[WS_ODE_CNT-1].WS_IA_BR;
                BPROVWA.BASIC_AREA.CNT += 1;
                VCH_AREA = new OVWA_VCH_AREA();
                BPROVWA.VCH_AREA.add(VCH_AREA);
                IBS.init(SCCGWA, VCH_AREA.PARTB);
                VCH_AREA.CONTROL.AC_FLG = 'I';
                VCH_AREA.PARTB.BOOK_FLG = WS_EX_ODE_DATA[WS_ODE_CNT-1].WS_GL_BOOK;
                VCH_AREA.PARTB.AC_NO = WS_EX_ODE_DATA[WS_ODE_CNT-1].WS_AC;
                VCH_AREA.PARTB.BR = WS_IA_AC[WS_ODE_CNT-1].WS_IA_BR;
                VCH_AREA.PARTB.ITM = WS_IA_AC[WS_ODE_CNT-1].WS_IA_ITM;
                VCH_AREA.PARTB.RVS_NO = WS_EX_ODE_DATA[WS_ODE_CNT-1].WS_REV_NO;
                VCH_AREA.PARTB.CCY = WS_IA_AC[WS_ODE_CNT-1].WS_IA_CCY;
                VCH_AREA.PARTB.SIGN = WS_EX_ODE_DATA[WS_ODE_CNT-1].WS_ACTION_CODE;
                if (WS_EX_ODE_DATA[WS_ODE_CNT-1].WS_AMOUNT.trim().length() == 0) VCH_AREA.PARTB.AMT = 0;
                else VCH_AREA.PARTB.AMT = Double.parseDouble(WS_EX_ODE_DATA[WS_ODE_CNT-1].WS_AMOUNT);
                VCH_AREA.PARTB.VAL_DATE = WS_EX_ODE_DATA[WS_ODE_CNT-1].WS_VALUE_DATE;
            }
        }
    }
    public void B300_UPDATE_CALE_TABLE() throws IOException,SQLException,Exception {
        WS_DD_BACK_FLG = 'N';
        WS_IB_BACK_FLG = 'N';
        WS_DIFF_FLG = 'N';
        for (WS_I = 1; WS_I <= WS_ODE_CNT; WS_I += 1) {
            if (WS_EX_ODE_DATA[WS_I-1].WS_BCH == ' ') {
                WS_EX_ODE_DATA[WS_I-1].WS_BCH = 0;
            }
            B300_01_ADD_CNT();
            if (pgmRtn) return;
        }
        B300_02_CHECK_GROUP();
        if (pgmRtn) return;
        B300_03_GET_GRP_SEQ();
        if (pgmRtn) return;
        R00_GET_DD_LVL();
        if (pgmRtn) return;
        B300_06_GEN_VWA_HEAD();
        if (pgmRtn) return;
        for (WS_I = 1; WS_I <= WS_ODE_CNT; WS_I += 1) {
            B300_05_ADD_ODE();
            if (pgmRtn) return;
            B300_07_ODE_POST();
            if (pgmRtn) return;
        }
        B300_04_ADD_GROUP();
        if (pgmRtn) return;
        B300_08_OUTPUT();
        if (pgmRtn) return;
    }
    public void B300_01_ADD_CNT() throws IOException,SQLException,Exception {
        if (WS_EX_ODE_DATA[WS_I-1].WS_ENTRY_TYPE == 'G') {
            WS_GL_CNT += 1;
        }
        if (WS_EX_ODE_DATA[WS_I-1].WS_ENTRY_TYPE == 'I') {
            WS_IA_CNT += 1;
        }
        if (WS_EX_ODE_DATA[WS_I-1].WS_ENTRY_TYPE == 'N') {
            WS_IB_CNT += 1;
            if (WS_EX_ODE_DATA[WS_I-1].WS_VALUE_DATE < SCCGWA.COMM_AREA.AC_DATE) {
                WS_IB_BACK_FLG = 'Y';
            }
        }
        if (WS_EX_ODE_DATA[WS_I-1].WS_ENTRY_TYPE == 'D') {
            WS_DD_CNT += 1;
            if (WS_EX_ODE_DATA[WS_I-1].WS_VALUE_DATE < SCCGWA.COMM_AREA.AC_DATE) {
                WS_DD_BACK_FLG = 'Y';
            }
        }
        if (WS_I < WS_ODE_CNT) {
            if (WS_EX_ODE_DATA[WS_I-1].WS_VALUE_DATE != WS_EX_ODE_DATA[WS_I + 1-1].WS_VALUE_DATE) {
                WS_DIFF_FLG = 'Y';
            }
        }
    }
    public void B300_02_CHECK_GROUP() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_IB_CNT);
        CEP.TRC(SCCGWA, WS_DD_CNT);
        CEP.TRC(SCCGWA, WS_GL_CNT);
        WS_TOT_CNT = WS_IB_CNT + WS_DD_CNT;
        if (WS_TOT_CNT > 40) {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_DDIB_CNT_EX, BPCEXAPV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_TOT_CNT = WS_GL_CNT + WS_IB_CNT + WS_DD_CNT + WS_IA_CNT;
        if (WS_TOT_CNT > 400) {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_EX_MAX_ENTRY, BPCEXAPV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B300_03_GET_GRP_SEQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSGSEQ);
        BPCSGSEQ.TYPE = "SEQ";
        BPCSGSEQ.CODE = "AISGRP";
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSGSEQ.RUN_MODE = 'O';
        S000_CALL_BPZSGSEQ();
        if (pgmRtn) return;
    }
    public void B300_04_ADD_GROUP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRGRP);
        AIRGRP.KEY.RECORD_TYPE = 'M';
        AIRGRP.KEY.ISSUING_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, WS_EX_ODE_DATA[1-1].WS_TLT_ID);
        AIRGRP.KEY.TLT_ID = WS_EX_ODE_DATA[1-1].WS_TLT_ID;
        AIRGRP.KEY.GRPNO = (int) BPCSGSEQ.SEQ;
        AIRGRP.ISSUING_BRANCH = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AIRGRP.STS = 'A';
        AIRGRP.TEMP_FLG = 'N';
        AIRGRP.MAX_ENTRY_NO = WS_TOT_CNT;
        AIRGRP.ENTR_NUM = WS_TOT_CNT;
        AIRGRP.AUTH_ID = SCCGWA.COMM_AREA.TL_ID;
        AIRGRP.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AIRGRP.UPDATE_TIME = SCCGWA.COMM_AREA.TR_TIME;
        T000_WRITE_AITGRP();
        if (pgmRtn) return;
    }
    public void B300_05_ADD_ODE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRODE);
        WS_ENT_AUTH_LVL = ' ';
        WS_T_AMT = 0;
        AIRODE.KEY.RECORD_TYPE = 'M';
        AIRODE.KEY.ISSUING_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AIRODE.KEY.TLT_ID = WS_EX_ODE_DATA[WS_I-1].WS_TLT_ID;
        AIRODE.KEY.GRPNO = (int) BPCSGSEQ.SEQ;
        AIRODE.KEY.ENTRY_NO = WS_I;
        AIRODE.ENTRY_TYPE = WS_EX_ODE_DATA[WS_I-1].WS_ENTRY_TYPE;
        AIRODE.ENTRY_STS = 'N';
        CEP.TRC(SCCGWA, AIRODE.KEY.ISSUING_DATE);
        CEP.TRC(SCCGWA, AIRODE.KEY.TLT_ID);
        CEP.TRC(SCCGWA, AIRODE.KEY.GRPNO);
        CEP.TRC(SCCGWA, AIRODE.KEY.ENTRY_NO);
        CEP.TRC(SCCGWA, WS_EX_ODE_DATA[WS_I-1].WS_REF_NO);
        CEP.TRC(SCCGWA, WS_EX_ODE_DATA[WS_I-1].WS_ANS_CD10);
        AIRODE.BCH = WS_EX_ODE_DATA[WS_I-1].WS_BCH;
        AIRODE.AC = WS_EX_ODE_DATA[WS_I-1].WS_AC;
        CEP.TRC(SCCGWA, WS_EX_ODE_DATA[WS_I-1].WS_ENTRY_TYPE);
        AIRODE.GL_BOOK_FLG = WS_EX_ODE_DATA[WS_I-1].WS_GL_BOOK;
        CEP.TRC(SCCGWA, WS_IA_AC[WS_I-1]);
        CEP.TRC(SCCGWA, WS_IA_AC[WS_I-1].WS_IA_ITM);
        if (!WS_IA_AC[WS_I-1].WS_IA_ITM.equalsIgnoreCase("0")) {
            AIRODE.ITM_NO = WS_IA_AC[WS_I-1].WS_IA_ITM;
            WS_ITM = WS_IA_AC[WS_I-1].WS_IA_ITM;
            WS_BOOK_FLG = AIRODE.GL_BOOK_FLG;
            R000_GET_INOFF_FLG();
            if (pgmRtn) return;
            if (AICPQITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("8")) {
                AIRODE.INOFF = 'O';
            } else {
                AIRODE.INOFF = 'I';
            }
        } else {
            AIRODE.INOFF = 'I';
        }
        AIRODE.CCY = WS_EX_ODE_DATA[WS_I-1].WS_CUR;
        AIRODE.VALUE_DATE = WS_EX_ODE_DATA[WS_I-1].WS_VALUE_DATE;
        AIRODE.ACTION_CODE = WS_EX_ODE_DATA[WS_I-1].WS_ACTION_CODE;
        if (WS_EX_ODE_DATA[WS_I-1].WS_AMOUNT.trim().length() == 0) WS_T_AMT = 0;
        else WS_T_AMT = Double.parseDouble(WS_EX_ODE_DATA[WS_I-1].WS_AMOUNT);
        AIRODE.AMOUNT = WS_T_AMT;
        AIRODE.NARR_CD = WS_EX_ODE_DATA[WS_I-1].WS_NARR_CD;
        AIRODE.TRN_DESC = WS_EX_ODE_DATA[WS_I-1].WS_TRN_DESC;
        AIRODE.ANS_CD1 = WS_EX_ODE_DATA[WS_I-1].WS_ANS_CD1;
        AIRODE.ANS_CD2 = WS_EX_ODE_DATA[WS_I-1].WS_ANS_CD2;
        AIRODE.ANS_CD3 = WS_EX_ODE_DATA[WS_I-1].WS_ANS_CD3;
        AIRODE.ANS_CD4 = WS_EX_ODE_DATA[WS_I-1].WS_ANS_CD4;
        AIRODE.ANS_CD5 = WS_EX_ODE_DATA[WS_I-1].WS_ANS_CD5;
        AIRODE.ANS_CD6 = WS_EX_ODE_DATA[WS_I-1].WS_ANS_CD6;
        AIRODE.ANS_CD7 = WS_EX_ODE_DATA[WS_I-1].WS_ANS_CD7;
        AIRODE.ANS_CD8 = WS_EX_ODE_DATA[WS_I-1].WS_ANS_CD8;
        AIRODE.ANS_CD9 = WS_EX_ODE_DATA[WS_I-1].WS_ANS_CD9;
        AIRODE.ANS_CD10 = WS_EX_ODE_DATA[WS_I-1].WS_ANS_CD10;
        AIRODE.REF_NO = WS_EX_ODE_DATA[WS_I-1].WS_REF_NO;
        AIRODE.RED_FLG = WS_EX_ODE_DATA[WS_I-1].WS_RED_FLG;
        AIRODE.REV_NO = WS_EX_ODE_DATA[WS_I-1].WS_REV_NO;
        AIRODE.RVS_FLG = WS_EX_ODE_DATA[WS_I-1].WS_RVS_FLG;
        AIRODE.PROD_TYPE = WS_EX_ODE_DATA[WS_I-1].WS_PROD_TYPE;
        AIRODE.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, AIRODE.GL_BOOK_FLG);
        CEP.TRC(SCCGWA, AIRODE.VALUE_DATE);
        CEP.TRC(SCCGWA, AIRODE.ENTRY_TYPE);
        CEP.TRC(SCCGWA, AIRODE.BCH);
        CEP.TRC(SCCGWA, AIRODE.CCY);
        CEP.TRC(SCCGWA, AIRODE.ITM_NO);
        CEP.TRC(SCCGWA, AIRODE.AC);
        CEP.TRC(SCCGWA, AIRODE.CIFNO);
        CEP.TRC(SCCGWA, AIRODE.ACTION_CODE);
        CEP.TRC(SCCGWA, AIRODE.RED_FLG);
        CEP.TRC(SCCGWA, AIRODE.AMOUNT);
        CEP.TRC(SCCGWA, AIRODE.REV_NO);
        CEP.TRC(SCCGWA, AIRODE.NARR_CD);
        CEP.TRC(SCCGWA, AIRODE.TRN_DESC);
        CEP.TRC(SCCGWA, AIRODE.REF_NO);
        CEP.TRC(SCCGWA, AIRODE.PROD_TYPE);
        CEP.TRC(SCCGWA, AIRODE.RVS_FLG);
        if (WS_EX_ODE_DATA[WS_I-1].WS_PROD_TYPE.trim().length() > 0) {
            IBS.init(SCCGWA, BPCPQPRD);
            BPCPQPRD.PRDT_CODE = AIRODE.PROD_TYPE;
            S000_CALL_BPZPQPRD();
            if (pgmRtn) return;
        }
        T000_WRITE_AITODE();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE == 0) {
            AIRODE.CN_TYPE = BPCPQPRD.PRDT_MODEL;
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXAPV.RC);
        }
    }
    public void S000_CALL_AIZPCKVH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-CHK-VCH", AICOCKVH);
        if (AICOCKVH.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICOCKVH.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXAPV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B300_06_GEN_VWA_HEAD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOVABS);
        BPCOVABS.ODE_FLG = 'Y';
        BPCOVABS.ODE_GRP_NO = (int) BPCSGSEQ.SEQ;
        S000_CALL_BPZPVABS();
        if (pgmRtn) return;
        WS_VWA_CNT = 0;
    }
    public void B300_07_ODE_POST() throws IOException,SQLException,Exception {
        if (WS_EX_ODE_DATA[WS_I-1].WS_ENTRY_TYPE == 'G') {
            IBS.init(SCCGWA, BPCOVAWR);
            BPCOVAWR.PARTB.BOOK_FLG = WS_EX_ODE_DATA[WS_I-1].WS_GL_BOOK;
            BPCOVAWR.PARTB.ITM = WS_EX_ODE_DATA[WS_I-1].WS_ITM_NO;
            R00_MOVE_VAWR_DATA();
            if (pgmRtn) return;
            R00_GEN_VWA();
            if (pgmRtn) return;
        }
        if (WS_EX_ODE_DATA[WS_I-1].WS_ENTRY_TYPE == 'I') {
            IBS.init(SCCGWA, BPCOVAWR);
            IBS.init(SCCGWA, AICUIANO);
            AICUIANO.INPUT_DATA.GL_BOOK = WS_EX_ODE_DATA[WS_I-1].WS_GL_BOOK;
            AICUIANO.INPUT_DATA.AC = WS_EX_ODE_DATA[WS_I-1].WS_AC;
            AICUIANO.INPUT_DATA.CCY = WS_EX_ODE_DATA[WS_I-1].WS_CUR;
            S000_CALL_AIZUIANO();
            if (pgmRtn) return;
            BPCOVAWR.PARTB.BOOK_FLG = WS_EX_ODE_DATA[WS_I-1].WS_GL_BOOK;
            BPCOVAWR.PARTB.ITM = AICUIANO.INPUT_DATA.ITM;
            BPCOVAWR.PARTB.MIB_NO = WS_EX_ODE_DATA[WS_I-1].WS_AC;
            BPCOVAWR.PARTB.CCY = WS_EX_ODE_DATA[WS_I-1].WS_CUR;
            BPCOVAWR.PARTB.RVS_NO = WS_EX_ODE_DATA[WS_I-1].WS_REV_NO;
            BPCOVAWR.PARTB.RED_FLG = WS_EX_ODE_DATA[WS_I-1].WS_RED_FLG;
            BPCOVAWR.PARTB.TLR_ID = WS_EX_ODE_DATA[WS_I-1].WS_TLT_ID;
            R00_MOVE_VAWR_DATA();
            if (pgmRtn) return;
            R00_GEN_VWA();
            if (pgmRtn) return;
        }
    }
    public void R000_BEGIN_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = 6;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S00_WRITE_ODE_TSQ_291() throws IOException,SQLException,Exception {
        WS_OUT_ODE_291.WS_O_291_TLT = AIRGRP.KEY.TLT_ID;
        WS_OUT_ODE_291.WS_O_291_ISS = AIRGRP.KEY.ISSUING_DATE;
        WS_OUT_ODE_291.WS_O_291_GRP = AIRGRP.KEY.GRPNO;
        WS_OUT_ODE_291.WS_O_291_LVL = AIRGRP.AUTH_LEVEL;
        SCCMPAG.DATA_LEN = (short) 32000;
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUT_ODE_291);
        SCCMPAG.FUNC = 'D';
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S00_WRITE_ODE_TSQ_293() throws IOException,SQLException,Exception {
        SCCMPAG.DATA_LEN = (short) 50000;
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUT_ODE_293);
        SCCMPAG.FUNC = 'D';
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S00_WRITE_ODE_TSQ_295() throws IOException,SQLException,Exception {
        SCCMPAG.DATA_LEN = (short) 62000;
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUT_ODE_295);
        SCCMPAG.FUNC = 'D';
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S00_WRITE_ODE_TSQ_300() throws IOException,SQLException,Exception {
        SCCMPAG.DATA_LEN = (short) 45000;
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUT_ODE_300);
        SCCMPAG.FUNC = 'D';
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S00_WRITE_ODE_TSQ_301() throws IOException,SQLException,Exception {
        SCCMPAG.DATA_LEN = (short) 38500;
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUT_ODE_301);
        SCCMPAG.FUNC = 'D';
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B300_10_OUTPUT() throws IOException,SQLException,Exception {
        R000_BEGIN_MPAGE_OUTPUT();
        if (pgmRtn) return;
        S00_WRITE_ODE_TSQ_291();
        if (pgmRtn) return;
        S00_WRITE_ODE_TSQ_293();
        if (pgmRtn) return;
        S00_WRITE_ODE_TSQ_295();
        if (pgmRtn) return;
        IBS.CPY2CLS(SCCGWA, ALL, WS_OUT_ODE_295);
        S00_WRITE_ODE_TSQ_295();
        if (pgmRtn) return;
        IBS.init(SCCGWA, WS_OUT_ODE_300);
        IBS.init(SCCGWA, WS_OUT_ODE_301);
        for (WS_I = 1; WS_I <= WS_ODE_CNT; WS_I += 1) {
            WS_OUT_ODE_300.WS_O_300_ENTRY = (short) WS_I;
            WS_OUT_ODE_300.WS_O_300_GLBOOK = WS_EX_ODE_DATA[WS_I-1].WS_GL_BOOK;
            JIBS_tmp_str[0] = "" + WS_EX_ODE_DATA[WS_I-1].WS_VALUE_DATE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (WS_OUT_ODE_300.WS_O_300_VAL == null) WS_OUT_ODE_300.WS_O_300_VAL = "";
            JIBS_tmp_int = WS_OUT_ODE_300.WS_O_300_VAL.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) WS_OUT_ODE_300.WS_O_300_VAL += " ";
            WS_OUT_ODE_300.WS_O_300_VAL = JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1) + WS_OUT_ODE_300.WS_O_300_VAL.substring(2);
            JIBS_tmp_str[0] = "" + WS_EX_ODE_DATA[WS_I-1].WS_VALUE_DATE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (WS_OUT_ODE_300.WS_O_300_VAL == null) WS_OUT_ODE_300.WS_O_300_VAL = "";
            JIBS_tmp_int = WS_OUT_ODE_300.WS_O_300_VAL.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) WS_OUT_ODE_300.WS_O_300_VAL += " ";
            WS_OUT_ODE_300.WS_O_300_VAL = WS_OUT_ODE_300.WS_O_300_VAL.substring(0, 4 - 1) + JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1) + WS_OUT_ODE_300.WS_O_300_VAL.substring(4 + 2 - 1);
            JIBS_tmp_str[0] = "" + WS_EX_ODE_DATA[WS_I-1].WS_VALUE_DATE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (WS_OUT_ODE_300.WS_O_300_VAL == null) WS_OUT_ODE_300.WS_O_300_VAL = "";
            JIBS_tmp_int = WS_OUT_ODE_300.WS_O_300_VAL.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) WS_OUT_ODE_300.WS_O_300_VAL += " ";
            WS_OUT_ODE_300.WS_O_300_VAL = WS_OUT_ODE_300.WS_O_300_VAL.substring(0, 7 - 1) + JIBS_tmp_str[0].substring(0, 4) + WS_OUT_ODE_300.WS_O_300_VAL.substring(7 + 4 - 1);
            WS_OUT_ODE_300.WS_O_300_TY = WS_EX_ODE_DATA[WS_I-1].WS_ENTRY_TYPE;
            WS_OUT_ODE_300.WS_O_300_CUR = WS_EX_ODE_DATA[WS_I-1].WS_CUR;
            WS_OUT_ODE_300.WS_O_300_BR = WS_EX_ODE_DATA[WS_I-1].WS_BCH;
            WS_OUT_ODE_300.WS_O_300_ITM_NO = WS_EX_ODE_DATA[WS_I-1].WS_ITM_NO;
            WS_OUT_ODE_301.WS_O_301_AC = WS_EX_ODE_DATA[WS_I-1].WS_AC;
            WS_OUT_ODE_300.WS_O_300_AMT = WS_EX_ODE_DATA[WS_I-1].WS_AMOUNT;
            WS_OUT_ODE_300.WS_O_300_CODE = WS_EX_ODE_DATA[WS_I-1].WS_ACTION_CODE;
            WS_OUT_ODE_301.WS_O_301_REF = WS_EX_ODE_DATA[WS_I-1].WS_REF_NO;
            S00_WRITE_ODE_TSQ_300();
            if (pgmRtn) return;
            S00_WRITE_ODE_TSQ_301();
            if (pgmRtn) return;
        }
    }
    public void B300_08_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICX660);
        SCCFMT.FMTID = "AI660";
        AICX660.GRP = AIRGRP.KEY.GRPNO;
        AICX660.TLTID = AIRGRP.KEY.TLT_ID;
        AICX660.ISS_DATE = AIRGRP.KEY.ISSUING_DATE;
        AICX660.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        AICX660.UPD_TM = SCCGWA.COMM_AREA.TR_TIME;
        AICX660.ENTRY = (short) WS_ODE_CNT;
        SCCFMT.DATA_PTR = AICX660;
        SCCFMT.DATA_LEN = 48;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R00_MOVE_VAWR_DATA() throws IOException,SQLException,Exception {
        WS_T_AMT = 0;
        BPCOVAWR.PARTB.BR = WS_EX_ODE_DATA[WS_I-1].WS_BCH;
        BPCOVAWR.PARTB.CCY = WS_EX_ODE_DATA[WS_I-1].WS_CUR;
        BPCOVAWR.PARTB.SIGN = WS_EX_ODE_DATA[WS_I-1].WS_ACTION_CODE;
        if (WS_EX_ODE_DATA[WS_I-1].WS_AMOUNT.trim().length() == 0) WS_T_AMT = 0;
        else WS_T_AMT = Double.parseDouble(WS_EX_ODE_DATA[WS_I-1].WS_AMOUNT);
        BPCOVAWR.PARTB.AMT = WS_T_AMT;
        BPCOVAWR.PARTB.VAL_DATE = WS_EX_ODE_DATA[WS_I-1].WS_VALUE_DATE;
        CEP.TRC(SCCGWA, BPCOVAWR.PARTB.VAL_DATE);
        BPCOVAWR.PARTB.REF_NO = WS_EX_ODE_DATA[WS_I-1].WS_REF_NO;
        BPCOVAWR.PARTB.PROD_CODE = WS_EX_ODE_DATA[WS_I-1].WS_PROD_TYPE;
        if (WS_EX_ODE_DATA[WS_I-1].WS_ENTRY_TYPE == 'G') {
            IBS.init(SCCGWA, BPCPQPRD);
            BPCPQPRD.PRDT_CODE = BPCOVAWR.PARTB.PROD_CODE;
            IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
            if (BPCPQPRD.RC.RC_CODE == 0) {
                WS_PROD_AP = BPCPQPRD.BUSI_TYPE;
            }
        } else {
        }
        if (WS_PROD_AP.equalsIgnoreCase("MM")) {
            BPCOVAWR.PARTB.PORTFO_CD = "DEPOSIT";
        }
        BPCOVAWR.PARTB.NARR_CD = WS_EX_ODE_DATA[WS_I-1].WS_NARR_CD;
        BPCOVAWR.PARTB.DESC = WS_EX_ODE_DATA[WS_I-1].WS_TRN_DESC;
        BPCOVAWR.ANS_AREA.ANS_CD[1-1] = WS_EX_ODE_DATA[WS_I-1].WS_ANS_CD1;
        BPCOVAWR.ANS_AREA.ANS_CD[2-1] = WS_EX_ODE_DATA[WS_I-1].WS_ANS_CD2;
        BPCOVAWR.ANS_AREA.ANS_CD[3-1] = WS_EX_ODE_DATA[WS_I-1].WS_ANS_CD3;
        BPCOVAWR.ANS_AREA.ANS_CD[4-1] = WS_EX_ODE_DATA[WS_I-1].WS_ANS_CD4;
        BPCOVAWR.ANS_AREA.ANS_CD[5-1] = WS_EX_ODE_DATA[WS_I-1].WS_ANS_CD5;
        BPCOVAWR.ANS_AREA.ANS_CD[6-1] = WS_EX_ODE_DATA[WS_I-1].WS_ANS_CD6;
        BPCOVAWR.ANS_AREA.ANS_CD[7-1] = WS_EX_ODE_DATA[WS_I-1].WS_ANS_CD7;
        BPCOVAWR.ANS_AREA.ANS_CD[8-1] = WS_EX_ODE_DATA[WS_I-1].WS_ANS_CD8;
        BPCOVAWR.ANS_AREA.ANS_CD[9-1] = WS_EX_ODE_DATA[WS_I-1].WS_ANS_CD9;
        BPCOVAWR.ANS_AREA.ANS_CD[10-1] = WS_EX_ODE_DATA[WS_I-1].WS_ANS_CD10;
        BPCOVAWR.PARTB.FLR = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = BPCOVAWR.PARTB.FLR.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) BPCOVAWR.PARTB.FLR = "0" + BPCOVAWR.PARTB.FLR;
    }
    public void R00_GEN_VWA() throws IOException,SQLException,Exception {
        WS_VWA_CNT += 1;
        if (WS_VWA_CNT == 1) {
            BPCOVAWR.FST_FLG = 'Y';
        } else {
            BPCOVAWR.FST_FLG = 'N';
        }
        S000_CALL_BPZPVAWR();
        if (pgmRtn) return;
    }
    public void R000_GET_INOFF_FLG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICPQITM);
        AICPQITM.INPUT_DATA.NO = WS_ITM;
        AICPQITM.INPUT_DATA.BOOK_FLG = WS_BOOK_FLG;
        if (AICPQITM.OUTPUT_DATA.AUTH_LVL > WS_GRP_AUTH_LVL) {
            WS_GRP_AUTH_LVL = AICPQITM.OUTPUT_DATA.AUTH_LVL;
        }
        if (AICPQITM.OUTPUT_DATA.AUTH_LVL > WS_ENT_AUTH_LVL) {
            WS_ENT_AUTH_LVL = AICPQITM.OUTPUT_DATA.AUTH_LVL;
        }
    }
    public void R00_GET_DD_LVL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, AIRPAI2);
        AIRPAI2.KEY.TYP = "PAI02";
        AIRPAI2.KEY.REDEFINES5.CODE = "ENTLVL";
        AIRPAI2.KEY.CD = IBS.CLS2CPY(SCCGWA, AIRPAI2.KEY.REDEFINES5);
        BPCPRMR.DAT_PTR = AIRPAI2;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXAPV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZPQITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-ITM", AICPQITM);
        if (AICPQITM.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQITM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXAPV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S00_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB);
        if (AICPQMIB.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQMIB.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXAPV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZEODEL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-EXCEL-ODE-CHECK", BPCEXCHK);
        if (BPCEXCHK.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCEXCHK.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXAPV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-GET-SEQ", BPCSGSEQ);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXAPV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZUCALE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CMP_UPDATE_CALE, BPCUCALE);
        if (BPCUCALE.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUCALE.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXAPV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRMUPD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CMP_MAIN_BPTEXUPF, BPCRMUPD);
        if (BPCRMUPD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRMUPD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXAPV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_IBZCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-CREDIT-AC", IBCPOSTA);
        if (IBCPOSTA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCPOSTA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXAPV.RC);
            BPCEXAPV.RC.ERR_INF = "AC=" + WS_EX_ODE_DATA[WS_I-1].WS_AC + ",CCY=" + WS_EX_ODE_DATA[WS_I-1].WS_CUR;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_IBZDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-DEBIT-AC", IBCPOSTA);
        if (IBCPOSTA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCPOSTA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXAPV.RC);
            BPCEXAPV.RC.ERR_INF = "AC=" + WS_EX_ODE_DATA[WS_I-1].WS_AC + ",CCY=" + WS_EX_ODE_DATA[WS_I-1].WS_CUR;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPEBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-BASIC-EWA", BPCPEBAS);
        if (BPCPEBAS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPEBAS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXAPV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPVABS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-VWA-BASIC-ADD", BPCOVABS);
        if (BPCOVABS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOVABS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXAPV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPVAWR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-VWA-WRITE", BPCOVAWR);
        if (BPCOVAWR.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOVAWR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXAPV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC, true);
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC, true);
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
    }
    public void S000_CALL_IBZQINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZQINF", IBCQINF);
    }
    public void S000_CALL_AIZUIANO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-GEN-IANO", AICUIANO);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXAPV.RC);
        }
    }
    public void T000_WRITE_AITGRP() throws IOException,SQLException,Exception {
        AITGRP_RD = new DBParm();
        AITGRP_RD.TableName = "AITGRP";
        IBS.WRITE(SCCGWA, AIRGRP, AITGRP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITGRP";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_AITODE() throws IOException,SQLException,Exception {
        AITODE_RD = new DBParm();
        AITODE_RD.TableName = "AITODE";
        IBS.WRITE(SCCGWA, AIRODE, AITODE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITODE";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
