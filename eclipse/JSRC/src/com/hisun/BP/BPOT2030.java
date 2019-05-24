package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2030 {
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "BPX01";
    String CPN_S_IBAC_MT = "BP-S-IBAC-MAINTAIN";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPOT2030_WS_IBAC_KEY WS_IBAC_KEY = new BPOT2030_WS_IBAC_KEY();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSIBAC BPCSIBAC = new BPCSIBAC();
    SCCGWA SCCGWA;
    BPB2030_AWA_2030 BPB2030_AWA_2030;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2030 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2030_AWA_2030>");
        BPB2030_AWA_2030 = (BPB2030_AWA_2030) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B020_QUERY_IBAC_RECORD();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, BPB2030_AWA_2030.IBAC_CD, WS_IBAC_KEY);
        BPB2030_AWA_2030.CCY = WS_IBAC_KEY.WS_IBAC_CCY;
        BPB2030_AWA_2030.SWIFT = WS_IBAC_KEY.WS_IBAC_SWIFT;
    }
    public void B020_QUERY_IBAC_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSIBAC);
        BPCSIBAC.FUNC = 'I';
        BPCSIBAC.FUNC_CODE = BPB2030_AWA_2030.FUNC;
        BPCSIBAC.CCY = BPB2030_AWA_2030.CCY;
        BPCSIBAC.SWIFT = BPB2030_AWA_2030.SWIFT;
        BPCSIBAC.EFF_DATE = BPB2030_AWA_2030.EFF_DT;
        CEP.TRC(SCCGWA, BPCSIBAC.FUNC_CODE);
        CEP.TRC(SCCGWA, BPCSIBAC.CCY);
        CEP.TRC(SCCGWA, BPCSIBAC.SWIFT);
        CEP.TRC(SCCGWA, BPCSIBAC.EFF_DATE);
        BPCSIBAC.OUTPUT_FMT = K_OUTPUT_FMT;
        S000_CALL_BPZSIBAC();
    }
    public void S000_CALL_BPZSIBAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_S_IBAC_MT;
        SCCCALL.COMMAREA_PTR = BPCSIBAC;
        SCCCALL.ERR_FLDNO = BPB2030_AWA_2030.CCY_NO;
        IBS.CALL(SCCGWA, SCCCALL);
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
