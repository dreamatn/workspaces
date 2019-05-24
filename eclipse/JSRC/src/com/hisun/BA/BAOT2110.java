package com.hisun.BA;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BAOT2110 {
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BAW01";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BAOT2110_WS_FMT_TEMP WS_FMT_TEMP = new BAOT2110_WS_FMT_TEMP();
    BAOT2110_WS_HMPW_MAC_DA WS_HMPW_MAC_DA = new BAOT2110_WS_HMPW_MAC_DA();
    BACMSG_ERROR_MSG BACMSG_ERROR_MSG = new BACMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BACFMST1 BACFMST1 = new BACFMST1();
    BARMST1 BARMST1 = new BARMST1();
    SCCHMPW SCCHMPW = new SCCHMPW();
    SCCGWA SCCGWA;
    BAB2110_AWA_2110 BAB2110_AWA_2110;
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
        CEP.TRC(SCCGWA, "BAOT2110 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BAB2110_AWA_2110>");
        BAB2110_AWA_2110 = (BAB2110_AWA_2110) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) SCCGSCA_SC_AREA.APVL_AREA_PTR;
        BACFMST1.RC.RC_MMO = "BA";
        BACFMST1.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_PROC_MAIN();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BAB2110_AWA_2110.ID_NO);
        CEP.TRC(SCCGWA, BAB2110_AWA_2110.IS_ENCR);
        if (BAB2110_AWA_2110.ID_NO.trim().length() == 0) {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_INPUT_BILL_NO;
            WS_FLD_NO = BAB2110_AWA_2110.ID_NO_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BAB2110_AWA_2110.IS_ENCR.trim().length() == 0) {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_ENCR_M_INPUT;
            WS_FLD_NO = BAB2110_AWA_2110.IS_ENCR_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_PROC_MAIN() throws IOException,SQLException,Exception {
        B021_READ_BILL_INFO();
        if (pgmRtn) return;
        B022_KEY_CHECKOUT();
        if (pgmRtn) return;
        B023_FMT_OUTPUT();
        if (pgmRtn) return;
    }
    public void B021_READ_BILL_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BACFMST1);
        IBS.init(SCCGWA, BARMST1);
        IBS.init(SCCGWA, WS_HMPW_MAC_DA);
        BACFMST1.FUNC = 'T';
        BARMST1.BILL_NO = BAB2110_AWA_2110.ID_NO;
        CEP.TRC(SCCGWA, BARMST1.BILL_NO);
        BACFMST1.REC_PTR = BARMST1;
        BACFMST1.REC_LEN = 1163;
        S000_CALL_BAZFMST1();
        if (pgmRtn) return;
        if (BACFMST1.RETURN_INFO == 'N') {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_MST1_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BARMST1.KEY.CNTR_NO);
        WS_HMPW_MAC_DA.WS_MA_DAT = BARMST1.DRW_DT;
        WS_HMPW_MAC_DA.WS_MA_DAT1 = BARMST1.BILL_MAT_DT;
        WS_HMPW_MAC_DA.WS_MA_AMT3.WS_MA_AMT2 = BARMST1.BILL_AMT;
        WS_HMPW_MAC_DA.WS_MA_AMT3.WS_MA_AMT_1 = "" + WS_HMPW_MAC_DA.WS_MA_AMT3.WS_MA_AMT2;
        JIBS_tmp_int = WS_HMPW_MAC_DA.WS_MA_AMT3.WS_MA_AMT_1.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) WS_HMPW_MAC_DA.WS_MA_AMT3.WS_MA_AMT_1 = "0" + WS_HMPW_MAC_DA.WS_MA_AMT3.WS_MA_AMT_1;
        if (BARMST1.OLD_DRWR_AC.trim().length() == 0) {
            WS_HMPW_MAC_DA.WS_MA_ACN = BARMST1.DRWR_AC;
        } else {
            WS_HMPW_MAC_DA.WS_MA_ACN = BARMST1.OLD_DRWR_AC;
        }
        CEP.TRC(SCCGWA, WS_HMPW_MAC_DA.WS_MA_DAT);
        CEP.TRC(SCCGWA, WS_HMPW_MAC_DA.WS_MA_DAT1);
        CEP.TRC(SCCGWA, WS_HMPW_MAC_DA.WS_MA_AMT3.WS_MA_AMT2);
    }
    public void B022_KEY_CHECKOUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCHMPW);
        SCCHMPW.INP_DATA.SERV_ID = "EEA4";
        SCCHMPW.INP_DATA.MAC_FLG = '0';
        SCCHMPW.INP_DATA.CHECK_DATE = WS_HMPW_MAC_DA.WS_MA_DAT;
        if (BAB2110_AWA_2110.ID_NO == null) BAB2110_AWA_2110.ID_NO = "";
        JIBS_tmp_int = BAB2110_AWA_2110.ID_NO.length();
        for (int i=0;i<70-JIBS_tmp_int;i++) BAB2110_AWA_2110.ID_NO += " ";
        WS_HMPW_MAC_DA.WS_MA_BILL = BAB2110_AWA_2110.ID_NO.substring(9 - 1, 9 + 8 - 1);
        SCCHMPW.INP_DATA.MAC_DA = IBS.CLS2CPY(SCCGWA, WS_HMPW_MAC_DA);
        SCCHMPW.INP_DATA.MAC_LEN = 75;
        S000_CALL_SCZHMPW();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BAB2110_AWA_2110.IS_ENCR);
        CEP.TRC(SCCGWA, SCCHMPW.OUT_INFO.OUT_DATA.OUT_PW);
        if (!BAB2110_AWA_2110.IS_ENCR.equalsIgnoreCase(SCCHMPW.OUT_INFO.OUT_DATA.OUT_PW)) {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_ENCR_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B023_FMT_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, WS_FMT_TEMP.WS_OUT_TEST);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_FMT_TEMP;
        SCCFMT.DATA_LEN = 1;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_SCZHMPW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-HM-PASSWORD", SCCHMPW);
        IBS.init(SCCGWA, WS_FMT_TEMP);
        CEP.TRC(SCCGWA, SCCHMPW.OUT_INFO.ERR_CODE);
        WS_FMT_TEMP.WS_OUT_TEST = 'N';
        if ((SCCHMPW.OUT_INFO.ERR_CODE.equalsIgnoreCase("009040") 
            || SCCHMPW.OUT_INFO.ERR_CODE.equalsIgnoreCase("001024") 
            || SCCHMPW.OUT_INFO.ERR_CODE.equalsIgnoreCase("001014"))) {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_PSW_NOT_TRUE_LEN;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            if (SCCHMPW.OUT_INFO.ERR_CODE.equalsIgnoreCase("001020")) {
                WS_ERR_MSG = BACMSG_ERROR_MSG.BA_IDEAL_NOT_TRUE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                if (!SCCHMPW.OUT_INFO.ERR_CODE.equalsIgnoreCase("SC0000")) {
                    WS_ERR_MSG = SCCHMPW.OUT_INFO.ERR_CODE;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                    Z_RET();
                    if (pgmRtn) return;
                } else {
                    WS_FMT_TEMP.WS_OUT_TEST = 'Y';
                }
            }
        }
    }
    public void S000_CALL_BAZFMST1() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BA-R-BAZFMST1", BACFMST1);
        if (BACFMST1.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BACFMST1.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
