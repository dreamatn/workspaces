package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT9242 {
    String K_CPN_S_NONTERM_FTP = "BP-S-NONTERM-FTP";
    String K_CPN_S_PROD_INQ = "BP-P-QUERY-PRDT-INFO";
    String K_CPN_S_ACCT_INQ = "BP-P-INQ-ORG";
    String K_CPN_S_CCY = "BP-INQUIRE-CCY";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSNFTP BPCSNFTP = new BPCSNFTP();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB9240_AWA_9240 BPB9240_AWA_9240;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT9242 return!");
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
        BPCSNFTP.FUNC = 'A';
        BPCSNFTP.INFO.PROD_TYPE = BPB9240_AWA_9240.PROD_TYP;
        BPCSNFTP.INFO.ACC_CENTER = BPB9240_AWA_9240.ACCT_C;
        BPCSNFTP.INFO.CURRENCY_CODE = BPB9240_AWA_9240.CUR_CODE;
        BPCSNFTP.INFO.BID_CD = BPB9240_AWA_9240.BID_CODE;
        BPCSNFTP.INFO.FTP_PROD_TYPE = BPB9240_AWA_9240.FTP_P_TY;
        BPCSNFTP.INFO.FTP_RATE_CD = BPB9240_AWA_9240.FTP_RATE;
        BPCSNFTP.INFO.FTP_RATE_ADJ1 = BPB9240_AWA_9240.RATE_A1;
        BPCSNFTP.INFO.FTP_RATE_ADJ2 = BPB9240_AWA_9240.RATE_A2;
        S010_CALL_BPZSNFTP();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
        BPCPQORG.BR = BPB9240_AWA_9240.ACCT_C;
        S000_CALL_BPZPQORG();
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            WS_FLD_NO = BPB9240_AWA_9240.ACCT_C_NO;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = BPB9240_AWA_9240.CUR_CODE;
        S010_CALL_BPZSCCYT();
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            WS_FLD_NO = BPB9240_AWA_9240.CUR_CODE_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB9240_AWA_9240.BID_CODE != '0' 
            && BPB9240_AWA_9240.BID_CODE != '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BID_CODE_INVALID;
            WS_FLD_NO = BPB9240_AWA_9240.BID_CODE_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB9240_AWA_9240.FTP_P_TY != '0' 
            && BPB9240_AWA_9240.FTP_P_TY != '1') {
            CEP.TRC(SCCGWA, "KAIKIA2");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FTP_PROD_TYP_INVALID;
            WS_FLD_NO = BPB9240_AWA_9240.FTP_P_TY_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB9240_AWA_9240.FTP_RATE.trim().length() == 0) {
            CEP.TRC(SCCGWA, "KAIKIA3");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FTP_RATE_VACANT;
            WS_FLD_NO = BPB9240_AWA_9240.FTP_RATE_NO;
            S000_ERR_MSG_PROC();
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
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CPN_S_ACCT_INQ, BPCPQORG);
    }
    public void S010_CALL_BPZSCCYT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CPN_S_CCY, BPCQCCY);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
