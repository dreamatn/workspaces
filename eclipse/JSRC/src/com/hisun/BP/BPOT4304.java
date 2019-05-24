package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4304 {
    String K_CALL_NAME = "BP-QUR-CCY";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCBCCY BPCBCCY = new BPCBCCY();
    BPCMCCY BPCMCCY = new BPCMCCY();
    SCCGWA SCCGWA;
    BPB4301_AWA_4301 BPB4301_AWA_4301;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        B00_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4304 return!");
        Z_RET();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4301_AWA_4301>");
        BPB4301_AWA_4301 = (BPB4301_AWA_4301) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        IBS.init(SCCGWA, BPCBCCY);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B20_QUE_CCY_RECORD();
    }
    public void B20_QUE_CCY_RECORD() throws IOException,SQLException,Exception {
        R00_TRANS_DATA_PARAMETER();
        S00_CALL_BPZADDCY();
    }
    public void R00_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCBCCY.CCY = BPB4301_AWA_4301.CCY;
        BPCBCCY.CCY_CD = BPB4301_AWA_4301.CCY_CD;
        CEP.TRC(SCCGWA, BPB4301_AWA_4301.CCY);
        CEP.TRC(SCCGWA, BPB4301_AWA_4301.CCY_CD);
    }
    public void S00_CALL_BPZADDCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CALL_NAME, BPCBCCY);
        CEP.TRC(SCCGWA, BPCBCCY);
        if (BPCBCCY.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCBCCY.RC);
            S00_ERR_MSG_PROC();
        }
    }
    public void S00_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S00_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
