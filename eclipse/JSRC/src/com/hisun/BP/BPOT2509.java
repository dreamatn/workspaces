package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2509 {
    String JIBS_tmp_str[] = new String[10];
    String K_OUTPUT_FMT = "BP275";
    int K_MAX_CCY_CNT = 20;
    int K_LAMI_CCY = 1;
    int K_MAX_PAR_CNT = 12;
    String CPN_S_BOX_TO_FROM_LAT = "BP-S-BOX-TO-FROM-LAT";
    String CPN_R_ADW_TLVB = "BP-R-ADW-TLVB";
    String CPN_P_ADD_CASH_HIS = "BP-P-ADD-CHIS";
    String K_HIS_REMARKS = "BR-ATM-MOV-OUT-RES";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CCY_CNT = 0;
    int WS_PAR_CNT = 0;
    int WS_CUR_CNT = 0;
    int WS_CCY = 0;
    BPOT2509_WS_SUM_ATM_AMT[] WS_SUM_ATM_AMT = new BPOT2509_WS_SUM_ATM_AMT[999];
    BPOT2509_WS_CONF_TXT WS_CONF_TXT = new BPOT2509_WS_CONF_TXT();
    BPOT2509_WS_DATA_OUTPUT WS_DATA_OUTPUT = new BPOT2509_WS_DATA_OUTPUT();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSLAMI BPCSLAMI = new BPCSLAMI();
    BPCTLVBF BPCTLVBF = new BPCTLVBF();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPCPCHIS BPCPCHIS = new BPCPCHIS();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    BPB2530_AWA_2530 BPB2530_AWA_2530;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public BPOT2509() {
        for (int i=0;i<999;i++) WS_SUM_ATM_AMT[i] = new BPOT2509_WS_SUM_ATM_AMT();
    }
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2509 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2530_AWA_2530>");
        BPB2530_AWA_2530 = (BPB2530_AWA_2530) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_TRANSFER_CASH_TO_ATM();
        B030_HISTORY_RECORD();
        B030_TRANS_DATA_OUTPUT();
    }
    public void B030_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.TX_TYP_CD = "P905";
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
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (!BPB2530_AWA_2530.TR_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_ORIGIN_TLR;
            S000_ERR_MSG_PROC();
        }
        if (BPB2530_AWA_2530.TR_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_ORIGIN_BR;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPRTLVB);
        IBS.init(SCCGWA, BPCTLVBF);
        BPRTLVB.CRNT_TLR = BPB2530_AWA_2530.TR_TLR;
        BPCTLVBF.INFO.FUNC = 'T';
        BPCTLVBF.POINTER = BPRTLVB;
        BPCTLVBF.LEN = 187;
        S000_CALL_BPZTLVBF();
        if (BPCTLVBF.RETURN_INFO == 'N') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR112);
        }
        if (BPRTLVB.PLBOX_TP == '4') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR113);
        }
    }
    public void B020_TRANSFER_CASH_TO_ATM() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, BPB2530_AWA_2530.CONF_INF, WS_CONF_TXT);
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= K_MAX_CCY_CNT; WS_CCY_CNT += 1) {
            if (BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0 
                && BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY.charAt(0) != 0X00 
                && BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].ATM.trim().length() > 0) {
                IBS.init(SCCGWA, BPCSLAMI);
                BPCSLAMI.FUNC = '0';
                BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].MOVE_DT = WS_CONF_TXT.WS_CONF_INFO[WS_CCY_CNT-1].WS_MOVE_DT;
                BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CONF_NO = WS_CONF_TXT.WS_CONF_INFO[WS_CCY_CNT-1].WS_CONF_NO;
                CEP.TRC(SCCGWA, BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CONF_NO);
                CEP.TRC(SCCGWA, BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].MOVE_DT);
                CEP.TRC(SCCGWA, BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].ATM);
                BPCSLAMI.FROM_TLR = BPB2530_AWA_2530.TLR;
                BPCSLAMI.FROM_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPCSLAMI.TO_TLR = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].ATM;
                BPCSLAMI.TO_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPCSLAMI.CS_KIND = '0';
                BPCSLAMI.CONF_NO = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CONF_NO;
                BPCSLAMI.MOVE_DT = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].MOVE_DT;
                BPCSLAMI.CCY_INFO[K_LAMI_CCY-1].CASH_TYP = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
                BPCSLAMI.CCY_INFO[K_LAMI_CCY-1].CCY = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY;
                BPCSLAMI.CCY_INFO[K_LAMI_CCY-1].CCY_AMT = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
                CEP.TRC(SCCGWA, BPRTLVB.PLBOX_TP);
                BPCSLAMI.PLBOX_TYP = BPRTLVB.PLBOX_TP;
                S000_CALL_BPZSLAMI();
                if (BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() == 0) WS_CCY = 0;
                else WS_CCY = Integer.parseInt(BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY);
                WS_SUM_ATM_AMT[WS_CCY-1].WS_CASH_TYP = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
                WS_SUM_ATM_AMT[WS_CCY-1].WS_SUM_CCY = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY;
                WS_SUM_ATM_AMT[WS_CCY-1].WS_SUM_AMT = WS_SUM_ATM_AMT[WS_CCY-1].WS_SUM_AMT + BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
            }
        }
        for (WS_CCY = 1; WS_CCY <= 999; WS_CCY += 1) {
            if (WS_SUM_ATM_AMT[WS_CCY-1].WS_SUM_CCY.trim().length() > 0 
                && WS_SUM_ATM_AMT[WS_CCY-1].WS_SUM_AMT != 0) {
                IBS.init(SCCGWA, BPCPCHIS);
                BPCPCHIS.TLR = SCCGWA.COMM_AREA.TL_ID;
                BPCPCHIS.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
                BPCPCHIS.CCY = WS_SUM_ATM_AMT[WS_CCY-1].WS_SUM_CCY;
                BPCPCHIS.CASH_TYP = WS_SUM_ATM_AMT[WS_CCY-1].WS_CASH_TYP;
                BPCPCHIS.AMT = WS_SUM_ATM_AMT[WS_CCY-1].WS_SUM_AMT;
                BPCPCHIS.CS_KIND = BPCSLAMI.CS_KIND;
                CEP.TRC(SCCGWA, "DEV1");
                if (BPRTLVB.PLBOX_TP == '3' 
                    || BPRTLVB.PLBOX_TP == '6') {
                    BPCPCHIS.VB_FLG = '0';
                } else {
                    if (BPRTLVB.PLBOX_TP == '1' 
                        || BPRTLVB.PLBOX_TP == '2' 
                        || BPRTLVB.PLBOX_TP == '5') {
                        BPCPCHIS.VB_FLG = '1';
                    }
                }
                BPCPCHIS.IN_OUT = 'C';
                S000_CALL_BPZPCHIS();
            }
        }
    }
    public void B030_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        WS_DATA_OUTPUT.WS_OUT_FROM_TLR = BPB2530_AWA_2530.TLR;
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= K_MAX_CCY_CNT 
            && BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() != 0 
            && BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].ATM.trim().length() != 0; WS_CCY_CNT += 1) {
            WS_DATA_OUTPUT.WS_OUT_ATM_INFO[WS_CCY_CNT-1].WS_OUT_CS_TP = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
            WS_DATA_OUTPUT.WS_OUT_ATM_INFO[WS_CCY_CNT-1].WS_OUT_CCY = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY;
            WS_DATA_OUTPUT.WS_OUT_ATM_INFO[WS_CCY_CNT-1].WS_OUT_AMT = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
            WS_DATA_OUTPUT.WS_OUT_ATM_INFO[WS_CCY_CNT-1].WS_OUT_TO_ATM = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].ATM;
            WS_DATA_OUTPUT.WS_OUT_ATM_INFO[WS_CCY_CNT-1].WS_OUT_MV_DT = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].MOVE_DT;
            WS_DATA_OUTPUT.WS_OUT_ATM_INFO[WS_CCY_CNT-1].WS_OUT_CONF = BPB2530_AWA_2530.CCY_INFO[WS_CCY_CNT-1].CONF_NO;
        }
        WS_DATA_OUTPUT.WS_OUT_DT_CNT = WS_CCY_CNT;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_DATA_OUTPUT;
        SCCFMT.DATA_LEN = 932067;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZTLVBF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_TLVB, BPCTLVBF);
    }
    public void S000_CALL_BPZPCHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_ADD_CASH_HIS, BPCPCHIS);
        if (BPCPCHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPCHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZSLAMI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BOX_TO_FROM_LAT, BPCSLAMI);
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
