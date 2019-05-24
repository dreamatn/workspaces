package com.hisun.BA;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BAOT2071 {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BAT02";
    String WS_CNTR_NO = " ";
    short WS_ACCT_CNT = 0;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_ERR_FLG = ' ';
    BAOT2071_WS_OUT_DATA WS_OUT_DATA = new BAOT2071_WS_OUT_DATA();
    BACMSG_ERROR_MSG BACMSG_ERROR_MSG = new BACMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BARMST1 BARMST1 = new BARMST1();
    BARCLCT BARCLCT = new BARCLCT();
    BACFCLCT BACFCLCT = new BACFCLCT();
    BACUBINF BACUBINF = new BACUBINF();
    BACURCRE BACURCRE = new BACURCRE();
    SCCGWA SCCGWA;
    BAB2071_AWA_2071 BAB2071_AWA_2071;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGAPV SCCGAPV;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BAOT2071 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BAB2071_AWA_2071>");
        BAB2071_AWA_2071 = (BAB2071_AWA_2071) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) SCCGSCA_SC_AREA.APVL_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BAB2071_AWA_2071.ID_NO);
        CEP.TRC(SCCGWA, BAB2071_AWA_2071.RTN_RSN);
        CEP.TRC(SCCGWA, BAB2071_AWA_2071.REMK);
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_MAIN_PROCESS();
        if (pgmRtn) return;
        B300_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BACURCRE);
        BACURCRE.DATA.ID_NO = BAB2071_AWA_2071.ID_NO;
        BACURCRE.DATA.RTN_RSN = BAB2071_AWA_2071.RTN_RSN;
        BACURCRE.DATA.REMK = BAB2071_AWA_2071.REMK;
        S000_CALL_BAZURCRE();
        if (pgmRtn) return;
    }
    public void B300_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        WS_OUT_DATA.WS_CLCT_STS = BACURCRE.DATA.CLCT_STS;
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUT_DATA;
        SCCFMT.DATA_LEN = 1;
        IBS.FMT(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_CLCT_STS);
    }
    public void S000_CALL_BAZUBINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BA-U-INQ-BILL-INF", BACUBINF);
        if (BACUBINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BACUBINF.RC);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BAZFCLCT() throws IOException,SQLException,Exception {
        BACFCLCT.REC_PTR = BARCLCT;
        BACFCLCT.REC_LEN = 1021;
        IBS.CALLCPN(SCCGWA, "BA-R-BAZFCLCT", BACFCLCT);
        if (BACFCLCT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BACFCLCT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BAZURCRE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BA-U-CLCT-RCRE", BACURCRE);
        if (BACURCRE.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BACURCRE.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
        WS_ERR_FLG = 'Y';
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
