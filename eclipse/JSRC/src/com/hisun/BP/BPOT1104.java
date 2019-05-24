package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1104 {
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "BP051";
    char K_RUN_MODE = 'D';
    short K_NUM = 1;
    String K_BPFBAS_SEQ = "BAS";
    String K_SEQ_TYPE = "SEQ";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_CNT1 = 0;
    int WS_DATE1 = 0;
    int WS_DATE2 = 0;
    double WS_TOT_PCNT = 0;
    double WS_PCNT1 = 0;
    double WS_PCNT2 = 0;
    double WS_PCNT3 = 0;
    short WS_MON = 0;
    int WS_DAY = 0;
    char WS_FUNC_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOFBAS BPCOFBAS = new BPCOFBAS();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCPQGLM BPCPQGLM = new BPCPQGLM();
    BPCPQBNK BPCPQBNK = new BPCPQBNK();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPREXRT BPREXRT = new BPREXRT();
    SCCGWA SCCGWA;
    BPB1104_AWA_1104 BPB1104_AWA_1104;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1104 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1104_AWA_1104>");
        BPB1104_AWA_1104 = (BPB1104_AWA_1104) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        IBS.init(SCCGWA, BPCOFBAS);
        IBS.init(SCCGWA, BPCSGSEQ);
        IBS.init(SCCGWA, BPCPQGLM);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        WS_FUNC_FLG = 'D';
        CEP.TRC(SCCGWA, WS_FUNC_FLG);
        if (WS_FUNC_FLG == 'A' 
            || WS_FUNC_FLG == 'U') {
            if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
                B010_CHECK_INPUT_CN();
            } else {
                B010_CHECK_INPUT();
            }
        }
        if (WS_FUNC_FLG == 'A') {
            B020_CREATE_BAS_INFO();
        } else if (WS_FUNC_FLG == 'U') {
            B020_MODIFY_BAS_INFO();
        } else if (WS_FUNC_FLG == 'D') {
            B020_DELETE_BAS_INFO();
        } else if (WS_FUNC_FLG == 'Q') {
            B020_QUERY_BAS_INFO();
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_FUNC_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB1104_AWA_1104.CHG_TYPE);
        if (BPB1104_AWA_1104.CHG_TYPE != '0' 
            && BPB1104_AWA_1104.CHG_TYPE != '1') {
            WS_FLD_NO = BPB1104_AWA_1104.CHG_TYPE_NO;
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CHG_MTH_ERR;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPB1104_AWA_1104.AMOT_FLG);
        if (BPB1104_AWA_1104.AMOT_FLG != '0' 
            && BPB1104_AWA_1104.AMOT_FLG != '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AMOT_FLG_INPUT_ERR;
            WS_FLD_NO = BPB1104_AWA_1104.AMOT_FLG_NO;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPB1104_AWA_1104.DEBT_MTH);
        if (BPB1104_AWA_1104.DEBT_MTH != '0' 
            && BPB1104_AWA_1104.DEBT_MTH != '1' 
            && BPB1104_AWA_1104.DEBT_MTH != '2') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DEBT_MTH_ERROR;
            WS_FLD_NO = BPB1104_AWA_1104.DEBT_MTH_NO;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPB1104_AWA_1104.RECH_MTH);
        if (BPB1104_AWA_1104.RECH_MTH != '0' 
            && BPB1104_AWA_1104.RECH_MTH != '1' 
            && BPB1104_AWA_1104.RECH_MTH != '2') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECH_MTH_ERROR;
            WS_FLD_NO = BPB1104_AWA_1104.RECH_MTH_NO;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPB1104_AWA_1104.STR_DATE);
        CEP.TRC(SCCGWA, BPB1104_AWA_1104.END_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        WS_DATE1 = BPB1104_AWA_1104.STR_DATE;
        WS_DATE2 = BPB1104_AWA_1104.END_DATE;
        if (BPB1104_AWA_1104.STR_DATE != 0 
            && WS_DATE1 < SCCGWA.COMM_AREA.AC_DATE 
            && WS_FUNC_FLG == 'A') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EFFDT_LT_ACDT;
            WS_FLD_NO = BPB1104_AWA_1104.STR_DATE_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB1104_AWA_1104.STR_DATE != 0 
            && BPB1104_AWA_1104.END_DATE != 0 
            && (WS_DATE2 < WS_DATE1 
            || WS_DATE2 == WS_DATE1)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EFFDT_GT_EXPDT;
            WS_FLD_NO = BPB1104_AWA_1104.END_DATE_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB1104_AWA_1104.STR_DATE != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = BPB1104_AWA_1104.STR_DATE;
            SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
            SCSSCKDT1.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EFF_DATE_ERR;
                WS_FLD_NO = BPB1104_AWA_1104.STR_DATE_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB1104_AWA_1104.END_DATE != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = BPB1104_AWA_1104.END_DATE;
            SCSSCKDT SCSSCKDT2 = new SCSSCKDT();
            SCSSCKDT2.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EXP_DATE_ERR;
                WS_FLD_NO = BPB1104_AWA_1104.END_DATE_NO;
                S000_ERR_MSG_PROC();
            }
        }
        CEP.TRC(SCCGWA, BPB1104_AWA_1104.DWN_AMT);
        CEP.TRC(SCCGWA, BPB1104_AWA_1104.UP_AMT);
        if ((BPB1104_AWA_1104.DWN_AMT != 0) 
            && (BPB1104_AWA_1104.UP_AMT != 0) 
            && (BPB1104_AWA_1104.UP_AMT < BPB1104_AWA_1104.DWN_AMT)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AMT_INPUT_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B010_CHECK_INPUT_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB1104_AWA_1104.CHG_TYPE);
        if (BPB1104_AWA_1104.CHG_TYPE != '0' 
            && BPB1104_AWA_1104.CHG_TYPE != '1') {
            WS_FLD_NO = BPB1104_AWA_1104.CHG_TYPE_NO;
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CHG_MTH_ERR;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPB1104_AWA_1104.AMOT_FLG);
        if (BPB1104_AWA_1104.AMOT_FLG != '0' 
            && BPB1104_AWA_1104.AMOT_FLG != '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AMOT_FLG_INPUT_ERR;
            WS_FLD_NO = BPB1104_AWA_1104.AMOT_FLG_NO;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPB1104_AWA_1104.DEBT_MTH);
        if (BPB1104_AWA_1104.DEBT_MTH != '0' 
            && BPB1104_AWA_1104.DEBT_MTH != '1' 
            && BPB1104_AWA_1104.DEBT_MTH != '2') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DEBT_MTH_ERROR;
            WS_FLD_NO = BPB1104_AWA_1104.DEBT_MTH_NO;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPB1104_AWA_1104.RECH_MTH);
        if (BPB1104_AWA_1104.RECH_MTH != '0' 
            && BPB1104_AWA_1104.RECH_MTH != '1' 
            && BPB1104_AWA_1104.RECH_MTH != '2') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECH_MTH_ERROR;
            WS_FLD_NO = BPB1104_AWA_1104.RECH_MTH_NO;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPB1104_AWA_1104.STR_DATE);
        CEP.TRC(SCCGWA, BPB1104_AWA_1104.END_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        WS_DATE1 = BPB1104_AWA_1104.STR_DATE;
        WS_DATE2 = BPB1104_AWA_1104.END_DATE;
        if (BPB1104_AWA_1104.STR_DATE != 0 
            && WS_DATE1 < SCCGWA.COMM_AREA.AC_DATE 
            && WS_FUNC_FLG == 'A') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EFFDT_LT_ACDT;
            WS_FLD_NO = BPB1104_AWA_1104.STR_DATE_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB1104_AWA_1104.STR_DATE != 0 
            && BPB1104_AWA_1104.END_DATE != 0 
            && (WS_DATE2 < WS_DATE1 
            || WS_DATE2 == WS_DATE1)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EFFDT_GT_EXPDT;
            WS_FLD_NO = BPB1104_AWA_1104.END_DATE_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB1104_AWA_1104.STR_DATE != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = BPB1104_AWA_1104.STR_DATE;
            SCSSCKDT SCSSCKDT3 = new SCSSCKDT();
            SCSSCKDT3.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EFF_DATE_ERR;
                WS_FLD_NO = BPB1104_AWA_1104.STR_DATE_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB1104_AWA_1104.END_DATE != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = BPB1104_AWA_1104.END_DATE;
            SCSSCKDT SCSSCKDT4 = new SCSSCKDT();
            SCSSCKDT4.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EXP_DATE_ERR;
                WS_FLD_NO = BPB1104_AWA_1104.END_DATE_NO;
                S000_ERR_MSG_PROC();
            }
        }
        CEP.TRC(SCCGWA, BPB1104_AWA_1104.DWN_AMT);
        CEP.TRC(SCCGWA, BPB1104_AWA_1104.UP_AMT);
        if ((BPB1104_AWA_1104.DWN_AMT != 0) 
            && (BPB1104_AWA_1104.UP_AMT != 0) 
            && (BPB1104_AWA_1104.UP_AMT < BPB1104_AWA_1104.DWN_AMT)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AMT_INPUT_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_CREATE_BAS_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B020 START..");
        IBS.init(SCCGWA, BPCSGSEQ);
        BPCSGSEQ.TYPE = K_SEQ_TYPE;
        BPCSGSEQ.CODE = K_BPFBAS_SEQ;
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSGSEQ.RUN_MODE = K_RUN_MODE;
        BPCSGSEQ.NUM = K_NUM;
        S000_CALL_BPZSGSEQ();
        IBS.init(SCCGWA, BPCOFBAS);
        BPCOFBAS.FUNC = 'A';
        BPCOFBAS.OUTPUT_FMT = K_OUTPUT_FMT;
        BPCOFBAS.VAL.FEE_NO = (short) BPCSGSEQ.SEQ;
        CEP.TRC(SCCGWA, BPCOFBAS.VAL.FEE_NO);
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZFSBAS();
    }
    public void B020_MODIFY_BAS_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOFBAS);
        BPCOFBAS.FUNC = 'U';
        BPCOFBAS.OUTPUT_FMT = K_OUTPUT_FMT;
        CEP.TRC(SCCGWA, BPCOFBAS.OUTPUT_FMT);
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZFSBAS();
    }
    public void B020_DELETE_BAS_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOFBAS);
        BPCOFBAS.FUNC = 'D';
        BPCOFBAS.OUTPUT_FMT = K_OUTPUT_FMT;
        CEP.TRC(SCCGWA, BPCOFBAS.OUTPUT_FMT);
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZFSBAS();
    }
    public void B020_QUERY_BAS_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOFBAS);
        BPCOFBAS.FUNC = 'I';
        BPCOFBAS.OUTPUT_FMT = K_OUTPUT_FMT;
        CEP.TRC(SCCGWA, BPCOFBAS.OUTPUT_FMT);
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZFSBAS();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCOFBAS.KEY.FEE_CODE = BPB1104_AWA_1104.FEE_CODE;
        BPCOFBAS.VAL.FEE_NO = 0;
        BPCOFBAS.VAL.FEE_DESC = BPB1104_AWA_1104.FEE_DESC;
        BPCOFBAS.VAL.FEE_TYPE = "001";
        BPCOFBAS.VAL.UP_AMT = BPB1104_AWA_1104.UP_AMT;
        BPCOFBAS.VAL.DWN_AMT = BPB1104_AWA_1104.DWN_AMT;
        BPCOFBAS.VAL.UP_PCT = 1;
        BPCOFBAS.VAL.DWN_PCT = 0;
        BPCOFBAS.VAL.AREA_TYPE = "99";
        BPCOFBAS.VAL.FEE_UPD_FLG = '0';
        BPCOFBAS.VAL.GL_MASTERNO1 = 0;
        BPCOFBAS.VAL.GL_MASTERNO2 = 0;
        BPCOFBAS.VAL.GL_MASTERNO3 = 0;
        BPCOFBAS.VAL.GL_MASTERNO4 = 0;
        BPCOFBAS.VAL.RATE_GROUP = BPCPQBNK.DATA_INFO.EX_RA;
        BPCOFBAS.VAL.FEE_EXG_TYP = BPB1104_AWA_1104.EXG_TYP;
        BPCOFBAS.VAL.FEE_OWN_FLG = BPB1104_AWA_1104.OWN_FLG;
        BPCOFBAS.VAL.TRN_BR_PCT = BPB1104_AWA_1104.TX_BR_PE;
        BPCOFBAS.VAL.AC_BR1_PCT = BPB1104_AWA_1104.AC_BR_PE;
        BPCOFBAS.VAL.AC_BR2_PCT = BPB1104_AWA_1104.AC2_BR_P;
        BPCOFBAS.VAL.FEE_TX_MMO = BPB1104_AWA_1104.TX_MMO;
        BPCOFBAS.VAL.FEE_CHG_TYPE = BPB1104_AWA_1104.CHG_TYPE;
        BPCOFBAS.VAL.FEE_GTH_METH = '0';
        BPCOFBAS.VAL.FEE_CYC_METH = '0';
        BPCOFBAS.VAL.FEE_DIS_METH = '0';
        BPCOFBAS.VAL.FEE_AMOT_FLG = BPB1104_AWA_1104.AMOT_FLG;
        BPCOFBAS.VAL.AMOT_CODE = " ";
        BPCOFBAS.VAL.FEE_REB_FLG = ' ';
        BPCOFBAS.VAL.REB_CODE = BPB1104_AWA_1104.REB_CODE;
        BPCOFBAS.VAL.ACCRUAL_FLG = '0';
        BPCOFBAS.VAL.ACCRUAL_CYC = 'Y';
        if ("0".trim().length() == 0) BPCOFBAS.VAL.ACCRUAL_TIMES = 0;
        else BPCOFBAS.VAL.ACCRUAL_TIMES = Short.parseShort("0");
        BPCOFBAS.VAL.ACCRUAL_DAY = 0;
        BPCOFBAS.VAL.DEBT_METH = BPB1104_AWA_1104.DEBT_MTH;
        BPCOFBAS.VAL.RECH_METH = BPB1104_AWA_1104.RECH_MTH;
        BPCOFBAS.VAL.MAX_RECH_CNT = BPB1104_AWA_1104.MRECH_CN;
        BPCOFBAS.VAL.ADV_FLG = '1';
        if (BPB1104_AWA_1104.STR_DATE == 0) {
            BPCOFBAS.VAL.FEE_EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
        } else {
            BPCOFBAS.VAL.FEE_EFF_DATE = BPB1104_AWA_1104.STR_DATE;
        }
        if (BPB1104_AWA_1104.END_DATE == 0) {
            BPCOFBAS.VAL.FEE_EXP_DATE = 99991231;
        } else {
            BPCOFBAS.VAL.FEE_EXP_DATE = BPB1104_AWA_1104.END_DATE;
        }
        BPCOFBAS.LAST_TIME = SCCGWA.COMM_AREA.AC_DATE;
        BPCOFBAS.LAST_TELLER = SCCGWA.COMM_AREA.TL_ID;
    }
    public void S000_CALL_BPZFSBAS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "BP-F-S-MAINTAIN-FBAS";
        SCCCALL.COMMAREA_PTR = BPCOFBAS;
        SCCCALL.ERR_FLDNO = BPB1104_AWA_1104.FEE_CODE;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "BP-S-GET-SEQ";
        SCCCALL.COMMAREA_PTR = BPCSGSEQ;
        SCCCALL.ERR_FLDNO = BPB1104_AWA_1104.FEE_CODE_NO;
        IBS.CALL(SCCGWA, SCCCALL);
        CEP.TRC(SCCGWA, BPCSGSEQ.RC);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQGLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-GLMT", BPCPQGLM);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
