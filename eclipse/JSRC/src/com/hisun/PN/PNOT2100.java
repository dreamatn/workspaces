package com.hisun.PN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class PNOT2100 {
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "PN210";
    String K_OUTPUT_FMT_CANCEL = "PN104";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    PNCMSG_ERROR_MSG PNCMSG_ERROR_MSG = new PNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    PNCUDFT PNCUDFT = new PNCUDFT();
    PNCODFTS PNCODFTS = new PNCODFTS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    PNB2100_AWA_2100 PNB2100_AWA_2100;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "PNOT2100 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "PNB2100_AWA_2100>");
        PNB2100_AWA_2100 = (PNB2100_AWA_2100) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_INPUT_CHK_PROC();
        B200_ISS_NOTE_PROC();
    }
    public void B100_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        if (PNB2100_AWA_2100.BILL_KND.trim().length() == 0) {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_KND_MUST_IPT);
        }
        if (PNB2100_AWA_2100.BILL_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_NO_MUST_IPT);
        }
        if (PNB2100_AWA_2100.IO_FLG == ' ') {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_IOFLG_MUST_IPT);
        }
        if (PNB2100_AWA_2100.TRN_FLG == ' ') {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_TRNFLG_MUST_IPT);
        }
        if (PNB2100_AWA_2100.PAY_TYPE == ' ') {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_PMTH_MUST_IPT);
        }
        if (PNB2100_AWA_2100.C_T_FLG == ' ') {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_CTFLG_NOT_IPT);
        }
        if (PNB2100_AWA_2100.ISS_CCY.trim().length() == 0) {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_CCY_MUST_IPT);
        }
        if (PNB2100_AWA_2100.ISS_AMT == 0) {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_ISSAMT_NOT_IPT);
        }
        if (PNB2100_AWA_2100.APP_NM.trim().length() == 0) {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_APNM_MUST_IPT);
        }
        if (PNB2100_AWA_2100.PAYEE_NM.trim().length() == 0) {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_PAYE_MUST_IPT);
        }
        if (PNB2100_AWA_2100.FEE_FLG == ' ') {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_FFLG_MUST_IPT);
        }
        if (PNB2100_AWA_2100.APB_TYPE == ' ') {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_APTYP_MUST_IPT);
        }
        if (PNB2100_AWA_2100.ENCRY_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_ENCRY_NOT_IPT);
        }
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == K_ERROR 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE != 0) {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_INPUT_DATA_ERR);
        }
    }
    public void B200_ISS_NOTE_PROC() throws IOException,SQLException,Exception {
        B210_PUSH_DATA_FINANCE();
        B300_FMT_OUTPUT_PROC();
    }
    public void B210_PUSH_DATA_FINANCE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNCUDFT);
        PNCUDFT.PROD_CD = PNB2100_AWA_2100.PROD_CD;
        PNCUDFT.KND = PNB2100_AWA_2100.BILL_KND;
        PNCUDFT.DRFT_NO = PNB2100_AWA_2100.BILL_NO;
        PNCUDFT.IO_FLG = PNB2100_AWA_2100.IO_FLG;
        PNCUDFT.TRN_FLG = PNB2100_AWA_2100.TRN_FLG;
        PNCUDFT.PAY_TYPE = PNB2100_AWA_2100.PAY_TYPE;
        PNCUDFT.C_T_FLG = PNB2100_AWA_2100.C_T_FLG;
        PNCUDFT.ISS_CCY = PNB2100_AWA_2100.ISS_CCY;
        PNCUDFT.ISS_AMT = PNB2100_AWA_2100.ISS_AMT;
        PNCUDFT.BV_TYP = PNB2100_AWA_2100.BV_TYP;
        PNCUDFT.ISS_DATE = PNB2100_AWA_2100.ISS_DATE;
        PNCUDFT.APB_TYPE = PNB2100_AWA_2100.APB_TYPE;
        PNCUDFT.APB_NO = PNB2100_AWA_2100.APB_NO;
        PNCUDFT.APB_VLDT = PNB2100_AWA_2100.APB_VLDT;
        PNCUDFT.CREV_NO = PNB2100_AWA_2100.CREV_NO;
        PNCUDFT.APP_AC = PNB2100_AWA_2100.APP_AC;
        PNCUDFT.PSW = PNB2100_AWA_2100.PSW;
        PNCUDFT.APP_NM = PNB2100_AWA_2100.APP_NM;
        PNCUDFT.PAYEE_AC = PNB2100_AWA_2100.PAYEE_AC;
        PNCUDFT.PAYEE_NM = PNB2100_AWA_2100.PAYEE_NM;
        PNCUDFT.PAY_BK = PNB2100_AWA_2100.PAY_BK;
        PNCUDFT.PAY_PSW = PNB2100_AWA_2100.PAY_PSW;
        PNCUDFT.FEE_FLG = PNB2100_AWA_2100.FEE_FLG;
        PNCUDFT.AGT_BR = PNB2100_AWA_2100.AGT_BR;
        PNCUDFT.AGT_NM = PNB2100_AWA_2100.AGT_NM;
        PNCUDFT.USG_RMK = PNB2100_AWA_2100.USG_RMK;
        PNCUDFT.DFT_CINO = PNB2100_AWA_2100.DFT_CINO;
        PNCUDFT.ENCRY_NO = PNB2100_AWA_2100.ENCRY_NO;
        PNCUDFT.TRK_DAT2 = PNB2100_AWA_2100.TRK_DAT2;
        PNCUDFT.TRK_DAT3 = PNB2100_AWA_2100.TRK_DAT3;
        CEP.TRC(SCCGWA, PNCUDFT.TRK_DAT2);
        CEP.TRC(SCCGWA, PNCUDFT.TRK_DAT3);
        CEP.TRC(SCCGWA, PNCUDFT.CREV_NO);
        CEP.TRC(SCCGWA, PNCUDFT.PAY_PSW);
        CEP.TRC(SCCGWA, PNCUDFT.DFT_CINO);
        CEP.TRC(SCCGWA, PNCUDFT.AGT_BR);
        CEP.TRC(SCCGWA, PNB2100_AWA_2100.AGT_BR);
        S000_CALL_PNZUDFT();
    }
    public void B300_FMT_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNCODFTS);
        PNCODFTS.TXN_FEE = PNCUDFT.TXN_FEE;
        PNCODFTS.BOOK_FEE = PNCUDFT.BOOK_FEE;
        PNCODFTS.ENCRY_NO = PNCUDFT.ENCRY_NO;
        CEP.TRC(SCCGWA, PNCODFTS);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = PNCODFTS;
        SCCFMT.DATA_LEN = 50;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_PNZUDFT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "PN-U-PERFORM-FINANCE", PNCUDFT);
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
