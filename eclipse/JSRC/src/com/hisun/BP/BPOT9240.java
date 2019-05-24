package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT9240 {
    String K_CPN_S_NONTERM_FTP = "BP-S-NONTERM-FTP";
    String K_CPN_S_PROD_INQ = "BP-P-QUERY-PRDT-INFO";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSNFTP BPCSNFTP = new BPCSNFTP();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB9240_AWA_9240 BPB9240_AWA_9240;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT9240 return!");
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
        BPCSNFTP.FUNC = 'B';
        BPCSNFTP.INFO.PROD_TYPE = BPB9240_AWA_9240.PROD_TYP;
        BPCSNFTP.INFO.ACC_CENTER = BPB9240_AWA_9240.ACCT_C;
        BPCSNFTP.INFO.CURRENCY_CODE = BPB9240_AWA_9240.CUR_CODE;
        BPCSNFTP.INFO.BID_CD = BPB9240_AWA_9240.BID_CODE;
        BPCSNFTP.INFO.FTP_PROD_TYPE = BPB9240_AWA_9240.FTP_P_TY;
        S010_CALL_BPZSNFTP();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB9240_AWA_9240.PROD_TYP);
        CEP.TRC(SCCGWA, BPB9240_AWA_9240.ACCT_C);
        CEP.TRC(SCCGWA, BPB9240_AWA_9240.CUR_CODE);
        CEP.TRC(SCCGWA, BPB9240_AWA_9240.BID_CODE);
        CEP.TRC(SCCGWA, BPB9240_AWA_9240.FTP_P_TY);
        if (BPB9240_AWA_9240.PROD_TYP.trim().length() > 0) {
            IBS.init(SCCGWA, BPCPQPRD);
            BPCPQPRD.PRDT_CODE = BPB9240_AWA_9240.PROD_TYP;
            S000_CALL_BPZPQPRD();
            if (BPCPQPRD.RC.RC_CODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
                WS_FLD_NO = BPB9240_AWA_9240.PROD_TYP_NO;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S010_CALL_BPZSNFTP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CPN_S_NONTERM_FTP, BPCSNFTP);
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CPN_S_PROD_INQ, BPCPQPRD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
