package com.hisun.GD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class GDOT1209 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    char WS_HIS_FLG = ' ';
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    GDCSDDDR GDCSDDDR = new GDCSDDDR();
    GDRHIS GDRHIS = new GDRHIS();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    GDB1200_AWA_1200 GDB1200_AWA_1200;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "GDOT1209 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "GDB1200_AWA_1200>");
        GDB1200_AWA_1200 = (GDB1200_AWA_1200) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_REVERSAL_PROC();
    }
    public void B100_REVERSAL_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDB1200_AWA_1200.TXDRAC);
        CEP.TRC(SCCGWA, GDB1200_AWA_1200.TXCCY);
        CEP.TRC(SCCGWA, GDB1200_AWA_1200.TXTYP);
        CEP.TRC(SCCGWA, GDB1200_AWA_1200.TXAMT_S);
        CEP.TRC(SCCGWA, GDB1200_AWA_1200.TXSTLT);
        CEP.TRC(SCCGWA, GDB1200_AWA_1200.TXCRAC);
        IBS.init(SCCGWA, GDCSDDDR);
        GDCSDDDR.DRAC = GDB1200_AWA_1200.TXDRAC;
        GDCSDDDR.AC_NM = GDB1200_AWA_1200.TXAC_NM;
        GDCSDDDR.CCY = GDB1200_AWA_1200.TXCCY;
        GDCSDDDR.CCY_TYP = GDB1200_AWA_1200.TXTYP;
        GDCSDDDR.AMT = GDB1200_AWA_1200.TXAMT_S;
        GDCSDDDR.STLT = GDB1200_AWA_1200.TXSTLT;
        GDCSDDDR.CRAC = GDB1200_AWA_1200.TXCRAC;
        GDCSDDDR.REMARK = GDB1200_AWA_1200.TXSMR;
        S000_CALL_GDZSDDDR();
    }
    public void S000_CALL_GDZSDDDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SVR-GDZSDDDR", GDCSDDDR);
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
