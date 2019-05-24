package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3639 {
    String K_OUTPUT_FMT = "BP189";
    String CPN_S_SC_MOV_OUT = "BP-S-SC-MOV-OUT   ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG      ";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String CPN_R_BPTVHPB_MTN = "BP-R-BPTVHPB-MTN  ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_TEMP_PLBOX_NO = " ";
    int WS_CNT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRVHPB BPRVHPB = new BPRVHPB();
    BPCRVHPB BPCRVHPB = new BPCRVHPB();
    BPCSTLSC BPCSTLSC = new BPCSTLSC();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCSSCMO BPCSSCMO = new BPCSSCMO();
    SCCGWA SCCGWA;
    BPB3601_AWA_3601 BPB3601_AWA_3601;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT3639 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3601_AWA_3601>");
        BPB3601_AWA_3601 = (BPB3601_AWA_3601) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_QUR_RECORD();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB3601_AWA_3601.BR_CHG);
        CEP.TRC(SCCGWA, BPB3601_AWA_3601.FUNC);
        CEP.TRC(SCCGWA, BPB3601_AWA_3601.DATA[1-1].SC_DATE);
        CEP.TRC(SCCGWA, BPB3601_AWA_3601.DATA[1-1].SC_TYPE);
        CEP.TRC(SCCGWA, BPB3601_AWA_3601.DATA[1-1].CODE_NO);
        CEP.TRC(SCCGWA, BPB3601_AWA_3601.DATA[1-1].MC_NO);
        IBS.init(SCCGWA, BPCPQORG);
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        if (BPCFTLRQ.INFO.TX_LVL == '0') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_DIRECTOR_TLR;
            WS_FLD_NO = 0;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPRVHPB);
        IBS.init(SCCGWA, BPCRVHPB);
        BPRVHPB.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRVHPB.CUR_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRVHPB.POLL_BOX_IND = BPB3601_AWA_3601.VB_FLG;
        BPRVHPB.RELATE_FLG = 'Y';
        BPRVHPB.STS = 'N';
        CEP.TRC(SCCGWA, BPRVHPB.BR);
        CEP.TRC(SCCGWA, BPRVHPB.CUR_TLR);
        CEP.TRC(SCCGWA, BPRVHPB.POLL_BOX_IND);
        BPCRVHPB.INFO.FUNC = 'F';
        S000_CALL_BPZRVHPB();
        CEP.TRC(SCCGWA, BPRVHPB.POLL_BOX_IND);
        CEP.TRC(SCCGWA, BPRVHPB.KEY.POOL_BOX_NO);
        if (BPCRVHPB.RETURN_INFO == 'F') {
            WS_TEMP_PLBOX_NO = BPRVHPB.KEY.POOL_BOX_NO;
            CEP.TRC(SCCGWA, WS_TEMP_PLBOX_NO);
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_BV_TLR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_QUR_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSSCMO);
        if (BPB3601_AWA_3601.FUNC == 'N') {
            R000_TRANS_DATA_PARAMETER();
            BPCSSCMO.FLG = 'N';
            CEP.TRC(SCCGWA, BPCSSCMO.FLG);
        } else if (BPB3601_AWA_3601.FUNC == 'Y') {
            BPCSSCMO.FLG = 'Y';
            BPCSSCMO.BR_CHG = BPB3601_AWA_3601.BR_CHG;
            BPCSSCMO.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCSSCMO.TLR = SCCGWA.COMM_AREA.TL_ID;
            BPCSSCMO.TLR_CHG = BPB3601_AWA_3601.TLR_CHG;
            BPCSSCMO.PL_BOX_NO = WS_TEMP_PLBOX_NO;
            BPCSSCMO.MOV_DT = BPB3601_AWA_3601.MOV_DT;
            BPCSSCMO.CONF_NO = BPB3601_AWA_3601.CONF_NO;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
        S000_CALL_BPZSSCMO();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCSSCMO.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCSSCMO.BR_CHG = BPB3601_AWA_3601.BR_CHG;
        BPCSSCMO.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSSCMO.TLR_CHG = BPB3601_AWA_3601.TLR_CHG;
        BPCSSCMO.PL_BOX_NO = WS_TEMP_PLBOX_NO;
        BPCSSCMO.MOV_DT = BPB3601_AWA_3601.MOV_DT;
        BPCSSCMO.CONF_NO = BPB3601_AWA_3601.CONF_NO;
        CEP.TRC(SCCGWA, BPCSSCMO.CONF_NO);
        CEP.TRC(SCCGWA, BPCSSCMO.MOV_DT);
        for (WS_CNT = 1; WS_CNT <= 10 
            && BPB3601_AWA_3601.DATA[WS_CNT-1].CODE_NO.trim().length() != 0; WS_CNT += 1) {
            BPCSSCMO.DATA_INFO[WS_CNT-1].SC_DATE = BPB3601_AWA_3601.DATA[WS_CNT-1].SC_DATE;
            BPCSSCMO.DATA_INFO[WS_CNT-1].MC_NO = BPB3601_AWA_3601.DATA[WS_CNT-1].MC_NO;
            BPCSSCMO.DATA_INFO[WS_CNT-1].SC_TYPE = BPB3601_AWA_3601.DATA[WS_CNT-1].SC_TYPE;
            BPCSSCMO.DATA_INFO[WS_CNT-1].CODE_NO = BPB3601_AWA_3601.DATA[WS_CNT-1].CODE_NO;
            CEP.TRC(SCCGWA, BPB3601_AWA_3601.DATA[WS_CNT-1].CODE_NO);
            CEP.TRC(SCCGWA, BPCSSCMO.DATA_INFO[WS_CNT-1].CODE_NO);
            BPCSSCMO.DATA_INFO[WS_CNT-1].SC_STS = BPB3601_AWA_3601.DATA[WS_CNT-1].SC_STS;
        }
    }
    public void S000_CALL_BPZSSCMO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_SC_MOV_OUT, BPCSSCMO);
        if (BPCSSCMO.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSSCMO.RC);
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
    public void S000_CALL_BPZRVHPB() throws IOException,SQLException,Exception {
        BPCRVHPB.INFO.POINTER = BPRVHPB;
        BPCRVHPB.INFO.LEN = 152;
        IBS.CALLCPN(SCCGWA, CPN_R_BPTVHPB_MTN, BPCRVHPB);
        if (BPCRVHPB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRVHPB.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_INF_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            WS_FLD_NO = 0;
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
