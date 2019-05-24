package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT6057 {
    String CPN_Q_BPZSTXNM = "BP-Q-TXN-DESC";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSTXNM BPCSTXNM = new BPCSTXNM();
    SCCGWA SCCGWA;
    BPB6057_AWA_6057 BPB6057_AWA_6057;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "BPOT6057 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB6057_AWA_6057>");
        BPB6057_AWA_6057 = (BPB6057_AWA_6057) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_GET_TXN_DESC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB6057_AWA_6057.TXN_CODE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_HIS_MUST_INP_TYP_CD;
            WS_FLD_NO = BPB6057_AWA_6057.TXN_CODE_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_GET_TXN_DESC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSTXNM);
        BPCSTXNM.TXN_CODE = BPB6057_AWA_6057.TXN_CODE;
        S000_CALL_BPZSTXNM();
    }
    public void S000_CALL_BPZSTXNM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_Q_BPZSTXNM, BPCSTXNM);
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
