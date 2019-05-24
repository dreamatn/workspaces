package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class BPZFSDER {
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZFSDER";
    String K_OUTPUT_FMT = "BP080";
    String CPN_F_GET_DER_LIMIT = "BP-F-U-GET-DER-LIMIT";
    String WS_ERR_MSG = " ";
    short WS_CNT1 = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCODERO BPCODERO = new BPCODERO();
    SCCGWA SCCGWA;
    BPCOFDER BPCOSDER;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCOFDER BPCOSDER) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOSDER = BPCOSDER;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFSDER return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCOSDER.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        BPCOSDER.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCOSDER.INFO.DER_CODE);
        if (BPCOSDER.INFO.DER_CODE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FDER_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        S000_CALL_BPZFUDER();
        if (pgmRtn) return;
        B010_OUT_DER_INFO();
        if (pgmRtn) return;
    }
    public void B010_OUT_DER_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCODERO);
        for (WS_CNT1 = 1; WS_CNT1 <= 20; WS_CNT1 += 1) {
            BPCODERO.INFO.DATA[WS_CNT1-1].FEE_CODE = BPCOSDER.INFO.DATA[WS_CNT1-1].FEE_CODE;
            CEP.TRC(SCCGWA, BPCOSDER.INFO.DATA[WS_CNT1-1].FEE_CODE);
            BPCODERO.INFO.DATA[WS_CNT1-1].REL_FLG = BPCOSDER.INFO.DATA[WS_CNT1-1].REL_FLG;
            CEP.TRC(SCCGWA, BPCOSDER.INFO.DATA[WS_CNT1-1].REL_FLG);
            BPCODERO.INFO.DATA[WS_CNT1-1].DER_AMT = BPCOSDER.INFO.DATA[WS_CNT1-1].DER_AMT;
            CEP.TRC(SCCGWA, BPCOSDER.INFO.DATA[WS_CNT1-1].DER_AMT);
            BPCODERO.INFO.DATA[WS_CNT1-1].DER_AUTH = BPCOSDER.INFO.DATA[WS_CNT1-1].DER_AUTH;
            CEP.TRC(SCCGWA, BPCOSDER.INFO.DATA[WS_CNT1-1].DER_AUTH);
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCOSDER.OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCODERO;
        SCCFMT.DATA_LEN = 680;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_BPZFUDER() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_GET_DER_LIMIT, BPCOSDER);
        if (BPCOSDER.RC.RC_CODE != 0) {
