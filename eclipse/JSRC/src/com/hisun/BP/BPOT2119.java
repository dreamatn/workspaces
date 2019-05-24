package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2119 {
    String JIBS_tmp_str[] = new String[10];
    String CPN_S_HD_CASH = "BP-S-HD-CASH      ";
    String CPN_S_TLR_INFO_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_P_Q_CBOX = "BP-P-Q-CBOX         ";
    String K_HIS_REMARKS = "JIE XIAN CHONG ZHENG";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CCY_CNT = 0;
    int WS_INFO_CNT = 0;
    int WS_START_CNT = 0;
    int WS_I = 0;
    int WS_J = 0;
    double WS_GD_AMT = 0;
    char WS_MATCH_FLAG = ' ';
    char WS_CS_KIND = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRFHIST BPRFHIST = new BPRFHIST();
    BPCIFHIS BPCIFHIS = new BPCIFHIS();
    BPCSHDCS BPCSHDCS = new BPCSHDCS();
    BPCPQBOX BPCPQBOX = new BPCPQBOX();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    BPB2100_AWA_2100 BPB2100_AWA_2100;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2119 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2100_AWA_2100>");
        BPB2100_AWA_2100 = (BPB2100_AWA_2100) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSHDCS);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B020_CHECK_ORG_FOR_CN();
        } else {
            B020_CHECK_ORG();
        }
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5 
            && BPB2100_AWA_2100.CCY_INF1[WS_CCY_CNT-1].CCY1.trim().length() != 0; WS_CCY_CNT += 1) {
            B030_CHECK_TELLER();
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
        B030_HISTORY_RECORD();
        B040_OUT_STORE_PROCESS();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        WS_CS_KIND = BPB2100_AWA_2100.CS_KIND;
    }
    public void B030_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.TX_TYP_CD = "P915";
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
    public void B020_CHECK_ORG_FOR_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.TR_BR);
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.TR_TLR);
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.CS_KIND);
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.IN_BR);
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.IN_TLR);
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.CONF_NO);
        if (!BPB2100_AWA_2100.TR_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_ORIGIN_TLR;
            S000_ERR_MSG_PROC();
        }
        if (BPB2100_AWA_2100.TR_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_ORIGIN_BR;
            S000_ERR_MSG_PROC();
        }
        if (GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE != SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CANCEL_NO_TERTIAN;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_CHECK_ORG() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.TR_TLR);
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.CS_KIND);
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.IN_BR);
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.IN_TLR);
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.CONF_NO);
        if (GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE != SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CANCEL_NO_TERTIAN;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPCIFHIS);
        IBS.init(SCCGWA, BPRFHIST);
        BPCIFHIS.INPUT.FUNC = '5';
        BPRFHIST.KEY.AC_DT = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        BPRFHIST.KEY.JRNNO = GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
        BPRFHIST.KEY.JRN_SEQ = 1;
        BPCIFHIS.INPUT.REC_PT = BPRFHIST;
        BPCIFHIS.INPUT.REC_LEN = 690;
        S000_CALL_BPZIFHIS();
        if (BPRFHIST.TX_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_ORIGIN_BR;
            S000_ERR_MSG_PROC();
        }
        if (!BPRFHIST.TX_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_ORIGIN_TLR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_CHECK_TELLER() throws IOException,SQLException,Exception {
        WS_START_CNT = ( WS_CCY_CNT - 1 ) * 12 + 1;
        for (WS_INFO_CNT = WS_START_CNT; WS_INFO_CNT <= ( WS_CCY_CNT * 12 ) 
            && BPB2100_AWA_2100.P_INFO[WS_INFO_CNT-1].P_PVAL != 0; WS_INFO_CNT += 1) {
            WS_I = WS_INFO_CNT - WS_START_CNT + 1;
            BPCSHDCS.DATA_INFO[WS_CCY_CNT-1].CCY_INFO.CCY_DETAIL[WS_I-1].CCY_VAL = BPB2100_AWA_2100.P_INFO[WS_INFO_CNT-1].P_PVAL;
            BPCSHDCS.DATA_INFO[WS_CCY_CNT-1].CCY_INFO.CCY_DETAIL[WS_I-1].CCY_NUM = BPB2100_AWA_2100.P_INFO[WS_INFO_CNT-1].P_NUM;
            BPCSHDCS.DATA_INFO[WS_CCY_CNT-1].CCY_INFO.CCY_DETAIL[WS_I-1].CCY_MFLG = BPB2100_AWA_2100.P_INFO[WS_INFO_CNT-1].P_MFLG;
        }
        BPCSHDCS.DATA_INFO[WS_CCY_CNT-1].CCY = BPB2100_AWA_2100.CCY_INF1[WS_CCY_CNT-1].CCY1;
        BPCSHDCS.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT = BPB2100_AWA_2100.CCY_INF1[WS_CCY_CNT-1].AMT1;
        BPCSHDCS.DATA_INFO[WS_CCY_CNT-1].CASH_TYP = BPB2100_AWA_2100.CCY_INF1[WS_CCY_CNT-1].CASH_TP1;
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.CCY_INF1[WS_CCY_CNT-1].CCY1);
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.CCY_INF1[WS_CCY_CNT-1].AMT1);
        CEP.TRC(SCCGWA, BPB2100_AWA_2100.CCY_INF1[WS_CCY_CNT-1].CASH_TP1);
    }
    public void B040_OUT_STORE_PROCESS() throws IOException,SQLException,Exception {
        BPCSHDCS.CS_KIND = BPB2100_AWA_2100.CS_KIND;
        BPCSHDCS.TO_BANK = BPB2100_AWA_2100.C_SWIFT;
        BPCSHDCS.MOVE_DT = BPB2100_AWA_2100.MOVE_DT;
        BPCSHDCS.CONF_NO = BPB2100_AWA_2100.CONF_NO;
        S000_CALL_BPZSHDCS();
    }
    public void S000_CALL_BPZSHDCS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_S_HD_CASH;
        SCCCALL.COMMAREA_PTR = BPCSHDCS;
        SCCCALL.ERR_FLDNO = BPB2100_AWA_2100.IN_BR_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_TLR_INFO_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQBOX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_Q_CBOX, BPCPQBOX);
        if (BPCPQBOX.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQBOX.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZIFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-INQ-FHIST", BPCIFHIS);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCIFHIS.OUTPUT.RC);
        if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL) 
            && !JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_END_OF_TABLE)) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCIFHIS.OUTPUT.RC);
            S000_ERR_MSG_PROC();
        }
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
