package com.hisun.PN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class PNOT2400 {
    char K_ERROR = 'E';
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_TABLE_FLG = ' ';
    PNCMSG_ERROR_MSG PNCMSG_ERROR_MSG = new PNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    PNCUDPAY PNCUDPAY = new PNCUDPAY();
    PNCODPAY PNCODPAY = new PNCODPAY();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    PNB2400_AWA_2400 PNB2400_AWA_2400;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "PNOT2400 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "PNB2400_AWA_2400>");
        PNB2400_AWA_2400 = (PNB2400_AWA_2400) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_INPUT_CHK_PROC();
        B200_DRAFT_PAY();
    }
    public void B100_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        if (PNB2400_AWA_2400.STL_AMT == 0) {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_DPAY_PAY_MONEY_NULL, PNB2400_AWA_2400.STL_AMT_NO);
        }
        if (PNB2400_AWA_2400.BILL_NO.trim().length() == 0) {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_DRFT_NO_NULL, PNB2400_AWA_2400.BILL_NO_NO);
        }
        if (PNB2400_AWA_2400.BILL_KND.trim().length() == 0) {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_KND_MUST_IPT, PNB2400_AWA_2400.BILL_KND_NO);
        }
        if (PNB2400_AWA_2400.ENCRY_NO.trim().length() == 0) {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_ENCRY_NOT_IPT, PNB2400_AWA_2400.ENCRY_NO_NO);
        }
        if (PNB2400_AWA_2400.STL_FLG == ' ') {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_STLFLG_NOT_IPT, PNB2400_AWA_2400.STL_FLG_NO);
        }
        if (PNB2400_AWA_2400.CLR_CHNL == ' ') {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_CLR_CHNL_IPT, PNB2400_AWA_2400.CLR_CHNL_NO);
        }
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == K_ERROR 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE != 0) {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_INPUT_DATA_ERR);
        }
    }
    public void B200_DRAFT_PAY() throws IOException,SQLException,Exception {
        B210_PUSH_DATA_FINANCE();
    }
    public void B210_PUSH_DATA_FINANCE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNCUDPAY);
        PNCUDPAY.DRFT_NO = PNB2400_AWA_2400.BILL_NO;
        PNCUDPAY.KND = PNB2400_AWA_2400.BILL_KND;
        PNCUDPAY.ENCRY_NO = PNB2400_AWA_2400.ENCRY_NO;
        PNCUDPAY.ISS_DATE = PNB2400_AWA_2400.ISS_DATE;
        PNCUDPAY.CLR_CHNL = PNB2400_AWA_2400.CLR_CHNL;
        PNCUDPAY.STL_FLG = PNB2400_AWA_2400.STL_FLG;
        PNCUDPAY.LHD_AC = PNB2400_AWA_2400.LHD_AC;
        PNCUDPAY.LHD_NM = PNB2400_AWA_2400.LHD_NM;
        PNCUDPAY.CLR_BR = (int) PNB2400_AWA_2400.CLR_BK;
        PNCUDPAY.ISS_AMT = PNB2400_AWA_2400.ISS_AMT;
        PNCUDPAY.BAL_AMT = PNB2400_AWA_2400.BAL_AMT;
        CEP.TRC(SCCGWA, PNB2400_AWA_2400.BAL_AMT);
        CEP.TRC(SCCGWA, PNCUDPAY.BAL_AMT);
        PNCUDPAY.STL_AMT = PNB2400_AWA_2400.STL_AMT;
        PNCUDPAY.PRDMO_CD = PNB2400_AWA_2400.PRDMO_CD;
        PNCUDPAY.PROD_CD = PNB2400_AWA_2400.PROD_CD;
        PNCUDPAY.EVENT_CD = PNB2400_AWA_2400.EVENT_CD;
        PNCUDPAY.PRDGL_CD = PNB2400_AWA_2400.PRDGL_CD;
        PNCUDPAY.ACCT_BR = PNB2400_AWA_2400.ACCT_BR;
        PNCUDPAY.DFT_CINO = PNB2400_AWA_2400.DFT_CINO;
        CEP.TRC(SCCGWA, PNCUDPAY.PRDMO_CD);
        CEP.TRC(SCCGWA, PNCUDPAY.EVENT_CD);
        S000_CALL_PNZUDPAY();
    }
    public void S000_CALL_PNZUDPAY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "PN-U-DRAFT-PAY", PNCUDPAY);
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
