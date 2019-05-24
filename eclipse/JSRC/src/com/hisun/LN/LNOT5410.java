package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT5410 {
    int JIBS_tmp_int;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    LNCSLUUA LNCSLUUA = new LNCSLUUA();
    LNCSSTBL LNCSSTBL = new LNCSSTBL();
    SCCGWA SCCGWA;
    LNB5410_AWA_5410 LNB5410_AWA_5410;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNOT5410 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB5410_AWA_5410>");
        LNB5410_AWA_5410 = (LNB5410_AWA_5410) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        LNCSLUUA.RC.RC_MMO = "LN";
        LNCSLUUA.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B020_FUNDMULA_MAIN();
    }
    public void B020_FUNDMULA_MAIN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSLUUA);
        LNCSLUUA.FUNC = 'B';
        if (LNB5410_AWA_5410.PAGE_ROW == 0) {
            if ("15".trim().length() == 0) LNCSLUUA.PAGE_ROW = 0;
            else LNCSLUUA.PAGE_ROW = Integer.parseInt("15");
        } else {
            if (LNB5410_AWA_5410.PAGE_ROW > 15) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_PAGE_ROW;
                S000_ERR_MSG_PROC();
            } else {
                LNCSLUUA.PAGE_ROW = LNB5410_AWA_5410.PAGE_ROW;
            }
        }
        if (LNB5410_AWA_5410.PAGE_NUM == 0) {
            if ("0".trim().length() == 0) LNCSLUUA.PAGE_NUM = 0;
            else LNCSLUUA.PAGE_NUM = Integer.parseInt("0");
        } else {
            LNCSLUUA.PAGE_NUM = LNB5410_AWA_5410.PAGE_NUM;
        }
        LNCSLUUA.FUNC = LNB5410_AWA_5410.FUNC;
        LNCSLUUA.BUSI_KND = LNB5410_AWA_5410.BUSI_KND;
        LNCSLUUA.CCY = LNB5410_AWA_5410.CCY;
        LNCSLUUA.LN_ACNO = LNB5410_AWA_5410.LN_ACNO;
        LNCSLUUA.SG_SEQNO = LNB5410_AWA_5410.SG_SEQNO;
        LNCSLUUA.DCL_TYP = LNB5410_AWA_5410.DCL_TYP;
        LNCSLUUA.LN_NM = LNB5410_AWA_5410.LN_NM;
        LNCSLUUA.VAL_DT = LNB5410_AWA_5410.VAL_DT;
        LNCSLUUA.BAL = LNB5410_AWA_5410.BAL;
        LNCSLUUA.P_ACNO = LNB5410_AWA_5410.P_ACNO;
        LNCSLUUA.P_AC_CS = LNB5410_AWA_5410.P_AC_CS;
        LNCSLUUA.P_ACNM = LNB5410_AWA_5410.P_ACNM;
        LNCSLUUA.ACNO = LNB5410_AWA_5410.ACNO;
        LNCSLUUA.ACNM = LNB5410_AWA_5410.ACNM;
        LNCSLUUA.F_DT = LNB5410_AWA_5410.F_DT;
        LNCSLUUA.SP_DAY = LNB5410_AWA_5410.SP_DAY;
        LNCSLUUA.DUE_DT = LNB5410_AWA_5410.DUE_DT;
        LNCSLUUA.MON = LNB5410_AWA_5410.MON;
        LNCSLUUA.M_FLG = LNB5410_AWA_5410.M_FLG;
        LNCSLUUA.STOP_MTH = LNB5410_AWA_5410.STOP_MTH;
        LNCSLUUA.TERM_BAL = LNB5410_AWA_5410.TERM_BAL;
        LNCSLUUA.ONCE_BAL = LNB5410_AWA_5410.ONCE_BAL;
        LNCSLUUA.REMARK = LNB5410_AWA_5410.REMARK;
        LNCSLUUA.TRY_TM = LNB5410_AWA_5410.TRY_TM;
        S000_CALL_LNZSLUUA();
    }
    public void S000_CALL_LNZSLUUA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-LUUA-MAIN", LNCSLUUA);
        if (LNCSLUUA.RC.RC_CODE != 0) {
            WS_ERR_MSG = "" + LNCSLUUA.RC.RC_CODE;
            JIBS_tmp_int = WS_ERR_MSG.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_ERR_MSG = "0" + WS_ERR_MSG;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
