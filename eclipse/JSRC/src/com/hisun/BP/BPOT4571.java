package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4571 {
    BPOT4571_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPOT4571_WS_TEMP_VARIABLE();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    BPCSBPRX BPCSBPRX = new BPCSBPRX();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPB1410_AWA_1410 BPB1410_AWA_1410;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "BPOT4571 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1410_AWA_1410>");
        BPB1410_AWA_1410 = (BPB1410_AWA_1410) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
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
        IBS.init(SCCGWA, BPCSBPRX);
        BPCSBPRX.TYPE = BPB1410_AWA_1410.TYPE;
        BPCSBPRX.CODE = BPB1410_AWA_1410.CODE;
        BPCSBPRX.EFF_DATE = BPB1410_AWA_1410.EFFDATE;
        if (BPB1410_AWA_1410.FUNC == 'I') {
            BPCSBPRX.TX_CD = "9994571";
        }
        if (BPB1410_AWA_1410.FUNC == 'M') {
            BPCSBPRX.TX_CD = "9994573";
        }
        if (BPB1410_AWA_1410.FUNC == 'D') {
            BPCSBPRX.TX_CD = "9994574";
        }
        BPCSBPRX.FUNC = BPB1410_AWA_1410.FUNC;
        CEP.TRC(SCCGWA, BPCSBPRX);
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
