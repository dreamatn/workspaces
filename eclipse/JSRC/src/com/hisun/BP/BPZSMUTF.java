package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSMUTF {
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZSMUTF";
    String CPN_F_F_CON_CHG_INFO = "BP-F-F-CON-CHG-INFO ";
    String CPN_F_F_TX_INFO = "BP-F-F-TX-INFO      ";
    String WS_ERR_MSG = " ";
    short WS_CNT = 0;
    BPZSMUTF_WS_FEE_INFO WS_FEE_INFO = new BPZSMUTF_WS_FEE_INFO();
    char WS_AC_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPCFFCON BPCFFCON = new BPCFFCON();
    SCCGWA SCCGWA;
    BPCGCFEE BPCGCFEE;
    BPCGPFEE BPCGPFEE;
    BPCSMUTF BPCSMUTF;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSMUTF BPCSMUTF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSMUTF = BPCSMUTF;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSMUTF return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCGCFEE = new BPCGCFEE();
        IBS.init(SCCGWA, BPCGCFEE);
        IBS.CPY2CLS(SCCGWA, GWA_BP_AREA.FEE_AREA.FEE_DATA_PTR, BPCGCFEE);
        BPCGPFEE = (BPCGPFEE) GWA_BP_AREA.FEE_AREA.FEE_PARM_PTR;
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCSMUTF.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B005_CHECK_INPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCFFCON);
        IBS.init(SCCGWA, BPCFFTXI);
        B010_TRANS_PARM_DATAS();
        if (pgmRtn) return;
        for (WS_CNT = 1; WS_CNT <= BPCSMUTF.FEE_CNT 
            && SCCGWA.COMM_AREA.FEE_DATA_IND != 'Y'; WS_CNT += 1) {
            CEP.TRC(SCCGWA, "ENTER PERFORM ");
            CEP.TRC(SCCGWA, WS_CNT);
            CEP.TRC(SCCGWA, BPCSMUTF.FEE_INFO[WS_CNT-1].FEE_CODE);
            if (BPCSMUTF.FEE_INFO[WS_CNT-1].FEE_CODE.trim().length() > 0) {
                B010_TRANS_FEE_DATAS();
                if (pgmRtn) return;
            }
        }
        if (BPCFFCON.FEE_INFO.FEE_CNT > 0) {
            S000_CALL_BPZFFCON();
            if (pgmRtn) return;
        }
    }
    public void B010_TRANS_FEE_DATAS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CNT);
        BPCFFCON.FEE_INFO.FEE_CNT += 1;
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_CNT);
        BPCFFCON.FEE_INFO.FEE_INFO1[BPCFFCON.FEE_INFO.FEE_CNT-1].CHG_FLG = '0';
        BPCFFCON.FEE_INFO.FEE_INFO1[BPCFFCON.FEE_INFO.FEE_CNT-1].FEE_CCY = BPCSMUTF.CHG_CCY;
        BPCFFCON.FEE_INFO.FEE_INFO1[BPCFFCON.FEE_INFO.FEE_CNT-1].CHG_AC_TY = BPCSMUTF.CHG_AC_TY;
        BPCFFCON.FEE_INFO.FEE_INFO1[BPCFFCON.FEE_INFO.FEE_CNT-1].CHG_AC = BPCSMUTF.CHG_AC;
        BPCFFCON.FEE_INFO.FEE_INFO1[BPCFFCON.FEE_INFO.FEE_CNT-1].CHG_CCY = BPCSMUTF.CHG_CCY;
        BPCFFCON.FEE_INFO.FEE_INFO1[BPCFFCON.FEE_INFO.FEE_CNT-1].TO_ACCT_CEN = BPCSMUTF.FEE_INFO[WS_CNT-1].CHG_BR;
        BPCFFCON.FEE_INFO.FEE_INFO1[BPCFFCON.FEE_INFO.FEE_CNT-1].FEE_CODE = BPCSMUTF.FEE_INFO[WS_CNT-1].FEE_CODE;
        BPCFFCON.FEE_INFO.FEE_INFO1[BPCFFCON.FEE_INFO.FEE_CNT-1].FEE_AMT = BPCSMUTF.FEE_INFO[WS_CNT-1].CHG_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[BPCFFCON.FEE_INFO.FEE_CNT-1].CHG_BAS = BPCSMUTF.FEE_INFO[WS_CNT-1].CHG_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[BPCFFCON.FEE_INFO.FEE_CNT-1].CHG_AMT = BPCSMUTF.FEE_INFO[WS_CNT-1].CHG_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[BPCFFCON.FEE_INFO.FEE_CNT-1].ADJ_AMT = BPCSMUTF.FEE_INFO[WS_CNT-1].CHG_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[BPCFFCON.FEE_INFO.FEE_CNT-1].AMO_FLG = BPCSMUTF.FEE_INFO[WS_CNT-1].AMO_FLG;
        BPCFFCON.FEE_INFO.FEE_INFO1[BPCFFCON.FEE_INFO.FEE_CNT-1].AMO_STDT = BPCSMUTF.FEE_INFO[WS_CNT-1].AMO_STRDT;
        BPCFFCON.FEE_INFO.FEE_INFO1[BPCFFCON.FEE_INFO.FEE_CNT-1].AMO_EDDT = BPCSMUTF.FEE_INFO[WS_CNT-1].AMO_ENDDT;
        BPCFFCON.FEE_INFO.REMARK = BPCSMUTF.REMARK;
        BPCFFCON.FEE_INFO.VAT_AMT = BPCSMUTF.VAT_AMT;
    }
    public void B010_TRANS_PARM_DATAS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BEFORE TRANS DATA");
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFTXI.TX_DATA.FEE_CTRT = BPCSMUTF.CMMT_NO;
        BPCFFTXI.TX_DATA.CLT_TYPE = BPCSMUTF.CLT_TYPE;
        BPCFFTXI.TX_DATA.DRMCR_FLG = BPCSMUTF.DRMCR_FLG;
        BPCFFTXI.TX_DATA.PROC_DT = BPCSMUTF.PROC_DT;
        BPCFFTXI.TX_DATA.CI_NO = BPCSMUTF.CHG_CI_NO;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = BPCSMUTF.CHG_AC_TY;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = BPCSMUTF.CHG_AC;
        BPCFFTXI.TX_DATA.CARD_PSBK_NO = BPCSMUTF.CHG_AC;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = BPCSMUTF.CHG_CCY;
        BPCFFTXI.TX_DATA.CCY_TYPE = BPCSMUTF.CHG_CCY_TYPE;
        if (BPCSMUTF.CLT_TYPE != '1') {
            BPCFFTXI.TX_DATA.BSNS_NO = BPCSMUTF.BSNS_NO;
        }
        BPCFFTXI.TX_DATA.HLD_NO = BPCSMUTF.HLD_NO;
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.BSNS_NO);
        BPCFFTXI.TX_DATA.BAT_OTRT_DT = BPCSMUTF.BAT_ORG_DT;
        BPCFFTXI.TX_DATA.BAT_OTRT_JRN = BPCSMUTF.BAT_ORG_JRN;
        BPCFFTXI.TX_DATA.BAT_OTRT_SEQ = (short) BPCSMUTF.BAT_ORG_SEQ;
        S000_CALL_BPZFFTXI();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZFFTXI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_F_TX_INFO, BPCFFTXI);
        if (BPCFFTXI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFTXI.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFFCON() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_F_CON_CHG_INFO, BPCFFCON);
        if (BPCFFCON.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFCON.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void B005_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_CNT);
        CEP.TRC(SCCGWA, BPCSMUTF.FEE_CNT);
        if (BPCSMUTF.FEE_CNT == 0) {
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCSMUTF.CHG_AC_TY);
        if (BPCSMUTF.CHG_AC_TY != '0' 
            && BPCSMUTF.CHG_AC_TY != '4' 
            && BPCSMUTF.CHG_AC_TY != '5') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CHG_MTH_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCSMUTF.CHG_AC);
        if (BPCSMUTF.CHG_AC_TY == '0' 
            && BPCSMUTF.CHG_AC.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CHG_AC_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCSMUTF.CHG_AC);
        CEP.TRC(SCCGWA, BPCSMUTF.CARD_PSBK_NO);
        if (BPCSMUTF.FEE_CNT + BPCGCFEE.FEE_DATA.FEE_CNT > 20) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_OVER_MAX_CNT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
