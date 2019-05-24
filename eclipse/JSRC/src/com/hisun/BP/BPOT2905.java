package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2905 {
    String K_OUTPUT_FMT = "BP222";
    String CPN_S_DELV_CSBV_BOX = "BP-S-DELV-CSBV-BOX";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSBOXG BPCSBOXG = new BPCSBOXG();
    SCCGWA SCCGWA;
    BPB2905_AWA_2905 BPB2905_AWA_2905;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2905 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2905_AWA_2905>");
        BPB2905_AWA_2905 = (BPB2905_AWA_2905) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_DELIVER_CASH_BV_BOX();
    }
    public void B010_DELIVER_CASH_BV_BOX() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBOXG);
        BPCSBOXG.OUTPUT_FMT = K_OUTPUT_FMT;
        BPCSBOXG.BR = BPB2905_AWA_2905.BR;
        BPCSBOXG.TLR = BPB2905_AWA_2905.TLR;
        S000_CALL_BPZSBOXG();
    }
    public void S000_CALL_BPZSBOXG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_DELV_CSBV_BOX, BPCSBOXG);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
