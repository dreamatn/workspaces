package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5244 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSMBRT BPCSMBRT = new BPCSMBRT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB5240_AWA_5240 BPB5240_AWA_5240;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT5244 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5240_AWA_5240>");
        BPB5240_AWA_5240 = (BPB5240_AWA_5240) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        IBS.init(SCCGWA, BPCSMBRT);
        BPCSMBRT.FUNC = 'D';
        BPCSMBRT.BASE_TYP = BPB5240_AWA_5240.RATE_TYP;
        BPCSMBRT.UPD_DATA[1-1].CCY = BPB5240_AWA_5240.BRT_TBL[1-1].CCY;
        BPCSMBRT.UPD_DATA[1-1].OEFF_DT = BPB5240_AWA_5240.BRT_TBL[1-1].OEFF_DT;
        S010_CALL_BPZSMBRT();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB5240_AWA_5240.BRT_TBL[1-1].OEFF_DT <= SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CANNOT_DEL_EFF_RT;
            WS_FLD_NO = BPB5240_AWA_5240.BRT_TBL[1-1].OEFF_DT_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S010_CALL_BPZSMBRT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-BASE-RATE", BPCSMBRT);
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
