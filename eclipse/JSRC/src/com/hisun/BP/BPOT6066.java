package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT6066 {
    char K_ERROR = 'E';
    String CPN_PDTP_BROWER = "BP-S-BRW-PRD-TYPE   ";
    String K_CMP_SET_SUBS_TRN = "SC-SET-SUBS-TRANS";
    BPOT6066_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPOT6066_WS_TEMP_VARIABLE();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCPQPDT BPCPQPDT = new BPCPQPDT();
    BPCO702 BPCO702 = new BPCO702();
    SCCGWA SCCGWA;
    BPB6060_AWA_6060 BPB6060_AWA_6060;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "BPOT6066 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB6060_AWA_6060>");
        BPB6060_AWA_6060 = (BPB6060_AWA_6060) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_QUERY_PROCESS();
        B300_TRANS_DATA_OUT();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB6060_AWA_6060.PRDT_TYP.trim().length() == 0) {
            WS_TEMP_VARIABLE.WS_MSGID = BPCMSG_ERROR_MSG.BP_MUST_INPUT_PRD_TYPE;
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB6060_AWA_6060.PRDT_TYP_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPDT);
        BPCPQPDT.PRDT_TYPE = BPB6060_AWA_6060.PRDT_TYP;
        S000_CALL_BPZPQPDT();
        if (BPCPQPDT.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_MSGID = BPCMSG_ERROR_MSG.BP_PRD_TYPE_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void B300_TRANS_DATA_OUT() throws IOException,SQLException,Exception {
        if (BPB6060_AWA_6060.I_FUNC == 'M') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 6064;
            S000_SET_SUBS_TRN();
        } else if (BPB6060_AWA_6060.I_FUNC == 'D') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 6065;
            S000_SET_SUBS_TRN();
        } else {
        }
        IBS.init(SCCGWA, BPCO702);
        IBS.init(SCCGWA, SCCFMT);
        BPCO702.PRDT_TYPE = BPCPQPDT.PRDT_TYPE;
        BPCO702.BOTTOM_IND = BPCPQPDT.BOTTOM_IND;
        BPCO702.SUPR_TYPE = BPCPQPDT.SUPR_TYPE;
        BPCO702.DESC = BPCPQPDT.DESC;
        SCCFMT.FMTID = "BPX01";
        SCCFMT.DATA_PTR = BPCO702;
        SCCFMT.DATA_LEN = 369;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPQPDT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-PRD-TYPE", BPCPQPDT);
    }
    public void S000_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CMP_SET_SUBS_TRN, SCCSUBS);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID, WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID, WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
