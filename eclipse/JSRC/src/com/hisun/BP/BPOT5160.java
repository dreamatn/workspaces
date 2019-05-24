package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5160 {
    String CPN_MNT_TQP = "BP-MNT-TQP         ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String WS_CCY = " ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPREXRT BPREXRT = new BPREXRT();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCMT BPCMT = new BPCMT();
    BPRPRMT BPRPRMT = new BPRPRMT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB5160_AWA_5160 BPB5160_AWA_5160;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT5160 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5160_AWA_5160>");
        BPB5160_AWA_5160 = (BPB5160_AWA_5160) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_BRW_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_BRW_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCMT);
        BPCMT.DATA.FUNC = 'B';
        BPCMT.DATA.BR = BPB5160_AWA_5160.BR;
        BPCMT.DATA.EXR_TYPE = BPB5160_AWA_5160.EXR_TYPE;
        BPCMT.DATA.CCY_INFO[1-1].FWD_TENOR = BPB5160_AWA_5160.CCY_INFO[1-1].FWD_TENR;
        BPCMT.DATA.CCY_INFO[1-1].CCY = BPB5160_AWA_5160.CCY_INFO[1-1].CCY;
        CEP.TRC(SCCGWA, BPB5160_AWA_5160.BR);
        CEP.TRC(SCCGWA, BPB5160_AWA_5160.CCY_INFO[1-1].CCY);
        S00_CALL_BPZMT();
    }
    public void S00_CALL_BPZMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_MNT_TQP, BPCMT);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
