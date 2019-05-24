package com.hisun.PN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class PNOT3400 {
    String CPN_I_PNZIGHTH = "PN-I-GK-ICBCDRFT";
    char K_ERROR = 'E';
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    PNCMSG_ERROR_MSG PNCMSG_ERROR_MSG = new PNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    PNCIGHTH PNCIGHTH = new PNCIGHTH();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    PNB3400_AWA_3400 PNB3400_AWA_3400;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "PNOT3400 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "PNB3400_AWA_3400>");
        PNB3400_AWA_3400 = (PNB3400_AWA_3400) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_INPUT_CHK_PROC();
        B200_INF_DFT_PROC();
    }
    public void B100_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        if (PNB3400_AWA_3400.KND != '1') {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_KND_MUST_IPT, PNB3400_AWA_3400.KND_NO);
        }
        if (PNB3400_AWA_3400.STR_DATE > PNB3400_AWA_3400.END_DATE) {
            WS_ERR_MSG = PNCMSG_ERROR_MSG.PN_INPUT_DATA_ERR;
            CEP.ERRC(SCCGWA, WS_ERR_MSG, PNB3400_AWA_3400.STR_DATE_NO);
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
        IBS.init(SCCGWA, PNCIGHTH);
        PNCIGHTH.REC.KND = PNB3400_AWA_3400.KND;
        PNCIGHTH.REC.DRFT_NO = PNB3400_AWA_3400.DRFT_NO;
        PNCIGHTH.REC.STR_DATE = PNB3400_AWA_3400.STR_DATE;
        PNCIGHTH.REC.END_DATE = PNB3400_AWA_3400.END_DATE;
        S000_CALL_PNZIGHTH();
    }
    public void S000_CALL_PNZIGHTH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_I_PNZIGHTH, PNCIGHTH);
        if (PNCIGHTH.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, PNCIGHTH.RC);
            S000_ERR_MSG_PROC();
        }
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
