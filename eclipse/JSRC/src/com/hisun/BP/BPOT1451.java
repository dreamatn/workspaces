package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1451 {
    char K_FUNC_BRW = 'B';
    String K_OUTPUT_FMT = "BP288";
    String CPN_S_BRW_ORG_RGL = "BP-S-BRW-ORG-RGL    ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_ERR_INFO = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSORLB BPCSORLB = new BPCSORLB();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB1450_AWA_1450 BPB1450_AWA_1450;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1451 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1450_AWA_1450>");
        BPB1450_AWA_1450 = (BPB1450_AWA_1450) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB1450_AWA_1450.AC_DT);
        CEP.TRC(SCCGWA, BPB1450_AWA_1450.TX_JRN);
        B020_BROWSE_ORGMRG_RECORD();
    }
    public void B020_BROWSE_ORGMRG_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSORLB);
        BPCSORLB.AC_DT = BPB1450_AWA_1450.AC_DT;
        BPCSORLB.TX_JRN = BPB1450_AWA_1450.TX_JRN;
        BPCSORLB.FUNC = '2';
        S000_CALL_BPZSORLB();
    }
    public void S000_CALL_BPZSORLB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BRW_ORG_RGL, BPCSORLB);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_ERR_INFO, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
