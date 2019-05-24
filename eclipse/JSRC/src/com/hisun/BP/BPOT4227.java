package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4227 {
    String CPN_INQ_REGION_TYPE = "BP-P-INQ-RGNTYPE";
    String K_OUTPUT_FMT = "BP422";
    String WS_ERR_MSG = " ";
    BPOT4227_WS_OUTPUT_DATA WS_OUTPUT_DATA = new BPOT4227_WS_OUTPUT_DATA();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPQRGT BPCPQRGT = new BPCPQRGT();
    SCCGWA SCCGWA;
    BPB4580_AWA_4580 BPB4580_AWA_4580;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4227 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4580_AWA_4580>");
        BPB4580_AWA_4580 = (BPB4580_AWA_4580) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQRGT);
        BPCPQRGT.DATA_INFO.BNK = BPB4580_AWA_4580.BNK;
        BPCPQRGT.DATA_INFO.RGN_TYPE = BPB4580_AWA_4580.RGN_TYPE;
        CEP.TRC(SCCGWA, BPB4580_AWA_4580.BNK);
        CEP.TRC(SCCGWA, BPB4580_AWA_4580.RGN_TYPE);
        S000_CALL_BPZPQRGT();
        CEP.TRC(SCCGWA, "OK BPZPQRGT");
        CEP.TRC(SCCGWA, BPCPQRGT.DATA_INFO.UNIT_TYPE);
        WS_OUTPUT_DATA.WS_UNIT_TYPE = BPCPQRGT.DATA_INFO.UNIT_TYPE;
        WS_OUTPUT_DATA.WS_DOUBLE_FLG = BPCPQRGT.DATA_INFO.DOUBLE_FLG;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUTPUT_DATA;
        SCCFMT.DATA_LEN = 2;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPQRGT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_INQ_REGION_TYPE, BPCPQRGT);
        if (BPCPQRGT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQRGT.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
