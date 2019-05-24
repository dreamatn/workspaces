package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4526 {
    char K_FUNC_BRW = 'B';
    String K_OUTPUT_FMT = "BPX01";
    String CPN_S_MAINT_ORG = "BP-S-MAINT-ORG      ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSMORG BPCSMORG = new BPCSMORG();
    SCCGWA SCCGWA;
    BPB4520_AWA_4520 BPB4520_AWA_4520;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4526 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4520_AWA_4520>");
        BPB4520_AWA_4520 = (BPB4520_AWA_4520) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_BROWSE_ORG_RECORD();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB4520_AWA_4520.BNK.equalsIgnoreCase("0") 
            || BPB4520_AWA_4520.BNK.charAt(0) == 0X00) {
            BPB4520_AWA_4520.BNK = SCCGWA.COMM_AREA.TR_BANK;
        }
    }
    public void B020_BROWSE_ORG_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSMORG);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.BNK);
        CEP.TRC(SCCGWA, BPB4520_AWA_4520.BR);
        BPCSMORG.BNK = BPB4520_AWA_4520.BNK;
        if (BPB4520_AWA_4520.BR != 0 
            && BPB4520_AWA_4520.BR != 0X00) {
            BPCSMORG.BR = BPB4520_AWA_4520.BR;
            CEP.TRC(SCCGWA, BPCSMORG.BR);
        }
        BPCSMORG.OUTPUT_FMT = K_OUTPUT_FMT;
        BPCSMORG.FUNC = 'B';
        S000_CALL_BPZSMORG();
    }
    public void S000_CALL_BPZSMORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_MAINT_ORG, BPCSMORG);
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
