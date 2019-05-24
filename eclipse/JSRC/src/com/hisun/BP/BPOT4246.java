package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4246 {
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCSMACD BPCSMACD = new BPCSMACD();
    SCCGWA SCCGWA;
    BPB4240_AWA_4240 BPB4240_AWA_4240;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4246 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4240_AWA_4240>");
        BPB4240_AWA_4240 = (BPB4240_AWA_4240) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        IBS.init(SCCGWA, BPCSMACD);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B200_MAIN_PROCESS();
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSMACD);
        BPCSMACD.TXN_CD = BPB4240_AWA_4240.TXN_CODE;
        BPCSMACD.FUNC = 'G';
        BPCSMACD.OUTPUT_FLG = 'Y';
        S000_CALL_BPZSMACD();
    }
    public void S000_CALL_BPZSMACD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "BP-MAINT-ANALYSIS-CD";
        SCCCALL.COMMAREA_PTR = BPCSMACD;
        SCCCALL.ERR_FLDNO = BPB4240_AWA_4240.TXN_CODE_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
