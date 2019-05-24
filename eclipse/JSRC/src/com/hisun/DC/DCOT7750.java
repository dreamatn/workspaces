package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.DD.*;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT7750 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DCCSUHOL DCCSUHOL = new DCCSUHOL();
    SCCGWA SCCGWA;
    DDB7750_AWA_7750 DDB7750_AWA_7750;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DCOT7750 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB7750_AWA_7750>");
        DDB7750_AWA_7750 = (DDB7750_AWA_7750) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_TRANS_DATA_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDB7750_AWA_7750.HLD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLDNO_MUST_INPUT;
            WS_FLD_NO = DDB7750_AWA_7750.HLD_NO_NO;
            S000_ERR_MSG_PROC();
        }
        if (DDB7750_AWA_7750.U_HLD_NO.trim().length() == 0 
            && DDB7750_AWA_7750.D_HLD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLDNO_MUST_INPUT;
            if (DDB7750_AWA_7750.U_HLD_NO.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(DDB7750_AWA_7750.U_HLD_NO);
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSUHOL);
        DCCSUHOL.DATA.HLD_NO = DDB7750_AWA_7750.HLD_NO;
        DCCSUHOL.DATA.DOWN_HLD_NO = DDB7750_AWA_7750.D_HLD_NO;
        DCCSUHOL.DATA.UP_HLD_NO = DDB7750_AWA_7750.U_HLD_NO;
        S000_CALL_DCZSUHOL();
    }
    public void S000_CALL_DCZSUHOL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-SVR-UHOL", DCCSUHOL);
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
