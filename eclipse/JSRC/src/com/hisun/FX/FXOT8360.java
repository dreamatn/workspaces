package com.hisun.FX;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPCPRMM;
import com.hisun.BP.BPRPRMT;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class FXOT8360 {
    boolean pgmRtn = false;
    FXOT8360_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new FXOT8360_WS_TEMP_VARIABLE();
    FXOT8360_WS_OUTPUT_DATA WS_OUTPUT_DATA = new FXOT8360_WS_OUTPUT_DATA();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    FXCO8360 FXCO8360 = new FXCO8360();
    SCCFMT SCCFMT = new SCCFMT();
    SCCGWA SCCGWA;
    FXB8360_AWA_8360 FXB8360_AWA_8360;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "FXOT8360 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "FXB8360_AWA_8360>");
        FXB8360_AWA_8360 = (FXB8360_AWA_8360) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_QUERY_PROCESS();
        if (pgmRtn) return;
        B300_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B200_QUERY_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, FXB8360_AWA_8360.TYPE);
        CEP.TRC(SCCGWA, FXB8360_AWA_8360.CODE);
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, FXCO8360);
        BPCPRMM.FUNC = '3';
        BPRPRMT.KEY.TYP = FXB8360_AWA_8360.TYPE;
        BPRPRMT.KEY.CD = FXB8360_AWA_8360.CODE;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
