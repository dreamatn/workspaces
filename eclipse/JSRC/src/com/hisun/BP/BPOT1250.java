package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.DC.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1250 {
    String CPN_F_FEE_HIS_DETAIL = "BP-F-FEE-HIS-DETAIL";
    String CPN_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY  ";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCBINF SCCBINF = new SCCBINF();
    BPCFFEDT BPCFFEDT = new BPCFFEDT();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    DCCPACTY DCCPACTY = new DCCPACTY();
    CICACCU CICACCU = new CICACCU();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB1250_AWA_1250 BPB1250_AWA_1250;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1250 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1250_AWA_1250>");
        BPB1250_AWA_1250 = (BPB1250_AWA_1250) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_BROWSE_HIST_DETAIL();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB1250_AWA_1250.CHG_AC.trim().length() == 0 
            && BPB1250_AWA_1250.TX_TLR.trim().length() == 0 
            && BPB1250_AWA_1250.BSNS_NO.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INP_AC_OR_TLR_BSNS;
            WS_FLD_NO = BPB1250_AWA_1250.CHG_AC_NO;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPB1250_AWA_1250.CHG_AC);
        if (BPB1250_AWA_1250.CHG_AC.trim().length() > 0) {
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = BPB1250_AWA_1250.CHG_AC;
            S000_CALL_CIZACCU();
        }
        if (BPB1250_AWA_1250.TX_TLR.trim().length() > 0) {
            IBS.init(SCCGWA, BPCFTLRQ);
            BPCFTLRQ.INFO.TLR = BPB1250_AWA_1250.TX_TLR;
            S000_CALL_BPZFTLRQ();
        }
        if (BPB1250_AWA_1250.STR_DT == 0) {
            BPB1250_AWA_1250.STR_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (BPB1250_AWA_1250.END_DT == 0) {
            BPB1250_AWA_1250.END_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (BPB1250_AWA_1250.STR_DT > BPB1250_AWA_1250.END_DT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EFFDT_GT_EXPDT;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPB1250_AWA_1250.STR_DT);
        CEP.TRC(SCCGWA, BPB1250_AWA_1250.END_DT);
    }
    public void B020_BROWSE_HIST_DETAIL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFEDT);
        BPCFFEDT.FUNC = 'B';
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZFFEDT();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCFFEDT.AC = BPB1250_AWA_1250.CHG_AC;
        BPCFFEDT.TX_TLR = BPB1250_AWA_1250.TX_TLR;
        BPCFFEDT.STR_DT = BPB1250_AWA_1250.STR_DT;
        BPCFFEDT.END_DT = BPB1250_AWA_1250.END_DT;
        BPCFFEDT.PAGE_NUM = BPB1250_AWA_1250.PAGE_NUM;
        if (BPB1250_AWA_1250.PAGE_ROW != 0) {
            BPCFFEDT.PAGE_ROW = BPB1250_AWA_1250.PAGE_ROW;
        } else {
            BPCFFEDT.PAGE_ROW = 10;
        }
        BPCFFEDT.BSNS_NO = BPB1250_AWA_1250.BSNS_NO;
        CEP.TRC(SCCGWA, BPB1250_AWA_1250.PAGE_NUM);
    }
    public void S000_CALL_BPZFFEDT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_FEE_HIS_DETAIL, BPCFFEDT);
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_TLR_INF_QUERY;
        SCCCALL.COMMAREA_PTR = BPCFTLRQ;
        SCCCALL.ERR_FLDNO = BPB1250_AWA_1250.TX_TLR;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            WS_FLD_NO = BPB1250_AWA_1250.TX_TLR_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "DC-INQ-AC-INF";
        SCCCALL.COMMAREA_PTR = DCCPACTY;
        SCCCALL.ERRHDL_FLG = 'Y';
        IBS.CALL(SCCGWA, SCCCALL);
        CEP.TRC(SCCGWA, DCCPACTY.RC.RC_CODE);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU         ", CICACCU);
        if (CICACCU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INFO, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
