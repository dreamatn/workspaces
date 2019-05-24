package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5230 {
    String K_MMO = "BP";
    String K_OUTPUT_FMT = "BPX01";
    String K_CALL_NAME = "BP-U-U-UOD";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOUUOD BPCOUUOD = new BPCOUUOD();
    SCCGWA SCCGWA;
    BPB5230_AWA_5230 BPB5230_AWA_5230;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT5230 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5230_AWA_5230>");
        BPB5230_AWA_5230 = (BPB5230_AWA_5230) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCOUUOD);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_INQUIRE_ATH_TLR_INFO();
    }
    public void B010_INQUIRE_ATH_TLR_INFO() throws IOException,SQLException,Exception {
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZUUOD();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        if (BPB5230_AWA_5230.ATH_TLR.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ATH_TLR_SPACE;
            S000_ERR_MSG_PROC();
        } else {
            BPCOUUOD.VAL.ATH_TLR = BPB5230_AWA_5230.ATH_TLR;
        }
        CEP.TRC(SCCGWA, BPB5230_AWA_5230.ATH_TLR);
    }
    public void S000_CALL_BPZUUOD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CALL_NAME, BPCOUUOD);
        if (BPCOUUOD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOUUOD.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
