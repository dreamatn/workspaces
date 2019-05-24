package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2012 {
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "BP112";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String CPN_S_PROD_MAINTAIN = "BP-S-PROD-MAINTAIN";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_NUMB_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
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
        CEP.TRC(SCCGWA, "BPOT2012 return!");
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
        B020_DELETE_PROCESS();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2010_AWA_2010);
        if (BPB2010_AWA_2010.EFF_DATE <= SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_EFFECTTED;
            WS_FLD_NO = BPB2010_AWA_2010.EFF_DATE_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSPRDM);
        BPCSPRDM.DATA_INFO.FUNC = 'D';
        BPCSPRDM.DATA_INFO.OUTPUT_FMT = K_OUTPUT_FMT;
        R000_TRANS_DATA_PARAMETER();
        CEP.TRC(SCCGWA, BPCSPRDM);
        S000_CALL_BPZSPRDM();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCSPRDM.DATA_INFO.FUNC = BPB2010_AWA_2010.FUNC;
        BPCSPRDM.DATA_INFO.TYPE = BPB2010_AWA_2010.PARM_TP;
        BPCSPRDM.DATA_INFO.CCY = BPB2010_AWA_2010.CCY;
        BPCSPRDM.DATA_INFO.STAT = BPB2010_AWA_2010.STAT;
        BPCSPRDM.DATA_INFO.CS_KIND = BPB2010_AWA_2010.CS_KIND;
        BPCSPRDM.DATA_INFO.EFF_DATE = BPB2010_AWA_2010.EFF_DATE;
    }
    public void S000_CALL_BPZSPRDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_PROD_MAINTAIN, BPCSPRDM);
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_INF_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
        }
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
