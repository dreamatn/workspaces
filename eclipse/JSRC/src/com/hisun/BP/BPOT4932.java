package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4932 {
    String CPN_TLR_SIGN_ON = "BP-S-TLR-SIGN-ON    ";
    String CPN_TLR_SIGN_OFF = "BP-S-TLR-SIGN-OFF   ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSTLSF BPCSTLSF = new BPCSTLSF();
    SCCGWA SCCGWA;
    BPB4932_AWA_4932 BPB4932_AWA_4932;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4932 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4932_AWA_4932>");
        BPB4932_AWA_4932 = (BPB4932_AWA_4932) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_TELLER_SIGN_OFF();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB4932_AWA_4932.TLR);
        CEP.TRC(SCCGWA, BPB4932_AWA_4932.PSW);
        CEP.TRC(SCCGWA, BPB4932_AWA_4932.DEV_ID);
        CEP.TRC(SCCGWA, BPB4932_AWA_4932.STLR_FLG);
        if (BPB4932_AWA_4932.TLR.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            WS_FLD_NO = BPB4932_AWA_4932.TLR_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_TELLER_SIGN_OFF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSTLSF);
        BPCSTLSF.OPT = '2';
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZSTLSF();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCSTLSF.TLR = BPB4932_AWA_4932.TLR;
        BPCSTLSF.PSW = BPB4932_AWA_4932.PSW;
        BPCSTLSF.STLR_FLG = BPB4932_AWA_4932.STLR_FLG;
    }
    public void S000_CALL_BPZSTLSF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_TLR_SIGN_OFF;
        SCCCALL.COMMAREA_PTR = BPCSTLSF;
        SCCCALL.ERR_FLDNO = BPB4932_AWA_4932.TLR_NO;
        IBS.CALL(SCCGWA, SCCCALL);
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