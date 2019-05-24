package com.hisun.PS;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class PSOT1202 {
    String K_FMT_CD = "PS1202";
    String WS_ERR_MSG = " ";
    char WS_FLG = ' ';
    PSOT1202_WS_OUTPUT_DATA WS_OUTPUT_DATA = new PSOT1202_WS_OUTPUT_DATA();
    PSCMSG_ERROR_MSG PSCMSG_ERROR_MSG = new PSCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    PSCEXQRY PSCEXQRY = new PSCEXQRY();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    PSB1202_AWA_1202 PSB1202_AWA_1202;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "PSOT1202 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR = GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR.replaceAll("BODY>", "PSB1202_AWA_1202>");
        PSB1202_AWA_1202 = (PSB1202_AWA_1202) XStreamUtil.xmlToBean(GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_BUSI_LOGIC();
    }
    public void B100_BUSI_LOGIC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PSCEXQRY);
        PSCEXQRY.EXG_AREA_NO = PSB1202_AWA_1202.AREA_NO;
        PSCEXQRY.EXG_CCY = PSB1202_AWA_1202.CCY;
        PSCEXQRY.EXG_NO = PSB1202_AWA_1202.EXG_NO;
        S000_CALL_PSZEXQRY();
    }
    public void S000_CALL_PSZEXQRY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "PS-P-EXNO-QRY-PROC", PSCEXQRY);
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
