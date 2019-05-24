package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4682 {
    String JIBS_tmp_str[] = new String[10];
    String K_OUTPUT_FMT = "BPX82";
    String CPN_BP_PRE_AC = "BP-PRE-AC      ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPREAC BPCPREAC = new BPCPREAC();
    BPCUPREA BPCUPREA = new BPCUPREA();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    BPB4682_AWA_4682 BPB4682_AWA_4682;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4682 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4682_AWA_4682>");
        BPB4682_AWA_4682 = (BPB4682_AWA_4682) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCUPREA);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_CALL_BPZPREAC_UPD();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB4682_AWA_4682.AC);
        CEP.TRC(SCCGWA, BPB4682_AWA_4682.AC_NAME);
        CEP.TRC(SCCGWA, BPB4682_AWA_4682.CUR_BR);
        CEP.TRC(SCCGWA, BPB4682_AWA_4682.PRE_BR);
        CEP.TRC(SCCGWA, BPB4682_AWA_4682.PRE_BR_N);
        CEP.TRC(SCCGWA, BPB4682_AWA_4682.RM_CR_FL);
        CEP.TRC(SCCGWA, BPB4682_AWA_4682.REMARK);
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
        BPCPQORG.BR = BPB4682_AWA_4682.PRE_BR_N;
        S000_CALL_BPZPQORG();
        CEP.TRC(SCCGWA, BPCPQORG.EFF_DT);
        CEP.TRC(SCCGWA, BPCPQORG.EXP_DT);
        CEP.TRC(SCCGWA, BPCPQORG.OPN_DT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (SCCGWA.COMM_AREA.AC_DATE >= BPCPQORG.EFF_DT 
            && SCCGWA.COMM_AREA.AC_DATE < BPCPQORG.OPN_DT) {
        } else {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BR_STS_ERROR1);
        }
    }
    public void B020_CALL_BPZPREAC_UPD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPREAC);
        BPCPREAC.FLG = 'U';
        BPCPREAC.AC = BPB4682_AWA_4682.AC;
        BPCPREAC.PRE_BR = BPB4682_AWA_4682.PRE_BR_N;
        BPCPREAC.CUR_BR = BPB4682_AWA_4682.CUR_BR;
        BPCPREAC.RM_CR_FL = BPB4682_AWA_4682.RM_CR_FL;
        BPCPREAC.REMARK = BPB4682_AWA_4682.REMARK;
        BPCPREAC.LAST_UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPREAC.LAST_UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, BPCPREAC);
        if (BPCPREAC.CUR_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_NOT_BELONG_TO_BR);
        }
        S000_CALL_BPZPREAC();
        BPCUPREA.AC = BPB4682_AWA_4682.AC;
        BPCUPREA.AC_NAME = BPB4682_AWA_4682.AC_NAME;
        BPCUPREA.CUR_BR = BPB4682_AWA_4682.CUR_BR;
        BPCUPREA.PRE_BR = BPB4682_AWA_4682.PRE_BR;
        BPCUPREA.PRE_BR = BPB4682_AWA_4682.PRE_BR_N;
        BPCUPREA.RM_CR_FL = BPB4682_AWA_4682.RM_CR_FL;
        BPCUPREA.REMARK = BPB4682_AWA_4682.REMARK;
        BPCUPREA.LAST_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCUPREA.LAST_TL = SCCGWA.COMM_AREA.TL_ID;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCUPREA;
        SCCFMT.DATA_LEN = 441;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPREAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BP_PRE_AC, BPCPREAC);
        if (BPCPREAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPREAC.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPQORG.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
