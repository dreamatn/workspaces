package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3609 {
    String JIBS_NumStr;
    String K_OUTPUT_FMT = "BP189";
    char K_SC_STS = '0';
    String CPN_S_DRAW_SC = "BP-S-DRAW-SC      ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG      ";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String CPN_R_BPTVHPB_MTN = "BP-R-BPTVHPB-MTN  ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_TEMP_PLBOX_NO = " ";
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
    SCCGWA SCCGWA;
    BPB3600_AWA_3600 BPB3600_AWA_3600;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT3609 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3600_AWA_3600>");
        BPB3600_AWA_3600 = (BPB3600_AWA_3600) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSTLSC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_ADD_RECORD();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB3600_AWA_3600.SC_DATE);
        CEP.TRC(SCCGWA, BPB3600_AWA_3600.SC_TYPE);
        CEP.TRC(SCCGWA, BPB3600_AWA_3600.CODE_NO);
        CEP.TRC(SCCGWA, BPB3600_AWA_3600.MC_NO);
        IBS.init(SCCGWA, BPCPQORG);
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        if (BPCFTLRQ.INFO.TX_LVL == '0') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_DIRECTOR_TLR;
            WS_FLD_NO = 0;
            S000_ERR_MSG_PROC();
        }
        if (BPB3600_AWA_3600.SC_DATE == ' ') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_CONNOT_SPACE;
            WS_FLD_NO = BPB3600_AWA_3600.SC_DATE_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB3600_AWA_3600.SC_TYPE == ' ') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TYPE_CONNOT_SPACE;
            WS_FLD_NO = BPB3600_AWA_3600.SC_TYPE_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB3600_AWA_3600.CODE_NO.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CODE_NO_CONNOT_SPACE;
            WS_FLD_NO = BPB3600_AWA_3600.CODE_NO_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB3600_AWA_3600.MC_NO.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MC_NO_CONNOT_SPACE;
            WS_FLD_NO = BPB3600_AWA_3600.MC_NO_NO;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPRVHPB);
        IBS.init(SCCGWA, BPCRVHPB);
        BPRVHPB.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRVHPB.CUR_TLR = SCCGWA.COMM_AREA.TL_ID;
        JIBS_NumStr = "" + 0;
        BPRVHPB.POLL_BOX_IND = JIBS_NumStr.charAt(0);
        BPRVHPB.RELATE_FLG = 'Y';
        BPRVHPB.STS = 'N';
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
    public void B020_ADD_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSTLSC);
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZSTLSC();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCSTLSC.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCSTLSC.POOL_BOX_NO = WS_TEMP_PLBOX_NO;
        BPCSTLSC.SC_DATE = BPB3600_AWA_3600.SC_DATE;
        BPCSTLSC.SC_TYPE = BPB3600_AWA_3600.SC_TYPE;
        BPCSTLSC.SC_STS = K_SC_STS;
        BPCSTLSC.CODE_NO = BPB3600_AWA_3600.CODE_NO;
        BPCSTLSC.MC_NO = BPB3600_AWA_3600.MC_NO;
    }
    public void S000_CALL_BPZSTLSC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_DRAW_SC, BPCSTLSC);
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
