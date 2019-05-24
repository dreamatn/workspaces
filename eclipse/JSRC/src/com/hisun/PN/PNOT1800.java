package com.hisun.PN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CM.*;

import java.io.IOException;
import java.sql.SQLException;

public class PNOT1800 {
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "PN180";
    String WS_ERR_MSG = " ";
    PNCMSG_ERROR_MSG PNCMSG_ERROR_MSG = new PNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    PNCUCCLR PNCUCCLR = new PNCUCCLR();
    PNCUDPAY PNCUDPAY = new PNCUDPAY();
    PNCODPAY PNCODPAY = new PNCODPAY();
    CMCSABEN CMCSABEN = new CMCSABEN();
    SCCGWA SCCGWA;
    PNB1800_AWA_1800 PNB1800_AWA_1800;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "PNOT1800 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "PNB1800_AWA_1800>");
        PNB1800_AWA_1800 = (PNB1800_AWA_1800) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        IBS.init(SCCGWA, CMCSABEN);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_INPUT_CHK_PROC();
        B200_INF_NOTE_PROC();
    }
    public void B100_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        if (PNB1800_AWA_1800.BILL_KND.trim().length() == 0) {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_KND_MUST_IPT, PNB1800_AWA_1800.BILL_KND_NO);
        }
        if (PNB1800_AWA_1800.BILL_NO.trim().length() == 0) {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_NO_MUST_IPT, PNB1800_AWA_1800.BILL_NO_NO);
        }
        if (PNB1800_AWA_1800.ISS_DATE == 0) {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_ISS_DT_MUST_IN, PNB1800_AWA_1800.ISS_DATE_NO);
        }
        if (PNB1800_AWA_1800.ENCRY_NO.trim().length() == 0) {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_ENCRY_NOT_IPT, PNB1800_AWA_1800.ENCRY_NO_NO);
        }
        if (PNB1800_AWA_1800.AMT == 0) {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_AWA_AMT_MST_INPUT, PNB1800_AWA_1800.AMT_NO);
        }
        if (PNB1800_AWA_1800.STL_AMT == 0) {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_DPAY_PAY_MONEY_NULL, PNB1800_AWA_1800.STL_AMT_NO);
        }
        if (PNB1800_AWA_1800.CR_AC.trim().length() == 0) {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_LHDAC_NOT_IPT, PNB1800_AWA_1800.CR_AC_NO);
        }
        if (PNB1800_AWA_1800.CR_NM.trim().length() == 0) {
            CEP.ERRC(SCCGWA, PNCMSG_ERROR_MSG.PN_LHDNM_NOT_IPT, PNB1800_AWA_1800.CR_NM_NO);
        }
    }
    public void B200_INF_NOTE_PROC() throws IOException,SQLException,Exception {
        if (PNB1800_AWA_1800.BILL_KND.equalsIgnoreCase("C00001")) {
            CMCSABEN.TXN_AMT = PNB1800_AWA_1800.AMT;
            CMCSABEN.TXN_INF[1-1].AC = PNB1800_AWA_1800.CR_AC;
            S000_CALL_BPZSABEN();
            B210_PROC_PNCUCCLR();
        } else {
            if (PNB1800_AWA_1800.BILL_KND.equalsIgnoreCase("C00005")) {
                CMCSABEN.TXN_AMT = PNB1800_AWA_1800.STL_AMT;
                CMCSABEN.TXN_INF[1-1].AC = PNB1800_AWA_1800.CR_AC;
                S000_CALL_BPZSABEN();
                B220_PROC_PNCUDPAY();
            } else {
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B210_PROC_PNCUCCLR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNCUCCLR);
        PNCUCCLR.KND = PNB1800_AWA_1800.BILL_KND;
        PNCUCCLR.CC_NO = PNB1800_AWA_1800.BILL_NO;
        PNCUCCLR.ENCRY_NO = PNB1800_AWA_1800.ENCRY_NO;
        PNCUCCLR.STL_FLG = 'T';
        PNCUCCLR.CLR_CHNL = '0';
        CEP.TRC(SCCGWA, PNCUCCLR.CLR_CHNL);
        PNCUCCLR.LHD_AC = PNB1800_AWA_1800.CR_AC;
        PNCUCCLR.LHD_NM = PNB1800_AWA_1800.CR_NM;
        PNCUCCLR.ISS_AMT = PNB1800_AWA_1800.AMT;
        PNCUCCLR.ISS_DATE = PNB1800_AWA_1800.ISS_DATE;
        CEP.TRC(SCCGWA, PNCUCCLR.PRDMO_CD);
        CEP.TRC(SCCGWA, PNCUCCLR.EVENT_CD);
        S000_CALL_PNZUCCLR();
    }
    public void B220_PROC_PNCUDPAY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNCUDPAY);
        PNCUDPAY.DRFT_NO = PNB1800_AWA_1800.BILL_NO;
        PNCUDPAY.KND = PNB1800_AWA_1800.BILL_KND;
        PNCUDPAY.ENCRY_NO = PNB1800_AWA_1800.ENCRY_NO;
        PNCUDPAY.CLR_CHNL = '0';
        CEP.TRC(SCCGWA, PNCUDPAY.CLR_CHNL);
        PNCUDPAY.STL_FLG = 'T';
        PNCUDPAY.LHD_AC = PNB1800_AWA_1800.CR_AC;
        PNCUDPAY.LHD_NM = PNB1800_AWA_1800.CR_NM;
        PNCUDPAY.ISS_DATE = PNB1800_AWA_1800.ISS_DATE;
        PNCUDPAY.ISS_AMT = PNB1800_AWA_1800.AMT;
        PNCUDPAY.STL_AMT = PNB1800_AWA_1800.STL_AMT;
        PNCUDPAY.BAL_AMT = PNB1800_AWA_1800.AMT - PNB1800_AWA_1800.STL_AMT;
        CEP.TRC(SCCGWA, PNCUDPAY.PRDMO_CD);
        CEP.TRC(SCCGWA, PNCUDPAY.EVENT_CD);
        S000_CALL_PNZUDPAY();
    }
    public void S000_CALL_PNZUCCLR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "PN-U-CLR-PNT", PNCUCCLR);
    }
    public void S000_CALL_PNZUDPAY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "PN-U-DRAFT-PAY", PNCUDPAY);
    }
    public void S000_CALL_BPZSABEN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CM-S-CMZSABEN", CMCSABEN);
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
