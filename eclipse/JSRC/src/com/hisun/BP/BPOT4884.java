package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4884 {
    String CPN_S_TAMT = "BP-S-MGM-TAMT    ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSTAMT BPCSTAMT = new BPCSTAMT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB4880_AWA_4880 BPB4880_AWA_4880;
    SCCAWAC SCCAWAC;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4884 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4880_AWA_4880>");
        BPB4880_AWA_4880 = (BPB4880_AWA_4880) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        SCCAWAC = new SCCAWAC();
        IBS.init(SCCGWA, SCCAWAC);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.AWAC_AREA_PTR, SCCAWAC);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_DEL_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_DEL_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSTAMT);
        BPCSTAMT.FUNC = 'D';
        BPCSTAMT.TAMT_APP = BPB4880_AWA_4880.AP_MMO;
        BPCSTAMT.TBL_NO = BPB4880_AWA_4880.TBL_NO;
        BPCSTAMT.CHNL = BPB4880_AWA_4880.CHNL;
        BPCSTAMT.BR = BPB4880_AWA_4880.BR;
        CEP.TRC(SCCGWA, BPCSTAMT.TAMT_APP);
        CEP.TRC(SCCGWA, BPCSTAMT.TBL_NO);
        CEP.TRC(SCCGWA, BPCSTAMT.CHNL);
        CEP.TRC(SCCGWA, BPCSTAMT.BR);
        BPCSTAMT.TAMT_STS = 'D';
        S000_CALL_BPZSTAMT();
    }
    public void S000_CALL_BPZSTAMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_TAMT, BPCSTAMT);
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
