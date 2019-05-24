package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4307 {
    String K_OUTPUT_FMT = "BPM56";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCMCCY BPCMCCY = new BPCMCCY();
    BPCCCYO BPCCCYO = new BPCCCYO();
    SCCGWA SCCGWA;
    BPB4301_AWA_4301 BPB4301_AWA_4301;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        B00_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4307 return!");
        Z_RET();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4301_AWA_4301>");
        BPB4301_AWA_4301 = (BPB4301_AWA_4301) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCMCCY);
        BPCMCCY.DATA.CCY = BPB4301_AWA_4301.CCY;
        BPCMCCY.DATA.CCY_CD = BPB4301_AWA_4301.CCY_CD;
        BPCMCCY.OP_FUNC = '0';
        S000_CALL_BPZCACCY();
        B20_TRANS_DATA_OUTPUT();
    }
    public void S000_CALL_BPZCACCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-MAINTAIN-CCY", BPCMCCY);
        if (BPCMCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCMCCY.RC);
            S00_ERR_MSG_PROC();
        }
    }
    public void S00_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void B20_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCCCYO);
        IBS.init(SCCGWA, SCCFMT);
        R00_TRANS_DATA_PARAMETER();
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCCCYO;
        SCCFMT.DATA_LEN = 211;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R00_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCCCYO.CCY = BPCMCCY.DATA.CCY;
        BPCCCYO.CCY_CD = BPCMCCY.DATA.CCY_CD;
        BPCCCYO.CUR_NM = BPCMCCY.DATA.CUR_NM;
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
        BPCMCCY.DATA.ISR_DAYS = BPB4301_AWA_4301.ISR_DAYS;
        BPCMCCY.DATA.BAL_DAYS = BPB4301_AWA_4301.BAL_DAYS;
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
