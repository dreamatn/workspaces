package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT7930 {
    String CPN_TLR_SIGN_ON = "BP-S-TLR-SIGN-ON    ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSTLSO BPCSTLSO = new BPCSTLSO();
    SCCGWA SCCGWA;
    BPB7930_AWA_7930 BPB7930_AWA_7930;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT7930 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB7930_AWA_7930>");
        BPB7930_AWA_7930 = (BPB7930_AWA_7930) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_TELLER_SIGN_ON();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB7930_AWA_7930.TLR);
        CEP.TRC(SCCGWA, BPB7930_AWA_7930.REG_TYP);
        CEP.TRC(SCCGWA, BPB7930_AWA_7930.PSW);
        CEP.TRC(SCCGWA, BPB7930_AWA_7930.BR);
        if (BPB7930_AWA_7930.TLR.trim().length() == 0 
            && BPB7930_AWA_7930.BR == ' ') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR);
        }
    }
    public void B020_TELLER_SIGN_ON() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSTLSO);
        BPCSTLSO.OPT_QUDAO = 'Q';
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZSTLSO();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCSTLSO.TLR = BPB7930_AWA_7930.TLR;
        BPCSTLSO.NEW_BR = BPB7930_AWA_7930.BR;
        BPCSTLSO.DEV_ID = BPB7930_AWA_7930.DEV_ID;
    }
    public void S000_CALL_BPZSTLSO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_TLR_SIGN_ON;
        SCCCALL.COMMAREA_PTR = BPCSTLSO;
        SCCCALL.ERR_FLDNO = BPB7930_AWA_7930.TLR_NO;
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
