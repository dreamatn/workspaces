package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT6201 {
    String K_CPN_S_MAINTAIN_CNTY = "BP-S-MAINTAIN-CNTY  ";
    String K_FMT_CD_1 = "BPX01";
    String K_FMT_CD_2 = "BP741";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSCNTY BPCSCNTY = new BPCSCNTY();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB6200_AWA_6200 BPB6200_AWA_6200;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT6201 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB6200_AWA_6200>");
        BPB6200_AWA_6200 = (BPB6200_AWA_6200) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSCNTY);
        BPCSCNTY.FUNC = 'B';
        BPCSCNTY.KEY.CODE = BPB6200_AWA_6200.CNTY_CD;
        CEP.TRC(SCCGWA, BPCSCNTY.FUNC);
        CEP.TRC(SCCGWA, BPB6200_AWA_6200.CNTY_CD);
        CEP.TRC(SCCGWA, BPCSCNTY.KEY.CODE);
        S010_CALL_BPZSCNTY();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S010_CALL_BPZSCNTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CPN_S_MAINTAIN_CNTY, BPCSCNTY);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}