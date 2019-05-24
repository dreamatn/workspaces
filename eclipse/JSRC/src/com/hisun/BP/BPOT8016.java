package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT8016 {
    String K_MMO = "BP";
    String K_CALL_BPZQFLTL = "BP-INQ-FLT-STS-LIST ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCQFLTL BPCQFLTL = new BPCQFLTL();
    SCCGWA SCCGWA;
    BPB8016_AWA_8016 BPB8016_AWA_8016;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT8016 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB8016_AWA_8016>");
        BPB8016_AWA_8016 = (BPB8016_AWA_8016) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        IBS.init(SCCGWA, BPCQFLTL);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_GET_FLT_INFO();
    }
    public void B010_GET_FLT_INFO() throws IOException,SQLException,Exception {
        BPCQFLTL.FLT_CODE = BPB8016_AWA_8016.FLT_CODE;
        S000_CALL_BPZQFLTL();
    }
    public void S000_CALL_BPZQFLTL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CALL_BPZQFLTL, BPCQFLTL);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
