package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4596 {
    String K_CPN_S_MAINTAIN_CNTG = "BP-S-MAINTAIN-CNTG  ";
    String CPN_P_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    String K_OUTPUT_FMT = "BPX01";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCSCNTG BPCSCNTG = new BPCSCNTG();
    SCCGWA SCCGWA;
    BPB4596_AWA_4596 BPB4596_AWA_4596;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4596 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4596_AWA_4596>");
        BPB4596_AWA_4596 = (BPB4596_AWA_4596) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_CNTYG_INFO_QUERY();
        B030_SET_SUB_TRN();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_CNTYG_INFO_QUERY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSCNTG);
        BPCSCNTG.FUNC = 'Q';
        CEP.TRC(SCCGWA, BPB4596_AWA_4596.AREA_CD);
        CEP.TRC(SCCGWA, BPB4596_AWA_4596.GROUP_CD);
        CEP.TRC(SCCGWA, BPB4596_AWA_4596.CNTY_CD);
        CEP.TRC(SCCGWA, BPB4596_AWA_4596.EFF_DT2);
        BPCSCNTG.KEY.AREA_CD = BPB4596_AWA_4596.AREA_CD;
        BPCSCNTG.KEY.GROUP_CD = BPB4596_AWA_4596.GROUP_CD;
        BPCSCNTG.KEY.CNTY_CD = BPB4596_AWA_4596.CNTY_CD;
        BPCSCNTG.EFF_DATE_PARM = BPB4596_AWA_4596.EFF_DT2;
        S010_CALL_BPZSCNTG();
    }
    public void S010_CALL_BPZSCNTG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CPN_S_MAINTAIN_CNTG, BPCSCNTG);
    }
    public void B030_SET_SUB_TRN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCSUBS);
        SCCSUBS.AP_CODE = 999;
        if (BPB4596_AWA_4596.FUNC == 'I') {
            SCCSUBS.TR_CODE = 4595;
        } else if (BPB4596_AWA_4596.FUNC == 'M') {
            SCCSUBS.TR_CODE = 4592;
        } else if (BPB4596_AWA_4596.FUNC == 'D') {
            SCCSUBS.TR_CODE = 4593;
        } else {
            SCCSUBS.TR_CODE = 4595;
        }
        S000_SET_SUBS_TRN();
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_SET_SBUS_TRN, SCCSUBS);
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
