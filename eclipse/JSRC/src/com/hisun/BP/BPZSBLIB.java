package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSBLIB {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CMP_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    String PARM_BRW = "BP-PARM-BROWSE      ";
    String R_BRE_CLIB = "BP-R-BRE-CLIB       ";
    String P_INQ_ORG_STATION = "BP-P-INQ-ORG-STATION";
    String R_STARTBR_TLVB = "BP-R-STARTBR-TLVB";
    int MAX_COL = 140;
    int MAX_ROW = 99;
    BPZSBLIB_WS_VARIABLES WS_VARIABLES = new BPZSBLIB_WS_VARIABLES();
    BPZSBLIB_WS_COND_FLG WS_COND_FLG = new BPZSBLIB_WS_COND_FLG();
    BPCMSG_ERROR_MSG ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPRMB BPCPRMB = new BPCPRMB();
    BPRPORGM BPRPORGM = new BPRPORGM();
    BPRCLIB BPRCLIB = new BPRCLIB();
    BPRTLVB BPRTLVB = new BPRTLVB();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCPRGST BPCPRGST = new BPCPRGST();
    BPCRTLVB BPCRTLVB = new BPCRTLVB();
    BPCTLIBB BPCTLIBB = new BPCTLIBB();
    SCCGWA SCCGWA;
    BPCSBLIB BPCSBLIB;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSBLIB BPCSBLIB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSBLIB = BPCSBLIB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSBLIB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_COND_FLG);
        IBS.init(SCCGWA, BPCSBLIB.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            if (BPCSBLIB.VB_FLG == '1' 
                || BPCSBLIB.VB_FLG == '2' 
                || BPCSBLIB.VB_FLG == '3' 
                || BPCSBLIB.VB_FLG == '4' 
                || BPCSBLIB.VB_FLG == '5' 
                || BPCSBLIB.VB_FLG == '6') {
                B010_BROWSE_MAIN_PROC_FOR_CN();
                if (pgmRtn) return;
            }
        } else {
            if (BPCSBLIB.VB_FLG == '1' 
                || BPCSBLIB.VB_FLG == '2' 
                || BPCSBLIB.VB_FLG == '3' 
                || BPCSBLIB.VB_FLG == '4') {
                B010_BROWSE_MAIN_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_BROWSE_MAIN_PROC_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCSUBS);
        SCCSUBS.AP_CODE = 999;
        SCCSUBS.TR_CODE = 2061;
        S000_SET_SUBS_TRN();
        if (pgmRtn) return;
        B010_01_OUTPUT_TITLE();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRTLVB);
        IBS.init(SCCGWA, BPCRTLVB);
        WS_VARIABLES.TBL_FLAG = ' ';
        BPRTLVB.KEY.BR = BPCSBLIB.BR;
        BPRTLVB.PLBOX_TP = BPCSBLIB.VB_FLG;
        BPRTLVB.KEY.PLBOX_NO = BPCSBLIB.PLBOX_NO;
        BPRTLVB.CRNT_TLR = BPCSBLIB.TLR;
        if (BPCSBLIB.PLBOX_NO.trim().length() > 0) {
            BPCRTLVB.INFO.FUNC = 'B';
        } else if (BPCSBLIB.PLBOX_NO.trim().length() == 0 
                && BPCSBLIB.TLR.trim().length() == 0) {
            BPCRTLVB.INFO.FUNC = 'P';
        } else if (BPCSBLIB.PLBOX_NO.trim().length() == 0 
                && BPCSBLIB.TLR.trim().length() > 0) {
            BPCRTLVB.INFO.FUNC = 'R';
        } else {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_DATE_RANGE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCRTLVB.INFO.FUNC);
        BPCRTLVB.INFO.LEN = 187;
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        S000_CALL_BPZRTLVB();
        if (pgmRtn) return;
        BPCRTLVB.INFO.FUNC = 'N';
        BPCRTLVB.INFO.LEN = 187;
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        S000_CALL_BPZRTLVB();
        if (pgmRtn) return;
        while (BPCRTLVB.RETURN_INFO != 'N' 
            && WS_VARIABLES.TBL_FLAG != 'N' 
            && WS_VARIABLES.CNT2 <= 1000) {
            CEP.TRC(SCCGWA, "111222333");
            CEP.TRC(SCCGWA, BPRTLVB.KEY.PLBOX_NO);
            CEP.TRC(SCCGWA, BPRTLVB.PLBOX_TP);
            CEP.TRC(SCCGWA, BPCSBLIB.VB_FLG);
            CEP.TRC(SCCGWA, BPCSBLIB.TLR);
            CEP.TRC(SCCGWA, BPRTLVB.CRNT_TLR);
            CEP.TRC(SCCGWA, BPRTLVB.BIND_TYPE);
            if (BPCSBLIB.TLR.trim().length() > 0 
                && !BPCSBLIB.TLR.equalsIgnoreCase(BPRTLVB.CRNT_TLR) 
                && !BPCSBLIB.TLR.equalsIgnoreCase(BPRTLVB.CRNT_TLR1) 
                && !BPCSBLIB.TLR.equalsIgnoreCase(BPRTLVB.CRNT_TLR2) 
                && !BPCSBLIB.TLR.equalsIgnoreCase(BPRTLVB.CRNT_TLR3) 
                && !BPCSBLIB.TLR.equalsIgnoreCase(BPRTLVB.CRNT_TLR4)) {
                WS_VARIABLES.TBL_FLAG = 'N';
            } else {
                B010_03_QUIRE_BRDETAIL();
                if (pgmRtn) return;
            }
            WS_VARIABLES.CNT2 += 1;
            BPCRTLVB.INFO.FUNC = 'N';
            BPCRTLVB.INFO.POINTER = BPRTLVB;
            BPCRTLVB.INFO.LEN = 187;
            S000_CALL_BPZRTLVB();
            if (pgmRtn) return;
        }
        BPCRTLVB.INFO.FUNC = 'E';
        BPCRTLVB.INFO.LEN = 187;
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        S000_CALL_BPZRTLVB();
        if (pgmRtn) return;
        BPCSBLIB.CNT = WS_VARIABLES.CNT;
    }
    public void B010_BROWSE_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCSUBS);
        SCCSUBS.AP_CODE = 999;
        SCCSUBS.TR_CODE = 2061;
        S000_SET_SUBS_TRN();
        if (pgmRtn) return;
        B010_01_OUTPUT_TITLE();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRTLVB);
        IBS.init(SCCGWA, BPCRTLVB);
        WS_VARIABLES.TBL_FLAG = ' ';
        BPRTLVB.KEY.BR = BPCSBLIB.BR;
        if (BPCSBLIB.PLBOX_NO.trim().length() == 0) {
            BPRTLVB.KEY.PLBOX_NO = "%%%%%%";
        } else {
            BPRTLVB.KEY.PLBOX_NO = BPCSBLIB.PLBOX_NO;
        }
        CEP.TRC(SCCGWA, "11111111");
        BPCRTLVB.INFO.FUNC = 'Q';
        BPCRTLVB.INFO.LEN = 187;
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        S000_CALL_BPZRTLVB();
        if (pgmRtn) return;
        BPCRTLVB.INFO.FUNC = 'N';
        BPCRTLVB.INFO.LEN = 187;
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        S000_CALL_BPZRTLVB();
        if (pgmRtn) return;
        while (WS_VARIABLES.TBL_FLAG != 'N' 
            && WS_VARIABLES.CNT2 <= 1000) {
            CEP.TRC(SCCGWA, "111222333");
            CEP.TRC(SCCGWA, BPRTLVB.KEY.PLBOX_NO);
            CEP.TRC(SCCGWA, BPRTLVB.PLBOX_TP);
            CEP.TRC(SCCGWA, BPCSBLIB.VB_FLG);
            CEP.TRC(SCCGWA, BPCSBLIB.TLR);
            CEP.TRC(SCCGWA, BPRTLVB.CRNT_TLR);
            if ((BPCSBLIB.TLR.trim().length() > 0 
                && BPCSBLIB.TLR.equalsIgnoreCase(BPRTLVB.CRNT_TLR)) 
                || BPCSBLIB.TLR.trim().length() == 0) {
                B010_03_QUIRE_BRDETAIL();
                if (pgmRtn) return;
            }
            WS_VARIABLES.CNT2 += 1;
            BPCRTLVB.INFO.FUNC = 'N';
            BPCRTLVB.INFO.POINTER = BPRTLVB;
            BPCRTLVB.INFO.LEN = 187;
            S000_CALL_BPZRTLVB();
            if (pgmRtn) return;
            if (BPCRTLVB.RETURN_INFO == 'F') {
                WS_VARIABLES.TBL_FLAG = 'Y';
            } else if (BPCRTLVB.RETURN_INFO == 'N') {
                WS_VARIABLES.TBL_FLAG = 'N';
            } else {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_DATE_RANGE_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        BPCRTLVB.INFO.FUNC = 'E';
        BPCRTLVB.INFO.LEN = 187;
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        S000_CALL_BPZRTLVB();
        if (pgmRtn) return;
        BPCSBLIB.CNT = WS_VARIABLES.CNT;
    }
    public void B010_01_OUTPUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 182;
        SCCMPAG.SCR_ROW_CNT = 20;
        SCCMPAG.SCR_COL_CNT = 6;
        CEP.TRC(SCCGWA, "AAAA");
        B_MPAG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BBBB");
    }
    public void B010_03_QUIRE_BRDETAIL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCLIB);
        IBS.init(SCCGWA, BPCTLIBB);
        IBS.init(SCCGWA, BPCTLIBB.BPCFCLIB);
        BPRCLIB.KEY.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
        BPRCLIB.KEY.BR = BPCSBLIB.BR;
        CEP.TRC(SCCGWA, BPRCLIB.KEY.BR);
        CEP.TRC(SCCGWA, BPRCLIB.KEY.PLBOX_NO);
        BPCTLIBB.INFO.FUNC = '2';
        BPCTLIBB.POINTER = BPRCLIB;
        BPCTLIBB.LEN = 352;
        S000_CALL_BPZTLIBB();
        if (pgmRtn) return;
        BPCTLIBB.INFO.FUNC = 'N';
        BPCTLIBB.POINTER = BPRCLIB;
        BPCTLIBB.LEN = 352;
        S000_CALL_BPZTLIBB();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTLIBB.RC);
        while (!JIBS_tmp_str[0].equalsIgnoreCase(ERROR_MSG.BP_CLIB_NOTFND) 
            && SCCMPAG.FUNC != 'E') {
            BPCTLIBB.BPCFCLIB.CLIB_BR = BPRCLIB.KEY.BR;
            BPCTLIBB.BPCFCLIB.CLIB_PLBOX_NO = BPRCLIB.KEY.PLBOX_NO;
            BPCTLIBB.BPCFCLIB.CLIB_PLBOX_TP = BPRCLIB.PLBOX_TP;
            BPCTLIBB.BPCFCLIB.CLIB_BAL_FLG = BPRCLIB.BAL_FLG;
            BPCTLIBB.BPCFCLIB.CLIB_OPEN_TLR = BPRCLIB.OPEN_TLR;
            BPCTLIBB.BPCFCLIB.CLIB_OPEN_DT = BPRCLIB.OPEN_DT;
            BPCTLIBB.BPCFCLIB.CLIB_RLTD_FLG = BPRTLVB.RLTD_FLG;
            BPCTLIBB.BPCFCLIB.CLIB_LAST_TLR = BPRTLVB.LAST_TLR;
            BPCTLIBB.BPCFCLIB.CLIB_UPDT_TLR = BPRCLIB.UPD_TLR;
            BPCTLIBB.BPCFCLIB.CLIB_UPDT_DT = BPRCLIB.LAST_DT;
            BPCTLIBB.BPCFCLIB.CLIB_AC_DT = BPRCLIB.AC_DT;
            BPCTLIBB.BPCFCLIB.CLIB_INSR_CCY = BPRTLVB.INSR_CCY;
            BPCTLIBB.BPCFCLIB.CLIB_INSR_AMT = BPRTLVB.INSR_AMT;
            BPCTLIBB.BPCFCLIB.CLIB_CASH_TYP = BPRCLIB.KEY.CASH_TYP;
            BPCTLIBB.BPCFCLIB.CLIB_CCY = BPRCLIB.KEY.CCY;
            BPCTLIBB.BPCFCLIB.CLIB_LMT_CCY = BPRCLIB.LMT_CCY;
            BPCTLIBB.BPCFCLIB.CLIB_LMT_U = BPRCLIB.LMT_U;
            BPCTLIBB.BPCFCLIB.CLIB_LMT_L = BPRCLIB.LMT_L;
            BPCTLIBB.BPCFCLIB.CLIB_BAL = BPRCLIB.BAL;
            BPCTLIBB.BPCFCLIB.CLIB_GD_AMT = BPRCLIB.GD_AMT;
            BPCTLIBB.BPCFCLIB.CLIB_BD_AMT = BPRCLIB.BD_AMT;
            BPCTLIBB.BPCFCLIB.BIND_TYPE = BPRTLVB.BIND_TYPE;
            CEP.TRC(SCCGWA, "1111122222");
            CEP.TRC(SCCGWA, BPCTLIBB.BPCFCLIB.CLIB_BR);
            CEP.TRC(SCCGWA, BPCTLIBB.BPCFCLIB.CLIB_PLBOX_NO);
            CEP.TRC(SCCGWA, BPCTLIBB.BPCFCLIB.CLIB_PLBOX_TP);
            CEP.TRC(SCCGWA, BPCTLIBB.BPCFCLIB.CLIB_BAL_FLG);
            CEP.TRC(SCCGWA, BPCTLIBB.BPCFCLIB.CLIB_OPEN_TLR);
            CEP.TRC(SCCGWA, BPCTLIBB.BPCFCLIB.CLIB_OPEN_DT);
            CEP.TRC(SCCGWA, BPCTLIBB.BPCFCLIB.CLIB_CRNT_TLR);
            CEP.TRC(SCCGWA, BPCTLIBB.BPCFCLIB.CLIB_LAST_TLR);
            CEP.TRC(SCCGWA, BPCTLIBB.BPCFCLIB.CLIB_UPDT_TLR);
            CEP.TRC(SCCGWA, BPCTLIBB.BPCFCLIB.CLIB_UPDT_DT);
            CEP.TRC(SCCGWA, BPCTLIBB.BPCFCLIB.CLIB_AC_DT);
            CEP.TRC(SCCGWA, BPCTLIBB.BPCFCLIB.CLIB_INSR_CCY);
            CEP.TRC(SCCGWA, BPCTLIBB.BPCFCLIB.CLIB_INSR_AMT);
            CEP.TRC(SCCGWA, BPCTLIBB.BPCFCLIB.CLIB_CASH_TYP);
            CEP.TRC(SCCGWA, BPCTLIBB.BPCFCLIB.CLIB_CCY);
            CEP.TRC(SCCGWA, BPCTLIBB.BPCFCLIB.CLIB_LMT_CCY);
            CEP.TRC(SCCGWA, BPCTLIBB.BPCFCLIB.CLIB_LMT_U);
            CEP.TRC(SCCGWA, BPCTLIBB.BPCFCLIB.CLIB_LMT_L);
            if (BPRCLIB.PLBOX_TP == BPCSBLIB.VB_FLG) {
                if (BPRTLVB.PLBOX_TP != '6') {
                    BPCTLIBB.BPCFCLIB.CLIB_CRNT_TLR = " ";
                    BPCTLIBB.BPCFCLIB.CLIB_CRNT_TLR = BPRTLVB.CRNT_TLR;
                    CEP.TRC(SCCGWA, BPCTLIBB.BPCFCLIB.CLIB_CRNT_TLR);
                    B010_04_OUTPUT_DETAIL();
                    if (pgmRtn) return;
                } else {
                    CEP.TRC(SCCGWA, BPRTLVB.CRNT_TLR);
                    CEP.TRC(SCCGWA, BPRTLVB.CRNT_TLR1);
                    CEP.TRC(SCCGWA, BPRTLVB.CRNT_TLR2);
                    CEP.TRC(SCCGWA, BPRTLVB.CRNT_TLR3);
                    CEP.TRC(SCCGWA, BPRTLVB.CRNT_TLR4);
                    WS_VARIABLES.CNT4 = 0;
                    WS_VARIABLES.CNT5 = 0;
                    WS_VARIABLES.CNT6 = 0;
                    IBS.CPY2CLS(SCCGWA, BPRTLVB.CRNT_TLR, WS_VARIABLES.WS_TLR_INFO.WS_CRT_TLR[1-1]);
                    IBS.CPY2CLS(SCCGWA, BPRTLVB.CRNT_TLR1, WS_VARIABLES.WS_TLR_INFO.WS_CRT_TLR[2-1]);
                    IBS.CPY2CLS(SCCGWA, BPRTLVB.CRNT_TLR2, WS_VARIABLES.WS_TLR_INFO.WS_CRT_TLR[3-1]);
                    IBS.CPY2CLS(SCCGWA, BPRTLVB.CRNT_TLR3, WS_VARIABLES.WS_TLR_INFO.WS_CRT_TLR[4-1]);
                    IBS.CPY2CLS(SCCGWA, BPRTLVB.CRNT_TLR4, WS_VARIABLES.WS_TLR_INFO.WS_CRT_TLR[5-1]);
                    for (WS_VARIABLES.CNT4 = 1; WS_VARIABLES.CNT4 <= 5; WS_VARIABLES.CNT4 += 1) {
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.WS_TLR_INFO.WS_CRT_TLR[WS_VARIABLES.CNT4-1]);
                        if (JIBS_tmp_str[0].trim().length() == 0) {
                            WS_VARIABLES.CNT5 = WS_VARIABLES.CNT5 + 1;
                        }
                    }
                    CEP.TRC(SCCGWA, WS_VARIABLES.CNT5);
                    if (WS_VARIABLES.CNT5 == 5) {
                        B010_04_OUTPUT_DETAIL();
                        if (pgmRtn) return;
                    }
                    if (WS_VARIABLES.CNT5 < 5) {
                        WS_VARIABLES.CNT6 = 5 - WS_VARIABLES.CNT5;
                        CEP.TRC(SCCGWA, WS_VARIABLES.CNT6);
                        WS_VARIABLES.CNT5 = 0;
                        for (WS_VARIABLES.CNT4 = 1; WS_VARIABLES.CNT5 != WS_VARIABLES.CNT6 
                            && WS_VARIABLES.CNT4 <= 5; WS_VARIABLES.CNT4 += 1) {
                            CEP.TRC(SCCGWA, BPCSBLIB.TLR);
                            if (BPCSBLIB.TLR.trim().length() == 0) {
                                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.WS_TLR_INFO.WS_CRT_TLR[WS_VARIABLES.CNT4-1]);
                                if (JIBS_tmp_str[0].trim().length() > 0) {
                                    BPCTLIBB.BPCFCLIB.CLIB_CRNT_TLR = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.WS_TLR_INFO.WS_CRT_TLR[WS_VARIABLES.CNT4-1]);
                                    B010_04_OUTPUT_DETAIL();
                                    if (pgmRtn) return;
                                    WS_VARIABLES.CNT5 = WS_VARIABLES.CNT5 + 1;
                                    CEP.TRC(SCCGWA, WS_VARIABLES.CNT5);
                                }
                            } else {
                                CEP.TRC(SCCGWA, BPCSBLIB.TLR);
                                CEP.TRC(SCCGWA, WS_VARIABLES.WS_TLR_INFO.WS_CRT_TLR[WS_VARIABLES.CNT4-1]);
                                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.WS_TLR_INFO.WS_CRT_TLR[WS_VARIABLES.CNT4-1]);
                                if (JIBS_tmp_str[0].equalsIgnoreCase(BPCSBLIB.TLR)) {
                                    BPCTLIBB.BPCFCLIB.CLIB_CRNT_TLR = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.WS_TLR_INFO.WS_CRT_TLR[WS_VARIABLES.CNT4-1]);
                                    B010_04_OUTPUT_DETAIL();
                                    if (pgmRtn) return;
                                }
                            }
                        }
                    }
                }
            }
            WS_VARIABLES.CNT += 1;
            BPCTLIBB.INFO.FUNC = 'N';
            BPCTLIBB.POINTER = BPRCLIB;
            BPCTLIBB.LEN = 352;
            S000_CALL_BPZTLIBB();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCTLIBB.RC);
        }
        BPCTLIBB.INFO.FUNC = 'E';
        BPCTLIBB.POINTER = BPRCLIB;
        BPCTLIBB.LEN = 352;
        S000_CALL_BPZTLIBB();
        if (pgmRtn) return;
    }
    public void B010_04_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCTLIBB.BPCFCLIB);
        SCCMPAG.DATA_LEN = 182;
        CEP.TRC(SCCGWA, BPCTLIBB.BPCFCLIB);
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B020_01_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCTLIBB.BPCFCLIB);
        SCCMPAG.DATA_LEN = 182;
        B_MPAG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "B020-01-OUTPUT-DETAIL111");
        CEP.TRC(SCCGWA, BPCTLIBB.BPCFCLIB);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
    }
    public void S000_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CMP_SET_SBUS_TRN, SCCSUBS);
    }
    public void S000_CALL_BPZPRMB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, PARM_BRW, BPCPRMB);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCPRMB.RC);
        if (BPCPRMB.RC.RC_RTNCODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(ERROR_MSG.BP_PARM_NOTFND)) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, P_INQ_ORG_STATION, BPCPRGST);
        if (BPCPRGST.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRGST.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTLIBB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, R_BRE_CLIB, BPCTLIBB);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTLIBB.RC);
        if (!JIBS_tmp_str[0].equalsIgnoreCase(ERROR_MSG.BP_CLIB_NOTFND) 
            && BPCTLIBB.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTLIBB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRTLVB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, R_STARTBR_TLVB, BPCRTLVB);
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
