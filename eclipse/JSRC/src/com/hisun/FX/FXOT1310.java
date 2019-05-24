package com.hisun.FX;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class FXOT1310 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_I = 0;
    FXCMSG_ERROR_MSG FXCMSG_ERROR_MSG = new FXCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    FXCSBFXT FXCSBFXT = new FXCSBFXT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    SCCGCPT SCCGCPT;
    SCCGMSG SCCGMSG;
    BPRTRT BPRTRTT;
    FXB1310_AWA_1310 FXB1310_AWA_1310;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "FXOT1310 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        IBS.init(SCCGWA, SCCMSG);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGCPT = (SCCGCPT) SCCGSCA_SC_AREA.CMPT_AREA_PTR;
        SCCGMSG = (SCCGMSG) SCCGSCA_SC_AREA.MSG_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "FXB1310_AWA_1310>");
        FXB1310_AWA_1310 = (FXB1310_AWA_1310) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_BROWSE_RECORD();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_BROWSE_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FXCSBFXT);
        CEP.TRC(SCCGWA, FXB1310_AWA_1310.CRE_DT);
        CEP.TRC(SCCGWA, FXB1310_AWA_1310.SUP1_DT);
        CEP.TRC(SCCGWA, FXB1310_AWA_1310.ORDER_BR);
        FXCSBFXT.KEY.TIK_NO = FXB1310_AWA_1310.TIK_NO;
        FXCSBFXT.STATUS = FXB1310_AWA_1310.STATUS;
        FXCSBFXT.CI_NO = FXB1310_AWA_1310.CI_NO;
        FXCSBFXT.TRA_AC = FXB1310_AWA_1310.TRA_AC;
        FXCSBFXT.CI_CNM = FXB1310_AWA_1310.CI_CNM;
        FXCSBFXT.CI_ENM = FXB1310_AWA_1310.CI_ENM;
        FXCSBFXT.PROD_CD = FXB1310_AWA_1310.PROD_CD;
        FXCSBFXT.ID_TYP = FXB1310_AWA_1310.ID_TYP;
        FXCSBFXT.ID_NO = FXB1310_AWA_1310.ID_NO;
        FXCSBFXT.CRE_DT = FXB1310_AWA_1310.CRE_DT;
        FXCSBFXT.SUP1_DT = FXB1310_AWA_1310.SUP1_DT;
        FXCSBFXT.ORDER_BR = FXB1310_AWA_1310.ORDER_BR;
        FXCSBFXT.FUNC = 'B';
        S000_CALL_FXZSBFXT();
    }
    public void S000_CALL_FXZSBFXT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "FX-S-MAIN-BFXT", FXCSBFXT);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
