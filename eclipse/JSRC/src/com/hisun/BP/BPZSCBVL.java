package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSCBVL {
    int JIBS_tmp_int;
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY   ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    String CPN_R_MGM_BCUS = "BP-R-MGM-BCUS       ";
    String CPN_R_MGM_BHIS = "BP-R-MGM-BHIS       ";
    String CPN_R_BRW_BCUS = "BP-R-BRW-BCUS       ";
    String K_OUTPUT_FMT = "BP162";
    String K_HIS_REMARKS = "TLR-BV-TO-SELL ";
    String WS_ERR_MSG = " ";
    short WS_CNT = 0;
    short WS_NUM = 0;
    long WS_NOW_NO = 0;
    long WS_BEG_NO = 0;
    long WS_END_NO = 0;
    String WS_BV_CODE = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCO162 BPCO162 = new BPCO162();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPRBCUS BPRBCUS = new BPRBCUS();
    BPRBHIS BPRBHIS = new BPRBHIS();
    BPCRBCUS BPCRBCUS = new BPCRBCUS();
    BPCRBHIS BPCRBHIS = new BPCRBHIS();
    BPCRBCUB BPCRBCUB = new BPCRBCUB();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCSCBVL BPCSCBVL;
    public void MP(SCCGWA SCCGWA, BPCSCBVL BPCSCBVL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSCBVL = BPCSCBVL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSCBVL return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_QUERY_VOUCHER();
        B020_CHECK_INPUT();
        if (BPCSCBVL.CRG_TYP == '1') {
            B030_CHECK_AC();
        }
        if ((WS_BV_CODE.equalsIgnoreCase("TEST1") 
            || WS_BV_CODE.equalsIgnoreCase("996"))) {
            B040_BV_LOST_PROC();
        } else {
            B050_LINK_BV_LOST_PROC();
        }
        B060_ACC_FEE_PROC();
        B070_REC_NHIS();
        B090_DATA_OUTPUT();
    }
    public void B010_QUERY_VOUCHER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFBVQU);
        BPCFBVQU.TX_DATA.TYPE = '0';
        BPCFBVQU.TX_DATA.KEY.CODE = BPCSCBVL.BV_CODE;
        S000_CALL_BVZFBVQU();
    }
    public void B020_CHECK_INPUT() throws IOException,SQLException,Exception {
        WS_BV_CODE = BPCSCBVL.BV_CODE;
        if ((WS_BV_CODE.equalsIgnoreCase("999") 
            || WS_BV_CODE.equalsIgnoreCase("998"))) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = BPCSCBVL.BR;
            S000_CALL_BPZPQORG();
        }
        if ((WS_BV_CODE.equalsIgnoreCase("995") 
            || WS_BV_CODE.equalsIgnoreCase("994")) 
            && BPCSCBVL.OWNE_IND == ' ') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_OWNE_IND_MUST_IN;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_CHECK_AC() throws IOException,SQLException,Exception {
    }
    public void B040_BV_LOST_PROC() throws IOException,SQLException,Exception {
        R010_BCUS_LOST_PROC();
        R020_BHIS_LOST_PROC();
    }
    public void B050_LINK_BV_LOST_PROC() throws IOException,SQLException,Exception {
    }
    public void B060_ACC_FEE_PROC() throws IOException,SQLException,Exception {
    }
    public void B070_REC_NHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.TX_TYP_CD = "01";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        S000_CALL_BPZPNHIS();
    }
    public void B090_DATA_OUTPUT() throws IOException,SQLException,Exception {
        R090_TRANS_DATA_OUPUT();
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCO162;
        SCCFMT.DATA_LEN = 1283;
        CEP.TRC(SCCGWA, BPCO162);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R010_BCUS_LOST_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBCUS);
        IBS.init(SCCGWA, BPCRBCUB);
        BPRBCUS.KEY.BV_CODE = BPCSCBVL.BV_CODE;
        BPRBCUS.KEY.AC = BPCSCBVL.APP_AC;
        if (BPCSCBVL.CONT_MTH == '0') {
            BPRBCUS.KEY.BEG_NO = BPCSCBVL.BV_NO;
            BPRBCUS.KEY.END_NO = BPCSCBVL.BV_NO;
            if (BPCSCBVL.BV_NO == null) BPCSCBVL.BV_NO = "";
            JIBS_tmp_int = BPCSCBVL.BV_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCSCBVL.BV_NO += " ";
            if (BPCSCBVL.BV_NO.substring(0, BPCFBVQU.TX_DATA.NO_LENGTH).trim().length() == 0) WS_BEG_NO = 0;
            else WS_BEG_NO = Long.parseLong(BPCSCBVL.BV_NO.substring(0, BPCFBVQU.TX_DATA.NO_LENGTH));
            WS_NUM = 1;
        } else {
            BPRBCUS.KEY.BEG_NO = BPCSCBVL.BEG_NO;
            BPRBCUS.KEY.END_NO = BPCSCBVL.END_NO;
            if (BPCSCBVL.BEG_NO == null) BPCSCBVL.BEG_NO = "";
            JIBS_tmp_int = BPCSCBVL.BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCSCBVL.BEG_NO += " ";
            if (BPCSCBVL.BEG_NO.substring(0, BPCFBVQU.TX_DATA.NO_LENGTH).trim().length() == 0) WS_BEG_NO = 0;
            else WS_BEG_NO = Long.parseLong(BPCSCBVL.BEG_NO.substring(0, BPCFBVQU.TX_DATA.NO_LENGTH));
            if (BPCSCBVL.END_NO == null) BPCSCBVL.END_NO = "";
            JIBS_tmp_int = BPCSCBVL.END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCSCBVL.END_NO += " ";
            if (BPCSCBVL.END_NO.substring(0, BPCFBVQU.TX_DATA.NO_LENGTH).trim().length() == 0) WS_END_NO = 0;
            else WS_END_NO = Long.parseLong(BPCSCBVL.END_NO.substring(0, BPCFBVQU.TX_DATA.NO_LENGTH));
            WS_NUM = (short) (WS_END_NO - WS_BEG_NO + 1);
        }
        BPCRBCUB.INFO.FUNC = '1';
        S000_CALL_BPZRBCUB();
        IBS.init(SCCGWA, BPCRBCUB);
        BPCRBCUB.INFO.FUNC = 'N';
        S000_CALL_BPZRBCUB();
        CEP.TRC(SCCGWA, BPCRBCUB.RC);
        if (BPCRBCUB.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BCUS_NOTFND;
            CEP.TRC(SCCGWA, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
        }
        WS_NOW_NO = WS_BEG_NO;
        if (BPRBCUS.KEY.BEG_NO == null) BPRBCUS.KEY.BEG_NO = "";
        JIBS_tmp_int = BPRBCUS.KEY.BEG_NO.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) BPRBCUS.KEY.BEG_NO += " ";
        if (BPRBCUS.KEY.BEG_NO.substring(0, BPCFBVQU.TX_DATA.NO_LENGTH).trim().length() == 0) WS_BEG_NO = 0;
        else WS_BEG_NO = Long.parseLong(BPRBCUS.KEY.BEG_NO.substring(0, BPCFBVQU.TX_DATA.NO_LENGTH));
        WS_BEG_NO = WS_NOW_NO - WS_BEG_NO + 1;
        CEP.TRC(SCCGWA, WS_NUM);
        CEP.TRC(SCCGWA, BPRBCUS.KEY.BEG_NO);
        CEP.TRC(SCCGWA, BPRBCUS.NOR_CNT);
        CEP.TRC(SCCGWA, WS_BEG_NO);
        CEP.TRC(SCCGWA, BPRBCUS.STS);
        for (WS_CNT = 1; WS_CNT <= WS_NUM; WS_CNT += 1) {
            if (BPRBCUS.STS == null) BPRBCUS.STS = "";
            JIBS_tmp_int = BPRBCUS.STS.length();
            for (int i=0;i<999-JIBS_tmp_int;i++) BPRBCUS.STS += " ";
            if (!BPRBCUS.STS.substring(WS_BEG_NO - 1, WS_BEG_NO + 1 - 1).equalsIgnoreCase("0")) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BCUS_STS_ERR;
                S000_ERR_MSG_PROC();
            }
            if (BPRBCUS.STS == null) BPRBCUS.STS = "";
            JIBS_tmp_int = BPRBCUS.STS.length();
            for (int i=0;i<999-JIBS_tmp_int;i++) BPRBCUS.STS += " ";
            BPRBCUS.STS = BPRBCUS.STS.substring(0, WS_BEG_NO - 1) + "2" + BPRBCUS.STS.substring(WS_BEG_NO + 1 - 1);
            BPRBCUS.NOR_CNT -= 1;
            WS_BEG_NO += 1;
        }
        IBS.init(SCCGWA, BPCRBCUB);
        BPCRBCUB.INFO.FUNC = 'W';
        S000_CALL_BPZRBCUB();
        IBS.init(SCCGWA, BPCRBCUB);
        BPCRBCUB.INFO.FUNC = 'E';
        S000_CALL_BPZRBCUB();
    }
    public void R020_BHIS_LOST_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBHIS);
        IBS.init(SCCGWA, BPCRBHIS);
        BPRBHIS.KEY.AC = BPCSCBVL.APP_AC;
        BPRBHIS.KEY.BV_CODE = BPCSCBVL.BV_CODE;
        if (BPCSCBVL.CONT_MTH == '0') {
            BPRBHIS.KEY.BEG_NO = BPCSCBVL.BV_NO;
            BPRBHIS.KEY.END_NO = BPCSCBVL.BV_NO;
        } else {
            BPRBHIS.KEY.BEG_NO = BPCSCBVL.BEG_NO;
            BPRBHIS.KEY.END_NO = BPCSCBVL.END_NO;
        }
        BPRBHIS.KEY.TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRBHIS.KEY.TX_JRN = SCCGWA.COMM_AREA.JRN_NO;
        BPRBHIS.TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRBHIS.TX_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRBHIS.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPRBHIS.STS = '2';
        BPRBHIS.REC_STS = '0';
        BPCRBHIS.INFO.FUNC = 'A';
        S000_CALL_BPZRBHIS();
        if (BPCRBHIS.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_WRITE_BPTBHIS_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void R090_TRANS_DATA_OUPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCO162);
        BPCO162.LOST_MAN = BPCSCBVL.LOST_MAN;
        BPCO162.LOST_DT = BPCSCBVL.LOST_DT;
        BPCO162.LOST_ADD = BPCSCBVL.LOST_ADD;
        BPCO162.LOST_REA = BPCSCBVL.LOST_REA;
        BPCO162.BV_CODE = BPCSCBVL.BV_CODE;
        BPCO162.OWNE_IND = BPCSCBVL.OWNE_IND;
        BPCO162.BR = BPCSCBVL.BR;
        BPCO162.CONT_MTH = BPCSCBVL.CONT_MTH;
        BPCO162.BV_NO = BPCSCBVL.BV_NO;
        BPCO162.BEG_NO = BPCSCBVL.BEG_NO;
        BPCO162.END_NO = BPCSCBVL.END_NO;
        BPCO162.NUM = BPCSCBVL.NUM;
        BPCO162.TYPE = BPCSCBVL.TYPE;
        BPCO162.TRAN_IND = BPCSCBVL.TRAN_IND;
        BPCO162.AMT = BPCSCBVL.AMT;
        BPCO162.ISSU_DT = BPCSCBVL.ISSU_DT;
        BPCO162.APP_AC = BPCSCBVL.APP_AC;
        BPCO162.APP_CNM = BPCSCBVL.APP_CNM;
        BPCO162.OUT_AC = BPCSCBVL.OUT_AC;
        BPCO162.OUT_CNM = BPCSCBVL.OUT_CNM;
        BPCO162.PAY_DT = BPCSCBVL.PAY_DT;
        BPCO162.REMARK = BPCSCBVL.REMARK;
        BPCO162.CRG_TYP = BPCSCBVL.CRG_TYP;
        BPCO162.CASH_CD = BPCSCBVL.CASH_CD;
        BPCO162.PAY_AC = BPCSCBVL.PAY_AC;
        BPCO162.PAY_CNM = BPCSCBVL.PAY_CNM;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BVZFBVQU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_BV_PRM_QUERY, BPCFBVQU);
        if (BPCFBVQU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFBVQU.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRBHIS() throws IOException,SQLException,Exception {
        BPCRBHIS.INFO.POINTER = BPRBHIS;
        BPCRBHIS.INFO.LEN = 163;
        IBS.CALLCPN(SCCGWA, CPN_R_MGM_BHIS, BPCRBHIS);
        if (BPCRBHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRBHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRBCUS() throws IOException,SQLException,Exception {
        BPCRBCUS.INFO.POINTER = BPRBCUS;
        BPCRBCUS.INFO.LEN = 106;
        IBS.CALLCPN(SCCGWA, CPN_R_MGM_BCUS, BPCRBCUS);
        if (BPCRBCUS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRBCUS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRBCUB() throws IOException,SQLException,Exception {
        BPCRBCUB.INFO.POINTER = BPRBCUS;
        BPCRBCUB.INFO.LEN = 106;
        IBS.CALLCPN(SCCGWA, CPN_R_BRW_BCUS, BPCRBCUB);
        if (BPCRBCUB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRBCUB.RC);
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
