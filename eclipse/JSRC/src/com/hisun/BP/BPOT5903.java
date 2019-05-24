package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5903 {
    String CPN_S_BPPROD_DEF = "BP-S-MGM-BPPROD";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_CONT_TYPE = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSPROD BPCSPROD = new BPCSPROD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB5900_AWA_5900 BPB5900_AWA_5900;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT5903 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5900_AWA_5900>");
        BPB5900_AWA_5900 = (BPB5900_AWA_5900) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_DEL_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB5900_AWA_5900.PRD_TYPE.trim().length() == 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_PRD_CODE_MUSTINPUT);
        }
    }
    public void B020_DEL_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSPROD);
        BPCSPROD.KEY.PRD_TYPE = BPB5900_AWA_5900.PRD_TYPE;
        BPCSPROD.FUNC = 'D';
        S000_CALL_BPZSPROD();
    }
    public void S000_CALL_BPZSPROD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BPPROD_DEF, BPCSPROD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
