package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2027 {
    String CPN_S_CDTL_MAINTAIN = "BP-S-CDTL-MAINTAIN  ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPOT2027_WS_CDTL_KEY WS_CDTL_KEY = new BPOT2027_WS_CDTL_KEY();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSCDTL BPCSCDTL = new BPCSCDTL();
    SCCGWA SCCGWA;
    BPB2020_AWA_2020 BPB2020_AWA_2020;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2027 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2020_AWA_2020>");
        BPB2020_AWA_2020 = (BPB2020_AWA_2020) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2020_AWA_2020.CCY);
        CEP.TRC(SCCGWA, BPB2020_AWA_2020.PAR_VAL);
        CEP.TRC(SCCGWA, BPB2020_AWA_2020.M_FLG);
        B010_CHECK_INPUT();
        B020_BROWSE_CDTL_RECORD();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_BROWSE_CDTL_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSCDTL);
        BPCSCDTL.FUNC = 'B';
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZSCDTL();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCSCDTL.CCY = BPB2020_AWA_2020.CCY;
        BPCSCDTL.PAR_VAL = BPB2020_AWA_2020.PAR_VAL;
        BPCSCDTL.M_FLG = BPB2020_AWA_2020.M_FLG;
    }
    public void S000_CALL_BPZSCDTL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_S_CDTL_MAINTAIN;
        SCCCALL.COMMAREA_PTR = BPCSCDTL;
        SCCCALL.ERR_FLDNO = BPB2020_AWA_2020.CCY_NO;
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
