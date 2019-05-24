package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1437 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCBRCHK BPCBRCHK = new BPCBRCHK();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPB1437_AWA_1437 BPB1437_AWA_1437;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1437 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1437_AWA_1437>");
        BPB1437_AWA_1437 = (BPB1437_AWA_1437) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_IPT_CHK_PROC();
        B200_PNT_PROC();
    }
    public void B100_IPT_CHK_PROC() throws IOException,SQLException,Exception {
        if (BPB1437_AWA_1437.NEW_BR == 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_VBBR_INPUT);
        }
        if (BPB1437_AWA_1437.OLD_BR == 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_VBBR_INPUT);
        }
    }
    public void B200_PNT_PROC() throws IOException,SQLException,Exception {
        B210_PNT_PROC();
    }
    public void B210_PNT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCBRCHK);
        BPCBRCHK.OLD_BR = BPB1437_AWA_1437.OLD_BR;
        BPCBRCHK.NEW_BR = BPB1437_AWA_1437.NEW_BR;
        S000_CALL_BPZBRCHK();
    }
    public void S000_CALL_BPZBRCHK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-BPZBRCHK", BPCBRCHK);
        if (BPCBRCHK.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCBRCHK.RC);
            S000_ERR_MSG_PROC();
        }
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
