package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT8002 {
    String K_MMO = "BP";
    String K_OUTPUT_FMT = "BP800";
    String K_CALL_BPZNQFLT = "BP-INQ-FLT-ITEM-NORM";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCNQFLT BPCNQFLT = new BPCNQFLT();
    SCCGWA SCCGWA;
    BPB8002_AWA_8002 BPB8002_AWA_8002;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT8002 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB8002_AWA_8002>");
        BPB8002_AWA_8002 = (BPB8002_AWA_8002) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCNQFLT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_GET_FLT_T_INFO();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB8002_AWA_8002.FUNCTION);
        if (BPB8002_AWA_8002.FUNCTION != 'M' 
            && BPB8002_AWA_8002.FUNCTION != 'A') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INQ_FUNC_MUST_INPUT;
            WS_FLD_NO = BPB8002_AWA_8002.FUNCTION_NO;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPB8002_AWA_8002.PARM_TYP);
        if (!BPB8002_AWA_8002.PARM_TYP.equalsIgnoreCase("NRULE")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FLT_T_TY_MUST_INPUT;
            WS_FLD_NO = BPB8002_AWA_8002.PARM_TYP_NO;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPB8002_AWA_8002.FLT_CODE);
        if (BPB8002_AWA_8002.FUNCTION == 'M' 
            && BPB8002_AWA_8002.FLT_CODE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FLT_CODE_MUST_INPUT;
            WS_FLD_NO = BPB8002_AWA_8002.FLT_CODE_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_GET_FLT_T_INFO() throws IOException,SQLException,Exception {
        BPCNQFLT.FUNC = BPB8002_AWA_8002.FUNCTION;
        BPCNQFLT.TYP = BPB8002_AWA_8002.PARM_TYP;
        BPCNQFLT.FMT = K_OUTPUT_FMT;
        BPCNQFLT.CD = BPB8002_AWA_8002.FLT_CODE;
        S000_CALL_BPZNQFLT();
    }
    public void S000_CALL_BPZNQFLT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CALL_BPZNQFLT, BPCNQFLT);
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
