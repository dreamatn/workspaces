package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT9195 {
    String CPN_S_PRTR_MAINT = "BP-S-PRTR-MAINT  ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSMPRT BPCSMPRT = new BPCSMPRT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB9200_AWA_9200 BPB9200_AWA_9200;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT9195 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB9200_AWA_9200>");
        BPB9200_AWA_9200 = (BPB9200_AWA_9200) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_ADD_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB9200_AWA_9200.PRT_NM.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PRT_NM_MST_INPUT;
            WS_FLD_NO = BPB9200_AWA_9200.PRT_NM_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB9200_AWA_9200.PRT_ADR1.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PRT_ADDR1_MST_INPUT;
            WS_FLD_NO = BPB9200_AWA_9200.PRT_ADR1_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB9200_AWA_9200.PRT_ADR2.trim().length() > 0) {
            if (BPB9200_AWA_9200.PRT_ADR1.equalsIgnoreCase(BPB9200_AWA_9200.PRT_ADR2)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PRT_ADDR_DUP;
                WS_FLD_NO = BPB9200_AWA_9200.PRT_ADR2_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB9200_AWA_9200.PRT_ADR3.trim().length() > 0) {
            if (BPB9200_AWA_9200.PRT_ADR1.equalsIgnoreCase(BPB9200_AWA_9200.PRT_ADR3)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PRT_ADDR_DUP;
                WS_FLD_NO = BPB9200_AWA_9200.PRT_ADR3_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB9200_AWA_9200.PRT_ADR2.trim().length() > 0 
            && BPB9200_AWA_9200.PRT_ADR2.trim().length() > 0) {
            if (BPB9200_AWA_9200.PRT_ADR2.equalsIgnoreCase(BPB9200_AWA_9200.PRT_ADR3)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PRT_ADDR_DUP;
                WS_FLD_NO = BPB9200_AWA_9200.PRT_ADR3_NO;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B020_ADD_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSMPRT);
        BPCSMPRT.I_FUNC = 'A';
        BPCSMPRT.PRTR_NAME = BPB9200_AWA_9200.PRT_NM;
        BPCSMPRT.PRTR_ADDR1 = BPB9200_AWA_9200.PRT_ADR1;
        BPCSMPRT.PRTR_ADDR2 = BPB9200_AWA_9200.PRT_ADR2;
        BPCSMPRT.PRTR_ADDR3 = BPB9200_AWA_9200.PRT_ADR3;
        CEP.TRC(SCCGWA, BPCSMPRT.PRTR_NAME);
        CEP.TRC(SCCGWA, BPCSMPRT.PRTR_ADDR1);
        CEP.TRC(SCCGWA, BPCSMPRT.PRTR_ADDR2);
        CEP.TRC(SCCGWA, BPCSMPRT.PRTR_ADDR3);
        S00_CALL_BPZSMPRT();
    }
    public void S00_CALL_BPZSMPRT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_PRTR_MAINT, BPCSMPRT);
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
