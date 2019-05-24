package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5163 {
    String CPN_MNT_TQP = "BP-MNT-TQP         ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_INQ_PUB_PARM = "BP-PARM-READ      ";
    String CPN_CCY_QUERY = "BP-INQUIRE-CCY    ";
    short WS_I = 0;
    short WS_K = 0;
    String WS_CCY = " ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_FIND_LAST_FLAG = ' ';
    BPOT5163_WS_OUT_DATA WS_OUT_DATA = new BPOT5163_WS_OUT_DATA();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPREXRT BPREXRT = new BPREXRT();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCMT BPCMT = new BPCMT();
    BPRPRMT BPRPRMT = new BPRPRMT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB5160_AWA_5160 BPB5160_AWA_5160;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT5163 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5160_AWA_5160>");
        BPB5160_AWA_5160 = (BPB5160_AWA_5160) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_BRW_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_BRW_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCMT);
        BPCMT.DATA.FUNC = 'B';
        BPCMT.DATA.EXR_TYPE = BPB5160_AWA_5160.EXR_TYPE;
        CEP.TRC(SCCGWA, BPB5160_AWA_5160.CCY_INFO[1-1].CCY);
        CEP.TRC(SCCGWA, BPB5160_AWA_5160.CCY_INFO[2-1].CCY);
        CEP.TRC(SCCGWA, BPB5160_AWA_5160.CCY_INFO[3-1].CCY);
        CEP.TRC(SCCGWA, BPB5160_AWA_5160.CCY_INFO[4-1].CCY);
        CEP.TRC(SCCGWA, BPB5160_AWA_5160.CCY_INFO[5-1].CCY);
        CEP.TRC(SCCGWA, BPB5160_AWA_5160.CCY_INFO[6-1].CCY);
        CEP.TRC(SCCGWA, BPB5160_AWA_5160.CCY_INFO[7-1].CCY);
        S00_CALL_BPZMT();
        CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[1-1].CS_BUY);
        CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[2-1].CS_BUY);
        CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[3-1].CS_BUY);
        CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[4-1].CS_BUY);
        CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[5-1].CS_BUY);
        CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[6-1].CS_BUY);
        CEP.TRC(SCCGWA, BPCMT.DATA.CCY_INFO[7-1].CS_BUY);
        IBS.init(SCCGWA, WS_OUT_DATA);
        WS_OUT_DATA.WS_X_EXR_TYPE = BPCMT.DATA.EXR_TYPE;
        WS_OUT_DATA.WS_X_EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        WS_OUT_DATA.WS_X_IPT_DT = SCCGWA.COMM_AREA.TR_DATE;
        WS_OUT_DATA.WS_X_IPT_TM = SCCGWA.COMM_AREA.TR_TIME;
        for (WS_K = 1; WS_K <= 7; WS_K += 1) {
            CEP.TRC(SCCGWA, WS_K);
            WS_I = 0;
            WS_FIND_LAST_FLAG = 'N';
            for (WS_I = 1; WS_I <= 20 
                && WS_FIND_LAST_FLAG != 'Y'; WS_I += 1) {
                CEP.TRC(SCCGWA, WS_I);
                CEP.TRC(SCCGWA, BPB5160_AWA_5160.CCY_INFO[WS_K-1].CCY);
                if (BPB5160_AWA_5160.CCY_INFO[WS_K-1].CCY.equalsIgnoreCase(BPCMT.DATA.CCY_INFO[WS_I-1].CCY)) {
                    BPCQCCY.DATA.CCY = BPCMT.DATA.CCY_INFO[WS_I-1].CCY;
                    S000_CALL_BPZQCCY();
                    WS_OUT_DATA.WS_X_CCY_INFO[WS_K-1].WS_X_CCY = BPCMT.DATA.CCY_INFO[WS_I-1].CCY;
                    WS_OUT_DATA.WS_X_CCY_INFO[WS_K-1].WS_X_CCY_NM = BPCQCCY.DATA.CUR_NM;
                    CEP.TRC(SCCGWA, WS_OUT_DATA.WS_X_CCY_INFO[WS_K-1].WS_X_CCY_NM);
                    WS_OUT_DATA.WS_X_CCY_INFO[WS_K-1].WS_X_UNIT = BPCMT.DATA.CCY_INFO[WS_I-1].UNIT;
                    WS_OUT_DATA.WS_X_CCY_INFO[WS_K-1].WS_X_FWD_TENOR = BPCMT.DATA.CCY_INFO[WS_I-1].FWD_TENOR;
                    WS_OUT_DATA.WS_X_CCY_INFO[WS_K-1].WS_X_RECENT_MID_RAT = BPCMT.DATA.CCY_INFO[WS_I-1].RECENT_MID_RAT;
                    WS_OUT_DATA.WS_X_CCY_INFO[WS_K-1].WS_X_MID_RAT = BPCMT.DATA.CCY_INFO[WS_I-1].MID_RAT;
                    WS_OUT_DATA.WS_X_CCY_INFO[WS_K-1].WS_X_FX_BUY = BPCMT.DATA.CCY_INFO[WS_I-1].FX_BUY;
                    WS_OUT_DATA.WS_X_CCY_INFO[WS_K-1].WS_X_FX_SELL = BPCMT.DATA.CCY_INFO[WS_I-1].FX_SELL;
                    WS_OUT_DATA.WS_X_CCY_INFO[WS_K-1].WS_X_CS_BUY = BPCMT.DATA.CCY_INFO[WS_I-1].CS_BUY;
                    WS_OUT_DATA.WS_X_CCY_INFO[WS_K-1].WS_X_CS_SELL = BPCMT.DATA.CCY_INFO[WS_I-1].CS_SELL;
                    CEP.TRC(SCCGWA, WS_OUT_DATA.WS_X_CCY_INFO[WS_K-1].WS_X_CCY);
                    CEP.TRC(SCCGWA, WS_OUT_DATA.WS_X_CCY_INFO[WS_K-1].WS_X_MID_RAT);
                    CEP.TRC(SCCGWA, WS_OUT_DATA.WS_X_CCY_INFO[WS_K-1].WS_X_FX_BUY);
                    CEP.TRC(SCCGWA, WS_OUT_DATA.WS_X_CCY_INFO[WS_K-1].WS_X_FX_SELL);
                    CEP.TRC(SCCGWA, WS_OUT_DATA.WS_X_CCY_INFO[WS_K-1].WS_X_CS_BUY);
                    CEP.TRC(SCCGWA, WS_OUT_DATA.WS_X_CCY_INFO[WS_K-1].WS_X_CS_SELL);
                    WS_FIND_LAST_FLAG = 'Y';
                } else {
                    WS_FIND_LAST_FLAG = 'N';
                }
            }
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "BPX01";
        SCCFMT.DATA_PTR = WS_OUT_DATA;
        SCCFMT.DATA_LEN = 773;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S00_CALL_BPZMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_MNT_TQP, BPCMT, true);
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        BPCPRMR.DAT_PTR = BPRPRMT;
        IBS.CALLCPN(SCCGWA, CPN_INQ_PUB_PARM, BPCPRMR);
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_CCY_QUERY, BPCQCCY);
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
