package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSLIBM {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String K_HIS_REMARKS = "BOXLIB INFOMATION MAINTAIN";
    String K_CPY_BPRCLIB = "BPRCLIB";
    String K_CPY_BPRTLVB = "BPRTLVB";
    String CPN_BR_INF_BRW = "BP-BR-INF-BRW";
    String CPN_R_BRE_MVBB = "BP-R-BRE-MVBB";
    String CPN_R_BRE_CLIB = "BP-R-BRE-CLIB";
    String CPN_R_ADW_CLIB = "BP-R-ADW-CLIB";
    String CPN_R_ADW_TLVB = "BP-R-ADW-TLVB";
    String CPN_S_GET_SEQ = "BP-S-GET-SEQ";
    String CPN_R_CLIB = "BP-R-CLIB";
    String CPN_REC_NHIS = "BP-REC-NHIS";
    String CPN_P_CHK_CBOX = "BP-P-CHK-CBOX";
    String CPN_P_DEL_CBOX = "BP-P-DEL-CBOX";
    String CPN_R_STARTBR_TLVB = "BP-R-STARTBR-TLVB";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    int K_P_CNT = 20;
    char K_RUN_MODE = 'O';
    short K_NUM_1 = 1;
    String K_BPFBAS_SEQ = "CPBNO";
    String K_SEQ_TYPE = "SEQ";
    String WS_ERR_MSG = " ";
    short WS_IDX = 0;
    int WS_BR = 0;
    int WS_I = 0;
    int WS_O = 0;
    int WS_D = 0;
    int WS_CNT = 0;
    int WS_CNT1 = 0;
    BPZSLIBM_WS_PLBOX_NO WS_PLBOX_NO = new BPZSLIBM_WS_PLBOX_NO();
    char WS_TBL_BANK_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPCBOX BPCPCBOX = new BPCPCBOX();
    BPCTLIBF BPCTLIBF = new BPCTLIBF();
    BPCTLVBF BPCTLVBF = new BPCTLVBF();
    BPCPDBOX BPCPDBOX = new BPCPDBOX();
    BPCOLIBO BPCOLIBO = new BPCOLIBO();
    BPCOIBMO BPCOIBMO = new BPCOIBMO();
    BPCSTLVB BPCSTLVB = new BPCSTLVB();
    BPCRTLVB BPCRTLVB = new BPCRTLVB();
    BPCRCLIB BPCRCLIB = new BPCRCLIB();
    BPCOLVBM BPCOLVBM = new BPCOLVBM();
    BPCTLIBB BPCTLIBB = new BPCTLIBB();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCTMVBB BPCTMVBB = new BPCTMVBB();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPRCLIB BPRCLIB = new BPRCLIB();
    BPRCLIB BPROLIB = new BPRCLIB();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPRTLVB BPROLVB = new BPRTLVB();
    SCCGWA SCCGWA;
    BPCOLIBM BPCOLIBM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCOLIBM BPCOLIBM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOLIBM = BPCOLIBM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSLIBM return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCTLIBF);
        IBS.init(SCCGWA, BPCRCLIB);
        IBS.init(SCCGWA, BPCOLIBM.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCOLIBM.FUNC);
        if (!SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_01_TLR_CHECK();
        }
        if (BPCOLIBM.FUNC == 'A') {
            B010_ADD_PROCESS();
            R000_TRANS_DATA_OUTPUT();
        } else if (BPCOLIBM.FUNC == 'D') {
            if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
                B060_CHECK_DELETE_PLBOX_FOR_CN();
            } else {
                B060_CHECK_DELETE_PLBOX();
            }
            B020_DELETE_PROCESS();
            R000_TRANS_DATA_OUTPUT();
        } else if (BPCOLIBM.FUNC == 'M') {
            B030_MODIFY_PROCESS();
            R000_TRANS_DATA_OUTPUT();
        } else if (BPCOLIBM.FUNC == 'I') {
            B040_QUERY_PROCESS();
            R000_TRANS_DATA_OUTPUT();
        } else if (BPCOLIBM.FUNC == 'B') {
            CEP.TRC(SCCGWA, BPCOLIBM.PLBOX_NO);
            CEP.TRC(SCCGWA, BPCOLIBM.BR);
            B050_QUERY_DATA_PROCESS();
        } else {
            CEP.TRC(SCCGWA, "ERR");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B010_01_TLR_CHECK() throws IOException,SQLException,Exception {
        if (BPCOLIBM.FUNC == 'A' 
            || BPCOLIBM.FUNC == 'M' 
            || BPCOLIBM.FUNC == 'D') {
            IBS.init(SCCGWA, BPCFTLRQ);
            BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
            S000_CALL_BPZFTLRQ();
            if (BPCFTLRQ.INFO.TLR_TYP != 'S') {
                if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
                JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
                for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
                if (!BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TX_TLR_NOT_CASHLIB;
                    S000_ERR_MSG_PROC();
                }
            }
        } else {
        }
    }
    public void B010_ADD_PROCESS() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_CHECK_TLR_EXIST_FOR_CN();
        } else {
            B010_CHECK_TLR_EXIST();
        }
        B020_ADD_PLBOX_NO();
        WS_CNT = 0;
        for (WS_I = 1; WS_I <= 20; WS_I += 1) {
            if (BPCOLIBM.CCYS[WS_I-1].CCY.trim().length() > 0) {
                B010_CHECK_BOXLIB_EXIST();
                if (BPCTLIBF.RETURN_INFO == 'F') {
                    CEP.TRC(SCCGWA, BPRCLIB.KEY.PLBOX_NO);
                    CEP.TRC(SCCGWA, BPRCLIB.KEY.BR);
                    CEP.TRC(SCCGWA, BPRCLIB.KEY.CASH_TYP);
                    CEP.TRC(SCCGWA, BPRCLIB.KEY.CCY);
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_VABX_EXIST;
                    S000_ERR_MSG_PROC();
                }
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTLIBF.RC);
                if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CLIB_NOTFND)) {
                    IBS.init(SCCGWA, BPRCLIB);
                    if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
                        R000_TRANS_DATA_PARA_FOR_CN();
                    } else {
                        R000_TRANS_DATA_PARAMETER();
                    }
                    BPCTLIBF.POINTER = BPRCLIB;
                    BPCTLIBF.LEN = 352;
                    BPCTLIBF.INFO.FUNC = 'A';
                    S000_CALL_BPZTLIBF();
                    if (BPCTLIBF.RC.RC_CODE != 0) {
                        WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTLIBF.RC);
                        S000_ERR_MSG_PROC();
                    }
                    B010_01_HISTORY_RECORD();
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTLVBF.RC);
                    if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_TLVB_NOTFND)) {
                        CEP.TRC(SCCGWA, "CREATEBPRTLVB");
                        IBS.init(SCCGWA, BPRTLVB);
                        R010_TRANS_DATA_PARAMETER();
                        BPCTLVBF.POINTER = BPRTLVB;
                        BPCTLVBF.LEN = 187;
                        BPCTLVBF.INFO.FUNC = 'A';
                        S000_CALL_BPZTLVBF();
                        if (BPCTLVBF.RC.RC_CODE != 0) {
                            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTLVBF.RC);
                            S000_ERR_MSG_PROC();
                        }
                        B010_02_HISTORY_RECORD();
                    }
                }
                WS_CNT += 1;
                R010_TRANS_DATA_OUPUT();
            }
        }
    }
    public void B010_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRCLIB;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID_LEN = 352;
        BPCPNHIS.INFO.TX_TYP_CD = BPCOLIBM.TX_TYP_CD;
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_TYP_CD);
        BPCPNHIS.INFO.NEW_DAT_PT = BPRCLIB;
        S000_CALL_BPZPNHIS();
    }
    public void B010_02_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRTLVB;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID_LEN = 187;
        BPCPNHIS.INFO.TX_TYP_CD = BPCOLIBM.TX_TYP_CD;
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_TYP_CD);
        BPCPNHIS.INFO.NEW_DAT_PT = BPRTLVB;
        S000_CALL_BPZPNHIS();
    }
    public void B020_ADD_PLBOX_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSGSEQ);
        BPCSGSEQ.TYPE = K_SEQ_TYPE;
        BPCSGSEQ.CODE = K_BPFBAS_SEQ;
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSGSEQ.RUN_MODE = K_RUN_MODE;
        BPCSGSEQ.NUM = K_NUM_1;
        S000_CALL_BPZSGSEQ();
        WS_PLBOX_NO.WS_NO = (int) BPCSGSEQ.SEQ;
        CEP.TRC(SCCGWA, WS_PLBOX_NO.WS_NO);
    }
    public void B010_CHECK_BOXLIB_EXIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCLIB);
        IBS.init(SCCGWA, BPRTLVB);
        BPRCLIB.PLBOX_TP = BPCOLIBM.PLBOX_TP;
        WS_PLBOX_NO.WS_TYPE = BPCOLIBM.PLBOX_TP;
        BPRCLIB.KEY.PLBOX_NO = IBS.CLS2CPY(SCCGWA, WS_PLBOX_NO);
        CEP.TRC(SCCGWA, WS_PLBOX_NO.WS_TYPE);
        CEP.TRC(SCCGWA, WS_PLBOX_NO.WS_TYPE);
        CEP.TRC(SCCGWA, WS_PLBOX_NO);
        CEP.TRC(SCCGWA, BPRCLIB.PLBOX_TP);
        CEP.TRC(SCCGWA, BPRCLIB.KEY.PLBOX_NO);
        BPRCLIB.KEY.CCY = BPCOLIBM.CCYS[WS_I-1].CCY;
        BPRCLIB.KEY.CASH_TYP = BPCOLIBM.CCYS[WS_I-1].CASH_TYP;
        BPRCLIB.KEY.BR = BPCOLIBM.BR;
        CEP.TRC(SCCGWA, "CHEK");
        CEP.TRC(SCCGWA, BPCOLIBM.CCYS[WS_I-1].CCY);
        CEP.TRC(SCCGWA, BPRCLIB.KEY.PLBOX_NO);
        CEP.TRC(SCCGWA, BPRCLIB.KEY.BR);
        CEP.TRC(SCCGWA, BPRCLIB.KEY.PLBOX_NO);
        CEP.TRC(SCCGWA, BPRCLIB.KEY.CASH_TYP);
        CEP.TRC(SCCGWA, BPRCLIB.KEY.CCY);
        CEP.TRC(SCCGWA, "HONG");
        BPCTLIBF.POINTER = BPRCLIB;
        BPCTLIBF.LEN = 352;
        BPCTLIBF.INFO.FUNC = 'Q';
        S000_CALL_BPZTLIBF();
        if (WS_I == 1) {
            WS_PLBOX_NO.WS_TYPE = BPCOLIBM.PLBOX_TP;
            BPRTLVB.KEY.PLBOX_NO = IBS.CLS2CPY(SCCGWA, WS_PLBOX_NO);
            BPRTLVB.KEY.BR = BPCOLIBM.BR;
            CEP.TRC(SCCGWA, BPRTLVB.KEY.BR);
            CEP.TRC(SCCGWA, BPRTLVB.KEY.PLBOX_NO);
            BPCTLVBF.POINTER = BPRTLVB;
            BPCTLVBF.LEN = 187;
            BPCTLVBF.INFO.FUNC = 'Q';
            S000_CALL_BPZTLVBF();
            if (BPCTLVBF.RETURN_INFO == 'F') {
                CEP.TRC(SCCGWA, BPRTLVB.KEY.PLBOX_NO);
                CEP.TRC(SCCGWA, BPRTLVB.KEY.BR);
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_VABX_EXIST;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B010_CHECK_TLR_EXIST_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLVB);
        CEP.TRC(SCCGWA, "DEVLYY1");
        if (BPCOLIBM.TLR.trim().length() > 0) {
            BPRTLVB.CRNT_TLR = BPCOLIBM.TLR;
            BPCRTLVB.INFO.FUNC = 'L';
            BPCRTLVB.INFO.LEN = 187;
            BPCRTLVB.INFO.POINTER = BPRTLVB;
            S000_CALL_BPZRTLVB();
            BPCRTLVB.INFO.FUNC = 'N';
            BPCRTLVB.INFO.POINTER = BPRTLVB;
            BPCRTLVB.INFO.LEN = 187;
            S000_CALL_BPZRTLVB();
            WS_CNT = 0;
            if (BPCRTLVB.RETURN_INFO == 'F') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLVB_MUST_NOREC);
            }
            BPCRTLVB.INFO.FUNC = 'E';
            BPCRTLVB.INFO.LEN = 187;
            BPCRTLVB.INFO.POINTER = BPRTLVB;
            S000_CALL_BPZRTLVB();
        }
        CEP.TRC(SCCGWA, "DEVLYY2");
        CEP.TRC(SCCGWA, BPCOLIBM.PLBOX_TP);
        CEP.TRC(SCCGWA, BPCOLIBM.TLR);
        if (BPCOLIBM.PLBOX_TP == '1' 
            || BPCOLIBM.PLBOX_TP == '2' 
            || BPCOLIBM.PLBOX_TP == '5') {
            CEP.TRC(SCCGWA, "DEVLYY3");
            CEP.TRC(SCCGWA, BPCOLIBM.BR);
            BPRTLVB.KEY.BR = BPCOLIBM.BR;
            BPRTLVB.KEY.PLBOX_NO = "%%%%%%";
            BPCRTLVB.INFO.FUNC = 'Q';
            BPCRTLVB.INFO.LEN = 187;
            BPCRTLVB.INFO.POINTER = BPRTLVB;
            S000_CALL_BPZRTLVB();
            BPCRTLVB.INFO.FUNC = 'N';
            BPCRTLVB.INFO.POINTER = BPRTLVB;
            BPCRTLVB.INFO.LEN = 187;
            S000_CALL_BPZRTLVB();
            WS_CNT = 0;
            CEP.TRC(SCCGWA, "DEVLYY4");
            while (BPCRTLVB.RETURN_INFO != 'N' 
                && WS_CNT <= 1000) {
                WS_CNT += 1;
                CEP.TRC(SCCGWA, "DEVLYY5");
                CEP.TRC(SCCGWA, BPRTLVB.PLBOX_TP);
                if (BPRTLVB.PLBOX_TP == '1' 
                    || BPRTLVB.PLBOX_TP == '2' 
                    || BPRTLVB.PLBOX_TP == '5') {
                    CEP.TRC(SCCGWA, "DEVLYY6");
                    CEP.TRC(SCCGWA, BPRTLVB.PLBOX_TP);
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PLBOX_EXIST;
                    S000_ERR_MSG_PROC();
                }
                BPCRTLVB.INFO.FUNC = 'N';
                BPCRTLVB.INFO.POINTER = BPRTLVB;
                BPCRTLVB.INFO.LEN = 187;
                S000_CALL_BPZRTLVB();
            }
            CEP.TRC(SCCGWA, WS_CNT);
            BPCRTLVB.INFO.FUNC = 'E';
            BPCRTLVB.INFO.LEN = 187;
            BPCRTLVB.INFO.POINTER = BPRTLVB;
            S000_CALL_BPZRTLVB();
        }
    }
    public void B010_CHECK_TLR_EXIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLVB);
        if (BPCOLIBM.TLR.trim().length() > 0) {
            BPRTLVB.KEY.BR = BPCOLIBM.BR;
            BPRTLVB.PLBOX_TP = ALL.charAt(0);
            BPRTLVB.CRNT_TLR = BPCOLIBM.TLR;
            BPCRTLVB.INFO.FUNC = 'T';
            BPCRTLVB.INFO.LEN = 187;
            BPCRTLVB.INFO.POINTER = BPRTLVB;
            S000_CALL_BPZRTLVB();
            BPCRTLVB.INFO.FUNC = 'N';
            BPCRTLVB.INFO.POINTER = BPRTLVB;
            BPCRTLVB.INFO.LEN = 187;
            S000_CALL_BPZRTLVB();
            WS_CNT = 0;
            while (BPCRTLVB.RETURN_INFO != 'N' 
                && WS_CNT <= 1000) {
                WS_CNT += 1;
                if ((BPCOLIBM.PLBOX_TP == '1' 
                    && BPRTLVB.PLBOX_TP == '3') 
                    || (BPCOLIBM.PLBOX_TP == '2' 
                    && BPRTLVB.PLBOX_TP == '3') 
                    || (BPCOLIBM.PLBOX_TP == '3' 
                    && BPRTLVB.PLBOX_TP == '1') 
                    || (BPCOLIBM.PLBOX_TP == '3' 
                    && BPRTLVB.PLBOX_TP == '2')) {
                } else {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_PLBOX_EXIST;
                    S000_ERR_MSG_PROC();
                }
                BPCRTLVB.INFO.FUNC = 'N';
                BPCRTLVB.INFO.POINTER = BPRTLVB;
                BPCRTLVB.INFO.LEN = 187;
                S000_CALL_BPZRTLVB();
            }
            BPCRTLVB.INFO.FUNC = 'E';
            BPCRTLVB.INFO.LEN = 187;
            BPCRTLVB.INFO.POINTER = BPRTLVB;
            S000_CALL_BPZRTLVB();
        }
        if (BPCOLIBM.PLBOX_TP == '1' 
            || BPCOLIBM.PLBOX_TP == '2') {
            BPRTLVB.KEY.BR = BPCOLIBM.BR;
            BPRTLVB.KEY.PLBOX_NO = "%%%%%%";
            BPCRTLVB.INFO.FUNC = 'Q';
            BPCRTLVB.INFO.LEN = 187;
            BPCRTLVB.INFO.POINTER = BPRTLVB;
            S000_CALL_BPZRTLVB();
            BPCRTLVB.INFO.FUNC = 'N';
            BPCRTLVB.INFO.POINTER = BPRTLVB;
            BPCRTLVB.INFO.LEN = 187;
            S000_CALL_BPZRTLVB();
            WS_CNT = 0;
            while (BPCRTLVB.RETURN_INFO != 'N' 
                && WS_CNT <= 1000) {
                WS_CNT += 1;
                if (BPRTLVB.PLBOX_TP == '1' 
                    || BPRTLVB.PLBOX_TP == '2') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PLBOX_EXIST;
                    S000_ERR_MSG_PROC();
                }
                BPCRTLVB.INFO.FUNC = 'N';
                BPCRTLVB.INFO.POINTER = BPRTLVB;
                BPCRTLVB.INFO.LEN = 187;
                S000_CALL_BPZRTLVB();
            }
            BPCRTLVB.INFO.FUNC = 'E';
            BPCRTLVB.INFO.LEN = 187;
            BPCRTLVB.INFO.POINTER = BPRTLVB;
            S000_CALL_BPZRTLVB();
        }
    }
    public void B020_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCLIB);
        IBS.init(SCCGWA, BPRTLVB);
        for (WS_I = 1; WS_I <= 20 
            && BPCOLIBM.CCYS[WS_I-1].CCY.trim().length() != 0; WS_I += 1) {
            BPRCLIB.KEY.PLBOX_NO = BPCOLIBM.PLBOX_NO;
            BPRCLIB.KEY.BR = BPCOLIBM.BR;
            BPRCLIB.KEY.CASH_TYP = BPCOLIBM.CCYS[WS_I-1].CASH_TYP;
            BPRCLIB.KEY.CCY = BPCOLIBM.CCYS[WS_I-1].CCY;
            BPCTLIBF.POINTER = BPRCLIB;
            BPCTLIBF.LEN = 352;
            BPCTLIBF.INFO.FUNC = 'R';
            S000_CALL_BPZTLIBF();
            if (BPCTLIBF.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PLBOX_NO_THIS_CCY;
                S000_ERR_MSG_PROC();
            }
            BPCTLIBF.INFO.FUNC = 'D';
            BPRCLIB.KEY.PLBOX_NO = BPCOLIBM.PLBOX_NO;
            BPRCLIB.KEY.BR = BPCOLIBM.BR;
            BPRCLIB.KEY.CASH_TYP = BPCOLIBM.CCYS[WS_I-1].CASH_TYP;
            BPRCLIB.KEY.CCY = BPCOLIBM.CCYS[WS_I-1].CCY;
            BPCTLIBF.POINTER = BPRCLIB;
            BPCTLIBF.LEN = 352;
            BPCTLIBF.INFO.FUNC = 'D';
            S000_CALL_BPZTLIBF();
            R010_TRANS_DATA_OUPUT();
            B020_01_HISTORY_RECORD();
        }
        BPRCLIB.KEY.PLBOX_NO = BPCOLIBM.PLBOX_NO;
        BPRCLIB.KEY.BR = BPCOLIBM.BR;
        BPCTLIBF.POINTER = BPRCLIB;
        BPCTLIBF.LEN = 352;
        BPCTLIBF.INFO.FUNC = 'Q';
        S000_CALL_BPZTLIBF();
        if (BPCTLIBF.RETURN_INFO == 'N') {
            BPRTLVB.KEY.PLBOX_NO = BPCOLIBM.PLBOX_NO;
            BPRTLVB.KEY.BR = BPCOLIBM.BR;
            BPCTLVBF.POINTER = BPRTLVB;
            BPCTLVBF.LEN = 187;
            BPCTLVBF.INFO.FUNC = 'R';
            S000_CALL_BPZTLVBF();
            if (BPCTLVBF.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLVB_NOTFND;
                S000_ERR_MSG_PROC();
            }
            BPCTLVBF.INFO.FUNC = 'D';
            BPRTLVB.KEY.PLBOX_NO = BPCOLIBM.PLBOX_NO;
            BPRTLVB.KEY.BR = BPCOLIBM.BR;
            BPCTLVBF.POINTER = BPRTLVB;
            BPCTLVBF.LEN = 187;
            BPCTLVBF.INFO.FUNC = 'D';
            S000_CALL_BPZTLVBF();
            B020_02_HISTORY_RECORD();
        }
    }
    public void B020_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'D';
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRCLIB;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.TX_TYP_CD = BPCOLIBM.TX_TYP_CD;
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_TYP_CD);
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID_LEN = 352;
        BPCPNHIS.INFO.OLD_DAT_PT = BPRCLIB;
        S000_CALL_BPZPNHIS();
    }
    public void B020_02_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'D';
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRTLVB;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.TX_TYP_CD = BPCOLIBM.TX_TYP_CD;
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_TYP_CD);
        BPCPNHIS.INFO.FMT_ID_LEN = 187;
        BPCPNHIS.INFO.OLD_DAT_PT = BPRTLVB;
        S000_CALL_BPZPNHIS();
    }
    public void B030_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCLIB);
        IBS.init(SCCGWA, BPCOIBMO);
        BPRCLIB.KEY.BR = BPCOLIBM.BR;
        BPRCLIB.KEY.PLBOX_NO = BPCOLIBM.PLBOX_NO;
        CEP.TRC(SCCGWA, BPRCLIB.KEY.BR);
        CEP.TRC(SCCGWA, BPRCLIB.KEY.PLBOX_NO);
        BPCRCLIB.INFO.FUNC = '1';
        BPCRCLIB.INFO.POINTER = BPRCLIB;
        BPCRCLIB.INFO.LEN = 352;
        S000_CALL_BPZRCLIB();
        BPCRCLIB.INFO.FUNC = 'N';
        BPCRCLIB.INFO.POINTER = BPRCLIB;
        BPCRCLIB.INFO.LEN = 352;
        S000_CALL_BPZRCLIB();
        WS_CNT = 0;
        while (BPCRCLIB.RETURN_INFO != 'N' 
            && WS_CNT <= 1000) {
            WS_CNT += 1;
            R010_TRANS_DATA_KEEP();
            BPCRCLIB.INFO.FUNC = 'N';
            BPCRCLIB.INFO.POINTER = BPRCLIB;
            BPCRCLIB.INFO.LEN = 352;
            S000_CALL_BPZRCLIB();
        }
        CEP.TRC(SCCGWA, WS_CNT);
        BPCRCLIB.INFO.FUNC = 'E';
        BPCRCLIB.INFO.POINTER = BPRCLIB;
        BPCRCLIB.INFO.LEN = 352;
        S000_CALL_BPZRCLIB();
        CEP.TRC(SCCGWA, BPCOIBMO);
        IBS.init(SCCGWA, BPRCLIB);
        IBS.init(SCCGWA, BPRTLVB);
        BPRTLVB.KEY.PLBOX_NO = BPCOLIBM.PLBOX_NO;
        BPRTLVB.KEY.BR = BPCOLIBM.BR;
        CEP.TRC(SCCGWA, BPRTLVB.KEY.PLBOX_NO);
        CEP.TRC(SCCGWA, BPRTLVB.KEY.BR);
        BPCTLVBF.POINTER = BPRTLVB;
        BPCTLVBF.LEN = 187;
        BPCTLVBF.INFO.FUNC = 'R';
        S000_CALL_BPZTLVBF();
        CEP.TRC(SCCGWA, "DEV1");
        if (BPCTLVBF.RETURN_INFO == 'N') {
            CEP.TRC(SCCGWA, "DEV2");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLVB_NOTFND;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPRTLVB.CRNT_TLR);
        CEP.TRC(SCCGWA, BPCOLIBM.TLR);
        if (BPRTLVB.PLBOX_TP != '6') {
            if (!BPRTLVB.CRNT_TLR.equalsIgnoreCase(BPCOLIBM.TLR)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CUR_OLD_DATA;
                S000_ERR_MSG_PROC();
            }
        } else {
            if (!BPRTLVB.CRNT_TLR.equalsIgnoreCase(BPCOLIBM.TLR) 
                && !BPRTLVB.CRNT_TLR1.equalsIgnoreCase(BPCOLIBM.TLR) 
                && !BPRTLVB.CRNT_TLR2.equalsIgnoreCase(BPCOLIBM.TLR) 
                && !BPRTLVB.CRNT_TLR3.equalsIgnoreCase(BPCOLIBM.TLR) 
                && !BPRTLVB.CRNT_TLR4.equalsIgnoreCase(BPCOLIBM.TLR)) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CUR_OLD_DATA);
            }
        }
        for (WS_I = 1; WS_I <= 20 
            && BPCOLIBM.CCYS[WS_I-1].CCY.trim().length() != 0; WS_I += 1) {
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, BPCOLIBM.CCYS[WS_I-1].CCY);
            BPRCLIB.KEY.PLBOX_NO = BPCOLIBM.PLBOX_NO;
            BPRCLIB.KEY.BR = BPCOLIBM.BR;
            BPRCLIB.KEY.CASH_TYP = BPCOLIBM.CCYS[WS_I-1].CASH_TYP;
            BPRCLIB.KEY.CCY = BPCOLIBM.CCYS[WS_I-1].CCY;
            BPRCLIB.PLBOX_TP = BPCOLIBM.PLBOX_TP;
            CEP.TRC(SCCGWA, BPRCLIB.KEY.PLBOX_NO);
            CEP.TRC(SCCGWA, BPRCLIB.KEY.BR);
            CEP.TRC(SCCGWA, BPRCLIB.KEY.CCY);
            CEP.TRC(SCCGWA, BPRCLIB.PLBOX_TP);
            BPCTLIBF.POINTER = BPRCLIB;
            BPCTLIBF.LEN = 352;
            BPCTLIBF.INFO.FUNC = 'R';
            S000_CALL_BPZTLIBF();
            if (BPCTLIBF.RETURN_INFO == 'N') {
                IBS.init(SCCGWA, BPRCLIB);
                R000_MODIFY_DATA_PARAMETER();
                BPCTLIBF.POINTER = BPRCLIB;
                BPCTLIBF.LEN = 352;
                BPCTLIBF.INFO.FUNC = 'A';
                S000_CALL_BPZTLIBF();
                if (BPCTLIBF.RC.RC_CODE != 0) {
                    WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTLIBF.RC);
                    S000_ERR_MSG_PROC();
                }
                IBS.init(SCCGWA, BPCPNHIS);
                BPCPNHIS.INFO.TX_TYP = 'M';
                BPCPNHIS.INFO.FMT_ID = K_CPY_BPRCLIB;
                BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
                BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
                BPCPNHIS.INFO.DATA_FLG = 'Y';
                BPCPNHIS.INFO.TX_TYP_CD = BPCOLIBM.TX_TYP_CD;
                CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_TYP_CD);
                BPCPNHIS.INFO.FMT_ID_LEN = 352;
                BPCPNHIS.INFO.NEW_DAT_PT = BPRCLIB;
                S000_CALL_BPZPNHIS();
            } else {
                CEP.TRC(SCCGWA, WS_I);
                BPRCLIB.KEY.CASH_TYP = BPCOLIBM.CCYS[WS_I-1].CASH_TYP;
                BPRCLIB.KEY.CCY = BPCOLIBM.CCYS[WS_I-1].CCY;
                BPRCLIB.LMT_CCY = BPCOLIBM.CCYS[WS_I-1].LMT_CCY;
                BPRCLIB.LMT_U = BPCOLIBM.CCYS[WS_I-1].MAX_LMT;
                BPRCLIB.LMT_L = BPCOLIBM.CCYS[WS_I-1].MIN_LMT;
                CEP.TRC(SCCGWA, BPCOLIBM.CCYS[WS_I-1].MAX_LMT);
                CEP.TRC(SCCGWA, BPCOLIBM.CCYS[WS_I-1].MIN_LMT);
                CEP.TRC(SCCGWA, BPRCLIB.KEY.CASH_TYP);
                CEP.TRC(SCCGWA, BPRCLIB.KEY.CCY);
                CEP.TRC(SCCGWA, BPRCLIB.LMT_CCY);
                CEP.TRC(SCCGWA, BPRCLIB.LMT_U);
                CEP.TRC(SCCGWA, BPRCLIB.LMT_L);
                BPCTLIBF.POINTER = BPRCLIB;
                BPCTLIBF.LEN = 352;
                BPCTLIBF.INFO.FUNC = 'U';
                S000_CALL_BPZTLIBF();
            }
            if (BPCOLIBM.INSR_CCY.equalsIgnoreCase(BPRTLVB.INSR_CCY) 
                && BPCOLIBM.INSR_AMT == BPRTLVB.INSR_AMT 
                && BPCOLIBM.BLMT_CCY.equalsIgnoreCase(BPRTLVB.BLMT_CCY) 
                && BPCOLIBM.BLMT_U == BPRTLVB.BLMT_U 
                && BPCOLIBM.BLMT_L == BPRTLVB.BLMT_L 
                && BPCOLIBM.BIND_TYP == BPRTLVB.BIND_TYPE) {
            } else {
                IBS.CLONE(SCCGWA, BPRTLVB, BPROLVB);
                BPRTLVB.INSR_CCY = BPCOLIBM.INSR_CCY;
                BPRTLVB.INSR_AMT = BPCOLIBM.INSR_AMT;
                BPRTLVB.BLMT_CCY = BPCOLIBM.BLMT_CCY;
                BPRTLVB.BLMT_U = BPCOLIBM.BLMT_U;
                BPRTLVB.BLMT_L = BPCOLIBM.BLMT_L;
                BPRTLVB.LAST_DT = BPCOLIBM.UPT_DT;
                BPRTLVB.UPD_TLR = BPCOLIBM.UPT_TLR;
                BPRTLVB.BIND_TYPE = BPCOLIBM.BIND_TYP;
                BPCTLVBF.POINTER = BPRTLVB;
                BPCTLVBF.LEN = 187;
                BPCTLVBF.INFO.FUNC = 'U';
                S000_CALL_BPZTLVBF();
                IBS.init(SCCGWA, BPCPNHIS);
                BPCPNHIS.INFO.TX_TYP = 'M';
                BPCPNHIS.INFO.FMT_ID = K_CPY_BPRTLVB;
                BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
                BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
                BPCPNHIS.INFO.DATA_FLG = 'Y';
                BPCPNHIS.INFO.TX_TYP_CD = BPCOLIBM.TX_TYP_CD;
                CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_TYP_CD);
                BPCPNHIS.INFO.FMT_ID_LEN = 187;
                BPCPNHIS.INFO.OLD_DAT_PT = BPROLVB;
                BPCPNHIS.INFO.NEW_DAT_PT = BPRTLVB;
                S000_CALL_BPZPNHIS();
            }
            for (WS_O = 1; WS_O <= 20; WS_O += 1) {
                CEP.TRC(SCCGWA, BPCOLIBM.CCYS[WS_I-1].CCY);
                CEP.TRC(SCCGWA, BPCOIBMO.DATA.CCYS[WS_O-1].CCY);
                CEP.TRC(SCCGWA, BPCOLIBM.CCYS[WS_I-1].CASH_TYP);
                CEP.TRC(SCCGWA, BPCOIBMO.DATA.CCYS[WS_O-1].CASH_TYP);
                if (BPCOLIBM.CCYS[WS_I-1].CCY.equalsIgnoreCase(BPCOIBMO.DATA.CCYS[WS_O-1].CCY) 
                    && BPCOLIBM.CCYS[WS_I-1].CASH_TYP.equalsIgnoreCase(BPCOIBMO.DATA.CCYS[WS_O-1].CASH_TYP)) {
                    BPCOIBMO.DATA.CCYS[WS_O-1].CCY = " ";
                    BPCOIBMO.DATA.CCYS[WS_O-1].CASH_TYP = " ";
                }
            }
            R010_TRANS_DATA_OUPUT();
            CEP.TRC(SCCGWA, "MODIFY");
        }
        CEP.TRC(SCCGWA, BPCOIBMO);
        CEP.TRC(SCCGWA, BPCOIBMO.DATA.CCYS[1-1].CCY);
        CEP.TRC(SCCGWA, BPCOIBMO.DATA.CCYS[2-1].CCY);
        for (WS_D = 1; WS_D <= 20; WS_D += 1) {
            CEP.TRC(SCCGWA, WS_D);
            CEP.TRC(SCCGWA, "DEL");
            CEP.TRC(SCCGWA, BPCOIBMO.DATA.CCYS[WS_D-1].CCY);
            if (BPCOIBMO.DATA.CCYS[WS_D-1].CCY.trim().length() > 0) {
                BPRCLIB.KEY.PLBOX_NO = BPCOIBMO.PLBOX_NO;
                BPRCLIB.KEY.BR = BPCOIBMO.BR;
                BPRCLIB.KEY.CASH_TYP = BPCOIBMO.DATA.CCYS[WS_D-1].CASH_TYP;
                BPRCLIB.KEY.CCY = BPCOIBMO.DATA.CCYS[WS_D-1].CCY;
                BPCTLIBF.POINTER = BPRCLIB;
                BPCTLIBF.LEN = 352;
                BPCTLIBF.INFO.FUNC = 'R';
                S000_CALL_BPZTLIBF();
                if (BPCTLIBF.RETURN_INFO == 'N') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PLBOX_NO_THIS_CCY;
                    S000_ERR_MSG_PROC();
                }
                if (BPRCLIB.BAL_FLG == 'N') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BAL_FLG;
                    S000_ERR_MSG_PROC();
                }
                if (BPRCLIB.BAL != 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BAL_ZERO;
                    S000_ERR_MSG_PROC();
                }
                BPCTLIBF.INFO.FUNC = 'D';
                BPRCLIB.KEY.PLBOX_NO = BPCOIBMO.PLBOX_NO;
                BPRCLIB.KEY.BR = BPCOIBMO.BR;
                BPRCLIB.KEY.CASH_TYP = BPCOIBMO.DATA.CCYS[WS_D-1].CASH_TYP;
                BPRCLIB.KEY.CCY = BPCOIBMO.DATA.CCYS[WS_D-1].CCY;
                BPCTLIBF.POINTER = BPRCLIB;
                BPCTLIBF.LEN = 352;
                BPCTLIBF.INFO.FUNC = 'D';
                S000_CALL_BPZTLIBF();
                IBS.init(SCCGWA, BPCPNHIS);
                BPCPNHIS.INFO.TX_TYP = 'M';
                BPCPNHIS.INFO.FMT_ID = K_CPY_BPRCLIB;
                BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
                BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
                BPCPNHIS.INFO.DATA_FLG = 'Y';
                BPCPNHIS.INFO.TX_TYP_CD = BPCOLIBM.TX_TYP_CD;
                CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_TYP_CD);
                BPCPNHIS.INFO.FMT_ID_LEN = 352;
                BPCPNHIS.INFO.OLD_DAT_PT = BPRCLIB;
                S000_CALL_BPZPNHIS();
            }
        }
        IBS.CLONE(SCCGWA, BPRCLIB, BPROLIB);
        if (BPCOLIBM.FUNC == 'A') {
            BPRCLIB.KEY.CCY = BPCOLIBM.CCYS[WS_I-1].CCY;
        }
        if (BPCOLIBM.FUNC == 'A') {
            BPRCLIB.LMT_CCY = BPCOLIBM.CCYS[WS_I-1].LMT_CCY;
            BPRCLIB.LMT_U = BPCOLIBM.CCYS[WS_I-1].MAX_LMT;
            CEP.TRC(SCCGWA, BPRCLIB.LMT_U);
            BPRCLIB.LMT_L = BPCOLIBM.CCYS[WS_I-1].MIN_LMT;
            CEP.TRC(SCCGWA, BPRCLIB.LMT_L);
            BPRCLIB.BAL = 0;
            BPRCLIB.GD_AMT = 0;
            BPRCLIB.BD_AMT = 0;
            BPRCLIB.L_GD_AMT = 0;
            BPRCLIB.L_BD_AMT = 0;
            BPRCLIB.L_TLT_AMT = 0;
            BPRCLIB.LAST_DT = BPCOLIBM.UPT_DT;
            BPRCLIB.OPEN_DT = BPCOLIBM.UPT_DT;
            BPRCLIB.OPEN_TLR = BPCOLIBM.UPT_TLR;
            BPRCLIB.UPD_TLR = BPCOLIBM.UPT_TLR;
            BPRTLVB.KEY.BR = BPCOLIBM.BR;
            BPRTLVB.CRNT_TLR = BPCOLIBM.TLR;
            BPRTLVB.INSR_CCY = BPCOLIBM.INSR_CCY;
            BPRTLVB.INSR_AMT = BPCOLIBM.INSR_AMT;
            BPRTLVB.OPEN_DT = BPCOLIBM.UPT_DT;
            BPRTLVB.OPEN_TLR = BPCOLIBM.UPT_TLR;
            BPRTLVB.UP_PBNO = BPCOLIBM.UP_PBNO;
        }
    }
    public void B040_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCLIB);
        IBS.init(SCCGWA, BPRTLVB);
        if (BPCOLIBM.FUNC == 'I') {
            IBS.init(SCCGWA, BPRCLIB);
            IBS.init(SCCGWA, BPRTLVB);
            BPRTLVB.KEY.BR = BPCOLIBM.BR;
            BPRTLVB.KEY.PLBOX_NO = BPCOLIBM.PLBOX_NO;
            BPCTLVBF.POINTER = BPRTLVB;
            BPCTLVBF.LEN = 187;
            BPCTLVBF.INFO.FUNC = 'Q';
            S000_CALL_BPZTLVBF();
            if (BPCTLVBF.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLVB_NOTFND;
                S000_ERR_MSG_PROC();
            }
            BPRCLIB.KEY.BR = BPCOLIBM.BR;
            if (BPCOLIBM.PLBOX_NO.trim().length() == 0) {
                BPRCLIB.KEY.PLBOX_NO = "%%%%%%";
            } else {
                BPRCLIB.KEY.PLBOX_NO = BPCOLIBM.PLBOX_NO;
            }
            CEP.TRC(SCCGWA, BPRCLIB.KEY.BR);
            CEP.TRC(SCCGWA, BPRCLIB.KEY.PLBOX_NO);
            BPCRCLIB.INFO.FUNC = '1';
            BPCRCLIB.INFO.POINTER = BPRCLIB;
            BPCRCLIB.INFO.LEN = 352;
            S000_CALL_BPZRCLIB();
            if (BPCRCLIB.RC.RC_CODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRCLIB.RC);
                S000_ERR_MSG_PROC();
            }
            BPCRCLIB.INFO.FUNC = 'N';
            BPCRCLIB.INFO.POINTER = BPRCLIB;
            BPCRCLIB.INFO.LEN = 352;
            S000_CALL_BPZRCLIB();
            CEP.TRC(SCCGWA, BPRCLIB);
            CEP.TRC(SCCGWA, BPCRCLIB.RC.RC_CODE);
            if (BPCRCLIB.RC.RC_CODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRCLIB.RC);
                S000_ERR_MSG_PROC();
            }
            WS_CNT = 0;
            while (BPCRCLIB.RETURN_INFO != 'N' 
                && WS_CNT <= 1000) {
                WS_CNT += 1;
                R010_TRANS_DATA_OUPUT();
                BPCRCLIB.INFO.FUNC = 'N';
                BPCRCLIB.INFO.POINTER = BPRCLIB;
                BPCRCLIB.INFO.LEN = 352;
                S000_CALL_BPZRCLIB();
            }
            BPCRCLIB.INFO.FUNC = 'E';
            BPCRCLIB.INFO.POINTER = BPRCLIB;
            BPCRCLIB.INFO.LEN = 352;
            S000_CALL_BPZRCLIB();
        }
        if (BPCOLIBM.FUNC == 'M' 
            || (BPCOLIBM.FUNC == 'D' 
            && BPCOLIBM.CCYS[1-1].CCY.trim().length() > 0)) {
            if (BPCOLIBM.FUNC == 'D' 
                || BPCOLIBM.FUNC == 'M') {
                BPRCLIB.KEY.PLBOX_NO = BPCOLIBM.PLBOX_NO;
                BPRCLIB.KEY.CASH_TYP = BPCOLIBM.CCYS[1-1].CASH_TYP;
                BPRCLIB.KEY.BR = BPCOLIBM.BR;
                BPRCLIB.KEY.CCY = BPCOLIBM.CCYS[1-1].CCY;
            } else {
                BPRCLIB.KEY.PLBOX_NO = BPCOLIBM.PLBOX_NO;
                BPRCLIB.KEY.BR = BPCOLIBM.BR;
                BPRCLIB.KEY.CCY = BPCOLIBM.CCYS[1-1].CCY;
                BPRCLIB.KEY.CASH_TYP = BPCOLIBM.CCYS[1-1].CASH_TYP;
            }
            BPCTLIBF.POINTER = BPRCLIB;
            BPCTLIBF.LEN = 352;
            BPCTLIBF.INFO.FUNC = 'Q';
            CEP.TRC(SCCGWA, BPRCLIB.KEY.PLBOX_NO);
            CEP.TRC(SCCGWA, BPRCLIB.KEY.BR);
            CEP.TRC(SCCGWA, BPRCLIB.KEY.CCY);
            CEP.TRC(SCCGWA, BPRCLIB.KEY.CASH_TYP);
            WS_BR = BPRCLIB.KEY.BR;
            CEP.TRC(SCCGWA, WS_BR);
            S000_CALL_BPZTLIBF();
            if (BPCOLIBM.FUNC == 'D' 
                || BPCOLIBM.FUNC == 'M') {
                BPRTLVB.KEY.BR = BPCOLIBM.BR;
                BPRTLVB.KEY.PLBOX_NO = BPCOLIBM.PLBOX_NO;
            }
            BPCTLVBF.POINTER = BPRTLVB;
            BPCTLVBF.LEN = 187;
            BPCTLVBF.INFO.FUNC = 'Q';
            CEP.TRC(SCCGWA, BPRTLVB.KEY.BR);
            CEP.TRC(SCCGWA, BPRTLVB.KEY.PLBOX_NO);
            S000_CALL_BPZTLVBF();
            if (BPCTLIBF.RETURN_INFO == 'N' 
                || BPCTLVBF.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CLIB_NOTFND;
                S000_ERR_MSG_PROC();
            }
            if (BPCTLIBF.RETURN_INFO == 'F' 
                && BPCOLIBM.FUNC == 'D' 
                && BPRCLIB.BAL_FLG == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BAL_FLG;
                S000_ERR_MSG_PROC();
            }
            if (BPCTLVBF.RETURN_INFO == 'F' 
                && BPCOLIBM.FUNC == 'D' 
                && BPRCLIB.BAL != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BAL_ZERO;
                S000_ERR_MSG_PROC();
            }
            if (BPRCLIB.L_TLT_AMT != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_LASTDAYBAL_NOT_ZERO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCOLIBM.FUNC == 'D' 
            && BPRTLVB.CRNT_TLR.trim().length() > 0 
            && BPCOLIBM.CCYS[1-1].CCY.trim().length() == 0) {
            BPCTMVBB.TLR = BPRTLVB.CRNT_TLR;
            BPCTMVBB.MOV_STS = '1';
            BPCTMVBB.MOV_TYP = '1';
            BPCTMVBB.FUNC = 'A';
            BPCTMVBB.INFO.POINTER = BPCTMVBB;
            BPCTMVBB.DATA_LEN = 25;
            S000_CALL_BPZTMVBB();
            BPCTMVBB.FUNC = 'R';
            BPCTMVBB.INFO.POINTER = BPCTMVBB;
            BPCTMVBB.DATA_LEN = 25;
            S000_CALL_BPZTMVBB();
            for (WS_CNT = 1; BPCTMVBB.RETURN_INFO != 'N' 
                && WS_CNT <= 5000; WS_CNT += 1) {
                BPCTMVBB.FUNC = 'R';
                BPCTMVBB.INFO.POINTER = BPCTMVBB;
                BPCTMVBB.DATA_LEN = 25;
                S000_CALL_BPZTMVBB();
            }
            BPCTMVBB.FUNC = 'E';
            BPCTMVBB.INFO.POINTER = BPCTMVBB;
            BPCTMVBB.DATA_LEN = 25;
            S000_CALL_BPZTMVBB();
            WS_CNT = WS_CNT - 1;
            CEP.TRC(SCCGWA, WS_CNT);
            if (WS_CNT != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NEED_RECEIVE_CASH;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCOLIBM.FUNC == 'D' 
            && BPRTLVB.CRNT_TLR.trim().length() > 0 
            && BPCOLIBM.CCYS[1-1].CCY.trim().length() > 0) {
            BPCTMVBB.CCY = BPCOLIBM.CCYS[1-1].CCY;
            BPCTMVBB.TLR = BPRTLVB.CRNT_TLR;
            BPCTMVBB.TLR = BPRTLVB.CRNT_TLR;
            BPCTMVBB.MOV_STS = '1';
            BPCTMVBB.MOV_TYP = '1';
            BPCTMVBB.FUNC = 'S';
            BPCTMVBB.INFO.POINTER = BPCTMVBB;
            BPCTMVBB.DATA_LEN = 25;
            S000_CALL_BPZTMVBB();
            BPCTMVBB.FUNC = 'R';
            BPCTMVBB.INFO.POINTER = BPCTMVBB;
            BPCTMVBB.DATA_LEN = 25;
            S000_CALL_BPZTMVBB();
            for (WS_CNT = 1; BPCTMVBB.RETURN_INFO != 'N' 
                && WS_CNT <= 5000; WS_CNT += 1) {
                BPCTMVBB.FUNC = 'R';
                BPCTMVBB.INFO.POINTER = BPCTMVBB;
                BPCTMVBB.DATA_LEN = 25;
                S000_CALL_BPZTMVBB();
            }
            BPCTMVBB.FUNC = 'E';
            BPCTMVBB.INFO.POINTER = BPCTMVBB;
            BPCTMVBB.DATA_LEN = 25;
            S000_CALL_BPZTMVBB();
            WS_CNT = WS_CNT - 1;
            if (WS_CNT != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NEED_RECEIVE_CASH;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B050_QUERY_DATA_PROCESS() throws IOException,SQLException,Exception {
        BPCSTLVB.PLBOX_NO = BPCOLIBM.PLBOX_NO;
        BPCSTLVB.BR = BPCOLIBM.BR;
        BPCSTLVB.PLBOX_TP = BPCOLIBM.PLBOX_TP;
        BPCSTLVB.CRNT_TLR = BPCOLIBM.TLR;
        BPCSTLVB.BIND_TYP = BPCOLIBM.BIND_TYP;
        BPCSTLVB.CASH_TYP = BPCOLIBM.CCYS[1-1].CASH_TYP;
        CEP.TRC(SCCGWA, "B050");
        CEP.TRC(SCCGWA, BPCSTLVB.CCY);
        CEP.TRC(SCCGWA, BPCOLIBM.CCYS[1-1].CCY);
        S000_CALL_BPZSTLVB();
    }
    public void B060_CHECK_DELETE_PLBOX_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCLIB);
        IBS.init(SCCGWA, BPRTLVB);
        IBS.init(SCCGWA, BPCTLIBF);
        IBS.init(SCCGWA, BPCTLVBF);
        WS_CNT = 0;
        BPRTLVB.KEY.BR = BPCOLIBM.BR;
        BPRTLVB.KEY.PLBOX_NO = BPCOLIBM.PLBOX_NO;
        BPCTLVBF.INFO.FUNC = 'Q';
        BPCTLVBF.POINTER = BPRTLVB;
        BPCTLVBF.LEN = 187;
        S000_CALL_BPZTLVBF();
        if (BPCTLIBF.RETURN_INFO == 'N' 
            || BPCTLVBF.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PLBOX_NOTFND;
            S000_ERR_MSG_PROC();
        }
        if (BPRTLVB.PLBOX_TP != '6') {
            if (!BPRTLVB.CRNT_TLR.equalsIgnoreCase(BPCOLIBM.TLR)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CUR_OLD_DATA;
                S000_ERR_MSG_PROC();
            }
        } else {
            if (!BPRTLVB.CRNT_TLR.equalsIgnoreCase(BPCOLIBM.TLR) 
                && !BPRTLVB.CRNT_TLR1.equalsIgnoreCase(BPCOLIBM.TLR) 
                && !BPRTLVB.CRNT_TLR2.equalsIgnoreCase(BPCOLIBM.TLR) 
                && !BPRTLVB.CRNT_TLR3.equalsIgnoreCase(BPCOLIBM.TLR) 
                && !BPRTLVB.CRNT_TLR4.equalsIgnoreCase(BPCOLIBM.TLR)) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CUR_OLD_DATA);
            }
        }
        if (BPRTLVB.PLBOX_TP == '3') {
            if (BPRTLVB.CRNT_TLR.trim().length() > 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CASH_BOX_INUSED;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPRTLVB.PLBOX_TP == '6') {
            if (BPRTLVB.CRNT_TLR.trim().length() > 0 
                || BPRTLVB.CRNT_TLR1.trim().length() > 0 
                || BPRTLVB.CRNT_TLR2.trim().length() > 0 
                || BPRTLVB.CRNT_TLR3.trim().length() > 0 
                || BPRTLVB.CRNT_TLR4.trim().length() > 0) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CASH_BOX_INUSED);
            }
        }
        if (BPRTLVB.PLBOX_TP == '1' 
            || BPRTLVB.PLBOX_TP == '2' 
            || BPRTLVB.PLBOX_TP == '5') {
            if (BPRTLVB.CRNT_TLR.trim().length() == 0) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_MUST_EXIST);
            }
            BPCTMVBB.TLR = BPRTLVB.CRNT_TLR;
            BPCTMVBB.MOV_STS = '1';
            BPCTMVBB.FUNC = 'T';
            BPCTMVBB.INFO.POINTER = BPCTMVBB;
            BPCTMVBB.DATA_LEN = 25;
            S000_CALL_BPZTMVBB();
            BPCTMVBB.FUNC = 'R';
            BPCTMVBB.INFO.POINTER = BPCTMVBB;
            BPCTMVBB.DATA_LEN = 25;
            S000_CALL_BPZTMVBB();
            while (BPCTMVBB.RETURN_INFO != 'N') {
                WS_CNT = WS_CNT + 1;
                BPCTMVBB.FUNC = 'R';
                BPCTMVBB.INFO.POINTER = BPCTMVBB;
                BPCTMVBB.DATA_LEN = 25;
                S000_CALL_BPZTMVBB();
            }
            BPCTMVBB.FUNC = 'E';
            BPCTMVBB.INFO.POINTER = BPCTMVBB;
            BPCTMVBB.DATA_LEN = 25;
            S000_CALL_BPZTMVBB();
            if (WS_CNT != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NEED_RECEIVE_CASH;
                S000_ERR_MSG_PROC();
            }
        }
        WS_CNT = K_NUM_1;
        while (BPCOLIBM.CCYS[WS_CNT-1].CCY.trim().length() != 0) {
            BPRCLIB.KEY.BR = BPCOLIBM.BR;
            BPRCLIB.KEY.PLBOX_NO = BPCOLIBM.PLBOX_NO;
            BPRCLIB.KEY.CASH_TYP = BPCOLIBM.CCYS[WS_CNT-1].CASH_TYP;
            BPRCLIB.KEY.CCY = BPCOLIBM.CCYS[WS_CNT-1].CCY;
            CEP.TRC(SCCGWA, BPRCLIB.KEY.BR);
            CEP.TRC(SCCGWA, BPRCLIB.KEY.PLBOX_NO);
            CEP.TRC(SCCGWA, BPRCLIB.KEY.CASH_TYP);
            CEP.TRC(SCCGWA, BPRCLIB.KEY.CCY);
            BPCTLIBF.INFO.FUNC = 'Q';
            BPCTLIBF.POINTER = BPRCLIB;
            BPCTLIBF.LEN = 352;
            S000_CALL_BPZTLIBF();
            if (BPCTLIBF.RETURN_INFO != 'F') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PLBOX_NOTFND;
                S000_ERR_MSG_PROC();
            }
            if (BPRCLIB.BAL_FLG == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BAL_FLG;
                S000_ERR_MSG_PROC();
            }
            if (BPRCLIB.BAL != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BAL_ZERO;
                S000_ERR_MSG_PROC();
            }
            WS_CNT = WS_CNT + 1;
        }
    }
    public void B060_CHECK_DELETE_PLBOX() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCLIB);
        IBS.init(SCCGWA, BPRTLVB);
        IBS.init(SCCGWA, BPCTLIBF);
        IBS.init(SCCGWA, BPCTLVBF);
        WS_CNT = 0;
        BPRTLVB.KEY.BR = BPCOLIBM.BR;
        BPRTLVB.KEY.PLBOX_NO = BPCOLIBM.PLBOX_NO;
        CEP.TRC(SCCGWA, BPRTLVB.KEY.BR);
        CEP.TRC(SCCGWA, BPRTLVB.KEY.PLBOX_NO);
        BPCTLVBF.INFO.FUNC = 'Q';
        BPCTLVBF.POINTER = BPRTLVB;
        BPCTLVBF.LEN = 187;
        S000_CALL_BPZTLVBF();
        if (BPCTLIBF.RETURN_INFO == 'N' 
            || BPCTLVBF.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PLBOX_NOTFND;
            S000_ERR_MSG_PROC();
        }
        if (BPRTLVB.PLBOX_TP == '3') {
            if (BPRTLVB.CRNT_TLR.trim().length() > 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CASH_BOX_INUSED;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPRTLVB.PLBOX_TP == '1' 
            || BPRTLVB.PLBOX_TP == '2') {
            BPCTMVBB.TLR = BPRTLVB.CRNT_TLR;
            BPCTMVBB.MOV_STS = '1';
            BPCTMVBB.FUNC = 'T';
            BPCTMVBB.INFO.POINTER = BPCTMVBB;
            BPCTMVBB.DATA_LEN = 25;
            S000_CALL_BPZTMVBB();
            BPCTMVBB.FUNC = 'R';
            BPCTMVBB.INFO.POINTER = BPCTMVBB;
            BPCTMVBB.DATA_LEN = 25;
            S000_CALL_BPZTMVBB();
            while (BPCTMVBB.RETURN_INFO != 'N') {
                WS_CNT = WS_CNT + 1;
                BPCTMVBB.FUNC = 'R';
                BPCTMVBB.INFO.POINTER = BPCTMVBB;
                BPCTMVBB.DATA_LEN = 25;
                S000_CALL_BPZTMVBB();
            }
            BPCTMVBB.FUNC = 'E';
            BPCTMVBB.INFO.POINTER = BPCTMVBB;
            BPCTMVBB.DATA_LEN = 25;
            S000_CALL_BPZTMVBB();
            if (WS_CNT != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NEED_RECEIVE_CASH;
                S000_ERR_MSG_PROC();
            }
        }
        WS_CNT = K_NUM_1;
        while (BPCOLIBM.CCYS[WS_CNT-1].CCY.trim().length() != 0) {
            BPRCLIB.KEY.BR = BPCOLIBM.BR;
            BPRCLIB.KEY.PLBOX_NO = BPCOLIBM.PLBOX_NO;
            BPRCLIB.KEY.CASH_TYP = BPCOLIBM.CCYS[WS_CNT-1].CASH_TYP;
            BPRCLIB.KEY.CCY = BPCOLIBM.CCYS[WS_CNT-1].CCY;
            BPCTLIBF.INFO.FUNC = 'Q';
            BPCTLIBF.POINTER = BPRCLIB;
            BPCTLIBF.LEN = 352;
            S000_CALL_BPZTLIBF();
            if (BPCTLIBF.RETURN_INFO != 'F') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PLBOX_NOTFND;
                S000_ERR_MSG_PROC();
            }
            if (BPRCLIB.BAL_FLG == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BAL_FLG;
                S000_ERR_MSG_PROC();
            }
            if (BPRCLIB.BAL != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BAL_ZERO;
                S000_ERR_MSG_PROC();
            }
            WS_CNT = WS_CNT + 1;
        }
    }
    public void S000_CALL_BPZSTLVB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BR_INF_BRW, BPCSTLVB);
    }
    public void R000_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCOLIBO.DATA);
        CEP.TRC(SCCGWA, BPCOLIBO.BIND_TYP);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCOLIBM.OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOLIBO;
        SCCFMT.DATA_LEN = 882;
        IBS.FMT(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, BPCOLIBO);
    }
    public void R000_TRANS_DATA_PARA_FOR_CN() throws IOException,SQLException,Exception {
        BPRCLIB.PLBOX_TP = BPCOLIBM.PLBOX_TP;
        BPRCLIB.KEY.BR = BPCOLIBM.BR;
        BPRCLIB.KEY.PLBOX_NO = IBS.CLS2CPY(SCCGWA, WS_PLBOX_NO);
        BPRCLIB.KEY.CASH_TYP = BPCOLIBM.CCYS[WS_I-1].CASH_TYP;
        BPRCLIB.KEY.CCY = BPCOLIBM.CCYS[WS_I-1].CCY;
        BPRCLIB.LMT_CCY = BPCOLIBM.CCYS[WS_I-1].LMT_CCY;
        BPRCLIB.LMT_U = BPCOLIBM.CCYS[WS_I-1].MAX_LMT;
        CEP.TRC(SCCGWA, BPRCLIB.LMT_U);
        BPRCLIB.LMT_L = BPCOLIBM.CCYS[WS_I-1].MIN_LMT;
        BPRCLIB.BAL = 0;
        BPRCLIB.AC_DT = BPCOLIBM.UPT_DT;
        BPRCLIB.GD_AMT = 0;
        BPRCLIB.BD_AMT = 0;
        BPRCLIB.L_GD_AMT = 0;
        BPRCLIB.L_BD_AMT = 0;
        BPRCLIB.L_TLT_AMT = 0;
        BPRCLIB.LAST_DT = BPCOLIBM.UPT_DT;
        BPRCLIB.UPD_TLR = BPCOLIBM.UPT_TLR;
        BPRCLIB.OPEN_DT = BPCOLIBM.UPT_DT;
        if (BPCOLIBM.TLR.trim().length() == 0) {
            BPRCLIB.BAL_FLG = 'Y';
        } else {
            BPRCLIB.BAL_FLG = 'N';
        }
        BPRCLIB.OPEN_TLR = BPCOLIBM.UPT_TLR;
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPRCLIB.PLBOX_TP = BPCOLIBM.PLBOX_TP;
        BPRCLIB.KEY.BR = BPCOLIBM.BR;
        BPRCLIB.KEY.PLBOX_NO = IBS.CLS2CPY(SCCGWA, WS_PLBOX_NO);
        BPRCLIB.KEY.CASH_TYP = BPCOLIBM.CCYS[WS_I-1].CASH_TYP;
        BPRCLIB.KEY.CCY = BPCOLIBM.CCYS[WS_I-1].CCY;
        BPRCLIB.LMT_CCY = BPCOLIBM.CCYS[WS_I-1].LMT_CCY;
        BPRCLIB.LMT_U = BPCOLIBM.CCYS[WS_I-1].MAX_LMT;
        CEP.TRC(SCCGWA, BPRCLIB.LMT_U);
        BPRCLIB.LMT_L = BPCOLIBM.CCYS[WS_I-1].MIN_LMT;
        BPRCLIB.BAL = 0;
        BPRCLIB.AC_DT = BPCOLIBM.UPT_DT;
        BPRCLIB.GD_AMT = 0;
        BPRCLIB.BD_AMT = 0;
        BPRCLIB.L_GD_AMT = 0;
        BPRCLIB.L_BD_AMT = 0;
        BPRCLIB.L_TLT_AMT = 0;
        BPRCLIB.LAST_DT = BPCOLIBM.UPT_DT;
        BPRCLIB.UPD_TLR = BPCOLIBM.UPT_TLR;
        BPRCLIB.OPEN_DT = BPCOLIBM.UPT_DT;
        BPRCLIB.BAL_FLG = 'Y';
        BPRCLIB.OPEN_TLR = BPCOLIBM.UPT_TLR;
    }
    public void R000_MODIFY_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPRCLIB.PLBOX_TP = BPCOLIBM.PLBOX_TP;
        BPRCLIB.KEY.BR = BPCOLIBM.BR;
        BPRCLIB.KEY.PLBOX_NO = BPCOLIBM.PLBOX_NO;
        BPRCLIB.KEY.CASH_TYP = BPCOLIBM.CCYS[WS_I-1].CASH_TYP;
        BPRCLIB.KEY.CCY = BPCOLIBM.CCYS[WS_I-1].CCY;
        BPRCLIB.LMT_CCY = BPCOLIBM.CCYS[WS_I-1].LMT_CCY;
        BPRCLIB.LMT_U = BPCOLIBM.CCYS[WS_I-1].MAX_LMT;
        BPRCLIB.LMT_L = BPCOLIBM.CCYS[WS_I-1].MIN_LMT;
        BPRCLIB.BAL = 0;
        BPRCLIB.GD_AMT = 0;
        BPRCLIB.BD_AMT = 0;
        BPRCLIB.L_GD_AMT = 0;
        BPRCLIB.L_BD_AMT = 0;
        BPRCLIB.L_TLT_AMT = 0;
        BPRCLIB.AC_DT = BPCOLIBM.UPT_DT;
        BPRCLIB.LAST_DT = BPCOLIBM.UPT_DT;
        BPRCLIB.UPD_TLR = BPCOLIBM.UPT_TLR;
        BPRCLIB.OPEN_DT = BPCOLIBM.UPT_DT;
        BPRCLIB.BAL_FLG = 'Y';
        BPRCLIB.OPEN_TLR = BPCOLIBM.UPT_TLR;
    }
    public void R010_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPRTLVB.KEY.PLBOX_NO = IBS.CLS2CPY(SCCGWA, WS_PLBOX_NO);
        BPRTLVB.PLBOX_TP = BPCOLIBM.PLBOX_TP;
        BPRTLVB.KEY.BR = BPCOLIBM.BR;
        BPRTLVB.BIND_TYPE = BPCOLIBM.BIND_TYP;
        if (BPCOLIBM.TLR.trim().length() == 0) {
            BPRTLVB.RLTD_FLG = 'Y';
        } else {
            BPRTLVB.RLTD_FLG = 'N';
        }
        BPRTLVB.INSR_CCY = BPCOLIBM.INSR_CCY;
        BPRTLVB.INSR_AMT = BPCOLIBM.INSR_AMT;
        BPRTLVB.BLMT_CCY = BPCOLIBM.BLMT_CCY;
        BPRTLVB.BLMT_U = BPCOLIBM.BLMT_U;
        BPRTLVB.BLMT_L = BPCOLIBM.BLMT_L;
        BPRTLVB.UP_PBNO = BPCOLIBM.UP_PBNO;
        BPRTLVB.CRNT_TLR = BPCOLIBM.TLR;
        BPRTLVB.LAST_TLR = "00000000";
        BPRTLVB.LAST_DT = BPCOLIBM.UPT_DT;
        BPRTLVB.UPD_TLR = BPCOLIBM.UPT_TLR;
        BPRTLVB.OPEN_DT = BPCOLIBM.UPT_DT;
        BPRTLVB.OPEN_TLR = BPCOLIBM.UPT_TLR;
    }
    public void R010_TRANS_DATA_OUPUT() throws IOException,SQLException,Exception {
        BPCOLIBO.FUNC = BPCOLIBM.FUNC;
        if (BPCOLIBM.FUNC == 'I') {
            BPCOLIBO.BR = BPCOLIBM.BR;
            BPCOLIBO.TLR = BPCOLIBM.TLR;
            BPCOLIBO.PLBOX_TP = BPCOLIBM.PLBOX_TP;
            BPCOLIBO.PLBOX_NO = BPCOLIBM.PLBOX_NO;
            BPCOLIBO.INSR_CCY = BPCOLIBM.INSR_CCY;
            BPCOLIBO.INSR_AMT = BPCOLIBM.INSR_AMT;
            BPCOLIBO.BLMT_CCY = BPRTLVB.BLMT_CCY;
            BPCOLIBO.BIND_TYP = BPRTLVB.BIND_TYPE;
            CEP.TRC(SCCGWA, BPCOLIBO.BIND_TYP);
            BPCOLIBO.BLMT_U = BPRTLVB.BLMT_U;
            BPCOLIBO.BLMT_L = BPRTLVB.BLMT_L;
            BPCOLIBO.CNT = WS_CNT;
            BPCOLIBO.DATA.CCYS[BPCOLIBO.CNT-1].CASH_TYP = BPRCLIB.KEY.CASH_TYP;
            BPCOLIBO.DATA.CCYS[BPCOLIBO.CNT-1].CCY = BPRCLIB.KEY.CCY;
            BPCOLIBO.DATA.CCYS[BPCOLIBO.CNT-1].LMT_CCY = BPRCLIB.LMT_CCY;
            BPCOLIBO.DATA.CCYS[BPCOLIBO.CNT-1].MAX_LMT = BPRCLIB.LMT_U;
            BPCOLIBO.DATA.CCYS[BPCOLIBO.CNT-1].MIN_LMT = BPRCLIB.LMT_L;
            CEP.TRC(SCCGWA, BPCOLIBO.CNT);
            CEP.TRC(SCCGWA, BPCOLIBO.DATA.CCYS[BPCOLIBO.CNT-1].CASH_TYP);
            CEP.TRC(SCCGWA, BPCOLIBO.DATA.CCYS[BPCOLIBO.CNT-1].CCY);
            CEP.TRC(SCCGWA, BPCOLIBO.DATA.CCYS[BPCOLIBO.CNT-1].LMT_CCY);
            CEP.TRC(SCCGWA, BPCOLIBO.DATA.CCYS[BPCOLIBO.CNT-1].MAX_LMT);
            CEP.TRC(SCCGWA, BPCOLIBO.DATA.CCYS[BPCOLIBO.CNT-1].MIN_LMT);
        }
        if (BPCOLIBM.FUNC == 'D') {
            BPCOLIBO.BR = BPRCLIB.KEY.BR;
            BPCOLIBO.TLR = BPRTLVB.CRNT_TLR;
            BPCOLIBO.PLBOX_TP = BPRCLIB.PLBOX_TP;
            BPCOLIBO.PLBOX_NO = BPRCLIB.KEY.PLBOX_NO;
            BPCOLIBO.DATA.CCYS[WS_I-1].CASH_TYP = BPRCLIB.KEY.CASH_TYP;
            BPCOLIBO.BIND_TYP = BPRTLVB.BIND_TYPE;
            BPCOLIBO.INSR_CCY = BPRTLVB.INSR_CCY;
            BPCOLIBO.INSR_AMT = BPRTLVB.INSR_AMT;
            BPCOLIBO.BLMT_CCY = BPRTLVB.BLMT_CCY;
            BPCOLIBO.BLMT_U = BPRTLVB.BLMT_U;
            BPCOLIBO.BLMT_L = BPRTLVB.BLMT_L;
        }
        if (BPCOLIBM.FUNC == 'M') {
            BPCOLIBO.BR = BPCOLIBM.BR;
            BPCOLIBO.TLR = BPCOLIBM.TLR;
            BPCOLIBO.PLBOX_TP = BPCOLIBM.PLBOX_TP;
            BPCOLIBO.BIND_TYP = BPCOLIBM.BIND_TYP;
            BPCOLIBO.PLBOX_NO = BPCOLIBM.PLBOX_NO;
            BPCOLIBO.DATA.CCYS[WS_I-1].CASH_TYP = BPCOLIBM.CCYS[WS_I-1].CASH_TYP;
            BPCOLIBO.INSR_CCY = BPCOLIBM.INSR_CCY;
            BPCOLIBO.INSR_AMT = BPCOLIBM.INSR_AMT;
            BPCOLIBO.BLMT_CCY = BPCOLIBM.BLMT_CCY;
            BPCOLIBO.BLMT_U = BPCOLIBM.BLMT_U;
            BPCOLIBO.BLMT_L = BPCOLIBM.BLMT_L;
        }
        if (BPCOLIBM.FUNC == 'A') {
            BPCOLIBO.FUNC = BPCOLIBM.FUNC;
            BPCOLIBO.TLR = BPCOLIBM.TLR;
            BPCOLIBO.BR = BPCOLIBM.BR;
            BPCOLIBO.PLBOX_NO = IBS.CLS2CPY(SCCGWA, WS_PLBOX_NO);
            BPCOLIBO.BIND_TYP = BPCOLIBM.BIND_TYP;
            BPCOLIBO.PLBOX_TP = BPCOLIBM.PLBOX_TP;
            BPCOLIBO.INSR_CCY = BPCOLIBM.INSR_CCY;
            BPCOLIBO.INSR_AMT = BPCOLIBM.INSR_AMT;
            BPCOLIBO.BLMT_CCY = BPCOLIBM.BLMT_CCY;
            BPCOLIBO.BLMT_U = BPCOLIBM.BLMT_U;
            BPCOLIBO.BLMT_L = BPCOLIBM.BLMT_L;
            BPCOLIBO.CNT = WS_CNT;
            BPCOLIBO.DATA.CCYS[BPCOLIBO.CNT-1].CASH_TYP = BPRCLIB.KEY.CASH_TYP;
            BPCOLIBO.DATA.CCYS[BPCOLIBO.CNT-1].CCY = BPRCLIB.KEY.CCY;
            BPCOLIBO.DATA.CCYS[BPCOLIBO.CNT-1].LMT_CCY = BPRCLIB.LMT_CCY;
            BPCOLIBO.DATA.CCYS[BPCOLIBO.CNT-1].MAX_LMT = BPRCLIB.LMT_U;
            BPCOLIBO.DATA.CCYS[BPCOLIBO.CNT-1].MIN_LMT = BPRCLIB.LMT_L;
        }
        if (BPCOLIBM.FUNC == 'D' 
            && BPCOLIBM.CCYS[1-1].CCY.trim().length() > 0) {
            BPCOLIBO.DATA.CCYS[WS_I-1].CCY = BPRCLIB.KEY.CCY;
            BPCOLIBO.DATA.CCYS[WS_I-1].LMT_CCY = BPRCLIB.LMT_CCY;
            BPCOLIBO.DATA.CCYS[WS_I-1].MAX_LMT = BPRCLIB.LMT_U;
            BPCOLIBO.DATA.CCYS[WS_I-1].MIN_LMT = BPRCLIB.LMT_L;
            BPCOLIBO.CNT = WS_I;
        }
        if (BPCOLIBM.FUNC == 'M') {
            BPCOLIBO.DATA.CCYS[WS_I-1].CCY = BPRCLIB.KEY.CCY;
            CEP.TRC(SCCGWA, BPCOLIBO.DATA.CCYS[WS_I-1].CCY);
            BPCOLIBO.DATA.CCYS[WS_I-1].LMT_CCY = BPRCLIB.LMT_CCY;
            CEP.TRC(SCCGWA, BPCOLIBO.DATA.CCYS[WS_I-1].LMT_CCY);
            BPCOLIBO.DATA.CCYS[WS_I-1].MAX_LMT = BPRCLIB.LMT_U;
            CEP.TRC(SCCGWA, BPCOLIBO.DATA.CCYS[WS_I-1].MAX_LMT);
            BPCOLIBO.DATA.CCYS[WS_I-1].MIN_LMT = BPRCLIB.LMT_L;
            CEP.TRC(SCCGWA, BPCOLIBO.DATA.CCYS[WS_I-1].MIN_LMT);
            BPCOLIBO.CNT = WS_I;
        }
    }
    public void R010_TRANS_DATA_KEEP() throws IOException,SQLException,Exception {
        BPCOIBMO.FUNC = BPCOLIBM.FUNC;
        BPCOIBMO.BR = BPCOLIBM.BR;
        BPCOIBMO.TLR = BPCOLIBM.TLR;
        BPCOIBMO.BIND_TYP = BPCOLIBM.BIND_TYP;
        BPCOIBMO.PLBOX_TP = BPCOLIBM.PLBOX_TP;
        BPCOIBMO.PLBOX_NO = BPCOLIBM.PLBOX_NO;
        BPCOIBMO.INSR_CCY = BPCOLIBM.INSR_CCY;
        BPCOIBMO.INSR_AMT = BPCOLIBM.INSR_AMT;
        BPCOIBMO.BLMT_CCY = BPCOLIBM.BLMT_CCY;
        BPCOIBMO.BLMT_U = BPCOLIBM.BLMT_U;
        BPCOIBMO.BLMT_L = BPCOLIBM.BLMT_L;
        BPCOIBMO.UP_PBNO = BPCOLIBM.UP_PBNO;
        BPCOIBMO.CNT = WS_CNT;
        BPCOIBMO.DATA.CCYS[BPCOIBMO.CNT-1].CASH_TYP = BPRCLIB.KEY.CASH_TYP;
        BPCOIBMO.DATA.CCYS[BPCOIBMO.CNT-1].CCY = BPRCLIB.KEY.CCY;
        BPCOIBMO.DATA.CCYS[BPCOIBMO.CNT-1].LMT_CCY = BPRCLIB.LMT_CCY;
        BPCOIBMO.DATA.CCYS[BPCOIBMO.CNT-1].MAX_LMT = BPRCLIB.LMT_U;
        BPCOIBMO.DATA.CCYS[BPCOIBMO.CNT-1].MIN_LMT = BPRCLIB.LMT_L;
        CEP.TRC(SCCGWA, BPCOIBMO);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_BPZTLIBF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_CLIB, BPCTLIBF);
    }
    public void S000_CALL_BPZTLVBF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_TLVB, BPCTLVBF);
    }
    public void S000_CALL_BPZRTLVB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_STARTBR_TLVB, BPCRTLVB);
    }
    public void S000_CALL_BPZTMVBB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_BRE_MVBB, BPCTMVBB);
        if (BPCTMVBB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTMVBB.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPDBOX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_DEL_CBOX, BPCPDBOX);
        if (BPCPDBOX.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPDBOX.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZTLIBB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_BRE_CLIB, BPCTLIBB);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTLIBB.RC);
        if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CLIB_NOTFND) 
            && BPCTLIBB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTLIBB.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPCBOX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_CHK_CBOX, BPCPCBOX);
        if (BPCPCBOX.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPCBOX.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_GET_SEQ, BPCSGSEQ);
        CEP.TRC(SCCGWA, BPCSGSEQ.RC);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRCLIB() throws IOException,SQLException,Exception {
        BPCRCLIB.INFO.POINTER = BPRCLIB;
        BPCRCLIB.INFO.LEN = 352;
        CEP.TRC(SCCGWA, "BPCRCLIB");
        IBS.CALLCPN(SCCGWA, CPN_R_CLIB, BPCRCLIB);
        CEP.TRC(SCCGWA, BPCRCLIB.RC.RC_CODE);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRCLIB.RC);
        if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CLIB_NOTFND) 
            && BPCRCLIB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRCLIB.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_INF_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
