package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4077 {
    char K_ERROR = 'E';
    String WS_MSG_ERR = " ";
    short WS_FLD_NO = 0;
    String WS_MOD_NO = " ";
    BPOT4077_WS_NM WS_NM = new BPOT4077_WS_NM();
    BPOT4077_WS_KEY WS_KEY = new BPOT4077_WS_KEY();
    char WS_FND_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPRACM BPRACM = new BPRACM();
    BPCPRMM BPCPRMM = new BPCPRMM();
    SCCGWA SCCGWA;
    BPB4070_AWA_4070 BPB4070_AWA_4070;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4077 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4070_AWA_4070>");
        BPB4070_AWA_4070 = (BPB4070_AWA_4070) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_MST_TRANSFER_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_MST_TRANSFER_PROC() throws IOException,SQLException,Exception {
        R000_TRANS_DATA_PARAM();
        S000_DATA_OUTPUT();
    }
    public void R000_TRANS_DATA_PARAM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRACM);
        IBS.init(SCCGWA, BPCPRMM);
        BPRACM.KEY.TYPE = "AMACM";
        WS_KEY.WS_CNTR_TYPE = BPB4070_AWA_4070.CNTR_TYP;
        if (BPB4070_AWA_4070.PROD_TYP.equalsIgnoreCase("*")) {
            WS_KEY.WS_PROD_TYPE = " ";
        } else {
            WS_KEY.WS_PROD_TYPE = BPB4070_AWA_4070.PROD_TYP;
        }
        if (BPB4070_AWA_4070.BR == 999999) {
            WS_KEY.WS_BR = 0;
        } else {
            WS_KEY.WS_BR = BPB4070_AWA_4070.BR;
        }
        BPRACM.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_KEY);
        IBS.CPY2CLS(SCCGWA, BPRACM.KEY.CD, BPRACM.KEY.REDEFINES6);
        BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPRMM.FUNC = '3';
        S000_CALL_BPZPRMM();
    }
    public void S000_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "BP212";
        SCCFMT.DATA_LEN = 6;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        BPRACM.DATA_LEN = 645;
        CEP.TRC(SCCGWA, BPRACM.DATA_LEN);
        BPCPRMM.DAT_PTR = BPRACM;
        CEP.TRC(SCCGWA, "CALL BP-PARM-MAINTAIN");
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        if (BPCPRMM.RC.RC_RTNCODE == 0) {
            WS_MOD_NO = BPRACM.DATA.MOD_NO;
        } else {
            WS_MOD_NO = " ";
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSG_ERR, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
