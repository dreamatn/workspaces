package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3079 {
    String CPN_S_BV_TO_DESTROY = "BP-S-BV-TO-DESTROY ";
    int K_CNT = 10;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSBVTD BPCSBVTD = new BPCSBVTD();
    SCCGWA SCCGWA;
    BPB3079_AWA_3079 BPB3079_AWA_3079;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT3079 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3079_AWA_3079>");
        BPB3079_AWA_3079 = (BPB3079_AWA_3079) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSBVTD);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B020_DRAW_CASH_PROC();
    }
    public void B020_DRAW_CASH_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBVTD);
        CEP.TRC(SCCGWA, BPB3079_AWA_3079.BV_CODE);
        BPCSBVTD.BV_CODE = BPB3079_AWA_3079.BV_CODE;
        BPCSBVTD.BV_NAME = BPB3079_AWA_3079.BV_NAME;
        BPCSBVTD.HEAD_NO = BPB3079_AWA_3079.HEAD_NO;
        BPCSBVTD.BEG_NO = BPB3079_AWA_3079.BEG_NO;
        BPCSBVTD.END_NO = BPB3079_AWA_3079.END_NO;
        BPCSBVTD.NUM = BPB3079_AWA_3079.NUM;
        CEP.TRC(SCCGWA, BPCSBVTD.BV_CODE);
        S000_CALL_BPZSBVTD();
    }
    public void S000_CALL_BPZSBVTD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_S_BV_TO_DESTROY;
        SCCCALL.COMMAREA_PTR = BPCSBVTD;
        SCCCALL.ERR_FLDNO = BPB3079_AWA_3079.BV_CODE;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
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
