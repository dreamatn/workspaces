package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSBVSC {
    int JIBS_tmp_int;
    int CYC_TIMES = 4;
    String REC_NHIS = "BP-REC-NHIS         ";
    String R_BRW_BCUS = "BP-R-BRW-BCUS       ";
    String F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY   ";
    String R_MGM_BHIS = "BP-R-MGM-BHIS       ";
    BPZSBVSC_WS_VARIABLES WS_VARIABLES = new BPZSBVSC_WS_VARIABLES();
    BPZSBVSC_WS_HIS_REMARKS WS_HIS_REMARKS = new BPZSBVSC_WS_HIS_REMARKS();
    BPCMSG_ERROR_MSG ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRBCUS BPRBCUS = new BPRBCUS();
    BPRBCUS BPRBCUS = new BPRBCUS();
    BPCRBCUS BPCRBCUS = new BPCRBCUS();
    BPRBHIS BPRBHIS = new BPRBHIS();
    BPCRBHIS BPCRBHIS = new BPCRBHIS();
    BPCO161 BPCO161 = new BPCO161();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCRBCUB BPCRBCUB = new BPCRBCUB();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    BPCSBVSC BPCSBVSC;
    public void MP(SCCGWA SCCGWA, BPCSBVSC BPCSBVSC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSBVSC = BPCSBVSC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSBVSC return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_BCUS_LOGOFF_PROC();
        B040_REC_NHIS();
        B050_WRITE_BHIS_REC();
        B090_DATA_OUTPUT();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFBVQU);
        BPCFBVQU.TX_DATA.TYPE = '0';
        BPCFBVQU.TX_DATA.KEY.CODE = BPCSBVSC.TX_DATA.BV_CODE;
        S000_CALL_BVZFBVQU();
    }
    public void B020_BCUS_LOGOFF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBCUS);
        IBS.init(SCCGWA, BPCRBCUB);
        BPRBCUS.KEY.BV_CODE = BPCSBVSC.TX_DATA.BV_CODE;
        BPRBCUS.KEY.AC = BPCSBVSC.TX_DATA.AC_NO;
        BPRBCUS.HEAD_NO = BPCSBVSC.TX_DATA.HEAD_NO;
        BPRBCUS.KEY.BEG_NO = BPCSBVSC.TX_DATA.BEG_NO;
        BPRBCUS.KEY.END_NO = BPCSBVSC.TX_DATA.END_NO;
        if (BPCSBVSC.TX_DATA.BEG_NO.trim().length() > 0) {
            if (BPCSBVSC.TX_DATA.BEG_NO == null) BPCSBVSC.TX_DATA.BEG_NO = "";
            JIBS_tmp_int = BPCSBVSC.TX_DATA.BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCSBVSC.TX_DATA.BEG_NO += " ";
            for (WS_VARIABLES.I = 1; BPCSBVSC.TX_DATA.BEG_NO.substring(WS_VARIABLES.I - 1, WS_VARIABLES.I + 1 - 1).trim().length() != 0; WS_VARIABLES.I += 1) {
            }
            WS_VARIABLES.I = WS_VARIABLES.I - 1;
        }
        if (BPCSBVSC.TX_DATA.BEG_NO == null) BPCSBVSC.TX_DATA.BEG_NO = "";
        JIBS_tmp_int = BPCSBVSC.TX_DATA.BEG_NO.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) BPCSBVSC.TX_DATA.BEG_NO += " ";
        if (BPCSBVSC.TX_DATA.BEG_NO.substring(0, WS_VARIABLES.I).trim().length() == 0) WS_VARIABLES.BEG_NO = 0;
        else WS_VARIABLES.BEG_NO = Long.parseLong(BPCSBVSC.TX_DATA.BEG_NO.substring(0, WS_VARIABLES.I));
        if (BPCSBVSC.TX_DATA.BEG_NO.substring(0, WS_VARIABLES.I).trim().length() == 0) WS_VARIABLES.NOW_NO = 0;
        else WS_VARIABLES.NOW_NO = Long.parseLong(BPCSBVSC.TX_DATA.BEG_NO.substring(0, WS_VARIABLES.I));
        if (BPCSBVSC.TX_DATA.END_NO.trim().length() > 0) {
            if (BPCSBVSC.TX_DATA.END_NO == null) BPCSBVSC.TX_DATA.END_NO = "";
            JIBS_tmp_int = BPCSBVSC.TX_DATA.END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCSBVSC.TX_DATA.END_NO += " ";
            for (WS_VARIABLES.I = 1; BPCSBVSC.TX_DATA.END_NO.substring(WS_VARIABLES.I - 1, WS_VARIABLES.I + 1 - 1).trim().length() != 0; WS_VARIABLES.I += 1) {
            }
            WS_VARIABLES.I = WS_VARIABLES.I - 1;
        }
        if (BPCSBVSC.TX_DATA.END_NO == null) BPCSBVSC.TX_DATA.END_NO = "";
        JIBS_tmp_int = BPCSBVSC.TX_DATA.END_NO.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) BPCSBVSC.TX_DATA.END_NO += " ";
        if (BPCSBVSC.TX_DATA.END_NO.substring(0, WS_VARIABLES.I).trim().length() == 0) WS_VARIABLES.END_NO = 0;
        else WS_VARIABLES.END_NO = Long.parseLong(BPCSBVSC.TX_DATA.END_NO.substring(0, WS_VARIABLES.I));
        WS_VARIABLES.NUM = WS_VARIABLES.END_NO - WS_VARIABLES.BEG_NO + 1;
        BPCRBCUB.INFO.FUNC = '1';
        S000_CALL_BPZRBCUB();
        IBS.init(SCCGWA, BPCRBCUB);
        BPCRBCUB.INFO.FUNC = 'N';
        S000_CALL_BPZRBCUB();
        if (BPCRBCUB.RETURN_INFO == 'N') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_BCUS_NOTFND;
            S000_ERR_MSG_PROC();
        }
        if (BPCSBVSC.TX_DATA.NUM > WS_VARIABLES.NUM) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_BEG_END_NUM;
            S000_ERR_MSG_PROC();
        }
        if (BPCSBVSC.TX_DATA.NUM == 0) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_BV_NO_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (BPRBCUS.NOR_CNT < BPCSBVSC.TX_DATA.NUM) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_NORAML_NO_NOT_ENOUGH;
            S000_ERR_MSG_PROC();
        }
        if (BPRBCUS.KEY.BEG_NO.trim().length() > 0) {
            if (BPRBCUS.KEY.BEG_NO == null) BPRBCUS.KEY.BEG_NO = "";
            JIBS_tmp_int = BPRBCUS.KEY.BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPRBCUS.KEY.BEG_NO += " ";
            for (WS_VARIABLES.I = 1; BPRBCUS.KEY.BEG_NO.substring(WS_VARIABLES.I - 1, WS_VARIABLES.I + 1 - 1).trim().length() != 0; WS_VARIABLES.I += 1) {
            }
            WS_VARIABLES.I = WS_VARIABLES.I - 1;
        }
        if (BPRBCUS.KEY.BEG_NO == null) BPRBCUS.KEY.BEG_NO = "";
        JIBS_tmp_int = BPRBCUS.KEY.BEG_NO.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) BPRBCUS.KEY.BEG_NO += " ";
        if (BPRBCUS.KEY.BEG_NO.substring(0, WS_VARIABLES.I).trim().length() == 0) WS_VARIABLES.BEG_NO = 0;
        else WS_VARIABLES.BEG_NO = Long.parseLong(BPRBCUS.KEY.BEG_NO.substring(0, WS_VARIABLES.I));
        WS_VARIABLES.BEG_NO = WS_VARIABLES.NOW_NO - WS_VARIABLES.BEG_NO + 1;
        for (WS_VARIABLES.CNT = 1; WS_VARIABLES.CNT <= BPCSBVSC.TX_DATA.NUM; WS_VARIABLES.CNT += 1) {
            if (BPRBCUS.STS == null) BPRBCUS.STS = "";
            JIBS_tmp_int = BPRBCUS.STS.length();
            for (int i=0;i<999-JIBS_tmp_int;i++) BPRBCUS.STS += " ";
            if (!BPRBCUS.STS.substring(WS_VARIABLES.BEG_NO - 1, WS_VARIABLES.BEG_NO + 1 - 1).equalsIgnoreCase("0")) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_STS_MUST_NOR;
                S000_ERR_MSG_PROC();
            }
            if (BPRBCUS.STS == null) BPRBCUS.STS = "";
            JIBS_tmp_int = BPRBCUS.STS.length();
            for (int i=0;i<999-JIBS_tmp_int;i++) BPRBCUS.STS += " ";
            BPRBCUS.STS = BPRBCUS.STS.substring(0, WS_VARIABLES.BEG_NO - 1) + "5" + BPRBCUS.STS.substring(WS_VARIABLES.BEG_NO + 1 - 1);
            BPRBCUS.NOR_CNT -= 1;
            WS_VARIABLES.BEG_NO += 1;
        }
        IBS.init(SCCGWA, BPCRBCUB);
        BPCRBCUB.INFO.FUNC = 'W';
        S000_CALL_BPZRBCUB();
        IBS.init(SCCGWA, BPCRBCUB);
        BPCRBCUB.INFO.FUNC = 'E';
        S000_CALL_BPZRBCUB();
    }
    public void B040_REC_NHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        IBS.init(SCCGWA, WS_HIS_REMARKS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        WS_HIS_REMARKS.HIS_AC_NO = BPCSBVSC.TX_DATA.AC_NO;
        WS_HIS_REMARKS.HIS_BV_CODE = BPCSBVSC.TX_DATA.BV_CODE;
        WS_HIS_REMARKS.HIS_HEAD_NO = BPCSBVSC.TX_DATA.HEAD_NO;
        WS_HIS_REMARKS.HIS_BEG_NO = BPCSBVSC.TX_DATA.BEG_NO;
        WS_HIS_REMARKS.HIS_NUM = BPCSBVSC.TX_DATA.NUM;
        BPCPNHIS.INFO.TX_RMK = IBS.CLS2CPY(SCCGWA, WS_HIS_REMARKS);
        S000_CALL_BPZPNHIS();
    }
    public void B050_WRITE_BHIS_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBHIS);
        BPRBHIS.KEY.AC = BPCSBVSC.TX_DATA.AC_NO;
        BPRBHIS.KEY.BV_CODE = BPCSBVSC.TX_DATA.BV_CODE;
        BPRBHIS.KEY.HEAD_NO = BPCSBVSC.TX_DATA.HEAD_NO;
        BPRBHIS.KEY.BEG_NO = BPCSBVSC.TX_DATA.BEG_NO;
        BPRBHIS.KEY.END_NO = BPCSBVSC.TX_DATA.END_NO;
        BPRBHIS.KEY.TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRBHIS.KEY.TX_JRN = SCCGWA.COMM_AREA.JRN_NO;
        BPRBHIS.TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRBHIS.TX_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRBHIS.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPRBHIS.STS = '5';
        IBS.init(SCCGWA, BPCRBHIS);
        BPCRBHIS.INFO.FUNC = 'A';
        S000_CALL_BPZRBHIS();
    }
    public void B090_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCO161);
        BPCO161.AC_NO = BPCSBVSC.TX_DATA.AC_NO;
        BPCO161.AC_NAME = BPCSBVSC.TX_DATA.AC_NAME;
        BPCO161.BV_CODE = BPCSBVSC.TX_DATA.BV_CODE;
        BPCO161.BV_NAME = BPCSBVSC.TX_DATA.BV_NAME;
        BPCO161.HEAD_NO = BPCSBVSC.TX_DATA.HEAD_NO;
        BPCO161.BEG_NO = BPCSBVSC.TX_DATA.BEG_NO;
        BPCO161.END_NO = BPCSBVSC.TX_DATA.END_NO;
        BPCO161.NUM = BPCSBVSC.TX_DATA.NUM;
        BPCO161.REASON = BPCSBVSC.TX_DATA.REASON;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCSBVSC.TX_DATA.OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCO161;
        SCCFMT.DATA_LEN = 399;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BVZFBVQU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, F_BV_PRM_QUERY, BPCFBVQU);
        if (BPCFBVQU.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFBVQU.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRBCUB() throws IOException,SQLException,Exception {
        BPCRBCUB.INFO.POINTER = BPRBCUS;
        BPCRBCUB.INFO.LEN = 106;
        IBS.CALLCPN(SCCGWA, R_BRW_BCUS, BPCRBCUB);
        if (BPCRBCUB.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRBCUB.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRBHIS() throws IOException,SQLException,Exception {
        BPCRBHIS.INFO.POINTER = BPRBHIS;
        BPCRBHIS.INFO.LEN = 163;
        IBS.CALLCPN(SCCGWA, R_MGM_BHIS, BPCRBHIS);
        if (BPCRBHIS.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRBHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
