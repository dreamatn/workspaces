package com.hisun.PN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class PNOT2800 {
    String CPN_U_PNZUDCHG = "PN-U-DCHG-PNT";
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "PN280";
    String WS_ERR_MSG = " ";
    PNCMSG_ERROR_MSG PNCMSG_ERROR_MSG = new PNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    PNCUDCHG PNCUDCHG = new PNCUDCHG();
    PNCODCHG PNCODCHG = new PNCODCHG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    PNB2800_AWA_2800 PNB2800_AWA_2800;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "PNOT2800 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "PNB2800_AWA_2800>");
        PNB2800_AWA_2800 = (PNB2800_AWA_2800) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_IPT_CHK_PROC();
        B200_CHG_PNT_PROC();
    }
    public void B100_IPT_CHK_PROC() throws IOException,SQLException,Exception {
        if (PNB2800_AWA_2800.BILL_KND.trim().length() == 0) {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_KND_MUST_IPT);
        }
        if (PNB2800_AWA_2800.OLD_DFNO.trim().length() == 0) {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_NO_MUST_IPT);
        }
        if (PNB2800_AWA_2800.NEW_DFNO.trim().length() == 0) {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_NO_MUST_IPT);
        }
        if (PNB2800_AWA_2800.REASON.trim().length() == 0) {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_RSN_NOT_IPT);
        }
        if (PNB2800_AWA_2800.ENCRY_NO.trim().length() == 0) {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_ENCRY_NOT_IPT);
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
        IBS.init(SCCGWA, PNCUDCHG);
        PNCUDCHG.KND = PNB2800_AWA_2800.BILL_KND;
        PNCUDCHG.OLD_DFNO = PNB2800_AWA_2800.OLD_DFNO;
        PNCUDCHG.NEW_DFNO = PNB2800_AWA_2800.NEW_DFNO;
        PNCUDCHG.REASON = PNB2800_AWA_2800.REASON;
        PNCUDCHG.ENCRY_NO = PNB2800_AWA_2800.ENCRY_NO;
        CEP.TRC(SCCGWA, PNCUDCHG.OLD_DFNO);
        CEP.TRC(SCCGWA, PNCUDCHG.KND);
        CEP.TRC(SCCGWA, PNCUDCHG.NEW_DFNO);
        CEP.TRC(SCCGWA, PNCUDCHG.REASON);
        S000_CALL_PNZUDCHG();
    }
    public void B220_FMT_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNCODCHG);
        PNCODCHG.ENCRY_NO = PNCUDCHG.ENCRY_NO;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = PNCODCHG;
        SCCFMT.DATA_LEN = 16;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_PNZUDCHG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_PNZUDCHG, PNCUDCHG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
