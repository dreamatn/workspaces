package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5254 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSMMRT BPCSMMRT = new BPCSMMRT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB5250_AWA_5250 BPB5250_AWA_5250;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT5254 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5250_AWA_5250>");
        BPB5250_AWA_5250 = (BPB5250_AWA_5250) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        IBS.init(SCCGWA, BPCSMMRT);
        BPCSMMRT.FUNC = 'D';
        BPCSMMRT.BASE_TYP = BPB5250_AWA_5250.RATE_TYP;
        BPCSMMRT.CCY = BPB5250_AWA_5250.CCY;
        BPCSMMRT.UPD_DATA[1-1].TENOR = BPB5250_AWA_5250.BRT_TBL[1-1].TENOR;
        BPCSMMRT.UPD_DATA[1-1].OEFF_DT = BPB5250_AWA_5250.BRT_TBL[1-1].OEFF_DT;
        S010_CALL_BPZSMMRT();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB5250_AWA_5250.RATE_TYP);
        CEP.TRC(SCCGWA, BPB5250_AWA_5250.CCY);
        CEP.TRC(SCCGWA, BPB5250_AWA_5250.BRT_TBL[1-1].TENOR);
        CEP.TRC(SCCGWA, BPB5250_AWA_5250.BRT_TBL[1-1].OEFF_DT);
        if (BPB5250_AWA_5250.BRT_TBL[1-1].OEFF_DT <= SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CANNOT_DEL_EFF_RT;
            WS_FLD_NO = BPB5250_AWA_5250.BRT_TBL[1-1].OEFF_DT_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S010_CALL_BPZSMMRT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MARKET-RATE", BPCSMMRT);
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
