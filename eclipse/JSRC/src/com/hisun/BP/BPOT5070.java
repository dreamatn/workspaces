package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5070 {
    BPOT5070_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPOT5070_WS_TEMP_VARIABLE();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    BPCSBPRM BPCSBPRM = new BPCSBPRM();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGWA SCCGWA;
    BPC1410 BPC1410;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "BPOT5070 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPC1410 = new BPC1410();
        IBS.init(SCCGWA, BPC1410);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, BPC1410);
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_UPD_PTR_PROCESS();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B200_UPD_PTR_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPC1410.TYPE);
        CEP.TRC(SCCGWA, BPC1410.CODE);
        CEP.TRC(SCCGWA, BPC1410.LEN);
        CEP.TRC(SCCGWA, BPC1410.LEN);
        CEP.TRC(SCCGWA, BPC1410);
        IBS.init(SCCGWA, BPCSBPRM);
        BPCSBPRM.TYPE = BPC1410.TYPE;
        BPCSBPRM.CODE = BPC1410.CODE;
        CEP.TRC(SCCGWA, "BEFORE MOVE LEN");
        BPCSBPRM.NUM = BPC1410.LEN;
        CEP.TRC(SCCGWA, "AFTER MOVE LEN");
        S000_CALL_BPZSBPRM();
    }
    public void S000_CALL_BPZSBPRM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BEFORE CALL");
        IBS.CALLCPN(SCCGWA, "BP-S-BRW-PARM", BPCSBPRM);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
