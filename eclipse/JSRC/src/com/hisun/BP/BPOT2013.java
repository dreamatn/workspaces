package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2013 {
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "BPX01";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String CPN_S_PROD_MAINTAIN = "BP-S-PROD-MAINTAIN";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPOT2013_WS_TEMP_KEY WS_TEMP_KEY = new BPOT2013_WS_TEMP_KEY();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSPRDM BPCSPRDM = new BPCSPRDM();
    SCCGWA SCCGWA;
    BPB2010_AWA_2010 BPB2010_AWA_2010;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2013 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2010_AWA_2010>");
        BPB2010_AWA_2010 = (BPB2010_AWA_2010) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_INQUIRE_PROCESS();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_INQUIRE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSPRDM);
        BPCSPRDM.DATA_INFO.FUNC = 'I';
        BPCSPRDM.DATA_INFO.TYPE = BPB2010_AWA_2010.PARM_TP;
        BPCSPRDM.DATA_INFO.FUNC_CODE = BPB2010_AWA_2010.FUNC;
        BPCSPRDM.DATA_INFO.CCY = BPB2010_AWA_2010.CCY;
        BPCSPRDM.DATA_INFO.STAT = BPB2010_AWA_2010.STAT;
        BPCSPRDM.DATA_INFO.CS_KIND = BPB2010_AWA_2010.CS_KIND;
        BPCSPRDM.DATA_INFO.EFF_DATE = BPB2010_AWA_2010.EFF_DATE;
        BPCSPRDM.DATA_INFO.OUTPUT_FMT = K_OUTPUT_FMT;
        CEP.TRC(SCCGWA, BPCSPRDM);
        S000_CALL_BPZSPRDM();
    }
    public void S000_CALL_BPZSPRDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_PROD_MAINTAIN, BPCSPRDM);
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
