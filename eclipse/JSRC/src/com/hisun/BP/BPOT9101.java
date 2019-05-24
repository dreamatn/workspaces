package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT9101 {
    String CPN_S_FEEPRD_DEF = "BP-S-MGM-FEEPRD  ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSFPDD BPCSFPDD = new BPCSFPDD();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPRFBAS BPRFBAS = new BPRFBAS();
    BPCTFBAS BPCTFBAS = new BPCTFBAS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB9100_AWA_9100 BPB9100_AWA_9100;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT9101 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB9100_AWA_9100>");
        BPB9100_AWA_9100 = (BPB9100_AWA_9100) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_ADD_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB9100_AWA_9100.ACCT_CTR != 0 
            && BPB9100_AWA_9100.ACCT_CTR != ' ') {
            if (BPB9100_AWA_9100.ACCT_CTR != 999999) {
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
                BPCPQORG.BR = BPB9100_AWA_9100.ACCT_CTR;
                S000_CALL_BPZPQORG();
            }
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ACCT_CTR_MUST_INPUT;
            WS_FLD_NO = BPB9100_AWA_9100.ACCT_CTR_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB9100_AWA_9100.PRD_TYPE.trim().length() > 0) {
            IBS.init(SCCGWA, BPCPQPRD);
            BPCPQPRD.PRDT_CODE = BPB9100_AWA_9100.PRD_TYPE;
            S000_CALL_BPZPQPRD();
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PRD_CODE_MUSTINPUT;
            WS_FLD_NO = BPB9100_AWA_9100.PRD_TYPE_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB9100_AWA_9100.FEE_TYPE.trim().length() > 0) {
            IBS.init(SCCGWA, BPRFBAS);
            IBS.init(SCCGWA, BPCTFBAS);
            BPRFBAS.KEY.FEE_CODE = BPB9100_AWA_9100.FEE_TYPE;
            BPCTFBAS.INFO.FUNC = 'Q';
            BPCTFBAS.INFO.POINTER = BPRFBAS;
            BPCTFBAS.INFO.REC_LEN = 312;
            S000_CALL_BPZTFBAS();
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CODE_MUSTINPUT;
            WS_FLD_NO = BPB9100_AWA_9100.FEE_TYPE_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_ADD_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSFPDD);
        BPCSFPDD.KEY.PROD_TYPE = BPB9100_AWA_9100.PRD_TYPE;
        BPCSFPDD.KEY.ACCT_CENTRE = BPB9100_AWA_9100.ACCT_CTR;
        BPCSFPDD.INF.ENG_DESC = BPB9100_AWA_9100.ENG_DESC;
        BPCSFPDD.INF.CHN_DESC = BPB9100_AWA_9100.CHN_DESC;
        BPCSFPDD.INF.FEE_TYPE = BPB9100_AWA_9100.FEE_TYPE;
        BPCSFPDD.INF.PAY_IND = BPB9100_AWA_9100.PAY_IND;
        BPCSFPDD.INF.PAY_METHOD = BPB9100_AWA_9100.PAY_MTHD;
        BPCSFPDD.INF.SETTL_FREQ = BPB9100_AWA_9100.SET_FREQ;
        BPCSFPDD.INF.PERIOD_COUNT = BPB9100_AWA_9100.PRD_CONT;
        BPCSFPDD.INF.SETTL_DATE = BPB9100_AWA_9100.SET_DT;
        BPCSFPDD.INF.HOLI_OVER = BPB9100_AWA_9100.HOL_OVER;
        BPCSFPDD.INF.HOLI_METHOD = BPB9100_AWA_9100.HOL_MTHD;
        BPCSFPDD.INF.CASHFLOW_IND = BPB9100_AWA_9100.CFL_IND;
        BPCSFPDD.INF.INT_BAS = BPB9100_AWA_9100.INT_BAS;
        BPCSFPDD.INF.MSG_PRI = BPB9100_AWA_9100.MSG_PRI;
        BPCSFPDD.INF.EFF_DATE = BPB9100_AWA_9100.EFF_DATE;
        BPCSFPDD.INF.EXP_DATE = BPB9100_AWA_9100.EXP_DATE;
        CEP.TRC(SCCGWA, BPB9100_AWA_9100.EFF_DATE);
        CEP.TRC(SCCGWA, BPB9100_AWA_9100.EXP_DATE);
        BPCSFPDD.FUNC = 'A';
        S00_CALL_BPZSFPDD();
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            WS_FLD_NO = BPB9100_AWA_9100.PRD_TYPE_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            WS_FLD_NO = BPB9100_AWA_9100.ACCT_CTR_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZTFBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-T-FEE-INFO", BPCTFBAS);
        if (BPCTFBAS.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CODE_NOTFND;
            WS_FLD_NO = BPB9100_AWA_9100.FEE_TYPE_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S00_CALL_BPZSFPDD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_FEEPRD_DEF, BPCSFPDD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
