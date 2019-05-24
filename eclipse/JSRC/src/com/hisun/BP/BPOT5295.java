package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5295 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSTAXR BPCSTAXR = new BPCSTAXR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    SCCGCPT SCCGCPT;
    SCCGMSG SCCGMSG;
    BPCPQBNK_DATA_INFO BPRPBANK;
    BPRTRT BPRTRTT;
    SCCAWAC SCCAWAC;
    BPCPORUP_DATA_INFO BPCPORUP;
    BPB5200_AWA_5200 BPB5200_AWA_5200;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT5295 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        IBS.init(SCCGWA, SCCMSG);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGCPT = (SCCGCPT) SCCGSCA_SC_AREA.CMPT_AREA_PTR;
        SCCGMSG = (SCCGMSG) SCCGSCA_SC_AREA.MSG_AREA_PTR;
        BPRTRTT = (BPRTRT) SCCGSCA_SC_AREA.TR_PARM_PTR;
        BPRPBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5200_AWA_5200>");
        BPB5200_AWA_5200 = (BPB5200_AWA_5200) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        SCCAWAC = new SCCAWAC();
        IBS.init(SCCGWA, SCCAWAC);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.AWAC_AREA_PTR, SCCAWAC);
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_INQ_RECORD();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB5200_AWA_5200.CCY.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_CCY;
            S000_ERR_MSG_PROC();
        }
        if (BPB5200_AWA_5200.TAX_TYP.equalsIgnoreCase("0")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_TAX_TYP;
            S000_ERR_MSG_PROC();
        }
        if (BPB5200_AWA_5200.BR == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_BR_TYPE;
            S000_ERR_MSG_PROC();
        }
        if (BPB5200_AWA_5200.EFF_DT == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EFF_DT_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_INQ_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSTAXR);
        BPCSTAXR.KEY.BR = BPB5200_AWA_5200.BR;
        BPCSTAXR.KEY.CCY = BPB5200_AWA_5200.CCY;
        BPCSTAXR.KEY.TAX_TYP = BPB5200_AWA_5200.TAX_TYP;
        BPCSTAXR.KEY.EFF_DT = BPB5200_AWA_5200.EFF_DT;
        BPCSTAXR.KEY.RESIDENT = BPB5200_AWA_5200.RESIDENT;
        CEP.TRC(SCCGWA, BPB5200_AWA_5200.BR);
        CEP.TRC(SCCGWA, BPB5200_AWA_5200.CCY);
        CEP.TRC(SCCGWA, BPB5200_AWA_5200.TAX_TYP);
        CEP.TRC(SCCGWA, BPB5200_AWA_5200.EFF_DT);
        CEP.TRC(SCCGWA, BPB5200_AWA_5200.RESIDENT);
        BPCSTAXR.FUNC = 'Q';
        S000_CALL_BPZSTAXR();
    }
    public void S000_CALL_BPZSTAXR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAIN-TAXR", BPCSTAXR);
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
