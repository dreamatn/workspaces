package com.hisun.PN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class PNOT1700 {
    String CPN_U_PNZUCHGE = "PN-U-CHG-PNT";
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "PN170";
    String WS_ERR_MSG = " ";
    PNCMSG_ERROR_MSG PNCMSG_ERROR_MSG = new PNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    PNCUCHGE PNCUCHGE = new PNCUCHGE();
    PNCOCHGE PNCOCHGE = new PNCOCHGE();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    PNB1700_AWA_1700 PNB1700_AWA_1700;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "PNOT1700 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "PNB1700_AWA_1700>");
        PNB1700_AWA_1700 = (PNB1700_AWA_1700) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_IPT_CHK_PROC();
        B200_CHG_PNT_PROC();
    }
    public void B100_IPT_CHK_PROC() throws IOException,SQLException,Exception {
        if (PNB1700_AWA_1700.BILL_KND.trim().length() == 0) {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_KND_MUST_IPT);
        }
        if (PNB1700_AWA_1700.OLD_CCNO.trim().length() == 0) {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_NO_MUST_IPT);
        }
        if (PNB1700_AWA_1700.NEW_CCNO.trim().length() == 0) {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_NO_MUST_IPT);
        }
        if (PNB1700_AWA_1700.REASON.trim().length() == 0) {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_RSN_NOT_IPT);
        }
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == K_ERROR 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE != 0) {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_INPUT_DATA_ERR);
        }
    }
    public void B200_CHG_PNT_PROC() throws IOException,SQLException,Exception {
        B210_CHG_PNT_PROC();
        B220_FMT_OUTPUT_PROC();
    }
    public void B210_CHG_PNT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNCUCHGE);
        PNCUCHGE.KND = PNB1700_AWA_1700.BILL_KND;
        PNCUCHGE.OLD_CCNO = PNB1700_AWA_1700.OLD_CCNO;
        PNCUCHGE.NEW_CCNO = PNB1700_AWA_1700.NEW_CCNO;
        PNCUCHGE.REASON = PNB1700_AWA_1700.REASON;
        PNCUCHGE.ENCRY_NO = PNB1700_AWA_1700.ENCRY_NO;
        CEP.TRC(SCCGWA, PNB1700_AWA_1700.ENCRY_NO);
        S000_CALL_PNZUCHGE();
    }
    public void B220_FMT_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNCOCHGE);
        PNCOCHGE.ENCRY_NO = PNCUCHGE.ENCRY_NO;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = PNCOCHGE;
        SCCFMT.DATA_LEN = 16;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_PNZUCHGE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_PNZUCHGE, PNCUCHGE);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
