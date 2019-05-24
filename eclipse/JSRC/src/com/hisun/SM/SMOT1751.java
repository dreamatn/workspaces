package com.hisun.SM;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class SMOT1751 {
    String K_CALL_SMOGMCPY = "SM-GEN-MSG-COPYBOOK";
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SMCGMCPY SMCGMCPY = new SMCGMCPY();
    SCCGWA SCCGWA;
    SMB1740_AWA_1740 SMB1740_AWA_1740;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "SMOT1751 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "SMB1740_AWA_1740>");
        SMB1740_AWA_1740 = (SMB1740_AWA_1740) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        IBS.init(SCCGWA, SMCGMCPY);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SMB1740_AWA_1740.MSGCLASS);
        SMCGMCPY.APP = SMB1740_AWA_1740.MSGCLASS;
        S000_CALL_SMOGMCPY();
    }
    public void S000_CALL_SMOGMCPY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CALL_SMOGMCPY, SMCGMCPY);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
