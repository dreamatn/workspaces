package com.hisun.PS;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class PSOT1201 {
    String K_FMT_CD = "PS1201";
    String WS_ERR_MSG = " ";
    char WS_FLG = ' ';
    PSCMSG_ERROR_MSG PSCMSG_ERROR_MSG = new PSCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    PSCBRQRY PSCBRQRY = new PSCBRQRY();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    PSB1201_AWA_1201 PSB1201_AWA_1201;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "PSOT1201 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR = GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR.replaceAll("BODY>", "PSB1201_AWA_1201>");
        PSB1201_AWA_1201 = (PSB1201_AWA_1201) XStreamUtil.xmlToBean(GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        PSCBRQRY.TX_BR = PSB1201_AWA_1201.TX_BR;
        PSCBRQRY.FLG = PSB1201_AWA_1201.FLG;
        CEP.TRC(SCCGWA, PSB1201_AWA_1201.TX_BR);
        S000_CALL_PSZBRQRY();
    }
    public void S000_CALL_PSZBRQRY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "PS-P-BR-QRY-PROC", PSCBRQRY);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
