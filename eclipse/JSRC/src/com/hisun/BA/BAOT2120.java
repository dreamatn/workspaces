package com.hisun.BA;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BAOT2120 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BAOT2120_WS_BILL_TEMP WS_BILL_TEMP = new BAOT2120_WS_BILL_TEMP();
    long WS_OUT_JRNNO = 0;
    BACMSG_ERROR_MSG BACMSG_ERROR_MSG = new BACMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BACFMST1 BACFMST1 = new BACFMST1();
    BACUCSEQ BACUCSEQ = new BACUCSEQ();
    SCCGWA SCCGWA;
    BAB2120_AWA_2120 BAB2120_AWA_2120;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGAPV SCCGAPV;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BAOT2120 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BAB2120_AWA_2120>");
        BAB2120_AWA_2120 = (BAB2120_AWA_2120) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) SCCGSCA_SC_AREA.APVL_AREA_PTR;
        BACFMST1.RC.RC_MMO = "BA";
        BACFMST1.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_PROC_MAIN();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BAB2120_AWA_2120.BILL_NO);
        if (BAB2120_AWA_2120.BILL_NO.trim().length() == 0) {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_INPUT_BILL_NO;
            WS_FLD_NO = BAB2120_AWA_2120.BILL_NO_NO;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BAB2120_AWA_2120.NEW_SEQ);
        if (BAB2120_AWA_2120.NEW_SEQ == ' ') {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_INPUT_NEW_SEQ;
            WS_FLD_NO = BAB2120_AWA_2120.NEW_SEQ_NO;
            S000_ERR_MSG_PROC();
        }
        if (BAB2120_AWA_2120.NEW_SEQ != '0' 
            && BAB2120_AWA_2120.NEW_SEQ != '1') {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_NEW_SEQ_ERR;
            WS_FLD_NO = BAB2120_AWA_2120.NEW_SEQ_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_PROC_MAIN() throws IOException,SQLException,Exception {
        B021_MAINTAIN_DEDUCT_SEQUENCE();
    }
    public void B021_MAINTAIN_DEDUCT_SEQUENCE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BACUCSEQ);
        BACUCSEQ.BILL_NO = BAB2120_AWA_2120.BILL_NO;
        BACUCSEQ.DBT_SEQ = BAB2120_AWA_2120.NEW_SEQ;
        S000_CALL_BAZUCSEQ();
    }
    public void S000_CALL_BAZUCSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BA-U-CHG-DBTSEQ", BACUCSEQ);
        if (BACUCSEQ.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "*CALL S000-CALL-BAZUCSEQ RETURN FAILED*");
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BACUCSEQ.RC);
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
