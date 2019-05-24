package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT5681 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    CICMALH CICMALH = new CICMALH();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB5680_AWA_5680 CIB5680_AWA_5680;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT5681 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB5680_AWA_5680>");
        CIB5680_AWA_5680 = (CIB5680_AWA_5680) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CICMALH.DATA.ALT_NO = CIB5680_AWA_5680.ALT_NO;
        CICMALH.DATA.ENTY_TYP = CIB5680_AWA_5680.ENTY_TYP;
        CICMALH.DATA.ENTY_NO = CIB5680_AWA_5680.ENTY_NO;
        CICMALH.DATA.MSG_CHNL = SCCGWA.COMM_AREA.CHNL;
        CICMALH.DATA.STOP = CIB5680_AWA_5680.STOP;
        S000_LINK_CIZMALH();
    }
    public void S000_LINK_CIZMALH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-ALH", CICMALH);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
