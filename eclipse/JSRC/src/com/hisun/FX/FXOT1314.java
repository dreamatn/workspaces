package com.hisun.FX;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class FXOT1314 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
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
    BPCPQBNK_DATA_INFO BPRPBANK;
    BPRTRT BPRTRTT;
    SCCAWAC SCCAWAC;
    BPCPORUP_DATA_INFO BPCPORUP;
    FXB1310_AWA_1310 FXB1310_AWA_1310;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "FXOT1314 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        IBS.init(SCCGWA, SCCMSG);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGCPT = (SCCGCPT) SCCGSCA_SC_AREA.CMPT_AREA_PTR;
        SCCGMSG = (SCCGMSG) SCCGSCA_SC_AREA.MSG_AREA_PTR;
        BPRTRTT = (BPRTRT) SCCGSCA_SC_AREA.TR_PARM_PTR;
        BPRPBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "FXB1310_AWA_1310>");
        FXB1310_AWA_1310 = (FXB1310_AWA_1310) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        SCCAWAC = new SCCAWAC();
        IBS.init(SCCGWA, SCCAWAC);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.AWAC_AREA_PTR, SCCAWAC);
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_INQUIRE_RECORD();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, FXB1310_AWA_1310.TIK_NO);
        if (FXB1310_AWA_1310.TIK_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_TICKET_NO_INVALID);
        }
    }
    public void B020_INQUIRE_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FXCSBFXT);
        FXCSBFXT.KEY.TIK_NO = FXB1310_AWA_1310.TIK_NO;
        FXCSBFXT.FUNC = 'Q';
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
