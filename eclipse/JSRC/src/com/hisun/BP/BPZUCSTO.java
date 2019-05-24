package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZUCSTO {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_MAINT_CLIB = "BP-R-ADW-CLIB";
    String CPN_R_MAINT_CLBI = "BP-R-ADW-CLBI";
    String CPN_P_VCH_CPNT = "BP-P-VWA-WRITE";
    String CPN_P_INQ_PROD = "BP-P-CASH-PROD-INQ";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String CPN_R_STARTBR_TLVB = "BP-R-STARTBR-TLVB";
    String CPN_R_ADW_TLVB = "BP-R-ADW-TLVB";
    String CPN_P_ADD_CASH_HIS = "BP-P-ADD-CHIS";
    String CPN_S_INLI_CTR = "BP-S-INLI-CTR";
    String CPN_S_LIMIT_CTR = "BP-S-LIMIT-CTR";
    int K_PAR_MAX_CNT = 20;
    String K_GEN_REMARK = "CASH OUT";
    char K_STSW_FLG_Y = '1';
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    long WS_ACCT_CD = 0;
    String WS_MGR_TLR = " ";
    char WS_PLBOX_TP = ' ';
    double WS_TMP_AMT = 0;
    BPZUCSTO_WS_EWA_AC_NO WS_EWA_AC_NO = new BPZUCSTO_WS_EWA_AC_NO();
    char WS_FND_PAR_FLG = ' ';
    char WS_DATA_OVERFLOW_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRCLIB BPRCLIB = new BPRCLIB();
    BPRCLBI BPRCLBI = new BPRCLBI();
    BPCTLIBF BPCTLIBF = new BPCTLIBF();
    BPCTLBIF BPCTLBIF = new BPCTLBIF();
    BPCTLVBF BPCTLVBF = new BPCTLVBF();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPCRTLVB BPCRTLVB = new BPCRTLVB();
    BPCSINLI BPCSINLI = new BPCSINLI();
    BPCPPRDQ BPCPPRDQ = new BPCPPRDQ();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCPCHIS BPCPCHIS = new BPCPCHIS();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCPEBAS BPCPEBAS = new BPCPEBAS();
    BPCSPBLI BPCSPBLI = new BPCSPBLI();
    SCCGWA SCCGWA;
    BPCUCSTO BPCUCSTO;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCRCWAT SCRCWA;
    public void MP(SCCGWA SCCGWA, BPCUCSTO BPCUCSTO) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCUCSTO = BPCUCSTO;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZUCSTO return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCRCWA = (SCRCWAT) GWA_SC_AREA.CWA_AREA_PTR;
        IBS.init(SCCGWA, BPCUCSTO.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B020_UPDATE_CASH_INFO_FOR_CN();
            if (pgmRtn) return;
            B030_GEN_CASH_VCH_FOR_CN();
            if (pgmRtn) return;
        } else {
            B020_UPDATE_CASH_INFO();
            if (pgmRtn) return;
            B030_GEN_CASH_VCH();
            if (pgmRtn) return;
            B050_PLBOX_INLI_CTR();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "TOEND");
        CEP.TRC(SCCGWA, BPRCLIB.BAL);
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCUCSTO.VB_BR);
        CEP.TRC(SCCGWA, BPCUCSTO.VB_TLR);
        CEP.TRC(SCCGWA, BPCUCSTO.TX_AMT);
        CEP.TRC(SCCGWA, BPCUCSTO.CCY);
        CEP.TRC(SCCGWA, BPCUCSTO.VB_FLG);
        if (BPCUCSTO.VB_BR == 0 
            && BPCUCSTO.VB_TLR.trim().length() == 0 
            || BPCUCSTO.VB_FLG != '0' 
            && BPCUCSTO.VB_FLG != '1' 
            || BPCUCSTO.CCY.trim().length() == 0) {
            CEP.TRC(SCCGWA, "DEV11");
            CEP.TRC(SCCGWA, "M-BP-INPUT-ERROR");
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCUCSTO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.CHNL == null) SCCGWA.COMM_AREA.CHNL = "";
        JIBS_tmp_int = SCCGWA.COMM_AREA.CHNL.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) SCCGWA.COMM_AREA.CHNL += " ";
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL.substring(0, 3));
        CEP.TRC(SCCGWA, BPCUCSTO.TX_AMT);
        if (SCCGWA.COMM_AREA.CHNL == null) SCCGWA.COMM_AREA.CHNL = "";
        JIBS_tmp_int = SCCGWA.COMM_AREA.CHNL.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) SCCGWA.COMM_AREA.CHNL += " ";
        if (!SCCGWA.COMM_AREA.CHNL.substring(0, 3).equalsIgnoreCase("103") 
            && BPCUCSTO.TX_AMT == 0) {
            CEP.TRC(SCCGWA, "DEV12");
            CEP.TRC(SCCGWA, "M-BP-INPUT-ERROR");
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCUCSTO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            if (GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE != SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CANCEL_NO_TERTIAN;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_UPDATE_CASH_INFO() throws IOException,SQLException,Exception {
        B02_01_UPDATE_CASH_BAL();
        if (pgmRtn) return;
        if (BPCUCSTO.VB_FLG == '1') {
            B02_02_UPDATE_CASH_PAR();
            if (pgmRtn) return;
        }
    }
    public void B020_UPDATE_CASH_INFO_FOR_CN() throws IOException,SQLException,Exception {
        B02_01_UPDATE_CASH_BAL_FOR_CN();
        if (pgmRtn) return;
        if (BPCUCSTO.VB_FLG == '1') {
            B02_02_UPDATE_CASH_PAR();
            if (pgmRtn) return;
        }
    }
    public void B02_01_UPDATE_CASH_BAL_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTLIBF);
        IBS.init(SCCGWA, BPRCLIB);
        if (BPCUCSTO.VB_FLG == '0') {
            CEP.TRC(SCCGWA, BPCUCSTO.PLBOX_TYP);
            CEP.TRC(SCCGWA, BPCUCSTO.VB_TLR);
            BPRTLVB.PLBOX_TP = BPCUCSTO.PLBOX_TYP;
            BPRTLVB.CRNT_TLR = BPCUCSTO.VB_TLR;
            BPCTLVBF.INFO.FUNC = 'I';
            BPCTLVBF.POINTER = BPRTLVB;
            BPCTLVBF.LEN = 187;
            S000_CALL_BPZTLVBF();
            if (pgmRtn) return;
            if (BPCTLVBF.RETURN_INFO == 'N' 
                || BPRTLVB.KEY.BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                B060_GET_LONGTOU_CLIB_RES();
                if (pgmRtn) return;
            }
            BPRCLIB.KEY.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
            BPCUCSTO.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
            WS_MGR_TLR = BPCUCSTO.VB_TLR;
            WS_PLBOX_TP = BPRTLVB.PLBOX_TP;
        } else {
            BPRTLVB.KEY.BR = BPCUCSTO.VB_BR;
            BPRTLVB.KEY.PLBOX_NO = "%%%%%%";
            BPCRTLVB.INFO.FUNC = 'Q';
            BPCRTLVB.INFO.LEN = 187;
            BPCRTLVB.INFO.POINTER = BPRTLVB;
            S000_CALL_BPZRTLVB();
            if (pgmRtn) return;
            BPCRTLVB.INFO.FUNC = 'N';
            BPCRTLVB.INFO.POINTER = BPRTLVB;
            BPCRTLVB.INFO.LEN = 187;
            S000_CALL_BPZRTLVB();
            if (pgmRtn) return;
            WS_CNT = 0;
            while (BPCRTLVB.RETURN_INFO != 'N' 
                && WS_CNT <= 1000) {
                WS_CNT += 1;
                if (BPRTLVB.PLBOX_TP == '1' 
                    || BPRTLVB.PLBOX_TP == '2' 
                    || BPRTLVB.PLBOX_TP == '5') {
                    WS_MGR_TLR = BPRTLVB.CRNT_TLR;
                    WS_PLBOX_TP = BPRTLVB.PLBOX_TP;
                    BPRCLIB.KEY.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
                    BPCUCSTO.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
                    WS_CNT = 1001;
                    CEP.TRC(SCCGWA, WS_CNT);
                }
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
            if (BPCRTLVB.RETURN_INFO != 'F') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        BPRCLIB.KEY.CCY = BPCUCSTO.CCY;
        BPRCLIB.KEY.BR = BPRTLVB.KEY.BR;
        BPRCLIB.KEY.CASH_TYP = BPCUCSTO.CASH_TYP;
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
        if (BPRCLIB.BAL < 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CASH_NOT_ENOUGH);
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            WS_TMP_AMT = BPRCLIB.BAL + BPCUCSTO.TX_AMT;
            if (WS_DATA_OVERFLOW_FLAG == 'Y') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATA_OVERFLOW;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            WS_TMP_AMT = BPRCLIB.BAL - BPCUCSTO.TX_AMT;
            if (BPRCLIB.BAL < BPCUCSTO.TX_AMT) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CASH_NOT_ENOUGH);
            }
        }
        CEP.TRC(SCCGWA, WS_TMP_AMT);
        CEP.TRC(SCCGWA, BPRCLIB.BAL);
        CEP.TRC(SCCGWA, BPCUCSTO.TX_AMT);
        if (SCCGWA.COMM_AREA.AC_DATE > BPRCLIB.NEW_DT) {
            BPRCLIB.L_TLT_AMT = BPRCLIB.BAL;
            if (BPCUCSTO.CS_KIND == '0') {
                BPRCLIB.L_GD_AMT = BPRCLIB.GD_AMT;
            }
            if (BPCUCSTO.CS_KIND == '2') {
                BPRCLIB.L_BD_AMT = BPRCLIB.BD_AMT;
            }
            if (BPCUCSTO.CS_KIND == '3') {
                BPRCLIB.L_HBD_AMT = BPRCLIB.HBD_AMT;
            }
            BPRCLIB.L_GD_AMT = BPRCLIB.GD_AMT;
            BPRCLIB.L_BD_AMT = BPRCLIB.BD_AMT;
            BPRCLIB.L_HBD_AMT = BPRCLIB.HBD_AMT;
            BPRCLIB.LAST_DT = SCRCWA.AC_DATE_AREA[1-1].LAST_AC_DATE;
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            BPRCLIB.BAL = BPRCLIB.BAL + BPCUCSTO.TX_AMT;
            if (SCCGWA.COMM_AREA.AC_DATE < BPRCLIB.NEW_DT) {
                BPRCLIB.L_TLT_AMT = BPRCLIB.L_TLT_AMT + BPCUCSTO.TX_AMT;
            }
            if (BPCUCSTO.CS_KIND == '0') {
                BPRCLIB.GD_AMT = BPRCLIB.GD_AMT + BPCUCSTO.TX_AMT;
                if (SCCGWA.COMM_AREA.AC_DATE < BPRCLIB.NEW_DT) {
                    BPRCLIB.L_GD_AMT = BPRCLIB.L_GD_AMT + BPCUCSTO.TX_AMT;
                }
            } else if (BPCUCSTO.CS_KIND == '2') {
                BPRCLIB.BD_AMT = BPRCLIB.BD_AMT + BPCUCSTO.TX_AMT;
                if (SCCGWA.COMM_AREA.AC_DATE < BPRCLIB.NEW_DT) {
                    BPRCLIB.L_BD_AMT = BPRCLIB.L_BD_AMT + BPCUCSTO.TX_AMT;
                }
            } else if (BPCUCSTO.CS_KIND == '3') {
                BPRCLIB.HBD_AMT = BPRCLIB.HBD_AMT + BPCUCSTO.TX_AMT;
                if (SCCGWA.COMM_AREA.AC_DATE < BPRCLIB.NEW_DT) {
                    BPRCLIB.L_HBD_AMT = BPRCLIB.L_HBD_AMT + BPCUCSTO.TX_AMT;
                }
            } else {
            }
            if (WS_DATA_OVERFLOW_FLAG == 'Y') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATA_OVERFLOW;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            BPRCLIB.BAL = BPRCLIB.BAL - BPCUCSTO.TX_AMT;
            if (BPRCLIB.BAL < 0) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CASH_NOT_ENOUGH);
            }
            if (SCCGWA.COMM_AREA.AC_DATE < BPRCLIB.NEW_DT) {
                BPRCLIB.L_TLT_AMT = BPRCLIB.L_TLT_AMT - BPCUCSTO.TX_AMT;
            }
            if (BPCUCSTO.CS_KIND == '0') {
                BPRCLIB.GD_AMT = BPRCLIB.GD_AMT - BPCUCSTO.TX_AMT;
                if (SCCGWA.COMM_AREA.AC_DATE < BPRCLIB.NEW_DT) {
                    BPRCLIB.L_GD_AMT = BPRCLIB.L_GD_AMT - BPCUCSTO.TX_AMT;
                }
            } else if (BPCUCSTO.CS_KIND == '2') {
                BPRCLIB.BD_AMT = BPRCLIB.BD_AMT - BPCUCSTO.TX_AMT;
                if (SCCGWA.COMM_AREA.AC_DATE < BPRCLIB.NEW_DT) {
                    BPRCLIB.L_BD_AMT = BPRCLIB.L_BD_AMT - BPCUCSTO.TX_AMT;
                }
            } else if (BPCUCSTO.CS_KIND == '3') {
                BPRCLIB.HBD_AMT = BPRCLIB.HBD_AMT - BPCUCSTO.TX_AMT;
                if (SCCGWA.COMM_AREA.AC_DATE < BPRCLIB.NEW_DT) {
                    BPRCLIB.L_HBD_AMT = BPRCLIB.L_HBD_AMT - BPCUCSTO.TX_AMT;
                }
            } else {
            }
        }
        CEP.TRC(SCCGWA, BPRCLIB.BAL);
        IBS.init(SCCGWA, BPCTLIBF.RC);
        BPRCLIB.BAL_FLG = 'N';
        BPRCLIB.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRCLIB.NEW_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRCLIB.UPD_TLR = BPCUCSTO.VB_TLR;
        BPCTLIBF.INFO.FUNC = 'U';
        BPCTLIBF.POINTER = BPRCLIB;
        BPCTLIBF.LEN = 352;
        S000_CALL_BPZTLIBF();
        if (pgmRtn) return;
        if (BPCTLIBF.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PLBOX_NO_THIS_CCY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B02_01_UPDATE_CASH_BAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTLIBF);
        IBS.init(SCCGWA, BPRCLIB);
        if (BPCUCSTO.VB_FLG == '0') {
            BPRTLVB.PLBOX_TP = BPCUCSTO.PLBOX_TYP;
            CEP.TRC(SCCGWA, BPCUCSTO.PLBOX_TYP);
            CEP.TRC(SCCGWA, BPRTLVB.PLBOX_TP);
            BPRTLVB.CRNT_TLR = BPCUCSTO.VB_TLR;
            BPCTLVBF.INFO.FUNC = 'I';
            BPCTLVBF.POINTER = BPRTLVB;
            BPCTLVBF.LEN = 187;
            S000_CALL_BPZTLVBF();
            if (pgmRtn) return;
            BPRCLIB.KEY.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
            BPCUCSTO.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
            WS_MGR_TLR = BPRTLVB.CRNT_TLR;
            WS_PLBOX_TP = BPRTLVB.PLBOX_TP;
            if (BPCTLVBF.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLVB_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            BPRTLVB.KEY.BR = BPCUCSTO.VB_BR;
            BPRTLVB.KEY.PLBOX_NO = "%%%%%%";
            BPCRTLVB.INFO.FUNC = 'Q';
            BPCRTLVB.INFO.LEN = 187;
            BPCRTLVB.INFO.POINTER = BPRTLVB;
            S000_CALL_BPZRTLVB();
            if (pgmRtn) return;
            BPCRTLVB.INFO.FUNC = 'N';
            BPCRTLVB.INFO.POINTER = BPRTLVB;
            BPCRTLVB.INFO.LEN = 187;
            S000_CALL_BPZRTLVB();
            if (pgmRtn) return;
            WS_CNT = 0;
            while (BPCRTLVB.RETURN_INFO != 'N' 
                && WS_CNT <= 1000) {
                WS_CNT += 1;
                if (BPRTLVB.PLBOX_TP == '1' 
                    || BPRTLVB.PLBOX_TP == '2') {
                    WS_MGR_TLR = BPRTLVB.CRNT_TLR;
                    WS_PLBOX_TP = BPRTLVB.PLBOX_TP;
                    BPRCLIB.KEY.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
                    BPCUCSTO.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
                    WS_CNT = 1001;
                }
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
            if (BPCRTLVB.RETURN_INFO != 'F') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        BPRCLIB.KEY.CCY = BPCUCSTO.CCY;
        BPRCLIB.KEY.BR = BPRTLVB.KEY.BR;
        BPRCLIB.KEY.CASH_TYP = BPCUCSTO.CASH_TYP;
        BPCTLIBF.INFO.FUNC = 'R';
        BPCTLIBF.POINTER = BPRCLIB;
        BPCTLIBF.LEN = 352;
        S000_CALL_BPZTLIBF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRCLIB);
        CEP.TRC(SCCGWA, "AAAAAAAAAAAAAAAAAAAA");
        if (BPCTLIBF.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PLBOX_NO_THIS_CCY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            CEP.TRC(SCCGWA, BPRCLIB.BAL);
            CEP.TRC(SCCGWA, BPCUCSTO.TX_AMT);
            CEP.TRC(SCCGWA, BPRCLIB.LMT_U);
            CEP.TRC(SCCGWA, "GWA-CANCEL-YES");
            CEP.TRC(SCCGWA, "WS-TMP-AMT = CLIB-BAL + UCSTO-TX-AMT");
            WS_TMP_AMT = BPRCLIB.BAL + BPCUCSTO.TX_AMT;
        } else {
            CEP.TRC(SCCGWA, "COMPUTE WS-TMP-AMT = CLIB-BAL - UCSTO-TX-AMT");
            CEP.TRC(SCCGWA, WS_TMP_AMT);
            CEP.TRC(SCCGWA, BPRCLIB.BAL);
            CEP.TRC(SCCGWA, BPCUCSTO.TX_AMT);
            CEP.TRC(SCCGWA, BPRCLIB.BAL);
            CEP.TRC(SCCGWA, BPCUCSTO.TX_AMT);
            WS_TMP_AMT = BPRCLIB.BAL - BPCUCSTO.TX_AMT;
            CEP.TRC(SCCGWA, WS_TMP_AMT);
            CEP.TRC(SCCGWA, BPRCLIB.LMT_L);
            CEP.TRC(SCCGWA, BPCUCSTO.TX_AMT);
            if (BPCUCSTO.CS_KIND == '0' 
                && BPRCLIB.GD_AMT < BPCUCSTO.TX_AMT 
                || BPCUCSTO.CS_KIND == '2' 
                && BPRCLIB.BD_AMT < BPCUCSTO.TX_AMT 
                || BPCUCSTO.CS_KIND == '3' 
                && BPRCLIB.HBD_AMT < BPCUCSTO.TX_AMT) {
                CEP.TRC(SCCGWA, "DEV4");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CASH_NOT_ENOUGH;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, "CSTO FIRST");
        CEP.TRC(SCCGWA, SCRCWA.AC_DATE_AREA[1-1].LAST_AC_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, BPRCLIB.NEW_DT);
        CEP.TRC(SCCGWA, BPRCLIB.LAST_DT);
        if (SCCGWA.COMM_AREA.AC_DATE > BPRCLIB.NEW_DT) {
            BPRCLIB.L_TLT_AMT = BPRCLIB.BAL;
            if (BPCUCSTO.CS_KIND == '0') {
                BPRCLIB.L_GD_AMT = BPRCLIB.GD_AMT;
            }
            if (BPCUCSTO.CS_KIND == '2') {
                BPRCLIB.L_BD_AMT = BPRCLIB.BD_AMT;
            }
            if (BPCUCSTO.CS_KIND == '3') {
                BPRCLIB.L_HBD_AMT = BPRCLIB.HBD_AMT;
            }
            BPRCLIB.LAST_DT = SCRCWA.AC_DATE_AREA[1-1].LAST_AC_DATE;
        }
        CEP.TRC(SCCGWA, "CSTO LAST");
        CEP.TRC(SCCGWA, SCRCWA.AC_DATE_AREA[1-1].LAST_AC_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, BPRCLIB.NEW_DT);
        CEP.TRC(SCCGWA, BPRCLIB.LAST_DT);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            BPRCLIB.BAL = BPRCLIB.BAL + BPCUCSTO.TX_AMT;
            if (SCCGWA.COMM_AREA.AC_DATE < BPRCLIB.NEW_DT) {
                BPRCLIB.L_TLT_AMT = BPRCLIB.L_TLT_AMT + BPCUCSTO.TX_AMT;
            }
            if (BPCUCSTO.CS_KIND == '0') {
                BPRCLIB.GD_AMT = BPRCLIB.GD_AMT + BPCUCSTO.TX_AMT;
                if (SCCGWA.COMM_AREA.AC_DATE < BPRCLIB.NEW_DT) {
                    BPRCLIB.L_GD_AMT = BPRCLIB.L_GD_AMT + BPCUCSTO.TX_AMT;
                }
            } else if (BPCUCSTO.CS_KIND == '2') {
                BPRCLIB.BD_AMT = BPRCLIB.BD_AMT + BPCUCSTO.TX_AMT;
                if (SCCGWA.COMM_AREA.AC_DATE < BPRCLIB.NEW_DT) {
                    BPRCLIB.L_BD_AMT = BPRCLIB.L_BD_AMT + BPCUCSTO.TX_AMT;
                }
            } else if (BPCUCSTO.CS_KIND == '3') {
                BPRCLIB.HBD_AMT = BPRCLIB.HBD_AMT + BPCUCSTO.TX_AMT;
                if (SCCGWA.COMM_AREA.AC_DATE < BPRCLIB.NEW_DT) {
                    BPRCLIB.L_HBD_AMT = BPRCLIB.L_HBD_AMT + BPCUCSTO.TX_AMT;
                }
            } else {
            }
        } else {
            BPRCLIB.BAL = BPRCLIB.BAL - BPCUCSTO.TX_AMT;
            if (SCCGWA.COMM_AREA.AC_DATE < BPRCLIB.NEW_DT) {
                BPRCLIB.L_TLT_AMT = BPRCLIB.L_TLT_AMT - BPCUCSTO.TX_AMT;
            }
            if (BPCUCSTO.CS_KIND == '0') {
                BPRCLIB.GD_AMT = BPRCLIB.GD_AMT - BPCUCSTO.TX_AMT;
                if (SCCGWA.COMM_AREA.AC_DATE < BPRCLIB.NEW_DT) {
                    BPRCLIB.L_GD_AMT = BPRCLIB.L_GD_AMT - BPCUCSTO.TX_AMT;
                }
            } else if (BPCUCSTO.CS_KIND == '2') {
                BPRCLIB.BD_AMT = BPRCLIB.BD_AMT - BPCUCSTO.TX_AMT;
                if (SCCGWA.COMM_AREA.AC_DATE < BPRCLIB.NEW_DT) {
                    BPRCLIB.L_BD_AMT = BPRCLIB.L_BD_AMT - BPCUCSTO.TX_AMT;
                }
            } else if (BPCUCSTO.CS_KIND == '3') {
                BPRCLIB.HBD_AMT = BPRCLIB.HBD_AMT - BPCUCSTO.TX_AMT;
                if (SCCGWA.COMM_AREA.AC_DATE < BPRCLIB.NEW_DT) {
                    BPRCLIB.L_HBD_AMT = BPRCLIB.L_HBD_AMT - BPCUCSTO.TX_AMT;
                }
            } else {
            }
        }
        IBS.init(SCCGWA, BPCTLIBF.RC);
        BPRCLIB.BAL_FLG = 'N';
        BPRCLIB.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRCLIB.NEW_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRCLIB.UPD_TLR = BPCUCSTO.VB_TLR;
        BPCTLIBF.INFO.FUNC = 'U';
        BPCTLIBF.POINTER = BPRCLIB;
        BPCTLIBF.LEN = 352;
        S000_CALL_BPZTLIBF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BBBBBBBBBBBBBBB");
        if (BPCTLIBF.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PLBOX_NO_THIS_CCY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCSPBLI);
        BPCSPBLI.BR = BPRTLVB.KEY.BR;
        BPCSPBLI.PLBOX_NO = BPCUCSTO.PLBOX_NO;
        S000_CALL_BPZUPBLI();
        if (pgmRtn) return;
    }
    public void B02_02_UPDATE_CASH_PAR() throws IOException,SQLException,Exception {
        for (WS_CNT = 1; WS_CNT <= K_PAR_MAX_CNT; WS_CNT += 1) {
            if (BPCUCSTO.PAR_INFO.PAR_REC[WS_CNT-1].PAR_VAL > 0 
                && BPCUCSTO.PAR_INFO.PAR_REC[WS_CNT-1].PAR_NUM > 0) {
                if (BPCUCSTO.PAR_INFO.PAR_REC[WS_CNT-1].M_FLG != '0' 
                    && BPCUCSTO.PAR_INFO.PAR_REC[WS_CNT-1].M_FLG != '1') {
                    CEP.TRC(SCCGWA, BPCUCSTO.PAR_INFO.PAR_REC[WS_CNT-1].M_FLG);
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                IBS.init(SCCGWA, BPCTLBIF);
                IBS.init(SCCGWA, BPRCLBI);
                BPRCLBI.KEY.VB_BR = BPCUCSTO.VB_BR;
                BPRCLBI.KEY.PLBOX_NO = BPRCLIB.KEY.PLBOX_NO;
                BPRCLBI.KEY.CASH_TYP = BPRCLIB.KEY.CASH_TYP;
                BPRCLBI.KEY.CCY = BPCUCSTO.CCY;
                BPRCLBI.KEY.PAR_VAL = BPCUCSTO.PAR_INFO.PAR_REC[WS_CNT-1].PAR_VAL;
                BPRCLBI.KEY.M_FLG = BPCUCSTO.PAR_INFO.PAR_REC[WS_CNT-1].M_FLG;
                BPCTLBIF.INFO.FUNC = 'R';
                BPCTLBIF.POINTER = BPRCLBI;
                BPCTLBIF.LEN = 222;
                S000_CALL_BPZTLBIF();
                if (pgmRtn) return;
                if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTLBIF.RC);
                    if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CLBI_NOTFND)) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_THIS_PVAL;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    } else {
                        if (BPCUCSTO.CS_KIND == '0' 
                            && BPRCLBI.GD_NUM < BPCUCSTO.PAR_INFO.PAR_REC[WS_CNT-1].PAR_NUM 
                            || BPCUCSTO.CS_KIND == '2' 
                            && BPRCLBI.BD_NUM < BPCUCSTO.PAR_INFO.PAR_REC[WS_CNT-1].PAR_NUM 
                            || BPCUCSTO.CS_KIND == '3' 
                            && BPRCLBI.HBD_NUM < BPCUCSTO.PAR_INFO.PAR_REC[WS_CNT-1].PAR_NUM) {
                            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PNUM_NOT_ENOUGH;
                            S000_ERR_MSG_PROC();
                            if (pgmRtn) return;
                        }
                    }
                }
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTLBIF.RC);
                if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CLBI_NOTFND)) {
                    IBS.init(SCCGWA, BPCTLBIF);
                    IBS.init(SCCGWA, BPRCLBI);
                    BPRCLBI.KEY.VB_BR = BPCUCSTO.VB_BR;
                    BPRCLBI.KEY.PLBOX_NO = BPRCLIB.KEY.PLBOX_NO;
                    BPRCLBI.KEY.CASH_TYP = BPRCLIB.KEY.CASH_TYP;
                    BPRCLBI.KEY.CCY = BPCUCSTO.CCY;
                    BPRCLBI.KEY.PAR_VAL = BPCUCSTO.PAR_INFO.PAR_REC[WS_CNT-1].PAR_VAL;
                    BPRCLBI.KEY.M_FLG = BPCUCSTO.PAR_INFO.PAR_REC[WS_CNT-1].M_FLG;
                    if (BPCUCSTO.CS_KIND == '0') {
                        BPRCLBI.GD_NUM = BPCUCSTO.PAR_INFO.PAR_REC[WS_CNT-1].PAR_NUM;
                    } else if (BPCUCSTO.CS_KIND == '2') {
                        BPRCLBI.BD_NUM = BPCUCSTO.PAR_INFO.PAR_REC[WS_CNT-1].PAR_NUM;
                    } else if (BPCUCSTO.CS_KIND == '3') {
                        BPRCLBI.HBD_NUM = BPCUCSTO.PAR_INFO.PAR_REC[WS_CNT-1].PAR_NUM;
                    } else {
                    }
                    BPCTLBIF.INFO.FUNC = 'A';
                    BPCTLBIF.POINTER = BPRCLBI;
                    BPCTLBIF.LEN = 222;
                    S000_CALL_BPZTLBIF();
                    if (pgmRtn) return;
                } else {
                    if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                        if (BPCUCSTO.CS_KIND == '0') {
                            BPRCLBI.GD_NUM = BPRCLBI.GD_NUM + BPCUCSTO.PAR_INFO.PAR_REC[WS_CNT-1].PAR_NUM;
                        } else if (BPCUCSTO.CS_KIND == '2') {
                            BPRCLBI.BD_NUM = BPRCLBI.BD_NUM + BPCUCSTO.PAR_INFO.PAR_REC[WS_CNT-1].PAR_NUM;
                        } else if (BPCUCSTO.CS_KIND == '3') {
                            BPRCLBI.HBD_NUM = BPRCLBI.HBD_NUM + BPCUCSTO.PAR_INFO.PAR_REC[WS_CNT-1].PAR_NUM;
                        } else {
                        }
                    } else {
                        if (BPCUCSTO.CS_KIND == '0') {
                            BPRCLBI.GD_NUM = BPRCLBI.GD_NUM - BPCUCSTO.PAR_INFO.PAR_REC[WS_CNT-1].PAR_NUM;
                        } else if (BPCUCSTO.CS_KIND == '2') {
                            BPRCLBI.BD_NUM = BPRCLBI.BD_NUM - BPCUCSTO.PAR_INFO.PAR_REC[WS_CNT-1].PAR_NUM;
                        } else if (BPCUCSTO.CS_KIND == '3') {
                            BPRCLBI.HBD_NUM = BPRCLBI.HBD_NUM - BPCUCSTO.PAR_INFO.PAR_REC[WS_CNT-1].PAR_NUM;
                        } else {
                        }
                    }
                    if (BPRCLBI.GD_NUM == 0 
                        && BPRCLBI.BD_NUM == 0 
                        && BPRCLBI.HBD_NUM == 0) {
                        BPCTLBIF.INFO.FUNC = 'D';
                    } else {
                        BPCTLBIF.INFO.FUNC = 'U';
                    }
                    BPCTLBIF.POINTER = BPRCLBI;
                    BPCTLBIF.LEN = 222;
                    IBS.init(SCCGWA, BPCTLIBF.RC);
                    S000_CALL_BPZTLBIF();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B030_GEN_CASH_VCH_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = WS_MGR_TLR;
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPPRDQ);
        BPCPPRDQ.DATA_INFO.CCY = BPCUCSTO.CCY;
        CEP.TRC(SCCGWA, WS_PLBOX_TP);
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR_STSW);
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
            BPCPPRDQ.DATA_INFO.STAT = '2';
        } else {
            if (BPCUCSTO.CASH_STAT == '1') {
                BPCPPRDQ.DATA_INFO.STAT = '1';
            } else {
                BPCPPRDQ.DATA_INFO.STAT = '0';
            }
        }
        BPCPPRDQ.DATA_INFO.CS_KIND = BPCUCSTO.CS_KIND;
        CEP.TRC(SCCGWA, BPCPPRDQ);
        WS_ACCT_CD = BPCPPRDQ.DATA_INFO.ACCT_CD;
        CEP.TRC(SCCGWA, BPCPPRDQ.DATA_INFO.ACCT_CD);
        CEP.TRC(SCCGWA, "NCB0710002");
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y' 
            && BPCUCSTO.ACCT_FLG != 'N' 
            && BPCUCSTO.TX_AMT != 0) {
            CEP.TRC(SCCGWA, "NCB0710001");
            B032_SET_EWA_EVENTS_FOR_CN();
            if (pgmRtn) return;
        }
    }
    public void B030_GEN_CASH_VCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = WS_MGR_TLR;
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPPRDQ);
        BPCPPRDQ.DATA_INFO.CCY = BPCUCSTO.CCY;
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Y+"")) {
            BPCPPRDQ.DATA_INFO.STAT = '2';
        } else {
            if (BPCUCSTO.CASH_STAT == '1') {
                BPCPPRDQ.DATA_INFO.STAT = '1';
            } else {
                BPCPPRDQ.DATA_INFO.STAT = '0';
            }
        }
        BPCPPRDQ.DATA_INFO.CS_KIND = BPCUCSTO.CS_KIND;
        CEP.TRC(SCCGWA, BPCPPRDQ);
        WS_ACCT_CD = BPCPPRDQ.DATA_INFO.ACCT_CD;
        CEP.TRC(SCCGWA, BPCPPRDQ.DATA_INFO.ACCT_CD);
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y' 
            && BPCUCSTO.ACCT_FLG != 'N' 
            && BPCUCSTO.TX_AMT != 0) {
            B032_SET_EWA_EVENTS();
            if (pgmRtn) return;
        }
    }
    public void B032_SET_EWA_EVENTS_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "CAS";
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("0")) {
            if (BPCUCSTO.VB_FLG == '0') {
                if (WS_PLBOX_TP == '6') {
                    BPCPOEWA.DATA.EVENT_CODE = "CSATMCT";
                } else {
                    BPCPOEWA.DATA.EVENT_CODE = "CR";
                }
            } else {
                BPCPOEWA.DATA.EVENT_CODE = "CSSTCR";
            }
        } else {
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
                || BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("4") 
                || BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("2")) {
                BPCPOEWA.DATA.EVENT_CODE = "CSATMCN";
            } else {
            }
        }
        BPCPOEWA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCUCSTO.CCY;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = BPCUCSTO.TX_AMT;
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void B032_SET_EWA_EVENTS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "CAS";
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Y+"")) {
            BPCPOEWA.DATA.EVENT_CODE = "BOXCSSUB";
        } else {
            BPCPOEWA.DATA.EVENT_CODE = "ATMCSSUB";
        }
        BPCPOEWA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCUCSTO.CCY;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = BPCUCSTO.TX_AMT;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.BR_OLD);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.BR_NEW);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.CCY_INFO[1-1].CCY);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.VALUE_DATE);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[1-1].AMT);
        CEP.TRC(SCCGWA, BPCUCSTO.TX_AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[1-1].AMT);
        CEP.TRC(SCCGWA, BPCUCSTO.TX_AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[1-1].AMT);
        if (BPCPOEWA.DATA.AC_NO == null) BPCPOEWA.DATA.AC_NO = "";
        JIBS_tmp_int = BPCPOEWA.DATA.AC_NO.length();
        for (int i=0;i<25-JIBS_tmp_int;i++) BPCPOEWA.DATA.AC_NO += " ";
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AC_NO.substring(9 - 1, 9 + 3 - 1));
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void B040_WRITE_HISTORY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPCHIS);
        BPCPCHIS.TLR = WS_MGR_TLR;
        BPCPCHIS.CCY = BPCUCSTO.CCY;
        BPCPCHIS.AMT = BPCUCSTO.TX_AMT;
        BPCPCHIS.VB_FLG = BPCUCSTO.VB_FLG;
        BPCPCHIS.IN_OUT = 'C';
        BPCPCHIS.CS_KIND = BPCUCSTO.CS_KIND;
        BPCPCHIS.CONTR_AC = "" + WS_ACCT_CD;
        JIBS_tmp_int = BPCPCHIS.CONTR_AC.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) BPCPCHIS.CONTR_AC = "0" + BPCPCHIS.CONTR_AC;
        for (WS_CNT = 1; WS_CNT <= K_PAR_MAX_CNT; WS_CNT += 1) {
            if (BPCUCSTO.PAR_INFO.PAR_REC[WS_CNT-1].PAR_VAL > 0 
                && BPCUCSTO.PAR_INFO.PAR_REC[WS_CNT-1].PAR_NUM > 0) {
                BPCPCHIS.PAR_INFO.PAR_REC[WS_CNT-1].PAR_VAL = BPCUCSTO.PAR_INFO.PAR_REC[WS_CNT-1].PAR_VAL;
                BPCPCHIS.PAR_INFO.PAR_REC[WS_CNT-1].M_FLG = BPCUCSTO.PAR_INFO.PAR_REC[WS_CNT-1].M_FLG;
                BPCPCHIS.PAR_INFO.PAR_REC[WS_CNT-1].PAR_NUM = BPCUCSTO.PAR_INFO.PAR_REC[WS_CNT-1].PAR_NUM;
            }
        }
        S000_CALL_BPZPCHIS();
        if (pgmRtn) return;
    }
    public void B050_PLBOX_INLI_CTR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSINLI);
        BPCSINLI.BR = BPRTLVB.KEY.BR;
        S000_CALL_BPZUINLI();
        if (pgmRtn) return;
    }
    public void B060_GET_LONGTOU_CLIB_RES() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLVB);
        IBS.init(SCCGWA, BPCTLVBF);
        BPRTLVB.PLBOX_TP = '5';
        BPRTLVB.CRNT_TLR = BPCUCSTO.VB_TLR;
        BPCTLVBF.INFO.FUNC = 'I';
        BPCTLVBF.POINTER = BPRTLVB;
        BPCTLVBF.LEN = 187;
        S000_CALL_BPZTLVBF();
        if (pgmRtn) return;
        if (BPCTLVBF.RETURN_INFO == 'N' 
            || BPRTLVB.KEY.BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TX_TLR_NOT_CS_LT_BOX;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTLIBF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_MAINT_CLIB, BPCTLIBF);
        CEP.TRC(SCCGWA, BPCTLIBF.RC.RC_CODE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void S000_CALL_BPZTLBIF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_MAINT_CLBI, BPCTLBIF);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCTLBIF.RC);
        if (BPCTLBIF.RC.RC_CODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CLBI_NOTFND)) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTLBIF.RC);
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
    public void S000_CALL_BPZPPRDQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_PROD, BPCPPRDQ);
        if (BPCPPRDQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPPRDQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_INF_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPCHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_ADD_CASH_HIS, BPCPCHIS);
        if (BPCPCHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPCHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTLVBF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_TLVB, BPCTLVBF);
    }
    public void S000_CALL_BPZRTLVB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_STARTBR_TLVB, BPCRTLVB);
    }
    public void S000_CALL_BPZUINLI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_INLI_CTR, BPCSINLI);
    }
    public void S000_CALL_BPZUPBLI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_LIMIT_CTR, BPCSPBLI);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCUCSTO.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCUCSTO = ");
            CEP.TRC(SCCGWA, BPCUCSTO);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
