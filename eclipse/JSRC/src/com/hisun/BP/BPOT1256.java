package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BA.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1256 {
    int JIBS_tmp_int;
    String K_OUTPUT_FMT_X = "BPX01";
    String K_HIS_REMARK = "TXN INFOMATION MAINTAIN";
    String CPN_F_F_TX_INFO = "BP-F-F-TX-INFO      ";
    short K_MAX_LEN = 23;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_CNTR_NO = " ";
    short WS_ACCT_CNT = 0;
    BPOT1256_WS_CONTRACT[] WS_CONTRACT = new BPOT1256_WS_CONTRACT[25];
    char WS_LEAVE = ' ';
    short WS_I = 0;
    short WS_J = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BACFMST1 BACFMST1 = new BACFMST1();
    BARMST1 BARMST1 = new BARMST1();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB1256_AWA_1256 BPB1256_AWA_1256;
    public BPOT1256() {
        for (int i=0;i<25;i++) WS_CONTRACT[i] = new BPOT1256_WS_CONTRACT();
    }
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1256 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1256_AWA_1256>");
        BPB1256_AWA_1256 = (BPB1256_AWA_1256) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_FEE_ROLLBACK_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB1256_AWA_1256.BSNS_NO.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CTR_NO_IS_BLK;
            if (BPB1256_AWA_1256.BSNS_NO.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(BPB1256_AWA_1256.BSNS_NO);
            S000_ERR_MSG_PROC();
        }
        WS_LEAVE = 'N';
        CEP.TRC(SCCGWA, BPB1256_AWA_1256.BSNS_NO);
        CEP.TRC(SCCGWA, K_MAX_LEN);
        for (WS_I = 1; WS_I <= K_MAX_LEN 
            && WS_LEAVE != 'Y'; WS_I += 1) {
            if (BPB1256_AWA_1256.BSNS_NO == null) BPB1256_AWA_1256.BSNS_NO = "";
            JIBS_tmp_int = BPB1256_AWA_1256.BSNS_NO.length();
            for (int i=0;i<25-JIBS_tmp_int;i++) BPB1256_AWA_1256.BSNS_NO += " ";
            CEP.TRC(SCCGWA, BPB1256_AWA_1256.BSNS_NO.substring(WS_I - 1, WS_I + 1 - 1));
            if (BPB1256_AWA_1256.BSNS_NO == null) BPB1256_AWA_1256.BSNS_NO = "";
            JIBS_tmp_int = BPB1256_AWA_1256.BSNS_NO.length();
            for (int i=0;i<25-JIBS_tmp_int;i++) BPB1256_AWA_1256.BSNS_NO += " ";
            WS_CONTRACT[WS_I-1].WS_IDX = BPB1256_AWA_1256.BSNS_NO.substring(WS_I - 1, WS_I + 1 - 1).charAt(0);
            if (WS_CONTRACT[WS_I-1].WS_IDX == ' ' 
                || (WS_CONTRACT[WS_I-1].WS_IDX != ' ' 
                && WS_I == K_MAX_LEN)) {
                if (WS_CONTRACT[WS_I-1].WS_IDX == ' ') {
                    WS_J = (short) (WS_I - 5);
                } else {
                    WS_J = (short) (WS_I - 4);
                }
                CEP.TRC(SCCGWA, WS_I);
                CEP.TRC(SCCGWA, WS_J);
                if (WS_J > 0) {
                    if (BPB1256_AWA_1256.BSNS_NO == null) BPB1256_AWA_1256.BSNS_NO = "";
                    JIBS_tmp_int = BPB1256_AWA_1256.BSNS_NO.length();
                    for (int i=0;i<25-JIBS_tmp_int;i++) BPB1256_AWA_1256.BSNS_NO += " ";
                    WS_CNTR_NO = BPB1256_AWA_1256.BSNS_NO.substring(0, WS_J);
                    WS_J = (short) (WS_J + 1);
                    if (BPB1256_AWA_1256.BSNS_NO == null) BPB1256_AWA_1256.BSNS_NO = "";
                    JIBS_tmp_int = BPB1256_AWA_1256.BSNS_NO.length();
                    for (int i=0;i<25-JIBS_tmp_int;i++) BPB1256_AWA_1256.BSNS_NO += " ";
                    if (BPB1256_AWA_1256.BSNS_NO.substring(WS_J - 1, WS_J + 4 - 1).trim().length() == 0) WS_ACCT_CNT = 0;
                    else WS_ACCT_CNT = Short.parseShort(BPB1256_AWA_1256.BSNS_NO.substring(WS_J - 1, WS_J + 4 - 1));
                } else {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CTR_NO_IS_BLK;
                    if (BPB1256_AWA_1256.BSNS_NO.trim().length() == 0) WS_FLD_NO = 0;
                    else WS_FLD_NO = Short.parseShort(BPB1256_AWA_1256.BSNS_NO);
                    S000_ERR_MSG_PROC();
                }
                WS_LEAVE = 'Y';
            }
        }
        CEP.TRC(SCCGWA, WS_CNTR_NO);
        CEP.TRC(SCCGWA, WS_ACCT_CNT);
        IBS.init(SCCGWA, BACFMST1);
        IBS.init(SCCGWA, BARMST1);
        BACFMST1.FUNC = 'I';
        BARMST1.KEY.CNTR_NO = WS_CNTR_NO;
        BARMST1.KEY.ACCT_CNT = WS_ACCT_CNT;
        S000_CALL_BAZFMST1_1();
        if (BACFMST1.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BACFMST1.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_FEE_ROLLBACK_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = BPB1256_AWA_1256.AC_TY;
        BPCFFTXI.TX_DATA.PROC_TYPE = '2';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = BPB1256_AWA_1256.CHG_AC;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = BPB1256_AWA_1256.CHG_CCY;
        BPCFFTXI.TX_DATA.CCY_TYPE = BPB1256_AWA_1256.CCY_TYPE;
        BPCFFTXI.TX_DATA.BAT_OTRT_DT = BPB1256_AWA_1256.AC_DT;
        BPCFFTXI.TX_DATA.BAT_OTRT_JRN = BPB1256_AWA_1256.JRN_NO;
        BPCFFTXI.TX_DATA.BAT_OTRT_SEQ = BPB1256_AWA_1256.JRN_SEQ;
        S000_CALL_BPZFFTXI();
    }
    public void S000_CALL_BPZFFTXI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_F_TX_INFO, BPCFFTXI);
        if (BPCFFTXI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFTXI.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BAZFMST1_1() throws IOException,SQLException,Exception {
        BACFMST1.REC_PTR = BARMST1;
        BACFMST1.REC_LEN = 1163;
        IBS.CALLCPN(SCCGWA, "BA-R-BAZFMST1", BACFMST1);
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
