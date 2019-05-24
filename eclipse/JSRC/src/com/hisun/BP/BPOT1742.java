package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1742 {
    BPOT1742_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPOT1742_WS_TEMP_VARIABLE();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPCSMSG BPCSMSG = new BPCSMSG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPC1412 BPC1412;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "BPOT1742 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPC1412 = new BPC1412();
        IBS.init(SCCGWA, BPC1412);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, BPC1412);
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
        IBS.init(SCCGWA, BPCSMSG);
        BPCSMSG.TYPE = BPC1412.TYPE;
        BPCSMSG.CODE = BPC1412.CODE;
        BPCSMSG.EFF_DATE = BPC1412.EFFDATE;
        BPCSMSG.TX_CD = BPC1412.TX_CD;
        BPCSMSG.FUNC = BPC1412.FUNC;
        CEP.TRC(SCCGWA, BPCSMSG.TX_CD);
        CEP.TRC(SCCGWA, BPCSMSG.FUNC);
        S000_CALL_BPZSMSG();
    }
    public void S000_CALL_BPZSMSG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MSG-MAINTAIN", BPCSMSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
