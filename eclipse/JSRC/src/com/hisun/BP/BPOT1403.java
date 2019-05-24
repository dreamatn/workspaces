package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1403 {
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    BPCSIPRP BPCSIPRP = new BPCSIPRP();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGWA SCCGWA;
    BPB1400_AWA_1400 BPB1400_AWA_1400;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "BPOT1403 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1400_AWA_1400>");
        BPB1400_AWA_1400 = (BPB1400_AWA_1400) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_INQ_PTR_PROCESS();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B200_INQ_PTR_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSIPRP);
        BPCSIPRP.FUNC = BPB1400_AWA_1400.FUNC;
        BPCSIPRP.DATA.KEY.TYPE = BPB1400_AWA_1400.TYPE;
        S000_CALL_BPZSIPRP();
    }
    public void S000_CALL_BPZSIPRP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-INQ-PRP", BPCSIPRP);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
