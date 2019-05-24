package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3670 {
    String K_OUTPUT_FMT = "BP189";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
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
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    BPB3601_AWA_3601 BPB3601_AWA_3601;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT3670 return!");
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
        B020_GETPOOL_BOXNO_PROC();
        B030_KEEPING_CARD_NHIS();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_GETPOOL_BOXNO_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRVHPB);
        IBS.init(SCCGWA, BPCRVHPB);
        BPRVHPB.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRVHPB.CUR_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRVHPB.POLL_BOX_IND = BPB3601_AWA_3601.VB_FLG;
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
    public void B030_KEEPING_CARD_NHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = WS_TEMP_PLBOX_NO;
        S000_CALL_BPZPNHIS();
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
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
