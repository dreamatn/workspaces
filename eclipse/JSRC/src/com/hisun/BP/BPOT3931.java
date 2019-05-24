package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3931 {
    String CPN_PL_BOX_MAIN = "BP-S-PL-BOX-MAIN";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCSVHPB BPCSVHPB = new BPCSVHPB();
    SCCGWA SCCGWA;
    BPB3930_AWA_3930 BPB3930_AWA_3930;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT3931 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3930_AWA_3930>");
        BPB3930_AWA_3930 = (BPB3930_AWA_3930) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSVHPB);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_INQUIRE_PLBOX();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB3930_AWA_3930.PLBOX_NO);
        CEP.TRC(SCCGWA, BPB3930_AWA_3930.FUNC);
        CEP.TRC(SCCGWA, BPB3930_AWA_3930.BIND_TYP);
    }
    public void B020_INQUIRE_PLBOX() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSVHPB);
        BPCSVHPB.INFO.FUNC = BPB3930_AWA_3930.FUNC;
        CEP.TRC(SCCGWA, BPB3930_AWA_3930.BR);
        BPCSVHPB.INFO.PLBOX_NO = BPB3930_AWA_3930.PLBOX_NO;
        BPCSVHPB.INFO.BR = BPB3930_AWA_3930.BR;
        BPCSVHPB.INFO.BIND_TYP = BPB3930_AWA_3930.BIND_TYP;
        S000_CALL_BPZSVHPB();
    }
    public void S000_CALL_BPZSVHPB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PL_BOX_MAIN, BPCSVHPB);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG        ", BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC.RC_CODE);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            WS_FLD_NO = BPB3930_AWA_3930.BR_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
