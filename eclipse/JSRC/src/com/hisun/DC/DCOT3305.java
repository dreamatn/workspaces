package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT3305 {
    double K_MAX_AMT = 1000;
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    int WS_TMP_DT = 0;
    double WS_TEMP_AMT = 0;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    DCCSQCCL DCCSQCCL = new DCCSQCCL();
    SCCGWA SCCGWA;
    DCB3305_AWA_3305 DCB3305_AWA_3305;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DCOT3305 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB3305_AWA_3305>");
        DCB3305_AWA_3305 = (DCB3305_AWA_3305) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_TRANS_DATA_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB3305_AWA_3305.OLD_JRNO);
        CEP.TRC(SCCGWA, DCB3305_AWA_3305.CARD_NO);
        CEP.TRC(SCCGWA, DCB3305_AWA_3305.CANC_AMT);
        CEP.TRC(SCCGWA, DCB3305_AWA_3305.BAL_AMT);
        CEP.TRC(SCCGWA, DCB3305_AWA_3305.QC_TYP);
        if (DCB3305_AWA_3305.OLD_JRNO == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_OLD_JRNNO_MUST_INPUT;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (DCB3305_AWA_3305.CANC_AMT == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CANC_AMT;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void B020_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSQCCL);
        DCCSQCCL.IO_AREA.OLD_JRNNO = DCB3305_AWA_3305.OLD_JRNO;
        DCCSQCCL.IO_AREA.CARD_NO = DCB3305_AWA_3305.CARD_NO;
        DCCSQCCL.IO_AREA.CANCEL_AMT = DCB3305_AWA_3305.CANC_AMT;
        DCCSQCCL.IO_AREA.BALANCE_AMT = DCB3305_AWA_3305.BAL_AMT;
        DCCSQCCL.IO_AREA.QC_TYP = DCB3305_AWA_3305.QC_TYP;
        S000_CALL_DCZSQCCL();
    }
    public void S000_CALL_DCZSQCCL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-SVR-DCZSQCCL", DCCSQCCL);
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
