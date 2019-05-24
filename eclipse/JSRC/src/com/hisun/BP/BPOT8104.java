package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT8104 {
    String K_PRDT_INF_MAINT = "BR-BRW-LTHL-HIS";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCSLTHL BPCSLTHL = new BPCSLTHL();
    SCCGWA SCCGWA;
    BPB8100_AWA_8100 BPB8100_AWA_8100;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT8104 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB8100_AWA_8100>");
        BPB8100_AWA_8100 = (BPB8100_AWA_8100) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        IBS.init(SCCGWA, BPCSLTHL);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B200_MAIN_PROCESS();
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSLTHL);
        BPCSLTHL.CLEAR_HOLD_TYPE = BPB8100_AWA_8100.CL_TYP;
        BPCSLTHL.CCY = BPB8100_AWA_8100.CCY;
        BPCSLTHL.INFO.FUNC = 'Q';
        BPCSLTHL.OUTPUT_FLG = 'Y';
        S000_CALL_BPZSLTHL();
    }
    public void S000_CALL_BPZSLTHL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_PRDT_INF_MAINT, BPCSLTHL);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
