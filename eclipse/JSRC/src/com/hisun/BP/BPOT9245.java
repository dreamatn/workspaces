package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT9245 {
    String K_CPN_S_NONTERM_FTP = "BP-S-NONTERM-FTP";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCSNFTP BPCSNFTP = new BPCSNFTP();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB9240_AWA_9240 BPB9240_AWA_9240;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT9245 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB9240_AWA_9240>");
        BPB9240_AWA_9240 = (BPB9240_AWA_9240) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSNFTP);
        BPCSNFTP.FUNC = 'I';
        BPCSNFTP.INFO.PROD_TYPE = BPB9240_AWA_9240.PROD_TYP;
        BPCSNFTP.INFO.ACC_CENTER = BPB9240_AWA_9240.ACCT_C;
        BPCSNFTP.INFO.CURRENCY_CODE = BPB9240_AWA_9240.CUR_CODE;
        BPCSNFTP.INFO.BID_CD = BPB9240_AWA_9240.BID_CODE;
        BPCSNFTP.INFO.FTP_PROD_TYPE = BPB9240_AWA_9240.FTP_P_TY;
        S010_CALL_BPZSNFTP();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB9240_AWA_9240.CUR_CODE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CURRENCY_VACANT;
            WS_FLD_NO = BPB9240_AWA_9240.CUR_CODE_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S010_CALL_BPZSNFTP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CPN_S_NONTERM_FTP, BPCSNFTP);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
