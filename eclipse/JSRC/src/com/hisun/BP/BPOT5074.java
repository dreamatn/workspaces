package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5074 {
    BPOT5074_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPOT5074_WS_TEMP_VARIABLE();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    BPCSBPRX BPCSBPRX = new BPCSBPRX();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPC1412 BPC1412;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "BPOT5074 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPC1412 = new BPC1412();
        IBS.init(SCCGWA, BPC1412);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, BPC1412);
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GWA_SC_AREA.TR_ATTR_AREA.SUBS_TR);
        B100_CHECK_INPUT();
        B200_UPD_PTR_PROCESS();
        CEP.TRC(SCCGWA, GWA_SC_AREA.TR_ATTR_AREA.SUBS_TR);
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B200_UPD_PTR_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPC1412.TYPE);
        CEP.TRC(SCCGWA, BPC1412.CODE);
        CEP.TRC(SCCGWA, BPC1412.EFFDATE);
        CEP.TRC(SCCGWA, BPC1412.TX_CD);
        CEP.TRC(SCCGWA, BPC1412.FUNC);
        IBS.init(SCCGWA, BPCSBPRX);
        BPCSBPRX.TYPE = BPC1412.TYPE;
        BPCSBPRX.CODE = BPC1412.CODE;
        BPCSBPRX.EFF_DATE = BPC1412.EFFDATE;
        BPCSBPRX.TX_CD = BPC1412.TX_CD;
        BPCSBPRX.FUNC = BPC1412.FUNC;
        S000_CALL_BPZSBPRX();
    }
    public void S000_CALL_BPZSBPRX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-BRW-PARX", BPCSBPRX);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
