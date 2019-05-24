package com.hisun.BA;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BAOT2060 {
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BAV01";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_ERR_FLG = ' ';
    BAOT2060_WS_OUT_DATA WS_OUT_DATA = new BAOT2060_WS_OUT_DATA();
    BACMSG_ERROR_MSG BACMSG_ERROR_MSG = new BACMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BARLOSS BARLOSS = new BARLOSS();
    BACUBINF BACUBINF = new BACUBINF();
    BACULOSS BACULOSS = new BACULOSS();
    BACFLOSS BACFLOSS = new BACFLOSS();
    SCCGWA SCCGWA;
    BAB2060_AWA_2060 BAB2060_AWA_2060;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    SCCGMSG SCCGMSG;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BAOT2060 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BAB2060_AWA_2060>");
        BAB2060_AWA_2060 = (BAB2060_AWA_2060) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_TRANCHE_MAIN_PROC();
        if (pgmRtn) return;
        B030_TRAN_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BAB2060_AWA_2060.ID_NO);
        CEP.TRC(SCCGWA, BAB2060_AWA_2060.JG_NO);
        CEP.TRC(SCCGWA, BAB2060_AWA_2060.JG_RMK);
        if (BAB2060_AWA_2060.ID_NO.trim().length() == 0) {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_ID_NO_M_INPUT;
            WS_FLD_NO = BAB2060_AWA_2060.ID_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (BAB2060_AWA_2060.JG_RMK.trim().length() == 0) {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_JG_RMK_M_INPUT;
            WS_FLD_NO = BAB2060_AWA_2060.JG_RMK_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (WS_ERR_FLG == 'Y') {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_INPUT_ERR;
            S000_ERR_MSG_PROC_LAST();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_TRANCHE_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BACULOSS);
        BACULOSS.DATA.TRAN_FLG = '1';
        BACULOSS.DATA.ID_NO = BAB2060_AWA_2060.ID_NO;
        BACULOSS.DATA.JG_NO = BAB2060_AWA_2060.JG_NO;
        BACULOSS.DATA.JG_RMK = BAB2060_AWA_2060.JG_RMK;
        S000_CALL_BAZULOSS();
        if (pgmRtn) return;
    }
    public void B030_TRAN_OUTPUT_PROC() throws IOException,SQLException,Exception {
        B031_GET_OUT_INFO();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        WS_OUT_DATA.WS_OUT_GS_ST_DT = SCCGWA.COMM_AREA.AC_DATE;
        WS_OUT_DATA.WS_OUT_REMK = BARLOSS.REMK;
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUT_DATA;
        SCCFMT.DATA_LEN = 408;
        IBS.FMT(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_OUT_GS_ST_DT);
    }
    public void B031_GET_OUT_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BARLOSS);
        IBS.init(SCCGWA, BACFLOSS);
        BACFLOSS.FUNC = 'O';
        BARLOSS.BILL_NO = BAB2060_AWA_2060.ID_NO;
        S000_CALL_BAZFLOSS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BAZUBINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BA-U-INQ-BILL-INF", BACUBINF);
        if (BACUBINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BACUBINF.RC);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BAZULOSS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BA-U-LOSS-ULOS", BACULOSS);
        if (BACULOSS.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = "" + BACULOSS.RC.RC_RTNCODE;
            JIBS_tmp_int = WS_ERR_MSG.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_ERR_MSG = "0" + WS_ERR_MSG;
            SCCMSG.INFO = "CALL-BAZSPLOS ERROR";
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BAZFLOSS() throws IOException,SQLException,Exception {
        BACFLOSS.REC_PTR = BARLOSS;
        BACFLOSS.REC_LEN = 898;
        IBS.CALLCPN(SCCGWA, "BA-R-BAZFLOSS", BACFLOSS);
        if (BACFLOSS.RETURN_INFO != 'F') {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_LOSS_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
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
