package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5246 {
    String K_SPEC_TENOR = "301";
    int K_BATCH_CNT = 40;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSMMRT BPCSMMRT = new BPCSMMRT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB5240_AWA_5240 BPB5240_AWA_5240;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT5246 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5240_AWA_5240>");
        BPB5240_AWA_5240 = (BPB5240_AWA_5240) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSMMRT);
        BPCSMMRT.FUNC = 'H';
        BPCSMMRT.BTH_OPT = 'B';
        CEP.TRC(SCCGWA, BPB5240_AWA_5240.RATE_TYP);
        BPCSMMRT.BASE_TYP = BPB5240_AWA_5240.RATE_TYP;
        for (WS_I = 1; WS_I <= K_BATCH_CNT; WS_I += 1) {
            CEP.TRC(SCCGWA, BPB5240_AWA_5240.BRT_TBL[WS_I-1].CCY);
            CEP.TRC(SCCGWA, BPB5240_AWA_5240.BRT_TBL[WS_I-1].RATEID);
            CEP.TRC(SCCGWA, BPB5240_AWA_5240.BRT_TBL[WS_I-1].OEFF_DT);
            CEP.TRC(SCCGWA, BPB5240_AWA_5240.BRT_TBL[WS_I-1].ORATE);
            CEP.TRC(SCCGWA, BPB5240_AWA_5240.BRT_TBL[WS_I-1].NEFF_DT);
            CEP.TRC(SCCGWA, BPB5240_AWA_5240.BRT_TBL[WS_I-1].NRATE);
            BPCSMMRT.UPD_DATA[WS_I-1].CCY_TBL = BPB5240_AWA_5240.BRT_TBL[WS_I-1].CCY;
            BPCSMMRT.UPD_DATA[WS_I-1].TENOR = K_SPEC_TENOR;
            BPCSMMRT.UPD_DATA[WS_I-1].RATE_ID = BPB5240_AWA_5240.BRT_TBL[WS_I-1].RATEID;
            BPCSMMRT.UPD_DATA[WS_I-1].OEFF_DT = BPB5240_AWA_5240.BRT_TBL[WS_I-1].OEFF_DT;
            BPCSMMRT.UPD_DATA[WS_I-1].ORATE = BPB5240_AWA_5240.BRT_TBL[WS_I-1].ORATE;
            BPCSMMRT.UPD_DATA[WS_I-1].NEFF_DT = BPB5240_AWA_5240.BRT_TBL[WS_I-1].NEFF_DT;
            BPCSMMRT.UPD_DATA[WS_I-1].NRATE = BPB5240_AWA_5240.BRT_TBL[WS_I-1].NRATE;
        }
        S010_CALL_BPZSMMRT();
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
