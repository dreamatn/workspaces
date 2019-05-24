package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5181 {
    String CPN_GEN_TQP_MIDR = "BP-GEN-TQP-MIDR    ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_INQ_PUB_PARM = "BP-PARM-READ      ";
    String CPN_SET_SUB_TXN = "SC-SET-SUBS-TRANS ";
    short WS_I = 0;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPOT5181_WS_OUT_DATA WS_OUT_DATA = new BPOT5181_WS_OUT_DATA();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPREXRT BPREXRT = new BPREXRT();
    BPCQCCY BPCQCCY = new BPCQCCY();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCGTI BPCGTI = new BPCGTI();
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
        CEP.TRC(SCCGWA, "BPOT5181 return!");
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
        IBS.init(SCCGWA, BPCGTI);
        BPCGTI.DATA.FUNC = 'A';
        BPCGTI.DATA.EXR_TYPE = BPB5180_AWA_5180.EXR_TYPE;
        BPCGTI.DATA.TENOR = BPB5180_AWA_5180.FWD_TENR;
        BPCGTI.DATA.BASE_TYPE = BPB5180_AWA_5180.BASE_TYP;
        BPCGTI.DATA.BASE_CCY = BPB5180_AWA_5180.BASE_CCY;
        BPCGTI.DATA.OUT_REC_CNT = BPB5180_AWA_5180.REC_CNT;
        for (WS_I = 1; WS_I <= BPB5180_AWA_5180.REC_CNT; WS_I += 1) {
            BPCGTI.DATA.CCY_INFO[WS_I-1].CCY = BPB5180_AWA_5180.RATE_INF[WS_I-1].CCY;
            BPCGTI.DATA.CCY_INFO[WS_I-1].CORR_CCY = BPB5180_AWA_5180.RATE_INF[WS_I-1].CORR_CCY;
            BPCGTI.DATA.CCY_INFO[WS_I-1].UNIT = BPB5180_AWA_5180.RATE_INF[WS_I-1].UNIT;
            BPCGTI.DATA.CCY_INFO[WS_I-1].MID_RATE = BPB5180_AWA_5180.RATE_INF[WS_I-1].MID_RATE;
        }
        S00_CALL_BPZGTI();
    }
    public void B030_BRW_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCGTI);
        BPCGTI.DATA.FUNC = 'B';
        BPCGTI.DATA.EXR_TYPE = BPB5180_AWA_5180.EXR_TYPE;
        BPCGTI.DATA.TENOR = BPB5180_AWA_5180.FWD_TENR;
        BPCGTI.DATA.BASE_TYPE = BPB5180_AWA_5180.BASE_TYP;
        BPCGTI.DATA.BASE_CCY = BPB5180_AWA_5180.BASE_CCY;
        BPCGTI.DATA.CONT_FLAG = 'Y';
        BPCGTI.DATA.CMPL_CNT = BPB5180_AWA_5180.CMPL_CNT;
        S00_CALL_BPZGTI();
    }
    public void S00_CALL_BPZGTI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_GEN_TQP_MIDR, BPCGTI);
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
