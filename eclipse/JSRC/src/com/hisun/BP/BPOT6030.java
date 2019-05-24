package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT6030 {
    String K_S_MAINT_PRDT_PARM = "BP-S-MAINT-PRDT-PARM";
    String K_CMP_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCSMPPR BPCSMPPR = new BPCSMPPR();
    SCCGWA SCCGWA;
    BPC6030 BPC6030;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT6030 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPC6030 = new BPC6030();
        IBS.init(SCCGWA, BPC6030);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, BPC6030);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B200_MAIN_PROCESS();
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSMPPR);
        BPCSMPPR.PARM_CODE = BPC6030.PARM_COD;
        BPCSMPPR.FUNC = 'B';
        BPCSMPPR.OUTPUT_FLG = 'Y';
        S000_CALL_BPZSMPPR();
    }
    public void S000_CALL_BPZSMPPR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_S_MAINT_PRDT_PARM, BPCSMPPR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
