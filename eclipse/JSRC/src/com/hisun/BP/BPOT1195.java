package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1195 {
    int JIBS_tmp_int;
    String K_OUTPUT_FMT = "BP400";
    String CPN_BANK_MAINTAIN = "BP-S-BANK-MAINTAIN  ";
    String CPN_F_F_CAL_FEE = "BP-F-F-CAL-FEE      ";
    String CPN_F_F_TX_INFO = "BP-F-F-TX-INFO      ";
    String CPN_F_F_RM_TX = "BP-RM-CREATE-RECORD ";
    String CPN_F_F_CON_CHG_INFO = "BP-F-F-CON-CHG-INFO ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_ITM = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCTCALF BPCTCALF = new BPCTCALF();
    BPCFFCON BPCFFCON = new BPCFFCON();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    SCCGWA SCCGWA;
    BPB1107_AWA_1107 BPB1107_AWA_1107;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCGCFEE BPCGCFEE;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1195 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1107_AWA_1107>");
        BPB1107_AWA_1107 = (BPB1107_AWA_1107) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B015_SET_FEE_INFO();
        B020_CALL_FEE();
        B030_CHG_FEE_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B015_SET_FEE_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '0';
        if (BPB1107_AWA_1107.CHG_FLG == '0') {
            BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = BPB1107_AWA_1107.FEE_AC;
        } else {
            if (BPB1107_AWA_1107.CHG_FLG == '1') {
                BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = "" + BPB1107_AWA_1107.ACCT_CD;
                JIBS_tmp_int = BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC.length();
                for (int i=0;i<12-JIBS_tmp_int;i++) BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = "0" + BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC;
            }
        }
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = "HKD";
        S000_CALL_BPZFFTXI();
    }
    public void B020_CALL_FEE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTCALF);
        BPCTCALF.INPUT_AREA.FUNC_CODE = 'T';
        BPCTCALF.INPUT_AREA.TX_CCY = "HKD";
        BPCTCALF.INPUT_AREA.CPN_ID = CPN_F_F_RM_TX;
        S000_CALL_BPZFCALF();
    }
    public void B030_CHG_FEE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFCON);
        for (WS_ITM = 1; WS_ITM <= 20 
            && BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_ITM-1].FEE_CODE.trim().length() != 0; WS_ITM += 1) {
            BPCFFCON.FEE_INFO.FEE_CNT += 1;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].CHG_FLG = BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_ITM-1].CHG_FLG;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].FEE_CODE = BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_ITM-1].FEE_CODE;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].FEE_CCY = BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_ITM-1].FEE_CCY;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].FEE_BAS = BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_ITM-1].FEE_BAS;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].FEE_AMT = BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_ITM-1].FEE_AMT;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].CHG_CCY = BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_ITM-1].CHG_CCY;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].CHG_BAS = BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_ITM-1].CHG_BAS;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_ITM-1].CHG_AMT = BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_ITM-1].CHG_AMT;
        }
        S000_CALL_BPZFFCON();
    }
    public void S000_CALL_BPZFFTXI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_F_TX_INFO, BPCFFTXI);
        if (BPCFFTXI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFTXI.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFCALF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_F_CAL_FEE, BPCTCALF);
    }
    public void S000_CALL_BPZFFCON() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_F_CON_CHG_INFO, BPCFFCON);
        if (BPCFFCON.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFCON.RC);
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
