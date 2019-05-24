package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT6006 {
    char K_ERROR = 'E';
    BPOT6006_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPOT6006_WS_TEMP_VARIABLE();
    BPOT6006_WS_OUT_DATA WS_OUT_DATA = new BPOT6006_WS_OUT_DATA();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
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
        CEP.TRC(SCCGWA, "BPOT6006 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB6060_AWA_6060>");
        BPB6060_AWA_6060 = (BPB6060_AWA_6060) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCPQPDT);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_QUERY_PROCESS();
        B200_OUT_PROCESS();
    }
    public void B100_QUERY_PROCESS() throws IOException,SQLException,Exception {
        if (BPB6060_AWA_6060.PRDT_TYP.trim().length() == 0) {
        } else {
            BPCPQPDT.PRDT_TYPE = BPB6060_AWA_6060.PRDT_TYP;
            CEP.TRC(SCCGWA, BPB6060_AWA_6060.PRDT_TYP);
            S000_CALL_BPZPQPDT();
            CEP.TRC(SCCGWA, BPCPQPDT.RC);
            if (BPCPQPDT.RC.RC_CODE == 0) {
                WS_OUT_DATA.WS_OUT_PRDT_TYPE = BPCPQPDT.PRDT_TYPE;
                WS_OUT_DATA.WS_OUT_BOTTOM_IND = BPCPQPDT.BOTTOM_IND;
                WS_OUT_DATA.WS_OUT_SUPR_TYPE = BPCPQPDT.SUPR_TYPE;
                WS_OUT_DATA.WS_OUT_DESC = BPCPQPDT.DESC;
                WS_OUT_DATA.WS_OUT_DESC_ENG = BPCPQPDT.DESC_ENG;
                WS_OUT_DATA.WS_OUT_OPEN_DATE = BPCPQPDT.OPEN_DATE;
                CEP.TRC(SCCGWA, BPCPQPDT.DESC);
                CEP.TRC(SCCGWA, BPCPQPDT.DESC_ENG);
            } else {
                WS_TEMP_VARIABLE.WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPQPDT.RC);
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B200_OUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCO702);
        IBS.init(SCCGWA, SCCFMT);
        BPCO702.PRDT_TYPE = WS_OUT_DATA.WS_OUT_PRDT_TYPE;
        BPCO702.BOTTOM_IND = WS_OUT_DATA.WS_OUT_BOTTOM_IND;
        BPCO702.SUPR_TYPE = WS_OUT_DATA.WS_OUT_SUPR_TYPE;
        BPCO702.DESC = WS_OUT_DATA.WS_OUT_DESC;
        BPCO702.DESC_ENG = WS_OUT_DATA.WS_OUT_DESC_ENG;
        BPCO702.FLAG = 0X02;
        BPCO702.SUPR_DESC = WS_OUT_DATA.WS_OUT_DESC;
        BPCO702.SUPR_DESC_ENG = WS_OUT_DATA.WS_OUT_DESC_ENG;
        BPCO702.SUPR_FLAG = 0X02;
        SCCFMT.FMTID = "BP702";
        CEP.TRC(SCCGWA, BPCO702);
        SCCFMT.DATA_PTR = BPCO702;
        SCCFMT.DATA_LEN = 369;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPQPDT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-PRD-TYPE", BPCPQPDT);
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
