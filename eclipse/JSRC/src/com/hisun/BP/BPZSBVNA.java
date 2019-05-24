package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class BPZSBVNA {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BP167";
    String K_HIS_REMARKS = "TLR-QUERY-BV-NAME ";
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY   ";
    String WS_ERR_MSG = " ";
    int WS_BR = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPCO167 BPCO167 = new BPCO167();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCSBVNA BPCSBVNA;
    public void MP(SCCGWA SCCGWA, BPCSBVNA BPCSBVNA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSBVNA = BPCSBVNA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSBVNA return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHK_INPUT();
        if (pgmRtn) return;
        B020_QUERY_BV_NAME();
        if (pgmRtn) return;
        B060_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B010_CHK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_QUERY_BV_NAME() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFBVQU);
        BPCFBVQU.TX_DATA.KEY.BR = BPCSBVNA.BR;
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.KEY.BR);
        BPCFBVQU.TX_DATA.KEY.CODE = BPCSBVNA.BV_CODE;
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.KEY.CODE);
        WS_BR = BPCFBVQU.TX_DATA.KEY.BR;
        CEP.TRC(SCCGWA, WS_BR);
        S000_CALL_BPZFBVQU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.BV_CNM);
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.BV_ENM);
    }
    public void B060_DATA_OUTPUT() throws IOException,SQLException,Exception {
        R020_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCO167;
