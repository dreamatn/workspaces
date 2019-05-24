package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.DC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSVALD {
    int JIBS_tmp_int;
    DBParm DDTMST_RD;
    DBParm DDTCCY_RD;
    String K_OUTPUT_FMT = "DD583";
    String K_HIS_COPYBOOK_NAME = "DDCSVALD";
    String K_HIS_REMARKS = "ACCOUNTI AUDIT";
    String WS_ERR_MSG = " ";
    DDZSVALD_WS_OUT_DATA WS_OUT_DATA = new DDZSVALD_WS_OUT_DATA();
    String WS_STD_AC = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCBINF SCCBINF = new SCCBINF();
    DCCPACTY DCCPACTY = new DCCPACTY();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPCTCALF BPCTCALF = new BPCTCALF();
    CICQACAC CICQACAC = new CICQACAC();
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    SCCGWA SCCGWA;
    DDCSVALD DDCSVALD;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DDCSVALD DDCSVALD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSVALD = DDCSVALD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZSVALD return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_DATA_VALIDITY();
        B030_CHECK_STS_PROC();
        B040_GET_CI_PROC();
        B050_UPD_STS_PROC();
        if (SCCGWA.COMM_AREA.JRN_NO != 0) {
            B170_NFIN_TX_HIS_PROC();
        }
        B070_OUTPUT_DATA();
    }
    public void B010_CHECK_DATA_VALIDITY() throws IOException,SQLException,Exception {
        if (DDCSVALD.ACNO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDCSVALD.VAL_RLT != 'Y' 
            && DDCSVALD.VAL_RLT != 'N') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_VAL_RLT_INVALID;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_CHECK_STS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCSVALD.ACNO;
        T000_READ_DDTMST();
        if (DDRMST.AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
        }
        if (DDRMST.AC_STS != 'V') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_NEED_VAL;
            S000_ERR_MSG_PROC();
        }
    }
    public void B040_GET_CI_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.AGR_NO = DDCSVALD.ACNO;
        CICQACAC.FUNC = 'R';
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_PROD_CD_ACAC);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_CR_FLG);
    }
    public void B050_UPD_STS_PROC() throws IOException,SQLException,Exception {
        if (DDCSVALD.VAL_RLT == 'Y') {
            DDRMST.AC_STS = 'O';
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            DDRMST.AC_STS_WORD = "0" + DDRMST.AC_STS_WORD.substring(1);
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 2 - 1) + "1" + DDRMST.AC_STS_WORD.substring(2 + 1 - 1);
            DDRMST.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRMST.LAST_TLR = SCCGWA.COMM_AREA.TL_ID;
            DDRMST.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRMST.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DDTMST();
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.REQ_SYSTEM);
            if (SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("031400") 
                || SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("061000")) {
                S000_SET_FEE_INFO();
                S000_CALL_FEE();
            }
        }
    }
    public void B170_NFIN_TX_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.AC = DDCSVALD.ACNO;
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK_NAME;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        S000_CALL_BPZPNHIS();
    }
    public void B070_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_DATA);
        WS_OUT_DATA.WS_ACNO = DDCSVALD.ACNO;
        WS_OUT_DATA.WS_VAL_RLT = DDCSVALD.VAL_RLT;
        CEP.TRC(SCCGWA, WS_OUT_DATA);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUT_DATA;
        SCCFMT.DATA_LEN = 33;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_SET_FEE_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = DDCSVALD.ACNO;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC;
        BPCFFTXI.TX_DATA.CCY_TYPE = CICQACAC.O_DATA.O_ACAC_DATA.O_CR_FLG;
        BPCFFTXI.TX_DATA.SVR_CD = "0115840";
        BPCFFTXI.TX_DATA.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        BPCFFTXI.TX_DATA.PROC_TYPE = '6';
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC);
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY);
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CCY_TYPE);
        S000_CALL_BPZFFTXI();
    }
    public void S000_CALL_FEE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTCALF);
        BPCTCALF.INPUT_AREA.FUNC_CODE = 'C';
        BPCTCALF.INPUT_AREA.TX_CCY = CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC;
        BPCTCALF.INPUT_AREA.TX_AC = DDCSVALD.ACNO;
        BPCTCALF.INPUT_AREA.TX_CNT = 1;
        BPCTCALF.INPUT_AREA.TX_AMT = 0;
        BPCTCALF.INPUT_AREA.TX_CI = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        BPCTCALF.INPUT_AREA.SVR_CD = "0115840";
        BPCTCALF.INPUT_AREA.PROD_CODE = CICQACAC.O_DATA.O_ACAC_DATA.O_PROD_CD_ACAC;
        BPCTCALF.INPUT_AREA.PROD_CODE1 = CICQACAC.O_DATA.O_ACAC_DATA.O_PROD_CD_ACAC;
        S000_CALL_BPZFCALF();
    }
    public void S000_CALL_BPZFFTXI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-TX-INFO", BPCFFTXI);
        if (BPCFFTXI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFTXI.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFCALF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-CAL-FEE", BPCTCALF);
        if (BPCTCALF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTCALF.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.upd = true;
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_REWRITE_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.REWRITE(SCCGWA, DDRMST, DDTMST_RD);
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
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
