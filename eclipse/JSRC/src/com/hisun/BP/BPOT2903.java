package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2903 {
    String JIBS_tmp_str[] = new String[10];
    String K_OUTPUT_FMT = "BP221";
    String CPN_S_CSBV_BOX_MAINTAIN = "BP-S-CSBV-BOX-MAINT";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSBOXM BPCSBOXM = new BPCSBOXM();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    SCCGWA SCCGWA;
    BPB2903_AWA_2903 BPB2903_AWA_2903;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2903 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2903_AWA_2903>");
        BPB2903_AWA_2903 = (BPB2903_AWA_2903) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2903_AWA_2903);
        CEP.TRC(SCCGWA, BPB2903_AWA_2903.PLAN_DT);
        CEP.TRC(SCCGWA, BPB2903_AWA_2903.BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
        B010_CHECK_INPUT();
        B020_QUERY_BOXP();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        if (BPCFTLRQ.INFO.TX_LVL == '0') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NOT_DIRECTOR_TLR);
        }
        if (BPB2903_AWA_2903.PLAN_DT == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_DATE_ERROR;
            WS_FLD_NO = BPB2903_AWA_2903.PLAN_DT_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB2903_AWA_2903.BR == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BRANCE_MUST_INPUT;
            WS_FLD_NO = BPB2903_AWA_2903.BR_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_INF_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void B020_QUERY_BOXP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBOXM);
        BPCSBOXM.FUNC = 'I';
        BPCSBOXM.OUTPUT_FMT = K_OUTPUT_FMT;
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZSBOXM();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCSBOXM.PLAN_DT = BPB2903_AWA_2903.PLAN_DT;
        BPCSBOXM.BR = BPB2903_AWA_2903.BR;
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
