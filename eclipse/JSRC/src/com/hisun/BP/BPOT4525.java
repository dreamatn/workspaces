package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4525 {
    String K_OUTPUT_FMT = "BPX01";
    String CPN_S_MAINT_ORG = "BP-S-MAINT-ORG      ";
    String CPN_P_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPOT4525_WS_ORG_CD WS_ORG_CD = new BPOT4525_WS_ORG_CD();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCSMORG BPCSMORG = new BPCSMORG();
    SCCGWA SCCGWA;
    BPB4520_AWA_4520 BPB4520_AWA_4520;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4525 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4520_AWA_4520>");
        BPB4520_AWA_4520 = (BPB4520_AWA_4520) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (BPB4520_AWA_4520.I_FUNC != 'A') {
            B020_QUERY_ORG_INFO();
        }
        B030_SET_SUB_TRN();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.I_FUNC);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.ORG_CD);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.EFF_DT);
        IBS.CPY2CLS(SCCGWA, BPB4520_AWA_4520.ORG_CD, WS_ORG_CD);
        BPB4520_AWA_4520.BNK = WS_ORG_CD.WS_BNK;
        BPB4520_AWA_4520.BR = WS_ORG_CD.WS_BR;
        if (BPB4520_AWA_4520.BNK.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            WS_FLD_NO = BPB4520_AWA_4520.BNK_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB4520_AWA_4520.BR == 0 
            || BPB4520_AWA_4520.BR == 0X00) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            WS_FLD_NO = BPB4520_AWA_4520.BR_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_QUERY_ORG_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSMORG);
        BPCSMORG.FUNC = 'I';
        BPCSMORG.BNK = BPB4520_AWA_4520.BNK;
        BPCSMORG.BR = BPB4520_AWA_4520.BR;
        BPCSMORG.FUNC_CODE = BPB4520_AWA_4520.I_FUNC;
        BPCSMORG.EFF_DT = BPB4520_AWA_4520.EFF_DT;
        BPCSMORG.OUTPUT_FMT = K_OUTPUT_FMT;
        S000_CALL_BPZSMORG();
    }
    public void B030_SET_SUB_TRN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCSUBS);
        SCCSUBS.AP_CODE = 999;
        if (BPB4520_AWA_4520.I_FUNC == 'I') {
            SCCSUBS.TR_CODE = 4524;
        } else if (BPB4520_AWA_4520.I_FUNC == 'A') {
            SCCSUBS.TR_CODE = 4520;
        } else if (BPB4520_AWA_4520.I_FUNC == 'M') {
            SCCSUBS.TR_CODE = 4523;
        } else if (BPB4520_AWA_4520.I_FUNC == 'D') {
            SCCSUBS.TR_CODE = 4522;
        } else {
            SCCSUBS.TR_CODE = 4524;
        }
        S000_SET_SUBS_TRN();
    }
    public void S000_CALL_BPZSMORG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_S_MAINT_ORG;
        SCCCALL.COMMAREA_PTR = BPCSMORG;
        SCCCALL.ERR_FLDNO = BPB4520_AWA_4520.BR_NO;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCSMORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSMORG.RC);
            WS_FLD_NO = BPB4520_AWA_4520.BR_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_SET_SBUS_TRN, SCCSUBS);
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
