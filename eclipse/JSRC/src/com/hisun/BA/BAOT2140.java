package com.hisun.BA;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BAOT2140 {
    String K_OUTPUT_FMT = "BAD01";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BAOT2140_WS_BILL_TEMP WS_BILL_TEMP = new BAOT2140_WS_BILL_TEMP();
    BAOT2140_WS_OUTPUT_FMT WS_OUTPUT_FMT = new BAOT2140_WS_OUTPUT_FMT();
    BACMSG_ERROR_MSG BACMSG_ERROR_MSG = new BACMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BACFMST1 BACFMST1 = new BACFMST1();
    BARMST1 BARMST1 = new BARMST1();
    BACUSPAY BACUSPAY = new BACUSPAY();
    SCCGWA SCCGWA;
    BAB2140_AWA_2140 BAB2140_AWA_2140;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGAPV SCCGAPV;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BAOT2140 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BAB2140_AWA_2140>");
        BAB2140_AWA_2140 = (BAB2140_AWA_2140) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) SCCGSCA_SC_AREA.APVL_AREA_PTR;
        BACUSPAY.RC.RC_MMO = "BA";
        BACUSPAY.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_SUBMIT_REPAY();
        B030_FMT_OUTPUT();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (!(BAB2140_AWA_2140.ACN_TYP == '1' 
            || BAB2140_AWA_2140.ACN_TYP == '2' 
            || BAB2140_AWA_2140.ACN_TYP == '3')) {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_ACN_TYP_INVALID;
            S000_ERR_MSG_PROC();
        }
        if (BAB2140_AWA_2140.ACN_TYP == '2' 
            && BAB2140_AWA_2140.SUSP_NO.trim().length() == 0) {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_SUSP_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_SUBMIT_REPAY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BACUSPAY);
        IBS.init(SCCGWA, WS_BILL_TEMP);
        BACUSPAY.BILL_NO = BAB2140_AWA_2140.BILL_NO;
        BACUSPAY.ACN_TYP = BAB2140_AWA_2140.ACN_TYP;
        BACUSPAY.DBT_AC = BAB2140_AWA_2140.DBT_AC;
        BACUSPAY.SUSP_NO = BAB2140_AWA_2140.SUSP_NO;
        BACUSPAY.TRX_AMT = BAB2140_AWA_2140.TRX_AMT;
        S000_CALL_BAZUSPAY();
    }
    public void B030_FMT_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        WS_OUTPUT_FMT.WS_F_TRXAMT = BACUSPAY.TRX_AMT;
        SCCFMT.DATA_PTR = WS_OUTPUT_FMT;
        SCCFMT.DATA_LEN = 16;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BAZUSPAY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BA-U-SUBM-RPAY", BACUSPAY);
        if (BACUSPAY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BACUSPAY.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BAZFMST1() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BA-R-BAZFMST1", BACFMST1);
        if (BACFMST1.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BACFMST1.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
