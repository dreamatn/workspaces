package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4303 {
    boolean pgmRtn = false;
    String K_QUERY_CCY_INFO = "BP-MAINTAIN-CCY";
    String K_CMP_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    String K_OUTPUT_FMT = "BPX01";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCMCCY BPCMCCY = new BPCMCCY();
    BPCCCYO BPCCCYO = new BPCCCYO();
    SCCGWA SCCGWA;
    BPB4301_AWA_4301 BPB4301_AWA_4301;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT4303 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4301_AWA_4301>");
        BPB4301_AWA_4301 = (BPB4301_AWA_4301) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        IBS.init(SCCGWA, BPCMCCY);
        IBS.init(SCCGWA, BPCCCYO);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_TRANS_AWA_MCCY();
        if (pgmRtn) return;
        B020_SET_RETURN_INFO();
        if (pgmRtn) return;
        B030_QUERY_CCY();
        if (pgmRtn) return;
        B040_TRANS_MCCY_OUTPUT();
        if (pgmRtn) return;
    }
    public void B010_TRANS_AWA_MCCY() throws IOException,SQLException,Exception {
        BPCMCCY.OP_FUNC = '0';
        BPCMCCY.DATA.CCY = BPB4301_AWA_4301.CCY;
        BPCMCCY.DATA.CCY_CD = BPB4301_AWA_4301.CCY_CD;
        CEP.TRC(SCCGWA, BPCMCCY.DATA.CCY);
        CEP.TRC(SCCGWA, BPCMCCY.DATA.CCY_CD);
    }
    public void B020_SET_RETURN_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCSUBS);
        SCCSUBS.AP_CODE = 999;
        SCCSUBS.TR_CODE = 4307;
        S000_SET_SUBS_TRN();
        if (pgmRtn) return;
    }
    public void B030_QUERY_CCY() throws IOException,SQLException,Exception {
        T000_CALL_BPZMCCY();
        if (pgmRtn) return;
    }
    public void B040_TRANS_MCCY_OUTPUT() throws IOException,SQLException,Exception {
        T000_FORMAT_OUTPUT_BPM56();
        if (pgmRtn) return;
    }
    public void T000_CALL_BPZMCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_QUERY_CCY_INFO, BPCMCCY);
        if (BPCMCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCMCCY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_FORMAT_OUTPUT_BPM56() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCCCYO);
        IBS.init(SCCGWA, SCCFMT);
        S000_TRANS_DATA_PARAMETER();
        if (pgmRtn) return;
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCCCYO;
        SCCFMT.DATA_LEN = 211;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CMP_SET_SBUS_TRN, SCCSUBS);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCCCYO.CCY = BPCMCCY.DATA.CCY;
        BPCCCYO.CCY_CD = BPCMCCY.DATA.CCY_CD;
        CEP.TRC(SCCGWA, BPCCCYO.CCY);
        CEP.TRC(SCCGWA, BPCCCYO.CCY_CD);
        BPCCCYO.CUR_NM = BPCMCCY.DATA.CUR_NM;
        BPCCCYO.CUR_CNM = BPCMCCY.DATA.CUR_CNM;
        CEP.TRC(SCCGWA, BPCCCYO.CUR_CNM);
        BPCCCYO.EFF_DT = BPCMCCY.DATA.EFF_DT;
        BPCCCYO.EXP_DT = BPCMCCY.DATA.EXP_DT;
        BPCCCYO.CNTY_CD = BPCMCCY.DATA.CNTY_CD;
        BPCCCYO.CITY_CD = BPCMCCY.DATA.CITY_CD;
        BPCCCYO.UNIT_CURU_NAME = BPCMCCY.DATA.UNIT_CURU_NAME;
        BPCCCYO.CENT_CURU_NAME = BPCMCCY.DATA.CENT_CURU_NAME;
        BPCCCYO.RGN_CCY = BPCMCCY.DATA.RGN_CCY;
        BPCCCYO.DEC_MTH = BPCMCCY.DATA.DEC_MTH;
        BPCCCYO.CASH_MTH = BPCMCCY.DATA.CASH_MTH;
        BPCCCYO.RND_MTH = BPCMCCY.DATA.RND_MTH;
        BPCCCYO.TR_FLG = BPCMCCY.DATA.TR_FLG;
        BPCCCYO.CASH_FLG = BPCMCCY.DATA.CASH_FLG;
        BPCCCYO.CHGU_MTH = BPCMCCY.DATA.CHGU_MTH;
        BPCCCYO.EXH_FLG = BPCMCCY.DATA.EXH_FLG;
        BPCCCYO.CALR_STD = BPCMCCY.DATA.CALR_STD;
        BPCCCYO.CAL_CD = BPCMCCY.DATA.CAL_CD;
        BPCCCYO.ISR_DAYS = BPCMCCY.DATA.ISR_DAYS;
        BPCCCYO.BAL_DAYS = BPCMCCY.DATA.BAL_DAYS;
        BPCCCYO.CHG_CCY = BPCMCCY.DATA.CHG_CCY;
        CEP.TRC(SCCGWA, BPCCCYO.CHG_CCY);
        CEP.TRC(SCCGWA, BPCCCYO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
