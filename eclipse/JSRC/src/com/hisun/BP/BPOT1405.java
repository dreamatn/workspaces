package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1405 {
    BPOT1405_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPOT1405_WS_TEMP_VARIABLE();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    BPCSUPRR BPCSUPRR = new BPCSUPRR();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGWA SCCGWA;
    BPB1405_AWA_1405 BPB1405_AWA_1405;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "BPOT1405 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1405_AWA_1405>");
        BPB1405_AWA_1405 = (BPB1405_AWA_1405) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_UPD_PARR_PROCESS();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B200_UPD_PARR_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSUPRR);
        BPCSUPRR.TYPEA = BPB1405_AWA_1405.TYPEA;
        BPCSUPRR.TYPEB = BPB1405_AWA_1405.TYPEB;
        BPCSUPRR.RELA = BPB1405_AWA_1405.RELA;
        BPCSUPRR.CTRL = BPB1405_AWA_1405.CTRL;
        S000_CALL_BPZSUPRR();
    }
    public void S000_CALL_BPZSUPRR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-UPD-PRR", BPCSUPRR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
