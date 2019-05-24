package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4683 {
    String JIBS_tmp_str[] = new String[10];
    String K_OUTPUT_FMT = "BPX83";
    String CPN_BP_PRE_AC = "BP-PRE-AC      ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPREAC BPCPREAC = new BPCPREAC();
    BPCDPREA BPCDPREA = new BPCDPREA();
    SCCGWA SCCGWA;
    BPB4683_AWA_4683 BPB4683_AWA_4683;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4683 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4683_AWA_4683>");
        BPB4683_AWA_4683 = (BPB4683_AWA_4683) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCDPREA);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_CALL_BPZPREAC_DEL();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_CALL_BPZPREAC_DEL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPREAC);
        BPCPREAC.FLG = 'D';
        BPCPREAC.AC = BPB4683_AWA_4683.AC;
        BPCPREAC.REMARK = BPB4683_AWA_4683.REMARK;
        S000_CALL_BPZPREAC();
        BPCDPREA.AC = BPB4683_AWA_4683.AC;
        BPCDPREA.AC_NAME = BPB4683_AWA_4683.AC_NAME;
        BPCDPREA.CUR_BR = BPB4683_AWA_4683.CUR_BR;
        BPCDPREA.PRE_BR = BPB4683_AWA_4683.PRE_BR;
        BPCDPREA.RM_CR_FL = BPB4683_AWA_4683.RM_CR_FL;
        BPCDPREA.REMARK = BPB4683_AWA_4683.REMARK;
        BPCDPREA.LAST_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCDPREA.LAST_TL = SCCGWA.COMM_AREA.TL_ID;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCDPREA;
        SCCFMT.DATA_LEN = 435;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPREAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BP_PRE_AC, BPCPREAC);
        if (BPCPREAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPREAC.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
