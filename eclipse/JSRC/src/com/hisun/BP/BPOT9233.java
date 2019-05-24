package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT9233 {
    String K_CPN_S_TERM_FTP = "BP-S-TERM-FTP";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCSTFTP BPCSTFTP = new BPCSTFTP();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB9230_AWA_9230 BPB9230_AWA_9230;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT9233 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB9230_AWA_9230>");
        BPB9230_AWA_9230 = (BPB9230_AWA_9230) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        IBS.init(SCCGWA, BPCSTFTP);
        BPCSTFTP.FUNC = 'U';
        R000_INPUT_DATA_PROCESS();
        S010_CALL_BPZSTFTP();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB9230_AWA_9230.CURRENCY.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CURRENCY_VACANT;
            WS_FLD_NO = BPB9230_AWA_9230.CURRENCY_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB9230_AWA_9230.TENOR_CD.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TENORCODE_VACANT;
            WS_FLD_NO = BPB9230_AWA_9230.TENOR_CD_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB9230_AWA_9230.O_RATE.trim().length() == 0 
            && BPB9230_AWA_9230.B_RATE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_DATA_VACANT;
            WS_FLD_NO = BPB9230_AWA_9230.O_RATE_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB9230_AWA_9230.O_RATE.trim().length() == 0 
            && (BPB9230_AWA_9230.ORATE_A1 != 0 
            || BPB9230_AWA_9230.ORATE_A2 != 0 
            || BPB9230_AWA_9230.ORATE_A3 != 0 
            || BPB9230_AWA_9230.ORATE_A4 != 0 
            || BPB9230_AWA_9230.ORATE_A5 != 0)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ADJ_RATE_VACANT;
            WS_FLD_NO = BPB9230_AWA_9230.O_RATE_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB9230_AWA_9230.B_RATE.trim().length() == 0 
            && (BPB9230_AWA_9230.BRATE_A1 != 0 
            || BPB9230_AWA_9230.BRATE_A2 != 0 
            || BPB9230_AWA_9230.BRATE_A3 != 0 
            || BPB9230_AWA_9230.BRATE_A4 != 0 
            || BPB9230_AWA_9230.BRATE_A5 != 0)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ADJ_RATE_VACANT;
            WS_FLD_NO = BPB9230_AWA_9230.O_RATE_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void R000_INPUT_DATA_PROCESS() throws IOException,SQLException,Exception {
        BPCSTFTP.INFO.CURRENCY_CODE = BPB9230_AWA_9230.CURRENCY;
        BPCSTFTP.INFO.TENOR_CD = BPB9230_AWA_9230.TENOR_CD;
        BPCSTFTP.INFO.O_RATE = BPB9230_AWA_9230.O_RATE;
        BPCSTFTP.INFO.O_RATE_ADJ1 = BPB9230_AWA_9230.ORATE_A1;
        BPCSTFTP.INFO.O_RATE_ADJ2 = BPB9230_AWA_9230.ORATE_A2;
        BPCSTFTP.INFO.O_RATE_ADJ3 = BPB9230_AWA_9230.ORATE_A3;
        BPCSTFTP.INFO.O_RATE_ADJ4 = BPB9230_AWA_9230.ORATE_A4;
        BPCSTFTP.INFO.O_RATE_ADJ5 = BPB9230_AWA_9230.ORATE_A5;
        BPCSTFTP.INFO.B_RATE = BPB9230_AWA_9230.B_RATE;
        BPCSTFTP.INFO.B_RATE_ADJ1 = BPB9230_AWA_9230.BRATE_A1;
        BPCSTFTP.INFO.B_RATE_ADJ2 = BPB9230_AWA_9230.BRATE_A2;
        BPCSTFTP.INFO.B_RATE_ADJ3 = BPB9230_AWA_9230.BRATE_A3;
        BPCSTFTP.INFO.B_RATE_ADJ4 = BPB9230_AWA_9230.BRATE_A4;
        BPCSTFTP.INFO.B_RATE_ADJ5 = BPB9230_AWA_9230.BRATE_A5;
        CEP.TRC(SCCGWA, "KIAKIA");
        CEP.TRC(SCCGWA, BPCSTFTP.INFO.CURRENCY_CODE);
        CEP.TRC(SCCGWA, BPCSTFTP.INFO.TENOR_CD);
        CEP.TRC(SCCGWA, BPCSTFTP.INFO.O_RATE);
        CEP.TRC(SCCGWA, BPCSTFTP.INFO.O_RATE_ADJ1);
        CEP.TRC(SCCGWA, BPCSTFTP.INFO.O_RATE_ADJ2);
        CEP.TRC(SCCGWA, BPCSTFTP.INFO.O_RATE_ADJ3);
        CEP.TRC(SCCGWA, BPCSTFTP.INFO.O_RATE_ADJ4);
        CEP.TRC(SCCGWA, BPCSTFTP.INFO.O_RATE_ADJ5);
        CEP.TRC(SCCGWA, BPCSTFTP.INFO.B_RATE);
        CEP.TRC(SCCGWA, BPCSTFTP.INFO.B_RATE_ADJ1);
        CEP.TRC(SCCGWA, BPCSTFTP.INFO.B_RATE_ADJ2);
        CEP.TRC(SCCGWA, BPCSTFTP.INFO.B_RATE_ADJ3);
        CEP.TRC(SCCGWA, BPCSTFTP.INFO.B_RATE_ADJ4);
        CEP.TRC(SCCGWA, BPCSTFTP.INFO.B_RATE_ADJ5);
    }
    public void S010_CALL_BPZSTFTP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CPN_S_TERM_FTP, BPCSTFTP);
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
