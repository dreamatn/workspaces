package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1171 {
    String CPN_F_F_CAL_FEE = "BP-F-F-CAL-FEE      ";
    String CPN_F_F_TX_INFO = "BP-F-F-TX-INFO      ";
    String K_BATCH_CHG_CPN = "BP-BBBBBBBBBBBBBBBBB";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    Object WS_POINT;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCTCALF BPCTCALF = new BPCTCALF();
    BPCGCFEE BPCGWFEE = new BPCGCFEE();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    SCCGWA SCCGWA;
    BPCGPFEE BPCGPFEE;
    BPCGCFEE BPCGCFEE;
    BPCGCFEE BPCGTFEE;
    BPB1107_AWA_1107 BPB1107_AWA_1107;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1171 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1107_AWA_1107>");
        BPB1107_AWA_1107 = (BPB1107_AWA_1107) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCGPFEE = (BPCGPFEE) GWA_BP_AREA.FEE_AREA.FEE_PARM_PTR;
        BPCGCFEE = new BPCGCFEE();
        IBS.init(SCCGWA, BPCGCFEE);
        IBS.CPY2CLS(SCCGWA, GWA_BP_AREA.FEE_AREA.FEE_DATA_PTR, BPCGCFEE);
        CEP.TRC(SCCGWA, BPCGCFEE);
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.SEND_FLG);
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_CNT);
        BPCGCFEE.FEE_DATA.FEE_CNT = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B015_SET_FEE_INFO();
        B020_CALL_FEE();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB1107_AWA_1107.SVR_CD);
        CEP.TRC(SCCGWA, BPB1107_AWA_1107.PRD_CODE);
        CEP.TRC(SCCGWA, BPB1107_AWA_1107.AC_NO);
        CEP.TRC(SCCGWA, BPB1107_AWA_1107.CI_NO);
        CEP.TRC(SCCGWA, BPB1107_AWA_1107.AC_TYP);
        CEP.TRC(SCCGWA, BPB1107_AWA_1107.FEE_AC);
        CEP.TRC(SCCGWA, BPB1107_AWA_1107.CHG_CCY);
        CEP.TRC(SCCGWA, BPB1107_AWA_1107.FEE_AMT);
        CEP.TRC(SCCGWA, BPB1107_AWA_1107.ACC_CNT);
        if (BPB1107_AWA_1107.SVR_CD.trim().length() == 0 
            || BPB1107_AWA_1107.SVR_CD.equalsIgnoreCase("0") 
            || BPB1107_AWA_1107.SVR_CD.charAt(0) == 0X00) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FSVR_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (BPB1107_AWA_1107.PRD_CODE.trim().length() == 0 
            || BPB1107_AWA_1107.PRD_CODE.equalsIgnoreCase("0") 
            || BPB1107_AWA_1107.PRD_CODE.charAt(0) == 0X00) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PRD_CODE_MUSTINPUT;
            S000_ERR_MSG_PROC();
        }
        if (BPB1107_AWA_1107.AC_NO.trim().length() == 0 
            || BPB1107_AWA_1107.AC_NO.equalsIgnoreCase("0") 
            || BPB1107_AWA_1107.AC_NO.charAt(0) == 0X00) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AC_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (BPB1107_AWA_1107.CI_NO.trim().length() == 0 
            || BPB1107_AWA_1107.CI_NO.equalsIgnoreCase("0") 
            || BPB1107_AWA_1107.CI_NO.charAt(0) == 0X00) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CI_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (BPB1107_AWA_1107.AC_TYP == ' ') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CHG_AC_TYP_M_I;
            S000_ERR_MSG_PROC();
        }
        if (BPB1107_AWA_1107.FEE_AC.trim().length() == 0 
            || BPB1107_AWA_1107.FEE_AC.equalsIgnoreCase("0")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CHG_AC_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (BPB1107_AWA_1107.CHG_CCY.trim().length() == 0 
            || BPB1107_AWA_1107.CHG_CCY.equalsIgnoreCase("0")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CHG_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B015_SET_FEE_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        if (BPB1107_AWA_1107.VAL_DT == 0) {
            BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        } else {
            BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = BPB1107_AWA_1107.VAL_DT;
        }
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = BPB1107_AWA_1107.AC_TYP;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = BPB1107_AWA_1107.FEE_AC;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = BPB1107_AWA_1107.CHG_CCY;
        BPCFFTXI.TX_DATA.SVR_CD = BPB1107_AWA_1107.SVR_CD;
        S000_CALL_BPZFFTXI();
    }
    public void B020_CALL_FEE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTCALF);
        BPCTCALF.INPUT_AREA.FUNC_CODE = 'C';
        BPCTCALF.INPUT_AREA.PROD_CODE = BPB1107_AWA_1107.PRD_CODE;
        BPCTCALF.INPUT_AREA.TX_AC = BPB1107_AWA_1107.AC_NO;
        BPCTCALF.INPUT_AREA.TX_CI = BPB1107_AWA_1107.CI_NO;
        BPCTCALF.INPUT_AREA.TX_CCY = BPB1107_AWA_1107.FFEE_CCY;
        BPCTCALF.INPUT_AREA.TX_AMT = BPB1107_AWA_1107.FEE_AMT;
        BPCTCALF.INPUT_AREA.TX_CNT = BPB1107_AWA_1107.ACC_CNT;
        BPCTCALF.INPUT_AREA.CPN_ID = K_BATCH_CHG_CPN;
        BPCTCALF.INPUT_AREA.POINT_LEN = 0;
        S000_CALL_BPZFCALF();
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
