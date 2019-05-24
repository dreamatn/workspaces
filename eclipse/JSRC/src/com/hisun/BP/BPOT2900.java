package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2900 {
    String CPN_S_CSBV_BOX_MAINTAIN = "BP-S-CSBV-BOX-MAINT";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSBOXM BPCSBOXM = new BPCSBOXM();
    SCCGWA SCCGWA;
    BPB2900_AWA_2900 BPB2900_AWA_2900;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2900 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2900_AWA_2900>");
        BPB2900_AWA_2900 = (BPB2900_AWA_2900) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2900_AWA_2900);
        CEP.TRC(SCCGWA, BPB2900_AWA_2900.PLAN_DT);
        CEP.TRC(SCCGWA, BPB2900_AWA_2900.BR);
        B010_CHECK_INPUT();
        B020_BROWSE_BOXP();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB2900_AWA_2900.PLAN_DT == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_DATE_ERROR;
            WS_FLD_NO = BPB2900_AWA_2900.PLAN_DT_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB2900_AWA_2900.BR == 0) {
            BPB2900_AWA_2900.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        }
    }
    public void B020_BROWSE_BOXP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBOXM);
        BPCSBOXM.FUNC = 'B';
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZSBOXM();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCSBOXM.PLAN_DT = BPB2900_AWA_2900.PLAN_DT;
        BPCSBOXM.BR = BPB2900_AWA_2900.BR;
    }
    public void S000_CALL_BPZSBOXM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_CSBV_BOX_MAINTAIN, BPCSBOXM);
        if (BPCSBOXM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSBOXM.RC);
            S000_ERR_MSG_PROC();
        }
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
