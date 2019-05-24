package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT7660 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DCCSOCSA DCCSOCSA = new DCCSOCSA();
    SCCGWA SCCGWA;
    DCB7660_AWA_7660 DCB7660_AWA_7660;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DCOT7660 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB7660_AWA_7660>");
        DCB7660_AWA_7660 = (DCB7660_AWA_7660) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB7660_AWA_7660.VIA_AC);
        B100_CHECK_INPUT_DATA();
        B200_TRANS_DATA_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB7660_AWA_7660);
        CEP.TRC(SCCGWA, DCB7660_AWA_7660.VIA_AC);
        if (DCB7660_AWA_7660.VIA_AC.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_VIA_AC_MUST_INPUT;
            WS_FLD_NO = DCB7660_AWA_7660.VIA_AC_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DCB7660_AWA_7660.USAGE.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_USAGE_MUST_INPUT;
            WS_FLD_NO = DCB7660_AWA_7660.USAGE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSOCSA);
        DCCSOCSA.VIA_AC = DCB7660_AWA_7660.VIA_AC;
        DCCSOCSA.USAGE = DCB7660_AWA_7660.USAGE;
        S000_CALL_DCZSOCSA();
    }
    public void S000_CALL_DCZSOCSA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-S-OPN-SUB-AC", DCCSOCSA);
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
