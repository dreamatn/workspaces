package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5510 {
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "BP075";
    String CPN_F_S_MAIN_FPEN = "BP-F-S-B-Q-FPEN";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFPEN BPCFPEN = new BPCFPEN();
    SCCGWA SCCGWA;
    BPB1101_AWA_1101 BPB1101_AWA_1101;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT5510 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1101_AWA_1101>");
        BPB1101_AWA_1101 = (BPB1101_AWA_1101) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_BROWSE_BAS_INFO();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_BROWSE_BAS_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFPEN);
        BPCFPEN.FUNC = 'B';
        CEP.TRC(SCCGWA, BPCFPEN.FUNC);
        BPCFPEN.KEY.FEE_CODE = BPB1101_AWA_1101.FEE_CODE;
        BPCFPEN.KEY.PEN_TYPE = BPB1101_AWA_1101.PEN_TYPE;
        BPCFPEN.KEY.PEN_AC = BPB1101_AWA_1101.PEN_AC;
        BPCFPEN.KEY.REF_NO = BPB1101_AWA_1101.REF_NO;
        BPCFPEN.KEY.PEN_CCY = BPB1101_AWA_1101.PEN_CCY;
        BPCFPEN.OUTPUT_FMT = K_OUTPUT_FMT;
        CEP.TRC(SCCGWA, BPCFPEN.KEY.FEE_CODE);
        CEP.TRC(SCCGWA, BPCFPEN.KEY.PEN_TYPE);
        CEP.TRC(SCCGWA, BPCFPEN.KEY.PEN_AC);
        CEP.TRC(SCCGWA, BPCFPEN.KEY.REF_NO);
        CEP.TRC(SCCGWA, BPCFPEN.KEY.PEN_CCY);
        S000_CALL_BPZFSPEN();
    }
    public void S000_CALL_BPZFSPEN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_F_S_MAIN_FPEN;
        SCCCALL.COMMAREA_PTR = BPCFPEN;
        SCCCALL.ERR_FLDNO = BPB1101_AWA_1101.FEE_CODE_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
