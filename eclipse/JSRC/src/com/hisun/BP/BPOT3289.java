package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3289 {
    String K_OUTPUT_FMT = "BP180";
    String CPN_S_BL_USE_OUT = "BP-S-BL-USE-OUT ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSBLUO BPCSBLUO = new BPCSBLUO();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB3280_AWA_3280 BPB3280_AWA_3280;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT3289 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3280_AWA_3280>");
        BPB3280_AWA_3280 = (BPB3280_AWA_3280) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_BL_PAY_OUT();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (!BPB3280_AWA_3280.TX_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_ORIGIN_TLR;
            S000_ERR_MSG_PROC();
        }
        if (BPB3280_AWA_3280.TX_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_ORIGIN_BR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_BL_PAY_OUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBLUO);
        BPCSBLUO.OUTPUT_FMT = K_OUTPUT_FMT;
        BPCSBLUO.PAY_TYP = BPB3280_AWA_3280.PAY_TYP;
        BPCSBLUO.CI_NO = BPB3280_AWA_3280.CI_NO;
        BPCSBLUO.AC = BPB3280_AWA_3280.AC;
        BPCSBLUO.AC_NAME = BPB3280_AWA_3280.AC_NAME;
        BPCSBLUO.AC_CCY = BPB3280_AWA_3280.AC_CCY;
        BPCSBLUO.BV_CODE = BPB3280_AWA_3280.BV_CODE;
        BPCSBLUO.BV_NAME = BPB3280_AWA_3280.BV_NAME;
        BPCSBLUO.CCY = BPB3280_AWA_3280.CCY;
        BPCSBLUO.VALUE = BPB3280_AWA_3280.VALUE;
        BPCSBLUO.HEAD_NO = BPB3280_AWA_3280.HEAD_NO;
        BPCSBLUO.BEG_NO = BPB3280_AWA_3280.BEG_NO;
        BPCSBLUO.END_NO = BPB3280_AWA_3280.END_NO;
        BPCSBLUO.NUM = BPB3280_AWA_3280.NUM;
        BPCSBLUO.AMT = BPB3280_AWA_3280.AMT;
        BPCSBLUO.CHAR_CCY = BPB3280_AWA_3280.CHAR_CCY;
        BPCSBLUO.CHAR_AMT = BPB3280_AWA_3280.CHAR_AMT;
        BPCSBLUO.PAY_RSN = BPB3280_AWA_3280.PAY_RSN;
        S000_CALL_BPZSBLUO();
    }
    public void S000_CALL_BPZSBLUO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BL_USE_OUT, BPCSBLUO);
        if (BPCSBLUO.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSBLUO.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
