package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3031 {
    String CPN_S_BV_NAME_QUERY = "BP-S-BV-NAME-QUERY ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSBVNA BPCSBVNA = new BPCSBVNA();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB3020_AWA_3020 BPB3020_AWA_3020;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT3031 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3020_AWA_3020>");
        BPB3020_AWA_3020 = (BPB3020_AWA_3020) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        CEP.TRC(SCCGWA, BPB3020_AWA_3020);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_QUERY_BV_NAME();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB3020_AWA_3020.BV_DATA[1-1].BV_CODE.equalsIgnoreCase("0")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BVCD_MUST_INPUT;
            WS_FLD_NO = BPB3020_AWA_3020.BV_DATA[1-1].BV_CODE_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_QUERY_BV_NAME() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBVNA);
        BPCSBVNA.BV_CODE = BPB3020_AWA_3020.BV_DATA[1-1].BV_CODE;
        BPCSBVNA.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, BPCSBVNA.BR);
        CEP.TRC(SCCGWA, BPCSBVNA.BV_CODE);
        S000_CALL_BPZSBVNA();
        CEP.TRC(SCCGWA, BPCSBVNA.BV_NAME);
        CEP.TRC(SCCGWA, BPCSBVNA.BV_ENAM);
    }
    public void S000_CALL_BPZSBVNA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BV_NAME_QUERY, BPCSBVNA);
        if (BPCSBVNA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSBVNA.RC);
            WS_FLD_NO = BPB3020_AWA_3020.MOV_DT_NO;
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
