package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4684 {
    String JIBS_tmp_str[] = new String[10];
    String K_OUTPUT_FMT = "BPX84";
    String CPN_BP_PRE_AC = "BP-PRE-AC      ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPREAC BPCPREAC = new BPCPREAC();
    BPCIPREA BPCIPREA = new BPCIPREA();
    SCCGWA SCCGWA;
    BPB4684_AWA_4684 BPB4684_AWA_4684;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4684 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4684_AWA_4684>");
        BPB4684_AWA_4684 = (BPB4684_AWA_4684) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCIPREA);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_CALL_BPZPREAC_INQ();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_CALL_BPZPREAC_INQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPREAC);
        BPCPREAC.FLG = 'I';
        BPCPREAC.AC = BPB4684_AWA_4684.AC;
        S000_CALL_BPZPREAC();
        BPCIPREA.AC = BPB4684_AWA_4684.AC;
        BPCIPREA.CUR_BR = BPCPREAC.CUR_BR;
        BPCIPREA.PRE_BR = BPCPREAC.PRE_BR;
        BPCIPREA.RM_CR_FL = BPCPREAC.RM_CR_FL;
        BPCIPREA.STS = BPCPREAC.STS;
        BPCIPREA.REMARK = BPCPREAC.REMARK;
        BPCIPREA.LAST_UPD_DT = BPCPREAC.LAST_UPD_DT;
        BPCIPREA.LAST_UPD_TLR = BPCPREAC.LAST_UPD_TLR;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCIPREA;
        SCCFMT.DATA_LEN = 183;
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
