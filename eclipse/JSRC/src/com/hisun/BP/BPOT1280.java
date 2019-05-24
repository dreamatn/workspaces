package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1280 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSFABF BPCSFABF = new BPCSFABF();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    SCCGCPT SCCGCPT;
    SCCGMSG SCCGMSG;
    BPCPQBNK_DATA_INFO BPRPBANK;
    BPRTRT BPRTRTT;
    SCCAWAC SCCAWAC;
    BPCPORUP_DATA_INFO BPCPORUP;
    BPB1280_AWA_1280 BPB1280_AWA_1280;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1280 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGCPT = (SCCGCPT) SCCGSCA_SC_AREA.CMPT_AREA_PTR;
        SCCGMSG = (SCCGMSG) SCCGSCA_SC_AREA.MSG_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1280_AWA_1280>");
        BPB1280_AWA_1280 = (BPB1280_AWA_1280) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        SCCAWAC = new SCCAWAC();
        IBS.init(SCCGWA, SCCAWAC);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.AWAC_AREA_PTR, SCCAWAC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_ADD_RECORD();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_ADD_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSFABF);
        BPCSFABF.KEY.AC = BPB1280_AWA_1280.AC;
        BPCSFABF.KEY.CCY = BPB1280_AWA_1280.CCY;
        BPCSFABF.KEY.CCY_TYPE = BPB1280_AWA_1280.CCY_TYPE;
        BPCSFABF.KEY.EFF_DATE = BPB1280_AWA_1280.EFF_DATE;
        BPCSFABF.KEY.EXP_DATE = BPB1280_AWA_1280.EXP_DATE;
        BPCSFABF.FEE_AC1 = BPB1280_AWA_1280.FEE_AC1;
        BPCSFABF.FEE_AC2 = BPB1280_AWA_1280.FEE_AC2;
        BPCSFABF.FEE_AC3 = BPB1280_AWA_1280.FEE_AC3;
        BPCSFABF.FEE_AC4 = BPB1280_AWA_1280.FEE_AC4;
        BPCSFABF.FEE_AC5 = BPB1280_AWA_1280.FEE_AC5;
        BPCSFABF.REMARK = BPB1280_AWA_1280.REMARK;
        CEP.TRC(SCCGWA, BPB1280_AWA_1280.AC);
        CEP.TRC(SCCGWA, BPB1280_AWA_1280.FEE_AC1);
        CEP.TRC(SCCGWA, BPCSFABF.KEY.AC);
        CEP.TRC(SCCGWA, BPCSFABF.FEE_AC1);
        CEP.TRC(SCCGWA, BPCSFABF.KEY.CCY_TYPE);
        BPCSFABF.FUNC = 'A';
        S000_CALL_BPZSFABF();
    }
    public void S000_CALL_BPZSFABF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-BRW-BPTFABF", BPCSFABF);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}