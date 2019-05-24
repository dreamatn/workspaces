package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT8364 {
    int JIBS_tmp_int;
    String CPN_INQ_EXR_RATE = "BP-INQ-FX-QTP   ";
    BPOT8364_WS_OUT_DATA WS_OUT_DATA = new BPOT8364_WS_OUT_DATA();
    BPCIFQ BPCIFQ = new BPCIFQ();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPB8364_AWA_8364 BPB8364_AWA_8364;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT8364 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB8364_AWA_8364>");
        BPB8364_AWA_8364 = (BPB8364_AWA_8364) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B020_MAIN_PROC();
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCIFQ);
        CEP.TRC(SCCGWA, BPB8364_AWA_8364.EXR_TYPE);
        CEP.TRC(SCCGWA, BPB8364_AWA_8364.BR);
        CEP.TRC(SCCGWA, BPB8364_AWA_8364.CCY);
        CEP.TRC(SCCGWA, BPB8364_AWA_8364.CORR_CCY);
        CEP.TRC(SCCGWA, BPB8364_AWA_8364.DT);
        CEP.TRC(SCCGWA, BPB8364_AWA_8364.TM);
        BPCIFQ.DATA.EXR_TYPE = BPB8364_AWA_8364.EXR_TYPE;
        BPCIFQ.DATA.BR = BPB8364_AWA_8364.BR;
        BPCIFQ.DATA.CCY = BPB8364_AWA_8364.CCY;
        BPCIFQ.DATA.CORR_CCY = BPB8364_AWA_8364.CORR_CCY;
        BPCIFQ.DATA.DT = BPB8364_AWA_8364.DT;
        BPCIFQ.DATA.TM = BPB8364_AWA_8364.TM;
        S000_CALL_BPZSIFQ();
        CEP.TRC(SCCGWA, BPCIFQ.DATA.BR);
        CEP.TRC(SCCGWA, BPCIFQ.DATA.EX_CODE);
        CEP.TRC(SCCGWA, BPCIFQ.DATA.UNT);
        CEP.TRC(SCCGWA, BPCIFQ.DATA.EFF_DT);
        CEP.TRC(SCCGWA, BPCIFQ.DATA.EFF_TM);
        CEP.TRC(SCCGWA, BPCIFQ.DATA.CS_BUY);
        CEP.TRC(SCCGWA, BPCIFQ.DATA.CS_SELL);
        CEP.TRC(SCCGWA, BPCIFQ.DATA.FX_BUY);
        CEP.TRC(SCCGWA, BPCIFQ.DATA.FX_SELL);
        CEP.TRC(SCCGWA, BPCIFQ.DATA.LOC_MID);
        if (BPB8364_AWA_8364.CASH_FLG == '0') {
            if (BPCIFQ.DATA.EX_CODE == null) BPCIFQ.DATA.EX_CODE = "";
            JIBS_tmp_int = BPCIFQ.DATA.EX_CODE.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPCIFQ.DATA.EX_CODE += " ";
            if (BPB8364_AWA_8364.CCY.equalsIgnoreCase(BPCIFQ.DATA.EX_CODE.substring(0, 3))) {
                BPB8364_AWA_8364.SYS_RATE = BPCIFQ.DATA.CS_BUY;
            } else {
                BPB8364_AWA_8364.SYS_RATE = BPCIFQ.DATA.CS_SELL;
            }
        } else {
            if (BPCIFQ.DATA.EX_CODE == null) BPCIFQ.DATA.EX_CODE = "";
            JIBS_tmp_int = BPCIFQ.DATA.EX_CODE.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPCIFQ.DATA.EX_CODE += " ";
            if (BPB8364_AWA_8364.CCY.equalsIgnoreCase(BPCIFQ.DATA.EX_CODE.substring(0, 3))) {
                BPB8364_AWA_8364.SYS_RATE = BPCIFQ.DATA.FX_BUY;
            } else {
                BPB8364_AWA_8364.SYS_RATE = BPCIFQ.DATA.FX_SELL;
            }
        }
        CEP.TRC(SCCGWA, BPB8364_AWA_8364.SYS_RATE);
        WS_OUT_DATA.WS_EXR_TYPE = BPCIFQ.DATA.EXR_TYPE;
        WS_OUT_DATA.WS_BR = BPCIFQ.DATA.BR;
        WS_OUT_DATA.WS_CCY = BPCIFQ.DATA.CCY;
        WS_OUT_DATA.WS_CORR_CCY = BPCIFQ.DATA.CORR_CCY;
        WS_OUT_DATA.WS_CASH_FLG = BPB8364_AWA_8364.CASH_FLG;
        WS_OUT_DATA.WS_EX_CODE = BPCIFQ.DATA.EX_CODE;
        WS_OUT_DATA.WS_UNT = BPCIFQ.DATA.UNT;
        WS_OUT_DATA.WS_EFF_DT = BPCIFQ.DATA.EFF_DT;
        WS_OUT_DATA.WS_EFF_TM = BPCIFQ.DATA.EFF_TM;
        WS_OUT_DATA.WS_SYS_RATE = BPB8364_AWA_8364.SYS_RATE;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "BP364";
        SCCFMT.DATA_PTR = WS_OUT_DATA;
        SCCFMT.DATA_LEN = 58;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZSIFQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_INQ_EXR_RATE, BPCIFQ);
        if (BPCIFQ.RC.RTNCODE != 0) {
            CEP.ERR(SCCGWA, BPCIFQ.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
