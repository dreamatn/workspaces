package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT0003 {
    String CPN_UPD_RATE = "BP-U-UPD-RATE     ";
    String CPN_R_BK_MT = "BP-R-IRPD-MAINT";
    int K_MAX_CNT = 70;
    String CPN_R_CALL_RATETYP = "BP-S-SEL-RT";
    int WS_GROUP_CNT = 0;
    String WS_ERR_MSG = " ";
    BPOT0003_WS_OUT_DATE WS_OUT_DATE = new BPOT0003_WS_OUT_DATE();
    char WS_RECORD_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCEX BPCEX = new BPCEX();
    BPCTENOR BPCTENOR = new BPCTENOR();
    BPREXRT BPREXRT1 = new BPREXRT();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCFMT SCCFMT = new SCCFMT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSURTE BPCSURTE = new BPCSURTE();
    BPRIRPD BPRIRPD = new BPRIRPD();
    BPCRIPDM BPCRIPDM = new BPCRIPDM();
    BPCSCORT BPCSCORT = new BPCSCORT();
    SCCGWA SCCGWA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPB0003_AWA_0003 BPB0003_AWA_0003;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT0003 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB0003_AWA_0003>");
        BPB0003_AWA_0003 = (BPB0003_AWA_0003) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        WS_GROUP_CNT = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B020_MAIN_PROC();
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSURTE);
        WS_OUT_DATE.WS_I = 0;
        WS_GROUP_CNT = 0;
        CEP.TRC(SCCGWA, BPB0003_AWA_0003.CURRNUM);
        CEP.TRC(SCCGWA, K_MAX_CNT);
        for (WS_OUT_DATE.WS_I = 1; WS_OUT_DATE.WS_I <= BPB0003_AWA_0003.CURRNUM 
            && WS_OUT_DATE.WS_I <= K_MAX_CNT; WS_OUT_DATE.WS_I += 1) {
            WS_GROUP_CNT = WS_GROUP_CNT + 1;
        }
        if (WS_GROUP_CNT == BPB0003_AWA_0003.CURRNUM) {
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CURNUM_ERROR;
            S000_ERR_MSG_PROC();
        }
        if (BPB0003_AWA_0003.CURRNUM <= BPB0003_AWA_0003.ALLNUM) {
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CURNUM_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, "TEST 01");
        WS_OUT_DATE.WS_I = 0;
        for (WS_OUT_DATE.WS_I = 1; WS_OUT_DATE.WS_I <= BPB0003_AWA_0003.CURRNUM 
            && WS_OUT_DATE.WS_I <= K_MAX_CNT; WS_OUT_DATE.WS_I += 1) {
            if (BPB0003_AWA_0003.RATE_VAL[WS_OUT_DATE.WS_I-1].OPERATE.equalsIgnoreCase("004")) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_OPERATE_TYP_ERROR;
                S000_ERR_MSG_PROC();
            }
            if (BPB0003_AWA_0003.RATE_VAL[WS_OUT_DATE.WS_I-1].OPERATE.equalsIgnoreCase("002") 
                || BPB0003_AWA_0003.RATE_VAL[WS_OUT_DATE.WS_I-1].OPERATE.equalsIgnoreCase("003")) {
                if (BPB0003_AWA_0003.RATE_VAL[WS_OUT_DATE.WS_I-1].OACTIVED > SCCGWA.COMM_AREA.AC_DATE) {
                } else {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_OACTIVED_ERROR;
                    S000_ERR_MSG_PROC();
                }
            }
            if (BPB0003_AWA_0003.RATE_VAL[WS_OUT_DATE.WS_I-1].OPERATE.equalsIgnoreCase("001")) {
                CEP.TRC(SCCGWA, BPB0003_AWA_0003.RATE_VAL[WS_OUT_DATE.WS_I-1].VAL_DT);
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
                if (BPB0003_AWA_0003.RATE_VAL[WS_OUT_DATE.WS_I-1].VAL_DT < SCCGWA.COMM_AREA.AC_DATE) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_VAL_DT_ERROR;
                    S000_ERR_MSG_PROC();
                }
            }
            CEP.TRC(SCCGWA, "TEST02");
            BPCSURTE.BR = BPCRBANK.HEAD_BR;
            CEP.TRC(SCCGWA, BPCRBANK.HEAD_BR);
            B000_CHECK_CORE_DT();
        }
    }
    public void B000_CHECK_CORE_DT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSCORT);
        CEP.TRC(SCCGWA, WS_OUT_DATE.WS_I);
        CEP.TRC(SCCGWA, BPB0003_AWA_0003.RATE_VAL[WS_OUT_DATE.WS_I-1].OLD_TYPE);
        CEP.TRC(SCCGWA, BPB0003_AWA_0003.RATE_VAL[WS_OUT_DATE.WS_I-1].OLD_BASE);
        if (BPB0003_AWA_0003.RATE_VAL[WS_OUT_DATE.WS_I-1].OLD_TYPE.trim().length() > 0 
            && BPB0003_AWA_0003.RATE_VAL[WS_OUT_DATE.WS_I-1].OLD_BASE.trim().length() > 0) {
            BPCSCORT.INP.INP_OLD_TYPE = BPB0003_AWA_0003.RATE_VAL[WS_OUT_DATE.WS_I-1].OLD_TYPE;
            BPCSCORT.INP.INP_OLD_BASE = BPB0003_AWA_0003.RATE_VAL[WS_OUT_DATE.WS_I-1].OLD_BASE;
            BPCSCORT.FUNC = 'A';
            BPCSCORT.OP_FUNC = 'S';
            S000_CALL_BPZSCORT();
            while (BPCSCORT.SEACH_FUNC != 'N') {
                BPCSCORT.FUNC = 'A';
                BPCSCORT.OP_FUNC = 'R';
                S000_CALL_BPZSCORT();
                if (BPCSCORT.SEACH_FUNC == 'Y') {
                    S00_FINISH_SAVE_DATA();
                }
            }
            BPCSCORT.FUNC = 'A';
            BPCSCORT.OP_FUNC = 'E';
            S000_CALL_BPZSCORT();
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_MUST_INPUT_1;
            S000_ERR_MSG_PROC();
        }
    }
    public void S00_FINISH_SAVE_DATA() throws IOException,SQLException,Exception {
        BPCSURTE.CCY = BPCSCORT.OUTPUT_INFO.OUT_NEW.OUT_CCY;
        BPCSURTE.BASE_TYP = BPCSCORT.OUTPUT_INFO.OUT_NEW.OUT_RBASE;
        BPCSURTE.TENOR = BPCSCORT.OUTPUT_INFO.OUT_NEW.OUT_RTENO;
        CEP.TRC(SCCGWA, BPCSURTE.CCY);
        CEP.TRC(SCCGWA, BPCSURTE.BASE_TYP);
        CEP.TRC(SCCGWA, BPCSURTE.TENOR);
        BPCSURTE.EFF_DT = BPB0003_AWA_0003.RATE_VAL[WS_OUT_DATE.WS_I-1].VAL_DT;
        CEP.TRC(SCCGWA, BPB0003_AWA_0003.RATE_VAL[WS_OUT_DATE.WS_I-1].VAL_DT);
        BPCSURTE.RATE = BPB0003_AWA_0003.RATE_VAL[WS_OUT_DATE.WS_I-1].RATE;
        CEP.TRC(SCCGWA, "TEST03");
        IBS.init(SCCGWA, BPRIRPD);
        BPRIRPD.KEY.CCY = BPCSURTE.CCY;
        BPRIRPD.KEY.BASE_TYP = BPCSURTE.BASE_TYP;
        BPRIRPD.KEY.TENOR = BPCSURTE.TENOR;
        CEP.TRC(SCCGWA, "TEST04");
        IBS.init(SCCGWA, BPCRIPDM);
        BPCRIPDM.INFO.FUNC = 'Q';
        BPCRIPDM.INFO.OPT_2 = 'O';
        S000_CALL_BPZRIPDM();
        CEP.TRC(SCCGWA, "TEST05");
        if (BPRIRPD.CONTRL == 'Y' 
            && WS_RECORD_FLG == 'F') {
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_IRPD_ERROR;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, "TEST03");
        S00_CALL_BPZSURTE();
    }
    public void S00_CALL_BPZSURTE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_UPD_RATE, BPCSURTE);
    }
    public void S000_CALL_BPZRIPDM() throws IOException,SQLException,Exception {
        BPCRIPDM.INFO.POINTER = BPRIRPD;
        WS_RECORD_FLG = 'F';
        IBS.CALLCPN(SCCGWA, CPN_R_BK_MT, BPCRIPDM);
        if (BPCRIPDM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRIPDM.RC);
            S000_ERR_MSG_PROC();
        }
        if (BPCRIPDM.RETURN_INFO == 'N') {
            WS_RECORD_FLG = 'T';
        }
    }
    public void S000_CALL_BPZSCORT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_CALL_RATETYP, BPCSCORT);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
