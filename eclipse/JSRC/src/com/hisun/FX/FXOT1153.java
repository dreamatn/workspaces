package com.hisun.FX;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class FXOT1153 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    FXCMSG_ERROR_MSG FXCMSG_ERROR_MSG = new FXCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    FXCSDRFX FXCSDRFX = new FXCSDRFX();
    SCCGWA SCCGWA;
    FXB1153_AWA_1153 FXB1153_AWA_1153;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "FXOT1153 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "FXB1153_AWA_1153>");
        FXB1153_AWA_1153 = (FXB1153_AWA_1153) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_INQ_REC_PROC();
    }
    public void B010_INQ_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FXCSDRFX);
        FXCSDRFX.FUNC = 'I';
        FXCSDRFX.TRN_DT = FXB1153_AWA_1153.TRN_DT;
        FXCSDRFX.JRN_NO = FXB1153_AWA_1153.JRN_NO;
        CEP.TRC(SCCGWA, FXB1153_AWA_1153.TRN_DT);
        CEP.TRC(SCCGWA, FXB1153_AWA_1153.JRN_NO);
        S000_CALL_FXZSDRFX();
    }
    public void S000_CALL_FXZSDRFX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "FX-S-MAIN-DRFX", FXCSDRFX);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
