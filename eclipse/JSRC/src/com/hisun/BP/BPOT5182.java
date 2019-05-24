package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5182 {
    String CPN_GEN_TQP_MKTR = "BP-GEN-TQP-MKTR    ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_INQ_PUB_PARM = "BP-PARM-READ      ";
    String CPN_SET_SUB_TXN = "SC-SET-SUBS-TRANS ";
    short WS_I = 0;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPOT5182_WS_OUT_DATA WS_OUT_DATA = new BPOT5182_WS_OUT_DATA();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPREXRT BPREXRT = new BPREXRT();
    BPCQCCY BPCQCCY = new BPCQCCY();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCGTK BPCGTK = new BPCGTK();
    BPRPRMT BPRPRMT = new BPRPRMT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB5180_AWA_5180 BPB5180_AWA_5180;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT5182 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5180_AWA_5180>");
        BPB5180_AWA_5180 = (BPB5180_AWA_5180) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_ADD_PROC();
        if (BPB5180_AWA_5180.CMPL_FLG == 'Y') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 0;
            if ("0000".trim().length() == 0) SCCSUBS.TR_CODE = 0;
            else SCCSUBS.TR_CODE = Short.parseShort("0000");
            S000_CALL_SCZSUBS();
            IBS.init(SCCGWA, SCCFMT);
            IBS.init(SCCGWA, WS_OUT_DATA);
            WS_OUT_DATA.WS_EXR_TYPE = BPB5180_AWA_5180.EXR_TYPE;
            WS_OUT_DATA.WS_FWD_TENOR = BPB5180_AWA_5180.FWD_TENR;
            WS_OUT_DATA.WS_BASE_TYPE = BPB5180_AWA_5180.BASE_TYP;
            WS_OUT_DATA.WS_BASE_CCY = BPB5180_AWA_5180.BASE_CCY;
            SCCFMT.FMTID = "BP279";
            SCCFMT.DATA_PTR = WS_OUT_DATA;
            SCCFMT.DATA_LEN = 11;
            IBS.FMT(SCCGWA, SCCFMT);
        } else {
            B030_BRW_PROC();
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_ADD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCGTK);
        BPCGTK.DATA.FUNC = 'A';
        CEP.TRC(SCCGWA, BPB5180_AWA_5180.EXR_TYPE);
        CEP.TRC(SCCGWA, BPB5180_AWA_5180.FWD_TENR);
        CEP.TRC(SCCGWA, BPB5180_AWA_5180.BASE_TYP);
        CEP.TRC(SCCGWA, BPB5180_AWA_5180.BASE_CCY);
        BPCGTK.DATA.EXR_TYPE = BPB5180_AWA_5180.EXR_TYPE;
        BPCGTK.DATA.TENOR = BPB5180_AWA_5180.FWD_TENR;
        BPCGTK.DATA.BASE_TYPE = BPB5180_AWA_5180.BASE_TYP;
        BPCGTK.DATA.BASE_CCY = BPB5180_AWA_5180.BASE_CCY;
        BPCGTK.DATA.OUT_REC_CNT = BPB5180_AWA_5180.REC_CNT;
        for (WS_I = 1; WS_I <= BPB5180_AWA_5180.REC_CNT; WS_I += 1) {
            BPCGTK.DATA.CCY_INFO[WS_I-1].CCY = BPB5180_AWA_5180.RATE_INF[WS_I-1].CCY;
            BPCGTK.DATA.CCY_INFO[WS_I-1].CORR_CCY = BPB5180_AWA_5180.RATE_INF[WS_I-1].CORR_CCY;
            BPCGTK.DATA.CCY_INFO[WS_I-1].UNIT = BPB5180_AWA_5180.RATE_INF[WS_I-1].UNIT;
            BPCGTK.DATA.CCY_INFO[WS_I-1].MKT_BUY = BPB5180_AWA_5180.RATE_INF[WS_I-1].MKT_BUY;
            BPCGTK.DATA.CCY_INFO[WS_I-1].MKT_SELL = BPB5180_AWA_5180.RATE_INF[WS_I-1].MKT_SELL;
        }
        S00_CALL_BPZGTK();
    }
    public void B030_BRW_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCGTK);
        BPCGTK.DATA.FUNC = 'B';
        BPCGTK.DATA.EXR_TYPE = BPB5180_AWA_5180.EXR_TYPE;
        BPCGTK.DATA.TENOR = BPB5180_AWA_5180.FWD_TENR;
        BPCGTK.DATA.BASE_TYPE = BPB5180_AWA_5180.BASE_TYP;
        BPCGTK.DATA.BASE_CCY = BPB5180_AWA_5180.BASE_CCY;
        BPCGTK.DATA.CONT_FLAG = 'Y';
        BPCGTK.DATA.CMPL_CNT = BPB5180_AWA_5180.CMPL_CNT;
        S00_CALL_BPZGTK();
    }
    public void S00_CALL_BPZGTK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_GEN_TQP_MKTR, BPCGTK);
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        BPCPRMR.DAT_PTR = BPRPRMT;
        IBS.CALLCPN(SCCGWA, CPN_INQ_PUB_PARM, BPCPRMR);
    }
    public void S000_CALL_SCZSUBS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_SET_SUB_TXN, SCCSUBS);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
