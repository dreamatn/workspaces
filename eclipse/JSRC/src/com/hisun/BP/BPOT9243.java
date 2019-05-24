package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT9243 {
    String K_CPN_S_FTP_PROD = "BP-S-FTP-PROD";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
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
        CEP.TRC(SCCGWA, "BPOT9243 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB9240_AWA_9240>");
        BPB9240_AWA_9240 = (BPB9240_AWA_9240) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        IBS.init(SCCGWA, BPCSNFTP);
        BPCSNFTP.FUNC = 'U';
        R000_INPUT_DATA_PROCESS();
        S010_CALL_BPZSNFTP();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void R000_INPUT_DATA_PROCESS() throws IOException,SQLException,Exception {
        BPCSNFTP.INFO.PROD_TYPE = BPB9240_AWA_9240.PROD_TYP;
        BPCSNFTP.INFO.ACC_CENTER = BPB9240_AWA_9240.ACCT_C;
        BPCSNFTP.INFO.CURRENCY_CODE = BPB9240_AWA_9240.CUR_CODE;
        BPCSNFTP.INFO.BID_CD = BPB9240_AWA_9240.BID_CODE;
        BPCSNFTP.INFO.FTP_PROD_TYPE = BPB9240_AWA_9240.FTP_P_TY;
        BPCSNFTP.INFO.FTP_RATE_CD = BPB9240_AWA_9240.FTP_RATE;
    }
    public void S010_CALL_BPZSNFTP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CPN_S_FTP_PROD, BPCSNFTP);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
