package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZQFLTF {
    String K_MMO = "BP";
    String K_PGM_NAME = "BPZQFLTF";
    String K_CALL_BPZQFLT = "BP-INQ-FLT-STS   ";
    String WS_ERR_MSG = " ";
    short WS_CNT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    BPCQFLT BPCQFLT = new BPCQFLT();
    SCCGWA SCCGWA;
    BPCQFLTF BPCQFLTF;
    public void MP(SCCGWA SCCGWA, BPCQFLTF BPCQFLTF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCQFLTF = BPCQFLTF;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAINTAIN_PROCESS();
        CEP.TRC(SCCGWA, "BPZQFLTF return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQFLT);
    }
    public void B000_MAINTAIN_PROCESS() throws IOException,SQLException,Exception {
        if (BPCQFLTF.FMT.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RULE_OUTPUT_FMT_VAN;
            S000_ERR_MSG_PROC();
        }
        T000_GET_NRULE_FLT_INFO();
    }
    public void T000_GET_NRULE_FLT_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "KIA");
        CEP.TRC(SCCGWA, BPCQFLTF.FLT_CODE);
        BPCQFLT.FLT_CODE = BPCQFLTF.FLT_CODE;
        S000_CALL_BPZQFLT();
        S000_DATA_OUTPUT();
    }
    public void S000_CALL_BPZQFLT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CALL_BPZQFLT, BPCQFLT);
        if (BPCQFLT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQFLT.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_DATA_OUTPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCQFLT.OUTPUT_DATA.FLT_CNT);
        WS_CNT = 1;
        while (WS_CNT <= BPCQFLT.OUTPUT_DATA.FLT_CNT) {
            BPCQFLTF.OUTPUT_DATA.DATA[WS_CNT-1].FLT_CD = BPCQFLT.OUTPUT_DATA.DATA[WS_CNT-1].FLT_CD;
            BPCQFLTF.OUTPUT_DATA.DATA[WS_CNT-1].FLT_ITM = BPCQFLT.OUTPUT_DATA.DATA[WS_CNT-1].FLT_ITM;
            BPCQFLTF.OUTPUT_DATA.DATA[WS_CNT-1].STS = BPCQFLT.OUTPUT_DATA.DATA[WS_CNT-1].STS;
            BPCQFLTF.OUTPUT_DATA.DATA[WS_CNT-1].TO_FLT = BPCQFLT.OUTPUT_DATA.DATA[WS_CNT-1].TO_FLT;
            BPCQFLTF.OUTPUT_DATA.FLT_CNT = WS_CNT;
            CEP.TRC(SCCGWA, BPCQFLT.OUTPUT_DATA.FLT_CNT);
            WS_CNT = (short) (WS_CNT + 1);
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCQFLTF.FMT;
        SCCFMT.DATA_PTR = BPCQFLTF.OUTPUT_DATA;
        SCCFMT.DATA_LEN = 3104;
        IBS.FMT(SCCGWA, SCCFMT);
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
