package com.hisun.PN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class PNOT2510 {
    String CPN_U_PNZSMDFT = "PN-S-INF-DFT";
    char K_ERROR = 'E';
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    PNCMSG_ERROR_MSG PNCMSG_ERROR_MSG = new PNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    PNCSMDFT PNCSMDFT = new PNCSMDFT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    PNB2500_AWA_2500 PNB2500_AWA_2500;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "PNOT2510 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "PNB2500_AWA_2500>");
        PNB2500_AWA_2500 = (PNB2500_AWA_2500) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_INPUT_CHK_PROC();
        B200_INF_DFT_PROC();
    }
    public void B100_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, PNB2500_AWA_2500.BILL_KND);
        if (PNB2500_AWA_2500.BILL_KND.trim().length() == 0) {
            WS_ERR_MSG = PNCMSG_ERROR_MSG.PN_KND_MUST_IPT;
            CEP.ERRC(SCCGWA, WS_ERR_MSG, PNB2500_AWA_2500.BILL_KND_NO);
        }
        CEP.TRC(SCCGWA, PNB2500_AWA_2500.BILL_NO);
        if (PNB2500_AWA_2500.BILL_NO.trim().length() == 0) {
            WS_ERR_MSG = PNCMSG_ERROR_MSG.PN_NO_MUST_IPT;
            CEP.ERRC(SCCGWA, WS_ERR_MSG, PNB2500_AWA_2500.BILL_NO);
        }
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == K_ERROR 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE != 0) {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_INPUT_DATA_ERR);
        }
    }
    public void B200_INF_DFT_PROC() throws IOException,SQLException,Exception {
        B220_INF_DRAFTS_PROC();
    }
    public void B220_INF_DRAFTS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNCSMDFT);
        PNCSMDFT.FUNC = 'I';
        PNCSMDFT.DATA.KEY.KND = PNB2500_AWA_2500.BILL_KND;
        PNCSMDFT.DATA.KEY.DRFT_NO = PNB2500_AWA_2500.BILL_NO;
        S000_CALL_PNZSMDFT();
    }
    public void S000_CALL_PNZSMDFT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_PNZSMDFT, PNCSMDFT);
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
