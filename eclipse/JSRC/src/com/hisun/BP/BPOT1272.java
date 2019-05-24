package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.DC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1272 {
    String CPN_S_BPZFFCLT = "BP-F-S-FEE-COLLECT";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_CNT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSFCLT BPCSFCLT = new BPCSFCLT();
    DCCPACTY DCCPACTY = new DCCPACTY();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB1270_AWA_1270 BPB1270_AWA_1270;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1272 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1270_AWA_1270>");
        BPB1270_AWA_1270 = (BPB1270_AWA_1270) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSFCLT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_ADD_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB1270_AWA_1270.CLT_TYPE);
        CEP.TRC(SCCGWA, BPB1270_AWA_1270.SGN_TYPE);
        CEP.TRC(SCCGWA, BPB1270_AWA_1270.SGN_AC);
        CEP.TRC(SCCGWA, BPB1270_AWA_1270.CHG_AC);
        if (BPB1270_AWA_1270.CLT_TYPE == ' ') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FE_SGN_TYPE_ERR;
            if (BPB1270_AWA_1270.CLT_TYPE == ' ') WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(""+BPB1270_AWA_1270.CLT_TYPE);
            S000_ERR_MSG_PROC_CONTINUE();
        }
        S000_ERR_MSG_PROC_LAST();
    }
    public void B020_ADD_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSFCLT);
        BPCSFCLT.CLT_TYPE = BPB1270_AWA_1270.CLT_TYPE;
        BPCSFCLT.SGN_TYPE = BPB1270_AWA_1270.SGN_TYPE;
        BPCSFCLT.SGN_CINO = BPB1270_AWA_1270.SGN_CINO;
        BPCSFCLT.SGN_AC = BPB1270_AWA_1270.SGN_AC;
        BPCSFCLT.EFF_DATE = BPB1270_AWA_1270.EFF_DATE;
        BPCSFCLT.EXP_DATE = BPB1270_AWA_1270.EXP_DATE;
        BPCSFCLT.CHG_AC = BPB1270_AWA_1270.CHG_AC;
        BPCSFCLT.CCY = BPB1270_AWA_1270.CCY;
        BPCSFCLT.CAL_CYC = BPB1270_AWA_1270.CAL_CYC;
        BPCSFCLT.PERD_CNT = BPB1270_AWA_1270.PERD_CNT;
        BPCSFCLT.HOLI_MTH = BPB1270_AWA_1270.HOLI_MTH;
        BPCSFCLT.HLD_NO = BPB1270_AWA_1270.HLD_NO;
        BPCSFCLT.REMARK = BPB1270_AWA_1270.REMARK;
        BPCSFCLT.SGN_RNG = BPB1270_AWA_1270.SGN_RNG;
        for (WS_CNT = 1; WS_CNT <= 20; WS_CNT += 1) {
            BPCSFCLT.FEE_CDS[WS_CNT-1].FEE_CD1 = BPB1270_AWA_1270.FEE_CDS[WS_CNT-1].FEE_CD1;
        }
        CEP.TRC(SCCGWA, BPCSFCLT);
        BPCSFCLT.FUNC = 'A';
        S00_CALL_BPZFFCLT();
    }
    public void S00_CALL_BPZFFCLT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BPZFFCLT, BPCSFCLT);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        CEP.TRC(SCCGWA, DCCPACTY.RC);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
        }
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
