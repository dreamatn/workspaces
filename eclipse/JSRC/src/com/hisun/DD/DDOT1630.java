package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT1630 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSDACT DDCSDACT = new DDCSDACT();
    SCCGWA SCCGWA;
    DDB1630_AWA_1630 DDB1630_AWA_1630;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DDOT1630 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB1630_AWA_1630>");
        DDB1630_AWA_1630 = (DDB1630_AWA_1630) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_TRANS_DATA_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB1630_AWA_1630.AC);
        CEP.TRC(SCCGWA, DDB1630_AWA_1630.CCY);
        CEP.TRC(SCCGWA, DDB1630_AWA_1630.CCY_TYPE);
        if (DDB1630_AWA_1630.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            WS_FLD_NO = DDB1630_AWA_1630.AC_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSDACT);
        DDCSDACT.DATA.AC = DDB1630_AWA_1630.AC;
        DDCSDACT.DATA.CCY = DDB1630_AWA_1630.CCY;
        DDCSDACT.DATA.CCY_TYPE = DDB1630_AWA_1630.CCY_TYPE;
        DDCSDACT.DATA.TX_MMO = DDB1630_AWA_1630.TX_MMO;
        DDCSDACT.DATA.REMARKS = DDB1630_AWA_1630.REMARKS;
        DDCSDACT.DATA.CUS_NM = DDB1630_AWA_1630.CUS_NM;
        DDCSDACT.DATA.ID_TYPE = DDB1630_AWA_1630.ID_TYPE;
        DDCSDACT.DATA.ID_NO = DDB1630_AWA_1630.ID_NO;
        S000_CALL_DDZSDACT();
    }
    public void S000_CALL_DDZSDACT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-DDZSDACT", DDCSDACT);
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
