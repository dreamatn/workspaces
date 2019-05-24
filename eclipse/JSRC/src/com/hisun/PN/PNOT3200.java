package com.hisun.PN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class PNOT3200 {
    String CPN_I_PNZIGHDF = "PN-I-ICBC-DRFT";
    char K_ERROR = 'E';
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    PNCMSG_ERROR_MSG PNCMSG_ERROR_MSG = new PNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    PNCIGHDF PNCIGHDF = new PNCIGHDF();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    PNB3200_AWA_3200 PNB3200_AWA_3200;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "PNOT3200 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "PNB3200_AWA_3200>");
        PNB3200_AWA_3200 = (PNB3200_AWA_3200) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_INPUT_CHK_PROC();
        B200_INF_DFT_PROC();
    }
    public void B100_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        if (PNB3200_AWA_3200.INQ_FLG == ' ' 
            || !(PNB3200_AWA_3200.INQ_FLG == '1' 
            || PNB3200_AWA_3200.INQ_FLG == '2')) {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_INPUT_DATA_ERR, PNB3200_AWA_3200.INQ_FLG_NO);
        }
        if (PNB3200_AWA_3200.STR_DATE > PNB3200_AWA_3200.END_DATE 
            && PNB3200_AWA_3200.INQ_FLG == '2') {
            WS_ERR_MSG = PNCMSG_ERROR_MSG.PN_INPUT_DATA_ERR;
            CEP.ERRC(SCCGWA, WS_ERR_MSG, PNB3200_AWA_3200.STR_DATE_NO);
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
        IBS.init(SCCGWA, PNCIGHDF);
        PNCIGHDF.REC.INQ_FLG = PNB3200_AWA_3200.INQ_FLG;
        PNCIGHDF.REC.STR_DATE = PNB3200_AWA_3200.STR_DATE;
        PNCIGHDF.REC.END_DATE = PNB3200_AWA_3200.END_DATE;
        S000_CALL_PNZIGHDF();
    }
    public void S000_CALL_PNZIGHDF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_I_PNZIGHDF, PNCIGHDF);
        if (PNCIGHDF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, PNCIGHDF.RC);
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
