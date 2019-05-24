package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1262 {
    String CPN_S_BPZFFPDT = "BP-F-S-FE-UNCHG-DTL";
    char WK_FREE_STS = '1';
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_PRC_STS = ' ';
    char WS_CHG_STS = ' ';
    char WS_FUNC = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSFPDT BPCSFPDT = new BPCSFPDT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB1260_AWA_1260 BPB1260_AWA_1260;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1262 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1260_AWA_1260>");
        BPB1260_AWA_1260 = (BPB1260_AWA_1260) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSFPDT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_MOD_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        WS_FUNC = BPB1260_AWA_1260.FUNC;
        if ((WS_FUNC != '0' 
            && WS_FUNC != '1')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FE_FUNC_ERR;
            WS_FLD_NO = BPB1260_AWA_1260.FUNC_NO;
            S000_ERR_MSG_PROC();
        }
        if ((BPB1260_AWA_1260.FDT_TYP != '2' 
            && BPB1260_AWA_1260.FDT_TYP != '0') 
            && BPB1260_AWA_1260.FUNC == '0') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UNCHG_FEE_DTL_ONLY;
            WS_FLD_NO = BPB1260_AWA_1260.FDT_TYP_NO;
            S000_ERR_MSG_PROC();
        }
        WS_CHG_STS = BPB1260_AWA_1260.CHG_STS;
        if ((WS_CHG_STS != '0' 
            && WS_CHG_STS != '1' 
            && WS_CHG_STS != '2' 
            && WS_CHG_STS != '3')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FE_CHG_STS_ERR;
            WS_FLD_NO = BPB1260_AWA_1260.CHG_STS_NO;
            S000_ERR_MSG_PROC();
        }
        WS_PRC_STS = BPB1260_AWA_1260.PRC_STS;
        if ((WS_PRC_STS != '0' 
            && WS_PRC_STS != '1')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FE_PRC_STS_ERR;
            WS_FLD_NO = BPB1260_AWA_1260.PRC_STS_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_MOD_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSFPDT);
        BPCSFPDT.FUNC_PROC = BPB1260_AWA_1260.FUNC;
        BPCSFPDT.FDT_TYP = BPB1260_AWA_1260.FDT_TYP;
        BPCSFPDT.TRT_DT = BPB1260_AWA_1260.TRT_DT;
        BPCSFPDT.JRN_NO = BPB1260_AWA_1260.JRN_NO;
        BPCSFPDT.JRN_SEQ = BPB1260_AWA_1260.JRN_SEQ;
        BPCSFPDT.PRC_STS = BPB1260_AWA_1260.PRC_STS;
        BPCSFPDT.CHG_STS = BPB1260_AWA_1260.CHG_STS;
        CEP.TRC(SCCGWA, BPB1260_AWA_1260.TRT_DT);
        CEP.TRC(SCCGWA, BPB1260_AWA_1260.JRN_NO);
        CEP.TRC(SCCGWA, BPB1260_AWA_1260.JRN_SEQ);
        CEP.TRC(SCCGWA, BPB1260_AWA_1260.FUNC);
        if (BPB1260_AWA_1260.FUNC == '0') {
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                BPCSFPDT.FUNC_PROC = 'P';
            } else {
                BPCSFPDT.FUNC_PROC = 'R';
            }
        } else {
            BPCSFPDT.FUNC_PROC = 'F';
            BPCSFPDT.PRC_STS = WK_FREE_STS;
        }
        S00_CALL_BPZFFPDT();
    }
    public void S00_CALL_BPZFFPDT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BPZFFPDT, BPCSFPDT);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
