package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2600 {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    String K_OUTPUT_FMT = "BP265";
    String CPN_S_BOOKING_MAINTAIN = "BP-S-BOKING-MAINTAIN";
    String CPN_P_INQ_ORG_STATION = "BP-P-INQ-ORG-STATION";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String K_HIS_REMARKS = "DA E XIAN JIN YU YUE";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_IDX = 0;
    int WS_BR = 0;
    int WS_BR3 = 0;
    int WS_BR4 = 0;
    int WS_I = 0;
    int WS_CNT = 0;
    char WS_FUNC_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCOPPDM BPCOPPDM = new BPCOPPDM();
    BPCPRGST BPCPRGST = new BPCPRGST();
    CICCUST CICCUST = new CICCUST();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    SCCGWA SCCGWA;
    BPB2600_AWA_2600 BPB2600_AWA_2600;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2600 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2600_AWA_2600>");
        BPB2600_AWA_2600 = (BPB2600_AWA_2600) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2600_AWA_2600);
        CEP.TRC(SCCGWA, BPB2600_AWA_2600.APT_CINO);
        CEP.TRC(SCCGWA, BPB2600_AWA_2600.APT_DT);
        CEP.TRC(SCCGWA, BPB2600_AWA_2600.APT_TM);
        CEP.TRC(SCCGWA, BPB2600_AWA_2600.APT_WDDT);
        CEP.TRC(SCCGWA, BPB2600_AWA_2600.APT_AMT);
        CEP.TRC(SCCGWA, BPB2600_AWA_2600.APT_CHNL);
        CEP.TRC(SCCGWA, BPB2600_AWA_2600.APT_BR);
        CEP.TRC(SCCGWA, BPB2600_AWA_2600.CONTACT);
        CEP.TRC(SCCGWA, BPB2600_AWA_2600.CONT_PN);
        CEP.TRC(SCCGWA, BPB2600_AWA_2600.CASH_TYP);
        CEP.TRC(SCCGWA, BPB2600_AWA_2600.APT_CCY);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_CHECK_INPUT_FOR_CN();
        } else {
            B010_CHECK_INPUT();
        }
        B030_HISTORY_RECORD();
        B020_ADD_BOOKING_CASH();
    }
    public void B030_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.TX_TYP_CD = "P916";
        S000_CALL_BPZPNHIS();
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void B010_CHECK_INPUT_FOR_CN() throws IOException,SQLException,Exception {
        WS_FUNC_FLG = BPB2600_AWA_2600.FUNC;
        if (WS_FUNC_FLG != 'A') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FUNC_IS_INVALID;
            WS_FLD_NO = BPB2600_AWA_2600.FUNC_NO;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
            && !BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASH_LIB_BOX_TLR;
            if (SCCGWA.COMM_AREA.TL_ID.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(SCCGWA.COMM_AREA.TL_ID);
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, CICCUST);
        CICCUST.DATA.CI_NO = BPB2600_AWA_2600.APT_CINO;
        CICCUST.FUNC = 'C';
        CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
        S000_CALL_CIZCUST_FOR_CN();
        CEP.TRC(SCCGWA, CICCUST.RC);
        if (CICCUST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPCOCLWD);
        BPCOCLWD.CAL_CODE = "CN";
        BPCOCLWD.DATE1 = SCCGWA.COMM_AREA.AC_DATE;
        BPCOCLWD.DATE2 = BPB2600_AWA_2600.APT_WDDT;
        CEP.TRC(SCCGWA, "NCB0822002");
        CEP.TRC(SCCGWA, BPCOCLWD.DATE1);
        CEP.TRC(SCCGWA, BPCOCLWD.DATE2);
        S000_CALL_BPZPCLWD();
        CEP.TRC(SCCGWA, "NCB0822001");
        CEP.TRC(SCCGWA, BPCOCLWD.WDAYS);
        if (BPCOCLWD.WDAYS < 2) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_APP_ADV_TWO_DAYS;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPCLWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CAL-WORK-DAY", BPCOCLWD);
        if (BPCOCLWD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOCLWD.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        WS_FUNC_FLG = BPB2600_AWA_2600.FUNC;
        if (WS_FUNC_FLG != 'A') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FUNC_IS_INVALID;
            WS_FLD_NO = BPB2600_AWA_2600.FUNC_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_ADD_BOOKING_CASH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOPPDM);
        BPCOPPDM.FUNC = 'A';
        BPCOPPDM.OUTPUT_FMT = K_OUTPUT_FMT;
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZSPPDM();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCOPPDM.APT_CINO = BPB2600_AWA_2600.APT_CINO;
        BPCOPPDM.APT_DT = BPB2600_AWA_2600.APT_DT;
        BPCOPPDM.APT_TM = BPB2600_AWA_2600.APT_TM;
        BPCOPPDM.APT_WDDT = BPB2600_AWA_2600.APT_WDDT;
        BPCOPPDM.APT_AMT = BPB2600_AWA_2600.APT_AMT;
        BPCOPPDM.APT_CHNL = BPB2600_AWA_2600.APT_CHNL;
        BPCOPPDM.APT_BR = BPB2600_AWA_2600.APT_BR;
        BPCOPPDM.CONTACT = BPB2600_AWA_2600.CONTACT;
        BPCOPPDM.CONT_PN = BPB2600_AWA_2600.CONT_PN;
        BPCOPPDM.CASH_TYP = BPB2600_AWA_2600.CASH_TYP;
        BPCOPPDM.APT_CCY = BPB2600_AWA_2600.APT_CCY;
        BPCOPPDM.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCOPPDM.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
    }
    public void S000_CALL_BPZSPPDM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_S_BOOKING_MAINTAIN;
        SCCCALL.COMMAREA_PTR = BPCOPPDM;
        SCCCALL.ERR_FLDNO = BPB2600_AWA_2600.FUNC_NO;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCOPPDM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOPPDM.RC);
            WS_FLD_NO = BPB2600_AWA_2600.FUNC_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_P_INQ_ORG_STATION;
        SCCCALL.COMMAREA_PTR = BPCPRGST;
        SCCCALL.ERR_FLDNO = SCCGWA.COMM_AREA.TL_ID;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCPRGST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRGST.RC);
            if (SCCGWA.COMM_AREA.TL_ID.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(SCCGWA.COMM_AREA.TL_ID);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_F_TLR_INF_QUERY;
        SCCCALL.COMMAREA_PTR = BPCFTLRQ;
        SCCCALL.ERR_FLDNO = SCCGWA.COMM_AREA.TL_ID;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            if (SCCGWA.COMM_AREA.TL_ID.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(SCCGWA.COMM_AREA.TL_ID);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZCUST_FOR_CN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
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
