package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSLIBT {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_BRE_CLIB = "BP-R-BRE-CLIB       ";
    String CPN_R_ADW_BRIS = "BP-R-ADW-BRIS       ";
    String CPN_R_BRW_BRIB = "BP-R-STARTBR-BRIB   ";
    String CPN_BP_EX = "BP-EX               ";
    String CPN_R_BRW_THIS = "BP-R-BRW-THIS       ";
    String CPN_R_BRW_CHIS = "BP-R-BRW-CHIS       ";
    String CPN_R_STARTBR_TLVB = "BP-R-STARTBR-TLVB";
    int K_NUM_50 = 50;
    int K_NUM_11 = 11;
    char K_F_CASHLIB_FLG = '1';
    char K_Z_CASHLIB_FLG = '2';
    char K_CASHBOX_FLG = '3';
    char K_ATMBOX_FLG = '4';
    char K_LT_BOX_FLG = '5';
    char K_DRAWING_FLG = 'D';
    char K_DEPOSIT_FLG = 'C';
    String K_ORD_CASH_TYP = "01";
    char K_DR_FLG = 'D';
    char K_CR_FLG = 'C';
    String K_CCY = 156;
    String WS_ERR_MSG = " ";
    double WS_TEMP_BAL = 0;
    int WS_CNT = 0;
    double WS_TD_AMT = 0;
    double WS_LD_AMT = 0;
    char WS_DATA_OVERFLOW_FLAG = ' ';
    char WS_CASHLIB_FND_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    BPRCLIB BPRCLIB = new BPRCLIB();
    BPRBRIS BPRBRIS = new BPRBRIS();
    BPRTHIS BPRTHIS = new BPRTHIS();
    BPRCHIS BPRCHIS = new BPRCHIS();
    BPCTBRIS BPCTBRIS = new BPCTBRIS();
    BPCRBRIB BPCRBRIB = new BPCRBRIB();
    BPCTLIBB BPCTLIBB = new BPCTLIBB();
    BPCOLIBT BPCOLIBT = new BPCOLIBT();
    BPCFX BPCFX = new BPCFX();
    BPCTTHIB BPCTTHIB = new BPCTTHIB();
    BPCTCHIB BPCTCHIB = new BPCTCHIB();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPCRTLVB BPCRTLVB = new BPCRTLVB();
    SCCGWA SCCGWA;
    BPCSLIBT BPCSLIBT;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSLIBT BPCSLIBT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSLIBT = BPCSLIBT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSLIBT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCOLIBT);
        IBS.init(SCCGWA, BPCSLIBT.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            if (BPCSLIBT.FUNC == 'B') {
                B020_BROWSE_BY_BR_PROC_CN();
                if (pgmRtn) return;
            } else {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCSLIBT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
            if (BPCSLIBT.FUNC == 'B') {
                B010_GET_EQUAL_CCY_BY_BR();
                if (pgmRtn) return;
                B020_BROWSE_BY_BR_PROC();
                if (pgmRtn) return;
            } else if (BPCSLIBT.FUNC == 'A') {
                B010_GET_EQUAL_CCY_INFO();
                if (pgmRtn) return;
                B030_BROWSE_ALL_BR_PROC();
                if (pgmRtn) return;
            } else {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCSLIBT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_GET_EQUAL_CCY_BY_BR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBRIS);
        IBS.init(SCCGWA, BPCTBRIS);
        BPRBRIS.KEY.BR = BPCSLIBT.BR;
        BPCTBRIS.POINTER = BPRBRIS;
        BPCTBRIS.LEN = 134;
        BPCTBRIS.INFO.FUNC = 'Q';
        S000_CALL_BPZTBRIS();
        if (pgmRtn) return;
        if (BPCTBRIS.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BRIS_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_GET_EQUAL_CCY_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBRIS);
        IBS.init(SCCGWA, BPCRBRIB);
        BPRBRIS.KEY.BR = 0;
        BPCRBRIB.INFO.FUNC = 'S';
        BPCRBRIB.INFO.POINTER = BPRBRIS;
        BPCRBRIB.INFO.LEN = 134;
        S000_CALL_BPZRBRIB();
        if (pgmRtn) return;
        BPCRBRIB.INFO.FUNC = 'N';
        BPCRBRIB.INFO.POINTER = BPRBRIS;
        BPCRBRIB.INFO.LEN = 134;
        S000_CALL_BPZRBRIB();
        if (pgmRtn) return;
        if (BPCRBRIB.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BRIS_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPCRBRIB.INFO.FUNC = 'E';
        BPCRBRIB.INFO.POINTER = BPRBRIS;
        BPCRBRIB.INFO.LEN = 134;
        S000_CALL_BPZRBRIB();
        if (pgmRtn) return;
    }
    public void B020_BROWSE_BY_BR_PROC_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCLIB);
        IBS.init(SCCGWA, BPCTLIBB);
        BPRCLIB.KEY.BR = BPCSLIBT.BR;
        BPRCLIB.KEY.CCY = BPCSLIBT.CCY;
        BPRCLIB.PLBOX_TP = BPCSLIBT.PLBOX_TP;
        if (BPCSLIBT.CCY.trim().length() == 0 
                && BPCSLIBT.PLBOX_TP == ' ') {
            BPCTLIBB.INFO.FUNC = '3';
        } else if (BPCSLIBT.CCY.trim().length() > 0 
                && BPCSLIBT.PLBOX_TP == ' ') {
            BPCTLIBB.INFO.FUNC = '5';
        } else if (BPCSLIBT.CCY.trim().length() == 0 
                && BPCSLIBT.PLBOX_TP != ' ') {
            BPCTLIBB.INFO.FUNC = '7';
        } else if (BPCSLIBT.CCY.trim().length() > 0 
                && BPCSLIBT.PLBOX_TP != ' ') {
            BPCTLIBB.INFO.FUNC = '8';
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPCTLIBB.POINTER = BPRCLIB;
        BPCTLIBB.LEN = 352;
        S000_CALL_BPZTLIBB();
        if (pgmRtn) return;
        if (BPCSLIBT.INQ_TYP == '0') {
            B040_READ_NEXT_AND_OUTPUT_CN();
            if (pgmRtn) return;
        } else {
            B050_READ_NEXT_AND_OUTPUT_TOT();
            if (pgmRtn) return;
        }
    }
    public void B020_BROWSE_BY_BR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCLIB);
        IBS.init(SCCGWA, BPCTLIBB);
        BPRCLIB.KEY.BR = BPCSLIBT.BR;
        if (BPCSLIBT.CCY.trim().length() == 0) {
            BPCTLIBB.INFO.FUNC = '3';
        } else {
            BPRCLIB.KEY.CCY = BPCSLIBT.CCY;
            BPCTLIBB.INFO.FUNC = '5';
        }
        BPCTLIBB.POINTER = BPRCLIB;
        BPCTLIBB.LEN = 352;
        S000_CALL_BPZTLIBB();
        if (pgmRtn) return;
        B040_READ_NEXT_AND_OUTPUT();
        if (pgmRtn) return;
    }
    public void B030_BROWSE_ALL_BR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCLIB);
        IBS.init(SCCGWA, BPCTLIBB);
        if (BPCSLIBT.CCY.trim().length() == 0) {
            BPCTLIBB.INFO.FUNC = '4';
        } else {
            BPCTLIBB.INFO.FUNC = '6';
        }
        BPCTLIBB.POINTER = BPRCLIB;
        BPCTLIBB.LEN = 352;
        S000_CALL_BPZTLIBB();
        if (pgmRtn) return;
        B040_READ_NEXT_AND_OUTPUT();
        if (pgmRtn) return;
    }
    public void B040_READ_NEXT_AND_OUTPUT_CN() throws IOException,SQLException,Exception {
        B090_OUTPUT_TITLE();
        if (pgmRtn) return;
        if (BPCSLIBT.PLBOX_TP == K_F_CASHLIB_FLG 
            || BPCSLIBT.PLBOX_TP == K_Z_CASHLIB_FLG) {
            B110_GET_CASHLIB_PLBOX_NO();
            if (pgmRtn) return;
        }
        BPCTLIBB.INFO.FUNC = 'N';
        BPCTLIBB.POINTER = BPRCLIB;
        BPCTLIBB.LEN = 352;
        S000_CALL_BPZTLIBB();
        if (pgmRtn) return;
        BPCOLIBT.CCY = BPRCLIB.KEY.CCY;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTLIBB.RC);
        while (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CLIB_NOTFND) 
            && SCCMPAG.FUNC != 'E') {
            WS_CNT += 1;
            B050_SUM_BAL_PROCESS();
            if (pgmRtn) return;
            BPCTLIBB.INFO.FUNC = 'N';
            BPCTLIBB.POINTER = BPRCLIB;
            BPCTLIBB.LEN = 352;
            S000_CALL_BPZTLIBB();
            if (pgmRtn) return;
            if (!BPRCLIB.KEY.CCY.equalsIgnoreCase(BPCOLIBT.CCY)) {
                if (WS_CASHLIB_FND_FLAG == 'Y') {
                    B080_GET_RECV_PAY_AMT_FOR_LIB();
                    if (pgmRtn) return;
                }
                BPCOLIBT.BR = BPCSLIBT.BR;
                B100_OUTPUT_DETAIL();
                if (pgmRtn) return;
                IBS.init(SCCGWA, BPCOLIBT);
            }
        }
        if (WS_CNT != 0) {
            BPCOLIBT.BR = BPCSLIBT.BR;
            B100_OUTPUT_DETAIL();
            if (pgmRtn) return;
        }
        BPCTLIBB.INFO.FUNC = 'E';
        BPCTLIBB.POINTER = BPRCLIB;
        BPCTLIBB.LEN = 352;
        S000_CALL_BPZTLIBB();
        if (pgmRtn) return;
    }
    public void B050_READ_NEXT_AND_OUTPUT_TOT() throws IOException,SQLException,Exception {
        B090_OUTPUT_TITLE();
        if (pgmRtn) return;
        if (BPCSLIBT.PLBOX_TP == K_F_CASHLIB_FLG 
            || BPCSLIBT.PLBOX_TP == K_Z_CASHLIB_FLG) {
            B110_GET_CASHLIB_PLBOX_NO();
            if (pgmRtn) return;
        }
        BPCTLIBB.INFO.FUNC = 'N';
        BPCTLIBB.POINTER = BPRCLIB;
        BPCTLIBB.LEN = 352;
        S000_CALL_BPZTLIBB();
        if (pgmRtn) return;
        BPCOLIBT.CCY = BPRCLIB.KEY.CCY;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTLIBB.RC);
        while (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CLIB_NOTFND) 
            && SCCMPAG.FUNC != 'E') {
            WS_CNT += 1;
            B050_SUM_BAL_PROCESS();
            if (pgmRtn) return;
            BPCTLIBB.INFO.FUNC = 'N';
            BPCTLIBB.POINTER = BPRCLIB;
            BPCTLIBB.LEN = 352;
            S000_CALL_BPZTLIBB();
            if (pgmRtn) return;
            if (!BPRCLIB.KEY.CCY.equalsIgnoreCase(BPCOLIBT.CCY)) {
                if (WS_CASHLIB_FND_FLAG == 'Y') {
                    B080_GET_RECV_PAY_AMT_FOR_LIB();
                    if (pgmRtn) return;
                }
                if (!BPCOLIBT.CCY.equalsIgnoreCase("156")) {
                    CEP.TRC(SCCGWA, "NCB0525001");
                    CEP.TRC(SCCGWA, BPCOLIBT.CCY);
                    CEP.TRC(SCCGWA, BPCOLIBT.TD_BAL);
                    if (BPCOLIBT.TD_BAL != 0) {
                        IBS.init(SCCGWA, BPCFX);
                        BPCFX.FUNC = '3';
                        BPCFX.EXR_TYPE = "TRE";
                        BPCFX.BUY_CCY = BPCOLIBT.CCY;
                        BPCFX.BUY_AMT = BPCOLIBT.TD_BAL;
                        BPCFX.SELL_CCY = K_CCY;
                        S000_CALL_BPZSFX();
                        if (pgmRtn) return;
                        CEP.TRC(SCCGWA, "NCB0525002");
                        CEP.TRC(SCCGWA, BPCFX.BUY_CCY);
                        CEP.TRC(SCCGWA, BPCFX.BUY_AMT);
                        CEP.TRC(SCCGWA, BPCFX.SELL_CCY);
                        CEP.TRC(SCCGWA, BPCFX.SELL_AMT);
                        CEP.TRC(SCCGWA, WS_TD_AMT);
                        WS_TD_AMT = WS_TD_AMT + BPCFX.SELL_AMT;
                    }
                    CEP.TRC(SCCGWA, WS_TD_AMT);
                    CEP.TRC(SCCGWA, "NCB0525003");
                    CEP.TRC(SCCGWA, BPCOLIBT.CCY);
                    CEP.TRC(SCCGWA, BPCOLIBT.LD_BAL);
                    if (BPCOLIBT.LD_BAL != 0) {
                        IBS.init(SCCGWA, BPCFX);
                        BPCFX.FUNC = '3';
                        BPCFX.EXR_TYPE = "TRE";
                        BPCFX.BUY_CCY = BPCOLIBT.CCY;
                        BPCFX.BUY_AMT = BPCOLIBT.LD_BAL;
                        BPCFX.SELL_CCY = K_CCY;
                        S000_CALL_BPZSFX();
                        if (pgmRtn) return;
                        CEP.TRC(SCCGWA, "NCB0525004");
                        CEP.TRC(SCCGWA, BPCFX.BUY_CCY);
                        CEP.TRC(SCCGWA, BPCFX.BUY_AMT);
                        CEP.TRC(SCCGWA, BPCFX.SELL_CCY);
                        CEP.TRC(SCCGWA, BPCFX.SELL_AMT);
                        CEP.TRC(SCCGWA, WS_LD_AMT);
                        WS_LD_AMT = WS_LD_AMT + BPCFX.SELL_AMT;
                    }
                    CEP.TRC(SCCGWA, WS_LD_AMT);
                }
                BPCOLIBT.BR = BPCSLIBT.BR;
                CEP.TRC(SCCGWA, "NCB052401");
                CEP.TRC(SCCGWA, BPCOLIBT.CCY);
                if (BPCOLIBT.CCY.equalsIgnoreCase("156")) {
                    WS_TD_AMT = WS_TD_AMT + BPCOLIBT.TD_BAL;
                    BPCOLIBT.TD_BAL = WS_TD_AMT;
                    WS_LD_AMT = WS_LD_AMT + BPCOLIBT.LD_BAL;
                    BPCOLIBT.LD_BAL = WS_LD_AMT;
                }
                IBS.init(SCCGWA, BPCOLIBT);
            }
        }
        CEP.TRC(SCCGWA, "NCB052402");
        CEP.TRC(SCCGWA, BPCOLIBT.CCY);
        CEP.TRC(SCCGWA, WS_CNT);
        if (WS_CNT != 0) {
            if (!BPCOLIBT.CCY.equalsIgnoreCase("156")) {
                CEP.TRC(SCCGWA, "NCB0525005");
                CEP.TRC(SCCGWA, BPCOLIBT.CCY);
                CEP.TRC(SCCGWA, BPCOLIBT.TD_BAL);
                if (BPCOLIBT.TD_BAL != 0) {
                    IBS.init(SCCGWA, BPCFX);
                    BPCFX.FUNC = '3';
                    BPCFX.EXR_TYPE = "TRE";
                    BPCFX.BUY_CCY = BPCOLIBT.CCY;
                    BPCFX.BUY_AMT = BPCOLIBT.TD_BAL;
                    BPCFX.SELL_CCY = K_CCY;
                    S000_CALL_BPZSFX();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, "NCB0525006");
                    CEP.TRC(SCCGWA, BPCFX.BUY_CCY);
                    CEP.TRC(SCCGWA, BPCFX.BUY_AMT);
                    CEP.TRC(SCCGWA, BPCFX.SELL_CCY);
                    CEP.TRC(SCCGWA, BPCFX.SELL_AMT);
                    CEP.TRC(SCCGWA, WS_TD_AMT);
                    WS_TD_AMT = WS_TD_AMT + BPCFX.SELL_AMT;
                }
                CEP.TRC(SCCGWA, WS_TD_AMT);
                CEP.TRC(SCCGWA, "NCB0525007");
                CEP.TRC(SCCGWA, BPCOLIBT.CCY);
                CEP.TRC(SCCGWA, BPCOLIBT.LD_BAL);
                if (BPCOLIBT.LD_BAL != 0) {
                    IBS.init(SCCGWA, BPCFX);
                    BPCFX.FUNC = '3';
                    BPCFX.EXR_TYPE = "TRE";
                    BPCFX.BUY_CCY = BPCOLIBT.CCY;
                    BPCFX.BUY_AMT = BPCOLIBT.LD_BAL;
                    BPCFX.SELL_CCY = K_CCY;
                    S000_CALL_BPZSFX();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, "NCB0525008");
                    CEP.TRC(SCCGWA, BPCFX.BUY_CCY);
                    CEP.TRC(SCCGWA, BPCFX.BUY_AMT);
                    CEP.TRC(SCCGWA, BPCFX.SELL_CCY);
                    CEP.TRC(SCCGWA, BPCFX.SELL_AMT);
                    CEP.TRC(SCCGWA, WS_LD_AMT);
                    WS_LD_AMT = WS_LD_AMT + BPCFX.SELL_AMT;
                }
                CEP.TRC(SCCGWA, WS_LD_AMT);
            } else {
                CEP.TRC(SCCGWA, "NCB061301");
                CEP.TRC(SCCGWA, BPCOLIBT.CCY);
                if (BPCOLIBT.CCY.equalsIgnoreCase("156")) {
                    WS_TD_AMT = WS_TD_AMT + BPCOLIBT.TD_BAL;
                    BPCOLIBT.TD_BAL = WS_TD_AMT;
                    WS_LD_AMT = WS_LD_AMT + BPCOLIBT.LD_BAL;
                    BPCOLIBT.LD_BAL = WS_LD_AMT;
                }
            }
            BPCOLIBT.BR = BPCSLIBT.BR;
            BPCOLIBT.CCY = K_CCY;
            BPCOLIBT.TD_BAL = WS_TD_AMT;
            BPCOLIBT.LD_BAL = WS_LD_AMT;
            B100_OUTPUT_DETAIL();
            if (pgmRtn) return;
        }
        BPCTLIBB.INFO.FUNC = 'E';
        BPCTLIBB.POINTER = BPRCLIB;
        BPCTLIBB.LEN = 352;
        S000_CALL_BPZTLIBB();
        if (pgmRtn) return;
    }
    public void B040_READ_NEXT_AND_OUTPUT() throws IOException,SQLException,Exception {
        B090_OUTPUT_TITLE();
        if (pgmRtn) return;
        BPCTLIBB.INFO.FUNC = 'N';
        BPCTLIBB.POINTER = BPRCLIB;
        BPCTLIBB.LEN = 352;
        S000_CALL_BPZTLIBB();
        if (pgmRtn) return;
        BPCOLIBT.CCY = BPRCLIB.KEY.CCY;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTLIBB.RC);
        while (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CLIB_NOTFND)) {
            WS_CNT += 1;
            B050_SUM_BAL_PROCESS();
            if (pgmRtn) return;
            BPCTLIBB.INFO.FUNC = 'N';
            BPCTLIBB.POINTER = BPRCLIB;
            BPCTLIBB.LEN = 352;
            S000_CALL_BPZTLIBB();
            if (pgmRtn) return;
            if (!BPRCLIB.KEY.CCY.equalsIgnoreCase(BPCOLIBT.CCY)) {
                B060_CALCUL_EQU_BAL_PROCESS();
                if (pgmRtn) return;
                if (BPCSLIBT.PLBOX_TP != K_CASHBOX_FLG 
                    && BPCSLIBT.PLBOX_TP != K_ATMBOX_FLG) {
                    B080_GET_RECV_PAY_AMT();
                    if (pgmRtn) return;
                }
                BPCOLIBT.BR = BPCSLIBT.BR;
                B100_OUTPUT_DETAIL();
                if (pgmRtn) return;
                IBS.init(SCCGWA, BPCOLIBT);
            }
        }
        if (WS_CNT != 0) {
            BPCOLIBT.BR = BPCSLIBT.BR;
            B100_OUTPUT_DETAIL();
            if (pgmRtn) return;
        }
        BPCTLIBB.INFO.FUNC = 'E';
        BPCTLIBB.POINTER = BPRCLIB;
        BPCTLIBB.LEN = 352;
        S000_CALL_BPZTLIBB();
        if (pgmRtn) return;
    }
    public void B050_SUM_BAL_PROCESS() throws IOException,SQLException,Exception {
        BPCOLIBT.CCY = BPRCLIB.KEY.CCY;
        BPCOLIBT.EQU_CCY = BPRBRIS.KEY.LMT_CCY;
        if (BPCSLIBT.PLBOX_TP == ' ') {
            BPCOLIBT.TD_BAL = BPCOLIBT.TD_BAL + BPRCLIB.BAL;
            if (BPRCLIB.NEW_DT == SCCGWA.COMM_AREA.AC_DATE) {
                BPCOLIBT.LD_BAL = BPCOLIBT.LD_BAL + BPRCLIB.L_TLT_AMT;
            } else {
                BPCOLIBT.LD_BAL = BPCOLIBT.LD_BAL + BPRCLIB.BAL;
            }
            if (!BPRCLIB.KEY.CASH_TYP.equalsIgnoreCase(K_ORD_CASH_TYP)) {
                BPCOLIBT.NEW_YEAR_BAL = BPCOLIBT.NEW_YEAR_BAL + BPRCLIB.BAL;
            }
        }
        if (BPRCLIB.PLBOX_TP == BPCSLIBT.PLBOX_TP) {
            BPCOLIBT.TD_BAL = BPCOLIBT.TD_BAL + BPRCLIB.BAL;
            if (BPRCLIB.NEW_DT == SCCGWA.COMM_AREA.AC_DATE) {
                BPCOLIBT.LD_BAL = BPCOLIBT.LD_BAL + BPRCLIB.L_TLT_AMT;
            } else {
                BPCOLIBT.LD_BAL = BPCOLIBT.LD_BAL + BPRCLIB.BAL;
            }
            if (!BPRCLIB.KEY.CASH_TYP.equalsIgnoreCase(K_ORD_CASH_TYP)) {
                BPCOLIBT.NEW_YEAR_BAL = BPCOLIBT.NEW_YEAR_BAL + BPRCLIB.BAL;
            }
        }
        if (BPRCLIB.PLBOX_TP == K_CASHBOX_FLG) {
            BPCOLIBT.CASHBOX_BAL = BPCOLIBT.CASHBOX_BAL + BPRCLIB.BAL;
        }
        if (BPRCLIB.PLBOX_TP == K_ATMBOX_FLG) {
            BPCOLIBT.ATM_BAL = BPCOLIBT.ATM_BAL + BPRCLIB.BAL;
        }
        if (WS_DATA_OVERFLOW_FLAG == 'Y') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATA_OVERFLOW;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B060_CALCUL_EQU_BAL_PROCESS() throws IOException,SQLException,Exception {
        if (BPCOLIBT.CCY.equalsIgnoreCase(BPCOLIBT.EQU_CCY)) {
            BPCOLIBT.TD_EQU_BAL = BPCOLIBT.TD_EQU_BAL + BPCOLIBT.TD_BAL;
            BPCOLIBT.LD_EQU_BAL = BPCOLIBT.LD_EQU_BAL + BPCOLIBT.LD_BAL;
        } else {
            if (BPCOLIBT.TD_BAL != 0) {
                WS_TEMP_BAL = BPCOLIBT.TD_BAL;
                B070_EQUAL_BAL_CHANGE();
                if (pgmRtn) return;
                BPCOLIBT.TD_EQU_BAL = BPCOLIBT.TD_EQU_BAL + BPCFX.SELL_AMT;
            }
            if (BPCOLIBT.LD_BAL != 0) {
                WS_TEMP_BAL = BPCOLIBT.LD_BAL;
                B070_EQUAL_BAL_CHANGE();
                if (pgmRtn) return;
                BPCOLIBT.LD_EQU_BAL = BPCOLIBT.LD_EQU_BAL + BPCFX.SELL_AMT;
            }
        }
    }
    public void B070_EQUAL_BAL_CHANGE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFX);
        BPCFX.FUNC = '3';
        BPCFX.BUY_CCY = BPCOLIBT.CCY;
        BPCFX.BUY_AMT = WS_TEMP_BAL;
        BPCFX.SELL_CCY = BPCOLIBT.EQU_CCY;
        BPCFX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCFX.EXR_TYPE = "MDR";
        S000_CALL_BPZSFX();
        if (pgmRtn) return;
    }
    public void B080_GET_RECV_PAY_AMT_FOR_LIB() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCHIS);
        IBS.init(SCCGWA, BPCTCHIB);
        BPRCHIS.KEY.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRCHIS.KEY.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
        BPRCHIS.KEY.CCY = BPCOLIBT.CCY;
        BPRCHIS.BR = BPCSLIBT.BR;
        BPRCHIS.STS = '0';
        BPCTCHIB.INFO.FUNC = 'C';
        BPCTCHIB.POINTER = BPRCHIS;
        BPCTCHIB.LEN = 153;
        S000_CALL_BPZTCHIB();
        if (pgmRtn) return;
        BPCTCHIB.INFO.FUNC = 'N';
        BPCTCHIB.POINTER = BPRCHIS;
        BPCTCHIB.LEN = 153;
        S000_CALL_BPZTCHIB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRCHIS.AMT);
        while (BPCTCHIB.RETURN_INFO != 'N') {
            if (BPRCHIS.IN_OUT == K_CR_FLG) {
                BPCOLIBT.TD_PAY_BAL = BPCOLIBT.TD_PAY_BAL + BPRCHIS.AMT;
            }
            if (BPRCHIS.IN_OUT == K_DR_FLG) {
                BPCOLIBT.TD_RECV_BAL = BPCOLIBT.TD_RECV_BAL + BPRCHIS.AMT;
            }
            BPCTCHIB.INFO.FUNC = 'N';
            BPCTCHIB.POINTER = BPRCHIS;
            BPCTCHIB.LEN = 153;
            S000_CALL_BPZTCHIB();
            if (pgmRtn) return;
        }
        BPCTCHIB.INFO.FUNC = 'E';
        BPCTCHIB.POINTER = BPRCHIS;
        BPCTCHIB.LEN = 153;
        S000_CALL_BPZTCHIB();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRTHIS);
        IBS.init(SCCGWA, BPCTTHIB);
        BPCTTHIB.DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCTTHIB.END_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCTTHIB.CCY = BPCOLIBT.CCY;
        BPCTTHIB.BR = BPCSLIBT.BR;
        BPCTTHIB.RCE_PBNO = BPRTLVB.KEY.PLBOX_NO;
        BPCTTHIB.PAY_PBNO = BPRTLVB.KEY.PLBOX_NO;
        BPCTTHIB.STS = '0';
        BPCTTHIB.TR_CODE = "9992260";
        BPCTTHIB.FUNC = 'C';
        BPCTTHIB.INFO.POINTER = BPCTTHIB;
        BPCTTHIB.DATA_LEN = 675;
        S000_CALL_BPZTTHIB();
        if (pgmRtn) return;
        BPCTTHIB.FUNC = 'R';
        BPCTTHIB.INFO.POINTER = BPCTTHIB;
        BPCTTHIB.DATA_LEN = 675;
        S000_CALL_BPZTTHIB();
        if (pgmRtn) return;
        while (BPCTTHIB.RETURN_INFO != 'N') {
            if (BPCTTHIB.DC_FLG == K_DRAWING_FLG) {
                BPCOLIBT.TD_PAY_BAL = BPCOLIBT.TD_PAY_BAL + BPCTTHIB.AMT;
            }
            if (BPCTTHIB.DC_FLG == K_DEPOSIT_FLG) {
                BPCOLIBT.TD_RECV_BAL = BPCOLIBT.TD_RECV_BAL + BPCTTHIB.AMT;
            }
            BPCTTHIB.FUNC = 'R';
            BPCTTHIB.INFO.POINTER = BPCTTHIB;
            BPCTTHIB.DATA_LEN = 675;
            S000_CALL_BPZTTHIB();
            if (pgmRtn) return;
        }
        BPCTTHIB.FUNC = 'E';
        BPCTTHIB.INFO.POINTER = BPCTTHIB;
        BPCTTHIB.DATA_LEN = 675;
        S000_CALL_BPZTTHIB();
        if (pgmRtn) return;
        if (WS_DATA_OVERFLOW_FLAG == 'Y') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATA_OVERFLOW;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B080_GET_RECV_PAY_AMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTHIS);
        IBS.init(SCCGWA, BPCTTHIB);
        BPCTTHIB.DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCTTHIB.END_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCTTHIB.CCY = BPCOLIBT.CCY;
        BPCTTHIB.BR = BPCSLIBT.BR;
        BPCTTHIB.RCE_PBNO = "4%";
        BPCTTHIB.PAY_PBNO = "4%";
        BPCTTHIB.STS = '0';
        BPCTTHIB.FUNC = 'A';
        BPCTTHIB.INFO.POINTER = BPCTTHIB;
        BPCTTHIB.DATA_LEN = 675;
        S000_CALL_BPZTTHIB();
        if (pgmRtn) return;
        BPCTTHIB.FUNC = 'R';
        BPCTTHIB.INFO.POINTER = BPCTTHIB;
        BPCTTHIB.DATA_LEN = 675;
        S000_CALL_BPZTTHIB();
        if (pgmRtn) return;
        while (BPCTTHIB.RETURN_INFO != 'N') {
            if (BPCTTHIB.DC_FLG == K_DRAWING_FLG) {
                BPCOLIBT.TD_PAY_BAL = BPCOLIBT.TD_PAY_BAL + BPCTTHIB.AMT;
            }
            if (BPCTTHIB.DC_FLG == K_DEPOSIT_FLG) {
                BPCOLIBT.TD_RECV_BAL = BPCOLIBT.TD_RECV_BAL + BPCTTHIB.AMT;
            }
            BPCTTHIB.FUNC = 'R';
            BPCTTHIB.INFO.POINTER = BPCTTHIB;
            BPCTTHIB.DATA_LEN = 675;
            S000_CALL_BPZTTHIB();
            if (pgmRtn) return;
        }
        BPCTTHIB.FUNC = 'E';
        BPCTTHIB.INFO.POINTER = BPCTTHIB;
        BPCTTHIB.DATA_LEN = 675;
        S000_CALL_BPZTTHIB();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRCHIS);
        IBS.init(SCCGWA, BPCTCHIB);
        BPRCHIS.KEY.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRCHIS.KEY.CCY = BPCOLIBT.CCY;
        BPRCHIS.BR = BPCSLIBT.BR;
        BPRCHIS.KEY.PLBOX_NO = "4%";
        BPRCHIS.STS = '0';
        BPCTCHIB.INFO.FUNC = '2';
        BPCTCHIB.POINTER = BPRCHIS;
        BPCTCHIB.LEN = 153;
        S000_CALL_BPZTCHIB();
        if (pgmRtn) return;
        BPCTCHIB.INFO.FUNC = 'N';
        BPCTCHIB.POINTER = BPRCHIS;
        BPCTCHIB.LEN = 153;
        S000_CALL_BPZTCHIB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRCHIS.AMT);
        while (BPCTCHIB.RETURN_INFO != 'N') {
            if (BPRCHIS.IN_OUT == K_CR_FLG) {
                BPCOLIBT.TD_PAY_BAL = BPCOLIBT.TD_PAY_BAL + BPRCHIS.AMT;
            }
            if (BPRCHIS.IN_OUT == K_DR_FLG) {
                BPCOLIBT.TD_RECV_BAL = BPCOLIBT.TD_RECV_BAL + BPRCHIS.AMT;
            }
            BPCTCHIB.INFO.FUNC = 'N';
            BPCTCHIB.POINTER = BPRCHIS;
            BPCTCHIB.LEN = 153;
            S000_CALL_BPZTCHIB();
            if (pgmRtn) return;
        }
        BPCTCHIB.INFO.FUNC = 'E';
        BPCTCHIB.POINTER = BPRCHIS;
        BPCTCHIB.LEN = 153;
        S000_CALL_BPZTCHIB();
        if (pgmRtn) return;
    }
    public void B090_OUTPUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 183;
        SCCMPAG.SCR_ROW_CNT = (short) K_NUM_50;
        SCCMPAG.SCR_COL_CNT = (short) K_NUM_11;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B100_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCOLIBT.BR);
        CEP.TRC(SCCGWA, BPCOLIBT.CCY);
        CEP.TRC(SCCGWA, BPCSLIBT.PLBOX_TP);
        CEP.TRC(SCCGWA, BPCOLIBT.TD_BAL);
        CEP.TRC(SCCGWA, BPCOLIBT.LD_BAL);
        CEP.TRC(SCCGWA, BPCOLIBT.TD_RECV_BAL);
        CEP.TRC(SCCGWA, BPCOLIBT.TD_PAY_BAL);
        CEP.TRC(SCCGWA, BPCOLIBT.CASHBOX_BAL);
        CEP.TRC(SCCGWA, BPCOLIBT.ATM_BAL);
        CEP.TRC(SCCGWA, BPCOLIBT.EQU_CCY);
        CEP.TRC(SCCGWA, BPCOLIBT.TD_EQU_BAL);
        CEP.TRC(SCCGWA, BPCOLIBT.LD_EQU_BAL);
        CEP.TRC(SCCGWA, BPCOLIBT.NEW_YEAR_BAL);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCOLIBT);
        SCCMPAG.DATA_LEN = 183;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B110_GET_CASHLIB_PLBOX_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLVB);
        IBS.init(SCCGWA, BPCRTLVB);
        BPRTLVB.KEY.BR = BPCSLIBT.BR;
        BPRTLVB.PLBOX_TP = BPCSLIBT.PLBOX_TP;
        BPCRTLVB.INFO.FUNC = 'P';
        BPCRTLVB.INFO.LEN = 187;
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        S000_CALL_BPZRTLVB();
        if (pgmRtn) return;
        BPCRTLVB.INFO.FUNC = 'N';
        BPCRTLVB.INFO.LEN = 187;
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        S000_CALL_BPZRTLVB();
        if (pgmRtn) return;
        if (BPCRTLVB.RETURN_INFO == 'N') {
            WS_CASHLIB_FND_FLAG = 'N';
        } else {
            WS_CASHLIB_FND_FLAG = 'Y';
        }
        BPCRTLVB.INFO.FUNC = 'E';
        BPCRTLVB.INFO.LEN = 187;
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        S000_CALL_BPZRTLVB();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZRTLVB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_STARTBR_TLVB, BPCRTLVB);
    }
    public void S000_CALL_BPZTLIBB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_BRE_CLIB, BPCTLIBB);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTLIBB.RC);
        if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CLIB_NOTFND) 
            && BPCTLIBB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTLIBB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTBRIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_BRIS, BPCTBRIS);
    }
    public void S000_CALL_BPZRBRIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_BRW_BRIB, BPCRBRIB);
    }
    public void S000_CALL_BPZSFX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-FX", BPCFX);
        if (BPCFX.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFX.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTTHIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_BRW_THIS, BPCTTHIB);
        if (BPCTTHIB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTTHIB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTCHIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_BRW_CHIS, BPCTCHIB);
        if (BPCTCHIB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTCHIB.RC);
            S000_ERR_MSG_PROC();
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
