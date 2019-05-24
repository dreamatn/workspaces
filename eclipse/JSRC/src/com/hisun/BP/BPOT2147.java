package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2147 {
    String K_OUTPUT_FMT = "BP223";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_ALLOT_TYPE = " ";
    BPOT2147_WS_OUTPUT_DETAIL WS_OUTPUT_DETAIL = new BPOT2147_WS_OUTPUT_DETAIL();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPQORR BPCPQORR = new BPCPQORR();
    SCCGWA SCCGWA;
    BPB2147_AWA_2147 BPB2147_AWA_2147;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2147 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2147_AWA_2147>");
        BPB2147_AWA_2147 = (BPB2147_AWA_2147) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B010_CHECK_BR_RELA();
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2147_AWA_2147.BR);
        CEP.TRC(SCCGWA, BPB2147_AWA_2147.FLG);
        CEP.TRC(SCCGWA, BPB2147_AWA_2147.ALLOT_TP);
        if (BPB2147_AWA_2147.BR == 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR128);
        }
        if (BPB2147_AWA_2147.FLG == ' ') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR129);
        } else {
            if (BPB2147_AWA_2147.FLG != '1' 
                && BPB2147_AWA_2147.FLG != '2') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR130);
            }
        }
        if (BPB2147_AWA_2147.FLG == '1') {
            if (BPB2147_AWA_2147.ALLOT_TP != '1' 
                && BPB2147_AWA_2147.ALLOT_TP != '2' 
                && BPB2147_AWA_2147.ALLOT_TP != '3') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR131);
            }
        }
    }
    public void B010_CHECK_BR_RELA() throws IOException,SQLException,Exception {
        if (BPB2147_AWA_2147.FLG == '2') {
            WS_ALLOT_TYPE = "09";
        } else {
            WS_ALLOT_TYPE = "07";
        }
        IBS.init(SCCGWA, BPCPQORR);
        BPCPQORR.TYP = WS_ALLOT_TYPE;
        BPCPQORR.BR = BPB2147_AWA_2147.BR;
        CEP.TRC(SCCGWA, BPCPQORR.BR);
        CEP.TRC(SCCGWA, BPCPQORR.TYP);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_BANK);
        S000_CALL_BPZPQORR();
        CEP.TRC(SCCGWA, BPCPQORR.REL_BR);
        CEP.TRC(SCCGWA, BPCPQORR.RC.RC_CODE);
        if (BPCPQORR.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR137);
        }
        WS_OUTPUT_DETAIL.WS_BR = BPCPQORR.REL_BR;
        if (BPCPQORR.ONWAY_DAY.trim().length() == 0) WS_OUTPUT_DETAIL.WS_ONWAY_DT = 0;
        else WS_OUTPUT_DETAIL.WS_ONWAY_DT = Integer.parseInt(BPCPQORR.ONWAY_DAY);
        CEP.TRC(SCCGWA, WS_OUTPUT_DETAIL.WS_ONWAY_DT);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUTPUT_DETAIL;
        SCCFMT.DATA_LEN = 15;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPQORR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG-REL", BPCPQORR);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
