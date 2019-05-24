package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2218 {
    String PGM_BPOT2218 = "BPOT2218";
    String PGM_SCSBJPRM = "SCSBJPRM";
    String CPN_F_F_MAINTAIN_PARM = "BP-F-F-MAINTAIN-PARM";
    String CPN_F_B_MAINTAIN_FPEN = "BP-F-B-MAINTAIN-PARM";
    String WS_ERR_MSG = " ";
    short WS_CNT = 0;
    double WS_BALANCE = 0;
    short WS_CKAC_ACNO = 0;
    BPOT2218_WS_RC WS_RC = new BPOT2218_WS_RC();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCRJPRM SCRJPRM = new SCRJPRM();
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCBOPEN BPCBOPEN = new BPCBOPEN();
    BPCBFPEN BPCBFPEN = new BPCBFPEN();
    SCCGWA SCCGWA;
    BPB2218_AWA_2218 BPB2218_AWA_2218;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2218 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2218_AWA_2218>");
        BPB2218_AWA_2218 = (BPB2218_AWA_2218) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCBFPEN);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_TRANS_BPCBFPEN();
        T000_CALL_BPZBSPEN();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2218_AWA_2218.FEE_CODE);
        if (BPB2218_AWA_2218.FEE_CODE.trim().length() == 0) {
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPB2218_AWA_2218.FEE_AC);
        if (BPB2218_AWA_2218.FEE_AC.trim().length() == 0) {
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPB2218_AWA_2218.FEE_ACTY);
        if (BPB2218_AWA_2218.FEE_ACTY == ' ') {
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPB2218_AWA_2218.FEE_CCY);
        if (BPB2218_AWA_2218.FEE_CCY.trim().length() == 0) {
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPB2218_AWA_2218.FEE_ACCT);
        if (BPB2218_AWA_2218.FEE_ACCT == ' ') {
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPB2218_AWA_2218.SUS_ACCT);
        if (BPB2218_AWA_2218.SUS_ACCT == ' ') {
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPB2218_AWA_2218.FEE_AMT);
        if (BPB2218_AWA_2218.FEE_AMT == 0) {
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPB2218_AWA_2218.NEXT_DAT);
        CEP.TRC(SCCGWA, BPB2218_AWA_2218.FAIL_FLG);
        if (BPB2218_AWA_2218.FAIL_FLG == ' ') {
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPB2218_AWA_2218.DEBT_MET);
        if (BPB2218_AWA_2218.DEBT_MET != '0' 
            && BPB2218_AWA_2218.DEBT_MET != '1' 
            && BPB2218_AWA_2218.DEBT_MET != '2') {
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPB2218_AWA_2218.RECH_MET);
        if (BPB2218_AWA_2218.RECH_MET != '0' 
            && BPB2218_AWA_2218.RECH_MET != '1' 
            && BPB2218_AWA_2218.RECH_MET != '2') {
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPB2218_AWA_2218.RECH_MET);
        CEP.TRC(SCCGWA, "CHECK INPUT END");
    }
    public void B020_TRANS_BPCBFPEN() throws IOException,SQLException,Exception {
        BPCBFPEN.FEE_CODE = BPB2218_AWA_2218.FEE_CODE;
        BPCBFPEN.FEE_AC = BPB2218_AWA_2218.FEE_AC;
        BPCBFPEN.FEE_AC_TYPE = BPB2218_AWA_2218.FEE_ACTY;
        BPCBFPEN.FEE_TX_MMO = BPB2218_AWA_2218.FEE_MMO;
        BPCBFPEN.FEE_CCY = BPB2218_AWA_2218.FEE_CCY;
        BPCBFPEN.FEE_ACCT = BPB2218_AWA_2218.FEE_ACCT;
        BPCBFPEN.SUS_ACCT = BPB2218_AWA_2218.SUS_ACCT;
        BPCBFPEN.FEE_AMT = BPB2218_AWA_2218.FEE_AMT;
        BPCBFPEN.CHGED_AMT = BPB2218_AWA_2218.CHGED_AM;
        BPCBFPEN.NEXT_DATE = BPB2218_AWA_2218.NEXT_DAT;
        BPCBFPEN.FAIL_CHG_FLG = BPB2218_AWA_2218.FAIL_FLG;
        BPCBFPEN.DEBT_METHOD = BPB2218_AWA_2218.DEBT_MET;
        BPCBFPEN.RECH_METHOD = BPB2218_AWA_2218.RECH_MET;
        BPCBFPEN.CHG_STS = BPB2218_AWA_2218.CHG_STS;
        BPCBFPEN.CHG_FAIL_CNT = BPB2218_AWA_2218.FAIL_CNT;
        CEP.TRC(SCCGWA, BPCBFPEN.FEE_CODE);
        CEP.TRC(SCCGWA, BPCBFPEN.FEE_AC);
        CEP.TRC(SCCGWA, BPCBFPEN.FEE_AC_TYPE);
        CEP.TRC(SCCGWA, BPCBFPEN.FEE_TX_MMO);
        CEP.TRC(SCCGWA, BPCBFPEN.FEE_CCY);
        CEP.TRC(SCCGWA, BPCBFPEN.FEE_ACCT);
        CEP.TRC(SCCGWA, BPCBFPEN.SUS_ACCT);
        CEP.TRC(SCCGWA, BPCBFPEN.FEE_AMT);
        CEP.TRC(SCCGWA, BPCBFPEN.CHGED_AMT);
        CEP.TRC(SCCGWA, BPCBFPEN.NEXT_DATE);
        CEP.TRC(SCCGWA, BPCBFPEN.FAIL_CHG_FLG);
        CEP.TRC(SCCGWA, BPCBFPEN.DEBT_METHOD);
        CEP.TRC(SCCGWA, BPCBFPEN.RECH_METHOD);
        CEP.TRC(SCCGWA, BPCBFPEN.CHG_STS);
        CEP.TRC(SCCGWA, BPCBFPEN.CHG_FAIL_CNT);
    }
    public void T000_CALL_BPZBSPEN() throws IOException,SQLException,Exception {
        BPCBOPEN.RC.REC_LEN = 15;
        BPCBOPEN.RC.POINTER = BPCBFPEN;
        S000_CALL_BPZBSPEN();
    }
    public void S000_CALL_BPZBSPEN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_B_MAINTAIN_FPEN, BPCBOPEN);
        if (BPCBOPEN.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCBOPEN.RC);
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
