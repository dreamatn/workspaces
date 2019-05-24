package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT5400 {
    int JIBS_tmp_int;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    LNCSLOOA LNCSLOOA = new LNCSLOOA();
    LNCSSTBL LNCSSTBL = new LNCSSTBL();
    SCCGWA SCCGWA;
    LNB5400_AWA_5400 LNB5400_AWA_5400;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNOT5400 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB5400_AWA_5400>");
        LNB5400_AWA_5400 = (LNB5400_AWA_5400) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        LNCSLOOA.RC.RC_MMO = "LN";
        LNCSLOOA.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B020_FUNDMULA_MAIN();
    }
    public void B020_FUNDMULA_MAIN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSLOOA);
        LNCSLOOA.FUNC = 'I';
        if (LNB5400_AWA_5400.PAGE_ROW == 0) {
            if ("15".trim().length() == 0) LNCSLOOA.PAGE_ROW = 0;
            else LNCSLOOA.PAGE_ROW = Integer.parseInt("15");
        } else {
            if (LNB5400_AWA_5400.PAGE_ROW > 15) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_PAGE_ROW;
                S000_ERR_MSG_PROC();
            } else {
                LNCSLOOA.PAGE_ROW = LNB5400_AWA_5400.PAGE_ROW;
            }
        }
        if (LNB5400_AWA_5400.PAGE_NUM == 0) {
            if ("0".trim().length() == 0) LNCSLOOA.PAGE_NUM = 0;
            else LNCSLOOA.PAGE_NUM = Integer.parseInt("0");
        } else {
            LNCSLOOA.PAGE_NUM = LNB5400_AWA_5400.PAGE_NUM;
        }
        LNCSLOOA.FUNC = LNB5400_AWA_5400.FUNC;
        LNCSLOOA.CCY = LNB5400_AWA_5400.CCY;
        LNCSLOOA.I_BK_NO = LNB5400_AWA_5400.I_BK_NO;
        LNCSLOOA.O_BK_NO = LNB5400_AWA_5400.O_BK_NO;
        LNCSLOOA.O_BK_NM = LNB5400_AWA_5400.O_BK_NM;
        LNCSLOOA.SWT_CD = LNB5400_AWA_5400.SWT_CD;
        LNCSLOOA.METHOD = LNB5400_AWA_5400.METHOD;
        LNCSLOOA.BUSI_KND = LNB5400_AWA_5400.BUSI_KND;
        LNCSLOOA.BUSI_KND = "0001";
        LNCSLOOA.REMARK = LNB5400_AWA_5400.REMARK;
        S000_CALL_LNZSLOOA();
    }
    public void S000_CALL_LNZSLOOA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-LOOA-MAIN", LNCSLOOA);
        if (LNCSLOOA.RC.RC_CODE != 0) {
            WS_ERR_MSG = "" + LNCSLOOA.RC.RC_CODE;
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
