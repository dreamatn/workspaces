package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZUABOX {
    int JIBS_tmp_int;
    DBParm BPTTHIS_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    double K_BASE_AMT_01 = 0.1;
    String CPN_P_LOC_CCY2 = "BP-P-QUERY-BKAI";
    String CPN_P_VCH_CPNT = "BP-P-VWA-WRITE";
    String CPN_P_QTLR = "BP-F-TLR-INF-QUERY";
    String CPN_P_MPCY = "BP-TRAN-AMT-BY-CCY";
    String CPN_R_CLIB = "BP-R-ADW-CLIB    ";
    String CPN_R_HISF = "BP-R-ADW-THIS    ";
    String CPN_P_CASH_PROD_INQ = "BP-P-CASH-PROD-INQ";
    String CPN_P_TLAM = "BP-F-TLR-ACCU-MGM ";
    String CPN_R_ADW_TLVB = "BP-R-ADW-TLVB";
    String CPN_S_INLI_CTR = "BP-S-INLI-CTR";
    String CPN_S_LIMIT_CTR = "BP-S-LIMIT-CTR";
    String CPN_R_BRW_THIS = "BP-R-BRW-THIS       ";
    String PGM_SCZTIME = "SCZTIME";
    String WS_ERR_MSG = " ";
    int WS_BALFLG_NUM = 0;
    int WS_BAL_NUM = 0;
    long WS_CASH_PROD = 0;
    double WS_PROF_AMT = 0;
    double WS_AC_AMT = 0;
    double WS_TEMP_AMT = 0;
    char WS_PLBOX_TP = ' ';
    BPZUABOX_WS_EWA_AC_NO WS_EWA_AC_NO = new BPZUABOX_WS_EWA_AC_NO();
    int WS_CNT2 = 0;
    String WS_EVENT_CODE = " ";
    int WS_CAN_AMT_CNT = 0;
    int WS_CNT = 0;
    char WS_ACCT_FLAG = ' ';
    char WS_PROF_FLAG = ' ';
    char WS_DATA_OVERFLOW_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    BPRCLIB BPRCLIB = new BPRCLIB();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPRTHIS BPRTHIS = new BPRTHIS();
    BPRFHIST BPRFHIST = new BPRFHIST();
    BPCIFHIS BPCIFHIS = new BPCIFHIS();
    BPCTTHIB BPCTTHIB = new BPCTTHIB();
    BPCPPRDQ BPCPPRDQ = new BPCPPRDQ();
    BPCFTLAM BPCFTLAM = new BPCFTLAM();
    BPCTLIBF BPCTLIBF = new BPCTLIBF();
    BPCTHISF BPCTHISF = new BPCTHISF();
    BPCMPCY BPCMPCY = new BPCMPCY();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCTLVBF BPCTLVBF = new BPCTLVBF();
    BPCSINLI BPCSINLI = new BPCSINLI();
    BPCPQBAI BPCPQBAI = new BPCPQBAI();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCPEBAS BPCPEBAS = new BPCPEBAS();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPCSPBLI BPCSPBLI = new BPCSPBLI();
    SCCTIME SCCTIME = new SCCTIME();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCUABOX BPCUABOX;
    BPCPQBNK_DATA_INFO BPCRBANK;
    SCRCWAT SCRCWA;
    public void MP(SCCGWA SCCGWA, BPCUABOX BPCUABOX) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCUABOX = BPCUABOX;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZUABOX return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        SCRCWA = (SCRCWAT) GWA_SC_AREA.CWA_AREA_PTR;
        IBS.init(SCCGWA, BPCTTHIB);
        IBS.init(SCCGWA, BPRCLIB);
        IBS.init(SCCGWA, BPRTHIS);
        IBS.init(SCCGWA, BPCTLIBF);
        IBS.init(SCCGWA, BPCTHISF);
        IBS.init(SCCGWA, BPCFTLRQ);
        WS_CNT = 0;
        IBS.init(SCCGWA, BPCPQBAI);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_CHK_INPUT_FOR_CN();
            if (pgmRtn) return;
            if (BPCUABOX.AMT != 0 
                && SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
                && GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE != SCCGWA.COMM_AREA.AC_DATE) {
                WS_EVENT_CODE = "CSADCAD";
                WS_TEMP_AMT = BPCUABOX.AMT;
                B080_GEN_CASH_VCH();
                if (pgmRtn) return;
            } else {
                B020_GET_CLIB_RES();
                if (pgmRtn) return;
                B025_NOT_CAL_PROFIT_FOR_CN();
                if (pgmRtn) return;
                B030_CHK_CLIB_RES();
                if (pgmRtn) return;
                B040_REW_CLIB_RES();
                if (pgmRtn) return;
                B050_UPD_CLIB_RES();
                if (pgmRtn) return;
                B060_INT_CHIS_RES();
                if (pgmRtn) return;
                if (WS_AC_AMT != 0) {
                    if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
                    JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
                    for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
                    if (BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("0")) {
                        CEP.TRC(SCCGWA, WS_PLBOX_TP);
                        if (WS_PLBOX_TP == '6') {
                            WS_EVENT_CODE = "CSATMDT";
                        } else {
                            WS_EVENT_CODE = "DR";
                        }
                    } else {
                        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
                        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
                        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
                        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
                        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
                        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
                        if (BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
                            || BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("4")) {
                            WS_EVENT_CODE = "CSATMDN";
                        } else {
                            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
                            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
                            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
                            if (BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("2")) {
                                WS_EVENT_CODE = "CSATMDF";
                            }
                        }
                    }
                    WS_TEMP_AMT = WS_AC_AMT;
                    WS_ACCT_FLAG = 'D';
                    B080_GEN_CASH_VCH();
                    if (pgmRtn) return;
                }
                WS_CASH_PROD = BPCPPRDQ.DATA_INFO.PL_CD;
                CEP.TRC(SCCGWA, WS_PROF_AMT);
                if (WS_PROF_AMT != 0) {
                    WS_EVENT_CODE = "CSMOCB";
                    WS_TEMP_AMT = WS_PROF_AMT;
                    WS_ACCT_FLAG = WS_PROF_FLAG;
                    B080_GEN_CASH_VCH();
                    if (pgmRtn) return;
                }
            }
        } else {
            B010_CHK_INPUT();
            if (pgmRtn) return;
            B020_GET_CLIB_RES();
            if (pgmRtn) return;
            B025_CAL_PROFIT();
            if (pgmRtn) return;
            B030_CHK_CLIB_RES();
            if (pgmRtn) return;
            B040_REW_CLIB_RES();
            if (pgmRtn) return;
            B050_UPD_CLIB_RES();
            if (pgmRtn) return;
            B060_INT_CHIS_RES();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                if (WS_AC_AMT != 0) {
                    if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
                    JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
                    for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
                    if (!BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
                        WS_EVENT_CODE = "BOXCSADD";
                    } else {
                        WS_EVENT_CODE = "ATMCSADD";
                    }
                    WS_TEMP_AMT = WS_AC_AMT;
                    WS_ACCT_FLAG = 'D';
                    B080_GEN_CASH_VCH();
                    if (pgmRtn) return;
                }
                WS_CASH_PROD = BPCPPRDQ.DATA_INFO.PL_CD;
                CEP.TRC(SCCGWA, WS_PROF_AMT);
                if (WS_PROF_AMT != 0) {
                    WS_EVENT_CODE = "CSMORECB";
                    WS_TEMP_AMT = WS_PROF_AMT;
                    WS_ACCT_FLAG = WS_PROF_FLAG;
                    B080_GEN_CASH_VCH();
                    if (pgmRtn) return;
                }
            }
            if (BPCUABOX.AMT != 0) {
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
                if (!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("IOBS") 
                    && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("ATM") 
                    && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("POS")) {
                    B090_MOD_TLT_ACCU();
                    if (pgmRtn) return;
                }
            }
            CEP.TRC(SCCGWA, "STARTTIME");
            S00_GET_TIME();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCCTIME.YYYYMMDD);
            CEP.TRC(SCCGWA, SCCTIME.HHMMSS);
            B100_PLBOX_INLI_CTR();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "ENDTIEM");
            S00_GET_TIME();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCCTIME.YYYYMMDD);
            CEP.TRC(SCCGWA, SCCTIME.HHMMSS);
            IBS.init(SCCGWA, BPCSPBLI);
            BPCSPBLI.BR = BPRTLVB.KEY.BR;
            BPCSPBLI.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
            CEP.TRC(SCCGWA, BPCSPBLI.BR);
            CEP.TRC(SCCGWA, BPCSPBLI.PLBOX_NO);
            S000_CALL_BPZUPBLI();
            if (pgmRtn) return;
        }
    }
    public void B010_CHK_INPUT_FOR_CN() throws IOException,SQLException,Exception {
        if (BPCUABOX.TLR.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_VBTLR_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, BPCFTLRQ);
            BPCFTLRQ.INFO.TLR = BPCUABOX.TLR;
            S000_CALL_BPZFTLRQ();
            if (pgmRtn) return;
        }
        if (BPCUABOX.CCY.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCUABOX.AMT == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AMT_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            if (GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE != SCCGWA.COMM_AREA.AC_DATE) {
                Z_RET();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, BPRTHIS);
            IBS.init(SCCGWA, BPCTTHIB);
            BPCTTHIB.DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            BPCTTHIB.END_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            BPCTTHIB.VCH_NO = GWA_BP_AREA.CANCEL_AREA.CAN_VCH_NO;
            BPCTTHIB.BR = BPCUABOX.BR;
            BPCTTHIB.FUNC = 'S';
            CEP.TRC(SCCGWA, BPCTTHIB.DATE);
            CEP.TRC(SCCGWA, BPCTTHIB.END_DATE);
            CEP.TRC(SCCGWA, BPCTTHIB.VCH_NO);
            CEP.TRC(SCCGWA, BPCTTHIB.BR);
            CEP.TRC(SCCGWA, BPCTTHIB.TLR);
            BPCTTHIB.INFO.POINTER = BPCTTHIB;
            BPCTTHIB.DATA_LEN = 675;
            CEP.TRC(SCCGWA, BPCTTHIB.FUNC);
            S000_CALL_BPZTTHIB();
            if (pgmRtn) return;
            BPCTTHIB.FUNC = 'R';
            BPCTTHIB.INFO.POINTER = BPCTTHIB;
            BPCTTHIB.DATA_LEN = 675;
            S000_CALL_BPZTTHIB();
            if (pgmRtn) return;
            if (BPCTTHIB.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_TX_FOUND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                while (BPCTTHIB.RETURN_INFO != 'N') {
                    CEP.TRC(SCCGWA, BPCTTHIB.STS);
                    CEP.TRC(SCCGWA, BPCTTHIB.AMT);
                    CEP.TRC(SCCGWA, BPCUABOX.AMT);
                    if (BPCTTHIB.BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_ORIGIN_BR;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    CEP.TRC(SCCGWA, "NCB0717001");
                    CEP.TRC(SCCGWA, BPCTTHIB.TLR);
                    CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
                    if (!BPCTTHIB.TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_ORIGIN_TLR;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    if (BPCTTHIB.AMT == BPCUABOX.AMT) {
                        WS_CAN_AMT_CNT += 1;
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
            }
            if (WS_CAN_AMT_CNT == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AMT_INPUT_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_CHK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCUABOX.TLR);
        CEP.TRC(SCCGWA, BPCUABOX.BR);
        CEP.TRC(SCCGWA, BPCUABOX.CCY);
        CEP.TRC(SCCGWA, BPCUABOX.AMT);
        if (BPCUABOX.TLR.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_VBTLR_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, BPCFTLRQ);
            BPCFTLRQ.INFO.TLR = BPCUABOX.TLR;
            S000_CALL_BPZFTLRQ();
            if (pgmRtn) return;
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (!BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASH_TLR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCUABOX.CCY.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCUABOX.AMT == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AMT_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            if (GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE != SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CANCEL_NO_TERTIAN;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, BPCIFHIS);
            IBS.init(SCCGWA, BPRFHIST);
            BPCIFHIS.INPUT.FUNC = '5';
            BPRFHIST.KEY.AC_DT = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            BPRFHIST.KEY.JRNNO = GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
            BPRFHIST.KEY.JRN_SEQ = 1;
            BPCIFHIS.INPUT.REC_PT = BPRFHIST;
            BPCIFHIS.INPUT.REC_LEN = 690;
            S000_CALL_BPZIFHIS();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPRFHIST.TX_BR);
            CEP.TRC(SCCGWA, BPRFHIST.TX_TLR);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
            if (BPRFHIST.TX_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_ORIGIN_BR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_GET_CLIB_RES() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCLIB);
        IBS.init(SCCGWA, BPCTLIBF);
        IBS.init(SCCGWA, BPRTLVB);
        if (BPCUABOX.CASH_TYP.trim().length() == 0) {
            BPCUABOX.CASH_TYP = "01";
        }
        BPRTLVB.CRNT_TLR = BPCUABOX.TLR;
        CEP.TRC(SCCGWA, BPRTLVB.PLBOX_TP);
        CEP.TRC(SCCGWA, BPRTLVB.CRNT_TLR);
        BPCTLVBF.INFO.FUNC = 'T';
        BPCTLVBF.POINTER = BPRTLVB;
        BPCTLVBF.LEN = 187;
        S000_CALL_BPZTLVBF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRTLVB.KEY.BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        if (BPCTLVBF.RETURN_INFO == 'N' 
            || BPRTLVB.KEY.BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLVB_NOTFND);
        }
        if (BPRTLVB.PLBOX_TP == '4') {
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("0")) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_ATMBOX_NOT_ATM_TLR);
            }
        } else {
            if (BPRTLVB.PLBOX_TP == '3' 
                || BPRTLVB.PLBOX_TP == '5' 
                || BPRTLVB.PLBOX_TP == '6') {
                if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
                JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
                for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
                if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
                JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
                for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
                if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
                JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
                for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
                if (BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
                    || BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("2") 
                    || BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("4")) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CASHBOX_NOT_ATMTLR);
                }
            } else {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CLIB_FND);
            }
        }
        BPRCLIB.KEY.BR = BPRTLVB.KEY.BR;
        BPRCLIB.KEY.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
        BPRCLIB.KEY.CASH_TYP = BPCUABOX.CASH_TYP;
        BPRCLIB.KEY.CCY = BPCUABOX.CCY;
        WS_PLBOX_TP = BPRTLVB.PLBOX_TP;
        CEP.TRC(SCCGWA, BPRCLIB.KEY.BR);
        CEP.TRC(SCCGWA, BPRCLIB.KEY.PLBOX_NO);
        CEP.TRC(SCCGWA, BPRCLIB.KEY.CASH_TYP);
        CEP.TRC(SCCGWA, BPRCLIB.KEY.CCY);
        BPCTLIBF.INFO.FUNC = 'R';
        BPCTLIBF.POINTER = BPRCLIB;
        BPCTLIBF.LEN = 352;
        S000_CALL_BPZTLIBF();
        if (pgmRtn) return;
        if (BPCTLIBF.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PLBOX_NO_THIS_CCY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            if (BPRCLIB.BAL < 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CASH_NOT_ENOUGH;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B025_NOT_CAL_PROFIT_FOR_CN() throws IOException,SQLException,Exception {
        WS_AC_AMT = BPCUABOX.AMT;
        WS_PROF_AMT = 0;
    }
    public void B025_CAL_PROFIT() throws IOException,SQLException,Exception {
        S000_GET_LOC_CCY2();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCMPCY);
        BPCMPCY.I_CCY = BPCUABOX.CCY;
        BPCMPCY.I_AMT = BPCUABOX.AMT;
        BPCMPCY.I_FLG = '0';
        if (BPCUABOX.CCY.equalsIgnoreCase(BPCRBANK.LOC_CCY1) 
            || BPCUABOX.CCY.equalsIgnoreCase(BPCPQBAI.DATA_INFO.LOC_CCY2)) {
            S000_CALL_BPZCMPCY();
            if (pgmRtn) return;
            WS_AC_AMT = BPCMPCY.O_AMT;
        } else {
            WS_AC_AMT = BPCUABOX.AMT;
        }
        WS_PROF_AMT = BPCUABOX.AMT - WS_AC_AMT;
        CEP.TRC(SCCGWA, WS_PROF_AMT);
        CEP.TRC(SCCGWA, BPCUABOX.AMT);
        CEP.TRC(SCCGWA, WS_AC_AMT);
        if (WS_PROF_AMT > 0) {
            WS_PROF_FLAG = 'D';
            WS_AC_AMT = WS_AC_AMT + K_BASE_AMT_01;
            WS_PROF_AMT = K_BASE_AMT_01 - WS_PROF_AMT;
        } else {
            WS_PROF_AMT = ( -1 ) * WS_PROF_AMT;
            WS_PROF_FLAG = 'C';
        }
    }
    public void B030_CHK_CLIB_RES() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "ABOX FIRST");
        CEP.TRC(SCCGWA, SCRCWA.AC_DATE_AREA[1-1].LAST_AC_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, BPRCLIB.NEW_DT);
        CEP.TRC(SCCGWA, BPRCLIB.LAST_DT);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            if (SCCGWA.COMM_AREA.AC_DATE > BPRCLIB.NEW_DT) {
                BPRCLIB.L_TLT_AMT = BPRCLIB.BAL;
                BPRCLIB.L_GD_AMT = BPRCLIB.GD_AMT;
                BPRCLIB.L_BD_AMT = BPRCLIB.BD_AMT;
                BPRCLIB.L_HBD_AMT = BPRCLIB.HBD_AMT;
                BPRCLIB.LAST_DT = SCRCWA.AC_DATE_AREA[1-1].LAST_AC_DATE;
            } else if (SCCGWA.COMM_AREA.AC_DATE == BPRCLIB.NEW_DT) {
            } else if (SCCGWA.COMM_AREA.AC_DATE < BPRCLIB.NEW_DT) {
                BPRCLIB.L_GD_AMT = BPRCLIB.L_GD_AMT - WS_AC_AMT;
                BPRCLIB.L_TLT_AMT = BPRCLIB.L_TLT_AMT - WS_AC_AMT;
            } else {
            }
            BPRCLIB.BAL = BPRCLIB.BAL - WS_AC_AMT;
            BPRCLIB.GD_AMT = BPRCLIB.GD_AMT - WS_AC_AMT;
        } else {
            if (SCCGWA.COMM_AREA.AC_DATE > BPRCLIB.NEW_DT) {
                BPRCLIB.L_TLT_AMT = BPRCLIB.BAL;
                BPRCLIB.L_GD_AMT = BPRCLIB.GD_AMT;
                BPRCLIB.L_BD_AMT = BPRCLIB.BD_AMT;
                BPRCLIB.L_HBD_AMT = BPRCLIB.HBD_AMT;
                BPRCLIB.LAST_DT = SCRCWA.AC_DATE_AREA[1-1].LAST_AC_DATE;
            } else if (SCCGWA.COMM_AREA.AC_DATE == BPRCLIB.NEW_DT) {
            } else if (SCCGWA.COMM_AREA.AC_DATE < BPRCLIB.NEW_DT) {
                BPRCLIB.L_GD_AMT = BPRCLIB.L_GD_AMT + WS_AC_AMT;
                BPRCLIB.L_TLT_AMT = BPRCLIB.L_TLT_AMT + WS_AC_AMT;
            } else {
            }
            BPRCLIB.BAL = WS_AC_AMT + BPRCLIB.BAL;
            CEP.TRC(SCCGWA, BPRCLIB.BAL);
            BPRCLIB.GD_AMT = WS_AC_AMT + BPRCLIB.GD_AMT;
            CEP.TRC(SCCGWA, BPRCLIB.GD_AMT);
            if (WS_DATA_OVERFLOW_FLAG == 'Y') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATA_OVERFLOW;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, "ABOX LAST");
        CEP.TRC(SCCGWA, SCRCWA.AC_DATE_AREA[1-1].LAST_AC_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, BPRCLIB.NEW_DT);
        CEP.TRC(SCCGWA, BPRCLIB.LAST_DT);
        if (BPRCLIB.BAL < 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CASH_NOT_ENOUGH);
        }
    }
    public void B040_REW_CLIB_RES() throws IOException,SQLException,Exception {
        BPRCLIB.BAL_FLG = 'N';
        BPRCLIB.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRCLIB.NEW_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRCLIB.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
    }
    public void B050_UPD_CLIB_RES() throws IOException,SQLException,Exception {
        BPCTLIBF.INFO.FUNC = 'U';
        BPCTLIBF.POINTER = BPRCLIB;
        BPCTLIBF.LEN = 352;
        CEP.TRC(SCCGWA, BPRCLIB);
        S000_CALL_BPZTLIBF();
        if (pgmRtn) return;
        if (BPCTLIBF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTLIBF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B060_INT_CHIS_RES() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.VCH_NO);
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_VCH_NO);
        IBS.init(SCCGWA, BPRTHIS);
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            BPRTHIS.KEY.DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPRTHIS.KEY.VCH_NO = SCCGWA.COMM_AREA.VCH_NO;
            S000_GET_SEQ();
            if (pgmRtn) return;
            IBS.init(SCCGWA, BPRTHIS);
            BPRTHIS.KEY.DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPRTHIS.KEY.VCH_NO = SCCGWA.COMM_AREA.VCH_NO;
            WS_CNT += 1;
            BPRTHIS.KEY.SEQ = WS_CNT;
            BPRTHIS.AP_CODE = SCCGWA.COMM_AREA.AP_MMO;
            BPRTHIS.TR_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            BPRTHIS.DC_FLG = 'C';
            BPRTHIS.RCE_PBNO = BPRTLVB.KEY.PLBOX_NO;
            BPRTHIS.CCY = BPCUABOX.CCY;
            BPRTHIS.CCY_TYP = BPCUABOX.CCY_TYP;
            if (BPCUABOX.BR == 0) {
                BPRTHIS.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            } else {
                BPRTHIS.BR = BPCUABOX.BR;
            }
            BPRTHIS.AC = BPCUABOX.OPP_AC;
            BPRTHIS.AC_NAME = BPCUABOX.OPP_ACNM;
            BPRTHIS.AMT = WS_AC_AMT;
            BPRTHIS.ID_TYP = BPCUABOX.ID_TYP;
            BPRTHIS.IDNO = BPCUABOX.IDNO;
            BPRTHIS.AGT_NAME = BPCUABOX.AGT_NAME;
            BPRTHIS.AGT_IDTYP = BPCUABOX.AGT_IDTYP;
            BPRTHIS.AGT_IDNO = BPCUABOX.AGT_IDNO;
            BPRTHIS.CASH_NO = BPCUABOX.CASH_NO;
            BPRTHIS.RMK = BPCUABOX.RMK;
            BPRTHIS.TLR = BPCUABOX.TLR;
            BPRTHIS.AC_TYP = BPCUABOX.AC_TYP;
            CEP.TRC(SCCGWA, BPCUABOX.APP_NO);
            BPRTHIS.APP_NO = BPCUABOX.APP_NO;
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.SUP1_ID);
            BPRTHIS.SUP = SCCGWA.COMM_AREA.SUP1_ID;
            BPRTHIS.STS = '0';
            BPCTHISF.INFO.FUNC = 'A';
            BPCTHISF.POINTER = BPRTHIS;
            BPCTHISF.LEN = 959;
            S000_CALL_BPZTHISF();
            if (pgmRtn) return;
        } else {
            BPRTHIS.KEY.DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPRTHIS.KEY.VCH_NO = GWA_BP_AREA.CANCEL_AREA.CAN_VCH_NO;
            S000_GET_SEQ();
            if (pgmRtn) return;
            IBS.init(SCCGWA, BPRTHIS);
            for (WS_CNT2 = 1; WS_CNT2 <= WS_CNT; WS_CNT2 += 1) {
                BPRTHIS.KEY.DATE = SCCGWA.COMM_AREA.AC_DATE;
                BPRTHIS.KEY.VCH_NO = GWA_BP_AREA.CANCEL_AREA.CAN_VCH_NO;
                BPRTHIS.KEY.SEQ = WS_CNT2;
                BPCTHISF.INFO.FUNC = 'R';
                BPCTHISF.POINTER = BPRTHIS;
                BPCTHISF.LEN = 959;
                S000_CALL_BPZTHISF();
                if (pgmRtn) return;
                if (BPRTHIS.STS != '0') {
                    CEP.TRC(SCCGWA, "NOT THIS-NORMA");
                } else {
                    BPRTHIS.STS = '1';
                    BPCTHISF.INFO.FUNC = 'U';
                    BPCTHISF.POINTER = BPRTHIS;
                    BPCTHISF.LEN = 959;
                    S000_CALL_BPZTHISF();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, "FIND ERR");
                }
            }
        }
    }
    public void B070_GET_PROD_RES() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPPRDQ);
        BPCPPRDQ.DATA_INFO.CCY = BPCUABOX.CCY;
        BPCPPRDQ.DATA_INFO.STAT = '0';
        BPCPPRDQ.DATA_INFO.CS_KIND = '0';
        S000_CALL_BPZPPRDQ();
        if (pgmRtn) return;
        WS_CASH_PROD = BPCPPRDQ.DATA_INFO.ACCT_CD;
        CEP.TRC(SCCGWA, WS_CASH_PROD);
    }
    public void S000_GET_SEQ() throws IOException,SQLException,Exception {
        BPTTHIS_RD = new DBParm();
        BPTTHIS_RD.TableName = "BPTTHIS";
        BPTTHIS_RD.set = "WS-CNT=COUNT(*)";
        BPTTHIS_RD.where = "'DATE' = :BPRTHIS.KEY.DATE "
            + "AND VCH_NO = :BPRTHIS.KEY.VCH_NO";
        IBS.GROUP(SCCGWA, BPRTHIS, this, BPTTHIS_RD);
        CEP.TRC(SCCGWA, WS_CNT);
    }
    public void B080_GEN_CASH_VCH() throws IOException,SQLException,Exception {
        B082_SET_EWA_EVENTS();
        if (pgmRtn) return;
    }
    public void B082_SET_EWA_EVENTS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "CAS";
        BPCPOEWA.DATA.EVENT_CODE = WS_EVENT_CODE;
        BPCPOEWA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCUABOX.CCY;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = WS_TEMP_AMT;
        CEP.TRC(SCCGWA, WS_TEMP_AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AC_NO);
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void B090_MOD_TLT_ACCU() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLAM);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            BPCFTLAM.OP_CODE = 'D';
        } else {
            BPCFTLAM.OP_CODE = 'A';
        }
        BPCFTLAM.ACCU_TYP = "01";
        BPCFTLAM.TLR = BPCUABOX.TLR;
        BPCFTLAM.CCY = BPCUABOX.CCY;
        BPCFTLAM.AMT = WS_AC_AMT;
        S000_CALL_BPZFTLAM();
        if (pgmRtn) return;
    }
    public void B100_PLBOX_INLI_CTR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSINLI);
        BPCSINLI.BR = BPRTLVB.KEY.BR;
        S000_CALL_BPZUINLI();
        if (pgmRtn) return;
    }
    public void B110_GET_LONGTOU_CLIB_RES() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLVB);
        IBS.init(SCCGWA, BPCTLVBF);
        BPRTLVB.PLBOX_TP = '5';
        BPRTLVB.CRNT_TLR = BPCUABOX.TLR;
        BPCTLVBF.INFO.FUNC = 'I';
        BPCTLVBF.POINTER = BPRTLVB;
        BPCTLVBF.LEN = 187;
        S000_CALL_BPZTLVBF();
        if (pgmRtn) return;
        if (BPCTLVBF.RETURN_INFO == 'N' 
            || BPRTLVB.KEY.BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLVB_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTLIBF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_CLIB, BPCTLIBF);
        CEP.TRC(SCCGWA, BPCTLIBF.RC.RC_CODE);
    }
    public void S000_CALL_BPZUPBLI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_LIMIT_CTR, BPCSPBLI);
    }
    public void S000_CALL_BPZTHISF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRTHIS.KEY.DATE);
        CEP.TRC(SCCGWA, BPRTHIS.KEY.VCH_NO);
        CEP.TRC(SCCGWA, BPRTHIS.KEY.SEQ);
        IBS.CALLCPN(SCCGWA, CPN_R_HISF, BPCTHISF);
        CEP.TRC(SCCGWA, BPCTHISF.RC.RC_CODE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (BPCTHISF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTHISF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPPRDQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_CASH_PROD_INQ, BPCPPRDQ);
        if (BPCPPRDQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPPRDQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_QTLR, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFTLAM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_TLAM, BPCFTLAM);
        if (BPCFTLAM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLAM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPEBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-BASIC-EWA", BPCPEBAS);
        if (BPCPEBAS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPEBAS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZCMPCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_MPCY, BPCMPCY);
        if (BPCMPCY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCMPCY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZIFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-INQ-FHIST", BPCIFHIS);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCIFHIS.OUTPUT.RC);
        if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL) 
            && !JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_END_OF_TABLE)) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCIFHIS.OUTPUT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQPCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-PC ", BPCOQPCD);
        if (BPCOQPCD.RC.RC_CODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CASH_NO_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTLVBF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_TLVB, BPCTLVBF);
    }
    public void S000_CALL_BPZUINLI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_INLI_CTR, BPCSINLI);
    }
    public void S00_GET_TIME() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCTIME);
        SCZTIME SCZTIME = new SCZTIME();
        SCZTIME.MP(SCCGWA, SCCTIME);
    }
    public void S000_GET_LOC_CCY2() throws IOException,SQLException,Exception {
        BPCPQBAI.DATA_INFO.BNK = SCCGWA.COMM_AREA.TR_BANK;
        IBS.CALLCPN(SCCGWA, CPN_P_LOC_CCY2, BPCPQBAI);
        if (BPCPQBAI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQBAI.RC);
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
        if (BPCUABOX.RTNCODE != 0) {
            CEP.TRC(SCCGWA, "BPCUABOX = ");
            CEP.TRC(SCCGWA, BPCUABOX);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
