package com.hisun.BP;

import com.hisun.SC.*;
import java.text.DecimalFormat;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZUSBOX {
    int JIBS_tmp_int;
    DecimalFormat df;
    DBParm BPTTHIS_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_LIBF = "BP-R-ADW-CLIB    ";
    String CPN_R_HISF = "BP-R-ADW-THIS    ";
    String CPN_P_QTLR = "BP-F-TLR-INF-QUERY";
    String CPN_P_MPCY = "BP-TRAN-AMT-BY-CCY";
    String CPN_P_TLAM = "BP-F-TLR-ACCU-MGM ";
    String CPN_P_VCH_CPNT = "BP-P-VWA-WRITE";
    String CPN_EXCHANGE = "BP-EX         ";
    String CPN_P_CASH_PROD_INQ = "BP-P-CASH-PROD-INQ";
    String CPN_P_LOC_CCY2 = "BP-P-QUERY-BKAI   ";
    String BP_INQUIRE_CCY = "BP-INQUIRE-CCY";
    char K_PROD_BOX_STAT = '1';
    char K_PROD_GOOD_CSKIND = '0';
    String CPN_R_ADW_TLVB = "BP-R-ADW-TLVB";
    String CPN_S_INLI_CTR = "BP-S-INLI-CTR";
    String CPN_S_LIMIT_CTR = "BP-S-LIMIT-CTR";
    String CPN_R_BRW_THIS = "BP-R-BRW-THIS       ";
    String WS_ERR_MSG = " ";
    BPZUSBOX_WS_ERR_INFO WS_ERR_INFO = new BPZUSBOX_WS_ERR_INFO();
    String WS_TEST_INF = " ";
    double WS_AMT_ORI = 0;
    double WS_AC_AMT = 0;
    double WS_TEMP_AMT = 0;
    double WS_VCH_AMT = 0;
    String WS_TEMP_CCY = " ";
    double WS_PROF_AMT = 0;
    char WS_STAT = ' ';
    char WS_CS_KIND = ' ';
    int WS_VCH_CNT = 0;
    String WS_EVENT_CODE = " ";
    char WS_EXG_FLG = ' ';
    char WS_PRIF_FLG = ' ';
    char WS_CCY_TYP = ' ';
    int WS_SEQ2 = 0;
    long WS_CASH_PROD = 0;
    long WS_PROF_PROD = 0;
    int WS_CAN_AMT_CNT = 0;
    char WS_PLBOX_TP = ' ';
    BPZUSBOX_WS_EWA_AC_NO WS_EWA_AC_NO = new BPZUSBOX_WS_EWA_AC_NO();
    BPZUSBOX_WS_EXCHG_AREA WS_EXCHG_AREA = new BPZUSBOX_WS_EXCHG_AREA();
    int WS_SEQ = 0;
    char WS_CASH_FLG = ' ';
    char WS_PROF_FLG = ' ';
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
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPRFHIST BPRFHIST = new BPRFHIST();
    BPCIFHIS BPCIFHIS = new BPCIFHIS();
    BPCTTHIB BPCTTHIB = new BPCTTHIB();
    BPCTLIBF BPCTLIBF = new BPCTLIBF();
    BPCTHISF BPCTHISF = new BPCTHISF();
    BPCFTLAM BPCFTLAM = new BPCFTLAM();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCMPCY BPCMPCY = new BPCMPCY();
    BPCPPRDQ BPCPPRDQ = new BPCPPRDQ();
    BPCEX BPCEX = new BPCEX();
    BPCPQBAI BPCPQBAI = new BPCPQBAI();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCPEBAS BPCPEBAS = new BPCPEBAS();
    BPCTLVBF BPCTLVBF = new BPCTLVBF();
    BPCSINLI BPCSINLI = new BPCSINLI();
    BPCSPBLI BPCSPBLI = new BPCSPBLI();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCUSBOX BPCUSBOX;
    BPCPQBNK_DATA_INFO BPCRBANK;
    SCRCWAT SCRCWA;
    public void MP(SCCGWA SCCGWA, BPCUSBOX BPCUSBOX) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCUSBOX = BPCUSBOX;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZUSBOX return!");
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
        IBS.init(SCCGWA, BPRTLVB);
        IBS.init(SCCGWA, BPCPQBAI);
        WS_SEQ = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_CHK_INPUT_DATA_CH();
            if (pgmRtn) return;
            B020_CHK_TLT_INFO_CN();
            if (pgmRtn) return;
        } else {
            B010_CHK_INPUT_DATA();
            if (pgmRtn) return;
            B020_CHK_TLT_INFO();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B040_PROCESS_LOCAL_CCY_FOR_CN();
            if (pgmRtn) return;
        } else {
            B040_PROCESS_LOCAL_CCY();
            if (pgmRtn) return;
            B100_PLBOX_INLI_CTR();
            if (pgmRtn) return;
        }
    }
    public void B010_CHK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCUSBOX.TLR.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_VBTLR_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCUSBOX.CCY.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCUSBOX.AMT <= 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AMT_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCUSBOX.CCY);
        CEP.TRC(SCCGWA, BPCUSBOX.AMT);
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
    public void B010_CHK_INPUT_DATA_CH() throws IOException,SQLException,Exception {
        if (BPCUSBOX.TLR.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_VBTLR_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCUSBOX.CCY.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCUSBOX.AMT <= 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AMT_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCUSBOX.CCY);
        CEP.TRC(SCCGWA, BPCUSBOX.AMT);
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            if (GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE != SCCGWA.COMM_AREA.AC_DATE) {
                Z_RET();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, BPRTHIS);
            IBS.init(SCCGWA, BPCTTHIB);
            CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_VCH_NO);
            BPCTTHIB.DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            BPCTTHIB.END_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            BPCTTHIB.VCH_NO = GWA_BP_AREA.CANCEL_AREA.CAN_VCH_NO;
            BPCTTHIB.BR = BPCUSBOX.BR;
            BPCTTHIB.FUNC = 'S';
            BPCTTHIB.INFO.POINTER = BPCTTHIB;
            BPCTTHIB.DATA_LEN = 675;
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
                    CEP.TRC(SCCGWA, BPCUSBOX.AMT);
                    if (BPCTTHIB.BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_ORIGIN_BR;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    if (!BPCTTHIB.TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_ORIGIN_TLR;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    if (BPCTTHIB.AMT == BPCUSBOX.AMT) {
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
    public void B020_CHK_TLT_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPCUSBOX.TLR;
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR_STSW);
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASH_TLR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_CHK_TLT_INFO_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPCUSBOX.TLR;
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
    }
    public void B040_PROCESS_LOCAL_CCY_FOR_CN() throws IOException,SQLException,Exception {
        WS_TEMP_CCY = BPCUSBOX.CCY;
        WS_AC_AMT = BPCUSBOX.AMT;
        WS_PROF_AMT = 0;
        if (!BPCUSBOX.CCY.equalsIgnoreCase(BPCRBANK.LOC_CCY1)) {
            B050_PROC_FORIGN_CCY_FOR_CN();
            if (pgmRtn) return;
        }
        B043_PROC_CASH_OUT_FOR_CN();
        if (pgmRtn) return;
    }
    public void B040_PROCESS_LOCAL_CCY() throws IOException,SQLException,Exception {
        WS_TEMP_CCY = BPCUSBOX.CCY;
        WS_STAT = K_PROD_BOX_STAT;
        WS_CS_KIND = K_PROD_GOOD_CSKIND;
        R000_GET_CASH_PRODUCT();
        if (pgmRtn) return;
        S000_GET_LOC_CCY2();
        if (pgmRtn) return;
        WS_TEMP_CCY = BPCUSBOX.CCY;
        if (BPCUSBOX.CCY.equalsIgnoreCase(BPCRBANK.LOC_CCY1) 
            || BPCUSBOX.CCY.equalsIgnoreCase(BPCPQBAI.DATA_INFO.LOC_CCY2)) {
            R000_CAL_PROFIT();
            if (pgmRtn) return;
        } else {
            WS_AC_AMT = BPCUSBOX.AMT;
            WS_PROF_AMT = 0;
        }
        B043_PROC_CASH_OUT();
        if (pgmRtn) return;
    }
    public void B043_PROC_CASH_OUT_FOR_CN() throws IOException,SQLException,Exception {
        if (WS_AC_AMT != 0) {
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
                && GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE != SCCGWA.COMM_AREA.AC_DATE) {
                WS_EVENT_CODE = "CSADCAC";
                WS_VCH_AMT = WS_AC_AMT;
                R000_GEN_CASH_VCH();
                if (pgmRtn) return;
            } else {
                R000_CHK_CASH_INFO();
                if (pgmRtn) return;
                R000_MOD_TLT_CASH_BOX();
                if (pgmRtn) return;
                WS_CASH_PROD = BPCPPRDQ.DATA_INFO.ACCT_CD;
                WS_CASH_FLG = 'C';
                WS_VCH_AMT = WS_AC_AMT;
                if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
                JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
                for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
                if (BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("0")) {
                    if (WS_PLBOX_TP == '6') {
                        WS_EVENT_CODE = "CSATMCF";
                    } else {
                        WS_EVENT_CODE = "CR";
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
                        WS_EVENT_CODE = "CSATMCN";
                    } else {
                        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
                        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
                        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
                        if (BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("2")) {
                            WS_EVENT_CODE = "CSATMCF";
                        }
                    }
                }
                R000_GEN_CASH_VCH();
                if (pgmRtn) return;
                R000_ADD_TR_HISTORY();
                if (pgmRtn) return;
            }
        }
    }
    public void B043_PROC_CASH_OUT() throws IOException,SQLException,Exception {
        if (WS_AC_AMT != 0) {
            R000_CHK_CASH_INFO();
            if (pgmRtn) return;
            R000_MOD_TLT_CASH_BOX();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                WS_CASH_PROD = BPCPPRDQ.DATA_INFO.ACCT_CD;
                WS_CASH_FLG = 'C';
                WS_VCH_AMT = WS_AC_AMT;
                if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
                JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
                for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
                if (!BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_EVENT_CODE = "BOXCSSUB";
                } else {
                    WS_EVENT_CODE = "ATMCSSUB";
                }
                R000_GEN_CASH_VCH();
                if (pgmRtn) return;
            }
            R000_ADD_TR_HISTORY();
            if (pgmRtn) return;
            if (!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("IOBS") 
                && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("ATM") 
                && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("POS")) {
                R000_MOD_TLT_ACCU();
                if (pgmRtn) return;
            }
        }
        if (WS_PROF_AMT != 0) {
            WS_CASH_PROD = BPCPPRDQ.DATA_INFO.PL_CD;
            WS_CASH_FLG = WS_PROF_FLG;
            WS_VCH_AMT = WS_PROF_AMT;
            WS_EVENT_CODE = "CSMORECB";
            R000_GEN_CASH_VCH();
            if (pgmRtn) return;
        }
    }
    public void B041_GET_MIN_CCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = BPCUSBOX.CCY;
        IBS.CALLCPN(SCCGWA, BP_INQUIRE_CCY, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B050_PROC_FORIGN_CCY_FOR_CN() throws IOException,SQLException,Exception {
        B041_GET_MIN_CCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCQCCY.DATA.CHGU_MTH);
        if (BPCQCCY.DATA.CHGU_MTH == '0') {
            WS_EXCHG_AREA.WS_BASE_AMT = 100;
        } else if (BPCQCCY.DATA.CHGU_MTH == '1') {
            WS_EXCHG_AREA.WS_BASE_AMT = 10;
        } else if (BPCQCCY.DATA.CHGU_MTH == '2') {
            WS_EXCHG_AREA.WS_BASE_AMT = 1;
        } else if (BPCQCCY.DATA.CHGU_MTH == '3') {
            WS_EXCHG_AREA.WS_BASE_AMT = 0.1;
        } else if (BPCQCCY.DATA.CHGU_MTH == '4') {
            WS_EXCHG_AREA.WS_BASE_AMT = 0.01;
        } else if (BPCQCCY.DATA.CHGU_MTH == '5') {
            WS_EXCHG_AREA.WS_BASE_AMT = 0.001;
        } else if (BPCQCCY.DATA.CHGU_MTH == '6') {
            WS_EXCHG_AREA.WS_BASE_AMT = 1000;
        } else if (BPCQCCY.DATA.CHGU_MTH == '7') {
            WS_EXCHG_AREA.WS_BASE_AMT = 10000;
        } else {
        }
        CEP.TRC(SCCGWA, BPCUSBOX.AMT);
        WS_EXCHG_AREA.WS_AMTA = (long) ((BPCUSBOX.AMT - BPCUSBOX.AMT % WS_EXCHG_AREA.WS_BASE_AMT) / WS_EXCHG_AREA.WS_BASE_AMT);
        WS_EXCHG_AREA.WS_LEAST_BUY_AMT = BPCUSBOX.AMT - WS_EXCHG_AREA.WS_BASE_AMT * WS_EXCHG_AREA.WS_AMTA;
        CEP.TRC(SCCGWA, WS_EXCHG_AREA.WS_BASE_AMT);
        CEP.TRC(SCCGWA, WS_EXCHG_AREA.WS_AMTA);
        CEP.TRC(SCCGWA, WS_EXCHG_AREA.WS_LEAST_BUY_AMT);
        if (WS_EXCHG_AREA.WS_LEAST_BUY_AMT != 0) {
            WS_ERR_INFO.WS_ERR_CCY = BPCQCCY.DATA.CCY;
            df = new DecimalFormat("00000000000000.00");
            WS_ERR_INFO.WS_ERR_AMT = df.format(WS_EXCHG_AREA.WS_LEAST_BUY_AMT);
            CEP.TRC(SCCGWA, WS_ERR_INFO);
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FORIGN_CCY_NEED_EXCH;
            S000_ERR_MSG_PROC_EXCH();
            if (pgmRtn) return;
        }
    }
    public void R000_CAL_PROFIT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCMPCY);
        BPCMPCY.I_CCY = WS_TEMP_CCY;
        BPCMPCY.I_AMT = BPCUSBOX.AMT;
        BPCMPCY.I_FLG = '0';
        S000_CALL_BPZCMPCY();
        if (pgmRtn) return;
        WS_AC_AMT = BPCMPCY.O_AMT;
        CEP.TRC(SCCGWA, WS_PROF_AMT);
        CEP.TRC(SCCGWA, WS_TEMP_AMT);
        CEP.TRC(SCCGWA, WS_AC_AMT);
        WS_PROF_AMT = BPCUSBOX.AMT - WS_AC_AMT;
        CEP.TRC(SCCGWA, WS_PROF_AMT);
        if (WS_PROF_AMT > 0) {
            WS_PROF_FLG = 'C';
        } else {
            WS_PROF_AMT = ( -1 ) * WS_PROF_AMT;
            WS_PROF_FLG = 'D';
        }
    }
    public void R000_CHK_CASH_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCLIB);
        IBS.init(SCCGWA, BPRTLVB);
        if (BPCUSBOX.CASH_TYP.trim().length() == 0) {
            BPCUSBOX.CASH_TYP = "01";
        }
        BPRTLVB.CRNT_TLR = BPCUSBOX.TLR;
        BPCTLVBF.INFO.FUNC = 'T';
        BPCTLVBF.POINTER = BPRTLVB;
        BPCTLVBF.LEN = 187;
        S000_CALL_BPZTLVBF();
        if (pgmRtn) return;
        if (BPCTLVBF.RETURN_INFO == 'N' 
            || BPRTLVB.KEY.BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLVB_NOTFND);
        }
        WS_PLBOX_TP = BPRTLVB.PLBOX_TP;
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
        BPRCLIB.KEY.CASH_TYP = BPCUSBOX.CASH_TYP;
        BPRCLIB.KEY.CCY = WS_TEMP_CCY;
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
        CEP.TRC(SCCGWA, BPRCLIB.GD_AMT);
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            if (BPRCLIB.BAL < 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CASH_NOT_ENOUGH;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_MOD_TLT_CASH_BOX() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "SBOX FIRST");
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
                BPRCLIB.L_GD_AMT = BPRCLIB.L_GD_AMT + WS_AC_AMT;
                BPRCLIB.L_TLT_AMT = BPRCLIB.L_TLT_AMT + WS_AC_AMT;
            } else {
            }
            BPRCLIB.GD_AMT = BPRCLIB.GD_AMT + WS_AC_AMT;
            BPRCLIB.BAL = BPRCLIB.BAL + WS_AC_AMT;
            if (WS_DATA_OVERFLOW_FLAG == 'Y') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATA_OVERFLOW;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
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
            BPRCLIB.GD_AMT = BPRCLIB.GD_AMT - WS_AC_AMT;
            BPRCLIB.BAL = BPRCLIB.BAL - WS_AC_AMT;
            if (BPRCLIB.BAL < 0) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CASH_NOT_ENOUGH);
            }
            BPRCLIB.BAL_FLG = 'N';
        }
        BPRCLIB.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRCLIB.NEW_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRCLIB.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, "SBOX LAST");
        CEP.TRC(SCCGWA, SCRCWA.AC_DATE_AREA[1-1].LAST_AC_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, BPRCLIB.NEW_DT);
        CEP.TRC(SCCGWA, BPRCLIB.LAST_DT);
        if (!SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            IBS.init(SCCGWA, BPCSPBLI);
            BPCSPBLI.BR = BPRTLVB.KEY.BR;
            BPCSPBLI.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
            S000_CALL_BPZUPBLI();
            if (pgmRtn) return;
        }
        BPCTLIBF.INFO.FUNC = 'U';
        BPCTLIBF.LEN = 352;
        BPCTLIBF.POINTER = BPRCLIB;
        S000_CALL_BPZTLIBF();
        if (pgmRtn) return;
        if (BPCTLIBF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTLIBF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_ADD_TR_HISTORY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTHIS);
        IBS.init(SCCGWA, BPCTHISF);
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            BPRTHIS.KEY.DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPRTHIS.KEY.VCH_NO = SCCGWA.COMM_AREA.VCH_NO;
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.VCH_NO);
            S000_GET_SEQ();
            if (pgmRtn) return;
            IBS.init(SCCGWA, BPRTHIS);
            BPRTHIS.KEY.DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPRTHIS.KEY.VCH_NO = SCCGWA.COMM_AREA.VCH_NO;
            WS_SEQ += 1;
            BPRTHIS.KEY.SEQ = WS_SEQ;
            BPRTHIS.AP_CODE = SCCGWA.COMM_AREA.AP_MMO;
            BPRTHIS.TR_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            BPRTHIS.DC_FLG = 'D';
            BPRTHIS.PAY_PBNO = BPRTLVB.KEY.PLBOX_NO;
            BPRTHIS.CCY = WS_TEMP_CCY;
            if (BPCUSBOX.BR == 0) {
                BPRTHIS.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            } else {
                BPRTHIS.BR = BPCUSBOX.BR;
            }
            BPRTHIS.CCY_TYP = BPCUSBOX.CCY_TYP;
            BPRTHIS.AC = BPCUSBOX.OPP_AC;
            BPRTHIS.AC_NAME = BPCUSBOX.OPP_ACNM;
            BPRTHIS.AMT = WS_AC_AMT;
            BPRTHIS.ID_TYP = BPCUSBOX.ID_TYP;
            BPRTHIS.IDNO = BPCUSBOX.IDNO;
            BPRTHIS.AGT_NAME = BPCUSBOX.AGT_NAME;
            BPRTHIS.AGT_IDTYP = BPCUSBOX.AGT_IDTYP;
            BPRTHIS.AGT_IDNO = BPCUSBOX.AGT_IDNO;
            BPRTHIS.CASH_NO = BPCUSBOX.CASH_NO;
            BPRTHIS.RMK = BPCUSBOX.RMK;
            BPRTHIS.TLR = BPCUSBOX.TLR;
            BPRTHIS.STS = '0';
            CEP.TRC(SCCGWA, BPCUSBOX.APP_NO);
            BPRTHIS.APP_NO = BPCUSBOX.APP_NO;
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.SUP1_ID);
            BPRTHIS.SUP = SCCGWA.COMM_AREA.SUP1_ID;
            BPCTHISF.LEN = 959;
            BPCTHISF.INFO.FUNC = 'A';
            BPCTHISF.POINTER = BPRTHIS;
            S000_CALL_BPZTHISF();
            if (pgmRtn) return;
        } else {
            BPRTHIS.KEY.DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPRTHIS.KEY.VCH_NO = GWA_BP_AREA.CANCEL_AREA.CAN_VCH_NO;
            S000_GET_SEQ();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_VCH_NO);
            IBS.init(SCCGWA, BPRTHIS);
            for (WS_SEQ2 = 1; WS_SEQ2 <= WS_SEQ; WS_SEQ2 += 1) {
                BPRTHIS.KEY.DATE = SCCGWA.COMM_AREA.AC_DATE;
                BPRTHIS.KEY.VCH_NO = GWA_BP_AREA.CANCEL_AREA.CAN_VCH_NO;
                BPRTHIS.KEY.SEQ = WS_SEQ2;
                BPCTHISF.LEN = 959;
                BPCTHISF.INFO.FUNC = 'R';
                BPCTHISF.POINTER = BPRTHIS;
                S000_CALL_BPZTHISF();
                if (pgmRtn) return;
                if (BPRTHIS.STS != '0') {
                } else {
                    BPRTHIS.STS = '1';
                    BPCTHISF.LEN = 959;
                    BPCTHISF.INFO.FUNC = 'U';
                    BPCTHISF.POINTER = BPRTHIS;
                    S000_CALL_BPZTHISF();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void R000_GET_CASH_PRODUCT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPPRDQ);
        BPCPPRDQ.DATA_INFO.CCY = WS_TEMP_CCY;
        BPCPPRDQ.DATA_INFO.STAT = WS_STAT;
        BPCPPRDQ.DATA_INFO.CS_KIND = WS_CS_KIND;
    }
    public void R000_GEN_CASH_VCH() throws IOException,SQLException,Exception {
        R000_SET_EWA_EVENTS();
        if (pgmRtn) return;
    }
    public void R000_SET_EWA_EVENTS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "CAS";
        BPCPOEWA.DATA.EVENT_CODE = WS_EVENT_CODE;
        BPCPOEWA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = WS_TEMP_CCY;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = WS_VCH_AMT;
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void R000_MOD_TLT_ACCU() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLAM);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            BPCFTLAM.OP_CODE = 'D';
        } else {
            BPCFTLAM.OP_CODE = 'A';
        }
        BPCFTLAM.ACCU_TYP = "02";
        BPCFTLAM.TLR = BPCUSBOX.TLR;
        BPCFTLAM.CCY = WS_TEMP_CCY;
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
        BPRTLVB.CRNT_TLR = BPCUSBOX.TLR;
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
    public void S000_CALL_BPZUPBLI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_LIMIT_CTR, BPCSPBLI);
    }
    public void S000_CALL_BPZTLIBF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_LIBF, BPCTLIBF);
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
    public void S000_CALL_BPZTHISF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRTHIS.KEY.DATE);
        CEP.TRC(SCCGWA, BPRTHIS.KEY.VCH_NO);
        CEP.TRC(SCCGWA, BPRTHIS.KEY.SEQ);
        IBS.CALLCPN(SCCGWA, CPN_R_HISF, BPCTHISF);
        if (BPCTHISF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTHISF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_QTLR, BPCFTLRQ);
        CEP.TRC(SCCGWA, BPCFTLRQ.RC);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            CEP.TRC(SCCGWA, WS_ERR_MSG);
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
    public void S000_CALL_BPZPPRDQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_CASH_PROD_INQ, BPCPPRDQ);
        if (BPCPPRDQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPPRDQ.RC);
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
    public void S000_CALL_BPZEX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_EXCHANGE, BPCEX);
        if (BPCEX.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCEX.RC);
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
    public void S000_CALL_BPZTLVBF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_TLVB, BPCTLVBF);
    }
    public void S000_CALL_BPZUINLI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_INLI_CTR, BPCSINLI);
    }
    public void S000_GET_SEQ() throws IOException,SQLException,Exception {
        BPTTHIS_RD = new DBParm();
        BPTTHIS_RD.TableName = "BPTTHIS";
        BPTTHIS_RD.set = "WS-SEQ=COUNT(*)";
        BPTTHIS_RD.where = "'DATE' = :BPRTHIS.KEY.DATE "
            + "AND VCH_NO = :BPRTHIS.KEY.VCH_NO";
        IBS.GROUP(SCCGWA, BPRTHIS, this, BPTTHIS_RD);
        CEP.TRC(SCCGWA, WS_SEQ);
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
    public void S000_ERR_MSG_PROC_EXCH() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INFO);
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
